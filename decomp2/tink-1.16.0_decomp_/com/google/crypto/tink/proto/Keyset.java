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
import com.google.protobuf.SingleFieldBuilder;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.RuntimeVersion.RuntimeDomain;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Keyset extends GeneratedMessage implements KeysetOrBuilder {
   private static final long serialVersionUID = 0L;
   public static final int PRIMARY_KEY_ID_FIELD_NUMBER = 1;
   private int primaryKeyId_;
   public static final int KEY_FIELD_NUMBER = 2;
   private List key_;
   private byte memoizedIsInitialized;
   private static final Keyset DEFAULT_INSTANCE;
   private static final Parser PARSER;

   private Keyset(GeneratedMessage.Builder builder) {
      super(builder);
      this.primaryKeyId_ = 0;
      this.memoizedIsInitialized = -1;
   }

   private Keyset() {
      this.primaryKeyId_ = 0;
      this.memoizedIsInitialized = -1;
      this.key_ = Collections.emptyList();
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return Tink.internal_static_google_crypto_tink_Keyset_descriptor;
   }

   protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
      return Tink.internal_static_google_crypto_tink_Keyset_fieldAccessorTable.ensureFieldAccessorsInitialized(Keyset.class, Builder.class);
   }

   public int getPrimaryKeyId() {
      return this.primaryKeyId_;
   }

   public List getKeyList() {
      return this.key_;
   }

   public List getKeyOrBuilderList() {
      return this.key_;
   }

   public int getKeyCount() {
      return this.key_.size();
   }

   public Key getKey(int index) {
      return (Key)this.key_.get(index);
   }

   public KeyOrBuilder getKeyOrBuilder(int index) {
      return (KeyOrBuilder)this.key_.get(index);
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

      for(int i = 0; i < this.key_.size(); ++i) {
         output.writeMessage(2, (MessageLite)this.key_.get(i));
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

         for(int i = 0; i < this.key_.size(); ++i) {
            size += CodedOutputStream.computeMessageSize(2, (MessageLite)this.key_.get(i));
         }

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof Keyset)) {
         return super.equals(obj);
      } else {
         Keyset other = (Keyset)obj;
         if (this.getPrimaryKeyId() != other.getPrimaryKeyId()) {
            return false;
         } else if (!this.getKeyList().equals(other.getKeyList())) {
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
         if (this.getKeyCount() > 0) {
            hash = 37 * hash + 2;
            hash = 53 * hash + this.getKeyList().hashCode();
         }

         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static Keyset parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (Keyset)PARSER.parseFrom(data);
   }

   public static Keyset parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Keyset)PARSER.parseFrom(data, extensionRegistry);
   }

   public static Keyset parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (Keyset)PARSER.parseFrom(data);
   }

   public static Keyset parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Keyset)PARSER.parseFrom(data, extensionRegistry);
   }

   public static Keyset parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (Keyset)PARSER.parseFrom(data);
   }

   public static Keyset parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Keyset)PARSER.parseFrom(data, extensionRegistry);
   }

   public static Keyset parseFrom(InputStream input) throws IOException {
      return (Keyset)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static Keyset parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (Keyset)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static Keyset parseDelimitedFrom(InputStream input) throws IOException {
      return (Keyset)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
   }

   public static Keyset parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (Keyset)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static Keyset parseFrom(CodedInputStream input) throws IOException {
      return (Keyset)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static Keyset parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (Keyset)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(Keyset prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static Keyset getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public Keyset getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", Keyset.class.getName());
      DEFAULT_INSTANCE = new Keyset();
      PARSER = new AbstractParser() {
         public Keyset parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            Builder builder = Keyset.newBuilder();

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

   public static final class Key extends GeneratedMessage implements KeyOrBuilder {
      private static final long serialVersionUID = 0L;
      private int bitField0_;
      public static final int KEY_DATA_FIELD_NUMBER = 1;
      private KeyData keyData_;
      public static final int STATUS_FIELD_NUMBER = 2;
      private int status_;
      public static final int KEY_ID_FIELD_NUMBER = 3;
      private int keyId_;
      public static final int OUTPUT_PREFIX_TYPE_FIELD_NUMBER = 4;
      private int outputPrefixType_;
      private byte memoizedIsInitialized;
      private static final Key DEFAULT_INSTANCE;
      private static final Parser PARSER;

      private Key(GeneratedMessage.Builder builder) {
         super(builder);
         this.status_ = 0;
         this.keyId_ = 0;
         this.outputPrefixType_ = 0;
         this.memoizedIsInitialized = -1;
      }

      private Key() {
         this.status_ = 0;
         this.keyId_ = 0;
         this.outputPrefixType_ = 0;
         this.memoizedIsInitialized = -1;
         this.status_ = 0;
         this.outputPrefixType_ = 0;
      }

      public static final Descriptors.Descriptor getDescriptor() {
         return Tink.internal_static_google_crypto_tink_Keyset_Key_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return Tink.internal_static_google_crypto_tink_Keyset_Key_fieldAccessorTable.ensureFieldAccessorsInitialized(Key.class, Builder.class);
      }

      public boolean hasKeyData() {
         return (this.bitField0_ & 1) != 0;
      }

      public KeyData getKeyData() {
         return this.keyData_ == null ? KeyData.getDefaultInstance() : this.keyData_;
      }

      public KeyDataOrBuilder getKeyDataOrBuilder() {
         return this.keyData_ == null ? KeyData.getDefaultInstance() : this.keyData_;
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
         if ((this.bitField0_ & 1) != 0) {
            output.writeMessage(1, this.getKeyData());
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
            if ((this.bitField0_ & 1) != 0) {
               size += CodedOutputStream.computeMessageSize(1, this.getKeyData());
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
         } else if (!(obj instanceof Key)) {
            return super.equals(obj);
         } else {
            Key other = (Key)obj;
            if (this.hasKeyData() != other.hasKeyData()) {
               return false;
            } else if (this.hasKeyData() && !this.getKeyData().equals(other.getKeyData())) {
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
            if (this.hasKeyData()) {
               hash = 37 * hash + 1;
               hash = 53 * hash + this.getKeyData().hashCode();
            }

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

      public static Key parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
         return (Key)PARSER.parseFrom(data);
      }

      public static Key parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
         return (Key)PARSER.parseFrom(data, extensionRegistry);
      }

      public static Key parseFrom(ByteString data) throws InvalidProtocolBufferException {
         return (Key)PARSER.parseFrom(data);
      }

      public static Key parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
         return (Key)PARSER.parseFrom(data, extensionRegistry);
      }

      public static Key parseFrom(byte[] data) throws InvalidProtocolBufferException {
         return (Key)PARSER.parseFrom(data);
      }

      public static Key parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
         return (Key)PARSER.parseFrom(data, extensionRegistry);
      }

      public static Key parseFrom(InputStream input) throws IOException {
         return (Key)GeneratedMessage.parseWithIOException(PARSER, input);
      }

      public static Key parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
         return (Key)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
      }

      public static Key parseDelimitedFrom(InputStream input) throws IOException {
         return (Key)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
      }

      public static Key parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
         return (Key)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
      }

      public static Key parseFrom(CodedInputStream input) throws IOException {
         return (Key)GeneratedMessage.parseWithIOException(PARSER, input);
      }

      public static Key parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
         return (Key)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
      }

      public Builder newBuilderForType() {
         return newBuilder();
      }

      public static Builder newBuilder() {
         return DEFAULT_INSTANCE.toBuilder();
      }

      public static Builder newBuilder(Key prototype) {
         return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
      }

      public Builder toBuilder() {
         return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
      }

      protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
         Builder builder = new Builder(parent);
         return builder;
      }

      public static Key getDefaultInstance() {
         return DEFAULT_INSTANCE;
      }

      public static Parser parser() {
         return PARSER;
      }

      public Parser getParserForType() {
         return PARSER;
      }

      public Key getDefaultInstanceForType() {
         return DEFAULT_INSTANCE;
      }

      static {
         RuntimeVersion.validateProtobufGencodeVersion(RuntimeDomain.PUBLIC, 4, 28, 2, "", Key.class.getName());
         DEFAULT_INSTANCE = new Key();
         PARSER = new AbstractParser() {
            public Key parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
               Builder builder = Keyset.Key.newBuilder();

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

      public static final class Builder extends GeneratedMessage.Builder implements KeyOrBuilder {
         private int bitField0_;
         private KeyData keyData_;
         private SingleFieldBuilder keyDataBuilder_;
         private int status_;
         private int keyId_;
         private int outputPrefixType_;

         public static final Descriptors.Descriptor getDescriptor() {
            return Tink.internal_static_google_crypto_tink_Keyset_Key_descriptor;
         }

         protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Tink.internal_static_google_crypto_tink_Keyset_Key_fieldAccessorTable.ensureFieldAccessorsInitialized(Key.class, Builder.class);
         }

         private Builder() {
            this.status_ = 0;
            this.outputPrefixType_ = 0;
            this.maybeForceBuilderInitialization();
         }

         private Builder(AbstractMessage.BuilderParent parent) {
            super(parent);
            this.status_ = 0;
            this.outputPrefixType_ = 0;
            this.maybeForceBuilderInitialization();
         }

         private void maybeForceBuilderInitialization() {
            if (Keyset.Key.alwaysUseFieldBuilders) {
               this.getKeyDataFieldBuilder();
            }

         }

         public Builder clear() {
            super.clear();
            this.bitField0_ = 0;
            this.keyData_ = null;
            if (this.keyDataBuilder_ != null) {
               this.keyDataBuilder_.dispose();
               this.keyDataBuilder_ = null;
            }

            this.status_ = 0;
            this.keyId_ = 0;
            this.outputPrefixType_ = 0;
            return this;
         }

         public Descriptors.Descriptor getDescriptorForType() {
            return Tink.internal_static_google_crypto_tink_Keyset_Key_descriptor;
         }

         public Key getDefaultInstanceForType() {
            return Keyset.Key.getDefaultInstance();
         }

         public Key build() {
            Key result = this.buildPartial();
            if (!result.isInitialized()) {
               throw newUninitializedMessageException(result);
            } else {
               return result;
            }
         }

         public Key buildPartial() {
            Key result = new Key(this);
            if (this.bitField0_ != 0) {
               this.buildPartial0(result);
            }

            this.onBuilt();
            return result;
         }

         private void buildPartial0(Key result) {
            int from_bitField0_ = this.bitField0_;
            int to_bitField0_ = 0;
            if ((from_bitField0_ & 1) != 0) {
               result.keyData_ = this.keyDataBuilder_ == null ? this.keyData_ : (KeyData)this.keyDataBuilder_.build();
               to_bitField0_ |= 1;
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

            result.bitField0_ = to_bitField0_;
         }

         public Builder mergeFrom(Message other) {
            if (other instanceof Key) {
               return this.mergeFrom((Key)other);
            } else {
               super.mergeFrom(other);
               return this;
            }
         }

         public Builder mergeFrom(Key other) {
            if (other == Keyset.Key.getDefaultInstance()) {
               return this;
            } else {
               if (other.hasKeyData()) {
                  this.mergeKeyData(other.getKeyData());
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
                           input.readMessage(this.getKeyDataFieldBuilder().getBuilder(), extensionRegistry);
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

         public boolean hasKeyData() {
            return (this.bitField0_ & 1) != 0;
         }

         public KeyData getKeyData() {
            if (this.keyDataBuilder_ == null) {
               return this.keyData_ == null ? KeyData.getDefaultInstance() : this.keyData_;
            } else {
               return (KeyData)this.keyDataBuilder_.getMessage();
            }
         }

         public Builder setKeyData(KeyData value) {
            if (this.keyDataBuilder_ == null) {
               if (value == null) {
                  throw new NullPointerException();
               }

               this.keyData_ = value;
            } else {
               this.keyDataBuilder_.setMessage(value);
            }

            this.bitField0_ |= 1;
            this.onChanged();
            return this;
         }

         public Builder setKeyData(KeyData.Builder builderForValue) {
            if (this.keyDataBuilder_ == null) {
               this.keyData_ = builderForValue.build();
            } else {
               this.keyDataBuilder_.setMessage(builderForValue.build());
            }

            this.bitField0_ |= 1;
            this.onChanged();
            return this;
         }

         public Builder mergeKeyData(KeyData value) {
            if (this.keyDataBuilder_ == null) {
               if ((this.bitField0_ & 1) != 0 && this.keyData_ != null && this.keyData_ != KeyData.getDefaultInstance()) {
                  this.getKeyDataBuilder().mergeFrom(value);
               } else {
                  this.keyData_ = value;
               }
            } else {
               this.keyDataBuilder_.mergeFrom(value);
            }

            if (this.keyData_ != null) {
               this.bitField0_ |= 1;
               this.onChanged();
            }

            return this;
         }

         public Builder clearKeyData() {
            this.bitField0_ &= -2;
            this.keyData_ = null;
            if (this.keyDataBuilder_ != null) {
               this.keyDataBuilder_.dispose();
               this.keyDataBuilder_ = null;
            }

            this.onChanged();
            return this;
         }

         public KeyData.Builder getKeyDataBuilder() {
            this.bitField0_ |= 1;
            this.onChanged();
            return (KeyData.Builder)this.getKeyDataFieldBuilder().getBuilder();
         }

         public KeyDataOrBuilder getKeyDataOrBuilder() {
            if (this.keyDataBuilder_ != null) {
               return (KeyDataOrBuilder)this.keyDataBuilder_.getMessageOrBuilder();
            } else {
               return this.keyData_ == null ? KeyData.getDefaultInstance() : this.keyData_;
            }
         }

         private SingleFieldBuilder getKeyDataFieldBuilder() {
            if (this.keyDataBuilder_ == null) {
               this.keyDataBuilder_ = new SingleFieldBuilder(this.getKeyData(), this.getParentForChildren(), this.isClean());
               this.keyData_ = null;
            }

            return this.keyDataBuilder_;
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

   public static final class Builder extends GeneratedMessage.Builder implements KeysetOrBuilder {
      private int bitField0_;
      private int primaryKeyId_;
      private List key_;
      private RepeatedFieldBuilder keyBuilder_;

      public static final Descriptors.Descriptor getDescriptor() {
         return Tink.internal_static_google_crypto_tink_Keyset_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return Tink.internal_static_google_crypto_tink_Keyset_fieldAccessorTable.ensureFieldAccessorsInitialized(Keyset.class, Builder.class);
      }

      private Builder() {
         this.key_ = Collections.emptyList();
      }

      private Builder(AbstractMessage.BuilderParent parent) {
         super(parent);
         this.key_ = Collections.emptyList();
      }

      public Builder clear() {
         super.clear();
         this.bitField0_ = 0;
         this.primaryKeyId_ = 0;
         if (this.keyBuilder_ == null) {
            this.key_ = Collections.emptyList();
         } else {
            this.key_ = null;
            this.keyBuilder_.clear();
         }

         this.bitField0_ &= -3;
         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return Tink.internal_static_google_crypto_tink_Keyset_descriptor;
      }

      public Keyset getDefaultInstanceForType() {
         return Keyset.getDefaultInstance();
      }

      public Keyset build() {
         Keyset result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public Keyset buildPartial() {
         Keyset result = new Keyset(this);
         this.buildPartialRepeatedFields(result);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartialRepeatedFields(Keyset result) {
         if (this.keyBuilder_ == null) {
            if ((this.bitField0_ & 2) != 0) {
               this.key_ = Collections.unmodifiableList(this.key_);
               this.bitField0_ &= -3;
            }

            result.key_ = this.key_;
         } else {
            result.key_ = this.keyBuilder_.build();
         }

      }

      private void buildPartial0(Keyset result) {
         int from_bitField0_ = this.bitField0_;
         if ((from_bitField0_ & 1) != 0) {
            result.primaryKeyId_ = this.primaryKeyId_;
         }

      }

      public Builder mergeFrom(Message other) {
         if (other instanceof Keyset) {
            return this.mergeFrom((Keyset)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(Keyset other) {
         if (other == Keyset.getDefaultInstance()) {
            return this;
         } else {
            if (other.getPrimaryKeyId() != 0) {
               this.setPrimaryKeyId(other.getPrimaryKeyId());
            }

            if (this.keyBuilder_ == null) {
               if (!other.key_.isEmpty()) {
                  if (this.key_.isEmpty()) {
                     this.key_ = other.key_;
                     this.bitField0_ &= -3;
                  } else {
                     this.ensureKeyIsMutable();
                     this.key_.addAll(other.key_);
                  }

                  this.onChanged();
               }
            } else if (!other.key_.isEmpty()) {
               if (this.keyBuilder_.isEmpty()) {
                  this.keyBuilder_.dispose();
                  this.keyBuilder_ = null;
                  this.key_ = other.key_;
                  this.bitField0_ &= -3;
                  this.keyBuilder_ = Keyset.alwaysUseFieldBuilders ? this.getKeyFieldBuilder() : null;
               } else {
                  this.keyBuilder_.addAllMessages(other.key_);
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
                        Key m = (Key)input.readMessage(Keyset.Key.parser(), extensionRegistry);
                        if (this.keyBuilder_ == null) {
                           this.ensureKeyIsMutable();
                           this.key_.add(m);
                        } else {
                           this.keyBuilder_.addMessage(m);
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

      private void ensureKeyIsMutable() {
         if ((this.bitField0_ & 2) == 0) {
            this.key_ = new ArrayList(this.key_);
            this.bitField0_ |= 2;
         }

      }

      public List getKeyList() {
         return this.keyBuilder_ == null ? Collections.unmodifiableList(this.key_) : this.keyBuilder_.getMessageList();
      }

      public int getKeyCount() {
         return this.keyBuilder_ == null ? this.key_.size() : this.keyBuilder_.getCount();
      }

      public Key getKey(int index) {
         return this.keyBuilder_ == null ? (Key)this.key_.get(index) : (Key)this.keyBuilder_.getMessage(index);
      }

      public Builder setKey(int index, Key value) {
         if (this.keyBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.ensureKeyIsMutable();
            this.key_.set(index, value);
            this.onChanged();
         } else {
            this.keyBuilder_.setMessage(index, value);
         }

         return this;
      }

      public Builder setKey(int index, Key.Builder builderForValue) {
         if (this.keyBuilder_ == null) {
            this.ensureKeyIsMutable();
            this.key_.set(index, builderForValue.build());
            this.onChanged();
         } else {
            this.keyBuilder_.setMessage(index, builderForValue.build());
         }

         return this;
      }

      public Builder addKey(Key value) {
         if (this.keyBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.ensureKeyIsMutable();
            this.key_.add(value);
            this.onChanged();
         } else {
            this.keyBuilder_.addMessage(value);
         }

         return this;
      }

      public Builder addKey(int index, Key value) {
         if (this.keyBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.ensureKeyIsMutable();
            this.key_.add(index, value);
            this.onChanged();
         } else {
            this.keyBuilder_.addMessage(index, value);
         }

         return this;
      }

      public Builder addKey(Key.Builder builderForValue) {
         if (this.keyBuilder_ == null) {
            this.ensureKeyIsMutable();
            this.key_.add(builderForValue.build());
            this.onChanged();
         } else {
            this.keyBuilder_.addMessage(builderForValue.build());
         }

         return this;
      }

      public Builder addKey(int index, Key.Builder builderForValue) {
         if (this.keyBuilder_ == null) {
            this.ensureKeyIsMutable();
            this.key_.add(index, builderForValue.build());
            this.onChanged();
         } else {
            this.keyBuilder_.addMessage(index, builderForValue.build());
         }

         return this;
      }

      public Builder addAllKey(Iterable values) {
         if (this.keyBuilder_ == null) {
            this.ensureKeyIsMutable();
            com.google.protobuf.AbstractMessageLite.Builder.addAll(values, this.key_);
            this.onChanged();
         } else {
            this.keyBuilder_.addAllMessages(values);
         }

         return this;
      }

      public Builder clearKey() {
         if (this.keyBuilder_ == null) {
            this.key_ = Collections.emptyList();
            this.bitField0_ &= -3;
            this.onChanged();
         } else {
            this.keyBuilder_.clear();
         }

         return this;
      }

      public Builder removeKey(int index) {
         if (this.keyBuilder_ == null) {
            this.ensureKeyIsMutable();
            this.key_.remove(index);
            this.onChanged();
         } else {
            this.keyBuilder_.remove(index);
         }

         return this;
      }

      public Key.Builder getKeyBuilder(int index) {
         return (Key.Builder)this.getKeyFieldBuilder().getBuilder(index);
      }

      public KeyOrBuilder getKeyOrBuilder(int index) {
         return this.keyBuilder_ == null ? (KeyOrBuilder)this.key_.get(index) : (KeyOrBuilder)this.keyBuilder_.getMessageOrBuilder(index);
      }

      public List getKeyOrBuilderList() {
         return this.keyBuilder_ != null ? this.keyBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList(this.key_);
      }

      public Key.Builder addKeyBuilder() {
         return (Key.Builder)this.getKeyFieldBuilder().addBuilder(Keyset.Key.getDefaultInstance());
      }

      public Key.Builder addKeyBuilder(int index) {
         return (Key.Builder)this.getKeyFieldBuilder().addBuilder(index, Keyset.Key.getDefaultInstance());
      }

      public List getKeyBuilderList() {
         return this.getKeyFieldBuilder().getBuilderList();
      }

      private RepeatedFieldBuilder getKeyFieldBuilder() {
         if (this.keyBuilder_ == null) {
            this.keyBuilder_ = new RepeatedFieldBuilder(this.key_, (this.bitField0_ & 2) != 0, this.getParentForChildren(), this.isClean());
            this.key_ = null;
         }

         return this.keyBuilder_;
      }
   }

   public interface KeyOrBuilder extends MessageOrBuilder {
      boolean hasKeyData();

      KeyData getKeyData();

      KeyDataOrBuilder getKeyDataOrBuilder();

      int getStatusValue();

      KeyStatusType getStatus();

      int getKeyId();

      int getOutputPrefixTypeValue();

      OutputPrefixType getOutputPrefixType();
   }
}
