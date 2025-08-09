package io.netty.buffer;

import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.StringUtil;

public class DefaultByteBufHolder implements ByteBufHolder {
   private final ByteBuf data;

   public DefaultByteBufHolder(ByteBuf data) {
      this.data = (ByteBuf)ObjectUtil.checkNotNull(data, "data");
   }

   public ByteBuf content() {
      return ByteBufUtil.ensureAccessible(this.data);
   }

   public ByteBufHolder copy() {
      return this.replace(this.data.copy());
   }

   public ByteBufHolder duplicate() {
      return this.replace(this.data.duplicate());
   }

   public ByteBufHolder retainedDuplicate() {
      return this.replace(this.data.retainedDuplicate());
   }

   public ByteBufHolder replace(ByteBuf content) {
      return new DefaultByteBufHolder(content);
   }

   public int refCnt() {
      return this.data.refCnt();
   }

   public ByteBufHolder retain() {
      this.data.retain();
      return this;
   }

   public ByteBufHolder retain(int increment) {
      this.data.retain(increment);
      return this;
   }

   public ByteBufHolder touch() {
      this.data.touch();
      return this;
   }

   public ByteBufHolder touch(Object hint) {
      this.data.touch(hint);
      return this;
   }

   public boolean release() {
      return this.data.release();
   }

   public boolean release(int decrement) {
      return this.data.release(decrement);
   }

   protected final String contentToString() {
      return this.data.toString();
   }

   public String toString() {
      return StringUtil.simpleClassName(this) + '(' + this.contentToString() + ')';
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else {
         return o != null && this.getClass() == o.getClass() ? this.data.equals(((DefaultByteBufHolder)o).data) : false;
      }
   }

   public int hashCode() {
      return this.data.hashCode();
   }
}
