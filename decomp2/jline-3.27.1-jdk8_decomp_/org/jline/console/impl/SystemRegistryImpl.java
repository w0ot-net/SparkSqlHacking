package org.jline.console.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jline.builtins.Completers;
import org.jline.builtins.ConfigurationPath;
import org.jline.builtins.Options;
import org.jline.builtins.Styles;
import org.jline.console.ArgDesc;
import org.jline.console.CmdDesc;
import org.jline.console.CmdLine;
import org.jline.console.CommandInput;
import org.jline.console.CommandMethods;
import org.jline.console.CommandRegistry;
import org.jline.console.ConsoleEngine;
import org.jline.console.SystemRegistry;
import org.jline.keymap.KeyMap;
import org.jline.reader.Candidate;
import org.jline.reader.Completer;
import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.ParsedLine;
import org.jline.reader.Parser;
import org.jline.reader.impl.completer.AggregateCompleter;
import org.jline.reader.impl.completer.ArgumentCompleter;
import org.jline.reader.impl.completer.NullCompleter;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.reader.impl.completer.SystemCompleter;
import org.jline.terminal.Attributes;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;
import org.jline.utils.Log;
import org.jline.utils.OSUtils;
import org.jline.utils.StyleResolver;

public class SystemRegistryImpl implements SystemRegistry {
   private static final Class[] BUILTIN_REGISTRIES = new Class[]{Builtins.class, ConsoleEngineImpl.class};
   private CommandRegistry[] commandRegistries;
   private Integer consoleId;
   protected final Parser parser;
   protected final ConfigurationPath configPath;
   protected final Supplier workDir;
   private final Map subcommands = new HashMap();
   private final Map pipeName = new HashMap();
   private final Map commandExecute = new HashMap();
   private final Map commandInfos = new HashMap();
   private Exception exception;
   private final CommandOutputStream outputStream;
   private ScriptStore scriptStore = new ScriptStore();
   private NamesAndValues names = new NamesAndValues();
   private final SystemCompleter customSystemCompleter = new SystemCompleter();
   private final AggregateCompleter customAggregateCompleter = new AggregateCompleter(new ArrayList());
   private boolean commandGroups = true;
   private Function scriptDescription;

   public SystemRegistryImpl(Parser parser, Terminal terminal, Supplier workDir, ConfigurationPath configPath) {
      this.parser = parser;
      this.workDir = workDir;
      this.configPath = configPath;
      this.outputStream = new CommandOutputStream(terminal);
      this.pipeName.put(SystemRegistryImpl.Pipe.FLIP, "|;");
      this.pipeName.put(SystemRegistryImpl.Pipe.NAMED, "|");
      this.pipeName.put(SystemRegistryImpl.Pipe.AND, "&&");
      this.pipeName.put(SystemRegistryImpl.Pipe.OR, "||");
      this.commandExecute.put("exit", new CommandMethods(this::exit, this::exitCompleter));
      this.commandExecute.put("help", new CommandMethods(this::help, this::helpCompleter));
   }

   public void rename(Pipe pipe, String name) {
      if (!name.matches("/w+") && !this.pipeName.containsValue(name)) {
         this.pipeName.put(pipe, name);
      } else {
         throw new IllegalArgumentException();
      }
   }

   public Collection getPipeNames() {
      return this.pipeName.values();
   }

   public void setCommandRegistries(CommandRegistry... commandRegistries) {
      this.commandRegistries = commandRegistries;

      for(int i = 0; i < commandRegistries.length; ++i) {
         if (commandRegistries[i] instanceof ConsoleEngine) {
            if (this.consoleId != null) {
               throw new IllegalArgumentException();
            }

            this.consoleId = i;
            ((ConsoleEngine)commandRegistries[i]).setSystemRegistry(this);
            this.scriptStore = new ScriptStore((ConsoleEngine)commandRegistries[i]);
            this.names = new NamesAndValues(this.configPath);
         } else if (commandRegistries[i] instanceof SystemRegistry) {
            throw new IllegalArgumentException();
         }
      }

      SystemRegistry.add(this);
   }

   public void initialize(File script) {
      if (this.consoleId != null) {
         try {
            this.consoleEngine().execute(script);
         } catch (Exception e) {
            this.trace((Throwable)e);
         }
      }

   }

   public Set commandNames() {
      Set<String> out = new HashSet();

      for(CommandRegistry r : this.commandRegistries) {
         out.addAll(r.commandNames());
      }

      out.addAll(this.localCommandNames());
      return out;
   }

   private Set localCommandNames() {
      return this.commandExecute.keySet();
   }

   public Map commandAliases() {
      Map<String, String> out = new HashMap();

      for(CommandRegistry r : this.commandRegistries) {
         out.putAll(r.commandAliases());
      }

      return out;
   }

   public Object consoleOption(String name) {
      return this.consoleOption(name, (Object)null);
   }

   public Object consoleOption(String name, Object defVal) {
      T out = defVal;
      if (this.consoleId != null) {
         out = (T)this.consoleEngine().consoleOption(name, defVal);
      }

      return out;
   }

   public void setConsoleOption(String name, Object value) {
      if (this.consoleId != null) {
         this.consoleEngine().setConsoleOption(name, value);
      }

   }

   public void register(String command, CommandRegistry subcommandRegistry) {
      this.subcommands.put(command, subcommandRegistry);
      this.commandExecute.put(command, new CommandMethods(this::subcommand, this::emptyCompleter));
   }

   private List localCommandInfo(String command) {
      try {
         CommandRegistry subCommand = (CommandRegistry)this.subcommands.get(command);
         if (subCommand != null) {
            this.registryHelp(subCommand);
         } else {
            this.localExecute(command, new String[]{"--help"});
         }
      } catch (Options.HelpException e) {
         this.exception = null;
         return JlineCommandRegistry.compileCommandInfo(e.getMessage());
      } catch (Exception e) {
         this.trace((Throwable)e);
      }

      return new ArrayList();
   }

   public List commandInfo(String command) {
      int id = this.registryId(command);
      List<String> out = new ArrayList();
      if (id > -1) {
         if (!this.commandInfos.containsKey(command)) {
            this.commandInfos.put(command, this.commandRegistries[id].commandInfo(command));
         }

         out = (List)this.commandInfos.get(command);
      } else if (this.scriptStore.hasScript(command) && this.consoleEngine() != null) {
         out = this.consoleEngine().commandInfo(command);
      } else if (this.isLocalCommand(command)) {
         out = this.localCommandInfo(command);
      }

      return out;
   }

   public boolean hasCommand(String command) {
      return this.registryId(command) > -1 || this.isLocalCommand(command);
   }

   public void setGroupCommandsInHelp(boolean commandGroups) {
      this.commandGroups = commandGroups;
   }

   public SystemRegistryImpl groupCommandsInHelp(boolean commandGroups) {
      this.commandGroups = commandGroups;
      return this;
   }

   private boolean isLocalCommand(String command) {
      return this.commandExecute.containsKey(command);
   }

   public boolean isCommandOrScript(ParsedLine line) {
      return this.isCommandOrScript(this.parser.getCommand((String)line.words().get(0)));
   }

   public boolean isCommandOrScript(String command) {
      return this.hasCommand(command) ? true : this.scriptStore.hasScript(command);
   }

   public void addCompleter(Completer completer) {
      if (completer instanceof SystemCompleter) {
         SystemCompleter sc = (SystemCompleter)completer;
         if (sc.isCompiled()) {
            this.customAggregateCompleter.getCompleters().add(sc);
         } else {
            this.customSystemCompleter.add(sc);
         }
      } else {
         this.customAggregateCompleter.getCompleters().add(completer);
      }

   }

   public SystemCompleter compileCompleters() {
      throw new IllegalStateException("Use method completer() to retrieve Completer!");
   }

   private SystemCompleter _compileCompleters() {
      SystemCompleter out = CommandRegistry.aggregateCompleters(this.commandRegistries);
      SystemCompleter local = new SystemCompleter();

      for(String command : this.commandExecute.keySet()) {
         CommandRegistry subCommand = (CommandRegistry)this.subcommands.get(command);
         if (subCommand != null) {
            for(Map.Entry entry : subCommand.compileCompleters().getCompleters().entrySet()) {
               for(Completer cc : (List)entry.getValue()) {
                  if (!(cc instanceof ArgumentCompleter)) {
                     throw new IllegalArgumentException();
                  }

                  List<Completer> cmps = ((ArgumentCompleter)cc).getCompleters();
                  cmps.add(0, NullCompleter.INSTANCE);
                  cmps.set(1, new StringsCompleter(new String[]{(String)entry.getKey()}));
                  Completer last = (Completer)cmps.get(cmps.size() - 1);
                  if (last instanceof Completers.OptionCompleter) {
                     ((Completers.OptionCompleter)last).setStartPos(cmps.size() - 1);
                     cmps.set(cmps.size() - 1, last);
                  }

                  local.add((String)command, (Completer)(new ArgumentCompleter(cmps)));
               }
            }
         } else {
            local.add(command, (List)((CommandMethods)this.commandExecute.get(command)).compileCompleter().apply(command));
         }
      }

      local.add(this.customSystemCompleter);
      out.add(local);
      out.compile();
      return out;
   }

   public Completer completer() {
      List<Completer> completers = new ArrayList();
      completers.add(this._compileCompleters());
      completers.add(this.customAggregateCompleter);
      if (this.consoleId != null) {
         completers.addAll(this.consoleEngine().scriptCompleters());
         completers.add((new PipelineCompleter(this.workDir, this.pipeName, this.names)).doCompleter());
      }

      return new AggregateCompleter(completers);
   }

   private CmdDesc localCommandDescription(String command) {
      if (!this.isLocalCommand(command)) {
         throw new IllegalArgumentException();
      } else {
         try {
            this.localExecute(command, new String[]{"--help"});
         } catch (Options.HelpException e) {
            this.exception = null;
            return JlineCommandRegistry.compileCommandDescription(e.getMessage());
         } catch (Exception e) {
            this.trace((Throwable)e);
         }

         return null;
      }
   }

   public CmdDesc commandDescription(List args) {
      CmdDesc out = new CmdDesc(false);
      String command = (String)args.get(0);
      int id = this.registryId(command);
      if (id > -1) {
         out = this.commandRegistries[id].commandDescription(args);
      } else if (this.scriptStore.hasScript(command) && this.consoleEngine() != null) {
         out = this.consoleEngine().commandDescription(args);
      } else if (this.isLocalCommand(command)) {
         out = this.localCommandDescription(command);
      }

      return out;
   }

   private CmdDesc commandDescription(CommandRegistry subreg) {
      List<AttributedString> main = new ArrayList();
      Map<String, List<AttributedString>> options = new HashMap();
      StyleResolver helpStyle = Styles.helpStyle();

      for(String sc : new TreeSet(subreg.commandNames())) {
         Iterator var7 = subreg.commandInfo(sc).iterator();
         if (var7.hasNext()) {
            String info = (String)var7.next();
            main.add(Options.HelpException.highlightSyntax(sc + " -  " + info, helpStyle, true));
         }
      }

      return new CmdDesc(main, ArgDesc.doArgNames(Collections.singletonList("")), options);
   }

   public void setScriptDescription(Function scriptDescription) {
      this.scriptDescription = scriptDescription;
   }

   public CmdDesc commandDescription(CmdLine line) {
      CmdDesc out = null;
      String cmd = this.parser.getCommand((String)line.getArgs().get(0));
      switch (line.getDescriptionType()) {
         case COMMAND:
            if (this.isCommandOrScript(cmd) && !this.names.hasPipes(line.getArgs())) {
               List<String> args = line.getArgs();
               CommandRegistry subCommand = (CommandRegistry)this.subcommands.get(cmd);
               if (subCommand != null) {
                  String c = args.size() > 1 ? (String)args.get(1) : null;
                  if (c != null && !subCommand.hasCommand(c)) {
                     out = this.commandDescription(subCommand);
                  } else if (c != null && c.equals("help")) {
                     out = null;
                  } else if (c != null) {
                     out = subCommand.commandDescription(Collections.singletonList(c));
                  } else {
                     out = this.commandDescription(subCommand);
                  }

                  if (out != null) {
                     out.setSubcommand(true);
                  }
               } else {
                  args.set(0, cmd);
                  out = this.commandDescription(args);
               }
            }
            break;
         case METHOD:
         case SYNTAX:
            if (!this.isCommandOrScript(cmd) && this.scriptDescription != null) {
               out = (CmdDesc)this.scriptDescription.apply(line);
            }
      }

      return out;
   }

   public Object invoke(String command, Object... args) throws Exception {
      Object out = null;
      command = ConsoleEngine.plainCommand(command);
      args = args == null ? new Object[]{null} : args;
      int id = this.registryId(command);
      if (id > -1) {
         out = this.commandRegistries[id].invoke(this.commandSession(), command, args);
      } else if (this.isLocalCommand(command)) {
         out = this.localExecute(command, args);
      } else if (this.consoleId != null) {
         out = this.consoleEngine().invoke(this.commandSession(), command, args);
      }

      return out;
   }

   private Object localExecute(String command, Object[] args) throws Exception {
      if (!this.isLocalCommand(command)) {
         throw new IllegalArgumentException();
      } else {
         Object out = ((CommandMethods)this.commandExecute.get(command)).execute().apply(new CommandInput(command, args, this.commandSession()));
         if (this.exception != null) {
            throw this.exception;
         } else {
            return out;
         }
      }
   }

   public Terminal terminal() {
      return this.commandSession().terminal();
   }

   private CommandRegistry.CommandSession commandSession() {
      return this.outputStream.getCommandSession();
   }

   public boolean isCommandAlias(String command) {
      if (this.consoleEngine() == null) {
         return false;
      } else {
         ConsoleEngine consoleEngine = this.consoleEngine();
         if (this.parser.validCommandName(command) && consoleEngine.hasAlias(command)) {
            String value = consoleEngine.getAlias(command).split("\\s+")[0];
            return !this.names.isPipe(value);
         } else {
            return false;
         }
      }
   }

   private String replaceCommandAlias(String variable, String command, String rawLine) {
      ConsoleEngine consoleEngine = this.consoleEngine();

      assert consoleEngine != null;

      return variable == null ? rawLine.replaceFirst(command + "(\\b|$)", consoleEngine.getAlias(command)) : rawLine.replaceFirst("=" + command + "(\\b|$)", "=" + consoleEngine.getAlias(command));
   }

   private String replacePipeAlias(ArgsParser ap, String pipeAlias, List args, Map customPipes) {
      ConsoleEngine consoleEngine = this.consoleEngine();

      assert consoleEngine != null;

      String alias = pipeAlias;

      for(int j = 0; j < args.size(); ++j) {
         alias = alias.replaceAll("\\s\\$" + j + "\\b", " " + (String)args.get(j));
         alias = alias.replaceAll("\\$\\{" + j + "(|:-.*)}", (String)args.get(j));
      }

      alias = alias.replaceAll("\\$\\{@}", consoleEngine.expandToList(args));
      alias = alias.replaceAll("\\$@", consoleEngine.expandToList(args));
      alias = alias.replaceAll("\\s+\\$\\d\\b", "");
      alias = alias.replaceAll("\\s+\\$\\{\\d+}", "");
      alias = alias.replaceAll("\\$\\{\\d+}", "");
      Matcher matcher = Pattern.compile("\\$\\{\\d+:-(.*?)}").matcher(alias);
      if (matcher.find()) {
         alias = matcher.replaceAll("$1");
      }

      ap.parse(alias);
      List<String> ws = ap.args();
      StringBuilder sb = new StringBuilder();

      for(int i = 0; i < ws.size(); ++i) {
         if (!((String)ws.get(i)).equals(this.pipeName.get(SystemRegistryImpl.Pipe.NAMED))) {
            sb.append((String)ws.get(i)).append(' ');
         } else if (i + 1 < ws.size() && consoleEngine.hasAlias((String)ws.get(i + 1))) {
            args.clear();
            ++i;
            String innerPipe = consoleEngine.getAlias((String)ws.get(i));

            while(i < ws.size() - 1 && !this.names.isPipe((String)ws.get(i + 1), customPipes.keySet())) {
               ++i;
               args.add((String)ws.get(i));
            }

            sb.append(this.replacePipeAlias(ap, innerPipe, args, customPipes));
         } else {
            sb.append((String)ws.get(i)).append(' ');
         }
      }

      return sb.toString();
   }

   private void replacePipeAliases(ConsoleEngine consoleEngine, Map customPipes, ArgsParser ap) {
      List<String> words = ap.args();
      if (consoleEngine != null && words.contains(this.pipeName.get(SystemRegistryImpl.Pipe.NAMED))) {
         StringBuilder sb = new StringBuilder();
         boolean trace = false;

         for(int i = 0; i < words.size(); ++i) {
            if (!((String)words.get(i)).equals(this.pipeName.get(SystemRegistryImpl.Pipe.NAMED))) {
               sb.append((String)words.get(i)).append(' ');
            } else if (i + 1 < words.size() && consoleEngine.hasAlias((String)words.get(i + 1))) {
               trace = true;
               List<String> args = new ArrayList();
               ++i;
               String pipeAlias = consoleEngine.getAlias((String)words.get(i));

               while(i < words.size() - 1 && !this.names.isPipe((String)words.get(i + 1), customPipes.keySet())) {
                  ++i;
                  args.add((String)words.get(i));
               }

               sb.append(this.replacePipeAlias(ap, pipeAlias, args, customPipes));
            } else {
               sb.append((String)words.get(i)).append(' ');
            }
         }

         ap.parse(sb.toString());
         if (trace) {
            consoleEngine.trace(ap.line());
         }
      }

   }

   private List compileCommandLine(String commandLine) {
      List<CommandData> out = new ArrayList();
      ArgsParser ap = new ArgsParser(this.parser);
      ap.parse(commandLine);
      ConsoleEngine consoleEngine = this.consoleEngine();
      Map<String, List<String>> customPipes = (Map<String, List<String>>)(consoleEngine != null ? consoleEngine.getPipes() : new HashMap());
      this.replacePipeAliases(consoleEngine, customPipes, ap);
      List<String> words = ap.args();
      String nextRawLine = ap.line();
      int first = 0;
      List<String> pipes = new ArrayList();
      String pipeSource = null;
      String rawLine = null;
      String pipeResult = null;
      if (this.isCommandAlias(ap.command())) {
         ap.parse(this.replaceCommandAlias(ap.variable(), ap.command(), nextRawLine));
         this.replacePipeAliases(consoleEngine, customPipes, ap);
         nextRawLine = ap.line();
         words = ap.args();
      }

      if (!this.names.hasPipes(words)) {
         out.add(new CommandData(ap, false, nextRawLine, ap.variable(), (File)null, false, ""));
      } else {
         do {
            String rawCommand = this.parser.getCommand((String)words.get(first));
            String command = ConsoleEngine.plainCommand(rawCommand);
            String variable = this.parser.getVariable((String)words.get(first));
            if (this.isCommandAlias(command)) {
               ap.parse(this.replaceCommandAlias(variable, command, nextRawLine));
               this.replacePipeAliases(consoleEngine, customPipes, ap);
               rawCommand = ap.rawCommand();
               command = ap.command();
               words = ap.args();
               first = 0;
            }

            if (this.scriptStore.isConsoleScript(command) && !rawCommand.startsWith(":")) {
               throw new IllegalArgumentException("Commands must be used in pipes with colon prefix!");
            }

            int last = words.size();
            File file = null;
            boolean append = false;
            boolean pipeStart = false;
            boolean skipPipe = false;
            List<String> _words = new ArrayList();

            for(int i = first; i < last; ++i) {
               if (((String)words.get(i)).equals(">") || ((String)words.get(i)).equals(">>")) {
                  pipes.add((String)words.get(i));
                  append = ((String)words.get(i)).equals(">>");
                  if (i + 1 >= last) {
                     throw new IllegalArgumentException();
                  }

                  file = this.redirectFile((String)words.get(i + 1));
                  last = i + 1;
                  break;
               }

               if (this.consoleId == null) {
                  _words.add((String)words.get(i));
               } else {
                  if (((String)words.get(i)).equals(this.pipeName.get(SystemRegistryImpl.Pipe.FLIP))) {
                     if (variable != null || file != null || pipeResult != null || this.consoleId == null) {
                        throw new IllegalArgumentException();
                     }

                     pipes.add((String)words.get(i));
                     last = i;
                     variable = "_pipe" + (pipes.size() - 1);
                     break;
                  }

                  if (!((String)words.get(i)).equals(this.pipeName.get(SystemRegistryImpl.Pipe.NAMED)) && (!((String)words.get(i)).matches("^.*[^a-zA-Z0-9 ].*$") || !customPipes.containsKey(words.get(i)))) {
                     if (!((String)words.get(i)).equals(this.pipeName.get(SystemRegistryImpl.Pipe.OR)) && !((String)words.get(i)).equals(this.pipeName.get(SystemRegistryImpl.Pipe.AND))) {
                        _words.add((String)words.get(i));
                        continue;
                     }

                     if (variable == null && pipeSource == null) {
                        if (pipes.size() <= 0 || !((String)pipes.get(pipes.size() - 1)).equals(">") && !((String)pipes.get(pipes.size() - 1)).equals(">>")) {
                           pipes.add((String)words.get(i));
                           pipeSource = "_pipe" + (pipes.size() - 1);
                           pipeResult = variable;
                           variable = pipeSource;
                           pipeStart = true;
                        } else {
                           pipes.remove(pipes.size() - 1);
                           ((CommandData)out.get(out.size() - 1)).setPipe((String)words.get(i));
                           skipPipe = true;
                        }
                     } else {
                        pipes.add((String)words.get(i));
                     }

                     last = i;
                     break;
                  }

                  String pipe = (String)words.get(i);
                  if (pipe.equals(this.pipeName.get(SystemRegistryImpl.Pipe.NAMED))) {
                     if (i + 1 >= last) {
                        throw new IllegalArgumentException("Pipe is NULL!");
                     }

                     pipe = (String)words.get(i + 1);
                     if (!pipe.matches("\\w+") || !customPipes.containsKey(pipe)) {
                        throw new IllegalArgumentException("Unknown or illegal pipe name: " + pipe);
                     }
                  }

                  pipes.add(pipe);
                  last = i;
                  if (pipeSource == null) {
                     pipeSource = "_pipe" + (pipes.size() - 1);
                     pipeResult = variable;
                     variable = pipeSource;
                     pipeStart = true;
                  }
                  break;
               }
            }

            if (last == words.size()) {
               pipes.add("END_PIPE");
            } else if (skipPipe) {
               first = last + 1;
               continue;
            }

            String subLine = last >= words.size() && first <= 0 ? ap.line() : String.join(" ", _words);
            if (last + 1 < words.size()) {
               nextRawLine = String.join(" ", words.subList(last + 1, words.size()));
            }

            boolean done = true;
            boolean statement = false;
            List<String> arglist = new ArrayList();
            if (_words.size() > 0) {
               arglist.addAll(_words.subList(1, _words.size()));
            }

            if (rawLine != null || pipes.size() > 1 && customPipes.containsKey(pipes.get(pipes.size() - 2))) {
               done = false;
               if (rawLine == null) {
                  rawLine = pipeSource;
               }

               if (customPipes.containsKey(pipes.get(pipes.size() - 2))) {
                  List<String> fixes = (List)customPipes.get(pipes.get(pipes.size() - 2));
                  if (((String)pipes.get(pipes.size() - 2)).matches("\\w+")) {
                     int idx = subLine.indexOf(" ");
                     subLine = idx > 0 ? subLine.substring(idx + 1) : "";
                  }

                  rawLine = rawLine + (String)fixes.get(0) + (this.consoleId != null ? this.consoleEngine().expandCommandLine(subLine) : subLine) + (String)fixes.get(1);
                  statement = true;
               }

               if (((String)pipes.get(pipes.size() - 1)).equals(this.pipeName.get(SystemRegistryImpl.Pipe.FLIP)) || ((String)pipes.get(pipes.size() - 1)).equals(this.pipeName.get(SystemRegistryImpl.Pipe.AND)) || ((String)pipes.get(pipes.size() - 1)).equals(this.pipeName.get(SystemRegistryImpl.Pipe.OR))) {
                  done = true;
                  pipeSource = null;
                  if (variable != null) {
                     rawLine = variable + " = " + rawLine;
                  }
               }

               if (last + 1 >= words.size() || file != null) {
                  done = true;
                  pipeSource = null;
                  if (pipeResult != null) {
                     rawLine = pipeResult + " = " + rawLine;
                  }
               }
            } else if (!((String)pipes.get(pipes.size() - 1)).equals(this.pipeName.get(SystemRegistryImpl.Pipe.FLIP)) && !pipeStart) {
               rawLine = this.flipArgument(command, subLine, pipes, arglist);
            } else {
               if (pipeStart && pipeResult != null) {
                  subLine = subLine.substring(subLine.indexOf("=") + 1);
               }

               rawLine = this.flipArgument(command, subLine, pipes, arglist);
               rawLine = variable + "=" + rawLine;
            }

            if (done) {
               out.add(new CommandData(ap, statement, rawLine, variable, file, append, (String)pipes.get(pipes.size() - 1)));
               if (((String)pipes.get(pipes.size() - 1)).equals(this.pipeName.get(SystemRegistryImpl.Pipe.AND)) || ((String)pipes.get(pipes.size() - 1)).equals(this.pipeName.get(SystemRegistryImpl.Pipe.OR))) {
                  pipeSource = null;
                  pipeResult = null;
               }

               rawLine = null;
            }

            first = last + 1;
         } while(first < words.size());
      }

      return out;
   }

   private File redirectFile(String name) {
      File out;
      if (name.equals("null")) {
         out = OSUtils.IS_WINDOWS ? new File("NUL") : new File("/dev/null");
      } else {
         out = new File(name);
      }

      return out;
   }

   private String flipArgument(String command, String subLine, List pipes, List arglist) {
      String out;
      if (pipes.size() > 1 && ((String)pipes.get(pipes.size() - 2)).equals(this.pipeName.get(SystemRegistryImpl.Pipe.FLIP))) {
         String s = this.isCommandOrScript(command) ? "$" : "";
         out = subLine + " " + s + "_pipe" + (pipes.size() - 2);
         if (!command.isEmpty()) {
            arglist.add(s + "_pipe" + (pipes.size() - 2));
         }
      } else {
         out = subLine;
      }

      return out;
   }

   private Object execute(String command, String rawLine, String[] args) throws Exception {
      if (!this.parser.validCommandName(command)) {
         throw new UnknownCommandException("Invalid command: " + rawLine);
      } else {
         Object out;
         if (this.isLocalCommand(command)) {
            out = this.localExecute(command, (Object[])(this.consoleId != null ? this.consoleEngine().expandParameters(args) : args));
         } else {
            int id = this.registryId(command);
            if (id > -1) {
               Object[] _args = (Object[])(this.consoleId != null ? this.consoleEngine().expandParameters(args) : args);
               out = this.commandRegistries[id].invoke(this.outputStream.getCommandSession(), command, _args);
            } else {
               if (!this.scriptStore.hasScript(command) || this.consoleEngine() == null) {
                  throw new UnknownCommandException("Unknown command: " + command);
               }

               out = this.consoleEngine().execute(command, rawLine, args);
            }
         }

         return out;
      }
   }

   public Object execute(String line) throws Exception {
      if (!line.trim().isEmpty() && !line.trim().startsWith("#")) {
         long start = (new Date()).getTime();
         Object out = null;
         boolean statement = false;
         boolean postProcessed = false;
         int errorCount = 0;
         this.scriptStore.refresh();
         List<CommandData> cmds = this.compileCommandLine(line);
         ConsoleEngine consoleEngine = this.consoleEngine();

         for(CommandData cmd : cmds) {
            if (cmd.file() != null && this.scriptStore.isConsoleScript(cmd.command())) {
               throw new IllegalArgumentException("Console script output cannot be redirected!");
            }

            try {
               this.outputStream.close();
               if (consoleEngine != null && !consoleEngine.isExecuting()) {
                  this.trace(cmd);
               }

               this.exception = null;
               statement = false;
               postProcessed = false;
               if (cmd.variable() != null || cmd.file() != null) {
                  if (cmd.file() != null) {
                     this.outputStream.redirect(cmd.file(), cmd.append());
                  } else if (this.consoleId != null) {
                     this.outputStream.redirect();
                  }

                  this.outputStream.open((Boolean)this.consoleOption("redirectColor", false));
               }

               boolean consoleScript = false;

               try {
                  out = this.execute(cmd.command(), cmd.rawLine(), cmd.args());
               } catch (UnknownCommandException e) {
                  if (consoleEngine == null) {
                     throw e;
                  }

                  consoleScript = true;
               }

               if (consoleEngine != null) {
                  if (consoleScript) {
                     statement = cmd.command().isEmpty() || !this.scriptStore.hasScript(cmd.command());
                     if (statement && this.outputStream.isByteOutputStream()) {
                        this.outputStream.close();
                     }

                     out = consoleEngine.execute(cmd.command(), cmd.rawLine(), cmd.args());
                  }

                  if (cmd.pipe().equals(this.pipeName.get(SystemRegistryImpl.Pipe.OR)) || cmd.pipe().equals(this.pipeName.get(SystemRegistryImpl.Pipe.AND))) {
                     ConsoleEngine.ExecutionResult er = this.postProcess(cmd, statement, consoleEngine, out);
                     postProcessed = true;
                     consoleEngine.println(er.result());
                     out = null;
                     boolean success = er.status() == 0;
                     if (cmd.pipe().equals(this.pipeName.get(SystemRegistryImpl.Pipe.OR)) && success || cmd.pipe().equals(this.pipeName.get(SystemRegistryImpl.Pipe.AND)) && !success) {
                        break;
                     }
                  }
               }
            } catch (Options.HelpException e) {
               this.trace((Throwable)e);
            } catch (Exception e) {
               ++errorCount;
               if (!cmd.pipe().equals(this.pipeName.get(SystemRegistryImpl.Pipe.OR))) {
                  throw e;
               }

               this.trace((Throwable)e);
               postProcessed = true;
            } finally {
               if (!postProcessed && consoleEngine != null) {
                  out = this.postProcess(cmd, statement, consoleEngine, out).result();
               }

            }
         }

         if (errorCount == 0) {
            this.names.extractNames(line);
         }

         Log.debug("execute: ", (new Date()).getTime() - start, " msec");
         return out;
      } else {
         return null;
      }
   }

   private ConsoleEngine.ExecutionResult postProcess(CommandData cmd, boolean statement, ConsoleEngine consoleEngine, Object result) {
      ConsoleEngine.ExecutionResult out;
      if (cmd.file() != null) {
         int status = 1;
         if (cmd.file().exists()) {
            long delta = (new Date()).getTime() - cmd.file().lastModified();
            status = delta < 100L ? 0 : 1;
         }

         out = new ConsoleEngine.ExecutionResult(status, result);
      } else if (!statement) {
         this.outputStream.close();
         out = consoleEngine.postProcess(cmd.rawLine(), result, this.outputStream.getOutput());
      } else if (cmd.variable() != null) {
         if (consoleEngine.hasVariable(cmd.variable())) {
            out = consoleEngine.postProcess(consoleEngine.getVariable(cmd.variable()));
         } else {
            out = consoleEngine.postProcess(result);
         }

         out = new ConsoleEngine.ExecutionResult(out.status(), (Object)null);
      } else {
         out = consoleEngine.postProcess(result);
      }

      return out;
   }

   public void cleanUp() {
      this.outputStream.close();
      this.outputStream.resetOutput();
      if (this.consoleEngine() != null) {
         this.consoleEngine().purge();
      }

   }

   private void trace(CommandData commandData) {
      if (this.consoleEngine() != null) {
         this.consoleEngine().trace(commandData);
      } else {
         AttributedStringBuilder asb = new AttributedStringBuilder();
         asb.append(commandData.rawLine(), AttributedStyle.DEFAULT.foreground(3)).println(this.terminal());
      }

   }

   public void trace(Throwable exception) {
      this.outputStream.close();
      ConsoleEngine consoleEngine = this.consoleEngine();
      if (consoleEngine != null) {
         if (!(exception instanceof Options.HelpException)) {
            consoleEngine.putVariable("exception", exception);
         }

         consoleEngine.trace(exception);
      } else {
         this.trace(false, exception);
      }

   }

   public void trace(boolean stack, Throwable exception) {
      if (exception instanceof Options.HelpException) {
         Options.HelpException.highlight(exception.getMessage(), Styles.helpStyle()).print(this.terminal());
      } else if (exception instanceof UnknownCommandException) {
         AttributedStringBuilder asb = new AttributedStringBuilder();
         asb.append(exception.getMessage(), Styles.prntStyle().resolve(".em"));
         asb.toAttributedString().println(this.terminal());
      } else if (stack) {
         exception.printStackTrace();
      } else {
         String message = exception.getMessage();
         AttributedStringBuilder asb = new AttributedStringBuilder();
         asb.style(Styles.prntStyle().resolve(".em"));
         if (message != null) {
            asb.append((CharSequence)exception.getClass().getSimpleName()).append((CharSequence)": ").append((CharSequence)message);
         } else {
            asb.append((CharSequence)"Caught exception: ");
            asb.append((CharSequence)exception.getClass().getCanonicalName());
         }

         asb.toAttributedString().println(this.terminal());
         Log.debug("Stack: ", exception);
      }

   }

   public void close() {
      this.names.save();
   }

   public ConsoleEngine consoleEngine() {
      return this.consoleId != null ? (ConsoleEngine)this.commandRegistries[this.consoleId] : null;
   }

   private boolean isBuiltinRegistry(CommandRegistry registry) {
      for(Class c : BUILTIN_REGISTRIES) {
         if (c == registry.getClass()) {
            return true;
         }
      }

      return false;
   }

   private void printHeader(String header) {
      AttributedStringBuilder asb = (new AttributedStringBuilder()).tabs(2);
      asb.append((CharSequence)"\t");
      asb.append(header, Options.HelpException.defaultStyle().resolve(".ti"));
      asb.append((CharSequence)":");
      asb.toAttributedString().println(this.terminal());
   }

   private void printCommandInfo(String command, String info, int max) {
      AttributedStringBuilder asb = (new AttributedStringBuilder()).tabs(Arrays.asList(4, max + 4));
      asb.append((CharSequence)"\t");
      asb.append(command, Options.HelpException.defaultStyle().resolve(".co"));
      asb.append((CharSequence)"\t");
      asb.append((CharSequence)info);
      asb.setLength(this.terminal().getWidth());
      asb.toAttributedString().println(this.terminal());
   }

   private void printCommands(Collection commands, int max) {
      AttributedStringBuilder asb = (new AttributedStringBuilder()).tabs(Arrays.asList(4, max + 4));
      int col = 0;
      asb.append((CharSequence)"\t");
      col += 4;
      boolean done = false;

      for(String c : commands) {
         asb.append(c, Options.HelpException.defaultStyle().resolve(".co"));
         asb.append((CharSequence)"\t");
         col += max;
         if (col + max > this.terminal().getWidth()) {
            asb.toAttributedString().println(this.terminal());
            asb = (new AttributedStringBuilder()).tabs(Arrays.asList(4, max + 4));
            col = 0;
            asb.append((CharSequence)"\t");
            col += 4;
            done = true;
         } else {
            done = false;
         }
      }

      if (!done) {
         asb.toAttributedString().println(this.terminal());
      }

      this.terminal().flush();
   }

   private String doCommandInfo(List info) {
      return info != null && info.size() > 0 ? (String)info.get(0) : " ";
   }

   private boolean isInTopics(List args, String name) {
      return args.isEmpty() || args.contains(name);
   }

   private Options parseOptions(String[] usage, Object[] args) throws Options.HelpException {
      Options opt = Options.compile(usage).parse(args);
      if (opt.isSet("help")) {
         throw new Options.HelpException(opt.usage());
      } else {
         return opt;
      }
   }

   private Object help(CommandInput input) {
      String groupsOption = this.commandGroups ? "nogroups" : "groups";
      String groupsHelp = this.commandGroups ? "     --nogroups                   Commands are not grouped by registries" : "     --groups                     Commands are grouped by registries";
      String[] usage = new String[]{"help -  command help", "Usage: help [TOPIC...]", "  -? --help                       Displays command help", groupsHelp, "  -i --info                       List commands with a short command info"};

      try {
         Options opt = this.parseOptions(usage, input.args());
         boolean doTopic = false;
         boolean cg = this.commandGroups;
         boolean info = false;
         if (!opt.args().isEmpty() && opt.args().size() == 1) {
            try {
               String[] args = new String[]{"--help"};
               String command = (String)opt.args().get(0);
               this.execute(command, command + " " + args[0], args);
            } catch (UnknownCommandException var11) {
               doTopic = true;
            } catch (Exception e) {
               this.exception = e;
            }
         } else {
            doTopic = true;
            if (opt.isSet(groupsOption)) {
               cg = !cg;
            }

            if (opt.isSet("info")) {
               info = true;
            }
         }

         if (doTopic) {
            this.helpTopic(opt.args(), cg, info);
         }
      } catch (Exception e) {
         this.exception = e;
      }

      return null;
   }

   private void helpTopic(List topics, boolean commandGroups, boolean info) {
      Set<String> commands = this.commandNames();
      commands.addAll(this.scriptStore.getScripts());
      boolean withInfo = commands.size() < this.terminal().getHeight() || !topics.isEmpty() || info;
      int max = ((String)Collections.max(commands, Comparator.comparing(String::length))).length() + 1;
      TreeMap<String, String> builtinCommands = new TreeMap();
      TreeMap<String, String> systemCommands = new TreeMap();
      if (!commandGroups && topics.isEmpty()) {
         TreeSet<String> ordered = new TreeSet(commands);
         if (withInfo) {
            for(String c : ordered) {
               List<String> infos = this.commandInfo(c);
               String cmdInfo = infos.isEmpty() ? "" : (String)infos.get(0);
               this.printCommandInfo(c, cmdInfo, max);
            }
         } else {
            this.printCommands(ordered, max);
         }
      } else {
         for(CommandRegistry r : this.commandRegistries) {
            if (this.isBuiltinRegistry(r)) {
               for(String c : r.commandNames()) {
                  builtinCommands.put(c, this.doCommandInfo(this.commandInfo(c)));
               }
            }
         }

         for(String c : this.localCommandNames()) {
            systemCommands.put(c, this.doCommandInfo(this.commandInfo(c)));
            this.exception = null;
         }

         if (this.isInTopics(topics, "System")) {
            this.printHeader("System");
            if (withInfo) {
               for(Map.Entry entry : systemCommands.entrySet()) {
                  this.printCommandInfo((String)entry.getKey(), (String)entry.getValue(), max);
               }
            } else {
               this.printCommands(systemCommands.keySet(), max);
            }
         }

         if (this.isInTopics(topics, "Builtins") && !builtinCommands.isEmpty()) {
            this.printHeader("Builtins");
            if (withInfo) {
               for(Map.Entry entry : builtinCommands.entrySet()) {
                  this.printCommandInfo((String)entry.getKey(), (String)entry.getValue(), max);
               }
            } else {
               this.printCommands(builtinCommands.keySet(), max);
            }
         }

         for(CommandRegistry r : this.commandRegistries) {
            if (!this.isBuiltinRegistry(r) && this.isInTopics(topics, r.name()) && !r.commandNames().isEmpty()) {
               TreeSet<String> cmds = new TreeSet(r.commandNames());
               this.printHeader(r.name());
               if (withInfo) {
                  for(String c : cmds) {
                     this.printCommandInfo(c, this.doCommandInfo(this.commandInfo(c)), max);
                  }
               } else {
                  this.printCommands(cmds, max);
               }
            }
         }

         if (this.consoleId != null && this.isInTopics(topics, "Scripts") && !this.scriptStore.getScripts().isEmpty()) {
            this.printHeader("Scripts");
            if (withInfo) {
               for(String c : this.scriptStore.getScripts()) {
                  this.printCommandInfo(c, this.doCommandInfo(this.commandInfo(c)), max);
               }
            } else {
               this.printCommands(this.scriptStore.getScripts(), max);
            }
         }
      }

      this.terminal().flush();
   }

   private Object exit(CommandInput input) {
      String[] usage = new String[]{"exit -  exit from app/script", "Usage: exit [OBJECT]", "  -? --help                       Displays command help"};

      try {
         Options opt = this.parseOptions(usage, input.xargs());
         ConsoleEngine consoleEngine = this.consoleEngine();
         if (!opt.argObjects().isEmpty() && consoleEngine != null) {
            try {
               consoleEngine.putVariable("_return", opt.argObjects().size() == 1 ? opt.argObjects().get(0) : opt.argObjects());
            } catch (Exception e) {
               this.trace((Throwable)e);
            }
         }

         this.exception = new EndOfFileException();
      } catch (Exception e) {
         this.exception = e;
      }

      return null;
   }

   private void registryHelp(CommandRegistry registry) throws Exception {
      List<Integer> tabs = new ArrayList();
      tabs.add(0);
      tabs.add(9);
      int max = (Integer)registry.commandNames().stream().map(String::length).max(Integer::compareTo).get();
      tabs.add(10 + max);
      AttributedStringBuilder sb = (new AttributedStringBuilder()).tabs(tabs);
      sb.append((CharSequence)" -  ");
      sb.append((CharSequence)registry.name());
      sb.append((CharSequence)" registry");
      sb.append((CharSequence)"\n");
      boolean first = true;

      for(String c : new TreeSet(registry.commandNames())) {
         if (first) {
            sb.append((CharSequence)"Summary:");
            first = false;
         }

         sb.append((CharSequence)"\t");
         sb.append((CharSequence)c);
         sb.append((CharSequence)"\t");
         sb.append((CharSequence)registry.commandInfo(c).get(0));
         sb.append((CharSequence)"\n");
      }

      throw new Options.HelpException(sb.toString());
   }

   private Object subcommand(CommandInput input) {
      Object out = null;

      try {
         if (input.args().length > 0 && ((CommandRegistry)this.subcommands.get(input.command())).hasCommand(input.args()[0])) {
            out = ((CommandRegistry)this.subcommands.get(input.command())).invoke(input.session(), input.args()[0], input.xargs().length > 1 ? Arrays.copyOfRange(input.xargs(), 1, input.xargs().length) : new Object[0]);
         } else {
            this.registryHelp((CommandRegistry)this.subcommands.get(input.command()));
         }
      } catch (Exception e) {
         this.exception = e;
      }

      return out;
   }

   private List commandOptions(String command) {
      try {
         this.localExecute(command, new String[]{"--help"});
      } catch (Options.HelpException e) {
         this.exception = null;
         return JlineCommandRegistry.compileCommandOptions(e.getMessage());
      } catch (Exception e) {
         this.trace((Throwable)e);
      }

      return null;
   }

   private List registryNames() {
      List<String> out = new ArrayList();
      out.add("System");
      out.add("Builtins");
      if (this.consoleId != null) {
         out.add("Scripts");
      }

      for(CommandRegistry r : this.commandRegistries) {
         if (!this.isBuiltinRegistry(r)) {
            out.add(r.name());
         }
      }

      out.addAll(this.commandNames());
      out.addAll(this.scriptStore.getScripts());
      return out;
   }

   private List emptyCompleter(String command) {
      return new ArrayList();
   }

   private List helpCompleter(String command) {
      List<Completer> completers = new ArrayList();
      List<Completer> params = new ArrayList();
      params.add(new StringsCompleter(this::registryNames));
      params.add(NullCompleter.INSTANCE);
      completers.add(new ArgumentCompleter(new Completer[]{NullCompleter.INSTANCE, new Completers.OptionCompleter(params, this::commandOptions, 1)}));
      return completers;
   }

   private List exitCompleter(String command) {
      List<Completer> completers = new ArrayList();
      completers.add(new ArgumentCompleter(new Completer[]{NullCompleter.INSTANCE, new Completers.OptionCompleter(NullCompleter.INSTANCE, this::commandOptions, 1)}));
      return completers;
   }

   private int registryId(String command) {
      for(int i = 0; i < this.commandRegistries.length; ++i) {
         if (this.commandRegistries[i].hasCommand(command)) {
            return i;
         }
      }

      return -1;
   }

   public static enum Pipe {
      FLIP,
      NAMED,
      AND,
      OR;

      // $FF: synthetic method
      private static Pipe[] $values() {
         return new Pipe[]{FLIP, NAMED, AND, OR};
      }
   }

   private static class CommandOutputStream {
      private final PrintStream origOut;
      private final PrintStream origErr;
      private final Terminal origTerminal;
      private OutputStream outputStream;
      private Terminal terminal;
      private String output;
      private CommandRegistry.CommandSession commandSession;
      private boolean redirecting = false;

      public CommandOutputStream(Terminal terminal) {
         this.origOut = System.out;
         this.origErr = System.err;
         this.origTerminal = terminal;
         this.terminal = terminal;
         PrintStream ps = new PrintStream(terminal.output());
         this.commandSession = new CommandRegistry.CommandSession(terminal, terminal.input(), ps, ps);
      }

      public void redirect() {
         this.outputStream = new ByteArrayOutputStream();
      }

      public void redirect(File file, boolean append) throws IOException {
         if (!file.exists()) {
            try {
               file.createNewFile();
            } catch (IOException var4) {
               (new File(file.getParent())).mkdirs();
               file.createNewFile();
            }
         }

         this.outputStream = new FileOutputStream(file, append);
      }

      public void open(boolean redirectColor) throws IOException {
         if (!this.redirecting && this.outputStream != null) {
            this.output = null;
            PrintStream out = new PrintStream(this.outputStream);
            System.setOut(out);
            System.setErr(out);
            String input = KeyMap.ctrl('X') + "q";
            InputStream in = new ByteArrayInputStream(input.getBytes());
            Attributes attrs = new Attributes();
            if (OSUtils.IS_WINDOWS) {
               attrs.setInputFlag(Attributes.InputFlag.IGNCR, true);
            }

            try {
               this.terminal = TerminalBuilder.builder().streams(in, this.outputStream).attributes(attrs).type(redirectColor ? "dumb-color" : "dumb").build();
               this.commandSession = new CommandRegistry.CommandSession(this.terminal, this.terminal.input(), out, out);
               this.redirecting = true;
            } catch (IOException e) {
               this.reset();
               throw e;
            }
         }
      }

      public void close() {
         if (this.redirecting) {
            try {
               this.terminal.flush();
               if (this.outputStream instanceof ByteArrayOutputStream) {
                  this.output = this.outputStream.toString();
               }

               this.terminal.close();
            } catch (Exception var2) {
            }

            this.reset();
         }
      }

      public void resetOutput() {
         this.output = null;
      }

      private void reset() {
         this.outputStream = null;
         System.setOut(this.origOut);
         System.setErr(this.origErr);
         this.terminal = null;
         this.terminal = this.origTerminal;
         PrintStream ps = new PrintStream(this.terminal.output());
         this.commandSession = new CommandRegistry.CommandSession(this.terminal, this.terminal.input(), ps, ps);
         this.redirecting = false;
      }

      public CommandRegistry.CommandSession getCommandSession() {
         return this.commandSession;
      }

      public String getOutput() {
         return this.output;
      }

      public boolean isRedirecting() {
         return this.redirecting;
      }

      public boolean isByteOutputStream() {
         return this.outputStream instanceof ByteArrayOutputStream;
      }
   }

   private static class ArgsParser {
      private int round = 0;
      private int curly = 0;
      private int square = 0;
      private boolean quoted;
      private boolean doubleQuoted;
      private String line;
      private String command = "";
      private String variable = "";
      private List args;
      private final Parser parser;

      public ArgsParser(Parser parser) {
         this.parser = parser;
      }

      private void reset() {
         this.round = 0;
         this.curly = 0;
         this.square = 0;
         this.quoted = false;
         this.doubleQuoted = false;
      }

      private void next(String arg) {
         char prevChar = ' ';

         for(int i = 0; i < arg.length(); ++i) {
            char c = arg.charAt(i);
            if (!this.parser.isEscapeChar(prevChar)) {
               if (!this.quoted && !this.doubleQuoted) {
                  if (c == '(') {
                     ++this.round;
                  } else if (c == ')') {
                     --this.round;
                  } else if (c == '{') {
                     ++this.curly;
                  } else if (c == '}') {
                     --this.curly;
                  } else if (c == '[') {
                     ++this.square;
                  } else if (c == ']') {
                     --this.square;
                  } else if (c == '"') {
                     this.doubleQuoted = true;
                  } else if (c == '\'') {
                     this.quoted = true;
                  }
               } else if (this.quoted && c == '\'') {
                  this.quoted = false;
               } else if (this.doubleQuoted && c == '"') {
                  this.doubleQuoted = false;
               }
            }

            prevChar = c;
         }

      }

      private boolean isEnclosed() {
         return this.round == 0 && this.curly == 0 && this.square == 0 && !this.quoted && !this.doubleQuoted;
      }

      public boolean isEnclosed(String arg) {
         this.reset();
         this.next(arg);
         return this.isEnclosed();
      }

      private void enclosedArgs(List words) {
         this.args = new ArrayList();
         this.reset();
         boolean first = true;
         StringBuilder sb = new StringBuilder();

         for(String a : words) {
            this.next(a);
            if (!first) {
               sb.append(" ");
            }

            if (this.isEnclosed()) {
               sb.append(a);
               this.args.add(sb.toString());
               sb = new StringBuilder();
               first = true;
            } else {
               sb.append(a);
               first = false;
            }
         }

         if (!first) {
            this.args.add(sb.toString());
         }

      }

      public void parse(String line) {
         this.line = line;
         ParsedLine pl = this.parser.parse(line, 0, Parser.ParseContext.SPLIT_LINE);
         this.enclosedArgs(pl.words());
         if (!this.args.isEmpty()) {
            this.command = this.parser.getCommand((String)this.args.get(0));
            if (!this.parser.validCommandName(this.command)) {
               this.command = "";
            }

            this.variable = this.parser.getVariable((String)this.args.get(0));
         } else {
            this.line = "";
         }

      }

      public String line() {
         return this.line;
      }

      public String command() {
         return ConsoleEngine.plainCommand(this.command);
      }

      public String rawCommand() {
         return this.command;
      }

      public String variable() {
         return this.variable;
      }

      public List args() {
         return this.args;
      }

      private int closingQuote(String arg) {
         int out = -1;
         char prevChar = ' ';

         for(int i = 1; i < arg.length(); ++i) {
            char c = arg.charAt(i);
            if (!this.parser.isEscapeChar(prevChar) && c == arg.charAt(0)) {
               out = i;
               break;
            }

            prevChar = c;
         }

         return out;
      }

      private String unquote(String arg) {
         return (arg.length() > 1 && arg.startsWith("\"") && arg.endsWith("\"") || arg.startsWith("'") && arg.endsWith("'")) && this.closingQuote(arg) == arg.length() - 1 ? arg.substring(1, arg.length() - 1) : arg;
      }

      private String unescape(String arg) {
         if (arg != null && this.parser.isEscapeChar('\\')) {
            StringBuilder sb = new StringBuilder(arg.length());

            for(int i = 0; i < arg.length(); ++i) {
               char ch = arg.charAt(i);
               if (ch == '\\') {
                  char nextChar = i == arg.length() - 1 ? 92 : arg.charAt(i + 1);
                  if (nextChar >= '0' && nextChar <= '7') {
                     String code = "" + nextChar;
                     ++i;
                     if (i < arg.length() - 1 && arg.charAt(i + 1) >= '0' && arg.charAt(i + 1) <= '7') {
                        code = code + arg.charAt(i + 1);
                        ++i;
                        if (i < arg.length() - 1 && arg.charAt(i + 1) >= '0' && arg.charAt(i + 1) <= '7') {
                           code = code + arg.charAt(i + 1);
                           ++i;
                        }
                     }

                     sb.append((char)Integer.parseInt(code, 8));
                     continue;
                  }

                  switch (nextChar) {
                     case ' ':
                        ch = ' ';
                        break;
                     case '"':
                        ch = '"';
                        break;
                     case '\'':
                        ch = '\'';
                        break;
                     case '\\':
                        ch = '\\';
                        break;
                     case 'b':
                        ch = '\b';
                        break;
                     case 'f':
                        ch = '\f';
                        break;
                     case 'n':
                        ch = '\n';
                        break;
                     case 'r':
                        ch = '\r';
                        break;
                     case 't':
                        ch = '\t';
                        break;
                     case 'u':
                        if (i < arg.length() - 5) {
                           int code = Integer.parseInt("" + arg.charAt(i + 2) + arg.charAt(i + 3) + arg.charAt(i + 4) + arg.charAt(i + 5), 16);
                           sb.append(Character.toChars(code));
                           i += 5;
                           continue;
                        }

                        ch = 'u';
                  }

                  ++i;
               }

               sb.append(ch);
            }

            return sb.toString();
         } else {
            return arg;
         }
      }
   }

   protected static class CommandData {
      private final String rawLine;
      private String command;
      private String[] args;
      private final File file;
      private final boolean append;
      private final String variable;
      private String pipe;

      public CommandData(ArgsParser parser, boolean statement, String rawLine, String variable, File file, boolean append, String pipe) {
         this.rawLine = rawLine;
         this.variable = variable;
         this.file = file;
         this.append = append;
         this.pipe = pipe;
         this.args = new String[0];
         this.command = "";
         if (!statement) {
            parser.parse(rawLine);
            this.command = parser.command();
            if (parser.args().size() > 1) {
               this.args = new String[parser.args().size() - 1];

               for(int i = 1; i < parser.args().size(); ++i) {
                  this.args[i - 1] = parser.unescape(parser.unquote((String)parser.args().get(i)));
               }
            }
         }

      }

      public void setPipe(String pipe) {
         this.pipe = pipe;
      }

      public File file() {
         return this.file;
      }

      public boolean append() {
         return this.append;
      }

      public String variable() {
         return this.variable;
      }

      public String command() {
         return this.command;
      }

      public String[] args() {
         return this.args;
      }

      public String rawLine() {
         return this.rawLine;
      }

      public String pipe() {
         return this.pipe;
      }

      public String toString() {
         return "[rawLine:" + this.rawLine + ", command:" + this.command + ", args:" + Arrays.asList(this.args) + ", variable:" + this.variable + ", file:" + this.file + ", append:" + this.append + ", pipe:" + this.pipe + "]";
      }
   }

   private static class ScriptStore {
      ConsoleEngine engine;
      Map scripts = new HashMap();

      public ScriptStore() {
      }

      public ScriptStore(ConsoleEngine engine) {
         this.engine = engine;
      }

      public void refresh() {
         if (this.engine != null) {
            this.scripts = this.engine.scripts();
         }

      }

      public boolean hasScript(String name) {
         return this.scripts.containsKey(name);
      }

      public boolean isConsoleScript(String name) {
         return (Boolean)this.scripts.getOrDefault(name, false);
      }

      public Set getScripts() {
         return this.scripts.keySet();
      }
   }

   public static class UnknownCommandException extends Exception {
      public UnknownCommandException(String message) {
         super(message);
      }
   }

   private static class PipelineCompleter implements Completer {
      private final NamesAndValues names;
      private final Supplier workDir;
      private final Map pipeName;

      public PipelineCompleter(Supplier workDir, Map pipeName, NamesAndValues names) {
         this.workDir = workDir;
         this.pipeName = pipeName;
         this.names = names;
      }

      public Completer doCompleter() {
         ArgumentCompleter out = new ArgumentCompleter(new Completer[]{this});
         out.setStrict(false);
         return out;
      }

      public void complete(LineReader reader, ParsedLine commandLine, List candidates) {
         assert commandLine != null;

         assert candidates != null;

         ArgsParser ap = new ArgsParser(reader.getParser());
         ap.parse(commandLine.line().substring(0, commandLine.cursor()));
         List<String> args = ap.args();
         if (args.size() >= 2 && this.names.hasPipes(args)) {
            boolean enclosed = ap.isEnclosed((String)args.get(args.size() - 1));
            String pWord = (String)commandLine.words().get(commandLine.wordIndex() - 1);
            if (enclosed && pWord.equals(this.pipeName.get(SystemRegistryImpl.Pipe.NAMED))) {
               for(String name : this.names.namedPipes()) {
                  candidates.add(new Candidate(name, name, (String)null, (String)null, (String)null, (String)null, true));
               }
            } else if ((!enclosed || !pWord.equals(">")) && !pWord.equals(">>")) {
               String buffer = commandLine.word().substring(0, commandLine.wordCursor());
               String param = buffer;
               String curBuf = "";
               int lastDelim = this.names.indexOfLastDelim(buffer);
               if (lastDelim > -1) {
                  param = buffer.substring(lastDelim + 1);
                  curBuf = buffer.substring(0, lastDelim + 1);
               }

               if (curBuf.startsWith("--") && !curBuf.contains("=")) {
                  this.doCandidates(candidates, this.names.options(), curBuf, "", param);
               } else if (param.length() == 0) {
                  this.doCandidates(candidates, this.names.fieldsAndValues(), curBuf, "", "");
               } else if (param.contains(".")) {
                  int point = buffer.lastIndexOf(".");
                  param = buffer.substring(point + 1);
                  curBuf = buffer.substring(0, point + 1);
                  this.doCandidates(candidates, this.names.fields(), curBuf, "", param);
               } else if (this.names.encloseBy(param).length() == 1) {
                  ++lastDelim;
                  String postFix = this.names.encloseBy(param);
                  param = buffer.substring(lastDelim + 1);
                  curBuf = buffer.substring(0, lastDelim + 1);
                  this.doCandidates(candidates, this.names.quoted(), curBuf, postFix, param);
               } else {
                  this.doCandidates(candidates, this.names.fieldsAndValues(), curBuf, "", param);
               }
            } else {
               Completer c = new Completers.FilesCompleter(this.workDir);
               c.complete(reader, commandLine, candidates);
            }

         }
      }

      private void doCandidates(List candidates, Collection fields, String curBuf, String postFix, String hint) {
         if (fields != null) {
            for(String s : fields) {
               if (s != null && s.startsWith(hint)) {
                  candidates.add(new Candidate(AttributedString.stripAnsi(curBuf + s + postFix), s, (String)null, (String)null, (String)null, (String)null, false));
               }
            }

         }
      }
   }

   private class NamesAndValues {
      private final String[] delims;
      private Path fileNames;
      private final Map names;
      private List namedPipes;

      public NamesAndValues() {
         this((ConfigurationPath)null);
      }

      public NamesAndValues(ConfigurationPath configPath) {
         this.delims = new String[]{"&", "\\|", "\\{", "\\}", "\\[", "\\]", "\\(", "\\)", "\\+", "-", "\\*", "=", ">", "<", "~", "!", ":", ",", ";"};
         this.names = new HashMap();
         this.names.put("fields", new ArrayList());
         this.names.put("values", new ArrayList());
         this.names.put("quoted", new ArrayList());
         this.names.put("options", new ArrayList());
         ConsoleEngine consoleEngine = SystemRegistryImpl.this.consoleEngine();
         if (configPath != null && consoleEngine != null) {
            try {
               this.fileNames = configPath.getUserConfig("pipeline-names.json", true);
               Map<String, List<String>> temp = (Map)consoleEngine.slurp(this.fileNames);

               for(Map.Entry entry : temp.entrySet()) {
                  ((List)this.names.get(entry.getKey())).addAll((Collection)entry.getValue());
               }
            } catch (Exception var7) {
            }
         }

      }

      public boolean isPipe(String arg) {
         Map<String, List<String>> customPipes = (Map<String, List<String>>)(SystemRegistryImpl.this.consoleEngine() != null ? SystemRegistryImpl.this.consoleEngine().getPipes() : new HashMap());
         return this.isPipe(arg, customPipes.keySet());
      }

      public boolean hasPipes(Collection args) {
         Map<String, List<String>> customPipes = (Map<String, List<String>>)(SystemRegistryImpl.this.consoleEngine() != null ? SystemRegistryImpl.this.consoleEngine().getPipes() : new HashMap());

         for(String a : args) {
            if (this.isPipe(a, customPipes.keySet()) || a.contains(">") || a.contains(">>")) {
               return true;
            }
         }

         return false;
      }

      private boolean isPipe(String arg, Set pipes) {
         return SystemRegistryImpl.this.pipeName.containsValue(arg) || pipes.contains(arg);
      }

      public void extractNames(String line) {
         if (!SystemRegistryImpl.this.parser.getCommand(line).equals("pipe")) {
            ArgsParser ap = new ArgsParser(SystemRegistryImpl.this.parser);
            ap.parse(line);
            List<String> args = ap.args();
            int pipeId = 0;

            for(String a : args) {
               if (this.isPipe(a)) {
                  break;
               }

               ++pipeId;
            }

            if (pipeId < args.size()) {
               StringBuilder sb = new StringBuilder();
               int redirectPipe = -1;

               for(int i = pipeId + 1; i < args.size(); ++i) {
                  String arg = (String)args.get(i);
                  if (!this.isPipe(arg) && !this.namedPipes().contains(arg) && !arg.matches("\\d+") && redirectPipe != i - 1) {
                     if (!arg.equals(">") && !arg.equals(">>")) {
                        if (arg.matches("\\w+(\\(\\))?")) {
                           this.addValues(arg);
                        } else if (arg.matches("--\\w+(=.*|)$") && arg.length() > 4) {
                           int idx = arg.indexOf(61);
                           if (idx > 0) {
                              if (idx > 4) {
                                 this.addOptions(arg.substring(2, idx));
                              }

                              sb.append(arg.substring(idx + 1));
                              sb.append(" ");
                           } else if (idx == -1) {
                              this.addOptions(arg.substring(2));
                           }
                        } else {
                           sb.append(arg);
                           sb.append(" ");
                        }
                     } else {
                        redirectPipe = i;
                     }
                  } else {
                     redirectPipe = -1;
                  }
               }

               if (sb.length() > 0) {
                  String rest = sb.toString();

                  for(String d : this.delims) {
                     rest = rest.replaceAll(d, " ");
                  }

                  String[] words = rest.split("\\s+");

                  for(String w : words) {
                     if (w.length() >= 3 && !w.matches("\\d+")) {
                        if (this.isQuoted(w)) {
                           this.addQuoted(w.substring(1, w.length() - 1));
                        } else if (w.contains(".")) {
                           for(String f : w.split("\\.")) {
                              if (!f.matches("\\d+") && f.matches("\\w+")) {
                                 this.addFields(f);
                              }
                           }
                        } else if (w.matches("\\w+")) {
                           this.addValues(w);
                        }
                     }
                  }
               }
            }

            this.namedPipes = null;
         }
      }

      public String encloseBy(String param) {
         boolean quoted = param.length() > 0 && (param.startsWith("\"") || param.startsWith("'") || param.startsWith("/"));
         if (quoted && param.length() > 1) {
            quoted = !param.endsWith(Character.toString(param.charAt(0)));
         }

         return quoted ? Character.toString(param.charAt(0)) : "";
      }

      private boolean isQuoted(String word) {
         return word.length() > 1 && (word.startsWith("\"") && word.endsWith("\"") || word.startsWith("'") && word.endsWith("'") || word.startsWith("/") && word.endsWith("/"));
      }

      public int indexOfLastDelim(String word) {
         int out = -1;

         for(String d : this.delims) {
            int x = word.lastIndexOf(d.replace("\\", ""));
            if (x > out) {
               out = x;
            }
         }

         return out;
      }

      private void addFields(String field) {
         this.add("fields", field);
      }

      private void addValues(String arg) {
         this.add("values", arg);
      }

      private void addQuoted(String arg) {
         this.add("quoted", arg);
      }

      private void addOptions(String arg) {
         this.add("options", arg);
      }

      private void add(String where, String value) {
         if (value.length() >= 3) {
            ((List)this.names.get(where)).remove(value);
            ((List)this.names.get(where)).add(0, value);
         }
      }

      public List namedPipes() {
         if (this.namedPipes == null) {
            this.namedPipes = (List)(SystemRegistryImpl.this.consoleId != null ? SystemRegistryImpl.this.consoleEngine().getNamedPipes() : new ArrayList());
         }

         return this.namedPipes;
      }

      public List values() {
         return (List)this.names.get("values");
      }

      public List fields() {
         return (List)this.names.get("fields");
      }

      public List quoted() {
         return (List)this.names.get("quoted");
      }

      public List options() {
         return (List)this.names.get("options");
      }

      private Set fieldsAndValues() {
         Set<String> out = new HashSet();
         out.addAll(this.fields());
         out.addAll(this.values());
         return out;
      }

      private void truncate(String where, int maxSize) {
         if (((List)this.names.get(where)).size() > maxSize) {
            this.names.put(where, ((List)this.names.get(where)).subList(0, maxSize));
         }

      }

      public void save() {
         ConsoleEngine consoleEngine = SystemRegistryImpl.this.consoleEngine();
         if (consoleEngine != null && this.fileNames != null) {
            int maxSize = (Integer)consoleEngine.consoleOption("maxValueNames", 100);
            this.truncate("fields", maxSize);
            this.truncate("values", maxSize);
            this.truncate("quoted", maxSize);
            this.truncate("options", maxSize);
            consoleEngine.persist(this.fileNames, this.names);
         }

      }
   }
}
