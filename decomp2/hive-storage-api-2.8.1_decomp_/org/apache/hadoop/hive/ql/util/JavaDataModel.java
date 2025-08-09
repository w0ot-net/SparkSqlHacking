package org.apache.hadoop.hive.ql.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum JavaDataModel {
   JAVA32 {
      public int object() {
         return 16;
      }

      public int array() {
         return 20;
      }

      public int ref() {
         return 4;
      }

      public int hashMap(int entry) {
         return this.hashMapBase() + this.hashMapEntry() * entry;
      }

      public int hashMapBase() {
         return 64;
      }

      public int hashMapEntry() {
         return 24;
      }

      public int hashSet(int entry) {
         return this.hashSetBase() + this.hashSetEntry() * entry;
      }

      public int hashSetBase() {
         return 80;
      }

      public int hashSetEntry() {
         return 24;
      }

      public int linkedHashMap(int entry) {
         return 72 + 32 * entry;
      }

      public int linkedList(int entry) {
         return this.linkedListBase() + this.linkedListEntry() * entry;
      }

      public int linkedListBase() {
         return 28;
      }

      public int linkedListEntry() {
         return 24;
      }

      public int arrayList() {
         return 44;
      }

      public int memoryAlign() {
         return 8;
      }
   },
   JAVA64 {
      public int object() {
         return 32;
      }

      public int array() {
         return 40;
      }

      public int ref() {
         return 8;
      }

      public int hashMap(int entry) {
         return this.hashMapBase() + this.hashMapEntry() * entry;
      }

      public int hashMapBase() {
         return 112;
      }

      public int hashMapEntry() {
         return 44;
      }

      public int hashSet(int entry) {
         return this.hashSetBase() + this.hashSetEntry() * entry;
      }

      public int hashSetBase() {
         return 144;
      }

      public int hashSetEntry() {
         return 44;
      }

      public int linkedHashMap(int entry) {
         return 128 + 60 * entry;
      }

      public int linkedList(int entry) {
         return this.linkedListBase() + this.linkedListEntry() * entry;
      }

      public int linkedListBase() {
         return 48;
      }

      public int linkedListEntry() {
         return 48;
      }

      public int arrayList() {
         return 80;
      }

      public int memoryAlign() {
         return 8;
      }
   };

   private static final Logger LOG = LoggerFactory.getLogger(JavaDataModel.class);
   public static final int JAVA32_META = 12;
   public static final int JAVA32_ARRAY_META = 16;
   public static final int JAVA32_REF = 4;
   public static final int JAVA32_OBJECT = 16;
   public static final int JAVA32_ARRAY = 20;
   public static final int JAVA64_META = 24;
   public static final int JAVA64_ARRAY_META = 32;
   public static final int JAVA64_REF = 8;
   public static final int JAVA64_OBJECT = 32;
   public static final int JAVA64_ARRAY = 40;
   public static final int PRIMITIVES1 = 4;
   public static final int PRIMITIVES2 = 8;
   public static final int PRIMITIVE_BYTE = 1;

   private JavaDataModel() {
   }

   public abstract int object();

   public abstract int array();

   public abstract int ref();

   public abstract int hashMap(int var1);

   public abstract int hashMapBase();

   public abstract int hashMapEntry();

   public abstract int hashSetBase();

   public abstract int hashSetEntry();

   public abstract int hashSet(int var1);

   public abstract int linkedHashMap(int var1);

   public abstract int linkedListBase();

   public abstract int linkedListEntry();

   public abstract int linkedList(int var1);

   public abstract int arrayList();

   public abstract int memoryAlign();

   public long lengthFor(String string) {
      return (long)this.lengthForStringOfLength(string.length());
   }

   public int lengthForRandom() {
      return this.object() + this.primitive1() + this.primitive2() + this.object() + this.primitive2();
   }

   public int primitive1() {
      return 4;
   }

   public int primitive2() {
      return 8;
   }

   public static long alignUp(long value, long align) {
      return value + align - 1L & ~(align - 1L);
   }

   static JavaDataModel getModelForSystem() {
      String props = null;

      try {
         props = System.getProperty("sun.arch.data.model");
      } catch (Exception e) {
         LOG.warn("Failed to determine java data model, defaulting to 64", e);
      }

      return "32".equals(props) ? JAVA32 : JAVA64;
   }

   public static JavaDataModel get() {
      return JavaDataModel.LazyHolder.MODEL_FOR_SYSTEM;
   }

   public static int round(int size) {
      JavaDataModel model = get();
      return model != JAVA32 && size % 8 != 0 ? size + 8 >> 3 << 3 : size;
   }

   public long lengthForPrimitiveArrayOfSize(int primitiveSize, long length) {
      return alignUp((long)this.array() + (long)primitiveSize * length, (long)this.memoryAlign());
   }

   public long lengthForByteArrayOfSize(long length) {
      return this.lengthForPrimitiveArrayOfSize(1, length);
   }

   public long lengthForObjectArrayOfSize(long length) {
      return this.lengthForPrimitiveArrayOfSize(this.ref(), length);
   }

   public long lengthForLongArrayOfSize(long length) {
      return this.lengthForPrimitiveArrayOfSize(this.primitive2(), length);
   }

   public long lengthForDoubleArrayOfSize(long length) {
      return this.lengthForPrimitiveArrayOfSize(this.primitive2(), length);
   }

   public long lengthForIntArrayOfSize(long length) {
      return this.lengthForPrimitiveArrayOfSize(this.primitive1(), length);
   }

   public long lengthForBooleanArrayOfSize(long length) {
      return this.lengthForPrimitiveArrayOfSize(1, length);
   }

   public long lengthForTimestampArrayOfSize(long length) {
      return this.lengthForPrimitiveArrayOfSize(this.lengthOfTimestamp(), length);
   }

   public long lengthForDateArrayOfSize(long length) {
      return this.lengthForPrimitiveArrayOfSize(this.lengthOfDate(), length);
   }

   public long lengthForDecimalArrayOfSize(long length) {
      return this.lengthForPrimitiveArrayOfSize(this.lengthOfDecimal(), length);
   }

   public int lengthOfDecimal() {
      return this.object() + 2 * this.primitive2() + this.lengthOfBigInteger();
   }

   private int lengthOfBigInteger() {
      return this.object() + 4 * this.primitive2();
   }

   public int lengthOfTimestamp() {
      return this.object() + this.primitive2();
   }

   public int lengthOfDate() {
      return this.object() + 3 * this.primitive2();
   }

   public int lengthForStringOfLength(int strLen) {
      return this.object() + this.primitive1() * 3 + this.array() + strLen;
   }

   private static final class LazyHolder {
      private static final JavaDataModel MODEL_FOR_SYSTEM = JavaDataModel.getModelForSystem();
   }
}
