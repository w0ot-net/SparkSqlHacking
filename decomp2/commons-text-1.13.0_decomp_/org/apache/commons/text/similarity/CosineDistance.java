package org.apache.commons.text.similarity;

import java.util.Map;

public class CosineDistance implements EditDistance {
   public Double apply(CharSequence left, CharSequence right) {
      CharSequence[] leftTokens = RegexTokenizer.INSTANCE.apply(left);
      CharSequence[] rightTokens = RegexTokenizer.INSTANCE.apply(right);
      Map<CharSequence, Integer> leftVector = Counter.of(leftTokens);
      Map<CharSequence, Integer> rightVector = Counter.of(rightTokens);
      double similarity = CosineSimilarity.INSTANCE.cosineSimilarity(leftVector, rightVector);
      return (double)1.0F - similarity;
   }
}
