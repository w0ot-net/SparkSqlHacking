package org.apache.hadoop.hive.serde2.typeinfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;

public class UnionTypeInfo extends TypeInfo implements Serializable {
   private static final long serialVersionUID = 1L;
   private List allUnionObjectTypeInfos;

   public UnionTypeInfo() {
   }

   public String getTypeName() {
      StringBuilder sb = new StringBuilder();
      sb.append("uniontype<");

      for(int i = 0; i < this.allUnionObjectTypeInfos.size(); ++i) {
         if (i > 0) {
            sb.append(",");
         }

         sb.append(((TypeInfo)this.allUnionObjectTypeInfos.get(i)).getTypeName());
      }

      sb.append(">");
      return sb.toString();
   }

   public void setAllUnionObjectTypeInfos(List allUnionObjectTypeInfos) {
      this.allUnionObjectTypeInfos = allUnionObjectTypeInfos;
   }

   UnionTypeInfo(List typeInfos) {
      this.allUnionObjectTypeInfos = new ArrayList();
      this.allUnionObjectTypeInfos.addAll(typeInfos);
   }

   public ObjectInspector.Category getCategory() {
      return ObjectInspector.Category.UNION;
   }

   public List getAllUnionObjectTypeInfos() {
      return this.allUnionObjectTypeInfos;
   }

   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof UnionTypeInfo)) {
         return false;
      } else {
         UnionTypeInfo o = (UnionTypeInfo)other;
         return o.getAllUnionObjectTypeInfos().equals(this.getAllUnionObjectTypeInfos());
      }
   }

   public int hashCode() {
      return this.allUnionObjectTypeInfos.hashCode();
   }
}
