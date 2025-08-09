package org.apache.orc.protobuf;

import java.io.IOException;
import java.io.InputStream;

@CheckReturnValue
public interface Message extends MessageLite, MessageOrBuilder {
   Parser getParserForType();

   boolean equals(Object other);

   int hashCode();

   String toString();

   Builder newBuilderForType();

   Builder toBuilder();

   public interface Builder extends MessageLite.Builder, MessageOrBuilder {
      @CanIgnoreReturnValue
      Builder clear();

      @CanIgnoreReturnValue
      Builder mergeFrom(Message other);

      Message build();

      Message buildPartial();

      Builder clone();

      @CanIgnoreReturnValue
      Builder mergeFrom(CodedInputStream input) throws IOException;

      @CanIgnoreReturnValue
      Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException;

      Descriptors.Descriptor getDescriptorForType();

      Builder newBuilderForField(Descriptors.FieldDescriptor field);

      Builder getFieldBuilder(Descriptors.FieldDescriptor field);

      Builder getRepeatedFieldBuilder(Descriptors.FieldDescriptor field, int index);

      @CanIgnoreReturnValue
      Builder setField(Descriptors.FieldDescriptor field, Object value);

      @CanIgnoreReturnValue
      Builder clearField(Descriptors.FieldDescriptor field);

      @CanIgnoreReturnValue
      Builder clearOneof(Descriptors.OneofDescriptor oneof);

      @CanIgnoreReturnValue
      Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value);

      @CanIgnoreReturnValue
      Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value);

      @CanIgnoreReturnValue
      Builder setUnknownFields(UnknownFieldSet unknownFields);

      @CanIgnoreReturnValue
      Builder mergeUnknownFields(UnknownFieldSet unknownFields);

      @CanIgnoreReturnValue
      Builder mergeFrom(ByteString data) throws InvalidProtocolBufferException;

      @CanIgnoreReturnValue
      Builder mergeFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException;

      @CanIgnoreReturnValue
      Builder mergeFrom(byte[] data) throws InvalidProtocolBufferException;

      @CanIgnoreReturnValue
      Builder mergeFrom(byte[] data, int off, int len) throws InvalidProtocolBufferException;

      @CanIgnoreReturnValue
      Builder mergeFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException;

      @CanIgnoreReturnValue
      Builder mergeFrom(byte[] data, int off, int len, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException;

      @CanIgnoreReturnValue
      Builder mergeFrom(InputStream input) throws IOException;

      @CanIgnoreReturnValue
      Builder mergeFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException;

      boolean mergeDelimitedFrom(InputStream input) throws IOException;

      boolean mergeDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException;
   }
}
