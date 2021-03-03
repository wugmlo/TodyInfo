package com.ktc.todyinfo.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class ItemTouchCallBack extends ItemTouchHelper.Callback {

    public static final String TAG = "ItemTouchCallBack";
    private OnItemTouchListener onItemTouchListener;

    public interface OnItemTouchListener {
        void onMove(int fromPosition, int toPosition);
        void onSwiped(int position);
    }

    public ItemTouchCallBack(OnItemTouchListener onItemTouchListener) {
        this.onItemTouchListener = onItemTouchListener;
    }

    /**
     * 根据 RecyclerView 不同的布局管理器，设置不同的滑动、拖动方向
     */
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        Log.i(TAG, "getMovementFlags");
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager || layoutManager instanceof StaggeredGridLayoutManager) {
            return makeMovementFlags(ItemTouchHelper.UP | ItemTouchHelper.DOWN
                    | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, 0);
        } else {
            return makeMovementFlags(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT);
        }
    }

    /**
     * 当 ItemTouchHelper 拖动一个Item时该方法将会被回调，Item将从旧的位置移动到新的位置
     */
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        Log.i(TAG, "onMove");
        int fromPosition = viewHolder.getAdapterPosition();
        int toPosition = target.getAdapterPosition();
        onItemTouchListener.onMove(fromPosition, toPosition);
        return false;
    }

    /**
     * 当Item被滑动的时候被调用
     */
    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        Log.i(TAG, "onSwiped");
        int adapterPosition = viewHolder.getAdapterPosition();
        onItemTouchListener.onSwiped(adapterPosition);
    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        Log.i(TAG, "clearView");
        super.clearView(recyclerView, viewHolder);
    }

    /**
     * 当Item被滑动、拖动的时候被调用
     */
    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        Log.i(TAG, "onSelectedChanged");
        super.onSelectedChanged(viewHolder, actionState);
    }
}
