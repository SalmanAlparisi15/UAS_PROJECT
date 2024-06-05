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
import com.example.uasts.DetailTransfer;
import com.example.uasts.R;
import com.example.uasts.model.transferfile.TransferFileData;
import com.example.uasts.others.temporary.TemporaryTransfer;

import java.util.Collections;
import java.util.List;

public class TransferAdapter extends RecyclerView.Adapter<TransferAdapter.ViewHolder> {

    private List<TransferFileData> transferList;
    private Context context;
    private TemporaryTransfer temporaryTransfer;


    public TransferAdapter(List<TransferFileData> transferList, Context context) {
        this.transferList = transferList;
        this.context = context;
        Collections.shuffle(this.transferList);
        temporaryTransfer = new TemporaryTransfer(context);

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
        holder.itemView.setOnClickListener(v-> {
            Intent intent = new Intent(context, DetailTransfer.class);
            temporaryTransfer.setTransferData(transfer.getPlayerName(), transfer.getPlayerPhoto(), transfer.getPlayerPositions(), transfer.getTransferPrice());
            context.startActivity(intent);

        });
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
