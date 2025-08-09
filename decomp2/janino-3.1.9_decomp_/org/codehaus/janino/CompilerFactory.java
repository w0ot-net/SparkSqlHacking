package org.codehaus.janino;

import java.security.AccessController;
import java.security.PrivilegedAction;
import org.codehaus.commons.compiler.AbstractCompilerFactory;
import org.codehaus.commons.compiler.AbstractJavaSourceClassLoader;
import org.codehaus.commons.compiler.IClassBodyEvaluator;
import org.codehaus.commons.compiler.ICompiler;
import org.codehaus.commons.compiler.IExpressionEvaluator;
import org.codehaus.commons.compiler.IScriptEvaluator;
import org.codehaus.commons.compiler.ISimpleCompiler;

public class CompilerFactory extends AbstractCompilerFactory {
   public String getId() {
      return "org.codehaus.janino";
   }

   public String toString() {
      return "janino";
   }

   public String getImplementationVersion() {
      return CompilerFactory.class.getPackage().getImplementationVersion();
   }

   public IExpressionEvaluator newExpressionEvaluator() {
      return new ExpressionEvaluator();
   }

   public IScriptEvaluator newScriptEvaluator() {
      return new ScriptEvaluator();
   }

   public IClassBodyEvaluator newClassBodyEvaluator() {
      return new ClassBodyEvaluator();
   }

   public ISimpleCompiler newSimpleCompiler() {
      return new SimpleCompiler();
   }

   public ICompiler newCompiler() {
      return new Compiler();
   }

   public AbstractJavaSourceClassLoader newJavaSourceClassLoader() {
      return (AbstractJavaSourceClassLoader)AccessController.doPrivileged(new PrivilegedAction() {
         public AbstractJavaSourceClassLoader run() {
            return new JavaSourceClassLoader();
         }
      });
   }

   public AbstractJavaSourceClassLoader newJavaSourceClassLoader(final ClassLoader parentClassLoader) {
      return (AbstractJavaSourceClassLoader)AccessController.doPrivileged(new PrivilegedAction() {
         public AbstractJavaSourceClassLoader run() {
            return new JavaSourceClassLoader(parentClassLoader);
         }
      });
   }
}
