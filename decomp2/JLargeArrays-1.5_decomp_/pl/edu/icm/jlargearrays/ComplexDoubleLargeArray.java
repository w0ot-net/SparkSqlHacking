package pl.edu.icm.jlargearrays;

import org.apache.commons.math3.util.FastMath;

public class ComplexDoubleLargeArray extends LargeArray {
   private static final long serialVersionUID = 15533907580310407L;
   private DoubleLargeArray dataRe;
   private DoubleLargeArray dataIm;

   public ComplexDoubleLargeArray(long length) {
      this(length, true);
   }

   public ComplexDoubleLargeArray(long length, boolean zeroNativeMemory) {
      this.type = LargeArrayType.COMPLEX_DOUBLE;
      this.sizeof = 8L;
      if (length <= 0L) {
         throw new IllegalArgumentException(length + " is not a positive long value");
      } else {
         this.length = length;
         this.dataRe = new DoubleLargeArray(length, zeroNativeMemory);
         this.dataIm = new DoubleLargeArray(length, zeroNativeMemory);
      }
   }

   public ComplexDoubleLargeArray(long length, double[] constantValue) {
      this.type = LargeArrayType.COMPLEX_DOUBLE;
      this.sizeof = 8L;
      if (length <= 0L) {
         throw new IllegalArgumentException(length + " is not a positive long value");
      } else if (constantValue != null && constantValue.length == 2) {
         this.length = length;
         this.isConstant = true;
         this.dataRe = new DoubleLargeArray(length, constantValue[0]);
         this.dataIm = new DoubleLargeArray(length, constantValue[1]);
      } else {
         throw new IllegalArgumentException("constantValue == null || constantValue.length != 2");
      }
   }

   public ComplexDoubleLargeArray(double[] data) {
      this(new DoubleLargeArray(data));
   }

   public ComplexDoubleLargeArray(DoubleLargeArray data) {
      if (data.length() % 2L != 0L) {
         throw new IllegalArgumentException("The length of the data array must be even.");
      } else if (data.length() <= 0L) {
         throw new IllegalArgumentException(data.length() + " is not a positive long value");
      } else {
         this.type = LargeArrayType.COMPLEX_DOUBLE;
         this.sizeof = 8L;
         this.length = data.length / 2L;
         this.isConstant = data.isConstant;
         if (this.isConstant) {
            this.dataRe = new DoubleLargeArray(this.length, data.getDouble(0L));
            this.dataIm = new DoubleLargeArray(this.length, data.getDouble(1L));
         } else {
            this.dataRe = new DoubleLargeArray(this.length, false);
            this.dataIm = new DoubleLargeArray(this.length, false);

            for(long i = 0L; i < this.length; ++i) {
               this.dataRe.setDouble(i, data.getDouble(2L * i));
               this.dataIm.setDouble(i, data.getDouble(2L * i + 1L));
            }
         }

      }
   }

   public ComplexDoubleLargeArray(double[] dataRe, double[] dataIm) {
      this(new DoubleLargeArray(dataRe), new DoubleLargeArray(dataIm));
   }

   public ComplexDoubleLargeArray(DoubleLargeArray dataRe, DoubleLargeArray dataIm) {
      if (dataRe.length() != dataIm.length()) {
         throw new IllegalArgumentException("The length of the dataRe must be equal to the length of dataIm.");
      } else if (dataRe.length() <= 0L) {
         throw new IllegalArgumentException(dataRe.length() + " is not a positive long value");
      } else if (dataRe.isLarge() != dataIm.isLarge()) {
         throw new IllegalArgumentException("dataRe.isLarge() != dataIm.isLarge()");
      } else {
         this.type = LargeArrayType.COMPLEX_DOUBLE;
         this.sizeof = 8L;
         this.length = dataRe.length();
         this.dataRe = dataRe;
         this.dataIm = dataIm;
      }
   }

   public ComplexDoubleLargeArray clone() {
      if (this.isConstant) {
         return new ComplexDoubleLargeArray(this.length, new double[]{this.dataRe.getDouble(0L), this.dataIm.getDouble(0L)});
      } else {
         ComplexDoubleLargeArray v = new ComplexDoubleLargeArray(this.length, false);
         LargeArrayUtils.arraycopy(this, 0L, v, 0L, this.length);
         return v;
      }
   }

   public boolean equals(Object o) {
      if (!super.equals(o)) {
         return false;
      } else {
         ComplexDoubleLargeArray la = (ComplexDoubleLargeArray)o;
         return this.dataRe.equals(la.dataRe) && this.dataIm.equals(la.dataIm);
      }
   }

   public int hashCode() {
      int hash = 29 * super.hashCode() + (this.dataRe != null ? this.dataRe.hashCode() : 0);
      return 29 * hash + (this.dataIm != null ? this.dataIm.hashCode() : 0);
   }

   public boolean isLarge() {
      return this.dataRe.isLarge();
   }

   public final DoubleLargeArray getRealArray() {
      return this.dataRe;
   }

   public final DoubleLargeArray getImaginaryArray() {
      return this.dataIm;
   }

   public final DoubleLargeArray getAbsArray() {
      DoubleLargeArray out = new DoubleLargeArray(this.length, false);

      for(long i = 0L; i < this.length; ++i) {
         double re = this.dataRe.getDouble(i);
         double im = this.dataIm.getDouble(i);
         out.setDouble(i, FastMath.sqrt(re * re + im * im));
      }

      return out;
   }

   public final DoubleLargeArray getArgArray() {
      DoubleLargeArray out = new DoubleLargeArray(this.length, false);

      for(long i = 0L; i < this.length; ++i) {
         double re = this.dataRe.getDouble(i);
         double im = this.dataIm.getDouble(i);
         out.setDouble(i, FastMath.atan2(im, re));
      }

      return out;
   }

   public final double[] get(long i) {
      return this.getComplexDouble(i);
   }

   public final double[] getFromNative(long i) {
      return new double[]{this.dataRe.getFromNative(i), this.dataIm.getFromNative(i)};
   }

   public final boolean getBoolean(long i) {
      return this.dataRe.getBoolean(i);
   }

   public final byte getByte(long i) {
      return this.dataRe.getByte(i);
   }

   public final short getUnsignedByte(long i) {
      return this.dataRe.getUnsignedByte(i);
   }

   public final short getShort(long i) {
      return this.dataRe.getShort(i);
   }

   public final int getInt(long i) {
      return this.dataRe.getInt(i);
   }

   public final long getLong(long i) {
      return this.dataRe.getLong(i);
   }

   public final float getFloat(long i) {
      return this.dataRe.getFloat(i);
   }

   public final double getDouble(long i) {
      return this.dataRe.getDouble(i);
   }

   public final float[] getComplexFloat(long i) {
      return new float[]{this.dataRe.getFloat(i), this.dataIm.getFloat(i)};
   }

   public final double[] getComplexDouble(long i) {
      return new double[]{this.dataRe.getDouble(i), this.dataIm.getDouble(i)};
   }

   public final double[][] getData() {
      return this.isLarge() ? (double[][])null : new double[][]{this.dataRe.getData(), this.dataIm.getData()};
   }

   public final boolean[] getBooleanData() {
      return this.dataRe.getBooleanData();
   }

   public final boolean[] getBooleanData(boolean[] a, long startPos, long endPos, long step) {
      return this.dataRe.getBooleanData(a, startPos, endPos, step);
   }

   public final byte[] getByteData() {
      return this.dataRe.getByteData();
   }

   public final byte[] getByteData(byte[] a, long startPos, long endPos, long step) {
      return this.dataRe.getByteData(a, startPos, endPos, step);
   }

   public final short[] getShortData() {
      return this.dataRe.getShortData();
   }

   public final short[] getShortData(short[] a, long startPos, long endPos, long step) {
      return this.dataRe.getShortData(a, startPos, endPos, step);
   }

   public final int[] getIntData() {
      return this.dataRe.getIntData();
   }

   public final int[] getIntData(int[] a, long startPos, long endPos, long step) {
      return this.dataRe.getIntData(a, startPos, endPos, step);
   }

   public final long[] getLongData() {
      return this.dataRe.getLongData();
   }

   public final long[] getLongData(long[] a, long startPos, long endPos, long step) {
      return this.dataRe.getLongData(a, startPos, endPos, step);
   }

   public final float[] getFloatData() {
      return this.dataRe.getFloatData();
   }

   public final float[] getFloatData(float[] a, long startPos, long endPos, long step) {
      return this.dataRe.getFloatData(a, startPos, endPos, step);
   }

   public final double[] getDoubleData() {
      return this.dataRe.getDoubleData();
   }

   public final double[] getDoubleData(double[] a, long startPos, long endPos, long step) {
      return this.dataRe.getDoubleData(a, startPos, endPos, step);
   }

   public final double[] getComplexData() {
      if (2L * this.length > 1073741824L) {
         return null;
      } else {
         double[] out = new double[(int)(2L * this.length)];

         for(int i = 0; (long)i < this.length; ++i) {
            out[2 * i] = this.dataRe.getDouble((long)i);
            out[2 * i + 1] = this.dataIm.getDouble((long)i);
         }

         return out;
      }
   }

   public final double[] getComplexData(double[] a, long startPos, long endPos, long step) {
      if (startPos >= 0L && startPos < this.length) {
         if (endPos >= 0L && endPos <= this.length && endPos >= startPos) {
            if (step < 1L) {
               throw new IllegalArgumentException("step < 1");
            } else {
               long len = 2L * (long)FastMath.ceil((double)(endPos - startPos) / (double)step);
               if (len > 1073741824L) {
                  return null;
               } else {
                  double[] out;
                  if (a != null && (long)a.length >= len) {
                     out = a;
                  } else {
                     out = new double[(int)len];
                  }

                  int idx = 0;

                  for(long i = startPos; i < endPos; i += step) {
                     out[idx++] = this.dataRe.getDouble(i);
                     out[idx++] = this.dataIm.getDouble(i);
                  }

                  return out;
               }
            }
         } else {
            throw new ArrayIndexOutOfBoundsException("endPos < 0 || endPos > length || endPos < startPos");
         }
      } else {
         throw new ArrayIndexOutOfBoundsException("startPos < 0 || startPos >= length");
      }
   }

   public final void setToNative(long i, Object value) {
      if (!(value instanceof double[])) {
         throw new IllegalArgumentException(value + " is not an array of doubles.");
      } else {
         this.dataRe.setToNative(i, ((double[])((double[])value))[0]);
         this.dataIm.setToNative(i, ((double[])((double[])value))[1]);
      }
   }

   public final void setBoolean(long i, boolean value) {
      this.dataRe.setBoolean(i, value);
      this.dataIm.setBoolean(i, false);
   }

   public final void setByte(long i, byte value) {
      this.dataRe.setByte(i, value);
      this.dataIm.setByte(i, (byte)0);
   }

   public final void setUnsignedByte(long i, short value) {
      this.dataRe.setUnsignedByte(i, value);
      this.dataIm.setUnsignedByte(i, (short)0);
   }

   public final void setShort(long i, short value) {
      this.dataRe.setShort(i, value);
      this.dataIm.setShort(i, (short)0);
   }

   public final void setInt(long i, int value) {
      this.dataRe.setInt(i, value);
      this.dataIm.setInt(i, 0);
   }

   public final void setLong(long i, long value) {
      this.dataRe.setLong(i, value);
      this.dataIm.setLong(i, 0L);
   }

   public final void setFloat(long i, float value) {
      this.dataRe.setFloat(i, value);
      this.dataIm.setFloat(i, 0.0F);
   }

   public final void setDouble(long i, double value) {
      this.dataRe.setDouble(i, value);
      this.dataIm.setDouble(i, (double)0.0F);
   }

   public final void set(long i, Object value) {
      if (!(value instanceof double[])) {
         throw new IllegalArgumentException(value + " is not an array of doubles.");
      } else {
         this.setComplexDouble(i, (double[])value);
      }
   }

   public final void setComplexFloat(long i, float[] value) {
      this.dataRe.setFloat(i, value[0]);
      this.dataIm.setFloat(i, value[1]);
   }

   public final void setComplexDouble(long i, double[] value) {
      this.dataRe.setDouble(i, value[0]);
      this.dataIm.setDouble(i, value[1]);
   }
}
