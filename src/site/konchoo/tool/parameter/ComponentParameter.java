package site.konchoo.tool.parameter;

public class ComponentParameter {
    private String title;
    private String categories;
    private String tags;
    private String date;
    private Boolean isSelectedFileCreatedTime;
    private Boolean isSelectedFileLastUpdatedTime;

    public ComponentParameter(String title, String categories, String tags, String date, Boolean isSelectedFileCreatedTime, Boolean isSelectedFileLastUpdatedTime) {
        this.title = title;
        this.categories = categories;
        this.tags = tags;
        this.date = date;
        this.isSelectedFileCreatedTime = isSelectedFileCreatedTime;
        this.isSelectedFileLastUpdatedTime = isSelectedFileLastUpdatedTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getSelectedFileCreatedTime() {
        return isSelectedFileCreatedTime;
    }

    public void setSelectedFileCreatedTime(Boolean selectedFileCreatedTime) {
        isSelectedFileCreatedTime = selectedFileCreatedTime;
    }

    public Boolean getSelectedFileLastUpdatedTime() {
        return isSelectedFileLastUpdatedTime;
    }

    public void setSelectedFileLastUpdatedTime(Boolean selectedFileLastUpdatedTime) {
        isSelectedFileLastUpdatedTime = selectedFileLastUpdatedTime;
    }
}

