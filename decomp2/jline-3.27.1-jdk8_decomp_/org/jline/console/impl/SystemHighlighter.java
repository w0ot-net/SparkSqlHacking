package org.jline.console.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import org.jline.builtins.Styles;
import org.jline.builtins.SyntaxHighlighter;
import org.jline.console.SystemRegistry;
import org.jline.reader.LineReader;
import org.jline.reader.ParsedLine;
import org.jline.reader.Parser;
import org.jline.reader.impl.DefaultHighlighter;
import org.jline.utils.AttributedCharSequence;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;
import org.jline.utils.Log;
import org.jline.utils.OSUtils;
import org.jline.utils.StyleResolver;

public class SystemHighlighter extends DefaultHighlighter {
   private StyleResolver resolver = Styles.lsStyle();
   private static final String REGEX_COMMENT_LINE = "\\s*#.*";
   private static final String READER_COLORS = "READER_COLORS";
   protected final SyntaxHighlighter commandHighlighter;
   protected final SyntaxHighlighter argsHighlighter;
   protected final SyntaxHighlighter langHighlighter;
   protected final SystemRegistry systemRegistry;
   protected final Map fileHighlight = new HashMap();
   protected final Map specificHighlighter = new HashMap();
   protected int commandIndex;
   private final List externalHighlightersRefresh = new ArrayList();

   public SystemHighlighter(SyntaxHighlighter commandHighlighter, SyntaxHighlighter argsHighlighter, SyntaxHighlighter langHighlighter) {
      this.commandHighlighter = commandHighlighter;
      this.argsHighlighter = argsHighlighter;
      this.langHighlighter = langHighlighter;
      this.systemRegistry = SystemRegistry.get();
   }

   public void setSpecificHighlighter(String command, SyntaxHighlighter highlighter) {
      this.specificHighlighter.put(command, highlighter);
   }

   public void refresh(LineReader lineReader) {
      Path currentTheme = null;
      if (this.commandHighlighter != null) {
         this.commandHighlighter.refresh();
         currentTheme = this.compareThemes(this.commandHighlighter, currentTheme);
      }

      if (this.argsHighlighter != null) {
         this.argsHighlighter.refresh();
         currentTheme = this.compareThemes(this.argsHighlighter, currentTheme);
      }

      if (this.langHighlighter != null) {
         this.langHighlighter.refresh();
         currentTheme = this.compareThemes(this.langHighlighter, currentTheme);
      }

      for(SyntaxHighlighter sh : this.specificHighlighter.values()) {
         sh.refresh();
         currentTheme = this.compareThemes(sh, currentTheme);
      }

      if (currentTheme != null) {
         try {
            BufferedReader reader = new BufferedReader(new FileReader(currentTheme.toFile()));

            try {
               Map<String, String> tokens = new HashMap();

               String line;
               while((line = reader.readLine()) != null) {
                  String[] parts = line.trim().split("\\s+", 2);
                  if (parts[0].matches("[A-Z_]+") && parts.length == 2) {
                     tokens.put(parts[0], parts[1]);
                  }
               }

               SystemRegistry registry = SystemRegistry.get();
               registry.setConsoleOption("NANORC_THEME", tokens);
               Map<String, String> readerColors = (Map)registry.consoleOption("READER_COLORS", new HashMap());
               Styles.StyleCompiler styleCompiler = new Styles.StyleCompiler(readerColors);

               for(String key : readerColors.keySet()) {
                  lineReader.setVariable(key, styleCompiler.getStyle(key));
               }

               for(Supplier refresh : this.externalHighlightersRefresh) {
                  refresh.get();
               }

               this.resolver = Styles.lsStyle();
            } catch (Throwable var12) {
               try {
                  reader.close();
               } catch (Throwable var11) {
                  var12.addSuppressed(var11);
               }

               throw var12;
            }

            reader.close();
         } catch (IOException e) {
            Log.warn(e.getMessage());
         }
      }

   }

   public void addExternalHighlighterRefresh(Supplier refresh) {
      this.externalHighlightersRefresh.add(refresh);
   }

   private Path compareThemes(SyntaxHighlighter highlighter, Path currentTheme) {
      Path out;
      if (currentTheme != null) {
         Path theme = highlighter.getCurrentTheme();

         try {
            if (theme != null && !Files.isSameFile(theme, currentTheme)) {
               Log.warn("Multiple nanorc themes are in use!");
            }
         } catch (Exception e) {
            Log.warn(e.getMessage());
         }

         out = currentTheme;
      } else {
         out = highlighter.getCurrentTheme();
      }

      return out;
   }

   public AttributedString highlight(LineReader reader, String buffer) {
      return this.doDefaultHighlight(reader) ? super.highlight(reader, buffer) : this.systemHighlight(reader, buffer);
   }

   public void addFileHighlight(String... commands) {
      for(String c : commands) {
         this.fileHighlight.put(c, new FileHighlightCommand());
      }

   }

   public void addFileHighlight(String command, String subcommand, Collection fileOptions) {
      this.fileHighlight.put(command, new FileHighlightCommand(subcommand, fileOptions));
   }

   private boolean doDefaultHighlight(LineReader reader) {
      String search = reader.getSearchTerm();
      return search != null && search.length() > 0 || reader.getRegionActive() != LineReader.RegionType.NONE || this.errorIndex > -1 || this.errorPattern != null;
   }

   protected AttributedString systemHighlight(LineReader reader, String buffer) {
      Parser parser = reader.getParser();
      ParsedLine pl = parser.parse(buffer, 0, Parser.ParseContext.SPLIT_LINE);
      String command = pl.words().size() > 0 ? parser.getCommand((String)pl.words().get(0)) : "";
      command = command.startsWith("!") ? "!" : command;
      this.commandIndex = buffer.indexOf(command) + command.length();
      AttributedString out;
      if (buffer.trim().isEmpty()) {
         out = (new AttributedStringBuilder()).append((CharSequence)buffer).toAttributedString();
      } else if (this.specificHighlighter.containsKey(command)) {
         AttributedStringBuilder asb = new AttributedStringBuilder();
         if (this.commandHighlighter == null) {
            asb.append(((SyntaxHighlighter)this.specificHighlighter.get(command)).reset().highlight(buffer));
         } else {
            this.highlightCommand(buffer.substring(0, this.commandIndex), asb);
            asb.append(((SyntaxHighlighter)this.specificHighlighter.get(command)).reset().highlight(buffer.substring(this.commandIndex)));
         }

         out = asb.toAttributedString();
      } else if (this.fileHighlight.containsKey(command)) {
         FileHighlightCommand fhc = (FileHighlightCommand)this.fileHighlight.get(command);
         if (!fhc.hasFileOptions()) {
            out = this.doFileArgsHighlight(reader, buffer, pl.words(), fhc);
         } else {
            out = this.doFileOptsHighlight(reader, buffer, pl.words(), fhc);
         }
      } else if (!this.systemRegistry.isCommandOrScript(command) && !this.systemRegistry.isCommandAlias(command) && !command.isEmpty() && !buffer.matches("\\s*#.*")) {
         if (this.langHighlighter != null) {
            out = this.langHighlighter.reset().highlight(buffer);
         } else {
            out = (new AttributedStringBuilder()).append((CharSequence)buffer).toAttributedString();
         }
      } else {
         out = this.doCommandHighlight(buffer);
      }

      return out;
   }

   protected AttributedString doFileOptsHighlight(LineReader reader, String buffer, List words, FileHighlightCommand fhc) {
      AttributedStringBuilder asb = new AttributedStringBuilder();
      if (this.commandIndex < 0) {
         this.highlightCommand(buffer, asb);
      } else {
         this.highlightCommand(buffer.substring(0, this.commandIndex), asb);
         if (!fhc.isSubcommand() || words.size() > 2 && fhc.getSubcommand().equals(words.get(1))) {
            boolean subCommand = fhc.isSubcommand();
            int idx = buffer.indexOf((String)words.get(0)) + ((String)words.get(0)).length();
            boolean fileOption = false;

            for(int i = 1; i < words.size(); ++i) {
               int nextIdx = buffer.substring(idx).indexOf((String)words.get(i)) + idx;

               for(int j = idx; j < nextIdx; ++j) {
                  asb.append(buffer.charAt(j));
               }

               String word = (String)words.get(i);
               if (subCommand) {
                  subCommand = false;
                  this.highlightArgs(word, asb);
               } else if (word.contains("=") && fhc.getFileOptions().contains(word.substring(0, word.indexOf("=")))) {
                  this.highlightArgs(word.substring(0, word.indexOf("=") + 1), asb);
                  this.highlightFileArg(reader, word.substring(word.indexOf("=") + 1), asb);
               } else if (fhc.getFileOptions().contains(word)) {
                  this.highlightArgs(word, asb);
                  fileOption = true;
               } else if (fileOption) {
                  this.highlightFileArg(reader, word, asb);
               } else {
                  this.highlightArgs(word, asb);
                  fileOption = false;
               }

               idx = nextIdx + word.length();
            }
         } else {
            this.highlightArgs(buffer.substring(this.commandIndex), asb);
         }
      }

      return asb.toAttributedString();
   }

   protected AttributedString doFileArgsHighlight(LineReader reader, String buffer, List words, FileHighlightCommand fhc) {
      AttributedStringBuilder asb = new AttributedStringBuilder();
      if (this.commandIndex < 0) {
         this.highlightCommand(buffer, asb);
      } else {
         this.highlightCommand(buffer.substring(0, this.commandIndex), asb);
         if (!fhc.isSubcommand() || words.size() > 2 && fhc.getSubcommand().equals(words.get(1))) {
            boolean subCommand = fhc.isSubcommand();
            int idx = buffer.indexOf((String)words.get(0)) + ((String)words.get(0)).length();

            for(int i = 1; i < words.size(); ++i) {
               int nextIdx = buffer.substring(idx).indexOf((String)words.get(i)) + idx;

               for(int j = idx; j < nextIdx; ++j) {
                  asb.append(buffer.charAt(j));
               }

               if (subCommand) {
                  subCommand = false;
                  this.highlightArgs((String)words.get(i), asb);
               } else {
                  this.highlightFileArg(reader, (String)words.get(i), asb);
                  idx = nextIdx + ((String)words.get(i)).length();
               }
            }
         } else {
            this.highlightArgs(buffer.substring(this.commandIndex), asb);
         }
      }

      return asb.toAttributedString();
   }

   protected AttributedString doCommandHighlight(String buffer) {
      AttributedString out;
      if (this.commandHighlighter == null && this.argsHighlighter == null) {
         out = (new AttributedStringBuilder()).append((CharSequence)buffer).toAttributedString();
      } else {
         AttributedStringBuilder asb = new AttributedStringBuilder();
         if (this.commandIndex >= 0 && !buffer.matches("\\s*#.*")) {
            this.highlightCommand(buffer.substring(0, this.commandIndex), asb);
            this.highlightArgs(buffer.substring(this.commandIndex), asb);
         } else {
            this.highlightCommand(buffer, asb);
         }

         out = asb.toAttributedString();
      }

      return out;
   }

   private void highlightFileArg(LineReader reader, String arg, AttributedStringBuilder asb) {
      if (arg.startsWith("-")) {
         this.highlightArgs(arg, asb);
      } else {
         String separator = reader.isSet(LineReader.Option.USE_FORWARD_SLASH) ? "/" : Paths.get(System.getProperty("user.dir")).getFileSystem().getSeparator();
         StringBuilder sb = new StringBuilder();

         try {
            Path path = (new File(arg)).toPath();
            Iterator<Path> iterator = path.iterator();
            if (OSUtils.IS_WINDOWS && arg.matches("^[A-Za-z]:.*$")) {
               if (arg.length() == 2) {
                  sb.append(arg);
                  asb.append((CharSequence)arg);
               } else if (arg.charAt(2) == separator.charAt(0)) {
                  sb.append(arg.substring(0, 3));
                  asb.append((CharSequence)arg.substring(0, 3));
               }
            }

            if (arg.startsWith(separator)) {
               sb.append(separator);
               asb.append((CharSequence)separator);
            }

            while(iterator.hasNext()) {
               sb.append(iterator.next());
               this.highlightFile((new File(sb.toString())).toPath(), asb);
               if (iterator.hasNext()) {
                  sb.append(separator);
                  asb.append((CharSequence)separator);
               }
            }

            if (arg.length() > 2 && !arg.matches("^[A-Za-z]:" + separator) && arg.endsWith(separator)) {
               asb.append((CharSequence)separator);
            }
         } catch (Exception var8) {
            asb.append((CharSequence)arg);
         }
      }

   }

   private void highlightFile(Path path, AttributedStringBuilder asb) {
      AttributedStringBuilder sb = new AttributedStringBuilder();
      String name = path.getFileName().toString();
      int idx = name.lastIndexOf(".");
      String type = idx != -1 ? ".*" + name.substring(idx) : null;
      if (Files.isSymbolicLink(path)) {
         sb.styled((AttributedStyle)this.resolver.resolve(".ln"), (CharSequence)name);
      } else if (Files.isDirectory(path, new LinkOption[0])) {
         sb.styled((AttributedStyle)this.resolver.resolve(".di"), (CharSequence)name);
      } else if (Files.isExecutable(path) && !OSUtils.IS_WINDOWS) {
         sb.styled((AttributedStyle)this.resolver.resolve(".ex"), (CharSequence)name);
      } else if (type != null && this.resolver.resolve(type).getStyle() != 0L) {
         sb.styled((AttributedStyle)this.resolver.resolve(type), (CharSequence)name);
      } else if (Files.isRegularFile(path, new LinkOption[0])) {
         sb.styled((AttributedStyle)this.resolver.resolve(".fi"), (CharSequence)name);
      } else {
         sb.append((CharSequence)name);
      }

      asb.append((AttributedCharSequence)sb);
   }

   private void highlightArgs(String args, AttributedStringBuilder asb) {
      if (this.argsHighlighter != null) {
         asb.append(this.argsHighlighter.reset().highlight(args));
      } else {
         asb.append((CharSequence)args);
      }

   }

   private void highlightCommand(String command, AttributedStringBuilder asb) {
      if (this.commandHighlighter != null) {
         asb.append(this.commandHighlighter.reset().highlight(command));
      } else {
         asb.append((CharSequence)command);
      }

   }

   protected static class FileHighlightCommand {
      private final String subcommand;
      private final List fileOptions;

      public FileHighlightCommand() {
         this((String)null, new ArrayList());
      }

      public FileHighlightCommand(String subcommand, Collection fileOptions) {
         this.fileOptions = new ArrayList();
         this.subcommand = subcommand;
         this.fileOptions.addAll(fileOptions);
      }

      public boolean isSubcommand() {
         return this.subcommand != null;
      }

      public boolean hasFileOptions() {
         return !this.fileOptions.isEmpty();
      }

      public String getSubcommand() {
         return this.subcommand;
      }

      public List getFileOptions() {
         return this.fileOptions;
      }
   }
}
