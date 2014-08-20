package com.xs.image;

import java.awt.image.BufferedImage;
import java.util.List;

public class DiagonalPatternHandle implements BaseNoiseHandle {
    

    public BufferedImage removeNoise(BufferedImage bi) {
        // TODO Auto-generated method stub
    	bi = prevRemove(bi);
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

    public List<Integer> getNoiseLines(BufferedImage bi) {
        // TODO Auto-generated method stub
        return null;
    }

    public void setTransverseNoiseList(List<Integer> transverseNoiseList) {
        // TODO Auto-generated method stub
        
    }

    public void setVerticalNoiseList(List<Integer> verticalNoiseList) {
        // TODO Auto-generated method stub
        
    }

    private BufferedImage prevRemove(BufferedImage  bi){
   	 for(int w = 0; w < bi.getWidth(); ++w){
        	bi.setRGB(w, 0, Color.WHITE.value);
        	bi.setRGB(w, bi.getHeight() - 1, Color.WHITE.value);
        }
        for(int h = 0; h < bi.getHeight(); ++h){
        	bi.setRGB(0, h, Color.WHITE.value);
        	bi.setRGB(bi.getWidth() - 1, h, Color.WHITE.value);
        }
        return bi;
   }
}
