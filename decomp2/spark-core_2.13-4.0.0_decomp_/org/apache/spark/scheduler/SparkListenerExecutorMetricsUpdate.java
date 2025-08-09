package org.apache.spark.scheduler;

import java.io.Serializable;
import org.apache.spark.annotation.DeveloperApi;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rg\u0001\u0002\u0010 \u0001\"B\u0001B\u0011\u0001\u0003\u0016\u0004%\ta\u0011\u0005\t\u0019\u0002\u0011\t\u0012)A\u0005\t\"AQ\n\u0001BK\u0002\u0013\u0005a\n\u0003\u0005`\u0001\tE\t\u0015!\u0003P\u0011!\u0001\u0007A!f\u0001\n\u0003\t\u0007\u0002C9\u0001\u0005#\u0005\u000b\u0011\u00022\t\u000bI\u0004A\u0011A:\t\u000fa\u0004\u0011\u0011!C\u0001s\"9Q\u0010AI\u0001\n\u0003q\b\"CA\n\u0001E\u0005I\u0011AA\u000b\u0011%\tI\u0002AI\u0001\n\u0003\tY\u0002C\u0005\u0002 \u0001\t\t\u0011\"\u0011\u0002\"!I\u0011\u0011\u0007\u0001\u0002\u0002\u0013\u0005\u00111\u0007\u0005\n\u0003k\u0001\u0011\u0011!C\u0001\u0003oA\u0011\"a\u0011\u0001\u0003\u0003%\t%!\u0012\t\u0013\u00055\u0003!!A\u0005\u0002\u0005=\u0003\"CA-\u0001\u0005\u0005I\u0011IA.\u0011%\ty\u0006AA\u0001\n\u0003\n\t\u0007C\u0005\u0002d\u0001\t\t\u0011\"\u0011\u0002f!I\u0011q\r\u0001\u0002\u0002\u0013\u0005\u0013\u0011N\u0004\n\u0003sz\u0012\u0011!E\u0001\u0003w2\u0001BH\u0010\u0002\u0002#\u0005\u0011Q\u0010\u0005\u0007eZ!\t!!&\t\u0013\u0005\rd#!A\u0005F\u0005\u0015\u0004\"CAL-\u0005\u0005I\u0011QAM\u0011%\t\tKFI\u0001\n\u0003\tY\u0002C\u0005\u0002$Z\t\t\u0011\"!\u0002&\"I\u0011q\u0017\f\u0012\u0002\u0013\u0005\u00111\u0004\u0005\n\u0003s3\u0012\u0011!C\u0005\u0003w\u0013!e\u00159be.d\u0015n\u001d;f]\u0016\u0014X\t_3dkR|'/T3ue&\u001c7/\u00169eCR,'B\u0001\u0011\"\u0003%\u00198\r[3ek2,'O\u0003\u0002#G\u0005)1\u000f]1sW*\u0011A%J\u0001\u0007CB\f7\r[3\u000b\u0003\u0019\n1a\u001c:h\u0007\u0001\u0019R\u0001A\u00150gY\u0002\"AK\u0017\u000e\u0003-R\u0011\u0001L\u0001\u0006g\u000e\fG.Y\u0005\u0003]-\u0012a!\u00118z%\u00164\u0007C\u0001\u00192\u001b\u0005y\u0012B\u0001\u001a \u0005I\u0019\u0006/\u0019:l\u0019&\u001cH/\u001a8fe\u00163XM\u001c;\u0011\u0005)\"\u0014BA\u001b,\u0005\u001d\u0001&o\u001c3vGR\u0004\"aN \u000f\u0005ajdBA\u001d=\u001b\u0005Q$BA\u001e(\u0003\u0019a$o\\8u}%\tA&\u0003\u0002?W\u00059\u0001/Y2lC\u001e,\u0017B\u0001!B\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tq4&\u0001\u0004fq\u0016\u001c\u0017\nZ\u000b\u0002\tB\u0011Q)\u0013\b\u0003\r\u001e\u0003\"!O\u0016\n\u0005![\u0013A\u0002)sK\u0012,g-\u0003\u0002K\u0017\n11\u000b\u001e:j]\u001eT!\u0001S\u0016\u0002\u000f\u0015DXmY%eA\u0005a\u0011mY2v[V\u0003H-\u0019;fgV\tq\nE\u00028!JK!!U!\u0003\u0007M+\u0017\u000f\u0005\u0004+'VC\u0006lW\u0005\u0003).\u0012a\u0001V;qY\u0016$\u0004C\u0001\u0016W\u0013\t96F\u0001\u0003M_:<\u0007C\u0001\u0016Z\u0013\tQ6FA\u0002J]R\u00042a\u000e)]!\t\u0001T,\u0003\u0002_?\ty\u0011iY2v[Vd\u0017M\u00197f\u0013:4w.A\u0007bG\u000e,X.\u00169eCR,7\u000fI\u0001\u0010Kb,7-\u001e;peV\u0003H-\u0019;fgV\t!\r\u0005\u0003dM\"\\W\"\u00013\u000b\u0005\u0015\\\u0013AC2pY2,7\r^5p]&\u0011q\r\u001a\u0002\u0004\u001b\u0006\u0004\b\u0003\u0002\u0016j1bK!A[\u0016\u0003\rQ+\b\u000f\\33!\taw.D\u0001n\u0015\tq\u0017%\u0001\u0005fq\u0016\u001cW\u000f^8s\u0013\t\u0001XNA\bFq\u0016\u001cW\u000f^8s\u001b\u0016$(/[2t\u0003A)\u00070Z2vi>\u0014X\u000b\u001d3bi\u0016\u001c\b%\u0001\u0004=S:LGO\u0010\u000b\u0005iV4x\u000f\u0005\u00021\u0001!)!i\u0002a\u0001\t\")Qj\u0002a\u0001\u001f\"9\u0001m\u0002I\u0001\u0002\u0004\u0011\u0017\u0001B2paf$B\u0001\u001e>|y\"9!\t\u0003I\u0001\u0002\u0004!\u0005bB'\t!\u0003\u0005\ra\u0014\u0005\bA\"\u0001\n\u00111\u0001c\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012a \u0016\u0004\t\u0006\u00051FAA\u0002!\u0011\t)!a\u0004\u000e\u0005\u0005\u001d!\u0002BA\u0005\u0003\u0017\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u000551&\u0001\u0006b]:|G/\u0019;j_:LA!!\u0005\u0002\b\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\u0011\u0011q\u0003\u0016\u0004\u001f\u0006\u0005\u0011AD2paf$C-\u001a4bk2$HeM\u000b\u0003\u0003;Q3AYA\u0001\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011\u00111\u0005\t\u0005\u0003K\ty#\u0004\u0002\u0002()!\u0011\u0011FA\u0016\u0003\u0011a\u0017M\\4\u000b\u0005\u00055\u0012\u0001\u00026bm\u0006L1ASA\u0014\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005A\u0016A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0005\u0003s\ty\u0004E\u0002+\u0003wI1!!\u0010,\u0005\r\te.\u001f\u0005\t\u0003\u0003r\u0011\u0011!a\u00011\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!a\u0012\u0011\u000b\r\fI%!\u000f\n\u0007\u0005-CM\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA)\u0003/\u00022AKA*\u0013\r\t)f\u000b\u0002\b\u0005>|G.Z1o\u0011%\t\t\u0005EA\u0001\u0002\u0004\tI$\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BA\u0012\u0003;B\u0001\"!\u0011\u0012\u0003\u0003\u0005\r\u0001W\u0001\tQ\u0006\u001c\bnQ8eKR\t\u0001,\u0001\u0005u_N#(/\u001b8h)\t\t\u0019#\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003#\nY\u0007C\u0005\u0002BQ\t\t\u00111\u0001\u0002:!\u001a\u0001!a\u001c\u0011\t\u0005E\u0014QO\u0007\u0003\u0003gR1!!\u0004\"\u0013\u0011\t9(a\u001d\u0003\u0019\u0011+g/\u001a7pa\u0016\u0014\u0018\t]5\u0002EM\u0003\u0018M]6MSN$XM\\3s\u000bb,7-\u001e;pe6+GO]5dgV\u0003H-\u0019;f!\t\u0001dcE\u0003\u0017\u0003\u007f\nY\t\u0005\u0005\u0002\u0002\u0006\u001dEi\u00142u\u001b\t\t\u0019IC\u0002\u0002\u0006.\nqA];oi&lW-\u0003\u0003\u0002\n\u0006\r%!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8ogA!\u0011QRAJ\u001b\t\tyI\u0003\u0003\u0002\u0012\u0006-\u0012AA5p\u0013\r\u0001\u0015q\u0012\u000b\u0003\u0003w\nQ!\u00199qYf$r\u0001^AN\u0003;\u000by\nC\u0003C3\u0001\u0007A\tC\u0003N3\u0001\u0007q\nC\u0004a3A\u0005\t\u0019\u00012\u0002\u001f\u0005\u0004\b\u000f\\=%I\u00164\u0017-\u001e7uIM\nq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002(\u0006M\u0006#\u0002\u0016\u0002*\u00065\u0016bAAVW\t1q\n\u001d;j_:\u0004bAKAX\t>\u0013\u0017bAAYW\t1A+\u001e9mKNB\u0001\"!.\u001c\u0003\u0003\u0005\r\u0001^\u0001\u0004q\u0012\u0002\u0014a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$3'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002>B!\u0011QEA`\u0013\u0011\t\t-a\n\u0003\r=\u0013'.Z2u\u0001"
)
public class SparkListenerExecutorMetricsUpdate implements SparkListenerEvent, Product, Serializable {
   private final String execId;
   private final Seq accumUpdates;
   private final Map executorUpdates;

   public static Map $lessinit$greater$default$3() {
      return SparkListenerExecutorMetricsUpdate$.MODULE$.$lessinit$greater$default$3();
   }

   public static Option unapply(final SparkListenerExecutorMetricsUpdate x$0) {
      return SparkListenerExecutorMetricsUpdate$.MODULE$.unapply(x$0);
   }

   public static Map apply$default$3() {
      return SparkListenerExecutorMetricsUpdate$.MODULE$.apply$default$3();
   }

   public static SparkListenerExecutorMetricsUpdate apply(final String execId, final Seq accumUpdates, final Map executorUpdates) {
      return SparkListenerExecutorMetricsUpdate$.MODULE$.apply(execId, accumUpdates, executorUpdates);
   }

   public static Function1 tupled() {
      return SparkListenerExecutorMetricsUpdate$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return SparkListenerExecutorMetricsUpdate$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean logEvent() {
      return SparkListenerEvent.logEvent$(this);
   }

   public String execId() {
      return this.execId;
   }

   public Seq accumUpdates() {
      return this.accumUpdates;
   }

   public Map executorUpdates() {
      return this.executorUpdates;
   }

   public SparkListenerExecutorMetricsUpdate copy(final String execId, final Seq accumUpdates, final Map executorUpdates) {
      return new SparkListenerExecutorMetricsUpdate(execId, accumUpdates, executorUpdates);
   }

   public String copy$default$1() {
      return this.execId();
   }

   public Seq copy$default$2() {
      return this.accumUpdates();
   }

   public Map copy$default$3() {
      return this.executorUpdates();
   }

   public String productPrefix() {
      return "SparkListenerExecutorMetricsUpdate";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.execId();
         }
         case 1 -> {
            return this.accumUpdates();
         }
         case 2 -> {
            return this.executorUpdates();
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
      return x$1 instanceof SparkListenerExecutorMetricsUpdate;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "execId";
         }
         case 1 -> {
            return "accumUpdates";
         }
         case 2 -> {
            return "executorUpdates";
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
      boolean var10;
      if (this != x$1) {
         label63: {
            if (x$1 instanceof SparkListenerExecutorMetricsUpdate) {
               label56: {
                  SparkListenerExecutorMetricsUpdate var4 = (SparkListenerExecutorMetricsUpdate)x$1;
                  String var10000 = this.execId();
                  String var5 = var4.execId();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label56;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label56;
                  }

                  Seq var8 = this.accumUpdates();
                  Seq var6 = var4.accumUpdates();
                  if (var8 == null) {
                     if (var6 != null) {
                        break label56;
                     }
                  } else if (!var8.equals(var6)) {
                     break label56;
                  }

                  Map var9 = this.executorUpdates();
                  Map var7 = var4.executorUpdates();
                  if (var9 == null) {
                     if (var7 != null) {
                        break label56;
                     }
                  } else if (!var9.equals(var7)) {
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

   public SparkListenerExecutorMetricsUpdate(final String execId, final Seq accumUpdates, final Map executorUpdates) {
      this.execId = execId;
      this.accumUpdates = accumUpdates;
      this.executorUpdates = executorUpdates;
      SparkListenerEvent.$init$(this);
      Product.$init$(this);
   }
}
