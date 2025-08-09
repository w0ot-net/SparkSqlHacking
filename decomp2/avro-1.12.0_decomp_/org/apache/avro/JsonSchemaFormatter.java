package org.apache.avro;

public class JsonSchemaFormatter implements SchemaFormatter {
   private final boolean prettyPrinted;

   public JsonSchemaFormatter(boolean prettyPrinted) {
      this.prettyPrinted = prettyPrinted;
   }

   public String format(Schema schema) {
      return schema.toString(this.prettyPrinted);
   }
}
