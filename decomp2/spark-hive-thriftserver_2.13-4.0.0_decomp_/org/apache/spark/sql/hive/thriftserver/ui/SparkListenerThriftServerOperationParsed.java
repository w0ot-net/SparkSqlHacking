package org.apache.spark.sql.hive.thriftserver.ui;

import java.io.Serializable;
import org.apache.spark.scheduler.SparkListenerEvent;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ed!B\r\u001b\u0001rA\u0003\u0002C#\u0001\u0005+\u0007I\u0011\u0001$\t\u0011=\u0003!\u0011#Q\u0001\n\u001dC\u0001\u0002\u0015\u0001\u0003\u0016\u0004%\tA\u0012\u0005\t#\u0002\u0011\t\u0012)A\u0005\u000f\")!\u000b\u0001C\u0001'\"9\u0001\fAA\u0001\n\u0003I\u0006b\u0002/\u0001#\u0003%\t!\u0018\u0005\bQ\u0002\t\n\u0011\"\u0001^\u0011\u001dI\u0007!!A\u0005B)DqA\u001d\u0001\u0002\u0002\u0013\u00051\u000fC\u0004x\u0001\u0005\u0005I\u0011\u0001=\t\u000fy\u0004\u0011\u0011!C!\u007f\"I\u0011Q\u0002\u0001\u0002\u0002\u0013\u0005\u0011q\u0002\u0005\n\u00033\u0001\u0011\u0011!C!\u00037A\u0011\"a\b\u0001\u0003\u0003%\t%!\t\t\u0013\u0005\r\u0002!!A\u0005B\u0005\u0015\u0002\"CA\u0014\u0001\u0005\u0005I\u0011IA\u0015\u000f)\tiCGA\u0001\u0012\u0003a\u0012q\u0006\u0004\n3i\t\t\u0011#\u0001\u001d\u0003cAaAU\n\u0005\u0002\u0005%\u0003\"CA\u0012'\u0005\u0005IQIA\u0013\u0011%\tYeEA\u0001\n\u0003\u000bi\u0005C\u0005\u0002TM\t\t\u0011\"!\u0002V!I\u0011qM\n\u0002\u0002\u0013%\u0011\u0011\u000e\u0002)'B\f'o\u001b'jgR,g.\u001a:UQJLg\r^*feZ,'o\u00149fe\u0006$\u0018n\u001c8QCJ\u001cX\r\u001a\u0006\u00037q\t!!^5\u000b\u0005uq\u0012\u0001\u0004;ie&4Go]3sm\u0016\u0014(BA\u0010!\u0003\u0011A\u0017N^3\u000b\u0005\u0005\u0012\u0013aA:rY*\u00111\u0005J\u0001\u0006gB\f'o\u001b\u0006\u0003K\u0019\na!\u00199bG\",'\"A\u0014\u0002\u0007=\u0014xmE\u0003\u0001S=*\u0004\b\u0005\u0002+[5\t1FC\u0001-\u0003\u0015\u00198-\u00197b\u0013\tq3F\u0001\u0004B]f\u0014VM\u001a\t\u0003aMj\u0011!\r\u0006\u0003e\t\n\u0011b]2iK\u0012,H.\u001a:\n\u0005Q\n$AE*qCJ\\G*[:uK:,'/\u0012<f]R\u0004\"A\u000b\u001c\n\u0005]Z#a\u0002)s_\u0012,8\r\u001e\t\u0003s\ts!A\u000f!\u000f\u0005mzT\"\u0001\u001f\u000b\u0005ur\u0014A\u0002\u001fs_>$hh\u0001\u0001\n\u00031J!!Q\u0016\u0002\u000fA\f7m[1hK&\u00111\t\u0012\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003\u0003.\n!!\u001b3\u0016\u0003\u001d\u0003\"\u0001\u0013'\u000f\u0005%S\u0005CA\u001e,\u0013\tY5&\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u001b:\u0013aa\u0015;sS:<'BA&,\u0003\rIG\rI\u0001\u000eKb,7-\u001e;j_:\u0004F.\u00198\u0002\u001d\u0015DXmY;uS>t\u0007\u000b\\1oA\u00051A(\u001b8jiz\"2\u0001\u0016,X!\t)\u0006!D\u0001\u001b\u0011\u0015)U\u00011\u0001H\u0011\u0015\u0001V\u00011\u0001H\u0003\u0011\u0019w\u000e]=\u0015\u0007QS6\fC\u0004F\rA\u0005\t\u0019A$\t\u000fA3\u0001\u0013!a\u0001\u000f\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#\u00010+\u0005\u001d{6&\u00011\u0011\u0005\u00054W\"\u00012\u000b\u0005\r$\u0017!C;oG\",7m[3e\u0015\t)7&\u0001\u0006b]:|G/\u0019;j_:L!a\u001a2\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005Y\u0007C\u00017r\u001b\u0005i'B\u00018p\u0003\u0011a\u0017M\\4\u000b\u0003A\fAA[1wC&\u0011Q*\\\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002iB\u0011!&^\u0005\u0003m.\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"!\u001f?\u0011\u0005)R\u0018BA>,\u0005\r\te.\u001f\u0005\b{.\t\t\u00111\u0001u\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011\u0011\u0001\t\u0006\u0003\u0007\tI!_\u0007\u0003\u0003\u000bQ1!a\u0002,\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003\u0017\t)A\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA\t\u0003/\u00012AKA\n\u0013\r\t)b\u000b\u0002\b\u0005>|G.Z1o\u0011\u001diX\"!AA\u0002e\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u00191.!\b\t\u000fut\u0011\u0011!a\u0001i\u0006A\u0001.Y:i\u0007>$W\rF\u0001u\u0003!!xn\u0015;sS:<G#A6\u0002\r\u0015\fX/\u00197t)\u0011\t\t\"a\u000b\t\u000fu\f\u0012\u0011!a\u0001s\u0006A3\u000b]1sW2K7\u000f^3oKJ$\u0006N]5giN+'O^3s\u001fB,'/\u0019;j_:\u0004\u0016M]:fIB\u0011QkE\n\u0006'\u0005M\u0012q\b\t\b\u0003k\tYdR$U\u001b\t\t9DC\u0002\u0002:-\nqA];oi&lW-\u0003\u0003\u0002>\u0005]\"!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8oeA!\u0011\u0011IA$\u001b\t\t\u0019EC\u0002\u0002F=\f!![8\n\u0007\r\u000b\u0019\u0005\u0006\u0002\u00020\u0005)\u0011\r\u001d9msR)A+a\u0014\u0002R!)QI\u0006a\u0001\u000f\")\u0001K\u0006a\u0001\u000f\u00069QO\\1qa2LH\u0003BA,\u0003G\u0002RAKA-\u0003;J1!a\u0017,\u0005\u0019y\u0005\u000f^5p]B)!&a\u0018H\u000f&\u0019\u0011\u0011M\u0016\u0003\rQ+\b\u000f\\33\u0011!\t)gFA\u0001\u0002\u0004!\u0016a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u00111\u000e\t\u0004Y\u00065\u0014bAA8[\n1qJ\u00196fGR\u0004"
)
public class SparkListenerThriftServerOperationParsed implements SparkListenerEvent, Product, Serializable {
   private final String id;
   private final String executionPlan;

   public static Option unapply(final SparkListenerThriftServerOperationParsed x$0) {
      return SparkListenerThriftServerOperationParsed$.MODULE$.unapply(x$0);
   }

   public static SparkListenerThriftServerOperationParsed apply(final String id, final String executionPlan) {
      return SparkListenerThriftServerOperationParsed$.MODULE$.apply(id, executionPlan);
   }

   public static Function1 tupled() {
      return SparkListenerThriftServerOperationParsed$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return SparkListenerThriftServerOperationParsed$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean logEvent() {
      return SparkListenerEvent.logEvent$(this);
   }

   public String id() {
      return this.id;
   }

   public String executionPlan() {
      return this.executionPlan;
   }

   public SparkListenerThriftServerOperationParsed copy(final String id, final String executionPlan) {
      return new SparkListenerThriftServerOperationParsed(id, executionPlan);
   }

   public String copy$default$1() {
      return this.id();
   }

   public String copy$default$2() {
      return this.executionPlan();
   }

   public String productPrefix() {
      return "SparkListenerThriftServerOperationParsed";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.id();
         }
         case 1 -> {
            return this.executionPlan();
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
      return x$1 instanceof SparkListenerThriftServerOperationParsed;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "id";
         }
         case 1 -> {
            return "executionPlan";
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
      boolean var8;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof SparkListenerThriftServerOperationParsed) {
               label48: {
                  SparkListenerThriftServerOperationParsed var4 = (SparkListenerThriftServerOperationParsed)x$1;
                  String var10000 = this.id();
                  String var5 = var4.id();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label48;
                  }

                  var10000 = this.executionPlan();
                  String var6 = var4.executionPlan();
                  if (var10000 == null) {
                     if (var6 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var6)) {
                     break label48;
                  }

                  if (var4.canEqual(this)) {
                     break label55;
                  }
               }
            }

            var8 = false;
            return var8;
         }
      }

      var8 = true;
      return var8;
   }

   public SparkListenerThriftServerOperationParsed(final String id, final String executionPlan) {
      this.id = id;
      this.executionPlan = executionPlan;
      SparkListenerEvent.$init$(this);
      Product.$init$(this);
   }
}
