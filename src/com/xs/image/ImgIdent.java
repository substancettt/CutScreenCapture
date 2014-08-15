package com.xs.image;

//~--- JDK imports ------------------------------------------------------------

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import java.awt.*;
import java.awt.image.*;

import java.io.*;

import java.net.*;

import javax.imageio.*;

 
public class ImgIdent {

    // 
    private final long[][] NUMERIC = {
        { 512104545, 562436190 },    // '0'
        { 148931080, 136348222 },    // '1'
        { 511971394, 69273663 },     // '2'
        { 511971406, 17045598 },     // '3'
        { 35168914, 586948743 },     // '4'
        { 1065486398, 17045598 },    // '5'
        { 239208494, 830871646 },    // '6'
        { 1065623684, 69239824 },    // '7'
        { 512104542, 562436190 },    // '8'
        { 512104547, 486805660 }     // '9'
    };                               

    // 
    private int intCharHeight = 10;

    // 
    private int intCharSpaceH = 5;

    // 
    private int intCharSpaceY = 1;

    // 
    private int           intCharWidth = 5;
    private int           IntImgHeight;
    private BufferedImage img;
    private int           intBgColor;
    private int           intCharColor;
    private int           intImgWith;
    private int           intMaxX;
    private int           intMaxY;
    private int           intMinX;
    private int           intMinY;

    // 
    private Point  pOrigin;
    private String strNum;

    
    public ImgIdent(BufferedImage img) throws IOException {
        this.img = img;
        init();
    }

    
    public ImgIdent(File file) throws IOException {
        img = ImageIO.read(file);
        init();
    }

    
    public ImgIdent(URL url) throws IOException {
        img = ImageIO.read(url);
        init();
    }

    
    private void init() {

        // 
        intImgWith   = img.getWidth();
        IntImgHeight = img.getHeight();

        // 
        intBgColor = img.getRGB(7, 4);

        // System.out.println(intBgColor);

        // 
        pOrigin = new Point(0, 0);
    }

    
    private void getBaseInfo() {
        System.out.println(intBgColor + "|" + intCharColor);
        System.out.println(intMinX + "|" + intMinY + "|" + intMaxX + "|" + intMaxY);
    }

    
    private Point[] getCharRange(int intNo) {

        // 
        Point pTopLeft     = new Point(0, 0);
        Point pBottomRight = new Point(0, 0);

        // 
        pTopLeft.x = pOrigin.x + intCharWidth * (intNo - 1) + intCharSpaceH * (intNo - 1);
        pTopLeft.y = pOrigin.y;

        // 
        pBottomRight.x = 1 + pOrigin.x + intCharWidth * intNo + intCharSpaceH * (intNo - 1) - 1;
        pBottomRight.y = pOrigin.y + intCharHeight - 1;

        return new Point[] { pTopLeft, pBottomRight };
    }

    
    private char getBit(int x, int y) {
        int intCurtColor;

        intCurtColor = img.getRGB(x, y);

        //System.out.println("[" + x + "," + y + "]" + intCurtColor + "==" + intBgColor + "==>" + (Math.abs(intCurtColor) >7308252));
//      return (Math.abs(intCurtColor) >= 5689325)
//               '0'
//              : '1';
        return (intCurtColor == intBgColor)?'0' : '1';

        // 5689325    6008535
    }

    
    private String getCharString(int intNo) {

        // 
        Point[] p            = getCharRange(intNo);
        Point   pTopLeft     = p[0];
        Point   pBottomRight = p[1];

        // 
        int intX1, intY1, intX2, intY2;

        intX1 = pTopLeft.x;
        intY1 = pTopLeft.y;
        intX2 = pBottomRight.x;
        intY2 = pBottomRight.y;

//      System.out.println("intX1=" + intX1);
//      System.out.println("intY1=" + intY1);
//      System.out.println("intX2=" + intX2);
//      System.out.println("intY2=" + intY2);

        // 
        int    i, j;
        String strChar = "";

        for (i = intY1; i <= intY2; i++) {
            for (j = intX1; j <= intX2; j++) {
                System.out.print(getBit(j, i));
                strChar = strChar + getBit(j, i);
            }

            System.out.println();
        }

        System.out.println();

        return strChar;
    }

    
    public int getNum(int intNo) {

        // 
        String strChar = getCharString(intNo);

        // System.out.println(intNo+"=="+strChar);
        // 
        String strCharHigh = strChar.substring(0, strChar.length() / 2);
        String strCharLow  = strChar.substring(strChar.length() / 2);

        // 
        long lCharHigh = Long.parseLong(strCharHigh, 2);

        System.out.println(lCharHigh);

        long lCharLow = Long.parseLong(strCharLow, 2);

        System.out.println(lCharLow);

        // 
        int intNum = '*';

        for (int i = 0; i <= 9; i++) {
            if ((lCharHigh == NUMERIC[i][0]) && (lCharLow == NUMERIC[i][1])) {
                intNum = i;

                break;
            } else {
                if ((lCharHigh == 834533329) && (lCharLow == 242870177)) {
                    intNum = 6;
                }    // 834533329 242870177
                        else {
                    intNum = 1;
                }    // 1       937393609  937393601
            }
        }

        return intNum;
    }

    
    public String getValidatecode(int length) {
        String strNum = "";

        for (int i = 1; i <= length; i++) {
            synchronized (this) {
                strNum += String.valueOf(getNum(i));
            }
        }

        return strNum;
    }

    
    public void saveJPEG(BufferedImage iag, String savePath) throws FileNotFoundException, IOException {
        OutputStream     jos     = new FileOutputStream(savePath);
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(jos);
        JPEGEncodeParam  jpegEP  = JPEGCodec.getDefaultJPEGEncodeParam(iag);

        jpegEP.setQuality((float) 1, true);
        encoder.encode(iag, jpegEP);
        jos.flush();
        jos.close();
    }
    
    
//    public BufferedImage loadJPEG(File file) throws ImageFormatException, IOException{
//        FileInputStream fio = new FileInputStream(file);
//        JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(fio);
//        return decoder.decodeAsBufferedImage();        
//    }
    
    
    public static void main(String[] args) throws IOException{
        File f = new File("C:\\Users\\lixu1\\work\\New folder\\char7.jpg");
        ImgIdent imgIdent = new ImgIdent(f);
        String num = imgIdent.getValidatecode(1);
        System.out.println(num);
    }
}
