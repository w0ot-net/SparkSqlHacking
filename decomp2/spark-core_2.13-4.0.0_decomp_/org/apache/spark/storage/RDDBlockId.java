package org.apache.spark.storage;

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
   bytes = "\u0006\u0005\u0005\u001dd\u0001B\r\u001b\u0001\u000eB\u0001B\u000f\u0001\u0003\u0016\u0004%\ta\u000f\u0005\t\u007f\u0001\u0011\t\u0012)A\u0005y!A\u0001\t\u0001BK\u0002\u0013\u00051\b\u0003\u0005B\u0001\tE\t\u0015!\u0003=\u0011\u0015\u0011\u0005\u0001\"\u0001D\u0011\u00159\u0005\u0001\"\u0011I\u0011\u001d\t\u0006!!A\u0005\u0002ICq!\u0016\u0001\u0012\u0002\u0013\u0005a\u000bC\u0004b\u0001E\u0005I\u0011\u0001,\t\u000f\t\u0004\u0011\u0011!C!G\"91\u000eAA\u0001\n\u0003Y\u0004b\u00027\u0001\u0003\u0003%\t!\u001c\u0005\bg\u0002\t\t\u0011\"\u0011u\u0011\u001dY\b!!A\u0005\u0002qD\u0011\"a\u0001\u0001\u0003\u0003%\t%!\u0002\t\u0013\u0005%\u0001!!A\u0005B\u0005-\u0001\"CA\u0007\u0001\u0005\u0005I\u0011IA\b\u000f%\tyBGA\u0001\u0012\u0003\t\tC\u0002\u0005\u001a5\u0005\u0005\t\u0012AA\u0012\u0011\u0019\u00115\u0003\"\u0001\u0002<!I\u0011QH\n\u0002\u0002\u0013\u0015\u0013q\b\u0005\n\u0003\u0003\u001a\u0012\u0011!CA\u0003\u0007B\u0011\"!\u0013\u0014\u0003\u0003%\t)a\u0013\t\u0013\u0005u3#!A\u0005\n\u0005}#A\u0003*E\t\ncwnY6JI*\u00111\u0004H\u0001\bgR|'/Y4f\u0015\tib$A\u0003ta\u0006\u00148N\u0003\u0002 A\u00051\u0011\r]1dQ\u0016T\u0011!I\u0001\u0004_J<7\u0001A\n\u0005\u0001\u0011Bc\u0006\u0005\u0002&M5\t!$\u0003\u0002(5\t9!\t\\8dW&#\u0007CA\u0015-\u001b\u0005Q#\"A\u0016\u0002\u000bM\u001c\u0017\r\\1\n\u00055R#a\u0002)s_\u0012,8\r\u001e\t\u0003_]r!\u0001M\u001b\u000f\u0005E\"T\"\u0001\u001a\u000b\u0005M\u0012\u0013A\u0002\u001fs_>$h(C\u0001,\u0013\t1$&A\u0004qC\u000e\\\u0017mZ3\n\u0005aJ$\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u001c+\u0003\u0015\u0011H\rZ%e+\u0005a\u0004CA\u0015>\u0013\tq$FA\u0002J]R\faA\u001d3e\u0013\u0012\u0004\u0013AC:qY&$\u0018J\u001c3fq\u0006Y1\u000f\u001d7ji&sG-\u001a=!\u0003\u0019a\u0014N\\5u}Q\u0019A)\u0012$\u0011\u0005\u0015\u0002\u0001\"\u0002\u001e\u0006\u0001\u0004a\u0004\"\u0002!\u0006\u0001\u0004a\u0014\u0001\u00028b[\u0016,\u0012!\u0013\t\u0003\u0015:s!a\u0013'\u0011\u0005ER\u0013BA'+\u0003\u0019\u0001&/\u001a3fM&\u0011q\n\u0015\u0002\u0007'R\u0014\u0018N\\4\u000b\u00055S\u0013\u0001B2paf$2\u0001R*U\u0011\u001dQt\u0001%AA\u0002qBq\u0001Q\u0004\u0011\u0002\u0003\u0007A(\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003]S#\u0001\u0010-,\u0003e\u0003\"AW0\u000e\u0003mS!\u0001X/\u0002\u0013Ut7\r[3dW\u0016$'B\u00010+\u0003)\tgN\\8uCRLwN\\\u0005\u0003An\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#\u00013\u0011\u0005\u0015TW\"\u00014\u000b\u0005\u001dD\u0017\u0001\u00027b]\u001eT\u0011![\u0001\u0005U\u00064\u0018-\u0003\u0002PM\u0006a\u0001O]8ek\u000e$\u0018I]5us\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HC\u00018r!\tIs.\u0003\u0002qU\t\u0019\u0011I\\=\t\u000fId\u0011\u0011!a\u0001y\u0005\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\u0012!\u001e\t\u0004mftW\"A<\u000b\u0005aT\u0013AC2pY2,7\r^5p]&\u0011!p\u001e\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000eF\u0002~\u0003\u0003\u0001\"!\u000b@\n\u0005}T#a\u0002\"p_2,\u0017M\u001c\u0005\be:\t\t\u00111\u0001o\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0007\u0011\f9\u0001C\u0004s\u001f\u0005\u0005\t\u0019\u0001\u001f\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012\u0001P\u0001\u0007KF,\u0018\r\\:\u0015\u0007u\f\t\u0002C\u0004s#\u0005\u0005\t\u0019\u00018)\u0007\u0001\t)\u0002\u0005\u0003\u0002\u0018\u0005mQBAA\r\u0015\tqF$\u0003\u0003\u0002\u001e\u0005e!\u0001\u0004#fm\u0016dw\u000e]3s\u0003BL\u0017A\u0003*E\t\ncwnY6JIB\u0011QeE\n\u0006'\u0005\u0015\u0012\u0011\u0007\t\b\u0003O\ti\u0003\u0010\u001fE\u001b\t\tICC\u0002\u0002,)\nqA];oi&lW-\u0003\u0003\u00020\u0005%\"!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8oeA!\u00111GA\u001d\u001b\t\t)DC\u0002\u00028!\f!![8\n\u0007a\n)\u0004\u0006\u0002\u0002\"\u0005AAo\\*ue&tw\rF\u0001e\u0003\u0015\t\u0007\u000f\u001d7z)\u0015!\u0015QIA$\u0011\u0015Qd\u00031\u0001=\u0011\u0015\u0001e\u00031\u0001=\u0003\u001d)h.\u00199qYf$B!!\u0014\u0002ZA)\u0011&a\u0014\u0002T%\u0019\u0011\u0011\u000b\u0016\u0003\r=\u0003H/[8o!\u0015I\u0013Q\u000b\u001f=\u0013\r\t9F\u000b\u0002\u0007)V\u0004H.\u001a\u001a\t\u0011\u0005ms#!AA\u0002\u0011\u000b1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t\t\u0007E\u0002f\u0003GJ1!!\u001ag\u0005\u0019y%M[3di\u0002"
)
public class RDDBlockId extends BlockId implements Product, Serializable {
   private final int rddId;
   private final int splitIndex;

   public static Option unapply(final RDDBlockId x$0) {
      return RDDBlockId$.MODULE$.unapply(x$0);
   }

   public static RDDBlockId apply(final int rddId, final int splitIndex) {
      return RDDBlockId$.MODULE$.apply(rddId, splitIndex);
   }

   public static Function1 tupled() {
      return RDDBlockId$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return RDDBlockId$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int rddId() {
      return this.rddId;
   }

   public int splitIndex() {
      return this.splitIndex;
   }

   public String name() {
      int var10000 = this.rddId();
      return "rdd_" + var10000 + "_" + this.splitIndex();
   }

   public RDDBlockId copy(final int rddId, final int splitIndex) {
      return new RDDBlockId(rddId, splitIndex);
   }

   public int copy$default$1() {
      return this.rddId();
   }

   public int copy$default$2() {
      return this.splitIndex();
   }

   public String productPrefix() {
      return "RDDBlockId";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.rddId());
         }
         case 1 -> {
            return BoxesRunTime.boxToInteger(this.splitIndex());
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
      return x$1 instanceof RDDBlockId;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "rddId";
         }
         case 1 -> {
            return "splitIndex";
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
      var1 = Statics.mix(var1, this.splitIndex());
      return Statics.finalizeHash(var1, 2);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label38: {
            if (x$1 instanceof RDDBlockId) {
               RDDBlockId var4 = (RDDBlockId)x$1;
               if (this.rddId() == var4.rddId() && this.splitIndex() == var4.splitIndex() && var4.canEqual(this)) {
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

   public RDDBlockId(final int rddId, final int splitIndex) {
      this.rddId = rddId;
      this.splitIndex = splitIndex;
      Product.$init$(this);
   }
}
