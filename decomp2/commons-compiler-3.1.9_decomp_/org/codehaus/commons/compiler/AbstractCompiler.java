package org.codehaus.commons.compiler;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.regex.Pattern;
import org.codehaus.commons.compiler.util.Disassembler;
import org.codehaus.commons.compiler.util.StringUtil;
import org.codehaus.commons.compiler.util.SystemProperties;
import org.codehaus.commons.compiler.util.resource.DirectoryResourceCreator;
import org.codehaus.commons.compiler.util.resource.DirectoryResourceFinder;
import org.codehaus.commons.compiler.util.resource.FileResource;
import org.codehaus.commons.compiler.util.resource.PathResourceFinder;
import org.codehaus.commons.compiler.util.resource.Resource;
import org.codehaus.commons.compiler.util.resource.ResourceCreator;
import org.codehaus.commons.compiler.util.resource.ResourceFinder;
import org.codehaus.commons.nullanalysis.Nullable;

public abstract class AbstractCompiler implements ICompiler {
   private static final boolean disassembleClassFilesToStdout = SystemProperties.getBooleanClassProperty(AbstractCompiler.class, "disassembleClassFilesToStdout");
   private static final Pattern disassembleClassNames = Pattern.compile(SystemProperties.getClassProperty(AbstractCompiler.class, "disassembleClassNames", ".*"));
   protected ResourceFinder sourceFinder;
   protected ResourceFinder classFileFinder;
   protected ResourceCreator classFileCreator;
   public Charset sourceCharset;
   protected boolean debugSource;
   protected boolean debugLines;
   protected boolean debugVars;
   protected int sourceVersion;
   protected int targetVersion;
   protected File[] extensionDirectories;
   protected File[] classPath;
   @Nullable
   protected File[] bootClassPath;
   @Nullable
   protected ErrorHandler compileErrorHandler;
   @Nullable
   protected WarningHandler warningHandler;

   public AbstractCompiler() {
      this.sourceFinder = ResourceFinder.EMPTY_RESOURCE_FINDER;
      this.classFileFinder = ICompiler.FIND_NEXT_TO_SOURCE_FILE;
      this.classFileCreator = ICompiler.CREATE_NEXT_TO_SOURCE_FILE;
      this.sourceCharset = Charset.defaultCharset();
      this.sourceVersion = -1;
      this.targetVersion = -1;
      this.extensionDirectories = StringUtil.parsePath(System.getProperty("java.ext.dirs", ""));
      this.classPath = StringUtil.parsePath(System.getProperty("java.class.path"));
      this.bootClassPath = StringUtil.parseOptionalPath(System.getProperty("sun.boot.class.path"));
   }

   public void setSourceFinder(ResourceFinder sourceFinder) {
      this.sourceFinder = sourceFinder;
   }

   public final void setClassFileFinder(ResourceFinder destination, boolean rebuild) {
      this.setClassFileFinder((ResourceFinder)(rebuild ? ResourceFinder.EMPTY_RESOURCE_FINDER : destination));
   }

   public void setClassFileFinder(ResourceFinder classFileFinder) {
      this.classFileFinder = classFileFinder;
   }

   public final void setClassFileCreator(final ResourceCreator classFileCreator) {
      if (disassembleClassFilesToStdout) {
         classFileCreator = new ResourceCreator() {
            public OutputStream createResource(String resourceName) throws IOException {
               final OutputStream delegateOs = classFileCreator.createResource(resourceName);

               assert resourceName.endsWith(".class");

               String className = resourceName.substring(0, resourceName.length() - 6).replace('/', '.');
               return (OutputStream)(AbstractCompiler.disassembleClassNames.matcher(className).matches() ? new ByteArrayOutputStream() {
                  public void close() throws IOException {
                     byte[] ba = this.toByteArray();
                     Disassembler.disassembleToStdout(ba);
                     delegateOs.write(ba);
                     delegateOs.close();
                  }
               } : delegateOs);
            }

            public boolean deleteResource(String resourceName) {
               return classFileCreator.deleteResource(resourceName);
            }
         };
      }

      this.classFileCreator = classFileCreator;
   }

   public final boolean compile(File[] sourceFiles) throws CompileException, IOException {
      Resource[] sourceFileResources = new Resource[sourceFiles.length];

      for(int i = 0; i < sourceFiles.length; ++i) {
         sourceFileResources[i] = new FileResource(sourceFiles[i]);
      }

      this.compile(sourceFileResources);
      return true;
   }

   public final void setEncoding(Charset encoding) {
      this.setSourceCharset(encoding);
   }

   public void setSourceCharset(Charset charset) {
      this.sourceCharset = charset;
   }

   public final void setCharacterEncoding(@Nullable String characterEncoding) {
      this.setSourceCharset(characterEncoding == null ? Charset.defaultCharset() : Charset.forName(characterEncoding));
   }

   public void setDebugLines(boolean value) {
      this.debugLines = value;
   }

   public void setDebugVars(boolean value) {
      this.debugVars = value;
   }

   public void setDebugSource(boolean value) {
      this.debugSource = value;
   }

   public void setSourceVersion(int version) {
      this.sourceVersion = version;
   }

   public void setTargetVersion(int version) {
      this.targetVersion = version;
   }

   public void setSourcePath(File[] directoriesAndArchives) {
      this.setSourceFinder(new PathResourceFinder(directoriesAndArchives));
   }

   public void setBootClassPath(File[] directoriesAndArchives) {
      this.bootClassPath = directoriesAndArchives;
   }

   public void setExtensionDirectories(File[] directories) {
      this.extensionDirectories = directories;
   }

   public void setClassPath(File[] directoriesAndArchives) {
      this.classPath = directoriesAndArchives;
   }

   public final void setDestinationDirectory(@Nullable File destinationDirectory, boolean rebuild) {
      if (destinationDirectory == ICompiler.NO_DESTINATION_DIRECTORY) {
         this.setClassFileCreator(ICompiler.CREATE_NEXT_TO_SOURCE_FILE);
         this.setClassFileFinder(ICompiler.FIND_NEXT_TO_SOURCE_FILE, rebuild);
      } else {
         assert destinationDirectory != null;

         this.setClassFileCreator(new DirectoryResourceCreator(destinationDirectory));
         this.setClassFileFinder(new DirectoryResourceFinder(destinationDirectory), rebuild);
      }

   }

   public void setCompileErrorHandler(@Nullable ErrorHandler compileErrorHandler) {
      this.compileErrorHandler = compileErrorHandler;
   }

   public void setWarningHandler(@Nullable WarningHandler warningHandler) {
      this.warningHandler = warningHandler;
   }
}
