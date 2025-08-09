package org.apache.avro.mapred.tether;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericEnumSymbol;
import org.apache.avro.specific.AvroGenerated;

@AvroGenerated
public enum TaskType implements GenericEnumSymbol {
   MAP,
   REDUCE;

   public static final Schema SCHEMA$ = (new Schema.Parser()).parse("{\"type\":\"enum\",\"name\":\"TaskType\",\"namespace\":\"org.apache.avro.mapred.tether\",\"symbols\":[\"MAP\",\"REDUCE\"]}");

   public static Schema getClassSchema() {
      return SCHEMA$;
   }

   public Schema getSchema() {
      return SCHEMA$;
   }

   // $FF: synthetic method
   private static TaskType[] $values() {
      return new TaskType[]{MAP, REDUCE};
   }
}
