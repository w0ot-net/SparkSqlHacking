package io.vertx.core.impl.verticle;

import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.net.impl.URIDecoder;
import java.io.File;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.DiagnosticListener;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;
import javax.tools.JavaFileObject.Kind;

public class CompilingClassLoader extends ClassLoader {
   private static final Logger log = LoggerFactory.getLogger(CompilingClassLoader.class);
   private static final String JAVA_COMPILER_OPTIONS_PROP_NAME = "vertx.javaCompilerOptions";
   private static final List COMPILER_OPTIONS;
   private final JavaSourceContext javaSourceContext;
   private final MemoryFileManager fileManager;

   public CompilingClassLoader(ClassLoader loader, String sourceName) {
      super(loader);
      URL resource = this.getResource(sourceName);
      if (resource == null) {
         throw new RuntimeException("Resource not found: " + sourceName);
      } else {
         File sourceFile = new File(URIDecoder.decodeURIComponent(resource.getFile(), false));
         if (!sourceFile.canRead()) {
            throw new RuntimeException("File not found: " + sourceFile.getAbsolutePath() + " current dir is: " + (new File(".")).getAbsolutePath());
         } else {
            this.javaSourceContext = new JavaSourceContext(sourceFile);

            try {
               DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector();
               JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
               if (javaCompiler == null) {
                  throw new RuntimeException("Unable to detect java compiler, make sure you're using a JDK not a JRE!");
               } else {
                  StandardJavaFileManager standardFileManager = javaCompiler.getStandardFileManager((DiagnosticListener)null, (Locale)null, (Charset)null);
                  standardFileManager.setLocation(StandardLocation.SOURCE_PATH, Collections.singleton(this.javaSourceContext.getSourceRoot()));
                  this.fileManager = new MemoryFileManager(loader, standardFileManager);
                  JavaFileObject javaFile = standardFileManager.getJavaFileForInput(StandardLocation.SOURCE_PATH, this.resolveMainClassName(), Kind.SOURCE);
                  JavaCompiler.CompilationTask task = javaCompiler.getTask((Writer)null, this.fileManager, diagnostics, COMPILER_OPTIONS, (Iterable)null, Collections.singleton(javaFile));
                  boolean valid = task.call();
                  if (valid) {
                     for(Diagnostic d : diagnostics.getDiagnostics()) {
                        String code = d.getCode();
                        if (code == null || !code.startsWith("compiler.warn.annotation.method.not.found") && !"compiler.warn.proc.processor.incompatible.source.version".equals(code)) {
                           log.info(d);
                        }
                     }

                  } else {
                     for(Diagnostic d : diagnostics.getDiagnostics()) {
                        log.warn(d);
                     }

                     throw new RuntimeException("Compilation failed!");
                  }
               }
            } catch (Exception e) {
               throw new RuntimeException("Compilation failed", e);
            }
         }
      }
   }

   public String resolveMainClassName() {
      return this.javaSourceContext.getClassName();
   }

   protected Class findClass(String name) throws ClassNotFoundException {
      byte[] bytecode = this.getClassBytes(name);
      if (bytecode == null) {
         throw new ClassNotFoundException(name);
      } else {
         return this.defineClass(name, bytecode, 0, bytecode.length);
      }
   }

   public byte[] getClassBytes(String name) {
      return this.fileManager.getCompiledClass(name);
   }

   static {
      String props = System.getProperty("vertx.javaCompilerOptions");
      if (props != null) {
         String[] array = props.split(",");
         List<String> compilerProps = new ArrayList(array.length);

         for(String prop : array) {
            compilerProps.add(prop.trim());
         }

         COMPILER_OPTIONS = Collections.unmodifiableList(compilerProps);
      } else {
         COMPILER_OPTIONS = null;
      }

   }
}
