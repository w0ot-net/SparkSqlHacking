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

public final class RsaSsaPkcs1PublicKey extends GeneratedMessage implements RsaSsaPkcs1PublicKeyOrBuilder {
   private static final long serialVersionUID = 0L;
   private int bitField0_;
   public static final int VERSION_FIELD_NUMBER = 1;
   private int version_;
   public static final int PARAMS_FIELD_NUMBER = 2;
   private RsaSsaPkcs1Params params_;
   public static final int N_FIELD_NUMBER = 3;
   private ByteString n_;
   public static final int E_FIELD_NUMBER = 4;
   private ByteString e_;
   private byte memoizedIsInitialized;
   private static final RsaSsaPkcs1PublicKey DEFAULT_INSTANCE;
   private static final Parser PARSER;

   private RsaSsaPkcs1PublicKey(GeneratedMessage.Builder builder) {
      super(builder);
      this.version_ = 0;
      this.n_ = ByteString.EMPTY;
      this.e_ = ByteString.EMPTY;
      this.memoizedIsInitialized = -1;
   }

   private RsaSsaPkcs1PublicKey() {
      this.version_ = 0;
      this.n_ = ByteString.EMPTY;
      this.e_ = ByteString.EMPTY;
      this.memoizedIsInitialized = -1;
      this.n_ = ByteString.EMPTY;
      this.e_ = ByteString.EMPTY;
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return RsaSsaPkcs1.internal_static_google_crypto_tink_RsaSsaPkcs1PublicKey_descriptor;
   }

   protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
      return RsaSsaPkcs1.internal_static_google_crypto_tink_RsaSsaPkcs1PublicKey_fieldAccessorTable.ensureFieldAccessorsInitialized(RsaSsaPkcs1PublicKey.class, Builder.class);
   }

   public int getVersion() {
      return this.version_;
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

   public ByteString getN() {
      return this.n_;
   }

   public ByteString getE() {
      return this.e_;
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

      if (!this.n_.isEmpty()) {
         output.writeBytes(3, this.n_);
      }

      if (!this.e_.isEmpty()) {
         output.writeBytes(4, this.e_);
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

         if (!this.n_.isEmpty()) {
            size += CodedOutputStream.computeBytesSize(3, this.n_);
         }

         if (!this.e_.isEmpty()) {
            size += CodedOutputStream.computeBytesSize(4, this.e_);
         }

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof RsaSsaPkcs1PublicKey)) {
         return super.equals(obj);
      } else {
         RsaSsaPkcs1PublicKey other = (RsaSsaPkcs1PublicKey)obj;
         if (this.getVersion() != other.getVersion()) {
            return false;
         } else if (this.hasParams() != other.hasParams()) {
            return false;
         } else if (this.hasParams() && !this.getParams().equals(other.getParams())) {
            return false;
         } else if (!this.getN().equals(other.getN())) {
            return false;
         } else if (!this.getE().equals(other.getE())) {
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

         hash = 37 * hash + 3;
         hash = 53 * hash + this.getN().hashCode();
         hash = 37 * hash + 4;
         hash = 53 * hash + this.getE().hashCode();
         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static RsaSsaPkcs1PublicKey parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (RsaSsaPkcs1PublicKey)PARSER.parseFrom(data);
   }

   public static RsaSsaPkcs1PublicKey parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (RsaSsaPkcs1PublicKey)PARSER.parseFrom(data, extensionRegistry);
   }

   public static RsaSsaPkcs1PublicKey parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (RsaSsaPkcs1PublicKey)PARSER.parseFrom(data);
   }

   public static RsaSsaPkcs1PublicKey parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (RsaSsaPkcs1PublicKey)PARSER.parseFrom(data, extensionRegistry);
   }

   public static RsaSsaPkcs1PublicKey parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (RsaSsaPkcs1PublicKey)PARSER.parseFrom(data);
   }

   public static RsaSsaPkcs1PublicKey parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (RsaSsaPkcs1PublicKey)PARSER.parseFrom(data, extensionRegistry);
   }

   public static RsaSsaPkcs1PublicKey parseFrom(InputStream input) throws IOException {
      return (RsaSsaPkcs1PublicKey)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static RsaSsaPkcs1PublicKey parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (RsaSsaPkcs1PublicKey)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static RsaSsaPkcs1PublicKey parseDelimitedFrom(InputStream input) throws IOException {
      return (RsaSsaPkcs1PublicKey)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
   }

   public static RsaSsaPkcs1PublicKey parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (RsaSsaPkcs1PublicKey)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static RsaSsaPkcs1PublicKey parseFrom(CodedInputStream input) throws IOException {
      return (RsaSsaPkcs1PublicKey)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static RsaSsaPkcs1PublicKey parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (RsaSsaPkcs1PublicKey)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(RsaSsaPkcs1PublicKey prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static RsaSsaPkcs1PublicKey getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public RsaSsaPkcs1PublicKey getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", RsaSsaPkcs1PublicKey.class.getName());
      DEFAULT_INSTANCE = new RsaSsaPkcs1PublicKey();
      PARSER = new AbstractParser() {
         public RsaSsaPkcs1PublicKey parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            Builder builder = RsaSsaPkcs1PublicKey.newBuilder();

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

   public static final class Builder extends GeneratedMessage.Builder implements RsaSsaPkcs1PublicKeyOrBuilder {
      private int bitField0_;
      private int version_;
      private RsaSsaPkcs1Params params_;
      private SingleFieldBuilder paramsBuilder_;
      private ByteString n_;
      private ByteString e_;

      public static final Descriptors.Descriptor getDescriptor() {
         return RsaSsaPkcs1.internal_static_google_crypto_tink_RsaSsaPkcs1PublicKey_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return RsaSsaPkcs1.internal_static_google_crypto_tink_RsaSsaPkcs1PublicKey_fieldAccessorTable.ensureFieldAccessorsInitialized(RsaSsaPkcs1PublicKey.class, Builder.class);
      }

      private Builder() {
         this.n_ = ByteString.EMPTY;
         this.e_ = ByteString.EMPTY;
         this.maybeForceBuilderInitialization();
      }

      private Builder(AbstractMessage.BuilderParent parent) {
         super(parent);
         this.n_ = ByteString.EMPTY;
         this.e_ = ByteString.EMPTY;
         this.maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization() {
         if (RsaSsaPkcs1PublicKey.alwaysUseFieldBuilders) {
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

         this.n_ = ByteString.EMPTY;
         this.e_ = ByteString.EMPTY;
         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return RsaSsaPkcs1.internal_static_google_crypto_tink_RsaSsaPkcs1PublicKey_descriptor;
      }

      public RsaSsaPkcs1PublicKey getDefaultInstanceForType() {
         return RsaSsaPkcs1PublicKey.getDefaultInstance();
      }

      public RsaSsaPkcs1PublicKey build() {
         RsaSsaPkcs1PublicKey result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public RsaSsaPkcs1PublicKey buildPartial() {
         RsaSsaPkcs1PublicKey result = new RsaSsaPkcs1PublicKey(this);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartial0(RsaSsaPkcs1PublicKey result) {
         int from_bitField0_ = this.bitField0_;
         if ((from_bitField0_ & 1) != 0) {
            result.version_ = this.version_;
         }

         int to_bitField0_ = 0;
         if ((from_bitField0_ & 2) != 0) {
            result.params_ = this.paramsBuilder_ == null ? this.params_ : (RsaSsaPkcs1Params)this.paramsBuilder_.build();
            to_bitField0_ |= 1;
         }

         if ((from_bitField0_ & 4) != 0) {
            result.n_ = this.n_;
         }

         if ((from_bitField0_ & 8) != 0) {
            result.e_ = this.e_;
         }

         result.bitField0_ = to_bitField0_;
      }

      public Builder mergeFrom(Message other) {
         if (other instanceof RsaSsaPkcs1PublicKey) {
            return this.mergeFrom((RsaSsaPkcs1PublicKey)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(RsaSsaPkcs1PublicKey other) {
         if (other == RsaSsaPkcs1PublicKey.getDefaultInstance()) {
            return this;
         } else {
            if (other.getVersion() != 0) {
               this.setVersion(other.getVersion());
            }

            if (other.hasParams()) {
               this.mergeParams(other.getParams());
            }

            if (other.getN() != ByteString.EMPTY) {
               this.setN(other.getN());
            }

            if (other.getE() != ByteString.EMPTY) {
               this.setE(other.getE());
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
                     case 26:
                        this.n_ = input.readBytes();
                        this.bitField0_ |= 4;
                        break;
                     case 34:
                        this.e_ = input.readBytes();
                        this.bitField0_ |= 8;
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

         this.bitField0_ |= 2;
         this.onChanged();
         return this;
      }

      public Builder setParams(RsaSsaPkcs1Params.Builder builderForValue) {
         if (this.paramsBuilder_ == null) {
            this.params_ = builderForValue.build();
         } else {
            this.paramsBuilder_.setMessage(builderForValue.build());
         }

         this.bitField0_ |= 2;
         this.onChanged();
         return this;
      }

      public Builder mergeParams(RsaSsaPkcs1Params value) {
         if (this.paramsBuilder_ == null) {
            if ((this.bitField0_ & 2) != 0 && this.params_ != null && this.params_ != RsaSsaPkcs1Params.getDefaultInstance()) {
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

      public RsaSsaPkcs1Params.Builder getParamsBuilder() {
         this.bitField0_ |= 2;
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

      public ByteString getN() {
         return this.n_;
      }

      public Builder setN(ByteString value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.n_ = value;
            this.bitField0_ |= 4;
            this.onChanged();
            return this;
         }
      }

      public Builder clearN() {
         this.bitField0_ &= -5;
         this.n_ = RsaSsaPkcs1PublicKey.getDefaultInstance().getN();
         this.onChanged();
         return this;
      }

      public ByteString getE() {
         return this.e_;
      }

      public Builder setE(ByteString value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.e_ = value;
            this.bitField0_ |= 8;
            this.onChanged();
            return this;
         }
      }

      public Builder clearE() {
         this.bitField0_ &= -9;
         this.e_ = RsaSsaPkcs1PublicKey.getDefaultInstance().getE();
         this.onChanged();
         return this;
      }
   }
}
