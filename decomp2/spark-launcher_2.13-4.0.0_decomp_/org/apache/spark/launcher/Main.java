package org.apache.spark.launcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

class Main {
   public static void main(String[] argsArray) throws Exception {
      CommandBuilderUtils.checkArgument(argsArray.length > 0, "Not enough arguments: missing class name.");
      List<String> args = new ArrayList(Arrays.asList(argsArray));
      String className = (String)args.remove(0);
      boolean printLaunchCommand = !CommandBuilderUtils.isEmpty(System.getenv("SPARK_PRINT_LAUNCH_COMMAND"));
      Map<String, String> env = new HashMap();
      List<String> cmd;
      if (className.equals("org.apache.spark.deploy.SparkSubmit")) {
         try {
            AbstractCommandBuilder builder = new SparkSubmitCommandBuilder(args);
            cmd = buildCommand(builder, env, printLaunchCommand);
         } catch (IllegalArgumentException e) {
            printLaunchCommand = false;
            System.err.println("Error: " + e.getMessage());
            System.err.println();
            MainClassOptionParser parser = new MainClassOptionParser();

            try {
               parser.parse(args);
            } catch (Exception var10) {
            }

            List<String> help = new ArrayList();
            if (parser.className != null) {
               Objects.requireNonNull(parser);
               help.add("--class");
               help.add(parser.className);
            }

            Objects.requireNonNull(parser);
            help.add("--usage-error");
            AbstractCommandBuilder builder = new SparkSubmitCommandBuilder(help);
            cmd = buildCommand(builder, env, printLaunchCommand);
         }
      } else {
         AbstractCommandBuilder builder = new SparkClassCommandBuilder(className, args);
         cmd = buildCommand(builder, env, printLaunchCommand);
      }

      boolean shellflag = !CommandBuilderUtils.isEmpty(System.getenv("SHELL"));
      if (CommandBuilderUtils.isWindows() && !shellflag) {
         System.out.println(prepareWindowsCommand(cmd, env));
      } else {
         System.out.println('\u0000');

         for(String c : prepareBashCommand(cmd, env)) {
            System.out.print(c.replaceFirst("\r$", ""));
            System.out.print('\u0000');
         }
      }

   }

   private static List buildCommand(AbstractCommandBuilder builder, Map env, boolean printLaunchCommand) throws IOException, IllegalArgumentException {
      List<String> cmd = builder.buildCommand(env);
      if (printLaunchCommand) {
         System.err.println("Spark Command: " + CommandBuilderUtils.join(" ", (Iterable)CommandBuilderUtils.redactCommandLineArgs(cmd)));
         System.err.println("========================================");
      }

      return cmd;
   }

   private static String prepareWindowsCommand(List cmd, Map childEnv) {
      StringBuilder cmdline = new StringBuilder();

      for(Map.Entry e : childEnv.entrySet()) {
         cmdline.append(String.format("set %s=%s", e.getKey(), e.getValue()));
         cmdline.append(" && ");
      }

      for(String arg : cmd) {
         cmdline.append(CommandBuilderUtils.quoteForBatchScript(arg));
         cmdline.append(" ");
      }

      return cmdline.toString();
   }

   private static List prepareBashCommand(List cmd, Map childEnv) {
      if (childEnv.isEmpty()) {
         return cmd;
      } else {
         List<String> newCmd = new ArrayList();
         newCmd.add("env");

         for(Map.Entry e : childEnv.entrySet()) {
            newCmd.add(String.format("%s=%s", e.getKey(), e.getValue()));
         }

         newCmd.addAll(cmd);
         return newCmd;
      }
   }

   private static class MainClassOptionParser extends SparkSubmitOptionParser {
      String className;

      protected boolean handle(String opt, String value) {
         if ("--class".equals(opt)) {
            this.className = value;
         }

         return false;
      }

      protected boolean handleUnknown(String opt) {
         return false;
      }

      protected void handleExtraArgs(List extra) {
      }
   }
}
