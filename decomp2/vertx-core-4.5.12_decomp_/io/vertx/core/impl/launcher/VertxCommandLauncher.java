package io.vertx.core.impl.launcher;

import io.vertx.core.cli.CLI;
import io.vertx.core.cli.CLIException;
import io.vertx.core.cli.CommandLine;
import io.vertx.core.cli.InvalidValueException;
import io.vertx.core.cli.MissingOptionException;
import io.vertx.core.cli.MissingValueException;
import io.vertx.core.cli.UsageMessageFormatter;
import io.vertx.core.cli.annotations.CLIConfigurator;
import io.vertx.core.impl.launcher.commands.RunCommand;
import io.vertx.core.spi.launcher.Command;
import io.vertx.core.spi.launcher.CommandFactory;
import io.vertx.core.spi.launcher.CommandFactoryLookup;
import io.vertx.core.spi.launcher.DefaultCommandFactory;
import io.vertx.core.spi.launcher.ExecutionContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Supplier;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import java.util.stream.Collectors;

public class VertxCommandLauncher extends UsageMessageFormatter {
   protected static List PROCESS_ARGS;
   protected final List lookups;
   protected final Map commandByName;
   protected Object main;

   public static List getProcessArguments() {
      return PROCESS_ARGS;
   }

   public VertxCommandLauncher() {
      this(Collections.singletonList(new ServiceCommandFactoryLoader()));
   }

   public VertxCommandLauncher(Collection lookups) {
      this.lookups = new ArrayList(lookups);
      this.commandByName = new TreeMap();
      this.load();
   }

   protected void load() {
      for(CommandFactoryLookup lookup : this.lookups) {
         Collection<CommandFactory<?>> commands = lookup.lookup();
         commands.forEach((factory) -> {
            CLI cli = factory.define();
            CommandRegistration previous = (CommandRegistration)this.commandByName.get(cli.getName());
            if (previous == null) {
               this.commandByName.put(cli.getName(), new CommandRegistration(factory, cli));
            } else if (cli.getPriority() > previous.cli.getPriority()) {
               this.commandByName.put(cli.getName(), new CommandRegistration(factory, cli));
            }

         });
      }

   }

   public VertxCommandLauncher register(CommandFactory factory) {
      CLI cli = factory.define();
      this.commandByName.put(cli.getName(), new CommandRegistration(factory, cli));
      return this;
   }

   /** @deprecated */
   @Deprecated
   public VertxCommandLauncher register(Class clazz) {
      DefaultCommandFactory factory = new DefaultCommandFactory(clazz);
      CLI cli = factory.define();
      this.commandByName.put(cli.getName(), new CommandRegistration(factory, cli));
      return this;
   }

   public VertxCommandLauncher register(Class clazz, Supplier supplier) {
      DefaultCommandFactory factory = new DefaultCommandFactory(clazz, supplier);
      CLI cli = factory.define();
      this.commandByName.put(cli.getName(), new CommandRegistration(factory, cli));
      return this;
   }

   public VertxCommandLauncher unregister(String name) {
      this.commandByName.remove(name);
      return this;
   }

   public Collection getCommandNames() {
      return this.commandByName.keySet();
   }

   protected Command getNewCommandInstance(String name, CommandLine commandLine) {
      CommandRegistration registration = (CommandRegistration)this.commandByName.get(name);
      if (registration != null) {
         Command command = registration.factory.create(commandLine);
         registration.addCommand(command);
         return command;
      } else {
         return null;
      }
   }

   public Command getExistingCommandInstance(String name) {
      CommandRegistration registration = (CommandRegistration)this.commandByName.get(name);
      return registration != null ? registration.getCommand() : null;
   }

   public void execute(String command, String... cla) {
      if (command != null && isAskingForVersion(command)) {
         this.execute("version");
      } else if (command != null && !isAskingForHelp(command)) {
         CommandRegistration registration = (CommandRegistration)this.commandByName.get(command);
         if (registration == null) {
            this.printCommandNotFound(command);
         } else {
            CLI cli = registration.cli;

            try {
               if (cla.length >= 1 && isAskingForHelp(cla[0])) {
                  this.printCommandUsage(cli);
                  return;
               }

               CommandLine evaluated = cli.parse(Arrays.asList(cla));
               Command cmd = this.getNewCommandInstance(command, evaluated);
               ExecutionContext context = new ExecutionContext(cmd, this, evaluated);
               if (this.main != null) {
                  context.put("Main", this.main);
                  context.put("Main-Class", this.main.getClass().getName());
                  context.put("Default-Verticle-Factory", this.getFromManifest("Default-Verticle-Factory"));
               }

               CLIConfigurator.inject(evaluated, cmd);
               cmd.setUp(context);
               cmd.run();
               cmd.tearDown();
            } catch (MissingValueException | InvalidValueException | MissingOptionException e) {
               this.printSpecificException(cli, e);
            } catch (CLIException e) {
               this.printGenericExecutionError(cli, e);
            } catch (RuntimeException e) {
               if (e.getCause() instanceof CLIException) {
                  this.printGenericExecutionError(cli, (CLIException)e.getCause());
                  return;
               }

               throw e;
            }

         }
      } else {
         this.printGlobalUsage();
      }
   }

   protected void printCommandUsage(CLI cli) {
      StringBuilder builder = new StringBuilder();
      cli.usage(builder, this.getCommandLinePrefix());
      this.getPrintStream().println(builder.toString());
   }

   protected void printGenericExecutionError(CLI cli, CLIException e) {
      this.getPrintStream().println("Error while executing command " + cli.getName() + ": " + e.getMessage() + this.getNewLine());
      if (e.getCause() != null) {
         e.getCause().printStackTrace(this.getPrintStream());
      }

   }

   protected void printSpecificException(CLI cli, Exception e) {
      this.getPrintStream().println(e.getMessage() + this.getNewLine());
      this.printCommandUsage(cli);
   }

   protected void printCommandNotFound(String command) {
      StringBuilder builder = new StringBuilder();
      this.buildWrapped(builder, 0, "The command '" + command + "' is not a valid command." + this.getNewLine() + "See '" + this.getCommandLinePrefix() + " --help'");
      this.getPrintStream().println(builder.toString());
   }

   protected void printGlobalUsage() {
      StringBuilder builder = new StringBuilder();
      this.computeUsage(builder, this.getCommandLinePrefix() + " [COMMAND] [OPTIONS] [arg...]");
      builder.append(this.getNewLine());
      builder.append("Commands:").append(this.getNewLine());
      this.renderCommands(builder, (Collection)this.commandByName.values().stream().map((r) -> r.cli).collect(Collectors.toList()));
      builder.append(this.getNewLine()).append(this.getNewLine());
      this.buildWrapped(builder, 0, "Run '" + this.getCommandLinePrefix() + " COMMAND --help' for more information on a command.");
      this.getPrintStream().println(builder.toString());
   }

   protected String getCommandLinePrefix() {
      String sysProp = System.getProperty("vertx.cli.usage.prefix");
      if (sysProp != null) {
         return sysProp;
      } else {
         String jar = CommandLineUtils.getJar();
         if (jar != null) {
            return "java -jar " + jar;
         } else {
            String command = CommandLineUtils.getFirstSegmentOfCommand();
            return command != null ? "java " + command : "vertx";
         }
      }
   }

   protected static boolean isAskingForHelp(String command) {
      return command.equalsIgnoreCase("--help") || command.equalsIgnoreCase("-help") || command.equalsIgnoreCase("-h") || command.equalsIgnoreCase("?") || command.equalsIgnoreCase("/?");
   }

   protected static boolean isAskingForVersion(String command) {
      return command.equalsIgnoreCase("-version") || command.equalsIgnoreCase("--version");
   }

   public void dispatch(String[] args) {
      this.dispatch((Object)null, args);
   }

   public void dispatch(Object main, String[] args) {
      this.main = main == null ? this : main;
      PROCESS_ARGS = Collections.unmodifiableList(Arrays.asList(args));
      if (args.length >= 1 && isAskingForHelp(args[0])) {
         this.printGlobalUsage();
      } else if (args.length >= 1 && isAskingForVersion(args[0])) {
         this.execute("version");
      } else if (args.length >= 1 && this.commandByName.get(args[0]) != null) {
         this.execute(args[0], (String[])Arrays.copyOfRange(args, 1, args.length));
      } else if (args.length >= 2 && isAskingForHelp(args[1])) {
         this.execute(args[0], "--help");
      } else {
         String verticle = this.getMainVerticle();
         String command = this.getCommandFromManifest();
         if (verticle != null) {
            String[] newArgs = new String[args.length + 1];
            newArgs[0] = verticle;
            System.arraycopy(args, 0, newArgs, 1, args.length);
            this.execute(this.getDefaultCommand(), newArgs);
         } else if (command != null) {
            this.execute(command, args);
         } else {
            if (args.length == 0) {
               this.printGlobalUsage();
            } else if (args[0].equalsIgnoreCase("-ha")) {
               this.execute("bare", (String[])Arrays.copyOfRange(args, 1, args.length));
            } else {
               this.printCommandNotFound(args[0]);
            }

         }
      }
   }

   protected String getDefaultCommand() {
      String fromManifest = this.getCommandFromManifest();
      return fromManifest == null ? "run" : fromManifest;
   }

   protected String getCommandFromManifest() {
      return this.getFromManifest("Main-Command");
   }

   private String getFromManifest(String key) {
      try {
         Enumeration<URL> resources = RunCommand.class.getClassLoader().getResources("META-INF/MANIFEST.MF");

         while(true) {
            if (resources.hasMoreElements()) {
               InputStream stream = ((URL)resources.nextElement()).openStream();
               Throwable var4 = null;

               String var9;
               try {
                  Manifest manifest = new Manifest(stream);
                  Attributes attributes = manifest.getMainAttributes();
                  String mainClass = attributes.getValue("Main-Class");
                  if (!this.main.getClass().getName().equals(mainClass)) {
                     continue;
                  }

                  String value = attributes.getValue(key);
                  if (value == null) {
                     continue;
                  }

                  var9 = value;
               } catch (Throwable var20) {
                  var4 = var20;
                  throw var20;
               } finally {
                  if (stream != null) {
                     if (var4 != null) {
                        try {
                           stream.close();
                        } catch (Throwable var19) {
                           var4.addSuppressed(var19);
                        }
                     } else {
                        stream.close();
                     }
                  }

               }

               return var9;
            }

            return null;
         }
      } catch (IOException e) {
         throw new IllegalStateException(e.getMessage());
      }
   }

   public PrintStream getPrintStream() {
      return System.out;
   }

   protected String getMainVerticle() {
      return this.getFromManifest("Main-Verticle");
   }

   public static void resetProcessArguments() {
      PROCESS_ARGS = null;
   }

   public static class CommandRegistration {
      public final CommandFactory factory;
      public final CLI cli;
      private List commands;

      public CommandRegistration(CommandFactory factory) {
         this(factory, factory.define());
      }

      public CommandRegistration(CommandFactory factory, CLI cli) {
         this.commands = new ArrayList();
         this.factory = factory;
         this.cli = cli;
      }

      public void addCommand(Command command) {
         this.commands.add(command);
      }

      public Command getCommand() {
         return !this.commands.isEmpty() ? (Command)this.commands.get(0) : null;
      }

      public List getCommands() {
         return this.commands;
      }
   }
}
