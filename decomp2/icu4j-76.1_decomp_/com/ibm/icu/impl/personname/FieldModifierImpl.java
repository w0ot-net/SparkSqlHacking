package com.ibm.icu.impl.personname;

import com.ibm.icu.lang.UCharacter;
import com.ibm.icu.text.BreakIterator;
import com.ibm.icu.text.CaseMap;
import com.ibm.icu.text.PersonName;
import com.ibm.icu.text.SimpleFormatter;
import java.util.Locale;

abstract class FieldModifierImpl {
   private static final FieldModifierImpl NOOP_MODIFIER = new FieldModifierImpl() {
      public String modifyField(String fieldValue) {
         return fieldValue;
      }
   };
   private static final FieldModifierImpl NULL_MODIFIER = new FieldModifierImpl() {
      public String modifyField(String fieldValue) {
         return "";
      }
   };
   private static final FieldModifierImpl MONOGRAM_MODIFIER = new FieldModifierImpl() {
      public String modifyField(String fieldValue) {
         return FieldModifierImpl.getFirstGrapheme(fieldValue);
      }
   };

   public abstract String modifyField(String var1);

   public static FieldModifierImpl forName(PersonName.FieldModifier modifierID, PersonNameFormatterImpl formatterImpl) {
      switch (modifierID) {
         case INFORMAL:
            return NOOP_MODIFIER;
         case PREFIX:
            return NULL_MODIFIER;
         case CORE:
            return NOOP_MODIFIER;
         case ALL_CAPS:
            return new AllCapsModifier(formatterImpl.getLocale());
         case INITIAL_CAP:
            return new InitialCapModifier(formatterImpl.getLocale());
         case INITIAL:
            return new InitialModifier(formatterImpl.getLocale(), formatterImpl.getInitialPattern(), formatterImpl.getInitialSequencePattern());
         case RETAIN:
            return NOOP_MODIFIER;
         case MONOGRAM:
            return MONOGRAM_MODIFIER;
         case GENITIVE:
            return NOOP_MODIFIER;
         case VOCATIVE:
            return NOOP_MODIFIER;
         default:
            throw new IllegalArgumentException("Invalid modifier ID " + modifierID);
      }
   }

   private static String getFirstGrapheme(String s) {
      if (s.isEmpty()) {
         return "";
      } else {
         BreakIterator bi = BreakIterator.getCharacterInstance(Locale.ROOT);
         bi.setText(s);
         return s.substring(0, bi.next());
      }
   }

   private static class AllCapsModifier extends FieldModifierImpl {
      private final Locale locale;

      public AllCapsModifier(Locale locale) {
         this.locale = locale;
      }

      public String modifyField(String fieldValue) {
         return UCharacter.toUpperCase(this.locale, fieldValue);
      }
   }

   private static class InitialCapModifier extends FieldModifierImpl {
      private final Locale locale;
      private static final CaseMap.Title TO_TITLE_WHOLE_STRING_NO_LOWERCASE = CaseMap.toTitle().wholeString().noLowercase();

      public InitialCapModifier(Locale locale) {
         this.locale = locale;
      }

      public String modifyField(String fieldValue) {
         return TO_TITLE_WHOLE_STRING_NO_LOWERCASE.apply(this.locale, (BreakIterator)null, fieldValue);
      }
   }

   static class InitialModifier extends FieldModifierImpl {
      private final Locale locale;
      private final SimpleFormatter initialFormatter;
      private final SimpleFormatter initialSequenceFormatter;
      private boolean retainPunctuation;

      public InitialModifier(Locale locale, String initialPattern, String initialSequencePattern) {
         this.locale = locale;
         this.initialFormatter = SimpleFormatter.compile(initialPattern);
         this.initialSequenceFormatter = SimpleFormatter.compile(initialSequencePattern);
         this.retainPunctuation = false;
      }

      public void setRetainPunctuation(boolean retain) {
         this.retainPunctuation = retain;
      }

      public String modifyField(String fieldValue) {
         String separator = "";
         String result = null;
         BreakIterator bi = BreakIterator.getWordInstance(this.locale);
         bi.setText(fieldValue);
         int wordStart = bi.first();

         for(int wordEnd = bi.next(); wordEnd != -1; wordEnd = bi.next()) {
            String word = fieldValue.substring(wordStart, wordEnd);
            if (Character.isLetter(word.charAt(0))) {
               String curInitial = FieldModifierImpl.getFirstGrapheme(word);
               if (result == null) {
                  result = this.initialFormatter.format(curInitial);
               } else if (this.retainPunctuation) {
                  result = result + separator + this.initialFormatter.format(curInitial);
                  separator = "";
               } else {
                  result = this.initialSequenceFormatter.format(result, this.initialFormatter.format(curInitial));
               }
            } else if (Character.isWhitespace(word.charAt(0))) {
               separator = separator + word.charAt(0);
            } else {
               separator = separator + word;
            }

            wordStart = wordEnd;
         }

         return result;
      }
   }
}
