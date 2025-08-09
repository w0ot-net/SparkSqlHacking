package io.jsonwebtoken.security;

import io.jsonwebtoken.Identifiable;
import java.io.OutputStream;

public interface AeadAlgorithm extends Identifiable, KeyLengthSupplier, KeyBuilderSupplier {
   void encrypt(AeadRequest var1, AeadResult var2) throws SecurityException;

   void decrypt(DecryptAeadRequest var1, OutputStream var2) throws SecurityException;
}
