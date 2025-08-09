package org.apache.orc.protobuf;

import java.io.IOException;

@CheckReturnValue
interface Schema {
   void writeTo(Object message, Writer writer) throws IOException;

   void mergeFrom(Object message, Reader reader, ExtensionRegistryLite extensionRegistry) throws IOException;

   void mergeFrom(Object message, byte[] data, int position, int limit, ArrayDecoders.Registers registers) throws IOException;

   void makeImmutable(Object message);

   boolean isInitialized(Object message);

   Object newInstance();

   boolean equals(Object message, Object other);

   int hashCode(Object message);

   void mergeFrom(Object message, Object other);

   int getSerializedSize(Object message);
}
