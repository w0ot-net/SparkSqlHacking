package org.apache.derby.impl.jdbc;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.derby.iapi.types.PositionedStream;
import org.apache.derby.shared.common.error.ExceptionUtil;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.MessageService;

public class LOBInputStream extends InputStream implements PositionedStream {
   private boolean closed = false;
   private final LOBStreamControl control;
   private long pos;
   private long updateCount;

   LOBInputStream(LOBStreamControl var1, long var2) {
      this.control = var1;
      this.pos = var2;
      this.updateCount = var1.getUpdateCount();
   }

   public int read(byte[] var1, int var2, int var3) throws IOException {
      if (this.closed) {
         throw new IOException(MessageService.getTextMessage("J104", new Object[0]));
      } else {
         try {
            int var4 = this.control.read(var1, var2, var3, this.pos);
            if (var4 != -1) {
               this.pos += (long)var4;
               return var4;
            } else {
               return -1;
            }
         } catch (StandardException var6) {
            String var5 = var6.getSQLState();
            if (var5.equals(ExceptionUtil.getSQLStateFromIdentifier("XJ076.S"))) {
               return -1;
            } else if (var5.equals(ExceptionUtil.getSQLStateFromIdentifier("XJ078.S"))) {
               throw new ArrayIndexOutOfBoundsException(var6.getMessage());
            } else {
               throw Util.newIOException(var6);
            }
         }
      }
   }

   public void close() throws IOException {
      this.closed = true;
   }

   public int read() throws IOException {
      if (this.closed) {
         throw new IOException(MessageService.getTextMessage("J104", new Object[0]));
      } else {
         try {
            int var1 = this.control.read(this.pos);
            if (var1 != -1) {
               ++this.pos;
            }

            return var1;
         } catch (StandardException var2) {
            throw Util.newIOException(var2);
         }
      }
   }

   boolean isObsolete() {
      return this.updateCount != this.control.getUpdateCount();
   }

   void reInitialize() {
      this.updateCount = this.control.getUpdateCount();
      this.pos = 0L;
   }

   long length() throws IOException {
      return this.control.getLength();
   }

   public InputStream asInputStream() {
      return this;
   }

   public long getPosition() {
      return this.pos;
   }

   public void reposition(long var1) throws IOException {
      if (var1 > this.length()) {
         this.pos = 0L;
         throw new EOFException();
      } else {
         this.pos = var1;
      }
   }
}
