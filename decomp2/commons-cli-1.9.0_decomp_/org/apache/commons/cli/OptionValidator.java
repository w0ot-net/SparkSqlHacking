package org.apache.commons.cli;

final class OptionValidator {
   static final char[] ADDITIONAL_OPTION_CHARS = new char[]{'?', '@'};
   static final char[] ADDITIONAL_LONG_CHARS = new char[]{'-'};

   private static boolean isValidChar(char c) {
      return Character.isJavaIdentifierPart(c) || search(ADDITIONAL_LONG_CHARS, c);
   }

   private static boolean isValidOpt(char c) {
      return Character.isJavaIdentifierPart(c) || search(ADDITIONAL_OPTION_CHARS, c);
   }

   private static boolean search(char[] chars, char c) {
      for(char a : chars) {
         if (a == c) {
            return true;
         }
      }

      return false;
   }

   static String validate(String option) throws IllegalArgumentException {
      if (option == null) {
         return null;
      } else if (option.isEmpty()) {
         throw new IllegalArgumentException("Empty option name.");
      } else {
         char[] chars = option.toCharArray();
         char ch0 = chars[0];
         if (!isValidOpt(ch0)) {
            throw new IllegalArgumentException(String.format("Illegal option name '%s'.", ch0));
         } else {
            if (option.length() > 1) {
               for(int i = 1; i < chars.length; ++i) {
                  char ch = chars[i];
                  if (!isValidChar(ch)) {
                     throw new IllegalArgumentException(String.format("The option '%s' contains an illegal character : '%s'.", option, ch));
                  }
               }
            }

            return option;
         }
      }
   }
}
