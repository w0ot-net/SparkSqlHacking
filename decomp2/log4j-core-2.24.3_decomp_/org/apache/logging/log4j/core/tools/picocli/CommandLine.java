package org.apache.logging.log4j.core.tools.picocli;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.File;
import java.io.PrintStream;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Time;
import java.text.BreakIterator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.regex.Pattern;
import org.apache.logging.log4j.core.util.Integers;
import org.apache.logging.log4j.util.Strings;

public class CommandLine {
   public static final String VERSION = "2.0.3";
   private final Tracer tracer = new Tracer();
   private final Interpreter interpreter;
   private String commandName = "<main class>";
   private boolean overwrittenOptionsAllowed = false;
   private boolean unmatchedArgumentsAllowed = false;
   private final List unmatchedArguments = new ArrayList();
   private CommandLine parent;
   private boolean usageHelpRequested;
   private boolean versionHelpRequested;
   private final List versionLines = new ArrayList();

   public CommandLine(final Object command) {
      this.interpreter = new Interpreter(command);
   }

   public CommandLine addSubcommand(final String name, final Object command) {
      CommandLine commandLine = toCommandLine(command);
      commandLine.parent = this;
      this.interpreter.commands.put(name, commandLine);
      return this;
   }

   public Map getSubcommands() {
      return new LinkedHashMap(this.interpreter.commands);
   }

   public CommandLine getParent() {
      return this.parent;
   }

   public Object getCommand() {
      return this.interpreter.command;
   }

   public boolean isUsageHelpRequested() {
      return this.usageHelpRequested;
   }

   public boolean isVersionHelpRequested() {
      return this.versionHelpRequested;
   }

   public boolean isOverwrittenOptionsAllowed() {
      return this.overwrittenOptionsAllowed;
   }

   public CommandLine setOverwrittenOptionsAllowed(final boolean newValue) {
      this.overwrittenOptionsAllowed = newValue;

      for(CommandLine command : this.interpreter.commands.values()) {
         command.setOverwrittenOptionsAllowed(newValue);
      }

      return this;
   }

   public boolean isUnmatchedArgumentsAllowed() {
      return this.unmatchedArgumentsAllowed;
   }

   public CommandLine setUnmatchedArgumentsAllowed(final boolean newValue) {
      this.unmatchedArgumentsAllowed = newValue;

      for(CommandLine command : this.interpreter.commands.values()) {
         command.setUnmatchedArgumentsAllowed(newValue);
      }

      return this;
   }

   public List getUnmatchedArguments() {
      return this.unmatchedArguments;
   }

   public static Object populateCommand(final Object command, final String... args) {
      CommandLine cli = toCommandLine(command);
      cli.parse(args);
      return command;
   }

   public List parse(final String... args) {
      return this.interpreter.parse(args);
   }

   public static boolean printHelpIfRequested(final List parsedCommands, final PrintStream out, final Help.Ansi ansi) {
      for(CommandLine parsed : parsedCommands) {
         if (parsed.isUsageHelpRequested()) {
            parsed.usage(out, ansi);
            return true;
         }

         if (parsed.isVersionHelpRequested()) {
            parsed.printVersionHelp(out, ansi);
            return true;
         }
      }

      return false;
   }

   private static Object execute(final CommandLine parsed) {
      Object command = parsed.getCommand();
      if (command instanceof Runnable) {
         try {
            ((Runnable)command).run();
            return null;
         } catch (Exception ex) {
            throw new ExecutionException(parsed, "Error while running command (" + command + ")", ex);
         }
      } else if (command instanceof Callable) {
         try {
            return ((Callable)command).call();
         } catch (Exception ex) {
            throw new ExecutionException(parsed, "Error while calling command (" + command + ")", ex);
         }
      } else {
         throw new ExecutionException(parsed, "Parsed command (" + command + ") is not Runnable or Callable");
      }
   }

   public List parseWithHandler(final IParseResultHandler handler, final PrintStream out, final String... args) {
      return this.parseWithHandlers(handler, out, CommandLine.Help.Ansi.AUTO, new DefaultExceptionHandler(), args);
   }

   public List parseWithHandlers(final IParseResultHandler handler, final PrintStream out, final Help.Ansi ansi, final IExceptionHandler exceptionHandler, final String... args) {
      try {
         List<CommandLine> result = this.parse(args);
         return handler.handleParseResult(result, out, ansi);
      } catch (ParameterException ex) {
         return exceptionHandler.handleException(ex, out, ansi, args);
      }
   }

   public static void usage(final Object command, final PrintStream out) {
      toCommandLine(command).usage(out);
   }

   public static void usage(final Object command, final PrintStream out, final Help.Ansi ansi) {
      toCommandLine(command).usage(out, ansi);
   }

   public static void usage(final Object command, final PrintStream out, final Help.ColorScheme colorScheme) {
      toCommandLine(command).usage(out, colorScheme);
   }

   public void usage(final PrintStream out) {
      this.usage(out, CommandLine.Help.Ansi.AUTO);
   }

   public void usage(final PrintStream out, final Help.Ansi ansi) {
      this.usage(out, CommandLine.Help.defaultColorScheme(ansi));
   }

   public void usage(final PrintStream out, final Help.ColorScheme colorScheme) {
      Help help = (new Help(this.interpreter.command, colorScheme)).addAllSubcommands(this.getSubcommands());
      if (!"=".equals(this.getSeparator())) {
         help.separator = this.getSeparator();
         help.parameterLabelRenderer = help.createDefaultParamLabelRenderer();
      }

      if (!"<main class>".equals(this.getCommandName())) {
         help.commandName = this.getCommandName();
      }

      StringBuilder sb = (new StringBuilder()).append(help.headerHeading()).append(help.header()).append(help.synopsisHeading()).append(help.synopsis(help.synopsisHeadingLength())).append(help.descriptionHeading()).append(help.description()).append(help.parameterListHeading()).append(help.parameterList()).append(help.optionListHeading()).append(help.optionList()).append(help.commandListHeading()).append(help.commandList()).append(help.footerHeading()).append(help.footer());
      out.print(sb);
   }

   public void printVersionHelp(final PrintStream out) {
      this.printVersionHelp(out, CommandLine.Help.Ansi.AUTO);
   }

   public void printVersionHelp(final PrintStream out, final Help.Ansi ansi) {
      for(String versionInfo : this.versionLines) {
         Objects.requireNonNull(ansi);
         out.println(ansi.new Text(versionInfo));
      }

   }

   public void printVersionHelp(final PrintStream out, final Help.Ansi ansi, final Object... params) {
      for(String versionInfo : this.versionLines) {
         Objects.requireNonNull(ansi);
         out.println(ansi.new Text(String.format(versionInfo, params)));
      }

   }

   public static Object call(final Callable callable, final PrintStream out, final String... args) {
      return call(callable, out, CommandLine.Help.Ansi.AUTO, args);
   }

   public static Object call(final Callable callable, final PrintStream out, final Help.Ansi ansi, final String... args) {
      CommandLine cmd = new CommandLine(callable);
      List<Object> results = cmd.parseWithHandlers(new RunLast(), out, ansi, new DefaultExceptionHandler(), args);
      return results != null && !results.isEmpty() ? results.get(0) : null;
   }

   public static void run(final Runnable runnable, final PrintStream out, final String... args) {
      run(runnable, out, CommandLine.Help.Ansi.AUTO, args);
   }

   public static void run(final Runnable runnable, final PrintStream out, final Help.Ansi ansi, final String... args) {
      CommandLine cmd = new CommandLine(runnable);
      cmd.parseWithHandlers(new RunLast(), out, ansi, new DefaultExceptionHandler(), args);
   }

   public CommandLine registerConverter(final Class cls, final ITypeConverter converter) {
      this.interpreter.converterRegistry.put((Class)CommandLine.Assert.notNull(cls, "class"), (ITypeConverter)CommandLine.Assert.notNull(converter, "converter"));

      for(CommandLine command : this.interpreter.commands.values()) {
         command.registerConverter(cls, converter);
      }

      return this;
   }

   public String getSeparator() {
      return this.interpreter.separator;
   }

   public CommandLine setSeparator(final String separator) {
      this.interpreter.separator = (String)CommandLine.Assert.notNull(separator, "separator");
      return this;
   }

   public String getCommandName() {
      return this.commandName;
   }

   public CommandLine setCommandName(final String commandName) {
      this.commandName = (String)CommandLine.Assert.notNull(commandName, "commandName");
      return this;
   }

   private static boolean empty(final String str) {
      return str == null || str.trim().length() == 0;
   }

   private static boolean empty(final Object[] array) {
      return array == null || array.length == 0;
   }

   private static boolean empty(final Help.Ansi.Text txt) {
      return txt == null || txt.plain.toString().trim().length() == 0;
   }

   private static String str(final String[] arr, final int i) {
      return arr != null && arr.length != 0 ? arr[i] : "";
   }

   private static boolean isBoolean(final Class type) {
      return type == Boolean.class || type == Boolean.TYPE;
   }

   private static CommandLine toCommandLine(final Object obj) {
      return obj instanceof CommandLine ? (CommandLine)obj : new CommandLine(obj);
   }

   private static boolean isMultiValue(final Field field) {
      return isMultiValue(field.getType());
   }

   private static boolean isMultiValue(final Class cls) {
      return cls.isArray() || Collection.class.isAssignableFrom(cls) || Map.class.isAssignableFrom(cls);
   }

   private static Class[] getTypeAttribute(final Field field) {
      Class<?>[] explicit = field.isAnnotationPresent(Parameters.class) ? ((Parameters)field.getAnnotation(Parameters.class)).type() : ((Option)field.getAnnotation(Option.class)).type();
      if (explicit.length > 0) {
         return explicit;
      } else if (field.getType().isArray()) {
         return new Class[]{field.getType().getComponentType()};
      } else if (!isMultiValue(field)) {
         return new Class[]{field.getType()};
      } else {
         Type type = field.getGenericType();
         if (!(type instanceof ParameterizedType)) {
            return new Class[]{String.class, String.class};
         } else {
            ParameterizedType parameterizedType = (ParameterizedType)type;
            Type[] paramTypes = parameterizedType.getActualTypeArguments();
            Class<?>[] result = new Class[paramTypes.length];
            int i = 0;

            while(true) {
               if (i >= paramTypes.length) {
                  return result;
               }

               if (paramTypes[i] instanceof Class) {
                  result[i] = (Class)paramTypes[i];
               } else {
                  if (!(paramTypes[i] instanceof WildcardType)) {
                     break;
                  }

                  WildcardType wildcardType = (WildcardType)paramTypes[i];
                  Type[] lower = wildcardType.getLowerBounds();
                  if (lower.length > 0 && lower[0] instanceof Class) {
                     result[i] = (Class)lower[0];
                  } else {
                     Type[] upper = wildcardType.getUpperBounds();
                     if (upper.length <= 0 || !(upper[0] instanceof Class)) {
                        break;
                     }

                     result[i] = (Class)upper[0];
                  }
               }

               ++i;
            }

            Arrays.fill(result, String.class);
            return result;
         }
      }
   }

   static void init(final Class cls, final List requiredFields, final Map optionName2Field, final Map singleCharOption2Field, final List positionalParametersFields) {
      Field[] declaredFields = cls.getDeclaredFields();

      for(Field field : declaredFields) {
         field.setAccessible(true);
         if (field.isAnnotationPresent(Option.class)) {
            Option option = (Option)field.getAnnotation(Option.class);
            if (option.required()) {
               requiredFields.add(field);
            }

            for(String name : option.names()) {
               Field existing = (Field)optionName2Field.put(name, field);
               if (existing != null && existing != field) {
                  throw CommandLine.DuplicateOptionAnnotationsException.create(name, field, existing);
               }

               if (name.length() == 2 && name.startsWith("-")) {
                  char flag = name.charAt(1);
                  Field existing2 = (Field)singleCharOption2Field.put(flag, field);
                  if (existing2 != null && existing2 != field) {
                     throw CommandLine.DuplicateOptionAnnotationsException.create(name, field, existing2);
                  }
               }
            }
         }

         if (field.isAnnotationPresent(Parameters.class)) {
            if (field.isAnnotationPresent(Option.class)) {
               throw new DuplicateOptionAnnotationsException("A field can be either @Option or @Parameters, but '" + field.getName() + "' is both.");
            }

            positionalParametersFields.add(field);
            Range arity = CommandLine.Range.parameterArity(field);
            if (arity.min > 0) {
               requiredFields.add(field);
            }
         }
      }

   }

   static void validatePositionalParameters(final List positionalParametersFields) {
      int min = 0;

      for(Field field : positionalParametersFields) {
         Range index = CommandLine.Range.parameterIndex(field);
         if (index.min > min) {
            throw new ParameterIndexGapException("Missing field annotated with @Parameter(index=" + min + "). Nearest field '" + field.getName() + "' has index=" + index.min);
         }

         min = Math.max(min, index.max);
         min = min == Integer.MAX_VALUE ? min : min + 1;
      }

   }

   private static Stack reverse(final Stack stack) {
      Collections.reverse(stack);
      return stack;
   }

   // $FF: synthetic method
   static boolean access$1776(CommandLine x0, int x1) {
      return x0.versionHelpRequested = (boolean)((byte)(x0.versionHelpRequested | x1));
   }

   // $FF: synthetic method
   static boolean access$1876(CommandLine x0, int x1) {
      return x0.usageHelpRequested = (boolean)((byte)(x0.usageHelpRequested | x1));
   }

   public static class DefaultExceptionHandler implements IExceptionHandler {
      public List handleException(final ParameterException ex, final PrintStream out, final Help.Ansi ansi, final String... args) {
         out.println(ex.getMessage());
         ex.getCommandLine().usage(out, ansi);
         return Collections.emptyList();
      }
   }

   public static class RunFirst implements IParseResultHandler {
      public List handleParseResult(final List parsedCommands, final PrintStream out, final Help.Ansi ansi) {
         return CommandLine.printHelpIfRequested(parsedCommands, out, ansi) ? Collections.emptyList() : Arrays.asList(CommandLine.execute((CommandLine)parsedCommands.get(0)));
      }
   }

   public static class RunLast implements IParseResultHandler {
      public List handleParseResult(final List parsedCommands, final PrintStream out, final Help.Ansi ansi) {
         if (CommandLine.printHelpIfRequested(parsedCommands, out, ansi)) {
            return Collections.emptyList();
         } else {
            CommandLine last = (CommandLine)parsedCommands.get(parsedCommands.size() - 1);
            return Arrays.asList(CommandLine.execute(last));
         }
      }
   }

   public static class RunAll implements IParseResultHandler {
      public List handleParseResult(final List parsedCommands, final PrintStream out, final Help.Ansi ansi) {
         if (CommandLine.printHelpIfRequested(parsedCommands, out, ansi)) {
            return null;
         } else {
            List<Object> result = new ArrayList();

            for(CommandLine parsed : parsedCommands) {
               result.add(CommandLine.execute(parsed));
            }

            return result;
         }
      }
   }

   public static class Range implements Comparable {
      public final int min;
      public final int max;
      public final boolean isVariable;
      private final boolean isUnspecified;
      private final String originalValue;

      public Range(final int min, final int max, final boolean variable, final boolean unspecified, final String originalValue) {
         this.min = min;
         this.max = max;
         this.isVariable = variable;
         this.isUnspecified = unspecified;
         this.originalValue = originalValue;
      }

      public static Range optionArity(final Field field) {
         return field.isAnnotationPresent(Option.class) ? adjustForType(valueOf(((Option)field.getAnnotation(Option.class)).arity()), field) : new Range(0, 0, false, true, "0");
      }

      public static Range parameterArity(final Field field) {
         return field.isAnnotationPresent(Parameters.class) ? adjustForType(valueOf(((Parameters)field.getAnnotation(Parameters.class)).arity()), field) : new Range(0, 0, false, true, "0");
      }

      public static Range parameterIndex(final Field field) {
         return field.isAnnotationPresent(Parameters.class) ? valueOf(((Parameters)field.getAnnotation(Parameters.class)).index()) : new Range(0, 0, false, true, "0");
      }

      static Range adjustForType(final Range result, final Field field) {
         return result.isUnspecified ? defaultArity(field) : result;
      }

      public static Range defaultArity(final Field field) {
         Class<?> type = field.getType();
         if (field.isAnnotationPresent(Option.class)) {
            return defaultArity(type);
         } else {
            return CommandLine.isMultiValue(type) ? valueOf("0..1") : valueOf("1");
         }
      }

      public static Range defaultArity(final Class type) {
         return CommandLine.isBoolean(type) ? valueOf("0") : valueOf("1");
      }

      private int size() {
         return 1 + this.max - this.min;
      }

      static Range parameterCapacity(final Field field) {
         Range arity = parameterArity(field);
         if (!CommandLine.isMultiValue(field)) {
            return arity;
         } else {
            Range index = parameterIndex(field);
            if (arity.max == 0) {
               return arity;
            } else if (index.size() == 1) {
               return arity;
            } else if (index.isVariable) {
               return valueOf(arity.min + "..*");
            } else if (arity.size() == 1) {
               return valueOf(arity.min * index.size() + "");
            } else {
               return arity.isVariable ? valueOf(arity.min * index.size() + "..*") : valueOf(arity.min * index.size() + ".." + arity.max * index.size());
            }
         }
      }

      public static Range valueOf(String range) {
         range = range.trim();
         boolean unspecified = range.length() == 0 || range.startsWith("..");
         int min = -1;
         int max = -1;
         boolean variable = false;
         int dots = -1;
         if ((dots = range.indexOf("..")) >= 0) {
            min = parseInt(range.substring(0, dots), 0);
            max = parseInt(range.substring(dots + 2), Integer.MAX_VALUE);
            variable = max == Integer.MAX_VALUE;
         } else {
            max = parseInt(range, Integer.MAX_VALUE);
            variable = max == Integer.MAX_VALUE;
            min = variable ? 0 : max;
         }

         Range result = new Range(min, max, variable, unspecified, range);
         return result;
      }

      private static int parseInt(final String str, final int defaultValue) {
         try {
            return Integers.parseInt(str);
         } catch (Exception var3) {
            return defaultValue;
         }
      }

      public Range min(final int newMin) {
         return new Range(newMin, Math.max(newMin, this.max), this.isVariable, this.isUnspecified, this.originalValue);
      }

      public Range max(final int newMax) {
         return new Range(Math.min(this.min, newMax), newMax, this.isVariable, this.isUnspecified, this.originalValue);
      }

      public boolean contains(final int value) {
         return this.min <= value && this.max >= value;
      }

      public boolean equals(final Object object) {
         if (!(object instanceof Range)) {
            return false;
         } else {
            Range other = (Range)object;
            return other.max == this.max && other.min == this.min && other.isVariable == this.isVariable;
         }
      }

      public int hashCode() {
         return ((629 + this.max) * 37 + this.min) * 37 + (this.isVariable ? 1 : 0);
      }

      public String toString() {
         return this.min == this.max ? String.valueOf(this.min) : this.min + ".." + (this.isVariable ? "*" : this.max);
      }

      public int compareTo(final Range other) {
         int result = this.min - other.min;
         return result == 0 ? this.max - other.max : result;
      }
   }

   private class Interpreter {
      private final Map commands = new LinkedHashMap();
      private final Map converterRegistry = new HashMap();
      private final Map optionName2Field = new HashMap();
      private final Map singleCharOption2Field = new HashMap();
      private final List requiredFields = new ArrayList();
      private final List positionalParametersFields = new ArrayList();
      private final Object command;
      private boolean isHelpRequested;
      private String separator = "=";
      private int position;

      Interpreter(final Object command) {
         this.converterRegistry.put(Path.class, new BuiltIn.PathConverter());
         this.converterRegistry.put(Object.class, new BuiltIn.StringConverter());
         this.converterRegistry.put(String.class, new BuiltIn.StringConverter());
         this.converterRegistry.put(StringBuilder.class, new BuiltIn.StringBuilderConverter());
         this.converterRegistry.put(CharSequence.class, new BuiltIn.CharSequenceConverter());
         this.converterRegistry.put(Byte.class, new BuiltIn.ByteConverter());
         this.converterRegistry.put(Byte.TYPE, new BuiltIn.ByteConverter());
         this.converterRegistry.put(Boolean.class, new BuiltIn.BooleanConverter());
         this.converterRegistry.put(Boolean.TYPE, new BuiltIn.BooleanConverter());
         this.converterRegistry.put(Character.class, new BuiltIn.CharacterConverter());
         this.converterRegistry.put(Character.TYPE, new BuiltIn.CharacterConverter());
         this.converterRegistry.put(Short.class, new BuiltIn.ShortConverter());
         this.converterRegistry.put(Short.TYPE, new BuiltIn.ShortConverter());
         this.converterRegistry.put(Integer.class, new BuiltIn.IntegerConverter());
         this.converterRegistry.put(Integer.TYPE, new BuiltIn.IntegerConverter());
         this.converterRegistry.put(Long.class, new BuiltIn.LongConverter());
         this.converterRegistry.put(Long.TYPE, new BuiltIn.LongConverter());
         this.converterRegistry.put(Float.class, new BuiltIn.FloatConverter());
         this.converterRegistry.put(Float.TYPE, new BuiltIn.FloatConverter());
         this.converterRegistry.put(Double.class, new BuiltIn.DoubleConverter());
         this.converterRegistry.put(Double.TYPE, new BuiltIn.DoubleConverter());
         this.converterRegistry.put(File.class, new BuiltIn.FileConverter());
         this.converterRegistry.put(URI.class, new BuiltIn.URIConverter());
         this.converterRegistry.put(URL.class, new BuiltIn.URLConverter());
         this.converterRegistry.put(Date.class, new BuiltIn.ISO8601DateConverter());
         this.converterRegistry.put(Time.class, new BuiltIn.ISO8601TimeConverter());
         this.converterRegistry.put(BigDecimal.class, new BuiltIn.BigDecimalConverter());
         this.converterRegistry.put(BigInteger.class, new BuiltIn.BigIntegerConverter());
         this.converterRegistry.put(Charset.class, new BuiltIn.CharsetConverter());
         this.converterRegistry.put(InetAddress.class, new BuiltIn.InetAddressConverter());
         this.converterRegistry.put(Pattern.class, new BuiltIn.PatternConverter());
         this.converterRegistry.put(UUID.class, new BuiltIn.UUIDConverter());
         this.command = CommandLine.Assert.notNull(command, "command");
         Class<?> cls = command.getClass();
         String declaredName = null;
         String declaredSeparator = null;

         boolean hasCommandAnnotation;
         for(hasCommandAnnotation = false; cls != null; cls = cls.getSuperclass()) {
            CommandLine.init(cls, this.requiredFields, this.optionName2Field, this.singleCharOption2Field, this.positionalParametersFields);
            if (cls.isAnnotationPresent(Command.class)) {
               hasCommandAnnotation = true;
               Command cmd = (Command)cls.getAnnotation(Command.class);
               declaredSeparator = declaredSeparator == null ? cmd.separator() : declaredSeparator;
               declaredName = declaredName == null ? cmd.name() : declaredName;
               CommandLine.this.versionLines.addAll(Arrays.asList(cmd.version()));

               for(Class sub : cmd.subcommands()) {
                  Command subCommand = (Command)sub.getAnnotation(Command.class);
                  if (subCommand == null || "<main class>".equals(subCommand.name())) {
                     throw new InitializationException("Subcommand " + sub.getName() + " is missing the mandatory @Command annotation with a 'name' attribute");
                  }

                  try {
                     Constructor<?> constructor = sub.getDeclaredConstructor();
                     constructor.setAccessible(true);
                     CommandLine commandLine = CommandLine.toCommandLine(constructor.newInstance());
                     commandLine.parent = CommandLine.this;
                     this.commands.put(subCommand.name(), commandLine);
                  } catch (InitializationException ex) {
                     throw ex;
                  } catch (NoSuchMethodException ex) {
                     throw new InitializationException("Cannot instantiate subcommand " + sub.getName() + ": the class has no constructor", ex);
                  } catch (Exception ex) {
                     throw new InitializationException("Could not instantiate and add subcommand " + sub.getName() + ": " + ex, ex);
                  }
               }
            }
         }

         this.separator = declaredSeparator != null ? declaredSeparator : this.separator;
         CommandLine.this.commandName = declaredName != null ? declaredName : CommandLine.this.commandName;
         Collections.sort(this.positionalParametersFields, new PositionalParametersSorter());
         CommandLine.validatePositionalParameters(this.positionalParametersFields);
         if (this.positionalParametersFields.isEmpty() && this.optionName2Field.isEmpty() && !hasCommandAnnotation) {
            throw new InitializationException(command + " (" + command.getClass() + ") is not a command: it has no @Command, @Option or @Parameters annotations");
         }
      }

      List parse(final String... args) {
         CommandLine.Assert.notNull(args, "argument array");
         if (CommandLine.this.tracer.isInfo()) {
            CommandLine.this.tracer.info("Parsing %d command line args %s%n", args.length, Arrays.toString(args));
         }

         Stack<String> arguments = new Stack();

         for(int i = args.length - 1; i >= 0; --i) {
            arguments.push(args[i]);
         }

         List<CommandLine> result = new ArrayList();
         this.parse(result, arguments, args);
         return result;
      }

      private void parse(final List parsedCommands, final Stack argumentStack, final String[] originalArgs) {
         this.isHelpRequested = false;
         CommandLine.this.versionHelpRequested = false;
         CommandLine.this.usageHelpRequested = false;
         Class<?> cmdClass = this.command.getClass();
         if (CommandLine.this.tracer.isDebug()) {
            CommandLine.this.tracer.debug("Initializing %s: %d options, %d positional parameters, %d required, %d subcommands.%n", cmdClass.getName(), (new HashSet(this.optionName2Field.values())).size(), this.positionalParametersFields.size(), this.requiredFields.size(), this.commands.size());
         }

         parsedCommands.add(CommandLine.this);
         List<Field> required = new ArrayList(this.requiredFields);
         Set<Field> initialized = new HashSet();
         Collections.sort(required, new PositionalParametersSorter());

         try {
            this.processArguments(parsedCommands, argumentStack, required, initialized, originalArgs);
         } catch (ParameterException ex) {
            throw ex;
         } catch (Exception ex) {
            int offendingArgIndex = originalArgs.length - argumentStack.size() - 1;
            String arg = offendingArgIndex >= 0 && offendingArgIndex < originalArgs.length ? originalArgs[offendingArgIndex] : "?";
            throw CommandLine.ParameterException.create(CommandLine.this, ex, arg, offendingArgIndex, originalArgs);
         }

         if (!this.isAnyHelpRequested() && !required.isEmpty()) {
            for(Field missing : required) {
               if (missing.isAnnotationPresent(Option.class)) {
                  throw CommandLine.MissingParameterException.create(CommandLine.this, required, this.separator);
               }

               this.assertNoMissingParameters(missing, CommandLine.Range.parameterArity(missing).min, argumentStack);
            }
         }

         if (!CommandLine.this.unmatchedArguments.isEmpty()) {
            if (!CommandLine.this.isUnmatchedArgumentsAllowed()) {
               throw new UnmatchedArgumentException(CommandLine.this, CommandLine.this.unmatchedArguments);
            }

            if (CommandLine.this.tracer.isWarn()) {
               CommandLine.this.tracer.warn("Unmatched arguments: %s%n", CommandLine.this.unmatchedArguments);
            }
         }

      }

      private void processArguments(final List parsedCommands, final Stack args, final Collection required, final Set initialized, final String[] originalArgs) throws Exception {
         while(!args.isEmpty()) {
            String arg = (String)args.pop();
            if (CommandLine.this.tracer.isDebug()) {
               CommandLine.this.tracer.debug("Processing argument '%s'. Remainder=%s%n", arg, CommandLine.reverse((Stack)args.clone()));
            }

            if ("--".equals(arg)) {
               CommandLine.this.tracer.info("Found end-of-options delimiter '--'. Treating remainder as positional parameters.%n");
               this.processRemainderAsPositionalParameters(required, initialized, args);
               return;
            }

            if (this.commands.containsKey(arg)) {
               if (!this.isHelpRequested && !required.isEmpty()) {
                  throw CommandLine.MissingParameterException.create(CommandLine.this, required, this.separator);
               }

               if (CommandLine.this.tracer.isDebug()) {
                  CommandLine.this.tracer.debug("Found subcommand '%s' (%s)%n", arg, ((CommandLine)this.commands.get(arg)).interpreter.command.getClass().getName());
               }

               ((CommandLine)this.commands.get(arg)).interpreter.parse(parsedCommands, args, originalArgs);
               return;
            }

            boolean paramAttachedToOption = false;
            int separatorIndex = arg.indexOf(this.separator);
            if (separatorIndex > 0) {
               String key = arg.substring(0, separatorIndex);
               if (this.optionName2Field.containsKey(key) && !this.optionName2Field.containsKey(arg)) {
                  paramAttachedToOption = true;
                  String optionParam = arg.substring(separatorIndex + this.separator.length());
                  args.push(optionParam);
                  arg = key;
                  if (CommandLine.this.tracer.isDebug()) {
                     CommandLine.this.tracer.debug("Separated '%s' option from '%s' option parameter%n", key, optionParam);
                  }
               } else if (CommandLine.this.tracer.isDebug()) {
                  CommandLine.this.tracer.debug("'%s' contains separator '%s' but '%s' is not a known option%n", arg, this.separator, key);
               }
            } else if (CommandLine.this.tracer.isDebug()) {
               CommandLine.this.tracer.debug("'%s' cannot be separated into <option>%s<option-parameter>%n", arg, this.separator);
            }

            if (this.optionName2Field.containsKey(arg)) {
               this.processStandaloneOption(required, initialized, arg, args, paramAttachedToOption);
            } else if (arg.length() > 2 && arg.startsWith("-")) {
               if (CommandLine.this.tracer.isDebug()) {
                  CommandLine.this.tracer.debug("Trying to process '%s' as clustered short options%n", arg, args);
               }

               this.processClusteredShortOptions(required, initialized, arg, args);
            } else {
               args.push(arg);
               if (CommandLine.this.tracer.isDebug()) {
                  CommandLine.this.tracer.debug("Could not find option '%s', deciding whether to treat as unmatched option or positional parameter...%n", arg);
               }

               if (this.resemblesOption(arg)) {
                  this.handleUnmatchedArguments((String)args.pop());
               } else {
                  if (CommandLine.this.tracer.isDebug()) {
                     CommandLine.this.tracer.debug("No option named '%s' found. Processing remainder as positional parameters%n", arg);
                  }

                  this.processPositionalParameter(required, initialized, args);
               }
            }
         }

      }

      private boolean resemblesOption(final String arg) {
         int count = 0;

         for(String optionName : this.optionName2Field.keySet()) {
            for(int i = 0; i < arg.length() && optionName.length() > i && arg.charAt(i) == optionName.charAt(i); ++i) {
               ++count;
            }
         }

         boolean result = count > 0 && count * 10 >= this.optionName2Field.size() * 9;
         if (CommandLine.this.tracer.isDebug()) {
            CommandLine.this.tracer.debug("%s %s an option: %d matching prefix chars out of %d option names%n", arg, result ? "resembles" : "doesn't resemble", count, this.optionName2Field.size());
         }

         return result;
      }

      private void handleUnmatchedArguments(final String arg) {
         Stack<String> args = new Stack();
         args.add(arg);
         this.handleUnmatchedArguments(args);
      }

      private void handleUnmatchedArguments(final Stack args) {
         while(!args.isEmpty()) {
            CommandLine.this.unmatchedArguments.add((String)args.pop());
         }

      }

      private void processRemainderAsPositionalParameters(final Collection required, final Set initialized, final Stack args) throws Exception {
         while(!args.empty()) {
            this.processPositionalParameter(required, initialized, args);
         }

      }

      private void processPositionalParameter(final Collection required, final Set initialized, final Stack args) throws Exception {
         if (CommandLine.this.tracer.isDebug()) {
            CommandLine.this.tracer.debug("Processing next arg as a positional parameter at index=%d. Remainder=%s%n", this.position, CommandLine.reverse((Stack)args.clone()));
         }

         int consumed = 0;

         for(Field positionalParam : this.positionalParametersFields) {
            Range indexRange = CommandLine.Range.parameterIndex(positionalParam);
            if (indexRange.contains(this.position)) {
               Stack<String> argsCopy = (Stack)args.clone();
               Range arity = CommandLine.Range.parameterArity(positionalParam);
               if (CommandLine.this.tracer.isDebug()) {
                  CommandLine.this.tracer.debug("Position %d is in index range %s. Trying to assign args to %s, arity=%s%n", this.position, indexRange, positionalParam, arity);
               }

               this.assertNoMissingParameters(positionalParam, arity.min, argsCopy);
               int originalSize = argsCopy.size();
               this.applyOption(positionalParam, Parameters.class, arity, false, argsCopy, initialized, "args[" + indexRange + "] at position " + this.position);
               int count = originalSize - argsCopy.size();
               if (count > 0) {
                  required.remove(positionalParam);
               }

               consumed = Math.max(consumed, count);
            }
         }

         for(int i = 0; i < consumed; ++i) {
            args.pop();
         }

         this.position += consumed;
         if (CommandLine.this.tracer.isDebug()) {
            CommandLine.this.tracer.debug("Consumed %d arguments, moving position to index %d.%n", consumed, this.position);
         }

         if (consumed == 0 && !args.isEmpty()) {
            this.handleUnmatchedArguments((String)args.pop());
         }

      }

      private void processStandaloneOption(final Collection required, final Set initialized, final String arg, final Stack args, final boolean paramAttachedToKey) throws Exception {
         Field field = (Field)this.optionName2Field.get(arg);
         required.remove(field);
         Range arity = CommandLine.Range.optionArity(field);
         if (paramAttachedToKey) {
            arity = arity.min(Math.max(1, arity.min));
         }

         if (CommandLine.this.tracer.isDebug()) {
            CommandLine.this.tracer.debug("Found option named '%s': field %s, arity=%s%n", arg, field, arity);
         }

         this.applyOption(field, Option.class, arity, paramAttachedToKey, args, initialized, "option " + arg);
      }

      private void processClusteredShortOptions(final Collection required, final Set initialized, final String arg, final Stack args) throws Exception {
         String prefix = arg.substring(0, 1);
         String cluster = arg.substring(1);
         boolean paramAttachedToOption = true;

         while(true) {
            if (cluster.length() > 0 && this.singleCharOption2Field.containsKey(cluster.charAt(0))) {
               Field field = (Field)this.singleCharOption2Field.get(cluster.charAt(0));
               Range arity = CommandLine.Range.optionArity(field);
               String argDescription = "option " + prefix + cluster.charAt(0);
               if (CommandLine.this.tracer.isDebug()) {
                  CommandLine.this.tracer.debug("Found option '%s%s' in %s: field %s, arity=%s%n", prefix, cluster.charAt(0), arg, field, arity);
               }

               required.remove(field);
               cluster = cluster.length() > 0 ? cluster.substring(1) : "";
               paramAttachedToOption = cluster.length() > 0;
               if (cluster.startsWith(this.separator)) {
                  cluster = cluster.substring(this.separator.length());
                  arity = arity.min(Math.max(1, arity.min));
               }

               if (arity.min > 0 && !CommandLine.empty(cluster) && CommandLine.this.tracer.isDebug()) {
                  CommandLine.this.tracer.debug("Trying to process '%s' as option parameter%n", cluster);
               }

               if (!CommandLine.empty(cluster)) {
                  args.push(cluster);
               }

               int consumed = this.applyOption(field, Option.class, arity, paramAttachedToOption, args, initialized, argDescription);
               if (!CommandLine.empty(cluster) && consumed <= 0 && !args.isEmpty()) {
                  cluster = (String)args.pop();
                  continue;
               }

               return;
            }

            if (cluster.length() == 0) {
               return;
            }

            if (arg.endsWith(cluster)) {
               args.push(paramAttachedToOption ? prefix + cluster : cluster);
               if (((String)args.peek()).equals(arg)) {
                  if (CommandLine.this.tracer.isDebug()) {
                     CommandLine.this.tracer.debug("Could not match any short options in %s, deciding whether to treat as unmatched option or positional parameter...%n", arg);
                  }

                  if (this.resemblesOption(arg)) {
                     this.handleUnmatchedArguments((String)args.pop());
                     return;
                  }

                  this.processPositionalParameter(required, initialized, args);
                  return;
               }

               if (CommandLine.this.tracer.isDebug()) {
                  CommandLine.this.tracer.debug("No option found for %s in %s%n", cluster, arg);
               }

               this.handleUnmatchedArguments((String)args.pop());
            } else {
               args.push(cluster);
               if (CommandLine.this.tracer.isDebug()) {
                  CommandLine.this.tracer.debug("%s is not an option parameter for %s%n", cluster, arg);
               }

               this.processPositionalParameter(required, initialized, args);
            }

            return;
         }
      }

      private int applyOption(final Field field, final Class annotation, final Range arity, final boolean valueAttachedToOption, final Stack args, final Set initialized, final String argDescription) throws Exception {
         this.updateHelpRequested(field);
         int length = args.size();
         this.assertNoMissingParameters(field, arity.min, args);
         Class<?> cls = field.getType();
         if (cls.isArray()) {
            return this.applyValuesToArrayField(field, annotation, arity, args, cls, argDescription);
         } else if (Collection.class.isAssignableFrom(cls)) {
            return this.applyValuesToCollectionField(field, annotation, arity, args, cls, argDescription);
         } else if (Map.class.isAssignableFrom(cls)) {
            return this.applyValuesToMapField(field, annotation, arity, args, cls, argDescription);
         } else {
            cls = CommandLine.getTypeAttribute(field)[0];
            return this.applyValueToSingleValuedField(field, arity, args, cls, initialized, argDescription);
         }
      }

      private int applyValueToSingleValuedField(final Field field, final Range arity, final Stack args, final Class cls, final Set initialized, final String argDescription) throws Exception {
         boolean noMoreValues = args.isEmpty();
         String value = args.isEmpty() ? null : this.trim((String)args.pop());
         int result = arity.min;
         if ((cls == Boolean.class || cls == Boolean.TYPE) && arity.min <= 0) {
            if (arity.max <= 0 || !"true".equalsIgnoreCase(value) && !"false".equalsIgnoreCase(value)) {
               if (value != null) {
                  args.push(value);
               }

               Boolean currentValue = (Boolean)field.get(this.command);
               value = String.valueOf(currentValue == null ? true : !currentValue);
            } else {
               result = 1;
            }
         }

         if (noMoreValues && value == null) {
            return 0;
         } else {
            ITypeConverter<?> converter = this.getTypeConverter(cls, field);
            Object newValue = this.tryConvert(field, -1, converter, value, cls);
            Object oldValue = field.get(this.command);
            TraceLevel level = CommandLine.TraceLevel.INFO;
            String traceMessage = "Setting %s field '%s.%s' to '%5$s' (was '%4$s') for %6$s%n";
            if (initialized != null) {
               if (initialized.contains(field)) {
                  if (!CommandLine.this.isOverwrittenOptionsAllowed()) {
                     throw new OverwrittenOptionException(CommandLine.this, this.optionDescription("", field, 0) + " should be specified only once");
                  }

                  level = CommandLine.TraceLevel.WARN;
                  traceMessage = "Overwriting %s field '%s.%s' value '%s' with '%s' for %s%n";
               }

               initialized.add(field);
            }

            if (CommandLine.this.tracer.level.isEnabled(level)) {
               level.print(CommandLine.this.tracer, traceMessage, field.getType().getSimpleName(), field.getDeclaringClass().getSimpleName(), field.getName(), String.valueOf(oldValue), String.valueOf(newValue), argDescription);
            }

            field.set(this.command, newValue);
            return result;
         }
      }

      private int applyValuesToMapField(final Field field, final Class annotation, final Range arity, final Stack args, final Class cls, final String argDescription) throws Exception {
         Class<?>[] classes = CommandLine.getTypeAttribute(field);
         if (classes.length < 2) {
            throw new ParameterException(CommandLine.this, "Field " + field + " needs two types (one for the map key, one for the value) but only has " + classes.length + " types configured.");
         } else {
            ITypeConverter<?> keyConverter = this.getTypeConverter(classes[0], field);
            ITypeConverter<?> valueConverter = this.getTypeConverter(classes[1], field);
            Map<Object, Object> result = (Map)field.get(this.command);
            if (result == null) {
               result = this.createMap(cls);
               field.set(this.command, result);
            }

            int originalSize = result.size();
            this.consumeMapArguments(field, arity, args, classes, keyConverter, valueConverter, result, argDescription);
            return result.size() - originalSize;
         }
      }

      private void consumeMapArguments(final Field field, final Range arity, final Stack args, final Class[] classes, final ITypeConverter keyConverter, final ITypeConverter valueConverter, final Map result, final String argDescription) throws Exception {
         for(int i = 0; i < arity.min; ++i) {
            this.consumeOneMapArgument(field, arity, args, classes, keyConverter, valueConverter, result, i, argDescription);
         }

         int i = arity.min;

         while(true) {
            if (i < arity.max && !args.isEmpty()) {
               if (field.isAnnotationPresent(Parameters.class) || !this.commands.containsKey(args.peek()) && !this.isOption((String)args.peek())) {
                  this.consumeOneMapArgument(field, arity, args, classes, keyConverter, valueConverter, result, i, argDescription);
                  ++i;
                  continue;
               }

               return;
            }

            return;
         }
      }

      private void consumeOneMapArgument(final Field field, final Range arity, final Stack args, final Class[] classes, final ITypeConverter keyConverter, final ITypeConverter valueConverter, final Map result, final int index, final String argDescription) throws Exception {
         String[] values = this.split(this.trim((String)args.pop()), field);

         for(String value : values) {
            String[] keyValue = value.split("=");
            if (keyValue.length < 2) {
               String splitRegex = this.splitRegex(field);
               if (splitRegex.length() == 0) {
                  throw new ParameterException(CommandLine.this, "Value for option " + this.optionDescription("", field, 0) + " should be in KEY=VALUE format but was " + value);
               }

               throw new ParameterException(CommandLine.this, "Value for option " + this.optionDescription("", field, 0) + " should be in KEY=VALUE[" + splitRegex + "KEY=VALUE]... format but was " + value);
            }

            Object mapKey = this.tryConvert(field, index, keyConverter, keyValue[0], classes[0]);
            Object mapValue = this.tryConvert(field, index, valueConverter, keyValue[1], classes[1]);
            result.put(mapKey, mapValue);
            if (CommandLine.this.tracer.isInfo()) {
               CommandLine.this.tracer.info("Putting [%s : %s] in %s<%s, %s> field '%s.%s' for %s%n", String.valueOf(mapKey), String.valueOf(mapValue), result.getClass().getSimpleName(), classes[0].getSimpleName(), classes[1].getSimpleName(), field.getDeclaringClass().getSimpleName(), field.getName(), argDescription);
            }
         }

      }

      private void checkMaxArityExceeded(final Range arity, final int remainder, final Field field, final String[] values) {
         if (values.length > remainder) {
            if (arity.max == remainder) {
               (new StringBuilder()).append("").append(remainder).toString();
            } else {
               (new StringBuilder()).append(arity).append(", remainder=").append(remainder).toString();
            }

            throw new MaxValuesforFieldExceededException(CommandLine.this, this.optionDescription("", field, -1) + " max number of values (" + arity.max + ") exceeded: remainder is " + remainder + " but " + values.length + " values were specified: " + Arrays.toString(values));
         }
      }

      private int applyValuesToArrayField(final Field field, final Class annotation, final Range arity, final Stack args, final Class cls, final String argDescription) throws Exception {
         Object existing = field.get(this.command);
         int length = existing == null ? 0 : Array.getLength(existing);
         Class<?> type = CommandLine.getTypeAttribute(field)[0];
         List<Object> converted = this.consumeArguments(field, annotation, arity, args, type, length, argDescription);
         List<Object> newValues = new ArrayList();

         for(int i = 0; i < length; ++i) {
            newValues.add(Array.get(existing, i));
         }

         for(Object obj : converted) {
            if (obj instanceof Collection) {
               newValues.addAll((Collection)obj);
            } else {
               newValues.add(obj);
            }
         }

         Object array = Array.newInstance(type, newValues.size());
         field.set(this.command, array);

         for(int i = 0; i < newValues.size(); ++i) {
            Array.set(array, i, newValues.get(i));
         }

         return converted.size();
      }

      private int applyValuesToCollectionField(final Field field, final Class annotation, final Range arity, final Stack args, final Class cls, final String argDescription) throws Exception {
         Collection<Object> collection = (Collection)field.get(this.command);
         Class<?> type = CommandLine.getTypeAttribute(field)[0];
         int length = collection == null ? 0 : collection.size();
         List<Object> converted = this.consumeArguments(field, annotation, arity, args, type, length, argDescription);
         if (collection == null) {
            collection = this.createCollection(cls);
            field.set(this.command, collection);
         }

         for(Object element : converted) {
            if (element instanceof Collection) {
               collection.addAll((Collection)element);
            } else {
               collection.add(element);
            }
         }

         return converted.size();
      }

      private List consumeArguments(final Field field, final Class annotation, final Range arity, final Stack args, final Class type, final int originalSize, final String argDescription) throws Exception {
         List<Object> result = new ArrayList();

         for(int i = 0; i < arity.min; ++i) {
            this.consumeOneArgument(field, arity, args, type, result, i, originalSize, argDescription);
         }

         int i = arity.min;

         while(true) {
            if (i < arity.max && !args.isEmpty()) {
               if (annotation == Parameters.class || !this.commands.containsKey(args.peek()) && !this.isOption((String)args.peek())) {
                  this.consumeOneArgument(field, arity, args, type, result, i, originalSize, argDescription);
                  ++i;
                  continue;
               }

               return result;
            }

            return result;
         }
      }

      private int consumeOneArgument(final Field field, final Range arity, final Stack args, final Class type, final List result, int index, final int originalSize, final String argDescription) throws Exception {
         String[] values = this.split(this.trim((String)args.pop()), field);
         ITypeConverter<?> converter = this.getTypeConverter(type, field);

         for(int j = 0; j < values.length; ++j) {
            result.add(this.tryConvert(field, index, converter, values[j], type));
            if (CommandLine.this.tracer.isInfo()) {
               if (field.getType().isArray()) {
                  CommandLine.this.tracer.info("Adding [%s] to %s[] field '%s.%s' for %s%n", String.valueOf(result.get(result.size() - 1)), type.getSimpleName(), field.getDeclaringClass().getSimpleName(), field.getName(), argDescription);
               } else {
                  CommandLine.this.tracer.info("Adding [%s] to %s<%s> field '%s.%s' for %s%n", String.valueOf(result.get(result.size() - 1)), field.getType().getSimpleName(), type.getSimpleName(), field.getDeclaringClass().getSimpleName(), field.getName(), argDescription);
               }
            }
         }

         ++index;
         return index;
      }

      private String splitRegex(final Field field) {
         if (field.isAnnotationPresent(Option.class)) {
            return ((Option)field.getAnnotation(Option.class)).split();
         } else {
            return field.isAnnotationPresent(Parameters.class) ? ((Parameters)field.getAnnotation(Parameters.class)).split() : "";
         }
      }

      private String[] split(final String value, final Field field) {
         String regex = this.splitRegex(field);
         return regex.length() == 0 ? new String[]{value} : value.split(regex);
      }

      private boolean isOption(final String arg) {
         if ("--".equals(arg)) {
            return true;
         } else if (this.optionName2Field.containsKey(arg)) {
            return true;
         } else {
            int separatorIndex = arg.indexOf(this.separator);
            if (separatorIndex > 0 && this.optionName2Field.containsKey(arg.substring(0, separatorIndex))) {
               return true;
            } else {
               return arg.length() > 2 && arg.startsWith("-") && this.singleCharOption2Field.containsKey(arg.charAt(1));
            }
         }
      }

      private Object tryConvert(final Field field, final int index, final ITypeConverter converter, final String value, final Class type) throws Exception {
         try {
            return converter.convert(value);
         } catch (TypeConversionException ex) {
            throw new ParameterException(CommandLine.this, ex.getMessage() + this.optionDescription(" for ", field, index));
         } catch (Exception other) {
            String desc = this.optionDescription(" for ", field, index) + ": " + other;
            throw new ParameterException(CommandLine.this, "Could not convert '" + value + "' to " + type.getSimpleName() + desc, other);
         }
      }

      private String optionDescription(final String prefix, final Field field, final int index) {
         Help.IParamLabelRenderer labelRenderer = CommandLine.Help.createMinimalParamLabelRenderer();
         String desc = "";
         if (field.isAnnotationPresent(Option.class)) {
            desc = prefix + "option '" + ((Option)field.getAnnotation(Option.class)).names()[0] + "'";
            if (index >= 0) {
               Range arity = CommandLine.Range.optionArity(field);
               if (arity.max > 1) {
                  desc = desc + " at index " + index;
               }

               desc = desc + " (" + labelRenderer.renderParameterLabel(field, CommandLine.Help.Ansi.OFF, Collections.emptyList()) + ")";
            }
         } else if (field.isAnnotationPresent(Parameters.class)) {
            Range indexRange = CommandLine.Range.parameterIndex(field);
            Help.Ansi.Text label = labelRenderer.renderParameterLabel(field, CommandLine.Help.Ansi.OFF, Collections.emptyList());
            desc = prefix + "positional parameter at index " + indexRange + " (" + label + ")";
         }

         return desc;
      }

      private boolean isAnyHelpRequested() {
         return this.isHelpRequested || CommandLine.this.versionHelpRequested || CommandLine.this.usageHelpRequested;
      }

      private void updateHelpRequested(final Field field) {
         if (field.isAnnotationPresent(Option.class)) {
            this.isHelpRequested |= this.is(field, "help", ((Option)field.getAnnotation(Option.class)).help());
            CommandLine.access$1776(CommandLine.this, this.is(field, "versionHelp", ((Option)field.getAnnotation(Option.class)).versionHelp()));
            CommandLine.access$1876(CommandLine.this, this.is(field, "usageHelp", ((Option)field.getAnnotation(Option.class)).usageHelp()));
         }

      }

      private boolean is(final Field f, final String description, final boolean value) {
         if (value && CommandLine.this.tracer.isInfo()) {
            CommandLine.this.tracer.info("Field '%s.%s' has '%s' annotation: not validating required fields%n", f.getDeclaringClass().getSimpleName(), f.getName(), description);
         }

         return value;
      }

      private Collection createCollection(final Class collectionClass) throws Exception {
         if (collectionClass.isInterface()) {
            if (SortedSet.class.isAssignableFrom(collectionClass)) {
               return new TreeSet();
            } else if (Set.class.isAssignableFrom(collectionClass)) {
               return new LinkedHashSet();
            } else {
               return (Collection)(Queue.class.isAssignableFrom(collectionClass) ? new LinkedList() : new ArrayList());
            }
         } else {
            return (Collection)collectionClass.newInstance();
         }
      }

      private Map createMap(final Class mapClass) throws Exception {
         try {
            return (Map)mapClass.newInstance();
         } catch (Exception var3) {
            return new LinkedHashMap();
         }
      }

      private ITypeConverter getTypeConverter(final Class type, final Field field) {
         ITypeConverter<?> result = (ITypeConverter)this.converterRegistry.get(type);
         if (result != null) {
            return result;
         } else if (type.isEnum()) {
            return new ITypeConverter() {
               public Object convert(final String value) throws Exception {
                  return Enum.valueOf(type, value);
               }
            };
         } else {
            throw new MissingTypeConverterException(CommandLine.this, "No TypeConverter registered for " + type.getName() + " of field " + field);
         }
      }

      private void assertNoMissingParameters(final Field field, final int arity, final Stack args) {
         if (arity > args.size()) {
            if (arity == 1) {
               if (field.isAnnotationPresent(Option.class)) {
                  throw new MissingParameterException(CommandLine.this, "Missing required parameter for " + this.optionDescription("", field, 0));
               } else {
                  Range indexRange = CommandLine.Range.parameterIndex(field);
                  Help.IParamLabelRenderer labelRenderer = CommandLine.Help.createMinimalParamLabelRenderer();
                  String sep = "";
                  String names = "";
                  int count = 0;

                  for(int i = indexRange.min; i < this.positionalParametersFields.size(); ++i) {
                     if (CommandLine.Range.parameterArity((Field)this.positionalParametersFields.get(i)).min > 0) {
                        names = names + sep + labelRenderer.renderParameterLabel((Field)this.positionalParametersFields.get(i), CommandLine.Help.Ansi.OFF, Collections.emptyList());
                        sep = ", ";
                        ++count;
                     }
                  }

                  String msg = "Missing required parameter";
                  Range paramArity = CommandLine.Range.parameterArity(field);
                  if (paramArity.isVariable) {
                     msg = msg + "s at positions " + indexRange + ": ";
                  } else {
                     msg = msg + (count > 1 ? "s: " : ": ");
                  }

                  throw new MissingParameterException(CommandLine.this, msg + names);
               }
            } else if (args.isEmpty()) {
               throw new MissingParameterException(CommandLine.this, this.optionDescription("", field, 0) + " requires at least " + arity + " values, but none were specified.");
            } else {
               throw new MissingParameterException(CommandLine.this, this.optionDescription("", field, 0) + " requires at least " + arity + " values, but only " + args.size() + " were specified: " + CommandLine.reverse(args));
            }
         }
      }

      private String trim(final String value) {
         return this.unquote(value);
      }

      private String unquote(final String value) {
         return value == null ? null : (value.length() > 1 && value.startsWith("\"") && value.endsWith("\"") ? value.substring(1, value.length() - 1) : value);
      }
   }

   private static class PositionalParametersSorter implements Comparator {
      private PositionalParametersSorter() {
      }

      public int compare(final Field o1, final Field o2) {
         int result = CommandLine.Range.parameterIndex(o1).compareTo(CommandLine.Range.parameterIndex(o2));
         return result == 0 ? CommandLine.Range.parameterArity(o1).compareTo(CommandLine.Range.parameterArity(o2)) : result;
      }
   }

   private static final class BuiltIn {
      @SuppressFBWarnings({"PATH_TRAVERSAL_IN"})
      static class PathConverter implements ITypeConverter {
         public Path convert(final String value) {
            return Paths.get(value);
         }
      }

      static class StringConverter implements ITypeConverter {
         public String convert(final String value) {
            return value;
         }
      }

      static class StringBuilderConverter implements ITypeConverter {
         public StringBuilder convert(final String value) {
            return new StringBuilder(value);
         }
      }

      static class CharSequenceConverter implements ITypeConverter {
         public String convert(final String value) {
            return value;
         }
      }

      static class ByteConverter implements ITypeConverter {
         public Byte convert(final String value) {
            return Byte.valueOf(value);
         }
      }

      static class BooleanConverter implements ITypeConverter {
         public Boolean convert(final String value) {
            if (!"true".equalsIgnoreCase(value) && !"false".equalsIgnoreCase(value)) {
               throw new TypeConversionException("'" + value + "' is not a boolean");
            } else {
               return Boolean.parseBoolean(value);
            }
         }
      }

      static class CharacterConverter implements ITypeConverter {
         public Character convert(final String value) {
            if (value.length() > 1) {
               throw new TypeConversionException("'" + value + "' is not a single character");
            } else {
               return value.charAt(0);
            }
         }
      }

      static class ShortConverter implements ITypeConverter {
         public Short convert(final String value) {
            return Short.valueOf(value);
         }
      }

      static class IntegerConverter implements ITypeConverter {
         public Integer convert(final String value) {
            return Integer.valueOf(value);
         }
      }

      static class LongConverter implements ITypeConverter {
         public Long convert(final String value) {
            return Long.valueOf(value);
         }
      }

      static class FloatConverter implements ITypeConverter {
         public Float convert(final String value) {
            return Float.valueOf(value);
         }
      }

      static class DoubleConverter implements ITypeConverter {
         public Double convert(final String value) {
            return Double.valueOf(value);
         }
      }

      @SuppressFBWarnings({"PATH_TRAVERSAL_IN"})
      static class FileConverter implements ITypeConverter {
         public File convert(final String value) {
            return new File(value);
         }
      }

      static class URLConverter implements ITypeConverter {
         public URL convert(final String value) throws MalformedURLException {
            return new URL(value);
         }
      }

      static class URIConverter implements ITypeConverter {
         public URI convert(final String value) throws URISyntaxException {
            return new URI(value);
         }
      }

      static class ISO8601DateConverter implements ITypeConverter {
         public Date convert(final String value) {
            try {
               return (new SimpleDateFormat("yyyy-MM-dd")).parse(value);
            } catch (ParseException var3) {
               throw new TypeConversionException("'" + value + "' is not a yyyy-MM-dd date");
            }
         }
      }

      static class ISO8601TimeConverter implements ITypeConverter {
         public Time convert(final String value) {
            try {
               if (value.length() <= 5) {
                  return new Time((new SimpleDateFormat("HH:mm")).parse(value).getTime());
               }

               if (value.length() <= 8) {
                  return new Time((new SimpleDateFormat("HH:mm:ss")).parse(value).getTime());
               }

               if (value.length() <= 12) {
                  try {
                     return new Time((new SimpleDateFormat("HH:mm:ss.SSS")).parse(value).getTime());
                  } catch (ParseException var3) {
                     return new Time((new SimpleDateFormat("HH:mm:ss,SSS")).parse(value).getTime());
                  }
               }
            } catch (ParseException var4) {
            }

            throw new TypeConversionException("'" + value + "' is not a HH:mm[:ss[.SSS]] time");
         }
      }

      static class BigDecimalConverter implements ITypeConverter {
         public BigDecimal convert(final String value) {
            return new BigDecimal(value);
         }
      }

      static class BigIntegerConverter implements ITypeConverter {
         public BigInteger convert(final String value) {
            return new BigInteger(value);
         }
      }

      static class CharsetConverter implements ITypeConverter {
         public Charset convert(final String s) {
            return Charset.forName(s);
         }
      }

      static class InetAddressConverter implements ITypeConverter {
         public InetAddress convert(final String s) throws Exception {
            return InetAddress.getByName(s);
         }
      }

      static class PatternConverter implements ITypeConverter {
         public Pattern convert(final String s) {
            return Pattern.compile(s);
         }
      }

      static class UUIDConverter implements ITypeConverter {
         public UUID convert(final String s) throws Exception {
            return UUID.fromString(s);
         }
      }
   }

   public static class Help {
      protected static final String DEFAULT_COMMAND_NAME = "<main class>";
      protected static final String DEFAULT_SEPARATOR = "=";
      private static final int usageHelpWidth = 80;
      private static final int optionsColumnWidth = 29;
      private final Object command;
      private final Map commands;
      final ColorScheme colorScheme;
      public final List optionFields;
      public final List positionalParametersFields;
      public String separator;
      public String commandName;
      public String[] description;
      public String[] customSynopsis;
      public String[] header;
      public String[] footer;
      public IParamLabelRenderer parameterLabelRenderer;
      public Boolean abbreviateSynopsis;
      public Boolean sortOptions;
      public Boolean showDefaultValues;
      public Character requiredOptionMarker;
      public String headerHeading;
      public String synopsisHeading;
      public String descriptionHeading;
      public String parameterListHeading;
      public String optionListHeading;
      public String commandListHeading;
      public String footerHeading;

      public Help(final Object command) {
         this(command, CommandLine.Help.Ansi.AUTO);
      }

      public Help(final Object command, final Ansi ansi) {
         this(command, defaultColorScheme(ansi));
      }

      public Help(final Object command, final ColorScheme colorScheme) {
         this.commands = new LinkedHashMap();
         this.commandName = "<main class>";
         this.description = new String[0];
         this.customSynopsis = new String[0];
         this.header = new String[0];
         this.footer = new String[0];
         this.command = CommandLine.Assert.notNull(command, "command");
         this.colorScheme = ((ColorScheme)CommandLine.Assert.notNull(colorScheme, "colorScheme")).applySystemProperties();
         List<Field> options = new ArrayList();
         List<Field> operands = new ArrayList();

         for(Class<?> cls = command.getClass(); cls != null; cls = cls.getSuperclass()) {
            for(Field field : cls.getDeclaredFields()) {
               field.setAccessible(true);
               if (field.isAnnotationPresent(Option.class)) {
                  Option option = (Option)field.getAnnotation(Option.class);
                  if (!option.hidden()) {
                     options.add(field);
                  }
               }

               if (field.isAnnotationPresent(Parameters.class)) {
                  operands.add(field);
               }
            }

            if (cls.isAnnotationPresent(Command.class)) {
               Command cmd = (Command)cls.getAnnotation(Command.class);
               if ("<main class>".equals(this.commandName)) {
                  this.commandName = cmd.name();
               }

               this.separator = this.separator == null ? cmd.separator() : this.separator;
               this.abbreviateSynopsis = this.abbreviateSynopsis == null ? cmd.abbreviateSynopsis() : this.abbreviateSynopsis;
               this.sortOptions = this.sortOptions == null ? cmd.sortOptions() : this.sortOptions;
               this.requiredOptionMarker = this.requiredOptionMarker == null ? cmd.requiredOptionMarker() : this.requiredOptionMarker;
               this.showDefaultValues = this.showDefaultValues == null ? cmd.showDefaultValues() : this.showDefaultValues;
               this.customSynopsis = CommandLine.empty((Object[])this.customSynopsis) ? cmd.customSynopsis() : this.customSynopsis;
               this.description = CommandLine.empty((Object[])this.description) ? cmd.description() : this.description;
               this.header = CommandLine.empty((Object[])this.header) ? cmd.header() : this.header;
               this.footer = CommandLine.empty((Object[])this.footer) ? cmd.footer() : this.footer;
               this.headerHeading = CommandLine.empty(this.headerHeading) ? cmd.headerHeading() : this.headerHeading;
               this.synopsisHeading = !CommandLine.empty(this.synopsisHeading) && !"Usage: ".equals(this.synopsisHeading) ? this.synopsisHeading : cmd.synopsisHeading();
               this.descriptionHeading = CommandLine.empty(this.descriptionHeading) ? cmd.descriptionHeading() : this.descriptionHeading;
               this.parameterListHeading = CommandLine.empty(this.parameterListHeading) ? cmd.parameterListHeading() : this.parameterListHeading;
               this.optionListHeading = CommandLine.empty(this.optionListHeading) ? cmd.optionListHeading() : this.optionListHeading;
               this.commandListHeading = !CommandLine.empty(this.commandListHeading) && !"Commands:%n".equals(this.commandListHeading) ? this.commandListHeading : cmd.commandListHeading();
               this.footerHeading = CommandLine.empty(this.footerHeading) ? cmd.footerHeading() : this.footerHeading;
            }
         }

         this.sortOptions = this.sortOptions == null ? true : this.sortOptions;
         this.abbreviateSynopsis = this.abbreviateSynopsis == null ? false : this.abbreviateSynopsis;
         this.requiredOptionMarker = this.requiredOptionMarker == null ? ' ' : this.requiredOptionMarker;
         this.showDefaultValues = this.showDefaultValues == null ? false : this.showDefaultValues;
         this.synopsisHeading = this.synopsisHeading == null ? "Usage: " : this.synopsisHeading;
         this.commandListHeading = this.commandListHeading == null ? "Commands:%n" : this.commandListHeading;
         this.separator = this.separator == null ? "=" : this.separator;
         this.parameterLabelRenderer = this.createDefaultParamLabelRenderer();
         Collections.sort(operands, new PositionalParametersSorter());
         this.positionalParametersFields = Collections.unmodifiableList(operands);
         this.optionFields = Collections.unmodifiableList(options);
      }

      public Help addAllSubcommands(final Map commands) {
         if (commands != null) {
            for(Map.Entry entry : commands.entrySet()) {
               this.addSubcommand((String)entry.getKey(), ((CommandLine)entry.getValue()).getCommand());
            }
         }

         return this;
      }

      public Help addSubcommand(final String commandName, final Object command) {
         this.commands.put(commandName, new Help(command));
         return this;
      }

      /** @deprecated */
      @Deprecated
      public String synopsis() {
         return this.synopsis(0);
      }

      public String synopsis(final int synopsisHeadingLength) {
         if (!CommandLine.empty((Object[])this.customSynopsis)) {
            return this.customSynopsis();
         } else {
            return this.abbreviateSynopsis ? this.abbreviatedSynopsis() : this.detailedSynopsis(synopsisHeadingLength, createShortOptionArityAndNameComparator(), true);
         }
      }

      public String abbreviatedSynopsis() {
         StringBuilder sb = new StringBuilder();
         if (!this.optionFields.isEmpty()) {
            sb.append(" [OPTIONS]");
         }

         for(Field positionalParam : this.positionalParametersFields) {
            if (!((Parameters)positionalParam.getAnnotation(Parameters.class)).hidden()) {
               sb.append(' ').append(this.parameterLabelRenderer.renderParameterLabel(positionalParam, this.ansi(), this.colorScheme.parameterStyles));
            }
         }

         return this.colorScheme.commandText(this.commandName).toString() + sb.toString() + System.getProperty("line.separator");
      }

      /** @deprecated */
      @Deprecated
      public String detailedSynopsis(final Comparator optionSort, final boolean clusterBooleanOptions) {
         return this.detailedSynopsis(0, optionSort, clusterBooleanOptions);
      }

      public String detailedSynopsis(final int synopsisHeadingLength, final Comparator optionSort, final boolean clusterBooleanOptions) {
         Ansi var10002 = this.ansi();
         Objects.requireNonNull(var10002);
         Ansi.Text optionText = var10002.new Text(0);
         List<Field> fields = new ArrayList(this.optionFields);
         if (optionSort != null) {
            Collections.sort(fields, optionSort);
         }

         if (clusterBooleanOptions) {
            List<Field> booleanOptions = new ArrayList();
            StringBuilder clusteredRequired = new StringBuilder("-");
            StringBuilder clusteredOptional = new StringBuilder("-");

            for(Field field : fields) {
               if (field.getType() == Boolean.TYPE || field.getType() == Boolean.class) {
                  Option option = (Option)field.getAnnotation(Option.class);
                  String shortestName = CommandLine.Help.ShortestFirst.sort(option.names())[0];
                  if (shortestName.length() == 2 && shortestName.startsWith("-")) {
                     booleanOptions.add(field);
                     if (option.required()) {
                        clusteredRequired.append(shortestName.substring(1));
                     } else {
                        clusteredOptional.append(shortestName.substring(1));
                     }
                  }
               }
            }

            fields.removeAll(booleanOptions);
            if (clusteredRequired.length() > 1) {
               optionText = optionText.append(" ").append(this.colorScheme.optionText(clusteredRequired.toString()));
            }

            if (clusteredOptional.length() > 1) {
               optionText = optionText.append(" [").append(this.colorScheme.optionText(clusteredOptional.toString())).append("]");
            }
         }

         for(Field field : fields) {
            Option option = (Option)field.getAnnotation(Option.class);
            if (!option.hidden()) {
               if (option.required()) {
                  optionText = this.appendOptionSynopsis(optionText, field, CommandLine.Help.ShortestFirst.sort(option.names())[0], " ", "");
                  if (CommandLine.isMultiValue(field)) {
                     optionText = this.appendOptionSynopsis(optionText, field, CommandLine.Help.ShortestFirst.sort(option.names())[0], " [", "]...");
                  }
               } else {
                  optionText = this.appendOptionSynopsis(optionText, field, CommandLine.Help.ShortestFirst.sort(option.names())[0], " [", "]");
                  if (CommandLine.isMultiValue(field)) {
                     optionText = optionText.append("...");
                  }
               }
            }
         }

         for(Field positionalParam : this.positionalParametersFields) {
            if (!((Parameters)positionalParam.getAnnotation(Parameters.class)).hidden()) {
               optionText = optionText.append(" ");
               Ansi.Text label = this.parameterLabelRenderer.renderParameterLabel(positionalParam, this.colorScheme.ansi(), this.colorScheme.parameterStyles);
               optionText = optionText.append(label);
            }
         }

         int firstColumnLength = this.commandName.length() + synopsisHeadingLength;
         TextTable textTable = new TextTable(this.ansi(), new int[]{firstColumnLength, 80 - firstColumnLength});
         textTable.indentWrappedLines = 1;
         var10002 = CommandLine.Help.Ansi.OFF;
         Objects.requireNonNull(var10002);
         Ansi.Text PADDING = var10002.new Text(stringOf('X', synopsisHeadingLength));
         textTable.addRowValues(PADDING.append(this.colorScheme.commandText(this.commandName)), optionText);
         return textTable.toString().substring(synopsisHeadingLength);
      }

      private Ansi.Text appendOptionSynopsis(final Ansi.Text optionText, final Field field, final String optionName, final String prefix, final String suffix) {
         Ansi.Text optionParamText = this.parameterLabelRenderer.renderParameterLabel(field, this.colorScheme.ansi(), this.colorScheme.optionParamStyles);
         return optionText.append(prefix).append(this.colorScheme.optionText(optionName)).append(optionParamText).append(suffix);
      }

      public int synopsisHeadingLength() {
         Ansi var10002 = CommandLine.Help.Ansi.OFF;
         Objects.requireNonNull(var10002);
         String[] lines = (var10002.new Text(this.synopsisHeading)).toString().split("\\r?\\n|\\r|%n", -1);
         return lines[lines.length - 1].length();
      }

      public String optionList() {
         Comparator<Field> sortOrder = this.sortOptions != null && !this.sortOptions ? null : createShortOptionNameComparator();
         return this.optionList(this.createDefaultLayout(), sortOrder, this.parameterLabelRenderer);
      }

      public String optionList(final Layout layout, final Comparator optionSort, final IParamLabelRenderer valueLabelRenderer) {
         List<Field> fields = new ArrayList(this.optionFields);
         if (optionSort != null) {
            Collections.sort(fields, optionSort);
         }

         layout.addOptions(fields, valueLabelRenderer);
         return layout.toString();
      }

      public String parameterList() {
         return this.parameterList(this.createDefaultLayout(), this.parameterLabelRenderer);
      }

      public String parameterList(final Layout layout, final IParamLabelRenderer paramLabelRenderer) {
         layout.addPositionalParameters(this.positionalParametersFields, paramLabelRenderer);
         return layout.toString();
      }

      private static String heading(final Ansi ansi, final String values, final Object... params) {
         StringBuilder sb = join(ansi, new String[]{values}, new StringBuilder(), params);
         String result = sb.toString();
         result = result.endsWith(System.getProperty("line.separator")) ? result.substring(0, result.length() - System.getProperty("line.separator").length()) : result;
         return result + new String(spaces(countTrailingSpaces(values)));
      }

      private static char[] spaces(final int length) {
         char[] result = new char[length];
         Arrays.fill(result, ' ');
         return result;
      }

      private static int countTrailingSpaces(final String str) {
         if (str == null) {
            return 0;
         } else {
            int trailingSpaces = 0;

            for(int i = str.length() - 1; i >= 0 && str.charAt(i) == ' '; --i) {
               ++trailingSpaces;
            }

            return trailingSpaces;
         }
      }

      public static StringBuilder join(final Ansi ansi, final String[] values, final StringBuilder sb, final Object... params) {
         if (values != null) {
            TextTable table = new TextTable(ansi, new int[]{80});
            table.indentWrappedLines = 0;

            for(String summaryLine : values) {
               Objects.requireNonNull(ansi);
               Ansi.Text[] lines = (ansi.new Text(format(summaryLine, params))).splitLines();

               for(Ansi.Text line : lines) {
                  table.addRowValues(line);
               }
            }

            table.toString(sb);
         }

         return sb;
      }

      private static String format(final String formatString, final Object... params) {
         return formatString == null ? "" : String.format(formatString, params);
      }

      public String customSynopsis(final Object... params) {
         return join(this.ansi(), this.customSynopsis, new StringBuilder(), params).toString();
      }

      public String description(final Object... params) {
         return join(this.ansi(), this.description, new StringBuilder(), params).toString();
      }

      public String header(final Object... params) {
         return join(this.ansi(), this.header, new StringBuilder(), params).toString();
      }

      public String footer(final Object... params) {
         return join(this.ansi(), this.footer, new StringBuilder(), params).toString();
      }

      public String headerHeading(final Object... params) {
         return heading(this.ansi(), this.headerHeading, params);
      }

      public String synopsisHeading(final Object... params) {
         return heading(this.ansi(), this.synopsisHeading, params);
      }

      public String descriptionHeading(final Object... params) {
         return CommandLine.empty(this.descriptionHeading) ? "" : heading(this.ansi(), this.descriptionHeading, params);
      }

      public String parameterListHeading(final Object... params) {
         return this.positionalParametersFields.isEmpty() ? "" : heading(this.ansi(), this.parameterListHeading, params);
      }

      public String optionListHeading(final Object... params) {
         return this.optionFields.isEmpty() ? "" : heading(this.ansi(), this.optionListHeading, params);
      }

      public String commandListHeading(final Object... params) {
         return this.commands.isEmpty() ? "" : heading(this.ansi(), this.commandListHeading, params);
      }

      public String footerHeading(final Object... params) {
         return heading(this.ansi(), this.footerHeading, params);
      }

      public String commandList() {
         if (this.commands.isEmpty()) {
            return "";
         } else {
            int commandLength = maxLength(this.commands.keySet());
            TextTable textTable = new TextTable(this.ansi(), new Column[]{new Column(commandLength + 2, 2, CommandLine.Help.Column.Overflow.SPAN), new Column(80 - (commandLength + 2), 2, CommandLine.Help.Column.Overflow.WRAP)});

            for(Map.Entry entry : this.commands.entrySet()) {
               Help command = (Help)entry.getValue();
               String header = command.header != null && command.header.length > 0 ? command.header[0] : (command.description != null && command.description.length > 0 ? command.description[0] : "");
               Ansi.Text[] var10001 = new Ansi.Text[]{this.colorScheme.commandText((String)entry.getKey()), null};
               Ansi var10006 = this.ansi();
               Objects.requireNonNull(var10006);
               var10001[1] = var10006.new Text(header);
               textTable.addRowValues(var10001);
            }

            return textTable.toString();
         }
      }

      private static int maxLength(final Collection any) {
         List<String> strings = new ArrayList(any);
         Collections.sort(strings, Collections.reverseOrder(shortestFirst()));
         return ((String)strings.get(0)).length();
      }

      private static String join(final String[] names, final int offset, final int length, final String separator) {
         if (names == null) {
            return "";
         } else {
            StringBuilder result = new StringBuilder();

            for(int i = offset; i < offset + length; ++i) {
               result.append(i > offset ? separator : "").append(names[i]);
            }

            return result.toString();
         }
      }

      private static String stringOf(final char chr, final int length) {
         char[] buff = new char[length];
         Arrays.fill(buff, chr);
         return new String(buff);
      }

      public Layout createDefaultLayout() {
         return new Layout(this.colorScheme, new TextTable(this.colorScheme.ansi()), this.createDefaultOptionRenderer(), this.createDefaultParameterRenderer());
      }

      public IOptionRenderer createDefaultOptionRenderer() {
         DefaultOptionRenderer result = new DefaultOptionRenderer();
         result.requiredMarker = String.valueOf(this.requiredOptionMarker);
         if (this.showDefaultValues != null && this.showDefaultValues) {
            result.command = this.command;
         }

         return result;
      }

      public static IOptionRenderer createMinimalOptionRenderer() {
         return new MinimalOptionRenderer();
      }

      public IParameterRenderer createDefaultParameterRenderer() {
         DefaultParameterRenderer result = new DefaultParameterRenderer();
         result.requiredMarker = String.valueOf(this.requiredOptionMarker);
         return result;
      }

      public static IParameterRenderer createMinimalParameterRenderer() {
         return new MinimalParameterRenderer();
      }

      public static IParamLabelRenderer createMinimalParamLabelRenderer() {
         return new IParamLabelRenderer() {
            public Ansi.Text renderParameterLabel(final Field field, final Ansi ansi, final List styles) {
               String text = CommandLine.Help.DefaultParamLabelRenderer.renderParameterName(field);
               return ansi.apply(text, styles);
            }

            public String separator() {
               return "";
            }
         };
      }

      public IParamLabelRenderer createDefaultParamLabelRenderer() {
         return new DefaultParamLabelRenderer(this.separator);
      }

      public static Comparator createShortOptionNameComparator() {
         return new SortByShortestOptionNameAlphabetically();
      }

      public static Comparator createShortOptionArityAndNameComparator() {
         return new SortByOptionArityAndNameAlphabetically();
      }

      public static Comparator shortestFirst() {
         return new ShortestFirst();
      }

      public Ansi ansi() {
         return this.colorScheme.ansi;
      }

      public static ColorScheme defaultColorScheme(final Ansi ansi) {
         return (new ColorScheme(ansi)).commands(CommandLine.Help.Ansi.Style.bold).options(CommandLine.Help.Ansi.Style.fg_yellow).parameters(CommandLine.Help.Ansi.Style.fg_yellow).optionParams(CommandLine.Help.Ansi.Style.italic);
      }

      static class DefaultOptionRenderer implements IOptionRenderer {
         public String requiredMarker = " ";
         public Object command;
         private String sep;
         private boolean showDefault;

         public Ansi.Text[][] render(final Option option, final Field field, final IParamLabelRenderer paramLabelRenderer, final ColorScheme scheme) {
            String[] names = CommandLine.Help.ShortestFirst.sort(option.names());
            int shortOptionCount = names[0].length() == 2 ? 1 : 0;
            String shortOption = shortOptionCount > 0 ? names[0] : "";
            this.sep = shortOptionCount > 0 && names.length > 1 ? "," : "";
            String longOption = CommandLine.Help.join(names, shortOptionCount, names.length - shortOptionCount, ", ");
            Ansi.Text longOptionText = this.createLongOptionText(field, paramLabelRenderer, scheme, longOption);
            this.showDefault = this.command != null && !option.help() && !CommandLine.isBoolean(field.getType());
            Object defaultValue = this.createDefaultValue(field);
            String requiredOption = option.required() ? this.requiredMarker : "";
            return this.renderDescriptionLines(option, scheme, requiredOption, shortOption, longOptionText, defaultValue);
         }

         private Object createDefaultValue(final Field field) {
            Object defaultValue = null;

            try {
               defaultValue = field.get(this.command);
               if (defaultValue == null) {
                  this.showDefault = false;
               } else if (field.getType().isArray()) {
                  StringBuilder sb = new StringBuilder();

                  for(int i = 0; i < Array.getLength(defaultValue); ++i) {
                     sb.append(i > 0 ? ", " : "").append(Array.get(defaultValue, i));
                  }

                  defaultValue = sb.insert(0, "[").append("]").toString();
               }
            } catch (Exception var5) {
               this.showDefault = false;
            }

            return defaultValue;
         }

         private Ansi.Text createLongOptionText(final Field field, final IParamLabelRenderer renderer, final ColorScheme scheme, final String longOption) {
            Ansi.Text paramLabelText = renderer.renderParameterLabel(field, scheme.ansi(), scheme.optionParamStyles);
            if (paramLabelText.length > 0 && longOption.length() == 0) {
               this.sep = renderer.separator();
               int sepStart = paramLabelText.plainString().indexOf(this.sep);
               Ansi.Text prefix = paramLabelText.substring(0, sepStart);
               paramLabelText = prefix.append(paramLabelText.substring(sepStart + this.sep.length()));
            }

            Ansi.Text longOptionText = scheme.optionText(longOption);
            longOptionText = longOptionText.append(paramLabelText);
            return longOptionText;
         }

         private Ansi.Text[][] renderDescriptionLines(final Option option, final ColorScheme scheme, final String requiredOption, final String shortOption, final Ansi.Text longOptionText, final Object defaultValue) {
            Ansi.Text EMPTY = CommandLine.Help.Ansi.EMPTY_TEXT;
            List<Ansi.Text[]> result = new ArrayList();
            Ansi var10002 = scheme.ansi();
            Objects.requireNonNull(var10002);
            Ansi.Text[] descriptionFirstLines = (var10002.new Text(CommandLine.str(option.description(), 0))).splitLines();
            if (descriptionFirstLines.length == 0) {
               if (this.showDefault) {
                  Ansi.Text[] var10000 = new Ansi.Text[1];
                  Ansi var10005 = scheme.ansi();
                  Objects.requireNonNull(var10005);
                  var10000[0] = var10005.new Text("  Default: " + defaultValue);
                  descriptionFirstLines = var10000;
                  this.showDefault = false;
               } else {
                  descriptionFirstLines = new Ansi.Text[]{EMPTY};
               }
            }

            Ansi.Text[] var10001 = new Ansi.Text[]{scheme.optionText(requiredOption), scheme.optionText(shortOption), null, null, null};
            Ansi var10006 = scheme.ansi();
            Objects.requireNonNull(var10006);
            var10001[2] = var10006.new Text(this.sep);
            var10001[3] = longOptionText;
            var10001[4] = descriptionFirstLines[0];
            result.add(var10001);

            for(int i = 1; i < descriptionFirstLines.length; ++i) {
               result.add(new Ansi.Text[]{EMPTY, EMPTY, EMPTY, EMPTY, descriptionFirstLines[i]});
            }

            for(int i = 1; i < option.description().length; ++i) {
               var10002 = scheme.ansi();
               Objects.requireNonNull(var10002);
               Ansi.Text[] descriptionNextLines = (var10002.new Text(option.description()[i])).splitLines();

               for(Ansi.Text line : descriptionNextLines) {
                  result.add(new Ansi.Text[]{EMPTY, EMPTY, EMPTY, EMPTY, line});
               }
            }

            if (this.showDefault) {
               var10001 = new Ansi.Text[]{EMPTY, EMPTY, EMPTY, EMPTY, null};
               var10006 = scheme.ansi();
               Objects.requireNonNull(var10006);
               var10001[4] = var10006.new Text("  Default: " + defaultValue);
               result.add(var10001);
            }

            return (Ansi.Text[][])result.toArray(new Ansi.Text[result.size()][]);
         }
      }

      static class MinimalOptionRenderer implements IOptionRenderer {
         public Ansi.Text[][] render(final Option option, final Field field, final IParamLabelRenderer parameterLabelRenderer, final ColorScheme scheme) {
            Ansi.Text optionText = scheme.optionText(option.names()[0]);
            Ansi.Text paramLabelText = parameterLabelRenderer.renderParameterLabel(field, scheme.ansi(), scheme.optionParamStyles);
            optionText = optionText.append(paramLabelText);
            Ansi.Text[][] var10000 = new Ansi.Text[1][];
            Ansi.Text[] var10003 = new Ansi.Text[]{optionText, null};
            Ansi var10008 = scheme.ansi();
            Objects.requireNonNull(var10008);
            var10003[1] = var10008.new Text(option.description().length == 0 ? "" : option.description()[0]);
            var10000[0] = var10003;
            return var10000;
         }
      }

      static class MinimalParameterRenderer implements IParameterRenderer {
         public Ansi.Text[][] render(final Parameters param, final Field field, final IParamLabelRenderer parameterLabelRenderer, final ColorScheme scheme) {
            Ansi.Text[][] var10000 = new Ansi.Text[1][];
            Ansi.Text[] var10003 = new Ansi.Text[]{parameterLabelRenderer.renderParameterLabel(field, scheme.ansi(), scheme.parameterStyles), null};
            Ansi var10008 = scheme.ansi();
            Objects.requireNonNull(var10008);
            var10003[1] = var10008.new Text(param.description().length == 0 ? "" : param.description()[0]);
            var10000[0] = var10003;
            return var10000;
         }
      }

      static class DefaultParameterRenderer implements IParameterRenderer {
         public String requiredMarker = " ";

         public Ansi.Text[][] render(final Parameters params, final Field field, final IParamLabelRenderer paramLabelRenderer, final ColorScheme scheme) {
            Ansi.Text label = paramLabelRenderer.renderParameterLabel(field, scheme.ansi(), scheme.parameterStyles);
            Ansi.Text requiredParameter = scheme.parameterText(CommandLine.Range.parameterArity(field).min > 0 ? this.requiredMarker : "");
            Ansi.Text EMPTY = CommandLine.Help.Ansi.EMPTY_TEXT;
            List<Ansi.Text[]> result = new ArrayList();
            Ansi var10002 = scheme.ansi();
            Objects.requireNonNull(var10002);
            Ansi.Text[] descriptionFirstLines = (var10002.new Text(CommandLine.str(params.description(), 0))).splitLines();
            if (descriptionFirstLines.length == 0) {
               descriptionFirstLines = new Ansi.Text[]{EMPTY};
            }

            result.add(new Ansi.Text[]{requiredParameter, EMPTY, EMPTY, label, descriptionFirstLines[0]});

            for(int i = 1; i < descriptionFirstLines.length; ++i) {
               result.add(new Ansi.Text[]{EMPTY, EMPTY, EMPTY, EMPTY, descriptionFirstLines[i]});
            }

            for(int i = 1; i < params.description().length; ++i) {
               var10002 = scheme.ansi();
               Objects.requireNonNull(var10002);
               Ansi.Text[] descriptionNextLines = (var10002.new Text(params.description()[i])).splitLines();

               for(Ansi.Text line : descriptionNextLines) {
                  result.add(new Ansi.Text[]{EMPTY, EMPTY, EMPTY, EMPTY, line});
               }
            }

            return (Ansi.Text[][])result.toArray(new Ansi.Text[result.size()][]);
         }
      }

      static class DefaultParamLabelRenderer implements IParamLabelRenderer {
         public final String separator;

         public DefaultParamLabelRenderer(final String separator) {
            this.separator = (String)CommandLine.Assert.notNull(separator, "separator");
         }

         public String separator() {
            return this.separator;
         }

         public Ansi.Text renderParameterLabel(final Field field, final Ansi ansi, final List styles) {
            boolean isOptionParameter = field.isAnnotationPresent(Option.class);
            Range arity = isOptionParameter ? CommandLine.Range.optionArity(field) : CommandLine.Range.parameterCapacity(field);
            String split = isOptionParameter ? ((Option)field.getAnnotation(Option.class)).split() : ((Parameters)field.getAnnotation(Parameters.class)).split();
            Objects.requireNonNull(ansi);
            Ansi.Text result = ansi.new Text("");
            String sep = isOptionParameter ? this.separator : "";
            Ansi.Text paramName = ansi.apply(renderParameterName(field), styles);
            if (!CommandLine.empty(split)) {
               paramName = paramName.append("[" + split).append(paramName).append("]...");
            }

            for(int i = 0; i < arity.min; ++i) {
               result = result.append(sep).append(paramName);
               sep = " ";
            }

            if (arity.isVariable) {
               if (result.length == 0) {
                  result = result.append(sep + "[").append(paramName).append("]...");
               } else if (!result.plainString().endsWith("...")) {
                  result = result.append("...");
               }
            } else {
               sep = result.length == 0 ? (isOptionParameter ? this.separator : "") : " ";

               for(int i = arity.min; i < arity.max; ++i) {
                  if (sep.trim().length() == 0) {
                     result = result.append(sep + "[").append(paramName);
                  } else {
                     result = result.append("[" + sep).append(paramName);
                  }

                  sep = " ";
               }

               for(int i = arity.min; i < arity.max; ++i) {
                  result = result.append("]");
               }
            }

            return result;
         }

         private static String renderParameterName(final Field field) {
            String result = null;
            if (field.isAnnotationPresent(Option.class)) {
               result = ((Option)field.getAnnotation(Option.class)).paramLabel();
            } else if (field.isAnnotationPresent(Parameters.class)) {
               result = ((Parameters)field.getAnnotation(Parameters.class)).paramLabel();
            }

            if (result != null && result.trim().length() > 0) {
               return result.trim();
            } else {
               String name = field.getName();
               if (Map.class.isAssignableFrom(field.getType())) {
                  Class<?>[] paramTypes = CommandLine.getTypeAttribute(field);
                  if (paramTypes.length >= 2 && paramTypes[0] != null && paramTypes[1] != null) {
                     name = paramTypes[0].getSimpleName() + "=" + paramTypes[1].getSimpleName();
                  } else {
                     name = "String=String";
                  }
               }

               return "<" + name + ">";
            }
         }
      }

      public static class Layout {
         protected final ColorScheme colorScheme;
         protected final TextTable table;
         protected IOptionRenderer optionRenderer;
         protected IParameterRenderer parameterRenderer;

         public Layout(final ColorScheme colorScheme) {
            this(colorScheme, new TextTable(colorScheme.ansi()));
         }

         public Layout(final ColorScheme colorScheme, final TextTable textTable) {
            this(colorScheme, textTable, new DefaultOptionRenderer(), new DefaultParameterRenderer());
         }

         public Layout(final ColorScheme colorScheme, final TextTable textTable, final IOptionRenderer optionRenderer, final IParameterRenderer parameterRenderer) {
            this.colorScheme = (ColorScheme)CommandLine.Assert.notNull(colorScheme, "colorScheme");
            this.table = (TextTable)CommandLine.Assert.notNull(textTable, "textTable");
            this.optionRenderer = (IOptionRenderer)CommandLine.Assert.notNull(optionRenderer, "optionRenderer");
            this.parameterRenderer = (IParameterRenderer)CommandLine.Assert.notNull(parameterRenderer, "parameterRenderer");
         }

         public void layout(final Field field, final Ansi.Text[][] cellValues) {
            for(Ansi.Text[] oneRow : cellValues) {
               this.table.addRowValues(oneRow);
            }

         }

         public void addOptions(final List fields, final IParamLabelRenderer paramLabelRenderer) {
            for(Field field : fields) {
               Option option = (Option)field.getAnnotation(Option.class);
               if (!option.hidden()) {
                  this.addOption(field, paramLabelRenderer);
               }
            }

         }

         public void addOption(final Field field, final IParamLabelRenderer paramLabelRenderer) {
            Option option = (Option)field.getAnnotation(Option.class);
            Ansi.Text[][] values = this.optionRenderer.render(option, field, paramLabelRenderer, this.colorScheme);
            this.layout(field, values);
         }

         public void addPositionalParameters(final List fields, final IParamLabelRenderer paramLabelRenderer) {
            for(Field field : fields) {
               Parameters parameters = (Parameters)field.getAnnotation(Parameters.class);
               if (!parameters.hidden()) {
                  this.addPositionalParameter(field, paramLabelRenderer);
               }
            }

         }

         public void addPositionalParameter(final Field field, final IParamLabelRenderer paramLabelRenderer) {
            Parameters option = (Parameters)field.getAnnotation(Parameters.class);
            Ansi.Text[][] values = this.parameterRenderer.render(option, field, paramLabelRenderer, this.colorScheme);
            this.layout(field, values);
         }

         public String toString() {
            return this.table.toString();
         }
      }

      static class ShortestFirst implements Comparator {
         public int compare(final String o1, final String o2) {
            return o1.length() - o2.length();
         }

         public static String[] sort(final String[] names) {
            Arrays.sort(names, new ShortestFirst());
            return names;
         }
      }

      static class SortByShortestOptionNameAlphabetically implements Comparator {
         public int compare(final Field f1, final Field f2) {
            Option o1 = (Option)f1.getAnnotation(Option.class);
            Option o2 = (Option)f2.getAnnotation(Option.class);
            if (o1 == null) {
               return 1;
            } else if (o2 == null) {
               return -1;
            } else {
               String[] names1 = CommandLine.Help.ShortestFirst.sort(o1.names());
               String[] names2 = CommandLine.Help.ShortestFirst.sort(o2.names());
               int result = Strings.toRootUpperCase(names1[0]).compareTo(Strings.toRootUpperCase(names2[0]));
               result = result == 0 ? -names1[0].compareTo(names2[0]) : result;
               return o1.help() == o2.help() ? result : (o2.help() ? -1 : 1);
            }
         }
      }

      static class SortByOptionArityAndNameAlphabetically extends SortByShortestOptionNameAlphabetically {
         public int compare(final Field f1, final Field f2) {
            Option o1 = (Option)f1.getAnnotation(Option.class);
            Option o2 = (Option)f2.getAnnotation(Option.class);
            Range arity1 = CommandLine.Range.optionArity(f1);
            Range arity2 = CommandLine.Range.optionArity(f2);
            int result = arity1.max - arity2.max;
            if (result == 0) {
               result = arity1.min - arity2.min;
            }

            if (result == 0) {
               if (CommandLine.isMultiValue(f1) && !CommandLine.isMultiValue(f2)) {
                  result = 1;
               }

               if (!CommandLine.isMultiValue(f1) && CommandLine.isMultiValue(f2)) {
                  result = -1;
               }
            }

            return result == 0 ? super.compare(f1, f2) : result;
         }
      }

      public static class TextTable {
         public final Column[] columns;
         protected final List columnValues;
         public int indentWrappedLines;
         private final Ansi ansi;

         public TextTable(final Ansi ansi) {
            this(ansi, new Column(2, 0, CommandLine.Help.Column.Overflow.TRUNCATE), new Column(2, 0, CommandLine.Help.Column.Overflow.TRUNCATE), new Column(1, 0, CommandLine.Help.Column.Overflow.TRUNCATE), new Column(24, 1, CommandLine.Help.Column.Overflow.SPAN), new Column(51, 1, CommandLine.Help.Column.Overflow.WRAP));
         }

         public TextTable(final Ansi ansi, final int... columnWidths) {
            this.columnValues = new ArrayList();
            this.indentWrappedLines = 2;
            this.ansi = (Ansi)CommandLine.Assert.notNull(ansi, "ansi");
            this.columns = new Column[columnWidths.length];

            for(int i = 0; i < columnWidths.length; ++i) {
               this.columns[i] = new Column(columnWidths[i], 0, i == columnWidths.length - 1 ? CommandLine.Help.Column.Overflow.SPAN : CommandLine.Help.Column.Overflow.WRAP);
            }

         }

         public TextTable(final Ansi ansi, final Column... columns) {
            this.columnValues = new ArrayList();
            this.indentWrappedLines = 2;
            this.ansi = (Ansi)CommandLine.Assert.notNull(ansi, "ansi");
            this.columns = (Column[])CommandLine.Assert.notNull(columns, "columns");
            if (columns.length == 0) {
               throw new IllegalArgumentException("At least one column is required");
            }
         }

         public Ansi.Text textAt(final int row, final int col) {
            return (Ansi.Text)this.columnValues.get(col + row * this.columns.length);
         }

         /** @deprecated */
         @Deprecated
         public Ansi.Text cellAt(final int row, final int col) {
            return this.textAt(row, col);
         }

         public int rowCount() {
            return this.columnValues.size() / this.columns.length;
         }

         public void addEmptyRow() {
            for(int i = 0; i < this.columns.length; ++i) {
               List var10000 = this.columnValues;
               Ansi var10003 = this.ansi;
               Objects.requireNonNull(var10003);
               var10000.add(var10003.new Text(this.columns[i].width));
            }

         }

         public void addRowValues(final String... values) {
            Ansi.Text[] array = new Ansi.Text[values.length];

            for(int i = 0; i < array.length; ++i) {
               Ansi.Text var10002;
               if (values[i] == null) {
                  var10002 = CommandLine.Help.Ansi.EMPTY_TEXT;
               } else {
                  Ansi var10004 = this.ansi;
                  Objects.requireNonNull(var10004);
                  var10002 = var10004.new Text(values[i]);
               }

               array[i] = var10002;
            }

            this.addRowValues(array);
         }

         public void addRowValues(final Ansi.Text... values) {
            if (values.length > this.columns.length) {
               throw new IllegalArgumentException(values.length + " values don't fit in " + this.columns.length + " columns");
            } else {
               this.addEmptyRow();

               for(int col = 0; col < values.length; ++col) {
                  int row = this.rowCount() - 1;
                  Cell cell = this.putValue(row, col, values[col]);
                  if ((cell.row != row || cell.column != col) && col != values.length - 1) {
                     this.addEmptyRow();
                  }
               }

            }
         }

         public Cell putValue(int row, int col, Ansi.Text value) {
            if (row > this.rowCount() - 1) {
               throw new IllegalArgumentException("Cannot write to row " + row + ": rowCount=" + this.rowCount());
            } else if (value != null && value.plain.length() != 0) {
               Column column = this.columns[col];
               int indent = column.indent;
               switch (column.overflow) {
                  case TRUNCATE:
                     copy(value, this.textAt(row, col), indent);
                     return new Cell(col, row);
                  case SPAN:
                     int startColumn = col;

                     do {
                        boolean lastColumn = col == this.columns.length - 1;
                        int charsWritten = lastColumn ? this.copy(BreakIterator.getLineInstance(), value, this.textAt(row, col), indent) : copy(value, this.textAt(row, col), indent);
                        value = value.substring(charsWritten);
                        indent = 0;
                        if (value.length > 0) {
                           ++col;
                        }

                        if (value.length > 0 && col >= this.columns.length) {
                           this.addEmptyRow();
                           ++row;
                           col = startColumn;
                           indent = column.indent + this.indentWrappedLines;
                        }
                     } while(value.length > 0);

                     return new Cell(col, row);
                  case WRAP:
                     BreakIterator lineBreakIterator = BreakIterator.getLineInstance();

                     do {
                        int charsWritten = this.copy(lineBreakIterator, value, this.textAt(row, col), indent);
                        value = value.substring(charsWritten);
                        indent = column.indent + this.indentWrappedLines;
                        if (value.length > 0) {
                           ++row;
                           this.addEmptyRow();
                        }
                     } while(value.length > 0);

                     return new Cell(col, row);
                  default:
                     throw new IllegalStateException(column.overflow.toString());
               }
            } else {
               return new Cell(col, row);
            }
         }

         private static int length(final Ansi.Text str) {
            return str.length;
         }

         private int copy(final BreakIterator line, final Ansi.Text text, final Ansi.Text columnValue, final int offset) {
            line.setText(text.plainString().replace("-", "ÿ"));
            int done = 0;
            int start = line.first();

            for(int end = line.next(); end != -1; end = line.next()) {
               Ansi.Text word = text.substring(start, end);
               if (columnValue.maxLength < offset + done + length(word)) {
                  break;
               }

               done += copy(word, columnValue, offset + done);
               start = end;
            }

            if (done == 0 && length(text) > columnValue.maxLength) {
               done = copy(text, columnValue, offset);
            }

            return done;
         }

         private static int copy(final Ansi.Text value, final Ansi.Text destination, final int offset) {
            int length = Math.min(value.length, destination.maxLength - offset);
            value.getStyledChars(value.from, length, destination, offset);
            return length;
         }

         public StringBuilder toString(final StringBuilder text) {
            int columnCount = this.columns.length;
            StringBuilder row = new StringBuilder(80);

            for(int i = 0; i < this.columnValues.size(); ++i) {
               Ansi.Text column = (Ansi.Text)this.columnValues.get(i);
               row.append(column.toString());
               row.append(new String(CommandLine.Help.spaces(this.columns[i % columnCount].width - column.length)));
               if (i % columnCount == columnCount - 1) {
                  int lastChar;
                  for(lastChar = row.length() - 1; lastChar >= 0 && row.charAt(lastChar) == ' '; --lastChar) {
                  }

                  row.setLength(lastChar + 1);
                  text.append(row.toString()).append(System.getProperty("line.separator"));
                  row.setLength(0);
               }
            }

            return text;
         }

         public String toString() {
            return this.toString(new StringBuilder()).toString();
         }

         public static class Cell {
            public final int column;
            public final int row;

            public Cell(final int column, final int row) {
               this.column = column;
               this.row = row;
            }
         }
      }

      public static class Column {
         public final int width;
         public final int indent;
         public final Overflow overflow;

         public Column(final int width, final int indent, final Overflow overflow) {
            this.width = width;
            this.indent = indent;
            this.overflow = (Overflow)CommandLine.Assert.notNull(overflow, "overflow");
         }

         public static enum Overflow {
            TRUNCATE,
            SPAN,
            WRAP;

            // $FF: synthetic method
            private static Overflow[] $values() {
               return new Overflow[]{TRUNCATE, SPAN, WRAP};
            }
         }
      }

      public static class ColorScheme {
         public final List commandStyles;
         public final List optionStyles;
         public final List parameterStyles;
         public final List optionParamStyles;
         private final Ansi ansi;

         public ColorScheme() {
            this(CommandLine.Help.Ansi.AUTO);
         }

         public ColorScheme(final Ansi ansi) {
            this.commandStyles = new ArrayList();
            this.optionStyles = new ArrayList();
            this.parameterStyles = new ArrayList();
            this.optionParamStyles = new ArrayList();
            this.ansi = (Ansi)CommandLine.Assert.notNull(ansi, "ansi");
         }

         public ColorScheme commands(final Ansi.IStyle... styles) {
            return this.addAll(this.commandStyles, styles);
         }

         public ColorScheme options(final Ansi.IStyle... styles) {
            return this.addAll(this.optionStyles, styles);
         }

         public ColorScheme parameters(final Ansi.IStyle... styles) {
            return this.addAll(this.parameterStyles, styles);
         }

         public ColorScheme optionParams(final Ansi.IStyle... styles) {
            return this.addAll(this.optionParamStyles, styles);
         }

         public Ansi.Text commandText(final String command) {
            return this.ansi().apply(command, this.commandStyles);
         }

         public Ansi.Text optionText(final String option) {
            return this.ansi().apply(option, this.optionStyles);
         }

         public Ansi.Text parameterText(final String parameter) {
            return this.ansi().apply(parameter, this.parameterStyles);
         }

         public Ansi.Text optionParamText(final String optionParam) {
            return this.ansi().apply(optionParam, this.optionParamStyles);
         }

         public ColorScheme applySystemProperties() {
            this.replace(this.commandStyles, System.getProperty("picocli.color.commands"));
            this.replace(this.optionStyles, System.getProperty("picocli.color.options"));
            this.replace(this.parameterStyles, System.getProperty("picocli.color.parameters"));
            this.replace(this.optionParamStyles, System.getProperty("picocli.color.optionParams"));
            return this;
         }

         private void replace(final List styles, final String property) {
            if (property != null) {
               styles.clear();
               this.addAll(styles, CommandLine.Help.Ansi.Style.parse(property));
            }

         }

         private ColorScheme addAll(final List styles, final Ansi.IStyle... add) {
            styles.addAll(Arrays.asList(add));
            return this;
         }

         public Ansi ansi() {
            return this.ansi;
         }
      }

      public static enum Ansi {
         AUTO,
         ON,
         OFF;

         static Text EMPTY_TEXT;
         static final boolean isWindows;
         static final boolean isXterm;
         static final boolean ISATTY;

         static final boolean calcTTY() {
            if (isWindows && isXterm) {
               return true;
            } else {
               try {
                  return System.class.getDeclaredMethod("console").invoke((Object)null) != null;
               } catch (Throwable var1) {
                  return true;
               }
            }
         }

         private static boolean ansiPossible() {
            return ISATTY && (!isWindows || isXterm);
         }

         public boolean enabled() {
            if (this == ON) {
               return true;
            } else if (this == OFF) {
               return false;
            } else {
               return System.getProperty("picocli.ansi") == null ? ansiPossible() : Boolean.getBoolean("picocli.ansi");
            }
         }

         public Text apply(final String plainText, final List styles) {
            if (plainText.length() == 0) {
               return new Text(0);
            } else {
               Text result = new Text(plainText.length());
               IStyle[] all = (IStyle[])styles.toArray(new IStyle[styles.size()]);
               result.sections.add(new StyledSection(0, plainText.length(), CommandLine.Help.Ansi.Style.on(all), CommandLine.Help.Ansi.Style.off((IStyle[])reverse(all)) + CommandLine.Help.Ansi.Style.reset.off()));
               result.plain.append(plainText);
               result.length = result.plain.length();
               return result;
            }
         }

         private static Object[] reverse(final Object[] all) {
            for(int i = 0; i < all.length / 2; ++i) {
               T temp = (T)all[i];
               all[i] = all[all.length - i - 1];
               all[all.length - i - 1] = temp;
            }

            return all;
         }

         // $FF: synthetic method
         private static Ansi[] $values() {
            return new Ansi[]{AUTO, ON, OFF};
         }

         static {
            Ansi var10002 = OFF;
            Objects.requireNonNull(var10002);
            EMPTY_TEXT = var10002.new Text(0);
            isWindows = System.getProperty("os.name").startsWith("Windows");
            isXterm = System.getenv("TERM") != null && System.getenv("TERM").startsWith("xterm");
            ISATTY = calcTTY();
         }

         public static enum Style implements IStyle {
            reset(0, 0),
            bold(1, 21),
            faint(2, 22),
            italic(3, 23),
            underline(4, 24),
            blink(5, 25),
            reverse(7, 27),
            fg_black(30, 39),
            fg_red(31, 39),
            fg_green(32, 39),
            fg_yellow(33, 39),
            fg_blue(34, 39),
            fg_magenta(35, 39),
            fg_cyan(36, 39),
            fg_white(37, 39),
            bg_black(40, 49),
            bg_red(41, 49),
            bg_green(42, 49),
            bg_yellow(43, 49),
            bg_blue(44, 49),
            bg_magenta(45, 49),
            bg_cyan(46, 49),
            bg_white(47, 49);

            private final int startCode;
            private final int endCode;

            private Style(final int startCode, final int endCode) {
               this.startCode = startCode;
               this.endCode = endCode;
            }

            public String on() {
               return "\u001b[" + this.startCode + "m";
            }

            public String off() {
               return "\u001b[" + this.endCode + "m";
            }

            public static String on(final IStyle... styles) {
               StringBuilder result = new StringBuilder();

               for(IStyle style : styles) {
                  result.append(style.on());
               }

               return result.toString();
            }

            public static String off(final IStyle... styles) {
               StringBuilder result = new StringBuilder();

               for(IStyle style : styles) {
                  result.append(style.off());
               }

               return result.toString();
            }

            public static IStyle fg(final String str) {
               try {
                  return valueOf(Strings.toRootLowerCase(str));
               } catch (Exception var3) {
                  try {
                     return valueOf("fg_" + Strings.toRootLowerCase(str));
                  } catch (Exception var2) {
                     return new Palette256Color(true, str);
                  }
               }
            }

            public static IStyle bg(final String str) {
               try {
                  return valueOf(Strings.toRootLowerCase(str));
               } catch (Exception var3) {
                  try {
                     return valueOf("bg_" + Strings.toRootLowerCase(str));
                  } catch (Exception var2) {
                     return new Palette256Color(false, str);
                  }
               }
            }

            public static IStyle[] parse(final String commaSeparatedCodes) {
               String[] codes = commaSeparatedCodes.split(",");
               IStyle[] styles = new IStyle[codes.length];

               for(int i = 0; i < codes.length; ++i) {
                  if (Strings.toRootLowerCase(codes[i]).startsWith("fg(")) {
                     int end = codes[i].indexOf(41);
                     styles[i] = fg(codes[i].substring(3, end < 0 ? codes[i].length() : end));
                  } else if (Strings.toRootLowerCase(codes[i]).startsWith("bg(")) {
                     int end = codes[i].indexOf(41);
                     styles[i] = bg(codes[i].substring(3, end < 0 ? codes[i].length() : end));
                  } else {
                     styles[i] = fg(codes[i]);
                  }
               }

               return styles;
            }

            // $FF: synthetic method
            private static Style[] $values() {
               return new Style[]{reset, bold, faint, italic, underline, blink, reverse, fg_black, fg_red, fg_green, fg_yellow, fg_blue, fg_magenta, fg_cyan, fg_white, bg_black, bg_red, bg_green, bg_yellow, bg_blue, bg_magenta, bg_cyan, bg_white};
            }
         }

         static class Palette256Color implements IStyle {
            private final int fgbg;
            private final int color;

            Palette256Color(final boolean foreground, final String color) {
               this.fgbg = foreground ? 38 : 48;
               String[] rgb = color.split(";");
               if (rgb.length == 3) {
                  this.color = 16 + 36 * Integer.decode(rgb[0]) + 6 * Integer.decode(rgb[1]) + Integer.decode(rgb[2]);
               } else {
                  this.color = Integer.decode(color);
               }

            }

            public String on() {
               return String.format("\u001b[%d;5;%dm", this.fgbg, this.color);
            }

            public String off() {
               return "\u001b[" + (this.fgbg + 1) + "m";
            }
         }

         private static class StyledSection {
            int startIndex;
            int length;
            String startStyles;
            String endStyles;

            StyledSection(final int start, final int len, final String style1, final String style2) {
               this.startIndex = start;
               this.length = len;
               this.startStyles = style1;
               this.endStyles = style2;
            }

            StyledSection withStartIndex(final int newStart) {
               return new StyledSection(newStart, this.length, this.startStyles, this.endStyles);
            }
         }

         public class Text implements Cloneable {
            private final int maxLength;
            private int from;
            private int length;
            private StringBuilder plain = new StringBuilder();
            private List sections = new ArrayList();

            public Text(final int maxLength) {
               this.maxLength = maxLength;
            }

            public Text(final String input) {
               this.maxLength = -1;
               this.plain.setLength(0);
               int i = 0;

               while(true) {
                  int j = input.indexOf("@|", i);
                  if (j == -1) {
                     if (i == 0) {
                        this.plain.append(input);
                        this.length = this.plain.length();
                        return;
                     }

                     this.plain.append(input.substring(i, input.length()));
                     this.length = this.plain.length();
                     return;
                  }

                  this.plain.append(input.substring(i, j));
                  int k = input.indexOf("|@", j);
                  if (k == -1) {
                     this.plain.append(input);
                     this.length = this.plain.length();
                     return;
                  }

                  j += 2;
                  String spec = input.substring(j, k);
                  String[] items = spec.split(" ", 2);
                  if (items.length == 1) {
                     this.plain.append(input);
                     this.length = this.plain.length();
                     return;
                  }

                  IStyle[] styles = CommandLine.Help.Ansi.Style.parse(items[0]);
                  this.addStyledSection(this.plain.length(), items[1].length(), CommandLine.Help.Ansi.Style.on(styles), CommandLine.Help.Ansi.Style.off((IStyle[])CommandLine.Help.Ansi.reverse(styles)) + CommandLine.Help.Ansi.Style.reset.off());
                  this.plain.append(items[1]);
                  i = k + 2;
               }
            }

            private void addStyledSection(final int start, final int length, final String startStyle, final String endStyle) {
               this.sections.add(new StyledSection(start, length, startStyle, endStyle));
            }

            public Object clone() {
               try {
                  return super.clone();
               } catch (CloneNotSupportedException e) {
                  throw new IllegalStateException(e);
               }
            }

            public Text[] splitLines() {
               List<Text> result = new ArrayList();
               boolean trailingEmptyString = false;
               int start = 0;
               int end = 0;

               for(int i = 0; i < this.plain.length(); end = i) {
                  char c;
                  boolean eol;
                  boolean var10001;
                  label48: {
                     c = this.plain.charAt(i);
                     eol = c == '\n';
                     if (c == '\r' && i + 1 < this.plain.length() && this.plain.charAt(i + 1) == '\n') {
                        ++i;
                        if (i > 0) {
                           var10001 = true;
                           break label48;
                        }
                     }

                     var10001 = false;
                  }

                  eol |= var10001;
                  eol |= c == '\r';
                  if (eol) {
                     result.add(this.substring(start, end));
                     trailingEmptyString = i == this.plain.length() - 1;
                     start = i + 1;
                  }

                  ++i;
               }

               if (start < this.plain.length() || trailingEmptyString) {
                  result.add(this.substring(start, this.plain.length()));
               }

               return (Text[])result.toArray(new Text[result.size()]);
            }

            public Text substring(final int start) {
               return this.substring(start, this.length);
            }

            public Text substring(final int start, final int end) {
               Text result = (Text)this.clone();
               result.from = this.from + start;
               result.length = end - start;
               return result;
            }

            public Text append(final String string) {
               return this.append(Ansi.this.new Text(string));
            }

            public Text append(final Text other) {
               Text result = (Text)this.clone();
               result.plain = new StringBuilder(this.plain.toString().substring(this.from, this.from + this.length));
               result.from = 0;
               result.sections = new ArrayList();

               for(StyledSection section : this.sections) {
                  result.sections.add(section.withStartIndex(section.startIndex - this.from));
               }

               result.plain.append(other.plain.toString().substring(other.from, other.from + other.length));

               for(StyledSection section : other.sections) {
                  int index = result.length + section.startIndex - other.from;
                  result.sections.add(section.withStartIndex(index));
               }

               result.length = result.plain.length();
               return result;
            }

            public void getStyledChars(final int from, final int length, final Text destination, final int offset) {
               if (destination.length < offset) {
                  for(int i = destination.length; i < offset; ++i) {
                     destination.plain.append(' ');
                  }

                  destination.length = offset;
               }

               for(StyledSection section : this.sections) {
                  destination.sections.add(section.withStartIndex(section.startIndex - from + destination.length));
               }

               destination.plain.append(this.plain.toString().substring(from, from + length));
               destination.length = destination.plain.length();
            }

            public String plainString() {
               return this.plain.toString().substring(this.from, this.from + this.length);
            }

            public boolean equals(final Object obj) {
               return this.toString().equals(String.valueOf(obj));
            }

            public int hashCode() {
               return this.toString().hashCode();
            }

            public String toString() {
               if (!Ansi.this.enabled()) {
                  return this.plain.toString().substring(this.from, this.from + this.length);
               } else if (this.length == 0) {
                  return "";
               } else {
                  StringBuilder sb = new StringBuilder(this.plain.length() + 20 * this.sections.size());
                  StyledSection current = null;
                  int end = Math.min(this.from + this.length, this.plain.length());

                  for(int i = this.from; i < end; ++i) {
                     StyledSection section = this.findSectionContaining(i);
                     if (section != current) {
                        if (current != null) {
                           sb.append(current.endStyles);
                        }

                        if (section != null) {
                           sb.append(section.startStyles);
                        }

                        current = section;
                     }

                     sb.append(this.plain.charAt(i));
                  }

                  if (current != null) {
                     sb.append(current.endStyles);
                  }

                  return sb.toString();
               }
            }

            private StyledSection findSectionContaining(final int index) {
               for(StyledSection section : this.sections) {
                  if (index >= section.startIndex && index < section.startIndex + section.length) {
                     return section;
                  }
               }

               return null;
            }
         }

         public interface IStyle {
            String CSI = "\u001b[";

            String on();

            String off();
         }
      }

      public interface IOptionRenderer {
         Ansi.Text[][] render(Option option, Field field, IParamLabelRenderer parameterLabelRenderer, ColorScheme scheme);
      }

      public interface IParamLabelRenderer {
         Ansi.Text renderParameterLabel(Field field, Ansi ansi, List styles);

         String separator();
      }

      public interface IParameterRenderer {
         Ansi.Text[][] render(Parameters parameters, Field field, IParamLabelRenderer parameterLabelRenderer, ColorScheme scheme);
      }
   }

   private static final class Assert {
      static Object notNull(final Object object, final String description) {
         if (object == null) {
            throw new NullPointerException(description);
         } else {
            return object;
         }
      }
   }

   private static enum TraceLevel {
      OFF,
      WARN,
      INFO,
      DEBUG;

      public boolean isEnabled(final TraceLevel other) {
         return this.ordinal() >= other.ordinal();
      }

      private void print(final Tracer tracer, final String msg, final Object... params) {
         if (tracer.level.isEnabled(this)) {
            tracer.stream.printf(this.prefix(msg), params);
         }

      }

      private String prefix(final String msg) {
         return "[picocli " + this + "] " + msg;
      }

      static TraceLevel lookup(final String key) {
         return key == null ? WARN : (!CommandLine.empty(key) && !"true".equalsIgnoreCase(key) ? valueOf(key) : INFO);
      }

      // $FF: synthetic method
      private static TraceLevel[] $values() {
         return new TraceLevel[]{OFF, WARN, INFO, DEBUG};
      }
   }

   private static class Tracer {
      TraceLevel level;
      PrintStream stream;

      private Tracer() {
         this.level = CommandLine.TraceLevel.lookup(System.getProperty("picocli.trace"));
         this.stream = System.err;
      }

      void warn(final String msg, final Object... params) {
         CommandLine.TraceLevel.WARN.print(this, msg, params);
      }

      void info(final String msg, final Object... params) {
         CommandLine.TraceLevel.INFO.print(this, msg, params);
      }

      void debug(final String msg, final Object... params) {
         CommandLine.TraceLevel.DEBUG.print(this, msg, params);
      }

      boolean isWarn() {
         return this.level.isEnabled(CommandLine.TraceLevel.WARN);
      }

      boolean isInfo() {
         return this.level.isEnabled(CommandLine.TraceLevel.INFO);
      }

      boolean isDebug() {
         return this.level.isEnabled(CommandLine.TraceLevel.DEBUG);
      }
   }

   public static class PicocliException extends RuntimeException {
      private static final long serialVersionUID = -2574128880125050818L;

      public PicocliException(final String msg) {
         super(msg);
      }

      public PicocliException(final String msg, final Exception ex) {
         super(msg, ex);
      }
   }

   public static class InitializationException extends PicocliException {
      private static final long serialVersionUID = 8423014001666638895L;

      public InitializationException(final String msg) {
         super(msg);
      }

      public InitializationException(final String msg, final Exception ex) {
         super(msg, ex);
      }
   }

   public static class ExecutionException extends PicocliException {
      private static final long serialVersionUID = 7764539594267007998L;
      private final CommandLine commandLine;

      public ExecutionException(final CommandLine commandLine, final String msg) {
         super(msg);
         this.commandLine = (CommandLine)CommandLine.Assert.notNull(commandLine, "commandLine");
      }

      public ExecutionException(final CommandLine commandLine, final String msg, final Exception ex) {
         super(msg, ex);
         this.commandLine = (CommandLine)CommandLine.Assert.notNull(commandLine, "commandLine");
      }

      public CommandLine getCommandLine() {
         return this.commandLine;
      }
   }

   public static class TypeConversionException extends PicocliException {
      private static final long serialVersionUID = 4251973913816346114L;

      public TypeConversionException(final String msg) {
         super(msg);
      }
   }

   public static class ParameterException extends PicocliException {
      private static final long serialVersionUID = 1477112829129763139L;
      private final CommandLine commandLine;

      public ParameterException(final CommandLine commandLine, final String msg) {
         super(msg);
         this.commandLine = (CommandLine)CommandLine.Assert.notNull(commandLine, "commandLine");
      }

      public ParameterException(final CommandLine commandLine, final String msg, final Exception ex) {
         super(msg, ex);
         this.commandLine = (CommandLine)CommandLine.Assert.notNull(commandLine, "commandLine");
      }

      public CommandLine getCommandLine() {
         return this.commandLine;
      }

      private static ParameterException create(final CommandLine cmd, final Exception ex, final String arg, final int i, final String[] args) {
         String msg = ex.getClass().getSimpleName() + ": " + ex.getLocalizedMessage() + " while processing argument at or before arg[" + i + "] '" + arg + "' in " + Arrays.toString(args) + ": " + ex.toString();
         return new ParameterException(cmd, msg, ex);
      }
   }

   public static class MissingParameterException extends ParameterException {
      private static final long serialVersionUID = 5075678535706338753L;

      public MissingParameterException(final CommandLine commandLine, final String msg) {
         super(commandLine, msg);
      }

      private static MissingParameterException create(final CommandLine cmd, final Collection missing, final String separator) {
         if (missing.size() == 1) {
            return new MissingParameterException(cmd, "Missing required option '" + describe((Field)missing.iterator().next(), separator) + "'");
         } else {
            List<String> names = new ArrayList(missing.size());

            for(Field field : missing) {
               names.add(describe(field, separator));
            }

            return new MissingParameterException(cmd, "Missing required options " + names.toString());
         }
      }

      private static String describe(final Field field, final String separator) {
         String prefix = field.isAnnotationPresent(Option.class) ? ((Option)field.getAnnotation(Option.class)).names()[0] + separator : "params[" + ((Parameters)field.getAnnotation(Parameters.class)).index() + "]" + separator;
         return prefix + CommandLine.Help.DefaultParamLabelRenderer.renderParameterName(field);
      }
   }

   public static class DuplicateOptionAnnotationsException extends InitializationException {
      private static final long serialVersionUID = -3355128012575075641L;

      public DuplicateOptionAnnotationsException(final String msg) {
         super(msg);
      }

      private static DuplicateOptionAnnotationsException create(final String name, final Field field1, final Field field2) {
         return new DuplicateOptionAnnotationsException("Option name '" + name + "' is used by both " + field1.getDeclaringClass().getName() + "." + field1.getName() + " and " + field2.getDeclaringClass().getName() + "." + field2.getName());
      }
   }

   public static class ParameterIndexGapException extends InitializationException {
      private static final long serialVersionUID = -1520981133257618319L;

      public ParameterIndexGapException(final String msg) {
         super(msg);
      }
   }

   public static class UnmatchedArgumentException extends ParameterException {
      private static final long serialVersionUID = -8700426380701452440L;

      public UnmatchedArgumentException(final CommandLine commandLine, final String msg) {
         super(commandLine, msg);
      }

      public UnmatchedArgumentException(final CommandLine commandLine, final Stack args) {
         this(commandLine, (List)(new ArrayList(CommandLine.reverse(args))));
      }

      public UnmatchedArgumentException(final CommandLine commandLine, final List args) {
         this(commandLine, "Unmatched argument" + (args.size() == 1 ? " " : "s ") + args);
      }
   }

   public static class MaxValuesforFieldExceededException extends ParameterException {
      private static final long serialVersionUID = 6536145439570100641L;

      public MaxValuesforFieldExceededException(final CommandLine commandLine, final String msg) {
         super(commandLine, msg);
      }
   }

   public static class OverwrittenOptionException extends ParameterException {
      private static final long serialVersionUID = 1338029208271055776L;

      public OverwrittenOptionException(final CommandLine commandLine, final String msg) {
         super(commandLine, msg);
      }
   }

   public static class MissingTypeConverterException extends ParameterException {
      private static final long serialVersionUID = -6050931703233083760L;

      public MissingTypeConverterException(final CommandLine commandLine, final String msg) {
         super(commandLine, msg);
      }
   }

   @Retention(RetentionPolicy.RUNTIME)
   @Target({ElementType.TYPE, ElementType.LOCAL_VARIABLE, ElementType.PACKAGE})
   public @interface Command {
      String name() default "<main class>";

      Class[] subcommands() default {};

      String separator() default "=";

      String[] version() default {};

      String headerHeading() default "";

      String[] header() default {};

      String synopsisHeading() default "Usage: ";

      boolean abbreviateSynopsis() default false;

      String[] customSynopsis() default {};

      String descriptionHeading() default "";

      String[] description() default {};

      String parameterListHeading() default "";

      String optionListHeading() default "";

      boolean sortOptions() default true;

      char requiredOptionMarker() default ' ';

      boolean showDefaultValues() default false;

      String commandListHeading() default "Commands:%n";

      String footerHeading() default "";

      String[] footer() default {};
   }

   public interface IExceptionHandler {
      List handleException(ParameterException ex, PrintStream out, Help.Ansi ansi, String... args);
   }

   public interface IParseResultHandler {
      List handleParseResult(List parsedCommands, PrintStream out, Help.Ansi ansi) throws ExecutionException;
   }

   public interface ITypeConverter {
      Object convert(String value) throws Exception;
   }

   @Retention(RetentionPolicy.RUNTIME)
   @Target({ElementType.FIELD})
   public @interface Option {
      String[] names();

      boolean required() default false;

      /** @deprecated */
      boolean help() default false;

      boolean usageHelp() default false;

      boolean versionHelp() default false;

      String[] description() default {};

      String arity() default "";

      String paramLabel() default "";

      Class[] type() default {};

      String split() default "";

      boolean hidden() default false;
   }

   @Retention(RetentionPolicy.RUNTIME)
   @Target({ElementType.FIELD})
   public @interface Parameters {
      String index() default "*";

      String[] description() default {};

      String arity() default "";

      String paramLabel() default "";

      Class[] type() default {};

      String split() default "";

      boolean hidden() default false;
   }
}
