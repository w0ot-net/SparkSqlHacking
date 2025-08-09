package org.apache.avro.file;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.avro.InvalidAvroMagicException;
import org.apache.avro.Schema;
import org.apache.avro.UnknownAvroCodecException;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.util.Utf8;

public class DataFileReader12 implements FileReader, Closeable {
   private static final byte VERSION = 0;
   static final byte[] MAGIC = new byte[]{79, 98, 106, 0};
   private static final long FOOTER_BLOCK = -1L;
   private static final int SYNC_SIZE = 16;
   private static final String SCHEMA = "schema";
   private static final String SYNC = "sync";
   private static final String CODEC = "codec";
   private static final String NULL_CODEC = "null";
   private Schema schema;
   private DatumReader reader;
   private DataFileReader.SeekableInputStream in;
   private BinaryDecoder vin;
   private Map meta = new HashMap();
   private long blockCount;
   private long blockStart;
   private byte[] sync = new byte[16];
   private byte[] syncBuffer = new byte[16];
   private Object peek;

   public DataFileReader12(SeekableInput sin, DatumReader reader) throws IOException {
      this.in = new DataFileReader.SeekableInputStream(sin);
      byte[] magic = new byte[4];
      this.in.seek(0L);
      this.in.read(magic);
      if (!Arrays.equals(MAGIC, magic)) {
         throw new InvalidAvroMagicException("Not a data file.");
      } else {
         long length = this.in.length();
         this.in.seek(length - 4L);
         int footerSize = (this.in.read() << 24) + (this.in.read() << 16) + (this.in.read() << 8) + this.in.read();
         this.seek(length - (long)footerSize);
         long l = this.vin.readMapStart();
         if (l > 0L) {
            do {
               for(long i = 0L; i < l; ++i) {
                  String key = this.vin.readString((Utf8)null).toString();
                  ByteBuffer value = this.vin.readBytes((ByteBuffer)null);
                  byte[] bb = new byte[value.remaining()];
                  value.get(bb);
                  this.meta.put(key, bb);
               }
            } while((l = this.vin.mapNext()) != 0L);
         }

         this.sync = this.getMeta("sync");
         String codec = this.getMetaString("codec");
         if (codec != null && !codec.equals("null")) {
            throw new UnknownAvroCodecException("Unknown codec: " + codec);
         } else {
            this.schema = (new Schema.Parser()).parse(this.getMetaString("schema"));
            this.reader = reader;
            reader.setSchema(this.schema);
            this.seek((long)MAGIC.length);
         }
      }
   }

   public synchronized byte[] getMeta(String key) {
      return (byte[])this.meta.get(key);
   }

   public synchronized String getMetaString(String key) {
      byte[] value = this.getMeta(key);
      return value == null ? null : new String(value, StandardCharsets.UTF_8);
   }

   public synchronized long getMetaLong(String key) {
      return Long.parseLong(this.getMetaString(key));
   }

   public Schema getSchema() {
      return this.schema;
   }

   public Iterator iterator() {
      return this;
   }

   public boolean hasNext() {
      if (this.peek == null && this.blockCount == 0L) {
         this.peek = this.next();
         return this.peek != null;
      } else {
         return true;
      }
   }

   public Object next() {
      if (this.peek != null) {
         D result = (D)this.peek;
         this.peek = null;
         return result;
      } else {
         try {
            return this.next((Object)null);
         } catch (IOException e) {
            throw new RuntimeException(e);
         }
      }
   }

   public void remove() {
      throw new UnsupportedOperationException();
   }

   public synchronized Object next(Object reuse) throws IOException {
      while(this.blockCount == 0L) {
         if (this.in.tell() == this.in.length()) {
            return null;
         }

         this.skipSync();
         this.blockCount = this.vin.readLong();
         if (this.blockCount == -1L) {
            this.seek(this.vin.readLong() + this.in.tell());
         }
      }

      --this.blockCount;
      return this.reader.read(reuse, this.vin);
   }

   private void skipSync() throws IOException {
      this.vin.readFixed(this.syncBuffer);
      if (!Arrays.equals(this.syncBuffer, this.sync)) {
         throw new IOException("Invalid sync!");
      }
   }

   public synchronized void seek(long position) throws IOException {
      this.in.seek(position);
      this.blockCount = 0L;
      this.blockStart = position;
      this.vin = DecoderFactory.get().binaryDecoder((InputStream)this.in, this.vin);
   }

   public synchronized void sync(long position) throws IOException {
      if (this.in.tell() + 16L >= this.in.length()) {
         this.seek(this.in.length());
      } else {
         this.in.seek(position);
         this.vin.readFixed(this.syncBuffer);

         for(int i = 0; this.in.tell() < this.in.length(); ++i) {
            int j;
            for(j = 0; j < this.sync.length && this.sync[j] == this.syncBuffer[(i + j) % this.sync.length]; ++j) {
            }

            if (j == this.sync.length) {
               this.seek(this.in.tell() - 16L);
               return;
            }

            this.syncBuffer[i % this.sync.length] = (byte)this.in.read();
         }

         this.seek(this.in.length());
      }
   }

   public boolean pastSync(long position) throws IOException {
      return this.blockStart >= position + 16L || this.blockStart >= this.in.length();
   }

   public long tell() throws IOException {
      return this.in.tell();
   }

   public synchronized void close() throws IOException {
      this.in.close();
   }
}
