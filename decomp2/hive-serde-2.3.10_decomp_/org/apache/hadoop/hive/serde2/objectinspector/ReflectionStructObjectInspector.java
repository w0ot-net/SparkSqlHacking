package org.apache.hadoop.hive.serde2.objectinspector;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ReflectionUtils;

public class ReflectionStructObjectInspector extends SettableStructObjectInspector {
   Class objectClass;
   List fields;
   volatile boolean inited = false;
   volatile Type type;

   public ObjectInspector.Category getCategory() {
      return ObjectInspector.Category.STRUCT;
   }

   public String getTypeName() {
      StringBuilder sb = new StringBuilder("struct<");
      boolean first = true;

      for(StructField structField : this.getAllStructFieldRefs()) {
         if (first) {
            first = false;
         } else {
            sb.append(",");
         }

         sb.append(structField.getFieldName()).append(":").append(structField.getFieldObjectInspector().getTypeName());
      }

      sb.append(">");
      return sb.toString();
   }

   ReflectionStructObjectInspector() {
   }

   protected boolean isFullyInited(Set checkedTypes) {
      if (this.type != null && ObjectInspectorFactory.objectInspectorCache.get(this.type) != this) {
         throw new RuntimeException("Cached object inspector is gone while waiting for it to initialize");
      } else if (!this.inited) {
         return false;
      } else {
         checkedTypes.add(this.type);

         for(StructField field : this.getAllStructFieldRefs()) {
            ObjectInspector oi = field.getFieldObjectInspector();
            if (oi instanceof ReflectionStructObjectInspector) {
               ReflectionStructObjectInspector soi = (ReflectionStructObjectInspector)oi;
               if (!checkedTypes.contains(soi.type) && !soi.isFullyInited(checkedTypes)) {
                  return false;
               }
            }
         }

         return true;
      }
   }

   protected void init(Type type, Class objectClass, ObjectInspectorFactory.ObjectInspectorOptions options) {
      this.type = type;
      this.verifyObjectClassType(objectClass);
      this.objectClass = objectClass;
      List<? extends ObjectInspector> structFieldObjectInspectors = this.extractFieldObjectInspectors(objectClass, options);
      Field[] reflectionFields = ObjectInspectorUtils.getDeclaredNonStaticFields(objectClass);
      synchronized(this) {
         this.fields = new ArrayList(structFieldObjectInspectors.size());
         int used = 0;

         for(int i = 0; i < reflectionFields.length; ++i) {
            if (!this.shouldIgnoreField(reflectionFields[i].getName())) {
               reflectionFields[i].setAccessible(true);
               this.fields.add(new MyField(i, reflectionFields[i], (ObjectInspector)structFieldObjectInspectors.get(used++)));
            }
         }

         assert this.fields.size() == structFieldObjectInspectors.size();

         this.inited = true;
         this.notifyAll();
      }
   }

   public boolean shouldIgnoreField(String name) {
      return false;
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
      } else if (!(fieldRef instanceof MyField)) {
         throw new RuntimeException("fieldRef has to be of MyField");
      } else {
         MyField f = (MyField)fieldRef;

         try {
            Object r = f.field.get(data);
            return r;
         } catch (Exception e) {
            throw new RuntimeException("cannot get field " + f.field + " from " + data.getClass() + " " + data, e);
         }
      }
   }

   public List getStructFieldsDataAsList(Object data) {
      if (data == null) {
         return null;
      } else {
         try {
            ArrayList<Object> result = new ArrayList(this.fields.size());

            for(int i = 0; i < this.fields.size(); ++i) {
               result.add(((MyField)this.fields.get(i)).field.get(data));
            }

            return result;
         } catch (Exception e) {
            throw new RuntimeException(e);
         }
      }
   }

   public Object create() {
      return ReflectionUtils.newInstance(this.objectClass, (Configuration)null);
   }

   public Object setStructFieldData(Object struct, StructField field, Object fieldValue) {
      MyField myField = (MyField)field;

      try {
         myField.field.set(struct, fieldValue);
         return struct;
      } catch (Exception e) {
         throw new RuntimeException("cannot set field " + myField.field + " of " + struct.getClass() + " " + struct, e);
      }
   }

   protected List extractFieldObjectInspectors(Class clazz, ObjectInspectorFactory.ObjectInspectorOptions options) {
      Field[] fields = ObjectInspectorUtils.getDeclaredNonStaticFields(clazz);
      ArrayList<ObjectInspector> structFieldObjectInspectors = new ArrayList(fields.length);

      for(int i = 0; i < fields.length; ++i) {
         if (!this.shouldIgnoreField(fields[i].getName())) {
            structFieldObjectInspectors.add(ObjectInspectorFactory.getReflectionObjectInspector(fields[i].getGenericType(), options, false));
         }
      }

      return structFieldObjectInspectors;
   }

   protected void verifyObjectClassType(Class objectClass) {
      assert !List.class.isAssignableFrom(objectClass);

      assert !Map.class.isAssignableFrom(objectClass);

   }

   public static class MyField implements StructField {
      protected int fieldID;
      protected Field field;
      protected ObjectInspector fieldObjectInspector;

      protected MyField() {
      }

      public MyField(int fieldID, Field field, ObjectInspector fieldObjectInspector) {
         this.fieldID = fieldID;
         this.field = field;
         this.fieldObjectInspector = fieldObjectInspector;
      }

      public String getFieldName() {
         return this.field.getName().toLowerCase();
      }

      public ObjectInspector getFieldObjectInspector() {
         return this.fieldObjectInspector;
      }

      public int getFieldID() {
         return this.fieldID;
      }

      public String getFieldComment() {
         return null;
      }

      public String toString() {
         return this.field.toString();
      }
   }
}
