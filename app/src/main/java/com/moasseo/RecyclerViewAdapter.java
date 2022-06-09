package com.moasseo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{

    private ArrayList<RecyclerViewItem> mList;

    public RecyclerViewAdapter(List<RecyclerViewItem> R_list) {
        mList = (ArrayList<RecyclerViewItem>) R_list;
    }

    // 아이템 뷰를 위한 뷰홀더 객체를 생성하여 리턴
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.activity_recycler_item, parent, false);
        //RecyclerViewAdapter.ViewHolder vh = new RecyclerViewAdapter.ViewHolder(view);
        return new RecyclerViewHolder(view);
    }

    // position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        final RecyclerViewItem item = mList.get(position);

        holder.ivIcon.setImageResource(item.getImageResId());   // imageView에 들어갈 이미지 설정
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

}
