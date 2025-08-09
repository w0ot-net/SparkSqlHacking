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

public final class KmsEnvelopeAeadKey extends GeneratedMessage implements KmsEnvelopeAeadKeyOrBuilder {
   private static final long serialVersionUID = 0L;
   private int bitField0_;
   public static final int VERSION_FIELD_NUMBER = 1;
   private int version_;
   public static final int PARAMS_FIELD_NUMBER = 2;
   private KmsEnvelopeAeadKeyFormat params_;
   private byte memoizedIsInitialized;
   private static final KmsEnvelopeAeadKey DEFAULT_INSTANCE;
   private static final Parser PARSER;

   private KmsEnvelopeAeadKey(GeneratedMessage.Builder builder) {
      super(builder);
      this.version_ = 0;
      this.memoizedIsInitialized = -1;
   }

   private KmsEnvelopeAeadKey() {
      this.version_ = 0;
      this.memoizedIsInitialized = -1;
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return KmsEnvelope.internal_static_google_crypto_tink_KmsEnvelopeAeadKey_descriptor;
   }

   protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
      return KmsEnvelope.internal_static_google_crypto_tink_KmsEnvelopeAeadKey_fieldAccessorTable.ensureFieldAccessorsInitialized(KmsEnvelopeAeadKey.class, Builder.class);
   }

   public int getVersion() {
      return this.version_;
   }

   public boolean hasParams() {
      return (this.bitField0_ & 1) != 0;
   }

   public KmsEnvelopeAeadKeyFormat getParams() {
      return this.params_ == null ? KmsEnvelopeAeadKeyFormat.getDefaultInstance() : this.params_;
   }

   public KmsEnvelopeAeadKeyFormatOrBuilder getParamsOrBuilder() {
      return this.params_ == null ? KmsEnvelopeAeadKeyFormat.getDefaultInstance() : this.params_;
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
      if (this.version_ != 0) {
         output.writeUInt32(1, this.version_);
      }

      if ((this.bitField0_ & 1) != 0) {
         output.writeMessage(2, this.getParams());
      }

      this.getUnknownFields().writeTo(output);
   }

   public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1) {
         return size;
      } else {
         size = 0;
         if (this.version_ != 0) {
            size += CodedOutputStream.computeUInt32Size(1, this.version_);
         }

         if ((this.bitField0_ & 1) != 0) {
            size += CodedOutputStream.computeMessageSize(2, this.getParams());
         }

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof KmsEnvelopeAeadKey)) {
         return super.equals(obj);
      } else {
         KmsEnvelopeAeadKey other = (KmsEnvelopeAeadKey)obj;
         if (this.getVersion() != other.getVersion()) {
            return false;
         } else if (this.hasParams() != other.hasParams()) {
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
         hash = 37 * hash + 1;
         hash = 53 * hash + this.getVersion();
         if (this.hasParams()) {
            hash = 37 * hash + 2;
            hash = 53 * hash + this.getParams().hashCode();
         }

         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static KmsEnvelopeAeadKey parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (KmsEnvelopeAeadKey)PARSER.parseFrom(data);
   }

   public static KmsEnvelopeAeadKey parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (KmsEnvelopeAeadKey)PARSER.parseFrom(data, extensionRegistry);
   }

   public static KmsEnvelopeAeadKey parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (KmsEnvelopeAeadKey)PARSER.parseFrom(data);
   }

   public static KmsEnvelopeAeadKey parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (KmsEnvelopeAeadKey)PARSER.parseFrom(data, extensionRegistry);
   }

   public static KmsEnvelopeAeadKey parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (KmsEnvelopeAeadKey)PARSER.parseFrom(data);
   }

   public static KmsEnvelopeAeadKey parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (KmsEnvelopeAeadKey)PARSER.parseFrom(data, extensionRegistry);
   }

   public static KmsEnvelopeAeadKey parseFrom(InputStream input) throws IOException {
      return (KmsEnvelopeAeadKey)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static KmsEnvelopeAeadKey parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (KmsEnvelopeAeadKey)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static KmsEnvelopeAeadKey parseDelimitedFrom(InputStream input) throws IOException {
      return (KmsEnvelopeAeadKey)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
   }

   public static KmsEnvelopeAeadKey parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (KmsEnvelopeAeadKey)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static KmsEnvelopeAeadKey parseFrom(CodedInputStream input) throws IOException {
      return (KmsEnvelopeAeadKey)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static KmsEnvelopeAeadKey parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (KmsEnvelopeAeadKey)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(KmsEnvelopeAeadKey prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static KmsEnvelopeAeadKey getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public KmsEnvelopeAeadKey getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", KmsEnvelopeAeadKey.class.getName());
      DEFAULT_INSTANCE = new KmsEnvelopeAeadKey();
      PARSER = new AbstractParser() {
         public KmsEnvelopeAeadKey parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            Builder builder = KmsEnvelopeAeadKey.newBuilder();

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

   public static final class Builder extends GeneratedMessage.Builder implements KmsEnvelopeAeadKeyOrBuilder {
      private int bitField0_;
      private int version_;
      private KmsEnvelopeAeadKeyFormat params_;
      private SingleFieldBuilder paramsBuilder_;

      public static final Descriptors.Descriptor getDescriptor() {
         return KmsEnvelope.internal_static_google_crypto_tink_KmsEnvelopeAeadKey_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return KmsEnvelope.internal_static_google_crypto_tink_KmsEnvelopeAeadKey_fieldAccessorTable.ensureFieldAccessorsInitialized(KmsEnvelopeAeadKey.class, Builder.class);
      }

      private Builder() {
         this.maybeForceBuilderInitialization();
      }

      private Builder(AbstractMessage.BuilderParent parent) {
         super(parent);
         this.maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization() {
         if (KmsEnvelopeAeadKey.alwaysUseFieldBuilders) {
            this.getParamsFieldBuilder();
         }

      }

      public Builder clear() {
         super.clear();
         this.bitField0_ = 0;
         this.version_ = 0;
         this.params_ = null;
         if (this.paramsBuilder_ != null) {
            this.paramsBuilder_.dispose();
            this.paramsBuilder_ = null;
         }

         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return KmsEnvelope.internal_static_google_crypto_tink_KmsEnvelopeAeadKey_descriptor;
      }

      public KmsEnvelopeAeadKey getDefaultInstanceForType() {
         return KmsEnvelopeAeadKey.getDefaultInstance();
      }

      public KmsEnvelopeAeadKey build() {
         KmsEnvelopeAeadKey result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public KmsEnvelopeAeadKey buildPartial() {
         KmsEnvelopeAeadKey result = new KmsEnvelopeAeadKey(this);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartial0(KmsEnvelopeAeadKey result) {
         int from_bitField0_ = this.bitField0_;
         if ((from_bitField0_ & 1) != 0) {
            result.version_ = this.version_;
         }

         int to_bitField0_ = 0;
         if ((from_bitField0_ & 2) != 0) {
            result.params_ = this.paramsBuilder_ == null ? this.params_ : (KmsEnvelopeAeadKeyFormat)this.paramsBuilder_.build();
            to_bitField0_ |= 1;
         }

         result.bitField0_ = to_bitField0_;
      }

      public Builder mergeFrom(Message other) {
         if (other instanceof KmsEnvelopeAeadKey) {
            return this.mergeFrom((KmsEnvelopeAeadKey)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(KmsEnvelopeAeadKey other) {
         if (other == KmsEnvelopeAeadKey.getDefaultInstance()) {
            return this;
         } else {
            if (other.getVersion() != 0) {
               this.setVersion(other.getVersion());
            }

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
                     case 8:
                        this.version_ = input.readUInt32();
                        this.bitField0_ |= 1;
                        break;
                     case 18:
                        input.readMessage(this.getParamsFieldBuilder().getBuilder(), extensionRegistry);
                        this.bitField0_ |= 2;
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

      public int getVersion() {
         return this.version_;
      }

      public Builder setVersion(int value) {
         this.version_ = value;
         this.bitField0_ |= 1;
         this.onChanged();
         return this;
      }

      public Builder clearVersion() {
         this.bitField0_ &= -2;
         this.version_ = 0;
         this.onChanged();
         return this;
      }

      public boolean hasParams() {
         return (this.bitField0_ & 2) != 0;
      }

      public KmsEnvelopeAeadKeyFormat getParams() {
         if (this.paramsBuilder_ == null) {
            return this.params_ == null ? KmsEnvelopeAeadKeyFormat.getDefaultInstance() : this.params_;
         } else {
            return (KmsEnvelopeAeadKeyFormat)this.paramsBuilder_.getMessage();
         }
      }

      public Builder setParams(KmsEnvelopeAeadKeyFormat value) {
         if (this.paramsBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.params_ = value;
         } else {
            this.paramsBuilder_.setMessage(value);
         }

         this.bitField0_ |= 2;
         this.onChanged();
         return this;
      }

      public Builder setParams(KmsEnvelopeAeadKeyFormat.Builder builderForValue) {
         if (this.paramsBuilder_ == null) {
            this.params_ = builderForValue.build();
         } else {
            this.paramsBuilder_.setMessage(builderForValue.build());
         }

         this.bitField0_ |= 2;
         this.onChanged();
         return this;
      }

      public Builder mergeParams(KmsEnvelopeAeadKeyFormat value) {
         if (this.paramsBuilder_ == null) {
            if ((this.bitField0_ & 2) != 0 && this.params_ != null && this.params_ != KmsEnvelopeAeadKeyFormat.getDefaultInstance()) {
               this.getParamsBuilder().mergeFrom(value);
            } else {
               this.params_ = value;
            }
         } else {
            this.paramsBuilder_.mergeFrom(value);
         }

         if (this.params_ != null) {
            this.bitField0_ |= 2;
            this.onChanged();
         }

         return this;
      }

      public Builder clearParams() {
         this.bitField0_ &= -3;
         this.params_ = null;
         if (this.paramsBuilder_ != null) {
            this.paramsBuilder_.dispose();
            this.paramsBuilder_ = null;
         }

         this.onChanged();
         return this;
      }

      public KmsEnvelopeAeadKeyFormat.Builder getParamsBuilder() {
         this.bitField0_ |= 2;
         this.onChanged();
         return (KmsEnvelopeAeadKeyFormat.Builder)this.getParamsFieldBuilder().getBuilder();
      }

      public KmsEnvelopeAeadKeyFormatOrBuilder getParamsOrBuilder() {
         if (this.paramsBuilder_ != null) {
            return (KmsEnvelopeAeadKeyFormatOrBuilder)this.paramsBuilder_.getMessageOrBuilder();
         } else {
            return this.params_ == null ? KmsEnvelopeAeadKeyFormat.getDefaultInstance() : this.params_;
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
