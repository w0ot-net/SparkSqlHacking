package org.codehaus.janino;

import [Ljava.lang.String;;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import org.codehaus.commons.compiler.CompileException;
import org.codehaus.commons.compiler.Cookable;
import org.codehaus.commons.compiler.ErrorHandler;
import org.codehaus.commons.compiler.IClassBodyEvaluator;
import org.codehaus.commons.compiler.InternalCompilerException;
import org.codehaus.commons.compiler.Location;
import org.codehaus.commons.compiler.WarningHandler;
import org.codehaus.commons.nullanalysis.Nullable;

public class ClassBodyEvaluator extends Cookable implements IClassBodyEvaluator {
   private static final Class[] ZERO_CLASSES = new Class[0];
   @Nullable
   private WarningHandler warningHandler;
   private final SimpleCompiler sc = new SimpleCompiler();
   private String[] defaultImports = new String[0];
   private int sourceVersion = -1;
   private String className = "SC";
   @Nullable
   private Class extendedType;
   private Class[] implementedTypes;
   @Nullable
   private Class result;

   public ClassBodyEvaluator(String classBody) throws CompileException {
      this.implementedTypes = ZERO_CLASSES;
      this.cook((String)classBody);
   }

   public ClassBodyEvaluator(@Nullable String fileName, InputStream is) throws CompileException, IOException {
      this.implementedTypes = ZERO_CLASSES;
      this.cook(fileName, is);
   }

   public ClassBodyEvaluator(@Nullable String fileName, Reader reader) throws CompileException, IOException {
      this.implementedTypes = ZERO_CLASSES;
      this.cook(fileName, reader);
   }

   public ClassBodyEvaluator(Scanner scanner, @Nullable ClassLoader parentClassLoader) throws CompileException, IOException {
      this.implementedTypes = ZERO_CLASSES;
      this.setParentClassLoader(parentClassLoader);
      this.cook(scanner);
   }

   public ClassBodyEvaluator(Scanner scanner, @Nullable Class extendedType, Class[] implementedTypes, @Nullable ClassLoader parentClassLoader) throws CompileException, IOException {
      this.implementedTypes = ZERO_CLASSES;
      this.setExtendedClass(extendedType);
      this.setImplementedInterfaces(implementedTypes);
      this.setParentClassLoader(parentClassLoader);
      this.cook(scanner);
   }

   public ClassBodyEvaluator(Scanner scanner, String className, @Nullable Class extendedType, Class[] implementedTypes, @Nullable ClassLoader parentClassLoader) throws CompileException, IOException {
      this.implementedTypes = ZERO_CLASSES;
      this.setClassName(className);
      this.setExtendedClass(extendedType);
      this.setImplementedInterfaces(implementedTypes);
      this.setParentClassLoader(parentClassLoader);
      this.cook(scanner);
   }

   public ClassBodyEvaluator() {
      this.implementedTypes = ZERO_CLASSES;
   }

   public void setDefaultImports(String... defaultImports) {
      this.defaultImports = (String[])((String;)defaultImports).clone();
   }

   public String[] getDefaultImports() {
      return (String[])this.defaultImports.clone();
   }

   public void setClassName(String className) {
      this.className = className;
   }

   public void setExtendedClass(@Nullable Class extendedType) {
      this.extendedType = extendedType;
   }

   public void setExtendedType(@Nullable Class extendedClass) {
      this.setExtendedClass(extendedClass);
   }

   public void setImplementedInterfaces(Class[] implementedTypes) {
      this.implementedTypes = implementedTypes;
   }

   public void setImplementedTypes(Class[] implementedInterfaces) {
      this.setImplementedInterfaces(implementedInterfaces);
   }

   public void setParentClassLoader(@Nullable ClassLoader parentClassLoader) {
      this.sc.setParentClassLoader(parentClassLoader);
   }

   public void setDebuggingInformation(boolean debugSource, boolean debugLines, boolean debugVars) {
      this.sc.setDebuggingInformation(debugSource, debugLines, debugVars);
   }

   public void setSourceVersion(int version) {
      this.sc.setSourceVersion(version);
      this.sourceVersion = version;
   }

   public void setTargetVersion(int version) {
      this.sc.setTargetVersion(version);
   }

   public void setCompileErrorHandler(@Nullable ErrorHandler compileErrorHandler) {
      this.sc.setCompileErrorHandler(compileErrorHandler);
   }

   public void setWarningHandler(@Nullable WarningHandler warningHandler) {
      this.sc.setWarningHandler(warningHandler);
      this.warningHandler = warningHandler;
   }

   public EnumSet options() {
      return this.sc.options();
   }

   public ClassBodyEvaluator options(EnumSet options) {
      this.sc.options(options);
      return this;
   }

   public final void cook(@Nullable String fileName, Reader r) throws CompileException, IOException {
      this.cook(new Scanner(fileName, r));
   }

   public void cook(Scanner scanner) throws CompileException, IOException {
      Parser parser = new Parser(scanner);
      parser.setSourceVersion(this.sourceVersion);
      Java.AbstractCompilationUnit.ImportDeclaration[] importDeclarations = this.makeImportDeclarations(parser);
      Java.CompilationUnit compilationUnit = new Java.CompilationUnit(scanner.getFileName(), importDeclarations);
      Java.AbstractClassDeclaration acd = this.addPackageMemberClassDeclaration(scanner.location(), compilationUnit);

      while(!parser.peek(TokenType.END_OF_INPUT)) {
         parser.parseClassBodyDeclaration(acd);
      }

      this.cook(compilationUnit);
   }

   void cook(Java.CompilationUnit compilationUnit) throws CompileException {
      this.sc.cook((Java.AbstractCompilationUnit)compilationUnit);

      Class<?> c;
      try {
         c = this.sc.getClassLoader().loadClass(this.className);
      } catch (ClassNotFoundException ex) {
         throw new InternalCompilerException("SNO: Generated compilation unit does not declare class '" + this.className + "'", ex);
      }

      this.result = c;
   }

   public Class getClazz() {
      return this.assertCooked();
   }

   public Map getBytecodes() {
      return this.sc.getBytecodes();
   }

   final Java.AbstractCompilationUnit.ImportDeclaration[] makeImportDeclarations(@Nullable Parser parser) throws CompileException, IOException {
      List<Java.AbstractCompilationUnit.ImportDeclaration> l = new ArrayList();

      for(String defaultImport : this.defaultImports) {
         Parser p = new Parser(new Scanner((String)null, new StringReader(defaultImport)));
         p.setSourceVersion(this.sourceVersion);
         p.setWarningHandler(this.warningHandler);
         l.add(p.parseImportDeclarationBody());
         p.read(TokenType.END_OF_INPUT);
      }

      if (parser != null) {
         while(parser.peek("import")) {
            l.add(parser.parseImportDeclaration());
         }
      }

      return (Java.AbstractCompilationUnit.ImportDeclaration[])l.toArray(new Java.AbstractCompilationUnit.ImportDeclaration[l.size()]);
   }

   protected Java.PackageMemberClassDeclaration addPackageMemberClassDeclaration(Location location, Java.CompilationUnit compilationUnit) {
      String cn = this.className;
      int idx = cn.lastIndexOf(46);
      if (idx != -1) {
         compilationUnit.setPackageDeclaration(new Java.PackageDeclaration(location, cn.substring(0, idx)));
         cn = cn.substring(idx + 1);
      }

      Java.PackageMemberClassDeclaration tlcd = new Java.PackageMemberClassDeclaration(location, (String)null, new Java.Modifier[]{new Java.AccessModifier("public", location)}, cn, (Java.TypeParameter[])null, this.optionalClassToType(location, this.extendedType), this.sc.classesToTypes(location, this.implementedTypes));
      compilationUnit.addPackageMemberTypeDeclaration(tlcd);
      return tlcd;
   }

   @Nullable
   protected Java.Type optionalClassToType(Location location, @Nullable Class clazz) {
      return this.sc.optionalClassToType(location, clazz);
   }

   protected Java.Type classToType(Location location, Class clazz) {
      return this.sc.classToType(location, clazz);
   }

   public Java.Type[] classesToTypes(Location location, Class[] classes) {
      return this.sc.classesToTypes(location, classes);
   }

   private Class assertCooked() {
      if (this.result != null) {
         return this.result;
      } else {
         throw new IllegalStateException("Must only be called after 'cook()'");
      }
   }

   public Object createInstance(Reader reader) throws CompileException, IOException {
      this.cook((Reader)reader);

      try {
         return this.getClazz().newInstance();
      } catch (InstantiationException ie) {
         CompileException ce = new CompileException("Class is abstract, an interface, an array class, a primitive type, or void; or has no zero-parameter constructor", (Location)null);
         ce.initCause(ie);
         throw ce;
      } catch (IllegalAccessException iae) {
         CompileException ce = new CompileException("The class or its zero-parameter constructor is not accessible", (Location)null);
         ce.initCause(iae);
         throw ce;
      }
   }

   /** @deprecated */
   @Deprecated
   public static Object createFastClassBodyEvaluator(Scanner scanner, @Nullable Class baseType, @Nullable ClassLoader parentClassLoader) throws CompileException, IOException {
      return createFastClassBodyEvaluator(scanner, "SC", baseType != null && !baseType.isInterface() ? baseType : null, baseType != null && baseType.isInterface() ? new Class[]{baseType} : new Class[0], parentClassLoader);
   }

   /** @deprecated */
   @Deprecated
   public static Object createFastClassBodyEvaluator(Scanner scanner, String className, @Nullable Class extendedClass, Class[] implementedInterfaces, @Nullable ClassLoader parentClassLoader) throws CompileException, IOException {
      ClassBodyEvaluator cbe = new ClassBodyEvaluator();
      cbe.setClassName(className);
      cbe.setExtendedClass(extendedClass);
      cbe.setImplementedInterfaces(implementedInterfaces);
      cbe.setParentClassLoader(parentClassLoader);
      cbe.cook(scanner);
      Class<?> c = cbe.getClazz();

      try {
         return c.newInstance();
      } catch (InstantiationException e) {
         throw new CompileException(e.getMessage(), (Location)null);
      } catch (IllegalAccessException e) {
         throw new InternalCompilerException(e.toString(), e);
      }
   }
}
