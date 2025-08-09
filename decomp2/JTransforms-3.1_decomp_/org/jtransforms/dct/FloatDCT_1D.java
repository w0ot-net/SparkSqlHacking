package org.jtransforms.dct;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.math3.util.FastMath;
import org.jtransforms.fft.FloatFFT_1D;
import org.jtransforms.utils.CommonUtils;
import pl.edu.icm.jlargearrays.ConcurrencyUtils;
import pl.edu.icm.jlargearrays.FloatLargeArray;
import pl.edu.icm.jlargearrays.LargeArray;
import pl.edu.icm.jlargearrays.LargeArrayUtils;
import pl.edu.icm.jlargearrays.LongLargeArray;

public class FloatDCT_1D {
   private int n;
   private long nl;
   private int[] ip;
   private LongLargeArray ipl;
   private float[] w;
   private FloatLargeArray wl;
   private int nw;
   private long nwl;
   private int nc;
   private long ncl;
   private boolean isPowerOfTwo = false;
   private FloatFFT_1D fft;
   private static final float PI = (float)Math.PI;
   private boolean useLargeArrays;

   public FloatDCT_1D(long n) {
      if (n < 1L) {
         throw new IllegalArgumentException("n must be greater than 0");
      } else {
         this.useLargeArrays = CommonUtils.isUseLargeArrays() || n > (long)LargeArray.getMaxSizeOf32bitArray();
         this.n = (int)n;
         this.nl = n;
         if (!this.useLargeArrays) {
            if (n > 268435456L) {
               throw new IllegalArgumentException("n must be smaller or equal to 268435456 when useLargeArrays argument is set to false");
            }

            if (CommonUtils.isPowerOf2(n)) {
               this.isPowerOfTwo = true;
               this.ip = new int[(int)FastMath.ceil((double)(2 + (1 << (int)(FastMath.log((double)(n / 2L) + (double)0.5F) / FastMath.log((double)2.0F)) / 2)))];
               this.w = new float[this.n * 5 / 4];
               this.nw = this.ip[0];
               if (n > (long)(this.nw << 2)) {
                  this.nw = this.n >> 2;
                  CommonUtils.makewt(this.nw, this.ip, this.w);
               }

               this.nc = this.ip[1];
               if (n > (long)this.nc) {
                  this.nc = this.n;
                  CommonUtils.makect(this.nc, this.w, this.nw, this.ip);
               }
            } else {
               this.w = this.makect(this.n);
               this.fft = new FloatFFT_1D(2L * n);
            }
         } else if (CommonUtils.isPowerOf2(n)) {
            this.isPowerOfTwo = true;
            this.ipl = new LongLargeArray((long)FastMath.ceil((double)(2L + (1L << (int)((long)(FastMath.log((double)(n / 2L) + (double)0.5F) / FastMath.log((double)2.0F)) / 2L)))));
            this.wl = new FloatLargeArray(this.nl * 5L / 4L);
            this.nwl = this.ipl.getLong(0L);
            if (n > this.nwl << 2) {
               this.nwl = this.nl >> 2;
               CommonUtils.makewt(this.nwl, this.ipl, this.wl);
            }

            this.ncl = this.ipl.getLong(1L);
            if (n > this.ncl) {
               this.ncl = this.nl;
               CommonUtils.makect(this.ncl, this.wl, this.nwl, this.ipl);
            }
         } else {
            this.wl = this.makect(n);
            this.fft = new FloatFFT_1D(2L * n);
         }

      }
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
         } else if (this.isPowerOfTwo) {
            float xr = a[offa + this.n - 1];

            for(int j = this.n - 2; j >= 2; j -= 2) {
               a[offa + j + 1] = a[offa + j] - a[offa + j - 1];
               a[offa + j] += a[offa + j - 1];
            }

            a[offa + 1] = a[offa] - xr;
            a[offa] += xr;
            if (this.n > 4) {
               rftbsub(this.n, a, offa, this.nc, this.w, this.nw);
               CommonUtils.cftbsub(this.n, a, offa, this.ip, this.nw, this.w);
            } else if (this.n == 4) {
               CommonUtils.cftbsub(this.n, a, offa, this.ip, this.nw, this.w);
            }

            CommonUtils.dctsub(this.n, a, offa, this.nc, this.w, this.nw);
            if (scale) {
               CommonUtils.scale(this.n, (float)FastMath.sqrt((double)2.0F / (double)this.n), a, offa, false);
               a[offa] /= (float)FastMath.sqrt((double)2.0F);
            }
         } else {
            int twon = 2 * this.n;
            final float[] t = new float[twon];
            System.arraycopy(a, offa, t, 0, this.n);
            int nthreads = ConcurrencyUtils.getNumberOfThreads();

            for(int i = this.n; i < twon; ++i) {
               t[i] = t[twon - i - 1];
            }

            this.fft.realForward(t);
            if (nthreads > 1 && (long)this.n > CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
               nthreads = 2;
               int k = this.n / nthreads;
               Future<?>[] futures = new Future[nthreads];

               for(int j = 0; j < nthreads; ++j) {
                  final int firstIdx = j * k;
                  final int lastIdx = j == nthreads - 1 ? this.n : firstIdx + k;
                  futures[j] = ConcurrencyUtils.submit(new Runnable() {
                     public void run() {
                        for(int i = firstIdx; i < lastIdx; ++i) {
                           int twoi = 2 * i;
                           int idx = offa + i;
                           a[idx] = FloatDCT_1D.this.w[twoi] * t[twoi] - FloatDCT_1D.this.w[twoi + 1] * t[twoi + 1];
                        }

                     }
                  });
               }

               try {
                  ConcurrencyUtils.waitForCompletion(futures);
               } catch (InterruptedException ex) {
                  Logger.getLogger(FloatDCT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
               } catch (ExecutionException ex) {
                  Logger.getLogger(FloatDCT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
               }
            } else {
               for(int i = 0; i < this.n; ++i) {
                  int twoi = 2 * i;
                  int idx = offa + i;
                  a[idx] = this.w[twoi] * t[twoi] - this.w[twoi + 1] * t[twoi + 1];
               }
            }

            if (scale) {
               CommonUtils.scale(this.n, 1.0F / (float)FastMath.sqrt((double)twon), a, offa, false);
               a[offa] /= (float)FastMath.sqrt((double)2.0F);
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
         } else if (this.isPowerOfTwo) {
            float xr = a.getFloat(offa + this.nl - 1L);

            for(long j = this.nl - 2L; j >= 2L; j -= 2L) {
               a.setFloat(offa + j + 1L, a.getFloat(offa + j) - a.getFloat(offa + j - 1L));
               a.setFloat(offa + j, a.getFloat(offa + j) + a.getFloat(offa + j - 1L));
            }

            a.setFloat(offa + 1L, a.getFloat(offa) - xr);
            a.setFloat(offa, a.getFloat(offa) + xr);
            if (this.nl > 4L) {
               rftbsub(this.nl, a, offa, this.ncl, this.wl, this.nwl);
               CommonUtils.cftbsub(this.nl, a, offa, this.ipl, this.nwl, this.wl);
            } else if (this.nl == 4L) {
               CommonUtils.cftbsub(this.nl, a, offa, this.ipl, this.nwl, this.wl);
            }

            CommonUtils.dctsub(this.nl, a, offa, this.ncl, this.wl, this.nwl);
            if (scale) {
               CommonUtils.scale(this.nl, (float)FastMath.sqrt((double)2.0F / (double)this.nl), a, offa, false);
               a.setFloat(offa, a.getFloat(offa) / (float)FastMath.sqrt((double)2.0F));
            }
         } else {
            long twon = 2L * this.nl;
            final FloatLargeArray t = new FloatLargeArray(twon);
            LargeArrayUtils.arraycopy(a, offa, t, 0L, this.nl);
            int nthreads = ConcurrencyUtils.getNumberOfThreads();

            for(long i = this.nl; i < twon; ++i) {
               t.setFloat(i, t.getFloat(twon - i - 1L));
            }

            this.fft.realForward(t);
            if (nthreads > 1 && this.nl > CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
               nthreads = 2;
               long k = this.nl / (long)nthreads;
               Future<?>[] futures = new Future[nthreads];

               for(int j = 0; j < nthreads; ++j) {
                  final long firstIdx = (long)j * k;
                  final long lastIdx = j == nthreads - 1 ? this.nl : firstIdx + k;
                  futures[j] = ConcurrencyUtils.submit(new Runnable() {
                     public void run() {
                        for(long i = firstIdx; i < lastIdx; ++i) {
                           long twoi = 2L * i;
                           long idx = offa + i;
                           a.setFloat(idx, FloatDCT_1D.this.wl.getFloat(twoi) * t.getFloat(twoi) - FloatDCT_1D.this.wl.getFloat(twoi + 1L) * t.getFloat(twoi + 1L));
                        }

                     }
                  });
               }

               try {
                  ConcurrencyUtils.waitForCompletion(futures);
               } catch (InterruptedException ex) {
                  Logger.getLogger(FloatDCT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
               } catch (ExecutionException ex) {
                  Logger.getLogger(FloatDCT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
               }
            } else {
               for(long i = 0L; i < this.nl; ++i) {
                  long twoi = 2L * i;
                  long idx = offa + i;
                  a.setFloat(idx, this.wl.getFloat(twoi) * t.getFloat(twoi) - this.wl.getFloat(twoi + 1L) * t.getFloat(twoi + 1L));
               }
            }

            if (scale) {
               CommonUtils.scale(this.nl, 1.0F / (float)FastMath.sqrt((double)twon), a, offa, false);
               a.setFloat(offa, a.getFloat(offa) / (float)FastMath.sqrt((double)2.0F));
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
         } else if (this.isPowerOfTwo) {
            if (scale) {
               CommonUtils.scale(this.n, (float)FastMath.sqrt((double)2.0F / (double)this.n), a, offa, false);
               a[offa] /= (float)FastMath.sqrt((double)2.0F);
            }

            CommonUtils.dctsub(this.n, a, offa, this.nc, this.w, this.nw);
            if (this.n > 4) {
               CommonUtils.cftfsub(this.n, a, offa, this.ip, this.nw, this.w);
               rftfsub(this.n, a, offa, this.nc, this.w, this.nw);
            } else if (this.n == 4) {
               CommonUtils.cftfsub(this.n, a, offa, this.ip, this.nw, this.w);
            }

            float xr = a[offa] - a[offa + 1];
            a[offa] += a[offa + 1];

            for(int j = 2; j < this.n; j += 2) {
               a[offa + j - 1] = a[offa + j] - a[offa + j + 1];
               a[offa + j] += a[offa + j + 1];
            }

            a[offa + this.n - 1] = xr;
         } else {
            int twon = 2 * this.n;
            if (scale) {
               CommonUtils.scale(this.n, (float)FastMath.sqrt((double)twon), a, offa, false);
               a[offa] *= (float)FastMath.sqrt((double)2.0F);
            }

            final float[] t = new float[twon];
            int nthreads = ConcurrencyUtils.getNumberOfThreads();
            if (nthreads > 1 && (long)this.n > CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
               nthreads = 2;
               int k = this.n / nthreads;
               Future<?>[] futures = new Future[nthreads];

               for(int j = 0; j < nthreads; ++j) {
                  final int firstIdx = j * k;
                  final int lastIdx = j == nthreads - 1 ? this.n : firstIdx + k;
                  futures[j] = ConcurrencyUtils.submit(new Runnable() {
                     public void run() {
                        for(int i = firstIdx; i < lastIdx; ++i) {
                           int twoi = 2 * i;
                           float elem = a[offa + i];
                           t[twoi] = FloatDCT_1D.this.w[twoi] * elem;
                           t[twoi + 1] = -FloatDCT_1D.this.w[twoi + 1] * elem;
                        }

                     }
                  });
               }

               try {
                  ConcurrencyUtils.waitForCompletion(futures);
               } catch (InterruptedException ex) {
                  Logger.getLogger(FloatDCT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
               } catch (ExecutionException ex) {
                  Logger.getLogger(FloatDCT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
               }
            } else {
               for(int i = 0; i < this.n; ++i) {
                  int twoi = 2 * i;
                  float elem = a[offa + i];
                  t[twoi] = this.w[twoi] * elem;
                  t[twoi + 1] = -this.w[twoi + 1] * elem;
               }
            }

            this.fft.realInverse(t, true);
            System.arraycopy(t, 0, a, offa, this.n);
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
         } else if (this.isPowerOfTwo) {
            if (scale) {
               CommonUtils.scale(this.nl, (float)FastMath.sqrt((double)2.0F / (double)this.nl), a, offa, false);
               a.setFloat(offa, a.getFloat(offa) / (float)FastMath.sqrt((double)2.0F));
            }

            CommonUtils.dctsub(this.nl, a, offa, this.ncl, this.wl, this.nwl);
            if (this.nl > 4L) {
               CommonUtils.cftfsub(this.nl, a, offa, this.ipl, this.nwl, this.wl);
               rftfsub(this.nl, a, offa, this.ncl, this.wl, this.nwl);
            } else if (this.nl == 4L) {
               CommonUtils.cftfsub(this.nl, a, offa, this.ipl, this.nwl, this.wl);
            }

            float xr = a.getFloat(offa) - a.getFloat(offa + 1L);
            a.setFloat(offa, a.getFloat(offa) + a.getFloat(offa + 1L));

            for(long j = 2L; j < this.nl; j += 2L) {
               a.setFloat(offa + j - 1L, a.getFloat(offa + j) - a.getFloat(offa + j + 1L));
               a.setFloat(offa + j, a.getFloat(offa + j) + a.getFloat(offa + j + 1L));
            }

            a.setFloat(offa + this.nl - 1L, xr);
         } else {
            long twon = 2L * this.nl;
            if (scale) {
               CommonUtils.scale(this.nl, (float)FastMath.sqrt((double)twon), a, offa, false);
               a.setFloat(offa, a.getFloat(offa) * (float)FastMath.sqrt((double)2.0F));
            }

            final FloatLargeArray t = new FloatLargeArray(twon);
            int nthreads = ConcurrencyUtils.getNumberOfThreads();
            if (nthreads > 1 && this.nl > CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
               nthreads = 2;
               long k = this.nl / (long)nthreads;
               Future<?>[] futures = new Future[nthreads];

               for(int j = 0; j < nthreads; ++j) {
                  final long firstIdx = (long)j * k;
                  final long lastIdx = j == nthreads - 1 ? this.nl : firstIdx + k;
                  futures[j] = ConcurrencyUtils.submit(new Runnable() {
                     public void run() {
                        for(long i = firstIdx; i < lastIdx; ++i) {
                           long twoi = 2L * i;
                           float elem = a.getFloat(offa + i);
                           t.setFloat(twoi, FloatDCT_1D.this.wl.getFloat(twoi) * elem);
                           t.setFloat(twoi + 1L, -FloatDCT_1D.this.wl.getFloat(twoi + 1L) * elem);
                        }

                     }
                  });
               }

               try {
                  ConcurrencyUtils.waitForCompletion(futures);
               } catch (InterruptedException ex) {
                  Logger.getLogger(FloatDCT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
               } catch (ExecutionException ex) {
                  Logger.getLogger(FloatDCT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
               }
            } else {
               for(long i = 0L; i < this.nl; ++i) {
                  long twoi = 2L * i;
                  float elem = a.getFloat(offa + i);
                  t.setFloat(twoi, this.wl.getFloat(twoi) * elem);
                  t.setFloat(twoi + 1L, -this.wl.getFloat(twoi + 1L) * elem);
               }
            }

            this.fft.realInverse(t, true);
            LargeArrayUtils.arraycopy(t, 0L, a, offa, this.nl);
         }

      }
   }

   private float[] makect(int n) {
      int twon = 2 * n;
      float delta = (float)Math.PI / (float)twon;
      float[] c = new float[twon];
      c[0] = 1.0F;

      for(int j = 1; j < n; ++j) {
         int idx = 2 * j;
         float deltaj = delta * (float)j;
         c[idx] = (float)FastMath.cos((double)deltaj);
         c[idx + 1] = -((float)FastMath.sin((double)deltaj));
      }

      return c;
   }

   private FloatLargeArray makect(long n) {
      long twon = 2L * n;
      float delta = (float)Math.PI / (float)twon;
      FloatLargeArray c = new FloatLargeArray(twon);
      c.setFloat(0L, 1.0F);

      for(long j = 1L; j < n; ++j) {
         long idx = 2L * j;
         float deltaj = delta * (float)j;
         c.setFloat(idx, (float)FastMath.cos((double)deltaj));
         c.setFloat(idx + 1L, -((float)FastMath.sin((double)deltaj)));
      }

      return c;
   }

   private static void rftfsub(int n, float[] a, int offa, int nc, float[] c, int startc) {
      int m = n >> 1;
      int ks = 2 * nc / m;
      int kk = 0;

      for(int j = 2; j < m; j += 2) {
         int k = n - j;
         kk += ks;
         float wkr = 0.5F - c[startc + nc - kk];
         float wki = c[startc + kk];
         int idx1 = offa + j;
         int idx2 = offa + k;
         float xr = a[idx1] - a[idx2];
         float xi = a[idx1 + 1] + a[idx2 + 1];
         float yr = wkr * xr - wki * xi;
         float yi = wkr * xi + wki * xr;
         a[idx1] -= yr;
         a[idx1 + 1] -= yi;
         a[idx2] += yr;
         a[idx2 + 1] -= yi;
      }

   }

   private static void rftfsub(long n, FloatLargeArray a, long offa, long nc, FloatLargeArray c, long startc) {
      long m = n >> 1;
      long ks = 2L * nc / m;
      long kk = 0L;

      for(long j = 2L; j < m; j += 2L) {
         long k = n - j;
         kk += ks;
         float wkr = 0.5F - c.getFloat(startc + nc - kk);
         float wki = c.getFloat(startc + kk);
         long idx1 = offa + j;
         long idx2 = offa + k;
         float xr = a.getFloat(idx1) - a.getFloat(idx2);
         float xi = a.getFloat(idx1 + 1L) + a.getFloat(idx2 + 1L);
         float yr = wkr * xr - wki * xi;
         float yi = wkr * xi + wki * xr;
         a.setFloat(idx1, a.getFloat(idx1) - yr);
         a.setFloat(idx1 + 1L, a.getFloat(idx1 + 1L) - yi);
         a.setFloat(idx2, a.getFloat(idx2) + yr);
         a.setFloat(idx2 + 1L, a.getFloat(idx2 + 1L) - yi);
      }

   }

   private static void rftbsub(int n, float[] a, int offa, int nc, float[] c, int startc) {
      int m = n >> 1;
      int ks = 2 * nc / m;
      int kk = 0;

      for(int j = 2; j < m; j += 2) {
         int k = n - j;
         kk += ks;
         float wkr = 0.5F - c[startc + nc - kk];
         float wki = c[startc + kk];
         int idx1 = offa + j;
         int idx2 = offa + k;
         float xr = a[idx1] - a[idx2];
         float xi = a[idx1 + 1] + a[idx2 + 1];
         float yr = wkr * xr + wki * xi;
         float yi = wkr * xi - wki * xr;
         a[idx1] -= yr;
         a[idx1 + 1] -= yi;
         a[idx2] += yr;
         a[idx2 + 1] -= yi;
      }

   }

   private static void rftbsub(long n, FloatLargeArray a, long offa, long nc, FloatLargeArray c, long startc) {
      long m = n >> 1;
      long ks = 2L * nc / m;
      long kk = 0L;

      for(long j = 2L; j < m; j += 2L) {
         long k = n - j;
         kk += ks;
         float wkr = 0.5F - c.getFloat(startc + nc - kk);
         float wki = c.getFloat(startc + kk);
         long idx1 = offa + j;
         long idx2 = offa + k;
         float xr = a.getFloat(idx1) - a.getFloat(idx2);
         float xi = a.getFloat(idx1 + 1L) + a.getFloat(idx2 + 1L);
         float yr = wkr * xr + wki * xi;
         float yi = wkr * xi - wki * xr;
         a.setFloat(idx1, a.getFloat(idx1) - yr);
         a.setFloat(idx1 + 1L, a.getFloat(idx1 + 1L) - yi);
         a.setFloat(idx2, a.getFloat(idx2) + yr);
         a.setFloat(idx2 + 1L, a.getFloat(idx2 + 1L) - yi);
      }

   }
}
