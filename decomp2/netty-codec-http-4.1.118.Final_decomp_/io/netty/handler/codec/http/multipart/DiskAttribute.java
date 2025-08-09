package io.netty.handler.codec.http.multipart;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelException;
import io.netty.handler.codec.http.HttpConstants;
import io.netty.util.internal.ObjectUtil;
import java.io.IOException;
import java.nio.charset.Charset;

public class DiskAttribute extends AbstractDiskHttpData implements Attribute {
   public static String baseDirectory;
   public static boolean deleteOnExitTemporaryFile = true;
   public static final String prefix = "Attr_";
   public static final String postfix = ".att";
   private String baseDir;
   private boolean deleteOnExit;

   public DiskAttribute(String name) {
      this(name, HttpConstants.DEFAULT_CHARSET);
   }

   public DiskAttribute(String name, String baseDir, boolean deleteOnExit) {
      this(name, HttpConstants.DEFAULT_CHARSET);
      this.baseDir = baseDir == null ? baseDirectory : baseDir;
      this.deleteOnExit = deleteOnExit;
   }

   public DiskAttribute(String name, long definedSize) {
      this(name, definedSize, HttpConstants.DEFAULT_CHARSET, baseDirectory, deleteOnExitTemporaryFile);
   }

   public DiskAttribute(String name, long definedSize, String baseDir, boolean deleteOnExit) {
      this(name, definedSize, HttpConstants.DEFAULT_CHARSET);
      this.baseDir = baseDir == null ? baseDirectory : baseDir;
      this.deleteOnExit = deleteOnExit;
   }

   public DiskAttribute(String name, Charset charset) {
      this(name, charset, baseDirectory, deleteOnExitTemporaryFile);
   }

   public DiskAttribute(String name, Charset charset, String baseDir, boolean deleteOnExit) {
      super(name, charset, 0L);
      this.baseDir = baseDir == null ? baseDirectory : baseDir;
      this.deleteOnExit = deleteOnExit;
   }

   public DiskAttribute(String name, long definedSize, Charset charset) {
      this(name, definedSize, charset, baseDirectory, deleteOnExitTemporaryFile);
   }

   public DiskAttribute(String name, long definedSize, Charset charset, String baseDir, boolean deleteOnExit) {
      super(name, charset, definedSize);
      this.baseDir = baseDir == null ? baseDirectory : baseDir;
      this.deleteOnExit = deleteOnExit;
   }

   public DiskAttribute(String name, String value) throws IOException {
      this(name, value, HttpConstants.DEFAULT_CHARSET);
   }

   public DiskAttribute(String name, String value, Charset charset) throws IOException {
      this(name, value, charset, baseDirectory, deleteOnExitTemporaryFile);
   }

   public DiskAttribute(String name, String value, Charset charset, String baseDir, boolean deleteOnExit) throws IOException {
      super(name, charset, 0L);
      this.setValue(value);
      this.baseDir = baseDir == null ? baseDirectory : baseDir;
      this.deleteOnExit = deleteOnExit;
   }

   public InterfaceHttpData.HttpDataType getHttpDataType() {
      return InterfaceHttpData.HttpDataType.Attribute;
   }

   public String getValue() throws IOException {
      byte[] bytes = this.get();
      return new String(bytes, this.getCharset());
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
      long newDefinedSize = this.size + (long)buffer.readableBytes();

      try {
         this.checkSize(newDefinedSize);
      } catch (IOException e) {
         buffer.release();
         throw e;
      }

      if (this.definedSize > 0L && this.definedSize < newDefinedSize) {
         this.definedSize = newDefinedSize;
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

   public int compareTo(InterfaceHttpData o) {
      if (!(o instanceof Attribute)) {
         throw new ClassCastException("Cannot compare " + this.getHttpDataType() + " with " + o.getHttpDataType());
      } else {
         return this.compareTo((Attribute)o);
      }
   }

   public int compareTo(Attribute o) {
      return this.getName().compareToIgnoreCase(o.getName());
   }

   public String toString() {
      try {
         return this.getName() + '=' + this.getValue();
      } catch (IOException e) {
         return this.getName() + '=' + e;
      }
   }

   protected boolean deleteOnExit() {
      return this.deleteOnExit;
   }

   protected String getBaseDirectory() {
      return this.baseDir;
   }

   protected String getDiskFilename() {
      return this.getName() + ".att";
   }

   protected String getPostfix() {
      return ".att";
   }

   protected String getPrefix() {
      return "Attr_";
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
      DiskAttribute attr = new DiskAttribute(this.getName(), this.baseDir, this.deleteOnExit);
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

   public Attribute retain(int increment) {
      super.retain(increment);
      return this;
   }

   public Attribute retain() {
      super.retain();
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
