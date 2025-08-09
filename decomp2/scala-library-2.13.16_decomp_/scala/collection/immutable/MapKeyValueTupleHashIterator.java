package scala.collection.immutable;

import scala.collection.Iterator$;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.util.hashing.MurmurHash3$;

@ScalaSignature(
   bytes = "\u0006\u0005U2Aa\u0002\u0005\u0007\u001f!Aq\u0005\u0001B\u0001B\u0003%\u0011\u0004C\u0003)\u0001\u0011\u0005\u0011\u0006\u0003\u0004-\u0001\u0001\u0006K!\f\u0005\na\u0001\u0001\r\u0011!Q!\n\u0011BQ!\r\u0001\u0005BIBQa\r\u0001\u0005\u0002Q\u0012A$T1q\u0017\u0016Lh+\u00197vKR+\b\u000f\\3ICND\u0017\n^3sCR|'O\u0003\u0002\n\u0015\u0005I\u0011.\\7vi\u0006\u0014G.\u001a\u0006\u0003\u00171\t!bY8mY\u0016\u001cG/[8o\u0015\u0005i\u0011!B:dC2\f7\u0001A\u000b\u0004!y)3C\u0001\u0001\u0012!\u0011\u00112#F\r\u000e\u0003!I!\u0001\u0006\u0005\u00031\rC\u0017-\u001c9CCN,'+\u001a<feN,\u0017\n^3sCR|'\u000f\u0005\u0002\u0017/5\tA\"\u0003\u0002\u0019\u0019\t\u0019\u0011I\\=\u0011\tIQB\u0004J\u0005\u00037!\u0011q!T1q\u001d>$W\r\u0005\u0002\u001e=1\u0001A!B\u0010\u0001\u0005\u0004\u0001#!A&\u0012\u0005\u0005*\u0002C\u0001\f#\u0013\t\u0019CBA\u0004O_RD\u0017N\\4\u0011\u0005u)C!\u0002\u0014\u0001\u0005\u0004\u0001#!\u0001,\u0002\u0011I|w\u000e\u001e(pI\u0016\fa\u0001P5oSRtDC\u0001\u0016,!\u0011\u0011\u0002\u0001\b\u0013\t\u000b\u001d\u0012\u0001\u0019A\r\u0002\t!\f7\u000f\u001b\t\u0003-9J!a\f\u0007\u0003\u0007%sG/A\u0003wC2,X-\u0001\u0005iCND7i\u001c3f)\u0005i\u0013\u0001\u00028fqR$\u0012A\u000b"
)
public final class MapKeyValueTupleHashIterator extends ChampBaseReverseIterator {
   private int hash = 0;
   private Object value;

   public int hashCode() {
      return MurmurHash3$.MODULE$.tuple2Hash(this.hash, Statics.anyHash(this.value), -889275714);
   }

   public MapKeyValueTupleHashIterator next() {
      if (!this.hasNext()) {
         Iterator$ var10000 = Iterator$.MODULE$;
         Iterator$.scala$collection$Iterator$$_empty.next();
      }

      this.hash = this.currentValueNode().getHash(this.currentValueCursor());
      this.value = ((MapNode)this.currentValueNode()).getValue(this.currentValueCursor());
      this.currentValueCursor_$eq(this.currentValueCursor() - 1);
      return this;
   }

   public MapKeyValueTupleHashIterator(final MapNode rootNode) {
      super(rootNode);
   }
}
