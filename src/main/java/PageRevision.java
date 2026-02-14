public class PageRevision {
    private final String user;
    private final String timeStamp;

    public PageRevision(String user, String timeStamp) {
        this.user = user;
        this.timeStamp = timeStamp;
    }

    public String getUser() {
        return user;
    }

    public String getTimeStamp() {
        return timeStamp;
    }
}