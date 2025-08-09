package org.jtransforms.utils;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.math3.util.FastMath;
import pl.edu.icm.jlargearrays.ConcurrencyUtils;
import pl.edu.icm.jlargearrays.DoubleLargeArray;
import pl.edu.icm.jlargearrays.FloatLargeArray;
import pl.edu.icm.jlargearrays.LongLargeArray;

public class CommonUtils {
   private static long THREADS_BEGIN_N_1D_FFT_2THREADS = 8192L;
   private static long THREADS_BEGIN_N_1D_FFT_4THREADS = 65536L;
   private static long THREADS_BEGIN_N_2D = 65536L;
   private static long THREADS_BEGIN_N_3D = 65536L;
   private static boolean useLargeArrays = false;

   public static void sleep(long millis) {
      try {
         Thread.sleep(5000L);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }

   }

   public static long getThreadsBeginN_1D_FFT_2Threads() {
      return THREADS_BEGIN_N_1D_FFT_2THREADS;
   }

   public static long getThreadsBeginN_1D_FFT_4Threads() {
      return THREADS_BEGIN_N_1D_FFT_4THREADS;
   }

   public static long getThreadsBeginN_2D() {
      return THREADS_BEGIN_N_2D;
   }

   public static long getThreadsBeginN_3D() {
      return THREADS_BEGIN_N_3D;
   }

   public static void setThreadsBeginN_1D_FFT_2Threads(long n) {
      if (n < 1024L) {
         THREADS_BEGIN_N_1D_FFT_2THREADS = 1024L;
      } else {
         THREADS_BEGIN_N_1D_FFT_2THREADS = n;
      }

   }

   public static void setThreadsBeginN_1D_FFT_4Threads(long n) {
      if (n < 1024L) {
         THREADS_BEGIN_N_1D_FFT_4THREADS = 1024L;
      } else {
         THREADS_BEGIN_N_1D_FFT_4THREADS = n;
      }

   }

   public static void setThreadsBeginN_2D(long n) {
      if (n < 4096L) {
         THREADS_BEGIN_N_2D = 4096L;
      } else {
         THREADS_BEGIN_N_2D = n;
      }

   }

   public static void setThreadsBeginN_3D(long n) {
      THREADS_BEGIN_N_3D = n;
   }

   public static void resetThreadsBeginN_FFT() {
      THREADS_BEGIN_N_1D_FFT_2THREADS = 8192L;
      THREADS_BEGIN_N_1D_FFT_4THREADS = 65536L;
   }

   public static void resetThreadsBeginN() {
      THREADS_BEGIN_N_2D = 65536L;
      THREADS_BEGIN_N_3D = 65536L;
   }

   public static boolean isUseLargeArrays() {
      return useLargeArrays;
   }

   public static void setUseLargeArrays(boolean useLargeArrays) {
      CommonUtils.useLargeArrays = useLargeArrays;
   }

   public static int nextPow2(int x) {
      if (x < 1) {
         throw new IllegalArgumentException("x must be greater or equal 1");
      } else if ((x & x - 1) == 0) {
         return x;
      } else {
         x |= x >>> 1;
         x |= x >>> 2;
         x |= x >>> 4;
         x |= x >>> 8;
         x |= x >>> 16;
         return x + 1;
      }
   }

   public static long nextPow2(long x) {
      if (x < 1L) {
         throw new IllegalArgumentException("x must be greater or equal 1");
      } else if ((x & x - 1L) == 0L) {
         return x;
      } else {
         x |= x >>> 1;
         x |= x >>> 2;
         x |= x >>> 4;
         x |= x >>> 8;
         x |= x >>> 16;
         x |= x >>> 32;
         return x + 1L;
      }
   }

   public static int prevPow2(int x) {
      if (x < 1) {
         throw new IllegalArgumentException("x must be greater or equal 1");
      } else {
         return (int)FastMath.pow((double)2.0F, FastMath.floor(FastMath.log((double)x) / FastMath.log((double)2.0F)));
      }
   }

   public static long prevPow2(long x) {
      if (x < 1L) {
         throw new IllegalArgumentException("x must be greater or equal 1");
      } else {
         return (long)FastMath.pow((double)2.0F, FastMath.floor(FastMath.log((double)x) / FastMath.log((double)2.0F)));
      }
   }

   public static boolean isPowerOf2(int x) {
      if (x <= 0) {
         return false;
      } else {
         return (x & x - 1) == 0;
      }
   }

   public static boolean isPowerOf2(long x) {
      if (x <= 0L) {
         return false;
      } else {
         return (x & x - 1L) == 0L;
      }
   }

   public static long getReminder(long n, int[] factors) {
      long reminder = n;
      if (n <= 0L) {
         throw new IllegalArgumentException("n must be positive integer");
      } else {
         for(int i = 0; i < factors.length && reminder != 1L; ++i) {
            for(long factor = (long)factors[i]; reminder % factor == 0L; reminder /= factor) {
            }
         }

         return reminder;
      }
   }

   public static void makeipt(int nw, int[] ip) {
      ip[2] = 0;
      ip[3] = 16;
      int m = 2;

      for(int l = nw; l > 32; l >>= 2) {
         int m2 = m << 1;
         int q = m2 << 3;

         for(int j = m; j < m2; ++j) {
            int p = ip[j] << 2;
            ip[m + j] = p;
            ip[m2 + j] = p + q;
         }

         m = m2;
      }

   }

   public static void makeipt(long nw, LongLargeArray ipl) {
      ipl.setLong(2L, 0L);
      ipl.setLong(3L, 16L);
      long m = 2L;

      for(long l = nw; l > 32L; l >>= 2) {
         long m2 = m << 1;
         long q = m2 << 3;

         for(long j = m; j < m2; ++j) {
            long p = ipl.getLong(j) << 2;
            ipl.setLong(m + j, p);
            ipl.setLong(m2 + j, p + q);
         }

         m = m2;
      }

   }

   public static void makewt(int nw, int[] ip, double[] w) {
      ip[0] = nw;
      ip[1] = 1;
      if (nw > 2) {
         int nwh = nw >> 1;
         double delta = (Math.PI / 4D) / (double)nwh;
         double delta2 = delta * (double)2.0F;
         double wn4r = FastMath.cos(delta * (double)nwh);
         w[0] = (double)1.0F;
         w[1] = wn4r;
         if (nwh == 4) {
            w[2] = FastMath.cos(delta2);
            w[3] = FastMath.sin(delta2);
         } else if (nwh > 4) {
            makeipt(nw, ip);
            w[2] = (double)0.5F / FastMath.cos(delta2);
            w[3] = (double)0.5F / FastMath.cos(delta * (double)6.0F);

            for(int j = 4; j < nwh; j += 4) {
               double deltaj = delta * (double)j;
               double deltaj3 = (double)3.0F * deltaj;
               w[j] = FastMath.cos(deltaj);
               w[j + 1] = FastMath.sin(deltaj);
               w[j + 2] = FastMath.cos(deltaj3);
               w[j + 3] = -FastMath.sin(deltaj3);
            }
         }

         int nw1;
         for(int nw0 = 0; nwh > 2; nw0 = nw1) {
            nw1 = nw0 + nwh;
            nwh >>= 1;
            w[nw1] = (double)1.0F;
            w[nw1 + 1] = wn4r;
            if (nwh == 4) {
               double wk1r = w[nw0 + 4];
               double wk1i = w[nw0 + 5];
               w[nw1 + 2] = wk1r;
               w[nw1 + 3] = wk1i;
            } else if (nwh > 4) {
               double wk1r = w[nw0 + 4];
               double wk3r = w[nw0 + 6];
               w[nw1 + 2] = (double)0.5F / wk1r;
               w[nw1 + 3] = (double)0.5F / wk3r;

               for(int j = 4; j < nwh; j += 4) {
                  int idx1 = nw0 + 2 * j;
                  int idx2 = nw1 + j;
                  wk1r = w[idx1];
                  double wk1i = w[idx1 + 1];
                  wk3r = w[idx1 + 2];
                  double wk3i = w[idx1 + 3];
                  w[idx2] = wk1r;
                  w[idx2 + 1] = wk1i;
                  w[idx2 + 2] = wk3r;
                  w[idx2 + 3] = wk3i;
               }
            }
         }
      }

   }

   public static void makewt(long nw, LongLargeArray ipl, DoubleLargeArray wl) {
      ipl.setLong(0L, nw);
      ipl.setLong(1L, 1L);
      if (nw > 2L) {
         long nwh = nw >> 1;
         double delta = (Math.PI / 4D) / (double)nwh;
         double delta2 = delta * (double)2.0F;
         double wn4r = FastMath.cos(delta * (double)nwh);
         wl.setDouble(0L, (double)1.0F);
         wl.setDouble(1L, wn4r);
         if (nwh == 4L) {
            wl.setDouble(2L, FastMath.cos(delta2));
            wl.setDouble(3L, FastMath.sin(delta2));
         } else if (nwh > 4L) {
            makeipt(nw, ipl);
            wl.setDouble(2L, (double)0.5F / FastMath.cos(delta2));
            wl.setDouble(3L, (double)0.5F / FastMath.cos(delta * (double)6.0F));

            for(long j = 4L; j < nwh; j += 4L) {
               double deltaj = delta * (double)j;
               double deltaj3 = (double)3.0F * deltaj;
               wl.setDouble(j, FastMath.cos(deltaj));
               wl.setDouble(j + 1L, FastMath.sin(deltaj));
               wl.setDouble(j + 2L, FastMath.cos(deltaj3));
               wl.setDouble(j + 3L, -FastMath.sin(deltaj3));
            }
         }

         long nw1;
         for(long nw0 = 0L; nwh > 2L; nw0 = nw1) {
            nw1 = nw0 + nwh;
            nwh >>= 1;
            wl.setDouble(nw1, (double)1.0F);
            wl.setDouble(nw1 + 1L, wn4r);
            if (nwh == 4L) {
               double wk1r = wl.getDouble(nw0 + 4L);
               double wk1i = wl.getDouble(nw0 + 5L);
               wl.setDouble(nw1 + 2L, wk1r);
               wl.setDouble(nw1 + 3L, wk1i);
            } else if (nwh > 4L) {
               double wk1r = wl.getDouble(nw0 + 4L);
               double wk3r = wl.getDouble(nw0 + 6L);
               wl.setDouble(nw1 + 2L, (double)0.5F / wk1r);
               wl.setDouble(nw1 + 3L, (double)0.5F / wk3r);

               for(long j = 4L; j < nwh; j += 4L) {
                  long idx1 = nw0 + 2L * j;
                  long idx2 = nw1 + j;
                  wk1r = wl.getDouble(idx1);
                  double wk1i = wl.getDouble(idx1 + 1L);
                  wk3r = wl.getDouble(idx1 + 2L);
                  double wk3i = wl.getDouble(idx1 + 3L);
                  wl.setDouble(idx2, wk1r);
                  wl.setDouble(idx2 + 1L, wk1i);
                  wl.setDouble(idx2 + 2L, wk3r);
                  wl.setDouble(idx2 + 3L, wk3i);
               }
            }
         }
      }

   }

   public static void makect(int nc, double[] c, int startc, int[] ip) {
      ip[1] = nc;
      if (nc > 1) {
         int nch = nc >> 1;
         double delta = (Math.PI / 4D) / (double)nch;
         c[startc] = FastMath.cos(delta * (double)nch);
         c[startc + nch] = (double)0.5F * c[startc];

         for(int j = 1; j < nch; ++j) {
            double deltaj = delta * (double)j;
            c[startc + j] = (double)0.5F * FastMath.cos(deltaj);
            c[startc + nc - j] = (double)0.5F * FastMath.sin(deltaj);
         }
      }

   }

   public static void makect(long nc, DoubleLargeArray c, long startc, LongLargeArray ipl) {
      ipl.setLong(1L, nc);
      if (nc > 1L) {
         long nch = nc >> 1;
         double delta = (Math.PI / 4D) / (double)nch;
         c.setDouble(startc, FastMath.cos(delta * (double)nch));
         c.setDouble(startc + nch, (double)0.5F * c.getDouble(startc));

         for(long j = 1L; j < nch; ++j) {
            double deltaj = delta * (double)j;
            c.setDouble(startc + j, (double)0.5F * FastMath.cos(deltaj));
            c.setDouble(startc + nc - j, (double)0.5F * FastMath.sin(deltaj));
         }
      }

   }

   public static void makect(int nc, float[] c, int startc, int[] ip) {
      ip[1] = nc;
      if (nc > 1) {
         int nch = nc >> 1;
         float delta = ((float)Math.PI / 4F) / (float)nch;
         c[startc] = (float)FastMath.cos((double)(delta * (float)nch));
         c[startc + nch] = 0.5F * c[startc];

         for(int j = 1; j < nch; ++j) {
            float deltaj = delta * (float)j;
            c[startc + j] = 0.5F * (float)FastMath.cos((double)deltaj);
            c[startc + nc - j] = 0.5F * (float)FastMath.sin((double)deltaj);
         }
      }

   }

   public static void makect(long nc, FloatLargeArray c, long startc, LongLargeArray ipl) {
      ipl.setLong(1L, nc);
      if (nc > 1L) {
         long nch = nc >> 1;
         float delta = ((float)Math.PI / 4F) / (float)nch;
         c.setFloat(startc, (float)FastMath.cos((double)(delta * (float)nch)));
         c.setFloat(startc + nch, 0.5F * c.getFloat(startc));

         for(long j = 1L; j < nch; ++j) {
            float deltaj = delta * (float)j;
            c.setFloat(startc + j, 0.5F * (float)FastMath.cos((double)deltaj));
            c.setFloat(startc + nc - j, 0.5F * (float)FastMath.sin((double)deltaj));
         }
      }

   }

   public static void makewt(int nw, int[] ip, float[] w) {
      ip[0] = nw;
      ip[1] = 1;
      if (nw > 2) {
         int nwh = nw >> 1;
         float delta = ((float)Math.PI / 4F) / (float)nwh;
         float delta2 = delta * 2.0F;
         float wn4r = (float)FastMath.cos((double)(delta * (float)nwh));
         w[0] = 1.0F;
         w[1] = wn4r;
         if (nwh == 4) {
            w[2] = (float)FastMath.cos((double)delta2);
            w[3] = (float)FastMath.sin((double)delta2);
         } else if (nwh > 4) {
            makeipt(nw, ip);
            w[2] = 0.5F / (float)FastMath.cos((double)delta2);
            w[3] = 0.5F / (float)FastMath.cos((double)(delta * 6.0F));

            for(int j = 4; j < nwh; j += 4) {
               float deltaj = delta * (float)j;
               float deltaj3 = 3.0F * deltaj;
               w[j] = (float)FastMath.cos((double)deltaj);
               w[j + 1] = (float)FastMath.sin((double)deltaj);
               w[j + 2] = (float)FastMath.cos((double)deltaj3);
               w[j + 3] = -((float)FastMath.sin((double)deltaj3));
            }
         }

         int nw1;
         for(int nw0 = 0; nwh > 2; nw0 = nw1) {
            nw1 = nw0 + nwh;
            nwh >>= 1;
            w[nw1] = 1.0F;
            w[nw1 + 1] = wn4r;
            if (nwh == 4) {
               float wk1r = w[nw0 + 4];
               float wk1i = w[nw0 + 5];
               w[nw1 + 2] = wk1r;
               w[nw1 + 3] = wk1i;
            } else if (nwh > 4) {
               float wk1r = w[nw0 + 4];
               float wk3r = w[nw0 + 6];
               w[nw1 + 2] = 0.5F / wk1r;
               w[nw1 + 3] = 0.5F / wk3r;

               for(int j = 4; j < nwh; j += 4) {
                  int idx1 = nw0 + 2 * j;
                  int idx2 = nw1 + j;
                  wk1r = w[idx1];
                  float wk1i = w[idx1 + 1];
                  wk3r = w[idx1 + 2];
                  float wk3i = w[idx1 + 3];
                  w[idx2] = wk1r;
                  w[idx2 + 1] = wk1i;
                  w[idx2 + 2] = wk3r;
                  w[idx2 + 3] = wk3i;
               }
            }
         }
      }

   }

   public static void makewt(long nw, LongLargeArray ipl, FloatLargeArray wl) {
      ipl.setLong(0L, nw);
      ipl.setLong(1L, 1L);
      if (nw > 2L) {
         long nwh = nw >> 1;
         float delta = ((float)Math.PI / 4F) / (float)nwh;
         float delta2 = delta * 2.0F;
         float wn4r = (float)FastMath.cos((double)(delta * (float)nwh));
         wl.setFloat(0L, 1.0F);
         wl.setFloat(1L, wn4r);
         if (nwh == 4L) {
            wl.setFloat(2L, (float)FastMath.cos((double)delta2));
            wl.setFloat(3L, (float)FastMath.sin((double)delta2));
         } else if (nwh > 4L) {
            makeipt(nw, ipl);
            wl.setFloat(2L, 0.5F / (float)FastMath.cos((double)delta2));
            wl.setFloat(3L, 0.5F / (float)FastMath.cos((double)(delta * 6.0F)));

            for(long j = 4L; j < nwh; j += 4L) {
               float deltaj = delta * (float)j;
               float deltaj3 = 3.0F * deltaj;
               wl.setFloat(j, (float)FastMath.cos((double)deltaj));
               wl.setFloat(j + 1L, (float)FastMath.sin((double)deltaj));
               wl.setFloat(j + 2L, (float)FastMath.cos((double)deltaj3));
               wl.setFloat(j + 3L, -((float)FastMath.sin((double)deltaj3)));
            }
         }

         long nw1;
         for(long nw0 = 0L; nwh > 2L; nw0 = nw1) {
            nw1 = nw0 + nwh;
            nwh >>= 1;
            wl.setFloat(nw1, 1.0F);
            wl.setFloat(nw1 + 1L, wn4r);
            if (nwh == 4L) {
               float wk1r = wl.getFloat(nw0 + 4L);
               float wk1i = wl.getFloat(nw0 + 5L);
               wl.setFloat(nw1 + 2L, wk1r);
               wl.setFloat(nw1 + 3L, wk1i);
            } else if (nwh > 4L) {
               float wk1r = wl.getFloat(nw0 + 4L);
               float wk3r = wl.getFloat(nw0 + 6L);
               wl.setFloat(nw1 + 2L, 0.5F / wk1r);
               wl.setFloat(nw1 + 3L, 0.5F / wk3r);

               for(long j = 4L; j < nwh; j += 4L) {
                  long idx1 = nw0 + 2L * j;
                  long idx2 = nw1 + j;
                  wk1r = wl.getFloat(idx1);
                  float wk1i = wl.getFloat(idx1 + 1L);
                  wk3r = wl.getFloat(idx1 + 2L);
                  float wk3i = wl.getFloat(idx1 + 3L);
                  wl.setFloat(idx2, wk1r);
                  wl.setFloat(idx2 + 1L, wk1i);
                  wl.setFloat(idx2 + 2L, wk3r);
                  wl.setFloat(idx2 + 3L, wk3i);
               }
            }
         }
      }

   }

   public static void cftfsub(int n, double[] a, int offa, int[] ip, int nw, double[] w) {
      if (n > 8) {
         if (n > 32) {
            cftf1st(n, a, offa, w, nw - (n >> 2));
            if (ConcurrencyUtils.getNumberOfThreads() > 1 && (long)n >= getThreadsBeginN_1D_FFT_2Threads()) {
               cftrec4_th(n, a, offa, nw, w);
            } else if (n > 512) {
               cftrec4(n, a, offa, nw, w);
            } else if (n > 128) {
               cftleaf(n, 1, (double[])a, offa, nw, (double[])w);
            } else {
               cftfx41(n, a, offa, nw, w);
            }

            bitrv2(n, ip, a, offa);
         } else if (n == 32) {
            cftf161(a, offa, w, nw - 8);
            bitrv216(a, offa);
         } else {
            cftf081((double[])a, offa, (double[])w, 0);
            bitrv208(a, offa);
         }
      } else if (n == 8) {
         cftf040(a, offa);
      } else if (n == 4) {
         cftxb020(a, offa);
      }

   }

   public static void cftfsub(long n, DoubleLargeArray a, long offa, LongLargeArray ip, long nw, DoubleLargeArray w) {
      if (n > 8L) {
         if (n > 32L) {
            cftf1st(n, a, offa, w, nw - (n >> 2));
            if (ConcurrencyUtils.getNumberOfThreads() > 1 && n >= getThreadsBeginN_1D_FFT_2Threads()) {
               cftrec4_th(n, a, offa, nw, w);
            } else if (n > 512L) {
               cftrec4(n, a, offa, nw, w);
            } else if (n > 128L) {
               cftleaf(n, 1L, a, offa, nw, w);
            } else {
               cftfx41(n, a, offa, nw, w);
            }

            bitrv2l(n, ip, a, offa);
         } else if (n == 32L) {
            cftf161(a, offa, w, nw - 8L);
            bitrv216(a, offa);
         } else {
            cftf081(a, offa, w, 0L);
            bitrv208(a, offa);
         }
      } else if (n == 8L) {
         cftf040(a, offa);
      } else if (n == 4L) {
         cftxb020(a, offa);
      }

   }

   public static void cftbsub(int n, double[] a, int offa, int[] ip, int nw, double[] w) {
      if (n > 8) {
         if (n > 32) {
            cftb1st(n, a, offa, w, nw - (n >> 2));
            if (ConcurrencyUtils.getNumberOfThreads() > 1 && (long)n >= getThreadsBeginN_1D_FFT_2Threads()) {
               cftrec4_th(n, a, offa, nw, w);
            } else if (n > 512) {
               cftrec4(n, a, offa, nw, w);
            } else if (n > 128) {
               cftleaf(n, 1, (double[])a, offa, nw, (double[])w);
            } else {
               cftfx41(n, a, offa, nw, w);
            }

            bitrv2conj(n, ip, a, offa);
         } else if (n == 32) {
            cftf161(a, offa, w, nw - 8);
            bitrv216neg(a, offa);
         } else {
            cftf081((double[])a, offa, (double[])w, 0);
            bitrv208neg(a, offa);
         }
      } else if (n == 8) {
         cftb040(a, offa);
      } else if (n == 4) {
         cftxb020(a, offa);
      }

   }

   public static void cftbsub(long n, DoubleLargeArray a, long offa, LongLargeArray ip, long nw, DoubleLargeArray w) {
      if (n > 8L) {
         if (n > 32L) {
            cftb1st(n, a, offa, w, nw - (n >> 2));
            if (ConcurrencyUtils.getNumberOfThreads() > 1 && n >= getThreadsBeginN_1D_FFT_2Threads()) {
               cftrec4_th(n, a, offa, nw, w);
            } else if (n > 512L) {
               cftrec4(n, a, offa, nw, w);
            } else if (n > 128L) {
               cftleaf(n, 1L, a, offa, nw, w);
            } else {
               cftfx41(n, a, offa, nw, w);
            }

            bitrv2conj(n, ip, a, offa);
         } else if (n == 32L) {
            cftf161(a, offa, w, nw - 8L);
            bitrv216neg(a, offa);
         } else {
            cftf081(a, offa, w, 0L);
            bitrv208neg(a, offa);
         }
      } else if (n == 8L) {
         cftb040(a, offa);
      } else if (n == 4L) {
         cftxb020(a, offa);
      }

   }

   public static void bitrv2(int n, int[] ip, double[] a, int offa) {
      int m = 1;

      int l;
      for(l = n >> 2; l > 8; l >>= 2) {
         m <<= 1;
      }

      int nh = n >> 1;
      int nm = 4 * m;
      if (l == 8) {
         for(int k = 0; k < m; ++k) {
            int idx0 = 4 * k;

            for(int j = 0; j < k; ++j) {
               int j1 = 4 * j + 2 * ip[m + k];
               int k1 = idx0 + 2 * ip[m + j];
               int idx1 = offa + j1;
               int idx2 = offa + k1;
               double xr = a[idx1];
               double xi = a[idx1 + 1];
               double yr = a[idx2];
               double yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += nm;
               k1 += 2 * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = a[idx1 + 1];
               yr = a[idx2];
               yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += nm;
               k1 -= nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = a[idx1 + 1];
               yr = a[idx2];
               yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += nm;
               k1 += 2 * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = a[idx1 + 1];
               yr = a[idx2];
               yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += nh;
               k1 += 2;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = a[idx1 + 1];
               yr = a[idx2];
               yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 -= nm;
               k1 -= 2 * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = a[idx1 + 1];
               yr = a[idx2];
               yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 -= nm;
               k1 += nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = a[idx1 + 1];
               yr = a[idx2];
               yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 -= nm;
               k1 -= 2 * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = a[idx1 + 1];
               yr = a[idx2];
               yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += 2;
               k1 += nh;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = a[idx1 + 1];
               yr = a[idx2];
               yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += nm;
               k1 += 2 * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = a[idx1 + 1];
               yr = a[idx2];
               yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += nm;
               k1 -= nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = a[idx1 + 1];
               yr = a[idx2];
               yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += nm;
               k1 += 2 * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = a[idx1 + 1];
               yr = a[idx2];
               yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 -= nh;
               k1 -= 2;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = a[idx1 + 1];
               yr = a[idx2];
               yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 -= nm;
               k1 -= 2 * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = a[idx1 + 1];
               yr = a[idx2];
               yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 -= nm;
               k1 += nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = a[idx1 + 1];
               yr = a[idx2];
               yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 -= nm;
               k1 -= 2 * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = a[idx1 + 1];
               yr = a[idx2];
               yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
            }

            int k1 = idx0 + 2 * ip[m + k];
            int j1 = k1 + 2;
            k1 += nh;
            int idx1 = offa + j1;
            int idx2 = offa + k1;
            double xr = a[idx1];
            double xi = a[idx1 + 1];
            double yr = a[idx2];
            double yi = a[idx2 + 1];
            a[idx1] = yr;
            a[idx1 + 1] = yi;
            a[idx2] = xr;
            a[idx2 + 1] = xi;
            j1 += nm;
            k1 += 2 * nm;
            idx1 = offa + j1;
            idx2 = offa + k1;
            xr = a[idx1];
            xi = a[idx1 + 1];
            yr = a[idx2];
            yi = a[idx2 + 1];
            a[idx1] = yr;
            a[idx1 + 1] = yi;
            a[idx2] = xr;
            a[idx2 + 1] = xi;
            j1 += nm;
            k1 -= nm;
            idx1 = offa + j1;
            idx2 = offa + k1;
            xr = a[idx1];
            xi = a[idx1 + 1];
            yr = a[idx2];
            yi = a[idx2 + 1];
            a[idx1] = yr;
            a[idx1 + 1] = yi;
            a[idx2] = xr;
            a[idx2 + 1] = xi;
            j1 -= 2;
            k1 -= nh;
            idx1 = offa + j1;
            idx2 = offa + k1;
            xr = a[idx1];
            xi = a[idx1 + 1];
            yr = a[idx2];
            yi = a[idx2 + 1];
            a[idx1] = yr;
            a[idx1 + 1] = yi;
            a[idx2] = xr;
            a[idx2 + 1] = xi;
            j1 += nh + 2;
            k1 += nh + 2;
            idx1 = offa + j1;
            idx2 = offa + k1;
            xr = a[idx1];
            xi = a[idx1 + 1];
            yr = a[idx2];
            yi = a[idx2 + 1];
            a[idx1] = yr;
            a[idx1 + 1] = yi;
            a[idx2] = xr;
            a[idx2 + 1] = xi;
            j1 -= nh - nm;
            k1 += 2 * nm - 2;
            idx1 = offa + j1;
            idx2 = offa + k1;
            xr = a[idx1];
            xi = a[idx1 + 1];
            yr = a[idx2];
            yi = a[idx2 + 1];
            a[idx1] = yr;
            a[idx1 + 1] = yi;
            a[idx2] = xr;
            a[idx2 + 1] = xi;
         }
      } else {
         for(int k = 0; k < m; ++k) {
            int idx0 = 4 * k;

            for(int j = 0; j < k; ++j) {
               int j1 = 4 * j + ip[m + k];
               int k1 = idx0 + ip[m + j];
               int idx1 = offa + j1;
               int idx2 = offa + k1;
               double xr = a[idx1];
               double xi = a[idx1 + 1];
               double yr = a[idx2];
               double yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += nm;
               k1 += nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = a[idx1 + 1];
               yr = a[idx2];
               yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += nh;
               k1 += 2;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = a[idx1 + 1];
               yr = a[idx2];
               yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 -= nm;
               k1 -= nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = a[idx1 + 1];
               yr = a[idx2];
               yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += 2;
               k1 += nh;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = a[idx1 + 1];
               yr = a[idx2];
               yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += nm;
               k1 += nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = a[idx1 + 1];
               yr = a[idx2];
               yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 -= nh;
               k1 -= 2;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = a[idx1 + 1];
               yr = a[idx2];
               yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 -= nm;
               k1 -= nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = a[idx1 + 1];
               yr = a[idx2];
               yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
            }

            int k1 = idx0 + ip[m + k];
            int j1 = k1 + 2;
            k1 += nh;
            int idx1 = offa + j1;
            int idx2 = offa + k1;
            double xr = a[idx1];
            double xi = a[idx1 + 1];
            double yr = a[idx2];
            double yi = a[idx2 + 1];
            a[idx1] = yr;
            a[idx1 + 1] = yi;
            a[idx2] = xr;
            a[idx2 + 1] = xi;
            j1 += nm;
            k1 += nm;
            idx1 = offa + j1;
            idx2 = offa + k1;
            xr = a[idx1];
            xi = a[idx1 + 1];
            yr = a[idx2];
            yi = a[idx2 + 1];
            a[idx1] = yr;
            a[idx1 + 1] = yi;
            a[idx2] = xr;
            a[idx2 + 1] = xi;
         }
      }

   }

   public static void bitrv2l(long n, LongLargeArray ip, DoubleLargeArray a, long offa) {
      long m = 1L;

      long l;
      for(l = n >> 2; l > 8L; l >>= 2) {
         m <<= 1;
      }

      long nh = n >> 1;
      long nm = 4L * m;
      if (l == 8L) {
         for(long k = 0L; k < m; ++k) {
            long idx0 = 4L * k;

            for(long j = 0L; j < k; ++j) {
               long j1 = 4L * j + 2L * ip.getLong(m + k);
               long k1 = idx0 + 2L * ip.getLong(m + j);
               long idx1 = offa + j1;
               long idx2 = offa + k1;
               double xr = a.getDouble(idx1);
               double xi = a.getDouble(idx1 + 1L);
               double yr = a.getDouble(idx2);
               double yi = a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
               j1 += nm;
               k1 += 2L * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getDouble(idx1);
               xi = a.getDouble(idx1 + 1L);
               yr = a.getDouble(idx2);
               yi = a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
               j1 += nm;
               k1 -= nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getDouble(idx1);
               xi = a.getDouble(idx1 + 1L);
               yr = a.getDouble(idx2);
               yi = a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
               j1 += nm;
               k1 += 2L * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getDouble(idx1);
               xi = a.getDouble(idx1 + 1L);
               yr = a.getDouble(idx2);
               yi = a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
               j1 += nh;
               k1 += 2L;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getDouble(idx1);
               xi = a.getDouble(idx1 + 1L);
               yr = a.getDouble(idx2);
               yi = a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
               j1 -= nm;
               k1 -= 2L * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getDouble(idx1);
               xi = a.getDouble(idx1 + 1L);
               yr = a.getDouble(idx2);
               yi = a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
               j1 -= nm;
               k1 += nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getDouble(idx1);
               xi = a.getDouble(idx1 + 1L);
               yr = a.getDouble(idx2);
               yi = a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
               j1 -= nm;
               k1 -= 2L * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getDouble(idx1);
               xi = a.getDouble(idx1 + 1L);
               yr = a.getDouble(idx2);
               yi = a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
               j1 += 2L;
               k1 += nh;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getDouble(idx1);
               xi = a.getDouble(idx1 + 1L);
               yr = a.getDouble(idx2);
               yi = a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
               j1 += nm;
               k1 += 2L * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getDouble(idx1);
               xi = a.getDouble(idx1 + 1L);
               yr = a.getDouble(idx2);
               yi = a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
               j1 += nm;
               k1 -= nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getDouble(idx1);
               xi = a.getDouble(idx1 + 1L);
               yr = a.getDouble(idx2);
               yi = a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
               j1 += nm;
               k1 += 2L * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getDouble(idx1);
               xi = a.getDouble(idx1 + 1L);
               yr = a.getDouble(idx2);
               yi = a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
               j1 -= nh;
               k1 -= 2L;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getDouble(idx1);
               xi = a.getDouble(idx1 + 1L);
               yr = a.getDouble(idx2);
               yi = a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
               j1 -= nm;
               k1 -= 2L * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getDouble(idx1);
               xi = a.getDouble(idx1 + 1L);
               yr = a.getDouble(idx2);
               yi = a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
               j1 -= nm;
               k1 += nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getDouble(idx1);
               xi = a.getDouble(idx1 + 1L);
               yr = a.getDouble(idx2);
               yi = a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
               j1 -= nm;
               k1 -= 2L * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getDouble(idx1);
               xi = a.getDouble(idx1 + 1L);
               yr = a.getDouble(idx2);
               yi = a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
            }

            long k1 = idx0 + 2L * ip.getLong(m + k);
            long j1 = k1 + 2L;
            k1 += nh;
            long idx1 = offa + j1;
            long idx2 = offa + k1;
            double xr = a.getDouble(idx1);
            double xi = a.getDouble(idx1 + 1L);
            double yr = a.getDouble(idx2);
            double yi = a.getDouble(idx2 + 1L);
            a.setDouble(idx1, yr);
            a.setDouble(idx1 + 1L, yi);
            a.setDouble(idx2, xr);
            a.setDouble(idx2 + 1L, xi);
            j1 += nm;
            k1 += 2L * nm;
            idx1 = offa + j1;
            idx2 = offa + k1;
            xr = a.getDouble(idx1);
            xi = a.getDouble(idx1 + 1L);
            yr = a.getDouble(idx2);
            yi = a.getDouble(idx2 + 1L);
            a.setDouble(idx1, yr);
            a.setDouble(idx1 + 1L, yi);
            a.setDouble(idx2, xr);
            a.setDouble(idx2 + 1L, xi);
            j1 += nm;
            k1 -= nm;
            idx1 = offa + j1;
            idx2 = offa + k1;
            xr = a.getDouble(idx1);
            xi = a.getDouble(idx1 + 1L);
            yr = a.getDouble(idx2);
            yi = a.getDouble(idx2 + 1L);
            a.setDouble(idx1, yr);
            a.setDouble(idx1 + 1L, yi);
            a.setDouble(idx2, xr);
            a.setDouble(idx2 + 1L, xi);
            j1 -= 2L;
            k1 -= nh;
            idx1 = offa + j1;
            idx2 = offa + k1;
            xr = a.getDouble(idx1);
            xi = a.getDouble(idx1 + 1L);
            yr = a.getDouble(idx2);
            yi = a.getDouble(idx2 + 1L);
            a.setDouble(idx1, yr);
            a.setDouble(idx1 + 1L, yi);
            a.setDouble(idx2, xr);
            a.setDouble(idx2 + 1L, xi);
            j1 += nh + 2L;
            k1 += nh + 2L;
            idx1 = offa + j1;
            idx2 = offa + k1;
            xr = a.getDouble(idx1);
            xi = a.getDouble(idx1 + 1L);
            yr = a.getDouble(idx2);
            yi = a.getDouble(idx2 + 1L);
            a.setDouble(idx1, yr);
            a.setDouble(idx1 + 1L, yi);
            a.setDouble(idx2, xr);
            a.setDouble(idx2 + 1L, xi);
            j1 -= nh - nm;
            k1 += 2L * nm - 2L;
            idx1 = offa + j1;
            idx2 = offa + k1;
            xr = a.getDouble(idx1);
            xi = a.getDouble(idx1 + 1L);
            yr = a.getDouble(idx2);
            yi = a.getDouble(idx2 + 1L);
            a.setDouble(idx1, yr);
            a.setDouble(idx1 + 1L, yi);
            a.setDouble(idx2, xr);
            a.setDouble(idx2 + 1L, xi);
         }
      } else {
         for(long k = 0L; k < m; ++k) {
            long idx0 = 4L * k;

            for(long j = 0L; j < k; ++j) {
               long j1 = 4L * j + ip.getLong(m + k);
               long k1 = idx0 + ip.getLong(m + j);
               long idx1 = offa + j1;
               long idx2 = offa + k1;
               double xr = a.getDouble(idx1);
               double xi = a.getDouble(idx1 + 1L);
               double yr = a.getDouble(idx2);
               double yi = a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
               j1 += nm;
               k1 += nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getDouble(idx1);
               xi = a.getDouble(idx1 + 1L);
               yr = a.getDouble(idx2);
               yi = a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
               j1 += nh;
               k1 += 2L;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getDouble(idx1);
               xi = a.getDouble(idx1 + 1L);
               yr = a.getDouble(idx2);
               yi = a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
               j1 -= nm;
               k1 -= nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getDouble(idx1);
               xi = a.getDouble(idx1 + 1L);
               yr = a.getDouble(idx2);
               yi = a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
               j1 += 2L;
               k1 += nh;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getDouble(idx1);
               xi = a.getDouble(idx1 + 1L);
               yr = a.getDouble(idx2);
               yi = a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
               j1 += nm;
               k1 += nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getDouble(idx1);
               xi = a.getDouble(idx1 + 1L);
               yr = a.getDouble(idx2);
               yi = a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
               j1 -= nh;
               k1 -= 2L;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getDouble(idx1);
               xi = a.getDouble(idx1 + 1L);
               yr = a.getDouble(idx2);
               yi = a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
               j1 -= nm;
               k1 -= nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getDouble(idx1);
               xi = a.getDouble(idx1 + 1L);
               yr = a.getDouble(idx2);
               yi = a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
            }

            long k1 = idx0 + ip.getLong(m + k);
            long j1 = k1 + 2L;
            k1 += nh;
            long idx1 = offa + j1;
            long idx2 = offa + k1;
            double xr = a.getDouble(idx1);
            double xi = a.getDouble(idx1 + 1L);
            double yr = a.getDouble(idx2);
            double yi = a.getDouble(idx2 + 1L);
            a.setDouble(idx1, yr);
            a.setDouble(idx1 + 1L, yi);
            a.setDouble(idx2, xr);
            a.setDouble(idx2 + 1L, xi);
            j1 += nm;
            k1 += nm;
            idx1 = offa + j1;
            idx2 = offa + k1;
            xr = a.getDouble(idx1);
            xi = a.getDouble(idx1 + 1L);
            yr = a.getDouble(idx2);
            yi = a.getDouble(idx2 + 1L);
            a.setDouble(idx1, yr);
            a.setDouble(idx1 + 1L, yi);
            a.setDouble(idx2, xr);
            a.setDouble(idx2 + 1L, xi);
         }
      }

   }

   public static void bitrv2conj(int n, int[] ip, double[] a, int offa) {
      int m = 1;

      int l;
      for(l = n >> 2; l > 8; l >>= 2) {
         m <<= 1;
      }

      int nh = n >> 1;
      int nm = 4 * m;
      if (l == 8) {
         for(int k = 0; k < m; ++k) {
            int idx0 = 4 * k;

            for(int j = 0; j < k; ++j) {
               int j1 = 4 * j + 2 * ip[m + k];
               int k1 = idx0 + 2 * ip[m + j];
               int idx1 = offa + j1;
               int idx2 = offa + k1;
               double xr = a[idx1];
               double xi = -a[idx1 + 1];
               double yr = a[idx2];
               double yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += nm;
               k1 += 2 * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = -a[idx1 + 1];
               yr = a[idx2];
               yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += nm;
               k1 -= nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = -a[idx1 + 1];
               yr = a[idx2];
               yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += nm;
               k1 += 2 * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = -a[idx1 + 1];
               yr = a[idx2];
               yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += nh;
               k1 += 2;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = -a[idx1 + 1];
               yr = a[idx2];
               yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 -= nm;
               k1 -= 2 * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = -a[idx1 + 1];
               yr = a[idx2];
               yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 -= nm;
               k1 += nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = -a[idx1 + 1];
               yr = a[idx2];
               yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 -= nm;
               k1 -= 2 * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = -a[idx1 + 1];
               yr = a[idx2];
               yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += 2;
               k1 += nh;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = -a[idx1 + 1];
               yr = a[idx2];
               yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += nm;
               k1 += 2 * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = -a[idx1 + 1];
               yr = a[idx2];
               yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += nm;
               k1 -= nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = -a[idx1 + 1];
               yr = a[idx2];
               yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += nm;
               k1 += 2 * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = -a[idx1 + 1];
               yr = a[idx2];
               yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 -= nh;
               k1 -= 2;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = -a[idx1 + 1];
               yr = a[idx2];
               yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 -= nm;
               k1 -= 2 * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = -a[idx1 + 1];
               yr = a[idx2];
               yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 -= nm;
               k1 += nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = -a[idx1 + 1];
               yr = a[idx2];
               yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 -= nm;
               k1 -= 2 * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = -a[idx1 + 1];
               yr = a[idx2];
               yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
            }

            int k1 = idx0 + 2 * ip[m + k];
            int j1 = k1 + 2;
            k1 += nh;
            int idx1 = offa + j1;
            int idx2 = offa + k1;
            a[idx1 - 1] = -a[idx1 - 1];
            double xr = a[idx1];
            double xi = -a[idx1 + 1];
            double yr = a[idx2];
            double yi = -a[idx2 + 1];
            a[idx1] = yr;
            a[idx1 + 1] = yi;
            a[idx2] = xr;
            a[idx2 + 1] = xi;
            a[idx2 + 3] = -a[idx2 + 3];
            j1 += nm;
            k1 += 2 * nm;
            idx1 = offa + j1;
            idx2 = offa + k1;
            xr = a[idx1];
            xi = -a[idx1 + 1];
            yr = a[idx2];
            yi = -a[idx2 + 1];
            a[idx1] = yr;
            a[idx1 + 1] = yi;
            a[idx2] = xr;
            a[idx2 + 1] = xi;
            j1 += nm;
            k1 -= nm;
            idx1 = offa + j1;
            idx2 = offa + k1;
            xr = a[idx1];
            xi = -a[idx1 + 1];
            yr = a[idx2];
            yi = -a[idx2 + 1];
            a[idx1] = yr;
            a[idx1 + 1] = yi;
            a[idx2] = xr;
            a[idx2 + 1] = xi;
            j1 -= 2;
            k1 -= nh;
            idx1 = offa + j1;
            idx2 = offa + k1;
            xr = a[idx1];
            xi = -a[idx1 + 1];
            yr = a[idx2];
            yi = -a[idx2 + 1];
            a[idx1] = yr;
            a[idx1 + 1] = yi;
            a[idx2] = xr;
            a[idx2 + 1] = xi;
            j1 += nh + 2;
            k1 += nh + 2;
            idx1 = offa + j1;
            idx2 = offa + k1;
            xr = a[idx1];
            xi = -a[idx1 + 1];
            yr = a[idx2];
            yi = -a[idx2 + 1];
            a[idx1] = yr;
            a[idx1 + 1] = yi;
            a[idx2] = xr;
            a[idx2 + 1] = xi;
            j1 -= nh - nm;
            k1 += 2 * nm - 2;
            idx1 = offa + j1;
            idx2 = offa + k1;
            a[idx1 - 1] = -a[idx1 - 1];
            xr = a[idx1];
            xi = -a[idx1 + 1];
            yr = a[idx2];
            yi = -a[idx2 + 1];
            a[idx1] = yr;
            a[idx1 + 1] = yi;
            a[idx2] = xr;
            a[idx2 + 1] = xi;
            a[idx2 + 3] = -a[idx2 + 3];
         }
      } else {
         for(int k = 0; k < m; ++k) {
            int idx0 = 4 * k;

            for(int j = 0; j < k; ++j) {
               int j1 = 4 * j + ip[m + k];
               int k1 = idx0 + ip[m + j];
               int idx1 = offa + j1;
               int idx2 = offa + k1;
               double xr = a[idx1];
               double xi = -a[idx1 + 1];
               double yr = a[idx2];
               double yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += nm;
               k1 += nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = -a[idx1 + 1];
               yr = a[idx2];
               yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += nh;
               k1 += 2;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = -a[idx1 + 1];
               yr = a[idx2];
               yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 -= nm;
               k1 -= nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = -a[idx1 + 1];
               yr = a[idx2];
               yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += 2;
               k1 += nh;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = -a[idx1 + 1];
               yr = a[idx2];
               yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += nm;
               k1 += nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = -a[idx1 + 1];
               yr = a[idx2];
               yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 -= nh;
               k1 -= 2;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = -a[idx1 + 1];
               yr = a[idx2];
               yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 -= nm;
               k1 -= nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = -a[idx1 + 1];
               yr = a[idx2];
               yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
            }

            int k1 = idx0 + ip[m + k];
            int j1 = k1 + 2;
            k1 += nh;
            int idx1 = offa + j1;
            int idx2 = offa + k1;
            a[idx1 - 1] = -a[idx1 - 1];
            double xr = a[idx1];
            double xi = -a[idx1 + 1];
            double yr = a[idx2];
            double yi = -a[idx2 + 1];
            a[idx1] = yr;
            a[idx1 + 1] = yi;
            a[idx2] = xr;
            a[idx2 + 1] = xi;
            a[idx2 + 3] = -a[idx2 + 3];
            j1 += nm;
            k1 += nm;
            idx1 = offa + j1;
            idx2 = offa + k1;
            a[idx1 - 1] = -a[idx1 - 1];
            xr = a[idx1];
            xi = -a[idx1 + 1];
            yr = a[idx2];
            yi = -a[idx2 + 1];
            a[idx1] = yr;
            a[idx1 + 1] = yi;
            a[idx2] = xr;
            a[idx2 + 1] = xi;
            a[idx2 + 3] = -a[idx2 + 3];
         }
      }

   }

   public static void bitrv2conj(long n, LongLargeArray ip, DoubleLargeArray a, long offa) {
      long m = 1L;

      long l;
      for(l = n >> 2; l > 8L; l >>= 2) {
         m <<= 1;
      }

      long nh = n >> 1;
      long nm = 4L * m;
      if (l == 8L) {
         for(long k = 0L; k < m; ++k) {
            long idx0 = 4L * k;

            for(long j = 0L; j < k; ++j) {
               long j1 = 4L * j + 2L * ip.getLong(m + k);
               long k1 = idx0 + 2L * ip.getLong(m + j);
               long idx1 = offa + j1;
               long idx2 = offa + k1;
               double xr = a.getDouble(idx1);
               double xi = -a.getDouble(idx1 + 1L);
               double yr = a.getDouble(idx2);
               double yi = -a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
               j1 += nm;
               k1 += 2L * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getDouble(idx1);
               xi = -a.getDouble(idx1 + 1L);
               yr = a.getDouble(idx2);
               yi = -a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
               j1 += nm;
               k1 -= nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getDouble(idx1);
               xi = -a.getDouble(idx1 + 1L);
               yr = a.getDouble(idx2);
               yi = -a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
               j1 += nm;
               k1 += 2L * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getDouble(idx1);
               xi = -a.getDouble(idx1 + 1L);
               yr = a.getDouble(idx2);
               yi = -a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
               j1 += nh;
               k1 += 2L;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getDouble(idx1);
               xi = -a.getDouble(idx1 + 1L);
               yr = a.getDouble(idx2);
               yi = -a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
               j1 -= nm;
               k1 -= 2L * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getDouble(idx1);
               xi = -a.getDouble(idx1 + 1L);
               yr = a.getDouble(idx2);
               yi = -a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
               j1 -= nm;
               k1 += nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getDouble(idx1);
               xi = -a.getDouble(idx1 + 1L);
               yr = a.getDouble(idx2);
               yi = -a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
               j1 -= nm;
               k1 -= 2L * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getDouble(idx1);
               xi = -a.getDouble(idx1 + 1L);
               yr = a.getDouble(idx2);
               yi = -a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
               j1 += 2L;
               k1 += nh;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getDouble(idx1);
               xi = -a.getDouble(idx1 + 1L);
               yr = a.getDouble(idx2);
               yi = -a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
               j1 += nm;
               k1 += 2L * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getDouble(idx1);
               xi = -a.getDouble(idx1 + 1L);
               yr = a.getDouble(idx2);
               yi = -a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
               j1 += nm;
               k1 -= nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getDouble(idx1);
               xi = -a.getDouble(idx1 + 1L);
               yr = a.getDouble(idx2);
               yi = -a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
               j1 += nm;
               k1 += 2L * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getDouble(idx1);
               xi = -a.getDouble(idx1 + 1L);
               yr = a.getDouble(idx2);
               yi = -a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
               j1 -= nh;
               k1 -= 2L;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getDouble(idx1);
               xi = -a.getDouble(idx1 + 1L);
               yr = a.getDouble(idx2);
               yi = -a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
               j1 -= nm;
               k1 -= 2L * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getDouble(idx1);
               xi = -a.getDouble(idx1 + 1L);
               yr = a.getDouble(idx2);
               yi = -a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
               j1 -= nm;
               k1 += nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getDouble(idx1);
               xi = -a.getDouble(idx1 + 1L);
               yr = a.getDouble(idx2);
               yi = -a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
               j1 -= nm;
               k1 -= 2L * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getDouble(idx1);
               xi = -a.getDouble(idx1 + 1L);
               yr = a.getDouble(idx2);
               yi = -a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
            }

            long k1 = idx0 + 2L * ip.getLong(m + k);
            long j1 = k1 + 2L;
            k1 += nh;
            long idx1 = offa + j1;
            long idx2 = offa + k1;
            a.setDouble(idx1 - 1L, -a.getDouble(idx1 - 1L));
            double xr = a.getDouble(idx1);
            double xi = -a.getDouble(idx1 + 1L);
            double yr = a.getDouble(idx2);
            double yi = -a.getDouble(idx2 + 1L);
            a.setDouble(idx1, yr);
            a.setDouble(idx1 + 1L, yi);
            a.setDouble(idx2, xr);
            a.setDouble(idx2 + 1L, xi);
            a.setDouble(idx2 + 3L, -a.getDouble(idx2 + 3L));
            j1 += nm;
            k1 += 2L * nm;
            idx1 = offa + j1;
            idx2 = offa + k1;
            xr = a.getDouble(idx1);
            xi = -a.getDouble(idx1 + 1L);
            yr = a.getDouble(idx2);
            yi = -a.getDouble(idx2 + 1L);
            a.setDouble(idx1, yr);
            a.setDouble(idx1 + 1L, yi);
            a.setDouble(idx2, xr);
            a.setDouble(idx2 + 1L, xi);
            j1 += nm;
            k1 -= nm;
            idx1 = offa + j1;
            idx2 = offa + k1;
            xr = a.getDouble(idx1);
            xi = -a.getDouble(idx1 + 1L);
            yr = a.getDouble(idx2);
            yi = -a.getDouble(idx2 + 1L);
            a.setDouble(idx1, yr);
            a.setDouble(idx1 + 1L, yi);
            a.setDouble(idx2, xr);
            a.setDouble(idx2 + 1L, xi);
            j1 -= 2L;
            k1 -= nh;
            idx1 = offa + j1;
            idx2 = offa + k1;
            xr = a.getDouble(idx1);
            xi = -a.getDouble(idx1 + 1L);
            yr = a.getDouble(idx2);
            yi = -a.getDouble(idx2 + 1L);
            a.setDouble(idx1, yr);
            a.setDouble(idx1 + 1L, yi);
            a.setDouble(idx2, xr);
            a.setDouble(idx2 + 1L, xi);
            j1 += nh + 2L;
            k1 += nh + 2L;
            idx1 = offa + j1;
            idx2 = offa + k1;
            xr = a.getDouble(idx1);
            xi = -a.getDouble(idx1 + 1L);
            yr = a.getDouble(idx2);
            yi = -a.getDouble(idx2 + 1L);
            a.setDouble(idx1, yr);
            a.setDouble(idx1 + 1L, yi);
            a.setDouble(idx2, xr);
            a.setDouble(idx2 + 1L, xi);
            j1 -= nh - nm;
            k1 += 2L * nm - 2L;
            idx1 = offa + j1;
            idx2 = offa + k1;
            a.setDouble(idx1 - 1L, -a.getDouble(idx1 - 1L));
            xr = a.getDouble(idx1);
            xi = -a.getDouble(idx1 + 1L);
            yr = a.getDouble(idx2);
            yi = -a.getDouble(idx2 + 1L);
            a.setDouble(idx1, yr);
            a.setDouble(idx1 + 1L, yi);
            a.setDouble(idx2, xr);
            a.setDouble(idx2 + 1L, xi);
            a.setDouble(idx2 + 3L, -a.getDouble(idx2 + 3L));
         }
      } else {
         for(int k = 0; (long)k < m; ++k) {
            long idx0 = (long)(4 * k);

            for(int j = 0; j < k; ++j) {
               long j1 = (long)(4 * j) + ip.getLong(m + (long)k);
               long k1 = idx0 + ip.getLong(m + (long)j);
               long idx1 = offa + j1;
               long idx2 = offa + k1;
               double xr = a.getDouble(idx1);
               double xi = -a.getDouble(idx1 + 1L);
               double yr = a.getDouble(idx2);
               double yi = -a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
               j1 += nm;
               k1 += nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getDouble(idx1);
               xi = -a.getDouble(idx1 + 1L);
               yr = a.getDouble(idx2);
               yi = -a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
               j1 += nh;
               k1 += 2L;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getDouble(idx1);
               xi = -a.getDouble(idx1 + 1L);
               yr = a.getDouble(idx2);
               yi = -a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
               j1 -= nm;
               k1 -= nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getDouble(idx1);
               xi = -a.getDouble(idx1 + 1L);
               yr = a.getDouble(idx2);
               yi = -a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
               j1 += 2L;
               k1 += nh;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getDouble(idx1);
               xi = -a.getDouble(idx1 + 1L);
               yr = a.getDouble(idx2);
               yi = -a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
               j1 += nm;
               k1 += nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getDouble(idx1);
               xi = -a.getDouble(idx1 + 1L);
               yr = a.getDouble(idx2);
               yi = -a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
               j1 -= nh;
               k1 -= 2L;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getDouble(idx1);
               xi = -a.getDouble(idx1 + 1L);
               yr = a.getDouble(idx2);
               yi = -a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
               j1 -= nm;
               k1 -= nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getDouble(idx1);
               xi = -a.getDouble(idx1 + 1L);
               yr = a.getDouble(idx2);
               yi = -a.getDouble(idx2 + 1L);
               a.setDouble(idx1, yr);
               a.setDouble(idx1 + 1L, yi);
               a.setDouble(idx2, xr);
               a.setDouble(idx2 + 1L, xi);
            }

            long k1 = idx0 + ip.getLong(m + (long)k);
            long j1 = k1 + 2L;
            k1 += nh;
            long idx1 = offa + j1;
            long idx2 = offa + k1;
            a.setDouble(idx1 - 1L, -a.getDouble(idx1 - 1L));
            double xr = a.getDouble(idx1);
            double xi = -a.getDouble(idx1 + 1L);
            double yr = a.getDouble(idx2);
            double yi = -a.getDouble(idx2 + 1L);
            a.setDouble(idx1, yr);
            a.setDouble(idx1 + 1L, yi);
            a.setDouble(idx2, xr);
            a.setDouble(idx2 + 1L, xi);
            a.setDouble(idx2 + 3L, -a.getDouble(idx2 + 3L));
            j1 += nm;
            k1 += nm;
            idx1 = offa + j1;
            idx2 = offa + k1;
            a.setDouble(idx1 - 1L, -a.getDouble(idx1 - 1L));
            xr = a.getDouble(idx1);
            xi = -a.getDouble(idx1 + 1L);
            yr = a.getDouble(idx2);
            yi = -a.getDouble(idx2 + 1L);
            a.setDouble(idx1, yr);
            a.setDouble(idx1 + 1L, yi);
            a.setDouble(idx2, xr);
            a.setDouble(idx2 + 1L, xi);
            a.setDouble(idx2 + 3L, -a.getDouble(idx2 + 3L));
         }
      }

   }

   public static void bitrv216(double[] a, int offa) {
      double x1r = a[offa + 2];
      double x1i = a[offa + 3];
      double x2r = a[offa + 4];
      double x2i = a[offa + 5];
      double x3r = a[offa + 6];
      double x3i = a[offa + 7];
      double x4r = a[offa + 8];
      double x4i = a[offa + 9];
      double x5r = a[offa + 10];
      double x5i = a[offa + 11];
      double x7r = a[offa + 14];
      double x7i = a[offa + 15];
      double x8r = a[offa + 16];
      double x8i = a[offa + 17];
      double x10r = a[offa + 20];
      double x10i = a[offa + 21];
      double x11r = a[offa + 22];
      double x11i = a[offa + 23];
      double x12r = a[offa + 24];
      double x12i = a[offa + 25];
      double x13r = a[offa + 26];
      double x13i = a[offa + 27];
      double x14r = a[offa + 28];
      double x14i = a[offa + 29];
      a[offa + 2] = x8r;
      a[offa + 3] = x8i;
      a[offa + 4] = x4r;
      a[offa + 5] = x4i;
      a[offa + 6] = x12r;
      a[offa + 7] = x12i;
      a[offa + 8] = x2r;
      a[offa + 9] = x2i;
      a[offa + 10] = x10r;
      a[offa + 11] = x10i;
      a[offa + 14] = x14r;
      a[offa + 15] = x14i;
      a[offa + 16] = x1r;
      a[offa + 17] = x1i;
      a[offa + 20] = x5r;
      a[offa + 21] = x5i;
      a[offa + 22] = x13r;
      a[offa + 23] = x13i;
      a[offa + 24] = x3r;
      a[offa + 25] = x3i;
      a[offa + 26] = x11r;
      a[offa + 27] = x11i;
      a[offa + 28] = x7r;
      a[offa + 29] = x7i;
   }

   public static void bitrv216(DoubleLargeArray a, long offa) {
      double x1r = a.getDouble(offa + 2L);
      double x1i = a.getDouble(offa + 3L);
      double x2r = a.getDouble(offa + 4L);
      double x2i = a.getDouble(offa + 5L);
      double x3r = a.getDouble(offa + 6L);
      double x3i = a.getDouble(offa + 7L);
      double x4r = a.getDouble(offa + 8L);
      double x4i = a.getDouble(offa + 9L);
      double x5r = a.getDouble(offa + 10L);
      double x5i = a.getDouble(offa + 11L);
      double x7r = a.getDouble(offa + 14L);
      double x7i = a.getDouble(offa + 15L);
      double x8r = a.getDouble(offa + 16L);
      double x8i = a.getDouble(offa + 17L);
      double x10r = a.getDouble(offa + 20L);
      double x10i = a.getDouble(offa + 21L);
      double x11r = a.getDouble(offa + 22L);
      double x11i = a.getDouble(offa + 23L);
      double x12r = a.getDouble(offa + 24L);
      double x12i = a.getDouble(offa + 25L);
      double x13r = a.getDouble(offa + 26L);
      double x13i = a.getDouble(offa + 27L);
      double x14r = a.getDouble(offa + 28L);
      double x14i = a.getDouble(offa + 29L);
      a.setDouble(offa + 2L, x8r);
      a.setDouble(offa + 3L, x8i);
      a.setDouble(offa + 4L, x4r);
      a.setDouble(offa + 5L, x4i);
      a.setDouble(offa + 6L, x12r);
      a.setDouble(offa + 7L, x12i);
      a.setDouble(offa + 8L, x2r);
      a.setDouble(offa + 9L, x2i);
      a.setDouble(offa + 10L, x10r);
      a.setDouble(offa + 11L, x10i);
      a.setDouble(offa + 14L, x14r);
      a.setDouble(offa + 15L, x14i);
      a.setDouble(offa + 16L, x1r);
      a.setDouble(offa + 17L, x1i);
      a.setDouble(offa + 20L, x5r);
      a.setDouble(offa + 21L, x5i);
      a.setDouble(offa + 22L, x13r);
      a.setDouble(offa + 23L, x13i);
      a.setDouble(offa + 24L, x3r);
      a.setDouble(offa + 25L, x3i);
      a.setDouble(offa + 26L, x11r);
      a.setDouble(offa + 27L, x11i);
      a.setDouble(offa + 28L, x7r);
      a.setDouble(offa + 29L, x7i);
   }

   public static void bitrv216neg(double[] a, int offa) {
      double x1r = a[offa + 2];
      double x1i = a[offa + 3];
      double x2r = a[offa + 4];
      double x2i = a[offa + 5];
      double x3r = a[offa + 6];
      double x3i = a[offa + 7];
      double x4r = a[offa + 8];
      double x4i = a[offa + 9];
      double x5r = a[offa + 10];
      double x5i = a[offa + 11];
      double x6r = a[offa + 12];
      double x6i = a[offa + 13];
      double x7r = a[offa + 14];
      double x7i = a[offa + 15];
      double x8r = a[offa + 16];
      double x8i = a[offa + 17];
      double x9r = a[offa + 18];
      double x9i = a[offa + 19];
      double x10r = a[offa + 20];
      double x10i = a[offa + 21];
      double x11r = a[offa + 22];
      double x11i = a[offa + 23];
      double x12r = a[offa + 24];
      double x12i = a[offa + 25];
      double x13r = a[offa + 26];
      double x13i = a[offa + 27];
      double x14r = a[offa + 28];
      double x14i = a[offa + 29];
      double x15r = a[offa + 30];
      double x15i = a[offa + 31];
      a[offa + 2] = x15r;
      a[offa + 3] = x15i;
      a[offa + 4] = x7r;
      a[offa + 5] = x7i;
      a[offa + 6] = x11r;
      a[offa + 7] = x11i;
      a[offa + 8] = x3r;
      a[offa + 9] = x3i;
      a[offa + 10] = x13r;
      a[offa + 11] = x13i;
      a[offa + 12] = x5r;
      a[offa + 13] = x5i;
      a[offa + 14] = x9r;
      a[offa + 15] = x9i;
      a[offa + 16] = x1r;
      a[offa + 17] = x1i;
      a[offa + 18] = x14r;
      a[offa + 19] = x14i;
      a[offa + 20] = x6r;
      a[offa + 21] = x6i;
      a[offa + 22] = x10r;
      a[offa + 23] = x10i;
      a[offa + 24] = x2r;
      a[offa + 25] = x2i;
      a[offa + 26] = x12r;
      a[offa + 27] = x12i;
      a[offa + 28] = x4r;
      a[offa + 29] = x4i;
      a[offa + 30] = x8r;
      a[offa + 31] = x8i;
   }

   public static void bitrv216neg(DoubleLargeArray a, long offa) {
      double x1r = a.getDouble(offa + 2L);
      double x1i = a.getDouble(offa + 3L);
      double x2r = a.getDouble(offa + 4L);
      double x2i = a.getDouble(offa + 5L);
      double x3r = a.getDouble(offa + 6L);
      double x3i = a.getDouble(offa + 7L);
      double x4r = a.getDouble(offa + 8L);
      double x4i = a.getDouble(offa + 9L);
      double x5r = a.getDouble(offa + 10L);
      double x5i = a.getDouble(offa + 11L);
      double x6r = a.getDouble(offa + 12L);
      double x6i = a.getDouble(offa + 13L);
      double x7r = a.getDouble(offa + 14L);
      double x7i = a.getDouble(offa + 15L);
      double x8r = a.getDouble(offa + 16L);
      double x8i = a.getDouble(offa + 17L);
      double x9r = a.getDouble(offa + 18L);
      double x9i = a.getDouble(offa + 19L);
      double x10r = a.getDouble(offa + 20L);
      double x10i = a.getDouble(offa + 21L);
      double x11r = a.getDouble(offa + 22L);
      double x11i = a.getDouble(offa + 23L);
      double x12r = a.getDouble(offa + 24L);
      double x12i = a.getDouble(offa + 25L);
      double x13r = a.getDouble(offa + 26L);
      double x13i = a.getDouble(offa + 27L);
      double x14r = a.getDouble(offa + 28L);
      double x14i = a.getDouble(offa + 29L);
      double x15r = a.getDouble(offa + 30L);
      double x15i = a.getDouble(offa + 31L);
      a.setDouble(offa + 2L, x15r);
      a.setDouble(offa + 3L, x15i);
      a.setDouble(offa + 4L, x7r);
      a.setDouble(offa + 5L, x7i);
      a.setDouble(offa + 6L, x11r);
      a.setDouble(offa + 7L, x11i);
      a.setDouble(offa + 8L, x3r);
      a.setDouble(offa + 9L, x3i);
      a.setDouble(offa + 10L, x13r);
      a.setDouble(offa + 11L, x13i);
      a.setDouble(offa + 12L, x5r);
      a.setDouble(offa + 13L, x5i);
      a.setDouble(offa + 14L, x9r);
      a.setDouble(offa + 15L, x9i);
      a.setDouble(offa + 16L, x1r);
      a.setDouble(offa + 17L, x1i);
      a.setDouble(offa + 18L, x14r);
      a.setDouble(offa + 19L, x14i);
      a.setDouble(offa + 20L, x6r);
      a.setDouble(offa + 21L, x6i);
      a.setDouble(offa + 22L, x10r);
      a.setDouble(offa + 23L, x10i);
      a.setDouble(offa + 24L, x2r);
      a.setDouble(offa + 25L, x2i);
      a.setDouble(offa + 26L, x12r);
      a.setDouble(offa + 27L, x12i);
      a.setDouble(offa + 28L, x4r);
      a.setDouble(offa + 29L, x4i);
      a.setDouble(offa + 30L, x8r);
      a.setDouble(offa + 31L, x8i);
   }

   public static void bitrv208(double[] a, int offa) {
      double x1r = a[offa + 2];
      double x1i = a[offa + 3];
      double x3r = a[offa + 6];
      double x3i = a[offa + 7];
      double x4r = a[offa + 8];
      double x4i = a[offa + 9];
      double x6r = a[offa + 12];
      double x6i = a[offa + 13];
      a[offa + 2] = x4r;
      a[offa + 3] = x4i;
      a[offa + 6] = x6r;
      a[offa + 7] = x6i;
      a[offa + 8] = x1r;
      a[offa + 9] = x1i;
      a[offa + 12] = x3r;
      a[offa + 13] = x3i;
   }

   public static void bitrv208(DoubleLargeArray a, long offa) {
      double x1r = a.getDouble(offa + 2L);
      double x1i = a.getDouble(offa + 3L);
      double x3r = a.getDouble(offa + 6L);
      double x3i = a.getDouble(offa + 7L);
      double x4r = a.getDouble(offa + 8L);
      double x4i = a.getDouble(offa + 9L);
      double x6r = a.getDouble(offa + 12L);
      double x6i = a.getDouble(offa + 13L);
      a.setDouble(offa + 2L, x4r);
      a.setDouble(offa + 3L, x4i);
      a.setDouble(offa + 6L, x6r);
      a.setDouble(offa + 7L, x6i);
      a.setDouble(offa + 8L, x1r);
      a.setDouble(offa + 9L, x1i);
      a.setDouble(offa + 12L, x3r);
      a.setDouble(offa + 13L, x3i);
   }

   public static void bitrv208neg(double[] a, int offa) {
      double x1r = a[offa + 2];
      double x1i = a[offa + 3];
      double x2r = a[offa + 4];
      double x2i = a[offa + 5];
      double x3r = a[offa + 6];
      double x3i = a[offa + 7];
      double x4r = a[offa + 8];
      double x4i = a[offa + 9];
      double x5r = a[offa + 10];
      double x5i = a[offa + 11];
      double x6r = a[offa + 12];
      double x6i = a[offa + 13];
      double x7r = a[offa + 14];
      double x7i = a[offa + 15];
      a[offa + 2] = x7r;
      a[offa + 3] = x7i;
      a[offa + 4] = x3r;
      a[offa + 5] = x3i;
      a[offa + 6] = x5r;
      a[offa + 7] = x5i;
      a[offa + 8] = x1r;
      a[offa + 9] = x1i;
      a[offa + 10] = x6r;
      a[offa + 11] = x6i;
      a[offa + 12] = x2r;
      a[offa + 13] = x2i;
      a[offa + 14] = x4r;
      a[offa + 15] = x4i;
   }

   public static void bitrv208neg(DoubleLargeArray a, long offa) {
      double x1r = a.getDouble(offa + 2L);
      double x1i = a.getDouble(offa + 3L);
      double x2r = a.getDouble(offa + 4L);
      double x2i = a.getDouble(offa + 5L);
      double x3r = a.getDouble(offa + 6L);
      double x3i = a.getDouble(offa + 7L);
      double x4r = a.getDouble(offa + 8L);
      double x4i = a.getDouble(offa + 9L);
      double x5r = a.getDouble(offa + 10L);
      double x5i = a.getDouble(offa + 11L);
      double x6r = a.getDouble(offa + 12L);
      double x6i = a.getDouble(offa + 13L);
      double x7r = a.getDouble(offa + 14L);
      double x7i = a.getDouble(offa + 15L);
      a.setDouble(offa + 2L, x7r);
      a.setDouble(offa + 3L, x7i);
      a.setDouble(offa + 4L, x3r);
      a.setDouble(offa + 5L, x3i);
      a.setDouble(offa + 6L, x5r);
      a.setDouble(offa + 7L, x5i);
      a.setDouble(offa + 8L, x1r);
      a.setDouble(offa + 9L, x1i);
      a.setDouble(offa + 10L, x6r);
      a.setDouble(offa + 11L, x6i);
      a.setDouble(offa + 12L, x2r);
      a.setDouble(offa + 13L, x2i);
      a.setDouble(offa + 14L, x4r);
      a.setDouble(offa + 15L, x4i);
   }

   public static void cftf1st(int n, double[] a, int offa, double[] w, int startw) {
      int mh = n >> 3;
      int m = 2 * mh;
      int j2 = m + m;
      int j3 = j2 + m;
      int idx1 = offa + m;
      int idx2 = offa + j2;
      int idx3 = offa + j3;
      double x0r = a[offa] + a[idx2];
      double x0i = a[offa + 1] + a[idx2 + 1];
      double x1r = a[offa] - a[idx2];
      double x1i = a[offa + 1] - a[idx2 + 1];
      double x2r = a[idx1] + a[idx3];
      double x2i = a[idx1 + 1] + a[idx3 + 1];
      double x3r = a[idx1] - a[idx3];
      double x3i = a[idx1 + 1] - a[idx3 + 1];
      a[offa] = x0r + x2r;
      a[offa + 1] = x0i + x2i;
      a[idx1] = x0r - x2r;
      a[idx1 + 1] = x0i - x2i;
      a[idx2] = x1r - x3i;
      a[idx2 + 1] = x1i + x3r;
      a[idx3] = x1r + x3i;
      a[idx3 + 1] = x1i - x3r;
      double wn4r = w[startw + 1];
      double csc1 = w[startw + 2];
      double csc3 = w[startw + 3];
      double wd1r = (double)1.0F;
      double wd1i = (double)0.0F;
      double wd3r = (double)1.0F;
      double wd3i = (double)0.0F;
      int k = 0;

      for(int j = 2; j < mh - 2; j += 4) {
         k += 4;
         int idx4 = startw + k;
         double wk1r = csc1 * (wd1r + w[idx4]);
         double wk1i = csc1 * (wd1i + w[idx4 + 1]);
         double wk3r = csc3 * (wd3r + w[idx4 + 2]);
         double wk3i = csc3 * (wd3i + w[idx4 + 3]);
         wd1r = w[idx4];
         wd1i = w[idx4 + 1];
         wd3r = w[idx4 + 2];
         wd3i = w[idx4 + 3];
         int j1 = j + m;
         j2 = j1 + m;
         j3 = j2 + m;
         idx1 = offa + j1;
         idx2 = offa + j2;
         idx3 = offa + j3;
         int idx5 = offa + j;
         x0r = a[idx5] + a[idx2];
         x0i = a[idx5 + 1] + a[idx2 + 1];
         x1r = a[idx5] - a[idx2];
         x1i = a[idx5 + 1] - a[idx2 + 1];
         double y0r = a[idx5 + 2] + a[idx2 + 2];
         double y0i = a[idx5 + 3] + a[idx2 + 3];
         double y1r = a[idx5 + 2] - a[idx2 + 2];
         double y1i = a[idx5 + 3] - a[idx2 + 3];
         x2r = a[idx1] + a[idx3];
         x2i = a[idx1 + 1] + a[idx3 + 1];
         x3r = a[idx1] - a[idx3];
         x3i = a[idx1 + 1] - a[idx3 + 1];
         double y2r = a[idx1 + 2] + a[idx3 + 2];
         double y2i = a[idx1 + 3] + a[idx3 + 3];
         double y3r = a[idx1 + 2] - a[idx3 + 2];
         double y3i = a[idx1 + 3] - a[idx3 + 3];
         a[idx5] = x0r + x2r;
         a[idx5 + 1] = x0i + x2i;
         a[idx5 + 2] = y0r + y2r;
         a[idx5 + 3] = y0i + y2i;
         a[idx1] = x0r - x2r;
         a[idx1 + 1] = x0i - x2i;
         a[idx1 + 2] = y0r - y2r;
         a[idx1 + 3] = y0i - y2i;
         x0r = x1r - x3i;
         x0i = x1i + x3r;
         a[idx2] = wk1r * x0r - wk1i * x0i;
         a[idx2 + 1] = wk1r * x0i + wk1i * x0r;
         x0r = y1r - y3i;
         x0i = y1i + y3r;
         a[idx2 + 2] = wd1r * x0r - wd1i * x0i;
         a[idx2 + 3] = wd1r * x0i + wd1i * x0r;
         x0r = x1r + x3i;
         x0i = x1i - x3r;
         a[idx3] = wk3r * x0r + wk3i * x0i;
         a[idx3 + 1] = wk3r * x0i - wk3i * x0r;
         x0r = y1r + y3i;
         x0i = y1i - y3r;
         a[idx3 + 2] = wd3r * x0r + wd3i * x0i;
         a[idx3 + 3] = wd3r * x0i - wd3i * x0r;
         int j0 = m - j;
         j1 = j0 + m;
         j2 = j1 + m;
         j3 = j2 + m;
         int idx0 = offa + j0;
         idx1 = offa + j1;
         idx2 = offa + j2;
         idx3 = offa + j3;
         x0r = a[idx0] + a[idx2];
         x0i = a[idx0 + 1] + a[idx2 + 1];
         x1r = a[idx0] - a[idx2];
         x1i = a[idx0 + 1] - a[idx2 + 1];
         y0r = a[idx0 - 2] + a[idx2 - 2];
         y0i = a[idx0 - 1] + a[idx2 - 1];
         y1r = a[idx0 - 2] - a[idx2 - 2];
         y1i = a[idx0 - 1] - a[idx2 - 1];
         x2r = a[idx1] + a[idx3];
         x2i = a[idx1 + 1] + a[idx3 + 1];
         x3r = a[idx1] - a[idx3];
         x3i = a[idx1 + 1] - a[idx3 + 1];
         y2r = a[idx1 - 2] + a[idx3 - 2];
         y2i = a[idx1 - 1] + a[idx3 - 1];
         y3r = a[idx1 - 2] - a[idx3 - 2];
         y3i = a[idx1 - 1] - a[idx3 - 1];
         a[idx0] = x0r + x2r;
         a[idx0 + 1] = x0i + x2i;
         a[idx0 - 2] = y0r + y2r;
         a[idx0 - 1] = y0i + y2i;
         a[idx1] = x0r - x2r;
         a[idx1 + 1] = x0i - x2i;
         a[idx1 - 2] = y0r - y2r;
         a[idx1 - 1] = y0i - y2i;
         x0r = x1r - x3i;
         x0i = x1i + x3r;
         a[idx2] = wk1i * x0r - wk1r * x0i;
         a[idx2 + 1] = wk1i * x0i + wk1r * x0r;
         x0r = y1r - y3i;
         x0i = y1i + y3r;
         a[idx2 - 2] = wd1i * x0r - wd1r * x0i;
         a[idx2 - 1] = wd1i * x0i + wd1r * x0r;
         x0r = x1r + x3i;
         x0i = x1i - x3r;
         a[idx3] = wk3i * x0r + wk3r * x0i;
         a[idx3 + 1] = wk3i * x0i - wk3r * x0r;
         x0r = y1r + y3i;
         x0i = y1i - y3r;
         a[offa + j3 - 2] = wd3i * x0r + wd3r * x0i;
         a[offa + j3 - 1] = wd3i * x0i - wd3r * x0r;
      }

      double wk1r = csc1 * (wd1r + wn4r);
      double wk1i = csc1 * (wd1i + wn4r);
      double wk3r = csc3 * (wd3r - wn4r);
      double wk3i = csc3 * (wd3i - wn4r);
      int var74 = mh + m;
      j2 = var74 + m;
      j3 = j2 + m;
      int idx0 = offa + mh;
      idx1 = offa + var74;
      idx2 = offa + j2;
      idx3 = offa + j3;
      x0r = a[idx0 - 2] + a[idx2 - 2];
      x0i = a[idx0 - 1] + a[idx2 - 1];
      x1r = a[idx0 - 2] - a[idx2 - 2];
      x1i = a[idx0 - 1] - a[idx2 - 1];
      x2r = a[idx1 - 2] + a[idx3 - 2];
      x2i = a[idx1 - 1] + a[idx3 - 1];
      x3r = a[idx1 - 2] - a[idx3 - 2];
      x3i = a[idx1 - 1] - a[idx3 - 1];
      a[idx0 - 2] = x0r + x2r;
      a[idx0 - 1] = x0i + x2i;
      a[idx1 - 2] = x0r - x2r;
      a[idx1 - 1] = x0i - x2i;
      x0r = x1r - x3i;
      x0i = x1i + x3r;
      a[idx2 - 2] = wk1r * x0r - wk1i * x0i;
      a[idx2 - 1] = wk1r * x0i + wk1i * x0r;
      x0r = x1r + x3i;
      x0i = x1i - x3r;
      a[idx3 - 2] = wk3r * x0r + wk3i * x0i;
      a[idx3 - 1] = wk3r * x0i - wk3i * x0r;
      x0r = a[idx0] + a[idx2];
      x0i = a[idx0 + 1] + a[idx2 + 1];
      x1r = a[idx0] - a[idx2];
      x1i = a[idx0 + 1] - a[idx2 + 1];
      x2r = a[idx1] + a[idx3];
      x2i = a[idx1 + 1] + a[idx3 + 1];
      x3r = a[idx1] - a[idx3];
      x3i = a[idx1 + 1] - a[idx3 + 1];
      a[idx0] = x0r + x2r;
      a[idx0 + 1] = x0i + x2i;
      a[idx1] = x0r - x2r;
      a[idx1 + 1] = x0i - x2i;
      x0r = x1r - x3i;
      x0i = x1i + x3r;
      a[idx2] = wn4r * (x0r - x0i);
      a[idx2 + 1] = wn4r * (x0i + x0r);
      x0r = x1r + x3i;
      x0i = x1i - x3r;
      a[idx3] = -wn4r * (x0r + x0i);
      a[idx3 + 1] = -wn4r * (x0i - x0r);
      x0r = a[idx0 + 2] + a[idx2 + 2];
      x0i = a[idx0 + 3] + a[idx2 + 3];
      x1r = a[idx0 + 2] - a[idx2 + 2];
      x1i = a[idx0 + 3] - a[idx2 + 3];
      x2r = a[idx1 + 2] + a[idx3 + 2];
      x2i = a[idx1 + 3] + a[idx3 + 3];
      x3r = a[idx1 + 2] - a[idx3 + 2];
      x3i = a[idx1 + 3] - a[idx3 + 3];
      a[idx0 + 2] = x0r + x2r;
      a[idx0 + 3] = x0i + x2i;
      a[idx1 + 2] = x0r - x2r;
      a[idx1 + 3] = x0i - x2i;
      x0r = x1r - x3i;
      x0i = x1i + x3r;
      a[idx2 + 2] = wk1i * x0r - wk1r * x0i;
      a[idx2 + 3] = wk1i * x0i + wk1r * x0r;
      x0r = x1r + x3i;
      x0i = x1i - x3r;
      a[idx3 + 2] = wk3i * x0r + wk3r * x0i;
      a[idx3 + 3] = wk3i * x0i - wk3r * x0r;
   }

   public static void cftf1st(long n, DoubleLargeArray a, long offa, DoubleLargeArray w, long startw) {
      long mh = n >> 3;
      long m = 2L * mh;
      long j2 = m + m;
      long j3 = j2 + m;
      long idx1 = offa + m;
      long idx2 = offa + j2;
      long idx3 = offa + j3;
      double x0r = a.getDouble(offa) + a.getDouble(idx2);
      double x0i = a.getDouble(offa + 1L) + a.getDouble(idx2 + 1L);
      double x1r = a.getDouble(offa) - a.getDouble(idx2);
      double x1i = a.getDouble(offa + 1L) - a.getDouble(idx2 + 1L);
      double x2r = a.getDouble(idx1) + a.getDouble(idx3);
      double x2i = a.getDouble(idx1 + 1L) + a.getDouble(idx3 + 1L);
      double x3r = a.getDouble(idx1) - a.getDouble(idx3);
      double x3i = a.getDouble(idx1 + 1L) - a.getDouble(idx3 + 1L);
      a.setDouble(offa, x0r + x2r);
      a.setDouble(offa + 1L, x0i + x2i);
      a.setDouble(idx1, x0r - x2r);
      a.setDouble(idx1 + 1L, x0i - x2i);
      a.setDouble(idx2, x1r - x3i);
      a.setDouble(idx2 + 1L, x1i + x3r);
      a.setDouble(idx3, x1r + x3i);
      a.setDouble(idx3 + 1L, x1i - x3r);
      double wn4r = w.getDouble(startw + 1L);
      double csc1 = w.getDouble(startw + 2L);
      double csc3 = w.getDouble(startw + 3L);
      double wd1r = (double)1.0F;
      double wd1i = (double)0.0F;
      double wd3r = (double)1.0F;
      double wd3i = (double)0.0F;
      long k = 0L;

      for(int j = 2; (long)j < mh - 2L; j += 4) {
         k += 4L;
         long idx4 = startw + k;
         double wk1r = csc1 * (wd1r + w.getDouble(idx4));
         double wk1i = csc1 * (wd1i + w.getDouble(idx4 + 1L));
         double wk3r = csc3 * (wd3r + w.getDouble(idx4 + 2L));
         double wk3i = csc3 * (wd3i + w.getDouble(idx4 + 3L));
         wd1r = w.getDouble(idx4);
         wd1i = w.getDouble(idx4 + 1L);
         wd3r = w.getDouble(idx4 + 2L);
         wd3i = w.getDouble(idx4 + 3L);
         long j1 = (long)j + m;
         j2 = j1 + m;
         j3 = j2 + m;
         idx1 = offa + j1;
         idx2 = offa + j2;
         idx3 = offa + j3;
         long idx5 = offa + (long)j;
         x0r = a.getDouble(idx5) + a.getDouble(idx2);
         x0i = a.getDouble(idx5 + 1L) + a.getDouble(idx2 + 1L);
         x1r = a.getDouble(idx5) - a.getDouble(idx2);
         x1i = a.getDouble(idx5 + 1L) - a.getDouble(idx2 + 1L);
         double y0r = a.getDouble(idx5 + 2L) + a.getDouble(idx2 + 2L);
         double y0i = a.getDouble(idx5 + 3L) + a.getDouble(idx2 + 3L);
         double y1r = a.getDouble(idx5 + 2L) - a.getDouble(idx2 + 2L);
         double y1i = a.getDouble(idx5 + 3L) - a.getDouble(idx2 + 3L);
         x2r = a.getDouble(idx1) + a.getDouble(idx3);
         x2i = a.getDouble(idx1 + 1L) + a.getDouble(idx3 + 1L);
         x3r = a.getDouble(idx1) - a.getDouble(idx3);
         x3i = a.getDouble(idx1 + 1L) - a.getDouble(idx3 + 1L);
         double y2r = a.getDouble(idx1 + 2L) + a.getDouble(idx3 + 2L);
         double y2i = a.getDouble(idx1 + 3L) + a.getDouble(idx3 + 3L);
         double y3r = a.getDouble(idx1 + 2L) - a.getDouble(idx3 + 2L);
         double y3i = a.getDouble(idx1 + 3L) - a.getDouble(idx3 + 3L);
         a.setDouble(idx5, x0r + x2r);
         a.setDouble(idx5 + 1L, x0i + x2i);
         a.setDouble(idx5 + 2L, y0r + y2r);
         a.setDouble(idx5 + 3L, y0i + y2i);
         a.setDouble(idx1, x0r - x2r);
         a.setDouble(idx1 + 1L, x0i - x2i);
         a.setDouble(idx1 + 2L, y0r - y2r);
         a.setDouble(idx1 + 3L, y0i - y2i);
         x0r = x1r - x3i;
         x0i = x1i + x3r;
         a.setDouble(idx2, wk1r * x0r - wk1i * x0i);
         a.setDouble(idx2 + 1L, wk1r * x0i + wk1i * x0r);
         x0r = y1r - y3i;
         x0i = y1i + y3r;
         a.setDouble(idx2 + 2L, wd1r * x0r - wd1i * x0i);
         a.setDouble(idx2 + 3L, wd1r * x0i + wd1i * x0r);
         x0r = x1r + x3i;
         x0i = x1i - x3r;
         a.setDouble(idx3, wk3r * x0r + wk3i * x0i);
         a.setDouble(idx3 + 1L, wk3r * x0i - wk3i * x0r);
         x0r = y1r + y3i;
         x0i = y1i - y3r;
         a.setDouble(idx3 + 2L, wd3r * x0r + wd3i * x0i);
         a.setDouble(idx3 + 3L, wd3r * x0i - wd3i * x0r);
         long j0 = m - (long)j;
         j1 = j0 + m;
         j2 = j1 + m;
         j3 = j2 + m;
         long idx0 = offa + j0;
         idx1 = offa + j1;
         idx2 = offa + j2;
         idx3 = offa + j3;
         x0r = a.getDouble(idx0) + a.getDouble(idx2);
         x0i = a.getDouble(idx0 + 1L) + a.getDouble(idx2 + 1L);
         x1r = a.getDouble(idx0) - a.getDouble(idx2);
         x1i = a.getDouble(idx0 + 1L) - a.getDouble(idx2 + 1L);
         y0r = a.getDouble(idx0 - 2L) + a.getDouble(idx2 - 2L);
         y0i = a.getDouble(idx0 - 1L) + a.getDouble(idx2 - 1L);
         y1r = a.getDouble(idx0 - 2L) - a.getDouble(idx2 - 2L);
         y1i = a.getDouble(idx0 - 1L) - a.getDouble(idx2 - 1L);
         x2r = a.getDouble(idx1) + a.getDouble(idx3);
         x2i = a.getDouble(idx1 + 1L) + a.getDouble(idx3 + 1L);
         x3r = a.getDouble(idx1) - a.getDouble(idx3);
         x3i = a.getDouble(idx1 + 1L) - a.getDouble(idx3 + 1L);
         y2r = a.getDouble(idx1 - 2L) + a.getDouble(idx3 - 2L);
         y2i = a.getDouble(idx1 - 1L) + a.getDouble(idx3 - 1L);
         y3r = a.getDouble(idx1 - 2L) - a.getDouble(idx3 - 2L);
         y3i = a.getDouble(idx1 - 1L) - a.getDouble(idx3 - 1L);
         a.setDouble(idx0, x0r + x2r);
         a.setDouble(idx0 + 1L, x0i + x2i);
         a.setDouble(idx0 - 2L, y0r + y2r);
         a.setDouble(idx0 - 1L, y0i + y2i);
         a.setDouble(idx1, x0r - x2r);
         a.setDouble(idx1 + 1L, x0i - x2i);
         a.setDouble(idx1 - 2L, y0r - y2r);
         a.setDouble(idx1 - 1L, y0i - y2i);
         x0r = x1r - x3i;
         x0i = x1i + x3r;
         a.setDouble(idx2, wk1i * x0r - wk1r * x0i);
         a.setDouble(idx2 + 1L, wk1i * x0i + wk1r * x0r);
         x0r = y1r - y3i;
         x0i = y1i + y3r;
         a.setDouble(idx2 - 2L, wd1i * x0r - wd1r * x0i);
         a.setDouble(idx2 - 1L, wd1i * x0i + wd1r * x0r);
         x0r = x1r + x3i;
         x0i = x1i - x3r;
         a.setDouble(idx3, wk3i * x0r + wk3r * x0i);
         a.setDouble(idx3 + 1L, wk3i * x0i - wk3r * x0r);
         x0r = y1r + y3i;
         x0i = y1i - y3r;
         a.setDouble(offa + j3 - 2L, wd3i * x0r + wd3r * x0i);
         a.setDouble(offa + j3 - 1L, wd3i * x0i - wd3r * x0r);
      }

      double wk1r = csc1 * (wd1r + wn4r);
      double wk1i = csc1 * (wd1i + wn4r);
      double wk3r = csc3 * (wd3r - wn4r);
      double wk3i = csc3 * (wd3i - wn4r);
      long var90 = mh + m;
      j2 = var90 + m;
      j3 = j2 + m;
      long idx0 = offa + mh;
      idx1 = offa + var90;
      idx2 = offa + j2;
      idx3 = offa + j3;
      x0r = a.getDouble(idx0 - 2L) + a.getDouble(idx2 - 2L);
      x0i = a.getDouble(idx0 - 1L) + a.getDouble(idx2 - 1L);
      x1r = a.getDouble(idx0 - 2L) - a.getDouble(idx2 - 2L);
      x1i = a.getDouble(idx0 - 1L) - a.getDouble(idx2 - 1L);
      x2r = a.getDouble(idx1 - 2L) + a.getDouble(idx3 - 2L);
      x2i = a.getDouble(idx1 - 1L) + a.getDouble(idx3 - 1L);
      x3r = a.getDouble(idx1 - 2L) - a.getDouble(idx3 - 2L);
      x3i = a.getDouble(idx1 - 1L) - a.getDouble(idx3 - 1L);
      a.setDouble(idx0 - 2L, x0r + x2r);
      a.setDouble(idx0 - 1L, x0i + x2i);
      a.setDouble(idx1 - 2L, x0r - x2r);
      a.setDouble(idx1 - 1L, x0i - x2i);
      x0r = x1r - x3i;
      x0i = x1i + x3r;
      a.setDouble(idx2 - 2L, wk1r * x0r - wk1i * x0i);
      a.setDouble(idx2 - 1L, wk1r * x0i + wk1i * x0r);
      x0r = x1r + x3i;
      x0i = x1i - x3r;
      a.setDouble(idx3 - 2L, wk3r * x0r + wk3i * x0i);
      a.setDouble(idx3 - 1L, wk3r * x0i - wk3i * x0r);
      x0r = a.getDouble(idx0) + a.getDouble(idx2);
      x0i = a.getDouble(idx0 + 1L) + a.getDouble(idx2 + 1L);
      x1r = a.getDouble(idx0) - a.getDouble(idx2);
      x1i = a.getDouble(idx0 + 1L) - a.getDouble(idx2 + 1L);
      x2r = a.getDouble(idx1) + a.getDouble(idx3);
      x2i = a.getDouble(idx1 + 1L) + a.getDouble(idx3 + 1L);
      x3r = a.getDouble(idx1) - a.getDouble(idx3);
      x3i = a.getDouble(idx1 + 1L) - a.getDouble(idx3 + 1L);
      a.setDouble(idx0, x0r + x2r);
      a.setDouble(idx0 + 1L, x0i + x2i);
      a.setDouble(idx1, x0r - x2r);
      a.setDouble(idx1 + 1L, x0i - x2i);
      x0r = x1r - x3i;
      x0i = x1i + x3r;
      a.setDouble(idx2, wn4r * (x0r - x0i));
      a.setDouble(idx2 + 1L, wn4r * (x0i + x0r));
      x0r = x1r + x3i;
      x0i = x1i - x3r;
      a.setDouble(idx3, -wn4r * (x0r + x0i));
      a.setDouble(idx3 + 1L, -wn4r * (x0i - x0r));
      x0r = a.getDouble(idx0 + 2L) + a.getDouble(idx2 + 2L);
      x0i = a.getDouble(idx0 + 3L) + a.getDouble(idx2 + 3L);
      x1r = a.getDouble(idx0 + 2L) - a.getDouble(idx2 + 2L);
      x1i = a.getDouble(idx0 + 3L) - a.getDouble(idx2 + 3L);
      x2r = a.getDouble(idx1 + 2L) + a.getDouble(idx3 + 2L);
      x2i = a.getDouble(idx1 + 3L) + a.getDouble(idx3 + 3L);
      x3r = a.getDouble(idx1 + 2L) - a.getDouble(idx3 + 2L);
      x3i = a.getDouble(idx1 + 3L) - a.getDouble(idx3 + 3L);
      a.setDouble(idx0 + 2L, x0r + x2r);
      a.setDouble(idx0 + 3L, x0i + x2i);
      a.setDouble(idx1 + 2L, x0r - x2r);
      a.setDouble(idx1 + 3L, x0i - x2i);
      x0r = x1r - x3i;
      x0i = x1i + x3r;
      a.setDouble(idx2 + 2L, wk1i * x0r - wk1r * x0i);
      a.setDouble(idx2 + 3L, wk1i * x0i + wk1r * x0r);
      x0r = x1r + x3i;
      x0i = x1i - x3r;
      a.setDouble(idx3 + 2L, wk3i * x0r + wk3r * x0i);
      a.setDouble(idx3 + 3L, wk3i * x0i - wk3r * x0r);
   }

   public static void cftb1st(int n, double[] a, int offa, double[] w, int startw) {
      int mh = n >> 3;
      int m = 2 * mh;
      int j2 = m + m;
      int j3 = j2 + m;
      int idx1 = offa + m;
      int idx2 = offa + j2;
      int idx3 = offa + j3;
      double x0r = a[offa] + a[idx2];
      double x0i = -a[offa + 1] - a[idx2 + 1];
      double x1r = a[offa] - a[idx2];
      double x1i = -a[offa + 1] + a[idx2 + 1];
      double x2r = a[idx1] + a[idx3];
      double x2i = a[idx1 + 1] + a[idx3 + 1];
      double x3r = a[idx1] - a[idx3];
      double x3i = a[idx1 + 1] - a[idx3 + 1];
      a[offa] = x0r + x2r;
      a[offa + 1] = x0i - x2i;
      a[idx1] = x0r - x2r;
      a[idx1 + 1] = x0i + x2i;
      a[idx2] = x1r + x3i;
      a[idx2 + 1] = x1i + x3r;
      a[idx3] = x1r - x3i;
      a[idx3 + 1] = x1i - x3r;
      double wn4r = w[startw + 1];
      double csc1 = w[startw + 2];
      double csc3 = w[startw + 3];
      double wd1r = (double)1.0F;
      double wd1i = (double)0.0F;
      double wd3r = (double)1.0F;
      double wd3i = (double)0.0F;
      int k = 0;

      for(int j = 2; j < mh - 2; j += 4) {
         k += 4;
         int idx4 = startw + k;
         double wk1r = csc1 * (wd1r + w[idx4]);
         double wk1i = csc1 * (wd1i + w[idx4 + 1]);
         double wk3r = csc3 * (wd3r + w[idx4 + 2]);
         double wk3i = csc3 * (wd3i + w[idx4 + 3]);
         wd1r = w[idx4];
         wd1i = w[idx4 + 1];
         wd3r = w[idx4 + 2];
         wd3i = w[idx4 + 3];
         int j1 = j + m;
         j2 = j1 + m;
         j3 = j2 + m;
         idx1 = offa + j1;
         idx2 = offa + j2;
         idx3 = offa + j3;
         int idx5 = offa + j;
         x0r = a[idx5] + a[idx2];
         x0i = -a[idx5 + 1] - a[idx2 + 1];
         x1r = a[idx5] - a[offa + j2];
         x1i = -a[idx5 + 1] + a[idx2 + 1];
         double y0r = a[idx5 + 2] + a[idx2 + 2];
         double y0i = -a[idx5 + 3] - a[idx2 + 3];
         double y1r = a[idx5 + 2] - a[idx2 + 2];
         double y1i = -a[idx5 + 3] + a[idx2 + 3];
         x2r = a[idx1] + a[idx3];
         x2i = a[idx1 + 1] + a[idx3 + 1];
         x3r = a[idx1] - a[idx3];
         x3i = a[idx1 + 1] - a[idx3 + 1];
         double y2r = a[idx1 + 2] + a[idx3 + 2];
         double y2i = a[idx1 + 3] + a[idx3 + 3];
         double y3r = a[idx1 + 2] - a[idx3 + 2];
         double y3i = a[idx1 + 3] - a[idx3 + 3];
         a[idx5] = x0r + x2r;
         a[idx5 + 1] = x0i - x2i;
         a[idx5 + 2] = y0r + y2r;
         a[idx5 + 3] = y0i - y2i;
         a[idx1] = x0r - x2r;
         a[idx1 + 1] = x0i + x2i;
         a[idx1 + 2] = y0r - y2r;
         a[idx1 + 3] = y0i + y2i;
         x0r = x1r + x3i;
         x0i = x1i + x3r;
         a[idx2] = wk1r * x0r - wk1i * x0i;
         a[idx2 + 1] = wk1r * x0i + wk1i * x0r;
         x0r = y1r + y3i;
         x0i = y1i + y3r;
         a[idx2 + 2] = wd1r * x0r - wd1i * x0i;
         a[idx2 + 3] = wd1r * x0i + wd1i * x0r;
         x0r = x1r - x3i;
         x0i = x1i - x3r;
         a[idx3] = wk3r * x0r + wk3i * x0i;
         a[idx3 + 1] = wk3r * x0i - wk3i * x0r;
         x0r = y1r - y3i;
         x0i = y1i - y3r;
         a[idx3 + 2] = wd3r * x0r + wd3i * x0i;
         a[idx3 + 3] = wd3r * x0i - wd3i * x0r;
         int j0 = m - j;
         j1 = j0 + m;
         j2 = j1 + m;
         j3 = j2 + m;
         int idx0 = offa + j0;
         idx1 = offa + j1;
         idx2 = offa + j2;
         idx3 = offa + j3;
         x0r = a[idx0] + a[idx2];
         x0i = -a[idx0 + 1] - a[idx2 + 1];
         x1r = a[idx0] - a[idx2];
         x1i = -a[idx0 + 1] + a[idx2 + 1];
         y0r = a[idx0 - 2] + a[idx2 - 2];
         y0i = -a[idx0 - 1] - a[idx2 - 1];
         y1r = a[idx0 - 2] - a[idx2 - 2];
         y1i = -a[idx0 - 1] + a[idx2 - 1];
         x2r = a[idx1] + a[idx3];
         x2i = a[idx1 + 1] + a[idx3 + 1];
         x3r = a[idx1] - a[idx3];
         x3i = a[idx1 + 1] - a[idx3 + 1];
         y2r = a[idx1 - 2] + a[idx3 - 2];
         y2i = a[idx1 - 1] + a[idx3 - 1];
         y3r = a[idx1 - 2] - a[idx3 - 2];
         y3i = a[idx1 - 1] - a[idx3 - 1];
         a[idx0] = x0r + x2r;
         a[idx0 + 1] = x0i - x2i;
         a[idx0 - 2] = y0r + y2r;
         a[idx0 - 1] = y0i - y2i;
         a[idx1] = x0r - x2r;
         a[idx1 + 1] = x0i + x2i;
         a[idx1 - 2] = y0r - y2r;
         a[idx1 - 1] = y0i + y2i;
         x0r = x1r + x3i;
         x0i = x1i + x3r;
         a[idx2] = wk1i * x0r - wk1r * x0i;
         a[idx2 + 1] = wk1i * x0i + wk1r * x0r;
         x0r = y1r + y3i;
         x0i = y1i + y3r;
         a[idx2 - 2] = wd1i * x0r - wd1r * x0i;
         a[idx2 - 1] = wd1i * x0i + wd1r * x0r;
         x0r = x1r - x3i;
         x0i = x1i - x3r;
         a[idx3] = wk3i * x0r + wk3r * x0i;
         a[idx3 + 1] = wk3i * x0i - wk3r * x0r;
         x0r = y1r - y3i;
         x0i = y1i - y3r;
         a[idx3 - 2] = wd3i * x0r + wd3r * x0i;
         a[idx3 - 1] = wd3i * x0i - wd3r * x0r;
      }

      double wk1r = csc1 * (wd1r + wn4r);
      double wk1i = csc1 * (wd1i + wn4r);
      double wk3r = csc3 * (wd3r - wn4r);
      double wk3i = csc3 * (wd3i - wn4r);
      int var74 = mh + m;
      j2 = var74 + m;
      j3 = j2 + m;
      int idx0 = offa + mh;
      idx1 = offa + var74;
      idx2 = offa + j2;
      idx3 = offa + j3;
      x0r = a[idx0 - 2] + a[idx2 - 2];
      x0i = -a[idx0 - 1] - a[idx2 - 1];
      x1r = a[idx0 - 2] - a[idx2 - 2];
      x1i = -a[idx0 - 1] + a[idx2 - 1];
      x2r = a[idx1 - 2] + a[idx3 - 2];
      x2i = a[idx1 - 1] + a[idx3 - 1];
      x3r = a[idx1 - 2] - a[idx3 - 2];
      x3i = a[idx1 - 1] - a[idx3 - 1];
      a[idx0 - 2] = x0r + x2r;
      a[idx0 - 1] = x0i - x2i;
      a[idx1 - 2] = x0r - x2r;
      a[idx1 - 1] = x0i + x2i;
      x0r = x1r + x3i;
      x0i = x1i + x3r;
      a[idx2 - 2] = wk1r * x0r - wk1i * x0i;
      a[idx2 - 1] = wk1r * x0i + wk1i * x0r;
      x0r = x1r - x3i;
      x0i = x1i - x3r;
      a[idx3 - 2] = wk3r * x0r + wk3i * x0i;
      a[idx3 - 1] = wk3r * x0i - wk3i * x0r;
      x0r = a[idx0] + a[idx2];
      x0i = -a[idx0 + 1] - a[idx2 + 1];
      x1r = a[idx0] - a[idx2];
      x1i = -a[idx0 + 1] + a[idx2 + 1];
      x2r = a[idx1] + a[idx3];
      x2i = a[idx1 + 1] + a[idx3 + 1];
      x3r = a[idx1] - a[idx3];
      x3i = a[idx1 + 1] - a[idx3 + 1];
      a[idx0] = x0r + x2r;
      a[idx0 + 1] = x0i - x2i;
      a[idx1] = x0r - x2r;
      a[idx1 + 1] = x0i + x2i;
      x0r = x1r + x3i;
      x0i = x1i + x3r;
      a[idx2] = wn4r * (x0r - x0i);
      a[idx2 + 1] = wn4r * (x0i + x0r);
      x0r = x1r - x3i;
      x0i = x1i - x3r;
      a[idx3] = -wn4r * (x0r + x0i);
      a[idx3 + 1] = -wn4r * (x0i - x0r);
      x0r = a[idx0 + 2] + a[idx2 + 2];
      x0i = -a[idx0 + 3] - a[idx2 + 3];
      x1r = a[idx0 + 2] - a[idx2 + 2];
      x1i = -a[idx0 + 3] + a[idx2 + 3];
      x2r = a[idx1 + 2] + a[idx3 + 2];
      x2i = a[idx1 + 3] + a[idx3 + 3];
      x3r = a[idx1 + 2] - a[idx3 + 2];
      x3i = a[idx1 + 3] - a[idx3 + 3];
      a[idx0 + 2] = x0r + x2r;
      a[idx0 + 3] = x0i - x2i;
      a[idx1 + 2] = x0r - x2r;
      a[idx1 + 3] = x0i + x2i;
      x0r = x1r + x3i;
      x0i = x1i + x3r;
      a[idx2 + 2] = wk1i * x0r - wk1r * x0i;
      a[idx2 + 3] = wk1i * x0i + wk1r * x0r;
      x0r = x1r - x3i;
      x0i = x1i - x3r;
      a[idx3 + 2] = wk3i * x0r + wk3r * x0i;
      a[idx3 + 3] = wk3i * x0i - wk3r * x0r;
   }

   public static void cftb1st(long n, DoubleLargeArray a, long offa, DoubleLargeArray w, long startw) {
      long mh = n >> 3;
      long m = 2L * mh;
      long j2 = m + m;
      long j3 = j2 + m;
      long idx1 = offa + m;
      long idx2 = offa + j2;
      long idx3 = offa + j3;
      double x0r = a.getDouble(offa) + a.getDouble(idx2);
      double x0i = -a.getDouble(offa + 1L) - a.getDouble(idx2 + 1L);
      double x1r = a.getDouble(offa) - a.getDouble(idx2);
      double x1i = -a.getDouble(offa + 1L) + a.getDouble(idx2 + 1L);
      double x2r = a.getDouble(idx1) + a.getDouble(idx3);
      double x2i = a.getDouble(idx1 + 1L) + a.getDouble(idx3 + 1L);
      double x3r = a.getDouble(idx1) - a.getDouble(idx3);
      double x3i = a.getDouble(idx1 + 1L) - a.getDouble(idx3 + 1L);
      a.setDouble(offa, x0r + x2r);
      a.setDouble(offa + 1L, x0i - x2i);
      a.setDouble(idx1, x0r - x2r);
      a.setDouble(idx1 + 1L, x0i + x2i);
      a.setDouble(idx2, x1r + x3i);
      a.setDouble(idx2 + 1L, x1i + x3r);
      a.setDouble(idx3, x1r - x3i);
      a.setDouble(idx3 + 1L, x1i - x3r);
      double wn4r = w.getDouble(startw + 1L);
      double csc1 = w.getDouble(startw + 2L);
      double csc3 = w.getDouble(startw + 3L);
      double wd1r = (double)1.0F;
      double wd1i = (double)0.0F;
      double wd3r = (double)1.0F;
      double wd3i = (double)0.0F;
      long k = 0L;

      for(long j = 2L; j < mh - 2L; j += 4L) {
         k += 4L;
         long idx4 = startw + k;
         double wk1r = csc1 * (wd1r + w.getDouble(idx4));
         double wk1i = csc1 * (wd1i + w.getDouble(idx4 + 1L));
         double wk3r = csc3 * (wd3r + w.getDouble(idx4 + 2L));
         double wk3i = csc3 * (wd3i + w.getDouble(idx4 + 3L));
         wd1r = w.getDouble(idx4);
         wd1i = w.getDouble(idx4 + 1L);
         wd3r = w.getDouble(idx4 + 2L);
         wd3i = w.getDouble(idx4 + 3L);
         long j1 = j + m;
         j2 = j1 + m;
         j3 = j2 + m;
         idx1 = offa + j1;
         idx2 = offa + j2;
         idx3 = offa + j3;
         long idx5 = offa + j;
         x0r = a.getDouble(idx5) + a.getDouble(idx2);
         x0i = -a.getDouble(idx5 + 1L) - a.getDouble(idx2 + 1L);
         x1r = a.getDouble(idx5) - a.getDouble(offa + j2);
         x1i = -a.getDouble(idx5 + 1L) + a.getDouble(idx2 + 1L);
         double y0r = a.getDouble(idx5 + 2L) + a.getDouble(idx2 + 2L);
         double y0i = -a.getDouble(idx5 + 3L) - a.getDouble(idx2 + 3L);
         double y1r = a.getDouble(idx5 + 2L) - a.getDouble(idx2 + 2L);
         double y1i = -a.getDouble(idx5 + 3L) + a.getDouble(idx2 + 3L);
         x2r = a.getDouble(idx1) + a.getDouble(idx3);
         x2i = a.getDouble(idx1 + 1L) + a.getDouble(idx3 + 1L);
         x3r = a.getDouble(idx1) - a.getDouble(idx3);
         x3i = a.getDouble(idx1 + 1L) - a.getDouble(idx3 + 1L);
         double y2r = a.getDouble(idx1 + 2L) + a.getDouble(idx3 + 2L);
         double y2i = a.getDouble(idx1 + 3L) + a.getDouble(idx3 + 3L);
         double y3r = a.getDouble(idx1 + 2L) - a.getDouble(idx3 + 2L);
         double y3i = a.getDouble(idx1 + 3L) - a.getDouble(idx3 + 3L);
         a.setDouble(idx5, x0r + x2r);
         a.setDouble(idx5 + 1L, x0i - x2i);
         a.setDouble(idx5 + 2L, y0r + y2r);
         a.setDouble(idx5 + 3L, y0i - y2i);
         a.setDouble(idx1, x0r - x2r);
         a.setDouble(idx1 + 1L, x0i + x2i);
         a.setDouble(idx1 + 2L, y0r - y2r);
         a.setDouble(idx1 + 3L, y0i + y2i);
         x0r = x1r + x3i;
         x0i = x1i + x3r;
         a.setDouble(idx2, wk1r * x0r - wk1i * x0i);
         a.setDouble(idx2 + 1L, wk1r * x0i + wk1i * x0r);
         x0r = y1r + y3i;
         x0i = y1i + y3r;
         a.setDouble(idx2 + 2L, wd1r * x0r - wd1i * x0i);
         a.setDouble(idx2 + 3L, wd1r * x0i + wd1i * x0r);
         x0r = x1r - x3i;
         x0i = x1i - x3r;
         a.setDouble(idx3, wk3r * x0r + wk3i * x0i);
         a.setDouble(idx3 + 1L, wk3r * x0i - wk3i * x0r);
         x0r = y1r - y3i;
         x0i = y1i - y3r;
         a.setDouble(idx3 + 2L, wd3r * x0r + wd3i * x0i);
         a.setDouble(idx3 + 3L, wd3r * x0i - wd3i * x0r);
         long j0 = m - j;
         j1 = j0 + m;
         j2 = j1 + m;
         j3 = j2 + m;
         long idx0 = offa + j0;
         idx1 = offa + j1;
         idx2 = offa + j2;
         idx3 = offa + j3;
         x0r = a.getDouble(idx0) + a.getDouble(idx2);
         x0i = -a.getDouble(idx0 + 1L) - a.getDouble(idx2 + 1L);
         x1r = a.getDouble(idx0) - a.getDouble(idx2);
         x1i = -a.getDouble(idx0 + 1L) + a.getDouble(idx2 + 1L);
         y0r = a.getDouble(idx0 - 2L) + a.getDouble(idx2 - 2L);
         y0i = -a.getDouble(idx0 - 1L) - a.getDouble(idx2 - 1L);
         y1r = a.getDouble(idx0 - 2L) - a.getDouble(idx2 - 2L);
         y1i = -a.getDouble(idx0 - 1L) + a.getDouble(idx2 - 1L);
         x2r = a.getDouble(idx1) + a.getDouble(idx3);
         x2i = a.getDouble(idx1 + 1L) + a.getDouble(idx3 + 1L);
         x3r = a.getDouble(idx1) - a.getDouble(idx3);
         x3i = a.getDouble(idx1 + 1L) - a.getDouble(idx3 + 1L);
         y2r = a.getDouble(idx1 - 2L) + a.getDouble(idx3 - 2L);
         y2i = a.getDouble(idx1 - 1L) + a.getDouble(idx3 - 1L);
         y3r = a.getDouble(idx1 - 2L) - a.getDouble(idx3 - 2L);
         y3i = a.getDouble(idx1 - 1L) - a.getDouble(idx3 - 1L);
         a.setDouble(idx0, x0r + x2r);
         a.setDouble(idx0 + 1L, x0i - x2i);
         a.setDouble(idx0 - 2L, y0r + y2r);
         a.setDouble(idx0 - 1L, y0i - y2i);
         a.setDouble(idx1, x0r - x2r);
         a.setDouble(idx1 + 1L, x0i + x2i);
         a.setDouble(idx1 - 2L, y0r - y2r);
         a.setDouble(idx1 - 1L, y0i + y2i);
         x0r = x1r + x3i;
         x0i = x1i + x3r;
         a.setDouble(idx2, wk1i * x0r - wk1r * x0i);
         a.setDouble(idx2 + 1L, wk1i * x0i + wk1r * x0r);
         x0r = y1r + y3i;
         x0i = y1i + y3r;
         a.setDouble(idx2 - 2L, wd1i * x0r - wd1r * x0i);
         a.setDouble(idx2 - 1L, wd1i * x0i + wd1r * x0r);
         x0r = x1r - x3i;
         x0i = x1i - x3r;
         a.setDouble(idx3, wk3i * x0r + wk3r * x0i);
         a.setDouble(idx3 + 1L, wk3i * x0i - wk3r * x0r);
         x0r = y1r - y3i;
         x0i = y1i - y3r;
         a.setDouble(idx3 - 2L, wd3i * x0r + wd3r * x0i);
         a.setDouble(idx3 - 1L, wd3i * x0i - wd3r * x0r);
      }

      double wk1r = csc1 * (wd1r + wn4r);
      double wk1i = csc1 * (wd1i + wn4r);
      double wk3r = csc3 * (wd3r - wn4r);
      double wk3i = csc3 * (wd3i - wn4r);
      long var91 = mh + m;
      j2 = var91 + m;
      j3 = j2 + m;
      long idx0 = offa + mh;
      idx1 = offa + var91;
      idx2 = offa + j2;
      idx3 = offa + j3;
      x0r = a.getDouble(idx0 - 2L) + a.getDouble(idx2 - 2L);
      x0i = -a.getDouble(idx0 - 1L) - a.getDouble(idx2 - 1L);
      x1r = a.getDouble(idx0 - 2L) - a.getDouble(idx2 - 2L);
      x1i = -a.getDouble(idx0 - 1L) + a.getDouble(idx2 - 1L);
      x2r = a.getDouble(idx1 - 2L) + a.getDouble(idx3 - 2L);
      x2i = a.getDouble(idx1 - 1L) + a.getDouble(idx3 - 1L);
      x3r = a.getDouble(idx1 - 2L) - a.getDouble(idx3 - 2L);
      x3i = a.getDouble(idx1 - 1L) - a.getDouble(idx3 - 1L);
      a.setDouble(idx0 - 2L, x0r + x2r);
      a.setDouble(idx0 - 1L, x0i - x2i);
      a.setDouble(idx1 - 2L, x0r - x2r);
      a.setDouble(idx1 - 1L, x0i + x2i);
      x0r = x1r + x3i;
      x0i = x1i + x3r;
      a.setDouble(idx2 - 2L, wk1r * x0r - wk1i * x0i);
      a.setDouble(idx2 - 1L, wk1r * x0i + wk1i * x0r);
      x0r = x1r - x3i;
      x0i = x1i - x3r;
      a.setDouble(idx3 - 2L, wk3r * x0r + wk3i * x0i);
      a.setDouble(idx3 - 1L, wk3r * x0i - wk3i * x0r);
      x0r = a.getDouble(idx0) + a.getDouble(idx2);
      x0i = -a.getDouble(idx0 + 1L) - a.getDouble(idx2 + 1L);
      x1r = a.getDouble(idx0) - a.getDouble(idx2);
      x1i = -a.getDouble(idx0 + 1L) + a.getDouble(idx2 + 1L);
      x2r = a.getDouble(idx1) + a.getDouble(idx3);
      x2i = a.getDouble(idx1 + 1L) + a.getDouble(idx3 + 1L);
      x3r = a.getDouble(idx1) - a.getDouble(idx3);
      x3i = a.getDouble(idx1 + 1L) - a.getDouble(idx3 + 1L);
      a.setDouble(idx0, x0r + x2r);
      a.setDouble(idx0 + 1L, x0i - x2i);
      a.setDouble(idx1, x0r - x2r);
      a.setDouble(idx1 + 1L, x0i + x2i);
      x0r = x1r + x3i;
      x0i = x1i + x3r;
      a.setDouble(idx2, wn4r * (x0r - x0i));
      a.setDouble(idx2 + 1L, wn4r * (x0i + x0r));
      x0r = x1r - x3i;
      x0i = x1i - x3r;
      a.setDouble(idx3, -wn4r * (x0r + x0i));
      a.setDouble(idx3 + 1L, -wn4r * (x0i - x0r));
      x0r = a.getDouble(idx0 + 2L) + a.getDouble(idx2 + 2L);
      x0i = -a.getDouble(idx0 + 3L) - a.getDouble(idx2 + 3L);
      x1r = a.getDouble(idx0 + 2L) - a.getDouble(idx2 + 2L);
      x1i = -a.getDouble(idx0 + 3L) + a.getDouble(idx2 + 3L);
      x2r = a.getDouble(idx1 + 2L) + a.getDouble(idx3 + 2L);
      x2i = a.getDouble(idx1 + 3L) + a.getDouble(idx3 + 3L);
      x3r = a.getDouble(idx1 + 2L) - a.getDouble(idx3 + 2L);
      x3i = a.getDouble(idx1 + 3L) - a.getDouble(idx3 + 3L);
      a.setDouble(idx0 + 2L, x0r + x2r);
      a.setDouble(idx0 + 3L, x0i - x2i);
      a.setDouble(idx1 + 2L, x0r - x2r);
      a.setDouble(idx1 + 3L, x0i + x2i);
      x0r = x1r + x3i;
      x0i = x1i + x3r;
      a.setDouble(idx2 + 2L, wk1i * x0r - wk1r * x0i);
      a.setDouble(idx2 + 3L, wk1i * x0i + wk1r * x0r);
      x0r = x1r - x3i;
      x0i = x1i - x3r;
      a.setDouble(idx3 + 2L, wk3i * x0r + wk3r * x0i);
      a.setDouble(idx3 + 3L, wk3i * x0i - wk3r * x0r);
   }

   public static void cftrec4_th(final int n, final double[] a, int offa, final int nw, final double[] w) {
      int idx = 0;
      int nthreads = 2;
      int idiv4 = 0;
      int m = n >> 1;
      if ((long)n >= getThreadsBeginN_1D_FFT_4Threads()) {
         nthreads = 4;
         idiv4 = 1;
         m >>= 1;
      }

      Future<?>[] futures = new Future[nthreads];
      final int mf = m;

      for(int i = 0; i < nthreads; ++i) {
         final int firstIdx = offa + i * m;
         if (i != idiv4) {
            futures[idx++] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  int idx1 = firstIdx + mf;
                  int m = n;

                  while(m > 512) {
                     m >>= 2;
                     CommonUtils.cftmdl1(m, a, idx1 - m, w, nw - (m >> 1));
                  }

                  CommonUtils.cftleaf(m, 1, (double[])a, idx1 - m, nw, (double[])w);
                  int k = 0;
                  int idx2 = firstIdx - m;

                  for(int j = mf - m; j > 0; j -= m) {
                     ++k;
                     int isplt = CommonUtils.cfttree(m, j, k, a, firstIdx, nw, w);
                     CommonUtils.cftleaf(m, isplt, a, idx2 + j, nw, w);
                  }

               }
            });
         } else {
            futures[idx++] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  int idx1 = firstIdx + mf;
                  int k = 1;
                  int m = n;

                  while(m > 512) {
                     m >>= 2;
                     k <<= 2;
                     CommonUtils.cftmdl2(m, a, idx1 - m, w, nw - m);
                  }

                  CommonUtils.cftleaf(m, 0, (double[])a, idx1 - m, nw, (double[])w);
                  k >>= 1;
                  int idx2 = firstIdx - m;

                  for(int j = mf - m; j > 0; j -= m) {
                     ++k;
                     int isplt = CommonUtils.cfttree(m, j, k, a, firstIdx, nw, w);
                     CommonUtils.cftleaf(m, isplt, a, idx2 + j, nw, w);
                  }

               }
            });
         }
      }

      try {
         ConcurrencyUtils.waitForCompletion(futures);
      } catch (InterruptedException ex) {
         Logger.getLogger(CommonUtils.class.getName()).log(Level.SEVERE, (String)null, ex);
      } catch (ExecutionException ex) {
         Logger.getLogger(CommonUtils.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

   }

   public static void cftrec4_th(final long n, final DoubleLargeArray a, long offa, final long nw, final DoubleLargeArray w) {
      int idx = 0;
      int nthreads = 2;
      int idiv4 = 0;
      long m = n >> 1;
      if (n >= getThreadsBeginN_1D_FFT_4Threads()) {
         nthreads = 4;
         idiv4 = 1;
         m >>= 1;
      }

      Future<?>[] futures = new Future[nthreads];
      final long mf = m;

      for(int i = 0; i < nthreads; ++i) {
         final long firstIdx = offa + (long)i * m;
         if (i != idiv4) {
            futures[idx++] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  long idx1 = firstIdx + mf;
                  long m = n;

                  while(m > 512L) {
                     m >>= 2;
                     CommonUtils.cftmdl1(m, a, idx1 - m, w, nw - (m >> 1));
                  }

                  CommonUtils.cftleaf(m, 1L, a, idx1 - m, nw, w);
                  long k = 0L;
                  long idx2 = firstIdx - m;

                  for(long j = mf - m; j > 0L; j -= m) {
                     ++k;
                     long isplt = CommonUtils.cfttree(m, j, k, a, firstIdx, nw, w);
                     CommonUtils.cftleaf(m, isplt, a, idx2 + j, nw, w);
                  }

               }
            });
         } else {
            futures[idx++] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  // $FF: Couldn't be decompiled
               }
            });
         }
      }

      try {
         ConcurrencyUtils.waitForCompletion(futures);
      } catch (InterruptedException ex) {
         Logger.getLogger(CommonUtils.class.getName()).log(Level.SEVERE, (String)null, ex);
      } catch (ExecutionException ex) {
         Logger.getLogger(CommonUtils.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

   }

   public static void cftrec4(int n, double[] a, int offa, int nw, double[] w) {
      int m = n;
      int idx1 = offa + n;

      while(m > 512) {
         m >>= 2;
         cftmdl1(m, a, idx1 - m, w, nw - (m >> 1));
      }

      cftleaf(m, 1, (double[])a, idx1 - m, nw, (double[])w);
      int k = 0;
      int idx2 = offa - m;

      for(int j = n - m; j > 0; j -= m) {
         ++k;
         int isplt = cfttree(m, j, k, a, offa, nw, w);
         cftleaf(m, isplt, a, idx2 + j, nw, w);
      }

   }

   public static void cftrec4(long n, DoubleLargeArray a, long offa, long nw, DoubleLargeArray w) {
      long m = n;
      long idx1 = offa + n;

      while(m > 512L) {
         m >>= 2;
         cftmdl1(m, a, idx1 - m, w, nw - (m >> 1));
      }

      cftleaf(m, 1L, a, idx1 - m, nw, w);
      long k = 0L;
      long idx2 = offa - m;

      for(long j = n - m; j > 0L; j -= m) {
         ++k;
         long isplt = cfttree(m, j, k, a, offa, nw, w);
         cftleaf(m, isplt, a, idx2 + j, nw, w);
      }

   }

   public static int cfttree(int n, int j, int k, double[] a, int offa, int nw, double[] w) {
      int idx1 = offa - n;
      int isplt;
      if ((k & 3) != 0) {
         isplt = k & 1;
         if (isplt != 0) {
            cftmdl1(n, a, idx1 + j, w, nw - (n >> 1));
         } else {
            cftmdl2(n, a, idx1 + j, w, nw - n);
         }
      } else {
         int m = n;

         int i;
         for(i = k; (i & 3) == 0; i >>= 2) {
            m <<= 2;
         }

         isplt = i & 1;
         int idx2 = offa + j;
         if (isplt != 0) {
            while(m > 128) {
               cftmdl1(m, a, idx2 - m, w, nw - (m >> 1));
               m >>= 2;
            }
         } else {
            while(m > 128) {
               cftmdl2(m, a, idx2 - m, w, nw - m);
               m >>= 2;
            }
         }
      }

      return isplt;
   }

   public static long cfttree(long n, long j, long k, DoubleLargeArray a, long offa, long nw, DoubleLargeArray w) {
      long idx1 = offa - n;
      long isplt;
      if ((k & 3L) != 0L) {
         isplt = k & 1L;
         if (isplt != 0L) {
            cftmdl1(n, a, idx1 + j, w, nw - (n >> 1));
         } else {
            cftmdl2(n, a, idx1 + j, w, nw - n);
         }
      } else {
         long m = n;

         long i;
         for(i = k; (i & 3L) == 0L; i >>= 2) {
            m <<= 2;
         }

         isplt = i & 1L;
         long idx2 = offa + j;
         if (isplt != 0L) {
            while(m > 128L) {
               cftmdl1(m, a, idx2 - m, w, nw - (m >> 1));
               m >>= 2;
            }
         } else {
            while(m > 128L) {
               cftmdl2(m, a, idx2 - m, w, nw - m);
               m >>= 2;
            }
         }
      }

      return isplt;
   }

   public static void cftleaf(int n, int isplt, double[] a, int offa, int nw, double[] w) {
      if (n == 512) {
         cftmdl1(128, (double[])a, offa, (double[])w, nw - 64);
         cftf161(a, offa, w, nw - 8);
         cftf162(a, offa + 32, w, nw - 32);
         cftf161(a, offa + 64, w, nw - 8);
         cftf161(a, offa + 96, w, nw - 8);
         cftmdl2(128, (double[])a, offa + 128, (double[])w, nw - 128);
         cftf161(a, offa + 128, w, nw - 8);
         cftf162(a, offa + 160, w, nw - 32);
         cftf161(a, offa + 192, w, nw - 8);
         cftf162(a, offa + 224, w, nw - 32);
         cftmdl1(128, (double[])a, offa + 256, (double[])w, nw - 64);
         cftf161(a, offa + 256, w, nw - 8);
         cftf162(a, offa + 288, w, nw - 32);
         cftf161(a, offa + 320, w, nw - 8);
         cftf161(a, offa + 352, w, nw - 8);
         if (isplt != 0) {
            cftmdl1(128, (double[])a, offa + 384, (double[])w, nw - 64);
            cftf161(a, offa + 480, w, nw - 8);
         } else {
            cftmdl2(128, (double[])a, offa + 384, (double[])w, nw - 128);
            cftf162(a, offa + 480, w, nw - 32);
         }

         cftf161(a, offa + 384, w, nw - 8);
         cftf162(a, offa + 416, w, nw - 32);
         cftf161(a, offa + 448, w, nw - 8);
      } else {
         cftmdl1(64, (double[])a, offa, (double[])w, nw - 32);
         cftf081(a, offa, w, nw - 8);
         cftf082(a, offa + 16, w, nw - 8);
         cftf081(a, offa + 32, w, nw - 8);
         cftf081(a, offa + 48, w, nw - 8);
         cftmdl2(64, (double[])a, offa + 64, (double[])w, nw - 64);
         cftf081(a, offa + 64, w, nw - 8);
         cftf082(a, offa + 80, w, nw - 8);
         cftf081(a, offa + 96, w, nw - 8);
         cftf082(a, offa + 112, w, nw - 8);
         cftmdl1(64, (double[])a, offa + 128, (double[])w, nw - 32);
         cftf081(a, offa + 128, w, nw - 8);
         cftf082(a, offa + 144, w, nw - 8);
         cftf081(a, offa + 160, w, nw - 8);
         cftf081(a, offa + 176, w, nw - 8);
         if (isplt != 0) {
            cftmdl1(64, (double[])a, offa + 192, (double[])w, nw - 32);
            cftf081(a, offa + 240, w, nw - 8);
         } else {
            cftmdl2(64, (double[])a, offa + 192, (double[])w, nw - 64);
            cftf082(a, offa + 240, w, nw - 8);
         }

         cftf081(a, offa + 192, w, nw - 8);
         cftf082(a, offa + 208, w, nw - 8);
         cftf081(a, offa + 224, w, nw - 8);
      }

   }

   public static void cftleaf(long n, long isplt, DoubleLargeArray a, long offa, long nw, DoubleLargeArray w) {
      if (n == 512L) {
         cftmdl1(128L, a, offa, w, nw - 64L);
         cftf161(a, offa, w, nw - 8L);
         cftf162(a, offa + 32L, w, nw - 32L);
         cftf161(a, offa + 64L, w, nw - 8L);
         cftf161(a, offa + 96L, w, nw - 8L);
         cftmdl2(128L, a, offa + 128L, w, nw - 128L);
         cftf161(a, offa + 128L, w, nw - 8L);
         cftf162(a, offa + 160L, w, nw - 32L);
         cftf161(a, offa + 192L, w, nw - 8L);
         cftf162(a, offa + 224L, w, nw - 32L);
         cftmdl1(128L, a, offa + 256L, w, nw - 64L);
         cftf161(a, offa + 256L, w, nw - 8L);
         cftf162(a, offa + 288L, w, nw - 32L);
         cftf161(a, offa + 320L, w, nw - 8L);
         cftf161(a, offa + 352L, w, nw - 8L);
         if (isplt != 0L) {
            cftmdl1(128L, a, offa + 384L, w, nw - 64L);
            cftf161(a, offa + 480L, w, nw - 8L);
         } else {
            cftmdl2(128L, a, offa + 384L, w, nw - 128L);
            cftf162(a, offa + 480L, w, nw - 32L);
         }

         cftf161(a, offa + 384L, w, nw - 8L);
         cftf162(a, offa + 416L, w, nw - 32L);
         cftf161(a, offa + 448L, w, nw - 8L);
      } else {
         cftmdl1(64L, a, offa, w, nw - 32L);
         cftf081(a, offa, w, nw - 8L);
         cftf082(a, offa + 16L, w, nw - 8L);
         cftf081(a, offa + 32L, w, nw - 8L);
         cftf081(a, offa + 48L, w, nw - 8L);
         cftmdl2(64L, a, offa + 64L, w, nw - 64L);
         cftf081(a, offa + 64L, w, nw - 8L);
         cftf082(a, offa + 80L, w, nw - 8L);
         cftf081(a, offa + 96L, w, nw - 8L);
         cftf082(a, offa + 112L, w, nw - 8L);
         cftmdl1(64L, a, offa + 128L, w, nw - 32L);
         cftf081(a, offa + 128L, w, nw - 8L);
         cftf082(a, offa + 144L, w, nw - 8L);
         cftf081(a, offa + 160L, w, nw - 8L);
         cftf081(a, offa + 176L, w, nw - 8L);
         if (isplt != 0L) {
            cftmdl1(64L, a, offa + 192L, w, nw - 32L);
            cftf081(a, offa + 240L, w, nw - 8L);
         } else {
            cftmdl2(64L, a, offa + 192L, w, nw - 64L);
            cftf082(a, offa + 240L, w, nw - 8L);
         }

         cftf081(a, offa + 192L, w, nw - 8L);
         cftf082(a, offa + 208L, w, nw - 8L);
         cftf081(a, offa + 224L, w, nw - 8L);
      }

   }

   public static void cftmdl1(int n, double[] a, int offa, double[] w, int startw) {
      int mh = n >> 3;
      int m = 2 * mh;
      int j2 = m + m;
      int j3 = j2 + m;
      int idx1 = offa + m;
      int idx2 = offa + j2;
      int idx3 = offa + j3;
      double x0r = a[offa] + a[idx2];
      double x0i = a[offa + 1] + a[idx2 + 1];
      double x1r = a[offa] - a[idx2];
      double x1i = a[offa + 1] - a[idx2 + 1];
      double x2r = a[idx1] + a[idx3];
      double x2i = a[idx1 + 1] + a[idx3 + 1];
      double x3r = a[idx1] - a[idx3];
      double x3i = a[idx1 + 1] - a[idx3 + 1];
      a[offa] = x0r + x2r;
      a[offa + 1] = x0i + x2i;
      a[idx1] = x0r - x2r;
      a[idx1 + 1] = x0i - x2i;
      a[idx2] = x1r - x3i;
      a[idx2 + 1] = x1i + x3r;
      a[idx3] = x1r + x3i;
      a[idx3 + 1] = x1i - x3r;
      double wn4r = w[startw + 1];
      int k = 0;

      for(int j = 2; j < mh; j += 2) {
         k += 4;
         int idx4 = startw + k;
         double wk1r = w[idx4];
         double wk1i = w[idx4 + 1];
         double wk3r = w[idx4 + 2];
         double wk3i = w[idx4 + 3];
         int j1 = j + m;
         j2 = j1 + m;
         j3 = j2 + m;
         idx1 = offa + j1;
         idx2 = offa + j2;
         idx3 = offa + j3;
         int idx5 = offa + j;
         x0r = a[idx5] + a[idx2];
         x0i = a[idx5 + 1] + a[idx2 + 1];
         x1r = a[idx5] - a[idx2];
         x1i = a[idx5 + 1] - a[idx2 + 1];
         x2r = a[idx1] + a[idx3];
         x2i = a[idx1 + 1] + a[idx3 + 1];
         x3r = a[idx1] - a[idx3];
         x3i = a[idx1 + 1] - a[idx3 + 1];
         a[idx5] = x0r + x2r;
         a[idx5 + 1] = x0i + x2i;
         a[idx1] = x0r - x2r;
         a[idx1 + 1] = x0i - x2i;
         x0r = x1r - x3i;
         x0i = x1i + x3r;
         a[idx2] = wk1r * x0r - wk1i * x0i;
         a[idx2 + 1] = wk1r * x0i + wk1i * x0r;
         x0r = x1r + x3i;
         x0i = x1i - x3r;
         a[idx3] = wk3r * x0r + wk3i * x0i;
         a[idx3 + 1] = wk3r * x0i - wk3i * x0r;
         int j0 = m - j;
         j1 = j0 + m;
         j2 = j1 + m;
         j3 = j2 + m;
         int idx0 = offa + j0;
         idx1 = offa + j1;
         idx2 = offa + j2;
         idx3 = offa + j3;
         x0r = a[idx0] + a[idx2];
         x0i = a[idx0 + 1] + a[idx2 + 1];
         x1r = a[idx0] - a[idx2];
         x1i = a[idx0 + 1] - a[idx2 + 1];
         x2r = a[idx1] + a[idx3];
         x2i = a[idx1 + 1] + a[idx3 + 1];
         x3r = a[idx1] - a[idx3];
         x3i = a[idx1 + 1] - a[idx3 + 1];
         a[idx0] = x0r + x2r;
         a[idx0 + 1] = x0i + x2i;
         a[idx1] = x0r - x2r;
         a[idx1 + 1] = x0i - x2i;
         x0r = x1r - x3i;
         x0i = x1i + x3r;
         a[idx2] = wk1i * x0r - wk1r * x0i;
         a[idx2 + 1] = wk1i * x0i + wk1r * x0r;
         x0r = x1r + x3i;
         x0i = x1i - x3r;
         a[idx3] = wk3i * x0r + wk3r * x0i;
         a[idx3 + 1] = wk3i * x0i - wk3r * x0r;
      }

      int var46 = mh + m;
      j2 = var46 + m;
      j3 = j2 + m;
      int idx0 = offa + mh;
      idx1 = offa + var46;
      idx2 = offa + j2;
      idx3 = offa + j3;
      x0r = a[idx0] + a[idx2];
      x0i = a[idx0 + 1] + a[idx2 + 1];
      x1r = a[idx0] - a[idx2];
      x1i = a[idx0 + 1] - a[idx2 + 1];
      x2r = a[idx1] + a[idx3];
      x2i = a[idx1 + 1] + a[idx3 + 1];
      x3r = a[idx1] - a[idx3];
      x3i = a[idx1 + 1] - a[idx3 + 1];
      a[idx0] = x0r + x2r;
      a[idx0 + 1] = x0i + x2i;
      a[idx1] = x0r - x2r;
      a[idx1 + 1] = x0i - x2i;
      x0r = x1r - x3i;
      x0i = x1i + x3r;
      a[idx2] = wn4r * (x0r - x0i);
      a[idx2 + 1] = wn4r * (x0i + x0r);
      x0r = x1r + x3i;
      x0i = x1i - x3r;
      a[idx3] = -wn4r * (x0r + x0i);
      a[idx3 + 1] = -wn4r * (x0i - x0r);
   }

   public static void cftmdl1(long n, DoubleLargeArray a, long offa, DoubleLargeArray w, long startw) {
      long mh = n >> 3;
      long m = 2L * mh;
      long j2 = m + m;
      long j3 = j2 + m;
      long idx1 = offa + m;
      long idx2 = offa + j2;
      long idx3 = offa + j3;
      double x0r = a.getDouble(offa) + a.getDouble(idx2);
      double x0i = a.getDouble(offa + 1L) + a.getDouble(idx2 + 1L);
      double x1r = a.getDouble(offa) - a.getDouble(idx2);
      double x1i = a.getDouble(offa + 1L) - a.getDouble(idx2 + 1L);
      double x2r = a.getDouble(idx1) + a.getDouble(idx3);
      double x2i = a.getDouble(idx1 + 1L) + a.getDouble(idx3 + 1L);
      double x3r = a.getDouble(idx1) - a.getDouble(idx3);
      double x3i = a.getDouble(idx1 + 1L) - a.getDouble(idx3 + 1L);
      a.setDouble(offa, x0r + x2r);
      a.setDouble(offa + 1L, x0i + x2i);
      a.setDouble(idx1, x0r - x2r);
      a.setDouble(idx1 + 1L, x0i - x2i);
      a.setDouble(idx2, x1r - x3i);
      a.setDouble(idx2 + 1L, x1i + x3r);
      a.setDouble(idx3, x1r + x3i);
      a.setDouble(idx3 + 1L, x1i - x3r);
      double wn4r = w.getDouble(startw + 1L);
      long k = 0L;

      for(long j = 2L; j < mh; j += 2L) {
         k += 4L;
         long idx4 = startw + k;
         double wk1r = w.getDouble(idx4);
         double wk1i = w.getDouble(idx4 + 1L);
         double wk3r = w.getDouble(idx4 + 2L);
         double wk3i = w.getDouble(idx4 + 3L);
         long j1 = j + m;
         j2 = j1 + m;
         j3 = j2 + m;
         idx1 = offa + j1;
         idx2 = offa + j2;
         idx3 = offa + j3;
         long idx5 = offa + j;
         x0r = a.getDouble(idx5) + a.getDouble(idx2);
         x0i = a.getDouble(idx5 + 1L) + a.getDouble(idx2 + 1L);
         x1r = a.getDouble(idx5) - a.getDouble(idx2);
         x1i = a.getDouble(idx5 + 1L) - a.getDouble(idx2 + 1L);
         x2r = a.getDouble(idx1) + a.getDouble(idx3);
         x2i = a.getDouble(idx1 + 1L) + a.getDouble(idx3 + 1L);
         x3r = a.getDouble(idx1) - a.getDouble(idx3);
         x3i = a.getDouble(idx1 + 1L) - a.getDouble(idx3 + 1L);
         a.setDouble(idx5, x0r + x2r);
         a.setDouble(idx5 + 1L, x0i + x2i);
         a.setDouble(idx1, x0r - x2r);
         a.setDouble(idx1 + 1L, x0i - x2i);
         x0r = x1r - x3i;
         x0i = x1i + x3r;
         a.setDouble(idx2, wk1r * x0r - wk1i * x0i);
         a.setDouble(idx2 + 1L, wk1r * x0i + wk1i * x0r);
         x0r = x1r + x3i;
         x0i = x1i - x3r;
         a.setDouble(idx3, wk3r * x0r + wk3i * x0i);
         a.setDouble(idx3 + 1L, wk3r * x0i - wk3i * x0r);
         long j0 = m - j;
         j1 = j0 + m;
         j2 = j1 + m;
         j3 = j2 + m;
         long idx0 = offa + j0;
         idx1 = offa + j1;
         idx2 = offa + j2;
         idx3 = offa + j3;
         x0r = a.getDouble(idx0) + a.getDouble(idx2);
         x0i = a.getDouble(idx0 + 1L) + a.getDouble(idx2 + 1L);
         x1r = a.getDouble(idx0) - a.getDouble(idx2);
         x1i = a.getDouble(idx0 + 1L) - a.getDouble(idx2 + 1L);
         x2r = a.getDouble(idx1) + a.getDouble(idx3);
         x2i = a.getDouble(idx1 + 1L) + a.getDouble(idx3 + 1L);
         x3r = a.getDouble(idx1) - a.getDouble(idx3);
         x3i = a.getDouble(idx1 + 1L) - a.getDouble(idx3 + 1L);
         a.setDouble(idx0, x0r + x2r);
         a.setDouble(idx0 + 1L, x0i + x2i);
         a.setDouble(idx1, x0r - x2r);
         a.setDouble(idx1 + 1L, x0i - x2i);
         x0r = x1r - x3i;
         x0i = x1i + x3r;
         a.setDouble(idx2, wk1i * x0r - wk1r * x0i);
         a.setDouble(idx2 + 1L, wk1i * x0i + wk1r * x0r);
         x0r = x1r + x3i;
         x0i = x1i - x3r;
         a.setDouble(idx3, wk3i * x0r + wk3r * x0i);
         a.setDouble(idx3 + 1L, wk3i * x0i - wk3r * x0r);
      }

      long var63 = mh + m;
      j2 = var63 + m;
      j3 = j2 + m;
      long idx0 = offa + mh;
      idx1 = offa + var63;
      idx2 = offa + j2;
      idx3 = offa + j3;
      x0r = a.getDouble(idx0) + a.getDouble(idx2);
      x0i = a.getDouble(idx0 + 1L) + a.getDouble(idx2 + 1L);
      x1r = a.getDouble(idx0) - a.getDouble(idx2);
      x1i = a.getDouble(idx0 + 1L) - a.getDouble(idx2 + 1L);
      x2r = a.getDouble(idx1) + a.getDouble(idx3);
      x2i = a.getDouble(idx1 + 1L) + a.getDouble(idx3 + 1L);
      x3r = a.getDouble(idx1) - a.getDouble(idx3);
      x3i = a.getDouble(idx1 + 1L) - a.getDouble(idx3 + 1L);
      a.setDouble(idx0, x0r + x2r);
      a.setDouble(idx0 + 1L, x0i + x2i);
      a.setDouble(idx1, x0r - x2r);
      a.setDouble(idx1 + 1L, x0i - x2i);
      x0r = x1r - x3i;
      x0i = x1i + x3r;
      a.setDouble(idx2, wn4r * (x0r - x0i));
      a.setDouble(idx2 + 1L, wn4r * (x0i + x0r));
      x0r = x1r + x3i;
      x0i = x1i - x3r;
      a.setDouble(idx3, -wn4r * (x0r + x0i));
      a.setDouble(idx3 + 1L, -wn4r * (x0i - x0r));
   }

   public static void cftmdl2(int n, double[] a, int offa, double[] w, int startw) {
      int mh = n >> 3;
      int m = 2 * mh;
      double wn4r = w[startw + 1];
      int j2 = m + m;
      int j3 = j2 + m;
      int idx1 = offa + m;
      int idx2 = offa + j2;
      int idx3 = offa + j3;
      double x0r = a[offa] - a[idx2 + 1];
      double x0i = a[offa + 1] + a[idx2];
      double x1r = a[offa] + a[idx2 + 1];
      double x1i = a[offa + 1] - a[idx2];
      double x2r = a[idx1] - a[idx3 + 1];
      double x2i = a[idx1 + 1] + a[idx3];
      double x3r = a[idx1] + a[idx3 + 1];
      double x3i = a[idx1 + 1] - a[idx3];
      double y0r = wn4r * (x2r - x2i);
      double y0i = wn4r * (x2i + x2r);
      a[offa] = x0r + y0r;
      a[offa + 1] = x0i + y0i;
      a[idx1] = x0r - y0r;
      a[idx1 + 1] = x0i - y0i;
      y0r = wn4r * (x3r - x3i);
      y0i = wn4r * (x3i + x3r);
      a[idx2] = x1r - y0i;
      a[idx2 + 1] = x1i + y0r;
      a[idx3] = x1r + y0i;
      a[idx3 + 1] = x1i - y0r;
      int k = 0;
      int kr = 2 * m;

      for(int j = 2; j < mh; j += 2) {
         k += 4;
         int idx4 = startw + k;
         double wk1r = w[idx4];
         double wk1i = w[idx4 + 1];
         double wk3r = w[idx4 + 2];
         double wk3i = w[idx4 + 3];
         kr -= 4;
         int idx5 = startw + kr;
         double wd1i = w[idx5];
         double wd1r = w[idx5 + 1];
         double wd3i = w[idx5 + 2];
         double wd3r = w[idx5 + 3];
         int j1 = j + m;
         j2 = j1 + m;
         j3 = j2 + m;
         idx1 = offa + j1;
         idx2 = offa + j2;
         idx3 = offa + j3;
         int idx6 = offa + j;
         x0r = a[idx6] - a[idx2 + 1];
         x0i = a[idx6 + 1] + a[idx2];
         x1r = a[idx6] + a[idx2 + 1];
         x1i = a[idx6 + 1] - a[idx2];
         x2r = a[idx1] - a[idx3 + 1];
         x2i = a[idx1 + 1] + a[idx3];
         x3r = a[idx1] + a[idx3 + 1];
         x3i = a[idx1 + 1] - a[idx3];
         y0r = wk1r * x0r - wk1i * x0i;
         y0i = wk1r * x0i + wk1i * x0r;
         double y2r = wd1r * x2r - wd1i * x2i;
         double y2i = wd1r * x2i + wd1i * x2r;
         a[idx6] = y0r + y2r;
         a[idx6 + 1] = y0i + y2i;
         a[idx1] = y0r - y2r;
         a[idx1 + 1] = y0i - y2i;
         y0r = wk3r * x1r + wk3i * x1i;
         y0i = wk3r * x1i - wk3i * x1r;
         y2r = wd3r * x3r + wd3i * x3i;
         y2i = wd3r * x3i - wd3i * x3r;
         a[idx2] = y0r + y2r;
         a[idx2 + 1] = y0i + y2i;
         a[idx3] = y0r - y2r;
         a[idx3 + 1] = y0i - y2i;
         int j0 = m - j;
         j1 = j0 + m;
         j2 = j1 + m;
         j3 = j2 + m;
         int idx0 = offa + j0;
         idx1 = offa + j1;
         idx2 = offa + j2;
         idx3 = offa + j3;
         x0r = a[idx0] - a[idx2 + 1];
         x0i = a[idx0 + 1] + a[idx2];
         x1r = a[idx0] + a[idx2 + 1];
         x1i = a[idx0 + 1] - a[idx2];
         x2r = a[idx1] - a[idx3 + 1];
         x2i = a[idx1 + 1] + a[idx3];
         x3r = a[idx1] + a[idx3 + 1];
         x3i = a[idx1 + 1] - a[idx3];
         y0r = wd1i * x0r - wd1r * x0i;
         y0i = wd1i * x0i + wd1r * x0r;
         y2r = wk1i * x2r - wk1r * x2i;
         y2i = wk1i * x2i + wk1r * x2r;
         a[idx0] = y0r + y2r;
         a[idx0 + 1] = y0i + y2i;
         a[idx1] = y0r - y2r;
         a[idx1 + 1] = y0i - y2i;
         y0r = wd3i * x1r + wd3r * x1i;
         y0i = wd3i * x1i - wd3r * x1r;
         y2r = wk3i * x3r + wk3r * x3i;
         y2i = wk3i * x3i - wk3r * x3r;
         a[idx2] = y0r + y2r;
         a[idx2 + 1] = y0i + y2i;
         a[idx3] = y0r - y2r;
         a[idx3 + 1] = y0i - y2i;
      }

      double wk1r = w[startw + m];
      double wk1i = w[startw + m + 1];
      int var64 = mh + m;
      j2 = var64 + m;
      j3 = j2 + m;
      int idx0 = offa + mh;
      idx1 = offa + var64;
      idx2 = offa + j2;
      idx3 = offa + j3;
      x0r = a[idx0] - a[idx2 + 1];
      x0i = a[idx0 + 1] + a[idx2];
      x1r = a[idx0] + a[idx2 + 1];
      x1i = a[idx0 + 1] - a[idx2];
      x2r = a[idx1] - a[idx3 + 1];
      x2i = a[idx1 + 1] + a[idx3];
      x3r = a[idx1] + a[idx3 + 1];
      x3i = a[idx1 + 1] - a[idx3];
      y0r = wk1r * x0r - wk1i * x0i;
      y0i = wk1r * x0i + wk1i * x0r;
      double y2r = wk1i * x2r - wk1r * x2i;
      double y2i = wk1i * x2i + wk1r * x2r;
      a[idx0] = y0r + y2r;
      a[idx0 + 1] = y0i + y2i;
      a[idx1] = y0r - y2r;
      a[idx1 + 1] = y0i - y2i;
      y0r = wk1i * x1r - wk1r * x1i;
      y0i = wk1i * x1i + wk1r * x1r;
      y2r = wk1r * x3r - wk1i * x3i;
      y2i = wk1r * x3i + wk1i * x3r;
      a[idx2] = y0r - y2r;
      a[idx2 + 1] = y0i - y2i;
      a[idx3] = y0r + y2r;
      a[idx3 + 1] = y0i + y2i;
   }

   public static void cftmdl2(long n, DoubleLargeArray a, long offa, DoubleLargeArray w, long startw) {
      long mh = n >> 3;
      long m = 2L * mh;
      double wn4r = w.getDouble(startw + 1L);
      long j2 = m + m;
      long j3 = j2 + m;
      long idx1 = offa + m;
      long idx2 = offa + j2;
      long idx3 = offa + j3;
      double x0r = a.getDouble(offa) - a.getDouble(idx2 + 1L);
      double x0i = a.getDouble(offa + 1L) + a.getDouble(idx2);
      double x1r = a.getDouble(offa) + a.getDouble(idx2 + 1L);
      double x1i = a.getDouble(offa + 1L) - a.getDouble(idx2);
      double x2r = a.getDouble(idx1) - a.getDouble(idx3 + 1L);
      double x2i = a.getDouble(idx1 + 1L) + a.getDouble(idx3);
      double x3r = a.getDouble(idx1) + a.getDouble(idx3 + 1L);
      double x3i = a.getDouble(idx1 + 1L) - a.getDouble(idx3);
      double y0r = wn4r * (x2r - x2i);
      double y0i = wn4r * (x2i + x2r);
      a.setDouble(offa, x0r + y0r);
      a.setDouble(offa + 1L, x0i + y0i);
      a.setDouble(idx1, x0r - y0r);
      a.setDouble(idx1 + 1L, x0i - y0i);
      y0r = wn4r * (x3r - x3i);
      y0i = wn4r * (x3i + x3r);
      a.setDouble(idx2, x1r - y0i);
      a.setDouble(idx2 + 1L, x1i + y0r);
      a.setDouble(idx3, x1r + y0i);
      a.setDouble(idx3 + 1L, x1i - y0r);
      long k = 0L;
      long kr = 2L * m;

      for(int j = 2; (long)j < mh; j += 2) {
         k += 4L;
         long idx4 = startw + k;
         double wk1r = w.getDouble(idx4);
         double wk1i = w.getDouble(idx4 + 1L);
         double wk3r = w.getDouble(idx4 + 2L);
         double wk3i = w.getDouble(idx4 + 3L);
         kr -= 4L;
         long idx5 = startw + kr;
         double wd1i = w.getDouble(idx5);
         double wd1r = w.getDouble(idx5 + 1L);
         double wd3i = w.getDouble(idx5 + 2L);
         double wd3r = w.getDouble(idx5 + 3L);
         long j1 = (long)j + m;
         j2 = j1 + m;
         j3 = j2 + m;
         idx1 = offa + j1;
         idx2 = offa + j2;
         idx3 = offa + j3;
         long idx6 = offa + (long)j;
         x0r = a.getDouble(idx6) - a.getDouble(idx2 + 1L);
         x0i = a.getDouble(idx6 + 1L) + a.getDouble(idx2);
         x1r = a.getDouble(idx6) + a.getDouble(idx2 + 1L);
         x1i = a.getDouble(idx6 + 1L) - a.getDouble(idx2);
         x2r = a.getDouble(idx1) - a.getDouble(idx3 + 1L);
         x2i = a.getDouble(idx1 + 1L) + a.getDouble(idx3);
         x3r = a.getDouble(idx1) + a.getDouble(idx3 + 1L);
         x3i = a.getDouble(idx1 + 1L) - a.getDouble(idx3);
         y0r = wk1r * x0r - wk1i * x0i;
         y0i = wk1r * x0i + wk1i * x0r;
         double y2r = wd1r * x2r - wd1i * x2i;
         double y2i = wd1r * x2i + wd1i * x2r;
         a.setDouble(idx6, y0r + y2r);
         a.setDouble(idx6 + 1L, y0i + y2i);
         a.setDouble(idx1, y0r - y2r);
         a.setDouble(idx1 + 1L, y0i - y2i);
         y0r = wk3r * x1r + wk3i * x1i;
         y0i = wk3r * x1i - wk3i * x1r;
         y2r = wd3r * x3r + wd3i * x3i;
         y2i = wd3r * x3i - wd3i * x3r;
         a.setDouble(idx2, y0r + y2r);
         a.setDouble(idx2 + 1L, y0i + y2i);
         a.setDouble(idx3, y0r - y2r);
         a.setDouble(idx3 + 1L, y0i - y2i);
         long j0 = m - (long)j;
         j1 = j0 + m;
         j2 = j1 + m;
         j3 = j2 + m;
         long idx0 = offa + j0;
         idx1 = offa + j1;
         idx2 = offa + j2;
         idx3 = offa + j3;
         x0r = a.getDouble(idx0) - a.getDouble(idx2 + 1L);
         x0i = a.getDouble(idx0 + 1L) + a.getDouble(idx2);
         x1r = a.getDouble(idx0) + a.getDouble(idx2 + 1L);
         x1i = a.getDouble(idx0 + 1L) - a.getDouble(idx2);
         x2r = a.getDouble(idx1) - a.getDouble(idx3 + 1L);
         x2i = a.getDouble(idx1 + 1L) + a.getDouble(idx3);
         x3r = a.getDouble(idx1) + a.getDouble(idx3 + 1L);
         x3i = a.getDouble(idx1 + 1L) - a.getDouble(idx3);
         y0r = wd1i * x0r - wd1r * x0i;
         y0i = wd1i * x0i + wd1r * x0r;
         y2r = wk1i * x2r - wk1r * x2i;
         y2i = wk1i * x2i + wk1r * x2r;
         a.setDouble(idx0, y0r + y2r);
         a.setDouble(idx0 + 1L, y0i + y2i);
         a.setDouble(idx1, y0r - y2r);
         a.setDouble(idx1 + 1L, y0i - y2i);
         y0r = wd3i * x1r + wd3r * x1i;
         y0i = wd3i * x1i - wd3r * x1r;
         y2r = wk3i * x3r + wk3r * x3i;
         y2i = wk3i * x3i - wk3r * x3r;
         a.setDouble(idx2, y0r + y2r);
         a.setDouble(idx2 + 1L, y0i + y2i);
         a.setDouble(idx3, y0r - y2r);
         a.setDouble(idx3 + 1L, y0i - y2i);
      }

      double wk1r = w.getDouble(startw + m);
      double wk1i = w.getDouble(startw + m + 1L);
      long var82 = mh + m;
      j2 = var82 + m;
      j3 = j2 + m;
      long idx0 = offa + mh;
      idx1 = offa + var82;
      idx2 = offa + j2;
      idx3 = offa + j3;
      x0r = a.getDouble(idx0) - a.getDouble(idx2 + 1L);
      x0i = a.getDouble(idx0 + 1L) + a.getDouble(idx2);
      x1r = a.getDouble(idx0) + a.getDouble(idx2 + 1L);
      x1i = a.getDouble(idx0 + 1L) - a.getDouble(idx2);
      x2r = a.getDouble(idx1) - a.getDouble(idx3 + 1L);
      x2i = a.getDouble(idx1 + 1L) + a.getDouble(idx3);
      x3r = a.getDouble(idx1) + a.getDouble(idx3 + 1L);
      x3i = a.getDouble(idx1 + 1L) - a.getDouble(idx3);
      y0r = wk1r * x0r - wk1i * x0i;
      y0i = wk1r * x0i + wk1i * x0r;
      double y2r = wk1i * x2r - wk1r * x2i;
      double y2i = wk1i * x2i + wk1r * x2r;
      a.setDouble(idx0, y0r + y2r);
      a.setDouble(idx0 + 1L, y0i + y2i);
      a.setDouble(idx1, y0r - y2r);
      a.setDouble(idx1 + 1L, y0i - y2i);
      y0r = wk1i * x1r - wk1r * x1i;
      y0i = wk1i * x1i + wk1r * x1r;
      y2r = wk1r * x3r - wk1i * x3i;
      y2i = wk1r * x3i + wk1i * x3r;
      a.setDouble(idx2, y0r - y2r);
      a.setDouble(idx2 + 1L, y0i - y2i);
      a.setDouble(idx3, y0r + y2r);
      a.setDouble(idx3 + 1L, y0i + y2i);
   }

   public static void cftfx41(int n, double[] a, int offa, int nw, double[] w) {
      if (n == 128) {
         cftf161(a, offa, w, nw - 8);
         cftf162(a, offa + 32, w, nw - 32);
         cftf161(a, offa + 64, w, nw - 8);
         cftf161(a, offa + 96, w, nw - 8);
      } else {
         cftf081(a, offa, w, nw - 8);
         cftf082(a, offa + 16, w, nw - 8);
         cftf081(a, offa + 32, w, nw - 8);
         cftf081(a, offa + 48, w, nw - 8);
      }

   }

   public static void cftfx41(long n, DoubleLargeArray a, long offa, long nw, DoubleLargeArray w) {
      if (n == 128L) {
         cftf161(a, offa, w, nw - 8L);
         cftf162(a, offa + 32L, w, nw - 32L);
         cftf161(a, offa + 64L, w, nw - 8L);
         cftf161(a, offa + 96L, w, nw - 8L);
      } else {
         cftf081(a, offa, w, nw - 8L);
         cftf082(a, offa + 16L, w, nw - 8L);
         cftf081(a, offa + 32L, w, nw - 8L);
         cftf081(a, offa + 48L, w, nw - 8L);
      }

   }

   public static void cftf161(double[] a, int offa, double[] w, int startw) {
      double wn4r = w[startw + 1];
      double wk1r = w[startw + 2];
      double wk1i = w[startw + 3];
      double x0r = a[offa] + a[offa + 16];
      double x0i = a[offa + 1] + a[offa + 17];
      double x1r = a[offa] - a[offa + 16];
      double x1i = a[offa + 1] - a[offa + 17];
      double x2r = a[offa + 8] + a[offa + 24];
      double x2i = a[offa + 9] + a[offa + 25];
      double x3r = a[offa + 8] - a[offa + 24];
      double x3i = a[offa + 9] - a[offa + 25];
      double y0r = x0r + x2r;
      double y0i = x0i + x2i;
      double y4r = x0r - x2r;
      double y4i = x0i - x2i;
      double y8r = x1r - x3i;
      double y8i = x1i + x3r;
      double y12r = x1r + x3i;
      double y12i = x1i - x3r;
      x0r = a[offa + 2] + a[offa + 18];
      x0i = a[offa + 3] + a[offa + 19];
      x1r = a[offa + 2] - a[offa + 18];
      x1i = a[offa + 3] - a[offa + 19];
      x2r = a[offa + 10] + a[offa + 26];
      x2i = a[offa + 11] + a[offa + 27];
      x3r = a[offa + 10] - a[offa + 26];
      x3i = a[offa + 11] - a[offa + 27];
      double y1r = x0r + x2r;
      double y1i = x0i + x2i;
      double y5r = x0r - x2r;
      double y5i = x0i - x2i;
      x0r = x1r - x3i;
      x0i = x1i + x3r;
      double y9r = wk1r * x0r - wk1i * x0i;
      double y9i = wk1r * x0i + wk1i * x0r;
      x0r = x1r + x3i;
      x0i = x1i - x3r;
      double y13r = wk1i * x0r - wk1r * x0i;
      double y13i = wk1i * x0i + wk1r * x0r;
      x0r = a[offa + 4] + a[offa + 20];
      x0i = a[offa + 5] + a[offa + 21];
      x1r = a[offa + 4] - a[offa + 20];
      x1i = a[offa + 5] - a[offa + 21];
      x2r = a[offa + 12] + a[offa + 28];
      x2i = a[offa + 13] + a[offa + 29];
      x3r = a[offa + 12] - a[offa + 28];
      x3i = a[offa + 13] - a[offa + 29];
      double y2r = x0r + x2r;
      double y2i = x0i + x2i;
      double y6r = x0r - x2r;
      double y6i = x0i - x2i;
      x0r = x1r - x3i;
      x0i = x1i + x3r;
      double y10r = wn4r * (x0r - x0i);
      double y10i = wn4r * (x0i + x0r);
      x0r = x1r + x3i;
      x0i = x1i - x3r;
      double y14r = wn4r * (x0r + x0i);
      double y14i = wn4r * (x0i - x0r);
      x0r = a[offa + 6] + a[offa + 22];
      x0i = a[offa + 7] + a[offa + 23];
      x1r = a[offa + 6] - a[offa + 22];
      x1i = a[offa + 7] - a[offa + 23];
      x2r = a[offa + 14] + a[offa + 30];
      x2i = a[offa + 15] + a[offa + 31];
      x3r = a[offa + 14] - a[offa + 30];
      x3i = a[offa + 15] - a[offa + 31];
      double y3r = x0r + x2r;
      double y3i = x0i + x2i;
      double y7r = x0r - x2r;
      double y7i = x0i - x2i;
      x0r = x1r - x3i;
      x0i = x1i + x3r;
      double y11r = wk1i * x0r - wk1r * x0i;
      double y11i = wk1i * x0i + wk1r * x0r;
      x0r = x1r + x3i;
      x0i = x1i - x3r;
      double y15r = wk1r * x0r - wk1i * x0i;
      double y15i = wk1r * x0i + wk1i * x0r;
      x0r = y12r - y14r;
      x0i = y12i - y14i;
      x1r = y12r + y14r;
      x1i = y12i + y14i;
      x2r = y13r - y15r;
      x2i = y13i - y15i;
      x3r = y13r + y15r;
      x3i = y13i + y15i;
      a[offa + 24] = x0r + x2r;
      a[offa + 25] = x0i + x2i;
      a[offa + 26] = x0r - x2r;
      a[offa + 27] = x0i - x2i;
      a[offa + 28] = x1r - x3i;
      a[offa + 29] = x1i + x3r;
      a[offa + 30] = x1r + x3i;
      a[offa + 31] = x1i - x3r;
      x0r = y8r + y10r;
      x0i = y8i + y10i;
      x1r = y8r - y10r;
      x1i = y8i - y10i;
      x2r = y9r + y11r;
      x2i = y9i + y11i;
      x3r = y9r - y11r;
      x3i = y9i - y11i;
      a[offa + 16] = x0r + x2r;
      a[offa + 17] = x0i + x2i;
      a[offa + 18] = x0r - x2r;
      a[offa + 19] = x0i - x2i;
      a[offa + 20] = x1r - x3i;
      a[offa + 21] = x1i + x3r;
      a[offa + 22] = x1r + x3i;
      a[offa + 23] = x1i - x3r;
      x0r = y5r - y7i;
      x0i = y5i + y7r;
      x2r = wn4r * (x0r - x0i);
      x2i = wn4r * (x0i + x0r);
      x0r = y5r + y7i;
      x0i = y5i - y7r;
      x3r = wn4r * (x0r - x0i);
      x3i = wn4r * (x0i + x0r);
      x0r = y4r - y6i;
      x0i = y4i + y6r;
      x1r = y4r + y6i;
      x1i = y4i - y6r;
      a[offa + 8] = x0r + x2r;
      a[offa + 9] = x0i + x2i;
      a[offa + 10] = x0r - x2r;
      a[offa + 11] = x0i - x2i;
      a[offa + 12] = x1r - x3i;
      a[offa + 13] = x1i + x3r;
      a[offa + 14] = x1r + x3i;
      a[offa + 15] = x1i - x3r;
      x0r = y0r + y2r;
      x0i = y0i + y2i;
      x1r = y0r - y2r;
      x1i = y0i - y2i;
      x2r = y1r + y3r;
      x2i = y1i + y3i;
      x3r = y1r - y3r;
      x3i = y1i - y3i;
      a[offa] = x0r + x2r;
      a[offa + 1] = x0i + x2i;
      a[offa + 2] = x0r - x2r;
      a[offa + 3] = x0i - x2i;
      a[offa + 4] = x1r - x3i;
      a[offa + 5] = x1i + x3r;
      a[offa + 6] = x1r + x3i;
      a[offa + 7] = x1i - x3r;
   }

   public static void cftf161(DoubleLargeArray a, long offa, DoubleLargeArray w, long startw) {
      double wn4r = w.getDouble(startw + 1L);
      double wk1r = w.getDouble(startw + 2L);
      double wk1i = w.getDouble(startw + 3L);
      double x0r = a.getDouble(offa) + a.getDouble(offa + 16L);
      double x0i = a.getDouble(offa + 1L) + a.getDouble(offa + 17L);
      double x1r = a.getDouble(offa) - a.getDouble(offa + 16L);
      double x1i = a.getDouble(offa + 1L) - a.getDouble(offa + 17L);
      double x2r = a.getDouble(offa + 8L) + a.getDouble(offa + 24L);
      double x2i = a.getDouble(offa + 9L) + a.getDouble(offa + 25L);
      double x3r = a.getDouble(offa + 8L) - a.getDouble(offa + 24L);
      double x3i = a.getDouble(offa + 9L) - a.getDouble(offa + 25L);
      double y0r = x0r + x2r;
      double y0i = x0i + x2i;
      double y4r = x0r - x2r;
      double y4i = x0i - x2i;
      double y8r = x1r - x3i;
      double y8i = x1i + x3r;
      double y12r = x1r + x3i;
      double y12i = x1i - x3r;
      x0r = a.getDouble(offa + 2L) + a.getDouble(offa + 18L);
      x0i = a.getDouble(offa + 3L) + a.getDouble(offa + 19L);
      x1r = a.getDouble(offa + 2L) - a.getDouble(offa + 18L);
      x1i = a.getDouble(offa + 3L) - a.getDouble(offa + 19L);
      x2r = a.getDouble(offa + 10L) + a.getDouble(offa + 26L);
      x2i = a.getDouble(offa + 11L) + a.getDouble(offa + 27L);
      x3r = a.getDouble(offa + 10L) - a.getDouble(offa + 26L);
      x3i = a.getDouble(offa + 11L) - a.getDouble(offa + 27L);
      double y1r = x0r + x2r;
      double y1i = x0i + x2i;
      double y5r = x0r - x2r;
      double y5i = x0i - x2i;
      x0r = x1r - x3i;
      x0i = x1i + x3r;
      double y9r = wk1r * x0r - wk1i * x0i;
      double y9i = wk1r * x0i + wk1i * x0r;
      x0r = x1r + x3i;
      x0i = x1i - x3r;
      double y13r = wk1i * x0r - wk1r * x0i;
      double y13i = wk1i * x0i + wk1r * x0r;
      x0r = a.getDouble(offa + 4L) + a.getDouble(offa + 20L);
      x0i = a.getDouble(offa + 5L) + a.getDouble(offa + 21L);
      x1r = a.getDouble(offa + 4L) - a.getDouble(offa + 20L);
      x1i = a.getDouble(offa + 5L) - a.getDouble(offa + 21L);
      x2r = a.getDouble(offa + 12L) + a.getDouble(offa + 28L);
      x2i = a.getDouble(offa + 13L) + a.getDouble(offa + 29L);
      x3r = a.getDouble(offa + 12L) - a.getDouble(offa + 28L);
      x3i = a.getDouble(offa + 13L) - a.getDouble(offa + 29L);
      double y2r = x0r + x2r;
      double y2i = x0i + x2i;
      double y6r = x0r - x2r;
      double y6i = x0i - x2i;
      x0r = x1r - x3i;
      x0i = x1i + x3r;
      double y10r = wn4r * (x0r - x0i);
      double y10i = wn4r * (x0i + x0r);
      x0r = x1r + x3i;
      x0i = x1i - x3r;
      double y14r = wn4r * (x0r + x0i);
      double y14i = wn4r * (x0i - x0r);
      x0r = a.getDouble(offa + 6L) + a.getDouble(offa + 22L);
      x0i = a.getDouble(offa + 7L) + a.getDouble(offa + 23L);
      x1r = a.getDouble(offa + 6L) - a.getDouble(offa + 22L);
      x1i = a.getDouble(offa + 7L) - a.getDouble(offa + 23L);
      x2r = a.getDouble(offa + 14L) + a.getDouble(offa + 30L);
      x2i = a.getDouble(offa + 15L) + a.getDouble(offa + 31L);
      x3r = a.getDouble(offa + 14L) - a.getDouble(offa + 30L);
      x3i = a.getDouble(offa + 15L) - a.getDouble(offa + 31L);
      double y3r = x0r + x2r;
      double y3i = x0i + x2i;
      double y7r = x0r - x2r;
      double y7i = x0i - x2i;
      x0r = x1r - x3i;
      x0i = x1i + x3r;
      double y11r = wk1i * x0r - wk1r * x0i;
      double y11i = wk1i * x0i + wk1r * x0r;
      x0r = x1r + x3i;
      x0i = x1i - x3r;
      double y15r = wk1r * x0r - wk1i * x0i;
      double y15i = wk1r * x0i + wk1i * x0r;
      x0r = y12r - y14r;
      x0i = y12i - y14i;
      x1r = y12r + y14r;
      x1i = y12i + y14i;
      x2r = y13r - y15r;
      x2i = y13i - y15i;
      x3r = y13r + y15r;
      x3i = y13i + y15i;
      a.setDouble(offa + 24L, x0r + x2r);
      a.setDouble(offa + 25L, x0i + x2i);
      a.setDouble(offa + 26L, x0r - x2r);
      a.setDouble(offa + 27L, x0i - x2i);
      a.setDouble(offa + 28L, x1r - x3i);
      a.setDouble(offa + 29L, x1i + x3r);
      a.setDouble(offa + 30L, x1r + x3i);
      a.setDouble(offa + 31L, x1i - x3r);
      x0r = y8r + y10r;
      x0i = y8i + y10i;
      x1r = y8r - y10r;
      x1i = y8i - y10i;
      x2r = y9r + y11r;
      x2i = y9i + y11i;
      x3r = y9r - y11r;
      x3i = y9i - y11i;
      a.setDouble(offa + 16L, x0r + x2r);
      a.setDouble(offa + 17L, x0i + x2i);
      a.setDouble(offa + 18L, x0r - x2r);
      a.setDouble(offa + 19L, x0i - x2i);
      a.setDouble(offa + 20L, x1r - x3i);
      a.setDouble(offa + 21L, x1i + x3r);
      a.setDouble(offa + 22L, x1r + x3i);
      a.setDouble(offa + 23L, x1i - x3r);
      x0r = y5r - y7i;
      x0i = y5i + y7r;
      x2r = wn4r * (x0r - x0i);
      x2i = wn4r * (x0i + x0r);
      x0r = y5r + y7i;
      x0i = y5i - y7r;
      x3r = wn4r * (x0r - x0i);
      x3i = wn4r * (x0i + x0r);
      x0r = y4r - y6i;
      x0i = y4i + y6r;
      x1r = y4r + y6i;
      x1i = y4i - y6r;
      a.setDouble(offa + 8L, x0r + x2r);
      a.setDouble(offa + 9L, x0i + x2i);
      a.setDouble(offa + 10L, x0r - x2r);
      a.setDouble(offa + 11L, x0i - x2i);
      a.setDouble(offa + 12L, x1r - x3i);
      a.setDouble(offa + 13L, x1i + x3r);
      a.setDouble(offa + 14L, x1r + x3i);
      a.setDouble(offa + 15L, x1i - x3r);
      x0r = y0r + y2r;
      x0i = y0i + y2i;
      x1r = y0r - y2r;
      x1i = y0i - y2i;
      x2r = y1r + y3r;
      x2i = y1i + y3i;
      x3r = y1r - y3r;
      x3i = y1i - y3i;
      a.setDouble(offa, x0r + x2r);
      a.setDouble(offa + 1L, x0i + x2i);
      a.setDouble(offa + 2L, x0r - x2r);
      a.setDouble(offa + 3L, x0i - x2i);
      a.setDouble(offa + 4L, x1r - x3i);
      a.setDouble(offa + 5L, x1i + x3r);
      a.setDouble(offa + 6L, x1r + x3i);
      a.setDouble(offa + 7L, x1i - x3r);
   }

   public static void cftf162(double[] a, int offa, double[] w, int startw) {
      double wn4r = w[startw + 1];
      double wk1r = w[startw + 4];
      double wk1i = w[startw + 5];
      double wk3r = w[startw + 6];
      double wk3i = -w[startw + 7];
      double wk2r = w[startw + 8];
      double wk2i = w[startw + 9];
      double x1r = a[offa] - a[offa + 17];
      double x1i = a[offa + 1] + a[offa + 16];
      double x0r = a[offa + 8] - a[offa + 25];
      double x0i = a[offa + 9] + a[offa + 24];
      double x2r = wn4r * (x0r - x0i);
      double x2i = wn4r * (x0i + x0r);
      double y0r = x1r + x2r;
      double y0i = x1i + x2i;
      double y4r = x1r - x2r;
      double y4i = x1i - x2i;
      x1r = a[offa] + a[offa + 17];
      x1i = a[offa + 1] - a[offa + 16];
      x0r = a[offa + 8] + a[offa + 25];
      x0i = a[offa + 9] - a[offa + 24];
      x2r = wn4r * (x0r - x0i);
      x2i = wn4r * (x0i + x0r);
      double y8r = x1r - x2i;
      double y8i = x1i + x2r;
      double y12r = x1r + x2i;
      double y12i = x1i - x2r;
      x0r = a[offa + 2] - a[offa + 19];
      x0i = a[offa + 3] + a[offa + 18];
      x1r = wk1r * x0r - wk1i * x0i;
      x1i = wk1r * x0i + wk1i * x0r;
      x0r = a[offa + 10] - a[offa + 27];
      x0i = a[offa + 11] + a[offa + 26];
      x2r = wk3i * x0r - wk3r * x0i;
      x2i = wk3i * x0i + wk3r * x0r;
      double y1r = x1r + x2r;
      double y1i = x1i + x2i;
      double y5r = x1r - x2r;
      double y5i = x1i - x2i;
      x0r = a[offa + 2] + a[offa + 19];
      x0i = a[offa + 3] - a[offa + 18];
      x1r = wk3r * x0r - wk3i * x0i;
      x1i = wk3r * x0i + wk3i * x0r;
      x0r = a[offa + 10] + a[offa + 27];
      x0i = a[offa + 11] - a[offa + 26];
      x2r = wk1r * x0r + wk1i * x0i;
      x2i = wk1r * x0i - wk1i * x0r;
      double y9r = x1r - x2r;
      double y9i = x1i - x2i;
      double y13r = x1r + x2r;
      double y13i = x1i + x2i;
      x0r = a[offa + 4] - a[offa + 21];
      x0i = a[offa + 5] + a[offa + 20];
      x1r = wk2r * x0r - wk2i * x0i;
      x1i = wk2r * x0i + wk2i * x0r;
      x0r = a[offa + 12] - a[offa + 29];
      x0i = a[offa + 13] + a[offa + 28];
      x2r = wk2i * x0r - wk2r * x0i;
      x2i = wk2i * x0i + wk2r * x0r;
      double y2r = x1r + x2r;
      double y2i = x1i + x2i;
      double y6r = x1r - x2r;
      double y6i = x1i - x2i;
      x0r = a[offa + 4] + a[offa + 21];
      x0i = a[offa + 5] - a[offa + 20];
      x1r = wk2i * x0r - wk2r * x0i;
      x1i = wk2i * x0i + wk2r * x0r;
      x0r = a[offa + 12] + a[offa + 29];
      x0i = a[offa + 13] - a[offa + 28];
      x2r = wk2r * x0r - wk2i * x0i;
      x2i = wk2r * x0i + wk2i * x0r;
      double y10r = x1r - x2r;
      double y10i = x1i - x2i;
      double y14r = x1r + x2r;
      double y14i = x1i + x2i;
      x0r = a[offa + 6] - a[offa + 23];
      x0i = a[offa + 7] + a[offa + 22];
      x1r = wk3r * x0r - wk3i * x0i;
      x1i = wk3r * x0i + wk3i * x0r;
      x0r = a[offa + 14] - a[offa + 31];
      x0i = a[offa + 15] + a[offa + 30];
      x2r = wk1i * x0r - wk1r * x0i;
      x2i = wk1i * x0i + wk1r * x0r;
      double y3r = x1r + x2r;
      double y3i = x1i + x2i;
      double y7r = x1r - x2r;
      double y7i = x1i - x2i;
      x0r = a[offa + 6] + a[offa + 23];
      x0i = a[offa + 7] - a[offa + 22];
      x1r = wk1i * x0r + wk1r * x0i;
      x1i = wk1i * x0i - wk1r * x0r;
      x0r = a[offa + 14] + a[offa + 31];
      x0i = a[offa + 15] - a[offa + 30];
      x2r = wk3i * x0r - wk3r * x0i;
      x2i = wk3i * x0i + wk3r * x0r;
      double y11r = x1r + x2r;
      double y11i = x1i + x2i;
      double y15r = x1r - x2r;
      double y15i = x1i - x2i;
      x1r = y0r + y2r;
      x1i = y0i + y2i;
      x2r = y1r + y3r;
      x2i = y1i + y3i;
      a[offa] = x1r + x2r;
      a[offa + 1] = x1i + x2i;
      a[offa + 2] = x1r - x2r;
      a[offa + 3] = x1i - x2i;
      x1r = y0r - y2r;
      x1i = y0i - y2i;
      x2r = y1r - y3r;
      x2i = y1i - y3i;
      a[offa + 4] = x1r - x2i;
      a[offa + 5] = x1i + x2r;
      a[offa + 6] = x1r + x2i;
      a[offa + 7] = x1i - x2r;
      x1r = y4r - y6i;
      x1i = y4i + y6r;
      x0r = y5r - y7i;
      x0i = y5i + y7r;
      x2r = wn4r * (x0r - x0i);
      x2i = wn4r * (x0i + x0r);
      a[offa + 8] = x1r + x2r;
      a[offa + 9] = x1i + x2i;
      a[offa + 10] = x1r - x2r;
      a[offa + 11] = x1i - x2i;
      x1r = y4r + y6i;
      x1i = y4i - y6r;
      x0r = y5r + y7i;
      x0i = y5i - y7r;
      x2r = wn4r * (x0r - x0i);
      x2i = wn4r * (x0i + x0r);
      a[offa + 12] = x1r - x2i;
      a[offa + 13] = x1i + x2r;
      a[offa + 14] = x1r + x2i;
      a[offa + 15] = x1i - x2r;
      x1r = y8r + y10r;
      x1i = y8i + y10i;
      x2r = y9r - y11r;
      x2i = y9i - y11i;
      a[offa + 16] = x1r + x2r;
      a[offa + 17] = x1i + x2i;
      a[offa + 18] = x1r - x2r;
      a[offa + 19] = x1i - x2i;
      x1r = y8r - y10r;
      x1i = y8i - y10i;
      x2r = y9r + y11r;
      x2i = y9i + y11i;
      a[offa + 20] = x1r - x2i;
      a[offa + 21] = x1i + x2r;
      a[offa + 22] = x1r + x2i;
      a[offa + 23] = x1i - x2r;
      x1r = y12r - y14i;
      x1i = y12i + y14r;
      x0r = y13r + y15i;
      x0i = y13i - y15r;
      x2r = wn4r * (x0r - x0i);
      x2i = wn4r * (x0i + x0r);
      a[offa + 24] = x1r + x2r;
      a[offa + 25] = x1i + x2i;
      a[offa + 26] = x1r - x2r;
      a[offa + 27] = x1i - x2i;
      x1r = y12r + y14i;
      x1i = y12i - y14r;
      x0r = y13r - y15i;
      x0i = y13i + y15r;
      x2r = wn4r * (x0r - x0i);
      x2i = wn4r * (x0i + x0r);
      a[offa + 28] = x1r - x2i;
      a[offa + 29] = x1i + x2r;
      a[offa + 30] = x1r + x2i;
      a[offa + 31] = x1i - x2r;
   }

   public static void cftf162(DoubleLargeArray a, long offa, DoubleLargeArray w, long startw) {
      double wn4r = w.getDouble(startw + 1L);
      double wk1r = w.getDouble(startw + 4L);
      double wk1i = w.getDouble(startw + 5L);
      double wk3r = w.getDouble(startw + 6L);
      double wk3i = -w.getDouble(startw + 7L);
      double wk2r = w.getDouble(startw + 8L);
      double wk2i = w.getDouble(startw + 9L);
      double x1r = a.getDouble(offa) - a.getDouble(offa + 17L);
      double x1i = a.getDouble(offa + 1L) + a.getDouble(offa + 16L);
      double x0r = a.getDouble(offa + 8L) - a.getDouble(offa + 25L);
      double x0i = a.getDouble(offa + 9L) + a.getDouble(offa + 24L);
      double x2r = wn4r * (x0r - x0i);
      double x2i = wn4r * (x0i + x0r);
      double y0r = x1r + x2r;
      double y0i = x1i + x2i;
      double y4r = x1r - x2r;
      double y4i = x1i - x2i;
      x1r = a.getDouble(offa) + a.getDouble(offa + 17L);
      x1i = a.getDouble(offa + 1L) - a.getDouble(offa + 16L);
      x0r = a.getDouble(offa + 8L) + a.getDouble(offa + 25L);
      x0i = a.getDouble(offa + 9L) - a.getDouble(offa + 24L);
      x2r = wn4r * (x0r - x0i);
      x2i = wn4r * (x0i + x0r);
      double y8r = x1r - x2i;
      double y8i = x1i + x2r;
      double y12r = x1r + x2i;
      double y12i = x1i - x2r;
      x0r = a.getDouble(offa + 2L) - a.getDouble(offa + 19L);
      x0i = a.getDouble(offa + 3L) + a.getDouble(offa + 18L);
      x1r = wk1r * x0r - wk1i * x0i;
      x1i = wk1r * x0i + wk1i * x0r;
      x0r = a.getDouble(offa + 10L) - a.getDouble(offa + 27L);
      x0i = a.getDouble(offa + 11L) + a.getDouble(offa + 26L);
      x2r = wk3i * x0r - wk3r * x0i;
      x2i = wk3i * x0i + wk3r * x0r;
      double y1r = x1r + x2r;
      double y1i = x1i + x2i;
      double y5r = x1r - x2r;
      double y5i = x1i - x2i;
      x0r = a.getDouble(offa + 2L) + a.getDouble(offa + 19L);
      x0i = a.getDouble(offa + 3L) - a.getDouble(offa + 18L);
      x1r = wk3r * x0r - wk3i * x0i;
      x1i = wk3r * x0i + wk3i * x0r;
      x0r = a.getDouble(offa + 10L) + a.getDouble(offa + 27L);
      x0i = a.getDouble(offa + 11L) - a.getDouble(offa + 26L);
      x2r = wk1r * x0r + wk1i * x0i;
      x2i = wk1r * x0i - wk1i * x0r;
      double y9r = x1r - x2r;
      double y9i = x1i - x2i;
      double y13r = x1r + x2r;
      double y13i = x1i + x2i;
      x0r = a.getDouble(offa + 4L) - a.getDouble(offa + 21L);
      x0i = a.getDouble(offa + 5L) + a.getDouble(offa + 20L);
      x1r = wk2r * x0r - wk2i * x0i;
      x1i = wk2r * x0i + wk2i * x0r;
      x0r = a.getDouble(offa + 12L) - a.getDouble(offa + 29L);
      x0i = a.getDouble(offa + 13L) + a.getDouble(offa + 28L);
      x2r = wk2i * x0r - wk2r * x0i;
      x2i = wk2i * x0i + wk2r * x0r;
      double y2r = x1r + x2r;
      double y2i = x1i + x2i;
      double y6r = x1r - x2r;
      double y6i = x1i - x2i;
      x0r = a.getDouble(offa + 4L) + a.getDouble(offa + 21L);
      x0i = a.getDouble(offa + 5L) - a.getDouble(offa + 20L);
      x1r = wk2i * x0r - wk2r * x0i;
      x1i = wk2i * x0i + wk2r * x0r;
      x0r = a.getDouble(offa + 12L) + a.getDouble(offa + 29L);
      x0i = a.getDouble(offa + 13L) - a.getDouble(offa + 28L);
      x2r = wk2r * x0r - wk2i * x0i;
      x2i = wk2r * x0i + wk2i * x0r;
      double y10r = x1r - x2r;
      double y10i = x1i - x2i;
      double y14r = x1r + x2r;
      double y14i = x1i + x2i;
      x0r = a.getDouble(offa + 6L) - a.getDouble(offa + 23L);
      x0i = a.getDouble(offa + 7L) + a.getDouble(offa + 22L);
      x1r = wk3r * x0r - wk3i * x0i;
      x1i = wk3r * x0i + wk3i * x0r;
      x0r = a.getDouble(offa + 14L) - a.getDouble(offa + 31L);
      x0i = a.getDouble(offa + 15L) + a.getDouble(offa + 30L);
      x2r = wk1i * x0r - wk1r * x0i;
      x2i = wk1i * x0i + wk1r * x0r;
      double y3r = x1r + x2r;
      double y3i = x1i + x2i;
      double y7r = x1r - x2r;
      double y7i = x1i - x2i;
      x0r = a.getDouble(offa + 6L) + a.getDouble(offa + 23L);
      x0i = a.getDouble(offa + 7L) - a.getDouble(offa + 22L);
      x1r = wk1i * x0r + wk1r * x0i;
      x1i = wk1i * x0i - wk1r * x0r;
      x0r = a.getDouble(offa + 14L) + a.getDouble(offa + 31L);
      x0i = a.getDouble(offa + 15L) - a.getDouble(offa + 30L);
      x2r = wk3i * x0r - wk3r * x0i;
      x2i = wk3i * x0i + wk3r * x0r;
      double y11r = x1r + x2r;
      double y11i = x1i + x2i;
      double y15r = x1r - x2r;
      double y15i = x1i - x2i;
      x1r = y0r + y2r;
      x1i = y0i + y2i;
      x2r = y1r + y3r;
      x2i = y1i + y3i;
      a.setDouble(offa, x1r + x2r);
      a.setDouble(offa + 1L, x1i + x2i);
      a.setDouble(offa + 2L, x1r - x2r);
      a.setDouble(offa + 3L, x1i - x2i);
      x1r = y0r - y2r;
      x1i = y0i - y2i;
      x2r = y1r - y3r;
      x2i = y1i - y3i;
      a.setDouble(offa + 4L, x1r - x2i);
      a.setDouble(offa + 5L, x1i + x2r);
      a.setDouble(offa + 6L, x1r + x2i);
      a.setDouble(offa + 7L, x1i - x2r);
      x1r = y4r - y6i;
      x1i = y4i + y6r;
      x0r = y5r - y7i;
      x0i = y5i + y7r;
      x2r = wn4r * (x0r - x0i);
      x2i = wn4r * (x0i + x0r);
      a.setDouble(offa + 8L, x1r + x2r);
      a.setDouble(offa + 9L, x1i + x2i);
      a.setDouble(offa + 10L, x1r - x2r);
      a.setDouble(offa + 11L, x1i - x2i);
      x1r = y4r + y6i;
      x1i = y4i - y6r;
      x0r = y5r + y7i;
      x0i = y5i - y7r;
      x2r = wn4r * (x0r - x0i);
      x2i = wn4r * (x0i + x0r);
      a.setDouble(offa + 12L, x1r - x2i);
      a.setDouble(offa + 13L, x1i + x2r);
      a.setDouble(offa + 14L, x1r + x2i);
      a.setDouble(offa + 15L, x1i - x2r);
      x1r = y8r + y10r;
      x1i = y8i + y10i;
      x2r = y9r - y11r;
      x2i = y9i - y11i;
      a.setDouble(offa + 16L, x1r + x2r);
      a.setDouble(offa + 17L, x1i + x2i);
      a.setDouble(offa + 18L, x1r - x2r);
      a.setDouble(offa + 19L, x1i - x2i);
      x1r = y8r - y10r;
      x1i = y8i - y10i;
      x2r = y9r + y11r;
      x2i = y9i + y11i;
      a.setDouble(offa + 20L, x1r - x2i);
      a.setDouble(offa + 21L, x1i + x2r);
      a.setDouble(offa + 22L, x1r + x2i);
      a.setDouble(offa + 23L, x1i - x2r);
      x1r = y12r - y14i;
      x1i = y12i + y14r;
      x0r = y13r + y15i;
      x0i = y13i - y15r;
      x2r = wn4r * (x0r - x0i);
      x2i = wn4r * (x0i + x0r);
      a.setDouble(offa + 24L, x1r + x2r);
      a.setDouble(offa + 25L, x1i + x2i);
      a.setDouble(offa + 26L, x1r - x2r);
      a.setDouble(offa + 27L, x1i - x2i);
      x1r = y12r + y14i;
      x1i = y12i - y14r;
      x0r = y13r - y15i;
      x0i = y13i + y15r;
      x2r = wn4r * (x0r - x0i);
      x2i = wn4r * (x0i + x0r);
      a.setDouble(offa + 28L, x1r - x2i);
      a.setDouble(offa + 29L, x1i + x2r);
      a.setDouble(offa + 30L, x1r + x2i);
      a.setDouble(offa + 31L, x1i - x2r);
   }

   public static void cftf081(double[] a, int offa, double[] w, int startw) {
      double wn4r = w[startw + 1];
      double x0r = a[offa] + a[offa + 8];
      double x0i = a[offa + 1] + a[offa + 9];
      double x1r = a[offa] - a[offa + 8];
      double x1i = a[offa + 1] - a[offa + 9];
      double x2r = a[offa + 4] + a[offa + 12];
      double x2i = a[offa + 5] + a[offa + 13];
      double x3r = a[offa + 4] - a[offa + 12];
      double x3i = a[offa + 5] - a[offa + 13];
      double y0r = x0r + x2r;
      double y0i = x0i + x2i;
      double y2r = x0r - x2r;
      double y2i = x0i - x2i;
      double y1r = x1r - x3i;
      double y1i = x1i + x3r;
      double y3r = x1r + x3i;
      double y3i = x1i - x3r;
      x0r = a[offa + 2] + a[offa + 10];
      x0i = a[offa + 3] + a[offa + 11];
      x1r = a[offa + 2] - a[offa + 10];
      x1i = a[offa + 3] - a[offa + 11];
      x2r = a[offa + 6] + a[offa + 14];
      x2i = a[offa + 7] + a[offa + 15];
      x3r = a[offa + 6] - a[offa + 14];
      x3i = a[offa + 7] - a[offa + 15];
      double y4r = x0r + x2r;
      double y4i = x0i + x2i;
      double y6r = x0r - x2r;
      double y6i = x0i - x2i;
      x0r = x1r - x3i;
      x0i = x1i + x3r;
      x2r = x1r + x3i;
      x2i = x1i - x3r;
      double y5r = wn4r * (x0r - x0i);
      double y5i = wn4r * (x0r + x0i);
      double y7r = wn4r * (x2r - x2i);
      double y7i = wn4r * (x2r + x2i);
      a[offa + 8] = y1r + y5r;
      a[offa + 9] = y1i + y5i;
      a[offa + 10] = y1r - y5r;
      a[offa + 11] = y1i - y5i;
      a[offa + 12] = y3r - y7i;
      a[offa + 13] = y3i + y7r;
      a[offa + 14] = y3r + y7i;
      a[offa + 15] = y3i - y7r;
      a[offa] = y0r + y4r;
      a[offa + 1] = y0i + y4i;
      a[offa + 2] = y0r - y4r;
      a[offa + 3] = y0i - y4i;
      a[offa + 4] = y2r - y6i;
      a[offa + 5] = y2i + y6r;
      a[offa + 6] = y2r + y6i;
      a[offa + 7] = y2i - y6r;
   }

   public static void cftf081(DoubleLargeArray a, long offa, DoubleLargeArray w, long startw) {
      double wn4r = w.getDouble(startw + 1L);
      double x0r = a.getDouble(offa) + a.getDouble(offa + 8L);
      double x0i = a.getDouble(offa + 1L) + a.getDouble(offa + 9L);
      double x1r = a.getDouble(offa) - a.getDouble(offa + 8L);
      double x1i = a.getDouble(offa + 1L) - a.getDouble(offa + 9L);
      double x2r = a.getDouble(offa + 4L) + a.getDouble(offa + 12L);
      double x2i = a.getDouble(offa + 5L) + a.getDouble(offa + 13L);
      double x3r = a.getDouble(offa + 4L) - a.getDouble(offa + 12L);
      double x3i = a.getDouble(offa + 5L) - a.getDouble(offa + 13L);
      double y0r = x0r + x2r;
      double y0i = x0i + x2i;
      double y2r = x0r - x2r;
      double y2i = x0i - x2i;
      double y1r = x1r - x3i;
      double y1i = x1i + x3r;
      double y3r = x1r + x3i;
      double y3i = x1i - x3r;
      x0r = a.getDouble(offa + 2L) + a.getDouble(offa + 10L);
      x0i = a.getDouble(offa + 3L) + a.getDouble(offa + 11L);
      x1r = a.getDouble(offa + 2L) - a.getDouble(offa + 10L);
      x1i = a.getDouble(offa + 3L) - a.getDouble(offa + 11L);
      x2r = a.getDouble(offa + 6L) + a.getDouble(offa + 14L);
      x2i = a.getDouble(offa + 7L) + a.getDouble(offa + 15L);
      x3r = a.getDouble(offa + 6L) - a.getDouble(offa + 14L);
      x3i = a.getDouble(offa + 7L) - a.getDouble(offa + 15L);
      double y4r = x0r + x2r;
      double y4i = x0i + x2i;
      double y6r = x0r - x2r;
      double y6i = x0i - x2i;
      x0r = x1r - x3i;
      x0i = x1i + x3r;
      x2r = x1r + x3i;
      x2i = x1i - x3r;
      double y5r = wn4r * (x0r - x0i);
      double y5i = wn4r * (x0r + x0i);
      double y7r = wn4r * (x2r - x2i);
      double y7i = wn4r * (x2r + x2i);
      a.setDouble(offa + 8L, y1r + y5r);
      a.setDouble(offa + 9L, y1i + y5i);
      a.setDouble(offa + 10L, y1r - y5r);
      a.setDouble(offa + 11L, y1i - y5i);
      a.setDouble(offa + 12L, y3r - y7i);
      a.setDouble(offa + 13L, y3i + y7r);
      a.setDouble(offa + 14L, y3r + y7i);
      a.setDouble(offa + 15L, y3i - y7r);
      a.setDouble(offa, y0r + y4r);
      a.setDouble(offa + 1L, y0i + y4i);
      a.setDouble(offa + 2L, y0r - y4r);
      a.setDouble(offa + 3L, y0i - y4i);
      a.setDouble(offa + 4L, y2r - y6i);
      a.setDouble(offa + 5L, y2i + y6r);
      a.setDouble(offa + 6L, y2r + y6i);
      a.setDouble(offa + 7L, y2i - y6r);
   }

   public static void cftf082(double[] a, int offa, double[] w, int startw) {
      double wn4r = w[startw + 1];
      double wk1r = w[startw + 2];
      double wk1i = w[startw + 3];
      double y0r = a[offa] - a[offa + 9];
      double y0i = a[offa + 1] + a[offa + 8];
      double y1r = a[offa] + a[offa + 9];
      double y1i = a[offa + 1] - a[offa + 8];
      double x0r = a[offa + 4] - a[offa + 13];
      double x0i = a[offa + 5] + a[offa + 12];
      double y2r = wn4r * (x0r - x0i);
      double y2i = wn4r * (x0i + x0r);
      x0r = a[offa + 4] + a[offa + 13];
      x0i = a[offa + 5] - a[offa + 12];
      double y3r = wn4r * (x0r - x0i);
      double y3i = wn4r * (x0i + x0r);
      x0r = a[offa + 2] - a[offa + 11];
      x0i = a[offa + 3] + a[offa + 10];
      double y4r = wk1r * x0r - wk1i * x0i;
      double y4i = wk1r * x0i + wk1i * x0r;
      x0r = a[offa + 2] + a[offa + 11];
      x0i = a[offa + 3] - a[offa + 10];
      double y5r = wk1i * x0r - wk1r * x0i;
      double y5i = wk1i * x0i + wk1r * x0r;
      x0r = a[offa + 6] - a[offa + 15];
      x0i = a[offa + 7] + a[offa + 14];
      double y6r = wk1i * x0r - wk1r * x0i;
      double y6i = wk1i * x0i + wk1r * x0r;
      x0r = a[offa + 6] + a[offa + 15];
      x0i = a[offa + 7] - a[offa + 14];
      double y7r = wk1r * x0r - wk1i * x0i;
      double y7i = wk1r * x0i + wk1i * x0r;
      x0r = y0r + y2r;
      x0i = y0i + y2i;
      double x1r = y4r + y6r;
      double x1i = y4i + y6i;
      a[offa] = x0r + x1r;
      a[offa + 1] = x0i + x1i;
      a[offa + 2] = x0r - x1r;
      a[offa + 3] = x0i - x1i;
      x0r = y0r - y2r;
      x0i = y0i - y2i;
      x1r = y4r - y6r;
      x1i = y4i - y6i;
      a[offa + 4] = x0r - x1i;
      a[offa + 5] = x0i + x1r;
      a[offa + 6] = x0r + x1i;
      a[offa + 7] = x0i - x1r;
      x0r = y1r - y3i;
      x0i = y1i + y3r;
      x1r = y5r - y7r;
      x1i = y5i - y7i;
      a[offa + 8] = x0r + x1r;
      a[offa + 9] = x0i + x1i;
      a[offa + 10] = x0r - x1r;
      a[offa + 11] = x0i - x1i;
      x0r = y1r + y3i;
      x0i = y1i - y3r;
      x1r = y5r + y7r;
      x1i = y5i + y7i;
      a[offa + 12] = x0r - x1i;
      a[offa + 13] = x0i + x1r;
      a[offa + 14] = x0r + x1i;
      a[offa + 15] = x0i - x1r;
   }

   public static void cftf082(DoubleLargeArray a, long offa, DoubleLargeArray w, long startw) {
      double wn4r = w.getDouble(startw + 1L);
      double wk1r = w.getDouble(startw + 2L);
      double wk1i = w.getDouble(startw + 3L);
      double y0r = a.getDouble(offa) - a.getDouble(offa + 9L);
      double y0i = a.getDouble(offa + 1L) + a.getDouble(offa + 8L);
      double y1r = a.getDouble(offa) + a.getDouble(offa + 9L);
      double y1i = a.getDouble(offa + 1L) - a.getDouble(offa + 8L);
      double x0r = a.getDouble(offa + 4L) - a.getDouble(offa + 13L);
      double x0i = a.getDouble(offa + 5L) + a.getDouble(offa + 12L);
      double y2r = wn4r * (x0r - x0i);
      double y2i = wn4r * (x0i + x0r);
      x0r = a.getDouble(offa + 4L) + a.getDouble(offa + 13L);
      x0i = a.getDouble(offa + 5L) - a.getDouble(offa + 12L);
      double y3r = wn4r * (x0r - x0i);
      double y3i = wn4r * (x0i + x0r);
      x0r = a.getDouble(offa + 2L) - a.getDouble(offa + 11L);
      x0i = a.getDouble(offa + 3L) + a.getDouble(offa + 10L);
      double y4r = wk1r * x0r - wk1i * x0i;
      double y4i = wk1r * x0i + wk1i * x0r;
      x0r = a.getDouble(offa + 2L) + a.getDouble(offa + 11L);
      x0i = a.getDouble(offa + 3L) - a.getDouble(offa + 10L);
      double y5r = wk1i * x0r - wk1r * x0i;
      double y5i = wk1i * x0i + wk1r * x0r;
      x0r = a.getDouble(offa + 6L) - a.getDouble(offa + 15L);
      x0i = a.getDouble(offa + 7L) + a.getDouble(offa + 14L);
      double y6r = wk1i * x0r - wk1r * x0i;
      double y6i = wk1i * x0i + wk1r * x0r;
      x0r = a.getDouble(offa + 6L) + a.getDouble(offa + 15L);
      x0i = a.getDouble(offa + 7L) - a.getDouble(offa + 14L);
      double y7r = wk1r * x0r - wk1i * x0i;
      double y7i = wk1r * x0i + wk1i * x0r;
      x0r = y0r + y2r;
      x0i = y0i + y2i;
      double x1r = y4r + y6r;
      double x1i = y4i + y6i;
      a.setDouble(offa, x0r + x1r);
      a.setDouble(offa + 1L, x0i + x1i);
      a.setDouble(offa + 2L, x0r - x1r);
      a.setDouble(offa + 3L, x0i - x1i);
      x0r = y0r - y2r;
      x0i = y0i - y2i;
      x1r = y4r - y6r;
      x1i = y4i - y6i;
      a.setDouble(offa + 4L, x0r - x1i);
      a.setDouble(offa + 5L, x0i + x1r);
      a.setDouble(offa + 6L, x0r + x1i);
      a.setDouble(offa + 7L, x0i - x1r);
      x0r = y1r - y3i;
      x0i = y1i + y3r;
      x1r = y5r - y7r;
      x1i = y5i - y7i;
      a.setDouble(offa + 8L, x0r + x1r);
      a.setDouble(offa + 9L, x0i + x1i);
      a.setDouble(offa + 10L, x0r - x1r);
      a.setDouble(offa + 11L, x0i - x1i);
      x0r = y1r + y3i;
      x0i = y1i - y3r;
      x1r = y5r + y7r;
      x1i = y5i + y7i;
      a.setDouble(offa + 12L, x0r - x1i);
      a.setDouble(offa + 13L, x0i + x1r);
      a.setDouble(offa + 14L, x0r + x1i);
      a.setDouble(offa + 15L, x0i - x1r);
   }

   public static void cftf040(double[] a, int offa) {
      double x0r = a[offa] + a[offa + 4];
      double x0i = a[offa + 1] + a[offa + 5];
      double x1r = a[offa] - a[offa + 4];
      double x1i = a[offa + 1] - a[offa + 5];
      double x2r = a[offa + 2] + a[offa + 6];
      double x2i = a[offa + 3] + a[offa + 7];
      double x3r = a[offa + 2] - a[offa + 6];
      double x3i = a[offa + 3] - a[offa + 7];
      a[offa] = x0r + x2r;
      a[offa + 1] = x0i + x2i;
      a[offa + 2] = x1r - x3i;
      a[offa + 3] = x1i + x3r;
      a[offa + 4] = x0r - x2r;
      a[offa + 5] = x0i - x2i;
      a[offa + 6] = x1r + x3i;
      a[offa + 7] = x1i - x3r;
   }

   public static void cftf040(DoubleLargeArray a, long offa) {
      double x0r = a.getDouble(offa) + a.getDouble(offa + 4L);
      double x0i = a.getDouble(offa + 1L) + a.getDouble(offa + 5L);
      double x1r = a.getDouble(offa) - a.getDouble(offa + 4L);
      double x1i = a.getDouble(offa + 1L) - a.getDouble(offa + 5L);
      double x2r = a.getDouble(offa + 2L) + a.getDouble(offa + 6L);
      double x2i = a.getDouble(offa + 3L) + a.getDouble(offa + 7L);
      double x3r = a.getDouble(offa + 2L) - a.getDouble(offa + 6L);
      double x3i = a.getDouble(offa + 3L) - a.getDouble(offa + 7L);
      a.setDouble(offa, x0r + x2r);
      a.setDouble(offa + 1L, x0i + x2i);
      a.setDouble(offa + 2L, x1r - x3i);
      a.setDouble(offa + 3L, x1i + x3r);
      a.setDouble(offa + 4L, x0r - x2r);
      a.setDouble(offa + 5L, x0i - x2i);
      a.setDouble(offa + 6L, x1r + x3i);
      a.setDouble(offa + 7L, x1i - x3r);
   }

   public static void cftb040(double[] a, int offa) {
      double x0r = a[offa] + a[offa + 4];
      double x0i = a[offa + 1] + a[offa + 5];
      double x1r = a[offa] - a[offa + 4];
      double x1i = a[offa + 1] - a[offa + 5];
      double x2r = a[offa + 2] + a[offa + 6];
      double x2i = a[offa + 3] + a[offa + 7];
      double x3r = a[offa + 2] - a[offa + 6];
      double x3i = a[offa + 3] - a[offa + 7];
      a[offa] = x0r + x2r;
      a[offa + 1] = x0i + x2i;
      a[offa + 2] = x1r + x3i;
      a[offa + 3] = x1i - x3r;
      a[offa + 4] = x0r - x2r;
      a[offa + 5] = x0i - x2i;
      a[offa + 6] = x1r - x3i;
      a[offa + 7] = x1i + x3r;
   }

   public static void cftb040(DoubleLargeArray a, long offa) {
      double x0r = a.getDouble(offa) + a.getDouble(offa + 4L);
      double x0i = a.getDouble(offa + 1L) + a.getDouble(offa + 5L);
      double x1r = a.getDouble(offa) - a.getDouble(offa + 4L);
      double x1i = a.getDouble(offa + 1L) - a.getDouble(offa + 5L);
      double x2r = a.getDouble(offa + 2L) + a.getDouble(offa + 6L);
      double x2i = a.getDouble(offa + 3L) + a.getDouble(offa + 7L);
      double x3r = a.getDouble(offa + 2L) - a.getDouble(offa + 6L);
      double x3i = a.getDouble(offa + 3L) - a.getDouble(offa + 7L);
      a.setDouble(offa, x0r + x2r);
      a.setDouble(offa + 1L, x0i + x2i);
      a.setDouble(offa + 2L, x1r + x3i);
      a.setDouble(offa + 3L, x1i - x3r);
      a.setDouble(offa + 4L, x0r - x2r);
      a.setDouble(offa + 5L, x0i - x2i);
      a.setDouble(offa + 6L, x1r - x3i);
      a.setDouble(offa + 7L, x1i + x3r);
   }

   public static void cftx020(double[] a, int offa) {
      double x0r = a[offa] - a[offa + 2];
      double x0i = -a[offa + 1] + a[offa + 3];
      a[offa] += a[offa + 2];
      a[offa + 1] += a[offa + 3];
      a[offa + 2] = x0r;
      a[offa + 3] = x0i;
   }

   public static void cftx020(DoubleLargeArray a, long offa) {
      double x0r = a.getDouble(offa) - a.getDouble(offa + 2L);
      double x0i = -a.getDouble(offa + 1L) + a.getDouble(offa + 3L);
      a.setDouble(offa, a.getDouble(offa) + a.getDouble(offa + 2L));
      a.setDouble(offa + 1L, a.getDouble(offa + 1L) + a.getDouble(offa + 3L));
      a.setDouble(offa + 2L, x0r);
      a.setDouble(offa + 3L, x0i);
   }

   public static void cftxb020(double[] a, int offa) {
      double x0r = a[offa] - a[offa + 2];
      double x0i = a[offa + 1] - a[offa + 3];
      a[offa] += a[offa + 2];
      a[offa + 1] += a[offa + 3];
      a[offa + 2] = x0r;
      a[offa + 3] = x0i;
   }

   public static void cftxb020(DoubleLargeArray a, long offa) {
      double x0r = a.getDouble(offa) - a.getDouble(offa + 2L);
      double x0i = a.getDouble(offa + 1L) - a.getDouble(offa + 3L);
      a.setDouble(offa, a.getDouble(offa) + a.getDouble(offa + 2L));
      a.setDouble(offa + 1L, a.getDouble(offa + 1L) + a.getDouble(offa + 3L));
      a.setDouble(offa + 2L, x0r);
      a.setDouble(offa + 3L, x0i);
   }

   public static void cftxc020(double[] a, int offa) {
      double x0r = a[offa] - a[offa + 2];
      double x0i = a[offa + 1] + a[offa + 3];
      a[offa] += a[offa + 2];
      a[offa + 1] -= a[offa + 3];
      a[offa + 2] = x0r;
      a[offa + 3] = x0i;
   }

   public static void cftxc020(DoubleLargeArray a, long offa) {
      double x0r = a.getDouble(offa) - a.getDouble(offa + 2L);
      double x0i = a.getDouble(offa + 1L) + a.getDouble(offa + 3L);
      a.setDouble(offa, a.getDouble(offa) + a.getDouble(offa + 2L));
      a.setDouble(offa + 1L, a.getDouble(offa + 1L) - a.getDouble(offa + 3L));
      a.setDouble(offa + 2L, x0r);
      a.setDouble(offa + 3L, x0i);
   }

   public static void rftfsub(int n, double[] a, int offa, int nc, double[] c, int startc) {
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
         a[idx1 + 1] = yi - a[idx1 + 1];
         a[idx2] += yr;
         a[idx2 + 1] = yi - a[idx2 + 1];
      }

      a[offa + m + 1] = -a[offa + m + 1];
   }

   public static void rftfsub(long n, DoubleLargeArray a, long offa, long nc, DoubleLargeArray c, long startc) {
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
         a.setDouble(idx1 + 1L, yi - a.getDouble(idx1 + 1L));
         a.setDouble(idx2, a.getDouble(idx2) + yr);
         a.setDouble(idx2 + 1L, yi - a.getDouble(idx2 + 1L));
      }

      a.setDouble(offa + m + 1L, -a.getDouble(offa + m + 1L));
   }

   public static void rftbsub(int n, double[] a, int offa, int nc, double[] c, int startc) {
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

   public static void rftbsub(long n, DoubleLargeArray a, long offa, long nc, DoubleLargeArray c, long startc) {
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

   public static void dctsub(int n, double[] a, int offa, int nc, double[] c, int startc) {
      int m = n >> 1;
      int ks = nc / n;
      int kk = 0;

      for(int j = 1; j < m; ++j) {
         int k = n - j;
         kk += ks;
         int idx0 = startc + kk;
         int idx1 = offa + j;
         int idx2 = offa + k;
         double wkr = c[idx0] - c[startc + nc - kk];
         double wki = c[idx0] + c[startc + nc - kk];
         double xr = wki * a[idx1] - wkr * a[idx2];
         a[idx1] = wkr * a[idx1] + wki * a[idx2];
         a[idx2] = xr;
      }

      a[offa + m] *= c[startc];
   }

   public static void dctsub(long n, DoubleLargeArray a, long offa, long nc, DoubleLargeArray c, long startc) {
      long m = n >> 1;
      long ks = nc / n;
      long kk = 0L;

      for(long j = 1L; j < m; ++j) {
         long k = n - j;
         kk += ks;
         long idx0 = startc + kk;
         long idx1 = offa + j;
         long idx2 = offa + k;
         double wkr = c.getDouble(idx0) - c.getDouble(startc + nc - kk);
         double wki = c.getDouble(idx0) + c.getDouble(startc + nc - kk);
         double xr = wki * a.getDouble(idx1) - wkr * a.getDouble(idx2);
         a.setDouble(idx1, wkr * a.getDouble(idx1) + wki * a.getDouble(idx2));
         a.setDouble(idx2, xr);
      }

      a.setDouble(offa + m, a.getDouble(offa + m) * c.getDouble(startc));
   }

   public static void cftfsub(int n, float[] a, int offa, int[] ip, int nw, float[] w) {
      if (n > 8) {
         if (n > 32) {
            cftf1st(n, a, offa, w, nw - (n >> 2));
            if (ConcurrencyUtils.getNumberOfThreads() > 1 && (long)n >= getThreadsBeginN_1D_FFT_2Threads()) {
               cftrec4_th(n, a, offa, nw, w);
            } else if (n > 512) {
               cftrec4(n, a, offa, nw, w);
            } else if (n > 128) {
               cftleaf(n, 1, (float[])a, offa, nw, (float[])w);
            } else {
               cftfx41(n, a, offa, nw, w);
            }

            bitrv2(n, ip, a, offa);
         } else if (n == 32) {
            cftf161(a, offa, w, nw - 8);
            bitrv216(a, offa);
         } else {
            cftf081((float[])a, offa, (float[])w, 0);
            bitrv208(a, offa);
         }
      } else if (n == 8) {
         cftf040(a, offa);
      } else if (n == 4) {
         cftxb020(a, offa);
      }

   }

   public static void cftfsub(long n, FloatLargeArray a, long offa, LongLargeArray ip, long nw, FloatLargeArray w) {
      if (n > 8L) {
         if (n > 32L) {
            cftf1st(n, a, offa, w, nw - (n >> 2));
            if (ConcurrencyUtils.getNumberOfThreads() > 1 && n >= getThreadsBeginN_1D_FFT_2Threads()) {
               cftrec4_th(n, a, offa, nw, w);
            } else if (n > 512L) {
               cftrec4(n, a, offa, nw, w);
            } else if (n > 128L) {
               cftleaf(n, 1L, a, offa, nw, w);
            } else {
               cftfx41(n, a, offa, nw, w);
            }

            bitrv2l(n, ip, a, offa);
         } else if (n == 32L) {
            cftf161(a, offa, w, nw - 8L);
            bitrv216(a, offa);
         } else {
            cftf081(a, offa, w, 0L);
            bitrv208(a, offa);
         }
      } else if (n == 8L) {
         cftf040(a, offa);
      } else if (n == 4L) {
         cftxb020(a, offa);
      }

   }

   public static void cftbsub(int n, float[] a, int offa, int[] ip, int nw, float[] w) {
      if (n > 8) {
         if (n > 32) {
            cftb1st(n, a, offa, w, nw - (n >> 2));
            if (ConcurrencyUtils.getNumberOfThreads() > 1 && (long)n >= getThreadsBeginN_1D_FFT_2Threads()) {
               cftrec4_th(n, a, offa, nw, w);
            } else if (n > 512) {
               cftrec4(n, a, offa, nw, w);
            } else if (n > 128) {
               cftleaf(n, 1, (float[])a, offa, nw, (float[])w);
            } else {
               cftfx41(n, a, offa, nw, w);
            }

            bitrv2conj(n, ip, a, offa);
         } else if (n == 32) {
            cftf161(a, offa, w, nw - 8);
            bitrv216neg(a, offa);
         } else {
            cftf081((float[])a, offa, (float[])w, 0);
            bitrv208neg(a, offa);
         }
      } else if (n == 8) {
         cftb040(a, offa);
      } else if (n == 4) {
         cftxb020(a, offa);
      }

   }

   public static void cftbsub(long n, FloatLargeArray a, long offa, LongLargeArray ip, long nw, FloatLargeArray w) {
      if (n > 8L) {
         if (n > 32L) {
            cftb1st(n, a, offa, w, nw - (n >> 2));
            if (ConcurrencyUtils.getNumberOfThreads() > 1 && n >= getThreadsBeginN_1D_FFT_2Threads()) {
               cftrec4_th(n, a, offa, nw, w);
            } else if (n > 512L) {
               cftrec4(n, a, offa, nw, w);
            } else if (n > 128L) {
               cftleaf(n, 1L, a, offa, nw, w);
            } else {
               cftfx41(n, a, offa, nw, w);
            }

            bitrv2conj(n, ip, a, offa);
         } else if (n == 32L) {
            cftf161(a, offa, w, nw - 8L);
            bitrv216neg(a, offa);
         } else {
            cftf081(a, offa, w, 0L);
            bitrv208neg(a, offa);
         }
      } else if (n == 8L) {
         cftb040(a, offa);
      } else if (n == 4L) {
         cftxb020(a, offa);
      }

   }

   public static void bitrv2(int n, int[] ip, float[] a, int offa) {
      int m = 1;

      int l;
      for(l = n >> 2; l > 8; l >>= 2) {
         m <<= 1;
      }

      int nh = n >> 1;
      int nm = 4 * m;
      if (l == 8) {
         for(int k = 0; k < m; ++k) {
            int idx0 = 4 * k;

            for(int j = 0; j < k; ++j) {
               int j1 = 4 * j + 2 * ip[m + k];
               int k1 = idx0 + 2 * ip[m + j];
               int idx1 = offa + j1;
               int idx2 = offa + k1;
               float xr = a[idx1];
               float xi = a[idx1 + 1];
               float yr = a[idx2];
               float yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += nm;
               k1 += 2 * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = a[idx1 + 1];
               yr = a[idx2];
               yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += nm;
               k1 -= nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = a[idx1 + 1];
               yr = a[idx2];
               yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += nm;
               k1 += 2 * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = a[idx1 + 1];
               yr = a[idx2];
               yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += nh;
               k1 += 2;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = a[idx1 + 1];
               yr = a[idx2];
               yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 -= nm;
               k1 -= 2 * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = a[idx1 + 1];
               yr = a[idx2];
               yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 -= nm;
               k1 += nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = a[idx1 + 1];
               yr = a[idx2];
               yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 -= nm;
               k1 -= 2 * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = a[idx1 + 1];
               yr = a[idx2];
               yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += 2;
               k1 += nh;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = a[idx1 + 1];
               yr = a[idx2];
               yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += nm;
               k1 += 2 * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = a[idx1 + 1];
               yr = a[idx2];
               yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += nm;
               k1 -= nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = a[idx1 + 1];
               yr = a[idx2];
               yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += nm;
               k1 += 2 * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = a[idx1 + 1];
               yr = a[idx2];
               yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 -= nh;
               k1 -= 2;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = a[idx1 + 1];
               yr = a[idx2];
               yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 -= nm;
               k1 -= 2 * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = a[idx1 + 1];
               yr = a[idx2];
               yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 -= nm;
               k1 += nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = a[idx1 + 1];
               yr = a[idx2];
               yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 -= nm;
               k1 -= 2 * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = a[idx1 + 1];
               yr = a[idx2];
               yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
            }

            int k1 = idx0 + 2 * ip[m + k];
            int j1 = k1 + 2;
            k1 += nh;
            int idx1 = offa + j1;
            int idx2 = offa + k1;
            float xr = a[idx1];
            float xi = a[idx1 + 1];
            float yr = a[idx2];
            float yi = a[idx2 + 1];
            a[idx1] = yr;
            a[idx1 + 1] = yi;
            a[idx2] = xr;
            a[idx2 + 1] = xi;
            j1 += nm;
            k1 += 2 * nm;
            idx1 = offa + j1;
            idx2 = offa + k1;
            xr = a[idx1];
            xi = a[idx1 + 1];
            yr = a[idx2];
            yi = a[idx2 + 1];
            a[idx1] = yr;
            a[idx1 + 1] = yi;
            a[idx2] = xr;
            a[idx2 + 1] = xi;
            j1 += nm;
            k1 -= nm;
            idx1 = offa + j1;
            idx2 = offa + k1;
            xr = a[idx1];
            xi = a[idx1 + 1];
            yr = a[idx2];
            yi = a[idx2 + 1];
            a[idx1] = yr;
            a[idx1 + 1] = yi;
            a[idx2] = xr;
            a[idx2 + 1] = xi;
            j1 -= 2;
            k1 -= nh;
            idx1 = offa + j1;
            idx2 = offa + k1;
            xr = a[idx1];
            xi = a[idx1 + 1];
            yr = a[idx2];
            yi = a[idx2 + 1];
            a[idx1] = yr;
            a[idx1 + 1] = yi;
            a[idx2] = xr;
            a[idx2 + 1] = xi;
            j1 += nh + 2;
            k1 += nh + 2;
            idx1 = offa + j1;
            idx2 = offa + k1;
            xr = a[idx1];
            xi = a[idx1 + 1];
            yr = a[idx2];
            yi = a[idx2 + 1];
            a[idx1] = yr;
            a[idx1 + 1] = yi;
            a[idx2] = xr;
            a[idx2 + 1] = xi;
            j1 -= nh - nm;
            k1 += 2 * nm - 2;
            idx1 = offa + j1;
            idx2 = offa + k1;
            xr = a[idx1];
            xi = a[idx1 + 1];
            yr = a[idx2];
            yi = a[idx2 + 1];
            a[idx1] = yr;
            a[idx1 + 1] = yi;
            a[idx2] = xr;
            a[idx2 + 1] = xi;
         }
      } else {
         for(int k = 0; k < m; ++k) {
            int idx0 = 4 * k;

            for(int j = 0; j < k; ++j) {
               int j1 = 4 * j + ip[m + k];
               int k1 = idx0 + ip[m + j];
               int idx1 = offa + j1;
               int idx2 = offa + k1;
               float xr = a[idx1];
               float xi = a[idx1 + 1];
               float yr = a[idx2];
               float yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += nm;
               k1 += nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = a[idx1 + 1];
               yr = a[idx2];
               yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += nh;
               k1 += 2;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = a[idx1 + 1];
               yr = a[idx2];
               yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 -= nm;
               k1 -= nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = a[idx1 + 1];
               yr = a[idx2];
               yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += 2;
               k1 += nh;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = a[idx1 + 1];
               yr = a[idx2];
               yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += nm;
               k1 += nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = a[idx1 + 1];
               yr = a[idx2];
               yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 -= nh;
               k1 -= 2;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = a[idx1 + 1];
               yr = a[idx2];
               yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 -= nm;
               k1 -= nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = a[idx1 + 1];
               yr = a[idx2];
               yi = a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
            }

            int k1 = idx0 + ip[m + k];
            int j1 = k1 + 2;
            k1 += nh;
            int idx1 = offa + j1;
            int idx2 = offa + k1;
            float xr = a[idx1];
            float xi = a[idx1 + 1];
            float yr = a[idx2];
            float yi = a[idx2 + 1];
            a[idx1] = yr;
            a[idx1 + 1] = yi;
            a[idx2] = xr;
            a[idx2 + 1] = xi;
            j1 += nm;
            k1 += nm;
            idx1 = offa + j1;
            idx2 = offa + k1;
            xr = a[idx1];
            xi = a[idx1 + 1];
            yr = a[idx2];
            yi = a[idx2 + 1];
            a[idx1] = yr;
            a[idx1 + 1] = yi;
            a[idx2] = xr;
            a[idx2 + 1] = xi;
         }
      }

   }

   public static void bitrv2l(long n, LongLargeArray ip, FloatLargeArray a, long offa) {
      long m = 1L;

      long l;
      for(l = n >> 2; l > 8L; l >>= 2) {
         m <<= 1;
      }

      long nh = n >> 1;
      long nm = 4L * m;
      if (l == 8L) {
         for(long k = 0L; k < m; ++k) {
            long idx0 = 4L * k;

            for(long j = 0L; j < k; ++j) {
               long j1 = 4L * j + 2L * ip.getLong(m + k);
               long k1 = idx0 + 2L * ip.getLong(m + j);
               long idx1 = offa + j1;
               long idx2 = offa + k1;
               float xr = a.getFloat(idx1);
               float xi = a.getFloat(idx1 + 1L);
               float yr = a.getFloat(idx2);
               float yi = a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
               j1 += nm;
               k1 += 2L * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getFloat(idx1);
               xi = a.getFloat(idx1 + 1L);
               yr = a.getFloat(idx2);
               yi = a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
               j1 += nm;
               k1 -= nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getFloat(idx1);
               xi = a.getFloat(idx1 + 1L);
               yr = a.getFloat(idx2);
               yi = a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
               j1 += nm;
               k1 += 2L * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getFloat(idx1);
               xi = a.getFloat(idx1 + 1L);
               yr = a.getFloat(idx2);
               yi = a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
               j1 += nh;
               k1 += 2L;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getFloat(idx1);
               xi = a.getFloat(idx1 + 1L);
               yr = a.getFloat(idx2);
               yi = a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
               j1 -= nm;
               k1 -= 2L * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getFloat(idx1);
               xi = a.getFloat(idx1 + 1L);
               yr = a.getFloat(idx2);
               yi = a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
               j1 -= nm;
               k1 += nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getFloat(idx1);
               xi = a.getFloat(idx1 + 1L);
               yr = a.getFloat(idx2);
               yi = a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
               j1 -= nm;
               k1 -= 2L * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getFloat(idx1);
               xi = a.getFloat(idx1 + 1L);
               yr = a.getFloat(idx2);
               yi = a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
               j1 += 2L;
               k1 += nh;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getFloat(idx1);
               xi = a.getFloat(idx1 + 1L);
               yr = a.getFloat(idx2);
               yi = a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
               j1 += nm;
               k1 += 2L * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getFloat(idx1);
               xi = a.getFloat(idx1 + 1L);
               yr = a.getFloat(idx2);
               yi = a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
               j1 += nm;
               k1 -= nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getFloat(idx1);
               xi = a.getFloat(idx1 + 1L);
               yr = a.getFloat(idx2);
               yi = a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
               j1 += nm;
               k1 += 2L * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getFloat(idx1);
               xi = a.getFloat(idx1 + 1L);
               yr = a.getFloat(idx2);
               yi = a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
               j1 -= nh;
               k1 -= 2L;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getFloat(idx1);
               xi = a.getFloat(idx1 + 1L);
               yr = a.getFloat(idx2);
               yi = a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
               j1 -= nm;
               k1 -= 2L * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getFloat(idx1);
               xi = a.getFloat(idx1 + 1L);
               yr = a.getFloat(idx2);
               yi = a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
               j1 -= nm;
               k1 += nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getFloat(idx1);
               xi = a.getFloat(idx1 + 1L);
               yr = a.getFloat(idx2);
               yi = a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
               j1 -= nm;
               k1 -= 2L * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getFloat(idx1);
               xi = a.getFloat(idx1 + 1L);
               yr = a.getFloat(idx2);
               yi = a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
            }

            long k1 = idx0 + 2L * ip.getLong(m + k);
            long j1 = k1 + 2L;
            k1 += nh;
            long idx1 = offa + j1;
            long idx2 = offa + k1;
            float xr = a.getFloat(idx1);
            float xi = a.getFloat(idx1 + 1L);
            float yr = a.getFloat(idx2);
            float yi = a.getFloat(idx2 + 1L);
            a.setFloat(idx1, yr);
            a.setFloat(idx1 + 1L, yi);
            a.setFloat(idx2, xr);
            a.setFloat(idx2 + 1L, xi);
            j1 += nm;
            k1 += 2L * nm;
            idx1 = offa + j1;
            idx2 = offa + k1;
            xr = a.getFloat(idx1);
            xi = a.getFloat(idx1 + 1L);
            yr = a.getFloat(idx2);
            yi = a.getFloat(idx2 + 1L);
            a.setFloat(idx1, yr);
            a.setFloat(idx1 + 1L, yi);
            a.setFloat(idx2, xr);
            a.setFloat(idx2 + 1L, xi);
            j1 += nm;
            k1 -= nm;
            idx1 = offa + j1;
            idx2 = offa + k1;
            xr = a.getFloat(idx1);
            xi = a.getFloat(idx1 + 1L);
            yr = a.getFloat(idx2);
            yi = a.getFloat(idx2 + 1L);
            a.setFloat(idx1, yr);
            a.setFloat(idx1 + 1L, yi);
            a.setFloat(idx2, xr);
            a.setFloat(idx2 + 1L, xi);
            j1 -= 2L;
            k1 -= nh;
            idx1 = offa + j1;
            idx2 = offa + k1;
            xr = a.getFloat(idx1);
            xi = a.getFloat(idx1 + 1L);
            yr = a.getFloat(idx2);
            yi = a.getFloat(idx2 + 1L);
            a.setFloat(idx1, yr);
            a.setFloat(idx1 + 1L, yi);
            a.setFloat(idx2, xr);
            a.setFloat(idx2 + 1L, xi);
            j1 += nh + 2L;
            k1 += nh + 2L;
            idx1 = offa + j1;
            idx2 = offa + k1;
            xr = a.getFloat(idx1);
            xi = a.getFloat(idx1 + 1L);
            yr = a.getFloat(idx2);
            yi = a.getFloat(idx2 + 1L);
            a.setFloat(idx1, yr);
            a.setFloat(idx1 + 1L, yi);
            a.setFloat(idx2, xr);
            a.setFloat(idx2 + 1L, xi);
            j1 -= nh - nm;
            k1 += 2L * nm - 2L;
            idx1 = offa + j1;
            idx2 = offa + k1;
            xr = a.getFloat(idx1);
            xi = a.getFloat(idx1 + 1L);
            yr = a.getFloat(idx2);
            yi = a.getFloat(idx2 + 1L);
            a.setFloat(idx1, yr);
            a.setFloat(idx1 + 1L, yi);
            a.setFloat(idx2, xr);
            a.setFloat(idx2 + 1L, xi);
         }
      } else {
         for(long k = 0L; k < m; ++k) {
            long idx0 = 4L * k;

            for(long j = 0L; j < k; ++j) {
               long j1 = 4L * j + ip.getLong(m + k);
               long k1 = idx0 + ip.getLong(m + j);
               long idx1 = offa + j1;
               long idx2 = offa + k1;
               float xr = a.getFloat(idx1);
               float xi = a.getFloat(idx1 + 1L);
               float yr = a.getFloat(idx2);
               float yi = a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
               j1 += nm;
               k1 += nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getFloat(idx1);
               xi = a.getFloat(idx1 + 1L);
               yr = a.getFloat(idx2);
               yi = a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
               j1 += nh;
               k1 += 2L;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getFloat(idx1);
               xi = a.getFloat(idx1 + 1L);
               yr = a.getFloat(idx2);
               yi = a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
               j1 -= nm;
               k1 -= nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getFloat(idx1);
               xi = a.getFloat(idx1 + 1L);
               yr = a.getFloat(idx2);
               yi = a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
               j1 += 2L;
               k1 += nh;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getFloat(idx1);
               xi = a.getFloat(idx1 + 1L);
               yr = a.getFloat(idx2);
               yi = a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
               j1 += nm;
               k1 += nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getFloat(idx1);
               xi = a.getFloat(idx1 + 1L);
               yr = a.getFloat(idx2);
               yi = a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
               j1 -= nh;
               k1 -= 2L;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getFloat(idx1);
               xi = a.getFloat(idx1 + 1L);
               yr = a.getFloat(idx2);
               yi = a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
               j1 -= nm;
               k1 -= nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getFloat(idx1);
               xi = a.getFloat(idx1 + 1L);
               yr = a.getFloat(idx2);
               yi = a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
            }

            long k1 = idx0 + ip.getLong(m + k);
            long j1 = k1 + 2L;
            k1 += nh;
            long idx1 = offa + j1;
            long idx2 = offa + k1;
            float xr = a.getFloat(idx1);
            float xi = a.getFloat(idx1 + 1L);
            float yr = a.getFloat(idx2);
            float yi = a.getFloat(idx2 + 1L);
            a.setFloat(idx1, yr);
            a.setFloat(idx1 + 1L, yi);
            a.setFloat(idx2, xr);
            a.setFloat(idx2 + 1L, xi);
            j1 += nm;
            k1 += nm;
            idx1 = offa + j1;
            idx2 = offa + k1;
            xr = a.getFloat(idx1);
            xi = a.getFloat(idx1 + 1L);
            yr = a.getFloat(idx2);
            yi = a.getFloat(idx2 + 1L);
            a.setFloat(idx1, yr);
            a.setFloat(idx1 + 1L, yi);
            a.setFloat(idx2, xr);
            a.setFloat(idx2 + 1L, xi);
         }
      }

   }

   public static void bitrv2conj(int n, int[] ip, float[] a, int offa) {
      int m = 1;

      int l;
      for(l = n >> 2; l > 8; l >>= 2) {
         m <<= 1;
      }

      int nh = n >> 1;
      int nm = 4 * m;
      if (l == 8) {
         for(int k = 0; k < m; ++k) {
            int idx0 = 4 * k;

            for(int j = 0; j < k; ++j) {
               int j1 = 4 * j + 2 * ip[m + k];
               int k1 = idx0 + 2 * ip[m + j];
               int idx1 = offa + j1;
               int idx2 = offa + k1;
               float xr = a[idx1];
               float xi = -a[idx1 + 1];
               float yr = a[idx2];
               float yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += nm;
               k1 += 2 * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = -a[idx1 + 1];
               yr = a[idx2];
               yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += nm;
               k1 -= nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = -a[idx1 + 1];
               yr = a[idx2];
               yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += nm;
               k1 += 2 * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = -a[idx1 + 1];
               yr = a[idx2];
               yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += nh;
               k1 += 2;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = -a[idx1 + 1];
               yr = a[idx2];
               yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 -= nm;
               k1 -= 2 * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = -a[idx1 + 1];
               yr = a[idx2];
               yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 -= nm;
               k1 += nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = -a[idx1 + 1];
               yr = a[idx2];
               yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 -= nm;
               k1 -= 2 * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = -a[idx1 + 1];
               yr = a[idx2];
               yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += 2;
               k1 += nh;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = -a[idx1 + 1];
               yr = a[idx2];
               yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += nm;
               k1 += 2 * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = -a[idx1 + 1];
               yr = a[idx2];
               yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += nm;
               k1 -= nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = -a[idx1 + 1];
               yr = a[idx2];
               yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += nm;
               k1 += 2 * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = -a[idx1 + 1];
               yr = a[idx2];
               yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 -= nh;
               k1 -= 2;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = -a[idx1 + 1];
               yr = a[idx2];
               yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 -= nm;
               k1 -= 2 * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = -a[idx1 + 1];
               yr = a[idx2];
               yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 -= nm;
               k1 += nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = -a[idx1 + 1];
               yr = a[idx2];
               yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 -= nm;
               k1 -= 2 * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = -a[idx1 + 1];
               yr = a[idx2];
               yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
            }

            int k1 = idx0 + 2 * ip[m + k];
            int j1 = k1 + 2;
            k1 += nh;
            int idx1 = offa + j1;
            int idx2 = offa + k1;
            a[idx1 - 1] = -a[idx1 - 1];
            float xr = a[idx1];
            float xi = -a[idx1 + 1];
            float yr = a[idx2];
            float yi = -a[idx2 + 1];
            a[idx1] = yr;
            a[idx1 + 1] = yi;
            a[idx2] = xr;
            a[idx2 + 1] = xi;
            a[idx2 + 3] = -a[idx2 + 3];
            j1 += nm;
            k1 += 2 * nm;
            idx1 = offa + j1;
            idx2 = offa + k1;
            xr = a[idx1];
            xi = -a[idx1 + 1];
            yr = a[idx2];
            yi = -a[idx2 + 1];
            a[idx1] = yr;
            a[idx1 + 1] = yi;
            a[idx2] = xr;
            a[idx2 + 1] = xi;
            j1 += nm;
            k1 -= nm;
            idx1 = offa + j1;
            idx2 = offa + k1;
            xr = a[idx1];
            xi = -a[idx1 + 1];
            yr = a[idx2];
            yi = -a[idx2 + 1];
            a[idx1] = yr;
            a[idx1 + 1] = yi;
            a[idx2] = xr;
            a[idx2 + 1] = xi;
            j1 -= 2;
            k1 -= nh;
            idx1 = offa + j1;
            idx2 = offa + k1;
            xr = a[idx1];
            xi = -a[idx1 + 1];
            yr = a[idx2];
            yi = -a[idx2 + 1];
            a[idx1] = yr;
            a[idx1 + 1] = yi;
            a[idx2] = xr;
            a[idx2 + 1] = xi;
            j1 += nh + 2;
            k1 += nh + 2;
            idx1 = offa + j1;
            idx2 = offa + k1;
            xr = a[idx1];
            xi = -a[idx1 + 1];
            yr = a[idx2];
            yi = -a[idx2 + 1];
            a[idx1] = yr;
            a[idx1 + 1] = yi;
            a[idx2] = xr;
            a[idx2 + 1] = xi;
            j1 -= nh - nm;
            k1 += 2 * nm - 2;
            idx1 = offa + j1;
            idx2 = offa + k1;
            a[idx1 - 1] = -a[idx1 - 1];
            xr = a[idx1];
            xi = -a[idx1 + 1];
            yr = a[idx2];
            yi = -a[idx2 + 1];
            a[idx1] = yr;
            a[idx1 + 1] = yi;
            a[idx2] = xr;
            a[idx2 + 1] = xi;
            a[idx2 + 3] = -a[idx2 + 3];
         }
      } else {
         for(int k = 0; k < m; ++k) {
            int idx0 = 4 * k;

            for(int j = 0; j < k; ++j) {
               int j1 = 4 * j + ip[m + k];
               int k1 = idx0 + ip[m + j];
               int idx1 = offa + j1;
               int idx2 = offa + k1;
               float xr = a[idx1];
               float xi = -a[idx1 + 1];
               float yr = a[idx2];
               float yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += nm;
               k1 += nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = -a[idx1 + 1];
               yr = a[idx2];
               yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += nh;
               k1 += 2;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = -a[idx1 + 1];
               yr = a[idx2];
               yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 -= nm;
               k1 -= nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = -a[idx1 + 1];
               yr = a[idx2];
               yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += 2;
               k1 += nh;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = -a[idx1 + 1];
               yr = a[idx2];
               yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 += nm;
               k1 += nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = -a[idx1 + 1];
               yr = a[idx2];
               yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 -= nh;
               k1 -= 2;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = -a[idx1 + 1];
               yr = a[idx2];
               yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
               j1 -= nm;
               k1 -= nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a[idx1];
               xi = -a[idx1 + 1];
               yr = a[idx2];
               yi = -a[idx2 + 1];
               a[idx1] = yr;
               a[idx1 + 1] = yi;
               a[idx2] = xr;
               a[idx2 + 1] = xi;
            }

            int k1 = idx0 + ip[m + k];
            int j1 = k1 + 2;
            k1 += nh;
            int idx1 = offa + j1;
            int idx2 = offa + k1;
            a[idx1 - 1] = -a[idx1 - 1];
            float xr = a[idx1];
            float xi = -a[idx1 + 1];
            float yr = a[idx2];
            float yi = -a[idx2 + 1];
            a[idx1] = yr;
            a[idx1 + 1] = yi;
            a[idx2] = xr;
            a[idx2 + 1] = xi;
            a[idx2 + 3] = -a[idx2 + 3];
            j1 += nm;
            k1 += nm;
            idx1 = offa + j1;
            idx2 = offa + k1;
            a[idx1 - 1] = -a[idx1 - 1];
            xr = a[idx1];
            xi = -a[idx1 + 1];
            yr = a[idx2];
            yi = -a[idx2 + 1];
            a[idx1] = yr;
            a[idx1 + 1] = yi;
            a[idx2] = xr;
            a[idx2 + 1] = xi;
            a[idx2 + 3] = -a[idx2 + 3];
         }
      }

   }

   public static void bitrv2conj(long n, LongLargeArray ip, FloatLargeArray a, long offa) {
      long m = 1L;

      long l;
      for(l = n >> 2; l > 8L; l >>= 2) {
         m <<= 1;
      }

      long nh = n >> 1;
      long nm = 4L * m;
      if (l == 8L) {
         for(long k = 0L; k < m; ++k) {
            long idx0 = 4L * k;

            for(long j = 0L; j < k; ++j) {
               long j1 = 4L * j + 2L * ip.getLong(m + k);
               long k1 = idx0 + 2L * ip.getLong(m + j);
               long idx1 = offa + j1;
               long idx2 = offa + k1;
               float xr = a.getFloat(idx1);
               float xi = -a.getFloat(idx1 + 1L);
               float yr = a.getFloat(idx2);
               float yi = -a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
               j1 += nm;
               k1 += 2L * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getFloat(idx1);
               xi = -a.getFloat(idx1 + 1L);
               yr = a.getFloat(idx2);
               yi = -a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
               j1 += nm;
               k1 -= nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getFloat(idx1);
               xi = -a.getFloat(idx1 + 1L);
               yr = a.getFloat(idx2);
               yi = -a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
               j1 += nm;
               k1 += 2L * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getFloat(idx1);
               xi = -a.getFloat(idx1 + 1L);
               yr = a.getFloat(idx2);
               yi = -a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
               j1 += nh;
               k1 += 2L;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getFloat(idx1);
               xi = -a.getFloat(idx1 + 1L);
               yr = a.getFloat(idx2);
               yi = -a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
               j1 -= nm;
               k1 -= 2L * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getFloat(idx1);
               xi = -a.getFloat(idx1 + 1L);
               yr = a.getFloat(idx2);
               yi = -a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
               j1 -= nm;
               k1 += nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getFloat(idx1);
               xi = -a.getFloat(idx1 + 1L);
               yr = a.getFloat(idx2);
               yi = -a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
               j1 -= nm;
               k1 -= 2L * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getFloat(idx1);
               xi = -a.getFloat(idx1 + 1L);
               yr = a.getFloat(idx2);
               yi = -a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
               j1 += 2L;
               k1 += nh;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getFloat(idx1);
               xi = -a.getFloat(idx1 + 1L);
               yr = a.getFloat(idx2);
               yi = -a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
               j1 += nm;
               k1 += 2L * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getFloat(idx1);
               xi = -a.getFloat(idx1 + 1L);
               yr = a.getFloat(idx2);
               yi = -a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
               j1 += nm;
               k1 -= nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getFloat(idx1);
               xi = -a.getFloat(idx1 + 1L);
               yr = a.getFloat(idx2);
               yi = -a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
               j1 += nm;
               k1 += 2L * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getFloat(idx1);
               xi = -a.getFloat(idx1 + 1L);
               yr = a.getFloat(idx2);
               yi = -a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
               j1 -= nh;
               k1 -= 2L;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getFloat(idx1);
               xi = -a.getFloat(idx1 + 1L);
               yr = a.getFloat(idx2);
               yi = -a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
               j1 -= nm;
               k1 -= 2L * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getFloat(idx1);
               xi = -a.getFloat(idx1 + 1L);
               yr = a.getFloat(idx2);
               yi = -a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
               j1 -= nm;
               k1 += nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getFloat(idx1);
               xi = -a.getFloat(idx1 + 1L);
               yr = a.getFloat(idx2);
               yi = -a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
               j1 -= nm;
               k1 -= 2L * nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getFloat(idx1);
               xi = -a.getFloat(idx1 + 1L);
               yr = a.getFloat(idx2);
               yi = -a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
            }

            long k1 = idx0 + 2L * ip.getLong(m + k);
            long j1 = k1 + 2L;
            k1 += nh;
            long idx1 = offa + j1;
            long idx2 = offa + k1;
            a.setFloat(idx1 - 1L, -a.getFloat(idx1 - 1L));
            float xr = a.getFloat(idx1);
            float xi = -a.getFloat(idx1 + 1L);
            float yr = a.getFloat(idx2);
            float yi = -a.getFloat(idx2 + 1L);
            a.setFloat(idx1, yr);
            a.setFloat(idx1 + 1L, yi);
            a.setFloat(idx2, xr);
            a.setFloat(idx2 + 1L, xi);
            a.setFloat(idx2 + 3L, -a.getFloat(idx2 + 3L));
            j1 += nm;
            k1 += 2L * nm;
            idx1 = offa + j1;
            idx2 = offa + k1;
            xr = a.getFloat(idx1);
            xi = -a.getFloat(idx1 + 1L);
            yr = a.getFloat(idx2);
            yi = -a.getFloat(idx2 + 1L);
            a.setFloat(idx1, yr);
            a.setFloat(idx1 + 1L, yi);
            a.setFloat(idx2, xr);
            a.setFloat(idx2 + 1L, xi);
            j1 += nm;
            k1 -= nm;
            idx1 = offa + j1;
            idx2 = offa + k1;
            xr = a.getFloat(idx1);
            xi = -a.getFloat(idx1 + 1L);
            yr = a.getFloat(idx2);
            yi = -a.getFloat(idx2 + 1L);
            a.setFloat(idx1, yr);
            a.setFloat(idx1 + 1L, yi);
            a.setFloat(idx2, xr);
            a.setFloat(idx2 + 1L, xi);
            j1 -= 2L;
            k1 -= nh;
            idx1 = offa + j1;
            idx2 = offa + k1;
            xr = a.getFloat(idx1);
            xi = -a.getFloat(idx1 + 1L);
            yr = a.getFloat(idx2);
            yi = -a.getFloat(idx2 + 1L);
            a.setFloat(idx1, yr);
            a.setFloat(idx1 + 1L, yi);
            a.setFloat(idx2, xr);
            a.setFloat(idx2 + 1L, xi);
            j1 += nh + 2L;
            k1 += nh + 2L;
            idx1 = offa + j1;
            idx2 = offa + k1;
            xr = a.getFloat(idx1);
            xi = -a.getFloat(idx1 + 1L);
            yr = a.getFloat(idx2);
            yi = -a.getFloat(idx2 + 1L);
            a.setFloat(idx1, yr);
            a.setFloat(idx1 + 1L, yi);
            a.setFloat(idx2, xr);
            a.setFloat(idx2 + 1L, xi);
            j1 -= nh - nm;
            k1 += 2L * nm - 2L;
            idx1 = offa + j1;
            idx2 = offa + k1;
            a.setFloat(idx1 - 1L, -a.getFloat(idx1 - 1L));
            xr = a.getFloat(idx1);
            xi = -a.getFloat(idx1 + 1L);
            yr = a.getFloat(idx2);
            yi = -a.getFloat(idx2 + 1L);
            a.setFloat(idx1, yr);
            a.setFloat(idx1 + 1L, yi);
            a.setFloat(idx2, xr);
            a.setFloat(idx2 + 1L, xi);
            a.setFloat(idx2 + 3L, -a.getFloat(idx2 + 3L));
         }
      } else {
         for(int k = 0; (long)k < m; ++k) {
            long idx0 = (long)(4 * k);

            for(int j = 0; j < k; ++j) {
               long j1 = (long)(4 * j) + ip.getLong(m + (long)k);
               long k1 = idx0 + ip.getLong(m + (long)j);
               long idx1 = offa + j1;
               long idx2 = offa + k1;
               float xr = a.getFloat(idx1);
               float xi = -a.getFloat(idx1 + 1L);
               float yr = a.getFloat(idx2);
               float yi = -a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
               j1 += nm;
               k1 += nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getFloat(idx1);
               xi = -a.getFloat(idx1 + 1L);
               yr = a.getFloat(idx2);
               yi = -a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
               j1 += nh;
               k1 += 2L;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getFloat(idx1);
               xi = -a.getFloat(idx1 + 1L);
               yr = a.getFloat(idx2);
               yi = -a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
               j1 -= nm;
               k1 -= nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getFloat(idx1);
               xi = -a.getFloat(idx1 + 1L);
               yr = a.getFloat(idx2);
               yi = -a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
               j1 += 2L;
               k1 += nh;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getFloat(idx1);
               xi = -a.getFloat(idx1 + 1L);
               yr = a.getFloat(idx2);
               yi = -a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
               j1 += nm;
               k1 += nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getFloat(idx1);
               xi = -a.getFloat(idx1 + 1L);
               yr = a.getFloat(idx2);
               yi = -a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
               j1 -= nh;
               k1 -= 2L;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getFloat(idx1);
               xi = -a.getFloat(idx1 + 1L);
               yr = a.getFloat(idx2);
               yi = -a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
               j1 -= nm;
               k1 -= nm;
               idx1 = offa + j1;
               idx2 = offa + k1;
               xr = a.getFloat(idx1);
               xi = -a.getFloat(idx1 + 1L);
               yr = a.getFloat(idx2);
               yi = -a.getFloat(idx2 + 1L);
               a.setFloat(idx1, yr);
               a.setFloat(idx1 + 1L, yi);
               a.setFloat(idx2, xr);
               a.setFloat(idx2 + 1L, xi);
            }

            long k1 = idx0 + ip.getLong(m + (long)k);
            long j1 = k1 + 2L;
            k1 += nh;
            long idx1 = offa + j1;
            long idx2 = offa + k1;
            a.setFloat(idx1 - 1L, -a.getFloat(idx1 - 1L));
            float xr = a.getFloat(idx1);
            float xi = -a.getFloat(idx1 + 1L);
            float yr = a.getFloat(idx2);
            float yi = -a.getFloat(idx2 + 1L);
            a.setFloat(idx1, yr);
            a.setFloat(idx1 + 1L, yi);
            a.setFloat(idx2, xr);
            a.setFloat(idx2 + 1L, xi);
            a.setFloat(idx2 + 3L, -a.getFloat(idx2 + 3L));
            j1 += nm;
            k1 += nm;
            idx1 = offa + j1;
            idx2 = offa + k1;
            a.setFloat(idx1 - 1L, -a.getFloat(idx1 - 1L));
            xr = a.getFloat(idx1);
            xi = -a.getFloat(idx1 + 1L);
            yr = a.getFloat(idx2);
            yi = -a.getFloat(idx2 + 1L);
            a.setFloat(idx1, yr);
            a.setFloat(idx1 + 1L, yi);
            a.setFloat(idx2, xr);
            a.setFloat(idx2 + 1L, xi);
            a.setFloat(idx2 + 3L, -a.getFloat(idx2 + 3L));
         }
      }

   }

   public static void bitrv216(float[] a, int offa) {
      float x1r = a[offa + 2];
      float x1i = a[offa + 3];
      float x2r = a[offa + 4];
      float x2i = a[offa + 5];
      float x3r = a[offa + 6];
      float x3i = a[offa + 7];
      float x4r = a[offa + 8];
      float x4i = a[offa + 9];
      float x5r = a[offa + 10];
      float x5i = a[offa + 11];
      float x7r = a[offa + 14];
      float x7i = a[offa + 15];
      float x8r = a[offa + 16];
      float x8i = a[offa + 17];
      float x10r = a[offa + 20];
      float x10i = a[offa + 21];
      float x11r = a[offa + 22];
      float x11i = a[offa + 23];
      float x12r = a[offa + 24];
      float x12i = a[offa + 25];
      float x13r = a[offa + 26];
      float x13i = a[offa + 27];
      float x14r = a[offa + 28];
      float x14i = a[offa + 29];
      a[offa + 2] = x8r;
      a[offa + 3] = x8i;
      a[offa + 4] = x4r;
      a[offa + 5] = x4i;
      a[offa + 6] = x12r;
      a[offa + 7] = x12i;
      a[offa + 8] = x2r;
      a[offa + 9] = x2i;
      a[offa + 10] = x10r;
      a[offa + 11] = x10i;
      a[offa + 14] = x14r;
      a[offa + 15] = x14i;
      a[offa + 16] = x1r;
      a[offa + 17] = x1i;
      a[offa + 20] = x5r;
      a[offa + 21] = x5i;
      a[offa + 22] = x13r;
      a[offa + 23] = x13i;
      a[offa + 24] = x3r;
      a[offa + 25] = x3i;
      a[offa + 26] = x11r;
      a[offa + 27] = x11i;
      a[offa + 28] = x7r;
      a[offa + 29] = x7i;
   }

   public static void bitrv216(FloatLargeArray a, long offa) {
      float x1r = a.getFloat(offa + 2L);
      float x1i = a.getFloat(offa + 3L);
      float x2r = a.getFloat(offa + 4L);
      float x2i = a.getFloat(offa + 5L);
      float x3r = a.getFloat(offa + 6L);
      float x3i = a.getFloat(offa + 7L);
      float x4r = a.getFloat(offa + 8L);
      float x4i = a.getFloat(offa + 9L);
      float x5r = a.getFloat(offa + 10L);
      float x5i = a.getFloat(offa + 11L);
      float x7r = a.getFloat(offa + 14L);
      float x7i = a.getFloat(offa + 15L);
      float x8r = a.getFloat(offa + 16L);
      float x8i = a.getFloat(offa + 17L);
      float x10r = a.getFloat(offa + 20L);
      float x10i = a.getFloat(offa + 21L);
      float x11r = a.getFloat(offa + 22L);
      float x11i = a.getFloat(offa + 23L);
      float x12r = a.getFloat(offa + 24L);
      float x12i = a.getFloat(offa + 25L);
      float x13r = a.getFloat(offa + 26L);
      float x13i = a.getFloat(offa + 27L);
      float x14r = a.getFloat(offa + 28L);
      float x14i = a.getFloat(offa + 29L);
      a.setFloat(offa + 2L, x8r);
      a.setFloat(offa + 3L, x8i);
      a.setFloat(offa + 4L, x4r);
      a.setFloat(offa + 5L, x4i);
      a.setFloat(offa + 6L, x12r);
      a.setFloat(offa + 7L, x12i);
      a.setFloat(offa + 8L, x2r);
      a.setFloat(offa + 9L, x2i);
      a.setFloat(offa + 10L, x10r);
      a.setFloat(offa + 11L, x10i);
      a.setFloat(offa + 14L, x14r);
      a.setFloat(offa + 15L, x14i);
      a.setFloat(offa + 16L, x1r);
      a.setFloat(offa + 17L, x1i);
      a.setFloat(offa + 20L, x5r);
      a.setFloat(offa + 21L, x5i);
      a.setFloat(offa + 22L, x13r);
      a.setFloat(offa + 23L, x13i);
      a.setFloat(offa + 24L, x3r);
      a.setFloat(offa + 25L, x3i);
      a.setFloat(offa + 26L, x11r);
      a.setFloat(offa + 27L, x11i);
      a.setFloat(offa + 28L, x7r);
      a.setFloat(offa + 29L, x7i);
   }

   public static void bitrv216neg(float[] a, int offa) {
      float x1r = a[offa + 2];
      float x1i = a[offa + 3];
      float x2r = a[offa + 4];
      float x2i = a[offa + 5];
      float x3r = a[offa + 6];
      float x3i = a[offa + 7];
      float x4r = a[offa + 8];
      float x4i = a[offa + 9];
      float x5r = a[offa + 10];
      float x5i = a[offa + 11];
      float x6r = a[offa + 12];
      float x6i = a[offa + 13];
      float x7r = a[offa + 14];
      float x7i = a[offa + 15];
      float x8r = a[offa + 16];
      float x8i = a[offa + 17];
      float x9r = a[offa + 18];
      float x9i = a[offa + 19];
      float x10r = a[offa + 20];
      float x10i = a[offa + 21];
      float x11r = a[offa + 22];
      float x11i = a[offa + 23];
      float x12r = a[offa + 24];
      float x12i = a[offa + 25];
      float x13r = a[offa + 26];
      float x13i = a[offa + 27];
      float x14r = a[offa + 28];
      float x14i = a[offa + 29];
      float x15r = a[offa + 30];
      float x15i = a[offa + 31];
      a[offa + 2] = x15r;
      a[offa + 3] = x15i;
      a[offa + 4] = x7r;
      a[offa + 5] = x7i;
      a[offa + 6] = x11r;
      a[offa + 7] = x11i;
      a[offa + 8] = x3r;
      a[offa + 9] = x3i;
      a[offa + 10] = x13r;
      a[offa + 11] = x13i;
      a[offa + 12] = x5r;
      a[offa + 13] = x5i;
      a[offa + 14] = x9r;
      a[offa + 15] = x9i;
      a[offa + 16] = x1r;
      a[offa + 17] = x1i;
      a[offa + 18] = x14r;
      a[offa + 19] = x14i;
      a[offa + 20] = x6r;
      a[offa + 21] = x6i;
      a[offa + 22] = x10r;
      a[offa + 23] = x10i;
      a[offa + 24] = x2r;
      a[offa + 25] = x2i;
      a[offa + 26] = x12r;
      a[offa + 27] = x12i;
      a[offa + 28] = x4r;
      a[offa + 29] = x4i;
      a[offa + 30] = x8r;
      a[offa + 31] = x8i;
   }

   public static void bitrv216neg(FloatLargeArray a, long offa) {
      float x1r = a.getFloat(offa + 2L);
      float x1i = a.getFloat(offa + 3L);
      float x2r = a.getFloat(offa + 4L);
      float x2i = a.getFloat(offa + 5L);
      float x3r = a.getFloat(offa + 6L);
      float x3i = a.getFloat(offa + 7L);
      float x4r = a.getFloat(offa + 8L);
      float x4i = a.getFloat(offa + 9L);
      float x5r = a.getFloat(offa + 10L);
      float x5i = a.getFloat(offa + 11L);
      float x6r = a.getFloat(offa + 12L);
      float x6i = a.getFloat(offa + 13L);
      float x7r = a.getFloat(offa + 14L);
      float x7i = a.getFloat(offa + 15L);
      float x8r = a.getFloat(offa + 16L);
      float x8i = a.getFloat(offa + 17L);
      float x9r = a.getFloat(offa + 18L);
      float x9i = a.getFloat(offa + 19L);
      float x10r = a.getFloat(offa + 20L);
      float x10i = a.getFloat(offa + 21L);
      float x11r = a.getFloat(offa + 22L);
      float x11i = a.getFloat(offa + 23L);
      float x12r = a.getFloat(offa + 24L);
      float x12i = a.getFloat(offa + 25L);
      float x13r = a.getFloat(offa + 26L);
      float x13i = a.getFloat(offa + 27L);
      float x14r = a.getFloat(offa + 28L);
      float x14i = a.getFloat(offa + 29L);
      float x15r = a.getFloat(offa + 30L);
      float x15i = a.getFloat(offa + 31L);
      a.setFloat(offa + 2L, x15r);
      a.setFloat(offa + 3L, x15i);
      a.setFloat(offa + 4L, x7r);
      a.setFloat(offa + 5L, x7i);
      a.setFloat(offa + 6L, x11r);
      a.setFloat(offa + 7L, x11i);
      a.setFloat(offa + 8L, x3r);
      a.setFloat(offa + 9L, x3i);
      a.setFloat(offa + 10L, x13r);
      a.setFloat(offa + 11L, x13i);
      a.setFloat(offa + 12L, x5r);
      a.setFloat(offa + 13L, x5i);
      a.setFloat(offa + 14L, x9r);
      a.setFloat(offa + 15L, x9i);
      a.setFloat(offa + 16L, x1r);
      a.setFloat(offa + 17L, x1i);
      a.setFloat(offa + 18L, x14r);
      a.setFloat(offa + 19L, x14i);
      a.setFloat(offa + 20L, x6r);
      a.setFloat(offa + 21L, x6i);
      a.setFloat(offa + 22L, x10r);
      a.setFloat(offa + 23L, x10i);
      a.setFloat(offa + 24L, x2r);
      a.setFloat(offa + 25L, x2i);
      a.setFloat(offa + 26L, x12r);
      a.setFloat(offa + 27L, x12i);
      a.setFloat(offa + 28L, x4r);
      a.setFloat(offa + 29L, x4i);
      a.setFloat(offa + 30L, x8r);
      a.setFloat(offa + 31L, x8i);
   }

   public static void bitrv208(float[] a, int offa) {
      float x1r = a[offa + 2];
      float x1i = a[offa + 3];
      float x3r = a[offa + 6];
      float x3i = a[offa + 7];
      float x4r = a[offa + 8];
      float x4i = a[offa + 9];
      float x6r = a[offa + 12];
      float x6i = a[offa + 13];
      a[offa + 2] = x4r;
      a[offa + 3] = x4i;
      a[offa + 6] = x6r;
      a[offa + 7] = x6i;
      a[offa + 8] = x1r;
      a[offa + 9] = x1i;
      a[offa + 12] = x3r;
      a[offa + 13] = x3i;
   }

   public static void bitrv208(FloatLargeArray a, long offa) {
      float x1r = a.getFloat(offa + 2L);
      float x1i = a.getFloat(offa + 3L);
      float x3r = a.getFloat(offa + 6L);
      float x3i = a.getFloat(offa + 7L);
      float x4r = a.getFloat(offa + 8L);
      float x4i = a.getFloat(offa + 9L);
      float x6r = a.getFloat(offa + 12L);
      float x6i = a.getFloat(offa + 13L);
      a.setFloat(offa + 2L, x4r);
      a.setFloat(offa + 3L, x4i);
      a.setFloat(offa + 6L, x6r);
      a.setFloat(offa + 7L, x6i);
      a.setFloat(offa + 8L, x1r);
      a.setFloat(offa + 9L, x1i);
      a.setFloat(offa + 12L, x3r);
      a.setFloat(offa + 13L, x3i);
   }

   public static void bitrv208neg(float[] a, int offa) {
      float x1r = a[offa + 2];
      float x1i = a[offa + 3];
      float x2r = a[offa + 4];
      float x2i = a[offa + 5];
      float x3r = a[offa + 6];
      float x3i = a[offa + 7];
      float x4r = a[offa + 8];
      float x4i = a[offa + 9];
      float x5r = a[offa + 10];
      float x5i = a[offa + 11];
      float x6r = a[offa + 12];
      float x6i = a[offa + 13];
      float x7r = a[offa + 14];
      float x7i = a[offa + 15];
      a[offa + 2] = x7r;
      a[offa + 3] = x7i;
      a[offa + 4] = x3r;
      a[offa + 5] = x3i;
      a[offa + 6] = x5r;
      a[offa + 7] = x5i;
      a[offa + 8] = x1r;
      a[offa + 9] = x1i;
      a[offa + 10] = x6r;
      a[offa + 11] = x6i;
      a[offa + 12] = x2r;
      a[offa + 13] = x2i;
      a[offa + 14] = x4r;
      a[offa + 15] = x4i;
   }

   public static void bitrv208neg(FloatLargeArray a, long offa) {
      float x1r = a.getFloat(offa + 2L);
      float x1i = a.getFloat(offa + 3L);
      float x2r = a.getFloat(offa + 4L);
      float x2i = a.getFloat(offa + 5L);
      float x3r = a.getFloat(offa + 6L);
      float x3i = a.getFloat(offa + 7L);
      float x4r = a.getFloat(offa + 8L);
      float x4i = a.getFloat(offa + 9L);
      float x5r = a.getFloat(offa + 10L);
      float x5i = a.getFloat(offa + 11L);
      float x6r = a.getFloat(offa + 12L);
      float x6i = a.getFloat(offa + 13L);
      float x7r = a.getFloat(offa + 14L);
      float x7i = a.getFloat(offa + 15L);
      a.setFloat(offa + 2L, x7r);
      a.setFloat(offa + 3L, x7i);
      a.setFloat(offa + 4L, x3r);
      a.setFloat(offa + 5L, x3i);
      a.setFloat(offa + 6L, x5r);
      a.setFloat(offa + 7L, x5i);
      a.setFloat(offa + 8L, x1r);
      a.setFloat(offa + 9L, x1i);
      a.setFloat(offa + 10L, x6r);
      a.setFloat(offa + 11L, x6i);
      a.setFloat(offa + 12L, x2r);
      a.setFloat(offa + 13L, x2i);
      a.setFloat(offa + 14L, x4r);
      a.setFloat(offa + 15L, x4i);
   }

   public static void cftf1st(int n, float[] a, int offa, float[] w, int startw) {
      int mh = n >> 3;
      int m = 2 * mh;
      int j2 = m + m;
      int j3 = j2 + m;
      int idx1 = offa + m;
      int idx2 = offa + j2;
      int idx3 = offa + j3;
      float x0r = a[offa] + a[idx2];
      float x0i = a[offa + 1] + a[idx2 + 1];
      float x1r = a[offa] - a[idx2];
      float x1i = a[offa + 1] - a[idx2 + 1];
      float x2r = a[idx1] + a[idx3];
      float x2i = a[idx1 + 1] + a[idx3 + 1];
      float x3r = a[idx1] - a[idx3];
      float x3i = a[idx1 + 1] - a[idx3 + 1];
      a[offa] = x0r + x2r;
      a[offa + 1] = x0i + x2i;
      a[idx1] = x0r - x2r;
      a[idx1 + 1] = x0i - x2i;
      a[idx2] = x1r - x3i;
      a[idx2 + 1] = x1i + x3r;
      a[idx3] = x1r + x3i;
      a[idx3 + 1] = x1i - x3r;
      float wn4r = w[startw + 1];
      float csc1 = w[startw + 2];
      float csc3 = w[startw + 3];
      float wd1r = 1.0F;
      float wd1i = 0.0F;
      float wd3r = 1.0F;
      float wd3i = 0.0F;
      int k = 0;

      for(int j = 2; j < mh - 2; j += 4) {
         k += 4;
         int idx4 = startw + k;
         float wk1r = csc1 * (wd1r + w[idx4]);
         float wk1i = csc1 * (wd1i + w[idx4 + 1]);
         float wk3r = csc3 * (wd3r + w[idx4 + 2]);
         float wk3i = csc3 * (wd3i + w[idx4 + 3]);
         wd1r = w[idx4];
         wd1i = w[idx4 + 1];
         wd3r = w[idx4 + 2];
         wd3i = w[idx4 + 3];
         int j1 = j + m;
         j2 = j1 + m;
         j3 = j2 + m;
         idx1 = offa + j1;
         idx2 = offa + j2;
         idx3 = offa + j3;
         int idx5 = offa + j;
         x0r = a[idx5] + a[idx2];
         x0i = a[idx5 + 1] + a[idx2 + 1];
         x1r = a[idx5] - a[idx2];
         x1i = a[idx5 + 1] - a[idx2 + 1];
         float y0r = a[idx5 + 2] + a[idx2 + 2];
         float y0i = a[idx5 + 3] + a[idx2 + 3];
         float y1r = a[idx5 + 2] - a[idx2 + 2];
         float y1i = a[idx5 + 3] - a[idx2 + 3];
         x2r = a[idx1] + a[idx3];
         x2i = a[idx1 + 1] + a[idx3 + 1];
         x3r = a[idx1] - a[idx3];
         x3i = a[idx1 + 1] - a[idx3 + 1];
         float y2r = a[idx1 + 2] + a[idx3 + 2];
         float y2i = a[idx1 + 3] + a[idx3 + 3];
         float y3r = a[idx1 + 2] - a[idx3 + 2];
         float y3i = a[idx1 + 3] - a[idx3 + 3];
         a[idx5] = x0r + x2r;
         a[idx5 + 1] = x0i + x2i;
         a[idx5 + 2] = y0r + y2r;
         a[idx5 + 3] = y0i + y2i;
         a[idx1] = x0r - x2r;
         a[idx1 + 1] = x0i - x2i;
         a[idx1 + 2] = y0r - y2r;
         a[idx1 + 3] = y0i - y2i;
         x0r = x1r - x3i;
         x0i = x1i + x3r;
         a[idx2] = wk1r * x0r - wk1i * x0i;
         a[idx2 + 1] = wk1r * x0i + wk1i * x0r;
         x0r = y1r - y3i;
         x0i = y1i + y3r;
         a[idx2 + 2] = wd1r * x0r - wd1i * x0i;
         a[idx2 + 3] = wd1r * x0i + wd1i * x0r;
         x0r = x1r + x3i;
         x0i = x1i - x3r;
         a[idx3] = wk3r * x0r + wk3i * x0i;
         a[idx3 + 1] = wk3r * x0i - wk3i * x0r;
         x0r = y1r + y3i;
         x0i = y1i - y3r;
         a[idx3 + 2] = wd3r * x0r + wd3i * x0i;
         a[idx3 + 3] = wd3r * x0i - wd3i * x0r;
         int j0 = m - j;
         j1 = j0 + m;
         j2 = j1 + m;
         j3 = j2 + m;
         int idx0 = offa + j0;
         idx1 = offa + j1;
         idx2 = offa + j2;
         idx3 = offa + j3;
         x0r = a[idx0] + a[idx2];
         x0i = a[idx0 + 1] + a[idx2 + 1];
         x1r = a[idx0] - a[idx2];
         x1i = a[idx0 + 1] - a[idx2 + 1];
         y0r = a[idx0 - 2] + a[idx2 - 2];
         y0i = a[idx0 - 1] + a[idx2 - 1];
         y1r = a[idx0 - 2] - a[idx2 - 2];
         y1i = a[idx0 - 1] - a[idx2 - 1];
         x2r = a[idx1] + a[idx3];
         x2i = a[idx1 + 1] + a[idx3 + 1];
         x3r = a[idx1] - a[idx3];
         x3i = a[idx1 + 1] - a[idx3 + 1];
         y2r = a[idx1 - 2] + a[idx3 - 2];
         y2i = a[idx1 - 1] + a[idx3 - 1];
         y3r = a[idx1 - 2] - a[idx3 - 2];
         y3i = a[idx1 - 1] - a[idx3 - 1];
         a[idx0] = x0r + x2r;
         a[idx0 + 1] = x0i + x2i;
         a[idx0 - 2] = y0r + y2r;
         a[idx0 - 1] = y0i + y2i;
         a[idx1] = x0r - x2r;
         a[idx1 + 1] = x0i - x2i;
         a[idx1 - 2] = y0r - y2r;
         a[idx1 - 1] = y0i - y2i;
         x0r = x1r - x3i;
         x0i = x1i + x3r;
         a[idx2] = wk1i * x0r - wk1r * x0i;
         a[idx2 + 1] = wk1i * x0i + wk1r * x0r;
         x0r = y1r - y3i;
         x0i = y1i + y3r;
         a[idx2 - 2] = wd1i * x0r - wd1r * x0i;
         a[idx2 - 1] = wd1i * x0i + wd1r * x0r;
         x0r = x1r + x3i;
         x0i = x1i - x3r;
         a[idx3] = wk3i * x0r + wk3r * x0i;
         a[idx3 + 1] = wk3i * x0i - wk3r * x0r;
         x0r = y1r + y3i;
         x0i = y1i - y3r;
         a[offa + j3 - 2] = wd3i * x0r + wd3r * x0i;
         a[offa + j3 - 1] = wd3i * x0i - wd3r * x0r;
      }

      float wk1r = csc1 * (wd1r + wn4r);
      float wk1i = csc1 * (wd1i + wn4r);
      float wk3r = csc3 * (wd3r - wn4r);
      float wk3i = csc3 * (wd3i - wn4r);
      int var47 = mh + m;
      j2 = var47 + m;
      j3 = j2 + m;
      int idx0 = offa + mh;
      idx1 = offa + var47;
      idx2 = offa + j2;
      idx3 = offa + j3;
      x0r = a[idx0 - 2] + a[idx2 - 2];
      x0i = a[idx0 - 1] + a[idx2 - 1];
      x1r = a[idx0 - 2] - a[idx2 - 2];
      x1i = a[idx0 - 1] - a[idx2 - 1];
      x2r = a[idx1 - 2] + a[idx3 - 2];
      x2i = a[idx1 - 1] + a[idx3 - 1];
      x3r = a[idx1 - 2] - a[idx3 - 2];
      x3i = a[idx1 - 1] - a[idx3 - 1];
      a[idx0 - 2] = x0r + x2r;
      a[idx0 - 1] = x0i + x2i;
      a[idx1 - 2] = x0r - x2r;
      a[idx1 - 1] = x0i - x2i;
      x0r = x1r - x3i;
      x0i = x1i + x3r;
      a[idx2 - 2] = wk1r * x0r - wk1i * x0i;
      a[idx2 - 1] = wk1r * x0i + wk1i * x0r;
      x0r = x1r + x3i;
      x0i = x1i - x3r;
      a[idx3 - 2] = wk3r * x0r + wk3i * x0i;
      a[idx3 - 1] = wk3r * x0i - wk3i * x0r;
      x0r = a[idx0] + a[idx2];
      x0i = a[idx0 + 1] + a[idx2 + 1];
      x1r = a[idx0] - a[idx2];
      x1i = a[idx0 + 1] - a[idx2 + 1];
      x2r = a[idx1] + a[idx3];
      x2i = a[idx1 + 1] + a[idx3 + 1];
      x3r = a[idx1] - a[idx3];
      x3i = a[idx1 + 1] - a[idx3 + 1];
      a[idx0] = x0r + x2r;
      a[idx0 + 1] = x0i + x2i;
      a[idx1] = x0r - x2r;
      a[idx1 + 1] = x0i - x2i;
      x0r = x1r - x3i;
      x0i = x1i + x3r;
      a[idx2] = wn4r * (x0r - x0i);
      a[idx2 + 1] = wn4r * (x0i + x0r);
      x0r = x1r + x3i;
      x0i = x1i - x3r;
      a[idx3] = -wn4r * (x0r + x0i);
      a[idx3 + 1] = -wn4r * (x0i - x0r);
      x0r = a[idx0 + 2] + a[idx2 + 2];
      x0i = a[idx0 + 3] + a[idx2 + 3];
      x1r = a[idx0 + 2] - a[idx2 + 2];
      x1i = a[idx0 + 3] - a[idx2 + 3];
      x2r = a[idx1 + 2] + a[idx3 + 2];
      x2i = a[idx1 + 3] + a[idx3 + 3];
      x3r = a[idx1 + 2] - a[idx3 + 2];
      x3i = a[idx1 + 3] - a[idx3 + 3];
      a[idx0 + 2] = x0r + x2r;
      a[idx0 + 3] = x0i + x2i;
      a[idx1 + 2] = x0r - x2r;
      a[idx1 + 3] = x0i - x2i;
      x0r = x1r - x3i;
      x0i = x1i + x3r;
      a[idx2 + 2] = wk1i * x0r - wk1r * x0i;
      a[idx2 + 3] = wk1i * x0i + wk1r * x0r;
      x0r = x1r + x3i;
      x0i = x1i - x3r;
      a[idx3 + 2] = wk3i * x0r + wk3r * x0i;
      a[idx3 + 3] = wk3i * x0i - wk3r * x0r;
   }

   public static void cftf1st(long n, FloatLargeArray a, long offa, FloatLargeArray w, long startw) {
      long mh = n >> 3;
      long m = 2L * mh;
      long j2 = m + m;
      long j3 = j2 + m;
      long idx1 = offa + m;
      long idx2 = offa + j2;
      long idx3 = offa + j3;
      float x0r = a.getFloat(offa) + a.getFloat(idx2);
      float x0i = a.getFloat(offa + 1L) + a.getFloat(idx2 + 1L);
      float x1r = a.getFloat(offa) - a.getFloat(idx2);
      float x1i = a.getFloat(offa + 1L) - a.getFloat(idx2 + 1L);
      float x2r = a.getFloat(idx1) + a.getFloat(idx3);
      float x2i = a.getFloat(idx1 + 1L) + a.getFloat(idx3 + 1L);
      float x3r = a.getFloat(idx1) - a.getFloat(idx3);
      float x3i = a.getFloat(idx1 + 1L) - a.getFloat(idx3 + 1L);
      a.setFloat(offa, x0r + x2r);
      a.setFloat(offa + 1L, x0i + x2i);
      a.setFloat(idx1, x0r - x2r);
      a.setFloat(idx1 + 1L, x0i - x2i);
      a.setFloat(idx2, x1r - x3i);
      a.setFloat(idx2 + 1L, x1i + x3r);
      a.setFloat(idx3, x1r + x3i);
      a.setFloat(idx3 + 1L, x1i - x3r);
      float wn4r = w.getFloat(startw + 1L);
      float csc1 = w.getFloat(startw + 2L);
      float csc3 = w.getFloat(startw + 3L);
      float wd1r = 1.0F;
      float wd1i = 0.0F;
      float wd3r = 1.0F;
      float wd3i = 0.0F;
      long k = 0L;

      for(int j = 2; (long)j < mh - 2L; j += 4) {
         k += 4L;
         long idx4 = startw + k;
         float wk1r = csc1 * (wd1r + w.getFloat(idx4));
         float wk1i = csc1 * (wd1i + w.getFloat(idx4 + 1L));
         float wk3r = csc3 * (wd3r + w.getFloat(idx4 + 2L));
         float wk3i = csc3 * (wd3i + w.getFloat(idx4 + 3L));
         wd1r = w.getFloat(idx4);
         wd1i = w.getFloat(idx4 + 1L);
         wd3r = w.getFloat(idx4 + 2L);
         wd3i = w.getFloat(idx4 + 3L);
         long j1 = (long)j + m;
         j2 = j1 + m;
         j3 = j2 + m;
         idx1 = offa + j1;
         idx2 = offa + j2;
         idx3 = offa + j3;
         long idx5 = offa + (long)j;
         x0r = a.getFloat(idx5) + a.getFloat(idx2);
         x0i = a.getFloat(idx5 + 1L) + a.getFloat(idx2 + 1L);
         x1r = a.getFloat(idx5) - a.getFloat(idx2);
         x1i = a.getFloat(idx5 + 1L) - a.getFloat(idx2 + 1L);
         float y0r = a.getFloat(idx5 + 2L) + a.getFloat(idx2 + 2L);
         float y0i = a.getFloat(idx5 + 3L) + a.getFloat(idx2 + 3L);
         float y1r = a.getFloat(idx5 + 2L) - a.getFloat(idx2 + 2L);
         float y1i = a.getFloat(idx5 + 3L) - a.getFloat(idx2 + 3L);
         x2r = a.getFloat(idx1) + a.getFloat(idx3);
         x2i = a.getFloat(idx1 + 1L) + a.getFloat(idx3 + 1L);
         x3r = a.getFloat(idx1) - a.getFloat(idx3);
         x3i = a.getFloat(idx1 + 1L) - a.getFloat(idx3 + 1L);
         float y2r = a.getFloat(idx1 + 2L) + a.getFloat(idx3 + 2L);
         float y2i = a.getFloat(idx1 + 3L) + a.getFloat(idx3 + 3L);
         float y3r = a.getFloat(idx1 + 2L) - a.getFloat(idx3 + 2L);
         float y3i = a.getFloat(idx1 + 3L) - a.getFloat(idx3 + 3L);
         a.setFloat(idx5, x0r + x2r);
         a.setFloat(idx5 + 1L, x0i + x2i);
         a.setFloat(idx5 + 2L, y0r + y2r);
         a.setFloat(idx5 + 3L, y0i + y2i);
         a.setFloat(idx1, x0r - x2r);
         a.setFloat(idx1 + 1L, x0i - x2i);
         a.setFloat(idx1 + 2L, y0r - y2r);
         a.setFloat(idx1 + 3L, y0i - y2i);
         x0r = x1r - x3i;
         x0i = x1i + x3r;
         a.setFloat(idx2, wk1r * x0r - wk1i * x0i);
         a.setFloat(idx2 + 1L, wk1r * x0i + wk1i * x0r);
         x0r = y1r - y3i;
         x0i = y1i + y3r;
         a.setFloat(idx2 + 2L, wd1r * x0r - wd1i * x0i);
         a.setFloat(idx2 + 3L, wd1r * x0i + wd1i * x0r);
         x0r = x1r + x3i;
         x0i = x1i - x3r;
         a.setFloat(idx3, wk3r * x0r + wk3i * x0i);
         a.setFloat(idx3 + 1L, wk3r * x0i - wk3i * x0r);
         x0r = y1r + y3i;
         x0i = y1i - y3r;
         a.setFloat(idx3 + 2L, wd3r * x0r + wd3i * x0i);
         a.setFloat(idx3 + 3L, wd3r * x0i - wd3i * x0r);
         long j0 = m - (long)j;
         j1 = j0 + m;
         j2 = j1 + m;
         j3 = j2 + m;
         long idx0 = offa + j0;
         idx1 = offa + j1;
         idx2 = offa + j2;
         idx3 = offa + j3;
         x0r = a.getFloat(idx0) + a.getFloat(idx2);
         x0i = a.getFloat(idx0 + 1L) + a.getFloat(idx2 + 1L);
         x1r = a.getFloat(idx0) - a.getFloat(idx2);
         x1i = a.getFloat(idx0 + 1L) - a.getFloat(idx2 + 1L);
         y0r = a.getFloat(idx0 - 2L) + a.getFloat(idx2 - 2L);
         y0i = a.getFloat(idx0 - 1L) + a.getFloat(idx2 - 1L);
         y1r = a.getFloat(idx0 - 2L) - a.getFloat(idx2 - 2L);
         y1i = a.getFloat(idx0 - 1L) - a.getFloat(idx2 - 1L);
         x2r = a.getFloat(idx1) + a.getFloat(idx3);
         x2i = a.getFloat(idx1 + 1L) + a.getFloat(idx3 + 1L);
         x3r = a.getFloat(idx1) - a.getFloat(idx3);
         x3i = a.getFloat(idx1 + 1L) - a.getFloat(idx3 + 1L);
         y2r = a.getFloat(idx1 - 2L) + a.getFloat(idx3 - 2L);
         y2i = a.getFloat(idx1 - 1L) + a.getFloat(idx3 - 1L);
         y3r = a.getFloat(idx1 - 2L) - a.getFloat(idx3 - 2L);
         y3i = a.getFloat(idx1 - 1L) - a.getFloat(idx3 - 1L);
         a.setFloat(idx0, x0r + x2r);
         a.setFloat(idx0 + 1L, x0i + x2i);
         a.setFloat(idx0 - 2L, y0r + y2r);
         a.setFloat(idx0 - 1L, y0i + y2i);
         a.setFloat(idx1, x0r - x2r);
         a.setFloat(idx1 + 1L, x0i - x2i);
         a.setFloat(idx1 - 2L, y0r - y2r);
         a.setFloat(idx1 - 1L, y0i - y2i);
         x0r = x1r - x3i;
         x0i = x1i + x3r;
         a.setFloat(idx2, wk1i * x0r - wk1r * x0i);
         a.setFloat(idx2 + 1L, wk1i * x0i + wk1r * x0r);
         x0r = y1r - y3i;
         x0i = y1i + y3r;
         a.setFloat(idx2 - 2L, wd1i * x0r - wd1r * x0i);
         a.setFloat(idx2 - 1L, wd1i * x0i + wd1r * x0r);
         x0r = x1r + x3i;
         x0i = x1i - x3r;
         a.setFloat(idx3, wk3i * x0r + wk3r * x0i);
         a.setFloat(idx3 + 1L, wk3i * x0i - wk3r * x0r);
         x0r = y1r + y3i;
         x0i = y1i - y3r;
         a.setFloat(offa + j3 - 2L, wd3i * x0r + wd3r * x0i);
         a.setFloat(offa + j3 - 1L, wd3i * x0i - wd3r * x0r);
      }

      float wk1r = csc1 * (wd1r + wn4r);
      float wk1i = csc1 * (wd1i + wn4r);
      float wk3r = csc3 * (wd3r - wn4r);
      float wk3i = csc3 * (wd3i - wn4r);
      long var63 = mh + m;
      j2 = var63 + m;
      j3 = j2 + m;
      long idx0 = offa + mh;
      idx1 = offa + var63;
      idx2 = offa + j2;
      idx3 = offa + j3;
      x0r = a.getFloat(idx0 - 2L) + a.getFloat(idx2 - 2L);
      x0i = a.getFloat(idx0 - 1L) + a.getFloat(idx2 - 1L);
      x1r = a.getFloat(idx0 - 2L) - a.getFloat(idx2 - 2L);
      x1i = a.getFloat(idx0 - 1L) - a.getFloat(idx2 - 1L);
      x2r = a.getFloat(idx1 - 2L) + a.getFloat(idx3 - 2L);
      x2i = a.getFloat(idx1 - 1L) + a.getFloat(idx3 - 1L);
      x3r = a.getFloat(idx1 - 2L) - a.getFloat(idx3 - 2L);
      x3i = a.getFloat(idx1 - 1L) - a.getFloat(idx3 - 1L);
      a.setFloat(idx0 - 2L, x0r + x2r);
      a.setFloat(idx0 - 1L, x0i + x2i);
      a.setFloat(idx1 - 2L, x0r - x2r);
      a.setFloat(idx1 - 1L, x0i - x2i);
      x0r = x1r - x3i;
      x0i = x1i + x3r;
      a.setFloat(idx2 - 2L, wk1r * x0r - wk1i * x0i);
      a.setFloat(idx2 - 1L, wk1r * x0i + wk1i * x0r);
      x0r = x1r + x3i;
      x0i = x1i - x3r;
      a.setFloat(idx3 - 2L, wk3r * x0r + wk3i * x0i);
      a.setFloat(idx3 - 1L, wk3r * x0i - wk3i * x0r);
      x0r = a.getFloat(idx0) + a.getFloat(idx2);
      x0i = a.getFloat(idx0 + 1L) + a.getFloat(idx2 + 1L);
      x1r = a.getFloat(idx0) - a.getFloat(idx2);
      x1i = a.getFloat(idx0 + 1L) - a.getFloat(idx2 + 1L);
      x2r = a.getFloat(idx1) + a.getFloat(idx3);
      x2i = a.getFloat(idx1 + 1L) + a.getFloat(idx3 + 1L);
      x3r = a.getFloat(idx1) - a.getFloat(idx3);
      x3i = a.getFloat(idx1 + 1L) - a.getFloat(idx3 + 1L);
      a.setFloat(idx0, x0r + x2r);
      a.setFloat(idx0 + 1L, x0i + x2i);
      a.setFloat(idx1, x0r - x2r);
      a.setFloat(idx1 + 1L, x0i - x2i);
      x0r = x1r - x3i;
      x0i = x1i + x3r;
      a.setFloat(idx2, wn4r * (x0r - x0i));
      a.setFloat(idx2 + 1L, wn4r * (x0i + x0r));
      x0r = x1r + x3i;
      x0i = x1i - x3r;
      a.setFloat(idx3, -wn4r * (x0r + x0i));
      a.setFloat(idx3 + 1L, -wn4r * (x0i - x0r));
      x0r = a.getFloat(idx0 + 2L) + a.getFloat(idx2 + 2L);
      x0i = a.getFloat(idx0 + 3L) + a.getFloat(idx2 + 3L);
      x1r = a.getFloat(idx0 + 2L) - a.getFloat(idx2 + 2L);
      x1i = a.getFloat(idx0 + 3L) - a.getFloat(idx2 + 3L);
      x2r = a.getFloat(idx1 + 2L) + a.getFloat(idx3 + 2L);
      x2i = a.getFloat(idx1 + 3L) + a.getFloat(idx3 + 3L);
      x3r = a.getFloat(idx1 + 2L) - a.getFloat(idx3 + 2L);
      x3i = a.getFloat(idx1 + 3L) - a.getFloat(idx3 + 3L);
      a.setFloat(idx0 + 2L, x0r + x2r);
      a.setFloat(idx0 + 3L, x0i + x2i);
      a.setFloat(idx1 + 2L, x0r - x2r);
      a.setFloat(idx1 + 3L, x0i - x2i);
      x0r = x1r - x3i;
      x0i = x1i + x3r;
      a.setFloat(idx2 + 2L, wk1i * x0r - wk1r * x0i);
      a.setFloat(idx2 + 3L, wk1i * x0i + wk1r * x0r);
      x0r = x1r + x3i;
      x0i = x1i - x3r;
      a.setFloat(idx3 + 2L, wk3i * x0r + wk3r * x0i);
      a.setFloat(idx3 + 3L, wk3i * x0i - wk3r * x0r);
   }

   public static void cftb1st(int n, float[] a, int offa, float[] w, int startw) {
      int mh = n >> 3;
      int m = 2 * mh;
      int j2 = m + m;
      int j3 = j2 + m;
      int idx1 = offa + m;
      int idx2 = offa + j2;
      int idx3 = offa + j3;
      float x0r = a[offa] + a[idx2];
      float x0i = -a[offa + 1] - a[idx2 + 1];
      float x1r = a[offa] - a[idx2];
      float x1i = -a[offa + 1] + a[idx2 + 1];
      float x2r = a[idx1] + a[idx3];
      float x2i = a[idx1 + 1] + a[idx3 + 1];
      float x3r = a[idx1] - a[idx3];
      float x3i = a[idx1 + 1] - a[idx3 + 1];
      a[offa] = x0r + x2r;
      a[offa + 1] = x0i - x2i;
      a[idx1] = x0r - x2r;
      a[idx1 + 1] = x0i + x2i;
      a[idx2] = x1r + x3i;
      a[idx2 + 1] = x1i + x3r;
      a[idx3] = x1r - x3i;
      a[idx3 + 1] = x1i - x3r;
      float wn4r = w[startw + 1];
      float csc1 = w[startw + 2];
      float csc3 = w[startw + 3];
      float wd1r = 1.0F;
      float wd1i = 0.0F;
      float wd3r = 1.0F;
      float wd3i = 0.0F;
      int k = 0;

      for(int j = 2; j < mh - 2; j += 4) {
         k += 4;
         int idx4 = startw + k;
         float wk1r = csc1 * (wd1r + w[idx4]);
         float wk1i = csc1 * (wd1i + w[idx4 + 1]);
         float wk3r = csc3 * (wd3r + w[idx4 + 2]);
         float wk3i = csc3 * (wd3i + w[idx4 + 3]);
         wd1r = w[idx4];
         wd1i = w[idx4 + 1];
         wd3r = w[idx4 + 2];
         wd3i = w[idx4 + 3];
         int j1 = j + m;
         j2 = j1 + m;
         j3 = j2 + m;
         idx1 = offa + j1;
         idx2 = offa + j2;
         idx3 = offa + j3;
         int idx5 = offa + j;
         x0r = a[idx5] + a[idx2];
         x0i = -a[idx5 + 1] - a[idx2 + 1];
         x1r = a[idx5] - a[offa + j2];
         x1i = -a[idx5 + 1] + a[idx2 + 1];
         float y0r = a[idx5 + 2] + a[idx2 + 2];
         float y0i = -a[idx5 + 3] - a[idx2 + 3];
         float y1r = a[idx5 + 2] - a[idx2 + 2];
         float y1i = -a[idx5 + 3] + a[idx2 + 3];
         x2r = a[idx1] + a[idx3];
         x2i = a[idx1 + 1] + a[idx3 + 1];
         x3r = a[idx1] - a[idx3];
         x3i = a[idx1 + 1] - a[idx3 + 1];
         float y2r = a[idx1 + 2] + a[idx3 + 2];
         float y2i = a[idx1 + 3] + a[idx3 + 3];
         float y3r = a[idx1 + 2] - a[idx3 + 2];
         float y3i = a[idx1 + 3] - a[idx3 + 3];
         a[idx5] = x0r + x2r;
         a[idx5 + 1] = x0i - x2i;
         a[idx5 + 2] = y0r + y2r;
         a[idx5 + 3] = y0i - y2i;
         a[idx1] = x0r - x2r;
         a[idx1 + 1] = x0i + x2i;
         a[idx1 + 2] = y0r - y2r;
         a[idx1 + 3] = y0i + y2i;
         x0r = x1r + x3i;
         x0i = x1i + x3r;
         a[idx2] = wk1r * x0r - wk1i * x0i;
         a[idx2 + 1] = wk1r * x0i + wk1i * x0r;
         x0r = y1r + y3i;
         x0i = y1i + y3r;
         a[idx2 + 2] = wd1r * x0r - wd1i * x0i;
         a[idx2 + 3] = wd1r * x0i + wd1i * x0r;
         x0r = x1r - x3i;
         x0i = x1i - x3r;
         a[idx3] = wk3r * x0r + wk3i * x0i;
         a[idx3 + 1] = wk3r * x0i - wk3i * x0r;
         x0r = y1r - y3i;
         x0i = y1i - y3r;
         a[idx3 + 2] = wd3r * x0r + wd3i * x0i;
         a[idx3 + 3] = wd3r * x0i - wd3i * x0r;
         int j0 = m - j;
         j1 = j0 + m;
         j2 = j1 + m;
         j3 = j2 + m;
         int idx0 = offa + j0;
         idx1 = offa + j1;
         idx2 = offa + j2;
         idx3 = offa + j3;
         x0r = a[idx0] + a[idx2];
         x0i = -a[idx0 + 1] - a[idx2 + 1];
         x1r = a[idx0] - a[idx2];
         x1i = -a[idx0 + 1] + a[idx2 + 1];
         y0r = a[idx0 - 2] + a[idx2 - 2];
         y0i = -a[idx0 - 1] - a[idx2 - 1];
         y1r = a[idx0 - 2] - a[idx2 - 2];
         y1i = -a[idx0 - 1] + a[idx2 - 1];
         x2r = a[idx1] + a[idx3];
         x2i = a[idx1 + 1] + a[idx3 + 1];
         x3r = a[idx1] - a[idx3];
         x3i = a[idx1 + 1] - a[idx3 + 1];
         y2r = a[idx1 - 2] + a[idx3 - 2];
         y2i = a[idx1 - 1] + a[idx3 - 1];
         y3r = a[idx1 - 2] - a[idx3 - 2];
         y3i = a[idx1 - 1] - a[idx3 - 1];
         a[idx0] = x0r + x2r;
         a[idx0 + 1] = x0i - x2i;
         a[idx0 - 2] = y0r + y2r;
         a[idx0 - 1] = y0i - y2i;
         a[idx1] = x0r - x2r;
         a[idx1 + 1] = x0i + x2i;
         a[idx1 - 2] = y0r - y2r;
         a[idx1 - 1] = y0i + y2i;
         x0r = x1r + x3i;
         x0i = x1i + x3r;
         a[idx2] = wk1i * x0r - wk1r * x0i;
         a[idx2 + 1] = wk1i * x0i + wk1r * x0r;
         x0r = y1r + y3i;
         x0i = y1i + y3r;
         a[idx2 - 2] = wd1i * x0r - wd1r * x0i;
         a[idx2 - 1] = wd1i * x0i + wd1r * x0r;
         x0r = x1r - x3i;
         x0i = x1i - x3r;
         a[idx3] = wk3i * x0r + wk3r * x0i;
         a[idx3 + 1] = wk3i * x0i - wk3r * x0r;
         x0r = y1r - y3i;
         x0i = y1i - y3r;
         a[idx3 - 2] = wd3i * x0r + wd3r * x0i;
         a[idx3 - 1] = wd3i * x0i - wd3r * x0r;
      }

      float wk1r = csc1 * (wd1r + wn4r);
      float wk1i = csc1 * (wd1i + wn4r);
      float wk3r = csc3 * (wd3r - wn4r);
      float wk3i = csc3 * (wd3i - wn4r);
      int var47 = mh + m;
      j2 = var47 + m;
      j3 = j2 + m;
      int idx0 = offa + mh;
      idx1 = offa + var47;
      idx2 = offa + j2;
      idx3 = offa + j3;
      x0r = a[idx0 - 2] + a[idx2 - 2];
      x0i = -a[idx0 - 1] - a[idx2 - 1];
      x1r = a[idx0 - 2] - a[idx2 - 2];
      x1i = -a[idx0 - 1] + a[idx2 - 1];
      x2r = a[idx1 - 2] + a[idx3 - 2];
      x2i = a[idx1 - 1] + a[idx3 - 1];
      x3r = a[idx1 - 2] - a[idx3 - 2];
      x3i = a[idx1 - 1] - a[idx3 - 1];
      a[idx0 - 2] = x0r + x2r;
      a[idx0 - 1] = x0i - x2i;
      a[idx1 - 2] = x0r - x2r;
      a[idx1 - 1] = x0i + x2i;
      x0r = x1r + x3i;
      x0i = x1i + x3r;
      a[idx2 - 2] = wk1r * x0r - wk1i * x0i;
      a[idx2 - 1] = wk1r * x0i + wk1i * x0r;
      x0r = x1r - x3i;
      x0i = x1i - x3r;
      a[idx3 - 2] = wk3r * x0r + wk3i * x0i;
      a[idx3 - 1] = wk3r * x0i - wk3i * x0r;
      x0r = a[idx0] + a[idx2];
      x0i = -a[idx0 + 1] - a[idx2 + 1];
      x1r = a[idx0] - a[idx2];
      x1i = -a[idx0 + 1] + a[idx2 + 1];
      x2r = a[idx1] + a[idx3];
      x2i = a[idx1 + 1] + a[idx3 + 1];
      x3r = a[idx1] - a[idx3];
      x3i = a[idx1 + 1] - a[idx3 + 1];
      a[idx0] = x0r + x2r;
      a[idx0 + 1] = x0i - x2i;
      a[idx1] = x0r - x2r;
      a[idx1 + 1] = x0i + x2i;
      x0r = x1r + x3i;
      x0i = x1i + x3r;
      a[idx2] = wn4r * (x0r - x0i);
      a[idx2 + 1] = wn4r * (x0i + x0r);
      x0r = x1r - x3i;
      x0i = x1i - x3r;
      a[idx3] = -wn4r * (x0r + x0i);
      a[idx3 + 1] = -wn4r * (x0i - x0r);
      x0r = a[idx0 + 2] + a[idx2 + 2];
      x0i = -a[idx0 + 3] - a[idx2 + 3];
      x1r = a[idx0 + 2] - a[idx2 + 2];
      x1i = -a[idx0 + 3] + a[idx2 + 3];
      x2r = a[idx1 + 2] + a[idx3 + 2];
      x2i = a[idx1 + 3] + a[idx3 + 3];
      x3r = a[idx1 + 2] - a[idx3 + 2];
      x3i = a[idx1 + 3] - a[idx3 + 3];
      a[idx0 + 2] = x0r + x2r;
      a[idx0 + 3] = x0i - x2i;
      a[idx1 + 2] = x0r - x2r;
      a[idx1 + 3] = x0i + x2i;
      x0r = x1r + x3i;
      x0i = x1i + x3r;
      a[idx2 + 2] = wk1i * x0r - wk1r * x0i;
      a[idx2 + 3] = wk1i * x0i + wk1r * x0r;
      x0r = x1r - x3i;
      x0i = x1i - x3r;
      a[idx3 + 2] = wk3i * x0r + wk3r * x0i;
      a[idx3 + 3] = wk3i * x0i - wk3r * x0r;
   }

   public static void cftb1st(long n, FloatLargeArray a, long offa, FloatLargeArray w, long startw) {
      long mh = n >> 3;
      long m = 2L * mh;
      long j2 = m + m;
      long j3 = j2 + m;
      long idx1 = offa + m;
      long idx2 = offa + j2;
      long idx3 = offa + j3;
      float x0r = a.getFloat(offa) + a.getFloat(idx2);
      float x0i = -a.getFloat(offa + 1L) - a.getFloat(idx2 + 1L);
      float x1r = a.getFloat(offa) - a.getFloat(idx2);
      float x1i = -a.getFloat(offa + 1L) + a.getFloat(idx2 + 1L);
      float x2r = a.getFloat(idx1) + a.getFloat(idx3);
      float x2i = a.getFloat(idx1 + 1L) + a.getFloat(idx3 + 1L);
      float x3r = a.getFloat(idx1) - a.getFloat(idx3);
      float x3i = a.getFloat(idx1 + 1L) - a.getFloat(idx3 + 1L);
      a.setFloat(offa, x0r + x2r);
      a.setFloat(offa + 1L, x0i - x2i);
      a.setFloat(idx1, x0r - x2r);
      a.setFloat(idx1 + 1L, x0i + x2i);
      a.setFloat(idx2, x1r + x3i);
      a.setFloat(idx2 + 1L, x1i + x3r);
      a.setFloat(idx3, x1r - x3i);
      a.setFloat(idx3 + 1L, x1i - x3r);
      float wn4r = w.getFloat(startw + 1L);
      float csc1 = w.getFloat(startw + 2L);
      float csc3 = w.getFloat(startw + 3L);
      float wd1r = 1.0F;
      float wd1i = 0.0F;
      float wd3r = 1.0F;
      float wd3i = 0.0F;
      long k = 0L;

      for(long j = 2L; j < mh - 2L; j += 4L) {
         k += 4L;
         long idx4 = startw + k;
         float wk1r = csc1 * (wd1r + w.getFloat(idx4));
         float wk1i = csc1 * (wd1i + w.getFloat(idx4 + 1L));
         float wk3r = csc3 * (wd3r + w.getFloat(idx4 + 2L));
         float wk3i = csc3 * (wd3i + w.getFloat(idx4 + 3L));
         wd1r = w.getFloat(idx4);
         wd1i = w.getFloat(idx4 + 1L);
         wd3r = w.getFloat(idx4 + 2L);
         wd3i = w.getFloat(idx4 + 3L);
         long j1 = j + m;
         j2 = j1 + m;
         j3 = j2 + m;
         idx1 = offa + j1;
         idx2 = offa + j2;
         idx3 = offa + j3;
         long idx5 = offa + j;
         x0r = a.getFloat(idx5) + a.getFloat(idx2);
         x0i = -a.getFloat(idx5 + 1L) - a.getFloat(idx2 + 1L);
         x1r = a.getFloat(idx5) - a.getFloat(offa + j2);
         x1i = -a.getFloat(idx5 + 1L) + a.getFloat(idx2 + 1L);
         float y0r = a.getFloat(idx5 + 2L) + a.getFloat(idx2 + 2L);
         float y0i = -a.getFloat(idx5 + 3L) - a.getFloat(idx2 + 3L);
         float y1r = a.getFloat(idx5 + 2L) - a.getFloat(idx2 + 2L);
         float y1i = -a.getFloat(idx5 + 3L) + a.getFloat(idx2 + 3L);
         x2r = a.getFloat(idx1) + a.getFloat(idx3);
         x2i = a.getFloat(idx1 + 1L) + a.getFloat(idx3 + 1L);
         x3r = a.getFloat(idx1) - a.getFloat(idx3);
         x3i = a.getFloat(idx1 + 1L) - a.getFloat(idx3 + 1L);
         float y2r = a.getFloat(idx1 + 2L) + a.getFloat(idx3 + 2L);
         float y2i = a.getFloat(idx1 + 3L) + a.getFloat(idx3 + 3L);
         float y3r = a.getFloat(idx1 + 2L) - a.getFloat(idx3 + 2L);
         float y3i = a.getFloat(idx1 + 3L) - a.getFloat(idx3 + 3L);
         a.setFloat(idx5, x0r + x2r);
         a.setFloat(idx5 + 1L, x0i - x2i);
         a.setFloat(idx5 + 2L, y0r + y2r);
         a.setFloat(idx5 + 3L, y0i - y2i);
         a.setFloat(idx1, x0r - x2r);
         a.setFloat(idx1 + 1L, x0i + x2i);
         a.setFloat(idx1 + 2L, y0r - y2r);
         a.setFloat(idx1 + 3L, y0i + y2i);
         x0r = x1r + x3i;
         x0i = x1i + x3r;
         a.setFloat(idx2, wk1r * x0r - wk1i * x0i);
         a.setFloat(idx2 + 1L, wk1r * x0i + wk1i * x0r);
         x0r = y1r + y3i;
         x0i = y1i + y3r;
         a.setFloat(idx2 + 2L, wd1r * x0r - wd1i * x0i);
         a.setFloat(idx2 + 3L, wd1r * x0i + wd1i * x0r);
         x0r = x1r - x3i;
         x0i = x1i - x3r;
         a.setFloat(idx3, wk3r * x0r + wk3i * x0i);
         a.setFloat(idx3 + 1L, wk3r * x0i - wk3i * x0r);
         x0r = y1r - y3i;
         x0i = y1i - y3r;
         a.setFloat(idx3 + 2L, wd3r * x0r + wd3i * x0i);
         a.setFloat(idx3 + 3L, wd3r * x0i - wd3i * x0r);
         long j0 = m - j;
         j1 = j0 + m;
         j2 = j1 + m;
         j3 = j2 + m;
         long idx0 = offa + j0;
         idx1 = offa + j1;
         idx2 = offa + j2;
         idx3 = offa + j3;
         x0r = a.getFloat(idx0) + a.getFloat(idx2);
         x0i = -a.getFloat(idx0 + 1L) - a.getFloat(idx2 + 1L);
         x1r = a.getFloat(idx0) - a.getFloat(idx2);
         x1i = -a.getFloat(idx0 + 1L) + a.getFloat(idx2 + 1L);
         y0r = a.getFloat(idx0 - 2L) + a.getFloat(idx2 - 2L);
         y0i = -a.getFloat(idx0 - 1L) - a.getFloat(idx2 - 1L);
         y1r = a.getFloat(idx0 - 2L) - a.getFloat(idx2 - 2L);
         y1i = -a.getFloat(idx0 - 1L) + a.getFloat(idx2 - 1L);
         x2r = a.getFloat(idx1) + a.getFloat(idx3);
         x2i = a.getFloat(idx1 + 1L) + a.getFloat(idx3 + 1L);
         x3r = a.getFloat(idx1) - a.getFloat(idx3);
         x3i = a.getFloat(idx1 + 1L) - a.getFloat(idx3 + 1L);
         y2r = a.getFloat(idx1 - 2L) + a.getFloat(idx3 - 2L);
         y2i = a.getFloat(idx1 - 1L) + a.getFloat(idx3 - 1L);
         y3r = a.getFloat(idx1 - 2L) - a.getFloat(idx3 - 2L);
         y3i = a.getFloat(idx1 - 1L) - a.getFloat(idx3 - 1L);
         a.setFloat(idx0, x0r + x2r);
         a.setFloat(idx0 + 1L, x0i - x2i);
         a.setFloat(idx0 - 2L, y0r + y2r);
         a.setFloat(idx0 - 1L, y0i - y2i);
         a.setFloat(idx1, x0r - x2r);
         a.setFloat(idx1 + 1L, x0i + x2i);
         a.setFloat(idx1 - 2L, y0r - y2r);
         a.setFloat(idx1 - 1L, y0i + y2i);
         x0r = x1r + x3i;
         x0i = x1i + x3r;
         a.setFloat(idx2, wk1i * x0r - wk1r * x0i);
         a.setFloat(idx2 + 1L, wk1i * x0i + wk1r * x0r);
         x0r = y1r + y3i;
         x0i = y1i + y3r;
         a.setFloat(idx2 - 2L, wd1i * x0r - wd1r * x0i);
         a.setFloat(idx2 - 1L, wd1i * x0i + wd1r * x0r);
         x0r = x1r - x3i;
         x0i = x1i - x3r;
         a.setFloat(idx3, wk3i * x0r + wk3r * x0i);
         a.setFloat(idx3 + 1L, wk3i * x0i - wk3r * x0r);
         x0r = y1r - y3i;
         x0i = y1i - y3r;
         a.setFloat(idx3 - 2L, wd3i * x0r + wd3r * x0i);
         a.setFloat(idx3 - 1L, wd3i * x0i - wd3r * x0r);
      }

      float wk1r = csc1 * (wd1r + wn4r);
      float wk1i = csc1 * (wd1i + wn4r);
      float wk3r = csc3 * (wd3r - wn4r);
      float wk3i = csc3 * (wd3i - wn4r);
      long var64 = mh + m;
      j2 = var64 + m;
      j3 = j2 + m;
      long idx0 = offa + mh;
      idx1 = offa + var64;
      idx2 = offa + j2;
      idx3 = offa + j3;
      x0r = a.getFloat(idx0 - 2L) + a.getFloat(idx2 - 2L);
      x0i = -a.getFloat(idx0 - 1L) - a.getFloat(idx2 - 1L);
      x1r = a.getFloat(idx0 - 2L) - a.getFloat(idx2 - 2L);
      x1i = -a.getFloat(idx0 - 1L) + a.getFloat(idx2 - 1L);
      x2r = a.getFloat(idx1 - 2L) + a.getFloat(idx3 - 2L);
      x2i = a.getFloat(idx1 - 1L) + a.getFloat(idx3 - 1L);
      x3r = a.getFloat(idx1 - 2L) - a.getFloat(idx3 - 2L);
      x3i = a.getFloat(idx1 - 1L) - a.getFloat(idx3 - 1L);
      a.setFloat(idx0 - 2L, x0r + x2r);
      a.setFloat(idx0 - 1L, x0i - x2i);
      a.setFloat(idx1 - 2L, x0r - x2r);
      a.setFloat(idx1 - 1L, x0i + x2i);
      x0r = x1r + x3i;
      x0i = x1i + x3r;
      a.setFloat(idx2 - 2L, wk1r * x0r - wk1i * x0i);
      a.setFloat(idx2 - 1L, wk1r * x0i + wk1i * x0r);
      x0r = x1r - x3i;
      x0i = x1i - x3r;
      a.setFloat(idx3 - 2L, wk3r * x0r + wk3i * x0i);
      a.setFloat(idx3 - 1L, wk3r * x0i - wk3i * x0r);
      x0r = a.getFloat(idx0) + a.getFloat(idx2);
      x0i = -a.getFloat(idx0 + 1L) - a.getFloat(idx2 + 1L);
      x1r = a.getFloat(idx0) - a.getFloat(idx2);
      x1i = -a.getFloat(idx0 + 1L) + a.getFloat(idx2 + 1L);
      x2r = a.getFloat(idx1) + a.getFloat(idx3);
      x2i = a.getFloat(idx1 + 1L) + a.getFloat(idx3 + 1L);
      x3r = a.getFloat(idx1) - a.getFloat(idx3);
      x3i = a.getFloat(idx1 + 1L) - a.getFloat(idx3 + 1L);
      a.setFloat(idx0, x0r + x2r);
      a.setFloat(idx0 + 1L, x0i - x2i);
      a.setFloat(idx1, x0r - x2r);
      a.setFloat(idx1 + 1L, x0i + x2i);
      x0r = x1r + x3i;
      x0i = x1i + x3r;
      a.setFloat(idx2, wn4r * (x0r - x0i));
      a.setFloat(idx2 + 1L, wn4r * (x0i + x0r));
      x0r = x1r - x3i;
      x0i = x1i - x3r;
      a.setFloat(idx3, -wn4r * (x0r + x0i));
      a.setFloat(idx3 + 1L, -wn4r * (x0i - x0r));
      x0r = a.getFloat(idx0 + 2L) + a.getFloat(idx2 + 2L);
      x0i = -a.getFloat(idx0 + 3L) - a.getFloat(idx2 + 3L);
      x1r = a.getFloat(idx0 + 2L) - a.getFloat(idx2 + 2L);
      x1i = -a.getFloat(idx0 + 3L) + a.getFloat(idx2 + 3L);
      x2r = a.getFloat(idx1 + 2L) + a.getFloat(idx3 + 2L);
      x2i = a.getFloat(idx1 + 3L) + a.getFloat(idx3 + 3L);
      x3r = a.getFloat(idx1 + 2L) - a.getFloat(idx3 + 2L);
      x3i = a.getFloat(idx1 + 3L) - a.getFloat(idx3 + 3L);
      a.setFloat(idx0 + 2L, x0r + x2r);
      a.setFloat(idx0 + 3L, x0i - x2i);
      a.setFloat(idx1 + 2L, x0r - x2r);
      a.setFloat(idx1 + 3L, x0i + x2i);
      x0r = x1r + x3i;
      x0i = x1i + x3r;
      a.setFloat(idx2 + 2L, wk1i * x0r - wk1r * x0i);
      a.setFloat(idx2 + 3L, wk1i * x0i + wk1r * x0r);
      x0r = x1r - x3i;
      x0i = x1i - x3r;
      a.setFloat(idx3 + 2L, wk3i * x0r + wk3r * x0i);
      a.setFloat(idx3 + 3L, wk3i * x0i - wk3r * x0r);
   }

   public static void cftrec4_th(final int n, final float[] a, int offa, final int nw, final float[] w) {
      int idx = 0;
      int nthreads = 2;
      int idiv4 = 0;
      int m = n >> 1;
      if ((long)n >= getThreadsBeginN_1D_FFT_4Threads()) {
         nthreads = 4;
         idiv4 = 1;
         m >>= 1;
      }

      Future<?>[] futures = new Future[nthreads];
      final int mf = m;

      for(int i = 0; i < nthreads; ++i) {
         final int firstIdx = offa + i * m;
         if (i != idiv4) {
            futures[idx++] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  int idx1 = firstIdx + mf;
                  int m = n;

                  while(m > 512) {
                     m >>= 2;
                     CommonUtils.cftmdl1(m, a, idx1 - m, w, nw - (m >> 1));
                  }

                  CommonUtils.cftleaf(m, 1, (float[])a, idx1 - m, nw, (float[])w);
                  int k = 0;
                  int idx2 = firstIdx - m;

                  for(int j = mf - m; j > 0; j -= m) {
                     ++k;
                     int isplt = CommonUtils.cfttree(m, j, k, a, firstIdx, nw, w);
                     CommonUtils.cftleaf(m, isplt, a, idx2 + j, nw, w);
                  }

               }
            });
         } else {
            futures[idx++] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  int idx1 = firstIdx + mf;
                  int k = 1;
                  int m = n;

                  while(m > 512) {
                     m >>= 2;
                     k <<= 2;
                     CommonUtils.cftmdl2(m, a, idx1 - m, w, nw - m);
                  }

                  CommonUtils.cftleaf(m, 0, (float[])a, idx1 - m, nw, (float[])w);
                  k >>= 1;
                  int idx2 = firstIdx - m;

                  for(int j = mf - m; j > 0; j -= m) {
                     ++k;
                     int isplt = CommonUtils.cfttree(m, j, k, a, firstIdx, nw, w);
                     CommonUtils.cftleaf(m, isplt, a, idx2 + j, nw, w);
                  }

               }
            });
         }
      }

      try {
         ConcurrencyUtils.waitForCompletion(futures);
      } catch (InterruptedException ex) {
         Logger.getLogger(CommonUtils.class.getName()).log(Level.SEVERE, (String)null, ex);
      } catch (ExecutionException ex) {
         Logger.getLogger(CommonUtils.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

   }

   public static void cftrec4_th(final long n, final FloatLargeArray a, long offa, final long nw, final FloatLargeArray w) {
      int idx = 0;
      int nthreads = 2;
      int idiv4 = 0;
      long m = n >> 1;
      if (n >= getThreadsBeginN_1D_FFT_4Threads()) {
         nthreads = 4;
         idiv4 = 1;
         m >>= 1;
      }

      Future<?>[] futures = new Future[nthreads];
      final long mf = m;

      for(int i = 0; i < nthreads; ++i) {
         final long firstIdx = offa + (long)i * m;
         if (i != idiv4) {
            futures[idx++] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  long idx1 = firstIdx + mf;
                  long m = n;

                  while(m > 512L) {
                     m >>= 2;
                     CommonUtils.cftmdl1(m, a, idx1 - m, w, nw - (m >> 1));
                  }

                  CommonUtils.cftleaf(m, 1L, a, idx1 - m, nw, w);
                  long k = 0L;
                  long idx2 = firstIdx - m;

                  for(long j = mf - m; j > 0L; j -= m) {
                     ++k;
                     long isplt = CommonUtils.cfttree(m, j, k, a, firstIdx, nw, w);
                     CommonUtils.cftleaf(m, isplt, a, idx2 + j, nw, w);
                  }

               }
            });
         } else {
            futures[idx++] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  // $FF: Couldn't be decompiled
               }
            });
         }
      }

      try {
         ConcurrencyUtils.waitForCompletion(futures);
      } catch (InterruptedException ex) {
         Logger.getLogger(CommonUtils.class.getName()).log(Level.SEVERE, (String)null, ex);
      } catch (ExecutionException ex) {
         Logger.getLogger(CommonUtils.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

   }

   public static void cftrec4(int n, float[] a, int offa, int nw, float[] w) {
      int m = n;
      int idx1 = offa + n;

      while(m > 512) {
         m >>= 2;
         cftmdl1(m, a, idx1 - m, w, nw - (m >> 1));
      }

      cftleaf(m, 1, (float[])a, idx1 - m, nw, (float[])w);
      int k = 0;
      int idx2 = offa - m;

      for(int j = n - m; j > 0; j -= m) {
         ++k;
         int isplt = cfttree(m, j, k, a, offa, nw, w);
         cftleaf(m, isplt, a, idx2 + j, nw, w);
      }

   }

   public static void cftrec4(long n, FloatLargeArray a, long offa, long nw, FloatLargeArray w) {
      long m = n;
      long idx1 = offa + n;

      while(m > 512L) {
         m >>= 2;
         cftmdl1(m, a, idx1 - m, w, nw - (m >> 1));
      }

      cftleaf(m, 1L, a, idx1 - m, nw, w);
      long k = 0L;
      long idx2 = offa - m;

      for(long j = n - m; j > 0L; j -= m) {
         ++k;
         long isplt = cfttree(m, j, k, a, offa, nw, w);
         cftleaf(m, isplt, a, idx2 + j, nw, w);
      }

   }

   public static int cfttree(int n, int j, int k, float[] a, int offa, int nw, float[] w) {
      int idx1 = offa - n;
      int isplt;
      if ((k & 3) != 0) {
         isplt = k & 1;
         if (isplt != 0) {
            cftmdl1(n, a, idx1 + j, w, nw - (n >> 1));
         } else {
            cftmdl2(n, a, idx1 + j, w, nw - n);
         }
      } else {
         int m = n;

         int i;
         for(i = k; (i & 3) == 0; i >>= 2) {
            m <<= 2;
         }

         isplt = i & 1;
         int idx2 = offa + j;
         if (isplt != 0) {
            while(m > 128) {
               cftmdl1(m, a, idx2 - m, w, nw - (m >> 1));
               m >>= 2;
            }
         } else {
            while(m > 128) {
               cftmdl2(m, a, idx2 - m, w, nw - m);
               m >>= 2;
            }
         }
      }

      return isplt;
   }

   public static long cfttree(long n, long j, long k, FloatLargeArray a, long offa, long nw, FloatLargeArray w) {
      long idx1 = offa - n;
      long isplt;
      if ((k & 3L) != 0L) {
         isplt = k & 1L;
         if (isplt != 0L) {
            cftmdl1(n, a, idx1 + j, w, nw - (n >> 1));
         } else {
            cftmdl2(n, a, idx1 + j, w, nw - n);
         }
      } else {
         long m = n;

         long i;
         for(i = k; (i & 3L) == 0L; i >>= 2) {
            m <<= 2;
         }

         isplt = i & 1L;
         long idx2 = offa + j;
         if (isplt != 0L) {
            while(m > 128L) {
               cftmdl1(m, a, idx2 - m, w, nw - (m >> 1));
               m >>= 2;
            }
         } else {
            while(m > 128L) {
               cftmdl2(m, a, idx2 - m, w, nw - m);
               m >>= 2;
            }
         }
      }

      return isplt;
   }

   public static void cftleaf(int n, int isplt, float[] a, int offa, int nw, float[] w) {
      if (n == 512) {
         cftmdl1(128, (float[])a, offa, (float[])w, nw - 64);
         cftf161(a, offa, w, nw - 8);
         cftf162(a, offa + 32, w, nw - 32);
         cftf161(a, offa + 64, w, nw - 8);
         cftf161(a, offa + 96, w, nw - 8);
         cftmdl2(128, (float[])a, offa + 128, (float[])w, nw - 128);
         cftf161(a, offa + 128, w, nw - 8);
         cftf162(a, offa + 160, w, nw - 32);
         cftf161(a, offa + 192, w, nw - 8);
         cftf162(a, offa + 224, w, nw - 32);
         cftmdl1(128, (float[])a, offa + 256, (float[])w, nw - 64);
         cftf161(a, offa + 256, w, nw - 8);
         cftf162(a, offa + 288, w, nw - 32);
         cftf161(a, offa + 320, w, nw - 8);
         cftf161(a, offa + 352, w, nw - 8);
         if (isplt != 0) {
            cftmdl1(128, (float[])a, offa + 384, (float[])w, nw - 64);
            cftf161(a, offa + 480, w, nw - 8);
         } else {
            cftmdl2(128, (float[])a, offa + 384, (float[])w, nw - 128);
            cftf162(a, offa + 480, w, nw - 32);
         }

         cftf161(a, offa + 384, w, nw - 8);
         cftf162(a, offa + 416, w, nw - 32);
         cftf161(a, offa + 448, w, nw - 8);
      } else {
         cftmdl1(64, (float[])a, offa, (float[])w, nw - 32);
         cftf081(a, offa, w, nw - 8);
         cftf082(a, offa + 16, w, nw - 8);
         cftf081(a, offa + 32, w, nw - 8);
         cftf081(a, offa + 48, w, nw - 8);
         cftmdl2(64, (float[])a, offa + 64, (float[])w, nw - 64);
         cftf081(a, offa + 64, w, nw - 8);
         cftf082(a, offa + 80, w, nw - 8);
         cftf081(a, offa + 96, w, nw - 8);
         cftf082(a, offa + 112, w, nw - 8);
         cftmdl1(64, (float[])a, offa + 128, (float[])w, nw - 32);
         cftf081(a, offa + 128, w, nw - 8);
         cftf082(a, offa + 144, w, nw - 8);
         cftf081(a, offa + 160, w, nw - 8);
         cftf081(a, offa + 176, w, nw - 8);
         if (isplt != 0) {
            cftmdl1(64, (float[])a, offa + 192, (float[])w, nw - 32);
            cftf081(a, offa + 240, w, nw - 8);
         } else {
            cftmdl2(64, (float[])a, offa + 192, (float[])w, nw - 64);
            cftf082(a, offa + 240, w, nw - 8);
         }

         cftf081(a, offa + 192, w, nw - 8);
         cftf082(a, offa + 208, w, nw - 8);
         cftf081(a, offa + 224, w, nw - 8);
      }

   }

   public static void cftleaf(long n, long isplt, FloatLargeArray a, long offa, long nw, FloatLargeArray w) {
      if (n == 512L) {
         cftmdl1(128L, a, offa, w, nw - 64L);
         cftf161(a, offa, w, nw - 8L);
         cftf162(a, offa + 32L, w, nw - 32L);
         cftf161(a, offa + 64L, w, nw - 8L);
         cftf161(a, offa + 96L, w, nw - 8L);
         cftmdl2(128L, a, offa + 128L, w, nw - 128L);
         cftf161(a, offa + 128L, w, nw - 8L);
         cftf162(a, offa + 160L, w, nw - 32L);
         cftf161(a, offa + 192L, w, nw - 8L);
         cftf162(a, offa + 224L, w, nw - 32L);
         cftmdl1(128L, a, offa + 256L, w, nw - 64L);
         cftf161(a, offa + 256L, w, nw - 8L);
         cftf162(a, offa + 288L, w, nw - 32L);
         cftf161(a, offa + 320L, w, nw - 8L);
         cftf161(a, offa + 352L, w, nw - 8L);
         if (isplt != 0L) {
            cftmdl1(128L, a, offa + 384L, w, nw - 64L);
            cftf161(a, offa + 480L, w, nw - 8L);
         } else {
            cftmdl2(128L, a, offa + 384L, w, nw - 128L);
            cftf162(a, offa + 480L, w, nw - 32L);
         }

         cftf161(a, offa + 384L, w, nw - 8L);
         cftf162(a, offa + 416L, w, nw - 32L);
         cftf161(a, offa + 448L, w, nw - 8L);
      } else {
         cftmdl1(64L, a, offa, w, nw - 32L);
         cftf081(a, offa, w, nw - 8L);
         cftf082(a, offa + 16L, w, nw - 8L);
         cftf081(a, offa + 32L, w, nw - 8L);
         cftf081(a, offa + 48L, w, nw - 8L);
         cftmdl2(64L, a, offa + 64L, w, nw - 64L);
         cftf081(a, offa + 64L, w, nw - 8L);
         cftf082(a, offa + 80L, w, nw - 8L);
         cftf081(a, offa + 96L, w, nw - 8L);
         cftf082(a, offa + 112L, w, nw - 8L);
         cftmdl1(64L, a, offa + 128L, w, nw - 32L);
         cftf081(a, offa + 128L, w, nw - 8L);
         cftf082(a, offa + 144L, w, nw - 8L);
         cftf081(a, offa + 160L, w, nw - 8L);
         cftf081(a, offa + 176L, w, nw - 8L);
         if (isplt != 0L) {
            cftmdl1(64L, a, offa + 192L, w, nw - 32L);
            cftf081(a, offa + 240L, w, nw - 8L);
         } else {
            cftmdl2(64L, a, offa + 192L, w, nw - 64L);
            cftf082(a, offa + 240L, w, nw - 8L);
         }

         cftf081(a, offa + 192L, w, nw - 8L);
         cftf082(a, offa + 208L, w, nw - 8L);
         cftf081(a, offa + 224L, w, nw - 8L);
      }

   }

   public static void cftmdl1(int n, float[] a, int offa, float[] w, int startw) {
      int mh = n >> 3;
      int m = 2 * mh;
      int j2 = m + m;
      int j3 = j2 + m;
      int idx1 = offa + m;
      int idx2 = offa + j2;
      int idx3 = offa + j3;
      float x0r = a[offa] + a[idx2];
      float x0i = a[offa + 1] + a[idx2 + 1];
      float x1r = a[offa] - a[idx2];
      float x1i = a[offa + 1] - a[idx2 + 1];
      float x2r = a[idx1] + a[idx3];
      float x2i = a[idx1 + 1] + a[idx3 + 1];
      float x3r = a[idx1] - a[idx3];
      float x3i = a[idx1 + 1] - a[idx3 + 1];
      a[offa] = x0r + x2r;
      a[offa + 1] = x0i + x2i;
      a[idx1] = x0r - x2r;
      a[idx1 + 1] = x0i - x2i;
      a[idx2] = x1r - x3i;
      a[idx2 + 1] = x1i + x3r;
      a[idx3] = x1r + x3i;
      a[idx3 + 1] = x1i - x3r;
      float wn4r = w[startw + 1];
      int k = 0;

      for(int j = 2; j < mh; j += 2) {
         k += 4;
         int idx4 = startw + k;
         float wk1r = w[idx4];
         float wk1i = w[idx4 + 1];
         float wk3r = w[idx4 + 2];
         float wk3i = w[idx4 + 3];
         int j1 = j + m;
         j2 = j1 + m;
         j3 = j2 + m;
         idx1 = offa + j1;
         idx2 = offa + j2;
         idx3 = offa + j3;
         int idx5 = offa + j;
         x0r = a[idx5] + a[idx2];
         x0i = a[idx5 + 1] + a[idx2 + 1];
         x1r = a[idx5] - a[idx2];
         x1i = a[idx5 + 1] - a[idx2 + 1];
         x2r = a[idx1] + a[idx3];
         x2i = a[idx1 + 1] + a[idx3 + 1];
         x3r = a[idx1] - a[idx3];
         x3i = a[idx1 + 1] - a[idx3 + 1];
         a[idx5] = x0r + x2r;
         a[idx5 + 1] = x0i + x2i;
         a[idx1] = x0r - x2r;
         a[idx1 + 1] = x0i - x2i;
         x0r = x1r - x3i;
         x0i = x1i + x3r;
         a[idx2] = wk1r * x0r - wk1i * x0i;
         a[idx2 + 1] = wk1r * x0i + wk1i * x0r;
         x0r = x1r + x3i;
         x0i = x1i - x3r;
         a[idx3] = wk3r * x0r + wk3i * x0i;
         a[idx3 + 1] = wk3r * x0i - wk3i * x0r;
         int j0 = m - j;
         j1 = j0 + m;
         j2 = j1 + m;
         j3 = j2 + m;
         int idx0 = offa + j0;
         idx1 = offa + j1;
         idx2 = offa + j2;
         idx3 = offa + j3;
         x0r = a[idx0] + a[idx2];
         x0i = a[idx0 + 1] + a[idx2 + 1];
         x1r = a[idx0] - a[idx2];
         x1i = a[idx0 + 1] - a[idx2 + 1];
         x2r = a[idx1] + a[idx3];
         x2i = a[idx1 + 1] + a[idx3 + 1];
         x3r = a[idx1] - a[idx3];
         x3i = a[idx1 + 1] - a[idx3 + 1];
         a[idx0] = x0r + x2r;
         a[idx0 + 1] = x0i + x2i;
         a[idx1] = x0r - x2r;
         a[idx1 + 1] = x0i - x2i;
         x0r = x1r - x3i;
         x0i = x1i + x3r;
         a[idx2] = wk1i * x0r - wk1r * x0i;
         a[idx2 + 1] = wk1i * x0i + wk1r * x0r;
         x0r = x1r + x3i;
         x0i = x1i - x3r;
         a[idx3] = wk3i * x0r + wk3r * x0i;
         a[idx3 + 1] = wk3i * x0i - wk3r * x0r;
      }

      int var33 = mh + m;
      j2 = var33 + m;
      j3 = j2 + m;
      int idx0 = offa + mh;
      idx1 = offa + var33;
      idx2 = offa + j2;
      idx3 = offa + j3;
      x0r = a[idx0] + a[idx2];
      x0i = a[idx0 + 1] + a[idx2 + 1];
      x1r = a[idx0] - a[idx2];
      x1i = a[idx0 + 1] - a[idx2 + 1];
      x2r = a[idx1] + a[idx3];
      x2i = a[idx1 + 1] + a[idx3 + 1];
      x3r = a[idx1] - a[idx3];
      x3i = a[idx1 + 1] - a[idx3 + 1];
      a[idx0] = x0r + x2r;
      a[idx0 + 1] = x0i + x2i;
      a[idx1] = x0r - x2r;
      a[idx1 + 1] = x0i - x2i;
      x0r = x1r - x3i;
      x0i = x1i + x3r;
      a[idx2] = wn4r * (x0r - x0i);
      a[idx2 + 1] = wn4r * (x0i + x0r);
      x0r = x1r + x3i;
      x0i = x1i - x3r;
      a[idx3] = -wn4r * (x0r + x0i);
      a[idx3 + 1] = -wn4r * (x0i - x0r);
   }

   public static void cftmdl1(long n, FloatLargeArray a, long offa, FloatLargeArray w, long startw) {
      long mh = n >> 3;
      long m = 2L * mh;
      long j2 = m + m;
      long j3 = j2 + m;
      long idx1 = offa + m;
      long idx2 = offa + j2;
      long idx3 = offa + j3;
      float x0r = a.getFloat(offa) + a.getFloat(idx2);
      float x0i = a.getFloat(offa + 1L) + a.getFloat(idx2 + 1L);
      float x1r = a.getFloat(offa) - a.getFloat(idx2);
      float x1i = a.getFloat(offa + 1L) - a.getFloat(idx2 + 1L);
      float x2r = a.getFloat(idx1) + a.getFloat(idx3);
      float x2i = a.getFloat(idx1 + 1L) + a.getFloat(idx3 + 1L);
      float x3r = a.getFloat(idx1) - a.getFloat(idx3);
      float x3i = a.getFloat(idx1 + 1L) - a.getFloat(idx3 + 1L);
      a.setFloat(offa, x0r + x2r);
      a.setFloat(offa + 1L, x0i + x2i);
      a.setFloat(idx1, x0r - x2r);
      a.setFloat(idx1 + 1L, x0i - x2i);
      a.setFloat(idx2, x1r - x3i);
      a.setFloat(idx2 + 1L, x1i + x3r);
      a.setFloat(idx3, x1r + x3i);
      a.setFloat(idx3 + 1L, x1i - x3r);
      float wn4r = w.getFloat(startw + 1L);
      long k = 0L;

      for(long j = 2L; j < mh; j += 2L) {
         k += 4L;
         long idx4 = startw + k;
         float wk1r = w.getFloat(idx4);
         float wk1i = w.getFloat(idx4 + 1L);
         float wk3r = w.getFloat(idx4 + 2L);
         float wk3i = w.getFloat(idx4 + 3L);
         long j1 = j + m;
         j2 = j1 + m;
         j3 = j2 + m;
         idx1 = offa + j1;
         idx2 = offa + j2;
         idx3 = offa + j3;
         long idx5 = offa + j;
         x0r = a.getFloat(idx5) + a.getFloat(idx2);
         x0i = a.getFloat(idx5 + 1L) + a.getFloat(idx2 + 1L);
         x1r = a.getFloat(idx5) - a.getFloat(idx2);
         x1i = a.getFloat(idx5 + 1L) - a.getFloat(idx2 + 1L);
         x2r = a.getFloat(idx1) + a.getFloat(idx3);
         x2i = a.getFloat(idx1 + 1L) + a.getFloat(idx3 + 1L);
         x3r = a.getFloat(idx1) - a.getFloat(idx3);
         x3i = a.getFloat(idx1 + 1L) - a.getFloat(idx3 + 1L);
         a.setFloat(idx5, x0r + x2r);
         a.setFloat(idx5 + 1L, x0i + x2i);
         a.setFloat(idx1, x0r - x2r);
         a.setFloat(idx1 + 1L, x0i - x2i);
         x0r = x1r - x3i;
         x0i = x1i + x3r;
         a.setFloat(idx2, wk1r * x0r - wk1i * x0i);
         a.setFloat(idx2 + 1L, wk1r * x0i + wk1i * x0r);
         x0r = x1r + x3i;
         x0i = x1i - x3r;
         a.setFloat(idx3, wk3r * x0r + wk3i * x0i);
         a.setFloat(idx3 + 1L, wk3r * x0i - wk3i * x0r);
         long j0 = m - j;
         j1 = j0 + m;
         j2 = j1 + m;
         j3 = j2 + m;
         long idx0 = offa + j0;
         idx1 = offa + j1;
         idx2 = offa + j2;
         idx3 = offa + j3;
         x0r = a.getFloat(idx0) + a.getFloat(idx2);
         x0i = a.getFloat(idx0 + 1L) + a.getFloat(idx2 + 1L);
         x1r = a.getFloat(idx0) - a.getFloat(idx2);
         x1i = a.getFloat(idx0 + 1L) - a.getFloat(idx2 + 1L);
         x2r = a.getFloat(idx1) + a.getFloat(idx3);
         x2i = a.getFloat(idx1 + 1L) + a.getFloat(idx3 + 1L);
         x3r = a.getFloat(idx1) - a.getFloat(idx3);
         x3i = a.getFloat(idx1 + 1L) - a.getFloat(idx3 + 1L);
         a.setFloat(idx0, x0r + x2r);
         a.setFloat(idx0 + 1L, x0i + x2i);
         a.setFloat(idx1, x0r - x2r);
         a.setFloat(idx1 + 1L, x0i - x2i);
         x0r = x1r - x3i;
         x0i = x1i + x3r;
         a.setFloat(idx2, wk1i * x0r - wk1r * x0i);
         a.setFloat(idx2 + 1L, wk1i * x0i + wk1r * x0r);
         x0r = x1r + x3i;
         x0i = x1i - x3r;
         a.setFloat(idx3, wk3i * x0r + wk3r * x0i);
         a.setFloat(idx3 + 1L, wk3i * x0i - wk3r * x0r);
      }

      long var50 = mh + m;
      j2 = var50 + m;
      j3 = j2 + m;
      long idx0 = offa + mh;
      idx1 = offa + var50;
      idx2 = offa + j2;
      idx3 = offa + j3;
      x0r = a.getFloat(idx0) + a.getFloat(idx2);
      x0i = a.getFloat(idx0 + 1L) + a.getFloat(idx2 + 1L);
      x1r = a.getFloat(idx0) - a.getFloat(idx2);
      x1i = a.getFloat(idx0 + 1L) - a.getFloat(idx2 + 1L);
      x2r = a.getFloat(idx1) + a.getFloat(idx3);
      x2i = a.getFloat(idx1 + 1L) + a.getFloat(idx3 + 1L);
      x3r = a.getFloat(idx1) - a.getFloat(idx3);
      x3i = a.getFloat(idx1 + 1L) - a.getFloat(idx3 + 1L);
      a.setFloat(idx0, x0r + x2r);
      a.setFloat(idx0 + 1L, x0i + x2i);
      a.setFloat(idx1, x0r - x2r);
      a.setFloat(idx1 + 1L, x0i - x2i);
      x0r = x1r - x3i;
      x0i = x1i + x3r;
      a.setFloat(idx2, wn4r * (x0r - x0i));
      a.setFloat(idx2 + 1L, wn4r * (x0i + x0r));
      x0r = x1r + x3i;
      x0i = x1i - x3r;
      a.setFloat(idx3, -wn4r * (x0r + x0i));
      a.setFloat(idx3 + 1L, -wn4r * (x0i - x0r));
   }

   public static void cftmdl2(int n, float[] a, int offa, float[] w, int startw) {
      int mh = n >> 3;
      int m = 2 * mh;
      float wn4r = w[startw + 1];
      int j2 = m + m;
      int j3 = j2 + m;
      int idx1 = offa + m;
      int idx2 = offa + j2;
      int idx3 = offa + j3;
      float x0r = a[offa] - a[idx2 + 1];
      float x0i = a[offa + 1] + a[idx2];
      float x1r = a[offa] + a[idx2 + 1];
      float x1i = a[offa + 1] - a[idx2];
      float x2r = a[idx1] - a[idx3 + 1];
      float x2i = a[idx1 + 1] + a[idx3];
      float x3r = a[idx1] + a[idx3 + 1];
      float x3i = a[idx1 + 1] - a[idx3];
      float y0r = wn4r * (x2r - x2i);
      float y0i = wn4r * (x2i + x2r);
      a[offa] = x0r + y0r;
      a[offa + 1] = x0i + y0i;
      a[idx1] = x0r - y0r;
      a[idx1 + 1] = x0i - y0i;
      y0r = wn4r * (x3r - x3i);
      y0i = wn4r * (x3i + x3r);
      a[idx2] = x1r - y0i;
      a[idx2 + 1] = x1i + y0r;
      a[idx3] = x1r + y0i;
      a[idx3 + 1] = x1i - y0r;
      int k = 0;
      int kr = 2 * m;

      for(int j = 2; j < mh; j += 2) {
         k += 4;
         int idx4 = startw + k;
         float wk1r = w[idx4];
         float wk1i = w[idx4 + 1];
         float wk3r = w[idx4 + 2];
         float wk3i = w[idx4 + 3];
         kr -= 4;
         int idx5 = startw + kr;
         float wd1i = w[idx5];
         float wd1r = w[idx5 + 1];
         float wd3i = w[idx5 + 2];
         float wd3r = w[idx5 + 3];
         int j1 = j + m;
         j2 = j1 + m;
         j3 = j2 + m;
         idx1 = offa + j1;
         idx2 = offa + j2;
         idx3 = offa + j3;
         int idx6 = offa + j;
         x0r = a[idx6] - a[idx2 + 1];
         x0i = a[idx6 + 1] + a[idx2];
         x1r = a[idx6] + a[idx2 + 1];
         x1i = a[idx6 + 1] - a[idx2];
         x2r = a[idx1] - a[idx3 + 1];
         x2i = a[idx1 + 1] + a[idx3];
         x3r = a[idx1] + a[idx3 + 1];
         x3i = a[idx1 + 1] - a[idx3];
         y0r = wk1r * x0r - wk1i * x0i;
         y0i = wk1r * x0i + wk1i * x0r;
         float y2r = wd1r * x2r - wd1i * x2i;
         float y2i = wd1r * x2i + wd1i * x2r;
         a[idx6] = y0r + y2r;
         a[idx6 + 1] = y0i + y2i;
         a[idx1] = y0r - y2r;
         a[idx1 + 1] = y0i - y2i;
         y0r = wk3r * x1r + wk3i * x1i;
         y0i = wk3r * x1i - wk3i * x1r;
         y2r = wd3r * x3r + wd3i * x3i;
         y2i = wd3r * x3i - wd3i * x3r;
         a[idx2] = y0r + y2r;
         a[idx2 + 1] = y0i + y2i;
         a[idx3] = y0r - y2r;
         a[idx3 + 1] = y0i - y2i;
         int j0 = m - j;
         j1 = j0 + m;
         j2 = j1 + m;
         j3 = j2 + m;
         int idx0 = offa + j0;
         idx1 = offa + j1;
         idx2 = offa + j2;
         idx3 = offa + j3;
         x0r = a[idx0] - a[idx2 + 1];
         x0i = a[idx0 + 1] + a[idx2];
         x1r = a[idx0] + a[idx2 + 1];
         x1i = a[idx0 + 1] - a[idx2];
         x2r = a[idx1] - a[idx3 + 1];
         x2i = a[idx1 + 1] + a[idx3];
         x3r = a[idx1] + a[idx3 + 1];
         x3i = a[idx1 + 1] - a[idx3];
         y0r = wd1i * x0r - wd1r * x0i;
         y0i = wd1i * x0i + wd1r * x0r;
         y2r = wk1i * x2r - wk1r * x2i;
         y2i = wk1i * x2i + wk1r * x2r;
         a[idx0] = y0r + y2r;
         a[idx0 + 1] = y0i + y2i;
         a[idx1] = y0r - y2r;
         a[idx1 + 1] = y0i - y2i;
         y0r = wd3i * x1r + wd3r * x1i;
         y0i = wd3i * x1i - wd3r * x1r;
         y2r = wk3i * x3r + wk3r * x3i;
         y2i = wk3i * x3i - wk3r * x3r;
         a[idx2] = y0r + y2r;
         a[idx2 + 1] = y0i + y2i;
         a[idx3] = y0r - y2r;
         a[idx3 + 1] = y0i - y2i;
      }

      float wk1r = w[startw + m];
      float wk1i = w[startw + m + 1];
      int var43 = mh + m;
      j2 = var43 + m;
      j3 = j2 + m;
      int idx0 = offa + mh;
      idx1 = offa + var43;
      idx2 = offa + j2;
      idx3 = offa + j3;
      x0r = a[idx0] - a[idx2 + 1];
      x0i = a[idx0 + 1] + a[idx2];
      x1r = a[idx0] + a[idx2 + 1];
      x1i = a[idx0 + 1] - a[idx2];
      x2r = a[idx1] - a[idx3 + 1];
      x2i = a[idx1 + 1] + a[idx3];
      x3r = a[idx1] + a[idx3 + 1];
      x3i = a[idx1 + 1] - a[idx3];
      y0r = wk1r * x0r - wk1i * x0i;
      y0i = wk1r * x0i + wk1i * x0r;
      float y2r = wk1i * x2r - wk1r * x2i;
      float y2i = wk1i * x2i + wk1r * x2r;
      a[idx0] = y0r + y2r;
      a[idx0 + 1] = y0i + y2i;
      a[idx1] = y0r - y2r;
      a[idx1 + 1] = y0i - y2i;
      y0r = wk1i * x1r - wk1r * x1i;
      y0i = wk1i * x1i + wk1r * x1r;
      y2r = wk1r * x3r - wk1i * x3i;
      y2i = wk1r * x3i + wk1i * x3r;
      a[idx2] = y0r - y2r;
      a[idx2 + 1] = y0i - y2i;
      a[idx3] = y0r + y2r;
      a[idx3 + 1] = y0i + y2i;
   }

   public static void cftmdl2(long n, FloatLargeArray a, long offa, FloatLargeArray w, long startw) {
      long mh = n >> 3;
      long m = 2L * mh;
      float wn4r = w.getFloat(startw + 1L);
      long j2 = m + m;
      long j3 = j2 + m;
      long idx1 = offa + m;
      long idx2 = offa + j2;
      long idx3 = offa + j3;
      float x0r = a.getFloat(offa) - a.getFloat(idx2 + 1L);
      float x0i = a.getFloat(offa + 1L) + a.getFloat(idx2);
      float x1r = a.getFloat(offa) + a.getFloat(idx2 + 1L);
      float x1i = a.getFloat(offa + 1L) - a.getFloat(idx2);
      float x2r = a.getFloat(idx1) - a.getFloat(idx3 + 1L);
      float x2i = a.getFloat(idx1 + 1L) + a.getFloat(idx3);
      float x3r = a.getFloat(idx1) + a.getFloat(idx3 + 1L);
      float x3i = a.getFloat(idx1 + 1L) - a.getFloat(idx3);
      float y0r = wn4r * (x2r - x2i);
      float y0i = wn4r * (x2i + x2r);
      a.setFloat(offa, x0r + y0r);
      a.setFloat(offa + 1L, x0i + y0i);
      a.setFloat(idx1, x0r - y0r);
      a.setFloat(idx1 + 1L, x0i - y0i);
      y0r = wn4r * (x3r - x3i);
      y0i = wn4r * (x3i + x3r);
      a.setFloat(idx2, x1r - y0i);
      a.setFloat(idx2 + 1L, x1i + y0r);
      a.setFloat(idx3, x1r + y0i);
      a.setFloat(idx3 + 1L, x1i - y0r);
      long k = 0L;
      long kr = 2L * m;

      for(int j = 2; (long)j < mh; j += 2) {
         k += 4L;
         long idx4 = startw + k;
         float wk1r = w.getFloat(idx4);
         float wk1i = w.getFloat(idx4 + 1L);
         float wk3r = w.getFloat(idx4 + 2L);
         float wk3i = w.getFloat(idx4 + 3L);
         kr -= 4L;
         long idx5 = startw + kr;
         float wd1i = w.getFloat(idx5);
         float wd1r = w.getFloat(idx5 + 1L);
         float wd3i = w.getFloat(idx5 + 2L);
         float wd3r = w.getFloat(idx5 + 3L);
         long j1 = (long)j + m;
         j2 = j1 + m;
         j3 = j2 + m;
         idx1 = offa + j1;
         idx2 = offa + j2;
         idx3 = offa + j3;
         long idx6 = offa + (long)j;
         x0r = a.getFloat(idx6) - a.getFloat(idx2 + 1L);
         x0i = a.getFloat(idx6 + 1L) + a.getFloat(idx2);
         x1r = a.getFloat(idx6) + a.getFloat(idx2 + 1L);
         x1i = a.getFloat(idx6 + 1L) - a.getFloat(idx2);
         x2r = a.getFloat(idx1) - a.getFloat(idx3 + 1L);
         x2i = a.getFloat(idx1 + 1L) + a.getFloat(idx3);
         x3r = a.getFloat(idx1) + a.getFloat(idx3 + 1L);
         x3i = a.getFloat(idx1 + 1L) - a.getFloat(idx3);
         y0r = wk1r * x0r - wk1i * x0i;
         y0i = wk1r * x0i + wk1i * x0r;
         float y2r = wd1r * x2r - wd1i * x2i;
         float y2i = wd1r * x2i + wd1i * x2r;
         a.setFloat(idx6, y0r + y2r);
         a.setFloat(idx6 + 1L, y0i + y2i);
         a.setFloat(idx1, y0r - y2r);
         a.setFloat(idx1 + 1L, y0i - y2i);
         y0r = wk3r * x1r + wk3i * x1i;
         y0i = wk3r * x1i - wk3i * x1r;
         y2r = wd3r * x3r + wd3i * x3i;
         y2i = wd3r * x3i - wd3i * x3r;
         a.setFloat(idx2, y0r + y2r);
         a.setFloat(idx2 + 1L, y0i + y2i);
         a.setFloat(idx3, y0r - y2r);
         a.setFloat(idx3 + 1L, y0i - y2i);
         long j0 = m - (long)j;
         j1 = j0 + m;
         j2 = j1 + m;
         j3 = j2 + m;
         long idx0 = offa + j0;
         idx1 = offa + j1;
         idx2 = offa + j2;
         idx3 = offa + j3;
         x0r = a.getFloat(idx0) - a.getFloat(idx2 + 1L);
         x0i = a.getFloat(idx0 + 1L) + a.getFloat(idx2);
         x1r = a.getFloat(idx0) + a.getFloat(idx2 + 1L);
         x1i = a.getFloat(idx0 + 1L) - a.getFloat(idx2);
         x2r = a.getFloat(idx1) - a.getFloat(idx3 + 1L);
         x2i = a.getFloat(idx1 + 1L) + a.getFloat(idx3);
         x3r = a.getFloat(idx1) + a.getFloat(idx3 + 1L);
         x3i = a.getFloat(idx1 + 1L) - a.getFloat(idx3);
         y0r = wd1i * x0r - wd1r * x0i;
         y0i = wd1i * x0i + wd1r * x0r;
         y2r = wk1i * x2r - wk1r * x2i;
         y2i = wk1i * x2i + wk1r * x2r;
         a.setFloat(idx0, y0r + y2r);
         a.setFloat(idx0 + 1L, y0i + y2i);
         a.setFloat(idx1, y0r - y2r);
         a.setFloat(idx1 + 1L, y0i - y2i);
         y0r = wd3i * x1r + wd3r * x1i;
         y0i = wd3i * x1i - wd3r * x1r;
         y2r = wk3i * x3r + wk3r * x3i;
         y2i = wk3i * x3i - wk3r * x3r;
         a.setFloat(idx2, y0r + y2r);
         a.setFloat(idx2 + 1L, y0i + y2i);
         a.setFloat(idx3, y0r - y2r);
         a.setFloat(idx3 + 1L, y0i - y2i);
      }

      float wk1r = w.getFloat(startw + m);
      float wk1i = w.getFloat(startw + m + 1L);
      long var61 = mh + m;
      j2 = var61 + m;
      j3 = j2 + m;
      long idx0 = offa + mh;
      idx1 = offa + var61;
      idx2 = offa + j2;
      idx3 = offa + j3;
      x0r = a.getFloat(idx0) - a.getFloat(idx2 + 1L);
      x0i = a.getFloat(idx0 + 1L) + a.getFloat(idx2);
      x1r = a.getFloat(idx0) + a.getFloat(idx2 + 1L);
      x1i = a.getFloat(idx0 + 1L) - a.getFloat(idx2);
      x2r = a.getFloat(idx1) - a.getFloat(idx3 + 1L);
      x2i = a.getFloat(idx1 + 1L) + a.getFloat(idx3);
      x3r = a.getFloat(idx1) + a.getFloat(idx3 + 1L);
      x3i = a.getFloat(idx1 + 1L) - a.getFloat(idx3);
      y0r = wk1r * x0r - wk1i * x0i;
      y0i = wk1r * x0i + wk1i * x0r;
      float y2r = wk1i * x2r - wk1r * x2i;
      float y2i = wk1i * x2i + wk1r * x2r;
      a.setFloat(idx0, y0r + y2r);
      a.setFloat(idx0 + 1L, y0i + y2i);
      a.setFloat(idx1, y0r - y2r);
      a.setFloat(idx1 + 1L, y0i - y2i);
      y0r = wk1i * x1r - wk1r * x1i;
      y0i = wk1i * x1i + wk1r * x1r;
      y2r = wk1r * x3r - wk1i * x3i;
      y2i = wk1r * x3i + wk1i * x3r;
      a.setFloat(idx2, y0r - y2r);
      a.setFloat(idx2 + 1L, y0i - y2i);
      a.setFloat(idx3, y0r + y2r);
      a.setFloat(idx3 + 1L, y0i + y2i);
   }

   public static void cftfx41(int n, float[] a, int offa, int nw, float[] w) {
      if (n == 128) {
         cftf161(a, offa, w, nw - 8);
         cftf162(a, offa + 32, w, nw - 32);
         cftf161(a, offa + 64, w, nw - 8);
         cftf161(a, offa + 96, w, nw - 8);
      } else {
         cftf081(a, offa, w, nw - 8);
         cftf082(a, offa + 16, w, nw - 8);
         cftf081(a, offa + 32, w, nw - 8);
         cftf081(a, offa + 48, w, nw - 8);
      }

   }

   public static void cftfx41(long n, FloatLargeArray a, long offa, long nw, FloatLargeArray w) {
      if (n == 128L) {
         cftf161(a, offa, w, nw - 8L);
         cftf162(a, offa + 32L, w, nw - 32L);
         cftf161(a, offa + 64L, w, nw - 8L);
         cftf161(a, offa + 96L, w, nw - 8L);
      } else {
         cftf081(a, offa, w, nw - 8L);
         cftf082(a, offa + 16L, w, nw - 8L);
         cftf081(a, offa + 32L, w, nw - 8L);
         cftf081(a, offa + 48L, w, nw - 8L);
      }

   }

   public static void cftf161(float[] a, int offa, float[] w, int startw) {
      float wn4r = w[startw + 1];
      float wk1r = w[startw + 2];
      float wk1i = w[startw + 3];
      float x0r = a[offa] + a[offa + 16];
      float x0i = a[offa + 1] + a[offa + 17];
      float x1r = a[offa] - a[offa + 16];
      float x1i = a[offa + 1] - a[offa + 17];
      float x2r = a[offa + 8] + a[offa + 24];
      float x2i = a[offa + 9] + a[offa + 25];
      float x3r = a[offa + 8] - a[offa + 24];
      float x3i = a[offa + 9] - a[offa + 25];
      float y0r = x0r + x2r;
      float y0i = x0i + x2i;
      float y4r = x0r - x2r;
      float y4i = x0i - x2i;
      float y8r = x1r - x3i;
      float y8i = x1i + x3r;
      float y12r = x1r + x3i;
      float y12i = x1i - x3r;
      x0r = a[offa + 2] + a[offa + 18];
      x0i = a[offa + 3] + a[offa + 19];
      x1r = a[offa + 2] - a[offa + 18];
      x1i = a[offa + 3] - a[offa + 19];
      x2r = a[offa + 10] + a[offa + 26];
      x2i = a[offa + 11] + a[offa + 27];
      x3r = a[offa + 10] - a[offa + 26];
      x3i = a[offa + 11] - a[offa + 27];
      float y1r = x0r + x2r;
      float y1i = x0i + x2i;
      float y5r = x0r - x2r;
      float y5i = x0i - x2i;
      x0r = x1r - x3i;
      x0i = x1i + x3r;
      float y9r = wk1r * x0r - wk1i * x0i;
      float y9i = wk1r * x0i + wk1i * x0r;
      x0r = x1r + x3i;
      x0i = x1i - x3r;
      float y13r = wk1i * x0r - wk1r * x0i;
      float y13i = wk1i * x0i + wk1r * x0r;
      x0r = a[offa + 4] + a[offa + 20];
      x0i = a[offa + 5] + a[offa + 21];
      x1r = a[offa + 4] - a[offa + 20];
      x1i = a[offa + 5] - a[offa + 21];
      x2r = a[offa + 12] + a[offa + 28];
      x2i = a[offa + 13] + a[offa + 29];
      x3r = a[offa + 12] - a[offa + 28];
      x3i = a[offa + 13] - a[offa + 29];
      float y2r = x0r + x2r;
      float y2i = x0i + x2i;
      float y6r = x0r - x2r;
      float y6i = x0i - x2i;
      x0r = x1r - x3i;
      x0i = x1i + x3r;
      float y10r = wn4r * (x0r - x0i);
      float y10i = wn4r * (x0i + x0r);
      x0r = x1r + x3i;
      x0i = x1i - x3r;
      float y14r = wn4r * (x0r + x0i);
      float y14i = wn4r * (x0i - x0r);
      x0r = a[offa + 6] + a[offa + 22];
      x0i = a[offa + 7] + a[offa + 23];
      x1r = a[offa + 6] - a[offa + 22];
      x1i = a[offa + 7] - a[offa + 23];
      x2r = a[offa + 14] + a[offa + 30];
      x2i = a[offa + 15] + a[offa + 31];
      x3r = a[offa + 14] - a[offa + 30];
      x3i = a[offa + 15] - a[offa + 31];
      float y3r = x0r + x2r;
      float y3i = x0i + x2i;
      float y7r = x0r - x2r;
      float y7i = x0i - x2i;
      x0r = x1r - x3i;
      x0i = x1i + x3r;
      float y11r = wk1i * x0r - wk1r * x0i;
      float y11i = wk1i * x0i + wk1r * x0r;
      x0r = x1r + x3i;
      x0i = x1i - x3r;
      float y15r = wk1r * x0r - wk1i * x0i;
      float y15i = wk1r * x0i + wk1i * x0r;
      x0r = y12r - y14r;
      x0i = y12i - y14i;
      x1r = y12r + y14r;
      x1i = y12i + y14i;
      x2r = y13r - y15r;
      x2i = y13i - y15i;
      x3r = y13r + y15r;
      x3i = y13i + y15i;
      a[offa + 24] = x0r + x2r;
      a[offa + 25] = x0i + x2i;
      a[offa + 26] = x0r - x2r;
      a[offa + 27] = x0i - x2i;
      a[offa + 28] = x1r - x3i;
      a[offa + 29] = x1i + x3r;
      a[offa + 30] = x1r + x3i;
      a[offa + 31] = x1i - x3r;
      x0r = y8r + y10r;
      x0i = y8i + y10i;
      x1r = y8r - y10r;
      x1i = y8i - y10i;
      x2r = y9r + y11r;
      x2i = y9i + y11i;
      x3r = y9r - y11r;
      x3i = y9i - y11i;
      a[offa + 16] = x0r + x2r;
      a[offa + 17] = x0i + x2i;
      a[offa + 18] = x0r - x2r;
      a[offa + 19] = x0i - x2i;
      a[offa + 20] = x1r - x3i;
      a[offa + 21] = x1i + x3r;
      a[offa + 22] = x1r + x3i;
      a[offa + 23] = x1i - x3r;
      x0r = y5r - y7i;
      x0i = y5i + y7r;
      x2r = wn4r * (x0r - x0i);
      x2i = wn4r * (x0i + x0r);
      x0r = y5r + y7i;
      x0i = y5i - y7r;
      x3r = wn4r * (x0r - x0i);
      x3i = wn4r * (x0i + x0r);
      x0r = y4r - y6i;
      x0i = y4i + y6r;
      x1r = y4r + y6i;
      x1i = y4i - y6r;
      a[offa + 8] = x0r + x2r;
      a[offa + 9] = x0i + x2i;
      a[offa + 10] = x0r - x2r;
      a[offa + 11] = x0i - x2i;
      a[offa + 12] = x1r - x3i;
      a[offa + 13] = x1i + x3r;
      a[offa + 14] = x1r + x3i;
      a[offa + 15] = x1i - x3r;
      x0r = y0r + y2r;
      x0i = y0i + y2i;
      x1r = y0r - y2r;
      x1i = y0i - y2i;
      x2r = y1r + y3r;
      x2i = y1i + y3i;
      x3r = y1r - y3r;
      x3i = y1i - y3i;
      a[offa] = x0r + x2r;
      a[offa + 1] = x0i + x2i;
      a[offa + 2] = x0r - x2r;
      a[offa + 3] = x0i - x2i;
      a[offa + 4] = x1r - x3i;
      a[offa + 5] = x1i + x3r;
      a[offa + 6] = x1r + x3i;
      a[offa + 7] = x1i - x3r;
   }

   public static void cftf161(FloatLargeArray a, long offa, FloatLargeArray w, long startw) {
      float wn4r = w.getFloat(startw + 1L);
      float wk1r = w.getFloat(startw + 2L);
      float wk1i = w.getFloat(startw + 3L);
      float x0r = a.getFloat(offa) + a.getFloat(offa + 16L);
      float x0i = a.getFloat(offa + 1L) + a.getFloat(offa + 17L);
      float x1r = a.getFloat(offa) - a.getFloat(offa + 16L);
      float x1i = a.getFloat(offa + 1L) - a.getFloat(offa + 17L);
      float x2r = a.getFloat(offa + 8L) + a.getFloat(offa + 24L);
      float x2i = a.getFloat(offa + 9L) + a.getFloat(offa + 25L);
      float x3r = a.getFloat(offa + 8L) - a.getFloat(offa + 24L);
      float x3i = a.getFloat(offa + 9L) - a.getFloat(offa + 25L);
      float y0r = x0r + x2r;
      float y0i = x0i + x2i;
      float y4r = x0r - x2r;
      float y4i = x0i - x2i;
      float y8r = x1r - x3i;
      float y8i = x1i + x3r;
      float y12r = x1r + x3i;
      float y12i = x1i - x3r;
      x0r = a.getFloat(offa + 2L) + a.getFloat(offa + 18L);
      x0i = a.getFloat(offa + 3L) + a.getFloat(offa + 19L);
      x1r = a.getFloat(offa + 2L) - a.getFloat(offa + 18L);
      x1i = a.getFloat(offa + 3L) - a.getFloat(offa + 19L);
      x2r = a.getFloat(offa + 10L) + a.getFloat(offa + 26L);
      x2i = a.getFloat(offa + 11L) + a.getFloat(offa + 27L);
      x3r = a.getFloat(offa + 10L) - a.getFloat(offa + 26L);
      x3i = a.getFloat(offa + 11L) - a.getFloat(offa + 27L);
      float y1r = x0r + x2r;
      float y1i = x0i + x2i;
      float y5r = x0r - x2r;
      float y5i = x0i - x2i;
      x0r = x1r - x3i;
      x0i = x1i + x3r;
      float y9r = wk1r * x0r - wk1i * x0i;
      float y9i = wk1r * x0i + wk1i * x0r;
      x0r = x1r + x3i;
      x0i = x1i - x3r;
      float y13r = wk1i * x0r - wk1r * x0i;
      float y13i = wk1i * x0i + wk1r * x0r;
      x0r = a.getFloat(offa + 4L) + a.getFloat(offa + 20L);
      x0i = a.getFloat(offa + 5L) + a.getFloat(offa + 21L);
      x1r = a.getFloat(offa + 4L) - a.getFloat(offa + 20L);
      x1i = a.getFloat(offa + 5L) - a.getFloat(offa + 21L);
      x2r = a.getFloat(offa + 12L) + a.getFloat(offa + 28L);
      x2i = a.getFloat(offa + 13L) + a.getFloat(offa + 29L);
      x3r = a.getFloat(offa + 12L) - a.getFloat(offa + 28L);
      x3i = a.getFloat(offa + 13L) - a.getFloat(offa + 29L);
      float y2r = x0r + x2r;
      float y2i = x0i + x2i;
      float y6r = x0r - x2r;
      float y6i = x0i - x2i;
      x0r = x1r - x3i;
      x0i = x1i + x3r;
      float y10r = wn4r * (x0r - x0i);
      float y10i = wn4r * (x0i + x0r);
      x0r = x1r + x3i;
      x0i = x1i - x3r;
      float y14r = wn4r * (x0r + x0i);
      float y14i = wn4r * (x0i - x0r);
      x0r = a.getFloat(offa + 6L) + a.getFloat(offa + 22L);
      x0i = a.getFloat(offa + 7L) + a.getFloat(offa + 23L);
      x1r = a.getFloat(offa + 6L) - a.getFloat(offa + 22L);
      x1i = a.getFloat(offa + 7L) - a.getFloat(offa + 23L);
      x2r = a.getFloat(offa + 14L) + a.getFloat(offa + 30L);
      x2i = a.getFloat(offa + 15L) + a.getFloat(offa + 31L);
      x3r = a.getFloat(offa + 14L) - a.getFloat(offa + 30L);
      x3i = a.getFloat(offa + 15L) - a.getFloat(offa + 31L);
      float y3r = x0r + x2r;
      float y3i = x0i + x2i;
      float y7r = x0r - x2r;
      float y7i = x0i - x2i;
      x0r = x1r - x3i;
      x0i = x1i + x3r;
      float y11r = wk1i * x0r - wk1r * x0i;
      float y11i = wk1i * x0i + wk1r * x0r;
      x0r = x1r + x3i;
      x0i = x1i - x3r;
      float y15r = wk1r * x0r - wk1i * x0i;
      float y15i = wk1r * x0i + wk1i * x0r;
      x0r = y12r - y14r;
      x0i = y12i - y14i;
      x1r = y12r + y14r;
      x1i = y12i + y14i;
      x2r = y13r - y15r;
      x2i = y13i - y15i;
      x3r = y13r + y15r;
      x3i = y13i + y15i;
      a.setFloat(offa + 24L, x0r + x2r);
      a.setFloat(offa + 25L, x0i + x2i);
      a.setFloat(offa + 26L, x0r - x2r);
      a.setFloat(offa + 27L, x0i - x2i);
      a.setFloat(offa + 28L, x1r - x3i);
      a.setFloat(offa + 29L, x1i + x3r);
      a.setFloat(offa + 30L, x1r + x3i);
      a.setFloat(offa + 31L, x1i - x3r);
      x0r = y8r + y10r;
      x0i = y8i + y10i;
      x1r = y8r - y10r;
      x1i = y8i - y10i;
      x2r = y9r + y11r;
      x2i = y9i + y11i;
      x3r = y9r - y11r;
      x3i = y9i - y11i;
      a.setFloat(offa + 16L, x0r + x2r);
      a.setFloat(offa + 17L, x0i + x2i);
      a.setFloat(offa + 18L, x0r - x2r);
      a.setFloat(offa + 19L, x0i - x2i);
      a.setFloat(offa + 20L, x1r - x3i);
      a.setFloat(offa + 21L, x1i + x3r);
      a.setFloat(offa + 22L, x1r + x3i);
      a.setFloat(offa + 23L, x1i - x3r);
      x0r = y5r - y7i;
      x0i = y5i + y7r;
      x2r = wn4r * (x0r - x0i);
      x2i = wn4r * (x0i + x0r);
      x0r = y5r + y7i;
      x0i = y5i - y7r;
      x3r = wn4r * (x0r - x0i);
      x3i = wn4r * (x0i + x0r);
      x0r = y4r - y6i;
      x0i = y4i + y6r;
      x1r = y4r + y6i;
      x1i = y4i - y6r;
      a.setFloat(offa + 8L, x0r + x2r);
      a.setFloat(offa + 9L, x0i + x2i);
      a.setFloat(offa + 10L, x0r - x2r);
      a.setFloat(offa + 11L, x0i - x2i);
      a.setFloat(offa + 12L, x1r - x3i);
      a.setFloat(offa + 13L, x1i + x3r);
      a.setFloat(offa + 14L, x1r + x3i);
      a.setFloat(offa + 15L, x1i - x3r);
      x0r = y0r + y2r;
      x0i = y0i + y2i;
      x1r = y0r - y2r;
      x1i = y0i - y2i;
      x2r = y1r + y3r;
      x2i = y1i + y3i;
      x3r = y1r - y3r;
      x3i = y1i - y3i;
      a.setFloat(offa, x0r + x2r);
      a.setFloat(offa + 1L, x0i + x2i);
      a.setFloat(offa + 2L, x0r - x2r);
      a.setFloat(offa + 3L, x0i - x2i);
      a.setFloat(offa + 4L, x1r - x3i);
      a.setFloat(offa + 5L, x1i + x3r);
      a.setFloat(offa + 6L, x1r + x3i);
      a.setFloat(offa + 7L, x1i - x3r);
   }

   public static void cftf162(float[] a, int offa, float[] w, int startw) {
      float wn4r = w[startw + 1];
      float wk1r = w[startw + 4];
      float wk1i = w[startw + 5];
      float wk3r = w[startw + 6];
      float wk3i = -w[startw + 7];
      float wk2r = w[startw + 8];
      float wk2i = w[startw + 9];
      float x1r = a[offa] - a[offa + 17];
      float x1i = a[offa + 1] + a[offa + 16];
      float x0r = a[offa + 8] - a[offa + 25];
      float x0i = a[offa + 9] + a[offa + 24];
      float x2r = wn4r * (x0r - x0i);
      float x2i = wn4r * (x0i + x0r);
      float y0r = x1r + x2r;
      float y0i = x1i + x2i;
      float y4r = x1r - x2r;
      float y4i = x1i - x2i;
      x1r = a[offa] + a[offa + 17];
      x1i = a[offa + 1] - a[offa + 16];
      x0r = a[offa + 8] + a[offa + 25];
      x0i = a[offa + 9] - a[offa + 24];
      x2r = wn4r * (x0r - x0i);
      x2i = wn4r * (x0i + x0r);
      float y8r = x1r - x2i;
      float y8i = x1i + x2r;
      float y12r = x1r + x2i;
      float y12i = x1i - x2r;
      x0r = a[offa + 2] - a[offa + 19];
      x0i = a[offa + 3] + a[offa + 18];
      x1r = wk1r * x0r - wk1i * x0i;
      x1i = wk1r * x0i + wk1i * x0r;
      x0r = a[offa + 10] - a[offa + 27];
      x0i = a[offa + 11] + a[offa + 26];
      x2r = wk3i * x0r - wk3r * x0i;
      x2i = wk3i * x0i + wk3r * x0r;
      float y1r = x1r + x2r;
      float y1i = x1i + x2i;
      float y5r = x1r - x2r;
      float y5i = x1i - x2i;
      x0r = a[offa + 2] + a[offa + 19];
      x0i = a[offa + 3] - a[offa + 18];
      x1r = wk3r * x0r - wk3i * x0i;
      x1i = wk3r * x0i + wk3i * x0r;
      x0r = a[offa + 10] + a[offa + 27];
      x0i = a[offa + 11] - a[offa + 26];
      x2r = wk1r * x0r + wk1i * x0i;
      x2i = wk1r * x0i - wk1i * x0r;
      float y9r = x1r - x2r;
      float y9i = x1i - x2i;
      float y13r = x1r + x2r;
      float y13i = x1i + x2i;
      x0r = a[offa + 4] - a[offa + 21];
      x0i = a[offa + 5] + a[offa + 20];
      x1r = wk2r * x0r - wk2i * x0i;
      x1i = wk2r * x0i + wk2i * x0r;
      x0r = a[offa + 12] - a[offa + 29];
      x0i = a[offa + 13] + a[offa + 28];
      x2r = wk2i * x0r - wk2r * x0i;
      x2i = wk2i * x0i + wk2r * x0r;
      float y2r = x1r + x2r;
      float y2i = x1i + x2i;
      float y6r = x1r - x2r;
      float y6i = x1i - x2i;
      x0r = a[offa + 4] + a[offa + 21];
      x0i = a[offa + 5] - a[offa + 20];
      x1r = wk2i * x0r - wk2r * x0i;
      x1i = wk2i * x0i + wk2r * x0r;
      x0r = a[offa + 12] + a[offa + 29];
      x0i = a[offa + 13] - a[offa + 28];
      x2r = wk2r * x0r - wk2i * x0i;
      x2i = wk2r * x0i + wk2i * x0r;
      float y10r = x1r - x2r;
      float y10i = x1i - x2i;
      float y14r = x1r + x2r;
      float y14i = x1i + x2i;
      x0r = a[offa + 6] - a[offa + 23];
      x0i = a[offa + 7] + a[offa + 22];
      x1r = wk3r * x0r - wk3i * x0i;
      x1i = wk3r * x0i + wk3i * x0r;
      x0r = a[offa + 14] - a[offa + 31];
      x0i = a[offa + 15] + a[offa + 30];
      x2r = wk1i * x0r - wk1r * x0i;
      x2i = wk1i * x0i + wk1r * x0r;
      float y3r = x1r + x2r;
      float y3i = x1i + x2i;
      float y7r = x1r - x2r;
      float y7i = x1i - x2i;
      x0r = a[offa + 6] + a[offa + 23];
      x0i = a[offa + 7] - a[offa + 22];
      x1r = wk1i * x0r + wk1r * x0i;
      x1i = wk1i * x0i - wk1r * x0r;
      x0r = a[offa + 14] + a[offa + 31];
      x0i = a[offa + 15] - a[offa + 30];
      x2r = wk3i * x0r - wk3r * x0i;
      x2i = wk3i * x0i + wk3r * x0r;
      float y11r = x1r + x2r;
      float y11i = x1i + x2i;
      float y15r = x1r - x2r;
      float y15i = x1i - x2i;
      x1r = y0r + y2r;
      x1i = y0i + y2i;
      x2r = y1r + y3r;
      x2i = y1i + y3i;
      a[offa] = x1r + x2r;
      a[offa + 1] = x1i + x2i;
      a[offa + 2] = x1r - x2r;
      a[offa + 3] = x1i - x2i;
      x1r = y0r - y2r;
      x1i = y0i - y2i;
      x2r = y1r - y3r;
      x2i = y1i - y3i;
      a[offa + 4] = x1r - x2i;
      a[offa + 5] = x1i + x2r;
      a[offa + 6] = x1r + x2i;
      a[offa + 7] = x1i - x2r;
      x1r = y4r - y6i;
      x1i = y4i + y6r;
      x0r = y5r - y7i;
      x0i = y5i + y7r;
      x2r = wn4r * (x0r - x0i);
      x2i = wn4r * (x0i + x0r);
      a[offa + 8] = x1r + x2r;
      a[offa + 9] = x1i + x2i;
      a[offa + 10] = x1r - x2r;
      a[offa + 11] = x1i - x2i;
      x1r = y4r + y6i;
      x1i = y4i - y6r;
      x0r = y5r + y7i;
      x0i = y5i - y7r;
      x2r = wn4r * (x0r - x0i);
      x2i = wn4r * (x0i + x0r);
      a[offa + 12] = x1r - x2i;
      a[offa + 13] = x1i + x2r;
      a[offa + 14] = x1r + x2i;
      a[offa + 15] = x1i - x2r;
      x1r = y8r + y10r;
      x1i = y8i + y10i;
      x2r = y9r - y11r;
      x2i = y9i - y11i;
      a[offa + 16] = x1r + x2r;
      a[offa + 17] = x1i + x2i;
      a[offa + 18] = x1r - x2r;
      a[offa + 19] = x1i - x2i;
      x1r = y8r - y10r;
      x1i = y8i - y10i;
      x2r = y9r + y11r;
      x2i = y9i + y11i;
      a[offa + 20] = x1r - x2i;
      a[offa + 21] = x1i + x2r;
      a[offa + 22] = x1r + x2i;
      a[offa + 23] = x1i - x2r;
      x1r = y12r - y14i;
      x1i = y12i + y14r;
      x0r = y13r + y15i;
      x0i = y13i - y15r;
      x2r = wn4r * (x0r - x0i);
      x2i = wn4r * (x0i + x0r);
      a[offa + 24] = x1r + x2r;
      a[offa + 25] = x1i + x2i;
      a[offa + 26] = x1r - x2r;
      a[offa + 27] = x1i - x2i;
      x1r = y12r + y14i;
      x1i = y12i - y14r;
      x0r = y13r - y15i;
      x0i = y13i + y15r;
      x2r = wn4r * (x0r - x0i);
      x2i = wn4r * (x0i + x0r);
      a[offa + 28] = x1r - x2i;
      a[offa + 29] = x1i + x2r;
      a[offa + 30] = x1r + x2i;
      a[offa + 31] = x1i - x2r;
   }

   public static void cftf162(FloatLargeArray a, long offa, FloatLargeArray w, long startw) {
      float wn4r = w.getFloat(startw + 1L);
      float wk1r = w.getFloat(startw + 4L);
      float wk1i = w.getFloat(startw + 5L);
      float wk3r = w.getFloat(startw + 6L);
      float wk3i = -w.getFloat(startw + 7L);
      float wk2r = w.getFloat(startw + 8L);
      float wk2i = w.getFloat(startw + 9L);
      float x1r = a.getFloat(offa) - a.getFloat(offa + 17L);
      float x1i = a.getFloat(offa + 1L) + a.getFloat(offa + 16L);
      float x0r = a.getFloat(offa + 8L) - a.getFloat(offa + 25L);
      float x0i = a.getFloat(offa + 9L) + a.getFloat(offa + 24L);
      float x2r = wn4r * (x0r - x0i);
      float x2i = wn4r * (x0i + x0r);
      float y0r = x1r + x2r;
      float y0i = x1i + x2i;
      float y4r = x1r - x2r;
      float y4i = x1i - x2i;
      x1r = a.getFloat(offa) + a.getFloat(offa + 17L);
      x1i = a.getFloat(offa + 1L) - a.getFloat(offa + 16L);
      x0r = a.getFloat(offa + 8L) + a.getFloat(offa + 25L);
      x0i = a.getFloat(offa + 9L) - a.getFloat(offa + 24L);
      x2r = wn4r * (x0r - x0i);
      x2i = wn4r * (x0i + x0r);
      float y8r = x1r - x2i;
      float y8i = x1i + x2r;
      float y12r = x1r + x2i;
      float y12i = x1i - x2r;
      x0r = a.getFloat(offa + 2L) - a.getFloat(offa + 19L);
      x0i = a.getFloat(offa + 3L) + a.getFloat(offa + 18L);
      x1r = wk1r * x0r - wk1i * x0i;
      x1i = wk1r * x0i + wk1i * x0r;
      x0r = a.getFloat(offa + 10L) - a.getFloat(offa + 27L);
      x0i = a.getFloat(offa + 11L) + a.getFloat(offa + 26L);
      x2r = wk3i * x0r - wk3r * x0i;
      x2i = wk3i * x0i + wk3r * x0r;
      float y1r = x1r + x2r;
      float y1i = x1i + x2i;
      float y5r = x1r - x2r;
      float y5i = x1i - x2i;
      x0r = a.getFloat(offa + 2L) + a.getFloat(offa + 19L);
      x0i = a.getFloat(offa + 3L) - a.getFloat(offa + 18L);
      x1r = wk3r * x0r - wk3i * x0i;
      x1i = wk3r * x0i + wk3i * x0r;
      x0r = a.getFloat(offa + 10L) + a.getFloat(offa + 27L);
      x0i = a.getFloat(offa + 11L) - a.getFloat(offa + 26L);
      x2r = wk1r * x0r + wk1i * x0i;
      x2i = wk1r * x0i - wk1i * x0r;
      float y9r = x1r - x2r;
      float y9i = x1i - x2i;
      float y13r = x1r + x2r;
      float y13i = x1i + x2i;
      x0r = a.getFloat(offa + 4L) - a.getFloat(offa + 21L);
      x0i = a.getFloat(offa + 5L) + a.getFloat(offa + 20L);
      x1r = wk2r * x0r - wk2i * x0i;
      x1i = wk2r * x0i + wk2i * x0r;
      x0r = a.getFloat(offa + 12L) - a.getFloat(offa + 29L);
      x0i = a.getFloat(offa + 13L) + a.getFloat(offa + 28L);
      x2r = wk2i * x0r - wk2r * x0i;
      x2i = wk2i * x0i + wk2r * x0r;
      float y2r = x1r + x2r;
      float y2i = x1i + x2i;
      float y6r = x1r - x2r;
      float y6i = x1i - x2i;
      x0r = a.getFloat(offa + 4L) + a.getFloat(offa + 21L);
      x0i = a.getFloat(offa + 5L) - a.getFloat(offa + 20L);
      x1r = wk2i * x0r - wk2r * x0i;
      x1i = wk2i * x0i + wk2r * x0r;
      x0r = a.getFloat(offa + 12L) + a.getFloat(offa + 29L);
      x0i = a.getFloat(offa + 13L) - a.getFloat(offa + 28L);
      x2r = wk2r * x0r - wk2i * x0i;
      x2i = wk2r * x0i + wk2i * x0r;
      float y10r = x1r - x2r;
      float y10i = x1i - x2i;
      float y14r = x1r + x2r;
      float y14i = x1i + x2i;
      x0r = a.getFloat(offa + 6L) - a.getFloat(offa + 23L);
      x0i = a.getFloat(offa + 7L) + a.getFloat(offa + 22L);
      x1r = wk3r * x0r - wk3i * x0i;
      x1i = wk3r * x0i + wk3i * x0r;
      x0r = a.getFloat(offa + 14L) - a.getFloat(offa + 31L);
      x0i = a.getFloat(offa + 15L) + a.getFloat(offa + 30L);
      x2r = wk1i * x0r - wk1r * x0i;
      x2i = wk1i * x0i + wk1r * x0r;
      float y3r = x1r + x2r;
      float y3i = x1i + x2i;
      float y7r = x1r - x2r;
      float y7i = x1i - x2i;
      x0r = a.getFloat(offa + 6L) + a.getFloat(offa + 23L);
      x0i = a.getFloat(offa + 7L) - a.getFloat(offa + 22L);
      x1r = wk1i * x0r + wk1r * x0i;
      x1i = wk1i * x0i - wk1r * x0r;
      x0r = a.getFloat(offa + 14L) + a.getFloat(offa + 31L);
      x0i = a.getFloat(offa + 15L) - a.getFloat(offa + 30L);
      x2r = wk3i * x0r - wk3r * x0i;
      x2i = wk3i * x0i + wk3r * x0r;
      float y11r = x1r + x2r;
      float y11i = x1i + x2i;
      float y15r = x1r - x2r;
      float y15i = x1i - x2i;
      x1r = y0r + y2r;
      x1i = y0i + y2i;
      x2r = y1r + y3r;
      x2i = y1i + y3i;
      a.setFloat(offa, x1r + x2r);
      a.setFloat(offa + 1L, x1i + x2i);
      a.setFloat(offa + 2L, x1r - x2r);
      a.setFloat(offa + 3L, x1i - x2i);
      x1r = y0r - y2r;
      x1i = y0i - y2i;
      x2r = y1r - y3r;
      x2i = y1i - y3i;
      a.setFloat(offa + 4L, x1r - x2i);
      a.setFloat(offa + 5L, x1i + x2r);
      a.setFloat(offa + 6L, x1r + x2i);
      a.setFloat(offa + 7L, x1i - x2r);
      x1r = y4r - y6i;
      x1i = y4i + y6r;
      x0r = y5r - y7i;
      x0i = y5i + y7r;
      x2r = wn4r * (x0r - x0i);
      x2i = wn4r * (x0i + x0r);
      a.setFloat(offa + 8L, x1r + x2r);
      a.setFloat(offa + 9L, x1i + x2i);
      a.setFloat(offa + 10L, x1r - x2r);
      a.setFloat(offa + 11L, x1i - x2i);
      x1r = y4r + y6i;
      x1i = y4i - y6r;
      x0r = y5r + y7i;
      x0i = y5i - y7r;
      x2r = wn4r * (x0r - x0i);
      x2i = wn4r * (x0i + x0r);
      a.setFloat(offa + 12L, x1r - x2i);
      a.setFloat(offa + 13L, x1i + x2r);
      a.setFloat(offa + 14L, x1r + x2i);
      a.setFloat(offa + 15L, x1i - x2r);
      x1r = y8r + y10r;
      x1i = y8i + y10i;
      x2r = y9r - y11r;
      x2i = y9i - y11i;
      a.setFloat(offa + 16L, x1r + x2r);
      a.setFloat(offa + 17L, x1i + x2i);
      a.setFloat(offa + 18L, x1r - x2r);
      a.setFloat(offa + 19L, x1i - x2i);
      x1r = y8r - y10r;
      x1i = y8i - y10i;
      x2r = y9r + y11r;
      x2i = y9i + y11i;
      a.setFloat(offa + 20L, x1r - x2i);
      a.setFloat(offa + 21L, x1i + x2r);
      a.setFloat(offa + 22L, x1r + x2i);
      a.setFloat(offa + 23L, x1i - x2r);
      x1r = y12r - y14i;
      x1i = y12i + y14r;
      x0r = y13r + y15i;
      x0i = y13i - y15r;
      x2r = wn4r * (x0r - x0i);
      x2i = wn4r * (x0i + x0r);
      a.setFloat(offa + 24L, x1r + x2r);
      a.setFloat(offa + 25L, x1i + x2i);
      a.setFloat(offa + 26L, x1r - x2r);
      a.setFloat(offa + 27L, x1i - x2i);
      x1r = y12r + y14i;
      x1i = y12i - y14r;
      x0r = y13r - y15i;
      x0i = y13i + y15r;
      x2r = wn4r * (x0r - x0i);
      x2i = wn4r * (x0i + x0r);
      a.setFloat(offa + 28L, x1r - x2i);
      a.setFloat(offa + 29L, x1i + x2r);
      a.setFloat(offa + 30L, x1r + x2i);
      a.setFloat(offa + 31L, x1i - x2r);
   }

   public static void cftf081(float[] a, int offa, float[] w, int startw) {
      float wn4r = w[startw + 1];
      float x0r = a[offa] + a[offa + 8];
      float x0i = a[offa + 1] + a[offa + 9];
      float x1r = a[offa] - a[offa + 8];
      float x1i = a[offa + 1] - a[offa + 9];
      float x2r = a[offa + 4] + a[offa + 12];
      float x2i = a[offa + 5] + a[offa + 13];
      float x3r = a[offa + 4] - a[offa + 12];
      float x3i = a[offa + 5] - a[offa + 13];
      float y0r = x0r + x2r;
      float y0i = x0i + x2i;
      float y2r = x0r - x2r;
      float y2i = x0i - x2i;
      float y1r = x1r - x3i;
      float y1i = x1i + x3r;
      float y3r = x1r + x3i;
      float y3i = x1i - x3r;
      x0r = a[offa + 2] + a[offa + 10];
      x0i = a[offa + 3] + a[offa + 11];
      x1r = a[offa + 2] - a[offa + 10];
      x1i = a[offa + 3] - a[offa + 11];
      x2r = a[offa + 6] + a[offa + 14];
      x2i = a[offa + 7] + a[offa + 15];
      x3r = a[offa + 6] - a[offa + 14];
      x3i = a[offa + 7] - a[offa + 15];
      float y4r = x0r + x2r;
      float y4i = x0i + x2i;
      float y6r = x0r - x2r;
      float y6i = x0i - x2i;
      x0r = x1r - x3i;
      x0i = x1i + x3r;
      x2r = x1r + x3i;
      x2i = x1i - x3r;
      float y5r = wn4r * (x0r - x0i);
      float y5i = wn4r * (x0r + x0i);
      float y7r = wn4r * (x2r - x2i);
      float y7i = wn4r * (x2r + x2i);
      a[offa + 8] = y1r + y5r;
      a[offa + 9] = y1i + y5i;
      a[offa + 10] = y1r - y5r;
      a[offa + 11] = y1i - y5i;
      a[offa + 12] = y3r - y7i;
      a[offa + 13] = y3i + y7r;
      a[offa + 14] = y3r + y7i;
      a[offa + 15] = y3i - y7r;
      a[offa] = y0r + y4r;
      a[offa + 1] = y0i + y4i;
      a[offa + 2] = y0r - y4r;
      a[offa + 3] = y0i - y4i;
      a[offa + 4] = y2r - y6i;
      a[offa + 5] = y2i + y6r;
      a[offa + 6] = y2r + y6i;
      a[offa + 7] = y2i - y6r;
   }

   public static void cftf081(FloatLargeArray a, long offa, FloatLargeArray w, long startw) {
      float wn4r = w.getFloat(startw + 1L);
      float x0r = a.getFloat(offa) + a.getFloat(offa + 8L);
      float x0i = a.getFloat(offa + 1L) + a.getFloat(offa + 9L);
      float x1r = a.getFloat(offa) - a.getFloat(offa + 8L);
      float x1i = a.getFloat(offa + 1L) - a.getFloat(offa + 9L);
      float x2r = a.getFloat(offa + 4L) + a.getFloat(offa + 12L);
      float x2i = a.getFloat(offa + 5L) + a.getFloat(offa + 13L);
      float x3r = a.getFloat(offa + 4L) - a.getFloat(offa + 12L);
      float x3i = a.getFloat(offa + 5L) - a.getFloat(offa + 13L);
      float y0r = x0r + x2r;
      float y0i = x0i + x2i;
      float y2r = x0r - x2r;
      float y2i = x0i - x2i;
      float y1r = x1r - x3i;
      float y1i = x1i + x3r;
      float y3r = x1r + x3i;
      float y3i = x1i - x3r;
      x0r = a.getFloat(offa + 2L) + a.getFloat(offa + 10L);
      x0i = a.getFloat(offa + 3L) + a.getFloat(offa + 11L);
      x1r = a.getFloat(offa + 2L) - a.getFloat(offa + 10L);
      x1i = a.getFloat(offa + 3L) - a.getFloat(offa + 11L);
      x2r = a.getFloat(offa + 6L) + a.getFloat(offa + 14L);
      x2i = a.getFloat(offa + 7L) + a.getFloat(offa + 15L);
      x3r = a.getFloat(offa + 6L) - a.getFloat(offa + 14L);
      x3i = a.getFloat(offa + 7L) - a.getFloat(offa + 15L);
      float y4r = x0r + x2r;
      float y4i = x0i + x2i;
      float y6r = x0r - x2r;
      float y6i = x0i - x2i;
      x0r = x1r - x3i;
      x0i = x1i + x3r;
      x2r = x1r + x3i;
      x2i = x1i - x3r;
      float y5r = wn4r * (x0r - x0i);
      float y5i = wn4r * (x0r + x0i);
      float y7r = wn4r * (x2r - x2i);
      float y7i = wn4r * (x2r + x2i);
      a.setFloat(offa + 8L, y1r + y5r);
      a.setFloat(offa + 9L, y1i + y5i);
      a.setFloat(offa + 10L, y1r - y5r);
      a.setFloat(offa + 11L, y1i - y5i);
      a.setFloat(offa + 12L, y3r - y7i);
      a.setFloat(offa + 13L, y3i + y7r);
      a.setFloat(offa + 14L, y3r + y7i);
      a.setFloat(offa + 15L, y3i - y7r);
      a.setFloat(offa, y0r + y4r);
      a.setFloat(offa + 1L, y0i + y4i);
      a.setFloat(offa + 2L, y0r - y4r);
      a.setFloat(offa + 3L, y0i - y4i);
      a.setFloat(offa + 4L, y2r - y6i);
      a.setFloat(offa + 5L, y2i + y6r);
      a.setFloat(offa + 6L, y2r + y6i);
      a.setFloat(offa + 7L, y2i - y6r);
   }

   public static void cftf082(float[] a, int offa, float[] w, int startw) {
      float wn4r = w[startw + 1];
      float wk1r = w[startw + 2];
      float wk1i = w[startw + 3];
      float y0r = a[offa] - a[offa + 9];
      float y0i = a[offa + 1] + a[offa + 8];
      float y1r = a[offa] + a[offa + 9];
      float y1i = a[offa + 1] - a[offa + 8];
      float x0r = a[offa + 4] - a[offa + 13];
      float x0i = a[offa + 5] + a[offa + 12];
      float y2r = wn4r * (x0r - x0i);
      float y2i = wn4r * (x0i + x0r);
      x0r = a[offa + 4] + a[offa + 13];
      x0i = a[offa + 5] - a[offa + 12];
      float y3r = wn4r * (x0r - x0i);
      float y3i = wn4r * (x0i + x0r);
      x0r = a[offa + 2] - a[offa + 11];
      x0i = a[offa + 3] + a[offa + 10];
      float y4r = wk1r * x0r - wk1i * x0i;
      float y4i = wk1r * x0i + wk1i * x0r;
      x0r = a[offa + 2] + a[offa + 11];
      x0i = a[offa + 3] - a[offa + 10];
      float y5r = wk1i * x0r - wk1r * x0i;
      float y5i = wk1i * x0i + wk1r * x0r;
      x0r = a[offa + 6] - a[offa + 15];
      x0i = a[offa + 7] + a[offa + 14];
      float y6r = wk1i * x0r - wk1r * x0i;
      float y6i = wk1i * x0i + wk1r * x0r;
      x0r = a[offa + 6] + a[offa + 15];
      x0i = a[offa + 7] - a[offa + 14];
      float y7r = wk1r * x0r - wk1i * x0i;
      float y7i = wk1r * x0i + wk1i * x0r;
      x0r = y0r + y2r;
      x0i = y0i + y2i;
      float x1r = y4r + y6r;
      float x1i = y4i + y6i;
      a[offa] = x0r + x1r;
      a[offa + 1] = x0i + x1i;
      a[offa + 2] = x0r - x1r;
      a[offa + 3] = x0i - x1i;
      x0r = y0r - y2r;
      x0i = y0i - y2i;
      x1r = y4r - y6r;
      x1i = y4i - y6i;
      a[offa + 4] = x0r - x1i;
      a[offa + 5] = x0i + x1r;
      a[offa + 6] = x0r + x1i;
      a[offa + 7] = x0i - x1r;
      x0r = y1r - y3i;
      x0i = y1i + y3r;
      x1r = y5r - y7r;
      x1i = y5i - y7i;
      a[offa + 8] = x0r + x1r;
      a[offa + 9] = x0i + x1i;
      a[offa + 10] = x0r - x1r;
      a[offa + 11] = x0i - x1i;
      x0r = y1r + y3i;
      x0i = y1i - y3r;
      x1r = y5r + y7r;
      x1i = y5i + y7i;
      a[offa + 12] = x0r - x1i;
      a[offa + 13] = x0i + x1r;
      a[offa + 14] = x0r + x1i;
      a[offa + 15] = x0i - x1r;
   }

   public static void cftf082(FloatLargeArray a, long offa, FloatLargeArray w, long startw) {
      float wn4r = w.getFloat(startw + 1L);
      float wk1r = w.getFloat(startw + 2L);
      float wk1i = w.getFloat(startw + 3L);
      float y0r = a.getFloat(offa) - a.getFloat(offa + 9L);
      float y0i = a.getFloat(offa + 1L) + a.getFloat(offa + 8L);
      float y1r = a.getFloat(offa) + a.getFloat(offa + 9L);
      float y1i = a.getFloat(offa + 1L) - a.getFloat(offa + 8L);
      float x0r = a.getFloat(offa + 4L) - a.getFloat(offa + 13L);
      float x0i = a.getFloat(offa + 5L) + a.getFloat(offa + 12L);
      float y2r = wn4r * (x0r - x0i);
      float y2i = wn4r * (x0i + x0r);
      x0r = a.getFloat(offa + 4L) + a.getFloat(offa + 13L);
      x0i = a.getFloat(offa + 5L) - a.getFloat(offa + 12L);
      float y3r = wn4r * (x0r - x0i);
      float y3i = wn4r * (x0i + x0r);
      x0r = a.getFloat(offa + 2L) - a.getFloat(offa + 11L);
      x0i = a.getFloat(offa + 3L) + a.getFloat(offa + 10L);
      float y4r = wk1r * x0r - wk1i * x0i;
      float y4i = wk1r * x0i + wk1i * x0r;
      x0r = a.getFloat(offa + 2L) + a.getFloat(offa + 11L);
      x0i = a.getFloat(offa + 3L) - a.getFloat(offa + 10L);
      float y5r = wk1i * x0r - wk1r * x0i;
      float y5i = wk1i * x0i + wk1r * x0r;
      x0r = a.getFloat(offa + 6L) - a.getFloat(offa + 15L);
      x0i = a.getFloat(offa + 7L) + a.getFloat(offa + 14L);
      float y6r = wk1i * x0r - wk1r * x0i;
      float y6i = wk1i * x0i + wk1r * x0r;
      x0r = a.getFloat(offa + 6L) + a.getFloat(offa + 15L);
      x0i = a.getFloat(offa + 7L) - a.getFloat(offa + 14L);
      float y7r = wk1r * x0r - wk1i * x0i;
      float y7i = wk1r * x0i + wk1i * x0r;
      x0r = y0r + y2r;
      x0i = y0i + y2i;
      float x1r = y4r + y6r;
      float x1i = y4i + y6i;
      a.setFloat(offa, x0r + x1r);
      a.setFloat(offa + 1L, x0i + x1i);
      a.setFloat(offa + 2L, x0r - x1r);
      a.setFloat(offa + 3L, x0i - x1i);
      x0r = y0r - y2r;
      x0i = y0i - y2i;
      x1r = y4r - y6r;
      x1i = y4i - y6i;
      a.setFloat(offa + 4L, x0r - x1i);
      a.setFloat(offa + 5L, x0i + x1r);
      a.setFloat(offa + 6L, x0r + x1i);
      a.setFloat(offa + 7L, x0i - x1r);
      x0r = y1r - y3i;
      x0i = y1i + y3r;
      x1r = y5r - y7r;
      x1i = y5i - y7i;
      a.setFloat(offa + 8L, x0r + x1r);
      a.setFloat(offa + 9L, x0i + x1i);
      a.setFloat(offa + 10L, x0r - x1r);
      a.setFloat(offa + 11L, x0i - x1i);
      x0r = y1r + y3i;
      x0i = y1i - y3r;
      x1r = y5r + y7r;
      x1i = y5i + y7i;
      a.setFloat(offa + 12L, x0r - x1i);
      a.setFloat(offa + 13L, x0i + x1r);
      a.setFloat(offa + 14L, x0r + x1i);
      a.setFloat(offa + 15L, x0i - x1r);
   }

   public static void cftf040(float[] a, int offa) {
      float x0r = a[offa] + a[offa + 4];
      float x0i = a[offa + 1] + a[offa + 5];
      float x1r = a[offa] - a[offa + 4];
      float x1i = a[offa + 1] - a[offa + 5];
      float x2r = a[offa + 2] + a[offa + 6];
      float x2i = a[offa + 3] + a[offa + 7];
      float x3r = a[offa + 2] - a[offa + 6];
      float x3i = a[offa + 3] - a[offa + 7];
      a[offa] = x0r + x2r;
      a[offa + 1] = x0i + x2i;
      a[offa + 2] = x1r - x3i;
      a[offa + 3] = x1i + x3r;
      a[offa + 4] = x0r - x2r;
      a[offa + 5] = x0i - x2i;
      a[offa + 6] = x1r + x3i;
      a[offa + 7] = x1i - x3r;
   }

   public static void cftf040(FloatLargeArray a, long offa) {
      float x0r = a.getFloat(offa) + a.getFloat(offa + 4L);
      float x0i = a.getFloat(offa + 1L) + a.getFloat(offa + 5L);
      float x1r = a.getFloat(offa) - a.getFloat(offa + 4L);
      float x1i = a.getFloat(offa + 1L) - a.getFloat(offa + 5L);
      float x2r = a.getFloat(offa + 2L) + a.getFloat(offa + 6L);
      float x2i = a.getFloat(offa + 3L) + a.getFloat(offa + 7L);
      float x3r = a.getFloat(offa + 2L) - a.getFloat(offa + 6L);
      float x3i = a.getFloat(offa + 3L) - a.getFloat(offa + 7L);
      a.setFloat(offa, x0r + x2r);
      a.setFloat(offa + 1L, x0i + x2i);
      a.setFloat(offa + 2L, x1r - x3i);
      a.setFloat(offa + 3L, x1i + x3r);
      a.setFloat(offa + 4L, x0r - x2r);
      a.setFloat(offa + 5L, x0i - x2i);
      a.setFloat(offa + 6L, x1r + x3i);
      a.setFloat(offa + 7L, x1i - x3r);
   }

   public static void cftb040(float[] a, int offa) {
      float x0r = a[offa] + a[offa + 4];
      float x0i = a[offa + 1] + a[offa + 5];
      float x1r = a[offa] - a[offa + 4];
      float x1i = a[offa + 1] - a[offa + 5];
      float x2r = a[offa + 2] + a[offa + 6];
      float x2i = a[offa + 3] + a[offa + 7];
      float x3r = a[offa + 2] - a[offa + 6];
      float x3i = a[offa + 3] - a[offa + 7];
      a[offa] = x0r + x2r;
      a[offa + 1] = x0i + x2i;
      a[offa + 2] = x1r + x3i;
      a[offa + 3] = x1i - x3r;
      a[offa + 4] = x0r - x2r;
      a[offa + 5] = x0i - x2i;
      a[offa + 6] = x1r - x3i;
      a[offa + 7] = x1i + x3r;
   }

   public static void cftb040(FloatLargeArray a, long offa) {
      float x0r = a.getFloat(offa) + a.getFloat(offa + 4L);
      float x0i = a.getFloat(offa + 1L) + a.getFloat(offa + 5L);
      float x1r = a.getFloat(offa) - a.getFloat(offa + 4L);
      float x1i = a.getFloat(offa + 1L) - a.getFloat(offa + 5L);
      float x2r = a.getFloat(offa + 2L) + a.getFloat(offa + 6L);
      float x2i = a.getFloat(offa + 3L) + a.getFloat(offa + 7L);
      float x3r = a.getFloat(offa + 2L) - a.getFloat(offa + 6L);
      float x3i = a.getFloat(offa + 3L) - a.getFloat(offa + 7L);
      a.setFloat(offa, x0r + x2r);
      a.setFloat(offa + 1L, x0i + x2i);
      a.setFloat(offa + 2L, x1r + x3i);
      a.setFloat(offa + 3L, x1i - x3r);
      a.setFloat(offa + 4L, x0r - x2r);
      a.setFloat(offa + 5L, x0i - x2i);
      a.setFloat(offa + 6L, x1r - x3i);
      a.setFloat(offa + 7L, x1i + x3r);
   }

   public static void cftx020(float[] a, int offa) {
      float x0r = a[offa] - a[offa + 2];
      float x0i = -a[offa + 1] + a[offa + 3];
      a[offa] += a[offa + 2];
      a[offa + 1] += a[offa + 3];
      a[offa + 2] = x0r;
      a[offa + 3] = x0i;
   }

   public static void cftx020(FloatLargeArray a, long offa) {
      float x0r = a.getFloat(offa) - a.getFloat(offa + 2L);
      float x0i = -a.getFloat(offa + 1L) + a.getFloat(offa + 3L);
      a.setFloat(offa, a.getFloat(offa) + a.getFloat(offa + 2L));
      a.setFloat(offa + 1L, a.getFloat(offa + 1L) + a.getFloat(offa + 3L));
      a.setFloat(offa + 2L, x0r);
      a.setFloat(offa + 3L, x0i);
   }

   public static void cftxb020(float[] a, int offa) {
      float x0r = a[offa] - a[offa + 2];
      float x0i = a[offa + 1] - a[offa + 3];
      a[offa] += a[offa + 2];
      a[offa + 1] += a[offa + 3];
      a[offa + 2] = x0r;
      a[offa + 3] = x0i;
   }

   public static void cftxb020(FloatLargeArray a, long offa) {
      float x0r = a.getFloat(offa) - a.getFloat(offa + 2L);
      float x0i = a.getFloat(offa + 1L) - a.getFloat(offa + 3L);
      a.setFloat(offa, a.getFloat(offa) + a.getFloat(offa + 2L));
      a.setFloat(offa + 1L, a.getFloat(offa + 1L) + a.getFloat(offa + 3L));
      a.setFloat(offa + 2L, x0r);
      a.setFloat(offa + 3L, x0i);
   }

   public static void cftxc020(float[] a, int offa) {
      float x0r = a[offa] - a[offa + 2];
      float x0i = a[offa + 1] + a[offa + 3];
      a[offa] += a[offa + 2];
      a[offa + 1] -= a[offa + 3];
      a[offa + 2] = x0r;
      a[offa + 3] = x0i;
   }

   public static void cftxc020(FloatLargeArray a, long offa) {
      float x0r = a.getFloat(offa) - a.getFloat(offa + 2L);
      float x0i = a.getFloat(offa + 1L) + a.getFloat(offa + 3L);
      a.setFloat(offa, a.getFloat(offa) + a.getFloat(offa + 2L));
      a.setFloat(offa + 1L, a.getFloat(offa + 1L) - a.getFloat(offa + 3L));
      a.setFloat(offa + 2L, x0r);
      a.setFloat(offa + 3L, x0i);
   }

   public static void rftfsub(int n, float[] a, int offa, int nc, float[] c, int startc) {
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
         a[idx1 + 1] = yi - a[idx1 + 1];
         a[idx2] += yr;
         a[idx2 + 1] = yi - a[idx2 + 1];
      }

      a[offa + m + 1] = -a[offa + m + 1];
   }

   public static void rftfsub(long n, FloatLargeArray a, long offa, long nc, FloatLargeArray c, long startc) {
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
         a.setFloat(idx1 + 1L, yi - a.getFloat(idx1 + 1L));
         a.setFloat(idx2, a.getFloat(idx2) + yr);
         a.setFloat(idx2 + 1L, yi - a.getFloat(idx2 + 1L));
      }

      a.setFloat(offa + m + 1L, -a.getFloat(offa + m + 1L));
   }

   public static void rftbsub(int n, float[] a, int offa, int nc, float[] c, int startc) {
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

   public static void rftbsub(long n, FloatLargeArray a, long offa, long nc, FloatLargeArray c, long startc) {
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

   public static void dctsub(int n, float[] a, int offa, int nc, float[] c, int startc) {
      int m = n >> 1;
      int ks = nc / n;
      int kk = 0;

      for(int j = 1; j < m; ++j) {
         int k = n - j;
         kk += ks;
         int idx0 = startc + kk;
         int idx1 = offa + j;
         int idx2 = offa + k;
         float wkr = c[idx0] - c[startc + nc - kk];
         float wki = c[idx0] + c[startc + nc - kk];
         float xr = wki * a[idx1] - wkr * a[idx2];
         a[idx1] = wkr * a[idx1] + wki * a[idx2];
         a[idx2] = xr;
      }

      a[offa + m] *= c[startc];
   }

   public static void dctsub(long n, FloatLargeArray a, long offa, long nc, FloatLargeArray c, long startc) {
      long m = n >> 1;
      long ks = nc / n;
      long kk = 0L;

      for(long j = 1L; j < m; ++j) {
         long k = n - j;
         kk += ks;
         long idx0 = startc + kk;
         long idx1 = offa + j;
         long idx2 = offa + k;
         float wkr = c.getFloat(idx0) - c.getFloat(startc + nc - kk);
         float wki = c.getFloat(idx0) + c.getFloat(startc + nc - kk);
         float xr = wki * a.getFloat(idx1) - wkr * a.getFloat(idx2);
         a.setFloat(idx1, wkr * a.getFloat(idx1) + wki * a.getFloat(idx2));
         a.setFloat(idx2, xr);
      }

      a.setFloat(offa + m, a.getFloat(offa + m) * c.getFloat(startc));
   }

   public static void scale(int n, final double m, final double[] a, int offa, boolean complex) {
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      int n2;
      if (complex) {
         n2 = 2 * n;
      } else {
         n2 = n;
      }

      if (nthreads > 1 && (long)n2 > getThreadsBeginN_1D_FFT_2Threads()) {
         nthreads = 2;
         int k = n2 / nthreads;
         Future<?>[] futures = new Future[nthreads];

         for(int i = 0; i < nthreads; ++i) {
            final int firstIdx = offa + i * k;
            final int lastIdx = i == nthreads - 1 ? offa + n2 : firstIdx + k;
            futures[i] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int i = firstIdx; i < lastIdx; ++i) {
                     double[] var10000 = a;
                     var10000[i] *= m;
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(CommonUtils.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(CommonUtils.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         int lastIdx = offa + n2;

         for(int i = offa; i < lastIdx; ++i) {
            a[i] *= m;
         }
      }

   }

   public static void scale(long nl, final double m, final DoubleLargeArray a, long offa, boolean complex) {
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      long n2;
      if (complex) {
         n2 = 2L * nl;
      } else {
         n2 = nl;
      }

      if (nthreads > 1 && n2 >= getThreadsBeginN_1D_FFT_2Threads()) {
         long k = n2 / (long)nthreads;
         Future<?>[] futures = new Future[nthreads];

         for(int i = 0; i < nthreads; ++i) {
            final long firstIdx = offa + (long)i * k;
            final long lastIdx = i == nthreads - 1 ? offa + n2 : firstIdx + k;
            futures[i] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(long i = firstIdx; i < lastIdx; ++i) {
                     a.setDouble(i, a.getDouble(i) * m);
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(CommonUtils.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(CommonUtils.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         for(long i = offa; i < offa + n2; ++i) {
            a.setDouble(i, a.getDouble(i) * m);
         }
      }

   }

   public static void scale(int n, final float m, final float[] a, int offa, boolean complex) {
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      int n2;
      if (complex) {
         n2 = 2 * n;
      } else {
         n2 = n;
      }

      if (nthreads > 1 && (long)n2 > getThreadsBeginN_1D_FFT_2Threads()) {
         nthreads = 2;
         int k = n2 / nthreads;
         Future<?>[] futures = new Future[nthreads];

         for(int i = 0; i < nthreads; ++i) {
            final int firstIdx = offa + i * k;
            final int lastIdx = i == nthreads - 1 ? offa + n2 : firstIdx + k;
            futures[i] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int i = firstIdx; i < lastIdx; ++i) {
                     float[] var10000 = a;
                     var10000[i] *= m;
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(CommonUtils.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(CommonUtils.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         int lastIdx = offa + n2;

         for(int i = offa; i < lastIdx; ++i) {
            a[i] *= m;
         }
      }

   }

   public static void scale(long nl, final float m, final FloatLargeArray a, long offa, boolean complex) {
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      long n2;
      if (complex) {
         n2 = 2L * nl;
      } else {
         n2 = nl;
      }

      if (nthreads > 1 && n2 >= getThreadsBeginN_1D_FFT_2Threads()) {
         long k = n2 / (long)nthreads;
         Future<?>[] futures = new Future[nthreads];

         for(int i = 0; i < nthreads; ++i) {
            final long firstIdx = offa + (long)i * k;
            final long lastIdx = i == nthreads - 1 ? offa + n2 : firstIdx + k;
            futures[i] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(long i = firstIdx; i < lastIdx; ++i) {
                     a.setDouble(i, a.getDouble(i) * (double)m);
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(CommonUtils.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(CommonUtils.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         for(long i = offa; i < offa + n2; ++i) {
            a.setDouble(i, a.getDouble(i) * (double)m);
         }
      }

   }
}
