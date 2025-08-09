package org.apache.derby.impl.load;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.Reader;
import org.apache.derby.iapi.services.io.LimitInputStream;
import org.apache.derby.shared.common.error.PublicAPI;
import org.apache.derby.shared.common.error.StandardException;

class ImportLobFile {
   private ImportFileInputStream lobInputStream = null;
   private LimitInputStream lobLimitIn;
   private Reader lobReader = null;
   private String dataCodeset;

   ImportLobFile(File var1, String var2) throws Exception {
      this.dataCodeset = var2;
      this.openLobFile(var1);
   }

   private void openLobFile(File var1) throws Exception {
      RandomAccessFile var2;
      try {
         var2 = new RandomAccessFile(var1, "r");
      } catch (FileNotFoundException var4) {
         throw PublicAPI.wrapStandardException(StandardException.newException("XIE0P.S", new Object[]{var1.getPath()}));
      }

      this.lobInputStream = new ImportFileInputStream(var2);
      this.lobLimitIn = new LimitInputStream(this.lobInputStream);
   }

   public InputStream getBinaryStream(long var1, long var3) throws IOException {
      this.lobInputStream.seek(var1);
      this.lobLimitIn.clearLimit();
      this.lobLimitIn.setLimit((int)var3);
      return this.lobLimitIn;
   }

   public String getString(long var1, int var3) throws IOException {
      this.lobInputStream.seek(var1);
      this.lobLimitIn.clearLimit();
      this.lobLimitIn.setLimit(var3);
      this.lobReader = this.dataCodeset == null ? new InputStreamReader(this.lobLimitIn) : new InputStreamReader(this.lobLimitIn, this.dataCodeset);
      StringBuffer var4 = new StringBuffer();
      char[] var5 = new char[1024];

      for(int var6 = this.lobReader.read(var5, 0, 1024); var6 != -1; var6 = this.lobReader.read(var5, 0, 1024)) {
         var4.append(var5, 0, var6);
      }

      return var4.toString();
   }

   public Reader getCharacterStream(long var1, long var3) throws IOException {
      this.lobInputStream.seek(var1);
      this.lobLimitIn.clearLimit();
      this.lobLimitIn.setLimit((int)var3);
      this.lobReader = this.dataCodeset == null ? new InputStreamReader(this.lobLimitIn) : new InputStreamReader(this.lobLimitIn, this.dataCodeset);
      return this.lobReader;
   }

   public long getClobDataLength(long var1, long var3) throws IOException {
      this.lobInputStream.seek(var1);
      this.lobLimitIn.clearLimit();
      this.lobLimitIn.setLimit((int)var3);
      this.lobReader = this.dataCodeset == null ? new InputStreamReader(this.lobLimitIn) : new InputStreamReader(this.lobLimitIn, this.dataCodeset);
      char[] var5 = new char[1024];
      long var6 = 0L;

      for(int var8 = this.lobReader.read(var5, 0, 1024); var8 != -1; var8 = this.lobReader.read(var5, 0, 1024)) {
         var6 += (long)var8;
      }

      return var6;
   }

   public void close() throws IOException {
      if (this.lobReader != null) {
         this.lobReader.close();
      } else if (this.lobLimitIn != null) {
         this.lobLimitIn.close();
      } else if (this.lobInputStream != null) {
         this.lobInputStream.close();
      }

   }
}
