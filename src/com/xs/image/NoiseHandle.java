package com.xs.image;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoiseHandle implements BaseNoiseHandle {

    public BufferedImage removeNoise(BufferedImage bi) {
        Map<Integer, List<Integer>> noiseMap = new HashMap<Integer, List<Integer>>();
        for (int h = 0; h < bi.getHeight(); ++h) {
            for (int w = 0; w < bi.getWidth(); ++w) {

                if (h != 0 && h != bi.getHeight() - 1 && w != 0
                        && w != bi.getWidth() - 1) {
                    if(bi.getRGB(w, h) == Color.BLACK.value ){
                        int count = 9;
                        int blackCount = 0;
                        if(bi.getRGB(w - 1, h -1) == Color.BLACK.value){
                            blackCount++;
                        }
                        if(bi.getRGB(w - 1, h) == Color.BLACK.value){
                            blackCount++;
                        }
                        if(bi.getRGB(w - 1, h + 1) == Color.BLACK.value){
                            blackCount++;
                        }
                        if(bi.getRGB(w, h - 1) == Color.BLACK.value){
                            blackCount++;
                        }
                        if(bi.getRGB(w , h + 1) == Color.BLACK.value){
                            blackCount++;
                        }
                        if(bi.getRGB(w + 1, h -1) == Color.BLACK.value){
                            blackCount++;
                        }
                        if(bi.getRGB(w + 1, h) == Color.BLACK.value){
                            blackCount++;
                        }
                        if(bi.getRGB(w + 1, h + 1) == Color.BLACK.value){
                            blackCount++;
                        }
                        if(blackCount*2 <= count){
                            if(noiseMap.get(w)!=null){
                                noiseMap.get(w).add(h);
                            }else{
                                List<Integer> hList = new ArrayList<Integer>();
                                hList.add(h);
                                noiseMap.put(w, hList);
                            }
                        }
                    }
                } else if (h == 0 && w != 0 && w != bi.getWidth() - 1) {
                    if(bi.getRGB(w, h) == Color.BLACK.value ){
                        int count = 6;
                        int blackCount = 0;
                 
                        if(bi.getRGB(w - 1, h) == Color.BLACK.value){
                            blackCount++;
                        }
                        if(bi.getRGB(w - 1, h + 1) == Color.BLACK.value){
                            blackCount++;
                        }
                        
                        if(bi.getRGB(w , h + 1) == Color.BLACK.value){
                            blackCount++;
                        }
                      
                        if(bi.getRGB(w + 1, h) == Color.BLACK.value){
                            blackCount++;
                        }
                        if(bi.getRGB(w + 1, h + 1) == Color.BLACK.value){
                            blackCount++;
                        }
                        if(blackCount*2 <= count){
                            if(noiseMap.get(w)!=null){
                                noiseMap.get(w).add(h);
                            }else{
                                List<Integer> hList = new ArrayList<Integer>();
                                hList.add(h);
                                noiseMap.put(w, hList);
                            }
                        }
                    }
                } else if (h == bi.getHeight() - 1 && w != 0
                        && w != bi.getWidth() - 1) {
                    if(bi.getRGB(w, h) == Color.BLACK.value ){
                        int count = 6;
                        int blackCount = 0;
                        if(bi.getRGB(w - 1, h -1) == Color.BLACK.value){
                            blackCount++;
                        }
                        if(bi.getRGB(w - 1, h) == Color.BLACK.value){
                            blackCount++;
                        }
                        
                        if(bi.getRGB(w, h - 1) == Color.BLACK.value){
                            blackCount++;
                        }
                       
                        if(bi.getRGB(w + 1, h -1) == Color.BLACK.value){
                            blackCount++;
                        }
                        if(bi.getRGB(w + 1, h) == Color.BLACK.value){
                            blackCount++;
                        }
                        
                        if(blackCount*2 <= count){
                            if(noiseMap.get(w)!=null){
                                noiseMap.get(w).add(h);
                            }else{
                                List<Integer> hList = new ArrayList<Integer>();
                                hList.add(h);
                                noiseMap.put(w, hList);
                            }
                        }
                    }
                } else if (h != 0 && h != bi.getHeight() - 1 && w == 0) {
                    if(bi.getRGB(w, h) == Color.BLACK.value ){
                        int count = 6;
                        int blackCount = 0;
                       
                        
                        if(bi.getRGB(w, h - 1) == Color.BLACK.value){
                            blackCount++;
                        }
                        if(bi.getRGB(w , h + 1) == Color.BLACK.value){
                            blackCount++;
                        }
                        if(bi.getRGB(w + 1, h -1) == Color.BLACK.value){
                            blackCount++;
                        }
                        if(bi.getRGB(w + 1, h) == Color.BLACK.value){
                            blackCount++;
                        }
                        if(bi.getRGB(w + 1, h + 1) == Color.BLACK.value){
                            blackCount++;
                        }
                        if(blackCount*2 <= count){
                            if(noiseMap.get(w)!=null){
                                noiseMap.get(w).add(h);
                            }else{
                                List<Integer> hList = new ArrayList<Integer>();
                                hList.add(h);
                                noiseMap.put(w, hList);
                            }
                        }
                    }
                } else if (h != 0 && h != bi.getHeight() - 1
                        && w == bi.getWidth() - 1) {
                    if(bi.getRGB(w, h) == Color.BLACK.value ){
                        int count = 6;
                        int blackCount = 0;
                        if(bi.getRGB(w - 1, h -1) == Color.BLACK.value){
                            blackCount++;
                        }
                        if(bi.getRGB(w - 1, h) == Color.BLACK.value){
                            blackCount++;
                        }
                        if(bi.getRGB(w - 1, h + 1) == Color.BLACK.value){
                            blackCount++;
                        }
                        if(bi.getRGB(w, h - 1) == Color.BLACK.value){
                            blackCount++;
                        }
                        if(bi.getRGB(w , h + 1) == Color.BLACK.value){
                            blackCount++;
                        }
                     
                        if(blackCount*2 <= count){
                            if(noiseMap.get(w)!=null){
                                noiseMap.get(w).add(h);
                            }else{
                                List<Integer> hList = new ArrayList<Integer>();
                                hList.add(h);
                                noiseMap.put(w, hList);
                            }
                        }
                    }
                } else if (h == 0 && w == 0) {
                    if(bi.getRGB(w, h) == Color.BLACK.value ){
                        int count = 4;
                        int blackCount = 0;
                       
                        
                        if(bi.getRGB(w , h + 1) == Color.BLACK.value){
                            blackCount++;
                        }
                        
                        if(bi.getRGB(w + 1, h) == Color.BLACK.value){
                            blackCount++;
                        }
                        if(bi.getRGB(w + 1, h + 1) == Color.BLACK.value){
                            blackCount++;
                        }
                        if(blackCount*2 <= count){
                            if(noiseMap.get(w)!=null){
                                noiseMap.get(w).add(h);
                            }else{
                                List<Integer> hList = new ArrayList<Integer>();
                                hList.add(h);
                                noiseMap.put(w, hList);
                            }
                        }
                    }
                } else if (h == bi.getHeight() - 1 && w == 0) {
                    if(bi.getRGB(w, h) == Color.BLACK.value ){
                        int count = 4;
                        int blackCount = 0;
                        
                        if(bi.getRGB(w, h - 1) == Color.BLACK.value){
                            blackCount++;
                        }
                        
                        if(bi.getRGB(w + 1, h -1) == Color.BLACK.value){
                            blackCount++;
                        }
                        if(bi.getRGB(w + 1, h) == Color.BLACK.value){
                            blackCount++;
                        }
                       
                        if(blackCount*2 <= count){
                            if(noiseMap.get(w)!=null){
                                noiseMap.get(w).add(h);
                            }else{
                                List<Integer> hList = new ArrayList<Integer>();
                                hList.add(h);
                                noiseMap.put(w, hList);
                            }
                        }
                    }
                } else if (h == 0 && w == bi.getWidth() - 1) {
                    if(bi.getRGB(w, h) == Color.BLACK.value ){
                        int count = 4;
                        int blackCount = 0;
                       
                        if(bi.getRGB(w - 1, h) == Color.BLACK.value){
                            blackCount++;
                        }
                        if(bi.getRGB(w - 1, h + 1) == Color.BLACK.value){
                            blackCount++;
                        }
                       
                        if(bi.getRGB(w , h + 1) == Color.BLACK.value){
                            blackCount++;
                        }
                        
                        if(blackCount*2 <= count){
                            if(noiseMap.get(w)!=null){
                                noiseMap.get(w).add(h);
                            }else{
                                List<Integer> hList = new ArrayList<Integer>();
                                hList.add(h);
                                noiseMap.put(w, hList);
                            }
                        }
                    }
                } else if (h == bi.getHeight() - 1 && w == bi.getWidth() - 1) {
                    if(bi.getRGB(w, h) == Color.BLACK.value ){
                        int count = 4;
                        int blackCount = 0;
                        if(bi.getRGB(w - 1, h -1) == Color.BLACK.value){
                            blackCount++;
                        }
                        if(bi.getRGB(w - 1, h) == Color.BLACK.value){
                            blackCount++;
                        }
                       
                        if(bi.getRGB(w, h - 1) == Color.BLACK.value){
                            blackCount++;
                        }
                      
                        if(blackCount*2 <= count){
                            if(noiseMap.get(w)!=null){
                                noiseMap.get(w).add(h);
                            }else{
                                List<Integer> hList = new ArrayList<Integer>();
                                hList.add(h);
                                noiseMap.put(w, hList);
                            }
                        }
                    }
                }
            }

        }
        
        for(int w:noiseMap.keySet()){
            for(int h:noiseMap.get(w)){
                bi.setRGB(w, h, Color.WHITE.value);
            }
        }
        return bi;
    }

    public void setTransverseNoiseList(List<Integer> transverseNoiseList) {
        // TODO Auto-generated method stub

    }

    public void setVerticalNoiseList(List<Integer> verticalNoiseList) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Integer> getNoiseLines(BufferedImage bi) {
        // TODO Auto-generated method stub
        return null;
    }
}
