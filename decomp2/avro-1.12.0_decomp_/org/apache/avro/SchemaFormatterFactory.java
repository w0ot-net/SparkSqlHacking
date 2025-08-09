package org.apache.avro;

import java.util.Locale;
import java.util.regex.Matcher;

public interface SchemaFormatterFactory {
   default String formatName() {
      String simpleName = this.getClass().getSimpleName();
      Matcher matcher = SchemaFormatterFactoryConstants.SIMPLE_NAME_PATTERN.matcher(simpleName);
      if (matcher.matches()) {
         return matcher.group(1).toLowerCase(Locale.ROOT);
      } else {
         throw new AvroRuntimeException("Formatter is not named \"<format>SchemaFormatterFactory\"; cannot determine format name.");
      }
   }

   SchemaFormatter getDefaultFormatter();

   default SchemaFormatter getFormatterForVariant(String variantName) {
      throw new AvroRuntimeException("The schema format \"" + this.formatName() + "\" has no variants.");
   }
}
