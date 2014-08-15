package com.xs.image;

import java.awt.image.BufferedImage;
import java.util.List;

public interface BaseNoiseHandle {
    public abstract BufferedImage removeNoise(BufferedImage bi);
    public abstract List<Integer> getNoiseLines(BufferedImage bi);
    public void setTransverseNoiseList(List<Integer> transverseNoiseList);
    public void setVerticalNoiseList(List<Integer> verticalNoiseList);
}
