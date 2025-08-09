package org.jtransforms.fft;

import java.util.Arrays;
import org.apache.commons.math3.util.FastMath;
import org.jtransforms.utils.CommonUtils;
import org.jtransforms.utils.IOUtils;
import pl.edu.icm.jlargearrays.ConcurrencyUtils;
import pl.edu.icm.jlargearrays.FloatLargeArray;

public class BenchmarkFloatFFT {
   private static int nthread = 16;
   private static int niter = 100;
   private static int nsize = 8;
   private static int threadsBegin2D = 65536;
   private static int threadsBegin3D = 65536;
   private static boolean doWarmup = true;
   private static long[] sizes1D = new long[]{262144L, 524288L, 1048576L, 2097152L, 4194304L, 8388608L, 16777216L, 33554432L, 10368L, 27000L, 75600L, 165375L, 362880L, 1562500L, 3211264L, 6250000L};
   private static long[] sizes2D = new long[]{256L, 512L, 1024L, 2048L, 4096L, 8192L, 16384L, 32768L, 260L, 520L, 1050L, 1458L, 1960L, 2916L, 4116L, 5832L};
   private static long[] sizes3D = new long[]{16L, 32L, 64L, 128L, 256L, 512L, 1024L, 2048L, 5L, 17L, 30L, 95L, 180L, 270L, 324L, 420L};
   private static boolean doScaling = false;

   private BenchmarkFloatFFT() {
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

   public static void benchmarkComplexForward_1D() {
      double[] times_without_constructor = new double[nsize];
      double[] times_with_constructor = new double[nsize];

      for(int i = 0; i < nsize; ++i) {
         System.out.println("Complex forward FFT 1D of size " + sizes1D[i]);
         if (doWarmup) {
            FloatFFT_1D fft = new FloatFFT_1D(sizes1D[i]);
            float[] x = new float[(int)(2L * sizes1D[i])];
            IOUtils.fillMatrix_1D(2L * sizes1D[i], x);
            fft.complexForward(x);
            IOUtils.fillMatrix_1D(2L * sizes1D[i], x);
            fft.complexForward(x);
         }

         long elapsedTime = System.nanoTime();
         FloatFFT_1D fft = new FloatFFT_1D(sizes1D[i]);
         times_with_constructor[i] = (double)(System.nanoTime() - elapsedTime) / (double)1000000.0F;
         float[] x = new float[(int)(2L * sizes1D[i])];
         double min_time = Double.MAX_VALUE;

         for(int j = 0; j < niter; ++j) {
            IOUtils.fillMatrix_1D(2L * sizes1D[i], x);
            elapsedTime = System.nanoTime();
            fft.complexForward(x);
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
         FloatFFT_1D var15 = null;
         System.gc();
         CommonUtils.sleep(5000L);
      }

      IOUtils.writeFFTBenchmarkResultsToFile("benchmarkFloatComplexForwardFFT_1D.txt", nthread, niter, doWarmup, doScaling, sizes1D, times_without_constructor, times_with_constructor);
   }

   public static void benchmarkRealForward_1D() {
      double[] times_without_constructor = new double[nsize];
      double[] times_with_constructor = new double[nsize];

      for(int i = 0; i < nsize; ++i) {
         System.out.println("Real forward FFT 1D of size " + sizes1D[i]);
         if (doWarmup) {
            FloatFFT_1D fft = new FloatFFT_1D(sizes1D[i]);
            float[] x = new float[(int)(2L * sizes1D[i])];
            IOUtils.fillMatrix_1D(sizes1D[i], x);
            fft.realForwardFull(x);
            IOUtils.fillMatrix_1D(sizes1D[i], x);
            fft.realForwardFull(x);
         }

         long elapsedTime = System.nanoTime();
         FloatFFT_1D fft = new FloatFFT_1D(sizes1D[i]);
         times_with_constructor[i] = (double)(System.nanoTime() - elapsedTime) / (double)1000000.0F;
         float[] x = new float[(int)(2L * sizes1D[i])];
         double min_time = Double.MAX_VALUE;

         for(int j = 0; j < niter; ++j) {
            IOUtils.fillMatrix_1D(sizes1D[i], x);
            elapsedTime = System.nanoTime();
            fft.realForwardFull(x);
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
         FloatFFT_1D var15 = null;
         System.gc();
         CommonUtils.sleep(5000L);
      }

      IOUtils.writeFFTBenchmarkResultsToFile("benchmarkFloatRealForwardFFT_1D.txt", nthread, niter, doWarmup, doScaling, sizes1D, times_without_constructor, times_with_constructor);
   }

   public static void benchmarkComplexForward_2D_input_1D() {
      double[] times_without_constructor = new double[nsize];
      double[] times_with_constructor = new double[nsize];

      for(int i = 0; i < nsize; ++i) {
         System.out.println("Complex forward FFT 2D (input 1D) of size " + sizes2D[i] + " x " + sizes2D[i]);
         if (doWarmup) {
            FloatFFT_2D fft2 = new FloatFFT_2D(sizes2D[i], sizes2D[i]);
            FloatLargeArray x = new FloatLargeArray(sizes2D[i] * 2L * sizes2D[i], false);
            IOUtils.fillMatrix_2D(sizes2D[i], 2L * sizes2D[i], x);
            fft2.complexForward(x);
            IOUtils.fillMatrix_2D(sizes2D[i], 2L * sizes2D[i], x);
            fft2.complexForward(x);
         }

         long elapsedTime = System.nanoTime();
         FloatFFT_2D fft2 = new FloatFFT_2D(sizes2D[i], sizes2D[i]);
         times_with_constructor[i] = (double)(System.nanoTime() - elapsedTime) / (double)1000000.0F;
         FloatLargeArray x = new FloatLargeArray(sizes2D[i] * 2L * sizes2D[i], false);
         double min_time = Double.MAX_VALUE;
         int niter_local = niter;
         if (sizes2D[i] >= 8192L) {
            niter_local = FastMath.max(1, niter / 10);
         }

         for(int j = 0; j < niter_local; ++j) {
            IOUtils.fillMatrix_2D(sizes2D[i], 2L * sizes2D[i], x);
            elapsedTime = System.nanoTime();
            fft2.complexForward(x);
            elapsedTime = System.nanoTime() - elapsedTime;
            if ((double)elapsedTime < min_time) {
               min_time = (double)elapsedTime;
            }
         }

         times_without_constructor[i] = min_time / (double)1000000.0F;
         times_with_constructor[i] += times_without_constructor[i];
         System.out.println("\tBest execution time without constructor: " + String.format("%.2f", times_without_constructor[i]) + " msec");
         System.out.println("\tBest execution time with constructor: " + String.format("%.2f", times_with_constructor[i]) + " msec");
         FloatLargeArray var12 = null;
         FloatFFT_2D var16 = null;
         System.gc();
         CommonUtils.sleep(5000L);
      }

      IOUtils.writeFFTBenchmarkResultsToFile("benchmarkFloatComplexForwardFFT_2D_input_1D.txt", nthread, niter, doWarmup, doScaling, sizes2D, times_without_constructor, times_with_constructor);
   }

   public static void benchmarkComplexForward_2D_input_2D() {
      double[] times_without_constructor = new double[nsize];
      double[] times_with_constructor = new double[nsize];

      for(int i = 0; i < nsize; ++i) {
         System.out.println("Complex forward FFT 2D (input 2D) of size " + sizes2D[i] + " x " + sizes2D[i]);
         if (doWarmup) {
            FloatFFT_2D fft2 = new FloatFFT_2D(sizes2D[i], sizes2D[i]);
            float[][] x = new float[(int)sizes2D[i]][2 * (int)sizes2D[i]];
            IOUtils.fillMatrix_2D(sizes2D[i], 2L * sizes2D[i], x);
            fft2.complexForward(x);
            IOUtils.fillMatrix_2D(sizes2D[i], 2L * sizes2D[i], x);
            fft2.complexForward(x);
         }

         long elapsedTime = System.nanoTime();
         FloatFFT_2D fft2 = new FloatFFT_2D(sizes2D[i], sizes2D[i]);
         times_with_constructor[i] = (double)(System.nanoTime() - elapsedTime) / (double)1000000.0F;
         float[][] x = new float[(int)sizes2D[i]][2 * (int)sizes2D[i]];
         double min_time = Double.MAX_VALUE;

         for(int j = 0; j < niter; ++j) {
            IOUtils.fillMatrix_2D(sizes2D[i], 2L * sizes2D[i], x);
            elapsedTime = System.nanoTime();
            fft2.complexForward(x);
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
         FloatFFT_2D var15 = null;
         System.gc();
         CommonUtils.sleep(5000L);
      }

      IOUtils.writeFFTBenchmarkResultsToFile("benchmarkFloatComplexForwardFFT_2D_input_2D.txt", nthread, niter, doWarmup, doScaling, sizes2D, times_without_constructor, times_with_constructor);
   }

   public static void benchmarkRealForward_2D_input_1D() {
      double[] times_without_constructor = new double[nsize];
      double[] times_with_constructor = new double[nsize];

      for(int i = 0; i < nsize; ++i) {
         System.out.println("Real forward FFT 2D (input 1D) of size " + sizes2D[i] + " x " + sizes2D[i]);
         if (doWarmup) {
            FloatFFT_2D fft2 = new FloatFFT_2D(sizes2D[i], sizes2D[i]);
            FloatLargeArray x = new FloatLargeArray(sizes2D[i] * 2L * sizes2D[i], false);
            IOUtils.fillMatrix_2D(sizes2D[i], sizes2D[i], x);
            fft2.realForwardFull(x);
            IOUtils.fillMatrix_2D(sizes2D[i], sizes2D[i], x);
            fft2.realForwardFull(x);
         }

         long elapsedTime = System.nanoTime();
         FloatFFT_2D fft2 = new FloatFFT_2D(sizes2D[i], sizes2D[i]);
         times_with_constructor[i] = (double)(System.nanoTime() - elapsedTime) / (double)1000000.0F;
         FloatLargeArray x = new FloatLargeArray(sizes2D[i] * 2L * sizes2D[i], false);
         double min_time = Double.MAX_VALUE;

         for(int j = 0; j < niter; ++j) {
            IOUtils.fillMatrix_2D(sizes2D[i], sizes2D[i], x);
            elapsedTime = System.nanoTime();
            fft2.realForwardFull(x);
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
         FloatFFT_2D var15 = null;
         System.gc();
         CommonUtils.sleep(5000L);
      }

      IOUtils.writeFFTBenchmarkResultsToFile("benchmarkFloatRealForwardFFT_2D_input_1D.txt", nthread, niter, doWarmup, doScaling, sizes2D, times_without_constructor, times_with_constructor);
   }

   public static void benchmarkRealForward_2D_input_2D() {
      double[] times_without_constructor = new double[nsize];
      double[] times_with_constructor = new double[nsize];

      for(int i = 0; i < nsize; ++i) {
         System.out.println("Real forward FFT 2D (input 2D) of size " + sizes2D[i] + " x " + sizes2D[i]);
         if (doWarmup) {
            FloatFFT_2D fft2 = new FloatFFT_2D(sizes2D[i], sizes2D[i]);
            float[][] x = new float[(int)sizes2D[i]][2 * (int)sizes2D[i]];
            IOUtils.fillMatrix_2D(sizes2D[i], sizes2D[i], x);
            fft2.realForwardFull(x);
            IOUtils.fillMatrix_2D(sizes2D[i], sizes2D[i], x);
            fft2.realForwardFull(x);
         }

         long elapsedTime = System.nanoTime();
         FloatFFT_2D fft2 = new FloatFFT_2D(sizes2D[i], sizes2D[i]);
         times_with_constructor[i] = (double)(System.nanoTime() - elapsedTime) / (double)1000000.0F;
         float[][] x = new float[(int)sizes2D[i]][2 * (int)sizes2D[i]];
         double min_time = Double.MAX_VALUE;

         for(int j = 0; j < niter; ++j) {
            IOUtils.fillMatrix_2D(sizes2D[i], sizes2D[i], x);
            elapsedTime = System.nanoTime();
            fft2.realForwardFull(x);
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
         FloatFFT_2D var15 = null;
         System.gc();
         CommonUtils.sleep(5000L);
      }

      IOUtils.writeFFTBenchmarkResultsToFile("benchmarkFloatRealForwardFFT_2D_input_2D.txt", nthread, niter, doWarmup, doScaling, sizes2D, times_without_constructor, times_with_constructor);
   }

   public static void benchmarkComplexForward_3D_input_1D() {
      double[] times_without_constructor = new double[nsize];
      double[] times_with_constructor = new double[nsize];

      for(int i = 0; i < nsize; ++i) {
         System.out.println("Complex forward FFT 3D (input 1D) of size " + sizes3D[i] + " x " + sizes3D[i] + " x " + sizes3D[i]);
         if (doWarmup) {
            FloatFFT_3D fft3 = new FloatFFT_3D(sizes3D[i], sizes3D[i], sizes3D[i]);
            FloatLargeArray x = new FloatLargeArray(sizes3D[i] * sizes3D[i] * 2L * sizes3D[i], false);
            IOUtils.fillMatrix_3D(sizes3D[i], sizes3D[i], 2L * sizes3D[i], x);
            fft3.complexForward(x);
            IOUtils.fillMatrix_3D(sizes3D[i], sizes3D[i], 2L * sizes3D[i], x);
            fft3.complexForward(x);
         }

         long elapsedTime = System.nanoTime();
         FloatFFT_3D fft3 = new FloatFFT_3D(sizes3D[i], sizes3D[i], sizes3D[i]);
         times_with_constructor[i] = (double)(System.nanoTime() - elapsedTime) / (double)1000000.0F;
         FloatLargeArray x = new FloatLargeArray(sizes3D[i] * sizes3D[i] * 2L * sizes3D[i], false);
         double min_time = Double.MAX_VALUE;
         int niter_local = niter;
         if (sizes3D[i] >= 1024L) {
            niter_local = FastMath.max(1, niter / 10);
         }

         for(int j = 0; j < niter_local; ++j) {
            IOUtils.fillMatrix_3D(sizes3D[i], sizes3D[i], 2L * sizes3D[i], x);
            elapsedTime = System.nanoTime();
            fft3.complexForward(x);
            elapsedTime = System.nanoTime() - elapsedTime;
            if ((double)elapsedTime < min_time) {
               min_time = (double)elapsedTime;
            }
         }

         times_without_constructor[i] = min_time / (double)1000000.0F;
         times_with_constructor[i] += times_without_constructor[i];
         System.out.println("\tBest execution time without constructor: " + String.format("%.2f", times_without_constructor[i]) + " msec");
         System.out.println("\tBest execution time with constructor: " + String.format("%.2f", times_with_constructor[i]) + " msec");
         FloatLargeArray var12 = null;
         FloatFFT_3D var16 = null;
         System.gc();
         CommonUtils.sleep(5000L);
      }

      IOUtils.writeFFTBenchmarkResultsToFile("benchmarkFloatComplexForwardFFT_3D_input_1D.txt", nthread, niter, doWarmup, doScaling, sizes3D, times_without_constructor, times_with_constructor);
   }

   public static void benchmarkComplexForward_3D_input_3D() {
      double[] times_without_constructor = new double[nsize];
      double[] times_with_constructor = new double[nsize];

      for(int i = 0; i < nsize; ++i) {
         System.out.println("Complex forward FFT 3D (input 3D) of size " + sizes3D[i] + " x " + sizes3D[i] + " x " + sizes3D[i]);
         if (doWarmup) {
            FloatFFT_3D fft3 = new FloatFFT_3D(sizes3D[i], sizes3D[i], sizes3D[i]);
            float[][][] x = new float[(int)sizes3D[i]][(int)sizes3D[i]][2 * (int)sizes3D[i]];
            IOUtils.fillMatrix_3D(sizes3D[i], sizes3D[i], 2L * sizes3D[i], x);
            fft3.complexForward(x);
            IOUtils.fillMatrix_3D(sizes3D[i], sizes3D[i], 2L * sizes3D[i], x);
            fft3.complexForward(x);
         }

         long elapsedTime = System.nanoTime();
         FloatFFT_3D fft3 = new FloatFFT_3D(sizes3D[i], sizes3D[i], sizes3D[i]);
         times_with_constructor[i] = (double)(System.nanoTime() - elapsedTime) / (double)1000000.0F;
         float[][][] x = new float[(int)sizes3D[i]][(int)sizes3D[i]][2 * (int)sizes3D[i]];
         double min_time = Double.MAX_VALUE;

         for(int j = 0; j < niter; ++j) {
            IOUtils.fillMatrix_3D(sizes3D[i], sizes3D[i], 2L * sizes3D[i], x);
            elapsedTime = System.nanoTime();
            fft3.complexForward(x);
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
         FloatFFT_3D var15 = null;
         System.gc();
         CommonUtils.sleep(5000L);
      }

      IOUtils.writeFFTBenchmarkResultsToFile("benchmarkFloatComplexForwardFFT_3D_input_3D.txt", nthread, niter, doWarmup, doScaling, sizes3D, times_without_constructor, times_with_constructor);
   }

   public static void benchmarkRealForward_3D_input_1D() {
      double[] times_without_constructor = new double[nsize];
      double[] times_with_constructor = new double[nsize];

      for(int i = 0; i < nsize; ++i) {
         System.out.println("Real forward FFT 3D (input 1D) of size " + sizes3D[i] + " x " + sizes3D[i] + " x " + sizes3D[i]);
         if (doWarmup) {
            FloatFFT_3D fft3 = new FloatFFT_3D(sizes3D[i], sizes3D[i], sizes3D[i]);
            FloatLargeArray x = new FloatLargeArray(sizes3D[i] * sizes3D[i] * 2L * sizes3D[i], false);
            fft3.realForwardFull(x);
            IOUtils.fillMatrix_3D(sizes3D[i], sizes3D[i], sizes3D[i], x);
            fft3.realForwardFull(x);
         }

         long elapsedTime = System.nanoTime();
         FloatFFT_3D fft3 = new FloatFFT_3D(sizes3D[i], sizes3D[i], sizes3D[i]);
         times_with_constructor[i] = (double)(System.nanoTime() - elapsedTime) / (double)1000000.0F;
         FloatLargeArray x = new FloatLargeArray(sizes3D[i] * sizes3D[i] * 2L * sizes3D[i], false);
         double min_time = Double.MAX_VALUE;

         for(int j = 0; j < niter; ++j) {
            IOUtils.fillMatrix_3D(sizes3D[i], sizes3D[i], sizes3D[i], x);
            elapsedTime = System.nanoTime();
            fft3.realForwardFull(x);
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
         FloatFFT_3D var15 = null;
         System.gc();
         CommonUtils.sleep(5000L);
      }

      IOUtils.writeFFTBenchmarkResultsToFile("benchmarkFloatRealForwardFFT_3D_input_1D.txt", nthread, niter, doWarmup, doScaling, sizes3D, times_without_constructor, times_with_constructor);
   }

   public static void benchmarkRealForward_3D_input_3D() {
      double[] times_without_constructor = new double[nsize];
      double[] times_with_constructor = new double[nsize];

      for(int i = 0; i < nsize; ++i) {
         System.out.println("Real forward FFT 3D (input 3D) of size " + sizes3D[i] + " x " + sizes3D[i] + " x " + sizes3D[i]);
         if (doWarmup) {
            FloatFFT_3D fft3 = new FloatFFT_3D(sizes3D[i], sizes3D[i], sizes3D[i]);
            float[][][] x = new float[(int)sizes3D[i]][(int)sizes3D[i]][2 * (int)sizes3D[i]];
            IOUtils.fillMatrix_3D(sizes3D[i], sizes3D[i], sizes3D[i], x);
            fft3.realForwardFull(x);
            IOUtils.fillMatrix_3D(sizes3D[i], sizes3D[i], sizes3D[i], x);
            fft3.realForwardFull(x);
         }

         long elapsedTime = System.nanoTime();
         FloatFFT_3D fft3 = new FloatFFT_3D(sizes3D[i], sizes3D[i], sizes3D[i]);
         times_with_constructor[i] = (double)(System.nanoTime() - elapsedTime) / (double)1000000.0F;
         float[][][] x = new float[(int)sizes3D[i]][(int)sizes3D[i]][2 * (int)sizes3D[i]];
         double min_time = Double.MAX_VALUE;

         for(int j = 0; j < niter; ++j) {
            IOUtils.fillMatrix_3D(sizes3D[i], sizes3D[i], sizes3D[i], x);
            elapsedTime = System.nanoTime();
            fft3.realForwardFull(x);
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
         FloatFFT_3D var15 = null;
         System.gc();
         CommonUtils.sleep(5000L);
      }

      IOUtils.writeFFTBenchmarkResultsToFile("benchmarkFloatRealForwardFFT_3D_input_3D.txt", nthread, niter, doWarmup, doScaling, sizes3D, times_without_constructor, times_with_constructor);
   }

   public static void main(String[] args) {
      parseArguments(args);
      benchmarkComplexForward_1D();
      benchmarkRealForward_1D();
      benchmarkComplexForward_2D_input_1D();
      benchmarkComplexForward_2D_input_2D();
      benchmarkRealForward_2D_input_1D();
      benchmarkRealForward_2D_input_2D();
      benchmarkComplexForward_3D_input_1D();
      benchmarkComplexForward_3D_input_3D();
      benchmarkRealForward_3D_input_1D();
      benchmarkRealForward_3D_input_3D();
      System.exit(0);
   }
}
