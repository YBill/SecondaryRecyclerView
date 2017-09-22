package com.example.bill.secondaryrecyclerview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bill on 2017/9/20.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;
    private List<DataBean> list = new ArrayList<>();

    public MyAdapter(Context context, List<DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.update(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private View itemLayout;
        private TextView textView;
        private ImageView imageView;
        private RecyclerView child;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemLayout = itemView.findViewById(R.id.fl_item);
            this.textView = (TextView) itemView.findViewById(R.id.tv_time);
            this.imageView = (ImageView) itemView.findViewById(R.id.image);
            this.child = (RecyclerView) itemView.findViewById(R.id.rv_child);
            LinearLayoutManager manager = new LinearLayoutManager(context);
            manager.setAutoMeasureEnabled(true);
            child.setLayoutManager(manager);
        }

        private void update(final int position) {
            textView.setText(list.get(position).title);

            final DataBean data = list.get(position);
            if (child.getAdapter() == null) {
                child.setAdapter(new HistoryChildAdapter(context));
                ((HistoryChildAdapter) child.getAdapter()).setList(data.list);
            }

            itemLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (data.isExpans) {
                        data.isExpans = false;
                        child.setVisibility(View.GONE);
                        rotationExpandIcon(180, 0);
                    } else {
                        data.isExpans = true;
                        child.setVisibility(View.VISIBLE);
                        rotationExpandIcon(0, 180);
                    }

                }
            });

        }

        private void rotationExpandIcon(float from, float to) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "rotation", from, to);
            animator.setDuration(200);
            animator.start();
        }

    }

}
