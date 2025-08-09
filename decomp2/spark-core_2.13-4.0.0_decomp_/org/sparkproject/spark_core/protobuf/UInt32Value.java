package org.sparkproject.spark_core.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class UInt32Value extends GeneratedMessage implements UInt32ValueOrBuilder {
   private static final long serialVersionUID = 0L;
   public static final int VALUE_FIELD_NUMBER = 1;
   private int value_;
   private byte memoizedIsInitialized;
   private static final UInt32Value DEFAULT_INSTANCE;
   private static final Parser PARSER;

   private UInt32Value(GeneratedMessage.Builder builder) {
      super(builder);
      this.value_ = 0;
      this.memoizedIsInitialized = -1;
   }

   private UInt32Value() {
      this.value_ = 0;
      this.memoizedIsInitialized = -1;
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return WrappersProto.internal_static_google_protobuf_UInt32Value_descriptor;
   }

   protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
      return WrappersProto.internal_static_google_protobuf_UInt32Value_fieldAccessorTable.ensureFieldAccessorsInitialized(UInt32Value.class, Builder.class);
   }

   public int getValue() {
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
      if (this.value_ != 0) {
         output.writeUInt32(1, this.value_);
      }

      this.getUnknownFields().writeTo(output);
   }

   public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1) {
         return size;
      } else {
         size = 0;
         if (this.value_ != 0) {
            size += CodedOutputStream.computeUInt32Size(1, this.value_);
         }

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof UInt32Value)) {
         return super.equals(obj);
      } else {
         UInt32Value other = (UInt32Value)obj;
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
         hash = 53 * hash + this.getValue();
         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static UInt32Value parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (UInt32Value)PARSER.parseFrom(data);
   }

   public static UInt32Value parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (UInt32Value)PARSER.parseFrom(data, extensionRegistry);
   }

   public static UInt32Value parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (UInt32Value)PARSER.parseFrom(data);
   }

   public static UInt32Value parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (UInt32Value)PARSER.parseFrom(data, extensionRegistry);
   }

   public static UInt32Value parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (UInt32Value)PARSER.parseFrom(data);
   }

   public static UInt32Value parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (UInt32Value)PARSER.parseFrom(data, extensionRegistry);
   }

   public static UInt32Value parseFrom(InputStream input) throws IOException {
      return (UInt32Value)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static UInt32Value parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (UInt32Value)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static UInt32Value parseDelimitedFrom(InputStream input) throws IOException {
      return (UInt32Value)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
   }

   public static UInt32Value parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (UInt32Value)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static UInt32Value parseFrom(CodedInputStream input) throws IOException {
      return (UInt32Value)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static UInt32Value parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (UInt32Value)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(UInt32Value prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static UInt32Value getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static UInt32Value of(int value) {
      return newBuilder().setValue(value).build();
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public UInt32Value getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeVersion.RuntimeDomain.PUBLIC, 4, 29, 3, "", UInt32Value.class.getName());
      DEFAULT_INSTANCE = new UInt32Value();
      PARSER = new AbstractParser() {
         public UInt32Value parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            Builder builder = UInt32Value.newBuilder();

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

   public static final class Builder extends GeneratedMessage.Builder implements UInt32ValueOrBuilder {
      private int bitField0_;
      private int value_;

      public static final Descriptors.Descriptor getDescriptor() {
         return WrappersProto.internal_static_google_protobuf_UInt32Value_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return WrappersProto.internal_static_google_protobuf_UInt32Value_fieldAccessorTable.ensureFieldAccessorsInitialized(UInt32Value.class, Builder.class);
      }

      private Builder() {
      }

      private Builder(AbstractMessage.BuilderParent parent) {
         super(parent);
      }

      public Builder clear() {
         super.clear();
         this.bitField0_ = 0;
         this.value_ = 0;
         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return WrappersProto.internal_static_google_protobuf_UInt32Value_descriptor;
      }

      public UInt32Value getDefaultInstanceForType() {
         return UInt32Value.getDefaultInstance();
      }

      public UInt32Value build() {
         UInt32Value result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public UInt32Value buildPartial() {
         UInt32Value result = new UInt32Value(this);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartial0(UInt32Value result) {
         int from_bitField0_ = this.bitField0_;
         if ((from_bitField0_ & 1) != 0) {
            result.value_ = this.value_;
         }

      }

      public Builder mergeFrom(Message other) {
         if (other instanceof UInt32Value) {
            return this.mergeFrom((UInt32Value)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(UInt32Value other) {
         if (other == UInt32Value.getDefaultInstance()) {
            return this;
         } else {
            if (other.getValue() != 0) {
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
                        this.value_ = input.readUInt32();
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

      public int getValue() {
         return this.value_;
      }

      public Builder setValue(int value) {
         this.value_ = value;
         this.bitField0_ |= 1;
         this.onChanged();
         return this;
      }

      public Builder clearValue() {
         this.bitField0_ &= -2;
         this.value_ = 0;
         this.onChanged();
         return this;
      }
   }
}
