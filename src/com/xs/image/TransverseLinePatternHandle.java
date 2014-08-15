package com.xs.image;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class TransverseLinePatternHandle implements BaseNoiseHandle{

    public BufferedImage removeNoise(BufferedImage bi) {
        // TODO Auto-generated method stub
        List<Integer> noiseList = getNoiseLines(bi);
        if(noiseList !=null && noiseList.size()>0){
            for (int h : noiseList) {
                for (int w = 0; w < bi.getWidth(); ++w) {
                    
                    if (h != 0 && h != bi.getHeight() - 1 && w != 0
                            && w != bi.getWidth() - 1) {
                        if (!(bi.getRGB(w - 1, h - 1) == Color.BLACK.value
                                || bi.getRGB(w, h - 1) == Color.BLACK.value
                                || bi.getRGB(w + 1, h - 1) == Color.BLACK.value
                                || bi.getRGB(w - 1, h + 1) == Color.BLACK.value
                                || bi.getRGB(w, h + 1) == Color.BLACK.value
                                || bi.getRGB(w + 1, h + 1) == Color.BLACK.value)) {
                            
                            bi.setRGB(w, h, Color.WHITE.value);
                        }
                    }else if( h == 0 && w != 0
                            && w != bi.getWidth() - 1){
                        if ( !(bi.getRGB(w - 1, h + 1) == Color.BLACK.value
                                || bi.getRGB(w, h + 1) == Color.BLACK.value
                                || bi.getRGB(w + 1, h + 1) == Color.BLACK.value)) {
                            bi.setRGB(w, h, Color.WHITE.value);
                        }
                    }else if( h == bi.getHeight() - 1 && w != 0
                            && w != bi.getWidth() - 1){
                        if ( !(bi.getRGB(w - 1, h - 1) == Color.BLACK.value
                                || bi.getRGB(w, h - 1) == Color.BLACK.value
                                || bi.getRGB(w + 1, h - 1) == Color.BLACK.value)) {
                            bi.setRGB(w, h, Color.WHITE.value);
                        }
                    }else if (h != 0 && h != bi.getHeight() - 1 && w == 0) {
                        if (!(bi.getRGB(w, h - 1) == Color.BLACK.value
                                || bi.getRGB(w + 1, h - 1) == Color.BLACK.value
                               
                                || bi.getRGB(w, h + 1) == Color.BLACK.value
                                || bi.getRGB(w + 1, h + 1) == Color.BLACK.value)) {
                            bi.setRGB(w, h, Color.WHITE.value);
                        }
                    }else if (h != 0 && h != bi.getHeight() - 1 && w == bi.getWidth() - 1) {
                        if (!(bi.getRGB(w, h - 1) == Color.BLACK.value
                                || bi.getRGB(w - 1, h - 1) == Color.BLACK.value
                               
                                || bi.getRGB(w, h + 1) == Color.BLACK.value
                                || bi.getRGB(w - 1, h + 1) == Color.BLACK.value)) {
                            bi.setRGB(w, h, Color.WHITE.value);
                        }
                    }else if(h == 0 && w == 0){
                        if(!(bi.getRGB(w, h + 1) == Color.BLACK.value || bi.getRGB(w + 1, h + 1) == Color.BLACK.value) ){
                            bi.setRGB(w, h, Color.WHITE.value);
                        }
                    }else if(h == bi.getHeight() -1 && w == 0){
                        if(!(bi.getRGB(w, h - 1) == Color.BLACK.value || bi.getRGB(w + 1, h - 1) == Color.BLACK.value) ){
                            bi.setRGB(w, h, Color.WHITE.value);
                        }
                    }else if(h == 0 && w == bi.getWidth() - 1){
                        if(!(bi.getRGB(w, h + 1) == Color.BLACK.value || bi.getRGB(w - 1, h + 1) == Color.BLACK.value) ){
                            bi.setRGB(w, h, Color.WHITE.value);
                        }
                    }else if(h == bi.getHeight() -1 && w == bi.getWidth() - 1){
                        if(!(bi.getRGB(w, h - 1) == Color.BLACK.value || bi.getRGB(w - 1, h - 1) == Color.BLACK.value) ){
                            bi.setRGB(w, h, Color.WHITE.value);
                        }
                    }
                }
            }
        }
        return bi;
    }

    public List<Integer> getNoiseLines(BufferedImage bi){
        List<Integer> noiseList = new ArrayList<Integer>();
        for (int h = 0; h < bi.getHeight(); ++h) {
            boolean lineNoiseFlag = true;
            for (int w = 0; w < bi.getWidth(); ++w) {
                if (bi.getRGB(w, h) == -1) {
                    lineNoiseFlag = false;
                }

            }
            if (lineNoiseFlag) {
                noiseList.add(h);
            }
        }
        return noiseList;
    }

    public void setTransverseNoiseList(List<Integer> transverseNoiseList) {
        // TODO Auto-generated method stub
        
    }

    public void setVerticalNoiseList(List<Integer> verticalNoiseList) {
        // TODO Auto-generated method stub
        
    }
}
