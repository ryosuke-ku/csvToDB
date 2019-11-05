public class Wolfpackmembersfetcher {


























































	public EWolfResponse handleData(JsonElement jsonReq) {
		Gson gson = new Gson();
		JsonReqWolfpackMembersParams jsonReqParams;
		try {
			jsonReqParams = gson.fromJson(jsonReq, JsonReqWolfpackMembersParams.class);
		} catch (Exception e) {
			e.printStackTrace();
			return new WolfpackMembersResponse(RES_BAD_REQUEST);
		}

		Map<String,List<Profile>> wolfpacksMembersMap = wolfpacksMembersCache.get();
		Set<Profile> profiles = new HashSet<Profile>();
		if (jsonReqParams.wolfpackName == null) {
			for (Map.Entry<String,List<Profile>> entry : wolfpacksMembersMap.entrySet()) {
				profiles.addAll(entry.getValue());
			}
		} else {
			List<Profile> wMembers = wolfpacksMembersMap.get(jsonReqParams.wolfpackName);
			if (wMembers != null) {
				profiles.addAll(wMembers);
			} else {
				return new WolfpackMembersResponse(RES_NOT_FOUND);
			}
		}

		List<ProfileData> resList = new ArrayList<ProfileData>();

		for (Profile profile: profiles) {
			resList.add(new ProfileData(profile.getName(), profile.getUserId().toString()));
		}
		return new WolfpackMembersResponse(resList);
	}

}
