package breeze.util;

import java.util.Arrays;
import scala.MatchError;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public final class ArrayUtil$ {
   public static final ArrayUtil$ MODULE$ = new ArrayUtil$();

   public void fill(final Object a, final int offset, final int length, final Object v) {
      if (a instanceof double[]) {
         double[] var7 = (double[])a;
         Arrays.fill(var7, offset, offset + length, BoxesRunTime.unboxToDouble(v));
         BoxedUnit var5 = BoxedUnit.UNIT;
      } else if (a instanceof int[]) {
         int[] var8 = (int[])a;
         Arrays.fill(var8, offset, offset + length, BoxesRunTime.unboxToInt(v));
         BoxedUnit var16 = BoxedUnit.UNIT;
      } else if (a instanceof float[]) {
         float[] var9 = (float[])a;
         Arrays.fill(var9, offset, offset + length, BoxesRunTime.unboxToFloat(v));
         BoxedUnit var17 = BoxedUnit.UNIT;
      } else if (a instanceof long[]) {
         long[] var10 = (long[])a;
         Arrays.fill(var10, offset, offset + length, BoxesRunTime.unboxToLong(v));
         BoxedUnit var18 = BoxedUnit.UNIT;
      } else if (a instanceof short[]) {
         short[] var11 = (short[])a;
         Arrays.fill(var11, offset, offset + length, BoxesRunTime.unboxToShort(v));
         BoxedUnit var19 = BoxedUnit.UNIT;
      } else if (a instanceof char[]) {
         char[] var12 = (char[])a;
         Arrays.fill(var12, offset, offset + length, BoxesRunTime.unboxToChar(v));
         BoxedUnit var20 = BoxedUnit.UNIT;
      } else if (a instanceof byte[]) {
         byte[] var13 = (byte[])a;
         Arrays.fill(var13, offset, offset + length, BoxesRunTime.unboxToByte(v));
         BoxedUnit var21 = BoxedUnit.UNIT;
      } else if (a instanceof boolean[]) {
         boolean[] var14 = (boolean[])a;
         Arrays.fill(var14, offset, offset + length, BoxesRunTime.unboxToBoolean(v));
         BoxedUnit var22 = BoxedUnit.UNIT;
      } else {
         if (a == null) {
            throw new MatchError(a);
         }

         Arrays.fill(a, offset, offset + length, v);
         BoxedUnit var23 = BoxedUnit.UNIT;
      }

   }

   public Object copyOf(final Object a, final int length) {
      Object var3;
      if (a instanceof double[]) {
         double[] var5 = (double[])a;
         var3 = Arrays.copyOf(var5, length);
      } else if (a instanceof int[]) {
         int[] var6 = (int[])a;
         var3 = Arrays.copyOf(var6, length);
      } else if (a instanceof float[]) {
         float[] var7 = (float[])a;
         var3 = Arrays.copyOf(var7, length);
      } else if (a instanceof long[]) {
         long[] var8 = (long[])a;
         var3 = Arrays.copyOf(var8, length);
      } else if (a instanceof short[]) {
         short[] var9 = (short[])a;
         var3 = Arrays.copyOf(var9, length);
      } else if (a instanceof char[]) {
         char[] var10 = (char[])a;
         var3 = Arrays.copyOf(var10, length);
      } else if (a instanceof byte[]) {
         byte[] var11 = (byte[])a;
         var3 = Arrays.copyOf(var11, length);
      } else if (a instanceof boolean[]) {
         boolean[] var12 = (boolean[])a;
         var3 = Arrays.copyOf(var12, length);
      } else {
         if (a == null) {
            throw new MatchError(a);
         }

         var3 = Arrays.copyOf(a, length);
      }

      return var3;
   }

   public Object copyOfRange(final Object a, final int from, final int to) {
      Object var4;
      if (a instanceof double[]) {
         double[] var6 = (double[])a;
         var4 = Arrays.copyOfRange(var6, from, to);
      } else if (a instanceof int[]) {
         int[] var7 = (int[])a;
         var4 = Arrays.copyOfRange(var7, from, to);
      } else if (a instanceof float[]) {
         float[] var8 = (float[])a;
         var4 = Arrays.copyOfRange(var8, from, to);
      } else if (a instanceof long[]) {
         long[] var9 = (long[])a;
         var4 = Arrays.copyOfRange(var9, from, to);
      } else if (a instanceof short[]) {
         short[] var10 = (short[])a;
         var4 = Arrays.copyOfRange(var10, from, to);
      } else if (a instanceof char[]) {
         char[] var11 = (char[])a;
         var4 = Arrays.copyOfRange(var11, from, to);
      } else if (a instanceof byte[]) {
         byte[] var12 = (byte[])a;
         var4 = Arrays.copyOfRange(var12, from, to);
      } else if (a instanceof boolean[]) {
         boolean[] var13 = (boolean[])a;
         var4 = Arrays.copyOfRange(var13, from, to);
      } else {
         if (a == null) {
            throw new MatchError(a);
         }

         var4 = Arrays.copyOfRange(a, from, to);
      }

      return var4;
   }

   public Object newArrayLike(final Object a, final int length) {
      Object var3;
      if (a instanceof double[]) {
         var3 = new double[length];
      } else if (a instanceof int[]) {
         var3 = new int[length];
      } else if (a instanceof float[]) {
         var3 = new float[length];
      } else if (a instanceof long[]) {
         var3 = new long[length];
      } else if (a instanceof short[]) {
         var3 = new short[length];
      } else if (a instanceof char[]) {
         var3 = new char[length];
      } else if (a instanceof byte[]) {
         var3 = new byte[length];
      } else if (a instanceof boolean[]) {
         var3 = new boolean[length];
      } else {
         if (a == null) {
            throw new MatchError(a);
         }

         ClassTag man = ReflectionUtil$.MODULE$.elemClassTagFromArray(a);
         var3 = man.newArray(length);
      }

      return var3;
   }

   public Object fillNewArrayLike(final Object a, final int length, final Object fill) {
      Object arr = this.newArrayLike(a, length);
      this.fill(arr, 0, length, fill);
      return arr;
   }

   public Object fillNewArray(final int length, final Object fill, final ClassTag evidence$1) {
      Object arr = evidence$1.newArray(length);
      this.fill(arr, 0, length, fill);
      return arr;
   }

   public void sort(final Object a) {
      if (a instanceof double[]) {
         double[] var4 = (double[])a;
         Arrays.sort(var4);
         BoxedUnit var2 = BoxedUnit.UNIT;
      } else if (a instanceof int[]) {
         int[] var5 = (int[])a;
         Arrays.sort(var5);
         BoxedUnit var12 = BoxedUnit.UNIT;
      } else if (a instanceof float[]) {
         float[] var6 = (float[])a;
         Arrays.sort(var6);
         BoxedUnit var13 = BoxedUnit.UNIT;
      } else if (a instanceof long[]) {
         long[] var7 = (long[])a;
         Arrays.sort(var7);
         BoxedUnit var14 = BoxedUnit.UNIT;
      } else if (a instanceof short[]) {
         short[] var8 = (short[])a;
         Arrays.sort(var8);
         BoxedUnit var15 = BoxedUnit.UNIT;
      } else if (a instanceof char[]) {
         char[] var9 = (char[])a;
         Arrays.sort(var9);
         BoxedUnit var16 = BoxedUnit.UNIT;
      } else if (a instanceof byte[]) {
         byte[] var10 = (byte[])a;
         Arrays.sort(var10);
         BoxedUnit var17 = BoxedUnit.UNIT;
      } else {
         if (a == null) {
            throw new MatchError(a);
         }

         Arrays.sort(a);
         BoxedUnit var18 = BoxedUnit.UNIT;
      }

   }

   public boolean nonstupidEquals(final Object a, final int aoffset, final int astride, final int alength, final Object b, final int boffset, final int bstride, final int blength) {
      boolean var10000;
      label28: {
         Class ac = a.getClass();
         Class bc = b.getClass();
         if (ac == null) {
            if (bc != null) {
               break label28;
            }
         } else if (!ac.equals(bc)) {
            break label28;
         }

         if (alength == blength) {
            boolean var9;
            if (a instanceof double[]) {
               double[] var14 = (double[])a;
               var9 = this.nonstupidEquals_Double(var14, aoffset, astride, alength, (double[])b, boffset, bstride);
            } else if (a instanceof float[]) {
               float[] var15 = (float[])a;
               var9 = this.nonstupidEquals_Float(var15, aoffset, astride, alength, (float[])b, boffset, bstride);
            } else {
               var9 = this.equals(a, aoffset, astride, alength, b, boffset, bstride, blength);
            }

            var10000 = var9;
            return var10000;
         }
      }

      var10000 = false;
      return var10000;
   }

   private boolean nonstupidEquals_Double(final double[] a, final int aoffset, final int astride, final int alength, final double[] b, final int boffset, final int bstride) {
      int ai = aoffset;
      int bi = boffset;
      int index$macro$2 = 0;

      for(int limit$macro$4 = alength; index$macro$2 < limit$macro$4; ++index$macro$2) {
         if (a[ai] != b[bi]) {
            return false;
         }

         ai += astride;
         bi += bstride;
      }

      return true;
   }

   private boolean nonstupidEquals_Float(final float[] a, final int aoffset, final int astride, final int alength, final float[] b, final int boffset, final int bstride) {
      int ai = aoffset;
      int bi = boffset;
      int index$macro$2 = 0;

      for(int limit$macro$4 = alength; index$macro$2 < limit$macro$4; ++index$macro$2) {
         if (a[ai] != b[bi]) {
            return false;
         }

         ai += astride;
         bi += bstride;
      }

      return true;
   }

   public boolean equals(final Object a, final Object b) {
      boolean var10000;
      label63: {
         Class ac = a.getClass();
         Class bc = b.getClass();
         if (ac == null) {
            if (bc != null) {
               break label63;
            }
         } else if (!ac.equals(bc)) {
            break label63;
         }

         boolean var3;
         if (a instanceof double[]) {
            var3 = Arrays.equals((double[])a, (double[])b);
         } else if (a instanceof int[]) {
            var3 = Arrays.equals((int[])a, (int[])b);
         } else if (a instanceof float[]) {
            var3 = Arrays.equals((float[])a, (float[])b);
         } else if (a instanceof boolean[]) {
            var3 = Arrays.equals((boolean[])a, (boolean[])b);
         } else if (a instanceof long[]) {
            var3 = Arrays.equals((long[])a, (long[])b);
         } else if (a instanceof short[]) {
            var3 = Arrays.equals((short[])a, (short[])b);
         } else if (a instanceof char[]) {
            var3 = Arrays.equals((char[])a, (char[])b);
         } else if (a instanceof byte[]) {
            var3 = Arrays.equals((byte[])a, (byte[])b);
         } else {
            if (a == null) {
               throw new MatchError(a);
            }

            var3 = Arrays.equals(a, b);
         }

         var10000 = var3;
         return var10000;
      }

      var10000 = false;
      return var10000;
   }

   public boolean equals(final Object a, final int aoffset, final int astride, final int alength, final Object b, final int boffset, final int bstride, final int blength) {
      boolean var10000;
      label198: {
         Class ac = a.getClass();
         Class bc = b.getClass();
         if (ac == null) {
            if (bc != null) {
               break label198;
            }
         } else if (!ac.equals(bc)) {
            break label198;
         }

         if (alength == blength) {
            if (aoffset == 0 && astride == 1 && alength == .MODULE$.array_length(a) && boffset == 0 && bstride == 1 && blength == .MODULE$.array_length(b)) {
               var10000 = this.equals(a, b);
            } else {
               boolean var9;
               if (a instanceof double[]) {
                  double[] var14 = (double[])a;
                  double[] y = (double[])b;
                  int ai = aoffset;
                  int bi = boffset;

                  for(int i = 0; i < alength; ++i) {
                     if (var14[ai] != y[bi]) {
                        return false;
                     }

                     ai += astride;
                     bi += bstride;
                  }

                  var9 = true;
               } else if (a instanceof int[]) {
                  int[] var19 = (int[])a;
                  int[] y = (int[])b;
                  int ai = aoffset;
                  int bi = boffset;

                  for(int i = 0; i < alength; ++i) {
                     if (var19[ai] != y[bi]) {
                        return false;
                     }

                     ai += astride;
                     bi += bstride;
                  }

                  var9 = true;
               } else if (a instanceof float[]) {
                  float[] var24 = (float[])a;
                  float[] y = (float[])b;
                  int ai = aoffset;
                  int bi = boffset;

                  for(int i = 0; i < alength; ++i) {
                     if (var24[ai] != y[bi]) {
                        return false;
                     }

                     ai += astride;
                     bi += bstride;
                  }

                  var9 = true;
               } else if (a instanceof long[]) {
                  long[] var29 = (long[])a;
                  long[] y = (long[])b;
                  int ai = aoffset;
                  int bi = boffset;

                  for(int i = 0; i < alength; ++i) {
                     if (var29[ai] != y[bi]) {
                        return false;
                     }

                     ai += astride;
                     bi += bstride;
                  }

                  var9 = true;
               } else if (a instanceof short[]) {
                  short[] var34 = (short[])a;
                  short[] y = (short[])b;
                  int ai = aoffset;
                  int bi = boffset;

                  for(int i = 0; i < alength; ++i) {
                     if (var34[ai] != y[bi]) {
                        return false;
                     }

                     ai += astride;
                     bi += bstride;
                  }

                  var9 = true;
               } else if (a instanceof char[]) {
                  char[] var39 = (char[])a;
                  char[] y = (char[])b;
                  int ai = aoffset;
                  int bi = boffset;

                  for(int i = 0; i < alength; ++i) {
                     if (var39[ai] != y[bi]) {
                        return false;
                     }

                     ai += astride;
                     bi += bstride;
                  }

                  var9 = true;
               } else if (a instanceof byte[]) {
                  byte[] var44 = (byte[])a;
                  byte[] y = (byte[])b;
                  int ai = aoffset;
                  int bi = boffset;

                  for(int i = 0; i < alength; ++i) {
                     if (var44[ai] != y[bi]) {
                        return false;
                     }

                     ai += astride;
                     bi += bstride;
                  }

                  var9 = true;
               } else if (a instanceof boolean[]) {
                  boolean[] var49 = (boolean[])a;
                  boolean[] y = (boolean[])b;
                  int ai = aoffset;
                  int bi = boffset;

                  for(int i = 0; i < alength; ++i) {
                     if (var49[ai] != y[bi]) {
                        return false;
                     }

                     ai += astride;
                     bi += bstride;
                  }

                  var9 = true;
               } else {
                  if (a == null) {
                     throw new MatchError(a);
                  }

                  Object var54 = a;
                  Object[] y = b;
                  int ai = aoffset;
                  int bi = boffset;

                  for(int i = 0; i < alength; ++i) {
                     if (!BoxesRunTime.equals(.MODULE$.array_apply(var54, ai), y[bi])) {
                        return false;
                     }

                     ai += astride;
                     bi += bstride;
                  }

                  var9 = true;
               }

               var10000 = var9;
            }

            return var10000;
         }
      }

      var10000 = false;
      return var10000;
   }

   public int gallopSearch(final int[] objs, final int fromIndex, final int toIndex, final int toFind) {
      if (objs.length == 0) {
         return ~0;
      } else {
         int low = fromIndex;
         int step = 1;

         int high;
         for(high = fromIndex + step; high < toIndex && objs[high] < toFind; high = fromIndex + step) {
            low = high;
            step *= 2;
         }

         return high < toIndex && objs[high] == toFind ? high : Arrays.binarySearch(objs, low, scala.math.package..MODULE$.min(high, toIndex), toFind);
      }
   }

   public int zeroSkippingHashCode(final Object data, final int offset, final int stride, final int length) {
      int var5;
      if (data instanceof double[]) {
         double[] var7 = (double[])data;
         var5 = this.zeroSkippingHashCodeImpl_Double(var7, offset, stride, length);
      } else if (data instanceof float[]) {
         float[] var8 = (float[])data;
         var5 = this.zeroSkippingHashCodeImpl_Float(var8, offset, stride, length);
      } else if (data instanceof int[]) {
         int[] var9 = (int[])data;
         var5 = this.zeroSkippingHashCodeImpl_Int(var9, offset, stride, length);
      } else if (data instanceof long[]) {
         long[] var10 = (long[])data;
         var5 = this.zeroSkippingHashCodeImpl_Long(var10, offset, stride, length);
      } else if (data instanceof short[]) {
         short[] var11 = (short[])data;
         var5 = this.zeroSkippingHashCodeImpl_Short(var11, offset, stride, length);
      } else if (data instanceof byte[]) {
         byte[] var12 = (byte[])data;
         var5 = this.zeroSkippingHashCodeImpl_Byte(var12, offset, stride, length);
      } else if (data instanceof char[]) {
         char[] var13 = (char[])data;
         var5 = this.zeroSkippingHashCodeImpl_Char(var13, offset, stride, length);
      } else if (data instanceof boolean[]) {
         boolean[] var14 = (boolean[])data;
         var5 = this.zeroSkippingHashCodeImpl_Boolean(var14, offset, stride, length);
      } else {
         var5 = this.zeroSkippingHashCodeImplSlow(data, offset, stride, length);
      }

      return var5;
   }

   private int zeroSkippingHashCodeImpl_Int(final int[] data, final int offset, final int stride, final int length) {
      int hash = 43;
      int i = offset;
      int index$macro$2 = 0;

      for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
         int v = data[i];
         if (v != 0) {
            hash = scala.util.hashing.MurmurHash3..MODULE$.mix(hash, v);
         }

         i += stride;
      }

      return hash;
   }

   private int zeroSkippingHashCodeImpl_Float(final float[] data, final int offset, final int stride, final int length) {
      int hash = 43;
      int i = offset;
      int index$macro$2 = 0;

      for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
         float v = data[i];
         int hh = Statics.floatHash(v);
         if (hh != 0) {
            hash = scala.util.hashing.MurmurHash3..MODULE$.mix(hash, hh);
         }

         i += stride;
      }

      return hash;
   }

   private int zeroSkippingHashCodeImpl_Double(final double[] data, final int offset, final int stride, final int length) {
      int hash = 43;
      int i = offset;
      int index$macro$2 = 0;

      for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
         double v = data[i];
         int hh = Statics.doubleHash(v);
         if (hh != 0) {
            hash = scala.util.hashing.MurmurHash3..MODULE$.mix(hash, hh);
         }

         i += stride;
      }

      return hash;
   }

   private int zeroSkippingHashCodeImpl_Long(final long[] data, final int offset, final int stride, final int length) {
      int hash = 43;
      int i = offset;
      int index$macro$2 = 0;

      for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
         long v = data[i];
         int hh = Statics.longHash(v);
         if (hh != 0) {
            hash = scala.util.hashing.MurmurHash3..MODULE$.mix(hash, hh);
         }

         i += stride;
      }

      return hash;
   }

   private int zeroSkippingHashCodeImpl_Byte(final byte[] data, final int offset, final int stride, final int length) {
      int hash = 43;
      int i = offset;
      int index$macro$2 = 0;

      for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
         byte v = data[i];
         if (v != 0) {
            hash = scala.util.hashing.MurmurHash3..MODULE$.mix(hash, v);
         }

         i += stride;
      }

      return hash;
   }

   private int zeroSkippingHashCodeImpl_Short(final short[] data, final int offset, final int stride, final int length) {
      int hash = 43;
      int i = offset;
      int index$macro$2 = 0;

      for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
         short v = data[i];
         if (v != 0) {
            hash = scala.util.hashing.MurmurHash3..MODULE$.mix(hash, v);
         }

         i += stride;
      }

      return hash;
   }

   private int zeroSkippingHashCodeImpl_Char(final char[] data, final int offset, final int stride, final int length) {
      int hash = 43;
      int i = offset;
      int index$macro$2 = 0;

      for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
         char v = data[i];
         if (v != 0) {
            hash = scala.util.hashing.MurmurHash3..MODULE$.mix(hash, v);
         }

         i += stride;
      }

      return hash;
   }

   private int zeroSkippingHashCodeImpl_Boolean(final boolean[] data, final int offset, final int stride, final int length) {
      int hash = 43;
      int i = offset;
      int index$macro$2 = 0;

      for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
         boolean v = data[i];
         int hh = v ? 1231 : 1237;
         if (hh != 0) {
            hash = scala.util.hashing.MurmurHash3..MODULE$.mix(hash, hh);
         }

         i += stride;
      }

      return hash;
   }

   private int zeroSkippingHashCodeImplSlow(final Object data, final int offset, final int stride, final int length) {
      int hash = 43;
      int i = offset;
      int index$macro$2 = 0;

      for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
         Object v = .MODULE$.array_apply(data, i);
         int hh = Statics.anyHash(v);
         if (hh != 0) {
            hash = scala.util.hashing.MurmurHash3..MODULE$.mix(hash, hh);
         }

         i += stride;
      }

      return hash;
   }

   public int[] range(final int start, final int end, final int stride) {
      int length = (end - start + stride - 1) / stride;
      if (length <= 0) {
         return new int[0];
      } else {
         int[] result = new int[length];
         int x = start;
         int index$macro$2 = 0;

         for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
            result[index$macro$2] = x;
            x += stride;
         }

         return result;
      }
   }

   public int range$default$3() {
      return 1;
   }

   private ArrayUtil$() {
   }
}
