package org.apache.hadoop.hive.serde2.typeinfo;

import java.io.Serializable;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;

public final class ListTypeInfo extends TypeInfo implements Serializable {
   private static final long serialVersionUID = 1L;
   private TypeInfo listElementTypeInfo;

   public ListTypeInfo() {
   }

   public String getTypeName() {
      return "array<" + this.listElementTypeInfo.getTypeName() + ">";
   }

   public void setListElementTypeInfo(TypeInfo listElementTypeInfo) {
      this.listElementTypeInfo = listElementTypeInfo;
   }

   ListTypeInfo(TypeInfo elementTypeInfo) {
      this.listElementTypeInfo = elementTypeInfo;
   }

   public ObjectInspector.Category getCategory() {
      return ObjectInspector.Category.LIST;
   }

   public TypeInfo getListElementTypeInfo() {
      return this.listElementTypeInfo;
   }

   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else {
         return !(other instanceof ListTypeInfo) ? false : this.getListElementTypeInfo().equals(((ListTypeInfo)other).getListElementTypeInfo());
      }
   }

   public int hashCode() {
      return this.listElementTypeInfo.hashCode();
   }
}
