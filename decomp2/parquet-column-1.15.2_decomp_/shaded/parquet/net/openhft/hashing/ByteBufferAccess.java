package shaded.parquet.net.openhft.hashing;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class ByteBufferAccess extends Access {
   public static final ByteBufferAccess INSTANCE = new ByteBufferAccess();
   private static final Access INSTANCE_REVERSE;

   private ByteBufferAccess() {
   }

   public long getLong(ByteBuffer input, long offset) {
      return input.getLong((int)offset);
   }

   public long getUnsignedInt(ByteBuffer input, long offset) {
      return Primitives.unsignedInt(this.getInt(input, offset));
   }

   public int getInt(ByteBuffer input, long offset) {
      return input.getInt((int)offset);
   }

   public int getUnsignedShort(ByteBuffer input, long offset) {
      return Primitives.unsignedShort(this.getShort(input, offset));
   }

   public int getShort(ByteBuffer input, long offset) {
      return input.getShort((int)offset);
   }

   public int getUnsignedByte(ByteBuffer input, long offset) {
      return Primitives.unsignedByte(this.getByte(input, offset));
   }

   public int getByte(ByteBuffer input, long offset) {
      return input.get((int)offset);
   }

   public ByteOrder byteOrder(ByteBuffer input) {
      return input.order();
   }

   protected Access reverseAccess() {
      return INSTANCE_REVERSE;
   }

   static {
      INSTANCE_REVERSE = Access.newDefaultReverseAccess(INSTANCE);
   }
}
