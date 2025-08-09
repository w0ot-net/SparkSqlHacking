package org.supercsv.comment;

public class CommentStartsWith implements CommentMatcher {
   private final String value;

   public CommentStartsWith(String value) {
      if (value == null) {
         throw new NullPointerException("value should not be null");
      } else if (value.length() == 0) {
         throw new IllegalArgumentException("value should not be empty");
      } else {
         this.value = value;
      }
   }

   public boolean isComment(String line) {
      return line.startsWith(this.value);
   }
}
