package tc.streethelper.streethelper;

public class FundingCharacter {
    private String name;
    private  String story;
    private String summaryStory;
    private int targetBudgget;
    private int currentBudget;
    private String imgTag;

    public String getImgTag() {
        return imgTag;
    }

    public void setImgTag(String imgTag) {
        this.imgTag = imgTag;
    }

    public FundingCharacter(String name, String story, String summaryStory, int targetBudgget, int currentBudget, String imgTag) {

        this.name = name;
        this.story = story;
        this.summaryStory = summaryStory;
        this.targetBudgget = targetBudgget;
        this.currentBudget = currentBudget;
        this.imgTag = imgTag;
    }

    public FundingCharacter(String name, String story, String summaryStory, int targetBudgget, int currentBudget) {
        this.name = name;
        this.story = story;
        this.summaryStory = summaryStory;
        this.targetBudgget = targetBudgget;
        this.currentBudget = currentBudget;
    }

    public FundingCharacter() {
    }

    public FundingCharacter(String name, int targetBudgget, int currentBudget) {
        this.name = name;
        this.targetBudgget = targetBudgget;
        this.currentBudget = currentBudget;
    }

    public FundingCharacter(String name, String summaryStory, int targetBudgget, int currentBudget) {
        this.name = name;
        this.summaryStory = summaryStory;
        this.targetBudgget = targetBudgget;
        this.currentBudget = currentBudget;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getSummaryStory() {
        return summaryStory;
    }

    public void setSummaryStory(String summaryStory) {
        this.summaryStory = summaryStory;
    }

    public int getTargetBudgget() {
        return targetBudgget;
    }

    public void setTargetBudgget(int targetBudgget) {
        this.targetBudgget = targetBudgget;
    }

    public int getCurrentBudget() {
        return currentBudget;
    }

    public void setCurrentBudget(int currentBudget) {
        this.currentBudget = currentBudget;
    }
}
