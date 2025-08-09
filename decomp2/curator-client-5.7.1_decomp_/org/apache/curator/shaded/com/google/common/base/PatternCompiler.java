package org.apache.curator.shaded.com.google.common.base;

import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
interface PatternCompiler {
   CommonPattern compile(String pattern);

   boolean isPcreLike();
}
