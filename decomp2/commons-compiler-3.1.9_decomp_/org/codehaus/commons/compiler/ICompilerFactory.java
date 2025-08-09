package org.codehaus.commons.compiler;

public interface ICompilerFactory {
   String getId();

   String toString();

   String getImplementationVersion();

   IExpressionEvaluator newExpressionEvaluator();

   IScriptEvaluator newScriptEvaluator();

   IClassBodyEvaluator newClassBodyEvaluator();

   ISimpleCompiler newSimpleCompiler();

   ICompiler newCompiler();

   AbstractJavaSourceClassLoader newJavaSourceClassLoader();

   AbstractJavaSourceClassLoader newJavaSourceClassLoader(ClassLoader var1);
}
