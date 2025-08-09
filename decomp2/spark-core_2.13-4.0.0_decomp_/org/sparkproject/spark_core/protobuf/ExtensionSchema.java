package org.sparkproject.spark_core.protobuf;

import java.io.IOException;
import java.util.Map;

@CheckReturnValue
abstract class ExtensionSchema {
   abstract boolean hasExtensions(MessageLite prototype);

   abstract FieldSet getExtensions(Object message);

   abstract void setExtensions(Object message, FieldSet extensions);

   abstract FieldSet getMutableExtensions(Object message);

   abstract void makeImmutable(Object message);

   abstract Object parseExtension(Object containerMessage, Reader reader, Object extension, ExtensionRegistryLite extensionRegistry, FieldSet extensions, Object unknownFields, UnknownFieldSchema unknownFieldSchema) throws IOException;

   abstract int extensionNumber(Map.Entry extension);

   abstract void serializeExtension(Writer writer, Map.Entry extension) throws IOException;

   abstract Object findExtensionByNumber(ExtensionRegistryLite extensionRegistry, MessageLite defaultInstance, int number);

   abstract void parseLengthPrefixedMessageSetItem(Reader reader, Object extension, ExtensionRegistryLite extensionRegistry, FieldSet extensions) throws IOException;

   abstract void parseMessageSetItem(ByteString data, Object extension, ExtensionRegistryLite extensionRegistry, FieldSet extensions) throws IOException;
}
