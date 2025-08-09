package io.jsonwebtoken.io;

import java.io.OutputStream;

public interface Serializer {
   /** @deprecated */
   @Deprecated
   byte[] serialize(Object var1) throws SerializationException;

   void serialize(Object var1, OutputStream var2) throws SerializationException;
}
