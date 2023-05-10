package com.example.fenrir;

public class Store  {

    private String imageUrlSmall;
    private String genreName;
    private String storeName;
    private String mobileAccess;

    public Store(String imageUrlSmall, String genreName, String storeName, String mobileAccess) {
        this.imageUrlSmall = imageUrlSmall;
        this.genreName = genreName;
        this.storeName = storeName;
        this.mobileAccess = mobileAccess;
    }

    public String getImageUrlSmall() {
        return imageUrlSmall;
    }

    public void setImageUrlSmall(String imageUrlSmall) {
        this.imageUrlSmall = imageUrlSmall;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getMobileAccess() {
        return mobileAccess;
    }

    public void setMobileAccess(String mobileAccess) {
        this.mobileAccess = mobileAccess;
    }

}
