package io.jsonwebtoken.io;

import java.io.Reader;

public interface Deserializer {
   /** @deprecated */
   @Deprecated
   Object deserialize(byte[] var1) throws DeserializationException;

   Object deserialize(Reader var1) throws DeserializationException;
}
