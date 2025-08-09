package pl.edu.icm.jlargearrays;

import java.io.UnsupportedEncodingException;
import org.apache.commons.math3.util.FastMath;
import sun.misc.Cleaner;

public class StringLargeArray extends LargeArray {
   private static final long serialVersionUID = -4096759496772248522L;
   private String[] data;
   private ShortLargeArray stringLengths;
   private int maxStringLength;
   private long size;
   private byte[] byteArray;
   private static final String CHARSET = "UTF-8";
   private static final int CHARSET_SIZE = 4;

   public StringLargeArray(long length) {
      this(length, 100);
   }

   public StringLargeArray(long length, int maxStringLength) {
      this(length, maxStringLength, true);
   }

   public StringLargeArray(long length, int maxStringLength, boolean zeroNativeMemory) {
      this.type = LargeArrayType.STRING;
      this.sizeof = 1L;
      if (length <= 0L) {
         throw new IllegalArgumentException(length + " is not a positive long value.");
      } else if (maxStringLength <= 0) {
         throw new IllegalArgumentException(maxStringLength + " is not a positive int value.");
      } else {
         this.length = length;
         this.size = length * (long)maxStringLength * 4L;
         this.maxStringLength = maxStringLength;
         if (length > (long)getMaxSizeOf32bitArray()) {
            this.ptr = LargeArrayUtils.UNSAFE.allocateMemory(this.size * this.sizeof);
            if (zeroNativeMemory) {
               this.zeroNativeMemory(this.size);
            }

            Cleaner.create(this, new LargeArray.Deallocator(this.ptr, this.size, this.sizeof));
            MemoryCounter.increaseCounter(this.size * this.sizeof);
            this.stringLengths = new ShortLargeArray(length);
            this.byteArray = new byte[maxStringLength * 4];
         } else {
            this.data = new String[(int)length];
         }

      }
   }

   public StringLargeArray(long length, String constantValue) {
      this.type = LargeArrayType.STRING;
      this.sizeof = 1L;
      if (length <= 0L) {
         throw new IllegalArgumentException(length + " is not a positive long value");
      } else {
         this.length = length;
         this.isConstant = true;
         this.data = new String[]{constantValue};
      }
   }

   public StringLargeArray(String[] data) {
      this.type = LargeArrayType.STRING;
      this.sizeof = 1L;
      this.length = (long)data.length;
      this.data = data;
   }

   public StringLargeArray clone() {
      if (this.isConstant) {
         return new StringLargeArray(this.length, this.get(0L));
      } else {
         StringLargeArray v = new StringLargeArray(this.length, FastMath.max(1, this.maxStringLength), false);
         LargeArrayUtils.arraycopy(this, 0L, v, 0L, this.length);
         return v;
      }
   }

   public boolean equals(Object o) {
      if (!super.equals(o)) {
         return false;
      } else {
         StringLargeArray la = (StringLargeArray)o;
         boolean res = this.maxStringLength == la.maxStringLength && this.data == la.data;
         if (this.stringLengths != null && la.stringLengths != null) {
            return res && this.stringLengths.equals(la.stringLengths);
         } else {
            return this.stringLengths == la.stringLengths ? res : false;
         }
      }
   }

   public int hashCode() {
      int hash = 29 * super.hashCode() + (this.data != null ? this.data.hashCode() : 0);
      hash = 29 * hash + (this.maxStringLength ^ this.maxStringLength >>> 16);
      return 29 * hash + (this.stringLengths != null ? this.stringLengths.hashCode() : 0);
   }

   public final String get(long i) {
      if (this.ptr == 0L) {
         return this.isConstant ? this.data[0] : this.data[(int)i];
      } else {
         short strLen = this.stringLengths.getShort(i);
         if (strLen < 0) {
            return null;
         } else {
            long offset = this.sizeof * i * (long)this.maxStringLength * 4L;

            for(int j = 0; j < strLen; ++j) {
               this.byteArray[j] = LargeArrayUtils.UNSAFE.getByte(this.ptr + offset + this.sizeof * (long)j);
            }

            try {
               return new String(this.byteArray, 0, strLen, "UTF-8");
            } catch (UnsupportedEncodingException var7) {
               return null;
            }
         }
      }
   }

   public final String getFromNative(long i) {
      short strLen = this.stringLengths.getShort(i);
      if (strLen < 0) {
         return null;
      } else {
         long offset = this.sizeof * i * (long)this.maxStringLength * 4L;

         for(int j = 0; j < strLen; ++j) {
            this.byteArray[j] = LargeArrayUtils.UNSAFE.getByte(this.ptr + offset + this.sizeof * (long)j);
         }

         try {
            return new String(this.byteArray, 0, strLen, "UTF-8");
         } catch (UnsupportedEncodingException var7) {
            return null;
         }
      }
   }

   public final boolean getBoolean(long i) {
      String s = this.get(i);
      return s != null ? s.length() != 0 : false;
   }

   public final byte getByte(long i) {
      String s = this.get(i);
      return (byte)(s != null ? s.length() : 0);
   }

   public final short getUnsignedByte(long i) {
      String s = this.get(i);
      return (short)(s != null ? 255 & s.length() : 0);
   }

   public final short getShort(long i) {
      String s = this.get(i);
      return (short)(s != null ? s.length() : 0);
   }

   public final int getInt(long i) {
      String s = this.get(i);
      return s != null ? s.length() : 0;
   }

   public final long getLong(long i) {
      String s = this.get(i);
      return (long)(s != null ? s.length() : 0);
   }

   public final float getFloat(long i) {
      String s = this.get(i);
      return (float)(s != null ? s.length() : 0);
   }

   public final double getDouble(long i) {
      String s = this.get(i);
      return (double)(s != null ? s.length() : 0);
   }

   public final String[] getData() {
      return this.data;
   }

   public final boolean[] getBooleanData() {
      if (this.length > 1073741824L) {
         return null;
      } else {
         boolean[] out = new boolean[(int)this.length];
         if (this.ptr != 0L) {
            for(int i = 0; (long)i < this.length; ++i) {
               short strLen = this.stringLengths.getShort((long)i);
               out[i] = strLen != 0;
            }
         } else if (this.isConstant) {
            boolean elem = this.data[0] != null ? this.data[0].length() != 0 : false;

            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = elem;
            }
         } else {
            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = this.data[i] != null ? this.data[i].length() != 0 : false;
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
                        short strLen = this.stringLengths.getShort(i);
                        out[idx++] = strLen > 0;
                     }
                  } else if (this.isConstant) {
                     boolean elem = this.data[0] != null ? this.data[0].length() != 0 : false;

                     for(long i = startPos; i < endPos; i += step) {
                        out[idx++] = elem;
                     }
                  } else {
                     for(long i = startPos; i < endPos; i += step) {
                        int v = this.data[(int)i] != null ? this.data[(int)i].length() : 0;
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
               out[i] = (byte)this.stringLengths.getShort((long)i);
            }
         } else if (this.isConstant) {
            byte elem = (byte)(this.data[0] != null ? this.data[0].length() : 0);

            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = elem;
            }
         } else {
            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = (byte)(this.data[i] != null ? this.data[i].length() : 0);
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
                        out[idx++] = (byte)this.stringLengths.getShort(i);
                     }
                  } else if (this.isConstant) {
                     for(long i = startPos; i < endPos; i += step) {
                        out[idx++] = (byte)(this.data[0] != null ? this.data[0].length() : 0);
                     }
                  } else {
                     for(long i = startPos; i < endPos; i += step) {
                        out[idx++] = (byte)(this.data[(int)i] != null ? this.data[(int)i].length() : 0);
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
               out[i] = this.stringLengths.getShort((long)i);
            }
         } else if (this.isConstant) {
            short elem = (short)(this.data[0] != null ? this.data[0].length() : 0);

            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = elem;
            }
         } else {
            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = (short)(this.data[i] != null ? this.data[i].length() : 0);
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
                        out[idx++] = this.stringLengths.getShort(i);
                     }
                  } else if (this.isConstant) {
                     for(long i = startPos; i < endPos; i += step) {
                        out[idx++] = (short)(this.data[0] != null ? this.data[0].length() : 0);
                     }
                  } else {
                     for(long i = startPos; i < endPos; i += step) {
                        out[idx++] = (short)(this.data[(int)i] != null ? this.data[(int)i].length() : 0);
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
               out[i] = this.stringLengths.getShort((long)i);
            }
         } else if (this.isConstant) {
            int elem = this.data[0] != null ? this.data[0].length() : 0;

            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = elem;
            }
         } else {
            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = this.data[i] != null ? this.data[i].length() : 0;
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
                        out[idx++] = this.stringLengths.getShort(i);
                     }
                  } else if (this.isConstant) {
                     for(long i = startPos; i < endPos; i += step) {
                        out[idx++] = this.data[0] != null ? this.data[0].length() : 0;
                     }
                  } else {
                     for(long i = startPos; i < endPos; i += step) {
                        out[idx++] = this.data[(int)i] != null ? this.data[(int)i].length() : 0;
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
               out[i] = (long)this.stringLengths.getShort((long)i);
            }
         } else if (this.isConstant) {
            int elem = this.data[0] != null ? this.data[0].length() : 0;

            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = (long)elem;
            }
         } else {
            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = this.data[i] != null ? (long)this.data[i].length() : 0L;
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
                        out[idx++] = (long)this.stringLengths.getShort(i);
                     }
                  } else if (this.isConstant) {
                     for(long i = startPos; i < endPos; i += step) {
                        out[idx++] = (long)(this.data[0] != null ? this.data[0].length() : 0);
                     }
                  } else {
                     for(long i = startPos; i < endPos; i += step) {
                        out[idx++] = (long)(this.data[(int)i] != null ? this.data[(int)i].length() : 0);
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
               out[i] = (float)this.stringLengths.getShort((long)i);
            }
         } else if (this.isConstant) {
            int elem = this.data[0] != null ? this.data[0].length() : 0;

            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = (float)elem;
            }
         } else {
            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = this.data[i] != null ? (float)this.data[i].length() : 0.0F;
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
                        out[idx++] = (float)this.stringLengths.getShort(i);
                     }
                  } else if (this.isConstant) {
                     for(long i = startPos; i < endPos; i += step) {
                        out[idx++] = this.data[0] != null ? (float)this.data[0].length() : 0.0F;
                     }
                  } else {
                     for(long i = startPos; i < endPos; i += step) {
                        out[idx++] = (float)(this.data[(int)i] != null ? this.data[(int)i].length() : 0);
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
               out[i] = (double)this.stringLengths.getShort((long)i);
            }
         } else if (this.isConstant) {
            int elem = this.data[0] != null ? this.data[0].length() : 0;

            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = (double)elem;
            }
         } else {
            for(int i = 0; (long)i < this.length; ++i) {
               out[i] = this.data[i] != null ? (double)this.data[i].length() : (double)0.0F;
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
                        out[idx++] = (double)this.stringLengths.getShort(i);
                     }
                  } else if (this.isConstant) {
                     for(long i = startPos; i < endPos; i += step) {
                        out[idx++] = this.data[0] != null ? (double)this.data[0].length() : (double)0.0F;
                     }
                  } else {
                     for(long i = startPos; i < endPos; i += step) {
                        out[idx++] = (double)(this.data[(int)i] != null ? this.data[(int)i].length() : 0);
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

   public final void setToNative(long i, Object o) {
      if (o == null) {
         this.stringLengths.setShort(i, (short)-1);
      } else {
         if (!(o instanceof String)) {
            throw new IllegalArgumentException(o + " is not a string.");
         }

         String s = (String)o;
         if (s.length() > this.maxStringLength) {
            throw new IllegalArgumentException("String  " + s + " is too long.");
         }

         byte[] tmp;
         try {
            tmp = s.getBytes("UTF-8");
         } catch (UnsupportedEncodingException var10) {
            return;
         }

         int strLen = tmp.length;
         if (strLen > 32767) {
            throw new IllegalArgumentException("String  " + s + " is too long.");
         }

         this.stringLengths.setShort(i, (short)strLen);
         long offset = this.sizeof * i * (long)this.maxStringLength * 4L;

         for(int j = 0; j < strLen; ++j) {
            LargeArrayUtils.UNSAFE.putByte(this.ptr + offset + this.sizeof * (long)j, tmp[j]);
         }
      }

   }

   public final void set(long i, Object o) {
      if (o == null) {
         if (this.ptr != 0L) {
            this.stringLengths.setShort(i, (short)-1);
         } else {
            if (this.isConstant) {
               throw new IllegalAccessError("Constant arrays cannot be modified.");
            }

            this.data[(int)i] = null;
         }
      } else {
         if (!(o instanceof String)) {
            throw new IllegalArgumentException(o + " is not a string.");
         }

         String s = (String)o;
         if (this.ptr != 0L) {
            if (s.length() > this.maxStringLength) {
               throw new IllegalArgumentException("String  " + s + " is too long.");
            }

            byte[] tmp;
            try {
               tmp = s.getBytes("UTF-8");
            } catch (UnsupportedEncodingException var10) {
               return;
            }

            int strLen = tmp.length;
            if (strLen > 32767) {
               throw new IllegalArgumentException("String  " + s + " is too long.");
            }

            this.stringLengths.setShort(i, (short)strLen);
            long offset = this.sizeof * i * (long)this.maxStringLength * 4L;

            for(int j = 0; j < strLen; ++j) {
               LargeArrayUtils.UNSAFE.putByte(this.ptr + offset + this.sizeof * (long)j, tmp[j]);
            }
         } else {
            if (this.isConstant) {
               throw new IllegalAccessError("Constant arrays cannot be modified.");
            }

            this.data[(int)i] = s;
         }
      }

   }

   public final void set_safe(long i, Object o) {
      if (i >= 0L && i < this.length) {
         this.set(i, o);
      } else {
         throw new ArrayIndexOutOfBoundsException(Long.toString(i));
      }
   }

   public final void setBoolean(long i, boolean value) {
      this.set(i, Boolean.toString(value));
   }

   public final void setByte(long i, byte value) {
      this.set(i, Byte.toString(value));
   }

   public final void setUnsignedByte(long i, short value) {
      this.setShort(i, value);
   }

   public final void setShort(long i, short value) {
      this.set(i, Short.toString(value));
   }

   public final void setInt(long i, int value) {
      this.set(i, Integer.toString(value));
   }

   public final void setLong(long i, long value) {
      this.set(i, Long.toString(value));
   }

   public final void setFloat(long i, float value) {
      this.set(i, Float.toString(value));
   }

   public final void setDouble(long i, double value) {
      this.set(i, Double.toString(value));
   }

   public int getMaxStringLength() {
      return this.maxStringLength;
   }
}
