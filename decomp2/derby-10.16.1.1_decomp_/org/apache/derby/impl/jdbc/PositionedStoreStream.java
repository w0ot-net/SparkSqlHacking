package org.apache.derby.impl.jdbc;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.derby.iapi.services.io.InputStreamUtil;
import org.apache.derby.iapi.types.PositionedStream;
import org.apache.derby.iapi.types.Resetable;
import org.apache.derby.shared.common.error.StandardException;

public class PositionedStoreStream extends InputStream implements PositionedStream, Resetable {
   private final InputStream stream;
   private long pos = 0L;

   public PositionedStoreStream(InputStream var1) throws IOException, StandardException {
      this.stream = var1;
      ((Resetable)var1).initStream();
      ((Resetable)var1).resetStream();
   }

   public int read(byte[] var1) throws IOException {
      return this.read(var1, 0, var1.length);
   }

   public int read(byte[] var1, int var2, int var3) throws IOException {
      int var4 = this.stream.read(var1, var2, var3);
      if (var4 > -1) {
         this.pos += (long)var4;
      }

      return var4;
   }

   public int read() throws IOException {
      int var1 = this.stream.read();
      if (var1 > -1) {
         ++this.pos;
      }

      return var1;
   }

   public long skip(long var1) throws IOException {
      long var3 = this.stream.skip(var1);
      this.pos += var3;
      return var3;
   }

   public void resetStream() throws IOException, StandardException {
      ((Resetable)this.stream).resetStream();
      this.pos = 0L;
   }

   public void initStream() throws StandardException {
      ((Resetable)this.stream).initStream();
   }

   public void closeStream() {
      ((Resetable)this.stream).closeStream();
   }

   public void reposition(long var1) throws IOException, StandardException {
      if (this.pos > var1) {
         this.resetStream();
      }

      if (this.pos < var1) {
         try {
            InputStreamUtil.skipFully(this.stream, var1 - this.pos);
         } catch (EOFException var4) {
            this.resetStream();
            throw var4;
         }

         this.pos = var1;
      }

   }

   public long getPosition() {
      return this.pos;
   }

   public InputStream asInputStream() {
      return this;
   }
}
