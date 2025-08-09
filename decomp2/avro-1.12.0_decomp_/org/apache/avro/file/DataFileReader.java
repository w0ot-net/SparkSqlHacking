package org.apache.avro.file;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import org.apache.avro.InvalidAvroMagicException;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DecoderFactory;
import org.apache.commons.io.IOUtils;

public class DataFileReader extends DataFileStream implements FileReader {
   private final SeekableInputStream sin;
   private long blockStart;
   private int[] partialMatchTable;

   public static FileReader openReader(File file, DatumReader reader) throws IOException {
      SeekableFileInput input = new SeekableFileInput(file);

      try {
         return openReader((SeekableInput)input, reader);
      } catch (Throwable e) {
         IOUtils.closeQuietly(input);
         throw e;
      }
   }

   public static FileReader openReader(SeekableInput in, DatumReader reader) throws IOException {
      if (in.length() < (long)DataFileConstants.MAGIC.length) {
         throw new InvalidAvroMagicException("Not an Avro data file");
      } else {
         byte[] magic = new byte[DataFileConstants.MAGIC.length];
         in.seek(0L);
         int offset = 0;

         int bytesRead;
         for(int length = magic.length; length > 0; offset += bytesRead) {
            bytesRead = in.read(magic, offset, length);
            if (bytesRead < 0) {
               throw new EOFException("Unexpected EOF with " + length + " bytes remaining to read");
            }

            length -= bytesRead;
         }

         if (Arrays.equals(DataFileConstants.MAGIC, magic)) {
            return new DataFileReader(in, reader, magic);
         } else if (Arrays.equals(DataFileReader12.MAGIC, magic)) {
            return new DataFileReader12(in, reader);
         } else {
            throw new InvalidAvroMagicException("Not an Avro data file");
         }
      }
   }

   public static DataFileReader openReader(SeekableInput in, DatumReader reader, DataFileStream.Header header, boolean sync) throws IOException {
      DataFileReader<D> dreader = new DataFileReader(in, reader, header);
      if (sync) {
         dreader.sync(in.tell());
      } else {
         dreader.seek(in.tell());
      }

      return dreader;
   }

   public DataFileReader(File file, DatumReader reader) throws IOException {
      this(new SeekableFileInput(file), reader, true, (byte[])null);
   }

   public DataFileReader(SeekableInput sin, DatumReader reader) throws IOException {
      this(sin, reader, false, (byte[])null);
   }

   private DataFileReader(SeekableInput sin, DatumReader reader, byte[] magic) throws IOException {
      this(sin, reader, false, magic);
   }

   protected DataFileReader(SeekableInput sin, DatumReader reader, boolean closeOnError, byte[] magic) throws IOException {
      super(reader);

      try {
         this.sin = new SeekableInputStream(sin);
         this.initialize(this.sin, magic);
         this.blockFinished();
      } catch (Throwable e) {
         if (closeOnError) {
            IOUtils.closeQuietly(sin);
         }

         throw e;
      }
   }

   protected DataFileReader(SeekableInput sin, DatumReader reader, DataFileStream.Header header) throws IOException {
      super(reader);
      this.sin = new SeekableInputStream(sin);
      this.initialize(header);
   }

   public void seek(long position) throws IOException {
      this.sin.seek(position);
      this.vin = DecoderFactory.get().binaryDecoder((InputStream)this.sin, this.vin);
      this.datumIn = null;
      this.blockRemaining = 0L;
      this.blockFinished();
   }

   public void sync(final long position) throws IOException {
      this.seek(position);
      if (position == 0L && this.getMeta("avro.sync") != null) {
         this.initialize(this.sin, (byte[])null);
      } else {
         if (this.partialMatchTable == null) {
            this.partialMatchTable = this.computePartialMatchTable(this.getHeader().sync);
         }

         byte[] sync = this.getHeader().sync;
         InputStream in = this.vin.inputStream();
         int[] pm = this.partialMatchTable;
         long i = 0L;
         int b = in.read();

         for(int j = 0; b != -1; ++i) {
            byte cb;
            for(cb = (byte)b; j > 0 && sync[j] != cb; j = pm[j - 1]) {
            }

            if (sync[j] == cb) {
               ++j;
            }

            if (j == 16) {
               this.blockStart = position + i + 1L;
               return;
            }

            b = in.read();
         }

         this.blockStart = this.sin.tell();
      }
   }

   private int[] computePartialMatchTable(final byte[] pattern) {
      int[] pm = new int[pattern.length];
      int i = 1;
      int len = 0;

      while(i < pattern.length) {
         if (pattern[i] == pattern[len]) {
            int var10001 = i++;
            ++len;
            pm[var10001] = len;
         } else if (len > 0) {
            len = pm[len - 1];
         } else {
            ++i;
         }
      }

      return pm;
   }

   protected void blockFinished() throws IOException {
      this.blockStart = this.sin.tell() - (long)this.vin.inputStream().available();
   }

   public long previousSync() {
      return this.blockStart;
   }

   public boolean pastSync(long position) throws IOException {
      return this.blockStart >= position + 16L || this.blockStart >= this.sin.length();
   }

   public long tell() throws IOException {
      return this.sin.tell();
   }

   static class SeekableInputStream extends InputStream implements SeekableInput {
      private final byte[] oneByte = new byte[1];
      private final SeekableInput in;

      SeekableInputStream(SeekableInput in) {
         this.in = in;
      }

      public void seek(long p) throws IOException {
         if (p < 0L) {
            throw new IOException("Illegal seek: " + p);
         } else {
            this.in.seek(p);
         }
      }

      public long tell() throws IOException {
         return this.in.tell();
      }

      public long length() throws IOException {
         return this.in.length();
      }

      public int read(byte[] b) throws IOException {
         return this.in.read(b, 0, b.length);
      }

      public int read(byte[] b, int off, int len) throws IOException {
         return this.in.read(b, off, len);
      }

      public int read() throws IOException {
         int n = this.read(this.oneByte, 0, 1);
         return n == 1 ? this.oneByte[0] & 255 : n;
      }

      public long skip(long skip) throws IOException {
         long position = this.in.tell();
         long skipToPosition = position + skip;
         long length = this.in.length();
         this.in.seek(Math.min(skipToPosition, length));
         return this.in.tell() - position;
      }

      public void close() throws IOException {
         this.in.close();
         super.close();
      }

      public int available() throws IOException {
         long remaining = this.in.length() - this.in.tell();
         return (int)Math.min(remaining, 2147483647L);
      }
   }
}
