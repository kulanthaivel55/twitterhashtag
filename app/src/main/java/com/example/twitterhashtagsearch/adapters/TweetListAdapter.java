package com.example.twitterhashtagsearch.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.twitterhashtagsearch.R;
import com.example.twitterhashtagsearch.models.Tweet;
import com.example.twitterhashtagsearch.models.TweetDateFormatter;


import java.util.List;

public class TweetListAdapter extends AnimatedRecyclerViewAdapter<TweetListAdapter.ViewHolder> {

    private final TweetDateFormatter mFormatter;
    private List<Tweet> mTweetList;
    private AppCallback myAppCallback;
    public TweetListAdapter(Context context, TweetDateFormatter formatter, AppCallback appCallback) {
        super(context);
        this.mFormatter = formatter;
        this.myAppCallback=appCallback;
    }

    public void update(List<Tweet> tweetList) {
        this.mTweetList = tweetList;
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View tweetItemView = inflater.inflate(R.layout.tweet_item, parent, false);
        return new ViewHolder(tweetItemView);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        final Tweet tweet = mTweetList.get(position);
        holder.content.setText(tweet.getContent());
        holder.createdAt.setText(mFormatter.format(mContext, tweet.getCreatedAt()));
        holder.username.setText(tweet.getUsername());
        String imageUrl = tweet.getImageUrl();


        if (TextUtils.isEmpty(imageUrl)) {
            holder.imageView.setVisibility(View.GONE);
        } else {
            Glide.with(mContext).load(imageUrl).centerCrop().into(holder.imageView);
            holder.imageView.setVisibility(View.VISIBLE);
        }

        holder.mRootLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAppCallback.sendSearchDetailsScreen("https://twitter.com/i/web/status/"+tweet.getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mTweetList == null ? 0 : mTweetList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final LinearLayout mRootLay;
        private final TextView username;

        private final TextView createdAt;
        private final TextView content;
        private final ImageView imageView;


        public ViewHolder(View itemView) {
            super(itemView);
            username = (TextView) itemView.findViewById(R.id.usernameTextView);

            createdAt = (TextView) itemView.findViewById(R.id.createdAtTextView);
            content = (TextView) itemView.findViewById(R.id.contentTextView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            mRootLay = (LinearLayout) itemView.findViewById(R.id.rootLay);
        }
    }


    public interface AppCallback {
        void sendSearchDetailsScreen(String aUrl);
    }
}
