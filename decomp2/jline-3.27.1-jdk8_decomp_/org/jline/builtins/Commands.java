package org.jline.builtins;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.jline.keymap.KeyMap;
import org.jline.reader.Binding;
import org.jline.reader.Highlighter;
import org.jline.reader.History;
import org.jline.reader.LineReader;
import org.jline.reader.Macro;
import org.jline.reader.Reference;
import org.jline.reader.Widget;
import org.jline.terminal.Terminal;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;
import org.jline.utils.StyleResolver;

public class Commands {
   public static void tmux(Terminal terminal, PrintStream out, PrintStream err, Supplier getter, Consumer setter, Consumer runner, String[] argv) throws Exception {
      String[] usage = new String[]{"tmux -  terminal multiplexer", "Usage: tmux [command]", "  -? --help                    Show help"};
      Options opt = Options.compile(usage).parse((Object[])argv);
      if (opt.isSet("help")) {
         throw new Options.HelpException(opt.usage());
      } else {
         if (argv.length == 0) {
            Object instance = getter.get();
            if (instance != null) {
               err.println("tmux: can't run tmux inside itself");
            } else {
               Tmux tmux = new Tmux(terminal, err, runner);
               setter.accept(tmux);

               try {
                  tmux.run();
               } finally {
                  setter.accept((Object)null);
               }
            }
         } else {
            Object instance = getter.get();
            if (instance != null) {
               ((Tmux)instance).execute(out, err, Arrays.asList(argv));
            } else {
               err.println("tmux: no instance running");
            }
         }

      }
   }

   public static void nano(Terminal terminal, PrintStream out, PrintStream err, Path currentDir, String[] argv) throws Exception {
      nano(terminal, out, err, currentDir, argv, (ConfigurationPath)null);
   }

   public static void nano(Terminal terminal, PrintStream out, PrintStream err, Path currentDir, String[] argv, ConfigurationPath configPath) throws Exception {
      Options opt = Options.compile(Nano.usage()).parse((Object[])argv);
      if (opt.isSet("help")) {
         throw new Options.HelpException(opt.usage());
      } else {
         Nano edit = new Nano(terminal, currentDir, opt, configPath);
         edit.open(opt.args());
         edit.run();
      }
   }

   public static void less(Terminal terminal, InputStream in, PrintStream out, PrintStream err, Path currentDir, Object[] argv) throws Exception {
      less(terminal, in, out, err, currentDir, argv, (ConfigurationPath)null);
   }

   public static void less(Terminal terminal, InputStream in, PrintStream out, PrintStream err, Path currentDir, Object[] argv, ConfigurationPath configPath) throws Exception {
      Options opt = Options.compile(Less.usage()).parse(argv);
      if (opt.isSet("help")) {
         throw new Options.HelpException(opt.usage());
      } else {
         Less less = new Less(terminal, currentDir, opt, configPath);
         List<Source> sources = new ArrayList();
         if (opt.argObjects().isEmpty()) {
            opt.argObjects().add("-");
         }

         for(Object o : opt.argObjects()) {
            if (o instanceof String) {
               String arg = (String)o;
               arg = arg.startsWith("~") ? arg.replace("~", System.getProperty("user.home")) : arg;
               if ("-".equals(arg)) {
                  sources.add(new Source.StdInSource(in));
               } else if (!arg.contains("*") && !arg.contains("?")) {
                  sources.add(new Source.URLSource(currentDir.resolve(arg).toUri().toURL(), arg));
               } else {
                  for(Path p : findFiles(currentDir, arg)) {
                     sources.add(new Source.URLSource(p.toUri().toURL(), p.toString()));
                  }
               }
            } else if (o instanceof Source) {
               sources.add((Source)o);
            } else {
               ByteArrayInputStream bais = null;
               if (o instanceof String[]) {
                  bais = new ByteArrayInputStream(String.join("\n", (String[])o).getBytes());
               } else if (o instanceof ByteArrayInputStream) {
                  bais = (ByteArrayInputStream)o;
               } else if (o instanceof byte[]) {
                  bais = new ByteArrayInputStream((byte[])o);
               }

               if (bais != null) {
                  sources.add(new Source.InputStreamSource(bais, true, "Less"));
               }
            }
         }

         less.run(sources);
      }
   }

   protected static List findFiles(Path root, String files) throws IOException {
      files = files.startsWith("~") ? files.replace("~", System.getProperty("user.home")) : files;
      Path searchRoot = Paths.get("/");
      String regex;
      if ((new File(files)).isAbsolute()) {
         regex = files.replaceAll("\\\\", "/").replaceAll("//", "/");
         if (regex.contains("/")) {
            String sr;
            for(sr = regex.substring(0, regex.lastIndexOf("/") + 1); sr.contains("*") || sr.contains("?"); sr = sr.substring(0, sr.lastIndexOf("/"))) {
            }

            searchRoot = Paths.get(sr + "/");
         }
      } else {
         regex = (root.toString().length() == 0 ? "" : root + "/") + files;
         regex = regex.replaceAll("\\\\", "/").replaceAll("//", "/");
         searchRoot = root;
      }

      PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + regex);
      Stream<Path> pathStream = Files.walk(searchRoot);

      List var6;
      try {
         Objects.requireNonNull(pathMatcher);
         var6 = (List)pathStream.filter(pathMatcher::matches).collect(Collectors.toList());
      } catch (Throwable var9) {
         if (pathStream != null) {
            try {
               pathStream.close();
            } catch (Throwable var8) {
               var9.addSuppressed(var8);
            }
         }

         throw var9;
      }

      if (pathStream != null) {
         pathStream.close();
      }

      return var6;
   }

   public static void history(LineReader reader, PrintStream out, PrintStream err, Path currentDir, String[] argv) throws Exception {
      String[] usage = new String[]{"history -  list history of commands", "Usage: history [-dnrfEie] [-m match] [first] [last]", "       history -ARWI [filename]", "       history -s [old=new] [command]", "       history --clear", "       history --save", "  -? --help                       Displays command help", "     --clear                      Clear history", "     --save                       Save history", "  -m match                        If option -m is present the first argument is taken as a pattern", "                                  and only the history events matching the pattern will be shown", "  -d                              Print timestamps for each event", "  -f                              Print full time date stamps in the US format", "  -E                              Print full time date stamps in the European format", "  -i                              Print full time date stamps in ISO8601 format", "  -n                              Suppresses command numbers", "  -r                              Reverses the order of the commands", "  -A                              Appends the history out to the given file", "  -R                              Reads the history from the given file", "  -W                              Writes the history out to the given file", "  -I                              If added to -R, only the events that are not contained within the internal list are added", "                                  If added to -W or -A, only the events that are new since the last incremental operation", "                                  to the file are added", "  [first] [last]                  These optional arguments may be specified as a number or as a string. A negative number", "                                  is used as an offset to the current history event number. A string specifies the most", "                                  recent event beginning with the given string.", "  -e                              Uses the nano editor to edit the commands before executing", "  -s                              Re-executes the command without invoking an editor"};
      Options opt = Options.compile(usage).parse((Object[])argv);
      if (opt.isSet("help")) {
         throw new Options.HelpException(opt.usage());
      } else {
         History history = reader.getHistory();
         boolean done = true;
         boolean increment = opt.isSet("I");
         if (opt.isSet("clear")) {
            history.purge();
         } else if (opt.isSet("save")) {
            history.save();
         } else if (opt.isSet("A")) {
            Path file = opt.args().size() > 0 ? currentDir.resolve((String)opt.args().get(0)) : null;
            history.append(file, increment);
         } else if (opt.isSet("R")) {
            Path file = opt.args().size() > 0 ? currentDir.resolve((String)opt.args().get(0)) : null;
            history.read(file, increment);
         } else if (opt.isSet("W")) {
            Path file = opt.args().size() > 0 ? currentDir.resolve((String)opt.args().get(0)) : null;
            history.write(file, increment);
         } else {
            done = false;
         }

         if (!done) {
            ReExecute execute = new ReExecute(history, opt);
            int argId = execute.getArgId();
            Pattern pattern = null;
            if (opt.isSet("m") && opt.args().size() > argId) {
               StringBuilder sb = new StringBuilder();
               char prev = '0';

               for(char c : ((String)opt.args().get(argId++)).toCharArray()) {
                  if (c == '*' && prev != '\\' && prev != '.') {
                     sb.append('.');
                  }

                  sb.append(c);
                  prev = c;
               }

               pattern = Pattern.compile(sb.toString(), 32);
            }

            boolean reverse = opt.isSet("r") || opt.isSet("s") && opt.args().size() <= argId;
            int firstId = opt.args().size() > argId ? retrieveHistoryId(history, (String)opt.args().get(argId++)) : -17;
            int lastId = opt.args().size() > argId ? retrieveHistoryId(history, (String)opt.args().get(argId++)) : -1;
            firstId = historyId(firstId, history.first(), history.last());
            lastId = historyId(lastId, history.first(), history.last());
            if (firstId > lastId) {
               int tmpId = firstId;
               firstId = lastId;
               lastId = tmpId;
               reverse = !reverse;
            }

            int tot = lastId - firstId + 1;
            int listed = 0;
            Highlighter highlighter = reader.getHighlighter();
            Iterator<History.Entry> iter = null;
            if (reverse) {
               iter = history.reverseIterator(lastId);
            } else {
               iter = history.iterator(firstId);
            }

            while(iter.hasNext() && listed < tot) {
               History.Entry entry = (History.Entry)iter.next();
               ++listed;
               if (pattern == null || pattern.matcher(entry.line()).matches()) {
                  if (execute.isExecute()) {
                     if (!execute.isEdit()) {
                        execute.addCommandInBuffer(reader, entry.line());
                        break;
                     }

                     execute.addCommandInFile(entry.line());
                  } else {
                     AttributedStringBuilder sb = new AttributedStringBuilder();
                     if (!opt.isSet("n")) {
                        sb.append((CharSequence)"  ");
                        sb.styled((Function)(AttributedStyle::bold), (CharSequence)String.format("%3d", entry.index()));
                     }

                     if (opt.isSet("d") || opt.isSet("f") || opt.isSet("E") || opt.isSet("i")) {
                        sb.append((CharSequence)"  ");
                        if (opt.isSet("d")) {
                           LocalTime lt = LocalTime.from(entry.time().atZone(ZoneId.systemDefault())).truncatedTo(ChronoUnit.SECONDS);
                           DateTimeFormatter.ISO_LOCAL_TIME.formatTo(lt, sb);
                        } else {
                           LocalDateTime lt = LocalDateTime.from(entry.time().atZone(ZoneId.systemDefault()).truncatedTo(ChronoUnit.MINUTES));
                           String format = "yyyy-MM-dd hh:mm";
                           if (opt.isSet("f")) {
                              format = "MM/dd/yy hh:mm";
                           } else if (opt.isSet("E")) {
                              format = "dd.MM.yyyy hh:mm";
                           }

                           DateTimeFormatter.ofPattern(format).formatTo(lt, sb);
                        }
                     }

                     sb.append((CharSequence)"  ");
                     sb.append(highlighter.highlight(reader, entry.line()));
                     out.println(sb.toAnsi(reader.getTerminal()));
                  }
               }
            }

            execute.editCommandsAndClose(reader);
         }
      }
   }

   private static int historyId(int id, int minId, int maxId) {
      int out = id;
      if (id < 0) {
         out = maxId + id + 1;
      }

      if (out < minId) {
         out = minId;
      } else if (out > maxId) {
         out = maxId;
      }

      return out;
   }

   private static int retrieveHistoryId(History history, String s) throws IllegalArgumentException {
      try {
         return Integer.parseInt(s);
      } catch (NumberFormatException var5) {
         Iterator<History.Entry> iter = history.iterator();

         while(iter.hasNext()) {
            History.Entry entry = (History.Entry)iter.next();
            if (entry.line().startsWith(s)) {
               return entry.index();
            }
         }

         throw new IllegalArgumentException("history: event not found: " + s);
      }
   }

   public static void complete(LineReader reader, PrintStream out, PrintStream err, Map completions, String[] argv) throws Options.HelpException {
      String[] usage = new String[]{"complete -  edit command specific tab-completions", "Usage: complete", "  -? --help                       Displays command help", "  -c --command=COMMAND            Command to add completion to", "  -d --description=DESCRIPTION    Description of this completions", "  -e --erase                      Erase the completions", "  -s --short-option=SHORT_OPTION  Posix-style option to complete", "  -l --long-option=LONG_OPTION    GNU-style option to complete", "  -a --argument=ARGUMENTS         A list of possible arguments", "  -n --condition=CONDITION        The completion should only be used if the", "                                  specified command has a zero exit status"};
      Options opt = Options.compile(usage).parse((Object[])argv);
      if (opt.isSet("help")) {
         throw new Options.HelpException(opt.usage());
      } else {
         String command = opt.get("command");
         if (opt.isSet("erase")) {
            completions.remove(command);
         } else {
            List<Completers.CompletionData> cmdCompletions = (List)completions.computeIfAbsent(command, (s) -> new ArrayList());
            List<String> options = null;
            if (opt.isSet("short-option")) {
               for(String op : opt.getList("short-option")) {
                  if (options == null) {
                     options = new ArrayList();
                  }

                  options.add("-" + op);
               }
            }

            if (opt.isSet("long-option")) {
               for(String op : opt.getList("long-option")) {
                  if (options == null) {
                     options = new ArrayList();
                  }

                  options.add("--" + op);
               }
            }

            String description = opt.isSet("description") ? opt.get("description") : null;
            String argument = opt.isSet("argument") ? opt.get("argument") : null;
            String condition = opt.isSet("condition") ? opt.get("condition") : null;
            cmdCompletions.add(new Completers.CompletionData(options, description, argument, condition));
         }
      }
   }

   public static void widget(LineReader reader, PrintStream out, PrintStream err, Function widgetCreator, String[] argv) throws Exception {
      String[] usage = new String[]{"widget -  manipulate widgets", "Usage: widget -N new-widget [function-name]", "       widget -D widget ...", "       widget -A old-widget new-widget", "       widget -U string ...", "       widget -l [options]", "  -? --help                       Displays command help", "  -A                              Create alias to widget", "  -N                              Create new widget", "  -D                              Delete widgets", "  -U                              Push characters to the stack", "  -l                              List user-defined widgets", "  -a                              With -l, list all widgets"};
      Options opt = Options.compile(usage).parse((Object[])argv);
      if (opt.isSet("help")) {
         throw new Options.HelpException(opt.usage());
      } else {
         int actions = (opt.isSet("N") ? 1 : 0) + (opt.isSet("D") ? 1 : 0) + (opt.isSet("U") ? 1 : 0) + (opt.isSet("l") ? 1 : 0) + (opt.isSet("A") ? 1 : 0);
         if (actions > 1) {
            err.println("widget: incompatible operation selection options");
         } else {
            if (opt.isSet("l")) {
               TreeSet<String> ws = new TreeSet(reader.getWidgets().keySet());
               if (opt.isSet("a")) {
                  for(String s : new HashSet(ws)) {
                     ws.add(((Widget)reader.getWidgets().get(s)).toString());
                  }
               }

               for(String s : ws) {
                  if (opt.isSet("a")) {
                     out.println(s);
                  } else if (!((Widget)reader.getWidgets().get(s)).toString().startsWith(".")) {
                     out.println(s + " (" + reader.getWidgets().get(s) + ")");
                  }
               }
            } else if (opt.isSet("N")) {
               if (opt.args().size() < 1) {
                  err.println("widget: not enough arguments for -N");
                  return;
               }

               if (opt.args().size() > 2) {
                  err.println("widget: too many arguments for -N");
                  return;
               }

               String name = (String)opt.args().get(0);
               String func = opt.args().size() == 2 ? (String)opt.args().get(1) : name;
               reader.getWidgets().put(name, (Widget)widgetCreator.apply(func));
            } else if (opt.isSet("D")) {
               for(String name : opt.args()) {
                  reader.getWidgets().remove(name);
               }
            } else if (opt.isSet("A")) {
               if (opt.args().size() < 2) {
                  err.println("widget: not enough arguments for -A");
                  return;
               }

               if (opt.args().size() > 2) {
                  err.println("widget: too many arguments for -A");
                  return;
               }

               Widget org = null;
               if (((String)opt.args().get(0)).startsWith(".")) {
                  org = (Widget)reader.getBuiltinWidgets().get(((String)opt.args().get(0)).substring(1));
               } else {
                  org = (Widget)reader.getWidgets().get(opt.args().get(0));
               }

               if (org == null) {
                  err.println("widget: no such widget `" + (String)opt.args().get(0) + "'");
                  return;
               }

               reader.getWidgets().put((String)opt.args().get(1), org);
            } else if (opt.isSet("U")) {
               for(String arg : opt.args()) {
                  reader.runMacro(KeyMap.translate(arg));
               }
            } else if (opt.args().size() == 1) {
               reader.callWidget((String)opt.args().get(0));
            }

         }
      }
   }

   public static void keymap(LineReader reader, PrintStream out, PrintStream err, String[] argv) throws Options.HelpException {
      String[] usage = new String[]{"keymap -  manipulate keymaps", "Usage: keymap [options] -l [-L] [keymap ...]", "       keymap [options] -d", "       keymap [options] -D keymap ...", "       keymap [options] -A old-keymap new-keymap", "       keymap [options] -N new-keymap [old-keymap]", "       keymap [options] -M", "       keymap [options] -r in-string ...", "       keymap [options] -s in-string out-string ...", "       keymap [options] in-string command ...", "       keymap [options] [in-string]", "  -? --help                       Displays command help", "  -A                              Create alias to keymap", "  -D                              Delete named keymaps", "  -L                              Output in form of keymap commands", "  -M (default=main)               Specify keymap to select", "  -N                              Create new keymap", "  -R                              Interpret in-strings as ranges", "  -a                              Select vicmd keymap", "  -d                              Delete existing keymaps and reset to default state", "  -e                              Select emacs keymap and bind it to main", "  -l                              List existing keymap names", "  -p                              List bindings which have given key sequence as a a prefix", "  -r                              Unbind specified in-strings ", "  -s                              Bind each in-string to each out-string ", "  -v                              Select viins keymap and bind it to main"};
      Options opt = Options.compile(usage).parse((Object[])argv);
      if (opt.isSet("help")) {
         throw new Options.HelpException(opt.usage());
      } else {
         Map<String, KeyMap<Binding>> keyMaps = reader.getKeyMaps();
         int actions = (opt.isSet("N") ? 1 : 0) + (opt.isSet("d") ? 1 : 0) + (opt.isSet("D") ? 1 : 0) + (opt.isSet("l") ? 1 : 0) + (opt.isSet("r") ? 1 : 0) + (opt.isSet("s") ? 1 : 0) + (opt.isSet("A") ? 1 : 0);
         if (actions > 1) {
            err.println("keymap: incompatible operation selection options");
         } else {
            if (opt.isSet("l")) {
               boolean commands = opt.isSet("L");
               if (opt.args().size() > 0) {
                  for(String arg : opt.args()) {
                     KeyMap<Binding> map = (KeyMap)keyMaps.get(arg);
                     if (map == null) {
                        err.println("keymap: no such keymap: `" + arg + "'");
                     } else {
                        out.println(arg);
                     }
                  }
               } else {
                  Set var10000 = keyMaps.keySet();
                  Objects.requireNonNull(out);
                  var10000.forEach(out::println);
               }
            } else if (opt.isSet("N")) {
               if (opt.isSet("e") || opt.isSet("v") || opt.isSet("a") || opt.isSet("M")) {
                  err.println("keymap: keymap can not be selected with -N");
                  return;
               }

               if (opt.args().size() < 1) {
                  err.println("keymap: not enough arguments for -N");
                  return;
               }

               if (opt.args().size() > 2) {
                  err.println("keymap: too many arguments for -N");
                  return;
               }

               KeyMap<Binding> org = null;
               if (opt.args().size() == 2) {
                  org = (KeyMap)keyMaps.get(opt.args().get(1));
                  if (org == null) {
                     err.println("keymap: no such keymap `" + (String)opt.args().get(1) + "'");
                     return;
                  }
               }

               KeyMap<Binding> map = new KeyMap();
               if (org != null) {
                  for(Map.Entry bound : org.getBoundKeys().entrySet()) {
                     map.bind((Binding)bound.getValue(), (CharSequence)((CharSequence)bound.getKey()));
                  }
               }

               keyMaps.put((String)opt.args().get(0), map);
            } else if (opt.isSet("A")) {
               if (opt.isSet("e") || opt.isSet("v") || opt.isSet("a") || opt.isSet("M")) {
                  err.println("keymap: keymap can not be selected with -N");
                  return;
               }

               if (opt.args().size() < 2) {
                  err.println("keymap: not enough arguments for -A");
                  return;
               }

               if (opt.args().size() > 2) {
                  err.println("keymap: too many arguments for -A");
                  return;
               }

               KeyMap<Binding> org = (KeyMap)keyMaps.get(opt.args().get(0));
               if (org == null) {
                  err.println("keymap: no such keymap `" + (String)opt.args().get(0) + "'");
                  return;
               }

               keyMaps.put((String)opt.args().get(1), org);
            } else if (opt.isSet("d")) {
               if (opt.isSet("e") || opt.isSet("v") || opt.isSet("a") || opt.isSet("M")) {
                  err.println("keymap: keymap can not be selected with -N");
                  return;
               }

               if (opt.args().size() > 0) {
                  err.println("keymap: too many arguments for -d");
                  return;
               }

               keyMaps.clear();
               keyMaps.putAll(reader.defaultKeyMaps());
            } else if (opt.isSet("D")) {
               if (opt.isSet("e") || opt.isSet("v") || opt.isSet("a") || opt.isSet("M")) {
                  err.println("keymap: keymap can not be selected with -N");
                  return;
               }

               if (opt.args().size() < 1) {
                  err.println("keymap: not enough arguments for -A");
                  return;
               }

               for(String name : opt.args()) {
                  if (keyMaps.remove(name) == null) {
                     err.println("keymap: no such keymap `" + name + "'");
                     return;
                  }
               }
            } else if (opt.isSet("r")) {
               String keyMapName = "main";
               int sel = (opt.isSet("a") ? 1 : 0) + (opt.isSet("e") ? 1 : 0) + (opt.isSet("v") ? 1 : 0) + (opt.isSet("M") ? 1 : 0);
               if (sel > 1) {
                  err.println("keymap: incompatible keymap selection options");
                  return;
               }

               if (opt.isSet("a")) {
                  keyMapName = "vicmd";
               } else if (opt.isSet("e")) {
                  keyMapName = "emacs";
               } else if (opt.isSet("v")) {
                  keyMapName = "viins";
               } else if (opt.isSet("M")) {
                  if (opt.args().isEmpty()) {
                     err.println("keymap: argument expected: -M");
                     return;
                  }

                  keyMapName = (String)opt.args().remove(0);
               }

               KeyMap<Binding> map = (KeyMap)keyMaps.get(keyMapName);
               if (map == null) {
                  err.println("keymap: no such keymap `" + keyMapName + "'");
                  return;
               }

               boolean range = opt.isSet("R");
               boolean prefix = opt.isSet("p");
               Set<String> toRemove = new HashSet();
               Map<String, Binding> bound = map.getBoundKeys();

               for(String arg : opt.args()) {
                  if (range) {
                     Collection<String> r = KeyMap.range((String)opt.args().get(0));
                     if (r == null) {
                        err.println("keymap: malformed key range `" + (String)opt.args().get(0) + "'");
                        return;
                     }

                     toRemove.addAll(r);
                  } else {
                     String seq = KeyMap.translate(arg);

                     for(String k : bound.keySet()) {
                        if (prefix && k.startsWith(seq) && k.length() > seq.length() || !prefix && k.equals(seq)) {
                           toRemove.add(k);
                        }
                     }
                  }
               }

               for(String seq : toRemove) {
                  map.unbind((CharSequence)seq);
               }

               if (opt.isSet("e") || opt.isSet("v")) {
                  keyMaps.put("main", map);
               }
            } else if (!opt.isSet("s") && opt.args().size() <= 1) {
               String keyMapName = "main";
               int sel = (opt.isSet("a") ? 1 : 0) + (opt.isSet("e") ? 1 : 0) + (opt.isSet("v") ? 1 : 0) + (opt.isSet("M") ? 1 : 0);
               if (sel > 1) {
                  err.println("keymap: incompatible keymap selection options");
                  return;
               }

               if (opt.isSet("a")) {
                  keyMapName = "vicmd";
               } else if (opt.isSet("e")) {
                  keyMapName = "emacs";
               } else if (opt.isSet("v")) {
                  keyMapName = "viins";
               } else if (opt.isSet("M")) {
                  if (opt.args().isEmpty()) {
                     err.println("keymap: argument expected: -M");
                     return;
                  }

                  keyMapName = (String)opt.args().remove(0);
               }

               KeyMap<Binding> map = (KeyMap)keyMaps.get(keyMapName);
               if (map == null) {
                  err.println("keymap: no such keymap `" + keyMapName + "'");
                  return;
               }

               boolean prefix = opt.isSet("p");
               boolean commands = opt.isSet("L");
               if (prefix && opt.args().isEmpty()) {
                  err.println("keymap: option -p requires a prefix string");
                  return;
               }

               if (opt.args().size() > 0 || !opt.isSet("e") && !opt.isSet("v")) {
                  Map<String, Binding> bound = map.getBoundKeys();
                  String seq = opt.args().size() > 0 ? KeyMap.translate((String)opt.args().get(0)) : null;
                  Map.Entry<String, Binding> begin = null;
                  String last = null;
                  Iterator<Map.Entry<String, Binding>> iterator = bound.entrySet().iterator();

                  while(iterator.hasNext()) {
                     Map.Entry<String, Binding> entry = (Map.Entry)iterator.next();
                     String key = (String)entry.getKey();
                     if (seq == null || prefix && key.startsWith(seq) && !key.equals(seq) || !prefix && key.equals(seq)) {
                        if (begin == null && iterator.hasNext()) {
                           begin = entry;
                           last = key;
                        } else {
                           String n = (last.length() > 1 ? last.substring(0, last.length() - 1) : "") + (char)(last.charAt(last.length() - 1) + 1);
                           if (key.equals(n) && ((Binding)entry.getValue()).equals(begin.getValue())) {
                              last = key;
                           } else {
                              StringBuilder sb = new StringBuilder();
                              if (commands) {
                                 sb.append("keymap -M ");
                                 sb.append(keyMapName);
                                 sb.append(" ");
                              }

                              if (((String)begin.getKey()).equals(last)) {
                                 sb.append(KeyMap.display(last));
                                 sb.append(" ");
                                 displayValue(sb, begin.getValue());
                                 out.println(sb);
                              } else {
                                 if (commands) {
                                    sb.append("-R ");
                                 }

                                 sb.append(KeyMap.display((String)begin.getKey()));
                                 sb.append("-");
                                 sb.append(KeyMap.display(last));
                                 sb.append(" ");
                                 displayValue(sb, begin.getValue());
                                 out.println(sb);
                              }

                              begin = entry;
                              last = key;
                           }
                        }
                     }
                  }
               }

               if (opt.isSet("e") || opt.isSet("v")) {
                  keyMaps.put("main", map);
               }
            } else {
               String keyMapName = "main";
               int sel = (opt.isSet("a") ? 1 : 0) + (opt.isSet("e") ? 1 : 0) + (opt.isSet("v") ? 1 : 0) + (opt.isSet("M") ? 1 : 0);
               if (sel > 1) {
                  err.println("keymap: incompatible keymap selection options");
                  return;
               }

               if (opt.isSet("a")) {
                  keyMapName = "vicmd";
               } else if (opt.isSet("e")) {
                  keyMapName = "emacs";
               } else if (opt.isSet("v")) {
                  keyMapName = "viins";
               } else if (opt.isSet("M")) {
                  if (opt.args().isEmpty()) {
                     err.println("keymap: argument expected: -M");
                     return;
                  }

                  keyMapName = (String)opt.args().remove(0);
               }

               KeyMap<Binding> map = (KeyMap)keyMaps.get(keyMapName);
               if (map == null) {
                  err.println("keymap: no such keymap `" + keyMapName + "'");
                  return;
               }

               boolean range = opt.isSet("R");
               if (opt.args().size() % 2 == 1) {
                  err.println("keymap: even number of arguments required");
                  return;
               }

               for(int i = 0; i < opt.args().size(); i += 2) {
                  Binding bout = (Binding)(opt.isSet("s") ? new Macro(KeyMap.translate((String)opt.args().get(i + 1))) : new Reference((String)opt.args().get(i + 1)));
                  if (range) {
                     Collection<String> r = KeyMap.range((String)opt.args().get(i));
                     if (r == null) {
                        err.println("keymap: malformed key range `" + (String)opt.args().get(i) + "'");
                        return;
                     }

                     map.bind(bout, (Iterable)r);
                  } else {
                     String in = KeyMap.translate((String)opt.args().get(i));
                     map.bind(bout, (CharSequence)in);
                  }
               }

               if (opt.isSet("e") || opt.isSet("v")) {
                  keyMaps.put("main", map);
               }
            }

         }
      }
   }

   public static void setopt(LineReader reader, PrintStream out, PrintStream err, String[] argv) throws Options.HelpException {
      String[] usage = new String[]{"setopt -  set options", "Usage: setopt [-m] option ...", "       setopt", "  -? --help                       Displays command help", "  -m                              Use pattern matching"};
      Options opt = Options.compile(usage).parse((Object[])argv);
      if (opt.isSet("help")) {
         throw new Options.HelpException(opt.usage());
      } else {
         if (opt.args().isEmpty()) {
            for(LineReader.Option option : LineReader.Option.values()) {
               if (reader.isSet(option) != option.isDef()) {
                  out.println((option.isDef() ? "no-" : "") + option.toString().toLowerCase().replace('_', '-'));
               }
            }
         } else {
            boolean match = opt.isSet("m");
            doSetOpts(reader, out, err, opt.args(), match, true);
         }

      }
   }

   public static void unsetopt(LineReader reader, PrintStream out, PrintStream err, String[] argv) throws Options.HelpException {
      String[] usage = new String[]{"unsetopt -  unset options", "Usage: unsetopt [-m] option ...", "       unsetopt", "  -? --help                       Displays command help", "  -m                              Use pattern matching"};
      Options opt = Options.compile(usage).parse((Object[])argv);
      if (opt.isSet("help")) {
         throw new Options.HelpException(opt.usage());
      } else {
         if (opt.args().isEmpty()) {
            for(LineReader.Option option : LineReader.Option.values()) {
               if (reader.isSet(option) == option.isDef()) {
                  out.println((option.isDef() ? "no-" : "") + option.toString().toLowerCase().replace('_', '-'));
               }
            }
         } else {
            boolean match = opt.isSet("m");
            doSetOpts(reader, out, err, opt.args(), match, false);
         }

      }
   }

   private static void doSetOpts(LineReader reader, PrintStream out, PrintStream err, List options, boolean match, boolean set) {
      for(String name : options) {
         String tname = name.toLowerCase().replaceAll("[-_]", "");
         if (match) {
            tname = tname.replaceAll("\\*", "[a-z]*");
            tname = tname.replaceAll("\\?", "[a-z]");
         }

         boolean found = false;
         LineReader.Option[] var10 = LineReader.Option.values();
         int var11 = var10.length;
         int var12 = 0;

         label66: {
            LineReader.Option option;
            while(true) {
               if (var12 >= var11) {
                  break label66;
               }

               label68: {
                  label55: {
                     option = var10[var12];
                     String optName = option.name().toLowerCase().replaceAll("[-_]", "");
                     if (match) {
                        if (optName.matches(tname)) {
                           break label55;
                        }
                     } else if (optName.equals(tname)) {
                        break label55;
                     }

                     if (match) {
                        if (("no" + optName).matches(tname)) {
                           break;
                        }
                     } else if (("no" + optName).equals(tname)) {
                        break;
                     }
                     break label68;
                  }

                  if (set) {
                     reader.setOpt(option);
                  } else {
                     reader.unsetOpt(option);
                  }

                  found = true;
                  if (!match) {
                     break label66;
                  }
               }

               ++var12;
            }

            if (set) {
               reader.unsetOpt(option);
            } else {
               reader.setOpt(option);
            }

            if (!match) {
               found = true;
            }
         }

         if (!found) {
            err.println("No matching option: " + name);
         }
      }

   }

   private static void displayValue(StringBuilder sb, Object value) {
      if (value == null) {
         sb.append("undefined-key");
      } else if (value instanceof Macro) {
         sb.append(KeyMap.display(((Macro)value).getSequence()));
      } else if (value instanceof Reference) {
         sb.append(((Reference)value).name());
      } else {
         sb.append(value);
      }

   }

   public static void setvar(LineReader lineReader, PrintStream out, PrintStream err, String[] argv) throws Options.HelpException {
      String[] usage = new String[]{"setvar -  set lineReader variable value", "Usage: setvar [variable] [value]", "  -? --help                    Show help"};
      Options opt = Options.compile(usage).parse((Object[])argv);
      if (opt.isSet("help")) {
         throw new Options.HelpException(opt.usage());
      } else {
         if (opt.args().isEmpty()) {
            for(Map.Entry entry : lineReader.getVariables().entrySet()) {
               out.println((String)entry.getKey() + ": " + entry.getValue());
            }
         } else if (opt.args().size() == 1) {
            out.println(lineReader.getVariable((String)opt.args().get(0)));
         } else {
            lineReader.setVariable((String)opt.args().get(0), opt.args().get(1));
         }

      }
   }

   public static void colors(Terminal terminal, PrintStream out, String[] argv) throws Options.HelpException, IOException {
      String[] usage = new String[]{"colors -  view 256-color table and ANSI-styles", "Usage: colors [OPTIONS]", "  -? --help                     Displays command help", "  -a --ansistyles               List ANSI-styles", "  -c --columns=COLUMNS          Number of columns in name/rgb table", "                                COLUMNS = 1, display columns: color, style, ansi and HSL", "  -f --find=NAME                Find color names which contains NAME ", "  -l --lock=STYLE               Lock fore- or background color", "  -n --name                     Color name table (default number table)", "  -r --rgb                      Use and display rgb value", "  -s --small                    View 16-color table (default 256-color)", "  -v --view=COLOR               View 24bit color table of COLOR ", "                                COLOR = <colorName>, <color24bit> or hue<angle>"};
      Options opt = Options.compile(usage).parse((Object[])argv);
      if (opt.isSet("help")) {
         throw new Options.HelpException(opt.usage());
      } else {
         Colors colors = new Colors(terminal, out);
         if (opt.isSet("ansistyles")) {
            colors.printStyles();
         } else {
            String style = null;
            if (opt.isSet("lock")) {
               style = opt.get("lock");
               if (style.length() - style.replace(":", "").length() > 1) {
                  style = null;
               }
            }

            if (!opt.isSet("view")) {
               boolean rgb = opt.isSet("rgb");
               int columns = terminal.getWidth() > (rgb ? 71 : 122) ? 6 : 5;
               String findName = null;
               boolean nameTable = opt.isSet("name");
               boolean table16 = opt.isSet("small");
               if (opt.isSet("find")) {
                  findName = opt.get("find").toLowerCase();
                  nameTable = true;
                  table16 = false;
                  columns = 4;
               }

               if (table16) {
                  columns += 2;
               }

               if (opt.isSet("columns")) {
                  columns = opt.getNumber("columns");
               }

               colors.printColors(nameTable, rgb, table16, columns, findName, style);
            } else {
               colors.printColor(opt.get("view").toLowerCase(), style);
            }
         }

      }
   }

   public static void highlighter(LineReader lineReader, Terminal terminal, PrintStream out, PrintStream err, String[] argv, ConfigurationPath configPath) throws Options.HelpException {
      String[] usage = new String[]{"highlighter -  manage nanorc theme system", "Usage: highlighter [OPTIONS]", "  -? --help                       Displays command help", "  -c --columns=COLUMNS            Number of columns in theme view", "  -l --list                       List available nanorc themes", "  -r --refresh                    Refresh highlighter config", "  -s --switch=THEME               Switch nanorc theme", "  -v --view=THEME                 View nanorc theme"};
      Options opt = Options.compile(usage).parse((Object[])argv);
      if (opt.isSet("help")) {
         throw new Options.HelpException(opt.usage());
      } else {
         try {
            if (opt.isSet("refresh")) {
               lineReader.getHighlighter().refresh(lineReader);
            } else if (opt.isSet("switch")) {
               Path userConfig = configPath.getUserConfig("jnanorc");
               if (userConfig != null) {
                  SyntaxHighlighter sh = SyntaxHighlighter.build(userConfig, (String)null);
                  Path currentTheme = sh.getCurrentTheme();
                  String newTheme = replaceFileName(currentTheme, opt.get("switch"));
                  File themeFile = new File(newTheme);
                  if (themeFile.exists()) {
                     switchTheme(err, userConfig, newTheme);
                     Path lessConfig = configPath.getUserConfig("jlessrc");
                     if (lessConfig != null) {
                        switchTheme(err, lessConfig, newTheme);
                     }

                     lineReader.getHighlighter().refresh(lineReader);
                  }
               }
            } else {
               Path config = configPath.getConfig("jnanorc");
               Path currentTheme = config != null ? SyntaxHighlighter.build(config, (String)null).getCurrentTheme() : null;
               if (currentTheme != null) {
                  if (opt.isSet("list")) {
                     String parameter = replaceFileName(currentTheme, "*.nanorctheme");
                     out.println(currentTheme.getParent() + ":");
                     PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + parameter);
                     Stream<Path> pathStream = Files.walk(Paths.get((new File(parameter)).getParent()));

                     try {
                        Objects.requireNonNull(pathMatcher);
                        pathStream.filter(pathMatcher::matches).forEach((p) -> out.println(p.getFileName()));
                     } catch (Throwable var24) {
                        if (pathStream != null) {
                           try {
                              pathStream.close();
                           } catch (Throwable var23) {
                              var24.addSuppressed(var23);
                           }
                        }

                        throw var24;
                     }

                     if (pathStream != null) {
                        pathStream.close();
                     }
                  } else {
                     File themeFile;
                     if (opt.isSet("view")) {
                        themeFile = new File(replaceFileName(currentTheme, opt.get("view")));
                     } else {
                        themeFile = currentTheme.toFile();
                     }

                     out.println(themeFile.getAbsolutePath());
                     BufferedReader reader = new BufferedReader(new FileReader(themeFile));

                     try {
                        List<List<String>> tokens = new ArrayList();
                        int maxKeyLen = 0;
                        int maxValueLen = 0;

                        String line;
                        while((line = reader.readLine()) != null) {
                           line = line.trim();
                           if (line.length() > 0 && !line.startsWith("#")) {
                              List<String> parts = Arrays.asList(line.split("\\s+", 2));
                              if (((String)parts.get(0)).matches("[A-Z_]+")) {
                                 if (((String)parts.get(0)).length() > maxKeyLen) {
                                    maxKeyLen = ((String)parts.get(0)).length();
                                 }

                                 if (((String)parts.get(1)).length() > maxValueLen) {
                                    maxValueLen = ((String)parts.get(1)).length();
                                 }

                                 tokens.add(parts);
                              }
                           }
                        }

                        AttributedStringBuilder asb = new AttributedStringBuilder();
                        maxKeyLen += 2;
                        ++maxValueLen;
                        int cols = opt.isSet("columns") ? opt.getNumber("columns") : 2;
                        List<Integer> tabstops = new ArrayList();

                        for(int c = 0; c < cols; ++c) {
                           tabstops.add((c + 1) * maxKeyLen + c * maxValueLen);
                           tabstops.add((c + 1) * maxKeyLen + (c + 1) * maxValueLen);
                        }

                        asb.tabs(tabstops);
                        int ind = 0;

                        for(List token : tokens) {
                           asb.style(AttributedStyle.DEFAULT).append((CharSequence)" ");
                           asb.style(compileStyle("token" + ind++, (String)token.get(1)));
                           asb.append((CharSequence)token.get(0)).append((CharSequence)"\t");
                           asb.append((CharSequence)token.get(1));
                           asb.style(AttributedStyle.DEFAULT).append((CharSequence)"\t");
                           if (ind % cols == 0) {
                              asb.style(AttributedStyle.DEFAULT).append((CharSequence)"\n");
                           }
                        }

                        asb.toAttributedString().println(terminal);
                     } catch (Throwable var25) {
                        try {
                           reader.close();
                        } catch (Throwable var22) {
                           var25.addSuppressed(var22);
                        }

                        throw var25;
                     }

                     reader.close();
                  }
               }
            }
         } catch (Exception e) {
            err.println(e.getMessage());
         }

      }
   }

   private static void switchTheme(PrintStream err, Path config, String theme) {
      try {
         Stream<String> stream = Files.lines(config, StandardCharsets.UTF_8);

         try {
            List<String> list = (List)stream.map((line) -> line.matches("\\s*theme\\s+.*") ? "theme " + theme : line).collect(Collectors.toList());
            Files.write(config, list, StandardCharsets.UTF_8);
         } catch (Throwable var7) {
            if (stream != null) {
               try {
                  stream.close();
               } catch (Throwable var6) {
                  var7.addSuppressed(var6);
               }
            }

            throw var7;
         }

         if (stream != null) {
            stream.close();
         }
      } catch (IOException e) {
         err.println(e.getMessage());
      }

   }

   private static String replaceFileName(Path path, String name) {
      int nameLength = path.getFileName().toString().length();
      int pathLength = path.toString().length();
      return (path.toString().substring(0, pathLength - nameLength) + name).replace("\\", "\\\\");
   }

   private static AttributedStyle compileStyle(String reference, String colorDef) {
      Map<String, String> spec = new HashMap();
      spec.put(reference, colorDef);
      Styles.StyleCompiler sh = new Styles.StyleCompiler(spec, true);
      Objects.requireNonNull(sh);
      return (new StyleResolver(sh::getStyle)).resolve("." + reference);
   }

   private static class ReExecute {
      private final boolean execute;
      private final boolean edit;
      private String oldParam;
      private String newParam;
      private FileWriter cmdWriter;
      private File cmdFile;
      private int argId = 0;

      public ReExecute(History history, Options opt) throws IOException {
         this.execute = opt.isSet("e") || opt.isSet("s");
         this.edit = opt.isSet("e");
         if (this.execute) {
            Iterator<History.Entry> iter = history.reverseIterator(history.last());
            if (iter.hasNext()) {
               iter.next();
               iter.remove();
            }

            if (this.edit) {
               this.cmdFile = File.createTempFile("jline-history-", (String)null);
               this.cmdWriter = new FileWriter(this.cmdFile);
            } else if (opt.args().size() > 0) {
               String[] s = ((String)opt.args().get(this.argId)).split("=");
               if (s.length == 2) {
                  ++this.argId;
                  this.oldParam = s[0];
                  this.newParam = s[1];
               }
            }
         }

      }

      public int getArgId() {
         return this.argId;
      }

      public boolean isEdit() {
         return this.edit;
      }

      public boolean isExecute() {
         return this.execute;
      }

      public void addCommandInFile(String command) throws IOException {
         this.cmdWriter.write(command + "\n");
      }

      public void addCommandInBuffer(LineReader reader, String command) {
         reader.addCommandsInBuffer(Arrays.asList(this.replaceParam(command)));
      }

      private String replaceParam(String command) {
         String out = command;
         if (this.oldParam != null && this.newParam != null) {
            out = command.replaceAll(this.oldParam, this.newParam);
         }

         return out;
      }

      public void editCommandsAndClose(LineReader reader) throws Exception {
         if (this.edit) {
            this.cmdWriter.close();

            try {
               reader.editAndAddInBuffer(this.cmdFile);
            } finally {
               this.cmdFile.delete();
            }
         }

      }
   }

   private static class Colors {
      private static final String COLORS_24BIT = "[0-9a-fA-F]{6}";
      private static final List COLORS_16 = Arrays.asList("black", "red", "green", "yellow", "blue", "magenta", "cyan", "white", "!black", "!red", "!green", "!yellow", "!blue", "!magenta", "!cyan", "!white");
      boolean name;
      boolean rgb;
      private final Terminal terminal;
      private final PrintStream out;
      private boolean fixedBg;
      private String fixedStyle;
      int r;
      int g;
      int b;

      public Colors(Terminal terminal, PrintStream out) {
         this.terminal = terminal;
         this.out = out;
      }

      private String getAnsiStyle(String style) {
         return style;
      }

      public void printStyles() {
         AttributedStringBuilder asb = new AttributedStringBuilder();
         asb.tabs(13);

         for(String s : Styles.ANSI_STYLES) {
            AttributedStyle as = (new StyleResolver(this::getAnsiStyle)).resolve("." + s);
            asb.style(as);
            asb.append((CharSequence)s);
            asb.style(AttributedStyle.DEFAULT);
            asb.append((CharSequence)"\t");
            asb.append((CharSequence)this.getAnsiStyle(s));
            asb.append((CharSequence)"\t");
            asb.append((CharSequence)as.toAnsi());
            asb.append((CharSequence)"\n");
         }

         asb.toAttributedString().println(this.terminal);
      }

      private String getStyle(String color) {
         char fg = ' ';
         String out;
         if (this.name) {
            out = (this.fixedBg ? "fg:" : "bg:") + "~" + color.substring(1);
            fg = color.charAt(0);
         } else if (this.rgb) {
            out = (this.fixedBg ? "fg-rgb:" : "bg-rgb:") + "#" + color.substring(1);
            fg = color.charAt(0);
         } else if (color.substring(1).matches("\\d+")) {
            out = (this.fixedBg ? "38;5;" : "48;5;") + color.substring(1);
            fg = color.charAt(0);
         } else {
            out = (this.fixedBg ? "fg:" : "bg:") + color;
         }

         if (this.fixedStyle == null) {
            if (!color.startsWith("!") && !color.equals("white") && fg != 'b') {
               out = out + ",fg:!white";
            } else {
               out = out + ",fg:black";
            }
         } else {
            out = out + "," + this.fixedStyle;
         }

         return out;
      }

      private String foreground(int idx) {
         String fg = "w";
         if (idx > 6 && idx < 16 || idx > 33 && idx < 52 || idx > 69 && idx < 88 || idx > 105 && idx < 124 || idx > 141 && idx < 160 || idx > 177 && idx < 196 || idx > 213 && idx < 232 || idx > 243) {
            fg = "b";
         }

         return fg;
      }

      private String addPadding(int width, String field) {
         int s = width - field.length();
         int left = s / 2;
         StringBuilder lp = new StringBuilder();
         StringBuilder rp = new StringBuilder();

         for(int i = 0; i < left; ++i) {
            lp.append(" ");
         }

         for(int i = 0; i < s - left; ++i) {
            rp.append(" ");
         }

         return lp + field + rp;
      }

      private String addLeftPadding(int width, String field) {
         int s = width - field.length();
         StringBuilder lp = new StringBuilder();

         for(int i = 0; i < s; ++i) {
            lp.append(" ");
         }

         return lp + field;
      }

      private void setFixedStyle(String style) {
         this.fixedStyle = style;
         if (style != null && (style.contains("b:") || style.contains("b-") || style.contains("bg:") || style.contains("bg-") || style.contains("background"))) {
            this.fixedBg = true;
         }

      }

      private List retrieveColorNames() throws IOException {
         InputStream is = (new Source.ResourceSource("/org/jline/utils/colors.txt", (String)null)).read();

         List<String> out;
         try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            try {
               out = (List)br.lines().map(String::trim).filter((s) -> !s.startsWith("#")).filter((s) -> !s.isEmpty()).collect(Collectors.toList());
            } catch (Throwable var8) {
               try {
                  br.close();
               } catch (Throwable var7) {
                  var8.addSuppressed(var7);
               }

               throw var8;
            }

            br.close();
         } catch (Throwable var9) {
            if (is != null) {
               try {
                  is.close();
               } catch (Throwable var6) {
                  var9.addSuppressed(var6);
               }
            }

            throw var9;
         }

         if (is != null) {
            is.close();
         }

         return out;
      }

      public void printColors(boolean name, boolean rgb, boolean small, int columns, String findName, String style) throws IOException {
         this.name = !rgb && name;
         this.rgb = rgb;
         this.setFixedStyle(style);
         AttributedStringBuilder asb = new AttributedStringBuilder();
         int width = this.terminal.getWidth();
         String tableName = small ? " 16-color " : "256-color ";
         if (!name && !rgb) {
            this.out.print(tableName);
            this.out.print("table, fg:<name> ");
            if (!small) {
               this.out.print("/ 38;5;<n>");
            }

            this.out.println();
            this.out.print("                 bg:<name> ");
            if (!small) {
               this.out.print("/ 48;5;<n>");
            }

            this.out.println("\n");
            boolean narrow = width < 180;

            for(String c : COLORS_16) {
               AttributedStyle ss = (new StyleResolver(this::getStyle)).resolve('.' + c, (String)null);
               asb.style(ss);
               asb.append((CharSequence)this.addPadding(11, c));
               asb.style(AttributedStyle.DEFAULT);
               if (c.equals("white")) {
                  if (!narrow && !small) {
                     asb.append((CharSequence)"    ");
                  } else {
                     asb.append('\n');
                  }
               } else if (c.equals("!white")) {
                  asb.append('\n');
               }
            }

            asb.append('\n');
            if (!small) {
               for(int i = 16; i < 256; ++i) {
                  String fg = this.foreground(i);
                  String code = Integer.toString(i);
                  AttributedStyle ss = (new StyleResolver(this::getStyle)).resolve("." + fg + code, (String)null);
                  asb.style(ss);
                  String str = " ";
                  if (i < 100) {
                     str = "  ";
                  } else if (i > 231) {
                     str = i % 2 == 0 ? "    " : "   ";
                  }

                  asb.append((CharSequence)str).append((CharSequence)code).append(' ');
                  if (i == 51 || i == 87 || i == 123 || i == 159 || i == 195 || i == 231 || narrow && (i == 33 || i == 69 || i == 105 || i == 141 || i == 177 || i == 213 || i == 243)) {
                     asb.style(AttributedStyle.DEFAULT);
                     asb.append('\n');
                     if (i == 231) {
                        asb.append('\n');
                     }
                  }
               }
            }
         } else {
            this.out.print(tableName);
            if (name) {
               asb.tabs(Arrays.asList(25, 60, 75));
               this.out.println("table, fg:~<name> OR 38;5;<n>");
               this.out.println("                 bg:~<name> OR 48;5;<n>");
            } else {
               asb.tabs(Arrays.asList(15, 45, 70));
               this.out.println("table, fg-rgb:<color24bit> OR 38;5;<n>");
               this.out.println("                 bg-rgb:<color24bit> OR 48;5;<n>");
            }

            this.out.println();
            int col = 0;
            int idx = 0;
            int colWidth = rgb ? 12 : 21;
            int lb = 1;
            if (findName != null && (findName.startsWith("#") || findName.startsWith("x"))) {
               findName = findName.substring(1);
            }

            for(String line : this.retrieveColorNames()) {
               if (!rgb) {
                  if (findName != null) {
                     if (!line.toLowerCase().contains(findName)) {
                        ++idx;
                        continue;
                     }
                  } else if (small) {
                     colWidth = 15;
                     lb = 1;
                  } else if (columns > 4) {
                     if (idx > 15 && idx < 232) {
                        colWidth = columns == 6 && col != 1 && col != 2 && col != 3 ? 20 : 21;
                        lb = 1;
                     } else {
                        colWidth = columns == 6 && idx % 2 != 0 && col != 7 ? 16 : 15;
                        lb = -1;
                     }
                  }
               }

               String fg = this.foreground(idx);
               if (rgb) {
                  line = Integer.toHexString(org.jline.utils.Colors.DEFAULT_COLORS_256[idx]);

                  for(int p = line.length(); p < 6; ++p) {
                     line = "0" + line;
                  }

                  if (findName != null && !line.toLowerCase().matches(findName)) {
                     ++idx;
                     continue;
                  }
               }

               AttributedStyle ss = (new StyleResolver(this::getStyle)).resolve("." + fg + line, (String)null);
               if (rgb) {
                  line = "#" + line;
               }

               asb.style(ss);
               String idxstr = Integer.toString(idx);
               if (rgb) {
                  if (idx < 10) {
                     idxstr = "  " + idxstr;
                  } else if (idx < 100) {
                     idxstr = " " + idxstr;
                  }
               }

               asb.append((CharSequence)idxstr).append((CharSequence)this.addPadding(colWidth - idxstr.length(), line));
               if (columns == 1) {
                  asb.style(AttributedStyle.DEFAULT);
                  asb.append((CharSequence)"\t").append((CharSequence)this.getStyle(fg + line.substring(rgb ? 1 : 0)));
                  asb.append((CharSequence)"\t").append((CharSequence)ss.toAnsi());
                  int[] rgb1 = this.rgb((long)org.jline.utils.Colors.DEFAULT_COLORS_256[idx]);
                  int[] hsl = this.rgb2hsl(rgb1[0], rgb1[1], rgb1[2]);
                  asb.append((CharSequence)"\t").append((CharSequence)this.addLeftPadding(6, hsl[0] + ", ")).append((CharSequence)this.addLeftPadding(4, hsl[1] + "%")).append((CharSequence)", ").append((CharSequence)this.addLeftPadding(4, hsl[2] + "%"));
               }

               ++col;
               ++idx;
               if ((col + 1) * colWidth > width || col + lb > columns) {
                  col = 0;
                  asb.style(AttributedStyle.DEFAULT);
                  asb.append('\n');
               }

               if (findName == null) {
                  if (idx == 16) {
                     if (small) {
                        break;
                     }

                     if (col != 0) {
                        col = 0;
                        asb.style(AttributedStyle.DEFAULT);
                        asb.append('\n');
                     }
                  } else if (idx == 232 && col != 0) {
                     col = 0;
                     asb.style(AttributedStyle.DEFAULT);
                     asb.append('\n');
                  }
               }
            }
         }

         asb.toAttributedString().println(this.terminal);
      }

      private int[] rgb(long color) {
         int[] rgb = new int[]{0, 0, 0};
         rgb[0] = (int)(color >> 16 & 255L);
         rgb[1] = (int)(color >> 8 & 255L);
         rgb[2] = (int)(color & 255L);
         return rgb;
      }

      private int[] hue2rgb(int degree) {
         int[] rgb = new int[]{0, 0, 0};
         double hue = (double)degree / (double)60.0F;
         double a = Math.tan((double)degree / (double)360.0F * (double)2.0F * Math.PI) / Math.sqrt((double)3.0F);
         if (hue >= (double)0.0F && hue < (double)1.0F) {
            rgb[0] = 255;
            rgb[1] = (int)((double)2.0F * a * (double)255.0F / ((double)1.0F + a));
         } else if (hue >= (double)1.0F && hue < (double)2.0F) {
            rgb[0] = (int)((double)255.0F * ((double)1.0F + a) / ((double)2.0F * a));
            rgb[1] = 255;
         } else if (hue >= (double)2.0F && hue < (double)3.0F) {
            rgb[1] = 255;
            rgb[2] = (int)((double)255.0F * ((double)1.0F + a) / ((double)1.0F - a));
         } else if (hue >= (double)3.0F && hue < (double)4.0F) {
            rgb[1] = (int)((double)255.0F * ((double)1.0F - a) / ((double)1.0F + a));
            rgb[2] = 255;
         } else if (hue >= (double)4.0F && hue <= (double)5.0F) {
            rgb[0] = (int)((double)255.0F * (a - (double)1.0F) / ((double)2.0F * a));
            rgb[2] = 255;
         } else if (hue > (double)5.0F && hue <= (double)6.0F) {
            rgb[0] = 255;
            rgb[2] = (int)((double)510.0F * a / (a - (double)1.0F));
         }

         return rgb;
      }

      private int[] rgb2hsl(int r, int g, int b) {
         int[] hsl = new int[]{0, 0, 0};
         if (r != 0 || g != 0 || b != 0) {
            for(hsl[0] = (int)Math.round((180D / Math.PI) * Math.atan2(Math.sqrt((double)3.0F) * (double)(g - b), (double)(2 * r - g - b))); hsl[0] < 0; hsl[0] += 360) {
            }
         }

         double mx = (double)Math.max(Math.max(r, g), b) / (double)255.0F;
         double mn = (double)Math.min(Math.min(r, g), b) / (double)255.0F;
         double l = (mx + mn) / (double)2.0F;
         hsl[1] = l != (double)0.0F && l != (double)1.0F ? (int)Math.round((double)100.0F * (mx - mn) / ((double)1.0F - Math.abs((double)2.0F * l - (double)1.0F))) : 0;
         hsl[2] = (int)Math.round((double)100.0F * l);
         return hsl;
      }

      String getStyleRGB(String s) {
         if (this.fixedStyle == null) {
            double ry = Math.pow((double)this.r / (double)255.0F, 2.2);
            double by = Math.pow((double)this.b / (double)255.0F, 2.2);
            double gy = Math.pow((double)this.g / (double)255.0F, 2.2);
            double y = 0.2126 * ry + 0.7151 * gy + 0.0721 * by;
            String fg = "black";
            if (1.05 / (y + 0.05) > (y + 0.05) / 0.05) {
               fg = "white";
            }

            return "bg-rgb:" + String.format("#%02x%02x%02x", this.r, this.g, this.b) + ",fg:" + fg;
         } else {
            return (this.fixedBg ? "fg-rgb:" : "bg-rgb:") + String.format("#%02x%02x%02x", this.r, this.g, this.b) + "," + this.fixedStyle;
         }
      }

      public void printColor(String name, String style) throws IOException {
         this.setFixedStyle(style);
         double zoom = (double)1.0F;
         int[] rgb = new int[]{0, 0, 0};
         if (name.matches("[0-9a-fA-F]{6}")) {
            rgb = this.rgb(Long.parseLong(name, 16));
            zoom = (double)2.0F;
         } else if ((name.startsWith("#") || name.startsWith("x")) && name.substring(1).matches("[0-9a-fA-F]{6}")) {
            rgb = this.rgb(Long.parseLong(name.substring(1), 16));
            zoom = (double)2.0F;
         } else if (COLORS_16.contains(name)) {
            for(int i = 0; i < 16; ++i) {
               if (((String)COLORS_16.get(i)).equals(name)) {
                  rgb = this.rgb((long)org.jline.utils.Colors.DEFAULT_COLORS_256[i]);
                  break;
               }
            }
         } else if (name.matches("hue[1-3]?[0-9]{1,2}")) {
            int hueAngle = Integer.parseInt(name.substring(3));
            if (hueAngle > 360) {
               throw new IllegalArgumentException("Color not found: " + name);
            }

            rgb = this.hue2rgb(hueAngle);
         } else {
            if (!name.matches("[a-z0-9]+")) {
               throw new IllegalArgumentException("Color not found: " + name);
            }

            List<String> colors = this.retrieveColorNames();
            if (colors.contains(name)) {
               for(int i = 0; i < 256; ++i) {
                  if (((String)colors.get(i)).equals(name)) {
                     rgb = this.rgb((long)org.jline.utils.Colors.DEFAULT_COLORS_256[i]);
                     break;
                  }
               }
            } else {
               boolean found = false;

               for(int i = 0; i < 256; ++i) {
                  if (((String)colors.get(i)).startsWith(name)) {
                     rgb = this.rgb((long)org.jline.utils.Colors.DEFAULT_COLORS_256[i]);
                     found = true;
                     break;
                  }
               }

               if (!found) {
                  for(int i = 0; i < 256; ++i) {
                     if (((String)colors.get(i)).contains(name)) {
                        rgb = this.rgb((long)org.jline.utils.Colors.DEFAULT_COLORS_256[i]);
                        found = true;
                        break;
                     }
                  }
               }

               if (!found) {
                  throw new IllegalArgumentException("Color not found: " + name);
               }
            }
         }

         double step = (double)32.0F;
         int barSize = 14;
         int width = this.terminal.getWidth();
         if (width > 287) {
            step = (double)8.0F;
            barSize = 58;
         } else if (width > 143) {
            step = (double)16.0F;
            barSize = 29;
         } else if (width > 98) {
            step = (double)24.0F;
            barSize = 18;
         }

         this.r = rgb[0];
         this.g = rgb[1];
         this.b = rgb[2];
         int[] hsl = this.rgb2hsl(this.r, this.g, this.b);
         int hueAngle = hsl[0];
         this.out.println("HSL: " + hsl[0] + "deg, " + hsl[1] + "%, " + hsl[2] + "%");
         if (hsl[2] > 85 || hsl[2] < 15 || hsl[1] < 15) {
            zoom = (double)1.0F;
         }

         double div = zoom * (double)256.0F / step;
         int ndiv = (int)(div / zoom);
         double xrs = (double)(255 - this.r) / div;
         double xgs = (double)(255 - this.g) / div;
         double xbs = (double)(255 - this.b) / div;
         double[] yrs = new double[ndiv];
         double[] ygs = new double[ndiv];
         double[] ybs = new double[ndiv];
         double[] ro = new double[ndiv];
         double[] go = new double[ndiv];
         double[] bo = new double[ndiv];
         AttributedStringBuilder asb = new AttributedStringBuilder();

         for(int y = 0; y < ndiv; ++y) {
            for(int x = 0; x < ndiv; ++x) {
               if (y == 0) {
                  yrs[x] = ((double)rgb[0] + (double)x * xrs) / div;
                  ygs[x] = ((double)rgb[1] + (double)x * xgs) / div;
                  ybs[x] = ((double)rgb[2] + (double)x * xbs) / div;
                  ro[x] = (double)rgb[0] + (double)x * xrs;
                  go[x] = (double)rgb[1] + (double)x * xgs;
                  bo[x] = (double)rgb[2] + (double)x * xbs;
                  this.r = (int)ro[x];
                  this.g = (int)go[x];
                  this.b = (int)bo[x];
               } else {
                  this.r = (int)(ro[x] - (double)y * yrs[x]);
                  this.g = (int)(go[x] - (double)y * ygs[x]);
                  this.b = (int)(bo[x] - (double)y * ybs[x]);
               }

               String col = String.format("%02x%02x%02x", this.r, this.g, this.b);
               AttributedStyle s = (new StyleResolver(this::getStyleRGB)).resolve(".rgb" + col);
               asb.style(s);
               asb.append((CharSequence)" ").append((CharSequence)"#").append((CharSequence)col).append((CharSequence)" ");
            }

            asb.style(AttributedStyle.DEFAULT).append((CharSequence)"\n");
         }

         asb.toAttributedString().println(this.terminal);
         if (hueAngle != -1) {
            int dAngle = 5;
            int zero = (int)((double)hueAngle - (double)dAngle / (double)2.0F * (double)(barSize - 1));
            zero -= zero % 5;
            AttributedStringBuilder asb2 = new AttributedStringBuilder();

            for(int i = 0; i < barSize; ++i) {
               int angle;
               for(angle = zero + dAngle * i; angle < 0; angle += 360) {
               }

               while(angle > 360) {
                  angle -= 360;
               }

               rgb = this.hue2rgb(angle);
               this.r = rgb[0];
               this.g = rgb[1];
               this.b = rgb[2];
               AttributedStyle s = (new StyleResolver(this::getStyleRGB)).resolve(".hue" + angle);
               asb2.style(s);
               asb2.append((CharSequence)" ").append((CharSequence)this.addPadding(3, "" + angle)).append((CharSequence)" ");
            }

            asb2.style(AttributedStyle.DEFAULT).append((CharSequence)"\n");
            asb2.toAttributedString().println(this.terminal);
         }

      }
   }
}
