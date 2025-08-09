package org.sparkproject.jpmml.model.visitors;

import java.lang.reflect.Field;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.jpmml.model.ReflectionUtil;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;

public abstract class Interner extends AbstractVisitor {
   private Class type = null;

   public Interner(Class type) {
      this.setType(type);
   }

   public abstract Object intern(Object var1);

   public Class getType() {
      return this.type;
   }

   private void setType(Class type) {
      this.type = (Class)Objects.requireNonNull(type);
   }

   protected void apply(Field field, PMMLObject object) {
      Class<? extends V> type = this.getType();
      Class<?> fieldType = field.getType();
      if (Objects.equals(List.class, fieldType)) {
         CollectionElementType collectionElementType = (CollectionElementType)field.getAnnotation(CollectionElementType.class);
         if (collectionElementType == null) {
            throw new IllegalArgumentException();
         } else {
            Class<?> elementClazz = collectionElementType.value();
            if (elementClazz.isAssignableFrom(type)) {
               List<V> values = (List)ReflectionUtil.getFieldValue(field, object);
               if (values != null && !values.isEmpty()) {
                  ListIterator<V> it = values.listIterator();

                  while(it.hasNext()) {
                     V value = (V)it.next();
                     if (type.isInstance(value)) {
                        V internedValue = (V)this.intern(value);
                        it.set(internedValue);
                     }
                  }
               }
            }

         }
      } else {
         if (fieldType.isAssignableFrom(type)) {
            Object value = ReflectionUtil.getFieldValue(field, object);
            if (value != null && type.isInstance(value)) {
               V internedValue = (V)this.intern(type.cast(value));
               ReflectionUtil.setFieldValue(field, object, internedValue);
            }
         }

      }
   }
}
