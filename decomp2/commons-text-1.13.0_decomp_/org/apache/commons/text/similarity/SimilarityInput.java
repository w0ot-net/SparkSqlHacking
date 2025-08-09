package org.apache.commons.text.similarity;

import java.util.Objects;

public interface SimilarityInput {
   static SimilarityInput input(CharSequence cs) {
      return new SimilarityCharacterInput(cs);
   }

   static SimilarityInput input(Object input) {
      if (input instanceof SimilarityInput) {
         return (SimilarityInput)input;
      } else if (input instanceof CharSequence) {
         return input((CharSequence)input);
      } else {
         throw new IllegalArgumentException(Objects.requireNonNull(input, "input").getClass().getName());
      }
   }

   Object at(int var1);

   int length();
}
