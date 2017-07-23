package com.vison.finance.financemanager.biz.money.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vison.finance.financemanager.R;
import com.vison.finance.financemanager.framework.bean.Category;
import com.vison.finance.financemanager.framework.bean.Money;
import com.vison.finance.financemanager.framework.bean.Money;

import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2017/7/12.
 */

public class MoneyListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements View.OnClickListener, View.OnLongClickListener {

    private Context mContext;
    private List<Money> moneyListItems;

    //自定义监听事件
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view);

        void onItemLongClick(View view);
    }

    public MoneyListAdapter(Context mContext, List<Money> moneyListItems) {
        this.mContext = mContext;
        this.moneyListItems = moneyListItems;
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
                .inflate(R.layout.list_item_money, parent, false);
        MoneyViewHolder holder = new MoneyViewHolder(view);
        //给布局设置点击和长点击监听
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MoneyViewHolder) holder).tvCategoryName.setText(moneyListItems.get(position).getCategory_name());
        ((MoneyViewHolder) holder).tvMoneyName.setText(moneyListItems.get(position).getName());
        ((MoneyViewHolder) holder).tvMoneyAmount.setText(
                String.format(Locale.CHINA, "%,.2f", moneyListItems.get(position).getAmount()));

    }

    @Override
    public int getItemCount() {
        return moneyListItems.size();
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
    public void addItem(Money moneyListItem, int position) {
        moneyListItems.add(position, moneyListItem);
        notifyItemInserted(position);
    }

    //删除一个item
    public void removeItem(final int position) {
        moneyListItems.remove(position);
        notifyItemRemoved(position);
    }

    //自定义ViewHolder，用于加载图片
    class MoneyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvMoneyName;
        private TextView tvCategoryName;
        private TextView tvMoneyAmount;


        public MoneyViewHolder(View view) {
            super(view);
            tvCategoryName = (TextView) view.findViewById(R.id.tv_category_name);
            tvMoneyName = (TextView) view.findViewById(R.id.tv_money_name);
            tvMoneyAmount = (TextView) view.findViewById(R.id.tv_money_amount);
        }
    }
}
