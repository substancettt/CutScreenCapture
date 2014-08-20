package com.xs.image;

import java.util.ArrayList;
import java.util.List;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;  
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.Properties;
import java.io.FileInputStream;

public class PicCapture {
    private static Robot robot;
    private static BufferedImage pickedImage = null;
    private static BufferedImage targetImage = null;
    private static BufferedImage handledImage = null;
    private static ArrayList<BufferedImage> charImageList = new ArrayList<BufferedImage>();
    private static Map<String, BufferedImage> standardImageMap = null;
    private static List <Integer> x_offsets = new ArrayList<Integer>();
    private static int x_offset = 1190;
    private static int y_offset = 615;
    private static int wight = 120;
    private static int heigth = 30;
    private static int threshold1 = 200;
    private static int threshold2 = 22;
    private static int black = 0;
    private static int white = 16777215;
    private static int charWidth = 11;
    private static int charHeight = 30;
    private static int charNum = 6;
    private static int offset_x = 19;
    private static int offset_y = 0;
    private static int gap_x = charWidth;
    private static int patternNum = 10;
    private static int target_wight = 112;
    private static int target_heigth = 33;
    private static int target_x = 0;
    private static int target_y = 0;
    private static int binaryImageInfo[][];
    private static Boolean bNoiseWipe = true;
    private static Boolean bSaveResult = false;
    private static Boolean bPrint = false;
    private static Boolean bAdust = false;
    private static Properties pro = null;

    private PicCapture() {
        standardImageMap = new HashMap<String, BufferedImage>();
        try {
            robot = new Robot();
        } catch (AWTException e) {
            System.err.println((new StringBuilder()).append("Internal Error: ")
                    .append(e).toString());
        }

        try {
            pro = new Properties();
            FileInputStream inputFile = new FileInputStream("conf\\properties");
            pro.load(inputFile);
            inputFile.close();
        } catch (FileNotFoundException e) {
            LogMsg("The properties file not found!");
            e.printStackTrace();
        } catch (IOException e) {
            LogMsg("Fail to load the properties file!");
            e.printStackTrace();
        }
    }

    public static BufferedImage capture(Rectangle rect)
            throws IOException {
        pickedImage = robot.createScreenCapture(rect);
        
        saveImage(pickedImage, "logs\\pickedImage1.bmp");
        return pickedImage;
    }

    public static BufferedImage binarilizeIamge()
            throws IOException {
        int green = 0;
        int red = 0;
        int blue = 0;
        int value = 0;
        Object data = null;

        binaryImageInfo = new int[wight][heigth];
        for (int i = 0; i < wight; i++) {
            for (int j = 0; j < heigth; j++) {
                data = pickedImage.getRaster().getDataElements(i, j, null);
                red = pickedImage.getColorModel().getRed(data);
                blue = pickedImage.getColorModel().getBlue(data);
                green = pickedImage.getColorModel().getGreen(data);
                value = (red * 3 + green * 6 + blue * 1) / 10;
                if (value > threshold1) {
                    value = white;
                    binaryImageInfo[i][j] = 1;
                } else {
                    value = black;
                    binaryImageInfo[i][j] = 0;
                }
                pickedImage.setRGB(i, j, value);
            }
        }

        saveImage(pickedImage, "logs\\pickedImage2.bmp");
        return pickedImage;
    }

    public static BufferedImage getCharImage(int index) {
        return (BufferedImage)charImageList.get(index);
    }

    public static BufferedImage getPicture()
            throws IOException {
        int green = 0;
        int red = 0;
        int blue = 0;
        int rgb;
        int xl = 0;
        int xr = 0;
        int value_l = 0;
        int value_r = 0;
        Object data = null;

        if (handledImage == null) {
            LogMsg("No target found! Exiting...");
            return null;
        }
        for (int n = 0; n < x_offsets.size() - 1; n++) {
            xl = x_offsets.get(n);
            xr = x_offsets.get(n + 1);
            value_l = 0;
            value_r = 0;
            while (xr - xl >= charWidth) {
                for (int j = 0; j < charHeight; j++) {
                    value_l += binaryImageInfo[xl + x_offset][j + y_offset];
                    value_r += binaryImageInfo[xr + x_offset][j + y_offset];
                }
                if (value_l > value_r) {
                    xl--;
                } else {
                    xr--;
                }
            }
            
            BufferedImage charImage = new BufferedImage(charWidth, charHeight,
                    handledImage.getType());
            for (int i = 0; i < charWidth; i++) {
                for (int j = 0; j < charHeight; j++) {
                    data = handledImage.getRaster().getDataElements(xl + i, j, null);
                    red = handledImage.getColorModel().getRed(data);
                    blue = handledImage.getColorModel().getBlue(data);
                    green = handledImage.getColorModel().getGreen(data);
                    rgb = (red * 3 + green * 6 + blue * 1) / 10;
                    if (rgb > threshold1) {
                        rgb = white;
                    } else {
                        rgb = black;
                    }
                    charImage.setRGB(i, j, rgb);
                }
            }
            if (bNoiseWipe) {
                charImageList.add(handlePic(charImage));
            } else {
                charImageList.add(charImage);
            }
            saveImage(charImage, "logs\\charImage_" + n + ".bmp");
        }

        return handledImage;
    }

    public static int getChar(int index)
            throws IOException {
        class Score{
            char id;
            int value; 
        }
        List <Score> scoreList = new ArrayList<Score>();

        if (null != charImageList) {
            for (int n = 0; n < patternNum; n++) {
                BufferedImage charImage = getCharImage(index);
                Score score = new Score();
                score.id = (char)n;
                score.value = getDiffValues(charImage, standardImageMap.get(String.valueOf(n)));
                if (bPrint) {
                    LogMsg("Pattern[" + n + "] score is " + score.value);
                }
                scoreList.add(score);
            }
        }

        Collections.sort(scoreList, new Comparator<Score>() {
            public int compare(Score s1, Score s2) {
                return s1.value - s2.value;
            }
        });

        return scoreList.get(0).id;
    }

    public static void getOffsets() {
        int temp;
        int value = 0;
        class Score{
            int id;
            int value; 
        }
        List <Score> scoreList = new ArrayList<Score>();

        for (int i = 1; i < target_wight; i++) {
            value = 0;
            for (int j = 0; j < target_heigth; j++) {
                value += binaryImageInfo[i + x_offset][j + y_offset];
            }
            if ((i > offset_x) && (i < target_wight - offset_x) && value > 22 ) {
                Score score = new Score();
                score.id = i;
                score.value = value; 
                scoreList.add(score);
            }
        }

        Collections.sort(scoreList, new Comparator<Score>() {
            public int compare(Score s1, Score s2) {
                return s1.value - s2.value;
            }
        });

        for (int n = scoreList.size() - 1; n >= 0; n--) {
            if (((n - 1) >= 0)
                    && (scoreList.get(n).id == (scoreList.get(n - 1).id + 1))
                    && (scoreList.get(n).value == scoreList.get(n - 1).value)) {
                continue;
            } else {
                Boolean bInsert = true;
                
                for (int x = 0; x < x_offsets.size(); x++) {
                    if (((x_offsets.get(x) >= scoreList.get(n).id)
                                && (x_offsets.get(x) - scoreList.get(n).id < charWidth ))
                            || ((x_offsets.get(x) < scoreList.get(n).id)
                                    && (scoreList.get(n).id - x_offsets.get(x) < charWidth))) {
                            bInsert = false;
                            break;
                        }
                }

                if (bInsert) {
                    x_offsets.add(scoreList.get(n).id);
                    LogMsg("X is " + scoreList.get(n).id + " Value is " + scoreList.get(n).value + " Size is " + x_offsets.size());
                    if (x_offsets.size() == charNum) {
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < x_offsets.size(); i++) {
            for(int j = i; j > 0; j--){
                if (x_offsets.get(j) < x_offsets.get(j - 1)){
                    temp = x_offsets.get(j - 1);
                    x_offsets.set(j - 1, x_offsets.get(j));
                    x_offsets.set(j, temp);
                } else {
                    break;
                }
            }
        }
        
        for (int n = 0; n < x_offsets.size(); n++) {
            LogMsg("X is " + x_offsets.get(n) + " Size is " + x_offsets.size());
        }
    }

    public static BufferedImage handlePic(BufferedImage iputImage) throws IOException {
        BufferedImage outImage;
        String noistType = getNoiseType(pickedImage);
        if("CrossLine".equals(noistType)){
            List<Integer> noiseList1 = NoiseHandleFactory.getBaseNoiseHandlerFactory()
            .getNoiseHandle(NoisePattern.TRANSVERSE.value).getNoiseLines(iputImage);
            BufferedImage firstResBi = NoiseHandleFactory.getBaseNoiseHandlerFactory()
                    .getNoiseHandle(NoisePattern.TRANSVERSE.value).removeNoise(iputImage);
            List<Integer> noiseList2 = NoiseHandleFactory.getBaseNoiseHandlerFactory()
            .getNoiseHandle(NoisePattern.VERTICAL.value).getNoiseLines(firstResBi);
            BufferedImage secResBi =  NoiseHandleFactory.getBaseNoiseHandlerFactory()
                    .getNoiseHandle(NoisePattern.VERTICAL.value).removeNoise(firstResBi);
            BaseNoiseHandle bn = NoiseHandleFactory.getBaseNoiseHandlerFactory()
                    .getNoiseHandle(NoisePattern.CROSS.value);
            bn.setTransverseNoiseList(noiseList1);
            bn.setVerticalNoiseList(noiseList2);
            outImage = bn.removeNoise(secResBi);
        }else{
            outImage = NoiseHandleFactory.getBaseNoiseHandlerFactory()
                .getNoiseHandle(getNoiseType(iputImage)).removeNoise(iputImage);
        }
        
        return outImage;
    }

    public static void saveImage(BufferedImage image, String imagePath)
            throws IOException {
        if (bSaveResult) {
            File file = new File(imagePath);
            String suffix = imagePath.substring(imagePath.lastIndexOf('.') + 1);
            ImageIO.write(image, suffix, file);
        }
    }

    public static BufferedImage loadImage(String imagePath)
            throws IOException {
        File file = new File(imagePath);
        return (BufferedImage)ImageIO.read(file);
    }

    public static void loadPattern() throws IOException {
        for (int n = 0; n < 10; n++) {
            File inputFile = new File("conf\\char_" + n + ".bmp");
            BufferedImage charImage = ImageIO.read(inputFile);
            standardImageMap.put(String.valueOf(n), charImage);
        }
    }

    public static void locateTarget() throws IOException {
        int green = 0;
        int red = 0;
        int blue = 0;
        int value = 0;
        Boolean bFound = false;
        Object data = null;

        targetImage = new BufferedImage(target_wight, target_heigth, pickedImage.getType());
        for (int j = 0; j < heigth - target_heigth; j++) {
            for (int i = 0; i < wight - target_wight; i++) {
                bFound = true;
                for (int n = 0; n < target_wight; n++ ) {
                    if ((binaryImageInfo[i + n][j] != 0)) {
                        bFound = false;
                        break;
                    }
                }

                for (int n = 0; n < target_heigth; n++ ) {
                    if ((binaryImageInfo[i][j + n] != 0)) {
                        bFound = false;
                        break;
                    }
                }

                if (bFound) {
                    target_x = i;
                    target_y = j;

                    for (int x = targetImage.getMinX(); x < target_wight; x++) {
                        for (int y = targetImage.getMinY(); y < target_heigth; y++) {
                            data = pickedImage.getRaster().getDataElements(target_x + x, target_y + y, null);
                            red = pickedImage.getColorModel().getRed(data);
                            blue = pickedImage.getColorModel().getBlue(data);
                            green = pickedImage.getColorModel().getGreen(data);
                            value = (red * 3 + green * 6 + blue * 1) / 10;
                            if (value > threshold1) {
                                value = white;
                            } else {
                                value = black;
                            }
                            targetImage.setRGB(x, y, value);
                        }
                    }
                    if (bNoiseWipe) {
                        handledImage = handlePic(targetImage);
                    } else {
                        handledImage = targetImage;
                    }
                    x_offset = i;
                    y_offset = j;
                    saveImage(handledImage, "logs\\handledImage.bmp");
                    LogMsg("X is " + x_offset + " Y is " + y_offset);
                    return;
                }
            }
        }
    }

    public static int getDiffValues(BufferedImage inputBi, BufferedImage standardBi)
            throws IOException {
        int diffValues = 0;

        for (int i = 0; i < charWidth; i++) {
            for (int j = 0; j < charHeight; j++) {
                if (inputBi.getRGB(i, j) != standardBi.getRGB(i, j)) {
                    diffValues++;
                }
            }
        }
        return diffValues;
    }

    private static String getNoiseType(BufferedImage bi){
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

        if(transverseNoiseList.size()>1 && verticalNoiseList.size()>1){
            return NoisePattern.CROSS.value;
        }
        if(transverseNoiseList.size()>1){
            return NoisePattern.TRANSVERSE.value;
        }
        if(verticalNoiseList.size()>1){
            return NoisePattern.VERTICAL.value;
        }
        return NoisePattern.DIAGONAL.value;
    }

    private static String getProperty(String key)
            throws IOException {
        String value = pro.getProperty(key);
        if (bPrint) {
            LogMsg("The value of variable " + key + " is " + value);
        }

        return value;
    }

    private static void loadProperties()
            throws IOException {
        x_offset = Integer.valueOf(getProperty("x_offset"));
        y_offset = Integer.valueOf(getProperty("y_offset"));
        wight = Integer.valueOf(getProperty("wight"));
        heigth = Integer.valueOf(getProperty("heigth"));
        target_wight = Integer.valueOf(getProperty("target_wight"));
        target_heigth = Integer.valueOf(getProperty("target_heigth"));
        threshold1 = Integer.valueOf(getProperty("threshold1"));
        threshold2 = Integer.valueOf(getProperty("threshold2"));
        black = Integer.valueOf(getProperty("black"));
        white = Integer.valueOf(getProperty("white"));
        charWidth = Integer.valueOf(getProperty("charWidth"));
        charHeight = Integer.valueOf(getProperty("charHeight"));
        charNum = Integer.valueOf(getProperty("charNum"));
        offset_x = Integer.valueOf(getProperty("offset_x"));
        offset_y = Integer.valueOf(getProperty("offset_y"));
        patternNum = Integer.valueOf(getProperty("patternNum"));
        bNoiseWipe = Boolean.valueOf(getProperty("bNoiseWipe"));
        bSaveResult = Boolean.valueOf(getProperty("bSaveResult"));
        bPrint = Boolean.valueOf(getProperty("bPrint"));
        bAdust = Boolean.valueOf(getProperty("bAdust"));
    }

    private static void adjustProperties() {
        if (bAdust) {
            int screenWidth = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
            int screenHeigth = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
            
            LogMsg("The screen size is " + screenWidth + " * " + screenHeigth);
            if (x_offset < 0) {
                x_offset = screenWidth / 2;
            }

            if (y_offset < 0) {
                y_offset = screenHeigth / 2;
            }
        }
    }

    private static void LogMsg(String msg) {
        if (bPrint) {
            System.out.println(msg);
        }
    }

    public static void main(String args[]) throws Exception {
        @SuppressWarnings("unused")
		PicCapture capture = new PicCapture();
        loadProperties();
        adjustProperties();

        Rectangle rect = new Rectangle(x_offset, y_offset, wight, heigth);
        long startTime = System.currentTimeMillis();
        capture(rect);
        binarilizeIamge();
        locateTarget();
        getOffsets();

        loadPattern();

        if (getPicture() != null) {
	        for (int i = 0; i < 6; i++) {
	            //LogMsg("The charactar[" + i + "] is " + getChar(i));
	            LogMsg("Hehe");
	        }
        }
        long consumedTime = System.currentTimeMillis() - startTime;
        LogMsg("Consumed Time is " + consumedTime);
    }
}
