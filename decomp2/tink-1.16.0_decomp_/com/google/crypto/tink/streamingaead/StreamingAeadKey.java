package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.Key;
import javax.annotation.Nullable;

public abstract class StreamingAeadKey extends Key {
   @Nullable
   public final Integer getIdRequirementOrNull() {
      return null;
   }

   public abstract StreamingAeadParameters getParameters();
}
