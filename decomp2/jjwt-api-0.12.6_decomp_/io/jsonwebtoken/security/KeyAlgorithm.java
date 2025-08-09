package io.jsonwebtoken.security;

import io.jsonwebtoken.Identifiable;
import javax.crypto.SecretKey;

public interface KeyAlgorithm extends Identifiable {
   KeyResult getEncryptionKey(KeyRequest var1) throws SecurityException;

   SecretKey getDecryptionKey(DecryptionKeyRequest var1) throws SecurityException;
}
