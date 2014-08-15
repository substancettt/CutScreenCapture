package com.xs.image;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class CrossLinePatternHandle implements BaseNoiseHandle {
    private List<Integer> transverseNoiseList = new ArrayList<Integer>();
    private List<Integer> verticalNoiseList = new ArrayList<Integer>();
    public BufferedImage removeNoise(BufferedImage bi) {
        // TODO Auto-generated method stub
        for(int h: transverseNoiseList){
            for(int w: verticalNoiseList){
                
                if (h != 0 && h != bi.getHeight() - 1 && w != 0
                        && w != bi.getWidth() - 1) {
                    if (bi.getRGB(w - 1, h - 1) == Color.WHITE.value
                            && bi.getRGB(w + 1, h - 1) == Color.WHITE.value
                            && bi.getRGB(w + 1, h) == Color.BLACK.value
                            && bi.getRGB(w - 1, h + 1) == Color.WHITE.value
                            && bi.getRGB(w + 1, h) == Color.BLACK.value
                            && bi.getRGB(w + 1, h + 1) == Color.WHITE.value
                            && bi.getRGB(w, h - 1) == Color.BLACK.value
                            && bi.getRGB(w, h + 1) == Color.BLACK.value
                            ) {
                        
                        bi.setRGB(w, h, Color.WHITE.value);
                        bi.setRGB(w, h - 1, Color.WHITE.value);
                        bi.setRGB(w, h + 1, Color.WHITE.value);
                        bi.setRGB(w - 1, h, Color.WHITE.value);
                        bi.setRGB(w - 1, h + 1, Color.WHITE.value);
                        bi.setRGB(w - 1, h - 1, Color.WHITE.value);
                        bi.setRGB(w + 1, h, Color.WHITE.value);
                        bi.setRGB(w + 1, h - 1, Color.WHITE.value);
                        bi.setRGB(w + 1, h + 1, Color.WHITE.value);
                    }
                }else if( h == 0 && w != 0
                        && w != bi.getWidth() - 1){
                    if (bi.getRGB(w - 1, h + 1) == Color.WHITE.value
                            && bi.getRGB(w - 1, h) == Color.BLACK.value
                            && bi.getRGB(w + 1, h) == Color.BLACK.value
                            && bi.getRGB(w + 1, h + 1) == Color.WHITE.value
                            && bi.getRGB(w, h + 1) == Color.BLACK.value) {
                        bi.setRGB(w, h, Color.WHITE.value);
                        bi.setRGB(w - 1, h + 1, Color.WHITE.value);
                        bi.setRGB(w - 1, h, Color.WHITE.value);
                        bi.setRGB(w + 1, h, Color.WHITE.value);
                        bi.setRGB(w + 1, h + 1, Color.WHITE.value);
                        bi.setRGB(w, h + 1, Color.WHITE.value);
//                        System.out.println("CASE2");
                    }
                }else if( h == bi.getHeight() - 1 && w != 0
                        && w != bi.getWidth() - 1){
                    if ( bi.getRGB(w - 1, h - 1) == Color.WHITE.value
                            && bi.getRGB(w - 1, h) == Color.BLACK.value
                            && bi.getRGB(w + 1, h) == Color.BLACK.value
                            && bi.getRGB(w + 1, h - 1) == Color.WHITE.value
                            && bi.getRGB(w, h - 1) == Color.BLACK.value) {
                        bi.setRGB(w, h, Color.WHITE.value);
                        bi.setRGB(w - 1, h - 1, Color.WHITE.value);
                        bi.setRGB(w - 1, h, Color.WHITE.value);
                        bi.setRGB(w + 1, h, Color.WHITE.value);
                        bi.setRGB(w + 1, h, Color.WHITE.value);
                        bi.setRGB(w, h - 1, Color.WHITE.value);
//                        System.out.println("CASE3");
                    }
                }else if (h != 0 && h != bi.getHeight() - 1 && w == 0) {
                    if ( bi.getRGB(w + 1, h - 1) == Color.WHITE.value
                            && bi.getRGB(w + 1, h) == Color.BLACK.value
                            && bi.getRGB(w + 1, h + 1) == Color.WHITE.value
                            && bi.getRGB(w, h - 1) == Color.BLACK.value
                            && bi.getRGB(w, h + 1) == Color.BLACK.value) {
                        bi.setRGB(w, h, Color.WHITE.value);
                        bi.setRGB(w + 1, h - 1, Color.WHITE.value);
                        bi.setRGB(w + 1, h, Color.WHITE.value);
                        bi.setRGB(w + 1, h + 1, Color.WHITE.value);
                        bi.setRGB(w, h - 1, Color.WHITE.value);
                        bi.setRGB(w, h + 1, Color.WHITE.value);
//                        System.out.println("CASE4");
                    }
                }else if (h != 0 && h != bi.getHeight() - 1 && w == bi.getWidth() - 1) {
                    if ( bi.getRGB(w - 1, h - 1) == Color.WHITE.value
                            && bi.getRGB(w - 1, h) == Color.BLACK.value
                            && bi.getRGB(w - 1, h + 1) == Color.WHITE.value
                            && bi.getRGB(w, h - 1) == Color.BLACK.value
                            && bi.getRGB(w, h + 1) == Color.BLACK.value ) {
                        bi.setRGB(w, h, Color.WHITE.value);
                        bi.setRGB(w - 1, h - 1, Color.WHITE.value);
                        bi.setRGB(w - 1, h, Color.WHITE.value);
                        bi.setRGB(w - 1, h + 1, Color.WHITE.value);
                        bi.setRGB(w, h - 1, Color.WHITE.value);
                        bi.setRGB(w, h + 1, Color.WHITE.value);      
//                        System.out.println("CASE5");
                    }
                }else if(h == 0 && w == 0){
                    if(bi.getRGB(w + 1, h) == Color.BLACK.value && bi.getRGB(w + 1, h + 1) == Color.WHITE.value&& bi.getRGB(w, h + 1) == Color.BLACK.value ){
                        bi.setRGB(w, h, Color.WHITE.value);
                        bi.setRGB(w + 1, h, Color.WHITE.value);
                        bi.setRGB(w + 1, h + 1, Color.WHITE.value);
                        bi.setRGB(w, h + 1, Color.WHITE.value);
//                        System.out.println("CASE6");
                    }
                }else if(h == bi.getHeight() -1 && w == 0){
                    if(bi.getRGB(w + 1, h) == Color.BLACK.value && bi.getRGB(w + 1, h - 1) == Color.WHITE.value && bi.getRGB(w, h - 1) == Color.BLACK.value ){
                        bi.setRGB(w, h, Color.WHITE.value);
                        bi.setRGB(w + 1, h, Color.WHITE.value);
                        bi.setRGB(w + 1, h - 1, Color.WHITE.value);
                        bi.setRGB(w, h - 1, Color.WHITE.value);
//                        System.out.println("CASE7");
                    }
                }else if(h == 0 && w == bi.getWidth() - 1){
                    if(bi.getRGB(w - 1, h) == Color.BLACK.value && bi.getRGB(w - 1, h + 1) == Color.WHITE.value&& bi.getRGB(w, h + 1) == Color.BLACK.value ){
                        bi.setRGB(w, h, Color.WHITE.value);
                        bi.setRGB(w - 1, h, Color.WHITE.value);
                        bi.setRGB(w - 1, h + 1, Color.WHITE.value);
                        bi.setRGB(w, h + 1, Color.WHITE.value);
//                        System.out.println("CASE8");
                    }
                }else if(h == bi.getHeight() -1 && w == bi.getWidth() - 1){
                    if(bi.getRGB(w - 1, h) == Color.BLACK.value && bi.getRGB(w - 1, h - 1) == Color.WHITE.value&& bi.getRGB(w, h - 1) == Color.BLACK.value ){
                        bi.setRGB(w, h, Color.WHITE.value);
                        bi.setRGB(w - 1, h, Color.WHITE.value);
                        bi.setRGB(w - 1, h - 1, Color.WHITE.value);
                        bi.setRGB(w, h - 1, Color.WHITE.value);
//                        System.out.println("CASE9");
                    }
                }
            }
        }
        return bi;
    }

    public List<Integer> getNoiseLines(BufferedImage bi) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<Integer> getTransverseNoiseList() {
        return transverseNoiseList;
    }

    public void setTransverseNoiseList(List<Integer> transverseNoiseList) {
        this.transverseNoiseList = transverseNoiseList;
    }

    public List<Integer> getVerticalNoiseList() {
        return verticalNoiseList;
    }

    public void setVerticalNoiseList(List<Integer> verticalNoiseList) {
        this.verticalNoiseList = verticalNoiseList;
    }
    
    
}
