package org.bouncycastle.jcajce.interfaces;

import java.security.PublicKey;

public interface MLKEMPublicKey extends PublicKey, MLKEMKey {
   byte[] getPublicData();
}
