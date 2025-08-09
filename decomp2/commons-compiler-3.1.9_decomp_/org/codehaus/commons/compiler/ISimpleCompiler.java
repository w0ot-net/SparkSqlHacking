package org.codehaus.commons.compiler;

import org.codehaus.commons.nullanalysis.Nullable;

public interface ISimpleCompiler extends ICookable {
   void setParentClassLoader(@Nullable ClassLoader var1);

   void setDebuggingInformation(boolean var1, boolean var2, boolean var3);

   void setCompileErrorHandler(@Nullable ErrorHandler var1);

   void setWarningHandler(@Nullable WarningHandler var1);

   ClassLoader getClassLoader();
}
