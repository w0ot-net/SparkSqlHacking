package org.sparkproject.guava.base;

import com.google.errorprone.annotations.RestrictedApi;
import org.sparkproject.guava.annotations.GwtIncompatible;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
interface PatternCompiler {
   @RestrictedApi(
      explanation = "PatternCompiler is an implementation detail of com.google.common.base",
      allowedOnPath = ".*/com/google/common/base/.*"
   )
   CommonPattern compile(String pattern);

   @RestrictedApi(
      explanation = "PatternCompiler is an implementation detail of com.google.common.base",
      allowedOnPath = ".*/com/google/common/base/.*"
   )
   boolean isPcreLike();
}
