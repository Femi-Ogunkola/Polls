package com.example.polls;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private ArrayList<ModelClass> list;

    public CustomAdapter(ArrayList<ModelClass> user) {
        this.list = user;
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_design,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
        String id = list.get(position).getId();
        String created_time = list.get(position).getCreated_time();
        String updated_time = list.get(position).getUpdated_time();
        String user = list.get(position).getUsername();
        String email = list.get(position).getEmail();
        String passowrd = list.get(position).getPassword();
        Boolean is_admin = list.get(position).isIs_admin();


        holder.setData(id,created_time,updated_time,user,email,passowrd,is_admin);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(ArrayList<ModelClass> user) {
        this.list = user;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView textView;
        private final TextView textView2;
        public final TextView isAdmin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView=itemView.findViewById(R.id.username);
            textView2=itemView.findViewById(R.id.email);
            isAdmin=itemView.findViewById(R.id.admin);
        }

        public void setData(String id,String created_time, String updated_time, String username, String email, String password,  Boolean is_admin) {
            textView.setText(username);
            textView2.setText(email);
            if (is_admin){
                isAdmin.setText("0");
            }
            else{
                isAdmin.setText("1");
            }
        }
    }
}
