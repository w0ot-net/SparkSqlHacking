package org.codehaus.commons.compiler.samples;

import java.util.Arrays;
import org.codehaus.commons.compiler.CompilerFactoryFactory;
import org.codehaus.commons.compiler.IScriptEvaluator;

public final class ScriptDemo extends DemoBase {
   public static void main(String[] args) throws Exception {
      Class<?> returnType = Void.TYPE;
      String[] parameterNames = new String[0];
      Class<?>[] parameterTypes = new Class[0];
      Class<?>[] thrownExceptions = new Class[0];
      String[] defaultImports = new String[0];

      int i;
      for(i = 0; i < args.length; ++i) {
         String arg = args[i];
         if (!arg.startsWith("-")) {
            break;
         }

         if ("-rt".equals(arg)) {
            ++i;
            returnType = DemoBase.stringToType(args[i]);
         } else if ("-pn".equals(arg)) {
            ++i;
            parameterNames = DemoBase.explode(args[i]);
         } else if ("-pt".equals(arg)) {
            ++i;
            parameterTypes = DemoBase.stringToTypes(args[i]);
         } else if ("-te".equals(arg)) {
            ++i;
            thrownExceptions = DemoBase.stringToTypes(args[i]);
         } else if ("-di".equals(arg)) {
            ++i;
            defaultImports = DemoBase.explode(args[i]);
         } else if ("-help".equals(arg)) {
            System.err.println("Usage:");
            System.err.println("  ScriptDemo { <option> } <script> { <parameter-value> }");
            System.err.println("Valid options are");
            System.err.println(" -rt <return-type>                            (default: void)");
            System.err.println(" -pn <comma-separated-parameter-names>        (default: none)");
            System.err.println(" -pt <comma-separated-parameter-types>        (default: none)");
            System.err.println(" -te <comma-separated-thrown-exception-types> (default: none)");
            System.err.println(" -di <comma-separated-default-imports>        (default: none)");
            System.err.println(" -help");
            System.err.println("The number of parameter names, types and values must be identical.");
            System.exit(0);
         } else {
            System.err.println("Invalid command line option \"" + arg + "\"; try \"-help\".");
            System.exit(1);
         }
      }

      if (i == args.length) {
         System.err.println("Script missing on command line; try \"-help\".");
         System.exit(1);
      }

      String script = args[i++];
      if (parameterTypes.length != parameterNames.length) {
         System.err.println("Parameter type count and parameter name count do not match; try \"-help\".");
         System.exit(1);
      }

      if (args.length - i != parameterNames.length) {
         System.err.println("Argument and parameter count do not match; try \"-help\".");
         System.exit(1);
      }

      Object[] arguments = new Object[parameterNames.length];

      for(int j = 0; j < parameterNames.length; ++j) {
         arguments[j] = DemoBase.createObject(parameterTypes[j], args[i + j]);
      }

      IScriptEvaluator se = CompilerFactoryFactory.getDefaultCompilerFactory(ScriptDemo.class.getClassLoader()).newScriptEvaluator();
      se.setReturnType(returnType);
      se.setDefaultImports(defaultImports);
      se.setParameters(parameterNames, parameterTypes);
      se.setThrownExceptions(thrownExceptions);
      se.cook((String)script);
      Object res = se.evaluate(arguments);
      System.out.println("Result = " + (res instanceof Object[] ? Arrays.toString(res) : String.valueOf(res)));
   }

   private ScriptDemo() {
   }
}
