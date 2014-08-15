package com.xs.image;

public enum NoisePattern {
    TRANSVERSE ("TransverseLine"),
    VERTICAL ("VerticalLine"),
    CROSS ("CrossLine"),
    DIAGONAL ("Diagonal");
    
    public String value;
    private NoisePattern(String value){
        this.value = value;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    
}
