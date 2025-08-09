package org.apache.avro;

import java.io.IOException;
import java.net.URI;

public class JsonSchemaParser implements FormattedSchemaParser {
   public static Schema parseInternal(String... fragments) {
      StringBuilder buffer = new StringBuilder();

      for(String fragment : fragments) {
         buffer.append(fragment);
      }

      boolean saved = Schema.getValidateDefaults();

      Schema var12;
      try {
         Schema.setValidateDefaults(false);
         ParseContext context = new ParseContext(NameValidator.NO_VALIDATION);
         Schema schema = (new JsonSchemaParser()).parse(context, buffer, true);
         context.commit();
         context.resolveAllSchemas();
         var12 = context.resolve(schema);
      } finally {
         Schema.setValidateDefaults(saved);
      }

      return var12;
   }

   public Schema parse(ParseContext parseContext, URI baseUri, CharSequence formattedSchema) throws IOException, SchemaParseException {
      return this.parse(parseContext, formattedSchema, false);
   }

   private Schema parse(ParseContext parseContext, CharSequence formattedSchema, boolean allowInvalidDefaults) throws SchemaParseException {
      Schema.Parser parser = new Schema.Parser(parseContext);
      if (allowInvalidDefaults) {
         parser.setValidateDefaults(false);
      }

      return parser.parseInternal(formattedSchema.toString());
   }
}
