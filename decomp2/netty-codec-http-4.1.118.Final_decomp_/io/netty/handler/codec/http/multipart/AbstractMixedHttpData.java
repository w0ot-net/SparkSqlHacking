package io.netty.handler.codec.http.multipart;

import io.netty.buffer.ByteBuf;
import io.netty.util.AbstractReferenceCounted;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

abstract class AbstractMixedHttpData extends AbstractReferenceCounted implements HttpData {
   final String baseDir;
   final boolean deleteOnExit;
   HttpData wrapped;
   private final long limitSize;

   AbstractMixedHttpData(long limitSize, String baseDir, boolean deleteOnExit, HttpData initial) {
      this.limitSize = limitSize;
      this.wrapped = initial;
      this.baseDir = baseDir;
      this.deleteOnExit = deleteOnExit;
   }

   abstract HttpData makeDiskData();

   public long getMaxSize() {
      return this.wrapped.getMaxSize();
   }

   public void setMaxSize(long maxSize) {
      this.wrapped.setMaxSize(maxSize);
   }

   public ByteBuf content() {
      return this.wrapped.content();
   }

   public void checkSize(long newSize) throws IOException {
      this.wrapped.checkSize(newSize);
   }

   public long definedLength() {
      return this.wrapped.definedLength();
   }

   public Charset getCharset() {
      return this.wrapped.getCharset();
   }

   public String getName() {
      return this.wrapped.getName();
   }

   public void addContent(ByteBuf buffer, boolean last) throws IOException {
      if (this.wrapped instanceof AbstractMemoryHttpData) {
         try {
            this.checkSize(this.wrapped.length() + (long)buffer.readableBytes());
            if (this.wrapped.length() + (long)buffer.readableBytes() > this.limitSize) {
               D diskData = (D)this.makeDiskData();
               ByteBuf data = ((AbstractMemoryHttpData)this.wrapped).getByteBuf();
               if (data != null && data.isReadable()) {
                  diskData.addContent(data.retain(), false);
               }

               this.wrapped.release();
               this.wrapped = diskData;
            }
         } catch (IOException e) {
            buffer.release();
            throw e;
         }
      }

      this.wrapped.addContent(buffer, last);
   }

   protected void deallocate() {
      this.delete();
   }

   public void delete() {
      this.wrapped.delete();
   }

   public byte[] get() throws IOException {
      return this.wrapped.get();
   }

   public ByteBuf getByteBuf() throws IOException {
      return this.wrapped.getByteBuf();
   }

   public String getString() throws IOException {
      return this.wrapped.getString();
   }

   public String getString(Charset encoding) throws IOException {
      return this.wrapped.getString(encoding);
   }

   public boolean isInMemory() {
      return this.wrapped.isInMemory();
   }

   public long length() {
      return this.wrapped.length();
   }

   public boolean renameTo(File dest) throws IOException {
      return this.wrapped.renameTo(dest);
   }

   public void setCharset(Charset charset) {
      this.wrapped.setCharset(charset);
   }

   public void setContent(ByteBuf buffer) throws IOException {
      try {
         this.checkSize((long)buffer.readableBytes());
      } catch (IOException e) {
         buffer.release();
         throw e;
      }

      if ((long)buffer.readableBytes() > this.limitSize && this.wrapped instanceof AbstractMemoryHttpData) {
         this.wrapped.release();
         this.wrapped = this.makeDiskData();
      }

      this.wrapped.setContent(buffer);
   }

   public void setContent(File file) throws IOException {
      this.checkSize(file.length());
      if (file.length() > this.limitSize && this.wrapped instanceof AbstractMemoryHttpData) {
         this.wrapped.release();
         this.wrapped = this.makeDiskData();
      }

      this.wrapped.setContent(file);
   }

   public void setContent(InputStream inputStream) throws IOException {
      if (this.wrapped instanceof AbstractMemoryHttpData) {
         this.wrapped.release();
         this.wrapped = this.makeDiskData();
      }

      this.wrapped.setContent(inputStream);
   }

   public boolean isCompleted() {
      return this.wrapped.isCompleted();
   }

   public InterfaceHttpData.HttpDataType getHttpDataType() {
      return this.wrapped.getHttpDataType();
   }

   public int hashCode() {
      return this.wrapped.hashCode();
   }

   public boolean equals(Object obj) {
      return this.wrapped.equals(obj);
   }

   public int compareTo(InterfaceHttpData o) {
      return this.wrapped.compareTo(o);
   }

   public String toString() {
      return "Mixed: " + this.wrapped;
   }

   public ByteBuf getChunk(int length) throws IOException {
      return this.wrapped.getChunk(length);
   }

   public File getFile() throws IOException {
      return this.wrapped.getFile();
   }

   public HttpData copy() {
      return this.wrapped.copy();
   }

   public HttpData duplicate() {
      return this.wrapped.duplicate();
   }

   public HttpData retainedDuplicate() {
      return this.wrapped.retainedDuplicate();
   }

   public HttpData replace(ByteBuf content) {
      return this.wrapped.replace(content);
   }

   public HttpData touch() {
      this.wrapped.touch();
      return this;
   }

   public HttpData touch(Object hint) {
      this.wrapped.touch(hint);
      return this;
   }

   public HttpData retain() {
      return (HttpData)super.retain();
   }

   public HttpData retain(int increment) {
      return (HttpData)super.retain(increment);
   }
}
