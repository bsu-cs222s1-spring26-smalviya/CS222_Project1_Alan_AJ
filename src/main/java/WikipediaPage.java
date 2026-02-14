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
        String output = title;
        int lineCounter = 1;
        for(PageRevision revision : revisions) {
            output += "\n" + lineCounter + ")  [" + revision.getTimeStamp() + "]" + "  " + revision.getUser();
            lineCounter++;
        }

        return output;
    }
}
