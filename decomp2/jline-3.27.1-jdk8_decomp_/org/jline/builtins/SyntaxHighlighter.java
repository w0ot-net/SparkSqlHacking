package org.jline.builtins;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Stream;
import org.jline.utils.AttributedCharSequence;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;
import org.jline.utils.Log;
import org.jline.utils.StyleResolver;

public class SyntaxHighlighter {
   public static final String REGEX_TOKEN_NAME = "[A-Z_]+";
   public static final String TYPE_NANORCTHEME = ".nanorctheme";
   public static final String DEFAULT_NANORC_FILE = "jnanorc";
   protected static final String DEFAULT_LESSRC_FILE = "jlessrc";
   protected static final String COMMAND_INCLUDE = "include";
   protected static final String COMMAND_THEME = "theme";
   private static final String TOKEN_NANORC = "NANORC";
   private final Path nanorc;
   private final String syntaxName;
   private final String nanorcUrl;
   private final Map rules;
   private Path currentTheme;
   private boolean startEndHighlight;
   private int ruleStartId;
   private Parser parser;

   private SyntaxHighlighter() {
      this((Path)null, (String)null, (String)null);
   }

   private SyntaxHighlighter(String nanorcUrl) {
      this((Path)null, (String)null, nanorcUrl);
   }

   private SyntaxHighlighter(Path nanorc, String syntaxName) {
      this(nanorc, syntaxName, (String)null);
   }

   private SyntaxHighlighter(Path nanorc, String syntaxName, String nanorcUrl) {
      this.rules = new HashMap();
      this.ruleStartId = 0;
      this.nanorc = nanorc;
      this.syntaxName = syntaxName;
      this.nanorcUrl = nanorcUrl;
      Map<String, List<HighlightRule>> defaultRules = new HashMap();
      defaultRules.put("NANORC", new ArrayList());
      this.rules.putAll(defaultRules);
   }

   protected static SyntaxHighlighter build(List syntaxFiles, String file, String syntaxName) {
      return build(syntaxFiles, file, syntaxName, false);
   }

   protected static SyntaxHighlighter build(List syntaxFiles, String file, String syntaxName, boolean ignoreErrors) {
      SyntaxHighlighter out = new SyntaxHighlighter();
      Map<String, String> colorTheme = new HashMap();

      try {
         if (syntaxName == null || !syntaxName.equals("none")) {
            for(Path p : syntaxFiles) {
               try {
                  if (colorTheme.isEmpty() && p.getFileName().toString().endsWith(".nanorctheme")) {
                     out.setCurrentTheme(p);
                     BufferedReader reader = new BufferedReader(new FileReader(p.toFile()));

                     String line;
                     try {
                        while((line = reader.readLine()) != null) {
                           line = line.trim();
                           if (line.length() > 0 && !line.startsWith("#")) {
                              List<String> parts = Arrays.asList(line.split("\\s+", 2));
                              colorTheme.put((String)parts.get(0), (String)parts.get(1));
                           }
                        }
                     } catch (Throwable var12) {
                        try {
                           reader.close();
                        } catch (Throwable var11) {
                           var12.addSuppressed(var11);
                        }

                        throw var12;
                     }

                     reader.close();
                  } else {
                     NanorcParser nanorcParser = new NanorcParser(p, syntaxName, file, colorTheme);
                     nanorcParser.parse();
                     if (nanorcParser.matches()) {
                        out.addRules(nanorcParser.getHighlightRules());
                        out.setParser(nanorcParser.getParser());
                        return out;
                     }

                     if (nanorcParser.isDefault()) {
                        out.addRules(nanorcParser.getHighlightRules());
                     }
                  }
               } catch (IOException var13) {
               }
            }
         }
      } catch (PatternSyntaxException e) {
         if (!ignoreErrors) {
            throw e;
         }
      }

      return out;
   }

   public static SyntaxHighlighter build(Path nanorc, String syntaxName) {
      SyntaxHighlighter out = new SyntaxHighlighter(nanorc, syntaxName);
      List<Path> syntaxFiles = new ArrayList();

      try {
         BufferedReader reader = new BufferedReader(new FileReader(nanorc.toFile()));

         String line;
         try {
            while((line = reader.readLine()) != null) {
               line = line.trim();
               if (line.length() > 0 && !line.startsWith("#")) {
                  List<String> parts = SyntaxHighlighter.RuleSplitter.split(line);
                  if (((String)parts.get(0)).equals("include")) {
                     nanorcInclude((String)parts.get(1), syntaxFiles);
                  } else if (((String)parts.get(0)).equals("theme")) {
                     nanorcTheme((String)parts.get(1), syntaxFiles);
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
         SyntaxHighlighter sh = build(syntaxFiles, (String)null, syntaxName);
         out.addRules(sh.rules);
         out.setParser(sh.parser);
         out.setCurrentTheme(sh.currentTheme);
      } catch (Exception var9) {
      }

      return out;
   }

   protected static void nanorcInclude(String parameter, List syntaxFiles) throws IOException {
      addFiles(parameter, (s) -> {
         Objects.requireNonNull(syntaxFiles);
         s.forEach(syntaxFiles::add);
      });
   }

   protected static void nanorcTheme(String parameter, List syntaxFiles) throws IOException {
      addFiles(parameter, (s) -> s.findFirst().ifPresent((p) -> syntaxFiles.add(0, p)));
   }

   protected static void addFiles(String parameter, Consumer consumer) throws IOException {
      if (!parameter.contains("*") && !parameter.contains("?")) {
         consumer.accept(Stream.of(Paths.get(parameter)));
      } else {
         PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + parameter);
         Stream<Path> pathStream = Files.walk(Paths.get((new File(parameter)).getParent()));

         try {
            Objects.requireNonNull(pathMatcher);
            consumer.accept(pathStream.filter(pathMatcher::matches));
         } catch (Throwable var7) {
            if (pathStream != null) {
               try {
                  pathStream.close();
               } catch (Throwable var6) {
                  var7.addSuppressed(var6);
               }
            }

            throw var7;
         }

         if (pathStream != null) {
            pathStream.close();
         }
      }

   }

   public static SyntaxHighlighter build(String nanorcUrl) {
      SyntaxHighlighter out = new SyntaxHighlighter(nanorcUrl);

      try {
         InputStream inputStream;
         if (nanorcUrl.startsWith("classpath:")) {
            inputStream = (new Source.ResourceSource(nanorcUrl.substring(10), (String)null)).read();
         } else {
            inputStream = (new Source.URLSource((new URI(nanorcUrl)).toURL(), (String)null)).read();
         }

         NanorcParser parser = new NanorcParser(inputStream, (String)null, (String)null);
         parser.parse();
         out.addRules(parser.getHighlightRules());
      } catch (URISyntaxException | IOException var4) {
      }

      return out;
   }

   private void addRules(Map rules) {
      this.rules.putAll(rules);
   }

   public void setCurrentTheme(Path currentTheme) {
      this.currentTheme = currentTheme;
   }

   public Path getCurrentTheme() {
      return this.currentTheme;
   }

   public void setParser(Parser parser) {
      this.parser = parser;
   }

   public SyntaxHighlighter reset() {
      this.ruleStartId = 0;
      this.startEndHighlight = false;
      if (this.parser != null) {
         this.parser.reset();
      }

      return this;
   }

   public void refresh() {
      SyntaxHighlighter sh;
      if (this.nanorc != null && this.syntaxName != null) {
         sh = build(this.nanorc, this.syntaxName);
      } else {
         if (this.nanorcUrl == null) {
            throw new IllegalStateException("Not possible to refresh highlighter!");
         }

         sh = build(this.nanorcUrl);
      }

      this.rules.clear();
      this.addRules(sh.rules);
      this.parser = sh.parser;
      this.currentTheme = sh.currentTheme;
   }

   public AttributedString highlight(String string) {
      return this.splitAndHighlight(new AttributedString(string));
   }

   public AttributedString highlight(AttributedStringBuilder asb) {
      return this.splitAndHighlight(asb.toAttributedString());
   }

   public AttributedString highlight(AttributedString attributedString) {
      return this.splitAndHighlight(attributedString);
   }

   private AttributedString splitAndHighlight(AttributedString attributedString) {
      AttributedStringBuilder asb = new AttributedStringBuilder();
      boolean first = true;

      for(AttributedString line : attributedString.columnSplitLength(Integer.MAX_VALUE)) {
         if (!first) {
            asb.append((CharSequence)"\n");
         }

         List<ParsedToken> tokens = new ArrayList();
         if (this.parser != null) {
            this.parser.parse(line);
            tokens = this.parser.getTokens();
         }

         if (tokens.isEmpty()) {
            asb.append((AttributedCharSequence)this._highlight(line, (List)this.rules.get("NANORC")));
         } else {
            int pos = 0;

            for(ParsedToken t : tokens) {
               if (t.getStart() > pos) {
                  AttributedStringBuilder head = this._highlight(line.columnSubSequence(pos, t.getStart() + 1), (List)this.rules.get("NANORC"));
                  asb.append(head.columnSubSequence(0, head.length() - 1));
               }

               asb.append((AttributedCharSequence)this._highlight(line.columnSubSequence(t.getStart(), t.getEnd()), (List)this.rules.get(t.getName()), t.getStartWith(), line.columnSubSequence(t.getEnd(), line.length())));
               pos = t.getEnd();
            }

            if (pos < line.length()) {
               asb.append((AttributedCharSequence)this._highlight(line.columnSubSequence(pos, line.length()), (List)this.rules.get("NANORC")));
            }
         }

         first = false;
      }

      return asb.toAttributedString();
   }

   private AttributedStringBuilder _highlight(AttributedString line, List rules) {
      return this._highlight(line, rules, (CharSequence)null, (CharSequence)null);
   }

   private AttributedStringBuilder _highlight(AttributedString line, List rules, CharSequence startWith, CharSequence continueAs) {
      AttributedStringBuilder asb = new AttributedStringBuilder();
      asb.append(line);
      if (rules.isEmpty()) {
         return asb;
      } else {
         int startId = this.ruleStartId;
         boolean endHighlight = this.startEndHighlight;

         for(int i = startId; i < (endHighlight ? startId + 1 : rules.size()); ++i) {
            HighlightRule rule = (HighlightRule)rules.get(i);
            switch (rule.getType().ordinal()) {
               case 0:
                  asb.styleMatches(rule.getPattern(), rule.getStyle());
                  break;
               case 1:
                  boolean done = false;
                  Matcher start = rule.getStart().matcher(asb.toAttributedString());
                  Matcher end = rule.getEnd().matcher(asb.toAttributedString());

                  while(!done) {
                     AttributedStringBuilder a = new AttributedStringBuilder();
                     if (this.startEndHighlight && this.ruleStartId == i) {
                        if (end.find()) {
                           this.ruleStartId = 0;
                           this.startEndHighlight = false;
                           a.append(asb.columnSubSequence(0, end.end()), rule.getStyle());
                           a.append((AttributedCharSequence)this._highlight(asb.columnSubSequence(end.end(), asb.length()).toAttributedString(), rules));
                        } else {
                           a.append(asb, rule.getStyle());
                           done = true;
                        }

                        asb = a;
                     } else if (start.find()) {
                        a.append(asb.columnSubSequence(0, start.start()));
                        if (end.find()) {
                           a.append(asb.columnSubSequence(start.start(), end.end()), rule.getStyle());
                           a.append(asb.columnSubSequence(end.end(), asb.length()));
                        } else {
                           this.ruleStartId = i;
                           this.startEndHighlight = true;
                           a.append(asb.columnSubSequence(start.start(), asb.length()), rule.getStyle());
                           done = true;
                        }

                        asb = a;
                     } else {
                        done = true;
                     }
                  }
                  break;
               case 2:
                  if (startWith != null && startWith.toString().startsWith(rule.getStartWith())) {
                     asb.styleMatches(rule.getPattern(), rule.getStyle());
                  }
                  break;
               case 3:
                  if (continueAs != null && continueAs.toString().matches(rule.getContinueAs() + ".*")) {
                     asb.styleMatches(rule.getPattern(), rule.getStyle());
                  }
            }
         }

         return asb;
      }
   }

   private static class HighlightRule {
      private final RuleType type;
      private Pattern pattern;
      private final AttributedStyle style;
      private Pattern start;
      private Pattern end;
      private String startWith;
      private String continueAs;

      public HighlightRule(AttributedStyle style, Pattern pattern) {
         this.type = SyntaxHighlighter.HighlightRule.RuleType.PATTERN;
         this.pattern = pattern;
         this.style = style;
      }

      public HighlightRule(AttributedStyle style, Pattern start, Pattern end) {
         this.type = SyntaxHighlighter.HighlightRule.RuleType.START_END;
         this.style = style;
         this.start = start;
         this.end = end;
      }

      public HighlightRule(RuleType parserRuleType, AttributedStyle style, String value) {
         this.type = parserRuleType;
         this.style = style;
         this.pattern = Pattern.compile(".*");
         if (parserRuleType == SyntaxHighlighter.HighlightRule.RuleType.PARSER_START_WITH) {
            this.startWith = value;
         } else {
            if (parserRuleType != SyntaxHighlighter.HighlightRule.RuleType.PARSER_CONTINUE_AS) {
               throw new IllegalArgumentException("Bad RuleType: " + parserRuleType);
            }

            this.continueAs = value;
         }

      }

      public RuleType getType() {
         return this.type;
      }

      public AttributedStyle getStyle() {
         return this.style;
      }

      public Pattern getPattern() {
         if (this.type == SyntaxHighlighter.HighlightRule.RuleType.START_END) {
            throw new IllegalAccessError();
         } else {
            return this.pattern;
         }
      }

      public Pattern getStart() {
         if (this.type == SyntaxHighlighter.HighlightRule.RuleType.PATTERN) {
            throw new IllegalAccessError();
         } else {
            return this.start;
         }
      }

      public Pattern getEnd() {
         if (this.type == SyntaxHighlighter.HighlightRule.RuleType.PATTERN) {
            throw new IllegalAccessError();
         } else {
            return this.end;
         }
      }

      public String getStartWith() {
         return this.startWith;
      }

      public String getContinueAs() {
         return this.continueAs;
      }

      public static RuleType evalRuleType(List colorCfg) {
         RuleType out = null;
         if (((String)colorCfg.get(0)).equals("color") || ((String)colorCfg.get(0)).equals("icolor")) {
            out = SyntaxHighlighter.HighlightRule.RuleType.PATTERN;
            if (colorCfg.size() == 3) {
               if (((String)colorCfg.get(2)).startsWith("startWith=")) {
                  out = SyntaxHighlighter.HighlightRule.RuleType.PARSER_START_WITH;
               } else if (((String)colorCfg.get(2)).startsWith("continueAs=")) {
                  out = SyntaxHighlighter.HighlightRule.RuleType.PARSER_CONTINUE_AS;
               }
            } else if (colorCfg.size() == 4 && ((String)colorCfg.get(2)).startsWith("start=") && ((String)colorCfg.get(3)).startsWith("end=")) {
               out = SyntaxHighlighter.HighlightRule.RuleType.START_END;
            }
         }

         return out;
      }

      public String toString() {
         return "{type:" + this.type + ", startWith: " + this.startWith + ", continueAs: " + this.continueAs + ", start: " + this.start + ", end: " + this.end + ", pattern: " + this.pattern + "}";
      }

      public static enum RuleType {
         PATTERN,
         START_END,
         PARSER_START_WITH,
         PARSER_CONTINUE_AS;

         // $FF: synthetic method
         private static RuleType[] $values() {
            return new RuleType[]{PATTERN, START_END, PARSER_START_WITH, PARSER_CONTINUE_AS};
         }
      }
   }

   private static class NanorcParser {
      private static final String DEFAULT_SYNTAX = "default";
      private final String name;
      private final String target;
      private final Map highlightRules;
      private final BufferedReader reader;
      private Map colorTheme;
      private boolean matches;
      private String syntaxName;
      private Parser parser;

      public NanorcParser(Path file, String name, String target, Map colorTheme) throws IOException {
         this((new Source.PathSource(file, (String)null)).read(), name, target);
         this.colorTheme = colorTheme;
      }

      public NanorcParser(InputStream in, String name, String target) {
         this.highlightRules = new HashMap();
         this.colorTheme = new HashMap();
         this.matches = false;
         this.syntaxName = "unknown";
         this.reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
         this.name = name;
         this.target = target;
         this.highlightRules.put("NANORC", new ArrayList());
      }

      public void parse() throws IOException {
         int idx = 0;

         String line;
         try {
            while((line = this.reader.readLine()) != null) {
               ++idx;
               line = line.trim();
               if (line.length() > 0 && !line.startsWith("#")) {
                  List<String> parts = SyntaxHighlighter.RuleSplitter.split(this.fixRegexes(line));
                  if (!((String)parts.get(0)).equals("syntax")) {
                     if (((String)parts.get(0)).startsWith("$")) {
                        String key = this.themeKey((String)parts.get(0));
                        if (this.colorTheme.containsKey(key)) {
                           if (this.parser == null) {
                              this.parser = new Parser();
                           }

                           String[] args = ((String)parts.get(1)).split(",\\s*");
                           boolean validKey = true;
                           if (key.startsWith("$BLOCK_COMMENT")) {
                              this.parser.setBlockCommentDelimiters(key, args);
                           } else if (key.startsWith("$LINE_COMMENT")) {
                              this.parser.setLineCommentDelimiters(key, args);
                           } else if (key.startsWith("$BALANCED_DELIMITERS")) {
                              this.parser.setBalancedDelimiters(key, args);
                           } else {
                              Log.warn("Unknown token type: ", key);
                              validKey = false;
                           }

                           if (validKey) {
                              if (!this.highlightRules.containsKey(key)) {
                                 this.highlightRules.put(key, new ArrayList());
                              }

                              for(String l : ((String)this.colorTheme.get(key)).split("\\\\n")) {
                                 ++idx;
                                 this.addHighlightRule(SyntaxHighlighter.RuleSplitter.split(this.fixRegexes(l)), idx, key);
                              }
                           }
                        } else {
                           Log.warn("Unknown token type: ", key);
                        }
                     } else if (!this.addHighlightRule(parts, idx, "NANORC") && ((String)parts.get(0)).matches("\\+[A-Z_]+")) {
                        String key = this.themeKey((String)parts.get(0));
                        String theme = (String)this.colorTheme.get(key);
                        if (theme != null) {
                           for(String l : theme.split("\\\\n")) {
                              ++idx;
                              this.addHighlightRule(SyntaxHighlighter.RuleSplitter.split(this.fixRegexes(l)), idx, "NANORC");
                           }
                        } else {
                           Log.warn("Unknown token type: ", key);
                        }
                     }
                  } else {
                     this.syntaxName = (String)parts.get(1);
                     List<Pattern> filePatterns = new ArrayList();
                     if (this.name != null) {
                        if (!this.name.equals(this.syntaxName)) {
                           break;
                        }

                        this.matches = true;
                     } else if (this.target == null) {
                        this.matches = true;
                     } else {
                        for(int i = 2; i < parts.size(); ++i) {
                           filePatterns.add(Pattern.compile((String)parts.get(i)));
                        }

                        for(Pattern p : filePatterns) {
                           if (p.matcher(this.target).find()) {
                              this.matches = true;
                              break;
                           }
                        }

                        if (!this.matches && !this.syntaxName.equals("default")) {
                           break;
                        }
                     }
                  }
               }
            }
         } finally {
            this.reader.close();
         }

      }

      private String fixRegexes(String line) {
         return line.replaceAll("\\\\<", "\\\\b").replaceAll("\\\\>", "\\\\b").replaceAll("\\[:alnum:]", "\\\\p{Alnum}").replaceAll("\\[:alpha:]", "\\\\p{Alpha}").replaceAll("\\[:blank:]", "\\\\p{Blank}").replaceAll("\\[:cntrl:]", "\\\\p{Cntrl}").replaceAll("\\[:digit:]", "\\\\p{Digit}").replaceAll("\\[:graph:]", "\\\\p{Graph}").replaceAll("\\[:lower:]", "\\\\p{Lower}").replaceAll("\\[:print:]", "\\\\p{Print}").replaceAll("\\[:punct:]", "\\\\p{Punct}").replaceAll("\\[:space:]", "\\\\s").replaceAll("\\[:upper:]", "\\\\p{Upper}").replaceAll("\\[:xdigit:]", "\\\\p{XDigit}");
      }

      private boolean addHighlightRule(List parts, int idx, String tokenName) {
         boolean out = true;
         if (((String)parts.get(0)).equals("color")) {
            this.addHighlightRule(this.syntaxName + idx, parts, false, tokenName);
         } else if (((String)parts.get(0)).equals("icolor")) {
            this.addHighlightRule(this.syntaxName + idx, parts, true, tokenName);
         } else if (((String)parts.get(0)).matches("[A-Z_]+[:]?")) {
            String key = this.themeKey((String)parts.get(0));
            String theme = (String)this.colorTheme.get(key);
            if (theme != null) {
               parts.set(0, "color");
               parts.add(1, theme);
               this.addHighlightRule(this.syntaxName + idx, parts, false, tokenName);
            } else {
               Log.warn("Unknown token type: ", key);
            }
         } else if (((String)parts.get(0)).matches("~[A-Z_]+[:]?")) {
            String key = this.themeKey((String)parts.get(0));
            String theme = (String)this.colorTheme.get(key);
            if (theme != null) {
               parts.set(0, "icolor");
               parts.add(1, theme);
               this.addHighlightRule(this.syntaxName + idx, parts, true, tokenName);
            } else {
               Log.warn("Unknown token type: ", key);
            }
         } else {
            out = false;
         }

         return out;
      }

      private String themeKey(String key) {
         if (key.startsWith("+")) {
            return key;
         } else {
            int keyEnd = key.endsWith(":") ? key.length() - 1 : key.length();
            return key.startsWith("~") ? key.substring(1, keyEnd) : key.substring(0, keyEnd);
         }
      }

      public boolean matches() {
         return this.matches;
      }

      public Parser getParser() {
         return this.parser;
      }

      public Map getHighlightRules() {
         return this.highlightRules;
      }

      public boolean isDefault() {
         return this.syntaxName.equals("default");
      }

      private void addHighlightRule(String reference, List parts, boolean caseInsensitive, String tokenName) {
         Map<String, String> spec = new HashMap();
         spec.put(reference, (String)parts.get(1));
         Styles.StyleCompiler sh = new Styles.StyleCompiler(spec, true);
         Objects.requireNonNull(sh);
         AttributedStyle style = (new StyleResolver(sh::getStyle)).resolve("." + reference);
         if (SyntaxHighlighter.HighlightRule.evalRuleType(parts) == SyntaxHighlighter.HighlightRule.RuleType.PATTERN) {
            if (parts.size() == 2) {
               ((List)this.highlightRules.get(tokenName)).add(new HighlightRule(style, this.doPattern(".*", caseInsensitive)));
            } else {
               for(int i = 2; i < parts.size(); ++i) {
                  ((List)this.highlightRules.get(tokenName)).add(new HighlightRule(style, this.doPattern((String)parts.get(i), caseInsensitive)));
               }
            }
         } else if (SyntaxHighlighter.HighlightRule.evalRuleType(parts) == SyntaxHighlighter.HighlightRule.RuleType.START_END) {
            String s = (String)parts.get(2);
            String e = (String)parts.get(3);
            ((List)this.highlightRules.get(tokenName)).add(new HighlightRule(style, this.doPattern(s.substring(7, s.length() - 1), caseInsensitive), this.doPattern(e.substring(5, e.length() - 1), caseInsensitive)));
         } else if (SyntaxHighlighter.HighlightRule.evalRuleType(parts) == SyntaxHighlighter.HighlightRule.RuleType.PARSER_START_WITH) {
            ((List)this.highlightRules.get(tokenName)).add(new HighlightRule(SyntaxHighlighter.HighlightRule.RuleType.PARSER_START_WITH, style, ((String)parts.get(2)).substring(10)));
         } else if (SyntaxHighlighter.HighlightRule.evalRuleType(parts) == SyntaxHighlighter.HighlightRule.RuleType.PARSER_CONTINUE_AS) {
            ((List)this.highlightRules.get(tokenName)).add(new HighlightRule(SyntaxHighlighter.HighlightRule.RuleType.PARSER_CONTINUE_AS, style, ((String)parts.get(2)).substring(11)));
         }

      }

      private Pattern doPattern(String regex, boolean caseInsensitive) {
         return caseInsensitive ? Pattern.compile(regex, 2) : Pattern.compile(regex);
      }
   }

   protected static class RuleSplitter {
      protected static List split(String s) {
         List<String> out = new ArrayList();
         if (s.length() == 0) {
            return out;
         } else {
            int depth = 0;
            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < s.length(); ++i) {
               char c = s.charAt(i);
               if (c == '"') {
                  if (depth == 0) {
                     depth = 1;
                  } else {
                     char nextChar = i < s.length() - 1 ? s.charAt(i + 1) : 32;
                     if (nextChar == ' ') {
                        depth = 0;
                     }
                  }
               } else if (c == ' ' && depth == 0 && sb.length() > 0) {
                  out.add(stripQuotes(sb.toString()));
                  sb = new StringBuilder();
                  continue;
               }

               if (sb.length() > 0 || c != ' ' && c != '\t') {
                  sb.append(c);
               }
            }

            if (sb.length() > 0) {
               out.add(stripQuotes(sb.toString()));
            }

            return out;
         }
      }

      private static String stripQuotes(String s) {
         String out = s.trim();
         if (s.startsWith("\"") && s.endsWith("\"")) {
            out = s.substring(1, s.length() - 1);
         }

         return out;
      }
   }

   private static class BlockCommentDelimiters {
      private final String start;
      private final String end;

      public BlockCommentDelimiters(String[] args) {
         if (args.length == 2 && args[0] != null && args[1] != null && !args[0].isEmpty() && !args[1].isEmpty() && !args[0].equals(args[1])) {
            this.start = args[0];
            this.end = args[1];
         } else {
            throw new IllegalArgumentException("Bad block comment delimiters!");
         }
      }

      public String getStart() {
         return this.start;
      }

      public String getEnd() {
         return this.end;
      }
   }

   private static class ParsedToken {
      private final String name;
      private final CharSequence startWith;
      private final int start;
      private final int end;

      public ParsedToken(String name, CharSequence startWith, int start, int end) {
         this.name = name;
         this.startWith = startWith;
         this.start = start;
         this.end = end;
      }

      public String getName() {
         return this.name;
      }

      public CharSequence getStartWith() {
         return this.startWith;
      }

      public int getStart() {
         return this.start;
      }

      public int getEnd() {
         return this.end;
      }
   }

   private static class Parser {
      private static final char escapeChar = '\\';
      private String blockCommentTokenName;
      private BlockCommentDelimiters blockCommentDelimiters;
      private String lineCommentTokenName;
      private String[] lineCommentDelimiters;
      private String balancedDelimiterTokenName;
      private String[] balancedDelimiters;
      private String balancedDelimiter;
      private List tokens;
      private CharSequence startWith;
      private int tokenStart = 0;
      private boolean blockComment;
      private boolean lineComment;
      private boolean balancedQuoted;

      public Parser() {
      }

      public void setBlockCommentDelimiters(String tokenName, String[] args) {
         try {
            this.blockCommentTokenName = tokenName;
            this.blockCommentDelimiters = new BlockCommentDelimiters(args);
         } catch (Exception e) {
            Log.warn(e.getMessage());
         }

      }

      public void setLineCommentDelimiters(String tokenName, String[] args) {
         this.lineCommentTokenName = tokenName;
         this.lineCommentDelimiters = args;
      }

      public void setBalancedDelimiters(String tokenName, String[] args) {
         this.balancedDelimiterTokenName = tokenName;
         this.balancedDelimiters = args;
      }

      public void reset() {
         this.startWith = null;
         this.blockComment = false;
         this.lineComment = false;
         this.balancedQuoted = false;
         this.tokenStart = 0;
      }

      public void parse(CharSequence line) {
         if (line != null) {
            this.tokens = new ArrayList();
            if (this.blockComment || this.balancedQuoted) {
               this.tokenStart = 0;
            }

            for(int i = 0; i < line.length(); ++i) {
               if (!this.isEscapeChar(line, i) && !this.isEscaped(line, i)) {
                  if (!this.blockComment && !this.lineComment && !this.balancedQuoted) {
                     if (this.blockCommentDelimiters != null && this.isDelimiter(line, i, this.blockCommentDelimiters.getStart())) {
                        this.blockComment = true;
                        this.tokenStart = i;
                        this.startWith = this.startWithSubstring(line, i);
                        i = i + this.blockCommentDelimiters.getStart().length() - 1;
                     } else {
                        if (this.isLineCommentDelimiter(line, i)) {
                           this.lineComment = true;
                           this.tokenStart = i;
                           this.startWith = this.startWithSubstring(line, i);
                           break;
                        }

                        if ((this.balancedDelimiter = this.balancedDelimiter(line, i)) != null) {
                           this.balancedQuoted = true;
                           this.tokenStart = i;
                           this.startWith = this.startWithSubstring(line, i);
                           i = i + this.balancedDelimiter.length() - 1;
                        }
                     }
                  } else if (this.blockComment) {
                     if (this.isDelimiter(line, i, this.blockCommentDelimiters.getEnd())) {
                        this.blockComment = false;
                        i = i + this.blockCommentDelimiters.getEnd().length() - 1;
                        this.tokens.add(new ParsedToken(this.blockCommentTokenName, this.startWith, this.tokenStart, i + 1));
                     }
                  } else if (this.balancedQuoted && this.isDelimiter(line, i, this.balancedDelimiter)) {
                     this.balancedQuoted = false;
                     i = i + this.balancedDelimiter.length() - 1;
                     if (i - this.tokenStart + 1 > 2 * this.balancedDelimiter.length()) {
                        this.tokens.add(new ParsedToken(this.balancedDelimiterTokenName, this.startWith, this.tokenStart, i + 1));
                     }
                  }
               }
            }

            if (this.blockComment) {
               this.tokens.add(new ParsedToken(this.blockCommentTokenName, this.startWith, this.tokenStart, line.length()));
            } else if (this.lineComment) {
               this.lineComment = false;
               this.tokens.add(new ParsedToken(this.lineCommentTokenName, this.startWith, this.tokenStart, line.length()));
            } else if (this.balancedQuoted) {
               this.tokens.add(new ParsedToken(this.balancedDelimiterTokenName, this.startWith, this.tokenStart, line.length()));
            }

         }
      }

      private CharSequence startWithSubstring(CharSequence line, int pos) {
         return line.subSequence(pos, Math.min(pos + 5, line.length()));
      }

      public List getTokens() {
         return this.tokens;
      }

      private String balancedDelimiter(CharSequence buffer, int pos) {
         if (this.balancedDelimiters != null) {
            for(String delimiter : this.balancedDelimiters) {
               if (this.isDelimiter(buffer, pos, delimiter)) {
                  return delimiter;
               }
            }
         }

         return null;
      }

      private boolean isDelimiter(CharSequence buffer, int pos, String delimiter) {
         if (pos >= 0 && delimiter != null) {
            int length = delimiter.length();
            if (length <= buffer.length() - pos) {
               for(int i = 0; i < length; ++i) {
                  if (delimiter.charAt(i) != buffer.charAt(pos + i)) {
                     return false;
                  }
               }

               return true;
            } else {
               return false;
            }
         } else {
            return false;
         }
      }

      private boolean isLineCommentDelimiter(CharSequence buffer, int pos) {
         if (this.lineCommentDelimiters != null) {
            for(String delimiter : this.lineCommentDelimiters) {
               if (this.isDelimiter(buffer, pos, delimiter)) {
                  return true;
               }
            }
         }

         return false;
      }

      private boolean isEscapeChar(char ch) {
         return '\\' == ch;
      }

      private boolean isEscapeChar(CharSequence buffer, int pos) {
         if (pos < 0) {
            return false;
         } else {
            char ch = buffer.charAt(pos);
            return this.isEscapeChar(ch) && !this.isEscaped(buffer, pos);
         }
      }

      private boolean isEscaped(CharSequence buffer, int pos) {
         return pos <= 0 ? false : this.isEscapeChar(buffer, pos - 1);
      }
   }
}
