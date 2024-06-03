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
import com.example.uasts.model.transferfile.TransferFileData;

import java.util.Collections;
import java.util.List;


public class TransferAdapter extends RecyclerView.Adapter<TransferAdapter.ViewHolder> {

    private List<TransferFileData> transferList;
    private Context context;

    public TransferAdapter(List<TransferFileData> transferList, Context context) {
        this.transferList = transferList;
        this.context = context;
        Collections.shuffle(this.transferList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_itemtransfer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TransferFileData transfer = transferList.get(position);
        holder.playerName.setText(transfer.getPlayerName());
        holder.clubName.setText(transfer.getClubName());
        Glide.with(context).load(transfer.getPlayerPhoto()).into(holder.playerPhoto);
        Glide.with(context).load(transfer.getClubPhoto()).into(holder.clubPhoto);
    }

    @Override
    public int getItemCount() {
        return transferList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView playerName, clubName;
        public ImageView playerPhoto, clubPhoto;

        public ViewHolder(View itemView) {
            super(itemView);
            playerName = itemView.findViewById(R.id.tvnamaPemainT);
            clubName = itemView.findViewById(R.id.tvnamaTeamT);
            playerPhoto = itemView.findViewById(R.id.ivPemainT);
            clubPhoto = itemView.findViewById(R.id.ivClubT);
        }
    }
}
