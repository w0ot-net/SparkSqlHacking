package org.apache.spark.streaming.ui;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ud!B\r\u001b\u0001j!\u0003\u0002C\u001e\u0001\u0005+\u0007I\u0011\u0001\u001f\t\u00119\u0003!\u0011#Q\u0001\nuB\u0001b\u0014\u0001\u0003\u0016\u0004%\t\u0001\u0015\u0005\t)\u0002\u0011\t\u0012)A\u0005#\")Q\u000b\u0001C\u0001-\"91\fAA\u0001\n\u0003a\u0006bB0\u0001#\u0003%\t\u0001\u0019\u0005\bW\u0002\t\n\u0011\"\u0001m\u0011\u001dq\u0007!!A\u0005B=Dq\u0001\u001f\u0001\u0002\u0002\u0013\u0005\u0011\u0010C\u0004~\u0001\u0005\u0005I\u0011\u0001@\t\u0013\u0005%\u0001!!A\u0005B\u0005-\u0001\"CA\r\u0001\u0005\u0005I\u0011AA\u000e\u0011%\t)\u0003AA\u0001\n\u0003\n9\u0003C\u0005\u0002,\u0001\t\t\u0011\"\u0011\u0002.!I\u0011q\u0006\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0007\u0005\n\u0003g\u0001\u0011\u0011!C!\u0003k9!\"!\u000f\u001b\u0003\u0003E\tAGA\u001e\r%I\"$!A\t\u0002i\ti\u0004\u0003\u0004V'\u0011\u0005\u0011Q\u000b\u0005\n\u0003_\u0019\u0012\u0011!C#\u0003cA\u0011\"a\u0016\u0014\u0003\u0003%\t)!\u0017\t\u0013\u0005}3#!A\u0005\u0002\u0006\u0005\u0004\"CA:'\u0005\u0005I\u0011BA;\u0005]yU\u000f\u001e9vi>\u0003\u0018\nZ!oIN\u0003\u0018M]6K_\nLEM\u0003\u0002\u001c9\u0005\u0011Q/\u001b\u0006\u0003;y\t\u0011b\u001d;sK\u0006l\u0017N\\4\u000b\u0005}\u0001\u0013!B:qCJ\\'BA\u0011#\u0003\u0019\t\u0007/Y2iK*\t1%A\u0002pe\u001e\u001cB\u0001A\u0013,]A\u0011a%K\u0007\u0002O)\t\u0001&A\u0003tG\u0006d\u0017-\u0003\u0002+O\t1\u0011I\\=SK\u001a\u0004\"A\n\u0017\n\u00055:#a\u0002)s_\u0012,8\r\u001e\t\u0003_ar!\u0001\r\u001c\u000f\u0005E*T\"\u0001\u001a\u000b\u0005M\"\u0014A\u0002\u001fs_>$hh\u0001\u0001\n\u0003!J!aN\u0014\u0002\u000fA\f7m[1hK&\u0011\u0011H\u000f\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003o\u001d\n!b\\;uaV$x\n]%e+\u0005i\u0004C\u0001 L\u001d\ty\u0014J\u0004\u0002A\u0011:\u0011\u0011i\u0012\b\u0003\u0005\u001as!aQ#\u000f\u0005E\"\u0015\"A\u0012\n\u0005\u0005\u0012\u0013BA\u0010!\u0013\tib$\u0003\u0002\u001c9%\u0011!JG\u0001\u001d'R\u0014X-Y7j]\u001eTuN\u0019)s_\u001e\u0014Xm]:MSN$XM\\3s\u0013\taUJ\u0001\u0006PkR\u0004X\u000f^(q\u0013\u0012T!A\u0013\u000e\u0002\u0017=,H\u000f];u\u001fBLE\rI\u0001\u000bgB\f'o\u001b&pE&#W#A)\u0011\u0005y\u0012\u0016BA*N\u0005)\u0019\u0006/\u0019:l\u0015>\u0014\u0017\nZ\u0001\fgB\f'o\u001b&pE&#\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0004/fS\u0006C\u0001-\u0001\u001b\u0005Q\u0002\"B\u001e\u0006\u0001\u0004i\u0004\"B(\u0006\u0001\u0004\t\u0016\u0001B2paf$2aV/_\u0011\u001dYd\u0001%AA\u0002uBqa\u0014\u0004\u0011\u0002\u0003\u0007\u0011+\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003\u0005T#!\u00102,\u0003\r\u0004\"\u0001Z5\u000e\u0003\u0015T!AZ4\u0002\u0013Ut7\r[3dW\u0016$'B\u00015(\u0003)\tgN\\8uCRLwN\\\u0005\u0003U\u0016\u0014\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*\u0012!\u001c\u0016\u0003#\n\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#\u00019\u0011\u0005E4X\"\u0001:\u000b\u0005M$\u0018\u0001\u00027b]\u001eT\u0011!^\u0001\u0005U\u00064\u0018-\u0003\u0002xe\n11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012A\u001f\t\u0003MmL!\u0001`\u0014\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0007}\f)\u0001E\u0002'\u0003\u0003I1!a\u0001(\u0005\r\te.\u001f\u0005\t\u0003\u000fY\u0011\u0011!a\u0001u\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!!\u0004\u0011\u000b\u0005=\u0011QC@\u000e\u0005\u0005E!bAA\nO\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005]\u0011\u0011\u0003\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002\u001e\u0005\r\u0002c\u0001\u0014\u0002 %\u0019\u0011\u0011E\u0014\u0003\u000f\t{w\u000e\\3b]\"A\u0011qA\u0007\u0002\u0002\u0003\u0007q0\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,Gc\u00019\u0002*!A\u0011q\u0001\b\u0002\u0002\u0003\u0007!0\u0001\u0005iCND7i\u001c3f)\u0005Q\u0018\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003A\fa!Z9vC2\u001cH\u0003BA\u000f\u0003oA\u0001\"a\u0002\u0012\u0003\u0003\u0005\ra`\u0001\u0018\u001fV$\b/\u001e;Pa&#\u0017I\u001c3Ta\u0006\u00148NS8c\u0013\u0012\u0004\"\u0001W\n\u0014\u000bM\ty$a\u0013\u0011\u000f\u0005\u0005\u0013qI\u001fR/6\u0011\u00111\t\u0006\u0004\u0003\u000b:\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003\u0013\n\u0019EA\tBEN$(/Y2u\rVt7\r^5p]J\u0002B!!\u0014\u0002T5\u0011\u0011q\n\u0006\u0004\u0003#\"\u0018AA5p\u0013\rI\u0014q\n\u000b\u0003\u0003w\tQ!\u00199qYf$RaVA.\u0003;BQa\u000f\fA\u0002uBQa\u0014\fA\u0002E\u000bq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002d\u0005=\u0004#\u0002\u0014\u0002f\u0005%\u0014bAA4O\t1q\n\u001d;j_:\u0004RAJA6{EK1!!\u001c(\u0005\u0019!V\u000f\u001d7fe!A\u0011\u0011O\f\u0002\u0002\u0003\u0007q+A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a\u001e\u0011\u0007E\fI(C\u0002\u0002|I\u0014aa\u00142kK\u000e$\b"
)
public class OutputOpIdAndSparkJobId implements Product, Serializable {
   private final int outputOpId;
   private final int sparkJobId;

   public static Option unapply(final OutputOpIdAndSparkJobId x$0) {
      return OutputOpIdAndSparkJobId$.MODULE$.unapply(x$0);
   }

   public static OutputOpIdAndSparkJobId apply(final int outputOpId, final int sparkJobId) {
      return OutputOpIdAndSparkJobId$.MODULE$.apply(outputOpId, sparkJobId);
   }

   public static Function1 tupled() {
      return OutputOpIdAndSparkJobId$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return OutputOpIdAndSparkJobId$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int outputOpId() {
      return this.outputOpId;
   }

   public int sparkJobId() {
      return this.sparkJobId;
   }

   public OutputOpIdAndSparkJobId copy(final int outputOpId, final int sparkJobId) {
      return new OutputOpIdAndSparkJobId(outputOpId, sparkJobId);
   }

   public int copy$default$1() {
      return this.outputOpId();
   }

   public int copy$default$2() {
      return this.sparkJobId();
   }

   public String productPrefix() {
      return "OutputOpIdAndSparkJobId";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.outputOpId());
         }
         case 1 -> {
            return BoxesRunTime.boxToInteger(this.sparkJobId());
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
      return x$1 instanceof OutputOpIdAndSparkJobId;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "outputOpId";
         }
         case 1 -> {
            return "sparkJobId";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.outputOpId());
      var1 = Statics.mix(var1, this.sparkJobId());
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label38: {
            if (x$1 instanceof OutputOpIdAndSparkJobId) {
               OutputOpIdAndSparkJobId var4 = (OutputOpIdAndSparkJobId)x$1;
               if (this.outputOpId() == var4.outputOpId() && this.sparkJobId() == var4.sparkJobId() && var4.canEqual(this)) {
                  break label38;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public OutputOpIdAndSparkJobId(final int outputOpId, final int sparkJobId) {
      this.outputOpId = outputOpId;
      this.sparkJobId = sparkJobId;
      Product.$init$(this);
   }
}
