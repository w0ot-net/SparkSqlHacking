package scala.collection.immutable;

import scala.collection.Hashing$;
import scala.collection.Iterator$;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Q2A!\u0002\u0004\u0007\u001b!A!\u0005\u0001B\u0001B\u0003%q\u0004C\u0003$\u0001\u0011\u0005A\u0005C\u0003(\u0001\u0011\u0005\u0001\u0006C\u00033\u0001\u0011\u00053GA\u0010NCBtu\u000eZ3SK6|g/Z!mYN+GOT8eK&#XM]1u_JT!a\u0002\u0005\u0002\u0013%lW.\u001e;bE2,'BA\u0005\u000b\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002\u0017\u0005)1oY1mC\u000e\u0001QC\u0001\b\u0016'\t\u0001q\u0002\u0005\u0003\u0011#MyR\"\u0001\u0004\n\u0005I1!!E\"iC6\u0004()Y:f\u0013R,'/\u0019;peB\u0011A#\u0006\u0007\u0001\t\u00151\u0002A1\u0001\u0018\u0005\u0005Y\u0015C\u0001\r\u001d!\tI\"$D\u0001\u000b\u0013\tY\"BA\u0004O_RD\u0017N\\4\u0011\u0005ei\u0012B\u0001\u0010\u000b\u0005\r\te.\u001f\t\u0004!\u0001\u001a\u0012BA\u0011\u0007\u0005\u001d\u0019V\r\u001e(pI\u0016\f1B]8piN+GOT8eK\u00061A(\u001b8jiz\"\"!\n\u0014\u0011\u0007A\u00011\u0003C\u0003#\u0005\u0001\u0007q$A\u0005sK6|g/Z!mYV\u0011\u0011F\f\u000b\u0003UA\u0002B\u0001E\u0016\u0014[%\u0011AF\u0002\u0002\u0015\u0005&$X.\u00199J]\u0012,\u00070\u001a3NCBtu\u000eZ3\u0011\u0005QqC!B\u0018\u0004\u0005\u00049\"!\u0001,\t\u000bE\u001a\u0001\u0019\u0001\u0016\u0002\u0017I|w\u000e^'ba:{G-Z\u0001\u0005]\u0016DH\u000fF\u0001\u0014\u0001"
)
public final class MapNodeRemoveAllSetNodeIterator extends ChampBaseIterator {
   public BitmapIndexedMapNode removeAll(final BitmapIndexedMapNode rootMapNode) {
      BitmapIndexedMapNode curr = rootMapNode;

      while(curr.size() > 0 && this.hasNext()) {
         int originalHash = this.currentValueNode().getHash(this.currentValueCursor());
         Object x$1 = ((SetNode)this.currentValueNode()).getPayload(this.currentValueCursor());
         int x$2 = Hashing$.MODULE$.improve(originalHash);
         curr = curr.removed(x$1, originalHash, x$2, 0);
         this.currentValueCursor_$eq(this.currentValueCursor() + 1);
      }

      return curr;
   }

   public Object next() {
      Iterator$ var10000 = Iterator$.MODULE$;
      return Iterator$.scala$collection$Iterator$$_empty.next();
   }

   public MapNodeRemoveAllSetNodeIterator(final SetNode rootSetNode) {
      super(rootSetNode);
   }
}
