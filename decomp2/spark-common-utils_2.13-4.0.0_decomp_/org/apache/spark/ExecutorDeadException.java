package org.apache.spark;

import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mb!B\u000b\u0017\u0001Za\u0002\u0002\u0003\u001b\u0001\u0005+\u0007I\u0011A\u001b\t\u0011y\u0002!\u0011#Q\u0001\nYBQa\u0010\u0001\u0005\u0002\u0001Cqa\u0011\u0001\u0002\u0002\u0013\u0005A\tC\u0004G\u0001E\u0005I\u0011A$\t\u000fI\u0003\u0011\u0011!C!'\"91\fAA\u0001\n\u0003a\u0006b\u00021\u0001\u0003\u0003%\t!\u0019\u0005\bO\u0002\t\t\u0011\"\u0011i\u0011\u001dy\u0007!!A\u0005\u0002ADq!\u001e\u0001\u0002\u0002\u0013\u0005c\u000fC\u0004y\u0001\u0005\u0005I\u0011I=\t\u000fi\u0004\u0011\u0011!C!w\u001eAQPFA\u0001\u0012\u00031bP\u0002\u0005\u0016-\u0005\u0005\t\u0012\u0001\f\u0000\u0011\u0019yt\u0002\"\u0001\u0002\u0018!I\u0011\u0011D\b\u0002\u0002\u0013\u0015\u00131\u0004\u0005\n\u0003;y\u0011\u0011!CA\u0003?A\u0011\"a\t\u0010\u0003\u0003%\t)!\n\t\u0013\u0005Er\"!A\u0005\n\u0005M\"!F#yK\u000e,Ho\u001c:EK\u0006$W\t_2faRLwN\u001c\u0006\u0003/a\tQa\u001d9be.T!!\u0007\u000e\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005Y\u0012aA8sON!\u0001!H\u0011(!\tqr$D\u0001\u0017\u0013\t\u0001cC\u0001\bTa\u0006\u00148.\u0012=dKB$\u0018n\u001c8\u0011\u0005\t*S\"A\u0012\u000b\u0003\u0011\nQa]2bY\u0006L!AJ\u0012\u0003\u000fA\u0013x\u000eZ;diB\u0011\u0001&\r\b\u0003S=r!A\u000b\u0018\u000e\u0003-R!\u0001L\u0017\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011\u0001J\u0005\u0003a\r\nq\u0001]1dW\u0006<W-\u0003\u00023g\ta1+\u001a:jC2L'0\u00192mK*\u0011\u0001gI\u0001\b[\u0016\u001c8/Y4f+\u00051\u0004CA\u001c<\u001d\tA\u0014\b\u0005\u0002+G%\u0011!hI\u0001\u0007!J,G-\u001a4\n\u0005qj$AB*ue&twM\u0003\u0002;G\u0005AQ.Z:tC\u001e,\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0003\u0003\n\u0003\"A\b\u0001\t\u000bQ\u001a\u0001\u0019\u0001\u001c\u0002\t\r|\u0007/\u001f\u000b\u0003\u0003\u0016Cq\u0001\u000e\u0003\u0011\u0002\u0003\u0007a'\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003!S#AN%,\u0003)\u0003\"a\u0013)\u000e\u00031S!!\u0014(\u0002\u0013Ut7\r[3dW\u0016$'BA($\u0003)\tgN\\8uCRLwN\\\u0005\u0003#2\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\tA\u000b\u0005\u0002V56\taK\u0003\u0002X1\u0006!A.\u00198h\u0015\u0005I\u0016\u0001\u00026bm\u0006L!\u0001\u0010,\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003u\u0003\"A\t0\n\u0005}\u001b#aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HC\u00012f!\t\u00113-\u0003\u0002eG\t\u0019\u0011I\\=\t\u000f\u0019D\u0011\u0011!a\u0001;\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\u0012!\u001b\t\u0004U6\u0014W\"A6\u000b\u00051\u001c\u0013AC2pY2,7\r^5p]&\u0011an\u001b\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0002riB\u0011!E]\u0005\u0003g\u000e\u0012qAQ8pY\u0016\fg\u000eC\u0004g\u0015\u0005\u0005\t\u0019\u00012\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0003)^DqAZ\u0006\u0002\u0002\u0003\u0007Q,\u0001\u0005iCND7i\u001c3f)\u0005i\u0016AB3rk\u0006d7\u000f\u0006\u0002ry\"9a-DA\u0001\u0002\u0004\u0011\u0017!F#yK\u000e,Ho\u001c:EK\u0006$W\t_2faRLwN\u001c\t\u0003==\u0019RaDA\u0001\u0003\u001b\u0001b!a\u0001\u0002\nY\nUBAA\u0003\u0015\r\t9aI\u0001\beVtG/[7f\u0013\u0011\tY!!\u0002\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0017\u0007\u0005\u0003\u0002\u0010\u0005UQBAA\t\u0015\r\t\u0019\u0002W\u0001\u0003S>L1AMA\t)\u0005q\u0018\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003Q\u000bQ!\u00199qYf$2!QA\u0011\u0011\u0015!$\u00031\u00017\u0003\u001d)h.\u00199qYf$B!a\n\u0002.A!!%!\u000b7\u0013\r\tYc\t\u0002\u0007\u001fB$\u0018n\u001c8\t\u0011\u0005=2#!AA\u0002\u0005\u000b1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t)\u0004E\u0002V\u0003oI1!!\u000fW\u0005\u0019y%M[3di\u0002"
)
public class ExecutorDeadException extends SparkException implements Product {
   private final String message;

   public static Option unapply(final ExecutorDeadException x$0) {
      return ExecutorDeadException$.MODULE$.unapply(x$0);
   }

   public static ExecutorDeadException apply(final String message) {
      return ExecutorDeadException$.MODULE$.apply(message);
   }

   public static Function1 andThen(final Function1 g) {
      return ExecutorDeadException$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return ExecutorDeadException$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String message() {
      return this.message;
   }

   public ExecutorDeadException copy(final String message) {
      return new ExecutorDeadException(message);
   }

   public String copy$default$1() {
      return this.message();
   }

   public String productPrefix() {
      return "ExecutorDeadException";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.message();
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
      return x$1 instanceof ExecutorDeadException;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "message";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label47: {
            if (x$1 instanceof ExecutorDeadException) {
               label40: {
                  ExecutorDeadException var4 = (ExecutorDeadException)x$1;
                  String var10000 = this.message();
                  String var5 = var4.message();
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

   public ExecutorDeadException(final String message) {
      super("INTERNAL_ERROR_NETWORK", (Map)scala.Predef..MODULE$.Map().apply(.MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("message"), message)}))), (Throwable)null);
      this.message = message;
      Product.$init$(this);
   }
}
