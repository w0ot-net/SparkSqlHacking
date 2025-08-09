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
   bytes = "\u0006\u0005\u0005-c\u0001\u0002\f\u0018\u0001\u0002B\u0001B\u000f\u0001\u0003\u0016\u0004%\ta\u000f\u0005\t\u007f\u0001\u0011\t\u0012)A\u0005y!)\u0001\t\u0001C\u0001\u0003\"9A\tAA\u0001\n\u0003)\u0005bB$\u0001#\u0003%\t\u0001\u0013\u0005\b'\u0002\t\t\u0011\"\u0011U\u0011\u001di\u0006!!A\u0005\u0002yCqA\u0019\u0001\u0002\u0002\u0013\u00051\rC\u0004j\u0001\u0005\u0005I\u0011\t6\t\u000fE\u0004\u0011\u0011!C\u0001e\"9q\u000fAA\u0001\n\u0003B\bb\u0002>\u0001\u0003\u0003%\te\u001f\u0005\by\u0002\t\t\u0011\"\u0011~\u0011\u001dq\b!!A\u0005B}<\u0011\"a\u0004\u0018\u0003\u0003E\t!!\u0005\u0007\u0011Y9\u0012\u0011!E\u0001\u0003'Aa\u0001\u0011\t\u0005\u0002\u0005-\u0002b\u0002?\u0011\u0003\u0003%)% \u0005\n\u0003[\u0001\u0012\u0011!CA\u0003_A\u0011\"a\r\u0011\u0003\u0003%\t)!\u000e\t\u0013\u0005\u0005\u0003#!A\u0005\n\u0005\r#aG*qCJ\\G*[:uK:,'o\u0015;bO\u0016\u001cu.\u001c9mKR,GM\u0003\u0002\u00193\u0005I1o\u00195fIVdWM\u001d\u0006\u00035m\tQa\u001d9be.T!\u0001H\u000f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005q\u0012aA8sO\u000e\u00011#\u0002\u0001\"O-r\u0003C\u0001\u0012&\u001b\u0005\u0019#\"\u0001\u0013\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0019\u001a#AB!osJ+g\r\u0005\u0002)S5\tq#\u0003\u0002+/\t\u00112\u000b]1sW2K7\u000f^3oKJ,e/\u001a8u!\t\u0011C&\u0003\u0002.G\t9\u0001K]8ek\u000e$\bCA\u00188\u001d\t\u0001TG\u0004\u00022i5\t!G\u0003\u00024?\u00051AH]8pizJ\u0011\u0001J\u0005\u0003m\r\nq\u0001]1dW\u0006<W-\u0003\u00029s\ta1+\u001a:jC2L'0\u00192mK*\u0011agI\u0001\ngR\fw-Z%oM>,\u0012\u0001\u0010\t\u0003QuJ!AP\f\u0003\u0013M#\u0018mZ3J]\u001a|\u0017AC:uC\u001e,\u0017J\u001c4pA\u00051A(\u001b8jiz\"\"AQ\"\u0011\u0005!\u0002\u0001\"\u0002\u001e\u0004\u0001\u0004a\u0014\u0001B2paf$\"A\u0011$\t\u000fi\"\u0001\u0013!a\u0001y\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#A%+\u0005qR5&A&\u0011\u00051\u000bV\"A'\u000b\u00059{\u0015!C;oG\",7m[3e\u0015\t\u00016%\u0001\u0006b]:|G/\u0019;j_:L!AU'\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002+B\u0011akW\u0007\u0002/*\u0011\u0001,W\u0001\u0005Y\u0006twMC\u0001[\u0003\u0011Q\u0017M^1\n\u0005q;&AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u0001`!\t\u0011\u0003-\u0003\u0002bG\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0011Am\u001a\t\u0003E\u0015L!AZ\u0012\u0003\u0007\u0005s\u0017\u0010C\u0004i\u0011\u0005\u0005\t\u0019A0\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\u0005Y\u0007c\u00017pI6\tQN\u0003\u0002oG\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005Al'\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$\"a\u001d<\u0011\u0005\t\"\u0018BA;$\u0005\u001d\u0011un\u001c7fC:Dq\u0001\u001b\u0006\u0002\u0002\u0003\u0007A-\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,GCA+z\u0011\u001dA7\"!AA\u0002}\u000b\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002?\u0006AAo\\*ue&tw\rF\u0001V\u0003\u0019)\u0017/^1mgR\u00191/!\u0001\t\u000f!t\u0011\u0011!a\u0001I\"\u001a\u0001!!\u0002\u0011\t\u0005\u001d\u00111B\u0007\u0003\u0003\u0013Q!\u0001U\r\n\t\u00055\u0011\u0011\u0002\u0002\r\t\u00164X\r\\8qKJ\f\u0005/[\u0001\u001c'B\f'o\u001b'jgR,g.\u001a:Ti\u0006<WmQ8na2,G/\u001a3\u0011\u0005!\u00022#\u0002\t\u0002\u0016\u0005\u0005\u0002CBA\f\u0003;a$)\u0004\u0002\u0002\u001a)\u0019\u00111D\u0012\u0002\u000fI,h\u000e^5nK&!\u0011qDA\r\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|g.\r\t\u0005\u0003G\tI#\u0004\u0002\u0002&)\u0019\u0011qE-\u0002\u0005%|\u0017b\u0001\u001d\u0002&Q\u0011\u0011\u0011C\u0001\u0006CB\u0004H.\u001f\u000b\u0004\u0005\u0006E\u0002\"\u0002\u001e\u0014\u0001\u0004a\u0014aB;oCB\u0004H.\u001f\u000b\u0005\u0003o\ti\u0004\u0005\u0003#\u0003sa\u0014bAA\u001eG\t1q\n\u001d;j_:D\u0001\"a\u0010\u0015\u0003\u0003\u0005\rAQ\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA#!\r1\u0016qI\u0005\u0004\u0003\u0013:&AB(cU\u0016\u001cG\u000f"
)
public class SparkListenerStageCompleted implements SparkListenerEvent, Product, Serializable {
   private final StageInfo stageInfo;

   public static Option unapply(final SparkListenerStageCompleted x$0) {
      return SparkListenerStageCompleted$.MODULE$.unapply(x$0);
   }

   public static SparkListenerStageCompleted apply(final StageInfo stageInfo) {
      return SparkListenerStageCompleted$.MODULE$.apply(stageInfo);
   }

   public static Function1 andThen(final Function1 g) {
      return SparkListenerStageCompleted$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return SparkListenerStageCompleted$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean logEvent() {
      return SparkListenerEvent.logEvent$(this);
   }

   public StageInfo stageInfo() {
      return this.stageInfo;
   }

   public SparkListenerStageCompleted copy(final StageInfo stageInfo) {
      return new SparkListenerStageCompleted(stageInfo);
   }

   public StageInfo copy$default$1() {
      return this.stageInfo();
   }

   public String productPrefix() {
      return "SparkListenerStageCompleted";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.stageInfo();
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
      return x$1 instanceof SparkListenerStageCompleted;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "stageInfo";
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
            if (x$1 instanceof SparkListenerStageCompleted) {
               label40: {
                  SparkListenerStageCompleted var4 = (SparkListenerStageCompleted)x$1;
                  StageInfo var10000 = this.stageInfo();
                  StageInfo var5 = var4.stageInfo();
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

   public SparkListenerStageCompleted(final StageInfo stageInfo) {
      this.stageInfo = stageInfo;
      SparkListenerEvent.$init$(this);
      Product.$init$(this);
   }
}
