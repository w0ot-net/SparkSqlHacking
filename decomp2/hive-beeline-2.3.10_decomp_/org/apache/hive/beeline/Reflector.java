package org.apache.hive.beeline;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Reflector {
   private final BeeLine beeLine;

   public Reflector(BeeLine beeLine) {
      this.beeLine = beeLine;
   }

   public Object invoke(Object on, String method, Object[] args) throws InvocationTargetException, IllegalAccessException, ClassNotFoundException {
      return this.invoke(on, method, Arrays.asList(args));
   }

   public Object invoke(Object on, String method, List args) throws InvocationTargetException, IllegalAccessException, ClassNotFoundException {
      return this.invoke(on, on == null ? null : on.getClass(), method, args);
   }

   public Object invoke(Object on, Class defClass, String method, List args) throws InvocationTargetException, IllegalAccessException, ClassNotFoundException {
      Class c = defClass != null ? defClass : on.getClass();
      List<Method> candidateMethods = new LinkedList();
      Method[] m = c.getMethods();

      for(int i = 0; i < m.length; ++i) {
         if (m[i].getName().equalsIgnoreCase(method)) {
            candidateMethods.add(m[i]);
         }
      }

      if (candidateMethods.size() == 0) {
         throw new IllegalArgumentException(this.beeLine.loc("no-method", new Object[]{method, c.getName()}));
      } else {
         for(Method meth : candidateMethods) {
            Class[] ptypes = meth.getParameterTypes();
            if (ptypes.length == args.size()) {
               Object[] converted = convert(args, ptypes);
               if (converted != null && Modifier.isPublic(meth.getModifiers())) {
                  return meth.invoke(on, converted);
               }
            }
         }

         return null;
      }
   }

   public static Object[] convert(List objects, Class[] toTypes) throws ClassNotFoundException {
      Object[] converted = new Object[objects.size()];

      for(int i = 0; i < converted.length; ++i) {
         converted[i] = convert(objects.get(i), toTypes[i]);
      }

      return converted;
   }

   public static Object convert(Object ob, Class toType) throws ClassNotFoundException {
      if (ob != null && !ob.toString().equals("null")) {
         if (toType == String.class) {
            return new String(ob.toString());
         } else if (toType != Byte.class && toType != Byte.TYPE) {
            if (toType != Character.class && toType != Character.TYPE) {
               if (toType != Short.class && toType != Short.TYPE) {
                  if (toType != Integer.class && toType != Integer.TYPE) {
                     if (toType != Long.class && toType != Long.TYPE) {
                        if (toType != Double.class && toType != Double.TYPE) {
                           if (toType != Float.class && toType != Float.TYPE) {
                              if (toType != Boolean.class && toType != Boolean.TYPE) {
                                 return toType == Class.class ? Class.forName(ob.toString()) : null;
                              } else {
                                 return new Boolean(ob.toString().equals("true") || ob.toString().equals("true") || ob.toString().equals("1") || ob.toString().equals("on") || ob.toString().equals("yes"));
                              }
                           } else {
                              return new Float(ob.toString());
                           }
                        } else {
                           return new Double(ob.toString());
                        }
                     } else {
                        return new Long(ob.toString());
                     }
                  } else {
                     return new Integer(ob.toString());
                  }
               } else {
                  return new Short(ob.toString());
               }
            } else {
               return new Character(ob.toString().charAt(0));
            }
         } else {
            return new Byte(ob.toString());
         }
      } else {
         return null;
      }
   }
}
