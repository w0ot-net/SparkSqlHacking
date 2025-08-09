package org.apache.commons.codec.language.bm;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.codec.Resources;

public class Languages {
   public static final String ANY = "any";
   private static final Map LANGUAGES = new EnumMap(NameType.class);
   public static final LanguageSet NO_LANGUAGES = new LanguageSet() {
      public boolean contains(String language) {
         return false;
      }

      public String getAny() {
         throw new NoSuchElementException("Can't fetch any language from the empty language set.");
      }

      public boolean isEmpty() {
         return true;
      }

      public boolean isSingleton() {
         return false;
      }

      public LanguageSet merge(LanguageSet other) {
         return other;
      }

      public LanguageSet restrictTo(LanguageSet other) {
         return this;
      }

      public String toString() {
         return "NO_LANGUAGES";
      }
   };
   public static final LanguageSet ANY_LANGUAGE = new LanguageSet() {
      public boolean contains(String language) {
         return true;
      }

      public String getAny() {
         throw new NoSuchElementException("Can't fetch any language from the any language set.");
      }

      public boolean isEmpty() {
         return false;
      }

      public boolean isSingleton() {
         return false;
      }

      public LanguageSet merge(LanguageSet other) {
         return other;
      }

      public LanguageSet restrictTo(LanguageSet other) {
         return other;
      }

      public String toString() {
         return "ANY_LANGUAGE";
      }
   };
   private final Set languages;

   public static Languages getInstance(NameType nameType) {
      return (Languages)LANGUAGES.get(nameType);
   }

   public static Languages getInstance(String languagesResourceName) {
      Set<String> ls = new HashSet();
      Scanner lsScanner = new Scanner(Resources.getInputStream(languagesResourceName), ResourceConstants.ENCODING);

      Languages var7;
      try {
         boolean inExtendedComment = false;

         while(lsScanner.hasNextLine()) {
            String line = lsScanner.nextLine().trim();
            if (inExtendedComment) {
               if (line.endsWith("*/")) {
                  inExtendedComment = false;
               }
            } else if (line.startsWith("/*")) {
               inExtendedComment = true;
            } else if (!line.isEmpty()) {
               ls.add(line);
            }
         }

         var7 = new Languages(Collections.unmodifiableSet(ls));
      } catch (Throwable var6) {
         try {
            lsScanner.close();
         } catch (Throwable var5) {
            var6.addSuppressed(var5);
         }

         throw var6;
      }

      lsScanner.close();
      return var7;
   }

   private static String langResourceName(NameType nameType) {
      return String.format("/org/apache/commons/codec/language/bm/%s_languages.txt", nameType.getName());
   }

   private Languages(Set languages) {
      this.languages = languages;
   }

   public Set getLanguages() {
      return this.languages;
   }

   static {
      for(NameType s : NameType.values()) {
         LANGUAGES.put(s, getInstance(langResourceName(s)));
      }

   }

   public abstract static class LanguageSet {
      public static LanguageSet from(Set languages) {
         return (LanguageSet)(languages.isEmpty() ? Languages.NO_LANGUAGES : new SomeLanguages(languages));
      }

      public abstract boolean contains(String var1);

      public abstract String getAny();

      public abstract boolean isEmpty();

      public abstract boolean isSingleton();

      abstract LanguageSet merge(LanguageSet var1);

      public abstract LanguageSet restrictTo(LanguageSet var1);
   }

   public static final class SomeLanguages extends LanguageSet {
      private final Set languages;

      private SomeLanguages(Set languages) {
         this.languages = Collections.unmodifiableSet(languages);
      }

      public boolean contains(String language) {
         return this.languages.contains(language);
      }

      public String getAny() {
         return (String)this.languages.iterator().next();
      }

      public Set getLanguages() {
         return this.languages;
      }

      public boolean isEmpty() {
         return this.languages.isEmpty();
      }

      public boolean isSingleton() {
         return this.languages.size() == 1;
      }

      public LanguageSet merge(LanguageSet other) {
         if (other == Languages.NO_LANGUAGES) {
            return this;
         } else if (other == Languages.ANY_LANGUAGE) {
            return other;
         } else {
            SomeLanguages someLanguages = (SomeLanguages)other;
            Set<String> set = new HashSet(this.languages);
            set.addAll(someLanguages.languages);
            return from(set);
         }
      }

      public LanguageSet restrictTo(LanguageSet other) {
         if (other == Languages.NO_LANGUAGES) {
            return other;
         } else if (other == Languages.ANY_LANGUAGE) {
            return this;
         } else {
            SomeLanguages someLanguages = (SomeLanguages)other;
            return from((Set)this.languages.stream().filter((lang) -> someLanguages.languages.contains(lang)).collect(Collectors.toSet()));
         }
      }

      public String toString() {
         return "Languages(" + this.languages.toString() + ")";
      }
   }
}
