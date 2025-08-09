package org.apache.hadoop.hive.serde2.objectinspector;

import java.util.List;
import org.apache.hadoop.hive.serde2.BaseStructObjectInspector;
import org.apache.hadoop.hive.serde2.columnar.ColumnarStructBase;

class ColumnarStructObjectInspector extends BaseStructObjectInspector {
   protected ColumnarStructObjectInspector() {
   }

   public ColumnarStructObjectInspector(List structFieldNames, List structFieldObjectInspectors) {
      super(structFieldNames, structFieldObjectInspectors);
   }

   public ColumnarStructObjectInspector(List structFieldNames, List structFieldObjectInspectors, List structFieldComments) {
      super(structFieldNames, structFieldObjectInspectors, structFieldComments);
   }

   public Object getStructFieldData(Object data, StructField fieldRef) {
      if (data == null) {
         return null;
      } else {
         ColumnarStructBase struct = (ColumnarStructBase)data;
         BaseStructObjectInspector.MyField f = (BaseStructObjectInspector.MyField)fieldRef;
         int fieldID = f.getFieldID();

         assert fieldID >= 0 && fieldID < this.fields.size();

         return struct.getField(fieldID);
      }
   }

   public List getStructFieldsDataAsList(Object data) {
      if (data == null) {
         return null;
      } else {
         ColumnarStructBase struct = (ColumnarStructBase)data;
         return struct.getFieldsAsList();
      }
   }
}
