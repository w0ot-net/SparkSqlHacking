package org.apache.hadoop.hive.serde2.lazybinary.objectinspector;

import java.util.List;
import org.apache.hadoop.hive.serde2.lazybinary.LazyBinaryArray;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StandardListObjectInspector;

public class LazyBinaryListObjectInspector extends StandardListObjectInspector {
   protected LazyBinaryListObjectInspector() {
   }

   protected LazyBinaryListObjectInspector(ObjectInspector listElementObjectInspector) {
      super(listElementObjectInspector);
   }

   public List getList(Object data) {
      if (data == null) {
         return null;
      } else {
         LazyBinaryArray array = (LazyBinaryArray)data;
         return array.getList();
      }
   }

   public Object getListElement(Object data, int index) {
      if (data == null) {
         return null;
      } else {
         LazyBinaryArray array = (LazyBinaryArray)data;
         return array.getListElementObject(index);
      }
   }

   public int getListLength(Object data) {
      if (data == null) {
         return -1;
      } else {
         LazyBinaryArray array = (LazyBinaryArray)data;
         return array.getListLength();
      }
   }
}
