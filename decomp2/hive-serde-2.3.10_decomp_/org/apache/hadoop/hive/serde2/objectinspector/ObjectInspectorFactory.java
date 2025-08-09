package org.apache.hadoop.hive.serde2.objectinspector;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.hadoop.hive.common.StringInternUtils;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorUtils;
import org.apache.thrift.TUnion;

public final class ObjectInspectorFactory {
   static ConcurrentHashMap objectInspectorCache = new ConcurrentHashMap();
   static ConcurrentHashMap cachedStandardListObjectInspector = new ConcurrentHashMap();
   static ConcurrentHashMap cachedStandardMapObjectInspector = new ConcurrentHashMap();
   static ConcurrentHashMap cachedStandardUnionObjectInspector = new ConcurrentHashMap();
   static ConcurrentHashMap cachedStandardStructObjectInspector = new ConcurrentHashMap();
   static ConcurrentHashMap cachedUnionStructObjectInspector = new ConcurrentHashMap();
   static ConcurrentHashMap cachedColumnarStructObjectInspector = new ConcurrentHashMap();

   public static ObjectInspector getReflectionObjectInspector(Type t, ObjectInspectorOptions options) {
      return getReflectionObjectInspector(t, options, true);
   }

   static ObjectInspector getReflectionObjectInspector(Type t, ObjectInspectorOptions options, boolean ensureInited) {
      ObjectInspector oi = (ObjectInspector)objectInspectorCache.get(t);
      if (oi == null) {
         oi = getReflectionObjectInspectorNoCache(t, options, ensureInited);
         ObjectInspector prev = (ObjectInspector)objectInspectorCache.putIfAbsent(t, oi);
         if (prev != null) {
            oi = prev;
         }
      }

      if (ensureInited && oi instanceof ReflectionStructObjectInspector) {
         ReflectionStructObjectInspector soi = (ReflectionStructObjectInspector)oi;
         synchronized(soi) {
            HashSet<Type> checkedTypes = new HashSet();

            while(!soi.isFullyInited(checkedTypes)) {
               try {
                  soi.wait(3000L);
               } catch (InterruptedException e) {
                  throw new RuntimeException("Interrupted while waiting for " + soi.getClass().getName() + " to initialize", e);
               }
            }
         }
      }

      verifyObjectInspector(options, oi, ObjectInspectorFactory.ObjectInspectorOptions.JAVA, new Class[]{ThriftStructObjectInspector.class, ProtocolBuffersStructObjectInspector.class});
      verifyObjectInspector(options, oi, ObjectInspectorFactory.ObjectInspectorOptions.THRIFT, new Class[]{ReflectionStructObjectInspector.class, ProtocolBuffersStructObjectInspector.class});
      verifyObjectInspector(options, oi, ObjectInspectorFactory.ObjectInspectorOptions.PROTOCOL_BUFFERS, new Class[]{ThriftStructObjectInspector.class, ReflectionStructObjectInspector.class});
      return oi;
   }

   private static void verifyObjectInspector(ObjectInspectorOptions option, ObjectInspector oi, ObjectInspectorOptions checkOption, Class[] classes) {
      if (option.equals(checkOption)) {
         for(Class checkClass : classes) {
            if (oi.getClass().equals(checkClass)) {
               throw new RuntimeException("Cannot call getObjectInspectorByReflection with more then one of " + Arrays.toString(ObjectInspectorFactory.ObjectInspectorOptions.values()) + "!");
            }
         }
      }

   }

   private static ObjectInspector getReflectionObjectInspectorNoCache(Type t, ObjectInspectorOptions options, boolean ensureInited) {
      if (t instanceof GenericArrayType) {
         GenericArrayType at = (GenericArrayType)t;
         return getStandardListObjectInspector(getReflectionObjectInspector(at.getGenericComponentType(), options, ensureInited));
      } else {
         if (t instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType)t;
            if (List.class.isAssignableFrom((Class)pt.getRawType()) || Set.class.isAssignableFrom((Class)pt.getRawType())) {
               return getStandardListObjectInspector(getReflectionObjectInspector(pt.getActualTypeArguments()[0], options, ensureInited));
            }

            if (Map.class.isAssignableFrom((Class)pt.getRawType())) {
               return getStandardMapObjectInspector(getReflectionObjectInspector(pt.getActualTypeArguments()[0], options, ensureInited), getReflectionObjectInspector(pt.getActualTypeArguments()[1], options, ensureInited));
            }

            t = pt.getRawType();
         }

         if (!(t instanceof Class)) {
            throw new RuntimeException(ObjectInspectorFactory.class.getName() + " internal error:" + t);
         } else {
            Class<?> c = (Class)t;
            if (PrimitiveObjectInspectorUtils.isPrimitiveJavaType(c)) {
               return PrimitiveObjectInspectorFactory.getPrimitiveJavaObjectInspector(PrimitiveObjectInspectorUtils.getTypeEntryFromPrimitiveJavaType(c).primitiveCategory);
            } else if (PrimitiveObjectInspectorUtils.isPrimitiveJavaClass(c)) {
               return PrimitiveObjectInspectorFactory.getPrimitiveJavaObjectInspector(PrimitiveObjectInspectorUtils.getTypeEntryFromPrimitiveJavaClass(c).primitiveCategory);
            } else if (PrimitiveObjectInspectorUtils.isPrimitiveWritableClass(c)) {
               return PrimitiveObjectInspectorFactory.getPrimitiveWritableObjectInspector(PrimitiveObjectInspectorUtils.getTypeEntryFromPrimitiveWritableClass(c).primitiveCategory);
            } else if (Enum.class.isAssignableFrom(c)) {
               return PrimitiveObjectInspectorFactory.getPrimitiveJavaObjectInspector(PrimitiveObjectInspector.PrimitiveCategory.STRING);
            } else {
               assert !List.class.isAssignableFrom(c);

               assert !Map.class.isAssignableFrom(c);

               ReflectionStructObjectInspector oi;
               switch (options) {
                  case JAVA:
                     oi = new ReflectionStructObjectInspector();
                     break;
                  case THRIFT:
                     oi = (ReflectionStructObjectInspector)(TUnion.class.isAssignableFrom(c) ? new ThriftUnionObjectInspector() : new ThriftStructObjectInspector());
                     break;
                  case PROTOCOL_BUFFERS:
                     oi = new ProtocolBuffersStructObjectInspector();
                     break;
                  default:
                     throw new RuntimeException(ObjectInspectorFactory.class.getName() + ": internal error.");
               }

               ReflectionStructObjectInspector prev = (ReflectionStructObjectInspector)objectInspectorCache.putIfAbsent(t, oi);
               if (prev != null) {
                  oi = prev;
               } else {
                  try {
                     oi.init(t, c, options);
                  } finally {
                     if (!oi.inited) {
                        objectInspectorCache.remove(t, oi);
                     }

                  }
               }

               return oi;
            }
         }
      }
   }

   public static StandardListObjectInspector getStandardListObjectInspector(ObjectInspector listElementObjectInspector) {
      StandardListObjectInspector result = (StandardListObjectInspector)cachedStandardListObjectInspector.get(listElementObjectInspector);
      if (result == null) {
         result = new StandardListObjectInspector(listElementObjectInspector);
         StandardListObjectInspector prev = (StandardListObjectInspector)cachedStandardListObjectInspector.putIfAbsent(listElementObjectInspector, result);
         if (prev != null) {
            result = prev;
         }
      }

      return result;
   }

   public static StandardConstantListObjectInspector getStandardConstantListObjectInspector(ObjectInspector listElementObjectInspector, List constantValue) {
      return new StandardConstantListObjectInspector(listElementObjectInspector, constantValue);
   }

   public static StandardMapObjectInspector getStandardMapObjectInspector(ObjectInspector mapKeyObjectInspector, ObjectInspector mapValueObjectInspector) {
      ArrayList<ObjectInspector> signature = new ArrayList(2);
      signature.add(mapKeyObjectInspector);
      signature.add(mapValueObjectInspector);
      StandardMapObjectInspector result = (StandardMapObjectInspector)cachedStandardMapObjectInspector.get(signature);
      if (result == null) {
         result = new StandardMapObjectInspector(mapKeyObjectInspector, mapValueObjectInspector);
         StandardMapObjectInspector prev = (StandardMapObjectInspector)cachedStandardMapObjectInspector.putIfAbsent(signature, result);
         if (prev != null) {
            result = prev;
         }
      }

      return result;
   }

   public static StandardConstantMapObjectInspector getStandardConstantMapObjectInspector(ObjectInspector mapKeyObjectInspector, ObjectInspector mapValueObjectInspector, Map constantValue) {
      return new StandardConstantMapObjectInspector(mapKeyObjectInspector, mapValueObjectInspector, constantValue);
   }

   public static StandardUnionObjectInspector getStandardUnionObjectInspector(List unionObjectInspectors) {
      StandardUnionObjectInspector result = (StandardUnionObjectInspector)cachedStandardUnionObjectInspector.get(unionObjectInspectors);
      if (result == null) {
         result = new StandardUnionObjectInspector(unionObjectInspectors);
         StandardUnionObjectInspector prev = (StandardUnionObjectInspector)cachedStandardUnionObjectInspector.putIfAbsent(unionObjectInspectors, result);
         if (prev != null) {
            result = prev;
         }
      }

      return result;
   }

   public static StandardStructObjectInspector getStandardStructObjectInspector(List structFieldNames, List structFieldObjectInspectors) {
      return getStandardStructObjectInspector(structFieldNames, structFieldObjectInspectors, (List)null);
   }

   public static StandardStructObjectInspector getStandardStructObjectInspector(List structFieldNames, List structFieldObjectInspectors, List structComments) {
      ArrayList<List<?>> signature = new ArrayList(3);
      StringInternUtils.internStringsInList(structFieldNames);
      signature.add(structFieldNames);
      signature.add(structFieldObjectInspectors);
      if (structComments != null) {
         StringInternUtils.internStringsInList(structComments);
         signature.add(structComments);
      }

      StandardStructObjectInspector result = (StandardStructObjectInspector)cachedStandardStructObjectInspector.get(signature);
      if (result == null) {
         result = new StandardStructObjectInspector(structFieldNames, structFieldObjectInspectors, structComments);
         StandardStructObjectInspector prev = (StandardStructObjectInspector)cachedStandardStructObjectInspector.putIfAbsent(signature, result);
         if (prev != null) {
            result = prev;
         }
      }

      return result;
   }

   public static StandardConstantStructObjectInspector getStandardConstantStructObjectInspector(List structFieldNames, List structFieldObjectInspectors, List value) {
      return new StandardConstantStructObjectInspector(structFieldNames, structFieldObjectInspectors, value);
   }

   public static UnionStructObjectInspector getUnionStructObjectInspector(List structObjectInspectors) {
      UnionStructObjectInspector result = (UnionStructObjectInspector)cachedUnionStructObjectInspector.get(structObjectInspectors);
      if (result == null) {
         result = new UnionStructObjectInspector(structObjectInspectors);
         UnionStructObjectInspector prev = (UnionStructObjectInspector)cachedUnionStructObjectInspector.putIfAbsent(structObjectInspectors, result);
         if (prev != null) {
            result = prev;
         }
      }

      return result;
   }

   public static ColumnarStructObjectInspector getColumnarStructObjectInspector(List structFieldNames, List structFieldObjectInspectors) {
      return getColumnarStructObjectInspector(structFieldNames, structFieldObjectInspectors, (List)null);
   }

   public static ColumnarStructObjectInspector getColumnarStructObjectInspector(List structFieldNames, List structFieldObjectInspectors, List structFieldComments) {
      ArrayList<Object> signature = new ArrayList(3);
      signature.add(structFieldNames);
      signature.add(structFieldObjectInspectors);
      if (structFieldComments != null) {
         signature.add(structFieldComments);
      }

      ColumnarStructObjectInspector result = (ColumnarStructObjectInspector)cachedColumnarStructObjectInspector.get(signature);
      if (result == null) {
         result = new ColumnarStructObjectInspector(structFieldNames, structFieldObjectInspectors, structFieldComments);
         ColumnarStructObjectInspector prev = (ColumnarStructObjectInspector)cachedColumnarStructObjectInspector.putIfAbsent(signature, result);
         if (prev != null) {
            result = prev;
         }
      }

      return result;
   }

   private ObjectInspectorFactory() {
   }

   public static enum ObjectInspectorOptions {
      JAVA,
      THRIFT,
      PROTOCOL_BUFFERS,
      AVRO;
   }
}
