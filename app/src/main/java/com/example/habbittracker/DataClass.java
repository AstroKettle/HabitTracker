package com.example.habbittracker;

import android.widget.ImageView;

public class DataClass {

    private String dataTitle;
    private String dataDesc;
    private String dataReg;
    private String dataImage;
    private String key;

    private int progress;
    public String getKey() {
        return key;
    }
    public void setProgress(int Prog) {
        this.progress = Prog;
    }

    public int getDataProg() {
        return progress;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getDataTitle() {
        return dataTitle;
    }
    public String getDataDesc() {
        return dataDesc;
    }
    public String getDataReg() {
        return dataReg;
    }
    public String getDataImage() {
        return dataImage;
    }
    public DataClass(String dataTitle, String dataDesc, String dataReg, String dataImage) {
        this.dataTitle = dataTitle;
        this.dataDesc = dataDesc;
        this.dataReg = dataReg;
        this.dataImage = dataImage;
    }
    public DataClass(){
    }
}
