package org.jtransforms.dst;

import java.util.Arrays;
import org.jtransforms.utils.CommonUtils;
import org.jtransforms.utils.IOUtils;
import pl.edu.icm.jlargearrays.ConcurrencyUtils;
import pl.edu.icm.jlargearrays.FloatLargeArray;

public class BenchmarkFloatDST {
   private static int nthread = 8;
   private static int niter = 200;
   private static int nsize = 16;
   private static int threadsBegin2D = 65636;
   private static int threadsBegin3D = 65636;
   private static boolean doWarmup = true;
   private static long[] sizes1D = new long[]{262144L, 524288L, 1048576L, 2097152L, 4194304L, 8388608L, 16777216L, 33554432L, 10368L, 27000L, 75600L, 165375L, 362880L, 1562500L, 3211264L, 6250000L};
   private static long[] sizes2D = new long[]{256L, 512L, 1024L, 2048L, 4096L, 8192L, 16384L, 32768L, 260L, 520L, 1050L, 1458L, 1960L, 2916L, 4116L, 5832L};
   private static long[] sizes3D = new long[]{16L, 32L, 64L, 128L, 256L, 512L, 1024L, 2048L, 5L, 17L, 30L, 95L, 180L, 270L, 324L, 420L};
   private static boolean doScaling = false;

   private BenchmarkFloatDST() {
   }

   public static void parseArguments(String[] args) {
      if (args.length > 0) {
         nthread = Integer.parseInt(args[0]);
         threadsBegin2D = Integer.parseInt(args[1]);
         threadsBegin3D = Integer.parseInt(args[2]);
         niter = Integer.parseInt(args[3]);
         doWarmup = Boolean.parseBoolean(args[4]);
         doScaling = Boolean.parseBoolean(args[5]);
         nsize = Integer.parseInt(args[6]);
         sizes1D = new long[nsize];
         sizes2D = new long[nsize];
         sizes3D = new long[nsize];

         for(int i = 0; i < nsize; ++i) {
            sizes1D[i] = (long)Integer.parseInt(args[7 + i]);
         }

         for(int i = 0; i < nsize; ++i) {
            sizes2D[i] = (long)Integer.parseInt(args[7 + nsize + i]);
         }

         for(int i = 0; i < nsize; ++i) {
            sizes3D[i] = (long)Integer.parseInt(args[7 + nsize + nsize + i]);
         }
      } else {
         System.out.println("Default settings are used.");
      }

      ConcurrencyUtils.setNumberOfThreads(nthread);
      CommonUtils.setThreadsBeginN_2D((long)threadsBegin2D);
      CommonUtils.setThreadsBeginN_3D((long)threadsBegin3D);
      System.out.println("nthred = " + nthread);
      System.out.println("threadsBegin2D = " + threadsBegin2D);
      System.out.println("threadsBegin3D = " + threadsBegin3D);
      System.out.println("niter = " + niter);
      System.out.println("doWarmup = " + doWarmup);
      System.out.println("doScaling = " + doScaling);
      System.out.println("nsize = " + nsize);
      System.out.println("sizes1D[] = " + Arrays.toString(sizes1D));
      System.out.println("sizes2D[] = " + Arrays.toString(sizes2D));
      System.out.println("sizes3D[] = " + Arrays.toString(sizes3D));
   }

   public static void benchmarkForward_1D() {
      double[] times_without_constructor = new double[nsize];
      double[] times_with_constructor = new double[nsize];

      for(int i = 0; i < nsize; ++i) {
         System.out.println("Forward DST 1D of size " + sizes1D[i]);
         if (doWarmup) {
            FloatDST_1D dst = new FloatDST_1D(sizes1D[i]);
            float[] x = new float[(int)sizes1D[i]];
            IOUtils.fillMatrix_1D(sizes1D[i], x);
            dst.forward(x, doScaling);
            IOUtils.fillMatrix_1D(sizes1D[i], x);
            dst.forward(x, doScaling);
         }

         long elapsedTime = System.nanoTime();
         FloatDST_1D dst = new FloatDST_1D(sizes1D[i]);
         times_with_constructor[i] = (double)(System.nanoTime() - elapsedTime) / (double)1000000.0F;
         float[] x = new float[(int)sizes1D[i]];
         double min_time = (double)0.0F;

         for(int j = 0; j < niter; ++j) {
            IOUtils.fillMatrix_1D(sizes1D[i], x);
            elapsedTime = System.nanoTime();
            dst.forward(x, doScaling);
            elapsedTime = System.nanoTime() - elapsedTime;
            if ((double)elapsedTime < min_time) {
               min_time = (double)elapsedTime;
            }
         }

         times_without_constructor[i] = min_time / (double)1000000.0F;
         times_with_constructor[i] += times_without_constructor[i];
         System.out.println("\tBest execution time without constructor: " + String.format("%.2f", times_without_constructor[i]) + " msec");
         System.out.println("\tBest execution time with constructor: " + String.format("%.2f", times_with_constructor[i]) + " msec");
         float[] var11 = null;
         FloatDST_1D var15 = null;
         System.gc();
         CommonUtils.sleep(5000L);
      }

      IOUtils.writeFFTBenchmarkResultsToFile("benchmarkFloatForwardDST_1D.txt", nthread, niter, doWarmup, doScaling, sizes1D, times_without_constructor, times_with_constructor);
   }

   public static void benchmarkForward_2D_input_1D() {
      double[] times_without_constructor = new double[nsize];
      double[] times_with_constructor = new double[nsize];

      for(int i = 0; i < nsize; ++i) {
         System.out.println("Forward DST 2D (input 1D) of size " + sizes2D[i] + " x " + sizes2D[i]);
         if (doWarmup) {
            FloatDST_2D dst2 = new FloatDST_2D(sizes2D[i], sizes2D[i]);
            FloatLargeArray x = new FloatLargeArray(sizes2D[i] * sizes2D[i], false);
            IOUtils.fillMatrix_2D(sizes2D[i], sizes2D[i], x);
            dst2.forward(x, doScaling);
            IOUtils.fillMatrix_2D(sizes2D[i], sizes2D[i], x);
            dst2.forward(x, doScaling);
         }

         long elapsedTime = System.nanoTime();
         FloatDST_2D dst2 = new FloatDST_2D(sizes2D[i], sizes2D[i]);
         times_with_constructor[i] = (double)(System.nanoTime() - elapsedTime) / (double)1000000.0F;
         FloatLargeArray x = new FloatLargeArray(sizes2D[i] * sizes2D[i], false);
         double min_time = (double)0.0F;

         for(int j = 0; j < niter; ++j) {
            IOUtils.fillMatrix_2D(sizes2D[i], sizes2D[i], x);
            elapsedTime = System.nanoTime();
            dst2.forward(x, doScaling);
            elapsedTime = System.nanoTime() - elapsedTime;
            if ((double)elapsedTime < min_time) {
               min_time = (double)elapsedTime;
            }
         }

         times_without_constructor[i] = min_time / (double)1000000.0F;
         times_with_constructor[i] += times_without_constructor[i];
         System.out.println("\tBest execution time without constructor: " + String.format("%.2f", times_without_constructor[i]) + " msec");
         System.out.println("\tBest execution time with constructor: " + String.format("%.2f", times_with_constructor[i]) + " msec");
         FloatLargeArray var11 = null;
         FloatDST_2D var15 = null;
         System.gc();
         CommonUtils.sleep(5000L);
      }

      IOUtils.writeFFTBenchmarkResultsToFile("benchmarkFloatForwardDST_2D_input_1D.txt", nthread, niter, doWarmup, doScaling, sizes2D, times_without_constructor, times_with_constructor);
   }

   public static void benchmarkForward_2D_input_2D() {
      double[] times_without_constructor = new double[nsize];
      double[] times_with_constructor = new double[nsize];

      for(int i = 0; i < nsize; ++i) {
         System.out.println("Forward DST 2D (input 2D) of size " + sizes2D[i] + " x " + sizes2D[i]);
         if (doWarmup) {
            FloatDST_2D dst2 = new FloatDST_2D(sizes2D[i], sizes2D[i]);
            float[][] x = new float[(int)sizes2D[i]][(int)sizes2D[i]];
            IOUtils.fillMatrix_2D(sizes2D[i], sizes2D[i], x);
            dst2.forward(x, doScaling);
            IOUtils.fillMatrix_2D(sizes2D[i], sizes2D[i], x);
            dst2.forward(x, doScaling);
         }

         long elapsedTime = System.nanoTime();
         FloatDST_2D dst2 = new FloatDST_2D(sizes2D[i], sizes2D[i]);
         times_with_constructor[i] = (double)(System.nanoTime() - elapsedTime) / (double)1000000.0F;
         float[][] x = new float[(int)sizes2D[i]][(int)sizes2D[i]];
         double min_time = (double)0.0F;

         for(int j = 0; j < niter; ++j) {
            IOUtils.fillMatrix_2D(sizes2D[i], sizes2D[i], x);
            elapsedTime = System.nanoTime();
            dst2.forward(x, doScaling);
            elapsedTime = System.nanoTime() - elapsedTime;
            if ((double)elapsedTime < min_time) {
               min_time = (double)elapsedTime;
            }
         }

         times_without_constructor[i] = min_time / (double)1000000.0F;
         times_with_constructor[i] += times_without_constructor[i];
         System.out.println("\tBest execution time without constructor: " + String.format("%.2f", times_without_constructor[i]) + " msec");
         System.out.println("\tBest execution time with constructor: " + String.format("%.2f", times_with_constructor[i]) + " msec");
         x = (float[][])null;
         FloatDST_2D var15 = null;
         System.gc();
         CommonUtils.sleep(5000L);
      }

      IOUtils.writeFFTBenchmarkResultsToFile("benchmarkFloatForwardDST_2D_input_2D.txt", nthread, niter, doWarmup, doScaling, sizes2D, times_without_constructor, times_with_constructor);
   }

   public static void benchmarkForward_3D_input_1D() {
      double[] times_without_constructor = new double[nsize];
      double[] times_with_constructor = new double[nsize];

      for(int i = 0; i < nsize; ++i) {
         System.out.println("Forward DST 3D (input 1D) of size " + sizes3D[i] + " x " + sizes3D[i] + " x " + sizes3D[i]);
         if (doWarmup) {
            FloatDST_3D dst3 = new FloatDST_3D(sizes3D[i], sizes3D[i], sizes3D[i]);
            FloatLargeArray x = new FloatLargeArray(sizes3D[i] * sizes3D[i] * sizes3D[i], false);
            IOUtils.fillMatrix_3D(sizes3D[i], sizes3D[i], sizes3D[i], x);
            dst3.forward(x, doScaling);
            IOUtils.fillMatrix_3D(sizes3D[i], sizes3D[i], sizes3D[i], x);
            dst3.forward(x, doScaling);
         }

         long elapsedTime = System.nanoTime();
         FloatDST_3D dst3 = new FloatDST_3D(sizes3D[i], sizes3D[i], sizes3D[i]);
         times_with_constructor[i] = (double)(System.nanoTime() - elapsedTime) / (double)1000000.0F;
         FloatLargeArray x = new FloatLargeArray(sizes3D[i] * sizes3D[i] * sizes3D[i], false);
         double min_time = (double)0.0F;

         for(int j = 0; j < niter; ++j) {
            IOUtils.fillMatrix_3D(sizes3D[i], sizes3D[i], sizes3D[i], x);
            elapsedTime = System.nanoTime();
            dst3.forward(x, doScaling);
            elapsedTime = System.nanoTime() - elapsedTime;
            if ((double)elapsedTime < min_time) {
               min_time = (double)elapsedTime;
            }
         }

         times_without_constructor[i] = min_time / (double)1000000.0F;
         times_with_constructor[i] += times_without_constructor[i];
         System.out.println("\tBest execution time without constructor: " + String.format("%.2f", times_without_constructor[i]) + " msec");
         System.out.println("\tBest execution time with constructor: " + String.format("%.2f", times_with_constructor[i]) + " msec");
         FloatLargeArray var11 = null;
         FloatDST_3D var15 = null;
         System.gc();
         CommonUtils.sleep(5000L);
      }

      IOUtils.writeFFTBenchmarkResultsToFile("benchmarkFloatForwardDST_3D_input_1D.txt", nthread, niter, doWarmup, doScaling, sizes3D, times_without_constructor, times_with_constructor);
   }

   public static void benchmarkForward_3D_input_3D() {
      double[] times_without_constructor = new double[nsize];
      double[] times_with_constructor = new double[nsize];

      for(int i = 0; i < nsize; ++i) {
         System.out.println("Forward DST 3D (input 3D) of size " + sizes3D[i] + " x " + sizes3D[i] + " x " + sizes3D[i]);
         if (doWarmup) {
            FloatDST_3D dst3 = new FloatDST_3D(sizes3D[i], sizes3D[i], sizes3D[i]);
            float[][][] x = new float[(int)sizes3D[i]][(int)sizes3D[i]][(int)sizes3D[i]];
            IOUtils.fillMatrix_3D(sizes3D[i], sizes3D[i], sizes3D[i], x);
            dst3.forward(x, doScaling);
            IOUtils.fillMatrix_3D(sizes3D[i], sizes3D[i], sizes3D[i], x);
            dst3.forward(x, doScaling);
         }

         long elapsedTime = System.nanoTime();
         FloatDST_3D dst3 = new FloatDST_3D(sizes3D[i], sizes3D[i], sizes3D[i]);
         times_with_constructor[i] = (double)(System.nanoTime() - elapsedTime) / (double)1000000.0F;
         float[][][] x = new float[(int)sizes3D[i]][(int)sizes3D[i]][(int)sizes3D[i]];
         double min_time = (double)0.0F;

         for(int j = 0; j < niter; ++j) {
            IOUtils.fillMatrix_3D(sizes3D[i], sizes3D[i], sizes3D[i], x);
            elapsedTime = System.nanoTime();
            dst3.forward(x, doScaling);
            elapsedTime = System.nanoTime() - elapsedTime;
            if ((double)elapsedTime < min_time) {
               min_time = (double)elapsedTime;
            }
         }

         times_without_constructor[i] = min_time / (double)1000000.0F;
         times_with_constructor[i] += times_without_constructor[i];
         System.out.println("\tBest execution time without constructor: " + String.format("%.2f", times_without_constructor[i]) + " msec");
         System.out.println("\tBest execution time with constructor: " + String.format("%.2f", times_with_constructor[i]) + " msec");
         x = (float[][][])null;
         FloatDST_3D var15 = null;
         System.gc();
         CommonUtils.sleep(5000L);
      }

      IOUtils.writeFFTBenchmarkResultsToFile("benchmarkFloatForwardDST_3D_input_3D.txt", nthread, niter, doWarmup, doScaling, sizes3D, times_without_constructor, times_with_constructor);
   }

   public static void main(String[] args) {
      parseArguments(args);
      benchmarkForward_1D();
      benchmarkForward_2D_input_1D();
      benchmarkForward_2D_input_2D();
      benchmarkForward_3D_input_1D();
      benchmarkForward_3D_input_3D();
      System.exit(0);
   }
}
