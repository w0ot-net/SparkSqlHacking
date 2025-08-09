package org.codehaus.commons.compiler.samples;

import java.util.Arrays;
import org.codehaus.commons.compiler.CompilerFactoryFactory;
import org.codehaus.commons.compiler.IExpressionEvaluator;
import org.codehaus.commons.compiler.util.Benchmark;

public final class ExpressionDemo extends DemoBase {
   public static void main(String[] args) throws Exception {
      Class<?> expressionType = null;
      String[] parameterNames = new String[0];
      Class<?>[] parameterTypes = new Class[0];
      Class<?>[] thrownExceptions = new Class[0];
      String[] defaultImports = new String[0];
      boolean benchmark = false;

      int i;
      for(i = 0; i < args.length; ++i) {
         String arg = args[i];
         if (!arg.startsWith("-")) {
            break;
         }

         if ("-et".equals(arg)) {
            ++i;
            expressionType = DemoBase.stringToType(args[i]);
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
         } else if ("-benchmark".equals(arg)) {
            benchmark = true;
         } else if ("-help".equals(arg)) {
            System.err.println("Usage:");
            System.err.println("  ExpressionDemo { <option> } <expression> { <parameter-value> }");
            System.err.println("Compiles and evaluates the given expression and prints its value.");
            System.err.println("Valid options are");
            System.err.println(" -et <expression-type>                        (default: any)");
            System.err.println(" -pn <comma-separated-parameter-names>        (default: none)");
            System.err.println(" -pt <comma-separated-parameter-types>        (default: none)");
            System.err.println(" -te <comma-separated-thrown-exception-types> (default: none)");
            System.err.println(" -di <comma-separated-default-imports>        (default: none)");
            System.err.println(" -benchmark                                   Report time usages");
            System.err.println(" -help");
            System.err.println("The number of parameter names, types and values must be identical.");
            System.exit(0);
         } else {
            System.err.println("Invalid command line option \"" + arg + "\"; try \"-help\".");
            System.exit(1);
         }
      }

      if (i >= args.length) {
         System.err.println("Expression missing; try \"-help\".");
         System.exit(1);
      }

      String expression = args[i++];
      if (parameterTypes.length != parameterNames.length) {
         System.err.println("Parameter type count (" + parameterTypes.length + ") and parameter name count (" + parameterNames.length + ") do not match; try \"-help\".");
         System.exit(1);
      }

      if (args.length - i != parameterNames.length) {
         System.err.println("Parameter value count (" + (args.length - i) + ") and parameter name count (" + parameterNames.length + ") do not match; try \"-help\".");
         System.exit(1);
      }

      Object[] arguments = new Object[parameterNames.length];

      for(int j = 0; j < parameterNames.length; ++j) {
         arguments[j] = DemoBase.createObject(parameterTypes[j], args[i + j]);
      }

      IExpressionEvaluator ee = CompilerFactoryFactory.getDefaultCompilerFactory(ExpressionDemo.class.getClassLoader()).newExpressionEvaluator();
      if (expressionType != null) {
         ee.setExpressionType(expressionType);
      }

      ee.setDefaultImports(defaultImports);
      ee.setParameters(parameterNames, parameterTypes);
      ee.setThrownExceptions(thrownExceptions);
      ee.cook(expression);
      Benchmark b = new Benchmark(benchmark);
      b.beginReporting();
      Object res = ee.evaluate(arguments);
      b.endReporting();
      System.out.println("Result = " + (res instanceof Object[] ? Arrays.toString(res) : String.valueOf(res)));
   }

   private ExpressionDemo() {
   }
}
