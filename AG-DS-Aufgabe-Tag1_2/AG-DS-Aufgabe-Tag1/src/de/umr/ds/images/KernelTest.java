package de.umr.ds.images;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KernelTest {
    @Test
    public void test1(){
        double[][] d = new double[3][3];
        d[0][0] = 1;
        d[0][2] = 1;
        d[1][1] = 1;
        d[2][2] = 1;
        d[2][0] = 1;
        Kernel k = new Kernel(d);
        int[][] i = new int[7][7];
        i[0][3] = 1;
        i[1][3] = 1;
        i[1][4] = 1;
        i[2][3] = 1;
        i[2][4] = 1;
        i[2][5] = 1;
        int[][] test = k.convolve(i);
        assertEquals(4, test[0][3]);





    }




}