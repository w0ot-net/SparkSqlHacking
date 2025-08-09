package com.google.crypto.tink.proto;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.RuntimeVersion;
import com.google.protobuf.SingleFieldBuilder;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.RuntimeVersion.RuntimeDomain;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class RsaSsaPkcs1KeyFormat extends GeneratedMessage implements RsaSsaPkcs1KeyFormatOrBuilder {
   private static final long serialVersionUID = 0L;
   private int bitField0_;
   public static final int PARAMS_FIELD_NUMBER = 1;
   private RsaSsaPkcs1Params params_;
   public static final int MODULUS_SIZE_IN_BITS_FIELD_NUMBER = 2;
   private int modulusSizeInBits_;
   public static final int PUBLIC_EXPONENT_FIELD_NUMBER = 3;
   private ByteString publicExponent_;
   private byte memoizedIsInitialized;
   private static final RsaSsaPkcs1KeyFormat DEFAULT_INSTANCE;
   private static final Parser PARSER;

   private RsaSsaPkcs1KeyFormat(GeneratedMessage.Builder builder) {
      super(builder);
      this.modulusSizeInBits_ = 0;
      this.publicExponent_ = ByteString.EMPTY;
      this.memoizedIsInitialized = -1;
   }

   private RsaSsaPkcs1KeyFormat() {
      this.modulusSizeInBits_ = 0;
      this.publicExponent_ = ByteString.EMPTY;
      this.memoizedIsInitialized = -1;
      this.publicExponent_ = ByteString.EMPTY;
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return RsaSsaPkcs1.internal_static_google_crypto_tink_RsaSsaPkcs1KeyFormat_descriptor;
   }

   protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
      return RsaSsaPkcs1.internal_static_google_crypto_tink_RsaSsaPkcs1KeyFormat_fieldAccessorTable.ensureFieldAccessorsInitialized(RsaSsaPkcs1KeyFormat.class, Builder.class);
   }

   public boolean hasParams() {
      return (this.bitField0_ & 1) != 0;
   }

   public RsaSsaPkcs1Params getParams() {
      return this.params_ == null ? RsaSsaPkcs1Params.getDefaultInstance() : this.params_;
   }

   public RsaSsaPkcs1ParamsOrBuilder getParamsOrBuilder() {
      return this.params_ == null ? RsaSsaPkcs1Params.getDefaultInstance() : this.params_;
   }

   public int getModulusSizeInBits() {
      return this.modulusSizeInBits_;
   }

   public ByteString getPublicExponent() {
      return this.publicExponent_;
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
      if ((this.bitField0_ & 1) != 0) {
         output.writeMessage(1, this.getParams());
      }

      if (this.modulusSizeInBits_ != 0) {
         output.writeUInt32(2, this.modulusSizeInBits_);
      }

      if (!this.publicExponent_.isEmpty()) {
         output.writeBytes(3, this.publicExponent_);
      }

      this.getUnknownFields().writeTo(output);
   }

   public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1) {
         return size;
      } else {
         size = 0;
         if ((this.bitField0_ & 1) != 0) {
            size += CodedOutputStream.computeMessageSize(1, this.getParams());
         }

         if (this.modulusSizeInBits_ != 0) {
            size += CodedOutputStream.computeUInt32Size(2, this.modulusSizeInBits_);
         }

         if (!this.publicExponent_.isEmpty()) {
            size += CodedOutputStream.computeBytesSize(3, this.publicExponent_);
         }

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof RsaSsaPkcs1KeyFormat)) {
         return super.equals(obj);
      } else {
         RsaSsaPkcs1KeyFormat other = (RsaSsaPkcs1KeyFormat)obj;
         if (this.hasParams() != other.hasParams()) {
            return false;
         } else if (this.hasParams() && !this.getParams().equals(other.getParams())) {
            return false;
         } else if (this.getModulusSizeInBits() != other.getModulusSizeInBits()) {
            return false;
         } else if (!this.getPublicExponent().equals(other.getPublicExponent())) {
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
         if (this.hasParams()) {
            hash = 37 * hash + 1;
            hash = 53 * hash + this.getParams().hashCode();
         }

         hash = 37 * hash + 2;
         hash = 53 * hash + this.getModulusSizeInBits();
         hash = 37 * hash + 3;
         hash = 53 * hash + this.getPublicExponent().hashCode();
         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static RsaSsaPkcs1KeyFormat parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (RsaSsaPkcs1KeyFormat)PARSER.parseFrom(data);
   }

   public static RsaSsaPkcs1KeyFormat parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (RsaSsaPkcs1KeyFormat)PARSER.parseFrom(data, extensionRegistry);
   }

   public static RsaSsaPkcs1KeyFormat parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (RsaSsaPkcs1KeyFormat)PARSER.parseFrom(data);
   }

   public static RsaSsaPkcs1KeyFormat parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (RsaSsaPkcs1KeyFormat)PARSER.parseFrom(data, extensionRegistry);
   }

   public static RsaSsaPkcs1KeyFormat parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (RsaSsaPkcs1KeyFormat)PARSER.parseFrom(data);
   }

   public static RsaSsaPkcs1KeyFormat parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (RsaSsaPkcs1KeyFormat)PARSER.parseFrom(data, extensionRegistry);
   }

   public static RsaSsaPkcs1KeyFormat parseFrom(InputStream input) throws IOException {
      return (RsaSsaPkcs1KeyFormat)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static RsaSsaPkcs1KeyFormat parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (RsaSsaPkcs1KeyFormat)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static RsaSsaPkcs1KeyFormat parseDelimitedFrom(InputStream input) throws IOException {
      return (RsaSsaPkcs1KeyFormat)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
   }

   public static RsaSsaPkcs1KeyFormat parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (RsaSsaPkcs1KeyFormat)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static RsaSsaPkcs1KeyFormat parseFrom(CodedInputStream input) throws IOException {
      return (RsaSsaPkcs1KeyFormat)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static RsaSsaPkcs1KeyFormat parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (RsaSsaPkcs1KeyFormat)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(RsaSsaPkcs1KeyFormat prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static RsaSsaPkcs1KeyFormat getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public RsaSsaPkcs1KeyFormat getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", RsaSsaPkcs1KeyFormat.class.getName());
      DEFAULT_INSTANCE = new RsaSsaPkcs1KeyFormat();
      PARSER = new AbstractParser() {
         public RsaSsaPkcs1KeyFormat parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            Builder builder = RsaSsaPkcs1KeyFormat.newBuilder();

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

   public static final class Builder extends GeneratedMessage.Builder implements RsaSsaPkcs1KeyFormatOrBuilder {
      private int bitField0_;
      private RsaSsaPkcs1Params params_;
      private SingleFieldBuilder paramsBuilder_;
      private int modulusSizeInBits_;
      private ByteString publicExponent_;

      public static final Descriptors.Descriptor getDescriptor() {
         return RsaSsaPkcs1.internal_static_google_crypto_tink_RsaSsaPkcs1KeyFormat_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return RsaSsaPkcs1.internal_static_google_crypto_tink_RsaSsaPkcs1KeyFormat_fieldAccessorTable.ensureFieldAccessorsInitialized(RsaSsaPkcs1KeyFormat.class, Builder.class);
      }

      private Builder() {
         this.publicExponent_ = ByteString.EMPTY;
         this.maybeForceBuilderInitialization();
      }

      private Builder(AbstractMessage.BuilderParent parent) {
         super(parent);
         this.publicExponent_ = ByteString.EMPTY;
         this.maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization() {
         if (RsaSsaPkcs1KeyFormat.alwaysUseFieldBuilders) {
            this.getParamsFieldBuilder();
         }

      }

      public Builder clear() {
         super.clear();
         this.bitField0_ = 0;
         this.params_ = null;
         if (this.paramsBuilder_ != null) {
            this.paramsBuilder_.dispose();
            this.paramsBuilder_ = null;
         }

         this.modulusSizeInBits_ = 0;
         this.publicExponent_ = ByteString.EMPTY;
         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return RsaSsaPkcs1.internal_static_google_crypto_tink_RsaSsaPkcs1KeyFormat_descriptor;
      }

      public RsaSsaPkcs1KeyFormat getDefaultInstanceForType() {
         return RsaSsaPkcs1KeyFormat.getDefaultInstance();
      }

      public RsaSsaPkcs1KeyFormat build() {
         RsaSsaPkcs1KeyFormat result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public RsaSsaPkcs1KeyFormat buildPartial() {
         RsaSsaPkcs1KeyFormat result = new RsaSsaPkcs1KeyFormat(this);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartial0(RsaSsaPkcs1KeyFormat result) {
         int from_bitField0_ = this.bitField0_;
         int to_bitField0_ = 0;
         if ((from_bitField0_ & 1) != 0) {
            result.params_ = this.paramsBuilder_ == null ? this.params_ : (RsaSsaPkcs1Params)this.paramsBuilder_.build();
            to_bitField0_ |= 1;
         }

         if ((from_bitField0_ & 2) != 0) {
            result.modulusSizeInBits_ = this.modulusSizeInBits_;
         }

         if ((from_bitField0_ & 4) != 0) {
            result.publicExponent_ = this.publicExponent_;
         }

         result.bitField0_ = to_bitField0_;
      }

      public Builder mergeFrom(Message other) {
         if (other instanceof RsaSsaPkcs1KeyFormat) {
            return this.mergeFrom((RsaSsaPkcs1KeyFormat)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(RsaSsaPkcs1KeyFormat other) {
         if (other == RsaSsaPkcs1KeyFormat.getDefaultInstance()) {
            return this;
         } else {
            if (other.hasParams()) {
               this.mergeParams(other.getParams());
            }

            if (other.getModulusSizeInBits() != 0) {
               this.setModulusSizeInBits(other.getModulusSizeInBits());
            }

            if (other.getPublicExponent() != ByteString.EMPTY) {
               this.setPublicExponent(other.getPublicExponent());
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
                        input.readMessage(this.getParamsFieldBuilder().getBuilder(), extensionRegistry);
                        this.bitField0_ |= 1;
                        break;
                     case 16:
                        this.modulusSizeInBits_ = input.readUInt32();
                        this.bitField0_ |= 2;
                        break;
                     case 26:
                        this.publicExponent_ = input.readBytes();
                        this.bitField0_ |= 4;
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

      public boolean hasParams() {
         return (this.bitField0_ & 1) != 0;
      }

      public RsaSsaPkcs1Params getParams() {
         if (this.paramsBuilder_ == null) {
            return this.params_ == null ? RsaSsaPkcs1Params.getDefaultInstance() : this.params_;
         } else {
            return (RsaSsaPkcs1Params)this.paramsBuilder_.getMessage();
         }
      }

      public Builder setParams(RsaSsaPkcs1Params value) {
         if (this.paramsBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.params_ = value;
         } else {
            this.paramsBuilder_.setMessage(value);
         }

         this.bitField0_ |= 1;
         this.onChanged();
         return this;
      }

      public Builder setParams(RsaSsaPkcs1Params.Builder builderForValue) {
         if (this.paramsBuilder_ == null) {
            this.params_ = builderForValue.build();
         } else {
            this.paramsBuilder_.setMessage(builderForValue.build());
         }

         this.bitField0_ |= 1;
         this.onChanged();
         return this;
      }

      public Builder mergeParams(RsaSsaPkcs1Params value) {
         if (this.paramsBuilder_ == null) {
            if ((this.bitField0_ & 1) != 0 && this.params_ != null && this.params_ != RsaSsaPkcs1Params.getDefaultInstance()) {
               this.getParamsBuilder().mergeFrom(value);
            } else {
               this.params_ = value;
            }
         } else {
            this.paramsBuilder_.mergeFrom(value);
         }

         if (this.params_ != null) {
            this.bitField0_ |= 1;
            this.onChanged();
         }

         return this;
      }

      public Builder clearParams() {
         this.bitField0_ &= -2;
         this.params_ = null;
         if (this.paramsBuilder_ != null) {
            this.paramsBuilder_.dispose();
            this.paramsBuilder_ = null;
         }

         this.onChanged();
         return this;
      }

      public RsaSsaPkcs1Params.Builder getParamsBuilder() {
         this.bitField0_ |= 1;
         this.onChanged();
         return (RsaSsaPkcs1Params.Builder)this.getParamsFieldBuilder().getBuilder();
      }

      public RsaSsaPkcs1ParamsOrBuilder getParamsOrBuilder() {
         if (this.paramsBuilder_ != null) {
            return (RsaSsaPkcs1ParamsOrBuilder)this.paramsBuilder_.getMessageOrBuilder();
         } else {
            return this.params_ == null ? RsaSsaPkcs1Params.getDefaultInstance() : this.params_;
         }
      }

      private SingleFieldBuilder getParamsFieldBuilder() {
         if (this.paramsBuilder_ == null) {
            this.paramsBuilder_ = new SingleFieldBuilder(this.getParams(), this.getParentForChildren(), this.isClean());
            this.params_ = null;
         }

         return this.paramsBuilder_;
      }

      public int getModulusSizeInBits() {
         return this.modulusSizeInBits_;
      }

      public Builder setModulusSizeInBits(int value) {
         this.modulusSizeInBits_ = value;
         this.bitField0_ |= 2;
         this.onChanged();
         return this;
      }

      public Builder clearModulusSizeInBits() {
         this.bitField0_ &= -3;
         this.modulusSizeInBits_ = 0;
         this.onChanged();
         return this;
      }

      public ByteString getPublicExponent() {
         return this.publicExponent_;
      }

      public Builder setPublicExponent(ByteString value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.publicExponent_ = value;
            this.bitField0_ |= 4;
            this.onChanged();
            return this;
         }
      }

      public Builder clearPublicExponent() {
         this.bitField0_ &= -5;
         this.publicExponent_ = RsaSsaPkcs1KeyFormat.getDefaultInstance().getPublicExponent();
         this.onChanged();
         return this;
      }
   }
}
