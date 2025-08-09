package org.bouncycastle.jcajce.interfaces;

import java.security.Key;
import org.bouncycastle.jcajce.spec.SLHDSAParameterSpec;

public interface SLHDSAKey extends Key {
   SLHDSAParameterSpec getParameterSpec();
}
