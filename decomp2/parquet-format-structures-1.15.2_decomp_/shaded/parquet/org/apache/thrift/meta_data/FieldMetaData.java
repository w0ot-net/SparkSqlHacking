package shaded.parquet.org.apache.thrift.meta_data;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class FieldMetaData implements Serializable {
   public final String fieldName;
   public final byte requirementType;
   public final FieldValueMetaData valueMetaData;
   private final Map fieldAnnotations;
   private static final ConcurrentMap structMap = new ConcurrentHashMap();

   public FieldMetaData(String name, byte req, FieldValueMetaData vMetaData) {
      this(name, req, vMetaData, Collections.emptyMap());
   }

   public FieldMetaData(String fieldName, byte requirementType, FieldValueMetaData valueMetaData, Map fieldAnnotations) {
      this.fieldName = fieldName;
      this.requirementType = requirementType;
      this.valueMetaData = valueMetaData;
      this.fieldAnnotations = fieldAnnotations;
   }

   public Map getFieldAnnotations() {
      return Collections.unmodifiableMap(this.fieldAnnotations);
   }

   public static void addStructMetaDataMap(Class sClass, Map map) {
      structMap.put(sClass, map);
   }

   public static Map getStructMetaDataMap(Class sClass) {
      if (!structMap.containsKey(sClass)) {
         try {
            sClass.getDeclaredConstructor().newInstance();
         } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getClass().getSimpleName() + " for TBase class: " + sClass.getName(), e);
         }
      }

      return (Map)structMap.get(sClass);
   }
}
