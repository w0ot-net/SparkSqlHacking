package org.sparkproject.spark_core.protobuf;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

final class ExtensionSchemaFull extends ExtensionSchema {
   private static final long EXTENSION_FIELD_OFFSET = getExtensionsFieldOffset();

   private static long getExtensionsFieldOffset() {
      try {
         java.lang.reflect.Field field = GeneratedMessage.ExtendableMessage.class.getDeclaredField("extensions");
         return UnsafeUtil.objectFieldOffset(field);
      } catch (Throwable var1) {
         throw new IllegalStateException("Unable to lookup extension field offset");
      }
   }

   boolean hasExtensions(MessageLite prototype) {
      return prototype instanceof GeneratedMessage.ExtendableMessage;
   }

   public FieldSet getExtensions(Object message) {
      return (FieldSet)UnsafeUtil.getObject(message, EXTENSION_FIELD_OFFSET);
   }

   void setExtensions(Object message, FieldSet extensions) {
      UnsafeUtil.putObject((Object)message, EXTENSION_FIELD_OFFSET, extensions);
   }

   FieldSet getMutableExtensions(Object message) {
      FieldSet<Descriptors.FieldDescriptor> extensions = this.getExtensions(message);
      if (extensions.isImmutable()) {
         extensions = extensions.clone();
         this.setExtensions(message, extensions);
      }

      return extensions;
   }

   void makeImmutable(Object message) {
      this.getExtensions(message).makeImmutable();
   }

   Object parseExtension(Object containerMessage, Reader reader, Object extensionObject, ExtensionRegistryLite extensionRegistry, FieldSet extensions, Object unknownFields, UnknownFieldSchema unknownFieldSchema) throws IOException {
      ExtensionRegistry.ExtensionInfo extension = (ExtensionRegistry.ExtensionInfo)extensionObject;
      int fieldNumber = extension.descriptor.getNumber();
      if (extension.descriptor.isRepeated() && extension.descriptor.isPacked()) {
         Object value = null;
         switch (extension.descriptor.getLiteType()) {
            case DOUBLE:
               List<Double> list = new ArrayList();
               reader.readDoubleList(list);
               value = list;
               break;
            case FLOAT:
               List<Float> list = new ArrayList();
               reader.readFloatList(list);
               value = list;
               break;
            case INT64:
               List<Long> list = new ArrayList();
               reader.readInt64List(list);
               value = list;
               break;
            case UINT64:
               List<Long> list = new ArrayList();
               reader.readUInt64List(list);
               value = list;
               break;
            case INT32:
               List<Integer> list = new ArrayList();
               reader.readInt32List(list);
               value = list;
               break;
            case FIXED64:
               List<Long> list = new ArrayList();
               reader.readFixed64List(list);
               value = list;
               break;
            case FIXED32:
               List<Integer> list = new ArrayList();
               reader.readFixed32List(list);
               value = list;
               break;
            case BOOL:
               List<Boolean> list = new ArrayList();
               reader.readBoolList(list);
               value = list;
               break;
            case UINT32:
               List<Integer> list = new ArrayList();
               reader.readUInt32List(list);
               value = list;
               break;
            case SFIXED32:
               List<Integer> list = new ArrayList();
               reader.readSFixed32List(list);
               value = list;
               break;
            case SFIXED64:
               List<Long> list = new ArrayList();
               reader.readSFixed64List(list);
               value = list;
               break;
            case SINT32:
               List<Integer> list = new ArrayList();
               reader.readSInt32List(list);
               value = list;
               break;
            case SINT64:
               List<Long> list = new ArrayList();
               reader.readSInt64List(list);
               value = list;
               break;
            case ENUM:
               List<Integer> list = new ArrayList();
               reader.readEnumList(list);
               List<Descriptors.EnumValueDescriptor> enumList = new ArrayList();

               for(int number : list) {
                  Descriptors.EnumValueDescriptor enumDescriptor = extension.descriptor.getEnumType().findValueByNumber(number);
                  if (enumDescriptor != null) {
                     enumList.add(enumDescriptor);
                  } else {
                     unknownFields = (UB)SchemaUtil.storeUnknownEnum(containerMessage, fieldNumber, number, unknownFields, unknownFieldSchema);
                  }
               }

               value = enumList;
               break;
            default:
               throw new IllegalStateException("Type cannot be packed: " + extension.descriptor.getLiteType());
         }

         extensions.setField(extension.descriptor, value);
      } else {
         Object value = null;
         if (extension.descriptor.getLiteType() == WireFormat.FieldType.ENUM) {
            int number = reader.readInt32();
            Object enumValue = extension.descriptor.getEnumType().findValueByNumber(number);
            if (enumValue == null) {
               return SchemaUtil.storeUnknownEnum(containerMessage, fieldNumber, number, unknownFields, unknownFieldSchema);
            }

            value = enumValue;
         } else {
            switch (extension.descriptor.getLiteType()) {
               case DOUBLE:
                  value = reader.readDouble();
                  break;
               case FLOAT:
                  value = reader.readFloat();
                  break;
               case INT64:
                  value = reader.readInt64();
                  break;
               case UINT64:
                  value = reader.readUInt64();
                  break;
               case INT32:
                  value = reader.readInt32();
                  break;
               case FIXED64:
                  value = reader.readFixed64();
                  break;
               case FIXED32:
                  value = reader.readFixed32();
                  break;
               case BOOL:
                  value = reader.readBool();
                  break;
               case UINT32:
                  value = reader.readUInt32();
                  break;
               case SFIXED32:
                  value = reader.readSFixed32();
                  break;
               case SFIXED64:
                  value = reader.readSFixed64();
                  break;
               case SINT32:
                  value = reader.readSInt32();
                  break;
               case SINT64:
                  value = reader.readSInt64();
                  break;
               case ENUM:
                  throw new IllegalStateException("Shouldn't reach here.");
               case BYTES:
                  value = reader.readBytes();
                  break;
               case STRING:
                  value = reader.readString();
                  break;
               case GROUP:
                  value = reader.readGroup(extension.defaultInstance.getClass(), extensionRegistry);
                  break;
               case MESSAGE:
                  value = reader.readMessage(extension.defaultInstance.getClass(), extensionRegistry);
            }
         }

         if (extension.descriptor.isRepeated()) {
            extensions.addRepeatedField(extension.descriptor, value);
         } else {
            switch (extension.descriptor.getLiteType()) {
               case GROUP:
               case MESSAGE:
                  Object oldValue = extensions.getField(extension.descriptor);
                  if (oldValue != null) {
                     value = Internal.mergeMessage(oldValue, value);
                  }
               default:
                  extensions.setField(extension.descriptor, value);
            }
         }
      }

      return unknownFields;
   }

   int extensionNumber(Map.Entry extension) {
      Descriptors.FieldDescriptor descriptor = (Descriptors.FieldDescriptor)extension.getKey();
      return descriptor.getNumber();
   }

   void serializeExtension(Writer writer, Map.Entry extension) throws IOException {
      Descriptors.FieldDescriptor descriptor = (Descriptors.FieldDescriptor)extension.getKey();
      if (descriptor.isRepeated()) {
         switch (descriptor.getLiteType()) {
            case DOUBLE:
               SchemaUtil.writeDoubleList(descriptor.getNumber(), (List)extension.getValue(), writer, descriptor.isPacked());
               break;
            case FLOAT:
               SchemaUtil.writeFloatList(descriptor.getNumber(), (List)extension.getValue(), writer, descriptor.isPacked());
               break;
            case INT64:
               SchemaUtil.writeInt64List(descriptor.getNumber(), (List)extension.getValue(), writer, descriptor.isPacked());
               break;
            case UINT64:
               SchemaUtil.writeUInt64List(descriptor.getNumber(), (List)extension.getValue(), writer, descriptor.isPacked());
               break;
            case INT32:
               SchemaUtil.writeInt32List(descriptor.getNumber(), (List)extension.getValue(), writer, descriptor.isPacked());
               break;
            case FIXED64:
               SchemaUtil.writeFixed64List(descriptor.getNumber(), (List)extension.getValue(), writer, descriptor.isPacked());
               break;
            case FIXED32:
               SchemaUtil.writeFixed32List(descriptor.getNumber(), (List)extension.getValue(), writer, descriptor.isPacked());
               break;
            case BOOL:
               SchemaUtil.writeBoolList(descriptor.getNumber(), (List)extension.getValue(), writer, descriptor.isPacked());
               break;
            case UINT32:
               SchemaUtil.writeUInt32List(descriptor.getNumber(), (List)extension.getValue(), writer, descriptor.isPacked());
               break;
            case SFIXED32:
               SchemaUtil.writeSFixed32List(descriptor.getNumber(), (List)extension.getValue(), writer, descriptor.isPacked());
               break;
            case SFIXED64:
               SchemaUtil.writeSFixed64List(descriptor.getNumber(), (List)extension.getValue(), writer, descriptor.isPacked());
               break;
            case SINT32:
               SchemaUtil.writeSInt32List(descriptor.getNumber(), (List)extension.getValue(), writer, descriptor.isPacked());
               break;
            case SINT64:
               SchemaUtil.writeSInt64List(descriptor.getNumber(), (List)extension.getValue(), writer, descriptor.isPacked());
               break;
            case ENUM:
               List<Descriptors.EnumValueDescriptor> enumList = (List)extension.getValue();
               List<Integer> list = new ArrayList();

               for(Descriptors.EnumValueDescriptor d : enumList) {
                  list.add(d.getNumber());
               }

               SchemaUtil.writeInt32List(descriptor.getNumber(), list, writer, descriptor.isPacked());
               break;
            case BYTES:
               SchemaUtil.writeBytesList(descriptor.getNumber(), (List)extension.getValue(), writer);
               break;
            case STRING:
               SchemaUtil.writeStringList(descriptor.getNumber(), (List)extension.getValue(), writer);
               break;
            case GROUP:
               SchemaUtil.writeGroupList(descriptor.getNumber(), (List)extension.getValue(), writer);
               break;
            case MESSAGE:
               SchemaUtil.writeMessageList(descriptor.getNumber(), (List)extension.getValue(), writer);
         }
      } else {
         switch (descriptor.getLiteType()) {
            case DOUBLE:
               writer.writeDouble(descriptor.getNumber(), (Double)extension.getValue());
               break;
            case FLOAT:
               writer.writeFloat(descriptor.getNumber(), (Float)extension.getValue());
               break;
            case INT64:
               writer.writeInt64(descriptor.getNumber(), (Long)extension.getValue());
               break;
            case UINT64:
               writer.writeUInt64(descriptor.getNumber(), (Long)extension.getValue());
               break;
            case INT32:
               writer.writeInt32(descriptor.getNumber(), (Integer)extension.getValue());
               break;
            case FIXED64:
               writer.writeFixed64(descriptor.getNumber(), (Long)extension.getValue());
               break;
            case FIXED32:
               writer.writeFixed32(descriptor.getNumber(), (Integer)extension.getValue());
               break;
            case BOOL:
               writer.writeBool(descriptor.getNumber(), (Boolean)extension.getValue());
               break;
            case UINT32:
               writer.writeUInt32(descriptor.getNumber(), (Integer)extension.getValue());
               break;
            case SFIXED32:
               writer.writeSFixed32(descriptor.getNumber(), (Integer)extension.getValue());
               break;
            case SFIXED64:
               writer.writeSFixed64(descriptor.getNumber(), (Long)extension.getValue());
               break;
            case SINT32:
               writer.writeSInt32(descriptor.getNumber(), (Integer)extension.getValue());
               break;
            case SINT64:
               writer.writeSInt64(descriptor.getNumber(), (Long)extension.getValue());
               break;
            case ENUM:
               writer.writeInt32(descriptor.getNumber(), ((Descriptors.EnumValueDescriptor)extension.getValue()).getNumber());
               break;
            case BYTES:
               writer.writeBytes(descriptor.getNumber(), (ByteString)extension.getValue());
               break;
            case STRING:
               writer.writeString(descriptor.getNumber(), (String)extension.getValue());
               break;
            case GROUP:
               writer.writeGroup(descriptor.getNumber(), extension.getValue());
               break;
            case MESSAGE:
               writer.writeMessage(descriptor.getNumber(), extension.getValue());
         }
      }

   }

   Object findExtensionByNumber(ExtensionRegistryLite extensionRegistry, MessageLite defaultInstance, int number) {
      return ((ExtensionRegistry)extensionRegistry).findImmutableExtensionByNumber(((Message)defaultInstance).getDescriptorForType(), number);
   }

   void parseLengthPrefixedMessageSetItem(Reader reader, Object extension, ExtensionRegistryLite extensionRegistry, FieldSet extensions) throws IOException {
      ExtensionRegistry.ExtensionInfo extensionInfo = (ExtensionRegistry.ExtensionInfo)extension;
      if (ExtensionRegistryLite.isEagerlyParseMessageSets()) {
         Object value = reader.readMessage(extensionInfo.defaultInstance.getClass(), extensionRegistry);
         extensions.setField(extensionInfo.descriptor, value);
      } else {
         extensions.setField(extensionInfo.descriptor, new LazyField(extensionInfo.defaultInstance, extensionRegistry, reader.readBytes()));
      }

   }

   void parseMessageSetItem(ByteString data, Object extension, ExtensionRegistryLite extensionRegistry, FieldSet extensions) throws IOException {
      ExtensionRegistry.ExtensionInfo extensionInfo = (ExtensionRegistry.ExtensionInfo)extension;
      Object value = extensionInfo.defaultInstance.newBuilderForType().buildPartial();
      if (ExtensionRegistryLite.isEagerlyParseMessageSets()) {
         Reader reader = BinaryReader.newInstance(ByteBuffer.wrap(data.toByteArray()), true);
         Protobuf.getInstance().mergeFrom(value, reader, extensionRegistry);
         extensions.setField(extensionInfo.descriptor, value);
         if (reader.getFieldNumber() != Integer.MAX_VALUE) {
            throw InvalidProtocolBufferException.invalidEndTag();
         }
      } else {
         extensions.setField(extensionInfo.descriptor, new LazyField(extensionInfo.defaultInstance, extensionRegistry, data));
      }

   }
}
