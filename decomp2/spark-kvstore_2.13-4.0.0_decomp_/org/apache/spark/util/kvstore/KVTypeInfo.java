package org.apache.spark.util.kvstore;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.apache.spark.annotation.Private;
import org.sparkproject.guava.base.Preconditions;

@Private
public class KVTypeInfo {
   private final Class type;
   private final Map indices;
   private final Map accessors;

   public KVTypeInfo(Class type) {
      this.type = type;
      this.accessors = new HashMap();
      this.indices = new HashMap();

      for(Field f : type.getDeclaredFields()) {
         KVIndex idx = (KVIndex)f.getAnnotation(KVIndex.class);
         if (idx != null) {
            this.checkIndex(idx, this.indices);
            f.setAccessible(true);
            this.indices.put(idx.value(), idx);
            this.accessors.put(idx.value(), new FieldAccessor(f));
         }
      }

      for(Method m : type.getDeclaredMethods()) {
         KVIndex idx = (KVIndex)m.getAnnotation(KVIndex.class);
         if (idx != null) {
            this.checkIndex(idx, this.indices);
            Preconditions.checkArgument(m.getParameterCount() == 0, "Annotated method %s::%s should not have any parameters.", type.getName(), m.getName());
            m.setAccessible(true);
            this.indices.put(idx.value(), idx);
            this.accessors.put(idx.value(), new MethodAccessor(m));
         }
      }

      Preconditions.checkArgument(this.indices.containsKey("__main__"), "No natural index defined for type %s.", type.getName());

      for(KVIndex idx : this.indices.values()) {
         if (!idx.parent().isEmpty()) {
            KVIndex parent = (KVIndex)this.indices.get(idx.parent());
            Preconditions.checkArgument(parent != null, "Cannot find parent %s of index %s.", idx.parent(), idx.value());
            Preconditions.checkArgument(parent.parent().isEmpty(), "Parent index %s of index %s cannot be itself a child index.", idx.parent(), idx.value());
         }
      }

   }

   private void checkIndex(KVIndex idx, Map indices) {
      Preconditions.checkArgument(idx.value() != null && !idx.value().isEmpty(), "No name provided for index in type %s.", this.type.getName());
      Preconditions.checkArgument(!idx.value().startsWith("_") || idx.value().equals("__main__"), "Index name %s (in type %s) is not allowed.", idx.value(), this.type.getName());
      Preconditions.checkArgument(idx.parent().isEmpty() || !idx.parent().equals(idx.value()), "Index %s cannot be parent of itself.", idx.value());
      Preconditions.checkArgument(!indices.containsKey(idx.value()), "Duplicate index %s for type %s.", idx.value(), this.type.getName());
   }

   public Class type() {
      return this.type;
   }

   public Object getIndexValue(String indexName, Object instance) throws Exception {
      return this.getAccessor(indexName).get(instance);
   }

   public Stream indices() {
      return this.indices.values().stream();
   }

   Accessor getAccessor(String indexName) {
      Accessor a = (Accessor)this.accessors.get(indexName);
      Preconditions.checkArgument(a != null, "No index %s.", indexName);
      return a;
   }

   Accessor getParentAccessor(String indexName) {
      KVIndex index = (KVIndex)this.indices.get(indexName);
      return index.parent().isEmpty() ? null : this.getAccessor(index.parent());
   }

   String getParentIndexName(String indexName) {
      KVIndex index = (KVIndex)this.indices.get(indexName);
      return index.parent();
   }

   private static class FieldAccessor implements Accessor {
      private final Field field;

      FieldAccessor(Field field) {
         this.field = field;
      }

      public Object get(Object instance) throws ReflectiveOperationException {
         return this.field.get(instance);
      }

      public Class getType() {
         return this.field.getType();
      }
   }

   private static class MethodAccessor implements Accessor {
      private final Method method;

      MethodAccessor(Method method) {
         this.method = method;
      }

      public Object get(Object instance) throws ReflectiveOperationException {
         return this.method.invoke(instance);
      }

      public Class getType() {
         return this.method.getReturnType();
      }
   }

   interface Accessor {
      Object get(Object var1) throws ReflectiveOperationException;

      Class getType();
   }
}
