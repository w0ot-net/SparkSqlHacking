package org.jtransforms.fft;

import pl.edu.icm.jlargearrays.DoubleLargeArray;
import pl.edu.icm.jlargearrays.FloatLargeArray;

public class RealFFTUtils_2D {
   private static final int ONE = 1;
   private static final int TWO = 2;
   private static final int ZERO = 0;
   private static final long ONEL = 1L;
   private static final long TWOL = 2L;
   private static final long ZEROL = 0L;
   private final int columns;
   private final int rows;
   private final long columnsl;
   private final long rowsl;

   public RealFFTUtils_2D(long rows, long columns) {
      this.columns = (int)columns;
      this.rows = (int)rows;
      this.columnsl = columns;
      this.rowsl = rows;
   }

   public int getIndex(int r, int c) {
      int cmod2 = c & 1;
      int rmul2 = r << 1;
      if (r != 0) {
         if (c <= 1) {
            if (rmul2 == this.rows) {
               return cmod2 == 1 ? Integer.MIN_VALUE : this.rows * this.columns >> 1;
            } else if (rmul2 < this.rows) {
               return this.columns * r + cmod2;
            } else {
               return cmod2 == 0 ? this.columns * (this.rows - r) : -(this.columns * (this.rows - r) + 1);
            }
         } else if (c != this.columns && c != this.columns + 1) {
            if (c < this.columns) {
               return this.columns * r + c;
            } else {
               return cmod2 == 0 ? this.columns * (this.rows + 2 - r) - c : -(this.columns * (this.rows + 2 - r) - c + 2);
            }
         } else if (rmul2 == this.rows) {
            return cmod2 == 1 ? Integer.MIN_VALUE : (this.rows * this.columns >> 1) + 1;
         } else if (rmul2 < this.rows) {
            return cmod2 == 0 ? this.columns * (this.rows - r) + 1 : -(this.columns * (this.rows - r));
         } else {
            return this.columns * r + 1 - cmod2;
         }
      } else if (c != 1 && c != this.columns + 1) {
         if (c == this.columns) {
            return 1;
         } else if (c < this.columns) {
            return c;
         } else {
            return cmod2 == 0 ? (this.columns << 1) - c : -((this.columns << 1) - c + 2);
         }
      } else {
         return Integer.MIN_VALUE;
      }
   }

   public long getIndex(long r, long c) {
      long cmod2 = c & 1L;
      long rmul2 = r << 1;
      if (r != 0L) {
         if (c <= 1L) {
            if (rmul2 == this.rowsl) {
               return cmod2 == 1L ? Long.MIN_VALUE : this.rowsl * this.columnsl >> 1;
            } else if (rmul2 < this.rowsl) {
               return this.columnsl * r + cmod2;
            } else {
               return cmod2 == 0L ? this.columnsl * (this.rowsl - r) : -(this.columnsl * (this.rowsl - r) + 1L);
            }
         } else if (c != this.columnsl && c != this.columnsl + 1L) {
            if (c < this.columnsl) {
               return this.columnsl * r + c;
            } else {
               return cmod2 == 0L ? this.columnsl * (this.rowsl + 2L - r) - c : -(this.columnsl * (this.rowsl + 2L - r) - c + 2L);
            }
         } else if (rmul2 == this.rowsl) {
            return cmod2 == 1L ? Long.MIN_VALUE : (this.rowsl * this.columnsl >> 1) + 1L;
         } else if (rmul2 < this.rowsl) {
            return cmod2 == 0L ? this.columnsl * (this.rowsl - r) + 1L : -(this.columnsl * (this.rowsl - r));
         } else {
            return this.columnsl * r + 1L - cmod2;
         }
      } else if (c != 1L && c != this.columnsl + 1L) {
         if (c == this.columnsl) {
            return 1L;
         } else if (c < this.columnsl) {
            return c;
         } else {
            return cmod2 == 0L ? (this.columnsl << 1) - c : -((this.columnsl << 1) - c + 2L);
         }
      } else {
         return Long.MIN_VALUE;
      }
   }

   public void pack(double val, int r, int c, double[] packed, int pos) {
      int index = this.getIndex(r, c);
      if (index >= 0) {
         packed[pos + index] = val;
      } else {
         if (index <= Integer.MIN_VALUE) {
            throw new IllegalArgumentException(String.format("[%d][%d] component cannot be modified (always zero)", r, c));
         }

         packed[pos - index] = -val;
      }

   }

   public void pack(double val, long r, long c, DoubleLargeArray packed, long pos) {
      long index = this.getIndex(r, c);
      if (index >= 0L) {
         packed.setDouble(pos + index, val);
      } else {
         if (index <= Long.MIN_VALUE) {
            throw new IllegalArgumentException(String.format("[%d][%d] component cannot be modified (always zero)", r, c));
         }

         packed.setDouble(pos - index, -val);
      }

   }

   public void pack(double val, int r, int c, double[][] packed) {
      int index = this.getIndex(r, c);
      if (index >= 0) {
         packed[index / this.columns][index % this.columns] = val;
      } else {
         if (index <= Integer.MIN_VALUE) {
            throw new IllegalArgumentException(String.format("[%d][%d] component cannot be modified (always zero)", r, c));
         }

         packed[-index / this.columns][-index % this.columns] = -val;
      }

   }

   public void pack(float val, int r, int c, float[] packed, int pos) {
      int index = this.getIndex(r, c);
      if (index >= 0) {
         packed[pos + index] = val;
      } else {
         if (index <= Integer.MIN_VALUE) {
            throw new IllegalArgumentException(String.format("[%d][%d] component cannot be modified (always zero)", r, c));
         }

         packed[pos - index] = -val;
      }

   }

   public void pack(float val, long r, long c, FloatLargeArray packed, long pos) {
      long index = this.getIndex(r, c);
      if (index >= 0L) {
         packed.setFloat(pos + index, val);
      } else {
         if (index <= Long.MIN_VALUE) {
            throw new IllegalArgumentException(String.format("[%d][%d] component cannot be modified (always zero)", r, c));
         }

         packed.setFloat(pos - index, -val);
      }

   }

   public void pack(float val, int r, int c, float[][] packed) {
      int index = this.getIndex(r, c);
      if (index >= 0) {
         packed[index / this.columns][index % this.columns] = val;
      } else {
         if (index <= Integer.MIN_VALUE) {
            throw new IllegalArgumentException(String.format("[%d][%d] component cannot be modified (always zero)", r, c));
         }

         packed[-index / this.columns][-index % this.columns] = -val;
      }

   }

   public double unpack(int r, int c, double[] packed, int pos) {
      int index = this.getIndex(r, c);
      if (index >= 0) {
         return packed[pos + index];
      } else {
         return index > Integer.MIN_VALUE ? -packed[pos - index] : (double)0.0F;
      }
   }

   public double unpack(long r, long c, DoubleLargeArray packed, long pos) {
      long index = this.getIndex(r, c);
      if (index >= 0L) {
         return packed.getDouble(pos + index);
      } else {
         return index > Long.MIN_VALUE ? -packed.getDouble(pos - index) : (double)0.0F;
      }
   }

   public double unpack(int r, int c, double[][] packed) {
      int index = this.getIndex(r, c);
      if (index >= 0) {
         return packed[index / this.columns][index % this.columns];
      } else {
         return index > Integer.MIN_VALUE ? -packed[-index / this.columns][-index % this.columns] : (double)0.0F;
      }
   }

   public float unpack(int r, int c, float[] packed, int pos) {
      int index = this.getIndex(r, c);
      if (index >= 0) {
         return packed[pos + index];
      } else {
         return index > Integer.MIN_VALUE ? -packed[pos - index] : 0.0F;
      }
   }

   public float unpack(long r, long c, FloatLargeArray packed, long pos) {
      long index = this.getIndex(r, c);
      if (index >= 0L) {
         return packed.getFloat(pos + index);
      } else {
         return index > Long.MIN_VALUE ? -packed.getFloat(pos - index) : 0.0F;
      }
   }

   public float unpack(int r, int c, float[][] packed) {
      int index = this.getIndex(r, c);
      if (index >= 0) {
         return packed[index / this.columns][index % this.columns];
      } else {
         return index > Integer.MIN_VALUE ? -packed[-index / this.columns][-index % this.columns] : 0.0F;
      }
   }
}
