package scala.collection.generic;

import scala.collection.Factory;
import scala.collection.immutable.Seq;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.ParIterable;
import scala.collection.parallel.ParSet;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005!4Q\u0001C\u0005\u0002\u0002AAQA\u0010\u0001\u0005\u0002}BQ!\u0011\u0001\u0005\u0002\tCQa\u0013\u0001\u0007\u000213AA\u0015\u0001\u0001'\")a\b\u0002C\u0001?\")!\r\u0002C!G\")!\r\u0002C!O\ni\u0001+\u0019:TKR4\u0015m\u0019;pefT!AC\u0006\u0002\u000f\u001d,g.\u001a:jG*\u0011A\"D\u0001\u000bG>dG.Z2uS>t'\"\u0001\b\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001U\u0011\u0011\u0003H\n\u0004\u0001I1\u0002CA\n\u0015\u001b\u0005i\u0011BA\u000b\u000e\u0005\u0019\te.\u001f*fMB\u0019q\u0003\u0007\u000e\u000e\u0003%I!!G\u0005\u0003'\u001d+g.\u001a:jGB\u000b'oQ8na\u0006t\u0017n\u001c8\u0011\u0005maB\u0002\u0001\u0003\u0006;\u0001\u0011\rA\b\u0002\u0003\u0007\u000e+\"aH\u0017\u0012\u0005\u0001\u001a\u0003CA\n\"\u0013\t\u0011SBA\u0004O_RD\u0017N\\4\u0013\t\u001123g\u000f\u0004\u0005K\u0001\u00011E\u0001\u0007=e\u00164\u0017N\\3nK:$h\bE\u0002(U1j\u0011\u0001\u000b\u0006\u0003S-\t\u0001\u0002]1sC2dW\r\\\u0005\u0003W!\u0012a\u0001U1s'\u0016$\bCA\u000e.\t\u0015qCD1\u00010\u0005\u0005A\u0016C\u0001\u00111!\t\u0019\u0012'\u0003\u00023\u001b\t\u0019\u0011I\\=1\u0005QJ\u0004CB\u00146Yi9\u0004(\u0003\u00027Q\tQ\u0001+\u0019:TKRd\u0015n[3\u0011\u0007maB\u0006\u0005\u0002\u001cs\u0011I!\bHA\u0001\u0002\u0003\u0015\ta\f\u0002\tIEl\u0017M]6%cA!q\u0003\u0010\u0017\u001b\u0013\ti\u0014B\u0001\nHK:,'/[2QCJ$V-\u001c9mCR,\u0017A\u0002\u001fj]&$h\bF\u0001A!\r9\u0002AG\u0001\u000b]\u0016<()^5mI\u0016\u0014XCA\"I+\u0005!\u0005\u0003B\u0014F\u000f*K!A\u0012\u0015\u0003\u0011\r{WNY5oKJ\u0004\"a\u0007%\u0005\u000b%\u0013!\u0019A\u0018\u0003\u0003\u0005\u00032a\u0007\u000fH\u0003-qWm^\"p[\nLg.\u001a:\u0016\u00055\u0003V#\u0001(\u0011\t\u001d*u*\u0015\t\u00037A#Q!S\u0002C\u0002=\u00022a\u0007\u000fP\u0005U9UM\\3sS\u000e\u001c\u0015M\\\"p[\nLg.\u001a$s_6,2\u0001\u0016.^'\r!!#\u0016\t\u0006/YCFLX\u0005\u0003/&\u0011abQ1o\u0007>l'-\u001b8f\rJ|W\u000eE\u0002\u001c9e\u0003\"a\u0007.\u0005\u000bm#!\u0019A\u0018\u0003\u0003\t\u0003\"aG/\u0005\u000b%#!\u0019A\u0018\u0011\u0007maB\fF\u0001a!\u0011\tG!\u0017/\u000e\u0003\u0001\tQ!\u00199qYf$\"\u0001Z3\u0011\t\u001d*EL\u0018\u0005\u0006M\u001a\u0001\r\u0001W\u0001\u0005MJ|W\u000eF\u0001e\u0001"
)
public abstract class ParSetFactory implements GenericParCompanion {
   public ParIterable empty() {
      return GenericParCompanion.empty$(this);
   }

   public ParIterable apply(final Seq elems) {
      return GenericParCompanion.apply$(this, elems);
   }

   public Factory toFactory() {
      return GenericParCompanion.toFactory$(this);
   }

   public Combiner newBuilder() {
      return this.newCombiner();
   }

   public abstract Combiner newCombiner();

   public ParSetFactory() {
      GenericParCompanion.$init$(this);
   }

   public class GenericCanCombineFrom implements CanCombineFrom {
      // $FF: synthetic field
      public final ParSetFactory $outer;

      public Combiner apply(final ParSet from) {
         return from.genericCombiner();
      }

      public Combiner apply() {
         return this.scala$collection$generic$ParSetFactory$GenericCanCombineFrom$$$outer().newCombiner();
      }

      // $FF: synthetic method
      public ParSetFactory scala$collection$generic$ParSetFactory$GenericCanCombineFrom$$$outer() {
         return this.$outer;
      }

      public GenericCanCombineFrom() {
         if (ParSetFactory.this == null) {
            throw null;
         } else {
            this.$outer = ParSetFactory.this;
            super();
         }
      }
   }
}
