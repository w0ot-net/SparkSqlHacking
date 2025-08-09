package org.datanucleus.metadata.annotations;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import org.datanucleus.util.ClassUtils;

public class Member {
   String name;
   Class type;
   int modifiers;
   Type genericType;
   boolean property;

   public Member(Field field) {
      this.name = field.getName();
      this.type = field.getType();
      this.modifiers = field.getModifiers();
      this.genericType = field.getGenericType();
      this.property = false;
   }

   public Member(Method method) {
      this.name = ClassUtils.getFieldNameForJavaBeanGetter(method.getName());
      this.type = method.getReturnType();
      this.modifiers = method.getModifiers();
      this.genericType = method.getGenericReturnType();
      this.property = true;
   }

   public String getName() {
      return this.name;
   }

   public boolean isProperty() {
      return this.property;
   }

   public Class getType() {
      return this.type;
   }

   public int getModifiers() {
      return this.modifiers;
   }

   public Type getGenericType() {
      return this.genericType;
   }
}
