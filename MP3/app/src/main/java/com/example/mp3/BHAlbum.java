package com.example.mp3;

public class BHAlbum {
    private String STT ;
    private String TenBaiHat;
    private String TenCaSi;
    private String NgayLuu;
    private String Hinh;

    public BHAlbum(String STT, String tenBaiHat, String tenCaSi, String ngayLuu, String hinh) {
        this.STT = STT;
        TenBaiHat = tenBaiHat;
        TenCaSi = tenCaSi;
        NgayLuu = ngayLuu;
        Hinh = hinh;
    }


    public String getSTT() {
        return STT;
    }

    public void setSTT(String STT) {
        this.STT = STT;
    }

    public String getTenBaiHat() {
        return TenBaiHat;
    }

    public void setTenBaiHat(String tenBaiHat) {
        TenBaiHat = tenBaiHat;
    }

    public String getTenCaSi() {
        return TenCaSi;
    }

    public void setTenCaSi(String tenCaSi) {
        TenCaSi = tenCaSi;
    }

    public String getNgayLuu() {
        return NgayLuu;
    }

    public void setNgayLuu(String ngayLuu) {
        NgayLuu = ngayLuu;
    }

    public String getHinh() {
        return Hinh;
    }

    public void setHinh(String hinh) {
        Hinh = hinh;
    }
}
