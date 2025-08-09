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

public final class EciesAeadDemParams extends GeneratedMessage implements EciesAeadDemParamsOrBuilder {
   private static final long serialVersionUID = 0L;
   private int bitField0_;
   public static final int AEAD_DEM_FIELD_NUMBER = 2;
   private KeyTemplate aeadDem_;
   private byte memoizedIsInitialized;
   private static final EciesAeadDemParams DEFAULT_INSTANCE;
   private static final Parser PARSER;

   private EciesAeadDemParams(GeneratedMessage.Builder builder) {
      super(builder);
      this.memoizedIsInitialized = -1;
   }

   private EciesAeadDemParams() {
      this.memoizedIsInitialized = -1;
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return EciesAeadHkdf.internal_static_google_crypto_tink_EciesAeadDemParams_descriptor;
   }

   protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
      return EciesAeadHkdf.internal_static_google_crypto_tink_EciesAeadDemParams_fieldAccessorTable.ensureFieldAccessorsInitialized(EciesAeadDemParams.class, Builder.class);
   }

   public boolean hasAeadDem() {
      return (this.bitField0_ & 1) != 0;
   }

   public KeyTemplate getAeadDem() {
      return this.aeadDem_ == null ? KeyTemplate.getDefaultInstance() : this.aeadDem_;
   }

   public KeyTemplateOrBuilder getAeadDemOrBuilder() {
      return this.aeadDem_ == null ? KeyTemplate.getDefaultInstance() : this.aeadDem_;
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
         output.writeMessage(2, this.getAeadDem());
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
            size += CodedOutputStream.computeMessageSize(2, this.getAeadDem());
         }

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof EciesAeadDemParams)) {
         return super.equals(obj);
      } else {
         EciesAeadDemParams other = (EciesAeadDemParams)obj;
         if (this.hasAeadDem() != other.hasAeadDem()) {
            return false;
         } else if (this.hasAeadDem() && !this.getAeadDem().equals(other.getAeadDem())) {
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
         if (this.hasAeadDem()) {
            hash = 37 * hash + 2;
            hash = 53 * hash + this.getAeadDem().hashCode();
         }

         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static EciesAeadDemParams parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (EciesAeadDemParams)PARSER.parseFrom(data);
   }

   public static EciesAeadDemParams parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (EciesAeadDemParams)PARSER.parseFrom(data, extensionRegistry);
   }

   public static EciesAeadDemParams parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (EciesAeadDemParams)PARSER.parseFrom(data);
   }

   public static EciesAeadDemParams parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (EciesAeadDemParams)PARSER.parseFrom(data, extensionRegistry);
   }

   public static EciesAeadDemParams parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (EciesAeadDemParams)PARSER.parseFrom(data);
   }

   public static EciesAeadDemParams parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (EciesAeadDemParams)PARSER.parseFrom(data, extensionRegistry);
   }

   public static EciesAeadDemParams parseFrom(InputStream input) throws IOException {
      return (EciesAeadDemParams)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static EciesAeadDemParams parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (EciesAeadDemParams)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static EciesAeadDemParams parseDelimitedFrom(InputStream input) throws IOException {
      return (EciesAeadDemParams)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
   }

   public static EciesAeadDemParams parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (EciesAeadDemParams)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static EciesAeadDemParams parseFrom(CodedInputStream input) throws IOException {
      return (EciesAeadDemParams)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static EciesAeadDemParams parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (EciesAeadDemParams)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(EciesAeadDemParams prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static EciesAeadDemParams getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public EciesAeadDemParams getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", EciesAeadDemParams.class.getName());
      DEFAULT_INSTANCE = new EciesAeadDemParams();
      PARSER = new AbstractParser() {
         public EciesAeadDemParams parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            Builder builder = EciesAeadDemParams.newBuilder();

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

   public static final class Builder extends GeneratedMessage.Builder implements EciesAeadDemParamsOrBuilder {
      private int bitField0_;
      private KeyTemplate aeadDem_;
      private SingleFieldBuilder aeadDemBuilder_;

      public static final Descriptors.Descriptor getDescriptor() {
         return EciesAeadHkdf.internal_static_google_crypto_tink_EciesAeadDemParams_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return EciesAeadHkdf.internal_static_google_crypto_tink_EciesAeadDemParams_fieldAccessorTable.ensureFieldAccessorsInitialized(EciesAeadDemParams.class, Builder.class);
      }

      private Builder() {
         this.maybeForceBuilderInitialization();
      }

      private Builder(AbstractMessage.BuilderParent parent) {
         super(parent);
         this.maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization() {
         if (EciesAeadDemParams.alwaysUseFieldBuilders) {
            this.getAeadDemFieldBuilder();
         }

      }

      public Builder clear() {
         super.clear();
         this.bitField0_ = 0;
         this.aeadDem_ = null;
         if (this.aeadDemBuilder_ != null) {
            this.aeadDemBuilder_.dispose();
            this.aeadDemBuilder_ = null;
         }

         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return EciesAeadHkdf.internal_static_google_crypto_tink_EciesAeadDemParams_descriptor;
      }

      public EciesAeadDemParams getDefaultInstanceForType() {
         return EciesAeadDemParams.getDefaultInstance();
      }

      public EciesAeadDemParams build() {
         EciesAeadDemParams result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public EciesAeadDemParams buildPartial() {
         EciesAeadDemParams result = new EciesAeadDemParams(this);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartial0(EciesAeadDemParams result) {
         int from_bitField0_ = this.bitField0_;
         int to_bitField0_ = 0;
         if ((from_bitField0_ & 1) != 0) {
            result.aeadDem_ = this.aeadDemBuilder_ == null ? this.aeadDem_ : (KeyTemplate)this.aeadDemBuilder_.build();
            to_bitField0_ |= 1;
         }

         result.bitField0_ = to_bitField0_;
      }

      public Builder mergeFrom(Message other) {
         if (other instanceof EciesAeadDemParams) {
            return this.mergeFrom((EciesAeadDemParams)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(EciesAeadDemParams other) {
         if (other == EciesAeadDemParams.getDefaultInstance()) {
            return this;
         } else {
            if (other.hasAeadDem()) {
               this.mergeAeadDem(other.getAeadDem());
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
                     case 18:
                        input.readMessage(this.getAeadDemFieldBuilder().getBuilder(), extensionRegistry);
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

      public boolean hasAeadDem() {
         return (this.bitField0_ & 1) != 0;
      }

      public KeyTemplate getAeadDem() {
         if (this.aeadDemBuilder_ == null) {
            return this.aeadDem_ == null ? KeyTemplate.getDefaultInstance() : this.aeadDem_;
         } else {
            return (KeyTemplate)this.aeadDemBuilder_.getMessage();
         }
      }

      public Builder setAeadDem(KeyTemplate value) {
         if (this.aeadDemBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.aeadDem_ = value;
         } else {
            this.aeadDemBuilder_.setMessage(value);
         }

         this.bitField0_ |= 1;
         this.onChanged();
         return this;
      }

      public Builder setAeadDem(KeyTemplate.Builder builderForValue) {
         if (this.aeadDemBuilder_ == null) {
            this.aeadDem_ = builderForValue.build();
         } else {
            this.aeadDemBuilder_.setMessage(builderForValue.build());
         }

         this.bitField0_ |= 1;
         this.onChanged();
         return this;
      }

      public Builder mergeAeadDem(KeyTemplate value) {
         if (this.aeadDemBuilder_ == null) {
            if ((this.bitField0_ & 1) != 0 && this.aeadDem_ != null && this.aeadDem_ != KeyTemplate.getDefaultInstance()) {
               this.getAeadDemBuilder().mergeFrom(value);
            } else {
               this.aeadDem_ = value;
            }
         } else {
            this.aeadDemBuilder_.mergeFrom(value);
         }

         if (this.aeadDem_ != null) {
            this.bitField0_ |= 1;
            this.onChanged();
         }

         return this;
      }

      public Builder clearAeadDem() {
         this.bitField0_ &= -2;
         this.aeadDem_ = null;
         if (this.aeadDemBuilder_ != null) {
            this.aeadDemBuilder_.dispose();
            this.aeadDemBuilder_ = null;
         }

         this.onChanged();
         return this;
      }

      public KeyTemplate.Builder getAeadDemBuilder() {
         this.bitField0_ |= 1;
         this.onChanged();
         return (KeyTemplate.Builder)this.getAeadDemFieldBuilder().getBuilder();
      }

      public KeyTemplateOrBuilder getAeadDemOrBuilder() {
         if (this.aeadDemBuilder_ != null) {
            return (KeyTemplateOrBuilder)this.aeadDemBuilder_.getMessageOrBuilder();
         } else {
            return this.aeadDem_ == null ? KeyTemplate.getDefaultInstance() : this.aeadDem_;
         }
      }

      private SingleFieldBuilder getAeadDemFieldBuilder() {
         if (this.aeadDemBuilder_ == null) {
            this.aeadDemBuilder_ = new SingleFieldBuilder(this.getAeadDem(), this.getParentForChildren(), this.isClean());
            this.aeadDem_ = null;
         }

         return this.aeadDemBuilder_;
      }
   }
}
