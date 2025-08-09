package org.apache.spark.sql.streaming;

import java.io.Serializable;
import java.time.Duration;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mb\u0001\u0002\f\u0018\u0001\nB\u0001\u0002\u000f\u0001\u0003\u0016\u0004%\t!\u000f\u0005\t\u0005\u0002\u0011\t\u0012)A\u0005u!)1\t\u0001C\u0001\t\"9\u0001\nAA\u0001\n\u0003I\u0005bB&\u0001#\u0003%\t\u0001\u0014\u0005\b/\u0002\t\t\u0011\"\u0011Y\u0011\u001dy\u0006!!A\u0005\u0002\u0001Dq\u0001\u001a\u0001\u0002\u0002\u0013\u0005Q\rC\u0004l\u0001\u0005\u0005I\u0011\t7\t\u000fM\u0004\u0011\u0011!C\u0001i\"9\u0011\u0010AA\u0001\n\u0003R\bb\u0002?\u0001\u0003\u0003%\t% \u0005\b}\u0002\t\t\u0011\"\u0011\u0000\u0011%\t\t\u0001AA\u0001\n\u0003\n\u0019aB\u0004\u0002\b]A\t!!\u0003\u0007\rY9\u0002\u0012AA\u0006\u0011\u0019\u0019\u0005\u0003\"\u0001\u0002\u0018!9\u0011\u0011\u0004\t\u0005\u0002\u0005m\u0001\"CA\u000f!\u0005\u0005I\u0011QA\u0010\u0011%\t\u0019\u0003EA\u0001\n\u0003\u000b)\u0003C\u0005\u00022A\t\t\u0011\"\u0003\u00024\tIA\u000b\u0016'D_:4\u0017n\u001a\u0006\u00031e\t\u0011b\u001d;sK\u0006l\u0017N\\4\u000b\u0005iY\u0012aA:rY*\u0011A$H\u0001\u0006gB\f'o\u001b\u0006\u0003=}\ta!\u00199bG\",'\"\u0001\u0011\u0002\u0007=\u0014xm\u0001\u0001\u0014\t\u0001\u0019\u0013\u0006\f\t\u0003I\u001dj\u0011!\n\u0006\u0002M\u0005)1oY1mC&\u0011\u0001&\n\u0002\u0007\u0003:L(+\u001a4\u0011\u0005\u0011R\u0013BA\u0016&\u0005\u001d\u0001&o\u001c3vGR\u0004\"!L\u001b\u000f\u00059\u001adBA\u00183\u001b\u0005\u0001$BA\u0019\"\u0003\u0019a$o\\8u}%\ta%\u0003\u00025K\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u001c8\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t!T%A\u0006ui2$UO]1uS>tW#\u0001\u001e\u0011\u0005m\u0002U\"\u0001\u001f\u000b\u0005ur\u0014\u0001\u0002;j[\u0016T\u0011aP\u0001\u0005U\u00064\u0018-\u0003\u0002By\tAA)\u001e:bi&|g.\u0001\u0007ui2$UO]1uS>t\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0003\u000b\u001e\u0003\"A\u0012\u0001\u000e\u0003]AQ\u0001O\u0002A\u0002i\nAaY8qsR\u0011QI\u0013\u0005\bq\u0011\u0001\n\u00111\u0001;\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012!\u0014\u0016\u0003u9[\u0013a\u0014\t\u0003!Vk\u0011!\u0015\u0006\u0003%N\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005Q+\u0013AC1o]>$\u0018\r^5p]&\u0011a+\u0015\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001Z!\tQV,D\u0001\\\u0015\taf(\u0001\u0003mC:<\u0017B\u00010\\\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5usV\t\u0011\r\u0005\u0002%E&\u00111-\n\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0003M&\u0004\"\u0001J4\n\u0005!,#aA!os\"9!\u000eCA\u0001\u0002\u0004\t\u0017a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/F\u0001n!\rq\u0017OZ\u0007\u0002_*\u0011\u0001/J\u0001\u000bG>dG.Z2uS>t\u0017B\u0001:p\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\u0005UD\bC\u0001\u0013w\u0013\t9XEA\u0004C_>dW-\u00198\t\u000f)T\u0011\u0011!a\u0001M\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\tI6\u0010C\u0004k\u0017\u0005\u0005\t\u0019A1\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012!Y\u0001\ti>\u001cFO]5oOR\t\u0011,\u0001\u0004fcV\fGn\u001d\u000b\u0004k\u0006\u0015\u0001b\u00026\u000f\u0003\u0003\u0005\rAZ\u0001\n)Rc5i\u001c8gS\u001e\u0004\"A\u0012\t\u0014\tA\u0019\u0013Q\u0002\t\u0005\u0003\u001f\t)\"\u0004\u0002\u0002\u0012)\u0019\u00111\u0003 \u0002\u0005%|\u0017b\u0001\u001c\u0002\u0012Q\u0011\u0011\u0011B\u0001\u0005\u001d>sU)F\u0001F\u0003\u0015\t\u0007\u000f\u001d7z)\r)\u0015\u0011\u0005\u0005\u0006qM\u0001\rAO\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\t9#!\f\u0011\t\u0011\nICO\u0005\u0004\u0003W)#AB(qi&|g\u000e\u0003\u0005\u00020Q\t\t\u00111\u0001F\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003k\u00012AWA\u001c\u0013\r\tId\u0017\u0002\u0007\u001f\nTWm\u0019;"
)
public class TTLConfig implements Product, Serializable {
   private final Duration ttlDuration;

   public static Option unapply(final TTLConfig x$0) {
      return TTLConfig$.MODULE$.unapply(x$0);
   }

   public static TTLConfig apply(final Duration ttlDuration) {
      return TTLConfig$.MODULE$.apply(ttlDuration);
   }

   public static TTLConfig NONE() {
      return TTLConfig$.MODULE$.NONE();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Duration ttlDuration() {
      return this.ttlDuration;
   }

   public TTLConfig copy(final Duration ttlDuration) {
      return new TTLConfig(ttlDuration);
   }

   public Duration copy$default$1() {
      return this.ttlDuration();
   }

   public String productPrefix() {
      return "TTLConfig";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.ttlDuration();
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
      return x$1 instanceof TTLConfig;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "ttlDuration";
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
            if (x$1 instanceof TTLConfig) {
               label40: {
                  TTLConfig var4 = (TTLConfig)x$1;
                  Duration var10000 = this.ttlDuration();
                  Duration var5 = var4.ttlDuration();
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

   public TTLConfig(final Duration ttlDuration) {
      this.ttlDuration = ttlDuration;
      Product.$init$(this);
   }
}
