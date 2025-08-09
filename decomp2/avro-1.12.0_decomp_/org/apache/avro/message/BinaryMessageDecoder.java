package org.apache.avro.message;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.avro.Schema;
import org.apache.avro.SchemaNormalization;
import org.apache.avro.generic.GenericData;
import org.apache.avro.util.internal.ThreadLocalWithInitial;

public class BinaryMessageDecoder extends MessageDecoder.BaseDecoder {
   private static final ThreadLocal HEADER_BUFFER = ThreadLocalWithInitial.of(() -> new byte[10]);
   private static final ThreadLocal FP_BUFFER = ThreadLocalWithInitial.of(() -> {
      byte[] header = (byte[])HEADER_BUFFER.get();
      return ByteBuffer.wrap(header).order(ByteOrder.LITTLE_ENDIAN);
   });
   private final GenericData model;
   private final Schema readSchema;
   private final SchemaStore resolver;
   private final Map codecByFingerprint;

   public BinaryMessageDecoder(GenericData model, Schema readSchema) {
      this(model, readSchema, (SchemaStore)null);
   }

   public BinaryMessageDecoder(GenericData model, Schema readSchema, SchemaStore resolver) {
      this.codecByFingerprint = new ConcurrentHashMap();
      this.model = model;
      this.readSchema = readSchema;
      this.resolver = resolver;
      if (readSchema != null) {
         this.addSchema(readSchema);
      }

   }

   public void addSchema(Schema writeSchema) {
      long fp = SchemaNormalization.parsingFingerprint64(writeSchema);
      Schema actualReadSchema = this.readSchema != null ? this.readSchema : writeSchema;
      this.codecByFingerprint.put(fp, new RawMessageDecoder(this.model, writeSchema, actualReadSchema));
   }

   private RawMessageDecoder getDecoder(long fp) {
      RawMessageDecoder<D> decoder = (RawMessageDecoder)this.codecByFingerprint.get(fp);
      if (decoder != null) {
         return decoder;
      } else {
         if (this.resolver != null) {
            Schema writeSchema = this.resolver.findByFingerprint(fp);
            if (writeSchema != null) {
               this.addSchema(writeSchema);
               return (RawMessageDecoder)this.codecByFingerprint.get(fp);
            }
         }

         throw new MissingSchemaException("Cannot resolve schema for fingerprint: " + fp);
      }
   }

   public Object decode(InputStream stream, Object reuse) throws IOException {
      byte[] header = (byte[])HEADER_BUFFER.get();

      try {
         if (!this.readFully(stream, header)) {
            throw new BadHeaderException("Not enough header bytes");
         }
      } catch (IOException e) {
         throw new IOException("Failed to read header and fingerprint bytes", e);
      }

      if (BinaryMessageEncoder.V1_HEADER[0] == header[0] && BinaryMessageEncoder.V1_HEADER[1] == header[1]) {
         RawMessageDecoder<D> decoder = this.getDecoder(((ByteBuffer)FP_BUFFER.get()).getLong(2));
         return decoder.decode(stream, reuse);
      } else {
         throw new BadHeaderException(String.format("Unrecognized header bytes: 0x%02X 0x%02X", header[0], header[1]));
      }
   }

   private boolean readFully(InputStream stream, byte[] bytes) throws IOException {
      int pos;
      int bytesRead;
      for(pos = 0; bytes.length - pos > 0 && (bytesRead = stream.read(bytes, pos, bytes.length - pos)) > 0; pos += bytesRead) {
      }

      return pos == bytes.length;
   }
}
