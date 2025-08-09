package org.jtransforms.fft;

import org.apache.commons.math3.util.FastMath;
import pl.edu.icm.jlargearrays.DoubleLargeArray;
import pl.edu.icm.jlargearrays.FloatLargeArray;

public class RealFFTUtils_3D {
   private static final int ONE = 1;
   private static final int TWO = 2;
   private static final int ZERO = 0;
   private static final long ONEL = 1L;
   private static final long TWOL = 2L;
   private static final long ZEROL = 0L;
   private final int columns;
   private final long columnsl;
   private final int rows;
   private final long rowsl;
   private final int rowStride;
   private final long rowStridel;
   private final int slices;
   private final long slicesl;
   private final int sliceStride;
   private final long sliceStridel;

   public RealFFTUtils_3D(long slices, long rows, long columns) {
      this.slices = (int)slices;
      this.rows = (int)rows;
      this.columns = (int)columns;
      this.rowStride = (int)columns;
      this.sliceStride = (int)rows * this.rowStride;
      this.slicesl = slices;
      this.rowsl = rows;
      this.columnsl = columns;
      this.rowStridel = columns;
      this.sliceStridel = rows * this.rowStridel;
   }

   public int getIndex(int s, int r, int c) {
      int cmod2 = c & 1;
      int rmul2 = r << 1;
      int smul2 = s << 1;
      int ss = s == 0 ? 0 : this.slices - s;
      int rr = r == 0 ? 0 : this.rows - r;
      if (c <= 1) {
         if (r == 0) {
            if (s == 0) {
               return c == 0 ? 0 : Integer.MIN_VALUE;
            } else if (smul2 < this.slices) {
               return s * this.sliceStride + c;
            } else if (smul2 > this.slices) {
               int index = ss * this.sliceStride;
               return cmod2 == 0 ? index : -(index + 1);
            } else {
               return cmod2 == 0 ? s * this.sliceStride : Integer.MIN_VALUE;
            }
         } else if (rmul2 < this.rows) {
            return s * this.sliceStride + r * this.rowStride + c;
         } else if (rmul2 > this.rows) {
            int index = ss * this.sliceStride + rr * this.rowStride;
            return cmod2 == 0 ? index : -(index + 1);
         } else if (s == 0) {
            return cmod2 == 0 ? r * this.rowStride : Integer.MIN_VALUE;
         } else if (smul2 < this.slices) {
            return s * this.sliceStride + r * this.rowStride + c;
         } else if (smul2 > this.slices) {
            int index = ss * this.sliceStride + r * this.rowStride;
            return cmod2 == 0 ? index : -(index + 1);
         } else {
            int index = s * this.sliceStride + r * this.rowStride;
            return cmod2 == 0 ? index : Integer.MIN_VALUE;
         }
      } else if (c < this.columns) {
         return s * this.sliceStride + r * this.rowStride + c;
      } else if (c > this.columns + 1) {
         int cc = (this.columns << 1) - c;
         int index = ss * this.sliceStride + rr * this.rowStride + cc;
         return cmod2 == 0 ? index : -(index + 2);
      } else if (r == 0) {
         if (s == 0) {
            return cmod2 == 0 ? 1 : Integer.MIN_VALUE;
         } else if (smul2 < this.slices) {
            int index = ss * this.sliceStride;
            return cmod2 == 0 ? index + 1 : -index;
         } else if (smul2 > this.slices) {
            int index = s * this.sliceStride;
            return cmod2 == 0 ? index + 1 : index;
         } else {
            int index = s * this.sliceStride;
            return cmod2 == 0 ? index + 1 : Integer.MIN_VALUE;
         }
      } else if (rmul2 < this.rows) {
         int index = ss * this.sliceStride + rr * this.rowStride;
         return cmod2 == 0 ? index + 1 : -index;
      } else if (rmul2 > this.rows) {
         int index = s * this.sliceStride + r * this.rowStride;
         return cmod2 == 0 ? index + 1 : index;
      } else if (s == 0) {
         int index = r * this.rowStride + 1;
         return cmod2 == 0 ? index : Integer.MIN_VALUE;
      } else if (smul2 < this.slices) {
         int index = ss * this.sliceStride + r * this.rowStride;
         return cmod2 == 0 ? index + 1 : -index;
      } else if (smul2 > this.slices) {
         int index = s * this.sliceStride + r * this.rowStride;
         return cmod2 == 0 ? index + 1 : index;
      } else {
         int index = s * this.sliceStride + r * this.rowStride;
         return cmod2 == 0 ? index + 1 : Integer.MIN_VALUE;
      }
   }

   public long getIndex(long s, long r, long c) {
      long cmod2 = c & 1L;
      long rmul2 = r << 1;
      long smul2 = s << 1;
      long ss = s == 0L ? 0L : this.slicesl - s;
      long rr = r == 0L ? 0L : this.rowsl - r;
      if (c <= 1L) {
         if (r == 0L) {
            if (s == 0L) {
               return c == 0L ? 0L : Long.MIN_VALUE;
            } else if (smul2 < this.slicesl) {
               return s * this.sliceStridel + c;
            } else if (smul2 > this.slicesl) {
               long index = ss * this.sliceStridel;
               return cmod2 == 0L ? index : -(index + 1L);
            } else {
               return cmod2 == 0L ? s * this.sliceStridel : Long.MIN_VALUE;
            }
         } else if (rmul2 < this.rowsl) {
            return s * this.sliceStridel + r * this.rowStridel + c;
         } else if (rmul2 > this.rowsl) {
            long index = ss * this.sliceStridel + rr * this.rowStridel;
            return cmod2 == 0L ? index : -(index + 1L);
         } else if (s == 0L) {
            return cmod2 == 0L ? r * this.rowStridel : Long.MIN_VALUE;
         } else if (smul2 < this.slicesl) {
            return s * this.sliceStridel + r * this.rowStridel + c;
         } else if (smul2 > this.slicesl) {
            long index = ss * this.sliceStridel + r * this.rowStridel;
            return cmod2 == 0L ? index : -(index + 1L);
         } else {
            long index = s * this.sliceStridel + r * this.rowStridel;
            return cmod2 == 0L ? index : Long.MIN_VALUE;
         }
      } else if (c < this.columnsl) {
         return s * this.sliceStridel + r * this.rowStridel + c;
      } else if (c > this.columnsl + 1L) {
         long cc = (this.columnsl << 1) - c;
         long index = ss * this.sliceStridel + rr * this.rowStridel + cc;
         return cmod2 == 0L ? index : -(index + 2L);
      } else if (r == 0L) {
         if (s == 0L) {
            return cmod2 == 0L ? 1L : Long.MIN_VALUE;
         } else if (smul2 < this.slicesl) {
            long index = ss * this.sliceStridel;
            return cmod2 == 0L ? index + 1L : -index;
         } else if (smul2 > this.slicesl) {
            long index = s * this.sliceStridel;
            return cmod2 == 0L ? index + 1L : index;
         } else {
            long index = s * this.sliceStridel;
            return cmod2 == 0L ? index + 1L : Long.MIN_VALUE;
         }
      } else if (rmul2 < this.rowsl) {
         long index = ss * this.sliceStridel + rr * this.rowStridel;
         return cmod2 == 0L ? index + 1L : -index;
      } else if (rmul2 > this.rowsl) {
         long index = s * this.sliceStridel + r * this.rowStridel;
         return cmod2 == 0L ? index + 1L : index;
      } else if (s == 0L) {
         long index = r * this.rowStridel + 1L;
         return cmod2 == 0L ? index : Long.MIN_VALUE;
      } else if (smul2 < this.slicesl) {
         long index = ss * this.sliceStridel + r * this.rowStridel;
         return cmod2 == 0L ? index + 1L : -index;
      } else if (smul2 > this.slicesl) {
         long index = s * this.sliceStridel + r * this.rowStridel;
         return cmod2 == 0L ? index + 1L : index;
      } else {
         long index = s * this.sliceStridel + r * this.rowStridel;
         return cmod2 == 0L ? index + 1L : Long.MIN_VALUE;
      }
   }

   public void pack(double val, int s, int r, int c, double[] packed, int pos) {
      int i = this.getIndex(s, r, c);
      if (i >= 0) {
         packed[pos + i] = val;
      } else {
         if (i <= Integer.MIN_VALUE) {
            throw new IllegalArgumentException(String.format("[%d][%d][%d] component cannot be modified (always zero)", s, r, c));
         }

         packed[pos - i] = -val;
      }

   }

   public void pack(double val, long s, long r, long c, DoubleLargeArray packed, long pos) {
      long i = this.getIndex(s, r, c);
      if (i >= 0L) {
         packed.setDouble(pos + i, val);
      } else {
         if (i <= Long.MIN_VALUE) {
            throw new IllegalArgumentException(String.format("[%d][%d][%d] component cannot be modified (always zero)", s, r, c));
         }

         packed.setDouble(pos - i, -val);
      }

   }

   public void pack(double val, int s, int r, int c, double[][][] packed) {
      int i = this.getIndex(s, r, c);
      int ii = FastMath.abs(i);
      int ss = ii / this.sliceStride;
      int remainder = ii % this.sliceStride;
      int rr = remainder / this.rowStride;
      int cc = remainder % this.rowStride;
      if (i >= 0) {
         packed[ss][rr][cc] = val;
      } else {
         if (i <= Integer.MIN_VALUE) {
            throw new IllegalArgumentException(String.format("[%d][%d] component cannot be modified (always zero)", r, c));
         }

         packed[ss][rr][cc] = -val;
      }

   }

   public void pack(float val, int s, int r, int c, float[] packed, int pos) {
      int i = this.getIndex(s, r, c);
      if (i >= 0) {
         packed[pos + i] = val;
      } else {
         if (i <= Integer.MIN_VALUE) {
            throw new IllegalArgumentException(String.format("[%d][%d][%d] component cannot be modified (always zero)", s, r, c));
         }

         packed[pos - i] = -val;
      }

   }

   public void pack(float val, long s, long r, long c, FloatLargeArray packed, long pos) {
      long i = this.getIndex(s, r, c);
      if (i >= 0L) {
         packed.setFloat(pos + i, val);
      } else {
         if (i <= Long.MIN_VALUE) {
            throw new IllegalArgumentException(String.format("[%d][%d][%d] component cannot be modified (always zero)", s, r, c));
         }

         packed.setFloat(pos - i, -val);
      }

   }

   public void pack(float val, int s, int r, int c, float[][][] packed) {
      int i = this.getIndex(s, r, c);
      int ii = FastMath.abs(i);
      int ss = ii / this.sliceStride;
      int remainder = ii % this.sliceStride;
      int rr = remainder / this.rowStride;
      int cc = remainder % this.rowStride;
      if (i >= 0) {
         packed[ss][rr][cc] = val;
      } else {
         if (i <= Integer.MIN_VALUE) {
            throw new IllegalArgumentException(String.format("[%d][%d][%d] component cannot be modified (always zero)", s, r, c));
         }

         packed[ss][rr][cc] = -val;
      }

   }

   public double unpack(int s, int r, int c, double[] packed, int pos) {
      int i = this.getIndex(s, r, c);
      if (i >= 0) {
         return packed[pos + i];
      } else {
         return i > Integer.MIN_VALUE ? -packed[pos - i] : (double)0.0F;
      }
   }

   public double unpack(long s, long r, long c, DoubleLargeArray packed, long pos) {
      long i = this.getIndex(s, r, c);
      if (i >= 0L) {
         return packed.getDouble(pos + i);
      } else {
         return i > Long.MIN_VALUE ? -packed.getDouble(pos - i) : (double)0.0F;
      }
   }

   public double unpack(int s, int r, int c, double[][][] packed) {
      int i = this.getIndex(s, r, c);
      int ii = FastMath.abs(i);
      int ss = ii / this.sliceStride;
      int remainder = ii % this.sliceStride;
      int rr = remainder / this.rowStride;
      int cc = remainder % this.rowStride;
      if (i >= 0) {
         return packed[ss][rr][cc];
      } else {
         return i > Integer.MIN_VALUE ? -packed[ss][rr][cc] : (double)0.0F;
      }
   }

   public float unpack(int s, int r, int c, float[] packed, int pos) {
      int i = this.getIndex(s, r, c);
      if (i >= 0) {
         return packed[pos + i];
      } else {
         return i > Integer.MIN_VALUE ? -packed[pos - i] : 0.0F;
      }
   }

   public float unpack(long s, long r, long c, FloatLargeArray packed, long pos) {
      long i = this.getIndex(s, r, c);
      if (i >= 0L) {
         return packed.getFloat(pos + i);
      } else {
         return i > Long.MIN_VALUE ? -packed.getFloat(pos - i) : 0.0F;
      }
   }

   public float unpack(int s, int r, int c, float[][][] packed) {
      int i = this.getIndex(s, r, c);
      int ii = FastMath.abs(i);
      int ss = ii / this.sliceStride;
      int remainder = ii % this.sliceStride;
      int rr = remainder / this.rowStride;
      int cc = remainder % this.rowStride;
      if (i >= 0) {
         return packed[ss][rr][cc];
      } else {
         return i > Integer.MIN_VALUE ? -packed[ss][rr][cc] : 0.0F;
      }
   }
}
