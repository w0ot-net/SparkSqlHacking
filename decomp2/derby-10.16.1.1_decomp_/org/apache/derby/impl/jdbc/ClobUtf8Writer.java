package org.apache.derby.impl.jdbc;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import org.apache.derby.shared.common.i18n.MessageService;

final class ClobUtf8Writer extends Writer {
   private TemporaryClob control;
   private long pos;
   private boolean closed;

   ClobUtf8Writer(TemporaryClob var1, long var2) {
      this.control = var1;
      this.pos = var2;
      this.closed = false;
   }

   public void flush() throws IOException {
      if (this.closed) {
         throw new IOException(MessageService.getTextMessage("J104", new Object[0]));
      }
   }

   public void close() {
      this.closed = true;
   }

   public void write(char[] var1, int var2, int var3) throws IOException {
      if (this.closed) {
         throw new IOException(MessageService.getTextMessage("J104", new Object[0]));
      } else {
         try {
            long var4 = this.control.insertString(String.copyValueOf(var1, var2, var3), this.pos);
            if (var4 > 0L) {
               this.pos += var4;
            }

         } catch (SQLException var6) {
            throw Util.newIOException(var6);
         }
      }
   }
}
