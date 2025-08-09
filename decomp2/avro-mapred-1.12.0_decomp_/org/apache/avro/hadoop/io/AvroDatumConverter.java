package org.apache.avro.hadoop.io;

import org.apache.avro.Schema;

public abstract class AvroDatumConverter {
   public abstract Object convert(Object input);

   public abstract Schema getWriterSchema();
}
