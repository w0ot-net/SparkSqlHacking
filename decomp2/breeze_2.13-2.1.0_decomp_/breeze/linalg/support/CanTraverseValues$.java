package breeze.linalg.support;

public final class CanTraverseValues$ implements LowPrioCanTraverseValues {
   public static final CanTraverseValues$ MODULE$ = new CanTraverseValues$();

   static {
      LowPrioCanTraverseValues.$init$(MODULE$);
   }

   public CanTraverseValues canTraverseTraversable() {
      return LowPrioCanTraverseValues.canTraverseTraversable$(this);
   }

   public CanTraverseValues canTraverseIterator() {
      return LowPrioCanTraverseValues.canTraverseIterator$(this);
   }

   public CanTraverseValues.OpArray opArray() {
      return new CanTraverseValues.OpArray();
   }

   private CanTraverseValues$() {
   }
}
