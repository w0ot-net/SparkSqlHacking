package org.apache.arrow.vector.validate;

import java.io.IOException;
import java.util.Iterator;
import org.apache.arrow.vector.types.MetadataVersion;
import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.Schema;

public final class MetadataV4UnionChecker {
   static boolean isUnion(Field field) {
      return field.getType().getTypeID() == ArrowType.ArrowTypeID.Union;
   }

   static Field check(Field field) {
      if (isUnion(field)) {
         return field;
      } else {
         for(Field child : field.getChildren()) {
            Field result = check(child);
            if (result != null) {
               return result;
            }
         }

         return null;
      }
   }

   public static void checkForUnion(Iterator fields, MetadataVersion metadataVersion) {
      if (metadataVersion.toFlatbufID() < MetadataVersion.V5.toFlatbufID()) {
         while(fields.hasNext()) {
            Field union = check((Field)fields.next());
            if (union != null) {
               throw new IllegalArgumentException("Cannot write union with V4 metadata version, use V5 instead. Found field: " + String.valueOf(union));
            }
         }

      }
   }

   public static void checkRead(Schema schema, MetadataVersion metadataVersion) throws IOException {
      if (metadataVersion.toFlatbufID() < MetadataVersion.V5.toFlatbufID()) {
         for(Field field : schema.getFields()) {
            Field union = check(field);
            if (union != null) {
               throw new IOException("Cannot read union with V4 metadata version. Found field: " + String.valueOf(union));
            }
         }

      }
   }
}
