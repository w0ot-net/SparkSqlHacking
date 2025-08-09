package org.datanucleus.metadata;

public class UniqueMetaData extends ConstraintMetaData {
   private static final long serialVersionUID = -707369332288973459L;
   boolean deferred = false;

   public UniqueMetaData() {
   }

   public UniqueMetaData(UniqueMetaData umd) {
      super(umd);
      this.deferred = umd.deferred;
   }

   public final boolean isDeferred() {
      return this.deferred;
   }

   public UniqueMetaData setDeferred(boolean deferred) {
      this.deferred = deferred;
      return this;
   }

   public String toString(String prefix, String indent) {
      StringBuilder sb = new StringBuilder();
      sb.append(prefix).append("<unique");
      if (this.table != null) {
         sb.append(" table=\"" + this.table + "\"");
      }

      if (this.deferred) {
         sb.append(" deferred=\"true\"");
      }

      sb.append(this.name != null ? " name=\"" + this.name + "\">\n" : ">\n");
      if (this.memberNames != null) {
         for(String memberName : this.memberNames) {
            sb.append(prefix).append(indent).append("<field name=\"" + memberName + "\"/>");
         }
      }

      if (this.columnNames != null) {
         for(String columnName : this.columnNames) {
            sb.append(prefix).append(indent).append("<column name=\"" + columnName + "\"/>");
         }
      }

      sb.append(super.toString(prefix + indent, indent));
      sb.append(prefix).append("</unique>\n");
      return sb.toString();
   }
}
