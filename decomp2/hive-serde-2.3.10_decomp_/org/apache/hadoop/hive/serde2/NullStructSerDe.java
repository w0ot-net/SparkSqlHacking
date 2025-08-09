package org.apache.hadoop.hive.serde2;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Writable;

public class NullStructSerDe extends AbstractSerDe {
   private static ObjectInspector nullStructOI = new NullStructSerDeObjectInspector();

   public Object deserialize(Writable blob) throws SerDeException {
      return null;
   }

   public ObjectInspector getObjectInspector() throws SerDeException {
      return nullStructOI;
   }

   public SerDeStats getSerDeStats() {
      return null;
   }

   public void initialize(Configuration conf, Properties tbl) throws SerDeException {
   }

   public Class getSerializedClass() {
      return NullWritable.class;
   }

   public Writable serialize(Object obj, ObjectInspector objInspector) throws SerDeException {
      return NullWritable.get();
   }

   class NullStructField implements StructField {
      public String getFieldName() {
         return null;
      }

      public ObjectInspector getFieldObjectInspector() {
         return null;
      }

      public int getFieldID() {
         return -1;
      }

      public String getFieldComment() {
         return "";
      }
   }

   public static class NullStructSerDeObjectInspector extends StructObjectInspector {
      public String getTypeName() {
         return "null";
      }

      public ObjectInspector.Category getCategory() {
         return ObjectInspector.Category.STRUCT;
      }

      public StructField getStructFieldRef(String fieldName) {
         return null;
      }

      public List getAllStructFieldRefs() {
         return new ArrayList();
      }

      public Object getStructFieldData(Object data, StructField fieldRef) {
         return null;
      }

      public List getStructFieldsDataAsList(Object data) {
         return new ArrayList();
      }
   }
}
