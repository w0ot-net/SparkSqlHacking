package org.sparkproject.spark_core.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class DoubleValue extends GeneratedMessage implements DoubleValueOrBuilder {
   private static final long serialVersionUID = 0L;
   public static final int VALUE_FIELD_NUMBER = 1;
   private double value_;
   private byte memoizedIsInitialized;
   private static final DoubleValue DEFAULT_INSTANCE;
   private static final Parser PARSER;

   private DoubleValue(GeneratedMessage.Builder builder) {
      super(builder);
      this.value_ = (double)0.0F;
      this.memoizedIsInitialized = -1;
   }

   private DoubleValue() {
      this.value_ = (double)0.0F;
      this.memoizedIsInitialized = -1;
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return WrappersProto.internal_static_google_protobuf_DoubleValue_descriptor;
   }

   protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
      return WrappersProto.internal_static_google_protobuf_DoubleValue_fieldAccessorTable.ensureFieldAccessorsInitialized(DoubleValue.class, Builder.class);
   }

   public double getValue() {
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
      if (Double.doubleToRawLongBits(this.value_) != 0L) {
         output.writeDouble(1, this.value_);
      }

      this.getUnknownFields().writeTo(output);
   }

   public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1) {
         return size;
      } else {
         size = 0;
         if (Double.doubleToRawLongBits(this.value_) != 0L) {
            size += CodedOutputStream.computeDoubleSize(1, this.value_);
         }

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof DoubleValue)) {
         return super.equals(obj);
      } else {
         DoubleValue other = (DoubleValue)obj;
         if (Double.doubleToLongBits(this.getValue()) != Double.doubleToLongBits(other.getValue())) {
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
         hash = 53 * hash + Internal.hashLong(Double.doubleToLongBits(this.getValue()));
         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static DoubleValue parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (DoubleValue)PARSER.parseFrom(data);
   }

   public static DoubleValue parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (DoubleValue)PARSER.parseFrom(data, extensionRegistry);
   }

   public static DoubleValue parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (DoubleValue)PARSER.parseFrom(data);
   }

   public static DoubleValue parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (DoubleValue)PARSER.parseFrom(data, extensionRegistry);
   }

   public static DoubleValue parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (DoubleValue)PARSER.parseFrom(data);
   }

   public static DoubleValue parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (DoubleValue)PARSER.parseFrom(data, extensionRegistry);
   }

   public static DoubleValue parseFrom(InputStream input) throws IOException {
      return (DoubleValue)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static DoubleValue parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (DoubleValue)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static DoubleValue parseDelimitedFrom(InputStream input) throws IOException {
      return (DoubleValue)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
   }

   public static DoubleValue parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (DoubleValue)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static DoubleValue parseFrom(CodedInputStream input) throws IOException {
      return (DoubleValue)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static DoubleValue parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (DoubleValue)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(DoubleValue prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static DoubleValue getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static DoubleValue of(double value) {
      return newBuilder().setValue(value).build();
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public DoubleValue getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeVersion.RuntimeDomain.PUBLIC, 4, 29, 3, "", DoubleValue.class.getName());
      DEFAULT_INSTANCE = new DoubleValue();
      PARSER = new AbstractParser() {
         public DoubleValue parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            Builder builder = DoubleValue.newBuilder();

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

   public static final class Builder extends GeneratedMessage.Builder implements DoubleValueOrBuilder {
      private int bitField0_;
      private double value_;

      public static final Descriptors.Descriptor getDescriptor() {
         return WrappersProto.internal_static_google_protobuf_DoubleValue_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return WrappersProto.internal_static_google_protobuf_DoubleValue_fieldAccessorTable.ensureFieldAccessorsInitialized(DoubleValue.class, Builder.class);
      }

      private Builder() {
      }

      private Builder(AbstractMessage.BuilderParent parent) {
         super(parent);
      }

      public Builder clear() {
         super.clear();
         this.bitField0_ = 0;
         this.value_ = (double)0.0F;
         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return WrappersProto.internal_static_google_protobuf_DoubleValue_descriptor;
      }

      public DoubleValue getDefaultInstanceForType() {
         return DoubleValue.getDefaultInstance();
      }

      public DoubleValue build() {
         DoubleValue result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public DoubleValue buildPartial() {
         DoubleValue result = new DoubleValue(this);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartial0(DoubleValue result) {
         int from_bitField0_ = this.bitField0_;
         if ((from_bitField0_ & 1) != 0) {
            result.value_ = this.value_;
         }

      }

      public Builder mergeFrom(Message other) {
         if (other instanceof DoubleValue) {
            return this.mergeFrom((DoubleValue)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(DoubleValue other) {
         if (other == DoubleValue.getDefaultInstance()) {
            return this;
         } else {
            if (other.getValue() != (double)0.0F) {
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
                     case 9:
                        this.value_ = input.readDouble();
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

      public double getValue() {
         return this.value_;
      }

      public Builder setValue(double value) {
         this.value_ = value;
         this.bitField0_ |= 1;
         this.onChanged();
         return this;
      }

      public Builder clearValue() {
         this.bitField0_ &= -2;
         this.value_ = (double)0.0F;
         this.onChanged();
         return this;
      }
   }
}
