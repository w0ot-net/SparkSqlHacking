package org.apache.spark.api.python;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Some;
import scala..less.colon.less.;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\re\u0001B\u000f\u001f\u0001&B\u0001b\u0010\u0001\u0003\u0016\u0004%\t\u0001\u0011\u0005\t\u0017\u0002\u0011\t\u0012)A\u0005\u0003\")A\n\u0001C\u0001\u001b\"1\u0011\u000b\u0001Q!\nICa\u0001\u0017\u0001!B\u0013I\u0006\"B/\u0001\t\u0003q\u0006\"B0\u0001\t\u0003\u0001\u0007\"B1\u0001\t\u0013\u0011\u0007\"\u00024\u0001\t\u00039\u0007\"B5\u0001\t\u0003\u0011\u0007b\u00026\u0001\u0003\u0003%\ta\u001b\u0005\b[\u0002\t\n\u0011\"\u0001o\u0011\u001dI\b!!A\u0005BiD\u0011\"a\u0001\u0001\u0003\u0003%\t!!\u0002\t\u0013\u00055\u0001!!A\u0005\u0002\u0005=\u0001\"CA\u000e\u0001\u0005\u0005I\u0011IA\u000f\u0011%\tY\u0003AA\u0001\n\u0003\ti\u0003C\u0005\u00028\u0001\t\t\u0011\"\u0011\u0002:!I\u0011Q\b\u0001\u0002\u0002\u0013\u0005\u0013q\b\u0005\n\u0003\u0003\u0002\u0011\u0011!C!\u0003\u0007B\u0011\"!\u0012\u0001\u0003\u0003%\t%a\u0012\b\u0013\u0005-c$!A\t\u0002\u00055c\u0001C\u000f\u001f\u0003\u0003E\t!a\u0014\t\r1;B\u0011AA4\u0011%\t\teFA\u0001\n\u000b\n\u0019\u0005C\u0005\u0002j]\t\t\u0011\"!\u0002l!I\u0011qN\f\u0002\u0002\u0013\u0005\u0015\u0011\u000f\u0005\n\u0003s:\u0012\u0011!C\u0005\u0003w\u0012A\u0002U=uQ>twk\u001c:lKJT!a\b\u0011\u0002\rALH\u000f[8o\u0015\t\t#%A\u0002ba&T!a\t\u0013\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u00152\u0013AB1qC\u000eDWMC\u0001(\u0003\ry'oZ\u0002\u0001'\u0011\u0001!\u0006M\u001a\u0011\u0005-rS\"\u0001\u0017\u000b\u00035\nQa]2bY\u0006L!a\f\u0017\u0003\r\u0005s\u0017PU3g!\tY\u0013'\u0003\u00023Y\t9\u0001K]8ek\u000e$\bC\u0001\u001b=\u001d\t)$H\u0004\u00027s5\tqG\u0003\u00029Q\u00051AH]8pizJ\u0011!L\u0005\u0003w1\nq\u0001]1dW\u0006<W-\u0003\u0002>}\ta1+\u001a:jC2L'0\u00192mK*\u00111\bL\u0001\bG\"\fgN\\3m+\u0005\t\u0005C\u0001\"J\u001b\u0005\u0019%B\u0001#F\u0003!\u0019\u0007.\u00198oK2\u001c(B\u0001$H\u0003\rq\u0017n\u001c\u0006\u0002\u0011\u0006!!.\u0019<b\u0013\tQ5IA\u0007T_\u000e\\W\r^\"iC:tW\r\\\u0001\tG\"\fgN\\3mA\u00051A(\u001b8jiz\"\"A\u0014)\u0011\u0005=\u0003Q\"\u0001\u0010\t\u000b}\u001a\u0001\u0019A!\u0002\u0017M,G.Z2u_J|\u0005\u000f\u001e\t\u0004WM+\u0016B\u0001+-\u0005\u0019y\u0005\u000f^5p]B\u0011!IV\u0005\u0003/\u000e\u0013\u0001bU3mK\u000e$xN]\u0001\u0010g\u0016dWm\u0019;j_:\\U-_(qiB\u00191f\u0015.\u0011\u0005\t[\u0016B\u0001/D\u00051\u0019V\r\\3di&|gnS3z\u0003!\u0019X\r\\3di>\u0014X#A+\u0002\u0019M,G.Z2uS>t7*Z=\u0016\u0003i\u000bQb\u00197pg\u0016\u001cV\r\\3di>\u0014H#A2\u0011\u0005-\"\u0017BA3-\u0005\u0011)f.\u001b;\u0002\u000fI,gM]3tQR\t\u0001.D\u0001\u0001\u0003\u0011\u0019Ho\u001c9\u0002\t\r|\u0007/\u001f\u000b\u0003\u001d2DqaP\u0006\u0011\u0002\u0003\u0007\u0011)\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003=T#!\u00119,\u0003E\u0004\"A]<\u000e\u0003MT!\u0001^;\u0002\u0013Ut7\r[3dW\u0016$'B\u0001<-\u0003)\tgN\\8uCRLwN\\\u0005\u0003qN\u0014\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\t1\u0010\u0005\u0002}\u007f6\tQP\u0003\u0002\u007f\u000f\u0006!A.\u00198h\u0013\r\t\t! \u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0005\u0005\u001d\u0001cA\u0016\u0002\n%\u0019\u00111\u0002\u0017\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005E\u0011q\u0003\t\u0004W\u0005M\u0011bAA\u000bY\t\u0019\u0011I\\=\t\u0013\u0005eq\"!AA\u0002\u0005\u001d\u0011a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002 A1\u0011\u0011EA\u0014\u0003#i!!a\t\u000b\u0007\u0005\u0015B&\u0001\u0006d_2dWm\u0019;j_:LA!!\u000b\u0002$\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\ty#!\u000e\u0011\u0007-\n\t$C\u0002\u000241\u0012qAQ8pY\u0016\fg\u000eC\u0005\u0002\u001aE\t\t\u00111\u0001\u0002\u0012\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\rY\u00181\b\u0005\n\u00033\u0011\u0012\u0011!a\u0001\u0003\u000f\t\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0003\u000f\t\u0001\u0002^8TiJLgn\u001a\u000b\u0002w\u00061Q-];bYN$B!a\f\u0002J!I\u0011\u0011D\u000b\u0002\u0002\u0003\u0007\u0011\u0011C\u0001\r!f$\bn\u001c8X_J\\WM\u001d\t\u0003\u001f^\u0019RaFA)\u0003;\u0002b!a\u0015\u0002Z\u0005sUBAA+\u0015\r\t9\u0006L\u0001\beVtG/[7f\u0013\u0011\tY&!\u0016\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0017\u0007\u0005\u0003\u0002`\u0005\u0015TBAA1\u0015\r\t\u0019gR\u0001\u0003S>L1!PA1)\t\ti%A\u0003baBd\u0017\u0010F\u0002O\u0003[BQa\u0010\u000eA\u0002\u0005\u000bq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002t\u0005U\u0004cA\u0016T\u0003\"A\u0011qO\u000e\u0002\u0002\u0003\u0007a*A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!! \u0011\u0007q\fy(C\u0002\u0002\u0002v\u0014aa\u00142kK\u000e$\b"
)
public class PythonWorker implements Product, Serializable {
   private final SocketChannel channel;
   private Option selectorOpt;
   private Option selectionKeyOpt;

   public static Option unapply(final PythonWorker x$0) {
      return PythonWorker$.MODULE$.unapply(x$0);
   }

   public static PythonWorker apply(final SocketChannel channel) {
      return PythonWorker$.MODULE$.apply(channel);
   }

   public static Function1 andThen(final Function1 g) {
      return PythonWorker$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return PythonWorker$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public SocketChannel channel() {
      return this.channel;
   }

   public Selector selector() {
      return (Selector)this.selectorOpt.orNull(.MODULE$.refl());
   }

   public SelectionKey selectionKey() {
      return (SelectionKey)this.selectionKeyOpt.orNull(.MODULE$.refl());
   }

   private void closeSelector() {
      this.selectionKeyOpt.foreach((x$1) -> {
         $anonfun$closeSelector$1(x$1);
         return BoxedUnit.UNIT;
      });
      this.selectorOpt.foreach((x$2) -> {
         $anonfun$closeSelector$2(x$2);
         return BoxedUnit.UNIT;
      });
   }

   public synchronized PythonWorker refresh() {
      this.closeSelector();
      if (this.channel().isBlocking()) {
         this.selectorOpt = scala.None..MODULE$;
         this.selectionKeyOpt = scala.None..MODULE$;
      } else {
         Selector selector = Selector.open();
         this.selectorOpt = new Some(selector);
         this.selectionKeyOpt = new Some(this.channel().register(selector, 5));
      }

      return this;
   }

   public synchronized void stop() {
      this.closeSelector();
      scala.Option..MODULE$.apply(this.channel()).foreach((x$3) -> {
         $anonfun$stop$1(x$3);
         return BoxedUnit.UNIT;
      });
   }

   public PythonWorker copy(final SocketChannel channel) {
      return new PythonWorker(channel);
   }

   public SocketChannel copy$default$1() {
      return this.channel();
   }

   public String productPrefix() {
      return "PythonWorker";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.channel();
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof PythonWorker;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "channel";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label47: {
            if (x$1 instanceof PythonWorker) {
               label40: {
                  PythonWorker var4 = (PythonWorker)x$1;
                  SocketChannel var10000 = this.channel();
                  SocketChannel var5 = var4.channel();
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

   // $FF: synthetic method
   public static final void $anonfun$closeSelector$1(final SelectionKey x$1) {
      x$1.cancel();
   }

   // $FF: synthetic method
   public static final void $anonfun$closeSelector$2(final Selector x$2) {
      x$2.close();
   }

   // $FF: synthetic method
   public static final void $anonfun$stop$1(final SocketChannel x$3) {
      x$3.close();
   }

   public PythonWorker(final SocketChannel channel) {
      this.channel = channel;
      Product.$init$(this);
      this.selectorOpt = scala.None..MODULE$;
      this.selectionKeyOpt = scala.None..MODULE$;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
