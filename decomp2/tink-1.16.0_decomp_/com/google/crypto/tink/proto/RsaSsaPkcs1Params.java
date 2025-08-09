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
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.RuntimeVersion.RuntimeDomain;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class RsaSsaPkcs1Params extends GeneratedMessage implements RsaSsaPkcs1ParamsOrBuilder {
   private static final long serialVersionUID = 0L;
   public static final int HASH_TYPE_FIELD_NUMBER = 1;
   private int hashType_;
   private byte memoizedIsInitialized;
   private static final RsaSsaPkcs1Params DEFAULT_INSTANCE;
   private static final Parser PARSER;

   private RsaSsaPkcs1Params(GeneratedMessage.Builder builder) {
      super(builder);
      this.hashType_ = 0;
      this.memoizedIsInitialized = -1;
   }

   private RsaSsaPkcs1Params() {
      this.hashType_ = 0;
      this.memoizedIsInitialized = -1;
      this.hashType_ = 0;
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return RsaSsaPkcs1.internal_static_google_crypto_tink_RsaSsaPkcs1Params_descriptor;
   }

   protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
      return RsaSsaPkcs1.internal_static_google_crypto_tink_RsaSsaPkcs1Params_fieldAccessorTable.ensureFieldAccessorsInitialized(RsaSsaPkcs1Params.class, Builder.class);
   }

   public int getHashTypeValue() {
      return this.hashType_;
   }

   public HashType getHashType() {
      HashType result = HashType.forNumber(this.hashType_);
      return result == null ? HashType.UNRECOGNIZED : result;
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
      if (this.hashType_ != HashType.UNKNOWN_HASH.getNumber()) {
         output.writeEnum(1, this.hashType_);
      }

      this.getUnknownFields().writeTo(output);
   }

   public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1) {
         return size;
      } else {
         size = 0;
         if (this.hashType_ != HashType.UNKNOWN_HASH.getNumber()) {
            size += CodedOutputStream.computeEnumSize(1, this.hashType_);
         }

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof RsaSsaPkcs1Params)) {
         return super.equals(obj);
      } else {
         RsaSsaPkcs1Params other = (RsaSsaPkcs1Params)obj;
         if (this.hashType_ != other.hashType_) {
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
         hash = 53 * hash + this.hashType_;
         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static RsaSsaPkcs1Params parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (RsaSsaPkcs1Params)PARSER.parseFrom(data);
   }

   public static RsaSsaPkcs1Params parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (RsaSsaPkcs1Params)PARSER.parseFrom(data, extensionRegistry);
   }

   public static RsaSsaPkcs1Params parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (RsaSsaPkcs1Params)PARSER.parseFrom(data);
   }

   public static RsaSsaPkcs1Params parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (RsaSsaPkcs1Params)PARSER.parseFrom(data, extensionRegistry);
   }

   public static RsaSsaPkcs1Params parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (RsaSsaPkcs1Params)PARSER.parseFrom(data);
   }

   public static RsaSsaPkcs1Params parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (RsaSsaPkcs1Params)PARSER.parseFrom(data, extensionRegistry);
   }

   public static RsaSsaPkcs1Params parseFrom(InputStream input) throws IOException {
      return (RsaSsaPkcs1Params)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static RsaSsaPkcs1Params parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (RsaSsaPkcs1Params)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static RsaSsaPkcs1Params parseDelimitedFrom(InputStream input) throws IOException {
      return (RsaSsaPkcs1Params)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
   }

   public static RsaSsaPkcs1Params parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (RsaSsaPkcs1Params)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static RsaSsaPkcs1Params parseFrom(CodedInputStream input) throws IOException {
      return (RsaSsaPkcs1Params)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static RsaSsaPkcs1Params parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (RsaSsaPkcs1Params)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(RsaSsaPkcs1Params prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static RsaSsaPkcs1Params getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public RsaSsaPkcs1Params getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", RsaSsaPkcs1Params.class.getName());
      DEFAULT_INSTANCE = new RsaSsaPkcs1Params();
      PARSER = new AbstractParser() {
         public RsaSsaPkcs1Params parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            Builder builder = RsaSsaPkcs1Params.newBuilder();

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

   public static final class Builder extends GeneratedMessage.Builder implements RsaSsaPkcs1ParamsOrBuilder {
      private int bitField0_;
      private int hashType_;

      public static final Descriptors.Descriptor getDescriptor() {
         return RsaSsaPkcs1.internal_static_google_crypto_tink_RsaSsaPkcs1Params_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return RsaSsaPkcs1.internal_static_google_crypto_tink_RsaSsaPkcs1Params_fieldAccessorTable.ensureFieldAccessorsInitialized(RsaSsaPkcs1Params.class, Builder.class);
      }

      private Builder() {
         this.hashType_ = 0;
      }

      private Builder(AbstractMessage.BuilderParent parent) {
         super(parent);
         this.hashType_ = 0;
      }

      public Builder clear() {
         super.clear();
         this.bitField0_ = 0;
         this.hashType_ = 0;
         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return RsaSsaPkcs1.internal_static_google_crypto_tink_RsaSsaPkcs1Params_descriptor;
      }

      public RsaSsaPkcs1Params getDefaultInstanceForType() {
         return RsaSsaPkcs1Params.getDefaultInstance();
      }

      public RsaSsaPkcs1Params build() {
         RsaSsaPkcs1Params result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public RsaSsaPkcs1Params buildPartial() {
         RsaSsaPkcs1Params result = new RsaSsaPkcs1Params(this);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartial0(RsaSsaPkcs1Params result) {
         int from_bitField0_ = this.bitField0_;
         if ((from_bitField0_ & 1) != 0) {
            result.hashType_ = this.hashType_;
         }

      }

      public Builder mergeFrom(Message other) {
         if (other instanceof RsaSsaPkcs1Params) {
            return this.mergeFrom((RsaSsaPkcs1Params)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(RsaSsaPkcs1Params other) {
         if (other == RsaSsaPkcs1Params.getDefaultInstance()) {
            return this;
         } else {
            if (other.hashType_ != 0) {
               this.setHashTypeValue(other.getHashTypeValue());
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
                        this.hashType_ = input.readEnum();
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

      public int getHashTypeValue() {
         return this.hashType_;
      }

      public Builder setHashTypeValue(int value) {
         this.hashType_ = value;
         this.bitField0_ |= 1;
         this.onChanged();
         return this;
      }

      public HashType getHashType() {
         HashType result = HashType.forNumber(this.hashType_);
         return result == null ? HashType.UNRECOGNIZED : result;
      }

      public Builder setHashType(HashType value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.bitField0_ |= 1;
            this.hashType_ = value.getNumber();
            this.onChanged();
            return this;
         }
      }

      public Builder clearHashType() {
         this.bitField0_ &= -2;
         this.hashType_ = 0;
         this.onChanged();
         return this;
      }
   }
}
