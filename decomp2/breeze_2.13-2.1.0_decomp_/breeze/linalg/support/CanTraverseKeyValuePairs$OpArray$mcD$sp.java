package breeze.linalg.support;

import scala.runtime.RichInt.;

public class CanTraverseKeyValuePairs$OpArray$mcD$sp extends CanTraverseKeyValuePairs.OpArray {
   public void traverse(final double[] from, final CanTraverseKeyValuePairs.KeyValuePairsVisitor fn) {
      this.traverse$mcD$sp(from, fn);
   }

   public void traverse$mcD$sp(final double[] from, final CanTraverseKeyValuePairs.KeyValuePairsVisitor fn) {
      fn.visitArray$mcDI$sp(.MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), from.length), from);
   }

   public boolean isTraversableAgain(final double[] from) {
      return this.isTraversableAgain$mcD$sp(from);
   }

   public boolean isTraversableAgain$mcD$sp(final double[] from) {
      return true;
   }
}
