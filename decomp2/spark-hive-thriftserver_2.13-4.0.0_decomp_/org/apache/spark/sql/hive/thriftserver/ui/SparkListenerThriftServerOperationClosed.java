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
   bytes = "\u0006\u0005\u0005ud!B\r\u001b\u0001rA\u0003\u0002C#\u0001\u0005+\u0007I\u0011\u0001$\t\u0011=\u0003!\u0011#Q\u0001\n\u001dC\u0001\u0002\u0015\u0001\u0003\u0016\u0004%\t!\u0015\u0005\t+\u0002\u0011\t\u0012)A\u0005%\")a\u000b\u0001C\u0001/\"9A\fAA\u0001\n\u0003i\u0006b\u00021\u0001#\u0003%\t!\u0019\u0005\bY\u0002\t\n\u0011\"\u0001n\u0011\u001dy\u0007!!A\u0005BADq\u0001\u001f\u0001\u0002\u0002\u0013\u0005\u0011\u0010C\u0004~\u0001\u0005\u0005I\u0011\u0001@\t\u0013\u0005%\u0001!!A\u0005B\u0005-\u0001\"CA\r\u0001\u0005\u0005I\u0011AA\u000e\u0011%\t)\u0003AA\u0001\n\u0003\n9\u0003C\u0005\u0002,\u0001\t\t\u0011\"\u0011\u0002.!I\u0011q\u0006\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0007\u0005\n\u0003g\u0001\u0011\u0011!C!\u0003k9!\"!\u000f\u001b\u0003\u0003E\t\u0001HA\u001e\r%I\"$!A\t\u0002q\ti\u0004\u0003\u0004W'\u0011\u0005\u0011Q\u000b\u0005\n\u0003_\u0019\u0012\u0011!C#\u0003cA\u0011\"a\u0016\u0014\u0003\u0003%\t)!\u0017\t\u0013\u0005}3#!A\u0005\u0002\u0006\u0005\u0004\"CA:'\u0005\u0005I\u0011BA;\u0005!\u001a\u0006/\u0019:l\u0019&\u001cH/\u001a8feRC'/\u001b4u'\u0016\u0014h/\u001a:Pa\u0016\u0014\u0018\r^5p]\u000ecwn]3e\u0015\tYB$\u0001\u0002vS*\u0011QDH\u0001\ri\"\u0014\u0018N\u001a;tKJ4XM\u001d\u0006\u0003?\u0001\nA\u0001[5wK*\u0011\u0011EI\u0001\u0004gFd'BA\u0012%\u0003\u0015\u0019\b/\u0019:l\u0015\t)c%\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002O\u0005\u0019qN]4\u0014\u000b\u0001Is&\u000e\u001d\u0011\u0005)jS\"A\u0016\u000b\u00031\nQa]2bY\u0006L!AL\u0016\u0003\r\u0005s\u0017PU3g!\t\u00014'D\u00012\u0015\t\u0011$%A\u0005tG\",G-\u001e7fe&\u0011A'\r\u0002\u0013'B\f'o\u001b'jgR,g.\u001a:Fm\u0016tG\u000f\u0005\u0002+m%\u0011qg\u000b\u0002\b!J|G-^2u!\tI$I\u0004\u0002;\u0001:\u00111hP\u0007\u0002y)\u0011QHP\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\tA&\u0003\u0002BW\u00059\u0001/Y2lC\u001e,\u0017BA\"E\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t\t5&\u0001\u0002jIV\tq\t\u0005\u0002I\u0019:\u0011\u0011J\u0013\t\u0003w-J!aS\u0016\u0002\rA\u0013X\rZ3g\u0013\tieJ\u0001\u0004TiJLgn\u001a\u0006\u0003\u0017.\n1!\u001b3!\u0003%\u0019Gn\\:f)&lW-F\u0001S!\tQ3+\u0003\u0002UW\t!Aj\u001c8h\u0003)\u0019Gn\\:f)&lW\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007aS6\f\u0005\u0002Z\u00015\t!\u0004C\u0003F\u000b\u0001\u0007q\tC\u0003Q\u000b\u0001\u0007!+\u0001\u0003d_BLHc\u0001-_?\"9QI\u0002I\u0001\u0002\u00049\u0005b\u0002)\u0007!\u0003\u0005\rAU\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005\u0011'FA$dW\u0005!\u0007CA3k\u001b\u00051'BA4i\u0003%)hn\u00195fG.,GM\u0003\u0002jW\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005-4'!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012T#\u00018+\u0005I\u001b\u0017!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001r!\t\u0011x/D\u0001t\u0015\t!X/\u0001\u0003mC:<'\"\u0001<\u0002\t)\fg/Y\u0005\u0003\u001bN\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012A\u001f\t\u0003UmL!\u0001`\u0016\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0007}\f)\u0001E\u0002+\u0003\u0003I1!a\u0001,\u0005\r\te.\u001f\u0005\t\u0003\u000fY\u0011\u0011!a\u0001u\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!!\u0004\u0011\u000b\u0005=\u0011QC@\u000e\u0005\u0005E!bAA\nW\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005]\u0011\u0011\u0003\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002\u001e\u0005\r\u0002c\u0001\u0016\u0002 %\u0019\u0011\u0011E\u0016\u0003\u000f\t{w\u000e\\3b]\"A\u0011qA\u0007\u0002\u0002\u0003\u0007q0\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,GcA9\u0002*!A\u0011q\u0001\b\u0002\u0002\u0003\u0007!0\u0001\u0005iCND7i\u001c3f)\u0005Q\u0018\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003E\fa!Z9vC2\u001cH\u0003BA\u000f\u0003oA\u0001\"a\u0002\u0012\u0003\u0003\u0005\ra`\u0001)'B\f'o\u001b'jgR,g.\u001a:UQJLg\r^*feZ,'o\u00149fe\u0006$\u0018n\u001c8DY>\u001cX\r\u001a\t\u00033N\u0019RaEA \u0003\u0017\u0002r!!\u0011\u0002H\u001d\u0013\u0006,\u0004\u0002\u0002D)\u0019\u0011QI\u0016\u0002\u000fI,h\u000e^5nK&!\u0011\u0011JA\"\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gN\r\t\u0005\u0003\u001b\n\u0019&\u0004\u0002\u0002P)\u0019\u0011\u0011K;\u0002\u0005%|\u0017bA\"\u0002PQ\u0011\u00111H\u0001\u0006CB\u0004H.\u001f\u000b\u00061\u0006m\u0013Q\f\u0005\u0006\u000bZ\u0001\ra\u0012\u0005\u0006!Z\u0001\rAU\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\t\u0019'a\u001c\u0011\u000b)\n)'!\u001b\n\u0007\u0005\u001d4F\u0001\u0004PaRLwN\u001c\t\u0006U\u0005-tIU\u0005\u0004\u0003[Z#A\u0002+va2,'\u0007\u0003\u0005\u0002r]\t\t\u00111\u0001Y\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003o\u00022A]A=\u0013\r\tYh\u001d\u0002\u0007\u001f\nTWm\u0019;"
)
public class SparkListenerThriftServerOperationClosed implements SparkListenerEvent, Product, Serializable {
   private final String id;
   private final long closeTime;

   public static Option unapply(final SparkListenerThriftServerOperationClosed x$0) {
      return SparkListenerThriftServerOperationClosed$.MODULE$.unapply(x$0);
   }

   public static SparkListenerThriftServerOperationClosed apply(final String id, final long closeTime) {
      return SparkListenerThriftServerOperationClosed$.MODULE$.apply(id, closeTime);
   }

   public static Function1 tupled() {
      return SparkListenerThriftServerOperationClosed$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return SparkListenerThriftServerOperationClosed$.MODULE$.curried();
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

   public long closeTime() {
      return this.closeTime;
   }

   public SparkListenerThriftServerOperationClosed copy(final String id, final long closeTime) {
      return new SparkListenerThriftServerOperationClosed(id, closeTime);
   }

   public String copy$default$1() {
      return this.id();
   }

   public long copy$default$2() {
      return this.closeTime();
   }

   public String productPrefix() {
      return "SparkListenerThriftServerOperationClosed";
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
            return BoxesRunTime.boxToLong(this.closeTime());
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
      return x$1 instanceof SparkListenerThriftServerOperationClosed;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "id";
         }
         case 1 -> {
            return "closeTime";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.id()));
      var1 = Statics.mix(var1, Statics.longHash(this.closeTime()));
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label51: {
            if (x$1 instanceof SparkListenerThriftServerOperationClosed) {
               SparkListenerThriftServerOperationClosed var4 = (SparkListenerThriftServerOperationClosed)x$1;
               if (this.closeTime() == var4.closeTime()) {
                  label44: {
                     String var10000 = this.id();
                     String var5 = var4.id();
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

   public SparkListenerThriftServerOperationClosed(final String id, final long closeTime) {
      this.id = id;
      this.closeTime = closeTime;
      SparkListenerEvent.$init$(this);
      Product.$init$(this);
   }
}
