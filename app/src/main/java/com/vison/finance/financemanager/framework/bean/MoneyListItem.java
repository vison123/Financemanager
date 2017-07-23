package com.vison.finance.financemanager.framework.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/6.
 */

public class MoneyListItem {
    private Long money_id;
    private Long category_id;
    private String category_name;
    private Long project_id;
    private String project_name;
    private String money_name;
    private String in_out_type;
    private Date occur_date;
    private BigDecimal amount;
    private String description;

    public MoneyListItem() {
    }

    public MoneyListItem(Long money_id, Long category_id, String category_name, Long project_id, String project_name, String money_name, String in_out_type, Date occur_date, BigDecimal amount, String description) {
        this.money_id = money_id;
        this.category_id = category_id;
        this.category_name = category_name;
        this.project_id = project_id;
        this.project_name = project_name;
        this.money_name = money_name;
        this.in_out_type = in_out_type;
        this.occur_date = occur_date;
        this.amount = amount;
        this.description = description;
    }

    public Long getMoney_id() {
        return money_id;
    }

    public void setMoney_id(Long money_id) {
        this.money_id = money_id;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public Long getProject_id() {
        return project_id;
    }

    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getMoney_name() {
        return money_name;
    }

    public void setMoney_name(String money_name) {
        this.money_name = money_name;
    }

    public String getIn_out_type() {
        return in_out_type;
    }

    public void setIn_out_type(String in_out_type) {
        this.in_out_type = in_out_type;
    }

    public Date getOccur_date() {
        return occur_date;
    }

    public void setOccur_date(Date occur_date) {
        this.occur_date = occur_date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "MoneyListItem{" +
                "money_id=" + money_id +
                ", category_id=" + category_id +
                ", category_name='" + category_name + '\'' +
                ", project_id=" + project_id +
                ", project_name='" + project_name + '\'' +
                ", money_name='" + money_name + '\'' +
                ", in_out_type='" + in_out_type + '\'' +
                ", occur_date=" + occur_date +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }
}
