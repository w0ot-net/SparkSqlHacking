package org.apache.avro.specific;

import org.apache.avro.Schema;
import org.apache.avro.data.RecordBuilderBase;

public abstract class SpecificRecordBuilderBase extends RecordBuilderBase {
   protected SpecificRecordBuilderBase(Schema schema) {
      super((Schema)schema, SpecificData.getForSchema(schema));
   }

   protected SpecificRecordBuilderBase(Schema schema, SpecificData model) {
      super((Schema)schema, model);
   }

   protected SpecificRecordBuilderBase(SpecificRecordBuilderBase other) {
      super((RecordBuilderBase)other, other.data());
   }

   protected SpecificRecordBuilderBase(SpecificRecord other) {
      super((Schema)other.getSchema(), SpecificData.getForSchema(other.getSchema()));
   }
}
