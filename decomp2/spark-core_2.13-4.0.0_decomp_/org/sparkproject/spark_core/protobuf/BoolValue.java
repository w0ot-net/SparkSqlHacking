package org.sparkproject.spark_core.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class BoolValue extends GeneratedMessage implements BoolValueOrBuilder {
   private static final long serialVersionUID = 0L;
   public static final int VALUE_FIELD_NUMBER = 1;
   private boolean value_;
   private byte memoizedIsInitialized;
   private static final BoolValue DEFAULT_INSTANCE;
   private static final Parser PARSER;

   private BoolValue(GeneratedMessage.Builder builder) {
      super(builder);
      this.value_ = false;
      this.memoizedIsInitialized = -1;
   }

   private BoolValue() {
      this.value_ = false;
      this.memoizedIsInitialized = -1;
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return WrappersProto.internal_static_google_protobuf_BoolValue_descriptor;
   }

   protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
      return WrappersProto.internal_static_google_protobuf_BoolValue_fieldAccessorTable.ensureFieldAccessorsInitialized(BoolValue.class, Builder.class);
   }

   public boolean getValue() {
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
      if (this.value_) {
         output.writeBool(1, this.value_);
      }

      this.getUnknownFields().writeTo(output);
   }

   public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1) {
         return size;
      } else {
         size = 0;
         if (this.value_) {
            size += CodedOutputStream.computeBoolSize(1, this.value_);
         }

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof BoolValue)) {
         return super.equals(obj);
      } else {
         BoolValue other = (BoolValue)obj;
         if (this.getValue() != other.getValue()) {
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
         hash = 53 * hash + Internal.hashBoolean(this.getValue());
         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static BoolValue parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (BoolValue)PARSER.parseFrom(data);
   }

   public static BoolValue parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (BoolValue)PARSER.parseFrom(data, extensionRegistry);
   }

   public static BoolValue parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (BoolValue)PARSER.parseFrom(data);
   }

   public static BoolValue parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (BoolValue)PARSER.parseFrom(data, extensionRegistry);
   }

   public static BoolValue parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (BoolValue)PARSER.parseFrom(data);
   }

   public static BoolValue parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (BoolValue)PARSER.parseFrom(data, extensionRegistry);
   }

   public static BoolValue parseFrom(InputStream input) throws IOException {
      return (BoolValue)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static BoolValue parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (BoolValue)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static BoolValue parseDelimitedFrom(InputStream input) throws IOException {
      return (BoolValue)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
   }

   public static BoolValue parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (BoolValue)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static BoolValue parseFrom(CodedInputStream input) throws IOException {
      return (BoolValue)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static BoolValue parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (BoolValue)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(BoolValue prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static BoolValue getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static BoolValue of(boolean value) {
      return newBuilder().setValue(value).build();
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public BoolValue getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeVersion.RuntimeDomain.PUBLIC, 4, 29, 3, "", BoolValue.class.getName());
      DEFAULT_INSTANCE = new BoolValue();
      PARSER = new AbstractParser() {
         public BoolValue parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            Builder builder = BoolValue.newBuilder();

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

   public static final class Builder extends GeneratedMessage.Builder implements BoolValueOrBuilder {
      private int bitField0_;
      private boolean value_;

      public static final Descriptors.Descriptor getDescriptor() {
         return WrappersProto.internal_static_google_protobuf_BoolValue_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return WrappersProto.internal_static_google_protobuf_BoolValue_fieldAccessorTable.ensureFieldAccessorsInitialized(BoolValue.class, Builder.class);
      }

      private Builder() {
      }

      private Builder(AbstractMessage.BuilderParent parent) {
         super(parent);
      }

      public Builder clear() {
         super.clear();
         this.bitField0_ = 0;
         this.value_ = false;
         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return WrappersProto.internal_static_google_protobuf_BoolValue_descriptor;
      }

      public BoolValue getDefaultInstanceForType() {
         return BoolValue.getDefaultInstance();
      }

      public BoolValue build() {
         BoolValue result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public BoolValue buildPartial() {
         BoolValue result = new BoolValue(this);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartial0(BoolValue result) {
         int from_bitField0_ = this.bitField0_;
         if ((from_bitField0_ & 1) != 0) {
            result.value_ = this.value_;
         }

      }

      public Builder mergeFrom(Message other) {
         if (other instanceof BoolValue) {
            return this.mergeFrom((BoolValue)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(BoolValue other) {
         if (other == BoolValue.getDefaultInstance()) {
            return this;
         } else {
            if (other.getValue()) {
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
                     case 8:
                        this.value_ = input.readBool();
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

      public boolean getValue() {
         return this.value_;
      }

      public Builder setValue(boolean value) {
         this.value_ = value;
         this.bitField0_ |= 1;
         this.onChanged();
         return this;
      }

      public Builder clearValue() {
         this.bitField0_ &= -2;
         this.value_ = false;
         this.onChanged();
         return this;
      }
   }
}
