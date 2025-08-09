package org.sparkproject.spark_core.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class GeneratedMessageLite extends AbstractMessageLite {
   static final int UNINITIALIZED_SERIALIZED_SIZE = Integer.MAX_VALUE;
   private static final int MUTABLE_FLAG_MASK = Integer.MIN_VALUE;
   private static final int MEMOIZED_SERIALIZED_SIZE_MASK = Integer.MAX_VALUE;
   private int memoizedSerializedSize = -1;
   static final int UNINITIALIZED_HASH_CODE = 0;
   protected UnknownFieldSetLite unknownFields = UnknownFieldSetLite.getDefaultInstance();
   private static Map defaultInstanceMap = new ConcurrentHashMap();

   boolean isMutable() {
      return (this.memoizedSerializedSize & Integer.MIN_VALUE) != 0;
   }

   void markImmutable() {
      this.memoizedSerializedSize &= Integer.MAX_VALUE;
   }

   int getMemoizedHashCode() {
      return this.memoizedHashCode;
   }

   void setMemoizedHashCode(int value) {
      this.memoizedHashCode = value;
   }

   void clearMemoizedHashCode() {
      this.memoizedHashCode = 0;
   }

   boolean hashCodeIsNotMemoized() {
      return 0 == this.getMemoizedHashCode();
   }

   public final Parser getParserForType() {
      return (Parser)this.dynamicMethod(GeneratedMessageLite.MethodToInvoke.GET_PARSER);
   }

   public final GeneratedMessageLite getDefaultInstanceForType() {
      return (GeneratedMessageLite)this.dynamicMethod(GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE);
   }

   public final Builder newBuilderForType() {
      return (Builder)this.dynamicMethod(GeneratedMessageLite.MethodToInvoke.NEW_BUILDER);
   }

   GeneratedMessageLite newMutableInstance() {
      return (GeneratedMessageLite)this.dynamicMethod(GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE);
   }

   public String toString() {
      return MessageLiteToString.toString(this, super.toString());
   }

   public int hashCode() {
      if (this.isMutable()) {
         return this.computeHashCode();
      } else {
         if (this.hashCodeIsNotMemoized()) {
            this.setMemoizedHashCode(this.computeHashCode());
         }

         return this.getMemoizedHashCode();
      }
   }

   int computeHashCode() {
      return Protobuf.getInstance().schemaFor((Object)this).hashCode(this);
   }

   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (other == null) {
         return false;
      } else {
         return this.getClass() != other.getClass() ? false : Protobuf.getInstance().schemaFor((Object)this).equals(this, (GeneratedMessageLite)other);
      }
   }

   private void ensureUnknownFieldsInitialized() {
      if (this.unknownFields == UnknownFieldSetLite.getDefaultInstance()) {
         this.unknownFields = UnknownFieldSetLite.newInstance();
      }

   }

   protected boolean parseUnknownField(int tag, CodedInputStream input) throws IOException {
      if (WireFormat.getTagWireType(tag) == 4) {
         return false;
      } else {
         this.ensureUnknownFieldsInitialized();
         return this.unknownFields.mergeFieldFrom(tag, input);
      }
   }

   protected void mergeVarintField(int tag, int value) {
      this.ensureUnknownFieldsInitialized();
      this.unknownFields.mergeVarintField(tag, value);
   }

   protected void mergeLengthDelimitedField(int fieldNumber, ByteString value) {
      this.ensureUnknownFieldsInitialized();
      this.unknownFields.mergeLengthDelimitedField(fieldNumber, value);
   }

   protected void makeImmutable() {
      Protobuf.getInstance().schemaFor((Object)this).makeImmutable(this);
      this.markImmutable();
   }

   protected final Builder createBuilder() {
      return (Builder)this.dynamicMethod(GeneratedMessageLite.MethodToInvoke.NEW_BUILDER);
   }

   protected final Builder createBuilder(GeneratedMessageLite prototype) {
      return this.createBuilder().mergeFrom(prototype);
   }

   public final boolean isInitialized() {
      return isInitialized(this, true);
   }

   public final Builder toBuilder() {
      BuilderType builder = (BuilderType)((Builder)this.dynamicMethod(GeneratedMessageLite.MethodToInvoke.NEW_BUILDER));
      return builder.mergeFrom(this);
   }

   protected abstract Object dynamicMethod(MethodToInvoke method, Object arg0, Object arg1);

   @CanIgnoreReturnValue
   protected Object dynamicMethod(MethodToInvoke method, Object arg0) {
      return this.dynamicMethod(method, arg0, (Object)null);
   }

   protected Object dynamicMethod(MethodToInvoke method) {
      return this.dynamicMethod(method, (Object)null, (Object)null);
   }

   void clearMemoizedSerializedSize() {
      this.setMemoizedSerializedSize(Integer.MAX_VALUE);
   }

   int getMemoizedSerializedSize() {
      return this.memoizedSerializedSize & Integer.MAX_VALUE;
   }

   void setMemoizedSerializedSize(int size) {
      if (size < 0) {
         throw new IllegalStateException("serialized size must be non-negative, was " + size);
      } else {
         this.memoizedSerializedSize = this.memoizedSerializedSize & Integer.MIN_VALUE | size & Integer.MAX_VALUE;
      }
   }

   public void writeTo(CodedOutputStream output) throws IOException {
      Protobuf.getInstance().schemaFor((Object)this).writeTo(this, CodedOutputStreamWriter.forCodedOutput(output));
   }

   int getSerializedSize(Schema schema) {
      if (this.isMutable()) {
         int size = this.computeSerializedSize(schema);
         if (size < 0) {
            throw new IllegalStateException("serialized size must be non-negative, was " + size);
         } else {
            return size;
         }
      } else if (this.getMemoizedSerializedSize() != Integer.MAX_VALUE) {
         return this.getMemoizedSerializedSize();
      } else {
         int size = this.computeSerializedSize(schema);
         this.setMemoizedSerializedSize(size);
         return size;
      }
   }

   public int getSerializedSize() {
      return this.getSerializedSize((Schema)null);
   }

   private int computeSerializedSize(Schema nullableSchema) {
      return nullableSchema == null ? Protobuf.getInstance().schemaFor((Object)this).getSerializedSize(this) : nullableSchema.getSerializedSize(this);
   }

   Object buildMessageInfo() throws Exception {
      return this.dynamicMethod(GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO);
   }

   static GeneratedMessageLite getDefaultInstance(Class clazz) {
      T result = (T)((GeneratedMessageLite)defaultInstanceMap.get(clazz));
      if (result == null) {
         try {
            Class.forName(clazz.getName(), true, clazz.getClassLoader());
         } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Class initialization cannot fail.", e);
         }

         result = (T)((GeneratedMessageLite)defaultInstanceMap.get(clazz));
      }

      if (result == null) {
         result = (T)((GeneratedMessageLite)UnsafeUtil.allocateInstance(clazz)).getDefaultInstanceForType();
         if (result == null) {
            throw new IllegalStateException();
         }

         defaultInstanceMap.put(clazz, result);
      }

      return result;
   }

   protected static void registerDefaultInstance(Class clazz, GeneratedMessageLite defaultInstance) {
      defaultInstance.markImmutable();
      defaultInstanceMap.put(clazz, defaultInstance);
   }

   protected static Object newMessageInfo(MessageLite defaultInstance, String info, Object[] objects) {
      return new RawMessageInfo(defaultInstance, info, objects);
   }

   protected final void mergeUnknownFields(UnknownFieldSetLite unknownFields) {
      this.unknownFields = UnknownFieldSetLite.mutableCopyOf(this.unknownFields, unknownFields);
   }

   public static GeneratedExtension newSingularGeneratedExtension(final MessageLite containingTypeDefaultInstance, final Object defaultValue, final MessageLite messageDefaultInstance, final Internal.EnumLiteMap enumTypeMap, final int number, final WireFormat.FieldType type, final Class singularType) {
      return new GeneratedExtension(containingTypeDefaultInstance, defaultValue, messageDefaultInstance, new ExtensionDescriptor(enumTypeMap, number, type, false, false), singularType);
   }

   public static GeneratedExtension newRepeatedGeneratedExtension(final MessageLite containingTypeDefaultInstance, final MessageLite messageDefaultInstance, final Internal.EnumLiteMap enumTypeMap, final int number, final WireFormat.FieldType type, final boolean isPacked, final Class singularType) {
      Type emptyList = (Type)ProtobufArrayList.emptyList();
      return new GeneratedExtension(containingTypeDefaultInstance, emptyList, messageDefaultInstance, new ExtensionDescriptor(enumTypeMap, number, type, true, isPacked), singularType);
   }

   static java.lang.reflect.Method getMethodOrDie(Class clazz, String name, Class... params) {
      try {
         return clazz.getMethod(name, params);
      } catch (NoSuchMethodException e) {
         throw new RuntimeException("Generated message class \"" + clazz.getName() + "\" missing method \"" + name + "\".", e);
      }
   }

   static Object invokeOrDie(java.lang.reflect.Method method, Object object, Object... params) {
      try {
         return method.invoke(object, params);
      } catch (IllegalAccessException e) {
         throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
      } catch (InvocationTargetException e) {
         Throwable cause = e.getCause();
         if (cause instanceof RuntimeException) {
            throw (RuntimeException)cause;
         } else if (cause instanceof Error) {
            throw (Error)cause;
         } else {
            throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
         }
      }
   }

   private static GeneratedExtension checkIsLite(ExtensionLite extension) {
      if (!extension.isLite()) {
         throw new IllegalArgumentException("Expected a lite extension.");
      } else {
         return (GeneratedExtension)extension;
      }
   }

   protected static final boolean isInitialized(GeneratedMessageLite message, boolean shouldMemoize) {
      byte memoizedIsInitialized = (Byte)message.dynamicMethod(GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED);
      if (memoizedIsInitialized == 1) {
         return true;
      } else if (memoizedIsInitialized == 0) {
         return false;
      } else {
         boolean isInitialized = Protobuf.getInstance().schemaFor((Object)message).isInitialized(message);
         if (shouldMemoize) {
            message.dynamicMethod(GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED, isInitialized ? message : null);
         }

         return isInitialized;
      }
   }

   protected static Internal.IntList emptyIntList() {
      return IntArrayList.emptyList();
   }

   protected static Internal.IntList mutableCopy(Internal.IntList list) {
      int size = list.size();
      return list.mutableCopyWithCapacity(size * 2);
   }

   protected static Internal.LongList emptyLongList() {
      return LongArrayList.emptyList();
   }

   protected static Internal.LongList mutableCopy(Internal.LongList list) {
      int size = list.size();
      return list.mutableCopyWithCapacity(size * 2);
   }

   protected static Internal.FloatList emptyFloatList() {
      return FloatArrayList.emptyList();
   }

   protected static Internal.FloatList mutableCopy(Internal.FloatList list) {
      int size = list.size();
      return list.mutableCopyWithCapacity(size * 2);
   }

   protected static Internal.DoubleList emptyDoubleList() {
      return DoubleArrayList.emptyList();
   }

   protected static Internal.DoubleList mutableCopy(Internal.DoubleList list) {
      int size = list.size();
      return list.mutableCopyWithCapacity(size * 2);
   }

   protected static Internal.BooleanList emptyBooleanList() {
      return BooleanArrayList.emptyList();
   }

   protected static Internal.BooleanList mutableCopy(Internal.BooleanList list) {
      int size = list.size();
      return list.mutableCopyWithCapacity(size * 2);
   }

   protected static Internal.ProtobufList emptyProtobufList() {
      return ProtobufArrayList.emptyList();
   }

   protected static Internal.ProtobufList mutableCopy(Internal.ProtobufList list) {
      int size = list.size();
      return list.mutableCopyWithCapacity(size * 2);
   }

   static GeneratedMessageLite parsePartialFrom(GeneratedMessageLite instance, CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      T result = (T)instance.newMutableInstance();

      try {
         Schema<T> schema = Protobuf.getInstance().schemaFor((Object)result);
         schema.mergeFrom(result, CodedInputStreamReader.forCodedInput(input), extensionRegistry);
         schema.makeImmutable(result);
         return result;
      } catch (InvalidProtocolBufferException var5) {
         InvalidProtocolBufferException e = var5;
         if (var5.getThrownFromInputStream()) {
            e = new InvalidProtocolBufferException(var5);
         }

         throw e.setUnfinishedMessage(result);
      } catch (UninitializedMessageException e) {
         throw e.asInvalidProtocolBufferException().setUnfinishedMessage(result);
      } catch (IOException e) {
         if (e.getCause() instanceof InvalidProtocolBufferException) {
            throw (InvalidProtocolBufferException)e.getCause();
         } else {
            throw (new InvalidProtocolBufferException(e)).setUnfinishedMessage(result);
         }
      } catch (RuntimeException e) {
         if (e.getCause() instanceof InvalidProtocolBufferException) {
            throw (InvalidProtocolBufferException)e.getCause();
         } else {
            throw e;
         }
      }
   }

   private static GeneratedMessageLite parsePartialFrom(GeneratedMessageLite defaultInstance, byte[] input, int offset, int length, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      if (length == 0) {
         return defaultInstance;
      } else {
         T result = (T)defaultInstance.newMutableInstance();

         try {
            Schema<T> schema = Protobuf.getInstance().schemaFor((Object)result);
            schema.mergeFrom(result, input, offset, offset + length, new ArrayDecoders.Registers(extensionRegistry));
            schema.makeImmutable(result);
            return result;
         } catch (InvalidProtocolBufferException var7) {
            InvalidProtocolBufferException e = var7;
            if (var7.getThrownFromInputStream()) {
               e = new InvalidProtocolBufferException(var7);
            }

            throw e.setUnfinishedMessage(result);
         } catch (UninitializedMessageException e) {
            throw e.asInvalidProtocolBufferException().setUnfinishedMessage(result);
         } catch (IOException e) {
            if (e.getCause() instanceof InvalidProtocolBufferException) {
               throw (InvalidProtocolBufferException)e.getCause();
            } else {
               throw (new InvalidProtocolBufferException(e)).setUnfinishedMessage(result);
            }
         } catch (IndexOutOfBoundsException var10) {
            throw InvalidProtocolBufferException.truncatedMessage().setUnfinishedMessage(result);
         }
      }
   }

   protected static GeneratedMessageLite parsePartialFrom(GeneratedMessageLite defaultInstance, CodedInputStream input) throws InvalidProtocolBufferException {
      return parsePartialFrom(defaultInstance, input, ExtensionRegistryLite.getEmptyRegistry());
   }

   private static GeneratedMessageLite checkMessageInitialized(GeneratedMessageLite message) throws InvalidProtocolBufferException {
      if (message != null && !message.isInitialized()) {
         throw message.newUninitializedMessageException().asInvalidProtocolBufferException().setUnfinishedMessage(message);
      } else {
         return message;
      }
   }

   protected static GeneratedMessageLite parseFrom(GeneratedMessageLite defaultInstance, ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return checkMessageInitialized(parseFrom(defaultInstance, CodedInputStream.newInstance(data), extensionRegistry));
   }

   protected static GeneratedMessageLite parseFrom(GeneratedMessageLite defaultInstance, ByteBuffer data) throws InvalidProtocolBufferException {
      return parseFrom(defaultInstance, data, ExtensionRegistryLite.getEmptyRegistry());
   }

   protected static GeneratedMessageLite parseFrom(GeneratedMessageLite defaultInstance, ByteString data) throws InvalidProtocolBufferException {
      return checkMessageInitialized(parseFrom(defaultInstance, data, ExtensionRegistryLite.getEmptyRegistry()));
   }

   protected static GeneratedMessageLite parseFrom(GeneratedMessageLite defaultInstance, ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return checkMessageInitialized(parsePartialFrom(defaultInstance, data, extensionRegistry));
   }

   private static GeneratedMessageLite parsePartialFrom(GeneratedMessageLite defaultInstance, ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      CodedInputStream input = data.newCodedInput();
      T message = (T)parsePartialFrom(defaultInstance, input, extensionRegistry);

      try {
         input.checkLastTagWas(0);
         return message;
      } catch (InvalidProtocolBufferException e) {
         throw e.setUnfinishedMessage(message);
      }
   }

   protected static GeneratedMessageLite parseFrom(GeneratedMessageLite defaultInstance, byte[] data) throws InvalidProtocolBufferException {
      return checkMessageInitialized(parsePartialFrom(defaultInstance, data, 0, data.length, ExtensionRegistryLite.getEmptyRegistry()));
   }

   protected static GeneratedMessageLite parseFrom(GeneratedMessageLite defaultInstance, byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return checkMessageInitialized(parsePartialFrom(defaultInstance, data, 0, data.length, extensionRegistry));
   }

   protected static GeneratedMessageLite parseFrom(GeneratedMessageLite defaultInstance, InputStream input) throws InvalidProtocolBufferException {
      return checkMessageInitialized(parsePartialFrom(defaultInstance, CodedInputStream.newInstance(input), ExtensionRegistryLite.getEmptyRegistry()));
   }

   protected static GeneratedMessageLite parseFrom(GeneratedMessageLite defaultInstance, InputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return checkMessageInitialized(parsePartialFrom(defaultInstance, CodedInputStream.newInstance(input), extensionRegistry));
   }

   protected static GeneratedMessageLite parseFrom(GeneratedMessageLite defaultInstance, CodedInputStream input) throws InvalidProtocolBufferException {
      return parseFrom(defaultInstance, input, ExtensionRegistryLite.getEmptyRegistry());
   }

   protected static GeneratedMessageLite parseFrom(GeneratedMessageLite defaultInstance, CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return checkMessageInitialized(parsePartialFrom(defaultInstance, input, extensionRegistry));
   }

   protected static GeneratedMessageLite parseDelimitedFrom(GeneratedMessageLite defaultInstance, InputStream input) throws InvalidProtocolBufferException {
      return checkMessageInitialized(parsePartialDelimitedFrom(defaultInstance, input, ExtensionRegistryLite.getEmptyRegistry()));
   }

   protected static GeneratedMessageLite parseDelimitedFrom(GeneratedMessageLite defaultInstance, InputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return checkMessageInitialized(parsePartialDelimitedFrom(defaultInstance, input, extensionRegistry));
   }

   private static GeneratedMessageLite parsePartialDelimitedFrom(GeneratedMessageLite defaultInstance, InputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      int size;
      try {
         int firstByte = input.read();
         if (firstByte == -1) {
            return null;
         }

         size = CodedInputStream.readRawVarint32(firstByte, input);
      } catch (InvalidProtocolBufferException var9) {
         InvalidProtocolBufferException e = var9;
         if (var9.getThrownFromInputStream()) {
            e = new InvalidProtocolBufferException(var9);
         }

         throw e;
      } catch (IOException e) {
         throw new InvalidProtocolBufferException(e);
      }

      InputStream limitedInput = new AbstractMessageLite.Builder.LimitedInputStream(input, size);
      CodedInputStream codedInput = CodedInputStream.newInstance(limitedInput);
      T message = (T)parsePartialFrom(defaultInstance, codedInput, extensionRegistry);

      try {
         codedInput.checkLastTagWas(0);
         return message;
      } catch (InvalidProtocolBufferException e) {
         throw e.setUnfinishedMessage(message);
      }
   }

   public static enum MethodToInvoke {
      GET_MEMOIZED_IS_INITIALIZED,
      SET_MEMOIZED_IS_INITIALIZED,
      BUILD_MESSAGE_INFO,
      NEW_MUTABLE_INSTANCE,
      NEW_BUILDER,
      GET_DEFAULT_INSTANCE,
      GET_PARSER;

      // $FF: synthetic method
      private static MethodToInvoke[] $values() {
         return new MethodToInvoke[]{GET_MEMOIZED_IS_INITIALIZED, SET_MEMOIZED_IS_INITIALIZED, BUILD_MESSAGE_INFO, NEW_MUTABLE_INSTANCE, NEW_BUILDER, GET_DEFAULT_INSTANCE, GET_PARSER};
      }
   }

   public abstract static class Builder extends AbstractMessageLite.Builder {
      private final GeneratedMessageLite defaultInstance;
      protected GeneratedMessageLite instance;

      protected Builder(GeneratedMessageLite defaultInstance) {
         this.defaultInstance = defaultInstance;
         if (defaultInstance.isMutable()) {
            throw new IllegalArgumentException("Default instance must be immutable.");
         } else {
            this.instance = this.newMutableInstance();
         }
      }

      private GeneratedMessageLite newMutableInstance() {
         return this.defaultInstance.newMutableInstance();
      }

      protected final void copyOnWrite() {
         if (!this.instance.isMutable()) {
            this.copyOnWriteInternal();
         }

      }

      protected void copyOnWriteInternal() {
         MessageType newInstance = (MessageType)this.newMutableInstance();
         mergeFromInstance(newInstance, this.instance);
         this.instance = newInstance;
      }

      public final boolean isInitialized() {
         return GeneratedMessageLite.isInitialized(this.instance, false);
      }

      public final Builder clear() {
         if (this.defaultInstance.isMutable()) {
            throw new IllegalArgumentException("Default instance must be immutable.");
         } else {
            this.instance = this.newMutableInstance();
            return this;
         }
      }

      public Builder clone() {
         BuilderType builder = (BuilderType)this.getDefaultInstanceForType().newBuilderForType();
         builder.instance = this.buildPartial();
         return builder;
      }

      public GeneratedMessageLite buildPartial() {
         if (!this.instance.isMutable()) {
            return this.instance;
         } else {
            this.instance.makeImmutable();
            return this.instance;
         }
      }

      public final GeneratedMessageLite build() {
         MessageType result = (MessageType)this.buildPartial();
         if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
         } else {
            return result;
         }
      }

      protected Builder internalMergeFrom(GeneratedMessageLite message) {
         return this.mergeFrom(message);
      }

      public Builder mergeFrom(GeneratedMessageLite message) {
         if (this.getDefaultInstanceForType().equals(message)) {
            return this;
         } else {
            this.copyOnWrite();
            mergeFromInstance(this.instance, message);
            return this;
         }
      }

      private static void mergeFromInstance(Object dest, Object src) {
         Protobuf.getInstance().schemaFor(dest).mergeFrom(dest, src);
      }

      public GeneratedMessageLite getDefaultInstanceForType() {
         return this.defaultInstance;
      }

      public Builder mergeFrom(byte[] input, int offset, int length, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
         this.copyOnWrite();

         try {
            Protobuf.getInstance().schemaFor((Object)this.instance).mergeFrom(this.instance, input, offset, offset + length, new ArrayDecoders.Registers(extensionRegistry));
            return this;
         } catch (InvalidProtocolBufferException e) {
            throw e;
         } catch (IndexOutOfBoundsException var7) {
            throw InvalidProtocolBufferException.truncatedMessage();
         } catch (IOException e) {
            throw new RuntimeException("Reading from byte array should not throw IOException.", e);
         }
      }

      public Builder mergeFrom(byte[] input, int offset, int length) throws InvalidProtocolBufferException {
         return this.mergeFrom(input, offset, length, ExtensionRegistryLite.getEmptyRegistry());
      }

      public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
         this.copyOnWrite();

         try {
            Protobuf.getInstance().schemaFor((Object)this.instance).mergeFrom(this.instance, CodedInputStreamReader.forCodedInput(input), extensionRegistry);
            return this;
         } catch (RuntimeException e) {
            if (e.getCause() instanceof IOException) {
               throw (IOException)e.getCause();
            } else {
               throw e;
            }
         }
      }
   }

   public abstract static class ExtendableMessage extends GeneratedMessageLite implements ExtendableMessageOrBuilder {
      protected FieldSet extensions = FieldSet.emptySet();

      protected final void mergeExtensionFields(final ExtendableMessage other) {
         if (this.extensions.isImmutable()) {
            this.extensions = this.extensions.clone();
         }

         this.extensions.mergeFrom(other.extensions);
      }

      protected boolean parseUnknownField(MessageLite defaultInstance, CodedInputStream input, ExtensionRegistryLite extensionRegistry, int tag) throws IOException {
         int fieldNumber = WireFormat.getTagFieldNumber(tag);
         GeneratedExtension<MessageType, ?> extension = extensionRegistry.findLiteExtensionByNumber(defaultInstance, fieldNumber);
         return this.parseExtension(input, extensionRegistry, extension, tag, fieldNumber);
      }

      private boolean parseExtension(CodedInputStream input, ExtensionRegistryLite extensionRegistry, GeneratedExtension extension, int tag, int fieldNumber) throws IOException {
         int wireType = WireFormat.getTagWireType(tag);
         boolean unknown = false;
         boolean packed = false;
         if (extension == null) {
            unknown = true;
         } else if (wireType == FieldSet.getWireFormatForFieldType(extension.descriptor.getLiteType(), false)) {
            packed = false;
         } else if (extension.descriptor.isRepeated && extension.descriptor.type.isPackable() && wireType == FieldSet.getWireFormatForFieldType(extension.descriptor.getLiteType(), true)) {
            packed = true;
         } else {
            unknown = true;
         }

         if (unknown) {
            return this.parseUnknownField(tag, input);
         } else {
            FieldSet<ExtensionDescriptor> unused = this.ensureExtensionsAreMutable();
            if (packed) {
               int length = input.readRawVarint32();
               int limit = input.pushLimit(length);
               if (extension.descriptor.getLiteType() == WireFormat.FieldType.ENUM) {
                  while(input.getBytesUntilLimit() > 0) {
                     int rawValue = input.readEnum();
                     Object value = extension.descriptor.getEnumType().findValueByNumber(rawValue);
                     if (value == null) {
                        return true;
                     }

                     this.extensions.addRepeatedField(extension.descriptor, extension.singularToFieldSetType(value));
                  }
               } else {
                  while(input.getBytesUntilLimit() > 0) {
                     Object value = FieldSet.readPrimitiveField(input, extension.descriptor.getLiteType(), false);
                     this.extensions.addRepeatedField(extension.descriptor, value);
                  }
               }

               input.popLimit(limit);
            } else {
               Object value;
               switch (extension.descriptor.getLiteJavaType()) {
                  case MESSAGE:
                     MessageLite.Builder subBuilder = null;
                     if (!extension.descriptor.isRepeated()) {
                        MessageLite existingValue = (MessageLite)this.extensions.getField(extension.descriptor);
                        if (existingValue != null) {
                           subBuilder = existingValue.toBuilder();
                        }
                     }

                     if (subBuilder == null) {
                        subBuilder = extension.getMessageDefaultInstance().newBuilderForType();
                     }

                     if (extension.descriptor.getLiteType() == WireFormat.FieldType.GROUP) {
                        input.readGroup(extension.getNumber(), subBuilder, extensionRegistry);
                     } else {
                        input.readMessage(subBuilder, extensionRegistry);
                     }

                     value = subBuilder.build();
                     break;
                  case ENUM:
                     int rawValue = input.readEnum();
                     value = extension.descriptor.getEnumType().findValueByNumber(rawValue);
                     if (value == null) {
                        this.mergeVarintField(fieldNumber, rawValue);
                        return true;
                     }
                     break;
                  default:
                     value = FieldSet.readPrimitiveField(input, extension.descriptor.getLiteType(), false);
               }

               if (extension.descriptor.isRepeated()) {
                  this.extensions.addRepeatedField(extension.descriptor, extension.singularToFieldSetType(value));
               } else {
                  this.extensions.setField(extension.descriptor, extension.singularToFieldSetType(value));
               }
            }

            return true;
         }
      }

      protected boolean parseUnknownFieldAsMessageSet(MessageLite defaultInstance, CodedInputStream input, ExtensionRegistryLite extensionRegistry, int tag) throws IOException {
         if (tag == WireFormat.MESSAGE_SET_ITEM_TAG) {
            this.mergeMessageSetExtensionFromCodedStream(defaultInstance, input, extensionRegistry);
            return true;
         } else {
            int wireType = WireFormat.getTagWireType(tag);
            return wireType == 2 ? this.parseUnknownField(defaultInstance, input, extensionRegistry, tag) : input.skipField(tag);
         }
      }

      private void mergeMessageSetExtensionFromCodedStream(MessageLite defaultInstance, CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
         int typeId = 0;
         ByteString rawBytes = null;
         GeneratedExtension<?, ?> extension = null;

         while(true) {
            int tag = input.readTag();
            if (tag == 0) {
               break;
            }

            if (tag == WireFormat.MESSAGE_SET_TYPE_ID_TAG) {
               typeId = input.readUInt32();
               if (typeId != 0) {
                  extension = extensionRegistry.findLiteExtensionByNumber(defaultInstance, typeId);
               }
            } else if (tag == WireFormat.MESSAGE_SET_MESSAGE_TAG) {
               if (typeId != 0 && extension != null) {
                  this.eagerlyMergeMessageSetExtension(input, extension, extensionRegistry, typeId);
                  rawBytes = null;
               } else {
                  rawBytes = input.readBytes();
               }
            } else if (!input.skipField(tag)) {
               break;
            }
         }

         input.checkLastTagWas(WireFormat.MESSAGE_SET_ITEM_END_TAG);
         if (rawBytes != null && typeId != 0) {
            if (extension != null) {
               this.mergeMessageSetExtensionFromBytes(rawBytes, extensionRegistry, extension);
            } else if (rawBytes != null) {
               this.mergeLengthDelimitedField(typeId, rawBytes);
            }
         }

      }

      private void eagerlyMergeMessageSetExtension(CodedInputStream input, GeneratedExtension extension, ExtensionRegistryLite extensionRegistry, int typeId) throws IOException {
         int tag = WireFormat.makeTag(typeId, 2);
         this.parseExtension(input, extensionRegistry, extension, tag, typeId);
      }

      private void mergeMessageSetExtensionFromBytes(ByteString rawBytes, ExtensionRegistryLite extensionRegistry, GeneratedExtension extension) throws IOException {
         MessageLite.Builder subBuilder = null;
         MessageLite existingValue = (MessageLite)this.extensions.getField(extension.descriptor);
         if (existingValue != null) {
            subBuilder = existingValue.toBuilder();
         }

         if (subBuilder == null) {
            subBuilder = extension.getMessageDefaultInstance().newBuilderForType();
         }

         subBuilder.mergeFrom(rawBytes, extensionRegistry);
         MessageLite value = subBuilder.build();
         this.ensureExtensionsAreMutable().setField(extension.descriptor, extension.singularToFieldSetType(value));
      }

      @CanIgnoreReturnValue
      FieldSet ensureExtensionsAreMutable() {
         if (this.extensions.isImmutable()) {
            this.extensions = this.extensions.clone();
         }

         return this.extensions;
      }

      private void verifyExtensionContainingType(final GeneratedExtension extension) {
         if (extension.getContainingTypeDefaultInstance() != this.getDefaultInstanceForType()) {
            throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
         }
      }

      public final boolean hasExtension(final ExtensionLite extension) {
         GeneratedExtension<MessageType, Type> extensionLite = GeneratedMessageLite.checkIsLite(extension);
         this.verifyExtensionContainingType(extensionLite);
         return this.extensions.hasField(extensionLite.descriptor);
      }

      public final int getExtensionCount(final ExtensionLite extension) {
         GeneratedExtension<MessageType, List<Type>> extensionLite = GeneratedMessageLite.checkIsLite(extension);
         this.verifyExtensionContainingType(extensionLite);
         return this.extensions.getRepeatedFieldCount(extensionLite.descriptor);
      }

      public final Object getExtension(final ExtensionLite extension) {
         GeneratedExtension<MessageType, Type> extensionLite = GeneratedMessageLite.checkIsLite(extension);
         this.verifyExtensionContainingType(extensionLite);
         Object value = this.extensions.getField(extensionLite.descriptor);
         return value == null ? extensionLite.defaultValue : extensionLite.fromFieldSetType(value);
      }

      public final Object getExtension(final ExtensionLite extension, final int index) {
         GeneratedExtension<MessageType, List<Type>> extensionLite = GeneratedMessageLite.checkIsLite(extension);
         this.verifyExtensionContainingType(extensionLite);
         return extensionLite.singularFromFieldSetType(this.extensions.getRepeatedField(extensionLite.descriptor, index));
      }

      protected boolean extensionsAreInitialized() {
         return this.extensions.isInitialized();
      }

      protected ExtensionWriter newExtensionWriter() {
         return new ExtensionWriter(false);
      }

      protected ExtensionWriter newMessageSetExtensionWriter() {
         return new ExtensionWriter(true);
      }

      protected int extensionsSerializedSize() {
         return this.extensions.getSerializedSize();
      }

      protected int extensionsSerializedSizeAsMessageSet() {
         return this.extensions.getMessageSetSerializedSize();
      }

      protected class ExtensionWriter {
         private final Iterator iter;
         private Map.Entry next;
         private final boolean messageSetWireFormat;

         private ExtensionWriter(boolean messageSetWireFormat) {
            this.iter = ExtendableMessage.this.extensions.iterator();
            if (this.iter.hasNext()) {
               this.next = (Map.Entry)this.iter.next();
            }

            this.messageSetWireFormat = messageSetWireFormat;
         }

         public void writeUntil(final int end, final CodedOutputStream output) throws IOException {
            while(this.next != null && ((ExtensionDescriptor)this.next.getKey()).getNumber() < end) {
               ExtensionDescriptor extension = (ExtensionDescriptor)this.next.getKey();
               if (this.messageSetWireFormat && extension.getLiteJavaType() == WireFormat.JavaType.MESSAGE && !extension.isRepeated()) {
                  output.writeMessageSetExtension(extension.getNumber(), (MessageLite)this.next.getValue());
               } else {
                  FieldSet.writeField(extension, this.next.getValue(), output);
               }

               if (this.iter.hasNext()) {
                  this.next = (Map.Entry)this.iter.next();
               } else {
                  this.next = null;
               }
            }

         }
      }
   }

   public abstract static class ExtendableBuilder extends Builder implements ExtendableMessageOrBuilder {
      protected ExtendableBuilder(ExtendableMessage defaultInstance) {
         super(defaultInstance);
      }

      void internalSetExtensionSet(FieldSet extensions) {
         this.copyOnWrite();
         ((ExtendableMessage)this.instance).extensions = extensions;
      }

      protected void copyOnWriteInternal() {
         super.copyOnWriteInternal();
         if (((ExtendableMessage)this.instance).extensions != FieldSet.emptySet()) {
            ((ExtendableMessage)this.instance).extensions = ((ExtendableMessage)this.instance).extensions.clone();
         }

      }

      private FieldSet ensureExtensionsAreMutable() {
         FieldSet<ExtensionDescriptor> extensions = ((ExtendableMessage)this.instance).extensions;
         if (extensions.isImmutable()) {
            extensions = extensions.clone();
            ((ExtendableMessage)this.instance).extensions = extensions;
         }

         return extensions;
      }

      public final ExtendableMessage buildPartial() {
         if (!((ExtendableMessage)this.instance).isMutable()) {
            return (ExtendableMessage)this.instance;
         } else {
            ((ExtendableMessage)this.instance).extensions.makeImmutable();
            return (ExtendableMessage)super.buildPartial();
         }
      }

      private void verifyExtensionContainingType(final GeneratedExtension extension) {
         if (extension.getContainingTypeDefaultInstance() != this.getDefaultInstanceForType()) {
            throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
         }
      }

      public final boolean hasExtension(final ExtensionLite extension) {
         return ((ExtendableMessage)this.instance).hasExtension(extension);
      }

      public final int getExtensionCount(final ExtensionLite extension) {
         return ((ExtendableMessage)this.instance).getExtensionCount(extension);
      }

      public final Object getExtension(final ExtensionLite extension) {
         return ((ExtendableMessage)this.instance).getExtension(extension);
      }

      public final Object getExtension(final ExtensionLite extension, final int index) {
         return ((ExtendableMessage)this.instance).getExtension(extension, index);
      }

      public final ExtendableBuilder setExtension(final ExtensionLite extension, final Object value) {
         GeneratedExtension<MessageType, Type> extensionLite = GeneratedMessageLite.checkIsLite(extension);
         this.verifyExtensionContainingType(extensionLite);
         this.copyOnWrite();
         this.ensureExtensionsAreMutable().setField(extensionLite.descriptor, extensionLite.toFieldSetType(value));
         return this;
      }

      public final ExtendableBuilder setExtension(final ExtensionLite extension, final int index, final Object value) {
         GeneratedExtension<MessageType, List<Type>> extensionLite = GeneratedMessageLite.checkIsLite(extension);
         this.verifyExtensionContainingType(extensionLite);
         this.copyOnWrite();
         this.ensureExtensionsAreMutable().setRepeatedField(extensionLite.descriptor, index, extensionLite.singularToFieldSetType(value));
         return this;
      }

      public final ExtendableBuilder addExtension(final ExtensionLite extension, final Object value) {
         GeneratedExtension<MessageType, List<Type>> extensionLite = GeneratedMessageLite.checkIsLite(extension);
         this.verifyExtensionContainingType(extensionLite);
         this.copyOnWrite();
         this.ensureExtensionsAreMutable().addRepeatedField(extensionLite.descriptor, extensionLite.singularToFieldSetType(value));
         return this;
      }

      public final ExtendableBuilder clearExtension(final ExtensionLite extension) {
         GeneratedExtension<MessageType, ?> extensionLite = GeneratedMessageLite.checkIsLite(extension);
         this.verifyExtensionContainingType(extensionLite);
         this.copyOnWrite();
         this.ensureExtensionsAreMutable().clearField(extensionLite.descriptor);
         return this;
      }
   }

   static final class ExtensionDescriptor implements FieldSet.FieldDescriptorLite {
      final Internal.EnumLiteMap enumTypeMap;
      final int number;
      final WireFormat.FieldType type;
      final boolean isRepeated;
      final boolean isPacked;

      ExtensionDescriptor(final Internal.EnumLiteMap enumTypeMap, final int number, final WireFormat.FieldType type, final boolean isRepeated, final boolean isPacked) {
         this.enumTypeMap = enumTypeMap;
         this.number = number;
         this.type = type;
         this.isRepeated = isRepeated;
         this.isPacked = isPacked;
      }

      public int getNumber() {
         return this.number;
      }

      public WireFormat.FieldType getLiteType() {
         return this.type;
      }

      public WireFormat.JavaType getLiteJavaType() {
         return this.type.getJavaType();
      }

      public boolean isRepeated() {
         return this.isRepeated;
      }

      public boolean isPacked() {
         return this.isPacked;
      }

      public Internal.EnumLiteMap getEnumType() {
         return this.enumTypeMap;
      }

      public MessageLite.Builder internalMergeFrom(MessageLite.Builder to, MessageLite from) {
         return ((Builder)to).mergeFrom((GeneratedMessageLite)from);
      }

      public int compareTo(ExtensionDescriptor other) {
         return this.number - other.number;
      }
   }

   public static class GeneratedExtension extends ExtensionLite {
      final MessageLite containingTypeDefaultInstance;
      final Object defaultValue;
      final MessageLite messageDefaultInstance;
      final ExtensionDescriptor descriptor;

      GeneratedExtension(final MessageLite containingTypeDefaultInstance, final Object defaultValue, final MessageLite messageDefaultInstance, final ExtensionDescriptor descriptor, final Class singularType) {
         if (containingTypeDefaultInstance == null) {
            throw new IllegalArgumentException("Null containingTypeDefaultInstance");
         } else if (descriptor.getLiteType() == WireFormat.FieldType.MESSAGE && messageDefaultInstance == null) {
            throw new IllegalArgumentException("Null messageDefaultInstance");
         } else {
            this.containingTypeDefaultInstance = containingTypeDefaultInstance;
            this.defaultValue = defaultValue;
            this.messageDefaultInstance = messageDefaultInstance;
            this.descriptor = descriptor;
         }
      }

      public MessageLite getContainingTypeDefaultInstance() {
         return this.containingTypeDefaultInstance;
      }

      public int getNumber() {
         return this.descriptor.getNumber();
      }

      public MessageLite getMessageDefaultInstance() {
         return this.messageDefaultInstance;
      }

      Object fromFieldSetType(Object value) {
         if (!this.descriptor.isRepeated()) {
            return this.singularFromFieldSetType(value);
         } else if (this.descriptor.getLiteJavaType() != WireFormat.JavaType.ENUM) {
            return value;
         } else {
            ProtobufArrayList<Object> result = new ProtobufArrayList();
            result.ensureCapacity(((List)value).size());

            for(Object element : (List)value) {
               result.add(this.singularFromFieldSetType(element));
            }

            result.makeImmutable();
            return result;
         }
      }

      Object singularFromFieldSetType(Object value) {
         return this.descriptor.getLiteJavaType() == WireFormat.JavaType.ENUM ? this.descriptor.enumTypeMap.findValueByNumber((Integer)value) : value;
      }

      Object toFieldSetType(Object value) {
         if (!this.descriptor.isRepeated()) {
            return this.singularToFieldSetType(value);
         } else if (this.descriptor.getLiteJavaType() != WireFormat.JavaType.ENUM) {
            return value;
         } else {
            List<Object> result = new ArrayList();

            for(Object element : (List)value) {
               result.add(this.singularToFieldSetType(element));
            }

            return result;
         }
      }

      Object singularToFieldSetType(Object value) {
         return this.descriptor.getLiteJavaType() == WireFormat.JavaType.ENUM ? ((Internal.EnumLite)value).getNumber() : value;
      }

      public WireFormat.FieldType getLiteType() {
         return this.descriptor.getLiteType();
      }

      public boolean isRepeated() {
         return this.descriptor.isRepeated;
      }

      public Object getDefaultValue() {
         return this.defaultValue;
      }
   }

   protected static final class SerializedForm implements Serializable {
      private static final long serialVersionUID = 0L;
      private final Class messageClass;
      private final String messageClassName;
      private final byte[] asBytes;

      public static SerializedForm of(MessageLite message) {
         return new SerializedForm(message);
      }

      SerializedForm(MessageLite regularForm) {
         this.messageClass = regularForm.getClass();
         this.messageClassName = regularForm.getClass().getName();
         this.asBytes = regularForm.toByteArray();
      }

      protected Object readResolve() throws ObjectStreamException {
         try {
            Class<?> messageClass = this.resolveMessageClass();
            java.lang.reflect.Field defaultInstanceField = messageClass.getDeclaredField("DEFAULT_INSTANCE");
            defaultInstanceField.setAccessible(true);
            MessageLite defaultInstance = (MessageLite)defaultInstanceField.get((Object)null);
            return defaultInstance.newBuilderForType().mergeFrom(this.asBytes).buildPartial();
         } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to find proto buffer class: " + this.messageClassName, e);
         } catch (NoSuchFieldException e) {
            throw new RuntimeException("Unable to find DEFAULT_INSTANCE in " + this.messageClassName, e);
         } catch (SecurityException e) {
            throw new RuntimeException("Unable to call DEFAULT_INSTANCE in " + this.messageClassName, e);
         } catch (IllegalAccessException e) {
            throw new RuntimeException("Unable to call parsePartialFrom", e);
         } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException("Unable to understand proto buffer", e);
         }
      }

      private Class resolveMessageClass() throws ClassNotFoundException {
         return this.messageClass != null ? this.messageClass : Class.forName(this.messageClassName);
      }
   }

   protected static class DefaultInstanceBasedParser extends AbstractParser {
      private final GeneratedMessageLite defaultInstance;

      public DefaultInstanceBasedParser(GeneratedMessageLite defaultInstance) {
         this.defaultInstance = defaultInstance;
      }

      public GeneratedMessageLite parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
         return GeneratedMessageLite.parsePartialFrom(this.defaultInstance, input, extensionRegistry);
      }

      public GeneratedMessageLite parsePartialFrom(byte[] input, int offset, int length, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
         return GeneratedMessageLite.parsePartialFrom(this.defaultInstance, input, offset, length, extensionRegistry);
      }
   }

   public interface ExtendableMessageOrBuilder extends MessageLiteOrBuilder {
      boolean hasExtension(ExtensionLite extension);

      int getExtensionCount(ExtensionLite extension);

      Object getExtension(ExtensionLite extension);

      Object getExtension(ExtensionLite extension, int index);
   }
}
