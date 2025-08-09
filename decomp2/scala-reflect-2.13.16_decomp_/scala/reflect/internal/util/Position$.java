package scala.reflect.internal.util;

public final class Position$ {
   public static final Position$ MODULE$ = new Position$();

   public final int tabInc() {
      return 8;
   }

   private Position validate(final Position pos) {
      if (pos.isRange() && pos.start() > pos.end()) {
         throw new AssertionError((new StringBuilder(18)).append("assertion failed: ").append($anonfun$validate$1(pos)).toString());
      } else {
         return pos;
      }
   }

   public String formatMessage(final Position posIn, final String msg, final boolean shortenFile) {
      Position pos = (Position)(posIn == null ? NoPosition$.MODULE$ : posIn);
      SourceFile var6 = pos.source();
      String prefix = NoSourceFile$.MODULE$.equals(var6) ? "" : (shortenFile ? (new StringBuilder(1)).append(var6.file().name()).append(":").toString() : (new StringBuilder(1)).append(var6.file().path()).append(":").toString());
      return (new StringBuilder(0)).append(prefix).append(pos.showError(msg)).toString();
   }

   public Position offset(final SourceFile source, final int point) {
      return this.validate(new OffsetPosition(source, point));
   }

   public Position range(final SourceFile source, final int start, final int point, final int end) {
      return this.validate(new RangePosition(source, start, point, end));
   }

   public Position transparent(final SourceFile source, final int start, final int point, final int end) {
      return this.validate(new TransparentPosition(source, start, point, end));
   }

   // $FF: synthetic method
   public static final String $anonfun$validate$1(final Position pos$1) {
      return (new StringBuilder(14)).append("bad position: ").append(pos$1.show()).toString();
   }

   private Position$() {
   }
}
