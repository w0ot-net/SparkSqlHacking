package com.sun.istack;

import jakarta.activation.DataSource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;

public final class ByteArrayDataSource implements DataSource {
   private final String contentType;
   private final byte[] buf;
   private final int len;

   public ByteArrayDataSource(byte[] buf, String contentType) {
      this(buf, buf.length, contentType);
   }

   public ByteArrayDataSource(byte[] buf, int length, String contentType) {
      this.buf = buf;
      this.len = length;
      this.contentType = contentType;
   }

   public String getContentType() {
      return this.contentType == null ? "application/octet-stream" : this.contentType;
   }

   public InputStream getInputStream() {
      return new ByteArrayInputStream(this.buf, 0, this.len);
   }

   public String getName() {
      return null;
   }

   public OutputStream getOutputStream() {
      throw new UnsupportedOperationException();
   }
}
