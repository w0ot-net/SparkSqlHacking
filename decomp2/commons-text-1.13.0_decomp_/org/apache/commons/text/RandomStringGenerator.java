package org.apache.commons.text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;

public final class RandomStringGenerator {
   private final int minimumCodePoint;
   private final int maximumCodePoint;
   private final Set inclusivePredicates;
   private final TextRandomProvider random;
   private final List characterList;

   public static Builder builder() {
      return new Builder();
   }

   private RandomStringGenerator(int minimumCodePoint, int maximumCodePoint, Set inclusivePredicates, TextRandomProvider random, List characterList) {
      this.minimumCodePoint = minimumCodePoint;
      this.maximumCodePoint = maximumCodePoint;
      this.inclusivePredicates = inclusivePredicates;
      this.random = random;
      this.characterList = characterList;
   }

   public String generate(int length) {
      if (length == 0) {
         return "";
      } else {
         Validate.isTrue(length > 0, "Length %d is smaller than zero.", (long)length);
         StringBuilder builder = new StringBuilder(length);
         long remaining = (long)length;

         do {
            int codePoint;
            if (this.characterList != null && !this.characterList.isEmpty()) {
               codePoint = this.generateRandomNumber(this.characterList);
            } else {
               codePoint = this.generateRandomNumber(this.minimumCodePoint, this.maximumCodePoint);
            }

            switch (Character.getType(codePoint)) {
               case 0:
               case 18:
               case 19:
                  continue;
            }

            if (this.inclusivePredicates != null) {
               boolean matchedFilter = false;

               for(CharacterPredicate predicate : this.inclusivePredicates) {
                  if (predicate.test(codePoint)) {
                     matchedFilter = true;
                     break;
                  }
               }

               if (!matchedFilter) {
                  continue;
               }
            }

            builder.appendCodePoint(codePoint);
            --remaining;
         } while(remaining != 0L);

         return builder.toString();
      }
   }

   public String generate(int minLengthInclusive, int maxLengthInclusive) {
      Validate.isTrue(minLengthInclusive >= 0, "Minimum length %d is smaller than zero.", (long)minLengthInclusive);
      Validate.isTrue(minLengthInclusive <= maxLengthInclusive, "Maximum length %d is smaller than minimum length %d.", new Object[]{maxLengthInclusive, minLengthInclusive});
      return this.generate(this.generateRandomNumber(minLengthInclusive, maxLengthInclusive));
   }

   private int generateRandomNumber(int minInclusive, int maxInclusive) {
      return this.random != null ? this.random.nextInt(maxInclusive - minInclusive + 1) + minInclusive : ThreadLocalRandom.current().nextInt(minInclusive, maxInclusive + 1);
   }

   private int generateRandomNumber(List characterList) {
      int listSize = characterList.size();
      return this.random != null ? String.valueOf(characterList.get(this.random.nextInt(listSize))).codePointAt(0) : String.valueOf(characterList.get(ThreadLocalRandom.current().nextInt(0, listSize))).codePointAt(0);
   }

   public static class Builder implements org.apache.commons.text.Builder {
      public static final int DEFAULT_MAXIMUM_CODE_POINT = 1114111;
      public static final int DEFAULT_LENGTH = 0;
      public static final int DEFAULT_MINIMUM_CODE_POINT = 0;
      private int minimumCodePoint = 0;
      private int maximumCodePoint = 1114111;
      private Set inclusivePredicates;
      private TextRandomProvider random;
      private List characterList;

      /** @deprecated */
      @Deprecated
      public RandomStringGenerator build() {
         return this.get();
      }

      public Builder filteredBy(CharacterPredicate... predicates) {
         if (ArrayUtils.isEmpty(predicates)) {
            this.inclusivePredicates = null;
            return this;
         } else {
            if (this.inclusivePredicates == null) {
               this.inclusivePredicates = new HashSet();
            } else {
               this.inclusivePredicates.clear();
            }

            Collections.addAll(this.inclusivePredicates, predicates);
            return this;
         }
      }

      public RandomStringGenerator get() {
         return new RandomStringGenerator(this.minimumCodePoint, this.maximumCodePoint, this.inclusivePredicates, this.random, this.characterList);
      }

      public Builder selectFrom(char... chars) {
         this.characterList = new ArrayList();
         if (chars != null) {
            for(char c : chars) {
               this.characterList.add(c);
            }
         }

         return this;
      }

      public Builder usingRandom(TextRandomProvider random) {
         this.random = random;
         return this;
      }

      public Builder withinRange(char[]... pairs) {
         this.characterList = new ArrayList();
         if (pairs != null) {
            for(char[] pair : pairs) {
               Validate.isTrue(pair.length == 2, "Each pair must contain minimum and maximum code point", new Object[0]);
               int minimumCodePoint = pair[0];
               int maximumCodePoint = pair[1];
               Validate.isTrue(minimumCodePoint <= maximumCodePoint, "Minimum code point %d is larger than maximum code point %d", new Object[]{minimumCodePoint, maximumCodePoint});

               for(int index = minimumCodePoint; index <= maximumCodePoint; ++index) {
                  this.characterList.add((char)index);
               }
            }
         }

         return this;
      }

      public Builder withinRange(int minimumCodePoint, int maximumCodePoint) {
         Validate.isTrue(minimumCodePoint <= maximumCodePoint, "Minimum code point %d is larger than maximum code point %d", new Object[]{minimumCodePoint, maximumCodePoint});
         Validate.isTrue(minimumCodePoint >= 0, "Minimum code point %d is negative", (long)minimumCodePoint);
         Validate.isTrue(maximumCodePoint <= 1114111, "Value %d is larger than Character.MAX_CODE_POINT.", (long)maximumCodePoint);
         this.minimumCodePoint = minimumCodePoint;
         this.maximumCodePoint = maximumCodePoint;
         return this;
      }
   }
}
