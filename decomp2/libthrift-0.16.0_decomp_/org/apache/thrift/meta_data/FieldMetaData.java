package org.apache.thrift.meta_data;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.thrift.TBase;
import org.apache.thrift.TFieldIdEnum;

public class FieldMetaData implements Serializable {
   public final String fieldName;
   public final byte requirementType;
   public final FieldValueMetaData valueMetaData;
   private static final Map structMap = new ConcurrentHashMap();

   public FieldMetaData(String name, byte req, FieldValueMetaData vMetaData) {
      this.fieldName = name;
      this.requirementType = req;
      this.valueMetaData = vMetaData;
   }

   public static void addStructMetaDataMap(Class sClass, Map map) {
      structMap.put(sClass, map);
   }

   public static Map getStructMetaDataMap(Class sClass) {
      if (!structMap.containsKey(sClass)) {
         try {
            sClass.newInstance();
         } catch (InstantiationException e) {
            throw new RuntimeException("InstantiationException for TBase class: " + sClass.getName() + ", message: " + e.getMessage());
         } catch (IllegalAccessException e) {
            throw new RuntimeException("IllegalAccessException for TBase class: " + sClass.getName() + ", message: " + e.getMessage());
         }
      }

      return (Map)structMap.get(sClass);
   }
}
