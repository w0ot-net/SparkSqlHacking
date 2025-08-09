package io.vertx.core.impl.launcher.commands;

import io.vertx.core.cli.annotations.Description;
import io.vertx.core.cli.annotations.Hidden;
import io.vertx.core.cli.annotations.Name;
import io.vertx.core.cli.annotations.Option;
import io.vertx.core.cli.annotations.Summary;
import io.vertx.core.impl.launcher.CommandLineUtils;
import io.vertx.core.spi.launcher.DefaultCommand;
import java.io.File;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Name("start")
@Summary("Start a vert.x application in background")
@Description("Start a vert.x application as a background service. The application is identified with an id that can be set using the `vertx-id` option. If not set a random UUID is generated. The application can be stopped with the `stop` command.")
public class StartCommand extends DefaultCommand {
   private String id;
   private String launcher;
   private boolean redirect;
   private String jvmOptions;

   @Option(
      longName = "vertx-id",
      shortName = "id",
      required = false,
      acceptValue = true
   )
   @Description("The id of the application, a random UUID by default")
   public void setApplicationId(String id) {
      this.id = id;
   }

   @Option(
      longName = "java-opts",
      required = false,
      acceptValue = true
   )
   @Description("Java Virtual Machine options to pass to the spawned process such as \"-Xmx1G -Xms256m -XX:MaxPermSize=256m\". If not set the `JAVA_OPTS` environment variable is used.")
   public void setJavaOptions(String options) {
      this.jvmOptions = options;
   }

   @Option(
      longName = "launcher-class"
   )
   @Hidden
   public void setLauncherClass(String clazz) {
      this.launcher = clazz;
   }

   @Option(
      longName = "redirect-output",
      flag = true
   )
   @Hidden
   public void setRedirect(boolean redirect) {
      this.redirect = redirect;
   }

   public void run() {
      this.out.println("Starting vert.x application...");
      List<String> cmd = new ArrayList();
      ProcessBuilder builder = new ProcessBuilder(new String[0]);
      this.addJavaCommand(cmd);
      List<String> cliArguments = this.getArguments();
      builder.environment().put("CLASSPATH", System.getProperty("java.class.path"));
      if (this.launcher != null) {
         ExecUtils.addArgument(cmd, this.launcher);
         Optional<String> maybeCommand = cliArguments.stream().filter((arg) -> this.executionContext.launcher().getCommandNames().contains(arg)).findFirst();
         if (!maybeCommand.isPresent()) {
            ExecUtils.addArgument(cmd, "run");
         }
      } else if (this.isLaunchedAsFatJar()) {
         ExecUtils.addArgument(cmd, "-jar");
         ExecUtils.addArgument(cmd, CommandLineUtils.getJar());
      } else {
         ExecUtils.addArgument(cmd, CommandLineUtils.getFirstSegmentOfCommand());
         ExecUtils.addArgument(cmd, "run");
      }

      cliArguments.forEach((arg) -> ExecUtils.addArgument(cmd, arg));

      try {
         builder.command(cmd);
         if (this.redirect) {
            builder.redirectError(Redirect.INHERIT);
            builder.redirectOutput(Redirect.INHERIT);
         }

         builder.start();
         this.out.println(this.id);
      } catch (Exception e) {
         this.out.println("Cannot create vert.x application process");
         e.printStackTrace(this.out);
         ExecUtils.exitBecauseOfProcessIssue();
      }

   }

   private void addJavaCommand(List cmd) {
      if (ExecUtils.isWindows()) {
         ExecUtils.addArgument(cmd, "cmd.exe");
         ExecUtils.addArgument(cmd, "/C");
         ExecUtils.addArgument(cmd, "start");
         ExecUtils.addArgument(cmd, "vertx-id - " + this.id);
         ExecUtils.addArgument(cmd, "/B");
      }

      ExecUtils.addArgument(cmd, this.getJava().getAbsolutePath());
      if (this.jvmOptions == null) {
         String opts = System.getenv("JAVA_OPTS");
         if (opts != null) {
            Arrays.stream(opts.split(" ")).forEach((s) -> ExecUtils.addArgument(cmd, s));
         }
      } else {
         Arrays.stream(this.jvmOptions.split(" ")).forEach((s) -> ExecUtils.addArgument(cmd, s));
      }

   }

   private File getJava() {
      File home = new File(System.getProperty("java.home"));
      File java;
      if (ExecUtils.isWindows()) {
         java = new File(home, "bin/java.exe");
      } else {
         java = new File(home, "bin/java");
      }

      if (!java.isFile()) {
         this.out.println("Cannot find java executable - " + java.getAbsolutePath() + " does not exist");
         ExecUtils.exitBecauseOfSystemConfigurationIssue();
      }

      return java;
   }

   private boolean isLaunchedAsFatJar() {
      return CommandLineUtils.getJar() != null;
   }

   private List getArguments() {
      List<String> args = this.executionContext.commandLine().allArguments();
      if (this.systemProperties != null) {
         this.systemProperties.stream().map((entry) -> "-D" + entry).forEach(args::add);
      }

      args.add("-Dvertx.id=" + this.getId());
      return args;
   }

   private String getId() {
      if (this.id == null) {
         this.id = UUID.randomUUID().toString();
      }

      return this.id;
   }
}
