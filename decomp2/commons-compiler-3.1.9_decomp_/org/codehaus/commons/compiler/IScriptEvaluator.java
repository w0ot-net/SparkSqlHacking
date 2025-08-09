package org.codehaus.commons.compiler;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.codehaus.commons.nullanalysis.Nullable;

public interface IScriptEvaluator extends ICookable, IMultiCookable {
   String DEFAULT_METHOD_NAME = "eval*";
   Class DEFAULT_RETURN_TYPE = Void.TYPE;

   void setParentClassLoader(@Nullable ClassLoader var1);

   void setDebuggingInformation(boolean var1, boolean var2, boolean var3);

   void setCompileErrorHandler(@Nullable ErrorHandler var1);

   void setWarningHandler(@Nullable WarningHandler var1);

   void setClassName(String var1);

   void setImplementedInterfaces(Class[] var1);

   void setExtendedClass(Class var1);

   void setDefaultReturnType(Class var1);

   Class getDefaultReturnType();

   void setOverrideMethod(boolean var1);

   void setStaticMethod(boolean var1);

   void setReturnType(Class var1);

   void setMethodName(@Nullable String var1);

   void setParameters(String[] var1, Class[] var2);

   void setThrownExceptions(Class[] var1);

   @Nullable
   Object evaluate(@Nullable Object... var1) throws InvocationTargetException;

   Method getMethod();

   void setOverrideMethod(boolean[] var1);

   void setStaticMethod(boolean[] var1);

   void setReturnTypes(Class[] var1);

   void setMethodNames(String[] var1);

   void setParameters(String[][] var1, Class[][] var2);

   void setThrownExceptions(Class[][] var1);

   void cook(Reader... var1) throws CompileException, IOException;

   void cook(String[] var1, Reader[] var2) throws CompileException, IOException;

   void cook(String[] var1) throws CompileException;

   void cook(String[] var1, String[] var2) throws CompileException;

   @Nullable
   Object evaluate(int var1, @Nullable Object[] var2) throws InvocationTargetException;

   Method getMethod(int var1);

   Object createFastEvaluator(String var1, Class var2, String[] var3) throws CompileException;

   Object createFastEvaluator(Reader var1, Class var2, String[] var3) throws CompileException, IOException;

   void setDefaultImports(String... var1);

   String[] getDefaultImports();

   Class getClazz();

   Method[] getResult();
}
