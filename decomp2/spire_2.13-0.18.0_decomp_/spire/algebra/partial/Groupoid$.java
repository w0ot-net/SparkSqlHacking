package spire.algebra.partial;

import cats.kernel.Group;

public final class Groupoid$ implements GroupoidLowPriority {
   public static final Groupoid$ MODULE$ = new Groupoid$();

   static {
      GroupoidLowPriority.$init$(MODULE$);
   }

   public Groupoid fromGroup(final Group group) {
      return GroupoidLowPriority.fromGroup$(this, group);
   }

   public final Groupoid apply(final Groupoid g) {
      return g;
   }

   private Groupoid$() {
   }
}
