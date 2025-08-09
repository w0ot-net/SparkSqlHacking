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

public final class JwtRsaSsaPssPrivateKey extends GeneratedMessage implements JwtRsaSsaPssPrivateKeyOrBuilder {
   private static final long serialVersionUID = 0L;
   private int bitField0_;
   public static final int VERSION_FIELD_NUMBER = 1;
   private int version_;
   public static final int PUBLIC_KEY_FIELD_NUMBER = 2;
   private JwtRsaSsaPssPublicKey publicKey_;
   public static final int D_FIELD_NUMBER = 3;
   private ByteString d_;
   public static final int P_FIELD_NUMBER = 4;
   private ByteString p_;
   public static final int Q_FIELD_NUMBER = 5;
   private ByteString q_;
   public static final int DP_FIELD_NUMBER = 6;
   private ByteString dp_;
   public static final int DQ_FIELD_NUMBER = 7;
   private ByteString dq_;
   public static final int CRT_FIELD_NUMBER = 8;
   private ByteString crt_;
   private byte memoizedIsInitialized;
   private static final JwtRsaSsaPssPrivateKey DEFAULT_INSTANCE;
   private static final Parser PARSER;

   private JwtRsaSsaPssPrivateKey(GeneratedMessage.Builder builder) {
      super(builder);
      this.version_ = 0;
      this.d_ = ByteString.EMPTY;
      this.p_ = ByteString.EMPTY;
      this.q_ = ByteString.EMPTY;
      this.dp_ = ByteString.EMPTY;
      this.dq_ = ByteString.EMPTY;
      this.crt_ = ByteString.EMPTY;
      this.memoizedIsInitialized = -1;
   }

   private JwtRsaSsaPssPrivateKey() {
      this.version_ = 0;
      this.d_ = ByteString.EMPTY;
      this.p_ = ByteString.EMPTY;
      this.q_ = ByteString.EMPTY;
      this.dp_ = ByteString.EMPTY;
      this.dq_ = ByteString.EMPTY;
      this.crt_ = ByteString.EMPTY;
      this.memoizedIsInitialized = -1;
      this.d_ = ByteString.EMPTY;
      this.p_ = ByteString.EMPTY;
      this.q_ = ByteString.EMPTY;
      this.dp_ = ByteString.EMPTY;
      this.dq_ = ByteString.EMPTY;
      this.crt_ = ByteString.EMPTY;
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return JwtRsaSsaPss.internal_static_google_crypto_tink_JwtRsaSsaPssPrivateKey_descriptor;
   }

   protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
      return JwtRsaSsaPss.internal_static_google_crypto_tink_JwtRsaSsaPssPrivateKey_fieldAccessorTable.ensureFieldAccessorsInitialized(JwtRsaSsaPssPrivateKey.class, Builder.class);
   }

   public int getVersion() {
      return this.version_;
   }

   public boolean hasPublicKey() {
      return (this.bitField0_ & 1) != 0;
   }

   public JwtRsaSsaPssPublicKey getPublicKey() {
      return this.publicKey_ == null ? JwtRsaSsaPssPublicKey.getDefaultInstance() : this.publicKey_;
   }

   public JwtRsaSsaPssPublicKeyOrBuilder getPublicKeyOrBuilder() {
      return this.publicKey_ == null ? JwtRsaSsaPssPublicKey.getDefaultInstance() : this.publicKey_;
   }

   public ByteString getD() {
      return this.d_;
   }

   public ByteString getP() {
      return this.p_;
   }

   public ByteString getQ() {
      return this.q_;
   }

   public ByteString getDp() {
      return this.dp_;
   }

   public ByteString getDq() {
      return this.dq_;
   }

   public ByteString getCrt() {
      return this.crt_;
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
         output.writeMessage(2, this.getPublicKey());
      }

      if (!this.d_.isEmpty()) {
         output.writeBytes(3, this.d_);
      }

      if (!this.p_.isEmpty()) {
         output.writeBytes(4, this.p_);
      }

      if (!this.q_.isEmpty()) {
         output.writeBytes(5, this.q_);
      }

      if (!this.dp_.isEmpty()) {
         output.writeBytes(6, this.dp_);
      }

      if (!this.dq_.isEmpty()) {
         output.writeBytes(7, this.dq_);
      }

      if (!this.crt_.isEmpty()) {
         output.writeBytes(8, this.crt_);
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
            size += CodedOutputStream.computeMessageSize(2, this.getPublicKey());
         }

         if (!this.d_.isEmpty()) {
            size += CodedOutputStream.computeBytesSize(3, this.d_);
         }

         if (!this.p_.isEmpty()) {
            size += CodedOutputStream.computeBytesSize(4, this.p_);
         }

         if (!this.q_.isEmpty()) {
            size += CodedOutputStream.computeBytesSize(5, this.q_);
         }

         if (!this.dp_.isEmpty()) {
            size += CodedOutputStream.computeBytesSize(6, this.dp_);
         }

         if (!this.dq_.isEmpty()) {
            size += CodedOutputStream.computeBytesSize(7, this.dq_);
         }

         if (!this.crt_.isEmpty()) {
            size += CodedOutputStream.computeBytesSize(8, this.crt_);
         }

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof JwtRsaSsaPssPrivateKey)) {
         return super.equals(obj);
      } else {
         JwtRsaSsaPssPrivateKey other = (JwtRsaSsaPssPrivateKey)obj;
         if (this.getVersion() != other.getVersion()) {
            return false;
         } else if (this.hasPublicKey() != other.hasPublicKey()) {
            return false;
         } else if (this.hasPublicKey() && !this.getPublicKey().equals(other.getPublicKey())) {
            return false;
         } else if (!this.getD().equals(other.getD())) {
            return false;
         } else if (!this.getP().equals(other.getP())) {
            return false;
         } else if (!this.getQ().equals(other.getQ())) {
            return false;
         } else if (!this.getDp().equals(other.getDp())) {
            return false;
         } else if (!this.getDq().equals(other.getDq())) {
            return false;
         } else if (!this.getCrt().equals(other.getCrt())) {
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
         if (this.hasPublicKey()) {
            hash = 37 * hash + 2;
            hash = 53 * hash + this.getPublicKey().hashCode();
         }

         hash = 37 * hash + 3;
         hash = 53 * hash + this.getD().hashCode();
         hash = 37 * hash + 4;
         hash = 53 * hash + this.getP().hashCode();
         hash = 37 * hash + 5;
         hash = 53 * hash + this.getQ().hashCode();
         hash = 37 * hash + 6;
         hash = 53 * hash + this.getDp().hashCode();
         hash = 37 * hash + 7;
         hash = 53 * hash + this.getDq().hashCode();
         hash = 37 * hash + 8;
         hash = 53 * hash + this.getCrt().hashCode();
         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static JwtRsaSsaPssPrivateKey parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (JwtRsaSsaPssPrivateKey)PARSER.parseFrom(data);
   }

   public static JwtRsaSsaPssPrivateKey parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (JwtRsaSsaPssPrivateKey)PARSER.parseFrom(data, extensionRegistry);
   }

   public static JwtRsaSsaPssPrivateKey parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (JwtRsaSsaPssPrivateKey)PARSER.parseFrom(data);
   }

   public static JwtRsaSsaPssPrivateKey parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (JwtRsaSsaPssPrivateKey)PARSER.parseFrom(data, extensionRegistry);
   }

   public static JwtRsaSsaPssPrivateKey parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (JwtRsaSsaPssPrivateKey)PARSER.parseFrom(data);
   }

   public static JwtRsaSsaPssPrivateKey parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (JwtRsaSsaPssPrivateKey)PARSER.parseFrom(data, extensionRegistry);
   }

   public static JwtRsaSsaPssPrivateKey parseFrom(InputStream input) throws IOException {
      return (JwtRsaSsaPssPrivateKey)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static JwtRsaSsaPssPrivateKey parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (JwtRsaSsaPssPrivateKey)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static JwtRsaSsaPssPrivateKey parseDelimitedFrom(InputStream input) throws IOException {
      return (JwtRsaSsaPssPrivateKey)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
   }

   public static JwtRsaSsaPssPrivateKey parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (JwtRsaSsaPssPrivateKey)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static JwtRsaSsaPssPrivateKey parseFrom(CodedInputStream input) throws IOException {
      return (JwtRsaSsaPssPrivateKey)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static JwtRsaSsaPssPrivateKey parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (JwtRsaSsaPssPrivateKey)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(JwtRsaSsaPssPrivateKey prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static JwtRsaSsaPssPrivateKey getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public JwtRsaSsaPssPrivateKey getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", JwtRsaSsaPssPrivateKey.class.getName());
      DEFAULT_INSTANCE = new JwtRsaSsaPssPrivateKey();
      PARSER = new AbstractParser() {
         public JwtRsaSsaPssPrivateKey parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            Builder builder = JwtRsaSsaPssPrivateKey.newBuilder();

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

   public static final class Builder extends GeneratedMessage.Builder implements JwtRsaSsaPssPrivateKeyOrBuilder {
      private int bitField0_;
      private int version_;
      private JwtRsaSsaPssPublicKey publicKey_;
      private SingleFieldBuilder publicKeyBuilder_;
      private ByteString d_;
      private ByteString p_;
      private ByteString q_;
      private ByteString dp_;
      private ByteString dq_;
      private ByteString crt_;

      public static final Descriptors.Descriptor getDescriptor() {
         return JwtRsaSsaPss.internal_static_google_crypto_tink_JwtRsaSsaPssPrivateKey_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return JwtRsaSsaPss.internal_static_google_crypto_tink_JwtRsaSsaPssPrivateKey_fieldAccessorTable.ensureFieldAccessorsInitialized(JwtRsaSsaPssPrivateKey.class, Builder.class);
      }

      private Builder() {
         this.d_ = ByteString.EMPTY;
         this.p_ = ByteString.EMPTY;
         this.q_ = ByteString.EMPTY;
         this.dp_ = ByteString.EMPTY;
         this.dq_ = ByteString.EMPTY;
         this.crt_ = ByteString.EMPTY;
         this.maybeForceBuilderInitialization();
      }

      private Builder(AbstractMessage.BuilderParent parent) {
         super(parent);
         this.d_ = ByteString.EMPTY;
         this.p_ = ByteString.EMPTY;
         this.q_ = ByteString.EMPTY;
         this.dp_ = ByteString.EMPTY;
         this.dq_ = ByteString.EMPTY;
         this.crt_ = ByteString.EMPTY;
         this.maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization() {
         if (JwtRsaSsaPssPrivateKey.alwaysUseFieldBuilders) {
            this.getPublicKeyFieldBuilder();
         }

      }

      public Builder clear() {
         super.clear();
         this.bitField0_ = 0;
         this.version_ = 0;
         this.publicKey_ = null;
         if (this.publicKeyBuilder_ != null) {
            this.publicKeyBuilder_.dispose();
            this.publicKeyBuilder_ = null;
         }

         this.d_ = ByteString.EMPTY;
         this.p_ = ByteString.EMPTY;
         this.q_ = ByteString.EMPTY;
         this.dp_ = ByteString.EMPTY;
         this.dq_ = ByteString.EMPTY;
         this.crt_ = ByteString.EMPTY;
         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return JwtRsaSsaPss.internal_static_google_crypto_tink_JwtRsaSsaPssPrivateKey_descriptor;
      }

      public JwtRsaSsaPssPrivateKey getDefaultInstanceForType() {
         return JwtRsaSsaPssPrivateKey.getDefaultInstance();
      }

      public JwtRsaSsaPssPrivateKey build() {
         JwtRsaSsaPssPrivateKey result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public JwtRsaSsaPssPrivateKey buildPartial() {
         JwtRsaSsaPssPrivateKey result = new JwtRsaSsaPssPrivateKey(this);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartial0(JwtRsaSsaPssPrivateKey result) {
         int from_bitField0_ = this.bitField0_;
         if ((from_bitField0_ & 1) != 0) {
            result.version_ = this.version_;
         }

         int to_bitField0_ = 0;
         if ((from_bitField0_ & 2) != 0) {
            result.publicKey_ = this.publicKeyBuilder_ == null ? this.publicKey_ : (JwtRsaSsaPssPublicKey)this.publicKeyBuilder_.build();
            to_bitField0_ |= 1;
         }

         if ((from_bitField0_ & 4) != 0) {
            result.d_ = this.d_;
         }

         if ((from_bitField0_ & 8) != 0) {
            result.p_ = this.p_;
         }

         if ((from_bitField0_ & 16) != 0) {
            result.q_ = this.q_;
         }

         if ((from_bitField0_ & 32) != 0) {
            result.dp_ = this.dp_;
         }

         if ((from_bitField0_ & 64) != 0) {
            result.dq_ = this.dq_;
         }

         if ((from_bitField0_ & 128) != 0) {
            result.crt_ = this.crt_;
         }

         result.bitField0_ = to_bitField0_;
      }

      public Builder mergeFrom(Message other) {
         if (other instanceof JwtRsaSsaPssPrivateKey) {
            return this.mergeFrom((JwtRsaSsaPssPrivateKey)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(JwtRsaSsaPssPrivateKey other) {
         if (other == JwtRsaSsaPssPrivateKey.getDefaultInstance()) {
            return this;
         } else {
            if (other.getVersion() != 0) {
               this.setVersion(other.getVersion());
            }

            if (other.hasPublicKey()) {
               this.mergePublicKey(other.getPublicKey());
            }

            if (other.getD() != ByteString.EMPTY) {
               this.setD(other.getD());
            }

            if (other.getP() != ByteString.EMPTY) {
               this.setP(other.getP());
            }

            if (other.getQ() != ByteString.EMPTY) {
               this.setQ(other.getQ());
            }

            if (other.getDp() != ByteString.EMPTY) {
               this.setDp(other.getDp());
            }

            if (other.getDq() != ByteString.EMPTY) {
               this.setDq(other.getDq());
            }

            if (other.getCrt() != ByteString.EMPTY) {
               this.setCrt(other.getCrt());
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
                        input.readMessage(this.getPublicKeyFieldBuilder().getBuilder(), extensionRegistry);
                        this.bitField0_ |= 2;
                        break;
                     case 26:
                        this.d_ = input.readBytes();
                        this.bitField0_ |= 4;
                        break;
                     case 34:
                        this.p_ = input.readBytes();
                        this.bitField0_ |= 8;
                        break;
                     case 42:
                        this.q_ = input.readBytes();
                        this.bitField0_ |= 16;
                        break;
                     case 50:
                        this.dp_ = input.readBytes();
                        this.bitField0_ |= 32;
                        break;
                     case 58:
                        this.dq_ = input.readBytes();
                        this.bitField0_ |= 64;
                        break;
                     case 66:
                        this.crt_ = input.readBytes();
                        this.bitField0_ |= 128;
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

      public boolean hasPublicKey() {
         return (this.bitField0_ & 2) != 0;
      }

      public JwtRsaSsaPssPublicKey getPublicKey() {
         if (this.publicKeyBuilder_ == null) {
            return this.publicKey_ == null ? JwtRsaSsaPssPublicKey.getDefaultInstance() : this.publicKey_;
         } else {
            return (JwtRsaSsaPssPublicKey)this.publicKeyBuilder_.getMessage();
         }
      }

      public Builder setPublicKey(JwtRsaSsaPssPublicKey value) {
         if (this.publicKeyBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.publicKey_ = value;
         } else {
            this.publicKeyBuilder_.setMessage(value);
         }

         this.bitField0_ |= 2;
         this.onChanged();
         return this;
      }

      public Builder setPublicKey(JwtRsaSsaPssPublicKey.Builder builderForValue) {
         if (this.publicKeyBuilder_ == null) {
            this.publicKey_ = builderForValue.build();
         } else {
            this.publicKeyBuilder_.setMessage(builderForValue.build());
         }

         this.bitField0_ |= 2;
         this.onChanged();
         return this;
      }

      public Builder mergePublicKey(JwtRsaSsaPssPublicKey value) {
         if (this.publicKeyBuilder_ == null) {
            if ((this.bitField0_ & 2) != 0 && this.publicKey_ != null && this.publicKey_ != JwtRsaSsaPssPublicKey.getDefaultInstance()) {
               this.getPublicKeyBuilder().mergeFrom(value);
            } else {
               this.publicKey_ = value;
            }
         } else {
            this.publicKeyBuilder_.mergeFrom(value);
         }

         if (this.publicKey_ != null) {
            this.bitField0_ |= 2;
            this.onChanged();
         }

         return this;
      }

      public Builder clearPublicKey() {
         this.bitField0_ &= -3;
         this.publicKey_ = null;
         if (this.publicKeyBuilder_ != null) {
            this.publicKeyBuilder_.dispose();
            this.publicKeyBuilder_ = null;
         }

         this.onChanged();
         return this;
      }

      public JwtRsaSsaPssPublicKey.Builder getPublicKeyBuilder() {
         this.bitField0_ |= 2;
         this.onChanged();
         return (JwtRsaSsaPssPublicKey.Builder)this.getPublicKeyFieldBuilder().getBuilder();
      }

      public JwtRsaSsaPssPublicKeyOrBuilder getPublicKeyOrBuilder() {
         if (this.publicKeyBuilder_ != null) {
            return (JwtRsaSsaPssPublicKeyOrBuilder)this.publicKeyBuilder_.getMessageOrBuilder();
         } else {
            return this.publicKey_ == null ? JwtRsaSsaPssPublicKey.getDefaultInstance() : this.publicKey_;
         }
      }

      private SingleFieldBuilder getPublicKeyFieldBuilder() {
         if (this.publicKeyBuilder_ == null) {
            this.publicKeyBuilder_ = new SingleFieldBuilder(this.getPublicKey(), this.getParentForChildren(), this.isClean());
            this.publicKey_ = null;
         }

         return this.publicKeyBuilder_;
      }

      public ByteString getD() {
         return this.d_;
      }

      public Builder setD(ByteString value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.d_ = value;
            this.bitField0_ |= 4;
            this.onChanged();
            return this;
         }
      }

      public Builder clearD() {
         this.bitField0_ &= -5;
         this.d_ = JwtRsaSsaPssPrivateKey.getDefaultInstance().getD();
         this.onChanged();
         return this;
      }

      public ByteString getP() {
         return this.p_;
      }

      public Builder setP(ByteString value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.p_ = value;
            this.bitField0_ |= 8;
            this.onChanged();
            return this;
         }
      }

      public Builder clearP() {
         this.bitField0_ &= -9;
         this.p_ = JwtRsaSsaPssPrivateKey.getDefaultInstance().getP();
         this.onChanged();
         return this;
      }

      public ByteString getQ() {
         return this.q_;
      }

      public Builder setQ(ByteString value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.q_ = value;
            this.bitField0_ |= 16;
            this.onChanged();
            return this;
         }
      }

      public Builder clearQ() {
         this.bitField0_ &= -17;
         this.q_ = JwtRsaSsaPssPrivateKey.getDefaultInstance().getQ();
         this.onChanged();
         return this;
      }

      public ByteString getDp() {
         return this.dp_;
      }

      public Builder setDp(ByteString value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.dp_ = value;
            this.bitField0_ |= 32;
            this.onChanged();
            return this;
         }
      }

      public Builder clearDp() {
         this.bitField0_ &= -33;
         this.dp_ = JwtRsaSsaPssPrivateKey.getDefaultInstance().getDp();
         this.onChanged();
         return this;
      }

      public ByteString getDq() {
         return this.dq_;
      }

      public Builder setDq(ByteString value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.dq_ = value;
            this.bitField0_ |= 64;
            this.onChanged();
            return this;
         }
      }

      public Builder clearDq() {
         this.bitField0_ &= -65;
         this.dq_ = JwtRsaSsaPssPrivateKey.getDefaultInstance().getDq();
         this.onChanged();
         return this;
      }

      public ByteString getCrt() {
         return this.crt_;
      }

      public Builder setCrt(ByteString value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.crt_ = value;
            this.bitField0_ |= 128;
            this.onChanged();
            return this;
         }
      }

      public Builder clearCrt() {
         this.bitField0_ &= -129;
         this.crt_ = JwtRsaSsaPssPrivateKey.getDefaultInstance().getCrt();
         this.onChanged();
         return this;
      }
   }
}
