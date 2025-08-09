package org.apache.commons.text.similarity;

import org.apache.commons.lang3.Validate;

public class EditDistanceFrom {
   private final EditDistance editDistance;
   private final CharSequence left;

   public EditDistanceFrom(EditDistance editDistance, CharSequence left) {
      Validate.isTrue(editDistance != null, "The edit distance may not be null.", new Object[0]);
      this.editDistance = editDistance;
      this.left = left;
   }

   public Object apply(CharSequence right) {
      return this.editDistance.apply(this.left, right);
   }

   public EditDistance getEditDistance() {
      return this.editDistance;
   }

   public CharSequence getLeft() {
      return this.left;
   }
}
