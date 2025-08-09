package org.apache.hadoop.hive.serde2;

import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorUtils;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;

public abstract class BaseStructObjectInspector extends StructObjectInspector {
   protected final List fields = new ArrayList();

   protected BaseStructObjectInspector() {
   }

   public BaseStructObjectInspector(List structFieldNames, List structFieldObjectInspectors) {
      this.init(structFieldNames, structFieldObjectInspectors, (List)null);
   }

   public BaseStructObjectInspector(List structFieldNames, List structFieldObjectInspectors, List structFieldComments) {
      this.init(structFieldNames, structFieldObjectInspectors, structFieldComments);
   }

   protected void init(List structFieldNames, List structFieldObjectInspectors, List structFieldComments) {
      assert structFieldNames.size() == structFieldObjectInspectors.size();

      assert structFieldComments == null || structFieldNames.size() == structFieldComments.size();

      for(int i = 0; i < structFieldNames.size(); ++i) {
         this.fields.add(this.createField(i, (String)structFieldNames.get(i), (ObjectInspector)structFieldObjectInspectors.get(i), structFieldComments == null ? null : (String)structFieldComments.get(i)));
      }

   }

   protected void init(List structFields) {
      for(int i = 0; i < structFields.size(); ++i) {
         this.fields.add(new MyField(i, (StructField)structFields.get(i)));
      }

   }

   protected MyField createField(int index, String fieldName, ObjectInspector fieldOI, String comment) {
      return new MyField(index, fieldName, fieldOI, comment);
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

   protected static class MyField implements StructField {
      protected final int fieldID;
      protected final String fieldName;
      protected final String fieldComment;
      protected final ObjectInspector fieldObjectInspector;

      public MyField(int fieldID, String fieldName, ObjectInspector fieldObjectInspector, String fieldComment) {
         this.fieldID = fieldID;
         this.fieldName = fieldName.toLowerCase();
         this.fieldObjectInspector = fieldObjectInspector;
         this.fieldComment = fieldComment;
      }

      public MyField(int fieldID, StructField field) {
         this.fieldID = fieldID;
         this.fieldName = field.getFieldName().toLowerCase();
         this.fieldObjectInspector = field.getFieldObjectInspector();
         this.fieldComment = field.getFieldComment();
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
         return this.fieldID + ":" + this.fieldName;
      }
   }
}
