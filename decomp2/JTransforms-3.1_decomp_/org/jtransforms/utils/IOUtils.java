package org.jtransforms.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Random;
import org.apache.commons.math3.util.FastMath;
import pl.edu.icm.jlargearrays.DoubleLargeArray;
import pl.edu.icm.jlargearrays.FloatLargeArray;

public class IOUtils {
   private static final String FF = "%.4f";

   private IOUtils() {
   }

   public static double computeRMSE(float a, float b) {
      double tmp = (double)(a - b);
      double rms = tmp * tmp;
      return FastMath.sqrt(rms);
   }

   public static double computeRMSE(float[] a, float[] b) {
      if (a.length != b.length) {
         throw new IllegalArgumentException("Arrays are not the same size");
      } else {
         double rms = (double)0.0F;

         for(int i = 0; i < a.length; ++i) {
            double tmp = (double)(a[i] - b[i]);
            rms += tmp * tmp;
         }

         return FastMath.sqrt(rms / (double)a.length);
      }
   }

   public static double computeRMSE(FloatLargeArray a, FloatLargeArray b) {
      if (a.length() != b.length()) {
         throw new IllegalArgumentException("Arrays are not the same size.");
      } else {
         double rms = (double)0.0F;

         for(long i = 0L; i < a.length(); ++i) {
            double tmp = (double)(a.getFloat(i) - b.getFloat(i));
            rms += tmp * tmp;
         }

         return FastMath.sqrt(rms / (double)a.length());
      }
   }

   public static double computeRMSE(float[][] a, float[][] b) {
      if (a.length == b.length && a[0].length == b[0].length) {
         double rms = (double)0.0F;

         for(int r = 0; r < a.length; ++r) {
            for(int c = 0; c < a[0].length; ++c) {
               double tmp = (double)(a[r][c] - b[r][c]);
               rms += tmp * tmp;
            }
         }

         return FastMath.sqrt(rms / (double)(a.length * a[0].length));
      } else {
         throw new IllegalArgumentException("Arrays are not the same size");
      }
   }

   public static double computeRMSE(float[][][] a, float[][][] b) {
      if (a.length == b.length && a[0].length == b[0].length && a[0][0].length == b[0][0].length) {
         double rms = (double)0.0F;

         for(int s = 0; s < a.length; ++s) {
            for(int r = 0; r < a[0].length; ++r) {
               for(int c = 0; c < a[0][0].length; ++c) {
                  double tmp = (double)(a[s][r][c] - b[s][r][c]);
                  rms += tmp * tmp;
               }
            }
         }

         return FastMath.sqrt(rms / (double)(a.length * a[0].length * a[0][0].length));
      } else {
         throw new IllegalArgumentException("Arrays are not the same size");
      }
   }

   public static double computeRMSE(double a, double b) {
      double tmp = a - b;
      double rms = tmp * tmp;
      return FastMath.sqrt(rms);
   }

   public static double computeRMSE(double[] a, double[] b) {
      if (a.length != b.length) {
         throw new IllegalArgumentException("Arrays are not the same size");
      } else {
         double rms = (double)0.0F;

         for(int i = 0; i < a.length; ++i) {
            double tmp = a[i] - b[i];
            rms += tmp * tmp;
         }

         return FastMath.sqrt(rms / (double)a.length);
      }
   }

   public static double computeRMSE(DoubleLargeArray a, DoubleLargeArray b) {
      if (a.length() != b.length()) {
         throw new IllegalArgumentException("Arrays are not the same size.");
      } else {
         double rms = (double)0.0F;

         for(long i = 0L; i < a.length(); ++i) {
            double tmp = a.getDouble(i) - b.getDouble(i);
            rms += tmp * tmp;
         }

         return FastMath.sqrt(rms / (double)a.length());
      }
   }

   public static double computeRMSE(double[][] a, double[][] b) {
      if (a.length == b.length && a[0].length == b[0].length) {
         double rms = (double)0.0F;

         for(int r = 0; r < a.length; ++r) {
            for(int c = 0; c < a[0].length; ++c) {
               double tmp = a[r][c] - b[r][c];
               rms += tmp * tmp;
            }
         }

         return FastMath.sqrt(rms / (double)(a.length * a[0].length));
      } else {
         throw new IllegalArgumentException("Arrays are not the same size");
      }
   }

   public static double computeRMSE(double[][][] a, double[][][] b) {
      if (a.length == b.length && a[0].length == b[0].length && a[0][0].length == b[0][0].length) {
         double rms = (double)0.0F;

         for(int s = 0; s < a.length; ++s) {
            for(int r = 0; r < a[0].length; ++r) {
               for(int c = 0; c < a[0][0].length; ++c) {
                  double tmp = a[s][r][c] - b[s][r][c];
                  rms += tmp * tmp;
               }
            }
         }

         return FastMath.sqrt(rms / (double)(a.length * a[0].length * a[0][0].length));
      } else {
         throw new IllegalArgumentException("Arrays are not the same size");
      }
   }

   public static void fillMatrix_1D(long N, double[] m) {
      Random r = new Random(2L);

      for(int i = 0; (long)i < N; ++i) {
         m[i] = r.nextDouble();
      }

   }

   public static void fillMatrix_1D(long N, DoubleLargeArray m) {
      Random r = new Random(2L);

      for(long i = 0L; i < N; ++i) {
         m.setDouble(i, r.nextDouble());
      }

   }

   public static void fillMatrix_1D(long N, FloatLargeArray m) {
      Random r = new Random(2L);

      for(long i = 0L; i < N; ++i) {
         m.setDouble(i, (double)r.nextFloat());
      }

   }

   public static void fillMatrix_1D(long N, float[] m) {
      Random r = new Random(2L);

      for(int i = 0; (long)i < N; ++i) {
         m[i] = r.nextFloat();
      }

   }

   public static void fillMatrix_2D(long n1, long n2, double[] m) {
      Random r = new Random(2L);

      for(int i = 0; (long)i < n1; ++i) {
         for(int j = 0; (long)j < n2; ++j) {
            m[(int)((long)i * n2 + (long)j)] = r.nextDouble();
         }
      }

   }

   public static void fillMatrix_2D(long n1, long n2, FloatLargeArray m) {
      Random r = new Random(2L);

      for(long i = 0L; i < n1; ++i) {
         for(long j = 0L; j < n2; ++j) {
            m.setFloat(i * n2 + j, r.nextFloat());
         }
      }

   }

   public static void fillMatrix_2D(long n1, long n2, DoubleLargeArray m) {
      Random r = new Random(2L);

      for(long i = 0L; i < n1; ++i) {
         for(long j = 0L; j < n2; ++j) {
            m.setDouble(i * n2 + j, r.nextDouble());
         }
      }

   }

   public static void fillMatrix_2D(long n1, long n2, float[] m) {
      Random r = new Random(2L);

      for(int i = 0; (long)i < n1; ++i) {
         for(int j = 0; (long)j < n2; ++j) {
            m[(int)((long)i * n2 + (long)j)] = r.nextFloat();
         }
      }

   }

   public static void fillMatrix_2D(long n1, long n2, double[][] m) {
      Random r = new Random(2L);

      for(int i = 0; (long)i < n1; ++i) {
         for(int j = 0; (long)j < n2; ++j) {
            m[i][j] = r.nextDouble();
         }
      }

   }

   public static void fillMatrix_2D(long n1, long n2, float[][] m) {
      Random r = new Random(2L);

      for(int i = 0; (long)i < n1; ++i) {
         for(int j = 0; (long)j < n2; ++j) {
            m[i][j] = r.nextFloat();
         }
      }

   }

   public static void fillMatrix_3D(long n1, long n2, long n3, double[] m) {
      Random r = new Random(2L);
      long sliceStride = n2 * n3;
      long rowStride = n3;

      for(int i = 0; (long)i < n1; ++i) {
         for(int j = 0; (long)j < n2; ++j) {
            for(int k = 0; (long)k < n3; ++k) {
               m[(int)((long)i * sliceStride + (long)j * rowStride + (long)k)] = r.nextDouble();
            }
         }
      }

   }

   public static void fillMatrix_3D(long n1, long n2, long n3, DoubleLargeArray m) {
      Random r = new Random(2L);
      long sliceStride = n2 * n3;
      long rowStride = n3;

      for(long i = 0L; i < n1; ++i) {
         for(long j = 0L; j < n2; ++j) {
            for(long k = 0L; k < n3; ++k) {
               m.setDouble(i * sliceStride + j * rowStride + k, r.nextDouble());
            }
         }
      }

   }

   public static void fillMatrix_3D(long n1, long n2, long n3, FloatLargeArray m) {
      Random r = new Random(2L);
      long sliceStride = n2 * n3;
      long rowStride = n3;

      for(long i = 0L; i < n1; ++i) {
         for(long j = 0L; j < n2; ++j) {
            for(long k = 0L; k < n3; ++k) {
               m.setDouble(i * sliceStride + j * rowStride + k, (double)r.nextFloat());
            }
         }
      }

   }

   public static void fillMatrix_3D(long n1, long n2, long n3, float[] m) {
      Random r = new Random(2L);
      long sliceStride = n2 * n3;
      long rowStride = n3;

      for(int i = 0; (long)i < n1; ++i) {
         for(int j = 0; (long)j < n2; ++j) {
            for(int k = 0; (long)k < n3; ++k) {
               m[(int)((long)i * sliceStride + (long)j * rowStride + (long)k)] = r.nextFloat();
            }
         }
      }

   }

   public static void fillMatrix_3D(long n1, long n2, long n3, double[][][] m) {
      Random r = new Random(2L);

      for(int i = 0; (long)i < n1; ++i) {
         for(int j = 0; (long)j < n2; ++j) {
            for(int k = 0; (long)k < n3; ++k) {
               m[i][j][k] = r.nextDouble();
            }
         }
      }

   }

   public static void fillMatrix_3D(long n1, long n2, long n3, float[][][] m) {
      Random r = new Random(2L);

      for(int i = 0; (long)i < n1; ++i) {
         for(int j = 0; (long)j < n2; ++j) {
            for(int k = 0; (long)k < n3; ++k) {
               m[i][j][k] = r.nextFloat();
            }
         }
      }

   }

   public static void showComplex_1D(double[] x, String title) {
      System.out.println(title);
      System.out.println("-------------------");

      for(int i = 0; i < x.length; i += 2) {
         if (x[i + 1] == (double)0.0F) {
            System.out.println(String.format("%.4f", x[i]));
         } else if (x[i] == (double)0.0F) {
            System.out.println(String.format("%.4f", x[i + 1]) + "i");
         } else if (x[i + 1] < (double)0.0F) {
            System.out.println(String.format("%.4f", x[i]) + " - " + String.format("%.4f", -x[i + 1]) + "i");
         } else {
            System.out.println(String.format("%.4f", x[i]) + " + " + String.format("%.4f", x[i + 1]) + "i");
         }
      }

      System.out.println();
   }

   public static void showComplex_2D(int rows, int columns, double[] x, String title) {
      StringBuilder s = new StringBuilder(String.format(title + ": complex array 2D: %d rows, %d columns\n\n", rows, columns));

      for(int r = 0; r < rows; ++r) {
         for(int c = 0; c < 2 * columns; c += 2) {
            if (x[r * 2 * columns + c + 1] == (double)0.0F) {
               s.append(String.format("%.4f\t", x[r * 2 * columns + c]));
            } else if (x[r * 2 * columns + c] == (double)0.0F) {
               s.append(String.format("%.4fi\t", x[r * 2 * columns + c + 1]));
            } else if (x[r * 2 * columns + c + 1] < (double)0.0F) {
               s.append(String.format("%.4f - %.4fi\t", x[r * 2 * columns + c], -x[r * 2 * columns + c + 1]));
            } else {
               s.append(String.format("%.4f + %.4fi\t", x[r * 2 * columns + c], x[r * 2 * columns + c + 1]));
            }
         }

         s.append("\n");
      }

      System.out.println(s.toString());
   }

   public static void showComplex_2D(double[][] x, String title) {
      int rows = x.length;
      int columns = x[0].length;
      StringBuilder s = new StringBuilder(String.format(title + ": complex array 2D: %d rows, %d columns\n\n", rows, columns));

      for(int r = 0; r < rows; ++r) {
         for(int c = 0; c < columns; c += 2) {
            if (x[r][c + 1] == (double)0.0F) {
               s.append(String.format("%.4f\t", x[r][c]));
            } else if (x[r][c] == (double)0.0F) {
               s.append(String.format("%.4fi\t", x[r][c + 1]));
            } else if (x[r][c + 1] < (double)0.0F) {
               s.append(String.format("%.4f - %.4fi\t", x[r][c], -x[r][c + 1]));
            } else {
               s.append(String.format("%.4f + %.4fi\t", x[r][c], x[r][c + 1]));
            }
         }

         s.append("\n");
      }

      System.out.println(s.toString());
   }

   public static void showComplex_3D(int n1, int n2, int n3, double[] x, String title) {
      int sliceStride = n2 * 2 * n3;
      int rowStride = 2 * n3;
      System.out.println(title);
      System.out.println("-------------------");

      for(int k = 0; k < 2 * n3; k += 2) {
         System.out.println("(:,:," + k / 2 + ")=\n");

         for(int i = 0; i < n1; ++i) {
            for(int j = 0; j < n2; ++j) {
               if (x[i * sliceStride + j * rowStride + k + 1] == (double)0.0F) {
                  System.out.print(String.format("%.4f", x[i * sliceStride + j * rowStride + k]) + "\t");
               } else if (x[i * sliceStride + j * rowStride + k] == (double)0.0F) {
                  System.out.print(String.format("%.4f", x[i * sliceStride + j * rowStride + k + 1]) + "i\t");
               } else if (x[i * sliceStride + j * rowStride + k + 1] < (double)0.0F) {
                  System.out.print(String.format("%.4f", x[i * sliceStride + j * rowStride + k]) + " - " + String.format("%.4f", -x[i * sliceStride + j * rowStride + k + 1]) + "i\t");
               } else {
                  System.out.print(String.format("%.4f", x[i * sliceStride + j * rowStride + k]) + " + " + String.format("%.4f", x[i * sliceStride + j * rowStride + k + 1]) + "i\t");
               }
            }

            System.out.println("");
         }
      }

      System.out.println("");
   }

   public static void showComplex_3D(double[][][] x, String title) {
      System.out.println(title);
      System.out.println("-------------------");
      int slices = x.length;
      int rows = x[0].length;
      int columns = x[0][0].length;

      for(int k = 0; k < columns; k += 2) {
         System.out.println("(:,:," + k / 2 + ")=\n");

         for(int i = 0; i < slices; ++i) {
            for(int j = 0; j < rows; ++j) {
               if (x[i][j][k + 1] == (double)0.0F) {
                  System.out.print(String.format("%.4f", x[i][j][k]) + "\t");
               } else if (x[i][j][k] == (double)0.0F) {
                  System.out.print(String.format("%.4f", x[i][j][k + 1]) + "i\t");
               } else if (x[i][j][k + 1] < (double)0.0F) {
                  System.out.print(String.format("%.4f", x[i][j][k]) + " - " + String.format("%.4f", -x[i][j][k + 1]) + "i\t");
               } else {
                  System.out.print(String.format("%.4f", x[i][j][k]) + " + " + String.format("%.4f", x[i][j][k + 1]) + "i\t");
               }
            }

            System.out.println("");
         }
      }

      System.out.println("");
   }

   public static void showComplex_3D(int n1, int n2, int n3, float[] x, String title) {
      int sliceStride = n2 * 2 * n3;
      int rowStride = 2 * n3;
      System.out.println(title);
      System.out.println("-------------------");

      for(int k = 0; k < 2 * n3; k += 2) {
         System.out.println("(:,:," + k / 2 + ")=\n");

         for(int i = 0; i < n1; ++i) {
            for(int j = 0; j < n2; ++j) {
               if (x[i * sliceStride + j * rowStride + k + 1] == 0.0F) {
                  System.out.print(String.format("%.4f", x[i * sliceStride + j * rowStride + k]) + "\t");
               } else if (x[i * sliceStride + j * rowStride + k] == 0.0F) {
                  System.out.print(String.format("%.4f", x[i * sliceStride + j * rowStride + k + 1]) + "i\t");
               } else if (x[i * sliceStride + j * rowStride + k + 1] < 0.0F) {
                  System.out.print(String.format("%.4f", x[i * sliceStride + j * rowStride + k]) + " - " + String.format("%.4f", -x[i * sliceStride + j * rowStride + k + 1]) + "i\t");
               } else {
                  System.out.print(String.format("%.4f", x[i * sliceStride + j * rowStride + k]) + " + " + String.format("%.4f", x[i * sliceStride + j * rowStride + k + 1]) + "i\t");
               }
            }

            System.out.println("");
         }
      }

      System.out.println("");
   }

   public static void showReal_1D(double[] x, String title) {
      System.out.println(title);
      System.out.println("-------------------");

      for(int j = 0; j < x.length; ++j) {
         System.out.println(String.format("%.4f", x[j]));
      }

      System.out.println();
   }

   public static void showReal_2D(int n1, int n2, double[] x, String title) {
      System.out.println(title);
      System.out.println("-------------------");

      for(int i = 0; i < n1; ++i) {
         for(int j = 0; j < n2; ++j) {
            if (FastMath.abs(x[i * n2 + j]) < 5.0E-5) {
               System.out.print("0\t");
            } else {
               System.out.print(String.format("%.4f", x[i * n2 + j]) + "\t");
            }
         }

         System.out.println();
      }

      System.out.println();
   }

   public static void showReal_3D(int n1, int n2, int n3, double[] x, String title) {
      int sliceStride = n2 * n3;
      int rowStride = n3;
      System.out.println(title);
      System.out.println("-------------------");

      for(int k = 0; k < n3; ++k) {
         System.out.println();
         System.out.println("(:,:," + k + ")=\n");

         for(int i = 0; i < n1; ++i) {
            for(int j = 0; j < n2; ++j) {
               if (FastMath.abs(x[i * sliceStride + j * rowStride + k]) <= 5.0E-5) {
                  System.out.print("0\t");
               } else {
                  System.out.print(String.format("%.4f", x[i * sliceStride + j * rowStride + k]) + "\t");
               }
            }

            System.out.println();
         }
      }

      System.out.println();
   }

   public static void showReal_3D(double[][][] x, String title) {
      System.out.println(title);
      System.out.println("-------------------");
      int slices = x.length;
      int rows = x[0].length;
      int columns = x[0][0].length;

      for(int k = 0; k < columns; ++k) {
         System.out.println();
         System.out.println("(:,:," + k + ")=\n");

         for(int i = 0; i < slices; ++i) {
            for(int j = 0; j < rows; ++j) {
               if (FastMath.abs(x[i][j][k]) <= 5.0E-5) {
                  System.out.print("0\t");
               } else {
                  System.out.print(String.format("%.4f", x[i][j][k]) + "\t");
               }
            }

            System.out.println();
         }
      }

      System.out.println();
   }

   public static void writeToFileComplex_1D(double[] x, String filename) {
      try {
         BufferedWriter out = new BufferedWriter(new FileWriter(filename));

         for(int i = 0; i < x.length; i += 2) {
            if (x[i + 1] == (double)0.0F) {
               out.write(String.format("%.4f", x[i]));
               out.newLine();
            } else if (x[i] == (double)0.0F) {
               out.write(String.format("%.4f", x[i + 1]) + "i");
               out.newLine();
            } else if (x[i + 1] < (double)0.0F) {
               out.write(String.format("%.4f", x[i]) + " - " + String.format("%.4f", -x[i + 1]) + "i");
               out.newLine();
            } else {
               out.write(String.format("%.4f", x[i]) + " + " + String.format("%.4f", x[i + 1]) + "i");
               out.newLine();
            }
         }

         out.newLine();
         out.close();
      } catch (IOException e) {
         e.printStackTrace();
      }

   }

   public static void writeToFileComplex_1D(float[] x, String filename) {
      try {
         BufferedWriter out = new BufferedWriter(new FileWriter(filename));

         for(int i = 0; i < x.length; i += 2) {
            if (x[i + 1] == 0.0F) {
               out.write(String.format("%.4f", x[i]));
               out.newLine();
            } else if (x[i] == 0.0F) {
               out.write(String.format("%.4f", x[i + 1]) + "i");
               out.newLine();
            } else if (x[i + 1] < 0.0F) {
               out.write(String.format("%.4f", x[i]) + " - " + String.format("%.4f", -x[i + 1]) + "i");
               out.newLine();
            } else {
               out.write(String.format("%.4f", x[i]) + " + " + String.format("%.4f", x[i + 1]) + "i");
               out.newLine();
            }
         }

         out.newLine();
         out.close();
      } catch (IOException e) {
         e.printStackTrace();
      }

   }

   public static void writeToFileComplex_2D(int n1, int n2, double[] x, String filename) {
      try {
         BufferedWriter out = new BufferedWriter(new FileWriter(filename));

         for(int i = 0; i < n1; ++i) {
            for(int j = 0; j < 2 * n2; j += 2) {
               if (FastMath.abs(x[i * 2 * n2 + j]) < 5.0E-5 && FastMath.abs(x[i * 2 * n2 + j + 1]) < 5.0E-5) {
                  if (x[i * 2 * n2 + j + 1] >= (double)0.0F) {
                     out.write("0 + 0i\t");
                  } else {
                     out.write("0 - 0i\t");
                  }
               } else if (FastMath.abs(x[i * 2 * n2 + j + 1]) < 5.0E-5) {
                  if (x[i * 2 * n2 + j + 1] >= (double)0.0F) {
                     out.write(String.format("%.4f", x[i * 2 * n2 + j]) + " + 0i\t");
                  } else {
                     out.write(String.format("%.4f", x[i * 2 * n2 + j]) + " - 0i\t");
                  }
               } else if (FastMath.abs(x[i * 2 * n2 + j]) < 5.0E-5) {
                  if (x[i * 2 * n2 + j + 1] >= (double)0.0F) {
                     out.write("0 + " + String.format("%.4f", x[i * 2 * n2 + j + 1]) + "i\t");
                  } else {
                     out.write("0 - " + String.format("%.4f", -x[i * 2 * n2 + j + 1]) + "i\t");
                  }
               } else if (x[i * 2 * n2 + j + 1] < (double)0.0F) {
                  out.write(String.format("%.4f", x[i * 2 * n2 + j]) + " - " + String.format("%.4f", -x[i * 2 * n2 + j + 1]) + "i\t");
               } else {
                  out.write(String.format("%.4f", x[i * 2 * n2 + j]) + " + " + String.format("%.4f", x[i * 2 * n2 + j + 1]) + "i\t");
               }
            }

            out.newLine();
         }

         out.newLine();
         out.close();
      } catch (IOException e) {
         e.printStackTrace();
      }

   }

   public static void writeToFileComplex_2D(int n1, int n2, float[] x, String filename) {
      try {
         BufferedWriter out = new BufferedWriter(new FileWriter(filename));

         for(int i = 0; i < n1; ++i) {
            for(int j = 0; j < 2 * n2; j += 2) {
               if ((double)FastMath.abs(x[i * 2 * n2 + j]) < 5.0E-5 && (double)FastMath.abs(x[i * 2 * n2 + j + 1]) < 5.0E-5) {
                  if ((double)x[i * 2 * n2 + j + 1] >= (double)0.0F) {
                     out.write("0 + 0i\t");
                  } else {
                     out.write("0 - 0i\t");
                  }
               } else if ((double)FastMath.abs(x[i * 2 * n2 + j + 1]) < 5.0E-5) {
                  if ((double)x[i * 2 * n2 + j + 1] >= (double)0.0F) {
                     out.write(String.format("%.4f", x[i * 2 * n2 + j]) + " + 0i\t");
                  } else {
                     out.write(String.format("%.4f", x[i * 2 * n2 + j]) + " - 0i\t");
                  }
               } else if ((double)FastMath.abs(x[i * 2 * n2 + j]) < 5.0E-5) {
                  if ((double)x[i * 2 * n2 + j + 1] >= (double)0.0F) {
                     out.write("0 + " + String.format("%.4f", x[i * 2 * n2 + j + 1]) + "i\t");
                  } else {
                     out.write("0 - " + String.format("%.4f", -x[i * 2 * n2 + j + 1]) + "i\t");
                  }
               } else if (x[i * 2 * n2 + j + 1] < 0.0F) {
                  out.write(String.format("%.4f", x[i * 2 * n2 + j]) + " - " + String.format("%.4f", -x[i * 2 * n2 + j + 1]) + "i\t");
               } else {
                  out.write(String.format("%.4f", x[i * 2 * n2 + j]) + " + " + String.format("%.4f", x[i * 2 * n2 + j + 1]) + "i\t");
               }
            }

            out.newLine();
         }

         out.newLine();
         out.close();
      } catch (IOException e) {
         e.printStackTrace();
      }

   }

   public static void writeToFileComplex_2D(double[][] x, String filename) {
      int n1 = x.length;
      int n2 = x[0].length;

      try {
         BufferedWriter out = new BufferedWriter(new FileWriter(filename));

         for(int i = 0; i < n1; ++i) {
            for(int j = 0; j < 2 * n2; j += 2) {
               if (FastMath.abs(x[i][j]) < 5.0E-5 && FastMath.abs(x[i][j + 1]) < 5.0E-5) {
                  if (x[i][j + 1] >= (double)0.0F) {
                     out.write("0 + 0i\t");
                  } else {
                     out.write("0 - 0i\t");
                  }
               } else if (FastMath.abs(x[i][j + 1]) < 5.0E-5) {
                  if (x[i][j + 1] >= (double)0.0F) {
                     out.write(String.format("%.4f", x[i][j]) + " + 0i\t");
                  } else {
                     out.write(String.format("%.4f", x[i][j]) + " - 0i\t");
                  }
               } else if (FastMath.abs(x[i][j]) < 5.0E-5) {
                  if (x[i][j + 1] >= (double)0.0F) {
                     out.write("0 + " + String.format("%.4f", x[i][j + 1]) + "i\t");
                  } else {
                     out.write("0 - " + String.format("%.4f", -x[i][j + 1]) + "i\t");
                  }
               } else if (x[i][j + 1] < (double)0.0F) {
                  out.write(String.format("%.4f", x[i][j]) + " - " + String.format("%.4f", -x[i][j + 1]) + "i\t");
               } else {
                  out.write(String.format("%.4f", x[i][j]) + " + " + String.format("%.4f", x[i][j + 1]) + "i\t");
               }
            }

            out.newLine();
         }

         out.newLine();
         out.close();
      } catch (IOException e) {
         e.printStackTrace();
      }

   }

   public static void writeToFileComplex_3D(int n1, int n2, int n3, double[] x, String filename) {
      int sliceStride = n2 * n3 * 2;
      int rowStride = n3 * 2;

      try {
         BufferedWriter out = new BufferedWriter(new FileWriter(filename));

         for(int k = 0; k < 2 * n3; k += 2) {
            out.newLine();
            out.write("(:,:," + k / 2 + ")=");
            out.newLine();
            out.newLine();

            for(int i = 0; i < n1; ++i) {
               for(int j = 0; j < n2; ++j) {
                  if (x[i * sliceStride + j * rowStride + k + 1] == (double)0.0F) {
                     out.write(String.format("%.4f", x[i * sliceStride + j * rowStride + k]) + "\t");
                  } else if (x[i * sliceStride + j * rowStride + k] == (double)0.0F) {
                     out.write(String.format("%.4f", x[i * sliceStride + j * rowStride + k + 1]) + "i\t");
                  } else if (x[i * sliceStride + j * rowStride + k + 1] < (double)0.0F) {
                     out.write(String.format("%.4f", x[i * sliceStride + j * rowStride + k]) + " - " + String.format("%.4f", -x[i * sliceStride + j * rowStride + k + 1]) + "i\t");
                  } else {
                     out.write(String.format("%.4f", x[i * sliceStride + j * rowStride + k]) + " + " + String.format("%.4f", x[i * sliceStride + j * rowStride + k + 1]) + "i\t");
                  }
               }

               out.newLine();
            }
         }

         out.newLine();
         out.close();
      } catch (IOException e) {
         e.printStackTrace();
      }

   }

   public static void writeToFileComplex_3D(double[][][] x, String filename) {
      int n1 = x.length;
      int n2 = x[0].length;
      int n3 = x[0][0].length;

      try {
         BufferedWriter out = new BufferedWriter(new FileWriter(filename));

         for(int k = 0; k < 2 * n3; k += 2) {
            out.newLine();
            out.write("(:,:," + k / 2 + ")=");
            out.newLine();
            out.newLine();

            for(int i = 0; i < n1; ++i) {
               for(int j = 0; j < n2; ++j) {
                  if (x[i][j][k + 1] == (double)0.0F) {
                     out.write(String.format("%.4f", x[i][j][k]) + "\t");
                  } else if (x[i][j][k] == (double)0.0F) {
                     out.write(String.format("%.4f", x[i][j][k + 1]) + "i\t");
                  } else if (x[i][j][k + 1] < (double)0.0F) {
                     out.write(String.format("%.4f", x[i][j][k]) + " - " + String.format("%.4f", -x[i][j][k + 1]) + "i\t");
                  } else {
                     out.write(String.format("%.4f", x[i][j][k]) + " + " + String.format("%.4f", x[i][j][k + 1]) + "i\t");
                  }
               }

               out.newLine();
            }
         }

         out.newLine();
         out.close();
      } catch (IOException e) {
         e.printStackTrace();
      }

   }

   public static void writeToFileReal_1D(double[] x, String filename) {
      try {
         BufferedWriter out = new BufferedWriter(new FileWriter(filename));

         for(int j = 0; j < x.length; ++j) {
            out.write(String.format("%.4f", x[j]));
            out.newLine();
         }

         out.close();
      } catch (IOException e) {
         e.printStackTrace();
      }

   }

   public static void writeToFileReal_1D(float[] x, String filename) {
      try {
         BufferedWriter out = new BufferedWriter(new FileWriter(filename));

         for(int j = 0; j < x.length; ++j) {
            out.write(String.format("%.4f", x[j]));
            out.newLine();
         }

         out.close();
      } catch (IOException e) {
         e.printStackTrace();
      }

   }

   public static void writeToFileReal_2D(int n1, int n2, double[] x, String filename) {
      try {
         BufferedWriter out = new BufferedWriter(new FileWriter(filename));

         for(int i = 0; i < n1; ++i) {
            for(int j = 0; j < n2; ++j) {
               if (FastMath.abs(x[i * n2 + j]) < 5.0E-5) {
                  out.write("0\t");
               } else {
                  out.write(String.format("%.4f", x[i * n2 + j]) + "\t");
               }
            }

            out.newLine();
         }

         out.close();
      } catch (IOException e) {
         e.printStackTrace();
      }

   }

   public static void writeToFileReal_2D(int n1, int n2, float[] x, String filename) {
      try {
         BufferedWriter out = new BufferedWriter(new FileWriter(filename));

         for(int i = 0; i < n1; ++i) {
            for(int j = 0; j < n2; ++j) {
               if ((double)FastMath.abs(x[i * n2 + j]) < 5.0E-5) {
                  out.write("0\t");
               } else {
                  out.write(String.format("%.4f", x[i * n2 + j]) + "\t");
               }
            }

            out.newLine();
         }

         out.close();
      } catch (IOException e) {
         e.printStackTrace();
      }

   }

   public static void writeToFileReal_3D(int n1, int n2, int n3, double[] x, String filename) {
      int sliceStride = n2 * n3;
      int rowStride = n3;

      try {
         BufferedWriter out = new BufferedWriter(new FileWriter(filename));

         for(int k = 0; k < n3; ++k) {
            out.newLine();
            out.write("(:,:," + k + ")=");
            out.newLine();
            out.newLine();

            for(int i = 0; i < n1; ++i) {
               for(int j = 0; j < n2; ++j) {
                  out.write(String.format("%.4f", x[i * sliceStride + j * rowStride + k]) + "\t");
               }

               out.newLine();
            }

            out.newLine();
         }

         out.close();
      } catch (IOException e) {
         e.printStackTrace();
      }

   }

   public static void writeFFTBenchmarkResultsToFile(String filename, int nthread, int niter, boolean doWarmup, boolean doScaling, long[] sizes, double[] times_without_constructor, double[] times_with_constructor) {
      String[] properties = new String[]{"os.name", "os.version", "os.arch", "java.vendor", "java.version"};

      try {
         BufferedWriter out = new BufferedWriter(new FileWriter(filename, false));
         out.write((new Date()).toString());
         out.newLine();
         out.write("System properties:");
         out.newLine();
         out.write("\tos.name = " + System.getProperty(properties[0]));
         out.newLine();
         out.write("\tos.version = " + System.getProperty(properties[1]));
         out.newLine();
         out.write("\tos.arch = " + System.getProperty(properties[2]));
         out.newLine();
         out.write("\tjava.vendor = " + System.getProperty(properties[3]));
         out.newLine();
         out.write("\tjava.version = " + System.getProperty(properties[4]));
         out.newLine();
         out.write("\tavailable processors = " + Runtime.getRuntime().availableProcessors());
         out.newLine();
         out.write("Settings:");
         out.newLine();
         out.write("\tused processors = " + nthread);
         out.newLine();
         out.write("\tTHREADS_BEGIN_N_2D = " + CommonUtils.getThreadsBeginN_2D());
         out.newLine();
         out.write("\tTHREADS_BEGIN_N_3D = " + CommonUtils.getThreadsBeginN_3D());
         out.newLine();
         out.write("\tnumber of iterations = " + niter);
         out.newLine();
         out.write("\twarm-up performed = " + doWarmup);
         out.newLine();
         out.write("\tscaling performed = " + doScaling);
         out.newLine();
         out.write("--------------------------------------------------------------------------------------------------");
         out.newLine();
         out.write("sizes=[");

         for(int i = 0; i < sizes.length; ++i) {
            out.write(Long.toString(sizes[i]));
            if (i < sizes.length - 1) {
               out.write(", ");
            } else {
               out.write("]");
            }
         }

         out.newLine();
         out.write("times without constructor(in msec)=[");

         for(int i = 0; i < times_without_constructor.length; ++i) {
            out.write(String.format("%.2f", times_without_constructor[i]));
            if (i < times_without_constructor.length - 1) {
               out.write(", ");
            } else {
               out.write("]");
            }
         }

         out.newLine();
         out.write("times with constructor(in msec)=[");

         for(int i = 0; i < times_without_constructor.length; ++i) {
            out.write(String.format("%.2f", times_with_constructor[i]));
            if (i < times_with_constructor.length - 1) {
               out.write(", ");
            } else {
               out.write("]");
            }
         }

         out.newLine();
         out.close();
      } catch (IOException e) {
         e.printStackTrace();
      }

   }
}
