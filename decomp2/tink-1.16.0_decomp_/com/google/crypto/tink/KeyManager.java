package com.google.crypto.tink;

import com.google.crypto.tink.proto.KeyData;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLite;
import java.security.GeneralSecurityException;

public interface KeyManager {
   Object getPrimitive(ByteString serializedKey) throws GeneralSecurityException;

   /** @deprecated */
   @Deprecated
   default Object getPrimitive(MessageLite key) throws GeneralSecurityException {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   default MessageLite newKey(ByteString serializedKeyFormat) throws GeneralSecurityException {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   default MessageLite newKey(MessageLite keyFormat) throws GeneralSecurityException {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   default boolean doesSupport(String typeUrl) {
      throw new UnsupportedOperationException();
   }

   String getKeyType();

   /** @deprecated */
   @Deprecated
   default int getVersion() {
      throw new UnsupportedOperationException();
   }

   Class getPrimitiveClass();

   KeyData newKeyData(ByteString serializedKeyFormat) throws GeneralSecurityException;
}
