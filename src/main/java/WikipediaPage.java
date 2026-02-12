public class WikipediaPage {

    private final PageRevision[] revisions;

    public WikipediaPage(PageRevision[] revisions) {
        this.revisions = revisions;
    }

    public PageRevision[] getRevisions() {
        return revisions;
    }

    public String showAllRevisions() {
        if(revisions.length == 0) {
            return "No revisions to show!";
        }
        String output = "";
        for(PageRevision revision : revisions) {
            output += "\n[" + revision.getTimeStamp() + "]" + " " + revision.getUser();
        }

        return output;
    }
}
