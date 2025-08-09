package org.apache.orc.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Map;

public final class Struct extends GeneratedMessageV3 implements StructOrBuilder {
   private static final long serialVersionUID = 0L;
   public static final int FIELDS_FIELD_NUMBER = 1;
   private MapField fields_;
   private byte memoizedIsInitialized;
   private static final Struct DEFAULT_INSTANCE = new Struct();
   private static final Parser PARSER = new AbstractParser() {
      public Struct parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
         Builder builder = Struct.newBuilder();

         try {
            builder.mergeFrom(input, extensionRegistry);
         } catch (InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(builder.buildPartial());
         } catch (UninitializedMessageException e) {
            throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
         } catch (IOException e) {
            throw (new InvalidProtocolBufferException(e)).setUnfinishedMessage(builder.buildPartial());
         }

         return builder.buildPartial();
      }
   };

   private Struct(GeneratedMessageV3.Builder builder) {
      super(builder);
      this.memoizedIsInitialized = -1;
   }

   private Struct() {
      this.memoizedIsInitialized = -1;
   }

   protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new Struct();
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return StructProto.internal_static_google_protobuf_Struct_descriptor;
   }

   protected MapFieldReflectionAccessor internalGetMapFieldReflection(int number) {
      switch (number) {
         case 1:
            return this.internalGetFields();
         default:
            throw new RuntimeException("Invalid map field number: " + number);
      }
   }

   protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return StructProto.internal_static_google_protobuf_Struct_fieldAccessorTable.ensureFieldAccessorsInitialized(Struct.class, Builder.class);
   }

   private MapField internalGetFields() {
      return this.fields_ == null ? MapField.emptyMapField(Struct.FieldsDefaultEntryHolder.defaultEntry) : this.fields_;
   }

   public int getFieldsCount() {
      return this.internalGetFields().getMap().size();
   }

   public boolean containsFields(String key) {
      if (key == null) {
         throw new NullPointerException("map key");
      } else {
         return this.internalGetFields().getMap().containsKey(key);
      }
   }

   /** @deprecated */
   @Deprecated
   public Map getFields() {
      return this.getFieldsMap();
   }

   public Map getFieldsMap() {
      return this.internalGetFields().getMap();
   }

   public Value getFieldsOrDefault(String key, Value defaultValue) {
      if (key == null) {
         throw new NullPointerException("map key");
      } else {
         Map<String, Value> map = this.internalGetFields().getMap();
         return map.containsKey(key) ? (Value)map.get(key) : defaultValue;
      }
   }

   public Value getFieldsOrThrow(String key) {
      if (key == null) {
         throw new NullPointerException("map key");
      } else {
         Map<String, Value> map = this.internalGetFields().getMap();
         if (!map.containsKey(key)) {
            throw new IllegalArgumentException();
         } else {
            return (Value)map.get(key);
         }
      }
   }

   public final boolean isInitialized() {
      byte isInitialized = this.memoizedIsInitialized;
      if (isInitialized == 1) {
         return true;
      } else if (isInitialized == 0) {
         return false;
      } else {
         this.memoizedIsInitialized = 1;
         return true;
      }
   }

   public void writeTo(CodedOutputStream output) throws IOException {
      GeneratedMessageV3.serializeStringMapTo(output, this.internalGetFields(), Struct.FieldsDefaultEntryHolder.defaultEntry, 1);
      this.getUnknownFields().writeTo(output);
   }

   public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1) {
         return size;
      } else {
         size = 0;

         for(Map.Entry entry : this.internalGetFields().getMap().entrySet()) {
            MapEntry<String, Value> fields__ = Struct.FieldsDefaultEntryHolder.defaultEntry.newBuilderForType().setKey((String)entry.getKey()).setValue((Value)entry.getValue()).build();
            size += CodedOutputStream.computeMessageSize(1, fields__);
         }

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof Struct)) {
         return super.equals(obj);
      } else {
         Struct other = (Struct)obj;
         if (!this.internalGetFields().equals(other.internalGetFields())) {
            return false;
         } else {
            return this.getUnknownFields().equals(other.getUnknownFields());
         }
      }
   }

   public int hashCode() {
      if (this.memoizedHashCode != 0) {
         return this.memoizedHashCode;
      } else {
         int hash = 41;
         hash = 19 * hash + getDescriptor().hashCode();
         if (!this.internalGetFields().getMap().isEmpty()) {
            hash = 37 * hash + 1;
            hash = 53 * hash + this.internalGetFields().hashCode();
         }

         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static Struct parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (Struct)PARSER.parseFrom(data);
   }

   public static Struct parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Struct)PARSER.parseFrom(data, extensionRegistry);
   }

   public static Struct parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (Struct)PARSER.parseFrom(data);
   }

   public static Struct parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Struct)PARSER.parseFrom(data, extensionRegistry);
   }

   public static Struct parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (Struct)PARSER.parseFrom(data);
   }

   public static Struct parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Struct)PARSER.parseFrom(data, extensionRegistry);
   }

   public static Struct parseFrom(InputStream input) throws IOException {
      return (Struct)GeneratedMessageV3.parseWithIOException(PARSER, input);
   }

   public static Struct parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (Struct)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static Struct parseDelimitedFrom(InputStream input) throws IOException {
      return (Struct)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
   }

   public static Struct parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (Struct)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static Struct parseFrom(CodedInputStream input) throws IOException {
      return (Struct)GeneratedMessageV3.parseWithIOException(PARSER, input);
   }

   public static Struct parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (Struct)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(Struct prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static Struct getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public Struct getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   private static final class FieldsDefaultEntryHolder {
      static final MapEntry defaultEntry;

      static {
         defaultEntry = MapEntry.newDefaultInstance(StructProto.internal_static_google_protobuf_Struct_FieldsEntry_descriptor, WireFormat.FieldType.STRING, "", WireFormat.FieldType.MESSAGE, Value.getDefaultInstance());
      }
   }

   public static final class Builder extends GeneratedMessageV3.Builder implements StructOrBuilder {
      private int bitField0_;
      private static final FieldsConverter fieldsConverter = new FieldsConverter();
      private MapFieldBuilder fields_;

      public static final Descriptors.Descriptor getDescriptor() {
         return StructProto.internal_static_google_protobuf_Struct_descriptor;
      }

      protected MapFieldReflectionAccessor internalGetMapFieldReflection(int number) {
         switch (number) {
            case 1:
               return this.internalGetFields();
            default:
               throw new RuntimeException("Invalid map field number: " + number);
         }
      }

      protected MapFieldReflectionAccessor internalGetMutableMapFieldReflection(int number) {
         switch (number) {
            case 1:
               return this.internalGetMutableFields();
            default:
               throw new RuntimeException("Invalid map field number: " + number);
         }
      }

      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
         return StructProto.internal_static_google_protobuf_Struct_fieldAccessorTable.ensureFieldAccessorsInitialized(Struct.class, Builder.class);
      }

      private Builder() {
      }

      private Builder(GeneratedMessageV3.BuilderParent parent) {
         super(parent);
      }

      public Builder clear() {
         super.clear();
         this.bitField0_ = 0;
         this.internalGetMutableFields().clear();
         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return StructProto.internal_static_google_protobuf_Struct_descriptor;
      }

      public Struct getDefaultInstanceForType() {
         return Struct.getDefaultInstance();
      }

      public Struct build() {
         Struct result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public Struct buildPartial() {
         Struct result = new Struct(this);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartial0(Struct result) {
         int from_bitField0_ = this.bitField0_;
         if ((from_bitField0_ & 1) != 0) {
            result.fields_ = this.internalGetFields().build(Struct.FieldsDefaultEntryHolder.defaultEntry);
         }

      }

      public Builder clone() {
         return (Builder)super.clone();
      }

      public Builder setField(Descriptors.FieldDescriptor field, Object value) {
         return (Builder)super.setField(field, value);
      }

      public Builder clearField(Descriptors.FieldDescriptor field) {
         return (Builder)super.clearField(field);
      }

      public Builder clearOneof(Descriptors.OneofDescriptor oneof) {
         return (Builder)super.clearOneof(oneof);
      }

      public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
         return (Builder)super.setRepeatedField(field, index, value);
      }

      public Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
         return (Builder)super.addRepeatedField(field, value);
      }

      public Builder mergeFrom(Message other) {
         if (other instanceof Struct) {
            return this.mergeFrom((Struct)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(Struct other) {
         if (other == Struct.getDefaultInstance()) {
            return this;
         } else {
            this.internalGetMutableFields().mergeFrom(other.internalGetFields());
            this.bitField0_ |= 1;
            this.mergeUnknownFields(other.getUnknownFields());
            this.onChanged();
            return this;
         }
      }

      public final boolean isInitialized() {
         return true;
      }

      public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
         if (extensionRegistry == null) {
            throw new NullPointerException();
         } else {
            try {
               boolean done = false;

               while(!done) {
                  int tag = input.readTag();
                  switch (tag) {
                     case 0:
                        done = true;
                        break;
                     case 10:
                        MapEntry<String, Value> fields__ = (MapEntry)input.readMessage(Struct.FieldsDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistry);
                        this.internalGetMutableFields().ensureBuilderMap().put((String)fields__.getKey(), (ValueOrBuilder)fields__.getValue());
                        this.bitField0_ |= 1;
                        break;
                     default:
                        if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                           done = true;
                        }
                  }
               }
            } catch (InvalidProtocolBufferException e) {
               throw e.unwrapIOException();
            } finally {
               this.onChanged();
            }

            return this;
         }
      }

      private MapFieldBuilder internalGetFields() {
         return this.fields_ == null ? new MapFieldBuilder(fieldsConverter) : this.fields_;
      }

      private MapFieldBuilder internalGetMutableFields() {
         if (this.fields_ == null) {
            this.fields_ = new MapFieldBuilder(fieldsConverter);
         }

         this.bitField0_ |= 1;
         this.onChanged();
         return this.fields_;
      }

      public int getFieldsCount() {
         return this.internalGetFields().ensureBuilderMap().size();
      }

      public boolean containsFields(String key) {
         if (key == null) {
            throw new NullPointerException("map key");
         } else {
            return this.internalGetFields().ensureBuilderMap().containsKey(key);
         }
      }

      /** @deprecated */
      @Deprecated
      public Map getFields() {
         return this.getFieldsMap();
      }

      public Map getFieldsMap() {
         return this.internalGetFields().getImmutableMap();
      }

      public Value getFieldsOrDefault(String key, Value defaultValue) {
         if (key == null) {
            throw new NullPointerException("map key");
         } else {
            Map<String, ValueOrBuilder> map = this.internalGetMutableFields().ensureBuilderMap();
            return map.containsKey(key) ? fieldsConverter.build((ValueOrBuilder)map.get(key)) : defaultValue;
         }
      }

      public Value getFieldsOrThrow(String key) {
         if (key == null) {
            throw new NullPointerException("map key");
         } else {
            Map<String, ValueOrBuilder> map = this.internalGetMutableFields().ensureBuilderMap();
            if (!map.containsKey(key)) {
               throw new IllegalArgumentException();
            } else {
               return fieldsConverter.build((ValueOrBuilder)map.get(key));
            }
         }
      }

      public Builder clearFields() {
         this.bitField0_ &= -2;
         this.internalGetMutableFields().clear();
         return this;
      }

      public Builder removeFields(String key) {
         if (key == null) {
            throw new NullPointerException("map key");
         } else {
            this.internalGetMutableFields().ensureBuilderMap().remove(key);
            return this;
         }
      }

      /** @deprecated */
      @Deprecated
      public Map getMutableFields() {
         this.bitField0_ |= 1;
         return this.internalGetMutableFields().ensureMessageMap();
      }

      public Builder putFields(String key, Value value) {
         if (key == null) {
            throw new NullPointerException("map key");
         } else if (value == null) {
            throw new NullPointerException("map value");
         } else {
            this.internalGetMutableFields().ensureBuilderMap().put(key, value);
            this.bitField0_ |= 1;
            return this;
         }
      }

      public Builder putAllFields(Map values) {
         for(Map.Entry e : values.entrySet()) {
            if (e.getKey() == null || e.getValue() == null) {
               throw new NullPointerException();
            }
         }

         this.internalGetMutableFields().ensureBuilderMap().putAll(values);
         this.bitField0_ |= 1;
         return this;
      }

      public Value.Builder putFieldsBuilderIfAbsent(String key) {
         Map<String, ValueOrBuilder> builderMap = this.internalGetMutableFields().ensureBuilderMap();
         ValueOrBuilder entry = (ValueOrBuilder)builderMap.get(key);
         if (entry == null) {
            entry = Value.newBuilder();
            builderMap.put(key, entry);
         }

         if (entry instanceof Value) {
            entry = ((Value)entry).toBuilder();
            builderMap.put(key, entry);
         }

         return (Value.Builder)entry;
      }

      public final Builder setUnknownFields(final UnknownFieldSet unknownFields) {
         return (Builder)super.setUnknownFields(unknownFields);
      }

      public final Builder mergeUnknownFields(final UnknownFieldSet unknownFields) {
         return (Builder)super.mergeUnknownFields(unknownFields);
      }

      private static final class FieldsConverter implements MapFieldBuilder.Converter {
         private FieldsConverter() {
         }

         public Value build(ValueOrBuilder val) {
            return val instanceof Value ? (Value)val : ((Value.Builder)val).build();
         }

         public MapEntry defaultEntry() {
            return Struct.FieldsDefaultEntryHolder.defaultEntry;
         }
      }
   }
}
