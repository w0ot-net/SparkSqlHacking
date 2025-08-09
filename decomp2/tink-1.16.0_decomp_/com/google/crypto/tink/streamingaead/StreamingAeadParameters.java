package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.Parameters;

public abstract class StreamingAeadParameters extends Parameters {
   public final boolean hasIdRequirement() {
      return false;
   }
}
