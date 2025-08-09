package jodd.io.filter;

import java.io.File;
import jodd.util.Wildcard;

public class WildcardFileFilter extends FileFilterBase {
   private final String pattern;

   public WildcardFileFilter(String pattern) {
      this.pattern = pattern;
   }

   public boolean accept(File dir, String name) {
      return Wildcard.matchPath(name, this.pattern);
   }
}
