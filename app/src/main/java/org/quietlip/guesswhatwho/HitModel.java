package org.quietlip.guesswhatwho;

public class HitModel {
    private int id;
    private String pageUrl;
    private String type;

    public HitModel(int id, String pageUrl, String type){
        this.id = id;
        this.pageUrl = pageUrl;
        this.type = type;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
