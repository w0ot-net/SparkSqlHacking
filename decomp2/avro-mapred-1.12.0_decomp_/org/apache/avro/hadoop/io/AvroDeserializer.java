package org.apache.avro.hadoop.io;

import java.io.IOException;
import java.io.InputStream;
import org.apache.avro.Schema;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.mapred.AvroWrapper;
import org.apache.avro.reflect.ReflectData;
import org.apache.avro.reflect.ReflectDatumReader;
import org.apache.hadoop.io.serializer.Deserializer;

public abstract class AvroDeserializer implements Deserializer {
   private final Schema mWriterSchema;
   private final Schema mReaderSchema;
   final DatumReader mAvroDatumReader;
   private BinaryDecoder mAvroDecoder;

   protected AvroDeserializer(Schema writerSchema, Schema readerSchema, ClassLoader classLoader) {
      this.mWriterSchema = writerSchema;
      this.mReaderSchema = null != readerSchema ? readerSchema : writerSchema;
      this.mAvroDatumReader = new ReflectDatumReader(this.mWriterSchema, this.mReaderSchema, new ReflectData(classLoader));
   }

   protected AvroDeserializer(Schema writerSchema, Schema readerSchema, DatumReader datumReader) {
      this.mWriterSchema = writerSchema;
      this.mReaderSchema = null != readerSchema ? readerSchema : writerSchema;
      this.mAvroDatumReader = datumReader;
   }

   public Schema getWriterSchema() {
      return this.mWriterSchema;
   }

   public Schema getReaderSchema() {
      return this.mReaderSchema;
   }

   public void open(InputStream inputStream) throws IOException {
      this.mAvroDecoder = DecoderFactory.get().directBinaryDecoder(inputStream, this.mAvroDecoder);
   }

   public AvroWrapper deserialize(AvroWrapper avroWrapperToReuse) throws IOException {
      if (null == avroWrapperToReuse) {
         avroWrapperToReuse = (T)this.createAvroWrapper();
      }

      avroWrapperToReuse.datum(this.mAvroDatumReader.read(avroWrapperToReuse.datum(), this.mAvroDecoder));
      return avroWrapperToReuse;
   }

   public void close() throws IOException {
      this.mAvroDecoder.inputStream().close();
   }

   protected abstract AvroWrapper createAvroWrapper();
}
