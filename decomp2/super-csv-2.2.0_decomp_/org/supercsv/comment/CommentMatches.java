package org.supercsv.comment;

import java.util.regex.Pattern;

public class CommentMatches implements CommentMatcher {
   private final Pattern pattern;

   public CommentMatches(String regex) {
      if (regex == null) {
         throw new NullPointerException("regex should not be null");
      } else if (regex.length() == 0) {
         throw new IllegalArgumentException("regex should not be empty");
      } else {
         this.pattern = Pattern.compile(regex);
      }
   }

   public boolean isComment(String line) {
      return this.pattern.matcher(line).matches();
   }
}
