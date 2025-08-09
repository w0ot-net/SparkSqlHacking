package org.apache.commons.text.similarity;

import java.util.function.BiFunction;

public interface ObjectSimilarityScore extends BiFunction {
   Object apply(Object var1, Object var2);
}
