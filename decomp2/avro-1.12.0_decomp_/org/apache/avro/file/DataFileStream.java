package org.apache.avro.file;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import org.apache.avro.AvroRuntimeException;
import org.apache.avro.InvalidAvroMagicException;
import org.apache.avro.NameValidator;
import org.apache.avro.Schema;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.util.Utf8;

public class DataFileStream implements Iterator, Iterable, Closeable {
   private DatumReader reader;
   private long blockSize;
   private boolean availableBlock = false;
   private Header header;
   BinaryDecoder vin;
   BinaryDecoder datumIn = null;
   ByteBuffer blockBuffer;
   long blockCount;
   long blockRemaining;
   byte[] syncBuffer = new byte[16];
   private Codec codec;
   private DataBlock block = null;

   public DataFileStream(InputStream in, DatumReader reader) throws IOException {
      this.reader = reader;
      this.initialize(in, (byte[])null);
   }

   protected DataFileStream(DatumReader reader) throws IOException {
      this.reader = reader;
   }

   byte[] readMagic() throws IOException {
      if (this.vin == null) {
         throw new IOException("InputStream is not initialized");
      } else {
         byte[] magic = new byte[DataFileConstants.MAGIC.length];

         try {
            this.vin.readFixed(magic);
            return magic;
         } catch (IOException e) {
            throw new IOException("Not an Avro data file.", e);
         }
      }
   }

   void validateMagic(byte[] magic) throws InvalidAvroMagicException {
      if (!Arrays.equals(DataFileConstants.MAGIC, magic)) {
         throw new InvalidAvroMagicException("Not an Avro data file.");
      }
   }

   void initialize(InputStream in, byte[] magic) throws IOException {
      this.header = new Header();
      this.vin = DecoderFactory.get().binaryDecoder(in, this.vin);
      magic = magic == null ? this.readMagic() : magic;
      this.validateMagic(magic);
      long l = this.vin.readMapStart();
      if (l > 0L) {
         do {
            for(long i = 0L; i < l; ++i) {
               String key = this.vin.readString((Utf8)null).toString();
               ByteBuffer value = this.vin.readBytes((ByteBuffer)null);
               byte[] bb = new byte[value.remaining()];
               value.get(bb);
               this.header.meta.put(key, bb);
               this.header.metaKeyList.add(key);
            }
         } while((l = this.vin.mapNext()) != 0L);
      }

      this.vin.readFixed(this.header.sync);
      this.header.metaKeyList = Collections.unmodifiableList(this.header.metaKeyList);
      this.header.schema = (new Schema.Parser(NameValidator.NO_VALIDATION)).setValidateDefaults(false).parse(this.getMetaString("avro.schema"));
      this.codec = this.resolveCodec();
      this.reader.setSchema(this.header.schema);
   }

   void initialize(Header header) throws IOException {
      this.header = header;
      this.codec = this.resolveCodec();
      this.reader.setSchema(header.schema);
   }

   Codec resolveCodec() {
      String codecStr = this.getMetaString("avro.codec");
      return codecStr != null ? CodecFactory.fromString(codecStr).createInstance() : CodecFactory.nullCodec().createInstance();
   }

   public Header getHeader() {
      return this.header;
   }

   public Schema getSchema() {
      return this.header.schema;
   }

   public List getMetaKeys() {
      return this.header.metaKeyList;
   }

   public byte[] getMeta(String key) {
      return (byte[])this.header.meta.get(key);
   }

   public String getMetaString(String key) {
      byte[] value = this.getMeta(key);
      return value == null ? null : new String(value, StandardCharsets.UTF_8);
   }

   public long getMetaLong(String key) {
      return Long.parseLong(this.getMetaString(key));
   }

   public Iterator iterator() {
      return this;
   }

   public boolean hasNext() {
      try {
         if (this.blockRemaining == 0L) {
            if (null != this.datumIn) {
               boolean atEnd = this.datumIn.isEnd();
               if (!atEnd) {
                  throw new IOException("Block read partially, the data may be corrupt");
               }
            }

            if (this.hasNextBlock()) {
               this.block = this.nextRawBlock(this.block);
               this.block.decompressUsing(this.codec);
               this.blockBuffer = this.block.getAsByteBuffer();
               this.datumIn = DecoderFactory.get().binaryDecoder(this.blockBuffer.array(), this.blockBuffer.arrayOffset() + this.blockBuffer.position(), this.blockBuffer.remaining(), this.datumIn);
            }
         }

         return this.blockRemaining != 0L;
      } catch (EOFException var2) {
         return false;
      } catch (IOException e) {
         throw new AvroRuntimeException(e);
      }
   }

   public Object next() {
      try {
         return this.next((Object)null);
      } catch (IOException e) {
         throw new AvroRuntimeException(e);
      }
   }

   public Object next(Object reuse) throws IOException {
      if (!this.hasNext()) {
         throw new NoSuchElementException();
      } else {
         D result = (D)this.reader.read(reuse, this.datumIn);
         if (0L == --this.blockRemaining) {
            this.blockFinished();
         }

         return result;
      }
   }

   public ByteBuffer nextBlock() throws IOException {
      if (!this.hasNext()) {
         throw new NoSuchElementException();
      } else if (this.blockRemaining != this.blockCount) {
         throw new IllegalStateException("Not at block start.");
      } else {
         this.blockRemaining = 0L;
         this.blockFinished();
         this.datumIn = null;
         return this.blockBuffer;
      }
   }

   public long getBlockCount() {
      return this.blockCount;
   }

   public long getBlockSize() {
      return this.blockSize;
   }

   protected void blockFinished() throws IOException {
   }

   boolean hasNextBlock() {
      try {
         if (this.availableBlock) {
            return true;
         } else if (this.vin.isEnd()) {
            return false;
         } else {
            this.blockRemaining = this.vin.readLong();
            this.blockSize = this.vin.readLong();
            if (this.blockSize <= 2147483647L && this.blockSize >= 0L) {
               this.blockCount = this.blockRemaining;
               this.availableBlock = true;
               return true;
            } else {
               throw new IOException("Block size invalid or too large for this implementation: " + this.blockSize);
            }
         }
      } catch (EOFException var2) {
         return false;
      } catch (IOException e) {
         throw new AvroRuntimeException(e);
      }
   }

   DataBlock nextRawBlock(DataBlock reuse) throws IOException {
      if (!this.hasNextBlock()) {
         throw new NoSuchElementException();
      } else {
         if (reuse != null && reuse.data.length >= (int)this.blockSize) {
            reuse.numEntries = this.blockRemaining;
            reuse.blockSize = (int)this.blockSize;
         } else {
            reuse = new DataBlock(this.blockRemaining, (int)this.blockSize);
         }

         this.vin.readFixed(reuse.data, 0, reuse.blockSize);
         this.vin.readFixed(this.syncBuffer);
         this.availableBlock = false;
         if (!Arrays.equals(this.syncBuffer, this.header.sync)) {
            throw new IOException("Invalid sync!");
         } else {
            return reuse;
         }
      }
   }

   public void remove() {
      throw new UnsupportedOperationException();
   }

   public void close() throws IOException {
      this.vin.inputStream().close();
   }

   public static final class Header {
      Schema schema;
      Map meta = new HashMap();
      private transient List metaKeyList = new ArrayList();
      byte[] sync = new byte[16];

      private Header() {
      }
   }

   static class DataBlock {
      private byte[] data;
      private long numEntries;
      private int blockSize;
      private int offset = 0;
      private boolean flushOnWrite = true;

      private DataBlock(long numEntries, int blockSize) {
         this.data = new byte[blockSize];
         this.numEntries = numEntries;
         this.blockSize = blockSize;
      }

      DataBlock(ByteBuffer block, long numEntries) {
         this.data = block.array();
         this.blockSize = block.remaining();
         this.offset = block.arrayOffset() + block.position();
         this.numEntries = numEntries;
      }

      byte[] getData() {
         return this.data;
      }

      long getNumEntries() {
         return this.numEntries;
      }

      int getBlockSize() {
         return this.blockSize;
      }

      boolean isFlushOnWrite() {
         return this.flushOnWrite;
      }

      void setFlushOnWrite(boolean flushOnWrite) {
         this.flushOnWrite = flushOnWrite;
      }

      ByteBuffer getAsByteBuffer() {
         return ByteBuffer.wrap(this.data, this.offset, this.blockSize);
      }

      void decompressUsing(Codec c) throws IOException {
         ByteBuffer result = c.decompress(this.getAsByteBuffer());
         this.data = result.array();
         this.blockSize = result.remaining();
      }

      void compressUsing(Codec c) throws IOException {
         ByteBuffer result = c.compress(this.getAsByteBuffer());
         this.data = result.array();
         this.blockSize = result.remaining();
      }

      void writeBlockTo(BinaryEncoder e, byte[] sync) throws IOException {
         e.writeLong(this.numEntries);
         e.writeLong((long)this.blockSize);
         e.writeFixed(this.data, this.offset, this.blockSize);
         e.writeFixed(sync);
         if (this.flushOnWrite) {
            e.flush();
         }

      }
   }
}
