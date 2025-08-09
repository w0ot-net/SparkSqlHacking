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

public final class RsaSsaPssParams extends GeneratedMessage implements RsaSsaPssParamsOrBuilder {
   private static final long serialVersionUID = 0L;
   public static final int SIG_HASH_FIELD_NUMBER = 1;
   private int sigHash_;
   public static final int MGF1_HASH_FIELD_NUMBER = 2;
   private int mgf1Hash_;
   public static final int SALT_LENGTH_FIELD_NUMBER = 3;
   private int saltLength_;
   private byte memoizedIsInitialized;
   private static final RsaSsaPssParams DEFAULT_INSTANCE;
   private static final Parser PARSER;

   private RsaSsaPssParams(GeneratedMessage.Builder builder) {
      super(builder);
      this.sigHash_ = 0;
      this.mgf1Hash_ = 0;
      this.saltLength_ = 0;
      this.memoizedIsInitialized = -1;
   }

   private RsaSsaPssParams() {
      this.sigHash_ = 0;
      this.mgf1Hash_ = 0;
      this.saltLength_ = 0;
      this.memoizedIsInitialized = -1;
      this.sigHash_ = 0;
      this.mgf1Hash_ = 0;
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return RsaSsaPss.internal_static_google_crypto_tink_RsaSsaPssParams_descriptor;
   }

   protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
      return RsaSsaPss.internal_static_google_crypto_tink_RsaSsaPssParams_fieldAccessorTable.ensureFieldAccessorsInitialized(RsaSsaPssParams.class, Builder.class);
   }

   public int getSigHashValue() {
      return this.sigHash_;
   }

   public HashType getSigHash() {
      HashType result = HashType.forNumber(this.sigHash_);
      return result == null ? HashType.UNRECOGNIZED : result;
   }

   public int getMgf1HashValue() {
      return this.mgf1Hash_;
   }

   public HashType getMgf1Hash() {
      HashType result = HashType.forNumber(this.mgf1Hash_);
      return result == null ? HashType.UNRECOGNIZED : result;
   }

   public int getSaltLength() {
      return this.saltLength_;
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
      if (this.sigHash_ != HashType.UNKNOWN_HASH.getNumber()) {
         output.writeEnum(1, this.sigHash_);
      }

      if (this.mgf1Hash_ != HashType.UNKNOWN_HASH.getNumber()) {
         output.writeEnum(2, this.mgf1Hash_);
      }

      if (this.saltLength_ != 0) {
         output.writeInt32(3, this.saltLength_);
      }

      this.getUnknownFields().writeTo(output);
   }

   public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1) {
         return size;
      } else {
         size = 0;
         if (this.sigHash_ != HashType.UNKNOWN_HASH.getNumber()) {
            size += CodedOutputStream.computeEnumSize(1, this.sigHash_);
         }

         if (this.mgf1Hash_ != HashType.UNKNOWN_HASH.getNumber()) {
            size += CodedOutputStream.computeEnumSize(2, this.mgf1Hash_);
         }

         if (this.saltLength_ != 0) {
            size += CodedOutputStream.computeInt32Size(3, this.saltLength_);
         }

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof RsaSsaPssParams)) {
         return super.equals(obj);
      } else {
         RsaSsaPssParams other = (RsaSsaPssParams)obj;
         if (this.sigHash_ != other.sigHash_) {
            return false;
         } else if (this.mgf1Hash_ != other.mgf1Hash_) {
            return false;
         } else if (this.getSaltLength() != other.getSaltLength()) {
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
         hash = 53 * hash + this.sigHash_;
         hash = 37 * hash + 2;
         hash = 53 * hash + this.mgf1Hash_;
         hash = 37 * hash + 3;
         hash = 53 * hash + this.getSaltLength();
         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static RsaSsaPssParams parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (RsaSsaPssParams)PARSER.parseFrom(data);
   }

   public static RsaSsaPssParams parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (RsaSsaPssParams)PARSER.parseFrom(data, extensionRegistry);
   }

   public static RsaSsaPssParams parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (RsaSsaPssParams)PARSER.parseFrom(data);
   }

   public static RsaSsaPssParams parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (RsaSsaPssParams)PARSER.parseFrom(data, extensionRegistry);
   }

   public static RsaSsaPssParams parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (RsaSsaPssParams)PARSER.parseFrom(data);
   }

   public static RsaSsaPssParams parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (RsaSsaPssParams)PARSER.parseFrom(data, extensionRegistry);
   }

   public static RsaSsaPssParams parseFrom(InputStream input) throws IOException {
      return (RsaSsaPssParams)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static RsaSsaPssParams parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (RsaSsaPssParams)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static RsaSsaPssParams parseDelimitedFrom(InputStream input) throws IOException {
      return (RsaSsaPssParams)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
   }

   public static RsaSsaPssParams parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (RsaSsaPssParams)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static RsaSsaPssParams parseFrom(CodedInputStream input) throws IOException {
      return (RsaSsaPssParams)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static RsaSsaPssParams parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (RsaSsaPssParams)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(RsaSsaPssParams prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static RsaSsaPssParams getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public RsaSsaPssParams getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", RsaSsaPssParams.class.getName());
      DEFAULT_INSTANCE = new RsaSsaPssParams();
      PARSER = new AbstractParser() {
         public RsaSsaPssParams parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            Builder builder = RsaSsaPssParams.newBuilder();

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

   public static final class Builder extends GeneratedMessage.Builder implements RsaSsaPssParamsOrBuilder {
      private int bitField0_;
      private int sigHash_;
      private int mgf1Hash_;
      private int saltLength_;

      public static final Descriptors.Descriptor getDescriptor() {
         return RsaSsaPss.internal_static_google_crypto_tink_RsaSsaPssParams_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return RsaSsaPss.internal_static_google_crypto_tink_RsaSsaPssParams_fieldAccessorTable.ensureFieldAccessorsInitialized(RsaSsaPssParams.class, Builder.class);
      }

      private Builder() {
         this.sigHash_ = 0;
         this.mgf1Hash_ = 0;
      }

      private Builder(AbstractMessage.BuilderParent parent) {
         super(parent);
         this.sigHash_ = 0;
         this.mgf1Hash_ = 0;
      }

      public Builder clear() {
         super.clear();
         this.bitField0_ = 0;
         this.sigHash_ = 0;
         this.mgf1Hash_ = 0;
         this.saltLength_ = 0;
         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return RsaSsaPss.internal_static_google_crypto_tink_RsaSsaPssParams_descriptor;
      }

      public RsaSsaPssParams getDefaultInstanceForType() {
         return RsaSsaPssParams.getDefaultInstance();
      }

      public RsaSsaPssParams build() {
         RsaSsaPssParams result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public RsaSsaPssParams buildPartial() {
         RsaSsaPssParams result = new RsaSsaPssParams(this);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartial0(RsaSsaPssParams result) {
         int from_bitField0_ = this.bitField0_;
         if ((from_bitField0_ & 1) != 0) {
            result.sigHash_ = this.sigHash_;
         }

         if ((from_bitField0_ & 2) != 0) {
            result.mgf1Hash_ = this.mgf1Hash_;
         }

         if ((from_bitField0_ & 4) != 0) {
            result.saltLength_ = this.saltLength_;
         }

      }

      public Builder mergeFrom(Message other) {
         if (other instanceof RsaSsaPssParams) {
            return this.mergeFrom((RsaSsaPssParams)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(RsaSsaPssParams other) {
         if (other == RsaSsaPssParams.getDefaultInstance()) {
            return this;
         } else {
            if (other.sigHash_ != 0) {
               this.setSigHashValue(other.getSigHashValue());
            }

            if (other.mgf1Hash_ != 0) {
               this.setMgf1HashValue(other.getMgf1HashValue());
            }

            if (other.getSaltLength() != 0) {
               this.setSaltLength(other.getSaltLength());
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
                        this.sigHash_ = input.readEnum();
                        this.bitField0_ |= 1;
                        break;
                     case 16:
                        this.mgf1Hash_ = input.readEnum();
                        this.bitField0_ |= 2;
                        break;
                     case 24:
                        this.saltLength_ = input.readInt32();
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

      public int getSigHashValue() {
         return this.sigHash_;
      }

      public Builder setSigHashValue(int value) {
         this.sigHash_ = value;
         this.bitField0_ |= 1;
         this.onChanged();
         return this;
      }

      public HashType getSigHash() {
         HashType result = HashType.forNumber(this.sigHash_);
         return result == null ? HashType.UNRECOGNIZED : result;
      }

      public Builder setSigHash(HashType value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.bitField0_ |= 1;
            this.sigHash_ = value.getNumber();
            this.onChanged();
            return this;
         }
      }

      public Builder clearSigHash() {
         this.bitField0_ &= -2;
         this.sigHash_ = 0;
         this.onChanged();
         return this;
      }

      public int getMgf1HashValue() {
         return this.mgf1Hash_;
      }

      public Builder setMgf1HashValue(int value) {
         this.mgf1Hash_ = value;
         this.bitField0_ |= 2;
         this.onChanged();
         return this;
      }

      public HashType getMgf1Hash() {
         HashType result = HashType.forNumber(this.mgf1Hash_);
         return result == null ? HashType.UNRECOGNIZED : result;
      }

      public Builder setMgf1Hash(HashType value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.bitField0_ |= 2;
            this.mgf1Hash_ = value.getNumber();
            this.onChanged();
            return this;
         }
      }

      public Builder clearMgf1Hash() {
         this.bitField0_ &= -3;
         this.mgf1Hash_ = 0;
         this.onChanged();
         return this;
      }

      public int getSaltLength() {
         return this.saltLength_;
      }

      public Builder setSaltLength(int value) {
         this.saltLength_ = value;
         this.bitField0_ |= 4;
         this.onChanged();
         return this;
      }

      public Builder clearSaltLength() {
         this.bitField0_ &= -5;
         this.saltLength_ = 0;
         this.onChanged();
         return this;
      }
   }
}
