package org.apache.parquet.column.values.bitpacking;

public enum Packer {
   BIG_ENDIAN {
      public IntPacker newIntPacker(int width) {
         return beIntPackerFactory.newIntPacker(width);
      }

      public BytePacker newBytePacker(int width) {
         return beBytePackerFactory.newBytePacker(width);
      }

      public BytePacker newBytePackerVector(int width) {
         throw new RuntimeException("Not currently supported!");
      }

      public BytePackerForLong newBytePackerForLong(int width) {
         return beBytePackerForLongFactory.newBytePackerForLong(width);
      }
   },
   LITTLE_ENDIAN {
      public IntPacker newIntPacker(int width) {
         return leIntPackerFactory.newIntPacker(width);
      }

      public BytePacker newBytePacker(int width) {
         return leBytePackerFactory.newBytePacker(width);
      }

      public BytePacker newBytePackerVector(int width) {
         if (leBytePacker512VectorFactory == null) {
            synchronized(Packer.class) {
               if (leBytePacker512VectorFactory == null) {
                  leBytePacker512VectorFactory = Packer.getBytePackerFactory("ByteBitPacking512VectorLE");
               }
            }
         }

         if (leBytePacker512VectorFactory == null) {
            throw new RuntimeException("No enable java vector plugin on little endian architectures");
         } else {
            return leBytePacker512VectorFactory.newBytePacker(width);
         }
      }

      public BytePackerForLong newBytePackerForLong(int width) {
         return leBytePackerForLongFactory.newBytePackerForLong(width);
      }
   };

   static IntPackerFactory beIntPackerFactory = getIntPackerFactory("LemireBitPackingBE");
   static IntPackerFactory leIntPackerFactory = getIntPackerFactory("LemireBitPackingLE");
   static BytePackerFactory beBytePackerFactory = getBytePackerFactory("ByteBitPackingBE");
   static BytePackerFactory leBytePackerFactory = getBytePackerFactory("ByteBitPackingLE");
   static BytePackerFactory leBytePacker512VectorFactory = null;
   static BytePackerForLongFactory beBytePackerForLongFactory = getBytePackerForLongFactory("ByteBitPackingForLongBE");
   static BytePackerForLongFactory leBytePackerForLongFactory = getBytePackerForLongFactory("ByteBitPackingForLongLE");

   private Packer() {
   }

   private static IntPackerFactory getIntPackerFactory(String name) {
      return (IntPackerFactory)getStaticField("org.apache.parquet.column.values.bitpacking." + name, "factory");
   }

   private static BytePackerFactory getBytePackerFactory(String name) {
      return (BytePackerFactory)getStaticField("org.apache.parquet.column.values.bitpacking." + name, "factory");
   }

   private static BytePackerForLongFactory getBytePackerForLongFactory(String name) {
      return (BytePackerForLongFactory)getStaticField("org.apache.parquet.column.values.bitpacking." + name, "factory");
   }

   private static Object getStaticField(String className, String fieldName) {
      try {
         return Class.forName(className).getField(fieldName).get((Object)null);
      } catch (IllegalAccessException | NoSuchFieldException | SecurityException | ClassNotFoundException | IllegalArgumentException e) {
         throw new RuntimeException(e);
      }
   }

   public abstract IntPacker newIntPacker(int var1);

   public abstract BytePacker newBytePacker(int var1);

   public BytePacker newBytePackerVector(int width) {
      throw new RuntimeException("newBytePackerVector must be implemented by subclasses!");
   }

   public abstract BytePackerForLong newBytePackerForLong(int var1);
}
