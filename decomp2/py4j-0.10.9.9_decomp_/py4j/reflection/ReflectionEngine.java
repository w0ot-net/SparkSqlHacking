package py4j.reflection;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import py4j.Py4JException;

public class ReflectionEngine {
   public static final int cacheSize = 100;
   private final Logger logger = Logger.getLogger(ReflectionEngine.class.getName());
   public static final Object RETURN_VOID = new Object();
   private static ThreadLocal cacheHolder = new ThreadLocal() {
      protected LRUCache initialValue() {
         return new LRUCache(100);
      }
   };

   public Object createArray(String fqn, int[] dimensions) {
      Class<?> clazz = null;
      Object returnObject = null;

      try {
         clazz = TypeUtil.forName(fqn);
         returnObject = Array.newInstance(clazz, dimensions);
         return returnObject;
      } catch (Exception e) {
         this.logger.log(Level.WARNING, "Class FQN does not exist: " + fqn, e);
         throw new Py4JException(e);
      }
   }

   private MethodInvoker getBestConstructor(List acceptableConstructors, Class[] parameters) {
      MethodInvoker lowestCost = null;

      for(Constructor constructor : acceptableConstructors) {
         MethodInvoker temp = MethodInvoker.buildInvoker(constructor, parameters);
         int cost = temp.getCost();
         if (cost != -1) {
            if (cost == 0) {
               lowestCost = temp;
               break;
            }

            if (lowestCost == null || cost < lowestCost.getCost()) {
               lowestCost = temp;
            }
         }
      }

      return lowestCost;
   }

   private MethodInvoker getBestMethod(List acceptableMethods, Class[] parameters) {
      MethodInvoker lowestCost = null;

      for(Method method : acceptableMethods) {
         MethodInvoker temp = MethodInvoker.buildInvoker(method, parameters);
         int cost = temp.getCost();
         if (cost != -1) {
            if (cost == 0) {
               lowestCost = temp;
               break;
            }

            if (lowestCost == null || cost < lowestCost.getCost()) {
               lowestCost = temp;
            }
         }
      }

      return lowestCost;
   }

   public Class getClass(Class clazz, String name) {
      Class<?> memberClass = null;

      try {
         for(Class tempClass : clazz.getClasses()) {
            if (tempClass.getSimpleName().equals(name)) {
               memberClass = tempClass;
               break;
            }
         }
      } catch (Exception var8) {
         memberClass = null;
      }

      return memberClass;
   }

   public Class[] getClassParameters(Object[] parameters) {
      int size = parameters.length;
      Class<?>[] classes = new Class[size];

      for(int i = 0; i < size; ++i) {
         if (parameters[i] == null) {
            classes[i] = null;
         } else {
            classes[i] = parameters[i].getClass();
         }
      }

      return classes;
   }

   public MethodInvoker getConstructor(Class clazz, Class[] parameters) {
      MethodDescriptor mDescriptor = new MethodDescriptor(clazz.getName(), clazz, parameters);
      MethodInvoker mInvoker = null;
      List<Constructor<?>> acceptableConstructors = null;
      LRUCache<MethodDescriptor, MethodInvoker> cache = (LRUCache)cacheHolder.get();
      mInvoker = (MethodInvoker)cache.get(mDescriptor);
      if (mInvoker == null) {
         acceptableConstructors = this.getConstructorsByLength(clazz, parameters.length);
         if (acceptableConstructors.size() == 1) {
            mInvoker = MethodInvoker.buildInvoker((Constructor)acceptableConstructors.get(0), parameters);
         } else {
            mInvoker = this.getBestConstructor(acceptableConstructors, parameters);
         }

         if (mInvoker == null || mInvoker.getCost() == -1) {
            String errorMessage = "Constructor " + clazz.getName() + "(" + Arrays.toString(parameters) + ") does not exist";
            this.logger.log(Level.WARNING, errorMessage);
            throw new Py4JException(errorMessage);
         }

         cache.put(mDescriptor, mInvoker);
      }

      return mInvoker;
   }

   public MethodInvoker getConstructor(String classFQN, Object[] parameters) {
      Class<?> clazz = null;

      try {
         clazz = ReflectionUtil.classForName(classFQN);
      } catch (Exception e) {
         this.logger.log(Level.WARNING, "Class FQN does not exist: " + classFQN, e);
         throw new Py4JException(e);
      }

      return this.getConstructor(clazz, this.getClassParameters(parameters));
   }

   private List getConstructorsByLength(Class clazz, int length) {
      List<Constructor<?>> methods = new ArrayList();

      for(Constructor constructor : clazz.getConstructors()) {
         if (constructor.getParameterTypes().length == length && ReflectionShim.trySetAccessible(constructor)) {
            methods.add(constructor);
         }
      }

      return methods;
   }

   public Field getField(Class clazz, String name) {
      Field field = null;

      try {
         field = clazz.getField(name);
         if (!Modifier.isPublic(field.getModifiers()) && !field.isAccessible()) {
            field = null;
         }
      } catch (NoSuchFieldException var5) {
         field = null;
      } catch (Exception var6) {
         field = null;
      }

      return field;
   }

   public Field getField(Object obj, String name) {
      return this.getField(obj.getClass(), name);
   }

   public Field getField(String classFQN, String name) {
      Class<?> clazz = null;

      try {
         clazz = ReflectionUtil.classForName(classFQN);
      } catch (Exception e) {
         this.logger.log(Level.WARNING, "Class FQN does not exist: " + classFQN, e);
         throw new Py4JException(e);
      }

      return this.getField(clazz, name);
   }

   public Object getFieldValue(Object obj, Field field) {
      Object fieldValue = null;

      try {
         fieldValue = field.get(obj);
         return fieldValue;
      } catch (Exception e) {
         this.logger.log(Level.SEVERE, "Error while fetching field value of " + field, e);
         throw new Py4JException(e);
      }
   }

   public Method getMethod(Class clazz, String name) {
      Method m = null;

      try {
         for(Method tempMethod : clazz.getMethods()) {
            if (tempMethod.getName().equals(name)) {
               m = tempMethod;
               break;
            }
         }
      } catch (Exception var8) {
         m = null;
      }

      return m;
   }

   public MethodInvoker getMethod(Class clazz, String name, Class[] parameters) {
      MethodDescriptor mDescriptor = new MethodDescriptor(name, clazz, parameters);
      MethodInvoker mInvoker = null;
      List<Method> acceptableMethods = null;
      LRUCache<MethodDescriptor, MethodInvoker> cache = (LRUCache)cacheHolder.get();
      mInvoker = (MethodInvoker)cache.get(mDescriptor);
      if (mInvoker == null) {
         acceptableMethods = this.getMethodsByNameAndLength(clazz, name, parameters.length);
         if (acceptableMethods.size() == 1) {
            mInvoker = MethodInvoker.buildInvoker((Method)acceptableMethods.get(0), parameters);
         } else {
            mInvoker = this.getBestMethod(acceptableMethods, parameters);
         }

         if (mInvoker == null || mInvoker.getCost() == -1) {
            String errorMessage = "Method " + name + "(" + Arrays.toString(parameters) + ") does not exist";
            this.logger.log(Level.WARNING, errorMessage);
            throw new Py4JException(errorMessage);
         }

         cache.put(mDescriptor, mInvoker);
      }

      return mInvoker;
   }

   public MethodInvoker getMethod(Object object, String name, Object[] parameters) {
      return this.getMethod(object.getClass(), name, this.getClassParameters(parameters));
   }

   public MethodInvoker getMethod(String classFQN, String name, Object[] parameters) {
      Class<?> clazz = null;

      try {
         clazz = ReflectionUtil.classForName(classFQN);
      } catch (Exception e) {
         this.logger.log(Level.WARNING, "Class FQN does not exist: " + classFQN, e);
         throw new Py4JException(e);
      }

      return this.getMethod(clazz, name, this.getClassParameters(parameters));
   }

   private List getMethodsByNameAndLength(Class clazz, String name, int length) {
      List<Method> methodsToCheck;
      for(methodsToCheck = new ArrayList(); clazz != null; clazz = clazz.getSuperclass()) {
         methodsToCheck.addAll(Arrays.asList(clazz.getDeclaredMethods()));

         for(Class intf : clazz.getInterfaces()) {
            methodsToCheck.addAll(Arrays.asList(intf.getDeclaredMethods()));
         }
      }

      List<Method> methods = new ArrayList();

      for(Method method : methodsToCheck) {
         if (method.getName().equals(name) && method.getParameterTypes().length == length && ReflectionShim.trySetAccessible(method)) {
            methods.add(method);
         }
      }

      return methods;
   }

   public Object invoke(Object object, MethodInvoker invoker, Object[] parameters) {
      Object returnObject = null;
      returnObject = invoker.invoke(object, parameters);
      if (invoker.isVoid()) {
         returnObject = RETURN_VOID;
      }

      return returnObject;
   }

   public void setFieldValue(Object obj, Field field, Object value) {
      try {
         field.set(obj, value);
      } catch (Exception e) {
         this.logger.log(Level.SEVERE, "Error while setting field value of " + field, e);
         throw new Py4JException(e);
      }
   }

   public String[] getPublicMethodNames(Object obj) {
      Method[] methods = obj.getClass().getMethods();
      Set<String> methodNames = new HashSet();

      for(Method method : methods) {
         if (Modifier.isPublic(method.getModifiers())) {
            methodNames.add(method.getName());
         }
      }

      return (String[])methodNames.toArray(new String[methodNames.size()]);
   }

   public String[] getPublicFieldNames(Object obj) {
      Field[] fields = obj.getClass().getFields();
      Set<String> fieldNames = new HashSet();

      for(Field field : fields) {
         if (Modifier.isPublic(field.getModifiers())) {
            fieldNames.add(field.getName());
         }
      }

      return (String[])fieldNames.toArray(new String[fieldNames.size()]);
   }

   public String[] getPublicStaticFieldNames(Class clazz) {
      Field[] fields = clazz.getFields();
      Set<String> fieldNames = new HashSet();

      for(Field field : fields) {
         if (Modifier.isPublic(field.getModifiers()) && Modifier.isStatic(field.getModifiers())) {
            fieldNames.add(field.getName());
         }
      }

      return (String[])fieldNames.toArray(new String[fieldNames.size()]);
   }

   public String[] getPublicStaticMethodNames(Class clazz) {
      Method[] methods = clazz.getMethods();
      Set<String> methodNames = new HashSet();

      for(Method method : methods) {
         if (Modifier.isPublic(method.getModifiers()) && Modifier.isStatic(method.getModifiers())) {
            methodNames.add(method.getName());
         }
      }

      return (String[])methodNames.toArray(new String[methodNames.size()]);
   }

   public String[] getPublicStaticClassNames(Class clazz) {
      Class<?>[] classes = clazz.getClasses();
      Set<String> classNames = new HashSet();

      for(Class clazz2 : classes) {
         if (Modifier.isPublic(clazz2.getModifiers()) && Modifier.isStatic(clazz2.getModifiers())) {
            classNames.add(clazz2.getSimpleName());
         }
      }

      return (String[])classNames.toArray(new String[classNames.size()]);
   }

   public String[] getPublicStaticNames(Class clazz) {
      Set<String> names = new HashSet();
      names.addAll(Arrays.asList(this.getPublicStaticClassNames(clazz)));
      names.addAll(Arrays.asList(this.getPublicStaticFieldNames(clazz)));
      names.addAll(Arrays.asList(this.getPublicStaticMethodNames(clazz)));
      return (String[])names.toArray(new String[names.size()]);
   }
}
