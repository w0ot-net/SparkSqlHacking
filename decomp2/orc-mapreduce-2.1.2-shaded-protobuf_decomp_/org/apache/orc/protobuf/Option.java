package org.apache.orc.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class Option extends GeneratedMessageV3 implements OptionOrBuilder {
   private static final long serialVersionUID = 0L;
   private int bitField0_;
   public static final int NAME_FIELD_NUMBER = 1;
   private volatile Object name_;
   public static final int VALUE_FIELD_NUMBER = 2;
   private Any value_;
   private byte memoizedIsInitialized;
   private static final Option DEFAULT_INSTANCE = new Option();
   private static final Parser PARSER = new AbstractParser() {
      public Option parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
         Builder builder = Option.newBuilder();

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

   private Option(GeneratedMessageV3.Builder builder) {
      super(builder);
      this.name_ = "";
      this.memoizedIsInitialized = -1;
   }

   private Option() {
      this.name_ = "";
      this.memoizedIsInitialized = -1;
      this.name_ = "";
   }

   protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new Option();
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return TypeProto.internal_static_google_protobuf_Option_descriptor;
   }

   protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return TypeProto.internal_static_google_protobuf_Option_fieldAccessorTable.ensureFieldAccessorsInitialized(Option.class, Builder.class);
   }

   public String getName() {
      Object ref = this.name_;
      if (ref instanceof String) {
         return (String)ref;
      } else {
         ByteString bs = (ByteString)ref;
         String s = bs.toStringUtf8();
         this.name_ = s;
         return s;
      }
   }

   public ByteString getNameBytes() {
      Object ref = this.name_;
      if (ref instanceof String) {
         ByteString b = ByteString.copyFromUtf8((String)ref);
         this.name_ = b;
         return b;
      } else {
         return (ByteString)ref;
      }
   }

   public boolean hasValue() {
      return (this.bitField0_ & 1) != 0;
   }

   public Any getValue() {
      return this.value_ == null ? Any.getDefaultInstance() : this.value_;
   }

   public AnyOrBuilder getValueOrBuilder() {
      return this.value_ == null ? Any.getDefaultInstance() : this.value_;
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
      if (!GeneratedMessageV3.isStringEmpty(this.name_)) {
         GeneratedMessageV3.writeString(output, 1, this.name_);
      }

      if ((this.bitField0_ & 1) != 0) {
         output.writeMessage(2, this.getValue());
      }

      this.getUnknownFields().writeTo(output);
   }

   public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1) {
         return size;
      } else {
         size = 0;
         if (!GeneratedMessageV3.isStringEmpty(this.name_)) {
            size += GeneratedMessageV3.computeStringSize(1, this.name_);
         }

         if ((this.bitField0_ & 1) != 0) {
            size += CodedOutputStream.computeMessageSize(2, this.getValue());
         }

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof Option)) {
         return super.equals(obj);
      } else {
         Option other = (Option)obj;
         if (!this.getName().equals(other.getName())) {
            return false;
         } else if (this.hasValue() != other.hasValue()) {
            return false;
         } else if (this.hasValue() && !this.getValue().equals(other.getValue())) {
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
         hash = 53 * hash + this.getName().hashCode();
         if (this.hasValue()) {
            hash = 37 * hash + 2;
            hash = 53 * hash + this.getValue().hashCode();
         }

         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static Option parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (Option)PARSER.parseFrom(data);
   }

   public static Option parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Option)PARSER.parseFrom(data, extensionRegistry);
   }

   public static Option parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (Option)PARSER.parseFrom(data);
   }

   public static Option parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Option)PARSER.parseFrom(data, extensionRegistry);
   }

   public static Option parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (Option)PARSER.parseFrom(data);
   }

   public static Option parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Option)PARSER.parseFrom(data, extensionRegistry);
   }

   public static Option parseFrom(InputStream input) throws IOException {
      return (Option)GeneratedMessageV3.parseWithIOException(PARSER, input);
   }

   public static Option parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (Option)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static Option parseDelimitedFrom(InputStream input) throws IOException {
      return (Option)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
   }

   public static Option parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (Option)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static Option parseFrom(CodedInputStream input) throws IOException {
      return (Option)GeneratedMessageV3.parseWithIOException(PARSER, input);
   }

   public static Option parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (Option)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(Option prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static Option getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public Option getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   public static final class Builder extends GeneratedMessageV3.Builder implements OptionOrBuilder {
      private int bitField0_;
      private Object name_;
      private Any value_;
      private SingleFieldBuilderV3 valueBuilder_;

      public static final Descriptors.Descriptor getDescriptor() {
         return TypeProto.internal_static_google_protobuf_Option_descriptor;
      }

      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
         return TypeProto.internal_static_google_protobuf_Option_fieldAccessorTable.ensureFieldAccessorsInitialized(Option.class, Builder.class);
      }

      private Builder() {
         this.name_ = "";
         this.maybeForceBuilderInitialization();
      }

      private Builder(GeneratedMessageV3.BuilderParent parent) {
         super(parent);
         this.name_ = "";
         this.maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization() {
         if (GeneratedMessageV3.alwaysUseFieldBuilders) {
            this.getValueFieldBuilder();
         }

      }

      public Builder clear() {
         super.clear();
         this.bitField0_ = 0;
         this.name_ = "";
         this.value_ = null;
         if (this.valueBuilder_ != null) {
            this.valueBuilder_.dispose();
            this.valueBuilder_ = null;
         }

         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return TypeProto.internal_static_google_protobuf_Option_descriptor;
      }

      public Option getDefaultInstanceForType() {
         return Option.getDefaultInstance();
      }

      public Option build() {
         Option result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public Option buildPartial() {
         Option result = new Option(this);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartial0(Option result) {
         int from_bitField0_ = this.bitField0_;
         if ((from_bitField0_ & 1) != 0) {
            result.name_ = this.name_;
         }

         int to_bitField0_ = 0;
         if ((from_bitField0_ & 2) != 0) {
            result.value_ = this.valueBuilder_ == null ? this.value_ : (Any)this.valueBuilder_.build();
            to_bitField0_ |= 1;
         }

         result.bitField0_ = to_bitField0_;
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
         if (other instanceof Option) {
            return this.mergeFrom((Option)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(Option other) {
         if (other == Option.getDefaultInstance()) {
            return this;
         } else {
            if (!other.getName().isEmpty()) {
               this.name_ = other.name_;
               this.bitField0_ |= 1;
               this.onChanged();
            }

            if (other.hasValue()) {
               this.mergeValue(other.getValue());
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
                        this.name_ = input.readStringRequireUtf8();
                        this.bitField0_ |= 1;
                        break;
                     case 18:
                        input.readMessage((MessageLite.Builder)this.getValueFieldBuilder().getBuilder(), extensionRegistry);
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

      public String getName() {
         Object ref = this.name_;
         if (!(ref instanceof String)) {
            ByteString bs = (ByteString)ref;
            String s = bs.toStringUtf8();
            this.name_ = s;
            return s;
         } else {
            return (String)ref;
         }
      }

      public ByteString getNameBytes() {
         Object ref = this.name_;
         if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.name_ = b;
            return b;
         } else {
            return (ByteString)ref;
         }
      }

      public Builder setName(String value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.name_ = value;
            this.bitField0_ |= 1;
            this.onChanged();
            return this;
         }
      }

      public Builder clearName() {
         this.name_ = Option.getDefaultInstance().getName();
         this.bitField0_ &= -2;
         this.onChanged();
         return this;
      }

      public Builder setNameBytes(ByteString value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            AbstractMessageLite.checkByteStringIsUtf8(value);
            this.name_ = value;
            this.bitField0_ |= 1;
            this.onChanged();
            return this;
         }
      }

      public boolean hasValue() {
         return (this.bitField0_ & 2) != 0;
      }

      public Any getValue() {
         if (this.valueBuilder_ == null) {
            return this.value_ == null ? Any.getDefaultInstance() : this.value_;
         } else {
            return (Any)this.valueBuilder_.getMessage();
         }
      }

      public Builder setValue(Any value) {
         if (this.valueBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.value_ = value;
         } else {
            this.valueBuilder_.setMessage(value);
         }

         this.bitField0_ |= 2;
         this.onChanged();
         return this;
      }

      public Builder setValue(Any.Builder builderForValue) {
         if (this.valueBuilder_ == null) {
            this.value_ = builderForValue.build();
         } else {
            this.valueBuilder_.setMessage(builderForValue.build());
         }

         this.bitField0_ |= 2;
         this.onChanged();
         return this;
      }

      public Builder mergeValue(Any value) {
         if (this.valueBuilder_ == null) {
            if ((this.bitField0_ & 2) != 0 && this.value_ != null && this.value_ != Any.getDefaultInstance()) {
               this.getValueBuilder().mergeFrom(value);
            } else {
               this.value_ = value;
            }
         } else {
            this.valueBuilder_.mergeFrom(value);
         }

         if (this.value_ != null) {
            this.bitField0_ |= 2;
            this.onChanged();
         }

         return this;
      }

      public Builder clearValue() {
         this.bitField0_ &= -3;
         this.value_ = null;
         if (this.valueBuilder_ != null) {
            this.valueBuilder_.dispose();
            this.valueBuilder_ = null;
         }

         this.onChanged();
         return this;
      }

      public Any.Builder getValueBuilder() {
         this.bitField0_ |= 2;
         this.onChanged();
         return (Any.Builder)this.getValueFieldBuilder().getBuilder();
      }

      public AnyOrBuilder getValueOrBuilder() {
         if (this.valueBuilder_ != null) {
            return (AnyOrBuilder)this.valueBuilder_.getMessageOrBuilder();
         } else {
            return this.value_ == null ? Any.getDefaultInstance() : this.value_;
         }
      }

      private SingleFieldBuilderV3 getValueFieldBuilder() {
         if (this.valueBuilder_ == null) {
            this.valueBuilder_ = new SingleFieldBuilderV3(this.getValue(), this.getParentForChildren(), this.isClean());
            this.value_ = null;
         }

         return this.valueBuilder_;
      }

      public final Builder setUnknownFields(final UnknownFieldSet unknownFields) {
         return (Builder)super.setUnknownFields(unknownFields);
      }

      public final Builder mergeUnknownFields(final UnknownFieldSet unknownFields) {
         return (Builder)super.mergeUnknownFields(unknownFields);
      }
   }
}
