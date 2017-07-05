package com.vison.finance.financemanager.framework.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/6.
 */

public class Money {
    private Long money_id;
    private Long money_category_id;
    private Long project_id;
    private String in_out_type;
    private Date occur_date;
    private BigDecimal amount;

    public Money(Long money_id, Long money_category_id, Long project_id, String in_out_type, Date occur_date, BigDecimal amount) {
        this.money_id = money_id;
        this.money_category_id = money_category_id;
        this.project_id = project_id;
        this.in_out_type = in_out_type;
        this.occur_date = occur_date;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Money{" +
                "money_id=" + money_id +
                ", money_category_id=" + money_category_id +
                ", project_id=" + project_id +
                ", in_out_type='" + in_out_type + '\'' +
                ", occur_date=" + occur_date +
                ", amount=" + amount +
                '}';
    }

    public Long getMoney_id() {
        return money_id;
    }

    public void setMoney_id(Long money_id) {
        this.money_id = money_id;
    }

    public Long getMoney_category_id() {
        return money_category_id;
    }

    public void setMoney_category_id(Long money_category_id) {
        this.money_category_id = money_category_id;
    }

    public Long getProject_id() {
        return project_id;
    }

    public void setProject_id(Long project_id) {
        this.project_id = project_id;
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
}
