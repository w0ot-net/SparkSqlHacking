package org.apache.avro;

import java.util.Locale;

public interface SchemaFormatter {
   static SchemaFormatter getInstance(String name) {
      int slashPos = name.indexOf("/");
      String formatName = slashPos < 0 ? name : name.substring(0, slashPos);
      String variantName = slashPos < 0 ? null : name.substring(slashPos + 1).toLowerCase(Locale.ROOT);

      for(SchemaFormatterFactory formatterFactory : SchemaFormatterCache.LOADER) {
         if (formatName.equalsIgnoreCase(formatterFactory.formatName())) {
            if (variantName == null) {
               return formatterFactory.getDefaultFormatter();
            }

            return formatterFactory.getFormatterForVariant(variantName);
         }
      }

      throw new AvroRuntimeException("Unsupported schema format: " + name + "; see the javadoc for valid examples");
   }

   static String format(String name, Schema schema) {
      return getInstance(name).format(schema);
   }

   String format(Schema schema);
}
