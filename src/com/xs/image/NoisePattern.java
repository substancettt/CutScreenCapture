package com.xs.image;

public enum NoisePattern {
    TRANSVERSE ("TransverseLinePattern"),
    VERTICAL ("VerticalLinePattern"),
    CROSS ("CrossLinePattern"),
    DIAGONAL ("DiagonalPattern");
    
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
