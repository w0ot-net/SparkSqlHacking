package com.google.crypto.tink.internal;

import java.security.GeneralSecurityException;

public interface PrimitiveWrapper {
   Object wrap(PrimitiveSet primitiveSet) throws GeneralSecurityException;

   Class getPrimitiveClass();

   Class getInputPrimitiveClass();
}
