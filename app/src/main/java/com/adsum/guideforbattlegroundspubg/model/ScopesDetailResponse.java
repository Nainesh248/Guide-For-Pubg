package com.adsum.guideforbattlegroundspubg.model;

public class ScopesDetailResponse {
    private String scopename,  detail;
    private int scopeimage;
    public ScopesDetailResponse() {
    }

    public ScopesDetailResponse(String scopename, String detail, int scopeimage) {
        this.scopename = scopename;
        this.detail = detail;
        this.scopeimage = scopeimage;
    }

    public String getScopename() {
        return scopename;
    }

    public void setScopename(String scopename) {
        this.scopename = scopename;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getScopeimage() {
        return scopeimage;
    }

    public void setScopeimage(int scopeimage) {
        this.scopeimage = scopeimage;
    }
}
