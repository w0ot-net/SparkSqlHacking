package org.apache.spark.sql.hive.thriftserver.ui;

import java.io.Serializable;
import org.apache.spark.scheduler.SparkListenerEvent;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ud!B\r\u001b\u0001rA\u0003\u0002C#\u0001\u0005+\u0007I\u0011\u0001$\t\u0011=\u0003!\u0011#Q\u0001\n\u001dC\u0001\u0002\u0015\u0001\u0003\u0016\u0004%\t!\u0015\u0005\t+\u0002\u0011\t\u0012)A\u0005%\")a\u000b\u0001C\u0001/\"9A\fAA\u0001\n\u0003i\u0006b\u00021\u0001#\u0003%\t!\u0019\u0005\bY\u0002\t\n\u0011\"\u0001n\u0011\u001dy\u0007!!A\u0005BADq\u0001\u001f\u0001\u0002\u0002\u0013\u0005\u0011\u0010C\u0004~\u0001\u0005\u0005I\u0011\u0001@\t\u0013\u0005%\u0001!!A\u0005B\u0005-\u0001\"CA\r\u0001\u0005\u0005I\u0011AA\u000e\u0011%\t)\u0003AA\u0001\n\u0003\n9\u0003C\u0005\u0002,\u0001\t\t\u0011\"\u0011\u0002.!I\u0011q\u0006\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0007\u0005\n\u0003g\u0001\u0011\u0011!C!\u0003k9!\"!\u000f\u001b\u0003\u0003E\t\u0001HA\u001e\r%I\"$!A\t\u0002q\ti\u0004\u0003\u0004W'\u0011\u0005\u0011Q\u000b\u0005\n\u0003_\u0019\u0012\u0011!C#\u0003cA\u0011\"a\u0016\u0014\u0003\u0003%\t)!\u0017\t\u0013\u0005}3#!A\u0005\u0002\u0006\u0005\u0004\"CA:'\u0005\u0005I\u0011BA;\u0005\u0019\u001a\u0006/\u0019:l\u0019&\u001cH/\u001a8feRC'/\u001b4u'\u0016\u0014h/\u001a:TKN\u001c\u0018n\u001c8DY>\u001cX\r\u001a\u0006\u00037q\t!!^5\u000b\u0005uq\u0012\u0001\u0004;ie&4Go]3sm\u0016\u0014(BA\u0010!\u0003\u0011A\u0017N^3\u000b\u0005\u0005\u0012\u0013aA:rY*\u00111\u0005J\u0001\u0006gB\f'o\u001b\u0006\u0003K\u0019\na!\u00199bG\",'\"A\u0014\u0002\u0007=\u0014xmE\u0003\u0001S=*\u0004\b\u0005\u0002+[5\t1FC\u0001-\u0003\u0015\u00198-\u00197b\u0013\tq3F\u0001\u0004B]f\u0014VM\u001a\t\u0003aMj\u0011!\r\u0006\u0003e\t\n\u0011b]2iK\u0012,H.\u001a:\n\u0005Q\n$AE*qCJ\\G*[:uK:,'/\u0012<f]R\u0004\"A\u000b\u001c\n\u0005]Z#a\u0002)s_\u0012,8\r\u001e\t\u0003s\ts!A\u000f!\u000f\u0005mzT\"\u0001\u001f\u000b\u0005ur\u0014A\u0002\u001fs_>$hh\u0001\u0001\n\u00031J!!Q\u0016\u0002\u000fA\f7m[1hK&\u00111\t\u0012\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003\u0003.\n\u0011b]3tg&|g.\u00133\u0016\u0003\u001d\u0003\"\u0001\u0013'\u000f\u0005%S\u0005CA\u001e,\u0013\tY5&\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u001b:\u0013aa\u0015;sS:<'BA&,\u0003)\u0019Xm]:j_:LE\rI\u0001\u000bM&t\u0017n\u001d5US6,W#\u0001*\u0011\u0005)\u001a\u0016B\u0001+,\u0005\u0011auN\\4\u0002\u0017\u0019Lg.[:i)&lW\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007aS6\f\u0005\u0002Z\u00015\t!\u0004C\u0003F\u000b\u0001\u0007q\tC\u0003Q\u000b\u0001\u0007!+\u0001\u0003d_BLHc\u0001-_?\"9QI\u0002I\u0001\u0002\u00049\u0005b\u0002)\u0007!\u0003\u0005\rAU\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005\u0011'FA$dW\u0005!\u0007CA3k\u001b\u00051'BA4i\u0003%)hn\u00195fG.,GM\u0003\u0002jW\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005-4'!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012T#\u00018+\u0005I\u001b\u0017!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001r!\t\u0011x/D\u0001t\u0015\t!X/\u0001\u0003mC:<'\"\u0001<\u0002\t)\fg/Y\u0005\u0003\u001bN\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012A\u001f\t\u0003UmL!\u0001`\u0016\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0007}\f)\u0001E\u0002+\u0003\u0003I1!a\u0001,\u0005\r\te.\u001f\u0005\t\u0003\u000fY\u0011\u0011!a\u0001u\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!!\u0004\u0011\u000b\u0005=\u0011QC@\u000e\u0005\u0005E!bAA\nW\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005]\u0011\u0011\u0003\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002\u001e\u0005\r\u0002c\u0001\u0016\u0002 %\u0019\u0011\u0011E\u0016\u0003\u000f\t{w\u000e\\3b]\"A\u0011qA\u0007\u0002\u0002\u0003\u0007q0\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,GcA9\u0002*!A\u0011q\u0001\b\u0002\u0002\u0003\u0007!0\u0001\u0005iCND7i\u001c3f)\u0005Q\u0018\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003E\fa!Z9vC2\u001cH\u0003BA\u000f\u0003oA\u0001\"a\u0002\u0012\u0003\u0003\u0005\ra`\u0001''B\f'o\u001b'jgR,g.\u001a:UQJLg\r^*feZ,'oU3tg&|gn\u00117pg\u0016$\u0007CA-\u0014'\u0015\u0019\u0012qHA&!\u001d\t\t%a\u0012H%bk!!a\u0011\u000b\u0007\u0005\u00153&A\u0004sk:$\u0018.\\3\n\t\u0005%\u00131\t\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u0014\u0004\u0003BA'\u0003'j!!a\u0014\u000b\u0007\u0005ES/\u0001\u0002j_&\u00191)a\u0014\u0015\u0005\u0005m\u0012!B1qa2LH#\u0002-\u0002\\\u0005u\u0003\"B#\u0017\u0001\u00049\u0005\"\u0002)\u0017\u0001\u0004\u0011\u0016aB;oCB\u0004H.\u001f\u000b\u0005\u0003G\ny\u0007E\u0003+\u0003K\nI'C\u0002\u0002h-\u0012aa\u00149uS>t\u0007#\u0002\u0016\u0002l\u001d\u0013\u0016bAA7W\t1A+\u001e9mKJB\u0001\"!\u001d\u0018\u0003\u0003\u0005\r\u0001W\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA<!\r\u0011\u0018\u0011P\u0005\u0004\u0003w\u001a(AB(cU\u0016\u001cG\u000f"
)
public class SparkListenerThriftServerSessionClosed implements SparkListenerEvent, Product, Serializable {
   private final String sessionId;
   private final long finishTime;

   public static Option unapply(final SparkListenerThriftServerSessionClosed x$0) {
      return SparkListenerThriftServerSessionClosed$.MODULE$.unapply(x$0);
   }

   public static SparkListenerThriftServerSessionClosed apply(final String sessionId, final long finishTime) {
      return SparkListenerThriftServerSessionClosed$.MODULE$.apply(sessionId, finishTime);
   }

   public static Function1 tupled() {
      return SparkListenerThriftServerSessionClosed$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return SparkListenerThriftServerSessionClosed$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean logEvent() {
      return SparkListenerEvent.logEvent$(this);
   }

   public String sessionId() {
      return this.sessionId;
   }

   public long finishTime() {
      return this.finishTime;
   }

   public SparkListenerThriftServerSessionClosed copy(final String sessionId, final long finishTime) {
      return new SparkListenerThriftServerSessionClosed(sessionId, finishTime);
   }

   public String copy$default$1() {
      return this.sessionId();
   }

   public long copy$default$2() {
      return this.finishTime();
   }

   public String productPrefix() {
      return "SparkListenerThriftServerSessionClosed";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.sessionId();
         }
         case 1 -> {
            return BoxesRunTime.boxToLong(this.finishTime());
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
      return x$1 instanceof SparkListenerThriftServerSessionClosed;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "sessionId";
         }
         case 1 -> {
            return "finishTime";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.sessionId()));
      var1 = Statics.mix(var1, Statics.longHash(this.finishTime()));
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label51: {
            if (x$1 instanceof SparkListenerThriftServerSessionClosed) {
               SparkListenerThriftServerSessionClosed var4 = (SparkListenerThriftServerSessionClosed)x$1;
               if (this.finishTime() == var4.finishTime()) {
                  label44: {
                     String var10000 = this.sessionId();
                     String var5 = var4.sessionId();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label44;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label44;
                     }

                     if (var4.canEqual(this)) {
                        break label51;
                     }
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

   public SparkListenerThriftServerSessionClosed(final String sessionId, final long finishTime) {
      this.sessionId = sessionId;
      this.finishTime = finishTime;
      SparkListenerEvent.$init$(this);
      Product.$init$(this);
   }
}
