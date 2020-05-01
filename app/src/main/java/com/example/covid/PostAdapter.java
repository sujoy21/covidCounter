package com.example.covid;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder>{

    private Context context;
    private List<Statewise> items;

    public PostAdapter(Context context, List<Statewise> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_item,parent,false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {

        Statewise statewise = items.get(position);
//        holder.name.setText(statewise.getActive());
//        holder.active.setText(statewise.getConfirmed());
        holder.state.setText(statewise.getState());
        holder.cnfmd.setText("Cnfmd : "+"^"+statewise.getDeltaconfirmed()+"- "+statewise.getConfirmed());
        holder.actv.setText("Actv : "+statewise.getActive());
        holder.rcvrd.setText("Rcvrd : "+"^"+statewise.getDeltarecovered()+"- "+statewise.getRecovered());
        holder.dcsd.setText("Dcsd : "+"^"+statewise.getDeltadeaths()+"- "+statewise.getDeaths());
        holder.update.setText("Updated : "+statewise.getLastupdatedtime().trim());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder{
        TextView state;
        TextView cnfmd;
        TextView actv;
        TextView rcvrd;
        TextView dcsd;
        TextView update;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            state = itemView.findViewById(R.id.state);
            cnfmd = itemView.findViewById(R.id.cnfmd);
            actv = itemView.findViewById(R.id.actv);
            rcvrd = itemView.findViewById(R.id.rcvrd);
            dcsd = itemView.findViewById(R.id.dcsd);
            update = itemView.findViewById(R.id.update);
        }
    }

}
