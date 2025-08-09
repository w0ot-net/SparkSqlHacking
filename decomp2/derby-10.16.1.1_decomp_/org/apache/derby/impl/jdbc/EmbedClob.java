package org.apache.derby.impl.jdbc;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Clob;
import java.sql.SQLException;
import org.apache.derby.iapi.jdbc.CharacterStreamDescriptor;
import org.apache.derby.iapi.jdbc.EngineLOB;
import org.apache.derby.iapi.types.StringDataValue;
import org.apache.derby.shared.common.error.StandardException;

final class EmbedClob extends ConnectionChild implements Clob, EngineLOB {
   private InternalClob clob;
   private boolean isValid = true;
   private int locator;

   EmbedClob(EmbedConnection var1) throws SQLException {
      super(var1);
      this.clob = new TemporaryClob(this);
      var1.addLOBReference(this);
   }

   protected EmbedClob(EmbedConnection var1, StringDataValue var2) throws StandardException, SQLException {
      super(var1);
      if (var2.hasStream()) {
         CharacterStreamDescriptor var3 = var2.getStreamWithDescriptor();

         try {
            this.clob = new StoreStreamClob(var3, this);
         } catch (StandardException var6) {
            if (var6.getMessageId().equals("40XD0")) {
               throw StandardException.newException("XJ073.S", new Object[0]);
            }

            throw var6;
         }
      } else {
         try {
            this.clob = new TemporaryClob(var2.getString(), this);
         } catch (IOException var5) {
            throw Util.setStreamFailure(var5);
         }
      }

      var1.addLOBReference(this);
   }

   public long length() throws SQLException {
      this.checkValidity();

      try {
         return this.clob.getCharLength();
      } catch (IOException var2) {
         throw Util.setStreamFailure(var2);
      }
   }

   public String getSubString(long var1, int var3) throws SQLException {
      this.checkValidity();
      if (var1 < 1L) {
         throw Util.generateCsSQLException("XJ070.S", var1);
      } else if (var3 < 0) {
         throw Util.generateCsSQLException("XJ071.S", var3);
      } else {
         try {
            Reader var5;
            try {
               var5 = this.clob.getInternalReader(var1);
            } catch (EOFException var9) {
               throw Util.generateCsSQLException("XJ076.S", var9, var1);
            }

            char[] var6 = new char[var3];

            int var7;
            int var8;
            for(var7 = 0; var7 < var3; var7 += var8) {
               var8 = var5.read(var6, var7, var3 - var7);
               if (var8 == -1) {
                  break;
               }
            }

            var5.close();
            String var4;
            if (var7 == 0) {
               var4 = "";
            } else {
               var4 = String.copyValueOf(var6, 0, var7);
            }

            return var4;
         } catch (IOException var10) {
            throw Util.setStreamFailure(var10);
         }
      }
   }

   public Reader getCharacterStream() throws SQLException {
      this.checkValidity();

      try {
         return new ClobUpdatableReader(this);
      } catch (IOException var2) {
         throw Util.setStreamFailure(var2);
      }
   }

   public InputStream getAsciiStream() throws SQLException {
      return new ReaderToAscii(this.getCharacterStream());
   }

   public long position(String var1, long var2) throws SQLException {
      this.checkValidity();
      if (var2 < 1L) {
         throw Util.generateCsSQLException("XJ070.S", var2);
      } else if (var1 == null) {
         throw Util.generateCsSQLException("XJ072.S");
      } else if ("".equals(var1)) {
         return var2;
      } else {
         boolean var4 = false;
         EmbedConnection var5 = this.getEmbedConnection();

         try {
            Object var6 = this.getConnectionSynchronization();
            synchronized(var6) {
               var4 = !var5.isClosed();
               if (var4) {
                  this.setupContextStack();
               }

               int var8 = 0;
               long var9 = var2;
               long var11 = -1L;
               Reader var13 = this.clob.getInternalReader(var2);
               char[] var14 = new char[4096];

               while(true) {
                  boolean var15 = false;
                  int var16 = var13.read(var14);
                  if (var16 == -1) {
                     restoreIntrFlagIfSeen(var4, var5);
                     long var30 = -1L;
                     return var30;
                  }

                  for(int var17 = 0; var17 < var16; ++var17) {
                     if (var14[var17] == var1.charAt(var8)) {
                        if (var8 != 0 && var11 == -1L && var14[var17] == var1.charAt(0)) {
                           var11 = var9 + (long)var17 + 1L;
                        }

                        ++var8;
                        if (var8 == var1.length()) {
                           restoreIntrFlagIfSeen(var4, var5);
                           long var18 = var9 + (long)var17 - (long)var1.length() + 1L;
                           return var18;
                        }
                     } else if (var8 > 0) {
                        if (var11 == -1L) {
                           if (var8 > 1) {
                              --var17;
                           }

                           var8 = 0;
                        } else {
                           var8 = 0;
                           if (var11 < var9) {
                              var9 = var11;
                              var13.close();
                              var13 = this.clob.getInternalReader(var11);
                              var11 = -1L;
                              var15 = true;
                              break;
                           }

                           var17 = (int)(var11 - var9) - 1;
                           var11 = -1L;
                        }
                     }
                  }

                  if (!var15) {
                     var9 += (long)var16;
                  }
               }
            }
         } catch (EOFException var27) {
            restoreIntrFlagIfSeen(var4, var5);
            throw Util.generateCsSQLException("XJ076.S", var27, var2);
         } catch (IOException var28) {
            restoreIntrFlagIfSeen(var4, var5);
            throw Util.setStreamFailure(var28);
         } finally {
            if (var4) {
               this.restoreContextStack();
            }

         }
      }
   }

   public long position(Clob var1, long var2) throws SQLException {
      this.checkValidity();
      if (var2 < 1L) {
         throw Util.generateCsSQLException("XJ070.S", var2);
      } else if (var1 == null) {
         throw Util.generateCsSQLException("XJ072.S");
      } else {
         boolean var4 = false;
         EmbedConnection var5 = this.getEmbedConnection();

         try {
            synchronized(this.getConnectionSynchronization()) {
               char[] var7 = new char[1024];
               boolean var8 = false;

               while(true) {
                  long var9 = -1L;
                  Reader var11 = var1.getCharacterStream();

                  while(true) {
                     int var12 = var11.read(var7, 0, var7.length);
                     if (var12 == -1) {
                        if (!var8) {
                           restoreIntrFlagIfSeen(var4, var5);
                           long var27 = var2;
                           return var27;
                        }

                        restoreIntrFlagIfSeen(var4, var5);
                        long var26 = var9;
                        return var26;
                     }

                     if (var12 != 0) {
                        var8 = true;
                        String var13 = new String(var7, 0, var12);
                        long var14 = this.position(var13, var2);
                        if (var14 == -1L) {
                           if (var9 == -1L) {
                              restoreIntrFlagIfSeen(var4, var5);
                              long var16 = -1L;
                              return var16;
                           }

                           var2 = var9 + 1L;
                           break;
                        }

                        if (var9 == -1L) {
                           var9 = var14;
                        } else if (var14 != var2) {
                           var2 = var9 + 1L;
                           break;
                        }

                        var2 = var14 + (long)var12;
                     }
                  }
               }
            }
         } catch (IOException var24) {
            restoreIntrFlagIfSeen(var4, var5);
            throw Util.setStreamFailure(var24);
         } finally {
            if (var4) {
               this.restoreContextStack();
            }

         }
      }
   }

   public int setString(long var1, String var3) throws SQLException {
      return this.setString(var1, var3, 0, var3.length());
   }

   public int setString(long var1, String var3, int var4, int var5) throws SQLException {
      this.checkValidity();
      if (var1 < 1L) {
         throw Util.generateCsSQLException("XJ070.S", var1);
      } else if (var1 > this.length() + 1L) {
         throw Util.generateCsSQLException("XJ076.S");
      } else if (var3 == null) {
         throw Util.generateCsSQLException("XJ072.S");
      } else if (var3.length() == 0) {
         return 0;
      } else if (var4 >= 0 && var4 < var3.length()) {
         if (var5 < 0) {
            throw Util.generateCsSQLException("XJ071.S");
         } else if (var5 + var4 > var3.length()) {
            throw Util.generateCsSQLException("22011.S.1", var4, var5, var3);
         } else {
            try {
               if (!this.clob.isWritable()) {
                  this.makeWritableClobClone();
               }

               this.clob.insertString(var3.substring(var4, var4 + var5), var1);
            } catch (EOFException var7) {
               throw Util.generateCsSQLException("XJ076.S", var1);
            } catch (IOException var8) {
               throw Util.setStreamFailure(var8);
            }

            return var3.length();
         }
      } else {
         throw Util.generateCsSQLException("XJ078.S");
      }
   }

   public OutputStream setAsciiStream(long var1) throws SQLException {
      this.checkValidity();

      try {
         return new ClobAsciiStream(this.clob.getWriter(var1));
      } catch (IOException var4) {
         throw Util.setStreamFailure(var4);
      }
   }

   public Writer setCharacterStream(long var1) throws SQLException {
      this.checkValidity();

      try {
         if (!this.clob.isWritable()) {
            this.makeWritableClobClone();
         }

         return this.clob.getWriter(var1);
      } catch (IOException var4) {
         throw Util.setStreamFailure(var4);
      }
   }

   public void truncate(long var1) throws SQLException {
      this.checkValidity();
      if (var1 < 0L) {
         throw Util.generateCsSQLException("XJ071.S", var1);
      } else {
         try {
            if (!this.clob.isWritable()) {
               this.makeWritableClobClone(var1);
            } else {
               this.clob.truncate(var1);
            }

         } catch (EOFException var4) {
            throw Util.generateCsSQLException("XJ079.S", var4, var1);
         } catch (IOException var5) {
            throw Util.setStreamFailure(var5);
         }
      }
   }

   public void free() throws SQLException {
      if (this.isValid) {
         this.isValid = false;

         try {
            this.clob.release();
         } catch (IOException var5) {
            throw Util.setStreamFailure(var5);
         } finally {
            this.localConn.removeLOBMapping(this.locator);
            this.clob = null;
         }
      }

   }

   public Reader getCharacterStream(long var1, long var3) throws SQLException {
      this.checkValidity();
      if (var1 <= 0L) {
         throw Util.generateCsSQLException("XJ070.S", var1);
      } else if (var3 < 0L) {
         throw Util.generateCsSQLException("XJ071.S", var3);
      } else if (var3 > this.length() - (var1 - 1L)) {
         throw Util.generateCsSQLException("XJ087.S", var1, var3);
      } else {
         try {
            return new ClobUpdatableReader(this, var1, var3);
         } catch (IOException var6) {
            throw Util.setStreamFailure(var6);
         }
      }
   }

   private void checkValidity() throws SQLException {
      this.localConn.checkIfClosed();
      if (!this.isValid) {
         throw newSQLException("XJ215.S", new Object[0]);
      }
   }

   private void makeWritableClobClone() throws IOException, SQLException {
      InternalClob var1 = this.clob;
      this.clob = TemporaryClob.cloneClobContent(this.getEmbedConnection().getDBName(), this, var1);
      var1.release();
   }

   private void makeWritableClobClone(long var1) throws IOException, SQLException {
      InternalClob var3 = this.clob;
      this.clob = TemporaryClob.cloneClobContent(this.getEmbedConnection().getDBName(), this, var3, var1);
      var3.release();
   }

   InternalClob getInternalClob() {
      return this.clob;
   }

   public int getLocator() {
      if (this.locator == 0) {
         this.locator = this.localConn.addLOBMapping(this);
      }

      return this.locator;
   }
}
