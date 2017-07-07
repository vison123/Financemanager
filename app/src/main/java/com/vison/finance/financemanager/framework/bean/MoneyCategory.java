package com.vison.finance.financemanager.framework.bean;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/7/6.
 */

public class MoneyCategory {
    private Long category_id;
    private Long project_id;
    private String name;
    private BigDecimal category_budget;
    private String description;

    public MoneyCategory(Long category_id, Long project_id, String name, BigDecimal category_budget, String description) {
        this.category_id = category_id;
        this.project_id = project_id;
        this.name = name;
        this.category_budget = category_budget;
        this.description = description;
    }

    @Override
    public String toString() {
        return "MoneyCategory{" +
                "category_id=" + category_id +
                ", project_id=" + project_id +
                ", name='" + name + '\'' +
                ", category_budget=" + category_budget +
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
