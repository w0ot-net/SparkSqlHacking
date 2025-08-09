package org.apache.derby.impl.jdbc;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.FilterReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.SQLException;
import org.apache.derby.iapi.jdbc.CharacterStreamDescriptor;
import org.apache.derby.iapi.util.UTF8Util;
import org.apache.derby.shared.common.error.StandardException;

final class TemporaryClob implements InternalClob {
   private ConnectionChild conChild;
   private final LOBStreamControl bytes;
   private boolean released = false;
   private long cachedCharLength;
   private UTF8Reader internalReader;
   private FilterReader unclosableInternalReader;
   private final CharToBytePositionCache posCache = new CharToBytePositionCache();

   static InternalClob cloneClobContent(String var0, ConnectionChild var1, InternalClob var2) throws IOException, SQLException {
      TemporaryClob var3 = new TemporaryClob(var1);
      var3.copyClobContent(var2);
      return var3;
   }

   static InternalClob cloneClobContent(String var0, ConnectionChild var1, InternalClob var2, long var3) throws IOException, SQLException {
      TemporaryClob var5 = new TemporaryClob(var1);
      var5.copyClobContent(var2, var3);
      return var5;
   }

   TemporaryClob(ConnectionChild var1) {
      if (var1 == null) {
         throw new NullPointerException("conChild cannot be <null>");
      } else {
         this.conChild = var1;
         this.bytes = new LOBStreamControl(var1.getEmbedConnection());
      }
   }

   public synchronized void release() throws IOException {
      if (!this.released) {
         this.released = true;
         this.bytes.free();
         if (this.internalReader != null) {
            this.internalReader.close();
            this.internalReader = null;
            this.unclosableInternalReader = null;
         }
      }

   }

   public synchronized InputStream getRawByteStream() throws IOException {
      this.checkIfValid();
      return this.bytes.getInputStream(0L);
   }

   TemporaryClob(String var1, ConnectionChild var2) throws IOException, StandardException {
      if (var2 == null) {
         throw new NullPointerException("conChild cannot be <null>");
      } else {
         this.conChild = var2;
         this.bytes = new LOBStreamControl(var2.getEmbedConnection(), this.getByteFromString(var1));
         this.cachedCharLength = (long)var1.length();
      }
   }

   private long getBytePosition(long var1) throws IOException {
      long var3;
      if (var1 == this.posCache.getCharPos()) {
         var3 = this.posCache.getBytePos();
      } else {
         long var5 = 0L;
         long var7 = var1 - 1L;
         if (var1 > this.posCache.getCharPos()) {
            var5 = this.posCache.getBytePos();
            var7 -= this.posCache.getCharPos() - 1L;
         }

         InputStream var9 = this.bytes.getInputStream(var5);
         var3 = var5 + UTF8Util.skipFully(new BufferedInputStream(var9), var7);
         this.posCache.updateCachedPos(var1, var3);
      }

      return var3;
   }

   public long getUpdateCount() {
      return this.bytes.getUpdateCount();
   }

   public synchronized Writer getWriter(long var1) throws IOException, SQLException {
      this.checkIfValid();
      if (var1 < this.posCache.getCharPos()) {
         this.posCache.reset();
      }

      return new ClobUtf8Writer(this, var1);
   }

   public synchronized Reader getReader(long var1) throws IOException, SQLException {
      this.checkIfValid();
      if (var1 < 1L) {
         throw new IllegalArgumentException("Position must be positive: " + var1);
      } else {
         UTF8Reader var3 = new UTF8Reader(this.getCSD(), this.conChild, this.conChild.getConnectionSynchronization());

         long var6;
         for(long var4 = var1 - 1L; var4 > 0L; var4 -= var6) {
            var6 = ((Reader)var3).skip(var4);
            if (var6 <= 0L) {
               throw new EOFException("Reached end-of-stream prematurely");
            }
         }

         return var3;
      }
   }

   public Reader getInternalReader(long var1) throws IOException, SQLException {
      if (this.internalReader == null) {
         this.internalReader = new UTF8Reader(this.getCSD(), this.conChild, this.conChild.getConnectionSynchronization());
         this.unclosableInternalReader = new FilterReader(this.internalReader) {
            public void close() {
            }
         };
      }

      try {
         this.internalReader.reposition(var1);
      } catch (StandardException var4) {
         throw Util.generateCsSQLException(var4);
      }

      return this.unclosableInternalReader;
   }

   public synchronized long getCharLength() throws IOException {
      this.checkIfValid();
      if (this.cachedCharLength == 0L) {
         this.cachedCharLength = UTF8Util.skipUntilEOF(new BufferedInputStream(this.getRawByteStream()));
      }

      return this.cachedCharLength;
   }

   public synchronized long getCharLengthIfKnown() {
      this.checkIfValid();
      return this.cachedCharLength == 0L ? -1L : this.cachedCharLength;
   }

   public synchronized long getByteLength() throws IOException {
      this.checkIfValid();
      return this.bytes.getLength();
   }

   public synchronized long insertString(String var1, long var2) throws IOException, SQLException {
      this.checkIfValid();
      if (var2 < 1L) {
         throw new IllegalArgumentException("Position must be positive: " + var2);
      } else {
         long var4 = this.cachedCharLength;
         this.updateInternalState(var2);
         long var6 = this.getBytePosition(var2);
         long var8 = this.bytes.getLength();
         byte[] var10 = this.getByteFromString(var1);
         if (var6 == var8) {
            try {
               this.bytes.write(var10, 0, var10.length, var6);
            } catch (StandardException var17) {
               throw Util.generateCsSQLException(var17);
            }
         } else {
            long var11;
            try {
               var11 = this.getBytePosition(var2 + (long)var1.length());
               this.posCache.updateCachedPos(var2, var6);
            } catch (EOFException var16) {
               var11 = var8;
            }

            try {
               this.bytes.replaceBytes(var10, var6, var11);
            } catch (StandardException var15) {
               throw Util.generateCsSQLException(var15);
            }

            if (var4 != 0L) {
               long var13 = var2 - 1L + (long)var1.length();
               if (var13 > var4) {
                  this.cachedCharLength = var13;
               } else {
                  this.cachedCharLength = var4;
               }
            }
         }

         return (long)var1.length();
      }
   }

   public synchronized boolean isReleased() {
      return this.released;
   }

   public boolean isWritable() {
      return true;
   }

   public synchronized void truncate(long var1) throws IOException, SQLException {
      this.checkIfValid();

      try {
         long var3 = UTF8Util.skipFully(new BufferedInputStream(this.getRawByteStream()), var1);
         this.bytes.truncate(var3);
         this.updateInternalState(var1);
         this.cachedCharLength = var1;
      } catch (StandardException var5) {
         throw Util.generateCsSQLException(var5);
      }
   }

   private byte[] getByteFromString(String var1) {
      byte[] var2 = new byte[3 * var1.length()];
      int var3 = 0;

      for(int var4 = 0; var4 < var1.length(); ++var4) {
         char var5 = var1.charAt(var4);
         if (var5 >= 1 && var5 <= 127) {
            var2[var3++] = (byte)var5;
         } else if (var5 > 2047) {
            var2[var3++] = (byte)(224 | var5 >> 12 & 15);
            var2[var3++] = (byte)(128 | var5 >> 6 & 63);
            var2[var3++] = (byte)(128 | var5 >> 0 & 63);
         } else {
            var2[var3++] = (byte)(192 | var5 >> 6 & 31);
            var2[var3++] = (byte)(128 | var5 >> 0 & 63);
         }
      }

      byte[] var9 = new byte[var3];
      System.arraycopy(var2, 0, var9, 0, var3);
      return var9;
   }

   private void copyClobContent(InternalClob var1) throws IOException, SQLException {
      try {
         long var2 = var1.getCharLengthIfKnown();
         if (var2 == -1L) {
            this.cachedCharLength = this.bytes.copyUtf8Data(var1.getRawByteStream(), Long.MAX_VALUE);
         } else {
            this.cachedCharLength = var2;
            this.bytes.copyData(var1.getRawByteStream(), Long.MAX_VALUE);
         }

      } catch (StandardException var4) {
         throw Util.generateCsSQLException(var4);
      }
   }

   private void copyClobContent(InternalClob var1, long var2) throws IOException, SQLException {
      try {
         long var4 = var1.getCharLengthIfKnown();
         if (var4 <= var2 && var4 != -1L) {
            if (var4 != var2) {
               throw new EOFException();
            }

            this.cachedCharLength = var4;
            this.bytes.copyData(var1.getRawByteStream(), Long.MAX_VALUE);
         } else {
            this.cachedCharLength = this.bytes.copyUtf8Data(var1.getRawByteStream(), var2);
         }

      } catch (StandardException var6) {
         throw Util.generateCsSQLException(var6);
      }
   }

   private final void checkIfValid() {
      if (this.released) {
         throw new IllegalStateException("The Clob has been released and is not valid");
      }
   }

   private final void updateInternalState(long var1) {
      if (this.internalReader != null) {
         this.internalReader.close();
         this.internalReader = null;
         this.unclosableInternalReader = null;
      }

      if (var1 < this.posCache.getCharPos()) {
         this.posCache.reset();
      }

      this.cachedCharLength = 0L;
   }

   private final CharacterStreamDescriptor getCSD() throws IOException {
      return (new CharacterStreamDescriptor.Builder()).positionAware(true).maxCharLength(2147483647L).stream(this.bytes.getInputStream(0L)).bufferable(this.bytes.getLength() > 4096L).byteLength(this.bytes.getLength()).charLength(this.cachedCharLength).build();
   }

   private static class CharToBytePositionCache {
      private long charPos = 1L;
      private long bytePos = 0L;

      CharToBytePositionCache() {
      }

      long getBytePos() {
         return this.bytePos;
      }

      long getCharPos() {
         return this.charPos;
      }

      void updateCachedPos(long var1, long var3) {
         if (var1 - 1L > var3) {
            throw new IllegalArgumentException("(charPos -1) cannot be greater than bytePos; " + (var1 - 1L) + " > " + var3);
         } else {
            this.charPos = var1;
            this.bytePos = var3;
         }
      }

      void reset() {
         this.charPos = 1L;
         this.bytePos = 0L;
      }
   }
}
