package com.xs.image;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiagonalPatternHandle implements BaseNoiseHandle {
    

    @Override
    public BufferedImage removeNoise(BufferedImage bi) {
        // TODO Auto-generated method stub
            for (int h = 0; h < bi.getHeight(); ++h) {
                for (int w = 0; w < bi.getWidth(); ++w) {
                    
                    if (h != 0 && h != bi.getHeight() - 1 && w != 0
                            && w != bi.getWidth() - 1) {
                        if (!(bi.getRGB(w, h - 1) == Color.BLACK.value
                                || bi.getRGB(w + 1, h) == Color.BLACK.value
                                || bi.getRGB(w - 1, h ) == Color.BLACK.value
                                || bi.getRGB(w, h + 1) == Color.BLACK.value)) {
//                            System.out.println("CASE1");
                            bi.setRGB(w, h, Color.WHITE.value);
                        }
                    }else if( h == 0 && w != 0
                            && w != bi.getWidth() - 1){
                        if ( !( bi.getRGB(w + 1, h) == Color.BLACK.value
                                || bi.getRGB(w - 1, h ) == Color.BLACK.value
                                || bi.getRGB(w, h + 1) == Color.BLACK.value)) {
                            bi.setRGB(w, h, Color.WHITE.value);
//                            System.out.println("CASE2");
                        }
                    }else if( h == bi.getHeight() - 1 && w != 0
                            && w != bi.getWidth() - 1){
                        if ( !(bi.getRGB(w, h - 1) == Color.BLACK.value
                                || bi.getRGB(w + 1, h) == Color.BLACK.value
                                || bi.getRGB(w - 1, h ) == Color.BLACK.value)) {
                            bi.setRGB(w, h, Color.WHITE.value);
//                            System.out.println("CASE3");
                        }
                    }else if (h != 0 && h != bi.getHeight() - 1 && w == 0) {
                        if (!(bi.getRGB(w, h - 1) == Color.BLACK.value
                                || bi.getRGB(w + 1, h) == Color.BLACK.value
                                
                                || bi.getRGB(w, h + 1) == Color.BLACK.value)) {
                            bi.setRGB(w, h, Color.WHITE.value);
//                            System.out.println("CASE4");
                        }
                    }else if (h != 0 && h != bi.getHeight() - 1 && w == bi.getWidth() - 1) {
                        if (!(bi.getRGB(w, h - 1) == Color.BLACK.value
                                
                                || bi.getRGB(w - 1, h ) == Color.BLACK.value
                                || bi.getRGB(w, h + 1) == Color.BLACK.value)) {
                            bi.setRGB(w, h, Color.WHITE.value);
//                            System.out.println("CASE5");
                        }
                    }else if(h == 0 && w == 0){
                        if(!( bi.getRGB(w + 1, h) == Color.BLACK.value
                                
                                || bi.getRGB(w, h + 1) == Color.BLACK.value) ){
                            bi.setRGB(w, h, Color.WHITE.value);
//                            System.out.println("CASE6");
                        }
                    }else if(h == bi.getHeight() -1 && w == 0){
                        if(!(bi.getRGB(w, h - 1) == Color.BLACK.value
                                || bi.getRGB(w + 1, h) == Color.BLACK.value
                               
                                ) ){
                            bi.setRGB(w, h, Color.WHITE.value);
//                            System.out.println("CASE7");
                        }
                    }else if(h == 0 && w == bi.getWidth() - 1){
                        if(!( bi.getRGB(w - 1, h ) == Color.BLACK.value
                                || bi.getRGB(w, h + 1) == Color.BLACK.value) ){
                            bi.setRGB(w, h, Color.WHITE.value);
//                            System.out.println("CASE8");
                        }
                    }else if(h == bi.getHeight() -1 && w == bi.getWidth() - 1){
                        if(!(bi.getRGB(w, h - 1) == Color.BLACK.value
                              
                                || bi.getRGB(w - 1, h ) == Color.BLACK.value) ){
                            bi.setRGB(w, h, Color.WHITE.value);
//                            System.out.println("CASE9");
                        }
                    }
                }
            }
        return bi;
    }

    @Override
    public List<Integer> getNoiseLines(BufferedImage bi) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setTransverseNoiseList(List<Integer> transverseNoiseList) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setVerticalNoiseList(List<Integer> verticalNoiseList) {
        // TODO Auto-generated method stub
        
    }


}
