package com.example.myapplication.model;

public class Maps {
    public String diaChi;
    public String kinhDo;
    public String viDo;

    public Maps() {
    }

    public Maps(String diaChi, String viDo, String kinhDo) {
        this.diaChi = diaChi;
        this.kinhDo = kinhDo;
        this.viDo = viDo;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getKinhDo() {
        return kinhDo;
    }

    public void setKinhDo(String kinhDo) {
        this.kinhDo = kinhDo;
    }

    public String getViDo() {
        return viDo;
    }

    public void setViDo(String viDo) {
        this.viDo = viDo;
    }
}
