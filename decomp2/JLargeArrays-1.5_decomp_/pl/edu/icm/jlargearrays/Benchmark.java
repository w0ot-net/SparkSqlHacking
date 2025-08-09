package pl.edu.icm.jlargearrays;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Benchmark {
   private static void writeToFile(long[] sizes, int[] nthreads, double[][] results, String file) {
      try {
         BufferedWriter writer = new BufferedWriter(new FileWriter(file));
         writer.write(System.getProperty("os.name") + " " + System.getProperty("os.arch") + " " + System.getProperty("os.version"));
         writer.newLine();
         writer.write(System.getProperty("java.vendor") + " " + System.getProperty("java.version"));
         writer.newLine();
         writer.write("Available processors (cores): " + Runtime.getRuntime().availableProcessors());
         writer.newLine();
         writer.write("Total memory (bytes): " + Runtime.getRuntime().totalMemory());
         writer.newLine();
         writer.write("Number of threads: {");

         for(int th = 0; th < nthreads.length; ++th) {
            if (th < nthreads.length - 1) {
               writer.write(nthreads[th] + ",");
            } else {
               writer.write(nthreads[nthreads.length - 1] + "}");
            }
         }

         writer.newLine();
         writer.write("Sizes: {");

         for(int i = 0; i < sizes.length; ++i) {
            if (i < sizes.length - 1) {
               writer.write(sizes[i] + ",");
            } else {
               writer.write(sizes[sizes.length - 1] + "}");
            }
         }

         writer.newLine();
         writer.write("Timings: {");

         for(int th = 0; th < nthreads.length; ++th) {
            writer.write("{");
            if (th >= nthreads.length - 1) {
               for(int i = 0; i < sizes.length; ++i) {
                  if (i < sizes.length - 1) {
                     writer.write(results[th][i] + ",");
                  } else {
                     writer.write(results[th][i] + "}}");
                  }
               }
            } else {
               for(int i = 0; i < sizes.length; ++i) {
                  if (i < sizes.length - 1) {
                     writer.write(results[th][i] + ",");
                  } else {
                     writer.write(results[th][i] + "},");
                  }
               }

               writer.newLine();
            }
         }
      } catch (IOException ex) {
         ex.printStackTrace();
      }

   }

   public static double[][] benchmarkJavaArraysByteSequential(long[] sizes, int[] nthreads, int iters, String file) {
      for(int i = 0; i < sizes.length; ++i) {
         if (sizes[i] > 2147483643L) {
            return (double[][])null;
         }
      }

      double[][] results = new double[nthreads.length][sizes.length];
      System.out.println("Benchmarking java arrays (bytes, sequentual)");

      for(int th = 0; th < nthreads.length; ++th) {
         int nt = nthreads[th];
         Thread[] threads = new Thread[nt];
         System.out.println("\tNumber of threads = " + nt);

         for(int i = 0; i < sizes.length; ++i) {
            System.out.print("\tSize = " + sizes[i]);
            final byte[] a = new byte[(int)sizes[i]];
            double t = (double)System.nanoTime();

            for(int it = 0; it < iters; ++it) {
               long k = sizes[i] / (long)nt;

               for(int j = 0; j < nt; ++j) {
                  final int firstIdx = (int)((long)j * k);
                  final int lastIdx = (int)(j == nt - 1 ? sizes[i] : (long)firstIdx + k);
                  threads[j] = new Thread(new Runnable() {
                     public void run() {
                        for(int k = firstIdx; k < lastIdx; ++k) {
                           a[k] = 1;
                           ++a[k];
                        }

                     }
                  });
                  threads[j].start();
               }

               try {
                  for(int j = 0; j < nt; ++j) {
                     threads[j].join();
                     threads[j] = null;
                  }
               } catch (Exception ex) {
                  ex.printStackTrace();
               }
            }

            results[th][i] = ((double)System.nanoTime() - t) / (double)1.0E9F / (double)iters;
            System.out.println(" : " + String.format("%.7f sec", results[th][i]));
         }
      }

      writeToFile(sizes, nthreads, results, file);
      return results;
   }

   public static double[][] benchmarkJavaArraysDoubleSequential(long[] sizes, int[] nthreads, int iters, String file) {
      for(int i = 0; i < sizes.length; ++i) {
         if (sizes[i] > 2147483643L) {
            return (double[][])null;
         }
      }

      double[][] results = new double[nthreads.length][sizes.length];
      System.out.println("Benchmarking java arrays (doubles, sequentual)");

      for(int th = 0; th < nthreads.length; ++th) {
         int nt = nthreads[th];
         Thread[] threads = new Thread[nt];
         System.out.println("\tNumber of threads = " + nt);

         for(int i = 0; i < sizes.length; ++i) {
            System.out.print("\tSize = " + sizes[i]);
            final double[] a = new double[(int)sizes[i]];
            double t = (double)System.nanoTime();

            for(int it = 0; it < iters; ++it) {
               long k = sizes[i] / (long)nt;

               for(int j = 0; j < nt; ++j) {
                  final int firstIdx = (int)((long)j * k);
                  final int lastIdx = (int)(j == nt - 1 ? sizes[i] : (long)firstIdx + k);
                  threads[j] = new Thread(new Runnable() {
                     public void run() {
                        for(int k = firstIdx; k < lastIdx; ++k) {
                           a[k] = (double)1.0F;
                           int var10002 = a[k]++;
                        }

                     }
                  });
                  threads[j].start();
               }

               try {
                  for(int j = 0; j < nt; ++j) {
                     threads[j].join();
                     threads[j] = null;
                  }
               } catch (Exception ex) {
                  ex.printStackTrace();
               }
            }

            results[th][i] = ((double)System.nanoTime() - t) / (double)1.0E9F / (double)iters;
            System.out.println(" : " + String.format("%.7f sec", results[th][i]));
         }
      }

      writeToFile(sizes, nthreads, results, file);
      return results;
   }

   public static double[][] benchmarkJavaArraysByteRandom(long[] sizes, int[] nthreads, int iters, String file) {
      for(int i = 0; i < sizes.length; ++i) {
         if (sizes[i] > 2147483643L) {
            return (double[][])null;
         }
      }

      final int[] randIdx = new int[(int)sizes[sizes.length - 1]];
      double[][] results = new double[nthreads.length][sizes.length];
      Random r = new Random(0L);
      System.out.println("generating random indices.");
      int max = (int)sizes[sizes.length - 1];

      for(int i = 0; i < max; ++i) {
         randIdx[i] = (int)(r.nextDouble() * (double)(max - 1));
      }

      System.out.println("Benchmarking java arrays (bytes, random)");

      for(int th = 0; th < nthreads.length; ++th) {
         int nt = nthreads[th];
         Thread[] threads = new Thread[nt];
         System.out.println("\tNumber of threads = " + nt);

         for(int i = 0; i < sizes.length; ++i) {
            System.out.print("\tSize = " + sizes[i]);
            final byte[] a = new byte[(int)sizes[i]];
            final long size = sizes[i];
            double t = (double)System.nanoTime();

            for(int it = 0; it < iters; ++it) {
               long k = sizes[i] / (long)nt;

               for(int j = 0; j < nt; ++j) {
                  final int firstIdx = (int)((long)j * k);
                  final int lastIdx = (int)(j == nt - 1 ? size : (long)firstIdx + k);
                  threads[j] = new Thread(new Runnable() {
                     public void run() {
                        for(int k = firstIdx; k < lastIdx; ++k) {
                           int idx = (int)((long)randIdx[k] % size);
                           a[idx] = 1;
                           ++a[idx];
                        }

                     }
                  });
                  threads[j].start();
               }

               try {
                  for(int j = 0; j < nt; ++j) {
                     threads[j].join();
                     threads[j] = null;
                  }
               } catch (Exception ex) {
                  ex.printStackTrace();
               }
            }

            results[th][i] = ((double)System.nanoTime() - t) / (double)1.0E9F / (double)iters;
            System.out.println(" : " + String.format("%.7f sec", results[th][i]));
         }
      }

      writeToFile(sizes, nthreads, results, file);
      return results;
   }

   public static double[][] benchmarkJavaArraysDoubleRandom(long[] sizes, int[] nthreads, int iters, String file) {
      for(int i = 0; i < sizes.length; ++i) {
         if (sizes[i] > 2147483643L) {
            return (double[][])null;
         }
      }

      final int[] randIdx = new int[(int)sizes[sizes.length - 1]];
      double[][] results = new double[nthreads.length][sizes.length];
      Random r = new Random(0L);
      System.out.println("generating random indices.");
      int max = (int)sizes[sizes.length - 1];

      for(int i = 0; i < max; ++i) {
         randIdx[i] = (int)(r.nextDouble() * (double)(max - 1));
      }

      System.out.println("Benchmarking java arrays (double, random)");

      for(int th = 0; th < nthreads.length; ++th) {
         int nt = nthreads[th];
         Thread[] threads = new Thread[nt];
         System.out.println("\tNumber of threads = " + nt);

         for(int i = 0; i < sizes.length; ++i) {
            System.out.print("\tSize = " + sizes[i]);
            final double[] a = new double[(int)sizes[i]];
            final long size = sizes[i];
            double t = (double)System.nanoTime();

            for(int it = 0; it < iters; ++it) {
               long k = sizes[i] / (long)nt;

               for(int j = 0; j < nt; ++j) {
                  final int firstIdx = (int)((long)j * k);
                  final int lastIdx = (int)(j == nt - 1 ? size : (long)firstIdx + k);
                  threads[j] = new Thread(new Runnable() {
                     public void run() {
                        for(int k = firstIdx; k < lastIdx; ++k) {
                           int idx = (int)((long)randIdx[k] % size);
                           a[idx] = (double)1.0F;
                           int var10002 = a[idx]++;
                        }

                     }
                  });
                  threads[j].start();
               }

               try {
                  for(int j = 0; j < nt; ++j) {
                     threads[j].join();
                     threads[j] = null;
                  }
               } catch (Exception ex) {
                  ex.printStackTrace();
               }
            }

            results[th][i] = ((double)System.nanoTime() - t) / (double)1.0E9F / (double)iters;
            System.out.println(" : " + String.format("%.7f sec", results[th][i]));
         }
      }

      writeToFile(sizes, nthreads, results, file);
      return results;
   }

   public static double[][] benchmarkJLargeArraysByteSequentual(long[] sizes, int[] nthreads, int iters, String file) {
      double[][] results = new double[nthreads.length][sizes.length];
      System.out.println("Benchmarking JLargeArrays (bytes, sequentual)");

      for(int th = 0; th < nthreads.length; ++th) {
         int nt = nthreads[th];
         Thread[] threads = new Thread[nt];
         System.out.println("\tNumber of threads = " + nt);

         for(int i = 0; i < sizes.length; ++i) {
            System.out.print("\tSize = " + sizes[i]);
            final ByteLargeArray a = new ByteLargeArray(sizes[i]);
            double t = (double)System.nanoTime();

            for(int it = 0; it < iters; ++it) {
               long k = sizes[i] / (long)nt;

               for(int j = 0; j < nt; ++j) {
                  final long firstIdx = (long)j * k;
                  final long lastIdx = j == nt - 1 ? sizes[i] : firstIdx + k;
                  threads[j] = new Thread(new Runnable() {
                     public void run() {
                        for(long k = firstIdx; k < lastIdx; ++k) {
                           a.setByte(k, (byte)1);
                           a.setByte(k, (byte)(a.getByte(k) + 1));
                        }

                     }
                  });
                  threads[j].start();
               }

               try {
                  for(int j = 0; j < nt; ++j) {
                     threads[j].join();
                     threads[j] = null;
                  }
               } catch (Exception ex) {
                  ex.printStackTrace();
               }
            }

            results[th][i] = ((double)System.nanoTime() - t) / (double)1.0E9F / (double)iters;
            System.out.println(" : " + String.format("%.7f sec", results[th][i]));
         }
      }

      writeToFile(sizes, nthreads, results, file);
      return results;
   }

   public static double[][] benchmarkJLargeArraysByteSequentualNative(long[] sizes, int[] nthreads, int iters, String file) {
      LargeArray.setMaxSizeOf32bitArray(1);
      double[][] results = new double[nthreads.length][sizes.length];
      System.out.println("Benchmarking JLargeArrays (bytes, sequentual, native)");

      for(int th = 0; th < nthreads.length; ++th) {
         int nt = nthreads[th];
         Thread[] threads = new Thread[nt];
         System.out.println("\tNumber of threads = " + nt);

         for(int i = 0; i < sizes.length; ++i) {
            System.out.print("\tSize = " + sizes[i]);
            final ByteLargeArray a = new ByteLargeArray(sizes[i]);
            double t = (double)System.nanoTime();

            for(int it = 0; it < iters; ++it) {
               long k = sizes[i] / (long)nt;

               for(int j = 0; j < nt; ++j) {
                  final long firstIdx = (long)j * k;
                  final long lastIdx = j == nt - 1 ? sizes[i] : firstIdx + k;
                  threads[j] = new Thread(new Runnable() {
                     public void run() {
                        for(long k = firstIdx; k < lastIdx; ++k) {
                           a.setToNative(k, (byte)1);
                           a.setToNative(k, (byte)(a.getFromNative(k) + 1));
                        }

                     }
                  });
                  threads[j].start();
               }

               try {
                  for(int j = 0; j < nt; ++j) {
                     threads[j].join();
                     threads[j] = null;
                  }
               } catch (Exception ex) {
                  ex.printStackTrace();
               }
            }

            results[th][i] = ((double)System.nanoTime() - t) / (double)1.0E9F / (double)iters;
            System.out.println(" : " + String.format("%.7f sec", results[th][i]));
         }
      }

      writeToFile(sizes, nthreads, results, file);
      return results;
   }

   public static double[][] benchmarkJLargeArraysByteSequentual_safe(long[] sizes, int[] nthreads, int iters, String file) {
      double[][] results = new double[nthreads.length][sizes.length];
      System.out.println("Benchmarking JLargeArrays (bytes, sequentual, with bounds checking)");

      for(int th = 0; th < nthreads.length; ++th) {
         int nt = nthreads[th];
         Thread[] threads = new Thread[nt];
         System.out.println("\tNumber of threads = " + nt);

         for(int i = 0; i < sizes.length; ++i) {
            System.out.print("\tSize = " + sizes[i]);
            final ByteLargeArray a = new ByteLargeArray(sizes[i]);
            double t = (double)System.nanoTime();

            for(int it = 0; it < iters; ++it) {
               long k = sizes[i] / (long)nt;

               for(int j = 0; j < nt; ++j) {
                  final long firstIdx = (long)j * k;
                  final long lastIdx = j == nt - 1 ? sizes[i] : firstIdx + k;
                  threads[j] = new Thread(new Runnable() {
                     public void run() {
                        for(long k = firstIdx; k < lastIdx; ++k) {
                           a.setByte_safe(k, (byte)1);
                           a.setByte_safe(k, (byte)(a.getByte_safe(k) + 1));
                        }

                     }
                  });
                  threads[j].start();
               }

               try {
                  for(int j = 0; j < nt; ++j) {
                     threads[j].join();
                     threads[j] = null;
                  }
               } catch (Exception ex) {
                  ex.printStackTrace();
               }
            }

            results[th][i] = ((double)System.nanoTime() - t) / (double)1.0E9F / (double)iters;
            System.out.println(" : " + String.format("%.7f sec", results[th][i]));
         }
      }

      writeToFile(sizes, nthreads, results, file);
      return results;
   }

   public static double[][] benchmarkJLargeArraysDoubleSequentual(long[] sizes, int[] nthreads, int iters, String file) {
      double[][] results = new double[nthreads.length][sizes.length];
      System.out.println("Benchmarking JLargeArrays (doubles, sequentual)");

      for(int th = 0; th < nthreads.length; ++th) {
         int nt = nthreads[th];
         Thread[] threads = new Thread[nt];
         System.out.println("\tNumber of threads = " + nt);

         for(int i = 0; i < sizes.length; ++i) {
            System.out.print("\tSize = " + sizes[i]);
            final DoubleLargeArray a = new DoubleLargeArray(sizes[i]);
            double t = (double)System.nanoTime();

            for(int it = 0; it < iters; ++it) {
               long k = sizes[i] / (long)nt;

               for(int j = 0; j < nt; ++j) {
                  final long firstIdx = (long)j * k;
                  final long lastIdx = j == nt - 1 ? sizes[i] : firstIdx + k;
                  threads[j] = new Thread(new Runnable() {
                     public void run() {
                        for(long k = firstIdx; k < lastIdx; ++k) {
                           a.setDouble(k, (double)1.0F);
                           a.setDouble(k, a.getDouble(k) + (double)1.0F);
                        }

                     }
                  });
                  threads[j].start();
               }

               try {
                  for(int j = 0; j < nt; ++j) {
                     threads[j].join();
                     threads[j] = null;
                  }
               } catch (Exception ex) {
                  ex.printStackTrace();
               }
            }

            results[th][i] = ((double)System.nanoTime() - t) / (double)1.0E9F / (double)iters;
            System.out.println(" : " + String.format("%.7f sec", results[th][i]));
         }
      }

      writeToFile(sizes, nthreads, results, file);
      return results;
   }

   public static double[][] benchmarkJLargeArraysDoubleSequentualNative(long[] sizes, int[] nthreads, int iters, String file) {
      LargeArray.setMaxSizeOf32bitArray(1);
      double[][] results = new double[nthreads.length][sizes.length];
      System.out.println("Benchmarking JLargeArrays (doubles, sequentual, native)");

      for(int th = 0; th < nthreads.length; ++th) {
         int nt = nthreads[th];
         Thread[] threads = new Thread[nt];
         System.out.println("\tNumber of threads = " + nt);

         for(int i = 0; i < sizes.length; ++i) {
            System.out.print("\tSize = " + sizes[i]);
            final DoubleLargeArray a = new DoubleLargeArray(sizes[i]);
            double t = (double)System.nanoTime();

            for(int it = 0; it < iters; ++it) {
               long k = sizes[i] / (long)nt;

               for(int j = 0; j < nt; ++j) {
                  final long firstIdx = (long)j * k;
                  final long lastIdx = j == nt - 1 ? sizes[i] : firstIdx + k;
                  threads[j] = new Thread(new Runnable() {
                     public void run() {
                        for(long k = firstIdx; k < lastIdx; ++k) {
                           a.setToNative(k, (double)1.0F);
                           a.setToNative(k, a.getFromNative(k) + (double)1.0F);
                        }

                     }
                  });
                  threads[j].start();
               }

               try {
                  for(int j = 0; j < nt; ++j) {
                     threads[j].join();
                     threads[j] = null;
                  }
               } catch (Exception ex) {
                  ex.printStackTrace();
               }
            }

            results[th][i] = ((double)System.nanoTime() - t) / (double)1.0E9F / (double)iters;
            System.out.println(" : " + String.format("%.7f sec", results[th][i]));
         }
      }

      writeToFile(sizes, nthreads, results, file);
      return results;
   }

   public static double[][] benchmarkJLargeArraysByteRandom(long[] sizes, int[] nthreads, int iters, String file) {
      final int[] randIdx = new int[(int)sizes[sizes.length - 1]];
      double[][] results = new double[nthreads.length][sizes.length];
      Random r = new Random(0L);
      System.out.println("generating random indices.");
      int max = (int)sizes[sizes.length - 1];

      for(int i = 0; i < max; ++i) {
         randIdx[i] = (int)(r.nextDouble() * (double)(max - 1));
      }

      System.out.println("Benchmarking JLargeArrays (bytes, random)");

      for(int th = 0; th < nthreads.length; ++th) {
         int nt = nthreads[th];
         Thread[] threads = new Thread[nt];
         System.out.println("\tNumber of threads = " + nt);

         for(int i = 0; i < sizes.length; ++i) {
            System.out.print("\tSize = " + sizes[i]);
            final ByteLargeArray a = new ByteLargeArray(sizes[i]);
            final int size = (int)sizes[i];
            double t = (double)System.nanoTime();

            for(int it = 0; it < iters; ++it) {
               long k = sizes[i] / (long)nt;

               for(int j = 0; j < nt; ++j) {
                  final long firstIdx = (long)j * k;
                  final long lastIdx = j == nt - 1 ? sizes[i] : firstIdx + k;
                  threads[j] = new Thread(new Runnable() {
                     public void run() {
                        for(long k = firstIdx; k < lastIdx; ++k) {
                           long idx = (long)(randIdx[(int)k] % size);
                           a.setByte(idx, (byte)1);
                           a.setByte(idx, (byte)(a.getByte(idx) + 1));
                        }

                     }
                  });
                  threads[j].start();
               }

               try {
                  for(int j = 0; j < nt; ++j) {
                     threads[j].join();
                     threads[j] = null;
                  }
               } catch (Exception ex) {
                  ex.printStackTrace();
               }
            }

            results[th][i] = ((double)System.nanoTime() - t) / (double)1.0E9F / (double)iters;
            System.out.println(" : " + String.format("%.7f sec", results[th][i]));
         }
      }

      writeToFile(sizes, nthreads, results, file);
      return results;
   }

   public static double[][] benchmarkJLargeArraysDoubleRandom(long[] sizes, int[] nthreads, int iters, String file) {
      final int[] randIdx = new int[(int)sizes[sizes.length - 1]];
      double[][] results = new double[nthreads.length][sizes.length];
      Random r = new Random(0L);
      System.out.println("generating random indices.");
      int max = (int)sizes[sizes.length - 1];

      for(int i = 0; i < max; ++i) {
         randIdx[i] = (int)(r.nextDouble() * (double)(max - 1));
      }

      System.out.println("Benchmarking JLargeArrays (doubles, random)");

      for(int th = 0; th < nthreads.length; ++th) {
         int nt = nthreads[th];
         Thread[] threads = new Thread[nt];
         System.out.println("\tNumber of threads = " + nt);

         for(int i = 0; i < sizes.length; ++i) {
            System.out.print("\tSize = " + sizes[i]);
            final DoubleLargeArray a = new DoubleLargeArray(sizes[i]);
            final int size = (int)sizes[i];
            double t = (double)System.nanoTime();

            for(int it = 0; it < iters; ++it) {
               long k = sizes[i] / (long)nt;

               for(int j = 0; j < nt; ++j) {
                  final long firstIdx = (long)j * k;
                  final long lastIdx = j == nt - 1 ? sizes[i] : firstIdx + k;
                  threads[j] = new Thread(new Runnable() {
                     public void run() {
                        for(long k = firstIdx; k < lastIdx; ++k) {
                           long idx = (long)(randIdx[(int)k] % size);
                           a.setDouble(idx, (double)1.0F);
                           a.setDouble(idx, a.getDouble(idx) + (double)1.0F);
                        }

                     }
                  });
                  threads[j].start();
               }

               try {
                  for(int j = 0; j < nt; ++j) {
                     threads[j].join();
                     threads[j] = null;
                  }
               } catch (Exception ex) {
                  ex.printStackTrace();
               }
            }

            results[th][i] = ((double)System.nanoTime() - t) / (double)1.0E9F / (double)iters;
            System.out.println(" : " + String.format("%.7f sec", results[th][i]));
         }
      }

      writeToFile(sizes, nthreads, results, file);
      return results;
   }

   public static void benchmarkByteSequential(long[] sizes, int[] nthreads, int iters, String directory) {
      benchmarkJavaArraysDoubleSequential(sizes, nthreads, iters, directory + System.getProperty("file.separator") + "java_arrays_byte_sequential.txt");
      System.gc();
      benchmarkJLargeArraysByteSequentual(sizes, nthreads, iters, directory + System.getProperty("file.separator") + "jlargearrays_byte_sequentual.txt");
   }

   public static void benchmarkDoubleSequential(long[] sizes, int[] nthreads, int iters, String directory) {
      benchmarkJavaArraysDoubleSequential(sizes, nthreads, iters, directory + System.getProperty("file.separator") + "java_arrays_double_sequential.txt");
      System.gc();
      benchmarkJLargeArraysDoubleSequentual(sizes, nthreads, iters, directory + System.getProperty("file.separator") + "jlargearrays_double_sequentual.txt");
   }

   public static void benchmarkByteRandom(long[] sizes, int[] nthreads, int iters, String directory) {
      benchmarkJavaArraysByteRandom(sizes, nthreads, iters, directory + System.getProperty("file.separator") + "java_arrays_byte_random.txt");
      System.gc();
      benchmarkJLargeArraysByteRandom(sizes, nthreads, iters, directory + System.getProperty("file.separator") + "jlargearrays_byte_random.txt");
   }

   public static void benchmarkDoubleRandom(long[] sizes, int[] nthreads, int iters, String directory) {
      benchmarkJavaArraysDoubleRandom(sizes, nthreads, iters, directory + System.getProperty("file.separator") + "java_arrays_double_random.txt");
      System.gc();
      benchmarkJLargeArraysDoubleRandom(sizes, nthreads, iters, directory + System.getProperty("file.separator") + "jlargearrays_double_random.txt");
   }

   public static void benchmarkByteLargeArray() {
      System.out.println("Benchmarking ByteLargeArray.");
      long length = (long)Math.pow((double)2.0F, (double)32.0F);
      long start = System.nanoTime();
      ByteLargeArray array = new ByteLargeArray(length);
      System.out.println("Constructor time: " + (double)(System.nanoTime() - start) / (double)1.0E9F + " sec");
      int iters = 5;
      byte one = 1;

      for(int it = 0; it < iters; ++it) {
         start = System.nanoTime();

         for(long i = 0L; i < length; ++i) {
            array.getByte(i);
            array.setByte(i, one);
            array.setByte(i, (byte)(array.getByte(i) + one));
         }

         System.out.println("Computation time: " + (double)(System.nanoTime() - start) / (double)1.0E9F + "sec");
      }

   }

   public static void benchmarkByteLargeArrayInANewThread() {
      System.out.println("Benchmarking ByteLargeArray in a new thread.");
      final long length = (long)Math.pow((double)2.0F, (double)32.0F);
      long start = System.nanoTime();
      final ByteLargeArray array = new ByteLargeArray(length);
      System.out.println("Constructor time: " + (double)(System.nanoTime() - start) / (double)1.0E9F + " sec");
      int iters = 5;
      byte one = 1;

      for(int it = 0; it < 5; ++it) {
         start = System.nanoTime();
         Thread thread = new Thread(new Runnable() {
            public void run() {
               for(long k = 0L; k < length; ++k) {
                  array.setByte(k, (byte)1);
                  array.setByte(k, (byte)(array.getByte(k) + 1));
               }

            }
         });
         thread.start();

         try {
            thread.join();
         } catch (Exception ex) {
            ex.printStackTrace();
         }

         System.out.println("Computation time: " + (double)(System.nanoTime() - start) / (double)1.0E9F + " sec");
      }

   }

   public static void benchmarkFloatLargeArray() {
      System.out.println("Benchmarking FloatLargeArray.");
      long length = (long)Math.pow((double)2.0F, (double)32.0F);
      long start = System.nanoTime();
      FloatLargeArray array = new FloatLargeArray(length);
      System.out.println("Constructor time: " + (double)(System.nanoTime() - start) / (double)1.0E9F + " sec");
      int iters = 5;

      for(int it = 0; it < iters; ++it) {
         start = System.nanoTime();

         for(long i = 0L; i < length; ++i) {
            array.getFloat(i);
            array.setFloat(i, 1.0F);
            array.setFloat(i, array.getFloat(i) + 1.0F);
         }

         System.out.println("Computation time: " + (double)(System.nanoTime() - start) / (double)1.0E9F + "sec");
      }

   }

   public static void benchmarkFloatLargeArrayInANewThread() {
      System.out.println("Benchmarking FloatLargeArray in a new thread.");
      final long length = (long)Math.pow((double)2.0F, (double)32.0F);
      long start = System.nanoTime();
      final FloatLargeArray array = new FloatLargeArray(length);
      System.out.println("Constructor time: " + (double)(System.nanoTime() - start) / (double)1.0E9F + " sec");
      int iters = 5;

      for(int it = 0; it < 5; ++it) {
         start = System.nanoTime();
         Thread thread = new Thread(new Runnable() {
            public void run() {
               for(long k = 0L; k < length; ++k) {
                  array.setFloat(k, 1.0F);
                  array.setFloat(k, array.getFloat(k) + 1.0F);
               }

            }
         });
         thread.start();

         try {
            thread.join();
         } catch (Exception ex) {
            ex.printStackTrace();
         }

         System.out.println("Computation time: " + (double)(System.nanoTime() - start) / (double)1.0E9F + " sec");
      }

   }

   public static void benchmarkByteLargeArrayNative() {
      System.out.println("Benchmarking ByteLargeArray native.");
      long length = (long)Math.pow((double)2.0F, (double)32.0F);
      long start = System.nanoTime();
      ByteLargeArray array = new ByteLargeArray(length, false);
      System.out.println("Constructor time: " + (double)(System.nanoTime() - start) / (double)1.0E9F + " sec");
      int iters = 5;
      byte one = 1;
      if (array.isLarge()) {
         for(int it = 0; it < iters; ++it) {
            start = System.nanoTime();

            for(long i = 0L; i < length; ++i) {
               array.getFromNative(i);
               array.setToNative(i, one);
               array.setToNative(i, (byte)(array.getFromNative(i) + one));
            }

            System.out.println("Computation time: " + (double)(System.nanoTime() - start) / (double)1.0E9F + " sec");
         }
      }

   }

   public static void benchmarkByteLargeArrayNativeInANewThread() {
      System.out.println("Benchmarking ByteLargeArray native in a new thread.");
      final long length = (long)Math.pow((double)2.0F, (double)32.0F);
      long start = System.nanoTime();
      final ByteLargeArray array = new ByteLargeArray(length);
      System.out.println("Constructor time: " + (double)(System.nanoTime() - start) / (double)1.0E9F + " sec");
      int iters = 5;
      byte one = 1;

      for(int it = 0; it < 5; ++it) {
         start = System.nanoTime();
         Thread thread = new Thread(new Runnable() {
            public void run() {
               for(long k = 0L; k < length; ++k) {
                  array.setToNative(k, (byte)1);
                  array.setToNative(k, (byte)(array.getFromNative(k) + 1));
               }

            }
         });
         thread.start();

         try {
            thread.join();
         } catch (Exception ex) {
            ex.printStackTrace();
         }

         System.out.println("Computation time: " + (double)(System.nanoTime() - start) / (double)1.0E9F + " sec");
      }

   }

   public static void benchmarkFloatLargeArrayNative() {
      System.out.println("Benchmarking FloatLargeArray native.");
      long length = (long)Math.pow((double)2.0F, (double)32.0F);
      long start = System.nanoTime();
      FloatLargeArray array = new FloatLargeArray(length, false);
      System.out.println("Constructor time: " + (double)(System.nanoTime() - start) / (double)1.0E9F + " sec");
      int iters = 5;
      if (array.isLarge()) {
         for(int it = 0; it < iters; ++it) {
            start = System.nanoTime();

            for(long i = 0L; i < length; ++i) {
               array.getFromNative(i);
               array.setToNative(i, 1.0F);
               array.setToNative(i, array.getFromNative(i) + 1.0F);
            }

            System.out.println("Computation time: " + (double)(System.nanoTime() - start) / (double)1.0E9F + " sec");
         }
      }

   }

   public static void benchmarkFloatLargeArrayNativeInANewThread() {
      System.out.println("Benchmarking FloatLargeArray native in a new thread.");
      final long length = (long)Math.pow((double)2.0F, (double)32.0F);
      long start = System.nanoTime();
      final FloatLargeArray array = new FloatLargeArray(length);
      System.out.println("Constructor time: " + (double)(System.nanoTime() - start) / (double)1.0E9F + " sec");
      int iters = 5;

      for(int it = 0; it < 5; ++it) {
         start = System.nanoTime();
         Thread thread = new Thread(new Runnable() {
            public void run() {
               for(long k = 0L; k < length; ++k) {
                  array.setToNative(k, 1.0F);
                  array.setToNative(k, array.getFromNative(k) + 1.0F);
               }

            }
         });
         thread.start();

         try {
            thread.join();
         } catch (Exception ex) {
            ex.printStackTrace();
         }

         System.out.println("Computation time: " + (double)(System.nanoTime() - start) / (double)1.0E9F + " sec");
      }

   }

   public static void benchmarkArithmeticAdd() {
      System.out.println("Benchmarking addition of two ByteLargeArrays.");
      LargeArray.setMaxSizeOf32bitArray(1);
      long length = (long)Math.pow((double)2.0F, (double)27.0F);
      LargeArray a = LargeArrayUtils.generateRandom(LargeArrayType.BYTE, length);
      LargeArray b = LargeArrayUtils.generateRandom(LargeArrayType.BYTE, length);
      LargeArray al = LargeArrayUtils.convert(a, LargeArrayType.LONG);
      LargeArray bl = LargeArrayUtils.convert(b, LargeArrayType.LONG);
      int iters = 5;

      for(int t = 1; t <= 16; t += 2) {
         ConcurrencyUtils.setNumberOfThreads(t);
         LargeArrayArithmetics.add(a, b);
         LargeArrayArithmetics.add(a, b);
         long start = System.nanoTime();

         for(int it = 0; it < 5; ++it) {
            LargeArrayArithmetics.add(a, b);
         }

         System.out.println("Average computation time using " + t + " threads: " + (double)(System.nanoTime() - start) / (double)1.0E9F + " sec");
      }

      System.out.println("Benchmarking addition of two LongLargeArrays.");

      for(int t = 1; t <= 16; t += 2) {
         ConcurrencyUtils.setNumberOfThreads(t);
         LargeArrayArithmetics.add(al, bl);
         LargeArrayArithmetics.add(al, bl);
         long start = System.nanoTime();

         for(int it = 0; it < 5; ++it) {
            LargeArrayArithmetics.add(al, bl);
         }

         System.out.println("Average computation time using " + t + " threads: " + (double)(System.nanoTime() - start) / (double)1.0E9F + " sec");
      }

   }

   public static void benchmarkStatisticsAvg() {
      System.out.println("Benchmarking avgKahan (DoubleLargeArray of length = 2^28).");
      LargeArray.setMaxSizeOf32bitArray(1);
      long length = (long)Math.pow((double)2.0F, (double)28.0F);
      LargeArray a = LargeArrayUtils.generateRandom(LargeArrayType.DOUBLE, length);
      int iters = 5;

      for(int t = 1; t <= 16; ++t) {
         ConcurrencyUtils.setNumberOfThreads(t);
         double avg1 = LargeArrayStatistics.avgKahan(a);
         avg1 = LargeArrayStatistics.avgKahan(a);
         long start = System.nanoTime();

         for(int it = 0; it < 5; ++it) {
            avg1 = LargeArrayStatistics.avgKahan(a);
         }

         System.out.println("Average computation time using " + t + " threads: " + (double)(System.nanoTime() - start) / (double)1.0E9F + " sec");
      }

      System.out.println("Benchmarking avg (DoubleLargeArray of length = 2^28).");
      LargeArray.setMaxSizeOf32bitArray(1);

      for(int t = 1; t <= 16; ++t) {
         ConcurrencyUtils.setNumberOfThreads(t);
         double avg2 = LargeArrayStatistics.avg(a);
         avg2 = LargeArrayStatistics.avg(a);
         long start = System.nanoTime();

         for(int it = 0; it < 5; ++it) {
            avg2 = LargeArrayStatistics.avg(a);
         }

         System.out.println("Average computation time using " + t + " threads: " + (double)(System.nanoTime() - start) / (double)1.0E9F + " sec");
      }

   }

   public static void main(String[] args) {
      int smallSizesIters = 10;
      int initial_power_of_two_exp = 27;
      int final_power_of_two_exp = 32;
      int length = final_power_of_two_exp - initial_power_of_two_exp + 1;
      long[] smallSizes = new long[length];

      for(int i = 0; i < length; ++i) {
         if (initial_power_of_two_exp + i == 31) {
            smallSizes[i] = (long)Math.pow((double)2.0F, (double)31.0F) - 4L;
         } else {
            smallSizes[i] = (long)Math.pow((double)2.0F, (double)(initial_power_of_two_exp + i));
         }
      }

      int largeSizesIters = 2;
      initial_power_of_two_exp = 32;
      final_power_of_two_exp = 35;
      length = final_power_of_two_exp - initial_power_of_two_exp + 1;
      long[] largeSizes = new long[length];

      for(int i = 0; i < length; ++i) {
         largeSizes[i] = (long)Math.pow((double)2.0F, (double)(initial_power_of_two_exp + i));
      }

      int[] threads = new int[]{1, 2, 4, 8, 16};
      LargeArray.setMaxSizeOf32bitArray(1);
      benchmarkByteSequential(smallSizes, threads, 10, "/tmp/");
      benchmarkDoubleSequential(smallSizes, threads, 10, "/tmp/");
      benchmarkByteRandom(smallSizes, threads, 10, "/tmp/");
      benchmarkDoubleRandom(smallSizes, threads, 10, "/tmp/");
      System.exit(0);
   }
}
