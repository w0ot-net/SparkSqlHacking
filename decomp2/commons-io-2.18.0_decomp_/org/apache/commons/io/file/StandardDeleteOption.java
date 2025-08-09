package org.apache.commons.io.file;

import java.util.stream.Stream;
import org.apache.commons.io.IOUtils;

public enum StandardDeleteOption implements DeleteOption {
   OVERRIDE_READ_ONLY;

   public static boolean overrideReadOnly(DeleteOption[] options) {
      return IOUtils.length((Object[])options) == 0 ? false : Stream.of(options).anyMatch((e) -> OVERRIDE_READ_ONLY == e);
   }

   // $FF: synthetic method
   private static StandardDeleteOption[] $values() {
      return new StandardDeleteOption[]{OVERRIDE_READ_ONLY};
   }
}
