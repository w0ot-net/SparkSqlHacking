package org.apache.hadoop.hive.serde2.lazybinary.objectinspector;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;

public final class LazyBinaryObjectInspectorFactory {
   static ConcurrentHashMap cachedLazyBinaryStructObjectInspector = new ConcurrentHashMap();
   static ConcurrentHashMap cachedLazyBinaryUnionObjectInspector = new ConcurrentHashMap();
   static ConcurrentHashMap cachedLazyBinaryListObjectInspector = new ConcurrentHashMap();
   static ConcurrentHashMap cachedLazyBinaryMapObjectInspector = new ConcurrentHashMap();

   public static LazyBinaryStructObjectInspector getLazyBinaryStructObjectInspector(List structFieldNames, List structFieldObjectInspectors) {
      return getLazyBinaryStructObjectInspector(structFieldNames, structFieldObjectInspectors, (List)null);
   }

   public static LazyBinaryStructObjectInspector getLazyBinaryStructObjectInspector(List structFieldNames, List structFieldObjectInspectors, List structFieldComments) {
      ArrayList<Object> signature = new ArrayList(3);
      signature.add(structFieldNames);
      signature.add(structFieldObjectInspectors);
      if (structFieldComments != null) {
         signature.add(structFieldComments);
      }

      LazyBinaryStructObjectInspector result = (LazyBinaryStructObjectInspector)cachedLazyBinaryStructObjectInspector.get(signature);
      if (result == null) {
         result = new LazyBinaryStructObjectInspector(structFieldNames, structFieldObjectInspectors, structFieldComments);
         LazyBinaryStructObjectInspector prev = (LazyBinaryStructObjectInspector)cachedLazyBinaryStructObjectInspector.putIfAbsent(signature, result);
         if (prev != null) {
            result = prev;
         }
      }

      return result;
   }

   public static LazyBinaryUnionObjectInspector getLazyBinaryUnionObjectInspector(List unionFieldObjectInspectors) {
      ArrayList<Object> signature = new ArrayList(1);
      signature.add(unionFieldObjectInspectors);
      LazyBinaryUnionObjectInspector result = (LazyBinaryUnionObjectInspector)cachedLazyBinaryUnionObjectInspector.get(signature);
      if (result == null) {
         result = new LazyBinaryUnionObjectInspector(unionFieldObjectInspectors);
         LazyBinaryUnionObjectInspector prev = (LazyBinaryUnionObjectInspector)cachedLazyBinaryUnionObjectInspector.putIfAbsent(signature, result);
         if (prev != null) {
            result = prev;
         }
      }

      return result;
   }

   public static LazyBinaryListObjectInspector getLazyBinaryListObjectInspector(ObjectInspector listElementObjectInspector) {
      ArrayList<Object> signature = new ArrayList();
      signature.add(listElementObjectInspector);
      LazyBinaryListObjectInspector result = (LazyBinaryListObjectInspector)cachedLazyBinaryListObjectInspector.get(signature);
      if (result == null) {
         result = new LazyBinaryListObjectInspector(listElementObjectInspector);
         LazyBinaryListObjectInspector prev = (LazyBinaryListObjectInspector)cachedLazyBinaryListObjectInspector.putIfAbsent(signature, result);
         if (prev != null) {
            result = prev;
         }
      }

      return result;
   }

   public static LazyBinaryMapObjectInspector getLazyBinaryMapObjectInspector(ObjectInspector mapKeyObjectInspector, ObjectInspector mapValueObjectInspector) {
      ArrayList<Object> signature = new ArrayList();
      signature.add(mapKeyObjectInspector);
      signature.add(mapValueObjectInspector);
      LazyBinaryMapObjectInspector result = (LazyBinaryMapObjectInspector)cachedLazyBinaryMapObjectInspector.get(signature);
      if (result == null) {
         result = new LazyBinaryMapObjectInspector(mapKeyObjectInspector, mapValueObjectInspector);
         LazyBinaryMapObjectInspector prev = (LazyBinaryMapObjectInspector)cachedLazyBinaryMapObjectInspector.putIfAbsent(signature, result);
         if (prev != null) {
            result = prev;
         }
      }

      return result;
   }

   private LazyBinaryObjectInspectorFactory() {
   }
}
