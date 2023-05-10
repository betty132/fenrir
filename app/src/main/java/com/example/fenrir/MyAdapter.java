package com.example.fenrir;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends  RecyclerView.Adapter<MyAdapter.storeViewHolder> {

    private Context context;
    private List<Store> mListStore;
    private OnItemClickListener mListener;

    public MyAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Store> list) {
        this.mListStore = list;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public storeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_overview, parent, false);
        return new storeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull storeViewHolder holder, int position) {
        Store store = mListStore.get(position);
        if (store == null) {
            return;
        }

        Picasso.get()
                .load(store.getImageUrlSmall())
                .into(holder.storePhoto);
        holder.genre.setText(store.getGenreName());
        holder.storeName.setText(store.getStoreName());
        holder.access.setText(store.getMobileAccess());
    }

    @Override
    public int getItemCount() {
        if (mListStore != null) {
            return mListStore.size();
        }
        else {
            return 0;
        }
    }

    public Store getItem(int position) {
        if (mListStore != null && position < mListStore.size()) {
            return mListStore.get(position);
        }
        return null;
    }

    public class storeViewHolder extends RecyclerView.ViewHolder {

        private ImageView storePhoto;
        private TextView genre;
        private TextView storeName;
        private TextView access;

        public storeViewHolder(@NonNull View itemView) {
            super(itemView);

            storePhoto = itemView.findViewById(R.id.storePhoto);
            genre = itemView.findViewById(R.id.genre);
            storeName = itemView.findViewById(R.id.storeName);
            access = itemView.findViewById(R.id.access);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
