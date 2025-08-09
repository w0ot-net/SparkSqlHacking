package io.netty.handler.codec.http.multipart;

import io.netty.buffer.ByteBuf;
import java.nio.charset.Charset;

public class MixedFileUpload extends AbstractMixedHttpData implements FileUpload {
   public MixedFileUpload(String name, String filename, String contentType, String contentTransferEncoding, Charset charset, long size, long limitSize) {
      this(name, filename, contentType, contentTransferEncoding, charset, size, limitSize, DiskFileUpload.baseDirectory, DiskFileUpload.deleteOnExitTemporaryFile);
   }

   public MixedFileUpload(String name, String filename, String contentType, String contentTransferEncoding, Charset charset, long size, long limitSize, String baseDir, boolean deleteOnExit) {
      super(limitSize, baseDir, deleteOnExit, (HttpData)(size > limitSize ? new DiskFileUpload(name, filename, contentType, contentTransferEncoding, charset, size, baseDir, deleteOnExit) : new MemoryFileUpload(name, filename, contentType, contentTransferEncoding, charset, size)));
   }

   public String getContentTransferEncoding() {
      return ((FileUpload)this.wrapped).getContentTransferEncoding();
   }

   public String getFilename() {
      return ((FileUpload)this.wrapped).getFilename();
   }

   public void setContentTransferEncoding(String contentTransferEncoding) {
      ((FileUpload)this.wrapped).setContentTransferEncoding(contentTransferEncoding);
   }

   public void setFilename(String filename) {
      ((FileUpload)this.wrapped).setFilename(filename);
   }

   public void setContentType(String contentType) {
      ((FileUpload)this.wrapped).setContentType(contentType);
   }

   public String getContentType() {
      return ((FileUpload)this.wrapped).getContentType();
   }

   FileUpload makeDiskData() {
      DiskFileUpload diskFileUpload = new DiskFileUpload(this.getName(), this.getFilename(), this.getContentType(), this.getContentTransferEncoding(), this.getCharset(), this.definedLength(), this.baseDir, this.deleteOnExit);
      diskFileUpload.setMaxSize(this.getMaxSize());
      return diskFileUpload;
   }

   public FileUpload copy() {
      return (FileUpload)super.copy();
   }

   public FileUpload duplicate() {
      return (FileUpload)super.duplicate();
   }

   public FileUpload retainedDuplicate() {
      return (FileUpload)super.retainedDuplicate();
   }

   public FileUpload replace(ByteBuf content) {
      return (FileUpload)super.replace(content);
   }

   public FileUpload touch() {
      return (FileUpload)super.touch();
   }

   public FileUpload touch(Object hint) {
      return (FileUpload)super.touch(hint);
   }

   public FileUpload retain() {
      return (FileUpload)super.retain();
   }

   public FileUpload retain(int increment) {
      return (FileUpload)super.retain(increment);
   }
}
