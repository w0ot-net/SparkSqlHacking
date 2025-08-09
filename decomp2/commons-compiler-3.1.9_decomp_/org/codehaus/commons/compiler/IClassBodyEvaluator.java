package org.codehaus.commons.compiler;

import java.io.IOException;
import java.io.Reader;
import org.codehaus.commons.nullanalysis.Nullable;

public interface IClassBodyEvaluator extends ICookable {
   String DEFAULT_CLASS_NAME = "SC";

   void setParentClassLoader(@Nullable ClassLoader var1);

   void setDebuggingInformation(boolean var1, boolean var2, boolean var3);

   void setCompileErrorHandler(@Nullable ErrorHandler var1);

   void setWarningHandler(@Nullable WarningHandler var1);

   void setDefaultImports(String... var1);

   String[] getDefaultImports();

   void setClassName(String var1);

   void setExtendedClass(@Nullable Class var1);

   /** @deprecated */
   @Deprecated
   void setExtendedType(@Nullable Class var1);

   void setImplementedInterfaces(Class[] var1);

   /** @deprecated */
   @Deprecated
   void setImplementedTypes(Class[] var1);

   Class getClazz();

   Object createInstance(Reader var1) throws CompileException, IOException;
}
