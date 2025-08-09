package org.apache.spark.scheduler;

import java.io.Serializable;
import org.apache.spark.annotation.DeveloperApi;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005-c\u0001\u0002\f\u0018\u0001\u0002B\u0001B\u000f\u0001\u0003\u0016\u0004%\ta\u000f\u0005\t\u007f\u0001\u0011\t\u0012)A\u0005y!)\u0001\t\u0001C\u0001\u0003\"9A\tAA\u0001\n\u0003)\u0005bB$\u0001#\u0003%\t\u0001\u0013\u0005\b'\u0002\t\t\u0011\"\u0011U\u0011\u001di\u0006!!A\u0005\u0002yCqA\u0019\u0001\u0002\u0002\u0013\u00051\rC\u0004j\u0001\u0005\u0005I\u0011\t6\t\u000fE\u0004\u0011\u0011!C\u0001e\"9q\u000fAA\u0001\n\u0003B\bb\u0002>\u0001\u0003\u0003%\te\u001f\u0005\by\u0002\t\t\u0011\"\u0011~\u0011\u001dq\b!!A\u0005B}<\u0011\"a\u0004\u0018\u0003\u0003E\t!!\u0005\u0007\u0011Y9\u0012\u0011!E\u0001\u0003'Aa\u0001\u0011\t\u0005\u0002\u0005-\u0002b\u0002?\u0011\u0003\u0003%)% \u0005\n\u0003[\u0001\u0012\u0011!CA\u0003_A\u0011\"a\r\u0011\u0003\u0003%\t)!\u000e\t\u0013\u0005\u0005\u0003#!A\u0005\n\u0005\r#AH*qCJ\\G*[:uK:,'\u000fV1tW\u001e+G\u000f^5oOJ+7/\u001e7u\u0015\tA\u0012$A\u0005tG\",G-\u001e7fe*\u0011!dG\u0001\u0006gB\f'o\u001b\u0006\u00039u\ta!\u00199bG\",'\"\u0001\u0010\u0002\u0007=\u0014xm\u0001\u0001\u0014\u000b\u0001\tse\u000b\u0018\u0011\u0005\t*S\"A\u0012\u000b\u0003\u0011\nQa]2bY\u0006L!AJ\u0012\u0003\r\u0005s\u0017PU3g!\tA\u0013&D\u0001\u0018\u0013\tQsC\u0001\nTa\u0006\u00148\u000eT5ti\u0016tWM]#wK:$\bC\u0001\u0012-\u0013\ti3EA\u0004Qe>$Wo\u0019;\u0011\u0005=:dB\u0001\u00196\u001d\t\tD'D\u00013\u0015\t\u0019t$\u0001\u0004=e>|GOP\u0005\u0002I%\u0011agI\u0001\ba\u0006\u001c7.Y4f\u0013\tA\u0014H\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u00027G\u0005AA/Y:l\u0013:4w.F\u0001=!\tAS(\u0003\u0002?/\tAA+Y:l\u0013:4w.A\u0005uCN\\\u0017J\u001c4pA\u00051A(\u001b8jiz\"\"AQ\"\u0011\u0005!\u0002\u0001\"\u0002\u001e\u0004\u0001\u0004a\u0014\u0001B2paf$\"A\u0011$\t\u000fi\"\u0001\u0013!a\u0001y\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#A%+\u0005qR5&A&\u0011\u00051\u000bV\"A'\u000b\u00059{\u0015!C;oG\",7m[3e\u0015\t\u00016%\u0001\u0006b]:|G/\u0019;j_:L!AU'\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002+B\u0011akW\u0007\u0002/*\u0011\u0001,W\u0001\u0005Y\u0006twMC\u0001[\u0003\u0011Q\u0017M^1\n\u0005q;&AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u0001`!\t\u0011\u0003-\u0003\u0002bG\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0011Am\u001a\t\u0003E\u0015L!AZ\u0012\u0003\u0007\u0005s\u0017\u0010C\u0004i\u0011\u0005\u0005\t\u0019A0\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\u0005Y\u0007c\u00017pI6\tQN\u0003\u0002oG\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005Al'\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$\"a\u001d<\u0011\u0005\t\"\u0018BA;$\u0005\u001d\u0011un\u001c7fC:Dq\u0001\u001b\u0006\u0002\u0002\u0003\u0007A-\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,GCA+z\u0011\u001dA7\"!AA\u0002}\u000b\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002?\u0006AAo\\*ue&tw\rF\u0001V\u0003\u0019)\u0017/^1mgR\u00191/!\u0001\t\u000f!t\u0011\u0011!a\u0001I\"\u001a\u0001!!\u0002\u0011\t\u0005\u001d\u00111B\u0007\u0003\u0003\u0013Q!\u0001U\r\n\t\u00055\u0011\u0011\u0002\u0002\r\t\u00164X\r\\8qKJ\f\u0005/[\u0001\u001f'B\f'o\u001b'jgR,g.\u001a:UCN\\w)\u001a;uS:<'+Z:vYR\u0004\"\u0001\u000b\t\u0014\u000bA\t)\"!\t\u0011\r\u0005]\u0011Q\u0004\u001fC\u001b\t\tIBC\u0002\u0002\u001c\r\nqA];oi&lW-\u0003\u0003\u0002 \u0005e!!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8ocA!\u00111EA\u0015\u001b\t\t)CC\u0002\u0002(e\u000b!![8\n\u0007a\n)\u0003\u0006\u0002\u0002\u0012\u0005)\u0011\r\u001d9msR\u0019!)!\r\t\u000bi\u001a\u0002\u0019\u0001\u001f\u0002\u000fUt\u0017\r\u001d9msR!\u0011qGA\u001f!\u0011\u0011\u0013\u0011\b\u001f\n\u0007\u0005m2E\u0001\u0004PaRLwN\u001c\u0005\t\u0003\u007f!\u0012\u0011!a\u0001\u0005\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005\u0015\u0003c\u0001,\u0002H%\u0019\u0011\u0011J,\u0003\r=\u0013'.Z2u\u0001"
)
public class SparkListenerTaskGettingResult implements SparkListenerEvent, Product, Serializable {
   private final TaskInfo taskInfo;

   public static Option unapply(final SparkListenerTaskGettingResult x$0) {
      return SparkListenerTaskGettingResult$.MODULE$.unapply(x$0);
   }

   public static SparkListenerTaskGettingResult apply(final TaskInfo taskInfo) {
      return SparkListenerTaskGettingResult$.MODULE$.apply(taskInfo);
   }

   public static Function1 andThen(final Function1 g) {
      return SparkListenerTaskGettingResult$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return SparkListenerTaskGettingResult$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean logEvent() {
      return SparkListenerEvent.logEvent$(this);
   }

   public TaskInfo taskInfo() {
      return this.taskInfo;
   }

   public SparkListenerTaskGettingResult copy(final TaskInfo taskInfo) {
      return new SparkListenerTaskGettingResult(taskInfo);
   }

   public TaskInfo copy$default$1() {
      return this.taskInfo();
   }

   public String productPrefix() {
      return "SparkListenerTaskGettingResult";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.taskInfo();
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
      return x$1 instanceof SparkListenerTaskGettingResult;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "taskInfo";
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
            if (x$1 instanceof SparkListenerTaskGettingResult) {
               label40: {
                  SparkListenerTaskGettingResult var4 = (SparkListenerTaskGettingResult)x$1;
                  TaskInfo var10000 = this.taskInfo();
                  TaskInfo var5 = var4.taskInfo();
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

   public SparkListenerTaskGettingResult(final TaskInfo taskInfo) {
      this.taskInfo = taskInfo;
      SparkListenerEvent.$init$(this);
      Product.$init$(this);
   }
}
