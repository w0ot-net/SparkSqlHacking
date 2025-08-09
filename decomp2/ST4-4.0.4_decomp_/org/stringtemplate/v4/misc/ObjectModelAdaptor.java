package org.stringtemplate.v4.misc;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import org.antlr.runtime.misc.DoubleKeyMap;
import org.stringtemplate.v4.Interpreter;
import org.stringtemplate.v4.ModelAdaptor;
import org.stringtemplate.v4.ST;

public class ObjectModelAdaptor implements ModelAdaptor {
   protected DoubleKeyMap classAndPropertyToMemberCache = new DoubleKeyMap();
   static STNoSuchPropertyException cachedException;

   public Object getProperty(Interpreter interp, ST self, Object o, Object property, String propertyName) throws STNoSuchPropertyException {
      Object value = null;
      Class c = o.getClass();
      if (property == null) {
         return this.throwNoSuchProperty(c.getName() + "." + propertyName);
      } else {
         Member member = (Member)this.classAndPropertyToMemberCache.get(c, propertyName);
         if (member != null) {
            try {
               Class memberClass = member.getClass();
               if (memberClass == Method.class) {
                  return ((Method)member).invoke(o);
               }

               if (memberClass == Field.class) {
                  return ((Field)member).get(o);
               }
            } catch (Exception var10) {
               this.throwNoSuchProperty(c.getName() + "." + propertyName);
            }
         }

         return this.lookupMethod(o, propertyName, value, c);
      }
   }

   public Object lookupMethod(Object o, String propertyName, Object value, Class c) {
      String methodSuffix = Character.toUpperCase(propertyName.charAt(0)) + propertyName.substring(1, propertyName.length());
      Method m = Misc.getMethod(c, "get" + methodSuffix);
      if (m == null) {
         m = Misc.getMethod(c, "is" + methodSuffix);
         if (m == null) {
            m = Misc.getMethod(c, "has" + methodSuffix);
         }
      }

      try {
         if (m != null) {
            this.classAndPropertyToMemberCache.put(c, propertyName, m);
            value = Misc.invokeMethod(m, o, value);
         } else {
            Field f = c.getField(propertyName);
            this.classAndPropertyToMemberCache.put(c, propertyName, f);

            try {
               value = Misc.accessField(f, o, value);
            } catch (IllegalAccessException var9) {
               this.throwNoSuchProperty(c.getName() + "." + propertyName);
            }
         }
      } catch (Exception var10) {
         this.throwNoSuchProperty(c.getName() + "." + propertyName);
      }

      return value;
   }

   protected Object throwNoSuchProperty(String propertyName) {
      if (cachedException == null) {
         cachedException = new STNoSuchPropertyException();
      }

      cachedException.propertyName = propertyName;
      throw cachedException;
   }
}
