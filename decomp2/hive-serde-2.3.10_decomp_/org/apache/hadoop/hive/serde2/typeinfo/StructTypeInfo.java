package org.apache.hadoop.hive.serde2.typeinfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;

public final class StructTypeInfo extends TypeInfo implements Serializable {
   private static final long serialVersionUID = 1L;
   private ArrayList allStructFieldNames;
   private ArrayList allStructFieldTypeInfos;

   public StructTypeInfo() {
   }

   public String getTypeName() {
      StringBuilder sb = new StringBuilder();
      sb.append("struct<");

      for(int i = 0; i < this.allStructFieldNames.size(); ++i) {
         if (i > 0) {
            sb.append(",");
         }

         sb.append((String)this.allStructFieldNames.get(i));
         sb.append(":");
         sb.append(((TypeInfo)this.allStructFieldTypeInfos.get(i)).getTypeName());
      }

      sb.append(">");
      return sb.toString();
   }

   public void setAllStructFieldNames(ArrayList allStructFieldNames) {
      this.allStructFieldNames = allStructFieldNames;
   }

   public void setAllStructFieldTypeInfos(ArrayList allStructFieldTypeInfos) {
      this.allStructFieldTypeInfos = allStructFieldTypeInfos;
   }

   StructTypeInfo(List names, List typeInfos) {
      this.allStructFieldNames = new ArrayList(names);
      this.allStructFieldTypeInfos = new ArrayList(typeInfos);
   }

   public ObjectInspector.Category getCategory() {
      return ObjectInspector.Category.STRUCT;
   }

   public ArrayList getAllStructFieldNames() {
      return this.allStructFieldNames;
   }

   public ArrayList getAllStructFieldTypeInfos() {
      return this.allStructFieldTypeInfos;
   }

   public TypeInfo getStructFieldTypeInfo(String field) {
      String fieldLowerCase = field.toLowerCase();

      for(int i = 0; i < this.allStructFieldNames.size(); ++i) {
         if (fieldLowerCase.equalsIgnoreCase((String)this.allStructFieldNames.get(i))) {
            return (TypeInfo)this.allStructFieldTypeInfos.get(i);
         }
      }

      throw new RuntimeException("cannot find field " + field + "(lowercase form: " + fieldLowerCase + ") in " + this.allStructFieldNames);
   }

   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof StructTypeInfo)) {
         return false;
      } else {
         StructTypeInfo o = (StructTypeInfo)other;
         Iterator<String> namesIterator = this.getAllStructFieldNames().iterator();
         Iterator<String> otherNamesIterator = o.getAllStructFieldNames().iterator();

         while(namesIterator.hasNext() && otherNamesIterator.hasNext()) {
            if (!((String)namesIterator.next()).equalsIgnoreCase((String)otherNamesIterator.next())) {
               return false;
            }
         }

         return !namesIterator.hasNext() && !otherNamesIterator.hasNext() ? o.getAllStructFieldTypeInfos().equals(this.getAllStructFieldTypeInfos()) : false;
      }
   }

   public int hashCode() {
      return this.allStructFieldNames.hashCode() ^ this.allStructFieldTypeInfos.hashCode();
   }
}
