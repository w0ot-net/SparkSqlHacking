package org.apache.hadoop.hive.serde2.objectinspector;

import com.google.common.primitives.UnsignedBytes;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.TUnion;
import org.apache.thrift.meta_data.FieldMetaData;

public class ThriftUnionObjectInspector extends ReflectionStructObjectInspector implements UnionObjectInspector {
   private static final String FIELD_METADATA_MAP = "metaDataMap";
   private List ois;
   private List fields;

   public boolean shouldIgnoreField(String name) {
      return name.startsWith("__isset");
   }

   public List getObjectInspectors() {
      return this.ois;
   }

   public byte getTag(Object o) {
      if (o == null) {
         return -1;
      } else {
         TFieldIdEnum setField = ((TUnion)o).getSetField();
         return UnsignedBytes.checkedCast((long)(setField.getThriftFieldId() - 1));
      }
   }

   public Object getField(Object o) {
      return o == null ? null : ((TUnion)o).getFieldValue();
   }

   protected void init(Type type, Class objectClass, ObjectInspectorFactory.ObjectInspectorOptions options) {
      this.type = type;
      this.verifyObjectClassType(objectClass);
      this.objectClass = objectClass;

      Field fieldMetaData;
      try {
         fieldMetaData = objectClass.getDeclaredField("metaDataMap");

         assert Map.class.isAssignableFrom(fieldMetaData.getType());

         fieldMetaData.setAccessible(true);
      } catch (NoSuchFieldException e) {
         throw new RuntimeException("Unable to find field metadata for thrift union field ", e);
      }

      try {
         Map<? extends TFieldIdEnum, FieldMetaData> fieldMap = (Map)fieldMetaData.get((Object)null);
         synchronized(this) {
            this.fields = new ArrayList(fieldMap.size());
            this.ois = new ArrayList();

            for(Map.Entry metadata : fieldMap.entrySet()) {
               int fieldId = ((TFieldIdEnum)metadata.getKey()).getThriftFieldId();
               String fieldName = ((FieldMetaData)metadata.getValue()).fieldName;
               Type fieldType = ThriftObjectInspectorUtils.getFieldType(objectClass, fieldName);
               ObjectInspector reflectionObjectInspector = ObjectInspectorFactory.getReflectionObjectInspector(fieldType, options, false);
               this.fields.add(new StandardStructObjectInspector.MyField(fieldId, fieldName, reflectionObjectInspector));
               this.ois.add(reflectionObjectInspector);
            }

            this.inited = true;
         }
      } catch (IllegalAccessException e) {
         throw new RuntimeException("Unable to find field metadata for thrift union field ", e);
      }
   }

   public ObjectInspector.Category getCategory() {
      return ObjectInspector.Category.UNION;
   }

   public synchronized List getAllStructFieldRefs() {
      return this.fields;
   }

   public String getTypeName() {
      return ObjectInspectorUtils.getStandardUnionTypeName(this);
   }
}
