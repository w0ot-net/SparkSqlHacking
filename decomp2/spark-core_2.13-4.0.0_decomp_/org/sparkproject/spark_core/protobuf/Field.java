package org.sparkproject.spark_core.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Field extends GeneratedMessage implements FieldOrBuilder {
   private static final long serialVersionUID = 0L;
   public static final int KIND_FIELD_NUMBER = 1;
   private int kind_;
   public static final int CARDINALITY_FIELD_NUMBER = 2;
   private int cardinality_;
   public static final int NUMBER_FIELD_NUMBER = 3;
   private int number_;
   public static final int NAME_FIELD_NUMBER = 4;
   private volatile Object name_;
   public static final int TYPE_URL_FIELD_NUMBER = 6;
   private volatile Object typeUrl_;
   public static final int ONEOF_INDEX_FIELD_NUMBER = 7;
   private int oneofIndex_;
   public static final int PACKED_FIELD_NUMBER = 8;
   private boolean packed_;
   public static final int OPTIONS_FIELD_NUMBER = 9;
   private List options_;
   public static final int JSON_NAME_FIELD_NUMBER = 10;
   private volatile Object jsonName_;
   public static final int DEFAULT_VALUE_FIELD_NUMBER = 11;
   private volatile Object defaultValue_;
   private byte memoizedIsInitialized;
   private static final Field DEFAULT_INSTANCE;
   private static final Parser PARSER;

   private Field(GeneratedMessage.Builder builder) {
      super(builder);
      this.kind_ = 0;
      this.cardinality_ = 0;
      this.number_ = 0;
      this.name_ = "";
      this.typeUrl_ = "";
      this.oneofIndex_ = 0;
      this.packed_ = false;
      this.jsonName_ = "";
      this.defaultValue_ = "";
      this.memoizedIsInitialized = -1;
   }

   private Field() {
      this.kind_ = 0;
      this.cardinality_ = 0;
      this.number_ = 0;
      this.name_ = "";
      this.typeUrl_ = "";
      this.oneofIndex_ = 0;
      this.packed_ = false;
      this.jsonName_ = "";
      this.defaultValue_ = "";
      this.memoizedIsInitialized = -1;
      this.kind_ = 0;
      this.cardinality_ = 0;
      this.name_ = "";
      this.typeUrl_ = "";
      this.options_ = Collections.emptyList();
      this.jsonName_ = "";
      this.defaultValue_ = "";
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return TypeProto.internal_static_google_protobuf_Field_descriptor;
   }

   protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
      return TypeProto.internal_static_google_protobuf_Field_fieldAccessorTable.ensureFieldAccessorsInitialized(Field.class, Builder.class);
   }

   public int getKindValue() {
      return this.kind_;
   }

   public Kind getKind() {
      Kind result = Field.Kind.forNumber(this.kind_);
      return result == null ? Field.Kind.UNRECOGNIZED : result;
   }

   public int getCardinalityValue() {
      return this.cardinality_;
   }

   public Cardinality getCardinality() {
      Cardinality result = Field.Cardinality.forNumber(this.cardinality_);
      return result == null ? Field.Cardinality.UNRECOGNIZED : result;
   }

   public int getNumber() {
      return this.number_;
   }

   public String getName() {
      Object ref = this.name_;
      if (ref instanceof String) {
         return (String)ref;
      } else {
         ByteString bs = (ByteString)ref;
         String s = bs.toStringUtf8();
         this.name_ = s;
         return s;
      }
   }

   public ByteString getNameBytes() {
      Object ref = this.name_;
      if (ref instanceof String) {
         ByteString b = ByteString.copyFromUtf8((String)ref);
         this.name_ = b;
         return b;
      } else {
         return (ByteString)ref;
      }
   }

   public String getTypeUrl() {
      Object ref = this.typeUrl_;
      if (ref instanceof String) {
         return (String)ref;
      } else {
         ByteString bs = (ByteString)ref;
         String s = bs.toStringUtf8();
         this.typeUrl_ = s;
         return s;
      }
   }

   public ByteString getTypeUrlBytes() {
      Object ref = this.typeUrl_;
      if (ref instanceof String) {
         ByteString b = ByteString.copyFromUtf8((String)ref);
         this.typeUrl_ = b;
         return b;
      } else {
         return (ByteString)ref;
      }
   }

   public int getOneofIndex() {
      return this.oneofIndex_;
   }

   public boolean getPacked() {
      return this.packed_;
   }

   public List getOptionsList() {
      return this.options_;
   }

   public List getOptionsOrBuilderList() {
      return this.options_;
   }

   public int getOptionsCount() {
      return this.options_.size();
   }

   public Option getOptions(int index) {
      return (Option)this.options_.get(index);
   }

   public OptionOrBuilder getOptionsOrBuilder(int index) {
      return (OptionOrBuilder)this.options_.get(index);
   }

   public String getJsonName() {
      Object ref = this.jsonName_;
      if (ref instanceof String) {
         return (String)ref;
      } else {
         ByteString bs = (ByteString)ref;
         String s = bs.toStringUtf8();
         this.jsonName_ = s;
         return s;
      }
   }

   public ByteString getJsonNameBytes() {
      Object ref = this.jsonName_;
      if (ref instanceof String) {
         ByteString b = ByteString.copyFromUtf8((String)ref);
         this.jsonName_ = b;
         return b;
      } else {
         return (ByteString)ref;
      }
   }

   public String getDefaultValue() {
      Object ref = this.defaultValue_;
      if (ref instanceof String) {
         return (String)ref;
      } else {
         ByteString bs = (ByteString)ref;
         String s = bs.toStringUtf8();
         this.defaultValue_ = s;
         return s;
      }
   }

   public ByteString getDefaultValueBytes() {
      Object ref = this.defaultValue_;
      if (ref instanceof String) {
         ByteString b = ByteString.copyFromUtf8((String)ref);
         this.defaultValue_ = b;
         return b;
      } else {
         return (ByteString)ref;
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
      if (this.kind_ != Field.Kind.TYPE_UNKNOWN.getNumber()) {
         output.writeEnum(1, this.kind_);
      }

      if (this.cardinality_ != Field.Cardinality.CARDINALITY_UNKNOWN.getNumber()) {
         output.writeEnum(2, this.cardinality_);
      }

      if (this.number_ != 0) {
         output.writeInt32(3, this.number_);
      }

      if (!GeneratedMessage.isStringEmpty(this.name_)) {
         GeneratedMessage.writeString(output, 4, this.name_);
      }

      if (!GeneratedMessage.isStringEmpty(this.typeUrl_)) {
         GeneratedMessage.writeString(output, 6, this.typeUrl_);
      }

      if (this.oneofIndex_ != 0) {
         output.writeInt32(7, this.oneofIndex_);
      }

      if (this.packed_) {
         output.writeBool(8, this.packed_);
      }

      for(int i = 0; i < this.options_.size(); ++i) {
         output.writeMessage(9, (MessageLite)this.options_.get(i));
      }

      if (!GeneratedMessage.isStringEmpty(this.jsonName_)) {
         GeneratedMessage.writeString(output, 10, this.jsonName_);
      }

      if (!GeneratedMessage.isStringEmpty(this.defaultValue_)) {
         GeneratedMessage.writeString(output, 11, this.defaultValue_);
      }

      this.getUnknownFields().writeTo(output);
   }

   public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1) {
         return size;
      } else {
         size = 0;
         if (this.kind_ != Field.Kind.TYPE_UNKNOWN.getNumber()) {
            size += CodedOutputStream.computeEnumSize(1, this.kind_);
         }

         if (this.cardinality_ != Field.Cardinality.CARDINALITY_UNKNOWN.getNumber()) {
            size += CodedOutputStream.computeEnumSize(2, this.cardinality_);
         }

         if (this.number_ != 0) {
            size += CodedOutputStream.computeInt32Size(3, this.number_);
         }

         if (!GeneratedMessage.isStringEmpty(this.name_)) {
            size += GeneratedMessage.computeStringSize(4, this.name_);
         }

         if (!GeneratedMessage.isStringEmpty(this.typeUrl_)) {
            size += GeneratedMessage.computeStringSize(6, this.typeUrl_);
         }

         if (this.oneofIndex_ != 0) {
            size += CodedOutputStream.computeInt32Size(7, this.oneofIndex_);
         }

         if (this.packed_) {
            size += CodedOutputStream.computeBoolSize(8, this.packed_);
         }

         for(int i = 0; i < this.options_.size(); ++i) {
            size += CodedOutputStream.computeMessageSize(9, (MessageLite)this.options_.get(i));
         }

         if (!GeneratedMessage.isStringEmpty(this.jsonName_)) {
            size += GeneratedMessage.computeStringSize(10, this.jsonName_);
         }

         if (!GeneratedMessage.isStringEmpty(this.defaultValue_)) {
            size += GeneratedMessage.computeStringSize(11, this.defaultValue_);
         }

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof Field)) {
         return super.equals(obj);
      } else {
         Field other = (Field)obj;
         if (this.kind_ != other.kind_) {
            return false;
         } else if (this.cardinality_ != other.cardinality_) {
            return false;
         } else if (this.getNumber() != other.getNumber()) {
            return false;
         } else if (!this.getName().equals(other.getName())) {
            return false;
         } else if (!this.getTypeUrl().equals(other.getTypeUrl())) {
            return false;
         } else if (this.getOneofIndex() != other.getOneofIndex()) {
            return false;
         } else if (this.getPacked() != other.getPacked()) {
            return false;
         } else if (!this.getOptionsList().equals(other.getOptionsList())) {
            return false;
         } else if (!this.getJsonName().equals(other.getJsonName())) {
            return false;
         } else if (!this.getDefaultValue().equals(other.getDefaultValue())) {
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
         hash = 37 * hash + 1;
         hash = 53 * hash + this.kind_;
         hash = 37 * hash + 2;
         hash = 53 * hash + this.cardinality_;
         hash = 37 * hash + 3;
         hash = 53 * hash + this.getNumber();
         hash = 37 * hash + 4;
         hash = 53 * hash + this.getName().hashCode();
         hash = 37 * hash + 6;
         hash = 53 * hash + this.getTypeUrl().hashCode();
         hash = 37 * hash + 7;
         hash = 53 * hash + this.getOneofIndex();
         hash = 37 * hash + 8;
         hash = 53 * hash + Internal.hashBoolean(this.getPacked());
         if (this.getOptionsCount() > 0) {
            hash = 37 * hash + 9;
            hash = 53 * hash + this.getOptionsList().hashCode();
         }

         hash = 37 * hash + 10;
         hash = 53 * hash + this.getJsonName().hashCode();
         hash = 37 * hash + 11;
         hash = 53 * hash + this.getDefaultValue().hashCode();
         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static Field parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (Field)PARSER.parseFrom(data);
   }

   public static Field parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Field)PARSER.parseFrom(data, extensionRegistry);
   }

   public static Field parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (Field)PARSER.parseFrom(data);
   }

   public static Field parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Field)PARSER.parseFrom(data, extensionRegistry);
   }

   public static Field parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (Field)PARSER.parseFrom(data);
   }

   public static Field parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Field)PARSER.parseFrom(data, extensionRegistry);
   }

   public static Field parseFrom(InputStream input) throws IOException {
      return (Field)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static Field parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (Field)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static Field parseDelimitedFrom(InputStream input) throws IOException {
      return (Field)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
   }

   public static Field parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (Field)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static Field parseFrom(CodedInputStream input) throws IOException {
      return (Field)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static Field parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (Field)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(Field prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static Field getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public Field getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeVersion.RuntimeDomain.PUBLIC, 4, 29, 3, "", Field.class.getName());
      DEFAULT_INSTANCE = new Field();
      PARSER = new AbstractParser() {
         public Field parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            Builder builder = Field.newBuilder();

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
   }

   public static enum Kind implements ProtocolMessageEnum {
      TYPE_UNKNOWN(0),
      TYPE_DOUBLE(1),
      TYPE_FLOAT(2),
      TYPE_INT64(3),
      TYPE_UINT64(4),
      TYPE_INT32(5),
      TYPE_FIXED64(6),
      TYPE_FIXED32(7),
      TYPE_BOOL(8),
      TYPE_STRING(9),
      TYPE_GROUP(10),
      TYPE_MESSAGE(11),
      TYPE_BYTES(12),
      TYPE_UINT32(13),
      TYPE_ENUM(14),
      TYPE_SFIXED32(15),
      TYPE_SFIXED64(16),
      TYPE_SINT32(17),
      TYPE_SINT64(18),
      UNRECOGNIZED(-1);

      public static final int TYPE_UNKNOWN_VALUE = 0;
      public static final int TYPE_DOUBLE_VALUE = 1;
      public static final int TYPE_FLOAT_VALUE = 2;
      public static final int TYPE_INT64_VALUE = 3;
      public static final int TYPE_UINT64_VALUE = 4;
      public static final int TYPE_INT32_VALUE = 5;
      public static final int TYPE_FIXED64_VALUE = 6;
      public static final int TYPE_FIXED32_VALUE = 7;
      public static final int TYPE_BOOL_VALUE = 8;
      public static final int TYPE_STRING_VALUE = 9;
      public static final int TYPE_GROUP_VALUE = 10;
      public static final int TYPE_MESSAGE_VALUE = 11;
      public static final int TYPE_BYTES_VALUE = 12;
      public static final int TYPE_UINT32_VALUE = 13;
      public static final int TYPE_ENUM_VALUE = 14;
      public static final int TYPE_SFIXED32_VALUE = 15;
      public static final int TYPE_SFIXED64_VALUE = 16;
      public static final int TYPE_SINT32_VALUE = 17;
      public static final int TYPE_SINT64_VALUE = 18;
      private static final Internal.EnumLiteMap internalValueMap;
      private static final Kind[] VALUES;
      private final int value;

      public final int getNumber() {
         if (this == UNRECOGNIZED) {
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
         } else {
            return this.value;
         }
      }

      /** @deprecated */
      @Deprecated
      public static Kind valueOf(int value) {
         return forNumber(value);
      }

      public static Kind forNumber(int value) {
         switch (value) {
            case 0:
               return TYPE_UNKNOWN;
            case 1:
               return TYPE_DOUBLE;
            case 2:
               return TYPE_FLOAT;
            case 3:
               return TYPE_INT64;
            case 4:
               return TYPE_UINT64;
            case 5:
               return TYPE_INT32;
            case 6:
               return TYPE_FIXED64;
            case 7:
               return TYPE_FIXED32;
            case 8:
               return TYPE_BOOL;
            case 9:
               return TYPE_STRING;
            case 10:
               return TYPE_GROUP;
            case 11:
               return TYPE_MESSAGE;
            case 12:
               return TYPE_BYTES;
            case 13:
               return TYPE_UINT32;
            case 14:
               return TYPE_ENUM;
            case 15:
               return TYPE_SFIXED32;
            case 16:
               return TYPE_SFIXED64;
            case 17:
               return TYPE_SINT32;
            case 18:
               return TYPE_SINT64;
            default:
               return null;
         }
      }

      public static Internal.EnumLiteMap internalGetValueMap() {
         return internalValueMap;
      }

      public final Descriptors.EnumValueDescriptor getValueDescriptor() {
         if (this == UNRECOGNIZED) {
            throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
         } else {
            return (Descriptors.EnumValueDescriptor)getDescriptor().getValues().get(this.ordinal());
         }
      }

      public final Descriptors.EnumDescriptor getDescriptorForType() {
         return getDescriptor();
      }

      public static final Descriptors.EnumDescriptor getDescriptor() {
         return (Descriptors.EnumDescriptor)Field.getDescriptor().getEnumTypes().get(0);
      }

      public static Kind valueOf(Descriptors.EnumValueDescriptor desc) {
         if (desc.getType() != getDescriptor()) {
            throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
         } else {
            return desc.getIndex() == -1 ? UNRECOGNIZED : VALUES[desc.getIndex()];
         }
      }

      private Kind(int value) {
         this.value = value;
      }

      // $FF: synthetic method
      private static Kind[] $values() {
         return new Kind[]{TYPE_UNKNOWN, TYPE_DOUBLE, TYPE_FLOAT, TYPE_INT64, TYPE_UINT64, TYPE_INT32, TYPE_FIXED64, TYPE_FIXED32, TYPE_BOOL, TYPE_STRING, TYPE_GROUP, TYPE_MESSAGE, TYPE_BYTES, TYPE_UINT32, TYPE_ENUM, TYPE_SFIXED32, TYPE_SFIXED64, TYPE_SINT32, TYPE_SINT64, UNRECOGNIZED};
      }

      static {
         RuntimeVersion.validateProtobufGencodeVersion(RuntimeVersion.RuntimeDomain.PUBLIC, 4, 29, 3, "", Kind.class.getName());
         internalValueMap = new Internal.EnumLiteMap() {
            public Kind findValueByNumber(int number) {
               return Field.Kind.forNumber(number);
            }
         };
         VALUES = values();
      }
   }

   public static enum Cardinality implements ProtocolMessageEnum {
      CARDINALITY_UNKNOWN(0),
      CARDINALITY_OPTIONAL(1),
      CARDINALITY_REQUIRED(2),
      CARDINALITY_REPEATED(3),
      UNRECOGNIZED(-1);

      public static final int CARDINALITY_UNKNOWN_VALUE = 0;
      public static final int CARDINALITY_OPTIONAL_VALUE = 1;
      public static final int CARDINALITY_REQUIRED_VALUE = 2;
      public static final int CARDINALITY_REPEATED_VALUE = 3;
      private static final Internal.EnumLiteMap internalValueMap;
      private static final Cardinality[] VALUES;
      private final int value;

      public final int getNumber() {
         if (this == UNRECOGNIZED) {
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
         } else {
            return this.value;
         }
      }

      /** @deprecated */
      @Deprecated
      public static Cardinality valueOf(int value) {
         return forNumber(value);
      }

      public static Cardinality forNumber(int value) {
         switch (value) {
            case 0:
               return CARDINALITY_UNKNOWN;
            case 1:
               return CARDINALITY_OPTIONAL;
            case 2:
               return CARDINALITY_REQUIRED;
            case 3:
               return CARDINALITY_REPEATED;
            default:
               return null;
         }
      }

      public static Internal.EnumLiteMap internalGetValueMap() {
         return internalValueMap;
      }

      public final Descriptors.EnumValueDescriptor getValueDescriptor() {
         if (this == UNRECOGNIZED) {
            throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
         } else {
            return (Descriptors.EnumValueDescriptor)getDescriptor().getValues().get(this.ordinal());
         }
      }

      public final Descriptors.EnumDescriptor getDescriptorForType() {
         return getDescriptor();
      }

      public static final Descriptors.EnumDescriptor getDescriptor() {
         return (Descriptors.EnumDescriptor)Field.getDescriptor().getEnumTypes().get(1);
      }

      public static Cardinality valueOf(Descriptors.EnumValueDescriptor desc) {
         if (desc.getType() != getDescriptor()) {
            throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
         } else {
            return desc.getIndex() == -1 ? UNRECOGNIZED : VALUES[desc.getIndex()];
         }
      }

      private Cardinality(int value) {
         this.value = value;
      }

      // $FF: synthetic method
      private static Cardinality[] $values() {
         return new Cardinality[]{CARDINALITY_UNKNOWN, CARDINALITY_OPTIONAL, CARDINALITY_REQUIRED, CARDINALITY_REPEATED, UNRECOGNIZED};
      }

      static {
         RuntimeVersion.validateProtobufGencodeVersion(RuntimeVersion.RuntimeDomain.PUBLIC, 4, 29, 3, "", Cardinality.class.getName());
         internalValueMap = new Internal.EnumLiteMap() {
            public Cardinality findValueByNumber(int number) {
               return Field.Cardinality.forNumber(number);
            }
         };
         VALUES = values();
      }
   }

   public static final class Builder extends GeneratedMessage.Builder implements FieldOrBuilder {
      private int bitField0_;
      private int kind_;
      private int cardinality_;
      private int number_;
      private Object name_;
      private Object typeUrl_;
      private int oneofIndex_;
      private boolean packed_;
      private List options_;
      private RepeatedFieldBuilder optionsBuilder_;
      private Object jsonName_;
      private Object defaultValue_;

      public static final Descriptors.Descriptor getDescriptor() {
         return TypeProto.internal_static_google_protobuf_Field_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return TypeProto.internal_static_google_protobuf_Field_fieldAccessorTable.ensureFieldAccessorsInitialized(Field.class, Builder.class);
      }

      private Builder() {
         this.kind_ = 0;
         this.cardinality_ = 0;
         this.name_ = "";
         this.typeUrl_ = "";
         this.options_ = Collections.emptyList();
         this.jsonName_ = "";
         this.defaultValue_ = "";
      }

      private Builder(AbstractMessage.BuilderParent parent) {
         super(parent);
         this.kind_ = 0;
         this.cardinality_ = 0;
         this.name_ = "";
         this.typeUrl_ = "";
         this.options_ = Collections.emptyList();
         this.jsonName_ = "";
         this.defaultValue_ = "";
      }

      public Builder clear() {
         super.clear();
         this.bitField0_ = 0;
         this.kind_ = 0;
         this.cardinality_ = 0;
         this.number_ = 0;
         this.name_ = "";
         this.typeUrl_ = "";
         this.oneofIndex_ = 0;
         this.packed_ = false;
         if (this.optionsBuilder_ == null) {
            this.options_ = Collections.emptyList();
         } else {
            this.options_ = null;
            this.optionsBuilder_.clear();
         }

         this.bitField0_ &= -129;
         this.jsonName_ = "";
         this.defaultValue_ = "";
         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return TypeProto.internal_static_google_protobuf_Field_descriptor;
      }

      public Field getDefaultInstanceForType() {
         return Field.getDefaultInstance();
      }

      public Field build() {
         Field result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public Field buildPartial() {
         Field result = new Field(this);
         this.buildPartialRepeatedFields(result);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartialRepeatedFields(Field result) {
         if (this.optionsBuilder_ == null) {
            if ((this.bitField0_ & 128) != 0) {
               this.options_ = Collections.unmodifiableList(this.options_);
               this.bitField0_ &= -129;
            }

            result.options_ = this.options_;
         } else {
            result.options_ = this.optionsBuilder_.build();
         }

      }

      private void buildPartial0(Field result) {
         int from_bitField0_ = this.bitField0_;
         if ((from_bitField0_ & 1) != 0) {
            result.kind_ = this.kind_;
         }

         if ((from_bitField0_ & 2) != 0) {
            result.cardinality_ = this.cardinality_;
         }

         if ((from_bitField0_ & 4) != 0) {
            result.number_ = this.number_;
         }

         if ((from_bitField0_ & 8) != 0) {
            result.name_ = this.name_;
         }

         if ((from_bitField0_ & 16) != 0) {
            result.typeUrl_ = this.typeUrl_;
         }

         if ((from_bitField0_ & 32) != 0) {
            result.oneofIndex_ = this.oneofIndex_;
         }

         if ((from_bitField0_ & 64) != 0) {
            result.packed_ = this.packed_;
         }

         if ((from_bitField0_ & 256) != 0) {
            result.jsonName_ = this.jsonName_;
         }

         if ((from_bitField0_ & 512) != 0) {
            result.defaultValue_ = this.defaultValue_;
         }

      }

      public Builder mergeFrom(Message other) {
         if (other instanceof Field) {
            return this.mergeFrom((Field)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(Field other) {
         if (other == Field.getDefaultInstance()) {
            return this;
         } else {
            if (other.kind_ != 0) {
               this.setKindValue(other.getKindValue());
            }

            if (other.cardinality_ != 0) {
               this.setCardinalityValue(other.getCardinalityValue());
            }

            if (other.getNumber() != 0) {
               this.setNumber(other.getNumber());
            }

            if (!other.getName().isEmpty()) {
               this.name_ = other.name_;
               this.bitField0_ |= 8;
               this.onChanged();
            }

            if (!other.getTypeUrl().isEmpty()) {
               this.typeUrl_ = other.typeUrl_;
               this.bitField0_ |= 16;
               this.onChanged();
            }

            if (other.getOneofIndex() != 0) {
               this.setOneofIndex(other.getOneofIndex());
            }

            if (other.getPacked()) {
               this.setPacked(other.getPacked());
            }

            if (this.optionsBuilder_ == null) {
               if (!other.options_.isEmpty()) {
                  if (this.options_.isEmpty()) {
                     this.options_ = other.options_;
                     this.bitField0_ &= -129;
                  } else {
                     this.ensureOptionsIsMutable();
                     this.options_.addAll(other.options_);
                  }

                  this.onChanged();
               }
            } else if (!other.options_.isEmpty()) {
               if (this.optionsBuilder_.isEmpty()) {
                  this.optionsBuilder_.dispose();
                  this.optionsBuilder_ = null;
                  this.options_ = other.options_;
                  this.bitField0_ &= -129;
                  this.optionsBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? this.getOptionsFieldBuilder() : null;
               } else {
                  this.optionsBuilder_.addAllMessages(other.options_);
               }
            }

            if (!other.getJsonName().isEmpty()) {
               this.jsonName_ = other.jsonName_;
               this.bitField0_ |= 256;
               this.onChanged();
            }

            if (!other.getDefaultValue().isEmpty()) {
               this.defaultValue_ = other.defaultValue_;
               this.bitField0_ |= 512;
               this.onChanged();
            }

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
                     case 8:
                        this.kind_ = input.readEnum();
                        this.bitField0_ |= 1;
                        break;
                     case 16:
                        this.cardinality_ = input.readEnum();
                        this.bitField0_ |= 2;
                        break;
                     case 24:
                        this.number_ = input.readInt32();
                        this.bitField0_ |= 4;
                        break;
                     case 34:
                        this.name_ = input.readStringRequireUtf8();
                        this.bitField0_ |= 8;
                        break;
                     case 50:
                        this.typeUrl_ = input.readStringRequireUtf8();
                        this.bitField0_ |= 16;
                        break;
                     case 56:
                        this.oneofIndex_ = input.readInt32();
                        this.bitField0_ |= 32;
                        break;
                     case 64:
                        this.packed_ = input.readBool();
                        this.bitField0_ |= 64;
                        break;
                     case 74:
                        Option m = (Option)input.readMessage(Option.parser(), extensionRegistry);
                        if (this.optionsBuilder_ == null) {
                           this.ensureOptionsIsMutable();
                           this.options_.add(m);
                        } else {
                           this.optionsBuilder_.addMessage(m);
                        }
                        break;
                     case 82:
                        this.jsonName_ = input.readStringRequireUtf8();
                        this.bitField0_ |= 256;
                        break;
                     case 90:
                        this.defaultValue_ = input.readStringRequireUtf8();
                        this.bitField0_ |= 512;
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

      public int getKindValue() {
         return this.kind_;
      }

      public Builder setKindValue(int value) {
         this.kind_ = value;
         this.bitField0_ |= 1;
         this.onChanged();
         return this;
      }

      public Kind getKind() {
         Kind result = Field.Kind.forNumber(this.kind_);
         return result == null ? Field.Kind.UNRECOGNIZED : result;
      }

      public Builder setKind(Kind value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.bitField0_ |= 1;
            this.kind_ = value.getNumber();
            this.onChanged();
            return this;
         }
      }

      public Builder clearKind() {
         this.bitField0_ &= -2;
         this.kind_ = 0;
         this.onChanged();
         return this;
      }

      public int getCardinalityValue() {
         return this.cardinality_;
      }

      public Builder setCardinalityValue(int value) {
         this.cardinality_ = value;
         this.bitField0_ |= 2;
         this.onChanged();
         return this;
      }

      public Cardinality getCardinality() {
         Cardinality result = Field.Cardinality.forNumber(this.cardinality_);
         return result == null ? Field.Cardinality.UNRECOGNIZED : result;
      }

      public Builder setCardinality(Cardinality value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.bitField0_ |= 2;
            this.cardinality_ = value.getNumber();
            this.onChanged();
            return this;
         }
      }

      public Builder clearCardinality() {
         this.bitField0_ &= -3;
         this.cardinality_ = 0;
         this.onChanged();
         return this;
      }

      public int getNumber() {
         return this.number_;
      }

      public Builder setNumber(int value) {
         this.number_ = value;
         this.bitField0_ |= 4;
         this.onChanged();
         return this;
      }

      public Builder clearNumber() {
         this.bitField0_ &= -5;
         this.number_ = 0;
         this.onChanged();
         return this;
      }

      public String getName() {
         Object ref = this.name_;
         if (!(ref instanceof String)) {
            ByteString bs = (ByteString)ref;
            String s = bs.toStringUtf8();
            this.name_ = s;
            return s;
         } else {
            return (String)ref;
         }
      }

      public ByteString getNameBytes() {
         Object ref = this.name_;
         if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.name_ = b;
            return b;
         } else {
            return (ByteString)ref;
         }
      }

      public Builder setName(String value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.name_ = value;
            this.bitField0_ |= 8;
            this.onChanged();
            return this;
         }
      }

      public Builder clearName() {
         this.name_ = Field.getDefaultInstance().getName();
         this.bitField0_ &= -9;
         this.onChanged();
         return this;
      }

      public Builder setNameBytes(ByteString value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            AbstractMessageLite.checkByteStringIsUtf8(value);
            this.name_ = value;
            this.bitField0_ |= 8;
            this.onChanged();
            return this;
         }
      }

      public String getTypeUrl() {
         Object ref = this.typeUrl_;
         if (!(ref instanceof String)) {
            ByteString bs = (ByteString)ref;
            String s = bs.toStringUtf8();
            this.typeUrl_ = s;
            return s;
         } else {
            return (String)ref;
         }
      }

      public ByteString getTypeUrlBytes() {
         Object ref = this.typeUrl_;
         if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.typeUrl_ = b;
            return b;
         } else {
            return (ByteString)ref;
         }
      }

      public Builder setTypeUrl(String value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.typeUrl_ = value;
            this.bitField0_ |= 16;
            this.onChanged();
            return this;
         }
      }

      public Builder clearTypeUrl() {
         this.typeUrl_ = Field.getDefaultInstance().getTypeUrl();
         this.bitField0_ &= -17;
         this.onChanged();
         return this;
      }

      public Builder setTypeUrlBytes(ByteString value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            AbstractMessageLite.checkByteStringIsUtf8(value);
            this.typeUrl_ = value;
            this.bitField0_ |= 16;
            this.onChanged();
            return this;
         }
      }

      public int getOneofIndex() {
         return this.oneofIndex_;
      }

      public Builder setOneofIndex(int value) {
         this.oneofIndex_ = value;
         this.bitField0_ |= 32;
         this.onChanged();
         return this;
      }

      public Builder clearOneofIndex() {
         this.bitField0_ &= -33;
         this.oneofIndex_ = 0;
         this.onChanged();
         return this;
      }

      public boolean getPacked() {
         return this.packed_;
      }

      public Builder setPacked(boolean value) {
         this.packed_ = value;
         this.bitField0_ |= 64;
         this.onChanged();
         return this;
      }

      public Builder clearPacked() {
         this.bitField0_ &= -65;
         this.packed_ = false;
         this.onChanged();
         return this;
      }

      private void ensureOptionsIsMutable() {
         if ((this.bitField0_ & 128) == 0) {
            this.options_ = new ArrayList(this.options_);
            this.bitField0_ |= 128;
         }

      }

      public List getOptionsList() {
         return this.optionsBuilder_ == null ? Collections.unmodifiableList(this.options_) : this.optionsBuilder_.getMessageList();
      }

      public int getOptionsCount() {
         return this.optionsBuilder_ == null ? this.options_.size() : this.optionsBuilder_.getCount();
      }

      public Option getOptions(int index) {
         return this.optionsBuilder_ == null ? (Option)this.options_.get(index) : (Option)this.optionsBuilder_.getMessage(index);
      }

      public Builder setOptions(int index, Option value) {
         if (this.optionsBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.ensureOptionsIsMutable();
            this.options_.set(index, value);
            this.onChanged();
         } else {
            this.optionsBuilder_.setMessage(index, value);
         }

         return this;
      }

      public Builder setOptions(int index, Option.Builder builderForValue) {
         if (this.optionsBuilder_ == null) {
            this.ensureOptionsIsMutable();
            this.options_.set(index, builderForValue.build());
            this.onChanged();
         } else {
            this.optionsBuilder_.setMessage(index, builderForValue.build());
         }

         return this;
      }

      public Builder addOptions(Option value) {
         if (this.optionsBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.ensureOptionsIsMutable();
            this.options_.add(value);
            this.onChanged();
         } else {
            this.optionsBuilder_.addMessage(value);
         }

         return this;
      }

      public Builder addOptions(int index, Option value) {
         if (this.optionsBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.ensureOptionsIsMutable();
            this.options_.add(index, value);
            this.onChanged();
         } else {
            this.optionsBuilder_.addMessage(index, value);
         }

         return this;
      }

      public Builder addOptions(Option.Builder builderForValue) {
         if (this.optionsBuilder_ == null) {
            this.ensureOptionsIsMutable();
            this.options_.add(builderForValue.build());
            this.onChanged();
         } else {
            this.optionsBuilder_.addMessage(builderForValue.build());
         }

         return this;
      }

      public Builder addOptions(int index, Option.Builder builderForValue) {
         if (this.optionsBuilder_ == null) {
            this.ensureOptionsIsMutable();
            this.options_.add(index, builderForValue.build());
            this.onChanged();
         } else {
            this.optionsBuilder_.addMessage(index, builderForValue.build());
         }

         return this;
      }

      public Builder addAllOptions(Iterable values) {
         if (this.optionsBuilder_ == null) {
            this.ensureOptionsIsMutable();
            AbstractMessageLite.Builder.addAll(values, this.options_);
            this.onChanged();
         } else {
            this.optionsBuilder_.addAllMessages(values);
         }

         return this;
      }

      public Builder clearOptions() {
         if (this.optionsBuilder_ == null) {
            this.options_ = Collections.emptyList();
            this.bitField0_ &= -129;
            this.onChanged();
         } else {
            this.optionsBuilder_.clear();
         }

         return this;
      }

      public Builder removeOptions(int index) {
         if (this.optionsBuilder_ == null) {
            this.ensureOptionsIsMutable();
            this.options_.remove(index);
            this.onChanged();
         } else {
            this.optionsBuilder_.remove(index);
         }

         return this;
      }

      public Option.Builder getOptionsBuilder(int index) {
         return (Option.Builder)this.getOptionsFieldBuilder().getBuilder(index);
      }

      public OptionOrBuilder getOptionsOrBuilder(int index) {
         return this.optionsBuilder_ == null ? (OptionOrBuilder)this.options_.get(index) : (OptionOrBuilder)this.optionsBuilder_.getMessageOrBuilder(index);
      }

      public List getOptionsOrBuilderList() {
         return this.optionsBuilder_ != null ? this.optionsBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList(this.options_);
      }

      public Option.Builder addOptionsBuilder() {
         return (Option.Builder)this.getOptionsFieldBuilder().addBuilder(Option.getDefaultInstance());
      }

      public Option.Builder addOptionsBuilder(int index) {
         return (Option.Builder)this.getOptionsFieldBuilder().addBuilder(index, Option.getDefaultInstance());
      }

      public List getOptionsBuilderList() {
         return this.getOptionsFieldBuilder().getBuilderList();
      }

      private RepeatedFieldBuilder getOptionsFieldBuilder() {
         if (this.optionsBuilder_ == null) {
            this.optionsBuilder_ = new RepeatedFieldBuilder(this.options_, (this.bitField0_ & 128) != 0, this.getParentForChildren(), this.isClean());
            this.options_ = null;
         }

         return this.optionsBuilder_;
      }

      public String getJsonName() {
         Object ref = this.jsonName_;
         if (!(ref instanceof String)) {
            ByteString bs = (ByteString)ref;
            String s = bs.toStringUtf8();
            this.jsonName_ = s;
            return s;
         } else {
            return (String)ref;
         }
      }

      public ByteString getJsonNameBytes() {
         Object ref = this.jsonName_;
         if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.jsonName_ = b;
            return b;
         } else {
            return (ByteString)ref;
         }
      }

      public Builder setJsonName(String value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.jsonName_ = value;
            this.bitField0_ |= 256;
            this.onChanged();
            return this;
         }
      }

      public Builder clearJsonName() {
         this.jsonName_ = Field.getDefaultInstance().getJsonName();
         this.bitField0_ &= -257;
         this.onChanged();
         return this;
      }

      public Builder setJsonNameBytes(ByteString value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            AbstractMessageLite.checkByteStringIsUtf8(value);
            this.jsonName_ = value;
            this.bitField0_ |= 256;
            this.onChanged();
            return this;
         }
      }

      public String getDefaultValue() {
         Object ref = this.defaultValue_;
         if (!(ref instanceof String)) {
            ByteString bs = (ByteString)ref;
            String s = bs.toStringUtf8();
            this.defaultValue_ = s;
            return s;
         } else {
            return (String)ref;
         }
      }

      public ByteString getDefaultValueBytes() {
         Object ref = this.defaultValue_;
         if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.defaultValue_ = b;
            return b;
         } else {
            return (ByteString)ref;
         }
      }

      public Builder setDefaultValue(String value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.defaultValue_ = value;
            this.bitField0_ |= 512;
            this.onChanged();
            return this;
         }
      }

      public Builder clearDefaultValue() {
         this.defaultValue_ = Field.getDefaultInstance().getDefaultValue();
         this.bitField0_ &= -513;
         this.onChanged();
         return this;
      }

      public Builder setDefaultValueBytes(ByteString value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            AbstractMessageLite.checkByteStringIsUtf8(value);
            this.defaultValue_ = value;
            this.bitField0_ |= 512;
            this.onChanged();
            return this;
         }
      }
   }
}
