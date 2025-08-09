package com.google.crypto.tink.keyderivation;

import com.google.crypto.tink.Parameters;

public abstract class KeyDerivationParameters extends Parameters {
   public abstract Parameters getDerivedKeyParameters();

   public boolean hasIdRequirement() {
      return this.getDerivedKeyParameters().hasIdRequirement();
   }
}
