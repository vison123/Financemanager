package com.vison.finance.financemanager.biz.category.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vison.finance.financemanager.R;
import com.vison.finance.financemanager.framework.bean.Category;
import com.vison.finance.financemanager.framework.bean.Project;

import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2017/7/12.
 */

public class CategoryListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements View.OnClickListener, View.OnLongClickListener {

    private Context mContext;
    private List<Category> categorys;

    //自定义监听事件
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view);

        void onItemLongClick(View view);
    }

    public CategoryListAdapter(Context mContext, List<Category> categorys) {
        this.mContext = mContext;
        this.categorys = categorys;
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
                .inflate(R.layout.list_item_category, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        //给布局设置点击和长点击监听
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder) holder).tvCategoryName.setText(categorys.get(position).getName());
        ((MyViewHolder) holder).tvCategoryBudget.setText(
                String.format(Locale.CHINA, "%,.2f", categorys.get(position).getCategory_budget()));
        ((MyViewHolder) holder).tvCategoryIncome.setText(
                String.format(Locale.CHINA, "%,.2f", categorys.get(position).getIn_amount()));
        ((MyViewHolder) holder).tvCategoryOutcome.setText(
                String.format(Locale.CHINA, "%,.2f", categorys.get(position).getOut_amount()));
    }

    @Override
    public int getItemCount() {
        return categorys.size();
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
    public void addItem(Category category, int position) {
        categorys.add(position, category);
        notifyItemInserted(position);
    }

    //删除一个item
    public void removeItem(final int position) {
        categorys.remove(position);
        notifyItemRemoved(position);
    }

    //自定义ViewHolder，用于加载图片
    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCategoryName;
        private TextView tvCategoryBudget;
        private TextView tvCategoryIncome;
        private TextView tvCategoryOutcome;

        public MyViewHolder(View view) {
            super(view);
            tvCategoryName = (TextView) view.findViewById(R.id.tv_category_name);
            tvCategoryBudget = (TextView) view.findViewById(R.id.tv_category_budget);
            tvCategoryIncome = (TextView) view.findViewById(R.id.tv_category_income);
            tvCategoryOutcome = (TextView) view.findViewById(R.id.tv_category_outcome);
        }
    }
}
