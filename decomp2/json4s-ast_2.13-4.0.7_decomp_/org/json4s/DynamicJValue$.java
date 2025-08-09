package org.json4s;

public final class DynamicJValue$ implements DynamicJValueImplicits {
   public static final DynamicJValue$ MODULE$ = new DynamicJValue$();

   static {
      DynamicJValueImplicits.$init$(MODULE$);
   }

   public JValue dynamic2Jv(final DynamicJValue dynJv) {
      return DynamicJValueImplicits.dynamic2Jv$(this, dynJv);
   }

   public JValue dynamic2monadic(final DynamicJValue dynJv) {
      return DynamicJValueImplicits.dynamic2monadic$(this, dynJv);
   }

   public DynamicJValue dyn(final JValue jv) {
      return DynamicJValueImplicits.dyn$(this, jv);
   }

   private DynamicJValue$() {
   }
}
