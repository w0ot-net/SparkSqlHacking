package org.sparkproject.spark_core.protobuf;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public final class MapEntry extends AbstractMessage {
   private final Object key;
   private final Object value;
   private final Metadata metadata;
   private volatile int cachedSerializedSize;

   private MapEntry(Descriptors.Descriptor descriptor, WireFormat.FieldType keyType, Object defaultKey, WireFormat.FieldType valueType, Object defaultValue) {
      this.cachedSerializedSize = -1;
      this.key = defaultKey;
      this.value = defaultValue;
      this.metadata = new Metadata(descriptor, this, keyType, valueType);
   }

   private MapEntry(Metadata metadata, Object key, Object value) {
      this.cachedSerializedSize = -1;
      this.key = key;
      this.value = value;
      this.metadata = metadata;
   }

   private MapEntry(Metadata metadata, CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      this.cachedSerializedSize = -1;

      try {
         this.metadata = metadata;
         Map.Entry<K, V> entry = MapEntryLite.parseEntry(input, metadata, extensionRegistry);
         this.key = entry.getKey();
         this.value = entry.getValue();
      } catch (InvalidProtocolBufferException e) {
         throw e.setUnfinishedMessage(this);
      } catch (IOException e) {
         throw (new InvalidProtocolBufferException(e)).setUnfinishedMessage(this);
      }
   }

   public static MapEntry newDefaultInstance(Descriptors.Descriptor descriptor, WireFormat.FieldType keyType, Object defaultKey, WireFormat.FieldType valueType, Object defaultValue) {
      return new MapEntry(descriptor, keyType, defaultKey, valueType, defaultValue);
   }

   public Object getKey() {
      return this.key;
   }

   public Object getValue() {
      return this.value;
   }

   public int getSerializedSize() {
      if (this.cachedSerializedSize != -1) {
         return this.cachedSerializedSize;
      } else {
         int size = MapEntryLite.computeSerializedSize(this.metadata, this.key, this.value);
         this.cachedSerializedSize = size;
         return size;
      }
   }

   public void writeTo(CodedOutputStream output) throws IOException {
      MapEntryLite.writeTo(output, this.metadata, this.key, this.value);
   }

   public boolean isInitialized() {
      return isInitialized(this.metadata, this.value);
   }

   public Parser getParserForType() {
      return this.metadata.parser;
   }

   public Builder newBuilderForType() {
      return new Builder(this.metadata);
   }

   public Builder toBuilder() {
      return new Builder(this.metadata, this.key, this.value, true, true);
   }

   public MapEntry getDefaultInstanceForType() {
      return new MapEntry(this.metadata, this.metadata.defaultKey, this.metadata.defaultValue);
   }

   public Descriptors.Descriptor getDescriptorForType() {
      return this.metadata.descriptor;
   }

   public Map getAllFields() {
      TreeMap<Descriptors.FieldDescriptor, Object> result = new TreeMap();

      for(Descriptors.FieldDescriptor field : this.metadata.descriptor.getFields()) {
         if (this.hasField(field)) {
            result.put(field, this.getField(field));
         }
      }

      return Collections.unmodifiableMap(result);
   }

   private void checkFieldDescriptor(Descriptors.FieldDescriptor field) {
      if (field.getContainingType() != this.metadata.descriptor) {
         throw new RuntimeException("Wrong FieldDescriptor \"" + field.getFullName() + "\" used in message \"" + this.metadata.descriptor.getFullName());
      }
   }

   public boolean hasField(Descriptors.FieldDescriptor field) {
      this.checkFieldDescriptor(field);
      return true;
   }

   public Object getField(Descriptors.FieldDescriptor field) {
      this.checkFieldDescriptor(field);
      Object result = field.getNumber() == 1 ? this.getKey() : this.getValue();
      if (field.getType() == Descriptors.FieldDescriptor.Type.ENUM) {
         result = field.getEnumType().findValueByNumberCreatingIfUnknown((Integer)result);
      }

      return result;
   }

   public int getRepeatedFieldCount(Descriptors.FieldDescriptor field) {
      throw new RuntimeException("There is no repeated field in a map entry message.");
   }

   public Object getRepeatedField(Descriptors.FieldDescriptor field, int index) {
      throw new RuntimeException("There is no repeated field in a map entry message.");
   }

   public UnknownFieldSet getUnknownFields() {
      return UnknownFieldSet.getDefaultInstance();
   }

   private static boolean isInitialized(Metadata metadata, Object value) {
      return metadata.valueType.getJavaType() == WireFormat.JavaType.MESSAGE ? ((MessageLite)value).isInitialized() : true;
   }

   final Metadata getMetadata() {
      return this.metadata;
   }

   private static final class Metadata extends MapEntryLite.Metadata {
      public final Descriptors.Descriptor descriptor;
      public final Parser parser;

      public Metadata(Descriptors.Descriptor descriptor, MapEntry defaultInstance, WireFormat.FieldType keyType, WireFormat.FieldType valueType) {
         super(keyType, defaultInstance.key, valueType, defaultInstance.value);
         this.descriptor = descriptor;
         this.parser = new AbstractParser() {
            public MapEntry parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
               return new MapEntry(Metadata.this, input, extensionRegistry);
            }
         };
      }
   }

   public static class Builder extends AbstractMessage.Builder {
      private final Metadata metadata;
      private Object key;
      private Object value;
      private boolean hasKey;
      private boolean hasValue;

      private Builder(Metadata metadata) {
         this(metadata, metadata.defaultKey, metadata.defaultValue, false, false);
      }

      private Builder(Metadata metadata, Object key, Object value, boolean hasKey, boolean hasValue) {
         this.metadata = metadata;
         this.key = key;
         this.value = value;
         this.hasKey = hasKey;
         this.hasValue = hasValue;
      }

      public Object getKey() {
         return this.key;
      }

      public Object getValue() {
         return this.value;
      }

      public Builder setKey(Object key) {
         this.key = key;
         this.hasKey = true;
         return this;
      }

      public Builder clearKey() {
         this.key = this.metadata.defaultKey;
         this.hasKey = false;
         return this;
      }

      public Builder setValue(Object value) {
         this.value = value;
         this.hasValue = true;
         return this;
      }

      public Builder clearValue() {
         this.value = this.metadata.defaultValue;
         this.hasValue = false;
         return this;
      }

      public MapEntry build() {
         MapEntry<K, V> result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public MapEntry buildPartial() {
         return new MapEntry(this.metadata, this.key, this.value);
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return this.metadata.descriptor;
      }

      private void checkFieldDescriptor(Descriptors.FieldDescriptor field) {
         if (field.getContainingType() != this.metadata.descriptor) {
            throw new RuntimeException("Wrong FieldDescriptor \"" + field.getFullName() + "\" used in message \"" + this.metadata.descriptor.getFullName());
         }
      }

      public Message.Builder newBuilderForField(Descriptors.FieldDescriptor field) {
         this.checkFieldDescriptor(field);
         if (field.getNumber() == 2 && field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
            return ((Message)this.value).newBuilderForType();
         } else {
            throw new RuntimeException("\"" + field.getFullName() + "\" is not a message value field.");
         }
      }

      public Builder setField(Descriptors.FieldDescriptor field, Object value) {
         this.checkFieldDescriptor(field);
         if (value == null) {
            throw new NullPointerException(field.getFullName() + " is null");
         } else {
            if (field.getNumber() == 1) {
               this.setKey(value);
            } else {
               if (field.getType() == Descriptors.FieldDescriptor.Type.ENUM) {
                  value = ((Descriptors.EnumValueDescriptor)value).getNumber();
               } else if (field.getType() == Descriptors.FieldDescriptor.Type.MESSAGE && !this.metadata.defaultValue.getClass().isInstance(value)) {
                  value = ((Message)this.metadata.defaultValue).toBuilder().mergeFrom((Message)value).build();
               }

               this.setValue(value);
            }

            return this;
         }
      }

      public Builder clearField(Descriptors.FieldDescriptor field) {
         this.checkFieldDescriptor(field);
         if (field.getNumber() == 1) {
            this.clearKey();
         } else {
            this.clearValue();
         }

         return this;
      }

      public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
         throw new RuntimeException("There is no repeated field in a map entry message.");
      }

      public Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
         throw new RuntimeException("There is no repeated field in a map entry message.");
      }

      public Builder setUnknownFields(UnknownFieldSet unknownFields) {
         return this;
      }

      public MapEntry getDefaultInstanceForType() {
         return new MapEntry(this.metadata, this.metadata.defaultKey, this.metadata.defaultValue);
      }

      public boolean isInitialized() {
         return MapEntry.isInitialized(this.metadata, this.value);
      }

      public Map getAllFields() {
         TreeMap<Descriptors.FieldDescriptor, Object> result = new TreeMap();

         for(Descriptors.FieldDescriptor field : this.metadata.descriptor.getFields()) {
            if (this.hasField(field)) {
               result.put(field, this.getField(field));
            }
         }

         return Collections.unmodifiableMap(result);
      }

      public boolean hasField(Descriptors.FieldDescriptor field) {
         this.checkFieldDescriptor(field);
         return field.getNumber() == 1 ? this.hasKey : this.hasValue;
      }

      public Object getField(Descriptors.FieldDescriptor field) {
         this.checkFieldDescriptor(field);
         Object result = field.getNumber() == 1 ? this.getKey() : this.getValue();
         if (field.getType() == Descriptors.FieldDescriptor.Type.ENUM) {
            result = field.getEnumType().findValueByNumberCreatingIfUnknown((Integer)result);
         }

         return result;
      }

      public int getRepeatedFieldCount(Descriptors.FieldDescriptor field) {
         throw new RuntimeException("There is no repeated field in a map entry message.");
      }

      public Object getRepeatedField(Descriptors.FieldDescriptor field, int index) {
         throw new RuntimeException("There is no repeated field in a map entry message.");
      }

      public UnknownFieldSet getUnknownFields() {
         return UnknownFieldSet.getDefaultInstance();
      }

      public Builder clone() {
         return new Builder(this.metadata, this.key, this.value, this.hasKey, this.hasValue);
      }
   }
}
