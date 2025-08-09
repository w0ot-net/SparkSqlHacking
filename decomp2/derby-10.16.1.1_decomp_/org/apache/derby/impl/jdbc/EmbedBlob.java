package org.apache.derby.impl.jdbc;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import org.apache.derby.iapi.jdbc.EngineLOB;
import org.apache.derby.iapi.services.io.InputStreamUtil;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.Resetable;
import org.apache.derby.iapi.util.InterruptStatus;
import org.apache.derby.shared.common.error.StandardException;

final class EmbedBlob extends ConnectionChild implements Blob, EngineLOB {
   private boolean materialized;
   private PositionedStoreStream myStream;
   private int locator = 0;
   private long streamLength = -1L;
   private final int streamPositionOffset;
   private boolean isValid = true;
   private LOBStreamControl control;

   EmbedBlob(byte[] var1, EmbedConnection var2) throws SQLException {
      super(var2);

      try {
         this.control = new LOBStreamControl(var2, var1);
         this.materialized = true;
         this.streamPositionOffset = Integer.MIN_VALUE;
         var2.addLOBReference(this);
      } catch (IOException var4) {
         throw Util.setStreamFailure(var4);
      } catch (StandardException var5) {
         throw Util.generateCsSQLException(var5);
      }
   }

   protected EmbedBlob(DataValueDescriptor var1, EmbedConnection var2) throws StandardException, SQLException {
      super(var2);
      if (var1.hasStream()) {
         this.streamPositionOffset = this.handleStreamValue(var1.getStream(), var2);
      } else {
         this.materialized = true;
         this.streamPositionOffset = Integer.MIN_VALUE;
         byte[] var3 = var1.getBytes();

         try {
            this.control = new LOBStreamControl(this.getEmbedConnection(), var3);
         } catch (IOException var5) {
            throw Util.setStreamFailure(var5);
         }
      }

      var2.addLOBReference(this);
   }

   private int handleStreamValue(InputStream var1, EmbedConnection var2) throws StandardException, SQLException {
      int var3 = 0;
      if (var1 instanceof Resetable) {
         this.materialized = false;

         try {
            this.myStream = new PositionedStoreStream(var1);
            BinaryToRawStream var4 = new BinaryToRawStream(this.myStream, var2);
            var3 = (int)this.myStream.getPosition();
            this.streamLength = (long)var4.getLength();
            var4.close();
         } catch (StandardException var9) {
            if (var9.getMessageId().equals("40XD0")) {
               throw StandardException.newException("XJ073.S", new Object[0]);
            }

            throw var9;
         } catch (IOException var10) {
            throw StandardException.newException("XCL30.S", var10, new Object[]{"BLOB"});
         }
      } else {
         this.materialized = true;
         var3 = Integer.MIN_VALUE;

         try {
            this.control = new LOBStreamControl(this.getEmbedConnection());
            BinaryToRawStream var13 = new BinaryToRawStream(var1, var2);
            byte[] var5 = new byte[4096];
            long var6 = 0L;

            while(true) {
               int var8 = var13.read(var5, 0, var5.length);
               if (var8 < 1) {
                  var13.close();
                  break;
               }

               var6 = this.control.write(var5, 0, var8, var6);
            }
         } catch (IOException var11) {
            throw Util.setStreamFailure(var11);
         }
      }

      return var3;
   }

   private long setBlobPosition(long var1) throws StandardException, IOException {
      if (this.materialized) {
         if (var1 >= this.control.getLength()) {
            throw StandardException.newException("XJ076.S", new Object[]{var1});
         }
      } else {
         try {
            this.myStream.reposition(var1 + (long)this.streamPositionOffset);
         } catch (EOFException var4) {
            throw StandardException.newException("XJ076.S", var4, new Object[]{var1});
         }
      }

      return var1;
   }

   private int read(long var1) throws IOException, StandardException {
      int var3;
      if (this.materialized) {
         if (var1 >= this.control.getLength()) {
            return -1;
         }

         var3 = this.control.read(var1);
      } else {
         this.myStream.reposition(var1 + (long)this.streamPositionOffset);
         var3 = this.myStream.read();
      }

      return var3;
   }

   public long length() throws SQLException {
      this.checkValidity();

      try {
         if (this.materialized) {
            return this.control.getLength();
         }
      } catch (IOException var13) {
         throw Util.setStreamFailure(var13);
      }

      if (this.streamLength != -1L) {
         return this.streamLength;
      } else {
         boolean var1 = false;

         long var5;
         try {
            synchronized(this.getConnectionSynchronization()) {
               EmbedConnection var3 = this.getEmbedConnection();
               var1 = !var3.isClosed();
               if (var1) {
                  this.setupContextStack();
               }

               this.myStream.resetStream();
               BinaryToRawStream var4 = new BinaryToRawStream(this.myStream, this);
               this.streamLength = InputStreamUtil.skipUntilEOF(var4);
               var4.close();
               restoreIntrFlagIfSeen(var1, var3);
               var5 = this.streamLength;
            }
         } catch (Throwable var15) {
            throw this.handleMyExceptions(var15);
         } finally {
            if (var1) {
               this.restoreContextStack();
            }

         }

         return var5;
      }
   }

   public byte[] getBytes(long var1, int var3) throws SQLException {
      this.checkValidity();
      boolean var4 = false;

      try {
         if (var1 < 1L) {
            throw StandardException.newException("XJ070.S", new Object[]{var1});
         } else if (var3 < 0) {
            throw StandardException.newException("XJ071.S", new Object[]{var3});
         } else {
            byte[] var21;
            if (this.materialized) {
               var21 = new byte[var3];
               int var6 = this.control.read(var21, 0, var21.length, var1 - 1L);
               if (var6 == -1) {
                  InterruptStatus.restoreIntrFlagIfSeen();
                  byte[] var23 = new byte[0];
                  return var23;
               }

               if (var6 < var3) {
                  byte[] var7 = new byte[var6];
                  System.arraycopy(var21, 0, var7, 0, var6);
                  var21 = var7;
               }

               InterruptStatus.restoreIntrFlagIfSeen();
            } else {
               synchronized(this.getConnectionSynchronization()) {
                  EmbedConnection var24 = this.getEmbedConnection();
                  var4 = !var24.isClosed();
                  if (var4) {
                     this.setupContextStack();
                  }

                  this.setBlobPosition(var1 - 1L);
                  var21 = new byte[var3];
                  int var8 = InputStreamUtil.readLoop(this.myStream, var21, 0, var3);
                  if (var8 < var3) {
                     byte[] var9 = new byte[var8];
                     System.arraycopy(var21, 0, var9, 0, var8);
                     restoreIntrFlagIfSeen(var4, var24);
                     byte[] var10 = var9;
                     return var10;
                  }

                  restoreIntrFlagIfSeen(var4, var24);
               }
            }

            byte[] var22 = var21;
            return var22;
         }
      } catch (StandardException var18) {
         StandardException var5 = var18;
         if (var18.getMessageId().equals("XJ079.S")) {
            var5 = StandardException.newException("XJ076.S", new Object[]{var1});
         }

         throw this.handleMyExceptions(var5);
      } catch (Throwable var19) {
         throw this.handleMyExceptions(var19);
      } finally {
         if (var4) {
            this.restoreContextStack();
         }

      }
   }

   public InputStream getBinaryStream() throws SQLException {
      this.checkValidity();
      boolean var1 = false;

      InputStream var3;
      try {
         if (!this.materialized) {
            synchronized(this.getConnectionSynchronization()) {
               EmbedConnection var14 = this.getEmbedConnection();
               var1 = !var14.isClosed();
               if (var1) {
                  this.setupContextStack();
               }

               this.myStream.resetStream();
               UpdatableBlobStream var4 = new UpdatableBlobStream(this, new AutoPositioningStream(this, this.myStream, this));
               restoreIntrFlagIfSeen(var1, var14);
               UpdatableBlobStream var5 = var4;
               return var5;
            }
         }

         InputStream var2 = this.control.getInputStream(0L);
         var3 = var2;
      } catch (Throwable var12) {
         throw this.handleMyExceptions(var12);
      } finally {
         if (var1) {
            this.restoreContextStack();
         }

      }

      return var3;
   }

   public long position(byte[] var1, long var2) throws SQLException {
      this.checkValidity();
      boolean var4 = false;

      long var5;
      try {
         if (var2 < 1L) {
            throw StandardException.newException("XJ070.S", new Object[]{var2});
         }

         if (var1 == null) {
            throw StandardException.newException("XJ072.S", new Object[0]);
         }

         if (var1.length != 0) {
            synchronized(this.getConnectionSynchronization()) {
               EmbedConnection var6 = this.getEmbedConnection();
               var4 = !var6.isClosed();
               if (var4) {
                  this.setupContextStack();
               }

               long var7 = this.setBlobPosition(var2 - 1L);
               byte var9 = var1[0];

               while(true) {
                  int var12 = this.read(var7++);
                  if (var12 == -1) {
                     restoreIntrFlagIfSeen(var4, var6);
                     long var25 = -1L;
                     return var25;
                  }

                  if (var12 == var9) {
                     if (this.checkMatch(var1, var7)) {
                        restoreIntrFlagIfSeen(var4, var6);
                        long var13 = var7;
                        return var13;
                     }

                     var7 = this.setBlobPosition(var7);
                  }
               }
            }
         }

         var5 = var2;
      } catch (StandardException var22) {
         throw this.handleMyExceptions(var22);
      } catch (Throwable var23) {
         throw this.handleMyExceptions(var23);
      } finally {
         if (var4) {
            this.restoreContextStack();
         }

      }

      return var5;
   }

   private boolean checkMatch(byte[] var1, long var2) throws IOException, StandardException {
      for(int var4 = 1; var4 < var1.length; ++var4) {
         int var5 = this.read(var2++);
         if (var5 < 0 || var5 != var1[var4]) {
            return false;
         }
      }

      return true;
   }

   public long position(Blob var1, long var2) throws SQLException {
      this.checkValidity();
      boolean var4 = false;

      long var10;
      try {
         if (var2 < 1L) {
            throw StandardException.newException("XJ070.S", new Object[]{var2});
         }

         if (var1 == null) {
            throw StandardException.newException("XJ072.S", new Object[0]);
         }

         synchronized(this.getConnectionSynchronization()) {
            EmbedConnection var6 = this.getEmbedConnection();
            var4 = !var6.isClosed();
            if (var4) {
               this.setupContextStack();
            }

            long var7 = this.setBlobPosition(var2 - 1L);

            byte[] var9;
            try {
               var9 = var1.getBytes(1L, 1);
            } catch (SQLException var23) {
               throw StandardException.newException("XJ077.S", new Object[0]);
            }

            if (var9 != null && var9.length >= 1) {
               byte var28 = var9[0];

               while(true) {
                  int var11 = this.read(var7++);
                  if (var11 == -1) {
                     restoreIntrFlagIfSeen(var4, var6);
                     long var29 = -1L;
                     return var29;
                  }

                  if (var11 == var28) {
                     if (this.checkMatch(var1, var7)) {
                        restoreIntrFlagIfSeen(var4, var6);
                        long var14 = var7;
                        return var14;
                     }

                     var7 = this.setBlobPosition(var7);
                  }
               }
            }

            restoreIntrFlagIfSeen(var4, var6);
            var10 = var2;
         }
      } catch (StandardException var25) {
         throw this.handleMyExceptions(var25);
      } catch (Throwable var26) {
         throw this.handleMyExceptions(var26);
      } finally {
         if (var4) {
            this.restoreContextStack();
         }

      }

      return var10;
   }

   private boolean checkMatch(Blob var1, long var2) throws IOException, StandardException {
      InputStream var4;
      try {
         var4 = var1.getBinaryStream();
      } catch (SQLException var7) {
         return false;
      }

      if (var4 == null) {
         return false;
      } else {
         int var5 = var4.read();
         if (var5 < 0) {
            return false;
         } else {
            int var6;
            do {
               var5 = var4.read();
               if (var5 < 0) {
                  return true;
               }

               var6 = this.read(var2++);
            } while(var5 == var6 && var6 >= 0);

            return false;
         }
      }
   }

   private SQLException handleMyExceptions(Throwable var1) throws SQLException {
      if (var1 instanceof StandardException && ((StandardException)var1).getMessageId().equals("40XD0")) {
         var1 = StandardException.newException("XJ073.S", new Object[0]);
      }

      return this.handleException((Throwable)var1);
   }

   protected void finalize() {
      if (!this.materialized) {
         this.myStream.closeStream();
      }

   }

   public int setBytes(long var1, byte[] var3) throws SQLException {
      return this.setBytes(var1, var3, 0, var3.length);
   }

   public int setBytes(long var1, byte[] var3, int var4, int var5) throws SQLException {
      this.checkValidity();
      if (var1 - 1L > this.length()) {
         throw Util.generateCsSQLException("XJ076.S", var1);
      } else if (var1 < 1L) {
         throw Util.generateCsSQLException("XJ070.S", var1);
      } else if (var4 >= 0 && var4 <= var3.length) {
         if (var5 < 0) {
            throw Util.generateCsSQLException("XJ071.S", var5);
         } else if (var5 == 0) {
            return 0;
         } else if (var5 > var3.length - var4) {
            throw Util.generateCsSQLException("XJ079.S", var5);
         } else {
            try {
               if (this.materialized) {
                  this.control.write(var3, var4, var5, var1 - 1L);
               } else {
                  this.control = new LOBStreamControl(this.getEmbedConnection());
                  this.control.copyData(this.myStream, this.length());
                  this.control.write(var3, var4, var5, var1 - 1L);
                  this.myStream.close();
                  this.streamLength = -1L;
                  this.materialized = true;
               }

               return var5;
            } catch (IOException var7) {
               throw Util.setStreamFailure(var7);
            } catch (StandardException var8) {
               throw Util.generateCsSQLException(var8);
            }
         }
      } else {
         throw Util.generateCsSQLException("XJ078.S", var4);
      }
   }

   public OutputStream setBinaryStream(long var1) throws SQLException {
      this.checkValidity();
      if (var1 - 1L > this.length()) {
         throw Util.generateCsSQLException("XJ076.S", var1);
      } else if (var1 < 1L) {
         throw Util.generateCsSQLException("XJ070.S", var1);
      } else {
         try {
            if (this.materialized) {
               return this.control.getOutputStream(var1 - 1L);
            } else {
               this.control = new LOBStreamControl(this.getEmbedConnection());
               this.control.copyData(this.myStream, var1 - 1L);
               this.myStream.close();
               this.streamLength = -1L;
               this.materialized = true;
               return this.control.getOutputStream(var1 - 1L);
            }
         } catch (IOException var4) {
            throw Util.setStreamFailure(var4);
         } catch (StandardException var5) {
            throw Util.generateCsSQLException(var5);
         }
      }
   }

   public void truncate(long var1) throws SQLException {
      if (var1 > this.length()) {
         throw Util.generateCsSQLException("XJ079.S", var1);
      } else {
         try {
            if (this.materialized) {
               this.control.truncate(var1);
            } else {
               this.setBlobPosition(0L);
               this.control = new LOBStreamControl(this.getEmbedConnection());
               this.control.copyData(this.myStream, var1);
               this.myStream.close();
               this.streamLength = -1L;
               this.materialized = true;
            }

         } catch (IOException var4) {
            throw Util.setStreamFailure(var4);
         } catch (StandardException var5) {
            throw Util.generateCsSQLException(var5);
         }
      }
   }

   public void free() throws SQLException {
      if (this.isValid) {
         this.isValid = false;
         if (this.locator != 0) {
            this.localConn.removeLOBMapping(this.locator);
         }

         this.streamLength = -1L;
         if (!this.materialized) {
            this.myStream.closeStream();
            this.myStream = null;
         } else {
            try {
               this.control.free();
               this.control = null;
            } catch (IOException var2) {
               throw Util.setStreamFailure(var2);
            }
         }

      }
   }

   public InputStream getBinaryStream(long var1, long var3) throws SQLException {
      this.checkValidity();
      if (var1 <= 0L) {
         throw Util.generateCsSQLException("XJ070.S", var1);
      } else if (var3 < 0L) {
         throw Util.generateCsSQLException("XJ071.S", var3);
      } else if (var3 > this.length() - (var1 - 1L)) {
         throw Util.generateCsSQLException("XJ087.S", var1, var3);
      } else {
         try {
            return new UpdatableBlobStream(this, this.getBinaryStream(), var1 - 1L, var3);
         } catch (IOException var6) {
            throw Util.setStreamFailure(var6);
         }
      }
   }

   private void checkValidity() throws SQLException {
      this.getEmbedConnection().checkIfClosed();
      if (!this.isValid) {
         throw newSQLException("XJ215.S", new Object[0]);
      }
   }

   boolean isMaterialized() {
      return this.materialized;
   }

   public int getLocator() {
      if (this.locator == 0) {
         this.locator = this.localConn.addLOBMapping(this);
      }

      return this.locator;
   }
}
