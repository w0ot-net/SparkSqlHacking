package pl.edu.icm.jlargearrays;

import org.apache.commons.math3.util.FastMath;
import sun.misc.Cleaner;

public class IntLargeArray extends LargeArray {
   private static final long serialVersionUID = 86623276977976615L;
   private int[] data;

   public IntLargeArray(long length) {
      this(length, true);
   }

   public IntLargeArray(long length, boolean zeroNativeMemory) {
      this.type = LargeArrayType.INT;
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
            this.data = new int[(int)length];
         }

      }
   }

   public IntLargeArray(long length, int constantValue) {
      this.type = LargeArrayType.INT;
      this.sizeof = 4L;
      if (length <= 0L) {
         throw new IllegalArgumentException(length + " is not a positive long value");
      } else {
         this.length = length;
         this.isConstant = true;
         this.data = new int[]{constantValue};
      }
   }

   public IntLargeArray(int[] data) {
      this.type = LargeArrayType.INT;
      this.sizeof = 4L;
      this.length = (long)data.length;
      this.data = data;
   }

   public IntLargeArray clone() {
      if (this.isConstant) {
         return new IntLargeArray(this.length, this.getInt(0L));
      } else {
         IntLargeArray v = new IntLargeArray(this.length, false);
         LargeArrayUtils.arraycopy(this, 0L, v, 0L, this.length);
         return v;
      }
   }

   public boolean equals(Object o) {
      if (super.equals(o)) {
         IntLargeArray la = (IntLargeArray)o;
         return this.data == la.data;
      } else {
         return false;
      }
   }

   public int hashCode() {
      return 29 * super.hashCode() + (this.data != null ? this.data.hashCode() : 0);
   }

   public final Integer get(long i) {
      return this.getInt(i);
   }

   public final Integer getFromNative(long i) {
      return LargeArrayUtils.UNSAFE.getInt(this.ptr + this.sizeof * i);
   }

   public final boolean getBoolean(long i) {
      if (this.ptr != 0L) {
         return LargeArrayUtils.UNSAFE.getInt(this.ptr + this.sizeof * i) != 0;
      } else if (this.isConstant) {
         return this.data[0] != 0;
      } else {
         return this.data[(int)i] != 0;
      }
   }

   public final byte getByte(long i) {
      if (this.ptr != 0L) {
         return (byte)LargeArrayUtils.UNSAFE.getInt(this.ptr + this.sizeof * i);
      } else {
         return this.isConstant ? (byte)this.data[0] : (byte)this.data[(int)i];
      }
   }

   public final short getUnsignedByte(long i) {
      if (this.ptr != 0L) {
         return (short)(255 & LargeArrayUtils.UNSAFE.getInt(this.ptr + this.sizeof * i));
      } else {
         return this.isConstant ? (short)(255 & this.data[0]) : (short)(255 & this.data[(int)i]);
      }
   }

   public final short getShort(long i) {
      if (this.ptr != 0L) {
         return (short)LargeArrayUtils.UNSAFE.getInt(this.ptr + this.sizeof * i);
      } else {
         return this.isConstant ? (short)this.data[0] : (short)this.data[(int)i];
      }
   }

   public final int getInt(long i) {
      if (this.ptr != 0L) {
         return LargeArrayUtils.UNSAFE.getInt(this.ptr + this.sizeof * i);
      } else {
         return this.isConstant ? this.data[0] : this.data[(int)i];
      }
   }

   public final long getLong(long i) {
      if (this.ptr != 0L) {
         return (long)LargeArrayUtils.UNSAFE.getInt(this.ptr + this.sizeof * i);
      } else {
         return this.isConstant ? (long)this.data[0] : (long)this.data[(int)i];
      }
   }

   public final float getFloat(long i) {
      if (this.ptr != 0L) {
         return (float)LargeArrayUtils.UNSAFE.getInt(this.ptr + this.sizeof * i);
      } else {
         return this.isConstant ? (float)this.data[0] : (float)this.data[(int)i];
      }
   }

   public final double getDouble(long i) {
      if (this.ptr != 0L) {
         return (double)LargeArrayUtils.UNSAFE.getInt(this.ptr + this.sizeof * i);
      } else {
         return this.isConstant ? (double)this.data[0] : (double)this.data[(int)i];
      }
   }

   public final int[] getData() {
      return this.data;
   }

   public final boolean[] getBooleanData() {
      if (this.length > 1073741824L) {
         return null;
      } else {
         boolean[] out = new boolean[(int)this.length];
         if (this.ptr != 0L) {
            for(int i = 0; (long)i < this.length; ++i) {
               int v = LargeArrayUtils.UNSAFE.getInt(this.ptr + this.sizeof * (long)i);
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
                        int v = LargeArrayUtils.UNSAFE.getInt(this.ptr + this.sizeof * i);
                        out[idx++] = v != 0;
                     }
                  } else if (this.isConstant) {
                     for(long i = startPos; i < endPos; i += step) {
                        out[idx++] = this.data[0] != 0;
                     }
                  } else {
                     for(long i = startPos; i < endPos; i += step) {
                        int v = this.data[(int)i];
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

   public final byte[] getByteData() {
      if (this.length > 1073741824L) {
         return null;
      } else {
         byte[] out = new byte[(int)this.length];
         if (this.ptr != 0L) {
            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = (byte)LargeArrayUtils.UNSAFE.getInt(this.ptr + this.sizeof * (long)i);
            }
         } else if (this.isConstant) {
            int elem = this.data[0];

            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = (byte)elem;
            }
         } else {
            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = (byte)this.data[i];
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
                        out[idx++] = (byte)LargeArrayUtils.UNSAFE.getInt(this.ptr + this.sizeof * i);
                     }
                  } else if (this.isConstant) {
                     for(long i = startPos; i < endPos; i += step) {
                        out[idx++] = (byte)this.data[0];
                     }
                  } else if (this.isConstant) {
                     for(long i = startPos; i < endPos; i += step) {
                        out[idx++] = (byte)this.data[0];
                     }
                  } else {
                     for(long i = startPos; i < endPos; i += step) {
                        out[idx++] = (byte)this.data[(int)i];
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
               out[i] = (short)LargeArrayUtils.UNSAFE.getInt(this.ptr + this.sizeof * (long)i);
            }
         } else if (this.isConstant) {
            int elem = this.data[0];

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
                        out[idx++] = (short)LargeArrayUtils.UNSAFE.getInt(this.ptr + this.sizeof * i);
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
               out[i] = LargeArrayUtils.UNSAFE.getInt(this.ptr + this.sizeof * (long)i);
            }
         } else if (this.isConstant) {
            int elem = this.data[0];

            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = elem;
            }
         } else {
            System.arraycopy(this.data, 0, out, 0, (int)this.length);
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
                        out[idx++] = LargeArrayUtils.UNSAFE.getInt(this.ptr + this.sizeof * i);
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
               out[i] = (long)LargeArrayUtils.UNSAFE.getInt(this.ptr + this.sizeof * (long)i);
            }
         } else if (this.isConstant) {
            int elem = this.data[0];

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
                        out[idx++] = (long)LargeArrayUtils.UNSAFE.getInt(this.ptr + this.sizeof * i);
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
               out[i] = (float)LargeArrayUtils.UNSAFE.getInt(this.ptr + this.sizeof * (long)i);
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
                        out[idx++] = (float)LargeArrayUtils.UNSAFE.getInt(this.ptr + this.sizeof * i);
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
               out[i] = (double)LargeArrayUtils.UNSAFE.getInt(this.ptr + this.sizeof * (long)i);
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
                        out[idx++] = (double)LargeArrayUtils.UNSAFE.getInt(this.ptr + this.sizeof * i);
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
      LargeArrayUtils.UNSAFE.putInt(this.ptr + this.sizeof * i, (Integer)value);
   }

   public final void setBoolean(long i, boolean value) {
      if (this.ptr != 0L) {
         LargeArrayUtils.UNSAFE.putInt(this.ptr + this.sizeof * i, value ? 1 : 0);
      } else {
         if (this.isConstant) {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
         }

         this.data[(int)i] = value ? 1 : 0;
      }

   }

   public final void setByte(long i, byte value) {
      if (this.ptr != 0L) {
         LargeArrayUtils.UNSAFE.putInt(this.ptr + this.sizeof * i, value);
      } else {
         if (this.isConstant) {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
         }

         this.data[(int)i] = value;
      }

   }

   public final void setUnsignedByte(long i, short value) {
      this.setShort(i, value);
   }

   public final void setShort(long i, short value) {
      if (this.ptr != 0L) {
         LargeArrayUtils.UNSAFE.putInt(this.ptr + this.sizeof * i, value);
      } else {
         if (this.isConstant) {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
         }

         this.data[(int)i] = value;
      }

   }

   public final void setInt(long i, int value) {
      if (this.ptr != 0L) {
         LargeArrayUtils.UNSAFE.putInt(this.ptr + this.sizeof * i, value);
      } else {
         if (this.isConstant) {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
         }

         this.data[(int)i] = value;
      }

   }

   public final void setLong(long i, long value) {
      if (this.ptr != 0L) {
         LargeArrayUtils.UNSAFE.putInt(this.ptr + this.sizeof * i, (int)value);
      } else {
         if (this.isConstant) {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
         }

         this.data[(int)i] = (int)value;
      }

   }

   public final void setFloat(long i, float value) {
      if (this.ptr != 0L) {
         LargeArrayUtils.UNSAFE.putInt(this.ptr + this.sizeof * i, (int)value);
      } else {
         if (this.isConstant) {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
         }

         this.data[(int)i] = (int)value;
      }

   }

   public final void setDouble(long i, double value) {
      if (this.ptr != 0L) {
         LargeArrayUtils.UNSAFE.putInt(this.ptr + this.sizeof * i, (int)value);
      } else {
         if (this.isConstant) {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
         }

         this.data[(int)i] = (int)value;
      }

   }
}
