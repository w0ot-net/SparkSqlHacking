package org.sparkproject.spark_core.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ListValue extends GeneratedMessage implements ListValueOrBuilder {
   private static final long serialVersionUID = 0L;
   public static final int VALUES_FIELD_NUMBER = 1;
   private List values_;
   private byte memoizedIsInitialized;
   private static final ListValue DEFAULT_INSTANCE;
   private static final Parser PARSER;

   private ListValue(GeneratedMessage.Builder builder) {
      super(builder);
      this.memoizedIsInitialized = -1;
   }

   private ListValue() {
      this.memoizedIsInitialized = -1;
      this.values_ = Collections.emptyList();
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return StructProto.internal_static_google_protobuf_ListValue_descriptor;
   }

   protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
      return StructProto.internal_static_google_protobuf_ListValue_fieldAccessorTable.ensureFieldAccessorsInitialized(ListValue.class, Builder.class);
   }

   public List getValuesList() {
      return this.values_;
   }

   public List getValuesOrBuilderList() {
      return this.values_;
   }

   public int getValuesCount() {
      return this.values_.size();
   }

   public Value getValues(int index) {
      return (Value)this.values_.get(index);
   }

   public ValueOrBuilder getValuesOrBuilder(int index) {
      return (ValueOrBuilder)this.values_.get(index);
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
      for(int i = 0; i < this.values_.size(); ++i) {
         output.writeMessage(1, (MessageLite)this.values_.get(i));
      }

      this.getUnknownFields().writeTo(output);
   }

   public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1) {
         return size;
      } else {
         size = 0;

         for(int i = 0; i < this.values_.size(); ++i) {
            size += CodedOutputStream.computeMessageSize(1, (MessageLite)this.values_.get(i));
         }

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof ListValue)) {
         return super.equals(obj);
      } else {
         ListValue other = (ListValue)obj;
         if (!this.getValuesList().equals(other.getValuesList())) {
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
         if (this.getValuesCount() > 0) {
            hash = 37 * hash + 1;
            hash = 53 * hash + this.getValuesList().hashCode();
         }

         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static ListValue parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (ListValue)PARSER.parseFrom(data);
   }

   public static ListValue parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (ListValue)PARSER.parseFrom(data, extensionRegistry);
   }

   public static ListValue parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (ListValue)PARSER.parseFrom(data);
   }

   public static ListValue parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (ListValue)PARSER.parseFrom(data, extensionRegistry);
   }

   public static ListValue parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (ListValue)PARSER.parseFrom(data);
   }

   public static ListValue parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (ListValue)PARSER.parseFrom(data, extensionRegistry);
   }

   public static ListValue parseFrom(InputStream input) throws IOException {
      return (ListValue)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static ListValue parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (ListValue)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static ListValue parseDelimitedFrom(InputStream input) throws IOException {
      return (ListValue)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
   }

   public static ListValue parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (ListValue)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static ListValue parseFrom(CodedInputStream input) throws IOException {
      return (ListValue)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static ListValue parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (ListValue)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(ListValue prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static ListValue getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public ListValue getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeVersion.RuntimeDomain.PUBLIC, 4, 29, 3, "", ListValue.class.getName());
      DEFAULT_INSTANCE = new ListValue();
      PARSER = new AbstractParser() {
         public ListValue parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            Builder builder = ListValue.newBuilder();

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

   public static final class Builder extends GeneratedMessage.Builder implements ListValueOrBuilder {
      private int bitField0_;
      private List values_;
      private RepeatedFieldBuilder valuesBuilder_;

      public static final Descriptors.Descriptor getDescriptor() {
         return StructProto.internal_static_google_protobuf_ListValue_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return StructProto.internal_static_google_protobuf_ListValue_fieldAccessorTable.ensureFieldAccessorsInitialized(ListValue.class, Builder.class);
      }

      private Builder() {
         this.values_ = Collections.emptyList();
      }

      private Builder(AbstractMessage.BuilderParent parent) {
         super(parent);
         this.values_ = Collections.emptyList();
      }

      public Builder clear() {
         super.clear();
         this.bitField0_ = 0;
         if (this.valuesBuilder_ == null) {
            this.values_ = Collections.emptyList();
         } else {
            this.values_ = null;
            this.valuesBuilder_.clear();
         }

         this.bitField0_ &= -2;
         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return StructProto.internal_static_google_protobuf_ListValue_descriptor;
      }

      public ListValue getDefaultInstanceForType() {
         return ListValue.getDefaultInstance();
      }

      public ListValue build() {
         ListValue result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public ListValue buildPartial() {
         ListValue result = new ListValue(this);
         this.buildPartialRepeatedFields(result);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartialRepeatedFields(ListValue result) {
         if (this.valuesBuilder_ == null) {
            if ((this.bitField0_ & 1) != 0) {
               this.values_ = Collections.unmodifiableList(this.values_);
               this.bitField0_ &= -2;
            }

            result.values_ = this.values_;
         } else {
            result.values_ = this.valuesBuilder_.build();
         }

      }

      private void buildPartial0(ListValue result) {
         int from_bitField0_ = this.bitField0_;
      }

      public Builder mergeFrom(Message other) {
         if (other instanceof ListValue) {
            return this.mergeFrom((ListValue)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(ListValue other) {
         if (other == ListValue.getDefaultInstance()) {
            return this;
         } else {
            if (this.valuesBuilder_ == null) {
               if (!other.values_.isEmpty()) {
                  if (this.values_.isEmpty()) {
                     this.values_ = other.values_;
                     this.bitField0_ &= -2;
                  } else {
                     this.ensureValuesIsMutable();
                     this.values_.addAll(other.values_);
                  }

                  this.onChanged();
               }
            } else if (!other.values_.isEmpty()) {
               if (this.valuesBuilder_.isEmpty()) {
                  this.valuesBuilder_.dispose();
                  this.valuesBuilder_ = null;
                  this.values_ = other.values_;
                  this.bitField0_ &= -2;
                  this.valuesBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? this.getValuesFieldBuilder() : null;
               } else {
                  this.valuesBuilder_.addAllMessages(other.values_);
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
                     case 10:
                        Value m = (Value)input.readMessage(Value.parser(), extensionRegistry);
                        if (this.valuesBuilder_ == null) {
                           this.ensureValuesIsMutable();
                           this.values_.add(m);
                        } else {
                           this.valuesBuilder_.addMessage(m);
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

      private void ensureValuesIsMutable() {
         if ((this.bitField0_ & 1) == 0) {
            this.values_ = new ArrayList(this.values_);
            this.bitField0_ |= 1;
         }

      }

      public List getValuesList() {
         return this.valuesBuilder_ == null ? Collections.unmodifiableList(this.values_) : this.valuesBuilder_.getMessageList();
      }

      public int getValuesCount() {
         return this.valuesBuilder_ == null ? this.values_.size() : this.valuesBuilder_.getCount();
      }

      public Value getValues(int index) {
         return this.valuesBuilder_ == null ? (Value)this.values_.get(index) : (Value)this.valuesBuilder_.getMessage(index);
      }

      public Builder setValues(int index, Value value) {
         if (this.valuesBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.ensureValuesIsMutable();
            this.values_.set(index, value);
            this.onChanged();
         } else {
            this.valuesBuilder_.setMessage(index, value);
         }

         return this;
      }

      public Builder setValues(int index, Value.Builder builderForValue) {
         if (this.valuesBuilder_ == null) {
            this.ensureValuesIsMutable();
            this.values_.set(index, builderForValue.build());
            this.onChanged();
         } else {
            this.valuesBuilder_.setMessage(index, builderForValue.build());
         }

         return this;
      }

      public Builder addValues(Value value) {
         if (this.valuesBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.ensureValuesIsMutable();
            this.values_.add(value);
            this.onChanged();
         } else {
            this.valuesBuilder_.addMessage(value);
         }

         return this;
      }

      public Builder addValues(int index, Value value) {
         if (this.valuesBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.ensureValuesIsMutable();
            this.values_.add(index, value);
            this.onChanged();
         } else {
            this.valuesBuilder_.addMessage(index, value);
         }

         return this;
      }

      public Builder addValues(Value.Builder builderForValue) {
         if (this.valuesBuilder_ == null) {
            this.ensureValuesIsMutable();
            this.values_.add(builderForValue.build());
            this.onChanged();
         } else {
            this.valuesBuilder_.addMessage(builderForValue.build());
         }

         return this;
      }

      public Builder addValues(int index, Value.Builder builderForValue) {
         if (this.valuesBuilder_ == null) {
            this.ensureValuesIsMutable();
            this.values_.add(index, builderForValue.build());
            this.onChanged();
         } else {
            this.valuesBuilder_.addMessage(index, builderForValue.build());
         }

         return this;
      }

      public Builder addAllValues(Iterable values) {
         if (this.valuesBuilder_ == null) {
            this.ensureValuesIsMutable();
            AbstractMessageLite.Builder.addAll(values, this.values_);
            this.onChanged();
         } else {
            this.valuesBuilder_.addAllMessages(values);
         }

         return this;
      }

      public Builder clearValues() {
         if (this.valuesBuilder_ == null) {
            this.values_ = Collections.emptyList();
            this.bitField0_ &= -2;
            this.onChanged();
         } else {
            this.valuesBuilder_.clear();
         }

         return this;
      }

      public Builder removeValues(int index) {
         if (this.valuesBuilder_ == null) {
            this.ensureValuesIsMutable();
            this.values_.remove(index);
            this.onChanged();
         } else {
            this.valuesBuilder_.remove(index);
         }

         return this;
      }

      public Value.Builder getValuesBuilder(int index) {
         return (Value.Builder)this.getValuesFieldBuilder().getBuilder(index);
      }

      public ValueOrBuilder getValuesOrBuilder(int index) {
         return this.valuesBuilder_ == null ? (ValueOrBuilder)this.values_.get(index) : (ValueOrBuilder)this.valuesBuilder_.getMessageOrBuilder(index);
      }

      public List getValuesOrBuilderList() {
         return this.valuesBuilder_ != null ? this.valuesBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList(this.values_);
      }

      public Value.Builder addValuesBuilder() {
         return (Value.Builder)this.getValuesFieldBuilder().addBuilder(Value.getDefaultInstance());
      }

      public Value.Builder addValuesBuilder(int index) {
         return (Value.Builder)this.getValuesFieldBuilder().addBuilder(index, Value.getDefaultInstance());
      }

      public List getValuesBuilderList() {
         return this.getValuesFieldBuilder().getBuilderList();
      }

      private RepeatedFieldBuilder getValuesFieldBuilder() {
         if (this.valuesBuilder_ == null) {
            this.valuesBuilder_ = new RepeatedFieldBuilder(this.values_, (this.bitField0_ & 1) != 0, this.getParentForChildren(), this.isClean());
            this.values_ = null;
         }

         return this.valuesBuilder_;
      }
   }
}
