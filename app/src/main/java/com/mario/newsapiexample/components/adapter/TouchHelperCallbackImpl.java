package com.mario.newsapiexample.components.adapter;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

/**
 * Created by mario on 11/06/18.
 */

public class TouchHelperCallbackImpl extends ItemTouchHelper.Callback {

    private static final float ALPHA_FULL = 1.0f;
    private final DraggableAdapter adapter;
    private Drawable backDrawable;

    private String swipeLabel;
    private int textMargin;
    private int textSize;
    private boolean enableSwipe;
    private boolean enableLongPressDrag;
    private boolean enableSwipeFadeOut;
    private int swipeDirection;

    public TouchHelperCallbackImpl(@NonNull DraggableAdapter adapter) {
        this.adapter = adapter;
    }

    public TouchHelperCallbackImpl(@NonNull DraggableAdapter adapter, String swipeLabel, int textSize, int textMargin, int swipeBackColor, boolean enableSwipeFadeOut) {
        this.adapter = adapter;
        this.swipeLabel = swipeLabel;
        this.textMargin = textMargin;
        this.textSize = textSize;
        this.enableSwipeFadeOut = enableSwipeFadeOut;
        this.enableSwipe = true;
        this.backDrawable = new ColorDrawable(swipeBackColor);
        this.swipeDirection = ItemTouchHelper.START;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return enableLongPressDrag;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return enableSwipe;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (!(viewHolder instanceof DragSwipeItems)) return makeFlag(0, 0);
        final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        final int result = makeMovementFlags(dragFlags, swipeDirection);
        if (result != 0) {
            adapter.onDragSwipeStarted();
        }
        return result;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        Log.d(getClass().getSimpleName(), "onMove called");
        return adapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        Log.d(getClass().getSimpleName(), "onSwiped called");
        adapter.onItemSwiped(viewHolder);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        Log.d(getClass().getSimpleName(), "onChildDrawCalled");
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            if (enableSwipeFadeOut) {
                final float alpha = ALPHA_FULL - Math.abs(dX) / (float) viewHolder.itemView.getWidth();
                viewHolder.itemView.setAlpha(alpha);
            }

            if (backDrawable != null) {
                c.save();
                final View itemView = viewHolder.itemView;
                c.clipRect((int) (itemView.getRight() + dX), itemView.getTop(), itemView.getRight(), itemView.getBottom());
                backDrawable.setBounds((int) (itemView.getRight() + dX), itemView.getTop(), itemView.getRight(), itemView.getBottom());
                backDrawable.draw(c);

                if (swipeLabel != null) {
                    final Paint paint = new Paint();
                    paint.setColor(Color.WHITE);
                    paint.setTextSize(textSize);
                    paint.setTextAlign(Paint.Align.CENTER);

                    int dx = (int) (itemView.getRight() - paint.measureText(swipeLabel) - textMargin);
                    int dy = itemView.getTop() - ((itemView.getTop() - itemView.getBottom()) / 2);
                    dy += paint.descent();
                    c.drawText(swipeLabel, dx, dy, paint);
                }
                c.restore();
            }
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            Log.d(getClass().getSimpleName(), "onSelectedChanged called");
            if (viewHolder instanceof DragSwipeItems) {
                DragSwipeItems dragSwipeItems = (DragSwipeItems) viewHolder;
                dragSwipeItems.onDragSwipeItemSelected();
            }
        }
    }

    @Override
    public boolean canDropOver(RecyclerView recyclerView, RecyclerView.ViewHolder current, RecyclerView.ViewHolder target) {
        Log.d(getClass().getSimpleName(), "canDropOver called");
        return super.canDropOver(recyclerView, current, target);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof DragSwipeItems) {
            Log.d(getClass().getSimpleName(), "ClearView called");
            DragSwipeItems dragSwipeItems = (DragSwipeItems) viewHolder;
            dragSwipeItems.onDragSwipeItemCleared();
        }

        adapter.onDragSwipeCompleted();
        super.clearView(recyclerView, viewHolder);
    }

    public void setEnableLongPressDrag(boolean enableLongPressDrag) {
        this.enableLongPressDrag = enableLongPressDrag;
    }

    public interface DragSwipeItems {

        void onDragSwipeItemCleared();

        void onDragSwipeItemSelected();
    }

    public interface DraggableAdapter {

        boolean onItemMove(int from, int to);

        void onItemSwiped(RecyclerView.ViewHolder viewHolder);

        void onDragSwipeCompleted();

        void onDragSwipeStarted();

    }

    public interface OnSwipeDragListener {

        void onStarted();

        void onCompleted();

        void onSwiped(BaseRecyclerViewAdapter adapter, RecyclerView.ViewHolder viewHolder, int position);
    }
}