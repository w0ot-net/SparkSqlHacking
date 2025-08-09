package breeze.util;

public final class Terminal$ {
   public static final Terminal$ MODULE$ = new Terminal$();
   private static final int terminalWidth = 80;
   private static final int terminalHeight = 80;
   private static final String newline = "\n";

   public int terminalWidth() {
      return terminalWidth;
   }

   public int terminalHeight() {
      return terminalHeight;
   }

   public String newline() {
      return newline;
   }

   private Terminal$() {
   }
}
