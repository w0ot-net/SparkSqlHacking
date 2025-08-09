package com.google.crypto.tink;

import com.google.crypto.tink.proto.KeyData;
import com.google.protobuf.ByteString;
import java.security.GeneralSecurityException;

public interface PrivateKeyManager extends KeyManager {
   KeyData getPublicKeyData(ByteString serializedKey) throws GeneralSecurityException;
}
