package com.example.polls;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PollAdapter extends RecyclerView.Adapter<PollAdapter.ViewHolder> {

    private ArrayList<Poll> list;
    private onPollListener mOnPollListener;

    public PollAdapter(ArrayList<Poll> user, onPollListener onPollListener) {
        this.list = user;
        this.mOnPollListener = onPollListener;
    }

    @NonNull
    @Override
    public PollAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.poll_item_design,parent,false);
        return new ViewHolder(view, mOnPollListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PollAdapter.ViewHolder holder, int position) {

        String id = list.get(position).getId();
        String created_date = list.get(position).getCreated_date();
        String updated_date = list.get(position).getUpdated_date();
        String poll_title = list.get(position).getPoll_title();
        String poll_description = list.get(position).getPoll_description();
        int positive_vote_count = list.get(position).getPostive_vote_count();
        int negative_vote_count = list.get(position).getNegative_vote_count();
        int duration = list.get(position).getDuration();
        Boolean is_active = list.get(position).isIs_active();
        int authorId = list.get(position).getAuthor_id();



        holder.setData(id,created_date,updated_date,poll_title,poll_description,positive_vote_count,negative_vote_count,duration,is_active,authorId);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(ArrayList<Poll> user) {
        this.list = user;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView textView;
        private final TextView textView2;
        public final TextView count;
        onPollListener onPollListener;

        public ViewHolder(@NonNull View itemView, onPollListener onPollListener)  {
            super(itemView);

            textView=itemView.findViewById(R.id.poll_title);
            textView2=itemView.findViewById(R.id.poll_description);
            count=itemView.findViewById(R.id.count);
            itemView.setOnClickListener(this);
            this.onPollListener = onPollListener;
        }

        public void setData(String id,String created_date,String updated_date,String poll_title,String poll_description,int positive_vote_count,int negative_vote_count,Integer duration,Boolean is_active,int authorId) {
            textView.setText(poll_title);
            textView2.setText(poll_description);
            String counter =  String.valueOf(positive_vote_count + negative_vote_count);
            Log.d("INFO", counter);
            count.setText(counter);
            }

        @Override
        public void onClick(View view) {
            onPollListener.onPollClick(getAdapterPosition());
        }
    }

        public interface  onPollListener{
            void onPollClick(int position);
        }
}

