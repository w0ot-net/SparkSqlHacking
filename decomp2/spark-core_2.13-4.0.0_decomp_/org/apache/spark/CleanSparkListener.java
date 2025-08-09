package org.apache.spark;

import java.io.Serializable;
import org.apache.spark.scheduler.SparkListener;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005c\u0001\u0002\f\u0018\tzA\u0001\u0002\u000f\u0001\u0003\u0016\u0004%\t!\u000f\u0005\t\u0001\u0002\u0011\t\u0012)A\u0005u!)\u0011\t\u0001C\u0001\u0005\"9Q\tAA\u0001\n\u00031\u0005b\u0002%\u0001#\u0003%\t!\u0013\u0005\b)\u0002\t\t\u0011\"\u0011V\u0011\u001dq\u0006!!A\u0005\u0002}Cqa\u0019\u0001\u0002\u0002\u0013\u0005A\rC\u0004k\u0001\u0005\u0005I\u0011I6\t\u000fI\u0004\u0011\u0011!C\u0001g\"9\u0001\u0010AA\u0001\n\u0003J\bbB>\u0001\u0003\u0003%\t\u0005 \u0005\b{\u0002\t\t\u0011\"\u0011\u007f\u0011!y\b!!A\u0005B\u0005\u0005q!CA\u0003/\u0005\u0005\t\u0012BA\u0004\r!1r#!A\t\n\u0005%\u0001BB!\u0011\t\u0003\t\t\u0003C\u0004~!\u0005\u0005IQ\t@\t\u0013\u0005\r\u0002#!A\u0005\u0002\u0006\u0015\u0002\"CA\u0015!\u0005\u0005I\u0011QA\u0016\u0011%\t9\u0004EA\u0001\n\u0013\tID\u0001\nDY\u0016\fgn\u00159be.d\u0015n\u001d;f]\u0016\u0014(B\u0001\r\u001a\u0003\u0015\u0019\b/\u0019:l\u0015\tQ2$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u00029\u0005\u0019qN]4\u0004\u0001M)\u0001aH\u0013*YA\u0011\u0001eI\u0007\u0002C)\t!%A\u0003tG\u0006d\u0017-\u0003\u0002%C\t1\u0011I\\=SK\u001a\u0004\"AJ\u0014\u000e\u0003]I!\u0001K\f\u0003\u0017\rcW-\u00198vaR\u000b7o\u001b\t\u0003A)J!aK\u0011\u0003\u000fA\u0013x\u000eZ;diB\u0011Q&\u000e\b\u0003]Mr!a\f\u001a\u000e\u0003AR!!M\u000f\u0002\rq\u0012xn\u001c;?\u0013\u0005\u0011\u0013B\u0001\u001b\"\u0003\u001d\u0001\u0018mY6bO\u0016L!AN\u001c\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005Q\n\u0013\u0001\u00037jgR,g.\u001a:\u0016\u0003i\u0002\"a\u000f \u000e\u0003qR!!P\f\u0002\u0013M\u001c\u0007.\u001a3vY\u0016\u0014\u0018BA =\u00055\u0019\u0006/\u0019:l\u0019&\u001cH/\u001a8fe\u0006IA.[:uK:,'\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\r#\u0005C\u0001\u0014\u0001\u0011\u0015A4\u00011\u0001;\u0003\u0011\u0019w\u000e]=\u0015\u0005\r;\u0005b\u0002\u001d\u0005!\u0003\u0005\rAO\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005Q%F\u0001\u001eLW\u0005a\u0005CA'S\u001b\u0005q%BA(Q\u0003%)hn\u00195fG.,GM\u0003\u0002RC\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005Ms%!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006i\u0001O]8ek\u000e$\bK]3gSb,\u0012A\u0016\t\u0003/rk\u0011\u0001\u0017\u0006\u00033j\u000bA\u0001\\1oO*\t1,\u0001\u0003kCZ\f\u0017BA/Y\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5usV\t\u0001\r\u0005\u0002!C&\u0011!-\t\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0003K\"\u0004\"\u0001\t4\n\u0005\u001d\f#aA!os\"9\u0011\u000eCA\u0001\u0002\u0004\u0001\u0017a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/F\u0001m!\ri\u0007/Z\u0007\u0002]*\u0011q.I\u0001\u000bG>dG.Z2uS>t\u0017BA9o\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\u0005Q<\bC\u0001\u0011v\u0013\t1\u0018EA\u0004C_>dW-\u00198\t\u000f%T\u0011\u0011!a\u0001K\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\t1&\u0010C\u0004j\u0017\u0005\u0005\t\u0019\u00011\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012\u0001Y\u0001\ti>\u001cFO]5oOR\ta+\u0001\u0004fcV\fGn\u001d\u000b\u0004i\u0006\r\u0001bB5\u000f\u0003\u0003\u0005\r!Z\u0001\u0013\u00072,\u0017M\\*qCJ\\G*[:uK:,'\u000f\u0005\u0002'!M)\u0001#a\u0003\u0002\u0018A1\u0011QBA\nu\rk!!a\u0004\u000b\u0007\u0005E\u0011%A\u0004sk:$\u0018.\\3\n\t\u0005U\u0011q\u0002\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\f\u0004\u0003BA\r\u0003?i!!a\u0007\u000b\u0007\u0005u!,\u0001\u0002j_&\u0019a'a\u0007\u0015\u0005\u0005\u001d\u0011!B1qa2LHcA\"\u0002(!)\u0001h\u0005a\u0001u\u00059QO\\1qa2LH\u0003BA\u0017\u0003g\u0001B\u0001IA\u0018u%\u0019\u0011\u0011G\u0011\u0003\r=\u0003H/[8o\u0011!\t)\u0004FA\u0001\u0002\u0004\u0019\u0015a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u00111\b\t\u0004/\u0006u\u0012bAA 1\n1qJ\u00196fGR\u0004"
)
public class CleanSparkListener implements CleanupTask, Product, Serializable {
   private final SparkListener listener;

   public static Option unapply(final CleanSparkListener x$0) {
      return CleanSparkListener$.MODULE$.unapply(x$0);
   }

   public static CleanSparkListener apply(final SparkListener listener) {
      return CleanSparkListener$.MODULE$.apply(listener);
   }

   public static Function1 andThen(final Function1 g) {
      return CleanSparkListener$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return CleanSparkListener$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public SparkListener listener() {
      return this.listener;
   }

   public CleanSparkListener copy(final SparkListener listener) {
      return new CleanSparkListener(listener);
   }

   public SparkListener copy$default$1() {
      return this.listener();
   }

   public String productPrefix() {
      return "CleanSparkListener";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.listener();
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
      return x$1 instanceof CleanSparkListener;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "listener";
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
            if (x$1 instanceof CleanSparkListener) {
               label40: {
                  CleanSparkListener var4 = (CleanSparkListener)x$1;
                  SparkListener var10000 = this.listener();
                  SparkListener var5 = var4.listener();
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

   public CleanSparkListener(final SparkListener listener) {
      this.listener = listener;
      Product.$init$(this);
   }
}
