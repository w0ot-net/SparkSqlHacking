package org.apache.orc.protobuf;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@CheckReturnValue
interface Reader {
   int READ_DONE = Integer.MAX_VALUE;
   int TAG_UNKNOWN = 0;

   boolean shouldDiscardUnknownFields();

   int getFieldNumber() throws IOException;

   int getTag();

   boolean skipField() throws IOException;

   double readDouble() throws IOException;

   float readFloat() throws IOException;

   long readUInt64() throws IOException;

   long readInt64() throws IOException;

   int readInt32() throws IOException;

   long readFixed64() throws IOException;

   int readFixed32() throws IOException;

   boolean readBool() throws IOException;

   String readString() throws IOException;

   String readStringRequireUtf8() throws IOException;

   Object readMessageBySchemaWithCheck(Schema schema, ExtensionRegistryLite extensionRegistry) throws IOException;

   Object readMessage(Class clazz, ExtensionRegistryLite extensionRegistry) throws IOException;

   /** @deprecated */
   @Deprecated
   Object readGroup(Class clazz, ExtensionRegistryLite extensionRegistry) throws IOException;

   /** @deprecated */
   @Deprecated
   Object readGroupBySchemaWithCheck(Schema schema, ExtensionRegistryLite extensionRegistry) throws IOException;

   void mergeMessageField(Object target, Schema schema, ExtensionRegistryLite extensionRegistry) throws IOException;

   void mergeGroupField(Object target, Schema schema, ExtensionRegistryLite extensionRegistry) throws IOException;

   ByteString readBytes() throws IOException;

   int readUInt32() throws IOException;

   int readEnum() throws IOException;

   int readSFixed32() throws IOException;

   long readSFixed64() throws IOException;

   int readSInt32() throws IOException;

   long readSInt64() throws IOException;

   void readDoubleList(List target) throws IOException;

   void readFloatList(List target) throws IOException;

   void readUInt64List(List target) throws IOException;

   void readInt64List(List target) throws IOException;

   void readInt32List(List target) throws IOException;

   void readFixed64List(List target) throws IOException;

   void readFixed32List(List target) throws IOException;

   void readBoolList(List target) throws IOException;

   void readStringList(List target) throws IOException;

   void readStringListRequireUtf8(List target) throws IOException;

   void readMessageList(List target, Schema schema, ExtensionRegistryLite extensionRegistry) throws IOException;

   void readMessageList(List target, Class targetType, ExtensionRegistryLite extensionRegistry) throws IOException;

   /** @deprecated */
   @Deprecated
   void readGroupList(List target, Class targetType, ExtensionRegistryLite extensionRegistry) throws IOException;

   /** @deprecated */
   @Deprecated
   void readGroupList(List target, Schema targetType, ExtensionRegistryLite extensionRegistry) throws IOException;

   void readBytesList(List target) throws IOException;

   void readUInt32List(List target) throws IOException;

   void readEnumList(List target) throws IOException;

   void readSFixed32List(List target) throws IOException;

   void readSFixed64List(List target) throws IOException;

   void readSInt32List(List target) throws IOException;

   void readSInt64List(List target) throws IOException;

   void readMap(Map target, MapEntryLite.Metadata mapDefaultEntry, ExtensionRegistryLite extensionRegistry) throws IOException;
}
