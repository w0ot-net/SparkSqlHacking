package org.jline.jansi;

public enum AnsiType {
   Native("Supports ansi sequences natively"),
   Unsupported("Ansi sequences are stripped out"),
   VirtualTerminal("Supported through windows virtual terminal"),
   Emulation("Emulated through using windows API console commands"),
   Redirected("The stream is redirected to a file or a pipe");

   private final String description;

   private AnsiType(String description) {
      this.description = description;
   }

   String getDescription() {
      return this.description;
   }

   // $FF: synthetic method
   private static AnsiType[] $values() {
      return new AnsiType[]{Native, Unsupported, VirtualTerminal, Emulation, Redirected};
   }
}
