package scala.collection.immutable;

import scala.Array$;
import scala.Function1;
import scala.MatchError;
import scala.collection.Hashing$;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Growable;
import scala.collection.mutable.ReusableBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mb!\u0002\f\u0018\u0005ei\u0002\"B\u001d\u0001\t\u0003Q\u0004\"\u0002\u001f\u0001\t\u0013i\u0004\"C!\u0001\u0001\u0004\u0005\r\u0011\"\u0003C\u0011%\u0019\u0005\u00011AA\u0002\u0013%A\tC\u0005K\u0001\u0001\u0007\t\u0011)Q\u0005k!)1\n\u0001C\u0005\u0019\"9\u0001\u000b\u0001a\u0001\n\u0013i\u0004bB)\u0001\u0001\u0004%IA\u0015\u0005\u0007)\u0002\u0001\u000b\u0015\u0002 \t\u000bU\u0003A\u0011\u0002,\t\u000b\r\u0004A\u0011\u00023\t\u000bM\u0004A\u0011\u0002;\t\u000bi\u0004A\u0011A>\t\u000f\u0005E\u0001\u0001\"\u0003\u0002\u0014!9\u0011Q\u0003\u0001\u0005\n\u0005M\u0001bBA\f\u0001\u0011\u0005\u0013\u0011\u0004\u0005\b\u00037\u0001A\u0011IA\u000f\u0011\u001d\t\u0019\u0003\u0001C!\u0003KAq!a\r\u0001\t\u0003\n\u0019\u0002\u0003\u0005\u00026\u0001!\t!GA\u001c\u0011\u001d\tI\u0004\u0001C!\u0003o\u0011a\u0002S1tQN+GOQ;jY\u0012,'O\u0003\u0002\u00193\u0005I\u0011.\\7vi\u0006\u0014G.\u001a\u0006\u00035m\t!bY8mY\u0016\u001cG/[8o\u0015\u0005a\u0012!B:dC2\fWC\u0001\u0010,'\r\u0001qd\t\t\u0003A\u0005j\u0011aG\u0005\u0003Em\u0011a!\u00118z%\u00164\u0007\u0003\u0002\u0013(SUj\u0011!\n\u0006\u0003Me\tq!\\;uC\ndW-\u0003\u0002)K\ty!+Z;tC\ndWMQ;jY\u0012,'\u000f\u0005\u0002+W1\u0001A!\u0002\u0017\u0001\u0005\u0004q#!A!\u0004\u0001E\u0011qF\r\t\u0003AAJ!!M\u000e\u0003\u000f9{G\u000f[5oOB\u0011\u0001eM\u0005\u0003im\u00111!\u00118z!\r1t'K\u0007\u0002/%\u0011\u0001h\u0006\u0002\b\u0011\u0006\u001c\bnU3u\u0003\u0019a\u0014N\\5u}Q\t1\bE\u00027\u0001%\n\u0001C\\3x\u000b6\u0004H/\u001f*p_Rtu\u000eZ3\u0016\u0003y\u00022AN *\u0013\t\u0001uC\u0001\u000bCSRl\u0017\r]%oI\u0016DX\rZ*fi:{G-Z\u0001\bC2L\u0017m]3e+\u0005)\u0014aC1mS\u0006\u001cX\rZ0%KF$\"!\u0012%\u0011\u0005\u00012\u0015BA$\u001c\u0005\u0011)f.\u001b;\t\u000f%#\u0011\u0011!a\u0001k\u0005\u0019\u0001\u0010J\u0019\u0002\u0011\u0005d\u0017.Y:fI\u0002\n\u0011\"[:BY&\f7/\u001a3\u0016\u00035\u0003\"\u0001\t(\n\u0005=[\"a\u0002\"p_2,\u0017M\\\u0001\te>|GOT8eK\u0006a!o\\8u\u001d>$Wm\u0018\u0013fcR\u0011Qi\u0015\u0005\b\u0013\"\t\t\u00111\u0001?\u0003%\u0011xn\u001c;O_\u0012,\u0007%A\u0007j]N,'\u000f^#mK6,g\u000e\u001e\u000b\u0005/v{\u0016\rE\u0002!1jK!!W\u000e\u0003\u000b\u0005\u0013(/Y=\u0011\u0005\u0001Z\u0016B\u0001/\u001c\u0005\rIe\u000e\u001e\u0005\u0006=*\u0001\raV\u0001\u0003CNDQ\u0001\u0019\u0006A\u0002i\u000b!!\u001b=\t\u000b\tT\u0001\u0019\u0001.\u0002\t\u0015dW-\\\u0001\fS:\u001cXM\u001d;WC2,X-\u0006\u0002faR1QI\u001a5kY:DQaZ\u0006A\u0002y\n!AY7\t\u000b%\\\u0001\u0019\u0001.\u0002\r\tLG\u000f]8t\u0011\u0015Y7\u00021\u0001*\u0003\rYW-\u001f\u0005\u0006[.\u0001\rAW\u0001\r_JLw-\u001b8bY\"\u000b7\u000f\u001b\u0005\u0006_.\u0001\rAW\u0001\bW\u0016L\b*Y:i\t\u0015\t8B1\u0001s\u0005\t\t\u0015'\u0005\u0002*e\u0005A1/\u001a;WC2,X-\u0006\u0002vsR!QI^<y\u0011\u00159G\u00021\u0001?\u0011\u0015IG\u00021\u0001[\u0011\u0015\u0011G\u00021\u0001*\t\u0015\tHB1\u0001s\u0003\u0019)\b\u000fZ1uKRQQ\t`A\u0002\u0003\u000f\tI!!\u0004\t\u000bul\u0001\u0019\u0001@\u0002\u000fM,GOT8eKB\u0019ag`\u0015\n\u0007\u0005\u0005qCA\u0004TKRtu\u000eZ3\t\r\u0005\u0015Q\u00021\u0001*\u0003\u001d)G.Z7f]RDQ!\\\u0007A\u0002iCa!a\u0003\u000e\u0001\u0004Q\u0016aC3mK6,g\u000e\u001e%bg\"Da!a\u0004\u000e\u0001\u0004Q\u0016!B:iS\u001a$\u0018aD3ogV\u0014X-\u00168bY&\f7/\u001a3\u0015\u0003\u0015\u000b\u0011bY8qs\u0016cW-\\:\u0002\rI,7/\u001e7u)\u0005)\u0014AB1eI>sW\r\u0006\u0003\u0002 \u0005\u0005R\"\u0001\u0001\t\u000b\t\f\u0002\u0019A\u0015\u0002\r\u0005$G-\u00117m)\u0011\ty\"a\n\t\u000f\u0005%\"\u00031\u0001\u0002,\u0005\u0011\u0001p\u001d\t\u0006\u0003[\ty#K\u0007\u00023%\u0019\u0011\u0011G\r\u0003\u0019%#XM]1cY\u0016|enY3\u0002\u000b\rdW-\u0019:\u0002\tML'0Z\u000b\u00025\u0006I1N\\8x]NK'0\u001a"
)
public final class HashSetBuilder implements ReusableBuilder {
   private HashSet aliased;
   private BitmapIndexedSetNode scala$collection$immutable$HashSetBuilder$$rootNode = this.newEmptyRootNode();

   public void sizeHint(final int size) {
      Builder.sizeHint$(this, size);
   }

   public final void sizeHint(final IterableOnce coll, final int delta) {
      Builder.sizeHint$(this, coll, delta);
   }

   public final int sizeHint$default$2() {
      return Builder.sizeHint$default$2$(this);
   }

   public final void sizeHintBounded(final int size, final scala.collection.Iterable boundingColl) {
      Builder.sizeHintBounded$(this, size, boundingColl);
   }

   public Builder mapResult(final Function1 f) {
      return Builder.mapResult$(this, f);
   }

   public final Growable $plus$eq(final Object elem) {
      return Growable.$plus$eq$(this, elem);
   }

   /** @deprecated */
   public final Growable $plus$eq(final Object elem1, final Object elem2, final Seq elems) {
      return Growable.$plus$eq$(this, elem1, elem2, elems);
   }

   public final Growable $plus$plus$eq(final IterableOnce elems) {
      return Growable.$plus$plus$eq$(this, elems);
   }

   private BitmapIndexedSetNode newEmptyRootNode() {
      return new BitmapIndexedSetNode(0, 0, Array$.MODULE$.emptyObjectArray(), Array$.MODULE$.emptyIntArray(), 0, 0);
   }

   private HashSet aliased() {
      return this.aliased;
   }

   private void aliased_$eq(final HashSet x$1) {
      this.aliased = x$1;
   }

   private boolean isAliased() {
      return this.aliased() != null;
   }

   public BitmapIndexedSetNode scala$collection$immutable$HashSetBuilder$$rootNode() {
      return this.scala$collection$immutable$HashSetBuilder$$rootNode;
   }

   private void rootNode_$eq(final BitmapIndexedSetNode x$1) {
      this.scala$collection$immutable$HashSetBuilder$$rootNode = x$1;
   }

   private int[] insertElement(final int[] as, final int ix, final int elem) {
      if (ix < 0) {
         throw new ArrayIndexOutOfBoundsException();
      } else if (ix > as.length) {
         throw new ArrayIndexOutOfBoundsException();
      } else {
         int[] result = new int[as.length + 1];
         System.arraycopy(as, 0, result, 0, ix);
         result[ix] = elem;
         System.arraycopy(as, ix, result, ix + 1, as.length - ix);
         return result;
      }
   }

   private void insertValue(final BitmapIndexedSetNode bm, final int bitpos, final Object key, final int originalHash, final int keyHash) {
      int dataIx = bm.dataIndex(bitpos);
      int idx = 1 * dataIx;
      Object[] src = bm.content();
      Object[] dst = new Object[src.length + 1];
      System.arraycopy(src, 0, dst, 0, idx);
      dst[idx] = key;
      System.arraycopy(src, idx, dst, idx + 1, src.length - idx);
      int[] dstHashes = this.insertElement(bm.originalHashes(), dataIx, originalHash);
      bm.dataMap_$eq(bm.dataMap() | bitpos);
      bm.content_$eq(dst);
      bm.originalHashes_$eq(dstHashes);
      bm.size_$eq(bm.size() + 1);
      bm.cachedJavaKeySetHashCode_$eq(bm.cachedJavaKeySetHashCode() + keyHash);
   }

   private void setValue(final BitmapIndexedSetNode bm, final int bitpos, final Object elem) {
      int dataIx = bm.dataIndex(bitpos);
      int idx = 1 * dataIx;
      bm.content()[idx] = elem;
   }

   public void update(final SetNode setNode, final Object element, final int originalHash, final int elementHash, final int shift) {
      if (setNode instanceof BitmapIndexedSetNode) {
         BitmapIndexedSetNode var6 = (BitmapIndexedSetNode)setNode;
         Node$ var10000 = Node$.MODULE$;
         int mask = elementHash >>> shift & 31;
         var10000 = Node$.MODULE$;
         int bitpos = 1 << mask;
         if ((var6.dataMap() & bitpos) != 0) {
            int index = Node$.MODULE$.indexFrom(var6.dataMap(), mask, bitpos);
            Object element0 = var6.content()[index];
            int element0UnimprovedHash = var6.originalHashes()[index];
            if (element0UnimprovedHash == originalHash && BoxesRunTime.equals(element0, element)) {
               this.setValue(var6, bitpos, element0);
            } else {
               int element0Hash = Hashing$.MODULE$.improve(element0UnimprovedHash);
               SetNode subNodeNew = var6.mergeTwoKeyValPairs(element0, element0UnimprovedHash, element0Hash, element, originalHash, elementHash, shift + 5);
               var6.migrateFromInlineToNodeInPlace(bitpos, element0Hash, subNodeNew);
            }
         } else if ((var6.nodeMap() & bitpos) != 0) {
            int index = Node$.MODULE$.indexFrom(var6.nodeMap(), mask, bitpos);
            SetNode subNode = var6.getNode(index);
            int beforeSize = subNode.size();
            int beforeHashCode = subNode.cachedJavaKeySetHashCode();
            this.update(subNode, element, originalHash, elementHash, shift + 5);
            var6.size_$eq(var6.size() + (subNode.size() - beforeSize));
            var6.cachedJavaKeySetHashCode_$eq(var6.cachedJavaKeySetHashCode() + (subNode.cachedJavaKeySetHashCode() - beforeHashCode));
         } else {
            this.insertValue(var6, bitpos, element, originalHash, elementHash);
         }
      } else if (setNode instanceof HashCollisionSetNode) {
         HashCollisionSetNode var18 = (HashCollisionSetNode)setNode;
         int index = var18.content().indexOf(element);
         if (index < 0) {
            var18.content_$eq(var18.content().appended(element));
         } else {
            var18.content_$eq(var18.content().updated(index, element));
         }
      } else {
         throw new MatchError(setNode);
      }
   }

   private void ensureUnaliased() {
      if (this.isAliased()) {
         this.copyElems();
      }

      this.aliased_$eq((HashSet)null);
   }

   private void copyElems() {
      this.rootNode_$eq(this.scala$collection$immutable$HashSetBuilder$$rootNode().copy());
   }

   public HashSet result() {
      if (this.scala$collection$immutable$HashSetBuilder$$rootNode().size() == 0) {
         return HashSet$.MODULE$.empty();
      } else if (this.aliased() != null) {
         return this.aliased();
      } else {
         this.aliased_$eq(new HashSet(this.scala$collection$immutable$HashSetBuilder$$rootNode()));
         Statics.releaseFence();
         return this.aliased();
      }
   }

   public HashSetBuilder addOne(final Object elem) {
      this.ensureUnaliased();
      int h = Statics.anyHash(elem);
      int im = Hashing$.MODULE$.improve(h);
      this.update(this.scala$collection$immutable$HashSetBuilder$$rootNode(), elem, h, im, 0);
      return this;
   }

   public HashSetBuilder addAll(final IterableOnce xs) {
      this.ensureUnaliased();
      if (xs instanceof HashSet) {
         HashSet var2 = (HashSet)xs;
         ChampBaseIterator var10000 = new ChampBaseIterator(var2) {
            public Nothing$ next() {
               Iterator$ var10000 = Iterator$.MODULE$;
               return (Nothing$)Iterator$.scala$collection$Iterator$$_empty.next();
            }

            public {
               while(this.hasNext()) {
                  int originalHash = this.currentValueNode().getHash(this.currentValueCursor());
                  HashSetBuilder.this.update(HashSetBuilder.this.scala$collection$immutable$HashSetBuilder$$rootNode(), ((SetNode)this.currentValueNode()).getPayload(this.currentValueCursor()), originalHash, Hashing$.MODULE$.improve(originalHash), 0);
                  this.currentValueCursor_$eq(this.currentValueCursor() + 1);
               }

            }
         };
      } else {
         Iterator it = xs.iterator();

         while(it.hasNext()) {
            this.addOne(it.next());
         }
      }

      return this;
   }

   public void clear() {
      this.aliased_$eq((HashSet)null);
      if (this.scala$collection$immutable$HashSetBuilder$$rootNode().size() > 0) {
         this.rootNode_$eq(this.newEmptyRootNode());
      }
   }

   public int size() {
      return this.scala$collection$immutable$HashSetBuilder$$rootNode().size();
   }

   public int knownSize() {
      return this.scala$collection$immutable$HashSetBuilder$$rootNode().size();
   }
}
