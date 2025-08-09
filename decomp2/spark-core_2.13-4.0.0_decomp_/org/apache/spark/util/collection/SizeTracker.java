package org.apache.spark.util.collection;

import java.io.Serializable;
import org.apache.spark.util.SizeEstimator$;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.immutable.;
import scala.collection.immutable.List;
import scala.collection.mutable.Queue;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-g\u0001\u0003\u0016,!\u0003\r\taL\u001b\t\u000bq\u0002A\u0011\u0001 \t\u000f\t\u0003!\u0019!C\u0005\u0007\"9q\t\u0001b\u0001\n\u0013A\u0005BCAV\u0001\u0001\u0007\t\u0019!C\u0005\u0007\"Y\u0011Q\u0016\u0001A\u0002\u0003\u0007I\u0011BAX\u0011%y\u0007\u00011AA\u0002\u0013%!\u000eC\u0006\u00024\u0002\u0001\r\u00111A\u0005\n\u0005U\u0006BCA]\u0001\u0001\u0007\t\u0019!C\u0005U\"Y\u00111\u0018\u0001A\u0002\u0003\u0007I\u0011BA_\u0011\u0019\t\t\r\u0001C\t}!1\u00111\u0019\u0001\u0005\u0012yBa!!2\u0001\t\u0013q\u0004bBAd\u0001\u0011\u0005\u0011\u0011Z\u0004\u0006'.BI\u0001\u0016\u0004\u0006U-BI!\u0016\u0005\u0006->!\ta\u0016\u0004\u00051>\u0001\u0015\f\u0003\u0005j#\tU\r\u0011\"\u0001k\u0011!q\u0017C!E!\u0002\u0013Y\u0007\u0002C8\u0012\u0005+\u0007I\u0011\u00016\t\u0011A\f\"\u0011#Q\u0001\n-DQAV\t\u0005\u0002EDqA^\t\u0002\u0002\u0013\u0005q\u000fC\u0004{#E\u0005I\u0011A>\t\u0011\u00055\u0011#%A\u0005\u0002mD\u0011\"a\u0004\u0012\u0003\u0003%\t%!\u0005\t\u0013\u0005\r\u0012#!A\u0005\u0002\u0005\u0015\u0002\"CA\u0017#\u0005\u0005I\u0011AA\u0018\u0011%\tY$EA\u0001\n\u0003\ni\u0004C\u0005\u0002HE\t\t\u0011\"\u0001\u0002J!I\u00111K\t\u0002\u0002\u0013\u0005\u0013Q\u000b\u0005\n\u00033\n\u0012\u0011!C!\u00037B\u0011\"!\u0018\u0012\u0003\u0003%\t%a\u0018\t\u0013\u0005\u0005\u0014#!A\u0005B\u0005\rt!CA4\u001f\u0005\u0005\t\u0012AA5\r!Av\"!A\t\u0002\u0005-\u0004B\u0002,%\t\u0003\t\u0019\tC\u0005\u0002^\u0011\n\t\u0011\"\u0012\u0002`!I\u0011Q\u0011\u0013\u0002\u0002\u0013\u0005\u0015q\u0011\u0005\n\u0003\u001b#\u0013\u0011!CA\u0003\u001fC\u0011\"!)%\u0003\u0003%I!a)\u0003\u0017MK'0\u001a+sC\u000e\\WM\u001d\u0006\u0003Y5\n!bY8mY\u0016\u001cG/[8o\u0015\tqs&\u0001\u0003vi&d'B\u0001\u00192\u0003\u0015\u0019\b/\u0019:l\u0015\t\u00114'\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002i\u0005\u0019qN]4\u0014\u0005\u00011\u0004CA\u001c;\u001b\u0005A$\"A\u001d\u0002\u000bM\u001c\u0017\r\\1\n\u0005mB$AB!osJ+g-\u0001\u0004%S:LG\u000fJ\u0002\u0001)\u0005y\u0004CA\u001cA\u0013\t\t\u0005H\u0001\u0003V]&$\u0018AE*B\u001bBcUiX$S\u001f^#\u0006j\u0018*B)\u0016+\u0012\u0001\u0012\t\u0003o\u0015K!A\u0012\u001d\u0003\r\u0011{WO\u00197f\u0003\u001d\u0019\u0018-\u001c9mKN,\u0012!\u0013\t\u0004\u0015:\u0003V\"A&\u000b\u00051k\u0015aB7vi\u0006\u0014G.\u001a\u0006\u0003YaJ!aT&\u0003\u000bE+X-^3\u0011\u0005E\u000bbB\u0001*\u000f\u001b\u0005Y\u0013aC*ju\u0016$&/Y2lKJ\u0004\"AU\b\u0014\u0005=1\u0014A\u0002\u001fj]&$h\bF\u0001U\u0005\u0019\u0019\u0016-\u001c9mKN!\u0011C\u000e.^!\t94,\u0003\u0002]q\t9\u0001K]8ek\u000e$\bC\u00010g\u001d\tyFM\u0004\u0002aG6\t\u0011M\u0003\u0002c{\u00051AH]8pizJ\u0011!O\u0005\u0003Kb\nq\u0001]1dW\u0006<W-\u0003\u0002hQ\na1+\u001a:jC2L'0\u00192mK*\u0011Q\rO\u0001\u0005g&TX-F\u0001l!\t9D.\u0003\u0002nq\t!Aj\u001c8h\u0003\u0015\u0019\u0018N_3!\u0003)qW/\\+qI\u0006$Xm]\u0001\f]VlW\u000b\u001d3bi\u0016\u001c\b\u0005F\u0002siV\u0004\"a]\t\u000e\u0003=AQ!\u001b\fA\u0002-DQa\u001c\fA\u0002-\fAaY8qsR\u0019!\u000f_=\t\u000f%<\u0002\u0013!a\u0001W\"9qn\u0006I\u0001\u0002\u0004Y\u0017AD2paf$C-\u001a4bk2$H%M\u000b\u0002y*\u00121.`\u0016\u0002}B\u0019q0!\u0003\u000e\u0005\u0005\u0005!\u0002BA\u0002\u0003\u000b\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005\u001d\u0001(\u0001\u0006b]:|G/\u0019;j_:LA!a\u0003\u0002\u0002\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%e\u0005i\u0001O]8ek\u000e$\bK]3gSb,\"!a\u0005\u0011\t\u0005U\u0011qD\u0007\u0003\u0003/QA!!\u0007\u0002\u001c\u0005!A.\u00198h\u0015\t\ti\"\u0001\u0003kCZ\f\u0017\u0002BA\u0011\u0003/\u0011aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLXCAA\u0014!\r9\u0014\u0011F\u0005\u0004\u0003WA$aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BA\u0019\u0003o\u00012aNA\u001a\u0013\r\t)\u0004\u000f\u0002\u0004\u0003:L\b\"CA\u001d9\u0005\u0005\t\u0019AA\u0014\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011q\b\t\u0007\u0003\u0003\n\u0019%!\r\u000e\u00035K1!!\u0012N\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005-\u0013\u0011\u000b\t\u0004o\u00055\u0013bAA(q\t9!i\\8mK\u0006t\u0007\"CA\u001d=\u0005\u0005\t\u0019AA\u0019\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\u0005M\u0011q\u000b\u0005\n\u0003sy\u0012\u0011!a\u0001\u0003O\t\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0003O\t\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003'\ta!Z9vC2\u001cH\u0003BA&\u0003KB\u0011\"!\u000f#\u0003\u0003\u0005\r!!\r\u0002\rM\u000bW\u000e\u001d7f!\t\u0019HeE\u0003%\u0003[\nI\bE\u0004\u0002p\u0005U4n\u001b:\u000e\u0005\u0005E$bAA:q\u00059!/\u001e8uS6,\u0017\u0002BA<\u0003c\u0012\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c83!\u0011\tY(!!\u000e\u0005\u0005u$\u0002BA@\u00037\t!![8\n\u0007\u001d\fi\b\u0006\u0002\u0002j\u0005)\u0011\r\u001d9msR)!/!#\u0002\f\")\u0011n\na\u0001W\")qn\na\u0001W\u00069QO\\1qa2LH\u0003BAI\u0003;\u0003RaNAJ\u0003/K1!!&9\u0005\u0019y\u0005\u000f^5p]B)q'!'lW&\u0019\u00111\u0014\u001d\u0003\rQ+\b\u000f\\33\u0011!\ty\nKA\u0001\u0002\u0004\u0011\u0018a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011Q\u0015\t\u0005\u0003+\t9+\u0003\u0003\u0002*\u0006]!AB(cU\u0016\u001cG/\u0001\bcsR,7\u000fU3s+B$\u0017\r^3\u0002%\tLH/Z:QKJ,\u0006\u000fZ1uK~#S-\u001d\u000b\u0004\u007f\u0005E\u0006\u0002CA\u001d\u000b\u0005\u0005\t\u0019\u0001#\u0002\u001d9,X.\u00169eCR,7o\u0018\u0013fcR\u0019q(a.\t\u0011\u0005er!!AA\u0002-\fQB\\3yiN\u000bW\u000e\u001d7f\u001dVl\u0017!\u00058fqR\u001c\u0016-\u001c9mK:+Xn\u0018\u0013fcR\u0019q(a0\t\u0011\u0005e\u0012\"!AA\u0002-\fAB]3tKR\u001c\u0016-\u001c9mKN\f1\"\u00194uKJ,\u0006\u000fZ1uK\u0006QA/Y6f'\u0006l\u0007\u000f\\3\u0002\u0019\u0015\u001cH/[7bi\u0016\u001c\u0016N_3\u0015\u0003-\u0004"
)
public interface SizeTracker {
   void org$apache$spark$util$collection$SizeTracker$_setter_$org$apache$spark$util$collection$SizeTracker$$SAMPLE_GROWTH_RATE_$eq(final double x$1);

   void org$apache$spark$util$collection$SizeTracker$_setter_$org$apache$spark$util$collection$SizeTracker$$samples_$eq(final Queue x$1);

   double org$apache$spark$util$collection$SizeTracker$$SAMPLE_GROWTH_RATE();

   Queue org$apache$spark$util$collection$SizeTracker$$samples();

   double org$apache$spark$util$collection$SizeTracker$$bytesPerUpdate();

   void org$apache$spark$util$collection$SizeTracker$$bytesPerUpdate_$eq(final double x$1);

   long org$apache$spark$util$collection$SizeTracker$$numUpdates();

   void org$apache$spark$util$collection$SizeTracker$$numUpdates_$eq(final long x$1);

   long org$apache$spark$util$collection$SizeTracker$$nextSampleNum();

   void org$apache$spark$util$collection$SizeTracker$$nextSampleNum_$eq(final long x$1);

   // $FF: synthetic method
   static void resetSamples$(final SizeTracker $this) {
      $this.resetSamples();
   }

   default void resetSamples() {
      this.org$apache$spark$util$collection$SizeTracker$$numUpdates_$eq(1L);
      this.org$apache$spark$util$collection$SizeTracker$$nextSampleNum_$eq(1L);
      this.org$apache$spark$util$collection$SizeTracker$$samples().clear();
      this.takeSample();
   }

   // $FF: synthetic method
   static void afterUpdate$(final SizeTracker $this) {
      $this.afterUpdate();
   }

   default void afterUpdate() {
      this.org$apache$spark$util$collection$SizeTracker$$numUpdates_$eq(this.org$apache$spark$util$collection$SizeTracker$$numUpdates() + 1L);
      if (this.org$apache$spark$util$collection$SizeTracker$$nextSampleNum() == this.org$apache$spark$util$collection$SizeTracker$$numUpdates()) {
         this.takeSample();
      }
   }

   private void takeSample() {
      this.org$apache$spark$util$collection$SizeTracker$$samples().enqueue(new Sample(SizeEstimator$.MODULE$.estimate(this), this.org$apache$spark$util$collection$SizeTracker$$numUpdates()));
      if (this.org$apache$spark$util$collection$SizeTracker$$samples().size() > 2) {
         this.org$apache$spark$util$collection$SizeTracker$$samples().dequeue();
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      double var11;
      label18: {
         List var5 = this.org$apache$spark$util$collection$SizeTracker$$samples().toList().reverse();
         if (var5 instanceof .colon.colon var6) {
            Sample latest = (Sample)var6.head();
            List var8 = var6.next$access$1();
            if (var8 instanceof .colon.colon var9) {
               Sample previous = (Sample)var9.head();
               var11 = (double)(latest.size() - previous.size()) / (double)(latest.numUpdates() - previous.numUpdates());
               break label18;
            }
         }

         var11 = (double)0.0F;
      }

      double bytesDelta = var11;
      this.org$apache$spark$util$collection$SizeTracker$$bytesPerUpdate_$eq(scala.math.package..MODULE$.max((double)0.0F, bytesDelta));
      this.org$apache$spark$util$collection$SizeTracker$$nextSampleNum_$eq((long)scala.math.package..MODULE$.ceil((double)this.org$apache$spark$util$collection$SizeTracker$$numUpdates() * this.org$apache$spark$util$collection$SizeTracker$$SAMPLE_GROWTH_RATE()));
   }

   // $FF: synthetic method
   static long estimateSize$(final SizeTracker $this) {
      return $this.estimateSize();
   }

   default long estimateSize() {
      scala.Predef..MODULE$.assert(this.org$apache$spark$util$collection$SizeTracker$$samples().nonEmpty());
      double extrapolatedDelta = this.org$apache$spark$util$collection$SizeTracker$$bytesPerUpdate() * (double)(this.org$apache$spark$util$collection$SizeTracker$$numUpdates() - ((Sample)this.org$apache$spark$util$collection$SizeTracker$$samples().last()).numUpdates());
      return (long)((double)((Sample)this.org$apache$spark$util$collection$SizeTracker$$samples().last()).size() + extrapolatedDelta);
   }

   static void $init$(final SizeTracker $this) {
      $this.org$apache$spark$util$collection$SizeTracker$_setter_$org$apache$spark$util$collection$SizeTracker$$SAMPLE_GROWTH_RATE_$eq(1.1);
      $this.org$apache$spark$util$collection$SizeTracker$_setter_$org$apache$spark$util$collection$SizeTracker$$samples_$eq(new Queue(scala.collection.mutable.Queue..MODULE$.$lessinit$greater$default$1()));
      $this.resetSamples();
   }

   public static class Sample implements Product, Serializable {
      private final long size;
      private final long numUpdates;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public long size() {
         return this.size;
      }

      public long numUpdates() {
         return this.numUpdates;
      }

      public Sample copy(final long size, final long numUpdates) {
         return new Sample(size, numUpdates);
      }

      public long copy$default$1() {
         return this.size();
      }

      public long copy$default$2() {
         return this.numUpdates();
      }

      public String productPrefix() {
         return "Sample";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return BoxesRunTime.boxToLong(this.size());
            }
            case 1 -> {
               return BoxesRunTime.boxToLong(this.numUpdates());
            }
            default -> {
               return Statics.ioobe(x$1);
            }
         }
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Sample;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "size";
            }
            case 1 -> {
               return "numUpdates";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.longHash(this.size()));
         var1 = Statics.mix(var1, Statics.longHash(this.numUpdates()));
         return Statics.finalizeHash(var1, 2);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label38: {
               if (x$1 instanceof Sample) {
                  Sample var4 = (Sample)x$1;
                  if (this.size() == var4.size() && this.numUpdates() == var4.numUpdates() && var4.canEqual(this)) {
                     break label38;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      public Sample(final long size, final long numUpdates) {
         this.size = size;
         this.numUpdates = numUpdates;
         Product.$init$(this);
      }
   }

   public static class Sample$ extends AbstractFunction2 implements Serializable {
      public static final Sample$ MODULE$ = new Sample$();

      public final String toString() {
         return "Sample";
      }

      public Sample apply(final long size, final long numUpdates) {
         return new Sample(size, numUpdates);
      }

      public Option unapply(final Sample x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2.mcJJ.sp(x$0.size(), x$0.numUpdates())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Sample$.class);
      }
   }
}
