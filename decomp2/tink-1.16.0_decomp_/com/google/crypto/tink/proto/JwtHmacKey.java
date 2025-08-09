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
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.RuntimeVersion;
import com.google.protobuf.SingleFieldBuilder;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.RuntimeVersion.RuntimeDomain;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class JwtHmacKey extends GeneratedMessage implements JwtHmacKeyOrBuilder {
   private static final long serialVersionUID = 0L;
   private int bitField0_;
   public static final int VERSION_FIELD_NUMBER = 1;
   private int version_;
   public static final int ALGORITHM_FIELD_NUMBER = 2;
   private int algorithm_;
   public static final int KEY_VALUE_FIELD_NUMBER = 3;
   private ByteString keyValue_;
   public static final int CUSTOM_KID_FIELD_NUMBER = 4;
   private CustomKid customKid_;
   private byte memoizedIsInitialized;
   private static final JwtHmacKey DEFAULT_INSTANCE;
   private static final Parser PARSER;

   private JwtHmacKey(GeneratedMessage.Builder builder) {
      super(builder);
      this.version_ = 0;
      this.algorithm_ = 0;
      this.keyValue_ = ByteString.EMPTY;
      this.memoizedIsInitialized = -1;
   }

   private JwtHmacKey() {
      this.version_ = 0;
      this.algorithm_ = 0;
      this.keyValue_ = ByteString.EMPTY;
      this.memoizedIsInitialized = -1;
      this.algorithm_ = 0;
      this.keyValue_ = ByteString.EMPTY;
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return JwtHmac.internal_static_google_crypto_tink_JwtHmacKey_descriptor;
   }

   protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
      return JwtHmac.internal_static_google_crypto_tink_JwtHmacKey_fieldAccessorTable.ensureFieldAccessorsInitialized(JwtHmacKey.class, Builder.class);
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

   public ByteString getKeyValue() {
      return this.keyValue_;
   }

   public boolean hasCustomKid() {
      return (this.bitField0_ & 1) != 0;
   }

   public CustomKid getCustomKid() {
      return this.customKid_ == null ? JwtHmacKey.CustomKid.getDefaultInstance() : this.customKid_;
   }

   public CustomKidOrBuilder getCustomKidOrBuilder() {
      return this.customKid_ == null ? JwtHmacKey.CustomKid.getDefaultInstance() : this.customKid_;
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

      if (!this.keyValue_.isEmpty()) {
         output.writeBytes(3, this.keyValue_);
      }

      if ((this.bitField0_ & 1) != 0) {
         output.writeMessage(4, this.getCustomKid());
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

         if (!this.keyValue_.isEmpty()) {
            size += CodedOutputStream.computeBytesSize(3, this.keyValue_);
         }

         if ((this.bitField0_ & 1) != 0) {
            size += CodedOutputStream.computeMessageSize(4, this.getCustomKid());
         }

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof JwtHmacKey)) {
         return super.equals(obj);
      } else {
         JwtHmacKey other = (JwtHmacKey)obj;
         if (this.getVersion() != other.getVersion()) {
            return false;
         } else if (this.algorithm_ != other.algorithm_) {
            return false;
         } else if (!this.getKeyValue().equals(other.getKeyValue())) {
            return false;
         } else if (this.hasCustomKid() != other.hasCustomKid()) {
            return false;
         } else if (this.hasCustomKid() && !this.getCustomKid().equals(other.getCustomKid())) {
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
         hash = 53 * hash + this.getKeyValue().hashCode();
         if (this.hasCustomKid()) {
            hash = 37 * hash + 4;
            hash = 53 * hash + this.getCustomKid().hashCode();
         }

         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static JwtHmacKey parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (JwtHmacKey)PARSER.parseFrom(data);
   }

   public static JwtHmacKey parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (JwtHmacKey)PARSER.parseFrom(data, extensionRegistry);
   }

   public static JwtHmacKey parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (JwtHmacKey)PARSER.parseFrom(data);
   }

   public static JwtHmacKey parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (JwtHmacKey)PARSER.parseFrom(data, extensionRegistry);
   }

   public static JwtHmacKey parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (JwtHmacKey)PARSER.parseFrom(data);
   }

   public static JwtHmacKey parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (JwtHmacKey)PARSER.parseFrom(data, extensionRegistry);
   }

   public static JwtHmacKey parseFrom(InputStream input) throws IOException {
      return (JwtHmacKey)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static JwtHmacKey parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (JwtHmacKey)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static JwtHmacKey parseDelimitedFrom(InputStream input) throws IOException {
      return (JwtHmacKey)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
   }

   public static JwtHmacKey parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (JwtHmacKey)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static JwtHmacKey parseFrom(CodedInputStream input) throws IOException {
      return (JwtHmacKey)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static JwtHmacKey parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (JwtHmacKey)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(JwtHmacKey prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static JwtHmacKey getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public JwtHmacKey getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", JwtHmacKey.class.getName());
      DEFAULT_INSTANCE = new JwtHmacKey();
      PARSER = new AbstractParser() {
         public JwtHmacKey parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            Builder builder = JwtHmacKey.newBuilder();

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

   public static final class CustomKid extends GeneratedMessage implements CustomKidOrBuilder {
      private static final long serialVersionUID = 0L;
      public static final int VALUE_FIELD_NUMBER = 1;
      private volatile Object value_;
      private byte memoizedIsInitialized;
      private static final CustomKid DEFAULT_INSTANCE;
      private static final Parser PARSER;

      private CustomKid(GeneratedMessage.Builder builder) {
         super(builder);
         this.value_ = "";
         this.memoizedIsInitialized = -1;
      }

      private CustomKid() {
         this.value_ = "";
         this.memoizedIsInitialized = -1;
         this.value_ = "";
      }

      public static final Descriptors.Descriptor getDescriptor() {
         return JwtHmac.internal_static_google_crypto_tink_JwtHmacKey_CustomKid_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return JwtHmac.internal_static_google_crypto_tink_JwtHmacKey_CustomKid_fieldAccessorTable.ensureFieldAccessorsInitialized(CustomKid.class, Builder.class);
      }

      public String getValue() {
         Object ref = this.value_;
         if (ref instanceof String) {
            return (String)ref;
         } else {
            ByteString bs = (ByteString)ref;
            String s = bs.toStringUtf8();
            this.value_ = s;
            return s;
         }
      }

      public ByteString getValueBytes() {
         Object ref = this.value_;
         if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.value_ = b;
            return b;
         } else {
            return (ByteString)ref;
         }
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
         if (!GeneratedMessage.isStringEmpty(this.value_)) {
            GeneratedMessage.writeString(output, 1, this.value_);
         }

         this.getUnknownFields().writeTo(output);
      }

      public int getSerializedSize() {
         int size = this.memoizedSize;
         if (size != -1) {
            return size;
         } else {
            size = 0;
            if (!GeneratedMessage.isStringEmpty(this.value_)) {
               size += GeneratedMessage.computeStringSize(1, this.value_);
            }

            size += this.getUnknownFields().getSerializedSize();
            this.memoizedSize = size;
            return size;
         }
      }

      public boolean equals(final Object obj) {
         if (obj == this) {
            return true;
         } else if (!(obj instanceof CustomKid)) {
            return super.equals(obj);
         } else {
            CustomKid other = (CustomKid)obj;
            if (!this.getValue().equals(other.getValue())) {
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
            hash = 53 * hash + this.getValue().hashCode();
            hash = 29 * hash + this.getUnknownFields().hashCode();
            this.memoizedHashCode = hash;
            return hash;
         }
      }

      public static CustomKid parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
         return (CustomKid)PARSER.parseFrom(data);
      }

      public static CustomKid parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
         return (CustomKid)PARSER.parseFrom(data, extensionRegistry);
      }

      public static CustomKid parseFrom(ByteString data) throws InvalidProtocolBufferException {
         return (CustomKid)PARSER.parseFrom(data);
      }

      public static CustomKid parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
         return (CustomKid)PARSER.parseFrom(data, extensionRegistry);
      }

      public static CustomKid parseFrom(byte[] data) throws InvalidProtocolBufferException {
         return (CustomKid)PARSER.parseFrom(data);
      }

      public static CustomKid parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
         return (CustomKid)PARSER.parseFrom(data, extensionRegistry);
      }

      public static CustomKid parseFrom(InputStream input) throws IOException {
         return (CustomKid)GeneratedMessage.parseWithIOException(PARSER, input);
      }

      public static CustomKid parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
         return (CustomKid)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
      }

      public static CustomKid parseDelimitedFrom(InputStream input) throws IOException {
         return (CustomKid)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
      }

      public static CustomKid parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
         return (CustomKid)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
      }

      public static CustomKid parseFrom(CodedInputStream input) throws IOException {
         return (CustomKid)GeneratedMessage.parseWithIOException(PARSER, input);
      }

      public static CustomKid parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
         return (CustomKid)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
      }

      public Builder newBuilderForType() {
         return newBuilder();
      }

      public static Builder newBuilder() {
         return DEFAULT_INSTANCE.toBuilder();
      }

      public static Builder newBuilder(CustomKid prototype) {
         return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
      }

      public Builder toBuilder() {
         return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
      }

      protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
         Builder builder = new Builder(parent);
         return builder;
      }

      public static CustomKid getDefaultInstance() {
         return DEFAULT_INSTANCE;
      }

      public static Parser parser() {
         return PARSER;
      }

      public Parser getParserForType() {
         return PARSER;
      }

      public CustomKid getDefaultInstanceForType() {
         return DEFAULT_INSTANCE;
      }

      static {
         RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", CustomKid.class.getName());
         DEFAULT_INSTANCE = new CustomKid();
         PARSER = new AbstractParser() {
            public CustomKid parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
               Builder builder = JwtHmacKey.CustomKid.newBuilder();

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

      public static final class Builder extends GeneratedMessage.Builder implements CustomKidOrBuilder {
         private int bitField0_;
         private Object value_;

         public static final Descriptors.Descriptor getDescriptor() {
            return JwtHmac.internal_static_google_crypto_tink_JwtHmacKey_CustomKid_descriptor;
         }

         protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return JwtHmac.internal_static_google_crypto_tink_JwtHmacKey_CustomKid_fieldAccessorTable.ensureFieldAccessorsInitialized(CustomKid.class, Builder.class);
         }

         private Builder() {
            this.value_ = "";
         }

         private Builder(AbstractMessage.BuilderParent parent) {
            super(parent);
            this.value_ = "";
         }

         public Builder clear() {
            super.clear();
            this.bitField0_ = 0;
            this.value_ = "";
            return this;
         }

         public Descriptors.Descriptor getDescriptorForType() {
            return JwtHmac.internal_static_google_crypto_tink_JwtHmacKey_CustomKid_descriptor;
         }

         public CustomKid getDefaultInstanceForType() {
            return JwtHmacKey.CustomKid.getDefaultInstance();
         }

         public CustomKid build() {
            CustomKid result = this.buildPartial();
            if (!result.isInitialized()) {
               throw newUninitializedMessageException(result);
            } else {
               return result;
            }
         }

         public CustomKid buildPartial() {
            CustomKid result = new CustomKid(this);
            if (this.bitField0_ != 0) {
               this.buildPartial0(result);
            }

            this.onBuilt();
            return result;
         }

         private void buildPartial0(CustomKid result) {
            int from_bitField0_ = this.bitField0_;
            if ((from_bitField0_ & 1) != 0) {
               result.value_ = this.value_;
            }

         }

         public Builder mergeFrom(Message other) {
            if (other instanceof CustomKid) {
               return this.mergeFrom((CustomKid)other);
            } else {
               super.mergeFrom(other);
               return this;
            }
         }

         public Builder mergeFrom(CustomKid other) {
            if (other == JwtHmacKey.CustomKid.getDefaultInstance()) {
               return this;
            } else {
               if (!other.getValue().isEmpty()) {
                  this.value_ = other.value_;
                  this.bitField0_ |= 1;
                  this.onChanged();
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
                        case 10:
                           this.value_ = input.readStringRequireUtf8();
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

         public String getValue() {
            Object ref = this.value_;
            if (!(ref instanceof String)) {
               ByteString bs = (ByteString)ref;
               String s = bs.toStringUtf8();
               this.value_ = s;
               return s;
            } else {
               return (String)ref;
            }
         }

         public ByteString getValueBytes() {
            Object ref = this.value_;
            if (ref instanceof String) {
               ByteString b = ByteString.copyFromUtf8((String)ref);
               this.value_ = b;
               return b;
            } else {
               return (ByteString)ref;
            }
         }

         public Builder setValue(String value) {
            if (value == null) {
               throw new NullPointerException();
            } else {
               this.value_ = value;
               this.bitField0_ |= 1;
               this.onChanged();
               return this;
            }
         }

         public Builder clearValue() {
            this.value_ = JwtHmacKey.CustomKid.getDefaultInstance().getValue();
            this.bitField0_ &= -2;
            this.onChanged();
            return this;
         }

         public Builder setValueBytes(ByteString value) {
            if (value == null) {
               throw new NullPointerException();
            } else {
               JwtHmacKey.CustomKid.checkByteStringIsUtf8(value);
               this.value_ = value;
               this.bitField0_ |= 1;
               this.onChanged();
               return this;
            }
         }
      }
   }

   public static final class Builder extends GeneratedMessage.Builder implements JwtHmacKeyOrBuilder {
      private int bitField0_;
      private int version_;
      private int algorithm_;
      private ByteString keyValue_;
      private CustomKid customKid_;
      private SingleFieldBuilder customKidBuilder_;

      public static final Descriptors.Descriptor getDescriptor() {
         return JwtHmac.internal_static_google_crypto_tink_JwtHmacKey_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return JwtHmac.internal_static_google_crypto_tink_JwtHmacKey_fieldAccessorTable.ensureFieldAccessorsInitialized(JwtHmacKey.class, Builder.class);
      }

      private Builder() {
         this.algorithm_ = 0;
         this.keyValue_ = ByteString.EMPTY;
         this.maybeForceBuilderInitialization();
      }

      private Builder(AbstractMessage.BuilderParent parent) {
         super(parent);
         this.algorithm_ = 0;
         this.keyValue_ = ByteString.EMPTY;
         this.maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization() {
         if (JwtHmacKey.alwaysUseFieldBuilders) {
            this.getCustomKidFieldBuilder();
         }

      }

      public Builder clear() {
         super.clear();
         this.bitField0_ = 0;
         this.version_ = 0;
         this.algorithm_ = 0;
         this.keyValue_ = ByteString.EMPTY;
         this.customKid_ = null;
         if (this.customKidBuilder_ != null) {
            this.customKidBuilder_.dispose();
            this.customKidBuilder_ = null;
         }

         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return JwtHmac.internal_static_google_crypto_tink_JwtHmacKey_descriptor;
      }

      public JwtHmacKey getDefaultInstanceForType() {
         return JwtHmacKey.getDefaultInstance();
      }

      public JwtHmacKey build() {
         JwtHmacKey result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public JwtHmacKey buildPartial() {
         JwtHmacKey result = new JwtHmacKey(this);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartial0(JwtHmacKey result) {
         int from_bitField0_ = this.bitField0_;
         if ((from_bitField0_ & 1) != 0) {
            result.version_ = this.version_;
         }

         if ((from_bitField0_ & 2) != 0) {
            result.algorithm_ = this.algorithm_;
         }

         if ((from_bitField0_ & 4) != 0) {
            result.keyValue_ = this.keyValue_;
         }

         int to_bitField0_ = 0;
         if ((from_bitField0_ & 8) != 0) {
            result.customKid_ = this.customKidBuilder_ == null ? this.customKid_ : (CustomKid)this.customKidBuilder_.build();
            to_bitField0_ |= 1;
         }

         result.bitField0_ = to_bitField0_;
      }

      public Builder mergeFrom(Message other) {
         if (other instanceof JwtHmacKey) {
            return this.mergeFrom((JwtHmacKey)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(JwtHmacKey other) {
         if (other == JwtHmacKey.getDefaultInstance()) {
            return this;
         } else {
            if (other.getVersion() != 0) {
               this.setVersion(other.getVersion());
            }

            if (other.algorithm_ != 0) {
               this.setAlgorithmValue(other.getAlgorithmValue());
            }

            if (other.getKeyValue() != ByteString.EMPTY) {
               this.setKeyValue(other.getKeyValue());
            }

            if (other.hasCustomKid()) {
               this.mergeCustomKid(other.getCustomKid());
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
                     case 26:
                        this.keyValue_ = input.readBytes();
                        this.bitField0_ |= 4;
                        break;
                     case 34:
                        input.readMessage(this.getCustomKidFieldBuilder().getBuilder(), extensionRegistry);
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

      public ByteString getKeyValue() {
         return this.keyValue_;
      }

      public Builder setKeyValue(ByteString value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.keyValue_ = value;
            this.bitField0_ |= 4;
            this.onChanged();
            return this;
         }
      }

      public Builder clearKeyValue() {
         this.bitField0_ &= -5;
         this.keyValue_ = JwtHmacKey.getDefaultInstance().getKeyValue();
         this.onChanged();
         return this;
      }

      public boolean hasCustomKid() {
         return (this.bitField0_ & 8) != 0;
      }

      public CustomKid getCustomKid() {
         if (this.customKidBuilder_ == null) {
            return this.customKid_ == null ? JwtHmacKey.CustomKid.getDefaultInstance() : this.customKid_;
         } else {
            return (CustomKid)this.customKidBuilder_.getMessage();
         }
      }

      public Builder setCustomKid(CustomKid value) {
         if (this.customKidBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.customKid_ = value;
         } else {
            this.customKidBuilder_.setMessage(value);
         }

         this.bitField0_ |= 8;
         this.onChanged();
         return this;
      }

      public Builder setCustomKid(CustomKid.Builder builderForValue) {
         if (this.customKidBuilder_ == null) {
            this.customKid_ = builderForValue.build();
         } else {
            this.customKidBuilder_.setMessage(builderForValue.build());
         }

         this.bitField0_ |= 8;
         this.onChanged();
         return this;
      }

      public Builder mergeCustomKid(CustomKid value) {
         if (this.customKidBuilder_ == null) {
            if ((this.bitField0_ & 8) != 0 && this.customKid_ != null && this.customKid_ != JwtHmacKey.CustomKid.getDefaultInstance()) {
               this.getCustomKidBuilder().mergeFrom(value);
            } else {
               this.customKid_ = value;
            }
         } else {
            this.customKidBuilder_.mergeFrom(value);
         }

         if (this.customKid_ != null) {
            this.bitField0_ |= 8;
            this.onChanged();
         }

         return this;
      }

      public Builder clearCustomKid() {
         this.bitField0_ &= -9;
         this.customKid_ = null;
         if (this.customKidBuilder_ != null) {
            this.customKidBuilder_.dispose();
            this.customKidBuilder_ = null;
         }

         this.onChanged();
         return this;
      }

      public CustomKid.Builder getCustomKidBuilder() {
         this.bitField0_ |= 8;
         this.onChanged();
         return (CustomKid.Builder)this.getCustomKidFieldBuilder().getBuilder();
      }

      public CustomKidOrBuilder getCustomKidOrBuilder() {
         if (this.customKidBuilder_ != null) {
            return (CustomKidOrBuilder)this.customKidBuilder_.getMessageOrBuilder();
         } else {
            return this.customKid_ == null ? JwtHmacKey.CustomKid.getDefaultInstance() : this.customKid_;
         }
      }

      private SingleFieldBuilder getCustomKidFieldBuilder() {
         if (this.customKidBuilder_ == null) {
            this.customKidBuilder_ = new SingleFieldBuilder(this.getCustomKid(), this.getParentForChildren(), this.isClean());
            this.customKid_ = null;
         }

         return this.customKidBuilder_;
      }
   }

   public interface CustomKidOrBuilder extends MessageOrBuilder {
      String getValue();

      ByteString getValueBytes();
   }
}
