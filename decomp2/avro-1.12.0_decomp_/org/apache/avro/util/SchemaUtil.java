package org.apache.avro.util;

import java.util.StringJoiner;
import org.apache.avro.Schema;

public class SchemaUtil {
   private SchemaUtil() {
   }

   public static String describe(Schema schema) {
      if (schema == null) {
         return "unknown";
      } else {
         switch (schema.getType()) {
            case UNION:
               StringJoiner csv = new StringJoiner(", ");

               for(Schema branch : schema.getTypes()) {
                  csv.add(describe(branch));
               }

               return "[" + String.valueOf(csv) + "]";
            case MAP:
               return "Map<String, " + describe(schema.getValueType()) + ">";
            case ARRAY:
               return "List<" + describe(schema.getElementType()) + ">";
            default:
               return schema.getName();
         }
      }
   }

   public static String describe(Object datum) {
      if (datum == null) {
         return "null";
      } else {
         String var10000 = String.valueOf(datum);
         return var10000 + " (a " + datum.getClass().getName() + ")";
      }
   }
}
