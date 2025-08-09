package org.jtransforms.dht;

import java.util.Arrays;
import org.jtransforms.utils.CommonUtils;
import org.jtransforms.utils.IOUtils;
import pl.edu.icm.jlargearrays.ConcurrencyUtils;
import pl.edu.icm.jlargearrays.DoubleLargeArray;

public class BenchmarkDoubleDHT {
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

   private BenchmarkDoubleDHT() {
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
         System.out.println("Forward DHT 1D of size " + sizes1D[i]);
         if (doWarmup) {
            DoubleDHT_1D dht = new DoubleDHT_1D(sizes1D[i]);
            double[] x = new double[(int)sizes1D[i]];
            IOUtils.fillMatrix_1D(sizes1D[i], x);
            dht.forward(x);
            IOUtils.fillMatrix_1D(sizes1D[i], x);
            dht.forward(x);
         }

         long elapsedTime = System.nanoTime();
         DoubleDHT_1D dht = new DoubleDHT_1D(sizes1D[i]);
         times_with_constructor[i] = (double)(System.nanoTime() - elapsedTime) / (double)1000000.0F;
         double[] x = new double[(int)sizes1D[i]];
         double min_time = Double.MAX_VALUE;

         for(int j = 0; j < niter; ++j) {
            IOUtils.fillMatrix_1D(sizes1D[i], x);
            elapsedTime = System.nanoTime();
            dht.forward(x);
            elapsedTime = System.nanoTime() - elapsedTime;
            if ((double)elapsedTime < min_time) {
               min_time = (double)elapsedTime;
            }
         }

         times_without_constructor[i] = min_time / (double)1000000.0F;
         times_with_constructor[i] += times_without_constructor[i];
         System.out.println("\tBest execution time without constructor: " + String.format("%.2f", times_without_constructor[i]) + " msec");
         System.out.println("\tBest execution time with constructor: " + String.format("%.2f", times_with_constructor[i]) + " msec");
         double[] var11 = null;
         DoubleDHT_1D var15 = null;
         System.gc();
         CommonUtils.sleep(5000L);
      }

      IOUtils.writeFFTBenchmarkResultsToFile("benchmarkDoubleForwardDHT_1D.txt", nthread, niter, doWarmup, doScaling, sizes1D, times_without_constructor, times_with_constructor);
   }

   public static void benchmarkForward_2D_input_1D() {
      double[] times_without_constructor = new double[nsize];
      double[] times_with_constructor = new double[nsize];

      for(int i = 0; i < nsize; ++i) {
         System.out.println("Forward DHT 2D (input 1D) of size " + sizes2D[i] + " x " + sizes2D[i]);
         if (doWarmup) {
            DoubleDHT_2D dht2 = new DoubleDHT_2D(sizes2D[i], sizes2D[i]);
            DoubleLargeArray x = new DoubleLargeArray(sizes2D[i] * sizes2D[i], false);
            IOUtils.fillMatrix_2D(sizes2D[i], sizes2D[i], x);
            dht2.forward(x);
            IOUtils.fillMatrix_2D(sizes2D[i], sizes2D[i], x);
            dht2.forward(x);
         }

         long elapsedTime = System.nanoTime();
         DoubleDHT_2D dht2 = new DoubleDHT_2D(sizes2D[i], sizes2D[i]);
         times_with_constructor[i] = (double)(System.nanoTime() - elapsedTime) / (double)1000000.0F;
         DoubleLargeArray x = new DoubleLargeArray(sizes2D[i] * sizes2D[i], false);
         double min_time = Double.MAX_VALUE;

         for(int j = 0; j < niter; ++j) {
            IOUtils.fillMatrix_2D(sizes2D[i], sizes2D[i], x);
            elapsedTime = System.nanoTime();
            dht2.forward(x);
            elapsedTime = System.nanoTime() - elapsedTime;
            if ((double)elapsedTime < min_time) {
               min_time = (double)elapsedTime;
            }
         }

         times_without_constructor[i] = min_time / (double)1000000.0F;
         times_with_constructor[i] += times_without_constructor[i];
         System.out.println("\tBest execution time without constructor: " + String.format("%.2f", times_without_constructor[i]) + " msec");
         System.out.println("\tBest execution time with constructor: " + String.format("%.2f", times_with_constructor[i]) + " msec");
         DoubleLargeArray var11 = null;
         DoubleDHT_2D var15 = null;
         System.gc();
         CommonUtils.sleep(5000L);
      }

      IOUtils.writeFFTBenchmarkResultsToFile("benchmarkDoubleForwardDHT_2D_input_1D.txt", nthread, niter, doWarmup, doScaling, sizes2D, times_without_constructor, times_with_constructor);
   }

   public static void benchmarkForward_2D_input_2D() {
      double[] times_without_constructor = new double[nsize];
      double[] times_with_constructor = new double[nsize];

      for(int i = 0; i < nsize; ++i) {
         System.out.println("Forward DHT 2D (input 2D) of size " + sizes2D[i] + " x " + sizes2D[i]);
         if (doWarmup) {
            DoubleDHT_2D dht2 = new DoubleDHT_2D(sizes2D[i], sizes2D[i]);
            double[][] x = new double[(int)sizes2D[i]][(int)sizes2D[i]];
            IOUtils.fillMatrix_2D(sizes2D[i], sizes2D[i], x);
            dht2.forward(x);
            IOUtils.fillMatrix_2D(sizes2D[i], sizes2D[i], x);
            dht2.forward(x);
         }

         long elapsedTime = System.nanoTime();
         DoubleDHT_2D dht2 = new DoubleDHT_2D(sizes2D[i], sizes2D[i]);
         times_with_constructor[i] = (double)(System.nanoTime() - elapsedTime) / (double)1000000.0F;
         double[][] x = new double[(int)sizes2D[i]][(int)sizes2D[i]];
         double min_time = Double.MAX_VALUE;

         for(int j = 0; j < niter; ++j) {
            IOUtils.fillMatrix_2D(sizes2D[i], sizes2D[i], x);
            elapsedTime = System.nanoTime();
            dht2.forward(x);
            elapsedTime = System.nanoTime() - elapsedTime;
            if ((double)elapsedTime < min_time) {
               min_time = (double)elapsedTime;
            }
         }

         times_without_constructor[i] = min_time / (double)1000000.0F;
         times_with_constructor[i] += times_without_constructor[i];
         System.out.println("\tBest execution time without constructor: " + String.format("%.2f", times_without_constructor[i]) + " msec");
         System.out.println("\tBest execution time with constructor: " + String.format("%.2f", times_with_constructor[i]) + " msec");
         x = (double[][])null;
         DoubleDHT_2D var15 = null;
         System.gc();
         CommonUtils.sleep(5000L);
      }

      IOUtils.writeFFTBenchmarkResultsToFile("benchmarkDoubleForwardDHT_2D_input_2D.txt", nthread, niter, doWarmup, doScaling, sizes2D, times_without_constructor, times_with_constructor);
   }

   public static void benchmarkForward_3D_input_1D() {
      double[] times_without_constructor = new double[nsize];
      double[] times_with_constructor = new double[nsize];

      for(int i = 0; i < nsize; ++i) {
         System.out.println("Forward DHT 3D (input 1D) of size " + sizes3D[i] + " x " + sizes3D[i] + " x " + sizes3D[i]);
         if (doWarmup) {
            DoubleDHT_3D dht3 = new DoubleDHT_3D(sizes3D[i], sizes3D[i], sizes3D[i]);
            DoubleLargeArray x = new DoubleLargeArray(sizes3D[i] * sizes3D[i] * sizes3D[i], false);
            IOUtils.fillMatrix_3D(sizes3D[i], sizes3D[i], sizes3D[i], x);
            dht3.forward(x);
            IOUtils.fillMatrix_3D(sizes3D[i], sizes3D[i], sizes3D[i], x);
            dht3.forward(x);
         }

         long elapsedTime = System.nanoTime();
         DoubleDHT_3D dht3 = new DoubleDHT_3D(sizes3D[i], sizes3D[i], sizes3D[i]);
         times_with_constructor[i] = (double)(System.nanoTime() - elapsedTime) / (double)1000000.0F;
         DoubleLargeArray x = new DoubleLargeArray(sizes3D[i] * sizes3D[i] * sizes3D[i], false);
         double min_time = Double.MAX_VALUE;

         for(int j = 0; j < niter; ++j) {
            IOUtils.fillMatrix_3D(sizes3D[i], sizes3D[i], sizes3D[i], x);
            elapsedTime = System.nanoTime();
            dht3.forward(x);
            elapsedTime = System.nanoTime() - elapsedTime;
            if ((double)elapsedTime < min_time) {
               min_time = (double)elapsedTime;
            }
         }

         times_without_constructor[i] = min_time / (double)1000000.0F;
         times_with_constructor[i] += times_without_constructor[i];
         System.out.println("\tBest execution time without constructor: " + String.format("%.2f", times_without_constructor[i]) + " msec");
         System.out.println("\tBest execution time with constructor: " + String.format("%.2f", times_with_constructor[i]) + " msec");
         DoubleLargeArray var11 = null;
         DoubleDHT_3D var15 = null;
         System.gc();
         CommonUtils.sleep(5000L);
      }

      IOUtils.writeFFTBenchmarkResultsToFile("benchmarkDoubleForwardDHT_3D_input_1D.txt", nthread, niter, doWarmup, doScaling, sizes3D, times_without_constructor, times_with_constructor);
   }

   public static void benchmarkForward_3D_input_3D() {
      double[] times_without_constructor = new double[nsize];
      double[] times_with_constructor = new double[nsize];

      for(int i = 0; i < nsize; ++i) {
         System.out.println("Forward DHT 3D (input 3D) of size " + sizes3D[i] + " x " + sizes3D[i] + " x " + sizes3D[i]);
         if (doWarmup) {
            DoubleDHT_3D dht3 = new DoubleDHT_3D(sizes3D[i], sizes3D[i], sizes3D[i]);
            double[][][] x = new double[(int)sizes3D[i]][(int)sizes3D[i]][(int)sizes3D[i]];
            IOUtils.fillMatrix_3D(sizes3D[i], sizes3D[i], sizes3D[i], x);
            dht3.forward(x);
            IOUtils.fillMatrix_3D(sizes3D[i], sizes3D[i], sizes3D[i], x);
            dht3.forward(x);
         }

         long elapsedTime = System.nanoTime();
         DoubleDHT_3D dht3 = new DoubleDHT_3D(sizes3D[i], sizes3D[i], sizes3D[i]);
         times_with_constructor[i] = (double)(System.nanoTime() - elapsedTime) / (double)1000000.0F;
         double[][][] x = new double[(int)sizes3D[i]][(int)sizes3D[i]][(int)sizes3D[i]];
         double min_time = Double.MAX_VALUE;

         for(int j = 0; j < niter; ++j) {
            IOUtils.fillMatrix_3D(sizes3D[i], sizes3D[i], sizes3D[i], x);
            elapsedTime = System.nanoTime();
            dht3.forward(x);
            elapsedTime = System.nanoTime() - elapsedTime;
            if ((double)elapsedTime < min_time) {
               min_time = (double)elapsedTime;
            }
         }

         times_without_constructor[i] = min_time / (double)1000000.0F;
         times_with_constructor[i] += times_without_constructor[i];
         System.out.println("\tBest execution time without constructor: " + String.format("%.2f", times_without_constructor[i]) + " msec");
         System.out.println("\tBest execution time with constructor: " + String.format("%.2f", times_with_constructor[i]) + " msec");
         x = (double[][][])null;
         DoubleDHT_3D var15 = null;
         System.gc();
         CommonUtils.sleep(5000L);
      }

      IOUtils.writeFFTBenchmarkResultsToFile("benchmarkDoubleForwardDHT_3D_input_3D.txt", nthread, niter, doWarmup, doScaling, sizes3D, times_without_constructor, times_with_constructor);
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
