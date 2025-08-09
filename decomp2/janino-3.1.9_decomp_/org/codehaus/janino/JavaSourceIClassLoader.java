package org.codehaus.janino;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.commons.compiler.CompileException;
import org.codehaus.commons.compiler.ErrorHandler;
import org.codehaus.commons.compiler.Location;
import org.codehaus.commons.compiler.WarningHandler;
import org.codehaus.commons.compiler.util.resource.Resource;
import org.codehaus.commons.compiler.util.resource.ResourceFinder;
import org.codehaus.commons.nullanalysis.Nullable;
import org.codehaus.janino.util.ClassFile;

public class JavaSourceIClassLoader extends IClassLoader {
   private static final Logger LOGGER = Logger.getLogger(JavaSourceIClassLoader.class.getName());
   private ResourceFinder sourceFinder;
   private Charset sourceCharset;
   private EnumSet options = EnumSet.noneOf(JaninoOption.class);
   private final Set unitCompilers = new HashSet();
   private int sourceVersion = -1;
   private int targetVersion = -1;
   @Nullable
   private ErrorHandler compileErrorHandler;
   @Nullable
   private WarningHandler warningHandler;

   public JavaSourceIClassLoader(ResourceFinder sourceFinder, @Nullable String sourceCharsetName, @Nullable IClassLoader parentIClassLoader) {
      super(parentIClassLoader);
      this.sourceFinder = sourceFinder;
      this.sourceCharset = sourceCharsetName == null ? Charset.defaultCharset() : Charset.forName(sourceCharsetName);
      super.postConstruct();
   }

   public void setSourceVersion(int version) {
      this.sourceVersion = version;
   }

   public void setTargetVersion(int version) {
      this.targetVersion = version;
   }

   public Set getUnitCompilers() {
      return this.unitCompilers;
   }

   public void setSourceFinder(ResourceFinder sourceFinder) {
      this.sourceFinder = sourceFinder;
   }

   public ResourceFinder getSourceFinder() {
      return this.sourceFinder;
   }

   /** @deprecated */
   @Deprecated
   public void setCharacterEncoding(@Nullable String sourceCharsetName) {
      this.setSourceCharset(sourceCharsetName == null ? Charset.defaultCharset() : Charset.forName(sourceCharsetName));
   }

   public void setSourceCharset(Charset sourceCharset) {
      this.sourceCharset = sourceCharset;
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

   public JavaSourceIClassLoader options(EnumSet options) {
      this.options = options;
      return this;
   }

   @Nullable
   public IClass findIClass(String fieldDescriptor) throws ClassNotFoundException {
      LOGGER.entering((String)null, "findIClass", fieldDescriptor);
      String className = Descriptor.toClassName(fieldDescriptor);
      LOGGER.log(Level.FINE, "className={0}", className);
      if (className.startsWith("java.")) {
         return null;
      } else {
         int idx = className.indexOf(36);
         String topLevelClassName = idx == -1 ? className : className.substring(0, idx);

         for(UnitCompiler uc : this.unitCompilers) {
            IClass res = uc.findClass(topLevelClassName);
            if (res != null) {
               if (!className.equals(topLevelClassName)) {
                  res = uc.findClass(className);
                  if (res == null) {
                     return null;
                  }
               }

               this.defineIClass(res);
               return res;
            }
         }

         try {
            Java.AbstractCompilationUnit acu = this.findCompilationUnit(className);
            if (acu == null) {
               return null;
            } else {
               UnitCompiler uc = (new UnitCompiler(acu, this)).options(this.options);
               uc.setTargetVersion(this.targetVersion);
               uc.setCompileErrorHandler(this.compileErrorHandler);
               uc.setWarningHandler(this.warningHandler);
               this.unitCompilers.add(uc);
               IClass res = uc.findClass(className);
               if (res == null) {
                  if (className.equals(topLevelClassName)) {
                     throw new CompileException("Compilation unit '" + className + "' does not declare a class with the same name", (Location)null);
                  } else {
                     return null;
                  }
               } else {
                  this.defineIClass(res);
                  return res;
               }
            }
         } catch (IOException e) {
            throw new ClassNotFoundException("Parsing compilation unit '" + className + "'", e);
         } catch (CompileException e) {
            throw new ClassNotFoundException("Parsing compilation unit '" + className + "'", e);
         }
      }
   }

   @Nullable
   protected Java.AbstractCompilationUnit findCompilationUnit(String className) throws IOException, CompileException {
      Resource sourceResource = this.sourceFinder.findResource(ClassFile.getSourceResourceName(className));
      LOGGER.log(Level.FINE, "sourceResource={0}", sourceResource);
      if (sourceResource == null) {
         return null;
      } else {
         InputStream inputStream = sourceResource.open();

         Java.AbstractCompilationUnit var6;
         try {
            Scanner scanner = new Scanner(sourceResource.getFileName(), new InputStreamReader(inputStream, this.sourceCharset));
            Parser parser = new Parser(scanner);
            parser.setSourceVersion(this.sourceVersion);
            parser.setWarningHandler(this.warningHandler);
            var6 = parser.parseAbstractCompilationUnit();
         } finally {
            try {
               inputStream.close();
            } catch (IOException var13) {
            }

         }

         return var6;
      }
   }
}
