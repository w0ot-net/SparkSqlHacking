package org.apache.spark.scheduler;

import java.io.Serializable;
import org.apache.spark.annotation.DeveloperApi;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rc\u0001\u0002\f\u0018\u0001\u0002B\u0001B\u000f\u0001\u0003\u0016\u0004%\ta\u000f\u0005\t\u007f\u0001\u0011\t\u0012)A\u0005y!)\u0001\t\u0001C\u0001\u0003\"9A\tAA\u0001\n\u0003)\u0005bB$\u0001#\u0003%\t\u0001\u0013\u0005\b'\u0002\t\t\u0011\"\u0011U\u0011\u001di\u0006!!A\u0005\u0002mBqA\u0018\u0001\u0002\u0002\u0013\u0005q\fC\u0004f\u0001\u0005\u0005I\u0011\t4\t\u000f5\u0004\u0011\u0011!C\u0001]\"91\u000fAA\u0001\n\u0003\"\bb\u0002<\u0001\u0003\u0003%\te\u001e\u0005\bq\u0002\t\t\u0011\"\u0011z\u0011\u001dQ\b!!A\u0005Bm<\u0011\"a\u0002\u0018\u0003\u0003E\t!!\u0003\u0007\u0011Y9\u0012\u0011!E\u0001\u0003\u0017Aa\u0001\u0011\t\u0005\u0002\u0005\r\u0002b\u0002=\u0011\u0003\u0003%)%\u001f\u0005\n\u0003K\u0001\u0012\u0011!CA\u0003OA\u0011\"a\u000b\u0011\u0003\u0003%\t)!\f\t\u0013\u0005e\u0002#!A\u0005\n\u0005m\"!G*qCJ\\G*[:uK:,'/\u00168qKJ\u001c\u0018n\u001d;S\t\u0012S!\u0001G\r\u0002\u0013M\u001c\u0007.\u001a3vY\u0016\u0014(B\u0001\u000e\u001c\u0003\u0015\u0019\b/\u0019:l\u0015\taR$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002=\u0005\u0019qN]4\u0004\u0001M)\u0001!I\u0014,]A\u0011!%J\u0007\u0002G)\tA%A\u0003tG\u0006d\u0017-\u0003\u0002'G\t1\u0011I\\=SK\u001a\u0004\"\u0001K\u0015\u000e\u0003]I!AK\f\u0003%M\u0003\u0018M]6MSN$XM\\3s\u000bZ,g\u000e\u001e\t\u0003E1J!!L\u0012\u0003\u000fA\u0013x\u000eZ;diB\u0011qf\u000e\b\u0003aUr!!\r\u001b\u000e\u0003IR!aM\u0010\u0002\rq\u0012xn\u001c;?\u0013\u0005!\u0013B\u0001\u001c$\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001O\u001d\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005Y\u001a\u0013!\u0002:eI&#W#\u0001\u001f\u0011\u0005\tj\u0014B\u0001 $\u0005\rIe\u000e^\u0001\u0007e\u0012$\u0017\n\u001a\u0011\u0002\rqJg.\u001b;?)\t\u00115\t\u0005\u0002)\u0001!)!h\u0001a\u0001y\u0005!1m\u001c9z)\t\u0011e\tC\u0004;\tA\u0005\t\u0019\u0001\u001f\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\t\u0011J\u000b\u0002=\u0015.\n1\n\u0005\u0002M#6\tQJ\u0003\u0002O\u001f\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003!\u000e\n!\"\u00198o_R\fG/[8o\u0013\t\u0011VJA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#A+\u0011\u0005Y[V\"A,\u000b\u0005aK\u0016\u0001\u00027b]\u001eT\u0011AW\u0001\u0005U\u00064\u0018-\u0003\u0002]/\n11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0002aGB\u0011!%Y\u0005\u0003E\u000e\u00121!\u00118z\u0011\u001d!\u0007\"!AA\u0002q\n1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#A4\u0011\u0007!\\\u0007-D\u0001j\u0015\tQ7%\u0001\u0006d_2dWm\u0019;j_:L!\u0001\\5\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0003_J\u0004\"A\t9\n\u0005E\u001c#a\u0002\"p_2,\u0017M\u001c\u0005\bI*\t\t\u00111\u0001a\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0005U+\bb\u00023\f\u0003\u0003\u0005\r\u0001P\u0001\tQ\u0006\u001c\bnQ8eKR\tA(\u0001\u0005u_N#(/\u001b8h)\u0005)\u0016AB3rk\u0006d7\u000f\u0006\u0002py\"9AMDA\u0001\u0002\u0004\u0001\u0007F\u0001\u0001\u007f!\ry\u00181A\u0007\u0003\u0003\u0003Q!\u0001U\r\n\t\u0005\u0015\u0011\u0011\u0001\u0002\r\t\u00164X\r\\8qKJ\f\u0005/[\u0001\u001a'B\f'o\u001b'jgR,g.\u001a:V]B,'o]5tiJ#E\t\u0005\u0002)!M)\u0001#!\u0004\u0002\u001aA1\u0011qBA\u000by\tk!!!\u0005\u000b\u0007\u0005M1%A\u0004sk:$\u0018.\\3\n\t\u0005]\u0011\u0011\u0003\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\f\u0004\u0003BA\u000e\u0003Ci!!!\b\u000b\u0007\u0005}\u0011,\u0001\u0002j_&\u0019\u0001(!\b\u0015\u0005\u0005%\u0011!B1qa2LHc\u0001\"\u0002*!)!h\u0005a\u0001y\u00059QO\\1qa2LH\u0003BA\u0018\u0003k\u0001BAIA\u0019y%\u0019\u00111G\u0012\u0003\r=\u0003H/[8o\u0011!\t9\u0004FA\u0001\u0002\u0004\u0011\u0015a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011Q\b\t\u0004-\u0006}\u0012bAA!/\n1qJ\u00196fGR\u0004"
)
public class SparkListenerUnpersistRDD implements SparkListenerEvent, Product, Serializable {
   private final int rddId;

   public static Option unapply(final SparkListenerUnpersistRDD x$0) {
      return SparkListenerUnpersistRDD$.MODULE$.unapply(x$0);
   }

   public static SparkListenerUnpersistRDD apply(final int rddId) {
      return SparkListenerUnpersistRDD$.MODULE$.apply(rddId);
   }

   public static Function1 andThen(final Function1 g) {
      return SparkListenerUnpersistRDD$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return SparkListenerUnpersistRDD$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean logEvent() {
      return SparkListenerEvent.logEvent$(this);
   }

   public int rddId() {
      return this.rddId;
   }

   public SparkListenerUnpersistRDD copy(final int rddId) {
      return new SparkListenerUnpersistRDD(rddId);
   }

   public int copy$default$1() {
      return this.rddId();
   }

   public String productPrefix() {
      return "SparkListenerUnpersistRDD";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.rddId());
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
      return x$1 instanceof SparkListenerUnpersistRDD;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "rddId";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.rddId());
      return Statics.finalizeHash(var1, 1);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label36: {
            if (x$1 instanceof SparkListenerUnpersistRDD) {
               SparkListenerUnpersistRDD var4 = (SparkListenerUnpersistRDD)x$1;
               if (this.rddId() == var4.rddId() && var4.canEqual(this)) {
                  break label36;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public SparkListenerUnpersistRDD(final int rddId) {
      this.rddId = rddId;
      SparkListenerEvent.$init$(this);
      Product.$init$(this);
   }
}
