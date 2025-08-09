package org.apache.hadoop.hive.serde2.lazy.objectinspector;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.hadoop.hive.serde2.avro.AvroLazyObjectInspector;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyObjectInspectorParameters;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyObjectInspectorParametersImpl;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.io.Text;

public final class LazyObjectInspectorFactory {
   static ConcurrentHashMap cachedLazySimpleStructObjectInspector = new ConcurrentHashMap();
   static ConcurrentHashMap cachedLazySimpleListObjectInspector = new ConcurrentHashMap();
   static ConcurrentHashMap cachedLazySimpleMapObjectInspector = new ConcurrentHashMap();
   static ConcurrentHashMap cachedLazyUnionObjectInspector = new ConcurrentHashMap();

   /** @deprecated */
   @Deprecated
   public static LazySimpleStructObjectInspector getLazySimpleStructObjectInspector(List structFieldNames, List structFieldObjectInspectors, byte separator, Text nullSequence, boolean lastColumnTakesRest, boolean escaped, byte escapeChar) {
      return getLazySimpleStructObjectInspector(structFieldNames, structFieldObjectInspectors, (List)null, separator, nullSequence, lastColumnTakesRest, escaped, escapeChar, ObjectInspectorFactory.ObjectInspectorOptions.JAVA);
   }

   /** @deprecated */
   @Deprecated
   public static LazySimpleStructObjectInspector getLazySimpleStructObjectInspector(List structFieldNames, List structFieldObjectInspectors, byte separator, Text nullSequence, boolean lastColumnTakesRest, boolean escaped, byte escapeChar, ObjectInspectorFactory.ObjectInspectorOptions option) {
      return getLazySimpleStructObjectInspector(structFieldNames, structFieldObjectInspectors, (List)null, separator, nullSequence, lastColumnTakesRest, escaped, escapeChar, option);
   }

   /** @deprecated */
   @Deprecated
   public static LazySimpleStructObjectInspector getLazySimpleStructObjectInspector(List structFieldNames, List structFieldObjectInspectors, List structFieldComments, byte separator, Text nullSequence, boolean lastColumnTakesRest, boolean escaped, byte escapeChar) {
      return getLazySimpleStructObjectInspector(structFieldNames, structFieldObjectInspectors, structFieldComments, separator, nullSequence, lastColumnTakesRest, escaped, escapeChar, ObjectInspectorFactory.ObjectInspectorOptions.JAVA);
   }

   /** @deprecated */
   @Deprecated
   public static LazySimpleStructObjectInspector getLazySimpleStructObjectInspector(List structFieldNames, List structFieldObjectInspectors, List structFieldComments, byte separator, Text nullSequence, boolean lastColumnTakesRest, boolean escaped, byte escapeChar, ObjectInspectorFactory.ObjectInspectorOptions option) {
      return getLazySimpleStructObjectInspector(structFieldNames, structFieldObjectInspectors, structFieldComments, separator, new LazyObjectInspectorParametersImpl(escaped, escapeChar, false, (List)null, (byte[])null, nullSequence, lastColumnTakesRest), option);
   }

   public static LazySimpleStructObjectInspector getLazySimpleStructObjectInspector(List structFieldNames, List structFieldObjectInspectors, List structFieldComments, byte separator, LazyObjectInspectorParameters lazyParams, ObjectInspectorFactory.ObjectInspectorOptions option) {
      ArrayList<Object> signature = new ArrayList();
      signature.add(structFieldNames);
      signature.add(structFieldObjectInspectors);
      signature.add(separator);
      signature.add(lazyParams.getNullSequence().toString());
      signature.add(lazyParams.isLastColumnTakesRest());
      addCommonLazyParamsToSignature(lazyParams, signature);
      signature.add(option);
      if (structFieldComments != null) {
         signature.add(structFieldComments);
      }

      LazySimpleStructObjectInspector result = (LazySimpleStructObjectInspector)cachedLazySimpleStructObjectInspector.get(signature);
      if (result == null) {
         switch (option) {
            case JAVA:
               result = new LazySimpleStructObjectInspector(structFieldNames, structFieldObjectInspectors, structFieldComments, separator, lazyParams);
               break;
            case AVRO:
               result = new AvroLazyObjectInspector(structFieldNames, structFieldObjectInspectors, structFieldComments, separator, lazyParams);
               break;
            default:
               throw new IllegalArgumentException("Illegal ObjectInspector type [" + option + "]");
         }

         LazySimpleStructObjectInspector prev = (LazySimpleStructObjectInspector)cachedLazySimpleStructObjectInspector.putIfAbsent(signature, result);
         if (prev != null) {
            result = prev;
         }
      }

      return result;
   }

   /** @deprecated */
   @Deprecated
   public static LazyListObjectInspector getLazySimpleListObjectInspector(ObjectInspector listElementObjectInspector, byte separator, Text nullSequence, boolean escaped, byte escapeChar) {
      return getLazySimpleListObjectInspector(listElementObjectInspector, separator, new LazyObjectInspectorParametersImpl(escaped, escapeChar, false, (List)null, (byte[])null, nullSequence));
   }

   public static LazyListObjectInspector getLazySimpleListObjectInspector(ObjectInspector listElementObjectInspector, byte separator, LazyObjectInspectorParameters lazyParams) {
      ArrayList<Object> signature = new ArrayList();
      signature.add(listElementObjectInspector);
      signature.add(separator);
      signature.add(lazyParams.getNullSequence().toString());
      addCommonLazyParamsToSignature(lazyParams, signature);
      LazyListObjectInspector result = (LazyListObjectInspector)cachedLazySimpleListObjectInspector.get(signature);
      if (result == null) {
         result = new LazyListObjectInspector(listElementObjectInspector, separator, lazyParams);
         LazyListObjectInspector prev = (LazyListObjectInspector)cachedLazySimpleListObjectInspector.putIfAbsent(signature, result);
         if (prev != null) {
            result = prev;
         }
      }

      return result;
   }

   /** @deprecated */
   @Deprecated
   public static LazyMapObjectInspector getLazySimpleMapObjectInspector(ObjectInspector mapKeyObjectInspector, ObjectInspector mapValueObjectInspector, byte itemSeparator, byte keyValueSeparator, Text nullSequence, boolean escaped, byte escapeChar) {
      return getLazySimpleMapObjectInspector(mapKeyObjectInspector, mapValueObjectInspector, itemSeparator, keyValueSeparator, new LazyObjectInspectorParametersImpl(escaped, escapeChar, false, (List)null, (byte[])null, nullSequence));
   }

   public static LazyMapObjectInspector getLazySimpleMapObjectInspector(ObjectInspector mapKeyObjectInspector, ObjectInspector mapValueObjectInspector, byte itemSeparator, byte keyValueSeparator, LazyObjectInspectorParameters lazyParams) {
      ArrayList<Object> signature = new ArrayList();
      signature.add(mapKeyObjectInspector);
      signature.add(mapValueObjectInspector);
      signature.add(itemSeparator);
      signature.add(keyValueSeparator);
      signature.add(lazyParams.getNullSequence().toString());
      addCommonLazyParamsToSignature(lazyParams, signature);
      LazyMapObjectInspector result = (LazyMapObjectInspector)cachedLazySimpleMapObjectInspector.get(signature);
      if (result == null) {
         result = new LazyMapObjectInspector(mapKeyObjectInspector, mapValueObjectInspector, itemSeparator, keyValueSeparator, lazyParams);
         LazyMapObjectInspector prev = (LazyMapObjectInspector)cachedLazySimpleMapObjectInspector.putIfAbsent(signature, result);
         if (prev != null) {
            result = prev;
         }
      }

      return result;
   }

   /** @deprecated */
   @Deprecated
   public static LazyUnionObjectInspector getLazyUnionObjectInspector(List ois, byte separator, Text nullSequence, boolean escaped, byte escapeChar) {
      return getLazyUnionObjectInspector(ois, separator, new LazyObjectInspectorParametersImpl(escaped, escapeChar, false, (List)null, (byte[])null, nullSequence));
   }

   public static LazyUnionObjectInspector getLazyUnionObjectInspector(List ois, byte separator, LazyObjectInspectorParameters lazyParams) {
      List<Object> signature = new ArrayList();
      signature.add(ois);
      signature.add(separator);
      signature.add(lazyParams.getNullSequence().toString());
      addCommonLazyParamsToSignature(lazyParams, signature);
      LazyUnionObjectInspector result = (LazyUnionObjectInspector)cachedLazyUnionObjectInspector.get(signature);
      if (result == null) {
         result = new LazyUnionObjectInspector(ois, separator, lazyParams);
         LazyUnionObjectInspector prev = (LazyUnionObjectInspector)cachedLazyUnionObjectInspector.putIfAbsent(signature, result);
         if (prev != null) {
            result = prev;
         }
      }

      return result;
   }

   private LazyObjectInspectorFactory() {
   }

   private static void addCommonLazyParamsToSignature(LazyObjectInspectorParameters lazyParams, List signature) {
      signature.add(lazyParams.isEscaped());
      signature.add(lazyParams.getEscapeChar());
      signature.add(lazyParams.isExtendedBooleanLiteral());
      signature.add(lazyParams.getTimestampFormats());
   }
}
