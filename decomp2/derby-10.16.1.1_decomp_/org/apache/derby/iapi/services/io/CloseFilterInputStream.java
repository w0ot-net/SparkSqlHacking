package org.apache.derby.iapi.services.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.derby.shared.common.i18n.MessageService;

public class CloseFilterInputStream extends FilterInputStream {
   private static final String MESSAGE = MessageService.getTextMessage("J104", new Object[0]);
   private boolean closed;

   public CloseFilterInputStream(InputStream var1) {
      super(var1);
   }

   public void close() throws IOException {
      this.closed = true;
      super.close();
   }

   public int available() throws IOException {
      this.checkIfClosed();
      return super.available();
   }

   public int read() throws IOException {
      this.checkIfClosed();
      return super.read();
   }

   public int read(byte[] var1) throws IOException {
      this.checkIfClosed();
      return super.read(var1);
   }

   public int read(byte[] var1, int var2, int var3) throws IOException {
      this.checkIfClosed();
      return super.read(var1, var2, var3);
   }

   public long skip(long var1) throws IOException {
      this.checkIfClosed();
      return super.skip(var1);
   }

   private void checkIfClosed() throws IOException {
      if (this.closed) {
         throw new IOException(MESSAGE);
      }
   }
}
