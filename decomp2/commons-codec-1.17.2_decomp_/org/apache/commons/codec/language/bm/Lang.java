package org.apache.commons.codec.language.bm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;
import org.apache.commons.codec.Resources;

public class Lang {
   private static final Map LANGS = new EnumMap(NameType.class);
   private static final String LANGUAGE_RULES_RN = "/org/apache/commons/codec/language/bm/%s_lang.txt";
   private final Languages languages;
   private final List rules;

   public static Lang instance(NameType nameType) {
      return (Lang)LANGS.get(nameType);
   }

   public static Lang loadFromResource(String languageRulesResourceName, Languages languages) {
      List<LangRule> rules = new ArrayList();
      Scanner scanner = new Scanner(Resources.getInputStream(languageRulesResourceName), ResourceConstants.ENCODING);

      try {
         boolean inExtendedComment = false;

         while(scanner.hasNextLine()) {
            String rawLine = scanner.nextLine();
            String line = rawLine;
            if (inExtendedComment) {
               if (rawLine.endsWith("*/")) {
                  inExtendedComment = false;
               }
            } else if (rawLine.startsWith("/*")) {
               inExtendedComment = true;
            } else {
               int cmtI = rawLine.indexOf("//");
               if (cmtI >= 0) {
                  line = rawLine.substring(0, cmtI);
               }

               line = line.trim();
               if (!line.isEmpty()) {
                  String[] parts = line.split("\\s+");
                  if (parts.length != 3) {
                     throw new IllegalArgumentException("Malformed line '" + rawLine + "' in language resource '" + languageRulesResourceName + "'");
                  }

                  Pattern pattern = Pattern.compile(parts[0]);
                  String[] langs = parts[1].split("\\+");
                  boolean accept = parts[2].equals("true");
                  rules.add(new LangRule(pattern, new HashSet(Arrays.asList(langs)), accept));
               }
            }
         }
      } catch (Throwable var13) {
         try {
            scanner.close();
         } catch (Throwable var12) {
            var13.addSuppressed(var12);
         }

         throw var13;
      }

      scanner.close();
      return new Lang(rules, languages);
   }

   private Lang(List rules, Languages languages) {
      this.rules = Collections.unmodifiableList(rules);
      this.languages = languages;
   }

   public String guessLanguage(String text) {
      Languages.LanguageSet ls = this.guessLanguages(text);
      return ls.isSingleton() ? ls.getAny() : "any";
   }

   public Languages.LanguageSet guessLanguages(String input) {
      String text = input.toLowerCase(Locale.ENGLISH);
      Set<String> langs = new HashSet(this.languages.getLanguages());
      this.rules.forEach((rule) -> {
         if (rule.matches(text)) {
            if (rule.acceptOnMatch) {
               langs.retainAll(rule.languages);
            } else {
               langs.removeAll(rule.languages);
            }
         }

      });
      Languages.LanguageSet ls = Languages.LanguageSet.from(langs);
      return ls.equals(Languages.NO_LANGUAGES) ? Languages.ANY_LANGUAGE : ls;
   }

   static {
      for(NameType s : NameType.values()) {
         LANGS.put(s, loadFromResource(String.format("/org/apache/commons/codec/language/bm/%s_lang.txt", s.getName()), Languages.getInstance(s)));
      }

   }

   private static final class LangRule {
      private final boolean acceptOnMatch;
      private final Set languages;
      private final Pattern pattern;

      private LangRule(Pattern pattern, Set languages, boolean acceptOnMatch) {
         this.pattern = pattern;
         this.languages = languages;
         this.acceptOnMatch = acceptOnMatch;
      }

      public boolean matches(String txt) {
         return this.pattern.matcher(txt).find();
      }
   }
}
