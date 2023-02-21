package com.jake5113.tp06memberlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.VH> {

    Context context;
    ArrayList<Member> members;

    public MyAdapter(Context context, ArrayList<Member> members) {
        this.context = context;
        this.members = members;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.listview_member, parent, false);
        VH holder = new VH(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Member member = members.get(position);

        holder.tvName.setText(member.name);
        holder.tvNation.setText(member.nation);
        holder.ivGender.setImageResource(member.imgGender);
        holder.ivNation.setImageResource(member.imgNation);
    }

    @Override
    public int getItemCount() {
        return members.size();
    }


    class VH extends RecyclerView.ViewHolder{
        ImageView ivGender, ivNation;
        TextView tvName, tvNation;
        public VH(@NonNull View itemView) {
            super(itemView);
            ivGender = itemView.findViewById(R.id.iv_gender);
            ivNation = itemView.findViewById(R.id.iv_nation);
            tvNation = itemView.findViewById(R.id.tv_nation);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
