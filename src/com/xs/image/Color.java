package com.xs.image;

public enum Color {
    BLACK (-16777216),
    WHITE (-1);
    
    public int value;
    private Color(int value){
        this.value = value;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    
}
