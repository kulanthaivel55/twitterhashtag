package com.example.twitterhashtagsearch.models;

import android.os.Parcel;
import android.os.Parcelable;


import com.example.twitterhashtagsearch.network.models.TweetStatuses;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tweet implements Parcelable {
    private final String username;
    private final String content;
    private final Date createdAt;
    private final String imageUrl;
    private final String id;

    public Tweet(String username, String content, Date createdAt, String imageUrl, String id) {
        this.username = username;
        this.content = content;
        this.createdAt = createdAt;
        this.imageUrl = imageUrl;
        this.id=id;
    }

    protected Tweet(Parcel in) {
        username = in.readString();
        content = in.readString();
        createdAt = new Date(in.readLong());
        imageUrl = in.readString();
        id=in.readString();
    }

    public static final Creator<Tweet> CREATOR = new Creator<Tweet>() {
        @Override
        public Tweet createFromParcel(Parcel in) {
            return new Tweet(in);
        }

        @Override
        public Tweet[] newArray(int size) {
            return new Tweet[size];
        }
    };

    public static List<Tweet> buildTweets(TweetStatuses statuses) {
        List<Tweet> tweetList = new ArrayList<>();
        for (TweetStatuses.Status status : statuses.getStatuses()) {
            String content = status.getText();
            String username = status.getUserName();
            Date createdAt = status.getCreatedAt();
            String imageUrl = status.getImageUrl();
            String id = status.getId();
            tweetList.add(new Tweet(username, content, createdAt, imageUrl,id));
        }
        return tweetList;
    }

    public String getUsername() {
        return username;
    }

    public String getContent() {
        return content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getId() { return id; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(username);
        parcel.writeString(content);
        parcel.writeLong(createdAt.getTime());
        parcel.writeString(imageUrl);
        parcel.writeString(id);
    }
}
