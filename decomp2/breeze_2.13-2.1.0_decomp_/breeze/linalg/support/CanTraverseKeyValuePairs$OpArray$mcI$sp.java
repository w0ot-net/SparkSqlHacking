package breeze.linalg.support;

import scala.runtime.RichInt.;

public class CanTraverseKeyValuePairs$OpArray$mcI$sp extends CanTraverseKeyValuePairs.OpArray {
   public void traverse(final int[] from, final CanTraverseKeyValuePairs.KeyValuePairsVisitor fn) {
      this.traverse$mcI$sp(from, fn);
   }

   public void traverse$mcI$sp(final int[] from, final CanTraverseKeyValuePairs.KeyValuePairsVisitor fn) {
      fn.visitArray$mcII$sp(.MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), from.length), from);
   }

   public boolean isTraversableAgain(final int[] from) {
      return this.isTraversableAgain$mcI$sp(from);
   }

   public boolean isTraversableAgain$mcI$sp(final int[] from) {
      return true;
   }
}
