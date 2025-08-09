package jline;

public class UnsupportedTerminal extends TerminalSupport {
   public UnsupportedTerminal() {
      this(false, true);
   }

   public UnsupportedTerminal(boolean ansiSupported, boolean echoEnabled) {
      super(false);
      this.setAnsiSupported(ansiSupported);
      this.setEchoEnabled(echoEnabled);
   }
}
