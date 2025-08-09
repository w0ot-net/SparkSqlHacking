package org.glassfish.hk2.utilities.reflection;

import jakarta.inject.Named;
import jakarta.inject.Qualifier;
import jakarta.inject.Scope;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.glassfish.hk2.utilities.general.GeneralUtilities;
import org.glassfish.hk2.utilities.reflection.internal.MethodWrapperImpl;

public final class ReflectionHelper {
   private static final HashSet ESCAPE_CHARACTERS = new HashSet();
   private static final char[] ILLEGAL_CHARACTERS = new char[]{'{', '}', '[', ']', ':', ';', '=', ',', '\\'};
   private static final HashMap REPLACE_CHARACTERS = new HashMap();
   private static final String EQUALS_STRING = "=";
   private static final String COMMA_STRING = ",";
   private static final String QUOTE_STRING = "\"";

   public static Class getRawClass(Type type) {
      if (type == null) {
         return null;
      } else if (type instanceof GenericArrayType) {
         Type componentType = ((GenericArrayType)type).getGenericComponentType();
         if (!(componentType instanceof ParameterizedType) && !(componentType instanceof Class)) {
            return null;
         } else {
            Class<?> rawComponentClass = getRawClass(componentType);
            String forNameName = "[L" + rawComponentClass.getName() + ";";

            try {
               return Class.forName(forNameName);
            } catch (Throwable var5) {
               return null;
            }
         }
      } else if (type instanceof Class) {
         return (Class)type;
      } else {
         if (type instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType)type).getRawType();
            if (rawType instanceof Class) {
               return (Class)rawType;
            }
         }

         return null;
      }
   }

   public static Type resolveField(Class topclass, Field field) {
      return resolveMember(topclass, field.getGenericType(), field.getDeclaringClass());
   }

   public static Type resolveMember(Class topclass, Type lookingForType, Class declaringClass) {
      Map<String, Type> typeArguments = typesFromSubClassToDeclaringClass(topclass, declaringClass);
      if (typeArguments == null) {
         return lookingForType;
      } else if (lookingForType instanceof ParameterizedType) {
         return fixTypeVariables((ParameterizedType)lookingForType, typeArguments);
      } else if (lookingForType instanceof GenericArrayType) {
         return fixGenericArrayTypeVariables((GenericArrayType)lookingForType, typeArguments);
      } else if (!(lookingForType instanceof TypeVariable)) {
         return lookingForType;
      } else {
         TypeVariable<?> tv = (TypeVariable)lookingForType;
         String typeVariableName = tv.getName();
         Type retVal = (Type)typeArguments.get(typeVariableName);
         if (retVal == null) {
            return lookingForType;
         } else if (retVal instanceof Class) {
            return retVal;
         } else if (retVal instanceof ParameterizedType) {
            return fixTypeVariables((ParameterizedType)retVal, typeArguments);
         } else {
            return retVal instanceof GenericArrayType ? fixGenericArrayTypeVariables((GenericArrayType)retVal, typeArguments) : retVal;
         }
      }
   }

   public static Type resolveKnownType(TypeVariable userType, ParameterizedType knownType, Class knownDeclaringClass) {
      TypeVariable<?>[] knownTypeVariables = knownDeclaringClass.getTypeParameters();

      for(int lcv = 0; lcv < knownTypeVariables.length; ++lcv) {
         TypeVariable<?> knownTypeVariable = knownTypeVariables[lcv];
         if (GeneralUtilities.safeEquals(knownTypeVariable.getName(), userType.getName())) {
            return knownType.getActualTypeArguments()[lcv];
         }
      }

      return null;
   }

   private static Map typesFromSubClassToDeclaringClass(Class topClass, Class declaringClass) {
      if (topClass.equals(declaringClass)) {
         return null;
      } else {
         Type superType = topClass.getGenericSuperclass();
         Class<?> superClass = getRawClass(superType);

         while(superType != null && superClass != null) {
            if (!(superType instanceof ParameterizedType)) {
               if (superClass.equals(declaringClass)) {
                  return null;
               }

               superType = superClass.getGenericSuperclass();
               superClass = getRawClass(superType);
            } else {
               ParameterizedType superPT = (ParameterizedType)superType;
               Map<String, Type> typeArguments = getTypeArguments(superClass, superPT);
               if (superClass.equals(declaringClass)) {
                  return typeArguments;
               }

               superType = superClass.getGenericSuperclass();
               superClass = getRawClass(superType);
               if (superType instanceof ParameterizedType) {
                  superType = fixTypeVariables((ParameterizedType)superType, typeArguments);
               }
            }
         }

         String var10002 = topClass.getName();
         throw new AssertionError(var10002 + " is not the same as or a subclass of " + declaringClass.getName());
      }
   }

   public static Type getFirstTypeArgument(Type type) {
      if (type instanceof Class) {
         return Object.class;
      } else if (!(type instanceof ParameterizedType)) {
         return Object.class;
      } else {
         ParameterizedType pt = (ParameterizedType)type;
         Type[] arguments = pt.getActualTypeArguments();
         return (Type)(arguments.length <= 0 ? Object.class : arguments[0]);
      }
   }

   private static String getNamedName(Named named, Class implClass) {
      String name = named.value();
      if (name != null && !name.equals("")) {
         return name;
      } else {
         String cn = implClass.getName();
         int index = cn.lastIndexOf(".");
         return index < 0 ? cn : cn.substring(index + 1);
      }
   }

   public static String getName(Class implClass) {
      Named named = (Named)implClass.getAnnotation(Named.class);
      String namedName = named != null ? getNamedName(named, implClass) : null;
      return namedName != null ? namedName : null;
   }

   private static void addAllGenericInterfaces(Class rawClass, Type type, Set closures) {
      Map<String, Type> typeArgumentsMap = null;

      for(Type currentType : rawClass.getGenericInterfaces()) {
         if (type instanceof ParameterizedType && currentType instanceof ParameterizedType) {
            if (typeArgumentsMap == null) {
               typeArgumentsMap = getTypeArguments(rawClass, (ParameterizedType)type);
            }

            currentType = fixTypeVariables((ParameterizedType)currentType, typeArgumentsMap);
         }

         closures.add(currentType);
         rawClass = getRawClass(currentType);
         if (rawClass != null) {
            addAllGenericInterfaces(rawClass, currentType, closures);
         }
      }

   }

   private static Type fixTypeVariables(ParameterizedType type, Map typeArgumentsMap) {
      Type[] newTypeArguments = getNewTypeArguments(type, typeArgumentsMap);
      return (Type)(newTypeArguments != null ? new ParameterizedTypeImpl(type.getRawType(), newTypeArguments) : type);
   }

   private static Type fixGenericArrayTypeVariables(GenericArrayType type, Map typeArgumentsMap) {
      Type newTypeArgument = getNewTypeArrayArguments(type, typeArgumentsMap);
      if (newTypeArgument != null) {
         if (newTypeArgument instanceof Class) {
            return getArrayOfType((Class)newTypeArgument);
         } else {
            if (newTypeArgument instanceof ParameterizedType) {
               ParameterizedType pt = (ParameterizedType)newTypeArgument;
               if (pt.getRawType() instanceof Class) {
                  return getArrayOfType((Class)pt.getRawType());
               }
            }

            return new GenericArrayTypeImpl(newTypeArgument);
         }
      } else {
         return type;
      }
   }

   private static Class getArrayOfType(Class type) {
      return Array.newInstance(type, 0).getClass();
   }

   private static Type[] getNewTypeArguments(ParameterizedType type, Map typeArgumentsMap) {
      Type[] typeArguments = type.getActualTypeArguments();
      Type[] newTypeArguments = new Type[typeArguments.length];
      boolean newArgsNeeded = false;
      int i = 0;

      for(Type argType : typeArguments) {
         if (argType instanceof TypeVariable) {
            newTypeArguments[i] = (Type)typeArgumentsMap.get(((TypeVariable)argType).getName());
            newArgsNeeded = true;
         } else if (argType instanceof ParameterizedType) {
            ParameterizedType original = (ParameterizedType)argType;
            Type[] internalTypeArgs = getNewTypeArguments(original, typeArgumentsMap);
            if (internalTypeArgs != null) {
               newTypeArguments[i] = new ParameterizedTypeImpl(original.getRawType(), internalTypeArgs);
               newArgsNeeded = true;
            } else {
               newTypeArguments[i] = argType;
            }
         } else if (argType instanceof GenericArrayType) {
            GenericArrayType gat = (GenericArrayType)argType;
            Type internalTypeArg = getNewTypeArrayArguments(gat, typeArgumentsMap);
            if (internalTypeArg != null) {
               if (internalTypeArg instanceof Class) {
                  newTypeArguments[i] = getArrayOfType((Class)internalTypeArg);
                  newArgsNeeded = true;
               } else if (internalTypeArg instanceof ParameterizedType && ((ParameterizedType)internalTypeArg).getRawType() instanceof Class) {
                  ParameterizedType pt = (ParameterizedType)internalTypeArg;
                  newTypeArguments[i] = getArrayOfType((Class)pt.getRawType());
                  newArgsNeeded = true;
               } else {
                  newTypeArguments[i] = new GenericArrayTypeImpl(internalTypeArg);
                  newArgsNeeded = true;
               }
            } else {
               newTypeArguments[i] = argType;
            }
         } else {
            newTypeArguments[i] = argType;
         }

         ++i;
      }

      return newArgsNeeded ? newTypeArguments : null;
   }

   private static Type getNewTypeArrayArguments(GenericArrayType gat, Map typeArgumentsMap) {
      Type typeArgument = gat.getGenericComponentType();
      if (typeArgument instanceof TypeVariable) {
         return (Type)typeArgumentsMap.get(((TypeVariable)typeArgument).getName());
      } else if (typeArgument instanceof ParameterizedType) {
         ParameterizedType original = (ParameterizedType)typeArgument;
         Type[] internalTypeArgs = getNewTypeArguments(original, typeArgumentsMap);
         return (Type)(internalTypeArgs != null ? new ParameterizedTypeImpl(original.getRawType(), internalTypeArgs) : original);
      } else if (typeArgument instanceof GenericArrayType) {
         GenericArrayType original = (GenericArrayType)typeArgument;
         Type internalTypeArg = getNewTypeArrayArguments(original, typeArgumentsMap);
         if (internalTypeArg != null) {
            if (internalTypeArg instanceof Class) {
               return getArrayOfType((Class)internalTypeArg);
            } else {
               if (internalTypeArg instanceof ParameterizedType) {
                  ParameterizedType pt = (ParameterizedType)internalTypeArg;
                  if (pt.getRawType() instanceof Class) {
                     return getArrayOfType((Class)pt.getRawType());
                  }
               }

               return new GenericArrayTypeImpl(internalTypeArg);
            }
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   private static Map getTypeArguments(Class rawClass, ParameterizedType type) {
      Map<String, Type> typeMap = new HashMap();
      Type[] typeArguments = type.getActualTypeArguments();
      int i = 0;

      for(TypeVariable typeVariable : rawClass.getTypeParameters()) {
         typeMap.put(typeVariable.getName(), typeArguments[i++]);
      }

      return typeMap;
   }

   private static Set getTypeClosure(Type ofType) {
      Set<Type> retVal = new HashSet();
      Class<?> rawClass = getRawClass(ofType);
      if (rawClass != null) {
         Map<String, Type> typeArgumentsMap = null;
         Type currentType = ofType;

         while(currentType != null && rawClass != null) {
            retVal.add(currentType);
            addAllGenericInterfaces(rawClass, currentType, retVal);
            if (typeArgumentsMap == null && currentType instanceof ParameterizedType) {
               typeArgumentsMap = getTypeArguments(rawClass, (ParameterizedType)currentType);
            }

            currentType = rawClass.getGenericSuperclass();
            if (currentType != null) {
               rawClass = getRawClass(currentType);
               if (typeArgumentsMap != null && currentType instanceof ParameterizedType) {
                  currentType = fixTypeVariables((ParameterizedType)currentType, typeArgumentsMap);
               }
            }
         }
      }

      return retVal;
   }

   public static Set getTypeClosure(Type ofType, Set contracts) {
      Set<Type> closure = getTypeClosure(ofType);
      HashSet<Type> retVal = new HashSet();

      for(Type t : closure) {
         Class<?> rawClass = getRawClass(t);
         if (rawClass != null && contracts.contains(rawClass.getName())) {
            retVal.add(t);
         }
      }

      return retVal;
   }

   public static Set getAdvertisedTypesFromClass(Type type, Class markerAnnotation) {
      Set<Type> retVal = new LinkedHashSet();
      if (type == null) {
         return retVal;
      } else {
         retVal.add(type);
         Class<?> originalRawClass = getRawClass(type);
         if (originalRawClass == null) {
            return retVal;
         } else {
            Class<?> rawClass;
            for(Type genericSuperclass = originalRawClass.getGenericSuperclass(); genericSuperclass != null; genericSuperclass = rawClass.getGenericSuperclass()) {
               rawClass = getRawClass(genericSuperclass);
               if (rawClass == null) {
                  break;
               }

               if (rawClass.isAnnotationPresent(markerAnnotation)) {
                  retVal.add(genericSuperclass);
               }
            }

            for(Set<Class<?>> alreadyHandled = new HashSet(); originalRawClass != null; originalRawClass = originalRawClass.getSuperclass()) {
               getAllContractsFromInterfaces(originalRawClass, markerAnnotation, retVal, alreadyHandled);
            }

            return retVal;
         }
      }
   }

   private static void getAllContractsFromInterfaces(Class clazzOrInterface, Class markerAnnotation, Set addToMe, Set alreadyHandled) {
      Type[] interfacesAsType = clazzOrInterface.getGenericInterfaces();

      for(Type interfaceAsType : interfacesAsType) {
         Class<?> interfaceAsClass = getRawClass(interfaceAsType);
         if (interfaceAsClass != null && !alreadyHandled.contains(interfaceAsClass)) {
            alreadyHandled.add(interfaceAsClass);
            if (interfaceAsClass.isAnnotationPresent(markerAnnotation)) {
               addToMe.add(interfaceAsType);
            }

            getAllContractsFromInterfaces(interfaceAsClass, markerAnnotation, addToMe, alreadyHandled);
         }
      }

   }

   public static Set getAdvertisedTypesFromObject(Object t, Class markerAnnotation) {
      return t == null ? Collections.emptySet() : getAdvertisedTypesFromClass(t.getClass(), markerAnnotation);
   }

   public static Set getContractsFromClass(Class clazz, Class markerAnnotation) {
      Set<String> contractSet = new LinkedHashSet();
      if (clazz == null) {
         return contractSet;
      } else {
         contractSet.add(clazz.getName());

         for(Class extendsClasses = clazz; extendsClasses != null; extendsClasses = extendsClasses.getSuperclass()) {
            if (extendsClasses.isAnnotationPresent(markerAnnotation)) {
               contractSet.add(extendsClasses.getName());
            }

            recursiveAddContractsFromInterfacesToMap(extendsClasses.getInterfaces(), contractSet, markerAnnotation);
         }

         return contractSet;
      }
   }

   private static void recursiveAddContractsFromInterfacesToMap(Class[] interfaces, Set contractSet, Class markerAnnotation) {
      for(Class anInterface : interfaces) {
         if (anInterface.isAnnotationPresent(markerAnnotation)) {
            contractSet.add(anInterface.getName());
         }

         recursiveAddContractsFromInterfacesToMap(anInterface.getInterfaces(), contractSet, markerAnnotation);
      }

   }

   public static Annotation getScopeAnnotationFromObject(Object t) {
      if (t == null) {
         throw new IllegalArgumentException();
      } else {
         return getScopeAnnotationFromClass(t.getClass());
      }
   }

   public static Annotation getScopeAnnotationFromClass(Class clazz) {
      if (clazz == null) {
         throw new IllegalArgumentException();
      } else {
         for(Annotation annotation : clazz.getAnnotations()) {
            Class<? extends Annotation> annoClass = annotation.annotationType();
            if (annoClass.isAnnotationPresent(Scope.class)) {
               return annotation;
            }
         }

         return null;
      }
   }

   public static Annotation getScopeFromObject(Object t, Annotation annoDefault) {
      return t == null ? annoDefault : getScopeFromClass(t.getClass(), annoDefault);
   }

   public static Annotation getScopeFromClass(Class clazz, Annotation annoDefault) {
      if (clazz == null) {
         return annoDefault;
      } else {
         for(Annotation annotation : clazz.getAnnotations()) {
            Class<? extends Annotation> annoClass = annotation.annotationType();
            if (annoClass.isAnnotationPresent(Scope.class)) {
               return annotation;
            }
         }

         return annoDefault;
      }
   }

   public static boolean isAnnotationAQualifier(Annotation anno) {
      Class<? extends Annotation> annoType = anno.annotationType();
      return annoType.isAnnotationPresent(Qualifier.class);
   }

   public static Set getQualifiersFromObject(Object t) {
      return t == null ? Collections.emptySet() : getQualifierAnnotations(t.getClass());
   }

   public static Set getQualifiersFromClass(Class clazz) {
      Set<String> retVal = new LinkedHashSet();
      if (clazz == null) {
         return retVal;
      } else {
         for(Annotation annotation : clazz.getAnnotations()) {
            if (isAnnotationAQualifier(annotation)) {
               retVal.add(annotation.annotationType().getName());
            }
         }

         while(clazz != null) {
            for(Class iFace : clazz.getInterfaces()) {
               for(Annotation annotation : iFace.getAnnotations()) {
                  if (isAnnotationAQualifier(annotation)) {
                     retVal.add(annotation.annotationType().getName());
                  }
               }
            }

            clazz = clazz.getSuperclass();
         }

         return retVal;
      }
   }

   private static Set internalGetQualifierAnnotations(AnnotatedElement annotatedGuy) {
      Set<Annotation> retVal = new LinkedHashSet();
      if (annotatedGuy == null) {
         return retVal;
      } else {
         for(Annotation annotation : annotatedGuy.getAnnotations()) {
            if (isAnnotationAQualifier(annotation)) {
               if (annotatedGuy instanceof Field && Named.class.equals(annotation.annotationType())) {
                  Named n = (Named)annotation;
                  if (n.value() == null || "".equals(n.value())) {
                     continue;
                  }
               }

               retVal.add(annotation);
            }
         }

         if (!(annotatedGuy instanceof Class)) {
            return retVal;
         } else {
            for(Class<?> clazz = (Class)annotatedGuy; clazz != null; clazz = clazz.getSuperclass()) {
               for(Class iFace : clazz.getInterfaces()) {
                  for(Annotation annotation : iFace.getAnnotations()) {
                     if (isAnnotationAQualifier(annotation)) {
                        retVal.add(annotation);
                     }
                  }
               }
            }

            return retVal;
         }
      }
   }

   public static Set getQualifierAnnotations(final AnnotatedElement annotatedGuy) {
      Set<Annotation> retVal = (Set)AccessController.doPrivileged(new PrivilegedAction() {
         public Set run() {
            return ReflectionHelper.internalGetQualifierAnnotations(annotatedGuy);
         }
      });
      return retVal;
   }

   public static String writeSet(Set set) {
      return writeSet(set, (Object)null);
   }

   public static String writeSet(Set set, Object excludeMe) {
      if (set == null) {
         return "{}";
      } else {
         StringBuffer sb = new StringBuffer("{");
         boolean first = true;

         for(Object writeMe : set) {
            if (excludeMe == null || !excludeMe.equals(writeMe)) {
               if (first) {
                  first = false;
                  sb.append(escapeString(writeMe.toString()));
               } else {
                  sb.append("," + escapeString(writeMe.toString()));
               }
            }
         }

         sb.append("}");
         return sb.toString();
      }
   }

   public static void readSet(String line, Collection addToMe) throws IOException {
      char[] asChars = new char[line.length()];
      line.getChars(0, line.length(), asChars, 0);
      internalReadSet(asChars, 0, addToMe);
   }

   private static int internalReadSet(char[] asChars, int startIndex, Collection addToMe) throws IOException {
      int dot = startIndex;

      int startOfSet;
      for(startOfSet = -1; dot < asChars.length; ++dot) {
         if (asChars[dot] == '{') {
            startOfSet = dot++;
            break;
         }
      }

      if (startOfSet == -1) {
         String var9 = new String(asChars);
         throw new IOException("Unknown set format, no initial { character : " + var9);
      } else {
         StringBuffer elementBuffer = new StringBuffer();

         int endOfSet;
         for(endOfSet = -1; dot < asChars.length; ++dot) {
            char dotChar = asChars[dot];
            if (dotChar == '}') {
               addToMe.add(elementBuffer.toString());
               endOfSet = dot;
               break;
            }

            if (dotChar == ',') {
               addToMe.add(elementBuffer.toString());
               elementBuffer = new StringBuffer();
            } else if (dotChar != '\\') {
               elementBuffer.append(dotChar);
            } else {
               if (dot + 1 >= asChars.length) {
                  break;
               }

               ++dot;
               dotChar = asChars[dot];
               if (dotChar == 'n') {
                  elementBuffer.append('\n');
               } else if (dotChar == 'r') {
                  elementBuffer.append('\r');
               } else {
                  elementBuffer.append(dotChar);
               }
            }
         }

         if (endOfSet == -1) {
            String var10002 = new String(asChars);
            throw new IOException("Unknown set format, no ending } character : " + var10002);
         } else {
            return dot - startIndex;
         }
      }
   }

   private static int readKeyStringListLine(char[] asChars, int startIndex, Map addToMe) throws IOException {
      int dot = startIndex;

      int equalsIndex;
      for(equalsIndex = -1; dot < asChars.length; ++dot) {
         char dotChar = asChars[dot];
         if (dotChar == '=') {
            equalsIndex = dot;
            break;
         }
      }

      if (equalsIndex < 0) {
         String var10002 = new String(asChars);
         throw new IOException("Unknown key-string list format, no equals: " + var10002);
      } else {
         String key = new String(asChars, startIndex, equalsIndex - startIndex);
         ++dot;
         if (dot >= asChars.length) {
            throw new IOException("Found a key with no value, " + key + " in line " + new String(asChars));
         } else {
            LinkedList<String> listValues = new LinkedList();
            int addOn = internalReadSet(asChars, dot, listValues);
            if (!listValues.isEmpty()) {
               addToMe.put(key, listValues);
            }

            dot += addOn + 1;
            if (dot < asChars.length) {
               char skipComma = asChars[dot];
               if (skipComma == ',') {
                  ++dot;
               }
            }

            return dot - startIndex;
         }
      }
   }

   public static void readMetadataMap(String line, Map addToMe) throws IOException {
      char[] asChars = new char[line.length()];
      line.getChars(0, line.length(), asChars, 0);

      int addMe;
      for(int dot = 0; dot < asChars.length; dot += addMe) {
         addMe = readKeyStringListLine(asChars, dot, addToMe);
      }

   }

   private static String escapeString(String escapeMe) {
      char[] asChars = new char[escapeMe.length()];
      escapeMe.getChars(0, escapeMe.length(), asChars, 0);
      StringBuffer sb = new StringBuffer();

      for(int lcv = 0; lcv < asChars.length; ++lcv) {
         char candidateChar = asChars[lcv];
         if (ESCAPE_CHARACTERS.contains(candidateChar)) {
            sb.append('\\');
            sb.append(candidateChar);
         } else if (REPLACE_CHARACTERS.containsKey(candidateChar)) {
            char replaceWithMe = (Character)REPLACE_CHARACTERS.get(candidateChar);
            sb.append('\\');
            sb.append(replaceWithMe);
         } else {
            sb.append(candidateChar);
         }
      }

      return sb.toString();
   }

   private static String writeList(List list) {
      StringBuffer sb = new StringBuffer("{");
      boolean first = true;

      for(String writeMe : list) {
         if (first) {
            first = false;
            sb.append(escapeString(writeMe));
         } else {
            sb.append("," + escapeString(writeMe));
         }
      }

      sb.append("}");
      return sb.toString();
   }

   public static String writeMetadata(Map metadata) {
      StringBuffer sb = new StringBuffer();
      boolean first = true;

      for(Map.Entry entry : metadata.entrySet()) {
         if (first) {
            first = false;
            sb.append((String)entry.getKey() + "=");
         } else {
            sb.append("," + (String)entry.getKey() + "=");
         }

         sb.append(writeList((List)entry.getValue()));
      }

      return sb.toString();
   }

   public static void addMetadata(Map metadatas, String key, String value) {
      if (key != null && value != null) {
         if (key.indexOf(61) >= 0) {
            throw new IllegalArgumentException("The key field may not have an = in it:" + key);
         } else {
            List<String> inner = (List)metadatas.get(key);
            if (inner == null) {
               inner = new LinkedList();
               metadatas.put(key, inner);
            }

            inner.add(value);
         }
      }
   }

   public static boolean removeMetadata(Map metadatas, String key, String value) {
      if (key != null && value != null) {
         List<String> inner = (List)metadatas.get(key);
         if (inner == null) {
            return false;
         } else {
            boolean retVal = inner.remove(value);
            if (inner.size() <= 0) {
               metadatas.remove(key);
            }

            return retVal;
         }
      } else {
         return false;
      }
   }

   public static boolean removeAllMetadata(Map metadatas, String key) {
      List<String> values = (List)metadatas.remove(key);
      return values != null && values.size() > 0;
   }

   public static Map deepCopyMetadata(Map copyMe) {
      if (copyMe == null) {
         return null;
      } else {
         Map<String, List<String>> retVal = new LinkedHashMap();

         for(Map.Entry entry : copyMe.entrySet()) {
            String key = (String)entry.getKey();
            if (key.indexOf(61) >= 0) {
               throw new IllegalArgumentException("The key field may not have an = in it:" + key);
            }

            List<String> values = (List)entry.getValue();
            LinkedList<String> valuesCopy = new LinkedList();

            for(String value : values) {
               valuesCopy.add(value);
            }

            retVal.put(key, valuesCopy);
         }

         return retVal;
      }
   }

   public static void setField(Field field, Object instance, Object value) throws Throwable {
      setAccessible(field);

      try {
         field.set(instance, value);
      } catch (IllegalArgumentException e) {
         Logger.getLogger().debug(field.getDeclaringClass().getName(), field.getName(), e);
         throw e;
      } catch (IllegalAccessException e) {
         Logger.getLogger().debug(field.getDeclaringClass().getName(), field.getName(), e);
         throw e;
      }
   }

   public static Object invoke(Object o, Method m, Object[] args, boolean neutralCCL) throws Throwable {
      if (isStatic(m)) {
         o = null;
      }

      setAccessible(m);
      ClassLoader currentCCL = null;
      if (neutralCCL) {
         currentCCL = getCurrentContextClassLoader();
      }

      Object var5;
      try {
         var5 = m.invoke(o, args);
      } catch (InvocationTargetException ite) {
         Throwable targetException = ite.getTargetException();
         Logger.getLogger().debug(m.getDeclaringClass().getName(), m.getName(), targetException);
         throw targetException;
      } catch (Throwable th) {
         Logger.getLogger().debug(m.getDeclaringClass().getName(), m.getName(), th);
         throw th;
      } finally {
         if (neutralCCL) {
            setContextClassLoader(Thread.currentThread(), currentCCL);
         }

      }

      return var5;
   }

   public static boolean isStatic(Member member) {
      int modifiers = member.getModifiers();
      return (modifiers & 8) != 0;
   }

   private static void setContextClassLoader(final Thread t, final ClassLoader l) {
      AccessController.doPrivileged(new PrivilegedAction() {
         public Object run() {
            t.setContextClassLoader(l);
            return null;
         }
      });
   }

   private static void setAccessible(final AccessibleObject ao) {
      if (!ao.isAccessible()) {
         AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
               ao.setAccessible(true);
               return null;
            }
         });
      }
   }

   public static Object makeMe(Constructor c, Object[] args, boolean neutralCCL) throws Throwable {
      ClassLoader currentCCL = null;
      if (neutralCCL) {
         currentCCL = getCurrentContextClassLoader();
      }

      Object var4;
      try {
         var4 = c.newInstance(args);
      } catch (InvocationTargetException ite) {
         Throwable targetException = ite.getTargetException();
         Logger.getLogger().debug(c.getDeclaringClass().getName(), c.getName(), targetException);
         throw targetException;
      } catch (Throwable th) {
         Logger.getLogger().debug(c.getDeclaringClass().getName(), c.getName(), th);
         throw th;
      } finally {
         if (neutralCCL) {
            setContextClassLoader(Thread.currentThread(), currentCCL);
         }

      }

      return var4;
   }

   public static void parseServiceMetadataString(String metadataField, Map metadata) {
      StringBuffer sb = new StringBuffer(metadataField);
      int dot = 0;
      int nextEquals = sb.indexOf("=", dot);

      while(nextEquals > 0) {
         String key = sb.substring(dot, nextEquals);
         dot = nextEquals + 1;
         String value = null;
         int commaPlace;
         if (sb.charAt(dot) == '"') {
            ++dot;
            int nextQuote = sb.indexOf("\"", dot);
            if (nextQuote < 0) {
               throw new IllegalStateException("Badly formed metadata \"" + metadataField + "\" for key " + key + " has a leading quote but no trailing quote");
            }

            value = sb.substring(dot, nextQuote);
            dot = nextQuote + 1;
            commaPlace = sb.indexOf(",", dot);
         } else {
            commaPlace = sb.indexOf(",", dot);
            if (commaPlace < 0) {
               value = sb.substring(dot);
            } else {
               value = sb.substring(dot, commaPlace);
            }
         }

         List<String> addToMe = (List)metadata.get(key);
         if (addToMe == null) {
            addToMe = new LinkedList();
            metadata.put(key, addToMe);
         }

         addToMe.add(value);
         if (commaPlace >= 0) {
            dot = commaPlace + 1;
            nextEquals = sb.indexOf("=", dot);
         } else {
            nextEquals = -1;
         }
      }

   }

   public static String getNameFromAllQualifiers(Set qualifiers, AnnotatedElement parent) throws IllegalStateException {
      for(Annotation qualifier : qualifiers) {
         if (Named.class.equals(qualifier.annotationType())) {
            Named named = (Named)qualifier;
            if (named.value() != null && !named.value().equals("")) {
               return named.value();
            }

            if (parent != null) {
               if (parent instanceof Class) {
                  return Pretty.clazz((Class)parent);
               }

               if (parent instanceof Field) {
                  return ((Field)parent).getName();
               }
            }

            throw new IllegalStateException("@Named must have a value for " + parent);
         }
      }

      return null;
   }

   private static ClassLoader getCurrentContextClassLoader() {
      return (ClassLoader)AccessController.doPrivileged(new PrivilegedAction() {
         public ClassLoader run() {
            return Thread.currentThread().getContextClassLoader();
         }
      });
   }

   public static boolean annotationContainsAll(final Set candidateAnnotations, final Set requiredAnnotations) {
      return (Boolean)AccessController.doPrivileged(new PrivilegedAction() {
         public Boolean run() {
            return candidateAnnotations.containsAll(requiredAnnotations);
         }
      });
   }

   public static Class translatePrimitiveType(Class type) {
      Class<?> translation = (Class)Constants.PRIMITIVE_MAP.get(type);
      return translation == null ? type : translation;
   }

   public static boolean isPrivate(Member member) {
      int modifiers = member.getModifiers();
      return (modifiers & 2) != 0;
   }

   public static Set getAllTypes(Type t) {
      LinkedHashSet<Type> retVal = new LinkedHashSet();
      retVal.add(t);
      Class<?> rawClass = getRawClass(t);
      if (rawClass == null) {
         return retVal;
      } else {
         Class<?> rawSuperclass;
         for(Type genericSuperclass = rawClass.getGenericSuperclass(); genericSuperclass != null; genericSuperclass = rawSuperclass.getGenericSuperclass()) {
            rawSuperclass = getRawClass(genericSuperclass);
            if (rawSuperclass == null) {
               break;
            }

            retVal.add(genericSuperclass);
         }

         while(rawClass != null) {
            for(Type iface : rawClass.getGenericInterfaces()) {
               addAllInterfaceContracts(iface, retVal);
            }

            rawClass = rawClass.getSuperclass();
         }

         LinkedHashSet<Type> altRetVal = new LinkedHashSet();
         HashMap<Class<?>, ParameterizedType> class2TypeMap = new HashMap();

         for(Type foundType : retVal) {
            if (!(foundType instanceof ParameterizedType)) {
               altRetVal.add(foundType);
            } else {
               ParameterizedType originalPt = (ParameterizedType)foundType;
               Class<?> rawType = getRawClass(foundType);
               class2TypeMap.put(rawType, originalPt);
               if (isFilledIn(originalPt)) {
                  altRetVal.add(foundType);
               } else {
                  ParameterizedType pti = fillInPT(originalPt, class2TypeMap);
                  altRetVal.add(pti);
                  class2TypeMap.put(rawType, pti);
               }
            }
         }

         return altRetVal;
      }
   }

   private static ParameterizedType fillInPT(ParameterizedType pt, HashMap class2TypeMap) {
      if (isFilledIn(pt)) {
         return pt;
      } else {
         Type[] newActualArguments = new Type[pt.getActualTypeArguments().length];

         for(int outerIndex = 0; outerIndex < newActualArguments.length; ++outerIndex) {
            Type fillMeIn = pt.getActualTypeArguments()[outerIndex];
            newActualArguments[outerIndex] = fillMeIn;
            if (fillMeIn instanceof ParameterizedType) {
               newActualArguments[outerIndex] = fillInPT((ParameterizedType)fillMeIn, class2TypeMap);
            } else if (fillMeIn instanceof TypeVariable) {
               TypeVariable<Class<?>> tv = (TypeVariable)fillMeIn;
               Class<?> genericDeclaration = (Class)tv.getGenericDeclaration();
               boolean found = false;
               int count = -1;

               for(Type parentVariable : genericDeclaration.getTypeParameters()) {
                  ++count;
                  if (parentVariable.equals(tv)) {
                     found = true;
                     break;
                  }
               }

               if (found) {
                  ParameterizedType parentPType = (ParameterizedType)class2TypeMap.get(genericDeclaration);
                  if (parentPType != null) {
                     newActualArguments[outerIndex] = parentPType.getActualTypeArguments()[count];
                  }
               }
            }
         }

         ParameterizedTypeImpl pti = new ParameterizedTypeImpl(getRawClass(pt), newActualArguments);
         return pti;
      }
   }

   private static boolean isFilledIn(ParameterizedType pt, HashSet recursionKiller) {
      if (recursionKiller.contains(pt)) {
         return false;
      } else {
         recursionKiller.add(pt);

         for(Type t : pt.getActualTypeArguments()) {
            if (t instanceof TypeVariable) {
               return false;
            }

            if (t instanceof WildcardType) {
               return false;
            }

            if (t instanceof ParameterizedType) {
               return isFilledIn((ParameterizedType)t, recursionKiller);
            }
         }

         return true;
      }
   }

   private static boolean isFilledIn(ParameterizedType pt) {
      return isFilledIn(pt, new HashSet());
   }

   private static void addAllInterfaceContracts(Type interfaceType, LinkedHashSet addToMe) {
      Class<?> interfaceClass = getRawClass(interfaceType);
      if (interfaceClass != null) {
         if (!addToMe.contains(interfaceType)) {
            addToMe.add(interfaceType);

            for(Type extendedInterfaces : interfaceClass.getGenericInterfaces()) {
               addAllInterfaceContracts(extendedInterfaces, addToMe);
            }

         }
      }
   }

   public static MethodWrapper createMethodWrapper(Method wrapMe) {
      return new MethodWrapperImpl(wrapMe);
   }

   public static Object cast(Object o) {
      return o;
   }

   static {
      for(char illegal : ILLEGAL_CHARACTERS) {
         ESCAPE_CHARACTERS.add(illegal);
      }

      REPLACE_CHARACTERS.put('\n', 'n');
      REPLACE_CHARACTERS.put('\r', 'r');
   }
}
