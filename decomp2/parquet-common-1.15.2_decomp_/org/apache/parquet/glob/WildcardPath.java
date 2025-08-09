package org.apache.parquet.glob;

import java.util.Objects;
import java.util.regex.Pattern;

public class WildcardPath {
   private static final String STAR_REGEX = "(.*)";
   private static final String MORE_NESTED_FIELDS_TEMPLATE = "((%s).*)?";
   private final String parentGlobPath;
   private final String originalPattern;
   private final Pattern pattern;

   public WildcardPath(String parentGlobPath, String wildcardPath, char delim) {
      this.parentGlobPath = (String)Objects.requireNonNull(parentGlobPath, "parentGlobPath cannot be null");
      this.originalPattern = (String)Objects.requireNonNull(wildcardPath, "wildcardPath cannot be null");
      this.pattern = Pattern.compile(buildRegex(wildcardPath, delim));
   }

   public static String buildRegex(String wildcardPath, char delim) {
      if (wildcardPath.isEmpty()) {
         return wildcardPath;
      } else {
         String delimStr = Pattern.quote(Character.toString(delim));
         String[] splits = wildcardPath.split("\\*", -1);
         StringBuilder regex = new StringBuilder();

         for(int i = 0; i < splits.length; ++i) {
            if ((i == 0 || i == splits.length - 1) && splits[i].isEmpty()) {
               regex.append("(.*)");
            } else if (!splits[i].isEmpty()) {
               regex.append(Pattern.quote(splits[i]));
               if (i < splits.length - 1) {
                  regex.append("(.*)");
               }
            }
         }

         regex.append(String.format("((%s).*)?", delimStr));
         return regex.toString();
      }
   }

   public boolean matches(String path) {
      return this.pattern.matcher(path).matches();
   }

   public String getParentGlobPath() {
      return this.parentGlobPath;
   }

   public String getOriginalPattern() {
      return this.originalPattern;
   }

   public String toString() {
      return String.format("WildcardPath(parentGlobPath: '%s', pattern: '%s')", this.parentGlobPath, this.originalPattern);
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         WildcardPath wildcardPath = (WildcardPath)o;
         return this.originalPattern.equals(wildcardPath.originalPattern);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.originalPattern.hashCode();
   }
}
