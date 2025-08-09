package breeze.linalg.operators;

import breeze.linalg.SparseVector;
import breeze.linalg.support.CanTraverseValues;
import scala.Function2;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M2qa\u0001\u0003\u0011\u0002\u0007\u00051\u0002C\u0003\u0017\u0001\u0011\u0005q\u0003C\u0003\u001c\u0001\u0011\rADA\rTa\u0006\u00148/\u001a,fGR|'o\u0018+sCZ,'o]1m\u001fB\u001c(BA\u0003\u0007\u0003%y\u0007/\u001a:bi>\u00148O\u0003\u0002\b\u0011\u00051A.\u001b8bY\u001eT\u0011!C\u0001\u0007EJ,WM_3\u0004\u0001M\u0019\u0001\u0001\u0004\n\u0011\u00055\u0001R\"\u0001\b\u000b\u0003=\tQa]2bY\u0006L!!\u0005\b\u0003\r\u0005s\u0017PU3g!\t\u0019B#D\u0001\u0005\u0013\t)BAA\nWK\u000e$xN]0Ue\u00064XM]:bY>\u00038/\u0001\u0004%S:LG\u000f\n\u000b\u00021A\u0011Q\"G\u0005\u000359\u0011A!\u00168ji\u0006\u00192-\u00198Ji\u0016\u0014\u0018\r^3WC2,Xm]0T-V\u0011QDK\u000b\u0002=A!qD\t\u0013)\u001b\u0005\u0001#BA\u0011\u0007\u0003\u001d\u0019X\u000f\u001d9peRL!a\t\u0011\u0003#\r\u000bg\u000e\u0016:bm\u0016\u00148/\u001a,bYV,7\u000fE\u0002&M!j\u0011AB\u0005\u0003O\u0019\u0011Ab\u00159beN,g+Z2u_J\u0004\"!\u000b\u0016\r\u0001\u0011)1F\u0001b\u0001Y\t\ta+\u0005\u0002.aA\u0011QBL\u0005\u0003_9\u0011qAT8uQ&tw\r\u0005\u0002\u000ec%\u0011!G\u0004\u0002\u0004\u0003:L\b"
)
public interface SparseVector_TraversalOps extends Vector_TraversalOps {
   // $FF: synthetic method
   static CanTraverseValues canIterateValues_SV$(final SparseVector_TraversalOps $this) {
      return $this.canIterateValues_SV();
   }

   default CanTraverseValues canIterateValues_SV() {
      return new CanTraverseValues() {
         public Object foldLeft(final Object from, final Object b, final Function2 fn) {
            return CanTraverseValues.foldLeft$(this, from, b, fn);
         }

         public boolean isTraversableAgain(final SparseVector from) {
            return true;
         }

         public CanTraverseValues.ValuesVisitor traverse(final SparseVector from, final CanTraverseValues.ValuesVisitor fn) {
            fn.zeros(from.size() - from.activeSize(), from.default());
            fn.visitArray(from.data(), 0, from.activeSize(), 1);
            return fn;
         }

         public {
            CanTraverseValues.$init$(this);
         }
      };
   }

   static void $init$(final SparseVector_TraversalOps $this) {
   }
}
