package scala.collection.concurrent;

import scala.math.Equiv;

public final class INode$ {
   public static final INode$ MODULE$ = new INode$();
   private static final Object KEY_PRESENT = new Object();
   private static final Object KEY_ABSENT = new Object();
   private static final Object KEY_PRESENT_OR_ABSENT = new Object();

   public final Object KEY_PRESENT() {
      return KEY_PRESENT;
   }

   public final Object KEY_ABSENT() {
      return KEY_ABSENT;
   }

   public final Object KEY_PRESENT_OR_ABSENT() {
      return KEY_PRESENT_OR_ABSENT;
   }

   public INode newRootNode(final Equiv equiv) {
      Gen gen = new Gen();
      CNode cn = new CNode(0, new BasicNode[0], gen);
      return new INode(cn, gen, equiv);
   }

   private INode$() {
   }
}
