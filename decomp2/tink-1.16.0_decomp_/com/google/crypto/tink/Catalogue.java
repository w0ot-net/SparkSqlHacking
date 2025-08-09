package com.google.crypto.tink;

import com.google.crypto.tink.internal.PrimitiveWrapper;
import java.security.GeneralSecurityException;

/** @deprecated */
@Deprecated
public interface Catalogue {
   KeyManager getKeyManager(String typeUrl, String primitiveName, int minVersion) throws GeneralSecurityException;

   PrimitiveWrapper getPrimitiveWrapper() throws GeneralSecurityException;
}
