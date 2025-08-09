package org.apache.derby.impl.jdbc;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.derby.shared.common.error.ExceptionUtil;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.MessageService;

public class LOBOutputStream extends OutputStream {
   private boolean closed = false;
   private final LOBStreamControl control;
   private long pos;

   LOBOutputStream(LOBStreamControl var1, long var2) {
      this.control = var1;
      this.pos = var2;
   }

   public void write(int var1) throws IOException {
      if (this.closed) {
         throw new IOException(MessageService.getTextMessage("J104", new Object[0]));
      } else {
         try {
            this.pos = this.control.write(var1, this.pos);
         } catch (StandardException var3) {
            throw Util.newIOException(var3);
         }
      }
   }

   public void write(byte[] var1, int var2, int var3) throws IOException {
      if (this.closed) {
         throw new IOException(MessageService.getTextMessage("J104", new Object[0]));
      } else {
         try {
            this.pos = this.control.write(var1, var2, var3, this.pos);
         } catch (StandardException var5) {
            if (var5.getSQLState().equals(ExceptionUtil.getSQLStateFromIdentifier("XJ078.S"))) {
               throw new ArrayIndexOutOfBoundsException(var5.getMessage());
            } else {
               throw Util.newIOException(var5);
            }
         }
      }
   }

   public void close() throws IOException {
      this.closed = true;
   }
}
