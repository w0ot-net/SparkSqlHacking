package org.sparkproject.spark_core.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public abstract class AbstractParser implements Parser {
   private static final ExtensionRegistryLite EMPTY_REGISTRY = ExtensionRegistryLite.getEmptyRegistry();

   private UninitializedMessageException newUninitializedMessageException(MessageLite message) {
      return message instanceof AbstractMessageLite ? ((AbstractMessageLite)message).newUninitializedMessageException() : new UninitializedMessageException(message);
   }

   private MessageLite checkMessageInitialized(MessageLite message) throws InvalidProtocolBufferException {
      if (message != null && !message.isInitialized()) {
         throw this.newUninitializedMessageException(message).asInvalidProtocolBufferException().setUnfinishedMessage(message);
      } else {
         return message;
      }
   }

   public MessageLite parsePartialFrom(CodedInputStream input) throws InvalidProtocolBufferException {
      return (MessageLite)this.parsePartialFrom((CodedInputStream)input, EMPTY_REGISTRY);
   }

   public MessageLite parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return this.checkMessageInitialized((MessageLite)this.parsePartialFrom((CodedInputStream)input, extensionRegistry));
   }

   public MessageLite parseFrom(CodedInputStream input) throws InvalidProtocolBufferException {
      return this.parseFrom(input, EMPTY_REGISTRY);
   }

   public MessageLite parsePartialFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      try {
         CodedInputStream input = data.newCodedInput();
         MessageType message = (MessageType)((MessageLite)this.parsePartialFrom((CodedInputStream)input, extensionRegistry));

         try {
            input.checkLastTagWas(0);
         } catch (InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(message);
         }

         return message;
      } catch (InvalidProtocolBufferException e) {
         throw e;
      }
   }

   public MessageLite parsePartialFrom(ByteString data) throws InvalidProtocolBufferException {
      return this.parsePartialFrom(data, EMPTY_REGISTRY);
   }

   public MessageLite parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return this.checkMessageInitialized(this.parsePartialFrom(data, extensionRegistry));
   }

   public MessageLite parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return this.parseFrom(data, EMPTY_REGISTRY);
   }

   public MessageLite parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      MessageType message;
      try {
         CodedInputStream input = CodedInputStream.newInstance(data);
         message = (MessageType)((MessageLite)this.parsePartialFrom((CodedInputStream)input, extensionRegistry));

         try {
            input.checkLastTagWas(0);
         } catch (InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(message);
         }
      } catch (InvalidProtocolBufferException e) {
         throw e;
      }

      return this.checkMessageInitialized(message);
   }

   public MessageLite parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return this.parseFrom(data, EMPTY_REGISTRY);
   }

   public MessageLite parsePartialFrom(byte[] data, int off, int len, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      try {
         CodedInputStream input = CodedInputStream.newInstance(data, off, len);
         MessageType message = (MessageType)((MessageLite)this.parsePartialFrom((CodedInputStream)input, extensionRegistry));

         try {
            input.checkLastTagWas(0);
         } catch (InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(message);
         }

         return message;
      } catch (InvalidProtocolBufferException e) {
         throw e;
      }
   }

   public MessageLite parsePartialFrom(byte[] data, int off, int len) throws InvalidProtocolBufferException {
      return this.parsePartialFrom(data, off, len, EMPTY_REGISTRY);
   }

   public MessageLite parsePartialFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return this.parsePartialFrom(data, 0, data.length, extensionRegistry);
   }

   public MessageLite parsePartialFrom(byte[] data) throws InvalidProtocolBufferException {
      return this.parsePartialFrom(data, 0, data.length, EMPTY_REGISTRY);
   }

   public MessageLite parseFrom(byte[] data, int off, int len, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return this.checkMessageInitialized(this.parsePartialFrom(data, off, len, extensionRegistry));
   }

   public MessageLite parseFrom(byte[] data, int off, int len) throws InvalidProtocolBufferException {
      return this.parseFrom(data, off, len, EMPTY_REGISTRY);
   }

   public MessageLite parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return this.parseFrom(data, 0, data.length, extensionRegistry);
   }

   public MessageLite parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return this.parseFrom(data, EMPTY_REGISTRY);
   }

   public MessageLite parsePartialFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      CodedInputStream codedInput = CodedInputStream.newInstance(input);
      MessageType message = (MessageType)((MessageLite)this.parsePartialFrom((CodedInputStream)codedInput, extensionRegistry));

      try {
         codedInput.checkLastTagWas(0);
         return message;
      } catch (InvalidProtocolBufferException e) {
         throw e.setUnfinishedMessage(message);
      }
   }

   public MessageLite parsePartialFrom(InputStream input) throws InvalidProtocolBufferException {
      return this.parsePartialFrom(input, EMPTY_REGISTRY);
   }

   public MessageLite parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return this.checkMessageInitialized(this.parsePartialFrom(input, extensionRegistry));
   }

   public MessageLite parseFrom(InputStream input) throws InvalidProtocolBufferException {
      return this.parseFrom(input, EMPTY_REGISTRY);
   }

   public MessageLite parsePartialDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      int size;
      try {
         int firstByte = input.read();
         if (firstByte == -1) {
            return null;
         }

         size = CodedInputStream.readRawVarint32(firstByte, input);
      } catch (IOException e) {
         throw new InvalidProtocolBufferException(e);
      }

      InputStream limitedInput = new AbstractMessageLite.Builder.LimitedInputStream(input, size);
      return this.parsePartialFrom(limitedInput, extensionRegistry);
   }

   public MessageLite parsePartialDelimitedFrom(InputStream input) throws InvalidProtocolBufferException {
      return this.parsePartialDelimitedFrom(input, EMPTY_REGISTRY);
   }

   public MessageLite parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return this.checkMessageInitialized(this.parsePartialDelimitedFrom(input, extensionRegistry));
   }

   public MessageLite parseDelimitedFrom(InputStream input) throws InvalidProtocolBufferException {
      return this.parseDelimitedFrom(input, EMPTY_REGISTRY);
   }
}
