package breeze.linalg.support;

import scala.runtime.RichInt.;

public class CanTraverseKeyValuePairs$OpArray$mcF$sp extends CanTraverseKeyValuePairs.OpArray {
   public void traverse(final float[] from, final CanTraverseKeyValuePairs.KeyValuePairsVisitor fn) {
      this.traverse$mcF$sp(from, fn);
   }

   public void traverse$mcF$sp(final float[] from, final CanTraverseKeyValuePairs.KeyValuePairsVisitor fn) {
      fn.visitArray$mcFI$sp(.MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), from.length), from);
   }

   public boolean isTraversableAgain(final float[] from) {
      return this.isTraversableAgain$mcF$sp(from);
   }

   public boolean isTraversableAgain$mcF$sp(final float[] from) {
      return true;
   }
}
