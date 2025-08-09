package org.jline.utils;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Colors {
   public static final int[] DEFAULT_COLORS_256 = new int[]{0, 8388608, 32768, 8421376, 128, 8388736, 32896, 12632256, 8421504, 16711680, 65280, 16776960, 255, 16711935, 65535, 16777215, 0, 95, 135, 175, 215, 255, 24320, 24415, 24455, 24495, 24535, 24575, 34560, 34655, 34695, 34735, 34775, 34815, 44800, 44895, 44935, 44975, 45015, 45055, 55040, 55135, 55175, 55215, 55255, 55295, 65280, 65375, 65415, 65455, 65495, 65535, 6225920, 6226015, 6226055, 6226095, 6226135, 6226175, 6250240, 6250335, 6250375, 6250415, 6250455, 6250495, 6260480, 6260575, 6260615, 6260655, 6260695, 6260735, 6270720, 6270815, 6270855, 6270895, 6270935, 6270975, 6280960, 6281055, 6281095, 6281135, 6281175, 6281215, 6291200, 6291295, 6291335, 6291375, 6291415, 6291455, 8847360, 8847455, 8847495, 8847535, 8847575, 8847615, 8871680, 8871775, 8871815, 8871855, 8871895, 8871935, 8881920, 8882015, 8882055, 8882095, 8882135, 8882175, 8892160, 8892255, 8892295, 8892335, 8892375, 8892415, 8902400, 8902495, 8902535, 8902575, 8902615, 8902655, 8912640, 8912735, 8912775, 8912815, 8912855, 8912895, 11468800, 11468895, 11468935, 11468975, 11469015, 11469055, 11493120, 11493215, 11493255, 11493295, 11493335, 11493375, 11503360, 11503455, 11503495, 11503535, 11503575, 11503615, 11513600, 11513695, 11513735, 11513775, 11513815, 11513855, 11523840, 11523935, 11523975, 11524015, 11524055, 11524095, 11534080, 11534175, 11534215, 11534255, 11534295, 11534335, 14090240, 14090335, 14090375, 14090415, 14090455, 14090495, 14114560, 14114655, 14114695, 14114735, 14114775, 14114815, 14124800, 14124895, 14124935, 14124975, 14125015, 14125055, 14135040, 14135135, 14135175, 14135215, 14135255, 14135295, 14145280, 14145375, 14145415, 14145455, 14145495, 14145535, 14155520, 14155615, 14155655, 14155695, 14155735, 14155775, 16711680, 16711775, 16711815, 16711855, 16711895, 16711935, 16736000, 16736095, 16736135, 16736175, 16736215, 16736255, 16746240, 16746335, 16746375, 16746415, 16746455, 16746495, 16756480, 16756575, 16756615, 16756655, 16756695, 16756735, 16766720, 16766815, 16766855, 16766895, 16766935, 16766975, 16776960, 16777055, 16777095, 16777135, 16777175, 16777215, 526344, 1184274, 1842204, 2500134, 3158064, 3815994, 4473924, 5131854, 5789784, 6447714, 7105644, 7763574, 8421504, 9079434, 9737364, 10395294, 11053224, 11711154, 12369084, 13027014, 13684944, 14342874, 15000804, 15658734};
   public static final int[] DEFAULT_COLORS_88 = new int[]{0, 8388608, 32768, 8421376, 128, 8388736, 32896, 12632256, 8421504, 16711680, 65280, 16776960, 255, 16711935, 65535, 16777215, 0, 139, 205, 255, 35584, 35723, 35789, 35839, 52480, 52619, 52685, 52735, 65280, 65419, 65485, 65535, 9109504, 9109643, 9109709, 9109759, 9145088, 9145227, 9145293, 9145343, 9161984, 9162123, 9162189, 9162239, 9174784, 9174923, 9174989, 9175039, 13434880, 13435019, 13435085, 13435135, 13470464, 13470603, 13470669, 13470719, 13487360, 13487499, 13487565, 13487615, 13500160, 13500299, 13500365, 13500415, 16711680, 16711819, 16711885, 16711935, 16747264, 16747403, 16747469, 16747519, 16764160, 16764299, 16764365, 16764415, 16776960, 16777099, 16777165, 16777215, 3026478, 6052956, 7566195, 9145227, 10658466, 12171705, 13684944, 15198183};
   public static final double[] D50 = new double[]{(double)96.422F, (double)100.0F, (double)82.521F};
   public static final double[] D65 = new double[]{95.047, (double)100.0F, 108.883};
   public static final double[] averageSurrounding = new double[]{(double)1.0F, 0.69, (double)1.0F};
   public static final double[] dimSurrounding = new double[]{0.9, 0.59, 0.9};
   public static final double[] darkSurrounding = new double[]{0.8, 0.525, 0.8};
   public static final double[] sRGB_encoding_environment;
   public static final double[] sRGB_typical_environment;
   public static final double[] AdobeRGB_environment;
   private static int[] COLORS_256;
   private static Map COLOR_NAMES;
   private static final int L = 0;
   private static final int A = 1;
   private static final int B = 2;
   private static final int X = 0;
   private static final int Y = 1;
   private static final int Z = 2;
   private static final double kl = (double)2.0F;
   private static final double kc = (double)1.0F;
   private static final double kh = (double)1.0F;
   private static final double k1 = 0.045;
   private static final double k2 = 0.015;
   public static final int J = 0;
   public static final int Q = 1;
   public static final int C = 2;
   public static final int M = 3;
   public static final int s = 4;
   public static final int H = 5;
   public static final int h = 6;
   static final int SUR_F = 0;
   static final int SUR_C = 1;
   static final int SUR_N_C = 2;
   static final int VC_X_W = 0;
   static final int VC_Y_W = 1;
   static final int VC_Z_W = 2;
   static final int VC_L_A = 3;
   static final int VC_Y_B = 4;
   static final int VC_F = 5;
   static final int VC_C = 6;
   static final int VC_N_C = 7;
   static final int VC_Z = 8;
   static final int VC_N = 9;
   static final int VC_N_BB = 10;
   static final int VC_N_CB = 11;
   static final int VC_A_W = 12;
   static final int VC_F_L = 13;
   static final int VC_D_RGB_R = 14;
   static final int VC_D_RGB_G = 15;
   static final int VC_D_RGB_B = 16;
   private static final double epsilon = 0.008856451679035631;
   private static final double kappa = 903.2962962962963;

   public static void setRgbColors(int[] colors) {
      if (colors != null && colors.length == 256) {
         COLORS_256 = colors;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public static int rgbColor(int col) {
      return COLORS_256[col];
   }

   public static Integer rgbColor(String name) {
      if (COLOR_NAMES == null) {
         Map<String, Integer> colors = new LinkedHashMap();

         try {
            InputStream is = InfoCmp.class.getResourceAsStream("colors.txt");

            try {
               BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

               try {
                  br.lines().map(String::trim).filter((s) -> !s.startsWith("#")).filter((s) -> !s.isEmpty()).forEachOrdered((s) -> colors.put(s, colors.size()));
                  COLOR_NAMES = colors;
               } catch (Throwable var8) {
                  try {
                     br.close();
                  } catch (Throwable var7) {
                     var8.addSuppressed(var7);
                  }

                  throw var8;
               }

               br.close();
            } catch (Throwable var9) {
               if (is != null) {
                  try {
                     is.close();
                  } catch (Throwable var6) {
                     var9.addSuppressed(var6);
                  }
               }

               throw var9;
            }

            if (is != null) {
               is.close();
            }
         } catch (IOException e) {
            throw new IOError(e);
         }
      }

      return (Integer)COLOR_NAMES.get(name);
   }

   public static int roundColor(int col, int max) {
      return roundColor(col, max, (String)null);
   }

   public static int roundColor(int col, int max, String dist) {
      if (col >= max) {
         int c = COLORS_256[col];
         col = roundColor(c, COLORS_256, max, dist);
      }

      return col;
   }

   public static int roundRgbColor(int r, int g, int b, int max) {
      return roundColor((r << 16) + (g << 8) + b, COLORS_256, max, (String)null);
   }

   static int roundColor(int color, int[] colors, int max, String dist) {
      return roundColor(color, colors, max, getDistance(dist));
   }

   static int roundColor(int color, int[] colors, int max, Distance distance) {
      double best_distance = (double)Integer.MAX_VALUE;
      int best_index = Integer.MAX_VALUE;

      for(int idx = 0; idx < max; ++idx) {
         double d = distance.compute(color, colors[idx]);
         if (d <= best_distance) {
            best_index = idx;
            best_distance = d;
         }
      }

      return best_index;
   }

   static Distance getDistance(String dist) {
      if (dist == null) {
         dist = System.getProperty("org.jline.utils.colorDistance", "cie76");
      }

      return new NamedDistance(dist, doGetDistance(dist));
   }

   private static Distance doGetDistance(String dist) {
      if (dist.equals("rgb")) {
         return (p1, p2) -> {
            double[] c1 = rgb(p1);
            double[] c2 = rgb(p2);
            double rmean = (c1[0] + c2[0]) / (double)2.0F;
            double[] w = new double[]{(double)2.0F + rmean, (double)4.0F, (double)3.0F - rmean};
            return scalar(c1, c2, w);
         };
      } else if (dist.matches("rgb\\(([0-9]+(\\.[0-9]+)?),([0-9]+(\\.[0-9]+)?),([0-9]+(\\.[0-9]+)?)\\)")) {
         return (p1, p2) -> scalar(rgb(p1), rgb(p2), getWeights(dist));
      } else if (!dist.equals("lab") && !dist.equals("cie76")) {
         if (dist.matches("lab\\(([0-9]+(\\.[0-9]+)?),([0-9]+(\\.[0-9]+)?)\\)")) {
            double[] w = getWeights(dist);
            return (p1, p2) -> scalar(rgb2cielab(p1), rgb2cielab(p2), new double[]{w[0], w[1], w[1]});
         } else if (dist.equals("cie94")) {
            return (p1, p2) -> cie94(rgb2cielab(p1), rgb2cielab(p2));
         } else if (!dist.equals("cie00") && !dist.equals("cie2000")) {
            if (dist.equals("cam02")) {
               return (p1, p2) -> cam02(p1, p2, sRGB_typical_environment);
            } else if (dist.equals("camlab")) {
               return (p1, p2) -> {
                  double[] c1 = camlab(p1, sRGB_typical_environment);
                  double[] c2 = camlab(p2, sRGB_typical_environment);
                  return scalar(c1, c2);
               };
            } else if (dist.matches("camlab\\(([0-9]+(\\.[0-9]+)?),([0-9]+(\\.[0-9]+)?)\\)")) {
               return (p1, p2) -> {
                  double[] c1 = camlab(p1, sRGB_typical_environment);
                  double[] c2 = camlab(p2, sRGB_typical_environment);
                  double[] w = getWeights(dist);
                  return scalar(c1, c2, new double[]{w[0], w[1], w[1]});
               };
            } else if (dist.matches("camlch")) {
               return (p1, p2) -> {
                  double[] c1 = camlch(p1, sRGB_typical_environment);
                  double[] c2 = camlch(p2, sRGB_typical_environment);
                  return camlch(c1, c2);
               };
            } else if (dist.matches("camlch\\(([0-9]+(\\.[0-9]+)?),([0-9]+(\\.[0-9]+)?),([0-9]+(\\.[0-9]+)?)\\)")) {
               return (p1, p2) -> {
                  double[] c1 = camlch(p1, sRGB_typical_environment);
                  double[] c2 = camlch(p2, sRGB_typical_environment);
                  double[] w = getWeights(dist);
                  return camlch(c1, c2, w);
               };
            } else {
               throw new IllegalArgumentException("Unsupported distance function: " + dist);
            }
         } else {
            return (p1, p2) -> cie00(rgb2cielab(p1), rgb2cielab(p2));
         }
      } else {
         return (p1, p2) -> scalar(rgb2cielab(p1), rgb2cielab(p2));
      }
   }

   private static double[] getWeights(String dist) {
      String[] weights = dist.substring(dist.indexOf(40) + 1, dist.length() - 1).split(",");
      return Stream.of(weights).mapToDouble(Double::parseDouble).toArray();
   }

   private static double scalar(double[] c1, double[] c2, double[] w) {
      return sqr((c1[0] - c2[0]) * w[0]) + sqr((c1[1] - c2[1]) * w[1]) + sqr((c1[2] - c2[2]) * w[2]);
   }

   private static double scalar(double[] c1, double[] c2) {
      return sqr(c1[0] - c2[0]) + sqr(c1[1] - c2[1]) + sqr(c1[2] - c2[2]);
   }

   private static double cie94(double[] lab1, double[] lab2) {
      double dl = lab1[0] - lab2[0];
      double da = lab1[1] - lab2[1];
      double db = lab1[2] - lab2[2];
      double c1 = Math.sqrt(lab1[1] * lab1[1] + lab1[2] * lab1[2]);
      double c2 = Math.sqrt(lab2[1] * lab2[1] + lab2[2] * lab2[2]);
      double dc = c1 - c2;
      double dh = da * da + db * db - dc * dc;
      dh = dh < (double)0.0F ? (double)0.0F : Math.sqrt(dh);
      double sl = (double)1.0F;
      double sc = (double)1.0F + 0.045 * c1;
      double sh = (double)1.0F + 0.015 * c1;
      double dLKlsl = dl / ((double)2.0F * sl);
      double dCkcsc = dc / ((double)1.0F * sc);
      double dHkhsh = dh / ((double)1.0F * sh);
      return dLKlsl * dLKlsl + dCkcsc * dCkcsc + dHkhsh * dHkhsh;
   }

   private static double cie00(double[] lab1, double[] lab2) {
      double c_star_1_ab = Math.sqrt(lab1[1] * lab1[1] + lab1[2] * lab1[2]);
      double c_star_2_ab = Math.sqrt(lab2[1] * lab2[1] + lab2[2] * lab2[2]);
      double c_star_average_ab = (c_star_1_ab + c_star_2_ab) / (double)2.0F;
      double c_star_average_ab_pot_3 = c_star_average_ab * c_star_average_ab * c_star_average_ab;
      double c_star_average_ab_pot_7 = c_star_average_ab_pot_3 * c_star_average_ab_pot_3 * c_star_average_ab;
      double G = (double)0.5F * ((double)1.0F - Math.sqrt(c_star_average_ab_pot_7 / (c_star_average_ab_pot_7 + 6.103515625E9)));
      double a1_prime = ((double)1.0F + G) * lab1[1];
      double a2_prime = ((double)1.0F + G) * lab2[1];
      double C_prime_1 = Math.sqrt(a1_prime * a1_prime + lab1[2] * lab1[2]);
      double C_prime_2 = Math.sqrt(a2_prime * a2_prime + lab2[2] * lab2[2]);
      double h_prime_1 = (Math.toDegrees(Math.atan2(lab1[2], a1_prime)) + (double)360.0F) % (double)360.0F;
      double h_prime_2 = (Math.toDegrees(Math.atan2(lab2[2], a2_prime)) + (double)360.0F) % (double)360.0F;
      double delta_L_prime = lab2[0] - lab1[0];
      double delta_C_prime = C_prime_2 - C_prime_1;
      double h_bar = Math.abs(h_prime_1 - h_prime_2);
      double delta_h_prime;
      if (C_prime_1 * C_prime_2 == (double)0.0F) {
         delta_h_prime = (double)0.0F;
      } else if (h_bar <= (double)180.0F) {
         delta_h_prime = h_prime_2 - h_prime_1;
      } else if (h_prime_2 <= h_prime_1) {
         delta_h_prime = h_prime_2 - h_prime_1 + (double)360.0F;
      } else {
         delta_h_prime = h_prime_2 - h_prime_1 - (double)360.0F;
      }

      double delta_H_prime = (double)2.0F * Math.sqrt(C_prime_1 * C_prime_2) * Math.sin(Math.toRadians(delta_h_prime / (double)2.0F));
      double L_prime_average = (lab1[0] + lab2[0]) / (double)2.0F;
      double C_prime_average = (C_prime_1 + C_prime_2) / (double)2.0F;
      double h_prime_average;
      if (C_prime_1 * C_prime_2 == (double)0.0F) {
         h_prime_average = (double)0.0F;
      } else if (h_bar <= (double)180.0F) {
         h_prime_average = (h_prime_1 + h_prime_2) / (double)2.0F;
      } else if (h_prime_1 + h_prime_2 < (double)360.0F) {
         h_prime_average = (h_prime_1 + h_prime_2 + (double)360.0F) / (double)2.0F;
      } else {
         h_prime_average = (h_prime_1 + h_prime_2 - (double)360.0F) / (double)2.0F;
      }

      double L_prime_average_minus_50 = L_prime_average - (double)50.0F;
      double L_prime_average_minus_50_square = L_prime_average_minus_50 * L_prime_average_minus_50;
      double T = (double)1.0F - 0.17 * Math.cos(Math.toRadians(h_prime_average - (double)30.0F)) + 0.24 * Math.cos(Math.toRadians(h_prime_average * (double)2.0F)) + 0.32 * Math.cos(Math.toRadians(h_prime_average * (double)3.0F + (double)6.0F)) - 0.2 * Math.cos(Math.toRadians(h_prime_average * (double)4.0F - (double)63.0F));
      double S_L = (double)1.0F + 0.015 * L_prime_average_minus_50_square / Math.sqrt((double)20.0F + L_prime_average_minus_50_square);
      double S_C = (double)1.0F + 0.045 * C_prime_average;
      double S_H = (double)1.0F + 0.015 * T * C_prime_average;
      double h_prime_average_minus_275_div_25 = (h_prime_average - (double)275.0F) / (double)25.0F;
      double h_prime_average_minus_275_div_25_square = h_prime_average_minus_275_div_25 * h_prime_average_minus_275_div_25;
      double delta_theta = (double)30.0F * Math.exp(-h_prime_average_minus_275_div_25_square);
      double C_prime_average_pot_3 = C_prime_average * C_prime_average * C_prime_average;
      double C_prime_average_pot_7 = C_prime_average_pot_3 * C_prime_average_pot_3 * C_prime_average;
      double R_C = (double)2.0F * Math.sqrt(C_prime_average_pot_7 / (C_prime_average_pot_7 + 6.103515625E9));
      double R_T = -Math.sin(Math.toRadians((double)2.0F * delta_theta)) * R_C;
      double dLKlsl = delta_L_prime / ((double)2.0F * S_L);
      double dCkcsc = delta_C_prime / ((double)1.0F * S_C);
      double dHkhsh = delta_H_prime / ((double)1.0F * S_H);
      return dLKlsl * dLKlsl + dCkcsc * dCkcsc + dHkhsh * dHkhsh + R_T * dCkcsc * dHkhsh;
   }

   private static double cam02(int p1, int p2, double[] vc) {
      double[] c1 = jmh2ucs(camlch(p1, vc));
      double[] c2 = jmh2ucs(camlch(p2, vc));
      return scalar(c1, c2);
   }

   private static double[] jmh2ucs(double[] lch) {
      double sJ = 1.7000000000000002 * lch[0] / ((double)1.0F + 0.007 * lch[0]);
      double sM = 43.859649122807014 * Math.log((double)1.0F + 0.0228 * lch[1]);
      double a = sM * Math.cos(Math.toRadians(lch[2]));
      double b = sM * Math.sin(Math.toRadians(lch[2]));
      return new double[]{sJ, a, b};
   }

   static double camlch(double[] c1, double[] c2) {
      return camlch(c1, c2, new double[]{(double)1.0F, (double)1.0F, (double)1.0F});
   }

   static double camlch(double[] c1, double[] c2, double[] w) {
      double lightnessWeight = w[0] / (double)100.0F;
      double colorfulnessWeight = w[1] / (double)120.0F;
      double hueWeight = w[2] / (double)360.0F;
      double dl = (c1[0] - c2[0]) * lightnessWeight;
      double dc = (c1[1] - c2[1]) * colorfulnessWeight;
      double dh = hueDifference(c1[2], c2[2], (double)360.0F) * hueWeight;
      return dl * dl + dc * dc + dh * dh;
   }

   private static double hueDifference(double hue1, double hue2, double c) {
      double difference = (hue2 - hue1) % c;
      double ch = c / (double)2.0F;
      if (difference > ch) {
         difference -= c;
      }

      if (difference < -ch) {
         difference += c;
      }

      return difference;
   }

   private static double[] rgb(int color) {
      int r = color >> 16 & 255;
      int g = color >> 8 & 255;
      int b = color >> 0 & 255;
      return new double[]{(double)r / (double)255.0F, (double)g / (double)255.0F, (double)b / (double)255.0F};
   }

   static double[] rgb2xyz(int color) {
      return rgb2xyz(rgb(color));
   }

   static double[] rgb2cielab(int color) {
      return rgb2cielab(rgb(color));
   }

   static double[] camlch(int color) {
      return camlch(color, sRGB_typical_environment);
   }

   static double[] camlch(int color, double[] vc) {
      return xyz2camlch(rgb2xyz(color), vc);
   }

   static double[] camlab(int color) {
      return camlab(color, sRGB_typical_environment);
   }

   static double[] camlab(int color, double[] vc) {
      return lch2lab(camlch(color, vc));
   }

   static double[] lch2lab(double[] lch) {
      double toRad = (Math.PI / 180D);
      return new double[]{lch[0], lch[1] * Math.cos(lch[2] * toRad), lch[1] * Math.sin(lch[2] * toRad)};
   }

   private static double[] xyz2camlch(double[] xyz, double[] vc) {
      double[] XYZ = new double[]{xyz[0] * (double)100.0F, xyz[1] * (double)100.0F, xyz[2] * (double)100.0F};
      double[] cam = forwardTransform(XYZ, vc);
      return new double[]{cam[0], cam[3], cam[6]};
   }

   private static double[] forwardTransform(double[] XYZ, double[] vc) {
      double[] RGB = forwardPreAdaptationConeResponse(XYZ);
      double[] RGB_c = forwardPostAdaptationConeResponse(RGB, vc);
      double[] RGBPrime = CAT02toHPE(RGB_c);
      double[] RGBPrime_a = forwardResponseCompression(RGBPrime, vc);
      double A = ((double)2.0F * RGBPrime_a[0] + RGBPrime_a[1] + RGBPrime_a[2] / (double)20.0F - 0.305) * vc[10];
      double J = (double)100.0F * Math.pow(A / vc[12], vc[8] * vc[6]);
      double a = RGBPrime_a[0] + ((double)-12.0F * RGBPrime_a[1] + RGBPrime_a[2]) / (double)11.0F;
      double b = (RGBPrime_a[0] + RGBPrime_a[1] - (double)2.0F * RGBPrime_a[2]) / (double)9.0F;
      double h = (Math.toDegrees(Math.atan2(b, a)) + (double)360.0F) % (double)360.0F;
      double e = 961.5384615384615 * vc[7] * vc[11] * (Math.cos(Math.toRadians(h) + (double)2.0F) + 3.8);
      double t = e * Math.sqrt(Math.pow(a, (double)2.0F) + Math.pow(b, (double)2.0F)) / (RGBPrime_a[0] + RGBPrime_a[1] + 1.05 * RGBPrime_a[2]);
      double Q = (double)4.0F / vc[6] * Math.sqrt(J / (double)100.0F) * (vc[12] + (double)4.0F) * Math.pow(vc[13], (double)0.25F);
      double C = Math.signum(t) * Math.pow(Math.abs(t), 0.9) * Math.sqrt(J / (double)100.0F) * Math.pow(1.64 - Math.pow(0.29, vc[9]), 0.73);
      double M = C * Math.pow(vc[13], (double)0.25F);
      double s = (double)100.0F * Math.sqrt(M / Q);
      double H = calculateH(h);
      return new double[]{J, Q, C, M, s, H, h};
   }

   private static double calculateH(double h) {
      if (h < 20.14) {
         h += (double)360.0F;
      }

      if (h >= 20.14 && h < (double)90.0F) {
         double i = (h - 20.14) / 0.8;
         return (double)100.0F * i / (i + ((double)90.0F - h) / 0.7);
      } else if (h < (double)164.25F) {
         double i = (h - (double)90.0F) / 0.7;
         return (double)100.0F + (double)100.0F * i / (i + ((double)164.25F - h) / (double)1.0F);
      } else if (h < 237.53) {
         double i = (h - (double)164.25F) / (double)1.0F;
         return (double)200.0F + (double)100.0F * i / (i + (237.53 - h) / 1.2);
      } else if (h <= 380.14) {
         double i = (h - 237.53) / 1.2;
         double H = (double)300.0F + (double)100.0F * i / (i + (380.14 - h) / 0.8);
         if (H <= (double)400.0F && H >= 399.999) {
            H = (double)0.0F;
         }

         return H;
      } else {
         throw new IllegalArgumentException("h outside assumed range 0..360: " + h);
      }
   }

   private static double[] forwardResponseCompression(double[] RGB, double[] vc) {
      double[] result = new double[3];

      for(int channel = 0; channel < RGB.length; ++channel) {
         if (RGB[channel] >= (double)0.0F) {
            double n = Math.pow(vc[13] * RGB[channel] / (double)100.0F, 0.42);
            result[channel] = (double)400.0F * n / (n + 27.13) + 0.1;
         } else {
            double n = Math.pow((double)-1.0F * vc[13] * RGB[channel] / (double)100.0F, 0.42);
            result[channel] = (double)-400.0F * n / (n + 27.13) + 0.1;
         }
      }

      return result;
   }

   private static double[] forwardPostAdaptationConeResponse(double[] RGB, double[] vc) {
      return new double[]{vc[14] * RGB[0], vc[15] * RGB[1], vc[16] * RGB[2]};
   }

   public static double[] CAT02toHPE(double[] RGB) {
      double[] RGBPrime = new double[3];
      RGBPrime[0] = 0.7409792 * RGB[0] + 0.218025 * RGB[1] + 0.0410058 * RGB[2];
      RGBPrime[1] = 0.2853532 * RGB[0] + 0.6242014 * RGB[1] + 0.0904454 * RGB[2];
      RGBPrime[2] = -0.009628 * RGB[0] - 0.005698 * RGB[1] + 1.015326 * RGB[2];
      return RGBPrime;
   }

   private static double[] forwardPreAdaptationConeResponse(double[] XYZ) {
      double[] RGB = new double[3];
      RGB[0] = 0.7328 * XYZ[0] + 0.4296 * XYZ[1] - 0.1624 * XYZ[2];
      RGB[1] = -0.7036 * XYZ[0] + 1.6975 * XYZ[1] + 0.0061 * XYZ[2];
      RGB[2] = 0.003 * XYZ[0] + 0.0136 * XYZ[1] + 0.9834 * XYZ[2];
      return RGB;
   }

   static double[] vc(double[] xyz_w, double L_A, double Y_b, double[] surrounding) {
      double[] vc = new double[17];
      vc[0] = xyz_w[0];
      vc[1] = xyz_w[1];
      vc[2] = xyz_w[2];
      vc[3] = L_A;
      vc[4] = Y_b;
      vc[5] = surrounding[0];
      vc[6] = surrounding[1];
      vc[7] = surrounding[2];
      double[] RGB_w = forwardPreAdaptationConeResponse(xyz_w);
      double D = Math.max((double)0.0F, Math.min((double)1.0F, vc[5] * ((double)1.0F - 0.2777777777777778 * Math.pow(Math.E, (-L_A - (double)42.0F) / (double)92.0F))));
      double Yw = xyz_w[1];
      double[] RGB_c = new double[]{D * Yw / RGB_w[0] + ((double)1.0F - D), D * Yw / RGB_w[1] + ((double)1.0F - D), D * Yw / RGB_w[2] + ((double)1.0F - D)};
      double L_Ax5 = (double)5.0F * L_A;
      double k = (double)1.0F / (L_Ax5 + (double)1.0F);
      double kpow4 = Math.pow(k, (double)4.0F);
      vc[13] = 0.2 * kpow4 * L_Ax5 + 0.1 * Math.pow((double)1.0F - kpow4, (double)2.0F) * Math.pow(L_Ax5, 0.3333333333333333);
      vc[9] = Y_b / Yw;
      vc[8] = 1.48 + Math.sqrt(vc[9]);
      vc[10] = 0.725 * Math.pow((double)1.0F / vc[9], 0.2);
      vc[11] = vc[10];
      double[] RGB_wc = new double[]{RGB_c[0] * RGB_w[0], RGB_c[1] * RGB_w[1], RGB_c[2] * RGB_w[2]};
      double[] RGBPrime_w = CAT02toHPE(RGB_wc);
      double[] RGBPrime_aw = new double[3];

      for(int channel = 0; channel < RGBPrime_w.length; ++channel) {
         if (RGBPrime_w[channel] >= (double)0.0F) {
            double n = Math.pow(vc[13] * RGBPrime_w[channel] / (double)100.0F, 0.42);
            RGBPrime_aw[channel] = (double)400.0F * n / (n + 27.13) + 0.1;
         } else {
            double n = Math.pow((double)-1.0F * vc[13] * RGBPrime_w[channel] / (double)100.0F, 0.42);
            RGBPrime_aw[channel] = (double)-400.0F * n / (n + 27.13) + 0.1;
         }
      }

      vc[12] = ((double)2.0F * RGBPrime_aw[0] + RGBPrime_aw[1] + RGBPrime_aw[2] / (double)20.0F - 0.305) * vc[10];
      vc[14] = RGB_c[0];
      vc[15] = RGB_c[1];
      vc[16] = RGB_c[2];
      return vc;
   }

   public static double[] rgb2cielab(double[] rgb) {
      return xyz2lab(rgb2xyz(rgb));
   }

   private static double[] rgb2xyz(double[] rgb) {
      double vr = pivotRgb(rgb[0]);
      double vg = pivotRgb(rgb[1]);
      double vb = pivotRgb(rgb[2]);
      double x = vr * 0.4124564 + vg * 0.3575761 + vb * 0.1804375;
      double y = vr * 0.2126729 + vg * 0.7151522 + vb * 0.072175;
      double z = vr * 0.0193339 + vg * 0.119192 + vb * 0.9503041;
      return new double[]{x, y, z};
   }

   private static double pivotRgb(double n) {
      return n > 0.04045 ? Math.pow((n + 0.055) / 1.055, 2.4) : n / 12.92;
   }

   private static double[] xyz2lab(double[] xyz) {
      double fx = pivotXyz(xyz[0]);
      double fy = pivotXyz(xyz[1]);
      double fz = pivotXyz(xyz[2]);
      double l = (double)116.0F * fy - (double)16.0F;
      double a = (double)500.0F * (fx - fy);
      double b = (double)200.0F * (fy - fz);
      return new double[]{l, a, b};
   }

   private static double pivotXyz(double n) {
      return n > 0.008856451679035631 ? Math.cbrt(n) : (903.2962962962963 * n + (double)16.0F) / (double)116.0F;
   }

   private static double sqr(double n) {
      return n * n;
   }

   static {
      sRGB_encoding_environment = vc(D50, (double)64.0F, 12.8, dimSurrounding);
      sRGB_typical_environment = vc(D50, (double)200.0F, (double)40.0F, averageSurrounding);
      AdobeRGB_environment = vc(D65, (double)160.0F, (double)32.0F, averageSurrounding);
      COLORS_256 = DEFAULT_COLORS_256;
   }

   private static class NamedDistance implements Distance {
      private final String name;
      private final Distance delegate;

      public NamedDistance(String name, Distance delegate) {
         this.name = name;
         this.delegate = delegate;
      }

      public double compute(int c1, int c2) {
         return this.delegate.compute(c1, c2);
      }

      public String toString() {
         return this.name;
      }
   }

   @FunctionalInterface
   interface Distance {
      double compute(int var1, int var2);
   }
}
