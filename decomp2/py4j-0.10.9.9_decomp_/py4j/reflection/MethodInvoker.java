package py4j.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import py4j.Py4JException;
import py4j.Py4JJavaException;

public class MethodInvoker {
   public static final int INVALID_INVOKER_COST = -1;
   public static final int MAX_DISTANCE = 100000000;
   private int cost;
   private List converters;
   private Method method;
   private Constructor constructor;
   private final Logger logger = Logger.getLogger(MethodInvoker.class.getName());
   public static final MethodInvoker INVALID_INVOKER = new MethodInvoker((Method)null, (TypeConverter[])null, -1);

   private static boolean allNoConverter(List converters) {
      boolean allNo = true;

      for(TypeConverter converter : converters) {
         if (converter != TypeConverter.NO_CONVERTER) {
            allNo = false;
            break;
         }
      }

      return allNo;
   }

   public static int buildConverters(List converters, Class[] parameters, Class[] arguments) {
      int cost = 0;
      int tempCost = -1;
      int size = arguments.length;

      for(int i = 0; i < size; ++i) {
         if (arguments[i] == null) {
            if (parameters[i].isPrimitive()) {
               tempCost = -1;
            } else {
               int distance = TypeUtil.computeDistance(Object.class, parameters[i]);
               tempCost = Math.abs(100000000 - distance);
               converters.add(TypeConverter.NO_CONVERTER);
            }
         } else if (parameters[i].isAssignableFrom(arguments[i])) {
            tempCost = TypeUtil.computeDistance(parameters[i], arguments[i]);
            converters.add(TypeConverter.NO_CONVERTER);
         } else if (TypeUtil.isNumeric(parameters[i]) && TypeUtil.isNumeric(arguments[i])) {
            tempCost = TypeUtil.computeNumericConversion(parameters[i], arguments[i], converters);
         } else if (TypeUtil.isCharacter(parameters[i])) {
            tempCost = TypeUtil.computeCharacterConversion(parameters[i], arguments[i], converters);
         } else if (TypeUtil.isBoolean(parameters[i]) && TypeUtil.isBoolean(arguments[i])) {
            tempCost = 0;
            converters.add(TypeConverter.NO_CONVERTER);
         }

         if (tempCost == -1) {
            cost = -1;
            break;
         }

         cost += tempCost;
         tempCost = -1;
      }

      return cost;
   }

   public static MethodInvoker buildInvoker(Constructor constructor, Class[] arguments) {
      MethodInvoker invoker = null;
      int size = 0;
      int cost = 0;
      if (arguments != null) {
         size = arguments.length;
      }

      List<TypeConverter> converters = new ArrayList();
      if (arguments != null && size > 0) {
         cost = buildConverters(converters, constructor.getParameterTypes(), arguments);
      }

      if (cost == -1) {
         invoker = INVALID_INVOKER;
      } else {
         TypeConverter[] convertersArray = null;
         if (!allNoConverter(converters)) {
            convertersArray = (TypeConverter[])converters.toArray(new TypeConverter[0]);
         }

         invoker = new MethodInvoker(constructor, convertersArray, cost);
      }

      return invoker;
   }

   public static MethodInvoker buildInvoker(Method method, Class[] arguments) {
      MethodInvoker invoker = null;
      int size = 0;
      int cost = 0;
      if (arguments != null) {
         size = arguments.length;
      }

      List<TypeConverter> converters = new ArrayList();
      if (arguments != null && size > 0) {
         cost = buildConverters(converters, method.getParameterTypes(), arguments);
      }

      if (cost == -1) {
         invoker = INVALID_INVOKER;
      } else {
         TypeConverter[] convertersArray = null;
         if (!allNoConverter(converters)) {
            convertersArray = (TypeConverter[])converters.toArray(new TypeConverter[0]);
         }

         invoker = new MethodInvoker(method, convertersArray, cost);
      }

      return invoker;
   }

   public MethodInvoker(Constructor constructor, TypeConverter[] converters, int cost) {
      this.constructor = constructor;
      if (converters != null) {
         this.converters = Collections.unmodifiableList(Arrays.asList(converters));
      }

      this.cost = cost;
   }

   public MethodInvoker(Method method, TypeConverter[] converters, int cost) {
      this.method = method;
      if (converters != null) {
         this.converters = Collections.unmodifiableList(Arrays.asList(converters));
      }

      this.cost = cost;
   }

   public Constructor getConstructor() {
      return this.constructor;
   }

   public List getConverters() {
      return this.converters;
   }

   public int getCost() {
      return this.cost;
   }

   public Method getMethod() {
      return this.method;
   }

   public Object invoke(Object obj, Object[] arguments) {
      Object returnObject = null;

      try {
         Object[] newArguments = arguments;
         if (this.converters != null) {
            int size = arguments.length;
            newArguments = new Object[size];

            for(int i = 0; i < size; ++i) {
               newArguments[i] = ((TypeConverter)this.converters.get(i)).convert(arguments[i]);
            }
         }

         if (this.method != null) {
            AccessController.doPrivileged(new PrivilegedAction() {
               public Object run() {
                  ReflectionShim.trySetAccessible(MethodInvoker.this.method);
                  return null;
               }
            });
            returnObject = this.method.invoke(obj, newArguments);
         } else if (this.constructor != null) {
            ReflectionShim.trySetAccessible(this.constructor);
            returnObject = this.constructor.newInstance(newArguments);
         }

         return returnObject;
      } catch (InvocationTargetException ie) {
         this.logger.log(Level.WARNING, "Exception occurred in client code.", ie);
         throw new Py4JJavaException(ie.getCause());
      } catch (Exception e) {
         this.logger.log(Level.WARNING, "Could not invoke method or received an exception while invoking.", e);
         throw new Py4JException(e);
      }
   }

   public boolean isVoid() {
      if (this.constructor != null) {
         return false;
      } else if (this.method != null) {
         return this.method.getReturnType().equals(Void.TYPE);
      } else {
         throw new Py4JException("Null method or constructor");
      }
   }
}
