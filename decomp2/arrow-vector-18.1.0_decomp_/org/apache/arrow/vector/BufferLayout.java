package org.apache.arrow.vector;

import org.apache.arrow.util.Preconditions;

public class BufferLayout {
   private static final BufferLayout VALIDITY_BUFFER;
   private static final BufferLayout OFFSET_BUFFER;
   private static final BufferLayout LARGE_OFFSET_BUFFER;
   private static final BufferLayout TYPE_BUFFER;
   private static final BufferLayout BIT_BUFFER;
   private static final BufferLayout VALUES_256;
   private static final BufferLayout VALUES_128;
   private static final BufferLayout VALUES_64;
   private static final BufferLayout VALUES_32;
   private static final BufferLayout VALUES_16;
   private static final BufferLayout VALUES_8;
   private static final BufferLayout LARGE_SIZE_BUFFER;
   private static final BufferLayout SIZE_BUFFER;
   private static final BufferLayout VIEW_BUFFER;
   private final short typeBitWidth;
   private final BufferType type;

   public static BufferLayout typeBuffer() {
      return TYPE_BUFFER;
   }

   public static BufferLayout offsetBuffer() {
      return OFFSET_BUFFER;
   }

   public static BufferLayout largeOffsetBuffer() {
      return LARGE_OFFSET_BUFFER;
   }

   public static BufferLayout sizeBuffer() {
      return SIZE_BUFFER;
   }

   public static BufferLayout largeSizeBuffer() {
      return LARGE_SIZE_BUFFER;
   }

   public static BufferLayout dataBuffer(int typeBitWidth) {
      switch (typeBitWidth) {
         case 8:
            return VALUES_8;
         case 16:
            return VALUES_16;
         case 32:
            return VALUES_32;
         case 64:
            return VALUES_64;
         case 128:
            return VALUES_128;
         case 256:
            return VALUES_256;
         default:
            throw new IllegalArgumentException("only 8, 16, 32, 64, 128, or 256 bits supported");
      }
   }

   public static BufferLayout booleanVector() {
      return BIT_BUFFER;
   }

   public static BufferLayout validityVector() {
      return VALIDITY_BUFFER;
   }

   public static BufferLayout byteVector() {
      return dataBuffer(8);
   }

   public static BufferLayout viewVector() {
      return VIEW_BUFFER;
   }

   BufferLayout(BufferType type, int typeBitWidth) {
      this.type = (BufferType)Preconditions.checkNotNull(type);
      this.typeBitWidth = (short)typeBitWidth;
      if (typeBitWidth <= 0) {
         throw new IllegalArgumentException("bitWidth invalid: " + typeBitWidth);
      }
   }

   public int getTypeBitWidth() {
      return this.typeBitWidth;
   }

   public BufferType getType() {
      return this.type;
   }

   public String toString() {
      return String.format("%s(%s)", this.type, this.typeBitWidth);
   }

   public int hashCode() {
      return 31 * (31 + this.type.hashCode()) + this.typeBitWidth;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (!(obj instanceof BufferLayout)) {
         return false;
      } else {
         BufferLayout other = (BufferLayout)obj;
         return this.type.equals(other.type) && this.typeBitWidth == other.typeBitWidth;
      }
   }

   static {
      VALIDITY_BUFFER = new BufferLayout(BufferLayout.BufferType.VALIDITY, 1);
      OFFSET_BUFFER = new BufferLayout(BufferLayout.BufferType.OFFSET, 32);
      LARGE_OFFSET_BUFFER = new BufferLayout(BufferLayout.BufferType.OFFSET, 64);
      TYPE_BUFFER = new BufferLayout(BufferLayout.BufferType.TYPE, 32);
      BIT_BUFFER = new BufferLayout(BufferLayout.BufferType.DATA, 1);
      VALUES_256 = new BufferLayout(BufferLayout.BufferType.DATA, 256);
      VALUES_128 = new BufferLayout(BufferLayout.BufferType.DATA, 128);
      VALUES_64 = new BufferLayout(BufferLayout.BufferType.DATA, 64);
      VALUES_32 = new BufferLayout(BufferLayout.BufferType.DATA, 32);
      VALUES_16 = new BufferLayout(BufferLayout.BufferType.DATA, 16);
      VALUES_8 = new BufferLayout(BufferLayout.BufferType.DATA, 8);
      LARGE_SIZE_BUFFER = new BufferLayout(BufferLayout.BufferType.SIZE, 64);
      SIZE_BUFFER = new BufferLayout(BufferLayout.BufferType.SIZE, 32);
      VIEW_BUFFER = new BufferLayout(BufferLayout.BufferType.VIEWS, 16);
   }

   public static enum BufferType {
      DATA("DATA"),
      OFFSET("OFFSET"),
      VALIDITY("VALIDITY"),
      TYPE("TYPE_ID"),
      SIZE("SIZE"),
      VIEWS("VIEWS"),
      VARIADIC_DATA_BUFFERS("VARIADIC_DATA_BUFFERS");

      private final String name;

      private BufferType(String name) {
         this.name = name;
      }

      public String getName() {
         return this.name;
      }

      // $FF: synthetic method
      private static BufferType[] $values() {
         return new BufferType[]{DATA, OFFSET, VALIDITY, TYPE, SIZE, VIEWS, VARIADIC_DATA_BUFFERS};
      }
   }
}
