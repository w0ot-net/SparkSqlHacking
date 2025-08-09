package org.codehaus.janino;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.codehaus.commons.compiler.CompileException;
import org.codehaus.commons.compiler.ErrorHandler;
import org.codehaus.commons.compiler.IScriptEvaluator;
import org.codehaus.commons.compiler.InternalCompilerException;
import org.codehaus.commons.compiler.Location;
import org.codehaus.commons.compiler.MultiCookable;
import org.codehaus.commons.compiler.WarningHandler;
import org.codehaus.commons.nullanalysis.Nullable;
import org.codehaus.janino.util.AbstractTraverser;

public class ScriptEvaluator extends MultiCookable implements IScriptEvaluator {
   private int sourceVersion = -1;
   @Nullable
   private WarningHandler warningHandler;
   private final ClassBodyEvaluator cbe = new ClassBodyEvaluator();
   @Nullable
   private Script[] scripts;
   private Class defaultReturnType;
   @Nullable
   private Method[] getMethodsCache;

   public void setParentClassLoader(@Nullable ClassLoader parentClassLoader) {
      this.cbe.setParentClassLoader(parentClassLoader);
   }

   public void setDebuggingInformation(boolean debugSource, boolean debugLines, boolean debugVars) {
      this.cbe.setDebuggingInformation(debugSource, debugLines, debugVars);
   }

   public void setSourceVersion(int version) {
      this.cbe.setSourceVersion(version);
      this.sourceVersion = version;
   }

   public void setTargetVersion(int version) {
      this.cbe.setTargetVersion(version);
   }

   public void setCompileErrorHandler(@Nullable ErrorHandler compileErrorHandler) {
      this.cbe.setCompileErrorHandler(compileErrorHandler);
   }

   public void setWarningHandler(@Nullable WarningHandler warningHandler) {
      this.cbe.setWarningHandler(warningHandler);
      this.warningHandler = warningHandler;
   }

   public EnumSet options() {
      return this.cbe.options();
   }

   public ScriptEvaluator options(EnumSet options) {
      this.cbe.options(options);
      return this;
   }

   public void setScriptCount(int count) {
      Script[] ss = this.scripts;
      if (ss == null) {
         this.scripts = ss = new Script[count];

         for(int i = 0; i < count; ++i) {
            ss[i] = new Script("eval*".replace("*", Integer.toString(i)));
         }
      } else if (count != ss.length) {
         throw new IllegalArgumentException("Inconsistent script count; previously " + ss.length + ", now " + count);
      }

   }

   private Script getScript(int index) {
      if (this.scripts != null) {
         return this.scripts[index];
      } else {
         throw new IllegalStateException("\"getScript()\" invoked before \"setScriptCount()\"");
      }
   }

   public ScriptEvaluator(String script) throws CompileException {
      this.defaultReturnType = IScriptEvaluator.DEFAULT_RETURN_TYPE;
      this.cook((String)script);
   }

   public ScriptEvaluator(String script, Class returnType) throws CompileException {
      this.defaultReturnType = IScriptEvaluator.DEFAULT_RETURN_TYPE;
      this.setReturnType(returnType);
      this.cook((String)script);
   }

   public ScriptEvaluator(String script, Class returnType, String[] parameterNames, Class[] parameterTypes) throws CompileException {
      this.defaultReturnType = IScriptEvaluator.DEFAULT_RETURN_TYPE;
      this.setReturnType(returnType);
      this.setParameters(parameterNames, parameterTypes);
      this.cook((String)script);
   }

   public ScriptEvaluator(String script, Class returnType, String[] parameterNames, Class[] parameterTypes, Class[] thrownExceptions) throws CompileException {
      this.defaultReturnType = IScriptEvaluator.DEFAULT_RETURN_TYPE;
      this.setReturnType(returnType);
      this.setParameters(parameterNames, parameterTypes);
      this.setThrownExceptions(thrownExceptions);
      this.cook((String)script);
   }

   public ScriptEvaluator(@Nullable String fileName, InputStream is, Class returnType, String[] parameterNames, Class[] parameterTypes, Class[] thrownExceptions, @Nullable ClassLoader parentClassLoader) throws CompileException, IOException {
      this.defaultReturnType = IScriptEvaluator.DEFAULT_RETURN_TYPE;
      this.setReturnType(returnType);
      this.setParameters(parameterNames, parameterTypes);
      this.setThrownExceptions(thrownExceptions);
      this.setParentClassLoader(parentClassLoader);
      this.cook((String)fileName, (InputStream)is);
   }

   public ScriptEvaluator(@Nullable String fileName, Reader reader, Class returnType, String[] parameterNames, Class[] parameterTypes, Class[] thrownExceptions, @Nullable ClassLoader parentClassLoader) throws CompileException, IOException {
      this.defaultReturnType = IScriptEvaluator.DEFAULT_RETURN_TYPE;
      this.setReturnType(returnType);
      this.setParameters(parameterNames, parameterTypes);
      this.setThrownExceptions(thrownExceptions);
      this.setParentClassLoader(parentClassLoader);
      this.cook(fileName, reader);
   }

   public ScriptEvaluator(Scanner scanner, Class returnType, String[] parameterNames, Class[] parameterTypes, Class[] thrownExceptions, @Nullable ClassLoader parentClassLoader) throws CompileException, IOException {
      this.defaultReturnType = IScriptEvaluator.DEFAULT_RETURN_TYPE;
      this.setReturnType(returnType);
      this.setParameters(parameterNames, parameterTypes);
      this.setThrownExceptions(thrownExceptions);
      this.setParentClassLoader(parentClassLoader);
      this.cook(scanner);
   }

   public ScriptEvaluator(Scanner scanner, @Nullable Class extendedType, Class[] implementedTypes, Class returnType, String[] parameterNames, Class[] parameterTypes, Class[] thrownExceptions, @Nullable ClassLoader parentClassLoader) throws CompileException, IOException {
      this.defaultReturnType = IScriptEvaluator.DEFAULT_RETURN_TYPE;
      this.setExtendedClass(extendedType);
      this.setImplementedInterfaces(implementedTypes);
      this.setReturnType(returnType);
      this.setParameters(parameterNames, parameterTypes);
      this.setThrownExceptions(thrownExceptions);
      this.setParentClassLoader(parentClassLoader);
      this.cook(scanner);
   }

   public ScriptEvaluator(Scanner scanner, String className, @Nullable Class extendedType, Class[] implementedTypes, boolean staticMethod, Class returnType, String methodName, String[] parameterNames, Class[] parameterTypes, Class[] thrownExceptions, @Nullable ClassLoader parentClassLoader) throws CompileException, IOException {
      this.defaultReturnType = IScriptEvaluator.DEFAULT_RETURN_TYPE;
      this.setClassName(className);
      this.setExtendedClass(extendedType);
      this.setImplementedInterfaces(implementedTypes);
      this.setStaticMethod(staticMethod);
      this.setReturnType(returnType);
      this.setMethodName(methodName);
      this.setParameters(parameterNames, parameterTypes);
      this.setThrownExceptions(thrownExceptions);
      this.setParentClassLoader(parentClassLoader);
      this.cook(scanner);
   }

   public ScriptEvaluator() {
      this.defaultReturnType = IScriptEvaluator.DEFAULT_RETURN_TYPE;
   }

   public ScriptEvaluator(int count) {
      this.defaultReturnType = IScriptEvaluator.DEFAULT_RETURN_TYPE;
      this.setScriptCount(count);
   }

   public void setClassName(String className) {
      this.cbe.setClassName(className);
   }

   public void setImplementedInterfaces(Class[] implementedTypes) {
      this.cbe.setImplementedInterfaces(implementedTypes);
   }

   public void setExtendedClass(@Nullable Class extendedType) {
      this.cbe.setExtendedClass(extendedType);
   }

   public void setDefaultReturnType(Class defaultReturnType) {
      this.defaultReturnType = defaultReturnType;
   }

   public Class getDefaultReturnType() {
      return this.defaultReturnType;
   }

   public void setOverrideMethod(boolean overrideMethod) {
      this.setOverrideMethod(new boolean[]{overrideMethod});
   }

   public void setStaticMethod(boolean staticMethod) {
      this.setStaticMethod(new boolean[]{staticMethod});
   }

   public void setReturnType(@Nullable Class returnType) {
      this.setReturnTypes(new Class[]{returnType});
   }

   public void setMethodName(@Nullable String methodName) {
      this.setMethodNames(new String[]{methodName});
   }

   public void setParameters(String[] parameterNames, Class[] parameterTypes) {
      this.setParameters(new String[][]{parameterNames}, new Class[][]{parameterTypes});
   }

   public void setThrownExceptions(Class[] thrownExceptions) {
      this.setThrownExceptions(new Class[][]{thrownExceptions});
   }

   public void setOverrideMethod(boolean[] overrideMethod) {
      this.setScriptCount(overrideMethod.length);

      for(int i = 0; i < overrideMethod.length; ++i) {
         this.getScript(i).overrideMethod = overrideMethod[i];
      }

   }

   public void setStaticMethod(boolean[] staticMethod) {
      this.setScriptCount(staticMethod.length);

      for(int i = 0; i < staticMethod.length; ++i) {
         this.getScript(i).staticMethod = staticMethod[i];
      }

   }

   public void setReturnTypes(Class[] returnTypes) {
      this.setScriptCount(returnTypes.length);

      for(int i = 0; i < returnTypes.length; ++i) {
         this.getScript(i).returnType = returnTypes[i];
      }

   }

   public void setMethodNames(String[] methodNames) {
      this.setScriptCount(methodNames.length);

      for(int i = 0; i < methodNames.length; ++i) {
         this.getScript(i).methodName = methodNames[i];
      }

   }

   public void setParameters(String[][] parameterNames, Class[][] parameterTypes) {
      this.setScriptCount(parameterNames.length);
      this.setScriptCount(parameterTypes.length);

      for(int i = 0; i < parameterNames.length; ++i) {
         Script script = this.getScript(i);
         script.parameterNames = (String[])parameterNames[i].clone();
         script.parameterTypes = (Class[])parameterTypes[i].clone();
      }

   }

   public void setThrownExceptions(Class[][] thrownExceptions) {
      this.setScriptCount(thrownExceptions.length);

      for(int i = 0; i < thrownExceptions.length; ++i) {
         this.getScript(i).thrownExceptions = thrownExceptions[i];
      }

   }

   public void cook(@Nullable String fileName, Reader reader) throws CompileException, IOException {
      this.cook(new Scanner(fileName, reader));
   }

   public final void cook(String[] fileNames, Reader[] readers) throws CompileException, IOException {
      this.setScriptCount(fileNames.length);
      this.setScriptCount(readers.length);
      Scanner[] scanners = new Scanner[readers.length];

      for(int i = 0; i < readers.length; ++i) {
         scanners[i] = new Scanner(fileNames[i], readers[i]);
      }

      this.cook(scanners);
   }

   public final void cook(Scanner... scanners) throws CompileException, IOException {
      this.setScriptCount(scanners.length);
      Parser[] parsers = new Parser[scanners.length];

      for(int i = 0; i < scanners.length; ++i) {
         parsers[i] = new Parser(scanners[i]);
         parsers[i].setSourceVersion(this.sourceVersion);
         parsers[i].setWarningHandler(this.warningHandler);
      }

      this.cook(parsers);
   }

   public final void cook(Parser[] parsers) throws CompileException, IOException {
      int count = parsers.length;
      this.setScriptCount(count);
      Parser parser = count == 1 ? parsers[0] : null;
      Java.AbstractCompilationUnit.ImportDeclaration[] importDeclarations = this.parseImports(parser);
      Java.BlockStatement[][] statementss = new Java.BlockStatement[count][];
      Java.MethodDeclarator[][] localMethodss = new Java.MethodDeclarator[count][];

      for(int i = 0; i < count; ++i) {
         List<Java.BlockStatement> statements = new ArrayList();
         List<Java.MethodDeclarator> localMethods = new ArrayList();
         this.makeStatements(i, parsers[i], statements, localMethods);
         statementss[i] = (Java.BlockStatement[])statements.toArray(new Java.BlockStatement[statements.size()]);
         localMethodss[i] = (Java.MethodDeclarator[])localMethods.toArray(new Java.MethodDeclarator[localMethods.size()]);
      }

      this.cook(parsers.length >= 1 ? parsers[0].getScanner().getFileName() : null, importDeclarations, statementss, localMethodss);
   }

   void cook(@Nullable String fileName, Java.AbstractCompilationUnit.ImportDeclaration[] importDeclarations, Java.BlockStatement[][] statementss, Java.MethodDeclarator[][] localMethodss) throws CompileException {
      int count = statementss.length;
      Collection<Java.MethodDeclarator> methodDeclarators = new ArrayList();

      for(int i = 0; i < count; ++i) {
         Script es = this.getScript(i);
         Java.BlockStatement[] statements = statementss[i];
         Java.MethodDeclarator[] localMethods = localMethodss[i];
         Location loc = statements.length == 0 ? Location.NOWHERE : statements[0].getLocation();
         Class<?> rt = es.returnType;
         if (rt == null) {
            rt = this.getDefaultReturnType();
         }

         methodDeclarators.add(this.makeMethodDeclaration(loc, es.overrideMethod ? new Java.Annotation[]{new Java.MarkerAnnotation(this.classToType(loc, Override.class))} : new Java.Annotation[0], es.staticMethod, rt, es.methodName, es.parameterTypes, es.parameterNames, es.thrownExceptions, statements));

         for(Java.MethodDeclarator lm : localMethods) {
            methodDeclarators.add(lm);
         }
      }

      this.cook(new Java.CompilationUnit(fileName, importDeclarations), methodDeclarators);
   }

   public final void cook(Java.CompilationUnit compilationUnit, Collection methodDeclarators) throws CompileException {
      Java.AbstractClassDeclaration cd = this.cbe.addPackageMemberClassDeclaration(((Java.MethodDeclarator)methodDeclarators.iterator().next()).getLocation(), compilationUnit);

      for(Java.MethodDeclarator md : methodDeclarators) {
         cd.addDeclaredMethod(md);
      }

      this.cook(compilationUnit);
   }

   Java.AbstractCompilationUnit.ImportDeclaration[] parseImports(@Nullable Parser parser) throws CompileException, IOException {
      return this.cbe.makeImportDeclarations(parser);
   }

   public Method[] getResult() {
      return this.getMethods();
   }

   Method[] getMethods() {
      Method[] result = this.getMethodsCache;
      if (result != null) {
         return result;
      } else {
         Class<?> c = this.getClazz();

         assert this.scripts != null;

         int count = this.scripts.length;
         result = new Method[count];
         Map<Object, Integer> dms = new HashMap(2 * count);

         for(int i = 0; i < count; ++i) {
            Script es = this.getScript(i);
            Integer prev = (Integer)dms.put(methodKey(es.methodName, es.parameterTypes), i);

            assert prev == null;
         }

         for(Method m : c.getDeclaredMethods()) {
            Integer idx = (Integer)dms.get(methodKey(m.getName(), m.getParameterTypes()));
            if (idx != null) {
               assert result[idx] == null;

               result[idx] = m;
            }
         }

         for(int i = 0; i < count; ++i) {
            if (result[i] == null) {
               throw new InternalCompilerException("SNO: Generated class does not declare method \"" + this.getScript(i).methodName + "\" (index " + i + ")");
            }
         }

         return this.getMethodsCache = result;
      }
   }

   @Nullable
   protected Java.Type optionalClassToType(Location loc, @Nullable Class clazz) {
      return this.cbe.optionalClassToType(loc, clazz);
   }

   protected Java.Type classToType(Location loc, Class clazz) {
      return this.cbe.classToType(loc, clazz);
   }

   protected Java.Type[] classesToTypes(Location location, Class[] classes) {
      return this.cbe.classesToTypes(location, classes);
   }

   protected void cook(Java.CompilationUnit compilationUnit) throws CompileException {
      this.cbe.cook(compilationUnit);
   }

   private static Object methodKey(String methodName, Class[] parameterTypes) {
      return Arrays.asList(cat(methodName, parameterTypes, Object.class));
   }

   private static Object[] cat(Object firstElement, Object[] followingElements, Class componentType) {
      T[] result = (T[])((Object[])Array.newInstance(componentType, 1 + followingElements.length));
      result[0] = firstElement;
      System.arraycopy(followingElements, 0, result, 1, followingElements.length);
      return result;
   }

   @Nullable
   public Object evaluate(@Nullable Object... arguments) throws InvocationTargetException {
      return this.evaluate(0, arguments);
   }

   @Nullable
   public Object evaluate(int idx, @Nullable Object[] arguments) throws InvocationTargetException {
      Method method = this.getMethod(idx);

      try {
         return method.invoke((Object)null, arguments);
      } catch (IllegalAccessException ex) {
         throw new InternalCompilerException(ex.toString(), ex);
      }
   }

   public Method getMethod() {
      return this.getMethod(0);
   }

   public Method getMethod(int idx) {
      return this.getMethods()[idx];
   }

   public Class getClazz() {
      return this.cbe.getClazz();
   }

   public Map getBytecodes() {
      return this.cbe.getBytecodes();
   }

   @Nullable
   protected final Class getReturnType(int index) {
      return this.getScript(index).returnType;
   }

   protected void makeStatements(int idx, Parser parser, List resultStatements, List resultMethods) throws CompileException, IOException {
      while(!parser.peek(TokenType.END_OF_INPUT)) {
         parseScriptStatement(parser, resultStatements, resultMethods);
      }

   }

   private static void parseScriptStatement(Parser parser, List mainStatements, List localMethods) throws CompileException, IOException {
      if ((!parser.peek(TokenType.IDENTIFIER) || !parser.peekNextButOne(":")) && parser.peek("if", "for", "while", "do", "try", "switch", "synchronized", "return", "throw", "break", "continue", "assert", "{", ";") == -1) {
         if (parser.peekRead("class")) {
            Java.LocalClassDeclaration lcd = (Java.LocalClassDeclaration)parser.parseClassDeclarationRest((String)null, new Java.Modifier[0], Parser.ClassDeclarationContext.BLOCK);
            mainStatements.add(new Java.LocalClassDeclarationStatement(lcd));
         } else {
            Java.Modifier[] modifiers = parser.parseModifiers();
            if (parser.peekRead("void")) {
               String name = parser.read(TokenType.IDENTIFIER);
               localMethods.add(parser.parseMethodDeclarationRest((String)null, modifiers, (Java.TypeParameter[])null, new Java.PrimitiveType(parser.location(), Java.Primitive.VOID), name, false, Parser.MethodDeclarationContext.CLASS_DECLARATION));
            } else if (modifiers.length > 0) {
               Java.Type methodOrVariableType = parser.parseType();
               if (parser.peek(TokenType.IDENTIFIER) && parser.peekNextButOne("(")) {
                  localMethods.add(parser.parseMethodDeclarationRest((String)null, modifiers, (Java.TypeParameter[])null, methodOrVariableType, parser.read(TokenType.IDENTIFIER), false, Parser.MethodDeclarationContext.CLASS_DECLARATION));
               } else {
                  mainStatements.add(new Java.LocalVariableDeclarationStatement(parser.location(), modifiers, methodOrVariableType, parser.parseVariableDeclarators()));
                  parser.read(";");
               }
            } else {
               Java.Atom a = parser.parseExpressionOrType();
               if (parser.peekRead(";")) {
                  mainStatements.add(new Java.ExpressionStatement(a.toRvalueOrCompileException()));
               } else {
                  Java.Type methodOrVariableType = a.toTypeOrCompileException();
                  if (parser.peek(TokenType.IDENTIFIER) && parser.peekNextButOne("(")) {
                     localMethods.add(parser.parseMethodDeclarationRest((String)null, modifiers, (Java.TypeParameter[])null, methodOrVariableType, parser.read(TokenType.IDENTIFIER), false, Parser.MethodDeclarationContext.CLASS_DECLARATION));
                  } else {
                     mainStatements.add(new Java.LocalVariableDeclarationStatement(a.getLocation(), modifiers, methodOrVariableType, parser.parseVariableDeclarators()));
                     parser.read(";");
                  }
               }
            }
         }
      } else {
         mainStatements.add(parser.parseStatement());
      }
   }

   private Java.MethodDeclarator makeMethodDeclaration(Location location, Java.Annotation[] annotations, boolean staticMethod, Class returnType, String methodName, Class[] parameterTypes, String[] parameterNames, Class[] thrownExceptions, Java.BlockStatement[] statements) {
      if (parameterNames.length != parameterTypes.length) {
         throw new InternalCompilerException("Lengths of \"parameterNames\" (" + parameterNames.length + ") and \"parameterTypes\" (" + parameterTypes.length + ") do not match");
      } else {
         Java.FunctionDeclarator.FormalParameters fps = new Java.FunctionDeclarator.FormalParameters(location, new Java.FunctionDeclarator.FormalParameter[parameterNames.length], false);

         for(int i = 0; i < fps.parameters.length; ++i) {
            fps.parameters[i] = new Java.FunctionDeclarator.FormalParameter(location, Java.accessModifiers(location, "final"), this.classToType(location, parameterTypes[i]), parameterNames[i]);
         }

         List<Java.Modifier> l = new ArrayList();
         l.addAll(Arrays.asList(annotations));
         l.add(new Java.AccessModifier("public", location));
         if (staticMethod) {
            l.add(new Java.AccessModifier("static", location));
         }

         Java.Modifier[] modifiers = (Java.Modifier[])l.toArray(new Java.Modifier[l.size()]);
         return new Java.MethodDeclarator(location, (String)null, modifiers, (Java.TypeParameter[])null, this.classToType(location, returnType), methodName, fps, this.classesToTypes(location, thrownExceptions), (Java.ElementValue)null, Arrays.asList(statements));
      }
   }

   /** @deprecated */
   @Deprecated
   public static Object createFastScriptEvaluator(String script, Class interfaceToImplement, String[] parameterNames) throws CompileException {
      ScriptEvaluator se = new ScriptEvaluator();
      return se.createFastEvaluator(script, interfaceToImplement, parameterNames);
   }

   /** @deprecated */
   @Deprecated
   public static Object createFastScriptEvaluator(Scanner scanner, Class interfaceToImplement, String[] parameterNames, @Nullable ClassLoader parentClassLoader) throws CompileException, IOException {
      ScriptEvaluator se = new ScriptEvaluator();
      se.setParentClassLoader(parentClassLoader);
      return se.createFastEvaluator(scanner, interfaceToImplement, parameterNames);
   }

   /** @deprecated */
   @Deprecated
   public static Object createFastScriptEvaluator(Scanner scanner, String className, @Nullable Class extendedType, Class interfaceToImplement, String[] parameterNames, @Nullable ClassLoader parentClassLoader) throws CompileException, IOException {
      ScriptEvaluator se = new ScriptEvaluator();
      se.setClassName(className);
      se.setExtendedClass(extendedType);
      se.setParentClassLoader(parentClassLoader);
      return se.createFastEvaluator(scanner, interfaceToImplement, parameterNames);
   }

   /** @deprecated */
   @Deprecated
   public static Object createFastScriptEvaluator(Scanner scanner, String[] defaultImports, String className, @Nullable Class extendedClass, Class interfaceToImplement, String[] parameterNames, @Nullable ClassLoader parentClassLoader) throws CompileException, IOException {
      ScriptEvaluator se = new ScriptEvaluator();
      se.setDefaultImports(defaultImports);
      se.setClassName(className);
      se.setExtendedClass(extendedClass);
      se.setParentClassLoader(parentClassLoader);
      return se.createFastEvaluator(scanner, interfaceToImplement, parameterNames);
   }

   public void setDefaultImports(String... defaultImports) {
      this.cbe.setDefaultImports(defaultImports);
   }

   public String[] getDefaultImports() {
      return this.cbe.getDefaultImports();
   }

   public Object createFastEvaluator(Reader reader, Class interfaceToImplement, String[] parameterNames) throws CompileException, IOException {
      return this.createFastEvaluator(new Scanner((String)null, reader), interfaceToImplement, parameterNames);
   }

   public Object createFastEvaluator(String script, Class interfaceToImplement, String[] parameterNames) throws CompileException {
      try {
         return this.createFastEvaluator((Reader)(new StringReader(script)), interfaceToImplement, parameterNames);
      } catch (IOException ex) {
         throw new InternalCompilerException("IOException despite StringReader", ex);
      }
   }

   public Object createFastEvaluator(Scanner scanner, Class interfaceToImplement, String[] parameterNames) throws CompileException, IOException {
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
            this.setReturnType(methodToImplement.getReturnType());
            this.setMethodName(methodToImplement.getName());
            this.setParameters(parameterNames, methodToImplement.getParameterTypes());
            this.setThrownExceptions(methodToImplement.getExceptionTypes());
            this.cook(scanner);
            Class<?> c = this.getMethod().getDeclaringClass();

            try {
               T result = (T)c.newInstance();
               return result;
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

      Java.Block block = new Java.Block(scanner.location());

      while(!parser.peek(TokenType.END_OF_INPUT)) {
         block.addStatement(parser.parseBlockStatement());
      }

      final Set<String> localVariableNames = new HashSet();
      final Set<String> parameterNames = new HashSet();
      (new AbstractTraverser() {
         public void traverseLocalVariableDeclarationStatement(Java.LocalVariableDeclarationStatement lvds) {
            for(Java.VariableDeclarator vd : lvds.variableDeclarators) {
               localVariableNames.add(vd.name);
            }

            super.traverseLocalVariableDeclarationStatement(lvds);
         }

         public void traverseAmbiguousName(Java.AmbiguousName an) {
            for(int i = 0; i < an.identifiers.length; ++i) {
               if (Character.isUpperCase(an.identifiers[i].charAt(0))) {
                  return;
               }
            }

            if (!localVariableNames.contains(an.identifiers[0])) {
               parameterNames.add(an.identifiers[0]);
            }
         }
      }).visitBlockStatement(block);
      return (String[])parameterNames.toArray(new String[parameterNames.size()]);
   }

   class Script {
      protected boolean overrideMethod;
      protected boolean staticMethod = true;
      @Nullable
      protected Class returnType;
      private String methodName;
      private String[] parameterNames = new String[0];
      private Class[] parameterTypes = new Class[0];
      private Class[] thrownExceptions = new Class[0];

      Script(String methodName) {
         this.methodName = methodName;
      }
   }
}
