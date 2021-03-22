package com.farhanrinsky.kamus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.farhanrinsky.kamus.R;
import com.farhanrinsky.kamus.model.KamusModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KamusAdapter extends RecyclerView.Adapter<KamusAdapter.KamusHolder> {

    private ArrayList<KamusModel> mData = new ArrayList<>();
    private Context context;

    public KamusAdapter(Context context) {
        this.context = context;
    }

    public void addItem(ArrayList<KamusModel> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    public void replaceItem(ArrayList<KamusModel> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    @Override
    public KamusHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);
        return new KamusHolder(view);
    }

    @Override
    public void onBindViewHolder(KamusHolder holder, int position) {
        holder.txtKata.setText(mData.get(position).getKata());
        holder.txtDeskripsi.setText(mData.get(position).getDeskripsi());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class KamusHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_kata)
        TextView txtKata;
        @BindView(R.id.txt_deskripsi)
        TextView txtDeskripsi;

        public KamusHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}