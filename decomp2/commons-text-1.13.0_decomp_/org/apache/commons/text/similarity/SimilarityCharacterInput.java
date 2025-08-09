package org.apache.commons.text.similarity;

import java.util.Objects;

final class SimilarityCharacterInput implements SimilarityInput {
   private final CharSequence cs;

   SimilarityCharacterInput(CharSequence cs) {
      if (cs == null) {
         throw new IllegalArgumentException("CharSequence");
      } else {
         this.cs = cs;
      }
   }

   public Character at(int index) {
      return this.cs.charAt(index);
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         SimilarityCharacterInput other = (SimilarityCharacterInput)obj;
         return Objects.equals(this.cs, other.cs);
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.cs});
   }

   public int length() {
      return this.cs.length();
   }

   public String toString() {
      return this.cs.toString();
   }
}
