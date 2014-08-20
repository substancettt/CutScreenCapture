package com.xs.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.imageio.*;

public class ImageHandle {
    public static Map<String, BufferedImage> standardImageMap = new HashMap<String, BufferedImage>();
    
    
    
    
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        loadPattern();
        File inputFile = new File(
                "C:\\Users\\lixu1\\workspaceForGit\\CutScreenCapture\\logs\\handledImage.bmp");
        BufferedImage bi = ImageIO.read(inputFile);
        
        BufferedImage outputBi = null;
        
        String noistType = getNoiseType(bi);
        System.out.println(noistType);
        if("CrossLinePattern".equals(noistType)){
            List<Integer> noiseList1 = NoiseHandleFactory.getBaseNoiseHandlerFactory()
            .getNoiseHandle(NoisePattern.TRANSVERSE.value).getNoiseLines(bi);

            List<Integer> noiseList2 = NoiseHandleFactory.getBaseNoiseHandlerFactory()
            .getNoiseHandle(NoisePattern.VERTICAL.value).getNoiseLines(bi);

            
            BaseNoiseHandle unDefBn = NoiseHandleFactory.getBaseNoiseHandlerFactory().getNoiseHandle("undefined");
            unDefBn.setTransverseNoiseList(noiseList1);
            unDefBn.setVerticalNoiseList(noiseList2);
            outputBi = unDefBn.removeNoise(bi);
            
            System.out.println("CrossLine");
        }else{            
            if("TransverseLinePattern".equals(noistType)){
            	BaseNoiseHandle unDefBn = NoiseHandleFactory.getBaseNoiseHandlerFactory().getNoiseHandle("undefined");
            	List<Integer> noiseList = NoiseHandleFactory.getBaseNoiseHandlerFactory()
                        .getNoiseHandle(NoisePattern.TRANSVERSE.value).getNoiseLines(bi);
            	unDefBn.setTransverseNoiseList(noiseList);
            	outputBi = unDefBn.removeNoise(bi);
            }else if("VerticalLinePattern".equals(noistType)){
            	BaseNoiseHandle unDefBn = NoiseHandleFactory.getBaseNoiseHandlerFactory().getNoiseHandle("undefined");
            	List<Integer> noiseList = NoiseHandleFactory.getBaseNoiseHandlerFactory()
                        .getNoiseHandle(NoisePattern.VERTICAL.value).getNoiseLines(bi);
            	unDefBn.setVerticalNoiseList(noiseList);
            	outputBi = unDefBn.removeNoise(bi);
            }else{
            	outputBi = NoiseHandleFactory.getBaseNoiseHandlerFactory().getNoiseHandle(NoisePattern.DIAGONAL.value).removeNoise(bi);
            }
           
        }
        
//      outputBi = NoiseHandleFactory.getBaseNoiseHandlerFactory().getNoiseHandle("undefined").removeNoise(bi);

       
//        int diffValues = ImageHandle.getDiffValues(outputBi, standardImageMap.get("8"));

//        System.out.println(diffValues);
        

        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);

        ImageIO.write(outputBi, "bmp", new File(
                "C:\\Users\\lixu1\\workspaceForGit\\CutScreenCapture\\logs\\myImage.bmp"));
     

    }

    public static int getDiffValues(BufferedImage inputBi, BufferedImage standardBi)
            throws IOException {
        int diffValues = 0;

        for (int h = 0; h < inputBi.getHeight(); ++h) {
            for (int w = 0; w < inputBi.getWidth(); ++w) {
                if (inputBi.getRGB(w, h) != standardBi.getRGB(w, h)) {
                    diffValues++;
                }
            }
        }
        return diffValues;
    }
    
    public static String getNoiseType(BufferedImage bi){
        List<Integer> transverseNoiseList = new ArrayList<Integer>();
        List<Integer> verticalNoiseList = new ArrayList<Integer>();
        for (int h = 0; h < bi.getHeight(); ++h) {
            boolean lineNoiseFlag = true;
            for (int w = 0; w < bi.getWidth(); ++w) {
                if (bi.getRGB(w, h) == -1) {
                    lineNoiseFlag = false;
                }

            }
            if (lineNoiseFlag) {
                transverseNoiseList.add(h);
            }
        }
       
        for (int w = 0; w < bi.getWidth(); ++w) {
            boolean lineNoiseFlag = true;
            for (int h = 0; h < bi.getHeight(); ++h) {
                if (bi.getRGB(w, h) == -1) {
                    lineNoiseFlag = false;
                }

            }
            if (lineNoiseFlag) {
                verticalNoiseList.add(w);
            }
        }
        System.out.println(transverseNoiseList);
        System.out.println(verticalNoiseList);
        if(transverseNoiseList.size()>2 && verticalNoiseList.size()>2){
            return NoisePattern.CROSS.value;
        }
        if(transverseNoiseList.size()>2){
            return NoisePattern.TRANSVERSE.value;
        }
        if(verticalNoiseList.size()>2){
            return NoisePattern.VERTICAL.value;
        }
        return NoisePattern.DIAGONAL.value;
    }
    
    public static void loadPattern() throws IOException {

        for (int n = 0; n < 10; n++) {

            File inputFile = new File(

                    "C:\\Users\\lixu1\\work\\New folder\\charImage_" + n + ".bmp");

            BufferedImage charImage = ImageIO.read(inputFile);

            standardImageMap.put(String.valueOf(n), charImage);

        }

    }

    
    
}


