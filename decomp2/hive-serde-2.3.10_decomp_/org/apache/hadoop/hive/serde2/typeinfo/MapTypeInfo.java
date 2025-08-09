package org.apache.hadoop.hive.serde2.typeinfo;

import java.io.Serializable;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;

public final class MapTypeInfo extends TypeInfo implements Serializable {
   private static final long serialVersionUID = 1L;
   private TypeInfo mapKeyTypeInfo;
   private TypeInfo mapValueTypeInfo;

   public MapTypeInfo() {
   }

   public String getTypeName() {
      return "map<" + this.mapKeyTypeInfo.getTypeName() + "," + this.mapValueTypeInfo.getTypeName() + ">";
   }

   public void setMapKeyTypeInfo(TypeInfo mapKeyTypeInfo) {
      this.mapKeyTypeInfo = mapKeyTypeInfo;
   }

   public void setMapValueTypeInfo(TypeInfo mapValueTypeInfo) {
      this.mapValueTypeInfo = mapValueTypeInfo;
   }

   MapTypeInfo(TypeInfo keyTypeInfo, TypeInfo valueTypeInfo) {
      this.mapKeyTypeInfo = keyTypeInfo;
      this.mapValueTypeInfo = valueTypeInfo;
   }

   public ObjectInspector.Category getCategory() {
      return ObjectInspector.Category.MAP;
   }

   public TypeInfo getMapKeyTypeInfo() {
      return this.mapKeyTypeInfo;
   }

   public TypeInfo getMapValueTypeInfo() {
      return this.mapValueTypeInfo;
   }

   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof MapTypeInfo)) {
         return false;
      } else {
         MapTypeInfo o = (MapTypeInfo)other;
         return o.getMapKeyTypeInfo().equals(this.getMapKeyTypeInfo()) && o.getMapValueTypeInfo().equals(this.getMapValueTypeInfo());
      }
   }

   public int hashCode() {
      return this.mapKeyTypeInfo.hashCode() ^ this.mapValueTypeInfo.hashCode();
   }
}
