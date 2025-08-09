package org.codehaus.janino;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import org.apache.tools.ant.taskdefs.compilers.DefaultCompilerAdapter;
import org.apache.tools.ant.types.Path;
import org.codehaus.commons.compiler.CompileException;
import org.codehaus.commons.compiler.ICompiler;
import org.codehaus.commons.nullanalysis.Nullable;

public class AntCompilerAdapter extends DefaultCompilerAdapter {
   public boolean execute() {
      File[] sourceFiles = this.compileList;
      File destinationDirectory = this.destDir;
      File[] sourcePath = pathToFiles(this.compileSourcepath != null ? this.compileSourcepath : this.src);
      File[] classPath = pathToFiles(this.compileClasspath, new File[]{new File(".")});
      File[] extDirs = pathToFiles(this.extdirs);
      File[] bootClassPath = pathToFiles(this.bootclasspath);
      Charset encoding2 = Charset.forName(this.encoding);
      boolean verbose = this.verbose;
      boolean debugSource;
      boolean debugLines;
      boolean debugVars;
      if (!this.debug) {
         debugSource = false;
         debugLines = false;
         debugVars = false;
      } else {
         String debugLevel = this.attributes.getDebugLevel();
         if (debugLevel == null) {
            debugSource = true;
            debugLines = true;
            debugVars = false;
         } else {
            debugSource = debugLevel.contains("source");
            debugLines = debugLevel.contains("lines");
            debugVars = debugLevel.contains("vars");
         }
      }

      try {
         ICompiler compiler = new Compiler();
         compiler.setSourcePath(sourcePath);
         compiler.setClassPath(classPath);
         compiler.setExtensionDirectories(extDirs);
         compiler.setBootClassPath(bootClassPath);
         compiler.setDestinationDirectory(destinationDirectory, false);
         compiler.setEncoding(encoding2);
         compiler.setVerbose(verbose);
         compiler.setDebugSource(debugSource);
         compiler.setDebugLines(debugLines);
         compiler.setDebugVars(debugVars);
         compiler.compile(sourceFiles);
         return true;
      } catch (CompileException e) {
         System.out.println(e.getMessage());
         return false;
      } catch (IOException e) {
         System.out.println(e.getMessage());
         return false;
      }
   }

   private static File[] pathToFiles(@Nullable Path path) {
      if (path == null) {
         return new File[0];
      } else {
         String[] fileNames = path.list();
         File[] files = new File[fileNames.length];

         for(int i = 0; i < fileNames.length; ++i) {
            files[i] = new File(fileNames[i]);
         }

         return files;
      }
   }

   private static File[] pathToFiles(@Nullable Path path, File[] defaultValue) {
      if (path == null) {
         return defaultValue;
      } else {
         File[] result = pathToFiles(path);

         assert result != null;

         return result;
      }
   }
}
