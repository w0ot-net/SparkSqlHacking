package org.apache.zookeeper.audit;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public final class AuditEvent {
   private static final char PAIR_SEPARATOR = '\t';
   private static final String KEY_VAL_SEPARATOR = "=";
   private Map logEntries = new LinkedHashMap();
   private Result result;

   AuditEvent(Result result) {
      this.result = result;
   }

   public Set getLogEntries() {
      return this.logEntries.entrySet();
   }

   void addEntry(FieldName fieldName, String value) {
      if (value != null) {
         this.logEntries.put(fieldName.name().toLowerCase(), value);
      }

   }

   public String getValue(FieldName fieldName) {
      return (String)this.logEntries.get(fieldName.name().toLowerCase());
   }

   public Result getResult() {
      return this.result;
   }

   public String toString() {
      StringBuilder buffer = new StringBuilder();
      boolean first = true;

      for(Map.Entry entry : this.logEntries.entrySet()) {
         String key = (String)entry.getKey();
         String value = (String)entry.getValue();
         if (null != value) {
            if (first) {
               first = false;
            } else {
               buffer.append('\t');
            }

            buffer.append(key).append("=").append(value);
         }
      }

      if (buffer.length() > 0) {
         buffer.append('\t');
      }

      buffer.append("result").append("=").append(this.result.name().toLowerCase());
      return buffer.toString();
   }

   public static enum FieldName {
      USER,
      OPERATION,
      IP,
      ACL,
      ZNODE,
      SESSION,
      ZNODE_TYPE;
   }

   public static enum Result {
      SUCCESS,
      FAILURE,
      INVOKED;
   }
}
