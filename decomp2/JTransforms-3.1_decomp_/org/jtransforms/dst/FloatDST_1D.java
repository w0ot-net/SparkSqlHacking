package org.jtransforms.dst;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jtransforms.dct.FloatDCT_1D;
import org.jtransforms.utils.CommonUtils;
import pl.edu.icm.jlargearrays.ConcurrencyUtils;
import pl.edu.icm.jlargearrays.FloatLargeArray;
import pl.edu.icm.jlargearrays.LargeArray;

public class FloatDST_1D {
   private final int n;
   private final long nl;
   private final FloatDCT_1D dct;
   private final boolean useLargeArrays;

   public FloatDST_1D(long n) {
      this.n = (int)n;
      this.nl = n;
      this.useLargeArrays = CommonUtils.isUseLargeArrays() || n > (long)LargeArray.getMaxSizeOf32bitArray();
      this.dct = new FloatDCT_1D(n);
   }

   public void forward(float[] a, boolean scale) {
      this.forward(a, 0, scale);
   }

   public void forward(FloatLargeArray a, boolean scale) {
      this.forward(a, 0L, scale);
   }

   public void forward(final float[] a, final int offa, boolean scale) {
      if (this.n != 1) {
         if (this.useLargeArrays) {
            this.forward(new FloatLargeArray(a), (long)offa, scale);
         } else {
            int nd2 = this.n / 2;
            int startIdx = 1 + offa;
            int stopIdx = offa + this.n;

            for(int i = startIdx; i < stopIdx; i += 2) {
               a[i] = -a[i];
            }

            this.dct.forward(a, offa, scale);
            int nthreads = ConcurrencyUtils.getNumberOfThreads();
            if (nthreads > 1 && (long)nd2 > CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
               nthreads = 2;
               int k = nd2 / nthreads;
               Future<?>[] futures = new Future[nthreads];

               for(int j = 0; j < nthreads; ++j) {
                  final int firstIdx = j * k;
                  final int lastIdx = j == nthreads - 1 ? nd2 : firstIdx + k;
                  futures[j] = ConcurrencyUtils.submit(new Runnable() {
                     public void run() {
                        int idx0 = offa + FloatDST_1D.this.n - 1;

                        for(int i = firstIdx; i < lastIdx; ++i) {
                           int idx2 = offa + i;
                           float tmp = a[idx2];
                           int idx1 = idx0 - i;
                           a[idx2] = a[idx1];
                           a[idx1] = tmp;
                        }

                     }
                  });
               }

               try {
                  ConcurrencyUtils.waitForCompletion(futures);
               } catch (InterruptedException ex) {
                  Logger.getLogger(FloatDST_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
               } catch (ExecutionException ex) {
                  Logger.getLogger(FloatDST_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
               }
            } else {
               int idx0 = offa + this.n - 1;

               for(int i = 0; i < nd2; ++i) {
                  int idx2 = offa + i;
                  float tmp = a[idx2];
                  int idx1 = idx0 - i;
                  a[idx2] = a[idx1];
                  a[idx1] = tmp;
               }
            }
         }

      }
   }

   public void forward(final FloatLargeArray a, final long offa, boolean scale) {
      if (this.nl != 1L) {
         if (!this.useLargeArrays) {
            if (a.isLarge() || a.isConstant() || offa >= 2147483647L) {
               throw new IllegalArgumentException("The data array is too big.");
            }

            this.forward(a.getData(), (int)offa, scale);
         } else {
            long nd2 = this.nl / 2L;
            long startIdx = 1L + offa;
            long stopIdx = offa + this.nl;

            for(long i = startIdx; i < stopIdx; i += 2L) {
               a.setFloat(i, -a.getFloat(i));
            }

            this.dct.forward(a, offa, scale);
            int nthreads = ConcurrencyUtils.getNumberOfThreads();
            if (nthreads > 1 && nd2 > CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
               nthreads = 2;
               long k = nd2 / (long)nthreads;
               Future<?>[] futures = new Future[nthreads];

               for(int j = 0; j < nthreads; ++j) {
                  final long firstIdx = (long)j * k;
                  final long lastIdx = j == nthreads - 1 ? nd2 : firstIdx + k;
                  futures[j] = ConcurrencyUtils.submit(new Runnable() {
                     public void run() {
                        long idx0 = offa + FloatDST_1D.this.nl - 1L;

                        for(long i = firstIdx; i < lastIdx; ++i) {
                           long idx2 = offa + i;
                           float tmp = a.getFloat(idx2);
                           long idx1 = idx0 - i;
                           a.setFloat(idx2, a.getFloat(idx1));
                           a.setFloat(idx1, tmp);
                        }

                     }
                  });
               }

               try {
                  ConcurrencyUtils.waitForCompletion(futures);
               } catch (InterruptedException ex) {
                  Logger.getLogger(FloatDST_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
               } catch (ExecutionException ex) {
                  Logger.getLogger(FloatDST_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
               }
            } else {
               long idx0 = offa + this.nl - 1L;

               for(long i = 0L; i < nd2; ++i) {
                  long idx2 = offa + i;
                  float tmp = a.getFloat(idx2);
                  long idx1 = idx0 - i;
                  a.setFloat(idx2, a.getFloat(idx1));
                  a.setFloat(idx1, tmp);
               }
            }
         }

      }
   }

   public void inverse(float[] a, boolean scale) {
      this.inverse(a, 0, scale);
   }

   public void inverse(FloatLargeArray a, boolean scale) {
      this.inverse(a, 0L, scale);
   }

   public void inverse(final float[] a, final int offa, boolean scale) {
      if (this.n != 1) {
         if (this.useLargeArrays) {
            this.inverse(new FloatLargeArray(a), (long)offa, scale);
         } else {
            int nd2 = this.n / 2;
            int nthreads = ConcurrencyUtils.getNumberOfThreads();
            if (nthreads > 1 && (long)nd2 > CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
               nthreads = 2;
               int k = nd2 / nthreads;
               Future<?>[] futures = new Future[nthreads];

               for(int j = 0; j < nthreads; ++j) {
                  final int firstIdx = j * k;
                  final int lastIdx = j == nthreads - 1 ? nd2 : firstIdx + k;
                  futures[j] = ConcurrencyUtils.submit(new Runnable() {
                     public void run() {
                        int idx0 = offa + FloatDST_1D.this.n - 1;

                        for(int i = firstIdx; i < lastIdx; ++i) {
                           int idx2 = offa + i;
                           float tmp = a[idx2];
                           int idx1 = idx0 - i;
                           a[idx2] = a[idx1];
                           a[idx1] = tmp;
                        }

                     }
                  });
               }

               try {
                  ConcurrencyUtils.waitForCompletion(futures);
               } catch (InterruptedException ex) {
                  Logger.getLogger(FloatDST_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
               } catch (ExecutionException ex) {
                  Logger.getLogger(FloatDST_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
               }
            } else {
               int idx0 = offa + this.n - 1;

               for(int i = 0; i < nd2; ++i) {
                  float tmp = a[offa + i];
                  a[offa + i] = a[idx0 - i];
                  a[idx0 - i] = tmp;
               }
            }

            this.dct.inverse(a, offa, scale);
            int startidx = 1 + offa;
            int stopidx = offa + this.n;

            for(int i = startidx; i < stopidx; i += 2) {
               a[i] = -a[i];
            }
         }

      }
   }

   public void inverse(final FloatLargeArray a, final long offa, boolean scale) {
      if (this.nl != 1L) {
         if (!this.useLargeArrays) {
            if (a.isLarge() || a.isConstant() || offa >= 2147483647L) {
               throw new IllegalArgumentException("The data array is too big.");
            }

            this.inverse(a.getData(), (int)offa, scale);
         } else {
            long nd2 = this.nl / 2L;
            int nthreads = ConcurrencyUtils.getNumberOfThreads();
            if (nthreads > 1 && nd2 > CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
               nthreads = 2;
               long k = nd2 / (long)nthreads;
               Future<?>[] futures = new Future[nthreads];

               for(int j = 0; j < nthreads; ++j) {
                  final long firstIdx = (long)j * k;
                  final long lastIdx = j == nthreads - 1 ? nd2 : firstIdx + k;
                  futures[j] = ConcurrencyUtils.submit(new Runnable() {
                     public void run() {
                        long idx0 = offa + FloatDST_1D.this.nl - 1L;

                        for(long i = firstIdx; i < lastIdx; ++i) {
                           long idx2 = offa + i;
                           float tmp = a.getFloat(idx2);
                           long idx1 = idx0 - i;
                           a.setFloat(idx2, a.getFloat(idx1));
                           a.setFloat(idx1, tmp);
                        }

                     }
                  });
               }

               try {
                  ConcurrencyUtils.waitForCompletion(futures);
               } catch (InterruptedException ex) {
                  Logger.getLogger(FloatDST_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
               } catch (ExecutionException ex) {
                  Logger.getLogger(FloatDST_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
               }
            } else {
               long idx0 = offa + this.nl - 1L;

               for(long i = 0L; i < nd2; ++i) {
                  float tmp = a.getFloat(offa + i);
                  a.setFloat(offa + i, a.getFloat(idx0 - i));
                  a.setFloat(idx0 - i, tmp);
               }
            }

            this.dct.inverse(a, offa, scale);
            long startidx = 1L + offa;
            long stopidx = offa + this.nl;

            for(long i = startidx; i < stopidx; i += 2L) {
               a.setFloat(i, -a.getFloat(i));
            }
         }

      }
   }
}
