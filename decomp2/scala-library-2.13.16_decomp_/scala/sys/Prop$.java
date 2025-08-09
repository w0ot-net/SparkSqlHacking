package scala.sys;

public final class Prop$ {
   public static final Prop$ MODULE$ = new Prop$();

   public Prop apply(final String key, final Prop.Creator evidence$1) {
      return evidence$1.apply(key);
   }

   private Prop$() {
   }
}
