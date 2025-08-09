package org.sparkproject.jetty.security;

public enum UserDataConstraint {
   None,
   Integral,
   Confidential;

   public static UserDataConstraint get(int dataConstraint) {
      if (dataConstraint >= -1 && dataConstraint <= 2) {
         return dataConstraint == -1 ? None : values()[dataConstraint];
      } else {
         throw new IllegalArgumentException("Expected -1, 0, 1, or 2, not: " + dataConstraint);
      }
   }

   public UserDataConstraint combine(UserDataConstraint other) {
      return this.compareTo(other) < 0 ? this : other;
   }

   // $FF: synthetic method
   private static UserDataConstraint[] $values() {
      return new UserDataConstraint[]{None, Integral, Confidential};
   }
}
