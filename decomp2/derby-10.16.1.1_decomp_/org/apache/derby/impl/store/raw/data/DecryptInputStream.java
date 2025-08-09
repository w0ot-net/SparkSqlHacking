package org.apache.derby.impl.store.raw.data;

import java.io.IOException;
import java.io.InputStream;
import org.apache.derby.iapi.services.io.CompressedNumber;
import org.apache.derby.iapi.store.raw.data.DataFactory;
import org.apache.derby.shared.common.error.StandardException;

public class DecryptInputStream extends BufferedByteHolderInputStream {
   protected DataFactory dataFactory;
   protected InputStream in;

   public DecryptInputStream(InputStream var1, ByteHolder var2, DataFactory var3) throws IOException {
      super(var2);
      this.in = var1;
      this.dataFactory = var3;
      this.fillByteHolder();
   }

   public void fillByteHolder() throws IOException {
      if (this.bh.available() == 0) {
         this.bh.clear();

         try {
            int var1 = CompressedNumber.readInt(this.in);
            if (var1 == -1) {
               return;
            }

            int var2 = var1 % this.dataFactory.getEncryptionBlockSize();
            int var3 = var2 == 0 ? 0 : this.dataFactory.getEncryptionBlockSize() - var2;
            int var4 = var1 + var3;
            byte[] var5 = new byte[var4];
            this.in.read(var5, 0, var4);
            byte[] var6 = new byte[var4];
            this.dataFactory.decrypt(var5, 0, var4, var6, 0);
            this.bh.write(var6, var3, var1);
         } catch (StandardException var7) {
            throw new IOException();
         }

         this.bh.startReading();
      }

   }
}
