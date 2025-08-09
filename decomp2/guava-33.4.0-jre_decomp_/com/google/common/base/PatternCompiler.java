package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.RestrictedApi;

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
