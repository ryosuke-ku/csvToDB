public class Inboxfetcher {












































































	public EWolfResponse handleData(JsonElement jsonReq) {
		Gson gson = new Gson();
		JsonReqInboxParams jsonReqParams;
		try {
			jsonReqParams = gson.fromJson(jsonReq, JsonReqInboxParams.class);
		} catch (Exception e) {
			return new InboxResponse(RES_BAD_REQUEST);
		}
		List<InboxMessage> lst = new ArrayList<InboxMessage>();			

		List<SocialMessage> messages = inboxCache.get();

		for (SocialMessage m : messages) {
			if (m.getClass() != ContentMessage.class) {
				continue;
			}

			InboxMessage msg = new InboxMessage();

			try {
				Profile sender = m.getSender();
				msg.senderID = sender.getUserId().toString();
				msg.senderName = sender.getName();
			} catch (ProfileNotFoundException e) {
				msg.senderID = SENDER_NOT_FOUND_MESSAGE;
				msg.senderName = SENDER_NOT_FOUND_MESSAGE;
				e.printStackTrace();
			}
			msg.timestamp = m.getTimestamp();

			if(jsonReqParams.isMatchCriteria(msg)) {
				msg.mail = ((ContentMessage)m).getMessage();
				lst.add(msg);
			}
		}
		//sort by timestamp
		Collections.sort(lst);	

		if (jsonReqParams.maxMessages != null && lst.size() > jsonReqParams.maxMessages) {
			lst = lst.subList(0, jsonReqParams.maxMessages);
		}

		return new InboxResponse(lst);
	}
}
