package io.fabric8.kubernetes.api;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.UnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pluralize implements UnaryOperator {
   private static final Pluralize INSTANCE = new Pluralize();
   private static final Set UNCOUNTABLE = new HashSet(Arrays.asList("equipment", "fish", "information", "money", "rice", "series", "sheep", "species", "news"));
   private static final Map EXCEPTIONS = new HashMap();
   private static final List PLURALS;

   public static String toPlural(String word) {
      return INSTANCE.apply(word);
   }

   public String apply(String word) {
      if (word != null && !word.isEmpty() && !UNCOUNTABLE.contains(word)) {
         String plural = (String)EXCEPTIONS.get(word);
         if (plural != null) {
            return plural;
         } else {
            for(UnaryOperator function : PLURALS) {
               String result = (String)function.apply(word);
               if (result != null) {
                  return result;
               }
            }

            return this.isAlreadyPlural(word) ? word : word + "s";
         }
      } else {
         return word;
      }
   }

   private boolean isAlreadyPlural(String word) {
      return !word.endsWith("ss") ? word.endsWith("s") : false;
   }

   static {
      EXCEPTIONS.put("person", "people");
      EXCEPTIONS.put("woman", "women");
      EXCEPTIONS.put("man", "men");
      EXCEPTIONS.put("child", "children");
      EXCEPTIONS.put("ox", "oxen");
      EXCEPTIONS.put("die", "dice");
      EXCEPTIONS.put("podmetrics", "pods");
      EXCEPTIONS.put("nodemetrics", "nodes");
      EXCEPTIONS.put("networkattachmentdefinition", "network-attachment-definitions");
      EXCEPTIONS.put("egressqos", "egressqoses");
      PLURALS = Arrays.asList(new StringReplace("([^aeiouy]|qu)y$", "$1ies"), new StringReplace("(x|ch|ss|sh)$", "$1es"), new StringReplace("(s)?ex$", "$1exes"), new StringReplace("(bus)$", "$1es"), new StringReplace("(quiz)$", "$1zes"), new StringReplace("(matr)ix$", "$1ices"), new StringReplace("(vert|ind)ex$", "$1ices"), new StringReplace("(alias|status|dns)$", "$1es"), new StringReplace("(octop|vir)us$", "$1us"), new StringReplace("(cris|ax|test)is$", "$1es"), new StringReplace("(o)$", "$1es"), new StringReplace("([m|l])ouse$", "$1ice"), new StringReplace("([lr])f$", "$1ves"), new StringReplace("([^f])fe$", "$1ves"), new StringReplace("(^analy)sis$", "$1sis"), new StringReplace("((a)naly|(b)a|(d)iagno|(p)arenthe|(p)rogno|(s)ynop|(t)he)ses$", "$1$2sis"), new StringReplace("([ti])um$", "$1a"), new StringReplace("(prometheus)$", "$1es"), new StringReplace("(s|si|u)s$", "$1s"));
   }

   private static class StringReplace implements UnaryOperator {
      private final String replacement;
      private final Pattern pattern;

      public StringReplace(String target, String replacement) {
         this.replacement = replacement;
         this.pattern = Pattern.compile(target, 2);
      }

      public String apply(String word) {
         Matcher matcher = this.pattern.matcher(word);
         return !matcher.find() ? null : matcher.replaceAll(this.replacement);
      }
   }
}
