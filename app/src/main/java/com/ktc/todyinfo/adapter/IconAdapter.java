package com.ktc.todyinfo.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ktc.todyinfo.R;
import com.ktc.todyinfo.model.Icon;

import java.util.Collections;
import java.util.List;

public class IconAdapter extends RecyclerView.Adapter<IconAdapter.ViewHolder>
                             implements View.OnClickListener,ItemTouchCallBack.OnItemTouchListener {

    public static final String TAG = "IconAdapter";
    private List<Icon> mIconList;
    private RecyclerView recyclerView;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 设计recycleView选项单击事件的回调接口（给外面使用）
     */
    public interface OnItemClickListener {
        void onItemClick(RecyclerView recyclerView, View view, int position, Icon icon);
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener != null) {
            int position = recyclerView.getChildAdapterPosition(v);
            onItemClickListener.onItemClick(recyclerView, v, position, mIconList.get(position));
        }
    }

    @Override
    public void onMove(int fromPosition, int toPosition) {
        Log.i(TAG,"onMove");
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(mIconList, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(mIconList, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onSwiped(int position) {
        Log.i(TAG,"onSwiped");
        mIconList.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * 将RecycleView附加到Adapter上
     */
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    /**
     * 将RecycleView从Adapter解除
     */
    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.recyclerView = null;
    }

    static public class ViewHolder extends RecyclerView.ViewHolder{
        View iconView;
        ImageView iconImage;
        TextView iconName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iconView = itemView;
            iconImage = itemView.findViewById(R.id.icon_image);
            iconName = itemView.findViewById(R.id.icon_name);
        }
    }

    public IconAdapter(List<Icon> mIconList) {
        this.mIconList = mIconList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.icon_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        /*holder.iconView.setOnClickListener((v -> {
            int position = holder.getAdapterPosition();
            Icon icon = mIconList.get(position);
            Toast.makeText(view.getContext(), "click view!" + icon.getName(), Toast.LENGTH_SHORT).show();
        }));
        holder.iconImage.setOnClickListener((v -> {
            int position = holder.getAdapterPosition();
            Icon icon = mIconList.get(position);
            Toast.makeText(view.getContext(), "click image!" + icon.getName(), Toast.LENGTH_SHORT).show();
        }));*/
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Icon icon = mIconList.get(position);
        holder.iconImage.setImageResource(icon.getImageId());
        holder.iconName.setText(icon.getName());
    }

    @Override
    public int getItemCount() {
        return mIconList.size();
    }
}
