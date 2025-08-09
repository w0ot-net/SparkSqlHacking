package org.datanucleus.store.rdbms.mapping.datastore;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.sql.Clob;
import java.sql.SQLException;

public class ClobImpl implements Clob {
   private String string;
   private long length;
   private StringReader reader;
   private InputStream inputStream;
   boolean freed = false;

   public ClobImpl(String string) throws IOException {
      if (string == null) {
         throw new IllegalArgumentException("String cannot be null");
      } else {
         this.string = string;
         this.length = (long)string.length();
      }
   }

   public long length() throws SQLException {
      if (this.freed) {
         throw new SQLException("free() has been called");
      } else {
         return this.length;
      }
   }

   public void truncate(long len) throws SQLException {
      if (this.freed) {
         throw new SQLException("free() has been called");
      } else {
         throw new UnsupportedOperationException();
      }
   }

   public InputStream getAsciiStream() throws SQLException {
      if (this.freed) {
         throw new SQLException("free() has been called");
      } else {
         if (this.inputStream == null) {
            this.inputStream = new ByteArrayInputStream(this.string.getBytes());
         }

         return this.inputStream;
      }
   }

   public OutputStream setAsciiStream(long pos) throws SQLException {
      if (this.freed) {
         throw new SQLException("free() has been called");
      } else {
         throw new UnsupportedOperationException();
      }
   }

   public Reader getCharacterStream() throws SQLException {
      if (this.freed) {
         throw new SQLException("free() has been called");
      } else {
         if (this.reader == null) {
            this.reader = new StringReader(this.string);
         }

         return this.reader;
      }
   }

   public Writer setCharacterStream(long pos) throws SQLException {
      if (this.freed) {
         throw new SQLException("free() has been called");
      } else {
         throw new UnsupportedOperationException();
      }
   }

   public void free() throws SQLException {
      if (!this.freed) {
         this.string = null;
         if (this.reader != null) {
            this.reader.close();
         }

         if (this.inputStream != null) {
            try {
               this.inputStream.close();
            } catch (IOException var2) {
            }
         }

         this.freed = true;
      }
   }

   public Reader getCharacterStream(long pos, long length) throws SQLException {
      if (this.freed) {
         throw new SQLException("free() has been called");
      } else {
         if (this.reader == null) {
            this.reader = new StringReader(this.string);
         }

         return this.reader;
      }
   }

   public String getSubString(long pos, int length) throws SQLException {
      if (this.freed) {
         throw new SQLException("free() has been called");
      } else if (pos > 2147483647L) {
         throw new IllegalArgumentException("Initial position cannot be larger than 2147483647");
      } else if (pos + (long)length - 1L > this.length()) {
         throw new IndexOutOfBoundsException("The requested substring is greater than the actual length of the Clob String.");
      } else {
         return this.string.substring((int)pos - 1, (int)pos + length - 1);
      }
   }

   public int setString(long pos, String str) throws SQLException {
      if (this.freed) {
         throw new SQLException("free() has been called");
      } else {
         throw new UnsupportedOperationException();
      }
   }

   public int setString(long pos, String str, int offset, int len) throws SQLException {
      if (this.freed) {
         throw new SQLException("free() has been called");
      } else {
         throw new UnsupportedOperationException();
      }
   }

   public long position(String searchstr, long start) throws SQLException {
      if (this.freed) {
         throw new SQLException("free() has been called");
      } else {
         throw new UnsupportedOperationException();
      }
   }

   public long position(Clob searchstr, long start) throws SQLException {
      if (this.freed) {
         throw new SQLException("free() has been called");
      } else {
         throw new UnsupportedOperationException();
      }
   }
}
