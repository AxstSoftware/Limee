package io.github.axst.api.utils;

public class MathUtil {

    public static final double[] a;
    private static final double[] b;

    static {
        a = new double[65536];
        b = new double[360];
        for (int i = 0; i < 65536; ++i) {
            MathUtil.a[i] = Math.sin(i * 3.141592653589793 * 2.0 / 65536.0);
        }
        for (int i = 0; i < 360; ++i) {
            MathUtil.b[i] = Math.sin(Math.toRadians(i));
        }
    }

    public static double getAngle(int nameInt) {
        nameInt %= 360;
        return MathUtil.b[nameInt];
    }

    public static double getRightAngle(int nameInt) {
        nameInt += 90;
        nameInt %= 360;
        return MathUtil.b[nameInt];
    }

}