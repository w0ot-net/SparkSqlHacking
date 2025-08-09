package org.jline.builtins;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Stream;
import org.jline.keymap.BindingReader;
import org.jline.keymap.KeyMap;
import org.jline.terminal.Attributes;
import org.jline.terminal.Size;
import org.jline.terminal.Terminal;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;
import org.jline.utils.Display;
import org.jline.utils.InfoCmp;
import org.jline.utils.Status;

public class Less {
   private static final int ESCAPE = 27;
   private static final String MESSAGE_FILE_INFO = "FILE_INFO";
   public boolean quitAtSecondEof;
   public boolean quitAtFirstEof;
   public boolean quitIfOneScreen;
   public boolean printLineNumbers;
   public boolean quiet;
   public boolean veryQuiet;
   public boolean chopLongLines;
   public boolean ignoreCaseCond;
   public boolean ignoreCaseAlways;
   public boolean noKeypad;
   public boolean noInit;
   protected List tabs;
   protected String syntaxName;
   private String historyLog;
   protected final Terminal terminal;
   protected final Display display;
   protected final BindingReader bindingReader;
   protected final Path currentDir;
   protected List sources;
   protected int sourceIdx;
   protected BufferedReader reader;
   protected KeyMap keys;
   protected int firstLineInMemory;
   protected List lines;
   protected int firstLineToDisplay;
   protected int firstColumnToDisplay;
   protected int offsetInLine;
   protected String message;
   protected String errorMessage;
   protected final StringBuilder buffer;
   protected final Map options;
   protected int window;
   protected int halfWindow;
   protected int nbEof;
   protected Nano.PatternHistory patternHistory;
   protected String pattern;
   protected String displayPattern;
   protected final Size size;
   SyntaxHighlighter syntaxHighlighter;
   private final List syntaxFiles;
   private boolean highlight;
   private boolean nanorcIgnoreErrors;

   public static String[] usage() {
      return new String[]{"less -  file pager", "Usage: less [OPTIONS] [FILES]", "  -? --help                    Show help", "  -e --quit-at-eof             Exit on second EOF", "  -E --QUIT-AT-EOF             Exit on EOF", "  -F --quit-if-one-screen      Exit if entire file fits on first screen", "  -q --quiet --silent          Silent mode", "  -Q --QUIET --SILENT          Completely silent", "  -S --chop-long-lines         Do not fold long lines", "  -i --ignore-case             Search ignores lowercase case", "  -I --IGNORE-CASE             Search ignores all case", "  -x --tabs=N[,...]            Set tab stops", "  -N --LINE-NUMBERS            Display line number for each line", "  -Y --syntax=name             The name of the syntax highlighting to use.", "     --no-init                 Disable terminal initialization", "     --no-keypad               Disable keypad handling", "     --ignorercfiles           Don't look at the system's lessrc nor at the user's lessrc.", "  -H --historylog=name         Log search strings to file, so they can be retrieved in later sessions"};
   }

   public Less(Terminal terminal, Path currentDir) {
      this(terminal, currentDir, (Options)null);
   }

   public Less(Terminal terminal, Path currentDir, Options opts) {
      this(terminal, currentDir, opts, (ConfigurationPath)null);
   }

   public Less(Terminal terminal, Path currentDir, Options opts, ConfigurationPath configPath) {
      this.tabs = Collections.singletonList(4);
      this.historyLog = null;
      this.firstLineInMemory = 0;
      this.lines = new ArrayList();
      this.firstLineToDisplay = 0;
      this.firstColumnToDisplay = 0;
      this.offsetInLine = 0;
      this.buffer = new StringBuilder();
      this.options = new TreeMap();
      this.patternHistory = new Nano.PatternHistory((Path)null);
      this.size = new Size();
      this.syntaxFiles = new ArrayList();
      this.highlight = true;
      this.terminal = terminal;
      this.display = new Display(terminal, true);
      this.bindingReader = new BindingReader(terminal.reader());
      this.currentDir = currentDir;
      Path lessrc = configPath != null ? configPath.getConfig("jlessrc") : null;
      boolean ignorercfiles = opts != null && opts.isSet("ignorercfiles");
      if (lessrc != null && !ignorercfiles) {
         try {
            this.parseConfig(lessrc);
         } catch (IOException var13) {
            this.errorMessage = "Encountered error while reading config file: " + lessrc;
         }
      } else if ((new File("/usr/share/nano")).exists() && !ignorercfiles) {
         PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:/usr/share/nano/*.nanorc");

         try {
            Stream<Path> pathStream = Files.walk(Paths.get("/usr/share/nano"));

            try {
               Objects.requireNonNull(pathMatcher);
               Stream var10000 = pathStream.filter(pathMatcher::matches);
               List var10001 = this.syntaxFiles;
               Objects.requireNonNull(var10001);
               var10000.forEach(var10001::add);
               this.nanorcIgnoreErrors = true;
            } catch (Throwable var14) {
               if (pathStream != null) {
                  try {
                     pathStream.close();
                  } catch (Throwable var12) {
                     var14.addSuppressed(var12);
                  }
               }

               throw var14;
            }

            if (pathStream != null) {
               pathStream.close();
            }
         } catch (IOException var15) {
            this.errorMessage = "Encountered error while reading nanorc files";
         }
      }

      if (opts != null) {
         if (opts.isSet("QUIT-AT-EOF")) {
            this.quitAtFirstEof = true;
         }

         if (opts.isSet("quit-at-eof")) {
            this.quitAtSecondEof = true;
         }

         if (opts.isSet("quit-if-one-screen")) {
            this.quitIfOneScreen = true;
         }

         if (opts.isSet("quiet")) {
            this.quiet = true;
         }

         if (opts.isSet("QUIET")) {
            this.veryQuiet = true;
         }

         if (opts.isSet("chop-long-lines")) {
            this.chopLongLines = true;
         }

         if (opts.isSet("IGNORE-CASE")) {
            this.ignoreCaseAlways = true;
         }

         if (opts.isSet("ignore-case")) {
            this.ignoreCaseCond = true;
         }

         if (opts.isSet("LINE-NUMBERS")) {
            this.printLineNumbers = true;
         }

         if (opts.isSet("tabs")) {
            this.doTabs(opts.get("tabs"));
         }

         if (opts.isSet("syntax")) {
            this.syntaxName = opts.get("syntax");
            this.nanorcIgnoreErrors = false;
         }

         if (opts.isSet("no-init")) {
            this.noInit = true;
         }

         if (opts.isSet("no-keypad")) {
            this.noKeypad = true;
         }

         if (opts.isSet("historylog")) {
            this.historyLog = opts.get("historylog");
         }
      }

      if (configPath != null && this.historyLog != null) {
         try {
            this.patternHistory = new Nano.PatternHistory(configPath.getUserConfig(this.historyLog, true));
         } catch (IOException var11) {
            this.errorMessage = "Encountered error while reading pattern-history file: " + this.historyLog;
         }
      }

   }

   private void parseConfig(Path file) throws IOException {
      BufferedReader reader = new BufferedReader(new FileReader(file.toFile()));

      try {
         for(String line = reader.readLine(); line != null; line = reader.readLine()) {
            line = line.trim();
            if (line.length() > 0 && !line.startsWith("#")) {
               List<String> parts = SyntaxHighlighter.RuleSplitter.split(line);
               if (((String)parts.get(0)).equals("include")) {
                  SyntaxHighlighter.nanorcInclude((String)parts.get(1), this.syntaxFiles);
               } else if (((String)parts.get(0)).equals("theme")) {
                  SyntaxHighlighter.nanorcTheme((String)parts.get(1), this.syntaxFiles);
               } else if (parts.size() == 2 && (((String)parts.get(0)).equals("set") || ((String)parts.get(0)).equals("unset"))) {
                  String option = (String)parts.get(1);
                  boolean val = ((String)parts.get(0)).equals("set");
                  if (option.equals("QUIT-AT-EOF")) {
                     this.quitAtFirstEof = val;
                  } else if (option.equals("quit-at-eof")) {
                     this.quitAtSecondEof = val;
                  } else if (option.equals("quit-if-one-screen")) {
                     this.quitIfOneScreen = val;
                  } else if (!option.equals("quiet") && !option.equals("silent")) {
                     if (!option.equals("QUIET") && !option.equals("SILENT")) {
                        if (option.equals("chop-long-lines")) {
                           this.chopLongLines = val;
                        } else if (option.equals("IGNORE-CASE")) {
                           this.ignoreCaseAlways = val;
                        } else if (option.equals("ignore-case")) {
                           this.ignoreCaseCond = val;
                        } else if (option.equals("LINE-NUMBERS")) {
                           this.printLineNumbers = val;
                        } else {
                           this.errorMessage = "Less config: Unknown or unsupported configuration option " + option;
                        }
                     } else {
                        this.veryQuiet = val;
                     }
                  } else {
                     this.quiet = val;
                  }
               } else if (parts.size() == 3 && ((String)parts.get(0)).equals("set")) {
                  String option = (String)parts.get(1);
                  String val = (String)parts.get(2);
                  if (option.equals("tabs")) {
                     this.doTabs(val);
                  } else if (option.equals("historylog")) {
                     this.historyLog = val;
                  } else {
                     this.errorMessage = "Less config: Unknown or unsupported configuration option " + option;
                  }
               } else if (!((String)parts.get(0)).equals("bind") && !((String)parts.get(0)).equals("unbind")) {
                  this.errorMessage = "Less config: Bad configuration '" + line + "'";
               } else {
                  this.errorMessage = "Less config: Key bindings can not be changed!";
               }
            }
         }
      } catch (Throwable var8) {
         try {
            reader.close();
         } catch (Throwable var7) {
            var8.addSuppressed(var7);
         }

         throw var8;
      }

      reader.close();
   }

   private void doTabs(String val) {
      this.tabs = new ArrayList();

      for(String s : val.split(",")) {
         try {
            this.tabs.add(Integer.parseInt(s));
         } catch (Exception var7) {
            this.errorMessage = "Less config: tabs option error parsing number: " + s;
         }
      }

   }

   public Less tabs(List tabs) {
      this.tabs = tabs;
      return this;
   }

   public void handle(Terminal.Signal signal) {
      this.size.copy(this.terminal.getSize());

      try {
         this.display.clear();
         this.display(false);
      } catch (IOException e) {
         e.printStackTrace();
      }

   }

   public void run(Source... sources) throws IOException, InterruptedException {
      this.run((List)(new ArrayList(Arrays.asList(sources))));
   }

   public void run(List sources) throws IOException, InterruptedException {
      if (sources != null && !sources.isEmpty()) {
         sources.add(0, new Source.ResourceSource("less-help.txt", "HELP -- Press SPACE for more, or q when done"));
         this.sources = sources;
         this.sourceIdx = 1;
         this.openSource();
         if (this.errorMessage != null) {
            this.message = this.errorMessage;
            this.errorMessage = null;
         }

         Status status = Status.getStatus(this.terminal, false);

         try {
            if (status != null) {
               status.suspend();
            }

            this.size.copy(this.terminal.getSize());
            if (!this.quitIfOneScreen || sources.size() != 2 || !this.display(true)) {
               Terminal.SignalHandler prevHandler = this.terminal.handle(Terminal.Signal.WINCH, this::handle);
               Attributes attr = this.terminal.enterRawMode();

               try {
                  this.window = this.size.getRows() - 1;
                  this.halfWindow = this.window / 2;
                  this.keys = new KeyMap();
                  this.bindKeys(this.keys);
                  if (!this.noInit) {
                     this.terminal.puts(InfoCmp.Capability.enter_ca_mode);
                  }

                  if (!this.noKeypad) {
                     this.terminal.puts(InfoCmp.Capability.keypad_xmit);
                  }

                  this.terminal.writer().flush();
                  this.display.clear();
                  this.display(false);
                  checkInterrupted();
                  this.options.put("-e", Less.Operation.OPT_QUIT_AT_SECOND_EOF);
                  this.options.put("--quit-at-eof", Less.Operation.OPT_QUIT_AT_SECOND_EOF);
                  this.options.put("-E", Less.Operation.OPT_QUIT_AT_FIRST_EOF);
                  this.options.put("-QUIT-AT-EOF", Less.Operation.OPT_QUIT_AT_FIRST_EOF);
                  this.options.put("-N", Less.Operation.OPT_PRINT_LINES);
                  this.options.put("--LINE-NUMBERS", Less.Operation.OPT_PRINT_LINES);
                  this.options.put("-q", Less.Operation.OPT_QUIET);
                  this.options.put("--quiet", Less.Operation.OPT_QUIET);
                  this.options.put("--silent", Less.Operation.OPT_QUIET);
                  this.options.put("-Q", Less.Operation.OPT_VERY_QUIET);
                  this.options.put("--QUIET", Less.Operation.OPT_VERY_QUIET);
                  this.options.put("--SILENT", Less.Operation.OPT_VERY_QUIET);
                  this.options.put("-S", Less.Operation.OPT_CHOP_LONG_LINES);
                  this.options.put("--chop-long-lines", Less.Operation.OPT_CHOP_LONG_LINES);
                  this.options.put("-i", Less.Operation.OPT_IGNORE_CASE_COND);
                  this.options.put("--ignore-case", Less.Operation.OPT_IGNORE_CASE_COND);
                  this.options.put("-I", Less.Operation.OPT_IGNORE_CASE_ALWAYS);
                  this.options.put("--IGNORE-CASE", Less.Operation.OPT_IGNORE_CASE_ALWAYS);
                  this.options.put("-Y", Less.Operation.OPT_SYNTAX_HIGHLIGHT);
                  this.options.put("--syntax", Less.Operation.OPT_SYNTAX_HIGHLIGHT);
                  boolean forward = true;

                  Operation op;
                  do {
                     checkInterrupted();
                     op = null;
                     if (this.buffer.length() > 0 && this.buffer.charAt(0) == '-') {
                        int c = this.terminal.reader().read();
                        this.message = null;
                        if (this.buffer.length() == 1) {
                           this.buffer.append((char)c);
                           if (c != 45) {
                              op = (Operation)this.options.get(this.buffer.toString());
                              if (op == null) {
                                 this.message = "There is no " + this.printable(this.buffer.toString()) + " option";
                                 this.buffer.setLength(0);
                              }
                           }
                        } else if (c == 13) {
                           op = (Operation)this.options.get(this.buffer.toString());
                           if (op == null) {
                              this.message = "There is no " + this.printable(this.buffer.toString()) + " option";
                              this.buffer.setLength(0);
                           }
                        } else {
                           this.buffer.append((char)c);
                           Map<String, Operation> matching = new HashMap();

                           for(Map.Entry entry : this.options.entrySet()) {
                              if (((String)entry.getKey()).startsWith(this.buffer.toString())) {
                                 matching.put((String)entry.getKey(), (Operation)entry.getValue());
                              }
                           }

                           switch (matching.size()) {
                              case 0:
                                 this.buffer.setLength(0);
                                 break;
                              case 1:
                                 this.buffer.setLength(0);
                                 this.buffer.append((String)matching.keySet().iterator().next());
                           }
                        }
                     } else if (this.buffer.length() <= 0 || this.buffer.charAt(0) != '/' && this.buffer.charAt(0) != '?' && this.buffer.charAt(0) != '&') {
                        Operation obj = (Operation)this.bindingReader.readBinding(this.keys, (KeyMap)null, false);
                        if (obj == Less.Operation.CHAR) {
                           char c = this.bindingReader.getLastBinding().charAt(0);
                           if (c == '-' || c == '/' || c == '?' || c == '&') {
                              this.buffer.setLength(0);
                           }

                           this.buffer.append(c);
                        } else if (obj == Less.Operation.BACKSPACE) {
                           if (this.buffer.length() > 0) {
                              this.buffer.deleteCharAt(this.buffer.length() - 1);
                           }
                        } else {
                           op = obj;
                        }
                     } else {
                        forward = this.search();
                     }

                     if (op != null) {
                        this.message = null;
                        switch (op.ordinal()) {
                           case 0:
                              this.help();
                           case 1:
                           case 13:
                           case 23:
                           case 24:
                           case 25:
                           case 26:
                           case 27:
                           case 30:
                           case 31:
                           case 43:
                           case 44:
                           case 45:
                           case 46:
                           case 47:
                           case 48:
                           default:
                              break;
                           case 2:
                              this.moveForward(this.getStrictPositiveNumberInBuffer(1));
                              break;
                           case 3:
                              this.moveBackward(this.getStrictPositiveNumberInBuffer(1));
                              break;
                           case 4:
                              this.moveForward(this.getStrictPositiveNumberInBuffer(this.window));
                              break;
                           case 5:
                              this.moveBackward(this.getStrictPositiveNumberInBuffer(this.window));
                              break;
                           case 6:
                              this.window = this.getStrictPositiveNumberInBuffer(this.window);
                              this.moveForward(this.window);
                              break;
                           case 7:
                              this.window = this.getStrictPositiveNumberInBuffer(this.window);
                              this.moveBackward(this.window);
                              break;
                           case 8:
                              this.moveForward(this.window);
                              break;
                           case 9:
                              this.halfWindow = this.getStrictPositiveNumberInBuffer(this.halfWindow);
                              this.moveForward(this.halfWindow);
                              break;
                           case 10:
                              this.halfWindow = this.getStrictPositiveNumberInBuffer(this.halfWindow);
                              this.moveBackward(this.halfWindow);
                              break;
                           case 11:
                              this.firstColumnToDisplay = Math.max(0, this.firstColumnToDisplay - this.size.getColumns() / 2);
                              break;
                           case 12:
                              this.firstColumnToDisplay += this.size.getColumns() / 2;
                              break;
                           case 14:
                              this.size.copy(this.terminal.getSize());
                              this.display.clear();
                              break;
                           case 15:
                              this.message = null;
                              this.size.copy(this.terminal.getSize());
                              this.display.clear();
                              break;
                           case 16:
                              this.moveToMatch(forward, false);
                              break;
                           case 17:
                              this.moveToMatch(!forward, false);
                              break;
                           case 18:
                              this.moveToMatch(forward, true);
                              break;
                           case 19:
                              this.moveToMatch(!forward, true);
                              break;
                           case 20:
                              this.pattern = null;
                              break;
                           case 21:
                              this.moveTo(this.getStrictPositiveNumberInBuffer(1) - 1);
                              break;
                           case 22:
                              int lineNum = this.getStrictPositiveNumberInBuffer(0) - 1;
                              if (lineNum < 0) {
                                 this.moveForward(Integer.MAX_VALUE);
                              } else {
                                 this.moveTo(lineNum);
                              }
                              break;
                           case 28:
                              this.buffer.setLength(0);
                              this.printLineNumbers = !this.printLineNumbers;
                              this.message = this.printLineNumbers ? "Constantly display line numbers" : "Don't use line numbers";
                              break;
                           case 29:
                              this.buffer.setLength(0);
                              this.offsetInLine = 0;
                              this.chopLongLines = !this.chopLongLines;
                              this.message = this.chopLongLines ? "Chop long lines" : "Fold long lines";
                              this.display.clear();
                              break;
                           case 32:
                              this.buffer.setLength(0);
                              this.quiet = !this.quiet;
                              this.veryQuiet = false;
                              this.message = this.quiet ? "Ring the bell for errors but not at eof/bof" : "Ring the bell for errors AND at eof/bof";
                              break;
                           case 33:
                              this.buffer.setLength(0);
                              this.veryQuiet = !this.veryQuiet;
                              this.quiet = false;
                              this.message = this.veryQuiet ? "Never ring the bell" : "Ring the bell for errors AND at eof/bof";
                              break;
                           case 34:
                              this.ignoreCaseCond = !this.ignoreCaseCond;
                              this.ignoreCaseAlways = false;
                              this.message = this.ignoreCaseCond ? "Ignore case in searches" : "Case is significant in searches";
                              break;
                           case 35:
                              this.ignoreCaseAlways = !this.ignoreCaseAlways;
                              this.ignoreCaseCond = false;
                              this.message = this.ignoreCaseAlways ? "Ignore case in searches and in patterns" : "Case is significant in searches";
                              break;
                           case 36:
                              this.highlight = !this.highlight;
                              this.message = "Highlight " + (this.highlight ? "enabled" : "disabled");
                              break;
                           case 37:
                              this.addFile();
                              break;
                           case 38:
                              int next = this.getStrictPositiveNumberInBuffer(1);
                              if (this.sourceIdx < sources.size() - next) {
                                 SavedSourcePositions ssp = new SavedSourcePositions();
                                 this.sourceIdx += next;
                                 String newSource = ((Source)sources.get(this.sourceIdx)).getName();

                                 try {
                                    this.openSource();
                                 } catch (FileNotFoundException var30) {
                                    ssp.restore(newSource);
                                 }
                              } else {
                                 this.message = "No next file";
                              }
                              break;
                           case 39:
                              int prev = this.getStrictPositiveNumberInBuffer(1);
                              if (this.sourceIdx > prev) {
                                 SavedSourcePositions ssp = new SavedSourcePositions(-1);
                                 this.sourceIdx -= prev;
                                 String newSource = ((Source)sources.get(this.sourceIdx)).getName();

                                 try {
                                    this.openSource();
                                 } catch (FileNotFoundException var29) {
                                    ssp.restore(newSource);
                                 }
                              } else {
                                 this.message = "No previous file";
                              }
                              break;
                           case 40:
                              int tofile = this.getStrictPositiveNumberInBuffer(1);
                              if (tofile < sources.size()) {
                                 SavedSourcePositions ssp = new SavedSourcePositions(tofile < this.sourceIdx ? -1 : 0);
                                 this.sourceIdx = tofile;
                                 String newSource = ((Source)sources.get(this.sourceIdx)).getName();

                                 try {
                                    this.openSource();
                                 } catch (FileNotFoundException var28) {
                                    ssp.restore(newSource);
                                 }
                              } else {
                                 this.message = "No such file";
                              }
                              break;
                           case 41:
                              this.message = "FILE_INFO";
                              break;
                           case 42:
                              if (sources.size() > 2) {
                                 sources.remove(this.sourceIdx);
                                 if (this.sourceIdx >= sources.size()) {
                                    this.sourceIdx = sources.size() - 1;
                                 }

                                 this.openSource();
                              }
                              break;
                           case 49:
                              this.moveTo(0);
                              break;
                           case 50:
                              this.moveForward(Integer.MAX_VALUE);
                        }

                        this.buffer.setLength(0);
                     }

                     if (this.quitAtFirstEof && this.nbEof > 0 || this.quitAtSecondEof && this.nbEof > 1) {
                        if (this.sourceIdx < sources.size() - 1) {
                           ++this.sourceIdx;
                           this.openSource();
                        } else {
                           op = Less.Operation.EXIT;
                        }
                     }

                     this.display(false);
                  } while(op != Less.Operation.EXIT);

                  return;
               } catch (InterruptedException var31) {
                  return;
               } finally {
                  this.terminal.setAttributes(attr);
                  if (prevHandler != null) {
                     this.terminal.handle(Terminal.Signal.WINCH, prevHandler);
                  }

                  if (!this.noInit) {
                     this.terminal.puts(InfoCmp.Capability.exit_ca_mode);
                  }

                  if (!this.noKeypad) {
                     this.terminal.puts(InfoCmp.Capability.keypad_local);
                  }

                  this.terminal.writer().flush();
               }
            }
         } finally {
            if (this.reader != null) {
               this.reader.close();
            }

            if (status != null) {
               status.restore();
            }

            this.patternHistory.persist();
         }

      } else {
         throw new IllegalArgumentException("No sources");
      }
   }

   private void moveToMatch(boolean forward, boolean spanFiles) throws IOException {
      if (forward) {
         this.moveToNextMatch(spanFiles);
      } else {
         this.moveToPreviousMatch(spanFiles);
      }

   }

   private void addSource(String file) throws IOException {
      if (!file.contains("*") && !file.contains("?")) {
         this.sources.add(new Source.URLSource(this.currentDir.resolve(file).toUri().toURL(), file));
      } else {
         for(Path p : Commands.findFiles(this.currentDir, file)) {
            this.sources.add(new Source.URLSource(p.toUri().toURL(), p.toString()));
         }
      }

      this.sourceIdx = this.sources.size() - 1;
   }

   private void addFile() throws IOException, InterruptedException {
      KeyMap<Operation> fileKeyMap = new KeyMap();
      fileKeyMap.setUnicode(Less.Operation.INSERT);

      for(char i = ' '; i < 256; ++i) {
         fileKeyMap.bind(Less.Operation.INSERT, (CharSequence)Character.toString(i));
      }

      fileKeyMap.bind(Less.Operation.RIGHT, (CharSequence[])(KeyMap.key(this.terminal, InfoCmp.Capability.key_right), KeyMap.alt('l')));
      fileKeyMap.bind(Less.Operation.LEFT, (CharSequence[])(KeyMap.key(this.terminal, InfoCmp.Capability.key_left), KeyMap.alt('h')));
      fileKeyMap.bind(Less.Operation.HOME, (CharSequence[])(KeyMap.key(this.terminal, InfoCmp.Capability.key_home), KeyMap.alt('0')));
      fileKeyMap.bind(Less.Operation.END, (CharSequence[])(KeyMap.key(this.terminal, InfoCmp.Capability.key_end), KeyMap.alt('$')));
      fileKeyMap.bind(Less.Operation.BACKSPACE, (CharSequence)KeyMap.del());
      fileKeyMap.bind(Less.Operation.DELETE, (CharSequence)KeyMap.alt('x'));
      fileKeyMap.bind(Less.Operation.DELETE_WORD, (CharSequence)KeyMap.alt('X'));
      fileKeyMap.bind(Less.Operation.DELETE_LINE, (CharSequence)KeyMap.ctrl('U'));
      fileKeyMap.bind(Less.Operation.ACCEPT, (CharSequence)"\r");
      SavedSourcePositions ssp = new SavedSourcePositions();
      this.message = null;
      this.buffer.append("Examine: ");
      int curPos = this.buffer.length();
      int begPos = curPos;
      this.display(false, curPos);
      LineEditor lineEditor = new LineEditor(curPos);

      while(true) {
         checkInterrupted();
         Operation op;
         switch ((op = (Operation)this.bindingReader.readBinding(fileKeyMap)).ordinal()) {
            case 55:
               String name = this.buffer.substring(begPos);
               this.addSource(name);

               try {
                  this.openSource();
               } catch (Exception var9) {
                  ssp.restore(name);
               }

               return;
            default:
               curPos = lineEditor.editBuffer(op, curPos);
               if (curPos <= begPos) {
                  this.buffer.setLength(0);
                  return;
               }

               this.display(false, curPos);
         }
      }
   }

   private boolean search() throws IOException, InterruptedException {
      KeyMap<Operation> searchKeyMap = new KeyMap();
      searchKeyMap.setUnicode(Less.Operation.INSERT);

      for(char i = ' '; i < 256; ++i) {
         searchKeyMap.bind(Less.Operation.INSERT, (CharSequence)Character.toString(i));
      }

      searchKeyMap.bind(Less.Operation.RIGHT, (CharSequence[])(KeyMap.key(this.terminal, InfoCmp.Capability.key_right), KeyMap.alt('l')));
      searchKeyMap.bind(Less.Operation.LEFT, (CharSequence[])(KeyMap.key(this.terminal, InfoCmp.Capability.key_left), KeyMap.alt('h')));
      searchKeyMap.bind(Less.Operation.NEXT_WORD, (CharSequence)KeyMap.alt('w'));
      searchKeyMap.bind(Less.Operation.PREV_WORD, (CharSequence)KeyMap.alt('b'));
      searchKeyMap.bind(Less.Operation.HOME, (CharSequence[])(KeyMap.key(this.terminal, InfoCmp.Capability.key_home), KeyMap.alt('0')));
      searchKeyMap.bind(Less.Operation.END, (CharSequence[])(KeyMap.key(this.terminal, InfoCmp.Capability.key_end), KeyMap.alt('$')));
      searchKeyMap.bind(Less.Operation.BACKSPACE, (CharSequence)KeyMap.del());
      searchKeyMap.bind(Less.Operation.DELETE, (CharSequence)KeyMap.alt('x'));
      searchKeyMap.bind(Less.Operation.DELETE_WORD, (CharSequence)KeyMap.alt('X'));
      searchKeyMap.bind(Less.Operation.DELETE_LINE, (CharSequence)KeyMap.ctrl('U'));
      searchKeyMap.bind(Less.Operation.UP, (CharSequence[])(KeyMap.key(this.terminal, InfoCmp.Capability.key_up), KeyMap.alt('k')));
      searchKeyMap.bind(Less.Operation.DOWN, (CharSequence[])(KeyMap.key(this.terminal, InfoCmp.Capability.key_down), KeyMap.alt('j')));
      searchKeyMap.bind(Less.Operation.ACCEPT, (CharSequence)"\r");
      boolean forward = true;
      this.message = null;
      int curPos = this.buffer.length();
      int begPos = curPos;
      char type = this.buffer.charAt(0);
      String currentBuffer = this.buffer.toString();
      LineEditor lineEditor = new LineEditor(curPos);

      while(true) {
         checkInterrupted();
         Operation op;
         switch ((op = (Operation)this.bindingReader.readBinding(searchKeyMap)).ordinal()) {
            case 55:
               try {
                  String _pattern = this.buffer.substring(1);
                  if (type == '&') {
                     this.displayPattern = _pattern.length() > 0 ? _pattern : null;
                     this.getPattern(true);
                  } else {
                     this.pattern = _pattern;
                     this.getPattern();
                     if (type == '/') {
                        this.moveToNextMatch();
                     } else {
                        if (this.lines.size() - this.firstLineToDisplay <= this.size.getRows()) {
                           this.firstLineToDisplay = this.lines.size();
                        } else {
                           this.moveForward(this.size.getRows() - 1);
                        }

                        this.moveToPreviousMatch();
                        forward = false;
                     }
                  }

                  this.patternHistory.add(_pattern);
                  this.buffer.setLength(0);
               } catch (PatternSyntaxException e) {
                  String str = e.getMessage();
                  if (str.indexOf(10) > 0) {
                     str = str.substring(0, str.indexOf(10));
                  }

                  if (type == '&') {
                     this.displayPattern = null;
                  } else {
                     this.pattern = null;
                  }

                  this.buffer.setLength(0);
                  this.message = "Invalid pattern: " + str + " (Press a key)";
                  this.display(false);
                  this.terminal.reader().read();
                  this.message = null;
               }

               return forward;
            case 56:
               this.buffer.setLength(0);
               this.buffer.append(type);
               this.buffer.append(this.patternHistory.up(currentBuffer.substring(1)));
               curPos = this.buffer.length();
               break;
            case 57:
               this.buffer.setLength(0);
               this.buffer.append(type);
               this.buffer.append(this.patternHistory.down(currentBuffer.substring(1)));
               curPos = this.buffer.length();
               break;
            default:
               curPos = lineEditor.editBuffer(op, curPos);
               currentBuffer = this.buffer.toString();
         }

         if (curPos < begPos) {
            this.buffer.setLength(0);
            return forward;
         }

         this.display(false, curPos);
      }
   }

   private void help() throws IOException {
      SavedSourcePositions ssp = new SavedSourcePositions();
      this.printLineNumbers = false;
      this.sourceIdx = 0;

      try {
         this.openSource();
         this.display(false);

         Operation op;
         do {
            checkInterrupted();
            op = (Operation)this.bindingReader.readBinding(this.keys, (KeyMap)null, false);
            if (op != null) {
               switch (op.ordinal()) {
                  case 4:
                     this.moveForward(this.getStrictPositiveNumberInBuffer(this.window));
                     break;
                  case 5:
                     this.moveBackward(this.getStrictPositiveNumberInBuffer(this.window));
               }
            }

            this.display(false);
         } while(op != Less.Operation.EXIT);
      } catch (InterruptedException | IOException var6) {
      } finally {
         ssp.restore((String)null);
      }

   }

   protected void openSource() throws IOException {
      boolean wasOpen = false;
      if (this.reader != null) {
         this.reader.close();
         wasOpen = true;
      }

      boolean displayMessage = false;

      boolean open;
      do {
         Source source = (Source)this.sources.get(this.sourceIdx);

         try {
            InputStream in = source.read();
            if (this.sources.size() != 2 && this.sourceIdx != 0) {
               this.message = source.getName() + " (file " + this.sourceIdx + " of " + (this.sources.size() - 1) + ")";
            } else {
               this.message = source.getName();
            }

            this.reader = new BufferedReader(new InputStreamReader(new InterruptibleInputStream(in)));
            this.firstLineInMemory = 0;
            this.lines = new ArrayList();
            this.firstLineToDisplay = 0;
            this.firstColumnToDisplay = 0;
            this.offsetInLine = 0;
            this.display.clear();
            if (this.sourceIdx == 0) {
               this.syntaxHighlighter = SyntaxHighlighter.build(this.syntaxFiles, (String)null, "none");
            } else {
               this.syntaxHighlighter = SyntaxHighlighter.build(this.syntaxFiles, source.getName(), this.syntaxName, this.nanorcIgnoreErrors);
            }

            open = true;
            if (displayMessage) {
               AttributedStringBuilder asb = new AttributedStringBuilder();
               asb.style(AttributedStyle.INVERSE);
               asb.append((CharSequence)(source.getName() + " (press RETURN)"));
               asb.toAttributedString().println(this.terminal);
               this.terminal.writer().flush();
               this.terminal.reader().read();
            }
         } catch (FileNotFoundException exp) {
            this.sources.remove(this.sourceIdx);
            if (this.sourceIdx > this.sources.size() - 1) {
               this.sourceIdx = this.sources.size() - 1;
            }

            if (wasOpen) {
               throw exp;
            }

            AttributedStringBuilder asb = new AttributedStringBuilder();
            asb.append((CharSequence)(source.getName() + " not found!"));
            asb.toAttributedString().println(this.terminal);
            this.terminal.writer().flush();
            open = false;
            displayMessage = true;
         }
      } while(!open && this.sourceIdx > 0);

      if (!open) {
         throw new FileNotFoundException();
      }
   }

   void moveTo(int lineNum) throws IOException {
      AttributedString line = this.getLine(lineNum);
      if (line != null) {
         this.display.clear();
         if (this.firstLineInMemory > lineNum) {
            this.openSource();
         }

         this.firstLineToDisplay = lineNum;
         this.offsetInLine = 0;
      } else {
         this.message = "Cannot seek to line number " + (lineNum + 1);
      }

   }

   private void moveToNextMatch() throws IOException {
      this.moveToNextMatch(false);
   }

   private void moveToNextMatch(boolean spanFiles) throws IOException {
      Pattern compiled = this.getPattern();
      Pattern dpCompiled = this.getPattern(true);
      if (compiled != null) {
         int lineNumber = this.firstLineToDisplay + 1;

         while(true) {
            AttributedString line = this.getLine(lineNumber);
            if (line == null) {
               break;
            }

            if (this.toBeDisplayed(line, dpCompiled) && compiled.matcher(line).find()) {
               this.display.clear();
               this.firstLineToDisplay = lineNumber;
               this.offsetInLine = 0;
               return;
            }

            ++lineNumber;
         }
      }

      if (spanFiles) {
         if (this.sourceIdx < this.sources.size() - 1) {
            SavedSourcePositions ssp = new SavedSourcePositions();
            String newSource = ((Source)this.sources.get(++this.sourceIdx)).getName();

            try {
               this.openSource();
               this.moveToNextMatch(true);
            } catch (FileNotFoundException var7) {
               ssp.restore(newSource);
            }
         } else {
            this.message = "Pattern not found";
         }
      } else {
         this.message = "Pattern not found";
      }

   }

   private void moveToPreviousMatch() throws IOException {
      this.moveToPreviousMatch(false);
   }

   private void moveToPreviousMatch(boolean spanFiles) throws IOException {
      Pattern compiled = this.getPattern();
      Pattern dpCompiled = this.getPattern(true);
      if (compiled != null) {
         for(int lineNumber = this.firstLineToDisplay - 1; lineNumber >= this.firstLineInMemory; --lineNumber) {
            AttributedString line = this.getLine(lineNumber);
            if (line == null) {
               break;
            }

            if (this.toBeDisplayed(line, dpCompiled) && compiled.matcher(line).find()) {
               this.display.clear();
               this.firstLineToDisplay = lineNumber;
               this.offsetInLine = 0;
               return;
            }
         }
      }

      if (spanFiles) {
         if (this.sourceIdx > 1) {
            SavedSourcePositions ssp = new SavedSourcePositions(-1);
            String newSource = ((Source)this.sources.get(--this.sourceIdx)).getName();

            try {
               this.openSource();
               this.moveTo(Integer.MAX_VALUE);
               this.moveToPreviousMatch(true);
            } catch (FileNotFoundException var7) {
               ssp.restore(newSource);
            }
         } else {
            this.message = "Pattern not found";
         }
      } else {
         this.message = "Pattern not found";
      }

   }

   private String printable(String s) {
      StringBuilder sb = new StringBuilder();

      for(int i = 0; i < s.length(); ++i) {
         char c = s.charAt(i);
         if (c == 27) {
            sb.append("ESC");
         } else if (c < ' ') {
            sb.append('^').append((char)(c + 64));
         } else if (c < 128) {
            sb.append(c);
         } else {
            sb.append('\\').append(String.format("%03o", Integer.valueOf(c)));
         }
      }

      return sb.toString();
   }

   void moveForward(int lines) throws IOException {
      Pattern dpCompiled = this.getPattern(true);
      int width = this.size.getColumns() - (this.printLineNumbers ? 8 : 0);
      int height = this.size.getRows();
      boolean doOffsets = this.firstColumnToDisplay == 0 && !this.chopLongLines;
      if (lines >= this.size.getRows() - 1) {
         this.display.clear();
      }

      if (lines == Integer.MAX_VALUE) {
         this.moveTo(Integer.MAX_VALUE);
         this.firstLineToDisplay = height - 1;

         for(int l = 0; l < height - 1; ++l) {
            this.firstLineToDisplay = (Integer)this.prevLine2display(this.firstLineToDisplay, dpCompiled).getU();
         }
      }

      while(true) {
         --lines;
         if (lines < 0) {
            return;
         }

         int lastLineToDisplay = this.firstLineToDisplay;
         if (!doOffsets) {
            for(int l = 0; l < height - 1; ++l) {
               lastLineToDisplay = (Integer)this.nextLine2display(lastLineToDisplay, dpCompiled).getU();
            }
         } else {
            int off = this.offsetInLine;

            for(int l = 0; l < height - 1; ++l) {
               Pair<Integer, AttributedString> nextLine = this.nextLine2display(lastLineToDisplay, dpCompiled);
               AttributedString line = (AttributedString)nextLine.getV();
               if (line == null) {
                  lastLineToDisplay = (Integer)nextLine.getU();
                  break;
               }

               if (line.columnLength() > off + width) {
                  off += width;
               } else {
                  off = 0;
                  lastLineToDisplay = (Integer)nextLine.getU();
               }
            }
         }

         if (this.getLine(lastLineToDisplay) == null) {
            this.eof();
            return;
         }

         Pair<Integer, AttributedString> nextLine = this.nextLine2display(this.firstLineToDisplay, dpCompiled);
         AttributedString line = (AttributedString)nextLine.getV();
         if (doOffsets && line.columnLength() > width + this.offsetInLine) {
            this.offsetInLine += width;
         } else {
            this.offsetInLine = 0;
            this.firstLineToDisplay = (Integer)nextLine.getU();
         }
      }
   }

   void moveBackward(int lines) throws IOException {
      Pattern dpCompiled = this.getPattern(true);
      int width = this.size.getColumns() - (this.printLineNumbers ? 8 : 0);
      if (lines >= this.size.getRows() - 1) {
         this.display.clear();
      }

      while(true) {
         --lines;
         if (lines < 0) {
            return;
         }

         if (this.offsetInLine > 0) {
            this.offsetInLine = Math.max(0, this.offsetInLine - width);
         } else {
            if (this.firstLineInMemory >= this.firstLineToDisplay) {
               this.bof();
               return;
            }

            Pair<Integer, AttributedString> prevLine = this.prevLine2display(this.firstLineToDisplay, dpCompiled);
            this.firstLineToDisplay = (Integer)prevLine.getU();
            AttributedString line = (AttributedString)prevLine.getV();
            if (line != null && this.firstColumnToDisplay == 0 && !this.chopLongLines) {
               int length = line.columnLength();
               this.offsetInLine = length - length % width;
            }
         }
      }
   }

   private void eof() {
      ++this.nbEof;
      if (this.sourceIdx > 0 && this.sourceIdx < this.sources.size() - 1) {
         this.message = "(END) - Next: " + ((Source)this.sources.get(this.sourceIdx + 1)).getName();
      } else {
         this.message = "(END)";
      }

      if (!this.quiet && !this.veryQuiet && !this.quitAtFirstEof && !this.quitAtSecondEof) {
         this.terminal.puts(InfoCmp.Capability.bell);
         this.terminal.writer().flush();
      }

   }

   private void bof() {
      if (!this.quiet && !this.veryQuiet) {
         this.terminal.puts(InfoCmp.Capability.bell);
         this.terminal.writer().flush();
      }

   }

   int getStrictPositiveNumberInBuffer(int def) {
      int var3;
      try {
         int n = Integer.parseInt(this.buffer.toString());
         var3 = n > 0 ? n : def;
         return var3;
      } catch (NumberFormatException var7) {
         var3 = def;
      } finally {
         this.buffer.setLength(0);
      }

      return var3;
   }

   private Pair nextLine2display(int line, Pattern dpCompiled) throws IOException {
      AttributedString curLine;
      do {
         curLine = this.getLine(line++);
      } while(!this.toBeDisplayed(curLine, dpCompiled));

      return new Pair(line, curLine);
   }

   private Pair prevLine2display(int line, Pattern dpCompiled) throws IOException {
      AttributedString curLine;
      do {
         curLine = this.getLine(line--);
      } while(line > 0 && !this.toBeDisplayed(curLine, dpCompiled));

      if (line == 0 && !this.toBeDisplayed(curLine, dpCompiled)) {
         curLine = null;
      }

      return new Pair(line, curLine);
   }

   private boolean toBeDisplayed(AttributedString curLine, Pattern dpCompiled) {
      return curLine == null || dpCompiled == null || this.sourceIdx == 0 || dpCompiled.matcher(curLine).find();
   }

   synchronized boolean display(boolean oneScreen) throws IOException {
      return this.display(oneScreen, (Integer)null);
   }

   synchronized boolean display(boolean oneScreen, Integer curPos) throws IOException {
      List<AttributedString> newLines = new ArrayList();
      int width = this.size.getColumns() - (this.printLineNumbers ? 8 : 0);
      int height = this.size.getRows();
      int inputLine = this.firstLineToDisplay;
      AttributedString curLine = null;
      Pattern compiled = this.getPattern();
      Pattern dpCompiled = this.getPattern(true);
      boolean fitOnOneScreen = false;
      boolean eof = false;
      if (this.highlight) {
         this.syntaxHighlighter.reset();

         for(int i = Math.max(0, inputLine - height); i < inputLine; ++i) {
            this.syntaxHighlighter.highlight(this.getLine(i));
         }
      }

      for(int terminalLine = 0; terminalLine < height - 1; ++terminalLine) {
         if (curLine == null) {
            Pair<Integer, AttributedString> nextLine = this.nextLine2display(inputLine, dpCompiled);
            inputLine = (Integer)nextLine.getU();
            curLine = (AttributedString)nextLine.getV();
            if (curLine == null) {
               if (oneScreen) {
                  fitOnOneScreen = true;
                  break;
               }

               eof = true;
               curLine = new AttributedString("~");
            } else if (this.highlight) {
               curLine = this.syntaxHighlighter.highlight(curLine);
            }

            if (compiled != null) {
               curLine = curLine.styleMatches(compiled, AttributedStyle.DEFAULT.inverse());
            }
         }

         AttributedString toDisplay;
         if (this.firstColumnToDisplay <= 0 && !this.chopLongLines) {
            if (terminalLine == 0 && this.offsetInLine > 0) {
               curLine = curLine.columnSubSequence(this.offsetInLine, Integer.MAX_VALUE);
            }

            toDisplay = curLine.columnSubSequence(0, width);
            curLine = curLine.columnSubSequence(width, Integer.MAX_VALUE);
            if (curLine.length() == 0) {
               curLine = null;
            }
         } else {
            int off = this.firstColumnToDisplay;
            if (terminalLine == 0 && this.offsetInLine > 0) {
               off = Math.max(this.offsetInLine, off);
            }

            toDisplay = curLine.columnSubSequence(off, off + width);
            curLine = null;
         }

         if (this.printLineNumbers && !eof) {
            AttributedStringBuilder sb = new AttributedStringBuilder();
            sb.append((CharSequence)String.format("%7d ", inputLine));
            sb.append(toDisplay);
            newLines.add(sb.toAttributedString());
         } else {
            newLines.add(toDisplay);
         }
      }

      if (oneScreen) {
         if (fitOnOneScreen) {
            newLines.forEach((l) -> l.println(this.terminal));
         }

         return fitOnOneScreen;
      } else {
         AttributedStringBuilder msg = new AttributedStringBuilder();
         if ("FILE_INFO".equals(this.message)) {
            Source source = (Source)this.sources.get(this.sourceIdx);
            Long allLines = source.lines();
            this.message = source.getName() + (this.sources.size() > 2 ? " (file " + this.sourceIdx + " of " + (this.sources.size() - 1) + ")" : "") + " lines " + (this.firstLineToDisplay + 1) + "-" + inputLine + "/" + (allLines != null ? allLines : (long)this.lines.size()) + (eof ? " (END)" : "");
         }

         if (this.buffer.length() > 0) {
            msg.append((CharSequence)" ").append((CharSequence)this.buffer);
         } else if (this.bindingReader.getCurrentBuffer().length() > 0 && this.terminal.reader().peek(1L) == -2) {
            msg.append((CharSequence)" ").append((CharSequence)this.printable(this.bindingReader.getCurrentBuffer()));
         } else if (this.message != null) {
            msg.style(AttributedStyle.INVERSE);
            msg.append((CharSequence)this.message);
            msg.style(AttributedStyle.INVERSE.inverseOff());
         } else if (this.displayPattern != null) {
            msg.append((CharSequence)"&");
         } else {
            msg.append((CharSequence)":");
         }

         newLines.add(msg.toAttributedString());
         this.display.resize(this.size.getRows(), this.size.getColumns());
         if (curPos == null) {
            this.display.update(newLines, -1);
         } else {
            this.display.update(newLines, this.size.cursorPos(this.size.getRows() - 1, curPos + 1));
         }

         return false;
      }
   }

   private Pattern getPattern() {
      return this.getPattern(false);
   }

   private Pattern getPattern(boolean doDisplayPattern) {
      Pattern compiled = null;
      String _pattern = doDisplayPattern ? this.displayPattern : this.pattern;
      if (_pattern != null) {
         boolean insensitive = this.ignoreCaseAlways || this.ignoreCaseCond && _pattern.toLowerCase().equals(_pattern);
         compiled = Pattern.compile("(" + _pattern + ")", insensitive ? 66 : 0);
      }

      return compiled;
   }

   AttributedString getLine(int line) throws IOException {
      while(true) {
         if (line >= this.lines.size()) {
            String str = this.reader.readLine();
            if (str != null) {
               this.lines.add(AttributedString.fromAnsi(str, this.tabs));
               continue;
            }
         }

         if (line < this.lines.size()) {
            return (AttributedString)this.lines.get(line);
         }

         return null;
      }
   }

   public static void checkInterrupted() throws InterruptedException {
      Thread.yield();
      if (Thread.currentThread().isInterrupted()) {
         throw new InterruptedException();
      }
   }

   private void bindKeys(KeyMap map) {
      map.bind(Less.Operation.HELP, (CharSequence[])("h", "H"));
      map.bind(Less.Operation.EXIT, (CharSequence[])("q", ":q", "Q", ":Q", "ZZ"));
      map.bind(Less.Operation.FORWARD_ONE_LINE, (CharSequence[])("e", KeyMap.ctrl('E'), "j", KeyMap.ctrl('N'), "\r", KeyMap.key(this.terminal, InfoCmp.Capability.key_down)));
      map.bind(Less.Operation.BACKWARD_ONE_LINE, (CharSequence[])("y", KeyMap.ctrl('Y'), "k", KeyMap.ctrl('K'), KeyMap.ctrl('P'), KeyMap.key(this.terminal, InfoCmp.Capability.key_up)));
      map.bind(Less.Operation.FORWARD_ONE_WINDOW_OR_LINES, (CharSequence[])("f", KeyMap.ctrl('F'), KeyMap.ctrl('V'), " ", KeyMap.key(this.terminal, InfoCmp.Capability.key_npage)));
      map.bind(Less.Operation.BACKWARD_ONE_WINDOW_OR_LINES, (CharSequence[])("b", KeyMap.ctrl('B'), KeyMap.alt('v'), KeyMap.key(this.terminal, InfoCmp.Capability.key_ppage)));
      map.bind(Less.Operation.FORWARD_ONE_WINDOW_AND_SET, (CharSequence)"z");
      map.bind(Less.Operation.BACKWARD_ONE_WINDOW_AND_SET, (CharSequence)"w");
      map.bind(Less.Operation.FORWARD_ONE_WINDOW_NO_STOP, (CharSequence)KeyMap.alt(' '));
      map.bind(Less.Operation.FORWARD_HALF_WINDOW_AND_SET, (CharSequence[])("d", KeyMap.ctrl('D')));
      map.bind(Less.Operation.BACKWARD_HALF_WINDOW_AND_SET, (CharSequence[])("u", KeyMap.ctrl('U')));
      map.bind(Less.Operation.RIGHT_ONE_HALF_SCREEN, (CharSequence[])(KeyMap.alt(')'), KeyMap.key(this.terminal, InfoCmp.Capability.key_right)));
      map.bind(Less.Operation.LEFT_ONE_HALF_SCREEN, (CharSequence[])(KeyMap.alt('('), KeyMap.key(this.terminal, InfoCmp.Capability.key_left)));
      map.bind(Less.Operation.FORWARD_FOREVER, (CharSequence)"F");
      map.bind(Less.Operation.REPAINT, (CharSequence[])("r", KeyMap.ctrl('R'), KeyMap.ctrl('L')));
      map.bind(Less.Operation.REPAINT_AND_DISCARD, (CharSequence)"R");
      map.bind(Less.Operation.REPEAT_SEARCH_FORWARD, (CharSequence)"n");
      map.bind(Less.Operation.REPEAT_SEARCH_BACKWARD, (CharSequence)"N");
      map.bind(Less.Operation.REPEAT_SEARCH_FORWARD_SPAN_FILES, (CharSequence)KeyMap.alt('n'));
      map.bind(Less.Operation.REPEAT_SEARCH_BACKWARD_SPAN_FILES, (CharSequence)KeyMap.alt('N'));
      map.bind(Less.Operation.UNDO_SEARCH, (CharSequence)KeyMap.alt('u'));
      map.bind(Less.Operation.GO_TO_FIRST_LINE_OR_N, (CharSequence[])("g", "<", KeyMap.alt('<')));
      map.bind(Less.Operation.GO_TO_LAST_LINE_OR_N, (CharSequence[])("G", ">", KeyMap.alt('>')));
      map.bind(Less.Operation.HOME, (CharSequence)KeyMap.key(this.terminal, InfoCmp.Capability.key_home));
      map.bind(Less.Operation.END, (CharSequence)KeyMap.key(this.terminal, InfoCmp.Capability.key_end));
      map.bind(Less.Operation.ADD_FILE, (CharSequence[])(":e", KeyMap.ctrl('X') + KeyMap.ctrl('V')));
      map.bind(Less.Operation.NEXT_FILE, (CharSequence)":n");
      map.bind(Less.Operation.PREV_FILE, (CharSequence)":p");
      map.bind(Less.Operation.GOTO_FILE, (CharSequence)":x");
      map.bind(Less.Operation.INFO_FILE, (CharSequence[])("=", ":f", KeyMap.ctrl('G')));
      map.bind(Less.Operation.DELETE_FILE, (CharSequence)":d");
      map.bind(Less.Operation.BACKSPACE, (CharSequence)KeyMap.del());
      "-/0123456789?&".chars().forEach((c) -> map.bind(Less.Operation.CHAR, (CharSequence)Character.toString((char)c)));
   }

   private class LineEditor {
      private final int begPos;

      public LineEditor(int begPos) {
         this.begPos = begPos;
      }

      public int editBuffer(Operation op, int curPos) {
         switch (op.ordinal()) {
            case 44:
               Less.this.buffer.insert(curPos++, Less.this.bindingReader.getLastBinding());
               break;
            case 45:
               if (curPos < Less.this.buffer.length()) {
                  ++curPos;
               }
               break;
            case 46:
               if (curPos > this.begPos) {
                  --curPos;
               }
               break;
            case 47:
               int newPos = Less.this.buffer.length();

               for(int i = curPos; i < Less.this.buffer.length(); ++i) {
                  if (Less.this.buffer.charAt(i) == ' ') {
                     newPos = i + 1;
                     break;
                  }
               }

               curPos = newPos;
               break;
            case 48:
               int newPos = this.begPos;

               for(int i = curPos - 2; i > this.begPos; --i) {
                  if (Less.this.buffer.charAt(i) == ' ') {
                     newPos = i + 1;
                     break;
                  }
               }

               curPos = newPos;
               break;
            case 49:
               curPos = this.begPos;
               break;
            case 50:
               curPos = Less.this.buffer.length();
               break;
            case 51:
               if (curPos > this.begPos - 1) {
                  --curPos;
                  Less.this.buffer.deleteCharAt(curPos);
               }
               break;
            case 52:
               if (curPos >= this.begPos && curPos < Less.this.buffer.length()) {
                  Less.this.buffer.deleteCharAt(curPos);
               }
               break;
            case 53:
               while(curPos < Less.this.buffer.length() && Less.this.buffer.charAt(curPos) != ' ') {
                  Less.this.buffer.deleteCharAt(curPos);
               }

               while(curPos - 1 >= this.begPos) {
                  if (Less.this.buffer.charAt(curPos - 1) == ' ') {
                     --curPos;
                     Less.this.buffer.deleteCharAt(curPos);
                     return curPos;
                  }

                  --curPos;
                  Less.this.buffer.deleteCharAt(curPos);
               }
               break;
            case 54:
               Less.this.buffer.setLength(this.begPos);
               curPos = 1;
         }

         return curPos;
      }
   }

   private class SavedSourcePositions {
      int saveSourceIdx;
      int saveFirstLineToDisplay;
      int saveFirstColumnToDisplay;
      int saveOffsetInLine;
      boolean savePrintLineNumbers;

      public SavedSourcePositions() {
         this(0);
      }

      public SavedSourcePositions(int dec) {
         this.saveSourceIdx = Less.this.sourceIdx + dec;
         this.saveFirstLineToDisplay = Less.this.firstLineToDisplay;
         this.saveFirstColumnToDisplay = Less.this.firstColumnToDisplay;
         this.saveOffsetInLine = Less.this.offsetInLine;
         this.savePrintLineNumbers = Less.this.printLineNumbers;
      }

      public void restore(String failingSource) throws IOException {
         Less.this.sourceIdx = this.saveSourceIdx;
         Less.this.openSource();
         Less.this.firstLineToDisplay = this.saveFirstLineToDisplay;
         Less.this.firstColumnToDisplay = this.saveFirstColumnToDisplay;
         Less.this.offsetInLine = this.saveOffsetInLine;
         Less.this.printLineNumbers = this.savePrintLineNumbers;
         if (failingSource != null) {
            Less.this.message = failingSource + " not found!";
         }

      }
   }

   protected static enum Operation {
      HELP,
      EXIT,
      FORWARD_ONE_LINE,
      BACKWARD_ONE_LINE,
      FORWARD_ONE_WINDOW_OR_LINES,
      BACKWARD_ONE_WINDOW_OR_LINES,
      FORWARD_ONE_WINDOW_AND_SET,
      BACKWARD_ONE_WINDOW_AND_SET,
      FORWARD_ONE_WINDOW_NO_STOP,
      FORWARD_HALF_WINDOW_AND_SET,
      BACKWARD_HALF_WINDOW_AND_SET,
      LEFT_ONE_HALF_SCREEN,
      RIGHT_ONE_HALF_SCREEN,
      FORWARD_FOREVER,
      REPAINT,
      REPAINT_AND_DISCARD,
      REPEAT_SEARCH_FORWARD,
      REPEAT_SEARCH_BACKWARD,
      REPEAT_SEARCH_FORWARD_SPAN_FILES,
      REPEAT_SEARCH_BACKWARD_SPAN_FILES,
      UNDO_SEARCH,
      GO_TO_FIRST_LINE_OR_N,
      GO_TO_LAST_LINE_OR_N,
      GO_TO_PERCENT_OR_N,
      GO_TO_NEXT_TAG,
      GO_TO_PREVIOUS_TAG,
      FIND_CLOSE_BRACKET,
      FIND_OPEN_BRACKET,
      OPT_PRINT_LINES,
      OPT_CHOP_LONG_LINES,
      OPT_QUIT_AT_FIRST_EOF,
      OPT_QUIT_AT_SECOND_EOF,
      OPT_QUIET,
      OPT_VERY_QUIET,
      OPT_IGNORE_CASE_COND,
      OPT_IGNORE_CASE_ALWAYS,
      OPT_SYNTAX_HIGHLIGHT,
      ADD_FILE,
      NEXT_FILE,
      PREV_FILE,
      GOTO_FILE,
      INFO_FILE,
      DELETE_FILE,
      CHAR,
      INSERT,
      RIGHT,
      LEFT,
      NEXT_WORD,
      PREV_WORD,
      HOME,
      END,
      BACKSPACE,
      DELETE,
      DELETE_WORD,
      DELETE_LINE,
      ACCEPT,
      UP,
      DOWN;

      // $FF: synthetic method
      private static Operation[] $values() {
         return new Operation[]{HELP, EXIT, FORWARD_ONE_LINE, BACKWARD_ONE_LINE, FORWARD_ONE_WINDOW_OR_LINES, BACKWARD_ONE_WINDOW_OR_LINES, FORWARD_ONE_WINDOW_AND_SET, BACKWARD_ONE_WINDOW_AND_SET, FORWARD_ONE_WINDOW_NO_STOP, FORWARD_HALF_WINDOW_AND_SET, BACKWARD_HALF_WINDOW_AND_SET, LEFT_ONE_HALF_SCREEN, RIGHT_ONE_HALF_SCREEN, FORWARD_FOREVER, REPAINT, REPAINT_AND_DISCARD, REPEAT_SEARCH_FORWARD, REPEAT_SEARCH_BACKWARD, REPEAT_SEARCH_FORWARD_SPAN_FILES, REPEAT_SEARCH_BACKWARD_SPAN_FILES, UNDO_SEARCH, GO_TO_FIRST_LINE_OR_N, GO_TO_LAST_LINE_OR_N, GO_TO_PERCENT_OR_N, GO_TO_NEXT_TAG, GO_TO_PREVIOUS_TAG, FIND_CLOSE_BRACKET, FIND_OPEN_BRACKET, OPT_PRINT_LINES, OPT_CHOP_LONG_LINES, OPT_QUIT_AT_FIRST_EOF, OPT_QUIT_AT_SECOND_EOF, OPT_QUIET, OPT_VERY_QUIET, OPT_IGNORE_CASE_COND, OPT_IGNORE_CASE_ALWAYS, OPT_SYNTAX_HIGHLIGHT, ADD_FILE, NEXT_FILE, PREV_FILE, GOTO_FILE, INFO_FILE, DELETE_FILE, CHAR, INSERT, RIGHT, LEFT, NEXT_WORD, PREV_WORD, HOME, END, BACKSPACE, DELETE, DELETE_WORD, DELETE_LINE, ACCEPT, UP, DOWN};
      }
   }

   static class InterruptibleInputStream extends FilterInputStream {
      InterruptibleInputStream(InputStream in) {
         super(in);
      }

      public int read(byte[] b, int off, int len) throws IOException {
         if (Thread.currentThread().isInterrupted()) {
            throw new InterruptedIOException();
         } else {
            return super.read(b, off, len);
         }
      }
   }

   static class Pair {
      final Object u;
      final Object v;

      public Pair(Object u, Object v) {
         this.u = u;
         this.v = v;
      }

      public Object getU() {
         return this.u;
      }

      public Object getV() {
         return this.v;
      }
   }
}
