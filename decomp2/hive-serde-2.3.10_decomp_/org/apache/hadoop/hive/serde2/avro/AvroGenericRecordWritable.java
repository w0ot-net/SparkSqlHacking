package org.apache.hadoop.hive.serde2.avro;

import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.server.UID;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileStream;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.EncoderFactory;
import org.apache.hadoop.io.Writable;

public class AvroGenericRecordWritable implements Writable {
   GenericRecord record;
   private BinaryDecoder binaryDecoder;
   private Schema fileSchema;
   private UID recordReaderID;

   public GenericRecord getRecord() {
      return this.record;
   }

   public void setRecord(GenericRecord record) {
      this.record = record;
   }

   public AvroGenericRecordWritable() {
   }

   public AvroGenericRecordWritable(GenericRecord record) {
      this.record = record;
   }

   public void write(DataOutput out) throws IOException {
      String schemaString = this.record.getSchema().toString(false);
      out.writeUTF(schemaString);
      schemaString = this.fileSchema.toString(false);
      out.writeUTF(schemaString);
      this.recordReaderID.write(out);
      GenericDatumWriter<GenericRecord> gdw = new GenericDatumWriter();
      BinaryEncoder be = EncoderFactory.get().directBinaryEncoder((DataOutputStream)out, (BinaryEncoder)null);
      gdw.setSchema(this.record.getSchema());
      gdw.write(this.record, be);
   }

   public void readFields(DataInput in) throws IOException {
      Schema schema = AvroSerdeUtils.getSchemaFor(in.readUTF());
      this.fileSchema = AvroSerdeUtils.getSchemaFor(in.readUTF());
      this.recordReaderID = UID.read(in);
      this.record = new GenericData.Record(schema);
      this.binaryDecoder = DecoderFactory.defaultFactory().createBinaryDecoder((InputStream)in, this.binaryDecoder);
      GenericDatumReader<GenericRecord> gdr = new GenericDatumReader(schema);
      this.record = (GenericRecord)gdr.read(this.record, this.binaryDecoder);
   }

   public void readFields(byte[] bytes, int offset, int length, Schema writerSchema, Schema readerSchema) throws IOException {
      this.fileSchema = writerSchema;
      this.record = new GenericData.Record(writerSchema);
      this.binaryDecoder = DecoderFactory.get().binaryDecoder(bytes, offset, length - offset, this.binaryDecoder);
      GenericDatumReader<GenericRecord> gdr = new GenericDatumReader(writerSchema, readerSchema);
      this.record = (GenericRecord)gdr.read((Object)null, this.binaryDecoder);
   }

   public void readFields(byte[] bytes, Schema writerSchema, Schema readerSchema) throws IOException {
      this.fileSchema = writerSchema;
      this.record = new GenericData.Record(writerSchema);
      GenericDatumReader<GenericRecord> gdr = new GenericDatumReader();
      gdr.setExpected(readerSchema);
      ByteArrayInputStream is = new ByteArrayInputStream(bytes);
      DataFileStream<GenericRecord> dfr = new DataFileStream(is, gdr);
      this.record = (GenericRecord)dfr.next(this.record);
      dfr.close();
   }

   public UID getRecordReaderID() {
      return this.recordReaderID;
   }

   public void setRecordReaderID(UID recordReaderID) {
      this.recordReaderID = recordReaderID;
   }

   public Schema getFileSchema() {
      return this.fileSchema;
   }

   public void setFileSchema(Schema originalSchema) {
      this.fileSchema = originalSchema;
   }
}
