package com.rabbyte.foody.domains;

import androidx.annotation.NonNull;

public class Price {
    private int Id;
    private String Value;

    public Price() {
    }

    public Price(int id, String value) {
        Id = id;
        Value = value;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    @NonNull
    @Override
    public String toString() {
        return Value;
    }
}
