package org.jtransforms.dct;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.math3.util.FastMath;
import org.jtransforms.fft.DoubleFFT_1D;
import org.jtransforms.utils.CommonUtils;
import pl.edu.icm.jlargearrays.ConcurrencyUtils;
import pl.edu.icm.jlargearrays.DoubleLargeArray;
import pl.edu.icm.jlargearrays.LargeArray;
import pl.edu.icm.jlargearrays.LargeArrayUtils;
import pl.edu.icm.jlargearrays.LongLargeArray;

public class DoubleDCT_1D {
   private int n;
   private long nl;
   private int[] ip;
   private LongLargeArray ipl;
   private double[] w;
   private DoubleLargeArray wl;
   private int nw;
   private long nwl;
   private int nc;
   private long ncl;
   private boolean isPowerOfTwo = false;
   private DoubleFFT_1D fft;
   private static final double PI = Math.PI;
   private boolean useLargeArrays;

   public DoubleDCT_1D(long n) {
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
               this.w = new double[this.n * 5 / 4];
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
               this.fft = new DoubleFFT_1D(2L * n);
            }
         } else if (CommonUtils.isPowerOf2(n)) {
            this.isPowerOfTwo = true;
            this.ipl = new LongLargeArray((long)FastMath.ceil((double)(2L + (1L << (int)((long)(FastMath.log((double)(n / 2L) + (double)0.5F) / FastMath.log((double)2.0F)) / 2L)))));
            this.wl = new DoubleLargeArray(this.nl * 5L / 4L);
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
            this.fft = new DoubleFFT_1D(2L * n);
         }

      }
   }

   public void forward(double[] a, boolean scale) {
      this.forward(a, 0, scale);
   }

   public void forward(DoubleLargeArray a, boolean scale) {
      this.forward(a, 0L, scale);
   }

   public void forward(final double[] a, final int offa, boolean scale) {
      if (this.n != 1) {
         if (this.useLargeArrays) {
            this.forward(new DoubleLargeArray(a), (long)offa, scale);
         } else if (this.isPowerOfTwo) {
            double xr = a[offa + this.n - 1];

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
               CommonUtils.scale(this.n, FastMath.sqrt((double)2.0F / (double)this.n), a, offa, false);
               a[offa] /= FastMath.sqrt((double)2.0F);
            }
         } else {
            int twon = 2 * this.n;
            final double[] t = new double[twon];
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
                           a[idx] = DoubleDCT_1D.this.w[twoi] * t[twoi] - DoubleDCT_1D.this.w[twoi + 1] * t[twoi + 1];
                        }

                     }
                  });
               }

               try {
                  ConcurrencyUtils.waitForCompletion(futures);
               } catch (InterruptedException ex) {
                  Logger.getLogger(DoubleDCT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
               } catch (ExecutionException ex) {
                  Logger.getLogger(DoubleDCT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
               }
            } else {
               for(int i = 0; i < this.n; ++i) {
                  int twoi = 2 * i;
                  int idx = offa + i;
                  a[idx] = this.w[twoi] * t[twoi] - this.w[twoi + 1] * t[twoi + 1];
               }
            }

            if (scale) {
               CommonUtils.scale(this.n, (double)1.0F / FastMath.sqrt((double)twon), a, offa, false);
               a[offa] /= FastMath.sqrt((double)2.0F);
            }
         }

      }
   }

   public void forward(final DoubleLargeArray a, final long offa, boolean scale) {
      if (this.nl != 1L) {
         if (!this.useLargeArrays) {
            if (a.isLarge() || a.isConstant() || offa >= 2147483647L) {
               throw new IllegalArgumentException("The data array is too big.");
            }

            this.forward(a.getData(), (int)offa, scale);
         } else if (this.isPowerOfTwo) {
            double xr = a.getDouble(offa + this.nl - 1L);

            for(long j = this.nl - 2L; j >= 2L; j -= 2L) {
               a.setDouble(offa + j + 1L, a.getDouble(offa + j) - a.getDouble(offa + j - 1L));
               a.setDouble(offa + j, a.getDouble(offa + j) + a.getDouble(offa + j - 1L));
            }

            a.setDouble(offa + 1L, a.getDouble(offa) - xr);
            a.setDouble(offa, a.getDouble(offa) + xr);
            if (this.nl > 4L) {
               rftbsub(this.nl, a, offa, this.ncl, this.wl, this.nwl);
               CommonUtils.cftbsub(this.nl, a, offa, this.ipl, this.nwl, this.wl);
            } else if (this.nl == 4L) {
               CommonUtils.cftbsub(this.nl, a, offa, this.ipl, this.nwl, this.wl);
            }

            CommonUtils.dctsub(this.nl, a, offa, this.ncl, this.wl, this.nwl);
            if (scale) {
               CommonUtils.scale(this.nl, FastMath.sqrt((double)2.0F / (double)this.nl), a, offa, false);
               a.setDouble(offa, a.getDouble(offa) / FastMath.sqrt((double)2.0F));
            }
         } else {
            long twon = 2L * this.nl;
            final DoubleLargeArray t = new DoubleLargeArray(twon);
            LargeArrayUtils.arraycopy(a, offa, t, 0L, this.nl);
            int nthreads = ConcurrencyUtils.getNumberOfThreads();

            for(long i = this.nl; i < twon; ++i) {
               t.setDouble(i, t.getDouble(twon - i - 1L));
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
                           a.setDouble(idx, DoubleDCT_1D.this.wl.getDouble(twoi) * t.getDouble(twoi) - DoubleDCT_1D.this.wl.getDouble(twoi + 1L) * t.getDouble(twoi + 1L));
                        }

                     }
                  });
               }

               try {
                  ConcurrencyUtils.waitForCompletion(futures);
               } catch (InterruptedException ex) {
                  Logger.getLogger(DoubleDCT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
               } catch (ExecutionException ex) {
                  Logger.getLogger(DoubleDCT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
               }
            } else {
               for(long i = 0L; i < this.nl; ++i) {
                  long twoi = 2L * i;
                  long idx = offa + i;
                  a.setDouble(idx, this.wl.getDouble(twoi) * t.getDouble(twoi) - this.wl.getDouble(twoi + 1L) * t.getDouble(twoi + 1L));
               }
            }

            if (scale) {
               CommonUtils.scale(this.nl, (double)1.0F / FastMath.sqrt((double)twon), a, offa, false);
               a.setDouble(offa, a.getDouble(offa) / FastMath.sqrt((double)2.0F));
            }
         }

      }
   }

   public void inverse(double[] a, boolean scale) {
      this.inverse(a, 0, scale);
   }

   public void inverse(DoubleLargeArray a, boolean scale) {
      this.inverse(a, 0L, scale);
   }

   public void inverse(final double[] a, final int offa, boolean scale) {
      if (this.n != 1) {
         if (this.useLargeArrays) {
            this.inverse(new DoubleLargeArray(a), (long)offa, scale);
         } else if (this.isPowerOfTwo) {
            if (scale) {
               CommonUtils.scale(this.n, FastMath.sqrt((double)2.0F / (double)this.n), a, offa, false);
               a[offa] /= FastMath.sqrt((double)2.0F);
            }

            CommonUtils.dctsub(this.n, a, offa, this.nc, this.w, this.nw);
            if (this.n > 4) {
               CommonUtils.cftfsub(this.n, a, offa, this.ip, this.nw, this.w);
               rftfsub(this.n, a, offa, this.nc, this.w, this.nw);
            } else if (this.n == 4) {
               CommonUtils.cftfsub(this.n, a, offa, this.ip, this.nw, this.w);
            }

            double xr = a[offa] - a[offa + 1];
            a[offa] += a[offa + 1];

            for(int j = 2; j < this.n; j += 2) {
               a[offa + j - 1] = a[offa + j] - a[offa + j + 1];
               a[offa + j] += a[offa + j + 1];
            }

            a[offa + this.n - 1] = xr;
         } else {
            int twon = 2 * this.n;
            if (scale) {
               CommonUtils.scale(this.n, FastMath.sqrt((double)twon), a, offa, false);
               a[offa] *= FastMath.sqrt((double)2.0F);
            }

            final double[] t = new double[twon];
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
                           double elem = a[offa + i];
                           t[twoi] = DoubleDCT_1D.this.w[twoi] * elem;
                           t[twoi + 1] = -DoubleDCT_1D.this.w[twoi + 1] * elem;
                        }

                     }
                  });
               }

               try {
                  ConcurrencyUtils.waitForCompletion(futures);
               } catch (InterruptedException ex) {
                  Logger.getLogger(DoubleDCT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
               } catch (ExecutionException ex) {
                  Logger.getLogger(DoubleDCT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
               }
            } else {
               for(int i = 0; i < this.n; ++i) {
                  int twoi = 2 * i;
                  double elem = a[offa + i];
                  t[twoi] = this.w[twoi] * elem;
                  t[twoi + 1] = -this.w[twoi + 1] * elem;
               }
            }

            this.fft.realInverse(t, true);
            System.arraycopy(t, 0, a, offa, this.n);
         }

      }
   }

   public void inverse(final DoubleLargeArray a, final long offa, boolean scale) {
      if (this.nl != 1L) {
         if (!this.useLargeArrays) {
            if (a.isLarge() || a.isConstant() || offa >= 2147483647L) {
               throw new IllegalArgumentException("The data array is too big.");
            }

            this.inverse(a.getData(), (int)offa, scale);
         } else if (this.isPowerOfTwo) {
            if (scale) {
               CommonUtils.scale(this.nl, FastMath.sqrt((double)2.0F / (double)this.nl), a, offa, false);
               a.setDouble(offa, a.getDouble(offa) / FastMath.sqrt((double)2.0F));
            }

            CommonUtils.dctsub(this.nl, a, offa, this.ncl, this.wl, this.nwl);
            if (this.nl > 4L) {
               CommonUtils.cftfsub(this.nl, a, offa, this.ipl, this.nwl, this.wl);
               rftfsub(this.nl, a, offa, this.ncl, this.wl, this.nwl);
            } else if (this.nl == 4L) {
               CommonUtils.cftfsub(this.nl, a, offa, this.ipl, this.nwl, this.wl);
            }

            double xr = a.getDouble(offa) - a.getDouble(offa + 1L);
            a.setDouble(offa, a.getDouble(offa) + a.getDouble(offa + 1L));

            for(long j = 2L; j < this.nl; j += 2L) {
               a.setDouble(offa + j - 1L, a.getDouble(offa + j) - a.getDouble(offa + j + 1L));
               a.setDouble(offa + j, a.getDouble(offa + j) + a.getDouble(offa + j + 1L));
            }

            a.setDouble(offa + this.nl - 1L, xr);
         } else {
            long twon = 2L * this.nl;
            if (scale) {
               CommonUtils.scale(this.nl, FastMath.sqrt((double)twon), a, offa, false);
               a.setDouble(offa, a.getDouble(offa) * FastMath.sqrt((double)2.0F));
            }

            final DoubleLargeArray t = new DoubleLargeArray(twon);
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
                           double elem = a.getDouble(offa + i);
                           t.setDouble(twoi, DoubleDCT_1D.this.wl.getDouble(twoi) * elem);
                           t.setDouble(twoi + 1L, -DoubleDCT_1D.this.wl.getDouble(twoi + 1L) * elem);
                        }

                     }
                  });
               }

               try {
                  ConcurrencyUtils.waitForCompletion(futures);
               } catch (InterruptedException ex) {
                  Logger.getLogger(DoubleDCT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
               } catch (ExecutionException ex) {
                  Logger.getLogger(DoubleDCT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
               }
            } else {
               for(long i = 0L; i < this.nl; ++i) {
                  long twoi = 2L * i;
                  double elem = a.getDouble(offa + i);
                  t.setDouble(twoi, this.wl.getDouble(twoi) * elem);
                  t.setDouble(twoi + 1L, -this.wl.getDouble(twoi + 1L) * elem);
               }
            }

            this.fft.realInverse(t, true);
            LargeArrayUtils.arraycopy(t, 0L, a, offa, this.nl);
         }

      }
   }

   private double[] makect(int n) {
      int twon = 2 * n;
      double delta = Math.PI / (double)twon;
      double[] c = new double[twon];
      c[0] = (double)1.0F;

      for(int j = 1; j < n; ++j) {
         int idx = 2 * j;
         double deltaj = delta * (double)j;
         c[idx] = FastMath.cos(deltaj);
         c[idx + 1] = -FastMath.sin(deltaj);
      }

      return c;
   }

   private DoubleLargeArray makect(long n) {
      long twon = 2L * n;
      double delta = Math.PI / (double)twon;
      DoubleLargeArray c = new DoubleLargeArray(twon);
      c.setDouble(0L, (double)1.0F);

      for(long j = 1L; j < n; ++j) {
         long idx = 2L * j;
         double deltaj = delta * (double)j;
         c.setDouble(idx, FastMath.cos(deltaj));
         c.setDouble(idx + 1L, -FastMath.sin(deltaj));
      }

      return c;
   }

   private static void rftfsub(int n, double[] a, int offa, int nc, double[] c, int startc) {
      int m = n >> 1;
      int ks = 2 * nc / m;
      int kk = 0;

      for(int j = 2; j < m; j += 2) {
         int k = n - j;
         kk += ks;
         double wkr = (double)0.5F - c[startc + nc - kk];
         double wki = c[startc + kk];
         int idx1 = offa + j;
         int idx2 = offa + k;
         double xr = a[idx1] - a[idx2];
         double xi = a[idx1 + 1] + a[idx2 + 1];
         double yr = wkr * xr - wki * xi;
         double yi = wkr * xi + wki * xr;
         a[idx1] -= yr;
         a[idx1 + 1] -= yi;
         a[idx2] += yr;
         a[idx2 + 1] -= yi;
      }

   }

   private static void rftfsub(long n, DoubleLargeArray a, long offa, long nc, DoubleLargeArray c, long startc) {
      long m = n >> 1;
      long ks = 2L * nc / m;
      long kk = 0L;

      for(long j = 2L; j < m; j += 2L) {
         long k = n - j;
         kk += ks;
         double wkr = (double)0.5F - c.getDouble(startc + nc - kk);
         double wki = c.getDouble(startc + kk);
         long idx1 = offa + j;
         long idx2 = offa + k;
         double xr = a.getDouble(idx1) - a.getDouble(idx2);
         double xi = a.getDouble(idx1 + 1L) + a.getDouble(idx2 + 1L);
         double yr = wkr * xr - wki * xi;
         double yi = wkr * xi + wki * xr;
         a.setDouble(idx1, a.getDouble(idx1) - yr);
         a.setDouble(idx1 + 1L, a.getDouble(idx1 + 1L) - yi);
         a.setDouble(idx2, a.getDouble(idx2) + yr);
         a.setDouble(idx2 + 1L, a.getDouble(idx2 + 1L) - yi);
      }

   }

   private static void rftbsub(int n, double[] a, int offa, int nc, double[] c, int startc) {
      int m = n >> 1;
      int ks = 2 * nc / m;
      int kk = 0;

      for(int j = 2; j < m; j += 2) {
         int k = n - j;
         kk += ks;
         double wkr = (double)0.5F - c[startc + nc - kk];
         double wki = c[startc + kk];
         int idx1 = offa + j;
         int idx2 = offa + k;
         double xr = a[idx1] - a[idx2];
         double xi = a[idx1 + 1] + a[idx2 + 1];
         double yr = wkr * xr + wki * xi;
         double yi = wkr * xi - wki * xr;
         a[idx1] -= yr;
         a[idx1 + 1] -= yi;
         a[idx2] += yr;
         a[idx2 + 1] -= yi;
      }

   }

   private static void rftbsub(long n, DoubleLargeArray a, long offa, long nc, DoubleLargeArray c, long startc) {
      long m = n >> 1;
      long ks = 2L * nc / m;
      long kk = 0L;

      for(long j = 2L; j < m; j += 2L) {
         long k = n - j;
         kk += ks;
         double wkr = (double)0.5F - c.getDouble(startc + nc - kk);
         double wki = c.getDouble(startc + kk);
         long idx1 = offa + j;
         long idx2 = offa + k;
         double xr = a.getDouble(idx1) - a.getDouble(idx2);
         double xi = a.getDouble(idx1 + 1L) + a.getDouble(idx2 + 1L);
         double yr = wkr * xr + wki * xi;
         double yi = wkr * xi - wki * xr;
         a.setDouble(idx1, a.getDouble(idx1) - yr);
         a.setDouble(idx1 + 1L, a.getDouble(idx1 + 1L) - yi);
         a.setDouble(idx2, a.getDouble(idx2) + yr);
         a.setDouble(idx2 + 1L, a.getDouble(idx2 + 1L) - yi);
      }

   }
}
