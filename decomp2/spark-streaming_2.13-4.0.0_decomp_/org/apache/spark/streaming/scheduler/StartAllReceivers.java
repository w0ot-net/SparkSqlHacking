package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001de!\u0002\f\u0018\u0001f\t\u0003\u0002\u0003\u001f\u0001\u0005+\u0007I\u0011A\u001f\t\u0011-\u0003!\u0011#Q\u0001\nyBQa\u0015\u0001\u0005\u0002QCq\u0001\u0018\u0001\u0002\u0002\u0013\u0005Q\fC\u0004`\u0001E\u0005I\u0011\u00011\t\u000f-\u0004\u0011\u0011!C!Y\"9Q\u000fAA\u0001\n\u00031\bb\u0002>\u0001\u0003\u0003%\ta\u001f\u0005\b}\u0002\t\t\u0011\"\u0011\u0000\u0011%\ti\u0001AA\u0001\n\u0003\ty\u0001C\u0005\u0002\u001a\u0001\t\t\u0011\"\u0011\u0002\u001c!I\u0011q\u0004\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0005\u0005\n\u0003G\u0001\u0011\u0011!C!\u0003KA\u0011\"a\n\u0001\u0003\u0003%\t%!\u000b\b\u0015\u00055r#!A\t\u0002e\tyCB\u0005\u0017/\u0005\u0005\t\u0012A\r\u00022!11\u000b\u0005C\u0001\u0003'B\u0011\"a\t\u0011\u0003\u0003%)%!\n\t\u0013\u0005U\u0003#!A\u0005\u0002\u0006]\u0003\"CA3!\u0005\u0005I\u0011QA4\u0011%\ti\bEA\u0001\n\u0013\tyHA\tTi\u0006\u0014H/\u00117m%\u0016\u001cW-\u001b<feNT!\u0001G\r\u0002\u0013M\u001c\u0007.\u001a3vY\u0016\u0014(B\u0001\u000e\u001c\u0003%\u0019HO]3b[&twM\u0003\u0002\u001d;\u0005)1\u000f]1sW*\u0011adH\u0001\u0007CB\f7\r[3\u000b\u0003\u0001\n1a\u001c:h'\u0015\u0001!\u0005\u000b\u00170!\t\u0019c%D\u0001%\u0015\u0005)\u0013!B:dC2\f\u0017BA\u0014%\u0005\u0019\te.\u001f*fMB\u0011\u0011FK\u0007\u0002/%\u00111f\u0006\u0002\u001c%\u0016\u001cW-\u001b<feR\u0013\u0018mY6fe2{7-\u00197NKN\u001c\u0018mZ3\u0011\u0005\rj\u0013B\u0001\u0018%\u0005\u001d\u0001&o\u001c3vGR\u0004\"\u0001M\u001d\u000f\u0005E:dB\u0001\u001a7\u001b\u0005\u0019$B\u0001\u001b6\u0003\u0019a$o\\8u}\r\u0001\u0011\"A\u0013\n\u0005a\"\u0013a\u00029bG.\fw-Z\u0005\u0003um\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!\u0001\u000f\u0013\u0002\u0011I,7-Z5wKJ,\u0012A\u0010\t\u0004a}\n\u0015B\u0001!<\u0005\r\u0019V-\u001d\u0019\u0003\u0005&\u00032aQ#H\u001b\u0005!%B\u0001\u001f\u001a\u0013\t1EI\u0001\u0005SK\u000e,\u0017N^3s!\tA\u0015\n\u0004\u0001\u0005\u0013)\u0013\u0011\u0011!A\u0001\u0006\u0003a%aA0%e\u0005I!/Z2fSZ,'\u000fI\t\u0003\u001bB\u0003\"a\t(\n\u0005=##a\u0002(pi\"Lgn\u001a\t\u0003GEK!A\u0015\u0013\u0003\u0007\u0005s\u00170\u0001\u0004=S:LGO\u0010\u000b\u0003+Z\u0003\"!\u000b\u0001\t\u000bq\u001a\u0001\u0019A,\u0011\u0007Az\u0004\f\r\u0002Z7B\u00191)\u0012.\u0011\u0005![F!\u0003&W\u0003\u0003\u0005\tQ!\u0001M\u0003\u0011\u0019w\u000e]=\u0015\u0005Us\u0006b\u0002\u001f\u0005!\u0003\u0005\raV\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005\t'F\u0001 cW\u0005\u0019\u0007C\u00013j\u001b\u0005)'B\u00014h\u0003%)hn\u00195fG.,GM\u0003\u0002iI\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005),'!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006i\u0001O]8ek\u000e$\bK]3gSb,\u0012!\u001c\t\u0003]Nl\u0011a\u001c\u0006\u0003aF\fA\u0001\\1oO*\t!/\u0001\u0003kCZ\f\u0017B\u0001;p\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5usV\tq\u000f\u0005\u0002$q&\u0011\u0011\u0010\n\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0003!rDq! \u0005\u0002\u0002\u0003\u0007q/A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003\u0003\u0001R!a\u0001\u0002\nAk!!!\u0002\u000b\u0007\u0005\u001dA%\u0001\u0006d_2dWm\u0019;j_:LA!a\u0003\u0002\u0006\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\t\t\"a\u0006\u0011\u0007\r\n\u0019\"C\u0002\u0002\u0016\u0011\u0012qAQ8pY\u0016\fg\u000eC\u0004~\u0015\u0005\u0005\t\u0019\u0001)\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0004[\u0006u\u0001bB?\f\u0003\u0003\u0005\ra^\u0001\tQ\u0006\u001c\bnQ8eKR\tq/\u0001\u0005u_N#(/\u001b8h)\u0005i\u0017AB3rk\u0006d7\u000f\u0006\u0003\u0002\u0012\u0005-\u0002bB?\u000f\u0003\u0003\u0005\r\u0001U\u0001\u0012'R\f'\u000f^!mYJ+7-Z5wKJ\u001c\bCA\u0015\u0011'\u0015\u0001\u00121GA%!\u001d\t)$a\u000f\u0002@Uk!!a\u000e\u000b\u0007\u0005eB%A\u0004sk:$\u0018.\\3\n\t\u0005u\u0012q\u0007\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\f\u0004\u0003\u0002\u0019@\u0003\u0003\u0002D!a\u0011\u0002HA!1)RA#!\rA\u0015q\t\u0003\n\u0015B\t\t\u0011!A\u0003\u00021\u0003B!a\u0013\u0002R5\u0011\u0011Q\n\u0006\u0004\u0003\u001f\n\u0018AA5p\u0013\rQ\u0014Q\n\u000b\u0003\u0003_\tQ!\u00199qYf$2!VA-\u0011\u0019a4\u00031\u0001\u0002\\A!\u0001gPA/a\u0011\ty&a\u0019\u0011\t\r+\u0015\u0011\r\t\u0004\u0011\u0006\rDA\u0003&\u0002Z\u0005\u0005\t\u0011!B\u0001\u0019\u00069QO\\1qa2LH\u0003BA5\u0003s\u0002RaIA6\u0003_J1!!\u001c%\u0005\u0019y\u0005\u000f^5p]B!\u0001gPA9a\u0011\t\u0019(a\u001e\u0011\t\r+\u0015Q\u000f\t\u0004\u0011\u0006]D!\u0003&\u0015\u0003\u0003\u0005\tQ!\u0001M\u0011!\tY\bFA\u0001\u0002\u0004)\u0016a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011\u0011\u0011\t\u0004]\u0006\r\u0015bAAC_\n1qJ\u00196fGR\u0004"
)
public class StartAllReceivers implements ReceiverTrackerLocalMessage, Product, Serializable {
   private final Seq receiver;

   public static Option unapply(final StartAllReceivers x$0) {
      return StartAllReceivers$.MODULE$.unapply(x$0);
   }

   public static StartAllReceivers apply(final Seq receiver) {
      return StartAllReceivers$.MODULE$.apply(receiver);
   }

   public static Function1 andThen(final Function1 g) {
      return StartAllReceivers$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return StartAllReceivers$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Seq receiver() {
      return this.receiver;
   }

   public StartAllReceivers copy(final Seq receiver) {
      return new StartAllReceivers(receiver);
   }

   public Seq copy$default$1() {
      return this.receiver();
   }

   public String productPrefix() {
      return "StartAllReceivers";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.receiver();
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
      return x$1 instanceof StartAllReceivers;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "receiver";
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
            if (x$1 instanceof StartAllReceivers) {
               label40: {
                  StartAllReceivers var4 = (StartAllReceivers)x$1;
                  Seq var10000 = this.receiver();
                  Seq var5 = var4.receiver();
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

   public StartAllReceivers(final Seq receiver) {
      this.receiver = receiver;
      Product.$init$(this);
   }
}
