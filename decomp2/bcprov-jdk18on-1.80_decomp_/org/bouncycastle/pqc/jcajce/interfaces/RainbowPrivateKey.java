package org.bouncycastle.pqc.jcajce.interfaces;

import java.security.PrivateKey;

public interface RainbowPrivateKey extends PrivateKey, RainbowKey {
   RainbowPublicKey getPublicKey();
}
