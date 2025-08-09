package org.roaringbitmap.art;

class SearchResult {
   final Outcome outcome;
   private final int lessOrEqualPos;
   private final int greaterPos;

   private SearchResult(Outcome outcome, int lessOrEqualPos, int greaterPos) {
      this.outcome = outcome;
      this.lessOrEqualPos = lessOrEqualPos;
      this.greaterPos = greaterPos;
   }

   static SearchResult found(int keyPos) {
      return new SearchResult(SearchResult.Outcome.FOUND, keyPos, -1);
   }

   static SearchResult notFound(int lowerPos, int higherPos) {
      return new SearchResult(SearchResult.Outcome.NOT_FOUND, lowerPos, higherPos);
   }

   boolean hasKeyPos() {
      if (this.outcome == SearchResult.Outcome.FOUND) {
         assert this.lessOrEqualPos != -1;

         return true;
      } else {
         return false;
      }
   }

   int getKeyPos() {
      if (this.outcome == SearchResult.Outcome.FOUND) {
         return this.lessOrEqualPos;
      } else {
         throw new IllegalAccessError("Only results with outcome FOUND have this field!");
      }
   }

   boolean hasNextSmallerPos() {
      return this.outcome == SearchResult.Outcome.NOT_FOUND && this.lessOrEqualPos != -1;
   }

   int getNextSmallerPos() {
      if (this.outcome == SearchResult.Outcome.NOT_FOUND) {
         return this.lessOrEqualPos;
      } else {
         throw new IllegalAccessError("Only results with outcome NOT_FOUND have this field!");
      }
   }

   boolean hasNextLargerPos() {
      return this.outcome == SearchResult.Outcome.NOT_FOUND && this.greaterPos != -1;
   }

   int getNextLargerPos() {
      if (this.outcome == SearchResult.Outcome.NOT_FOUND) {
         return this.greaterPos;
      } else {
         throw new IllegalAccessError("Only results with outcome NOT_FOUND have this field!");
      }
   }

   static enum Outcome {
      FOUND,
      NOT_FOUND;

      // $FF: synthetic method
      private static Outcome[] $values() {
         return new Outcome[]{FOUND, NOT_FOUND};
      }
   }
}
