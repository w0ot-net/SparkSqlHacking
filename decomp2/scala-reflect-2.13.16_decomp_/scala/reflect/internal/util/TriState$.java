package scala.reflect.internal.util;

public final class TriState$ {
   public static final TriState$ MODULE$ = new TriState$();
   private static final int Unknown = -1;
   private static final int False = 0;
   private static final int True = 1;

   public int booleanToTriState(final boolean b) {
      return b ? this.True() : this.False();
   }

   public int Unknown() {
      return Unknown;
   }

   public int False() {
      return False;
   }

   public int True() {
      return True;
   }

   public final boolean isKnown$extension(final int $this) {
      return $this != this.Unknown();
   }

   public final boolean booleanValue$extension(final int $this) {
      if (this.True() == $this) {
         return true;
      } else if (this.False() == $this) {
         return false;
      } else {
         throw new IllegalStateException("Not a Boolean value");
      }
   }

   public final int hashCode$extension(final int $this) {
      return Integer.hashCode($this);
   }

   public final boolean equals$extension(final int $this, final Object x$1) {
      if (x$1 instanceof TriState) {
         int var3 = ((TriState)x$1).value();
         if ($this == var3) {
            return true;
         }
      }

      return false;
   }

   private TriState$() {
   }
}
