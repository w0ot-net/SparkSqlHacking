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

final class StoreStreamClob implements InternalClob {
   private volatile boolean released = false;
   private final PositionedStoreStream positionedStoreStream;
   private CharacterStreamDescriptor csd;
   private final ConnectionChild conChild;
   private final Object synchronizationObject;
   private UTF8Reader internalReader;
   private FilterReader unclosableInternalReader;

   public StoreStreamClob(CharacterStreamDescriptor var1, ConnectionChild var2) throws StandardException {
      try {
         this.positionedStoreStream = new PositionedStoreStream(var1.getStream());
      } catch (StandardException var4) {
         if (var4.getMessageId().equals("40XD0")) {
            throw StandardException.newException("XJ073.S", new Object[0]);
         }

         throw var4;
      } catch (IOException var5) {
         throw StandardException.newException("XCL30.S", var5, new Object[]{"CLOB"});
      }

      this.conChild = var2;
      this.synchronizationObject = var2.getConnectionSynchronization();
      this.csd = (new CharacterStreamDescriptor.Builder()).copyState(var1).stream(this.positionedStoreStream).positionAware(true).curBytePos(0L).curCharPos(0L).build();
   }

   public void release() {
      if (!this.released) {
         if (this.internalReader != null) {
            this.internalReader.close();
         }

         this.positionedStoreStream.closeStream();
         this.released = true;
      }

   }

   public long getCharLength() throws SQLException {
      this.checkIfValid();
      if (this.csd.getCharLength() == 0L) {
         long var1 = 0L;
         synchronized(this.synchronizationObject) {
            this.conChild.setupContextStack();

            try {
               var1 = UTF8Util.skipUntilEOF(new BufferedInputStream(this.getRawByteStream()));
            } catch (Throwable var10) {
               throw noStateChangeLOB(var10);
            } finally {
               ConnectionChild.restoreIntrFlagIfSeen(true, this.conChild.getEmbedConnection());
               this.conChild.restoreContextStack();
            }
         }

         this.csd = (new CharacterStreamDescriptor.Builder()).copyState(this.csd).charLength(var1).build();
      }

      return this.csd.getCharLength();
   }

   public long getCharLengthIfKnown() {
      this.checkIfValid();
      return this.csd.getCharLength() == 0L ? -1L : this.csd.getCharLength();
   }

   public InputStream getRawByteStream() throws IOException, SQLException {
      this.checkIfValid();

      try {
         this.positionedStoreStream.reposition(this.csd.getDataOffset());
      } catch (StandardException var2) {
         throw Util.generateCsSQLException(var2);
      }

      return this.positionedStoreStream;
   }

   public Reader getReader(long var1) throws IOException, SQLException {
      this.checkIfValid();

      try {
         this.positionedStoreStream.reposition(0L);
      } catch (StandardException var8) {
         throw Util.generateCsSQLException(var8);
      }

      UTF8Reader var3 = new UTF8Reader(this.csd, this.conChild, this.synchronizationObject);

      long var6;
      for(long var4 = var1 - 1L; var4 > 0L; var4 -= var6) {
         var6 = ((Reader)var3).skip(var4);
         if (var6 <= 0L) {
            throw new EOFException("Reached end-of-stream prematurely");
         }
      }

      return var3;
   }

   public Reader getInternalReader(long var1) throws IOException, SQLException {
      if (this.internalReader == null) {
         if (this.positionedStoreStream.getPosition() != 0L) {
            try {
               this.positionedStoreStream.resetStream();
            } catch (StandardException var5) {
               throw Util.generateCsSQLException(var5);
            }
         }

         this.internalReader = new UTF8Reader(this.csd, this.conChild, this.synchronizationObject);
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

   public long getUpdateCount() {
      return 0L;
   }

   public Writer getWriter(long var1) {
      throw new UnsupportedOperationException("A StoreStreamClob object is not updatable");
   }

   public long insertString(String var1, long var2) {
      throw new UnsupportedOperationException("A StoreStreamClob object is not updatable");
   }

   public boolean isReleased() {
      return this.released;
   }

   public boolean isWritable() {
      return false;
   }

   public void truncate(long var1) {
      throw new UnsupportedOperationException("A StoreStreamClob object is not updatable");
   }

   private static SQLException noStateChangeLOB(Throwable var0) {
      if (var0 instanceof StandardException && ((StandardException)var0).getMessageId().equals("40XD0")) {
         var0 = StandardException.newException("XJ073.S", new Object[0]);
      }

      return EmbedResultSet.noStateChangeException((Throwable)var0);
   }

   private void checkIfValid() {
      if (this.released) {
         throw new IllegalStateException("The Clob has been released and is not valid");
      }
   }
}
