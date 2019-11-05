public class Newsfeedfetcher {

























































































		public String toString() {
			return "PostData " +
					"( itemID: " + itemID +
					", senderID: " + senderID +
					", senderName: " + senderName +
					", timestamp: " + timestamp.toString() +
					", mail: \"" + mail + "\" )";
		}
	}






















	public EWolfResponse handleData(JsonElement jsonReq) {
		Gson gson = new Gson();
		JsonReqNewsFeedParams jsonReqParams;
		try {
			jsonReqParams = gson.fromJson(jsonReq, JsonReqNewsFeedParams.class);
		} catch (Exception e) {
			e.printStackTrace();
			return new NewsFeedResponse(RES_BAD_REQUEST);
		}

		if (jsonReqParams.newsOf == null) {
			return new NewsFeedResponse(RES_BAD_REQUEST,
					"Must specify whose news feed to fetch.");
		}

		List<Post> posts;
		try {
			if (jsonReqParams.newsOf.equals("user")) {
				posts = fetchPostsForUser(jsonReqParams.userID);
			} else if (jsonReqParams.newsOf.equals("wolfpack")) {
				posts = fetchPostsForWolfpack(jsonReqParams.wolfpackName);
			} else {
				return new NewsFeedResponse(RES_BAD_REQUEST,
						"Request type should be either \"user\" or \"wolfpack\"");
			}
		} catch (ProfileNotFoundException e) {
			e.printStackTrace();
			return new NewsFeedResponse(RES_NOT_FOUND, "User with given ID wasn't found.");
		}

		return (posts != null) ?
				(new NewsFeedResponse(filterPosts(posts, jsonReqParams.maxMessages,
						jsonReqParams.newerThan, jsonReqParams.olderThan)))
				: (new NewsFeedResponse(new HashSet<PostData>()));
	}






































































}
