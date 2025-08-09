package org.codehaus.commons.compiler;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import org.codehaus.commons.compiler.util.resource.ResourceFinder;
import org.codehaus.commons.nullanalysis.NotNullByDefault;
import org.codehaus.commons.nullanalysis.Nullable;

public abstract class AbstractJavaSourceClassLoader extends ClassLoader {
   @Nullable
   protected ProtectionDomainFactory protectionDomainFactory;

   public AbstractJavaSourceClassLoader() {
   }

   public AbstractJavaSourceClassLoader(ClassLoader parentClassLoader) {
      super(parentClassLoader);
   }

   @NotNullByDefault(false)
   public InputStream getResourceAsStream(String name) {
      return super.getParent().getResourceAsStream(name);
   }

   public abstract void setSourcePath(File[] var1);

   public abstract void setSourceFinder(ResourceFinder var1);

   public void setSourceFileCharacterEncoding(@Nullable String charsetName) {
      this.setSourceCharset(charsetName == null ? Charset.defaultCharset() : Charset.forName(charsetName));
   }

   public abstract void setSourceCharset(Charset var1);

   public abstract void setDebuggingInfo(boolean var1, boolean var2, boolean var3);

   public final void setProtectionDomainFactory(@Nullable ProtectionDomainFactory protectionDomainFactory) {
      this.protectionDomainFactory = protectionDomainFactory;
   }

   public static void main(String[] args) throws Exception {
      File[] sourcePath = null;
      String characterEncoding = null;
      boolean debuggingInfoLines = false;
      boolean debuggingInfoVars = false;
      boolean debuggingInfoSource = false;
      boolean haveDebuggingInfo = false;

      int i;
      for(i = 0; i < args.length; ++i) {
         String arg = args[i];
         if (!arg.startsWith("-")) {
            break;
         }

         if ("-sourcepath".equals(arg)) {
            ++i;
            sourcePath = splitPath(args[i]);
         } else if ("-encoding".equals(arg)) {
            ++i;
            characterEncoding = args[i];
         } else if ("-g".equals(arg)) {
            debuggingInfoLines = true;
            debuggingInfoVars = true;
            debuggingInfoSource = true;
            haveDebuggingInfo = true;
         } else if ("-g:none".equals(arg)) {
            debuggingInfoLines = false;
            debuggingInfoVars = false;
            debuggingInfoSource = false;
            haveDebuggingInfo = true;
         } else if ("-g:".startsWith(arg)) {
            debuggingInfoLines = arg.indexOf("lines") != -1;
            debuggingInfoVars = arg.indexOf("vars") != -1;
            debuggingInfoSource = arg.indexOf("source") != -1;
            haveDebuggingInfo = true;
         } else if ("-help".equals(arg)) {
            System.out.println("Usage:");
            System.out.println("  java " + AbstractJavaSourceClassLoader.class.getName() + " { <option> } <class-name> { <argument> }");
            System.out.println("Loads the named class by name and invoke its \"main(String[])\" method, passing");
            System.out.println("the given <argument>s.");
            System.out.println("  <option>:");
            System.out.println("    -sourcepath <" + File.pathSeparator + "-separated-list-of-source-directories>");
            System.out.println("    -encoding <character-encoding>");
            System.out.println("    -g                     Generate all debugging info");
            System.out.println("    -g:none                Generate no debugging info");
            System.out.println("    -g:{source,lines,vars} Generate only some debugging info");
            System.exit(0);
         } else {
            System.err.println("Invalid command line option \"" + arg + "\"; try \"-help\"");
            System.exit(1);
         }
      }

      if (i == args.length) {
         System.err.println("No class name given, try \"-help\"");
         System.exit(1);
      }

      String className = args[i++];
      String[] mainArgs = new String[args.length - i];
      System.arraycopy(args, i, mainArgs, 0, args.length - i);
      AbstractJavaSourceClassLoader ajscl = CompilerFactoryFactory.getDefaultCompilerFactory(AbstractJavaSourceClassLoader.class.getClassLoader()).newJavaSourceClassLoader();
      if (haveDebuggingInfo) {
         ajscl.setDebuggingInfo(debuggingInfoLines, debuggingInfoVars, debuggingInfoSource);
      }

      if (characterEncoding != null) {
         ajscl.setSourceFileCharacterEncoding(characterEncoding);
      }

      if (sourcePath != null) {
         ajscl.setSourcePath(sourcePath);
      }

      Class<?> clazz = ajscl.loadClass(className);

      Method mainMethod;
      try {
         mainMethod = clazz.getMethod("main", String[].class);
      } catch (NoSuchMethodException var14) {
         System.err.println("Class \"" + className + "\" has not public method \"main(String[])\".");
         System.exit(1);
         return;
      }

      mainMethod.invoke((Object)null, mainArgs);
   }

   private static File[] splitPath(String string) {
      List<File> l = new ArrayList();
      StringTokenizer st = new StringTokenizer(string, File.pathSeparator);

      while(st.hasMoreTokens()) {
         l.add(new File(st.nextToken()));
      }

      return (File[])l.toArray(new File[l.size()]);
   }

   public interface ProtectionDomainFactory {
      ProtectionDomain getProtectionDomain(String var1);
   }
}
