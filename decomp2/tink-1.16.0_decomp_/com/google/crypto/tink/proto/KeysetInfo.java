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
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilder;
import com.google.protobuf.RuntimeVersion;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.RuntimeVersion.RuntimeDomain;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class KeysetInfo extends GeneratedMessage implements KeysetInfoOrBuilder {
   private static final long serialVersionUID = 0L;
   public static final int PRIMARY_KEY_ID_FIELD_NUMBER = 1;
   private int primaryKeyId_;
   public static final int KEY_INFO_FIELD_NUMBER = 2;
   private List keyInfo_;
   private byte memoizedIsInitialized;
   private static final KeysetInfo DEFAULT_INSTANCE;
   private static final Parser PARSER;

   private KeysetInfo(GeneratedMessage.Builder builder) {
      super(builder);
      this.primaryKeyId_ = 0;
      this.memoizedIsInitialized = -1;
   }

   private KeysetInfo() {
      this.primaryKeyId_ = 0;
      this.memoizedIsInitialized = -1;
      this.keyInfo_ = Collections.emptyList();
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return Tink.internal_static_google_crypto_tink_KeysetInfo_descriptor;
   }

   protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
      return Tink.internal_static_google_crypto_tink_KeysetInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(KeysetInfo.class, Builder.class);
   }

   public int getPrimaryKeyId() {
      return this.primaryKeyId_;
   }

   public List getKeyInfoList() {
      return this.keyInfo_;
   }

   public List getKeyInfoOrBuilderList() {
      return this.keyInfo_;
   }

   public int getKeyInfoCount() {
      return this.keyInfo_.size();
   }

   public KeyInfo getKeyInfo(int index) {
      return (KeyInfo)this.keyInfo_.get(index);
   }

   public KeyInfoOrBuilder getKeyInfoOrBuilder(int index) {
      return (KeyInfoOrBuilder)this.keyInfo_.get(index);
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
      if (this.primaryKeyId_ != 0) {
         output.writeUInt32(1, this.primaryKeyId_);
      }

      for(int i = 0; i < this.keyInfo_.size(); ++i) {
         output.writeMessage(2, (MessageLite)this.keyInfo_.get(i));
      }

      this.getUnknownFields().writeTo(output);
   }

   public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1) {
         return size;
      } else {
         size = 0;
         if (this.primaryKeyId_ != 0) {
            size += CodedOutputStream.computeUInt32Size(1, this.primaryKeyId_);
         }

         for(int i = 0; i < this.keyInfo_.size(); ++i) {
            size += CodedOutputStream.computeMessageSize(2, (MessageLite)this.keyInfo_.get(i));
         }

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof KeysetInfo)) {
         return super.equals(obj);
      } else {
         KeysetInfo other = (KeysetInfo)obj;
         if (this.getPrimaryKeyId() != other.getPrimaryKeyId()) {
            return false;
         } else if (!this.getKeyInfoList().equals(other.getKeyInfoList())) {
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
         hash = 53 * hash + this.getPrimaryKeyId();
         if (this.getKeyInfoCount() > 0) {
            hash = 37 * hash + 2;
            hash = 53 * hash + this.getKeyInfoList().hashCode();
         }

         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static KeysetInfo parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (KeysetInfo)PARSER.parseFrom(data);
   }

   public static KeysetInfo parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (KeysetInfo)PARSER.parseFrom(data, extensionRegistry);
   }

   public static KeysetInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (KeysetInfo)PARSER.parseFrom(data);
   }

   public static KeysetInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (KeysetInfo)PARSER.parseFrom(data, extensionRegistry);
   }

   public static KeysetInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (KeysetInfo)PARSER.parseFrom(data);
   }

   public static KeysetInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (KeysetInfo)PARSER.parseFrom(data, extensionRegistry);
   }

   public static KeysetInfo parseFrom(InputStream input) throws IOException {
      return (KeysetInfo)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static KeysetInfo parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (KeysetInfo)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static KeysetInfo parseDelimitedFrom(InputStream input) throws IOException {
      return (KeysetInfo)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
   }

   public static KeysetInfo parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (KeysetInfo)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static KeysetInfo parseFrom(CodedInputStream input) throws IOException {
      return (KeysetInfo)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static KeysetInfo parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (KeysetInfo)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(KeysetInfo prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static KeysetInfo getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public KeysetInfo getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", KeysetInfo.class.getName());
      DEFAULT_INSTANCE = new KeysetInfo();
      PARSER = new AbstractParser() {
         public KeysetInfo parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            Builder builder = KeysetInfo.newBuilder();

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

   public static final class KeyInfo extends GeneratedMessage implements KeyInfoOrBuilder {
      private static final long serialVersionUID = 0L;
      public static final int TYPE_URL_FIELD_NUMBER = 1;
      private volatile Object typeUrl_;
      public static final int STATUS_FIELD_NUMBER = 2;
      private int status_;
      public static final int KEY_ID_FIELD_NUMBER = 3;
      private int keyId_;
      public static final int OUTPUT_PREFIX_TYPE_FIELD_NUMBER = 4;
      private int outputPrefixType_;
      private byte memoizedIsInitialized;
      private static final KeyInfo DEFAULT_INSTANCE;
      private static final Parser PARSER;

      private KeyInfo(GeneratedMessage.Builder builder) {
         super(builder);
         this.typeUrl_ = "";
         this.status_ = 0;
         this.keyId_ = 0;
         this.outputPrefixType_ = 0;
         this.memoizedIsInitialized = -1;
      }

      private KeyInfo() {
         this.typeUrl_ = "";
         this.status_ = 0;
         this.keyId_ = 0;
         this.outputPrefixType_ = 0;
         this.memoizedIsInitialized = -1;
         this.typeUrl_ = "";
         this.status_ = 0;
         this.outputPrefixType_ = 0;
      }

      public static final Descriptors.Descriptor getDescriptor() {
         return Tink.internal_static_google_crypto_tink_KeysetInfo_KeyInfo_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return Tink.internal_static_google_crypto_tink_KeysetInfo_KeyInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(KeyInfo.class, Builder.class);
      }

      public String getTypeUrl() {
         Object ref = this.typeUrl_;
         if (ref instanceof String) {
            return (String)ref;
         } else {
            ByteString bs = (ByteString)ref;
            String s = bs.toStringUtf8();
            this.typeUrl_ = s;
            return s;
         }
      }

      public ByteString getTypeUrlBytes() {
         Object ref = this.typeUrl_;
         if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.typeUrl_ = b;
            return b;
         } else {
            return (ByteString)ref;
         }
      }

      public int getStatusValue() {
         return this.status_;
      }

      public KeyStatusType getStatus() {
         KeyStatusType result = KeyStatusType.forNumber(this.status_);
         return result == null ? KeyStatusType.UNRECOGNIZED : result;
      }

      public int getKeyId() {
         return this.keyId_;
      }

      public int getOutputPrefixTypeValue() {
         return this.outputPrefixType_;
      }

      public OutputPrefixType getOutputPrefixType() {
         OutputPrefixType result = OutputPrefixType.forNumber(this.outputPrefixType_);
         return result == null ? OutputPrefixType.UNRECOGNIZED : result;
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
         if (!GeneratedMessage.isStringEmpty(this.typeUrl_)) {
            GeneratedMessage.writeString(output, 1, this.typeUrl_);
         }

         if (this.status_ != KeyStatusType.UNKNOWN_STATUS.getNumber()) {
            output.writeEnum(2, this.status_);
         }

         if (this.keyId_ != 0) {
            output.writeUInt32(3, this.keyId_);
         }

         if (this.outputPrefixType_ != OutputPrefixType.UNKNOWN_PREFIX.getNumber()) {
            output.writeEnum(4, this.outputPrefixType_);
         }

         this.getUnknownFields().writeTo(output);
      }

      public int getSerializedSize() {
         int size = this.memoizedSize;
         if (size != -1) {
            return size;
         } else {
            size = 0;
            if (!GeneratedMessage.isStringEmpty(this.typeUrl_)) {
               size += GeneratedMessage.computeStringSize(1, this.typeUrl_);
            }

            if (this.status_ != KeyStatusType.UNKNOWN_STATUS.getNumber()) {
               size += CodedOutputStream.computeEnumSize(2, this.status_);
            }

            if (this.keyId_ != 0) {
               size += CodedOutputStream.computeUInt32Size(3, this.keyId_);
            }

            if (this.outputPrefixType_ != OutputPrefixType.UNKNOWN_PREFIX.getNumber()) {
               size += CodedOutputStream.computeEnumSize(4, this.outputPrefixType_);
            }

            size += this.getUnknownFields().getSerializedSize();
            this.memoizedSize = size;
            return size;
         }
      }

      public boolean equals(final Object obj) {
         if (obj == this) {
            return true;
         } else if (!(obj instanceof KeyInfo)) {
            return super.equals(obj);
         } else {
            KeyInfo other = (KeyInfo)obj;
            if (!this.getTypeUrl().equals(other.getTypeUrl())) {
               return false;
            } else if (this.status_ != other.status_) {
               return false;
            } else if (this.getKeyId() != other.getKeyId()) {
               return false;
            } else if (this.outputPrefixType_ != other.outputPrefixType_) {
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
            hash = 53 * hash + this.getTypeUrl().hashCode();
            hash = 37 * hash + 2;
            hash = 53 * hash + this.status_;
            hash = 37 * hash + 3;
            hash = 53 * hash + this.getKeyId();
            hash = 37 * hash + 4;
            hash = 53 * hash + this.outputPrefixType_;
            hash = 29 * hash + this.getUnknownFields().hashCode();
            this.memoizedHashCode = hash;
            return hash;
         }
      }

      public static KeyInfo parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
         return (KeyInfo)PARSER.parseFrom(data);
      }

      public static KeyInfo parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
         return (KeyInfo)PARSER.parseFrom(data, extensionRegistry);
      }

      public static KeyInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
         return (KeyInfo)PARSER.parseFrom(data);
      }

      public static KeyInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
         return (KeyInfo)PARSER.parseFrom(data, extensionRegistry);
      }

      public static KeyInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
         return (KeyInfo)PARSER.parseFrom(data);
      }

      public static KeyInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
         return (KeyInfo)PARSER.parseFrom(data, extensionRegistry);
      }

      public static KeyInfo parseFrom(InputStream input) throws IOException {
         return (KeyInfo)GeneratedMessage.parseWithIOException(PARSER, input);
      }

      public static KeyInfo parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
         return (KeyInfo)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
      }

      public static KeyInfo parseDelimitedFrom(InputStream input) throws IOException {
         return (KeyInfo)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
      }

      public static KeyInfo parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
         return (KeyInfo)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
      }

      public static KeyInfo parseFrom(CodedInputStream input) throws IOException {
         return (KeyInfo)GeneratedMessage.parseWithIOException(PARSER, input);
      }

      public static KeyInfo parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
         return (KeyInfo)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
      }

      public Builder newBuilderForType() {
         return newBuilder();
      }

      public static Builder newBuilder() {
         return DEFAULT_INSTANCE.toBuilder();
      }

      public static Builder newBuilder(KeyInfo prototype) {
         return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
      }

      public Builder toBuilder() {
         return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
      }

      protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
         Builder builder = new Builder(parent);
         return builder;
      }

      public static KeyInfo getDefaultInstance() {
         return DEFAULT_INSTANCE;
      }

      public static Parser parser() {
         return PARSER;
      }

      public Parser getParserForType() {
         return PARSER;
      }

      public KeyInfo getDefaultInstanceForType() {
         return DEFAULT_INSTANCE;
      }

      static {
         RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", KeyInfo.class.getName());
         DEFAULT_INSTANCE = new KeyInfo();
         PARSER = new AbstractParser() {
            public KeyInfo parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
               Builder builder = KeysetInfo.KeyInfo.newBuilder();

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

      public static final class Builder extends GeneratedMessage.Builder implements KeyInfoOrBuilder {
         private int bitField0_;
         private Object typeUrl_;
         private int status_;
         private int keyId_;
         private int outputPrefixType_;

         public static final Descriptors.Descriptor getDescriptor() {
            return Tink.internal_static_google_crypto_tink_KeysetInfo_KeyInfo_descriptor;
         }

         protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Tink.internal_static_google_crypto_tink_KeysetInfo_KeyInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(KeyInfo.class, Builder.class);
         }

         private Builder() {
            this.typeUrl_ = "";
            this.status_ = 0;
            this.outputPrefixType_ = 0;
         }

         private Builder(AbstractMessage.BuilderParent parent) {
            super(parent);
            this.typeUrl_ = "";
            this.status_ = 0;
            this.outputPrefixType_ = 0;
         }

         public Builder clear() {
            super.clear();
            this.bitField0_ = 0;
            this.typeUrl_ = "";
            this.status_ = 0;
            this.keyId_ = 0;
            this.outputPrefixType_ = 0;
            return this;
         }

         public Descriptors.Descriptor getDescriptorForType() {
            return Tink.internal_static_google_crypto_tink_KeysetInfo_KeyInfo_descriptor;
         }

         public KeyInfo getDefaultInstanceForType() {
            return KeysetInfo.KeyInfo.getDefaultInstance();
         }

         public KeyInfo build() {
            KeyInfo result = this.buildPartial();
            if (!result.isInitialized()) {
               throw newUninitializedMessageException(result);
            } else {
               return result;
            }
         }

         public KeyInfo buildPartial() {
            KeyInfo result = new KeyInfo(this);
            if (this.bitField0_ != 0) {
               this.buildPartial0(result);
            }

            this.onBuilt();
            return result;
         }

         private void buildPartial0(KeyInfo result) {
            int from_bitField0_ = this.bitField0_;
            if ((from_bitField0_ & 1) != 0) {
               result.typeUrl_ = this.typeUrl_;
            }

            if ((from_bitField0_ & 2) != 0) {
               result.status_ = this.status_;
            }

            if ((from_bitField0_ & 4) != 0) {
               result.keyId_ = this.keyId_;
            }

            if ((from_bitField0_ & 8) != 0) {
               result.outputPrefixType_ = this.outputPrefixType_;
            }

         }

         public Builder mergeFrom(Message other) {
            if (other instanceof KeyInfo) {
               return this.mergeFrom((KeyInfo)other);
            } else {
               super.mergeFrom(other);
               return this;
            }
         }

         public Builder mergeFrom(KeyInfo other) {
            if (other == KeysetInfo.KeyInfo.getDefaultInstance()) {
               return this;
            } else {
               if (!other.getTypeUrl().isEmpty()) {
                  this.typeUrl_ = other.typeUrl_;
                  this.bitField0_ |= 1;
                  this.onChanged();
               }

               if (other.status_ != 0) {
                  this.setStatusValue(other.getStatusValue());
               }

               if (other.getKeyId() != 0) {
                  this.setKeyId(other.getKeyId());
               }

               if (other.outputPrefixType_ != 0) {
                  this.setOutputPrefixTypeValue(other.getOutputPrefixTypeValue());
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
                           this.typeUrl_ = input.readStringRequireUtf8();
                           this.bitField0_ |= 1;
                           break;
                        case 16:
                           this.status_ = input.readEnum();
                           this.bitField0_ |= 2;
                           break;
                        case 24:
                           this.keyId_ = input.readUInt32();
                           this.bitField0_ |= 4;
                           break;
                        case 32:
                           this.outputPrefixType_ = input.readEnum();
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

         public String getTypeUrl() {
            Object ref = this.typeUrl_;
            if (!(ref instanceof String)) {
               ByteString bs = (ByteString)ref;
               String s = bs.toStringUtf8();
               this.typeUrl_ = s;
               return s;
            } else {
               return (String)ref;
            }
         }

         public ByteString getTypeUrlBytes() {
            Object ref = this.typeUrl_;
            if (ref instanceof String) {
               ByteString b = ByteString.copyFromUtf8((String)ref);
               this.typeUrl_ = b;
               return b;
            } else {
               return (ByteString)ref;
            }
         }

         public Builder setTypeUrl(String value) {
            if (value == null) {
               throw new NullPointerException();
            } else {
               this.typeUrl_ = value;
               this.bitField0_ |= 1;
               this.onChanged();
               return this;
            }
         }

         public Builder clearTypeUrl() {
            this.typeUrl_ = KeysetInfo.KeyInfo.getDefaultInstance().getTypeUrl();
            this.bitField0_ &= -2;
            this.onChanged();
            return this;
         }

         public Builder setTypeUrlBytes(ByteString value) {
            if (value == null) {
               throw new NullPointerException();
            } else {
               KeysetInfo.KeyInfo.checkByteStringIsUtf8(value);
               this.typeUrl_ = value;
               this.bitField0_ |= 1;
               this.onChanged();
               return this;
            }
         }

         public int getStatusValue() {
            return this.status_;
         }

         public Builder setStatusValue(int value) {
            this.status_ = value;
            this.bitField0_ |= 2;
            this.onChanged();
            return this;
         }

         public KeyStatusType getStatus() {
            KeyStatusType result = KeyStatusType.forNumber(this.status_);
            return result == null ? KeyStatusType.UNRECOGNIZED : result;
         }

         public Builder setStatus(KeyStatusType value) {
            if (value == null) {
               throw new NullPointerException();
            } else {
               this.bitField0_ |= 2;
               this.status_ = value.getNumber();
               this.onChanged();
               return this;
            }
         }

         public Builder clearStatus() {
            this.bitField0_ &= -3;
            this.status_ = 0;
            this.onChanged();
            return this;
         }

         public int getKeyId() {
            return this.keyId_;
         }

         public Builder setKeyId(int value) {
            this.keyId_ = value;
            this.bitField0_ |= 4;
            this.onChanged();
            return this;
         }

         public Builder clearKeyId() {
            this.bitField0_ &= -5;
            this.keyId_ = 0;
            this.onChanged();
            return this;
         }

         public int getOutputPrefixTypeValue() {
            return this.outputPrefixType_;
         }

         public Builder setOutputPrefixTypeValue(int value) {
            this.outputPrefixType_ = value;
            this.bitField0_ |= 8;
            this.onChanged();
            return this;
         }

         public OutputPrefixType getOutputPrefixType() {
            OutputPrefixType result = OutputPrefixType.forNumber(this.outputPrefixType_);
            return result == null ? OutputPrefixType.UNRECOGNIZED : result;
         }

         public Builder setOutputPrefixType(OutputPrefixType value) {
            if (value == null) {
               throw new NullPointerException();
            } else {
               this.bitField0_ |= 8;
               this.outputPrefixType_ = value.getNumber();
               this.onChanged();
               return this;
            }
         }

         public Builder clearOutputPrefixType() {
            this.bitField0_ &= -9;
            this.outputPrefixType_ = 0;
            this.onChanged();
            return this;
         }
      }
   }

   public static final class Builder extends GeneratedMessage.Builder implements KeysetInfoOrBuilder {
      private int bitField0_;
      private int primaryKeyId_;
      private List keyInfo_;
      private RepeatedFieldBuilder keyInfoBuilder_;

      public static final Descriptors.Descriptor getDescriptor() {
         return Tink.internal_static_google_crypto_tink_KeysetInfo_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return Tink.internal_static_google_crypto_tink_KeysetInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(KeysetInfo.class, Builder.class);
      }

      private Builder() {
         this.keyInfo_ = Collections.emptyList();
      }

      private Builder(AbstractMessage.BuilderParent parent) {
         super(parent);
         this.keyInfo_ = Collections.emptyList();
      }

      public Builder clear() {
         super.clear();
         this.bitField0_ = 0;
         this.primaryKeyId_ = 0;
         if (this.keyInfoBuilder_ == null) {
            this.keyInfo_ = Collections.emptyList();
         } else {
            this.keyInfo_ = null;
            this.keyInfoBuilder_.clear();
         }

         this.bitField0_ &= -3;
         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return Tink.internal_static_google_crypto_tink_KeysetInfo_descriptor;
      }

      public KeysetInfo getDefaultInstanceForType() {
         return KeysetInfo.getDefaultInstance();
      }

      public KeysetInfo build() {
         KeysetInfo result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public KeysetInfo buildPartial() {
         KeysetInfo result = new KeysetInfo(this);
         this.buildPartialRepeatedFields(result);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartialRepeatedFields(KeysetInfo result) {
         if (this.keyInfoBuilder_ == null) {
            if ((this.bitField0_ & 2) != 0) {
               this.keyInfo_ = Collections.unmodifiableList(this.keyInfo_);
               this.bitField0_ &= -3;
            }

            result.keyInfo_ = this.keyInfo_;
         } else {
            result.keyInfo_ = this.keyInfoBuilder_.build();
         }

      }

      private void buildPartial0(KeysetInfo result) {
         int from_bitField0_ = this.bitField0_;
         if ((from_bitField0_ & 1) != 0) {
            result.primaryKeyId_ = this.primaryKeyId_;
         }

      }

      public Builder mergeFrom(Message other) {
         if (other instanceof KeysetInfo) {
            return this.mergeFrom((KeysetInfo)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(KeysetInfo other) {
         if (other == KeysetInfo.getDefaultInstance()) {
            return this;
         } else {
            if (other.getPrimaryKeyId() != 0) {
               this.setPrimaryKeyId(other.getPrimaryKeyId());
            }

            if (this.keyInfoBuilder_ == null) {
               if (!other.keyInfo_.isEmpty()) {
                  if (this.keyInfo_.isEmpty()) {
                     this.keyInfo_ = other.keyInfo_;
                     this.bitField0_ &= -3;
                  } else {
                     this.ensureKeyInfoIsMutable();
                     this.keyInfo_.addAll(other.keyInfo_);
                  }

                  this.onChanged();
               }
            } else if (!other.keyInfo_.isEmpty()) {
               if (this.keyInfoBuilder_.isEmpty()) {
                  this.keyInfoBuilder_.dispose();
                  this.keyInfoBuilder_ = null;
                  this.keyInfo_ = other.keyInfo_;
                  this.bitField0_ &= -3;
                  this.keyInfoBuilder_ = KeysetInfo.alwaysUseFieldBuilders ? this.getKeyInfoFieldBuilder() : null;
               } else {
                  this.keyInfoBuilder_.addAllMessages(other.keyInfo_);
               }
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
                        this.primaryKeyId_ = input.readUInt32();
                        this.bitField0_ |= 1;
                        break;
                     case 18:
                        KeyInfo m = (KeyInfo)input.readMessage(KeysetInfo.KeyInfo.parser(), extensionRegistry);
                        if (this.keyInfoBuilder_ == null) {
                           this.ensureKeyInfoIsMutable();
                           this.keyInfo_.add(m);
                        } else {
                           this.keyInfoBuilder_.addMessage(m);
                        }
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

      public int getPrimaryKeyId() {
         return this.primaryKeyId_;
      }

      public Builder setPrimaryKeyId(int value) {
         this.primaryKeyId_ = value;
         this.bitField0_ |= 1;
         this.onChanged();
         return this;
      }

      public Builder clearPrimaryKeyId() {
         this.bitField0_ &= -2;
         this.primaryKeyId_ = 0;
         this.onChanged();
         return this;
      }

      private void ensureKeyInfoIsMutable() {
         if ((this.bitField0_ & 2) == 0) {
            this.keyInfo_ = new ArrayList(this.keyInfo_);
            this.bitField0_ |= 2;
         }

      }

      public List getKeyInfoList() {
         return this.keyInfoBuilder_ == null ? Collections.unmodifiableList(this.keyInfo_) : this.keyInfoBuilder_.getMessageList();
      }

      public int getKeyInfoCount() {
         return this.keyInfoBuilder_ == null ? this.keyInfo_.size() : this.keyInfoBuilder_.getCount();
      }

      public KeyInfo getKeyInfo(int index) {
         return this.keyInfoBuilder_ == null ? (KeyInfo)this.keyInfo_.get(index) : (KeyInfo)this.keyInfoBuilder_.getMessage(index);
      }

      public Builder setKeyInfo(int index, KeyInfo value) {
         if (this.keyInfoBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.ensureKeyInfoIsMutable();
            this.keyInfo_.set(index, value);
            this.onChanged();
         } else {
            this.keyInfoBuilder_.setMessage(index, value);
         }

         return this;
      }

      public Builder setKeyInfo(int index, KeyInfo.Builder builderForValue) {
         if (this.keyInfoBuilder_ == null) {
            this.ensureKeyInfoIsMutable();
            this.keyInfo_.set(index, builderForValue.build());
            this.onChanged();
         } else {
            this.keyInfoBuilder_.setMessage(index, builderForValue.build());
         }

         return this;
      }

      public Builder addKeyInfo(KeyInfo value) {
         if (this.keyInfoBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.ensureKeyInfoIsMutable();
            this.keyInfo_.add(value);
            this.onChanged();
         } else {
            this.keyInfoBuilder_.addMessage(value);
         }

         return this;
      }

      public Builder addKeyInfo(int index, KeyInfo value) {
         if (this.keyInfoBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.ensureKeyInfoIsMutable();
            this.keyInfo_.add(index, value);
            this.onChanged();
         } else {
            this.keyInfoBuilder_.addMessage(index, value);
         }

         return this;
      }

      public Builder addKeyInfo(KeyInfo.Builder builderForValue) {
         if (this.keyInfoBuilder_ == null) {
            this.ensureKeyInfoIsMutable();
            this.keyInfo_.add(builderForValue.build());
            this.onChanged();
         } else {
            this.keyInfoBuilder_.addMessage(builderForValue.build());
         }

         return this;
      }

      public Builder addKeyInfo(int index, KeyInfo.Builder builderForValue) {
         if (this.keyInfoBuilder_ == null) {
            this.ensureKeyInfoIsMutable();
            this.keyInfo_.add(index, builderForValue.build());
            this.onChanged();
         } else {
            this.keyInfoBuilder_.addMessage(index, builderForValue.build());
         }

         return this;
      }

      public Builder addAllKeyInfo(Iterable values) {
         if (this.keyInfoBuilder_ == null) {
            this.ensureKeyInfoIsMutable();
            com.google.protobuf.AbstractMessageLite.Builder.addAll(values, this.keyInfo_);
            this.onChanged();
         } else {
            this.keyInfoBuilder_.addAllMessages(values);
         }

         return this;
      }

      public Builder clearKeyInfo() {
         if (this.keyInfoBuilder_ == null) {
            this.keyInfo_ = Collections.emptyList();
            this.bitField0_ &= -3;
            this.onChanged();
         } else {
            this.keyInfoBuilder_.clear();
         }

         return this;
      }

      public Builder removeKeyInfo(int index) {
         if (this.keyInfoBuilder_ == null) {
            this.ensureKeyInfoIsMutable();
            this.keyInfo_.remove(index);
            this.onChanged();
         } else {
            this.keyInfoBuilder_.remove(index);
         }

         return this;
      }

      public KeyInfo.Builder getKeyInfoBuilder(int index) {
         return (KeyInfo.Builder)this.getKeyInfoFieldBuilder().getBuilder(index);
      }

      public KeyInfoOrBuilder getKeyInfoOrBuilder(int index) {
         return this.keyInfoBuilder_ == null ? (KeyInfoOrBuilder)this.keyInfo_.get(index) : (KeyInfoOrBuilder)this.keyInfoBuilder_.getMessageOrBuilder(index);
      }

      public List getKeyInfoOrBuilderList() {
         return this.keyInfoBuilder_ != null ? this.keyInfoBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList(this.keyInfo_);
      }

      public KeyInfo.Builder addKeyInfoBuilder() {
         return (KeyInfo.Builder)this.getKeyInfoFieldBuilder().addBuilder(KeysetInfo.KeyInfo.getDefaultInstance());
      }

      public KeyInfo.Builder addKeyInfoBuilder(int index) {
         return (KeyInfo.Builder)this.getKeyInfoFieldBuilder().addBuilder(index, KeysetInfo.KeyInfo.getDefaultInstance());
      }

      public List getKeyInfoBuilderList() {
         return this.getKeyInfoFieldBuilder().getBuilderList();
      }

      private RepeatedFieldBuilder getKeyInfoFieldBuilder() {
         if (this.keyInfoBuilder_ == null) {
            this.keyInfoBuilder_ = new RepeatedFieldBuilder(this.keyInfo_, (this.bitField0_ & 2) != 0, this.getParentForChildren(), this.isClean());
            this.keyInfo_ = null;
         }

         return this.keyInfoBuilder_;
      }
   }

   public interface KeyInfoOrBuilder extends MessageOrBuilder {
      String getTypeUrl();

      ByteString getTypeUrlBytes();

      int getStatusValue();

      KeyStatusType getStatus();

      int getKeyId();

      int getOutputPrefixTypeValue();

      OutputPrefixType getOutputPrefixType();
   }
}
