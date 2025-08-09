package org.jtransforms.fft;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.math3.util.FastMath;
import org.jtransforms.utils.CommonUtils;
import pl.edu.icm.jlargearrays.ConcurrencyUtils;
import pl.edu.icm.jlargearrays.FloatLargeArray;
import pl.edu.icm.jlargearrays.LargeArray;
import pl.edu.icm.jlargearrays.LargeArrayUtils;
import pl.edu.icm.jlargearrays.LongLargeArray;

public final class FloatFFT_1D {
   private int n;
   private long nl;
   private int nBluestein;
   private long nBluesteinl;
   private int[] ip;
   private LongLargeArray ipl;
   private float[] w;
   private FloatLargeArray wl;
   private int nw;
   private long nwl;
   private int nc;
   private long ncl;
   private float[] wtable;
   private FloatLargeArray wtablel;
   private float[] wtable_r;
   private FloatLargeArray wtable_rl;
   private float[] bk1;
   private FloatLargeArray bk1l;
   private float[] bk2;
   private FloatLargeArray bk2l;
   private Plans plan;
   private boolean useLargeArrays;
   private static final int[] factors = new int[]{4, 2, 3, 5};
   private static final float PI = (float)Math.PI;
   private static final float TWO_PI = ((float)Math.PI * 2F);

   public FloatFFT_1D(long n) {
      if (n < 1L) {
         throw new IllegalArgumentException("n must be greater than 0");
      } else {
         this.useLargeArrays = CommonUtils.isUseLargeArrays() || 2L * n > (long)LargeArray.getMaxSizeOf32bitArray();
         this.n = (int)n;
         this.nl = n;
         if (!this.useLargeArrays) {
            if (!CommonUtils.isPowerOf2(n)) {
               if (CommonUtils.getReminder(n, factors) >= 211L) {
                  this.plan = FloatFFT_1D.Plans.BLUESTEIN;
                  this.nBluestein = CommonUtils.nextPow2(this.n * 2 - 1);
                  this.bk1 = new float[2 * this.nBluestein];
                  this.bk2 = new float[2 * this.nBluestein];
                  this.ip = new int[2 + (int)FastMath.ceil((double)(2 + (1 << (int)(FastMath.log((double)((float)this.nBluestein + 0.5F)) / FastMath.log((double)2.0F)) / 2)))];
                  this.w = new float[this.nBluestein];
                  int twon = 2 * this.nBluestein;
                  this.nw = twon >> 2;
                  CommonUtils.makewt(this.nw, this.ip, this.w);
                  this.nc = this.nBluestein >> 2;
                  CommonUtils.makect(this.nc, this.w, this.nw, this.ip);
                  this.bluesteini();
               } else {
                  this.plan = FloatFFT_1D.Plans.MIXED_RADIX;
                  this.wtable = new float[4 * this.n + 15];
                  this.wtable_r = new float[2 * this.n + 15];
                  this.cffti();
                  this.rffti();
               }
            } else {
               this.plan = FloatFFT_1D.Plans.SPLIT_RADIX;
               this.ip = new int[2 + (int)FastMath.ceil((double)(2 + (1 << (int)(FastMath.log((double)((float)n + 0.5F)) / FastMath.log((double)2.0F)) / 2)))];
               this.w = new float[this.n];
               int twon = 2 * this.n;
               this.nw = twon >> 2;
               CommonUtils.makewt(this.nw, this.ip, this.w);
               this.nc = this.n >> 2;
               CommonUtils.makect(this.nc, this.w, this.nw, this.ip);
            }
         } else if (!CommonUtils.isPowerOf2(this.nl)) {
            if (CommonUtils.getReminder(this.nl, factors) >= 211L) {
               this.plan = FloatFFT_1D.Plans.BLUESTEIN;
               this.nBluesteinl = CommonUtils.nextPow2(this.nl * 2L - 1L);
               this.bk1l = new FloatLargeArray(2L * this.nBluesteinl);
               this.bk2l = new FloatLargeArray(2L * this.nBluesteinl);
               this.ipl = new LongLargeArray(2L + (long)FastMath.ceil((double)(2L + (1L << (int)((long)(FastMath.log((double)((float)this.nBluesteinl + 0.5F)) / FastMath.log((double)2.0F)) / 2L)))));
               this.wl = new FloatLargeArray(this.nBluesteinl);
               long twon = 2L * this.nBluesteinl;
               this.nwl = twon >> 2;
               CommonUtils.makewt(this.nwl, this.ipl, this.wl);
               this.ncl = this.nBluesteinl >> 2;
               CommonUtils.makect(this.ncl, this.wl, this.nwl, this.ipl);
               this.bluesteinil();
            } else {
               this.plan = FloatFFT_1D.Plans.MIXED_RADIX;
               this.wtablel = new FloatLargeArray(4L * this.nl + 15L);
               this.wtable_rl = new FloatLargeArray(2L * this.nl + 15L);
               this.cfftil();
               this.rfftil();
            }
         } else {
            this.plan = FloatFFT_1D.Plans.SPLIT_RADIX;
            this.ipl = new LongLargeArray(2L + (long)FastMath.ceil((double)(2L + (1L << (int)((long)(FastMath.log((double)((float)this.nl + 0.5F)) / FastMath.log((double)2.0F)) / 2L)))));
            this.wl = new FloatLargeArray(this.nl);
            long twon = 2L * this.nl;
            this.nwl = twon >> 2;
            CommonUtils.makewt(this.nwl, this.ipl, this.wl);
            this.ncl = this.nl >> 2;
            CommonUtils.makect(this.ncl, this.wl, this.nwl, this.ipl);
         }

      }
   }

   public void complexForward(float[] a) {
      this.complexForward(a, 0);
   }

   public void complexForward(FloatLargeArray a) {
      this.complexForward(a, 0L);
   }

   public void complexForward(float[] a, int offa) {
      if (this.useLargeArrays) {
         this.complexForward(new FloatLargeArray(a), (long)offa);
      } else {
         if (this.n == 1) {
            return;
         }

         switch (this.plan) {
            case SPLIT_RADIX:
               CommonUtils.cftbsub(2 * this.n, a, offa, this.ip, this.nw, this.w);
               break;
            case MIXED_RADIX:
               this.cfftf(a, offa, -1);
               break;
            case BLUESTEIN:
               this.bluestein_complex(a, offa, -1);
         }
      }

   }

   public void complexForward(FloatLargeArray a, long offa) {
      if (!this.useLargeArrays) {
         if (a.isLarge() || a.isConstant() || offa >= 2147483647L) {
            throw new IllegalArgumentException("The data array is too big.");
         }

         this.complexForward(a.getData(), (int)offa);
      } else {
         if (this.nl == 1L) {
            return;
         }

         switch (this.plan) {
            case SPLIT_RADIX:
               CommonUtils.cftbsub(2L * this.nl, a, offa, this.ipl, this.nwl, this.wl);
               break;
            case MIXED_RADIX:
               this.cfftf(a, offa, -1);
               break;
            case BLUESTEIN:
               this.bluestein_complex(a, offa, -1);
         }
      }

   }

   public void complexInverse(float[] a, boolean scale) {
      this.complexInverse(a, 0, scale);
   }

   public void complexInverse(FloatLargeArray a, boolean scale) {
      this.complexInverse(a, 0L, scale);
   }

   public void complexInverse(float[] a, int offa, boolean scale) {
      if (this.useLargeArrays) {
         this.complexInverse(new FloatLargeArray(a), (long)offa, scale);
      } else {
         if (this.n == 1) {
            return;
         }

         switch (this.plan) {
            case SPLIT_RADIX:
               CommonUtils.cftfsub(2 * this.n, a, offa, this.ip, this.nw, this.w);
               break;
            case MIXED_RADIX:
               this.cfftf(a, offa, 1);
               break;
            case BLUESTEIN:
               this.bluestein_complex(a, offa, 1);
         }

         if (scale) {
            CommonUtils.scale(this.n, 1.0F / (float)this.n, a, offa, true);
         }
      }

   }

   public void complexInverse(FloatLargeArray a, long offa, boolean scale) {
      if (!this.useLargeArrays) {
         if (a.isLarge() || a.isConstant() || offa >= 2147483647L) {
            throw new IllegalArgumentException("The data array is too big.");
         }

         this.complexInverse(a.getData(), (int)offa, scale);
      } else {
         if (this.nl == 1L) {
            return;
         }

         switch (this.plan) {
            case SPLIT_RADIX:
               CommonUtils.cftfsub(2L * this.nl, a, offa, this.ipl, this.nwl, this.wl);
               break;
            case MIXED_RADIX:
               this.cfftf(a, offa, 1);
               break;
            case BLUESTEIN:
               this.bluestein_complex(a, offa, 1);
         }

         if (scale) {
            CommonUtils.scale(this.nl, 1.0F / (float)this.nl, a, offa, true);
         }
      }

   }

   public void realForward(float[] a) {
      this.realForward(a, 0);
   }

   public void realForward(FloatLargeArray a) {
      this.realForward(a, 0L);
   }

   public void realForward(float[] a, int offa) {
      if (this.useLargeArrays) {
         this.realForward(new FloatLargeArray(a), (long)offa);
      } else {
         if (this.n == 1) {
            return;
         }

         switch (this.plan) {
            case SPLIT_RADIX:
               if (this.n > 4) {
                  CommonUtils.cftfsub(this.n, a, offa, this.ip, this.nw, this.w);
                  CommonUtils.rftfsub(this.n, a, offa, this.nc, this.w, this.nw);
               } else if (this.n == 4) {
                  CommonUtils.cftx020(a, offa);
               }

               float xi = a[offa] - a[offa + 1];
               a[offa] += a[offa + 1];
               a[offa + 1] = xi;
               break;
            case MIXED_RADIX:
               this.rfftf(a, offa);

               for(int k = this.n - 1; k >= 2; --k) {
                  int idx = offa + k;
                  float tmp = a[idx];
                  a[idx] = a[idx - 1];
                  a[idx - 1] = tmp;
               }
               break;
            case BLUESTEIN:
               this.bluestein_real_forward(a, offa);
         }
      }

   }

   public void realForward(FloatLargeArray a, long offa) {
      if (!this.useLargeArrays) {
         if (a.isLarge() || a.isConstant() || offa >= 2147483647L) {
            throw new IllegalArgumentException("The data array is too big.");
         }

         this.realForward(a.getData(), (int)offa);
      } else {
         if (this.nl == 1L) {
            return;
         }

         switch (this.plan) {
            case SPLIT_RADIX:
               if (this.nl > 4L) {
                  CommonUtils.cftfsub(this.nl, a, offa, this.ipl, this.nwl, this.wl);
                  CommonUtils.rftfsub(this.nl, a, offa, this.ncl, this.wl, this.nwl);
               } else if (this.nl == 4L) {
                  CommonUtils.cftx020(a, offa);
               }

               float xi = a.getFloat(offa) - a.getFloat(offa + 1L);
               a.setFloat(offa, a.getFloat(offa) + a.getFloat(offa + 1L));
               a.setFloat(offa + 1L, xi);
               break;
            case MIXED_RADIX:
               this.rfftf(a, offa);

               for(long k = this.nl - 1L; k >= 2L; --k) {
                  long idx = offa + k;
                  float tmp = a.getFloat(idx);
                  a.setFloat(idx, a.getFloat(idx - 1L));
                  a.setFloat(idx - 1L, tmp);
               }
               break;
            case BLUESTEIN:
               this.bluestein_real_forward(a, offa);
         }
      }

   }

   public void realForwardFull(float[] a) {
      this.realForwardFull(a, 0);
   }

   public void realForwardFull(FloatLargeArray a) {
      this.realForwardFull(a, 0L);
   }

   public void realForwardFull(final float[] a, final int offa) {
      if (this.useLargeArrays) {
         this.realForwardFull(new FloatLargeArray(a), (long)offa);
      } else {
         final int twon = 2 * this.n;
         switch (this.plan) {
            case SPLIT_RADIX:
               this.realForward(a, offa);
               int nthreads = ConcurrencyUtils.getNumberOfThreads();
               if (nthreads > 1 && (long)(this.n / 2) > CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
                  Future<?>[] futures = new Future[nthreads];
                  int k = this.n / 2 / nthreads;

                  for(int i = 0; i < nthreads; ++i) {
                     final int firstIdx = i * k;
                     final int lastIdx = i == nthreads - 1 ? this.n / 2 : firstIdx + k;
                     futures[i] = ConcurrencyUtils.submit(new Runnable() {
                        public void run() {
                           for(int k = firstIdx; k < lastIdx; ++k) {
                              int idx1 = 2 * k;
                              int idx2 = offa + (twon - idx1) % twon;
                              a[idx2] = a[offa + idx1];
                              a[idx2 + 1] = -a[offa + idx1 + 1];
                           }

                        }
                     });
                  }

                  try {
                     ConcurrencyUtils.waitForCompletion(futures);
                  } catch (InterruptedException ex) {
                     Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
                  } catch (ExecutionException ex) {
                     Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
                  }
               } else {
                  for(int k = 0; k < this.n / 2; ++k) {
                     int idx1 = 2 * k;
                     int idx2 = offa + (twon - idx1) % twon;
                     a[idx2] = a[offa + idx1];
                     a[idx2 + 1] = -a[offa + idx1 + 1];
                  }
               }

               a[offa + this.n] = -a[offa + 1];
               a[offa + 1] = 0.0F;
               break;
            case MIXED_RADIX:
               this.rfftf(a, offa);
               int m;
               if (this.n % 2 == 0) {
                  m = this.n / 2;
               } else {
                  m = (this.n + 1) / 2;
               }

               for(int k = 1; k < m; ++k) {
                  int idx1 = offa + twon - 2 * k;
                  int idx2 = offa + 2 * k;
                  a[idx1 + 1] = -a[idx2];
                  a[idx1] = a[idx2 - 1];
               }

               for(int k = 1; k < this.n; ++k) {
                  int idx = offa + this.n - k;
                  float tmp = a[idx + 1];
                  a[idx + 1] = a[idx];
                  a[idx] = tmp;
               }

               a[offa + 1] = 0.0F;
               break;
            case BLUESTEIN:
               this.bluestein_real_full(a, offa, -1);
         }
      }

   }

   public void realForwardFull(final FloatLargeArray a, final long offa) {
      if (!this.useLargeArrays) {
         if (a.isLarge() || a.isConstant() || offa >= 2147483647L) {
            throw new IllegalArgumentException("The data array is too big.");
         }

         this.realForwardFull(a.getData(), (int)offa);
      } else {
         final long twon = 2L * this.nl;
         switch (this.plan) {
            case SPLIT_RADIX:
               this.realForward(a, offa);
               int nthreads = ConcurrencyUtils.getNumberOfThreads();
               if (nthreads > 1 && this.nl / 2L > CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
                  Future<?>[] futures = new Future[nthreads];
                  long k = this.nl / 2L / (long)nthreads;

                  for(int i = 0; i < nthreads; ++i) {
                     final long firstIdx = (long)i * k;
                     final long lastIdx = i == nthreads - 1 ? this.nl / 2L : firstIdx + k;
                     futures[i] = ConcurrencyUtils.submit(new Runnable() {
                        public void run() {
                           for(long k = firstIdx; k < lastIdx; ++k) {
                              long idx1 = 2L * k;
                              long idx2 = offa + (twon - idx1) % twon;
                              a.setFloat(idx2, a.getFloat(offa + idx1));
                              a.setFloat(idx2 + 1L, -a.getFloat(offa + idx1 + 1L));
                           }

                        }
                     });
                  }

                  try {
                     ConcurrencyUtils.waitForCompletion(futures);
                  } catch (InterruptedException ex) {
                     Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
                  } catch (ExecutionException ex) {
                     Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
                  }
               } else {
                  for(long k = 0L; k < this.nl / 2L; ++k) {
                     long idx1 = 2L * k;
                     long idx2 = offa + (twon - idx1) % twon;
                     a.setFloat(idx2, a.getFloat(offa + idx1));
                     a.setFloat(idx2 + 1L, -a.getFloat(offa + idx1 + 1L));
                  }
               }

               a.setFloat(offa + this.nl, -a.getFloat(offa + 1L));
               a.setFloat(offa + 1L, 0.0F);
               break;
            case MIXED_RADIX:
               this.rfftf(a, offa);
               long m;
               if (this.nl % 2L == 0L) {
                  m = this.nl / 2L;
               } else {
                  m = (this.nl + 1L) / 2L;
               }

               for(long k = 1L; k < m; ++k) {
                  long idx1 = offa + twon - 2L * k;
                  long idx2 = offa + 2L * k;
                  a.setFloat(idx1 + 1L, -a.getFloat(idx2));
                  a.setFloat(idx1, a.getFloat(idx2 - 1L));
               }

               for(long k = 1L; k < this.nl; ++k) {
                  long idx = offa + this.nl - k;
                  float tmp = a.getFloat(idx + 1L);
                  a.setFloat(idx + 1L, a.getFloat(idx));
                  a.setFloat(idx, tmp);
               }

               a.setFloat(offa + 1L, 0.0F);
               break;
            case BLUESTEIN:
               this.bluestein_real_full(a, offa, -1L);
         }
      }

   }

   public void realInverse(float[] a, boolean scale) {
      this.realInverse(a, 0, scale);
   }

   public void realInverse(FloatLargeArray a, boolean scale) {
      this.realInverse(a, 0L, scale);
   }

   public void realInverse(float[] a, int offa, boolean scale) {
      if (this.useLargeArrays) {
         this.realInverse(new FloatLargeArray(a), (long)offa, scale);
      } else {
         if (this.n == 1) {
            return;
         }

         switch (this.plan) {
            case SPLIT_RADIX:
               a[offa + 1] = 0.5F * (a[offa] - a[offa + 1]);
               a[offa] -= a[offa + 1];
               if (this.n > 4) {
                  CommonUtils.rftfsub(this.n, a, offa, this.nc, this.w, this.nw);
                  CommonUtils.cftbsub(this.n, a, offa, this.ip, this.nw, this.w);
               } else if (this.n == 4) {
                  CommonUtils.cftxc020(a, offa);
               }

               if (scale) {
                  CommonUtils.scale(this.n, 1.0F / ((float)this.n / 2.0F), a, offa, false);
               }
               break;
            case MIXED_RADIX:
               for(int k = 2; k < this.n; ++k) {
                  int idx = offa + k;
                  float tmp = a[idx - 1];
                  a[idx - 1] = a[idx];
                  a[idx] = tmp;
               }

               this.rfftb(a, offa);
               if (scale) {
                  CommonUtils.scale(this.n, 1.0F / (float)this.n, a, offa, false);
               }
               break;
            case BLUESTEIN:
               this.bluestein_real_inverse(a, offa);
               if (scale) {
                  CommonUtils.scale(this.n, 1.0F / (float)this.n, a, offa, false);
               }
         }
      }

   }

   public void realInverse(FloatLargeArray a, long offa, boolean scale) {
      if (!this.useLargeArrays) {
         if (a.isLarge() || a.isConstant() || offa >= 2147483647L) {
            throw new IllegalArgumentException("The data array is too big.");
         }

         this.realInverse(a.getData(), (int)offa, scale);
      } else {
         if (this.nl == 1L) {
            return;
         }

         switch (this.plan) {
            case SPLIT_RADIX:
               a.setFloat(offa + 1L, 0.5F * (a.getFloat(offa) - a.getFloat(offa + 1L)));
               a.setFloat(offa, a.getFloat(offa) - a.getFloat(offa + 1L));
               if (this.nl > 4L) {
                  CommonUtils.rftfsub(this.nl, a, offa, this.ncl, this.wl, this.nwl);
                  CommonUtils.cftbsub(this.nl, a, offa, this.ipl, this.nwl, this.wl);
               } else if (this.nl == 4L) {
                  CommonUtils.cftxc020(a, offa);
               }

               if (scale) {
                  CommonUtils.scale(this.nl, 1.0F / ((float)this.nl / 2.0F), a, offa, false);
               }
               break;
            case MIXED_RADIX:
               for(long k = 2L; k < this.nl; ++k) {
                  long idx = offa + k;
                  float tmp = a.getFloat(idx - 1L);
                  a.setFloat(idx - 1L, a.getFloat(idx));
                  a.setFloat(idx, tmp);
               }

               this.rfftb(a, offa);
               if (scale) {
                  CommonUtils.scale(this.nl, 1.0F / (float)this.nl, a, offa, false);
               }
               break;
            case BLUESTEIN:
               this.bluestein_real_inverse(a, offa);
               if (scale) {
                  CommonUtils.scale(this.nl, 1.0F / (float)this.nl, a, offa, false);
               }
         }
      }

   }

   public void realInverseFull(float[] a, boolean scale) {
      this.realInverseFull(a, 0, scale);
   }

   public void realInverseFull(FloatLargeArray a, boolean scale) {
      this.realInverseFull(a, 0L, scale);
   }

   public void realInverseFull(final float[] a, final int offa, boolean scale) {
      if (this.useLargeArrays) {
         this.realInverseFull(new FloatLargeArray(a), (long)offa, scale);
      } else {
         final int twon = 2 * this.n;
         switch (this.plan) {
            case SPLIT_RADIX:
               this.realInverse2(a, offa, scale);
               int nthreads = ConcurrencyUtils.getNumberOfThreads();
               if (nthreads > 1 && (long)(this.n / 2) > CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
                  Future<?>[] futures = new Future[nthreads];
                  int k = this.n / 2 / nthreads;

                  for(int i = 0; i < nthreads; ++i) {
                     final int firstIdx = i * k;
                     final int lastIdx = i == nthreads - 1 ? this.n / 2 : firstIdx + k;
                     futures[i] = ConcurrencyUtils.submit(new Runnable() {
                        public void run() {
                           for(int k = firstIdx; k < lastIdx; ++k) {
                              int idx1 = 2 * k;
                              int idx2 = offa + (twon - idx1) % twon;
                              a[idx2] = a[offa + idx1];
                              a[idx2 + 1] = -a[offa + idx1 + 1];
                           }

                        }
                     });
                  }

                  try {
                     ConcurrencyUtils.waitForCompletion(futures);
                  } catch (InterruptedException ex) {
                     Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
                  } catch (ExecutionException ex) {
                     Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
                  }
               } else {
                  for(int k = 0; k < this.n / 2; ++k) {
                     int idx1 = 2 * k;
                     int idx2 = offa + (twon - idx1) % twon;
                     a[idx2] = a[offa + idx1];
                     a[idx2 + 1] = -a[offa + idx1 + 1];
                  }
               }

               a[offa + this.n] = -a[offa + 1];
               a[offa + 1] = 0.0F;
               break;
            case MIXED_RADIX:
               this.rfftf(a, offa);
               if (scale) {
                  CommonUtils.scale(this.n, 1.0F / (float)this.n, a, offa, false);
               }

               int m;
               if (this.n % 2 == 0) {
                  m = this.n / 2;
               } else {
                  m = (this.n + 1) / 2;
               }

               for(int k = 1; k < m; ++k) {
                  int idx1 = offa + 2 * k;
                  int idx2 = offa + twon - 2 * k;
                  a[idx1] = -a[idx1];
                  a[idx2 + 1] = -a[idx1];
                  a[idx2] = a[idx1 - 1];
               }

               for(int k = 1; k < this.n; ++k) {
                  int idx = offa + this.n - k;
                  float tmp = a[idx + 1];
                  a[idx + 1] = a[idx];
                  a[idx] = tmp;
               }

               a[offa + 1] = 0.0F;
               break;
            case BLUESTEIN:
               this.bluestein_real_full(a, offa, 1);
               if (scale) {
                  CommonUtils.scale(this.n, 1.0F / (float)this.n, a, offa, true);
               }
         }
      }

   }

   public void realInverseFull(final FloatLargeArray a, final long offa, boolean scale) {
      if (!this.useLargeArrays) {
         if (a.isLarge() || a.isConstant() || offa >= 2147483647L) {
            throw new IllegalArgumentException("The data array is too big.");
         }

         this.realInverseFull(a.getData(), (int)offa, scale);
      } else {
         final long twon = 2L * this.nl;
         switch (this.plan) {
            case SPLIT_RADIX:
               this.realInverse2(a, offa, scale);
               int nthreads = ConcurrencyUtils.getNumberOfThreads();
               if (nthreads > 1 && this.nl / 2L > CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
                  Future<?>[] futures = new Future[nthreads];
                  long k = this.nl / 2L / (long)nthreads;

                  for(int i = 0; i < nthreads; ++i) {
                     final long firstIdx = (long)i * k;
                     final long lastIdx = i == nthreads - 1 ? this.nl / 2L : firstIdx + k;
                     futures[i] = ConcurrencyUtils.submit(new Runnable() {
                        public void run() {
                           for(long k = firstIdx; k < lastIdx; ++k) {
                              long idx1 = 2L * k;
                              long idx2 = offa + (twon - idx1) % twon;
                              a.setFloat(idx2, a.getFloat(offa + idx1));
                              a.setFloat(idx2 + 1L, -a.getFloat(offa + idx1 + 1L));
                           }

                        }
                     });
                  }

                  try {
                     ConcurrencyUtils.waitForCompletion(futures);
                  } catch (InterruptedException ex) {
                     Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
                  } catch (ExecutionException ex) {
                     Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
                  }
               } else {
                  for(long k = 0L; k < this.nl / 2L; ++k) {
                     long idx1 = 2L * k;
                     long idx2 = offa + (twon - idx1) % twon;
                     a.setFloat(idx2, a.getFloat(offa + idx1));
                     a.setFloat(idx2 + 1L, -a.getFloat(offa + idx1 + 1L));
                  }
               }

               a.setFloat(offa + this.nl, -a.getFloat(offa + 1L));
               a.setFloat(offa + 1L, 0.0F);
               break;
            case MIXED_RADIX:
               this.rfftf(a, offa);
               if (scale) {
                  CommonUtils.scale(this.nl, 1.0F / (float)this.nl, a, offa, false);
               }

               long m;
               if (this.nl % 2L == 0L) {
                  m = this.nl / 2L;
               } else {
                  m = (this.nl + 1L) / 2L;
               }

               for(long k = 1L; k < m; ++k) {
                  long idx1 = offa + 2L * k;
                  long idx2 = offa + twon - 2L * k;
                  a.setFloat(idx1, -a.getFloat(idx1));
                  a.setFloat(idx2 + 1L, -a.getFloat(idx1));
                  a.setFloat(idx2, a.getFloat(idx1 - 1L));
               }

               for(long k = 1L; k < this.nl; ++k) {
                  long idx = offa + this.nl - k;
                  float tmp = a.getFloat(idx + 1L);
                  a.setFloat(idx + 1L, a.getFloat(idx));
                  a.setFloat(idx, tmp);
               }

               a.setFloat(offa + 1L, 0.0F);
               break;
            case BLUESTEIN:
               this.bluestein_real_full(a, offa, 1L);
               if (scale) {
                  CommonUtils.scale(this.nl, 1.0F / (float)this.nl, a, offa, true);
               }
         }
      }

   }

   protected void realInverse2(float[] a, int offa, boolean scale) {
      if (this.useLargeArrays) {
         this.realInverse2(new FloatLargeArray(a), (long)offa, scale);
      } else {
         if (this.n == 1) {
            return;
         }

         switch (this.plan) {
            case SPLIT_RADIX:
               if (this.n > 4) {
                  CommonUtils.cftfsub(this.n, a, offa, this.ip, this.nw, this.w);
                  CommonUtils.rftbsub(this.n, a, offa, this.nc, this.w, this.nw);
               } else if (this.n == 4) {
                  CommonUtils.cftbsub(this.n, a, offa, this.ip, this.nw, this.w);
               }

               float xi = a[offa] - a[offa + 1];
               a[offa] += a[offa + 1];
               a[offa + 1] = xi;
               if (scale) {
                  CommonUtils.scale(this.n, 1.0F / (float)this.n, a, offa, false);
               }
               break;
            case MIXED_RADIX:
               this.rfftf(a, offa);

               for(int k = this.n - 1; k >= 2; --k) {
                  int idx = offa + k;
                  float tmp = a[idx];
                  a[idx] = a[idx - 1];
                  a[idx - 1] = tmp;
               }

               if (scale) {
                  CommonUtils.scale(this.n, 1.0F / (float)this.n, a, offa, false);
               }

               if (this.n % 2 == 0) {
                  int m = this.n / 2;

                  for(int i = 1; i < m; ++i) {
                     int idx = offa + 2 * i + 1;
                     a[idx] = -a[idx];
                  }
               } else {
                  int m = (this.n - 1) / 2;

                  for(int i = 0; i < m; ++i) {
                     int idx = offa + 2 * i + 1;
                     a[idx] = -a[idx];
                  }
               }
               break;
            case BLUESTEIN:
               this.bluestein_real_inverse2(a, offa);
               if (scale) {
                  CommonUtils.scale(this.n, 1.0F / (float)this.n, a, offa, false);
               }
         }
      }

   }

   protected void realInverse2(FloatLargeArray a, long offa, boolean scale) {
      if (!this.useLargeArrays) {
         if (a.isLarge() || a.isConstant() || offa >= 2147483647L) {
            throw new IllegalArgumentException("The data array is too big.");
         }

         this.realInverse2(a.getData(), (int)offa, scale);
      } else {
         if (this.nl == 1L) {
            return;
         }

         switch (this.plan) {
            case SPLIT_RADIX:
               if (this.nl > 4L) {
                  CommonUtils.cftfsub(this.nl, a, offa, this.ipl, this.nwl, this.wl);
                  CommonUtils.rftbsub(this.nl, a, offa, this.ncl, this.wl, this.nwl);
               } else if (this.nl == 4L) {
                  CommonUtils.cftbsub(this.nl, a, offa, this.ipl, this.nwl, this.wl);
               }

               float xi = a.getFloat(offa) - a.getFloat(offa + 1L);
               a.setFloat(offa, a.getFloat(offa) + a.getFloat(offa + 1L));
               a.setFloat(offa + 1L, xi);
               if (scale) {
                  CommonUtils.scale(this.nl, 1.0F / (float)this.nl, a, offa, false);
               }
               break;
            case MIXED_RADIX:
               this.rfftf(a, offa);

               for(long k = this.nl - 1L; k >= 2L; --k) {
                  long idx = offa + k;
                  float tmp = a.getFloat(idx);
                  a.setFloat(idx, a.getFloat(idx - 1L));
                  a.setFloat(idx - 1L, tmp);
               }

               if (scale) {
                  CommonUtils.scale(this.nl, 1.0F / (float)this.nl, a, offa, false);
               }

               if (this.nl % 2L == 0L) {
                  long m = this.nl / 2L;

                  for(long i = 1L; i < m; ++i) {
                     long idx = offa + 2L * i + 1L;
                     a.setFloat(idx, -a.getFloat(idx));
                  }
               } else {
                  long m = (this.nl - 1L) / 2L;

                  for(long i = 0L; i < m; ++i) {
                     long idx = offa + 2L * i + 1L;
                     a.setFloat(idx, -a.getFloat(idx));
                  }
               }
               break;
            case BLUESTEIN:
               this.bluestein_real_inverse2(a, offa);
               if (scale) {
                  CommonUtils.scale(this.nl, 1.0F / (float)this.nl, a, offa, false);
               }
         }
      }

   }

   void cffti(int n, int offw) {
      if (n != 1) {
         int twon = 2 * n;
         int fourn = 4 * n;
         int ntry = 0;
         int nll = n;
         int nf = 0;
         int j = 0;

         label71:
         while(true) {
            ++j;
            if (j <= 4) {
               ntry = factors[j - 1];
            } else {
               ntry += 2;
            }

            int nq;
            do {
               nq = nll / ntry;
               int nr = nll - ntry * nq;
               if (nr != 0) {
                  continue label71;
               }

               ++nf;
               this.wtable[offw + nf + 1 + fourn] = (float)ntry;
               nll = nq;
               if (ntry == 2 && nf != 1) {
                  for(int i = 2; i <= nf; ++i) {
                     int ib = nf - i + 2;
                     int idx = ib + fourn;
                     this.wtable[offw + idx + 1] = this.wtable[offw + idx];
                  }

                  this.wtable[offw + 2 + fourn] = 2.0F;
               }
            } while(nq != 1);

            this.wtable[offw + fourn] = (float)n;
            this.wtable[offw + 1 + fourn] = (float)nf;
            float argh = ((float)Math.PI * 2F) / (float)n;
            int i = 1;
            int l1 = 1;

            for(int k1 = 1; k1 <= nf; ++k1) {
               int ipll = (int)this.wtable[offw + k1 + 1 + fourn];
               int ld = 0;
               int l2 = l1 * ipll;
               int ido = n / l2;
               int idot = ido + ido + 2;
               int ipm = ipll - 1;

               for(int var30 = 1; var30 <= ipm; ++var30) {
                  int i1 = i;
                  this.wtable[offw + i - 1 + twon] = 1.0F;
                  this.wtable[offw + i + twon] = 0.0F;
                  ld += l1;
                  float fi = 0.0F;
                  float argld = (float)ld * argh;

                  for(int ii = 4; ii <= idot; ii += 2) {
                     i += 2;
                     ++fi;
                     float arg = fi * argld;
                     int idx = i + twon;
                     this.wtable[offw + idx - 1] = (float)FastMath.cos((double)arg);
                     this.wtable[offw + idx] = (float)FastMath.sin((double)arg);
                  }

                  if (ipll > 5) {
                     int idx1 = i1 + twon;
                     int idx2 = i + twon;
                     this.wtable[offw + idx1 - 1] = this.wtable[offw + idx2 - 1];
                     this.wtable[offw + idx1] = this.wtable[offw + idx2];
                  }
               }

               l1 = l2;
            }

            return;
         }
      }
   }

   final void cffti() {
      if (this.n != 1) {
         int twon = 2 * this.n;
         int fourn = 4 * this.n;
         int ntry = 0;
         int nll = this.n;
         int nf = 0;
         int j = 0;

         label71:
         while(true) {
            ++j;
            if (j <= 4) {
               ntry = factors[j - 1];
            } else {
               ntry += 2;
            }

            int nq;
            do {
               nq = nll / ntry;
               int nr = nll - ntry * nq;
               if (nr != 0) {
                  continue label71;
               }

               ++nf;
               this.wtable[nf + 1 + fourn] = (float)ntry;
               nll = nq;
               if (ntry == 2 && nf != 1) {
                  for(int i = 2; i <= nf; ++i) {
                     int ib = nf - i + 2;
                     int idx = ib + fourn;
                     this.wtable[idx + 1] = this.wtable[idx];
                  }

                  this.wtable[2 + fourn] = 2.0F;
               }
            } while(nq != 1);

            this.wtable[fourn] = (float)this.n;
            this.wtable[1 + fourn] = (float)nf;
            float argh = ((float)Math.PI * 2F) / (float)this.n;
            int i = 1;
            int l1 = 1;

            for(int k1 = 1; k1 <= nf; ++k1) {
               int ipll = (int)this.wtable[k1 + 1 + fourn];
               int ld = 0;
               int l2 = l1 * ipll;
               int ido = this.n / l2;
               int idot = ido + ido + 2;
               int ipm = ipll - 1;

               for(int var28 = 1; var28 <= ipm; ++var28) {
                  int i1 = i;
                  this.wtable[i - 1 + twon] = 1.0F;
                  this.wtable[i + twon] = 0.0F;
                  ld += l1;
                  float fi = 0.0F;
                  float argld = (float)ld * argh;

                  for(int ii = 4; ii <= idot; ii += 2) {
                     i += 2;
                     ++fi;
                     float arg = fi * argld;
                     int idx = i + twon;
                     this.wtable[idx - 1] = (float)FastMath.cos((double)arg);
                     this.wtable[idx] = (float)FastMath.sin((double)arg);
                  }

                  if (ipll > 5) {
                     int idx1 = i1 + twon;
                     int idx2 = i + twon;
                     this.wtable[idx1 - 1] = this.wtable[idx2 - 1];
                     this.wtable[idx1] = this.wtable[idx2];
                  }
               }

               l1 = l2;
            }

            return;
         }
      }
   }

   final void cfftil() {
      if (this.nl != 1L) {
         long twon = 2L * this.nl;
         long fourn = 4L * this.nl;
         long ntry = 0L;
         long nl2 = this.nl;
         long nf = 0L;
         long j = 0L;

         label71:
         while(true) {
            ++j;
            if (j <= 4L) {
               ntry = (long)factors[(int)(j - 1L)];
            } else {
               ntry += 2L;
            }

            long nq;
            do {
               nq = nl2 / ntry;
               long nr = nl2 - ntry * nq;
               if (nr != 0L) {
                  continue label71;
               }

               ++nf;
               this.wtablel.setFloat(nf + 1L + fourn, (float)ntry);
               nl2 = nq;
               if (ntry == 2L && nf != 1L) {
                  for(long i = 2L; i <= nf; ++i) {
                     long ib = nf - i + 2L;
                     long idx = ib + fourn;
                     this.wtablel.setFloat(idx + 1L, this.wtablel.getFloat(idx));
                  }

                  this.wtablel.setFloat(2L + fourn, 2.0F);
               }
            } while(nq != 1L);

            this.wtablel.setFloat(fourn, (float)this.nl);
            this.wtablel.setFloat(1L + fourn, (float)nf);
            float argh = ((float)Math.PI * 2F) / (float)this.nl;
            long i = 1L;
            long l1 = 1L;

            for(long k1 = 1L; k1 <= nf; ++k1) {
               long ipll = (long)this.wtablel.getFloat(k1 + 1L + fourn);
               long ld = 0L;
               long l2 = l1 * ipll;
               long ido = this.nl / l2;
               long idot = ido + ido + 2L;
               long ipm = ipll - 1L;

               for(long var50 = 1L; var50 <= ipm; ++var50) {
                  long i1 = i;
                  this.wtablel.setFloat(i - 1L + twon, 1.0F);
                  this.wtablel.setFloat(i + twon, 0.0F);
                  ld += l1;
                  float fi = 0.0F;
                  float argld = (float)ld * argh;

                  for(long ii = 4L; ii <= idot; ii += 2L) {
                     i += 2L;
                     ++fi;
                     float arg = fi * argld;
                     long idx = i + twon;
                     this.wtablel.setFloat(idx - 1L, (float)FastMath.cos((double)arg));
                     this.wtablel.setFloat(idx, (float)FastMath.sin((double)arg));
                  }

                  if (ipll > 5L) {
                     long idx1 = i1 + twon;
                     long idx2 = i + twon;
                     this.wtablel.setFloat(idx1 - 1L, this.wtablel.getFloat(idx2 - 1L));
                     this.wtablel.setFloat(idx1, this.wtablel.getFloat(idx2));
                  }
               }

               l1 = l2;
            }

            return;
         }
      }
   }

   void rffti() {
      if (this.n != 1) {
         int twon = 2 * this.n;
         int ntry = 0;
         int nll = this.n;
         int nf = 0;
         int j = 0;

         label71:
         while(true) {
            ++j;
            if (j <= 4) {
               ntry = factors[j - 1];
            } else {
               ntry += 2;
            }

            int nq;
            do {
               nq = nll / ntry;
               int nr = nll - ntry * nq;
               if (nr != 0) {
                  continue label71;
               }

               ++nf;
               this.wtable_r[nf + 1 + twon] = (float)ntry;
               nll = nq;
               if (ntry == 2 && nf != 1) {
                  for(int i = 2; i <= nf; ++i) {
                     int ib = nf - i + 2;
                     int idx = ib + twon;
                     this.wtable_r[idx + 1] = this.wtable_r[idx];
                  }

                  this.wtable_r[2 + twon] = 2.0F;
               }
            } while(nq != 1);

            this.wtable_r[twon] = (float)this.n;
            this.wtable_r[1 + twon] = (float)nf;
            float argh = ((float)Math.PI * 2F) / (float)this.n;
            int is = 0;
            int nfm1 = nf - 1;
            int l1 = 1;
            if (nfm1 == 0) {
               return;
            }

            for(int k1 = 1; k1 <= nfm1; ++k1) {
               int ipll = (int)this.wtable_r[k1 + 1 + twon];
               int ld = 0;
               int l2 = l1 * ipll;
               int ido = this.n / l2;
               int ipm = ipll - 1;

               for(int var26 = 1; var26 <= ipm; ++var26) {
                  ld += l1;
                  int i = is;
                  float argld = (float)ld * argh;
                  float fi = 0.0F;

                  for(int ii = 3; ii <= ido; ii += 2) {
                     i += 2;
                     ++fi;
                     float arg = fi * argld;
                     int idx = i + this.n;
                     this.wtable_r[idx - 2] = (float)FastMath.cos((double)arg);
                     this.wtable_r[idx - 1] = (float)FastMath.sin((double)arg);
                  }

                  is += ido;
               }

               l1 = l2;
            }

            return;
         }
      }
   }

   void rfftil() {
      if (this.nl != 1L) {
         long twon = 2L * this.nl;
         long ntry = 0L;
         long nl2 = this.nl;
         long nf = 0L;
         long j = 0L;

         label71:
         while(true) {
            ++j;
            if (j <= 4L) {
               ntry = (long)factors[(int)(j - 1L)];
            } else {
               ntry += 2L;
            }

            long nq;
            do {
               nq = nl2 / ntry;
               long nr = nl2 - ntry * nq;
               if (nr != 0L) {
                  continue label71;
               }

               ++nf;
               this.wtable_rl.setFloat(nf + 1L + twon, (float)ntry);
               nl2 = nq;
               if (ntry == 2L && nf != 1L) {
                  for(long i = 2L; i <= nf; ++i) {
                     long ib = nf - i + 2L;
                     long idx = ib + twon;
                     this.wtable_rl.setFloat(idx + 1L, this.wtable_rl.getFloat(idx));
                  }

                  this.wtable_rl.setFloat(2L + twon, 2.0F);
               }
            } while(nq != 1L);

            this.wtable_rl.setFloat(twon, (float)this.nl);
            this.wtable_rl.setFloat(1L + twon, (float)nf);
            float argh = ((float)Math.PI * 2F) / (float)this.nl;
            long is = 0L;
            long nfm1 = nf - 1L;
            long l1 = 1L;
            if (nfm1 == 0L) {
               return;
            }

            for(long k1 = 1L; k1 <= nfm1; ++k1) {
               long ipll = (long)this.wtable_rl.getFloat(k1 + 1L + twon);
               long ld = 0L;
               long l2 = l1 * ipll;
               long ido = this.nl / l2;
               long ipm = ipll - 1L;

               for(long var46 = 1L; var46 <= ipm; ++var46) {
                  ld += l1;
                  long i = is;
                  float argld = (float)ld * argh;
                  float fi = 0.0F;

                  for(long ii = 3L; ii <= ido; ii += 2L) {
                     i += 2L;
                     ++fi;
                     float arg = fi * argld;
                     long idx = i + this.nl;
                     this.wtable_rl.setFloat(idx - 2L, (float)FastMath.cos((double)arg));
                     this.wtable_rl.setFloat(idx - 1L, (float)FastMath.sin((double)arg));
                  }

                  is += ido;
               }

               l1 = l2;
            }

            return;
         }
      }
   }

   private void bluesteini() {
      int k = 0;
      float pi_n = (float)Math.PI / (float)this.n;
      this.bk1[0] = 1.0F;
      this.bk1[1] = 0.0F;

      for(int i = 1; i < this.n; ++i) {
         k += 2 * i - 1;
         if (k >= 2 * this.n) {
            k -= 2 * this.n;
         }

         float arg = pi_n * (float)k;
         this.bk1[2 * i] = (float)FastMath.cos((double)arg);
         this.bk1[2 * i + 1] = (float)FastMath.sin((double)arg);
      }

      float scale = 1.0F / (float)this.nBluestein;
      this.bk2[0] = this.bk1[0] * scale;
      this.bk2[1] = this.bk1[1] * scale;

      for(int i = 2; i < 2 * this.n; i += 2) {
         this.bk2[i] = this.bk1[i] * scale;
         this.bk2[i + 1] = this.bk1[i + 1] * scale;
         this.bk2[2 * this.nBluestein - i] = this.bk2[i];
         this.bk2[2 * this.nBluestein - i + 1] = this.bk2[i + 1];
      }

      CommonUtils.cftbsub(2 * this.nBluestein, (float[])this.bk2, 0, this.ip, this.nw, (float[])this.w);
   }

   private void bluesteinil() {
      long k = 0L;
      float pi_n = (float)Math.PI / (float)this.nl;
      this.bk1l.setFloat(0L, 1.0F);
      this.bk1l.setFloat(1L, 0.0F);

      for(int i = 1; (long)i < this.nl; ++i) {
         k += (long)(2 * i - 1);
         if (k >= 2L * this.nl) {
            k -= 2L * this.nl;
         }

         float arg = pi_n * (float)k;
         this.bk1l.setFloat((long)(2 * i), (float)FastMath.cos((double)arg));
         this.bk1l.setFloat((long)(2 * i + 1), (float)FastMath.sin((double)arg));
      }

      float scale = 1.0F / (float)this.nBluesteinl;
      this.bk2l.setFloat(0L, this.bk1l.getFloat(0L) * scale);
      this.bk2l.setFloat(1L, this.bk1l.getFloat(1L) * scale);

      for(int i = 2; (long)i < 2L * this.nl; i += 2) {
         this.bk2l.setFloat((long)i, this.bk1l.getFloat((long)i) * scale);
         this.bk2l.setFloat((long)(i + 1), this.bk1l.getFloat((long)(i + 1)) * scale);
         this.bk2l.setFloat(2L * this.nBluesteinl - (long)i, this.bk2l.getFloat((long)i));
         this.bk2l.setFloat(2L * this.nBluesteinl - (long)i + 1L, this.bk2l.getFloat((long)(i + 1)));
      }

      CommonUtils.cftbsub(2L * this.nBluesteinl, this.bk2l, 0L, this.ipl, this.nwl, this.wl);
   }

   private void bluestein_complex(final float[] a, final int offa, final int isign) {
      final float[] ak = new float[2 * this.nBluestein];
      int threads = ConcurrencyUtils.getNumberOfThreads();
      if (threads > 1 && (long)this.n >= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
         int nthreads = 2;
         if (threads >= 4 && (long)this.n >= CommonUtils.getThreadsBeginN_1D_FFT_4Threads()) {
            nthreads = 4;
         }

         Future<?>[] futures = new Future[nthreads];
         int k = this.n / nthreads;

         for(int i = 0; i < nthreads; ++i) {
            final int firstIdx = i * k;
            final int lastIdx = i == nthreads - 1 ? this.n : firstIdx + k;
            futures[i] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  if (isign > 0) {
                     for(int i = firstIdx; i < lastIdx; ++i) {
                        int idx1 = 2 * i;
                        int idx2 = idx1 + 1;
                        int idx3 = offa + idx1;
                        int idx4 = offa + idx2;
                        ak[idx1] = a[idx3] * FloatFFT_1D.this.bk1[idx1] - a[idx4] * FloatFFT_1D.this.bk1[idx2];
                        ak[idx2] = a[idx3] * FloatFFT_1D.this.bk1[idx2] + a[idx4] * FloatFFT_1D.this.bk1[idx1];
                     }
                  } else {
                     for(int i = firstIdx; i < lastIdx; ++i) {
                        int idx1 = 2 * i;
                        int idx2 = idx1 + 1;
                        int idx3 = offa + idx1;
                        int idx4 = offa + idx2;
                        ak[idx1] = a[idx3] * FloatFFT_1D.this.bk1[idx1] + a[idx4] * FloatFFT_1D.this.bk1[idx2];
                        ak[idx2] = -a[idx3] * FloatFFT_1D.this.bk1[idx2] + a[idx4] * FloatFFT_1D.this.bk1[idx1];
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         CommonUtils.cftbsub(2 * this.nBluestein, (float[])ak, 0, this.ip, this.nw, (float[])this.w);
         k = this.nBluestein / nthreads;

         for(int i = 0; i < nthreads; ++i) {
            final int firstIdx = i * k;
            final int lastIdx = i == nthreads - 1 ? this.nBluestein : firstIdx + k;
            futures[i] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  if (isign > 0) {
                     for(int i = firstIdx; i < lastIdx; ++i) {
                        int idx1 = 2 * i;
                        int idx2 = idx1 + 1;
                        float im = -ak[idx1] * FloatFFT_1D.this.bk2[idx2] + ak[idx2] * FloatFFT_1D.this.bk2[idx1];
                        ak[idx1] = ak[idx1] * FloatFFT_1D.this.bk2[idx1] + ak[idx2] * FloatFFT_1D.this.bk2[idx2];
                        ak[idx2] = im;
                     }
                  } else {
                     for(int i = firstIdx; i < lastIdx; ++i) {
                        int idx1 = 2 * i;
                        int idx2 = idx1 + 1;
                        float im = ak[idx1] * FloatFFT_1D.this.bk2[idx2] + ak[idx2] * FloatFFT_1D.this.bk2[idx1];
                        ak[idx1] = ak[idx1] * FloatFFT_1D.this.bk2[idx1] - ak[idx2] * FloatFFT_1D.this.bk2[idx2];
                        ak[idx2] = im;
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         CommonUtils.cftfsub(2 * this.nBluestein, (float[])ak, 0, this.ip, this.nw, (float[])this.w);
         k = this.n / nthreads;

         for(int i = 0; i < nthreads; ++i) {
            final int firstIdx = i * k;
            final int lastIdx = i == nthreads - 1 ? this.n : firstIdx + k;
            futures[i] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  if (isign > 0) {
                     for(int i = firstIdx; i < lastIdx; ++i) {
                        int idx1 = 2 * i;
                        int idx2 = idx1 + 1;
                        int idx3 = offa + idx1;
                        int idx4 = offa + idx2;
                        a[idx3] = FloatFFT_1D.this.bk1[idx1] * ak[idx1] - FloatFFT_1D.this.bk1[idx2] * ak[idx2];
                        a[idx4] = FloatFFT_1D.this.bk1[idx2] * ak[idx1] + FloatFFT_1D.this.bk1[idx1] * ak[idx2];
                     }
                  } else {
                     for(int i = firstIdx; i < lastIdx; ++i) {
                        int idx1 = 2 * i;
                        int idx2 = idx1 + 1;
                        int idx3 = offa + idx1;
                        int idx4 = offa + idx2;
                        a[idx3] = FloatFFT_1D.this.bk1[idx1] * ak[idx1] + FloatFFT_1D.this.bk1[idx2] * ak[idx2];
                        a[idx4] = -FloatFFT_1D.this.bk1[idx2] * ak[idx1] + FloatFFT_1D.this.bk1[idx1] * ak[idx2];
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         if (isign > 0) {
            for(int i = 0; i < this.n; ++i) {
               int idx1 = 2 * i;
               int idx2 = idx1 + 1;
               int idx3 = offa + idx1;
               int idx4 = offa + idx2;
               ak[idx1] = a[idx3] * this.bk1[idx1] - a[idx4] * this.bk1[idx2];
               ak[idx2] = a[idx3] * this.bk1[idx2] + a[idx4] * this.bk1[idx1];
            }
         } else {
            for(int i = 0; i < this.n; ++i) {
               int idx1 = 2 * i;
               int idx2 = idx1 + 1;
               int idx3 = offa + idx1;
               int idx4 = offa + idx2;
               ak[idx1] = a[idx3] * this.bk1[idx1] + a[idx4] * this.bk1[idx2];
               ak[idx2] = -a[idx3] * this.bk1[idx2] + a[idx4] * this.bk1[idx1];
            }
         }

         CommonUtils.cftbsub(2 * this.nBluestein, (float[])ak, 0, this.ip, this.nw, (float[])this.w);
         if (isign > 0) {
            for(int i = 0; i < this.nBluestein; ++i) {
               int idx1 = 2 * i;
               int idx2 = idx1 + 1;
               float im = -ak[idx1] * this.bk2[idx2] + ak[idx2] * this.bk2[idx1];
               ak[idx1] = ak[idx1] * this.bk2[idx1] + ak[idx2] * this.bk2[idx2];
               ak[idx2] = im;
            }
         } else {
            for(int i = 0; i < this.nBluestein; ++i) {
               int idx1 = 2 * i;
               int idx2 = idx1 + 1;
               float im = ak[idx1] * this.bk2[idx2] + ak[idx2] * this.bk2[idx1];
               ak[idx1] = ak[idx1] * this.bk2[idx1] - ak[idx2] * this.bk2[idx2];
               ak[idx2] = im;
            }
         }

         CommonUtils.cftfsub(2 * this.nBluestein, (float[])ak, 0, this.ip, this.nw, (float[])this.w);
         if (isign > 0) {
            for(int i = 0; i < this.n; ++i) {
               int idx1 = 2 * i;
               int idx2 = idx1 + 1;
               int idx3 = offa + idx1;
               int idx4 = offa + idx2;
               a[idx3] = this.bk1[idx1] * ak[idx1] - this.bk1[idx2] * ak[idx2];
               a[idx4] = this.bk1[idx2] * ak[idx1] + this.bk1[idx1] * ak[idx2];
            }
         } else {
            for(int i = 0; i < this.n; ++i) {
               int idx1 = 2 * i;
               int idx2 = idx1 + 1;
               int idx3 = offa + idx1;
               int idx4 = offa + idx2;
               a[idx3] = this.bk1[idx1] * ak[idx1] + this.bk1[idx2] * ak[idx2];
               a[idx4] = -this.bk1[idx2] * ak[idx1] + this.bk1[idx1] * ak[idx2];
            }
         }
      }

   }

   private void bluestein_complex(final FloatLargeArray a, final long offa, final int isign) {
      final FloatLargeArray ak = new FloatLargeArray(2L * this.nBluesteinl);
      int threads = ConcurrencyUtils.getNumberOfThreads();
      if (threads > 1 && this.nl > CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
         int nthreads = 2;
         if (threads >= 4 && this.nl > CommonUtils.getThreadsBeginN_1D_FFT_4Threads()) {
            nthreads = 4;
         }

         Future<?>[] futures = new Future[nthreads];
         long k = this.nl / (long)nthreads;

         for(int i = 0; i < nthreads; ++i) {
            final long firstIdx = (long)i * k;
            final long lastIdx = i == nthreads - 1 ? this.nl : firstIdx + k;
            futures[i] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  if (isign > 0) {
                     for(long i = firstIdx; i < lastIdx; ++i) {
                        long idx1 = 2L * i;
                        long idx2 = idx1 + 1L;
                        long idx3 = offa + idx1;
                        long idx4 = offa + idx2;
                        ak.setFloat(idx1, a.getFloat(idx3) * FloatFFT_1D.this.bk1l.getFloat(idx1) - a.getFloat(idx4) * FloatFFT_1D.this.bk1l.getFloat(idx2));
                        ak.setFloat(idx2, a.getFloat(idx3) * FloatFFT_1D.this.bk1l.getFloat(idx2) + a.getFloat(idx4) * FloatFFT_1D.this.bk1l.getFloat(idx1));
                     }
                  } else {
                     for(long i = firstIdx; i < lastIdx; ++i) {
                        long idx1 = 2L * i;
                        long idx2 = idx1 + 1L;
                        long idx3 = offa + idx1;
                        long idx4 = offa + idx2;
                        ak.setFloat(idx1, a.getFloat(idx3) * FloatFFT_1D.this.bk1l.getFloat(idx1) + a.getFloat(idx4) * FloatFFT_1D.this.bk1l.getFloat(idx2));
                        ak.setFloat(idx2, -a.getFloat(idx3) * FloatFFT_1D.this.bk1l.getFloat(idx2) + a.getFloat(idx4) * FloatFFT_1D.this.bk1l.getFloat(idx1));
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         CommonUtils.cftbsub(2L * this.nBluesteinl, ak, 0L, this.ipl, this.nwl, this.wl);
         k = this.nBluesteinl / (long)nthreads;

         for(int i = 0; i < nthreads; ++i) {
            final long firstIdx = (long)i * k;
            final long lastIdx = i == nthreads - 1 ? this.nBluesteinl : firstIdx + k;
            futures[i] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  if (isign > 0) {
                     for(long i = firstIdx; i < lastIdx; ++i) {
                        long idx1 = 2L * i;
                        long idx2 = idx1 + 1L;
                        float im = -ak.getFloat(idx1) * FloatFFT_1D.this.bk2l.getFloat(idx2) + ak.getFloat(idx2) * FloatFFT_1D.this.bk2l.getFloat(idx1);
                        ak.setFloat(idx1, ak.getFloat(idx1) * FloatFFT_1D.this.bk2l.getFloat(idx1) + ak.getFloat(idx2) * FloatFFT_1D.this.bk2l.getFloat(idx2));
                        ak.setFloat(idx2, im);
                     }
                  } else {
                     for(long i = firstIdx; i < lastIdx; ++i) {
                        long idx1 = 2L * i;
                        long idx2 = idx1 + 1L;
                        float im = ak.getFloat(idx1) * FloatFFT_1D.this.bk2l.getFloat(idx2) + ak.getFloat(idx2) * FloatFFT_1D.this.bk2l.getFloat(idx1);
                        ak.setFloat(idx1, ak.getFloat(idx1) * FloatFFT_1D.this.bk2l.getFloat(idx1) - ak.getFloat(idx2) * FloatFFT_1D.this.bk2l.getFloat(idx2));
                        ak.setFloat(idx2, im);
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         CommonUtils.cftfsub(2L * this.nBluesteinl, ak, 0L, this.ipl, this.nwl, this.wl);
         k = this.nl / (long)nthreads;

         for(int i = 0; i < nthreads; ++i) {
            final long firstIdx = (long)i * k;
            final long lastIdx = i == nthreads - 1 ? this.nl : firstIdx + k;
            futures[i] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  if (isign > 0) {
                     for(long i = firstIdx; i < lastIdx; ++i) {
                        long idx1 = 2L * i;
                        long idx2 = idx1 + 1L;
                        long idx3 = offa + idx1;
                        long idx4 = offa + idx2;
                        a.setFloat(idx3, FloatFFT_1D.this.bk1l.getFloat(idx1) * ak.getFloat(idx1) - FloatFFT_1D.this.bk1l.getFloat(idx2) * ak.getFloat(idx2));
                        a.setFloat(idx4, FloatFFT_1D.this.bk1l.getFloat(idx2) * ak.getFloat(idx1) + FloatFFT_1D.this.bk1l.getFloat(idx1) * ak.getFloat(idx2));
                     }
                  } else {
                     for(long i = firstIdx; i < lastIdx; ++i) {
                        long idx1 = 2L * i;
                        long idx2 = idx1 + 1L;
                        long idx3 = offa + idx1;
                        long idx4 = offa + idx2;
                        a.setFloat(idx3, FloatFFT_1D.this.bk1l.getFloat(idx1) * ak.getFloat(idx1) + FloatFFT_1D.this.bk1l.getFloat(idx2) * ak.getFloat(idx2));
                        a.setFloat(idx4, -FloatFFT_1D.this.bk1l.getFloat(idx2) * ak.getFloat(idx1) + FloatFFT_1D.this.bk1l.getFloat(idx1) * ak.getFloat(idx2));
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         if (isign > 0) {
            for(long i = 0L; i < this.nl; ++i) {
               long idx1 = 2L * i;
               long idx2 = idx1 + 1L;
               long idx3 = offa + idx1;
               long idx4 = offa + idx2;
               ak.setFloat(idx1, a.getFloat(idx3) * this.bk1l.getFloat(idx1) - a.getFloat(idx4) * this.bk1l.getFloat(idx2));
               ak.setFloat(idx2, a.getFloat(idx3) * this.bk1l.getFloat(idx2) + a.getFloat(idx4) * this.bk1l.getFloat(idx1));
            }
         } else {
            for(long i = 0L; i < this.nl; ++i) {
               long idx1 = 2L * i;
               long idx2 = idx1 + 1L;
               long idx3 = offa + idx1;
               long idx4 = offa + idx2;
               ak.setFloat(idx1, a.getFloat(idx3) * this.bk1l.getFloat(idx1) + a.getFloat(idx4) * this.bk1l.getFloat(idx2));
               ak.setFloat(idx2, -a.getFloat(idx3) * this.bk1l.getFloat(idx2) + a.getFloat(idx4) * this.bk1l.getFloat(idx1));
            }
         }

         CommonUtils.cftbsub(2L * this.nBluesteinl, ak, 0L, this.ipl, this.nwl, this.wl);
         if (isign > 0) {
            for(long i = 0L; i < this.nBluesteinl; ++i) {
               long idx1 = 2L * i;
               long idx2 = idx1 + 1L;
               float im = -ak.getFloat(idx1) * this.bk2l.getFloat(idx2) + ak.getFloat(idx2) * this.bk2l.getFloat(idx1);
               ak.setFloat(idx1, ak.getFloat(idx1) * this.bk2l.getFloat(idx1) + ak.getFloat(idx2) * this.bk2l.getFloat(idx2));
               ak.setFloat(idx2, im);
            }
         } else {
            for(long i = 0L; i < this.nBluesteinl; ++i) {
               long idx1 = 2L * i;
               long idx2 = idx1 + 1L;
               float im = ak.getFloat(idx1) * this.bk2l.getFloat(idx2) + ak.getFloat(idx2) * this.bk2l.getFloat(idx1);
               ak.setFloat(idx1, ak.getFloat(idx1) * this.bk2l.getFloat(idx1) - ak.getFloat(idx2) * this.bk2l.getFloat(idx2));
               ak.setFloat(idx2, im);
            }
         }

         CommonUtils.cftfsub(2L * this.nBluesteinl, ak, 0L, this.ipl, this.nwl, this.wl);
         if (isign > 0) {
            for(long i = 0L; i < this.nl; ++i) {
               long idx1 = 2L * i;
               long idx2 = idx1 + 1L;
               long idx3 = offa + idx1;
               long idx4 = offa + idx2;
               a.setFloat(idx3, this.bk1l.getFloat(idx1) * ak.getFloat(idx1) - this.bk1l.getFloat(idx2) * ak.getFloat(idx2));
               a.setFloat(idx4, this.bk1l.getFloat(idx2) * ak.getFloat(idx1) + this.bk1l.getFloat(idx1) * ak.getFloat(idx2));
            }
         } else {
            for(long i = 0L; i < this.nl; ++i) {
               long idx1 = 2L * i;
               long idx2 = idx1 + 1L;
               long idx3 = offa + idx1;
               long idx4 = offa + idx2;
               a.setFloat(idx3, this.bk1l.getFloat(idx1) * ak.getFloat(idx1) + this.bk1l.getFloat(idx2) * ak.getFloat(idx2));
               a.setFloat(idx4, -this.bk1l.getFloat(idx2) * ak.getFloat(idx1) + this.bk1l.getFloat(idx1) * ak.getFloat(idx2));
            }
         }
      }

   }

   private void bluestein_real_full(final float[] a, final int offa, final int isign) {
      final float[] ak = new float[2 * this.nBluestein];
      int threads = ConcurrencyUtils.getNumberOfThreads();
      if (threads > 1 && (long)this.n >= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
         int nthreads = 2;
         if (threads >= 4 && (long)this.n >= CommonUtils.getThreadsBeginN_1D_FFT_4Threads()) {
            nthreads = 4;
         }

         Future<?>[] futures = new Future[nthreads];
         int k = this.n / nthreads;

         for(int i = 0; i < nthreads; ++i) {
            final int firstIdx = i * k;
            final int lastIdx = i == nthreads - 1 ? this.n : firstIdx + k;
            futures[i] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  if (isign > 0) {
                     for(int i = firstIdx; i < lastIdx; ++i) {
                        int idx1 = 2 * i;
                        int idx2 = idx1 + 1;
                        int idx3 = offa + i;
                        ak[idx1] = a[idx3] * FloatFFT_1D.this.bk1[idx1];
                        ak[idx2] = a[idx3] * FloatFFT_1D.this.bk1[idx2];
                     }
                  } else {
                     for(int i = firstIdx; i < lastIdx; ++i) {
                        int idx1 = 2 * i;
                        int idx2 = idx1 + 1;
                        int idx3 = offa + i;
                        ak[idx1] = a[idx3] * FloatFFT_1D.this.bk1[idx1];
                        ak[idx2] = -a[idx3] * FloatFFT_1D.this.bk1[idx2];
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         CommonUtils.cftbsub(2 * this.nBluestein, (float[])ak, 0, this.ip, this.nw, (float[])this.w);
         k = this.nBluestein / nthreads;

         for(int i = 0; i < nthreads; ++i) {
            final int firstIdx = i * k;
            final int lastIdx = i == nthreads - 1 ? this.nBluestein : firstIdx + k;
            futures[i] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  if (isign > 0) {
                     for(int i = firstIdx; i < lastIdx; ++i) {
                        int idx1 = 2 * i;
                        int idx2 = idx1 + 1;
                        float im = -ak[idx1] * FloatFFT_1D.this.bk2[idx2] + ak[idx2] * FloatFFT_1D.this.bk2[idx1];
                        ak[idx1] = ak[idx1] * FloatFFT_1D.this.bk2[idx1] + ak[idx2] * FloatFFT_1D.this.bk2[idx2];
                        ak[idx2] = im;
                     }
                  } else {
                     for(int i = firstIdx; i < lastIdx; ++i) {
                        int idx1 = 2 * i;
                        int idx2 = idx1 + 1;
                        float im = ak[idx1] * FloatFFT_1D.this.bk2[idx2] + ak[idx2] * FloatFFT_1D.this.bk2[idx1];
                        ak[idx1] = ak[idx1] * FloatFFT_1D.this.bk2[idx1] - ak[idx2] * FloatFFT_1D.this.bk2[idx2];
                        ak[idx2] = im;
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         CommonUtils.cftfsub(2 * this.nBluestein, (float[])ak, 0, this.ip, this.nw, (float[])this.w);
         k = this.n / nthreads;

         for(int i = 0; i < nthreads; ++i) {
            final int firstIdx = i * k;
            final int lastIdx = i == nthreads - 1 ? this.n : firstIdx + k;
            futures[i] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  if (isign > 0) {
                     for(int i = firstIdx; i < lastIdx; ++i) {
                        int idx1 = 2 * i;
                        int idx2 = idx1 + 1;
                        a[offa + idx1] = FloatFFT_1D.this.bk1[idx1] * ak[idx1] - FloatFFT_1D.this.bk1[idx2] * ak[idx2];
                        a[offa + idx2] = FloatFFT_1D.this.bk1[idx2] * ak[idx1] + FloatFFT_1D.this.bk1[idx1] * ak[idx2];
                     }
                  } else {
                     for(int i = firstIdx; i < lastIdx; ++i) {
                        int idx1 = 2 * i;
                        int idx2 = idx1 + 1;
                        a[offa + idx1] = FloatFFT_1D.this.bk1[idx1] * ak[idx1] + FloatFFT_1D.this.bk1[idx2] * ak[idx2];
                        a[offa + idx2] = -FloatFFT_1D.this.bk1[idx2] * ak[idx1] + FloatFFT_1D.this.bk1[idx1] * ak[idx2];
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         if (isign > 0) {
            for(int i = 0; i < this.n; ++i) {
               int idx1 = 2 * i;
               int idx2 = idx1 + 1;
               int idx3 = offa + i;
               ak[idx1] = a[idx3] * this.bk1[idx1];
               ak[idx2] = a[idx3] * this.bk1[idx2];
            }
         } else {
            for(int i = 0; i < this.n; ++i) {
               int idx1 = 2 * i;
               int idx2 = idx1 + 1;
               int idx3 = offa + i;
               ak[idx1] = a[idx3] * this.bk1[idx1];
               ak[idx2] = -a[idx3] * this.bk1[idx2];
            }
         }

         CommonUtils.cftbsub(2 * this.nBluestein, (float[])ak, 0, this.ip, this.nw, (float[])this.w);
         if (isign > 0) {
            for(int i = 0; i < this.nBluestein; ++i) {
               int idx1 = 2 * i;
               int idx2 = idx1 + 1;
               float im = -ak[idx1] * this.bk2[idx2] + ak[idx2] * this.bk2[idx1];
               ak[idx1] = ak[idx1] * this.bk2[idx1] + ak[idx2] * this.bk2[idx2];
               ak[idx2] = im;
            }
         } else {
            for(int i = 0; i < this.nBluestein; ++i) {
               int idx1 = 2 * i;
               int idx2 = idx1 + 1;
               float im = ak[idx1] * this.bk2[idx2] + ak[idx2] * this.bk2[idx1];
               ak[idx1] = ak[idx1] * this.bk2[idx1] - ak[idx2] * this.bk2[idx2];
               ak[idx2] = im;
            }
         }

         CommonUtils.cftfsub(2 * this.nBluestein, (float[])ak, 0, this.ip, this.nw, (float[])this.w);
         if (isign > 0) {
            for(int i = 0; i < this.n; ++i) {
               int idx1 = 2 * i;
               int idx2 = idx1 + 1;
               a[offa + idx1] = this.bk1[idx1] * ak[idx1] - this.bk1[idx2] * ak[idx2];
               a[offa + idx2] = this.bk1[idx2] * ak[idx1] + this.bk1[idx1] * ak[idx2];
            }
         } else {
            for(int i = 0; i < this.n; ++i) {
               int idx1 = 2 * i;
               int idx2 = idx1 + 1;
               a[offa + idx1] = this.bk1[idx1] * ak[idx1] + this.bk1[idx2] * ak[idx2];
               a[offa + idx2] = -this.bk1[idx2] * ak[idx1] + this.bk1[idx1] * ak[idx2];
            }
         }
      }

   }

   private void bluestein_real_full(final FloatLargeArray a, final long offa, final long isign) {
      final FloatLargeArray ak = new FloatLargeArray(2L * this.nBluesteinl);
      int threads = ConcurrencyUtils.getNumberOfThreads();
      if (threads > 1 && this.nl > CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
         int nthreads = 2;
         if (threads >= 4 && this.nl > CommonUtils.getThreadsBeginN_1D_FFT_4Threads()) {
            nthreads = 4;
         }

         Future<?>[] futures = new Future[nthreads];
         long k = this.nl / (long)nthreads;

         for(int i = 0; i < nthreads; ++i) {
            final long firstIdx = (long)i * k;
            final long lastIdx = i == nthreads - 1 ? this.nl : firstIdx + k;
            futures[i] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  if (isign > 0L) {
                     for(long i = firstIdx; i < lastIdx; ++i) {
                        long idx1 = 2L * i;
                        long idx2 = idx1 + 1L;
                        long idx3 = offa + i;
                        ak.setFloat(idx1, a.getFloat(idx3) * FloatFFT_1D.this.bk1l.getFloat(idx1));
                        ak.setFloat(idx2, a.getFloat(idx3) * FloatFFT_1D.this.bk1l.getFloat(idx2));
                     }
                  } else {
                     for(long i = firstIdx; i < lastIdx; ++i) {
                        long idx1 = 2L * i;
                        long idx2 = idx1 + 1L;
                        long idx3 = offa + i;
                        ak.setFloat(idx1, a.getFloat(idx3) * FloatFFT_1D.this.bk1l.getFloat(idx1));
                        ak.setFloat(idx2, -a.getFloat(idx3) * FloatFFT_1D.this.bk1l.getFloat(idx2));
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         CommonUtils.cftbsub(2L * this.nBluesteinl, ak, 0L, this.ipl, this.nwl, this.wl);
         k = this.nBluesteinl / (long)nthreads;

         for(int i = 0; i < nthreads; ++i) {
            final long firstIdx = (long)i * k;
            final long lastIdx = i == nthreads - 1 ? this.nBluesteinl : firstIdx + k;
            futures[i] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  if (isign > 0L) {
                     for(long i = firstIdx; i < lastIdx; ++i) {
                        long idx1 = 2L * i;
                        long idx2 = idx1 + 1L;
                        float im = -ak.getFloat(idx1) * FloatFFT_1D.this.bk2l.getFloat(idx2) + ak.getFloat(idx2) * FloatFFT_1D.this.bk2l.getFloat(idx1);
                        ak.setFloat(idx1, ak.getFloat(idx1) * FloatFFT_1D.this.bk2l.getFloat(idx1) + ak.getFloat(idx2) * FloatFFT_1D.this.bk2l.getFloat(idx2));
                        ak.setFloat(idx2, im);
                     }
                  } else {
                     for(long i = firstIdx; i < lastIdx; ++i) {
                        long idx1 = 2L * i;
                        long idx2 = idx1 + 1L;
                        float im = ak.getFloat(idx1) * FloatFFT_1D.this.bk2l.getFloat(idx2) + ak.getFloat(idx2) * FloatFFT_1D.this.bk2l.getFloat(idx1);
                        ak.setFloat(idx1, ak.getFloat(idx1) * FloatFFT_1D.this.bk2l.getFloat(idx1) - ak.getFloat(idx2) * FloatFFT_1D.this.bk2l.getFloat(idx2));
                        ak.setFloat(idx2, im);
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         CommonUtils.cftfsub(2L * this.nBluesteinl, ak, 0L, this.ipl, this.nwl, this.wl);
         k = this.nl / (long)nthreads;

         for(int i = 0; i < nthreads; ++i) {
            final long firstIdx = (long)i * k;
            final long lastIdx = i == nthreads - 1 ? this.nl : firstIdx + k;
            futures[i] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  if (isign > 0L) {
                     for(long i = firstIdx; i < lastIdx; ++i) {
                        long idx1 = 2L * i;
                        long idx2 = idx1 + 1L;
                        a.setFloat(offa + idx1, FloatFFT_1D.this.bk1l.getFloat(idx1) * ak.getFloat(idx1) - FloatFFT_1D.this.bk1l.getFloat(idx2) * ak.getFloat(idx2));
                        a.setFloat(offa + idx2, FloatFFT_1D.this.bk1l.getFloat(idx2) * ak.getFloat(idx1) + FloatFFT_1D.this.bk1l.getFloat(idx1) * ak.getFloat(idx2));
                     }
                  } else {
                     for(long i = firstIdx; i < lastIdx; ++i) {
                        long idx1 = 2L * i;
                        long idx2 = idx1 + 1L;
                        a.setFloat(offa + idx1, FloatFFT_1D.this.bk1l.getFloat(idx1) * ak.getFloat(idx1) + FloatFFT_1D.this.bk1l.getFloat(idx2) * ak.getFloat(idx2));
                        a.setFloat(offa + idx2, -FloatFFT_1D.this.bk1l.getFloat(idx2) * ak.getFloat(idx1) + FloatFFT_1D.this.bk1l.getFloat(idx1) * ak.getFloat(idx2));
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         if (isign > 0L) {
            for(long i = 0L; i < this.nl; ++i) {
               long idx1 = 2L * i;
               long idx2 = idx1 + 1L;
               long idx3 = offa + i;
               ak.setFloat(idx1, a.getFloat(idx3) * this.bk1l.getFloat(idx1));
               ak.setFloat(idx2, a.getFloat(idx3) * this.bk1l.getFloat(idx2));
            }
         } else {
            for(long i = 0L; i < this.nl; ++i) {
               long idx1 = 2L * i;
               long idx2 = idx1 + 1L;
               long idx3 = offa + i;
               ak.setFloat(idx1, a.getFloat(idx3) * this.bk1l.getFloat(idx1));
               ak.setFloat(idx2, -a.getFloat(idx3) * this.bk1l.getFloat(idx2));
            }
         }

         CommonUtils.cftbsub(2L * this.nBluesteinl, ak, 0L, this.ipl, this.nwl, this.wl);
         if (isign > 0L) {
            for(long i = 0L; i < this.nBluesteinl; ++i) {
               long idx1 = 2L * i;
               long idx2 = idx1 + 1L;
               float im = -ak.getFloat(idx1) * this.bk2l.getFloat(idx2) + ak.getFloat(idx2) * this.bk2l.getFloat(idx1);
               ak.setFloat(idx1, ak.getFloat(idx1) * this.bk2l.getFloat(idx1) + ak.getFloat(idx2) * this.bk2l.getFloat(idx2));
               ak.setFloat(idx2, im);
            }
         } else {
            for(long i = 0L; i < this.nBluesteinl; ++i) {
               long idx1 = 2L * i;
               long idx2 = idx1 + 1L;
               float im = ak.getFloat(idx1) * this.bk2l.getFloat(idx2) + ak.getFloat(idx2) * this.bk2l.getFloat(idx1);
               ak.setFloat(idx1, ak.getFloat(idx1) * this.bk2l.getFloat(idx1) - ak.getFloat(idx2) * this.bk2l.getFloat(idx2));
               ak.setFloat(idx2, im);
            }
         }

         CommonUtils.cftfsub(2L * this.nBluesteinl, ak, 0L, this.ipl, this.nwl, this.wl);
         if (isign > 0L) {
            for(long i = 0L; i < this.nl; ++i) {
               long idx1 = 2L * i;
               long idx2 = idx1 + 1L;
               a.setFloat(offa + idx1, this.bk1l.getFloat(idx1) * ak.getFloat(idx1) - this.bk1l.getFloat(idx2) * ak.getFloat(idx2));
               a.setFloat(offa + idx2, this.bk1l.getFloat(idx2) * ak.getFloat(idx1) + this.bk1l.getFloat(idx1) * ak.getFloat(idx2));
            }
         } else {
            for(long i = 0L; i < this.nl; ++i) {
               long idx1 = 2L * i;
               long idx2 = idx1 + 1L;
               a.setFloat(offa + idx1, this.bk1l.getFloat(idx1) * ak.getFloat(idx1) + this.bk1l.getFloat(idx2) * ak.getFloat(idx2));
               a.setFloat(offa + idx2, -this.bk1l.getFloat(idx2) * ak.getFloat(idx1) + this.bk1l.getFloat(idx1) * ak.getFloat(idx2));
            }
         }
      }

   }

   private void bluestein_real_forward(final float[] a, final int offa) {
      final float[] ak = new float[2 * this.nBluestein];
      int threads = ConcurrencyUtils.getNumberOfThreads();
      if (threads > 1 && (long)this.n >= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
         int nthreads = 2;
         if (threads >= 4 && (long)this.n >= CommonUtils.getThreadsBeginN_1D_FFT_4Threads()) {
            nthreads = 4;
         }

         Future<?>[] futures = new Future[nthreads];
         int k = this.n / nthreads;

         for(int i = 0; i < nthreads; ++i) {
            final int firstIdx = i * k;
            final int lastIdx = i == nthreads - 1 ? this.n : firstIdx + k;
            futures[i] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int i = firstIdx; i < lastIdx; ++i) {
                     int idx1 = 2 * i;
                     int idx2 = idx1 + 1;
                     int idx3 = offa + i;
                     ak[idx1] = a[idx3] * FloatFFT_1D.this.bk1[idx1];
                     ak[idx2] = -a[idx3] * FloatFFT_1D.this.bk1[idx2];
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         CommonUtils.cftbsub(2 * this.nBluestein, (float[])ak, 0, this.ip, this.nw, (float[])this.w);
         k = this.nBluestein / nthreads;

         for(int i = 0; i < nthreads; ++i) {
            final int firstIdx = i * k;
            final int lastIdx = i == nthreads - 1 ? this.nBluestein : firstIdx + k;
            futures[i] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int i = firstIdx; i < lastIdx; ++i) {
                     int idx1 = 2 * i;
                     int idx2 = idx1 + 1;
                     float im = ak[idx1] * FloatFFT_1D.this.bk2[idx2] + ak[idx2] * FloatFFT_1D.this.bk2[idx1];
                     ak[idx1] = ak[idx1] * FloatFFT_1D.this.bk2[idx1] - ak[idx2] * FloatFFT_1D.this.bk2[idx2];
                     ak[idx2] = im;
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         for(int i = 0; i < this.n; ++i) {
            int idx1 = 2 * i;
            int idx2 = idx1 + 1;
            int idx3 = offa + i;
            ak[idx1] = a[idx3] * this.bk1[idx1];
            ak[idx2] = -a[idx3] * this.bk1[idx2];
         }

         CommonUtils.cftbsub(2 * this.nBluestein, (float[])ak, 0, this.ip, this.nw, (float[])this.w);

         for(int i = 0; i < this.nBluestein; ++i) {
            int idx1 = 2 * i;
            int idx2 = idx1 + 1;
            float im = ak[idx1] * this.bk2[idx2] + ak[idx2] * this.bk2[idx1];
            ak[idx1] = ak[idx1] * this.bk2[idx1] - ak[idx2] * this.bk2[idx2];
            ak[idx2] = im;
         }
      }

      CommonUtils.cftfsub(2 * this.nBluestein, (float[])ak, 0, this.ip, this.nw, (float[])this.w);
      if (this.n % 2 == 0) {
         a[offa] = this.bk1[0] * ak[0] + this.bk1[1] * ak[1];
         a[offa + 1] = this.bk1[this.n] * ak[this.n] + this.bk1[this.n + 1] * ak[this.n + 1];

         for(int i = 1; i < this.n / 2; ++i) {
            int idx1 = 2 * i;
            int idx2 = idx1 + 1;
            a[offa + idx1] = this.bk1[idx1] * ak[idx1] + this.bk1[idx2] * ak[idx2];
            a[offa + idx2] = -this.bk1[idx2] * ak[idx1] + this.bk1[idx1] * ak[idx2];
         }
      } else {
         a[offa] = this.bk1[0] * ak[0] + this.bk1[1] * ak[1];
         a[offa + 1] = -this.bk1[this.n] * ak[this.n - 1] + this.bk1[this.n - 1] * ak[this.n];

         for(int i = 1; i < (this.n - 1) / 2; ++i) {
            int idx1 = 2 * i;
            int idx2 = idx1 + 1;
            a[offa + idx1] = this.bk1[idx1] * ak[idx1] + this.bk1[idx2] * ak[idx2];
            a[offa + idx2] = -this.bk1[idx2] * ak[idx1] + this.bk1[idx1] * ak[idx2];
         }

         a[offa + this.n - 1] = this.bk1[this.n - 1] * ak[this.n - 1] + this.bk1[this.n] * ak[this.n];
      }

   }

   private void bluestein_real_forward(final FloatLargeArray a, final long offa) {
      final FloatLargeArray ak = new FloatLargeArray(2L * this.nBluesteinl);
      int threads = ConcurrencyUtils.getNumberOfThreads();
      if (threads > 1 && this.nl > CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
         int nthreads = 2;
         if (threads >= 4 && this.nl > CommonUtils.getThreadsBeginN_1D_FFT_4Threads()) {
            nthreads = 4;
         }

         Future<?>[] futures = new Future[nthreads];
         long k = this.nl / (long)nthreads;

         for(int i = 0; i < nthreads; ++i) {
            final long firstIdx = (long)i * k;
            final long lastIdx = i == nthreads - 1 ? this.nl : firstIdx + k;
            futures[i] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(long i = firstIdx; i < lastIdx; ++i) {
                     long idx1 = 2L * i;
                     long idx2 = idx1 + 1L;
                     long idx3 = offa + i;
                     ak.setFloat(idx1, a.getFloat(idx3) * FloatFFT_1D.this.bk1l.getFloat(idx1));
                     ak.setFloat(idx2, -a.getFloat(idx3) * FloatFFT_1D.this.bk1l.getFloat(idx2));
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         CommonUtils.cftbsub(2L * this.nBluesteinl, ak, 0L, this.ipl, this.nwl, this.wl);
         k = this.nBluesteinl / (long)nthreads;

         for(int i = 0; i < nthreads; ++i) {
            final long firstIdx = (long)i * k;
            final long lastIdx = i == nthreads - 1 ? this.nBluesteinl : firstIdx + k;
            futures[i] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(long i = firstIdx; i < lastIdx; ++i) {
                     long idx1 = 2L * i;
                     long idx2 = idx1 + 1L;
                     float im = ak.getFloat(idx1) * FloatFFT_1D.this.bk2l.getFloat(idx2) + ak.getFloat(idx2) * FloatFFT_1D.this.bk2l.getFloat(idx1);
                     ak.setFloat(idx1, ak.getFloat(idx1) * FloatFFT_1D.this.bk2l.getFloat(idx1) - ak.getFloat(idx2) * FloatFFT_1D.this.bk2l.getFloat(idx2));
                     ak.setFloat(idx2, im);
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         for(long i = 0L; i < this.nl; ++i) {
            long idx1 = 2L * i;
            long idx2 = idx1 + 1L;
            long idx3 = offa + i;
            ak.setFloat(idx1, a.getFloat(idx3) * this.bk1l.getFloat(idx1));
            ak.setFloat(idx2, -a.getFloat(idx3) * this.bk1l.getFloat(idx2));
         }

         CommonUtils.cftbsub(2L * this.nBluesteinl, ak, 0L, this.ipl, this.nwl, this.wl);

         for(long i = 0L; i < this.nBluesteinl; ++i) {
            long idx1 = 2L * i;
            long idx2 = idx1 + 1L;
            float im = ak.getFloat(idx1) * this.bk2l.getFloat(idx2) + ak.getFloat(idx2) * this.bk2l.getFloat(idx1);
            ak.setFloat(idx1, ak.getFloat(idx1) * this.bk2l.getFloat(idx1) - ak.getFloat(idx2) * this.bk2l.getFloat(idx2));
            ak.setFloat(idx2, im);
         }
      }

      CommonUtils.cftfsub(2L * this.nBluesteinl, ak, 0L, this.ipl, this.nwl, this.wl);
      if (this.nl % 2L == 0L) {
         a.setFloat(offa, this.bk1l.getFloat(0L) * ak.getFloat(0L) + this.bk1l.getFloat(1L) * ak.getFloat(1L));
         a.setFloat(offa + 1L, this.bk1l.getFloat(this.nl) * ak.getFloat(this.nl) + this.bk1l.getFloat(this.nl + 1L) * ak.getFloat(this.nl + 1L));

         for(long i = 1L; i < this.nl / 2L; ++i) {
            long idx1 = 2L * i;
            long idx2 = idx1 + 1L;
            a.setFloat(offa + idx1, this.bk1l.getFloat(idx1) * ak.getFloat(idx1) + this.bk1l.getFloat(idx2) * ak.getFloat(idx2));
            a.setFloat(offa + idx2, -this.bk1l.getFloat(idx2) * ak.getFloat(idx1) + this.bk1l.getFloat(idx1) * ak.getFloat(idx2));
         }
      } else {
         a.setFloat(offa, this.bk1l.getFloat(0L) * ak.getFloat(0L) + this.bk1l.getFloat(1L) * ak.getFloat(1L));
         a.setFloat(offa + 1L, -this.bk1l.getFloat(this.nl) * ak.getFloat(this.nl - 1L) + this.bk1l.getFloat(this.nl - 1L) * ak.getFloat(this.nl));

         for(long i = 1L; i < (this.nl - 1L) / 2L; ++i) {
            long idx1 = 2L * i;
            long idx2 = idx1 + 1L;
            a.setFloat(offa + idx1, this.bk1l.getFloat(idx1) * ak.getFloat(idx1) + this.bk1l.getFloat(idx2) * ak.getFloat(idx2));
            a.setFloat(offa + idx2, -this.bk1l.getFloat(idx2) * ak.getFloat(idx1) + this.bk1l.getFloat(idx1) * ak.getFloat(idx2));
         }

         a.setFloat(offa + this.nl - 1L, this.bk1l.getFloat(this.nl - 1L) * ak.getFloat(this.nl - 1L) + this.bk1l.getFloat(this.nl) * ak.getFloat(this.nl));
      }

   }

   private void bluestein_real_inverse(final float[] a, final int offa) {
      final float[] ak = new float[2 * this.nBluestein];
      if (this.n % 2 == 0) {
         ak[0] = a[offa] * this.bk1[0];
         ak[1] = a[offa] * this.bk1[1];

         for(int i = 1; i < this.n / 2; ++i) {
            int idx1 = 2 * i;
            int idx2 = idx1 + 1;
            int idx3 = offa + idx1;
            int idx4 = offa + idx2;
            ak[idx1] = a[idx3] * this.bk1[idx1] - a[idx4] * this.bk1[idx2];
            ak[idx2] = a[idx3] * this.bk1[idx2] + a[idx4] * this.bk1[idx1];
         }

         ak[this.n] = a[offa + 1] * this.bk1[this.n];
         ak[this.n + 1] = a[offa + 1] * this.bk1[this.n + 1];

         for(int i = this.n / 2 + 1; i < this.n; ++i) {
            int idx1 = 2 * i;
            int idx2 = idx1 + 1;
            int idx3 = offa + 2 * this.n - idx1;
            int idx4 = idx3 + 1;
            ak[idx1] = a[idx3] * this.bk1[idx1] + a[idx4] * this.bk1[idx2];
            ak[idx2] = a[idx3] * this.bk1[idx2] - a[idx4] * this.bk1[idx1];
         }
      } else {
         ak[0] = a[offa] * this.bk1[0];
         ak[1] = a[offa] * this.bk1[1];

         for(int i = 1; i < (this.n - 1) / 2; ++i) {
            int idx1 = 2 * i;
            int idx2 = idx1 + 1;
            int idx3 = offa + idx1;
            int idx4 = offa + idx2;
            ak[idx1] = a[idx3] * this.bk1[idx1] - a[idx4] * this.bk1[idx2];
            ak[idx2] = a[idx3] * this.bk1[idx2] + a[idx4] * this.bk1[idx1];
         }

         ak[this.n - 1] = a[offa + this.n - 1] * this.bk1[this.n - 1] - a[offa + 1] * this.bk1[this.n];
         ak[this.n] = a[offa + this.n - 1] * this.bk1[this.n] + a[offa + 1] * this.bk1[this.n - 1];
         ak[this.n + 1] = a[offa + this.n - 1] * this.bk1[this.n + 1] + a[offa + 1] * this.bk1[this.n + 2];
         ak[this.n + 2] = a[offa + this.n - 1] * this.bk1[this.n + 2] - a[offa + 1] * this.bk1[this.n + 1];

         for(int i = (this.n - 1) / 2 + 2; i < this.n; ++i) {
            int idx1 = 2 * i;
            int idx2 = idx1 + 1;
            int idx3 = offa + 2 * this.n - idx1;
            int idx4 = idx3 + 1;
            ak[idx1] = a[idx3] * this.bk1[idx1] + a[idx4] * this.bk1[idx2];
            ak[idx2] = a[idx3] * this.bk1[idx2] - a[idx4] * this.bk1[idx1];
         }
      }

      CommonUtils.cftbsub(2 * this.nBluestein, (float[])ak, 0, this.ip, this.nw, (float[])this.w);
      int threads = ConcurrencyUtils.getNumberOfThreads();
      if (threads > 1 && (long)this.n >= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
         int nthreads = 2;
         if (threads >= 4 && (long)this.n >= CommonUtils.getThreadsBeginN_1D_FFT_4Threads()) {
            nthreads = 4;
         }

         Future<?>[] futures = new Future[nthreads];
         int k = this.nBluestein / nthreads;

         for(int i = 0; i < nthreads; ++i) {
            final int firstIdx = i * k;
            final int lastIdx = i == nthreads - 1 ? this.nBluestein : firstIdx + k;
            futures[i] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int i = firstIdx; i < lastIdx; ++i) {
                     int idx1 = 2 * i;
                     int idx2 = idx1 + 1;
                     float im = -ak[idx1] * FloatFFT_1D.this.bk2[idx2] + ak[idx2] * FloatFFT_1D.this.bk2[idx1];
                     ak[idx1] = ak[idx1] * FloatFFT_1D.this.bk2[idx1] + ak[idx2] * FloatFFT_1D.this.bk2[idx2];
                     ak[idx2] = im;
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         CommonUtils.cftfsub(2 * this.nBluestein, (float[])ak, 0, this.ip, this.nw, (float[])this.w);
         k = this.n / nthreads;

         for(int i = 0; i < nthreads; ++i) {
            final int firstIdx = i * k;
            final int lastIdx = i == nthreads - 1 ? this.n : firstIdx + k;
            futures[i] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int i = firstIdx; i < lastIdx; ++i) {
                     int idx1 = 2 * i;
                     int idx2 = idx1 + 1;
                     a[offa + i] = FloatFFT_1D.this.bk1[idx1] * ak[idx1] - FloatFFT_1D.this.bk1[idx2] * ak[idx2];
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         for(int i = 0; i < this.nBluestein; ++i) {
            int idx1 = 2 * i;
            int idx2 = idx1 + 1;
            float im = -ak[idx1] * this.bk2[idx2] + ak[idx2] * this.bk2[idx1];
            ak[idx1] = ak[idx1] * this.bk2[idx1] + ak[idx2] * this.bk2[idx2];
            ak[idx2] = im;
         }

         CommonUtils.cftfsub(2 * this.nBluestein, (float[])ak, 0, this.ip, this.nw, (float[])this.w);

         for(int i = 0; i < this.n; ++i) {
            int idx1 = 2 * i;
            int idx2 = idx1 + 1;
            a[offa + i] = this.bk1[idx1] * ak[idx1] - this.bk1[idx2] * ak[idx2];
         }
      }

   }

   private void bluestein_real_inverse(final FloatLargeArray a, final long offa) {
      final FloatLargeArray ak = new FloatLargeArray(2L * this.nBluesteinl);
      if (this.nl % 2L == 0L) {
         ak.setFloat(0L, a.getFloat(offa) * this.bk1l.getFloat(0L));
         ak.setFloat(1L, a.getFloat(offa) * this.bk1l.getFloat(1L));

         for(long i = 1L; i < this.nl / 2L; ++i) {
            long idx1 = 2L * i;
            long idx2 = idx1 + 1L;
            long idx3 = offa + idx1;
            long idx4 = offa + idx2;
            ak.setFloat(idx1, a.getFloat(idx3) * this.bk1l.getFloat(idx1) - a.getFloat(idx4) * this.bk1l.getFloat(idx2));
            ak.setFloat(idx2, a.getFloat(idx3) * this.bk1l.getFloat(idx2) + a.getFloat(idx4) * this.bk1l.getFloat(idx1));
         }

         ak.setFloat(this.nl, a.getFloat(offa + 1L) * this.bk1l.getFloat(this.nl));
         ak.setFloat(this.nl + 1L, a.getFloat(offa + 1L) * this.bk1l.getFloat(this.nl + 1L));

         for(long i = this.nl / 2L + 1L; i < this.nl; ++i) {
            long idx1 = 2L * i;
            long idx2 = idx1 + 1L;
            long idx3 = offa + 2L * this.nl - idx1;
            long idx4 = idx3 + 1L;
            ak.setFloat(idx1, a.getFloat(idx3) * this.bk1l.getFloat(idx1) + a.getFloat(idx4) * this.bk1l.getFloat(idx2));
            ak.setFloat(idx2, a.getFloat(idx3) * this.bk1l.getFloat(idx2) - a.getFloat(idx4) * this.bk1l.getFloat(idx1));
         }
      } else {
         ak.setFloat(0L, a.getFloat(offa) * this.bk1l.getFloat(0L));
         ak.setFloat(1L, a.getFloat(offa) * this.bk1l.getFloat(1L));

         for(long i = 1L; i < (this.nl - 1L) / 2L; ++i) {
            long idx1 = 2L * i;
            long idx2 = idx1 + 1L;
            long idx3 = offa + idx1;
            long idx4 = offa + idx2;
            ak.setFloat(idx1, a.getFloat(idx3) * this.bk1l.getFloat(idx1) - a.getFloat(idx4) * this.bk1l.getFloat(idx2));
            ak.setFloat(idx2, a.getFloat(idx3) * this.bk1l.getFloat(idx2) + a.getFloat(idx4) * this.bk1l.getFloat(idx1));
         }

         ak.setFloat(this.nl - 1L, a.getFloat(offa + this.nl - 1L) * this.bk1l.getFloat(this.nl - 1L) - a.getFloat(offa + 1L) * this.bk1l.getFloat(this.nl));
         ak.setFloat(this.nl, a.getFloat(offa + this.nl - 1L) * this.bk1l.getFloat(this.nl) + a.getFloat(offa + 1L) * this.bk1l.getFloat(this.nl - 1L));
         ak.setFloat(this.nl + 1L, a.getFloat(offa + this.nl - 1L) * this.bk1l.getFloat(this.nl + 1L) + a.getFloat(offa + 1L) * this.bk1l.getFloat(this.nl + 2L));
         ak.setFloat(this.nl + 2L, a.getFloat(offa + this.nl - 1L) * this.bk1l.getFloat(this.nl + 2L) - a.getFloat(offa + 1L) * this.bk1l.getFloat(this.nl + 1L));

         for(long i = (this.nl - 1L) / 2L + 2L; i < this.nl; ++i) {
            long idx1 = 2L * i;
            long idx2 = idx1 + 1L;
            long idx3 = offa + 2L * this.nl - idx1;
            long idx4 = idx3 + 1L;
            ak.setFloat(idx1, a.getFloat(idx3) * this.bk1l.getFloat(idx1) + a.getFloat(idx4) * this.bk1l.getFloat(idx2));
            ak.setFloat(idx2, a.getFloat(idx3) * this.bk1l.getFloat(idx2) - a.getFloat(idx4) * this.bk1l.getFloat(idx1));
         }
      }

      CommonUtils.cftbsub(2L * this.nBluesteinl, ak, 0L, this.ipl, this.nwl, this.wl);
      int threads = ConcurrencyUtils.getNumberOfThreads();
      if (threads > 1 && this.nl > CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
         int nthreads = 2;
         if (threads >= 4 && this.nl > CommonUtils.getThreadsBeginN_1D_FFT_4Threads()) {
            nthreads = 4;
         }

         Future<?>[] futures = new Future[nthreads];
         long k = this.nBluesteinl / (long)nthreads;

         for(int i = 0; i < nthreads; ++i) {
            final long firstIdx = (long)i * k;
            final long lastIdx = i == nthreads - 1 ? this.nBluesteinl : firstIdx + k;
            futures[i] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(long i = firstIdx; i < lastIdx; ++i) {
                     long idx1 = 2L * i;
                     long idx2 = idx1 + 1L;
                     float im = -ak.getFloat(idx1) * FloatFFT_1D.this.bk2l.getFloat(idx2) + ak.getFloat(idx2) * FloatFFT_1D.this.bk2l.getFloat(idx1);
                     ak.setFloat(idx1, ak.getFloat(idx1) * FloatFFT_1D.this.bk2l.getFloat(idx1) + ak.getFloat(idx2) * FloatFFT_1D.this.bk2l.getFloat(idx2));
                     ak.setFloat(idx2, im);
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         CommonUtils.cftfsub(2L * this.nBluesteinl, ak, 0L, this.ipl, this.nwl, this.wl);
         k = this.nl / (long)nthreads;

         for(int i = 0; i < nthreads; ++i) {
            final long firstIdx = (long)i * k;
            final long lastIdx = i == nthreads - 1 ? this.nl : firstIdx + k;
            futures[i] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(long i = firstIdx; i < lastIdx; ++i) {
                     long idx1 = 2L * i;
                     long idx2 = idx1 + 1L;
                     a.setFloat(offa + i, FloatFFT_1D.this.bk1l.getFloat(idx1) * ak.getFloat(idx1) - FloatFFT_1D.this.bk1l.getFloat(idx2) * ak.getFloat(idx2));
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         for(long i = 0L; i < this.nBluesteinl; ++i) {
            long idx1 = 2L * i;
            long idx2 = idx1 + 1L;
            float im = -ak.getFloat(idx1) * this.bk2l.getFloat(idx2) + ak.getFloat(idx2) * this.bk2l.getFloat(idx1);
            ak.setFloat(idx1, ak.getFloat(idx1) * this.bk2l.getFloat(idx1) + ak.getFloat(idx2) * this.bk2l.getFloat(idx2));
            ak.setFloat(idx2, im);
         }

         CommonUtils.cftfsub(2L * this.nBluesteinl, ak, 0L, this.ipl, this.nwl, this.wl);

         for(long i = 0L; i < this.nl; ++i) {
            long idx1 = 2L * i;
            long idx2 = idx1 + 1L;
            a.setFloat(offa + i, this.bk1l.getFloat(idx1) * ak.getFloat(idx1) - this.bk1l.getFloat(idx2) * ak.getFloat(idx2));
         }
      }

   }

   private void bluestein_real_inverse2(final float[] a, final int offa) {
      final float[] ak = new float[2 * this.nBluestein];
      int threads = ConcurrencyUtils.getNumberOfThreads();
      if (threads > 1 && (long)this.n >= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
         int nthreads = 2;
         if (threads >= 4 && (long)this.n >= CommonUtils.getThreadsBeginN_1D_FFT_4Threads()) {
            nthreads = 4;
         }

         Future<?>[] futures = new Future[nthreads];
         int k = this.n / nthreads;

         for(int i = 0; i < nthreads; ++i) {
            final int firstIdx = i * k;
            final int lastIdx = i == nthreads - 1 ? this.n : firstIdx + k;
            futures[i] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int i = firstIdx; i < lastIdx; ++i) {
                     int idx1 = 2 * i;
                     int idx2 = idx1 + 1;
                     int idx3 = offa + i;
                     ak[idx1] = a[idx3] * FloatFFT_1D.this.bk1[idx1];
                     ak[idx2] = a[idx3] * FloatFFT_1D.this.bk1[idx2];
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         CommonUtils.cftbsub(2 * this.nBluestein, (float[])ak, 0, this.ip, this.nw, (float[])this.w);
         k = this.nBluestein / nthreads;

         for(int i = 0; i < nthreads; ++i) {
            final int firstIdx = i * k;
            final int lastIdx = i == nthreads - 1 ? this.nBluestein : firstIdx + k;
            futures[i] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int i = firstIdx; i < lastIdx; ++i) {
                     int idx1 = 2 * i;
                     int idx2 = idx1 + 1;
                     float im = -ak[idx1] * FloatFFT_1D.this.bk2[idx2] + ak[idx2] * FloatFFT_1D.this.bk2[idx1];
                     ak[idx1] = ak[idx1] * FloatFFT_1D.this.bk2[idx1] + ak[idx2] * FloatFFT_1D.this.bk2[idx2];
                     ak[idx2] = im;
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         for(int i = 0; i < this.n; ++i) {
            int idx1 = 2 * i;
            int idx2 = idx1 + 1;
            int idx3 = offa + i;
            ak[idx1] = a[idx3] * this.bk1[idx1];
            ak[idx2] = a[idx3] * this.bk1[idx2];
         }

         CommonUtils.cftbsub(2 * this.nBluestein, (float[])ak, 0, this.ip, this.nw, (float[])this.w);

         for(int i = 0; i < this.nBluestein; ++i) {
            int idx1 = 2 * i;
            int idx2 = idx1 + 1;
            float im = -ak[idx1] * this.bk2[idx2] + ak[idx2] * this.bk2[idx1];
            ak[idx1] = ak[idx1] * this.bk2[idx1] + ak[idx2] * this.bk2[idx2];
            ak[idx2] = im;
         }
      }

      CommonUtils.cftfsub(2 * this.nBluestein, (float[])ak, 0, this.ip, this.nw, (float[])this.w);
      if (this.n % 2 == 0) {
         a[offa] = this.bk1[0] * ak[0] - this.bk1[1] * ak[1];
         a[offa + 1] = this.bk1[this.n] * ak[this.n] - this.bk1[this.n + 1] * ak[this.n + 1];

         for(int i = 1; i < this.n / 2; ++i) {
            int idx1 = 2 * i;
            int idx2 = idx1 + 1;
            a[offa + idx1] = this.bk1[idx1] * ak[idx1] - this.bk1[idx2] * ak[idx2];
            a[offa + idx2] = this.bk1[idx2] * ak[idx1] + this.bk1[idx1] * ak[idx2];
         }
      } else {
         a[offa] = this.bk1[0] * ak[0] - this.bk1[1] * ak[1];
         a[offa + 1] = this.bk1[this.n] * ak[this.n - 1] + this.bk1[this.n - 1] * ak[this.n];

         for(int i = 1; i < (this.n - 1) / 2; ++i) {
            int idx1 = 2 * i;
            int idx2 = idx1 + 1;
            a[offa + idx1] = this.bk1[idx1] * ak[idx1] - this.bk1[idx2] * ak[idx2];
            a[offa + idx2] = this.bk1[idx2] * ak[idx1] + this.bk1[idx1] * ak[idx2];
         }

         a[offa + this.n - 1] = this.bk1[this.n - 1] * ak[this.n - 1] - this.bk1[this.n] * ak[this.n];
      }

   }

   private void bluestein_real_inverse2(final FloatLargeArray a, final long offa) {
      final FloatLargeArray ak = new FloatLargeArray(2L * this.nBluesteinl);
      int threads = ConcurrencyUtils.getNumberOfThreads();
      if (threads > 1 && this.nl > CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
         int nthreads = 2;
         if (threads >= 4 && this.nl > CommonUtils.getThreadsBeginN_1D_FFT_4Threads()) {
            nthreads = 4;
         }

         Future<?>[] futures = new Future[nthreads];
         long k = this.nl / (long)nthreads;

         for(int i = 0; i < nthreads; ++i) {
            final long firstIdx = (long)i * k;
            final long lastIdx = i == nthreads - 1 ? this.nl : firstIdx + k;
            futures[i] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(long i = firstIdx; i < lastIdx; ++i) {
                     long idx1 = 2L * i;
                     long idx2 = idx1 + 1L;
                     long idx3 = offa + i;
                     ak.setFloat(idx1, a.getFloat(idx3) * FloatFFT_1D.this.bk1l.getFloat(idx1));
                     ak.setFloat(idx2, a.getFloat(idx3) * FloatFFT_1D.this.bk1l.getFloat(idx2));
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         CommonUtils.cftbsub(2L * this.nBluesteinl, ak, 0L, this.ipl, this.nwl, this.wl);
         k = this.nBluesteinl / (long)nthreads;

         for(int i = 0; i < nthreads; ++i) {
            final long firstIdx = (long)i * k;
            final long lastIdx = i == nthreads - 1 ? this.nBluesteinl : firstIdx + k;
            futures[i] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(long i = firstIdx; i < lastIdx; ++i) {
                     long idx1 = 2L * i;
                     long idx2 = idx1 + 1L;
                     float im = -ak.getFloat(idx1) * FloatFFT_1D.this.bk2l.getFloat(idx2) + ak.getFloat(idx2) * FloatFFT_1D.this.bk2l.getFloat(idx1);
                     ak.setFloat(idx1, ak.getFloat(idx1) * FloatFFT_1D.this.bk2l.getFloat(idx1) + ak.getFloat(idx2) * FloatFFT_1D.this.bk2l.getFloat(idx2));
                     ak.setFloat(idx2, im);
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(FloatFFT_1D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         for(long i = 0L; i < this.nl; ++i) {
            long idx1 = 2L * i;
            long idx2 = idx1 + 1L;
            long idx3 = offa + i;
            ak.setFloat(idx1, a.getFloat(idx3) * this.bk1l.getFloat(idx1));
            ak.setFloat(idx2, a.getFloat(idx3) * this.bk1l.getFloat(idx2));
         }

         CommonUtils.cftbsub(2L * this.nBluesteinl, ak, 0L, this.ipl, this.nwl, this.wl);

         for(long i = 0L; i < this.nBluesteinl; ++i) {
            long idx1 = 2L * i;
            long idx2 = idx1 + 1L;
            float im = -ak.getFloat(idx1) * this.bk2l.getFloat(idx2) + ak.getFloat(idx2) * this.bk2l.getFloat(idx1);
            ak.setFloat(idx1, ak.getFloat(idx1) * this.bk2l.getFloat(idx1) + ak.getFloat(idx2) * this.bk2l.getFloat(idx2));
            ak.setFloat(idx2, im);
         }
      }

      CommonUtils.cftfsub(2L * this.nBluesteinl, ak, 0L, this.ipl, this.nwl, this.wl);
      if (this.nl % 2L == 0L) {
         a.setFloat(offa, this.bk1l.getFloat(0L) * ak.getFloat(0L) - this.bk1l.getFloat(1L) * ak.getFloat(1L));
         a.setFloat(offa + 1L, this.bk1l.getFloat(this.nl) * ak.getFloat(this.nl) - this.bk1l.getFloat(this.nl + 1L) * ak.getFloat(this.nl + 1L));

         for(long i = 1L; i < this.nl / 2L; ++i) {
            long idx1 = 2L * i;
            long idx2 = idx1 + 1L;
            a.setFloat(offa + idx1, this.bk1l.getFloat(idx1) * ak.getFloat(idx1) - this.bk1l.getFloat(idx2) * ak.getFloat(idx2));
            a.setFloat(offa + idx2, this.bk1l.getFloat(idx2) * ak.getFloat(idx1) + this.bk1l.getFloat(idx1) * ak.getFloat(idx2));
         }
      } else {
         a.setFloat(offa, this.bk1l.getFloat(0L) * ak.getFloat(0L) - this.bk1l.getFloat(1L) * ak.getFloat(1L));
         a.setFloat(offa + 1L, this.bk1l.getFloat(this.nl) * ak.getFloat(this.nl - 1L) + this.bk1l.getFloat(this.nl - 1L) * ak.getFloat(this.nl));

         for(long i = 1L; i < (this.nl - 1L) / 2L; ++i) {
            long idx1 = 2L * i;
            long idx2 = idx1 + 1L;
            a.setFloat(offa + idx1, this.bk1l.getFloat(idx1) * ak.getFloat(idx1) - this.bk1l.getFloat(idx2) * ak.getFloat(idx2));
            a.setFloat(offa + idx2, this.bk1l.getFloat(idx2) * ak.getFloat(idx1) + this.bk1l.getFloat(idx1) * ak.getFloat(idx2));
         }

         a.setFloat(offa + this.nl - 1L, this.bk1l.getFloat(this.nl - 1L) * ak.getFloat(this.nl - 1L) - this.bk1l.getFloat(this.nl) * ak.getFloat(this.nl));
      }

   }

   void rfftf(float[] a, int offa) {
      if (this.n != 1) {
         float[] ch = new float[this.n];
         int twon = 2 * this.n;
         int nf = (int)this.wtable_r[1 + twon];
         int na = 1;
         int l2 = this.n;
         int iw = twon - 1;

         for(int k1 = 1; k1 <= nf; ++k1) {
            int kh = nf - k1;
            int ipll = (int)this.wtable_r[kh + 2 + twon];
            int l1 = l2 / ipll;
            int ido = this.n / l2;
            int idl1 = ido * l1;
            iw -= (ipll - 1) * ido;
            na = 1 - na;
            switch (ipll) {
               case 2:
                  if (na == 0) {
                     this.radf2(ido, l1, a, offa, ch, 0, iw);
                  } else {
                     this.radf2(ido, l1, ch, 0, a, offa, iw);
                  }
                  break;
               case 3:
                  if (na == 0) {
                     this.radf3(ido, l1, a, offa, ch, 0, iw);
                  } else {
                     this.radf3(ido, l1, ch, 0, a, offa, iw);
                  }
                  break;
               case 4:
                  if (na == 0) {
                     this.radf4(ido, l1, a, offa, ch, 0, iw);
                  } else {
                     this.radf4(ido, l1, ch, 0, a, offa, iw);
                  }
                  break;
               case 5:
                  if (na == 0) {
                     this.radf5(ido, l1, a, offa, ch, 0, iw);
                  } else {
                     this.radf5(ido, l1, ch, 0, a, offa, iw);
                  }
                  break;
               default:
                  if (ido == 1) {
                     na = 1 - na;
                  }

                  if (na == 0) {
                     this.radfg(ido, ipll, l1, idl1, a, offa, ch, 0, iw);
                     na = 1;
                  } else {
                     this.radfg(ido, ipll, l1, idl1, ch, 0, a, offa, iw);
                     na = 0;
                  }
            }

            l2 = l1;
         }

         if (na != 1) {
            System.arraycopy(ch, 0, a, offa, this.n);
         }
      }
   }

   void rfftf(FloatLargeArray a, long offa) {
      if (this.nl != 1L) {
         FloatLargeArray ch = new FloatLargeArray(this.nl);
         long twon = 2L * this.nl;
         long nf = (long)this.wtable_rl.getFloat(1L + twon);
         long na = 1L;
         long l2 = this.nl;
         long iw = twon - 1L;

         for(long k1 = 1L; k1 <= nf; ++k1) {
            long kh = nf - k1;
            int ipll = (int)this.wtable_rl.getFloat(kh + 2L + twon);
            long l1 = l2 / (long)ipll;
            long ido = this.nl / l2;
            long idl1 = ido * l1;
            iw -= (long)(ipll - 1) * ido;
            na = 1L - na;
            switch (ipll) {
               case 2:
                  if (na == 0L) {
                     this.radf2(ido, l1, a, offa, ch, 0L, iw);
                  } else {
                     this.radf2(ido, l1, ch, 0L, a, offa, iw);
                  }
                  break;
               case 3:
                  if (na == 0L) {
                     this.radf3(ido, l1, a, offa, ch, 0L, iw);
                  } else {
                     this.radf3(ido, l1, ch, 0L, a, offa, iw);
                  }
                  break;
               case 4:
                  if (na == 0L) {
                     this.radf4(ido, l1, a, offa, ch, 0L, iw);
                  } else {
                     this.radf4(ido, l1, ch, 0L, a, offa, iw);
                  }
                  break;
               case 5:
                  if (na == 0L) {
                     this.radf5(ido, l1, a, offa, ch, 0L, iw);
                  } else {
                     this.radf5(ido, l1, ch, 0L, a, offa, iw);
                  }
                  break;
               default:
                  if (ido == 1L) {
                     na = 1L - na;
                  }

                  if (na == 0L) {
                     this.radfg(ido, (long)ipll, l1, idl1, a, offa, ch, 0L, iw);
                     na = 1L;
                  } else {
                     this.radfg(ido, (long)ipll, l1, idl1, ch, 0L, a, offa, iw);
                     na = 0L;
                  }
            }

            l2 = l1;
         }

         if (na != 1L) {
            LargeArrayUtils.arraycopy(ch, 0L, a, offa, this.nl);
         }
      }
   }

   void rfftb(float[] a, int offa) {
      if (this.n != 1) {
         float[] ch = new float[this.n];
         int twon = 2 * this.n;
         int nf = (int)this.wtable_r[1 + twon];
         int na = 0;
         int l1 = 1;
         int iw = this.n;

         for(int k1 = 1; k1 <= nf; ++k1) {
            int ipll = (int)this.wtable_r[k1 + 1 + twon];
            int l2 = ipll * l1;
            int ido = this.n / l2;
            int idl1 = ido * l1;
            switch (ipll) {
               case 2:
                  if (na == 0) {
                     this.radb2(ido, l1, a, offa, ch, 0, iw);
                  } else {
                     this.radb2(ido, l1, ch, 0, a, offa, iw);
                  }

                  na = 1 - na;
                  break;
               case 3:
                  if (na == 0) {
                     this.radb3(ido, l1, a, offa, ch, 0, iw);
                  } else {
                     this.radb3(ido, l1, ch, 0, a, offa, iw);
                  }

                  na = 1 - na;
                  break;
               case 4:
                  if (na == 0) {
                     this.radb4(ido, l1, a, offa, ch, 0, iw);
                  } else {
                     this.radb4(ido, l1, ch, 0, a, offa, iw);
                  }

                  na = 1 - na;
                  break;
               case 5:
                  if (na == 0) {
                     this.radb5(ido, l1, a, offa, ch, 0, iw);
                  } else {
                     this.radb5(ido, l1, ch, 0, a, offa, iw);
                  }

                  na = 1 - na;
                  break;
               default:
                  if (na == 0) {
                     this.radbg(ido, ipll, l1, idl1, a, offa, ch, 0, iw);
                  } else {
                     this.radbg(ido, ipll, l1, idl1, ch, 0, a, offa, iw);
                  }

                  if (ido == 1) {
                     na = 1 - na;
                  }
            }

            l1 = l2;
            iw += (ipll - 1) * ido;
         }

         if (na != 0) {
            System.arraycopy(ch, 0, a, offa, this.n);
         }
      }
   }

   void rfftb(FloatLargeArray a, long offa) {
      if (this.nl != 1L) {
         FloatLargeArray ch = new FloatLargeArray(this.nl);
         long twon = 2L * this.nl;
         long nf = (long)this.wtable_rl.getFloat(1L + twon);
         long na = 0L;
         long l1 = 1L;
         long iw = this.nl;

         for(long k1 = 1L; k1 <= nf; ++k1) {
            int ipll = (int)this.wtable_rl.getFloat(k1 + 1L + twon);
            long l2 = (long)ipll * l1;
            long ido = this.nl / l2;
            long idl1 = ido * l1;
            switch (ipll) {
               case 2:
                  if (na == 0L) {
                     this.radb2(ido, l1, a, offa, ch, 0L, iw);
                  } else {
                     this.radb2(ido, l1, ch, 0L, a, offa, iw);
                  }

                  na = 1L - na;
                  break;
               case 3:
                  if (na == 0L) {
                     this.radb3(ido, l1, a, offa, ch, 0L, iw);
                  } else {
                     this.radb3(ido, l1, ch, 0L, a, offa, iw);
                  }

                  na = 1L - na;
                  break;
               case 4:
                  if (na == 0L) {
                     this.radb4(ido, l1, a, offa, ch, 0L, iw);
                  } else {
                     this.radb4(ido, l1, ch, 0L, a, offa, iw);
                  }

                  na = 1L - na;
                  break;
               case 5:
                  if (na == 0L) {
                     this.radb5(ido, l1, a, offa, ch, 0L, iw);
                  } else {
                     this.radb5(ido, l1, ch, 0L, a, offa, iw);
                  }

                  na = 1L - na;
                  break;
               default:
                  if (na == 0L) {
                     this.radbg(ido, (long)ipll, l1, idl1, a, offa, ch, 0L, iw);
                  } else {
                     this.radbg(ido, (long)ipll, l1, idl1, ch, 0L, a, offa, iw);
                  }

                  if (ido == 1L) {
                     na = 1L - na;
                  }
            }

            l1 = l2;
            iw += (long)(ipll - 1) * ido;
         }

         if (na != 0L) {
            LargeArrayUtils.arraycopy(ch, 0L, a, offa, this.nl);
         }
      }
   }

   void radf2(int ido, int l1, float[] in, int in_off, float[] out, int out_off, int offset) {
      int iw1 = offset;
      int idx0 = l1 * ido;
      int idx1 = 2 * ido;

      for(int k = 0; k < l1; ++k) {
         int oidx1 = out_off + k * idx1;
         int oidx2 = oidx1 + idx1 - 1;
         int iidx1 = in_off + k * ido;
         int iidx2 = iidx1 + idx0;
         float i1r = in[iidx1];
         float i2r = in[iidx2];
         out[oidx1] = i1r + i2r;
         out[oidx2] = i1r - i2r;
      }

      if (ido >= 2) {
         if (ido != 2) {
            for(int k = 0; k < l1; ++k) {
               idx1 = k * ido;
               int idx2 = 2 * idx1;
               int idx3 = idx2 + ido;
               int idx4 = idx1 + idx0;

               for(int i = 2; i < ido; i += 2) {
                  int ic = ido - i;
                  int widx1 = i - 1 + iw1;
                  int oidx1 = out_off + i + idx2;
                  int oidx2 = out_off + ic + idx3;
                  int iidx1 = in_off + i + idx1;
                  int iidx2 = in_off + i + idx4;
                  float a1i = in[iidx1 - 1];
                  float a1r = in[iidx1];
                  float a2i = in[iidx2 - 1];
                  float a2r = in[iidx2];
                  float w1r = this.wtable_r[widx1 - 1];
                  float w1i = this.wtable_r[widx1];
                  float t1r = w1r * a2i + w1i * a2r;
                  float t1i = w1r * a2r - w1i * a2i;
                  out[oidx1] = a1r + t1i;
                  out[oidx1 - 1] = a1i + t1r;
                  out[oidx2] = t1i - a1r;
                  out[oidx2 - 1] = a1i - t1r;
               }
            }

            if (ido % 2 == 1) {
               return;
            }
         }

         int idx2 = 2 * idx1;

         for(int k = 0; k < l1; ++k) {
            idx1 = k * ido;
            int oidx1 = out_off + idx2 + ido;
            int iidx1 = in_off + ido - 1 + idx1;
            out[oidx1] = -in[iidx1 + idx0];
            out[oidx1 - 1] = in[iidx1];
         }

      }
   }

   void radf2(long ido, long l1, FloatLargeArray in, long in_off, FloatLargeArray out, long out_off, long offset) {
      long iw1 = offset;
      long idx0 = l1 * ido;
      long idx1 = 2L * ido;

      for(long k = 0L; k < l1; ++k) {
         long oidx1 = out_off + k * idx1;
         long oidx2 = oidx1 + idx1 - 1L;
         long iidx1 = in_off + k * ido;
         long iidx2 = iidx1 + idx0;
         float i1r = in.getFloat(iidx1);
         float i2r = in.getFloat(iidx2);
         out.setFloat(oidx1, i1r + i2r);
         out.setFloat(oidx2, i1r - i2r);
      }

      if (ido >= 2L) {
         if (ido != 2L) {
            for(long k = 0L; k < l1; ++k) {
               idx1 = k * ido;
               long idx2 = 2L * idx1;
               long idx3 = idx2 + ido;
               long idx4 = idx1 + idx0;

               for(long i = 2L; i < ido; i += 2L) {
                  long ic = ido - i;
                  long widx1 = i - 1L + iw1;
                  long oidx1 = out_off + i + idx2;
                  long oidx2 = out_off + ic + idx3;
                  long iidx1 = in_off + i + idx1;
                  long iidx2 = in_off + i + idx4;
                  float a1i = in.getFloat(iidx1 - 1L);
                  float a1r = in.getFloat(iidx1);
                  float a2i = in.getFloat(iidx2 - 1L);
                  float a2r = in.getFloat(iidx2);
                  float w1r = this.wtable_rl.getFloat(widx1 - 1L);
                  float w1i = this.wtable_rl.getFloat(widx1);
                  float t1r = w1r * a2i + w1i * a2r;
                  float t1i = w1r * a2r - w1i * a2i;
                  out.setFloat(oidx1, a1r + t1i);
                  out.setFloat(oidx1 - 1L, a1i + t1r);
                  out.setFloat(oidx2, t1i - a1r);
                  out.setFloat(oidx2 - 1L, a1i - t1r);
               }
            }

            if (ido % 2L == 1L) {
               return;
            }
         }

         long idx2 = 2L * idx1;

         for(long k = 0L; k < l1; ++k) {
            idx1 = k * ido;
            long oidx1 = out_off + idx2 + ido;
            long iidx1 = in_off + ido - 1L + idx1;
            out.setFloat(oidx1, -in.getFloat(iidx1 + idx0));
            out.setFloat(oidx1 - 1L, in.getFloat(iidx1));
         }

      }
   }

   void radb2(int ido, int l1, float[] in, int in_off, float[] out, int out_off, int offset) {
      int iw1 = offset;
      int idx0 = l1 * ido;

      for(int k = 0; k < l1; ++k) {
         int idx1 = k * ido;
         int idx2 = 2 * idx1;
         int idx3 = idx2 + ido;
         int oidx1 = out_off + idx1;
         int iidx1 = in_off + idx2;
         int iidx2 = in_off + ido - 1 + idx3;
         float i1r = in[iidx1];
         float i2r = in[iidx2];
         out[oidx1] = i1r + i2r;
         out[oidx1 + idx0] = i1r - i2r;
      }

      if (ido >= 2) {
         if (ido != 2) {
            for(int k = 0; k < l1; ++k) {
               int idx1 = k * ido;
               int idx2 = 2 * idx1;
               int idx3 = idx2 + ido;
               int idx4 = idx1 + idx0;

               for(int i = 2; i < ido; i += 2) {
                  int ic = ido - i;
                  int idx5 = i - 1 + iw1;
                  int idx6 = out_off + i;
                  int idx7 = in_off + i;
                  int idx8 = in_off + ic;
                  float w1r = this.wtable_r[idx5 - 1];
                  float w1i = this.wtable_r[idx5];
                  int iidx1 = idx7 + idx2;
                  int iidx2 = idx8 + idx3;
                  int oidx1 = idx6 + idx1;
                  int oidx2 = idx6 + idx4;
                  float t1r = in[iidx1 - 1] - in[iidx2 - 1];
                  float t1i = in[iidx1] + in[iidx2];
                  float i1i = in[iidx1];
                  float i1r = in[iidx1 - 1];
                  float i2i = in[iidx2];
                  float i2r = in[iidx2 - 1];
                  out[oidx1 - 1] = i1r + i2r;
                  out[oidx1] = i1i - i2i;
                  out[oidx2 - 1] = w1r * t1r - w1i * t1i;
                  out[oidx2] = w1r * t1i + w1i * t1r;
               }
            }

            if (ido % 2 == 1) {
               return;
            }
         }

         for(int k = 0; k < l1; ++k) {
            int idx1 = k * ido;
            int idx2 = 2 * idx1;
            int oidx1 = out_off + ido - 1 + idx1;
            int iidx1 = in_off + idx2 + ido;
            out[oidx1] = 2.0F * in[iidx1 - 1];
            out[oidx1 + idx0] = -2.0F * in[iidx1];
         }

      }
   }

   void radb2(long ido, long l1, FloatLargeArray in, long in_off, FloatLargeArray out, long out_off, long offset) {
      long iw1 = offset;
      long idx0 = l1 * ido;

      for(long k = 0L; k < l1; ++k) {
         long idx1 = k * ido;
         long idx2 = 2L * idx1;
         long idx3 = idx2 + ido;
         long oidx1 = out_off + idx1;
         long iidx1 = in_off + idx2;
         long iidx2 = in_off + ido - 1L + idx3;
         float i1r = in.getFloat(iidx1);
         float i2r = in.getFloat(iidx2);
         out.setFloat(oidx1, i1r + i2r);
         out.setFloat(oidx1 + idx0, i1r - i2r);
      }

      if (ido >= 2L) {
         if (ido != 2L) {
            for(long k = 0L; k < l1; ++k) {
               long idx1 = k * ido;
               long idx2 = 2L * idx1;
               long idx3 = idx2 + ido;
               long idx4 = idx1 + idx0;

               for(long i = 2L; i < ido; i += 2L) {
                  long ic = ido - i;
                  long idx5 = i - 1L + iw1;
                  long idx6 = out_off + i;
                  long idx7 = in_off + i;
                  long idx8 = in_off + ic;
                  float w1r = this.wtable_rl.getFloat(idx5 - 1L);
                  float w1i = this.wtable_rl.getFloat(idx5);
                  long iidx1 = idx7 + idx2;
                  long iidx2 = idx8 + idx3;
                  long oidx1 = idx6 + idx1;
                  long oidx2 = idx6 + idx4;
                  float t1r = in.getFloat(iidx1 - 1L) - in.getFloat(iidx2 - 1L);
                  float t1i = in.getFloat(iidx1) + in.getFloat(iidx2);
                  float i1i = in.getFloat(iidx1);
                  float i1r = in.getFloat(iidx1 - 1L);
                  float i2i = in.getFloat(iidx2);
                  float i2r = in.getFloat(iidx2 - 1L);
                  out.setFloat(oidx1 - 1L, i1r + i2r);
                  out.setFloat(oidx1, i1i - i2i);
                  out.setFloat(oidx2 - 1L, w1r * t1r - w1i * t1i);
                  out.setFloat(oidx2, w1r * t1i + w1i * t1r);
               }
            }

            if (ido % 2L == 1L) {
               return;
            }
         }

         for(long k = 0L; k < l1; ++k) {
            long idx1 = k * ido;
            long idx2 = 2L * idx1;
            long oidx1 = out_off + ido - 1L + idx1;
            long iidx1 = in_off + idx2 + ido;
            out.setFloat(oidx1, 2.0F * in.getFloat(iidx1 - 1L));
            out.setFloat(oidx1 + idx0, -2.0F * in.getFloat(iidx1));
         }

      }
   }

   void radf3(int ido, int l1, float[] in, int in_off, float[] out, int out_off, int offset) {
      float taur = -0.5F;
      float taui = 0.8660254F;
      int iw1 = offset;
      int iw2 = offset + ido;
      int idx0 = l1 * ido;

      for(int k = 0; k < l1; ++k) {
         int idx1 = k * ido;
         int idx3 = 2 * idx0;
         int idx4 = (3 * k + 1) * ido;
         int iidx1 = in_off + idx1;
         int iidx2 = iidx1 + idx0;
         int iidx3 = iidx1 + idx3;
         float i1r = in[iidx1];
         float i2r = in[iidx2];
         float i3r = in[iidx3];
         float cr2 = i2r + i3r;
         out[out_off + 3 * idx1] = i1r + cr2;
         out[out_off + idx4 + ido] = 0.8660254F * (i3r - i2r);
         out[out_off + ido - 1 + idx4] = i1r + -0.5F * cr2;
      }

      if (ido != 1) {
         for(int k = 0; k < l1; ++k) {
            int idx3 = k * ido;
            int idx4 = 3 * idx3;
            int idx5 = idx3 + idx0;
            int idx6 = idx5 + idx0;
            int idx7 = idx4 + ido;
            int idx8 = idx7 + ido;

            for(int i = 2; i < ido; i += 2) {
               int ic = ido - i;
               int widx1 = i - 1 + iw1;
               int widx2 = i - 1 + iw2;
               float w1r = this.wtable_r[widx1 - 1];
               float w1i = this.wtable_r[widx1];
               float w2r = this.wtable_r[widx2 - 1];
               float w2i = this.wtable_r[widx2];
               int idx9 = in_off + i;
               int idx10 = out_off + i;
               int idx11 = out_off + ic;
               int iidx1 = idx9 + idx3;
               int iidx2 = idx9 + idx5;
               int iidx3 = idx9 + idx6;
               float i1i = in[iidx1 - 1];
               float i1r = in[iidx1];
               float i2i = in[iidx2 - 1];
               float i2r = in[iidx2];
               float i3i = in[iidx3 - 1];
               float i3r = in[iidx3];
               float dr2 = w1r * i2i + w1i * i2r;
               float di2 = w1r * i2r - w1i * i2i;
               float dr3 = w2r * i3i + w2i * i3r;
               float di3 = w2r * i3r - w2i * i3i;
               float cr2 = dr2 + dr3;
               float ci2 = di2 + di3;
               float tr2 = i1i + -0.5F * cr2;
               float ti2 = i1r + -0.5F * ci2;
               float tr3 = 0.8660254F * (di2 - di3);
               float ti3 = 0.8660254F * (dr3 - dr2);
               int oidx1 = idx10 + idx4;
               int oidx2 = idx11 + idx7;
               int oidx3 = idx10 + idx8;
               out[oidx1 - 1] = i1i + cr2;
               out[oidx1] = i1r + ci2;
               out[oidx2 - 1] = tr2 - tr3;
               out[oidx2] = ti3 - ti2;
               out[oidx3 - 1] = tr2 + tr3;
               out[oidx3] = ti2 + ti3;
            }
         }

      }
   }

   void radf3(long ido, long l1, FloatLargeArray in, long in_off, FloatLargeArray out, long out_off, long offset) {
      float taur = -0.5F;
      float taui = 0.8660254F;
      long iw1 = offset;
      long iw2 = offset + ido;
      long idx0 = l1 * ido;

      for(long k = 0L; k < l1; ++k) {
         long idx1 = k * ido;
         long idx3 = 2L * idx0;
         long idx4 = (3L * k + 1L) * ido;
         long iidx1 = in_off + idx1;
         long iidx2 = iidx1 + idx0;
         long iidx3 = iidx1 + idx3;
         float i1r = in.getFloat(iidx1);
         float i2r = in.getFloat(iidx2);
         float i3r = in.getFloat(iidx3);
         float cr2 = i2r + i3r;
         out.setFloat(out_off + 3L * idx1, i1r + cr2);
         out.setFloat(out_off + idx4 + ido, 0.8660254F * (i3r - i2r));
         out.setFloat(out_off + ido - 1L + idx4, i1r + -0.5F * cr2);
      }

      if (ido != 1L) {
         for(long k = 0L; k < l1; ++k) {
            long idx3 = k * ido;
            long idx4 = 3L * idx3;
            long idx5 = idx3 + idx0;
            long idx6 = idx5 + idx0;
            long idx7 = idx4 + ido;
            long idx8 = idx7 + ido;

            for(long i = 2L; i < ido; i += 2L) {
               long ic = ido - i;
               long widx1 = i - 1L + iw1;
               long widx2 = i - 1L + iw2;
               float w1r = this.wtable_rl.getFloat(widx1 - 1L);
               float w1i = this.wtable_rl.getFloat(widx1);
               float w2r = this.wtable_rl.getFloat(widx2 - 1L);
               float w2i = this.wtable_rl.getFloat(widx2);
               long idx9 = in_off + i;
               long idx10 = out_off + i;
               long idx11 = out_off + ic;
               long iidx1 = idx9 + idx3;
               long iidx2 = idx9 + idx5;
               long iidx3 = idx9 + idx6;
               float i1i = in.getFloat(iidx1 - 1L);
               float i1r = in.getFloat(iidx1);
               float i2i = in.getFloat(iidx2 - 1L);
               float i2r = in.getFloat(iidx2);
               float i3i = in.getFloat(iidx3 - 1L);
               float i3r = in.getFloat(iidx3);
               float dr2 = w1r * i2i + w1i * i2r;
               float di2 = w1r * i2r - w1i * i2i;
               float dr3 = w2r * i3i + w2i * i3r;
               float di3 = w2r * i3r - w2i * i3i;
               float cr2 = dr2 + dr3;
               float ci2 = di2 + di3;
               float tr2 = i1i + -0.5F * cr2;
               float ti2 = i1r + -0.5F * ci2;
               float tr3 = 0.8660254F * (di2 - di3);
               float ti3 = 0.8660254F * (dr3 - dr2);
               long oidx1 = idx10 + idx4;
               long oidx2 = idx11 + idx7;
               long oidx3 = idx10 + idx8;
               out.setFloat(oidx1 - 1L, i1i + cr2);
               out.setFloat(oidx1, i1r + ci2);
               out.setFloat(oidx2 - 1L, tr2 - tr3);
               out.setFloat(oidx2, ti3 - ti2);
               out.setFloat(oidx3 - 1L, tr2 + tr3);
               out.setFloat(oidx3, ti2 + ti3);
            }
         }

      }
   }

   void radb3(int ido, int l1, float[] in, int in_off, float[] out, int out_off, int offset) {
      float taur = -0.5F;
      float taui = 0.8660254F;
      int iw1 = offset;
      int iw2 = offset + ido;

      for(int k = 0; k < l1; ++k) {
         int idx1 = k * ido;
         int iidx1 = in_off + 3 * idx1;
         int iidx2 = iidx1 + 2 * ido;
         float i1i = in[iidx1];
         float tr2 = 2.0F * in[iidx2 - 1];
         float cr2 = i1i + -0.5F * tr2;
         float ci3 = 1.7320508F * in[iidx2];
         out[out_off + idx1] = i1i + tr2;
         out[out_off + (k + l1) * ido] = cr2 - ci3;
         out[out_off + (k + 2 * l1) * ido] = cr2 + ci3;
      }

      if (ido != 1) {
         int idx0 = l1 * ido;

         for(int k = 0; k < l1; ++k) {
            int idx1 = k * ido;
            int idx2 = 3 * idx1;
            int idx3 = idx2 + ido;
            int idx4 = idx3 + ido;
            int idx5 = idx1 + idx0;
            int idx6 = idx5 + idx0;

            for(int i = 2; i < ido; i += 2) {
               int ic = ido - i;
               int idx7 = in_off + i;
               int idx8 = in_off + ic;
               int idx9 = out_off + i;
               int iidx1 = idx7 + idx2;
               int iidx2 = idx7 + idx4;
               int iidx3 = idx8 + idx3;
               float i1i = in[iidx1 - 1];
               float i1r = in[iidx1];
               float i2i = in[iidx2 - 1];
               float i2r = in[iidx2];
               float i3i = in[iidx3 - 1];
               float i3r = in[iidx3];
               float tr2 = i2i + i3i;
               float cr2 = i1i + -0.5F * tr2;
               float ti2 = i2r - i3r;
               float ci2 = i1r + -0.5F * ti2;
               float cr3 = 0.8660254F * (i2i - i3i);
               float ci3 = 0.8660254F * (i2r + i3r);
               float dr2 = cr2 - ci3;
               float dr3 = cr2 + ci3;
               float di2 = ci2 + cr3;
               float di3 = ci2 - cr3;
               int widx1 = i - 1 + iw1;
               int widx2 = i - 1 + iw2;
               float w1r = this.wtable_r[widx1 - 1];
               float w1i = this.wtable_r[widx1];
               float w2r = this.wtable_r[widx2 - 1];
               float w2i = this.wtable_r[widx2];
               int oidx1 = idx9 + idx1;
               int oidx2 = idx9 + idx5;
               int oidx3 = idx9 + idx6;
               out[oidx1 - 1] = i1i + tr2;
               out[oidx1] = i1r + ti2;
               out[oidx2 - 1] = w1r * dr2 - w1i * di2;
               out[oidx2] = w1r * di2 + w1i * dr2;
               out[oidx3 - 1] = w2r * dr3 - w2i * di3;
               out[oidx3] = w2r * di3 + w2i * dr3;
            }
         }

      }
   }

   void radb3(long ido, long l1, FloatLargeArray in, long in_off, FloatLargeArray out, long out_off, long offset) {
      float taur = -0.5F;
      float taui = 0.8660254F;
      long iw1 = offset;
      long iw2 = offset + ido;

      for(long k = 0L; k < l1; ++k) {
         long idx1 = k * ido;
         long iidx1 = in_off + 3L * idx1;
         long iidx2 = iidx1 + 2L * ido;
         float i1i = in.getFloat(iidx1);
         float tr2 = 2.0F * in.getFloat(iidx2 - 1L);
         float cr2 = i1i + -0.5F * tr2;
         float ci3 = 1.7320508F * in.getFloat(iidx2);
         out.setFloat(out_off + idx1, i1i + tr2);
         out.setFloat(out_off + (k + l1) * ido, cr2 - ci3);
         out.setFloat(out_off + (k + 2L * l1) * ido, cr2 + ci3);
      }

      if (ido != 1L) {
         long idx0 = l1 * ido;

         for(long k = 0L; k < l1; ++k) {
            long idx1 = k * ido;
            long idx2 = 3L * idx1;
            long idx3 = idx2 + ido;
            long idx4 = idx3 + ido;
            long idx5 = idx1 + idx0;
            long idx6 = idx5 + idx0;

            for(long i = 2L; i < ido; i += 2L) {
               long ic = ido - i;
               long idx7 = in_off + i;
               long idx8 = in_off + ic;
               long idx9 = out_off + i;
               long iidx1 = idx7 + idx2;
               long iidx2 = idx7 + idx4;
               long iidx3 = idx8 + idx3;
               float i1i = in.getFloat(iidx1 - 1L);
               float i1r = in.getFloat(iidx1);
               float i2i = in.getFloat(iidx2 - 1L);
               float i2r = in.getFloat(iidx2);
               float i3i = in.getFloat(iidx3 - 1L);
               float i3r = in.getFloat(iidx3);
               float tr2 = i2i + i3i;
               float cr2 = i1i + -0.5F * tr2;
               float ti2 = i2r - i3r;
               float ci2 = i1r + -0.5F * ti2;
               float cr3 = 0.8660254F * (i2i - i3i);
               float ci3 = 0.8660254F * (i2r + i3r);
               float dr2 = cr2 - ci3;
               float dr3 = cr2 + ci3;
               float di2 = ci2 + cr3;
               float di3 = ci2 - cr3;
               long widx1 = i - 1L + iw1;
               long widx2 = i - 1L + iw2;
               float w1r = this.wtable_rl.getFloat(widx1 - 1L);
               float w1i = this.wtable_rl.getFloat(widx1);
               float w2r = this.wtable_rl.getFloat(widx2 - 1L);
               float w2i = this.wtable_rl.getFloat(widx2);
               long oidx1 = idx9 + idx1;
               long oidx2 = idx9 + idx5;
               long oidx3 = idx9 + idx6;
               out.setFloat(oidx1 - 1L, i1i + tr2);
               out.setFloat(oidx1, i1r + ti2);
               out.setFloat(oidx2 - 1L, w1r * dr2 - w1i * di2);
               out.setFloat(oidx2, w1r * di2 + w1i * dr2);
               out.setFloat(oidx3 - 1L, w2r * dr3 - w2i * di3);
               out.setFloat(oidx3, w2r * di3 + w2i * dr3);
            }
         }

      }
   }

   void radf4(int ido, int l1, float[] in, int in_off, float[] out, int out_off, int offset) {
      float hsqt2 = 0.70710677F;
      int iw1 = offset;
      int iw2 = offset + ido;
      int iw3 = iw2 + ido;
      int idx0 = l1 * ido;

      for(int k = 0; k < l1; ++k) {
         int idx1 = k * ido;
         int idx2 = 4 * idx1;
         int idx3 = idx1 + idx0;
         int idx4 = idx3 + idx0;
         int idx5 = idx4 + idx0;
         int idx6 = idx2 + ido;
         float i1r = in[in_off + idx1];
         float i2r = in[in_off + idx3];
         float i3r = in[in_off + idx4];
         float i4r = in[in_off + idx5];
         float tr1 = i2r + i4r;
         float tr2 = i1r + i3r;
         int oidx1 = out_off + idx2;
         int oidx2 = out_off + idx6 + ido;
         out[oidx1] = tr1 + tr2;
         out[oidx2 - 1 + ido + ido] = tr2 - tr1;
         out[oidx2 - 1] = i1r - i3r;
         out[oidx2] = i4r - i2r;
      }

      if (ido >= 2) {
         if (ido != 2) {
            for(int k = 0; k < l1; ++k) {
               int idx1 = k * ido;
               int idx2 = idx1 + idx0;
               int idx3 = idx2 + idx0;
               int idx4 = idx3 + idx0;
               int idx5 = 4 * idx1;
               int idx6 = idx5 + ido;
               int idx7 = idx6 + ido;
               int idx8 = idx7 + ido;

               for(int i = 2; i < ido; i += 2) {
                  int ic = ido - i;
                  int widx1 = i - 1 + iw1;
                  int widx2 = i - 1 + iw2;
                  int widx3 = i - 1 + iw3;
                  float w1r = this.wtable_r[widx1 - 1];
                  float w1i = this.wtable_r[widx1];
                  float w2r = this.wtable_r[widx2 - 1];
                  float w2i = this.wtable_r[widx2];
                  float w3r = this.wtable_r[widx3 - 1];
                  float w3i = this.wtable_r[widx3];
                  int idx9 = in_off + i;
                  int idx10 = out_off + i;
                  int idx11 = out_off + ic;
                  int iidx1 = idx9 + idx1;
                  int iidx2 = idx9 + idx2;
                  int iidx3 = idx9 + idx3;
                  int iidx4 = idx9 + idx4;
                  float i1i = in[iidx1 - 1];
                  float i1r = in[iidx1];
                  float i2i = in[iidx2 - 1];
                  float i2r = in[iidx2];
                  float i3i = in[iidx3 - 1];
                  float i3r = in[iidx3];
                  float i4i = in[iidx4 - 1];
                  float i4r = in[iidx4];
                  float cr2 = w1r * i2i + w1i * i2r;
                  float ci2 = w1r * i2r - w1i * i2i;
                  float cr3 = w2r * i3i + w2i * i3r;
                  float ci3 = w2r * i3r - w2i * i3i;
                  float cr4 = w3r * i4i + w3i * i4r;
                  float ci4 = w3r * i4r - w3i * i4i;
                  float tr1 = cr2 + cr4;
                  float tr4 = cr4 - cr2;
                  float ti1 = ci2 + ci4;
                  float ti4 = ci2 - ci4;
                  float ti2 = i1r + ci3;
                  float ti3 = i1r - ci3;
                  float tr2 = i1i + cr3;
                  float tr3 = i1i - cr3;
                  int oidx1 = idx10 + idx5;
                  int oidx2 = idx11 + idx6;
                  int oidx3 = idx10 + idx7;
                  int oidx4 = idx11 + idx8;
                  out[oidx1 - 1] = tr1 + tr2;
                  out[oidx4 - 1] = tr2 - tr1;
                  out[oidx1] = ti1 + ti2;
                  out[oidx4] = ti1 - ti2;
                  out[oidx3 - 1] = ti4 + tr3;
                  out[oidx2 - 1] = tr3 - ti4;
                  out[oidx3] = tr4 + ti3;
                  out[oidx2] = tr4 - ti3;
               }
            }

            if (ido % 2 == 1) {
               return;
            }
         }

         for(int k = 0; k < l1; ++k) {
            int idx1 = k * ido;
            int idx2 = 4 * idx1;
            int idx3 = idx1 + idx0;
            int idx4 = idx3 + idx0;
            int idx5 = idx4 + idx0;
            int idx6 = idx2 + ido;
            int idx7 = idx6 + ido;
            int idx8 = idx7 + ido;
            int idx9 = in_off + ido;
            int idx10 = out_off + ido;
            float i1i = in[idx9 - 1 + idx1];
            float i2i = in[idx9 - 1 + idx3];
            float i3i = in[idx9 - 1 + idx4];
            float i4i = in[idx9 - 1 + idx5];
            float ti1 = -0.70710677F * (i2i + i4i);
            float tr1 = 0.70710677F * (i2i - i4i);
            out[idx10 - 1 + idx2] = tr1 + i1i;
            out[idx10 - 1 + idx7] = i1i - tr1;
            out[out_off + idx6] = ti1 - i3i;
            out[out_off + idx8] = ti1 + i3i;
         }

      }
   }

   void radf4(long ido, long l1, FloatLargeArray in, long in_off, FloatLargeArray out, long out_off, long offset) {
      float hsqt2 = 0.70710677F;
      long iw1 = offset;
      long iw2 = offset + ido;
      long iw3 = iw2 + ido;
      long idx0 = l1 * ido;

      for(long k = 0L; k < l1; ++k) {
         long idx1 = k * ido;
         long idx2 = 4L * idx1;
         long idx3 = idx1 + idx0;
         long idx4 = idx3 + idx0;
         long idx5 = idx4 + idx0;
         long idx6 = idx2 + ido;
         float i1r = in.getFloat(in_off + idx1);
         float i2r = in.getFloat(in_off + idx3);
         float i3r = in.getFloat(in_off + idx4);
         float i4r = in.getFloat(in_off + idx5);
         float tr1 = i2r + i4r;
         float tr2 = i1r + i3r;
         long oidx1 = out_off + idx2;
         long oidx2 = out_off + idx6 + ido;
         out.setFloat(oidx1, tr1 + tr2);
         out.setFloat(oidx2 - 1L + ido + ido, tr2 - tr1);
         out.setFloat(oidx2 - 1L, i1r - i3r);
         out.setFloat(oidx2, i4r - i2r);
      }

      if (ido >= 2L) {
         if (ido != 2L) {
            for(long k = 0L; k < l1; ++k) {
               long idx1 = k * ido;
               long idx2 = idx1 + idx0;
               long idx3 = idx2 + idx0;
               long idx4 = idx3 + idx0;
               long idx5 = 4L * idx1;
               long idx6 = idx5 + ido;
               long idx7 = idx6 + ido;
               long idx8 = idx7 + ido;

               for(long i = 2L; i < ido; i += 2L) {
                  long ic = ido - i;
                  long widx1 = i - 1L + iw1;
                  long widx2 = i - 1L + iw2;
                  long widx3 = i - 1L + iw3;
                  float w1r = this.wtable_rl.getFloat(widx1 - 1L);
                  float w1i = this.wtable_rl.getFloat(widx1);
                  float w2r = this.wtable_rl.getFloat(widx2 - 1L);
                  float w2i = this.wtable_rl.getFloat(widx2);
                  float w3r = this.wtable_rl.getFloat(widx3 - 1L);
                  float w3i = this.wtable_rl.getFloat(widx3);
                  long idx9 = in_off + i;
                  long idx10 = out_off + i;
                  long idx11 = out_off + ic;
                  long iidx1 = idx9 + idx1;
                  long iidx2 = idx9 + idx2;
                  long iidx3 = idx9 + idx3;
                  long iidx4 = idx9 + idx4;
                  float i1i = in.getFloat(iidx1 - 1L);
                  float i1r = in.getFloat(iidx1);
                  float i2i = in.getFloat(iidx2 - 1L);
                  float i2r = in.getFloat(iidx2);
                  float i3i = in.getFloat(iidx3 - 1L);
                  float i3r = in.getFloat(iidx3);
                  float i4i = in.getFloat(iidx4 - 1L);
                  float i4r = in.getFloat(iidx4);
                  float cr2 = w1r * i2i + w1i * i2r;
                  float ci2 = w1r * i2r - w1i * i2i;
                  float cr3 = w2r * i3i + w2i * i3r;
                  float ci3 = w2r * i3r - w2i * i3i;
                  float cr4 = w3r * i4i + w3i * i4r;
                  float ci4 = w3r * i4r - w3i * i4i;
                  float tr1 = cr2 + cr4;
                  float tr4 = cr4 - cr2;
                  float ti1 = ci2 + ci4;
                  float ti4 = ci2 - ci4;
                  float ti2 = i1r + ci3;
                  float ti3 = i1r - ci3;
                  float tr2 = i1i + cr3;
                  float tr3 = i1i - cr3;
                  long oidx1 = idx10 + idx5;
                  long oidx2 = idx11 + idx6;
                  long oidx3 = idx10 + idx7;
                  long oidx4 = idx11 + idx8;
                  out.setFloat(oidx1 - 1L, tr1 + tr2);
                  out.setFloat(oidx4 - 1L, tr2 - tr1);
                  out.setFloat(oidx1, ti1 + ti2);
                  out.setFloat(oidx4, ti1 - ti2);
                  out.setFloat(oidx3 - 1L, ti4 + tr3);
                  out.setFloat(oidx2 - 1L, tr3 - ti4);
                  out.setFloat(oidx3, tr4 + ti3);
                  out.setFloat(oidx2, tr4 - ti3);
               }
            }

            if (ido % 2L == 1L) {
               return;
            }
         }

         for(long k = 0L; k < l1; ++k) {
            long idx1 = k * ido;
            long idx2 = 4L * idx1;
            long idx3 = idx1 + idx0;
            long idx4 = idx3 + idx0;
            long idx5 = idx4 + idx0;
            long idx6 = idx2 + ido;
            long idx7 = idx6 + ido;
            long idx8 = idx7 + ido;
            long idx9 = in_off + ido;
            long idx10 = out_off + ido;
            float i1i = in.getFloat(idx9 - 1L + idx1);
            float i2i = in.getFloat(idx9 - 1L + idx3);
            float i3i = in.getFloat(idx9 - 1L + idx4);
            float i4i = in.getFloat(idx9 - 1L + idx5);
            float ti1 = -0.70710677F * (i2i + i4i);
            float tr1 = 0.70710677F * (i2i - i4i);
            out.setFloat(idx10 - 1L + idx2, tr1 + i1i);
            out.setFloat(idx10 - 1L + idx7, i1i - tr1);
            out.setFloat(out_off + idx6, ti1 - i3i);
            out.setFloat(out_off + idx8, ti1 + i3i);
         }

      }
   }

   void radb4(int ido, int l1, float[] in, int in_off, float[] out, int out_off, int offset) {
      float sqrt2 = 1.4142135F;
      int iw1 = offset;
      int iw2 = offset + ido;
      int iw3 = iw2 + ido;
      int idx0 = l1 * ido;

      for(int k = 0; k < l1; ++k) {
         int idx1 = k * ido;
         int idx2 = 4 * idx1;
         int idx3 = idx1 + idx0;
         int idx4 = idx3 + idx0;
         int idx5 = idx4 + idx0;
         int idx6 = idx2 + ido;
         int idx7 = idx6 + ido;
         int idx8 = idx7 + ido;
         float i1r = in[in_off + idx2];
         float i2r = in[in_off + idx7];
         float i3r = in[in_off + ido - 1 + idx8];
         float i4r = in[in_off + ido - 1 + idx6];
         float tr1 = i1r - i3r;
         float tr2 = i1r + i3r;
         float tr3 = i4r + i4r;
         float tr4 = i2r + i2r;
         out[out_off + idx1] = tr2 + tr3;
         out[out_off + idx3] = tr1 - tr4;
         out[out_off + idx4] = tr2 - tr3;
         out[out_off + idx5] = tr1 + tr4;
      }

      if (ido >= 2) {
         if (ido != 2) {
            for(int k = 0; k < l1; ++k) {
               int idx1 = k * ido;
               int idx2 = idx1 + idx0;
               int idx3 = idx2 + idx0;
               int idx4 = idx3 + idx0;
               int idx5 = 4 * idx1;
               int idx6 = idx5 + ido;
               int idx7 = idx6 + ido;
               int idx8 = idx7 + ido;

               for(int i = 2; i < ido; i += 2) {
                  int ic = ido - i;
                  int widx1 = i - 1 + iw1;
                  int widx2 = i - 1 + iw2;
                  int widx3 = i - 1 + iw3;
                  float w1r = this.wtable_r[widx1 - 1];
                  float w1i = this.wtable_r[widx1];
                  float w2r = this.wtable_r[widx2 - 1];
                  float w2i = this.wtable_r[widx2];
                  float w3r = this.wtable_r[widx3 - 1];
                  float w3i = this.wtable_r[widx3];
                  int idx12 = in_off + i;
                  int idx13 = in_off + ic;
                  int idx14 = out_off + i;
                  int iidx1 = idx12 + idx5;
                  int iidx2 = idx13 + idx6;
                  int iidx3 = idx12 + idx7;
                  int iidx4 = idx13 + idx8;
                  float i1i = in[iidx1 - 1];
                  float i1r = in[iidx1];
                  float i2i = in[iidx2 - 1];
                  float i2r = in[iidx2];
                  float i3i = in[iidx3 - 1];
                  float i3r = in[iidx3];
                  float i4i = in[iidx4 - 1];
                  float i4r = in[iidx4];
                  float ti1 = i1r + i4r;
                  float ti2 = i1r - i4r;
                  float ti3 = i3r - i2r;
                  float tr4 = i3r + i2r;
                  float tr1 = i1i - i4i;
                  float tr2 = i1i + i4i;
                  float ti4 = i3i - i2i;
                  float tr3 = i3i + i2i;
                  float cr3 = tr2 - tr3;
                  float ci3 = ti2 - ti3;
                  float cr2 = tr1 - tr4;
                  float cr4 = tr1 + tr4;
                  float ci2 = ti1 + ti4;
                  float ci4 = ti1 - ti4;
                  int oidx1 = idx14 + idx1;
                  int oidx2 = idx14 + idx2;
                  int oidx3 = idx14 + idx3;
                  int oidx4 = idx14 + idx4;
                  out[oidx1 - 1] = tr2 + tr3;
                  out[oidx1] = ti2 + ti3;
                  out[oidx2 - 1] = w1r * cr2 - w1i * ci2;
                  out[oidx2] = w1r * ci2 + w1i * cr2;
                  out[oidx3 - 1] = w2r * cr3 - w2i * ci3;
                  out[oidx3] = w2r * ci3 + w2i * cr3;
                  out[oidx4 - 1] = w3r * cr4 - w3i * ci4;
                  out[oidx4] = w3r * ci4 + w3i * cr4;
               }
            }

            if (ido % 2 == 1) {
               return;
            }
         }

         for(int k = 0; k < l1; ++k) {
            int idx1 = k * ido;
            int idx2 = 4 * idx1;
            int idx3 = idx1 + idx0;
            int idx4 = idx3 + idx0;
            int idx5 = idx4 + idx0;
            int idx6 = idx2 + ido;
            int idx7 = idx6 + ido;
            int idx8 = idx7 + ido;
            int idx9 = in_off + ido;
            int idx10 = out_off + ido;
            float i1r = in[idx9 - 1 + idx2];
            float i2r = in[idx9 - 1 + idx7];
            float i3r = in[in_off + idx6];
            float i4r = in[in_off + idx8];
            float ti1 = i3r + i4r;
            float ti2 = i4r - i3r;
            float tr1 = i1r - i2r;
            float tr2 = i1r + i2r;
            out[idx10 - 1 + idx1] = tr2 + tr2;
            out[idx10 - 1 + idx3] = 1.4142135F * (tr1 - ti1);
            out[idx10 - 1 + idx4] = ti2 + ti2;
            out[idx10 - 1 + idx5] = -1.4142135F * (tr1 + ti1);
         }

      }
   }

   void radb4(long ido, long l1, FloatLargeArray in, long in_off, FloatLargeArray out, long out_off, long offset) {
      float sqrt2 = 1.4142135F;
      long iw1 = offset;
      long iw2 = offset + ido;
      long iw3 = iw2 + ido;
      long idx0 = l1 * ido;

      for(long k = 0L; k < l1; ++k) {
         long idx1 = k * ido;
         long idx2 = 4L * idx1;
         long idx3 = idx1 + idx0;
         long idx4 = idx3 + idx0;
         long idx5 = idx4 + idx0;
         long idx6 = idx2 + ido;
         long idx7 = idx6 + ido;
         long idx8 = idx7 + ido;
         float i1r = in.getFloat(in_off + idx2);
         float i2r = in.getFloat(in_off + idx7);
         float i3r = in.getFloat(in_off + ido - 1L + idx8);
         float i4r = in.getFloat(in_off + ido - 1L + idx6);
         float tr1 = i1r - i3r;
         float tr2 = i1r + i3r;
         float tr3 = i4r + i4r;
         float tr4 = i2r + i2r;
         out.setFloat(out_off + idx1, tr2 + tr3);
         out.setFloat(out_off + idx3, tr1 - tr4);
         out.setFloat(out_off + idx4, tr2 - tr3);
         out.setFloat(out_off + idx5, tr1 + tr4);
      }

      if (ido >= 2L) {
         if (ido != 2L) {
            for(long k = 0L; k < l1; ++k) {
               long idx1 = k * ido;
               long idx2 = idx1 + idx0;
               long idx3 = idx2 + idx0;
               long idx4 = idx3 + idx0;
               long idx5 = 4L * idx1;
               long idx6 = idx5 + ido;
               long idx7 = idx6 + ido;
               long idx8 = idx7 + ido;

               for(long i = 2L; i < ido; i += 2L) {
                  long ic = ido - i;
                  long widx1 = i - 1L + iw1;
                  long widx2 = i - 1L + iw2;
                  long widx3 = i - 1L + iw3;
                  float w1r = this.wtable_rl.getFloat(widx1 - 1L);
                  float w1i = this.wtable_rl.getFloat(widx1);
                  float w2r = this.wtable_rl.getFloat(widx2 - 1L);
                  float w2i = this.wtable_rl.getFloat(widx2);
                  float w3r = this.wtable_rl.getFloat(widx3 - 1L);
                  float w3i = this.wtable_rl.getFloat(widx3);
                  long idx12 = in_off + i;
                  long idx13 = in_off + ic;
                  long idx14 = out_off + i;
                  long iidx1 = idx12 + idx5;
                  long iidx2 = idx13 + idx6;
                  long iidx3 = idx12 + idx7;
                  long iidx4 = idx13 + idx8;
                  float i1i = in.getFloat(iidx1 - 1L);
                  float i1r = in.getFloat(iidx1);
                  float i2i = in.getFloat(iidx2 - 1L);
                  float i2r = in.getFloat(iidx2);
                  float i3i = in.getFloat(iidx3 - 1L);
                  float i3r = in.getFloat(iidx3);
                  float i4i = in.getFloat(iidx4 - 1L);
                  float i4r = in.getFloat(iidx4);
                  float ti1 = i1r + i4r;
                  float ti2 = i1r - i4r;
                  float ti3 = i3r - i2r;
                  float tr4 = i3r + i2r;
                  float tr1 = i1i - i4i;
                  float tr2 = i1i + i4i;
                  float ti4 = i3i - i2i;
                  float tr3 = i3i + i2i;
                  float cr3 = tr2 - tr3;
                  float ci3 = ti2 - ti3;
                  float cr2 = tr1 - tr4;
                  float cr4 = tr1 + tr4;
                  float ci2 = ti1 + ti4;
                  float ci4 = ti1 - ti4;
                  long oidx1 = idx14 + idx1;
                  long oidx2 = idx14 + idx2;
                  long oidx3 = idx14 + idx3;
                  long oidx4 = idx14 + idx4;
                  out.setFloat(oidx1 - 1L, tr2 + tr3);
                  out.setFloat(oidx1, ti2 + ti3);
                  out.setFloat(oidx2 - 1L, w1r * cr2 - w1i * ci2);
                  out.setFloat(oidx2, w1r * ci2 + w1i * cr2);
                  out.setFloat(oidx3 - 1L, w2r * cr3 - w2i * ci3);
                  out.setFloat(oidx3, w2r * ci3 + w2i * cr3);
                  out.setFloat(oidx4 - 1L, w3r * cr4 - w3i * ci4);
                  out.setFloat(oidx4, w3r * ci4 + w3i * cr4);
               }
            }

            if (ido % 2L == 1L) {
               return;
            }
         }

         for(long k = 0L; k < l1; ++k) {
            long idx1 = k * ido;
            long idx2 = 4L * idx1;
            long idx3 = idx1 + idx0;
            long idx4 = idx3 + idx0;
            long idx5 = idx4 + idx0;
            long idx6 = idx2 + ido;
            long idx7 = idx6 + ido;
            long idx8 = idx7 + ido;
            long idx9 = in_off + ido;
            long idx10 = out_off + ido;
            float i1r = in.getFloat(idx9 - 1L + idx2);
            float i2r = in.getFloat(idx9 - 1L + idx7);
            float i3r = in.getFloat(in_off + idx6);
            float i4r = in.getFloat(in_off + idx8);
            float ti1 = i3r + i4r;
            float ti2 = i4r - i3r;
            float tr1 = i1r - i2r;
            float tr2 = i1r + i2r;
            out.setFloat(idx10 - 1L + idx1, tr2 + tr2);
            out.setFloat(idx10 - 1L + idx3, 1.4142135F * (tr1 - ti1));
            out.setFloat(idx10 - 1L + idx4, ti2 + ti2);
            out.setFloat(idx10 - 1L + idx5, -1.4142135F * (tr1 + ti1));
         }

      }
   }

   void radf5(int ido, int l1, float[] in, int in_off, float[] out, int out_off, int offset) {
      float tr11 = 0.309017F;
      float ti11 = 0.95105654F;
      float tr12 = -0.809017F;
      float ti12 = 0.58778524F;
      int iw1 = offset;
      int iw2 = offset + ido;
      int iw3 = iw2 + ido;
      int iw4 = iw3 + ido;
      int idx0 = l1 * ido;

      for(int k = 0; k < l1; ++k) {
         int idx1 = k * ido;
         int idx2 = 5 * idx1;
         int idx3 = idx2 + ido;
         int idx4 = idx3 + ido;
         int idx5 = idx4 + ido;
         int idx6 = idx5 + ido;
         int idx7 = idx1 + idx0;
         int idx8 = idx7 + idx0;
         int idx9 = idx8 + idx0;
         int idx10 = idx9 + idx0;
         int idx11 = out_off + ido - 1;
         float i1r = in[in_off + idx1];
         float i2r = in[in_off + idx7];
         float i3r = in[in_off + idx8];
         float i4r = in[in_off + idx9];
         float i5r = in[in_off + idx10];
         float cr2 = i5r + i2r;
         float ci5 = i5r - i2r;
         float cr3 = i4r + i3r;
         float ci4 = i4r - i3r;
         out[out_off + idx2] = i1r + cr2 + cr3;
         out[idx11 + idx3] = i1r + 0.309017F * cr2 + -0.809017F * cr3;
         out[out_off + idx4] = 0.95105654F * ci5 + 0.58778524F * ci4;
         out[idx11 + idx5] = i1r + -0.809017F * cr2 + 0.309017F * cr3;
         out[out_off + idx6] = 0.58778524F * ci5 - 0.95105654F * ci4;
      }

      if (ido != 1) {
         for(int k = 0; k < l1; ++k) {
            int idx1 = k * ido;
            int idx2 = 5 * idx1;
            int idx3 = idx2 + ido;
            int idx4 = idx3 + ido;
            int idx5 = idx4 + ido;
            int idx6 = idx5 + ido;
            int idx7 = idx1 + idx0;
            int idx8 = idx7 + idx0;
            int idx9 = idx8 + idx0;
            int idx10 = idx9 + idx0;

            for(int i = 2; i < ido; i += 2) {
               int widx1 = i - 1 + iw1;
               int widx2 = i - 1 + iw2;
               int widx3 = i - 1 + iw3;
               int widx4 = i - 1 + iw4;
               float w1r = this.wtable_r[widx1 - 1];
               float w1i = this.wtable_r[widx1];
               float w2r = this.wtable_r[widx2 - 1];
               float w2i = this.wtable_r[widx2];
               float w3r = this.wtable_r[widx3 - 1];
               float w3i = this.wtable_r[widx3];
               float w4r = this.wtable_r[widx4 - 1];
               float w4i = this.wtable_r[widx4];
               int ic = ido - i;
               int idx15 = in_off + i;
               int idx16 = out_off + i;
               int idx17 = out_off + ic;
               int iidx1 = idx15 + idx1;
               int iidx2 = idx15 + idx7;
               int iidx3 = idx15 + idx8;
               int iidx4 = idx15 + idx9;
               int iidx5 = idx15 + idx10;
               float i1i = in[iidx1 - 1];
               float i1r = in[iidx1];
               float i2i = in[iidx2 - 1];
               float i2r = in[iidx2];
               float i3i = in[iidx3 - 1];
               float i3r = in[iidx3];
               float i4i = in[iidx4 - 1];
               float i4r = in[iidx4];
               float i5i = in[iidx5 - 1];
               float i5r = in[iidx5];
               float dr2 = w1r * i2i + w1i * i2r;
               float di2 = w1r * i2r - w1i * i2i;
               float dr3 = w2r * i3i + w2i * i3r;
               float di3 = w2r * i3r - w2i * i3i;
               float dr4 = w3r * i4i + w3i * i4r;
               float di4 = w3r * i4r - w3i * i4i;
               float dr5 = w4r * i5i + w4i * i5r;
               float di5 = w4r * i5r - w4i * i5i;
               float cr2 = dr2 + dr5;
               float ci5 = dr5 - dr2;
               float cr5 = di2 - di5;
               float ci2 = di2 + di5;
               float cr3 = dr3 + dr4;
               float ci4 = dr4 - dr3;
               float cr4 = di3 - di4;
               float ci3 = di3 + di4;
               float tr2 = i1i + 0.309017F * cr2 + -0.809017F * cr3;
               float ti2 = i1r + 0.309017F * ci2 + -0.809017F * ci3;
               float tr3 = i1i + -0.809017F * cr2 + 0.309017F * cr3;
               float ti3 = i1r + -0.809017F * ci2 + 0.309017F * ci3;
               float tr5 = 0.95105654F * cr5 + 0.58778524F * cr4;
               float ti5 = 0.95105654F * ci5 + 0.58778524F * ci4;
               float tr4 = 0.58778524F * cr5 - 0.95105654F * cr4;
               float ti4 = 0.58778524F * ci5 - 0.95105654F * ci4;
               int oidx1 = idx16 + idx2;
               int oidx2 = idx17 + idx3;
               int oidx3 = idx16 + idx4;
               int oidx4 = idx17 + idx5;
               int oidx5 = idx16 + idx6;
               out[oidx1 - 1] = i1i + cr2 + cr3;
               out[oidx1] = i1r + ci2 + ci3;
               out[oidx3 - 1] = tr2 + tr5;
               out[oidx2 - 1] = tr2 - tr5;
               out[oidx3] = ti2 + ti5;
               out[oidx2] = ti5 - ti2;
               out[oidx5 - 1] = tr3 + tr4;
               out[oidx4 - 1] = tr3 - tr4;
               out[oidx5] = ti3 + ti4;
               out[oidx4] = ti4 - ti3;
            }
         }

      }
   }

   void radf5(long ido, long l1, FloatLargeArray in, long in_off, FloatLargeArray out, long out_off, long offset) {
      float tr11 = 0.309017F;
      float ti11 = 0.95105654F;
      float tr12 = -0.809017F;
      float ti12 = 0.58778524F;
      long iw1 = offset;
      long iw2 = offset + ido;
      long iw3 = iw2 + ido;
      long iw4 = iw3 + ido;
      long idx0 = l1 * ido;

      for(long k = 0L; k < l1; ++k) {
         long idx1 = k * ido;
         long idx2 = 5L * idx1;
         long idx3 = idx2 + ido;
         long idx4 = idx3 + ido;
         long idx5 = idx4 + ido;
         long idx6 = idx5 + ido;
         long idx7 = idx1 + idx0;
         long idx8 = idx7 + idx0;
         long idx9 = idx8 + idx0;
         long idx10 = idx9 + idx0;
         long idx11 = out_off + ido - 1L;
         float i1r = in.getFloat(in_off + idx1);
         float i2r = in.getFloat(in_off + idx7);
         float i3r = in.getFloat(in_off + idx8);
         float i4r = in.getFloat(in_off + idx9);
         float i5r = in.getFloat(in_off + idx10);
         float cr2 = i5r + i2r;
         float ci5 = i5r - i2r;
         float cr3 = i4r + i3r;
         float ci4 = i4r - i3r;
         out.setFloat(out_off + idx2, i1r + cr2 + cr3);
         out.setFloat(idx11 + idx3, i1r + 0.309017F * cr2 + -0.809017F * cr3);
         out.setFloat(out_off + idx4, 0.95105654F * ci5 + 0.58778524F * ci4);
         out.setFloat(idx11 + idx5, i1r + -0.809017F * cr2 + 0.309017F * cr3);
         out.setFloat(out_off + idx6, 0.58778524F * ci5 - 0.95105654F * ci4);
      }

      if (ido != 1L) {
         for(long k = 0L; k < l1; ++k) {
            long idx1 = k * ido;
            long idx2 = 5L * idx1;
            long idx3 = idx2 + ido;
            long idx4 = idx3 + ido;
            long idx5 = idx4 + ido;
            long idx6 = idx5 + ido;
            long idx7 = idx1 + idx0;
            long idx8 = idx7 + idx0;
            long idx9 = idx8 + idx0;
            long idx10 = idx9 + idx0;

            for(long i = 2L; i < ido; i += 2L) {
               long widx1 = i - 1L + iw1;
               long widx2 = i - 1L + iw2;
               long widx3 = i - 1L + iw3;
               long widx4 = i - 1L + iw4;
               float w1r = this.wtable_rl.getFloat(widx1 - 1L);
               float w1i = this.wtable_rl.getFloat(widx1);
               float w2r = this.wtable_rl.getFloat(widx2 - 1L);
               float w2i = this.wtable_rl.getFloat(widx2);
               float w3r = this.wtable_rl.getFloat(widx3 - 1L);
               float w3i = this.wtable_rl.getFloat(widx3);
               float w4r = this.wtable_rl.getFloat(widx4 - 1L);
               float w4i = this.wtable_rl.getFloat(widx4);
               long ic = ido - i;
               long idx15 = in_off + i;
               long idx16 = out_off + i;
               long idx17 = out_off + ic;
               long iidx1 = idx15 + idx1;
               long iidx2 = idx15 + idx7;
               long iidx3 = idx15 + idx8;
               long iidx4 = idx15 + idx9;
               long iidx5 = idx15 + idx10;
               float i1i = in.getFloat(iidx1 - 1L);
               float i1r = in.getFloat(iidx1);
               float i2i = in.getFloat(iidx2 - 1L);
               float i2r = in.getFloat(iidx2);
               float i3i = in.getFloat(iidx3 - 1L);
               float i3r = in.getFloat(iidx3);
               float i4i = in.getFloat(iidx4 - 1L);
               float i4r = in.getFloat(iidx4);
               float i5i = in.getFloat(iidx5 - 1L);
               float i5r = in.getFloat(iidx5);
               float dr2 = w1r * i2i + w1i * i2r;
               float di2 = w1r * i2r - w1i * i2i;
               float dr3 = w2r * i3i + w2i * i3r;
               float di3 = w2r * i3r - w2i * i3i;
               float dr4 = w3r * i4i + w3i * i4r;
               float di4 = w3r * i4r - w3i * i4i;
               float dr5 = w4r * i5i + w4i * i5r;
               float di5 = w4r * i5r - w4i * i5i;
               float cr2 = dr2 + dr5;
               float ci5 = dr5 - dr2;
               float cr5 = di2 - di5;
               float ci2 = di2 + di5;
               float cr3 = dr3 + dr4;
               float ci4 = dr4 - dr3;
               float cr4 = di3 - di4;
               float ci3 = di3 + di4;
               float tr2 = i1i + 0.309017F * cr2 + -0.809017F * cr3;
               float ti2 = i1r + 0.309017F * ci2 + -0.809017F * ci3;
               float tr3 = i1i + -0.809017F * cr2 + 0.309017F * cr3;
               float ti3 = i1r + -0.809017F * ci2 + 0.309017F * ci3;
               float tr5 = 0.95105654F * cr5 + 0.58778524F * cr4;
               float ti5 = 0.95105654F * ci5 + 0.58778524F * ci4;
               float tr4 = 0.58778524F * cr5 - 0.95105654F * cr4;
               float ti4 = 0.58778524F * ci5 - 0.95105654F * ci4;
               long oidx1 = idx16 + idx2;
               long oidx2 = idx17 + idx3;
               long oidx3 = idx16 + idx4;
               long oidx4 = idx17 + idx5;
               long oidx5 = idx16 + idx6;
               out.setFloat(oidx1 - 1L, i1i + cr2 + cr3);
               out.setFloat(oidx1, i1r + ci2 + ci3);
               out.setFloat(oidx3 - 1L, tr2 + tr5);
               out.setFloat(oidx2 - 1L, tr2 - tr5);
               out.setFloat(oidx3, ti2 + ti5);
               out.setFloat(oidx2, ti5 - ti2);
               out.setFloat(oidx5 - 1L, tr3 + tr4);
               out.setFloat(oidx4 - 1L, tr3 - tr4);
               out.setFloat(oidx5, ti3 + ti4);
               out.setFloat(oidx4, ti4 - ti3);
            }
         }

      }
   }

   void radb5(int ido, int l1, float[] in, int in_off, float[] out, int out_off, int offset) {
      float tr11 = 0.309017F;
      float ti11 = 0.95105654F;
      float tr12 = -0.809017F;
      float ti12 = 0.58778524F;
      int iw1 = offset;
      int iw2 = offset + ido;
      int iw3 = iw2 + ido;
      int iw4 = iw3 + ido;
      int idx0 = l1 * ido;

      for(int k = 0; k < l1; ++k) {
         int idx1 = k * ido;
         int idx2 = 5 * idx1;
         int idx3 = idx2 + ido;
         int idx4 = idx3 + ido;
         int idx5 = idx4 + ido;
         int idx6 = idx5 + ido;
         int idx7 = idx1 + idx0;
         int idx8 = idx7 + idx0;
         int idx9 = idx8 + idx0;
         int idx10 = idx9 + idx0;
         int idx11 = in_off + ido - 1;
         float i1r = in[in_off + idx2];
         float ti5 = 2.0F * in[in_off + idx4];
         float ti4 = 2.0F * in[in_off + idx6];
         float tr2 = 2.0F * in[idx11 + idx3];
         float tr3 = 2.0F * in[idx11 + idx5];
         float cr2 = i1r + 0.309017F * tr2 + -0.809017F * tr3;
         float cr3 = i1r + -0.809017F * tr2 + 0.309017F * tr3;
         float ci5 = 0.95105654F * ti5 + 0.58778524F * ti4;
         float ci4 = 0.58778524F * ti5 - 0.95105654F * ti4;
         out[out_off + idx1] = i1r + tr2 + tr3;
         out[out_off + idx7] = cr2 - ci5;
         out[out_off + idx8] = cr3 - ci4;
         out[out_off + idx9] = cr3 + ci4;
         out[out_off + idx10] = cr2 + ci5;
      }

      if (ido != 1) {
         for(int k = 0; k < l1; ++k) {
            int idx1 = k * ido;
            int idx2 = 5 * idx1;
            int idx3 = idx2 + ido;
            int idx4 = idx3 + ido;
            int idx5 = idx4 + ido;
            int idx6 = idx5 + ido;
            int idx7 = idx1 + idx0;
            int idx8 = idx7 + idx0;
            int idx9 = idx8 + idx0;
            int idx10 = idx9 + idx0;

            for(int i = 2; i < ido; i += 2) {
               int ic = ido - i;
               int widx1 = i - 1 + iw1;
               int widx2 = i - 1 + iw2;
               int widx3 = i - 1 + iw3;
               int widx4 = i - 1 + iw4;
               float w1r = this.wtable_r[widx1 - 1];
               float w1i = this.wtable_r[widx1];
               float w2r = this.wtable_r[widx2 - 1];
               float w2i = this.wtable_r[widx2];
               float w3r = this.wtable_r[widx3 - 1];
               float w3i = this.wtable_r[widx3];
               float w4r = this.wtable_r[widx4 - 1];
               float w4i = this.wtable_r[widx4];
               int idx15 = in_off + i;
               int idx16 = in_off + ic;
               int idx17 = out_off + i;
               int iidx1 = idx15 + idx2;
               int iidx2 = idx16 + idx3;
               int iidx3 = idx15 + idx4;
               int iidx4 = idx16 + idx5;
               int iidx5 = idx15 + idx6;
               float i1i = in[iidx1 - 1];
               float i1r = in[iidx1];
               float i2i = in[iidx2 - 1];
               float i2r = in[iidx2];
               float i3i = in[iidx3 - 1];
               float i3r = in[iidx3];
               float i4i = in[iidx4 - 1];
               float i4r = in[iidx4];
               float i5i = in[iidx5 - 1];
               float i5r = in[iidx5];
               float ti5 = i3r + i2r;
               float ti2 = i3r - i2r;
               float ti4 = i5r + i4r;
               float ti3 = i5r - i4r;
               float tr5 = i3i - i2i;
               float tr2 = i3i + i2i;
               float tr4 = i5i - i4i;
               float tr3 = i5i + i4i;
               float cr2 = i1i + 0.309017F * tr2 + -0.809017F * tr3;
               float ci2 = i1r + 0.309017F * ti2 + -0.809017F * ti3;
               float cr3 = i1i + -0.809017F * tr2 + 0.309017F * tr3;
               float ci3 = i1r + -0.809017F * ti2 + 0.309017F * ti3;
               float cr5 = 0.95105654F * tr5 + 0.58778524F * tr4;
               float ci5 = 0.95105654F * ti5 + 0.58778524F * ti4;
               float cr4 = 0.58778524F * tr5 - 0.95105654F * tr4;
               float ci4 = 0.58778524F * ti5 - 0.95105654F * ti4;
               float dr3 = cr3 - ci4;
               float dr4 = cr3 + ci4;
               float di3 = ci3 + cr4;
               float di4 = ci3 - cr4;
               float dr5 = cr2 + ci5;
               float dr2 = cr2 - ci5;
               float di5 = ci2 - cr5;
               float di2 = ci2 + cr5;
               int oidx1 = idx17 + idx1;
               int oidx2 = idx17 + idx7;
               int oidx3 = idx17 + idx8;
               int oidx4 = idx17 + idx9;
               int oidx5 = idx17 + idx10;
               out[oidx1 - 1] = i1i + tr2 + tr3;
               out[oidx1] = i1r + ti2 + ti3;
               out[oidx2 - 1] = w1r * dr2 - w1i * di2;
               out[oidx2] = w1r * di2 + w1i * dr2;
               out[oidx3 - 1] = w2r * dr3 - w2i * di3;
               out[oidx3] = w2r * di3 + w2i * dr3;
               out[oidx4 - 1] = w3r * dr4 - w3i * di4;
               out[oidx4] = w3r * di4 + w3i * dr4;
               out[oidx5 - 1] = w4r * dr5 - w4i * di5;
               out[oidx5] = w4r * di5 + w4i * dr5;
            }
         }

      }
   }

   void radb5(long ido, long l1, FloatLargeArray in, long in_off, FloatLargeArray out, long out_off, long offset) {
      float tr11 = 0.309017F;
      float ti11 = 0.95105654F;
      float tr12 = -0.809017F;
      float ti12 = 0.58778524F;
      long iw1 = offset;
      long iw2 = offset + ido;
      long iw3 = iw2 + ido;
      long iw4 = iw3 + ido;
      long idx0 = l1 * ido;

      for(long k = 0L; k < l1; ++k) {
         long idx1 = k * ido;
         long idx2 = 5L * idx1;
         long idx3 = idx2 + ido;
         long idx4 = idx3 + ido;
         long idx5 = idx4 + ido;
         long idx6 = idx5 + ido;
         long idx7 = idx1 + idx0;
         long idx8 = idx7 + idx0;
         long idx9 = idx8 + idx0;
         long idx10 = idx9 + idx0;
         long idx11 = in_off + ido - 1L;
         float i1r = in.getFloat(in_off + idx2);
         float ti5 = 2.0F * in.getFloat(in_off + idx4);
         float ti4 = 2.0F * in.getFloat(in_off + idx6);
         float tr2 = 2.0F * in.getFloat(idx11 + idx3);
         float tr3 = 2.0F * in.getFloat(idx11 + idx5);
         float cr2 = i1r + 0.309017F * tr2 + -0.809017F * tr3;
         float cr3 = i1r + -0.809017F * tr2 + 0.309017F * tr3;
         float ci5 = 0.95105654F * ti5 + 0.58778524F * ti4;
         float ci4 = 0.58778524F * ti5 - 0.95105654F * ti4;
         out.setFloat(out_off + idx1, i1r + tr2 + tr3);
         out.setFloat(out_off + idx7, cr2 - ci5);
         out.setFloat(out_off + idx8, cr3 - ci4);
         out.setFloat(out_off + idx9, cr3 + ci4);
         out.setFloat(out_off + idx10, cr2 + ci5);
      }

      if (ido != 1L) {
         for(long k = 0L; k < l1; ++k) {
            long idx1 = k * ido;
            long idx2 = 5L * idx1;
            long idx3 = idx2 + ido;
            long idx4 = idx3 + ido;
            long idx5 = idx4 + ido;
            long idx6 = idx5 + ido;
            long idx7 = idx1 + idx0;
            long idx8 = idx7 + idx0;
            long idx9 = idx8 + idx0;
            long idx10 = idx9 + idx0;

            for(long i = 2L; i < ido; i += 2L) {
               long ic = ido - i;
               long widx1 = i - 1L + iw1;
               long widx2 = i - 1L + iw2;
               long widx3 = i - 1L + iw3;
               long widx4 = i - 1L + iw4;
               float w1r = this.wtable_rl.getFloat(widx1 - 1L);
               float w1i = this.wtable_rl.getFloat(widx1);
               float w2r = this.wtable_rl.getFloat(widx2 - 1L);
               float w2i = this.wtable_rl.getFloat(widx2);
               float w3r = this.wtable_rl.getFloat(widx3 - 1L);
               float w3i = this.wtable_rl.getFloat(widx3);
               float w4r = this.wtable_rl.getFloat(widx4 - 1L);
               float w4i = this.wtable_rl.getFloat(widx4);
               long idx15 = in_off + i;
               long idx16 = in_off + ic;
               long idx17 = out_off + i;
               long iidx1 = idx15 + idx2;
               long iidx2 = idx16 + idx3;
               long iidx3 = idx15 + idx4;
               long iidx4 = idx16 + idx5;
               long iidx5 = idx15 + idx6;
               float i1i = in.getFloat(iidx1 - 1L);
               float i1r = in.getFloat(iidx1);
               float i2i = in.getFloat(iidx2 - 1L);
               float i2r = in.getFloat(iidx2);
               float i3i = in.getFloat(iidx3 - 1L);
               float i3r = in.getFloat(iidx3);
               float i4i = in.getFloat(iidx4 - 1L);
               float i4r = in.getFloat(iidx4);
               float i5i = in.getFloat(iidx5 - 1L);
               float i5r = in.getFloat(iidx5);
               float ti5 = i3r + i2r;
               float ti2 = i3r - i2r;
               float ti4 = i5r + i4r;
               float ti3 = i5r - i4r;
               float tr5 = i3i - i2i;
               float tr2 = i3i + i2i;
               float tr4 = i5i - i4i;
               float tr3 = i5i + i4i;
               float cr2 = i1i + 0.309017F * tr2 + -0.809017F * tr3;
               float ci2 = i1r + 0.309017F * ti2 + -0.809017F * ti3;
               float cr3 = i1i + -0.809017F * tr2 + 0.309017F * tr3;
               float ci3 = i1r + -0.809017F * ti2 + 0.309017F * ti3;
               float cr5 = 0.95105654F * tr5 + 0.58778524F * tr4;
               float ci5 = 0.95105654F * ti5 + 0.58778524F * ti4;
               float cr4 = 0.58778524F * tr5 - 0.95105654F * tr4;
               float ci4 = 0.58778524F * ti5 - 0.95105654F * ti4;
               float dr3 = cr3 - ci4;
               float dr4 = cr3 + ci4;
               float di3 = ci3 + cr4;
               float di4 = ci3 - cr4;
               float dr5 = cr2 + ci5;
               float dr2 = cr2 - ci5;
               float di5 = ci2 - cr5;
               float di2 = ci2 + cr5;
               long oidx1 = idx17 + idx1;
               long oidx2 = idx17 + idx7;
               long oidx3 = idx17 + idx8;
               long oidx4 = idx17 + idx9;
               long oidx5 = idx17 + idx10;
               out.setFloat(oidx1 - 1L, i1i + tr2 + tr3);
               out.setFloat(oidx1, i1r + ti2 + ti3);
               out.setFloat(oidx2 - 1L, w1r * dr2 - w1i * di2);
               out.setFloat(oidx2, w1r * di2 + w1i * dr2);
               out.setFloat(oidx3 - 1L, w2r * dr3 - w2i * di3);
               out.setFloat(oidx3, w2r * di3 + w2i * dr3);
               out.setFloat(oidx4 - 1L, w3r * dr4 - w3i * di4);
               out.setFloat(oidx4, w3r * di4 + w3i * dr4);
               out.setFloat(oidx5 - 1L, w4r * dr5 - w4i * di5);
               out.setFloat(oidx5, w4r * di5 + w4i * dr5);
            }
         }

      }
   }

   void radfg(int ido, int ip, int l1, int idl1, float[] in, int in_off, float[] out, int out_off, int offset) {
      int iw1 = offset;
      float arg = ((float)Math.PI * 2F) / (float)ip;
      float dcp = (float)FastMath.cos((double)arg);
      float dsp = (float)FastMath.sin((double)arg);
      int ipph = (ip + 1) / 2;
      int nbd = (ido - 1) / 2;
      if (ido != 1) {
         for(int ik = 0; ik < idl1; ++ik) {
            out[out_off + ik] = in[in_off + ik];
         }

         for(int j = 1; j < ip; ++j) {
            int idx1 = j * l1 * ido;

            for(int k = 0; k < l1; ++k) {
               int idx2 = k * ido + idx1;
               out[out_off + idx2] = in[in_off + idx2];
            }
         }

         if (nbd <= l1) {
            int is = -ido;

            for(int j = 1; j < ip; ++j) {
               is += ido;
               int idij = is - 1;
               int idx1 = j * l1 * ido;

               for(int i = 2; i < ido; i += 2) {
                  idij += 2;
                  int idx2 = idij + iw1;
                  int idx4 = in_off + i;
                  int idx5 = out_off + i;
                  float w1r = this.wtable_r[idx2 - 1];
                  float w1i = this.wtable_r[idx2];

                  for(int k = 0; k < l1; ++k) {
                     int idx3 = k * ido + idx1;
                     int oidx1 = idx5 + idx3;
                     int iidx1 = idx4 + idx3;
                     float i1i = in[iidx1 - 1];
                     float i1r = in[iidx1];
                     out[oidx1 - 1] = w1r * i1i + w1i * i1r;
                     out[oidx1] = w1r * i1r - w1i * i1i;
                  }
               }
            }
         } else {
            int is = -ido;

            for(int j = 1; j < ip; ++j) {
               is += ido;
               int idx1 = j * l1 * ido;

               for(int k = 0; k < l1; ++k) {
                  int idij = is - 1;
                  int idx3 = k * ido + idx1;

                  for(int i = 2; i < ido; i += 2) {
                     idij += 2;
                     int idx2 = idij + iw1;
                     float w1r = this.wtable_r[idx2 - 1];
                     float w1i = this.wtable_r[idx2];
                     int oidx1 = out_off + i + idx3;
                     int iidx1 = in_off + i + idx3;
                     float i1i = in[iidx1 - 1];
                     float i1r = in[iidx1];
                     out[oidx1 - 1] = w1r * i1i + w1i * i1r;
                     out[oidx1] = w1r * i1r - w1i * i1i;
                  }
               }
            }
         }

         if (nbd >= l1) {
            for(int j = 1; j < ipph; ++j) {
               int jc = ip - j;
               int idx1 = j * l1 * ido;
               int idx2 = jc * l1 * ido;

               for(int k = 0; k < l1; ++k) {
                  int idx3 = k * ido + idx1;
                  int idx4 = k * ido + idx2;

                  for(int i = 2; i < ido; i += 2) {
                     int idx5 = in_off + i;
                     int idx6 = out_off + i;
                     int iidx1 = idx5 + idx3;
                     int iidx2 = idx5 + idx4;
                     int oidx1 = idx6 + idx3;
                     int oidx2 = idx6 + idx4;
                     float o1i = out[oidx1 - 1];
                     float o1r = out[oidx1];
                     float o2i = out[oidx2 - 1];
                     float o2r = out[oidx2];
                     in[iidx1 - 1] = o1i + o2i;
                     in[iidx1] = o1r + o2r;
                     in[iidx2 - 1] = o1r - o2r;
                     in[iidx2] = o2i - o1i;
                  }
               }
            }
         } else {
            for(int j = 1; j < ipph; ++j) {
               int jc = ip - j;
               int idx1 = j * l1 * ido;
               int idx2 = jc * l1 * ido;

               for(int i = 2; i < ido; i += 2) {
                  int idx5 = in_off + i;
                  int idx6 = out_off + i;

                  for(int k = 0; k < l1; ++k) {
                     int idx3 = k * ido + idx1;
                     int idx4 = k * ido + idx2;
                     int iidx1 = idx5 + idx3;
                     int iidx2 = idx5 + idx4;
                     int oidx1 = idx6 + idx3;
                     int oidx2 = idx6 + idx4;
                     float o1i = out[oidx1 - 1];
                     float o1r = out[oidx1];
                     float o2i = out[oidx2 - 1];
                     float o2r = out[oidx2];
                     in[iidx1 - 1] = o1i + o2i;
                     in[iidx1] = o1r + o2r;
                     in[iidx2 - 1] = o1r - o2r;
                     in[iidx2] = o2i - o1i;
                  }
               }
            }
         }
      } else {
         System.arraycopy(out, out_off, in, in_off, idl1);
      }

      for(int j = 1; j < ipph; ++j) {
         int jc = ip - j;
         int idx1 = j * l1 * ido;
         int idx2 = jc * l1 * ido;

         for(int k = 0; k < l1; ++k) {
            int idx3 = k * ido + idx1;
            int idx4 = k * ido + idx2;
            int oidx1 = out_off + idx3;
            int oidx2 = out_off + idx4;
            float o1r = out[oidx1];
            float o2r = out[oidx2];
            in[in_off + idx3] = o1r + o2r;
            in[in_off + idx4] = o2r - o1r;
         }
      }

      float ar1 = 1.0F;
      float ai1 = 0.0F;
      int idx0 = (ip - 1) * idl1;

      for(int l = 1; l < ipph; ++l) {
         int lc = ip - l;
         float ar1h = dcp * ar1 - dsp * ai1;
         ai1 = dcp * ai1 + dsp * ar1;
         ar1 = ar1h;
         int idx1 = l * idl1;
         int idx2 = lc * idl1;

         for(int ik = 0; ik < idl1; ++ik) {
            int idx3 = out_off + ik;
            int idx4 = in_off + ik;
            out[idx3 + idx1] = in[idx4] + ar1 * in[idx4 + idl1];
            out[idx3 + idx2] = ai1 * in[idx4 + idx0];
         }

         float dc2 = ar1;
         float ds2 = ai1;
         float ar2 = ar1;
         float ai2 = ai1;

         for(int j = 2; j < ipph; ++j) {
            int jc = ip - j;
            float ar2h = dc2 * ar2 - ds2 * ai2;
            ai2 = dc2 * ai2 + ds2 * ar2;
            ar2 = ar2h;
            int idx3 = j * idl1;
            int idx4 = jc * idl1;

            for(int ik = 0; ik < idl1; ++ik) {
               int idx5 = out_off + ik;
               int idx6 = in_off + ik;
               out[idx5 + idx1] += ar2 * in[idx6 + idx3];
               out[idx5 + idx2] += ai2 * in[idx6 + idx4];
            }
         }
      }

      for(int j = 1; j < ipph; ++j) {
         int idx1 = j * idl1;

         for(int ik = 0; ik < idl1; ++ik) {
            out[out_off + ik] += in[in_off + ik + idx1];
         }
      }

      if (ido >= l1) {
         for(int k = 0; k < l1; ++k) {
            int idx1 = k * ido;
            int idx2 = idx1 * ip;

            for(int i = 0; i < ido; ++i) {
               in[in_off + i + idx2] = out[out_off + i + idx1];
            }
         }
      } else {
         for(int i = 0; i < ido; ++i) {
            for(int k = 0; k < l1; ++k) {
               int idx1 = k * ido;
               in[in_off + i + idx1 * ip] = out[out_off + i + idx1];
            }
         }
      }

      int idx01 = ip * ido;

      for(int j = 1; j < ipph; ++j) {
         int jc = ip - j;
         int j2 = 2 * j;
         int idx1 = j * l1 * ido;
         int idx2 = jc * l1 * ido;
         int idx3 = j2 * ido;

         for(int k = 0; k < l1; ++k) {
            int idx4 = k * ido;
            int idx5 = idx4 + idx1;
            int idx6 = idx4 + idx2;
            int idx7 = k * idx01;
            in[in_off + ido - 1 + idx3 - ido + idx7] = out[out_off + idx5];
            in[in_off + idx3 + idx7] = out[out_off + idx6];
         }
      }

      if (ido != 1) {
         if (nbd >= l1) {
            for(int j = 1; j < ipph; ++j) {
               int jc = ip - j;
               int j2 = 2 * j;
               int idx1 = j * l1 * ido;
               int idx2 = jc * l1 * ido;
               int idx3 = j2 * ido;

               for(int k = 0; k < l1; ++k) {
                  int idx4 = k * idx01;
                  int idx5 = k * ido;

                  for(int i = 2; i < ido; i += 2) {
                     int ic = ido - i;
                     int idx6 = in_off + i;
                     int idx7 = in_off + ic;
                     int idx8 = out_off + i;
                     int iidx1 = idx6 + idx3 + idx4;
                     int iidx2 = idx7 + idx3 - ido + idx4;
                     int oidx1 = idx8 + idx5 + idx1;
                     int oidx2 = idx8 + idx5 + idx2;
                     float o1i = out[oidx1 - 1];
                     float o1r = out[oidx1];
                     float o2i = out[oidx2 - 1];
                     float o2r = out[oidx2];
                     in[iidx1 - 1] = o1i + o2i;
                     in[iidx2 - 1] = o1i - o2i;
                     in[iidx1] = o1r + o2r;
                     in[iidx2] = o2r - o1r;
                  }
               }
            }
         } else {
            for(int j = 1; j < ipph; ++j) {
               int jc = ip - j;
               int j2 = 2 * j;
               int idx1 = j * l1 * ido;
               int idx2 = jc * l1 * ido;
               int idx3 = j2 * ido;

               for(int i = 2; i < ido; i += 2) {
                  int ic = ido - i;
                  int idx6 = in_off + i;
                  int idx7 = in_off + ic;
                  int idx8 = out_off + i;

                  for(int k = 0; k < l1; ++k) {
                     int idx4 = k * idx01;
                     int idx5 = k * ido;
                     int iidx1 = idx6 + idx3 + idx4;
                     int iidx2 = idx7 + idx3 - ido + idx4;
                     int oidx1 = idx8 + idx5 + idx1;
                     int oidx2 = idx8 + idx5 + idx2;
                     float o1i = out[oidx1 - 1];
                     float o1r = out[oidx1];
                     float o2i = out[oidx2 - 1];
                     float o2r = out[oidx2];
                     in[iidx1 - 1] = o1i + o2i;
                     in[iidx2 - 1] = o1i - o2i;
                     in[iidx1] = o1r + o2r;
                     in[iidx2] = o2r - o1r;
                  }
               }
            }
         }

      }
   }

   void radfg(long ido, long ip, long l1, long idl1, FloatLargeArray in, long in_off, FloatLargeArray out, long out_off, long offset) {
      long iw1 = offset;
      float arg = ((float)Math.PI * 2F) / (float)ip;
      float dcp = (float)FastMath.cos((double)arg);
      float dsp = (float)FastMath.sin((double)arg);
      long ipph = (ip + 1L) / 2L;
      long nbd = (ido - 1L) / 2L;
      if (ido != 1L) {
         for(long ik = 0L; ik < idl1; ++ik) {
            out.setFloat(out_off + ik, in.getFloat(in_off + ik));
         }

         for(long j = 1L; j < ip; ++j) {
            long idx1 = j * l1 * ido;

            for(long k = 0L; k < l1; ++k) {
               long idx2 = k * ido + idx1;
               out.setFloat(out_off + idx2, in.getFloat(in_off + idx2));
            }
         }

         if (nbd <= l1) {
            long is = -ido;

            for(long j = 1L; j < ip; ++j) {
               is += ido;
               long idij = is - 1L;
               long idx1 = j * l1 * ido;

               for(long i = 2L; i < ido; i += 2L) {
                  idij += 2L;
                  long idx2 = idij + iw1;
                  long idx4 = in_off + i;
                  long idx5 = out_off + i;
                  float w1r = this.wtable_rl.getFloat(idx2 - 1L);
                  float w1i = this.wtable_rl.getFloat(idx2);

                  for(long k = 0L; k < l1; ++k) {
                     long idx3 = k * ido + idx1;
                     long oidx1 = idx5 + idx3;
                     long iidx1 = idx4 + idx3;
                     float i1i = in.getFloat(iidx1 - 1L);
                     float i1r = in.getFloat(iidx1);
                     out.setFloat(oidx1 - 1L, w1r * i1i + w1i * i1r);
                     out.setFloat(oidx1, w1r * i1r - w1i * i1i);
                  }
               }
            }
         } else {
            long is = -ido;

            for(long j = 1L; j < ip; ++j) {
               is += ido;
               long idx1 = j * l1 * ido;

               for(long k = 0L; k < l1; ++k) {
                  long idij = is - 1L;
                  long idx3 = k * ido + idx1;

                  for(long i = 2L; i < ido; i += 2L) {
                     idij += 2L;
                     long idx2 = idij + iw1;
                     float w1r = this.wtable_rl.getFloat(idx2 - 1L);
                     float w1i = this.wtable_rl.getFloat(idx2);
                     long oidx1 = out_off + i + idx3;
                     long iidx1 = in_off + i + idx3;
                     float i1i = in.getFloat(iidx1 - 1L);
                     float i1r = in.getFloat(iidx1);
                     out.setFloat(oidx1 - 1L, w1r * i1i + w1i * i1r);
                     out.setFloat(oidx1, w1r * i1r - w1i * i1i);
                  }
               }
            }
         }

         if (nbd >= l1) {
            for(long j = 1L; j < ipph; ++j) {
               long jc = ip - j;
               long idx1 = j * l1 * ido;
               long idx2 = jc * l1 * ido;

               for(long k = 0L; k < l1; ++k) {
                  long idx3 = k * ido + idx1;
                  long idx4 = k * ido + idx2;

                  for(long i = 2L; i < ido; i += 2L) {
                     long idx5 = in_off + i;
                     long idx6 = out_off + i;
                     long iidx1 = idx5 + idx3;
                     long iidx2 = idx5 + idx4;
                     long oidx1 = idx6 + idx3;
                     long oidx2 = idx6 + idx4;
                     float o1i = out.getFloat(oidx1 - 1L);
                     float o1r = out.getFloat(oidx1);
                     float o2i = out.getFloat(oidx2 - 1L);
                     float o2r = out.getFloat(oidx2);
                     in.setFloat(iidx1 - 1L, o1i + o2i);
                     in.setFloat(iidx1, o1r + o2r);
                     in.setFloat(iidx2 - 1L, o1r - o2r);
                     in.setFloat(iidx2, o2i - o1i);
                  }
               }
            }
         } else {
            for(long j = 1L; j < ipph; ++j) {
               long jc = ip - j;
               long idx1 = j * l1 * ido;
               long idx2 = jc * l1 * ido;

               for(long i = 2L; i < ido; i += 2L) {
                  long idx5 = in_off + i;
                  long idx6 = out_off + i;

                  for(long k = 0L; k < l1; ++k) {
                     long idx3 = k * ido + idx1;
                     long idx4 = k * ido + idx2;
                     long iidx1 = idx5 + idx3;
                     long iidx2 = idx5 + idx4;
                     long oidx1 = idx6 + idx3;
                     long oidx2 = idx6 + idx4;
                     float o1i = out.getFloat(oidx1 - 1L);
                     float o1r = out.getFloat(oidx1);
                     float o2i = out.getFloat(oidx2 - 1L);
                     float o2r = out.getFloat(oidx2);
                     in.setFloat(iidx1 - 1L, o1i + o2i);
                     in.setFloat(iidx1, o1r + o2r);
                     in.setFloat(iidx2 - 1L, o1r - o2r);
                     in.setFloat(iidx2, o2i - o1i);
                  }
               }
            }
         }
      } else {
         LargeArrayUtils.arraycopy(out, out_off, in, in_off, idl1);
      }

      for(long j = 1L; j < ipph; ++j) {
         long jc = ip - j;
         long idx1 = j * l1 * ido;
         long idx2 = jc * l1 * ido;

         for(long k = 0L; k < l1; ++k) {
            long idx3 = k * ido + idx1;
            long idx4 = k * ido + idx2;
            long oidx1 = out_off + idx3;
            long oidx2 = out_off + idx4;
            float o1r = out.getFloat(oidx1);
            float o2r = out.getFloat(oidx2);
            in.setFloat(in_off + idx3, o1r + o2r);
            in.setFloat(in_off + idx4, o2r - o1r);
         }
      }

      float ar1 = 1.0F;
      float ai1 = 0.0F;
      long idx0 = (ip - 1L) * idl1;

      for(long l = 1L; l < ipph; ++l) {
         long lc = ip - l;
         float ar1h = dcp * ar1 - dsp * ai1;
         ai1 = dcp * ai1 + dsp * ar1;
         ar1 = ar1h;
         long idx1 = l * idl1;
         long idx2 = lc * idl1;

         for(long ik = 0L; ik < idl1; ++ik) {
            long idx3 = out_off + ik;
            long idx4 = in_off + ik;
            out.setFloat(idx3 + idx1, in.getFloat(idx4) + ar1 * in.getFloat(idx4 + idl1));
            out.setFloat(idx3 + idx2, ai1 * in.getFloat(idx4 + idx0));
         }

         float dc2 = ar1;
         float ds2 = ai1;
         float ar2 = ar1;
         float ai2 = ai1;

         for(long j = 2L; j < ipph; ++j) {
            long jc = ip - j;
            float ar2h = dc2 * ar2 - ds2 * ai2;
            ai2 = dc2 * ai2 + ds2 * ar2;
            ar2 = ar2h;
            long idx3 = j * idl1;
            long idx4 = jc * idl1;

            for(long ik = 0L; ik < idl1; ++ik) {
               long idx5 = out_off + ik;
               long idx6 = in_off + ik;
               out.setFloat(idx5 + idx1, out.getFloat(idx5 + idx1) + ar2 * in.getFloat(idx6 + idx3));
               out.setFloat(idx5 + idx2, out.getFloat(idx5 + idx2) + ai2 * in.getFloat(idx6 + idx4));
            }
         }
      }

      for(long j = 1L; j < ipph; ++j) {
         long idx1 = j * idl1;

         for(long ik = 0L; ik < idl1; ++ik) {
            out.setFloat(out_off + ik, out.getFloat(out_off + ik) + in.getFloat(in_off + ik + idx1));
         }
      }

      if (ido >= l1) {
         for(long k = 0L; k < l1; ++k) {
            long idx1 = k * ido;
            long idx2 = idx1 * ip;

            for(long i = 0L; i < ido; ++i) {
               in.setFloat(in_off + i + idx2, out.getFloat(out_off + i + idx1));
            }
         }
      } else {
         for(long i = 0L; i < ido; ++i) {
            for(long k = 0L; k < l1; ++k) {
               long idx1 = k * ido;
               in.setFloat(in_off + i + idx1 * ip, out.getFloat(out_off + i + idx1));
            }
         }
      }

      long idx01 = ip * ido;

      for(long j = 1L; j < ipph; ++j) {
         long jc = ip - j;
         long j2 = 2L * j;
         long idx1 = j * l1 * ido;
         long idx2 = jc * l1 * ido;
         long idx3 = j2 * ido;

         for(long k = 0L; k < l1; ++k) {
            long idx4 = k * ido;
            long idx5 = idx4 + idx1;
            long idx6 = idx4 + idx2;
            long idx7 = k * idx01;
            in.setFloat(in_off + ido - 1L + idx3 - ido + idx7, out.getFloat(out_off + idx5));
            in.setFloat(in_off + idx3 + idx7, out.getFloat(out_off + idx6));
         }
      }

      if (ido != 1L) {
         if (nbd >= l1) {
            for(long j = 1L; j < ipph; ++j) {
               long jc = ip - j;
               long j2 = 2L * j;
               long idx1 = j * l1 * ido;
               long idx2 = jc * l1 * ido;
               long idx3 = j2 * ido;

               for(long k = 0L; k < l1; ++k) {
                  long idx4 = k * idx01;
                  long idx5 = k * ido;

                  for(long i = 2L; i < ido; i += 2L) {
                     long ic = ido - i;
                     long idx6 = in_off + i;
                     long idx7 = in_off + ic;
                     long idx8 = out_off + i;
                     long iidx1 = idx6 + idx3 + idx4;
                     long iidx2 = idx7 + idx3 - ido + idx4;
                     long oidx1 = idx8 + idx5 + idx1;
                     long oidx2 = idx8 + idx5 + idx2;
                     float o1i = out.getFloat(oidx1 - 1L);
                     float o1r = out.getFloat(oidx1);
                     float o2i = out.getFloat(oidx2 - 1L);
                     float o2r = out.getFloat(oidx2);
                     in.setFloat(iidx1 - 1L, o1i + o2i);
                     in.setFloat(iidx2 - 1L, o1i - o2i);
                     in.setFloat(iidx1, o1r + o2r);
                     in.setFloat(iidx2, o2r - o1r);
                  }
               }
            }
         } else {
            for(long j = 1L; j < ipph; ++j) {
               long jc = ip - j;
               long j2 = 2L * j;
               long idx1 = j * l1 * ido;
               long idx2 = jc * l1 * ido;
               long idx3 = j2 * ido;

               for(long i = 2L; i < ido; i += 2L) {
                  long ic = ido - i;
                  long idx6 = in_off + i;
                  long idx7 = in_off + ic;
                  long idx8 = out_off + i;

                  for(long k = 0L; k < l1; ++k) {
                     long idx4 = k * idx01;
                     long idx5 = k * ido;
                     long iidx1 = idx6 + idx3 + idx4;
                     long iidx2 = idx7 + idx3 - ido + idx4;
                     long oidx1 = idx8 + idx5 + idx1;
                     long oidx2 = idx8 + idx5 + idx2;
                     float o1i = out.getFloat(oidx1 - 1L);
                     float o1r = out.getFloat(oidx1);
                     float o2i = out.getFloat(oidx2 - 1L);
                     float o2r = out.getFloat(oidx2);
                     in.setFloat(iidx1 - 1L, o1i + o2i);
                     in.setFloat(iidx2 - 1L, o1i - o2i);
                     in.setFloat(iidx1, o1r + o2r);
                     in.setFloat(iidx2, o2r - o1r);
                  }
               }
            }
         }

      }
   }

   void radbg(int ido, int ip, int l1, int idl1, float[] in, int in_off, float[] out, int out_off, int offset) {
      int iw1 = offset;
      float arg = ((float)Math.PI * 2F) / (float)ip;
      float dcp = (float)FastMath.cos((double)arg);
      float dsp = (float)FastMath.sin((double)arg);
      int nbd = (ido - 1) / 2;
      int ipph = (ip + 1) / 2;
      int idx0 = ip * ido;
      if (ido >= l1) {
         for(int k = 0; k < l1; ++k) {
            int idx1 = k * ido;
            int idx2 = k * idx0;

            for(int i = 0; i < ido; ++i) {
               out[out_off + i + idx1] = in[in_off + i + idx2];
            }
         }
      } else {
         for(int i = 0; i < ido; ++i) {
            int idx1 = out_off + i;
            int idx2 = in_off + i;

            for(int k = 0; k < l1; ++k) {
               out[idx1 + k * ido] = in[idx2 + k * idx0];
            }
         }
      }

      int iidx0 = in_off + ido - 1;

      for(int j = 1; j < ipph; ++j) {
         int jc = ip - j;
         int j2 = 2 * j;
         int idx1 = j * l1 * ido;
         int idx2 = jc * l1 * ido;
         int idx3 = j2 * ido;

         for(int k = 0; k < l1; ++k) {
            int idx4 = k * ido;
            int idx5 = idx4 * ip;
            int iidx1 = iidx0 + idx3 + idx5 - ido;
            int iidx2 = in_off + idx3 + idx5;
            float i1r = in[iidx1];
            float i2r = in[iidx2];
            out[out_off + idx4 + idx1] = i1r + i1r;
            out[out_off + idx4 + idx2] = i2r + i2r;
         }
      }

      if (ido != 1) {
         if (nbd >= l1) {
            for(int j = 1; j < ipph; ++j) {
               int jc = ip - j;
               int idx1 = j * l1 * ido;
               int idx2 = jc * l1 * ido;
               int idx3 = 2 * j * ido;

               for(int k = 0; k < l1; ++k) {
                  int idx4 = k * ido + idx1;
                  int idx5 = k * ido + idx2;
                  int idx6 = k * ip * ido + idx3;

                  for(int i = 2; i < ido; i += 2) {
                     int ic = ido - i;
                     int idx7 = out_off + i;
                     int idx8 = in_off + ic;
                     int idx9 = in_off + i;
                     int oidx1 = idx7 + idx4;
                     int oidx2 = idx7 + idx5;
                     int iidx1 = idx9 + idx6;
                     int iidx2 = idx8 + idx6 - ido;
                     float a1i = in[iidx1 - 1];
                     float a1r = in[iidx1];
                     float a2i = in[iidx2 - 1];
                     float a2r = in[iidx2];
                     out[oidx1 - 1] = a1i + a2i;
                     out[oidx2 - 1] = a1i - a2i;
                     out[oidx1] = a1r - a2r;
                     out[oidx2] = a1r + a2r;
                  }
               }
            }
         } else {
            for(int j = 1; j < ipph; ++j) {
               int jc = ip - j;
               int idx1 = j * l1 * ido;
               int idx2 = jc * l1 * ido;
               int idx3 = 2 * j * ido;

               for(int i = 2; i < ido; i += 2) {
                  int ic = ido - i;
                  int idx7 = out_off + i;
                  int idx8 = in_off + ic;
                  int idx9 = in_off + i;

                  for(int k = 0; k < l1; ++k) {
                     int idx4 = k * ido + idx1;
                     int idx5 = k * ido + idx2;
                     int idx6 = k * ip * ido + idx3;
                     int oidx1 = idx7 + idx4;
                     int oidx2 = idx7 + idx5;
                     int iidx1 = idx9 + idx6;
                     int iidx2 = idx8 + idx6 - ido;
                     float a1i = in[iidx1 - 1];
                     float a1r = in[iidx1];
                     float a2i = in[iidx2 - 1];
                     float a2r = in[iidx2];
                     out[oidx1 - 1] = a1i + a2i;
                     out[oidx2 - 1] = a1i - a2i;
                     out[oidx1] = a1r - a2r;
                     out[oidx2] = a1r + a2r;
                  }
               }
            }
         }
      }

      float ar1 = 1.0F;
      float ai1 = 0.0F;
      int idx01 = (ip - 1) * idl1;

      for(int l = 1; l < ipph; ++l) {
         int lc = ip - l;
         float ar1h = dcp * ar1 - dsp * ai1;
         ai1 = dcp * ai1 + dsp * ar1;
         ar1 = ar1h;
         int idx1 = l * idl1;
         int idx2 = lc * idl1;

         for(int ik = 0; ik < idl1; ++ik) {
            int idx3 = in_off + ik;
            int idx4 = out_off + ik;
            in[idx3 + idx1] = out[idx4] + ar1 * out[idx4 + idl1];
            in[idx3 + idx2] = ai1 * out[idx4 + idx01];
         }

         float dc2 = ar1;
         float ds2 = ai1;
         float ar2 = ar1;
         float ai2 = ai1;

         for(int j = 2; j < ipph; ++j) {
            int jc = ip - j;
            float ar2h = dc2 * ar2 - ds2 * ai2;
            ai2 = dc2 * ai2 + ds2 * ar2;
            ar2 = ar2h;
            int idx5 = j * idl1;
            int idx6 = jc * idl1;

            for(int ik = 0; ik < idl1; ++ik) {
               int idx7 = in_off + ik;
               int idx8 = out_off + ik;
               in[idx7 + idx1] += ar2 * out[idx8 + idx5];
               in[idx7 + idx2] += ai2 * out[idx8 + idx6];
            }
         }
      }

      for(int j = 1; j < ipph; ++j) {
         int idx1 = j * idl1;

         for(int ik = 0; ik < idl1; ++ik) {
            int idx2 = out_off + ik;
            out[idx2] += out[idx2 + idx1];
         }
      }

      for(int j = 1; j < ipph; ++j) {
         int jc = ip - j;
         int idx1 = j * l1 * ido;
         int idx2 = jc * l1 * ido;

         for(int k = 0; k < l1; ++k) {
            int idx3 = k * ido;
            int oidx1 = out_off + idx3;
            int iidx1 = in_off + idx3 + idx1;
            int iidx2 = in_off + idx3 + idx2;
            float i1r = in[iidx1];
            float i2r = in[iidx2];
            out[oidx1 + idx1] = i1r - i2r;
            out[oidx1 + idx2] = i1r + i2r;
         }
      }

      if (ido != 1) {
         if (nbd >= l1) {
            for(int j = 1; j < ipph; ++j) {
               int jc = ip - j;
               int idx1 = j * l1 * ido;
               int idx2 = jc * l1 * ido;

               for(int k = 0; k < l1; ++k) {
                  int idx3 = k * ido;

                  for(int i = 2; i < ido; i += 2) {
                     int idx4 = out_off + i;
                     int idx5 = in_off + i;
                     int oidx1 = idx4 + idx3 + idx1;
                     int oidx2 = idx4 + idx3 + idx2;
                     int iidx1 = idx5 + idx3 + idx1;
                     int iidx2 = idx5 + idx3 + idx2;
                     float i1i = in[iidx1 - 1];
                     float i1r = in[iidx1];
                     float i2i = in[iidx2 - 1];
                     float i2r = in[iidx2];
                     out[oidx1 - 1] = i1i - i2r;
                     out[oidx2 - 1] = i1i + i2r;
                     out[oidx1] = i1r + i2i;
                     out[oidx2] = i1r - i2i;
                  }
               }
            }
         } else {
            for(int j = 1; j < ipph; ++j) {
               int jc = ip - j;
               int idx1 = j * l1 * ido;
               int idx2 = jc * l1 * ido;

               for(int i = 2; i < ido; i += 2) {
                  int idx4 = out_off + i;
                  int idx5 = in_off + i;

                  for(int k = 0; k < l1; ++k) {
                     int idx3 = k * ido;
                     int oidx1 = idx4 + idx3 + idx1;
                     int oidx2 = idx4 + idx3 + idx2;
                     int iidx1 = idx5 + idx3 + idx1;
                     int iidx2 = idx5 + idx3 + idx2;
                     float i1i = in[iidx1 - 1];
                     float i1r = in[iidx1];
                     float i2i = in[iidx2 - 1];
                     float i2r = in[iidx2];
                     out[oidx1 - 1] = i1i - i2r;
                     out[oidx2 - 1] = i1i + i2r;
                     out[oidx1] = i1r + i2i;
                     out[oidx2] = i1r - i2i;
                  }
               }
            }
         }

         System.arraycopy(out, out_off, in, in_off, idl1);

         for(int j = 1; j < ip; ++j) {
            int idx1 = j * l1 * ido;

            for(int k = 0; k < l1; ++k) {
               int idx2 = k * ido + idx1;
               in[in_off + idx2] = out[out_off + idx2];
            }
         }

         if (nbd <= l1) {
            int is = -ido;

            for(int j = 1; j < ip; ++j) {
               is += ido;
               int idij = is - 1;
               int idx1 = j * l1 * ido;

               for(int i = 2; i < ido; i += 2) {
                  idij += 2;
                  int idx2 = idij + iw1;
                  float w1r = this.wtable_r[idx2 - 1];
                  float w1i = this.wtable_r[idx2];
                  int idx4 = in_off + i;
                  int idx5 = out_off + i;

                  for(int k = 0; k < l1; ++k) {
                     int idx3 = k * ido + idx1;
                     int iidx1 = idx4 + idx3;
                     int oidx1 = idx5 + idx3;
                     float o1i = out[oidx1 - 1];
                     float o1r = out[oidx1];
                     in[iidx1 - 1] = w1r * o1i - w1i * o1r;
                     in[iidx1] = w1r * o1r + w1i * o1i;
                  }
               }
            }
         } else {
            int is = -ido;

            for(int j = 1; j < ip; ++j) {
               is += ido;
               int idx1 = j * l1 * ido;

               for(int k = 0; k < l1; ++k) {
                  int idij = is - 1;
                  int idx3 = k * ido + idx1;

                  for(int i = 2; i < ido; i += 2) {
                     idij += 2;
                     int idx2 = idij + iw1;
                     float w1r = this.wtable_r[idx2 - 1];
                     float w1i = this.wtable_r[idx2];
                     int idx4 = in_off + i;
                     int idx5 = out_off + i;
                     int iidx1 = idx4 + idx3;
                     int oidx1 = idx5 + idx3;
                     float o1i = out[oidx1 - 1];
                     float o1r = out[oidx1];
                     in[iidx1 - 1] = w1r * o1i - w1i * o1r;
                     in[iidx1] = w1r * o1r + w1i * o1i;
                  }
               }
            }
         }

      }
   }

   void radbg(long ido, long ip, long l1, long idl1, FloatLargeArray in, long in_off, FloatLargeArray out, long out_off, long offset) {
      long iw1 = offset;
      float arg = ((float)Math.PI * 2F) / (float)ip;
      float dcp = (float)FastMath.cos((double)arg);
      float dsp = (float)FastMath.sin((double)arg);
      long nbd = (ido - 1L) / 2L;
      long ipph = (ip + 1L) / 2L;
      long idx0 = ip * ido;
      if (ido >= l1) {
         for(long k = 0L; k < l1; ++k) {
            long idx1 = k * ido;
            long idx2 = k * idx0;

            for(long i = 0L; i < ido; ++i) {
               out.setFloat(out_off + i + idx1, in.getFloat(in_off + i + idx2));
            }
         }
      } else {
         for(long i = 0L; i < ido; ++i) {
            long idx1 = out_off + i;
            long idx2 = in_off + i;

            for(long k = 0L; k < l1; ++k) {
               out.setFloat(idx1 + k * ido, in.getFloat(idx2 + k * idx0));
            }
         }
      }

      long iidx0 = in_off + ido - 1L;

      for(long j = 1L; j < ipph; ++j) {
         long jc = ip - j;
         long j2 = 2L * j;
         long idx1 = j * l1 * ido;
         long idx2 = jc * l1 * ido;
         long idx3 = j2 * ido;

         for(long k = 0L; k < l1; ++k) {
            long idx4 = k * ido;
            long idx5 = idx4 * ip;
            long iidx1 = iidx0 + idx3 + idx5 - ido;
            long iidx2 = in_off + idx3 + idx5;
            float i1r = in.getFloat(iidx1);
            float i2r = in.getFloat(iidx2);
            out.setFloat(out_off + idx4 + idx1, i1r + i1r);
            out.setFloat(out_off + idx4 + idx2, i2r + i2r);
         }
      }

      if (ido != 1L) {
         if (nbd >= l1) {
            for(long j = 1L; j < ipph; ++j) {
               long jc = ip - j;
               long idx1 = j * l1 * ido;
               long idx2 = jc * l1 * ido;
               long idx3 = 2L * j * ido;

               for(long k = 0L; k < l1; ++k) {
                  long idx4 = k * ido + idx1;
                  long idx5 = k * ido + idx2;
                  long idx6 = k * ip * ido + idx3;

                  for(long i = 2L; i < ido; i += 2L) {
                     long ic = ido - i;
                     long idx7 = out_off + i;
                     long idx8 = in_off + ic;
                     long idx9 = in_off + i;
                     long oidx1 = idx7 + idx4;
                     long oidx2 = idx7 + idx5;
                     long iidx1 = idx9 + idx6;
                     long iidx2 = idx8 + idx6 - ido;
                     float a1i = in.getFloat(iidx1 - 1L);
                     float a1r = in.getFloat(iidx1);
                     float a2i = in.getFloat(iidx2 - 1L);
                     float a2r = in.getFloat(iidx2);
                     out.setFloat(oidx1 - 1L, a1i + a2i);
                     out.setFloat(oidx2 - 1L, a1i - a2i);
                     out.setFloat(oidx1, a1r - a2r);
                     out.setFloat(oidx2, a1r + a2r);
                  }
               }
            }
         } else {
            for(long j = 1L; j < ipph; ++j) {
               long jc = ip - j;
               long idx1 = j * l1 * ido;
               long idx2 = jc * l1 * ido;
               long idx3 = 2L * j * ido;

               for(long i = 2L; i < ido; i += 2L) {
                  long ic = ido - i;
                  long idx7 = out_off + i;
                  long idx8 = in_off + ic;
                  long idx9 = in_off + i;

                  for(long k = 0L; k < l1; ++k) {
                     long idx4 = k * ido + idx1;
                     long idx5 = k * ido + idx2;
                     long idx6 = k * ip * ido + idx3;
                     long oidx1 = idx7 + idx4;
                     long oidx2 = idx7 + idx5;
                     long iidx1 = idx9 + idx6;
                     long iidx2 = idx8 + idx6 - ido;
                     float a1i = in.getFloat(iidx1 - 1L);
                     float a1r = in.getFloat(iidx1);
                     float a2i = in.getFloat(iidx2 - 1L);
                     float a2r = in.getFloat(iidx2);
                     out.setFloat(oidx1 - 1L, a1i + a2i);
                     out.setFloat(oidx2 - 1L, a1i - a2i);
                     out.setFloat(oidx1, a1r - a2r);
                     out.setFloat(oidx2, a1r + a2r);
                  }
               }
            }
         }
      }

      float ar1 = 1.0F;
      float ai1 = 0.0F;
      long idx01 = (ip - 1L) * idl1;

      for(long l = 1L; l < ipph; ++l) {
         long lc = ip - l;
         float ar1h = dcp * ar1 - dsp * ai1;
         ai1 = dcp * ai1 + dsp * ar1;
         ar1 = ar1h;
         long idx1 = l * idl1;
         long idx2 = lc * idl1;

         for(long ik = 0L; ik < idl1; ++ik) {
            long idx3 = in_off + ik;
            long idx4 = out_off + ik;
            in.setFloat(idx3 + idx1, out.getFloat(idx4) + ar1 * out.getFloat(idx4 + idl1));
            in.setFloat(idx3 + idx2, ai1 * out.getFloat(idx4 + idx01));
         }

         float dc2 = ar1;
         float ds2 = ai1;
         float ar2 = ar1;
         float ai2 = ai1;

         for(long j = 2L; j < ipph; ++j) {
            long jc = ip - j;
            float ar2h = dc2 * ar2 - ds2 * ai2;
            ai2 = dc2 * ai2 + ds2 * ar2;
            ar2 = ar2h;
            long idx5 = j * idl1;
            long idx6 = jc * idl1;

            for(long ik = 0L; ik < idl1; ++ik) {
               long idx7 = in_off + ik;
               long idx8 = out_off + ik;
               in.setFloat(idx7 + idx1, in.getFloat(idx7 + idx1) + ar2 * out.getFloat(idx8 + idx5));
               in.setFloat(idx7 + idx2, in.getFloat(idx7 + idx2) + ai2 * out.getFloat(idx8 + idx6));
            }
         }
      }

      for(long j = 1L; j < ipph; ++j) {
         long idx1 = j * idl1;

         for(long ik = 0L; ik < idl1; ++ik) {
            long idx2 = out_off + ik;
            out.setFloat(idx2, out.getFloat(idx2) + out.getFloat(idx2 + idx1));
         }
      }

      for(long j = 1L; j < ipph; ++j) {
         long jc = ip - j;
         long idx1 = j * l1 * ido;
         long idx2 = jc * l1 * ido;

         for(long k = 0L; k < l1; ++k) {
            long idx3 = k * ido;
            long oidx1 = out_off + idx3;
            long iidx1 = in_off + idx3 + idx1;
            long iidx2 = in_off + idx3 + idx2;
            float i1r = in.getFloat(iidx1);
            float i2r = in.getFloat(iidx2);
            out.setFloat(oidx1 + idx1, i1r - i2r);
            out.setFloat(oidx1 + idx2, i1r + i2r);
         }
      }

      if (ido != 1L) {
         if (nbd >= l1) {
            for(long j = 1L; j < ipph; ++j) {
               long jc = ip - j;
               long idx1 = j * l1 * ido;
               long idx2 = jc * l1 * ido;

               for(long k = 0L; k < l1; ++k) {
                  long idx3 = k * ido;

                  for(long i = 2L; i < ido; i += 2L) {
                     long idx4 = out_off + i;
                     long idx5 = in_off + i;
                     long oidx1 = idx4 + idx3 + idx1;
                     long oidx2 = idx4 + idx3 + idx2;
                     long iidx1 = idx5 + idx3 + idx1;
                     long iidx2 = idx5 + idx3 + idx2;
                     float i1i = in.getFloat(iidx1 - 1L);
                     float i1r = in.getFloat(iidx1);
                     float i2i = in.getFloat(iidx2 - 1L);
                     float i2r = in.getFloat(iidx2);
                     out.setFloat(oidx1 - 1L, i1i - i2r);
                     out.setFloat(oidx2 - 1L, i1i + i2r);
                     out.setFloat(oidx1, i1r + i2i);
                     out.setFloat(oidx2, i1r - i2i);
                  }
               }
            }
         } else {
            for(long j = 1L; j < ipph; ++j) {
               long jc = ip - j;
               long idx1 = j * l1 * ido;
               long idx2 = jc * l1 * ido;

               for(long i = 2L; i < ido; i += 2L) {
                  long idx4 = out_off + i;
                  long idx5 = in_off + i;

                  for(long k = 0L; k < l1; ++k) {
                     long idx3 = k * ido;
                     long oidx1 = idx4 + idx3 + idx1;
                     long oidx2 = idx4 + idx3 + idx2;
                     long iidx1 = idx5 + idx3 + idx1;
                     long iidx2 = idx5 + idx3 + idx2;
                     float i1i = in.getFloat(iidx1 - 1L);
                     float i1r = in.getFloat(iidx1);
                     float i2i = in.getFloat(iidx2 - 1L);
                     float i2r = in.getFloat(iidx2);
                     out.setFloat(oidx1 - 1L, i1i - i2r);
                     out.setFloat(oidx2 - 1L, i1i + i2r);
                     out.setFloat(oidx1, i1r + i2i);
                     out.setFloat(oidx2, i1r - i2i);
                  }
               }
            }
         }

         LargeArrayUtils.arraycopy(out, out_off, in, in_off, idl1);

         for(long j = 1L; j < ip; ++j) {
            long idx1 = j * l1 * ido;

            for(long k = 0L; k < l1; ++k) {
               long idx2 = k * ido + idx1;
               in.setFloat(in_off + idx2, out.getFloat(out_off + idx2));
            }
         }

         if (nbd <= l1) {
            long is = -ido;

            for(long j = 1L; j < ip; ++j) {
               is += ido;
               long idij = is - 1L;
               long idx1 = j * l1 * ido;

               for(long i = 2L; i < ido; i += 2L) {
                  idij += 2L;
                  long idx2 = idij + iw1;
                  float w1r = this.wtable_rl.getFloat(idx2 - 1L);
                  float w1i = this.wtable_rl.getFloat(idx2);
                  long idx4 = in_off + i;
                  long idx5 = out_off + i;

                  for(long k = 0L; k < l1; ++k) {
                     long idx3 = k * ido + idx1;
                     long iidx1 = idx4 + idx3;
                     long oidx1 = idx5 + idx3;
                     float o1i = out.getFloat(oidx1 - 1L);
                     float o1r = out.getFloat(oidx1);
                     in.setFloat(iidx1 - 1L, w1r * o1i - w1i * o1r);
                     in.setFloat(iidx1, w1r * o1r + w1i * o1i);
                  }
               }
            }
         } else {
            long is = -ido;

            for(long j = 1L; j < ip; ++j) {
               is += ido;
               long idx1 = j * l1 * ido;

               for(long k = 0L; k < l1; ++k) {
                  long idij = is - 1L;
                  long idx3 = k * ido + idx1;

                  for(long i = 2L; i < ido; i += 2L) {
                     idij += 2L;
                     long idx2 = idij + iw1;
                     float w1r = this.wtable_rl.getFloat(idx2 - 1L);
                     float w1i = this.wtable_rl.getFloat(idx2);
                     long idx4 = in_off + i;
                     long idx5 = out_off + i;
                     long iidx1 = idx4 + idx3;
                     long oidx1 = idx5 + idx3;
                     float o1i = out.getFloat(oidx1 - 1L);
                     float o1r = out.getFloat(oidx1);
                     in.setFloat(iidx1 - 1L, w1r * o1i - w1i * o1r);
                     in.setFloat(iidx1, w1r * o1r + w1i * o1i);
                  }
               }
            }
         }

      }
   }

   void cfftf(float[] a, int offa, int isign) {
      int[] nac = new int[1];
      int twon = 2 * this.n;
      float[] ch = new float[twon];
      int iw2 = 4 * this.n;
      nac[0] = 0;
      int nf = (int)this.wtable[1 + iw2];
      int na = 0;
      int l1 = 1;
      int iw = twon;

      for(int k1 = 2; k1 <= nf + 1; ++k1) {
         int ipll = (int)this.wtable[k1 + iw2];
         int l2 = ipll * l1;
         int ido = this.n / l2;
         int idot = ido + ido;
         int idl1 = idot * l1;
         switch (ipll) {
            case 2:
               if (na == 0) {
                  this.passf2(idot, l1, a, offa, ch, 0, iw, isign);
               } else {
                  this.passf2(idot, l1, ch, 0, a, offa, iw, isign);
               }

               na = 1 - na;
               break;
            case 3:
               if (na == 0) {
                  this.passf3(idot, l1, a, offa, ch, 0, iw, isign);
               } else {
                  this.passf3(idot, l1, ch, 0, a, offa, iw, isign);
               }

               na = 1 - na;
               break;
            case 4:
               if (na == 0) {
                  this.passf4(idot, l1, a, offa, ch, 0, iw, isign);
               } else {
                  this.passf4(idot, l1, ch, 0, a, offa, iw, isign);
               }

               na = 1 - na;
               break;
            case 5:
               if (na == 0) {
                  this.passf5(idot, l1, a, offa, ch, 0, iw, isign);
               } else {
                  this.passf5(idot, l1, ch, 0, a, offa, iw, isign);
               }

               na = 1 - na;
               break;
            default:
               if (na == 0) {
                  this.passfg(nac, idot, ipll, l1, idl1, a, offa, ch, 0, iw, isign);
               } else {
                  this.passfg(nac, idot, ipll, l1, idl1, ch, 0, a, offa, iw, isign);
               }

               if (nac[0] != 0) {
                  na = 1 - na;
               }
         }

         l1 = l2;
         iw += (ipll - 1) * idot;
      }

      if (na != 0) {
         System.arraycopy(ch, 0, a, offa, twon);
      }
   }

   void cfftf(FloatLargeArray a, long offa, int isign) {
      int[] nac = new int[1];
      long twon = 2L * this.nl;
      FloatLargeArray ch = new FloatLargeArray(twon);
      long iw2 = 4L * this.nl;
      nac[0] = 0;
      long nf = (long)this.wtablel.getFloat(1L + iw2);
      long na = 0L;
      long l1 = 1L;
      long iw = twon;

      for(long k1 = 2L; k1 <= nf + 1L; ++k1) {
         int ipll = (int)this.wtablel.getFloat(k1 + iw2);
         long l2 = (long)ipll * l1;
         long ido = this.nl / l2;
         long idot = ido + ido;
         long idl1 = idot * l1;
         switch (ipll) {
            case 2:
               if (na == 0L) {
                  this.passf2(idot, l1, a, offa, ch, 0L, iw, (long)isign);
               } else {
                  this.passf2(idot, l1, ch, 0L, a, offa, iw, (long)isign);
               }

               na = 1L - na;
               break;
            case 3:
               if (na == 0L) {
                  this.passf3(idot, l1, a, offa, ch, 0L, iw, (long)isign);
               } else {
                  this.passf3(idot, l1, ch, 0L, a, offa, iw, (long)isign);
               }

               na = 1L - na;
               break;
            case 4:
               if (na == 0L) {
                  this.passf4(idot, l1, a, offa, ch, 0L, iw, isign);
               } else {
                  this.passf4(idot, l1, ch, 0L, a, offa, iw, isign);
               }

               na = 1L - na;
               break;
            case 5:
               if (na == 0L) {
                  this.passf5(idot, l1, a, offa, ch, 0L, iw, (long)isign);
               } else {
                  this.passf5(idot, l1, ch, 0L, a, offa, iw, (long)isign);
               }

               na = 1L - na;
               break;
            default:
               if (na == 0L) {
                  this.passfg(nac, idot, (long)ipll, l1, idl1, a, offa, ch, 0L, iw, (long)isign);
               } else {
                  this.passfg(nac, idot, (long)ipll, l1, idl1, ch, 0L, a, offa, iw, (long)isign);
               }

               if (nac[0] != 0) {
                  na = 1L - na;
               }
         }

         l1 = l2;
         iw += (long)(ipll - 1) * idot;
      }

      if (na != 0L) {
         LargeArrayUtils.arraycopy(ch, 0L, a, offa, twon);
      }
   }

   void passf2(int ido, int l1, float[] in, int in_off, float[] out, int out_off, int offset, int isign) {
      int iw1 = offset;
      int idx = ido * l1;
      if (ido <= 2) {
         for(int k = 0; k < l1; ++k) {
            int idx0 = k * ido;
            int iidx1 = in_off + 2 * idx0;
            int iidx2 = iidx1 + ido;
            float a1r = in[iidx1];
            float a1i = in[iidx1 + 1];
            float a2r = in[iidx2];
            float a2i = in[iidx2 + 1];
            int oidx1 = out_off + idx0;
            int oidx2 = oidx1 + idx;
            out[oidx1] = a1r + a2r;
            out[oidx1 + 1] = a1i + a2i;
            out[oidx2] = a1r - a2r;
            out[oidx2 + 1] = a1i - a2i;
         }
      } else {
         for(int k = 0; k < l1; ++k) {
            for(int i = 0; i < ido - 1; i += 2) {
               int idx0 = k * ido;
               int iidx1 = in_off + i + 2 * idx0;
               int iidx2 = iidx1 + ido;
               float i1r = in[iidx1];
               float i1i = in[iidx1 + 1];
               float i2r = in[iidx2];
               float i2i = in[iidx2 + 1];
               int widx1 = i + iw1;
               float w1r = this.wtable[widx1];
               float w1i = (float)isign * this.wtable[widx1 + 1];
               float t1r = i1r - i2r;
               float t1i = i1i - i2i;
               int oidx1 = out_off + i + idx0;
               int oidx2 = oidx1 + idx;
               out[oidx1] = i1r + i2r;
               out[oidx1 + 1] = i1i + i2i;
               out[oidx2] = w1r * t1r - w1i * t1i;
               out[oidx2 + 1] = w1r * t1i + w1i * t1r;
            }
         }
      }

   }

   void passf2(long ido, long l1, FloatLargeArray in, long in_off, FloatLargeArray out, long out_off, long offset, long isign) {
      long iw1 = offset;
      long idx = ido * l1;
      if (ido <= 2L) {
         for(long k = 0L; k < l1; ++k) {
            long idx0 = k * ido;
            long iidx1 = in_off + 2L * idx0;
            long iidx2 = iidx1 + ido;
            float a1r = in.getFloat(iidx1);
            float a1i = in.getFloat(iidx1 + 1L);
            float a2r = in.getFloat(iidx2);
            float a2i = in.getFloat(iidx2 + 1L);
            long oidx1 = out_off + idx0;
            long oidx2 = oidx1 + idx;
            out.setFloat(oidx1, a1r + a2r);
            out.setFloat(oidx1 + 1L, a1i + a2i);
            out.setFloat(oidx2, a1r - a2r);
            out.setFloat(oidx2 + 1L, a1i - a2i);
         }
      } else {
         for(long k = 0L; k < l1; ++k) {
            for(long i = 0L; i < ido - 1L; i += 2L) {
               long idx0 = k * ido;
               long iidx1 = in_off + i + 2L * idx0;
               long iidx2 = iidx1 + ido;
               float i1r = in.getFloat(iidx1);
               float i1i = in.getFloat(iidx1 + 1L);
               float i2r = in.getFloat(iidx2);
               float i2i = in.getFloat(iidx2 + 1L);
               long widx1 = i + iw1;
               float w1r = this.wtablel.getFloat(widx1);
               float w1i = (float)isign * this.wtablel.getFloat(widx1 + 1L);
               float t1r = i1r - i2r;
               float t1i = i1i - i2i;
               long oidx1 = out_off + i + idx0;
               long oidx2 = oidx1 + idx;
               out.setFloat(oidx1, i1r + i2r);
               out.setFloat(oidx1 + 1L, i1i + i2i);
               out.setFloat(oidx2, w1r * t1r - w1i * t1i);
               out.setFloat(oidx2 + 1L, w1r * t1i + w1i * t1r);
            }
         }
      }

   }

   void passf3(int ido, int l1, float[] in, int in_off, float[] out, int out_off, int offset, int isign) {
      float taur = -0.5F;
      float taui = 0.8660254F;
      int iw1 = offset;
      int iw2 = offset + ido;
      int idxt = l1 * ido;
      if (ido == 2) {
         for(int k = 1; k <= l1; ++k) {
            int iidx1 = in_off + (3 * k - 2) * ido;
            int iidx2 = iidx1 + ido;
            int iidx3 = iidx1 - ido;
            float i1r = in[iidx1];
            float i1i = in[iidx1 + 1];
            float i2r = in[iidx2];
            float i2i = in[iidx2 + 1];
            float i3r = in[iidx3];
            float i3i = in[iidx3 + 1];
            float tr2 = i1r + i2r;
            float cr2 = i3r + -0.5F * tr2;
            float ti2 = i1i + i2i;
            float ci2 = i3i + -0.5F * ti2;
            float cr3 = (float)isign * 0.8660254F * (i1r - i2r);
            float ci3 = (float)isign * 0.8660254F * (i1i - i2i);
            int oidx1 = out_off + (k - 1) * ido;
            int oidx2 = oidx1 + idxt;
            int oidx3 = oidx2 + idxt;
            out[oidx1] = in[iidx3] + tr2;
            out[oidx1 + 1] = i3i + ti2;
            out[oidx2] = cr2 - ci3;
            out[oidx2 + 1] = ci2 + cr3;
            out[oidx3] = cr2 + ci3;
            out[oidx3 + 1] = ci2 - cr3;
         }
      } else {
         for(int k = 1; k <= l1; ++k) {
            int idx1 = in_off + (3 * k - 2) * ido;
            int idx2 = out_off + (k - 1) * ido;

            for(int i = 0; i < ido - 1; i += 2) {
               int iidx1 = i + idx1;
               int iidx2 = iidx1 + ido;
               int iidx3 = iidx1 - ido;
               float a1r = in[iidx1];
               float a1i = in[iidx1 + 1];
               float a2r = in[iidx2];
               float a2i = in[iidx2 + 1];
               float a3r = in[iidx3];
               float a3i = in[iidx3 + 1];
               float tr2 = a1r + a2r;
               float cr2 = a3r + -0.5F * tr2;
               float ti2 = a1i + a2i;
               float ci2 = a3i + -0.5F * ti2;
               float cr3 = (float)isign * 0.8660254F * (a1r - a2r);
               float ci3 = (float)isign * 0.8660254F * (a1i - a2i);
               float dr2 = cr2 - ci3;
               float dr3 = cr2 + ci3;
               float di2 = ci2 + cr3;
               float di3 = ci2 - cr3;
               int widx1 = i + iw1;
               int widx2 = i + iw2;
               float w1r = this.wtable[widx1];
               float w1i = (float)isign * this.wtable[widx1 + 1];
               float w2r = this.wtable[widx2];
               float w2i = (float)isign * this.wtable[widx2 + 1];
               int oidx1 = i + idx2;
               int oidx2 = oidx1 + idxt;
               int oidx3 = oidx2 + idxt;
               out[oidx1] = a3r + tr2;
               out[oidx1 + 1] = a3i + ti2;
               out[oidx2] = w1r * dr2 - w1i * di2;
               out[oidx2 + 1] = w1r * di2 + w1i * dr2;
               out[oidx3] = w2r * dr3 - w2i * di3;
               out[oidx3 + 1] = w2r * di3 + w2i * dr3;
            }
         }
      }

   }

   void passf3(long ido, long l1, FloatLargeArray in, long in_off, FloatLargeArray out, long out_off, long offset, long isign) {
      float taur = -0.5F;
      float taui = 0.8660254F;
      long iw1 = offset;
      long iw2 = offset + ido;
      long idxt = l1 * ido;
      if (ido == 2L) {
         for(long k = 1L; k <= l1; ++k) {
            long iidx1 = in_off + (3L * k - 2L) * ido;
            long iidx2 = iidx1 + ido;
            long iidx3 = iidx1 - ido;
            float i1r = in.getFloat(iidx1);
            float i1i = in.getFloat(iidx1 + 1L);
            float i2r = in.getFloat(iidx2);
            float i2i = in.getFloat(iidx2 + 1L);
            float i3r = in.getFloat(iidx3);
            float i3i = in.getFloat(iidx3 + 1L);
            float tr2 = i1r + i2r;
            float cr2 = i3r + -0.5F * tr2;
            float ti2 = i1i + i2i;
            float ci2 = i3i + -0.5F * ti2;
            float cr3 = (float)isign * 0.8660254F * (i1r - i2r);
            float ci3 = (float)isign * 0.8660254F * (i1i - i2i);
            long oidx1 = out_off + (k - 1L) * ido;
            long oidx2 = oidx1 + idxt;
            long oidx3 = oidx2 + idxt;
            out.setFloat(oidx1, in.getFloat(iidx3) + tr2);
            out.setFloat(oidx1 + 1L, i3i + ti2);
            out.setFloat(oidx2, cr2 - ci3);
            out.setFloat(oidx2 + 1L, ci2 + cr3);
            out.setFloat(oidx3, cr2 + ci3);
            out.setFloat(oidx3 + 1L, ci2 - cr3);
         }
      } else {
         for(long k = 1L; k <= l1; ++k) {
            long idx1 = in_off + (3L * k - 2L) * ido;
            long idx2 = out_off + (k - 1L) * ido;

            for(long i = 0L; i < ido - 1L; i += 2L) {
               long iidx1 = i + idx1;
               long iidx2 = iidx1 + ido;
               long iidx3 = iidx1 - ido;
               float a1r = in.getFloat(iidx1);
               float a1i = in.getFloat(iidx1 + 1L);
               float a2r = in.getFloat(iidx2);
               float a2i = in.getFloat(iidx2 + 1L);
               float a3r = in.getFloat(iidx3);
               float a3i = in.getFloat(iidx3 + 1L);
               float tr2 = a1r + a2r;
               float cr2 = a3r + -0.5F * tr2;
               float ti2 = a1i + a2i;
               float ci2 = a3i + -0.5F * ti2;
               float cr3 = (float)isign * 0.8660254F * (a1r - a2r);
               float ci3 = (float)isign * 0.8660254F * (a1i - a2i);
               float dr2 = cr2 - ci3;
               float dr3 = cr2 + ci3;
               float di2 = ci2 + cr3;
               float di3 = ci2 - cr3;
               long widx1 = i + iw1;
               long widx2 = i + iw2;
               float w1r = this.wtablel.getFloat(widx1);
               float w1i = (float)isign * this.wtablel.getFloat(widx1 + 1L);
               float w2r = this.wtablel.getFloat(widx2);
               float w2i = (float)isign * this.wtablel.getFloat(widx2 + 1L);
               long oidx1 = i + idx2;
               long oidx2 = oidx1 + idxt;
               long oidx3 = oidx2 + idxt;
               out.setFloat(oidx1, a3r + tr2);
               out.setFloat(oidx1 + 1L, a3i + ti2);
               out.setFloat(oidx2, w1r * dr2 - w1i * di2);
               out.setFloat(oidx2 + 1L, w1r * di2 + w1i * dr2);
               out.setFloat(oidx3, w2r * dr3 - w2i * di3);
               out.setFloat(oidx3 + 1L, w2r * di3 + w2i * dr3);
            }
         }
      }

   }

   void passf4(int ido, int l1, float[] in, int in_off, float[] out, int out_off, int offset, int isign) {
      int iw1 = offset;
      int iw2 = offset + ido;
      int iw3 = iw2 + ido;
      int idx0 = l1 * ido;
      if (ido == 2) {
         for(int k = 0; k < l1; ++k) {
            int idxt1 = k * ido;
            int iidx1 = in_off + 4 * idxt1 + 1;
            int iidx2 = iidx1 + ido;
            int iidx3 = iidx2 + ido;
            int iidx4 = iidx3 + ido;
            float i1i = in[iidx1 - 1];
            float i1r = in[iidx1];
            float i2i = in[iidx2 - 1];
            float i2r = in[iidx2];
            float i3i = in[iidx3 - 1];
            float i3r = in[iidx3];
            float i4i = in[iidx4 - 1];
            float i4r = in[iidx4];
            float ti1 = i1r - i3r;
            float ti2 = i1r + i3r;
            float tr4 = i4r - i2r;
            float ti3 = i2r + i4r;
            float tr1 = i1i - i3i;
            float tr2 = i1i + i3i;
            float ti4 = i2i - i4i;
            float tr3 = i2i + i4i;
            int oidx1 = out_off + idxt1;
            int oidx2 = oidx1 + idx0;
            int oidx3 = oidx2 + idx0;
            int oidx4 = oidx3 + idx0;
            out[oidx1] = tr2 + tr3;
            out[oidx1 + 1] = ti2 + ti3;
            out[oidx2] = tr1 + (float)isign * tr4;
            out[oidx2 + 1] = ti1 + (float)isign * ti4;
            out[oidx3] = tr2 - tr3;
            out[oidx3 + 1] = ti2 - ti3;
            out[oidx4] = tr1 - (float)isign * tr4;
            out[oidx4 + 1] = ti1 - (float)isign * ti4;
         }
      } else {
         for(int k = 0; k < l1; ++k) {
            int idx1 = k * ido;
            int idx2 = in_off + 1 + 4 * idx1;

            for(int i = 0; i < ido - 1; i += 2) {
               int iidx1 = i + idx2;
               int iidx2 = iidx1 + ido;
               int iidx3 = iidx2 + ido;
               int iidx4 = iidx3 + ido;
               float i1i = in[iidx1 - 1];
               float i1r = in[iidx1];
               float i2i = in[iidx2 - 1];
               float i2r = in[iidx2];
               float i3i = in[iidx3 - 1];
               float i3r = in[iidx3];
               float i4i = in[iidx4 - 1];
               float i4r = in[iidx4];
               float ti1 = i1r - i3r;
               float ti2 = i1r + i3r;
               float ti3 = i2r + i4r;
               float tr4 = i4r - i2r;
               float tr1 = i1i - i3i;
               float tr2 = i1i + i3i;
               float ti4 = i2i - i4i;
               float tr3 = i2i + i4i;
               float cr3 = tr2 - tr3;
               float ci3 = ti2 - ti3;
               float cr2 = tr1 + (float)isign * tr4;
               float cr4 = tr1 - (float)isign * tr4;
               float ci2 = ti1 + (float)isign * ti4;
               float ci4 = ti1 - (float)isign * ti4;
               int widx1 = i + iw1;
               int widx2 = i + iw2;
               int widx3 = i + iw3;
               float w1r = this.wtable[widx1];
               float w1i = (float)isign * this.wtable[widx1 + 1];
               float w2r = this.wtable[widx2];
               float w2i = (float)isign * this.wtable[widx2 + 1];
               float w3r = this.wtable[widx3];
               float w3i = (float)isign * this.wtable[widx3 + 1];
               int oidx1 = out_off + i + idx1;
               int oidx2 = oidx1 + idx0;
               int oidx3 = oidx2 + idx0;
               int oidx4 = oidx3 + idx0;
               out[oidx1] = tr2 + tr3;
               out[oidx1 + 1] = ti2 + ti3;
               out[oidx2] = w1r * cr2 - w1i * ci2;
               out[oidx2 + 1] = w1r * ci2 + w1i * cr2;
               out[oidx3] = w2r * cr3 - w2i * ci3;
               out[oidx3 + 1] = w2r * ci3 + w2i * cr3;
               out[oidx4] = w3r * cr4 - w3i * ci4;
               out[oidx4 + 1] = w3r * ci4 + w3i * cr4;
            }
         }
      }

   }

   void passf4(long ido, long l1, FloatLargeArray in, long in_off, FloatLargeArray out, long out_off, long offset, int isign) {
      long iw1 = offset;
      long iw2 = offset + ido;
      long iw3 = iw2 + ido;
      long idx0 = l1 * ido;
      if (ido == 2L) {
         for(long k = 0L; k < l1; ++k) {
            long idxt1 = k * ido;
            long iidx1 = in_off + 4L * idxt1 + 1L;
            long iidx2 = iidx1 + ido;
            long iidx3 = iidx2 + ido;
            long iidx4 = iidx3 + ido;
            float i1i = in.getFloat(iidx1 - 1L);
            float i1r = in.getFloat(iidx1);
            float i2i = in.getFloat(iidx2 - 1L);
            float i2r = in.getFloat(iidx2);
            float i3i = in.getFloat(iidx3 - 1L);
            float i3r = in.getFloat(iidx3);
            float i4i = in.getFloat(iidx4 - 1L);
            float i4r = in.getFloat(iidx4);
            float ti1 = i1r - i3r;
            float ti2 = i1r + i3r;
            float tr4 = i4r - i2r;
            float ti3 = i2r + i4r;
            float tr1 = i1i - i3i;
            float tr2 = i1i + i3i;
            float ti4 = i2i - i4i;
            float tr3 = i2i + i4i;
            long oidx1 = out_off + idxt1;
            long oidx2 = oidx1 + idx0;
            long oidx3 = oidx2 + idx0;
            long oidx4 = oidx3 + idx0;
            out.setFloat(oidx1, tr2 + tr3);
            out.setFloat(oidx1 + 1L, ti2 + ti3);
            out.setFloat(oidx2, tr1 + (float)isign * tr4);
            out.setFloat(oidx2 + 1L, ti1 + (float)isign * ti4);
            out.setFloat(oidx3, tr2 - tr3);
            out.setFloat(oidx3 + 1L, ti2 - ti3);
            out.setFloat(oidx4, tr1 - (float)isign * tr4);
            out.setFloat(oidx4 + 1L, ti1 - (float)isign * ti4);
         }
      } else {
         for(long k = 0L; k < l1; ++k) {
            long idx1 = k * ido;
            long idx2 = in_off + 1L + 4L * idx1;

            for(long i = 0L; i < ido - 1L; i += 2L) {
               long iidx1 = i + idx2;
               long iidx2 = iidx1 + ido;
               long iidx3 = iidx2 + ido;
               long iidx4 = iidx3 + ido;
               float i1i = in.getFloat(iidx1 - 1L);
               float i1r = in.getFloat(iidx1);
               float i2i = in.getFloat(iidx2 - 1L);
               float i2r = in.getFloat(iidx2);
               float i3i = in.getFloat(iidx3 - 1L);
               float i3r = in.getFloat(iidx3);
               float i4i = in.getFloat(iidx4 - 1L);
               float i4r = in.getFloat(iidx4);
               float ti1 = i1r - i3r;
               float ti2 = i1r + i3r;
               float ti3 = i2r + i4r;
               float tr4 = i4r - i2r;
               float tr1 = i1i - i3i;
               float tr2 = i1i + i3i;
               float ti4 = i2i - i4i;
               float tr3 = i2i + i4i;
               float cr3 = tr2 - tr3;
               float ci3 = ti2 - ti3;
               float cr2 = tr1 + (float)isign * tr4;
               float cr4 = tr1 - (float)isign * tr4;
               float ci2 = ti1 + (float)isign * ti4;
               float ci4 = ti1 - (float)isign * ti4;
               long widx1 = i + iw1;
               long widx2 = i + iw2;
               long widx3 = i + iw3;
               float w1r = this.wtablel.getFloat(widx1);
               float w1i = (float)isign * this.wtablel.getFloat(widx1 + 1L);
               float w2r = this.wtablel.getFloat(widx2);
               float w2i = (float)isign * this.wtablel.getFloat(widx2 + 1L);
               float w3r = this.wtablel.getFloat(widx3);
               float w3i = (float)isign * this.wtablel.getFloat(widx3 + 1L);
               long oidx1 = out_off + i + idx1;
               long oidx2 = oidx1 + idx0;
               long oidx3 = oidx2 + idx0;
               long oidx4 = oidx3 + idx0;
               out.setFloat(oidx1, tr2 + tr3);
               out.setFloat(oidx1 + 1L, ti2 + ti3);
               out.setFloat(oidx2, w1r * cr2 - w1i * ci2);
               out.setFloat(oidx2 + 1L, w1r * ci2 + w1i * cr2);
               out.setFloat(oidx3, w2r * cr3 - w2i * ci3);
               out.setFloat(oidx3 + 1L, w2r * ci3 + w2i * cr3);
               out.setFloat(oidx4, w3r * cr4 - w3i * ci4);
               out.setFloat(oidx4 + 1L, w3r * ci4 + w3i * cr4);
            }
         }
      }

   }

   void passf5(int ido, int l1, float[] in, int in_off, float[] out, int out_off, int offset, int isign) {
      float tr11 = 0.309017F;
      float ti11 = 0.95105654F;
      float tr12 = -0.809017F;
      float ti12 = 0.58778524F;
      int iw1 = offset;
      int iw2 = offset + ido;
      int iw3 = iw2 + ido;
      int iw4 = iw3 + ido;
      int idx0 = l1 * ido;
      if (ido == 2) {
         for(int k = 1; k <= l1; ++k) {
            int iidx1 = in_off + (5 * k - 4) * ido + 1;
            int iidx2 = iidx1 + ido;
            int iidx3 = iidx1 - ido;
            int iidx4 = iidx2 + ido;
            int iidx5 = iidx4 + ido;
            float i1i = in[iidx1 - 1];
            float i1r = in[iidx1];
            float i2i = in[iidx2 - 1];
            float i2r = in[iidx2];
            float i3i = in[iidx3 - 1];
            float i3r = in[iidx3];
            float i4i = in[iidx4 - 1];
            float i4r = in[iidx4];
            float i5i = in[iidx5 - 1];
            float i5r = in[iidx5];
            float ti5 = i1r - i5r;
            float ti2 = i1r + i5r;
            float ti4 = i2r - i4r;
            float ti3 = i2r + i4r;
            float tr5 = i1i - i5i;
            float tr2 = i1i + i5i;
            float tr4 = i2i - i4i;
            float tr3 = i2i + i4i;
            float cr2 = i3i + 0.309017F * tr2 + -0.809017F * tr3;
            float ci2 = i3r + 0.309017F * ti2 + -0.809017F * ti3;
            float cr3 = i3i + -0.809017F * tr2 + 0.309017F * tr3;
            float ci3 = i3r + -0.809017F * ti2 + 0.309017F * ti3;
            float cr5 = (float)isign * (0.95105654F * tr5 + 0.58778524F * tr4);
            float ci5 = (float)isign * (0.95105654F * ti5 + 0.58778524F * ti4);
            float cr4 = (float)isign * (0.58778524F * tr5 - 0.95105654F * tr4);
            float ci4 = (float)isign * (0.58778524F * ti5 - 0.95105654F * ti4);
            int oidx1 = out_off + (k - 1) * ido;
            int oidx2 = oidx1 + idx0;
            int oidx3 = oidx2 + idx0;
            int oidx4 = oidx3 + idx0;
            int oidx5 = oidx4 + idx0;
            out[oidx1] = i3i + tr2 + tr3;
            out[oidx1 + 1] = i3r + ti2 + ti3;
            out[oidx2] = cr2 - ci5;
            out[oidx2 + 1] = ci2 + cr5;
            out[oidx3] = cr3 - ci4;
            out[oidx3 + 1] = ci3 + cr4;
            out[oidx4] = cr3 + ci4;
            out[oidx4 + 1] = ci3 - cr4;
            out[oidx5] = cr2 + ci5;
            out[oidx5 + 1] = ci2 - cr5;
         }
      } else {
         for(int k = 1; k <= l1; ++k) {
            int idx1 = in_off + 1 + (k * 5 - 4) * ido;
            int idx2 = out_off + (k - 1) * ido;

            for(int i = 0; i < ido - 1; i += 2) {
               int iidx1 = i + idx1;
               int iidx2 = iidx1 + ido;
               int iidx3 = iidx1 - ido;
               int iidx4 = iidx2 + ido;
               int iidx5 = iidx4 + ido;
               float i1i = in[iidx1 - 1];
               float i1r = in[iidx1];
               float i2i = in[iidx2 - 1];
               float i2r = in[iidx2];
               float i3i = in[iidx3 - 1];
               float i3r = in[iidx3];
               float i4i = in[iidx4 - 1];
               float i4r = in[iidx4];
               float i5i = in[iidx5 - 1];
               float i5r = in[iidx5];
               float ti5 = i1r - i5r;
               float ti2 = i1r + i5r;
               float ti4 = i2r - i4r;
               float ti3 = i2r + i4r;
               float tr5 = i1i - i5i;
               float tr2 = i1i + i5i;
               float tr4 = i2i - i4i;
               float tr3 = i2i + i4i;
               float cr2 = i3i + 0.309017F * tr2 + -0.809017F * tr3;
               float ci2 = i3r + 0.309017F * ti2 + -0.809017F * ti3;
               float cr3 = i3i + -0.809017F * tr2 + 0.309017F * tr3;
               float ci3 = i3r + -0.809017F * ti2 + 0.309017F * ti3;
               float cr5 = (float)isign * (0.95105654F * tr5 + 0.58778524F * tr4);
               float ci5 = (float)isign * (0.95105654F * ti5 + 0.58778524F * ti4);
               float cr4 = (float)isign * (0.58778524F * tr5 - 0.95105654F * tr4);
               float ci4 = (float)isign * (0.58778524F * ti5 - 0.95105654F * ti4);
               float dr3 = cr3 - ci4;
               float dr4 = cr3 + ci4;
               float di3 = ci3 + cr4;
               float di4 = ci3 - cr4;
               float dr5 = cr2 + ci5;
               float dr2 = cr2 - ci5;
               float di5 = ci2 - cr5;
               float di2 = ci2 + cr5;
               int widx1 = i + iw1;
               int widx2 = i + iw2;
               int widx3 = i + iw3;
               int widx4 = i + iw4;
               float w1r = this.wtable[widx1];
               float w1i = (float)isign * this.wtable[widx1 + 1];
               float w2r = this.wtable[widx2];
               float w2i = (float)isign * this.wtable[widx2 + 1];
               float w3r = this.wtable[widx3];
               float w3i = (float)isign * this.wtable[widx3 + 1];
               float w4r = this.wtable[widx4];
               float w4i = (float)isign * this.wtable[widx4 + 1];
               int oidx1 = i + idx2;
               int oidx2 = oidx1 + idx0;
               int oidx3 = oidx2 + idx0;
               int oidx4 = oidx3 + idx0;
               int oidx5 = oidx4 + idx0;
               out[oidx1] = i3i + tr2 + tr3;
               out[oidx1 + 1] = i3r + ti2 + ti3;
               out[oidx2] = w1r * dr2 - w1i * di2;
               out[oidx2 + 1] = w1r * di2 + w1i * dr2;
               out[oidx3] = w2r * dr3 - w2i * di3;
               out[oidx3 + 1] = w2r * di3 + w2i * dr3;
               out[oidx4] = w3r * dr4 - w3i * di4;
               out[oidx4 + 1] = w3r * di4 + w3i * dr4;
               out[oidx5] = w4r * dr5 - w4i * di5;
               out[oidx5 + 1] = w4r * di5 + w4i * dr5;
            }
         }
      }

   }

   void passf5(long ido, long l1, FloatLargeArray in, long in_off, FloatLargeArray out, long out_off, long offset, long isign) {
      float tr11 = 0.309017F;
      float ti11 = 0.95105654F;
      float tr12 = -0.809017F;
      float ti12 = 0.58778524F;
      long iw1 = offset;
      long iw2 = offset + ido;
      long iw3 = iw2 + ido;
      long iw4 = iw3 + ido;
      long idx0 = l1 * ido;
      if (ido == 2L) {
         for(long k = 1L; k <= l1; ++k) {
            long iidx1 = in_off + (5L * k - 4L) * ido + 1L;
            long iidx2 = iidx1 + ido;
            long iidx3 = iidx1 - ido;
            long iidx4 = iidx2 + ido;
            long iidx5 = iidx4 + ido;
            float i1i = in.getFloat(iidx1 - 1L);
            float i1r = in.getFloat(iidx1);
            float i2i = in.getFloat(iidx2 - 1L);
            float i2r = in.getFloat(iidx2);
            float i3i = in.getFloat(iidx3 - 1L);
            float i3r = in.getFloat(iidx3);
            float i4i = in.getFloat(iidx4 - 1L);
            float i4r = in.getFloat(iidx4);
            float i5i = in.getFloat(iidx5 - 1L);
            float i5r = in.getFloat(iidx5);
            float ti5 = i1r - i5r;
            float ti2 = i1r + i5r;
            float ti4 = i2r - i4r;
            float ti3 = i2r + i4r;
            float tr5 = i1i - i5i;
            float tr2 = i1i + i5i;
            float tr4 = i2i - i4i;
            float tr3 = i2i + i4i;
            float cr2 = i3i + 0.309017F * tr2 + -0.809017F * tr3;
            float ci2 = i3r + 0.309017F * ti2 + -0.809017F * ti3;
            float cr3 = i3i + -0.809017F * tr2 + 0.309017F * tr3;
            float ci3 = i3r + -0.809017F * ti2 + 0.309017F * ti3;
            float cr5 = (float)isign * (0.95105654F * tr5 + 0.58778524F * tr4);
            float ci5 = (float)isign * (0.95105654F * ti5 + 0.58778524F * ti4);
            float cr4 = (float)isign * (0.58778524F * tr5 - 0.95105654F * tr4);
            float ci4 = (float)isign * (0.58778524F * ti5 - 0.95105654F * ti4);
            long oidx1 = out_off + (k - 1L) * ido;
            long oidx2 = oidx1 + idx0;
            long oidx3 = oidx2 + idx0;
            long oidx4 = oidx3 + idx0;
            long oidx5 = oidx4 + idx0;
            out.setFloat(oidx1, i3i + tr2 + tr3);
            out.setFloat(oidx1 + 1L, i3r + ti2 + ti3);
            out.setFloat(oidx2, cr2 - ci5);
            out.setFloat(oidx2 + 1L, ci2 + cr5);
            out.setFloat(oidx3, cr3 - ci4);
            out.setFloat(oidx3 + 1L, ci3 + cr4);
            out.setFloat(oidx4, cr3 + ci4);
            out.setFloat(oidx4 + 1L, ci3 - cr4);
            out.setFloat(oidx5, cr2 + ci5);
            out.setFloat(oidx5 + 1L, ci2 - cr5);
         }
      } else {
         for(long k = 1L; k <= l1; ++k) {
            long idx1 = in_off + 1L + (k * 5L - 4L) * ido;
            long idx2 = out_off + (k - 1L) * ido;

            for(long i = 0L; i < ido - 1L; i += 2L) {
               long iidx1 = i + idx1;
               long iidx2 = iidx1 + ido;
               long iidx3 = iidx1 - ido;
               long iidx4 = iidx2 + ido;
               long iidx5 = iidx4 + ido;
               float i1i = in.getFloat(iidx1 - 1L);
               float i1r = in.getFloat(iidx1);
               float i2i = in.getFloat(iidx2 - 1L);
               float i2r = in.getFloat(iidx2);
               float i3i = in.getFloat(iidx3 - 1L);
               float i3r = in.getFloat(iidx3);
               float i4i = in.getFloat(iidx4 - 1L);
               float i4r = in.getFloat(iidx4);
               float i5i = in.getFloat(iidx5 - 1L);
               float i5r = in.getFloat(iidx5);
               float ti5 = i1r - i5r;
               float ti2 = i1r + i5r;
               float ti4 = i2r - i4r;
               float ti3 = i2r + i4r;
               float tr5 = i1i - i5i;
               float tr2 = i1i + i5i;
               float tr4 = i2i - i4i;
               float tr3 = i2i + i4i;
               float cr2 = i3i + 0.309017F * tr2 + -0.809017F * tr3;
               float ci2 = i3r + 0.309017F * ti2 + -0.809017F * ti3;
               float cr3 = i3i + -0.809017F * tr2 + 0.309017F * tr3;
               float ci3 = i3r + -0.809017F * ti2 + 0.309017F * ti3;
               float cr5 = (float)isign * (0.95105654F * tr5 + 0.58778524F * tr4);
               float ci5 = (float)isign * (0.95105654F * ti5 + 0.58778524F * ti4);
               float cr4 = (float)isign * (0.58778524F * tr5 - 0.95105654F * tr4);
               float ci4 = (float)isign * (0.58778524F * ti5 - 0.95105654F * ti4);
               float dr3 = cr3 - ci4;
               float dr4 = cr3 + ci4;
               float di3 = ci3 + cr4;
               float di4 = ci3 - cr4;
               float dr5 = cr2 + ci5;
               float dr2 = cr2 - ci5;
               float di5 = ci2 - cr5;
               float di2 = ci2 + cr5;
               long widx1 = i + iw1;
               long widx2 = i + iw2;
               long widx3 = i + iw3;
               long widx4 = i + iw4;
               float w1r = this.wtablel.getFloat(widx1);
               float w1i = (float)isign * this.wtablel.getFloat(widx1 + 1L);
               float w2r = this.wtablel.getFloat(widx2);
               float w2i = (float)isign * this.wtablel.getFloat(widx2 + 1L);
               float w3r = this.wtablel.getFloat(widx3);
               float w3i = (float)isign * this.wtablel.getFloat(widx3 + 1L);
               float w4r = this.wtablel.getFloat(widx4);
               float w4i = (float)isign * this.wtablel.getFloat(widx4 + 1L);
               long oidx1 = i + idx2;
               long oidx2 = oidx1 + idx0;
               long oidx3 = oidx2 + idx0;
               long oidx4 = oidx3 + idx0;
               long oidx5 = oidx4 + idx0;
               out.setFloat(oidx1, i3i + tr2 + tr3);
               out.setFloat(oidx1 + 1L, i3r + ti2 + ti3);
               out.setFloat(oidx2, w1r * dr2 - w1i * di2);
               out.setFloat(oidx2 + 1L, w1r * di2 + w1i * dr2);
               out.setFloat(oidx3, w2r * dr3 - w2i * di3);
               out.setFloat(oidx3 + 1L, w2r * di3 + w2i * dr3);
               out.setFloat(oidx4, w3r * dr4 - w3i * di4);
               out.setFloat(oidx4 + 1L, w3r * di4 + w3i * dr4);
               out.setFloat(oidx5, w4r * dr5 - w4i * di5);
               out.setFloat(oidx5 + 1L, w4r * di5 + w4i * dr5);
            }
         }
      }

   }

   void passfg(int[] nac, int ido, int ip, int l1, int idl1, float[] in, int in_off, float[] out, int out_off, int offset, int isign) {
      int iw1 = offset;
      int idot = ido / 2;
      int ipph = (ip + 1) / 2;
      int idp = ip * ido;
      if (ido >= l1) {
         for(int j = 1; j < ipph; ++j) {
            int jc = ip - j;
            int idx1 = j * ido;
            int idx2 = jc * ido;

            for(int k = 0; k < l1; ++k) {
               int idx3 = k * ido;
               int idx4 = idx3 + idx1 * l1;
               int idx5 = idx3 + idx2 * l1;
               int idx6 = idx3 * ip;

               for(int i = 0; i < ido; ++i) {
                  int oidx1 = out_off + i;
                  float i1r = in[in_off + i + idx1 + idx6];
                  float i2r = in[in_off + i + idx2 + idx6];
                  out[oidx1 + idx4] = i1r + i2r;
                  out[oidx1 + idx5] = i1r - i2r;
               }
            }
         }

         for(int k = 0; k < l1; ++k) {
            int idxt1 = k * ido;
            int idxt2 = idxt1 * ip;

            for(int i = 0; i < ido; ++i) {
               out[out_off + i + idxt1] = in[in_off + i + idxt2];
            }
         }
      } else {
         for(int j = 1; j < ipph; ++j) {
            int jc = ip - j;
            int idxt1 = j * l1 * ido;
            int idxt2 = jc * l1 * ido;
            int idxt3 = j * ido;
            int idxt4 = jc * ido;

            for(int i = 0; i < ido; ++i) {
               for(int k = 0; k < l1; ++k) {
                  int idx1 = k * ido;
                  int idx2 = idx1 * ip;
                  int idx3 = out_off + i;
                  int idx4 = in_off + i;
                  float i1r = in[idx4 + idxt3 + idx2];
                  float i2r = in[idx4 + idxt4 + idx2];
                  out[idx3 + idx1 + idxt1] = i1r + i2r;
                  out[idx3 + idx1 + idxt2] = i1r - i2r;
               }
            }
         }

         for(int i = 0; i < ido; ++i) {
            for(int k = 0; k < l1; ++k) {
               int idx1 = k * ido;
               out[out_off + i + idx1] = in[in_off + i + idx1 * ip];
            }
         }
      }

      int idl = 2 - ido;
      int inc = 0;
      int idxt0 = (ip - 1) * idl1;

      for(int l = 1; l < ipph; ++l) {
         int lc = ip - l;
         idl += ido;
         int idxt1 = l * idl1;
         int idxt2 = lc * idl1;
         int idxt3 = idl + iw1;
         float w1r = this.wtable[idxt3 - 2];
         float w1i = (float)isign * this.wtable[idxt3 - 1];

         for(int ik = 0; ik < idl1; ++ik) {
            int idx1 = in_off + ik;
            int idx2 = out_off + ik;
            in[idx1 + idxt1] = out[idx2] + w1r * out[idx2 + idl1];
            in[idx1 + idxt2] = w1i * out[idx2 + idxt0];
         }

         int idlj = idl;
         inc += ido;

         for(int j = 2; j < ipph; ++j) {
            int jc = ip - j;
            idlj += inc;
            if (idlj > idp) {
               idlj -= idp;
            }

            int idxt4 = idlj + iw1;
            float w2r = this.wtable[idxt4 - 2];
            float w2i = (float)isign * this.wtable[idxt4 - 1];
            int idxt5 = j * idl1;
            int idxt6 = jc * idl1;

            for(int ik = 0; ik < idl1; ++ik) {
               int idx1 = in_off + ik;
               int idx2 = out_off + ik;
               in[idx1 + idxt1] += w2r * out[idx2 + idxt5];
               in[idx1 + idxt2] += w2i * out[idx2 + idxt6];
            }
         }
      }

      for(int j = 1; j < ipph; ++j) {
         int idxt1 = j * idl1;

         for(int ik = 0; ik < idl1; ++ik) {
            int idx1 = out_off + ik;
            out[idx1] += out[idx1 + idxt1];
         }
      }

      for(int j = 1; j < ipph; ++j) {
         int jc = ip - j;
         int idx1 = j * idl1;
         int idx2 = jc * idl1;

         for(int ik = 1; ik < idl1; ik += 2) {
            int idx3 = out_off + ik;
            int idx4 = in_off + ik;
            int iidx1 = idx4 + idx1;
            int iidx2 = idx4 + idx2;
            float i1i = in[iidx1 - 1];
            float i1r = in[iidx1];
            float i2i = in[iidx2 - 1];
            float i2r = in[iidx2];
            int oidx1 = idx3 + idx1;
            int oidx2 = idx3 + idx2;
            out[oidx1 - 1] = i1i - i2r;
            out[oidx2 - 1] = i1i + i2r;
            out[oidx1] = i1r + i2i;
            out[oidx2] = i1r - i2i;
         }
      }

      nac[0] = 1;
      if (ido != 2) {
         nac[0] = 0;
         System.arraycopy(out, out_off, in, in_off, idl1);
         int idx0 = l1 * ido;

         for(int j = 1; j < ip; ++j) {
            int idx1 = j * idx0;

            for(int k = 0; k < l1; ++k) {
               int idx2 = k * ido;
               int oidx1 = out_off + idx2 + idx1;
               int iidx1 = in_off + idx2 + idx1;
               in[iidx1] = out[oidx1];
               in[iidx1 + 1] = out[oidx1 + 1];
            }
         }

         if (idot <= l1) {
            int idij = 0;

            for(int j = 1; j < ip; ++j) {
               idij += 2;
               int idx1 = j * l1 * ido;

               for(int i = 3; i < ido; i += 2) {
                  idij += 2;
                  int idx2 = idij + iw1 - 1;
                  float w1r = this.wtable[idx2 - 1];
                  float w1i = (float)isign * this.wtable[idx2];
                  int idx3 = in_off + i;
                  int idx4 = out_off + i;

                  for(int k = 0; k < l1; ++k) {
                     int idx5 = k * ido + idx1;
                     int iidx1 = idx3 + idx5;
                     int oidx1 = idx4 + idx5;
                     float o1i = out[oidx1 - 1];
                     float o1r = out[oidx1];
                     in[iidx1 - 1] = w1r * o1i - w1i * o1r;
                     in[iidx1] = w1r * o1r + w1i * o1i;
                  }
               }
            }
         } else {
            int idj = 2 - ido;

            for(int j = 1; j < ip; ++j) {
               idj += ido;
               int idx1 = j * l1 * ido;

               for(int k = 0; k < l1; ++k) {
                  int idij = idj;
                  int idx3 = k * ido + idx1;

                  for(int i = 3; i < ido; i += 2) {
                     idij += 2;
                     int idx2 = idij - 1 + iw1;
                     float w1r = this.wtable[idx2 - 1];
                     float w1i = (float)isign * this.wtable[idx2];
                     int iidx1 = in_off + i + idx3;
                     int oidx1 = out_off + i + idx3;
                     float o1i = out[oidx1 - 1];
                     float o1r = out[oidx1];
                     in[iidx1 - 1] = w1r * o1i - w1i * o1r;
                     in[iidx1] = w1r * o1r + w1i * o1i;
                  }
               }
            }
         }

      }
   }

   void passfg(int[] nac, long ido, long ip, long l1, long idl1, FloatLargeArray in, long in_off, FloatLargeArray out, long out_off, long offset, long isign) {
      long iw1 = offset;
      long idot = ido / 2L;
      long ipph = (ip + 1L) / 2L;
      long idp = ip * ido;
      if (ido >= l1) {
         for(long j = 1L; j < ipph; ++j) {
            long jc = ip - j;
            long idx1 = j * ido;
            long idx2 = jc * ido;

            for(long k = 0L; k < l1; ++k) {
               long idx3 = k * ido;
               long idx4 = idx3 + idx1 * l1;
               long idx5 = idx3 + idx2 * l1;
               long idx6 = idx3 * ip;

               for(long i = 0L; i < ido; ++i) {
                  long oidx1 = out_off + i;
                  float i1r = in.getFloat(in_off + i + idx1 + idx6);
                  float i2r = in.getFloat(in_off + i + idx2 + idx6);
                  out.setFloat(oidx1 + idx4, i1r + i2r);
                  out.setFloat(oidx1 + idx5, i1r - i2r);
               }
            }
         }

         for(long k = 0L; k < l1; ++k) {
            long idxt1 = k * ido;
            long idxt2 = idxt1 * ip;

            for(long i = 0L; i < ido; ++i) {
               out.setFloat(out_off + i + idxt1, in.getFloat(in_off + i + idxt2));
            }
         }
      } else {
         for(long j = 1L; j < ipph; ++j) {
            long jc = ip - j;
            long idxt1 = j * l1 * ido;
            long idxt2 = jc * l1 * ido;
            long idxt3 = j * ido;
            long idxt4 = jc * ido;

            for(long i = 0L; i < ido; ++i) {
               for(long k = 0L; k < l1; ++k) {
                  long idx1 = k * ido;
                  long idx2 = idx1 * ip;
                  long idx3 = out_off + i;
                  long idx4 = in_off + i;
                  float i1r = in.getFloat(idx4 + idxt3 + idx2);
                  float i2r = in.getFloat(idx4 + idxt4 + idx2);
                  out.setFloat(idx3 + idx1 + idxt1, i1r + i2r);
                  out.setFloat(idx3 + idx1 + idxt2, i1r - i2r);
               }
            }
         }

         for(long i = 0L; i < ido; ++i) {
            for(long k = 0L; k < l1; ++k) {
               long idx1 = k * ido;
               out.setFloat(out_off + i + idx1, in.getFloat(in_off + i + idx1 * ip));
            }
         }
      }

      long idl = 2L - ido;
      long inc = 0L;
      long idxt0 = (ip - 1L) * idl1;

      for(long l = 1L; l < ipph; ++l) {
         long lc = ip - l;
         idl += ido;
         long idxt1 = l * idl1;
         long idxt2 = lc * idl1;
         long idxt3 = idl + iw1;
         float w1r = this.wtablel.getFloat(idxt3 - 2L);
         float w1i = (float)isign * this.wtablel.getFloat(idxt3 - 1L);

         for(long ik = 0L; ik < idl1; ++ik) {
            long idx1 = in_off + ik;
            long idx2 = out_off + ik;
            in.setFloat(idx1 + idxt1, out.getFloat(idx2) + w1r * out.getFloat(idx2 + idl1));
            in.setFloat(idx1 + idxt2, w1i * out.getFloat(idx2 + idxt0));
         }

         long idlj = idl;
         inc += ido;

         for(long j = 2L; j < ipph; ++j) {
            long jc = ip - j;
            idlj += inc;
            if (idlj > idp) {
               idlj -= idp;
            }

            long idxt4 = idlj + iw1;
            float w2r = this.wtablel.getFloat(idxt4 - 2L);
            float w2i = (float)isign * this.wtablel.getFloat(idxt4 - 1L);
            long idxt5 = j * idl1;
            long idxt6 = jc * idl1;

            for(long ik = 0L; ik < idl1; ++ik) {
               long idx1 = in_off + ik;
               long idx2 = out_off + ik;
               in.setFloat(idx1 + idxt1, in.getFloat(idx1 + idxt1) + w2r * out.getFloat(idx2 + idxt5));
               in.setFloat(idx1 + idxt2, in.getFloat(idx1 + idxt2) + w2i * out.getFloat(idx2 + idxt6));
            }
         }
      }

      for(long j = 1L; j < ipph; ++j) {
         long idxt1 = j * idl1;

         for(long ik = 0L; ik < idl1; ++ik) {
            long idx1 = out_off + ik;
            out.setFloat(idx1, out.getFloat(idx1) + out.getFloat(idx1 + idxt1));
         }
      }

      for(long j = 1L; j < ipph; ++j) {
         long jc = ip - j;
         long idx1 = j * idl1;
         long idx2 = jc * idl1;

         for(long ik = 1L; ik < idl1; ik += 2L) {
            long idx3 = out_off + ik;
            long idx4 = in_off + ik;
            long iidx1 = idx4 + idx1;
            long iidx2 = idx4 + idx2;
            float i1i = in.getFloat(iidx1 - 1L);
            float i1r = in.getFloat(iidx1);
            float i2i = in.getFloat(iidx2 - 1L);
            float i2r = in.getFloat(iidx2);
            long oidx1 = idx3 + idx1;
            long oidx2 = idx3 + idx2;
            out.setFloat(oidx1 - 1L, i1i - i2r);
            out.setFloat(oidx2 - 1L, i1i + i2r);
            out.setFloat(oidx1, i1r + i2i);
            out.setFloat(oidx2, i1r - i2i);
         }
      }

      nac[0] = 1;
      if (ido != 2L) {
         nac[0] = 0;
         LargeArrayUtils.arraycopy(out, out_off, in, in_off, idl1);
         long idx0 = l1 * ido;

         for(long j = 1L; j < ip; ++j) {
            long idx1 = j * idx0;

            for(long k = 0L; k < l1; ++k) {
               long idx2 = k * ido;
               long oidx1 = out_off + idx2 + idx1;
               long iidx1 = in_off + idx2 + idx1;
               in.setFloat(iidx1, out.getFloat(oidx1));
               in.setFloat(iidx1 + 1L, out.getFloat(oidx1 + 1L));
            }
         }

         if (idot <= l1) {
            long idij = 0L;

            for(long j = 1L; j < ip; ++j) {
               idij += 2L;
               long idx1 = j * l1 * ido;

               for(long i = 3L; i < ido; i += 2L) {
                  idij += 2L;
                  long idx2 = idij + iw1 - 1L;
                  float w1r = this.wtablel.getFloat(idx2 - 1L);
                  float w1i = (float)isign * this.wtablel.getFloat(idx2);
                  long idx3 = in_off + i;
                  long idx4 = out_off + i;

                  for(long k = 0L; k < l1; ++k) {
                     long idx5 = k * ido + idx1;
                     long iidx1 = idx3 + idx5;
                     long oidx1 = idx4 + idx5;
                     float o1i = out.getFloat(oidx1 - 1L);
                     float o1r = out.getFloat(oidx1);
                     in.setFloat(iidx1 - 1L, w1r * o1i - w1i * o1r);
                     in.setFloat(iidx1, w1r * o1r + w1i * o1i);
                  }
               }
            }
         } else {
            long idj = 2L - ido;

            for(long j = 1L; j < ip; ++j) {
               idj += ido;
               long idx1 = j * l1 * ido;

               for(long k = 0L; k < l1; ++k) {
                  long idij = idj;
                  long idx3 = k * ido + idx1;

                  for(long i = 3L; i < ido; i += 2L) {
                     idij += 2L;
                     long idx2 = idij - 1L + iw1;
                     float w1r = this.wtablel.getFloat(idx2 - 1L);
                     float w1i = (float)isign * this.wtablel.getFloat(idx2);
                     long iidx1 = in_off + i + idx3;
                     long oidx1 = out_off + i + idx3;
                     float o1i = out.getFloat(oidx1 - 1L);
                     float o1r = out.getFloat(oidx1);
                     in.setFloat(iidx1 - 1L, w1r * o1i - w1i * o1r);
                     in.setFloat(iidx1, w1r * o1r + w1i * o1i);
                  }
               }
            }
         }

      }
   }

   private static enum Plans {
      SPLIT_RADIX,
      MIXED_RADIX,
      BLUESTEIN;
   }
}
