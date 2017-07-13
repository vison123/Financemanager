package com.vison.finance.financemanager.framework.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/6.
 */

public class Project {
    private Long project_id;
    private String project_name;
    private Date start_date;
    private Date end_date;
    private String description;
    private String comment;
    private BigDecimal budget;
    private BigDecimal out_amount;
    private BigDecimal in_amount;

    public Project() {
    }

    public Project(Long project_id, String project_name, Date start_date, Date end_date, String description, String comment, BigDecimal budget, BigDecimal out_amount, BigDecimal in_amount) {
        this.project_id = project_id;
        this.project_name = project_name;
        this.start_date = start_date;
        this.end_date = end_date;
        this.description = description;
        this.comment = comment;
        this.budget = budget;
        this.out_amount = out_amount;
        this.in_amount = in_amount;
    }

    @Override
    public String toString() {
        return "Project{" +
                "project_id=" + project_id +
                ", project_name='" + project_name + '\'' +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", description='" + description + '\'' +
                ", comment='" + comment + '\'' +
                ", budget=" + budget +
                ", out_amount=" + out_amount +
                ", in_amount=" + in_amount +
                '}';
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

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public BigDecimal getOut_amount() {
        return out_amount;
    }

    public void setOut_amount(BigDecimal out_amount) {
        this.out_amount = out_amount;
    }

    public BigDecimal getIn_amount() {
        return in_amount;
    }

    public void setIn_amount(BigDecimal in_amount) {
        this.in_amount = in_amount;
    }
}
