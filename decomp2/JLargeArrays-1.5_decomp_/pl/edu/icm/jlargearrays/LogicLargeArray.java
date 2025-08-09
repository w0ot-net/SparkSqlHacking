package pl.edu.icm.jlargearrays;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.apache.commons.math3.util.FastMath;
import sun.misc.Cleaner;

public class LogicLargeArray extends LargeArray {
   private static final long serialVersionUID = 3135411647668758832L;
   private byte[] data;

   public LogicLargeArray(long length) {
      this(length, true);
   }

   public LogicLargeArray(long length, boolean zeroNativeMemory) {
      this.type = LargeArrayType.LOGIC;
      this.sizeof = 1L;
      if (length <= 0L) {
         throw new IllegalArgumentException(length + " is not a positive long value");
      } else {
         this.length = length;
         if (length > (long)getMaxSizeOf32bitArray()) {
            this.ptr = LargeArrayUtils.UNSAFE.allocateMemory(this.length * this.sizeof);
            if (zeroNativeMemory) {
               this.zeroNativeMemory(length);
            }

            Cleaner.create(this, new LargeArray.Deallocator(this.ptr, this.length, this.sizeof));
            MemoryCounter.increaseCounter(this.length * this.sizeof);
         } else {
            this.data = new byte[(int)length];
         }

      }
   }

   public LogicLargeArray(long length, byte constantValue) {
      this.type = LargeArrayType.LOGIC;
      this.sizeof = 1L;
      if (length <= 0L) {
         throw new IllegalArgumentException(length + " is not a positive long value");
      } else {
         this.length = length;
         this.isConstant = true;
         this.data = new byte[]{(byte)(constantValue != 0 ? 1 : 0)};
      }
   }

   public LogicLargeArray(byte[] data) {
      this.type = LargeArrayType.LOGIC;
      this.sizeof = 1L;
      this.length = (long)data.length;

      for(int i = 0; i < data.length; ++i) {
         if (data[i] != 0 && data[i] != 1) {
            throw new IllegalArgumentException("The array contans values different than 0 and 1.");
         }
      }

      this.data = data;
   }

   public LogicLargeArray(boolean[] data) {
      this.type = LargeArrayType.LOGIC;
      this.sizeof = 1L;
      this.length = (long)data.length;
      this.data = new byte[data.length];

      for(int i = 0; i < data.length; ++i) {
         this.data[i] = (byte)(data[i] ? 1 : 0);
      }

   }

   public LogicLargeArray clone() {
      if (this.isConstant) {
         return new LogicLargeArray(this.length, this.getByte(0L));
      } else {
         LogicLargeArray v = new LogicLargeArray(this.length, false);
         LargeArrayUtils.arraycopy(this, 0L, v, 0L, this.length);
         return v;
      }
   }

   public boolean equals(Object o) {
      if (super.equals(o)) {
         LogicLargeArray la = (LogicLargeArray)o;
         return this.data == la.data;
      } else {
         return false;
      }
   }

   public int hashCode() {
      return 29 * super.hashCode() + (this.data != null ? this.data.hashCode() : 0);
   }

   public final Byte get(long i) {
      return this.getByte(i);
   }

   public final Byte getFromNative(long i) {
      return LargeArrayUtils.UNSAFE.getByte(this.ptr + i);
   }

   public final boolean getBoolean(long i) {
      if (this.ptr != 0L) {
         return LargeArrayUtils.UNSAFE.getByte(this.ptr + i) != 0;
      } else if (this.isConstant) {
         return this.data[0] != 0;
      } else {
         return this.data[(int)i] != 0;
      }
   }

   public final byte getByte(long i) {
      if (this.ptr != 0L) {
         return LargeArrayUtils.UNSAFE.getByte(this.ptr + i);
      } else {
         return this.isConstant ? this.data[0] : this.data[(int)i];
      }
   }

   public final short getUnsignedByte(long i) {
      return (short)this.getByte(i);
   }

   public final short getShort(long i) {
      if (this.ptr != 0L) {
         return (short)LargeArrayUtils.UNSAFE.getByte(this.ptr + i);
      } else {
         return this.isConstant ? (short)this.data[0] : (short)this.data[(int)i];
      }
   }

   public final int getInt(long i) {
      if (this.ptr != 0L) {
         return LargeArrayUtils.UNSAFE.getByte(this.ptr + i);
      } else {
         return this.isConstant ? this.data[0] : this.data[(int)i];
      }
   }

   public final long getLong(long i) {
      if (this.ptr != 0L) {
         return (long)LargeArrayUtils.UNSAFE.getByte(this.ptr + i);
      } else {
         return this.isConstant ? (long)this.data[0] : (long)this.data[(int)i];
      }
   }

   public final float getFloat(long i) {
      if (this.ptr != 0L) {
         return (float)LargeArrayUtils.UNSAFE.getByte(this.ptr + i);
      } else {
         return this.isConstant ? (float)this.data[0] : (float)this.data[(int)i];
      }
   }

   public final double getDouble(long i) {
      if (this.ptr != 0L) {
         return (double)LargeArrayUtils.UNSAFE.getByte(this.ptr + i);
      } else {
         return this.isConstant ? (double)this.data[0] : (double)this.data[(int)i];
      }
   }

   public final boolean[] getBooleanData(boolean[] a, long startPos, long endPos, long step) {
      if (startPos >= 0L && startPos < this.length) {
         if (endPos >= 0L && endPos <= this.length && endPos >= startPos) {
            if (step < 1L) {
               throw new IllegalArgumentException("step < 1");
            } else {
               long len = (long)FastMath.ceil((double)(endPos - startPos) / (double)step);
               if (len > 1073741824L) {
                  return null;
               } else {
                  boolean[] out;
                  if (a != null && (long)a.length >= len) {
                     out = a;
                  } else {
                     out = new boolean[(int)len];
                  }

                  int idx = 0;
                  if (this.ptr != 0L) {
                     for(long i = startPos; i < endPos; i += step) {
                        byte v = LargeArrayUtils.UNSAFE.getByte(this.ptr + i);
                        out[idx++] = v == 1;
                     }
                  } else if (this.isConstant) {
                     boolean elem = this.data[0] != 0;

                     for(long i = startPos; i < endPos; i += step) {
                        out[idx++] = elem;
                     }
                  } else {
                     for(long i = startPos; i < endPos; i += step) {
                        byte v = this.data[(int)i];
                        out[idx++] = v != 0;
                     }
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

   public final boolean[] getBooleanData() {
      if (this.length > 1073741824L) {
         return null;
      } else {
         boolean[] out = new boolean[(int)this.length];
         if (this.ptr != 0L) {
            for(int i = 0; (long)i < this.length; ++i) {
               byte v = LargeArrayUtils.UNSAFE.getByte(this.ptr + this.sizeof * (long)i);
               out[i] = v != 0;
            }
         } else if (this.isConstant) {
            boolean elem = this.data[0] != 0;

            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = elem;
            }
         } else {
            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = this.data[i] != 0;
            }
         }

         return out;
      }
   }

   public final byte[] getData() {
      return this.data;
   }

   public final byte[] getByteData() {
      if (this.length > 1073741824L) {
         return null;
      } else {
         byte[] out = new byte[(int)this.length];
         if (this.ptr != 0L) {
            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = LargeArrayUtils.UNSAFE.getByte(this.ptr + this.sizeof * (long)i);
            }
         } else if (this.isConstant) {
            byte elem = this.data[0];

            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = elem;
            }
         } else {
            System.arraycopy(this.data, 0, out, 0, (int)this.length);
         }

         return out;
      }
   }

   public final byte[] getByteData(byte[] a, long startPos, long endPos, long step) {
      if (startPos >= 0L && startPos < this.length) {
         if (endPos >= 0L && endPos <= this.length && endPos >= startPos) {
            if (step < 1L) {
               throw new IllegalArgumentException("step < 1");
            } else {
               long len = (long)FastMath.ceil((double)(endPos - startPos) / (double)step);
               if (len > 1073741824L) {
                  return null;
               } else {
                  byte[] out;
                  if (a != null && (long)a.length >= len) {
                     out = a;
                  } else {
                     out = new byte[(int)len];
                  }

                  int idx = 0;
                  if (this.ptr != 0L) {
                     for(long i = startPos; i < endPos; i += step) {
                        out[idx++] = LargeArrayUtils.UNSAFE.getByte(this.ptr + i);
                     }
                  } else if (this.isConstant) {
                     for(long i = startPos; i < endPos; i += step) {
                        out[idx++] = this.data[0];
                     }
                  } else {
                     for(long i = startPos; i < endPos; i += step) {
                        out[idx++] = this.data[(int)i];
                     }
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

   public final short[] getShortData() {
      if (this.length > 1073741824L) {
         return null;
      } else {
         short[] out = new short[(int)this.length];
         if (this.ptr != 0L) {
            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = (short)LargeArrayUtils.UNSAFE.getByte(this.ptr + this.sizeof * (long)i);
            }
         } else if (this.isConstant) {
            byte elem = this.data[0];

            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = (short)elem;
            }
         } else {
            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = (short)this.data[i];
            }
         }

         return out;
      }
   }

   public final short[] getShortData(short[] a, long startPos, long endPos, long step) {
      if (startPos >= 0L && startPos < this.length) {
         if (endPos >= 0L && endPos <= this.length && endPos >= startPos) {
            if (step < 1L) {
               throw new IllegalArgumentException("step < 1");
            } else {
               long len = (long)FastMath.ceil((double)(endPos - startPos) / (double)step);
               if (len > 1073741824L) {
                  return null;
               } else {
                  short[] out;
                  if (a != null && (long)a.length >= len) {
                     out = a;
                  } else {
                     out = new short[(int)len];
                  }

                  int idx = 0;
                  if (this.ptr != 0L) {
                     for(long i = startPos; i < endPos; i += step) {
                        out[idx++] = (short)LargeArrayUtils.UNSAFE.getByte(this.ptr + i);
                     }
                  } else if (this.isConstant) {
                     for(long i = startPos; i < endPos; i += step) {
                        out[idx++] = (short)this.data[0];
                     }
                  } else {
                     for(long i = startPos; i < endPos; i += step) {
                        out[idx++] = (short)this.data[(int)i];
                     }
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

   public final int[] getIntData() {
      if (this.length > 1073741824L) {
         return null;
      } else {
         int[] out = new int[(int)this.length];
         if (this.ptr != 0L) {
            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = LargeArrayUtils.UNSAFE.getByte(this.ptr + this.sizeof * (long)i);
            }
         } else if (this.isConstant) {
            byte elem = this.data[0];

            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = elem;
            }
         } else {
            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = this.data[i];
            }
         }

         return out;
      }
   }

   public final int[] getIntData(int[] a, long startPos, long endPos, long step) {
      if (startPos >= 0L && startPos < this.length) {
         if (endPos >= 0L && endPos <= this.length && endPos >= startPos) {
            if (step < 1L) {
               throw new IllegalArgumentException("step < 1");
            } else {
               long len = (long)FastMath.ceil((double)(endPos - startPos) / (double)step);
               if (len > 1073741824L) {
                  return null;
               } else {
                  int[] out;
                  if (a != null && (long)a.length >= len) {
                     out = a;
                  } else {
                     out = new int[(int)len];
                  }

                  int idx = 0;
                  if (this.ptr != 0L) {
                     for(long i = startPos; i < endPos; i += step) {
                        out[idx++] = LargeArrayUtils.UNSAFE.getByte(this.ptr + i);
                     }
                  } else if (this.isConstant) {
                     for(long i = startPos; i < endPos; i += step) {
                        out[idx++] = this.data[0];
                     }
                  } else {
                     for(long i = startPos; i < endPos; i += step) {
                        out[idx++] = this.data[(int)i];
                     }
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

   public final long[] getLongData() {
      if (this.length > 1073741824L) {
         return null;
      } else {
         long[] out = new long[(int)this.length];
         if (this.ptr != 0L) {
            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = (long)LargeArrayUtils.UNSAFE.getByte(this.ptr + this.sizeof * (long)i);
            }
         } else if (this.isConstant) {
            byte elem = this.data[0];

            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = (long)elem;
            }
         } else {
            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = (long)this.data[i];
            }
         }

         return out;
      }
   }

   public final long[] getLongData(long[] a, long startPos, long endPos, long step) {
      if (startPos >= 0L && startPos < this.length) {
         if (endPos >= 0L && endPos <= this.length && endPos >= startPos) {
            if (step < 1L) {
               throw new IllegalArgumentException("step < 1");
            } else {
               long len = (long)FastMath.ceil((double)(endPos - startPos) / (double)step);
               if (len > 1073741824L) {
                  return null;
               } else {
                  long[] out;
                  if (a != null && (long)a.length >= len) {
                     out = a;
                  } else {
                     out = new long[(int)len];
                  }

                  int idx = 0;
                  if (this.ptr != 0L) {
                     for(long i = startPos; i < endPos; i += step) {
                        out[idx++] = (long)LargeArrayUtils.UNSAFE.getByte(this.ptr + i);
                     }
                  } else if (this.isConstant) {
                     for(long i = startPos; i < endPos; i += step) {
                        out[idx++] = (long)this.data[0];
                     }
                  } else {
                     for(long i = startPos; i < endPos; i += step) {
                        out[idx++] = (long)this.data[(int)i];
                     }
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

   public final float[] getFloatData() {
      if (this.length > 1073741824L) {
         return null;
      } else {
         float[] out = new float[(int)this.length];
         if (this.ptr != 0L) {
            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = (float)LargeArrayUtils.UNSAFE.getByte(this.ptr + this.sizeof * (long)i);
            }
         } else if (this.isConstant) {
            float elem = (float)this.data[0];

            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = elem;
            }
         } else {
            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = (float)this.data[i];
            }
         }

         return out;
      }
   }

   public final float[] getFloatData(float[] a, long startPos, long endPos, long step) {
      if (startPos >= 0L && startPos < this.length) {
         if (endPos >= 0L && endPos <= this.length && endPos >= startPos) {
            if (step < 1L) {
               throw new IllegalArgumentException("step < 1");
            } else {
               long len = (long)FastMath.ceil((double)(endPos - startPos) / (double)step);
               if (len > 1073741824L) {
                  return null;
               } else {
                  float[] out;
                  if (a != null && (long)a.length >= len) {
                     out = a;
                  } else {
                     out = new float[(int)len];
                  }

                  int idx = 0;
                  if (this.ptr != 0L) {
                     for(long i = startPos; i < endPos; i += step) {
                        out[idx++] = (float)LargeArrayUtils.UNSAFE.getByte(this.ptr + i);
                     }
                  } else if (this.isConstant) {
                     for(long i = startPos; i < endPos; i += step) {
                        out[idx++] = (float)this.data[0];
                     }
                  } else {
                     for(long i = startPos; i < endPos; i += step) {
                        out[idx++] = (float)this.data[(int)i];
                     }
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

   public final double[] getDoubleData() {
      if (this.length > 1073741824L) {
         return null;
      } else {
         double[] out = new double[(int)this.length];
         if (this.ptr != 0L) {
            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = (double)LargeArrayUtils.UNSAFE.getByte(this.ptr + this.sizeof * (long)i);
            }
         } else if (this.isConstant) {
            double elem = (double)this.data[0];

            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = elem;
            }
         } else {
            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = (double)this.data[i];
            }
         }

         return out;
      }
   }

   public final double[] getDoubleData(double[] a, long startPos, long endPos, long step) {
      if (startPos >= 0L && startPos < this.length) {
         if (endPos >= 0L && endPos <= this.length && endPos >= startPos) {
            if (step < 1L) {
               throw new IllegalArgumentException("step < 1");
            } else {
               long len = (long)FastMath.ceil((double)(endPos - startPos) / (double)step);
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
                  if (this.ptr != 0L) {
                     for(long i = startPos; i < endPos; i += step) {
                        out[idx++] = (double)LargeArrayUtils.UNSAFE.getByte(this.ptr + i);
                     }
                  } else if (this.isConstant) {
                     for(long i = startPos; i < endPos; i += step) {
                        out[idx++] = (double)this.data[0];
                     }
                  } else {
                     for(long i = startPos; i < endPos; i += step) {
                        out[idx++] = (double)this.data[(int)i];
                     }
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
      LargeArrayUtils.UNSAFE.putByte(this.ptr + i, (Byte)value);
   }

   public final void setBoolean(long i, boolean value) {
      if (this.ptr != 0L) {
         LargeArrayUtils.UNSAFE.putByte(this.ptr + i, (byte)(value ? 1 : 0));
      } else {
         if (this.isConstant) {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
         }

         this.data[(int)i] = (byte)(value ? 1 : 0);
      }

   }

   public final void setByte(long i, byte value) {
      if (value >= 0 && value <= 1) {
         if (this.ptr != 0L) {
            LargeArrayUtils.UNSAFE.putByte(this.ptr + i, value);
         } else {
            if (this.isConstant) {
               throw new IllegalAccessError("Constant arrays cannot be modified.");
            }

            this.data[(int)i] = value;
         }

      } else {
         throw new IllegalArgumentException("The value has to be 0 or 1.");
      }
   }

   public final void setUnsignedByte(long i, short value) {
      this.setShort(i, value);
   }

   public final void setShort(long i, short value) {
      if (value >= 0 && value <= 1) {
         if (this.ptr != 0L) {
            LargeArrayUtils.UNSAFE.putByte(this.ptr + i, (byte)value);
         } else {
            if (this.isConstant) {
               throw new IllegalAccessError("Constant arrays cannot be modified.");
            }

            this.data[(int)i] = (byte)value;
         }

      } else {
         throw new IllegalArgumentException("The value has to be 0 or 1.");
      }
   }

   public final void setInt(long i, int value) {
      if (value >= 0 && value <= 1) {
         if (this.ptr != 0L) {
            LargeArrayUtils.UNSAFE.putByte(this.ptr + i, (byte)value);
         } else {
            if (this.isConstant) {
               throw new IllegalAccessError("Constant arrays cannot be modified.");
            }

            this.data[(int)i] = (byte)value;
         }

      } else {
         throw new IllegalArgumentException("The value has to be 0 or 1.");
      }
   }

   public final void setLong(long i, long value) {
      if (value >= 0L && value <= 1L) {
         if (this.ptr != 0L) {
            LargeArrayUtils.UNSAFE.putByte(this.ptr + i, (byte)((int)value));
         } else {
            if (this.isConstant) {
               throw new IllegalAccessError("Constant arrays cannot be modified.");
            }

            this.data[(int)i] = (byte)((int)value);
         }

      } else {
         throw new IllegalArgumentException("The value has to be 0 or 1.");
      }
   }

   public final void setFloat(long i, float value) {
      if ((double)value != (double)0.0F && (double)value != (double)1.0F) {
         throw new IllegalArgumentException("The value has to be 0 or 1.");
      } else {
         if (this.ptr != 0L) {
            LargeArrayUtils.UNSAFE.putByte(this.ptr + i, (byte)((int)value));
         } else {
            if (this.isConstant) {
               throw new IllegalAccessError("Constant arrays cannot be modified.");
            }

            this.data[(int)i] = (byte)((int)value);
         }

      }
   }

   public final void setDouble(long i, double value) {
      if (value != (double)0.0F && value != (double)1.0F) {
         throw new IllegalArgumentException("The value has to be 0 or 1.");
      } else {
         if (this.ptr != 0L) {
            LargeArrayUtils.UNSAFE.putByte(this.ptr + i, (byte)((int)value));
         } else {
            if (this.isConstant) {
               throw new IllegalAccessError("Constant arrays cannot be modified.");
            }

            this.data[(int)i] = (byte)((int)value);
         }

      }
   }

   public LogicLargeArray and(final LogicLargeArray array) {
      if (array != null && array.length() == this.length) {
         final LogicLargeArray out = new LogicLargeArray(array.length(), false);
         int nthreads = (int)FastMath.min(this.length, (long)ConcurrencyUtils.getNumberOfThreads());
         if (nthreads > 2 && this.length >= ConcurrencyUtils.getConcurrentThreshold()) {
            long k = this.length / (long)nthreads;
            Future[] threads = new Future[nthreads];

            for(int j = 0; j < nthreads; ++j) {
               final long firstIdx = (long)j * k;
               final long lastIdx = j == nthreads - 1 ? this.length : firstIdx + k;
               threads[j] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     for(long k = firstIdx; k < lastIdx; ++k) {
                        out.setByte(k, (byte)(LogicLargeArray.this.getByte(k) & array.getByte(k)));
                     }

                  }
               });
            }

            try {
               ConcurrencyUtils.waitForCompletion(threads);
            } catch (InterruptedException var12) {
               for(long i = 0L; i < this.length; ++i) {
                  out.setByte(i, (byte)(this.getByte(i) & array.getByte(i)));
               }
            } catch (ExecutionException var13) {
               for(long i = 0L; i < this.length; ++i) {
                  out.setByte(i, (byte)(this.getByte(i) & array.getByte(i)));
               }
            }
         } else {
            for(long i = 0L; i < this.length; ++i) {
               out.setByte(i, (byte)(this.getByte(i) & array.getByte(i)));
            }
         }

         return out;
      } else {
         throw new IllegalArgumentException("array == null || array.length() != length");
      }
   }

   public LogicLargeArray or(final LogicLargeArray array) {
      if (array != null && array.length() == this.length) {
         final LogicLargeArray out = new LogicLargeArray(array.length(), false);
         int nthreads = (int)FastMath.min(this.length, (long)ConcurrencyUtils.getNumberOfThreads());
         if (nthreads > 2 && this.length >= ConcurrencyUtils.getConcurrentThreshold()) {
            long k = this.length / (long)nthreads;
            Future[] threads = new Future[nthreads];

            for(int j = 0; j < nthreads; ++j) {
               final long firstIdx = (long)j * k;
               final long lastIdx = j == nthreads - 1 ? this.length : firstIdx + k;
               threads[j] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     for(long k = firstIdx; k < lastIdx; ++k) {
                        out.setByte(k, (byte)(LogicLargeArray.this.getByte(k) | array.getByte(k)));
                     }

                  }
               });
            }

            try {
               ConcurrencyUtils.waitForCompletion(threads);
            } catch (InterruptedException var12) {
               for(long i = 0L; i < this.length; ++i) {
                  out.setByte(i, (byte)(this.getByte(i) | array.getByte(i)));
               }
            } catch (ExecutionException var13) {
               for(long i = 0L; i < this.length; ++i) {
                  out.setByte(i, (byte)(this.getByte(i) | array.getByte(i)));
               }
            }
         } else {
            for(long i = 0L; i < this.length; ++i) {
               out.setByte(i, (byte)(this.getByte(i) | array.getByte(i)));
            }
         }

         return out;
      } else {
         throw new IllegalArgumentException("array == null || array.length() != length");
      }
   }

   public LogicLargeArray xor(final LogicLargeArray array) {
      if (array != null && array.length() == this.length) {
         final LogicLargeArray out = new LogicLargeArray(array.length(), false);
         int nthreads = (int)FastMath.min(this.length, (long)ConcurrencyUtils.getNumberOfThreads());
         if (nthreads > 2 && this.length >= ConcurrencyUtils.getConcurrentThreshold()) {
            long k = this.length / (long)nthreads;
            Future[] threads = new Future[nthreads];

            for(int j = 0; j < nthreads; ++j) {
               final long firstIdx = (long)j * k;
               final long lastIdx = j == nthreads - 1 ? this.length : firstIdx + k;
               threads[j] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     for(long k = firstIdx; k < lastIdx; ++k) {
                        out.setByte(k, (byte)(LogicLargeArray.this.getByte(k) ^ array.getByte(k)));
                     }

                  }
               });
            }

            try {
               ConcurrencyUtils.waitForCompletion(threads);
            } catch (InterruptedException var12) {
               for(long i = 0L; i < this.length; ++i) {
                  out.setByte(i, (byte)(this.getByte(i) ^ array.getByte(i)));
               }
            } catch (ExecutionException var13) {
               for(long i = 0L; i < this.length; ++i) {
                  out.setByte(i, (byte)(this.getByte(i) ^ array.getByte(i)));
               }
            }
         } else {
            for(long i = 0L; i < this.length; ++i) {
               out.setByte(i, (byte)(this.getByte(i) ^ array.getByte(i)));
            }
         }

         return out;
      } else {
         throw new IllegalArgumentException("array == null || array.length() != length");
      }
   }

   public LogicLargeArray not() {
      final LogicLargeArray out = new LogicLargeArray(this.length, false);
      int nthreads = (int)FastMath.min(this.length, (long)ConcurrencyUtils.getNumberOfThreads());
      if (nthreads > 2 && this.length >= ConcurrencyUtils.getConcurrentThreshold()) {
         long k = this.length / (long)nthreads;
         Future[] threads = new Future[nthreads];

         for(int j = 0; j < nthreads; ++j) {
            final long firstIdx = (long)j * k;
            final long lastIdx = j == nthreads - 1 ? this.length : firstIdx + k;
            threads[j] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(long k = firstIdx; k < lastIdx; ++k) {
                     out.setByte(k, (byte)(1 - LogicLargeArray.this.getByte(k)));
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(threads);
         } catch (InterruptedException var11) {
            for(long i = 0L; i < this.length; ++i) {
               out.setByte(i, (byte)(1 - this.getByte(i)));
            }
         } catch (ExecutionException var12) {
            for(long i = 0L; i < this.length; ++i) {
               out.setByte(i, (byte)(1 - this.getByte(i)));
            }
         }
      } else {
         for(long i = 0L; i < this.length; ++i) {
            out.setByte(i, (byte)(1 - this.getByte(i)));
         }
      }

      return out;
   }
}
