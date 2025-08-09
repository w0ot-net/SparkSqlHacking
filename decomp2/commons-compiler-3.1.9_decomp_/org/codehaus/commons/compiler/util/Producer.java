package org.codehaus.commons.compiler.util;

import org.codehaus.commons.nullanalysis.Nullable;

public interface Producer {
   @Nullable
   Object produce();
}
