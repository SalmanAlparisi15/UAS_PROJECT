package com.example.uasts.others.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.uasts.DetailRumour;
import com.example.uasts.R;
import com.example.uasts.model.rumourfile.RumourFileData;
import com.example.uasts.others.temporary.TemporaryRumour;

import java.util.Collections;
import java.util.List;

public class RumourAdapter extends RecyclerView.Adapter<RumourAdapter.ViewHolder> {

    private List<RumourFileData> rumourList;
    private Context context;
    private TemporaryRumour temporaryRumour;


    public RumourAdapter(List<RumourFileData> rumourList, Context context) {
        this.rumourList = rumourList;
        this.context = context;
        Collections.shuffle(this.rumourList);
        temporaryRumour = new TemporaryRumour(context);
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
        RumourFileData rumour = rumourList.get(position);
        holder.playerName.setText(rumour.getRumourplayerName());
        holder.clubName.setText(rumour.getRumourclubName());
        Glide.with(context).load(rumour.getRumourplayerPhoto()).into(holder.playerPhoto);
        Glide.with(context).load(rumour.getRumourclubPhoto()).into(holder.clubPhoto);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailRumour.class);
            temporaryRumour.setRumourData(rumour.getRumourplayerName(), rumour.getRumourplayerPhoto(), rumour.getRumourplayerPosition(), rumour.getRumourPrice());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return rumourList.size();
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
