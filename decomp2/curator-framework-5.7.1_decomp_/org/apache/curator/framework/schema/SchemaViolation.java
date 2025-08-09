package org.apache.curator.framework.schema;

import java.util.Arrays;
import java.util.List;
import org.apache.curator.shaded.com.google.common.collect.ImmutableList;
import org.apache.zookeeper.data.ACL;

public class SchemaViolation extends RuntimeException {
   private final Schema schema;
   private final String violation;
   private final ViolatorData violatorData;

   /** @deprecated */
   public SchemaViolation(String violation) {
      super(String.format("Schema violation: %s", violation));
      this.schema = null;
      this.violation = violation;
      this.violatorData = new ViolatorData((String)null, (byte[])null, (List)null);
   }

   /** @deprecated */
   public SchemaViolation(Schema schema, String violation) {
      super(String.format("Schema violation: %s for schema: %s", violation, schema));
      this.schema = schema;
      this.violation = violation;
      this.violatorData = new ViolatorData((String)null, (byte[])null, (List)null);
   }

   public SchemaViolation(Schema schema, ViolatorData violatorData, String violation) {
      super(toString(schema, violation, violatorData));
      this.schema = schema;
      this.violation = violation;
      this.violatorData = violatorData;
   }

   public Schema getSchema() {
      return this.schema;
   }

   public String getViolation() {
      return this.violation;
   }

   public ViolatorData getViolatorData() {
      return this.violatorData;
   }

   public String toString() {
      return toString(this.schema, this.violation, this.violatorData) + super.toString();
   }

   private static String toString(Schema schema, String violation, ViolatorData violatorData) {
      return (violation != null ? violation : "") + " " + schema + " " + violatorData;
   }

   public static class ViolatorData {
      private final String path;
      private final byte[] data;
      private final List acl;

      public ViolatorData(String path, byte[] data, List acl) {
         this.path = path;
         this.data = data != null ? Arrays.copyOf(data, data.length) : null;
         this.acl = acl != null ? ImmutableList.copyOf(acl) : null;
      }

      public String getPath() {
         return this.path;
      }

      public byte[] getData() {
         return this.data;
      }

      public List getAcl() {
         return this.acl;
      }

      public String toString() {
         String dataString = this.data != null ? new String(this.data) : "";
         return "ViolatorData{path='" + this.path + '\'' + ", data=" + dataString + ", acl=" + this.acl + '}';
      }
   }
}
