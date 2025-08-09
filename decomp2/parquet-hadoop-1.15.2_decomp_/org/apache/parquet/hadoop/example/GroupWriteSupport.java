package org.apache.parquet.hadoop.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.apache.hadoop.conf.Configuration;
import org.apache.parquet.conf.HadoopParquetConfiguration;
import org.apache.parquet.conf.ParquetConfiguration;
import org.apache.parquet.example.data.Group;
import org.apache.parquet.example.data.GroupWriter;
import org.apache.parquet.hadoop.api.WriteSupport;
import org.apache.parquet.io.api.RecordConsumer;
import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.MessageTypeParser;

public class GroupWriteSupport extends WriteSupport {
   public static final String PARQUET_EXAMPLE_SCHEMA = "parquet.example.schema";
   private MessageType schema;
   private GroupWriter groupWriter;
   private Map extraMetaData;

   public static void setSchema(MessageType schema, Configuration configuration) {
      configuration.set("parquet.example.schema", schema.toString());
   }

   public static MessageType getSchema(Configuration configuration) {
      return getSchema((ParquetConfiguration)(new HadoopParquetConfiguration(configuration)));
   }

   public static MessageType getSchema(ParquetConfiguration configuration) {
      return MessageTypeParser.parseMessageType((String)Objects.requireNonNull(configuration.get("parquet.example.schema"), "parquet.example.schema"));
   }

   public GroupWriteSupport() {
      this((MessageType)null, new HashMap());
   }

   GroupWriteSupport(MessageType schema) {
      this(schema, new HashMap());
   }

   GroupWriteSupport(MessageType schema, Map extraMetaData) {
      this.schema = schema;
      this.extraMetaData = extraMetaData;
   }

   public String getName() {
      return "example";
   }

   public WriteSupport.WriteContext init(Configuration configuration) {
      return this.init((ParquetConfiguration)(new HadoopParquetConfiguration(configuration)));
   }

   public WriteSupport.WriteContext init(ParquetConfiguration configuration) {
      if (this.schema == null) {
         this.schema = getSchema(configuration);
      }

      return new WriteSupport.WriteContext(this.schema, this.extraMetaData);
   }

   public void prepareForWrite(RecordConsumer recordConsumer) {
      this.groupWriter = new GroupWriter(recordConsumer, this.schema);
   }

   public void write(Group record) {
      this.groupWriter.write(record);
   }
}
