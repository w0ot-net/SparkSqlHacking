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

public final class EncryptedKeyset extends GeneratedMessage implements EncryptedKeysetOrBuilder {
   private static final long serialVersionUID = 0L;
   private int bitField0_;
   public static final int ENCRYPTED_KEYSET_FIELD_NUMBER = 2;
   private ByteString encryptedKeyset_;
   public static final int KEYSET_INFO_FIELD_NUMBER = 3;
   private KeysetInfo keysetInfo_;
   private byte memoizedIsInitialized;
   private static final EncryptedKeyset DEFAULT_INSTANCE;
   private static final Parser PARSER;

   private EncryptedKeyset(GeneratedMessage.Builder builder) {
      super(builder);
      this.encryptedKeyset_ = ByteString.EMPTY;
      this.memoizedIsInitialized = -1;
   }

   private EncryptedKeyset() {
      this.encryptedKeyset_ = ByteString.EMPTY;
      this.memoizedIsInitialized = -1;
      this.encryptedKeyset_ = ByteString.EMPTY;
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return Tink.internal_static_google_crypto_tink_EncryptedKeyset_descriptor;
   }

   protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
      return Tink.internal_static_google_crypto_tink_EncryptedKeyset_fieldAccessorTable.ensureFieldAccessorsInitialized(EncryptedKeyset.class, Builder.class);
   }

   public ByteString getEncryptedKeyset() {
      return this.encryptedKeyset_;
   }

   public boolean hasKeysetInfo() {
      return (this.bitField0_ & 1) != 0;
   }

   public KeysetInfo getKeysetInfo() {
      return this.keysetInfo_ == null ? KeysetInfo.getDefaultInstance() : this.keysetInfo_;
   }

   public KeysetInfoOrBuilder getKeysetInfoOrBuilder() {
      return this.keysetInfo_ == null ? KeysetInfo.getDefaultInstance() : this.keysetInfo_;
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
      if (!this.encryptedKeyset_.isEmpty()) {
         output.writeBytes(2, this.encryptedKeyset_);
      }

      if ((this.bitField0_ & 1) != 0) {
         output.writeMessage(3, this.getKeysetInfo());
      }

      this.getUnknownFields().writeTo(output);
   }

   public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1) {
         return size;
      } else {
         size = 0;
         if (!this.encryptedKeyset_.isEmpty()) {
            size += CodedOutputStream.computeBytesSize(2, this.encryptedKeyset_);
         }

         if ((this.bitField0_ & 1) != 0) {
            size += CodedOutputStream.computeMessageSize(3, this.getKeysetInfo());
         }

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof EncryptedKeyset)) {
         return super.equals(obj);
      } else {
         EncryptedKeyset other = (EncryptedKeyset)obj;
         if (!this.getEncryptedKeyset().equals(other.getEncryptedKeyset())) {
            return false;
         } else if (this.hasKeysetInfo() != other.hasKeysetInfo()) {
            return false;
         } else if (this.hasKeysetInfo() && !this.getKeysetInfo().equals(other.getKeysetInfo())) {
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
         hash = 37 * hash + 2;
         hash = 53 * hash + this.getEncryptedKeyset().hashCode();
         if (this.hasKeysetInfo()) {
            hash = 37 * hash + 3;
            hash = 53 * hash + this.getKeysetInfo().hashCode();
         }

         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static EncryptedKeyset parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (EncryptedKeyset)PARSER.parseFrom(data);
   }

   public static EncryptedKeyset parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (EncryptedKeyset)PARSER.parseFrom(data, extensionRegistry);
   }

   public static EncryptedKeyset parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (EncryptedKeyset)PARSER.parseFrom(data);
   }

   public static EncryptedKeyset parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (EncryptedKeyset)PARSER.parseFrom(data, extensionRegistry);
   }

   public static EncryptedKeyset parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (EncryptedKeyset)PARSER.parseFrom(data);
   }

   public static EncryptedKeyset parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (EncryptedKeyset)PARSER.parseFrom(data, extensionRegistry);
   }

   public static EncryptedKeyset parseFrom(InputStream input) throws IOException {
      return (EncryptedKeyset)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static EncryptedKeyset parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (EncryptedKeyset)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static EncryptedKeyset parseDelimitedFrom(InputStream input) throws IOException {
      return (EncryptedKeyset)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
   }

   public static EncryptedKeyset parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (EncryptedKeyset)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static EncryptedKeyset parseFrom(CodedInputStream input) throws IOException {
      return (EncryptedKeyset)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static EncryptedKeyset parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (EncryptedKeyset)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(EncryptedKeyset prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static EncryptedKeyset getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public EncryptedKeyset getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", EncryptedKeyset.class.getName());
      DEFAULT_INSTANCE = new EncryptedKeyset();
      PARSER = new AbstractParser() {
         public EncryptedKeyset parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            Builder builder = EncryptedKeyset.newBuilder();

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

   public static final class Builder extends GeneratedMessage.Builder implements EncryptedKeysetOrBuilder {
      private int bitField0_;
      private ByteString encryptedKeyset_;
      private KeysetInfo keysetInfo_;
      private SingleFieldBuilder keysetInfoBuilder_;

      public static final Descriptors.Descriptor getDescriptor() {
         return Tink.internal_static_google_crypto_tink_EncryptedKeyset_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return Tink.internal_static_google_crypto_tink_EncryptedKeyset_fieldAccessorTable.ensureFieldAccessorsInitialized(EncryptedKeyset.class, Builder.class);
      }

      private Builder() {
         this.encryptedKeyset_ = ByteString.EMPTY;
         this.maybeForceBuilderInitialization();
      }

      private Builder(AbstractMessage.BuilderParent parent) {
         super(parent);
         this.encryptedKeyset_ = ByteString.EMPTY;
         this.maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization() {
         if (EncryptedKeyset.alwaysUseFieldBuilders) {
            this.getKeysetInfoFieldBuilder();
         }

      }

      public Builder clear() {
         super.clear();
         this.bitField0_ = 0;
         this.encryptedKeyset_ = ByteString.EMPTY;
         this.keysetInfo_ = null;
         if (this.keysetInfoBuilder_ != null) {
            this.keysetInfoBuilder_.dispose();
            this.keysetInfoBuilder_ = null;
         }

         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return Tink.internal_static_google_crypto_tink_EncryptedKeyset_descriptor;
      }

      public EncryptedKeyset getDefaultInstanceForType() {
         return EncryptedKeyset.getDefaultInstance();
      }

      public EncryptedKeyset build() {
         EncryptedKeyset result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public EncryptedKeyset buildPartial() {
         EncryptedKeyset result = new EncryptedKeyset(this);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartial0(EncryptedKeyset result) {
         int from_bitField0_ = this.bitField0_;
         if ((from_bitField0_ & 1) != 0) {
            result.encryptedKeyset_ = this.encryptedKeyset_;
         }

         int to_bitField0_ = 0;
         if ((from_bitField0_ & 2) != 0) {
            result.keysetInfo_ = this.keysetInfoBuilder_ == null ? this.keysetInfo_ : (KeysetInfo)this.keysetInfoBuilder_.build();
            to_bitField0_ |= 1;
         }

         result.bitField0_ = to_bitField0_;
      }

      public Builder mergeFrom(Message other) {
         if (other instanceof EncryptedKeyset) {
            return this.mergeFrom((EncryptedKeyset)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(EncryptedKeyset other) {
         if (other == EncryptedKeyset.getDefaultInstance()) {
            return this;
         } else {
            if (other.getEncryptedKeyset() != ByteString.EMPTY) {
               this.setEncryptedKeyset(other.getEncryptedKeyset());
            }

            if (other.hasKeysetInfo()) {
               this.mergeKeysetInfo(other.getKeysetInfo());
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
                        this.encryptedKeyset_ = input.readBytes();
                        this.bitField0_ |= 1;
                        break;
                     case 26:
                        input.readMessage(this.getKeysetInfoFieldBuilder().getBuilder(), extensionRegistry);
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

      public ByteString getEncryptedKeyset() {
         return this.encryptedKeyset_;
      }

      public Builder setEncryptedKeyset(ByteString value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.encryptedKeyset_ = value;
            this.bitField0_ |= 1;
            this.onChanged();
            return this;
         }
      }

      public Builder clearEncryptedKeyset() {
         this.bitField0_ &= -2;
         this.encryptedKeyset_ = EncryptedKeyset.getDefaultInstance().getEncryptedKeyset();
         this.onChanged();
         return this;
      }

      public boolean hasKeysetInfo() {
         return (this.bitField0_ & 2) != 0;
      }

      public KeysetInfo getKeysetInfo() {
         if (this.keysetInfoBuilder_ == null) {
            return this.keysetInfo_ == null ? KeysetInfo.getDefaultInstance() : this.keysetInfo_;
         } else {
            return (KeysetInfo)this.keysetInfoBuilder_.getMessage();
         }
      }

      public Builder setKeysetInfo(KeysetInfo value) {
         if (this.keysetInfoBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.keysetInfo_ = value;
         } else {
            this.keysetInfoBuilder_.setMessage(value);
         }

         this.bitField0_ |= 2;
         this.onChanged();
         return this;
      }

      public Builder setKeysetInfo(KeysetInfo.Builder builderForValue) {
         if (this.keysetInfoBuilder_ == null) {
            this.keysetInfo_ = builderForValue.build();
         } else {
            this.keysetInfoBuilder_.setMessage(builderForValue.build());
         }

         this.bitField0_ |= 2;
         this.onChanged();
         return this;
      }

      public Builder mergeKeysetInfo(KeysetInfo value) {
         if (this.keysetInfoBuilder_ == null) {
            if ((this.bitField0_ & 2) != 0 && this.keysetInfo_ != null && this.keysetInfo_ != KeysetInfo.getDefaultInstance()) {
               this.getKeysetInfoBuilder().mergeFrom(value);
            } else {
               this.keysetInfo_ = value;
            }
         } else {
            this.keysetInfoBuilder_.mergeFrom(value);
         }

         if (this.keysetInfo_ != null) {
            this.bitField0_ |= 2;
            this.onChanged();
         }

         return this;
      }

      public Builder clearKeysetInfo() {
         this.bitField0_ &= -3;
         this.keysetInfo_ = null;
         if (this.keysetInfoBuilder_ != null) {
            this.keysetInfoBuilder_.dispose();
            this.keysetInfoBuilder_ = null;
         }

         this.onChanged();
         return this;
      }

      public KeysetInfo.Builder getKeysetInfoBuilder() {
         this.bitField0_ |= 2;
         this.onChanged();
         return (KeysetInfo.Builder)this.getKeysetInfoFieldBuilder().getBuilder();
      }

      public KeysetInfoOrBuilder getKeysetInfoOrBuilder() {
         if (this.keysetInfoBuilder_ != null) {
            return (KeysetInfoOrBuilder)this.keysetInfoBuilder_.getMessageOrBuilder();
         } else {
            return this.keysetInfo_ == null ? KeysetInfo.getDefaultInstance() : this.keysetInfo_;
         }
      }

      private SingleFieldBuilder getKeysetInfoFieldBuilder() {
         if (this.keysetInfoBuilder_ == null) {
            this.keysetInfoBuilder_ = new SingleFieldBuilder(this.getKeysetInfo(), this.getParentForChildren(), this.isClean());
            this.keysetInfo_ = null;
         }

         return this.keysetInfoBuilder_;
      }
   }
}
