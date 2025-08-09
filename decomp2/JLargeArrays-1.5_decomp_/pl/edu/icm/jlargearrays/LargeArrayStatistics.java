package pl.edu.icm.jlargearrays;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.apache.commons.math3.util.FastMath;

public class LargeArrayStatistics {
   private LargeArrayStatistics() {
   }

   public static double min(final LargeArray a) {
      if (a != null && a.isNumeric() && a.getType() != LargeArrayType.COMPLEX_FLOAT && a.getType() != LargeArrayType.COMPLEX_DOUBLE) {
         if (a.isConstant()) {
            return a.getDouble(0L);
         } else {
            double min = a.getDouble(0L);
            long length = a.length();
            int nthreads = (int)FastMath.min(length, (long)ConcurrencyUtils.getNumberOfThreads());
            if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
               long k = length / (long)nthreads;
               Future[] threads = new Future[nthreads];

               for(int j = 0; j < nthreads; ++j) {
                  final long firstIdx = (long)j * k;
                  final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                  threads[j] = ConcurrencyUtils.submit(new Callable() {
                     public Double call() {
                        double min = a.getDouble(firstIdx);

                        for(long k = firstIdx + 1L; k < lastIdx; ++k) {
                           double elem = a.getDouble(k);
                           if (elem < min) {
                              min = elem;
                           }
                        }

                        return min;
                     }
                  });
               }

               try {
                  ConcurrencyUtils.waitForCompletion(threads);

                  for(int j = 0; j < nthreads; ++j) {
                     double res = (Double)threads[j].get();
                     if (res < min) {
                        min = res;
                     }
                  }
               } catch (ExecutionException | InterruptedException var14) {
                  for(long i = 1L; i < length; ++i) {
                     double elem = a.getDouble(i);
                     if (elem < min) {
                        min = elem;
                     }
                  }
               }
            } else {
               for(long i = 1L; i < length; ++i) {
                  double elem = a.getDouble(i);
                  if (elem < min) {
                     min = elem;
                  }
               }
            }

            return min;
         }
      } else {
         throw new IllegalArgumentException("a == null || !a.isNumeric() || a.getType() == LargeArrayType.COMPLEX_FLOAT || a.getType() == LargeArrayType.COMPLEX_DOUBLE");
      }
   }

   public static double max(final LargeArray a) {
      if (a != null && a.isNumeric() && a.getType() != LargeArrayType.COMPLEX_FLOAT && a.getType() != LargeArrayType.COMPLEX_DOUBLE) {
         if (a.isConstant()) {
            return a.getDouble(0L);
         } else {
            double max = a.getDouble(0L);
            long length = a.length();
            int nthreads = (int)FastMath.min(length, (long)ConcurrencyUtils.getNumberOfThreads());
            if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
               long k = length / (long)nthreads;
               Future[] threads = new Future[nthreads];

               for(int j = 0; j < nthreads; ++j) {
                  final long firstIdx = (long)j * k;
                  final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                  threads[j] = ConcurrencyUtils.submit(new Callable() {
                     public Double call() {
                        double max = a.getDouble(firstIdx);

                        for(long k = firstIdx + 1L; k < lastIdx; ++k) {
                           double elem = a.getDouble(k);
                           if (elem > max) {
                              max = elem;
                           }
                        }

                        return max;
                     }
                  });
               }

               try {
                  ConcurrencyUtils.waitForCompletion(threads);

                  for(int j = 0; j < nthreads; ++j) {
                     double res = (Double)threads[j].get();
                     if (res > max) {
                        max = res;
                     }
                  }
               } catch (ExecutionException | InterruptedException var14) {
                  for(long i = 1L; i < length; ++i) {
                     double elem = a.getDouble(i);
                     if (elem > max) {
                        max = elem;
                     }
                  }
               }
            } else {
               for(long i = 1L; i < length; ++i) {
                  double elem = a.getDouble(i);
                  if (elem > max) {
                     max = elem;
                  }
               }
            }

            return max;
         }
      } else {
         throw new IllegalArgumentException("a == null || !a.isNumeric() || a.getType() == LargeArrayType.COMPLEX_FLOAT || a.getType() == LargeArrayType.COMPLEX_DOUBLE");
      }
   }

   public static double sum(final LargeArray a) {
      if (a != null && a.isNumeric() && a.getType() != LargeArrayType.COMPLEX_FLOAT && a.getType() != LargeArrayType.COMPLEX_DOUBLE) {
         if (a.isConstant()) {
            return (double)a.length() * a.getDouble(0L);
         } else {
            double sum = (double)0.0F;
            long length = a.length();
            int nthreads = (int)FastMath.min(length, (long)ConcurrencyUtils.getNumberOfThreads());
            if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
               long k = length / (long)nthreads;
               Future[] threads = new Future[nthreads];

               for(int j = 0; j < nthreads; ++j) {
                  final long firstIdx = (long)j * k;
                  final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                  threads[j] = ConcurrencyUtils.submit(new Callable() {
                     public Double call() {
                        double sum = (double)0.0F;

                        for(long k = firstIdx; k < lastIdx; ++k) {
                           sum += a.getDouble(k);
                        }

                        return sum;
                     }
                  });
               }

               try {
                  ConcurrencyUtils.waitForCompletion(threads);

                  for(int j = 0; j < nthreads; ++j) {
                     double res = (Double)threads[j].get();
                     sum += res;
                  }
               } catch (ExecutionException | InterruptedException var14) {
                  for(long i = 0L; i < length; ++i) {
                     sum += a.getDouble(i);
                  }
               }
            } else {
               for(long i = 0L; i < length; ++i) {
                  sum += a.getDouble(i);
               }
            }

            return sum;
         }
      } else {
         throw new IllegalArgumentException("a == null || !a.isNumeric() || a.getType() == LargeArrayType.COMPLEX_FLOAT || a.getType() == LargeArrayType.COMPLEX_DOUBLE");
      }
   }

   public static double sumKahan(final LargeArray a) {
      if (a != null && a.isNumeric() && a.getType() != LargeArrayType.COMPLEX_FLOAT && a.getType() != LargeArrayType.COMPLEX_DOUBLE) {
         if (a.isConstant()) {
            return (double)a.length() * a.getDouble(0L);
         } else {
            double sum = (double)0.0F;
            double c = (double)0.0F;
            long length = a.length();
            int nthreads = (int)FastMath.min(length, (long)ConcurrencyUtils.getNumberOfThreads());
            if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
               long k = length / (long)nthreads;
               Future[] threads = new Future[nthreads];

               for(int j = 0; j < nthreads; ++j) {
                  final long firstIdx = (long)j * k;
                  final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                  threads[j] = ConcurrencyUtils.submit(new Callable() {
                     public Double call() {
                        double sum = (double)0.0F;
                        double c = (double)0.0F;

                        for(long k = firstIdx; k < lastIdx; ++k) {
                           double y = a.getDouble(k) - c;
                           double t = sum + y;
                           c = t - sum - y;
                           sum = t;
                        }

                        return sum;
                     }
                  });
               }

               try {
                  ConcurrencyUtils.waitForCompletion(threads);

                  for(int j = 0; j < nthreads; ++j) {
                     double res = (Double)threads[j].get();
                     sum += res;
                  }
               } catch (ExecutionException | InterruptedException var18) {
                  for(long i = 0L; i < length; ++i) {
                     double y = a.getDouble(i) - c;
                     double t = sum + y;
                     c = t - sum - y;
                     sum = t;
                  }
               }
            } else {
               for(long i = 0L; i < length; ++i) {
                  double y = a.getDouble(i) - c;
                  double t = sum + y;
                  c = t - sum - y;
                  sum = t;
               }
            }

            return sum;
         }
      } else {
         throw new IllegalArgumentException("a == null || !a.isNumeric() || a.getType() == LargeArrayType.COMPLEX_FLOAT || a.getType() == LargeArrayType.COMPLEX_DOUBLE");
      }
   }

   public static double avg(final LargeArray a) {
      if (a != null && a.isNumeric() && a.getType() != LargeArrayType.COMPLEX_FLOAT && a.getType() != LargeArrayType.COMPLEX_DOUBLE) {
         if (a.isConstant()) {
            return a.getDouble(0L);
         } else {
            double sum = (double)0.0F;
            long length = a.length();
            int nthreads = (int)FastMath.min(length, (long)ConcurrencyUtils.getNumberOfThreads());
            if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
               long k = length / (long)nthreads;
               Future[] threads = new Future[nthreads];

               for(int j = 0; j < nthreads; ++j) {
                  final long firstIdx = (long)j * k;
                  final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                  threads[j] = ConcurrencyUtils.submit(new Callable() {
                     public Double call() {
                        double sum = (double)0.0F;

                        for(long k = firstIdx; k < lastIdx; ++k) {
                           sum += a.getDouble(k);
                        }

                        return sum;
                     }
                  });
               }

               try {
                  ConcurrencyUtils.waitForCompletion(threads);

                  for(int j = 0; j < nthreads; ++j) {
                     double res = (Double)threads[j].get();
                     sum += res;
                  }
               } catch (ExecutionException | InterruptedException var14) {
                  for(long i = 0L; i < length; ++i) {
                     sum += a.getDouble(i);
                  }
               }
            } else {
               for(long i = 0L; i < length; ++i) {
                  sum += a.getDouble(i);
               }
            }

            return sum / (double)length;
         }
      } else {
         throw new IllegalArgumentException("a == null || !a.isNumeric() || a.getType() == LargeArrayType.COMPLEX_FLOAT || a.getType() == LargeArrayType.COMPLEX_DOUBLE");
      }
   }

   public static double avgKahan(final LargeArray a) {
      if (a != null && a.isNumeric() && a.getType() != LargeArrayType.COMPLEX_FLOAT && a.getType() != LargeArrayType.COMPLEX_DOUBLE) {
         if (a.isConstant()) {
            return a.getDouble(0L);
         } else {
            double sum = (double)0.0F;
            double c = (double)0.0F;
            long length = a.length();
            int nthreads = (int)FastMath.min(length, (long)ConcurrencyUtils.getNumberOfThreads());
            if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
               long k = length / (long)nthreads;
               Future[] threads = new Future[nthreads];

               for(int j = 0; j < nthreads; ++j) {
                  final long firstIdx = (long)j * k;
                  final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                  threads[j] = ConcurrencyUtils.submit(new Callable() {
                     public Double call() {
                        double sum = (double)0.0F;
                        double c = (double)0.0F;

                        for(long k = firstIdx; k < lastIdx; ++k) {
                           double y = a.getDouble(k) - c;
                           double t = sum + y;
                           c = t - sum - y;
                           sum = t;
                        }

                        return sum;
                     }
                  });
               }

               try {
                  ConcurrencyUtils.waitForCompletion(threads);

                  for(int j = 0; j < nthreads; ++j) {
                     double res = (Double)threads[j].get();
                     sum += res;
                  }
               } catch (ExecutionException | InterruptedException var18) {
                  for(long i = 0L; i < length; ++i) {
                     double y = a.getDouble(i) - c;
                     double t = sum + y;
                     c = t - sum - y;
                     sum = t;
                  }
               }
            } else {
               for(long i = 0L; i < length; ++i) {
                  double y = a.getDouble(i) - c;
                  double t = sum + y;
                  c = t - sum - y;
                  sum = t;
               }
            }

            return sum / (double)length;
         }
      } else {
         throw new IllegalArgumentException("a == null || !a.isNumeric() || a.getType() == LargeArrayType.COMPLEX_FLOAT || a.getType() == LargeArrayType.COMPLEX_DOUBLE");
      }
   }

   public static double std(final LargeArray a) {
      if (a != null && a.isNumeric() && a.getType() != LargeArrayType.COMPLEX_FLOAT && a.getType() != LargeArrayType.COMPLEX_DOUBLE) {
         if (a.isConstant()) {
            return (double)0.0F;
         } else {
            double sum = (double)0.0F;
            double sum2 = (double)0.0F;
            double c1 = (double)0.0F;
            double c2 = (double)0.0F;
            long length = a.length();
            if (length < 2L) {
               return Double.NaN;
            } else {
               int nthreads = (int)FastMath.min(length, (long)ConcurrencyUtils.getNumberOfThreads());
               if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
                  long k = length / (long)nthreads;
                  Future[] threads = new Future[nthreads];

                  for(int j = 0; j < nthreads; ++j) {
                     final long firstIdx = (long)j * k;
                     final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                     threads[j] = ConcurrencyUtils.submit(new Callable() {
                        public double[] call() {
                           double[] sum = new double[2];
                           double c1 = (double)0.0F;
                           double c2 = (double)0.0F;

                           for(long k = firstIdx; k < lastIdx; ++k) {
                              double elem = a.getDouble(k);
                              double y = elem - c1;
                              double t = sum[0] + y;
                              c1 = t - sum[0] - y;
                              sum[0] = t;
                              double y2 = elem * elem - c2;
                              double t2 = sum[1] + y2;
                              c2 = t2 - sum[1] - y2;
                              sum[1] = t2;
                           }

                           return sum;
                        }
                     });
                  }

                  try {
                     ConcurrencyUtils.waitForCompletion(threads);

                     for(int j = 0; j < nthreads; ++j) {
                        double[] res = (double[])threads[j].get();
                        sum += res[0];
                        sum2 += res[1];
                     }
                  } catch (ExecutionException | InterruptedException var28) {
                     for(long i = 0L; i < length; ++i) {
                        double elem = a.getDouble(i);
                        double y = elem - c1;
                        double t = sum + y;
                        c1 = t - sum - y;
                        sum = t;
                        double y2 = elem * elem - c2;
                        double t2 = sum2 + y2;
                        c2 = t2 - sum2 - y2;
                        sum2 = t2;
                     }
                  }
               } else {
                  for(long i = 0L; i < length; ++i) {
                     double elem = a.getDouble(i);
                     double y = elem - c1;
                     double t = sum + y;
                     c1 = t - sum - y;
                     sum = t;
                     double y2 = elem * elem - c2;
                     double t2 = sum2 + y2;
                     c2 = t2 - sum2 - y2;
                     sum2 = t2;
                  }
               }

               sum /= (double)length;
               sum2 /= (double)length;
               return FastMath.sqrt(FastMath.max((double)0.0F, sum2 - sum * sum));
            }
         }
      } else {
         throw new IllegalArgumentException("a == null || !a.isNumeric() || a.getType() == LargeArrayType.COMPLEX_FLOAT || a.getType() == LargeArrayType.COMPLEX_DOUBLE");
      }
   }

   public static double stdKahan(final LargeArray a) {
      if (a != null && a.isNumeric() && a.getType() != LargeArrayType.COMPLEX_FLOAT && a.getType() != LargeArrayType.COMPLEX_DOUBLE) {
         if (a.isConstant()) {
            return (double)0.0F;
         } else {
            double sum = (double)0.0F;
            double sum2 = (double)0.0F;
            long length = a.length();
            if (length < 2L) {
               return Double.NaN;
            } else {
               int nthreads = (int)FastMath.min(length, (long)ConcurrencyUtils.getNumberOfThreads());
               if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
                  long k = length / (long)nthreads;
                  Future[] threads = new Future[nthreads];

                  for(int j = 0; j < nthreads; ++j) {
                     final long firstIdx = (long)j * k;
                     final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                     threads[j] = ConcurrencyUtils.submit(new Callable() {
                        public double[] call() {
                           double[] sum = new double[2];

                           for(long k = firstIdx; k < lastIdx; ++k) {
                              double elem = a.getDouble(k);
                              sum[0] += elem;
                              sum[1] += elem * elem;
                           }

                           return sum;
                        }
                     });
                  }

                  try {
                     ConcurrencyUtils.waitForCompletion(threads);

                     for(int j = 0; j < nthreads; ++j) {
                        double[] res = (double[])threads[j].get();
                        sum += res[0];
                        sum2 += res[1];
                     }
                  } catch (ExecutionException | InterruptedException var16) {
                     for(long i = 0L; i < length; ++i) {
                        double elem = a.getDouble(i);
                        sum += elem;
                        sum2 += elem * elem;
                     }
                  }
               } else {
                  for(long i = 0L; i < length; ++i) {
                     double elem = a.getDouble(i);
                     sum += elem;
                     sum2 += elem * elem;
                  }
               }

               sum /= (double)length;
               sum2 /= (double)length;
               return FastMath.sqrt(FastMath.max((double)0.0F, sum2 - sum * sum));
            }
         }
      } else {
         throw new IllegalArgumentException("a == null || !a.isNumeric() || a.getType() == LargeArrayType.COMPLEX_FLOAT || a.getType() == LargeArrayType.COMPLEX_DOUBLE");
      }
   }
}
