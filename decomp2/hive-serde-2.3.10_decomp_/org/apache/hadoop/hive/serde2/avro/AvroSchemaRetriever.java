package org.apache.hadoop.hive.serde2.avro;

import org.apache.avro.Schema;

public abstract class AvroSchemaRetriever {
   public abstract Schema retrieveWriterSchema(Object var1);

   public Schema retrieveReaderSchema(Object source) {
      return null;
   }

   public int getOffset() {
      return 0;
   }
}
