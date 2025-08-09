package org.apache.ivy.util;

public class CopyProgressEvent {
   private long totalReadBytes;
   private byte[] buffer;
   private int readBytes;

   public CopyProgressEvent() {
   }

   public CopyProgressEvent(byte[] buffer, int read, long total) {
      this.update(buffer, read, total);
   }

   public CopyProgressEvent(byte[] buffer, long total) {
      this.update(buffer, 0, total);
   }

   protected CopyProgressEvent update(byte[] buffer, int read, long total) {
      this.buffer = buffer;
      this.readBytes = read;
      this.totalReadBytes = total;
      return this;
   }

   public long getTotalReadBytes() {
      return this.totalReadBytes;
   }

   public byte[] getBuffer() {
      return this.buffer;
   }

   public int getReadBytes() {
      return this.readBytes;
   }
}
