package com.example.polls;

public class Poll {
    private String id;
    private String poll_title;
    private String poll_description;
    private int positive_vote_count;
    private int negative_vote_count;
    private String updated_date;
    private String created_date;
    private boolean is_active;
    private int duration;
    private int author_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPoll_title() {
        return poll_title;
    }

    public void setPoll_title(String poll_title) {
        this.poll_title = poll_title;
    }

    public String getPoll_description() {
        return poll_description;
    }

    public void setPoll_description(String poll_description) {
        this.poll_description = poll_description;
    }

    public int getPostive_vote_count() {
        return positive_vote_count;
    }

    public void setPostive_vote_count(int postive_vote_count) {
        this.positive_vote_count = postive_vote_count;
    }

    public int getNegative_vote_count() {
        return negative_vote_count;
    }

    public void setNegative_vote_count(int negative_vote_count) {
        this.negative_vote_count = negative_vote_count;
    }

    public String getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(String updated_date) {
        this.updated_date = updated_date;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }
}
