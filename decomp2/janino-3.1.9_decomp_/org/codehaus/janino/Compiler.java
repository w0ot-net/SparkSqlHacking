package org.codehaus.janino;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.commons.compiler.AbstractCompiler;
import org.codehaus.commons.compiler.CompileException;
import org.codehaus.commons.compiler.ICompiler;
import org.codehaus.commons.compiler.InternalCompilerException;
import org.codehaus.commons.compiler.Location;
import org.codehaus.commons.compiler.WarningHandler;
import org.codehaus.commons.compiler.java9.java.lang.module.ModuleFinder;
import org.codehaus.commons.compiler.java9.java.lang.module.ModuleReference;
import org.codehaus.commons.compiler.util.Benchmark;
import org.codehaus.commons.compiler.util.StringPattern;
import org.codehaus.commons.compiler.util.StringUtil;
import org.codehaus.commons.compiler.util.resource.DirectoryResourceFinder;
import org.codehaus.commons.compiler.util.resource.FileResource;
import org.codehaus.commons.compiler.util.resource.FileResourceCreator;
import org.codehaus.commons.compiler.util.resource.JarDirectoriesResourceFinder;
import org.codehaus.commons.compiler.util.resource.MultiResourceFinder;
import org.codehaus.commons.compiler.util.resource.PathResourceFinder;
import org.codehaus.commons.compiler.util.resource.Resource;
import org.codehaus.commons.compiler.util.resource.ResourceCreator;
import org.codehaus.commons.compiler.util.resource.ResourceFinder;
import org.codehaus.commons.nullanalysis.Nullable;
import org.codehaus.janino.util.ClassFile;

public class Compiler extends AbstractCompiler {
   private static final Logger LOGGER = Logger.getLogger(Compiler.class.getName());
   private EnumSet options;
   @Nullable
   private IClassLoader iClassLoader;
   private Benchmark benchmark;
   private final List parsedCompilationUnits;
   public static final StringPattern[] DEFAULT_WARNING_HANDLE_PATTERNS;

   public Compiler() {
      this.options = EnumSet.noneOf(JaninoOption.class);
      this.benchmark = new Benchmark(false);
      this.parsedCompilationUnits = new ArrayList();
   }

   /** @deprecated */
   @Deprecated
   public Compiler(ResourceFinder sourceFinder, IClassLoader parentIClassLoader) {
      this();
      this.setSourceFinder(sourceFinder);
      this.setIClassLoader(parentIClassLoader);
   }

   /** @deprecated */
   @Deprecated
   public Compiler(File[] sourcePath, File[] classPath, @Nullable File[] extDirs, @Nullable File[] bootClassPath, @Nullable File destinationDirectory, @Nullable String characterEncoding, boolean verbose, boolean debugSource, boolean debugLines, boolean debugVars, StringPattern[] warningHandlePatterns, boolean rebuild) {
      this.options = EnumSet.noneOf(JaninoOption.class);
      this.benchmark = new Benchmark(false);
      this.parsedCompilationUnits = new ArrayList();
      this.setSourcePath(sourcePath);
      this.setClassPath(classPath);
      this.setExtensionDirectories((File[])nullToEmptyArray(extDirs, File.class));
      this.setBootClassPath((File[])nullToEmptyArray(bootClassPath, File.class));
      this.setDestinationDirectory(destinationDirectory, rebuild);
      this.setCharacterEncoding(characterEncoding);
      this.setVerbose(verbose);
      this.setDebugSource(debugSource);
      this.setDebugLines(debugLines);
      this.setDebugVars(debugVars);
      this.setClassFileFinder((ResourceFinder)(rebuild ? ResourceFinder.EMPTY_RESOURCE_FINDER : (destinationDirectory == null ? ICompiler.FIND_NEXT_TO_SOURCE_FILE : new DirectoryResourceFinder(destinationDirectory))));
      this.setVerbose(verbose);
      this.setDebugSource(debugSource);
      this.setDebugLines(debugLines);
      this.setDebugVars(debugVars);
      this.setCharacterEncoding(characterEncoding);
      this.setWarningHandler(new FilterWarningHandler(warningHandlePatterns, new WarningHandler() {
         public void handleWarning(@Nullable String handle, String message, @Nullable Location location) {
            StringBuilder sb = new StringBuilder();
            if (location != null) {
               sb.append(location).append(": ");
            }

            if (handle == null) {
               sb.append("Warning: ");
            } else {
               sb.append("Warning ").append(handle).append(": ");
            }

            sb.append(message);
            System.err.println(sb.toString());
         }
      }));
      this.benchmark.report("*** JANINO - an embedded compiler for the Java(TM) programming language");
      this.benchmark.report("*** For more information visit http://janino.codehaus.org");
      this.benchmark.report("Source path", sourcePath);
      this.benchmark.report("Class path", classPath);
      this.benchmark.report("Ext dirs", extDirs);
      this.benchmark.report("Boot class path", bootClassPath);
      this.benchmark.report("Destination directory", destinationDirectory);
      this.benchmark.report("Character encoding", characterEncoding);
      this.benchmark.report("Verbose", verbose);
      this.benchmark.report("Debug source", debugSource);
      this.benchmark.report("Debug lines", debugSource);
      this.benchmark.report("Debug vars", debugSource);
      this.benchmark.report("Warning handle patterns", warningHandlePatterns);
      this.benchmark.report("Rebuild", rebuild);
   }

   private static Object[] nullToEmptyArray(@Nullable Object[] a, Class elementType) {
      return a != null ? a : (Object[])Array.newInstance(elementType, 0);
   }

   public EnumSet options() {
      return this.options;
   }

   public Compiler options(EnumSet options) {
      this.options = options;
      return this;
   }

   public void compile(Resource[] sourceResources) throws CompileException, IOException {
      this.benchmark.beginReporting();

      try {
         IClassLoader iClassLoader = new CompilerIClassLoader(this.sourceFinder, this.classFileFinder, this.getIClassLoader());
         this.parsedCompilationUnits.clear();

         for(Resource sourceResource : sourceResources) {
            LOGGER.log(Level.FINE, "Compiling \"{0}\"", sourceResource);
            UnitCompiler uc = new UnitCompiler(this.parseAbstractCompilationUnit(sourceResource.getFileName(), new BufferedInputStream(sourceResource.open()), this.sourceCharset), iClassLoader);
            uc.setTargetVersion(this.targetVersion);
            uc.setCompileErrorHandler(this.compileErrorHandler);
            uc.setWarningHandler(this.warningHandler);
            uc.options(this.options);
            this.parsedCompilationUnits.add(uc);
         }

         for(int i = 0; i < this.parsedCompilationUnits.size(); ++i) {
            UnitCompiler unitCompiler = (UnitCompiler)this.parsedCompilationUnits.get(i);
            Java.AbstractCompilationUnit acu = unitCompiler.getAbstractCompilationUnit();
            if (acu.fileName == null) {
               throw new InternalCompilerException();
            }

            final File sourceFile = new File(acu.fileName);
            unitCompiler.setTargetVersion(this.targetVersion);
            unitCompiler.setCompileErrorHandler(this.compileErrorHandler);
            unitCompiler.setWarningHandler(this.warningHandler);
            this.benchmark.beginReporting("Compiling compilation unit \"" + sourceFile + "\"");

            try {
               unitCompiler.compileUnit(this.debugSource, this.debugLines, this.debugVars, new UnitCompiler.ClassFileConsumer() {
                  public void consume(ClassFile classFile) throws IOException {
                     Compiler.this.storeClassFile(classFile, sourceFile);
                  }
               });
            } finally {
               this.benchmark.endReporting();
            }
         }
      } finally {
         this.benchmark.endReporting("Compiled " + this.parsedCompilationUnits.size() + " compilation unit(s)");
      }

   }

   private Java.AbstractCompilationUnit parseAbstractCompilationUnit(String fileName, InputStream inputStream, Charset charset) throws CompileException, IOException {
      Java.AbstractCompilationUnit var6;
      try {
         Scanner scanner = new Scanner(fileName, new InputStreamReader(inputStream, charset));
         Parser parser = new Parser(scanner);
         parser.setSourceVersion(this.sourceVersion);
         parser.setWarningHandler(this.warningHandler);
         this.benchmark.beginReporting("Parsing \"" + fileName + "\"");

         try {
            var6 = parser.parseAbstractCompilationUnit();
         } finally {
            this.benchmark.endReporting();
         }
      } finally {
         inputStream.close();
      }

      return var6;
   }

   public static File getClassFile(String className, File sourceFile, @Nullable File destinationDirectory) {
      if (destinationDirectory != null) {
         return new File(destinationDirectory, ClassFile.getClassFileResourceName(className));
      } else {
         int idx = className.lastIndexOf(46);
         return new File(sourceFile.getParentFile(), ClassFile.getClassFileResourceName(className.substring(idx + 1)));
      }
   }

   public void storeClassFile(ClassFile classFile, final File sourceFile) throws IOException {
      String classFileResourceName = ClassFile.getClassFileResourceName(classFile.getThisClassName());
      ResourceCreator rc;
      if (this.classFileCreator != ICompiler.CREATE_NEXT_TO_SOURCE_FILE) {
         rc = this.classFileCreator;

         assert rc != null;
      } else {
         rc = new FileResourceCreator() {
            protected File getFile(String resourceName) {
               return new File(sourceFile.getParentFile(), resourceName.substring(resourceName.lastIndexOf(47) + 1));
            }
         };
      }

      OutputStream os = rc.createResource(classFileResourceName);

      try {
         classFile.store(os);
      } catch (IOException ioe) {
         try {
            os.close();
         } catch (IOException var16) {
         }

         os = null;
         if (!rc.deleteResource(classFileResourceName)) {
            IOException ioe2 = new IOException("Could not delete incompletely written class file \"" + classFileResourceName + "\"");
            ioe2.initCause(ioe);
            throw ioe2;
         }

         throw ioe;
      } finally {
         if (os != null) {
            try {
               os.close();
            } catch (IOException var15) {
            }
         }

      }

   }

   public void setIClassLoader(IClassLoader iClassLoader) {
      this.iClassLoader = iClassLoader;
   }

   public void setVerbose(boolean verbose) {
      this.benchmark = new Benchmark(verbose);
   }

   private IClassLoader getIClassLoader() {
      if (this.iClassLoader != null) {
         return this.iClassLoader;
      } else {
         File[] bcp = this.bootClassPath;
         if (bcp == null) {
            String sbcp = System.getProperty("sun.boot.class.path");
            if (sbcp != null) {
               this.bootClassPath = bcp = StringUtil.parsePath(sbcp);
            }
         }

         ResourceFinder classPathResourceFinder;
         if (bcp != null) {
            classPathResourceFinder = new MultiResourceFinder(Arrays.asList(new PathResourceFinder(bcp), new JarDirectoriesResourceFinder(this.extensionDirectories), new PathResourceFinder(this.classPath)));
         } else {
            URL r = ClassLoader.getSystemClassLoader().getResource("java/lang/Object.class");

            assert r != null;

            assert "jrt".equalsIgnoreCase(r.getProtocol()) : r.toString();

            ResourceFinder rf = new ResourceFinder() {
               @Nullable
               public Resource findResource(final String resourceName) {
                  try {
                     for(ModuleReference mr : ModuleFinder.ofSystem().findAll()) {
                        final URI moduleContentLocation = (URI)mr.location().get();
                        URL classFileUrl = new URL(moduleContentLocation + "/" + resourceName);
                        final URLConnection uc = classFileUrl.openConnection();

                        try {
                           uc.connect();
                           return new Resource() {
                              public InputStream open() throws IOException {
                                 try {
                                    return uc.getInputStream();
                                 } catch (IOException ioe) {
                                    throw new IOException(moduleContentLocation + ", " + resourceName, ioe);
                                 }
                              }

                              public String getFileName() {
                                 return resourceName;
                              }

                              public long lastModified() {
                                 return uc.getLastModified();
                              }
                           };
                        } catch (IOException var9) {
                        }
                     }

                     return null;
                  } catch (Exception e) {
                     throw new AssertionError(e);
                  }
               }
            };
            classPathResourceFinder = new MultiResourceFinder(Arrays.asList(rf, new JarDirectoriesResourceFinder(this.extensionDirectories), new PathResourceFinder(this.classPath)));
         }

         return this.iClassLoader = new ResourceFinderIClassLoader(classPathResourceFinder, (IClassLoader)null);
      }
   }

   static {
      DEFAULT_WARNING_HANDLE_PATTERNS = StringPattern.PATTERNS_NONE;
   }

   private class CompilerIClassLoader extends IClassLoader {
      private final ResourceFinder sourceFinder;
      @Nullable
      private final ResourceFinder classFileFinder;

      CompilerIClassLoader(ResourceFinder sourceFinder, @Nullable ResourceFinder classFileFinder, IClassLoader parentIClassLoader) {
         super(parentIClassLoader);
         this.sourceFinder = sourceFinder;
         this.classFileFinder = classFileFinder;
         super.postConstruct();
      }

      @Nullable
      protected IClass findIClass(String type) throws ClassNotFoundException {
         Compiler.LOGGER.entering((String)null, "findIClass", type);
         String className = Descriptor.toClassName(type);
         Compiler.LOGGER.log(Level.FINE, "className={0}", className);
         if (className.startsWith("java.")) {
            return null;
         } else {
            int idx = className.indexOf(36, className.lastIndexOf(46) + 2);

            while(true) {
               String topLevelClassName = idx == -1 ? className : className.substring(0, idx);

               for(int i = 0; i < Compiler.this.parsedCompilationUnits.size(); ++i) {
                  UnitCompiler uc = (UnitCompiler)Compiler.this.parsedCompilationUnits.get(i);
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

               if (idx == -1) {
                  Resource sourceResource = this.sourceFinder.findResource(ClassFile.getSourceResourceName(className));
                  if (sourceResource == null) {
                     return null;
                  }

                  ResourceFinder cff = this.classFileFinder;
                  Resource classFileResource;
                  if (cff != ICompiler.FIND_NEXT_TO_SOURCE_FILE) {
                     assert cff != null;

                     classFileResource = cff.findResource(ClassFile.getClassFileResourceName(className));
                  } else {
                     if (!(sourceResource instanceof FileResource)) {
                        return null;
                     }

                     File classFile = new File(((FileResource)sourceResource).getFile().getParentFile(), ClassFile.getClassFileResourceName(className.substring(className.lastIndexOf(46) + 1)));
                     classFileResource = classFile.exists() ? new FileResource(classFile) : null;
                  }

                  if (classFileResource != null && sourceResource.lastModified() <= classFileResource.lastModified()) {
                     return this.defineIClassFromClassFileResource(classFileResource);
                  }

                  return this.defineIClassFromSourceResource(sourceResource, className);
               }

               idx = className.indexOf(36, idx + 2);
            }
         }
      }

      private IClass defineIClassFromSourceResource(Resource sourceResource, String className) throws ClassNotFoundException {
         UnitCompiler uc;
         try {
            Java.AbstractCompilationUnit acu = Compiler.this.parseAbstractCompilationUnit(sourceResource.getFileName(), new BufferedInputStream(sourceResource.open()), Compiler.this.sourceCharset);
            uc = (new UnitCompiler(acu, this)).options(Compiler.this.options);
         } catch (IOException ex) {
            throw new ClassNotFoundException("Parsing compilation unit \"" + sourceResource + "\"", ex);
         } catch (CompileException ex) {
            throw new ClassNotFoundException("Parsing compilation unit \"" + sourceResource + "\"", ex);
         }

         Compiler.this.parsedCompilationUnits.add(uc);
         IClass res = uc.findClass(className);
         if (res == null) {
            throw new ClassNotFoundException("\"" + sourceResource + "\" does not declare \"" + className + "\"");
         } else {
            this.defineIClass(res);
            return res;
         }
      }

      private IClass defineIClassFromClassFileResource(Resource classFileResource) throws ClassNotFoundException {
         Compiler.this.benchmark.beginReporting("Loading class file \"" + classFileResource.getFileName() + "\"");

         ClassFileIClass var5;
         try {
            InputStream is = null;

            ClassFile cf;
            try {
               is = classFileResource.open();
               cf = new ClassFile(new BufferedInputStream(is));
            } catch (IOException ex) {
               throw new ClassNotFoundException("Opening class file resource \"" + classFileResource + "\"", ex);
            } finally {
               if (is != null) {
                  try {
                     is.close();
                  } catch (IOException var18) {
                  }
               }

            }

            ClassFileIClass result = new ClassFileIClass(cf, this);
            this.defineIClass(result);
            result.resolveAllClasses();
            var5 = result;
         } finally {
            Compiler.this.benchmark.endReporting();
         }

         return var5;
      }
   }
}
