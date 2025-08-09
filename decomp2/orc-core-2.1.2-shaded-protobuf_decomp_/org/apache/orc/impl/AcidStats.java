package org.apache.orc.impl;

public class AcidStats {
   public long inserts;
   public long updates;
   public long deletes;

   public AcidStats() {
      this.inserts = 0L;
      this.updates = 0L;
      this.deletes = 0L;
   }

   public AcidStats(String serialized) {
      String[] parts = serialized.split(",");
      this.inserts = Long.parseLong(parts[0]);
      this.updates = Long.parseLong(parts[1]);
      this.deletes = Long.parseLong(parts[2]);
   }

   public String serialize() {
      StringBuilder builder = new StringBuilder();
      builder.append(this.inserts);
      builder.append(",");
      builder.append(this.updates);
      builder.append(",");
      builder.append(this.deletes);
      return builder.toString();
   }

   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append(" inserts: ").append(this.inserts);
      builder.append(" updates: ").append(this.updates);
      builder.append(" deletes: ").append(this.deletes);
      return builder.toString();
   }
}
