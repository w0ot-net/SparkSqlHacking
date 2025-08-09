package org.sparkproject.spark_core.protobuf;

import java.util.Map;

@CheckReturnValue
final class MapFieldSchemaLite implements MapFieldSchema {
   public Map forMutableMapData(Object mapField) {
      return (MapFieldLite)mapField;
   }

   public MapEntryLite.Metadata forMapMetadata(Object mapDefaultEntry) {
      return ((MapEntryLite)mapDefaultEntry).getMetadata();
   }

   public Map forMapData(Object mapField) {
      return (MapFieldLite)mapField;
   }

   public boolean isImmutable(Object mapField) {
      return !((MapFieldLite)mapField).isMutable();
   }

   public Object toImmutable(Object mapField) {
      ((MapFieldLite)mapField).makeImmutable();
      return mapField;
   }

   public Object newMapField(Object unused) {
      return MapFieldLite.emptyMapField().mutableCopy();
   }

   public Object mergeFrom(Object destMapField, Object srcMapField) {
      return mergeFromLite(destMapField, srcMapField);
   }

   private static MapFieldLite mergeFromLite(Object destMapField, Object srcMapField) {
      MapFieldLite<K, V> mine = (MapFieldLite)destMapField;
      MapFieldLite<K, V> other = (MapFieldLite)srcMapField;
      if (!other.isEmpty()) {
         if (!mine.isMutable()) {
            mine = mine.mutableCopy();
         }

         mine.mergeFrom(other);
      }

      return mine;
   }

   public int getSerializedSize(int fieldNumber, Object mapField, Object mapDefaultEntry) {
      return getSerializedSizeLite(fieldNumber, mapField, mapDefaultEntry);
   }

   private static int getSerializedSizeLite(int fieldNumber, Object mapField, Object defaultEntry) {
      MapFieldLite<K, V> mapFieldLite = (MapFieldLite)mapField;
      MapEntryLite<K, V> defaultEntryLite = (MapEntryLite)defaultEntry;
      if (mapFieldLite.isEmpty()) {
         return 0;
      } else {
         int size = 0;

         for(Map.Entry entry : mapFieldLite.entrySet()) {
            size += defaultEntryLite.computeMessageSize(fieldNumber, entry.getKey(), entry.getValue());
         }

         return size;
      }
   }
}
