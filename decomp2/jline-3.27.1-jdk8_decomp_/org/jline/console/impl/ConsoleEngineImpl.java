package org.jline.console.impl;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.jline.builtins.Completers;
import org.jline.builtins.ConfigurationPath;
import org.jline.builtins.Options;
import org.jline.builtins.Styles;
import org.jline.console.CommandInput;
import org.jline.console.CommandMethods;
import org.jline.console.CommandRegistry;
import org.jline.console.ConsoleEngine;
import org.jline.console.Printer;
import org.jline.console.ScriptEngine;
import org.jline.console.SystemRegistry;
import org.jline.reader.Candidate;
import org.jline.reader.Completer;
import org.jline.reader.EOFError;
import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.ParsedLine;
import org.jline.reader.Parser;
import org.jline.reader.SyntaxError;
import org.jline.reader.impl.completer.AggregateCompleter;
import org.jline.reader.impl.completer.ArgumentCompleter;
import org.jline.reader.impl.completer.NullCompleter;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.terminal.Terminal;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;
import org.jline.utils.Log;
import org.jline.utils.OSUtils;

public class ConsoleEngineImpl extends JlineCommandRegistry implements ConsoleEngine {
   private static final String VAR_CONSOLE_OPTIONS = "CONSOLE_OPTIONS";
   private static final String VAR_PATH = "PATH";
   private static final String[] OPTION_HELP = new String[]{"-?", "--help"};
   private static final String OPTION_VERBOSE = "-v";
   private static final String SLURP_FORMAT_TEXT = "TEXT";
   private static final String END_HELP = "END_HELP";
   private static final int HELP_MAX_SIZE = 30;
   private final ScriptEngine engine;
   private Exception exception;
   private SystemRegistry systemRegistry;
   private String scriptExtension;
   private final Supplier workDir;
   private final Map aliases;
   private final Map pipes;
   private Path aliasFile;
   private LineReader reader;
   private boolean executing;
   private final Printer printer;

   public ConsoleEngineImpl(ScriptEngine engine, Printer printer, Supplier workDir, ConfigurationPath configPath) throws IOException {
      this((Set)null, engine, printer, workDir, configPath);
   }

   public ConsoleEngineImpl(Set commands, ScriptEngine engine, Printer printer, Supplier workDir, ConfigurationPath configPath) throws IOException {
      this.scriptExtension = "jline";
      this.aliases = new HashMap();
      this.pipes = new HashMap();
      this.executing = false;
      this.engine = engine;
      this.workDir = workDir;
      this.printer = printer;
      Map<Command, String> commandName = new HashMap();
      Map<Command, CommandMethods> commandExecute = new HashMap();
      Set<Command> cmds;
      if (commands == null) {
         cmds = new HashSet(EnumSet.allOf(Command.class));
      } else {
         cmds = new HashSet(commands);
      }

      for(Command c : cmds) {
         commandName.put(c, c.name().toLowerCase());
      }

      commandExecute.put(ConsoleEngineImpl.Command.DEL, new CommandMethods(this::del, this::variableCompleter));
      commandExecute.put(ConsoleEngineImpl.Command.SHOW, new CommandMethods(this::show, this::variableCompleter));
      commandExecute.put(ConsoleEngineImpl.Command.PRNT, new CommandMethods(this::prnt, this::prntCompleter));
      commandExecute.put(ConsoleEngineImpl.Command.SLURP, new CommandMethods(this::slurpcmd, this::slurpCompleter));
      commandExecute.put(ConsoleEngineImpl.Command.ALIAS, new CommandMethods(this::aliascmd, this::aliasCompleter));
      commandExecute.put(ConsoleEngineImpl.Command.UNALIAS, new CommandMethods(this::unalias, this::unaliasCompleter));
      commandExecute.put(ConsoleEngineImpl.Command.DOC, new CommandMethods(this::doc, this::docCompleter));
      commandExecute.put(ConsoleEngineImpl.Command.PIPE, new CommandMethods(this::pipe, this::defaultCompleter));
      this.aliasFile = configPath.getUserConfig("aliases.json");
      if (this.aliasFile == null) {
         this.aliasFile = configPath.getUserConfig("aliases.json", true);
         if (this.aliasFile == null) {
            Log.warn("Failed to write in user config path!");
            this.aliasFile = OSUtils.IS_WINDOWS ? Paths.get("NUL") : Paths.get("/dev/null");
         }

         this.persist(this.aliasFile, this.aliases);
      } else {
         this.aliases.putAll((Map)this.slurp(this.aliasFile));
      }

      this.registerCommands(commandName, commandExecute);
   }

   public void setLineReader(LineReader reader) {
      this.reader = reader;
   }

   private Parser parser() {
      return this.reader.getParser();
   }

   private Terminal terminal() {
      return this.systemRegistry.terminal();
   }

   public boolean isExecuting() {
      return this.executing;
   }

   public void setSystemRegistry(SystemRegistry systemRegistry) {
      this.systemRegistry = systemRegistry;
   }

   public void setScriptExtension(String extension) {
      this.scriptExtension = extension;
   }

   public boolean hasAlias(String name) {
      return this.aliases.containsKey(name);
   }

   public String getAlias(String name) {
      return (String)this.aliases.getOrDefault(name, (Object)null);
   }

   public Map getPipes() {
      return this.pipes;
   }

   public List getNamedPipes() {
      List<String> out = new ArrayList();
      List<String> opers = new ArrayList();

      for(String p : this.pipes.keySet()) {
         if (p.matches("[a-zA-Z0-9]+")) {
            out.add(p);
         } else {
            opers.add(p);
         }
      }

      opers.addAll(this.systemRegistry.getPipeNames());

      for(Map.Entry entry : this.aliases.entrySet()) {
         if (opers.contains(((String)entry.getValue()).split(" ")[0])) {
            out.add((String)entry.getKey());
         }
      }

      return out;
   }

   public List scriptCompleters() {
      List<Completer> out = new ArrayList();
      out.add(new ArgumentCompleter(new Completer[]{new StringsCompleter(this::scriptNames), new Completers.OptionCompleter(NullCompleter.INSTANCE, this::commandOptions, 1)}));
      out.add(new ArgumentCompleter(new Completer[]{new StringsCompleter(this::commandAliasNames), NullCompleter.INSTANCE}));
      return out;
   }

   private Set commandAliasNames() {
      Set<String> opers = (Set)this.pipes.keySet().stream().filter((p) -> !p.matches("\\w+")).collect(Collectors.toSet());
      opers.addAll(this.systemRegistry.getPipeNames());
      return (Set)this.aliases.entrySet().stream().filter((e) -> !opers.contains(((String)e.getValue()).split(" ")[0])).map(Map.Entry::getKey).collect(Collectors.toSet());
   }

   private Set scriptNames() {
      return this.scripts().keySet();
   }

   public Map scripts() {
      Map<String, Boolean> out = new HashMap();

      try {
         List<Path> scripts = new ArrayList();
         if (this.engine.hasVariable("PATH")) {
            List<String> dirs = new ArrayList();

            for(String file : (List)this.engine.get("PATH")) {
               file = file.startsWith("~") ? file.replace("~", System.getProperty("user.home")) : file;
               File dir = new File(file);
               if (dir.exists() && dir.isDirectory()) {
                  dirs.add(file);
               }
            }

            for(String pp : dirs) {
               for(String e : this.scriptExtensions()) {
                  String regex = pp + "/*." + e;
                  PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + regex);
                  Stream<Path> pathStream = Files.walk((new File(regex)).getParentFile().toPath());

                  try {
                     Objects.requireNonNull(pathMatcher);
                     Stream var10000 = pathStream.filter(pathMatcher::matches);
                     Objects.requireNonNull(scripts);
                     var10000.forEach(scripts::add);
                  } catch (Throwable var14) {
                     if (pathStream != null) {
                        try {
                           pathStream.close();
                        } catch (Throwable var13) {
                           var14.addSuppressed(var13);
                        }
                     }

                     throw var14;
                  }

                  if (pathStream != null) {
                     pathStream.close();
                  }
               }
            }
         }

         for(Path p : scripts) {
            String name = p.toFile().getName();
            int idx = name.lastIndexOf(".");
            out.put(name.substring(0, idx), name.substring(idx + 1).equals(this.scriptExtension));
         }
      } catch (NoSuchFileException e) {
         this.error("Failed reading PATH. No file found: " + e.getMessage());
      } catch (InvalidPathException e) {
         this.error("Failed reading PATH. Invalid path:");
         this.error(e.toString());
      } catch (Exception e) {
         this.error("Failed reading PATH:");
         this.trace(e);
         this.engine.put("exception", e);
      }

      return out;
   }

   public Object[] expandParameters(String[] args) throws Exception {
      Object[] out = new Object[args.length];
      String regexPath = "(.*)\\$\\{(.*?)}(/.*)";

      for(int i = 0; i < args.length; ++i) {
         if (args[i].matches(regexPath)) {
            Matcher matcher = Pattern.compile(regexPath).matcher(args[i]);
            if (!matcher.find()) {
               throw new IllegalArgumentException();
            }

            out[i] = matcher.group(1) + this.engine.get(matcher.group(2)) + matcher.group(3);
         } else if (args[i].startsWith("${")) {
            out[i] = this.engine.execute(this.expandName(args[i]));
         } else if (args[i].startsWith("$")) {
            out[i] = this.engine.get(this.expandName(args[i]));
         } else {
            out[i] = this.engine.deserialize(args[i]);
         }
      }

      return out;
   }

   private String expandToList(String[] args) {
      return this.expandToList(Arrays.asList(args));
   }

   public String expandToList(List params) {
      StringBuilder sb = new StringBuilder();
      sb.append("[");
      boolean first = true;

      for(String param : params) {
         if (!first) {
            sb.append(",");
         }

         if (!param.equalsIgnoreCase("true") && !param.equalsIgnoreCase("false") && !param.equalsIgnoreCase("null")) {
            if (this.isNumber(param)) {
               sb.append(param);
            } else {
               sb.append(param.startsWith("$") ? param.substring(1) : this.quote(param));
            }
         } else {
            sb.append(param.toLowerCase());
         }

         first = false;
      }

      sb.append("]");
      return sb.toString();
   }

   private String expandName(String name) {
      String regexVar = "[a-zA-Z_]+[a-zA-Z0-9_-]*";
      String out = name;
      if (name.matches("^\\$" + regexVar)) {
         out = name.substring(1);
      } else if (name.matches("^\\$\\{" + regexVar + "}.*")) {
         Matcher matcher = Pattern.compile("^\\$\\{(" + regexVar + ")}(.*)").matcher(name);
         if (!matcher.find()) {
            throw new IllegalArgumentException();
         }

         out = matcher.group(1) + matcher.group(2);
      }

      return out;
   }

   private boolean isNumber(String str) {
      return str.matches("-?\\d+(\\.\\d+)?");
   }

   private boolean isCodeBlock(String line) {
      return line.contains("\n") && line.trim().endsWith("}");
   }

   private boolean isCommandLine(String line) {
      String command = this.parser().getCommand(line);
      boolean out = false;
      if (command != null && command.startsWith(":")) {
         command = command.substring(1);
         if (this.hasAlias(command)) {
            command = this.getAlias(command);
         }

         if (this.systemRegistry.hasCommand(command)) {
            out = true;
         } else {
            ScriptFile sf = new ScriptFile(command, "", new String[0]);
            if (sf.isScript()) {
               out = true;
            }
         }
      }

      return out;
   }

   private String quote(String var) {
      if ((!var.startsWith("\"") || !var.endsWith("\"")) && (!var.startsWith("'") || !var.endsWith("'"))) {
         return var.contains("\\\"") ? "'" + var + "'" : "\"" + var + "\"";
      } else {
         return var;
      }
   }

   private List scriptExtensions() {
      List<String> extensions = new ArrayList(this.engine.getExtensions());
      extensions.add(this.scriptExtension);
      return extensions;
   }

   public Object execute(File script, String cmdLine, String[] args) throws Exception {
      ScriptFile file = new ScriptFile(script, cmdLine, args);
      file.execute();
      return file.getResult();
   }

   public String expandCommandLine(String line) {
      String out;
      if (this.isCommandLine(line)) {
         StringBuilder sb = new StringBuilder();
         List<String> ws = this.parser().parse(line, 0, Parser.ParseContext.COMPLETE).words();
         int idx = ((String)ws.get(0)).lastIndexOf(":");
         if (idx > 0) {
            sb.append(((String)ws.get(0)).substring(0, idx));
         }

         String[] argv = new String[ws.size()];

         for(int i = 1; i < ws.size(); ++i) {
            argv[i] = (String)ws.get(i);
            if (argv[i].startsWith("${")) {
               Matcher argvMatcher = Pattern.compile("\\$\\{(.*)}").matcher(argv[i]);
               if (argvMatcher.find()) {
                  argv[i] = argv[i].replace(argv[i], argvMatcher.group(1));
               }
            } else if (argv[i].startsWith("$")) {
               argv[i] = argv[i].substring(1);
            } else {
               argv[i] = this.quote(argv[i]);
            }
         }

         String cmd = this.hasAlias(((String)ws.get(0)).substring(idx + 1)) ? this.getAlias(((String)ws.get(0)).substring(idx + 1)) : ((String)ws.get(0)).substring(idx + 1);
         sb.append(SystemRegistry.class.getCanonicalName()).append(".get().invoke('").append(cmd).append("'");

         for(int i = 1; i < argv.length; ++i) {
            sb.append(", ");
            sb.append(argv[i]);
         }

         sb.append(")");
         out = sb.toString();
      } else {
         out = line;
      }

      return out;
   }

   public Object execute(String cmd, String line, String[] args) throws Exception {
      if (line.trim().startsWith("#")) {
         return null;
      } else {
         Object out = null;
         ScriptFile file = null;
         if (this.parser().validCommandName(cmd)) {
            file = new ScriptFile(cmd, line, args);
         } else {
            File f = new File(line.split("\\s+")[0]);
            if (f.exists()) {
               file = new ScriptFile(f, line, args);
            }
         }

         if (file != null && file.execute()) {
            out = file.getResult();
         } else {
            line = line.trim();
            if (this.isCodeBlock(line)) {
               StringBuilder sb = new StringBuilder();

               for(String s : line.split("\\r?\\n")) {
                  sb.append(this.expandCommandLine(s));
                  sb.append("\n");
               }

               line = sb.toString();
            }

            if (this.engine.hasVariable(line)) {
               out = this.engine.get(line);
            } else if (this.parser().getVariable(line) == null) {
               out = this.engine.execute(line);
               this.engine.put("_", out);
            } else {
               this.engine.execute(line);
            }
         }

         return out;
      }
   }

   public void purge() {
      this.engine.del("_*");
   }

   public void putVariable(String name, Object value) {
      this.engine.put(name, value);
   }

   public Object getVariable(String name) {
      if (!this.engine.hasVariable(name)) {
         throw new IllegalArgumentException("Variable " + name + " does not exists!");
      } else {
         return this.engine.get(name);
      }
   }

   public boolean hasVariable(String name) {
      return this.engine.hasVariable(name);
   }

   public boolean executeWidget(Object function) {
      this.engine.put("_reader", this.reader);
      this.engine.put("_widgetFunction", function);

      boolean var3;
      try {
         if (this.engine.getEngineName().equals("GroovyEngine")) {
            this.engine.execute("def _buffer() {_reader.getBuffer()}");
            this.engine.execute("def _widget(w) {_reader.callWidget(w)}");
         }

         this.engine.execute("_widgetFunction()");
         return true;
      } catch (Exception e) {
         this.trace(e);
         var3 = false;
      } finally {
         this.purge();
      }

      return var3;
   }

   private Map consoleOptions() {
      return (Map)(this.engine.hasVariable("CONSOLE_OPTIONS") ? (Map)this.engine.get("CONSOLE_OPTIONS") : new HashMap());
   }

   public Object consoleOption(String option, Object defval) {
      T out = defval;

      try {
         out = (T)this.consoleOptions().getOrDefault(option, defval);
      } catch (Exception e) {
         this.trace(new Exception("Bad CONSOLE_OPTION value: " + e.getMessage()));
      }

      return out;
   }

   public void setConsoleOption(String name, Object value) {
      this.consoleOptions().put(name, value);
   }

   private boolean consoleOption(String option) {
      boolean out = false;

      try {
         out = this.consoleOptions().containsKey(option);
      } catch (Exception e) {
         this.trace(new Exception("Bad CONSOLE_OPTION value: " + e.getMessage()));
      }

      return out;
   }

   public ConsoleEngine.ExecutionResult postProcess(String line, Object result, String output) {
      Object _output = output != null && !output.trim().isEmpty() && !this.consoleOption("no-splittedOutput") ? output.split("\\r?\\n") : output;
      String consoleVar = this.parser().getVariable(line);
      if (consoleVar != null && result != null) {
         this.engine.put("output", _output);
      }

      ConsoleEngine.ExecutionResult out;
      if (this.systemRegistry.hasCommand(this.parser().getCommand(line))) {
         out = this.postProcess(line, consoleVar != null && result == null ? _output : result);
      } else {
         Object _result = result == null ? _output : result;
         int status = this.saveResult(consoleVar, _result);
         out = new ConsoleEngine.ExecutionResult(status, consoleVar != null && !consoleVar.startsWith("_") ? null : _result);
      }

      return out;
   }

   private ConsoleEngine.ExecutionResult postProcess(String line, Object result) {
      int status = 0;
      Object out = result instanceof String && ((String)result).trim().isEmpty() ? null : result;
      String consoleVar = this.parser().getVariable(line);
      if (consoleVar != null) {
         status = this.saveResult(consoleVar, result);
         out = null;
      } else if (!this.parser().getCommand(line).equals("show")) {
         if (result != null) {
            status = this.saveResult("_", result);
         } else {
            status = 1;
         }
      }

      return new ConsoleEngine.ExecutionResult(status, out);
   }

   public ConsoleEngine.ExecutionResult postProcess(Object result) {
      return new ConsoleEngine.ExecutionResult(this.saveResult((String)null, result), result);
   }

   private int saveResult(String var, Object result) {
      int out;
      try {
         this.engine.put("_executionResult", result);
         if (var != null) {
            if (!var.contains(".") && !var.contains("[")) {
               this.engine.put(var, result);
            } else {
               this.engine.execute(var + " = _executionResult");
            }
         }

         out = (Integer)this.engine.execute("_executionResult ? 0 : 1");
      } catch (Exception e) {
         this.trace(e);
         out = 1;
      }

      return out;
   }

   public Object invoke(CommandRegistry.CommandSession session, String command, Object... args) throws Exception {
      this.exception = null;
      Object out = null;
      if (this.hasCommand(command)) {
         out = this.getCommandMethods(command).execute().apply(new CommandInput(command, args, session));
      } else {
         String[] _args = new String[args.length];

         for(int i = 0; i < args.length; ++i) {
            if (!(args[i] instanceof String)) {
               throw new IllegalArgumentException();
            }

            _args[i] = args[i].toString();
         }

         ScriptFile sf = new ScriptFile(command, "", _args);
         if (sf.execute()) {
            out = sf.getResult();
         }
      }

      if (this.exception != null) {
         throw this.exception;
      } else {
         return out;
      }
   }

   public void trace(Object object) {
      Object toPrint = object;
      int level = (Integer)this.consoleOption("trace", 0);
      Map<String, Object> options = new HashMap();
      if (level < 2) {
         options.put("exception", "message");
      }

      if (level == 0) {
         if (!(object instanceof Throwable)) {
            toPrint = null;
         }
      } else if (level == 1) {
         if (object instanceof SystemRegistryImpl.CommandData) {
            toPrint = ((SystemRegistryImpl.CommandData)object).rawLine();
         }
      } else if (level > 1 && object instanceof SystemRegistryImpl.CommandData) {
         toPrint = object.toString();
      }

      this.printer.println(options, toPrint);
   }

   private void error(String message) {
      AttributedStringBuilder asb = new AttributedStringBuilder();
      asb.styled((AttributedStyle)Styles.prntStyle().resolve(".em"), (CharSequence)message);
      asb.println(this.terminal());
   }

   public void println(Object object) {
      this.printer.println(object);
   }

   private Object show(CommandInput input) {
      String[] usage = new String[]{"show -  list console variables", "Usage: show [VARIABLE]", "  -? --help                       Displays command help"};

      try {
         this.parseOptions(usage, input.args());
         Map<String, Object> options = new HashMap();
         options.put("maxDepth", 0);
         this.printer.println(options, this.engine.find(input.args().length > 0 ? input.args()[0] : null));
      } catch (Exception e) {
         this.exception = e;
      }

      return null;
   }

   private Object del(CommandInput input) {
      String[] usage = new String[]{"del -  delete console variables, methods, classes and imports", "Usage: del [var1] ...", "  -? --help                       Displays command help"};

      try {
         this.parseOptions(usage, input.args());
         this.engine.del(input.args());
      } catch (Exception e) {
         this.exception = e;
      }

      return null;
   }

   private Object prnt(CommandInput input) {
      Exception result = this.printer.prntCommand(input);
      if (result != null) {
         this.exception = result;
      }

      return null;
   }

   private Object slurpcmd(CommandInput input) {
      String[] usage = new String[]{"slurp -  slurp file or string variable context to object", "Usage: slurp [OPTIONS] file|variable", "  -? --help                       Displays command help", "  -e --encoding=ENCODING          Encoding (default UTF-8)", "  -f --format=FORMAT              Serialization format"};
      Object out = null;

      try {
         Options opt = this.parseOptions(usage, input.xargs());
         if (!opt.args().isEmpty()) {
            Object _arg = opt.argObjects().get(0);
            if (!(_arg instanceof String)) {
               throw new IllegalArgumentException("Invalid parameter type: " + _arg.getClass().getSimpleName());
            }

            String arg = (String)_arg;
            Charset encoding = opt.isSet("encoding") ? Charset.forName(opt.get("encoding")) : StandardCharsets.UTF_8;
            String format = opt.isSet("format") ? opt.get("format") : (String)this.engine.getSerializationFormats().get(0);

            try {
               Path path = Paths.get(arg);
               if (path.toFile().exists()) {
                  if (!format.equals("TEXT")) {
                     out = this.slurp(path, encoding, format);
                  } else {
                     out = Files.readAllLines(Paths.get(arg), encoding);
                  }
               } else if (!format.equals("TEXT")) {
                  out = this.engine.deserialize(arg, format);
               } else {
                  out = arg.split("\n");
               }
            } catch (Exception var10) {
               out = this.engine.deserialize(arg, format);
            }
         }
      } catch (Exception e) {
         this.exception = e;
      }

      return out;
   }

   public void persist(Path file, Object object) {
      this.engine.persist(file, object);
   }

   public Object slurp(Path file) throws IOException {
      return this.slurp(file, StandardCharsets.UTF_8, (String)this.engine.getSerializationFormats().get(0));
   }

   private Object slurp(Path file, Charset encoding, String format) throws IOException {
      byte[] encoded = Files.readAllBytes(file);
      return this.engine.deserialize(new String(encoded, encoding), format);
   }

   private Object aliascmd(CommandInput input) {
      String[] usage = new String[]{"alias -  create command alias", "Usage: alias [ALIAS] [COMMANDLINE]", "  -? --help                       Displays command help"};
      Object out = null;

      try {
         Options opt = this.parseOptions(usage, input.args());
         List<String> args = opt.args();
         if (args.isEmpty()) {
            out = this.aliases;
         } else if (args.size() == 1) {
            out = this.aliases.getOrDefault(args.get(0), (Object)null);
         } else {
            String alias = String.join(" ", args.subList(1, args.size()));

            for(int j = 0; j < 10; ++j) {
               alias = alias.replaceAll("%" + j, "\\$" + j);
               alias = alias.replaceAll("%\\{" + j + "}", "\\$\\{" + j + "\\}");
               alias = alias.replaceAll("%\\{" + j + ":-", "\\$\\{" + j + ":-");
            }

            alias = alias.replaceAll("%@", "\\$@");
            alias = alias.replaceAll("%\\{@}", "\\$\\{@\\}");
            this.aliases.put((String)args.get(0), alias);
            this.persist(this.aliasFile, this.aliases);
         }
      } catch (Exception e) {
         this.exception = e;
      }

      return out;
   }

   private Object unalias(CommandInput input) {
      String[] usage = new String[]{"unalias -  remove command alias", "Usage: unalias [ALIAS...]", "  -? --help                       Displays command help"};

      try {
         Options opt = this.parseOptions(usage, input.args());

         for(String a : opt.args()) {
            this.aliases.remove(a);
         }

         this.persist(this.aliasFile, this.aliases);
      } catch (Exception e) {
         this.exception = e;
      }

      return null;
   }

   private Object pipe(CommandInput input) {
      String[] usage = new String[]{"pipe -  create/delete pipe operator", "Usage: pipe [OPERATOR] [PREFIX] [POSTFIX]", "       pipe --list", "       pipe --delete [OPERATOR...]", "  -? --help                       Displays command help", "  -d --delete                     Delete pipe operators", "  -l --list                       List pipe operators"};

      try {
         Options opt = this.parseOptions(usage, input.args());
         Map<String, Object> options = new HashMap();
         if (opt.isSet("delete")) {
            if (opt.args().size() == 1 && ((String)opt.args().get(0)).equals("*")) {
               this.pipes.clear();
            } else {
               for(String p : opt.args()) {
                  this.pipes.remove(p.trim());
               }
            }
         } else if (!opt.isSet("list") && opt.args().size() != 0) {
            if (opt.args().size() != 3) {
               this.exception = new IllegalArgumentException("Bad number of arguments!");
            } else if (this.systemRegistry.getPipeNames().contains(opt.args().get(0))) {
               this.exception = new IllegalArgumentException("Reserved pipe operator");
            } else {
               List<String> fixes = new ArrayList();
               fixes.add((String)opt.args().get(1));
               fixes.add((String)opt.args().get(2));
               this.pipes.put((String)opt.args().get(0), fixes);
            }
         } else {
            options.put("maxDepth", 0);
            this.printer.println(options, this.pipes);
         }
      } catch (Exception e) {
         this.exception = e;
      }

      return null;
   }

   private Object doc(CommandInput input) {
      String[] usage = new String[]{"doc -  open document on browser", "Usage: doc [OBJECT]", "  -? --help                       Displays command help"};

      try {
         this.parseOptions(usage, input.xargs());
         if (input.xargs().length == 0) {
            return null;
         }

         if (!Desktop.isDesktopSupported()) {
            throw new IllegalStateException("Desktop is not supported!");
         }

         Map<String, Object> docs;
         try {
            docs = (Map)this.consoleOption("docs", (Object)null);
         } catch (Exception e) {
            Exception exception = new IllegalStateException("Bad documents configuration!");
            exception.addSuppressed(e);
            throw exception;
         }

         if (docs == null) {
            throw new IllegalStateException("No documents configuration!");
         }

         boolean done = false;
         Object arg = input.xargs()[0];
         if (arg instanceof String) {
            String address = (String)docs.get(input.args()[0]);
            if (address != null) {
               done = true;
               if (!this.urlExists(address)) {
                  throw new IllegalArgumentException("Document not found: " + address);
               }

               Desktop.getDesktop().browse(new URI(address));
            }
         }

         if (!done) {
            String name;
            if (arg instanceof String && ((String)arg).matches("([a-z]+\\.)+[A-Z][a-zA-Z]+")) {
               name = (String)arg;
            } else {
               name = arg.getClass().getCanonicalName();
            }

            name = name.replaceAll("\\.", "/") + ".html";
            Object doc = null;

            for(Map.Entry entry : docs.entrySet()) {
               if (name.matches((String)entry.getKey())) {
                  doc = entry.getValue();
                  break;
               }
            }

            if (doc == null) {
               throw new IllegalArgumentException("No document configuration for " + name);
            }

            String url = name;
            if (doc instanceof Collection) {
               for(Object o : (Collection)doc) {
                  url = o + name;
                  if (this.urlExists(url)) {
                     Desktop.getDesktop().browse(new URI(url));
                     done = true;
                  }
               }
            } else {
               url = doc + name;
               if (this.urlExists(url)) {
                  Desktop.getDesktop().browse(new URI(url));
                  done = true;
               }
            }

            if (!done) {
               throw new IllegalArgumentException("Document not found: " + url);
            }
         }
      } catch (Exception e) {
         this.exception = e;
      }

      return null;
   }

   private boolean urlExists(String weburl) {
      try {
         URL url = URI.create(weburl).toURL();
         HttpURLConnection huc = (HttpURLConnection)url.openConnection();
         huc.setRequestMethod("HEAD");
         return huc.getResponseCode() == 200;
      } catch (Exception var4) {
         return false;
      }
   }

   private List slurpCompleter(String command) {
      List<Completer> completers = new ArrayList();
      List<Completers.OptDesc> optDescs = this.commandOptions("slurp");

      for(Completers.OptDesc o : optDescs) {
         if (o.shortOption() != null && o.shortOption().equals("-f")) {
            List<String> formats = new ArrayList(this.engine.getDeserializationFormats());
            formats.add("TEXT");
            o.setValueCompleter(new StringsCompleter(formats));
            break;
         }
      }

      AggregateCompleter argCompleter = new AggregateCompleter(new Completer[]{new Completers.FilesCompleter(this.workDir), new VariableReferenceCompleter(this.engine)});
      completers.add(new ArgumentCompleter(new Completer[]{NullCompleter.INSTANCE, new Completers.OptionCompleter(Arrays.asList(argCompleter, NullCompleter.INSTANCE), optDescs, 1)}));
      return completers;
   }

   private List variableCompleter(String command) {
      List<Completer> completers = new ArrayList();
      completers.add(new StringsCompleter(() -> this.engine.find().keySet()));
      return completers;
   }

   private List prntCompleter(String command) {
      List<Completer> completers = new ArrayList();
      completers.add(new ArgumentCompleter(new Completer[]{NullCompleter.INSTANCE, new Completers.OptionCompleter(Arrays.asList(new VariableReferenceCompleter(this.engine), NullCompleter.INSTANCE), this::commandOptions, 1)}));
      return completers;
   }

   private List aliasCompleter(String command) {
      List<Completer> completers = new ArrayList();
      List<Completer> params = new ArrayList();
      Map var10003 = this.aliases;
      Objects.requireNonNull(var10003);
      params.add(new StringsCompleter(var10003::keySet));
      params.add(new AliasValueCompleter(this.aliases));
      completers.add(new ArgumentCompleter(new Completer[]{NullCompleter.INSTANCE, new Completers.OptionCompleter(params, this::commandOptions, 1)}));
      return completers;
   }

   private List unaliasCompleter(String command) {
      List<Completer> completers = new ArrayList();
      Completer[] var10003 = new Completer[]{NullCompleter.INSTANCE, null};
      Map var10010 = this.aliases;
      Objects.requireNonNull(var10010);
      var10003[1] = new Completers.OptionCompleter(new StringsCompleter(var10010::keySet), this::commandOptions, 1);
      completers.add(new ArgumentCompleter(var10003));
      return completers;
   }

   private List docs() {
      List<String> out = new ArrayList();
      Map<String, String> docs = (Map)this.consoleOption("docs", (Object)null);
      if (docs == null) {
         return out;
      } else {
         for(String v : this.engine.find().keySet()) {
            out.add("$" + v);
         }

         if (!docs.isEmpty()) {
            for(String d : docs.keySet()) {
               if (d.matches("\\w+")) {
                  out.add(d);
               }
            }
         }

         return out;
      }
   }

   private List docCompleter(String command) {
      List<Completer> completers = new ArrayList();
      completers.add(new ArgumentCompleter(new Completer[]{NullCompleter.INSTANCE, new Completers.OptionCompleter(Arrays.asList(new StringsCompleter(this::docs), NullCompleter.INSTANCE), this::commandOptions, 1)}));
      return completers;
   }

   public static enum Command {
      SHOW,
      DEL,
      PRNT,
      ALIAS,
      PIPE,
      UNALIAS,
      DOC,
      SLURP;

      // $FF: synthetic method
      private static Command[] $values() {
         return new Command[]{SHOW, DEL, PRNT, ALIAS, PIPE, UNALIAS, DOC, SLURP};
      }
   }

   private class ScriptFile {
      private File script;
      private String extension = "";
      private String cmdLine;
      private String[] args;
      private boolean verbose;
      private Object result;

      public ScriptFile(String command, String cmdLine, String[] args) {
         if (ConsoleEngineImpl.this.parser().validCommandName(command)) {
            try {
               this.script = new File(command);
               this.cmdLine = cmdLine;
               if (this.script.exists()) {
                  this.scriptExtension(command);
               } else if (ConsoleEngineImpl.this.engine.hasVariable("PATH")) {
                  boolean found = false;

                  for(String p : (List)ConsoleEngineImpl.this.engine.get("PATH")) {
                     for(String e : ConsoleEngineImpl.this.scriptExtensions()) {
                        String file = command + "." + e;
                        Path path = Paths.get(p, file);
                        if (path.toFile().exists()) {
                           this.script = path.toFile();
                           this.scriptExtension(command);
                           found = true;
                           break;
                        }
                     }

                     if (found) {
                        break;
                     }
                  }
               }

               this.doArgs(args);
            } catch (Exception var12) {
            }

         }
      }

      public ScriptFile(File script, String cmdLine, String[] args) {
         if (!script.exists()) {
            throw new IllegalArgumentException("Script file not found!");
         } else {
            this.script = script;
            this.cmdLine = cmdLine;
            this.scriptExtension(script.getName());
            this.doArgs(args);
         }
      }

      private void scriptExtension(String command) {
         String name = this.script.getName();
         this.extension = name.contains(".") ? name.substring(name.lastIndexOf(".") + 1) : "";
         if (!this.isEngineScript() && !this.isConsoleScript()) {
            throw new IllegalArgumentException("Command not found: " + command);
         }
      }

      private void doArgs(String[] args) {
         List<String> _args = new ArrayList();
         if (this.isConsoleScript()) {
            _args.add(this.script.getAbsolutePath());
         }

         for(String a : args) {
            if (this.isConsoleScript()) {
               if (!a.equals("-v")) {
                  _args.add(a);
               } else {
                  this.verbose = true;
               }
            } else {
               _args.add(a);
            }
         }

         this.args = (String[])_args.toArray(new String[0]);
      }

      private boolean isEngineScript() {
         return ConsoleEngineImpl.this.engine.getExtensions().contains(this.extension);
      }

      private boolean isConsoleScript() {
         return ConsoleEngineImpl.this.scriptExtension.equals(this.extension);
      }

      private boolean isScript() {
         return ConsoleEngineImpl.this.engine.getExtensions().contains(this.extension) || ConsoleEngineImpl.this.scriptExtension.equals(this.extension);
      }

      public boolean execute() throws Exception {
         if (!this.isScript()) {
            return false;
         } else {
            this.result = null;
            if (!Arrays.asList(this.args).contains(ConsoleEngineImpl.OPTION_HELP[0]) && !Arrays.asList(this.args).contains(ConsoleEngineImpl.OPTION_HELP[1])) {
               this.internalExecute();
            } else {
               BufferedReader br = new BufferedReader(new FileReader(this.script));

               try {
                  int size = 0;
                  StringBuilder usage = new StringBuilder();
                  boolean helpEnd = false;

                  String l;
                  String line;
                  for(boolean headComment = false; (l = br.readLine()) != null; usage.append(line).append('\n')) {
                     ++size;
                     l = l.replaceAll("\\s+$", "");
                     line = l;
                     if (size > 30 || l.endsWith("END_HELP")) {
                        helpEnd = l.endsWith("END_HELP");
                        break;
                     }

                     if (headComment || size < 3) {
                        String ltr = l.trim();
                        if (!ltr.startsWith("*") && !ltr.startsWith("#")) {
                           if (ltr.startsWith("/*") || ltr.startsWith("//")) {
                              headComment = true;
                              line = ltr.length() > 2 ? ltr.substring(3) : "";
                           }
                        } else {
                           headComment = true;
                           line = ltr.length() > 1 ? ltr.substring(2) : "";
                        }
                     }
                  }

                  if (usage.length() > 0) {
                     usage.append("\n");
                     if (!helpEnd) {
                        usage.insert(0, "\n");
                     }

                     throw new Options.HelpException(usage.toString());
                  }

                  this.internalExecute();
               } catch (Throwable var10) {
                  try {
                     br.close();
                  } catch (Throwable var9) {
                     var10.addSuppressed(var9);
                  }

                  throw var10;
               }

               br.close();
            }

            return true;
         }
      }

      private String expandParameterName(String parameter) {
         if (parameter.startsWith("$")) {
            return ConsoleEngineImpl.this.expandName(parameter);
         } else {
            return ConsoleEngineImpl.this.isNumber(parameter) ? parameter : ConsoleEngineImpl.this.quote(parameter);
         }
      }

      private void internalExecute() throws Exception {
         if (this.isEngineScript()) {
            this.result = ConsoleEngineImpl.this.engine.execute(this.script, ConsoleEngineImpl.this.expandParameters(this.args));
         } else if (this.isConsoleScript()) {
            ConsoleEngineImpl.this.executing = true;
            boolean done = true;
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader(this.script));

            try {
               String l;
               while((l = br.readLine()) != null) {
                  if (!l.trim().isEmpty() && !l.trim().startsWith("#")) {
                     try {
                        line = line + l;
                        ConsoleEngineImpl.this.parser().parse(line, line.length() + 1, Parser.ParseContext.ACCEPT_LINE);
                        done = true;

                        for(int i = 1; i < this.args.length; ++i) {
                           line = line.replaceAll("\\s\\$" + i + "\\b", " " + this.expandParameterName(this.args[i]) + " ");
                           line = line.replaceAll("\\$\\{" + i + "(|:-.*)}", this.expandParameterName(this.args[i]));
                        }

                        line = line.replaceAll("\\$\\{@}", ConsoleEngineImpl.this.expandToList(this.args));
                        line = line.replaceAll("\\$@", ConsoleEngineImpl.this.expandToList(this.args));
                        line = line.replaceAll("\\s\\$\\d\\b", "");
                        line = line.replaceAll("\\$\\{\\d+}", "");
                        Matcher matcher = Pattern.compile("\\$\\{\\d+:-(.*?)}").matcher(line);
                        if (matcher.find()) {
                           line = matcher.replaceAll(this.expandParameterName(matcher.group(1)));
                        }

                        if (this.verbose) {
                           AttributedStringBuilder asb = new AttributedStringBuilder();
                           asb.styled((AttributedStyle)Styles.prntStyle().resolve(".vs"), (CharSequence)line);
                           asb.toAttributedString().println(ConsoleEngineImpl.this.terminal());
                           ConsoleEngineImpl.this.terminal().flush();
                        }

                        ConsoleEngineImpl.this.println(ConsoleEngineImpl.this.systemRegistry.execute(line));
                        line = "";
                     } catch (EOFError var8) {
                        done = false;
                        line = line + "\n";
                     } catch (SyntaxError e) {
                        throw e;
                     } catch (EndOfFileException var10) {
                        done = true;
                        this.result = ConsoleEngineImpl.this.engine.get("_return");
                        ConsoleEngineImpl.this.postProcess(this.cmdLine, this.result);
                        break;
                     } catch (Exception e) {
                        ConsoleEngineImpl.this.executing = false;
                        throw new IllegalArgumentException(line + "\n" + e.getMessage());
                     }
                  } else {
                     done = true;
                  }
               }

               if (!done) {
                  ConsoleEngineImpl.this.executing = false;
                  throw new IllegalArgumentException("Incompleted command: \n" + line);
               }

               ConsoleEngineImpl.this.executing = false;
            } catch (Throwable var12) {
               try {
                  br.close();
               } catch (Throwable var7) {
                  var12.addSuppressed(var7);
               }

               throw var12;
            }

            br.close();
         }

      }

      public Object getResult() {
         return this.result;
      }

      public String toString() {
         StringBuilder sb = new StringBuilder();
         sb.append("[");

         try {
            sb.append("script:").append(this.script.getCanonicalPath());
         } catch (Exception e) {
            sb.append(e.getMessage());
         }

         sb.append(", ");
         sb.append("extension:").append(this.extension);
         sb.append(", ");
         sb.append("cmdLine:").append(this.cmdLine);
         sb.append(", ");
         sb.append("args:").append(Arrays.asList(this.args));
         sb.append(", ");
         sb.append("verbose:").append(this.verbose);
         sb.append(", ");
         sb.append("result:").append(this.result);
         sb.append("]");
         return sb.toString();
      }
   }

   private static class VariableReferenceCompleter implements Completer {
      private final ScriptEngine engine;

      public VariableReferenceCompleter(ScriptEngine engine) {
         this.engine = engine;
      }

      public void complete(LineReader reader, ParsedLine commandLine, List candidates) {
         assert commandLine != null;

         assert candidates != null;

         String word = commandLine.word();

         try {
            if (!word.contains(".") && !word.contains("}")) {
               for(String v : this.engine.find().keySet()) {
                  String c = "${" + v + "}";
                  candidates.add(new Candidate(AttributedString.stripAnsi(c), c, (String)null, (String)null, (String)null, (String)null, false));
               }
            } else if (word.startsWith("${") && word.contains("}") && word.contains(".")) {
               String var = word.substring(2, word.indexOf(125));
               if (this.engine.hasVariable(var)) {
                  String curBuf = word.substring(0, word.lastIndexOf("."));
                  String objStatement = curBuf.replace("${", "").replace("}", "");
                  Object obj = curBuf.contains(".") ? this.engine.execute(objStatement) : this.engine.get(var);
                  Map<?, ?> map = obj instanceof Map ? (Map)obj : null;
                  Set<String> identifiers = new HashSet();
                  if (map != null && !map.isEmpty() && map.keySet().iterator().next() instanceof String) {
                     identifiers = map.keySet();
                  } else if (map == null && obj != null) {
                     identifiers = this.getClassMethodIdentifiers(obj.getClass());
                  }

                  for(String key : identifiers) {
                     candidates.add(new Candidate(AttributedString.stripAnsi(curBuf + "." + key), key, (String)null, (String)null, (String)null, (String)null, false));
                  }
               }
            }
         } catch (Exception var13) {
         }

      }

      private Set getClassMethodIdentifiers(Class clazz) {
         Set<String> out = new HashSet();

         do {
            for(Method m : clazz.getMethods()) {
               if (!m.isSynthetic() && m.getParameterCount() == 0) {
                  String name = m.getName();
                  if (name.matches("get[A-Z].*")) {
                     out.add(this.convertGetMethod2identifier(name));
                  }
               }
            }

            clazz = clazz.getSuperclass();
         } while(clazz != null);

         return out;
      }

      private String convertGetMethod2identifier(String name) {
         char[] c = name.substring(3).toCharArray();
         c[0] = Character.toLowerCase(c[0]);
         return new String(c);
      }
   }

   private static class AliasValueCompleter implements Completer {
      private final Map aliases;

      public AliasValueCompleter(Map aliases) {
         this.aliases = aliases;
      }

      public void complete(LineReader reader, ParsedLine commandLine, List candidates) {
         assert commandLine != null;

         assert candidates != null;

         List<String> words = commandLine.words();
         if (words.size() > 1) {
            String h = (String)words.get(words.size() - 2);
            if (h != null && h.length() > 0) {
               String v = (String)this.aliases.get(h);
               if (v != null) {
                  candidates.add(new Candidate(AttributedString.stripAnsi(v), v, (String)null, (String)null, (String)null, (String)null, true));
               }
            }
         }

      }
   }
}
