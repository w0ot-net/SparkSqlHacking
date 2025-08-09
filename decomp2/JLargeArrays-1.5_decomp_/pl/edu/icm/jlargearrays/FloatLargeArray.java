package pl.edu.icm.jlargearrays;

import org.apache.commons.math3.util.FastMath;
import sun.misc.Cleaner;

public class FloatLargeArray extends LargeArray {
   private static final long serialVersionUID = -8342458159338079576L;
   private float[] data;

   public FloatLargeArray(long length) {
      this(length, true);
   }

   public FloatLargeArray(long length, boolean zeroNativeMemory) {
      this.type = LargeArrayType.FLOAT;
      this.sizeof = 4L;
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
            this.data = new float[(int)length];
         }

      }
   }

   public FloatLargeArray(long length, float constantValue) {
      this.type = LargeArrayType.FLOAT;
      this.sizeof = 4L;
      if (length <= 0L) {
         throw new IllegalArgumentException(length + " is not a positive long value");
      } else {
         this.length = length;
         this.isConstant = true;
         this.data = new float[]{constantValue};
      }
   }

   public FloatLargeArray(float[] data) {
      this.type = LargeArrayType.FLOAT;
      this.sizeof = 4L;
      this.length = (long)data.length;
      this.data = data;
   }

   public FloatLargeArray clone() {
      if (this.isConstant) {
         return new FloatLargeArray(this.length, this.getFloat(0L));
      } else {
         FloatLargeArray v = new FloatLargeArray(this.length, false);
         LargeArrayUtils.arraycopy(this, 0L, v, 0L, this.length);
         return v;
      }
   }

   public boolean equals(Object o) {
      if (super.equals(o)) {
         FloatLargeArray la = (FloatLargeArray)o;
         return this.data == la.data;
      } else {
         return false;
      }
   }

   public int hashCode() {
      return 29 * super.hashCode() + (this.data != null ? this.data.hashCode() : 0);
   }

   public final Float get(long i) {
      return this.getFloat(i);
   }

   public final Float getFromNative(long i) {
      return LargeArrayUtils.UNSAFE.getFloat(this.ptr + this.sizeof * i);
   }

   public final boolean getBoolean(long i) {
      if (this.ptr != 0L) {
         return LargeArrayUtils.UNSAFE.getFloat(this.ptr + this.sizeof * i) != 0.0F;
      } else if (this.isConstant) {
         return this.data[0] != 0.0F;
      } else {
         return this.data[(int)i] != 0.0F;
      }
   }

   public final byte getByte(long i) {
      if (this.ptr != 0L) {
         return (byte)((int)LargeArrayUtils.UNSAFE.getFloat(this.ptr + this.sizeof * i));
      } else {
         return this.isConstant ? (byte)((int)this.data[0]) : (byte)((int)this.data[(int)i]);
      }
   }

   public final short getUnsignedByte(long i) {
      if (this.ptr != 0L) {
         return (short)(255 & (int)LargeArrayUtils.UNSAFE.getFloat(this.ptr + this.sizeof * i));
      } else {
         return this.isConstant ? (short)(255 & (int)this.data[0]) : (short)(255 & (int)this.data[(int)i]);
      }
   }

   public final short getShort(long i) {
      if (this.ptr != 0L) {
         return (short)((int)LargeArrayUtils.UNSAFE.getFloat(this.ptr + this.sizeof * i));
      } else {
         return this.isConstant ? (short)((int)this.data[0]) : (short)((int)this.data[(int)i]);
      }
   }

   public final int getInt(long i) {
      if (this.ptr != 0L) {
         return (int)LargeArrayUtils.UNSAFE.getFloat(this.ptr + this.sizeof * i);
      } else {
         return this.isConstant ? (int)this.data[0] : (int)this.data[(int)i];
      }
   }

   public final long getLong(long i) {
      if (this.ptr != 0L) {
         return (long)LargeArrayUtils.UNSAFE.getFloat(this.ptr + this.sizeof * i);
      } else {
         return this.isConstant ? (long)this.data[0] : (long)this.data[(int)i];
      }
   }

   public final float getFloat(long i) {
      if (this.ptr != 0L) {
         return LargeArrayUtils.UNSAFE.getFloat(this.ptr + this.sizeof * i);
      } else {
         return this.isConstant ? this.data[0] : this.data[(int)i];
      }
   }

   public final double getDouble(long i) {
      if (this.ptr != 0L) {
         return (double)LargeArrayUtils.UNSAFE.getFloat(this.ptr + this.sizeof * i);
      } else {
         return this.isConstant ? (double)this.data[0] : (double)this.data[(int)i];
      }
   }

   public final float[] getData() {
      return this.data;
   }

   public final boolean[] getBooleanData() {
      if (this.length > 1073741824L) {
         return null;
      } else {
         boolean[] out = new boolean[(int)this.length];
         if (this.ptr != 0L) {
            for(int i = 0; (long)i < this.length; ++i) {
               float v = LargeArrayUtils.UNSAFE.getFloat(this.ptr + this.sizeof * (long)i);
               out[i] = v != 0.0F;
            }
         } else if (this.isConstant) {
            boolean elem = this.data[0] != 0.0F;

            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = elem;
            }
         } else {
            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = this.data[i] != 0.0F;
            }
         }

         return out;
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
                        float v = LargeArrayUtils.UNSAFE.getFloat(this.ptr + this.sizeof * i);
                        out[idx++] = v != 0.0F;
                     }
                  } else if (this.isConstant) {
                     for(long i = startPos; i < endPos; i += step) {
                        out[idx++] = this.data[0] != 0.0F;
                     }
                  } else {
                     for(long i = startPos; i < endPos; i += step) {
                        float v = this.data[(int)i];
                        out[idx++] = v != 0.0F;
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

   public final byte[] getByteData() {
      if (this.length > 1073741824L) {
         return null;
      } else {
         byte[] out = new byte[(int)this.length];
         if (this.ptr != 0L) {
            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = (byte)((int)LargeArrayUtils.UNSAFE.getFloat(this.ptr + this.sizeof * (long)i));
            }
         } else if (this.isConstant) {
            float elem = this.data[0];

            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = (byte)((int)elem);
            }
         } else {
            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = (byte)((int)this.data[i]);
            }
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
                        out[idx++] = (byte)((int)LargeArrayUtils.UNSAFE.getFloat(this.ptr + this.sizeof * i));
                     }
                  } else if (this.isConstant) {
                     for(long i = startPos; i < endPos; i += step) {
                        out[idx++] = (byte)((int)this.data[0]);
                     }
                  } else {
                     for(long i = startPos; i < endPos; i += step) {
                        out[idx++] = (byte)((int)this.data[(int)i]);
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
               out[i] = (short)((int)LargeArrayUtils.UNSAFE.getFloat(this.ptr + this.sizeof * (long)i));
            }
         } else if (this.isConstant) {
            float elem = this.data[0];

            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = (short)((int)elem);
            }
         } else {
            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = (short)((int)this.data[i]);
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
                        out[idx++] = (short)((int)LargeArrayUtils.UNSAFE.getFloat(this.ptr + this.sizeof * i));
                     }
                  } else if (this.isConstant) {
                     for(long i = startPos; i < endPos; i += step) {
                        out[idx++] = (short)((int)this.data[0]);
                     }
                  } else {
                     for(long i = startPos; i < endPos; i += step) {
                        out[idx++] = (short)((int)this.data[(int)i]);
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
               out[i] = (int)LargeArrayUtils.UNSAFE.getFloat(this.ptr + this.sizeof * (long)i);
            }
         } else if (this.isConstant) {
            float elem = this.data[0];

            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = (int)elem;
            }
         } else {
            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = (int)this.data[i];
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
                        out[idx++] = (int)LargeArrayUtils.UNSAFE.getFloat(this.ptr + this.sizeof * i);
                     }
                  } else if (this.isConstant) {
                     for(long i = startPos; i < endPos; i += step) {
                        out[idx++] = (int)this.data[0];
                     }
                  } else {
                     for(long i = startPos; i < endPos; i += step) {
                        out[idx++] = (int)this.data[(int)i];
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
               out[i] = (long)LargeArrayUtils.UNSAFE.getFloat(this.ptr + this.sizeof * (long)i);
            }
         } else if (this.isConstant) {
            float elem = this.data[0];

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
                        out[idx++] = (long)LargeArrayUtils.UNSAFE.getFloat(this.ptr + this.sizeof * i);
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
               out[i] = LargeArrayUtils.UNSAFE.getFloat(this.ptr + this.sizeof * (long)i);
            }
         } else if (this.isConstant) {
            float elem = this.data[0];

            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = elem;
            }
         } else {
            System.arraycopy(this.data, 0, out, 0, (int)this.length);
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
                        out[idx++] = LargeArrayUtils.UNSAFE.getFloat(this.ptr + this.sizeof * i);
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

   public final double[] getDoubleData() {
      if (this.length > 1073741824L) {
         return null;
      } else {
         double[] out = new double[(int)this.length];
         if (this.ptr != 0L) {
            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = (double)LargeArrayUtils.UNSAFE.getFloat(this.ptr + this.sizeof * (long)i);
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
                        out[idx++] = (double)LargeArrayUtils.UNSAFE.getFloat(this.ptr + this.sizeof * i);
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
      LargeArrayUtils.UNSAFE.putFloat(this.ptr + this.sizeof * i, (Float)value);
   }

   public final void setBoolean(long i, boolean value) {
      if (this.ptr != 0L) {
         LargeArrayUtils.UNSAFE.putFloat(this.ptr + this.sizeof * i, value ? 1.0F : 0.0F);
      } else {
         if (this.isConstant) {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
         }

         this.data[(int)i] = value ? 1.0F : 0.0F;
      }

   }

   public final void setByte(long i, byte value) {
      if (this.ptr != 0L) {
         LargeArrayUtils.UNSAFE.putFloat(this.ptr + this.sizeof * i, (float)value);
      } else {
         if (this.isConstant) {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
         }

         this.data[(int)i] = (float)value;
      }

   }

   public final void setUnsignedByte(long i, short value) {
      this.setShort(i, value);
   }

   public final void setShort(long i, short value) {
      if (this.ptr != 0L) {
         LargeArrayUtils.UNSAFE.putFloat(this.ptr + this.sizeof * i, (float)value);
      } else {
         if (this.isConstant) {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
         }

         this.data[(int)i] = (float)value;
      }

   }

   public final void setInt(long i, int value) {
      if (this.ptr != 0L) {
         LargeArrayUtils.UNSAFE.putFloat(this.ptr + this.sizeof * i, (float)value);
      } else {
         if (this.isConstant) {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
         }

         this.data[(int)i] = (float)value;
      }

   }

   public final void setLong(long i, long value) {
      if (this.ptr != 0L) {
         LargeArrayUtils.UNSAFE.putFloat(this.ptr + this.sizeof * i, (float)value);
      } else {
         if (this.isConstant) {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
         }

         this.data[(int)i] = (float)value;
      }

   }

   public final void setFloat(long i, float value) {
      if (this.ptr != 0L) {
         LargeArrayUtils.UNSAFE.putFloat(this.ptr + this.sizeof * i, value);
      } else {
         if (this.isConstant) {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
         }

         this.data[(int)i] = value;
      }

   }

   public final void setDouble(long i, double value) {
      if (this.ptr != 0L) {
         LargeArrayUtils.UNSAFE.putFloat(this.ptr + this.sizeof * i, (float)value);
      } else {
         if (this.isConstant) {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
         }

         this.data[(int)i] = (float)value;
      }

   }
}
