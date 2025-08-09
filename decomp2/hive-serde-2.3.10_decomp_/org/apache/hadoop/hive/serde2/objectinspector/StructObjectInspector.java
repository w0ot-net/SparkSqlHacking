package org.apache.hadoop.hive.serde2.objectinspector;

import java.util.List;

public abstract class StructObjectInspector implements ObjectInspector {
   public abstract List getAllStructFieldRefs();

   public abstract StructField getStructFieldRef(String var1);

   public abstract Object getStructFieldData(Object var1, StructField var2);

   public abstract List getStructFieldsDataAsList(Object var1);

   public boolean isSettable() {
      return false;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      List<? extends StructField> fields = this.getAllStructFieldRefs();
      sb.append(this.getClass().getName());
      sb.append("<");

      for(int i = 0; i < fields.size(); ++i) {
         if (i > 0) {
            sb.append(",");
         }

         sb.append(((StructField)fields.get(i)).getFieldObjectInspector().toString());
      }

      sb.append(">");
      return sb.toString();
   }
}
