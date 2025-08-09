package scala.collection.immutable;

import scala.Function1;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Growable;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M3Q!\u0003\u0006\u0003\u0015AAQA\r\u0001\u0005\u0002MBa!\u000e\u0001!\u0002\u00131\u0004BB\u001d\u0001A\u0003%!\bC\u0005B\u0001\u0001\u0007\t\u0011)Q\u0005]!)!\t\u0001C!\u0007\")q\t\u0001C!\u0011\")\u0011\n\u0001C\u0001\u0015\")\u0011\n\u0001C!!\n\u0001b+Z2u_Jl\u0015\r\u001d\"vS2$WM\u001d\u0006\u0003\u00171\t\u0011\"[7nkR\f'\r\\3\u000b\u00055q\u0011AC2pY2,7\r^5p]*\tq\"A\u0003tG\u0006d\u0017-F\u0002\u0012C1\u001a2\u0001\u0001\n\u0017!\t\u0019B#D\u0001\u000f\u0013\t)bB\u0001\u0004B]f\u0014VM\u001a\t\u0005/iab&D\u0001\u0019\u0015\tIB\"A\u0004nkR\f'\r\\3\n\u0005mA\"a\u0002\"vS2$WM\u001d\t\u0005'uy2&\u0003\u0002\u001f\u001d\t1A+\u001e9mKJ\u0002\"\u0001I\u0011\r\u0001\u0011)!\u0005\u0001b\u0001I\t\t1j\u0001\u0001\u0012\u0005\u0015B\u0003CA\n'\u0013\t9cBA\u0004O_RD\u0017N\\4\u0011\u0005MI\u0013B\u0001\u0016\u000f\u0005\r\te.\u001f\t\u0003A1\"Q!\f\u0001C\u0002\u0011\u0012\u0011A\u0016\t\u0005_Az2&D\u0001\u000b\u0013\t\t$BA\u0005WK\u000e$xN]'ba\u00061A(\u001b8jiz\"\u0012\u0001\u000e\t\u0005_\u0001y2&A\u0007wK\u000e$xN\u001d\"vS2$WM\u001d\t\u0004_]z\u0012B\u0001\u001d\u000b\u000551Vm\u0019;pe\n+\u0018\u000e\u001c3fe\u0006QQ.\u00199Ck&dG-\u001a:\u0011\t=Zt$P\u0005\u0003y)\u0011a\"T1q\u0005VLG\u000eZ3s\u00136\u0004H\u000e\u0005\u0003\u0014;yZ\u0003CA\n@\u0013\t\u0001eBA\u0002J]R\fq!\u00197jCN,G-A\u0003dY\u0016\f'\u000fF\u0001E!\t\u0019R)\u0003\u0002G\u001d\t!QK\\5u\u0003\u0019\u0011Xm];miR\ta&\u0001\u0004bI\u0012|e.\u001a\u000b\u0004\u00172sU\"\u0001\u0001\t\u000b5;\u0001\u0019A\u0010\u0002\u0007-,\u0017\u0010C\u0003P\u000f\u0001\u00071&A\u0003wC2,X\r\u0006\u0002L#\")!\u000b\u0003a\u00019\u0005!Q\r\\3n\u0001"
)
public final class VectorMapBuilder implements Builder {
   private final VectorBuilder vectorBuilder = new VectorBuilder();
   private final MapBuilderImpl mapBuilder = new MapBuilderImpl();
   private VectorMap aliased;

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

   public Growable addAll(final IterableOnce elems) {
      return Growable.addAll$(this, elems);
   }

   public final Growable $plus$plus$eq(final IterableOnce elems) {
      return Growable.$plus$plus$eq$(this, elems);
   }

   public int knownSize() {
      return Growable.knownSize$(this);
   }

   public void clear() {
      this.vectorBuilder.clear();
      this.mapBuilder.clear();
      this.aliased = null;
   }

   public VectorMap result() {
      if (this.aliased == null) {
         this.aliased = new VectorMap(this.vectorBuilder.result(), this.mapBuilder.result());
      }

      return this.aliased;
   }

   public VectorMapBuilder addOne(final Object key, final Object value) {
      if (this.aliased != null) {
         this.aliased = this.aliased.updated(key, value);
      } else {
         Tuple2 var3 = (Tuple2)this.mapBuilder.getOrElse(key, (Object)null);
         if (var3 != null) {
            int slot = var3._1$mcI$sp();
            this.mapBuilder.addOne(key, new Tuple2(slot, value));
         } else {
            VectorBuilder var10000 = this.vectorBuilder;
            if (var10000 == null) {
               throw null;
            }

            VectorBuilder size_this = var10000;
            int var8 = size_this.knownSize();
            Object var7 = null;
            int vectorSize = var8;
            this.vectorBuilder.addOne(key);
            this.mapBuilder.addOne(key, new Tuple2(vectorSize, value));
         }
      }

      return this;
   }

   public VectorMapBuilder addOne(final Tuple2 elem) {
      return this.addOne(elem._1(), elem._2());
   }
}
