package scala.io;

public final class Position$ extends Position {
   public static final Position$ MODULE$ = new Position$();

   public void checkInput(final int line, final int column) {
      if (line < 0) {
         throw new IllegalArgumentException((new StringBuilder(4)).append(line).append(" < 0").toString());
      } else if (line == 0 && column != 0 || column < 0) {
         throw new IllegalArgumentException((new StringBuilder(13)).append(line).append(",").append(column).append(" not allowed").toString());
      }
   }

   private Position$() {
   }
}
