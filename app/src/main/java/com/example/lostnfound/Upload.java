package com.example.lostnfound;

public class Upload {
    String itemType;
    String itemColor;
    String foundDate;
    String imageIUrl;

    public Upload(){

    }

    public Upload(String type, String color, String date, String imageurl){
        itemType = type;
        itemColor = color;
        foundDate = date;
        imageIUrl = imageurl;
    }

}
