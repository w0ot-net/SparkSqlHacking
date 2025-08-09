package org.jline.builtins;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;
import org.jline.reader.Candidate;
import org.jline.reader.LineReader;
import org.jline.reader.ParsedLine;
import org.jline.reader.impl.completer.NullCompleter;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.terminal.Terminal;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;
import org.jline.utils.OSUtils;
import org.jline.utils.StyleResolver;

public class Completers {
   public static class CompletionData {
      public final List options;
      public final String description;
      public final String argument;
      public final String condition;

      public CompletionData(List options, String description, String argument, String condition) {
         this.options = options;
         this.description = description;
         this.argument = argument;
         this.condition = condition;
      }
   }

   public static class Completer implements org.jline.reader.Completer {
      private final CompletionEnvironment environment;

      public Completer(CompletionEnvironment environment) {
         this.environment = environment;
      }

      public void complete(LineReader reader, ParsedLine line, List candidates) {
         if (line.wordIndex() == 0) {
            this.completeCommand(candidates);
         } else {
            this.tryCompleteArguments(reader, line, candidates);
         }

      }

      protected void tryCompleteArguments(LineReader reader, ParsedLine line, List candidates) {
         String command = (String)line.words().get(0);
         String resolved = this.environment.resolveCommand(command);
         Map<String, List<CompletionData>> comp = this.environment.getCompletions();
         if (comp != null) {
            List<CompletionData> cmd = (List)comp.get(resolved);
            if (cmd != null) {
               this.completeCommandArguments(reader, line, candidates, cmd);
            }
         }

      }

      protected void completeCommandArguments(LineReader reader, ParsedLine line, List candidates, List completions) {
         for(CompletionData completion : completions) {
            boolean isOption = line.word().startsWith("-");
            String prevOption = line.wordIndex() >= 2 && ((String)line.words().get(line.wordIndex() - 1)).startsWith("-") ? (String)line.words().get(line.wordIndex() - 1) : null;
            String key = UUID.randomUUID().toString();
            boolean conditionValue = true;
            if (completion.condition != null) {
               Object res = Boolean.FALSE;

               try {
                  res = this.environment.evaluate(reader, line, completion.condition);
               } catch (Throwable var17) {
               }

               conditionValue = this.isTrue(res);
            }

            if (conditionValue && isOption && completion.options != null) {
               for(String opt : completion.options) {
                  candidates.add(new Candidate(opt, opt, "options", completion.description, (String)null, key, true));
               }
            } else if (!isOption && prevOption != null && completion.argument != null && completion.options != null && completion.options.contains(prevOption)) {
               Object res = null;

               try {
                  res = this.environment.evaluate(reader, line, completion.argument);
               } catch (Throwable var16) {
               }

               if (res instanceof Candidate) {
                  candidates.add((Candidate)res);
               } else if (res instanceof String) {
                  candidates.add(new Candidate((String)res, (String)res, (String)null, (String)null, (String)null, (String)null, true));
               } else if (res instanceof Collection) {
                  for(Object s : (Collection)res) {
                     if (s instanceof Candidate) {
                        candidates.add((Candidate)s);
                     } else if (s instanceof String) {
                        candidates.add(new Candidate((String)s, (String)s, (String)null, (String)null, (String)null, (String)null, true));
                     }
                  }
               } else if (res != null && res.getClass().isArray()) {
                  int i = 0;

                  for(int l = Array.getLength(res); i < l; ++i) {
                     Object s = Array.get(res, i);
                     if (s instanceof Candidate) {
                        candidates.add((Candidate)s);
                     } else if (s instanceof String) {
                        candidates.add(new Candidate((String)s, (String)s, (String)null, (String)null, (String)null, (String)null, true));
                     }
                  }
               }
            } else if (!isOption && completion.argument != null) {
               Object res = null;

               try {
                  res = this.environment.evaluate(reader, line, completion.argument);
               } catch (Throwable var15) {
               }

               if (res instanceof Candidate) {
                  candidates.add((Candidate)res);
               } else if (res instanceof String) {
                  candidates.add(new Candidate((String)res, (String)res, (String)null, completion.description, (String)null, (String)null, true));
               } else if (res instanceof Collection) {
                  for(Object s : (Collection)res) {
                     if (s instanceof Candidate) {
                        candidates.add((Candidate)s);
                     } else if (s instanceof String) {
                        candidates.add(new Candidate((String)s, (String)s, (String)null, completion.description, (String)null, (String)null, true));
                     }
                  }
               }
            }
         }

      }

      protected void completeCommand(List candidates) {
         for(String command : this.environment.getCommands()) {
            String name = this.environment.commandName(command);
            boolean resolved = command.equals(this.environment.resolveCommand(name));
            if (!name.startsWith("_")) {
               String desc = null;
               Map<String, List<CompletionData>> comp = this.environment.getCompletions();
               if (comp != null) {
                  List<CompletionData> completions = (List)comp.get(command);
                  if (completions != null) {
                     for(CompletionData completion : completions) {
                        if (completion.description != null && completion.options == null && completion.argument == null && completion.condition == null) {
                           desc = completion.description;
                        }
                     }
                  }
               }

               String key = UUID.randomUUID().toString();
               if (desc != null) {
                  candidates.add(new Candidate(command, command, (String)null, desc, (String)null, key, true));
                  if (resolved) {
                     candidates.add(new Candidate(name, name, (String)null, desc, (String)null, key, true));
                  }
               } else {
                  candidates.add(new Candidate(command, command, (String)null, (String)null, (String)null, key, true));
                  if (resolved) {
                     candidates.add(new Candidate(name, name, (String)null, (String)null, (String)null, key, true));
                  }
               }
            }
         }

      }

      private boolean isTrue(Object result) {
         if (result == null) {
            return false;
         } else if (result instanceof Boolean) {
            return (Boolean)result;
         } else if (result instanceof Number && 0 == ((Number)result).intValue()) {
            return false;
         } else {
            return !"".equals(result) && !"0".equals(result);
         }
      }
   }

   public static class DirectoriesCompleter extends FileNameCompleter {
      private final Supplier currentDir;

      public DirectoriesCompleter(File currentDir) {
         this(currentDir.toPath());
      }

      public DirectoriesCompleter(Path currentDir) {
         this.currentDir = () -> currentDir;
      }

      public DirectoriesCompleter(Supplier currentDir) {
         this.currentDir = currentDir;
      }

      protected Path getUserDir() {
         return (Path)this.currentDir.get();
      }

      protected boolean accept(Path path) {
         return Files.isDirectory(path, new LinkOption[0]) && super.accept(path);
      }
   }

   public static class FilesCompleter extends FileNameCompleter {
      private final Supplier currentDir;
      private final String namePattern;

      public FilesCompleter(File currentDir) {
         this((Path)currentDir.toPath(), (String)null);
      }

      public FilesCompleter(File currentDir, String namePattern) {
         this(currentDir.toPath(), namePattern);
      }

      public FilesCompleter(Path currentDir) {
         this((Path)currentDir, (String)null);
      }

      public FilesCompleter(Path currentDir, String namePattern) {
         this.currentDir = () -> currentDir;
         this.namePattern = this.compilePattern(namePattern);
      }

      public FilesCompleter(Supplier currentDir) {
         this((Supplier)currentDir, (String)null);
      }

      public FilesCompleter(Supplier currentDir, String namePattern) {
         this.currentDir = currentDir;
         this.namePattern = this.compilePattern(namePattern);
      }

      private String compilePattern(String pattern) {
         if (pattern == null) {
            return null;
         } else {
            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < pattern.length(); ++i) {
               char ch = pattern.charAt(i);
               if (ch == '\\') {
                  ++i;
                  ch = pattern.charAt(i);
                  sb.append(ch);
               } else if (ch == '.') {
                  sb.append('\\').append('.');
               } else if (ch == '*') {
                  sb.append('.').append('*');
               } else {
                  sb.append(ch);
               }
            }

            return sb.toString();
         }
      }

      protected Path getUserDir() {
         return (Path)this.currentDir.get();
      }

      protected boolean accept(Path path) {
         if (this.namePattern != null && !Files.isDirectory(path, new LinkOption[0])) {
            return path.getFileName().toString().matches(this.namePattern) && super.accept(path);
         } else {
            return super.accept(path);
         }
      }
   }

   public static class FileNameCompleter implements org.jline.reader.Completer {
      public void complete(LineReader reader, ParsedLine commandLine, List candidates) {
         assert commandLine != null;

         assert candidates != null;

         String buffer = commandLine.word().substring(0, commandLine.wordCursor());
         String sep = this.getSeparator(reader.isSet(LineReader.Option.USE_FORWARD_SLASH));
         int lastSep = buffer.lastIndexOf(sep);

         try {
            Path current;
            String curBuf;
            if (lastSep >= 0) {
               curBuf = buffer.substring(0, lastSep + 1);
               if (curBuf.startsWith("~")) {
                  if (curBuf.startsWith("~" + sep)) {
                     current = this.getUserHome().resolve(curBuf.substring(2));
                  } else {
                     current = this.getUserHome().getParent().resolve(curBuf.substring(1));
                  }
               } else {
                  current = this.getUserDir().resolve(curBuf);
               }
            } else {
               curBuf = "";
               current = this.getUserDir();
            }

            StyleResolver resolver = Styles.lsStyle();

            try {
               DirectoryStream<Path> directory = Files.newDirectoryStream(current, this::accept);

               try {
                  directory.forEach((p) -> {
                     String value = curBuf + p.getFileName().toString();
                     if (Files.isDirectory(p, new LinkOption[0])) {
                        candidates.add(new Candidate(value + (reader.isSet(LineReader.Option.AUTO_PARAM_SLASH) ? sep : ""), this.getDisplay(reader.getTerminal(), p, resolver, sep), (String)null, (String)null, reader.isSet(LineReader.Option.AUTO_REMOVE_SLASH) ? sep : null, (String)null, false));
                     } else {
                        candidates.add(new Candidate(value, this.getDisplay(reader.getTerminal(), p, resolver, sep), (String)null, (String)null, (String)null, (String)null, true));
                     }

                  });
               } catch (Throwable var14) {
                  if (directory != null) {
                     try {
                        directory.close();
                     } catch (Throwable var13) {
                        var14.addSuppressed(var13);
                     }
                  }

                  throw var14;
               }

               if (directory != null) {
                  directory.close();
               }
            } catch (IOException var15) {
            }
         } catch (Exception var16) {
         }

      }

      protected boolean accept(Path path) {
         try {
            return !Files.isHidden(path);
         } catch (IOException var3) {
            return false;
         }
      }

      protected Path getUserDir() {
         return Paths.get(System.getProperty("user.dir"));
      }

      protected Path getUserHome() {
         return Paths.get(System.getProperty("user.home"));
      }

      protected String getSeparator(boolean useForwardSlash) {
         return useForwardSlash ? "/" : this.getUserDir().getFileSystem().getSeparator();
      }

      protected String getDisplay(Terminal terminal, Path p, StyleResolver resolver, String separator) {
         AttributedStringBuilder sb = new AttributedStringBuilder();
         String name = p.getFileName().toString();
         int idx = name.lastIndexOf(".");
         String type = idx != -1 ? ".*" + name.substring(idx) : null;
         if (Files.isSymbolicLink(p)) {
            sb.styled((AttributedStyle)resolver.resolve(".ln"), (CharSequence)name).append((CharSequence)"@");
         } else if (Files.isDirectory(p, new LinkOption[0])) {
            sb.styled((AttributedStyle)resolver.resolve(".di"), (CharSequence)name).append((CharSequence)separator);
         } else if (Files.isExecutable(p) && !OSUtils.IS_WINDOWS) {
            sb.styled((AttributedStyle)resolver.resolve(".ex"), (CharSequence)name).append((CharSequence)"*");
         } else if (type != null && resolver.resolve(type).getStyle() != 0L) {
            sb.styled((AttributedStyle)resolver.resolve(type), (CharSequence)name);
         } else if (Files.isRegularFile(p, new LinkOption[0])) {
            sb.styled((AttributedStyle)resolver.resolve(".fi"), (CharSequence)name);
         } else {
            sb.append((CharSequence)name);
         }

         return sb.toAnsi(terminal);
      }
   }

   public static class TreeCompleter implements org.jline.reader.Completer {
      final Map completers;
      final RegexCompleter completer;

      public TreeCompleter(Node... nodes) {
         this(Arrays.asList(nodes));
      }

      public TreeCompleter(List nodes) {
         this.completers = new HashMap();
         StringBuilder sb = new StringBuilder();
         this.addRoots(sb, nodes);
         String var10003 = sb.toString();
         Map var10004 = this.completers;
         Objects.requireNonNull(var10004);
         this.completer = new RegexCompleter(var10003, var10004::get);
      }

      public static Node node(Object... objs) {
         org.jline.reader.Completer comp = null;
         List<Candidate> cands = new ArrayList();
         List<Node> nodes = new ArrayList();

         for(Object obj : objs) {
            if (obj instanceof String) {
               cands.add(new Candidate((String)obj));
            } else if (obj instanceof Candidate) {
               cands.add((Candidate)obj);
            } else if (obj instanceof Node) {
               nodes.add((Node)obj);
            } else {
               if (!(obj instanceof org.jline.reader.Completer)) {
                  throw new IllegalArgumentException();
               }

               comp = (org.jline.reader.Completer)obj;
            }
         }

         if (comp != null) {
            if (!cands.isEmpty()) {
               throw new IllegalArgumentException();
            } else {
               return new Node(comp, nodes);
            }
         } else if (!cands.isEmpty()) {
            return new Node((r, l, c) -> c.addAll(cands), nodes);
         } else {
            throw new IllegalArgumentException();
         }
      }

      void addRoots(StringBuilder sb, List nodes) {
         if (!nodes.isEmpty()) {
            sb.append(" ( ");
            boolean first = true;

            for(Node n : nodes) {
               if (first) {
                  first = false;
               } else {
                  sb.append(" | ");
               }

               String name = "c" + this.completers.size();
               this.completers.put(name, n.completer);
               sb.append(name);
               this.addRoots(sb, n.nodes);
            }

            sb.append(" ) ");
         }

      }

      public void complete(LineReader reader, ParsedLine line, List candidates) {
         this.completer.complete(reader, line, candidates);
      }

      public static class Node {
         final org.jline.reader.Completer completer;
         final List nodes;

         public Node(org.jline.reader.Completer completer, List nodes) {
            this.completer = completer;
            this.nodes = nodes;
         }
      }
   }

   public static class RegexCompleter implements org.jline.reader.Completer {
      private final NfaMatcher matcher;
      private final Function completers;
      private final ThreadLocal reader = new ThreadLocal();

      public RegexCompleter(String syntax, Function completers) {
         this.matcher = new NfaMatcher(syntax, this::doMatch);
         this.completers = completers;
      }

      public synchronized void complete(LineReader reader, ParsedLine line, List candidates) {
         List<String> words = line.words().subList(0, line.wordIndex());
         this.reader.set(reader);

         for(String n : this.matcher.matchPartial(words)) {
            ((org.jline.reader.Completer)this.completers.apply(n)).complete(reader, new ArgumentLine(line.word(), line.wordCursor()), candidates);
         }

         this.reader.set((Object)null);
      }

      private boolean doMatch(String arg, String name) {
         List<Candidate> candidates = new ArrayList();
         LineReader r = (LineReader)this.reader.get();
         boolean caseInsensitive = r != null && r.isSet(LineReader.Option.CASE_INSENSITIVE);
         ((org.jline.reader.Completer)this.completers.apply(name)).complete(r, new ArgumentLine(arg, arg.length()), candidates);
         return candidates.stream().anyMatch((c) -> caseInsensitive ? c.value().equalsIgnoreCase(arg) : c.value().equals(arg));
      }

      public static class ArgumentLine implements ParsedLine {
         private final String word;
         private final int cursor;

         public ArgumentLine(String word, int cursor) {
            this.word = word;
            this.cursor = cursor;
         }

         public String word() {
            return this.word;
         }

         public int wordCursor() {
            return this.cursor;
         }

         public int wordIndex() {
            return 0;
         }

         public List words() {
            return Collections.singletonList(this.word);
         }

         public String line() {
            return this.word;
         }

         public int cursor() {
            return this.cursor;
         }
      }
   }

   public static class OptDesc {
      private String shortOption;
      private String longOption;
      private String description;
      private org.jline.reader.Completer valueCompleter;

      protected static List compile(Map optionValues, Collection options) {
         List<OptDesc> out = new ArrayList();

         for(Map.Entry entry : optionValues.entrySet()) {
            if (((String)entry.getKey()).startsWith("--")) {
               out.add(new OptDesc((String)null, (String)entry.getKey(), new StringsCompleter((Iterable)entry.getValue())));
            } else if (((String)entry.getKey()).matches("-[a-zA-Z]")) {
               out.add(new OptDesc((String)entry.getKey(), (String)null, new StringsCompleter((Iterable)entry.getValue())));
            }
         }

         for(String o : options) {
            if (o.startsWith("--")) {
               out.add(new OptDesc((String)null, o));
            } else if (o.matches("-[a-zA-Z]")) {
               out.add(new OptDesc(o, (String)null));
            }
         }

         return out;
      }

      public OptDesc(String shortOption, String longOption, String description, org.jline.reader.Completer valueCompleter) {
         this.shortOption = shortOption;
         this.longOption = longOption;
         this.description = description;
         this.valueCompleter = valueCompleter;
      }

      public OptDesc(String shortOption, String longOption, org.jline.reader.Completer valueCompleter) {
         this(shortOption, longOption, (String)null, valueCompleter);
      }

      public OptDesc(String shortOption, String longOption, String description) {
         this(shortOption, longOption, description, (org.jline.reader.Completer)null);
      }

      public OptDesc(String shortOption, String longOption) {
         this(shortOption, longOption, (String)null, (org.jline.reader.Completer)null);
      }

      protected OptDesc() {
      }

      public void setValueCompleter(org.jline.reader.Completer valueCompleter) {
         this.valueCompleter = valueCompleter;
      }

      public String longOption() {
         return this.longOption;
      }

      public String shortOption() {
         return this.shortOption;
      }

      public String description() {
         return this.description;
      }

      protected boolean hasValue() {
         return this.valueCompleter != null && this.valueCompleter != NullCompleter.INSTANCE;
      }

      protected org.jline.reader.Completer valueCompleter() {
         return this.valueCompleter;
      }

      protected void completeOption(LineReader reader, ParsedLine commandLine, List candidates, boolean longOpt) {
         if (!longOpt) {
            if (this.shortOption != null) {
               candidates.add(new Candidate(this.shortOption, this.shortOption, (String)null, this.description, (String)null, (String)null, false));
            }
         } else if (this.longOption != null) {
            if (this.hasValue()) {
               candidates.add(new Candidate(this.longOption + "=", this.longOption, (String)null, this.description, (String)null, (String)null, false));
            } else {
               candidates.add(new Candidate(this.longOption, this.longOption, (String)null, this.description, (String)null, (String)null, true));
            }
         }

      }

      protected boolean completeValue(LineReader reader, ParsedLine commandLine, List candidates, String curBuf, String partialValue) {
         boolean out = false;
         List<Candidate> temp = new ArrayList();
         ParsedLine pl = reader.getParser().parse(partialValue, partialValue.length());
         this.valueCompleter.complete(reader, pl, temp);

         for(Candidate c : temp) {
            String v = c.value();
            if (v.startsWith(partialValue)) {
               out = true;
               String val = c.value();
               if (this.valueCompleter instanceof FileNameCompleter) {
                  FileNameCompleter cc = (FileNameCompleter)this.valueCompleter;
                  String sep = cc.getSeparator(reader.isSet(LineReader.Option.USE_FORWARD_SLASH));
                  val = cc.getDisplay(reader.getTerminal(), Paths.get(c.value()), Styles.lsStyle(), sep);
               }

               candidates.add(new Candidate(curBuf + v, val, (String)null, (String)null, (String)null, (String)null, c.complete()));
            }
         }

         return out;
      }

      protected boolean match(String option) {
         return this.shortOption != null && this.shortOption.equals(option) || this.longOption != null && this.longOption.equals(option);
      }

      protected boolean startsWith(String option) {
         return this.shortOption != null && this.shortOption.startsWith(option) || this.longOption != null && this.longOption.startsWith(option);
      }
   }

   public static class OptionCompleter implements org.jline.reader.Completer {
      private Function commandOptions;
      private Collection options;
      private List argsCompleters;
      private int startPos;

      public OptionCompleter(org.jline.reader.Completer completer, Function commandOptions, int startPos) {
         this.argsCompleters = new ArrayList();
         this.startPos = startPos;
         this.commandOptions = commandOptions;
         this.argsCompleters.add(completer);
      }

      public OptionCompleter(List completers, Function commandOptions, int startPos) {
         this.argsCompleters = new ArrayList();
         this.startPos = startPos;
         this.commandOptions = commandOptions;
         this.argsCompleters = new ArrayList(completers);
      }

      public OptionCompleter(List completers, Map optionValues, Collection options, int startPos) {
         this(optionValues, options, startPos);
         this.argsCompleters = new ArrayList(completers);
      }

      public OptionCompleter(org.jline.reader.Completer completer, Map optionValues, Collection options, int startPos) {
         this(optionValues, options, startPos);
         this.argsCompleters.add(completer);
      }

      public OptionCompleter(Map optionValues, Collection options, int startPos) {
         this(Completers.OptDesc.compile(optionValues, options), startPos);
      }

      public OptionCompleter(org.jline.reader.Completer completer, Collection options, int startPos) {
         this(options, startPos);
         this.argsCompleters.add(completer);
      }

      public OptionCompleter(List completers, Collection options, int startPos) {
         this(options, startPos);
         this.argsCompleters = new ArrayList(completers);
      }

      public OptionCompleter(Collection options, int startPos) {
         this.argsCompleters = new ArrayList();
         this.options = options;
         this.startPos = startPos;
      }

      public void setStartPos(int startPos) {
         this.startPos = startPos;
      }

      public void complete(LineReader reader, ParsedLine commandLine, List candidates) {
         assert commandLine != null;

         assert candidates != null;

         List<String> words = commandLine.words();
         String buffer = commandLine.word().substring(0, commandLine.wordCursor());
         if (this.startPos >= words.size()) {
            candidates.add(new Candidate(buffer, buffer, (String)null, (String)null, (String)null, (String)null, true));
         } else {
            String command = reader.getParser().getCommand((String)words.get(this.startPos - 1));
            if (buffer.startsWith("-")) {
               boolean addbuff = true;
               boolean valueCandidates = false;
               boolean longOption = buffer.startsWith("--");
               int eq = buffer.matches("-[a-zA-Z][a-zA-Z0-9]+") ? 2 : buffer.indexOf(61);
               if (eq >= 0) {
                  addbuff = false;
                  int nb = buffer.contains("=") ? 1 : 0;
                  String value = buffer.substring(eq + nb);
                  String curBuf = buffer.substring(0, eq + nb);
                  String opt = buffer.substring(0, eq);
                  OptDesc option = this.findOptDesc(command, opt);
                  if (option.hasValue()) {
                     valueCandidates = option.completeValue(reader, commandLine, candidates, curBuf, value);
                  }
               } else {
                  List<String> usedOptions = new ArrayList();

                  for(int i = this.startPos; i < words.size(); ++i) {
                     if (((String)words.get(i)).startsWith("-")) {
                        String w = (String)words.get(i);
                        int ind = w.indexOf(61);
                        if (ind < 0) {
                           usedOptions.add(w);
                        } else {
                           usedOptions.add(w.substring(0, ind));
                        }
                     }
                  }

                  for(OptDesc o : this.commandOptions == null ? this.options : (Collection)this.commandOptions.apply(command)) {
                     if (!usedOptions.contains(o.shortOption()) && !usedOptions.contains(o.longOption())) {
                        if (o.startsWith(buffer)) {
                           addbuff = false;
                        }

                        o.completeOption(reader, commandLine, candidates, longOption);
                     }
                  }
               }

               if (buffer.contains("=") && !buffer.endsWith("=") && !valueCandidates || addbuff) {
                  candidates.add(new Candidate(buffer, buffer, (String)null, (String)null, (String)null, (String)null, true));
               }
            } else if (words.size() > 1 && this.shortOptionValueCompleter(command, (String)words.get(words.size() - 2)) != null) {
               this.shortOptionValueCompleter(command, (String)words.get(words.size() - 2)).complete(reader, commandLine, candidates);
            } else if (words.size() > 1 && this.longOptionValueCompleter(command, (String)words.get(words.size() - 2)) != null) {
               this.longOptionValueCompleter(command, (String)words.get(words.size() - 2)).complete(reader, commandLine, candidates);
            } else if (!this.argsCompleters.isEmpty()) {
               int args = -1;

               for(int i = this.startPos; i < words.size(); ++i) {
                  if (!((String)words.get(i)).startsWith("-") && i > 0 && this.shortOptionValueCompleter(command, (String)words.get(i - 1)) == null && this.longOptionValueCompleter(command, (String)words.get(i - 1)) == null) {
                     ++args;
                  }
               }

               if (args == -1) {
                  candidates.add(new Candidate(buffer, buffer, (String)null, (String)null, (String)null, (String)null, true));
               } else if (args < this.argsCompleters.size()) {
                  ((org.jline.reader.Completer)this.argsCompleters.get(args)).complete(reader, commandLine, candidates);
               } else {
                  ((org.jline.reader.Completer)this.argsCompleters.get(this.argsCompleters.size() - 1)).complete(reader, commandLine, candidates);
               }
            }

         }
      }

      private org.jline.reader.Completer longOptionValueCompleter(String command, String opt) {
         if (!opt.matches("--[a-zA-Z]+")) {
            return null;
         } else {
            Collection<OptDesc> optDescs = this.commandOptions == null ? this.options : (Collection)this.commandOptions.apply(command);
            OptDesc option = this.findOptDesc(optDescs, opt);
            return option.hasValue() ? option.valueCompleter() : null;
         }
      }

      private org.jline.reader.Completer shortOptionValueCompleter(String command, String opt) {
         if (!opt.matches("-[a-zA-Z]+")) {
            return null;
         } else {
            org.jline.reader.Completer out = null;
            Collection<OptDesc> optDescs = this.commandOptions == null ? this.options : (Collection)this.commandOptions.apply(command);
            if (opt.length() == 2) {
               out = this.findOptDesc(optDescs, opt).valueCompleter();
            } else if (opt.length() > 2) {
               for(int i = 1; i < opt.length(); ++i) {
                  OptDesc o = this.findOptDesc(optDescs, "-" + opt.charAt(i));
                  if (o.shortOption() == null) {
                     return null;
                  }

                  if (out == null) {
                     out = o.valueCompleter();
                  }
               }
            }

            return out;
         }
      }

      private OptDesc findOptDesc(String command, String opt) {
         return this.findOptDesc(this.commandOptions == null ? this.options : (Collection)this.commandOptions.apply(command), opt);
      }

      private OptDesc findOptDesc(Collection optDescs, String opt) {
         for(OptDesc o : optDescs) {
            if (o.match(opt)) {
               return o;
            }
         }

         return new OptDesc();
      }
   }

   public static class AnyCompleter implements org.jline.reader.Completer {
      public static final AnyCompleter INSTANCE = new AnyCompleter();

      public void complete(LineReader reader, ParsedLine commandLine, List candidates) {
         assert commandLine != null;

         assert candidates != null;

         String buffer = commandLine.word().substring(0, commandLine.wordCursor());
         candidates.add(new Candidate(AttributedString.stripAnsi(buffer), buffer, (String)null, (String)null, (String)null, (String)null, true));
      }
   }

   public interface CompletionEnvironment {
      Map getCompletions();

      Set getCommands();

      String resolveCommand(String var1);

      String commandName(String var1);

      Object evaluate(LineReader var1, ParsedLine var2, String var3) throws Exception;
   }
}
