package org.apache.spark;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.util.collection.ExternalAppendOnlyMap;
import org.apache.spark.util.collection.ExternalAppendOnlyMap$;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.Product;
import scala.Predef.;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.Statics;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\t\u001dd\u0001B\u0010!\u0001\u001eB\u0001B\u0010\u0001\u0003\u0016\u0004%\ta\u0010\u0005\t#\u0002\u0011\t\u0012)A\u0005\u0001\"A!\u000b\u0001BK\u0002\u0013\u00051\u000b\u0003\u0005X\u0001\tE\t\u0015!\u0003U\u0011!A\u0006A!f\u0001\n\u0003I\u0006\u0002C.\u0001\u0005#\u0005\u000b\u0011\u0002.\t\u000bq\u0003A\u0011A/\t\u000b\u0019\u0004A\u0011A4\t\u000by\u0004A\u0011A@\t\u000f\u0005M\u0001\u0001\"\u0003\u0002\u0016!I\u0011q\t\u0001\u0002\u0002\u0013\u0005\u0011\u0011\n\u0005\n\u0003O\u0002\u0011\u0013!C\u0001\u0003SB\u0011\"a\"\u0001#\u0003%\t!!#\t\u0013\u0005U\u0005!%A\u0005\u0002\u0005]\u0005\"CAR\u0001\u0005\u0005I\u0011IAS\u0011%\t9\fAA\u0001\n\u0003\tI\fC\u0005\u0002B\u0002\t\t\u0011\"\u0001\u0002D\"I\u0011\u0011\u001a\u0001\u0002\u0002\u0013\u0005\u00131\u001a\u0005\n\u0003+\u0004\u0011\u0011!C\u0001\u0003/D\u0011\"!9\u0001\u0003\u0003%\t%a9\t\u0013\u0005\u001d\b!!A\u0005B\u0005%\b\"CAv\u0001\u0005\u0005I\u0011IAw\u0011%\ty\u000fAA\u0001\n\u0003\n\tpB\u0005\u0003\u0002\u0001\n\t\u0011#\u0001\u0003\u0004\u0019Aq\u0004IA\u0001\u0012\u0003\u0011)\u0001\u0003\u0004]3\u0011\u0005!\u0011\u0003\u0005\n\u0003WL\u0012\u0011!C#\u0003[D\u0011Ba\u0005\u001a\u0003\u0003%\tI!\u0006\t\u0013\tM\u0012$!A\u0005\u0002\nU\u0002\"\u0003B/3\u0005\u0005I\u0011\u0002B0\u0005)\tum\u001a:fO\u0006$xN\u001d\u0006\u0003C\t\nQa\u001d9be.T!a\t\u0013\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005)\u0013aA8sO\u000e\u0001Q\u0003\u0002\u0015b\u000b>\u001bB\u0001A\u00150eA\u0011!&L\u0007\u0002W)\tA&A\u0003tG\u0006d\u0017-\u0003\u0002/W\t1\u0011I\\=SK\u001a\u0004\"A\u000b\u0019\n\u0005EZ#a\u0002)s_\u0012,8\r\u001e\t\u0003gmr!\u0001N\u001d\u000f\u0005UBT\"\u0001\u001c\u000b\u0005]2\u0013A\u0002\u001fs_>$h(C\u0001-\u0013\tQ4&A\u0004qC\u000e\\\u0017mZ3\n\u0005qj$\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u001e,\u00039\u0019'/Z1uK\u000e{WNY5oKJ,\u0012\u0001\u0011\t\u0005U\u0005\u001be*\u0003\u0002CW\tIa)\u001e8di&|g.\r\t\u0003\t\u0016c\u0001\u0001B\u0003G\u0001\t\u0007qIA\u0001W#\tA5\n\u0005\u0002+\u0013&\u0011!j\u000b\u0002\b\u001d>$\b.\u001b8h!\tQC*\u0003\u0002NW\t\u0019\u0011I\\=\u0011\u0005\u0011{E!\u0002)\u0001\u0005\u00049%!A\"\u0002\u001f\r\u0014X-\u0019;f\u0007>l'-\u001b8fe\u0002\n!\"\\3sO\u00164\u0016\r\\;f+\u0005!\u0006#\u0002\u0016V\u001d\u000es\u0015B\u0001,,\u0005%1UO\\2uS>t''A\u0006nKJ<WMV1mk\u0016\u0004\u0013AD7fe\u001e,7i\\7cS:,'o]\u000b\u00025B)!&\u0016(O\u001d\u0006yQ.\u001a:hK\u000e{WNY5oKJ\u001c\b%\u0001\u0004=S:LGO\u0010\u000b\u0005=\u000e$W\rE\u0003`\u0001\u0001\u001ce*D\u0001!!\t!\u0015\rB\u0003c\u0001\t\u0007qIA\u0001L\u0011\u0015qt\u00011\u0001A\u0011\u0015\u0011v\u00011\u0001U\u0011\u0015Av\u00011\u0001[\u0003I\u0019w.\u001c2j]\u00164\u0016\r\\;fg\nK8*Z=\u0015\u0007!t\u0017\u0010E\u00024S.L!A[\u001f\u0003\u0011%#XM]1u_J\u0004BA\u000b7a\u001d&\u0011Qn\u000b\u0002\u0007)V\u0004H.\u001a\u001a\t\u000b=D\u0001\u0019\u00019\u0002\t%$XM\u001d\u0019\u0003cN\u00042aM5s!\t!5\u000fB\u0005u]\u0006\u0005\t\u0011!B\u0001k\n\u0019q\fJ\u0019\u0012\u0005!3\b\u0003\u0002\u0016xA\u000eK!\u0001_\u0016\u0003\u0011A\u0013x\u000eZ;diJBQA\u001f\u0005A\u0002m\fqaY8oi\u0016DH\u000f\u0005\u0002`y&\u0011Q\u0010\t\u0002\f)\u0006\u001c8nQ8oi\u0016DH/A\u000bd_6\u0014\u0017N\\3D_6\u0014\u0017N\\3sg\nK8*Z=\u0015\u000b!\f\t!!\u0005\t\r=L\u0001\u0019AA\u0002a\u0011\t)!!\u0003\u0011\tMJ\u0017q\u0001\t\u0004\t\u0006%A\u0001DA\u0006\u0003\u0003\t\t\u0011!A\u0003\u0002\u00055!aA0%eE\u0019\u0001*a\u0004\u0011\t):\bM\u0014\u0005\u0006u&\u0001\ra_\u0001\u000ekB$\u0017\r^3NKR\u0014\u0018nY:\u0015\r\u0005]\u0011QDA\u0010!\rQ\u0013\u0011D\u0005\u0004\u00037Y#\u0001B+oSRDQA\u001f\u0006A\u0002mDq!!\t\u000b\u0001\u0004\t\u0019#A\u0002nCB\u0004\u0004\"!\n\u00028\u0005u\u00121\t\t\u000b\u0003O\t\t$!\u000e\u0002<\u0005\u0005SBAA\u0015\u0015\u0011\tY#!\f\u0002\u0015\r|G\u000e\\3di&|gNC\u0002\u00020\u0001\nA!\u001e;jY&!\u00111GA\u0015\u0005U)\u0005\u0010^3s]\u0006d\u0017\t\u001d9f]\u0012|e\u000e\\=NCB\u00042\u0001RA\u001c\t-\tI$a\b\u0002\u0002\u0003\u0005)\u0011A$\u0003\u0007}#3\u0007E\u0002E\u0003{!1\"a\u0010\u0002 \u0005\u0005\t\u0011!B\u0001\u000f\n\u0019q\f\n\u001b\u0011\u0007\u0011\u000b\u0019\u0005B\u0006\u0002F\u0005}\u0011\u0011!A\u0001\u0006\u00039%aA0%k\u0005!1m\u001c9z+!\tY%!\u0015\u0002V\u0005eC\u0003CA'\u00037\ny&a\u0019\u0011\u0011}\u0003\u0011qJA*\u0003/\u00022\u0001RA)\t\u0015\u00117B1\u0001H!\r!\u0015Q\u000b\u0003\u0006\r.\u0011\ra\u0012\t\u0004\t\u0006eC!\u0002)\f\u0005\u00049\u0005\u0002\u0003 \f!\u0003\u0005\r!!\u0018\u0011\r)\n\u00151KA,\u0011!\u00116\u0002%AA\u0002\u0005\u0005\u0004\u0003\u0003\u0016V\u0003/\n\u0019&a\u0016\t\u0011a[\u0001\u0013!a\u0001\u0003K\u0002\u0002BK+\u0002X\u0005]\u0013qK\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+!\tY'!!\u0002\u0004\u0006\u0015UCAA7U\r\u0001\u0015qN\u0016\u0003\u0003c\u0002B!a\u001d\u0002~5\u0011\u0011Q\u000f\u0006\u0005\u0003o\nI(A\u0005v]\u000eDWmY6fI*\u0019\u00111P\u0016\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002\u0000\u0005U$!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0012)!\r\u0004b\u0001\u000f\u0012)a\t\u0004b\u0001\u000f\u0012)\u0001\u000b\u0004b\u0001\u000f\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012T\u0003CAF\u0003\u001f\u000b\t*a%\u0016\u0005\u00055%f\u0001+\u0002p\u0011)!-\u0004b\u0001\u000f\u0012)a)\u0004b\u0001\u000f\u0012)\u0001+\u0004b\u0001\u000f\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u001aT\u0003CAM\u0003;\u000by*!)\u0016\u0005\u0005m%f\u0001.\u0002p\u0011)!M\u0004b\u0001\u000f\u0012)aI\u0004b\u0001\u000f\u0012)\u0001K\u0004b\u0001\u000f\u0006i\u0001O]8ek\u000e$\bK]3gSb,\"!a*\u0011\t\u0005%\u00161W\u0007\u0003\u0003WSA!!,\u00020\u0006!A.\u00198h\u0015\t\t\t,\u0001\u0003kCZ\f\u0017\u0002BA[\u0003W\u0013aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLXCAA^!\rQ\u0013QX\u0005\u0004\u0003\u007f[#aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HcA&\u0002F\"I\u0011qY\t\u0002\u0002\u0003\u0007\u00111X\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u00055\u0007#BAh\u0003'\\UBAAi\u0015\r\tYcK\u0005\u0004U\u0006E\u0017\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005e\u0017q\u001c\t\u0004U\u0005m\u0017bAAoW\t9!i\\8mK\u0006t\u0007\u0002CAd'\u0005\u0005\t\u0019A&\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0003O\u000b)\u000fC\u0005\u0002HR\t\t\u00111\u0001\u0002<\u0006A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002<\u0006AAo\\*ue&tw\r\u0006\u0002\u0002(\u00061Q-];bYN$B!!7\u0002t\"A\u0011qY\f\u0002\u0002\u0003\u00071\nK\u0002\u0001\u0003o\u0004B!!?\u0002~6\u0011\u00111 \u0006\u0004\u0003w\u0002\u0013\u0002BA\u0000\u0003w\u0014A\u0002R3wK2|\u0007/\u001a:Ba&\f!\"Q4he\u0016<\u0017\r^8s!\ty\u0016d\u0005\u0003\u001aS\t\u001d\u0001\u0003\u0002B\u0005\u0005\u001fi!Aa\u0003\u000b\t\t5\u0011qV\u0001\u0003S>L1\u0001\u0010B\u0006)\t\u0011\u0019!A\u0003baBd\u00170\u0006\u0005\u0003\u0018\tu!\u0011\u0005B\u0013)!\u0011IBa\n\u0003,\t=\u0002\u0003C0\u0001\u00057\u0011yBa\t\u0011\u0007\u0011\u0013i\u0002B\u0003c9\t\u0007q\tE\u0002E\u0005C!QA\u0012\u000fC\u0002\u001d\u00032\u0001\u0012B\u0013\t\u0015\u0001FD1\u0001H\u0011\u0019qD\u00041\u0001\u0003*A1!&\u0011B\u0010\u0005GAaA\u0015\u000fA\u0002\t5\u0002\u0003\u0003\u0016V\u0005G\u0011yBa\t\t\rac\u0002\u0019\u0001B\u0019!!QSKa\t\u0003$\t\r\u0012aB;oCB\u0004H._\u000b\t\u0005o\u0011YF!\u0013\u0003NQ!!\u0011\bB*!\u0015Q#1\bB \u0013\r\u0011id\u000b\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0013)\u0012\tE!\u0012\u0003P\tE\u0013b\u0001B\"W\t1A+\u001e9mKN\u0002bAK!\u0003H\t-\u0003c\u0001#\u0003J\u0011)a)\bb\u0001\u000fB\u0019AI!\u0014\u0005\u000bAk\"\u0019A$\u0011\u0011)*&1\nB$\u0005\u0017\u0002\u0002BK+\u0003L\t-#1\n\u0005\n\u0005+j\u0012\u0011!a\u0001\u0005/\n1\u0001\u001f\u00131!!y\u0006A!\u0017\u0003H\t-\u0003c\u0001#\u0003\\\u0011)!-\bb\u0001\u000f\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u0011!\u0011\r\t\u0005\u0003S\u0013\u0019'\u0003\u0003\u0003f\u0005-&AB(cU\u0016\u001cG\u000f"
)
public class Aggregator implements Product, Serializable {
   private final Function1 createCombiner;
   private final Function2 mergeValue;
   private final Function2 mergeCombiners;

   public static Option unapply(final Aggregator x$0) {
      return Aggregator$.MODULE$.unapply(x$0);
   }

   public static Aggregator apply(final Function1 createCombiner, final Function2 mergeValue, final Function2 mergeCombiners) {
      return Aggregator$.MODULE$.apply(createCombiner, mergeValue, mergeCombiners);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Function1 createCombiner() {
      return this.createCombiner;
   }

   public Function2 mergeValue() {
      return this.mergeValue;
   }

   public Function2 mergeCombiners() {
      return this.mergeCombiners;
   }

   public Iterator combineValuesByKey(final Iterator iter, final TaskContext context) {
      ExternalAppendOnlyMap combiners = new ExternalAppendOnlyMap(this.createCombiner(), this.mergeValue(), this.mergeCombiners(), ExternalAppendOnlyMap$.MODULE$.$lessinit$greater$default$4(), ExternalAppendOnlyMap$.MODULE$.$lessinit$greater$default$5(), ExternalAppendOnlyMap$.MODULE$.$lessinit$greater$default$6(), ExternalAppendOnlyMap$.MODULE$.$lessinit$greater$default$7());
      combiners.insertAll(iter);
      this.updateMetrics(context, combiners);
      return combiners.iterator();
   }

   public Iterator combineCombinersByKey(final Iterator iter, final TaskContext context) {
      ExternalAppendOnlyMap combiners = new ExternalAppendOnlyMap((x) -> .MODULE$.identity(x), this.mergeCombiners(), this.mergeCombiners(), ExternalAppendOnlyMap$.MODULE$.$lessinit$greater$default$4(), ExternalAppendOnlyMap$.MODULE$.$lessinit$greater$default$5(), ExternalAppendOnlyMap$.MODULE$.$lessinit$greater$default$6(), ExternalAppendOnlyMap$.MODULE$.$lessinit$greater$default$7());
      combiners.insertAll(iter);
      this.updateMetrics(context, combiners);
      return combiners.iterator();
   }

   private void updateMetrics(final TaskContext context, final ExternalAppendOnlyMap map) {
      scala.Option..MODULE$.apply(context).foreach((c) -> {
         $anonfun$updateMetrics$1(map, c);
         return BoxedUnit.UNIT;
      });
   }

   public Aggregator copy(final Function1 createCombiner, final Function2 mergeValue, final Function2 mergeCombiners) {
      return new Aggregator(createCombiner, mergeValue, mergeCombiners);
   }

   public Function1 copy$default$1() {
      return this.createCombiner();
   }

   public Function2 copy$default$2() {
      return this.mergeValue();
   }

   public Function2 copy$default$3() {
      return this.mergeCombiners();
   }

   public String productPrefix() {
      return "Aggregator";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.createCombiner();
         }
         case 1 -> {
            return this.mergeValue();
         }
         case 2 -> {
            return this.mergeCombiners();
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
      return x$1 instanceof Aggregator;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "createCombiner";
         }
         case 1 -> {
            return "mergeValue";
         }
         case 2 -> {
            return "mergeCombiners";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10;
      if (this != x$1) {
         label63: {
            if (x$1 instanceof Aggregator) {
               label56: {
                  Aggregator var4 = (Aggregator)x$1;
                  Function1 var10000 = this.createCombiner();
                  Function1 var5 = var4.createCombiner();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label56;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label56;
                  }

                  Function2 var8 = this.mergeValue();
                  Function2 var6 = var4.mergeValue();
                  if (var8 == null) {
                     if (var6 != null) {
                        break label56;
                     }
                  } else if (!var8.equals(var6)) {
                     break label56;
                  }

                  var8 = this.mergeCombiners();
                  Function2 var7 = var4.mergeCombiners();
                  if (var8 == null) {
                     if (var7 != null) {
                        break label56;
                     }
                  } else if (!var8.equals(var7)) {
                     break label56;
                  }

                  if (var4.canEqual(this)) {
                     break label63;
                  }
               }
            }

            var10 = false;
            return var10;
         }
      }

      var10 = true;
      return var10;
   }

   // $FF: synthetic method
   public static final void $anonfun$updateMetrics$1(final ExternalAppendOnlyMap map$1, final TaskContext c) {
      c.taskMetrics().incMemoryBytesSpilled(map$1.memoryBytesSpilled());
      c.taskMetrics().incDiskBytesSpilled(map$1.diskBytesSpilled());
      c.taskMetrics().incPeakExecutionMemory(map$1.peakMemoryUsedBytes());
   }

   public Aggregator(final Function1 createCombiner, final Function2 mergeValue, final Function2 mergeCombiners) {
      this.createCombiner = createCombiner;
      this.mergeValue = mergeValue;
      this.mergeCombiners = mergeCombiners;
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
