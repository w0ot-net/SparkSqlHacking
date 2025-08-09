package org.apache.orc.protobuf;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MapFieldBuilder extends MapFieldReflectionAccessor {
   Map builderMap = new LinkedHashMap();
   Map messageMap = null;
   List messageList = null;
   Converter converter;

   public MapFieldBuilder(Converter converter) {
      this.converter = converter;
   }

   private List getMapEntryList() {
      ArrayList<MapEntry<KeyT, MessageT>> list = new ArrayList(this.messageList.size());
      Class<?> valueClass = ((MessageOrBuilder)this.converter.defaultEntry().getValue()).getClass();

      for(Message entry : this.messageList) {
         MapEntry<KeyT, ?> typedEntry = (MapEntry)entry;
         if (valueClass.isInstance(typedEntry.getValue())) {
            list.add(typedEntry);
         } else {
            list.add(((MapEntry.Builder)this.converter.defaultEntry().toBuilder().mergeFrom(entry)).build());
         }
      }

      return list;
   }

   public Map ensureBuilderMap() {
      if (this.builderMap != null) {
         return this.builderMap;
      } else if (this.messageMap != null) {
         this.builderMap = new LinkedHashMap(this.messageMap.size());

         for(Map.Entry entry : this.messageMap.entrySet()) {
            this.builderMap.put(entry.getKey(), (MessageOrBuilder)entry.getValue());
         }

         this.messageMap = null;
         return this.builderMap;
      } else {
         this.builderMap = new LinkedHashMap(this.messageList.size());

         for(MapEntry entry : this.getMapEntryList()) {
            this.builderMap.put(entry.getKey(), (MessageOrBuilder)entry.getValue());
         }

         this.messageList = null;
         return this.builderMap;
      }
   }

   public List ensureMessageList() {
      if (this.messageList != null) {
         return this.messageList;
      } else if (this.builderMap != null) {
         this.messageList = new ArrayList(this.builderMap.size());

         for(Map.Entry entry : this.builderMap.entrySet()) {
            this.messageList.add(this.converter.defaultEntry().toBuilder().setKey(entry.getKey()).setValue(this.converter.build((MessageOrBuilder)entry.getValue())).build());
         }

         this.builderMap = null;
         return this.messageList;
      } else {
         this.messageList = new ArrayList(this.messageMap.size());

         for(Map.Entry entry : this.messageMap.entrySet()) {
            this.messageList.add(this.converter.defaultEntry().toBuilder().setKey(entry.getKey()).setValue((MessageOrBuilder)entry.getValue()).build());
         }

         this.messageMap = null;
         return this.messageList;
      }
   }

   public Map ensureMessageMap() {
      this.messageMap = this.populateMutableMap();
      this.builderMap = null;
      this.messageList = null;
      return this.messageMap;
   }

   public Map getImmutableMap() {
      return new MapField.MutabilityAwareMap(MutabilityOracle.IMMUTABLE, this.populateMutableMap());
   }

   private Map populateMutableMap() {
      if (this.messageMap != null) {
         return this.messageMap;
      } else if (this.builderMap != null) {
         Map<KeyT, MessageT> toReturn = new LinkedHashMap(this.builderMap.size());

         for(Map.Entry entry : this.builderMap.entrySet()) {
            toReturn.put(entry.getKey(), this.converter.build((MessageOrBuilder)entry.getValue()));
         }

         return toReturn;
      } else {
         Map<KeyT, MessageT> toReturn = new LinkedHashMap(this.messageList.size());

         for(MapEntry entry : this.getMapEntryList()) {
            toReturn.put(entry.getKey(), (MessageOrBuilder)entry.getValue());
         }

         return toReturn;
      }
   }

   public void mergeFrom(MapField other) {
      this.ensureBuilderMap().putAll(MapFieldLite.copy(other.getMap()));
   }

   public void clear() {
      this.builderMap = new LinkedHashMap();
      this.messageMap = null;
      this.messageList = null;
   }

   private boolean typedEquals(MapFieldBuilder other) {
      return MapFieldLite.equals(this.ensureBuilderMap(), other.ensureBuilderMap());
   }

   public boolean equals(Object object) {
      return !(object instanceof MapFieldBuilder) ? false : this.typedEquals((MapFieldBuilder)object);
   }

   public int hashCode() {
      return MapFieldLite.calculateHashCodeForMap(this.ensureBuilderMap());
   }

   public MapFieldBuilder copy() {
      MapFieldBuilder<KeyT, MessageOrBuilderT, MessageT, BuilderT> clone = new MapFieldBuilder(this.converter);
      clone.ensureBuilderMap().putAll(this.ensureBuilderMap());
      return clone;
   }

   public MapField build(MapEntry defaultEntry) {
      MapField<KeyT, MessageT> mapField = MapField.newMapField(defaultEntry);
      Map<KeyT, MessageT> map = mapField.getMutableMap();

      for(Map.Entry entry : this.ensureBuilderMap().entrySet()) {
         map.put(entry.getKey(), this.converter.build((MessageOrBuilder)entry.getValue()));
      }

      mapField.makeImmutable();
      return mapField;
   }

   List getList() {
      return this.ensureMessageList();
   }

   List getMutableList() {
      return this.ensureMessageList();
   }

   Message getMapEntryMessageDefaultInstance() {
      return this.converter.defaultEntry();
   }

   public interface Converter {
      MessageOrBuilder build(MessageOrBuilder val);

      MapEntry defaultEntry();
   }
}
