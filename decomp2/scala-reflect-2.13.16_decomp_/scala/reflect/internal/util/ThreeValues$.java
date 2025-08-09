package scala.reflect.internal.util;

public final class ThreeValues$ {
   public static final ThreeValues$ MODULE$ = new ThreeValues$();

   public final int YES() {
      return 1;
   }

   public final int NO() {
      return -1;
   }

   public final int UNKNOWN() {
      return 0;
   }

   public byte fromBoolean(final boolean b) {
      return (byte)(b ? 1 : -1);
   }

   public boolean toBoolean(final byte x) {
      return x == 1;
   }

   private ThreeValues$() {
   }
}
