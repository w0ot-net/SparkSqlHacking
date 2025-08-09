package org.apache.hadoop.hive.serde2.typeinfo;

import java.io.Serializable;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;

public abstract class TypeInfo implements Serializable {
   private static final long serialVersionUID = 1L;

   protected TypeInfo() {
   }

   public abstract ObjectInspector.Category getCategory();

   public abstract String getTypeName();

   public String getQualifiedName() {
      return this.getTypeName();
   }

   public String toString() {
      return this.getTypeName();
   }

   public abstract boolean equals(Object var1);

   public abstract int hashCode();

   public boolean accept(TypeInfo other) {
      return this.equals(other);
   }
}
