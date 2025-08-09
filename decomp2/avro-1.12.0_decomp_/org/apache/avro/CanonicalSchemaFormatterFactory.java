package org.apache.avro;

public class CanonicalSchemaFormatterFactory implements SchemaFormatterFactory, SchemaFormatter {
   public SchemaFormatter getDefaultFormatter() {
      return this;
   }

   public String format(Schema schema) {
      return SchemaNormalization.toParsingForm(schema);
   }
}
