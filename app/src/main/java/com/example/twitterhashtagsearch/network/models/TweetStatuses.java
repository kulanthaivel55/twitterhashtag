package com.example.twitterhashtagsearch.network.models;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class TweetStatuses {
    private final List<Status> statuses;

    public TweetStatuses(List<Status> statuses) {
        this.statuses = statuses;
    }

    public static class Status {
        @SerializedName("created_at")
        private final Date createdAt;
        @SerializedName("id")
        private final String id;



        private final String text;

        private final User user;

        private final Entities entities;

        private final Urls urls;



        private static class User {
            private final String name;
            @SerializedName("screen_name")
            private final String screenName;

            private User(String name, String screenName) {
                this.name = name;
                this.screenName = screenName;
            }

            public String getName() {
                return name;
            }

            public String getScreenName() {
                return screenName;
            }
        }

        private static class Urls {
            @SerializedName("url")
            private final String url;
            @SerializedName("expanded_url")
            private final String expanded_url;

            private Urls(String name, String screenName) {
                this.url = name;
                this.expanded_url = screenName;
            }

            public String geturl() {
                return url;
            }

            public String getexpanded_url() {
                return expanded_url;
            }
        }

        private static class Entities {
            private final List<Media> media;

            private static class Media {
                @SerializedName("media_url_https")
                private final String url;

                private final String type;

                public Media(String url, String type) {
                    this.url = url;
                    this.type = type;
                }

                public String getUrl() {
                    return url;
                }

                public boolean isPhoto() {
                    return "photo".equalsIgnoreCase(type) && !TextUtils.isEmpty(getUrl());
                }
            }

            public Entities(List<Media> media) {
                this.media = media;
            }
        }

        public Status(Date createdAt, String text, User user, Entities entities, String aId) {
            this.createdAt = createdAt;
            this.text = text;
            this.user = user;
            this.entities = entities;
            this.id=aId;
            this.urls = null;
        }

        public Date getCreatedAt() {
            return createdAt;
        }

        public String getText() {
            return text;
        }public String getId() {
            return id;
        }

        public String getUserName() {
            return user.getName();
        }

        public String getUserScreenName() {
            return user.getScreenName();
        }

        public String getImageUrl() {
            if (entities != null && entities.media != null) {
                for (Entities.Media media : entities.media) {
                    if (media.isPhoto()) {
                        return media.getUrl();
                    }
                }
            }
            return null;
        }
    }

    public List<Status> getStatuses() {
        return statuses;
    }
}

