package com.example.uasts.others.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.uasts.DetailRumour;
import com.example.uasts.R;
import com.example.uasts.api.ApiClient;
import com.example.uasts.api.ApiInterface;
import com.example.uasts.model.deleterumour.DeleteRumour;
import com.example.uasts.model.rumourfile.RumourFileData;
import com.example.uasts.others.temporary.TemporaryRumour;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        Glide.with(context).load(rumour.getFromclub()).into(holder.clubPhoto);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailRumour.class);
            TemporaryRumour temporaryRumour = new TemporaryRumour(context);
            temporaryRumour.setRumourData(
                    rumour.getRumourplayerName(),
                    rumour.getRumourplayerPhoto(),
                    rumour.getRumourplayerPosition(),
                    rumour.getRumourPrice(),
                    rumour.getFromclub(),
                    rumour.getDescription(),
                    rumour.getFromclubname(),
                    rumour.getRumourclubName(),
                    rumour.getRumourclubPhoto(),
                    rumour.getId()
            );
            context.startActivity(intent);
        });

        holder.deleteButton.setOnClickListener(view ->{
            deleteRumour(rumour.getId(),position);
        });
    }

    private void deleteRumour(int id, int position) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<DeleteRumour> call = apiInterface.deleteRumour(id);
        call.enqueue(new Callback<DeleteRumour>() {
            @Override
            public void onResponse(Call<DeleteRumour> call, Response<DeleteRumour> response) {
                if (response.isSuccessful() && response.body() !=null) {
                    DeleteRumour DeleteResponse = response.body();

                    if (DeleteResponse.isStatus()) {
                        rumourList.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, rumourList.size());
                        Toast.makeText(context, "Delete successful", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, "Failed to delete" + DeleteResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "Failed to delete. Please try again.", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<DeleteRumour> call, Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return rumourList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView playerName, clubName;
        public ImageView playerPhoto, clubPhoto;
        public ImageButton deleteButton;

        public ViewHolder(View itemView) {
            super(itemView);
            playerName = itemView.findViewById(R.id.tvnamaPemain);
            clubName = itemView.findViewById(R.id.tvnamaTeam);
            playerPhoto = itemView.findViewById(R.id.ivPemain);
            clubPhoto = itemView.findViewById(R.id.ivClub);
            deleteButton = itemView.findViewById(R.id.ibDelete);
        }
    }
}
