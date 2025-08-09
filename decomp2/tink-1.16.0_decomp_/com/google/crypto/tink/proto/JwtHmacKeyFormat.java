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

public final class JwtHmacKeyFormat extends GeneratedMessage implements JwtHmacKeyFormatOrBuilder {
   private static final long serialVersionUID = 0L;
   public static final int VERSION_FIELD_NUMBER = 1;
   private int version_;
   public static final int ALGORITHM_FIELD_NUMBER = 2;
   private int algorithm_;
   public static final int KEY_SIZE_FIELD_NUMBER = 3;
   private int keySize_;
   private byte memoizedIsInitialized;
   private static final JwtHmacKeyFormat DEFAULT_INSTANCE;
   private static final Parser PARSER;

   private JwtHmacKeyFormat(GeneratedMessage.Builder builder) {
      super(builder);
      this.version_ = 0;
      this.algorithm_ = 0;
      this.keySize_ = 0;
      this.memoizedIsInitialized = -1;
   }

   private JwtHmacKeyFormat() {
      this.version_ = 0;
      this.algorithm_ = 0;
      this.keySize_ = 0;
      this.memoizedIsInitialized = -1;
      this.algorithm_ = 0;
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return JwtHmac.internal_static_google_crypto_tink_JwtHmacKeyFormat_descriptor;
   }

   protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
      return JwtHmac.internal_static_google_crypto_tink_JwtHmacKeyFormat_fieldAccessorTable.ensureFieldAccessorsInitialized(JwtHmacKeyFormat.class, Builder.class);
   }

   public int getVersion() {
      return this.version_;
   }

   public int getAlgorithmValue() {
      return this.algorithm_;
   }

   public JwtHmacAlgorithm getAlgorithm() {
      JwtHmacAlgorithm result = JwtHmacAlgorithm.forNumber(this.algorithm_);
      return result == null ? JwtHmacAlgorithm.UNRECOGNIZED : result;
   }

   public int getKeySize() {
      return this.keySize_;
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

      if (this.algorithm_ != JwtHmacAlgorithm.HS_UNKNOWN.getNumber()) {
         output.writeEnum(2, this.algorithm_);
      }

      if (this.keySize_ != 0) {
         output.writeUInt32(3, this.keySize_);
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

         if (this.algorithm_ != JwtHmacAlgorithm.HS_UNKNOWN.getNumber()) {
            size += CodedOutputStream.computeEnumSize(2, this.algorithm_);
         }

         if (this.keySize_ != 0) {
            size += CodedOutputStream.computeUInt32Size(3, this.keySize_);
         }

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof JwtHmacKeyFormat)) {
         return super.equals(obj);
      } else {
         JwtHmacKeyFormat other = (JwtHmacKeyFormat)obj;
         if (this.getVersion() != other.getVersion()) {
            return false;
         } else if (this.algorithm_ != other.algorithm_) {
            return false;
         } else if (this.getKeySize() != other.getKeySize()) {
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
         hash = 37 * hash + 2;
         hash = 53 * hash + this.algorithm_;
         hash = 37 * hash + 3;
         hash = 53 * hash + this.getKeySize();
         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static JwtHmacKeyFormat parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (JwtHmacKeyFormat)PARSER.parseFrom(data);
   }

   public static JwtHmacKeyFormat parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (JwtHmacKeyFormat)PARSER.parseFrom(data, extensionRegistry);
   }

   public static JwtHmacKeyFormat parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (JwtHmacKeyFormat)PARSER.parseFrom(data);
   }

   public static JwtHmacKeyFormat parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (JwtHmacKeyFormat)PARSER.parseFrom(data, extensionRegistry);
   }

   public static JwtHmacKeyFormat parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (JwtHmacKeyFormat)PARSER.parseFrom(data);
   }

   public static JwtHmacKeyFormat parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (JwtHmacKeyFormat)PARSER.parseFrom(data, extensionRegistry);
   }

   public static JwtHmacKeyFormat parseFrom(InputStream input) throws IOException {
      return (JwtHmacKeyFormat)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static JwtHmacKeyFormat parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (JwtHmacKeyFormat)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static JwtHmacKeyFormat parseDelimitedFrom(InputStream input) throws IOException {
      return (JwtHmacKeyFormat)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
   }

   public static JwtHmacKeyFormat parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (JwtHmacKeyFormat)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static JwtHmacKeyFormat parseFrom(CodedInputStream input) throws IOException {
      return (JwtHmacKeyFormat)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static JwtHmacKeyFormat parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (JwtHmacKeyFormat)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(JwtHmacKeyFormat prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static JwtHmacKeyFormat getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public JwtHmacKeyFormat getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", JwtHmacKeyFormat.class.getName());
      DEFAULT_INSTANCE = new JwtHmacKeyFormat();
      PARSER = new AbstractParser() {
         public JwtHmacKeyFormat parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            Builder builder = JwtHmacKeyFormat.newBuilder();

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

   public static final class Builder extends GeneratedMessage.Builder implements JwtHmacKeyFormatOrBuilder {
      private int bitField0_;
      private int version_;
      private int algorithm_;
      private int keySize_;

      public static final Descriptors.Descriptor getDescriptor() {
         return JwtHmac.internal_static_google_crypto_tink_JwtHmacKeyFormat_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return JwtHmac.internal_static_google_crypto_tink_JwtHmacKeyFormat_fieldAccessorTable.ensureFieldAccessorsInitialized(JwtHmacKeyFormat.class, Builder.class);
      }

      private Builder() {
         this.algorithm_ = 0;
      }

      private Builder(AbstractMessage.BuilderParent parent) {
         super(parent);
         this.algorithm_ = 0;
      }

      public Builder clear() {
         super.clear();
         this.bitField0_ = 0;
         this.version_ = 0;
         this.algorithm_ = 0;
         this.keySize_ = 0;
         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return JwtHmac.internal_static_google_crypto_tink_JwtHmacKeyFormat_descriptor;
      }

      public JwtHmacKeyFormat getDefaultInstanceForType() {
         return JwtHmacKeyFormat.getDefaultInstance();
      }

      public JwtHmacKeyFormat build() {
         JwtHmacKeyFormat result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public JwtHmacKeyFormat buildPartial() {
         JwtHmacKeyFormat result = new JwtHmacKeyFormat(this);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartial0(JwtHmacKeyFormat result) {
         int from_bitField0_ = this.bitField0_;
         if ((from_bitField0_ & 1) != 0) {
            result.version_ = this.version_;
         }

         if ((from_bitField0_ & 2) != 0) {
            result.algorithm_ = this.algorithm_;
         }

         if ((from_bitField0_ & 4) != 0) {
            result.keySize_ = this.keySize_;
         }

      }

      public Builder mergeFrom(Message other) {
         if (other instanceof JwtHmacKeyFormat) {
            return this.mergeFrom((JwtHmacKeyFormat)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(JwtHmacKeyFormat other) {
         if (other == JwtHmacKeyFormat.getDefaultInstance()) {
            return this;
         } else {
            if (other.getVersion() != 0) {
               this.setVersion(other.getVersion());
            }

            if (other.algorithm_ != 0) {
               this.setAlgorithmValue(other.getAlgorithmValue());
            }

            if (other.getKeySize() != 0) {
               this.setKeySize(other.getKeySize());
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
                     case 16:
                        this.algorithm_ = input.readEnum();
                        this.bitField0_ |= 2;
                        break;
                     case 24:
                        this.keySize_ = input.readUInt32();
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

      public int getAlgorithmValue() {
         return this.algorithm_;
      }

      public Builder setAlgorithmValue(int value) {
         this.algorithm_ = value;
         this.bitField0_ |= 2;
         this.onChanged();
         return this;
      }

      public JwtHmacAlgorithm getAlgorithm() {
         JwtHmacAlgorithm result = JwtHmacAlgorithm.forNumber(this.algorithm_);
         return result == null ? JwtHmacAlgorithm.UNRECOGNIZED : result;
      }

      public Builder setAlgorithm(JwtHmacAlgorithm value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.bitField0_ |= 2;
            this.algorithm_ = value.getNumber();
            this.onChanged();
            return this;
         }
      }

      public Builder clearAlgorithm() {
         this.bitField0_ &= -3;
         this.algorithm_ = 0;
         this.onChanged();
         return this;
      }

      public int getKeySize() {
         return this.keySize_;
      }

      public Builder setKeySize(int value) {
         this.keySize_ = value;
         this.bitField0_ |= 4;
         this.onChanged();
         return this;
      }

      public Builder clearKeySize() {
         this.bitField0_ &= -5;
         this.keySize_ = 0;
         this.onChanged();
         return this;
      }
   }
}
