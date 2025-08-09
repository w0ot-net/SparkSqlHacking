package com.google.crypto.tink.jwt;

import com.google.crypto.tink.Key;
import java.util.Optional;

public abstract class JwtMacKey extends Key {
   public abstract Optional getKid();

   public abstract JwtMacParameters getParameters();
}
