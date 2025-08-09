package org.apache.orc.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class BytesValue extends GeneratedMessageV3 implements BytesValueOrBuilder {
   private static final long serialVersionUID = 0L;
   public static final int VALUE_FIELD_NUMBER = 1;
   private ByteString value_;
   private byte memoizedIsInitialized;
   private static final BytesValue DEFAULT_INSTANCE = new BytesValue();
   private static final Parser PARSER = new AbstractParser() {
      public BytesValue parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
         Builder builder = BytesValue.newBuilder();

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

   private BytesValue(GeneratedMessageV3.Builder builder) {
      super(builder);
      this.value_ = ByteString.EMPTY;
      this.memoizedIsInitialized = -1;
   }

   private BytesValue() {
      this.value_ = ByteString.EMPTY;
      this.memoizedIsInitialized = -1;
      this.value_ = ByteString.EMPTY;
   }

   protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new BytesValue();
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return WrappersProto.internal_static_google_protobuf_BytesValue_descriptor;
   }

   protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return WrappersProto.internal_static_google_protobuf_BytesValue_fieldAccessorTable.ensureFieldAccessorsInitialized(BytesValue.class, Builder.class);
   }

   public ByteString getValue() {
      return this.value_;
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
      if (!this.value_.isEmpty()) {
         output.writeBytes(1, this.value_);
      }

      this.getUnknownFields().writeTo(output);
   }

   public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1) {
         return size;
      } else {
         size = 0;
         if (!this.value_.isEmpty()) {
            size += CodedOutputStream.computeBytesSize(1, this.value_);
         }

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof BytesValue)) {
         return super.equals(obj);
      } else {
         BytesValue other = (BytesValue)obj;
         if (!this.getValue().equals(other.getValue())) {
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
         hash = 53 * hash + this.getValue().hashCode();
         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static BytesValue parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (BytesValue)PARSER.parseFrom(data);
   }

   public static BytesValue parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (BytesValue)PARSER.parseFrom(data, extensionRegistry);
   }

   public static BytesValue parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (BytesValue)PARSER.parseFrom(data);
   }

   public static BytesValue parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (BytesValue)PARSER.parseFrom(data, extensionRegistry);
   }

   public static BytesValue parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (BytesValue)PARSER.parseFrom(data);
   }

   public static BytesValue parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (BytesValue)PARSER.parseFrom(data, extensionRegistry);
   }

   public static BytesValue parseFrom(InputStream input) throws IOException {
      return (BytesValue)GeneratedMessageV3.parseWithIOException(PARSER, input);
   }

   public static BytesValue parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (BytesValue)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static BytesValue parseDelimitedFrom(InputStream input) throws IOException {
      return (BytesValue)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
   }

   public static BytesValue parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (BytesValue)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static BytesValue parseFrom(CodedInputStream input) throws IOException {
      return (BytesValue)GeneratedMessageV3.parseWithIOException(PARSER, input);
   }

   public static BytesValue parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (BytesValue)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(BytesValue prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static BytesValue getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static BytesValue of(ByteString value) {
      return newBuilder().setValue(value).build();
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public BytesValue getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   public static final class Builder extends GeneratedMessageV3.Builder implements BytesValueOrBuilder {
      private int bitField0_;
      private ByteString value_;

      public static final Descriptors.Descriptor getDescriptor() {
         return WrappersProto.internal_static_google_protobuf_BytesValue_descriptor;
      }

      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
         return WrappersProto.internal_static_google_protobuf_BytesValue_fieldAccessorTable.ensureFieldAccessorsInitialized(BytesValue.class, Builder.class);
      }

      private Builder() {
         this.value_ = ByteString.EMPTY;
      }

      private Builder(GeneratedMessageV3.BuilderParent parent) {
         super(parent);
         this.value_ = ByteString.EMPTY;
      }

      public Builder clear() {
         super.clear();
         this.bitField0_ = 0;
         this.value_ = ByteString.EMPTY;
         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return WrappersProto.internal_static_google_protobuf_BytesValue_descriptor;
      }

      public BytesValue getDefaultInstanceForType() {
         return BytesValue.getDefaultInstance();
      }

      public BytesValue build() {
         BytesValue result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public BytesValue buildPartial() {
         BytesValue result = new BytesValue(this);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartial0(BytesValue result) {
         int from_bitField0_ = this.bitField0_;
         if ((from_bitField0_ & 1) != 0) {
            result.value_ = this.value_;
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
         if (other instanceof BytesValue) {
            return this.mergeFrom((BytesValue)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(BytesValue other) {
         if (other == BytesValue.getDefaultInstance()) {
            return this;
         } else {
            if (other.getValue() != ByteString.EMPTY) {
               this.setValue(other.getValue());
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
                     case 10:
                        this.value_ = input.readBytes();
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

      public ByteString getValue() {
         return this.value_;
      }

      public Builder setValue(ByteString value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.value_ = value;
            this.bitField0_ |= 1;
            this.onChanged();
            return this;
         }
      }

      public Builder clearValue() {
         this.bitField0_ &= -2;
         this.value_ = BytesValue.getDefaultInstance().getValue();
         this.onChanged();
         return this;
      }

      public final Builder setUnknownFields(final UnknownFieldSet unknownFields) {
         return (Builder)super.setUnknownFields(unknownFields);
      }

      public final Builder mergeUnknownFields(final UnknownFieldSet unknownFields) {
         return (Builder)super.mergeUnknownFields(unknownFields);
      }
   }
}
