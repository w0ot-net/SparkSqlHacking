package org.datanucleus.metadata;

import java.io.Serializable;

public class ForeignKeyAction implements Serializable {
   private static final long serialVersionUID = -7578177253405576968L;
   public static final ForeignKeyAction CASCADE = new ForeignKeyAction(1);
   public static final ForeignKeyAction RESTRICT = new ForeignKeyAction(2);
   public static final ForeignKeyAction NULL = new ForeignKeyAction(3);
   public static final ForeignKeyAction DEFAULT = new ForeignKeyAction(4);
   public static final ForeignKeyAction NONE = new ForeignKeyAction(5);
   private final int typeId;

   protected ForeignKeyAction(int i) {
      this.typeId = i;
   }

   public String toString() {
      switch (this.getType()) {
         case 1:
            return "cascade";
         case 2:
            return "restrict";
         case 3:
            return "null";
         case 4:
            return "default";
         case 5:
            return "none";
         default:
            return "";
      }
   }

   public int hashCode() {
      return this.typeId;
   }

   public boolean equals(Object o) {
      if (o instanceof ForeignKeyAction) {
         return ((ForeignKeyAction)o).typeId == this.typeId;
      } else {
         return false;
      }
   }

   protected int getType() {
      return this.typeId;
   }

   public static ForeignKeyAction getForeignKeyAction(String value) {
      if (value == null) {
         return null;
      } else if (CASCADE.toString().equalsIgnoreCase(value)) {
         return CASCADE;
      } else if (DEFAULT.toString().equalsIgnoreCase(value)) {
         return DEFAULT;
      } else if (NULL.toString().equalsIgnoreCase(value)) {
         return NULL;
      } else if (RESTRICT.toString().equalsIgnoreCase(value)) {
         return RESTRICT;
      } else {
         return NONE.toString().equalsIgnoreCase(value) ? NONE : null;
      }
   }
}
