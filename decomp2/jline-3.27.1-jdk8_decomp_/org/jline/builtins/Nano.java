package org.jline.builtins;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.jline.keymap.BindingReader;
import org.jline.keymap.KeyMap;
import org.jline.reader.Editor;
import org.jline.terminal.Attributes;
import org.jline.terminal.MouseEvent;
import org.jline.terminal.Size;
import org.jline.terminal.Terminal;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;
import org.jline.utils.Display;
import org.jline.utils.InfoCmp;
import org.jline.utils.Status;
import org.mozilla.universalchardet.CharsetListener;
import org.mozilla.universalchardet.UniversalDetector;

public class Nano implements Editor {
   protected final Terminal terminal;
   protected final Display display;
   protected final BindingReader bindingReader;
   protected final Size size;
   protected final Path root;
   protected final int vsusp;
   private final List syntaxFiles;
   protected KeyMap keys;
   public String title;
   public boolean printLineNumbers;
   public boolean wrapping;
   public boolean smoothScrolling;
   public boolean mouseSupport;
   public boolean oneMoreLine;
   public boolean constantCursor;
   public boolean quickBlank;
   public int tabs;
   public String brackets;
   public String matchBrackets;
   public String punct;
   public String quoteStr;
   private boolean restricted;
   private String syntaxName;
   private boolean writeBackup;
   private boolean atBlanks;
   private boolean view;
   private boolean cut2end;
   private boolean tempFile;
   private String historyLog;
   private boolean tabsToSpaces;
   private boolean autoIndent;
   protected final List buffers;
   protected int bufferIndex;
   protected Buffer buffer;
   protected String message;
   protected String errorMessage;
   protected int nbBindings;
   protected LinkedHashMap shortcuts;
   protected String editMessage;
   protected final StringBuilder editBuffer;
   protected boolean searchCaseSensitive;
   protected boolean searchRegexp;
   protected boolean searchBackwards;
   protected String searchTerm;
   protected int matchedLength;
   protected PatternHistory patternHistory;
   protected WriteMode writeMode;
   protected List cutbuffer;
   protected boolean mark;
   protected boolean highlight;
   private boolean searchToReplace;
   protected boolean readNewBuffer;
   private boolean nanorcIgnoreErrors;
   private final boolean windowsTerminal;

   public static String[] usage() {
      return new String[]{"nano -  edit files", "Usage: nano [OPTIONS] [FILES]", "  -? --help                    Show help", "  -B --backup                  When saving a file, back up the previous version of it, using the current filename", "                               suffixed with a tilde (~).", "  -I --ignorercfiles           Don't look at the system's nanorc nor at the user's nanorc.", "  -Q --quotestr=regex          Set the regular expression for matching the quoting part of a line.", "  -T --tabsize=number          Set the size (width) of a tab to number columns.", "  -U --quickblank              Do quick status-bar blanking: status-bar messages will disappear after 1 keystroke.", "  -c --constantshow            Constantly show the cursor position on the status bar.", "  -e --emptyline               Do not use the line below the title bar, leaving it entirely blank.", "  -j --jumpyscrolling          Scroll the buffer contents per half-screen instead of per line.", "  -l --linenumbers             Display line numbers to the left of the text area.", "  -m --mouse                   Enable mouse support, if available for your system.", "  -$ --softwrap                Enable 'soft wrapping'. ", "  -a --atblanks                Wrap lines at whitespace instead of always at the edge of the screen.", "  -R --restricted              Restricted mode: don't allow suspending; don't allow a file to be appended to,", "                               prepended to, or saved under a different name if it already has one;", "                               and don't use backup files.", "  -Y --syntax=name             The name of the syntax highlighting to use.", "  -z --suspend                 Enable the ability to suspend nano using the system's suspend keystroke (usually ^Z).", "  -v --view                    Don't allow the contents of the file to be altered: read-only mode.", "  -k --cutfromcursor           Make the 'Cut Text' command cut from the current cursor position to the end of the line", "  -t --tempfile                Save a changed buffer without prompting (when exiting with ^X).", "  -H --historylog=name         Log search strings to file, so they can be retrieved in later sessions", "  -E --tabstospaces            Convert typed tabs to spaces.", "  -i --autoindent              Indent new lines to the previous line's indentation."};
   }

   public Nano(Terminal terminal, File root) {
      this(terminal, root.toPath());
   }

   public Nano(Terminal terminal, Path root) {
      this(terminal, root, (Options)null);
   }

   public Nano(Terminal terminal, Path root, Options opts) {
      this(terminal, root, opts, (ConfigurationPath)null);
   }

   public Nano(Terminal terminal, Path root, Options opts, ConfigurationPath configPath) {
      this.syntaxFiles = new ArrayList();
      this.title = "JLine Nano 3.0.0";
      this.printLineNumbers = false;
      this.wrapping = false;
      this.smoothScrolling = true;
      this.mouseSupport = false;
      this.oneMoreLine = true;
      this.constantCursor = false;
      this.quickBlank = false;
      this.tabs = 4;
      this.brackets = "\"’)>]}";
      this.matchBrackets = "(<[{)>]}";
      this.punct = "!.?";
      this.quoteStr = "^([ \\t]*[#:>\\|}])+";
      this.restricted = false;
      this.writeBackup = false;
      this.atBlanks = false;
      this.view = false;
      this.cut2end = false;
      this.tempFile = false;
      this.historyLog = null;
      this.tabsToSpaces = false;
      this.autoIndent = false;
      this.buffers = new ArrayList();
      this.errorMessage = null;
      this.nbBindings = 0;
      this.editBuffer = new StringBuilder();
      this.matchedLength = -1;
      this.patternHistory = new PatternHistory((Path)null);
      this.writeMode = Nano.WriteMode.WRITE;
      this.cutbuffer = new ArrayList();
      this.mark = false;
      this.highlight = true;
      this.searchToReplace = false;
      this.readNewBuffer = true;
      this.terminal = terminal;
      this.windowsTerminal = terminal.getClass().getSimpleName().endsWith("WinSysTerminal");
      this.root = root;
      this.display = new Display(terminal, true);
      this.bindingReader = new BindingReader(terminal.reader());
      this.size = new Size();
      Attributes attrs = terminal.getAttributes();
      this.vsusp = attrs.getControlChar(Attributes.ControlChar.VSUSP);
      if (this.vsusp > 0) {
         attrs.setControlChar(Attributes.ControlChar.VSUSP, 0);
         terminal.setAttributes(attrs);
      }

      Path nanorc = configPath != null ? configPath.getConfig("jnanorc") : null;
      boolean ignorercfiles = opts != null && opts.isSet("ignorercfiles");
      if (nanorc != null && !ignorercfiles) {
         try {
            this.parseConfig(nanorc);
         } catch (IOException var14) {
            this.errorMessage = "Encountered error while reading config file: " + nanorc;
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
            } catch (Throwable var15) {
               if (pathStream != null) {
                  try {
                     pathStream.close();
                  } catch (Throwable var13) {
                     var15.addSuppressed(var13);
                  }
               }

               throw var15;
            }

            if (pathStream != null) {
               pathStream.close();
            }
         } catch (IOException var16) {
            this.errorMessage = "Encountered error while reading nanorc files";
         }
      }

      if (opts != null) {
         this.restricted = opts.isSet("restricted");
         this.syntaxName = null;
         if (opts.isSet("syntax")) {
            this.syntaxName = opts.get("syntax");
            this.nanorcIgnoreErrors = false;
         }

         if (opts.isSet("backup")) {
            this.writeBackup = true;
         }

         if (opts.isSet("quotestr")) {
            this.quoteStr = opts.get("quotestr");
         }

         if (opts.isSet("tabsize")) {
            this.tabs = opts.getNumber("tabsize");
         }

         if (opts.isSet("quickblank")) {
            this.quickBlank = true;
         }

         if (opts.isSet("constantshow")) {
            this.constantCursor = true;
         }

         if (opts.isSet("emptyline")) {
            this.oneMoreLine = false;
         }

         if (opts.isSet("jumpyscrolling")) {
            this.smoothScrolling = false;
         }

         if (opts.isSet("linenumbers")) {
            this.printLineNumbers = true;
         }

         if (opts.isSet("mouse")) {
            this.mouseSupport = true;
         }

         if (opts.isSet("softwrap")) {
            this.wrapping = true;
         }

         if (opts.isSet("atblanks")) {
            this.atBlanks = true;
         }

         if (opts.isSet("suspend")) {
            this.enableSuspension();
         }

         if (opts.isSet("view")) {
            this.view = true;
         }

         if (opts.isSet("cutfromcursor")) {
            this.cut2end = true;
         }

         if (opts.isSet("tempfile")) {
            this.tempFile = true;
         }

         if (opts.isSet("historylog")) {
            this.historyLog = opts.get("historyLog");
         }

         if (opts.isSet("tabstospaces")) {
            this.tabsToSpaces = true;
         }

         if (opts.isSet("autoindent")) {
            this.autoIndent = true;
         }
      }

      this.bindKeys();
      if (configPath != null && this.historyLog != null) {
         try {
            this.patternHistory = new PatternHistory(configPath.getUserConfig(this.historyLog, true));
         } catch (IOException var12) {
            this.errorMessage = "Encountered error while reading pattern-history file: " + this.historyLog;
         }
      }

   }

   private void parseConfig(Path file) throws IOException {
      BufferedReader reader = new BufferedReader(new FileReader(file.toFile()));

      String line;
      try {
         while((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.length() > 0 && !line.startsWith("#")) {
               List<String> parts = SyntaxHighlighter.RuleSplitter.split(line);
               if (((String)parts.get(0)).equals("include")) {
                  SyntaxHighlighter.nanorcInclude((String)parts.get(1), this.syntaxFiles);
               } else if (((String)parts.get(0)).equals("theme")) {
                  SyntaxHighlighter.nanorcTheme((String)parts.get(1), this.syntaxFiles);
               } else if (parts.size() != 2 || !((String)parts.get(0)).equals("set") && !((String)parts.get(0)).equals("unset")) {
                  if (parts.size() == 3 && ((String)parts.get(0)).equals("set")) {
                     String option = (String)parts.get(1);
                     String val = (String)parts.get(2);
                     if (option.equals("quotestr")) {
                        this.quoteStr = val;
                     } else if (option.equals("punct")) {
                        this.punct = val;
                     } else if (option.equals("matchbrackets")) {
                        this.matchBrackets = val;
                     } else if (option.equals("brackets")) {
                        this.brackets = val;
                     } else if (option.equals("historylog")) {
                        this.historyLog = val;
                     } else {
                        this.errorMessage = "Nano config: Unknown or unsupported configuration option " + option;
                     }
                  } else if (!((String)parts.get(0)).equals("bind") && !((String)parts.get(0)).equals("unbind")) {
                     this.errorMessage = "Nano config: Bad configuration '" + line + "'";
                  } else {
                     this.errorMessage = "Nano config: Key bindings can not be changed!";
                  }
               } else {
                  String option = (String)parts.get(1);
                  boolean val = ((String)parts.get(0)).equals("set");
                  if (option.equals("linenumbers")) {
                     this.printLineNumbers = val;
                  } else if (option.equals("jumpyscrolling")) {
                     this.smoothScrolling = !val;
                  } else if (option.equals("smooth")) {
                     this.smoothScrolling = val;
                  } else if (option.equals("softwrap")) {
                     this.wrapping = val;
                  } else if (option.equals("mouse")) {
                     this.mouseSupport = val;
                  } else if (option.equals("emptyline")) {
                     this.oneMoreLine = val;
                  } else if (option.equals("morespace")) {
                     this.oneMoreLine = !val;
                  } else if (option.equals("constantshow")) {
                     this.constantCursor = val;
                  } else if (option.equals("quickblank")) {
                     this.quickBlank = val;
                  } else if (option.equals("atblanks")) {
                     this.atBlanks = val;
                  } else if (option.equals("suspend")) {
                     this.enableSuspension();
                  } else if (option.equals("view")) {
                     this.view = val;
                  } else if (option.equals("cutfromcursor")) {
                     this.cut2end = val;
                  } else if (option.equals("tempfile")) {
                     this.tempFile = val;
                  } else if (option.equals("tabstospaces")) {
                     this.tabsToSpaces = val;
                  } else if (option.equals("autoindent")) {
                     this.autoIndent = val;
                  } else {
                     this.errorMessage = "Nano config: Unknown or unsupported configuration option " + option;
                  }
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

   public void setRestricted(boolean restricted) {
      this.restricted = restricted;
   }

   public void open(String... files) throws IOException {
      this.open(Arrays.asList(files));
   }

   public void open(List files) throws IOException {
      for(String file : files) {
         file = file.startsWith("~") ? file.replace("~", System.getProperty("user.home")) : file;
         if (!file.contains("*") && !file.contains("?")) {
            this.buffers.add(new Buffer(file));
         } else {
            for(Path p : Commands.findFiles(this.root, file)) {
               this.buffers.add(new Buffer(p.toString()));
            }
         }
      }

   }

   public void run() throws IOException {
      if (this.buffers.isEmpty()) {
         this.buffers.add(new Buffer((String)null));
      }

      this.buffer = (Buffer)this.buffers.get(this.bufferIndex);
      Attributes attributes = this.terminal.getAttributes();
      Attributes newAttr = new Attributes(attributes);
      if (this.vsusp > 0) {
         attributes.setControlChar(Attributes.ControlChar.VSUSP, this.vsusp);
      }

      newAttr.setLocalFlags(EnumSet.of(Attributes.LocalFlag.ICANON, Attributes.LocalFlag.ECHO, Attributes.LocalFlag.IEXTEN), false);
      newAttr.setInputFlags(EnumSet.of(Attributes.InputFlag.IXON, Attributes.InputFlag.ICRNL, Attributes.InputFlag.INLCR), false);
      newAttr.setControlChar(Attributes.ControlChar.VMIN, 1);
      newAttr.setControlChar(Attributes.ControlChar.VTIME, 0);
      newAttr.setControlChar(Attributes.ControlChar.VINTR, 0);
      this.terminal.setAttributes(newAttr);
      this.terminal.puts(InfoCmp.Capability.enter_ca_mode);
      this.terminal.puts(InfoCmp.Capability.keypad_xmit);
      if (this.mouseSupport) {
         this.terminal.trackMouse(Terminal.MouseTracking.Normal);
      }

      this.shortcuts = this.standardShortcuts();
      Terminal.SignalHandler prevHandler = null;
      Status status = Status.getStatus(this.terminal, false);

      try {
         this.size.copy(this.terminal.getSize());
         if (status != null) {
            status.suspend();
         }

         this.buffer.open();
         if (this.errorMessage != null) {
            this.setMessage(this.errorMessage);
            this.errorMessage = null;
         } else if (this.buffer.file != null) {
            this.setMessage("Read " + this.buffer.lines.size() + " lines");
         }

         this.display.clear();
         this.display.reset();
         this.display.resize(this.size.getRows(), this.size.getColumns());
         prevHandler = this.terminal.handle(Terminal.Signal.WINCH, this::handle);
         this.display();

         while(true) {
            Operation op;
            switch ((op = this.readOperation(this.keys)).ordinal()) {
               case 1:
                  if (this.quit()) {
                     return;
                  }
                  break;
               case 2:
                  this.write();
                  break;
               case 3:
                  this.read();
                  break;
               case 4:
                  this.gotoLine();
                  this.curPos();
                  break;
               case 5:
               case 32:
               case 33:
               case 34:
               case 35:
               case 36:
               case 38:
               case 39:
               case 40:
               case 41:
               case 42:
               case 43:
               case 44:
               case 45:
               case 46:
               case 47:
               case 48:
               case 49:
               case 54:
               case 55:
               case 60:
               case 61:
               case 62:
               case 63:
               case 65:
               case 66:
               case 68:
               case 70:
               default:
                  this.setMessage("Unsupported " + op.name().toLowerCase().replace('_', '-'));
                  break;
               case 6:
                  this.wrap();
                  break;
               case 7:
                  this.numbers();
                  break;
               case 8:
                  this.smoothScrolling();
                  break;
               case 9:
                  this.mouseSupport();
                  break;
               case 10:
                  this.oneMoreLine();
                  break;
               case 11:
                  this.clearScreen();
                  break;
               case 12:
                  this.buffer.moveUp(1);
                  break;
               case 13:
                  this.buffer.moveDown(1);
                  break;
               case 14:
                  this.buffer.moveLeft(1);
                  break;
               case 15:
                  this.buffer.moveRight(1);
                  break;
               case 16:
                  this.buffer.insert(this.bindingReader.getLastBinding());
                  break;
               case 17:
                  this.buffer.backspace(1);
                  break;
               case 18:
                  this.nextBuffer();
                  break;
               case 19:
                  this.prevBuffer();
                  break;
               case 20:
                  this.help("nano-main-help.txt");
                  break;
               case 21:
                  this.buffer.nextPage();
                  break;
               case 22:
                  this.buffer.prevPage();
                  break;
               case 23:
                  this.buffer.scrollUp(1);
                  break;
               case 24:
                  this.buffer.scrollDown(1);
                  break;
               case 25:
                  this.buffer.nextWord();
                  break;
               case 26:
                  this.buffer.prevWord();
                  break;
               case 27:
                  this.buffer.beginningOfLine();
                  break;
               case 28:
                  this.buffer.endOfLine();
                  break;
               case 29:
                  this.buffer.firstLine();
                  break;
               case 30:
                  this.buffer.lastLine();
                  break;
               case 31:
                  this.curPos();
                  break;
               case 37:
                  this.searchToReplace = false;
                  this.searchAndReplace();
                  break;
               case 50:
                  this.buffer.nextSearch();
                  break;
               case 51:
                  this.buffer.matching();
                  break;
               case 52:
                  this.buffer.insert(new String(Character.toChars(this.bindingReader.readCharacter())));
                  break;
               case 53:
                  this.buffer.delete(1);
                  break;
               case 56:
                  this.buffer.cut();
                  break;
               case 57:
                  this.searchToReplace = true;
                  this.searchAndReplace();
                  break;
               case 58:
                  this.mark = !this.mark;
                  this.setMessage("Mark " + (this.mark ? "Set" : "Unset"));
                  this.buffer.mark();
                  break;
               case 59:
                  this.buffer.copy();
                  break;
               case 64:
                  this.buffer.cut(true);
                  break;
               case 67:
                  this.constantCursor();
                  break;
               case 69:
                  this.highlight = !this.highlight;
                  this.setMessage("Highlight " + (this.highlight ? "enabled" : "disabled"));
                  break;
               case 71:
                  this.autoIndent = !this.autoIndent;
                  this.setMessage("Auto indent " + (this.autoIndent ? "enabled" : "disabled"));
                  break;
               case 72:
                  this.cut2end = !this.cut2end;
                  this.setMessage("Cut to end " + (this.cut2end ? "enabled" : "disabled"));
                  break;
               case 73:
                  this.tabsToSpaces = !this.tabsToSpaces;
                  this.setMessage("Conversion of typed tabs to spaces " + (this.tabsToSpaces ? "enabled" : "disabled"));
                  break;
               case 74:
                  this.buffer.uncut();
                  break;
               case 75:
                  this.mouseEvent();
                  break;
               case 76:
                  this.toggleSuspension();
            }

            this.display();
         }
      } finally {
         if (this.mouseSupport) {
            this.terminal.trackMouse(Terminal.MouseTracking.Off);
         }

         if (!this.terminal.puts(InfoCmp.Capability.exit_ca_mode)) {
            this.terminal.puts(InfoCmp.Capability.clear_screen);
         }

         this.terminal.puts(InfoCmp.Capability.keypad_local);
         this.terminal.flush();
         this.terminal.setAttributes(attributes);
         this.terminal.handle(Terminal.Signal.WINCH, prevHandler);
         if (status != null) {
            status.restore();
         }

         this.patternHistory.persist();
      }
   }

   private int editInputBuffer(Operation operation, int curPos) {
      switch (operation.ordinal()) {
         case 14:
            if (curPos > 0) {
               --curPos;
            }
            break;
         case 15:
            if (curPos < this.editBuffer.length()) {
               ++curPos;
            }
            break;
         case 16:
            this.editBuffer.insert(curPos++, this.bindingReader.getLastBinding());
            break;
         case 17:
            if (curPos > 0) {
               --curPos;
               this.editBuffer.deleteCharAt(curPos);
            }
      }

      return curPos;
   }

   boolean write() throws IOException {
      KeyMap<Operation> writeKeyMap = new KeyMap();
      if (!this.restricted) {
         writeKeyMap.setUnicode(Nano.Operation.INSERT);

         for(char i = ' '; i < 256; ++i) {
            writeKeyMap.bind(Nano.Operation.INSERT, (CharSequence)Character.toString(i));
         }

         for(char i = 'A'; i <= 'Z'; ++i) {
            writeKeyMap.bind(Nano.Operation.DO_LOWER_CASE, (CharSequence)KeyMap.alt(i));
         }

         writeKeyMap.bind(Nano.Operation.BACKSPACE, (CharSequence)KeyMap.del());
         writeKeyMap.bind(Nano.Operation.APPEND_MODE, (CharSequence)KeyMap.alt('a'));
         writeKeyMap.bind(Nano.Operation.PREPEND_MODE, (CharSequence)KeyMap.alt('p'));
         writeKeyMap.bind(Nano.Operation.BACKUP, (CharSequence)KeyMap.alt('b'));
         writeKeyMap.bind(Nano.Operation.TO_FILES, (CharSequence)KeyMap.ctrl('T'));
      }

      writeKeyMap.bind(Nano.Operation.MAC_FORMAT, (CharSequence)KeyMap.alt('m'));
      writeKeyMap.bind(Nano.Operation.DOS_FORMAT, (CharSequence)KeyMap.alt('d'));
      writeKeyMap.bind(Nano.Operation.ACCEPT, (CharSequence)"\r");
      writeKeyMap.bind(Nano.Operation.CANCEL, (CharSequence)KeyMap.ctrl('C'));
      writeKeyMap.bind(Nano.Operation.HELP, (CharSequence[])(KeyMap.ctrl('G'), KeyMap.key(this.terminal, InfoCmp.Capability.key_f1)));
      writeKeyMap.bind(Nano.Operation.MOUSE_EVENT, (CharSequence)KeyMap.key(this.terminal, InfoCmp.Capability.key_mouse));
      writeKeyMap.bind(Nano.Operation.TOGGLE_SUSPENSION, (CharSequence)KeyMap.alt('z'));
      writeKeyMap.bind(Nano.Operation.RIGHT, (CharSequence)KeyMap.key(this.terminal, InfoCmp.Capability.key_right));
      writeKeyMap.bind(Nano.Operation.LEFT, (CharSequence)KeyMap.key(this.terminal, InfoCmp.Capability.key_left));
      this.editMessage = this.getWriteMessage();
      this.editBuffer.setLength(0);
      this.editBuffer.append(this.buffer.file == null ? "" : this.buffer.file);
      int curPos = this.editBuffer.length();
      this.shortcuts = this.writeShortcuts();
      this.display(curPos);

      while(true) {
         Operation op = this.readOperation(writeKeyMap);
         switch (op.ordinal()) {
            case 20:
               this.help("nano-write-help.txt");
               break;
            case 35:
               this.editMessage = null;
               if (this.save(this.editBuffer.toString())) {
                  this.shortcuts = this.standardShortcuts();
                  return true;
               }

               return false;
            case 36:
               this.editMessage = null;
               this.shortcuts = this.standardShortcuts();
               return false;
            case 39:
               this.buffer.format = this.buffer.format == Nano.WriteFormat.MAC ? Nano.WriteFormat.UNIX : Nano.WriteFormat.MAC;
               break;
            case 40:
               this.buffer.format = this.buffer.format == Nano.WriteFormat.DOS ? Nano.WriteFormat.UNIX : Nano.WriteFormat.DOS;
               break;
            case 41:
               this.writeMode = this.writeMode == Nano.WriteMode.APPEND ? Nano.WriteMode.WRITE : Nano.WriteMode.APPEND;
               break;
            case 42:
               this.writeMode = this.writeMode == Nano.WriteMode.PREPEND ? Nano.WriteMode.WRITE : Nano.WriteMode.PREPEND;
               break;
            case 43:
               this.writeBackup = !this.writeBackup;
               break;
            case 75:
               this.mouseEvent();
               break;
            case 76:
               this.toggleSuspension();
               break;
            default:
               curPos = this.editInputBuffer(op, curPos);
         }

         this.editMessage = this.getWriteMessage();
         this.display(curPos);
      }
   }

   private Operation readOperation(KeyMap keymap) {
      while(true) {
         Operation op = (Operation)this.bindingReader.readBinding(keymap);
         if (op != Nano.Operation.DO_LOWER_CASE) {
            return op;
         }

         this.bindingReader.runMacro(this.bindingReader.getLastBinding().toLowerCase());
      }
   }

   private boolean save(String name) throws IOException {
      Path orgPath = this.buffer.file != null ? this.root.resolve(this.buffer.file) : null;
      Path newPath = this.root.resolve(name);
      boolean isSame = orgPath != null && Files.exists(orgPath, new LinkOption[0]) && Files.exists(newPath, new LinkOption[0]) && Files.isSameFile(orgPath, newPath);
      if (!isSame && Files.exists(Paths.get(name), new LinkOption[0]) && this.writeMode == Nano.WriteMode.WRITE) {
         Operation op = this.getYNC("File exists, OVERWRITE ? ");
         if (op != Nano.Operation.YES) {
            return false;
         }
      } else if (!Files.exists(newPath, new LinkOption[0])) {
         newPath.toFile().createNewFile();
      }

      Path t = Files.createTempFile("jline-", ".temp");

      boolean w;
      try {
         OutputStream os = Files.newOutputStream(t, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);

         boolean var20;
         try {
            if (this.writeMode == Nano.WriteMode.APPEND && Files.isReadable(newPath)) {
               Files.copy(newPath, os);
            }

            Writer w = new OutputStreamWriter(os, this.buffer.charset);

            for(int i = 0; i < this.buffer.lines.size(); ++i) {
               w.write((String)this.buffer.lines.get(i));
               switch (this.buffer.format.ordinal()) {
                  case 0:
                     w.write("\n");
                     break;
                  case 1:
                     w.write("\r\n");
                     break;
                  case 2:
                     w.write("\r");
               }
            }

            w.flush();
            if (this.writeMode == Nano.WriteMode.PREPEND && Files.isReadable(newPath)) {
               Files.copy(newPath, os);
            }

            if (this.writeBackup) {
               Files.move(newPath, newPath.resolveSibling(newPath.getFileName().toString() + "~"), StandardCopyOption.REPLACE_EXISTING);
            }

            Files.move(t, newPath, StandardCopyOption.REPLACE_EXISTING);
            if (this.writeMode == Nano.WriteMode.WRITE) {
               this.buffer.file = name;
               this.buffer.dirty = false;
            }

            this.setMessage("Wrote " + this.buffer.lines.size() + " lines");
            var20 = true;
         } catch (Throwable var15) {
            if (os != null) {
               try {
                  os.close();
               } catch (Throwable var14) {
                  var15.addSuppressed(var14);
               }
            }

            throw var15;
         }

         if (os != null) {
            os.close();
         }

         return var20;
      } catch (IOException e) {
         this.setMessage("Error writing " + name + ": " + e.toString());
         w = false;
      } finally {
         Files.deleteIfExists(t);
         this.writeMode = Nano.WriteMode.WRITE;
      }

      return w;
   }

   private Operation getYNC(String message) {
      return this.getYNC(message, false);
   }

   private Operation getYNC(String message, boolean andAll) {
      String oldEditMessage = this.editMessage;
      String oldEditBuffer = this.editBuffer.toString();
      LinkedHashMap<String, String> oldShortcuts = this.shortcuts;

      Operation var7;
      try {
         this.editMessage = message;
         this.editBuffer.setLength(0);
         KeyMap<Operation> yncKeyMap = new KeyMap();
         yncKeyMap.bind(Nano.Operation.YES, (CharSequence[])("y", "Y"));
         if (andAll) {
            yncKeyMap.bind(Nano.Operation.ALL, (CharSequence[])("a", "A"));
         }

         yncKeyMap.bind(Nano.Operation.NO, (CharSequence[])("n", "N"));
         yncKeyMap.bind(Nano.Operation.CANCEL, (CharSequence)KeyMap.ctrl('C'));
         this.shortcuts = new LinkedHashMap();
         this.shortcuts.put(" Y", "Yes");
         if (andAll) {
            this.shortcuts.put(" A", "All");
         }

         this.shortcuts.put(" N", "No");
         this.shortcuts.put("^C", "Cancel");
         this.display();
         var7 = this.readOperation(yncKeyMap);
      } finally {
         this.editMessage = oldEditMessage;
         this.editBuffer.append(oldEditBuffer);
         this.shortcuts = oldShortcuts;
      }

      return var7;
   }

   private String getWriteMessage() {
      StringBuilder sb = new StringBuilder();
      sb.append("File Name to ");
      switch (this.writeMode.ordinal()) {
         case 0:
            sb.append("Write");
            break;
         case 1:
            sb.append("Append");
            break;
         case 2:
            sb.append("Prepend");
      }

      switch (this.buffer.format.ordinal()) {
         case 0:
         default:
            break;
         case 1:
            sb.append(" [DOS Format]");
            break;
         case 2:
            sb.append(" [Mac Format]");
      }

      if (this.writeBackup) {
         sb.append(" [Backup]");
      }

      sb.append(": ");
      return sb.toString();
   }

   void read() {
      KeyMap<Operation> readKeyMap = new KeyMap();
      readKeyMap.setUnicode(Nano.Operation.INSERT);

      for(char i = ' '; i < 256; ++i) {
         readKeyMap.bind(Nano.Operation.INSERT, (CharSequence)Character.toString(i));
      }

      for(char i = 'A'; i <= 'Z'; ++i) {
         readKeyMap.bind(Nano.Operation.DO_LOWER_CASE, (CharSequence)KeyMap.alt(i));
      }

      readKeyMap.bind(Nano.Operation.BACKSPACE, (CharSequence)KeyMap.del());
      readKeyMap.bind(Nano.Operation.NEW_BUFFER, (CharSequence)KeyMap.alt('f'));
      readKeyMap.bind(Nano.Operation.TO_FILES, (CharSequence)KeyMap.ctrl('T'));
      readKeyMap.bind(Nano.Operation.EXECUTE, (CharSequence)KeyMap.ctrl('X'));
      readKeyMap.bind(Nano.Operation.ACCEPT, (CharSequence)"\r");
      readKeyMap.bind(Nano.Operation.CANCEL, (CharSequence)KeyMap.ctrl('C'));
      readKeyMap.bind(Nano.Operation.HELP, (CharSequence[])(KeyMap.ctrl('G'), KeyMap.key(this.terminal, InfoCmp.Capability.key_f1)));
      readKeyMap.bind(Nano.Operation.MOUSE_EVENT, (CharSequence)KeyMap.key(this.terminal, InfoCmp.Capability.key_mouse));
      readKeyMap.bind(Nano.Operation.RIGHT, (CharSequence)KeyMap.key(this.terminal, InfoCmp.Capability.key_right));
      readKeyMap.bind(Nano.Operation.LEFT, (CharSequence)KeyMap.key(this.terminal, InfoCmp.Capability.key_left));
      this.editMessage = this.getReadMessage();
      this.editBuffer.setLength(0);
      int curPos = this.editBuffer.length();
      this.shortcuts = this.readShortcuts();
      this.display(curPos);

      while(true) {
         Operation op = this.readOperation(readKeyMap);
         switch (op.ordinal()) {
            case 20:
               this.help("nano-read-help.txt");
               break;
            case 35:
               this.editMessage = null;
               String file = this.editBuffer.toString();
               boolean empty = file.isEmpty();
               Path p = empty ? null : this.root.resolve(file);
               if (!this.readNewBuffer && !empty && !Files.exists(p, new LinkOption[0])) {
                  this.setMessage("\"" + file + "\" not found");
               } else if (!empty && Files.isDirectory(p, new LinkOption[0])) {
                  this.setMessage("\"" + file + "\" is a directory");
               } else if (!empty && !Files.isRegularFile(p, new LinkOption[0])) {
                  this.setMessage("\"" + file + "\" is not a regular file");
               } else {
                  Buffer buf = new Buffer(empty ? null : file);

                  try {
                     buf.open();
                     if (this.readNewBuffer) {
                        this.buffers.add(++this.bufferIndex, buf);
                        this.buffer = buf;
                     } else {
                        this.buffer.insert(String.join("\n", buf.lines));
                     }

                     this.setMessage((String)null);
                  } catch (IOException e) {
                     this.setMessage("Error reading " + file + ": " + e.getMessage());
                  }
               }

               this.shortcuts = this.standardShortcuts();
               return;
            case 36:
               this.editMessage = null;
               this.shortcuts = this.standardShortcuts();
               return;
            case 48:
               this.readNewBuffer = !this.readNewBuffer;
               break;
            case 75:
               this.mouseEvent();
               break;
            default:
               curPos = this.editInputBuffer(op, curPos);
         }

         this.editMessage = this.getReadMessage();
         this.display(curPos);
      }
   }

   private String getReadMessage() {
      StringBuilder sb = new StringBuilder();
      sb.append("File to insert");
      if (this.readNewBuffer) {
         sb.append(" into new buffer");
      }

      sb.append(" [from ./]: ");
      return sb.toString();
   }

   void gotoLine() throws IOException {
      KeyMap<Operation> readKeyMap = new KeyMap();
      readKeyMap.setUnicode(Nano.Operation.INSERT);

      for(char i = ' '; i < 256; ++i) {
         readKeyMap.bind(Nano.Operation.INSERT, (CharSequence)Character.toString(i));
      }

      readKeyMap.bind(Nano.Operation.BACKSPACE, (CharSequence)KeyMap.del());
      readKeyMap.bind(Nano.Operation.ACCEPT, (CharSequence)"\r");
      readKeyMap.bind(Nano.Operation.HELP, (CharSequence[])(KeyMap.ctrl('G'), KeyMap.key(this.terminal, InfoCmp.Capability.key_f1)));
      readKeyMap.bind(Nano.Operation.CANCEL, (CharSequence)KeyMap.ctrl('C'));
      readKeyMap.bind(Nano.Operation.RIGHT, (CharSequence)KeyMap.key(this.terminal, InfoCmp.Capability.key_right));
      readKeyMap.bind(Nano.Operation.LEFT, (CharSequence)KeyMap.key(this.terminal, InfoCmp.Capability.key_left));
      readKeyMap.bind(Nano.Operation.FIRST_LINE, (CharSequence)KeyMap.ctrl('Y'));
      readKeyMap.bind(Nano.Operation.LAST_LINE, (CharSequence)KeyMap.ctrl('V'));
      readKeyMap.bind(Nano.Operation.SEARCH, (CharSequence)KeyMap.ctrl('T'));
      this.editMessage = "Enter line number, column number: ";
      this.editBuffer.setLength(0);
      int curPos = this.editBuffer.length();
      this.shortcuts = this.gotoShortcuts();
      this.display(curPos);

      while(true) {
         Operation op = this.readOperation(readKeyMap);
         switch (op.ordinal()) {
            case 20:
               this.help("nano-goto-help.txt");
               break;
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 31:
            case 32:
            case 33:
            case 34:
            default:
               curPos = this.editInputBuffer(op, curPos);
               break;
            case 29:
               this.editMessage = null;
               this.buffer.firstLine();
               this.shortcuts = this.standardShortcuts();
               return;
            case 30:
               this.editMessage = null;
               this.buffer.lastLine();
               this.shortcuts = this.standardShortcuts();
               return;
            case 35:
               this.editMessage = null;
               String[] pos = this.editBuffer.toString().split(",", 2);
               int[] args = new int[]{0, 0};

               try {
                  for(int i = 0; i < pos.length; ++i) {
                     if (pos[i].trim().length() > 0) {
                        args[i] = Integer.parseInt(pos[i]) - 1;
                        if (args[i] < 0) {
                           throw new NumberFormatException();
                        }
                     }
                  }

                  this.buffer.gotoLine(args[1], args[0]);
               } catch (NumberFormatException var7) {
                  this.setMessage("Invalid line or column number");
               } catch (Exception ex) {
                  this.setMessage("Internal error: " + ex.getMessage());
               }

               this.shortcuts = this.standardShortcuts();
               return;
            case 36:
               this.editMessage = null;
               this.shortcuts = this.standardShortcuts();
               return;
            case 37:
               this.searchToReplace = false;
               this.searchAndReplace();
               return;
         }

         this.display(curPos);
      }
   }

   private LinkedHashMap gotoShortcuts() {
      LinkedHashMap<String, String> shortcuts = new LinkedHashMap();
      shortcuts.put("^G", "Get Help");
      shortcuts.put("^Y", "First Line");
      shortcuts.put("^T", "Go To Text");
      shortcuts.put("^C", "Cancel");
      shortcuts.put("^V", "Last Line");
      return shortcuts;
   }

   private LinkedHashMap readShortcuts() {
      LinkedHashMap<String, String> shortcuts = new LinkedHashMap();
      shortcuts.put("^G", "Get Help");
      shortcuts.put("^T", "To Files");
      shortcuts.put("M-F", "New Buffer");
      shortcuts.put("^C", "Cancel");
      shortcuts.put("^X", "Execute Command");
      return shortcuts;
   }

   private LinkedHashMap writeShortcuts() {
      LinkedHashMap<String, String> s = new LinkedHashMap();
      s.put("^G", "Get Help");
      s.put("M-M", "Mac Format");
      s.put("^C", "Cancel");
      s.put("M-D", "DOS Format");
      if (!this.restricted) {
         s.put("^T", "To Files");
         s.put("M-P", "Prepend");
         s.put("M-A", "Append");
         s.put("M-B", "Backup File");
      }

      return s;
   }

   private LinkedHashMap helpShortcuts() {
      LinkedHashMap<String, String> s = new LinkedHashMap();
      s.put("^L", "Refresh");
      s.put("^Y", "Prev Page");
      s.put("^P", "Prev Line");
      s.put("M-\\", "First Line");
      s.put("^X", "Exit");
      s.put("^V", "Next Page");
      s.put("^N", "Next Line");
      s.put("M-/", "Last Line");
      return s;
   }

   private LinkedHashMap searchShortcuts() {
      LinkedHashMap<String, String> s = new LinkedHashMap();
      s.put("^G", "Get Help");
      s.put("^Y", "First Line");
      if (this.searchToReplace) {
         s.put("^R", "No Replace");
      } else {
         s.put("^R", "Replace");
         s.put("^W", "Beg of Par");
      }

      s.put("M-C", "Case Sens");
      s.put("M-R", "Regexp");
      s.put("^C", "Cancel");
      s.put("^V", "Last Line");
      s.put("^T", "Go To Line");
      if (!this.searchToReplace) {
         s.put("^O", "End of Par");
      }

      s.put("M-B", "Backwards");
      s.put("^P", "PrevHstory");
      return s;
   }

   private LinkedHashMap replaceShortcuts() {
      LinkedHashMap<String, String> s = new LinkedHashMap();
      s.put("^G", "Get Help");
      s.put("^Y", "First Line");
      s.put("^P", "PrevHstory");
      s.put("^C", "Cancel");
      s.put("^V", "Last Line");
      s.put("^N", "NextHstory");
      return s;
   }

   private LinkedHashMap standardShortcuts() {
      LinkedHashMap<String, String> s = new LinkedHashMap();
      s.put("^G", "Get Help");
      if (!this.view) {
         s.put("^O", "WriteOut");
      }

      s.put("^R", "Read File");
      s.put("^Y", "Prev Page");
      if (!this.view) {
         s.put("^K", "Cut Text");
      }

      s.put("^C", "Cur Pos");
      s.put("^X", "Exit");
      if (!this.view) {
         s.put("^J", "Justify");
      }

      s.put("^W", "Where Is");
      s.put("^V", "Next Page");
      if (!this.view) {
         s.put("^U", "UnCut Text");
      }

      s.put("^T", "To Spell");
      return s;
   }

   void help(String help) {
      Buffer org = this.buffer;
      Buffer newBuf = new Buffer((String)null);

      try {
         InputStream is = this.getClass().getResourceAsStream(help);

         try {
            newBuf.open(is);
         } catch (Throwable var17) {
            if (is != null) {
               try {
                  is.close();
               } catch (Throwable var16) {
                  var17.addSuppressed(var16);
               }
            }

            throw var17;
         }

         if (is != null) {
            is.close();
         }
      } catch (IOException var18) {
         this.setMessage("Unable to read help");
         return;
      }

      LinkedHashMap<String, String> oldShortcuts = this.shortcuts;
      this.shortcuts = this.helpShortcuts();
      boolean oldWrapping = this.wrapping;
      boolean oldPrintLineNumbers = this.printLineNumbers;
      boolean oldConstantCursor = this.constantCursor;
      boolean oldAtBlanks = this.atBlanks;
      boolean oldHighlight = this.highlight;
      String oldEditMessage = this.editMessage;
      this.editMessage = "";
      this.wrapping = true;
      this.atBlanks = true;
      this.printLineNumbers = false;
      this.constantCursor = false;
      this.highlight = false;
      this.buffer = newBuf;
      if (!oldWrapping) {
         this.buffer.computeAllOffsets();
      }

      try {
         this.message = null;
         this.terminal.puts(InfoCmp.Capability.cursor_invisible);
         this.display();

         while(true) {
            switch (this.readOperation(this.keys).ordinal()) {
               case 1:
                  return;
               case 11:
                  this.clearScreen();
                  break;
               case 12:
                  this.buffer.scrollUp(1);
                  break;
               case 13:
                  this.buffer.scrollDown(1);
                  break;
               case 21:
                  this.buffer.nextPage();
                  break;
               case 22:
                  this.buffer.prevPage();
                  break;
               case 29:
                  this.buffer.firstLine();
                  break;
               case 30:
                  this.buffer.lastLine();
                  break;
               case 75:
                  this.mouseEvent();
                  break;
               case 76:
                  this.toggleSuspension();
            }

            this.display();
         }
      } finally {
         this.buffer = org;
         this.wrapping = oldWrapping;
         this.printLineNumbers = oldPrintLineNumbers;
         this.constantCursor = oldConstantCursor;
         this.shortcuts = oldShortcuts;
         this.atBlanks = oldAtBlanks;
         this.highlight = oldHighlight;
         this.editMessage = oldEditMessage;
         this.terminal.puts(InfoCmp.Capability.cursor_visible);
         if (!oldWrapping) {
            this.buffer.computeAllOffsets();
         }

      }
   }

   void searchAndReplace() {
      try {
         this.search();
         if (this.searchToReplace) {
            String replaceTerm = this.replace();
            int replaced = 0;
            boolean all = false;
            boolean found = true;
            List<Integer> matches = new ArrayList();
            Operation op = Nano.Operation.NO;

            while(found) {
               found = this.buffer.nextSearch();
               if (found) {
                  int[] re = this.buffer.highlightStart();
                  int col = this.searchBackwards ? this.buffer.length(this.buffer.getLine(re[0])) - re[1] : re[1];
                  int match = re[0] * 10000 + col;
                  if (matches.contains(match)) {
                     break;
                  }

                  matches.add(match);
                  if (!all) {
                     op = this.getYNC("Replace this instance? ", true);
                  }
               } else {
                  op = Nano.Operation.NO;
               }

               switch (op.ordinal()) {
                  case 36:
                     found = false;
                     break;
                  case 45:
                     this.buffer.replaceFromCursor(this.matchedLength, replaceTerm);
                     ++replaced;
                  case 46:
                  default:
                     break;
                  case 47:
                     all = true;
                     this.buffer.replaceFromCursor(this.matchedLength, replaceTerm);
                     ++replaced;
               }
            }

            this.message = "Replaced " + replaced + " occurrences";
            return;
         }
      } catch (Exception var13) {
         return;
      } finally {
         this.searchToReplace = false;
         this.matchedLength = -1;
         this.shortcuts = this.standardShortcuts();
         this.editMessage = null;
      }

   }

   void search() throws IOException {
      KeyMap<Operation> searchKeyMap = new KeyMap();
      searchKeyMap.setUnicode(Nano.Operation.INSERT);

      for(char i = ' '; i < 256; ++i) {
         searchKeyMap.bind(Nano.Operation.INSERT, (CharSequence)Character.toString(i));
      }

      for(char i = 'A'; i <= 'Z'; ++i) {
         searchKeyMap.bind(Nano.Operation.DO_LOWER_CASE, (CharSequence)KeyMap.alt(i));
      }

      searchKeyMap.bind(Nano.Operation.BACKSPACE, (CharSequence)KeyMap.del());
      searchKeyMap.bind(Nano.Operation.CASE_SENSITIVE, (CharSequence)KeyMap.alt('c'));
      searchKeyMap.bind(Nano.Operation.BACKWARDS, (CharSequence)KeyMap.alt('b'));
      searchKeyMap.bind(Nano.Operation.REGEXP, (CharSequence)KeyMap.alt('r'));
      searchKeyMap.bind(Nano.Operation.ACCEPT, (CharSequence)"\r");
      searchKeyMap.bind(Nano.Operation.CANCEL, (CharSequence)KeyMap.ctrl('C'));
      searchKeyMap.bind(Nano.Operation.HELP, (CharSequence[])(KeyMap.ctrl('G'), KeyMap.key(this.terminal, InfoCmp.Capability.key_f1)));
      searchKeyMap.bind(Nano.Operation.FIRST_LINE, (CharSequence)KeyMap.ctrl('Y'));
      searchKeyMap.bind(Nano.Operation.LAST_LINE, (CharSequence)KeyMap.ctrl('V'));
      searchKeyMap.bind(Nano.Operation.MOUSE_EVENT, (CharSequence)KeyMap.key(this.terminal, InfoCmp.Capability.key_mouse));
      searchKeyMap.bind(Nano.Operation.RIGHT, (CharSequence)KeyMap.key(this.terminal, InfoCmp.Capability.key_right));
      searchKeyMap.bind(Nano.Operation.LEFT, (CharSequence)KeyMap.key(this.terminal, InfoCmp.Capability.key_left));
      searchKeyMap.bind(Nano.Operation.UP, (CharSequence)KeyMap.key(this.terminal, InfoCmp.Capability.key_up));
      searchKeyMap.bind(Nano.Operation.DOWN, (CharSequence)KeyMap.key(this.terminal, InfoCmp.Capability.key_down));
      searchKeyMap.bind(Nano.Operation.TOGGLE_REPLACE, (CharSequence)KeyMap.ctrl('R'));
      this.editMessage = this.getSearchMessage();
      this.editBuffer.setLength(0);
      String currentBuffer = this.editBuffer.toString();
      int curPos = this.editBuffer.length();
      this.shortcuts = this.searchShortcuts();
      this.display(curPos);

      try {
         label154:
         while(true) {
            Operation op = this.readOperation(searchKeyMap);
            switch (op.ordinal()) {
               case 12:
                  this.editBuffer.setLength(0);
                  this.editBuffer.append(this.patternHistory.up(currentBuffer));
                  curPos = this.editBuffer.length();
                  break;
               case 13:
                  this.editBuffer.setLength(0);
                  this.editBuffer.append(this.patternHistory.down(currentBuffer));
                  curPos = this.editBuffer.length();
                  break;
               case 20:
                  if (this.searchToReplace) {
                     this.help("nano-search-replace-help.txt");
                  } else {
                     this.help("nano-search-help.txt");
                  }
                  break;
               case 29:
                  this.buffer.firstLine();
                  break;
               case 30:
                  this.buffer.lastLine();
                  break;
               case 32:
                  this.searchCaseSensitive = !this.searchCaseSensitive;
                  break;
               case 33:
                  this.searchBackwards = !this.searchBackwards;
                  break;
               case 34:
                  this.searchRegexp = !this.searchRegexp;
                  break;
               case 35:
                  if (this.editBuffer.length() > 0) {
                     this.searchTerm = this.editBuffer.toString();
                  }
                  break label154;
               case 36:
                  throw new IllegalArgumentException();
               case 38:
                  this.searchToReplace = !this.searchToReplace;
                  this.shortcuts = this.searchShortcuts();
                  break;
               case 75:
                  this.mouseEvent();
                  break;
               default:
                  curPos = this.editInputBuffer(op, curPos);
                  currentBuffer = this.editBuffer.toString();
            }

            this.editMessage = this.getSearchMessage();
            this.display(curPos);
         }

         if (this.searchTerm == null || this.searchTerm.isEmpty()) {
            this.setMessage("Cancelled");
            throw new IllegalArgumentException();
         }

         this.patternHistory.add(this.searchTerm);
         if (!this.searchToReplace) {
            this.buffer.nextSearch();
         }
      } finally {
         this.shortcuts = this.standardShortcuts();
         this.editMessage = null;
      }

   }

   String replace() throws IOException {
      KeyMap<Operation> keyMap = new KeyMap();
      keyMap.setUnicode(Nano.Operation.INSERT);

      for(char i = ' '; i < 256; ++i) {
         keyMap.bind(Nano.Operation.INSERT, (CharSequence)Character.toString(i));
      }

      for(char i = 'A'; i <= 'Z'; ++i) {
         keyMap.bind(Nano.Operation.DO_LOWER_CASE, (CharSequence)KeyMap.alt(i));
      }

      keyMap.bind(Nano.Operation.BACKSPACE, (CharSequence)KeyMap.del());
      keyMap.bind(Nano.Operation.ACCEPT, (CharSequence)"\r");
      keyMap.bind(Nano.Operation.CANCEL, (CharSequence)KeyMap.ctrl('C'));
      keyMap.bind(Nano.Operation.HELP, (CharSequence[])(KeyMap.ctrl('G'), KeyMap.key(this.terminal, InfoCmp.Capability.key_f1)));
      keyMap.bind(Nano.Operation.FIRST_LINE, (CharSequence)KeyMap.ctrl('Y'));
      keyMap.bind(Nano.Operation.LAST_LINE, (CharSequence)KeyMap.ctrl('V'));
      keyMap.bind(Nano.Operation.RIGHT, (CharSequence)KeyMap.key(this.terminal, InfoCmp.Capability.key_right));
      keyMap.bind(Nano.Operation.LEFT, (CharSequence)KeyMap.key(this.terminal, InfoCmp.Capability.key_left));
      keyMap.bind(Nano.Operation.UP, (CharSequence)KeyMap.key(this.terminal, InfoCmp.Capability.key_up));
      keyMap.bind(Nano.Operation.DOWN, (CharSequence)KeyMap.key(this.terminal, InfoCmp.Capability.key_down));
      this.editMessage = "Replace with: ";
      this.editBuffer.setLength(0);
      String currentBuffer = this.editBuffer.toString();
      int curPos = this.editBuffer.length();
      this.shortcuts = this.replaceShortcuts();
      this.display(curPos);

      String var6;
      try {
         String replaceTerm;
         label92:
         while(true) {
            Operation op = this.readOperation(keyMap);
            switch (op.ordinal()) {
               case 12:
                  this.editBuffer.setLength(0);
                  this.editBuffer.append(this.patternHistory.up(currentBuffer));
                  curPos = this.editBuffer.length();
                  break;
               case 13:
                  this.editBuffer.setLength(0);
                  this.editBuffer.append(this.patternHistory.down(currentBuffer));
                  curPos = this.editBuffer.length();
                  break;
               case 20:
                  this.help("nano-replace-help.txt");
                  break;
               case 29:
                  this.buffer.firstLine();
                  break;
               case 30:
                  this.buffer.lastLine();
                  break;
               case 35:
                  replaceTerm = "";
                  if (this.editBuffer.length() > 0) {
                     replaceTerm = this.editBuffer.toString();
                  }
                  break label92;
               case 36:
                  throw new IllegalArgumentException();
               case 75:
                  this.mouseEvent();
                  break;
               default:
                  curPos = this.editInputBuffer(op, curPos);
                  currentBuffer = this.editBuffer.toString();
            }

            this.display(curPos);
         }

         if (replaceTerm == null) {
            this.setMessage("Cancelled");
            throw new IllegalArgumentException();
         }

         this.patternHistory.add(replaceTerm);
         var6 = replaceTerm;
      } finally {
         this.shortcuts = this.standardShortcuts();
         this.editMessage = null;
      }

      return var6;
   }

   private String getSearchMessage() {
      StringBuilder sb = new StringBuilder();
      sb.append("Search");
      if (this.searchToReplace) {
         sb.append(" (to replace)");
      }

      if (this.searchCaseSensitive) {
         sb.append(" [Case Sensitive]");
      }

      if (this.searchRegexp) {
         sb.append(" [Regexp]");
      }

      if (this.searchBackwards) {
         sb.append(" [Backwards]");
      }

      if (this.searchTerm != null) {
         sb.append(" [");
         sb.append(this.searchTerm);
         sb.append("]");
      }

      sb.append(": ");
      return sb.toString();
   }

   String computeCurPos() {
      int chari = 0;
      int chart = 0;

      for(int i = 0; i < this.buffer.lines.size(); ++i) {
         int l = ((String)this.buffer.lines.get(i)).length() + 1;
         if (i < this.buffer.line) {
            chari += l;
         } else if (i == this.buffer.line) {
            chari += this.buffer.offsetInLine + this.buffer.column;
         }

         chart += l;
      }

      StringBuilder sb = new StringBuilder();
      sb.append("line ");
      sb.append(this.buffer.line + 1);
      sb.append("/");
      sb.append(this.buffer.lines.size());
      sb.append(" (");
      sb.append(Math.round((double)100.0F * (double)this.buffer.line / (double)this.buffer.lines.size()));
      sb.append("%), ");
      sb.append("col ");
      sb.append(this.buffer.offsetInLine + this.buffer.column + 1);
      sb.append("/");
      sb.append(this.buffer.length((String)this.buffer.lines.get(this.buffer.line)) + 1);
      sb.append(" (");
      if (((String)this.buffer.lines.get(this.buffer.line)).length() > 0) {
         sb.append(Math.round((double)100.0F * (double)(this.buffer.offsetInLine + this.buffer.column) / (double)this.buffer.length((String)this.buffer.lines.get(this.buffer.line))));
      } else {
         sb.append("100");
      }

      sb.append("%), ");
      sb.append("char ");
      sb.append(chari + 1);
      sb.append("/");
      sb.append(chart);
      sb.append(" (");
      sb.append(Math.round((double)100.0F * (double)chari / (double)chart));
      sb.append("%)");
      return sb.toString();
   }

   void curPos() {
      this.setMessage(this.computeCurPos());
   }

   void prevBuffer() throws IOException {
      if (this.buffers.size() > 1) {
         this.bufferIndex = (this.bufferIndex + this.buffers.size() - 1) % this.buffers.size();
         this.buffer = (Buffer)this.buffers.get(this.bufferIndex);
         this.setMessage("Switched to " + this.buffer.getTitle());
         this.buffer.open();
         this.display.clear();
      } else {
         this.setMessage("No more open file buffers");
      }

   }

   void nextBuffer() throws IOException {
      if (this.buffers.size() > 1) {
         this.bufferIndex = (this.bufferIndex + 1) % this.buffers.size();
         this.buffer = (Buffer)this.buffers.get(this.bufferIndex);
         this.setMessage("Switched to " + this.buffer.getTitle());
         this.buffer.open();
         this.display.clear();
      } else {
         this.setMessage("No more open file buffers");
      }

   }

   void setMessage(String message) {
      this.message = message;
      this.nbBindings = this.quickBlank ? 2 : 25;
   }

   boolean quit() throws IOException {
      if (this.buffer.dirty) {
         if (this.tempFile) {
            if (!this.write()) {
               return false;
            }
         } else {
            Operation op = this.getYNC("Save modified buffer (ANSWERING \"No\" WILL DESTROY CHANGES) ? ");
            switch (op.ordinal()) {
               case 36:
                  return false;
               case 45:
                  if (!this.write()) {
                     return false;
                  }
               case 46:
            }
         }
      }

      this.buffers.remove(this.bufferIndex);
      if (this.bufferIndex == this.buffers.size() && this.bufferIndex > 0) {
         this.bufferIndex = this.buffers.size() - 1;
      }

      if (this.buffers.isEmpty()) {
         this.buffer = null;
         return true;
      } else {
         this.buffer = (Buffer)this.buffers.get(this.bufferIndex);
         this.buffer.open();
         this.display.clear();
         this.setMessage("Switched to " + this.buffer.getTitle());
         return false;
      }
   }

   void numbers() {
      this.printLineNumbers = !this.printLineNumbers;
      this.resetDisplay();
      this.setMessage("Lines numbering " + (this.printLineNumbers ? "enabled" : "disabled"));
   }

   void smoothScrolling() {
      this.smoothScrolling = !this.smoothScrolling;
      this.setMessage("Smooth scrolling " + (this.smoothScrolling ? "enabled" : "disabled"));
   }

   void mouseSupport() throws IOException {
      this.mouseSupport = !this.mouseSupport;
      this.setMessage("Mouse support " + (this.mouseSupport ? "enabled" : "disabled"));
      this.terminal.trackMouse(this.mouseSupport ? Terminal.MouseTracking.Normal : Terminal.MouseTracking.Off);
   }

   void constantCursor() {
      this.constantCursor = !this.constantCursor;
      this.setMessage("Constant cursor position display " + (this.constantCursor ? "enabled" : "disabled"));
   }

   void oneMoreLine() {
      this.oneMoreLine = !this.oneMoreLine;
      this.setMessage("Use of one more line for editing " + (this.oneMoreLine ? "enabled" : "disabled"));
   }

   void wrap() {
      this.wrapping = !this.wrapping;
      this.buffer.computeAllOffsets();
      this.resetDisplay();
      this.setMessage("Lines wrapping " + (this.wrapping ? "enabled" : "disabled"));
   }

   void clearScreen() {
      this.resetDisplay();
   }

   void mouseEvent() {
      MouseEvent event = this.terminal.readMouseEvent();
      if (event.getModifiers().isEmpty() && event.getType() == MouseEvent.Type.Released && event.getButton() == MouseEvent.Button.Button1) {
         int x = event.getX();
         int y = event.getY();
         int hdr = this.buffer.computeHeader().size();
         int ftr = this.computeFooter().size();
         if (y >= hdr) {
            if (y < this.size.getRows() - ftr) {
               this.buffer.moveTo(x, y - hdr);
            } else {
               int cols = (this.shortcuts.size() + 1) / 2;
               int cw = this.size.getColumns() / cols;
               int l = y - (this.size.getRows() - ftr) - 1;
               int si = l * cols + x / cw;
               String shortcut = null;

               for(Iterator<String> it = this.shortcuts.keySet().iterator(); si-- >= 0 && it.hasNext(); shortcut = (String)it.next()) {
               }

               if (shortcut != null) {
                  shortcut = shortcut.replaceAll("M-", "\\\\E");
                  String seq = KeyMap.translate(shortcut);
                  this.bindingReader.runMacro(seq);
               }
            }
         }
      } else if (event.getType() == MouseEvent.Type.Wheel) {
         if (event.getButton() == MouseEvent.Button.WheelDown) {
            this.buffer.moveDown(1);
         } else if (event.getButton() == MouseEvent.Button.WheelUp) {
            this.buffer.moveUp(1);
         }
      }

   }

   void enableSuspension() {
      if (!this.restricted && this.vsusp < 0) {
         Attributes attrs = this.terminal.getAttributes();
         attrs.setControlChar(Attributes.ControlChar.VSUSP, this.vsusp);
         this.terminal.setAttributes(attrs);
      }

   }

   void toggleSuspension() {
      if (this.restricted) {
         this.setMessage("This function is disabled in restricted mode");
      } else if (this.vsusp < 0) {
         this.setMessage("This function is disabled");
      } else {
         Attributes attrs = this.terminal.getAttributes();
         int toggle = this.vsusp;
         String message = "enabled";
         if (attrs.getControlChar(Attributes.ControlChar.VSUSP) > 0) {
            toggle = 0;
            message = "disabled";
         }

         attrs.setControlChar(Attributes.ControlChar.VSUSP, toggle);
         this.terminal.setAttributes(attrs);
         this.setMessage("Suspension " + message);
      }

   }

   public String getTitle() {
      return this.title;
   }

   void resetDisplay() {
      this.display.clear();
      this.display.resize(this.size.getRows(), this.size.getColumns());

      for(Buffer buffer : this.buffers) {
         buffer.resetDisplay();
      }

   }

   synchronized void display() {
      this.display((Integer)null);
   }

   synchronized void display(Integer editCursor) {
      if (this.nbBindings > 0 && --this.nbBindings == 0) {
         this.message = null;
      }

      List<AttributedString> header = this.buffer.computeHeader();
      List<AttributedString> footer = this.computeFooter();
      int nbLines = this.size.getRows() - header.size() - footer.size();
      List<AttributedString> newLines = this.buffer.getDisplayedLines(nbLines);
      newLines.addAll(0, header);
      newLines.addAll(footer);
      int cursor;
      if (this.editMessage != null) {
         int crsr = editCursor != null ? editCursor : this.editBuffer.length();
         cursor = this.editMessage.length() + crsr;
         cursor = this.size.cursorPos(this.size.getRows() - footer.size(), cursor);
      } else {
         cursor = this.size.cursorPos(header.size(), this.buffer.getDisplayedCursor());
      }

      this.display.update(newLines, cursor);
      if (this.windowsTerminal) {
         this.resetDisplay();
      }

   }

   protected List computeFooter() {
      List<AttributedString> footer = new ArrayList();
      if (this.editMessage != null) {
         AttributedStringBuilder sb = new AttributedStringBuilder();
         sb.style(AttributedStyle.INVERSE);
         sb.append((CharSequence)this.editMessage);
         sb.append((CharSequence)this.editBuffer);

         for(int i = this.editMessage.length() + this.editBuffer.length(); i < this.size.getColumns(); ++i) {
            sb.append(' ');
         }

         sb.append('\n');
         footer.add(sb.toAttributedString());
      } else if (this.message == null && !this.constantCursor) {
         footer.add(new AttributedString("\n"));
      } else {
         int rwidth = this.size.getColumns();
         String text = "[ " + (this.message == null ? this.computeCurPos() : this.message) + " ]";
         int len = text.length();
         AttributedStringBuilder sb = new AttributedStringBuilder();

         for(int i = 0; i < (rwidth - len) / 2; ++i) {
            sb.append(' ');
         }

         sb.style(AttributedStyle.INVERSE);
         sb.append((CharSequence)text);
         sb.append('\n');
         footer.add(sb.toAttributedString());
      }

      Iterator<Map.Entry<String, String>> sit = this.shortcuts.entrySet().iterator();
      int cols = (this.shortcuts.size() + 1) / 2;
      int cw = (this.size.getColumns() - 1) / cols;
      int rem = (this.size.getColumns() - 1) % cols;

      for(int l = 0; l < 2; ++l) {
         AttributedStringBuilder sb = new AttributedStringBuilder();

         for(int c = 0; c < cols; ++c) {
            Map.Entry<String, String> entry = sit.hasNext() ? (Map.Entry)sit.next() : null;
            String key = entry != null ? (String)entry.getKey() : "";
            String val = entry != null ? (String)entry.getValue() : "";
            sb.style(AttributedStyle.INVERSE);
            sb.append((CharSequence)key);
            sb.style(AttributedStyle.DEFAULT);
            sb.append((CharSequence)" ");
            int nb = cw - key.length() - 1 + (c < rem ? 1 : 0);
            if (val.length() > nb) {
               sb.append((CharSequence)val.substring(0, nb));
            } else {
               sb.append((CharSequence)val);
               if (c < cols - 1) {
                  for(int i = 0; i < nb - val.length(); ++i) {
                     sb.append((CharSequence)" ");
                  }
               }
            }
         }

         sb.append('\n');
         footer.add(sb.toAttributedString());
      }

      return footer;
   }

   protected void handle(Terminal.Signal signal) {
      if (this.buffer != null) {
         this.size.copy(this.terminal.getSize());
         this.buffer.computeAllOffsets();
         this.buffer.moveToChar(this.buffer.offsetInLine + this.buffer.column);
         this.resetDisplay();
         this.display();
      }

   }

   protected void bindKeys() {
      this.keys = new KeyMap();
      if (!this.view) {
         this.keys.setUnicode(Nano.Operation.INSERT);

         for(char i = ' '; i < 128; ++i) {
            this.keys.bind(Nano.Operation.INSERT, (CharSequence)Character.toString(i));
         }

         this.keys.bind(Nano.Operation.BACKSPACE, (CharSequence)KeyMap.del());

         for(char i = 'A'; i <= 'Z'; ++i) {
            this.keys.bind(Nano.Operation.DO_LOWER_CASE, (CharSequence)KeyMap.alt(i));
         }

         this.keys.bind(Nano.Operation.WRITE, (CharSequence[])(KeyMap.ctrl('O'), KeyMap.key(this.terminal, InfoCmp.Capability.key_f3)));
         this.keys.bind(Nano.Operation.JUSTIFY_PARAGRAPH, (CharSequence[])(KeyMap.ctrl('J'), KeyMap.key(this.terminal, InfoCmp.Capability.key_f4)));
         this.keys.bind(Nano.Operation.CUT, (CharSequence[])(KeyMap.ctrl('K'), KeyMap.key(this.terminal, InfoCmp.Capability.key_f9)));
         this.keys.bind(Nano.Operation.UNCUT, (CharSequence[])(KeyMap.ctrl('U'), KeyMap.key(this.terminal, InfoCmp.Capability.key_f10)));
         this.keys.bind(Nano.Operation.REPLACE, (CharSequence[])(KeyMap.ctrl('\\'), KeyMap.key(this.terminal, InfoCmp.Capability.key_f14), KeyMap.alt('r')));
         this.keys.bind(Nano.Operation.MARK, (CharSequence[])(KeyMap.ctrl('^'), KeyMap.key(this.terminal, InfoCmp.Capability.key_f15), KeyMap.alt('a')));
         this.keys.bind(Nano.Operation.COPY, (CharSequence[])(KeyMap.alt('^'), KeyMap.alt('6')));
         this.keys.bind(Nano.Operation.INDENT, (CharSequence)KeyMap.alt('}'));
         this.keys.bind(Nano.Operation.UNINDENT, (CharSequence)KeyMap.alt('{'));
         this.keys.bind(Nano.Operation.VERBATIM, (CharSequence)KeyMap.alt('v'));
         this.keys.bind(Nano.Operation.INSERT, (CharSequence[])(KeyMap.ctrl('I'), KeyMap.ctrl('M')));
         this.keys.bind(Nano.Operation.DELETE, (CharSequence[])(KeyMap.ctrl('D'), KeyMap.key(this.terminal, InfoCmp.Capability.key_dc)));
         this.keys.bind(Nano.Operation.BACKSPACE, (CharSequence)KeyMap.ctrl('H'));
         this.keys.bind(Nano.Operation.CUT_TO_END, (CharSequence)KeyMap.alt('t'));
         this.keys.bind(Nano.Operation.JUSTIFY_FILE, (CharSequence)KeyMap.alt('j'));
         this.keys.bind(Nano.Operation.AUTO_INDENT, (CharSequence)KeyMap.alt('i'));
         this.keys.bind(Nano.Operation.CUT_TO_END_TOGGLE, (CharSequence)KeyMap.alt('k'));
         this.keys.bind(Nano.Operation.TABS_TO_SPACE, (CharSequence)KeyMap.alt('q'));
      } else {
         this.keys.bind(Nano.Operation.NEXT_PAGE, (CharSequence[])(" ", "f"));
         this.keys.bind(Nano.Operation.PREV_PAGE, (CharSequence)"b");
      }

      this.keys.bind(Nano.Operation.NEXT_PAGE, (CharSequence[])(KeyMap.ctrl('V'), KeyMap.key(this.terminal, InfoCmp.Capability.key_f8)));
      this.keys.bind(Nano.Operation.PREV_PAGE, (CharSequence[])(KeyMap.ctrl('Y'), KeyMap.key(this.terminal, InfoCmp.Capability.key_f7)));
      this.keys.bind(Nano.Operation.HELP, (CharSequence[])(KeyMap.ctrl('G'), KeyMap.key(this.terminal, InfoCmp.Capability.key_f1)));
      this.keys.bind(Nano.Operation.QUIT, (CharSequence[])(KeyMap.ctrl('X'), KeyMap.key(this.terminal, InfoCmp.Capability.key_f2)));
      this.keys.bind(Nano.Operation.READ, (CharSequence[])(KeyMap.ctrl('R'), KeyMap.key(this.terminal, InfoCmp.Capability.key_f5)));
      this.keys.bind(Nano.Operation.SEARCH, (CharSequence[])(KeyMap.ctrl('W'), KeyMap.key(this.terminal, InfoCmp.Capability.key_f6)));
      this.keys.bind(Nano.Operation.CUR_POS, (CharSequence[])(KeyMap.ctrl('C'), KeyMap.key(this.terminal, InfoCmp.Capability.key_f11)));
      this.keys.bind(Nano.Operation.TO_SPELL, (CharSequence[])(KeyMap.ctrl('T'), KeyMap.key(this.terminal, InfoCmp.Capability.key_f11)));
      this.keys.bind(Nano.Operation.GOTO, (CharSequence[])(KeyMap.ctrl('_'), KeyMap.key(this.terminal, InfoCmp.Capability.key_f13), KeyMap.alt('g')));
      this.keys.bind(Nano.Operation.NEXT_SEARCH, (CharSequence[])(KeyMap.key(this.terminal, InfoCmp.Capability.key_f16), KeyMap.alt('w')));
      this.keys.bind(Nano.Operation.RIGHT, (CharSequence)KeyMap.ctrl('F'));
      this.keys.bind(Nano.Operation.LEFT, (CharSequence)KeyMap.ctrl('B'));
      this.keys.bind(Nano.Operation.NEXT_WORD, (CharSequence)KeyMap.ctrl(' '));
      this.keys.bind(Nano.Operation.PREV_WORD, (CharSequence)KeyMap.alt(' '));
      this.keys.bind(Nano.Operation.UP, (CharSequence)KeyMap.ctrl('P'));
      this.keys.bind(Nano.Operation.DOWN, (CharSequence)KeyMap.ctrl('N'));
      this.keys.bind(Nano.Operation.BEGINNING_OF_LINE, (CharSequence[])(KeyMap.ctrl('A'), KeyMap.key(this.terminal, InfoCmp.Capability.key_home)));
      this.keys.bind(Nano.Operation.END_OF_LINE, (CharSequence[])(KeyMap.ctrl('E'), KeyMap.key(this.terminal, InfoCmp.Capability.key_end)));
      this.keys.bind(Nano.Operation.BEGINNING_OF_PARAGRAPH, (CharSequence[])(KeyMap.alt('('), KeyMap.alt('9')));
      this.keys.bind(Nano.Operation.END_OF_PARAGRAPH, (CharSequence[])(KeyMap.alt(')'), KeyMap.alt('0')));
      this.keys.bind(Nano.Operation.FIRST_LINE, (CharSequence[])(KeyMap.alt('\\'), KeyMap.alt('|')));
      this.keys.bind(Nano.Operation.LAST_LINE, (CharSequence[])(KeyMap.alt('/'), KeyMap.alt('?')));
      this.keys.bind(Nano.Operation.MATCHING, (CharSequence)KeyMap.alt(']'));
      this.keys.bind(Nano.Operation.SCROLL_UP, (CharSequence[])(KeyMap.alt('-'), KeyMap.alt('_')));
      this.keys.bind(Nano.Operation.SCROLL_DOWN, (CharSequence[])(KeyMap.alt('+'), KeyMap.alt('=')));
      this.keys.bind(Nano.Operation.PREV_BUFFER, (CharSequence)KeyMap.alt('<'));
      this.keys.bind(Nano.Operation.NEXT_BUFFER, (CharSequence)KeyMap.alt('>'));
      this.keys.bind(Nano.Operation.PREV_BUFFER, (CharSequence)KeyMap.alt(','));
      this.keys.bind(Nano.Operation.NEXT_BUFFER, (CharSequence)KeyMap.alt('.'));
      this.keys.bind(Nano.Operation.COUNT, (CharSequence)KeyMap.alt('d'));
      this.keys.bind(Nano.Operation.CLEAR_SCREEN, (CharSequence)KeyMap.ctrl('L'));
      this.keys.bind(Nano.Operation.HELP, (CharSequence)KeyMap.alt('x'));
      this.keys.bind(Nano.Operation.CONSTANT_CURSOR, (CharSequence)KeyMap.alt('c'));
      this.keys.bind(Nano.Operation.ONE_MORE_LINE, (CharSequence)KeyMap.alt('o'));
      this.keys.bind(Nano.Operation.SMOOTH_SCROLLING, (CharSequence)KeyMap.alt('s'));
      this.keys.bind(Nano.Operation.MOUSE_SUPPORT, (CharSequence)KeyMap.alt('m'));
      this.keys.bind(Nano.Operation.WHITESPACE, (CharSequence)KeyMap.alt('p'));
      this.keys.bind(Nano.Operation.HIGHLIGHT, (CharSequence)KeyMap.alt('y'));
      this.keys.bind(Nano.Operation.SMART_HOME_KEY, (CharSequence)KeyMap.alt('h'));
      this.keys.bind(Nano.Operation.WRAP, (CharSequence)KeyMap.alt('l'));
      this.keys.bind(Nano.Operation.BACKUP, (CharSequence)KeyMap.alt('b'));
      this.keys.bind(Nano.Operation.NUMBERS, (CharSequence)KeyMap.alt('n'));
      this.keys.bind(Nano.Operation.UP, (CharSequence)KeyMap.key(this.terminal, InfoCmp.Capability.key_up));
      this.keys.bind(Nano.Operation.DOWN, (CharSequence)KeyMap.key(this.terminal, InfoCmp.Capability.key_down));
      this.keys.bind(Nano.Operation.RIGHT, (CharSequence)KeyMap.key(this.terminal, InfoCmp.Capability.key_right));
      this.keys.bind(Nano.Operation.LEFT, (CharSequence)KeyMap.key(this.terminal, InfoCmp.Capability.key_left));
      this.keys.bind(Nano.Operation.MOUSE_EVENT, (CharSequence)KeyMap.key(this.terminal, InfoCmp.Capability.key_mouse));
      this.keys.bind(Nano.Operation.TOGGLE_SUSPENSION, (CharSequence)KeyMap.alt('z'));
      this.keys.bind(Nano.Operation.NEXT_PAGE, (CharSequence)KeyMap.key(this.terminal, InfoCmp.Capability.key_npage));
      this.keys.bind(Nano.Operation.PREV_PAGE, (CharSequence)KeyMap.key(this.terminal, InfoCmp.Capability.key_ppage));
   }

   protected static enum WriteMode {
      WRITE,
      APPEND,
      PREPEND;

      // $FF: synthetic method
      private static WriteMode[] $values() {
         return new WriteMode[]{WRITE, APPEND, PREPEND};
      }
   }

   protected static enum WriteFormat {
      UNIX,
      DOS,
      MAC;

      // $FF: synthetic method
      private static WriteFormat[] $values() {
         return new WriteFormat[]{UNIX, DOS, MAC};
      }
   }

   protected static enum CursorMovement {
      RIGHT,
      LEFT,
      STILL;

      // $FF: synthetic method
      private static CursorMovement[] $values() {
         return new CursorMovement[]{RIGHT, LEFT, STILL};
      }
   }

   protected class Buffer {
      String file;
      Charset charset;
      WriteFormat format;
      List lines;
      int firstLineToDisplay;
      int firstColumnToDisplay;
      int offsetInLineToDisplay;
      int line;
      List offsets;
      int offsetInLine;
      int column;
      int wantedColumn;
      boolean uncut;
      int[] markPos;
      SyntaxHighlighter syntaxHighlighter;
      boolean dirty;

      protected Buffer(String file) {
         this.format = Nano.WriteFormat.UNIX;
         this.firstColumnToDisplay = 0;
         this.offsets = new ArrayList();
         this.uncut = false;
         this.markPos = new int[]{-1, -1};
         this.file = file;
         this.syntaxHighlighter = SyntaxHighlighter.build(Nano.this.syntaxFiles, file, Nano.this.syntaxName, Nano.this.nanorcIgnoreErrors);
      }

      void open() throws IOException {
         if (this.lines == null) {
            this.lines = new ArrayList();
            this.lines.add("");
            this.charset = Charset.defaultCharset();
            this.computeAllOffsets();
            if (this.file != null) {
               Path path = Nano.this.root.resolve(this.file);
               if (Files.isDirectory(path, new LinkOption[0])) {
                  Nano.this.setMessage("\"" + this.file + "\" is a directory");
               } else {
                  try {
                     InputStream fis = Files.newInputStream(path);

                     try {
                        this.read(fis);
                     } catch (Throwable var6) {
                        if (fis != null) {
                           try {
                              fis.close();
                           } catch (Throwable var5) {
                              var6.addSuppressed(var5);
                           }
                        }

                        throw var6;
                     }

                     if (fis != null) {
                        fis.close();
                     }
                  } catch (IOException e) {
                     Nano.this.setMessage("Error reading " + this.file + ": " + e.getMessage());
                  }

               }
            }
         }
      }

      void open(InputStream is) throws IOException {
         if (this.lines == null) {
            this.lines = new ArrayList();
            this.lines.add("");
            this.charset = Charset.defaultCharset();
            this.computeAllOffsets();
            this.read(is);
         }
      }

      void read(InputStream fis) throws IOException {
         ByteArrayOutputStream bos = new ByteArrayOutputStream();
         byte[] buffer = new byte[4096];

         int remaining;
         while((remaining = fis.read(buffer)) > 0) {
            bos.write(buffer, 0, remaining);
         }

         byte[] bytes = bos.toByteArray();

         try {
            UniversalDetector detector = new UniversalDetector((CharsetListener)null);
            detector.handleData(bytes, 0, bytes.length);
            detector.dataEnd();
            if (detector.getDetectedCharset() != null) {
               this.charset = Charset.forName(detector.getDetectedCharset());
            }
         } catch (Throwable var10) {
         }

         BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes), this.charset));

         try {
            this.lines.clear();

            String line;
            while((line = reader.readLine()) != null) {
               this.lines.add(line);
            }
         } catch (Throwable var11) {
            try {
               reader.close();
            } catch (Throwable var9) {
               var11.addSuppressed(var9);
            }

            throw var11;
         }

         reader.close();
         if (this.lines.isEmpty()) {
            this.lines.add("");
         }

         this.computeAllOffsets();
         this.moveToChar(0);
      }

      private int charPosition(int displayPosition) {
         return this.charPosition(this.line, displayPosition, Nano.CursorMovement.STILL);
      }

      private int charPosition(int displayPosition, CursorMovement move) {
         return this.charPosition(this.line, displayPosition, move);
      }

      private int charPosition(int line, int displayPosition) {
         return this.charPosition(line, displayPosition, Nano.CursorMovement.STILL);
      }

      private int charPosition(int line, int displayPosition, CursorMovement move) {
         int out = ((String)this.lines.get(line)).length();
         if (((String)this.lines.get(line)).contains("\t") && displayPosition != 0) {
            if (displayPosition < this.length((String)this.lines.get(line))) {
               int rdiff = 0;
               int ldiff = 0;

               for(int i = 0; i < ((String)this.lines.get(line)).length(); ++i) {
                  int dp = this.length(((String)this.lines.get(line)).substring(0, i));
                  if (move == Nano.CursorMovement.LEFT) {
                     if (dp > displayPosition) {
                        break;
                     }

                     out = i;
                  } else if (move == Nano.CursorMovement.RIGHT) {
                     if (dp >= displayPosition) {
                        out = i;
                        break;
                     }
                  } else if (move == Nano.CursorMovement.STILL) {
                     if (dp <= displayPosition) {
                        ldiff = displayPosition - dp;
                        out = i;
                     } else if (dp >= displayPosition) {
                        rdiff = dp - displayPosition;
                        if (rdiff < ldiff) {
                           out = i;
                        }
                        break;
                     }
                  }
               }
            }
         } else {
            out = displayPosition;
         }

         return out;
      }

      String blanks(int nb) {
         StringBuilder sb = new StringBuilder();

         for(int i = 0; i < nb; ++i) {
            sb.append(' ');
         }

         return sb.toString();
      }

      void insert(String insert) {
         String text = (String)this.lines.get(this.line);
         int pos = this.charPosition(this.offsetInLine + this.column);
         insert = insert.replaceAll("\r\n", "\n");
         insert = insert.replaceAll("\r", "\n");
         if (Nano.this.tabsToSpaces && insert.length() == 1 && insert.charAt(0) == '\t') {
            int len = pos == text.length() ? this.length(text + insert) : this.length(text.substring(0, pos) + insert);
            insert = this.blanks(len - this.offsetInLine - this.column);
         }

         if (Nano.this.autoIndent && insert.length() == 1 && insert.charAt(0) == '\n') {
            for(char c : ((String)this.lines.get(this.line)).toCharArray()) {
               if (c == ' ') {
                  insert = insert + c;
               } else {
                  if (c != '\t') {
                     break;
                  }

                  insert = insert + c;
               }
            }
         }

         String tail = "";
         String mod;
         if (pos == text.length()) {
            mod = text + insert;
         } else {
            mod = text.substring(0, pos) + insert;
            tail = text.substring(pos);
         }

         List<String> ins = new ArrayList();
         int last = 0;

         for(int idx = mod.indexOf(10, last); idx >= 0; idx = mod.indexOf(10, last)) {
            ins.add(mod.substring(last, idx));
            last = idx + 1;
         }

         ins.add(mod.substring(last) + tail);
         int curPos = this.length(mod.substring(last));
         this.lines.set(this.line, (String)ins.get(0));
         this.offsets.set(this.line, this.computeOffsets((String)ins.get(0)));

         for(int i = 1; i < ins.size(); ++i) {
            ++this.line;
            this.lines.add(this.line, (String)ins.get(i));
            this.offsets.add(this.line, this.computeOffsets((String)ins.get(i)));
         }

         this.moveToChar(curPos);
         this.ensureCursorVisible();
         this.dirty = true;
      }

      void computeAllOffsets() {
         this.offsets.clear();

         for(String text : this.lines) {
            this.offsets.add(this.computeOffsets(text));
         }

      }

      LinkedList computeOffsets(String line) {
         String text = (new AttributedStringBuilder()).tabs(Nano.this.tabs).append((CharSequence)line).toString();
         int width = Nano.this.size.getColumns() - (Nano.this.printLineNumbers ? 8 : 0);
         LinkedList<Integer> offsets = new LinkedList();
         offsets.add(0);
         if (Nano.this.wrapping) {
            int last = 0;
            int prevword = 0;
            boolean inspace = false;

            for(int i = 0; i < text.length(); ++i) {
               if (this.isBreakable(text.charAt(i))) {
                  inspace = true;
               } else if (inspace) {
                  prevword = i;
                  inspace = false;
               }

               if (i == last + width - 1) {
                  if (prevword == last) {
                     prevword = i;
                  }

                  offsets.add(prevword);
                  last = prevword;
               }
            }
         }

         return offsets;
      }

      boolean isBreakable(char ch) {
         return !Nano.this.atBlanks || ch == ' ';
      }

      void moveToChar(int pos) {
         this.moveToChar(pos, Nano.CursorMovement.STILL);
      }

      void moveToChar(int pos, CursorMovement move) {
         if (!Nano.this.wrapping) {
            if (pos > this.column && pos - this.firstColumnToDisplay + 1 > this.width()) {
               this.firstColumnToDisplay = this.offsetInLine + this.column - 6;
            } else if (pos < this.column && this.firstColumnToDisplay + 5 > pos) {
               this.firstColumnToDisplay = Math.max(0, this.firstColumnToDisplay - this.width() + 5);
            }
         }

         if (((String)this.lines.get(this.line)).contains("\t")) {
            int cpos = this.charPosition(pos, move);
            if (cpos < ((String)this.lines.get(this.line)).length()) {
               pos = this.length(((String)this.lines.get(this.line)).substring(0, cpos));
            } else {
               pos = this.length((String)this.lines.get(this.line));
            }
         }

         this.offsetInLine = (Integer)this.prevLineOffset(this.line, pos + 1).get();
         this.column = pos - this.offsetInLine;
      }

      void delete(int count) {
         do {
            --count;
         } while(count >= 0 && this.moveRight(1) && this.backspace(1));

      }

      boolean backspace(int count) {
         for(; count > 0; this.dirty = true) {
            String text = (String)this.lines.get(this.line);
            int pos = this.charPosition(this.offsetInLine + this.column);
            if (pos == 0) {
               if (this.line == 0) {
                  this.bof();
                  return false;
               }

               String prev = (String)this.lines.get(--this.line);
               this.lines.set(this.line, prev + text);
               this.offsets.set(this.line, this.computeOffsets(prev + text));
               this.moveToChar(this.length(prev));
               this.lines.remove(this.line + 1);
               this.offsets.remove(this.line + 1);
               --count;
            } else {
               int nb = Math.min(pos, count);
               int curPos = this.length(text.substring(0, pos - nb));
               text = text.substring(0, pos - nb) + text.substring(pos);
               this.lines.set(this.line, text);
               this.offsets.set(this.line, this.computeOffsets(text));
               this.moveToChar(curPos);
               count -= nb;
            }
         }

         this.ensureCursorVisible();
         return true;
      }

      boolean moveLeft(int chars) {
         boolean ret = true;

         while(true) {
            --chars;
            if (chars < 0) {
               break;
            }

            if (this.offsetInLine + this.column > 0) {
               this.moveToChar(this.offsetInLine + this.column - 1, Nano.CursorMovement.LEFT);
            } else {
               if (this.line <= 0) {
                  this.bof();
                  ret = false;
                  break;
               }

               --this.line;
               this.moveToChar(this.length(this.getLine(this.line)));
            }
         }

         this.wantedColumn = this.column;
         this.ensureCursorVisible();
         return ret;
      }

      boolean moveRight(int chars) {
         return this.moveRight(chars, false);
      }

      int width() {
         return Nano.this.size.getColumns() - (Nano.this.printLineNumbers ? 8 : 0) - (Nano.this.wrapping ? 0 : 1) - (this.firstColumnToDisplay > 0 ? 1 : 0);
      }

      boolean moveRight(int chars, boolean fromBeginning) {
         if (fromBeginning) {
            this.firstColumnToDisplay = 0;
            this.offsetInLine = 0;
            this.column = 0;
            chars = Math.min(chars, this.length(this.getLine(this.line)));
         }

         boolean ret = true;

         while(true) {
            --chars;
            if (chars < 0) {
               break;
            }

            int len = this.length(this.getLine(this.line));
            if (this.offsetInLine + this.column + 1 <= len) {
               this.moveToChar(this.offsetInLine + this.column + 1, Nano.CursorMovement.RIGHT);
            } else {
               if (this.getLine(this.line + 1) == null) {
                  this.eof();
                  ret = false;
                  break;
               }

               ++this.line;
               this.firstColumnToDisplay = 0;
               this.offsetInLine = 0;
               this.column = 0;
            }
         }

         this.wantedColumn = this.column;
         this.ensureCursorVisible();
         return ret;
      }

      void moveDown(int lines) {
         this.cursorDown(lines);
         this.ensureCursorVisible();
      }

      void moveUp(int lines) {
         this.cursorUp(lines);
         this.ensureCursorVisible();
      }

      private Optional prevLineOffset(int line, int offsetInLine) {
         if (line >= this.offsets.size()) {
            return Optional.empty();
         } else {
            Iterator<Integer> it = ((LinkedList)this.offsets.get(line)).descendingIterator();

            while(it.hasNext()) {
               int off = (Integer)it.next();
               if (off < offsetInLine) {
                  return Optional.of(off);
               }
            }

            return Optional.empty();
         }
      }

      private Optional nextLineOffset(int line, int offsetInLine) {
         return line >= this.offsets.size() ? Optional.empty() : ((LinkedList)this.offsets.get(line)).stream().filter((o) -> o > offsetInLine).findFirst();
      }

      void moveDisplayDown(int lines) {
         int height = Nano.this.size.getRows() - this.computeHeader().size() - Nano.this.computeFooter().size();

         while(true) {
            --lines;
            if (lines < 0) {
               return;
            }

            int lastLineToDisplay = this.firstLineToDisplay;
            if (!Nano.this.wrapping) {
               lastLineToDisplay += height - 1;
            } else {
               int off = this.offsetInLineToDisplay;

               for(int l = 0; l < height - 1; ++l) {
                  Optional<Integer> next = this.nextLineOffset(lastLineToDisplay, off);
                  if (next.isPresent()) {
                     off = (Integer)next.get();
                  } else {
                     off = 0;
                     ++lastLineToDisplay;
                  }
               }
            }

            if (this.getLine(lastLineToDisplay) == null) {
               this.eof();
               return;
            }

            Optional<Integer> next = this.nextLineOffset(this.firstLineToDisplay, this.offsetInLineToDisplay);
            if (next.isPresent()) {
               this.offsetInLineToDisplay = (Integer)next.get();
            } else {
               this.offsetInLineToDisplay = 0;
               ++this.firstLineToDisplay;
            }
         }
      }

      void moveDisplayUp(int lines) {
         int width = Nano.this.size.getColumns() - (Nano.this.printLineNumbers ? 8 : 0);

         while(true) {
            --lines;
            if (lines < 0) {
               return;
            }

            if (this.offsetInLineToDisplay > 0) {
               this.offsetInLineToDisplay = Math.max(0, this.offsetInLineToDisplay - (width - 1));
            } else {
               if (this.firstLineToDisplay <= 0) {
                  this.bof();
                  return;
               }

               --this.firstLineToDisplay;
               this.offsetInLineToDisplay = (Integer)this.prevLineOffset(this.firstLineToDisplay, Integer.MAX_VALUE).get();
            }
         }
      }

      private void cursorDown(int lines) {
         this.firstColumnToDisplay = 0;

         while(true) {
            --lines;
            if (lines < 0) {
               break;
            }

            if (!Nano.this.wrapping) {
               if (this.getLine(this.line + 1) == null) {
                  this.bof();
                  break;
               }

               ++this.line;
               this.offsetInLine = 0;
               this.column = Math.min(this.length(this.getLine(this.line)), this.wantedColumn);
            } else {
               String txt = this.getLine(this.line);
               Optional<Integer> off = this.nextLineOffset(this.line, this.offsetInLine);
               if (off.isPresent()) {
                  this.offsetInLine = (Integer)off.get();
               } else {
                  if (this.getLine(this.line + 1) == null) {
                     this.eof();
                     break;
                  }

                  ++this.line;
                  this.offsetInLine = 0;
                  txt = this.getLine(this.line);
               }

               int next = (Integer)this.nextLineOffset(this.line, this.offsetInLine).orElse(this.length(txt));
               this.column = Math.min(this.wantedColumn, next - this.offsetInLine);
            }
         }

         this.moveToChar(this.offsetInLine + this.column);
      }

      private void cursorUp(int lines) {
         this.firstColumnToDisplay = 0;

         while(true) {
            --lines;
            if (lines < 0) {
               break;
            }

            if (!Nano.this.wrapping) {
               if (this.line <= 0) {
                  this.bof();
                  break;
               }

               --this.line;
               this.column = Math.min(this.length(this.getLine(this.line)) - this.offsetInLine, this.wantedColumn);
            } else {
               Optional<Integer> prev = this.prevLineOffset(this.line, this.offsetInLine);
               if (prev.isPresent()) {
                  this.offsetInLine = (Integer)prev.get();
               } else {
                  if (this.line <= 0) {
                     this.bof();
                     break;
                  }

                  --this.line;
                  this.offsetInLine = (Integer)this.prevLineOffset(this.line, Integer.MAX_VALUE).get();
                  int next = (Integer)this.nextLineOffset(this.line, this.offsetInLine).orElse(this.length(this.getLine(this.line)));
                  this.column = Math.min(this.wantedColumn, next - this.offsetInLine);
               }
            }
         }

         this.moveToChar(this.offsetInLine + this.column);
      }

      void ensureCursorVisible() {
         List<AttributedString> header = this.computeHeader();
         int rwidth = Nano.this.size.getColumns();
         int height = Nano.this.size.getRows() - header.size() - Nano.this.computeFooter().size();

         while(this.line < this.firstLineToDisplay || this.line == this.firstLineToDisplay && this.offsetInLine < this.offsetInLineToDisplay) {
            this.moveDisplayUp(Nano.this.smoothScrolling ? 1 : height / 2);
         }

         while(true) {
            int cursor = this.computeCursorPosition(header.size() * Nano.this.size.getColumns() + (Nano.this.printLineNumbers ? 8 : 0), rwidth);
            if (cursor < (height + header.size()) * rwidth) {
               return;
            }

            this.moveDisplayDown(Nano.this.smoothScrolling ? 1 : height / 2);
         }
      }

      void eof() {
      }

      void bof() {
      }

      void resetDisplay() {
         this.column += this.offsetInLine;
         this.moveRight(this.column, true);
      }

      String getLine(int line) {
         return line < this.lines.size() ? (String)this.lines.get(line) : null;
      }

      String getTitle() {
         return this.file != null ? "File: " + this.file : "New Buffer";
      }

      List computeHeader() {
         String left = Nano.this.getTitle();
         String middle = null;
         String right = this.dirty ? "Modified" : "        ";
         int width = Nano.this.size.getColumns();
         int mstart = 2 + left.length() + 1;
         int mend = width - 2 - 8;
         if (this.file == null) {
            middle = "New Buffer";
         } else {
            int max = mend - mstart;
            String src = this.file;
            if ("File: ".length() + src.length() > max) {
               int lastSep = src.lastIndexOf(47);
               if (lastSep > 0) {
                  String p1 = src.substring(lastSep);

                  String p0;
                  for(p0 = src.substring(0, lastSep); p0.startsWith("."); p0 = p0.substring(1)) {
                  }

                  int nb = max - p1.length() - "File: ...".length();
                  int cut = Math.max(0, Math.min(p0.length(), p0.length() - nb));
                  middle = "File: ..." + p0.substring(cut) + p1;
               }

               if (middle == null || middle.length() > max) {
                  left = null;
                  max = mend - 2;
                  int nb = max - "File: ...".length();
                  int cut = Math.max(0, Math.min(src.length(), src.length() - nb));
                  middle = "File: ..." + src.substring(cut);
                  if (middle.length() > max) {
                     middle = middle.substring(0, max);
                  }
               }
            } else {
               middle = "File: " + src;
            }
         }

         int pos = 0;
         AttributedStringBuilder sb = new AttributedStringBuilder();
         sb.style(AttributedStyle.INVERSE);
         sb.append((CharSequence)"  ");
         pos += 2;
         if (left != null) {
            sb.append((CharSequence)left);
            pos += left.length();
            sb.append((CharSequence)" ");
            ++pos;

            for(int i = 1; i < (Nano.this.size.getColumns() - middle.length()) / 2 - left.length() - 1 - 2; ++i) {
               sb.append((CharSequence)" ");
               ++pos;
            }
         }

         sb.append((CharSequence)middle);

         for(int var18 = pos + middle.length(); var18 < width - 8 - 2; ++var18) {
            sb.append((CharSequence)" ");
         }

         sb.append((CharSequence)right);
         sb.append((CharSequence)"  \n");
         return Nano.this.oneMoreLine ? Collections.singletonList(sb.toAttributedString()) : Arrays.asList(sb.toAttributedString(), new AttributedString("\n"));
      }

      void highlightDisplayedLine(int curLine, int curOffset, int nextOffset, AttributedStringBuilder line) {
         AttributedString disp = Nano.this.highlight ? this.syntaxHighlighter.highlight((new AttributedStringBuilder()).tabs(Nano.this.tabs).append((CharSequence)this.getLine(curLine))) : (new AttributedStringBuilder()).tabs(Nano.this.tabs).append((CharSequence)this.getLine(curLine)).toAttributedString();
         int[] hls = this.highlightStart();
         int[] hle = this.highlightEnd();
         if (hls[0] != -1 && hle[0] != -1) {
            if (hls[0] == hle[0]) {
               if (curLine == hls[0]) {
                  if (hls[1] > nextOffset) {
                     line.append(disp.columnSubSequence(curOffset, nextOffset));
                  } else if (hls[1] < curOffset) {
                     if (hle[1] > nextOffset) {
                        line.append(disp.columnSubSequence(curOffset, nextOffset), AttributedStyle.INVERSE);
                     } else if (hle[1] > curOffset) {
                        line.append(disp.columnSubSequence(curOffset, hle[1]), AttributedStyle.INVERSE);
                        line.append(disp.columnSubSequence(hle[1], nextOffset));
                     } else {
                        line.append(disp.columnSubSequence(curOffset, nextOffset));
                     }
                  } else {
                     line.append(disp.columnSubSequence(curOffset, hls[1]));
                     if (hle[1] > nextOffset) {
                        line.append(disp.columnSubSequence(hls[1], nextOffset), AttributedStyle.INVERSE);
                     } else {
                        line.append(disp.columnSubSequence(hls[1], hle[1]), AttributedStyle.INVERSE);
                        line.append(disp.columnSubSequence(hle[1], nextOffset));
                     }
                  }
               } else {
                  line.append(disp.columnSubSequence(curOffset, nextOffset));
               }
            } else if (curLine > hls[0] && curLine < hle[0]) {
               line.append(disp.columnSubSequence(curOffset, nextOffset), AttributedStyle.INVERSE);
            } else if (curLine == hls[0]) {
               if (hls[1] > nextOffset) {
                  line.append(disp.columnSubSequence(curOffset, nextOffset));
               } else if (hls[1] < curOffset) {
                  line.append(disp.columnSubSequence(curOffset, nextOffset), AttributedStyle.INVERSE);
               } else {
                  line.append(disp.columnSubSequence(curOffset, hls[1]));
                  line.append(disp.columnSubSequence(hls[1], nextOffset), AttributedStyle.INVERSE);
               }
            } else if (curLine == hle[0]) {
               if (hle[1] < curOffset) {
                  line.append(disp.columnSubSequence(curOffset, nextOffset));
               } else if (hle[1] > nextOffset) {
                  line.append(disp.columnSubSequence(curOffset, nextOffset), AttributedStyle.INVERSE);
               } else {
                  line.append(disp.columnSubSequence(curOffset, hle[1]), AttributedStyle.INVERSE);
                  line.append(disp.columnSubSequence(hle[1], nextOffset));
               }
            } else {
               line.append(disp.columnSubSequence(curOffset, nextOffset));
            }
         } else {
            line.append(disp.columnSubSequence(curOffset, nextOffset));
         }

      }

      List getDisplayedLines(int nbLines) {
         AttributedStyle s = AttributedStyle.DEFAULT.foreground(8);
         AttributedString cut = new AttributedString("…", s);
         AttributedString ret = new AttributedString("↩", s);
         List<AttributedString> newLines = new ArrayList();
         int rwidth = Nano.this.size.getColumns();
         int width = rwidth - (Nano.this.printLineNumbers ? 8 : 0);
         int curLine = this.firstLineToDisplay;
         int curOffset = this.offsetInLineToDisplay;
         int prevLine = -1;
         if (Nano.this.highlight) {
            this.syntaxHighlighter.reset();

            for(int i = Math.max(0, curLine - nbLines); i < curLine; ++i) {
               this.syntaxHighlighter.highlight(this.getLine(i));
            }
         }

         for(int terminalLine = 0; terminalLine < nbLines; ++terminalLine) {
            AttributedStringBuilder line = (new AttributedStringBuilder()).tabs(Nano.this.tabs);
            if (Nano.this.printLineNumbers && curLine < this.lines.size()) {
               line.style(s);
               if (curLine != prevLine) {
                  line.append((CharSequence)String.format("%7d ", curLine + 1));
               } else {
                  line.append((CharSequence)"      ‧ ");
               }

               line.style(AttributedStyle.DEFAULT);
               prevLine = curLine;
            }

            if (curLine < this.lines.size()) {
               if (!Nano.this.wrapping) {
                  AttributedString disp = (new AttributedStringBuilder()).tabs(Nano.this.tabs).append((CharSequence)this.getLine(curLine)).toAttributedString();
                  if (this.line == curLine) {
                     int cutCount = 1;
                     if (this.firstColumnToDisplay > 0) {
                        line.append(cut);
                        cutCount = 2;
                     }

                     if (disp.columnLength() - this.firstColumnToDisplay >= width - (cutCount - 1) * cut.columnLength()) {
                        this.highlightDisplayedLine(curLine, this.firstColumnToDisplay, this.firstColumnToDisplay + width - cutCount * cut.columnLength(), line);
                        line.append(cut);
                     } else {
                        this.highlightDisplayedLine(curLine, this.firstColumnToDisplay, disp.columnLength(), line);
                     }
                  } else if (disp.columnLength() >= width) {
                     this.highlightDisplayedLine(curLine, 0, width - cut.columnLength(), line);
                     line.append(cut);
                  } else {
                     this.highlightDisplayedLine(curLine, 0, disp.columnLength(), line);
                  }

                  ++curLine;
               } else {
                  Optional<Integer> nextOffset = this.nextLineOffset(curLine, curOffset);
                  if (nextOffset.isPresent()) {
                     this.highlightDisplayedLine(curLine, curOffset, (Integer)nextOffset.get(), line);
                     line.append(ret);
                     curOffset = (Integer)nextOffset.get();
                  } else {
                     this.highlightDisplayedLine(curLine, curOffset, Integer.MAX_VALUE, line);
                     ++curLine;
                     curOffset = 0;
                  }
               }
            }

            line.append('\n');
            newLines.add(line.toAttributedString());
         }

         return newLines;
      }

      public void moveTo(int x, int y) {
         if (Nano.this.printLineNumbers) {
            x = Math.max(x - 8, 0);
         }

         this.line = this.firstLineToDisplay;
         this.offsetInLine = this.offsetInLineToDisplay;
         this.wantedColumn = x;
         this.cursorDown(y);
      }

      public void gotoLine(int x, int y) {
         this.line = y < this.lines.size() ? y : this.lines.size() - 1;
         x = Math.min(x, this.length((String)this.lines.get(this.line)));
         this.firstLineToDisplay = this.line > 0 ? this.line - 1 : this.line;
         this.offsetInLine = 0;
         this.offsetInLineToDisplay = 0;
         this.column = 0;
         this.moveRight(x);
      }

      public int getDisplayedCursor() {
         return this.computeCursorPosition(Nano.this.printLineNumbers ? 8 : 0, Nano.this.size.getColumns() + 1);
      }

      private int computeCursorPosition(int cursor, int rwidth) {
         int cur = this.firstLineToDisplay;
         int off = this.offsetInLineToDisplay;

         while(cur < this.line || off < this.offsetInLine) {
            if (!Nano.this.wrapping) {
               cursor += rwidth;
               ++cur;
            } else {
               cursor += rwidth;
               Optional<Integer> next = this.nextLineOffset(cur, off);
               if (next.isPresent()) {
                  off = (Integer)next.get();
               } else {
                  ++cur;
                  off = 0;
               }
            }
         }

         if (cur != this.line) {
            throw new IllegalStateException();
         } else {
            if (!Nano.this.wrapping && this.column > this.firstColumnToDisplay + this.width()) {
               while(this.column > this.firstColumnToDisplay + this.width()) {
                  this.firstColumnToDisplay += this.width();
               }
            }

            cursor += this.column - this.firstColumnToDisplay + (this.firstColumnToDisplay > 0 ? 1 : 0);
            return cursor;
         }
      }

      char getCurrentChar() {
         String str = (String)this.lines.get(this.line);
         if (this.column + this.offsetInLine < str.length()) {
            return str.charAt(this.column + this.offsetInLine);
         } else {
            return (char)(this.line < this.lines.size() - 1 ? '\n' : '\u0000');
         }
      }

      public void prevWord() {
         while(Character.isAlphabetic(this.getCurrentChar()) && this.moveLeft(1)) {
         }

         while(!Character.isAlphabetic(this.getCurrentChar()) && this.moveLeft(1)) {
         }

         while(Character.isAlphabetic(this.getCurrentChar()) && this.moveLeft(1)) {
         }

         this.moveRight(1);
      }

      public void nextWord() {
         while(Character.isAlphabetic(this.getCurrentChar()) && this.moveRight(1)) {
         }

         while(!Character.isAlphabetic(this.getCurrentChar()) && this.moveRight(1)) {
         }

      }

      public void beginningOfLine() {
         this.column = this.offsetInLine = 0;
         this.wantedColumn = 0;
         this.ensureCursorVisible();
      }

      public void endOfLine() {
         int x = this.length((String)this.lines.get(this.line));
         this.moveRight(x, true);
      }

      public void prevPage() {
         int height = Nano.this.size.getRows() - this.computeHeader().size() - Nano.this.computeFooter().size();
         this.scrollUp(height - 2);
         this.column = 0;
         this.firstLineToDisplay = this.line;
         this.offsetInLineToDisplay = this.offsetInLine;
      }

      public void nextPage() {
         int height = Nano.this.size.getRows() - this.computeHeader().size() - Nano.this.computeFooter().size();
         this.scrollDown(height - 2);
         this.column = 0;
         this.firstLineToDisplay = this.line;
         this.offsetInLineToDisplay = this.offsetInLine;
      }

      public void scrollUp(int lines) {
         this.cursorUp(lines);
         this.moveDisplayUp(lines);
      }

      public void scrollDown(int lines) {
         this.cursorDown(lines);
         this.moveDisplayDown(lines);
      }

      public void firstLine() {
         this.line = 0;
         this.offsetInLine = this.column = 0;
         this.ensureCursorVisible();
      }

      public void lastLine() {
         this.line = this.lines.size() - 1;
         this.offsetInLine = this.column = 0;
         this.ensureCursorVisible();
      }

      boolean nextSearch() {
         boolean out = false;
         if (Nano.this.searchTerm == null) {
            Nano.this.setMessage("No current search pattern");
            return false;
         } else {
            Nano.this.setMessage((String)null);
            int cur = this.line;
            int dir = Nano.this.searchBackwards ? -1 : 1;
            int newPos = -1;
            int newLine = -1;
            List<Integer> curRes = this.doSearch((String)this.lines.get(this.line));
            if (Nano.this.searchBackwards) {
               Collections.reverse(curRes);
            }

            for(int r : curRes) {
               if (Nano.this.searchBackwards) {
                  if (r >= this.offsetInLine + this.column) {
                     continue;
                  }
               } else if (r <= this.offsetInLine + this.column) {
                  continue;
               }

               newPos = r;
               newLine = this.line;
               break;
            }

            if (newPos < 0) {
               while(true) {
                  cur = (cur + dir + this.lines.size()) % this.lines.size();
                  if (cur == this.line) {
                     break;
                  }

                  List<Integer> res = this.doSearch((String)this.lines.get(cur));
                  if (!res.isEmpty()) {
                     newPos = Nano.this.searchBackwards ? (Integer)res.get(res.size() - 1) : (Integer)res.get(0);
                     newLine = cur;
                     break;
                  }
               }
            }

            if (newPos < 0 && !curRes.isEmpty()) {
               newPos = (Integer)curRes.get(0);
               newLine = this.line;
            }

            if (newPos >= 0) {
               if (newLine == this.line && newPos == this.offsetInLine + this.column) {
                  Nano.this.setMessage("This is the only occurence");
                  return false;
               }

               if (Nano.this.searchBackwards && (newLine > this.line || newLine == this.line && newPos > this.offsetInLine + this.column) || !Nano.this.searchBackwards && (newLine < this.line || newLine == this.line && newPos < this.offsetInLine + this.column)) {
                  Nano.this.setMessage("Search Wrapped");
               }

               this.line = newLine;
               this.moveRight(newPos, true);
               out = true;
            } else {
               Nano.this.setMessage("\"" + Nano.this.searchTerm + "\" not found");
            }

            return out;
         }
      }

      private List doSearch(String text) {
         Pattern pat = Pattern.compile(Nano.this.searchTerm, (Nano.this.searchCaseSensitive ? 0 : 66) | (Nano.this.searchRegexp ? 0 : 16));
         Matcher m = pat.matcher(text);

         List<Integer> res;
         for(res = new ArrayList(); m.find(); Nano.this.matchedLength = m.group(0).length()) {
            res.add(m.start());
         }

         return res;
      }

      protected int[] highlightStart() {
         int[] out = new int[]{-1, -1};
         if (Nano.this.mark) {
            out = this.getMarkStart();
         } else if (Nano.this.searchToReplace) {
            out[0] = this.line;
            out[1] = this.offsetInLine + this.column;
         }

         return out;
      }

      protected int[] highlightEnd() {
         int[] out = new int[]{-1, -1};
         if (Nano.this.mark) {
            out = this.getMarkEnd();
         } else if (Nano.this.searchToReplace && Nano.this.matchedLength > 0) {
            out[0] = this.line;
            int col = this.charPosition(this.offsetInLine + this.column) + Nano.this.matchedLength;
            if (col < ((String)this.lines.get(this.line)).length()) {
               out[1] = this.length(((String)this.lines.get(this.line)).substring(0, col));
            } else {
               out[1] = this.length((String)this.lines.get(this.line));
            }
         }

         return out;
      }

      public void matching() {
         int opening = this.getCurrentChar();
         int idx = Nano.this.matchBrackets.indexOf(opening);
         if (idx < 0) {
            Nano.this.setMessage("Not a bracket");
         } else {
            int dir = idx >= Nano.this.matchBrackets.length() / 2 ? -1 : 1;
            int closing = Nano.this.matchBrackets.charAt((idx + Nano.this.matchBrackets.length() / 2) % Nano.this.matchBrackets.length());
            int lvl = 1;
            int cur = this.line;
            int pos = this.offsetInLine + this.column;

            while(true) {
               do {
                  if (pos + dir >= 0 && pos + dir < this.getLine(cur).length()) {
                     pos += dir;
                     break;
                  }

                  if (cur + dir < 0 || cur + dir >= this.lines.size()) {
                     Nano.this.setMessage("No matching bracket");
                     return;
                  }

                  cur += dir;
                  pos = dir > 0 ? 0 : ((String)this.lines.get(cur)).length() - 1;
               } while(pos < 0 || pos >= ((String)this.lines.get(cur)).length());

               int c = ((String)this.lines.get(cur)).charAt(pos);
               if (c == opening) {
                  ++lvl;
               } else if (c == closing) {
                  --lvl;
                  if (lvl == 0) {
                     this.line = cur;
                     this.moveToChar(pos);
                     this.ensureCursorVisible();
                     return;
                  }
               }
            }
         }
      }

      private int length(String line) {
         return (new AttributedStringBuilder()).tabs(Nano.this.tabs).append((CharSequence)line).columnLength();
      }

      void copy() {
         if (this.uncut || Nano.this.cut2end || Nano.this.mark) {
            Nano.this.cutbuffer = new ArrayList();
         }

         if (Nano.this.mark) {
            int[] s = this.getMarkStart();
            int[] e = this.getMarkEnd();
            if (s[0] == e[0]) {
               Nano.this.cutbuffer.add(((String)this.lines.get(s[0])).substring(this.charPosition(s[0], s[1]), this.charPosition(e[0], e[1])));
            } else {
               if (s[1] != 0) {
                  Nano.this.cutbuffer.add(((String)this.lines.get(s[0])).substring(this.charPosition(s[0], s[1])));
                  int var10002 = s[0]++;
               }

               for(int i = s[0]; i < e[0]; ++i) {
                  Nano.this.cutbuffer.add((String)this.lines.get(i));
               }

               if (e[1] != 0) {
                  Nano.this.cutbuffer.add(((String)this.lines.get(e[0])).substring(0, this.charPosition(e[0], e[1])));
               }
            }

            Nano.this.mark = false;
            this.mark();
         } else if (Nano.this.cut2end) {
            String l = (String)this.lines.get(this.line);
            int col = this.charPosition(this.offsetInLine + this.column);
            Nano.this.cutbuffer.add(l.substring(col));
            this.moveRight(l.substring(col).length());
         } else {
            Nano.this.cutbuffer.add((String)this.lines.get(this.line));
            this.cursorDown(1);
         }

         this.uncut = false;
      }

      void cut() {
         this.cut(false);
      }

      void cut(boolean toEnd) {
         if (this.lines.size() > 1) {
            if (this.uncut || Nano.this.cut2end || toEnd || Nano.this.mark) {
               Nano.this.cutbuffer = new ArrayList();
            }

            if (Nano.this.mark) {
               int[] s = this.getMarkStart();
               int[] e = this.getMarkEnd();
               if (s[0] == e[0]) {
                  String l = (String)this.lines.get(s[0]);
                  int cols = this.charPosition(s[0], s[1]);
                  int cole = this.charPosition(e[0], e[1]);
                  Nano.this.cutbuffer.add(l.substring(cols, cole));
                  this.lines.set(s[0], l.substring(0, cols) + l.substring(cole));
                  this.computeAllOffsets();
                  this.moveRight(cols, true);
               } else {
                  int ls = s[0];
                  int cs = this.charPosition(s[0], s[1]);
                  if (s[1] != 0) {
                     String l = (String)this.lines.get(s[0]);
                     Nano.this.cutbuffer.add(l.substring(cs));
                     this.lines.set(s[0], l.substring(0, cs));
                     int var10002 = s[0]++;
                  }

                  for(int i = s[0]; i < e[0]; ++i) {
                     Nano.this.cutbuffer.add((String)this.lines.get(s[0]));
                     this.lines.remove(s[0]);
                  }

                  if (e[1] != 0) {
                     String l = (String)this.lines.get(s[0]);
                     int col = this.charPosition(e[0], e[1]);
                     Nano.this.cutbuffer.add(l.substring(0, col));
                     this.lines.set(s[0], l.substring(col));
                  }

                  this.computeAllOffsets();
                  this.gotoLine(cs, ls);
               }

               Nano.this.mark = false;
               this.mark();
            } else if (!Nano.this.cut2end && !toEnd) {
               Nano.this.cutbuffer.add((String)this.lines.get(this.line));
               this.lines.remove(this.line);
               this.offsetInLine = 0;
               if (this.line > this.lines.size() - 1) {
                  --this.line;
               }
            } else {
               String l = (String)this.lines.get(this.line);
               int col = this.charPosition(this.offsetInLine + this.column);
               Nano.this.cutbuffer.add(l.substring(col));
               this.lines.set(this.line, l.substring(0, col));
               if (toEnd) {
                  ++this.line;

                  do {
                     Nano.this.cutbuffer.add((String)this.lines.get(this.line));
                     this.lines.remove(this.line);
                  } while(this.line <= this.lines.size() - 1);

                  --this.line;
               }
            }

            Nano.this.display.clear();
            this.computeAllOffsets();
            this.dirty = true;
            this.uncut = false;
         }

      }

      void uncut() {
         if (!Nano.this.cutbuffer.isEmpty()) {
            String l = (String)this.lines.get(this.line);
            int col = this.charPosition(this.offsetInLine + this.column);
            if (Nano.this.cut2end) {
               this.lines.set(this.line, l.substring(0, col) + (String)Nano.this.cutbuffer.get(0) + l.substring(col));
               this.computeAllOffsets();
               this.moveRight(col + ((String)Nano.this.cutbuffer.get(0)).length(), true);
            } else if (col == 0) {
               this.lines.addAll(this.line, Nano.this.cutbuffer);
               this.computeAllOffsets();
               if (Nano.this.cutbuffer.size() > 1) {
                  this.gotoLine(((String)Nano.this.cutbuffer.get(Nano.this.cutbuffer.size() - 1)).length(), this.line + Nano.this.cutbuffer.size());
               } else {
                  this.moveRight(((String)Nano.this.cutbuffer.get(0)).length(), true);
               }
            } else {
               int gotol = this.line;
               if (Nano.this.cutbuffer.size() == 1) {
                  this.lines.set(this.line, l.substring(0, col) + (String)Nano.this.cutbuffer.get(0) + l.substring(col));
               } else {
                  this.lines.set(this.line++, l.substring(0, col) + (String)Nano.this.cutbuffer.get(0));
                  gotol = this.line;
                  this.lines.add(this.line, (String)Nano.this.cutbuffer.get(Nano.this.cutbuffer.size() - 1) + l.substring(col));

                  for(int i = Nano.this.cutbuffer.size() - 2; i > 0; --i) {
                     ++gotol;
                     this.lines.add(this.line, (String)Nano.this.cutbuffer.get(i));
                  }
               }

               this.computeAllOffsets();
               if (Nano.this.cutbuffer.size() > 1) {
                  this.gotoLine(((String)Nano.this.cutbuffer.get(Nano.this.cutbuffer.size() - 1)).length(), gotol);
               } else {
                  this.moveRight(col + ((String)Nano.this.cutbuffer.get(0)).length(), true);
               }
            }

            Nano.this.display.clear();
            this.dirty = true;
            this.uncut = true;
         }
      }

      void mark() {
         if (Nano.this.mark) {
            this.markPos[0] = this.line;
            this.markPos[1] = this.offsetInLine + this.column;
         } else {
            this.markPos[0] = -1;
            this.markPos[1] = -1;
         }

      }

      int[] getMarkStart() {
         int[] out = new int[]{-1, -1};
         if (!Nano.this.mark) {
            return out;
         } else {
            if (this.markPos[0] <= this.line && (this.markPos[0] != this.line || this.markPos[1] <= this.offsetInLine + this.column)) {
               out = this.markPos;
            } else {
               out[0] = this.line;
               out[1] = this.offsetInLine + this.column;
            }

            return out;
         }
      }

      int[] getMarkEnd() {
         int[] out = new int[]{-1, -1};
         if (!Nano.this.mark) {
            return out;
         } else {
            if (this.markPos[0] <= this.line && (this.markPos[0] != this.line || this.markPos[1] <= this.offsetInLine + this.column)) {
               out[0] = this.line;
               out[1] = this.offsetInLine + this.column;
            } else {
               out = this.markPos;
            }

            return out;
         }
      }

      void replaceFromCursor(int chars, String string) {
         int pos = this.charPosition(this.offsetInLine + this.column);
         String text = (String)this.lines.get(this.line);
         String mod = text.substring(0, pos) + string;
         if (chars + pos < text.length()) {
            mod = mod + text.substring(chars + pos);
         }

         this.lines.set(this.line, mod);
         this.dirty = true;
      }
   }

   protected static class PatternHistory {
      private final Path historyFile;
      private final int size = 100;
      private List patterns = new ArrayList();
      private int patternId = -1;
      private boolean lastMoveUp = false;

      public PatternHistory(Path historyFile) {
         this.historyFile = historyFile;
         this.load();
      }

      public String up(String hint) {
         String out = hint;
         if (this.patterns.size() > 0 && this.patternId < this.patterns.size()) {
            if (!this.lastMoveUp && this.patternId > 0 && this.patternId < this.patterns.size() - 1) {
               ++this.patternId;
            }

            if (this.patternId < 0) {
               this.patternId = 0;
            }

            boolean found = false;

            for(int pid = this.patternId; pid < this.patterns.size(); ++pid) {
               if (hint.length() == 0 || ((String)this.patterns.get(pid)).startsWith(hint)) {
                  this.patternId = pid + 1;
                  out = (String)this.patterns.get(pid);
                  found = true;
                  break;
               }
            }

            if (!found) {
               this.patternId = this.patterns.size();
            }
         }

         this.lastMoveUp = true;
         return out;
      }

      public String down(String hint) {
         String out = hint;
         if (this.patterns.size() > 0) {
            if (this.lastMoveUp) {
               --this.patternId;
            }

            if (this.patternId < 0) {
               this.patternId = -1;
            } else {
               boolean found = false;

               for(int pid = this.patternId; pid >= 0; --pid) {
                  if (hint.length() == 0 || ((String)this.patterns.get(pid)).startsWith(hint)) {
                     this.patternId = pid - 1;
                     out = (String)this.patterns.get(pid);
                     found = true;
                     break;
                  }
               }

               if (!found) {
                  this.patternId = -1;
               }
            }
         }

         this.lastMoveUp = false;
         return out;
      }

      public void add(String pattern) {
         if (pattern.trim().length() != 0) {
            this.patterns.remove(pattern);
            if (this.patterns.size() > 100) {
               this.patterns.remove(this.patterns.size() - 1);
            }

            this.patterns.add(0, pattern);
            this.patternId = -1;
         }
      }

      public void persist() {
         if (this.historyFile != null) {
            try {
               BufferedWriter writer = Files.newBufferedWriter(this.historyFile.toAbsolutePath(), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

               try {
                  for(String s : this.patterns) {
                     if (s.trim().length() > 0) {
                        writer.append(s);
                        writer.newLine();
                     }
                  }
               } catch (Throwable var5) {
                  if (writer != null) {
                     try {
                        writer.close();
                     } catch (Throwable var4) {
                        var5.addSuppressed(var4);
                     }
                  }

                  throw var5;
               }

               if (writer != null) {
                  writer.close();
               }
            } catch (Exception var6) {
            }

         }
      }

      private void load() {
         if (this.historyFile != null) {
            try {
               if (Files.exists(this.historyFile, new LinkOption[0])) {
                  this.patterns = new ArrayList();
                  BufferedReader reader = Files.newBufferedReader(this.historyFile);

                  try {
                     reader.lines().forEach((line) -> this.patterns.add(line));
                  } catch (Throwable var5) {
                     if (reader != null) {
                        try {
                           reader.close();
                        } catch (Throwable var4) {
                           var5.addSuppressed(var4);
                        }
                     }

                     throw var5;
                  }

                  if (reader != null) {
                     reader.close();
                  }
               }
            } catch (Exception var6) {
            }

         }
      }
   }

   protected static enum Operation {
      DO_LOWER_CASE,
      QUIT,
      WRITE,
      READ,
      GOTO,
      FIND,
      WRAP,
      NUMBERS,
      SMOOTH_SCROLLING,
      MOUSE_SUPPORT,
      ONE_MORE_LINE,
      CLEAR_SCREEN,
      UP,
      DOWN,
      LEFT,
      RIGHT,
      INSERT,
      BACKSPACE,
      NEXT_BUFFER,
      PREV_BUFFER,
      HELP,
      NEXT_PAGE,
      PREV_PAGE,
      SCROLL_UP,
      SCROLL_DOWN,
      NEXT_WORD,
      PREV_WORD,
      BEGINNING_OF_LINE,
      END_OF_LINE,
      FIRST_LINE,
      LAST_LINE,
      CUR_POS,
      CASE_SENSITIVE,
      BACKWARDS,
      REGEXP,
      ACCEPT,
      CANCEL,
      SEARCH,
      TOGGLE_REPLACE,
      MAC_FORMAT,
      DOS_FORMAT,
      APPEND_MODE,
      PREPEND_MODE,
      BACKUP,
      TO_FILES,
      YES,
      NO,
      ALL,
      NEW_BUFFER,
      EXECUTE,
      NEXT_SEARCH,
      MATCHING,
      VERBATIM,
      DELETE,
      JUSTIFY_PARAGRAPH,
      TO_SPELL,
      CUT,
      REPLACE,
      MARK,
      COPY,
      INDENT,
      UNINDENT,
      BEGINNING_OF_PARAGRAPH,
      END_OF_PARAGRAPH,
      CUT_TO_END,
      JUSTIFY_FILE,
      COUNT,
      CONSTANT_CURSOR,
      WHITESPACE,
      HIGHLIGHT,
      SMART_HOME_KEY,
      AUTO_INDENT,
      CUT_TO_END_TOGGLE,
      TABS_TO_SPACE,
      UNCUT,
      MOUSE_EVENT,
      TOGGLE_SUSPENSION;

      // $FF: synthetic method
      private static Operation[] $values() {
         return new Operation[]{DO_LOWER_CASE, QUIT, WRITE, READ, GOTO, FIND, WRAP, NUMBERS, SMOOTH_SCROLLING, MOUSE_SUPPORT, ONE_MORE_LINE, CLEAR_SCREEN, UP, DOWN, LEFT, RIGHT, INSERT, BACKSPACE, NEXT_BUFFER, PREV_BUFFER, HELP, NEXT_PAGE, PREV_PAGE, SCROLL_UP, SCROLL_DOWN, NEXT_WORD, PREV_WORD, BEGINNING_OF_LINE, END_OF_LINE, FIRST_LINE, LAST_LINE, CUR_POS, CASE_SENSITIVE, BACKWARDS, REGEXP, ACCEPT, CANCEL, SEARCH, TOGGLE_REPLACE, MAC_FORMAT, DOS_FORMAT, APPEND_MODE, PREPEND_MODE, BACKUP, TO_FILES, YES, NO, ALL, NEW_BUFFER, EXECUTE, NEXT_SEARCH, MATCHING, VERBATIM, DELETE, JUSTIFY_PARAGRAPH, TO_SPELL, CUT, REPLACE, MARK, COPY, INDENT, UNINDENT, BEGINNING_OF_PARAGRAPH, END_OF_PARAGRAPH, CUT_TO_END, JUSTIFY_FILE, COUNT, CONSTANT_CURSOR, WHITESPACE, HIGHLIGHT, SMART_HOME_KEY, AUTO_INDENT, CUT_TO_END_TOGGLE, TABS_TO_SPACE, UNCUT, MOUSE_EVENT, TOGGLE_SUSPENSION};
      }
   }
}
