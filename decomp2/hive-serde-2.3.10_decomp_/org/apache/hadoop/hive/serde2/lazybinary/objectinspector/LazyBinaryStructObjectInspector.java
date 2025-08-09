package org.apache.hadoop.hive.serde2.lazybinary.objectinspector;

import java.util.List;
import org.apache.hadoop.hive.serde2.lazybinary.LazyBinaryStruct;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StandardStructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;

public class LazyBinaryStructObjectInspector extends StandardStructObjectInspector {
   protected LazyBinaryStructObjectInspector() {
   }

   protected LazyBinaryStructObjectInspector(List structFieldNames, List structFieldObjectInspectors) {
      super(structFieldNames, structFieldObjectInspectors);
   }

   protected LazyBinaryStructObjectInspector(List structFieldNames, List structFieldObjectInspectors, List structFieldComments) {
      super(structFieldNames, structFieldObjectInspectors, structFieldComments);
   }

   protected LazyBinaryStructObjectInspector(List fields) {
      super(fields);
   }

   public Object getStructFieldData(Object data, StructField fieldRef) {
      if (data == null) {
         return null;
      } else {
         LazyBinaryStruct struct = (LazyBinaryStruct)data;
         StandardStructObjectInspector.MyField f = (StandardStructObjectInspector.MyField)fieldRef;
         int fieldID = f.getFieldID();

         assert fieldID >= 0 && fieldID < this.fields.size();

         return struct.getField(fieldID);
      }
   }

   public List getStructFieldsDataAsList(Object data) {
      if (data == null) {
         return null;
      } else {
         LazyBinaryStruct struct = (LazyBinaryStruct)data;
         return struct.getFieldsAsList();
      }
   }

   public StructField getStructFieldRef(int index) {
      return (StructField)this.fields.get(index);
   }
}
