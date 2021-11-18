package com.example.mp3;

public class BH {
    private String STT;
    private String tenbaihat;
    private String tencasi ;
    private String soluotxem;
//    private String filenhac;
    private String hinh;

    public BH(String STT, String tenbaihat, String tencasi, String soluotxem, String hinh) {
        this.STT = STT;
        this.tenbaihat = tenbaihat;
        this.tencasi = tencasi;
        this.soluotxem = soluotxem;
        this.hinh = hinh;
    }


    public String getSTT() {
        return STT;
    }

    public void setSTT(String STT) {
        this.STT = STT;
    }

    public String getTenbaihat() {
        return tenbaihat;
    }

    public void setTenbaihat(String tenbaihat) {
        this.tenbaihat = tenbaihat;
    }

    public String getTencasi() {
        return tencasi;
    }

    public void setTencasi(String tencasi) {
        this.tencasi = tencasi;
    }

    public String getSoluotxem() {
        return soluotxem;
    }

    public void setSoluotxem(String soluotxem) {
        this.soluotxem = soluotxem;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }
}
