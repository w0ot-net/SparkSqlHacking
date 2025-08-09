package org.apache.hadoop.hive.serde2.objectinspector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.hadoop.hive.serde2.ColumnSet;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

public class MetadataListStructObjectInspector extends StandardStructObjectInspector {
   static ConcurrentHashMap cached = new ConcurrentHashMap();

   public static MetadataListStructObjectInspector getInstance(List columnNames) {
      ArrayList<List<String>> key = new ArrayList(1);
      key.add(columnNames);
      MetadataListStructObjectInspector result = (MetadataListStructObjectInspector)cached.get(key);
      if (result == null) {
         result = new MetadataListStructObjectInspector(columnNames);
         MetadataListStructObjectInspector prev = (MetadataListStructObjectInspector)cached.putIfAbsent(key, result);
         if (prev != null) {
            result = prev;
         }
      }

      return result;
   }

   public static MetadataListStructObjectInspector getInstance(List columnNames, List columnComments) {
      ArrayList<List<String>> key = new ArrayList(2);
      Collections.addAll(key, new List[]{columnNames, columnComments});
      MetadataListStructObjectInspector result = (MetadataListStructObjectInspector)cached.get(key);
      if (result == null) {
         result = new MetadataListStructObjectInspector(columnNames, columnComments);
         MetadataListStructObjectInspector prev = (MetadataListStructObjectInspector)cached.putIfAbsent(key, result);
         if (prev != null) {
            result = prev;
         }
      }

      return result;
   }

   static ArrayList getFieldObjectInspectors(int fields) {
      ArrayList<ObjectInspector> r = new ArrayList(fields);

      for(int i = 0; i < fields; ++i) {
         r.add(PrimitiveObjectInspectorFactory.getPrimitiveJavaObjectInspector(PrimitiveObjectInspector.PrimitiveCategory.STRING));
      }

      return r;
   }

   protected MetadataListStructObjectInspector() {
   }

   MetadataListStructObjectInspector(List columnNames) {
      super(columnNames, getFieldObjectInspectors(columnNames.size()));
   }

   public MetadataListStructObjectInspector(List columnNames, List columnComments) {
      super(columnNames, getFieldObjectInspectors(columnNames.size()), columnComments);
   }

   public Object getStructFieldData(Object data, StructField fieldRef) {
      if (data instanceof ColumnSet) {
         data = ((ColumnSet)data).col;
      }

      return super.getStructFieldData(data, fieldRef);
   }

   public List getStructFieldsDataAsList(Object data) {
      if (data instanceof ColumnSet) {
         data = ((ColumnSet)data).col;
      }

      return super.getStructFieldsDataAsList(data);
   }
}
