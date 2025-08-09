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
   bytes = "\u0006\u0005\u0005%d!B\u000e\u001d\u0001z!\u0003\u0002C\u001e\u0001\u0005+\u0007I\u0011\u0001\u001f\t\u0011\u0015\u0003!\u0011#Q\u0001\nuB\u0001B\u0012\u0001\u0003\u0016\u0004%\ta\u0012\u0005\t\u0017\u0002\u0011\t\u0012)A\u0005\u0011\")A\n\u0001C\u0001\u001b\"9!\u000bAA\u0001\n\u0003\u0019\u0006b\u0002,\u0001#\u0003%\ta\u0016\u0005\bE\u0002\t\n\u0011\"\u0001d\u0011\u001d)\u0007!!A\u0005B\u0019DqA\u001c\u0001\u0002\u0002\u0013\u0005q\u000eC\u0004t\u0001\u0005\u0005I\u0011\u0001;\t\u000fi\u0004\u0011\u0011!C!w\"I\u0011Q\u0001\u0001\u0002\u0002\u0013\u0005\u0011q\u0001\u0005\n\u0003#\u0001\u0011\u0011!C!\u0003'A\u0011\"a\u0006\u0001\u0003\u0003%\t%!\u0007\t\u0013\u0005m\u0001!!A\u0005B\u0005u\u0001\"CA\u0010\u0001\u0005\u0005I\u0011IA\u0011\u000f)\t)\u0003HA\u0001\u0012\u0003q\u0012q\u0005\u0004\n7q\t\t\u0011#\u0001\u001f\u0003SAa\u0001T\n\u0005\u0002\u0005\u0005\u0003\"CA\u000e'\u0005\u0005IQIA\u000f\u0011%\t\u0019eEA\u0001\n\u0003\u000b)\u0005\u0003\u0005\u0002LM\t\n\u0011\"\u0001d\u0011%\tieEA\u0001\n\u0003\u000by\u0005\u0003\u0005\u0002^M\t\n\u0011\"\u0001d\u0011%\tyfEA\u0001\n\u0013\t\tG\u0001\rFq\u0016\u001cW\u000f^8s\t\u0016\u001cw.\\7jgNLwN\\%oM>T!!\b\u0010\u0002\u0013M\u001c\u0007.\u001a3vY\u0016\u0014(BA\u0010!\u0003\u0015\u0019\b/\u0019:l\u0015\t\t#%\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002G\u0005\u0019qN]4\u0014\t\u0001)3F\f\t\u0003M%j\u0011a\n\u0006\u0002Q\u0005)1oY1mC&\u0011!f\n\u0002\u0007\u0003:L(+\u001a4\u0011\u0005\u0019b\u0013BA\u0017(\u0005\u001d\u0001&o\u001c3vGR\u0004\"a\f\u001d\u000f\u0005A2dBA\u00196\u001b\u0005\u0011$BA\u001a5\u0003\u0019a$o\\8u}\r\u0001\u0011\"\u0001\u0015\n\u0005]:\u0013a\u00029bG.\fw-Z\u0005\u0003si\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!aN\u0014\u0002\u000f5,7o]1hKV\tQ\b\u0005\u0002?\u0005:\u0011q\b\u0011\t\u0003c\u001dJ!!Q\u0014\u0002\rA\u0013X\rZ3g\u0013\t\u0019EI\u0001\u0004TiJLgn\u001a\u0006\u0003\u0003\u001e\n\u0001\"\\3tg\u0006<W\rI\u0001\u000bo>\u00148.\u001a:I_N$X#\u0001%\u0011\u0007\u0019JU(\u0003\u0002KO\t1q\n\u001d;j_:\f1b^8sW\u0016\u0014\bj\\:uA\u00051A(\u001b8jiz\"2A\u0014)R!\ty\u0005!D\u0001\u001d\u0011\u0015YT\u00011\u0001>\u0011\u001d1U\u0001%AA\u0002!\u000bAaY8qsR\u0019a\nV+\t\u000fm2\u0001\u0013!a\u0001{!9aI\u0002I\u0001\u0002\u0004A\u0015AD2paf$C-\u001a4bk2$H%M\u000b\u00021*\u0012Q(W\u0016\u00025B\u00111\fY\u0007\u00029*\u0011QLX\u0001\nk:\u001c\u0007.Z2lK\u0012T!aX\u0014\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002b9\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\tAM\u000b\u0002I3\u0006i\u0001O]8ek\u000e$\bK]3gSb,\u0012a\u001a\t\u0003Q6l\u0011!\u001b\u0006\u0003U.\fA\u0001\\1oO*\tA.\u0001\u0003kCZ\f\u0017BA\"j\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005\u0001\bC\u0001\u0014r\u0013\t\u0011xEA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0002vqB\u0011aE^\u0005\u0003o\u001e\u00121!\u00118z\u0011\u001dI8\"!AA\u0002A\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#\u0001?\u0011\tu\f\t!^\u0007\u0002}*\u0011qpJ\u0001\u000bG>dG.Z2uS>t\u0017bAA\u0002}\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\tI!a\u0004\u0011\u0007\u0019\nY!C\u0002\u0002\u000e\u001d\u0012qAQ8pY\u0016\fg\u000eC\u0004z\u001b\u0005\u0005\t\u0019A;\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0004O\u0006U\u0001bB=\u000f\u0003\u0003\u0005\r\u0001]\u0001\tQ\u0006\u001c\bnQ8eKR\t\u0001/\u0001\u0005u_N#(/\u001b8h)\u00059\u0017AB3rk\u0006d7\u000f\u0006\u0003\u0002\n\u0005\r\u0002bB=\u0012\u0003\u0003\u0005\r!^\u0001\u0019\u000bb,7-\u001e;pe\u0012+7m\\7nSN\u001c\u0018n\u001c8J]\u001a|\u0007CA(\u0014'\u0015\u0019\u00121FA\u001c!\u001d\ti#a\r>\u0011:k!!a\f\u000b\u0007\u0005Er%A\u0004sk:$\u0018.\\3\n\t\u0005U\u0012q\u0006\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u0014\u0004\u0003BA\u001d\u0003\u007fi!!a\u000f\u000b\u0007\u0005u2.\u0001\u0002j_&\u0019\u0011(a\u000f\u0015\u0005\u0005\u001d\u0012!B1qa2LH#\u0002(\u0002H\u0005%\u0003\"B\u001e\u0017\u0001\u0004i\u0004b\u0002$\u0017!\u0003\u0005\r\u0001S\u0001\u0010CB\u0004H.\u001f\u0013eK\u001a\fW\u000f\u001c;%e\u00059QO\\1qa2LH\u0003BA)\u00033\u0002BAJ%\u0002TA)a%!\u0016>\u0011&\u0019\u0011qK\u0014\u0003\rQ+\b\u000f\\33\u0011!\tY\u0006GA\u0001\u0002\u0004q\u0015a\u0001=%a\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uII\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a\u0019\u0011\u0007!\f)'C\u0002\u0002h%\u0014aa\u00142kK\u000e$\b"
)
public class ExecutorDecommissionInfo implements Product, Serializable {
   private final String message;
   private final Option workerHost;

   public static Option $lessinit$greater$default$2() {
      return ExecutorDecommissionInfo$.MODULE$.$lessinit$greater$default$2();
   }

   public static Option unapply(final ExecutorDecommissionInfo x$0) {
      return ExecutorDecommissionInfo$.MODULE$.unapply(x$0);
   }

   public static Option apply$default$2() {
      return ExecutorDecommissionInfo$.MODULE$.apply$default$2();
   }

   public static ExecutorDecommissionInfo apply(final String message, final Option workerHost) {
      return ExecutorDecommissionInfo$.MODULE$.apply(message, workerHost);
   }

   public static Function1 tupled() {
      return ExecutorDecommissionInfo$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ExecutorDecommissionInfo$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String message() {
      return this.message;
   }

   public Option workerHost() {
      return this.workerHost;
   }

   public ExecutorDecommissionInfo copy(final String message, final Option workerHost) {
      return new ExecutorDecommissionInfo(message, workerHost);
   }

   public String copy$default$1() {
      return this.message();
   }

   public Option copy$default$2() {
      return this.workerHost();
   }

   public String productPrefix() {
      return "ExecutorDecommissionInfo";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.message();
         }
         case 1 -> {
            return this.workerHost();
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
      return x$1 instanceof ExecutorDecommissionInfo;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "message";
         }
         case 1 -> {
            return "workerHost";
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
      boolean var8;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof ExecutorDecommissionInfo) {
               label48: {
                  ExecutorDecommissionInfo var4 = (ExecutorDecommissionInfo)x$1;
                  String var10000 = this.message();
                  String var5 = var4.message();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label48;
                  }

                  Option var7 = this.workerHost();
                  Option var6 = var4.workerHost();
                  if (var7 == null) {
                     if (var6 != null) {
                        break label48;
                     }
                  } else if (!var7.equals(var6)) {
                     break label48;
                  }

                  if (var4.canEqual(this)) {
                     break label55;
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

   public ExecutorDecommissionInfo(final String message, final Option workerHost) {
      this.message = message;
      this.workerHost = workerHost;
      Product.$init$(this);
   }
}
