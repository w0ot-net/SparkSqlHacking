package org.codehaus.commons.compiler;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.codehaus.commons.nullanalysis.Nullable;

public interface IExpressionEvaluator extends ICookable, IMultiCookable {
   String DEFAULT_CLASS_NAME = "SC";
   Class DEFAULT_EXPRESSION_TYPE = Object.class;
   /** @deprecated */
   @Deprecated
   Class ANY_TYPE = Object.class;

   void setParentClassLoader(@Nullable ClassLoader var1);

   void setDebuggingInformation(boolean var1, boolean var2, boolean var3);

   void setCompileErrorHandler(@Nullable ErrorHandler var1);

   void setWarningHandler(@Nullable WarningHandler var1);

   @Nullable
   Object evaluate(@Nullable Object... var1) throws InvocationTargetException;

   void setDefaultExpressionType(Class var1);

   Class getDefaultExpressionType();

   void setImplementedInterfaces(Class[] var1);

   /** @deprecated */
   @Deprecated
   void setReturnType(@Deprecated Class var1);

   void setExpressionType(Class var1);

   void setExpressionTypes(Class[] var1);

   void setOverrideMethod(boolean var1);

   void setOverrideMethod(boolean[] var1);

   void setParameters(String[] var1, Class[] var2);

   void setParameters(String[][] var1, Class[][] var2);

   void setClassName(String var1);

   void setExtendedClass(Class var1);

   void setDefaultImports(String... var1);

   String[] getDefaultImports();

   void setStaticMethod(boolean var1);

   void setStaticMethod(boolean[] var1);

   void setMethodName(String var1);

   void setMethodNames(String[] var1);

   void setThrownExceptions(Class[] var1);

   void setThrownExceptions(Class[][] var1);

   @Nullable
   Object evaluate(int var1, @Nullable Object... var2) throws InvocationTargetException;

   Object createFastEvaluator(String var1, Class var2, String... var3) throws CompileException;

   Object createFastEvaluator(Reader var1, Class var2, String... var3) throws CompileException, IOException;

   Method getMethod();

   Method getMethod(int var1);

   Class getClazz();

   Method[] getResult();
}
