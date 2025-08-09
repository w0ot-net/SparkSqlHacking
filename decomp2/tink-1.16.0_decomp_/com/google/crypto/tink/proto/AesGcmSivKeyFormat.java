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

public final class AesGcmSivKeyFormat extends GeneratedMessage implements AesGcmSivKeyFormatOrBuilder {
   private static final long serialVersionUID = 0L;
   public static final int KEY_SIZE_FIELD_NUMBER = 2;
   private int keySize_;
   public static final int VERSION_FIELD_NUMBER = 1;
   private int version_;
   private byte memoizedIsInitialized;
   private static final AesGcmSivKeyFormat DEFAULT_INSTANCE;
   private static final Parser PARSER;

   private AesGcmSivKeyFormat(GeneratedMessage.Builder builder) {
      super(builder);
      this.keySize_ = 0;
      this.version_ = 0;
      this.memoizedIsInitialized = -1;
   }

   private AesGcmSivKeyFormat() {
      this.keySize_ = 0;
      this.version_ = 0;
      this.memoizedIsInitialized = -1;
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return AesGcmSiv.internal_static_google_crypto_tink_AesGcmSivKeyFormat_descriptor;
   }

   protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
      return AesGcmSiv.internal_static_google_crypto_tink_AesGcmSivKeyFormat_fieldAccessorTable.ensureFieldAccessorsInitialized(AesGcmSivKeyFormat.class, Builder.class);
   }

   public int getKeySize() {
      return this.keySize_;
   }

   public int getVersion() {
      return this.version_;
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

      if (this.keySize_ != 0) {
         output.writeUInt32(2, this.keySize_);
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

         if (this.keySize_ != 0) {
            size += CodedOutputStream.computeUInt32Size(2, this.keySize_);
         }

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof AesGcmSivKeyFormat)) {
         return super.equals(obj);
      } else {
         AesGcmSivKeyFormat other = (AesGcmSivKeyFormat)obj;
         if (this.getKeySize() != other.getKeySize()) {
            return false;
         } else if (this.getVersion() != other.getVersion()) {
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
         hash = 53 * hash + this.getKeySize();
         hash = 37 * hash + 1;
         hash = 53 * hash + this.getVersion();
         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static AesGcmSivKeyFormat parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (AesGcmSivKeyFormat)PARSER.parseFrom(data);
   }

   public static AesGcmSivKeyFormat parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (AesGcmSivKeyFormat)PARSER.parseFrom(data, extensionRegistry);
   }

   public static AesGcmSivKeyFormat parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (AesGcmSivKeyFormat)PARSER.parseFrom(data);
   }

   public static AesGcmSivKeyFormat parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (AesGcmSivKeyFormat)PARSER.parseFrom(data, extensionRegistry);
   }

   public static AesGcmSivKeyFormat parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (AesGcmSivKeyFormat)PARSER.parseFrom(data);
   }

   public static AesGcmSivKeyFormat parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (AesGcmSivKeyFormat)PARSER.parseFrom(data, extensionRegistry);
   }

   public static AesGcmSivKeyFormat parseFrom(InputStream input) throws IOException {
      return (AesGcmSivKeyFormat)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static AesGcmSivKeyFormat parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (AesGcmSivKeyFormat)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static AesGcmSivKeyFormat parseDelimitedFrom(InputStream input) throws IOException {
      return (AesGcmSivKeyFormat)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
   }

   public static AesGcmSivKeyFormat parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (AesGcmSivKeyFormat)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static AesGcmSivKeyFormat parseFrom(CodedInputStream input) throws IOException {
      return (AesGcmSivKeyFormat)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static AesGcmSivKeyFormat parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (AesGcmSivKeyFormat)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(AesGcmSivKeyFormat prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static AesGcmSivKeyFormat getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public AesGcmSivKeyFormat getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", AesGcmSivKeyFormat.class.getName());
      DEFAULT_INSTANCE = new AesGcmSivKeyFormat();
      PARSER = new AbstractParser() {
         public AesGcmSivKeyFormat parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            Builder builder = AesGcmSivKeyFormat.newBuilder();

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

   public static final class Builder extends GeneratedMessage.Builder implements AesGcmSivKeyFormatOrBuilder {
      private int bitField0_;
      private int keySize_;
      private int version_;

      public static final Descriptors.Descriptor getDescriptor() {
         return AesGcmSiv.internal_static_google_crypto_tink_AesGcmSivKeyFormat_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return AesGcmSiv.internal_static_google_crypto_tink_AesGcmSivKeyFormat_fieldAccessorTable.ensureFieldAccessorsInitialized(AesGcmSivKeyFormat.class, Builder.class);
      }

      private Builder() {
      }

      private Builder(AbstractMessage.BuilderParent parent) {
         super(parent);
      }

      public Builder clear() {
         super.clear();
         this.bitField0_ = 0;
         this.keySize_ = 0;
         this.version_ = 0;
         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return AesGcmSiv.internal_static_google_crypto_tink_AesGcmSivKeyFormat_descriptor;
      }

      public AesGcmSivKeyFormat getDefaultInstanceForType() {
         return AesGcmSivKeyFormat.getDefaultInstance();
      }

      public AesGcmSivKeyFormat build() {
         AesGcmSivKeyFormat result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public AesGcmSivKeyFormat buildPartial() {
         AesGcmSivKeyFormat result = new AesGcmSivKeyFormat(this);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartial0(AesGcmSivKeyFormat result) {
         int from_bitField0_ = this.bitField0_;
         if ((from_bitField0_ & 1) != 0) {
            result.keySize_ = this.keySize_;
         }

         if ((from_bitField0_ & 2) != 0) {
            result.version_ = this.version_;
         }

      }

      public Builder mergeFrom(Message other) {
         if (other instanceof AesGcmSivKeyFormat) {
            return this.mergeFrom((AesGcmSivKeyFormat)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(AesGcmSivKeyFormat other) {
         if (other == AesGcmSivKeyFormat.getDefaultInstance()) {
            return this;
         } else {
            if (other.getKeySize() != 0) {
               this.setKeySize(other.getKeySize());
            }

            if (other.getVersion() != 0) {
               this.setVersion(other.getVersion());
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
                        this.bitField0_ |= 2;
                        break;
                     case 16:
                        this.keySize_ = input.readUInt32();
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

      public int getKeySize() {
         return this.keySize_;
      }

      public Builder setKeySize(int value) {
         this.keySize_ = value;
         this.bitField0_ |= 1;
         this.onChanged();
         return this;
      }

      public Builder clearKeySize() {
         this.bitField0_ &= -2;
         this.keySize_ = 0;
         this.onChanged();
         return this;
      }

      public int getVersion() {
         return this.version_;
      }

      public Builder setVersion(int value) {
         this.version_ = value;
         this.bitField0_ |= 2;
         this.onChanged();
         return this;
      }

      public Builder clearVersion() {
         this.bitField0_ &= -3;
         this.version_ = 0;
         this.onChanged();
         return this;
      }
   }
}
