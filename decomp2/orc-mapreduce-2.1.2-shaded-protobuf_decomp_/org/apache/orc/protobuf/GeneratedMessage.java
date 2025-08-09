package org.apache.orc.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public abstract class GeneratedMessage extends AbstractMessage implements Serializable {
   private static final long serialVersionUID = 1L;
   protected static boolean alwaysUseFieldBuilders = false;
   protected UnknownFieldSet unknownFields;

   protected GeneratedMessage() {
      this.unknownFields = UnknownFieldSet.getDefaultInstance();
   }

   protected GeneratedMessage(Builder builder) {
      this.unknownFields = builder.getUnknownFields();
   }

   public Parser getParserForType() {
      throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
   }

   static void enableAlwaysUseFieldBuildersForTesting() {
      alwaysUseFieldBuilders = true;
   }

   protected abstract FieldAccessorTable internalGetFieldAccessorTable();

   public Descriptors.Descriptor getDescriptorForType() {
      return this.internalGetFieldAccessorTable().descriptor;
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
      throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
   }

   protected boolean parseUnknownField(CodedInputStream input, UnknownFieldSet.Builder unknownFields, ExtensionRegistryLite extensionRegistry, int tag) throws IOException {
      return unknownFields.mergeFieldFrom(tag, input);
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

   public static GeneratedExtension newMessageScopedGeneratedExtension(final Message scope, final int descriptorIndex, final Class singularType, final Message defaultInstance) {
      return new GeneratedExtension(new CachedDescriptorRetriever() {
         public Descriptors.FieldDescriptor loadDescriptor() {
            return (Descriptors.FieldDescriptor)scope.getDescriptorForType().getExtensions().get(descriptorIndex);
         }
      }, singularType, defaultInstance, Extension.ExtensionType.IMMUTABLE);
   }

   public static GeneratedExtension newFileScopedGeneratedExtension(final Class singularType, final Message defaultInstance) {
      return new GeneratedExtension((ExtensionDescriptorRetriever)null, singularType, defaultInstance, Extension.ExtensionType.IMMUTABLE);
   }

   public static GeneratedExtension newMessageScopedGeneratedExtension(final Message scope, final String name, final Class singularType, final Message defaultInstance) {
      return new GeneratedExtension(new CachedDescriptorRetriever() {
         protected Descriptors.FieldDescriptor loadDescriptor() {
            return scope.getDescriptorForType().findFieldByName(name);
         }
      }, singularType, defaultInstance, Extension.ExtensionType.MUTABLE);
   }

   public static GeneratedExtension newFileScopedGeneratedExtension(final Class singularType, final Message defaultInstance, final String descriptorOuterClass, final String extensionName) {
      return new GeneratedExtension(new CachedDescriptorRetriever() {
         protected Descriptors.FieldDescriptor loadDescriptor() {
            try {
               Class clazz = singularType.getClassLoader().loadClass(descriptorOuterClass);
               Descriptors.FileDescriptor file = (Descriptors.FileDescriptor)clazz.getField("descriptor").get((Object)null);
               return file.findExtensionByName(extensionName);
            } catch (Exception e) {
               throw new RuntimeException("Cannot load descriptors: " + descriptorOuterClass + " is not a valid descriptor class name", e);
            }
         }
      }, singularType, defaultInstance, Extension.ExtensionType.MUTABLE);
   }

   private static java.lang.reflect.Method getMethodOrDie(final Class clazz, final String name, final Class... params) {
      try {
         return clazz.getMethod(name, params);
      } catch (NoSuchMethodException e) {
         throw new RuntimeException("Generated message class \"" + clazz.getName() + "\" missing method \"" + name + "\".", e);
      }
   }

   private static Object invokeOrDie(final java.lang.reflect.Method method, final Object object, final Object... params) {
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

   protected MapField internalGetMapField(int fieldNumber) {
      throw new RuntimeException("No map fields found in " + this.getClass().getName());
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

   public abstract static class Builder extends AbstractMessage.Builder {
      private BuilderParent builderParent;
      private BuilderParentImpl meAsParent;
      private boolean isClean;
      private UnknownFieldSet unknownFields;

      protected Builder() {
         this((BuilderParent)null);
      }

      protected Builder(BuilderParent builderParent) {
         this.unknownFields = UnknownFieldSet.getDefaultInstance();
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
         BuilderType builder = (BuilderType)((Builder)this.getDefaultInstanceForType().newBuilderForType());
         builder.mergeFrom(this.buildPartial());
         return builder;
      }

      public Builder clear() {
         this.unknownFields = UnknownFieldSet.getDefaultInstance();
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

      public Builder setUnknownFields(final UnknownFieldSet unknownFields) {
         this.unknownFields = unknownFields;
         this.onChanged();
         return this;
      }

      public Builder mergeUnknownFields(final UnknownFieldSet unknownFields) {
         this.unknownFields = UnknownFieldSet.newBuilder(this.unknownFields).mergeFrom(unknownFields).build();
         this.onChanged();
         return this;
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
         return this.unknownFields;
      }

      protected boolean parseUnknownField(final CodedInputStream input, final UnknownFieldSet.Builder unknownFields, final ExtensionRegistryLite extensionRegistry, final int tag) throws IOException {
         return unknownFields.mergeFieldFrom(tag, input);
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

      protected MapField internalGetMapField(int fieldNumber) {
         throw new RuntimeException("No map fields found in " + this.getClass().getName());
      }

      protected MapField internalGetMutableMapField(int fieldNumber) {
         throw new RuntimeException("No map fields found in " + this.getClass().getName());
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

      boolean hasExtension(GeneratedExtension extension);

      int getExtensionCount(Extension extension);

      int getExtensionCount(GeneratedExtension extension);

      Object getExtension(Extension extension);

      Object getExtension(GeneratedExtension extension);

      Object getExtension(Extension extension, int index);

      Object getExtension(GeneratedExtension extension, int index);
   }

   public abstract static class ExtendableMessage extends GeneratedMessage implements ExtendableMessageOrBuilder {
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
         Extension<MessageType, Type> extension = GeneratedMessage.checkNotLite(extensionLite);
         this.verifyExtensionContainingType(extension);
         return this.extensions.hasField(extension.getDescriptor());
      }

      public final int getExtensionCount(final ExtensionLite extensionLite) {
         Extension<MessageType, List<Type>> extension = GeneratedMessage.checkNotLite(extensionLite);
         this.verifyExtensionContainingType(extension);
         Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
         return this.extensions.getRepeatedFieldCount(descriptor);
      }

      public final Object getExtension(final ExtensionLite extensionLite) {
         Extension<MessageType, Type> extension = GeneratedMessage.checkNotLite(extensionLite);
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
         Extension<MessageType, List<Type>> extension = GeneratedMessage.checkNotLite(extensionLite);
         this.verifyExtensionContainingType(extension);
         Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
         return extension.singularFromReflectionType(this.extensions.getRepeatedField(descriptor, index));
      }

      public final boolean hasExtension(final Extension extension) {
         return this.hasExtension((ExtensionLite)extension);
      }

      public final boolean hasExtension(final GeneratedExtension extension) {
         return this.hasExtension((ExtensionLite)extension);
      }

      public final int getExtensionCount(final Extension extension) {
         return this.getExtensionCount((ExtensionLite)extension);
      }

      public final int getExtensionCount(final GeneratedExtension extension) {
         return this.getExtensionCount((ExtensionLite)extension);
      }

      public final Object getExtension(final Extension extension) {
         return this.getExtension((ExtensionLite)extension);
      }

      public final Object getExtension(final GeneratedExtension extension) {
         return this.getExtension((ExtensionLite)extension);
      }

      public final Object getExtension(final Extension extension, final int index) {
         return this.getExtension((ExtensionLite)extension, index);
      }

      public final Object getExtension(final GeneratedExtension extension, final int index) {
         return this.getExtension((ExtensionLite)extension, index);
      }

      protected boolean extensionsAreInitialized() {
         return this.extensions.isInitialized();
      }

      public boolean isInitialized() {
         return super.isInitialized() && this.extensionsAreInitialized();
      }

      protected boolean parseUnknownField(CodedInputStream input, UnknownFieldSet.Builder unknownFields, ExtensionRegistryLite extensionRegistry, int tag) throws IOException {
         return MessageReflection.mergeFieldFrom(input, unknownFields, extensionRegistry, this.getDescriptorForType(), new MessageReflection.ExtensionAdapter(this.extensions), tag);
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
      private FieldSet extensions = FieldSet.emptySet();

      protected ExtendableBuilder() {
      }

      protected ExtendableBuilder(BuilderParent parent) {
         super(parent);
      }

      void internalSetExtensionSet(FieldSet extensions) {
         this.extensions = extensions;
      }

      public ExtendableBuilder clear() {
         this.extensions = FieldSet.emptySet();
         return (ExtendableBuilder)super.clear();
      }

      public ExtendableBuilder clone() {
         return (ExtendableBuilder)super.clone();
      }

      private void ensureExtensionsIsMutable() {
         if (this.extensions.isImmutable()) {
            this.extensions = this.extensions.clone();
         }

      }

      private void verifyExtensionContainingType(final Extension extension) {
         if (extension.getDescriptor().getContainingType() != this.getDescriptorForType()) {
            throw new IllegalArgumentException("Extension is for type \"" + extension.getDescriptor().getContainingType().getFullName() + "\" which does not match message type \"" + this.getDescriptorForType().getFullName() + "\".");
         }
      }

      public final boolean hasExtension(final ExtensionLite extensionLite) {
         Extension<MessageType, Type> extension = GeneratedMessage.checkNotLite(extensionLite);
         this.verifyExtensionContainingType(extension);
         return this.extensions.hasField(extension.getDescriptor());
      }

      public final int getExtensionCount(final ExtensionLite extensionLite) {
         Extension<MessageType, List<Type>> extension = GeneratedMessage.checkNotLite(extensionLite);
         this.verifyExtensionContainingType(extension);
         Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
         return this.extensions.getRepeatedFieldCount(descriptor);
      }

      public final Object getExtension(final ExtensionLite extensionLite) {
         Extension<MessageType, Type> extension = GeneratedMessage.checkNotLite(extensionLite);
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
         Extension<MessageType, List<Type>> extension = GeneratedMessage.checkNotLite(extensionLite);
         this.verifyExtensionContainingType(extension);
         Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
         return extension.singularFromReflectionType(this.extensions.getRepeatedField(descriptor, index));
      }

      public final ExtendableBuilder setExtension(final ExtensionLite extensionLite, final Object value) {
         Extension<MessageType, Type> extension = GeneratedMessage.checkNotLite(extensionLite);
         this.verifyExtensionContainingType(extension);
         this.ensureExtensionsIsMutable();
         Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
         this.extensions.setField(descriptor, extension.toReflectionType(value));
         this.onChanged();
         return this;
      }

      public final ExtendableBuilder setExtension(final ExtensionLite extensionLite, final int index, final Object value) {
         Extension<MessageType, List<Type>> extension = GeneratedMessage.checkNotLite(extensionLite);
         this.verifyExtensionContainingType(extension);
         this.ensureExtensionsIsMutable();
         Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
         this.extensions.setRepeatedField(descriptor, index, extension.singularToReflectionType(value));
         this.onChanged();
         return this;
      }

      public final ExtendableBuilder addExtension(final ExtensionLite extensionLite, final Object value) {
         Extension<MessageType, List<Type>> extension = GeneratedMessage.checkNotLite(extensionLite);
         this.verifyExtensionContainingType(extension);
         this.ensureExtensionsIsMutable();
         Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
         this.extensions.addRepeatedField(descriptor, extension.singularToReflectionType(value));
         this.onChanged();
         return this;
      }

      public final ExtendableBuilder clearExtension(final ExtensionLite extensionLite) {
         Extension<MessageType, ?> extension = GeneratedMessage.checkNotLite(extensionLite);
         this.verifyExtensionContainingType(extension);
         this.ensureExtensionsIsMutable();
         this.extensions.clearField(extension.getDescriptor());
         this.onChanged();
         return this;
      }

      public final boolean hasExtension(final Extension extension) {
         return this.hasExtension((ExtensionLite)extension);
      }

      public final boolean hasExtension(final GeneratedExtension extension) {
         return this.hasExtension((ExtensionLite)extension);
      }

      public final int getExtensionCount(final Extension extension) {
         return this.getExtensionCount((ExtensionLite)extension);
      }

      public final int getExtensionCount(final GeneratedExtension extension) {
         return this.getExtensionCount((ExtensionLite)extension);
      }

      public final Object getExtension(final Extension extension) {
         return this.getExtension((ExtensionLite)extension);
      }

      public final Object getExtension(final GeneratedExtension extension) {
         return this.getExtension((ExtensionLite)extension);
      }

      public final Object getExtension(final Extension extension, final int index) {
         return this.getExtension((ExtensionLite)extension, index);
      }

      public final Object getExtension(final GeneratedExtension extension, final int index) {
         return this.getExtension((ExtensionLite)extension, index);
      }

      public final ExtendableBuilder setExtension(final Extension extension, final Object value) {
         return this.setExtension((ExtensionLite)extension, value);
      }

      public ExtendableBuilder setExtension(final GeneratedExtension extension, final Object value) {
         return this.setExtension((ExtensionLite)extension, value);
      }

      public final ExtendableBuilder setExtension(final Extension extension, final int index, final Object value) {
         return this.setExtension((ExtensionLite)extension, index, value);
      }

      public ExtendableBuilder setExtension(final GeneratedExtension extension, final int index, final Object value) {
         return this.setExtension((ExtensionLite)extension, index, value);
      }

      public final ExtendableBuilder addExtension(final Extension extension, final Object value) {
         return this.addExtension((ExtensionLite)extension, value);
      }

      public ExtendableBuilder addExtension(final GeneratedExtension extension, final Object value) {
         return this.addExtension((ExtensionLite)extension, value);
      }

      public final ExtendableBuilder clearExtension(final Extension extension) {
         return this.clearExtension((ExtensionLite)extension);
      }

      public ExtendableBuilder clearExtension(final GeneratedExtension extension) {
         return this.clearExtension((ExtensionLite)extension);
      }

      protected boolean extensionsAreInitialized() {
         return this.extensions.isInitialized();
      }

      private FieldSet buildExtensions() {
         this.extensions.makeImmutable();
         return this.extensions;
      }

      public boolean isInitialized() {
         return super.isInitialized() && this.extensionsAreInitialized();
      }

      protected boolean parseUnknownField(final CodedInputStream input, final UnknownFieldSet.Builder unknownFields, final ExtensionRegistryLite extensionRegistry, final int tag) throws IOException {
         return MessageReflection.mergeFieldFrom(input, unknownFields, extensionRegistry, this.getDescriptorForType(), new MessageReflection.BuilderAdapter(this), tag);
      }

      public Map getAllFields() {
         Map<Descriptors.FieldDescriptor, Object> result = super.getAllFieldsMutable();
         result.putAll(this.extensions.getAllFields());
         return Collections.unmodifiableMap(result);
      }

      public Object getField(final Descriptors.FieldDescriptor field) {
         if (field.isExtension()) {
            this.verifyContainingType(field);
            Object value = this.extensions.getField(field);
            if (value == null) {
               return field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE ? DynamicMessage.getDefaultInstance(field.getMessageType()) : field.getDefaultValue();
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

      public boolean hasField(final Descriptors.FieldDescriptor field) {
         if (field.isExtension()) {
            this.verifyContainingType(field);
            return this.extensions.hasField(field);
         } else {
            return super.hasField(field);
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

      protected final void mergeExtensionFields(final ExtendableMessage other) {
         this.ensureExtensionsIsMutable();
         this.extensions.mergeFrom(other.extensions);
         this.onChanged();
      }

      private void verifyContainingType(final Descriptors.FieldDescriptor field) {
         if (field.getContainingType() != this.getDescriptorForType()) {
            throw new IllegalArgumentException("FieldDescriptor does not match message type.");
         }
      }
   }

   private abstract static class CachedDescriptorRetriever implements ExtensionDescriptorRetriever {
      private volatile Descriptors.FieldDescriptor descriptor;

      private CachedDescriptorRetriever() {
      }

      protected abstract Descriptors.FieldDescriptor loadDescriptor();

      public Descriptors.FieldDescriptor getDescriptor() {
         if (this.descriptor == null) {
            synchronized(this) {
               if (this.descriptor == null) {
                  this.descriptor = this.loadDescriptor();
               }
            }
         }

         return this.descriptor;
      }
   }

   public static class GeneratedExtension extends Extension {
      private ExtensionDescriptorRetriever descriptorRetriever;
      private final Class singularType;
      private final Message messageDefaultInstance;
      private final java.lang.reflect.Method enumValueOf;
      private final java.lang.reflect.Method enumGetValueDescriptor;
      private final Extension.ExtensionType extensionType;

      GeneratedExtension(ExtensionDescriptorRetriever descriptorRetriever, Class singularType, Message messageDefaultInstance, Extension.ExtensionType extensionType) {
         if (Message.class.isAssignableFrom(singularType) && !singularType.isInstance(messageDefaultInstance)) {
            throw new IllegalArgumentException("Bad messageDefaultInstance for " + singularType.getName());
         } else {
            this.descriptorRetriever = descriptorRetriever;
            this.singularType = singularType;
            this.messageDefaultInstance = messageDefaultInstance;
            if (ProtocolMessageEnum.class.isAssignableFrom(singularType)) {
               this.enumValueOf = GeneratedMessage.getMethodOrDie(singularType, "valueOf", Descriptors.EnumValueDescriptor.class);
               this.enumGetValueDescriptor = GeneratedMessage.getMethodOrDie(singularType, "getValueDescriptor");
            } else {
               this.enumValueOf = null;
               this.enumGetValueDescriptor = null;
            }

            this.extensionType = extensionType;
         }
      }

      public void internalInit(final Descriptors.FieldDescriptor descriptor) {
         if (this.descriptorRetriever != null) {
            throw new IllegalStateException("Already initialized.");
         } else {
            this.descriptorRetriever = new ExtensionDescriptorRetriever() {
               public Descriptors.FieldDescriptor getDescriptor() {
                  return descriptor;
               }
            };
         }
      }

      public Descriptors.FieldDescriptor getDescriptor() {
         if (this.descriptorRetriever == null) {
            throw new IllegalStateException("getDescriptor() called before internalInit()");
         } else {
            return this.descriptorRetriever.getDescriptor();
         }
      }

      public Message getMessageDefaultInstance() {
         return this.messageDefaultInstance;
      }

      protected Extension.ExtensionType getExtensionType() {
         return this.extensionType;
      }

      protected Object fromReflectionType(final Object value) {
         Descriptors.FieldDescriptor descriptor = this.getDescriptor();
         if (!descriptor.isRepeated()) {
            return this.singularFromReflectionType(value);
         } else if (descriptor.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE && descriptor.getJavaType() != Descriptors.FieldDescriptor.JavaType.ENUM) {
            return value;
         } else {
            List result = new ArrayList();

            for(Object element : (List)value) {
               result.add(this.singularFromReflectionType(element));
            }

            return result;
         }
      }

      protected Object singularFromReflectionType(final Object value) {
         Descriptors.FieldDescriptor descriptor = this.getDescriptor();
         switch (descriptor.getJavaType()) {
            case MESSAGE:
               if (this.singularType.isInstance(value)) {
                  return value;
               }

               return this.messageDefaultInstance.newBuilderForType().mergeFrom((Message)value).build();
            case ENUM:
               return GeneratedMessage.invokeOrDie(this.enumValueOf, (Object)null, (Descriptors.EnumValueDescriptor)value);
            default:
               return value;
         }
      }

      protected Object toReflectionType(final Object value) {
         Descriptors.FieldDescriptor descriptor = this.getDescriptor();
         if (!descriptor.isRepeated()) {
            return this.singularToReflectionType(value);
         } else if (descriptor.getJavaType() != Descriptors.FieldDescriptor.JavaType.ENUM) {
            return value;
         } else {
            List result = new ArrayList();

            for(Object element : (List)value) {
               result.add(this.singularToReflectionType(element));
            }

            return result;
         }
      }

      protected Object singularToReflectionType(final Object value) {
         Descriptors.FieldDescriptor descriptor = this.getDescriptor();
         switch (descriptor.getJavaType()) {
            case ENUM:
               return GeneratedMessage.invokeOrDie(this.enumGetValueDescriptor, value);
            default:
               return value;
         }
      }

      public int getNumber() {
         return this.getDescriptor().getNumber();
      }

      public WireFormat.FieldType getLiteType() {
         return this.getDescriptor().getLiteType();
      }

      public boolean isRepeated() {
         return this.getDescriptor().isRepeated();
      }

      public Object getDefaultValue() {
         if (this.isRepeated()) {
            return Collections.emptyList();
         } else {
            return this.getDescriptor().getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE ? this.messageDefaultInstance : this.singularFromReflectionType(this.getDescriptor().getDefaultValue());
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

      private boolean isMapFieldEnabled(Descriptors.FieldDescriptor field) {
         boolean result = true;
         return result;
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
                        containingOneofCamelCaseName = this.camelCaseNames[fieldsSize + field.getContainingOneof().getIndex()];
                     }

                     if (field.isRepeated()) {
                        if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                           if (field.isMapField() && this.isMapFieldEnabled(field)) {
                              this.fields[i] = new MapFieldAccessor(field, this.camelCaseNames[i], messageClass, builderClass);
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

                  int oneofsSize = this.oneofs.length;

                  for(int i = 0; i < oneofsSize; ++i) {
                     this.oneofs[i] = new OneofAccessor(this.descriptor, this.camelCaseNames[i + fieldsSize], messageClass, builderClass);
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

      private static boolean supportFieldPresence(Descriptors.FileDescriptor file) {
         return file.getSyntax() == Descriptors.FileDescriptor.Syntax.PROTO2;
      }

      private static class OneofAccessor {
         private final Descriptors.Descriptor descriptor;
         private final java.lang.reflect.Method caseMethod;
         private final java.lang.reflect.Method caseMethodBuilder;
         private final java.lang.reflect.Method clearMethod;

         OneofAccessor(final Descriptors.Descriptor descriptor, final String camelCaseName, final Class messageClass, final Class builderClass) {
            this.descriptor = descriptor;
            this.caseMethod = GeneratedMessage.getMethodOrDie(messageClass, "get" + camelCaseName + "Case");
            this.caseMethodBuilder = GeneratedMessage.getMethodOrDie(builderClass, "get" + camelCaseName + "Case");
            this.clearMethod = GeneratedMessage.getMethodOrDie(builderClass, "clear" + camelCaseName);
         }

         public boolean has(final GeneratedMessage message) {
            return ((Internal.EnumLite)GeneratedMessage.invokeOrDie(this.caseMethod, message)).getNumber() != 0;
         }

         public boolean has(Builder builder) {
            return ((Internal.EnumLite)GeneratedMessage.invokeOrDie(this.caseMethodBuilder, builder)).getNumber() != 0;
         }

         public Descriptors.FieldDescriptor get(final GeneratedMessage message) {
            int fieldNumber = ((Internal.EnumLite)GeneratedMessage.invokeOrDie(this.caseMethod, message)).getNumber();
            return fieldNumber > 0 ? this.descriptor.findFieldByNumber(fieldNumber) : null;
         }

         public Descriptors.FieldDescriptor get(Builder builder) {
            int fieldNumber = ((Internal.EnumLite)GeneratedMessage.invokeOrDie(this.caseMethodBuilder, builder)).getNumber();
            return fieldNumber > 0 ? this.descriptor.findFieldByNumber(fieldNumber) : null;
         }

         public void clear(final Builder builder) {
            GeneratedMessage.invokeOrDie(this.clearMethod, builder);
         }
      }

      private static class SingularFieldAccessor implements FieldAccessor {
         protected final Class type;
         protected final java.lang.reflect.Method getMethod;
         protected final java.lang.reflect.Method getMethodBuilder;
         protected final java.lang.reflect.Method setMethod;
         protected final java.lang.reflect.Method hasMethod;
         protected final java.lang.reflect.Method hasMethodBuilder;
         protected final java.lang.reflect.Method clearMethod;
         protected final java.lang.reflect.Method caseMethod;
         protected final java.lang.reflect.Method caseMethodBuilder;
         protected final Descriptors.FieldDescriptor field;
         protected final boolean isOneofField;
         protected final boolean hasHasMethod;

         SingularFieldAccessor(final Descriptors.FieldDescriptor descriptor, final String camelCaseName, final Class messageClass, final Class builderClass, final String containingOneofCamelCaseName) {
            this.field = descriptor;
            this.isOneofField = descriptor.getContainingOneof() != null;
            this.hasHasMethod = GeneratedMessage.FieldAccessorTable.supportFieldPresence(descriptor.getFile()) || !this.isOneofField && descriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE;
            this.getMethod = GeneratedMessage.getMethodOrDie(messageClass, "get" + camelCaseName);
            this.getMethodBuilder = GeneratedMessage.getMethodOrDie(builderClass, "get" + camelCaseName);
            this.type = this.getMethod.getReturnType();
            this.setMethod = GeneratedMessage.getMethodOrDie(builderClass, "set" + camelCaseName, this.type);
            this.hasMethod = this.hasHasMethod ? GeneratedMessage.getMethodOrDie(messageClass, "has" + camelCaseName) : null;
            this.hasMethodBuilder = this.hasHasMethod ? GeneratedMessage.getMethodOrDie(builderClass, "has" + camelCaseName) : null;
            this.clearMethod = GeneratedMessage.getMethodOrDie(builderClass, "clear" + camelCaseName);
            this.caseMethod = this.isOneofField ? GeneratedMessage.getMethodOrDie(messageClass, "get" + containingOneofCamelCaseName + "Case") : null;
            this.caseMethodBuilder = this.isOneofField ? GeneratedMessage.getMethodOrDie(builderClass, "get" + containingOneofCamelCaseName + "Case") : null;
         }

         private int getOneofFieldNumber(final GeneratedMessage message) {
            return ((Internal.EnumLite)GeneratedMessage.invokeOrDie(this.caseMethod, message)).getNumber();
         }

         private int getOneofFieldNumber(final Builder builder) {
            return ((Internal.EnumLite)GeneratedMessage.invokeOrDie(this.caseMethodBuilder, builder)).getNumber();
         }

         public Object get(final GeneratedMessage message) {
            return GeneratedMessage.invokeOrDie(this.getMethod, message);
         }

         public Object get(Builder builder) {
            return GeneratedMessage.invokeOrDie(this.getMethodBuilder, builder);
         }

         public Object getRaw(final GeneratedMessage message) {
            return this.get(message);
         }

         public Object getRaw(Builder builder) {
            return this.get(builder);
         }

         public void set(final Builder builder, final Object value) {
            GeneratedMessage.invokeOrDie(this.setMethod, builder, value);
         }

         public Object getRepeated(final GeneratedMessage message, final int index) {
            throw new UnsupportedOperationException("getRepeatedField() called on a singular field.");
         }

         public Object getRepeatedRaw(final GeneratedMessage message, final int index) {
            throw new UnsupportedOperationException("getRepeatedFieldRaw() called on a singular field.");
         }

         public Object getRepeated(Builder builder, int index) {
            throw new UnsupportedOperationException("getRepeatedField() called on a singular field.");
         }

         public Object getRepeatedRaw(Builder builder, int index) {
            throw new UnsupportedOperationException("getRepeatedFieldRaw() called on a singular field.");
         }

         public void setRepeated(final Builder builder, final int index, final Object value) {
            throw new UnsupportedOperationException("setRepeatedField() called on a singular field.");
         }

         public void addRepeated(final Builder builder, final Object value) {
            throw new UnsupportedOperationException("addRepeatedField() called on a singular field.");
         }

         public boolean has(final GeneratedMessage message) {
            if (!this.hasHasMethod) {
               if (this.isOneofField) {
                  return this.getOneofFieldNumber(message) == this.field.getNumber();
               } else {
                  return !this.get(message).equals(this.field.getDefaultValue());
               }
            } else {
               return (Boolean)GeneratedMessage.invokeOrDie(this.hasMethod, message);
            }
         }

         public boolean has(Builder builder) {
            if (!this.hasHasMethod) {
               if (this.isOneofField) {
                  return this.getOneofFieldNumber(builder) == this.field.getNumber();
               } else {
                  return !this.get(builder).equals(this.field.getDefaultValue());
               }
            } else {
               return (Boolean)GeneratedMessage.invokeOrDie(this.hasMethodBuilder, builder);
            }
         }

         public int getRepeatedCount(final GeneratedMessage message) {
            throw new UnsupportedOperationException("getRepeatedFieldSize() called on a singular field.");
         }

         public int getRepeatedCount(Builder builder) {
            throw new UnsupportedOperationException("getRepeatedFieldSize() called on a singular field.");
         }

         public void clear(final Builder builder) {
            GeneratedMessage.invokeOrDie(this.clearMethod, builder);
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
      }

      private static class RepeatedFieldAccessor implements FieldAccessor {
         protected final Class type;
         protected final java.lang.reflect.Method getMethod;
         protected final java.lang.reflect.Method getMethodBuilder;
         protected final java.lang.reflect.Method getRepeatedMethod;
         protected final java.lang.reflect.Method getRepeatedMethodBuilder;
         protected final java.lang.reflect.Method setRepeatedMethod;
         protected final java.lang.reflect.Method addRepeatedMethod;
         protected final java.lang.reflect.Method getCountMethod;
         protected final java.lang.reflect.Method getCountMethodBuilder;
         protected final java.lang.reflect.Method clearMethod;

         RepeatedFieldAccessor(final Descriptors.FieldDescriptor descriptor, final String camelCaseName, final Class messageClass, final Class builderClass) {
            this.getMethod = GeneratedMessage.getMethodOrDie(messageClass, "get" + camelCaseName + "List");
            this.getMethodBuilder = GeneratedMessage.getMethodOrDie(builderClass, "get" + camelCaseName + "List");
            this.getRepeatedMethod = GeneratedMessage.getMethodOrDie(messageClass, "get" + camelCaseName, Integer.TYPE);
            this.getRepeatedMethodBuilder = GeneratedMessage.getMethodOrDie(builderClass, "get" + camelCaseName, Integer.TYPE);
            this.type = this.getRepeatedMethod.getReturnType();
            this.setRepeatedMethod = GeneratedMessage.getMethodOrDie(builderClass, "set" + camelCaseName, Integer.TYPE, this.type);
            this.addRepeatedMethod = GeneratedMessage.getMethodOrDie(builderClass, "add" + camelCaseName, this.type);
            this.getCountMethod = GeneratedMessage.getMethodOrDie(messageClass, "get" + camelCaseName + "Count");
            this.getCountMethodBuilder = GeneratedMessage.getMethodOrDie(builderClass, "get" + camelCaseName + "Count");
            this.clearMethod = GeneratedMessage.getMethodOrDie(builderClass, "clear" + camelCaseName);
         }

         public Object get(final GeneratedMessage message) {
            return GeneratedMessage.invokeOrDie(this.getMethod, message);
         }

         public Object get(Builder builder) {
            return GeneratedMessage.invokeOrDie(this.getMethodBuilder, builder);
         }

         public Object getRaw(final GeneratedMessage message) {
            return this.get(message);
         }

         public Object getRaw(Builder builder) {
            return this.get(builder);
         }

         public void set(final Builder builder, final Object value) {
            this.clear(builder);

            for(Object element : (List)value) {
               this.addRepeated(builder, element);
            }

         }

         public Object getRepeated(final GeneratedMessage message, final int index) {
            return GeneratedMessage.invokeOrDie(this.getRepeatedMethod, message, index);
         }

         public Object getRepeated(Builder builder, int index) {
            return GeneratedMessage.invokeOrDie(this.getRepeatedMethodBuilder, builder, index);
         }

         public Object getRepeatedRaw(GeneratedMessage message, int index) {
            return this.getRepeated(message, index);
         }

         public Object getRepeatedRaw(Builder builder, int index) {
            return this.getRepeated(builder, index);
         }

         public void setRepeated(final Builder builder, final int index, final Object value) {
            GeneratedMessage.invokeOrDie(this.setRepeatedMethod, builder, index, value);
         }

         public void addRepeated(final Builder builder, final Object value) {
            GeneratedMessage.invokeOrDie(this.addRepeatedMethod, builder, value);
         }

         public boolean has(final GeneratedMessage message) {
            throw new UnsupportedOperationException("hasField() called on a repeated field.");
         }

         public boolean has(Builder builder) {
            throw new UnsupportedOperationException("hasField() called on a repeated field.");
         }

         public int getRepeatedCount(final GeneratedMessage message) {
            return (Integer)GeneratedMessage.invokeOrDie(this.getCountMethod, message);
         }

         public int getRepeatedCount(Builder builder) {
            return (Integer)GeneratedMessage.invokeOrDie(this.getCountMethodBuilder, builder);
         }

         public void clear(final Builder builder) {
            GeneratedMessage.invokeOrDie(this.clearMethod, builder);
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
      }

      private static class MapFieldAccessor implements FieldAccessor {
         private final Descriptors.FieldDescriptor field;
         private final Message mapEntryMessageDefaultInstance;

         MapFieldAccessor(final Descriptors.FieldDescriptor descriptor, final String camelCaseName, final Class messageClass, final Class builderClass) {
            this.field = descriptor;
            java.lang.reflect.Method getDefaultInstanceMethod = GeneratedMessage.getMethodOrDie(messageClass, "getDefaultInstance");
            MapField defaultMapField = this.getMapField((GeneratedMessage)GeneratedMessage.invokeOrDie(getDefaultInstanceMethod, (Object)null));
            this.mapEntryMessageDefaultInstance = defaultMapField.getMapEntryMessageDefaultInstance();
         }

         private MapField getMapField(GeneratedMessage message) {
            return message.internalGetMapField(this.field.getNumber());
         }

         private MapField getMapField(Builder builder) {
            return builder.internalGetMapField(this.field.getNumber());
         }

         private MapField getMutableMapField(Builder builder) {
            return builder.internalGetMutableMapField(this.field.getNumber());
         }

         public Object get(GeneratedMessage message) {
            List result = new ArrayList();

            for(int i = 0; i < this.getRepeatedCount(message); ++i) {
               result.add(this.getRepeated(message, i));
            }

            return Collections.unmodifiableList(result);
         }

         public Object get(Builder builder) {
            List result = new ArrayList();

            for(int i = 0; i < this.getRepeatedCount(builder); ++i) {
               result.add(this.getRepeated(builder, i));
            }

            return Collections.unmodifiableList(result);
         }

         public Object getRaw(GeneratedMessage message) {
            return this.get(message);
         }

         public Object getRaw(Builder builder) {
            return this.get(builder);
         }

         public void set(Builder builder, Object value) {
            this.clear(builder);

            for(Object entry : (List)value) {
               this.addRepeated(builder, entry);
            }

         }

         public Object getRepeated(GeneratedMessage message, int index) {
            return this.getMapField(message).getList().get(index);
         }

         public Object getRepeated(Builder builder, int index) {
            return this.getMapField(builder).getList().get(index);
         }

         public Object getRepeatedRaw(GeneratedMessage message, int index) {
            return this.getRepeated(message, index);
         }

         public Object getRepeatedRaw(Builder builder, int index) {
            return this.getRepeated(builder, index);
         }

         public void setRepeated(Builder builder, int index, Object value) {
            this.getMutableMapField(builder).getMutableList().set(index, (Message)value);
         }

         public void addRepeated(Builder builder, Object value) {
            this.getMutableMapField(builder).getMutableList().add((Message)value);
         }

         public boolean has(GeneratedMessage message) {
            throw new UnsupportedOperationException("hasField() is not supported for repeated fields.");
         }

         public boolean has(Builder builder) {
            throw new UnsupportedOperationException("hasField() is not supported for repeated fields.");
         }

         public int getRepeatedCount(GeneratedMessage message) {
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
            throw new UnsupportedOperationException("Nested builder not supported for map fields.");
         }
      }

      private static final class SingularEnumFieldAccessor extends SingularFieldAccessor {
         private Descriptors.EnumDescriptor enumDescriptor;
         private java.lang.reflect.Method valueOfMethod;
         private java.lang.reflect.Method getValueDescriptorMethod;
         private boolean supportUnknownEnumValue;
         private java.lang.reflect.Method getValueMethod;
         private java.lang.reflect.Method getValueMethodBuilder;
         private java.lang.reflect.Method setValueMethod;

         SingularEnumFieldAccessor(final Descriptors.FieldDescriptor descriptor, final String camelCaseName, final Class messageClass, final Class builderClass, final String containingOneofCamelCaseName) {
            super(descriptor, camelCaseName, messageClass, builderClass, containingOneofCamelCaseName);
            this.enumDescriptor = descriptor.getEnumType();
            this.valueOfMethod = GeneratedMessage.getMethodOrDie(this.type, "valueOf", Descriptors.EnumValueDescriptor.class);
            this.getValueDescriptorMethod = GeneratedMessage.getMethodOrDie(this.type, "getValueDescriptor");
            this.supportUnknownEnumValue = !descriptor.legacyEnumFieldTreatedAsClosed();
            if (this.supportUnknownEnumValue) {
               this.getValueMethod = GeneratedMessage.getMethodOrDie(messageClass, "get" + camelCaseName + "Value");
               this.getValueMethodBuilder = GeneratedMessage.getMethodOrDie(builderClass, "get" + camelCaseName + "Value");
               this.setValueMethod = GeneratedMessage.getMethodOrDie(builderClass, "set" + camelCaseName + "Value", Integer.TYPE);
            }

         }

         public Object get(final GeneratedMessage message) {
            if (this.supportUnknownEnumValue) {
               int value = (Integer)GeneratedMessage.invokeOrDie(this.getValueMethod, message);
               return this.enumDescriptor.findValueByNumberCreatingIfUnknown(value);
            } else {
               return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.get(message));
            }
         }

         public Object get(final Builder builder) {
            if (this.supportUnknownEnumValue) {
               int value = (Integer)GeneratedMessage.invokeOrDie(this.getValueMethodBuilder, builder);
               return this.enumDescriptor.findValueByNumberCreatingIfUnknown(value);
            } else {
               return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.get(builder));
            }
         }

         public void set(final Builder builder, final Object value) {
            if (this.supportUnknownEnumValue) {
               GeneratedMessage.invokeOrDie(this.setValueMethod, builder, ((Descriptors.EnumValueDescriptor)value).getNumber());
            } else {
               super.set(builder, GeneratedMessage.invokeOrDie(this.valueOfMethod, (Object)null, value));
            }
         }
      }

      private static final class RepeatedEnumFieldAccessor extends RepeatedFieldAccessor {
         private Descriptors.EnumDescriptor enumDescriptor;
         private final java.lang.reflect.Method valueOfMethod;
         private final java.lang.reflect.Method getValueDescriptorMethod;
         private boolean supportUnknownEnumValue;
         private java.lang.reflect.Method getRepeatedValueMethod;
         private java.lang.reflect.Method getRepeatedValueMethodBuilder;
         private java.lang.reflect.Method setRepeatedValueMethod;
         private java.lang.reflect.Method addRepeatedValueMethod;

         RepeatedEnumFieldAccessor(final Descriptors.FieldDescriptor descriptor, final String camelCaseName, final Class messageClass, final Class builderClass) {
            super(descriptor, camelCaseName, messageClass, builderClass);
            this.enumDescriptor = descriptor.getEnumType();
            this.valueOfMethod = GeneratedMessage.getMethodOrDie(this.type, "valueOf", Descriptors.EnumValueDescriptor.class);
            this.getValueDescriptorMethod = GeneratedMessage.getMethodOrDie(this.type, "getValueDescriptor");
            this.supportUnknownEnumValue = !descriptor.legacyEnumFieldTreatedAsClosed();
            if (this.supportUnknownEnumValue) {
               this.getRepeatedValueMethod = GeneratedMessage.getMethodOrDie(messageClass, "get" + camelCaseName + "Value", Integer.TYPE);
               this.getRepeatedValueMethodBuilder = GeneratedMessage.getMethodOrDie(builderClass, "get" + camelCaseName + "Value", Integer.TYPE);
               this.setRepeatedValueMethod = GeneratedMessage.getMethodOrDie(builderClass, "set" + camelCaseName + "Value", Integer.TYPE, Integer.TYPE);
               this.addRepeatedValueMethod = GeneratedMessage.getMethodOrDie(builderClass, "add" + camelCaseName + "Value", Integer.TYPE);
            }

         }

         public Object get(final GeneratedMessage message) {
            List newList = new ArrayList();
            int size = this.getRepeatedCount(message);

            for(int i = 0; i < size; ++i) {
               newList.add(this.getRepeated(message, i));
            }

            return Collections.unmodifiableList(newList);
         }

         public Object get(final Builder builder) {
            List newList = new ArrayList();
            int size = this.getRepeatedCount(builder);

            for(int i = 0; i < size; ++i) {
               newList.add(this.getRepeated(builder, i));
            }

            return Collections.unmodifiableList(newList);
         }

         public Object getRepeated(final GeneratedMessage message, final int index) {
            if (this.supportUnknownEnumValue) {
               int value = (Integer)GeneratedMessage.invokeOrDie(this.getRepeatedValueMethod, message, index);
               return this.enumDescriptor.findValueByNumberCreatingIfUnknown(value);
            } else {
               return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.getRepeated(message, index));
            }
         }

         public Object getRepeated(final Builder builder, final int index) {
            if (this.supportUnknownEnumValue) {
               int value = (Integer)GeneratedMessage.invokeOrDie(this.getRepeatedValueMethodBuilder, builder, index);
               return this.enumDescriptor.findValueByNumberCreatingIfUnknown(value);
            } else {
               return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.getRepeated(builder, index));
            }
         }

         public void setRepeated(final Builder builder, final int index, final Object value) {
            if (this.supportUnknownEnumValue) {
               GeneratedMessage.invokeOrDie(this.setRepeatedValueMethod, builder, index, ((Descriptors.EnumValueDescriptor)value).getNumber());
            } else {
               super.setRepeated(builder, index, GeneratedMessage.invokeOrDie(this.valueOfMethod, (Object)null, value));
            }
         }

         public void addRepeated(final Builder builder, final Object value) {
            if (this.supportUnknownEnumValue) {
               GeneratedMessage.invokeOrDie(this.addRepeatedValueMethod, builder, ((Descriptors.EnumValueDescriptor)value).getNumber());
            } else {
               super.addRepeated(builder, GeneratedMessage.invokeOrDie(this.valueOfMethod, (Object)null, value));
            }
         }
      }

      private static final class SingularStringFieldAccessor extends SingularFieldAccessor {
         private final java.lang.reflect.Method getBytesMethod;
         private final java.lang.reflect.Method getBytesMethodBuilder;
         private final java.lang.reflect.Method setBytesMethodBuilder;

         SingularStringFieldAccessor(final Descriptors.FieldDescriptor descriptor, final String camelCaseName, final Class messageClass, final Class builderClass, final String containingOneofCamelCaseName) {
            super(descriptor, camelCaseName, messageClass, builderClass, containingOneofCamelCaseName);
            this.getBytesMethod = GeneratedMessage.getMethodOrDie(messageClass, "get" + camelCaseName + "Bytes");
            this.getBytesMethodBuilder = GeneratedMessage.getMethodOrDie(builderClass, "get" + camelCaseName + "Bytes");
            this.setBytesMethodBuilder = GeneratedMessage.getMethodOrDie(builderClass, "set" + camelCaseName + "Bytes", ByteString.class);
         }

         public Object getRaw(final GeneratedMessage message) {
            return GeneratedMessage.invokeOrDie(this.getBytesMethod, message);
         }

         public Object getRaw(Builder builder) {
            return GeneratedMessage.invokeOrDie(this.getBytesMethodBuilder, builder);
         }

         public void set(Builder builder, Object value) {
            if (value instanceof ByteString) {
               GeneratedMessage.invokeOrDie(this.setBytesMethodBuilder, builder, value);
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
            this.newBuilderMethod = GeneratedMessage.getMethodOrDie(this.type, "newBuilder");
            this.getBuilderMethodBuilder = GeneratedMessage.getMethodOrDie(builderClass, "get" + camelCaseName + "Builder");
         }

         private Object coerceType(final Object value) {
            return this.type.isInstance(value) ? value : ((Message.Builder)GeneratedMessage.invokeOrDie(this.newBuilderMethod, (Object)null)).mergeFrom((Message)value).buildPartial();
         }

         public void set(final Builder builder, final Object value) {
            super.set(builder, this.coerceType(value));
         }

         public Message.Builder newBuilder() {
            return (Message.Builder)GeneratedMessage.invokeOrDie(this.newBuilderMethod, (Object)null);
         }

         public Message.Builder getBuilder(Builder builder) {
            return (Message.Builder)GeneratedMessage.invokeOrDie(this.getBuilderMethodBuilder, builder);
         }
      }

      private static final class RepeatedMessageFieldAccessor extends RepeatedFieldAccessor {
         private final java.lang.reflect.Method newBuilderMethod;
         private final java.lang.reflect.Method getBuilderMethodBuilder;

         RepeatedMessageFieldAccessor(final Descriptors.FieldDescriptor descriptor, final String camelCaseName, final Class messageClass, final Class builderClass) {
            super(descriptor, camelCaseName, messageClass, builderClass);
            this.newBuilderMethod = GeneratedMessage.getMethodOrDie(this.type, "newBuilder");
            this.getBuilderMethodBuilder = GeneratedMessage.getMethodOrDie(builderClass, "get" + camelCaseName + "Builder", Integer.TYPE);
         }

         private Object coerceType(final Object value) {
            return this.type.isInstance(value) ? value : ((Message.Builder)GeneratedMessage.invokeOrDie(this.newBuilderMethod, (Object)null)).mergeFrom((Message)value).build();
         }

         public void setRepeated(final Builder builder, final int index, final Object value) {
            super.setRepeated(builder, index, this.coerceType(value));
         }

         public void addRepeated(final Builder builder, final Object value) {
            super.addRepeated(builder, this.coerceType(value));
         }

         public Message.Builder newBuilder() {
            return (Message.Builder)GeneratedMessage.invokeOrDie(this.newBuilderMethod, (Object)null);
         }

         public Message.Builder getRepeatedBuilder(final Builder builder, final int index) {
            return (Message.Builder)GeneratedMessage.invokeOrDie(this.getBuilderMethodBuilder, builder, index);
         }
      }

      private interface FieldAccessor {
         Object get(GeneratedMessage message);

         Object get(Builder builder);

         Object getRaw(GeneratedMessage message);

         Object getRaw(Builder builder);

         void set(Builder builder, Object value);

         Object getRepeated(GeneratedMessage message, int index);

         Object getRepeated(Builder builder, int index);

         Object getRepeatedRaw(GeneratedMessage message, int index);

         Object getRepeatedRaw(Builder builder, int index);

         void setRepeated(Builder builder, int index, Object value);

         void addRepeated(Builder builder, Object value);

         boolean has(GeneratedMessage message);

         boolean has(Builder builder);

         int getRepeatedCount(GeneratedMessage message);

         int getRepeatedCount(Builder builder);

         void clear(Builder builder);

         Message.Builder newBuilder();

         Message.Builder getBuilder(Builder builder);

         Message.Builder getRepeatedBuilder(Builder builder, int index);
      }
   }

   protected interface BuilderParent extends AbstractMessage.BuilderParent {
   }

   interface ExtensionDescriptorRetriever {
      Descriptors.FieldDescriptor getDescriptor();
   }
}
