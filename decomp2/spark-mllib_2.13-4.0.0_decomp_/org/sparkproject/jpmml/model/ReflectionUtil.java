package org.sparkproject.jpmml.model;

import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.sparkproject.dmg.pmml.PMMLAttributes;
import org.sparkproject.dmg.pmml.PMMLElements;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.adapters.FieldNameAdapter;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.w3c.dom.Element;
import org.xml.sax.Locator;

public class ReflectionUtil {
   private static final ConcurrentMap classFields = new ConcurrentHashMap();
   private static final ConcurrentMap classGetterMethods = new ConcurrentHashMap();
   private static final ConcurrentMap classSetterMethods = new ConcurrentHashMap();
   private static final Set primitiveWrapperClasses = new HashSet(Arrays.asList(Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class, Boolean.class, Character.class));

   private ReflectionUtil() {
   }

   public static boolean isFieldName(Field field) {
      XmlJavaTypeAdapter xmlJavaTypeAdapter = (XmlJavaTypeAdapter)field.getAnnotation(XmlJavaTypeAdapter.class);
      return xmlJavaTypeAdapter != null ? Objects.equals(FieldNameAdapter.class, xmlJavaTypeAdapter.value()) : false;
   }

   public static int hashCode(Object object) {
      if (object instanceof Element) {
         return hashCode((Element)object);
      } else {
         return object instanceof PMMLObject ? hashCode((PMMLObject)object) : Objects.hashCode(object);
      }
   }

   public static int hashCode(Element element) {
      int result = 0;
      result += 31 * result + Objects.hashCode(element.getNamespaceURI());
      result += 31 * result + Objects.hashCode(element.getLocalName());
      result += 31 * result + Objects.hashCode(element.getTextContent());
      return result;
   }

   public static int hashCode(PMMLObject object) {
      int result = 0;
      Map<Field, Method> getterMethods = getGetterMethods(object.getClass());

      for(Map.Entry entry : getterMethods.entrySet()) {
         Field field = (Field)entry.getKey();
         Method getterMethod = (Method)entry.getValue();
         Class<?> fieldType = field.getType();
         if (!Objects.equals(Locator.class, fieldType)) {
            Object value;
            if (Objects.equals(List.class, fieldType)) {
               value = getFieldValue(field, object);
            } else {
               value = getGetterMethodValue(getterMethod, object);
            }

            value = standardizeValue(value);
            if (value instanceof List) {
               List<?> values = (List)value;
               int i = 0;

               for(int max = values.size(); i < max; ++i) {
                  result += 31 * result + hashCode(values.get(i));
               }
            } else {
               result += 31 * result + hashCode(value);
            }
         }
      }

      return result;
   }

   public static boolean equals(Object left, Object right) {
      if (left instanceof Element && right instanceof Element) {
         return equals((Element)left, (Element)right);
      } else {
         return left instanceof PMMLObject && right instanceof PMMLObject ? equals((PMMLObject)left, (PMMLObject)right) : Objects.equals(left, right);
      }
   }

   public static boolean equals(Element left, Element right) {
      if (!Objects.equals(left.getClass(), right.getClass())) {
         return false;
      } else {
         return Objects.equals(left.getNamespaceURI(), right.getNamespaceURI()) && Objects.equals(left.getLocalName(), right.getLocalName()) && Objects.equals(left.getTextContent(), right.getTextContent());
      }
   }

   public static boolean equals(PMMLObject left, PMMLObject right) {
      if (!Objects.equals(left.getClass(), right.getClass())) {
         return false;
      } else {
         Map<Field, Method> getterMethods = getGetterMethods(left.getClass());

         for(Map.Entry entry : getterMethods.entrySet()) {
            Field field = (Field)entry.getKey();
            Method getterMethod = (Method)entry.getValue();
            Class<?> fieldType = field.getType();
            if (!Objects.equals(Locator.class, fieldType)) {
               Object leftValue;
               Object rightValue;
               if (Objects.equals(List.class, fieldType)) {
                  leftValue = getFieldValue(field, left);
                  rightValue = getFieldValue(field, right);
               } else {
                  leftValue = getGetterMethodValue(getterMethod, left);
                  rightValue = getGetterMethodValue(getterMethod, right);
               }

               leftValue = standardizeValue(leftValue);
               rightValue = standardizeValue(rightValue);
               boolean equals;
               if (leftValue instanceof List && rightValue instanceof List) {
                  List<?> leftValues = (List)leftValue;
                  List<?> rightValues = (List)rightValue;
                  if (leftValues.size() == rightValues.size()) {
                     equals = true;
                     int i = 0;

                     for(int max = leftValues.size(); i < max; ++i) {
                        equals &= equals(leftValues.get(i), rightValues.get(i));
                        if (!equals) {
                           break;
                        }
                     }
                  } else {
                     equals = false;
                  }
               } else {
                  equals = equals(leftValue, rightValue);
               }

               if (!equals) {
                  return false;
               }
            }
         }

         return true;
      }
   }

   public static void copyState(PMMLObject from, PMMLObject to) {
      Class<?> fromClazz = from.getClass();
      Class<?> toClazz = to.getClass();
      if (!fromClazz.isAssignableFrom(toClazz)) {
         throw new IllegalArgumentException();
      } else {
         for(Field field : getFields(fromClazz)) {
            Object value = getFieldValue(field, from);
            setFieldValue(field, to, value);
         }

      }
   }

   public static Field getField(Class clazz, String name) {
      while(clazz != null) {
         try {
            Field field = clazz.getDeclaredField(name);
            if (!isValidInstanceField(field)) {
               throw new IllegalArgumentException(name);
            }

            return field;
         } catch (NoSuchFieldException var3) {
            clazz = clazz.getSuperclass();
         }
      }

      throw new RuntimeException(new NoSuchFieldException(name));
   }

   public static List getFields(Class clazz) {
      List<Field> result = (List)classFields.get(clazz);
      if (result == null) {
         result = loadFields(clazz);
         classFields.putIfAbsent(clazz, result);
      }

      return result;
   }

   public static Field getSerialVersionUIDField(Class clazz) {
      while(clazz != null) {
         try {
            return clazz.getDeclaredField("serialVersionUID");
         } catch (NoSuchFieldException var2) {
            clazz = clazz.getSuperclass();
         }
      }

      throw new RuntimeException(new NoSuchFieldException("serialVersionUID"));
   }

   public static List getAttributeFields() {
      List<Class<?>> clazzes = Arrays.asList(PMMLAttributes.class, org.sparkproject.dmg.pmml.anomaly_detection.PMMLAttributes.class, org.sparkproject.dmg.pmml.association.PMMLAttributes.class, org.sparkproject.dmg.pmml.baseline.PMMLAttributes.class, org.sparkproject.dmg.pmml.bayesian_network.PMMLAttributes.class, org.sparkproject.dmg.pmml.clustering.PMMLAttributes.class, org.sparkproject.dmg.pmml.gaussian_process.PMMLAttributes.class, org.sparkproject.dmg.pmml.general_regression.PMMLAttributes.class, org.sparkproject.dmg.pmml.mining.PMMLAttributes.class, org.sparkproject.dmg.pmml.naive_bayes.PMMLAttributes.class, org.sparkproject.dmg.pmml.nearest_neighbor.PMMLAttributes.class, org.sparkproject.dmg.pmml.neural_network.PMMLAttributes.class, org.sparkproject.dmg.pmml.regression.PMMLAttributes.class, org.sparkproject.dmg.pmml.rule_set.PMMLAttributes.class, org.sparkproject.dmg.pmml.scorecard.PMMLAttributes.class, org.sparkproject.dmg.pmml.sequence.PMMLAttributes.class, org.sparkproject.dmg.pmml.support_vector_machine.PMMLAttributes.class, org.sparkproject.dmg.pmml.text.PMMLAttributes.class, org.sparkproject.dmg.pmml.time_series.PMMLAttributes.class, org.sparkproject.dmg.pmml.tree.PMMLAttributes.class);
      return getClassConstants(clazzes);
   }

   public static List getElementFields() {
      List<Class<?>> clazzes = Arrays.asList(PMMLElements.class, org.sparkproject.dmg.pmml.anomaly_detection.PMMLElements.class, org.sparkproject.dmg.pmml.association.PMMLElements.class, org.sparkproject.dmg.pmml.baseline.PMMLElements.class, org.sparkproject.dmg.pmml.bayesian_network.PMMLElements.class, org.sparkproject.dmg.pmml.clustering.PMMLElements.class, org.sparkproject.dmg.pmml.gaussian_process.PMMLElements.class, org.sparkproject.dmg.pmml.general_regression.PMMLElements.class, org.sparkproject.dmg.pmml.mining.PMMLElements.class, org.sparkproject.dmg.pmml.naive_bayes.PMMLElements.class, org.sparkproject.dmg.pmml.nearest_neighbor.PMMLElements.class, org.sparkproject.dmg.pmml.neural_network.PMMLElements.class, org.sparkproject.dmg.pmml.regression.PMMLElements.class, org.sparkproject.dmg.pmml.rule_set.PMMLElements.class, org.sparkproject.dmg.pmml.scorecard.PMMLElements.class, org.sparkproject.dmg.pmml.sequence.PMMLElements.class, org.sparkproject.dmg.pmml.support_vector_machine.PMMLElements.class, org.sparkproject.dmg.pmml.text.PMMLElements.class, org.sparkproject.dmg.pmml.time_series.PMMLElements.class, org.sparkproject.dmg.pmml.tree.PMMLElements.class);
      return getClassConstants(clazzes);
   }

   public static Method getGetterMethod(Field field) {
      Class<?> clazz = field.getDeclaringClass();
      Map<Field, Method> getterMethods = getGetterMethods(clazz);
      Method getterMethod = (Method)getterMethods.get(field);
      if (getterMethod == null) {
         throw new RuntimeException(new NoSuchMethodException());
      } else {
         return getterMethod;
      }
   }

   public static Map getGetterMethods(Class clazz) {
      Map<Field, Method> result = (Map)classGetterMethods.get(clazz);
      if (result == null) {
         result = loadGetterMethods(clazz);
         classGetterMethods.putIfAbsent(clazz, result);
      }

      return result;
   }

   public static Method getSetterMethod(Field field) {
      Class<?> clazz = field.getDeclaringClass();
      Map<Field, Method> setterMethods = getSetterMethods(clazz);
      Method setterMethod = (Method)setterMethods.get(field);
      if (setterMethod == null) {
         throw new RuntimeException(new NoSuchMethodException());
      } else {
         return setterMethod;
      }
   }

   public static Map getSetterMethods(Class clazz) {
      Map<Field, Method> result = (Map)classSetterMethods.get(clazz);
      if (result == null) {
         result = loadSetterMethods(clazz);
         classSetterMethods.putIfAbsent(clazz, result);
      }

      return result;
   }

   public static Method getAppenderMethod(Field field) {
      Class<?> clazz = field.getDeclaringClass();
      Method getterMethod = getGetterMethod(field);
      Class<?> fieldType = field.getType();
      if (!Objects.equals(List.class, fieldType)) {
         throw new RuntimeException(new NoSuchMethodException());
      } else {
         CollectionElementType collectionElementType = (CollectionElementType)field.getAnnotation(CollectionElementType.class);
         Class<?> elementClazz = collectionElementType.value();
         String name = getterMethod.getName();
         if (name.startsWith("get")) {
            name = "add" + name.substring("get".length());

            Class<?> valueArrayClazz;
            try {
               valueArrayClazz = Class.forName("[L" + elementClazz.getCanonicalName() + ";");
            } catch (ClassNotFoundException cnfe) {
               throw new RuntimeException(cnfe);
            }

            try {
               Method appenderMethod = clazz.getMethod(name, valueArrayClazz);
               return appenderMethod;
            } catch (NoSuchMethodException nsme) {
               throw new RuntimeException(nsme);
            }
         } else {
            throw new IllegalArgumentException();
         }
      }
   }

   public static Object getFieldValue(Field field, Object object) {
      if (!field.isAccessible()) {
         field.setAccessible(true);
      }

      try {
         return field.get(object);
      } catch (IllegalAccessException iae) {
         throw new RuntimeException(iae);
      }
   }

   public static void setFieldValue(Field field, Object object, Object value) {
      if (!field.isAccessible()) {
         field.setAccessible(true);
      }

      try {
         field.set(object, value);
      } catch (IllegalAccessException iae) {
         throw new RuntimeException(iae);
      }
   }

   public static Object getGetterMethodValue(Method method, Object object) {
      try {
         return method.invoke(object);
      } catch (InvocationTargetException | IllegalAccessException e) {
         throw new RuntimeException(e);
      }
   }

   public static boolean isPrimitiveWrapper(Class clazz) {
      return primitiveWrapperClasses.contains(clazz);
   }

   public static boolean isDefaultValue(Object value) {
      if (value instanceof Boolean) {
         return Objects.equals(Boolean.FALSE, value);
      } else if (value instanceof Character) {
         Character character = (Character)value;
         return character == 0;
      } else if (value instanceof Number) {
         Number number = (Number)value;
         return Double.compare(number.doubleValue(), (double)0.0F) == 0;
      } else {
         return false;
      }
   }

   public static List getClassConstants(List clazzes) {
      List<Field> result = new ArrayList();

      try {
         for(Class clazz : clazzes) {
            Field[] fields = clazz.getDeclaredFields();

            for(Field field : fields) {
               result.add((Field)field.get((Object)null));
            }
         }

         return result;
      } catch (IllegalAccessException iae) {
         throw new RuntimeException(iae);
      }
   }

   private static Object standardizeValue(Object value) {
      if (value instanceof List) {
         List<?> list = (List)value;
         if (list.isEmpty()) {
            return null;
         }
      }

      return value;
   }

   private static List loadFields(Class clazz) {
      List<Field> result;
      for(result = new ArrayList(); clazz != null; clazz = clazz.getSuperclass()) {
         Field[] fields = clazz.getDeclaredFields();

         for(Field field : fields) {
            if (isValidInstanceField(field)) {
               result.add(field);
            }
         }
      }

      return Collections.unmodifiableList(result);
   }

   private static Map loadGetterMethods(Class clazz) {
      Map<Field, Method> result = new LinkedHashMap();
      Map<String, Field> fieldMap = new HashMap();

      for(Field field : getFields(clazz)) {
         String name = field.getName();
         fieldMap.put(name.toLowerCase(), field);
      }

      Method[] methods = clazz.getMethods();

      for(Method method : methods) {
         String name = method.getName();
         Class<?>[] parameterTypes = method.getParameterTypes();
         if (name.startsWith("is")) {
            name = name.substring("is".length());
         } else {
            if (!name.startsWith("get")) {
               continue;
            }

            name = name.substring("get".length());
         }

         Field field = (Field)fieldMap.get(name.toLowerCase());
         if (field != null && Arrays.equals(new Class[0], parameterTypes)) {
            result.put(field, method);
         }
      }

      return result;
   }

   private static Map loadSetterMethods(Class clazz) {
      Map<Field, Method> result = new LinkedHashMap();
      Map<String, Field> fieldMap = new HashMap();

      for(Field field : getFields(clazz)) {
         String name = field.getName();
         fieldMap.put(name.toLowerCase(), field);
      }

      Method[] methods = clazz.getMethods();

      for(Method method : methods) {
         String name = method.getName();
         Class<?>[] parameterTypes = method.getParameterTypes();
         if (name.startsWith("set")) {
            name = name.substring("set".length());
            Field field = (Field)fieldMap.get(name.toLowerCase());
            if (field != null && Arrays.equals(new Class[]{field.getType()}, parameterTypes)) {
               result.put(field, method);
            }
         }
      }

      return result;
   }

   private static boolean isValidInstanceField(Field field) {
      if (hasValidName(field)) {
         int modifiers = field.getModifiers();
         return !Modifier.isStatic(modifiers);
      } else {
         return false;
      }
   }

   private static boolean hasValidName(Field field) {
      String name = field.getName();
      return name.length() > 0 ? Character.isLetterOrDigit(name.charAt(0)) : false;
   }
}
