package jodd.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import jodd.util.cl.ClassLoaderStrategy;

public class ReflectUtil {
   public static final Class[] NO_PARAMETERS = new Class[0];
   public static final String METHOD_GET_PREFIX = "get";
   public static final String METHOD_IS_PREFIX = "is";
   public static final String METHOD_SET_PREFIX = "set";
   private static Method _getMethod0;
   private static ReflectUtilSecurityManager SECURITY_MANAGER;

   public static Method getMethod0(Class c, String name, Class... parameterTypes) {
      try {
         return (Method)_getMethod0.invoke(c, name, parameterTypes);
      } catch (Exception var4) {
         return null;
      }
   }

   public static Method findMethod(Class c, String methodName) {
      return findDeclaredMethod(c, methodName, true);
   }

   public static Method findDeclaredMethod(Class c, String methodName) {
      return findDeclaredMethod(c, methodName, false);
   }

   private static Method findDeclaredMethod(Class c, String methodName, boolean publicOnly) {
      if (methodName != null && c != null) {
         Method[] ms = publicOnly ? c.getMethods() : c.getDeclaredMethods();

         for(Method m : ms) {
            if (m.getName().equals(methodName)) {
               return m;
            }
         }

         return null;
      } else {
         return null;
      }
   }

   public static Class[] getClasses(Object... objects) {
      if (objects == null) {
         return null;
      } else {
         Class[] result = new Class[objects.length];

         for(int i = 0; i < objects.length; ++i) {
            if (objects[i] != null) {
               result[i] = objects[i].getClass();
            }
         }

         return result;
      }
   }

   public static Object invoke(Class c, Object obj, String method, Class[] paramClasses, Object[] params) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
      Method m = c.getMethod(method, paramClasses);
      return m.invoke(obj, params);
   }

   public static Object invoke(Class c, String method, Class[] paramClasses, Object[] params) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
      Method m = c.getMethod(method, paramClasses);
      return m.invoke((Object)null, params);
   }

   public static Object invoke(Object obj, String method, Class[] paramClasses, Object[] params) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
      Method m = obj.getClass().getMethod(method, paramClasses);
      return m.invoke(obj, params);
   }

   public static Object invoke(Object obj, String method, Object... params) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
      Class[] paramClass = getClasses(params);
      return invoke(obj, method, paramClass, params);
   }

   public static Object invoke(Class c, Object obj, String method, Object... params) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
      Class[] paramClass = getClasses(params);
      return invoke(c, obj, method, paramClass, params);
   }

   public static Object invoke(Class c, String method, Object... params) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
      Class[] paramClass = getClasses(params);
      return invoke(c, (Object)null, method, paramClass, params);
   }

   public static Object invokeDeclared(Class c, Object obj, String method, Class[] paramClasses, Object[] params) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
      Method m = c.getDeclaredMethod(method, paramClasses);
      m.setAccessible(true);
      return m.invoke(obj, params);
   }

   public static Object invokeDeclared(Class c, String method, Class[] paramClasses, Object[] params) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
      Method m = c.getDeclaredMethod(method, paramClasses);
      m.setAccessible(true);
      return m.invoke((Object)null, params);
   }

   public static Object invokeDeclared(Object obj, String method, Class[] paramClasses, Object[] params) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
      Method m = obj.getClass().getDeclaredMethod(method, paramClasses);
      m.setAccessible(true);
      return m.invoke(obj, params);
   }

   public static Object invokeDeclared(Object obj, String method, Object... params) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
      Class[] paramClass = getClasses(params);
      return invokeDeclared(obj, method, paramClass, params);
   }

   public static Object invokeDeclared(Class c, Object obj, String method, Object... params) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
      Class[] paramClass = getClasses(params);
      return invokeDeclared(c, obj, method, paramClass, params);
   }

   public static Object invokeDeclared(Class c, String method, Object... params) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
      Class[] paramClass = getClasses(params);
      return invokeDeclared(c, (Object)null, method, paramClass, params);
   }

   public static boolean isSubclass(Class thisClass, Class target) {
      if (target.isInterface()) {
         return isInterfaceImpl(thisClass, target);
      } else {
         for(Class x = thisClass; x != null; x = x.getSuperclass()) {
            if (x == target) {
               return true;
            }
         }

         return false;
      }
   }

   public static boolean isInterfaceImpl(Class thisClass, Class targetInterface) {
      for(Class x = thisClass; x != null; x = x.getSuperclass()) {
         Class[] interfaces = x.getInterfaces();

         for(Class i : interfaces) {
            if (i == targetInterface) {
               return true;
            }

            if (isInterfaceImpl(i, targetInterface)) {
               return true;
            }
         }
      }

      return false;
   }

   public static boolean isInstanceOf(Object o, Class target) {
      return isSubclass(o.getClass(), target);
   }

   public static Method[] getAccessibleMethods(Class clazz) {
      return getAccessibleMethods(clazz, Object.class);
   }

   public static Method[] getAccessibleMethods(Class clazz, Class limit) {
      Package topPackage = clazz.getPackage();
      List<Method> methodList = new ArrayList();
      int topPackageHash = topPackage == null ? 0 : topPackage.hashCode();
      boolean top = true;

      while(clazz != null) {
         Method[] declaredMethods = clazz.getDeclaredMethods();

         for(Method method : declaredMethods) {
            if (!Modifier.isVolatile(method.getModifiers())) {
               if (top) {
                  methodList.add(method);
               } else {
                  int modifier = method.getModifiers();
                  if (!Modifier.isPrivate(modifier) && !Modifier.isAbstract(modifier)) {
                     if (Modifier.isPublic(modifier)) {
                        addMethodIfNotExist(methodList, method);
                     } else if (Modifier.isProtected(modifier)) {
                        addMethodIfNotExist(methodList, method);
                     } else {
                        Package pckg = method.getDeclaringClass().getPackage();
                        int pckgHash = pckg == null ? 0 : pckg.hashCode();
                        if (pckgHash == topPackageHash) {
                           addMethodIfNotExist(methodList, method);
                        }
                     }
                  }
               }
            }
         }

         top = false;
         if ((clazz = clazz.getSuperclass()) == limit) {
            break;
         }
      }

      Method[] methods = new Method[methodList.size()];

      for(int i = 0; i < methods.length; ++i) {
         methods[i] = (Method)methodList.get(i);
      }

      return methods;
   }

   private static void addMethodIfNotExist(List allMethods, Method newMethod) {
      for(Method m : allMethods) {
         if (compareSignatures(m, newMethod)) {
            return;
         }
      }

      allMethods.add(newMethod);
   }

   public static Field[] getAccessibleFields(Class clazz) {
      return getAccessibleFields(clazz, Object.class);
   }

   public static Field[] getAccessibleFields(Class clazz, Class limit) {
      Package topPackage = clazz.getPackage();
      List<Field> fieldList = new ArrayList();
      int topPackageHash = topPackage == null ? 0 : topPackage.hashCode();
      boolean top = true;

      while(clazz != null) {
         Field[] declaredFields = clazz.getDeclaredFields();

         for(Field field : declaredFields) {
            if (top) {
               fieldList.add(field);
            } else {
               int modifier = field.getModifiers();
               if (!Modifier.isPrivate(modifier)) {
                  if (Modifier.isPublic(modifier)) {
                     addFieldIfNotExist(fieldList, field);
                  } else if (Modifier.isProtected(modifier)) {
                     addFieldIfNotExist(fieldList, field);
                  } else {
                     Package pckg = field.getDeclaringClass().getPackage();
                     int pckgHash = pckg == null ? 0 : pckg.hashCode();
                     if (pckgHash == topPackageHash) {
                        addFieldIfNotExist(fieldList, field);
                     }
                  }
               }
            }
         }

         top = false;
         if ((clazz = clazz.getSuperclass()) == limit) {
            break;
         }
      }

      Field[] fields = new Field[fieldList.size()];

      for(int i = 0; i < fields.length; ++i) {
         fields[i] = (Field)fieldList.get(i);
      }

      return fields;
   }

   private static void addFieldIfNotExist(List allFields, Field newField) {
      for(Field f : allFields) {
         if (compareSignatures(f, newField)) {
            return;
         }
      }

      allFields.add(newField);
   }

   public static Method[] getSupportedMethods(Class clazz) {
      return getSupportedMethods(clazz, Object.class);
   }

   public static Method[] getSupportedMethods(Class clazz, Class limit) {
      ArrayList<Method> supportedMethods = new ArrayList();

      for(Class c = clazz; c != limit; c = c.getSuperclass()) {
         Method[] methods = c.getDeclaredMethods();

         for(Method method : methods) {
            boolean found = false;

            for(Method supportedMethod : supportedMethods) {
               if (compareSignatures(method, supportedMethod)) {
                  found = true;
                  break;
               }
            }

            if (!found) {
               supportedMethods.add(method);
            }
         }
      }

      return (Method[])supportedMethods.toArray(new Method[supportedMethods.size()]);
   }

   public static Field[] getSupportedFields(Class clazz) {
      return getSupportedFields(clazz, Object.class);
   }

   public static Field[] getSupportedFields(Class clazz, Class limit) {
      ArrayList<Field> supportedFields = new ArrayList();

      for(Class c = clazz; c != limit; c = c.getSuperclass()) {
         Field[] fields = c.getDeclaredFields();

         for(Field field : fields) {
            boolean found = false;

            for(Field supportedField : supportedFields) {
               if (compareSignatures(field, supportedField)) {
                  found = true;
                  break;
               }
            }

            if (!found) {
               supportedFields.add(field);
            }
         }
      }

      return (Field[])supportedFields.toArray(new Field[supportedFields.size()]);
   }

   public static boolean compareDeclarations(Method first, Method second) {
      return first.getReturnType() != second.getReturnType() ? false : compareSignatures(first, second);
   }

   public static boolean compareSignatures(Method first, Method second) {
      return !first.getName().equals(second.getName()) ? false : compareParameters(first.getParameterTypes(), second.getParameterTypes());
   }

   public static boolean compareSignatures(Constructor first, Constructor second) {
      return !first.getName().equals(second.getName()) ? false : compareParameters(first.getParameterTypes(), second.getParameterTypes());
   }

   public static boolean compareSignatures(Field first, Field second) {
      return first.getName().equals(second.getName());
   }

   public static boolean compareParameters(Class[] first, Class[] second) {
      if (first.length != second.length) {
         return false;
      } else {
         for(int i = 0; i < first.length; ++i) {
            if (first[i] != second[i]) {
               return false;
            }
         }

         return true;
      }
   }

   public static void forceAccess(AccessibleObject accObject) {
      if (!accObject.isAccessible()) {
         try {
            accObject.setAccessible(true);
         } catch (SecurityException var2) {
         }

      }
   }

   public static boolean isPublic(Member member) {
      return Modifier.isPublic(member.getModifiers());
   }

   public static boolean isPublicPublic(Member member) {
      return Modifier.isPublic(member.getModifiers()) && Modifier.isPublic(member.getDeclaringClass().getModifiers());
   }

   public static boolean isPublic(Class c) {
      return Modifier.isPublic(c.getModifiers());
   }

   public static Object newInstance(Class type) throws IllegalAccessException, InstantiationException {
      if (type.isPrimitive()) {
         if (type == Integer.TYPE) {
            return 0;
         } else if (type == Long.TYPE) {
            return 0L;
         } else if (type == Boolean.TYPE) {
            return Boolean.FALSE;
         } else if (type == Float.TYPE) {
            return 0.0F;
         } else if (type == Double.TYPE) {
            return (double)0.0F;
         } else if (type == Byte.TYPE) {
            return 0;
         } else if (type == Short.TYPE) {
            return Short.valueOf((short)0);
         } else if (type == Character.TYPE) {
            return '\u0000';
         } else {
            throw new IllegalArgumentException("Invalid primitive type: " + type);
         }
      } else if (type == Integer.class) {
         return 0;
      } else if (type == String.class) {
         return "";
      } else if (type == Long.class) {
         return 0L;
      } else if (type == Boolean.class) {
         return Boolean.FALSE;
      } else {
         if (type == Float.class) {
            0.0F;
         }

         if (type == Double.class) {
            (double)0.0F;
         }

         if (type == Map.class) {
            return new HashMap();
         } else if (type == List.class) {
            return new ArrayList();
         } else if (type == Set.class) {
            return new LinkedHashSet();
         } else if (type == Collection.class) {
            return new ArrayList();
         } else if (type == Byte.class) {
            return 0;
         } else if (type == Short.class) {
            return Short.valueOf((short)0);
         } else if (type == Character.class) {
            return '\u0000';
         } else if (type.isEnum()) {
            return type.getEnumConstants()[0];
         } else {
            return type.isArray() ? Array.newInstance(type.getComponentType(), 0) : type.newInstance();
         }
      }
   }

   public static boolean isAssignableFrom(Member member1, Member member2) {
      return member1.getDeclaringClass().isAssignableFrom(member2.getDeclaringClass());
   }

   public static Class[] getSuperclasses(Class type) {
      int i = 0;

      for(Class x = type.getSuperclass(); x != null; x = x.getSuperclass()) {
         ++i;
      }

      Class[] result = new Class[i];
      i = 0;

      for(Class x = type.getSuperclass(); x != null; x = x.getSuperclass()) {
         result[i] = x;
         ++i;
      }

      return result;
   }

   public static boolean isUserDefinedMethod(Method method) {
      return method.getDeclaringClass() != Object.class;
   }

   public static boolean isObjectMethod(Method method) {
      return method.getDeclaringClass() == Object.class;
   }

   public static boolean isBeanProperty(Method method) {
      if (isObjectMethod(method)) {
         return false;
      } else {
         String methodName = method.getName();
         Class returnType = method.getReturnType();
         Class[] paramTypes = method.getParameterTypes();
         if (methodName.startsWith("get")) {
            if (returnType != null && paramTypes.length == 0) {
               return true;
            }
         } else if (methodName.startsWith("is")) {
            if (returnType != null && paramTypes.length == 0) {
               return true;
            }
         } else if (methodName.startsWith("set") && paramTypes.length == 1) {
            return true;
         }

         return false;
      }
   }

   public static boolean isBeanPropertyGetter(Method method) {
      return getBeanPropertyGetterPrefixLength(method) != 0;
   }

   private static int getBeanPropertyGetterPrefixLength(Method method) {
      if (isObjectMethod(method)) {
         return 0;
      } else {
         String methodName = method.getName();
         Class returnType = method.getReturnType();
         Class[] paramTypes = method.getParameterTypes();
         if (methodName.startsWith("get")) {
            if (returnType != null && paramTypes.length == 0) {
               return 3;
            }
         } else if (methodName.startsWith("is") && returnType != null && paramTypes.length == 0) {
            return 2;
         }

         return 0;
      }
   }

   public static String getBeanPropertyGetterName(Method method) {
      int prefixLength = getBeanPropertyGetterPrefixLength(method);
      if (prefixLength == 0) {
         return null;
      } else {
         String methodName = method.getName().substring(prefixLength);
         return StringUtil.decapitalize(methodName);
      }
   }

   public static boolean isBeanPropertySetter(Method method) {
      return getBeanPropertySetterPrefixLength(method) != 0;
   }

   private static int getBeanPropertySetterPrefixLength(Method method) {
      if (isObjectMethod(method)) {
         return 0;
      } else {
         String methodName = method.getName();
         Class[] paramTypes = method.getParameterTypes();
         return methodName.startsWith("set") && paramTypes.length == 1 ? 3 : 0;
      }
   }

   public static String getBeanPropertySetterName(Method method) {
      int prefixLength = getBeanPropertySetterPrefixLength(method);
      if (prefixLength == 0) {
         return null;
      } else {
         String methodName = method.getName().substring(prefixLength);
         return StringUtil.decapitalize(methodName);
      }
   }

   public static Class getComponentType(Type type) {
      return getComponentType(type, (Class)null);
   }

   public static Class getComponentType(Type type, Class implClass) {
      Class[] componentTypes = getComponentTypes(type, implClass);
      return componentTypes == null ? null : componentTypes[componentTypes.length - 1];
   }

   public static Class[] getComponentTypes(Type type) {
      return getComponentTypes(type, (Class)null);
   }

   public static Class[] getComponentTypes(Type type, Class implClass) {
      if (type instanceof Class) {
         Class clazz = (Class)type;
         if (clazz.isArray()) {
            return new Class[]{clazz.getComponentType()};
         }
      } else {
         if (type instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType)type;
            Type[] generics = pt.getActualTypeArguments();
            if (generics.length == 0) {
               return null;
            }

            Class[] types = new Class[generics.length];

            for(int i = 0; i < generics.length; ++i) {
               types[i] = getRawType(generics[i], implClass);
            }

            return types;
         }

         if (type instanceof GenericArrayType) {
            GenericArrayType gat = (GenericArrayType)type;
            Class rawType = getRawType(gat.getGenericComponentType(), implClass);
            if (rawType == null) {
               return null;
            }

            return new Class[]{rawType};
         }
      }

      return null;
   }

   public static Class[] getGenericSupertypes(Class type) {
      return getComponentTypes(type.getGenericSuperclass());
   }

   public static Class getGenericSupertype(Class type) {
      Class[] componentTypes = getComponentTypes(type.getGenericSuperclass());
      return componentTypes == null ? null : componentTypes[0];
   }

   public static Class getRawType(Type type) {
      return getRawType(type, (Class)null);
   }

   public static Class getRawType(Type type, Class implClass) {
      if (type instanceof Class) {
         return (Class)type;
      } else if (type instanceof ParameterizedType) {
         ParameterizedType pType = (ParameterizedType)type;
         return getRawType(pType.getRawType(), implClass);
      } else if (type instanceof WildcardType) {
         WildcardType wType = (WildcardType)type;
         Type[] lowerTypes = wType.getLowerBounds();
         if (lowerTypes.length > 0) {
            return getRawType(lowerTypes[0], implClass);
         } else {
            Type[] upperTypes = wType.getUpperBounds();
            return upperTypes.length != 0 ? getRawType(upperTypes[0], implClass) : Object.class;
         }
      } else if (type instanceof GenericArrayType) {
         Type genericComponentType = ((GenericArrayType)type).getGenericComponentType();
         Class<?> rawType = getRawType(genericComponentType, implClass);
         return Array.newInstance(rawType, 0).getClass();
      } else if (type instanceof TypeVariable) {
         TypeVariable<?> varType = (TypeVariable)type;
         if (implClass != null) {
            Type resolvedType = resolveVariable(varType, implClass);
            if (resolvedType != null) {
               return getRawType(resolvedType, (Class)null);
            }
         }

         Type[] boundsTypes = varType.getBounds();
         return boundsTypes.length == 0 ? Object.class : getRawType(boundsTypes[0], implClass);
      } else {
         return null;
      }
   }

   public static Type resolveVariable(TypeVariable variable, Class implClass) {
      Class rawType = getRawType(implClass, (Class)null);
      int index = ArraysUtil.indexOf((Object[])rawType.getTypeParameters(), (Object)variable);
      if (index >= 0) {
         return variable;
      } else {
         Class[] interfaces = rawType.getInterfaces();
         Type[] genericInterfaces = rawType.getGenericInterfaces();

         for(int i = 0; i <= interfaces.length; ++i) {
            Class rawInterface;
            if (i < interfaces.length) {
               rawInterface = interfaces[i];
            } else {
               rawInterface = rawType.getSuperclass();
               if (rawInterface == null) {
                  continue;
               }
            }

            Type resolved = resolveVariable(variable, rawInterface);
            if (resolved instanceof Class || resolved instanceof ParameterizedType) {
               return resolved;
            }

            if (resolved instanceof TypeVariable) {
               TypeVariable typeVariable = (TypeVariable)resolved;
               index = ArraysUtil.indexOf((Object[])rawInterface.getTypeParameters(), (Object)typeVariable);
               if (index < 0) {
                  throw new IllegalArgumentException("Invalid type variable:" + typeVariable);
               }

               Type type = i < genericInterfaces.length ? genericInterfaces[i] : rawType.getGenericSuperclass();
               if (type instanceof Class) {
                  return Object.class;
               }

               if (type instanceof ParameterizedType) {
                  return ((ParameterizedType)type).getActualTypeArguments()[index];
               }

               throw new IllegalArgumentException("Unsupported type: " + type);
            }
         }

         return null;
      }
   }

   public static String typeToString(Type type) {
      StringBuilder sb = new StringBuilder();
      typeToString(sb, type, new HashSet());
      return sb.toString();
   }

   private static void typeToString(StringBuilder sb, Type type, Set visited) {
      if (type instanceof ParameterizedType) {
         ParameterizedType parameterizedType = (ParameterizedType)type;
         Class<?> rawType = (Class)parameterizedType.getRawType();
         sb.append(rawType.getName());
         boolean first = true;

         for(Type typeArg : parameterizedType.getActualTypeArguments()) {
            if (first) {
               first = false;
            } else {
               sb.append(", ");
            }

            sb.append('<');
            typeToString(sb, typeArg, visited);
            sb.append('>');
         }
      } else if (type instanceof WildcardType) {
         WildcardType wildcardType = (WildcardType)type;
         sb.append('?');
         Type bound;
         if (wildcardType.getLowerBounds().length != 0) {
            sb.append(" super ");
            bound = wildcardType.getLowerBounds()[0];
         } else {
            sb.append(" extends ");
            bound = wildcardType.getUpperBounds()[0];
         }

         typeToString(sb, bound, visited);
      } else if (type instanceof TypeVariable) {
         TypeVariable<?> typeVariable = (TypeVariable)type;
         sb.append(typeVariable.getName());
         if (!visited.contains(type)) {
            visited.add(type);
            sb.append(" extends ");
            boolean first = true;

            for(Type bound : typeVariable.getBounds()) {
               if (first) {
                  first = false;
               } else {
                  sb.append(" & ");
               }

               typeToString(sb, bound, visited);
            }

            visited.remove(type);
         }
      } else if (type instanceof GenericArrayType) {
         GenericArrayType genericArrayType = (GenericArrayType)type;
         typeToString(genericArrayType.getGenericComponentType());
         sb.append(genericArrayType.getGenericComponentType());
         sb.append("[]");
      } else {
         if (!(type instanceof Class)) {
            throw new IllegalArgumentException("Unsupported type: " + type);
         }

         Class<?> typeClass = (Class)type;
         sb.append(typeClass.getName());
      }

   }

   public static Object readAnnotationValue(Annotation annotation, String name) {
      try {
         Method method = annotation.annotationType().getDeclaredMethod(name);
         return method.invoke(annotation);
      } catch (Exception var3) {
         return null;
      }
   }

   public static Class getCallerClass(int framesToSkip) {
      if (SECURITY_MANAGER != null) {
         return SECURITY_MANAGER.getCallerClass(framesToSkip);
      } else {
         StackTraceElement[] stackTraceElements = (new Throwable()).getStackTrace();
         if (framesToSkip >= 2) {
            framesToSkip += 4;
         }

         String className = stackTraceElements[framesToSkip].getClassName();

         try {
            return Thread.currentThread().getContextClassLoader().loadClass(className);
         } catch (ClassNotFoundException var4) {
            throw new UnsupportedOperationException(className + " not found.");
         }
      }
   }

   public static Class getCallerClass() {
      String className = null;
      StackTraceElement[] stackTraceElements = (new Throwable()).getStackTrace();

      for(StackTraceElement stackTraceElement : stackTraceElements) {
         className = stackTraceElement.getClassName();
         String methodName = stackTraceElement.getMethodName();
         if (methodName.equals("loadClass")) {
            if (!className.contains(ClassLoaderStrategy.class.getSimpleName()) && !className.equals(ClassLoaderUtil.class.getName())) {
               break;
            }
         } else if (!methodName.equals("getCallerClass")) {
            break;
         }
      }

      try {
         return Thread.currentThread().getContextClassLoader().loadClass(className);
      } catch (ClassNotFoundException var7) {
         throw new UnsupportedOperationException(className + " not found.");
      }
   }

   static {
      try {
         _getMethod0 = Class.class.getDeclaredMethod("getMethod0", String.class, Class[].class);
         _getMethod0.setAccessible(true);
      } catch (Exception var4) {
         try {
            _getMethod0 = Class.class.getMethod("getMethod", String.class, Class[].class);
         } catch (Exception var3) {
            _getMethod0 = null;
         }
      }

      try {
         SECURITY_MANAGER = new ReflectUtilSecurityManager();
      } catch (Exception var2) {
         SECURITY_MANAGER = null;
      }

   }

   private static class ReflectUtilSecurityManager extends SecurityManager {
      private ReflectUtilSecurityManager() {
      }

      public Class getCallerClass(int callStackDepth) {
         return this.getClassContext()[callStackDepth + 1];
      }
   }
}
