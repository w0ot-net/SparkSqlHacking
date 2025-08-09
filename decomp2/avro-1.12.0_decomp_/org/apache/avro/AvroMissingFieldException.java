package org.apache.avro;

import java.util.ArrayList;
import java.util.List;

public class AvroMissingFieldException extends AvroRuntimeException {
   private List chainOfFields = new ArrayList(8);

   public AvroMissingFieldException(String message, Schema.Field field) {
      super(message);
      this.chainOfFields.add(field);
   }

   public void addParentField(Schema.Field field) {
      this.chainOfFields.add(field);
   }

   public String toString() {
      StringBuilder result = new StringBuilder();

      for(Schema.Field field : this.chainOfFields) {
         result.insert(0, " --> " + field.name());
      }

      return "Path in schema:" + String.valueOf(result);
   }
}
