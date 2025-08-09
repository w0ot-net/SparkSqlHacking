package scala.collection.immutable;

import scala.Function1;
import scala.collection.IterableOnce;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Growable;
import scala.collection.mutable.ReusableBuilder;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000513A!\u0003\u0006\u0007#!)A\u0006\u0001C\u0001[!1q\u0006\u0001Q!\n!Ba\u0001\r\u0001!B\u0013\t\u0004\"\u0003\u001b\u0001\u0001\u0004\u0005\t\u0015)\u00036\u0011\u0015A\u0004\u0001\"\u0011:\u0011\u0015i\u0004\u0001\"\u0011?\u0011\u0015y\u0004\u0001\"\u0001A\u0011\u0015!\u0005\u0001\"\u0011F\u00059\u0019V\r\u001e\"vS2$WM]%na2T!a\u0003\u0007\u0002\u0013%lW.\u001e;bE2,'BA\u0007\u000f\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002\u001f\u0005)1oY1mC\u000e\u0001QC\u0001\n '\r\u00011c\u0006\t\u0003)Ui\u0011AD\u0005\u0003-9\u0011a!\u00118z%\u00164\u0007\u0003\u0002\r\u001c;!j\u0011!\u0007\u0006\u000351\tq!\\;uC\ndW-\u0003\u0002\u001d3\ty!+Z;tC\ndWMQ;jY\u0012,'\u000f\u0005\u0002\u001f?1\u0001A!\u0002\u0011\u0001\u0005\u0004\t#!A!\u0012\u0005\t*\u0003C\u0001\u000b$\u0013\t!cBA\u0004O_RD\u0017N\\4\u0011\u0005Q1\u0013BA\u0014\u000f\u0005\r\te.\u001f\t\u0004S)jR\"\u0001\u0006\n\u0005-R!aA*fi\u00061A(\u001b8jiz\"\u0012A\f\t\u0004S\u0001i\u0012!B3mK6\u001c\u0018\u0001G:xSR\u001c\u0007.\u001a3U_\"\u000b7\u000f[*fi\n+\u0018\u000e\u001c3feB\u0011ACM\u0005\u0003g9\u0011qAQ8pY\u0016\fg.\u0001\biCND7+\u001a;Ck&dG-\u001a:\u0011\u0007%2T$\u0003\u00028\u0015\tq\u0001*Y:i'\u0016$()^5mI\u0016\u0014\u0018!B2mK\u0006\u0014H#\u0001\u001e\u0011\u0005QY\u0014B\u0001\u001f\u000f\u0005\u0011)f.\u001b;\u0002\rI,7/\u001e7u)\u0005A\u0013AB1eI>sW\r\u0006\u0002B\u00056\t\u0001\u0001C\u0003D\u000f\u0001\u0007Q$\u0001\u0003fY\u0016l\u0017AB1eI\u0006cG\u000e\u0006\u0002B\r\")q\t\u0003a\u0001\u0011\u0006\u0011\u0001p\u001d\t\u0004\u0013*kR\"\u0001\u0007\n\u0005-c!\u0001D%uKJ\f'\r\\3P]\u000e,\u0007"
)
public final class SetBuilderImpl implements ReusableBuilder {
   private Set elems;
   private boolean switchedToHashSetBuilder;
   private HashSetBuilder hashSetBuilder;

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

   public int knownSize() {
      return Growable.knownSize$(this);
   }

   public void clear() {
      Set$ var10001 = Set$.MODULE$;
      this.elems = Set.EmptySet$.MODULE$;
      if (this.hashSetBuilder != null) {
         this.hashSetBuilder.clear();
      }

      this.switchedToHashSetBuilder = false;
   }

   public Set result() {
      return (Set)(this.switchedToHashSetBuilder ? this.hashSetBuilder.result() : this.elems);
   }

   public SetBuilderImpl addOne(final Object elem) {
      if (this.switchedToHashSetBuilder) {
         this.hashSetBuilder.addOne(elem);
      } else if (this.elems.size() < 4) {
         Set var10001 = this.elems;
         if (var10001 == null) {
            throw null;
         }

         this.elems = (Set)var10001.incl(elem);
      } else if (!this.elems.contains(elem)) {
         this.switchedToHashSetBuilder = true;
         if (this.hashSetBuilder == null) {
            this.hashSetBuilder = new HashSetBuilder();
         }

         ((Set.Set4)this.elems).buildTo(this.hashSetBuilder);
         this.hashSetBuilder.addOne(elem);
      }

      return this;
   }

   public SetBuilderImpl addAll(final IterableOnce xs) {
      if (this.switchedToHashSetBuilder) {
         this.hashSetBuilder.addAll(xs);
         return this;
      } else {
         return (SetBuilderImpl)Growable.addAll$(this, xs);
      }
   }

   public SetBuilderImpl() {
      Set$ var10001 = Set$.MODULE$;
      this.elems = Set.EmptySet$.MODULE$;
      this.switchedToHashSetBuilder = false;
   }
}
