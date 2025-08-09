package io.netty.handler.codec.http.multipart;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelException;
import io.netty.handler.codec.http.HttpConstants;
import io.netty.util.internal.ObjectUtil;
import java.io.IOException;
import java.nio.charset.Charset;

public class MemoryAttribute extends AbstractMemoryHttpData implements Attribute {
   public MemoryAttribute(String name) {
      this(name, HttpConstants.DEFAULT_CHARSET);
   }

   public MemoryAttribute(String name, long definedSize) {
      this(name, definedSize, HttpConstants.DEFAULT_CHARSET);
   }

   public MemoryAttribute(String name, Charset charset) {
      super(name, charset, 0L);
   }

   public MemoryAttribute(String name, long definedSize, Charset charset) {
      super(name, charset, definedSize);
   }

   public MemoryAttribute(String name, String value) throws IOException {
      this(name, value, HttpConstants.DEFAULT_CHARSET);
   }

   public MemoryAttribute(String name, String value, Charset charset) throws IOException {
      super(name, charset, 0L);
      this.setValue(value);
   }

   public InterfaceHttpData.HttpDataType getHttpDataType() {
      return InterfaceHttpData.HttpDataType.Attribute;
   }

   public String getValue() {
      return this.getByteBuf().toString(this.getCharset());
   }

   public void setValue(String value) throws IOException {
      ObjectUtil.checkNotNull(value, "value");
      byte[] bytes = value.getBytes(this.getCharset());
      this.checkSize((long)bytes.length);
      ByteBuf buffer = Unpooled.wrappedBuffer(bytes);
      if (this.definedSize > 0L) {
         this.definedSize = (long)buffer.readableBytes();
      }

      this.setContent(buffer);
   }

   public void addContent(ByteBuf buffer, boolean last) throws IOException {
      int localsize = buffer.readableBytes();

      try {
         this.checkSize(this.size + (long)localsize);
      } catch (IOException e) {
         buffer.release();
         throw e;
      }

      if (this.definedSize > 0L && this.definedSize < this.size + (long)localsize) {
         this.definedSize = this.size + (long)localsize;
      }

      super.addContent(buffer, last);
   }

   public int hashCode() {
      return this.getName().hashCode();
   }

   public boolean equals(Object o) {
      if (!(o instanceof Attribute)) {
         return false;
      } else {
         Attribute attribute = (Attribute)o;
         return this.getName().equalsIgnoreCase(attribute.getName());
      }
   }

   public int compareTo(InterfaceHttpData other) {
      if (!(other instanceof Attribute)) {
         throw new ClassCastException("Cannot compare " + this.getHttpDataType() + " with " + other.getHttpDataType());
      } else {
         return this.compareTo((Attribute)other);
      }
   }

   public int compareTo(Attribute o) {
      return this.getName().compareToIgnoreCase(o.getName());
   }

   public String toString() {
      return this.getName() + '=' + this.getValue();
   }

   public Attribute copy() {
      ByteBuf content = this.content();
      return this.replace(content != null ? content.copy() : null);
   }

   public Attribute duplicate() {
      ByteBuf content = this.content();
      return this.replace(content != null ? content.duplicate() : null);
   }

   public Attribute retainedDuplicate() {
      ByteBuf content = this.content();
      if (content != null) {
         content = content.retainedDuplicate();
         boolean success = false;

         Attribute var4;
         try {
            Attribute duplicate = this.replace(content);
            success = true;
            var4 = duplicate;
         } finally {
            if (!success) {
               content.release();
            }

         }

         return var4;
      } else {
         return this.replace((ByteBuf)null);
      }
   }

   public Attribute replace(ByteBuf content) {
      MemoryAttribute attr = new MemoryAttribute(this.getName());
      attr.setCharset(this.getCharset());
      if (content != null) {
         try {
            attr.setContent(content);
         } catch (IOException e) {
            throw new ChannelException(e);
         }
      }

      attr.setCompleted(this.isCompleted());
      return attr;
   }

   public Attribute retain() {
      super.retain();
      return this;
   }

   public Attribute retain(int increment) {
      super.retain(increment);
      return this;
   }

   public Attribute touch() {
      super.touch();
      return this;
   }

   public Attribute touch(Object hint) {
      super.touch(hint);
      return this;
   }
}
