package top.itart.agile.common.util;

import java.math.BigDecimal;

public class Arith {
    private static final int DEF_DIV_SCALE = 10;

    private Arith() {
    }

    public static BigDecimal add(BigDecimal b1, BigDecimal b2) {
        b1 = (b1 == null? new BigDecimal(0): b1);
        b2 = (b2 == null? new BigDecimal(0): b2);
        return b1.add(b2);
    }

    public static BigDecimal add(double d1, double d2) {
        return add(new BigDecimal(d1), new BigDecimal(d2));
    }

    public static BigDecimal add(BigDecimal b1, double d2) {
        return add(b1, new BigDecimal(d2));
    }

    public static BigDecimal add(double d1, BigDecimal b2) {
        return add(new BigDecimal(d1), b2);
    }

    public static BigDecimal sub(BigDecimal b1, BigDecimal b2) {
        b1 = (b1 == null? new BigDecimal(0): b1);
        b2 = (b2 == null? new BigDecimal(0): b2);
        return b1.subtract(b2);
    }

    public static BigDecimal sub(double d1, double d2) {
        return sub(new BigDecimal(d1), new BigDecimal(d2));
    }

    public static BigDecimal sub(double d1, BigDecimal b2) {
        return sub(new BigDecimal(d1), b2);
    }

    public static BigDecimal sub(BigDecimal b1, double d2) {
        return sub(b1, new BigDecimal(d2));
    }

    public static BigDecimal mul(BigDecimal b1, BigDecimal b2) {
        b1 = (b1 == null? new BigDecimal(0): b1);
        b2 = (b2 == null? new BigDecimal(0): b2);
        
        return b1.multiply(b2);
    }

    public static BigDecimal mul(BigDecimal b1, double d2) {
        return mul(b1, new BigDecimal(d2));
    }

    public static BigDecimal mul(double d1, BigDecimal b2) {
        return mul(new BigDecimal(d1), b2);
    }

    public static BigDecimal mul(double d1, double d2) {
        return mul(new BigDecimal(d1), new BigDecimal(d2));
    }

    public static BigDecimal div(double v1, double v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    public static BigDecimal div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(v);
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP);
    }
    
    public static BigDecimal round(float v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(v);
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP);
    }
}
