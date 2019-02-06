package com.byshy.light.Models;

public class SearchResult {

    private String mContent;
    private int mType; // 1 is header, 0 is content

    public SearchResult(){}

    public SearchResult(String content){
        mContent = content;
    }
    public SearchResult(String content, int type){
        mContent = content;
        mType = type;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String mContent) {
        this.mContent = mContent;
    }

    public int getType() {
        return mType;
    }

    public void setType(int mType) {
        this.mType = mType;
    }
}
