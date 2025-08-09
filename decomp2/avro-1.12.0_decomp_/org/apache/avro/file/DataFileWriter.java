package org.apache.avro.file;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.Flushable;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.apache.avro.AvroRuntimeException;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.util.NonCopyingByteArrayOutputStream;
import org.apache.commons.compress.utils.IOUtils;

public class DataFileWriter implements Closeable, Flushable {
   private Schema schema;
   private DatumWriter dout;
   private OutputStream underlyingStream;
   private BufferedFileOutputStream out;
   private BinaryEncoder vout;
   private final Map meta = new HashMap();
   private long blockCount;
   private NonCopyingByteArrayOutputStream buffer;
   private BinaryEncoder bufOut;
   private byte[] sync;
   private int syncInterval = 64000;
   private Function initEncoder = (out) -> (new EncoderFactory()).directBinaryEncoder(out, (BinaryEncoder)null);
   private boolean isOpen;
   private Codec codec;
   private boolean flushOnEveryBlock = true;
   private static final SecureRandom RNG = new SecureRandom();

   public DataFileWriter(DatumWriter dout) {
      this.dout = dout;
   }

   private void assertOpen() {
      if (!this.isOpen) {
         throw new AvroRuntimeException("not open");
      }
   }

   private void assertNotOpen() {
      if (this.isOpen) {
         throw new AvroRuntimeException("already open");
      }
   }

   public DataFileWriter setCodec(CodecFactory c) {
      this.assertNotOpen();
      this.codec = c.createInstance();
      this.setMetaInternal("avro.codec", this.codec.getName());
      return this;
   }

   public DataFileWriter setSyncInterval(int syncInterval) {
      if (syncInterval >= 32 && syncInterval <= 1073741824) {
         this.syncInterval = syncInterval;
         return this;
      } else {
         throw new IllegalArgumentException("Invalid syncInterval value: " + syncInterval);
      }
   }

   public DataFileWriter setEncoder(Function initEncoderFunc) {
      this.initEncoder = initEncoderFunc;
      return this;
   }

   public DataFileWriter create(Schema schema, File file) throws IOException {
      SyncableFileOutputStream sfos = new SyncableFileOutputStream(file);

      try {
         return this.create(schema, sfos, (byte[])null);
      } catch (Throwable e) {
         IOUtils.closeQuietly(sfos);
         throw e;
      }
   }

   public DataFileWriter create(Schema schema, OutputStream outs) throws IOException {
      return this.create(schema, outs, (byte[])null);
   }

   public DataFileWriter create(Schema schema, OutputStream outs, byte[] sync) throws IOException {
      this.assertNotOpen();
      this.schema = schema;
      this.setMetaInternal("avro.schema", schema.toString());
      if (sync == null) {
         this.sync = generateSync();
      } else {
         if (sync.length != 16) {
            throw new IOException("sync must be exactly 16 bytes");
         }

         this.sync = sync;
      }

      this.init(outs);
      this.vout.writeFixed(DataFileConstants.MAGIC);
      this.vout.writeMapStart();
      this.vout.setItemCount((long)this.meta.size());

      for(Map.Entry entry : this.meta.entrySet()) {
         this.vout.startItem();
         this.vout.writeString((String)entry.getKey());
         this.vout.writeBytes((byte[])entry.getValue());
      }

      this.vout.writeMapEnd();
      this.vout.writeFixed(this.sync);
      this.vout.flush();
      return this;
   }

   public void setFlushOnEveryBlock(boolean flushOnEveryBlock) {
      this.flushOnEveryBlock = flushOnEveryBlock;
   }

   public boolean isFlushOnEveryBlock() {
      return this.flushOnEveryBlock;
   }

   public DataFileWriter appendTo(File file) throws IOException {
      SeekableInput input = new SeekableFileInput(file);

      DataFileWriter var4;
      try {
         OutputStream output = new SyncableFileOutputStream(file, true);
         var4 = this.appendTo(input, output);
      } catch (Throwable var6) {
         try {
            input.close();
         } catch (Throwable var5) {
            var6.addSuppressed(var5);
         }

         throw var6;
      }

      input.close();
      return var4;
   }

   public DataFileWriter appendTo(SeekableInput in, OutputStream out) throws IOException {
      this.assertNotOpen();
      DataFileReader<D> reader = new DataFileReader(in, new GenericDatumReader());
      this.schema = reader.getSchema();
      this.sync = reader.getHeader().sync;
      this.meta.putAll(reader.getHeader().meta);
      byte[] codecBytes = (byte[])this.meta.get("avro.codec");
      if (codecBytes != null) {
         String strCodec = new String(codecBytes, StandardCharsets.UTF_8);
         this.codec = CodecFactory.fromString(strCodec).createInstance();
      } else {
         this.codec = CodecFactory.nullCodec().createInstance();
      }

      this.init(out);
      return this;
   }

   private void init(OutputStream outs) throws IOException {
      this.underlyingStream = outs;
      this.out = new BufferedFileOutputStream(outs);
      EncoderFactory efactory = new EncoderFactory();
      this.vout = efactory.directBinaryEncoder(this.out, (BinaryEncoder)null);
      this.dout.setSchema(this.schema);
      this.buffer = new NonCopyingByteArrayOutputStream(Math.min((int)((double)this.syncInterval * (double)1.25F), 1073741822));
      this.bufOut = (BinaryEncoder)this.initEncoder.apply(this.buffer);
      if (this.codec == null) {
         this.codec = CodecFactory.nullCodec().createInstance();
      }

      this.isOpen = true;
   }

   private static byte[] generateSync() {
      byte[] sync = new byte[16];
      RNG.nextBytes(sync);
      return sync;
   }

   private DataFileWriter setMetaInternal(String key, byte[] value) {
      this.assertNotOpen();
      this.meta.put(key, value);
      return this;
   }

   private DataFileWriter setMetaInternal(String key, String value) {
      return this.setMetaInternal(key, value.getBytes(StandardCharsets.UTF_8));
   }

   public DataFileWriter setMeta(String key, byte[] value) {
      if (isReservedMeta(key)) {
         throw new AvroRuntimeException("Cannot set reserved meta key: " + key);
      } else {
         return this.setMetaInternal(key, value);
      }
   }

   public static boolean isReservedMeta(String key) {
      return key.startsWith("avro.");
   }

   public DataFileWriter setMeta(String key, String value) {
      return this.setMeta(key, value.getBytes(StandardCharsets.UTF_8));
   }

   public DataFileWriter setMeta(String key, long value) {
      return this.setMeta(key, Long.toString(value));
   }

   public void append(Object datum) throws IOException {
      this.assertOpen();
      int usedBuffer = this.bufferInUse();

      try {
         this.dout.write(datum, this.bufOut);
      } catch (RuntimeException | IOException e) {
         this.resetBufferTo(usedBuffer);
         throw new AppendWriteException(e);
      }

      ++this.blockCount;
      this.writeIfBlockFull();
   }

   private void resetBufferTo(int size) throws IOException {
      this.bufOut.flush();
      byte[] data = this.buffer.toByteArray();
      this.buffer.reset();
      this.buffer.write(data, 0, size);
   }

   public void appendEncoded(ByteBuffer datum) throws IOException {
      this.assertOpen();
      this.bufOut.writeFixed(datum);
      ++this.blockCount;
      this.writeIfBlockFull();
   }

   private int bufferInUse() {
      return this.buffer.size() + this.bufOut.bytesBuffered();
   }

   private void writeIfBlockFull() throws IOException {
      if (this.bufferInUse() >= this.syncInterval) {
         this.writeBlock();
      }

   }

   public void appendAllFrom(DataFileStream otherFile, boolean recompress) throws IOException {
      this.assertOpen();
      Schema otherSchema = otherFile.getSchema();
      if (!this.schema.equals(otherSchema)) {
         throw new IOException("Schema from file " + String.valueOf(otherFile) + " does not match");
      } else {
         this.writeBlock();
         Codec otherCodec = otherFile.resolveCodec();
         DataFileStream.DataBlock nextBlockRaw = null;
         if (this.codec.equals(otherCodec) && !recompress) {
            while(otherFile.hasNextBlock()) {
               nextBlockRaw = otherFile.nextRawBlock(nextBlockRaw);
               nextBlockRaw.writeBlockTo(this.vout, this.sync);
            }
         } else {
            while(otherFile.hasNextBlock()) {
               nextBlockRaw = otherFile.nextRawBlock(nextBlockRaw);
               nextBlockRaw.decompressUsing(otherCodec);
               nextBlockRaw.compressUsing(this.codec);
               nextBlockRaw.writeBlockTo(this.vout, this.sync);
            }
         }

      }
   }

   private void writeBlock() throws IOException {
      if (this.blockCount > 0L) {
         try {
            this.bufOut.flush();
            ByteBuffer uncompressed = this.buffer.asByteBuffer();
            DataFileStream.DataBlock block = new DataFileStream.DataBlock(uncompressed, this.blockCount);
            block.setFlushOnWrite(this.flushOnEveryBlock);
            block.compressUsing(this.codec);
            block.writeBlockTo(this.vout, this.sync);
         } finally {
            this.buffer.reset();
            this.blockCount = 0L;
         }
      }

   }

   public long sync() throws IOException {
      this.assertOpen();
      this.writeBlock();
      return this.out.tell();
   }

   public void flush() throws IOException {
      this.sync();
      this.vout.flush();
   }

   public void fSync() throws IOException {
      this.flush();
      if (this.underlyingStream instanceof Syncable) {
         ((Syncable)this.underlyingStream).sync();
      } else if (this.underlyingStream instanceof FileOutputStream) {
         ((FileOutputStream)this.underlyingStream).getFD().sync();
      }

   }

   public void close() throws IOException {
      if (this.isOpen) {
         this.flush();
         this.out.close();
         this.isOpen = false;
      }

   }

   public static class AppendWriteException extends RuntimeException {
      public AppendWriteException(Exception e) {
         super(e);
      }
   }

   private class BufferedFileOutputStream extends BufferedOutputStream {
      private long position;

      public BufferedFileOutputStream(OutputStream out) throws IOException {
         super((OutputStream)null);
         this.out = new PositionFilter(out);
      }

      public long tell() {
         return this.position + (long)this.count;
      }

      public synchronized void flush() throws IOException {
         try {
            super.flush();
         } finally {
            this.count = 0;
         }

      }

      private class PositionFilter extends FilterOutputStream {
         public PositionFilter(OutputStream out) throws IOException {
            super(out);
         }

         public void write(byte[] b, int off, int len) throws IOException {
            this.out.write(b, off, len);
            BufferedFileOutputStream var10000 = BufferedFileOutputStream.this;
            var10000.position += (long)len;
         }
      }
   }
}
