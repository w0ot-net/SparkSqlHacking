package io.netty.handler.codec.http.multipart;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelException;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.util.internal.ObjectUtil;
import java.io.IOException;
import java.nio.charset.Charset;

public class MemoryFileUpload extends AbstractMemoryHttpData implements FileUpload {
   private String filename;
   private String contentType;
   private String contentTransferEncoding;

   public MemoryFileUpload(String name, String filename, String contentType, String contentTransferEncoding, Charset charset, long size) {
      super(name, charset, size);
      this.setFilename(filename);
      this.setContentType(contentType);
      this.setContentTransferEncoding(contentTransferEncoding);
   }

   public InterfaceHttpData.HttpDataType getHttpDataType() {
      return InterfaceHttpData.HttpDataType.FileUpload;
   }

   public String getFilename() {
      return this.filename;
   }

   public void setFilename(String filename) {
      this.filename = (String)ObjectUtil.checkNotNull(filename, "filename");
   }

   public int hashCode() {
      return FileUploadUtil.hashCode(this);
   }

   public boolean equals(Object o) {
      return o instanceof FileUpload && FileUploadUtil.equals(this, (FileUpload)o);
   }

   public int compareTo(InterfaceHttpData o) {
      if (!(o instanceof FileUpload)) {
         throw new ClassCastException("Cannot compare " + this.getHttpDataType() + " with " + o.getHttpDataType());
      } else {
         return this.compareTo((FileUpload)o);
      }
   }

   public int compareTo(FileUpload o) {
      return FileUploadUtil.compareTo(this, o);
   }

   public void setContentType(String contentType) {
      this.contentType = (String)ObjectUtil.checkNotNull(contentType, "contentType");
   }

   public String getContentType() {
      return this.contentType;
   }

   public String getContentTransferEncoding() {
      return this.contentTransferEncoding;
   }

   public void setContentTransferEncoding(String contentTransferEncoding) {
      this.contentTransferEncoding = contentTransferEncoding;
   }

   public String toString() {
      return HttpHeaderNames.CONTENT_DISPOSITION + ": " + HttpHeaderValues.FORM_DATA + "; " + HttpHeaderValues.NAME + "=\"" + this.getName() + "\"; " + HttpHeaderValues.FILENAME + "=\"" + this.filename + "\"\r\n" + HttpHeaderNames.CONTENT_TYPE + ": " + this.contentType + (this.getCharset() != null ? "; " + HttpHeaderValues.CHARSET + '=' + this.getCharset().name() + "\r\n" : "\r\n") + HttpHeaderNames.CONTENT_LENGTH + ": " + this.length() + "\r\nCompleted: " + this.isCompleted() + "\r\nIsInMemory: " + this.isInMemory();
   }

   public FileUpload copy() {
      ByteBuf content = this.content();
      return this.replace(content != null ? content.copy() : content);
   }

   public FileUpload duplicate() {
      ByteBuf content = this.content();
      return this.replace(content != null ? content.duplicate() : content);
   }

   public FileUpload retainedDuplicate() {
      ByteBuf content = this.content();
      if (content != null) {
         content = content.retainedDuplicate();
         boolean success = false;

         FileUpload var4;
         try {
            FileUpload duplicate = this.replace(content);
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

   public FileUpload replace(ByteBuf content) {
      MemoryFileUpload upload = new MemoryFileUpload(this.getName(), this.getFilename(), this.getContentType(), this.getContentTransferEncoding(), this.getCharset(), this.size);
      if (content != null) {
         try {
            upload.setContent(content);
         } catch (IOException e) {
            throw new ChannelException(e);
         }
      }

      upload.setCompleted(this.isCompleted());
      return upload;
   }

   public FileUpload retain() {
      super.retain();
      return this;
   }

   public FileUpload retain(int increment) {
      super.retain(increment);
      return this;
   }

   public FileUpload touch() {
      super.touch();
      return this;
   }

   public FileUpload touch(Object hint) {
      super.touch(hint);
      return this;
   }
}
