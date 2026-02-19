public class WikipediaPage {

    private final String title;
    private final PageRevision[] revisions;

    public WikipediaPage(String title, PageRevision[] revisions) {
        this.title = title;
        this.revisions = revisions;
    }

    public String getTitle() {
        return title;
    }

    public PageRevision[] getRevisions() {
        return revisions;
    }

    public String printPageInformation() {
        if(revisions.length == 0) {
            return "No revisions to show!";
        }
        StringBuilder output = new StringBuilder(title);
        output.append("\n================================");
        int lineCounter = 1;
        for(PageRevision revision : revisions) {
            output.append("\n").append(lineCounter).append(") ").append(revision.getTimeStamp()).append("  ").append(revision.getUser());
            lineCounter++;
        }

        return output.toString();
    }
}
