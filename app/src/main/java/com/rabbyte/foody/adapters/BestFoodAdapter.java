package com.rabbyte.foody.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.rabbyte.foody.databinding.ViewholderBestFoodBinding;
import com.rabbyte.foody.domains.Food;

import java.util.ArrayList;

import eightbitlab.com.blurview.RenderEffectBlur;

public class BestFoodAdapter extends RecyclerView.Adapter<BestFoodAdapter.ViewHolder> {

    private final ArrayList<Food> items;
    private Context mContext;

    public BestFoodAdapter(ArrayList<Food> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        ViewholderBestFoodBinding binding =
                ViewholderBestFoodBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.titleTxt.setText(items.get(position).getTitle());
        holder.binding.priceTxt.setText("$" + items.get(position).getPrice());
        holder.binding.timeTxt.setText(items.get(position).getTimeValue() + "min");
        holder.binding.starTxt.setText("" + items.get(position).getStar());

        float radius = 10f;
        View decorView = ((Activity) holder.itemView.getContext()).getWindow().getDecorView();
        ViewGroup rootView = decorView.findViewById(android.R.id.content);
        Drawable windowBackground = decorView.getBackground();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            holder.binding.blurView.setupWith(rootView, new RenderEffectBlur())
                    .setFrameClearDrawable(windowBackground)
                    .setBlurRadius(radius);
        }

        holder.binding.blurView.setOutlineProvider(ViewOutlineProvider.BACKGROUND);
        holder.binding.blurView.setClipToOutline(true);

        Glide.with(mContext)
                .load(items.get(position).getImagePath())
                .transform(new CenterCrop(), new RoundedCorners(30))
                .into(holder.binding.img);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderBestFoodBinding binding;

        public ViewHolder(ViewholderBestFoodBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
