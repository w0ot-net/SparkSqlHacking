package org.jtransforms.dht;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jtransforms.fft.FloatFFT_1D;
import org.jtransforms.utils.CommonUtils;
import pl.edu.icm.jlargearrays.ConcurrencyUtils;
import pl.edu.icm.jlargearrays.FloatLargeArray;
import pl.edu.icm.jlargearrays.LargeArray;
import pl.edu.icm.jlargearrays.LargeArrayUtils;

public class FloatDHT_1D {
   private final int n;
   private final long nl;
   private final FloatFFT_1D fft;
   private final boolean useLargeArrays;

   public FloatDHT_1D(long n) {
      this.n = (int)n;
      this.nl = n;
      this.useLargeArrays = CommonUtils.isUseLargeArrays() || n > (long)LargeArray.getMaxSizeOf32bitArray();
      this.fft = new FloatFFT_1D(n);
   }

   public void forward(float[] a) {
      this.forward(a, 0);
   }

   public void forward(FloatLargeArray a) {
      this.forward(a, 0L);
   }

   public void forward(final float[] a, final int offa) {
      if (this.n != 1) {
         if (this.useLargeArrays) {
            this.forward(new FloatLargeArray(a), (long)offa);
         } else {
            this.fft.realForward(a, offa);
            final float[] b = new float[this.n];
            System.arraycopy(a, offa, b, 0, this.n);
            int nd2 = this.n / 2;
            int nthreads = ConcurrencyUtils.getNumberOfThreads();
            if (nthreads > 1 && (long)nd2 > CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
               nthreads = 2;
               int k1 = nd2 / nthreads;
               Future<?>[] futures = new Future[nthreads];

               for(int i = 0; i < nthreads; ++i) {
                  final int firstIdx = 1 + i * k1;
                  final int lastIdx = i == nthreads - 1 ? nd2 : firstIdx + k1;
                  futures[i] = ConcurrencyUtils.submit(new Runnable() {
                     public void run() {
                        for(int i = firstIdx; i < lastIdx; ++i) {
                           int idx1 = 2 * i;
                           int idx2 = idx1 + 1;
                           a[offa + i] = b[idx1] - b[idx2];
                           a[offa + FloatDHT_1D.this.n - i] = b[idx1] + b[idx2];
                        }

                     }
                  });
               }

               try {
                  ConcurrencyUtils.waitForCompletion(futures);
               } catch (InterruptedException ex) {
                  Logger.getLogger(FloatDHT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
               } catch (ExecutionException ex) {
                  Logger.getLogger(FloatDHT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
               }
            } else {
               for(int i = 1; i < nd2; ++i) {
                  int idx1 = 2 * i;
                  int idx2 = idx1 + 1;
                  a[offa + i] = b[idx1] - b[idx2];
                  a[offa + this.n - i] = b[idx1] + b[idx2];
               }
            }

            if (this.n % 2 == 0) {
               a[offa + nd2] = b[1];
            } else {
               a[offa + nd2] = b[this.n - 1] - b[1];
               a[offa + nd2 + 1] = b[this.n - 1] + b[1];
            }
         }

      }
   }

   public void forward(final FloatLargeArray a, final long offa) {
      if (this.nl != 1L) {
         if (!this.useLargeArrays) {
            if (a.isLarge() || a.isConstant() || offa >= 2147483647L) {
               throw new IllegalArgumentException("The data array is too big.");
            }

            this.forward(a.getData(), (int)offa);
         } else {
            this.fft.realForward(a, offa);
            final FloatLargeArray b = new FloatLargeArray(this.nl, false);
            LargeArrayUtils.arraycopy(a, offa, b, 0L, this.nl);
            long nd2 = this.nl / 2L;
            int nthreads = ConcurrencyUtils.getNumberOfThreads();
            if (nthreads > 1 && nd2 > CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
               nthreads = 2;
               long k1 = nd2 / (long)nthreads;
               Future<?>[] futures = new Future[nthreads];

               for(int i = 0; i < nthreads; ++i) {
                  final long firstIdx = 1L + (long)i * k1;
                  final long lastIdx = i == nthreads - 1 ? nd2 : firstIdx + k1;
                  futures[i] = ConcurrencyUtils.submit(new Runnable() {
                     public void run() {
                        for(long i = firstIdx; i < lastIdx; ++i) {
                           long idx1 = 2L * i;
                           long idx2 = idx1 + 1L;
                           a.setFloat(offa + i, b.getFloat(idx1) - b.getFloat(idx2));
                           a.setFloat(offa + FloatDHT_1D.this.nl - i, b.getFloat(idx1) + b.getFloat(idx2));
                        }

                     }
                  });
               }

               try {
                  ConcurrencyUtils.waitForCompletion(futures);
               } catch (InterruptedException ex) {
                  Logger.getLogger(FloatDHT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
               } catch (ExecutionException ex) {
                  Logger.getLogger(FloatDHT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
               }
            } else {
               for(long i = 1L; i < nd2; ++i) {
                  long idx1 = 2L * i;
                  long idx2 = idx1 + 1L;
                  a.setFloat(offa + i, b.getFloat(idx1) - b.getFloat(idx2));
                  a.setFloat(offa + this.nl - i, b.getFloat(idx1) + b.getFloat(idx2));
               }
            }

            if (this.nl % 2L == 0L) {
               a.setFloat(offa + nd2, b.getFloat(1L));
            } else {
               a.setFloat(offa + nd2, b.getFloat(this.nl - 1L) - b.getFloat(1L));
               a.setFloat(offa + nd2 + 1L, b.getFloat(this.nl - 1L) + b.getFloat(1L));
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

   public void inverse(float[] a, int offa, boolean scale) {
      if (this.n != 1) {
         if (this.useLargeArrays) {
            this.inverse(new FloatLargeArray(a), (long)offa, scale);
         } else {
            this.forward(a, offa);
            if (scale) {
               CommonUtils.scale(this.n, 1.0F / (float)this.n, a, offa, false);
            }
         }

      }
   }

   public void inverse(FloatLargeArray a, long offa, boolean scale) {
      if (this.n != 1) {
         if (!this.useLargeArrays) {
            if (a.isLarge() || a.isConstant() || offa >= 2147483647L) {
               throw new IllegalArgumentException("The data array is too big.");
            }

            this.inverse(a.getData(), (int)offa, scale);
         } else {
            this.forward(a, offa);
            if (scale) {
               CommonUtils.scale((long)this.n, 1.0F / (float)this.n, a, offa, false);
            }
         }

      }
   }
}
