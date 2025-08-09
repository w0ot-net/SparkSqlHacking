package org.apache.commons.codec.language;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.Resources;
import org.apache.commons.codec.StringEncoder;

public class DaitchMokotoffSoundex implements StringEncoder {
   private static final String COMMENT = "//";
   private static final String DOUBLE_QUOTE = "\"";
   private static final String MULTILINE_COMMENT_END = "*/";
   private static final String MULTILINE_COMMENT_START = "/*";
   private static final String RESOURCE_FILE = "/org/apache/commons/codec/language/dmrules.txt";
   private static final int MAX_LENGTH = 6;
   private static final Map RULES = new HashMap();
   private static final Map FOLDINGS = new HashMap();
   private final boolean folding;

   private static void parseRules(Scanner scanner, String location, Map ruleMapping, Map asciiFoldings) {
      int currentLine = 0;
      boolean inMultilineComment = false;

      while(scanner.hasNextLine()) {
         ++currentLine;
         String rawLine = scanner.nextLine();
         String line = rawLine;
         if (!inMultilineComment) {
            if (!rawLine.startsWith("/*")) {
               int cmtI = rawLine.indexOf("//");
               if (cmtI >= 0) {
                  line = rawLine.substring(0, cmtI);
               }

               line = line.trim();
               if (!line.isEmpty()) {
                  if (line.contains("=")) {
                     String[] parts = line.split("=");
                     if (parts.length != 2) {
                        throw new IllegalArgumentException("Malformed folding statement split into " + parts.length + " parts: " + rawLine + " in " + location);
                     }

                     String leftCharacter = parts[0];
                     String rightCharacter = parts[1];
                     if (leftCharacter.length() != 1 || rightCharacter.length() != 1) {
                        throw new IllegalArgumentException("Malformed folding statement - patterns are not single characters: " + rawLine + " in " + location);
                     }

                     asciiFoldings.put(leftCharacter.charAt(0), rightCharacter.charAt(0));
                  } else {
                     String[] parts = line.split("\\s+");
                     if (parts.length != 4) {
                        throw new IllegalArgumentException("Malformed rule statement split into " + parts.length + " parts: " + rawLine + " in " + location);
                     }

                     try {
                        String pattern = stripQuotes(parts[0]);
                        String replacement1 = stripQuotes(parts[1]);
                        String replacement2 = stripQuotes(parts[2]);
                        String replacement3 = stripQuotes(parts[3]);
                        Rule r = new Rule(pattern, replacement1, replacement2, replacement3);
                        char patternKey = r.pattern.charAt(0);
                        List<Rule> rules = (List)ruleMapping.computeIfAbsent(patternKey, (k) -> new ArrayList());
                        rules.add(r);
                     } catch (IllegalArgumentException e) {
                        throw new IllegalStateException("Problem parsing line '" + currentLine + "' in " + location, e);
                     }
                  }
               }
            } else {
               inMultilineComment = true;
            }
         } else if (rawLine.endsWith("*/")) {
            inMultilineComment = false;
         }
      }

   }

   private static String stripQuotes(String str) {
      if (str.startsWith("\"")) {
         str = str.substring(1);
      }

      if (str.endsWith("\"")) {
         str = str.substring(0, str.length() - 1);
      }

      return str;
   }

   public DaitchMokotoffSoundex() {
      this(true);
   }

   public DaitchMokotoffSoundex(boolean folding) {
      this.folding = folding;
   }

   private String cleanup(String input) {
      StringBuilder sb = new StringBuilder();

      for(char ch : input.toCharArray()) {
         if (!Character.isWhitespace(ch)) {
            ch = Character.toLowerCase(ch);
            Character character = (Character)FOLDINGS.get(ch);
            if (this.folding && character != null) {
               ch = character;
            }

            sb.append(ch);
         }
      }

      return sb.toString();
   }

   public Object encode(Object obj) throws EncoderException {
      if (!(obj instanceof String)) {
         throw new EncoderException("Parameter supplied to DaitchMokotoffSoundex encode is not of type java.lang.String");
      } else {
         return this.encode((String)obj);
      }
   }

   public String encode(String source) {
      return source == null ? null : this.soundex(source, false)[0];
   }

   public String soundex(String source) {
      return String.join("|", this.soundex(source, true));
   }

   private String[] soundex(String source, boolean branching) {
      if (source == null) {
         return null;
      } else {
         String input = this.cleanup(source);
         Set<Branch> currentBranches = new LinkedHashSet();
         currentBranches.add(new Branch());
         char lastChar = 0;

         for(int index = 0; index < input.length(); ++index) {
            char ch = input.charAt(index);
            if (!Character.isWhitespace(ch)) {
               String inputContext = input.substring(index);
               List<Rule> rules = (List)RULES.get(ch);
               if (rules != null) {
                  List<Branch> nextBranches = (List<Branch>)(branching ? new ArrayList() : Collections.emptyList());

                  for(Rule rule : rules) {
                     if (rule.matches(inputContext)) {
                        if (branching) {
                           nextBranches.clear();
                        }

                        String[] replacements = rule.getReplacements(inputContext, lastChar == 0);
                        boolean branchingRequired = replacements.length > 1 && branching;

                        for(Branch branch : currentBranches) {
                           for(String nextReplacement : replacements) {
                              Branch nextBranch = branchingRequired ? branch.createBranch() : branch;
                              boolean force = lastChar == 'm' && ch == 'n' || lastChar == 'n' && ch == 'm';
                              nextBranch.processNextReplacement(nextReplacement, force);
                              if (!branching) {
                                 break;
                              }

                              nextBranches.add(nextBranch);
                           }
                        }

                        if (branching) {
                           currentBranches.clear();
                           currentBranches.addAll(nextBranches);
                        }

                        index += rule.getPatternLength() - 1;
                        break;
                     }
                  }

                  lastChar = ch;
               }
            }
         }

         String[] result = new String[currentBranches.size()];
         int index = 0;

         for(Branch branch : currentBranches) {
            branch.finish();
            result[index++] = branch.toString();
         }

         return result;
      }
   }

   static {
      Scanner scanner = new Scanner(Resources.getInputStream("/org/apache/commons/codec/language/dmrules.txt"), CharEncoding.UTF_8);

      try {
         parseRules(scanner, "/org/apache/commons/codec/language/dmrules.txt", RULES, FOLDINGS);
      } catch (Throwable var4) {
         try {
            scanner.close();
         } catch (Throwable var3) {
            var4.addSuppressed(var3);
         }

         throw var4;
      }

      scanner.close();
      RULES.forEach((k, v) -> v.sort((rule1, rule2) -> rule2.getPatternLength() - rule1.getPatternLength()));
   }

   private static final class Branch {
      private final StringBuilder builder;
      private String cachedString;
      private String lastReplacement;

      private Branch() {
         this.builder = new StringBuilder();
         this.lastReplacement = null;
         this.cachedString = null;
      }

      public Branch createBranch() {
         Branch branch = new Branch();
         branch.builder.append(this.toString());
         branch.lastReplacement = this.lastReplacement;
         return branch;
      }

      public boolean equals(Object other) {
         if (this == other) {
            return true;
         } else {
            return !(other instanceof Branch) ? false : this.toString().equals(((Branch)other).toString());
         }
      }

      public void finish() {
         while(this.builder.length() < 6) {
            this.builder.append('0');
            this.cachedString = null;
         }

      }

      public int hashCode() {
         return this.toString().hashCode();
      }

      public void processNextReplacement(String replacement, boolean forceAppend) {
         boolean append = this.lastReplacement == null || !this.lastReplacement.endsWith(replacement) || forceAppend;
         if (append && this.builder.length() < 6) {
            this.builder.append(replacement);
            if (this.builder.length() > 6) {
               this.builder.delete(6, this.builder.length());
            }

            this.cachedString = null;
         }

         this.lastReplacement = replacement;
      }

      public String toString() {
         if (this.cachedString == null) {
            this.cachedString = this.builder.toString();
         }

         return this.cachedString;
      }
   }

   private static final class Rule {
      private final String pattern;
      private final String[] replacementAtStart;
      private final String[] replacementBeforeVowel;
      private final String[] replacementDefault;

      protected Rule(String pattern, String replacementAtStart, String replacementBeforeVowel, String replacementDefault) {
         this.pattern = pattern;
         this.replacementAtStart = replacementAtStart.split("\\|");
         this.replacementBeforeVowel = replacementBeforeVowel.split("\\|");
         this.replacementDefault = replacementDefault.split("\\|");
      }

      public int getPatternLength() {
         return this.pattern.length();
      }

      public String[] getReplacements(String context, boolean atStart) {
         if (atStart) {
            return this.replacementAtStart;
         } else {
            int nextIndex = this.getPatternLength();
            boolean nextCharIsVowel = nextIndex < context.length() && this.isVowel(context.charAt(nextIndex));
            return nextCharIsVowel ? this.replacementBeforeVowel : this.replacementDefault;
         }
      }

      private boolean isVowel(char ch) {
         return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
      }

      public boolean matches(String context) {
         return context.startsWith(this.pattern);
      }

      public String toString() {
         return String.format("%s=(%s,%s,%s)", this.pattern, Arrays.asList(this.replacementAtStart), Arrays.asList(this.replacementBeforeVowel), Arrays.asList(this.replacementDefault));
      }
   }
}
