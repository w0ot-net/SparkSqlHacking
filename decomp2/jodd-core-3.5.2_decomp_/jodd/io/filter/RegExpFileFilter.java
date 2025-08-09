package jodd.io.filter;

import java.io.File;
import java.util.regex.Pattern;

public class RegExpFileFilter extends FileFilterBase {
   private final Pattern regexpPattern;

   public RegExpFileFilter(String pattern) {
      this.regexpPattern = Pattern.compile(pattern);
   }

   public boolean accept(File dir, String name) {
      return this.regexpPattern.matcher(name).matches();
   }
}
