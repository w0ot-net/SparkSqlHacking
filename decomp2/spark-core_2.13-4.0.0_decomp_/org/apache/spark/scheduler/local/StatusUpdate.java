package org.apache.spark.scheduler.local;

import java.io.Serializable;
import java.nio.ByteBuffer;
import scala.Enumeration;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ee\u0001\u0002\u000f\u001e\t\"B\u0001B\u0010\u0001\u0003\u0016\u0004%\ta\u0010\u0005\t\u0007\u0002\u0011\t\u0012)A\u0005\u0001\"AA\t\u0001BK\u0002\u0013\u0005Q\t\u0003\u0005T\u0001\tE\t\u0015!\u0003G\u0011!!\u0006A!f\u0001\n\u0003)\u0006\u0002\u00030\u0001\u0005#\u0005\u000b\u0011\u0002,\t\u000b}\u0003A\u0011\u00011\t\u000f\u0019\u0004\u0011\u0011!C\u0001O\"91\u000eAI\u0001\n\u0003a\u0007bB<\u0001#\u0003%\t\u0001\u001f\u0005\bu\u0002\t\n\u0011\"\u0001|\u0011\u001di\b!!A\u0005ByD\u0011\"a\u0003\u0001\u0003\u0003%\t!!\u0004\t\u0013\u0005U\u0001!!A\u0005\u0002\u0005]\u0001\"CA\u0012\u0001\u0005\u0005I\u0011IA\u0013\u0011%\t\u0019\u0004AA\u0001\n\u0003\t)\u0004C\u0005\u0002@\u0001\t\t\u0011\"\u0011\u0002B!I\u0011Q\t\u0001\u0002\u0002\u0013\u0005\u0013q\t\u0005\n\u0003\u0013\u0002\u0011\u0011!C!\u0003\u0017B\u0011\"!\u0014\u0001\u0003\u0003%\t%a\u0014\b\u0013\u0005MS$!A\t\n\u0005Uc\u0001\u0003\u000f\u001e\u0003\u0003EI!a\u0016\t\r}3B\u0011AA8\u0011%\tIEFA\u0001\n\u000b\nY\u0005C\u0005\u0002rY\t\t\u0011\"!\u0002t!I\u00111\u0010\f\u0002\u0002\u0013\u0005\u0015Q\u0010\u0005\n\u0003\u001f3\u0012\u0011!C\u0005\u0003#\u0013Ab\u0015;biV\u001cX\u000b\u001d3bi\u0016T!AH\u0010\u0002\u000b1|7-\u00197\u000b\u0005\u0001\n\u0013!C:dQ\u0016$W\u000f\\3s\u0015\t\u00113%A\u0003ta\u0006\u00148N\u0003\u0002%K\u00051\u0011\r]1dQ\u0016T\u0011AJ\u0001\u0004_J<7\u0001A\n\u0005\u0001%z#\u0007\u0005\u0002+[5\t1FC\u0001-\u0003\u0015\u00198-\u00197b\u0013\tq3F\u0001\u0004B]f\u0014VM\u001a\t\u0003UAJ!!M\u0016\u0003\u000fA\u0013x\u000eZ;diB\u00111g\u000f\b\u0003ier!!\u000e\u001d\u000e\u0003YR!aN\u0014\u0002\rq\u0012xn\u001c;?\u0013\u0005a\u0013B\u0001\u001e,\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001P\u001f\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005iZ\u0013A\u0002;bg.LE-F\u0001A!\tQ\u0013)\u0003\u0002CW\t!Aj\u001c8h\u0003\u001d!\u0018m]6JI\u0002\nQa\u001d;bi\u0016,\u0012A\u0012\t\u0003\u000fBs!\u0001\u0013(\u000f\u0005%keB\u0001&M\u001d\t)4*C\u0001'\u0013\t!S%\u0003\u0002#G%\u0011q*I\u0001\n)\u0006\u001c8n\u0015;bi\u0016L!!\u0015*\u0003\u0013Q\u000b7o[*uCR,'BA(\"\u0003\u0019\u0019H/\u0019;fA\u0005q1/\u001a:jC2L'0\u001a3ECR\fW#\u0001,\u0011\u0005]cV\"\u0001-\u000b\u0005eS\u0016a\u00018j_*\t1,\u0001\u0003kCZ\f\u0017BA/Y\u0005)\u0011\u0015\u0010^3Ck\u001a4WM]\u0001\u0010g\u0016\u0014\u0018.\u00197ju\u0016$G)\u0019;bA\u00051A(\u001b8jiz\"B!Y2eKB\u0011!\rA\u0007\u0002;!)ah\u0002a\u0001\u0001\")Ai\u0002a\u0001\r\")Ak\u0002a\u0001-\u0006!1m\u001c9z)\u0011\t\u0007.\u001b6\t\u000fyB\u0001\u0013!a\u0001\u0001\"9A\t\u0003I\u0001\u0002\u00041\u0005b\u0002+\t!\u0003\u0005\rAV\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005i'F\u0001!oW\u0005y\u0007C\u00019v\u001b\u0005\t(B\u0001:t\u0003%)hn\u00195fG.,GM\u0003\u0002uW\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005Y\f(!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012T#A=+\u0005\u0019s\u0017AD2paf$C-\u001a4bk2$HeM\u000b\u0002y*\u0012aK\\\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003}\u0004B!!\u0001\u0002\b5\u0011\u00111\u0001\u0006\u0004\u0003\u000bQ\u0016\u0001\u00027b]\u001eLA!!\u0003\u0002\u0004\t11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\"!a\u0004\u0011\u0007)\n\t\"C\u0002\u0002\u0014-\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!!\u0007\u0002 A\u0019!&a\u0007\n\u0007\u0005u1FA\u0002B]fD\u0011\"!\t\u000f\u0003\u0003\u0005\r!a\u0004\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\t9\u0003\u0005\u0004\u0002*\u0005=\u0012\u0011D\u0007\u0003\u0003WQ1!!\f,\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003c\tYC\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA\u001c\u0003{\u00012AKA\u001d\u0013\r\tYd\u000b\u0002\b\u0005>|G.Z1o\u0011%\t\t\u0003EA\u0001\u0002\u0004\tI\"\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,GcA@\u0002D!I\u0011\u0011E\t\u0002\u0002\u0003\u0007\u0011qB\u0001\tQ\u0006\u001c\bnQ8eKR\u0011\u0011qB\u0001\ti>\u001cFO]5oOR\tq0\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003o\t\t\u0006C\u0005\u0002\"Q\t\t\u00111\u0001\u0002\u001a\u0005a1\u000b^1ukN,\u0006\u000fZ1uKB\u0011!MF\n\u0006-\u0005e\u0013Q\r\t\t\u00037\n\t\u0007\u0011$WC6\u0011\u0011Q\f\u0006\u0004\u0003?Z\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003G\niFA\tBEN$(/Y2u\rVt7\r^5p]N\u0002B!a\u001a\u0002n5\u0011\u0011\u0011\u000e\u0006\u0004\u0003WR\u0016AA5p\u0013\ra\u0014\u0011\u000e\u000b\u0003\u0003+\nQ!\u00199qYf$r!YA;\u0003o\nI\bC\u0003?3\u0001\u0007\u0001\tC\u0003E3\u0001\u0007a\tC\u0003U3\u0001\u0007a+A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005}\u00141\u0012\t\u0006U\u0005\u0005\u0015QQ\u0005\u0004\u0003\u0007[#AB(qi&|g\u000e\u0005\u0004+\u0003\u000f\u0003eIV\u0005\u0004\u0003\u0013[#A\u0002+va2,7\u0007\u0003\u0005\u0002\u000ej\t\t\u00111\u0001b\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003'\u0003B!!\u0001\u0002\u0016&!\u0011qSA\u0002\u0005\u0019y%M[3di\u0002"
)
public class StatusUpdate implements Product, Serializable {
   private final long taskId;
   private final Enumeration.Value state;
   private final ByteBuffer serializedData;

   public static Option unapply(final StatusUpdate x$0) {
      return StatusUpdate$.MODULE$.unapply(x$0);
   }

   public static StatusUpdate apply(final long taskId, final Enumeration.Value state, final ByteBuffer serializedData) {
      return StatusUpdate$.MODULE$.apply(taskId, state, serializedData);
   }

   public static Function1 tupled() {
      return StatusUpdate$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return StatusUpdate$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public long taskId() {
      return this.taskId;
   }

   public Enumeration.Value state() {
      return this.state;
   }

   public ByteBuffer serializedData() {
      return this.serializedData;
   }

   public StatusUpdate copy(final long taskId, final Enumeration.Value state, final ByteBuffer serializedData) {
      return new StatusUpdate(taskId, state, serializedData);
   }

   public long copy$default$1() {
      return this.taskId();
   }

   public Enumeration.Value copy$default$2() {
      return this.state();
   }

   public ByteBuffer copy$default$3() {
      return this.serializedData();
   }

   public String productPrefix() {
      return "StatusUpdate";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToLong(this.taskId());
         }
         case 1 -> {
            return this.state();
         }
         case 2 -> {
            return this.serializedData();
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
      return x$1 instanceof StatusUpdate;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "taskId";
         }
         case 1 -> {
            return "state";
         }
         case 2 -> {
            return "serializedData";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.longHash(this.taskId()));
      var1 = Statics.mix(var1, Statics.anyHash(this.state()));
      var1 = Statics.mix(var1, Statics.anyHash(this.serializedData()));
      return Statics.finalizeHash(var1, 3);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label59: {
            if (x$1 instanceof StatusUpdate) {
               StatusUpdate var4 = (StatusUpdate)x$1;
               if (this.taskId() == var4.taskId()) {
                  label52: {
                     Enumeration.Value var10000 = this.state();
                     Enumeration.Value var5 = var4.state();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label52;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label52;
                     }

                     ByteBuffer var7 = this.serializedData();
                     ByteBuffer var6 = var4.serializedData();
                     if (var7 == null) {
                        if (var6 != null) {
                           break label52;
                        }
                     } else if (!var7.equals(var6)) {
                        break label52;
                     }

                     if (var4.canEqual(this)) {
                        break label59;
                     }
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

   public StatusUpdate(final long taskId, final Enumeration.Value state, final ByteBuffer serializedData) {
      this.taskId = taskId;
      this.state = state;
      this.serializedData = serializedData;
      Product.$init$(this);
   }
}
