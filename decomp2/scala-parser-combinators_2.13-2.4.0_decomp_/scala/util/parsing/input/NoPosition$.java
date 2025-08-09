package scala.util.parsing.input;

public final class NoPosition$ implements Position {
   public static final NoPosition$ MODULE$ = new NoPosition$();

   static {
      Position.$init$(MODULE$);
   }

   public boolean $less(final Position that) {
      return Position.$less$(this, that);
   }

   public boolean equals(final Object other) {
      return Position.equals$(this, other);
   }

   public int line() {
      return 0;
   }

   public int column() {
      return 0;
   }

   public String toString() {
      return "<undefined position>";
   }

   public String longString() {
      return this.toString();
   }

   public String lineContents() {
      return "";
   }

   private NoPosition$() {
   }
}
