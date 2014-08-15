package com.xs.image;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class VerticalLinePatternHandle implements BaseNoiseHandle {

    @Override
    public BufferedImage removeNoise(BufferedImage bi) {
        // TODO Auto-generated method stub
        List<Integer> noiseList = getNoiseLines(bi);
        for(int w: noiseList){
            for(int h = 0; h < bi.getHeight(); ++h){
                
                if (h != 0 && h != bi.getHeight() - 1 && w != 0
                        && w != bi.getWidth() - 1) {
                    if (!(bi.getRGB(w - 1, h - 1) == Color.BLACK.value
                            || bi.getRGB(w - 1, h - 1) == Color.BLACK.value
                            || bi.getRGB(w + 1, h) == Color.BLACK.value
                            || bi.getRGB(w - 1, h + 1) == Color.BLACK.value
                            || bi.getRGB(w + 1, h) == Color.BLACK.value
                            || bi.getRGB(w + 1, h + 1) == Color.BLACK.value)) {
//                        System.out.println("CASE1");
                        bi.setRGB(w, h, Color.WHITE.value);
                    }
                }else if( h == 0 && w != 0
                        && w != bi.getWidth() - 1){
                    if ( !(bi.getRGB(w - 1, h + 1) == Color.BLACK.value
                            || bi.getRGB(w - 1, h) == Color.BLACK.value
                            || bi.getRGB(w + 1, h) == Color.BLACK.value
                            || bi.getRGB(w + 1, h + 1) == Color.BLACK.value)) {
//                        System.out.println("CASE2");
                        bi.setRGB(w, h, Color.WHITE.value);
                    }
                }else if( h == bi.getHeight() - 1 && w != 0
                        && w != bi.getWidth() - 1){
                    if ( !(bi.getRGB(w - 1, h - 1) == Color.BLACK.value
                            || bi.getRGB(w - 1, h) == Color.BLACK.value
                            || bi.getRGB(w + 1, h) == Color.BLACK.value
                            || bi.getRGB(w + 1, h - 1) == Color.BLACK.value)) {
//                        System.out.println("CASE3");
                        bi.setRGB(w, h, Color.WHITE.value);
                    }
                }else if (h != 0 && h != bi.getHeight() - 1 && w == 0) {
                    if (!( bi.getRGB(w + 1, h - 1) == Color.BLACK.value
                            ||bi.getRGB(w + 1, h) == Color.BLACK.value
                            || bi.getRGB(w + 1, h + 1) == Color.BLACK.value)) {
//                        System.out.println("CASE4");
                        bi.setRGB(w, h, Color.WHITE.value);
                    }
                }else if (h != 0 && h != bi.getHeight() - 1 && w == bi.getWidth() - 1) {
                    if (!( bi.getRGB(w - 1, h - 1) == Color.BLACK.value
                            ||bi.getRGB(w - 1, h) == Color.BLACK.value
                            || bi.getRGB(w - 1, h + 1) == Color.BLACK.value)) {
//                        System.out.println("CASE5");
                        bi.setRGB(w, h, Color.WHITE.value);
                    }
                }else if(h == 0 && w == 0){
                    if(!(bi.getRGB(w + 1, h) == Color.BLACK.value || bi.getRGB(w + 1, h + 1) == Color.BLACK.value) ){
//                        System.out.println("CASE6");
                        bi.setRGB(w, h, Color.WHITE.value);
                    }
                }else if(h == bi.getHeight() -1 && w == 0){
                    if(!(bi.getRGB(w + 1, h) == Color.BLACK.value || bi.getRGB(w + 1, h - 1) == Color.BLACK.value) ){
//                        System.out.println("CASE7");
                        bi.setRGB(w, h, Color.WHITE.value);
                    }
                }else if(h == 0 && w == bi.getWidth() - 1){
                    if(!(bi.getRGB(w - 1, h) == Color.BLACK.value || bi.getRGB(w - 1, h + 1) == Color.BLACK.value) ){
//                        System.out.println("CASE8");
                        bi.setRGB(w, h, Color.WHITE.value);
                    }
                }else if(h == bi.getHeight() -1 && w == bi.getWidth() - 1){
                    if(!(bi.getRGB(w - 1, h) == Color.BLACK.value || bi.getRGB(w - 1, h - 1) == Color.BLACK.value) ){
//                        System.out.println("CASE9");
                        bi.setRGB(w, h, Color.WHITE.value);
                    }
                }
            }
        }
        return bi;
    }

    public List<Integer> getNoiseLines(BufferedImage bi){
        List<Integer> noiseList = new ArrayList<Integer>();
        for (int w = 0; w < bi.getWidth(); ++w) {
            boolean lineNoiseFlag = true;
            for (int h = 0; h < bi.getHeight(); ++h) {
                if (bi.getRGB(w, h) == -1) {
                    lineNoiseFlag = false;
                }

            }
            if (lineNoiseFlag) {
                noiseList.add(w);
            }
        }
        return noiseList;
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
