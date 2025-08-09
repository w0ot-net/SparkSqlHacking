package org.apache.spark.ui.flamegraph;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.status.api.v1.ThreadStackTrace;
import scala.Option;
import scala.Product;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.mutable.HashMap;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%e\u0001\u0002\u000f\u001e\u0001\"B\u0001B\u0010\u0001\u0003\u0016\u0004%\ta\u0010\u0005\t\u0011\u0002\u0011\t\u0012)A\u0005\u0001\")\u0011\n\u0001C\u0001\u0015\"9a\n\u0001b\u0001\n\u0013y\u0005B\u0002-\u0001A\u0003%\u0001\u000bC\u0004Z\u0001\u0001\u0007I\u0011\u0002.\t\u000fy\u0003\u0001\u0019!C\u0005?\"1Q\r\u0001Q!\nmCQA\u001a\u0001\u0005\u0002}Bqa\u001a\u0001\u0002\u0002\u0013\u0005\u0001\u000eC\u0004k\u0001E\u0005I\u0011A6\t\u000fY\u0004\u0011\u0011!C!o\"9q\u0010AA\u0001\n\u0003Q\u0006\"CA\u0001\u0001\u0005\u0005I\u0011AA\u0002\u0011%\ti\u0001AA\u0001\n\u0003\ny\u0001C\u0005\u0002\u001a\u0001\t\t\u0011\"\u0001\u0002\u001c!I\u0011Q\u0005\u0001\u0002\u0002\u0013\u0005\u0013q\u0005\u0005\n\u0003W\u0001\u0011\u0011!C!\u0003[A\u0011\"a\f\u0001\u0003\u0003%\t%!\r\t\u0013\u0005M\u0002!!A\u0005B\u0005UraBA\u001d;!\u0005\u00111\b\u0004\u00079uA\t!!\u0010\t\r%3B\u0011AA%\u0011\u001d\tYE\u0006C\u0001\u0003\u001bB\u0011\"a\u0013\u0017\u0003\u0003%\t)!\u001c\t\u0013\u0005Ed#!A\u0005\u0002\u0006M\u0004\"CA@-\u0005\u0005I\u0011BAA\u000591E.Y7fOJ\f\u0007\u000f\u001b(pI\u0016T!AH\u0010\u0002\u0015\u0019d\u0017-\\3he\u0006\u0004\bN\u0003\u0002!C\u0005\u0011Q/\u001b\u0006\u0003E\r\nQa\u001d9be.T!\u0001J\u0013\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u00051\u0013aA8sO\u000e\u00011\u0003\u0002\u0001*_I\u0002\"AK\u0017\u000e\u0003-R\u0011\u0001L\u0001\u0006g\u000e\fG.Y\u0005\u0003]-\u0012a!\u00118z%\u00164\u0007C\u0001\u00161\u0013\t\t4FA\u0004Qe>$Wo\u0019;\u0011\u0005MZdB\u0001\u001b:\u001d\t)\u0004(D\u00017\u0015\t9t%\u0001\u0004=e>|GOP\u0005\u0002Y%\u0011!hK\u0001\ba\u0006\u001c7.Y4f\u0013\taTH\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002;W\u0005!a.Y7f+\u0005\u0001\u0005CA!F\u001d\t\u00115\t\u0005\u00026W%\u0011AiK\u0001\u0007!J,G-\u001a4\n\u0005\u0019;%AB*ue&twM\u0003\u0002EW\u0005)a.Y7fA\u00051A(\u001b8jiz\"\"aS'\u0011\u00051\u0003Q\"A\u000f\t\u000by\u001a\u0001\u0019\u0001!\u0002\u0011\rD\u0017\u000e\u001c3sK:,\u0012\u0001\u0015\t\u0005#Z\u00035*D\u0001S\u0015\t\u0019F+A\u0004nkR\f'\r\\3\u000b\u0005U[\u0013AC2pY2,7\r^5p]&\u0011qK\u0015\u0002\b\u0011\u0006\u001c\b.T1q\u0003%\u0019\u0007.\u001b7ee\u0016t\u0007%A\u0003wC2,X-F\u0001\\!\tQC,\u0003\u0002^W\t\u0019\u0011J\u001c;\u0002\u0013Y\fG.^3`I\u0015\fHC\u00011d!\tQ\u0013-\u0003\u0002cW\t!QK\\5u\u0011\u001d!w!!AA\u0002m\u000b1\u0001\u001f\u00132\u0003\u00191\u0018\r\\;fA\u0005aAo\u001c&t_:\u001cFO]5oO\u0006!1m\u001c9z)\tY\u0015\u000eC\u0004?\u0015A\u0005\t\u0019\u0001!\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\tAN\u000b\u0002A[.\na\u000e\u0005\u0002pi6\t\u0001O\u0003\u0002re\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003g.\n!\"\u00198o_R\fG/[8o\u0013\t)\bOA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#\u0001=\u0011\u0005etX\"\u0001>\u000b\u0005md\u0018\u0001\u00027b]\u001eT\u0011!`\u0001\u0005U\u00064\u0018-\u0003\u0002Gu\u0006a\u0001O]8ek\u000e$\u0018I]5us\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BA\u0003\u0003\u0017\u00012AKA\u0004\u0013\r\tIa\u000b\u0002\u0004\u0003:L\bb\u00023\u000f\u0003\u0003\u0005\raW\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011\u0011\u0003\t\u0007\u0003'\t)\"!\u0002\u000e\u0003QK1!a\u0006U\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005u\u00111\u0005\t\u0004U\u0005}\u0011bAA\u0011W\t9!i\\8mK\u0006t\u0007\u0002\u00033\u0011\u0003\u0003\u0005\r!!\u0002\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0004q\u0006%\u0002b\u00023\u0012\u0003\u0003\u0005\raW\u0001\tQ\u0006\u001c\bnQ8eKR\t1,\u0001\u0005u_N#(/\u001b8h)\u0005A\u0018AB3rk\u0006d7\u000f\u0006\u0003\u0002\u001e\u0005]\u0002\u0002\u00033\u0015\u0003\u0003\u0005\r!!\u0002\u0002\u001d\u0019c\u0017-\\3he\u0006\u0004\bNT8eKB\u0011AJF\n\u0005-%\ny\u0004\u0005\u0003\u0002B\u0005\u001dSBAA\"\u0015\r\t)\u0005`\u0001\u0003S>L1\u0001PA\")\t\tY$A\u0003baBd\u0017\u0010F\u0002L\u0003\u001fBq!!\u0015\u0019\u0001\u0004\t\u0019&\u0001\u0004ti\u0006\u001c7n\u001d\t\u0006U\u0005U\u0013\u0011L\u0005\u0004\u0003/Z#!B!se\u0006L\b\u0003BA.\u0003Sj!!!\u0018\u000b\t\u0005}\u0013\u0011M\u0001\u0003mFRA!a\u0019\u0002f\u0005\u0019\u0011\r]5\u000b\u0007\u0005\u001d\u0014%\u0001\u0004ti\u0006$Xo]\u0005\u0005\u0003W\niF\u0001\tUQJ,\u0017\rZ*uC\u000e\\GK]1dKR\u00191*a\u001c\t\u000byJ\u0002\u0019\u0001!\u0002\u000fUt\u0017\r\u001d9msR!\u0011QOA>!\u0011Q\u0013q\u000f!\n\u0007\u0005e4F\u0001\u0004PaRLwN\u001c\u0005\t\u0003{R\u0012\u0011!a\u0001\u0017\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005\r\u0005cA=\u0002\u0006&\u0019\u0011q\u0011>\u0003\r=\u0013'.Z2u\u0001"
)
public class FlamegraphNode implements Product, Serializable {
   private final String name;
   private final HashMap org$apache$spark$ui$flamegraph$FlamegraphNode$$children;
   private int org$apache$spark$ui$flamegraph$FlamegraphNode$$value;

   public static Option unapply(final FlamegraphNode x$0) {
      return FlamegraphNode$.MODULE$.unapply(x$0);
   }

   public static FlamegraphNode apply(final String name) {
      return FlamegraphNode$.MODULE$.apply(name);
   }

   public static FlamegraphNode apply(final ThreadStackTrace[] stacks) {
      return FlamegraphNode$.MODULE$.apply(stacks);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String name() {
      return this.name;
   }

   public HashMap org$apache$spark$ui$flamegraph$FlamegraphNode$$children() {
      return this.org$apache$spark$ui$flamegraph$FlamegraphNode$$children;
   }

   public int org$apache$spark$ui$flamegraph$FlamegraphNode$$value() {
      return this.org$apache$spark$ui$flamegraph$FlamegraphNode$$value;
   }

   public void org$apache$spark$ui$flamegraph$FlamegraphNode$$value_$eq(final int x$1) {
      this.org$apache$spark$ui$flamegraph$FlamegraphNode$$value = x$1;
   }

   public String toJsonString() {
      String var10000 = this.name();
      return "{\"name\":\"" + var10000 + "\",\"value\":" + this.org$apache$spark$ui$flamegraph$FlamegraphNode$$value() + ",\"children\":[" + ((IterableOnceOps)this.org$apache$spark$ui$flamegraph$FlamegraphNode$$children().map((x$1) -> ((FlamegraphNode)x$1._2()).toJsonString())).mkString(",") + "]}";
   }

   public FlamegraphNode copy(final String name) {
      return new FlamegraphNode(name);
   }

   public String copy$default$1() {
      return this.name();
   }

   public String productPrefix() {
      return "FlamegraphNode";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.name();
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
      return x$1 instanceof FlamegraphNode;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "name";
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
            if (x$1 instanceof FlamegraphNode) {
               label40: {
                  FlamegraphNode var4 = (FlamegraphNode)x$1;
                  String var10000 = this.name();
                  String var5 = var4.name();
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

   public FlamegraphNode(final String name) {
      this.name = name;
      Product.$init$(this);
      this.org$apache$spark$ui$flamegraph$FlamegraphNode$$children = new HashMap();
      this.org$apache$spark$ui$flamegraph$FlamegraphNode$$value = 0;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
