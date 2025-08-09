package org.apache.avro;

public class JsonSchemaFormatterFactory implements SchemaFormatterFactory {
   public static final String VARIANT_NAME_PRETTY = "pretty";
   public static final String VARIANT_NAME_INLINE = "inline";

   public SchemaFormatter getDefaultFormatter() {
      return this.getFormatterForVariant("pretty");
   }

   public SchemaFormatter getFormatterForVariant(String variantName) {
      if ("pretty".equals(variantName)) {
         return new JsonSchemaFormatter(true);
      } else if ("inline".equals(variantName)) {
         return new JsonSchemaFormatter(false);
      } else {
         throw new AvroRuntimeException("Unknown JSON variant: " + variantName);
      }
   }
}
