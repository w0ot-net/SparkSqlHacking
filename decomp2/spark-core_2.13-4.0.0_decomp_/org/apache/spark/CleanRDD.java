package org.apache.spark;

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
   bytes = "\u0006\u0005\u0005Mb\u0001\u0002\f\u0018\tzA\u0001\u0002\u000f\u0001\u0003\u0016\u0004%\t!\u000f\u0005\t{\u0001\u0011\t\u0012)A\u0005u!)a\b\u0001C\u0001\u007f!9!\tAA\u0001\n\u0003\u0019\u0005bB#\u0001#\u0003%\tA\u0012\u0005\b#\u0002\t\t\u0011\"\u0011S\u0011\u001dY\u0006!!A\u0005\u0002eBq\u0001\u0018\u0001\u0002\u0002\u0013\u0005Q\fC\u0004d\u0001\u0005\u0005I\u0011\t3\t\u000f-\u0004\u0011\u0011!C\u0001Y\"9\u0011\u000fAA\u0001\n\u0003\u0012\bb\u0002;\u0001\u0003\u0003%\t%\u001e\u0005\bm\u0002\t\t\u0011\"\u0011x\u0011\u001dA\b!!A\u0005Be<qa_\f\u0002\u0002#%APB\u0004\u0017/\u0005\u0005\t\u0012B?\t\ry\u0002B\u0011AA\n\u0011\u001d1\b#!A\u0005F]D\u0011\"!\u0006\u0011\u0003\u0003%\t)a\u0006\t\u0013\u0005m\u0001#!A\u0005\u0002\u0006u\u0001\"CA\u0015!\u0005\u0005I\u0011BA\u0016\u0005!\u0019E.Z1o%\u0012#%B\u0001\r\u001a\u0003\u0015\u0019\b/\u0019:l\u0015\tQ2$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u00029\u0005\u0019qN]4\u0004\u0001M)\u0001aH\u0013*YA\u0011\u0001eI\u0007\u0002C)\t!%A\u0003tG\u0006d\u0017-\u0003\u0002%C\t1\u0011I\\=SK\u001a\u0004\"AJ\u0014\u000e\u0003]I!\u0001K\f\u0003\u0017\rcW-\u00198vaR\u000b7o\u001b\t\u0003A)J!aK\u0011\u0003\u000fA\u0013x\u000eZ;diB\u0011Q&\u000e\b\u0003]Mr!a\f\u001a\u000e\u0003AR!!M\u000f\u0002\rq\u0012xn\u001c;?\u0013\u0005\u0011\u0013B\u0001\u001b\"\u0003\u001d\u0001\u0018mY6bO\u0016L!AN\u001c\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005Q\n\u0013!\u0002:eI&#W#\u0001\u001e\u0011\u0005\u0001Z\u0014B\u0001\u001f\"\u0005\rIe\u000e^\u0001\u0007e\u0012$\u0017\n\u001a\u0011\u0002\rqJg.\u001b;?)\t\u0001\u0015\t\u0005\u0002'\u0001!)\u0001h\u0001a\u0001u\u0005!1m\u001c9z)\t\u0001E\tC\u00049\tA\u0005\t\u0019\u0001\u001e\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\tqI\u000b\u0002;\u0011.\n\u0011\n\u0005\u0002K\u001f6\t1J\u0003\u0002M\u001b\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003\u001d\u0006\n!\"\u00198o_R\fG/[8o\u0013\t\u00016JA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#A*\u0011\u0005QKV\"A+\u000b\u0005Y;\u0016\u0001\u00027b]\u001eT\u0011\u0001W\u0001\u0005U\u00064\u0018-\u0003\u0002[+\n11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0002_CB\u0011\u0001eX\u0005\u0003A\u0006\u00121!\u00118z\u0011\u001d\u0011\u0007\"!AA\u0002i\n1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#A3\u0011\u0007\u0019Lg,D\u0001h\u0015\tA\u0017%\u0001\u0006d_2dWm\u0019;j_:L!A[4\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0003[B\u0004\"\u0001\t8\n\u0005=\f#a\u0002\"p_2,\u0017M\u001c\u0005\bE*\t\t\u00111\u0001_\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0005M\u001b\bb\u00022\f\u0003\u0003\u0005\rAO\u0001\tQ\u0006\u001c\bnQ8eKR\t!(\u0001\u0005u_N#(/\u001b8h)\u0005\u0019\u0016AB3rk\u0006d7\u000f\u0006\u0002nu\"9!MDA\u0001\u0002\u0004q\u0016\u0001C\"mK\u0006t'\u000b\u0012#\u0011\u0005\u0019\u00022\u0003\u0002\t\u007f\u0003\u0013\u0001Ra`A\u0003u\u0001k!!!\u0001\u000b\u0007\u0005\r\u0011%A\u0004sk:$\u0018.\\3\n\t\u0005\u001d\u0011\u0011\u0001\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\f\u0004\u0003BA\u0006\u0003#i!!!\u0004\u000b\u0007\u0005=q+\u0001\u0002j_&\u0019a'!\u0004\u0015\u0003q\fQ!\u00199qYf$2\u0001QA\r\u0011\u0015A4\u00031\u0001;\u0003\u001d)h.\u00199qYf$B!a\b\u0002&A!\u0001%!\t;\u0013\r\t\u0019#\t\u0002\u0007\u001fB$\u0018n\u001c8\t\u0011\u0005\u001dB#!AA\u0002\u0001\u000b1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\ti\u0003E\u0002U\u0003_I1!!\rV\u0005\u0019y%M[3di\u0002"
)
public class CleanRDD implements CleanupTask, Product, Serializable {
   private final int rddId;

   public static Option unapply(final CleanRDD x$0) {
      return CleanRDD$.MODULE$.unapply(x$0);
   }

   public static CleanRDD apply(final int rddId) {
      return CleanRDD$.MODULE$.apply(rddId);
   }

   public static Function1 andThen(final Function1 g) {
      return CleanRDD$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return CleanRDD$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int rddId() {
      return this.rddId;
   }

   public CleanRDD copy(final int rddId) {
      return new CleanRDD(rddId);
   }

   public int copy$default$1() {
      return this.rddId();
   }

   public String productPrefix() {
      return "CleanRDD";
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
      return x$1 instanceof CleanRDD;
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
            if (x$1 instanceof CleanRDD) {
               CleanRDD var4 = (CleanRDD)x$1;
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

   public CleanRDD(final int rddId) {
      this.rddId = rddId;
      Product.$init$(this);
   }
}
