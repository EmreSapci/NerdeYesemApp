package com.example.neredeyesemapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.neredeyesemapp.activities.VenueActivity;
import com.example.neredeyesemapp.model.Venue;

import com.example.neredeyesemapp.R;

import java.util.List;

public class RecyclerVeiwAdapter extends RecyclerView.Adapter<RecyclerVeiwAdapter.MyViewHolder> {

    private Context mContext;
    private List<Venue> mData;
    RequestOptions option;

    public RecyclerVeiwAdapter(Context mContext, List<Venue> mData) {
        this.mContext = mContext;
        this.mData = mData;

        // Request option for Glide
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.anime_row_item, parent, false);
        final MyViewHolder viewHolder = new MyViewHolder(view);
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, VenueActivity.class);
                i.putExtra("anime_name",mData.get(viewHolder.getAdapterPosition()).getName());
                i.putExtra("anime_studio",mData.get(viewHolder.getAdapterPosition()).getStudio());
                i.putExtra("anime_description",mData.get(viewHolder.getAdapterPosition()).getDescription());
                i.putExtra("anime_category",mData.get(viewHolder.getAdapterPosition()).getCategorie());
                i.putExtra("anime_nb_epsiode",mData.get(viewHolder.getAdapterPosition()).getNb_episode());
                i.putExtra("anime_img",mData.get(viewHolder.getAdapterPosition()).getImage_url());
                i.putExtra("anime_rating",mData.get(viewHolder.getAdapterPosition()).getRating());

                mContext.startActivity(i);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_rating.setText(mData.get(position).getRating());
        holder.tv_studio.setText(mData.get(position).getStudio());
        holder.tv_category.setText(mData.get(position).getCategorie());

        //Now Image Setting with Glide
        Glide.with(mContext).load(mData.get(position).getImage_url()).apply(option).into(holder.img_thumbnail);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_name;
        TextView tv_rating;
        TextView tv_studio;
        TextView tv_category;
        ImageView img_thumbnail;
        LinearLayout view_container;

        //Constructor
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            view_container = itemView.findViewById(R.id.container);
            tv_name = itemView.findViewById(R.id.venue_name);
            tv_rating = itemView.findViewById(R.id.rating);
            tv_studio = itemView.findViewById(R.id.rating_text);
            tv_category = itemView.findViewById(R.id.categorie);
            img_thumbnail = itemView.findViewById(R.id.thumbnail);
        }
    }
}
