package breeze.linalg.support;

import scala.runtime.RichInt.;

public class CanTraverseKeyValuePairs$OpArray$mcJ$sp extends CanTraverseKeyValuePairs.OpArray {
   public void traverse(final long[] from, final CanTraverseKeyValuePairs.KeyValuePairsVisitor fn) {
      this.traverse$mcJ$sp(from, fn);
   }

   public void traverse$mcJ$sp(final long[] from, final CanTraverseKeyValuePairs.KeyValuePairsVisitor fn) {
      fn.visitArray$mcJI$sp(.MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), from.length), from);
   }

   public boolean isTraversableAgain(final long[] from) {
      return this.isTraversableAgain$mcJ$sp(from);
   }

   public boolean isTraversableAgain$mcJ$sp(final long[] from) {
      return true;
   }
}
