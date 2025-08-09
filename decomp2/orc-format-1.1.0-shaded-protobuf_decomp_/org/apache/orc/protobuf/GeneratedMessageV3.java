package org.apache.orc.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public abstract class GeneratedMessageV3 extends AbstractMessage implements Serializable {
   private static final long serialVersionUID = 1L;
   protected static boolean alwaysUseFieldBuilders = false;
   protected UnknownFieldSet unknownFields;

   protected GeneratedMessageV3() {
      this.unknownFields = UnknownFieldSet.getDefaultInstance();
   }

   protected GeneratedMessageV3(Builder builder) {
      this.unknownFields = builder.getUnknownFields();
   }

   public Parser getParserForType() {
      throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
   }

   static void enableAlwaysUseFieldBuildersForTesting() {
      setAlwaysUseFieldBuildersForTesting(true);
   }

   static void setAlwaysUseFieldBuildersForTesting(boolean useBuilders) {
      alwaysUseFieldBuilders = useBuilders;
   }

   protected abstract FieldAccessorTable internalGetFieldAccessorTable();

   public Descriptors.Descriptor getDescriptorForType() {
      return this.internalGetFieldAccessorTable().descriptor;
   }

   /** @deprecated */
   @Deprecated
   protected void mergeFromAndMakeImmutableInternal(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      Schema<GeneratedMessageV3> schema = Protobuf.getInstance().schemaFor((Object)this);

      try {
         schema.mergeFrom(this, CodedInputStreamReader.forCodedInput(input), extensionRegistry);
      } catch (InvalidProtocolBufferException e) {
         throw e.setUnfinishedMessage(this);
      } catch (IOException e) {
         throw (new InvalidProtocolBufferException(e)).setUnfinishedMessage(this);
      }

      schema.makeImmutable(this);
   }

   private Map getAllFieldsMutable(boolean getBytesForString) {
      TreeMap<Descriptors.FieldDescriptor, Object> result = new TreeMap();
      Descriptors.Descriptor descriptor = this.internalGetFieldAccessorTable().descriptor;
      List<Descriptors.FieldDescriptor> fields = descriptor.getFields();

      for(int i = 0; i < fields.size(); ++i) {
         Descriptors.FieldDescriptor field = (Descriptors.FieldDescriptor)fields.get(i);
         Descriptors.OneofDescriptor oneofDescriptor = field.getContainingOneof();
         if (oneofDescriptor != null) {
            i += oneofDescriptor.getFieldCount() - 1;
            if (!this.hasOneof(oneofDescriptor)) {
               continue;
            }

            field = this.getOneofFieldDescriptor(oneofDescriptor);
         } else {
            if (field.isRepeated()) {
               List<?> value = (List)this.getField(field);
               if (!value.isEmpty()) {
                  result.put(field, value);
               }
               continue;
            }

            if (!this.hasField(field)) {
               continue;
            }
         }

         if (getBytesForString && field.getJavaType() == Descriptors.FieldDescriptor.JavaType.STRING) {
            result.put(field, this.getFieldRaw(field));
         } else {
            result.put(field, this.getField(field));
         }
      }

      return result;
   }

   public boolean isInitialized() {
      for(Descriptors.FieldDescriptor field : this.getDescriptorForType().getFields()) {
         if (field.isRequired() && !this.hasField(field)) {
            return false;
         }

         if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
            if (field.isRepeated()) {
               for(Message element : (List)this.getField(field)) {
                  if (!element.isInitialized()) {
                     return false;
                  }
               }
            } else if (this.hasField(field) && !((Message)this.getField(field)).isInitialized()) {
               return false;
            }
         }
      }

      return true;
   }

   public Map getAllFields() {
      return Collections.unmodifiableMap(this.getAllFieldsMutable(false));
   }

   Map getAllFieldsRaw() {
      return Collections.unmodifiableMap(this.getAllFieldsMutable(true));
   }

   public boolean hasOneof(final Descriptors.OneofDescriptor oneof) {
      return this.internalGetFieldAccessorTable().getOneof(oneof).has(this);
   }

   public Descriptors.FieldDescriptor getOneofFieldDescriptor(final Descriptors.OneofDescriptor oneof) {
      return this.internalGetFieldAccessorTable().getOneof(oneof).get(this);
   }

   public boolean hasField(final Descriptors.FieldDescriptor field) {
      return this.internalGetFieldAccessorTable().getField(field).has(this);
   }

   public Object getField(final Descriptors.FieldDescriptor field) {
      return this.internalGetFieldAccessorTable().getField(field).get(this);
   }

   Object getFieldRaw(final Descriptors.FieldDescriptor field) {
      return this.internalGetFieldAccessorTable().getField(field).getRaw(this);
   }

   public int getRepeatedFieldCount(final Descriptors.FieldDescriptor field) {
      return this.internalGetFieldAccessorTable().getField(field).getRepeatedCount(this);
   }

   public Object getRepeatedField(final Descriptors.FieldDescriptor field, final int index) {
      return this.internalGetFieldAccessorTable().getField(field).getRepeated(this, index);
   }

   public UnknownFieldSet getUnknownFields() {
      return this.unknownFields;
   }

   void setUnknownFields(UnknownFieldSet unknownFields) {
      this.unknownFields = unknownFields;
   }

   protected boolean parseUnknownField(CodedInputStream input, UnknownFieldSet.Builder unknownFields, ExtensionRegistryLite extensionRegistry, int tag) throws IOException {
      return input.shouldDiscardUnknownFields() ? input.skipField(tag) : unknownFields.mergeFieldFrom(tag, input);
   }

   protected boolean parseUnknownFieldProto3(CodedInputStream input, UnknownFieldSet.Builder unknownFields, ExtensionRegistryLite extensionRegistry, int tag) throws IOException {
      return this.parseUnknownField(input, unknownFields, extensionRegistry, tag);
   }

   protected static Message parseWithIOException(Parser parser, InputStream input) throws IOException {
      try {
         return (Message)parser.parseFrom(input);
      } catch (InvalidProtocolBufferException e) {
         throw e.unwrapIOException();
      }
   }

   protected static Message parseWithIOException(Parser parser, InputStream input, ExtensionRegistryLite extensions) throws IOException {
      try {
         return (Message)parser.parseFrom(input, extensions);
      } catch (InvalidProtocolBufferException e) {
         throw e.unwrapIOException();
      }
   }

   protected static Message parseWithIOException(Parser parser, CodedInputStream input) throws IOException {
      try {
         return (Message)parser.parseFrom(input);
      } catch (InvalidProtocolBufferException e) {
         throw e.unwrapIOException();
      }
   }

   protected static Message parseWithIOException(Parser parser, CodedInputStream input, ExtensionRegistryLite extensions) throws IOException {
      try {
         return (Message)parser.parseFrom(input, extensions);
      } catch (InvalidProtocolBufferException e) {
         throw e.unwrapIOException();
      }
   }

   protected static Message parseDelimitedWithIOException(Parser parser, InputStream input) throws IOException {
      try {
         return (Message)parser.parseDelimitedFrom(input);
      } catch (InvalidProtocolBufferException e) {
         throw e.unwrapIOException();
      }
   }

   protected static Message parseDelimitedWithIOException(Parser parser, InputStream input, ExtensionRegistryLite extensions) throws IOException {
      try {
         return (Message)parser.parseDelimitedFrom(input, extensions);
      } catch (InvalidProtocolBufferException e) {
         throw e.unwrapIOException();
      }
   }

   protected static boolean canUseUnsafe() {
      return UnsafeUtil.hasUnsafeArrayOperations() && UnsafeUtil.hasUnsafeByteBufferOperations();
   }

   protected static Internal.IntList emptyIntList() {
      return IntArrayList.emptyList();
   }

   protected static Internal.IntList newIntList() {
      return new IntArrayList();
   }

   protected static Internal.IntList mutableCopy(Internal.IntList list) {
      return (Internal.IntList)makeMutableCopy(list);
   }

   protected static Internal.LongList mutableCopy(Internal.LongList list) {
      return (Internal.LongList)makeMutableCopy(list);
   }

   protected static Internal.FloatList mutableCopy(Internal.FloatList list) {
      return (Internal.FloatList)makeMutableCopy(list);
   }

   protected static Internal.DoubleList mutableCopy(Internal.DoubleList list) {
      return (Internal.DoubleList)makeMutableCopy(list);
   }

   protected static Internal.BooleanList mutableCopy(Internal.BooleanList list) {
      return (Internal.BooleanList)makeMutableCopy(list);
   }

   protected static Internal.LongList emptyLongList() {
      return LongArrayList.emptyList();
   }

   protected static Internal.LongList newLongList() {
      return new LongArrayList();
   }

   protected static Internal.FloatList emptyFloatList() {
      return FloatArrayList.emptyList();
   }

   protected static Internal.FloatList newFloatList() {
      return new FloatArrayList();
   }

   protected static Internal.DoubleList emptyDoubleList() {
      return DoubleArrayList.emptyList();
   }

   protected static Internal.DoubleList newDoubleList() {
      return new DoubleArrayList();
   }

   protected static Internal.BooleanList emptyBooleanList() {
      return BooleanArrayList.emptyList();
   }

   protected static Internal.BooleanList newBooleanList() {
      return new BooleanArrayList();
   }

   protected static Internal.ProtobufList makeMutableCopy(Internal.ProtobufList list) {
      return makeMutableCopy(list, 0);
   }

   protected static Internal.ProtobufList makeMutableCopy(Internal.ProtobufList list, int minCapacity) {
      int size = list.size();
      if (minCapacity <= size) {
         minCapacity = size * 2;
      }

      if (minCapacity <= 0) {
         minCapacity = 10;
      }

      return list.mutableCopyWithCapacity(minCapacity);
   }

   protected static Internal.ProtobufList emptyList(Class elementType) {
      return ProtobufArrayList.emptyList();
   }

   public void writeTo(final CodedOutputStream output) throws IOException {
      MessageReflection.writeMessageTo(this, this.getAllFieldsRaw(), output, false);
   }

   public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1) {
         return size;
      } else {
         this.memoizedSize = MessageReflection.getSerializedSize(this, this.getAllFieldsRaw());
         return this.memoizedSize;
      }
   }

   protected Object newInstance(UnusedPrivateParameter unused) {
      throw new UnsupportedOperationException("This method must be overridden by the subclass.");
   }

   protected void makeExtensionsImmutable() {
   }

   protected abstract Message.Builder newBuilderForType(BuilderParent parent);

   protected Message.Builder newBuilderForType(final AbstractMessage.BuilderParent parent) {
      return this.newBuilderForType(new BuilderParent() {
         public void markDirty() {
            parent.markDirty();
         }
      });
   }

   private static java.lang.reflect.Method getMethodOrDie(final Class clazz, final String name, final Class... params) {
      try {
         return clazz.getMethod(name, params);
      } catch (NoSuchMethodException e) {
         throw new IllegalStateException("Generated message class \"" + clazz.getName() + "\" missing method \"" + name + "\".", e);
      }
   }

   @CanIgnoreReturnValue
   private static Object invokeOrDie(final java.lang.reflect.Method method, final Object object, final Object... params) {
      try {
         return method.invoke(object, params);
      } catch (IllegalAccessException e) {
         throw new IllegalStateException("Couldn't use Java reflection to implement protocol message reflection.", e);
      } catch (InvocationTargetException e) {
         Throwable cause = e.getCause();
         if (cause instanceof RuntimeException) {
            throw (RuntimeException)cause;
         } else if (cause instanceof Error) {
            throw (Error)cause;
         } else {
            throw new IllegalStateException("Unexpected exception thrown by generated accessor method.", cause);
         }
      }
   }

   protected MapFieldReflectionAccessor internalGetMapFieldReflection(int fieldNumber) {
      return this.internalGetMapField(fieldNumber);
   }

   /** @deprecated */
   @Deprecated
   protected MapField internalGetMapField(int fieldNumber) {
      throw new IllegalArgumentException("No map fields found in " + this.getClass().getName());
   }

   protected Object writeReplace() throws ObjectStreamException {
      return new GeneratedMessageLite.SerializedForm(this);
   }

   private static Extension checkNotLite(ExtensionLite extension) {
      if (extension.isLite()) {
         throw new IllegalArgumentException("Expected non-lite extension.");
      } else {
         return (Extension)extension;
      }
   }

   protected static boolean isStringEmpty(final Object value) {
      return value instanceof String ? ((String)value).isEmpty() : ((ByteString)value).isEmpty();
   }

   protected static int computeStringSize(final int fieldNumber, final Object value) {
      return value instanceof String ? CodedOutputStream.computeStringSize(fieldNumber, (String)value) : CodedOutputStream.computeBytesSize(fieldNumber, (ByteString)value);
   }

   protected static int computeStringSizeNoTag(final Object value) {
      return value instanceof String ? CodedOutputStream.computeStringSizeNoTag((String)value) : CodedOutputStream.computeBytesSizeNoTag((ByteString)value);
   }

   protected static void writeString(CodedOutputStream output, final int fieldNumber, final Object value) throws IOException {
      if (value instanceof String) {
         output.writeString(fieldNumber, (String)value);
      } else {
         output.writeBytes(fieldNumber, (ByteString)value);
      }

   }

   protected static void writeStringNoTag(CodedOutputStream output, final Object value) throws IOException {
      if (value instanceof String) {
         output.writeStringNoTag((String)value);
      } else {
         output.writeBytesNoTag((ByteString)value);
      }

   }

   protected static void serializeIntegerMapTo(CodedOutputStream out, MapField field, MapEntry defaultEntry, int fieldNumber) throws IOException {
      Map<Integer, V> m = field.getMap();
      if (!out.isSerializationDeterministic()) {
         serializeMapTo(out, m, defaultEntry, fieldNumber);
      } else {
         int[] keys = new int[m.size()];
         int index = 0;

         for(int k : m.keySet()) {
            keys[index++] = k;
         }

         Arrays.sort(keys);

         for(int key : keys) {
            out.writeMessage(fieldNumber, defaultEntry.newBuilderForType().setKey(key).setValue(m.get(key)).build());
         }

      }
   }

   protected static void serializeLongMapTo(CodedOutputStream out, MapField field, MapEntry defaultEntry, int fieldNumber) throws IOException {
      Map<Long, V> m = field.getMap();
      if (!out.isSerializationDeterministic()) {
         serializeMapTo(out, m, defaultEntry, fieldNumber);
      } else {
         long[] keys = new long[m.size()];
         int index = 0;

         for(long k : m.keySet()) {
            keys[index++] = k;
         }

         Arrays.sort(keys);

         for(long key : keys) {
            out.writeMessage(fieldNumber, defaultEntry.newBuilderForType().setKey(key).setValue(m.get(key)).build());
         }

      }
   }

   protected static void serializeStringMapTo(CodedOutputStream out, MapField field, MapEntry defaultEntry, int fieldNumber) throws IOException {
      Map<String, V> m = field.getMap();
      if (!out.isSerializationDeterministic()) {
         serializeMapTo(out, m, defaultEntry, fieldNumber);
      } else {
         String[] keys = new String[m.size()];
         keys = (String[])m.keySet().toArray(keys);
         Arrays.sort(keys);

         for(String key : keys) {
            out.writeMessage(fieldNumber, defaultEntry.newBuilderForType().setKey(key).setValue(m.get(key)).build());
         }

      }
   }

   protected static void serializeBooleanMapTo(CodedOutputStream out, MapField field, MapEntry defaultEntry, int fieldNumber) throws IOException {
      Map<Boolean, V> m = field.getMap();
      if (!out.isSerializationDeterministic()) {
         serializeMapTo(out, m, defaultEntry, fieldNumber);
      } else {
         maybeSerializeBooleanEntryTo(out, m, defaultEntry, fieldNumber, false);
         maybeSerializeBooleanEntryTo(out, m, defaultEntry, fieldNumber, true);
      }
   }

   private static void maybeSerializeBooleanEntryTo(CodedOutputStream out, Map m, MapEntry defaultEntry, int fieldNumber, boolean key) throws IOException {
      if (m.containsKey(key)) {
         out.writeMessage(fieldNumber, defaultEntry.newBuilderForType().setKey(key).setValue(m.get(key)).build());
      }

   }

   private static void serializeMapTo(CodedOutputStream out, Map m, MapEntry defaultEntry, int fieldNumber) throws IOException {
      for(Map.Entry entry : m.entrySet()) {
         out.writeMessage(fieldNumber, defaultEntry.newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).build());
      }

   }

   protected static final class UnusedPrivateParameter {
      static final UnusedPrivateParameter INSTANCE = new UnusedPrivateParameter();

      private UnusedPrivateParameter() {
      }
   }

   public abstract static class Builder extends AbstractMessage.Builder {
      private BuilderParent builderParent;
      private BuilderParentImpl meAsParent;
      private boolean isClean;
      private Object unknownFieldsOrBuilder;

      protected Builder() {
         this((BuilderParent)null);
      }

      protected Builder(BuilderParent builderParent) {
         this.unknownFieldsOrBuilder = UnknownFieldSet.getDefaultInstance();
         this.builderParent = builderParent;
      }

      void dispose() {
         this.builderParent = null;
      }

      protected void onBuilt() {
         if (this.builderParent != null) {
            this.markClean();
         }

      }

      protected void markClean() {
         this.isClean = true;
      }

      protected boolean isClean() {
         return this.isClean;
      }

      public Builder clone() {
         BuilderT builder = (BuilderT)((Builder)this.getDefaultInstanceForType().newBuilderForType());
         builder.mergeFrom(this.buildPartial());
         return builder;
      }

      public Builder clear() {
         this.unknownFieldsOrBuilder = UnknownFieldSet.getDefaultInstance();
         this.onChanged();
         return this;
      }

      protected abstract FieldAccessorTable internalGetFieldAccessorTable();

      public Descriptors.Descriptor getDescriptorForType() {
         return this.internalGetFieldAccessorTable().descriptor;
      }

      public Map getAllFields() {
         return Collections.unmodifiableMap(this.getAllFieldsMutable());
      }

      private Map getAllFieldsMutable() {
         TreeMap<Descriptors.FieldDescriptor, Object> result = new TreeMap();
         Descriptors.Descriptor descriptor = this.internalGetFieldAccessorTable().descriptor;
         List<Descriptors.FieldDescriptor> fields = descriptor.getFields();

         for(int i = 0; i < fields.size(); ++i) {
            Descriptors.FieldDescriptor field = (Descriptors.FieldDescriptor)fields.get(i);
            Descriptors.OneofDescriptor oneofDescriptor = field.getContainingOneof();
            if (oneofDescriptor != null) {
               i += oneofDescriptor.getFieldCount() - 1;
               if (!this.hasOneof(oneofDescriptor)) {
                  continue;
               }

               field = this.getOneofFieldDescriptor(oneofDescriptor);
            } else {
               if (field.isRepeated()) {
                  List<?> value = (List)this.getField(field);
                  if (!value.isEmpty()) {
                     result.put(field, value);
                  }
                  continue;
               }

               if (!this.hasField(field)) {
                  continue;
               }
            }

            result.put(field, this.getField(field));
         }

         return result;
      }

      public Message.Builder newBuilderForField(final Descriptors.FieldDescriptor field) {
         return this.internalGetFieldAccessorTable().getField(field).newBuilder();
      }

      public Message.Builder getFieldBuilder(final Descriptors.FieldDescriptor field) {
         return this.internalGetFieldAccessorTable().getField(field).getBuilder(this);
      }

      public Message.Builder getRepeatedFieldBuilder(final Descriptors.FieldDescriptor field, int index) {
         return this.internalGetFieldAccessorTable().getField(field).getRepeatedBuilder(this, index);
      }

      public boolean hasOneof(final Descriptors.OneofDescriptor oneof) {
         return this.internalGetFieldAccessorTable().getOneof(oneof).has(this);
      }

      public Descriptors.FieldDescriptor getOneofFieldDescriptor(final Descriptors.OneofDescriptor oneof) {
         return this.internalGetFieldAccessorTable().getOneof(oneof).get(this);
      }

      public boolean hasField(final Descriptors.FieldDescriptor field) {
         return this.internalGetFieldAccessorTable().getField(field).has(this);
      }

      public Object getField(final Descriptors.FieldDescriptor field) {
         Object object = this.internalGetFieldAccessorTable().getField(field).get(this);
         return field.isRepeated() ? Collections.unmodifiableList((List)object) : object;
      }

      public Builder setField(final Descriptors.FieldDescriptor field, final Object value) {
         this.internalGetFieldAccessorTable().getField(field).set(this, value);
         return this;
      }

      public Builder clearField(final Descriptors.FieldDescriptor field) {
         this.internalGetFieldAccessorTable().getField(field).clear(this);
         return this;
      }

      public Builder clearOneof(final Descriptors.OneofDescriptor oneof) {
         this.internalGetFieldAccessorTable().getOneof(oneof).clear(this);
         return this;
      }

      public int getRepeatedFieldCount(final Descriptors.FieldDescriptor field) {
         return this.internalGetFieldAccessorTable().getField(field).getRepeatedCount(this);
      }

      public Object getRepeatedField(final Descriptors.FieldDescriptor field, final int index) {
         return this.internalGetFieldAccessorTable().getField(field).getRepeated(this, index);
      }

      public Builder setRepeatedField(final Descriptors.FieldDescriptor field, final int index, final Object value) {
         this.internalGetFieldAccessorTable().getField(field).setRepeated(this, index, value);
         return this;
      }

      public Builder addRepeatedField(final Descriptors.FieldDescriptor field, final Object value) {
         this.internalGetFieldAccessorTable().getField(field).addRepeated(this, value);
         return this;
      }

      private Builder setUnknownFieldsInternal(final UnknownFieldSet unknownFields) {
         this.unknownFieldsOrBuilder = unknownFields;
         this.onChanged();
         return this;
      }

      public Builder setUnknownFields(final UnknownFieldSet unknownFields) {
         return this.setUnknownFieldsInternal(unknownFields);
      }

      protected Builder setUnknownFieldsProto3(final UnknownFieldSet unknownFields) {
         return this.setUnknownFieldsInternal(unknownFields);
      }

      public Builder mergeUnknownFields(final UnknownFieldSet unknownFields) {
         if (UnknownFieldSet.getDefaultInstance().equals(unknownFields)) {
            return this;
         } else if (UnknownFieldSet.getDefaultInstance().equals(this.unknownFieldsOrBuilder)) {
            this.unknownFieldsOrBuilder = unknownFields;
            this.onChanged();
            return this;
         } else {
            this.getUnknownFieldSetBuilder().mergeFrom(unknownFields);
            this.onChanged();
            return this;
         }
      }

      public boolean isInitialized() {
         for(Descriptors.FieldDescriptor field : this.getDescriptorForType().getFields()) {
            if (field.isRequired() && !this.hasField(field)) {
               return false;
            }

            if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
               if (field.isRepeated()) {
                  for(Message element : (List)this.getField(field)) {
                     if (!element.isInitialized()) {
                        return false;
                     }
                  }
               } else if (this.hasField(field) && !((Message)this.getField(field)).isInitialized()) {
                  return false;
               }
            }
         }

         return true;
      }

      public final UnknownFieldSet getUnknownFields() {
         return this.unknownFieldsOrBuilder instanceof UnknownFieldSet ? (UnknownFieldSet)this.unknownFieldsOrBuilder : ((UnknownFieldSet.Builder)this.unknownFieldsOrBuilder).buildPartial();
      }

      protected boolean parseUnknownField(CodedInputStream input, ExtensionRegistryLite extensionRegistry, int tag) throws IOException {
         return input.shouldDiscardUnknownFields() ? input.skipField(tag) : this.getUnknownFieldSetBuilder().mergeFieldFrom(tag, input);
      }

      protected final void mergeUnknownLengthDelimitedField(int number, ByteString bytes) {
         this.getUnknownFieldSetBuilder().mergeLengthDelimitedField(number, bytes);
      }

      protected final void mergeUnknownVarintField(int number, int value) {
         this.getUnknownFieldSetBuilder().mergeVarintField(number, value);
      }

      protected UnknownFieldSet.Builder getUnknownFieldSetBuilder() {
         if (this.unknownFieldsOrBuilder instanceof UnknownFieldSet) {
            this.unknownFieldsOrBuilder = ((UnknownFieldSet)this.unknownFieldsOrBuilder).toBuilder();
         }

         this.onChanged();
         return (UnknownFieldSet.Builder)this.unknownFieldsOrBuilder;
      }

      protected void setUnknownFieldSetBuilder(UnknownFieldSet.Builder builder) {
         this.unknownFieldsOrBuilder = builder;
         this.onChanged();
      }

      protected BuilderParent getParentForChildren() {
         if (this.meAsParent == null) {
            this.meAsParent = new BuilderParentImpl();
         }

         return this.meAsParent;
      }

      protected final void onChanged() {
         if (this.isClean && this.builderParent != null) {
            this.builderParent.markDirty();
            this.isClean = false;
         }

      }

      protected MapFieldReflectionAccessor internalGetMapFieldReflection(int fieldNumber) {
         return this.internalGetMapField(fieldNumber);
      }

      /** @deprecated */
      @Deprecated
      protected MapField internalGetMapField(int fieldNumber) {
         throw new IllegalArgumentException("No map fields found in " + this.getClass().getName());
      }

      protected MapFieldReflectionAccessor internalGetMutableMapFieldReflection(int fieldNumber) {
         return this.internalGetMutableMapField(fieldNumber);
      }

      /** @deprecated */
      @Deprecated
      protected MapField internalGetMutableMapField(int fieldNumber) {
         throw new IllegalArgumentException("No map fields found in " + this.getClass().getName());
      }

      private class BuilderParentImpl implements BuilderParent {
         private BuilderParentImpl() {
         }

         public void markDirty() {
            Builder.this.onChanged();
         }
      }
   }

   public interface ExtendableMessageOrBuilder extends MessageOrBuilder {
      Message getDefaultInstanceForType();

      boolean hasExtension(ExtensionLite extension);

      int getExtensionCount(ExtensionLite extension);

      Object getExtension(ExtensionLite extension);

      Object getExtension(ExtensionLite extension, int index);

      boolean hasExtension(Extension extension);

      boolean hasExtension(GeneratedMessage.GeneratedExtension extension);

      int getExtensionCount(Extension extension);

      int getExtensionCount(GeneratedMessage.GeneratedExtension extension);

      Object getExtension(Extension extension);

      Object getExtension(GeneratedMessage.GeneratedExtension extension);

      Object getExtension(Extension extension, int index);

      Object getExtension(GeneratedMessage.GeneratedExtension extension, int index);
   }

   public abstract static class ExtendableMessage extends GeneratedMessageV3 implements ExtendableMessageOrBuilder {
      private static final long serialVersionUID = 1L;
      private final FieldSet extensions;

      protected ExtendableMessage() {
         this.extensions = FieldSet.newFieldSet();
      }

      protected ExtendableMessage(ExtendableBuilder builder) {
         super(builder);
         this.extensions = builder.buildExtensions();
      }

      private void verifyExtensionContainingType(final Extension extension) {
         if (extension.getDescriptor().getContainingType() != this.getDescriptorForType()) {
            throw new IllegalArgumentException("Extension is for type \"" + extension.getDescriptor().getContainingType().getFullName() + "\" which does not match message type \"" + this.getDescriptorForType().getFullName() + "\".");
         }
      }

      public final boolean hasExtension(final ExtensionLite extensionLite) {
         Extension<MessageT, T> extension = GeneratedMessageV3.checkNotLite(extensionLite);
         this.verifyExtensionContainingType(extension);
         return this.extensions.hasField(extension.getDescriptor());
      }

      public final int getExtensionCount(final ExtensionLite extensionLite) {
         Extension<MessageT, List<T>> extension = GeneratedMessageV3.checkNotLite(extensionLite);
         this.verifyExtensionContainingType(extension);
         Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
         return this.extensions.getRepeatedFieldCount(descriptor);
      }

      public final Object getExtension(final ExtensionLite extensionLite) {
         Extension<MessageT, T> extension = GeneratedMessageV3.checkNotLite(extensionLite);
         this.verifyExtensionContainingType(extension);
         Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
         Object value = this.extensions.getField(descriptor);
         if (value == null) {
            if (descriptor.isRepeated()) {
               return Collections.emptyList();
            } else {
               return descriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE ? extension.getMessageDefaultInstance() : extension.fromReflectionType(descriptor.getDefaultValue());
            }
         } else {
            return extension.fromReflectionType(value);
         }
      }

      public final Object getExtension(final ExtensionLite extensionLite, final int index) {
         Extension<MessageT, List<T>> extension = GeneratedMessageV3.checkNotLite(extensionLite);
         this.verifyExtensionContainingType(extension);
         Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
         return extension.singularFromReflectionType(this.extensions.getRepeatedField(descriptor, index));
      }

      public final boolean hasExtension(final Extension extension) {
         return this.hasExtension((ExtensionLite)extension);
      }

      public final boolean hasExtension(final GeneratedMessage.GeneratedExtension extension) {
         return this.hasExtension((ExtensionLite)extension);
      }

      public final int getExtensionCount(final Extension extension) {
         return this.getExtensionCount((ExtensionLite)extension);
      }

      public final int getExtensionCount(final GeneratedMessage.GeneratedExtension extension) {
         return this.getExtensionCount((ExtensionLite)extension);
      }

      public final Object getExtension(final Extension extension) {
         return this.getExtension((ExtensionLite)extension);
      }

      public final Object getExtension(final GeneratedMessage.GeneratedExtension extension) {
         return this.getExtension((ExtensionLite)extension);
      }

      public final Object getExtension(final Extension extension, final int index) {
         return this.getExtension((ExtensionLite)extension, index);
      }

      public final Object getExtension(final GeneratedMessage.GeneratedExtension extension, final int index) {
         return this.getExtension((ExtensionLite)extension, index);
      }

      protected boolean extensionsAreInitialized() {
         return this.extensions.isInitialized();
      }

      public boolean isInitialized() {
         return super.isInitialized() && this.extensionsAreInitialized();
      }

      protected boolean parseUnknownField(CodedInputStream input, UnknownFieldSet.Builder unknownFields, ExtensionRegistryLite extensionRegistry, int tag) throws IOException {
         return MessageReflection.mergeFieldFrom(input, input.shouldDiscardUnknownFields() ? null : unknownFields, extensionRegistry, this.getDescriptorForType(), new MessageReflection.ExtensionAdapter(this.extensions), tag);
      }

      protected boolean parseUnknownFieldProto3(CodedInputStream input, UnknownFieldSet.Builder unknownFields, ExtensionRegistryLite extensionRegistry, int tag) throws IOException {
         return this.parseUnknownField(input, unknownFields, extensionRegistry, tag);
      }

      protected void makeExtensionsImmutable() {
         this.extensions.makeImmutable();
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

      protected Map getExtensionFields() {
         return this.extensions.getAllFields();
      }

      public Map getAllFields() {
         Map<Descriptors.FieldDescriptor, Object> result = super.getAllFieldsMutable(false);
         result.putAll(this.getExtensionFields());
         return Collections.unmodifiableMap(result);
      }

      public Map getAllFieldsRaw() {
         Map<Descriptors.FieldDescriptor, Object> result = super.getAllFieldsMutable(false);
         result.putAll(this.getExtensionFields());
         return Collections.unmodifiableMap(result);
      }

      public boolean hasField(final Descriptors.FieldDescriptor field) {
         if (field.isExtension()) {
            this.verifyContainingType(field);
            return this.extensions.hasField(field);
         } else {
            return super.hasField(field);
         }
      }

      public Object getField(final Descriptors.FieldDescriptor field) {
         if (field.isExtension()) {
            this.verifyContainingType(field);
            Object value = this.extensions.getField(field);
            if (value == null) {
               if (field.isRepeated()) {
                  return Collections.emptyList();
               } else {
                  return field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE ? DynamicMessage.getDefaultInstance(field.getMessageType()) : field.getDefaultValue();
               }
            } else {
               return value;
            }
         } else {
            return super.getField(field);
         }
      }

      public int getRepeatedFieldCount(final Descriptors.FieldDescriptor field) {
         if (field.isExtension()) {
            this.verifyContainingType(field);
            return this.extensions.getRepeatedFieldCount(field);
         } else {
            return super.getRepeatedFieldCount(field);
         }
      }

      public Object getRepeatedField(final Descriptors.FieldDescriptor field, final int index) {
         if (field.isExtension()) {
            this.verifyContainingType(field);
            return this.extensions.getRepeatedField(field, index);
         } else {
            return super.getRepeatedField(field, index);
         }
      }

      private void verifyContainingType(final Descriptors.FieldDescriptor field) {
         if (field.getContainingType() != this.getDescriptorForType()) {
            throw new IllegalArgumentException("FieldDescriptor does not match message type.");
         }
      }

      protected class ExtensionWriter {
         private final Iterator iter;
         private Map.Entry next;
         private final boolean messageSetWireFormat;

         private ExtensionWriter(final boolean messageSetWireFormat) {
            this.iter = ExtendableMessage.this.extensions.iterator();
            if (this.iter.hasNext()) {
               this.next = (Map.Entry)this.iter.next();
            }

            this.messageSetWireFormat = messageSetWireFormat;
         }

         public void writeUntil(final int end, final CodedOutputStream output) throws IOException {
            while(this.next != null && ((Descriptors.FieldDescriptor)this.next.getKey()).getNumber() < end) {
               Descriptors.FieldDescriptor descriptor = (Descriptors.FieldDescriptor)this.next.getKey();
               if (this.messageSetWireFormat && descriptor.getLiteJavaType() == WireFormat.JavaType.MESSAGE && !descriptor.isRepeated()) {
                  if (this.next instanceof LazyField.LazyEntry) {
                     output.writeRawMessageSetExtension(descriptor.getNumber(), ((LazyField.LazyEntry)this.next).getField().toByteString());
                  } else {
                     output.writeMessageSetExtension(descriptor.getNumber(), (Message)this.next.getValue());
                  }
               } else {
                  FieldSet.writeField(descriptor, this.next.getValue(), output);
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
      private FieldSet.Builder extensions;

      protected ExtendableBuilder() {
      }

      protected ExtendableBuilder(BuilderParent parent) {
         super(parent);
      }

      void internalSetExtensionSet(FieldSet extensions) {
         this.extensions = FieldSet.Builder.fromFieldSet(extensions);
      }

      public ExtendableBuilder clear() {
         this.extensions = null;
         return (ExtendableBuilder)super.clear();
      }

      private void ensureExtensionsIsMutable() {
         if (this.extensions == null) {
            this.extensions = FieldSet.newBuilder();
         }

      }

      private void verifyExtensionContainingType(final Extension extension) {
         if (extension.getDescriptor().getContainingType() != this.getDescriptorForType()) {
            throw new IllegalArgumentException("Extension is for type \"" + extension.getDescriptor().getContainingType().getFullName() + "\" which does not match message type \"" + this.getDescriptorForType().getFullName() + "\".");
         }
      }

      public final boolean hasExtension(final ExtensionLite extensionLite) {
         Extension<MessageT, T> extension = GeneratedMessageV3.checkNotLite(extensionLite);
         this.verifyExtensionContainingType(extension);
         return this.extensions != null && this.extensions.hasField(extension.getDescriptor());
      }

      public final int getExtensionCount(final ExtensionLite extensionLite) {
         Extension<MessageT, List<T>> extension = GeneratedMessageV3.checkNotLite(extensionLite);
         this.verifyExtensionContainingType(extension);
         Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
         return this.extensions == null ? 0 : this.extensions.getRepeatedFieldCount(descriptor);
      }

      public final Object getExtension(final ExtensionLite extensionLite) {
         Extension<MessageT, T> extension = GeneratedMessageV3.checkNotLite(extensionLite);
         this.verifyExtensionContainingType(extension);
         Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
         Object value = this.extensions == null ? null : this.extensions.getField(descriptor);
         if (value == null) {
            if (descriptor.isRepeated()) {
               return Collections.emptyList();
            } else {
               return descriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE ? extension.getMessageDefaultInstance() : extension.fromReflectionType(descriptor.getDefaultValue());
            }
         } else {
            return extension.fromReflectionType(value);
         }
      }

      public final Object getExtension(final ExtensionLite extensionLite, final int index) {
         Extension<MessageT, List<T>> extension = GeneratedMessageV3.checkNotLite(extensionLite);
         this.verifyExtensionContainingType(extension);
         Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
         if (this.extensions == null) {
            throw new IndexOutOfBoundsException();
         } else {
            return extension.singularFromReflectionType(this.extensions.getRepeatedField(descriptor, index));
         }
      }

      public final ExtendableBuilder setExtension(final ExtensionLite extensionLite, final Object value) {
         Extension<MessageT, T> extension = GeneratedMessageV3.checkNotLite(extensionLite);
         this.verifyExtensionContainingType(extension);
         this.ensureExtensionsIsMutable();
         Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
         this.extensions.setField(descriptor, extension.toReflectionType(value));
         this.onChanged();
         return this;
      }

      public final ExtendableBuilder setExtension(final ExtensionLite extensionLite, final int index, final Object value) {
         Extension<MessageT, List<T>> extension = GeneratedMessageV3.checkNotLite(extensionLite);
         this.verifyExtensionContainingType(extension);
         this.ensureExtensionsIsMutable();
         Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
         this.extensions.setRepeatedField(descriptor, index, extension.singularToReflectionType(value));
         this.onChanged();
         return this;
      }

      public final ExtendableBuilder addExtension(final ExtensionLite extensionLite, final Object value) {
         Extension<MessageT, List<T>> extension = GeneratedMessageV3.checkNotLite(extensionLite);
         this.verifyExtensionContainingType(extension);
         this.ensureExtensionsIsMutable();
         Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
         this.extensions.addRepeatedField(descriptor, extension.singularToReflectionType(value));
         this.onChanged();
         return this;
      }

      public final ExtendableBuilder clearExtension(final ExtensionLite extensionLite) {
         Extension<MessageT, T> extension = GeneratedMessageV3.checkNotLite(extensionLite);
         this.verifyExtensionContainingType(extension);
         this.ensureExtensionsIsMutable();
         this.extensions.clearField(extension.getDescriptor());
         this.onChanged();
         return this;
      }

      public final boolean hasExtension(final Extension extension) {
         return this.hasExtension((ExtensionLite)extension);
      }

      public final boolean hasExtension(final GeneratedMessage.GeneratedExtension extension) {
         return this.hasExtension((ExtensionLite)extension);
      }

      public final int getExtensionCount(final Extension extension) {
         return this.getExtensionCount((ExtensionLite)extension);
      }

      public final int getExtensionCount(final GeneratedMessage.GeneratedExtension extension) {
         return this.getExtensionCount((ExtensionLite)extension);
      }

      public final Object getExtension(final Extension extension) {
         return this.getExtension((ExtensionLite)extension);
      }

      public final Object getExtension(final GeneratedMessage.GeneratedExtension extension) {
         return this.getExtension((ExtensionLite)extension);
      }

      public final Object getExtension(final Extension extension, final int index) {
         return this.getExtension((ExtensionLite)extension, index);
      }

      public final Object getExtension(final GeneratedMessage.GeneratedExtension extension, final int index) {
         return this.getExtension((ExtensionLite)extension, index);
      }

      public final ExtendableBuilder setExtension(final Extension extension, final Object value) {
         return this.setExtension((ExtensionLite)extension, value);
      }

      public ExtendableBuilder setExtension(final GeneratedMessage.GeneratedExtension extension, final Object value) {
         return this.setExtension((ExtensionLite)extension, value);
      }

      public final ExtendableBuilder setExtension(final Extension extension, final int index, final Object value) {
         return this.setExtension((ExtensionLite)extension, index, value);
      }

      public ExtendableBuilder setExtension(final GeneratedMessage.GeneratedExtension extension, final int index, final Object value) {
         return this.setExtension((ExtensionLite)extension, index, value);
      }

      public final ExtendableBuilder addExtension(final Extension extension, final Object value) {
         return this.addExtension((ExtensionLite)extension, value);
      }

      public ExtendableBuilder addExtension(final GeneratedMessage.GeneratedExtension extension, final Object value) {
         return this.addExtension((ExtensionLite)extension, value);
      }

      public final ExtendableBuilder clearExtension(final Extension extension) {
         return this.clearExtension((ExtensionLite)extension);
      }

      public ExtendableBuilder clearExtension(final GeneratedMessage.GeneratedExtension extension) {
         return this.clearExtension((ExtensionLite)extension);
      }

      protected boolean extensionsAreInitialized() {
         return this.extensions == null || this.extensions.isInitialized();
      }

      private FieldSet buildExtensions() {
         return this.extensions == null ? FieldSet.emptySet() : this.extensions.buildPartial();
      }

      public boolean isInitialized() {
         return super.isInitialized() && this.extensionsAreInitialized();
      }

      public Map getAllFields() {
         Map<Descriptors.FieldDescriptor, Object> result = super.getAllFieldsMutable();
         if (this.extensions != null) {
            result.putAll(this.extensions.getAllFields());
         }

         return Collections.unmodifiableMap(result);
      }

      public Object getField(final Descriptors.FieldDescriptor field) {
         if (field.isExtension()) {
            this.verifyContainingType(field);
            Object value = this.extensions == null ? null : this.extensions.getField(field);
            if (value == null) {
               return field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE ? DynamicMessage.getDefaultInstance(field.getMessageType()) : field.getDefaultValue();
            } else {
               return value;
            }
         } else {
            return super.getField(field);
         }
      }

      public Message.Builder getFieldBuilder(final Descriptors.FieldDescriptor field) {
         if (field.isExtension()) {
            this.verifyContainingType(field);
            if (field.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE) {
               throw new UnsupportedOperationException("getFieldBuilder() called on a non-Message type.");
            } else {
               this.ensureExtensionsIsMutable();
               Object value = this.extensions.getFieldAllowBuilders(field);
               if (value == null) {
                  Message.Builder builder = DynamicMessage.newBuilder(field.getMessageType());
                  this.extensions.setField(field, builder);
                  this.onChanged();
                  return builder;
               } else if (value instanceof Message.Builder) {
                  return (Message.Builder)value;
               } else if (value instanceof Message) {
                  Message.Builder builder = ((Message)value).toBuilder();
                  this.extensions.setField(field, builder);
                  this.onChanged();
                  return builder;
               } else {
                  throw new UnsupportedOperationException("getRepeatedFieldBuilder() called on a non-Message type.");
               }
            }
         } else {
            return super.getFieldBuilder(field);
         }
      }

      public int getRepeatedFieldCount(final Descriptors.FieldDescriptor field) {
         if (field.isExtension()) {
            this.verifyContainingType(field);
            return this.extensions == null ? 0 : this.extensions.getRepeatedFieldCount(field);
         } else {
            return super.getRepeatedFieldCount(field);
         }
      }

      public Object getRepeatedField(final Descriptors.FieldDescriptor field, final int index) {
         if (field.isExtension()) {
            this.verifyContainingType(field);
            if (this.extensions == null) {
               throw new IndexOutOfBoundsException();
            } else {
               return this.extensions.getRepeatedField(field, index);
            }
         } else {
            return super.getRepeatedField(field, index);
         }
      }

      public Message.Builder getRepeatedFieldBuilder(final Descriptors.FieldDescriptor field, final int index) {
         if (field.isExtension()) {
            this.verifyContainingType(field);
            this.ensureExtensionsIsMutable();
            if (field.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE) {
               throw new UnsupportedOperationException("getRepeatedFieldBuilder() called on a non-Message type.");
            } else {
               Object value = this.extensions.getRepeatedFieldAllowBuilders(field, index);
               if (value instanceof Message.Builder) {
                  return (Message.Builder)value;
               } else if (value instanceof Message) {
                  Message.Builder builder = ((Message)value).toBuilder();
                  this.extensions.setRepeatedField(field, index, builder);
                  this.onChanged();
                  return builder;
               } else {
                  throw new UnsupportedOperationException("getRepeatedFieldBuilder() called on a non-Message type.");
               }
            }
         } else {
            return super.getRepeatedFieldBuilder(field, index);
         }
      }

      public boolean hasField(final Descriptors.FieldDescriptor field) {
         if (!field.isExtension()) {
            return super.hasField(field);
         } else {
            this.verifyContainingType(field);
            return this.extensions != null && this.extensions.hasField(field);
         }
      }

      public ExtendableBuilder setField(final Descriptors.FieldDescriptor field, final Object value) {
         if (field.isExtension()) {
            this.verifyContainingType(field);
            this.ensureExtensionsIsMutable();
            this.extensions.setField(field, value);
            this.onChanged();
            return this;
         } else {
            return (ExtendableBuilder)super.setField(field, value);
         }
      }

      public ExtendableBuilder clearField(final Descriptors.FieldDescriptor field) {
         if (field.isExtension()) {
            this.verifyContainingType(field);
            this.ensureExtensionsIsMutable();
            this.extensions.clearField(field);
            this.onChanged();
            return this;
         } else {
            return (ExtendableBuilder)super.clearField(field);
         }
      }

      public ExtendableBuilder setRepeatedField(final Descriptors.FieldDescriptor field, final int index, final Object value) {
         if (field.isExtension()) {
            this.verifyContainingType(field);
            this.ensureExtensionsIsMutable();
            this.extensions.setRepeatedField(field, index, value);
            this.onChanged();
            return this;
         } else {
            return (ExtendableBuilder)super.setRepeatedField(field, index, value);
         }
      }

      public ExtendableBuilder addRepeatedField(final Descriptors.FieldDescriptor field, final Object value) {
         if (field.isExtension()) {
            this.verifyContainingType(field);
            this.ensureExtensionsIsMutable();
            this.extensions.addRepeatedField(field, value);
            this.onChanged();
            return this;
         } else {
            return (ExtendableBuilder)super.addRepeatedField(field, value);
         }
      }

      public Message.Builder newBuilderForField(final Descriptors.FieldDescriptor field) {
         return (Message.Builder)(field.isExtension() ? DynamicMessage.newBuilder(field.getMessageType()) : super.newBuilderForField(field));
      }

      protected final void mergeExtensionFields(final ExtendableMessage other) {
         if (other.extensions != null) {
            this.ensureExtensionsIsMutable();
            this.extensions.mergeFrom(other.extensions);
            this.onChanged();
         }

      }

      protected boolean parseUnknownField(CodedInputStream input, ExtensionRegistryLite extensionRegistry, int tag) throws IOException {
         this.ensureExtensionsIsMutable();
         return MessageReflection.mergeFieldFrom(input, input.shouldDiscardUnknownFields() ? null : this.getUnknownFieldSetBuilder(), extensionRegistry, this.getDescriptorForType(), new MessageReflection.ExtensionBuilderAdapter(this.extensions), tag);
      }

      private void verifyContainingType(final Descriptors.FieldDescriptor field) {
         if (field.getContainingType() != this.getDescriptorForType()) {
            throw new IllegalArgumentException("FieldDescriptor does not match message type.");
         }
      }
   }

   public static final class FieldAccessorTable {
      private final Descriptors.Descriptor descriptor;
      private final FieldAccessor[] fields;
      private String[] camelCaseNames;
      private final OneofAccessor[] oneofs;
      private volatile boolean initialized;

      public FieldAccessorTable(final Descriptors.Descriptor descriptor, final String[] camelCaseNames, final Class messageClass, final Class builderClass) {
         this(descriptor, camelCaseNames);
         this.ensureFieldAccessorsInitialized(messageClass, builderClass);
      }

      public FieldAccessorTable(final Descriptors.Descriptor descriptor, final String[] camelCaseNames) {
         this.descriptor = descriptor;
         this.camelCaseNames = camelCaseNames;
         this.fields = new FieldAccessor[descriptor.getFields().size()];
         this.oneofs = new OneofAccessor[descriptor.getOneofs().size()];
         this.initialized = false;
      }

      public FieldAccessorTable ensureFieldAccessorsInitialized(Class messageClass, Class builderClass) {
         if (this.initialized) {
            return this;
         } else {
            synchronized(this) {
               if (this.initialized) {
                  return this;
               } else {
                  int fieldsSize = this.fields.length;

                  for(int i = 0; i < fieldsSize; ++i) {
                     Descriptors.FieldDescriptor field = (Descriptors.FieldDescriptor)this.descriptor.getFields().get(i);
                     String containingOneofCamelCaseName = null;
                     if (field.getContainingOneof() != null) {
                        int index = fieldsSize + field.getContainingOneof().getIndex();
                        if (index < this.camelCaseNames.length) {
                           containingOneofCamelCaseName = this.camelCaseNames[index];
                        }
                     }

                     if (field.isRepeated()) {
                        if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                           if (field.isMapField()) {
                              this.fields[i] = new MapFieldAccessor(field, messageClass);
                           } else {
                              this.fields[i] = new RepeatedMessageFieldAccessor(field, this.camelCaseNames[i], messageClass, builderClass);
                           }
                        } else if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.ENUM) {
                           this.fields[i] = new RepeatedEnumFieldAccessor(field, this.camelCaseNames[i], messageClass, builderClass);
                        } else {
                           this.fields[i] = new RepeatedFieldAccessor(field, this.camelCaseNames[i], messageClass, builderClass);
                        }
                     } else if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                        this.fields[i] = new SingularMessageFieldAccessor(field, this.camelCaseNames[i], messageClass, builderClass, containingOneofCamelCaseName);
                     } else if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.ENUM) {
                        this.fields[i] = new SingularEnumFieldAccessor(field, this.camelCaseNames[i], messageClass, builderClass, containingOneofCamelCaseName);
                     } else if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.STRING) {
                        this.fields[i] = new SingularStringFieldAccessor(field, this.camelCaseNames[i], messageClass, builderClass, containingOneofCamelCaseName);
                     } else {
                        this.fields[i] = new SingularFieldAccessor(field, this.camelCaseNames[i], messageClass, builderClass, containingOneofCamelCaseName);
                     }
                  }

                  for(int i = 0; i < this.descriptor.getOneofs().size(); ++i) {
                     if (i < this.descriptor.getRealOneofs().size()) {
                        this.oneofs[i] = new RealOneofAccessor(this.descriptor, i, this.camelCaseNames[i + fieldsSize], messageClass, builderClass);
                     } else {
                        this.oneofs[i] = new SyntheticOneofAccessor(this.descriptor, i);
                     }
                  }

                  this.initialized = true;
                  this.camelCaseNames = null;
                  return this;
               }
            }
         }
      }

      private FieldAccessor getField(final Descriptors.FieldDescriptor field) {
         if (field.getContainingType() != this.descriptor) {
            throw new IllegalArgumentException("FieldDescriptor does not match message type.");
         } else if (field.isExtension()) {
            throw new IllegalArgumentException("This type does not have extensions.");
         } else {
            return this.fields[field.getIndex()];
         }
      }

      private OneofAccessor getOneof(final Descriptors.OneofDescriptor oneof) {
         if (oneof.getContainingType() != this.descriptor) {
            throw new IllegalArgumentException("OneofDescriptor does not match message type.");
         } else {
            return this.oneofs[oneof.getIndex()];
         }
      }

      private static class RealOneofAccessor implements OneofAccessor {
         private final Descriptors.Descriptor descriptor;
         private final java.lang.reflect.Method caseMethod;
         private final java.lang.reflect.Method caseMethodBuilder;
         private final java.lang.reflect.Method clearMethod;

         RealOneofAccessor(final Descriptors.Descriptor descriptor, final int oneofIndex, final String camelCaseName, final Class messageClass, final Class builderClass) {
            this.descriptor = descriptor;
            this.caseMethod = GeneratedMessageV3.getMethodOrDie(messageClass, "get" + camelCaseName + "Case");
            this.caseMethodBuilder = GeneratedMessageV3.getMethodOrDie(builderClass, "get" + camelCaseName + "Case");
            this.clearMethod = GeneratedMessageV3.getMethodOrDie(builderClass, "clear" + camelCaseName);
         }

         public boolean has(final GeneratedMessageV3 message) {
            return ((Internal.EnumLite)GeneratedMessageV3.invokeOrDie(this.caseMethod, message)).getNumber() != 0;
         }

         public boolean has(Builder builder) {
            return ((Internal.EnumLite)GeneratedMessageV3.invokeOrDie(this.caseMethodBuilder, builder)).getNumber() != 0;
         }

         public Descriptors.FieldDescriptor get(final GeneratedMessageV3 message) {
            int fieldNumber = ((Internal.EnumLite)GeneratedMessageV3.invokeOrDie(this.caseMethod, message)).getNumber();
            return fieldNumber > 0 ? this.descriptor.findFieldByNumber(fieldNumber) : null;
         }

         public Descriptors.FieldDescriptor get(Builder builder) {
            int fieldNumber = ((Internal.EnumLite)GeneratedMessageV3.invokeOrDie(this.caseMethodBuilder, builder)).getNumber();
            return fieldNumber > 0 ? this.descriptor.findFieldByNumber(fieldNumber) : null;
         }

         public void clear(final Builder builder) {
            Object unused = GeneratedMessageV3.invokeOrDie(this.clearMethod, builder);
         }
      }

      private static class SyntheticOneofAccessor implements OneofAccessor {
         private final Descriptors.FieldDescriptor fieldDescriptor;

         SyntheticOneofAccessor(final Descriptors.Descriptor descriptor, final int oneofIndex) {
            Descriptors.OneofDescriptor oneofDescriptor = (Descriptors.OneofDescriptor)descriptor.getOneofs().get(oneofIndex);
            this.fieldDescriptor = (Descriptors.FieldDescriptor)oneofDescriptor.getFields().get(0);
         }

         public boolean has(final GeneratedMessageV3 message) {
            return message.hasField(this.fieldDescriptor);
         }

         public boolean has(Builder builder) {
            return builder.hasField(this.fieldDescriptor);
         }

         public Descriptors.FieldDescriptor get(final GeneratedMessageV3 message) {
            return message.hasField(this.fieldDescriptor) ? this.fieldDescriptor : null;
         }

         public Descriptors.FieldDescriptor get(Builder builder) {
            return builder.hasField(this.fieldDescriptor) ? this.fieldDescriptor : null;
         }

         public void clear(final Builder builder) {
            builder.clearField(this.fieldDescriptor);
         }
      }

      private static class SingularFieldAccessor implements FieldAccessor {
         protected final Class type;
         protected final Descriptors.FieldDescriptor field;
         protected final boolean isOneofField;
         protected final boolean hasHasMethod;
         protected final MethodInvoker invoker;

         SingularFieldAccessor(final Descriptors.FieldDescriptor descriptor, final String camelCaseName, final Class messageClass, final Class builderClass, final String containingOneofCamelCaseName) {
            this.isOneofField = descriptor.getRealContainingOneof() != null;
            this.hasHasMethod = descriptor.getFile().getSyntax() == Descriptors.FileDescriptor.Syntax.EDITIONS && descriptor.hasPresence() || descriptor.getFile().getSyntax() == Descriptors.FileDescriptor.Syntax.PROTO2 || descriptor.hasOptionalKeyword() || !this.isOneofField && descriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE;
            ReflectionInvoker reflectionInvoker = new ReflectionInvoker(descriptor, camelCaseName, messageClass, builderClass, containingOneofCamelCaseName, this.isOneofField, this.hasHasMethod);
            this.field = descriptor;
            this.type = reflectionInvoker.getMethod.getReturnType();
            this.invoker = getMethodInvoker(reflectionInvoker);
         }

         static MethodInvoker getMethodInvoker(ReflectionInvoker accessor) {
            return accessor;
         }

         public Object get(final GeneratedMessageV3 message) {
            return this.invoker.get(message);
         }

         public Object get(Builder builder) {
            return this.invoker.get(builder);
         }

         public Object getRaw(final GeneratedMessageV3 message) {
            return this.get(message);
         }

         public void set(final Builder builder, final Object value) {
            this.invoker.set(builder, value);
         }

         public Object getRepeated(final GeneratedMessageV3 message, final int index) {
            throw new UnsupportedOperationException("getRepeatedField() called on a singular field.");
         }

         public Object getRepeated(Builder builder, int index) {
            throw new UnsupportedOperationException("getRepeatedField() called on a singular field.");
         }

         public void setRepeated(final Builder builder, final int index, final Object value) {
            throw new UnsupportedOperationException("setRepeatedField() called on a singular field.");
         }

         public void addRepeated(final Builder builder, final Object value) {
            throw new UnsupportedOperationException("addRepeatedField() called on a singular field.");
         }

         public boolean has(final GeneratedMessageV3 message) {
            if (!this.hasHasMethod) {
               if (this.isOneofField) {
                  return this.invoker.getOneofFieldNumber(message) == this.field.getNumber();
               } else {
                  return !this.get(message).equals(this.field.getDefaultValue());
               }
            } else {
               return this.invoker.has(message);
            }
         }

         public boolean has(Builder builder) {
            if (!this.hasHasMethod) {
               if (this.isOneofField) {
                  return this.invoker.getOneofFieldNumber(builder) == this.field.getNumber();
               } else {
                  return !this.get(builder).equals(this.field.getDefaultValue());
               }
            } else {
               return this.invoker.has(builder);
            }
         }

         public int getRepeatedCount(final GeneratedMessageV3 message) {
            throw new UnsupportedOperationException("getRepeatedFieldSize() called on a singular field.");
         }

         public int getRepeatedCount(Builder builder) {
            throw new UnsupportedOperationException("getRepeatedFieldSize() called on a singular field.");
         }

         public void clear(final Builder builder) {
            this.invoker.clear(builder);
         }

         public Message.Builder newBuilder() {
            throw new UnsupportedOperationException("newBuilderForField() called on a non-Message type.");
         }

         public Message.Builder getBuilder(Builder builder) {
            throw new UnsupportedOperationException("getFieldBuilder() called on a non-Message type.");
         }

         public Message.Builder getRepeatedBuilder(Builder builder, int index) {
            throw new UnsupportedOperationException("getRepeatedFieldBuilder() called on a non-Message type.");
         }

         private static final class ReflectionInvoker implements MethodInvoker {
            private final java.lang.reflect.Method getMethod;
            private final java.lang.reflect.Method getMethodBuilder;
            private final java.lang.reflect.Method setMethod;
            private final java.lang.reflect.Method hasMethod;
            private final java.lang.reflect.Method hasMethodBuilder;
            private final java.lang.reflect.Method clearMethod;
            private final java.lang.reflect.Method caseMethod;
            private final java.lang.reflect.Method caseMethodBuilder;

            ReflectionInvoker(final Descriptors.FieldDescriptor descriptor, final String camelCaseName, final Class messageClass, final Class builderClass, final String containingOneofCamelCaseName, boolean isOneofField, boolean hasHasMethod) {
               this.getMethod = GeneratedMessageV3.getMethodOrDie(messageClass, "get" + camelCaseName);
               this.getMethodBuilder = GeneratedMessageV3.getMethodOrDie(builderClass, "get" + camelCaseName);
               Class<?> type = this.getMethod.getReturnType();
               this.setMethod = GeneratedMessageV3.getMethodOrDie(builderClass, "set" + camelCaseName, type);
               this.hasMethod = hasHasMethod ? GeneratedMessageV3.getMethodOrDie(messageClass, "has" + camelCaseName) : null;
               this.hasMethodBuilder = hasHasMethod ? GeneratedMessageV3.getMethodOrDie(builderClass, "has" + camelCaseName) : null;
               this.clearMethod = GeneratedMessageV3.getMethodOrDie(builderClass, "clear" + camelCaseName);
               this.caseMethod = isOneofField ? GeneratedMessageV3.getMethodOrDie(messageClass, "get" + containingOneofCamelCaseName + "Case") : null;
               this.caseMethodBuilder = isOneofField ? GeneratedMessageV3.getMethodOrDie(builderClass, "get" + containingOneofCamelCaseName + "Case") : null;
            }

            public Object get(final GeneratedMessageV3 message) {
               return GeneratedMessageV3.invokeOrDie(this.getMethod, message);
            }

            public Object get(Builder builder) {
               return GeneratedMessageV3.invokeOrDie(this.getMethodBuilder, builder);
            }

            public int getOneofFieldNumber(final GeneratedMessageV3 message) {
               return ((Internal.EnumLite)GeneratedMessageV3.invokeOrDie(this.caseMethod, message)).getNumber();
            }

            public int getOneofFieldNumber(final Builder builder) {
               return ((Internal.EnumLite)GeneratedMessageV3.invokeOrDie(this.caseMethodBuilder, builder)).getNumber();
            }

            public void set(final Builder builder, final Object value) {
               GeneratedMessageV3.invokeOrDie(this.setMethod, builder, value);
            }

            public boolean has(final GeneratedMessageV3 message) {
               return (Boolean)GeneratedMessageV3.invokeOrDie(this.hasMethod, message);
            }

            public boolean has(Builder builder) {
               return (Boolean)GeneratedMessageV3.invokeOrDie(this.hasMethodBuilder, builder);
            }

            public void clear(final Builder builder) {
               Object unused = GeneratedMessageV3.invokeOrDie(this.clearMethod, builder);
            }
         }

         private interface MethodInvoker {
            Object get(final GeneratedMessageV3 message);

            Object get(Builder builder);

            int getOneofFieldNumber(final GeneratedMessageV3 message);

            int getOneofFieldNumber(final Builder builder);

            void set(final Builder builder, final Object value);

            boolean has(final GeneratedMessageV3 message);

            boolean has(Builder builder);

            void clear(final Builder builder);
         }
      }

      private static class RepeatedFieldAccessor implements FieldAccessor {
         protected final Class type;
         protected final MethodInvoker invoker;

         RepeatedFieldAccessor(final Descriptors.FieldDescriptor descriptor, final String camelCaseName, final Class messageClass, final Class builderClass) {
            ReflectionInvoker reflectionInvoker = new ReflectionInvoker(descriptor, camelCaseName, messageClass, builderClass);
            this.type = reflectionInvoker.getRepeatedMethod.getReturnType();
            this.invoker = getMethodInvoker(reflectionInvoker);
         }

         static MethodInvoker getMethodInvoker(ReflectionInvoker accessor) {
            return accessor;
         }

         public Object get(final GeneratedMessageV3 message) {
            return this.invoker.get(message);
         }

         public Object get(Builder builder) {
            return this.invoker.get(builder);
         }

         public Object getRaw(final GeneratedMessageV3 message) {
            return this.get(message);
         }

         public void set(final Builder builder, final Object value) {
            this.clear(builder);

            for(Object element : (List)value) {
               this.addRepeated(builder, element);
            }

         }

         public Object getRepeated(final GeneratedMessageV3 message, final int index) {
            return this.invoker.getRepeated(message, index);
         }

         public Object getRepeated(Builder builder, int index) {
            return this.invoker.getRepeated(builder, index);
         }

         public void setRepeated(final Builder builder, final int index, final Object value) {
            this.invoker.setRepeated(builder, index, value);
         }

         public void addRepeated(final Builder builder, final Object value) {
            this.invoker.addRepeated(builder, value);
         }

         public boolean has(final GeneratedMessageV3 message) {
            throw new UnsupportedOperationException("hasField() called on a repeated field.");
         }

         public boolean has(Builder builder) {
            throw new UnsupportedOperationException("hasField() called on a repeated field.");
         }

         public int getRepeatedCount(final GeneratedMessageV3 message) {
            return this.invoker.getRepeatedCount(message);
         }

         public int getRepeatedCount(Builder builder) {
            return this.invoker.getRepeatedCount(builder);
         }

         public void clear(final Builder builder) {
            this.invoker.clear(builder);
         }

         public Message.Builder newBuilder() {
            throw new UnsupportedOperationException("newBuilderForField() called on a non-Message type.");
         }

         public Message.Builder getBuilder(Builder builder) {
            throw new UnsupportedOperationException("getFieldBuilder() called on a non-Message type.");
         }

         public Message.Builder getRepeatedBuilder(Builder builder, int index) {
            throw new UnsupportedOperationException("getRepeatedFieldBuilder() called on a non-Message type.");
         }

         private static final class ReflectionInvoker implements MethodInvoker {
            private final java.lang.reflect.Method getMethod;
            private final java.lang.reflect.Method getMethodBuilder;
            private final java.lang.reflect.Method getRepeatedMethod;
            private final java.lang.reflect.Method getRepeatedMethodBuilder;
            private final java.lang.reflect.Method setRepeatedMethod;
            private final java.lang.reflect.Method addRepeatedMethod;
            private final java.lang.reflect.Method getCountMethod;
            private final java.lang.reflect.Method getCountMethodBuilder;
            private final java.lang.reflect.Method clearMethod;

            ReflectionInvoker(final Descriptors.FieldDescriptor descriptor, final String camelCaseName, final Class messageClass, final Class builderClass) {
               this.getMethod = GeneratedMessageV3.getMethodOrDie(messageClass, "get" + camelCaseName + "List");
               this.getMethodBuilder = GeneratedMessageV3.getMethodOrDie(builderClass, "get" + camelCaseName + "List");
               this.getRepeatedMethod = GeneratedMessageV3.getMethodOrDie(messageClass, "get" + camelCaseName, Integer.TYPE);
               this.getRepeatedMethodBuilder = GeneratedMessageV3.getMethodOrDie(builderClass, "get" + camelCaseName, Integer.TYPE);
               Class<?> type = this.getRepeatedMethod.getReturnType();
               this.setRepeatedMethod = GeneratedMessageV3.getMethodOrDie(builderClass, "set" + camelCaseName, Integer.TYPE, type);
               this.addRepeatedMethod = GeneratedMessageV3.getMethodOrDie(builderClass, "add" + camelCaseName, type);
               this.getCountMethod = GeneratedMessageV3.getMethodOrDie(messageClass, "get" + camelCaseName + "Count");
               this.getCountMethodBuilder = GeneratedMessageV3.getMethodOrDie(builderClass, "get" + camelCaseName + "Count");
               this.clearMethod = GeneratedMessageV3.getMethodOrDie(builderClass, "clear" + camelCaseName);
            }

            public Object get(final GeneratedMessageV3 message) {
               return GeneratedMessageV3.invokeOrDie(this.getMethod, message);
            }

            public Object get(Builder builder) {
               return GeneratedMessageV3.invokeOrDie(this.getMethodBuilder, builder);
            }

            public Object getRepeated(final GeneratedMessageV3 message, final int index) {
               return GeneratedMessageV3.invokeOrDie(this.getRepeatedMethod, message, index);
            }

            public Object getRepeated(Builder builder, int index) {
               return GeneratedMessageV3.invokeOrDie(this.getRepeatedMethodBuilder, builder, index);
            }

            public void setRepeated(final Builder builder, final int index, final Object value) {
               GeneratedMessageV3.invokeOrDie(this.setRepeatedMethod, builder, index, value);
            }

            public void addRepeated(final Builder builder, final Object value) {
               GeneratedMessageV3.invokeOrDie(this.addRepeatedMethod, builder, value);
            }

            public int getRepeatedCount(final GeneratedMessageV3 message) {
               return (Integer)GeneratedMessageV3.invokeOrDie(this.getCountMethod, message);
            }

            public int getRepeatedCount(Builder builder) {
               return (Integer)GeneratedMessageV3.invokeOrDie(this.getCountMethodBuilder, builder);
            }

            public void clear(final Builder builder) {
               Object unused = GeneratedMessageV3.invokeOrDie(this.clearMethod, builder);
            }
         }

         interface MethodInvoker {
            Object get(final GeneratedMessageV3 message);

            Object get(Builder builder);

            Object getRepeated(final GeneratedMessageV3 message, final int index);

            Object getRepeated(Builder builder, int index);

            void setRepeated(final Builder builder, final int index, final Object value);

            void addRepeated(final Builder builder, final Object value);

            int getRepeatedCount(final GeneratedMessageV3 message);

            int getRepeatedCount(Builder builder);

            void clear(final Builder builder);
         }
      }

      private static class MapFieldAccessor implements FieldAccessor {
         private final Descriptors.FieldDescriptor field;
         private final Message mapEntryMessageDefaultInstance;

         MapFieldAccessor(final Descriptors.FieldDescriptor descriptor, final Class messageClass) {
            this.field = descriptor;
            java.lang.reflect.Method getDefaultInstanceMethod = GeneratedMessageV3.getMethodOrDie(messageClass, "getDefaultInstance");
            MapFieldReflectionAccessor defaultMapField = this.getMapField((GeneratedMessageV3)GeneratedMessageV3.invokeOrDie(getDefaultInstanceMethod, (Object)null));
            this.mapEntryMessageDefaultInstance = defaultMapField.getMapEntryMessageDefaultInstance();
         }

         private MapFieldReflectionAccessor getMapField(GeneratedMessageV3 message) {
            return message.internalGetMapFieldReflection(this.field.getNumber());
         }

         private MapFieldReflectionAccessor getMapField(Builder builder) {
            return builder.internalGetMapFieldReflection(this.field.getNumber());
         }

         private MapFieldReflectionAccessor getMutableMapField(Builder builder) {
            return builder.internalGetMutableMapFieldReflection(this.field.getNumber());
         }

         private Message coerceType(Message value) {
            if (value == null) {
               return null;
            } else {
               return this.mapEntryMessageDefaultInstance.getClass().isInstance(value) ? value : this.mapEntryMessageDefaultInstance.toBuilder().mergeFrom(value).build();
            }
         }

         public Object get(GeneratedMessageV3 message) {
            List<Object> result = new ArrayList();

            for(int i = 0; i < this.getRepeatedCount(message); ++i) {
               result.add(this.getRepeated(message, i));
            }

            return Collections.unmodifiableList(result);
         }

         public Object get(Builder builder) {
            List<Object> result = new ArrayList();

            for(int i = 0; i < this.getRepeatedCount(builder); ++i) {
               result.add(this.getRepeated(builder, i));
            }

            return Collections.unmodifiableList(result);
         }

         public Object getRaw(GeneratedMessageV3 message) {
            return this.get(message);
         }

         public void set(Builder builder, Object value) {
            this.clear(builder);

            for(Object entry : (List)value) {
               this.addRepeated(builder, entry);
            }

         }

         public Object getRepeated(GeneratedMessageV3 message, int index) {
            return this.getMapField(message).getList().get(index);
         }

         public Object getRepeated(Builder builder, int index) {
            return this.getMapField(builder).getList().get(index);
         }

         public void setRepeated(Builder builder, int index, Object value) {
            this.getMutableMapField(builder).getMutableList().set(index, this.coerceType((Message)value));
         }

         public void addRepeated(Builder builder, Object value) {
            this.getMutableMapField(builder).getMutableList().add(this.coerceType((Message)value));
         }

         public boolean has(GeneratedMessageV3 message) {
            throw new UnsupportedOperationException("hasField() is not supported for repeated fields.");
         }

         public boolean has(Builder builder) {
            throw new UnsupportedOperationException("hasField() is not supported for repeated fields.");
         }

         public int getRepeatedCount(GeneratedMessageV3 message) {
            return this.getMapField(message).getList().size();
         }

         public int getRepeatedCount(Builder builder) {
            return this.getMapField(builder).getList().size();
         }

         public void clear(Builder builder) {
            this.getMutableMapField(builder).getMutableList().clear();
         }

         public Message.Builder newBuilder() {
            return this.mapEntryMessageDefaultInstance.newBuilderForType();
         }

         public Message.Builder getBuilder(Builder builder) {
            throw new UnsupportedOperationException("Nested builder not supported for map fields.");
         }

         public Message.Builder getRepeatedBuilder(Builder builder, int index) {
            throw new UnsupportedOperationException("Map fields cannot be repeated");
         }
      }

      private static final class SingularEnumFieldAccessor extends SingularFieldAccessor {
         private final Descriptors.EnumDescriptor enumDescriptor;
         private final java.lang.reflect.Method valueOfMethod;
         private final java.lang.reflect.Method getValueDescriptorMethod;
         private final boolean supportUnknownEnumValue;
         private java.lang.reflect.Method getValueMethod;
         private java.lang.reflect.Method getValueMethodBuilder;
         private java.lang.reflect.Method setValueMethod;

         SingularEnumFieldAccessor(final Descriptors.FieldDescriptor descriptor, final String camelCaseName, final Class messageClass, final Class builderClass, final String containingOneofCamelCaseName) {
            super(descriptor, camelCaseName, messageClass, builderClass, containingOneofCamelCaseName);
            this.enumDescriptor = descriptor.getEnumType();
            this.valueOfMethod = GeneratedMessageV3.getMethodOrDie(this.type, "valueOf", Descriptors.EnumValueDescriptor.class);
            this.getValueDescriptorMethod = GeneratedMessageV3.getMethodOrDie(this.type, "getValueDescriptor");
            this.supportUnknownEnumValue = !descriptor.legacyEnumFieldTreatedAsClosed();
            if (this.supportUnknownEnumValue) {
               this.getValueMethod = GeneratedMessageV3.getMethodOrDie(messageClass, "get" + camelCaseName + "Value");
               this.getValueMethodBuilder = GeneratedMessageV3.getMethodOrDie(builderClass, "get" + camelCaseName + "Value");
               this.setValueMethod = GeneratedMessageV3.getMethodOrDie(builderClass, "set" + camelCaseName + "Value", Integer.TYPE);
            }

         }

         public Object get(final GeneratedMessageV3 message) {
            if (this.supportUnknownEnumValue) {
               int value = (Integer)GeneratedMessageV3.invokeOrDie(this.getValueMethod, message);
               return this.enumDescriptor.findValueByNumberCreatingIfUnknown(value);
            } else {
               return GeneratedMessageV3.invokeOrDie(this.getValueDescriptorMethod, super.get(message));
            }
         }

         public Object get(final Builder builder) {
            if (this.supportUnknownEnumValue) {
               int value = (Integer)GeneratedMessageV3.invokeOrDie(this.getValueMethodBuilder, builder);
               return this.enumDescriptor.findValueByNumberCreatingIfUnknown(value);
            } else {
               return GeneratedMessageV3.invokeOrDie(this.getValueDescriptorMethod, super.get(builder));
            }
         }

         public void set(final Builder builder, final Object value) {
            if (this.supportUnknownEnumValue) {
               GeneratedMessageV3.invokeOrDie(this.setValueMethod, builder, ((Descriptors.EnumValueDescriptor)value).getNumber());
            } else {
               super.set(builder, GeneratedMessageV3.invokeOrDie(this.valueOfMethod, (Object)null, value));
            }
         }
      }

      private static final class RepeatedEnumFieldAccessor extends RepeatedFieldAccessor {
         private final Descriptors.EnumDescriptor enumDescriptor;
         private final java.lang.reflect.Method valueOfMethod;
         private final java.lang.reflect.Method getValueDescriptorMethod;
         private final boolean supportUnknownEnumValue;
         private java.lang.reflect.Method getRepeatedValueMethod;
         private java.lang.reflect.Method getRepeatedValueMethodBuilder;
         private java.lang.reflect.Method setRepeatedValueMethod;
         private java.lang.reflect.Method addRepeatedValueMethod;

         RepeatedEnumFieldAccessor(final Descriptors.FieldDescriptor descriptor, final String camelCaseName, final Class messageClass, final Class builderClass) {
            super(descriptor, camelCaseName, messageClass, builderClass);
            this.enumDescriptor = descriptor.getEnumType();
            this.valueOfMethod = GeneratedMessageV3.getMethodOrDie(this.type, "valueOf", Descriptors.EnumValueDescriptor.class);
            this.getValueDescriptorMethod = GeneratedMessageV3.getMethodOrDie(this.type, "getValueDescriptor");
            this.supportUnknownEnumValue = !descriptor.legacyEnumFieldTreatedAsClosed();
            if (this.supportUnknownEnumValue) {
               this.getRepeatedValueMethod = GeneratedMessageV3.getMethodOrDie(messageClass, "get" + camelCaseName + "Value", Integer.TYPE);
               this.getRepeatedValueMethodBuilder = GeneratedMessageV3.getMethodOrDie(builderClass, "get" + camelCaseName + "Value", Integer.TYPE);
               this.setRepeatedValueMethod = GeneratedMessageV3.getMethodOrDie(builderClass, "set" + camelCaseName + "Value", Integer.TYPE, Integer.TYPE);
               this.addRepeatedValueMethod = GeneratedMessageV3.getMethodOrDie(builderClass, "add" + camelCaseName + "Value", Integer.TYPE);
            }

         }

         public Object get(final GeneratedMessageV3 message) {
            List<Object> newList = new ArrayList();
            int size = this.getRepeatedCount(message);

            for(int i = 0; i < size; ++i) {
               newList.add(this.getRepeated(message, i));
            }

            return Collections.unmodifiableList(newList);
         }

         public Object get(final Builder builder) {
            List<Object> newList = new ArrayList();
            int size = this.getRepeatedCount(builder);

            for(int i = 0; i < size; ++i) {
               newList.add(this.getRepeated(builder, i));
            }

            return Collections.unmodifiableList(newList);
         }

         public Object getRepeated(final GeneratedMessageV3 message, final int index) {
            if (this.supportUnknownEnumValue) {
               int value = (Integer)GeneratedMessageV3.invokeOrDie(this.getRepeatedValueMethod, message, index);
               return this.enumDescriptor.findValueByNumberCreatingIfUnknown(value);
            } else {
               return GeneratedMessageV3.invokeOrDie(this.getValueDescriptorMethod, super.getRepeated(message, index));
            }
         }

         public Object getRepeated(final Builder builder, final int index) {
            if (this.supportUnknownEnumValue) {
               int value = (Integer)GeneratedMessageV3.invokeOrDie(this.getRepeatedValueMethodBuilder, builder, index);
               return this.enumDescriptor.findValueByNumberCreatingIfUnknown(value);
            } else {
               return GeneratedMessageV3.invokeOrDie(this.getValueDescriptorMethod, super.getRepeated(builder, index));
            }
         }

         public void setRepeated(final Builder builder, final int index, final Object value) {
            if (this.supportUnknownEnumValue) {
               GeneratedMessageV3.invokeOrDie(this.setRepeatedValueMethod, builder, index, ((Descriptors.EnumValueDescriptor)value).getNumber());
            } else {
               super.setRepeated(builder, index, GeneratedMessageV3.invokeOrDie(this.valueOfMethod, (Object)null, value));
            }
         }

         public void addRepeated(final Builder builder, final Object value) {
            if (this.supportUnknownEnumValue) {
               GeneratedMessageV3.invokeOrDie(this.addRepeatedValueMethod, builder, ((Descriptors.EnumValueDescriptor)value).getNumber());
            } else {
               super.addRepeated(builder, GeneratedMessageV3.invokeOrDie(this.valueOfMethod, (Object)null, value));
            }
         }
      }

      private static final class SingularStringFieldAccessor extends SingularFieldAccessor {
         private final java.lang.reflect.Method getBytesMethod;
         private final java.lang.reflect.Method setBytesMethodBuilder;

         SingularStringFieldAccessor(final Descriptors.FieldDescriptor descriptor, final String camelCaseName, final Class messageClass, final Class builderClass, final String containingOneofCamelCaseName) {
            super(descriptor, camelCaseName, messageClass, builderClass, containingOneofCamelCaseName);
            this.getBytesMethod = GeneratedMessageV3.getMethodOrDie(messageClass, "get" + camelCaseName + "Bytes");
            this.setBytesMethodBuilder = GeneratedMessageV3.getMethodOrDie(builderClass, "set" + camelCaseName + "Bytes", ByteString.class);
         }

         public Object getRaw(final GeneratedMessageV3 message) {
            return GeneratedMessageV3.invokeOrDie(this.getBytesMethod, message);
         }

         public void set(Builder builder, Object value) {
            if (value instanceof ByteString) {
               GeneratedMessageV3.invokeOrDie(this.setBytesMethodBuilder, builder, value);
            } else {
               super.set(builder, value);
            }

         }
      }

      private static final class SingularMessageFieldAccessor extends SingularFieldAccessor {
         private final java.lang.reflect.Method newBuilderMethod;
         private final java.lang.reflect.Method getBuilderMethodBuilder;

         SingularMessageFieldAccessor(final Descriptors.FieldDescriptor descriptor, final String camelCaseName, final Class messageClass, final Class builderClass, final String containingOneofCamelCaseName) {
            super(descriptor, camelCaseName, messageClass, builderClass, containingOneofCamelCaseName);
            this.newBuilderMethod = GeneratedMessageV3.getMethodOrDie(this.type, "newBuilder");
            this.getBuilderMethodBuilder = GeneratedMessageV3.getMethodOrDie(builderClass, "get" + camelCaseName + "Builder");
         }

         private Object coerceType(final Object value) {
            return this.type.isInstance(value) ? value : ((Message.Builder)GeneratedMessageV3.invokeOrDie(this.newBuilderMethod, (Object)null)).mergeFrom((Message)value).buildPartial();
         }

         public void set(final Builder builder, final Object value) {
            super.set(builder, this.coerceType(value));
         }

         public Message.Builder newBuilder() {
            return (Message.Builder)GeneratedMessageV3.invokeOrDie(this.newBuilderMethod, (Object)null);
         }

         public Message.Builder getBuilder(Builder builder) {
            return (Message.Builder)GeneratedMessageV3.invokeOrDie(this.getBuilderMethodBuilder, builder);
         }
      }

      private static final class RepeatedMessageFieldAccessor extends RepeatedFieldAccessor {
         private final java.lang.reflect.Method newBuilderMethod;
         private final java.lang.reflect.Method getBuilderMethodBuilder;

         RepeatedMessageFieldAccessor(final Descriptors.FieldDescriptor descriptor, final String camelCaseName, final Class messageClass, final Class builderClass) {
            super(descriptor, camelCaseName, messageClass, builderClass);
            this.newBuilderMethod = GeneratedMessageV3.getMethodOrDie(this.type, "newBuilder");
            this.getBuilderMethodBuilder = GeneratedMessageV3.getMethodOrDie(builderClass, "get" + camelCaseName + "Builder", Integer.TYPE);
         }

         private Object coerceType(final Object value) {
            return this.type.isInstance(value) ? value : ((Message.Builder)GeneratedMessageV3.invokeOrDie(this.newBuilderMethod, (Object)null)).mergeFrom((Message)value).build();
         }

         public void setRepeated(final Builder builder, final int index, final Object value) {
            super.setRepeated(builder, index, this.coerceType(value));
         }

         public void addRepeated(final Builder builder, final Object value) {
            super.addRepeated(builder, this.coerceType(value));
         }

         public Message.Builder newBuilder() {
            return (Message.Builder)GeneratedMessageV3.invokeOrDie(this.newBuilderMethod, (Object)null);
         }

         public Message.Builder getRepeatedBuilder(final Builder builder, final int index) {
            return (Message.Builder)GeneratedMessageV3.invokeOrDie(this.getBuilderMethodBuilder, builder, index);
         }
      }

      private interface FieldAccessor {
         Object get(GeneratedMessageV3 message);

         Object get(Builder builder);

         Object getRaw(GeneratedMessageV3 message);

         void set(Builder builder, Object value);

         Object getRepeated(GeneratedMessageV3 message, int index);

         Object getRepeated(Builder builder, int index);

         void setRepeated(Builder builder, int index, Object value);

         void addRepeated(Builder builder, Object value);

         boolean has(GeneratedMessageV3 message);

         boolean has(Builder builder);

         int getRepeatedCount(GeneratedMessageV3 message);

         int getRepeatedCount(Builder builder);

         void clear(Builder builder);

         Message.Builder newBuilder();

         Message.Builder getBuilder(Builder builder);

         Message.Builder getRepeatedBuilder(Builder builder, int index);
      }

      private interface OneofAccessor {
         boolean has(final GeneratedMessageV3 message);

         boolean has(Builder builder);

         Descriptors.FieldDescriptor get(final GeneratedMessageV3 message);

         Descriptors.FieldDescriptor get(Builder builder);

         void clear(final Builder builder);
      }
   }

   protected interface BuilderParent extends AbstractMessage.BuilderParent {
   }

   interface ExtensionDescriptorRetriever {
      Descriptors.FieldDescriptor getDescriptor();
   }
}
