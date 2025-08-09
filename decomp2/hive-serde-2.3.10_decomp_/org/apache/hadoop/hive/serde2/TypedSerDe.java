package org.apache.hadoop.hive.serde2;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Properties;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.util.ReflectionUtils;

public abstract class TypedSerDe extends AbstractSerDe {
   protected Type objectType;
   protected Class objectClass;
   protected Object deserializeCache;

   public TypedSerDe(Type objectType) throws SerDeException {
      this.objectType = objectType;
      if (objectType instanceof Class) {
         this.objectClass = (Class)objectType;
      } else {
         if (!(objectType instanceof ParameterizedType)) {
            throw new SerDeException("Cannot create TypedSerDe with type " + objectType);
         }

         this.objectClass = (Class)((ParameterizedType)objectType).getRawType();
      }

   }

   public Object deserialize(Writable blob) throws SerDeException {
      if (this.deserializeCache == null) {
         return ReflectionUtils.newInstance(this.objectClass, (Configuration)null);
      } else {
         assert this.deserializeCache.getClass().equals(this.objectClass);

         return this.deserializeCache;
      }
   }

   public ObjectInspector getObjectInspector() throws SerDeException {
      return ObjectInspectorFactory.getReflectionObjectInspector(this.objectType, this.getObjectInspectorOptions());
   }

   protected ObjectInspectorFactory.ObjectInspectorOptions getObjectInspectorOptions() {
      return ObjectInspectorFactory.ObjectInspectorOptions.JAVA;
   }

   public void initialize(Configuration job, Properties tbl) throws SerDeException {
   }

   public Class getSerializedClass() {
      return BytesWritable.class;
   }

   public Writable serialize(Object obj, ObjectInspector objInspector) throws SerDeException {
      throw new RuntimeException("not supported");
   }

   public SerDeStats getSerDeStats() {
      return null;
   }
}
