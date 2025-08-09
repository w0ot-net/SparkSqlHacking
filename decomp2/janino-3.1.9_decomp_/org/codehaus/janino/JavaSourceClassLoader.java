package org.codehaus.janino;

import java.io.File;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.codehaus.commons.compiler.AbstractJavaSourceClassLoader;
import org.codehaus.commons.compiler.CompileException;
import org.codehaus.commons.compiler.ErrorHandler;
import org.codehaus.commons.compiler.InternalCompilerException;
import org.codehaus.commons.compiler.WarningHandler;
import org.codehaus.commons.compiler.util.Disassembler;
import org.codehaus.commons.compiler.util.resource.DirectoryResourceFinder;
import org.codehaus.commons.compiler.util.resource.PathResourceFinder;
import org.codehaus.commons.compiler.util.resource.ResourceFinder;
import org.codehaus.commons.nullanalysis.Nullable;
import org.codehaus.janino.util.ClassFile;

public class JavaSourceClassLoader extends AbstractJavaSourceClassLoader {
   private final Set compiledUnitCompilers;
   private final Map precompiledClasses;
   private final JavaSourceIClassLoader iClassLoader;
   private boolean debugSource;
   private boolean debugLines;
   private boolean debugVars;

   public JavaSourceClassLoader() {
      this(ClassLoader.getSystemClassLoader());
   }

   public JavaSourceClassLoader(ClassLoader parentClassLoader) {
      this(parentClassLoader, (File[])((File[])null), (String)null);
   }

   public JavaSourceClassLoader(ClassLoader parentClassLoader, @Nullable File[] sourcePath, @Nullable String characterEncoding) {
      this(parentClassLoader, (ResourceFinder)(sourcePath == null ? new DirectoryResourceFinder(new File(".")) : new PathResourceFinder(sourcePath)), characterEncoding);
   }

   public JavaSourceClassLoader(ClassLoader parentClassLoader, ResourceFinder sourceFinder, @Nullable String characterEncoding) {
      this(parentClassLoader, new JavaSourceIClassLoader(sourceFinder, characterEncoding, new ClassLoaderIClassLoader(parentClassLoader)));
   }

   public JavaSourceClassLoader(ClassLoader parentClassLoader, JavaSourceIClassLoader iClassLoader) {
      super(parentClassLoader);
      this.compiledUnitCompilers = new HashSet();
      this.precompiledClasses = new HashMap();
      this.debugSource = Boolean.getBoolean("org.codehaus.janino.source_debugging.enable");
      this.debugLines = this.debugSource;
      this.debugVars = this.debugSource;
      this.iClassLoader = iClassLoader;
   }

   public void setSourcePath(File[] sourcePath) {
      this.setSourceFinder(new PathResourceFinder(sourcePath));
   }

   public void setSourceFinder(ResourceFinder sourceFinder) {
      this.iClassLoader.setSourceFinder(sourceFinder);
   }

   public void setSourceCharset(Charset charset) {
      this.iClassLoader.setSourceCharset(charset);
   }

   public void setDebuggingInfo(boolean debugSource, boolean debugLines, boolean debugVars) {
      this.debugSource = debugSource;
      this.debugLines = debugLines;
      this.debugVars = debugVars;
   }

   public void setTargetVersion(int version) {
      this.iClassLoader.setTargetVersion(version);
   }

   public void setCompileErrorHandler(@Nullable ErrorHandler compileErrorHandler) {
      this.iClassLoader.setCompileErrorHandler(compileErrorHandler);
   }

   public void setWarningHandler(@Nullable WarningHandler warningHandler) {
      this.iClassLoader.setWarningHandler(warningHandler);
   }

   protected Class findClass(@Nullable String name) throws ClassNotFoundException {
      assert name != null;

      byte[] bytecode = (byte[])this.precompiledClasses.remove(name);
      if (bytecode == null) {
         Map<String, byte[]> bytecodes = this.generateBytecodes(name);
         if (bytecodes == null) {
            throw new ClassNotFoundException(name);
         }

         this.precompiledClasses.putAll(bytecodes);
         bytecode = (byte[])this.precompiledClasses.remove(name);
         if (bytecode == null) {
            throw new InternalCompilerException("SNO: Scanning, parsing and compiling class \"" + name + "\" did not create a class file!?");
         }
      }

      if (Boolean.getBoolean("disasm")) {
         Disassembler.disassembleToStdout(bytecode);
      }

      return this.defineBytecode(name, bytecode);
   }

   @Nullable
   protected Map generateBytecodes(String name) throws ClassNotFoundException {
      if (this.iClassLoader.loadIClass(Descriptor.fromClassName(name)) == null) {
         return null;
      } else {
         final Map<String, byte[]> bytecodes = new HashMap();

         label29:
         while(true) {
            for(UnitCompiler uc : this.iClassLoader.getUnitCompilers()) {
               if (!this.compiledUnitCompilers.contains(uc)) {
                  try {
                     uc.compileUnit(this.debugSource, this.debugLines, this.debugVars, new UnitCompiler.ClassFileConsumer() {
                        public void consume(ClassFile classFile) {
                           bytecodes.put(classFile.getThisClassName(), classFile.toByteArray());
                        }
                     });
                  } catch (CompileException ex) {
                     throw new ClassNotFoundException(ex.getMessage(), ex);
                  }

                  this.compiledUnitCompilers.add(uc);
                  continue label29;
               }
            }

            return bytecodes;
         }
      }
   }

   private Class defineBytecode(String className, byte[] ba) {
      return this.defineClass(className, ba, 0, ba.length, this.protectionDomainFactory != null ? this.protectionDomainFactory.getProtectionDomain(ClassFile.getSourceResourceName(className)) : null);
   }
}
