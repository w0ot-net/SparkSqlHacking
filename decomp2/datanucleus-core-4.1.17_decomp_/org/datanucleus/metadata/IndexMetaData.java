package org.datanucleus.metadata;

public class IndexMetaData extends ConstraintMetaData {
   private static final long serialVersionUID = -2262544953953181136L;
   boolean unique = false;

   public IndexMetaData() {
   }

   public IndexMetaData(IndexMetaData imd) {
      super(imd);
      this.unique = imd.unique;
   }

   public final boolean isUnique() {
      return this.unique;
   }

   public IndexMetaData setUnique(boolean unique) {
      this.unique = unique;
      return this;
   }

   public String toString(String prefix, String indent) {
      StringBuilder sb = new StringBuilder();
      sb.append(prefix).append("<index unique=\"" + this.unique + "\"");
      if (this.table != null) {
         sb.append(" table=\"" + this.table + "\"");
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
      sb.append(prefix).append("</index>\n");
      return sb.toString();
   }
}
