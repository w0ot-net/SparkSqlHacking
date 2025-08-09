package org.codehaus.commons.compiler;

import org.codehaus.commons.nullanalysis.Nullable;

public interface WarningHandler {
   void handleWarning(@Nullable String var1, String var2, @Nullable Location var3) throws CompileException;
}
