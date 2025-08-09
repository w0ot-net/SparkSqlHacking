package org.codehaus.commons.compiler.samples;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Method;
import java.util.Arrays;
import org.codehaus.commons.compiler.CompilerFactoryFactory;
import org.codehaus.commons.compiler.IClassBodyEvaluator;

public final class ClassBodyDemo {
   public static void main(String[] args) throws Exception {
      if (args.length > 0 && "-help".equals(args[0])) {
         System.out.println("Usage:");
         System.out.println("  ClassBodyDemo <class-body> { <argument> }");
         System.out.println("  ClassBodyDemo -help");
         System.out.println("If <class-body> starts with a '@', then the class body is read");
         System.out.println("from the named file.");
         System.out.println("The <class-body> must declare a method \"public static void main(String[])\"");
         System.out.println("to which the <argument>s are passed. If the return type of that method is");
         System.out.println("not VOID, then the returned value is printed to STDOUT.");
         System.exit(0);
      }

      int i = 0;
      if (i >= args.length) {
         System.err.println("Class body missing; try \"-help\".");
      }

      String classBody = args[i++];
      if (classBody.startsWith("@")) {
         classBody = readFileToString(classBody.substring(1));
      }

      String[] arguments = new String[args.length - i];
      System.arraycopy(args, i, arguments, 0, arguments.length);
      IClassBodyEvaluator cbe = CompilerFactoryFactory.getDefaultCompilerFactory(ClassBodyDemo.class.getClassLoader()).newClassBodyEvaluator();
      cbe.cook(classBody);
      Class<?> c = cbe.getClazz();
      Method m = c.getMethod("main", String[].class);
      Object returnValue = m.invoke((Object)null, arguments);
      if (m.getReturnType() != Void.TYPE) {
         System.out.println(returnValue instanceof Object[] ? Arrays.toString(returnValue) : String.valueOf(returnValue));
      }

   }

   private ClassBodyDemo() {
   }

   private static String readFileToString(String fileName) throws IOException {
      Reader r = new FileReader(fileName);

      try {
         StringBuilder sb = new StringBuilder();
         char[] ca = new char[1024];

         while(true) {
            int cnt = r.read(ca, 0, ca.length);
            if (cnt == -1) {
               String var8 = sb.toString();
               return var8;
            }

            sb.append(ca, 0, cnt);
         }
      } finally {
         r.close();
      }
   }
}
