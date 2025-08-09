package org.apache.avro.message;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.avro.Schema;
import org.apache.avro.SchemaNormalization;

public interface SchemaStore {
   Schema findByFingerprint(long fingerprint);

   public static class Cache implements SchemaStore {
      private final Map schemas = new ConcurrentHashMap();

      public void addSchema(Schema schema) {
         long fp = SchemaNormalization.parsingFingerprint64(schema);
         this.schemas.put(fp, schema);
      }

      public Schema findByFingerprint(long fingerprint) {
         return (Schema)this.schemas.get(fingerprint);
      }
   }
}
