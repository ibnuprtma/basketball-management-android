package com.example.managementbasket.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.managementbasket.Config.InitService;
import com.example.managementbasket.Config.UpdateDeleteListener;
import com.example.managementbasket.MapsActivity;
import com.example.managementbasket.Model.Player;
import com.example.managementbasket.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.MyViewHolder> {

    private List<Player> list_player;
    private UpdateDeleteListener mListener;
    private Context mContext;

    public PlayerAdapter(List<Player> list_player, UpdateDeleteListener mListener, Context mContext) {
        this.list_player = list_player;
        this.mListener = mListener;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public PlayerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_players,parent,false);
        return new PlayerAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final PlayerAdapter.MyViewHolder holder, int position) {
        final Player f = list_player.get(position);
        holder.tv_name.setText(f.getName());
        holder.tv_position.setText(f.getPosition());
        holder.tv_number_jersey.setText(f.getNumber_jersey());
        holder.tv_address.setText(f.getAddress());
        holder.iv_bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.iv_bookmark.setImageResource(R.drawable.ic_star_red_24dp);
            }
        });
        holder.tv_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MapsActivity.class);
                intent.putExtra("alamat",f.getAddress());
                mContext.startActivity(intent);
            }
        });
        Picasso.with(mContext).load(InitService.getUrl()+"images/"+f.getImages()).into(holder.iv_images);
    }

    @Override
    public int getItemCount() {
        return list_player.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_position, tv_number_jersey, tv_address;
        ImageView iv_images,iv_bookmark;
        Button btn_delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_name);
            tv_position = itemView.findViewById(R.id.tv_position);
            tv_number_jersey = itemView.findViewById(R.id.tv_number_jersey);
            tv_address = itemView.findViewById(R.id.tv_address);
            iv_images = itemView.findViewById(R.id.iv_images);
            iv_bookmark =itemView.findViewById(R.id.iv_bookmark);
            btn_delete = itemView.findViewById(R.id.btnDelete);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mListener.onUpdate(getAdapterPosition());
                    return false;
                }
            });

            btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onDelete(getAdapterPosition());
                }
            });

        }
    }
}
