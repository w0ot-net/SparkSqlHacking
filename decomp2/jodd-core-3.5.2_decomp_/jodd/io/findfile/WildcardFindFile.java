package jodd.io.findfile;

import jodd.util.Wildcard;

public class WildcardFindFile extends FindFile {
   protected boolean match(String path, String pattern) {
      return Wildcard.matchPath(path, pattern);
   }
}
