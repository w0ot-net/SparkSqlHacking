package org.apache.hadoop.hive.ql.util;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IncrementalObjectSizeEstimator {
   public static final JavaDataModel memoryModel = JavaDataModel.get();
   private static final Logger LOG = LoggerFactory.getLogger(IncrementalObjectSizeEstimator.class.getName());

   public static HashMap createEstimators(Object rootObj) {
      HashMap<Class<?>, ObjectEstimator> byType = new HashMap();
      addHardcodedEstimators(byType);
      createEstimators(rootObj, byType);
      return byType;
   }

   public static void createEstimators(Object rootObj, HashMap byType) {
      Deque<Object> stack = createWorkStack(rootObj, byType);

      while(!stack.isEmpty()) {
         Object obj = stack.pop();
         Class<?> clazz;
         if (obj instanceof Class) {
            clazz = (Class)obj;
            obj = null;
         } else {
            clazz = obj.getClass();
         }

         ObjectEstimator estimator = (ObjectEstimator)byType.get(clazz);

         assert estimator != null;

         if ((estimator.isFromClass || obj != null) && !estimator.isProcessed()) {
            estimator.init();

            for(Field field : getAllFields(clazz)) {
               Class<?> fieldClass = field.getType();
               if (!Modifier.isStatic(field.getModifiers()) && !Class.class.isAssignableFrom(fieldClass)) {
                  if (fieldClass.isPrimitive()) {
                     estimator.addPrimitive(fieldClass);
                  } else if (Enum.class.isAssignableFrom(fieldClass)) {
                     estimator.addEnum();
                  } else {
                     boolean isArray = fieldClass.isArray();
                     if (isArray && fieldClass.getComponentType().isPrimitive()) {
                        estimator.addField(IncrementalObjectSizeEstimator.FieldType.PRIMITIVE_ARRAY, field);
                     } else {
                        Object fieldObj = null;
                        if (obj != null) {
                           fieldObj = extractFieldObj(obj, field);
                           fieldClass = determineRealClass(byType, stack, field, fieldClass, fieldObj);
                        }

                        if (isArray) {
                           estimator.addField(IncrementalObjectSizeEstimator.FieldType.OBJECT_ARRAY, field);
                           addArrayEstimator(byType, stack, field, fieldObj);
                        } else if (Collection.class.isAssignableFrom(fieldClass)) {
                           estimator.addField(IncrementalObjectSizeEstimator.FieldType.COLLECTION, field);
                           addCollectionEstimator(byType, stack, field, fieldClass, fieldObj);
                        } else if (Map.class.isAssignableFrom(fieldClass)) {
                           estimator.addField(IncrementalObjectSizeEstimator.FieldType.MAP, field);
                           addMapEstimator(byType, stack, field, fieldClass, fieldObj);
                        } else {
                           estimator.addField(IncrementalObjectSizeEstimator.FieldType.OTHER, field);
                           addToProcessing(byType, stack, fieldObj, fieldClass);
                        }
                     }
                  }
               }
            }

            estimator.directSize = (int)JavaDataModel.alignUp((long)estimator.directSize, (long)memoryModel.memoryAlign());
         }
      }

   }

   private static Deque createWorkStack(Object rootObj, HashMap byType) {
      Deque<Object> stack = new ArrayDeque(32);
      Class<?> rootClass = rootObj.getClass();
      if (Class.class.equals(rootClass)) {
         rootClass = (Class)rootObj;
         rootObj = null;
      } else if (rootClass.isArray() && !rootClass.getComponentType().isPrimitive()) {
         addArrayEstimator(byType, stack, (Field)null, rootObj);
      } else if (Collection.class.isAssignableFrom(rootClass)) {
         addCollectionEstimator(byType, stack, (Field)null, rootClass, rootObj);
      } else if (Map.class.isAssignableFrom(rootClass)) {
         addMapEstimator(byType, stack, (Field)null, rootClass, rootObj);
      }

      addToProcessing(byType, stack, rootObj, rootClass);
      return stack;
   }

   private static void addHardcodedEstimators(HashMap byType) {
      byType.put(ArrayList.class, new CollectionEstimator(memoryModel.arrayList(), memoryModel.ref()));
      byType.put(LinkedList.class, new CollectionEstimator(memoryModel.linkedListBase(), memoryModel.linkedListEntry()));
      byType.put(HashSet.class, new CollectionEstimator(memoryModel.hashSetBase(), memoryModel.hashSetEntry()));
      byType.put(HashMap.class, new CollectionEstimator(memoryModel.hashMapBase(), memoryModel.hashMapEntry()));
   }

   private static Object extractFieldObj(Object obj, Field field) {
      try {
         return field.get(obj);
      } catch (IllegalAccessException e) {
         throw new AssertionError("IAE: " + field + "; " + e.getMessage());
      }
   }

   private static Class determineRealClass(HashMap byType, Deque stack, Field field, Class fieldClass, Object fieldObj) {
      if (fieldObj == null) {
         return fieldClass;
      } else {
         Class<?> realFieldClass = fieldObj.getClass();
         if (!fieldClass.equals(realFieldClass)) {
            addToProcessing(byType, stack, (Object)null, fieldClass);
            return realFieldClass;
         } else {
            return fieldClass;
         }
      }
   }

   private static void addCollectionEstimator(HashMap byType, Deque stack, Field field, Class fieldClass, Object fieldObj) {
      Collection<?> fieldCol = null;
      if (fieldObj != null) {
         fieldCol = (Collection)fieldObj;
         if (fieldCol.size() == 0) {
            fieldCol = null;
            LOG.trace("Empty collection {}", field);
         }
      }

      if (fieldCol != null) {
         for(Object element : fieldCol) {
            if (element != null) {
               addToProcessing(byType, stack, element, element.getClass());
            }
         }
      }

      if (field != null) {
         Class<?> collectionArg = getCollectionArg(field);
         if (collectionArg != null) {
            addToProcessing(byType, stack, (Object)null, collectionArg);
         }

         addToProcessing(byType, stack, fieldObj, fieldClass);
      }

   }

   private static void addMapEstimator(HashMap byType, Deque stack, Field field, Class fieldClass, Object fieldObj) {
      Map<?, ?> fieldCol = null;
      if (fieldObj != null) {
         fieldCol = (Map)fieldObj;
         if (fieldCol.size() == 0) {
            fieldCol = null;
            LOG.trace("Empty map {}", field);
         }
      }

      if (fieldCol != null) {
         for(Map.Entry element : fieldCol.entrySet()) {
            Object k = element.getKey();
            Object v = element.getValue();
            if (k != null) {
               addToProcessing(byType, stack, k, k.getClass());
            }

            if (v != null) {
               addToProcessing(byType, stack, v, v.getClass());
            }
         }
      }

      if (field != null) {
         Class<?>[] mapArgs = getMapArgs(field);
         if (mapArgs != null) {
            for(Class mapArg : mapArgs) {
               addToProcessing(byType, stack, (Object)null, mapArg);
            }
         }

         addToProcessing(byType, stack, fieldObj, fieldClass);
      }

   }

   private static Class[] getMapArgs(Field field) {
      Type genericType = field.getGenericType();
      if (genericType instanceof ParameterizedType) {
         Type[] types = ((ParameterizedType)genericType).getActualTypeArguments();
         if (types.length == 2 && types[0] instanceof Class && types[1] instanceof Class) {
            return new Class[]{(Class)types[0], (Class)types[1]};
         }

         LOG.trace("Cannot determine map type: {}", field);
      } else {
         LOG.trace("Non-parametrized map type: {}", field);
      }

      return null;
   }

   private static Class getCollectionArg(Field field) {
      Type genericType = field.getGenericType();
      if (genericType instanceof ParameterizedType) {
         Type type = ((ParameterizedType)genericType).getActualTypeArguments()[0];
         if (type instanceof Class) {
            return (Class)type;
         }

         LOG.trace("Cannot determine collection type: {}", field);
      } else {
         LOG.trace("Non-parametrized collection type: {}", field);
      }

      return null;
   }

   private static void addArrayEstimator(HashMap byType, Deque stack, Field field, Object fieldObj) {
      if (fieldObj != null) {
         int arrayLen = Array.getLength(fieldObj);
         LOG.trace("Empty array {}", field);

         for(int i = 0; i < arrayLen; ++i) {
            Object element = Array.get(fieldObj, i);
            if (element != null) {
               addToProcessing(byType, stack, element, element.getClass());
            }
         }

         Class<?> elementClass = fieldObj.getClass().getComponentType();
         addToProcessing(byType, stack, (Object)null, elementClass);
      }
   }

   private static void addToProcessing(HashMap byType, Deque stack, Object element, Class elementClass) {
      ObjectEstimator existing = (ObjectEstimator)byType.get(elementClass);
      if (existing == null || existing.isFromClass && element != null) {
         if (elementClass.isInterface()) {
            if (element == null) {
               return;
            }

            elementClass = element.getClass();
         }

         byType.put(elementClass, new ObjectEstimator(element == null));
         stack.push(element == null ? elementClass : element);
      }
   }

   private static int getPrimitiveSize(Class fieldClass) {
      if (fieldClass != Long.TYPE && fieldClass != Double.TYPE) {
         if (fieldClass != Integer.TYPE && fieldClass != Float.TYPE) {
            if (fieldClass != Short.TYPE && fieldClass != Character.TYPE) {
               if (fieldClass != Byte.TYPE && fieldClass != Boolean.TYPE) {
                  throw new AssertionError("Unrecognized primitive " + fieldClass.getName());
               } else {
                  return 1;
               }
            } else {
               return 2;
            }
         } else {
            return 4;
         }
      } else {
         return 8;
      }
   }

   private static Iterable getAllFields(Class clazz) {
      List<Field> fields;
      for(fields = new ArrayList(8); clazz != null; clazz = clazz.getSuperclass()) {
         fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
      }

      AccessibleObject.setAccessible((AccessibleObject[])fields.toArray(new AccessibleObject[fields.size()]), true);
      return fields;
   }

   public static void addEstimator(String className, HashMap sizeEstimators, Class topClass) {
      Class<?> clazz = null;

      try {
         clazz = Class.forName(className);
      } catch (ClassNotFoundException var5) {
         LOG.warn("Cannot find " + className);
         return;
      }

      createEstimators(clazz, sizeEstimators);
   }

   private static enum FieldType {
      PRIMITIVE_ARRAY,
      OBJECT_ARRAY,
      COLLECTION,
      MAP,
      OTHER;
   }

   private static class FieldAndType {
      public Field field;
      public FieldType type;

      public FieldAndType(Field field, FieldType type) {
         this.field = field;
         this.type = type;
      }
   }

   public static class ObjectEstimator {
      private List fields;
      protected int directSize = -1;
      private boolean isFromClass;

      public ObjectEstimator(boolean isFromClass) {
         this.isFromClass = isFromClass;
      }

      boolean isProcessed() {
         return this.directSize >= 0;
      }

      private void init() {
         assert this.directSize == -1;

         this.directSize = IncrementalObjectSizeEstimator.memoryModel.object();
      }

      private void addPrimitive(Class clazz) {
         this.directSize += IncrementalObjectSizeEstimator.getPrimitiveSize(clazz);
      }

      private void addEnum() {
         this.directSize += IncrementalObjectSizeEstimator.memoryModel.ref();
      }

      private void addField(FieldType type, Field field) {
         if (this.fields == null) {
            this.fields = new ArrayList();
         }

         this.directSize += IncrementalObjectSizeEstimator.memoryModel.ref();
         this.fields.add(new FieldAndType(field, type));
      }

      public int estimate(Object obj, HashMap parent) {
         IdentityHashMap<Object, Boolean> uniqueObjects = new IdentityHashMap();
         uniqueObjects.put(obj, Boolean.TRUE);
         return this.estimate(obj, parent, uniqueObjects);
      }

      protected int estimate(Object obj, HashMap parent, IdentityHashMap uniqueObjects) {
         if (this.fields == null) {
            return this.directSize;
         } else {
            int referencedSize = 0;

            for(FieldAndType e : this.fields) {
               Object fieldObj;
               try {
                  fieldObj = e.field.get(obj);
               } catch (IllegalAccessException ex) {
                  throw new AssertionError("IAE: " + ex.getMessage());
               }

               if (fieldObj != null && null == uniqueObjects.put(fieldObj, Boolean.TRUE)) {
                  switch (e.type) {
                     case COLLECTION:
                        Collection<?> c = (Collection)fieldObj;
                        ObjectEstimator collEstimator = (ObjectEstimator)parent.get(fieldObj.getClass());
                        if (collEstimator == null) {
                           IncrementalObjectSizeEstimator.LOG.trace("Approximate estimation for collection {} from {}", e.field, fieldObj.getClass().getName());
                           referencedSize += IncrementalObjectSizeEstimator.memoryModel.object();
                           referencedSize += this.estimateCollectionElements(parent, c, e.field, uniqueObjects);
                           referencedSize += IncrementalObjectSizeEstimator.memoryModel.array() + c.size() * IncrementalObjectSizeEstimator.memoryModel.ref();
                        } else if (collEstimator instanceof CollectionEstimator) {
                           referencedSize += IncrementalObjectSizeEstimator.memoryModel.object();
                           referencedSize += this.estimateCollectionElements(parent, c, e.field, uniqueObjects);
                           referencedSize += ((CollectionEstimator)collEstimator).estimateOverhead(c.size());
                        } else {
                           IncrementalObjectSizeEstimator.LOG.trace("Verbose estimation for collection {} from {}", fieldObj.getClass().getName(), e.field);
                           referencedSize += collEstimator.estimate(c, parent, uniqueObjects);
                        }
                        break;
                     case MAP:
                        Map<?, ?> m = (Map)fieldObj;
                        ObjectEstimator collEstimator = (ObjectEstimator)parent.get(fieldObj.getClass());
                        if (collEstimator == null) {
                           IncrementalObjectSizeEstimator.LOG.trace("Approximate estimation for map {} from {}", fieldObj.getClass().getName(), e.field);
                           referencedSize += IncrementalObjectSizeEstimator.memoryModel.object();
                           referencedSize += this.estimateMapElements(parent, m, e.field, uniqueObjects);
                           referencedSize += IncrementalObjectSizeEstimator.memoryModel.array() + m.size() * (IncrementalObjectSizeEstimator.memoryModel.ref() * 2 + IncrementalObjectSizeEstimator.memoryModel.object());
                        } else if (collEstimator instanceof CollectionEstimator) {
                           referencedSize += IncrementalObjectSizeEstimator.memoryModel.object();
                           referencedSize += this.estimateMapElements(parent, m, e.field, uniqueObjects);
                           referencedSize += ((CollectionEstimator)collEstimator).estimateOverhead(m.size());
                        } else {
                           IncrementalObjectSizeEstimator.LOG.trace("Verbose estimation for map {} from {}", fieldObj.getClass().getName(), e.field);
                           referencedSize += collEstimator.estimate(m, parent, uniqueObjects);
                        }
                        break;
                     case OBJECT_ARRAY:
                        int len = Array.getLength(fieldObj);
                        referencedSize = (int)((long)referencedSize + JavaDataModel.alignUp((long)(IncrementalObjectSizeEstimator.memoryModel.array() + len * IncrementalObjectSizeEstimator.memoryModel.ref()), (long)IncrementalObjectSizeEstimator.memoryModel.memoryAlign()));
                        if (len != 0) {
                           referencedSize += this.estimateArrayElements(parent, e, fieldObj, len, uniqueObjects);
                        }
                        break;
                     case PRIMITIVE_ARRAY:
                        int arraySize = IncrementalObjectSizeEstimator.memoryModel.array();
                        int len = Array.getLength(fieldObj);
                        if (len != 0) {
                           int elementSize = IncrementalObjectSizeEstimator.getPrimitiveSize(e.field.getType().getComponentType());
                           arraySize += elementSize * len;
                           arraySize = (int)JavaDataModel.alignUp((long)arraySize, (long)IncrementalObjectSizeEstimator.memoryModel.memoryAlign());
                        }

                        referencedSize += arraySize;
                        break;
                     case OTHER:
                        ObjectEstimator fieldEstimator = (ObjectEstimator)parent.get(fieldObj.getClass());
                        if (fieldEstimator == null) {
                           IncrementalObjectSizeEstimator.createEstimators(fieldObj.getClass(), parent);
                        }

                        fieldEstimator = (ObjectEstimator)parent.get(fieldObj.getClass());
                        if (fieldEstimator == null) {
                           throw new AssertionError("Don't know how to measure " + fieldObj.getClass().getName() + " from " + e.field);
                        }

                        referencedSize += fieldEstimator.estimate(fieldObj, parent, uniqueObjects);
                        break;
                     default:
                        throw new AssertionError("Unknown type " + e.type);
                  }
               }
            }

            return this.directSize + referencedSize;
         }
      }

      private int estimateArrayElements(HashMap parent, FieldAndType e, Object fieldObj, int len, IdentityHashMap uniqueObjects) {
         int result = 0;
         Class<?> lastClass = e.field.getType().getComponentType();
         ObjectEstimator lastEstimator = (ObjectEstimator)parent.get(lastClass);

         for(int i = 0; i < len; ++i) {
            Object element = Array.get(fieldObj, i);
            if (element != null && null == uniqueObjects.put(element, Boolean.TRUE)) {
               Class<?> elementClass = element.getClass();
               if (lastClass != elementClass) {
                  lastClass = elementClass;
                  lastEstimator = (ObjectEstimator)parent.get(elementClass);
                  if (lastEstimator == null) {
                     IncrementalObjectSizeEstimator.createEstimators(elementClass, parent);
                  }

                  lastEstimator = (ObjectEstimator)parent.get(elementClass);
                  if (lastEstimator == null) {
                     throw new AssertionError("Don't know how to measure element " + elementClass.getName() + " from " + e.field);
                  }
               }

               result += lastEstimator.estimate(element, parent, uniqueObjects);
            }
         }

         return result;
      }

      protected int estimateCollectionElements(HashMap parent, Collection c, Field field, IdentityHashMap uniqueObjects) {
         ObjectEstimator lastEstimator = null;
         Class<?> lastClass = null;
         int result = 0;

         for(Object element : c) {
            if (element != null && null == uniqueObjects.put(element, Boolean.TRUE)) {
               Class<?> elementClass = element.getClass();
               if (lastClass != elementClass) {
                  lastClass = elementClass;
                  lastEstimator = (ObjectEstimator)parent.get(elementClass);
                  if (lastEstimator == null) {
                     IncrementalObjectSizeEstimator.createEstimators(elementClass, parent);
                  }

                  lastEstimator = (ObjectEstimator)parent.get(elementClass);
                  if (lastEstimator == null) {
                     throw new AssertionError("Don't know how to measure element " + elementClass.getName() + " from " + field);
                  }
               }

               result += lastEstimator.estimate(element, parent, uniqueObjects);
            }
         }

         return result;
      }

      protected int estimateMapElements(HashMap parent, Map m, Field field, IdentityHashMap uniqueObjects) {
         ObjectEstimator keyEstimator = null;
         ObjectEstimator valueEstimator = null;
         Class<?> lastKeyClass = null;
         Class<?> lastValueClass = null;
         int result = 0;

         for(Map.Entry element : m.entrySet()) {
            Object key = element.getKey();
            Object value = element.getValue();
            if (null == uniqueObjects.put(key, Boolean.TRUE)) {
               Class<?> keyClass = key.getClass();
               if (lastKeyClass != keyClass) {
                  lastKeyClass = keyClass;
                  keyEstimator = (ObjectEstimator)parent.get(keyClass);
                  if (keyEstimator == null) {
                     IncrementalObjectSizeEstimator.createEstimators(keyClass, parent);
                  }

                  keyEstimator = (ObjectEstimator)parent.get(keyClass);
                  if (keyEstimator == null) {
                     throw new AssertionError("Don't know how to measure key " + keyClass.getName() + " from " + field);
                  }
               }

               result += keyEstimator.estimate(key, parent, uniqueObjects);
               if (value != null && null == uniqueObjects.put(value, Boolean.TRUE)) {
                  Class<?> valueClass = value.getClass();
                  if (lastValueClass != valueClass) {
                     lastValueClass = valueClass;
                     valueEstimator = (ObjectEstimator)parent.get(valueClass);
                     if (valueEstimator == null) {
                        IncrementalObjectSizeEstimator.createEstimators(valueClass, parent);
                     }

                     valueEstimator = (ObjectEstimator)parent.get(valueClass);
                     if (valueEstimator == null) {
                        throw new AssertionError("Don't know how to measure value " + valueClass.getName() + " from " + field);
                     }
                  }

                  result += valueEstimator.estimate(value, parent, uniqueObjects);
               }
            }
         }

         return result;
      }
   }

   private static class CollectionEstimator extends ObjectEstimator {
      private int perEntryOverhead;

      public CollectionEstimator(int base, int perElement) {
         super(false);
         this.directSize = base;
         this.perEntryOverhead = perElement;
      }

      protected int estimate(Object obj, HashMap parent, IdentityHashMap uniqueObjects) {
         if (obj instanceof Collection) {
            Collection<?> c = (Collection)obj;
            int overhead = this.estimateOverhead(c.size());
            int elements = this.estimateCollectionElements(parent, c, (Field)null, uniqueObjects);
            return overhead + elements + IncrementalObjectSizeEstimator.memoryModel.object();
         } else if (obj instanceof Map) {
            Map<?, ?> m = (Map)obj;
            int overhead = this.estimateOverhead(m.size());
            int elements = this.estimateMapElements(parent, m, (Field)null, uniqueObjects);
            return overhead + elements + IncrementalObjectSizeEstimator.memoryModel.object();
         } else {
            throw new AssertionError(obj.getClass().getName());
         }
      }

      int estimateOverhead(int size) {
         return this.directSize + this.perEntryOverhead * size;
      }
   }
}
