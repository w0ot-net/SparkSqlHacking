package org.codehaus.janino;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import org.codehaus.commons.compiler.CompileException;
import org.codehaus.commons.compiler.Cookable;
import org.codehaus.commons.compiler.ErrorHandler;
import org.codehaus.commons.compiler.ISimpleCompiler;
import org.codehaus.commons.compiler.InternalCompilerException;
import org.codehaus.commons.compiler.Location;
import org.codehaus.commons.compiler.WarningHandler;
import org.codehaus.commons.compiler.util.Disassembler;
import org.codehaus.commons.compiler.util.SystemProperties;
import org.codehaus.commons.compiler.util.reflect.ByteArrayClassLoader;
import org.codehaus.commons.nullanalysis.Nullable;
import org.codehaus.janino.util.ClassFile;

public class SimpleCompiler extends Cookable implements ISimpleCompiler {
   private static final boolean disassembleClassFilesToStdout = SystemProperties.getBooleanClassProperty(SimpleCompiler.class, "disassembleClassFilesToStdout");
   private static final Logger LOGGER = Logger.getLogger(SimpleCompiler.class.getName());
   private ClassLoader parentClassLoader = Thread.currentThread().getContextClassLoader();
   @Nullable
   private ClassLoaderIClassLoader classLoaderIClassLoader;
   @Nullable
   private ErrorHandler compileErrorHandler;
   @Nullable
   private WarningHandler warningHandler;
   private boolean debugSource = Boolean.getBoolean("org.codehaus.janino.source_debugging.enable");
   private boolean debugLines;
   private boolean debugVars;
   private int sourceVersion;
   private int targetVersion;
   private EnumSet options;
   @Nullable
   private Collection classFiles;
   @Nullable
   private Map getBytecodesCache;
   @Nullable
   private ClassLoader getClassLoaderCache;

   public static void main(String[] args) throws Exception {
      if (args.length >= 1 && "-help".equals(args[0])) {
         System.out.println("Usage:");
         System.out.println("    java " + SimpleCompiler.class.getName() + " <source-file> <class-name> { <argument> }");
         System.out.println("Reads a compilation unit from the given <source-file> and invokes method");
         System.out.println("\"public static void main(String[])\" of class <class-name>, passing the");
         System.out.println("given <argument>s.");
         System.exit(1);
      }

      if (args.length < 2) {
         System.err.println("Source file and/or class name missing; try \"-help\".");
         System.exit(1);
      }

      String sourceFileName = args[0];
      String className = args[1];
      String[] arguments = new String[args.length - 2];
      System.arraycopy(args, 2, arguments, 0, arguments.length);
      ClassLoader cl = (new SimpleCompiler(sourceFileName, new FileInputStream(sourceFileName))).getClassLoader();
      Class<?> c = cl.loadClass(className);
      Method m = c.getMethod("main", String[].class);
      m.invoke((Object)null, arguments);
   }

   public SimpleCompiler(@Nullable String fileName, Reader in) throws IOException, CompileException {
      this.debugLines = this.debugSource;
      this.debugVars = this.debugSource;
      this.sourceVersion = -1;
      this.targetVersion = -1;
      this.options = EnumSet.noneOf(JaninoOption.class);
      this.cook(fileName, in);
   }

   public SimpleCompiler(@Nullable String fileName, InputStream is) throws IOException, CompileException {
      this.debugLines = this.debugSource;
      this.debugVars = this.debugSource;
      this.sourceVersion = -1;
      this.targetVersion = -1;
      this.options = EnumSet.noneOf(JaninoOption.class);
      this.cook(fileName, is);
   }

   public SimpleCompiler(String fileName) throws IOException, CompileException {
      this.debugLines = this.debugSource;
      this.debugVars = this.debugSource;
      this.sourceVersion = -1;
      this.targetVersion = -1;
      this.options = EnumSet.noneOf(JaninoOption.class);
      this.cookFile(fileName);
   }

   public SimpleCompiler(Scanner scanner, @Nullable ClassLoader parentClassLoader) throws IOException, CompileException {
      this.debugLines = this.debugSource;
      this.debugVars = this.debugSource;
      this.sourceVersion = -1;
      this.targetVersion = -1;
      this.options = EnumSet.noneOf(JaninoOption.class);
      this.setParentClassLoader(parentClassLoader);
      this.cook(scanner);
   }

   public SimpleCompiler() {
      this.debugLines = this.debugSource;
      this.debugVars = this.debugSource;
      this.sourceVersion = -1;
      this.targetVersion = -1;
      this.options = EnumSet.noneOf(JaninoOption.class);
   }

   public void setParentClassLoader(@Nullable ClassLoader parentClassLoader) {
      this.parentClassLoader = parentClassLoader != null ? parentClassLoader : Thread.currentThread().getContextClassLoader();
   }

   public void setDebuggingInformation(boolean debugSource, boolean debugLines, boolean debugVars) {
      this.debugSource = debugSource;
      this.debugLines = debugLines;
      this.debugVars = debugVars;
   }

   public final void cook(@Nullable String fileName, Reader r) throws CompileException, IOException {
      this.cook(new Scanner(fileName, r));
   }

   public void cook(Scanner scanner) throws CompileException, IOException {
      Parser parser = new Parser(scanner);
      parser.setSourceVersion(this.sourceVersion);
      parser.setWarningHandler(this.warningHandler);

      Java.AbstractCompilationUnit acu;
      try {
         acu = parser.parseAbstractCompilationUnit();
      } catch (CompileException ce) {
         this.classFiles = Collections.emptyList();
         throw ce;
      }

      this.compileToClassLoader(acu);
   }

   public void cook(Java.AbstractCompilationUnit abstractCompilationUnit) throws CompileException {
      LOGGER.entering((String)null, "cook", abstractCompilationUnit);
      this.assertUncooked();
      IClassLoader icl = this.classLoaderIClassLoader = new ClassLoaderIClassLoader(this.parentClassLoader);

      try {
         UnitCompiler unitCompiler = (new UnitCompiler(abstractCompilationUnit, icl)).options(this.options);
         unitCompiler.setTargetVersion(this.targetVersion);
         unitCompiler.setCompileErrorHandler(this.compileErrorHandler);
         unitCompiler.setWarningHandler(this.warningHandler);
         final Collection<ClassFile> cfs = new ArrayList();
         unitCompiler.compileUnit(this.debugSource, this.debugLines, this.debugVars, new UnitCompiler.ClassFileConsumer() {
            public void consume(ClassFile classFile) {
               if (SimpleCompiler.disassembleClassFilesToStdout) {
                  Disassembler.disassembleToStdout(classFile.toByteArray());
               }

               cfs.add(classFile);
            }
         });
         this.classFiles = cfs;
      } catch (CompileException ce) {
         this.classFiles = Collections.emptyList();
         throw ce;
      } finally {
         this.classLoaderIClassLoader = null;
      }

   }

   public ClassFile[] getClassFiles() {
      Collection<ClassFile> c = this.assertCooked();
      return (ClassFile[])c.toArray(new ClassFile[c.size()]);
   }

   public void setSourceVersion(int version) {
      this.sourceVersion = version;
   }

   public void setTargetVersion(int version) {
      this.targetVersion = version;
   }

   public Map getBytecodes() {
      return this.getBytecodesCache != null ? this.getBytecodesCache : (this.getBytecodesCache = this.getBytecodes2());
   }

   private Map getBytecodes2() {
      Map<String, byte[]> result = new HashMap();

      for(ClassFile cf : this.getClassFiles()) {
         result.put(cf.getThisClassName(), cf.toByteArray());
      }

      return result;
   }

   public ClassLoader getClassLoader() {
      return this.getClassLoaderCache != null ? this.getClassLoaderCache : (this.getClassLoaderCache = this.getClassLoader2());
   }

   private ClassLoader getClassLoader2() {
      final Map<String, byte[]> bytecode = this.getBytecodes();
      return (ClassLoader)AccessController.doPrivileged(new PrivilegedAction() {
         public ClassLoader run() {
            return new ByteArrayClassLoader(bytecode, SimpleCompiler.this.parentClassLoader);
         }
      });
   }

   public boolean equals(@Nullable Object o) {
      if (!(o instanceof SimpleCompiler)) {
         return false;
      } else {
         SimpleCompiler that = (SimpleCompiler)o;
         return this.getClass() != that.getClass() ? false : this.assertCooked().equals(that.assertCooked());
      }
   }

   public int hashCode() {
      return this.parentClassLoader.hashCode();
   }

   public void setCompileErrorHandler(@Nullable ErrorHandler compileErrorHandler) {
      this.compileErrorHandler = compileErrorHandler;
   }

   public void setWarningHandler(@Nullable WarningHandler warningHandler) {
      this.warningHandler = warningHandler;
   }

   public EnumSet options() {
      return this.options;
   }

   public SimpleCompiler options(EnumSet options) {
      this.options = options;
      return this;
   }

   @Nullable
   protected Java.Type optionalClassToType(Location location, @Nullable Class clazz) {
      return clazz == null ? null : this.classToType(location, clazz);
   }

   protected Java.Type classToType(final Location location, final Class clazz) {
      return new Java.Type(location) {
         @Nullable
         private Java.SimpleType delegate;

         @Nullable
         public Object accept(Visitor.AtomVisitor visitor) throws Throwable {
            return visitor.visitType(this.getDelegate());
         }

         @Nullable
         public Object accept(Visitor.TypeVisitor visitor) throws Throwable {
            return this.getDelegate().accept(visitor);
         }

         public String toString() {
            return this.getDelegate().toString();
         }

         private Java.Type getDelegate() {
            if (this.delegate != null) {
               return this.delegate;
            } else {
               ClassLoaderIClassLoader icl = SimpleCompiler.this.classLoaderIClassLoader;

               assert icl != null;

               IClass iClass;
               try {
                  iClass = icl.loadIClass(Descriptor.fromClassName(clazz.getName()));
               } catch (ClassNotFoundException ex) {
                  throw new InternalCompilerException("Loading IClass \"" + clazz.getName() + "\": " + ex);
               }

               if (iClass == null) {
                  throw new InternalCompilerException("Cannot load class '" + clazz.getName() + "' through the parent loader");
               } else {
                  IClass iClass2 = iClass;
                  Class<?> class2 = clazz;

                  do {
                     IClass ct = iClass2.getComponentType();
                     if (ct == null) {
                        if (class2.getComponentType() != null) {
                           throw new InternalCompilerException("Array type/class inconsistency");
                        } else {
                           if (class2.isPrimitive()) {
                              if (!iClass2.isPrimitive()) {
                                 throw new InternalCompilerException("Primitive type/class inconsistency");
                              }
                           } else {
                              if (iClass2.isPrimitive()) {
                                 throw new InternalCompilerException("Primitive type/class inconsistency");
                              }

                              if (((ReflectionIClass)iClass2).getClazz() != class2) {
                                 throw new InternalCompilerException("Class '" + class2.getName() + "' was loaded through a different loader");
                              }
                           }

                           return this.delegate = new Java.SimpleType(location, iClass);
                        }
                     }

                     iClass2 = ct;
                     class2 = class2.getComponentType();
                  } while(class2 != null);

                  throw new InternalCompilerException("Array type/class inconsistency");
               }
            }
         }
      };
   }

   protected Java.Type[] classesToTypes(Location location, @Nullable Class[] classes) {
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

   protected final ClassLoader compileToClassLoader(Java.AbstractCompilationUnit abstractCompilationUnit) throws CompileException {
      this.cook(abstractCompilationUnit);
      return this.getClassLoader();
   }

   private void assertUncooked() {
      if (this.classFiles != null) {
         throw new IllegalStateException("Must only be called before \"cook()\"");
      }
   }

   private Collection assertCooked() {
      Collection<ClassFile> result = this.classFiles;
      if (result == null) {
         throw new IllegalStateException("Must only be called after \"cook()\"");
      } else {
         return result;
      }
   }
}
