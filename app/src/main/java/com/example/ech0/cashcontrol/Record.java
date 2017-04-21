package com.example.ech0.cashcontrol;

/**
 * Created by ech0 on 4/21/2017.
 */

public class Record {
    int _id;
    int _date;
    int _sum;
    int _category;
    String _comment;

    public Record() {}

    public Record(int _id, int _date, int _sum, int _category, String _comment) {
        this._id = _id;
        this._date = _date;
        this._sum = _sum;
        this._category = _category;
        this._comment = _comment;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_date() {
        return _date;
    }

    public void set_date(int _date) {
        this._date = _date;
    }

    public int get_sum() {
        return _sum;
    }

    public void set_sum(int _sum) {
        this._sum = _sum;
    }

    public int get_category() {
        return _category;
    }

    public void set_category(int _category) {
        this._category = _category;
    }

    public String get_comment() {
        return _comment;
    }

    public void set_comment(String _comment) {
        this._comment = _comment;
    }
}
