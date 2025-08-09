package com.univocity.parsers.conversions;

public class TrimConversion implements Conversion {
   private final int length;

   public TrimConversion() {
      this.length = -1;
   }

   public TrimConversion(int length) {
      if (length < 0) {
         throw new IllegalArgumentException("Maximum trim length must be positive");
      } else {
         this.length = length;
      }
   }

   public String execute(String input) {
      if (input == null) {
         return null;
      } else if (input.length() == 0) {
         return input;
      } else if (this.length == -1) {
         return input.trim();
      } else {
         int begin;
         for(begin = 0; begin < input.length() && input.charAt(begin) <= ' '; ++begin) {
         }

         if (begin == input.length()) {
            return "";
         } else {
            int end = begin + (this.length < input.length() ? this.length : input.length()) - 1;
            if (end >= input.length()) {
               end = input.length() - 1;
            }

            while(input.charAt(end) <= ' ') {
               --end;
            }

            return input.substring(begin, end + 1);
         }
      }
   }

   public String revert(String input) {
      return this.execute(input);
   }
}
