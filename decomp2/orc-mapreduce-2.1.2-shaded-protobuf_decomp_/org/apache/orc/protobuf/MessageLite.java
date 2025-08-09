package org.apache.orc.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@CheckReturnValue
public interface MessageLite extends MessageLiteOrBuilder {
   void writeTo(CodedOutputStream output) throws IOException;

   int getSerializedSize();

   Parser getParserForType();

   ByteString toByteString();

   byte[] toByteArray();

   void writeTo(OutputStream output) throws IOException;

   void writeDelimitedTo(OutputStream output) throws IOException;

   Builder newBuilderForType();

   Builder toBuilder();

   public interface Builder extends MessageLiteOrBuilder, Cloneable {
      @CanIgnoreReturnValue
      Builder clear();

      MessageLite build();

      MessageLite buildPartial();

      Builder clone();

      @CanIgnoreReturnValue
      Builder mergeFrom(CodedInputStream input) throws IOException;

      @CanIgnoreReturnValue
      Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException;

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

      @CanIgnoreReturnValue
      Builder mergeFrom(MessageLite other);

      boolean mergeDelimitedFrom(InputStream input) throws IOException;

      boolean mergeDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException;
   }
}
