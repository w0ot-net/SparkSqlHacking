package org.apache.orc.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class Any extends GeneratedMessageV3 implements AnyOrBuilder {
   private static final long serialVersionUID = 0L;
   private volatile Message cachedUnpackValue;
   public static final int TYPE_URL_FIELD_NUMBER = 1;
   private volatile Object typeUrl_;
   public static final int VALUE_FIELD_NUMBER = 2;
   private ByteString value_;
   private byte memoizedIsInitialized;
   private static final Any DEFAULT_INSTANCE = new Any();
   private static final Parser PARSER = new AbstractParser() {
      public Any parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
         Builder builder = Any.newBuilder();

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

   private Any(GeneratedMessageV3.Builder builder) {
      super(builder);
      this.typeUrl_ = "";
      this.value_ = ByteString.EMPTY;
      this.memoizedIsInitialized = -1;
   }

   private Any() {
      this.typeUrl_ = "";
      this.value_ = ByteString.EMPTY;
      this.memoizedIsInitialized = -1;
      this.typeUrl_ = "";
      this.value_ = ByteString.EMPTY;
   }

   protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new Any();
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return AnyProto.internal_static_google_protobuf_Any_descriptor;
   }

   protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return AnyProto.internal_static_google_protobuf_Any_fieldAccessorTable.ensureFieldAccessorsInitialized(Any.class, Builder.class);
   }

   private static String getTypeUrl(String typeUrlPrefix, Descriptors.Descriptor descriptor) {
      return typeUrlPrefix.endsWith("/") ? typeUrlPrefix + descriptor.getFullName() : typeUrlPrefix + "/" + descriptor.getFullName();
   }

   private static String getTypeNameFromTypeUrl(String typeUrl) {
      int pos = typeUrl.lastIndexOf(47);
      return pos == -1 ? "" : typeUrl.substring(pos + 1);
   }

   public static Any pack(Message message) {
      return newBuilder().setTypeUrl(getTypeUrl("type.googleapis.com", message.getDescriptorForType())).setValue(message.toByteString()).build();
   }

   public static Any pack(Message message, String typeUrlPrefix) {
      return newBuilder().setTypeUrl(getTypeUrl(typeUrlPrefix, message.getDescriptorForType())).setValue(message.toByteString()).build();
   }

   public boolean is(Class clazz) {
      T defaultInstance = (T)((Message)Internal.getDefaultInstance(clazz));
      return getTypeNameFromTypeUrl(this.getTypeUrl()).equals(defaultInstance.getDescriptorForType().getFullName());
   }

   public boolean isSameTypeAs(Message message) {
      return getTypeNameFromTypeUrl(this.getTypeUrl()).equals(message.getDescriptorForType().getFullName());
   }

   public Message unpack(Class clazz) throws InvalidProtocolBufferException {
      boolean invalidClazz = false;
      if (this.cachedUnpackValue != null) {
         if (this.cachedUnpackValue.getClass() == clazz) {
            return this.cachedUnpackValue;
         }

         invalidClazz = true;
      }

      if (!invalidClazz && this.is(clazz)) {
         T defaultInstance = (T)((Message)Internal.getDefaultInstance(clazz));
         T result = (T)((Message)defaultInstance.getParserForType().parseFrom(this.getValue()));
         this.cachedUnpackValue = result;
         return result;
      } else {
         throw new InvalidProtocolBufferException("Type of the Any message does not match the given class.");
      }
   }

   public Message unpackSameTypeAs(Message message) throws InvalidProtocolBufferException {
      boolean invalidValue = false;
      if (this.cachedUnpackValue != null) {
         if (this.cachedUnpackValue.getClass() == message.getClass()) {
            return this.cachedUnpackValue;
         }

         invalidValue = true;
      }

      if (!invalidValue && this.isSameTypeAs(message)) {
         T result = (T)((Message)message.getParserForType().parseFrom(this.getValue()));
         this.cachedUnpackValue = result;
         return result;
      } else {
         throw new InvalidProtocolBufferException("Type of the Any message does not match the given exemplar.");
      }
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

   public ByteString getValue() {
      return this.value_;
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
      if (!GeneratedMessageV3.isStringEmpty(this.typeUrl_)) {
         GeneratedMessageV3.writeString(output, 1, this.typeUrl_);
      }

      if (!this.value_.isEmpty()) {
         output.writeBytes(2, this.value_);
      }

      this.getUnknownFields().writeTo(output);
   }

   public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1) {
         return size;
      } else {
         size = 0;
         if (!GeneratedMessageV3.isStringEmpty(this.typeUrl_)) {
            size += GeneratedMessageV3.computeStringSize(1, this.typeUrl_);
         }

         if (!this.value_.isEmpty()) {
            size += CodedOutputStream.computeBytesSize(2, this.value_);
         }

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof Any)) {
         return super.equals(obj);
      } else {
         Any other = (Any)obj;
         if (!this.getTypeUrl().equals(other.getTypeUrl())) {
            return false;
         } else if (!this.getValue().equals(other.getValue())) {
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
         hash = 53 * hash + this.getValue().hashCode();
         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static Any parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (Any)PARSER.parseFrom(data);
   }

   public static Any parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Any)PARSER.parseFrom(data, extensionRegistry);
   }

   public static Any parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (Any)PARSER.parseFrom(data);
   }

   public static Any parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Any)PARSER.parseFrom(data, extensionRegistry);
   }

   public static Any parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (Any)PARSER.parseFrom(data);
   }

   public static Any parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Any)PARSER.parseFrom(data, extensionRegistry);
   }

   public static Any parseFrom(InputStream input) throws IOException {
      return (Any)GeneratedMessageV3.parseWithIOException(PARSER, input);
   }

   public static Any parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (Any)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static Any parseDelimitedFrom(InputStream input) throws IOException {
      return (Any)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
   }

   public static Any parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (Any)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static Any parseFrom(CodedInputStream input) throws IOException {
      return (Any)GeneratedMessageV3.parseWithIOException(PARSER, input);
   }

   public static Any parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (Any)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(Any prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static Any getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public Any getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   public static final class Builder extends GeneratedMessageV3.Builder implements AnyOrBuilder {
      private int bitField0_;
      private Object typeUrl_;
      private ByteString value_;

      public static final Descriptors.Descriptor getDescriptor() {
         return AnyProto.internal_static_google_protobuf_Any_descriptor;
      }

      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
         return AnyProto.internal_static_google_protobuf_Any_fieldAccessorTable.ensureFieldAccessorsInitialized(Any.class, Builder.class);
      }

      private Builder() {
         this.typeUrl_ = "";
         this.value_ = ByteString.EMPTY;
      }

      private Builder(GeneratedMessageV3.BuilderParent parent) {
         super(parent);
         this.typeUrl_ = "";
         this.value_ = ByteString.EMPTY;
      }

      public Builder clear() {
         super.clear();
         this.bitField0_ = 0;
         this.typeUrl_ = "";
         this.value_ = ByteString.EMPTY;
         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return AnyProto.internal_static_google_protobuf_Any_descriptor;
      }

      public Any getDefaultInstanceForType() {
         return Any.getDefaultInstance();
      }

      public Any build() {
         Any result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public Any buildPartial() {
         Any result = new Any(this);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartial0(Any result) {
         int from_bitField0_ = this.bitField0_;
         if ((from_bitField0_ & 1) != 0) {
            result.typeUrl_ = this.typeUrl_;
         }

         if ((from_bitField0_ & 2) != 0) {
            result.value_ = this.value_;
         }

      }

      public Builder clone() {
         return (Builder)super.clone();
      }

      public Builder setField(Descriptors.FieldDescriptor field, Object value) {
         return (Builder)super.setField(field, value);
      }

      public Builder clearField(Descriptors.FieldDescriptor field) {
         return (Builder)super.clearField(field);
      }

      public Builder clearOneof(Descriptors.OneofDescriptor oneof) {
         return (Builder)super.clearOneof(oneof);
      }

      public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
         return (Builder)super.setRepeatedField(field, index, value);
      }

      public Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
         return (Builder)super.addRepeatedField(field, value);
      }

      public Builder mergeFrom(Message other) {
         if (other instanceof Any) {
            return this.mergeFrom((Any)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(Any other) {
         if (other == Any.getDefaultInstance()) {
            return this;
         } else {
            if (!other.getTypeUrl().isEmpty()) {
               this.typeUrl_ = other.typeUrl_;
               this.bitField0_ |= 1;
               this.onChanged();
            }

            if (other.getValue() != ByteString.EMPTY) {
               this.setValue(other.getValue());
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
                     case 18:
                        this.value_ = input.readBytes();
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
         this.typeUrl_ = Any.getDefaultInstance().getTypeUrl();
         this.bitField0_ &= -2;
         this.onChanged();
         return this;
      }

      public Builder setTypeUrlBytes(ByteString value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            AbstractMessageLite.checkByteStringIsUtf8(value);
            this.typeUrl_ = value;
            this.bitField0_ |= 1;
            this.onChanged();
            return this;
         }
      }

      public ByteString getValue() {
         return this.value_;
      }

      public Builder setValue(ByteString value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.value_ = value;
            this.bitField0_ |= 2;
            this.onChanged();
            return this;
         }
      }

      public Builder clearValue() {
         this.bitField0_ &= -3;
         this.value_ = Any.getDefaultInstance().getValue();
         this.onChanged();
         return this;
      }

      public final Builder setUnknownFields(final UnknownFieldSet unknownFields) {
         return (Builder)super.setUnknownFields(unknownFields);
      }

      public final Builder mergeUnknownFields(final UnknownFieldSet unknownFields) {
         return (Builder)super.mergeUnknownFields(unknownFields);
      }
   }
}
