package org.apache.hive.beeline;

import jline.console.completer.StringsCompleter;

class BooleanCompleter extends StringsCompleter {
   public BooleanCompleter() {
      super(new String[]{"true", "false"});
   }
}
