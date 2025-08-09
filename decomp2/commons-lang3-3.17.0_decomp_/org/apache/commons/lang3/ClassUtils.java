package org.apache.commons.lang3;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.mutable.MutableObject;

public class ClassUtils {
   private static final Comparator COMPARATOR = (o1, o2) -> Objects.compare(getName(o1), getName(o2), String::compareTo);
   public static final char PACKAGE_SEPARATOR_CHAR = '.';
   public static final String PACKAGE_SEPARATOR = String.valueOf('.');
   public static final char INNER_CLASS_SEPARATOR_CHAR = '$';
   public static final String INNER_CLASS_SEPARATOR = String.valueOf('$');
   private static final Map namePrimitiveMap = new HashMap();
   private static final Map primitiveWrapperMap;
   private static final Map wrapperPrimitiveMap;
   private static final Map abbreviationMap;
   private static final Map reverseAbbreviationMap;

   public static Comparator comparator() {
      return COMPARATOR;
   }

   public static List convertClassesToClassNames(List classes) {
      return classes == null ? null : (List)classes.stream().map((e) -> getName((Class)e, (String)null)).collect(Collectors.toList());
   }

   public static List convertClassNamesToClasses(List classNames) {
      if (classNames == null) {
         return null;
      } else {
         List<Class<?>> classes = new ArrayList(classNames.size());
         classNames.forEach((className) -> {
            try {
               classes.add(Class.forName(className));
            } catch (Exception var3) {
               classes.add((Object)null);
            }

         });
         return classes;
      }
   }

   public static String getAbbreviatedName(Class cls, int lengthHint) {
      return cls == null ? "" : getAbbreviatedName(cls.getName(), lengthHint);
   }

   public static String getAbbreviatedName(String className, int lengthHint) {
      if (lengthHint <= 0) {
         throw new IllegalArgumentException("len must be > 0");
      } else if (className == null) {
         return "";
      } else if (className.length() <= lengthHint) {
         return className;
      } else {
         char[] abbreviated = className.toCharArray();
         int target = 0;
         int source = 0;

         while(source < abbreviated.length) {
            int runAheadTarget;
            for(runAheadTarget = target; source < abbreviated.length && abbreviated[source] != '.'; abbreviated[runAheadTarget++] = abbreviated[source++]) {
            }

            ++target;
            if (useFull(runAheadTarget, source, abbreviated.length, lengthHint) || target > runAheadTarget) {
               target = runAheadTarget;
            }

            if (source < abbreviated.length) {
               abbreviated[target++] = abbreviated[source++];
            }
         }

         return new String(abbreviated, 0, target);
      }
   }

   public static List getAllInterfaces(Class cls) {
      if (cls == null) {
         return null;
      } else {
         LinkedHashSet<Class<?>> interfacesFound = new LinkedHashSet();
         getAllInterfaces(cls, interfacesFound);
         return new ArrayList(interfacesFound);
      }
   }

   private static void getAllInterfaces(Class cls, HashSet interfacesFound) {
      while(cls != null) {
         Class<?>[] interfaces = cls.getInterfaces();

         for(Class i : interfaces) {
            if (interfacesFound.add(i)) {
               getAllInterfaces(i, interfacesFound);
            }
         }

         cls = cls.getSuperclass();
      }

   }

   public static List getAllSuperclasses(Class cls) {
      if (cls == null) {
         return null;
      } else {
         List<Class<?>> classes = new ArrayList();

         for(Class<?> superclass = cls.getSuperclass(); superclass != null; superclass = superclass.getSuperclass()) {
            classes.add(superclass);
         }

         return classes;
      }
   }

   public static String getCanonicalName(Class cls) {
      return getCanonicalName(cls, "");
   }

   public static String getCanonicalName(Class cls, String valueIfNull) {
      if (cls == null) {
         return valueIfNull;
      } else {
         String canonicalName = cls.getCanonicalName();
         return canonicalName == null ? valueIfNull : canonicalName;
      }
   }

   public static String getCanonicalName(Object object) {
      return getCanonicalName(object, "");
   }

   public static String getCanonicalName(Object object, String valueIfNull) {
      if (object == null) {
         return valueIfNull;
      } else {
         String canonicalName = object.getClass().getCanonicalName();
         return canonicalName == null ? valueIfNull : canonicalName;
      }
   }

   private static String getCanonicalName(String className) {
      className = StringUtils.deleteWhitespace(className);
      if (className == null) {
         return null;
      } else {
         int dim;
         for(dim = 0; className.startsWith("["); className = className.substring(1)) {
            ++dim;
         }

         if (dim < 1) {
            return className;
         } else {
            if (className.startsWith("L")) {
               className = className.substring(1, className.endsWith(";") ? className.length() - 1 : className.length());
            } else if (!className.isEmpty()) {
               className = (String)reverseAbbreviationMap.get(className.substring(0, 1));
            }

            StringBuilder canonicalClassNameBuffer = new StringBuilder(className);

            for(int i = 0; i < dim; ++i) {
               canonicalClassNameBuffer.append("[]");
            }

            return canonicalClassNameBuffer.toString();
         }
      }
   }

   public static Class getClass(ClassLoader classLoader, String className) throws ClassNotFoundException {
      return getClass(classLoader, className, true);
   }

   public static Class getClass(ClassLoader classLoader, String className, boolean initialize) throws ClassNotFoundException {
      try {
         Class<?> clazz = getPrimitiveClass(className);
         return clazz != null ? clazz : Class.forName(toCanonicalName(className), initialize, classLoader);
      } catch (ClassNotFoundException ex) {
         int lastDotIndex = className.lastIndexOf(46);
         if (lastDotIndex != -1) {
            try {
               return getClass(classLoader, className.substring(0, lastDotIndex) + '$' + className.substring(lastDotIndex + 1), initialize);
            } catch (ClassNotFoundException var6) {
            }
         }

         throw ex;
      }
   }

   public static Class getClass(String className) throws ClassNotFoundException {
      return getClass(className, true);
   }

   public static Class getClass(String className, boolean initialize) throws ClassNotFoundException {
      ClassLoader contextCL = Thread.currentThread().getContextClassLoader();
      ClassLoader loader = contextCL == null ? ClassUtils.class.getClassLoader() : contextCL;
      return getClass(loader, className, initialize);
   }

   public static Class getComponentType(Class cls) {
      return cls == null ? null : cls.getComponentType();
   }

   public static String getName(Class cls) {
      return getName(cls, "");
   }

   public static String getName(Class cls, String valueIfNull) {
      return cls == null ? valueIfNull : cls.getName();
   }

   public static String getName(Object object) {
      return getName(object, "");
   }

   public static String getName(Object object, String valueIfNull) {
      return object == null ? valueIfNull : object.getClass().getName();
   }

   public static String getPackageCanonicalName(Class cls) {
      return cls == null ? "" : getPackageCanonicalName(cls.getName());
   }

   public static String getPackageCanonicalName(Object object, String valueIfNull) {
      return object == null ? valueIfNull : getPackageCanonicalName(object.getClass().getName());
   }

   public static String getPackageCanonicalName(String name) {
      return getPackageName(getCanonicalName(name));
   }

   public static String getPackageName(Class cls) {
      return cls == null ? "" : getPackageName(cls.getName());
   }

   public static String getPackageName(Object object, String valueIfNull) {
      return object == null ? valueIfNull : getPackageName(object.getClass());
   }

   public static String getPackageName(String className) {
      if (StringUtils.isEmpty(className)) {
         return "";
      } else {
         while(className.charAt(0) == '[') {
            className = className.substring(1);
         }

         if (className.charAt(0) == 'L' && className.charAt(className.length() - 1) == ';') {
            className = className.substring(1);
         }

         int i = className.lastIndexOf(46);
         return i == -1 ? "" : className.substring(0, i);
      }
   }

   static Class getPrimitiveClass(String className) {
      return (Class)namePrimitiveMap.get(className);
   }

   public static Method getPublicMethod(Class cls, String methodName, Class... parameterTypes) throws NoSuchMethodException {
      Method declaredMethod = cls.getMethod(methodName, parameterTypes);
      if (isPublic(declaredMethod.getDeclaringClass())) {
         return declaredMethod;
      } else {
         List<Class<?>> candidateClasses = new ArrayList(getAllInterfaces(cls));
         candidateClasses.addAll(getAllSuperclasses(cls));

         for(Class candidateClass : candidateClasses) {
            if (isPublic(candidateClass)) {
               Method candidateMethod;
               try {
                  candidateMethod = candidateClass.getMethod(methodName, parameterTypes);
               } catch (NoSuchMethodException var9) {
                  continue;
               }

               if (Modifier.isPublic(candidateMethod.getDeclaringClass().getModifiers())) {
                  return candidateMethod;
               }
            }
         }

         throw new NoSuchMethodException("Can't find a public method for " + methodName + " " + ArrayUtils.toString(parameterTypes));
      }
   }

   public static String getShortCanonicalName(Class cls) {
      return cls == null ? "" : getShortCanonicalName(cls.getCanonicalName());
   }

   public static String getShortCanonicalName(Object object, String valueIfNull) {
      return object == null ? valueIfNull : getShortCanonicalName(object.getClass().getCanonicalName());
   }

   public static String getShortCanonicalName(String canonicalName) {
      return getShortClassName(getCanonicalName(canonicalName));
   }

   public static String getShortClassName(Class cls) {
      return cls == null ? "" : getShortClassName(cls.getName());
   }

   public static String getShortClassName(Object object, String valueIfNull) {
      return object == null ? valueIfNull : getShortClassName(object.getClass());
   }

   public static String getShortClassName(String className) {
      if (StringUtils.isEmpty(className)) {
         return "";
      } else {
         StringBuilder arrayPrefix = new StringBuilder();
         if (className.startsWith("[")) {
            while(className.charAt(0) == '[') {
               className = className.substring(1);
               arrayPrefix.append("[]");
            }

            if (className.charAt(0) == 'L' && className.charAt(className.length() - 1) == ';') {
               className = className.substring(1, className.length() - 1);
            }

            if (reverseAbbreviationMap.containsKey(className)) {
               className = (String)reverseAbbreviationMap.get(className);
            }
         }

         int lastDotIdx = className.lastIndexOf(46);
         int innerIdx = className.indexOf(36, lastDotIdx == -1 ? 0 : lastDotIdx + 1);
         String out = className.substring(lastDotIdx + 1);
         if (innerIdx != -1) {
            out = out.replace('$', '.');
         }

         return out + arrayPrefix;
      }
   }

   public static String getSimpleName(Class cls) {
      return getSimpleName(cls, "");
   }

   public static String getSimpleName(Class cls, String valueIfNull) {
      return cls == null ? valueIfNull : cls.getSimpleName();
   }

   public static String getSimpleName(Object object) {
      return getSimpleName(object, "");
   }

   public static String getSimpleName(Object object, String valueIfNull) {
      return object == null ? valueIfNull : object.getClass().getSimpleName();
   }

   public static Iterable hierarchy(Class type) {
      return hierarchy(type, ClassUtils.Interfaces.EXCLUDE);
   }

   public static Iterable hierarchy(Class type, Interfaces interfacesBehavior) {
      Iterable<Class<?>> classes = () -> {
         final MutableObject<Class<?>> next = new MutableObject(type);
         return new Iterator() {
            public boolean hasNext() {
               return next.getValue() != null;
            }

            public Class next() {
               Class<?> result = (Class)next.getValue();
               next.setValue(result.getSuperclass());
               return result;
            }

            public void remove() {
               throw new UnsupportedOperationException();
            }
         };
      };
      return interfacesBehavior != ClassUtils.Interfaces.INCLUDE ? classes : () -> {
         final Set<Class<?>> seenInterfaces = new HashSet();
         final Iterator<Class<?>> wrapped = classes.iterator();
         return new Iterator() {
            Iterator interfaces = Collections.emptyIterator();

            public boolean hasNext() {
               return this.interfaces.hasNext() || wrapped.hasNext();
            }

            public Class next() {
               if (this.interfaces.hasNext()) {
                  Class<?> nextInterface = (Class)this.interfaces.next();
                  seenInterfaces.add(nextInterface);
                  return nextInterface;
               } else {
                  Class<?> nextSuperclass = (Class)wrapped.next();
                  Set<Class<?>> currentInterfaces = new LinkedHashSet();
                  this.walkInterfaces(currentInterfaces, nextSuperclass);
                  this.interfaces = currentInterfaces.iterator();
                  return nextSuperclass;
               }
            }

            public void remove() {
               throw new UnsupportedOperationException();
            }

            private void walkInterfaces(Set addTo, Class c) {
               for(Class iface : c.getInterfaces()) {
                  if (!seenInterfaces.contains(iface)) {
                     addTo.add(iface);
                  }

                  this.walkInterfaces(addTo, iface);
               }

            }
         };
      };
   }

   public static boolean isAssignable(Class cls, Class toClass) {
      return isAssignable(cls, toClass, true);
   }

   public static boolean isAssignable(Class cls, Class toClass, boolean autoboxing) {
      if (toClass == null) {
         return false;
      } else if (cls == null) {
         return !toClass.isPrimitive();
      } else {
         if (autoboxing) {
            if (cls.isPrimitive() && !toClass.isPrimitive()) {
               cls = primitiveToWrapper(cls);
               if (cls == null) {
                  return false;
               }
            }

            if (toClass.isPrimitive() && !cls.isPrimitive()) {
               cls = wrapperToPrimitive(cls);
               if (cls == null) {
                  return false;
               }
            }
         }

         if (cls.equals(toClass)) {
            return true;
         } else if (cls.isPrimitive()) {
            if (!toClass.isPrimitive()) {
               return false;
            } else if (Integer.TYPE.equals(cls)) {
               return Long.TYPE.equals(toClass) || Float.TYPE.equals(toClass) || Double.TYPE.equals(toClass);
            } else if (Long.TYPE.equals(cls)) {
               return Float.TYPE.equals(toClass) || Double.TYPE.equals(toClass);
            } else if (Boolean.TYPE.equals(cls)) {
               return false;
            } else if (Double.TYPE.equals(cls)) {
               return false;
            } else if (Float.TYPE.equals(cls)) {
               return Double.TYPE.equals(toClass);
            } else if (!Character.TYPE.equals(cls) && !Short.TYPE.equals(cls)) {
               if (!Byte.TYPE.equals(cls)) {
                  return false;
               } else {
                  return Short.TYPE.equals(toClass) || Integer.TYPE.equals(toClass) || Long.TYPE.equals(toClass) || Float.TYPE.equals(toClass) || Double.TYPE.equals(toClass);
               }
            } else {
               return Integer.TYPE.equals(toClass) || Long.TYPE.equals(toClass) || Float.TYPE.equals(toClass) || Double.TYPE.equals(toClass);
            }
         } else {
            return toClass.isAssignableFrom(cls);
         }
      }
   }

   public static boolean isAssignable(Class[] classArray, Class... toClassArray) {
      return isAssignable(classArray, toClassArray, true);
   }

   public static boolean isAssignable(Class[] classArray, Class[] toClassArray, boolean autoboxing) {
      if (!ArrayUtils.isSameLength((Object[])classArray, (Object[])toClassArray)) {
         return false;
      } else {
         classArray = ArrayUtils.nullToEmpty(classArray);
         toClassArray = ArrayUtils.nullToEmpty(toClassArray);

         for(int i = 0; i < classArray.length; ++i) {
            if (!isAssignable(classArray[i], toClassArray[i], autoboxing)) {
               return false;
            }
         }

         return true;
      }
   }

   public static boolean isInnerClass(Class cls) {
      return cls != null && cls.getEnclosingClass() != null;
   }

   public static boolean isPrimitiveOrWrapper(Class type) {
      if (type == null) {
         return false;
      } else {
         return type.isPrimitive() || isPrimitiveWrapper(type);
      }
   }

   public static boolean isPrimitiveWrapper(Class type) {
      return wrapperPrimitiveMap.containsKey(type);
   }

   public static boolean isPublic(Class cls) {
      return Modifier.isPublic(cls.getModifiers());
   }

   public static Class[] primitivesToWrappers(Class... classes) {
      if (classes == null) {
         return null;
      } else if (classes.length == 0) {
         return classes;
      } else {
         Class<?>[] convertedClasses = new Class[classes.length];
         Arrays.setAll(convertedClasses, (i) -> primitiveToWrapper(classes[i]));
         return convertedClasses;
      }
   }

   public static Class primitiveToWrapper(Class cls) {
      Class<?> convertedClass = cls;
      if (cls != null && cls.isPrimitive()) {
         convertedClass = (Class)primitiveWrapperMap.get(cls);
      }

      return convertedClass;
   }

   private static String toCanonicalName(String className) {
      String canonicalName = StringUtils.deleteWhitespace(className);
      Objects.requireNonNull(canonicalName, "className");
      if (canonicalName.endsWith("[]")) {
         StringBuilder classNameBuffer = new StringBuilder();

         while(canonicalName.endsWith("[]")) {
            canonicalName = canonicalName.substring(0, canonicalName.length() - 2);
            classNameBuffer.append("[");
         }

         String abbreviation = (String)abbreviationMap.get(canonicalName);
         if (abbreviation != null) {
            classNameBuffer.append(abbreviation);
         } else {
            classNameBuffer.append("L").append(canonicalName).append(";");
         }

         canonicalName = classNameBuffer.toString();
      }

      return canonicalName;
   }

   public static Class[] toClass(Object... array) {
      if (array == null) {
         return null;
      } else if (array.length == 0) {
         return ArrayUtils.EMPTY_CLASS_ARRAY;
      } else {
         Class<?>[] classes = new Class[array.length];
         Arrays.setAll(classes, (i) -> array[i] == null ? null : array[i].getClass());
         return classes;
      }
   }

   private static boolean useFull(int runAheadTarget, int source, int originalLength, int desiredLength) {
      return source >= originalLength || runAheadTarget + originalLength - source <= desiredLength;
   }

   public static Class[] wrappersToPrimitives(Class... classes) {
      if (classes == null) {
         return null;
      } else if (classes.length == 0) {
         return classes;
      } else {
         Class<?>[] convertedClasses = new Class[classes.length];
         Arrays.setAll(convertedClasses, (i) -> wrapperToPrimitive(classes[i]));
         return convertedClasses;
      }
   }

   public static Class wrapperToPrimitive(Class cls) {
      return (Class)wrapperPrimitiveMap.get(cls);
   }

   static {
      namePrimitiveMap.put(Boolean.TYPE.getSimpleName(), Boolean.TYPE);
      namePrimitiveMap.put(Byte.TYPE.getSimpleName(), Byte.TYPE);
      namePrimitiveMap.put(Character.TYPE.getSimpleName(), Character.TYPE);
      namePrimitiveMap.put(Double.TYPE.getSimpleName(), Double.TYPE);
      namePrimitiveMap.put(Float.TYPE.getSimpleName(), Float.TYPE);
      namePrimitiveMap.put(Integer.TYPE.getSimpleName(), Integer.TYPE);
      namePrimitiveMap.put(Long.TYPE.getSimpleName(), Long.TYPE);
      namePrimitiveMap.put(Short.TYPE.getSimpleName(), Short.TYPE);
      namePrimitiveMap.put(Void.TYPE.getSimpleName(), Void.TYPE);
      primitiveWrapperMap = new HashMap();
      primitiveWrapperMap.put(Boolean.TYPE, Boolean.class);
      primitiveWrapperMap.put(Byte.TYPE, Byte.class);
      primitiveWrapperMap.put(Character.TYPE, Character.class);
      primitiveWrapperMap.put(Short.TYPE, Short.class);
      primitiveWrapperMap.put(Integer.TYPE, Integer.class);
      primitiveWrapperMap.put(Long.TYPE, Long.class);
      primitiveWrapperMap.put(Double.TYPE, Double.class);
      primitiveWrapperMap.put(Float.TYPE, Float.class);
      primitiveWrapperMap.put(Void.TYPE, Void.TYPE);
      wrapperPrimitiveMap = new HashMap();
      primitiveWrapperMap.forEach((primitiveClass, wrapperClass) -> {
         if (!primitiveClass.equals(wrapperClass)) {
            wrapperPrimitiveMap.put(wrapperClass, primitiveClass);
         }

      });
      Map<String, String> map = new HashMap();
      map.put("int", "I");
      map.put("boolean", "Z");
      map.put("float", "F");
      map.put("long", "J");
      map.put("short", "S");
      map.put("byte", "B");
      map.put("double", "D");
      map.put("char", "C");
      abbreviationMap = Collections.unmodifiableMap(map);
      reverseAbbreviationMap = Collections.unmodifiableMap((Map)map.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey)));
   }

   public static enum Interfaces {
      INCLUDE,
      EXCLUDE;

      // $FF: synthetic method
      private static Interfaces[] $values() {
         return new Interfaces[]{INCLUDE, EXCLUDE};
      }
   }
}
