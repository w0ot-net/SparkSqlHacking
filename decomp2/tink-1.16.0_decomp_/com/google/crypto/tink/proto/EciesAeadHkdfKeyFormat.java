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

public final class EciesAeadHkdfKeyFormat extends GeneratedMessage implements EciesAeadHkdfKeyFormatOrBuilder {
   private static final long serialVersionUID = 0L;
   private int bitField0_;
   public static final int PARAMS_FIELD_NUMBER = 1;
   private EciesAeadHkdfParams params_;
   private byte memoizedIsInitialized;
   private static final EciesAeadHkdfKeyFormat DEFAULT_INSTANCE;
   private static final Parser PARSER;

   private EciesAeadHkdfKeyFormat(GeneratedMessage.Builder builder) {
      super(builder);
      this.memoizedIsInitialized = -1;
   }

   private EciesAeadHkdfKeyFormat() {
      this.memoizedIsInitialized = -1;
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return EciesAeadHkdf.internal_static_google_crypto_tink_EciesAeadHkdfKeyFormat_descriptor;
   }

   protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
      return EciesAeadHkdf.internal_static_google_crypto_tink_EciesAeadHkdfKeyFormat_fieldAccessorTable.ensureFieldAccessorsInitialized(EciesAeadHkdfKeyFormat.class, Builder.class);
   }

   public boolean hasParams() {
      return (this.bitField0_ & 1) != 0;
   }

   public EciesAeadHkdfParams getParams() {
      return this.params_ == null ? EciesAeadHkdfParams.getDefaultInstance() : this.params_;
   }

   public EciesAeadHkdfParamsOrBuilder getParamsOrBuilder() {
      return this.params_ == null ? EciesAeadHkdfParams.getDefaultInstance() : this.params_;
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

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof EciesAeadHkdfKeyFormat)) {
         return super.equals(obj);
      } else {
         EciesAeadHkdfKeyFormat other = (EciesAeadHkdfKeyFormat)obj;
         if (this.hasParams() != other.hasParams()) {
            return false;
         } else if (this.hasParams() && !this.getParams().equals(other.getParams())) {
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

         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static EciesAeadHkdfKeyFormat parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (EciesAeadHkdfKeyFormat)PARSER.parseFrom(data);
   }

   public static EciesAeadHkdfKeyFormat parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (EciesAeadHkdfKeyFormat)PARSER.parseFrom(data, extensionRegistry);
   }

   public static EciesAeadHkdfKeyFormat parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (EciesAeadHkdfKeyFormat)PARSER.parseFrom(data);
   }

   public static EciesAeadHkdfKeyFormat parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (EciesAeadHkdfKeyFormat)PARSER.parseFrom(data, extensionRegistry);
   }

   public static EciesAeadHkdfKeyFormat parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (EciesAeadHkdfKeyFormat)PARSER.parseFrom(data);
   }

   public static EciesAeadHkdfKeyFormat parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (EciesAeadHkdfKeyFormat)PARSER.parseFrom(data, extensionRegistry);
   }

   public static EciesAeadHkdfKeyFormat parseFrom(InputStream input) throws IOException {
      return (EciesAeadHkdfKeyFormat)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static EciesAeadHkdfKeyFormat parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (EciesAeadHkdfKeyFormat)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static EciesAeadHkdfKeyFormat parseDelimitedFrom(InputStream input) throws IOException {
      return (EciesAeadHkdfKeyFormat)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
   }

   public static EciesAeadHkdfKeyFormat parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (EciesAeadHkdfKeyFormat)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static EciesAeadHkdfKeyFormat parseFrom(CodedInputStream input) throws IOException {
      return (EciesAeadHkdfKeyFormat)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static EciesAeadHkdfKeyFormat parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (EciesAeadHkdfKeyFormat)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(EciesAeadHkdfKeyFormat prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static EciesAeadHkdfKeyFormat getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public EciesAeadHkdfKeyFormat getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", EciesAeadHkdfKeyFormat.class.getName());
      DEFAULT_INSTANCE = new EciesAeadHkdfKeyFormat();
      PARSER = new AbstractParser() {
         public EciesAeadHkdfKeyFormat parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            Builder builder = EciesAeadHkdfKeyFormat.newBuilder();

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

   public static final class Builder extends GeneratedMessage.Builder implements EciesAeadHkdfKeyFormatOrBuilder {
      private int bitField0_;
      private EciesAeadHkdfParams params_;
      private SingleFieldBuilder paramsBuilder_;

      public static final Descriptors.Descriptor getDescriptor() {
         return EciesAeadHkdf.internal_static_google_crypto_tink_EciesAeadHkdfKeyFormat_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return EciesAeadHkdf.internal_static_google_crypto_tink_EciesAeadHkdfKeyFormat_fieldAccessorTable.ensureFieldAccessorsInitialized(EciesAeadHkdfKeyFormat.class, Builder.class);
      }

      private Builder() {
         this.maybeForceBuilderInitialization();
      }

      private Builder(AbstractMessage.BuilderParent parent) {
         super(parent);
         this.maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization() {
         if (EciesAeadHkdfKeyFormat.alwaysUseFieldBuilders) {
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

         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return EciesAeadHkdf.internal_static_google_crypto_tink_EciesAeadHkdfKeyFormat_descriptor;
      }

      public EciesAeadHkdfKeyFormat getDefaultInstanceForType() {
         return EciesAeadHkdfKeyFormat.getDefaultInstance();
      }

      public EciesAeadHkdfKeyFormat build() {
         EciesAeadHkdfKeyFormat result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public EciesAeadHkdfKeyFormat buildPartial() {
         EciesAeadHkdfKeyFormat result = new EciesAeadHkdfKeyFormat(this);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartial0(EciesAeadHkdfKeyFormat result) {
         int from_bitField0_ = this.bitField0_;
         int to_bitField0_ = 0;
         if ((from_bitField0_ & 1) != 0) {
            result.params_ = this.paramsBuilder_ == null ? this.params_ : (EciesAeadHkdfParams)this.paramsBuilder_.build();
            to_bitField0_ |= 1;
         }

         result.bitField0_ = to_bitField0_;
      }

      public Builder mergeFrom(Message other) {
         if (other instanceof EciesAeadHkdfKeyFormat) {
            return this.mergeFrom((EciesAeadHkdfKeyFormat)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(EciesAeadHkdfKeyFormat other) {
         if (other == EciesAeadHkdfKeyFormat.getDefaultInstance()) {
            return this;
         } else {
            if (other.hasParams()) {
               this.mergeParams(other.getParams());
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

      public EciesAeadHkdfParams getParams() {
         if (this.paramsBuilder_ == null) {
            return this.params_ == null ? EciesAeadHkdfParams.getDefaultInstance() : this.params_;
         } else {
            return (EciesAeadHkdfParams)this.paramsBuilder_.getMessage();
         }
      }

      public Builder setParams(EciesAeadHkdfParams value) {
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

      public Builder setParams(EciesAeadHkdfParams.Builder builderForValue) {
         if (this.paramsBuilder_ == null) {
            this.params_ = builderForValue.build();
         } else {
            this.paramsBuilder_.setMessage(builderForValue.build());
         }

         this.bitField0_ |= 1;
         this.onChanged();
         return this;
      }

      public Builder mergeParams(EciesAeadHkdfParams value) {
         if (this.paramsBuilder_ == null) {
            if ((this.bitField0_ & 1) != 0 && this.params_ != null && this.params_ != EciesAeadHkdfParams.getDefaultInstance()) {
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

      public EciesAeadHkdfParams.Builder getParamsBuilder() {
         this.bitField0_ |= 1;
         this.onChanged();
         return (EciesAeadHkdfParams.Builder)this.getParamsFieldBuilder().getBuilder();
      }

      public EciesAeadHkdfParamsOrBuilder getParamsOrBuilder() {
         if (this.paramsBuilder_ != null) {
            return (EciesAeadHkdfParamsOrBuilder)this.paramsBuilder_.getMessageOrBuilder();
         } else {
            return this.params_ == null ? EciesAeadHkdfParams.getDefaultInstance() : this.params_;
         }
      }

      private SingleFieldBuilder getParamsFieldBuilder() {
         if (this.paramsBuilder_ == null) {
            this.paramsBuilder_ = new SingleFieldBuilder(this.getParams(), this.getParentForChildren(), this.isClean());
            this.params_ = null;
         }

         return this.paramsBuilder_;
      }
   }
}
