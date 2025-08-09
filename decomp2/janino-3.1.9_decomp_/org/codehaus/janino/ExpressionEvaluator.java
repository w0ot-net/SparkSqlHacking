package org.codehaus.janino;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.codehaus.commons.compiler.CompileException;
import org.codehaus.commons.compiler.ErrorHandler;
import org.codehaus.commons.compiler.IExpressionEvaluator;
import org.codehaus.commons.compiler.InternalCompilerException;
import org.codehaus.commons.compiler.Location;
import org.codehaus.commons.compiler.MultiCookable;
import org.codehaus.commons.compiler.WarningHandler;
import org.codehaus.commons.nullanalysis.Nullable;
import org.codehaus.janino.util.AbstractTraverser;

public class ExpressionEvaluator extends MultiCookable implements IExpressionEvaluator {
   private int sourceVersion = -1;
   @Nullable
   private WarningHandler warningHandler;
   private final ScriptEvaluator se = new ScriptEvaluator();

   public ExpressionEvaluator(String expression, Class expressionType, String[] parameterNames, Class[] parameterTypes) throws CompileException {
      this.se.setClassName("SC");
      this.se.setDefaultReturnType(IExpressionEvaluator.DEFAULT_EXPRESSION_TYPE);
      this.setExpressionType(expressionType);
      this.setParameters(parameterNames, parameterTypes);
      this.cook((String)expression);
   }

   public ExpressionEvaluator(String expression, Class expressionType, String[] parameterNames, Class[] parameterTypes, Class[] thrownExceptions, @Nullable ClassLoader parentClassLoader) throws CompileException {
      this.se.setClassName("SC");
      this.se.setDefaultReturnType(IExpressionEvaluator.DEFAULT_EXPRESSION_TYPE);
      this.setExpressionType(expressionType);
      this.setParameters(parameterNames, parameterTypes);
      this.setThrownExceptions(thrownExceptions);
      this.setParentClassLoader(parentClassLoader);
      this.cook((String)expression);
   }

   public ExpressionEvaluator(String expression, Class expressionType, String[] parameterNames, Class[] parameterTypes, Class[] thrownExceptions, Class extendedClass, Class[] implementedTypes, @Nullable ClassLoader parentClassLoader) throws CompileException {
      this.se.setClassName("SC");
      this.se.setDefaultReturnType(IExpressionEvaluator.DEFAULT_EXPRESSION_TYPE);
      this.setExpressionType(expressionType);
      this.setParameters(parameterNames, parameterTypes);
      this.setThrownExceptions(thrownExceptions);
      this.setExtendedClass(extendedClass);
      this.setImplementedInterfaces(implementedTypes);
      this.setParentClassLoader(parentClassLoader);
      this.cook((String)expression);
   }

   public ExpressionEvaluator(Scanner scanner, String className, @Nullable Class extendedType, Class[] implementedTypes, boolean staticMethod, Class expressionType, String methodName, String[] parameterNames, Class[] parameterTypes, Class[] thrownExceptions, @Nullable ClassLoader parentClassLoader) throws CompileException, IOException {
      this.se.setClassName("SC");
      this.se.setDefaultReturnType(IExpressionEvaluator.DEFAULT_EXPRESSION_TYPE);
      this.setClassName(className);
      this.setExtendedClass(extendedType);
      this.setImplementedInterfaces(implementedTypes);
      this.setStaticMethod(staticMethod);
      this.setExpressionType(expressionType);
      this.setMethodName(methodName);
      this.setParameters(parameterNames, parameterTypes);
      this.setThrownExceptions(thrownExceptions);
      this.setParentClassLoader(parentClassLoader);
      this.cook(scanner);
   }

   public ExpressionEvaluator() {
      this.se.setClassName("SC");
      this.se.setDefaultReturnType(IExpressionEvaluator.DEFAULT_EXPRESSION_TYPE);
   }

   public void setParentClassLoader(@Nullable ClassLoader parentClassLoader) {
      this.se.setParentClassLoader(parentClassLoader);
   }

   public void setDebuggingInformation(boolean debugSource, boolean debugLines, boolean debugVars) {
      this.se.setDebuggingInformation(debugSource, debugLines, debugVars);
   }

   public void setSourceVersion(int version) {
      this.se.setSourceVersion(version);
      this.sourceVersion = version;
   }

   public void setTargetVersion(int version) {
      this.se.setTargetVersion(version);
   }

   public void setCompileErrorHandler(@Nullable ErrorHandler compileErrorHandler) {
      this.se.setCompileErrorHandler(compileErrorHandler);
   }

   public void setWarningHandler(@Nullable WarningHandler warningHandler) {
      this.se.setWarningHandler(warningHandler);
      this.warningHandler = warningHandler;
   }

   public void setDefaultImports(String... defaultImports) {
      this.se.setDefaultImports(defaultImports);
   }

   public String[] getDefaultImports() {
      return this.se.getDefaultImports();
   }

   public EnumSet options() {
      return this.se.options();
   }

   public ExpressionEvaluator options(EnumSet options) {
      this.se.options(options);
      return this;
   }

   public void setDefaultExpressionType(Class defaultExpressionType) {
      this.se.setDefaultReturnType(defaultExpressionType);
   }

   public Class getDefaultExpressionType() {
      return this.se.getDefaultReturnType();
   }

   public void setImplementedInterfaces(Class[] implementedTypes) {
      this.se.setImplementedInterfaces(implementedTypes);
   }

   public void setReturnType(Class returnType) {
      this.se.setReturnType(returnType);
   }

   public void setExpressionType(Class expressionType) {
      this.se.setReturnType(expressionType);
   }

   public void setExpressionTypes(Class[] expressionTypes) {
      this.se.setReturnTypes(expressionTypes);
   }

   public void setOverrideMethod(boolean overrideMethod) {
      this.se.setOverrideMethod(overrideMethod);
   }

   public void setOverrideMethod(boolean[] overrideMethod) {
      this.se.setOverrideMethod(overrideMethod);
   }

   public void setParameters(String[] parameterNames, Class[] parameterTypes) {
      this.se.setParameters(parameterNames, parameterTypes);
   }

   public void setParameters(String[][] parameterNames, Class[][] parameterTypes) {
      this.se.setParameters(parameterNames, parameterTypes);
   }

   public void setClassName(String className) {
      this.se.setClassName(className);
   }

   public void setExtendedClass(@Nullable Class extendedType) {
      this.se.setExtendedClass(extendedType);
   }

   public void setStaticMethod(boolean staticMethod) {
      this.se.setStaticMethod(staticMethod);
   }

   public void setStaticMethod(boolean[] staticMethod) {
      this.se.setStaticMethod(staticMethod);
   }

   public void setMethodName(String methodName) {
      this.se.setMethodName(methodName);
   }

   public void setMethodNames(String[] methodNames) {
      this.se.setMethodNames(methodNames);
   }

   public void setThrownExceptions(Class[] thrownExceptions) {
      this.se.setThrownExceptions(thrownExceptions);
   }

   public void setThrownExceptions(Class[][] thrownExceptions) {
      this.se.setThrownExceptions(thrownExceptions);
   }

   public Method getMethod() {
      return this.se.getMethod();
   }

   public Method getMethod(int idx) {
      return this.se.getMethod(idx);
   }

   public Method[] getResult() {
      return this.se.getResult();
   }

   public void cook(@Nullable String fileName, Reader reader) throws CompileException, IOException {
      this.cook(new Scanner(fileName, reader));
   }

   public void cook(String[] fileNames, Reader[] readers) throws CompileException, IOException {
      int count = fileNames.length;
      Scanner[] scanners = new Scanner[count];

      for(int i = 0; i < count; ++i) {
         scanners[i] = new Scanner(fileNames[i], readers[i]);
      }

      this.cook(scanners);
   }

   public final void cook(Scanner scanner) throws CompileException, IOException {
      this.cook(scanner);
   }

   public final void cook(Scanner... scanners) throws CompileException, IOException {
      Parser[] parsers = new Parser[scanners.length];

      for(int i = 0; i < scanners.length; ++i) {
         parsers[i] = new Parser(scanners[i]);
         parsers[i].setSourceVersion(this.sourceVersion);
         parsers[i].setWarningHandler(this.warningHandler);
      }

      this.cook(parsers);
   }

   public final void cook(Parser... parsers) throws CompileException, IOException {
      int count = parsers.length;
      this.se.setScriptCount(count);
      String fileName = parsers.length >= 1 ? parsers[0].getScanner().getFileName() : null;
      Java.AbstractCompilationUnit.ImportDeclaration[] importDeclarations = this.se.parseImports(parsers.length == 1 ? parsers[0] : null);
      Java.BlockStatement[][] statementss = new Java.BlockStatement[count][];
      Java.MethodDeclarator[][] localMethodss = new Java.MethodDeclarator[count][];

      for(int i = 0; i < parsers.length; ++i) {
         Class<?> et = this.se.getReturnType(i);
         Parser parser = parsers[i];
         Java.Rvalue value = parser.parseExpression();
         Java.BlockStatement statement;
         if (et == Void.TYPE) {
            statement = new Java.ExpressionStatement(value);
         } else {
            statement = new Java.ReturnStatement(parser.location(), value);
         }

         if (!parser.peek(TokenType.END_OF_INPUT)) {
            throw new CompileException("Unexpected token \"" + parser.peek() + "\"", parser.location());
         }

         statementss[i] = new Java.BlockStatement[]{statement};
         localMethodss[i] = new Java.MethodDeclarator[0];
      }

      this.se.cook(fileName, importDeclarations, statementss, localMethodss);
   }

   protected Java.Type[] classesToTypes(Location location, @Nullable Class... classes) {
      if (classes == null) {
         return new Java.Type[0];
      } else {
         Java.Type[] types = new Java.Type[classes.length];

         for(int i = 0; i < classes.length; ++i) {
            types[i] = this.classToType(location, classes[i]);
         }

         return types;
      }
   }

   @Nullable
   protected Java.Type optionalClassToType(Location location, @Nullable Class clazz) {
      return this.se.optionalClassToType(location, clazz);
   }

   protected Java.Type classToType(Location location, Class clazz) {
      return this.se.classToType(location, clazz);
   }

   @Nullable
   public Object evaluate(@Nullable Object... arguments) throws InvocationTargetException {
      return this.evaluate(0, arguments);
   }

   @Nullable
   public Object evaluate(int idx, @Nullable Object... arguments) throws InvocationTargetException {
      Method method = this.getMethod(idx);

      try {
         return method.invoke((Object)null, arguments);
      } catch (IllegalAccessException ex) {
         throw new InternalCompilerException(ex.toString(), ex);
      }
   }

   public Class getClazz() {
      return this.se.getClazz();
   }

   public Map getBytecodes() {
      return this.se.getBytecodes();
   }

   /** @deprecated */
   @Deprecated
   public static Object createFastExpressionEvaluator(String expression, Class interfaceToImplement, String[] parameterNames, @Nullable ClassLoader parentClassLoader) throws CompileException {
      try {
         return createFastExpressionEvaluator(new Scanner((String)null, new StringReader(expression)), "SC", (Class)null, interfaceToImplement, parameterNames, (ClassLoader)null);
      } catch (IOException ioe) {
         AssertionError ae = new AssertionError("IOException despite StringReader");
         ae.initCause(ioe);
         throw ae;
      }
   }

   /** @deprecated */
   @Deprecated
   public static Object createFastExpressionEvaluator(Scanner scanner, String className, @Nullable Class extendedType, Class interfaceToImplement, String[] parameterNames, @Nullable ClassLoader parentClassLoader) throws CompileException, IOException {
      return createFastExpressionEvaluator(scanner, new String[0], className, extendedType, interfaceToImplement, parameterNames, parentClassLoader);
   }

   /** @deprecated */
   @Deprecated
   public static Object createFastExpressionEvaluator(Scanner scanner, String[] defaultImports, String className, @Nullable Class extendedType, Class interfaceToImplement, String[] parameterNames, @Nullable ClassLoader parentClassLoader) throws CompileException, IOException {
      ExpressionEvaluator ee = new ExpressionEvaluator();
      ee.setClassName(className);
      ee.setExtendedClass(extendedType);
      ee.setDefaultImports(defaultImports);
      ee.setParentClassLoader(parentClassLoader);
      return ee.createFastEvaluator(scanner, interfaceToImplement, parameterNames);
   }

   public Object createFastEvaluator(Reader reader, Class interfaceToImplement, String... parameterNames) throws CompileException, IOException {
      return this.createFastEvaluator(new Scanner((String)null, reader), interfaceToImplement, parameterNames);
   }

   public Object createFastEvaluator(String script, Class interfaceToImplement, String... parameterNames) throws CompileException {
      try {
         return this.createFastEvaluator((Reader)(new StringReader(script)), interfaceToImplement, parameterNames);
      } catch (IOException ex) {
         throw new InternalCompilerException("IOException despite StringReader", ex);
      }
   }

   public Object createFastEvaluator(Scanner scanner, Class interfaceToImplement, String... parameterNames) throws CompileException, IOException {
      if (!interfaceToImplement.isInterface()) {
         throw new InternalCompilerException("\"" + interfaceToImplement + "\" is not an interface");
      } else {
         Method[] methods = interfaceToImplement.getDeclaredMethods();
         if (methods.length != 1) {
            throw new InternalCompilerException("Interface \"" + interfaceToImplement + "\" must declare exactly one method");
         } else {
            Method methodToImplement = methods[0];
            this.setImplementedInterfaces(new Class[]{interfaceToImplement});
            this.setOverrideMethod(true);
            this.setStaticMethod(false);
            if (this instanceof IExpressionEvaluator) {
               this.setExpressionType(methodToImplement.getReturnType());
            } else {
               this.setExpressionType(methodToImplement.getReturnType());
            }

            this.setMethodName(methodToImplement.getName());
            this.setParameters(parameterNames, methodToImplement.getParameterTypes());
            this.setThrownExceptions(methodToImplement.getExceptionTypes());
            this.cook(scanner);
            Class<? extends T> actualClass = this.getMethod().getDeclaringClass();

            try {
               return actualClass.newInstance();
            } catch (InstantiationException e) {
               throw new InternalCompilerException(e.toString(), e);
            } catch (IllegalAccessException e) {
               throw new InternalCompilerException(e.toString(), e);
            }
         }
      }
   }

   public static String[] guessParameterNames(Scanner scanner) throws CompileException, IOException {
      Parser parser = new Parser(scanner);

      while(parser.peek("import")) {
         parser.parseImportDeclaration();
      }

      Java.Rvalue rvalue = parser.parseExpression();
      if (!parser.peek(TokenType.END_OF_INPUT)) {
         throw new CompileException("Unexpected token \"" + parser.peek() + "\"", scanner.location());
      } else {
         final Set<String> parameterNames = new HashSet();
         (new AbstractTraverser() {
            public void traverseAmbiguousName(Java.AmbiguousName an) {
               for(String identifier : an.identifiers) {
                  if (Character.isUpperCase(identifier.charAt(0))) {
                     return;
                  }
               }

               parameterNames.add(an.identifiers[0]);
            }
         }).visitAtom(rvalue);
         return (String[])parameterNames.toArray(new String[parameterNames.size()]);
      }
   }
}
