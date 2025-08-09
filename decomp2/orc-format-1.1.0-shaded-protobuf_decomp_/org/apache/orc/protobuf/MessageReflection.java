package org.apache.orc.protobuf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class MessageReflection {
   static void writeMessageTo(Message message, Map fields, CodedOutputStream output, boolean alwaysWriteRequiredFields) throws IOException {
      boolean isMessageSet = message.getDescriptorForType().getOptions().getMessageSetWireFormat();
      if (alwaysWriteRequiredFields) {
         fields = new TreeMap(fields);

         for(Descriptors.FieldDescriptor field : message.getDescriptorForType().getFields()) {
            if (field.isRequired() && !fields.containsKey(field)) {
               fields.put(field, message.getField(field));
            }
         }
      }

      for(Map.Entry entry : fields.entrySet()) {
         Descriptors.FieldDescriptor field = (Descriptors.FieldDescriptor)entry.getKey();
         Object value = entry.getValue();
         if (isMessageSet && field.isExtension() && field.getType() == Descriptors.FieldDescriptor.Type.MESSAGE && !field.isRepeated()) {
            output.writeMessageSetExtension(field.getNumber(), (Message)value);
         } else {
            FieldSet.writeField(field, value, output);
         }
      }

      UnknownFieldSet unknownFields = message.getUnknownFields();
      if (isMessageSet) {
         unknownFields.writeAsMessageSetTo(output);
      } else {
         unknownFields.writeTo(output);
      }

   }

   static int getSerializedSize(Message message, Map fields) {
      int size = 0;
      boolean isMessageSet = message.getDescriptorForType().getOptions().getMessageSetWireFormat();

      for(Map.Entry entry : fields.entrySet()) {
         Descriptors.FieldDescriptor field = (Descriptors.FieldDescriptor)entry.getKey();
         Object value = entry.getValue();
         if (isMessageSet && field.isExtension() && field.getType() == Descriptors.FieldDescriptor.Type.MESSAGE && !field.isRepeated()) {
            size += CodedOutputStream.computeMessageSetExtensionSize(field.getNumber(), (Message)value);
         } else {
            size += FieldSet.computeFieldSize(field, value);
         }
      }

      UnknownFieldSet unknownFields = message.getUnknownFields();
      if (isMessageSet) {
         size += unknownFields.getSerializedSizeAsMessageSet();
      } else {
         size += unknownFields.getSerializedSize();
      }

      return size;
   }

   static String delimitWithCommas(List parts) {
      StringBuilder result = new StringBuilder();

      for(String part : parts) {
         if (result.length() > 0) {
            result.append(", ");
         }

         result.append(part);
      }

      return result.toString();
   }

   static boolean isInitialized(MessageOrBuilder message) {
      for(Descriptors.FieldDescriptor field : message.getDescriptorForType().getFields()) {
         if (field.isRequired() && !message.hasField(field)) {
            return false;
         }
      }

      for(Map.Entry entry : message.getAllFields().entrySet()) {
         Descriptors.FieldDescriptor field = (Descriptors.FieldDescriptor)entry.getKey();
         if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
            if (field.isRepeated()) {
               for(Message element : (List)entry.getValue()) {
                  if (!element.isInitialized()) {
                     return false;
                  }
               }
            } else if (!((Message)entry.getValue()).isInitialized()) {
               return false;
            }
         }
      }

      return true;
   }

   private static String subMessagePrefix(final String prefix, final Descriptors.FieldDescriptor field, final int index) {
      StringBuilder result = new StringBuilder(prefix);
      if (field.isExtension()) {
         result.append('(').append(field.getFullName()).append(')');
      } else {
         result.append(field.getName());
      }

      if (index != -1) {
         result.append('[').append(index).append(']');
      }

      result.append('.');
      return result.toString();
   }

   private static void findMissingFields(final MessageOrBuilder message, final String prefix, final List results) {
      for(Descriptors.FieldDescriptor field : message.getDescriptorForType().getFields()) {
         if (field.isRequired() && !message.hasField(field)) {
            results.add(prefix + field.getName());
         }
      }

      for(Map.Entry entry : message.getAllFields().entrySet()) {
         Descriptors.FieldDescriptor field = (Descriptors.FieldDescriptor)entry.getKey();
         Object value = entry.getValue();
         if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
            if (field.isRepeated()) {
               int i = 0;

               for(Object element : (List)value) {
                  findMissingFields((MessageOrBuilder)element, subMessagePrefix(prefix, field, i++), results);
               }
            } else if (message.hasField(field)) {
               findMissingFields((MessageOrBuilder)value, subMessagePrefix(prefix, field, -1), results);
            }
         }
      }

   }

   static List findMissingFields(final MessageOrBuilder message) {
      List<String> results = new ArrayList();
      findMissingFields(message, "", results);
      return results;
   }

   static boolean mergeFieldFrom(CodedInputStream input, UnknownFieldSet.Builder unknownFields, ExtensionRegistryLite extensionRegistry, Descriptors.Descriptor type, MergeTarget target, int tag) throws IOException {
      if (type.getOptions().getMessageSetWireFormat() && tag == WireFormat.MESSAGE_SET_ITEM_TAG) {
         mergeMessageSetExtensionFromCodedStream(input, unknownFields, extensionRegistry, type, target);
         return true;
      } else {
         int wireType = WireFormat.getTagWireType(tag);
         int fieldNumber = WireFormat.getTagFieldNumber(tag);
         Message defaultInstance = null;
         Descriptors.FieldDescriptor field;
         if (type.isExtensionNumber(fieldNumber)) {
            if (extensionRegistry instanceof ExtensionRegistry) {
               ExtensionRegistry.ExtensionInfo extension = target.findExtensionByNumber((ExtensionRegistry)extensionRegistry, type, fieldNumber);
               if (extension == null) {
                  field = null;
               } else {
                  field = extension.descriptor;
                  defaultInstance = extension.defaultInstance;
                  if (defaultInstance == null && field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                     throw new IllegalStateException("Message-typed extension lacked default instance: " + field.getFullName());
                  }
               }
            } else {
               field = null;
            }
         } else if (target.getContainerType() == MessageReflection.MergeTarget.ContainerType.MESSAGE) {
            field = type.findFieldByNumber(fieldNumber);
         } else {
            field = null;
         }

         boolean unknown = false;
         boolean packed = false;
         if (field == null) {
            unknown = true;
         } else if (wireType == FieldSet.getWireFormatForFieldType(field.getLiteType(), false)) {
            packed = false;
         } else if (field.isPackable() && wireType == FieldSet.getWireFormatForFieldType(field.getLiteType(), true)) {
            packed = true;
         } else {
            unknown = true;
         }

         if (unknown) {
            return unknownFields != null ? unknownFields.mergeFieldFrom(tag, input) : input.skipField(tag);
         } else {
            if (packed) {
               int length = input.readRawVarint32();
               int limit = input.pushLimit(length);
               if (field.getLiteType() == WireFormat.FieldType.ENUM) {
                  while(input.getBytesUntilLimit() > 0) {
                     int rawValue = input.readEnum();
                     if (field.legacyEnumFieldTreatedAsClosed()) {
                        Object value = field.getEnumType().findValueByNumber(rawValue);
                        if (value == null) {
                           if (unknownFields != null) {
                              unknownFields.mergeVarintField(fieldNumber, rawValue);
                           }
                        } else {
                           target.addRepeatedField(field, value);
                        }
                     } else {
                        target.addRepeatedField(field, field.getEnumType().findValueByNumberCreatingIfUnknown(rawValue));
                     }
                  }
               } else {
                  while(input.getBytesUntilLimit() > 0) {
                     Object value = WireFormat.readPrimitiveField(input, field.getLiteType(), target.getUtf8Validation(field));
                     target.addRepeatedField(field, value);
                  }
               }

               input.popLimit(limit);
            } else {
               Object value;
               switch (field.getType()) {
                  case GROUP:
                     target.mergeGroup(input, extensionRegistry, field, defaultInstance);
                     return true;
                  case MESSAGE:
                     target.mergeMessage(input, extensionRegistry, field, defaultInstance);
                     return true;
                  case ENUM:
                     int rawValue = input.readEnum();
                     if (field.legacyEnumFieldTreatedAsClosed()) {
                        value = field.getEnumType().findValueByNumber(rawValue);
                        if (value == null) {
                           if (unknownFields != null) {
                              unknownFields.mergeVarintField(fieldNumber, rawValue);
                           }

                           return true;
                        }
                     } else {
                        value = field.getEnumType().findValueByNumberCreatingIfUnknown(rawValue);
                     }
                     break;
                  default:
                     value = WireFormat.readPrimitiveField(input, field.getLiteType(), target.getUtf8Validation(field));
               }

               if (field.isRepeated()) {
                  target.addRepeatedField(field, value);
               } else {
                  target.setField(field, value);
               }
            }

            return true;
         }
      }
   }

   static void mergeMessageFrom(Message.Builder target, UnknownFieldSet.Builder unknownFields, CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      BuilderAdapter builderAdapter = new BuilderAdapter(target);
      Descriptors.Descriptor descriptorForType = target.getDescriptorForType();

      int tag;
      do {
         tag = input.readTag();
      } while(tag != 0 && mergeFieldFrom(input, unknownFields, extensionRegistry, descriptorForType, builderAdapter, tag));

   }

   private static void mergeMessageSetExtensionFromCodedStream(CodedInputStream input, UnknownFieldSet.Builder unknownFields, ExtensionRegistryLite extensionRegistry, Descriptors.Descriptor type, MergeTarget target) throws IOException {
      int typeId = 0;
      ByteString rawBytes = null;
      ExtensionRegistry.ExtensionInfo extension = null;

      while(true) {
         int tag = input.readTag();
         if (tag == 0) {
            break;
         }

         if (tag == WireFormat.MESSAGE_SET_TYPE_ID_TAG) {
            typeId = input.readUInt32();
            if (typeId != 0 && extensionRegistry instanceof ExtensionRegistry) {
               extension = target.findExtensionByNumber((ExtensionRegistry)extensionRegistry, type, typeId);
            }
         } else if (tag == WireFormat.MESSAGE_SET_MESSAGE_TAG) {
            if (typeId != 0 && extension != null && ExtensionRegistryLite.isEagerlyParseMessageSets()) {
               eagerlyMergeMessageSetExtension(input, extension, extensionRegistry, target);
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
            mergeMessageSetExtensionFromBytes(rawBytes, extension, extensionRegistry, target);
         } else if (rawBytes != null && unknownFields != null) {
            unknownFields.mergeField(typeId, UnknownFieldSet.Field.newBuilder().addLengthDelimited(rawBytes).build());
         }
      }

   }

   private static void mergeMessageSetExtensionFromBytes(ByteString rawBytes, ExtensionRegistry.ExtensionInfo extension, ExtensionRegistryLite extensionRegistry, MergeTarget target) throws IOException {
      Descriptors.FieldDescriptor field = extension.descriptor;
      boolean hasOriginalValue = target.hasField(field);
      if (!hasOriginalValue && !ExtensionRegistryLite.isEagerlyParseMessageSets()) {
         LazyField lazyField = new LazyField(extension.defaultInstance, extensionRegistry, rawBytes);
         target.setField(field, lazyField);
      } else {
         Object value = target.parseMessageFromBytes(rawBytes, extensionRegistry, field, extension.defaultInstance);
         target.setField(field, value);
      }

   }

   private static void eagerlyMergeMessageSetExtension(CodedInputStream input, ExtensionRegistry.ExtensionInfo extension, ExtensionRegistryLite extensionRegistry, MergeTarget target) throws IOException {
      Descriptors.FieldDescriptor field = extension.descriptor;
      Object value = target.parseMessage(input, extensionRegistry, field, extension.defaultInstance);
      target.setField(field, value);
   }

   static class BuilderAdapter implements MergeTarget {
      private final Message.Builder builder;
      private boolean hasNestedBuilders = true;

      public Descriptors.Descriptor getDescriptorForType() {
         return this.builder.getDescriptorForType();
      }

      public BuilderAdapter(Message.Builder builder) {
         this.builder = builder;
      }

      public Object getField(Descriptors.FieldDescriptor field) {
         return this.builder.getField(field);
      }

      private Message.Builder getFieldBuilder(Descriptors.FieldDescriptor field) {
         if (this.hasNestedBuilders) {
            try {
               return this.builder.getFieldBuilder(field);
            } catch (UnsupportedOperationException var3) {
               this.hasNestedBuilders = false;
            }
         }

         return null;
      }

      public boolean hasField(Descriptors.FieldDescriptor field) {
         return this.builder.hasField(field);
      }

      public MergeTarget setField(Descriptors.FieldDescriptor field, Object value) {
         if (!field.isRepeated() && value instanceof MessageLite.Builder) {
            if (value != this.getFieldBuilder(field)) {
               this.builder.setField(field, ((MessageLite.Builder)value).buildPartial());
            }

            return this;
         } else {
            this.builder.setField(field, value);
            return this;
         }
      }

      public MergeTarget clearField(Descriptors.FieldDescriptor field) {
         this.builder.clearField(field);
         return this;
      }

      public MergeTarget setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
         if (value instanceof MessageLite.Builder) {
            value = ((MessageLite.Builder)value).buildPartial();
         }

         this.builder.setRepeatedField(field, index, value);
         return this;
      }

      public MergeTarget addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
         if (value instanceof MessageLite.Builder) {
            value = ((MessageLite.Builder)value).buildPartial();
         }

         this.builder.addRepeatedField(field, value);
         return this;
      }

      public boolean hasOneof(Descriptors.OneofDescriptor oneof) {
         return this.builder.hasOneof(oneof);
      }

      public MergeTarget clearOneof(Descriptors.OneofDescriptor oneof) {
         this.builder.clearOneof(oneof);
         return this;
      }

      public Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor oneof) {
         return this.builder.getOneofFieldDescriptor(oneof);
      }

      public MergeTarget.ContainerType getContainerType() {
         return MessageReflection.MergeTarget.ContainerType.MESSAGE;
      }

      public ExtensionRegistry.ExtensionInfo findExtensionByName(ExtensionRegistry registry, String name) {
         return registry.findImmutableExtensionByName(name);
      }

      public ExtensionRegistry.ExtensionInfo findExtensionByNumber(ExtensionRegistry registry, Descriptors.Descriptor containingType, int fieldNumber) {
         return registry.findImmutableExtensionByNumber(containingType, fieldNumber);
      }

      public Object parseGroup(CodedInputStream input, ExtensionRegistryLite extensionRegistry, Descriptors.FieldDescriptor field, Message defaultInstance) throws IOException {
         Message.Builder subBuilder;
         if (defaultInstance != null) {
            subBuilder = defaultInstance.newBuilderForType();
         } else {
            subBuilder = this.builder.newBuilderForField(field);
         }

         if (!field.isRepeated()) {
            Message originalMessage = (Message)this.getField(field);
            if (originalMessage != null) {
               subBuilder.mergeFrom(originalMessage);
            }
         }

         input.readGroup(field.getNumber(), (MessageLite.Builder)subBuilder, extensionRegistry);
         return subBuilder.buildPartial();
      }

      public Object parseMessage(CodedInputStream input, ExtensionRegistryLite extensionRegistry, Descriptors.FieldDescriptor field, Message defaultInstance) throws IOException {
         Message.Builder subBuilder;
         if (defaultInstance != null) {
            subBuilder = defaultInstance.newBuilderForType();
         } else {
            subBuilder = this.builder.newBuilderForField(field);
         }

         if (!field.isRepeated()) {
            Message originalMessage = (Message)this.getField(field);
            if (originalMessage != null) {
               subBuilder.mergeFrom(originalMessage);
            }
         }

         input.readMessage((MessageLite.Builder)subBuilder, extensionRegistry);
         return subBuilder.buildPartial();
      }

      public Object parseMessageFromBytes(ByteString bytes, ExtensionRegistryLite extensionRegistry, Descriptors.FieldDescriptor field, Message defaultInstance) throws IOException {
         Message.Builder subBuilder;
         if (defaultInstance != null) {
            subBuilder = defaultInstance.newBuilderForType();
         } else {
            subBuilder = this.builder.newBuilderForField(field);
         }

         if (!field.isRepeated()) {
            Message originalMessage = (Message)this.getField(field);
            if (originalMessage != null) {
               subBuilder.mergeFrom(originalMessage);
            }
         }

         subBuilder.mergeFrom(bytes, extensionRegistry);
         return subBuilder.buildPartial();
      }

      public void mergeGroup(CodedInputStream input, ExtensionRegistryLite extensionRegistry, Descriptors.FieldDescriptor field, Message defaultInstance) throws IOException {
         if (!field.isRepeated()) {
            Message.Builder subBuilder;
            if (this.hasField(field)) {
               subBuilder = this.getFieldBuilder(field);
               if (subBuilder != null) {
                  input.readGroup(field.getNumber(), (MessageLite.Builder)subBuilder, extensionRegistry);
                  return;
               }

               subBuilder = this.newMessageFieldInstance(field, defaultInstance);
               subBuilder.mergeFrom((Message)this.getField(field));
            } else {
               subBuilder = this.newMessageFieldInstance(field, defaultInstance);
            }

            input.readGroup(field.getNumber(), (MessageLite.Builder)subBuilder, extensionRegistry);
            this.setField(field, subBuilder.buildPartial());
         } else {
            Message.Builder subBuilder = this.newMessageFieldInstance(field, defaultInstance);
            input.readGroup(field.getNumber(), (MessageLite.Builder)subBuilder, extensionRegistry);
            this.addRepeatedField(field, subBuilder.buildPartial());
         }

      }

      public void mergeMessage(CodedInputStream input, ExtensionRegistryLite extensionRegistry, Descriptors.FieldDescriptor field, Message defaultInstance) throws IOException {
         if (!field.isRepeated()) {
            Message.Builder subBuilder;
            if (this.hasField(field)) {
               subBuilder = this.getFieldBuilder(field);
               if (subBuilder != null) {
                  input.readMessage((MessageLite.Builder)subBuilder, extensionRegistry);
                  return;
               }

               subBuilder = this.newMessageFieldInstance(field, defaultInstance);
               subBuilder.mergeFrom((Message)this.getField(field));
            } else {
               subBuilder = this.newMessageFieldInstance(field, defaultInstance);
            }

            input.readMessage((MessageLite.Builder)subBuilder, extensionRegistry);
            this.setField(field, subBuilder.buildPartial());
         } else {
            Message.Builder subBuilder = this.newMessageFieldInstance(field, defaultInstance);
            input.readMessage((MessageLite.Builder)subBuilder, extensionRegistry);
            this.addRepeatedField(field, subBuilder.buildPartial());
         }

      }

      private Message.Builder newMessageFieldInstance(Descriptors.FieldDescriptor field, Message defaultInstance) {
         return defaultInstance != null ? defaultInstance.newBuilderForType() : this.builder.newBuilderForField(field);
      }

      public MergeTarget newMergeTargetForField(Descriptors.FieldDescriptor field, Message defaultInstance) {
         if (!field.isRepeated() && this.hasField(field)) {
            Message.Builder subBuilder = this.getFieldBuilder(field);
            if (subBuilder != null) {
               return new BuilderAdapter(subBuilder);
            }
         }

         Message.Builder subBuilder = this.newMessageFieldInstance(field, defaultInstance);
         if (!field.isRepeated()) {
            Message originalMessage = (Message)this.getField(field);
            if (originalMessage != null) {
               subBuilder.mergeFrom(originalMessage);
            }
         }

         return new BuilderAdapter(subBuilder);
      }

      public MergeTarget newEmptyTargetForField(Descriptors.FieldDescriptor field, Message defaultInstance) {
         Message.Builder subBuilder;
         if (defaultInstance != null) {
            subBuilder = defaultInstance.newBuilderForType();
         } else {
            subBuilder = this.builder.newBuilderForField(field);
         }

         return new BuilderAdapter(subBuilder);
      }

      public WireFormat.Utf8Validation getUtf8Validation(Descriptors.FieldDescriptor descriptor) {
         if (descriptor.needsUtf8Check()) {
            return WireFormat.Utf8Validation.STRICT;
         } else {
            return !descriptor.isRepeated() && this.builder instanceof GeneratedMessage.Builder ? WireFormat.Utf8Validation.LAZY : WireFormat.Utf8Validation.LOOSE;
         }
      }

      public Object finish() {
         return this.builder;
      }
   }

   static class ExtensionAdapter implements MergeTarget {
      private final FieldSet extensions;

      ExtensionAdapter(FieldSet extensions) {
         this.extensions = extensions;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         throw new UnsupportedOperationException("getDescriptorForType() called on FieldSet object");
      }

      public Object getField(Descriptors.FieldDescriptor field) {
         return this.extensions.getField(field);
      }

      public boolean hasField(Descriptors.FieldDescriptor field) {
         return this.extensions.hasField(field);
      }

      public MergeTarget setField(Descriptors.FieldDescriptor field, Object value) {
         this.extensions.setField(field, value);
         return this;
      }

      public MergeTarget clearField(Descriptors.FieldDescriptor field) {
         this.extensions.clearField(field);
         return this;
      }

      public MergeTarget setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
         this.extensions.setRepeatedField(field, index, value);
         return this;
      }

      public MergeTarget addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
         this.extensions.addRepeatedField(field, value);
         return this;
      }

      public boolean hasOneof(Descriptors.OneofDescriptor oneof) {
         return false;
      }

      public MergeTarget clearOneof(Descriptors.OneofDescriptor oneof) {
         return this;
      }

      public Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor oneof) {
         return null;
      }

      public MergeTarget.ContainerType getContainerType() {
         return MessageReflection.MergeTarget.ContainerType.EXTENSION_SET;
      }

      public ExtensionRegistry.ExtensionInfo findExtensionByName(ExtensionRegistry registry, String name) {
         return registry.findImmutableExtensionByName(name);
      }

      public ExtensionRegistry.ExtensionInfo findExtensionByNumber(ExtensionRegistry registry, Descriptors.Descriptor containingType, int fieldNumber) {
         return registry.findImmutableExtensionByNumber(containingType, fieldNumber);
      }

      public Object parseGroup(CodedInputStream input, ExtensionRegistryLite registry, Descriptors.FieldDescriptor field, Message defaultInstance) throws IOException {
         Message.Builder subBuilder = defaultInstance.newBuilderForType();
         if (!field.isRepeated()) {
            Message originalMessage = (Message)this.getField(field);
            if (originalMessage != null) {
               subBuilder.mergeFrom(originalMessage);
            }
         }

         input.readGroup(field.getNumber(), (MessageLite.Builder)subBuilder, registry);
         return subBuilder.buildPartial();
      }

      public Object parseMessage(CodedInputStream input, ExtensionRegistryLite registry, Descriptors.FieldDescriptor field, Message defaultInstance) throws IOException {
         Message.Builder subBuilder = defaultInstance.newBuilderForType();
         if (!field.isRepeated()) {
            Message originalMessage = (Message)this.getField(field);
            if (originalMessage != null) {
               subBuilder.mergeFrom(originalMessage);
            }
         }

         input.readMessage((MessageLite.Builder)subBuilder, registry);
         return subBuilder.buildPartial();
      }

      public void mergeGroup(CodedInputStream input, ExtensionRegistryLite extensionRegistry, Descriptors.FieldDescriptor field, Message defaultInstance) throws IOException {
         if (!field.isRepeated()) {
            if (this.hasField(field)) {
               MessageLite.Builder current = ((MessageLite)this.getField(field)).toBuilder();
               input.readGroup(field.getNumber(), current, extensionRegistry);
               this.setField(field, current.buildPartial());
               return;
            }

            Message.Builder subBuilder = defaultInstance.newBuilderForType();
            input.readGroup(field.getNumber(), (MessageLite.Builder)subBuilder, extensionRegistry);
            this.setField(field, subBuilder.buildPartial());
         } else {
            Message.Builder subBuilder = defaultInstance.newBuilderForType();
            input.readGroup(field.getNumber(), (MessageLite.Builder)subBuilder, extensionRegistry);
            this.addRepeatedField(field, subBuilder.buildPartial());
         }

      }

      public void mergeMessage(CodedInputStream input, ExtensionRegistryLite extensionRegistry, Descriptors.FieldDescriptor field, Message defaultInstance) throws IOException {
         if (!field.isRepeated()) {
            if (this.hasField(field)) {
               MessageLite.Builder current = ((MessageLite)this.getField(field)).toBuilder();
               input.readMessage(current, extensionRegistry);
               this.setField(field, current.buildPartial());
               return;
            }

            Message.Builder subBuilder = defaultInstance.newBuilderForType();
            input.readMessage((MessageLite.Builder)subBuilder, extensionRegistry);
            this.setField(field, subBuilder.buildPartial());
         } else {
            Message.Builder subBuilder = defaultInstance.newBuilderForType();
            input.readMessage((MessageLite.Builder)subBuilder, extensionRegistry);
            this.addRepeatedField(field, subBuilder.buildPartial());
         }

      }

      public Object parseMessageFromBytes(ByteString bytes, ExtensionRegistryLite registry, Descriptors.FieldDescriptor field, Message defaultInstance) throws IOException {
         Message.Builder subBuilder = defaultInstance.newBuilderForType();
         if (!field.isRepeated()) {
            Message originalMessage = (Message)this.getField(field);
            if (originalMessage != null) {
               subBuilder.mergeFrom(originalMessage);
            }
         }

         subBuilder.mergeFrom(bytes, registry);
         return subBuilder.buildPartial();
      }

      public MergeTarget newMergeTargetForField(Descriptors.FieldDescriptor descriptor, Message defaultInstance) {
         throw new UnsupportedOperationException("newMergeTargetForField() called on FieldSet object");
      }

      public MergeTarget newEmptyTargetForField(Descriptors.FieldDescriptor descriptor, Message defaultInstance) {
         throw new UnsupportedOperationException("newEmptyTargetForField() called on FieldSet object");
      }

      public WireFormat.Utf8Validation getUtf8Validation(Descriptors.FieldDescriptor descriptor) {
         return descriptor.needsUtf8Check() ? WireFormat.Utf8Validation.STRICT : WireFormat.Utf8Validation.LOOSE;
      }

      public Object finish() {
         throw new UnsupportedOperationException("finish() called on FieldSet object");
      }
   }

   static class ExtensionBuilderAdapter implements MergeTarget {
      private final FieldSet.Builder extensions;

      ExtensionBuilderAdapter(FieldSet.Builder extensions) {
         this.extensions = extensions;
      }

      public Descriptors.Descriptor getDescriptorForType() {
         throw new UnsupportedOperationException("getDescriptorForType() called on FieldSet object");
      }

      public Object getField(Descriptors.FieldDescriptor field) {
         return this.extensions.getField(field);
      }

      public boolean hasField(Descriptors.FieldDescriptor field) {
         return this.extensions.hasField(field);
      }

      @CanIgnoreReturnValue
      public MergeTarget setField(Descriptors.FieldDescriptor field, Object value) {
         this.extensions.setField(field, value);
         return this;
      }

      @CanIgnoreReturnValue
      public MergeTarget clearField(Descriptors.FieldDescriptor field) {
         this.extensions.clearField(field);
         return this;
      }

      @CanIgnoreReturnValue
      public MergeTarget setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
         this.extensions.setRepeatedField(field, index, value);
         return this;
      }

      @CanIgnoreReturnValue
      public MergeTarget addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
         this.extensions.addRepeatedField(field, value);
         return this;
      }

      public boolean hasOneof(Descriptors.OneofDescriptor oneof) {
         return false;
      }

      @CanIgnoreReturnValue
      public MergeTarget clearOneof(Descriptors.OneofDescriptor oneof) {
         return this;
      }

      public Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor oneof) {
         return null;
      }

      public MergeTarget.ContainerType getContainerType() {
         return MessageReflection.MergeTarget.ContainerType.EXTENSION_SET;
      }

      public ExtensionRegistry.ExtensionInfo findExtensionByName(ExtensionRegistry registry, String name) {
         return registry.findImmutableExtensionByName(name);
      }

      public ExtensionRegistry.ExtensionInfo findExtensionByNumber(ExtensionRegistry registry, Descriptors.Descriptor containingType, int fieldNumber) {
         return registry.findImmutableExtensionByNumber(containingType, fieldNumber);
      }

      public Object parseGroup(CodedInputStream input, ExtensionRegistryLite registry, Descriptors.FieldDescriptor field, Message defaultInstance) throws IOException {
         Message.Builder subBuilder = defaultInstance.newBuilderForType();
         if (!field.isRepeated()) {
            Message originalMessage = (Message)this.getField(field);
            if (originalMessage != null) {
               subBuilder.mergeFrom(originalMessage);
            }
         }

         input.readGroup(field.getNumber(), (MessageLite.Builder)subBuilder, registry);
         return subBuilder.buildPartial();
      }

      public Object parseMessage(CodedInputStream input, ExtensionRegistryLite registry, Descriptors.FieldDescriptor field, Message defaultInstance) throws IOException {
         Message.Builder subBuilder = defaultInstance.newBuilderForType();
         if (!field.isRepeated()) {
            Message originalMessage = (Message)this.getField(field);
            if (originalMessage != null) {
               subBuilder.mergeFrom(originalMessage);
            }
         }

         input.readMessage((MessageLite.Builder)subBuilder, registry);
         return subBuilder.buildPartial();
      }

      public void mergeGroup(CodedInputStream input, ExtensionRegistryLite extensionRegistry, Descriptors.FieldDescriptor field, Message defaultInstance) throws IOException {
         if (!field.isRepeated()) {
            if (this.hasField(field)) {
               Object fieldOrBuilder = this.extensions.getFieldAllowBuilders(field);
               MessageLite.Builder subBuilder;
               if (fieldOrBuilder instanceof MessageLite.Builder) {
                  subBuilder = (MessageLite.Builder)fieldOrBuilder;
               } else {
                  subBuilder = ((MessageLite)fieldOrBuilder).toBuilder();
                  this.extensions.setField(field, subBuilder);
               }

               input.readGroup(field.getNumber(), subBuilder, extensionRegistry);
               return;
            }

            Message.Builder subBuilder = defaultInstance.newBuilderForType();
            input.readGroup(field.getNumber(), (MessageLite.Builder)subBuilder, extensionRegistry);
            this.setField(field, subBuilder);
         } else {
            Message.Builder subBuilder = defaultInstance.newBuilderForType();
            input.readGroup(field.getNumber(), (MessageLite.Builder)subBuilder, extensionRegistry);
            this.addRepeatedField(field, subBuilder.buildPartial());
         }

      }

      public void mergeMessage(CodedInputStream input, ExtensionRegistryLite extensionRegistry, Descriptors.FieldDescriptor field, Message defaultInstance) throws IOException {
         if (!field.isRepeated()) {
            if (this.hasField(field)) {
               Object fieldOrBuilder = this.extensions.getFieldAllowBuilders(field);
               MessageLite.Builder subBuilder;
               if (fieldOrBuilder instanceof MessageLite.Builder) {
                  subBuilder = (MessageLite.Builder)fieldOrBuilder;
               } else {
                  subBuilder = ((MessageLite)fieldOrBuilder).toBuilder();
                  this.extensions.setField(field, subBuilder);
               }

               input.readMessage(subBuilder, extensionRegistry);
               return;
            }

            Message.Builder subBuilder = defaultInstance.newBuilderForType();
            input.readMessage((MessageLite.Builder)subBuilder, extensionRegistry);
            this.setField(field, subBuilder);
         } else {
            Message.Builder subBuilder = defaultInstance.newBuilderForType();
            input.readMessage((MessageLite.Builder)subBuilder, extensionRegistry);
            this.addRepeatedField(field, subBuilder.buildPartial());
         }

      }

      public Object parseMessageFromBytes(ByteString bytes, ExtensionRegistryLite registry, Descriptors.FieldDescriptor field, Message defaultInstance) throws IOException {
         Message.Builder subBuilder = defaultInstance.newBuilderForType();
         if (!field.isRepeated()) {
            Message originalMessage = (Message)this.getField(field);
            if (originalMessage != null) {
               subBuilder.mergeFrom(originalMessage);
            }
         }

         subBuilder.mergeFrom(bytes, registry);
         return subBuilder.buildPartial();
      }

      public MergeTarget newMergeTargetForField(Descriptors.FieldDescriptor descriptor, Message defaultInstance) {
         throw new UnsupportedOperationException("newMergeTargetForField() called on FieldSet object");
      }

      public MergeTarget newEmptyTargetForField(Descriptors.FieldDescriptor descriptor, Message defaultInstance) {
         throw new UnsupportedOperationException("newEmptyTargetForField() called on FieldSet object");
      }

      public WireFormat.Utf8Validation getUtf8Validation(Descriptors.FieldDescriptor descriptor) {
         return descriptor.needsUtf8Check() ? WireFormat.Utf8Validation.STRICT : WireFormat.Utf8Validation.LOOSE;
      }

      public Object finish() {
         throw new UnsupportedOperationException("finish() called on FieldSet object");
      }
   }

   interface MergeTarget {
      Descriptors.Descriptor getDescriptorForType();

      ContainerType getContainerType();

      ExtensionRegistry.ExtensionInfo findExtensionByName(ExtensionRegistry registry, String name);

      ExtensionRegistry.ExtensionInfo findExtensionByNumber(ExtensionRegistry registry, Descriptors.Descriptor containingType, int fieldNumber);

      Object getField(Descriptors.FieldDescriptor field);

      boolean hasField(Descriptors.FieldDescriptor field);

      MergeTarget setField(Descriptors.FieldDescriptor field, Object value);

      MergeTarget clearField(Descriptors.FieldDescriptor field);

      MergeTarget setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value);

      MergeTarget addRepeatedField(Descriptors.FieldDescriptor field, Object value);

      boolean hasOneof(Descriptors.OneofDescriptor oneof);

      MergeTarget clearOneof(Descriptors.OneofDescriptor oneof);

      Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor oneof);

      Object parseGroup(CodedInputStream input, ExtensionRegistryLite registry, Descriptors.FieldDescriptor descriptor, Message defaultInstance) throws IOException;

      Object parseMessage(CodedInputStream input, ExtensionRegistryLite registry, Descriptors.FieldDescriptor descriptor, Message defaultInstance) throws IOException;

      Object parseMessageFromBytes(ByteString bytes, ExtensionRegistryLite registry, Descriptors.FieldDescriptor descriptor, Message defaultInstance) throws IOException;

      void mergeGroup(CodedInputStream input, ExtensionRegistryLite extensionRegistry, Descriptors.FieldDescriptor field, Message defaultInstance) throws IOException;

      void mergeMessage(CodedInputStream input, ExtensionRegistryLite extensionRegistry, Descriptors.FieldDescriptor field, Message defaultInstance) throws IOException;

      WireFormat.Utf8Validation getUtf8Validation(Descriptors.FieldDescriptor descriptor);

      MergeTarget newMergeTargetForField(Descriptors.FieldDescriptor descriptor, Message defaultInstance);

      MergeTarget newEmptyTargetForField(Descriptors.FieldDescriptor descriptor, Message defaultInstance);

      Object finish();

      public static enum ContainerType {
         MESSAGE,
         EXTENSION_SET;
      }
   }
}
