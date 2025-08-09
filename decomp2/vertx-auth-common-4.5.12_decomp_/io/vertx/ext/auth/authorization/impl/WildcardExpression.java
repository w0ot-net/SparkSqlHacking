package io.vertx.ext.auth.authorization.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

class WildcardExpression {
   protected static final String PART_DIVIDER_TOKEN = ":";
   protected static final String SUBPART_DIVIDER_TOKEN = ",";
   protected static final String WILDCARD_TOKEN = "*";
   private List parts;
   private final String value;

   public WildcardExpression(String value) {
      Objects.requireNonNull(value);
      this.value = value.trim();
      if (value.isEmpty()) {
         throw new IllegalArgumentException("Wildcard value cannot be empty");
      } else {
         this.setParts(value);
      }
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (!(obj instanceof WildcardExpression)) {
         return false;
      } else {
         WildcardExpression other = (WildcardExpression)obj;
         return Objects.equals(this.parts, other.parts);
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.parts});
   }

   public boolean implies(String p) {
      if (p == null) {
         return false;
      } else {
         return this.value.equals(p) ? true : this.implies(new WildcardExpression(p));
      }
   }

   public boolean implies(WildcardExpression p) {
      if (p == null) {
         return false;
      } else {
         List<Set<String>> otherParts = p.parts;
         int i = 0;

         for(Set otherPart : otherParts) {
            if (this.parts.size() - 1 < i) {
               return true;
            }

            Set<String> part = (Set)this.parts.get(i);
            if (!part.contains("*") && !part.containsAll(otherPart)) {
               return false;
            }

            ++i;
         }

         while(i < this.parts.size()) {
            Set<String> part = (Set)this.parts.get(i);
            if (!part.contains("*")) {
               return false;
            }

            ++i;
         }

         return true;
      }
   }

   protected void setParts(String wildcardString) {
      wildcardString = wildcardString.trim();
      if (wildcardString.isEmpty()) {
         throw new IllegalArgumentException("Wildcard string cannot be empty");
      } else {
         this.parts = new ArrayList();

         for(String part : wildcardString.split(":")) {
            Set<String> subparts = new LinkedHashSet(Arrays.asList(part.split(",")));
            if (subparts.isEmpty()) {
               throw new IllegalArgumentException("Wildcard string cannot contain parts with only dividers. Make sure permission strings are properly formatted.");
            }

            this.parts.add(subparts);
         }

         if (this.parts.isEmpty()) {
            throw new IllegalArgumentException("Wildcard string cannot contain only dividers. Make sure permission strings are properly formatted.");
         }
      }
   }

   public String toString() {
      return this.value;
   }
}
