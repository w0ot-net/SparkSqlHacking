package org.apache.hadoop.hive.serde2.objectinspector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UnionStructObjectInspector extends StructObjectInspector {
   private List unionObjectInspectors;
   private List fields;

   protected UnionStructObjectInspector() {
   }

   protected UnionStructObjectInspector(List unionObjectInspectors) {
      this.init(unionObjectInspectors);
   }

   void init(List unionObjectInspectors) {
      this.unionObjectInspectors = unionObjectInspectors;
      int totalSize = 0;

      for(int i = 0; i < unionObjectInspectors.size(); ++i) {
         totalSize += ((StructObjectInspector)unionObjectInspectors.get(i)).getAllStructFieldRefs().size();
      }

      this.fields = new ArrayList(totalSize);

      for(int i = 0; i < unionObjectInspectors.size(); ++i) {
         StructObjectInspector oi = (StructObjectInspector)unionObjectInspectors.get(i);

         for(StructField sf : oi.getAllStructFieldRefs()) {
            this.fields.add(new MyField(i, sf));
         }
      }

   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else {
         return !(o instanceof UnionStructObjectInspector) ? false : this.unionObjectInspectors.equals(((UnionStructObjectInspector)o).unionObjectInspectors);
      }
   }

   public int hashCode() {
      return this.unionObjectInspectors.hashCode();
   }

   public final ObjectInspector.Category getCategory() {
      return ObjectInspector.Category.STRUCT;
   }

   public String getTypeName() {
      return ObjectInspectorUtils.getStandardStructTypeName(this);
   }

   public StructField getStructFieldRef(String fieldName) {
      return ObjectInspectorUtils.getStandardStructFieldRef(fieldName, this.fields);
   }

   public List getAllStructFieldRefs() {
      return this.fields;
   }

   public Object getStructFieldData(Object data, StructField fieldRef) {
      if (data == null) {
         return null;
      } else {
         MyField f = (MyField)fieldRef;
         Object fieldData;
         if (!(data instanceof List)) {
            Object[] list = data;

            assert list.length == this.unionObjectInspectors.size();

            fieldData = list[f.structID];
         } else {
            List<Object> list = (List)data;

            assert list.size() == this.unionObjectInspectors.size();

            fieldData = list.get(f.structID);
         }

         return ((StructObjectInspector)this.unionObjectInspectors.get(f.structID)).getStructFieldData(fieldData, f.structField);
      }
   }

   public List getStructFieldsDataAsList(Object data) {
      if (data == null) {
         return null;
      } else {
         if (!(data instanceof List)) {
            data = Arrays.asList(data);
         }

         List<Object> list = (List)data;

         assert list.size() == this.unionObjectInspectors.size();

         ArrayList<Object> result = new ArrayList(this.fields.size());

         for(int i = 0; i < this.unionObjectInspectors.size(); ++i) {
            result.addAll(((StructObjectInspector)this.unionObjectInspectors.get(i)).getStructFieldsDataAsList(list.get(i)));
         }

         return result;
      }
   }

   public static class MyField implements StructField {
      protected int structID;
      protected StructField structField;

      protected MyField() {
      }

      public MyField(int structID, StructField structField) {
         this.structID = structID;
         this.structField = structField;
      }

      public String getFieldName() {
         return this.structField.getFieldName();
      }

      public ObjectInspector getFieldObjectInspector() {
         return this.structField.getFieldObjectInspector();
      }

      public int getFieldID() {
         return this.structID;
      }

      public String getFieldComment() {
         return this.structField.getFieldComment();
      }
   }
}
