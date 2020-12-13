package com.ajira.ajirayaan.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AreaMap {
    @Id
    @GeneratedValue
    private Long id;
    private int rowNo;
    private int rowLimit;
    private int colLimit;
    private String row;

    public AreaMap() {
    }

    public AreaMap(Long id, int rowNo, int rowLimit, int colLimit, String row) {
        this.id = id;
        this.rowNo = rowNo;
        this.rowLimit = rowLimit;
        this.colLimit = colLimit;
        this.row = row;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRowNo() {
        return rowNo;
    }

    public void setRowNo(int rowNo) {
        this.rowNo = rowNo;
    }

    public int getRowLimit() {
        return rowLimit;
    }

    public void setRowLimit(int rowLimit) {
        this.rowLimit = rowLimit;
    }

    public int getColLimit() {
        return colLimit;
    }

    public void setColLimit(int colLimit) {
        this.colLimit = colLimit;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    @Override
    public String toString() {
        return "AreaMap{" +
                "id=" + id +
                ", rowNo=" + rowNo +
                ", rowLimit=" + rowLimit +
                ", colLimit=" + colLimit +
                ", row='" + row + '\'' +
                '}';
    }
}
