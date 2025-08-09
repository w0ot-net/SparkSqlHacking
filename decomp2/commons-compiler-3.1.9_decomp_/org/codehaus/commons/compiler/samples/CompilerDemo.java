package org.codehaus.commons.compiler.samples;

import java.io.File;
import java.nio.charset.Charset;
import org.codehaus.commons.compiler.CompilerFactoryFactory;
import org.codehaus.commons.compiler.ICompiler;
import org.codehaus.commons.compiler.util.StringUtil;

public final class CompilerDemo {
   private static final String USAGE = "A drop-in replacement for the JAVAC compiler, see the documentation for JAVAC.Usage:%n%n  java " + CompilerDemo.class.getName() + " [ <option> ] ... <source-file> ...%n%nSupported <option>s are:%n  -d <output-dir>           Where to save class files%n  -sourcepath <dirlist>     Where to look for other source files%n  -classpath <dirlist>      Where to look for other class files%n  -extdirs <dirlist>        Where to look for other class files%n  -bootclasspath <dirlist>  Where to look for other class files%n  -encoding <encoding>      Encoding of source files, e.g. \"UTF-8\" or \"ISO-8859-1\"%n  -verbose%n  -g                        Generate all debugging info%n  -g:none                   Generate no debugging info (the default)%n  -g:{source,lines,vars}    Generate only some debugging info%n  -rebuild                  Compile all source files, even if the class files%n                            seem up-to-date%n  -help%n%nThe default encoding in this environment is \"" + Charset.defaultCharset() + "\".%n";

   private CompilerDemo() {
   }

   public static void main(String[] args) throws Exception {
      File destinationDirectory = ICompiler.NO_DESTINATION_DIRECTORY;
      File[] sourcePath = new File[0];
      File[] classPath = new File[]{new File(".")};
      File[] extDirs = new File[0];
      File[] bootClassPath = null;
      Charset encoding = Charset.defaultCharset();
      boolean verbose = false;
      boolean debugSource = true;
      boolean debugLines = true;
      boolean debugVars = false;
      boolean rebuild = false;

      int i;
      for(i = 0; i < args.length; ++i) {
         String arg = args[i];
         if (arg.charAt(0) != '-') {
            break;
         }

         if ("-d".equals(arg)) {
            ++i;
            destinationDirectory = new File(args[i]);
         } else if ("-sourcepath".equals(arg)) {
            ++i;
            sourcePath = StringUtil.parsePath(args[i]);
         } else if ("-classpath".equals(arg)) {
            ++i;
            classPath = StringUtil.parsePath(args[i]);
         } else if ("-extdirs".equals(arg)) {
            ++i;
            extDirs = StringUtil.parsePath(args[i]);
         } else if ("-bootclasspath".equals(arg)) {
            ++i;
            bootClassPath = StringUtil.parsePath(args[i]);
         } else if ("-encoding".equals(arg)) {
            ++i;
            encoding = Charset.forName(args[i]);
         } else if ("-verbose".equals(arg)) {
            verbose = true;
         } else if ("-g".equals(arg)) {
            debugSource = true;
            debugLines = true;
            debugVars = true;
         } else if (arg.startsWith("-g:")) {
            if (arg.indexOf("none") != -1) {
               debugVars = false;
               debugLines = false;
               debugSource = false;
            }

            if (arg.indexOf("source") != -1) {
               debugSource = true;
            }

            if (arg.indexOf("lines") != -1) {
               debugLines = true;
            }

            if (arg.indexOf("vars") != -1) {
               debugVars = true;
            }
         } else if ("-rebuild".equals(arg)) {
            rebuild = true;
         } else if ("-help".equals(arg)) {
            System.out.printf(USAGE, (Object[])null);
            System.exit(1);
         } else {
            System.err.println("Unrecognized command line option \"" + arg + "\"; try \"-help\".");
            System.exit(1);
         }
      }

      if (i == args.length) {
         System.err.println("No source files given on command line; try \"-help\".");
         System.exit(1);
      }

      File[] sourceFiles = new File[args.length - i];

      for(int j = i; j < args.length; ++j) {
         sourceFiles[j - i] = new File(args[j]);
      }

      ICompiler compiler = CompilerFactoryFactory.getDefaultCompilerFactory(CompilerDemo.class.getClassLoader()).newCompiler();
      compiler.setSourcePath(sourcePath);
      compiler.setClassPath(classPath);
      compiler.setExtensionDirectories(extDirs);
      if (bootClassPath != null) {
         compiler.setBootClassPath(bootClassPath);
      }

      compiler.setDestinationDirectory(destinationDirectory, rebuild);
      compiler.setEncoding(encoding);
      compiler.setVerbose(verbose);
      compiler.setDebugSource(debugSource);
      compiler.setDebugLines(debugLines);
      compiler.setDebugVars(debugVars);

      try {
         compiler.compile(sourceFiles);
      } catch (Exception e) {
         if (verbose) {
            e.printStackTrace();
         } else {
            System.err.println(e.toString());
         }

         System.exit(1);
      }

   }
}
