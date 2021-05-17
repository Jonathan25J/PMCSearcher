package me.jonaqhan.pmcsearcher.website;

public class UsData {

	public String user;
	public String level;
	public String title;
	public String status;
	public int subscribers;
	public int profileViews;
	public int xp;
	public int submissions;
	public int submissionViews;
	public int diamonds;
	public int downloads;
	public int favorites;
	public int comments;
	public int forumThreads;
	public int discussionPosts;
	public int emeralds;
	public String joinDate;

	public UsData(String user, String level, String title, String status, int subscribers, int profileViews, int xp,
			int submissions, int submissionViews, int diamonds, int downloads, int favorites, int comments,
			int forumThreads, int discussionPosts, int emeralds, String joinDate, String minecraftname) {
		super();
		this.user = user;
		this.level = level;
		this.title = title;
		this.status = status;
		this.subscribers = subscribers;
		this.profileViews = profileViews;
		this.xp = xp;
		this.submissions = submissions;
		this.submissionViews = submissionViews;
		this.diamonds = diamonds;
		this.downloads = downloads;
		this.favorites = favorites;
		this.comments = comments;
		this.forumThreads = forumThreads;
		this.discussionPosts = discussionPosts;
		this.emeralds = emeralds;
		this.joinDate = joinDate;
		this.minecraftname = minecraftname;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getSubscribers() {
		return subscribers;
	}

	public void setSubscribers(int subscribers) {
		this.subscribers = subscribers;
	}

	public int getProfileViews() {
		return profileViews;
	}

	public void setProfileViews(int profileViews) {
		this.profileViews = profileViews;
	}

	public double getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public int getSubmissions() {
		return submissions;
	}

	public void setSubmissions(int submissions) {
		this.submissions = submissions;
	}

	public int getSubmissionViews() {
		return submissionViews;
	}

	public void setSubmissionViews(int submissionViews) {
		this.submissionViews = submissionViews;
	}

	public int getDiamonds() {
		return diamonds;
	}

	public void setDiamonds(int diamonds) {
		this.diamonds = diamonds;
	}

	public int getDownloads() {
		return downloads;
	}

	public void setDownloads(int downloads) {
		this.downloads = downloads;
	}

	public int getFavorites() {
		return favorites;
	}

	public void setFavorites(int favorites) {
		this.favorites = favorites;
	}

	public int getComments() {
		return comments;
	}

	public void setComments(int comments) {
		this.comments = comments;
	}

	public int getForumThreads() {
		return forumThreads;
	}

	public void setForumThreads(int forumThreads) {
		this.forumThreads = forumThreads;
	}

	public int getDiscussionPosts() {
		return discussionPosts;
	}

	public void setDiscussionPosts(int discussionPosts) {
		this.discussionPosts = discussionPosts;
	}

	public int getEmeralds() {
		return emeralds;
	}

	public void setEmeralds(int emeralds) {
		this.emeralds = emeralds;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public String getMinecraftname() {
		return minecraftname;
	}

	public void setMinecraftname(String minecraftname) {
		this.minecraftname = minecraftname;
	}

	public String minecraftname;

}
