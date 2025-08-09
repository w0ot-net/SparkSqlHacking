package org.sparkproject.spark_core.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Api extends GeneratedMessage implements ApiOrBuilder {
   private static final long serialVersionUID = 0L;
   private int bitField0_;
   public static final int NAME_FIELD_NUMBER = 1;
   private volatile Object name_;
   public static final int METHODS_FIELD_NUMBER = 2;
   private List methods_;
   public static final int OPTIONS_FIELD_NUMBER = 3;
   private List options_;
   public static final int VERSION_FIELD_NUMBER = 4;
   private volatile Object version_;
   public static final int SOURCE_CONTEXT_FIELD_NUMBER = 5;
   private SourceContext sourceContext_;
   public static final int MIXINS_FIELD_NUMBER = 6;
   private List mixins_;
   public static final int SYNTAX_FIELD_NUMBER = 7;
   private int syntax_;
   private byte memoizedIsInitialized;
   private static final Api DEFAULT_INSTANCE;
   private static final Parser PARSER;

   private Api(GeneratedMessage.Builder builder) {
      super(builder);
      this.name_ = "";
      this.version_ = "";
      this.syntax_ = 0;
      this.memoizedIsInitialized = -1;
   }

   private Api() {
      this.name_ = "";
      this.version_ = "";
      this.syntax_ = 0;
      this.memoizedIsInitialized = -1;
      this.name_ = "";
      this.methods_ = Collections.emptyList();
      this.options_ = Collections.emptyList();
      this.version_ = "";
      this.mixins_ = Collections.emptyList();
      this.syntax_ = 0;
   }

   public static final Descriptors.Descriptor getDescriptor() {
      return ApiProto.internal_static_google_protobuf_Api_descriptor;
   }

   protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
      return ApiProto.internal_static_google_protobuf_Api_fieldAccessorTable.ensureFieldAccessorsInitialized(Api.class, Builder.class);
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

   public List getMethodsList() {
      return this.methods_;
   }

   public List getMethodsOrBuilderList() {
      return this.methods_;
   }

   public int getMethodsCount() {
      return this.methods_.size();
   }

   public Method getMethods(int index) {
      return (Method)this.methods_.get(index);
   }

   public MethodOrBuilder getMethodsOrBuilder(int index) {
      return (MethodOrBuilder)this.methods_.get(index);
   }

   public List getOptionsList() {
      return this.options_;
   }

   public List getOptionsOrBuilderList() {
      return this.options_;
   }

   public int getOptionsCount() {
      return this.options_.size();
   }

   public Option getOptions(int index) {
      return (Option)this.options_.get(index);
   }

   public OptionOrBuilder getOptionsOrBuilder(int index) {
      return (OptionOrBuilder)this.options_.get(index);
   }

   public String getVersion() {
      Object ref = this.version_;
      if (ref instanceof String) {
         return (String)ref;
      } else {
         ByteString bs = (ByteString)ref;
         String s = bs.toStringUtf8();
         this.version_ = s;
         return s;
      }
   }

   public ByteString getVersionBytes() {
      Object ref = this.version_;
      if (ref instanceof String) {
         ByteString b = ByteString.copyFromUtf8((String)ref);
         this.version_ = b;
         return b;
      } else {
         return (ByteString)ref;
      }
   }

   public boolean hasSourceContext() {
      return (this.bitField0_ & 1) != 0;
   }

   public SourceContext getSourceContext() {
      return this.sourceContext_ == null ? SourceContext.getDefaultInstance() : this.sourceContext_;
   }

   public SourceContextOrBuilder getSourceContextOrBuilder() {
      return this.sourceContext_ == null ? SourceContext.getDefaultInstance() : this.sourceContext_;
   }

   public List getMixinsList() {
      return this.mixins_;
   }

   public List getMixinsOrBuilderList() {
      return this.mixins_;
   }

   public int getMixinsCount() {
      return this.mixins_.size();
   }

   public Mixin getMixins(int index) {
      return (Mixin)this.mixins_.get(index);
   }

   public MixinOrBuilder getMixinsOrBuilder(int index) {
      return (MixinOrBuilder)this.mixins_.get(index);
   }

   public int getSyntaxValue() {
      return this.syntax_;
   }

   public Syntax getSyntax() {
      Syntax result = Syntax.forNumber(this.syntax_);
      return result == null ? Syntax.UNRECOGNIZED : result;
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
      if (!GeneratedMessage.isStringEmpty(this.name_)) {
         GeneratedMessage.writeString(output, 1, this.name_);
      }

      for(int i = 0; i < this.methods_.size(); ++i) {
         output.writeMessage(2, (MessageLite)this.methods_.get(i));
      }

      for(int i = 0; i < this.options_.size(); ++i) {
         output.writeMessage(3, (MessageLite)this.options_.get(i));
      }

      if (!GeneratedMessage.isStringEmpty(this.version_)) {
         GeneratedMessage.writeString(output, 4, this.version_);
      }

      if ((this.bitField0_ & 1) != 0) {
         output.writeMessage(5, this.getSourceContext());
      }

      for(int i = 0; i < this.mixins_.size(); ++i) {
         output.writeMessage(6, (MessageLite)this.mixins_.get(i));
      }

      if (this.syntax_ != Syntax.SYNTAX_PROTO2.getNumber()) {
         output.writeEnum(7, this.syntax_);
      }

      this.getUnknownFields().writeTo(output);
   }

   public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1) {
         return size;
      } else {
         size = 0;
         if (!GeneratedMessage.isStringEmpty(this.name_)) {
            size += GeneratedMessage.computeStringSize(1, this.name_);
         }

         for(int i = 0; i < this.methods_.size(); ++i) {
            size += CodedOutputStream.computeMessageSize(2, (MessageLite)this.methods_.get(i));
         }

         for(int i = 0; i < this.options_.size(); ++i) {
            size += CodedOutputStream.computeMessageSize(3, (MessageLite)this.options_.get(i));
         }

         if (!GeneratedMessage.isStringEmpty(this.version_)) {
            size += GeneratedMessage.computeStringSize(4, this.version_);
         }

         if ((this.bitField0_ & 1) != 0) {
            size += CodedOutputStream.computeMessageSize(5, this.getSourceContext());
         }

         for(int i = 0; i < this.mixins_.size(); ++i) {
            size += CodedOutputStream.computeMessageSize(6, (MessageLite)this.mixins_.get(i));
         }

         if (this.syntax_ != Syntax.SYNTAX_PROTO2.getNumber()) {
            size += CodedOutputStream.computeEnumSize(7, this.syntax_);
         }

         size += this.getUnknownFields().getSerializedSize();
         this.memoizedSize = size;
         return size;
      }
   }

   public boolean equals(final Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof Api)) {
         return super.equals(obj);
      } else {
         Api other = (Api)obj;
         if (!this.getName().equals(other.getName())) {
            return false;
         } else if (!this.getMethodsList().equals(other.getMethodsList())) {
            return false;
         } else if (!this.getOptionsList().equals(other.getOptionsList())) {
            return false;
         } else if (!this.getVersion().equals(other.getVersion())) {
            return false;
         } else if (this.hasSourceContext() != other.hasSourceContext()) {
            return false;
         } else if (this.hasSourceContext() && !this.getSourceContext().equals(other.getSourceContext())) {
            return false;
         } else if (!this.getMixinsList().equals(other.getMixinsList())) {
            return false;
         } else if (this.syntax_ != other.syntax_) {
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
         if (this.getMethodsCount() > 0) {
            hash = 37 * hash + 2;
            hash = 53 * hash + this.getMethodsList().hashCode();
         }

         if (this.getOptionsCount() > 0) {
            hash = 37 * hash + 3;
            hash = 53 * hash + this.getOptionsList().hashCode();
         }

         hash = 37 * hash + 4;
         hash = 53 * hash + this.getVersion().hashCode();
         if (this.hasSourceContext()) {
            hash = 37 * hash + 5;
            hash = 53 * hash + this.getSourceContext().hashCode();
         }

         if (this.getMixinsCount() > 0) {
            hash = 37 * hash + 6;
            hash = 53 * hash + this.getMixinsList().hashCode();
         }

         hash = 37 * hash + 7;
         hash = 53 * hash + this.syntax_;
         hash = 29 * hash + this.getUnknownFields().hashCode();
         this.memoizedHashCode = hash;
         return hash;
      }
   }

   public static Api parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (Api)PARSER.parseFrom(data);
   }

   public static Api parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Api)PARSER.parseFrom(data, extensionRegistry);
   }

   public static Api parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (Api)PARSER.parseFrom(data);
   }

   public static Api parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Api)PARSER.parseFrom(data, extensionRegistry);
   }

   public static Api parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (Api)PARSER.parseFrom(data);
   }

   public static Api parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (Api)PARSER.parseFrom(data, extensionRegistry);
   }

   public static Api parseFrom(InputStream input) throws IOException {
      return (Api)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static Api parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (Api)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public static Api parseDelimitedFrom(InputStream input) throws IOException {
      return (Api)GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
   }

   public static Api parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (Api)GeneratedMessage.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
   }

   public static Api parseFrom(CodedInputStream input) throws IOException {
      return (Api)GeneratedMessage.parseWithIOException(PARSER, input);
   }

   public static Api parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (Api)GeneratedMessage.parseWithIOException(PARSER, input, extensionRegistry);
   }

   public Builder newBuilderForType() {
      return newBuilder();
   }

   public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
   }

   public static Builder newBuilder(Api prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
   }

   public Builder toBuilder() {
      return this == DEFAULT_INSTANCE ? new Builder() : (new Builder()).mergeFrom(this);
   }

   protected Builder newBuilderForType(AbstractMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
   }

   public static Api getDefaultInstance() {
      return DEFAULT_INSTANCE;
   }

   public static Parser parser() {
      return PARSER;
   }

   public Parser getParserForType() {
      return PARSER;
   }

   public Api getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
   }

   static {
      RuntimeVersion.validateProtobufGencodeVersion(RuntimeVersion.RuntimeDomain.PUBLIC, 4, 29, 3, "", Api.class.getName());
      DEFAULT_INSTANCE = new Api();
      PARSER = new AbstractParser() {
         public Api parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            Builder builder = Api.newBuilder();

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

   public static final class Builder extends GeneratedMessage.Builder implements ApiOrBuilder {
      private int bitField0_;
      private Object name_;
      private List methods_;
      private RepeatedFieldBuilder methodsBuilder_;
      private List options_;
      private RepeatedFieldBuilder optionsBuilder_;
      private Object version_;
      private SourceContext sourceContext_;
      private SingleFieldBuilder sourceContextBuilder_;
      private List mixins_;
      private RepeatedFieldBuilder mixinsBuilder_;
      private int syntax_;

      public static final Descriptors.Descriptor getDescriptor() {
         return ApiProto.internal_static_google_protobuf_Api_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return ApiProto.internal_static_google_protobuf_Api_fieldAccessorTable.ensureFieldAccessorsInitialized(Api.class, Builder.class);
      }

      private Builder() {
         this.name_ = "";
         this.methods_ = Collections.emptyList();
         this.options_ = Collections.emptyList();
         this.version_ = "";
         this.mixins_ = Collections.emptyList();
         this.syntax_ = 0;
         this.maybeForceBuilderInitialization();
      }

      private Builder(AbstractMessage.BuilderParent parent) {
         super(parent);
         this.name_ = "";
         this.methods_ = Collections.emptyList();
         this.options_ = Collections.emptyList();
         this.version_ = "";
         this.mixins_ = Collections.emptyList();
         this.syntax_ = 0;
         this.maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization() {
         if (GeneratedMessage.alwaysUseFieldBuilders) {
            this.getMethodsFieldBuilder();
            this.getOptionsFieldBuilder();
            this.getSourceContextFieldBuilder();
            this.getMixinsFieldBuilder();
         }

      }

      public Builder clear() {
         super.clear();
         this.bitField0_ = 0;
         this.name_ = "";
         if (this.methodsBuilder_ == null) {
            this.methods_ = Collections.emptyList();
         } else {
            this.methods_ = null;
            this.methodsBuilder_.clear();
         }

         this.bitField0_ &= -3;
         if (this.optionsBuilder_ == null) {
            this.options_ = Collections.emptyList();
         } else {
            this.options_ = null;
            this.optionsBuilder_.clear();
         }

         this.bitField0_ &= -5;
         this.version_ = "";
         this.sourceContext_ = null;
         if (this.sourceContextBuilder_ != null) {
            this.sourceContextBuilder_.dispose();
            this.sourceContextBuilder_ = null;
         }

         if (this.mixinsBuilder_ == null) {
            this.mixins_ = Collections.emptyList();
         } else {
            this.mixins_ = null;
            this.mixinsBuilder_.clear();
         }

         this.bitField0_ &= -33;
         this.syntax_ = 0;
         return this;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         return ApiProto.internal_static_google_protobuf_Api_descriptor;
      }

      public Api getDefaultInstanceForType() {
         return Api.getDefaultInstance();
      }

      public Api build() {
         Api result = this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      public Api buildPartial() {
         Api result = new Api(this);
         this.buildPartialRepeatedFields(result);
         if (this.bitField0_ != 0) {
            this.buildPartial0(result);
         }

         this.onBuilt();
         return result;
      }

      private void buildPartialRepeatedFields(Api result) {
         if (this.methodsBuilder_ == null) {
            if ((this.bitField0_ & 2) != 0) {
               this.methods_ = Collections.unmodifiableList(this.methods_);
               this.bitField0_ &= -3;
            }

            result.methods_ = this.methods_;
         } else {
            result.methods_ = this.methodsBuilder_.build();
         }

         if (this.optionsBuilder_ == null) {
            if ((this.bitField0_ & 4) != 0) {
               this.options_ = Collections.unmodifiableList(this.options_);
               this.bitField0_ &= -5;
            }

            result.options_ = this.options_;
         } else {
            result.options_ = this.optionsBuilder_.build();
         }

         if (this.mixinsBuilder_ == null) {
            if ((this.bitField0_ & 32) != 0) {
               this.mixins_ = Collections.unmodifiableList(this.mixins_);
               this.bitField0_ &= -33;
            }

            result.mixins_ = this.mixins_;
         } else {
            result.mixins_ = this.mixinsBuilder_.build();
         }

      }

      private void buildPartial0(Api result) {
         int from_bitField0_ = this.bitField0_;
         if ((from_bitField0_ & 1) != 0) {
            result.name_ = this.name_;
         }

         if ((from_bitField0_ & 8) != 0) {
            result.version_ = this.version_;
         }

         int to_bitField0_ = 0;
         if ((from_bitField0_ & 16) != 0) {
            result.sourceContext_ = this.sourceContextBuilder_ == null ? this.sourceContext_ : (SourceContext)this.sourceContextBuilder_.build();
            to_bitField0_ |= 1;
         }

         if ((from_bitField0_ & 64) != 0) {
            result.syntax_ = this.syntax_;
         }

         result.bitField0_ = to_bitField0_;
      }

      public Builder mergeFrom(Message other) {
         if (other instanceof Api) {
            return this.mergeFrom((Api)other);
         } else {
            super.mergeFrom(other);
            return this;
         }
      }

      public Builder mergeFrom(Api other) {
         if (other == Api.getDefaultInstance()) {
            return this;
         } else {
            if (!other.getName().isEmpty()) {
               this.name_ = other.name_;
               this.bitField0_ |= 1;
               this.onChanged();
            }

            if (this.methodsBuilder_ == null) {
               if (!other.methods_.isEmpty()) {
                  if (this.methods_.isEmpty()) {
                     this.methods_ = other.methods_;
                     this.bitField0_ &= -3;
                  } else {
                     this.ensureMethodsIsMutable();
                     this.methods_.addAll(other.methods_);
                  }

                  this.onChanged();
               }
            } else if (!other.methods_.isEmpty()) {
               if (this.methodsBuilder_.isEmpty()) {
                  this.methodsBuilder_.dispose();
                  this.methodsBuilder_ = null;
                  this.methods_ = other.methods_;
                  this.bitField0_ &= -3;
                  this.methodsBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? this.getMethodsFieldBuilder() : null;
               } else {
                  this.methodsBuilder_.addAllMessages(other.methods_);
               }
            }

            if (this.optionsBuilder_ == null) {
               if (!other.options_.isEmpty()) {
                  if (this.options_.isEmpty()) {
                     this.options_ = other.options_;
                     this.bitField0_ &= -5;
                  } else {
                     this.ensureOptionsIsMutable();
                     this.options_.addAll(other.options_);
                  }

                  this.onChanged();
               }
            } else if (!other.options_.isEmpty()) {
               if (this.optionsBuilder_.isEmpty()) {
                  this.optionsBuilder_.dispose();
                  this.optionsBuilder_ = null;
                  this.options_ = other.options_;
                  this.bitField0_ &= -5;
                  this.optionsBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? this.getOptionsFieldBuilder() : null;
               } else {
                  this.optionsBuilder_.addAllMessages(other.options_);
               }
            }

            if (!other.getVersion().isEmpty()) {
               this.version_ = other.version_;
               this.bitField0_ |= 8;
               this.onChanged();
            }

            if (other.hasSourceContext()) {
               this.mergeSourceContext(other.getSourceContext());
            }

            if (this.mixinsBuilder_ == null) {
               if (!other.mixins_.isEmpty()) {
                  if (this.mixins_.isEmpty()) {
                     this.mixins_ = other.mixins_;
                     this.bitField0_ &= -33;
                  } else {
                     this.ensureMixinsIsMutable();
                     this.mixins_.addAll(other.mixins_);
                  }

                  this.onChanged();
               }
            } else if (!other.mixins_.isEmpty()) {
               if (this.mixinsBuilder_.isEmpty()) {
                  this.mixinsBuilder_.dispose();
                  this.mixinsBuilder_ = null;
                  this.mixins_ = other.mixins_;
                  this.bitField0_ &= -33;
                  this.mixinsBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? this.getMixinsFieldBuilder() : null;
               } else {
                  this.mixinsBuilder_.addAllMessages(other.mixins_);
               }
            }

            if (other.syntax_ != 0) {
               this.setSyntaxValue(other.getSyntaxValue());
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
                        Method m = (Method)input.readMessage(Method.parser(), extensionRegistry);
                        if (this.methodsBuilder_ == null) {
                           this.ensureMethodsIsMutable();
                           this.methods_.add(m);
                        } else {
                           this.methodsBuilder_.addMessage(m);
                        }
                        break;
                     case 26:
                        Option m = (Option)input.readMessage(Option.parser(), extensionRegistry);
                        if (this.optionsBuilder_ == null) {
                           this.ensureOptionsIsMutable();
                           this.options_.add(m);
                        } else {
                           this.optionsBuilder_.addMessage(m);
                        }
                        break;
                     case 34:
                        this.version_ = input.readStringRequireUtf8();
                        this.bitField0_ |= 8;
                        break;
                     case 42:
                        input.readMessage((MessageLite.Builder)this.getSourceContextFieldBuilder().getBuilder(), extensionRegistry);
                        this.bitField0_ |= 16;
                        break;
                     case 50:
                        Mixin m = (Mixin)input.readMessage(Mixin.parser(), extensionRegistry);
                        if (this.mixinsBuilder_ == null) {
                           this.ensureMixinsIsMutable();
                           this.mixins_.add(m);
                        } else {
                           this.mixinsBuilder_.addMessage(m);
                        }
                        break;
                     case 56:
                        this.syntax_ = input.readEnum();
                        this.bitField0_ |= 64;
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
         this.name_ = Api.getDefaultInstance().getName();
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

      private void ensureMethodsIsMutable() {
         if ((this.bitField0_ & 2) == 0) {
            this.methods_ = new ArrayList(this.methods_);
            this.bitField0_ |= 2;
         }

      }

      public List getMethodsList() {
         return this.methodsBuilder_ == null ? Collections.unmodifiableList(this.methods_) : this.methodsBuilder_.getMessageList();
      }

      public int getMethodsCount() {
         return this.methodsBuilder_ == null ? this.methods_.size() : this.methodsBuilder_.getCount();
      }

      public Method getMethods(int index) {
         return this.methodsBuilder_ == null ? (Method)this.methods_.get(index) : (Method)this.methodsBuilder_.getMessage(index);
      }

      public Builder setMethods(int index, Method value) {
         if (this.methodsBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.ensureMethodsIsMutable();
            this.methods_.set(index, value);
            this.onChanged();
         } else {
            this.methodsBuilder_.setMessage(index, value);
         }

         return this;
      }

      public Builder setMethods(int index, Method.Builder builderForValue) {
         if (this.methodsBuilder_ == null) {
            this.ensureMethodsIsMutable();
            this.methods_.set(index, builderForValue.build());
            this.onChanged();
         } else {
            this.methodsBuilder_.setMessage(index, builderForValue.build());
         }

         return this;
      }

      public Builder addMethods(Method value) {
         if (this.methodsBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.ensureMethodsIsMutable();
            this.methods_.add(value);
            this.onChanged();
         } else {
            this.methodsBuilder_.addMessage(value);
         }

         return this;
      }

      public Builder addMethods(int index, Method value) {
         if (this.methodsBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.ensureMethodsIsMutable();
            this.methods_.add(index, value);
            this.onChanged();
         } else {
            this.methodsBuilder_.addMessage(index, value);
         }

         return this;
      }

      public Builder addMethods(Method.Builder builderForValue) {
         if (this.methodsBuilder_ == null) {
            this.ensureMethodsIsMutable();
            this.methods_.add(builderForValue.build());
            this.onChanged();
         } else {
            this.methodsBuilder_.addMessage(builderForValue.build());
         }

         return this;
      }

      public Builder addMethods(int index, Method.Builder builderForValue) {
         if (this.methodsBuilder_ == null) {
            this.ensureMethodsIsMutable();
            this.methods_.add(index, builderForValue.build());
            this.onChanged();
         } else {
            this.methodsBuilder_.addMessage(index, builderForValue.build());
         }

         return this;
      }

      public Builder addAllMethods(Iterable values) {
         if (this.methodsBuilder_ == null) {
            this.ensureMethodsIsMutable();
            AbstractMessageLite.Builder.addAll(values, this.methods_);
            this.onChanged();
         } else {
            this.methodsBuilder_.addAllMessages(values);
         }

         return this;
      }

      public Builder clearMethods() {
         if (this.methodsBuilder_ == null) {
            this.methods_ = Collections.emptyList();
            this.bitField0_ &= -3;
            this.onChanged();
         } else {
            this.methodsBuilder_.clear();
         }

         return this;
      }

      public Builder removeMethods(int index) {
         if (this.methodsBuilder_ == null) {
            this.ensureMethodsIsMutable();
            this.methods_.remove(index);
            this.onChanged();
         } else {
            this.methodsBuilder_.remove(index);
         }

         return this;
      }

      public Method.Builder getMethodsBuilder(int index) {
         return (Method.Builder)this.getMethodsFieldBuilder().getBuilder(index);
      }

      public MethodOrBuilder getMethodsOrBuilder(int index) {
         return this.methodsBuilder_ == null ? (MethodOrBuilder)this.methods_.get(index) : (MethodOrBuilder)this.methodsBuilder_.getMessageOrBuilder(index);
      }

      public List getMethodsOrBuilderList() {
         return this.methodsBuilder_ != null ? this.methodsBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList(this.methods_);
      }

      public Method.Builder addMethodsBuilder() {
         return (Method.Builder)this.getMethodsFieldBuilder().addBuilder(Method.getDefaultInstance());
      }

      public Method.Builder addMethodsBuilder(int index) {
         return (Method.Builder)this.getMethodsFieldBuilder().addBuilder(index, Method.getDefaultInstance());
      }

      public List getMethodsBuilderList() {
         return this.getMethodsFieldBuilder().getBuilderList();
      }

      private RepeatedFieldBuilder getMethodsFieldBuilder() {
         if (this.methodsBuilder_ == null) {
            this.methodsBuilder_ = new RepeatedFieldBuilder(this.methods_, (this.bitField0_ & 2) != 0, this.getParentForChildren(), this.isClean());
            this.methods_ = null;
         }

         return this.methodsBuilder_;
      }

      private void ensureOptionsIsMutable() {
         if ((this.bitField0_ & 4) == 0) {
            this.options_ = new ArrayList(this.options_);
            this.bitField0_ |= 4;
         }

      }

      public List getOptionsList() {
         return this.optionsBuilder_ == null ? Collections.unmodifiableList(this.options_) : this.optionsBuilder_.getMessageList();
      }

      public int getOptionsCount() {
         return this.optionsBuilder_ == null ? this.options_.size() : this.optionsBuilder_.getCount();
      }

      public Option getOptions(int index) {
         return this.optionsBuilder_ == null ? (Option)this.options_.get(index) : (Option)this.optionsBuilder_.getMessage(index);
      }

      public Builder setOptions(int index, Option value) {
         if (this.optionsBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.ensureOptionsIsMutable();
            this.options_.set(index, value);
            this.onChanged();
         } else {
            this.optionsBuilder_.setMessage(index, value);
         }

         return this;
      }

      public Builder setOptions(int index, Option.Builder builderForValue) {
         if (this.optionsBuilder_ == null) {
            this.ensureOptionsIsMutable();
            this.options_.set(index, builderForValue.build());
            this.onChanged();
         } else {
            this.optionsBuilder_.setMessage(index, builderForValue.build());
         }

         return this;
      }

      public Builder addOptions(Option value) {
         if (this.optionsBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.ensureOptionsIsMutable();
            this.options_.add(value);
            this.onChanged();
         } else {
            this.optionsBuilder_.addMessage(value);
         }

         return this;
      }

      public Builder addOptions(int index, Option value) {
         if (this.optionsBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.ensureOptionsIsMutable();
            this.options_.add(index, value);
            this.onChanged();
         } else {
            this.optionsBuilder_.addMessage(index, value);
         }

         return this;
      }

      public Builder addOptions(Option.Builder builderForValue) {
         if (this.optionsBuilder_ == null) {
            this.ensureOptionsIsMutable();
            this.options_.add(builderForValue.build());
            this.onChanged();
         } else {
            this.optionsBuilder_.addMessage(builderForValue.build());
         }

         return this;
      }

      public Builder addOptions(int index, Option.Builder builderForValue) {
         if (this.optionsBuilder_ == null) {
            this.ensureOptionsIsMutable();
            this.options_.add(index, builderForValue.build());
            this.onChanged();
         } else {
            this.optionsBuilder_.addMessage(index, builderForValue.build());
         }

         return this;
      }

      public Builder addAllOptions(Iterable values) {
         if (this.optionsBuilder_ == null) {
            this.ensureOptionsIsMutable();
            AbstractMessageLite.Builder.addAll(values, this.options_);
            this.onChanged();
         } else {
            this.optionsBuilder_.addAllMessages(values);
         }

         return this;
      }

      public Builder clearOptions() {
         if (this.optionsBuilder_ == null) {
            this.options_ = Collections.emptyList();
            this.bitField0_ &= -5;
            this.onChanged();
         } else {
            this.optionsBuilder_.clear();
         }

         return this;
      }

      public Builder removeOptions(int index) {
         if (this.optionsBuilder_ == null) {
            this.ensureOptionsIsMutable();
            this.options_.remove(index);
            this.onChanged();
         } else {
            this.optionsBuilder_.remove(index);
         }

         return this;
      }

      public Option.Builder getOptionsBuilder(int index) {
         return (Option.Builder)this.getOptionsFieldBuilder().getBuilder(index);
      }

      public OptionOrBuilder getOptionsOrBuilder(int index) {
         return this.optionsBuilder_ == null ? (OptionOrBuilder)this.options_.get(index) : (OptionOrBuilder)this.optionsBuilder_.getMessageOrBuilder(index);
      }

      public List getOptionsOrBuilderList() {
         return this.optionsBuilder_ != null ? this.optionsBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList(this.options_);
      }

      public Option.Builder addOptionsBuilder() {
         return (Option.Builder)this.getOptionsFieldBuilder().addBuilder(Option.getDefaultInstance());
      }

      public Option.Builder addOptionsBuilder(int index) {
         return (Option.Builder)this.getOptionsFieldBuilder().addBuilder(index, Option.getDefaultInstance());
      }

      public List getOptionsBuilderList() {
         return this.getOptionsFieldBuilder().getBuilderList();
      }

      private RepeatedFieldBuilder getOptionsFieldBuilder() {
         if (this.optionsBuilder_ == null) {
            this.optionsBuilder_ = new RepeatedFieldBuilder(this.options_, (this.bitField0_ & 4) != 0, this.getParentForChildren(), this.isClean());
            this.options_ = null;
         }

         return this.optionsBuilder_;
      }

      public String getVersion() {
         Object ref = this.version_;
         if (!(ref instanceof String)) {
            ByteString bs = (ByteString)ref;
            String s = bs.toStringUtf8();
            this.version_ = s;
            return s;
         } else {
            return (String)ref;
         }
      }

      public ByteString getVersionBytes() {
         Object ref = this.version_;
         if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.version_ = b;
            return b;
         } else {
            return (ByteString)ref;
         }
      }

      public Builder setVersion(String value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.version_ = value;
            this.bitField0_ |= 8;
            this.onChanged();
            return this;
         }
      }

      public Builder clearVersion() {
         this.version_ = Api.getDefaultInstance().getVersion();
         this.bitField0_ &= -9;
         this.onChanged();
         return this;
      }

      public Builder setVersionBytes(ByteString value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            AbstractMessageLite.checkByteStringIsUtf8(value);
            this.version_ = value;
            this.bitField0_ |= 8;
            this.onChanged();
            return this;
         }
      }

      public boolean hasSourceContext() {
         return (this.bitField0_ & 16) != 0;
      }

      public SourceContext getSourceContext() {
         if (this.sourceContextBuilder_ == null) {
            return this.sourceContext_ == null ? SourceContext.getDefaultInstance() : this.sourceContext_;
         } else {
            return (SourceContext)this.sourceContextBuilder_.getMessage();
         }
      }

      public Builder setSourceContext(SourceContext value) {
         if (this.sourceContextBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.sourceContext_ = value;
         } else {
            this.sourceContextBuilder_.setMessage(value);
         }

         this.bitField0_ |= 16;
         this.onChanged();
         return this;
      }

      public Builder setSourceContext(SourceContext.Builder builderForValue) {
         if (this.sourceContextBuilder_ == null) {
            this.sourceContext_ = builderForValue.build();
         } else {
            this.sourceContextBuilder_.setMessage(builderForValue.build());
         }

         this.bitField0_ |= 16;
         this.onChanged();
         return this;
      }

      public Builder mergeSourceContext(SourceContext value) {
         if (this.sourceContextBuilder_ == null) {
            if ((this.bitField0_ & 16) != 0 && this.sourceContext_ != null && this.sourceContext_ != SourceContext.getDefaultInstance()) {
               this.getSourceContextBuilder().mergeFrom(value);
            } else {
               this.sourceContext_ = value;
            }
         } else {
            this.sourceContextBuilder_.mergeFrom(value);
         }

         if (this.sourceContext_ != null) {
            this.bitField0_ |= 16;
            this.onChanged();
         }

         return this;
      }

      public Builder clearSourceContext() {
         this.bitField0_ &= -17;
         this.sourceContext_ = null;
         if (this.sourceContextBuilder_ != null) {
            this.sourceContextBuilder_.dispose();
            this.sourceContextBuilder_ = null;
         }

         this.onChanged();
         return this;
      }

      public SourceContext.Builder getSourceContextBuilder() {
         this.bitField0_ |= 16;
         this.onChanged();
         return (SourceContext.Builder)this.getSourceContextFieldBuilder().getBuilder();
      }

      public SourceContextOrBuilder getSourceContextOrBuilder() {
         if (this.sourceContextBuilder_ != null) {
            return (SourceContextOrBuilder)this.sourceContextBuilder_.getMessageOrBuilder();
         } else {
            return this.sourceContext_ == null ? SourceContext.getDefaultInstance() : this.sourceContext_;
         }
      }

      private SingleFieldBuilder getSourceContextFieldBuilder() {
         if (this.sourceContextBuilder_ == null) {
            this.sourceContextBuilder_ = new SingleFieldBuilder(this.getSourceContext(), this.getParentForChildren(), this.isClean());
            this.sourceContext_ = null;
         }

         return this.sourceContextBuilder_;
      }

      private void ensureMixinsIsMutable() {
         if ((this.bitField0_ & 32) == 0) {
            this.mixins_ = new ArrayList(this.mixins_);
            this.bitField0_ |= 32;
         }

      }

      public List getMixinsList() {
         return this.mixinsBuilder_ == null ? Collections.unmodifiableList(this.mixins_) : this.mixinsBuilder_.getMessageList();
      }

      public int getMixinsCount() {
         return this.mixinsBuilder_ == null ? this.mixins_.size() : this.mixinsBuilder_.getCount();
      }

      public Mixin getMixins(int index) {
         return this.mixinsBuilder_ == null ? (Mixin)this.mixins_.get(index) : (Mixin)this.mixinsBuilder_.getMessage(index);
      }

      public Builder setMixins(int index, Mixin value) {
         if (this.mixinsBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.ensureMixinsIsMutable();
            this.mixins_.set(index, value);
            this.onChanged();
         } else {
            this.mixinsBuilder_.setMessage(index, value);
         }

         return this;
      }

      public Builder setMixins(int index, Mixin.Builder builderForValue) {
         if (this.mixinsBuilder_ == null) {
            this.ensureMixinsIsMutable();
            this.mixins_.set(index, builderForValue.build());
            this.onChanged();
         } else {
            this.mixinsBuilder_.setMessage(index, builderForValue.build());
         }

         return this;
      }

      public Builder addMixins(Mixin value) {
         if (this.mixinsBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.ensureMixinsIsMutable();
            this.mixins_.add(value);
            this.onChanged();
         } else {
            this.mixinsBuilder_.addMessage(value);
         }

         return this;
      }

      public Builder addMixins(int index, Mixin value) {
         if (this.mixinsBuilder_ == null) {
            if (value == null) {
               throw new NullPointerException();
            }

            this.ensureMixinsIsMutable();
            this.mixins_.add(index, value);
            this.onChanged();
         } else {
            this.mixinsBuilder_.addMessage(index, value);
         }

         return this;
      }

      public Builder addMixins(Mixin.Builder builderForValue) {
         if (this.mixinsBuilder_ == null) {
            this.ensureMixinsIsMutable();
            this.mixins_.add(builderForValue.build());
            this.onChanged();
         } else {
            this.mixinsBuilder_.addMessage(builderForValue.build());
         }

         return this;
      }

      public Builder addMixins(int index, Mixin.Builder builderForValue) {
         if (this.mixinsBuilder_ == null) {
            this.ensureMixinsIsMutable();
            this.mixins_.add(index, builderForValue.build());
            this.onChanged();
         } else {
            this.mixinsBuilder_.addMessage(index, builderForValue.build());
         }

         return this;
      }

      public Builder addAllMixins(Iterable values) {
         if (this.mixinsBuilder_ == null) {
            this.ensureMixinsIsMutable();
            AbstractMessageLite.Builder.addAll(values, this.mixins_);
            this.onChanged();
         } else {
            this.mixinsBuilder_.addAllMessages(values);
         }

         return this;
      }

      public Builder clearMixins() {
         if (this.mixinsBuilder_ == null) {
            this.mixins_ = Collections.emptyList();
            this.bitField0_ &= -33;
            this.onChanged();
         } else {
            this.mixinsBuilder_.clear();
         }

         return this;
      }

      public Builder removeMixins(int index) {
         if (this.mixinsBuilder_ == null) {
            this.ensureMixinsIsMutable();
            this.mixins_.remove(index);
            this.onChanged();
         } else {
            this.mixinsBuilder_.remove(index);
         }

         return this;
      }

      public Mixin.Builder getMixinsBuilder(int index) {
         return (Mixin.Builder)this.getMixinsFieldBuilder().getBuilder(index);
      }

      public MixinOrBuilder getMixinsOrBuilder(int index) {
         return this.mixinsBuilder_ == null ? (MixinOrBuilder)this.mixins_.get(index) : (MixinOrBuilder)this.mixinsBuilder_.getMessageOrBuilder(index);
      }

      public List getMixinsOrBuilderList() {
         return this.mixinsBuilder_ != null ? this.mixinsBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList(this.mixins_);
      }

      public Mixin.Builder addMixinsBuilder() {
         return (Mixin.Builder)this.getMixinsFieldBuilder().addBuilder(Mixin.getDefaultInstance());
      }

      public Mixin.Builder addMixinsBuilder(int index) {
         return (Mixin.Builder)this.getMixinsFieldBuilder().addBuilder(index, Mixin.getDefaultInstance());
      }

      public List getMixinsBuilderList() {
         return this.getMixinsFieldBuilder().getBuilderList();
      }

      private RepeatedFieldBuilder getMixinsFieldBuilder() {
         if (this.mixinsBuilder_ == null) {
            this.mixinsBuilder_ = new RepeatedFieldBuilder(this.mixins_, (this.bitField0_ & 32) != 0, this.getParentForChildren(), this.isClean());
            this.mixins_ = null;
         }

         return this.mixinsBuilder_;
      }

      public int getSyntaxValue() {
         return this.syntax_;
      }

      public Builder setSyntaxValue(int value) {
         this.syntax_ = value;
         this.bitField0_ |= 64;
         this.onChanged();
         return this;
      }

      public Syntax getSyntax() {
         Syntax result = Syntax.forNumber(this.syntax_);
         return result == null ? Syntax.UNRECOGNIZED : result;
      }

      public Builder setSyntax(Syntax value) {
         if (value == null) {
            throw new NullPointerException();
         } else {
            this.bitField0_ |= 64;
            this.syntax_ = value.getNumber();
            this.onChanged();
            return this;
         }
      }

      public Builder clearSyntax() {
         this.bitField0_ &= -65;
         this.syntax_ = 0;
         this.onChanged();
         return this;
      }
   }
}
