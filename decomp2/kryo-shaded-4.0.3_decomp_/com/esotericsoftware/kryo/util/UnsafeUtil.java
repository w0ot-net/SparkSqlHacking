package com.esotericsoftware.kryo.util;

import com.esotericsoftware.minlog.Log;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import sun.misc.Cleaner;
import sun.misc.Unsafe;
import sun.nio.ch.DirectBuffer;

public class UnsafeUtil {
   private static final Unsafe _unsafe;
   public static final long byteArrayBaseOffset;
   public static final long floatArrayBaseOffset;
   public static final long doubleArrayBaseOffset;
   public static final long intArrayBaseOffset;
   public static final long longArrayBaseOffset;
   public static final long shortArrayBaseOffset;
   public static final long charArrayBaseOffset;
   static Constructor directByteBufferConstr;

   public static final Unsafe unsafe() {
      return _unsafe;
   }

   public static Field[] sortFieldsByOffset(List allFields) {
      Field[] allFieldsArray = (Field[])allFields.toArray(new Field[0]);
      Comparator<Field> fieldOffsetComparator = new Comparator() {
         public int compare(Field f1, Field f2) {
            long offset1 = UnsafeUtil.unsafe().objectFieldOffset(f1);
            long offset2 = UnsafeUtil.unsafe().objectFieldOffset(f2);
            if (offset1 < offset2) {
               return -1;
            } else {
               return offset1 == offset2 ? 0 : 1;
            }
         }
      };
      Arrays.sort(allFieldsArray, fieldOffsetComparator);

      for(Field f : allFields) {
         if (Log.TRACE) {
            Log.trace("kryo", "Field '" + f.getName() + "' at offset " + unsafe().objectFieldOffset(f));
         }
      }

      return allFieldsArray;
   }

   public static final ByteBuffer getDirectBufferAt(long address, int size) {
      if (directByteBufferConstr == null) {
         return null;
      } else {
         try {
            return (ByteBuffer)directByteBufferConstr.newInstance(address, size, null);
         } catch (Exception e) {
            throw new RuntimeException("Cannot allocate ByteBuffer at a given address: " + address, e);
         }
      }
   }

   public static void releaseBuffer(ByteBuffer niobuffer) {
      if (niobuffer != null && niobuffer.isDirect()) {
         Object cleaner = ((DirectBuffer)niobuffer).cleaner();
         if (cleaner != null) {
            ((Cleaner)cleaner).clean();
         }

         ByteBuffer var2 = null;
      }

   }

   static {
      Unsafe tmpUnsafe = null;
      long tmpByteArrayBaseOffset = 0L;
      long tmpFloatArrayBaseOffset = 0L;
      long tmpDoubleArrayBaseOffset = 0L;
      long tmpIntArrayBaseOffset = 0L;
      long tmpLongArrayBaseOffset = 0L;
      long tmpShortArrayBaseOffset = 0L;
      long tmpCharArrayBaseOffset = 0L;

      try {
         if (!Util.IS_ANDROID) {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            tmpUnsafe = (Unsafe)field.get((Object)null);
            tmpByteArrayBaseOffset = (long)tmpUnsafe.arrayBaseOffset(byte[].class);
            tmpCharArrayBaseOffset = (long)tmpUnsafe.arrayBaseOffset(char[].class);
            tmpShortArrayBaseOffset = (long)tmpUnsafe.arrayBaseOffset(short[].class);
            tmpIntArrayBaseOffset = (long)tmpUnsafe.arrayBaseOffset(int[].class);
            tmpFloatArrayBaseOffset = (long)tmpUnsafe.arrayBaseOffset(float[].class);
            tmpLongArrayBaseOffset = (long)tmpUnsafe.arrayBaseOffset(long[].class);
            tmpDoubleArrayBaseOffset = (long)tmpUnsafe.arrayBaseOffset(double[].class);
         } else if (Log.TRACE) {
            Log.trace("kryo", "Running on Android platform. Use of sun.misc.Unsafe should be disabled");
         }
      } catch (Exception var17) {
         if (Log.TRACE) {
            Log.trace("kryo", "sun.misc.Unsafe is not accessible or not available. Use of sun.misc.Unsafe should be disabled");
         }
      }

      byteArrayBaseOffset = tmpByteArrayBaseOffset;
      charArrayBaseOffset = tmpCharArrayBaseOffset;
      shortArrayBaseOffset = tmpShortArrayBaseOffset;
      intArrayBaseOffset = tmpIntArrayBaseOffset;
      floatArrayBaseOffset = tmpFloatArrayBaseOffset;
      longArrayBaseOffset = tmpLongArrayBaseOffset;
      doubleArrayBaseOffset = tmpDoubleArrayBaseOffset;
      _unsafe = tmpUnsafe;
      ByteBuffer buf = ByteBuffer.allocateDirect(1);

      try {
         directByteBufferConstr = buf.getClass().getDeclaredConstructor(Long.TYPE, Integer.TYPE, Object.class);
         directByteBufferConstr.setAccessible(true);
      } catch (Exception var16) {
         directByteBufferConstr = null;
      }

   }
}
