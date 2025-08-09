package org.apache.commons.text.similarity;

public interface SimilarityScore extends ObjectSimilarityScore {
   Object apply(CharSequence var1, CharSequence var2);
}
