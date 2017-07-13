package com.vison.finance.financemanager.framework.bean;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/7/6.
 */

public class Category {
    private Long category_id;
    private Long project_id;
    private String name;
    private BigDecimal category_budget;
    private BigDecimal in_amount;
    private BigDecimal out_amount;
    private String description;

    public Category() {
    }

    public Category(Long category_id, Long project_id, String name, BigDecimal category_budget, BigDecimal in_amount, BigDecimal out_amount, String description) {
        this.category_id = category_id;
        this.project_id = project_id;
        this.name = name;
        this.category_budget = category_budget;
        this.in_amount = in_amount;
        this.out_amount = out_amount;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Category{" +
                "category_id=" + category_id +
                ", project_id=" + project_id +
                ", name='" + name + '\'' +
                ", category_budget=" + category_budget +
                ", in_amount=" + in_amount +
                ", out_amount=" + out_amount +
                ", description='" + description + '\'' +
                '}';
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public Long getProject_id() {
        return project_id;
    }

    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCategory_budget() {
        return category_budget;
    }

    public void setCategory_budget(BigDecimal category_budget) {
        this.category_budget = category_budget;
    }

    public BigDecimal getIn_amount() {
        return in_amount;
    }

    public void setIn_amount(BigDecimal in_amount) {
        this.in_amount = in_amount;
    }

    public BigDecimal getOut_amount() {
        return out_amount;
    }

    public void setOut_amount(BigDecimal out_amount) {
        this.out_amount = out_amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
