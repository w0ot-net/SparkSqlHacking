package org.apache.hadoop.hive.serde2.objectinspector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StandardStructObjectInspector extends SettableStructObjectInspector {
   public static final Logger LOG = LoggerFactory.getLogger(StandardStructObjectInspector.class.getName());
   protected List fields;
   protected transient List originalColumnNames;
   boolean warned = false;

   protected StandardStructObjectInspector() {
   }

   protected StandardStructObjectInspector(List structFieldNames, List structFieldObjectInspectors) {
      this.init(structFieldNames, structFieldObjectInspectors, (List)null);
   }

   protected StandardStructObjectInspector(List structFieldNames, List structFieldObjectInspectors, List structFieldComments) {
      this.init(structFieldNames, structFieldObjectInspectors, structFieldComments);
   }

   protected void init(List structFieldNames, List structFieldObjectInspectors, List structFieldComments) {
      this.fields = new ArrayList(structFieldNames.size());
      this.originalColumnNames = new ArrayList(structFieldNames.size());

      for(int i = 0; i < structFieldNames.size(); ++i) {
         this.fields.add(new MyField(i, (String)structFieldNames.get(i), (ObjectInspector)structFieldObjectInspectors.get(i), structFieldComments == null ? null : (String)structFieldComments.get(i)));
         this.originalColumnNames.add(structFieldNames.get(i));
      }

   }

   protected StandardStructObjectInspector(List fields) {
      this.init(fields);
   }

   protected void init(List fields) {
      this.fields = new ArrayList(fields.size());
      this.originalColumnNames = new ArrayList(fields.size());

      for(int i = 0; i < fields.size(); ++i) {
         this.fields.add(new MyField(i, ((StructField)fields.get(i)).getFieldName(), ((StructField)fields.get(i)).getFieldObjectInspector()));
         this.originalColumnNames.add(((StructField)fields.get(i)).getFieldName());
      }

   }

   public String getTypeName() {
      return ObjectInspectorUtils.getStandardStructTypeName(this);
   }

   public final ObjectInspector.Category getCategory() {
      return ObjectInspector.Category.STRUCT;
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
         boolean isArray = data.getClass().isArray();
         if (!isArray && !(data instanceof List)) {
            if (!this.warned) {
               LOG.warn("Invalid type for struct " + data.getClass());
               LOG.warn("ignoring similar errors.");
               this.warned = true;
            }

            return data;
         } else {
            int listSize = isArray ? ((Object[])((Object[])data)).length : ((List)data).size();
            MyField f = (MyField)fieldRef;
            if (this.fields.size() != listSize && !this.warned) {
               this.warned = true;
               LOG.warn("Trying to access " + this.fields.size() + " fields inside a list of " + listSize + " elements: " + (isArray ? Arrays.asList(data) : (List)data));
               LOG.warn("ignoring similar errors.");
            }

            int fieldID = f.getFieldID();
            if (fieldID >= listSize) {
               return null;
            } else {
               return isArray ? ((Object[])((Object[])data))[fieldID] : ((List)data).get(fieldID);
            }
         }
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
         return list;
      }
   }

   public List getOriginalColumnNames() {
      return this.originalColumnNames;
   }

   public Object create() {
      ArrayList<Object> a = new ArrayList(this.fields.size());

      for(int i = 0; i < this.fields.size(); ++i) {
         a.add((Object)null);
      }

      return a;
   }

   public Object setStructFieldData(Object struct, StructField field, Object fieldValue) {
      ArrayList<Object> a = (ArrayList)struct;
      MyField myField = (MyField)field;
      a.set(myField.fieldID, fieldValue);
      return a;
   }

   protected static class MyField implements StructField {
      protected int fieldID;
      protected String fieldName;
      protected ObjectInspector fieldObjectInspector;
      protected String fieldComment;

      protected MyField() {
      }

      public MyField(int fieldID, String fieldName, ObjectInspector fieldObjectInspector) {
         this.fieldID = fieldID;
         this.fieldName = fieldName.toLowerCase().intern();
         this.fieldObjectInspector = fieldObjectInspector;
      }

      public MyField(int fieldID, String fieldName, ObjectInspector fieldObjectInspector, String fieldComment) {
         this(fieldID, fieldName, fieldObjectInspector);
         this.fieldComment = fieldComment;
      }

      public int getFieldID() {
         return this.fieldID;
      }

      public String getFieldName() {
         return this.fieldName;
      }

      public ObjectInspector getFieldObjectInspector() {
         return this.fieldObjectInspector;
      }

      public String getFieldComment() {
         return this.fieldComment;
      }

      public String toString() {
         return "" + this.fieldID + ":" + this.fieldName;
      }
   }
}
