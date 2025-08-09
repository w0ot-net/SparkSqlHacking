package jline.console.completer;

import jline.internal.Preconditions;

public class EnumCompleter extends StringsCompleter {
   public EnumCompleter(Class source) {
      this(source, true);
   }

   public EnumCompleter(Class source, boolean toLowerCase) {
      Preconditions.checkNotNull(source);

      for(Enum n : (Enum[])source.getEnumConstants()) {
         this.getStrings().add(toLowerCase ? n.name().toLowerCase() : n.name());
      }

   }
}
