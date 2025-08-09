package io.netty.buffer;

public class LargeBuffer extends MutableWrappedByteBuf {
   public LargeBuffer(ByteBuf buffer) {
      super(buffer);
   }

   public ByteBuf copy(int index, int length) {
      return new LargeBuffer(this.buffer.copy(index, length));
   }
}
