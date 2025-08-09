package org.apache.spark.storage;

import java.io.Serializable;
import java.util.UUID;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ec!\u0002\f\u0018\u0001fy\u0002\u0002C\u001c\u0001\u0005+\u0007I\u0011\u0001\u001d\t\u0011\u0005\u0003!\u0011#Q\u0001\neBQA\u0011\u0001\u0005\u0002\rCQA\u0012\u0001\u0005B\u001dCq\u0001\u0015\u0001\u0002\u0002\u0013\u0005\u0011\u000bC\u0004T\u0001E\u0005I\u0011\u0001+\t\u000f}\u0003\u0011\u0011!C!A\"9a\rAA\u0001\n\u00039\u0007bB6\u0001\u0003\u0003%\t\u0001\u001c\u0005\be\u0002\t\t\u0011\"\u0011t\u0011\u001dQ\b!!A\u0005\u0002mD\u0011\"!\u0001\u0001\u0003\u0003%\t%a\u0001\t\u0013\u0005\u001d\u0001!!A\u0005B\u0005%\u0001\"CA\u0006\u0001\u0005\u0005I\u0011IA\u0007\u000f)\t\tbFA\u0001\u0012\u0003I\u00121\u0003\u0004\n-]\t\t\u0011#\u0001\u001a\u0003+AaA\u0011\t\u0005\u0002\u00055\u0002\"CA\u0018!\u0005\u0005IQIA\u0019\u0011%\t\u0019\u0004EA\u0001\n\u0003\u000b)\u0004C\u0005\u0002:A\t\t\u0011\"!\u0002<!I\u0011q\t\t\u0002\u0002\u0013%\u0011\u0011\n\u0002\u0011)\u0016l\u0007\u000fT8dC2\u0014En\\2l\u0013\u0012T!\u0001G\r\u0002\u000fM$xN]1hK*\u0011!dG\u0001\u0006gB\f'o\u001b\u0006\u00039u\ta!\u00199bG\",'\"\u0001\u0010\u0002\u0007=\u0014xm\u0005\u0003\u0001A\u0011R\u0003CA\u0011#\u001b\u00059\u0012BA\u0012\u0018\u0005\u001d\u0011En\\2l\u0013\u0012\u0004\"!\n\u0015\u000e\u0003\u0019R\u0011aJ\u0001\u0006g\u000e\fG.Y\u0005\u0003S\u0019\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002,i9\u0011AF\r\b\u0003[Ej\u0011A\f\u0006\u0003_A\na\u0001\u0010:p_Rt4\u0001A\u0005\u0002O%\u00111GJ\u0001\ba\u0006\u001c7.Y4f\u0013\t)dG\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u00024M\u0005\u0011\u0011\u000eZ\u000b\u0002sA\u0011!hP\u0007\u0002w)\u0011A(P\u0001\u0005kRLGNC\u0001?\u0003\u0011Q\u0017M^1\n\u0005\u0001[$\u0001B+V\u0013\u0012\u000b1!\u001b3!\u0003\u0019a\u0014N\\5u}Q\u0011A)\u0012\t\u0003C\u0001AQaN\u0002A\u0002e\nAA\\1nKV\t\u0001\n\u0005\u0002J\u001b:\u0011!j\u0013\t\u0003[\u0019J!\u0001\u0014\u0014\u0002\rA\u0013X\rZ3g\u0013\tquJ\u0001\u0004TiJLgn\u001a\u0006\u0003\u0019\u001a\nAaY8qsR\u0011AI\u0015\u0005\bo\u0015\u0001\n\u00111\u0001:\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012!\u0016\u0016\u0003sY[\u0013a\u0016\t\u00031vk\u0011!\u0017\u0006\u00035n\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005q3\u0013AC1o]>$\u0018\r^5p]&\u0011a,\u0017\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001b!\t\u0011W-D\u0001d\u0015\t!W(\u0001\u0003mC:<\u0017B\u0001(d\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005A\u0007CA\u0013j\u0013\tQgEA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0002naB\u0011QE\\\u0005\u0003_\u001a\u00121!\u00118z\u0011\u001d\t\u0018\"!AA\u0002!\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#\u0001;\u0011\u0007UDX.D\u0001w\u0015\t9h%\u0001\u0006d_2dWm\u0019;j_:L!!\u001f<\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0003y~\u0004\"!J?\n\u0005y4#a\u0002\"p_2,\u0017M\u001c\u0005\bc.\t\t\u00111\u0001n\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0007\u0005\f)\u0001C\u0004r\u0019\u0005\u0005\t\u0019\u00015\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012\u0001[\u0001\u0007KF,\u0018\r\\:\u0015\u0007q\fy\u0001C\u0004r\u001d\u0005\u0005\t\u0019A7\u0002!Q+W\u000e\u001d'pG\u0006d'\t\\8dW&#\u0007CA\u0011\u0011'\u0015\u0001\u0012qCA\u0012!\u0019\tI\"a\b:\t6\u0011\u00111\u0004\u0006\u0004\u0003;1\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003C\tYBA\tBEN$(/Y2u\rVt7\r^5p]F\u0002B!!\n\u0002,5\u0011\u0011q\u0005\u0006\u0004\u0003Si\u0014AA5p\u0013\r)\u0014q\u0005\u000b\u0003\u0003'\t\u0001\u0002^8TiJLgn\u001a\u000b\u0002C\u0006)\u0011\r\u001d9msR\u0019A)a\u000e\t\u000b]\u001a\u0002\u0019A\u001d\u0002\u000fUt\u0017\r\u001d9msR!\u0011QHA\"!\u0011)\u0013qH\u001d\n\u0007\u0005\u0005cE\u0001\u0004PaRLwN\u001c\u0005\t\u0003\u000b\"\u0012\u0011!a\u0001\t\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005-\u0003c\u00012\u0002N%\u0019\u0011qJ2\u0003\r=\u0013'.Z2u\u0001"
)
public class TempLocalBlockId extends BlockId implements Product, Serializable {
   private final UUID id;

   public static Option unapply(final TempLocalBlockId x$0) {
      return TempLocalBlockId$.MODULE$.unapply(x$0);
   }

   public static TempLocalBlockId apply(final UUID id) {
      return TempLocalBlockId$.MODULE$.apply(id);
   }

   public static Function1 andThen(final Function1 g) {
      return TempLocalBlockId$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return TempLocalBlockId$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public UUID id() {
      return this.id;
   }

   public String name() {
      return "temp_local_" + this.id();
   }

   public TempLocalBlockId copy(final UUID id) {
      return new TempLocalBlockId(id);
   }

   public UUID copy$default$1() {
      return this.id();
   }

   public String productPrefix() {
      return "TempLocalBlockId";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.id();
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
      return x$1 instanceof TempLocalBlockId;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "id";
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
            if (x$1 instanceof TempLocalBlockId) {
               label40: {
                  TempLocalBlockId var4 = (TempLocalBlockId)x$1;
                  UUID var10000 = this.id();
                  UUID var5 = var4.id();
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

   public TempLocalBlockId(final UUID id) {
      this.id = id;
      Product.$init$(this);
   }
}
