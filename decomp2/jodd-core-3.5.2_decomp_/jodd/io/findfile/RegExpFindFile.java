package jodd.io.findfile;

import java.util.HashMap;
import java.util.regex.Pattern;

public class RegExpFindFile extends FindFile {
   private HashMap searchPatterns;

   protected boolean match(String path, String patternString) {
      if (this.searchPatterns == null) {
         this.searchPatterns = new HashMap();
      }

      Pattern pattern = (Pattern)this.searchPatterns.get(patternString);
      if (pattern == null) {
         pattern = Pattern.compile(patternString);
         this.searchPatterns.put(patternString, pattern);
      }

      return pattern.matcher(path).matches();
   }
}
