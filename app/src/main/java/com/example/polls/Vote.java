package com.example.polls;

public class Vote {
    private String poll_id;

    public Vote(String poll_id) {
        this.poll_id = poll_id;
    }

    public String getPoll_id() {
        return poll_id;
    }

    public void setPoll_id(String poll_id) {
        this.poll_id = poll_id;
    }
}
