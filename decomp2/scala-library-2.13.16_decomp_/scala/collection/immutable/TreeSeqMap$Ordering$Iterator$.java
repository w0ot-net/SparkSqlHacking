package scala.collection.immutable;

public class TreeSeqMap$Ordering$Iterator$ {
   public static final TreeSeqMap$Ordering$Iterator$ MODULE$ = new TreeSeqMap$Ordering$Iterator$();
   private static final TreeSeqMap$Ordering$Iterator Empty;

   static {
      TreeSeqMap.Ordering$ var10002 = TreeSeqMap.Ordering$.MODULE$;
      Empty = new TreeSeqMap$Ordering$Iterator(TreeSeqMap$Ordering$Zero$.MODULE$);
   }

   public TreeSeqMap$Ordering$Iterator Empty() {
      return Empty;
   }

   public TreeSeqMap$Ordering$Iterator empty() {
      return this.Empty();
   }
}
