package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}b!\u0002\f\u0018\u0001^y\u0002\u0002\u0003\u001e\u0001\u0005+\u0007I\u0011A\u001e\t\u0011}\u0002!\u0011#Q\u0001\nqBQ\u0001\u0011\u0001\u0005\u0002\u0005Cq\u0001\u0012\u0001\u0002\u0002\u0013\u0005Q\tC\u0004H\u0001E\u0005I\u0011\u0001%\t\u000fM\u0003\u0011\u0011!C!)\"9Q\fAA\u0001\n\u0003q\u0006b\u00022\u0001\u0003\u0003%\ta\u0019\u0005\bS\u0002\t\t\u0011\"\u0011k\u0011\u001d\t\b!!A\u0005\u0002IDqa\u001e\u0001\u0002\u0002\u0013\u0005\u0003\u0010C\u0004{\u0001\u0005\u0005I\u0011I>\t\u000fq\u0004\u0011\u0011!C!{\"9a\u0010AA\u0001\n\u0003zxACA\u0002/\u0005\u0005\t\u0012A\f\u0002\u0006\u0019IacFA\u0001\u0012\u00039\u0012q\u0001\u0005\u0007\u0001B!\t!a\b\t\u000fq\u0004\u0012\u0011!C#{\"I\u0011\u0011\u0005\t\u0002\u0002\u0013\u0005\u00151\u0005\u0005\n\u0003O\u0001\u0012\u0011!CA\u0003SA\u0011\"!\u000e\u0011\u0003\u0003%I!a\u000e\u0003+MCWO\u001a4mK6+'oZ3GS:\fG.\u001b>fI*\u0011\u0001$G\u0001\ng\u000eDW\rZ;mKJT!AG\u000e\u0002\u000bM\u0004\u0018M]6\u000b\u0005qi\u0012AB1qC\u000eDWMC\u0001\u001f\u0003\ry'oZ\n\u0006\u0001\u00012#&\f\t\u0003C\u0011j\u0011A\t\u0006\u0002G\u0005)1oY1mC&\u0011QE\t\u0002\u0007\u0003:L(+\u001a4\u0011\u0005\u001dBS\"A\f\n\u0005%:\"!\u0005#B\u000fN\u001b\u0007.\u001a3vY\u0016\u0014XI^3oiB\u0011\u0011eK\u0005\u0003Y\t\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002/o9\u0011q&\u000e\b\u0003aQj\u0011!\r\u0006\u0003eM\na\u0001\u0010:p_Rt4\u0001A\u0005\u0002G%\u0011aGI\u0001\ba\u0006\u001c7.Y4f\u0013\tA\u0014H\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u00027E\u0005)1\u000f^1hKV\tA\b\u0005\u0002({%\u0011ah\u0006\u0002\u0010'\",hM\u001a7f\u001b\u0006\u00048\u000b^1hK\u000611\u000f^1hK\u0002\na\u0001P5oSRtDC\u0001\"D!\t9\u0003\u0001C\u0003;\u0007\u0001\u0007A(\u0001\u0003d_BLHC\u0001\"G\u0011\u001dQD\u0001%AA\u0002q\nabY8qs\u0012\"WMZ1vYR$\u0013'F\u0001JU\ta$jK\u0001L!\ta\u0015+D\u0001N\u0015\tqu*A\u0005v]\u000eDWmY6fI*\u0011\u0001KI\u0001\u000bC:tw\u000e^1uS>t\u0017B\u0001*N\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003U\u0003\"AV.\u000e\u0003]S!\u0001W-\u0002\t1\fgn\u001a\u0006\u00025\u0006!!.\u0019<b\u0013\tavK\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002?B\u0011\u0011\u0005Y\u0005\u0003C\n\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"\u0001Z4\u0011\u0005\u0005*\u0017B\u00014#\u0005\r\te.\u001f\u0005\bQ\"\t\t\u00111\u0001`\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\t1\u000eE\u0002m_\u0012l\u0011!\u001c\u0006\u0003]\n\n!bY8mY\u0016\u001cG/[8o\u0013\t\u0001XN\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dGCA:w!\t\tC/\u0003\u0002vE\t9!i\\8mK\u0006t\u0007b\u00025\u000b\u0003\u0003\u0005\r\u0001Z\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0002Vs\"9\u0001nCA\u0001\u0002\u0004y\u0016\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003}\u000b\u0001\u0002^8TiJLgn\u001a\u000b\u0002+\u00061Q-];bYN$2a]A\u0001\u0011\u001dAg\"!AA\u0002\u0011\fQc\u00155vM\u001adW-T3sO\u00164\u0015N\\1mSj,G\r\u0005\u0002(!M)\u0001#!\u0003\u0002\u0016A1\u00111BA\ty\tk!!!\u0004\u000b\u0007\u0005=!%A\u0004sk:$\u0018.\\3\n\t\u0005M\u0011Q\u0002\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\f\u0004\u0003BA\f\u0003;i!!!\u0007\u000b\u0007\u0005m\u0011,\u0001\u0002j_&\u0019\u0001(!\u0007\u0015\u0005\u0005\u0015\u0011!B1qa2LHc\u0001\"\u0002&!)!h\u0005a\u0001y\u00059QO\\1qa2LH\u0003BA\u0016\u0003c\u0001B!IA\u0017y%\u0019\u0011q\u0006\u0012\u0003\r=\u0003H/[8o\u0011!\t\u0019\u0004FA\u0001\u0002\u0004\u0011\u0015a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011\u0011\b\t\u0004-\u0006m\u0012bAA\u001f/\n1qJ\u00196fGR\u0004"
)
public class ShuffleMergeFinalized implements DAGSchedulerEvent, Product, Serializable {
   private final ShuffleMapStage stage;

   public static Option unapply(final ShuffleMergeFinalized x$0) {
      return ShuffleMergeFinalized$.MODULE$.unapply(x$0);
   }

   public static ShuffleMergeFinalized apply(final ShuffleMapStage stage) {
      return ShuffleMergeFinalized$.MODULE$.apply(stage);
   }

   public static Function1 andThen(final Function1 g) {
      return ShuffleMergeFinalized$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return ShuffleMergeFinalized$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public ShuffleMapStage stage() {
      return this.stage;
   }

   public ShuffleMergeFinalized copy(final ShuffleMapStage stage) {
      return new ShuffleMergeFinalized(stage);
   }

   public ShuffleMapStage copy$default$1() {
      return this.stage();
   }

   public String productPrefix() {
      return "ShuffleMergeFinalized";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.stage();
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ShuffleMergeFinalized;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "stage";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label47: {
            if (x$1 instanceof ShuffleMergeFinalized) {
               label40: {
                  ShuffleMergeFinalized var4 = (ShuffleMergeFinalized)x$1;
                  ShuffleMapStage var10000 = this.stage();
                  ShuffleMapStage var5 = var4.stage();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label40;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label40;
                  }

                  if (var4.canEqual(this)) {
                     break label47;
                  }
               }
            }

            var6 = false;
            return var6;
         }
      }

      var6 = true;
      return var6;
   }

   public ShuffleMergeFinalized(final ShuffleMapStage stage) {
      this.stage = stage;
      Product.$init$(this);
   }
}
