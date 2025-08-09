package org.apache.hadoop.hive.metastore;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilder;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.Descriptors.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Metastore {
   private static Descriptors.Descriptor internal_static_org_apache_hadoop_hive_metastore_SplitInfo_descriptor;
   private static GeneratedMessage.FieldAccessorTable internal_static_org_apache_hadoop_hive_metastore_SplitInfo_fieldAccessorTable;
   private static Descriptors.Descriptor internal_static_org_apache_hadoop_hive_metastore_SplitInfos_descriptor;
   private static GeneratedMessage.FieldAccessorTable internal_static_org_apache_hadoop_hive_metastore_SplitInfos_fieldAccessorTable;
   private static Descriptors.FileDescriptor descriptor;

   private Metastore() {
   }

   public static void registerAllExtensions(ExtensionRegistry registry) {
   }

   public static Descriptors.FileDescriptor getDescriptor() {
      return descriptor;
   }

   static {
      String[] descriptorData = new String[]{"\n\u000fmetastore.proto\u0012 org.apache.hadoop.hive.metastore\":\n\tSplitInfo\u0012\u000e\n\u0006offset\u0018\u0001 \u0002(\u0003\u0012\u000e\n\u0006length\u0018\u0002 \u0002(\u0003\u0012\r\n\u0005index\u0018\u0003 \u0002(\u0005\"H\n\nSplitInfos\u0012:\n\u0005infos\u0018\u0001 \u0003(\u000b2+.org.apache.hadoop.hive.metastore.SplitInfo"};
      Descriptors.FileDescriptor.InternalDescriptorAssigner assigner = new Descriptors.FileDescriptor.InternalDescriptorAssigner() {
         public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor root) {
            Metastore.descriptor = root;
            Metastore.internal_static_org_apache_hadoop_hive_metastore_SplitInfo_descriptor = (Descriptors.Descriptor)Metastore.getDescriptor().getMessageTypes().get(0);
            Metastore.internal_static_org_apache_hadoop_hive_metastore_SplitInfo_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(Metastore.internal_static_org_apache_hadoop_hive_metastore_SplitInfo_descriptor, new String[]{"Offset", "Length", "Index"});
            Metastore.internal_static_org_apache_hadoop_hive_metastore_SplitInfos_descriptor = (Descriptors.Descriptor)Metastore.getDescriptor().getMessageTypes().get(1);
            Metastore.internal_static_org_apache_hadoop_hive_metastore_SplitInfos_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(Metastore.internal_static_org_apache_hadoop_hive_metastore_SplitInfos_descriptor, new String[]{"Infos"});
            return null;
         }
      };
      FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0], assigner);
   }

   public static final class SplitInfo extends GeneratedMessage implements SplitInfoOrBuilder {
      private static final SplitInfo defaultInstance = new SplitInfo(true);
      private final UnknownFieldSet unknownFields;
      public static Parser PARSER = new AbstractParser() {
         public SplitInfo parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new SplitInfo(input, extensionRegistry);
         }
      };
      private int bitField0_;
      public static final int OFFSET_FIELD_NUMBER = 1;
      private long offset_;
      public static final int LENGTH_FIELD_NUMBER = 2;
      private long length_;
      public static final int INDEX_FIELD_NUMBER = 3;
      private int index_;
      private byte memoizedIsInitialized;
      private int memoizedSerializedSize;
      private static final long serialVersionUID = 0L;

      private SplitInfo(GeneratedMessage.Builder builder) {
         super(builder);
         this.memoizedIsInitialized = -1;
         this.memoizedSerializedSize = -1;
         this.unknownFields = builder.getUnknownFields();
      }

      private SplitInfo(boolean noInit) {
         this.memoizedIsInitialized = -1;
         this.memoizedSerializedSize = -1;
         this.unknownFields = UnknownFieldSet.getDefaultInstance();
      }

      public static SplitInfo getDefaultInstance() {
         return defaultInstance;
      }

      public SplitInfo getDefaultInstanceForType() {
         return defaultInstance;
      }

      public final UnknownFieldSet getUnknownFields() {
         return this.unknownFields;
      }

      private SplitInfo(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
         this.memoizedIsInitialized = -1;
         this.memoizedSerializedSize = -1;
         this.initFields();
         int mutable_bitField0_ = 0;
         UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();

         try {
            boolean done = false;

            while(!done) {
               int tag = input.readTag();
               switch (tag) {
                  case 0:
                     done = true;
                     break;
                  case 8:
                     this.bitField0_ |= 1;
                     this.offset_ = input.readInt64();
                     break;
                  case 16:
                     this.bitField0_ |= 2;
                     this.length_ = input.readInt64();
                     break;
                  case 24:
                     this.bitField0_ |= 4;
                     this.index_ = input.readInt32();
                     break;
                  default:
                     if (!this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                        done = true;
                     }
               }
            }
         } catch (InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(this);
         } catch (IOException e) {
            throw (new InvalidProtocolBufferException(e.getMessage())).setUnfinishedMessage(this);
         } finally {
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
         }

      }

      public static final Descriptors.Descriptor getDescriptor() {
         return Metastore.internal_static_org_apache_hadoop_hive_metastore_SplitInfo_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return Metastore.internal_static_org_apache_hadoop_hive_metastore_SplitInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(SplitInfo.class, Builder.class);
      }

      public Parser getParserForType() {
         return PARSER;
      }

      public boolean hasOffset() {
         return (this.bitField0_ & 1) == 1;
      }

      public long getOffset() {
         return this.offset_;
      }

      public boolean hasLength() {
         return (this.bitField0_ & 2) == 2;
      }

      public long getLength() {
         return this.length_;
      }

      public boolean hasIndex() {
         return (this.bitField0_ & 4) == 4;
      }

      public int getIndex() {
         return this.index_;
      }

      private void initFields() {
         this.offset_ = 0L;
         this.length_ = 0L;
         this.index_ = 0;
      }

      public final boolean isInitialized() {
         byte isInitialized = this.memoizedIsInitialized;
         if (isInitialized != -1) {
            return isInitialized == 1;
         } else if (!this.hasOffset()) {
            this.memoizedIsInitialized = 0;
            return false;
         } else if (!this.hasLength()) {
            this.memoizedIsInitialized = 0;
            return false;
         } else if (!this.hasIndex()) {
            this.memoizedIsInitialized = 0;
            return false;
         } else {
            this.memoizedIsInitialized = 1;
            return true;
         }
      }

      public void writeTo(CodedOutputStream output) throws IOException {
         this.getSerializedSize();
         if ((this.bitField0_ & 1) == 1) {
            output.writeInt64(1, this.offset_);
         }

         if ((this.bitField0_ & 2) == 2) {
            output.writeInt64(2, this.length_);
         }

         if ((this.bitField0_ & 4) == 4) {
            output.writeInt32(3, this.index_);
         }

         this.getUnknownFields().writeTo(output);
      }

      public int getSerializedSize() {
         int size = this.memoizedSerializedSize;
         if (size != -1) {
            return size;
         } else {
            size = 0;
            if ((this.bitField0_ & 1) == 1) {
               size += CodedOutputStream.computeInt64Size(1, this.offset_);
            }

            if ((this.bitField0_ & 2) == 2) {
               size += CodedOutputStream.computeInt64Size(2, this.length_);
            }

            if ((this.bitField0_ & 4) == 4) {
               size += CodedOutputStream.computeInt32Size(3, this.index_);
            }

            size += this.getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size;
            return size;
         }
      }

      protected Object writeReplace() throws ObjectStreamException {
         return super.writeReplace();
      }

      public static SplitInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
         return (SplitInfo)PARSER.parseFrom(data);
      }

      public static SplitInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
         return (SplitInfo)PARSER.parseFrom(data, extensionRegistry);
      }

      public static SplitInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
         return (SplitInfo)PARSER.parseFrom(data);
      }

      public static SplitInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
         return (SplitInfo)PARSER.parseFrom(data, extensionRegistry);
      }

      public static SplitInfo parseFrom(InputStream input) throws IOException {
         return (SplitInfo)PARSER.parseFrom(input);
      }

      public static SplitInfo parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
         return (SplitInfo)PARSER.parseFrom(input, extensionRegistry);
      }

      public static SplitInfo parseDelimitedFrom(InputStream input) throws IOException {
         return (SplitInfo)PARSER.parseDelimitedFrom(input);
      }

      public static SplitInfo parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
         return (SplitInfo)PARSER.parseDelimitedFrom(input, extensionRegistry);
      }

      public static SplitInfo parseFrom(CodedInputStream input) throws IOException {
         return (SplitInfo)PARSER.parseFrom(input);
      }

      public static SplitInfo parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
         return (SplitInfo)PARSER.parseFrom(input, extensionRegistry);
      }

      public static Builder newBuilder() {
         return Metastore.SplitInfo.Builder.create();
      }

      public Builder newBuilderForType() {
         return newBuilder();
      }

      public static Builder newBuilder(SplitInfo prototype) {
         return newBuilder().mergeFrom(prototype);
      }

      public Builder toBuilder() {
         return newBuilder(this);
      }

      protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
         Builder builder = new Builder(parent);
         return builder;
      }

      static {
         defaultInstance.initFields();
      }

      public static final class Builder extends GeneratedMessage.Builder implements SplitInfoOrBuilder {
         private int bitField0_;
         private long offset_;
         private long length_;
         private int index_;

         public static final Descriptors.Descriptor getDescriptor() {
            return Metastore.internal_static_org_apache_hadoop_hive_metastore_SplitInfo_descriptor;
         }

         protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Metastore.internal_static_org_apache_hadoop_hive_metastore_SplitInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(SplitInfo.class, Builder.class);
         }

         private Builder() {
            this.maybeForceBuilderInitialization();
         }

         private Builder(GeneratedMessage.BuilderParent parent) {
            super(parent);
            this.maybeForceBuilderInitialization();
         }

         private void maybeForceBuilderInitialization() {
            if (Metastore.SplitInfo.alwaysUseFieldBuilders) {
            }

         }

         private static Builder create() {
            return new Builder();
         }

         public Builder clear() {
            super.clear();
            this.offset_ = 0L;
            this.bitField0_ &= -2;
            this.length_ = 0L;
            this.bitField0_ &= -3;
            this.index_ = 0;
            this.bitField0_ &= -5;
            return this;
         }

         public Builder clone() {
            return create().mergeFrom(this.buildPartial());
         }

         public Descriptors.Descriptor getDescriptorForType() {
            return Metastore.internal_static_org_apache_hadoop_hive_metastore_SplitInfo_descriptor;
         }

         public SplitInfo getDefaultInstanceForType() {
            return Metastore.SplitInfo.getDefaultInstance();
         }

         public SplitInfo build() {
            SplitInfo result = this.buildPartial();
            if (!result.isInitialized()) {
               throw newUninitializedMessageException(result);
            } else {
               return result;
            }
         }

         public SplitInfo buildPartial() {
            SplitInfo result = new SplitInfo(this);
            int from_bitField0_ = this.bitField0_;
            int to_bitField0_ = 0;
            if ((from_bitField0_ & 1) == 1) {
               to_bitField0_ |= 1;
            }

            result.offset_ = this.offset_;
            if ((from_bitField0_ & 2) == 2) {
               to_bitField0_ |= 2;
            }

            result.length_ = this.length_;
            if ((from_bitField0_ & 4) == 4) {
               to_bitField0_ |= 4;
            }

            result.index_ = this.index_;
            result.bitField0_ = to_bitField0_;
            this.onBuilt();
            return result;
         }

         public Builder mergeFrom(Message other) {
            if (other instanceof SplitInfo) {
               return this.mergeFrom((SplitInfo)other);
            } else {
               super.mergeFrom(other);
               return this;
            }
         }

         public Builder mergeFrom(SplitInfo other) {
            if (other == Metastore.SplitInfo.getDefaultInstance()) {
               return this;
            } else {
               if (other.hasOffset()) {
                  this.setOffset(other.getOffset());
               }

               if (other.hasLength()) {
                  this.setLength(other.getLength());
               }

               if (other.hasIndex()) {
                  this.setIndex(other.getIndex());
               }

               this.mergeUnknownFields(other.getUnknownFields());
               return this;
            }
         }

         public final boolean isInitialized() {
            if (!this.hasOffset()) {
               return false;
            } else if (!this.hasLength()) {
               return false;
            } else {
               return this.hasIndex();
            }
         }

         public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            SplitInfo parsedMessage = null;

            try {
               parsedMessage = (SplitInfo)Metastore.SplitInfo.PARSER.parsePartialFrom(input, extensionRegistry);
            } catch (InvalidProtocolBufferException e) {
               parsedMessage = (SplitInfo)e.getUnfinishedMessage();
               throw e;
            } finally {
               if (parsedMessage != null) {
                  this.mergeFrom(parsedMessage);
               }

            }

            return this;
         }

         public boolean hasOffset() {
            return (this.bitField0_ & 1) == 1;
         }

         public long getOffset() {
            return this.offset_;
         }

         public Builder setOffset(long value) {
            this.bitField0_ |= 1;
            this.offset_ = value;
            this.onChanged();
            return this;
         }

         public Builder clearOffset() {
            this.bitField0_ &= -2;
            this.offset_ = 0L;
            this.onChanged();
            return this;
         }

         public boolean hasLength() {
            return (this.bitField0_ & 2) == 2;
         }

         public long getLength() {
            return this.length_;
         }

         public Builder setLength(long value) {
            this.bitField0_ |= 2;
            this.length_ = value;
            this.onChanged();
            return this;
         }

         public Builder clearLength() {
            this.bitField0_ &= -3;
            this.length_ = 0L;
            this.onChanged();
            return this;
         }

         public boolean hasIndex() {
            return (this.bitField0_ & 4) == 4;
         }

         public int getIndex() {
            return this.index_;
         }

         public Builder setIndex(int value) {
            this.bitField0_ |= 4;
            this.index_ = value;
            this.onChanged();
            return this;
         }

         public Builder clearIndex() {
            this.bitField0_ &= -5;
            this.index_ = 0;
            this.onChanged();
            return this;
         }
      }
   }

   public static final class SplitInfos extends GeneratedMessage implements SplitInfosOrBuilder {
      private static final SplitInfos defaultInstance = new SplitInfos(true);
      private final UnknownFieldSet unknownFields;
      public static Parser PARSER = new AbstractParser() {
         public SplitInfos parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new SplitInfos(input, extensionRegistry);
         }
      };
      public static final int INFOS_FIELD_NUMBER = 1;
      private List infos_;
      private byte memoizedIsInitialized;
      private int memoizedSerializedSize;
      private static final long serialVersionUID = 0L;

      private SplitInfos(GeneratedMessage.Builder builder) {
         super(builder);
         this.memoizedIsInitialized = -1;
         this.memoizedSerializedSize = -1;
         this.unknownFields = builder.getUnknownFields();
      }

      private SplitInfos(boolean noInit) {
         this.memoizedIsInitialized = -1;
         this.memoizedSerializedSize = -1;
         this.unknownFields = UnknownFieldSet.getDefaultInstance();
      }

      public static SplitInfos getDefaultInstance() {
         return defaultInstance;
      }

      public SplitInfos getDefaultInstanceForType() {
         return defaultInstance;
      }

      public final UnknownFieldSet getUnknownFields() {
         return this.unknownFields;
      }

      private SplitInfos(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
         this.memoizedIsInitialized = -1;
         this.memoizedSerializedSize = -1;
         this.initFields();
         int mutable_bitField0_ = 0;
         UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();

         try {
            boolean done = false;

            while(!done) {
               int tag = input.readTag();
               switch (tag) {
                  case 0:
                     done = true;
                     break;
                  case 10:
                     if ((mutable_bitField0_ & 1) != 1) {
                        this.infos_ = new ArrayList();
                        mutable_bitField0_ |= 1;
                     }

                     this.infos_.add(input.readMessage(Metastore.SplitInfo.PARSER, extensionRegistry));
                     break;
                  default:
                     if (!this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                        done = true;
                     }
               }
            }
         } catch (InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(this);
         } catch (IOException e) {
            throw (new InvalidProtocolBufferException(e.getMessage())).setUnfinishedMessage(this);
         } finally {
            if ((mutable_bitField0_ & 1) == 1) {
               this.infos_ = Collections.unmodifiableList(this.infos_);
            }

            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
         }

      }

      public static final Descriptors.Descriptor getDescriptor() {
         return Metastore.internal_static_org_apache_hadoop_hive_metastore_SplitInfos_descriptor;
      }

      protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
         return Metastore.internal_static_org_apache_hadoop_hive_metastore_SplitInfos_fieldAccessorTable.ensureFieldAccessorsInitialized(SplitInfos.class, Builder.class);
      }

      public Parser getParserForType() {
         return PARSER;
      }

      public List getInfosList() {
         return this.infos_;
      }

      public List getInfosOrBuilderList() {
         return this.infos_;
      }

      public int getInfosCount() {
         return this.infos_.size();
      }

      public SplitInfo getInfos(int index) {
         return (SplitInfo)this.infos_.get(index);
      }

      public SplitInfoOrBuilder getInfosOrBuilder(int index) {
         return (SplitInfoOrBuilder)this.infos_.get(index);
      }

      private void initFields() {
         this.infos_ = Collections.emptyList();
      }

      public final boolean isInitialized() {
         byte isInitialized = this.memoizedIsInitialized;
         if (isInitialized != -1) {
            return isInitialized == 1;
         } else {
            for(int i = 0; i < this.getInfosCount(); ++i) {
               if (!this.getInfos(i).isInitialized()) {
                  this.memoizedIsInitialized = 0;
                  return false;
               }
            }

            this.memoizedIsInitialized = 1;
            return true;
         }
      }

      public void writeTo(CodedOutputStream output) throws IOException {
         this.getSerializedSize();

         for(int i = 0; i < this.infos_.size(); ++i) {
            output.writeMessage(1, (MessageLite)this.infos_.get(i));
         }

         this.getUnknownFields().writeTo(output);
      }

      public int getSerializedSize() {
         int size = this.memoizedSerializedSize;
         if (size != -1) {
            return size;
         } else {
            size = 0;

            for(int i = 0; i < this.infos_.size(); ++i) {
               size += CodedOutputStream.computeMessageSize(1, (MessageLite)this.infos_.get(i));
            }

            size += this.getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size;
            return size;
         }
      }

      protected Object writeReplace() throws ObjectStreamException {
         return super.writeReplace();
      }

      public static SplitInfos parseFrom(ByteString data) throws InvalidProtocolBufferException {
         return (SplitInfos)PARSER.parseFrom(data);
      }

      public static SplitInfos parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
         return (SplitInfos)PARSER.parseFrom(data, extensionRegistry);
      }

      public static SplitInfos parseFrom(byte[] data) throws InvalidProtocolBufferException {
         return (SplitInfos)PARSER.parseFrom(data);
      }

      public static SplitInfos parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
         return (SplitInfos)PARSER.parseFrom(data, extensionRegistry);
      }

      public static SplitInfos parseFrom(InputStream input) throws IOException {
         return (SplitInfos)PARSER.parseFrom(input);
      }

      public static SplitInfos parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
         return (SplitInfos)PARSER.parseFrom(input, extensionRegistry);
      }

      public static SplitInfos parseDelimitedFrom(InputStream input) throws IOException {
         return (SplitInfos)PARSER.parseDelimitedFrom(input);
      }

      public static SplitInfos parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
         return (SplitInfos)PARSER.parseDelimitedFrom(input, extensionRegistry);
      }

      public static SplitInfos parseFrom(CodedInputStream input) throws IOException {
         return (SplitInfos)PARSER.parseFrom(input);
      }

      public static SplitInfos parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
         return (SplitInfos)PARSER.parseFrom(input, extensionRegistry);
      }

      public static Builder newBuilder() {
         return Metastore.SplitInfos.Builder.create();
      }

      public Builder newBuilderForType() {
         return newBuilder();
      }

      public static Builder newBuilder(SplitInfos prototype) {
         return newBuilder().mergeFrom(prototype);
      }

      public Builder toBuilder() {
         return newBuilder(this);
      }

      protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
         Builder builder = new Builder(parent);
         return builder;
      }

      static {
         defaultInstance.initFields();
      }

      public static final class Builder extends GeneratedMessage.Builder implements SplitInfosOrBuilder {
         private int bitField0_;
         private List infos_;
         private RepeatedFieldBuilder infosBuilder_;

         public static final Descriptors.Descriptor getDescriptor() {
            return Metastore.internal_static_org_apache_hadoop_hive_metastore_SplitInfos_descriptor;
         }

         protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Metastore.internal_static_org_apache_hadoop_hive_metastore_SplitInfos_fieldAccessorTable.ensureFieldAccessorsInitialized(SplitInfos.class, Builder.class);
         }

         private Builder() {
            this.infos_ = Collections.emptyList();
            this.maybeForceBuilderInitialization();
         }

         private Builder(GeneratedMessage.BuilderParent parent) {
            super(parent);
            this.infos_ = Collections.emptyList();
            this.maybeForceBuilderInitialization();
         }

         private void maybeForceBuilderInitialization() {
            if (Metastore.SplitInfos.alwaysUseFieldBuilders) {
               this.getInfosFieldBuilder();
            }

         }

         private static Builder create() {
            return new Builder();
         }

         public Builder clear() {
            super.clear();
            if (this.infosBuilder_ == null) {
               this.infos_ = Collections.emptyList();
               this.bitField0_ &= -2;
            } else {
               this.infosBuilder_.clear();
            }

            return this;
         }

         public Builder clone() {
            return create().mergeFrom(this.buildPartial());
         }

         public Descriptors.Descriptor getDescriptorForType() {
            return Metastore.internal_static_org_apache_hadoop_hive_metastore_SplitInfos_descriptor;
         }

         public SplitInfos getDefaultInstanceForType() {
            return Metastore.SplitInfos.getDefaultInstance();
         }

         public SplitInfos build() {
            SplitInfos result = this.buildPartial();
            if (!result.isInitialized()) {
               throw newUninitializedMessageException(result);
            } else {
               return result;
            }
         }

         public SplitInfos buildPartial() {
            SplitInfos result = new SplitInfos(this);
            int from_bitField0_ = this.bitField0_;
            if (this.infosBuilder_ == null) {
               if ((this.bitField0_ & 1) == 1) {
                  this.infos_ = Collections.unmodifiableList(this.infos_);
                  this.bitField0_ &= -2;
               }

               result.infos_ = this.infos_;
            } else {
               result.infos_ = this.infosBuilder_.build();
            }

            this.onBuilt();
            return result;
         }

         public Builder mergeFrom(Message other) {
            if (other instanceof SplitInfos) {
               return this.mergeFrom((SplitInfos)other);
            } else {
               super.mergeFrom(other);
               return this;
            }
         }

         public Builder mergeFrom(SplitInfos other) {
            if (other == Metastore.SplitInfos.getDefaultInstance()) {
               return this;
            } else {
               if (this.infosBuilder_ == null) {
                  if (!other.infos_.isEmpty()) {
                     if (this.infos_.isEmpty()) {
                        this.infos_ = other.infos_;
                        this.bitField0_ &= -2;
                     } else {
                        this.ensureInfosIsMutable();
                        this.infos_.addAll(other.infos_);
                     }

                     this.onChanged();
                  }
               } else if (!other.infos_.isEmpty()) {
                  if (this.infosBuilder_.isEmpty()) {
                     this.infosBuilder_.dispose();
                     this.infosBuilder_ = null;
                     this.infos_ = other.infos_;
                     this.bitField0_ &= -2;
                     this.infosBuilder_ = Metastore.SplitInfos.alwaysUseFieldBuilders ? this.getInfosFieldBuilder() : null;
                  } else {
                     this.infosBuilder_.addAllMessages(other.infos_);
                  }
               }

               this.mergeUnknownFields(other.getUnknownFields());
               return this;
            }
         }

         public final boolean isInitialized() {
            for(int i = 0; i < this.getInfosCount(); ++i) {
               if (!this.getInfos(i).isInitialized()) {
                  return false;
               }
            }

            return true;
         }

         public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            SplitInfos parsedMessage = null;

            try {
               parsedMessage = (SplitInfos)Metastore.SplitInfos.PARSER.parsePartialFrom(input, extensionRegistry);
            } catch (InvalidProtocolBufferException e) {
               parsedMessage = (SplitInfos)e.getUnfinishedMessage();
               throw e;
            } finally {
               if (parsedMessage != null) {
                  this.mergeFrom(parsedMessage);
               }

            }

            return this;
         }

         private void ensureInfosIsMutable() {
            if ((this.bitField0_ & 1) != 1) {
               this.infos_ = new ArrayList(this.infos_);
               this.bitField0_ |= 1;
            }

         }

         public List getInfosList() {
            return this.infosBuilder_ == null ? Collections.unmodifiableList(this.infos_) : this.infosBuilder_.getMessageList();
         }

         public int getInfosCount() {
            return this.infosBuilder_ == null ? this.infos_.size() : this.infosBuilder_.getCount();
         }

         public SplitInfo getInfos(int index) {
            return this.infosBuilder_ == null ? (SplitInfo)this.infos_.get(index) : (SplitInfo)this.infosBuilder_.getMessage(index);
         }

         public Builder setInfos(int index, SplitInfo value) {
            if (this.infosBuilder_ == null) {
               if (value == null) {
                  throw new NullPointerException();
               }

               this.ensureInfosIsMutable();
               this.infos_.set(index, value);
               this.onChanged();
            } else {
               this.infosBuilder_.setMessage(index, value);
            }

            return this;
         }

         public Builder setInfos(int index, SplitInfo.Builder builderForValue) {
            if (this.infosBuilder_ == null) {
               this.ensureInfosIsMutable();
               this.infos_.set(index, builderForValue.build());
               this.onChanged();
            } else {
               this.infosBuilder_.setMessage(index, builderForValue.build());
            }

            return this;
         }

         public Builder addInfos(SplitInfo value) {
            if (this.infosBuilder_ == null) {
               if (value == null) {
                  throw new NullPointerException();
               }

               this.ensureInfosIsMutable();
               this.infos_.add(value);
               this.onChanged();
            } else {
               this.infosBuilder_.addMessage(value);
            }

            return this;
         }

         public Builder addInfos(int index, SplitInfo value) {
            if (this.infosBuilder_ == null) {
               if (value == null) {
                  throw new NullPointerException();
               }

               this.ensureInfosIsMutable();
               this.infos_.add(index, value);
               this.onChanged();
            } else {
               this.infosBuilder_.addMessage(index, value);
            }

            return this;
         }

         public Builder addInfos(SplitInfo.Builder builderForValue) {
            if (this.infosBuilder_ == null) {
               this.ensureInfosIsMutable();
               this.infos_.add(builderForValue.build());
               this.onChanged();
            } else {
               this.infosBuilder_.addMessage(builderForValue.build());
            }

            return this;
         }

         public Builder addInfos(int index, SplitInfo.Builder builderForValue) {
            if (this.infosBuilder_ == null) {
               this.ensureInfosIsMutable();
               this.infos_.add(index, builderForValue.build());
               this.onChanged();
            } else {
               this.infosBuilder_.addMessage(index, builderForValue.build());
            }

            return this;
         }

         public Builder addAllInfos(Iterable values) {
            if (this.infosBuilder_ == null) {
               this.ensureInfosIsMutable();
               com.google.protobuf.GeneratedMessage.Builder.addAll(values, this.infos_);
               this.onChanged();
            } else {
               this.infosBuilder_.addAllMessages(values);
            }

            return this;
         }

         public Builder clearInfos() {
            if (this.infosBuilder_ == null) {
               this.infos_ = Collections.emptyList();
               this.bitField0_ &= -2;
               this.onChanged();
            } else {
               this.infosBuilder_.clear();
            }

            return this;
         }

         public Builder removeInfos(int index) {
            if (this.infosBuilder_ == null) {
               this.ensureInfosIsMutable();
               this.infos_.remove(index);
               this.onChanged();
            } else {
               this.infosBuilder_.remove(index);
            }

            return this;
         }

         public SplitInfo.Builder getInfosBuilder(int index) {
            return (SplitInfo.Builder)this.getInfosFieldBuilder().getBuilder(index);
         }

         public SplitInfoOrBuilder getInfosOrBuilder(int index) {
            return this.infosBuilder_ == null ? (SplitInfoOrBuilder)this.infos_.get(index) : (SplitInfoOrBuilder)this.infosBuilder_.getMessageOrBuilder(index);
         }

         public List getInfosOrBuilderList() {
            return this.infosBuilder_ != null ? this.infosBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList(this.infos_);
         }

         public SplitInfo.Builder addInfosBuilder() {
            return (SplitInfo.Builder)this.getInfosFieldBuilder().addBuilder(Metastore.SplitInfo.getDefaultInstance());
         }

         public SplitInfo.Builder addInfosBuilder(int index) {
            return (SplitInfo.Builder)this.getInfosFieldBuilder().addBuilder(index, Metastore.SplitInfo.getDefaultInstance());
         }

         public List getInfosBuilderList() {
            return this.getInfosFieldBuilder().getBuilderList();
         }

         private RepeatedFieldBuilder getInfosFieldBuilder() {
            if (this.infosBuilder_ == null) {
               this.infosBuilder_ = new RepeatedFieldBuilder(this.infos_, (this.bitField0_ & 1) == 1, this.getParentForChildren(), this.isClean());
               this.infos_ = null;
            }

            return this.infosBuilder_;
         }
      }
   }

   public interface SplitInfoOrBuilder extends MessageOrBuilder {
      boolean hasOffset();

      long getOffset();

      boolean hasLength();

      long getLength();

      boolean hasIndex();

      int getIndex();
   }

   public interface SplitInfosOrBuilder extends MessageOrBuilder {
      List getInfosList();

      SplitInfo getInfos(int var1);

      int getInfosCount();

      List getInfosOrBuilderList();

      SplitInfoOrBuilder getInfosOrBuilder(int var1);
   }
}
