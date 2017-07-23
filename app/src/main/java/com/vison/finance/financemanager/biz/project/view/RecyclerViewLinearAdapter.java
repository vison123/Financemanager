package com.vison.finance.financemanager.biz.project.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vison.finance.financemanager.R;
import com.vison.finance.financemanager.framework.bean.Project;

import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2017/7/12.
 */

public class RecyclerViewLinearAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements View.OnClickListener, View.OnLongClickListener {

    private Context mContext;
    private List<Project> projects;

    //自定义监听事件
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view);

        void onItemLongClick(View view);
    }

    public RecyclerViewLinearAdapter(Context mContext, List<Project> projects) {
        this.mContext = mContext;
        this.projects = projects;
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //根据item类别加载不同ViewHolder
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.list_item_project, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        //给布局设置点击和长点击监听
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder)holder).tvProjectName.setText(projects.get(position).getProject_name());
        ((MyViewHolder)holder).tvProjectBudget.setText(String.format(Locale.CHINA,"%,.2f", projects.get(position).getBudget()));
        ((MyViewHolder)holder).tvProjectIncome.setText(String.format(Locale.CHINA,"%,.2f", projects.get(position).getIn_amount()));
        ((MyViewHolder)holder).tvProjectOutCome.setText(String.format(Locale.CHINA,"%,.2f", projects.get(position).getOut_amount()));
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    //点击事件回调
    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemLongClick(v);
        }
        return false;
    }

    //添加一个item
    public void addItem(Project Project, int position) {
        projects.add(position, Project);
        notifyItemInserted(position);
    }

    //删除一个item
    public void removeItem(final int position) {
        projects.remove(position);
        notifyItemRemoved(position);
    }

    //自定义ViewHolder，用于加载图片
    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvProjectName;
        private TextView tvProjectBudget;
        private TextView tvProjectIncome;
        private TextView tvProjectOutCome;

        public MyViewHolder(View view) {
            super(view);
            tvProjectName = (TextView) view.findViewById(R.id.tv_project_name);
            tvProjectBudget = (TextView) view.findViewById(R.id.tv_project_budget);
            tvProjectIncome = (TextView) view.findViewById(R.id.tv_project_income);
            tvProjectOutCome = (TextView) view.findViewById(R.id.tv_project_outcome);
        }
    }
}
