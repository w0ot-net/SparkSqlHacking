package io.jsonwebtoken.impl.io;

import io.jsonwebtoken.impl.lang.Bytes;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FilteredOutputStream extends FilterOutputStream {
   public FilteredOutputStream(OutputStream out) {
      super(out);
   }

   protected void afterWrite(int n) throws IOException {
   }

   protected void beforeWrite(int n) throws IOException {
   }

   public void close() throws IOException {
      try {
         super.close();
      } catch (Throwable t) {
         this.onThrowable(t);
      }

   }

   public void flush() throws IOException {
      try {
         this.out.flush();
      } catch (Throwable t) {
         this.onThrowable(t);
      }

   }

   protected void onThrowable(Throwable t) throws IOException {
      if (t instanceof IOException) {
         throw (IOException)t;
      } else {
         throw new IOException("IO Exception " + t.getMessage(), t);
      }
   }

   public void write(byte[] bts) throws IOException {
      try {
         int len = Bytes.length(bts);
         this.beforeWrite(len);
         this.out.write(bts);
         this.afterWrite(len);
      } catch (Throwable t) {
         this.onThrowable(t);
      }

   }

   public void write(byte[] bts, int st, int end) throws IOException {
      try {
         this.beforeWrite(end);
         this.out.write(bts, st, end);
         this.afterWrite(end);
      } catch (Throwable t) {
         this.onThrowable(t);
      }

   }

   public void write(int idx) throws IOException {
      try {
         this.beforeWrite(1);
         this.out.write(idx);
         this.afterWrite(1);
      } catch (Throwable t) {
         this.onThrowable(t);
      }

   }
}
