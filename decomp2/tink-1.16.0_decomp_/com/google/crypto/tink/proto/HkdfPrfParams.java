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

public final class HkdfPrfParams extends GeneratedMessage implements HkdfPrfParamsOrBuilder {
   private static final long serialVersionUID = 0L;
   public static final int HASH_FIELD_NUMBER = 1;
   private int hash_;
   public static final int SALT_FIELD_NUMBER = 2;
   private ByteString salt_;
   private byte memoizedIsInitialized;
   private static final HkdfPrfParams DEFAULT_INSTANCE;
   private static final Parser PARSER;

   private HkdfPrfParams(GeneratedMessage.Builder builder) {
      super(builder);
      this.hash_ = 0;
      this.salt_ = ByteString.EMPTY;
      this.memoizedIsInitialized = -1;
   }

   private HkdfPrfParams() {
      this.hash_ = 0;
      this.salt_ = ByteString.EMPTY;
      this.memoizedIsInitialized = -1;
      this.hash_ = 0;
      this.salt_ = ByteString.EMPTY;
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return HkdfPrf.internal_static_google_crypto_tink_HkdfPrfParams_descriptor;
   }

   protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
      return HkdfPrf.internal_static_google_crypto_tink_HkdfPrfParams_fieldAccessorTable.ensureFieldAccessorsInitialized(HkdfPrfParams.class, Builder.class);
   }

   public int getHashValue() {
      return this.hash_;
   }

   public HashType getHash() {
      HashType result = HashType.forNumber(this.hash_);
      return result == null ? HashType.UNRECOGNIZED : result;
   }

   public ByteString getSalt() {
      return this.salt_;
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

      if (!this.salt_.isEmpty()) {
         output.writeBytes(2, this.salt_);
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

         if (!this.salt_.isEmpty()) {
            size += CodedOutputStream.computeBytesSize(2, this.salt_);
         }

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof HkdfPrfParams)) {
         return super.equals(obj);
      } else {
         HkdfPrfParams other = (HkdfPrfParams)obj;
         if (this.hash_ != other.hash_) {
            return false;
         } else if (!this.getSalt().equals(other.getSalt())) {
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
         hash = 37 * hash + 2;
         hash = 53 * hash + this.getSalt().hashCode();
         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static HkdfPrfParams parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (HkdfPrfParams)PARSER.parseFrom(data);
   }

   public static HkdfPrfParams parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (HkdfPrfParams)PARSER.parseFrom(data, extensionRegistry);
   }

   public static HkdfPrfParams parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (HkdfPrfParams)PARSER.parseFrom(data);
   }

   public static HkdfPrfParams parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (HkdfPrfParams)PARSER.parseFrom(data, extensionRegistry);
   }

   public static HkdfPrfParams parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (HkdfPrfParams)PARSER.parseFrom(data);
   }

   public static HkdfPrfParams parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (HkdfPrfParams)PARSER.parseFrom(data, extensionRegistry);
   }

   public static HkdfPrfParams parseFrom(InputStream input) throws IOException {
      return (HkdfPrfParams)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static HkdfPrfParams parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (HkdfPrfParams)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static HkdfPrfParams parseDelimitedFrom(InputStream input) throws IOException {
      return (HkdfPrfParams)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
   }

   public static HkdfPrfParams parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (HkdfPrfParams)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static HkdfPrfParams parseFrom(CodedInputStream input) throws IOException {
      return (HkdfPrfParams)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static HkdfPrfParams parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (HkdfPrfParams)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(HkdfPrfParams prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static HkdfPrfParams getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public HkdfPrfParams getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", HkdfPrfParams.class.getName());
      DEFAULT_INSTANCE = new HkdfPrfParams();
      PARSER = new AbstractParser() {
         public HkdfPrfParams parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            Builder builder = HkdfPrfParams.newBuilder();

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

   public static final class Builder extends GeneratedMessage.Builder implements HkdfPrfParamsOrBuilder {
      private int bitField0_;
      private int hash_;
      private ByteString salt_;

      public static final Descriptors.Descriptor getDescriptor() {
         return HkdfPrf.internal_static_google_crypto_tink_HkdfPrfParams_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return HkdfPrf.internal_static_google_crypto_tink_HkdfPrfParams_fieldAccessorTable.ensureFieldAccessorsInitialized(HkdfPrfParams.class, Builder.class);
      }

      private Builder() {
         this.hash_ = 0;
         this.salt_ = ByteString.EMPTY;
      }

      private Builder(AbstractMessage.BuilderParent parent) {
         super(parent);
         this.hash_ = 0;
         this.salt_ = ByteString.EMPTY;
      }

      public Builder clear() {
         super.clear();
         this.bitField0_ = 0;
         this.hash_ = 0;
         this.salt_ = ByteString.EMPTY;
         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return HkdfPrf.internal_static_google_crypto_tink_HkdfPrfParams_descriptor;
      }

      public HkdfPrfParams getDefaultInstanceForType() {
         return HkdfPrfParams.getDefaultInstance();
      }

      public HkdfPrfParams build() {
         HkdfPrfParams result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public HkdfPrfParams buildPartial() {
         HkdfPrfParams result = new HkdfPrfParams(this);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartial0(HkdfPrfParams result) {
         int from_bitField0_ = this.bitField0_;
         if ((from_bitField0_ & 1) != 0) {
            result.hash_ = this.hash_;
         }

         if ((from_bitField0_ & 2) != 0) {
            result.salt_ = this.salt_;
         }

      }

      public Builder mergeFrom(Message other) {
         if (other instanceof HkdfPrfParams) {
            return this.mergeFrom((HkdfPrfParams)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(HkdfPrfParams other) {
         if (other == HkdfPrfParams.getDefaultInstance()) {
            return this;
         } else {
            if (other.hash_ != 0) {
               this.setHashValue(other.getHashValue());
            }

            if (other.getSalt() != ByteString.EMPTY) {
               this.setSalt(other.getSalt());
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
                     case 18:
                        this.salt_ = input.readBytes();
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

      public ByteString getSalt() {
         return this.salt_;
      }

      public Builder setSalt(ByteString value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.salt_ = value;
            this.bitField0_ |= 2;
            this.onChanged();
            return this;
         }
      }

      public Builder clearSalt() {
         this.bitField0_ &= -3;
         this.salt_ = HkdfPrfParams.getDefaultInstance().getSalt();
         this.onChanged();
         return this;
      }
   }
}
