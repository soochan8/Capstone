package com.moasseo;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    ImageView ivIcon;

    public RecyclerViewHolder(View view){
        super(view);

        ivIcon = view.findViewById(R.id.imgView_item);
    }
}
