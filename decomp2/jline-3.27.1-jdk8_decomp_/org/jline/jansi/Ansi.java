package org.jline.jansi;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class Ansi implements Appendable {
   private static final char FIRST_ESC_CHAR = '\u001b';
   private static final char SECOND_ESC_CHAR = '[';
   public static final String DISABLE = Ansi.class.getName() + ".disable";
   private static Callable detector = () -> !Boolean.getBoolean(DISABLE);
   private static final InheritableThreadLocal holder = new InheritableThreadLocal() {
      protected Boolean initialValue() {
         return Ansi.isDetected();
      }
   };
   private final StringBuilder builder;
   private final ArrayList attributeOptions;

   public static void setDetector(Callable detector) {
      if (detector == null) {
         throw new IllegalArgumentException();
      } else {
         Ansi.detector = detector;
      }
   }

   public static boolean isDetected() {
      try {
         return (Boolean)detector.call();
      } catch (Exception var1) {
         return true;
      }
   }

   public static void setEnabled(boolean flag) {
      holder.set(flag);
   }

   public static boolean isEnabled() {
      return (Boolean)holder.get();
   }

   public static Ansi ansi() {
      return (Ansi)(isEnabled() ? new Ansi() : new NoAnsi());
   }

   public static Ansi ansi(StringBuilder builder) {
      return (Ansi)(isEnabled() ? new Ansi(builder) : new NoAnsi(builder));
   }

   public static Ansi ansi(int size) {
      return (Ansi)(isEnabled() ? new Ansi(size) : new NoAnsi(size));
   }

   public Ansi() {
      this(new StringBuilder(80));
   }

   public Ansi(Ansi parent) {
      this(new StringBuilder(parent.builder));
      this.attributeOptions.addAll(parent.attributeOptions);
   }

   public Ansi(int size) {
      this(new StringBuilder(size));
   }

   public Ansi(StringBuilder builder) {
      this.attributeOptions = new ArrayList(5);
      this.builder = builder;
   }

   public Ansi fg(Color color) {
      this.attributeOptions.add(color.fg());
      return this;
   }

   public Ansi fg(int color) {
      this.attributeOptions.add(38);
      this.attributeOptions.add(5);
      this.attributeOptions.add(color & 255);
      return this;
   }

   public Ansi fgRgb(String hex) {
      if (hex.startsWith("#")) {
         hex = hex.substring(1);
      }

      return this.fgRgb(Integer.parseInt(hex, 16));
   }

   public Ansi fgRgb(int color) {
      return this.fgRgb(color >> 16, color >> 8, color);
   }

   public Ansi fgRgb(int r, int g, int b) {
      this.attributeOptions.add(38);
      this.attributeOptions.add(2);
      this.attributeOptions.add(r & 255);
      this.attributeOptions.add(g & 255);
      this.attributeOptions.add(b & 255);
      return this;
   }

   public Ansi fgBlack() {
      return this.fg(Ansi.Color.BLACK);
   }

   public Ansi fgBlue() {
      return this.fg(Ansi.Color.BLUE);
   }

   public Ansi fgCyan() {
      return this.fg(Ansi.Color.CYAN);
   }

   public Ansi fgDefault() {
      return this.fg(Ansi.Color.DEFAULT);
   }

   public Ansi fgGreen() {
      return this.fg(Ansi.Color.GREEN);
   }

   public Ansi fgMagenta() {
      return this.fg(Ansi.Color.MAGENTA);
   }

   public Ansi fgRed() {
      return this.fg(Ansi.Color.RED);
   }

   public Ansi fgYellow() {
      return this.fg(Ansi.Color.YELLOW);
   }

   public Ansi bg(Color color) {
      this.attributeOptions.add(color.bg());
      return this;
   }

   public Ansi bg(int color) {
      this.attributeOptions.add(48);
      this.attributeOptions.add(5);
      this.attributeOptions.add(color & 255);
      return this;
   }

   public Ansi bgRgb(String hex) {
      if (hex.startsWith("#")) {
         hex = hex.substring(1);
      }

      return this.bgRgb(Integer.parseInt(hex, 16));
   }

   public Ansi bgRgb(int color) {
      return this.bgRgb(color >> 16, color >> 8, color);
   }

   public Ansi bgRgb(int r, int g, int b) {
      this.attributeOptions.add(48);
      this.attributeOptions.add(2);
      this.attributeOptions.add(r & 255);
      this.attributeOptions.add(g & 255);
      this.attributeOptions.add(b & 255);
      return this;
   }

   public Ansi bgCyan() {
      return this.bg(Ansi.Color.CYAN);
   }

   public Ansi bgDefault() {
      return this.bg(Ansi.Color.DEFAULT);
   }

   public Ansi bgGreen() {
      return this.bg(Ansi.Color.GREEN);
   }

   public Ansi bgMagenta() {
      return this.bg(Ansi.Color.MAGENTA);
   }

   public Ansi bgRed() {
      return this.bg(Ansi.Color.RED);
   }

   public Ansi bgYellow() {
      return this.bg(Ansi.Color.YELLOW);
   }

   public Ansi fgBright(Color color) {
      this.attributeOptions.add(color.fgBright());
      return this;
   }

   public Ansi fgBrightBlack() {
      return this.fgBright(Ansi.Color.BLACK);
   }

   public Ansi fgBrightBlue() {
      return this.fgBright(Ansi.Color.BLUE);
   }

   public Ansi fgBrightCyan() {
      return this.fgBright(Ansi.Color.CYAN);
   }

   public Ansi fgBrightDefault() {
      return this.fgBright(Ansi.Color.DEFAULT);
   }

   public Ansi fgBrightGreen() {
      return this.fgBright(Ansi.Color.GREEN);
   }

   public Ansi fgBrightMagenta() {
      return this.fgBright(Ansi.Color.MAGENTA);
   }

   public Ansi fgBrightRed() {
      return this.fgBright(Ansi.Color.RED);
   }

   public Ansi fgBrightYellow() {
      return this.fgBright(Ansi.Color.YELLOW);
   }

   public Ansi bgBright(Color color) {
      this.attributeOptions.add(color.bgBright());
      return this;
   }

   public Ansi bgBrightCyan() {
      return this.bgBright(Ansi.Color.CYAN);
   }

   public Ansi bgBrightDefault() {
      return this.bgBright(Ansi.Color.DEFAULT);
   }

   public Ansi bgBrightGreen() {
      return this.bgBright(Ansi.Color.GREEN);
   }

   public Ansi bgBrightMagenta() {
      return this.bgBright(Ansi.Color.MAGENTA);
   }

   public Ansi bgBrightRed() {
      return this.bgBright(Ansi.Color.RED);
   }

   public Ansi bgBrightYellow() {
      return this.bgBright(Ansi.Color.YELLOW);
   }

   public Ansi a(Attribute attribute) {
      this.attributeOptions.add(attribute.value());
      return this;
   }

   public Ansi cursor(int row, int column) {
      return this.appendEscapeSequence('H', Math.max(1, row), Math.max(1, column));
   }

   public Ansi cursorToColumn(int x) {
      return this.appendEscapeSequence('G', Math.max(1, x));
   }

   public Ansi cursorUp(int y) {
      return y > 0 ? this.appendEscapeSequence('A', y) : (y < 0 ? this.cursorDown(-y) : this);
   }

   public Ansi cursorDown(int y) {
      return y > 0 ? this.appendEscapeSequence('B', y) : (y < 0 ? this.cursorUp(-y) : this);
   }

   public Ansi cursorRight(int x) {
      return x > 0 ? this.appendEscapeSequence('C', x) : (x < 0 ? this.cursorLeft(-x) : this);
   }

   public Ansi cursorLeft(int x) {
      return x > 0 ? this.appendEscapeSequence('D', x) : (x < 0 ? this.cursorRight(-x) : this);
   }

   public Ansi cursorMove(int x, int y) {
      return this.cursorRight(x).cursorDown(y);
   }

   public Ansi cursorDownLine() {
      return this.appendEscapeSequence('E');
   }

   public Ansi cursorDownLine(int n) {
      return n < 0 ? this.cursorUpLine(-n) : this.appendEscapeSequence('E', n);
   }

   public Ansi cursorUpLine() {
      return this.appendEscapeSequence('F');
   }

   public Ansi cursorUpLine(int n) {
      return n < 0 ? this.cursorDownLine(-n) : this.appendEscapeSequence('F', n);
   }

   public Ansi eraseScreen() {
      return this.appendEscapeSequence('J', Ansi.Erase.ALL.value());
   }

   public Ansi eraseScreen(Erase kind) {
      return this.appendEscapeSequence('J', kind.value());
   }

   public Ansi eraseLine() {
      return this.appendEscapeSequence('K');
   }

   public Ansi eraseLine(Erase kind) {
      return this.appendEscapeSequence('K', kind.value());
   }

   public Ansi scrollUp(int rows) {
      if (rows == Integer.MIN_VALUE) {
         return this.scrollDown(Integer.MAX_VALUE);
      } else {
         return rows > 0 ? this.appendEscapeSequence('S', rows) : (rows < 0 ? this.scrollDown(-rows) : this);
      }
   }

   public Ansi scrollDown(int rows) {
      if (rows == Integer.MIN_VALUE) {
         return this.scrollUp(Integer.MAX_VALUE);
      } else {
         return rows > 0 ? this.appendEscapeSequence('T', rows) : (rows < 0 ? this.scrollUp(-rows) : this);
      }
   }

   public Ansi saveCursorPosition() {
      this.saveCursorPositionSCO();
      return this.saveCursorPositionDEC();
   }

   public Ansi saveCursorPositionSCO() {
      return this.appendEscapeSequence('s');
   }

   public Ansi saveCursorPositionDEC() {
      this.builder.append('\u001b');
      this.builder.append('7');
      return this;
   }

   public Ansi restoreCursorPosition() {
      this.restoreCursorPositionSCO();
      return this.restoreCursorPositionDEC();
   }

   public Ansi restoreCursorPositionSCO() {
      return this.appendEscapeSequence('u');
   }

   public Ansi restoreCursorPositionDEC() {
      this.builder.append('\u001b');
      this.builder.append('8');
      return this;
   }

   public Ansi reset() {
      return this.a(Ansi.Attribute.RESET);
   }

   public Ansi bold() {
      return this.a(Ansi.Attribute.INTENSITY_BOLD);
   }

   public Ansi boldOff() {
      return this.a(Ansi.Attribute.INTENSITY_BOLD_OFF);
   }

   public Ansi a(String value) {
      this.flushAttributes();
      this.builder.append(value);
      return this;
   }

   public Ansi a(boolean value) {
      this.flushAttributes();
      this.builder.append(value);
      return this;
   }

   public Ansi a(char value) {
      this.flushAttributes();
      this.builder.append(value);
      return this;
   }

   public Ansi a(char[] value, int offset, int len) {
      this.flushAttributes();
      this.builder.append(value, offset, len);
      return this;
   }

   public Ansi a(char[] value) {
      this.flushAttributes();
      this.builder.append(value);
      return this;
   }

   public Ansi a(CharSequence value, int start, int end) {
      this.flushAttributes();
      this.builder.append(value, start, end);
      return this;
   }

   public Ansi a(CharSequence value) {
      this.flushAttributes();
      this.builder.append(value);
      return this;
   }

   public Ansi a(double value) {
      this.flushAttributes();
      this.builder.append(value);
      return this;
   }

   public Ansi a(float value) {
      this.flushAttributes();
      this.builder.append(value);
      return this;
   }

   public Ansi a(int value) {
      this.flushAttributes();
      this.builder.append(value);
      return this;
   }

   public Ansi a(long value) {
      this.flushAttributes();
      this.builder.append(value);
      return this;
   }

   public Ansi a(Object value) {
      this.flushAttributes();
      this.builder.append(value);
      return this;
   }

   public Ansi a(StringBuffer value) {
      this.flushAttributes();
      this.builder.append(value);
      return this;
   }

   public Ansi newline() {
      this.flushAttributes();
      this.builder.append(System.getProperty("line.separator"));
      return this;
   }

   public Ansi format(String pattern, Object... args) {
      this.flushAttributes();
      this.builder.append(String.format(pattern, args));
      return this;
   }

   public Ansi apply(Consumer fun) {
      fun.apply(this);
      return this;
   }

   public Ansi render(String text) {
      this.a(AnsiRenderer.render(text));
      return this;
   }

   public Ansi render(String text, Object... args) {
      this.a(String.format(AnsiRenderer.render(text), args));
      return this;
   }

   public String toString() {
      this.flushAttributes();
      return this.builder.toString();
   }

   private Ansi appendEscapeSequence(char command) {
      this.flushAttributes();
      this.builder.append('\u001b');
      this.builder.append('[');
      this.builder.append(command);
      return this;
   }

   private Ansi appendEscapeSequence(char command, int option) {
      this.flushAttributes();
      this.builder.append('\u001b');
      this.builder.append('[');
      this.builder.append(option);
      this.builder.append(command);
      return this;
   }

   private Ansi appendEscapeSequence(char command, Object... options) {
      this.flushAttributes();
      return this._appendEscapeSequence(command, options);
   }

   private void flushAttributes() {
      if (!this.attributeOptions.isEmpty()) {
         if (this.attributeOptions.size() == 1 && (Integer)this.attributeOptions.get(0) == 0) {
            this.builder.append('\u001b');
            this.builder.append('[');
            this.builder.append('m');
         } else {
            this._appendEscapeSequence('m', this.attributeOptions.toArray());
         }

         this.attributeOptions.clear();
      }
   }

   private Ansi _appendEscapeSequence(char command, Object... options) {
      this.builder.append('\u001b');
      this.builder.append('[');
      int size = options.length;

      for(int i = 0; i < size; ++i) {
         if (i != 0) {
            this.builder.append(';');
         }

         if (options[i] != null) {
            this.builder.append(options[i]);
         }
      }

      this.builder.append(command);
      return this;
   }

   public Ansi append(CharSequence csq) {
      this.builder.append(csq);
      return this;
   }

   public Ansi append(CharSequence csq, int start, int end) {
      this.builder.append(csq, start, end);
      return this;
   }

   public Ansi append(char c) {
      this.builder.append(c);
      return this;
   }

   public static enum Color {
      BLACK(0, "BLACK"),
      RED(1, "RED"),
      GREEN(2, "GREEN"),
      YELLOW(3, "YELLOW"),
      BLUE(4, "BLUE"),
      MAGENTA(5, "MAGENTA"),
      CYAN(6, "CYAN"),
      WHITE(7, "WHITE"),
      DEFAULT(9, "DEFAULT");

      private final int value;
      private final String name;

      private Color(int index, String name) {
         this.value = index;
         this.name = name;
      }

      public String toString() {
         return this.name;
      }

      public int value() {
         return this.value;
      }

      public int fg() {
         return this.value + 30;
      }

      public int bg() {
         return this.value + 40;
      }

      public int fgBright() {
         return this.value + 90;
      }

      public int bgBright() {
         return this.value + 100;
      }

      // $FF: synthetic method
      private static Color[] $values() {
         return new Color[]{BLACK, RED, GREEN, YELLOW, BLUE, MAGENTA, CYAN, WHITE, DEFAULT};
      }
   }

   public static enum Attribute {
      RESET(0, "RESET"),
      INTENSITY_BOLD(1, "INTENSITY_BOLD"),
      INTENSITY_FAINT(2, "INTENSITY_FAINT"),
      ITALIC(3, "ITALIC_ON"),
      UNDERLINE(4, "UNDERLINE_ON"),
      BLINK_SLOW(5, "BLINK_SLOW"),
      BLINK_FAST(6, "BLINK_FAST"),
      NEGATIVE_ON(7, "NEGATIVE_ON"),
      CONCEAL_ON(8, "CONCEAL_ON"),
      STRIKETHROUGH_ON(9, "STRIKETHROUGH_ON"),
      UNDERLINE_DOUBLE(21, "UNDERLINE_DOUBLE"),
      INTENSITY_BOLD_OFF(22, "INTENSITY_BOLD_OFF"),
      ITALIC_OFF(23, "ITALIC_OFF"),
      UNDERLINE_OFF(24, "UNDERLINE_OFF"),
      BLINK_OFF(25, "BLINK_OFF"),
      NEGATIVE_OFF(27, "NEGATIVE_OFF"),
      CONCEAL_OFF(28, "CONCEAL_OFF"),
      STRIKETHROUGH_OFF(29, "STRIKETHROUGH_OFF");

      private final int value;
      private final String name;

      private Attribute(int index, String name) {
         this.value = index;
         this.name = name;
      }

      public String toString() {
         return this.name;
      }

      public int value() {
         return this.value;
      }

      // $FF: synthetic method
      private static Attribute[] $values() {
         return new Attribute[]{RESET, INTENSITY_BOLD, INTENSITY_FAINT, ITALIC, UNDERLINE, BLINK_SLOW, BLINK_FAST, NEGATIVE_ON, CONCEAL_ON, STRIKETHROUGH_ON, UNDERLINE_DOUBLE, INTENSITY_BOLD_OFF, ITALIC_OFF, UNDERLINE_OFF, BLINK_OFF, NEGATIVE_OFF, CONCEAL_OFF, STRIKETHROUGH_OFF};
      }
   }

   public static enum Erase {
      FORWARD(0, "FORWARD"),
      BACKWARD(1, "BACKWARD"),
      ALL(2, "ALL");

      private final int value;
      private final String name;

      private Erase(int index, String name) {
         this.value = index;
         this.name = name;
      }

      public String toString() {
         return this.name;
      }

      public int value() {
         return this.value;
      }

      // $FF: synthetic method
      private static Erase[] $values() {
         return new Erase[]{FORWARD, BACKWARD, ALL};
      }
   }

   private static class NoAnsi extends Ansi {
      public NoAnsi() {
      }

      public NoAnsi(int size) {
         super(size);
      }

      public NoAnsi(StringBuilder builder) {
         super(builder);
      }

      public Ansi fg(Color color) {
         return this;
      }

      public Ansi bg(Color color) {
         return this;
      }

      public Ansi fgBright(Color color) {
         return this;
      }

      public Ansi bgBright(Color color) {
         return this;
      }

      public Ansi fg(int color) {
         return this;
      }

      public Ansi fgRgb(int r, int g, int b) {
         return this;
      }

      public Ansi bg(int color) {
         return this;
      }

      public Ansi bgRgb(int r, int g, int b) {
         return this;
      }

      public Ansi a(Attribute attribute) {
         return this;
      }

      public Ansi cursor(int row, int column) {
         return this;
      }

      public Ansi cursorToColumn(int x) {
         return this;
      }

      public Ansi cursorUp(int y) {
         return this;
      }

      public Ansi cursorRight(int x) {
         return this;
      }

      public Ansi cursorDown(int y) {
         return this;
      }

      public Ansi cursorLeft(int x) {
         return this;
      }

      public Ansi cursorDownLine() {
         return this;
      }

      public Ansi cursorDownLine(int n) {
         return this;
      }

      public Ansi cursorUpLine() {
         return this;
      }

      public Ansi cursorUpLine(int n) {
         return this;
      }

      public Ansi eraseScreen() {
         return this;
      }

      public Ansi eraseScreen(Erase kind) {
         return this;
      }

      public Ansi eraseLine() {
         return this;
      }

      public Ansi eraseLine(Erase kind) {
         return this;
      }

      public Ansi scrollUp(int rows) {
         return this;
      }

      public Ansi scrollDown(int rows) {
         return this;
      }

      public Ansi saveCursorPosition() {
         return this;
      }

      public Ansi restoreCursorPosition() {
         return this;
      }

      public Ansi reset() {
         return this;
      }
   }

   @FunctionalInterface
   public interface Consumer {
      void apply(Ansi var1);
   }
}
