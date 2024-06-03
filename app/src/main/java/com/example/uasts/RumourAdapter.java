package com.example.uasts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.uasts.model.rumourfile.RumourFileData;

import java.util.Collections;
import java.util.List;


public class RumourAdapter extends RecyclerView.Adapter<RumourAdapter.ViewHolder> {

    private List<RumourFileData> RumourList;
    private Context context;

    public RumourAdapter(List<RumourFileData> RumourList, Context context) {
        this.RumourList = RumourList;
        this.context = context;
        Collections.shuffle(this.RumourList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_itemrumour, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RumourFileData Rumour = RumourList.get(position);
        holder.playerName.setText(Rumour.getRumourplayerName());
        holder.clubName.setText(Rumour.getRumourclubName());
        Glide.with(context).load(Rumour.getRumourplayerPhoto()).into(holder.playerPhoto);
        Glide.with(context).load(Rumour.getRumourclubPhoto()).into(holder.clubPhoto);
    }

    @Override
    public int getItemCount() {
        return RumourList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView playerName, clubName;
        public ImageView playerPhoto, clubPhoto;

        public ViewHolder(View itemView) {
            super(itemView);
            playerName = itemView.findViewById(R.id.tvnamaPemain);
            clubName = itemView.findViewById(R.id.tvnamaTeam);
            playerPhoto = itemView.findViewById(R.id.ivPemain);
            clubPhoto = itemView.findViewById(R.id.ivClub);
        }
    }
}
