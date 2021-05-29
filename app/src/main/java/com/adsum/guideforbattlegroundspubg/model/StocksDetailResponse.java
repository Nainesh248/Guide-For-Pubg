package com.adsum.guideforbattlegroundspubg.model;

public class StocksDetailResponse {
    private String stocksname,  detail;
    private int stocksimage;
    public StocksDetailResponse() {
    }

    public StocksDetailResponse(String stocksname, String detail, int stocksimage) {
        this.stocksname = stocksname;
        this.detail = detail;
        this.stocksimage = stocksimage;
    }

    public String getStocksname() {
        return stocksname;
    }

    public void setStocksname(String stocksname) {
        this.stocksname = stocksname;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getStocksimage() {
        return stocksimage;
    }

    public void setStocksimage(int stocksimage) {
        this.stocksimage = stocksimage;
    }
}
