package com.google.elloribon.model;

/**
 * Created by Roman on 06.10.2015.
 */
public class Data {

    private final String titleClip;
    private final String titleArtist;
    private final String countViews;
    private final String imageUrl;

    public Data(String titleClip, String titleArtist, String countViews, String imageUrl) {
        super();
        this.titleClip = titleClip;
        this.titleArtist = titleArtist;
        this.countViews = countViews;
        this.imageUrl = imageUrl;

    }

    public String getTitleClip() {
        return titleClip;
    }

    public String getTitleArtist() {
        return titleArtist;
    }

    public String getCountViews() {
        return countViews;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}

