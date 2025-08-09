package org.codehaus.commons.compiler;

import org.codehaus.commons.nullanalysis.Nullable;

public interface ErrorHandler {
   void handleError(String var1, @Nullable Location var2) throws CompileException;
}
