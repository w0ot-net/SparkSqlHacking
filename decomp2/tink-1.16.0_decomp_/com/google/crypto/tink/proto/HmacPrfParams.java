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

public final class HmacPrfParams extends GeneratedMessage implements HmacPrfParamsOrBuilder {
   private static final long serialVersionUID = 0L;
   public static final int HASH_FIELD_NUMBER = 1;
   private int hash_;
   private byte memoizedIsInitialized;
   private static final HmacPrfParams DEFAULT_INSTANCE;
   private static final Parser PARSER;

   private HmacPrfParams(GeneratedMessage.Builder builder) {
      super(builder);
      this.hash_ = 0;
      this.memoizedIsInitialized = -1;
   }

   private HmacPrfParams() {
      this.hash_ = 0;
      this.memoizedIsInitialized = -1;
      this.hash_ = 0;
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return HmacPrf.internal_static_google_crypto_tink_HmacPrfParams_descriptor;
   }

   protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
      return HmacPrf.internal_static_google_crypto_tink_HmacPrfParams_fieldAccessorTable.ensureFieldAccessorsInitialized(HmacPrfParams.class, Builder.class);
   }

   public int getHashValue() {
      return this.hash_;
   }

   public HashType getHash() {
      HashType result = HashType.forNumber(this.hash_);
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
      if (this.hash_ != HashType.UNKNOWN_HASH.getNumber()) {
         output.writeEnum(1, this.hash_);
      }

      this.getUnknownFields().writeTo(output);
   }

   public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1) {
         return size;
      } else {
         size = 0;
         if (this.hash_ != HashType.UNKNOWN_HASH.getNumber()) {
            size += CodedOutputStream.computeEnumSize(1, this.hash_);
         }

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof HmacPrfParams)) {
         return super.equals(obj);
      } else {
         HmacPrfParams other = (HmacPrfParams)obj;
         if (this.hash_ != other.hash_) {
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
         hash = 53 * hash + this.hash_;
         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static HmacPrfParams parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (HmacPrfParams)PARSER.parseFrom(data);
   }

   public static HmacPrfParams parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (HmacPrfParams)PARSER.parseFrom(data, extensionRegistry);
   }

   public static HmacPrfParams parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (HmacPrfParams)PARSER.parseFrom(data);
   }

   public static HmacPrfParams parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (HmacPrfParams)PARSER.parseFrom(data, extensionRegistry);
   }

   public static HmacPrfParams parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (HmacPrfParams)PARSER.parseFrom(data);
   }

   public static HmacPrfParams parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (HmacPrfParams)PARSER.parseFrom(data, extensionRegistry);
   }

   public static HmacPrfParams parseFrom(InputStream input) throws IOException {
      return (HmacPrfParams)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static HmacPrfParams parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (HmacPrfParams)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static HmacPrfParams parseDelimitedFrom(InputStream input) throws IOException {
      return (HmacPrfParams)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
   }

   public static HmacPrfParams parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (HmacPrfParams)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static HmacPrfParams parseFrom(CodedInputStream input) throws IOException {
      return (HmacPrfParams)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static HmacPrfParams parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (HmacPrfParams)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(HmacPrfParams prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static HmacPrfParams getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public HmacPrfParams getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", HmacPrfParams.class.getName());
      DEFAULT_INSTANCE = new HmacPrfParams();
      PARSER = new AbstractParser() {
         public HmacPrfParams parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            Builder builder = HmacPrfParams.newBuilder();

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

   public static final class Builder extends GeneratedMessage.Builder implements HmacPrfParamsOrBuilder {
      private int bitField0_;
      private int hash_;

      public static final Descriptors.Descriptor getDescriptor() {
         return HmacPrf.internal_static_google_crypto_tink_HmacPrfParams_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return HmacPrf.internal_static_google_crypto_tink_HmacPrfParams_fieldAccessorTable.ensureFieldAccessorsInitialized(HmacPrfParams.class, Builder.class);
      }

      private Builder() {
         this.hash_ = 0;
      }

      private Builder(AbstractMessage.BuilderParent parent) {
         super(parent);
         this.hash_ = 0;
      }

      public Builder clear() {
         super.clear();
         this.bitField0_ = 0;
         this.hash_ = 0;
         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return HmacPrf.internal_static_google_crypto_tink_HmacPrfParams_descriptor;
      }

      public HmacPrfParams getDefaultInstanceForType() {
         return HmacPrfParams.getDefaultInstance();
      }

      public HmacPrfParams build() {
         HmacPrfParams result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public HmacPrfParams buildPartial() {
         HmacPrfParams result = new HmacPrfParams(this);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartial0(HmacPrfParams result) {
         int from_bitField0_ = this.bitField0_;
         if ((from_bitField0_ & 1) != 0) {
            result.hash_ = this.hash_;
         }

      }

      public Builder mergeFrom(Message other) {
         if (other instanceof HmacPrfParams) {
            return this.mergeFrom((HmacPrfParams)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(HmacPrfParams other) {
         if (other == HmacPrfParams.getDefaultInstance()) {
            return this;
         } else {
            if (other.hash_ != 0) {
               this.setHashValue(other.getHashValue());
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
                        this.hash_ = input.readEnum();
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

      public int getHashValue() {
         return this.hash_;
      }

      public Builder setHashValue(int value) {
         this.hash_ = value;
         this.bitField0_ |= 1;
         this.onChanged();
         return this;
      }

      public HashType getHash() {
         HashType result = HashType.forNumber(this.hash_);
         return result == null ? HashType.UNRECOGNIZED : result;
      }

      public Builder setHash(HashType value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.bitField0_ |= 1;
            this.hash_ = value.getNumber();
            this.onChanged();
            return this;
         }
      }

      public Builder clearHash() {
         this.bitField0_ &= -2;
         this.hash_ = 0;
         this.onChanged();
         return this;
      }
   }
}
