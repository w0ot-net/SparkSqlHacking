package org.apache.derby.shared.common.util;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class ArrayUtil {
   public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

   public static Object[] copy(Object[] var0) {
      return var0 == null ? null : Arrays.copyOf(var0, var0.length);
   }

   public static boolean[] copy(boolean[] var0) {
      return var0 == null ? null : (boolean[])(([Z)var0).clone();
   }

   public static byte[] copy(byte[] var0) {
      return var0 == null ? null : (byte[])(([B)var0).clone();
   }

   public static int[] copy(int[] var0) {
      return var0 == null ? null : (int[])(([I)var0).clone();
   }

   public static long[] copy(long[] var0) {
      return var0 == null ? null : (long[])(([J)var0).clone();
   }

   public static int[][] copy2(int[][] var0) {
      if (var0 == null) {
         return null;
      } else {
         int[][] var1 = new int[var0.length][];

         for(int var2 = 0; var2 < var0.length; ++var2) {
            var1[var2] = copy(var0[var2]);
         }

         return var1;
      }
   }

   @SafeVarargs
   public static List asReadOnlyList(Object... var0) {
      return var0 == null ? Collections.emptyList() : Collections.unmodifiableList(Arrays.asList(var0));
   }

   public static void writeArrayLength(ObjectOutput var0, Object[] var1) throws IOException {
      var0.writeInt(var1.length);
   }

   public static void writeArrayItems(ObjectOutput var0, Object[] var1) throws IOException {
      if (var1 != null) {
         for(int var2 = 0; var2 < var1.length; ++var2) {
            var0.writeObject(var1[var2]);
         }

      }
   }

   public static void writeArray(ObjectOutput var0, Object[] var1) throws IOException {
      if (var1 == null) {
         var0.writeInt(0);
      } else {
         var0.writeInt(var1.length);

         for(int var2 = 0; var2 < var1.length; ++var2) {
            var0.writeObject(var1[var2]);
         }

      }
   }

   public static void readArrayItems(ObjectInput var0, Object[] var1) throws IOException, ClassNotFoundException {
      for(int var2 = 0; var2 < var1.length; ++var2) {
         var1[var2] = var0.readObject();
      }

   }

   public static int readArrayLength(ObjectInput var0) throws IOException {
      return var0.readInt();
   }

   public static Object[] readObjectArray(ObjectInput var0) throws IOException, ClassNotFoundException {
      int var1 = var0.readInt();
      if (var1 == 0) {
         return null;
      } else {
         Object[] var2 = new Object[var1];
         readArrayItems(var0, var2);
         return var2;
      }
   }

   public static void writeIntArray(ObjectOutput var0, int[] var1) throws IOException {
      if (var1 == null) {
         var0.writeInt(0);
      } else {
         var0.writeInt(var1.length);

         for(int var2 = 0; var2 < var1.length; ++var2) {
            var0.writeInt(var1[var2]);
         }
      }

   }

   public static int[] readIntArray(ObjectInput var0) throws IOException {
      int var1 = var0.readInt();
      if (var1 == 0) {
         return null;
      } else {
         int[] var2 = new int[var1];

         for(int var3 = 0; var3 < var1; ++var3) {
            var2[var3] = var0.readInt();
         }

         return var2;
      }
   }

   public static void writeInts(ObjectOutput var0, int[][] var1) throws IOException {
      if (var1 == null) {
         var0.writeBoolean(false);
      } else {
         var0.writeBoolean(true);
         int var2 = var1.length;
         var0.writeInt(var2);

         for(int var3 = 0; var3 < var2; ++var3) {
            writeIntArray(var0, var1[var3]);
         }
      }

   }

   public static int[][] readInts(ObjectInput var0) throws IOException, ClassNotFoundException {
      int[][] var1 = null;
      if (var0.readBoolean()) {
         int var2 = var0.readInt();
         var1 = new int[var2][];

         for(int var3 = 0; var3 < var2; ++var3) {
            var1[var3] = readIntArray(var0);
         }
      }

      return var1;
   }

   public static String toString(int[] var0) {
      if (var0 != null && var0.length != 0) {
         StringBuffer var1 = new StringBuffer();

         for(int var2 = 0; var2 < var0.length; ++var2) {
            var1.append("[").append(var0[var2]).append("],");
         }

         return var1.toString();
      } else {
         return "null";
      }
   }

   public static void writeLongArray(ObjectOutput var0, long[] var1) throws IOException {
      if (var1 == null) {
         var0.writeInt(0);
      } else {
         var0.writeInt(var1.length);

         for(int var2 = 0; var2 < var1.length; ++var2) {
            var0.writeLong(var1[var2]);
         }
      }

   }

   public static long[] readLongArray(ObjectInput var0) throws IOException {
      int var1 = var0.readInt();
      long[] var2 = new long[var1];

      for(int var3 = 0; var3 < var1; ++var3) {
         var2[var3] = var0.readLong();
      }

      return var2;
   }

   public static String[] readStringArray(ObjectInput var0) throws IOException, ClassNotFoundException {
      String[] var1 = null;
      int var2 = readArrayLength(var0);
      if (var2 > 0) {
         var1 = new String[var2];
         readArrayItems(var0, var1);
      }

      return var1;
   }

   public static void writeBooleanArray(ObjectOutput var0, boolean[] var1) throws IOException {
      if (var1 == null) {
         var0.writeInt(0);
      } else {
         var0.writeInt(var1.length);

         for(int var2 = 0; var2 < var1.length; ++var2) {
            var0.writeBoolean(var1[var2]);
         }
      }

   }

   public static boolean[] readBooleanArray(ObjectInput var0) throws IOException {
      int var1 = var0.readInt();
      boolean[] var2 = new boolean[var1];

      for(int var3 = 0; var3 < var1; ++var3) {
         var2[var3] = var0.readBoolean();
      }

      return var2;
   }
}
