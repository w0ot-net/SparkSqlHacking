package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}b!\u0002\f\u0018\u0001^y\u0002\u0002\u0003\u001e\u0001\u0005+\u0007I\u0011A\u001e\t\u0011}\u0002!\u0011#Q\u0001\nqBQ\u0001\u0011\u0001\u0005\u0002\u0005Cq\u0001\u0012\u0001\u0002\u0002\u0013\u0005Q\tC\u0004H\u0001E\u0005I\u0011\u0001%\t\u000fM\u0003\u0011\u0011!C!)\"9Q\fAA\u0001\n\u0003q\u0006b\u00022\u0001\u0003\u0003%\ta\u0019\u0005\bS\u0002\t\t\u0011\"\u0011k\u0011\u001d\t\b!!A\u0005\u0002IDqa\u001e\u0001\u0002\u0002\u0013\u0005\u0003\u0010C\u0004{\u0001\u0005\u0005I\u0011I>\t\u000fq\u0004\u0011\u0011!C!{\"9a\u0010AA\u0001\n\u0003zxACA\u0002/\u0005\u0005\t\u0012A\f\u0002\u0006\u0019IacFA\u0001\u0012\u00039\u0012q\u0001\u0005\u0007\u0001B!\t!a\b\t\u000fq\u0004\u0012\u0011!C#{\"I\u0011\u0011\u0005\t\u0002\u0002\u0013\u0005\u00151\u0005\u0005\n\u0003O\u0001\u0012\u0011!CA\u0003SA\u0011\"!\u000e\u0011\u0003\u0003%I!a\u000e\u0003%\u001d+G\u000f^5oOJ+7/\u001e7u\u000bZ,g\u000e\u001e\u0006\u00031e\t\u0011b]2iK\u0012,H.\u001a:\u000b\u0005iY\u0012!B:qCJ\\'B\u0001\u000f\u001e\u0003\u0019\t\u0007/Y2iK*\ta$A\u0002pe\u001e\u001cR\u0001\u0001\u0011'U5\u0002\"!\t\u0013\u000e\u0003\tR\u0011aI\u0001\u0006g\u000e\fG.Y\u0005\u0003K\t\u0012a!\u00118z%\u00164\u0007CA\u0014)\u001b\u00059\u0012BA\u0015\u0018\u0005E!\u0015iR*dQ\u0016$W\u000f\\3s\u000bZ,g\u000e\u001e\t\u0003C-J!\u0001\f\u0012\u0003\u000fA\u0013x\u000eZ;diB\u0011af\u000e\b\u0003_Ur!\u0001\r\u001b\u000e\u0003ER!AM\u001a\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011aI\u0005\u0003m\t\nq\u0001]1dW\u0006<W-\u0003\u00029s\ta1+\u001a:jC2L'0\u00192mK*\u0011aGI\u0001\ti\u0006\u001c8.\u00138g_V\tA\b\u0005\u0002({%\u0011ah\u0006\u0002\t)\u0006\u001c8.\u00138g_\u0006IA/Y:l\u0013:4w\u000eI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\t\u001b\u0005CA\u0014\u0001\u0011\u0015Q4\u00011\u0001=\u0003\u0011\u0019w\u000e]=\u0015\u0005\t3\u0005b\u0002\u001e\u0005!\u0003\u0005\r\u0001P\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005I%F\u0001\u001fKW\u0005Y\u0005C\u0001'R\u001b\u0005i%B\u0001(P\u0003%)hn\u00195fG.,GM\u0003\u0002QE\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005Ik%!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006i\u0001O]8ek\u000e$\bK]3gSb,\u0012!\u0016\t\u0003-nk\u0011a\u0016\u0006\u00031f\u000bA\u0001\\1oO*\t!,\u0001\u0003kCZ\f\u0017B\u0001/X\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5usV\tq\f\u0005\u0002\"A&\u0011\u0011M\t\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0003I\u001e\u0004\"!I3\n\u0005\u0019\u0014#aA!os\"9\u0001\u000eCA\u0001\u0002\u0004y\u0016a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/F\u0001l!\raw\u000eZ\u0007\u0002[*\u0011aNI\u0001\u000bG>dG.Z2uS>t\u0017B\u00019n\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\u0005M4\bCA\u0011u\u0013\t)(EA\u0004C_>dW-\u00198\t\u000f!T\u0011\u0011!a\u0001I\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\t)\u0016\u0010C\u0004i\u0017\u0005\u0005\t\u0019A0\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012aX\u0001\ti>\u001cFO]5oOR\tQ+\u0001\u0004fcV\fGn\u001d\u000b\u0004g\u0006\u0005\u0001b\u00025\u000f\u0003\u0003\u0005\r\u0001Z\u0001\u0013\u000f\u0016$H/\u001b8h%\u0016\u001cX\u000f\u001c;Fm\u0016tG\u000f\u0005\u0002(!M)\u0001#!\u0003\u0002\u0016A1\u00111BA\ty\tk!!!\u0004\u000b\u0007\u0005=!%A\u0004sk:$\u0018.\\3\n\t\u0005M\u0011Q\u0002\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\f\u0004\u0003BA\f\u0003;i!!!\u0007\u000b\u0007\u0005m\u0011,\u0001\u0002j_&\u0019\u0001(!\u0007\u0015\u0005\u0005\u0015\u0011!B1qa2LHc\u0001\"\u0002&!)!h\u0005a\u0001y\u00059QO\\1qa2LH\u0003BA\u0016\u0003c\u0001B!IA\u0017y%\u0019\u0011q\u0006\u0012\u0003\r=\u0003H/[8o\u0011!\t\u0019\u0004FA\u0001\u0002\u0004\u0011\u0015a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011\u0011\b\t\u0004-\u0006m\u0012bAA\u001f/\n1qJ\u00196fGR\u0004"
)
public class GettingResultEvent implements DAGSchedulerEvent, Product, Serializable {
   private final TaskInfo taskInfo;

   public static Option unapply(final GettingResultEvent x$0) {
      return GettingResultEvent$.MODULE$.unapply(x$0);
   }

   public static GettingResultEvent apply(final TaskInfo taskInfo) {
      return GettingResultEvent$.MODULE$.apply(taskInfo);
   }

   public static Function1 andThen(final Function1 g) {
      return GettingResultEvent$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return GettingResultEvent$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public TaskInfo taskInfo() {
      return this.taskInfo;
   }

   public GettingResultEvent copy(final TaskInfo taskInfo) {
      return new GettingResultEvent(taskInfo);
   }

   public TaskInfo copy$default$1() {
      return this.taskInfo();
   }

   public String productPrefix() {
      return "GettingResultEvent";
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
      return x$1 instanceof GettingResultEvent;
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
            if (x$1 instanceof GettingResultEvent) {
               label40: {
                  GettingResultEvent var4 = (GettingResultEvent)x$1;
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

   public GettingResultEvent(final TaskInfo taskInfo) {
      this.taskInfo = taskInfo;
      Product.$init$(this);
   }
}
