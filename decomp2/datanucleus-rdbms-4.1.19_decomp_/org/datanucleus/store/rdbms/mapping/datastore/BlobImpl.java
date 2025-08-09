package org.datanucleus.store.rdbms.mapping.datastore;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.StreamCorruptedException;
import java.sql.Blob;
import java.sql.SQLException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.util.NucleusLogger;

public class BlobImpl implements Blob {
   private InputStream stream;
   private int length;
   private byte[] bytes;
   boolean freed = false;

   public BlobImpl(Object obj) throws IOException {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      ObjectOutputStream oos = new ObjectOutputStream(baos);
      oos.writeObject(obj);
      this.bytes = baos.toByteArray();
      this.stream = new ByteArrayInputStream(this.bytes);
      this.length = this.bytes.length;
   }

   public BlobImpl(byte[] bytes) {
      this.bytes = bytes;
      this.stream = new ByteArrayInputStream(bytes);
      this.length = bytes.length;
   }

   public BlobImpl(InputStream stream) {
      this.stream = stream;
   }

   public Object getObject() throws SQLException {
      if (this.freed) {
         throw new SQLException("free() has been called");
      } else {
         ByteArrayInputStream bais = new ByteArrayInputStream(this.bytes);

         try {
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
         } catch (StreamCorruptedException e) {
            String msg = "StreamCorruptedException: object is corrupted";
            NucleusLogger.DATASTORE.error(msg);
            throw (new NucleusUserException(msg, e)).setFatal();
         } catch (IOException e) {
            String msg = "IOException: error when reading object";
            NucleusLogger.DATASTORE.error(msg);
            throw (new NucleusUserException(msg, e)).setFatal();
         } catch (ClassNotFoundException e) {
            String msg = "ClassNotFoundException: error when creating object";
            NucleusLogger.DATASTORE.error(msg);
            throw (new NucleusUserException(msg, e)).setFatal();
         }
      }
   }

   public long length() throws SQLException {
      if (this.freed) {
         throw new SQLException("free() has been called");
      } else {
         return (long)this.length;
      }
   }

   public byte[] getBytes(long pos, int length) throws SQLException {
      if (this.freed) {
         throw new SQLException("free() has been called");
      } else {
         byte[] bytesToReturn = new byte[length];

         for(int i = 0; i < length; ++i) {
            bytesToReturn[i] = this.bytes[(int)pos + i];
         }

         return bytesToReturn;
      }
   }

   public int setBytes(long value, byte[] bytes, int pos, int length) throws SQLException {
      if (this.freed) {
         throw new SQLException("free() has been called");
      } else {
         return -1;
      }
   }

   public void truncate(long value) throws SQLException {
      if (this.freed) {
         throw new SQLException("free() has been called");
      }
   }

   public int setBytes(long value, byte[] bytes) throws SQLException {
      if (this.freed) {
         throw new SQLException("free() has been called");
      } else {
         return -1;
      }
   }

   public InputStream getBinaryStream() throws SQLException {
      if (this.freed) {
         throw new SQLException("free() has been called");
      } else {
         return this.stream;
      }
   }

   public InputStream getBinaryStream(long pos, long length) throws SQLException {
      if (this.freed) {
         throw new SQLException("free() has been called");
      } else {
         return this.stream;
      }
   }

   public OutputStream setBinaryStream(long value) throws SQLException {
      if (this.freed) {
         throw new SQLException("free() has been called");
      } else {
         return null;
      }
   }

   public void free() throws SQLException {
      if (!this.freed) {
         this.bytes = null;
         if (this.stream != null) {
            try {
               this.stream.close();
            } catch (IOException var2) {
            }
         }

         this.freed = true;
      }
   }

   public long position(byte[] pattern, long start) throws SQLException {
      if (this.freed) {
         throw new SQLException("free() has been called");
      } else {
         throw new UnsupportedOperationException("[BlobImpl.position] may not be called");
      }
   }

   public long position(Blob pattern, long start) throws SQLException {
      if (this.freed) {
         throw new SQLException("free() has been called");
      } else {
         throw new UnsupportedOperationException("[BlobImpl.position] may not be called");
      }
   }
}
