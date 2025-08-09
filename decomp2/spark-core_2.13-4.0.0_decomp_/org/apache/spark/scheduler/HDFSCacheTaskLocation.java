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
   bytes = "\u0006\u0005\u0005%c!\u0002\f\u0018\u0001fy\u0002\u0002\u0003\u001e\u0001\u0005+\u0007I\u0011I\u001e\t\u0011\u0011\u0003!\u0011#Q\u0001\nqBQ!\u0012\u0001\u0005\u0002\u0019CQ!\u0013\u0001\u0005B)Cqa\u0013\u0001\u0002\u0002\u0013\u0005A\nC\u0004O\u0001E\u0005I\u0011A(\t\u000fi\u0003\u0011\u0011!C!7\"91\rAA\u0001\n\u0003!\u0007b\u00025\u0001\u0003\u0003%\t!\u001b\u0005\b_\u0002\t\t\u0011\"\u0011q\u0011\u001d9\b!!A\u0005\u0002aDq! \u0001\u0002\u0002\u0013\u0005c\u0010C\u0005\u0002\u0002\u0001\t\t\u0011\"\u0011\u0002\u0004!I\u0011Q\u0001\u0001\u0002\u0002\u0013\u0005\u0013qA\u0004\u000b\u0003\u00179\u0012\u0011!E\u00013\u00055a!\u0003\f\u0018\u0003\u0003E\t!GA\b\u0011\u0019)\u0005\u0003\"\u0001\u0002(!A\u0011\nEA\u0001\n\u000b\nI\u0003C\u0005\u0002,A\t\t\u0011\"!\u0002.!I\u0011\u0011\u0007\t\u0002\u0002\u0013\u0005\u00151\u0007\u0005\n\u0003\u007f\u0001\u0012\u0011!C\u0005\u0003\u0003\u0012Q\u0003\u0013#G'\u000e\u000b7\r[3UCN\\Gj\\2bi&|gN\u0003\u0002\u00193\u0005I1o\u00195fIVdWM\u001d\u0006\u00035m\tQa\u001d9be.T!\u0001H\u000f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005q\u0012aA8sON)\u0001\u0001\t\u0014+[A\u0011\u0011\u0005J\u0007\u0002E)\t1%A\u0003tG\u0006d\u0017-\u0003\u0002&E\t1\u0011I\\=SK\u001a\u0004\"a\n\u0015\u000e\u0003]I!!K\f\u0003\u0019Q\u000b7o\u001b'pG\u0006$\u0018n\u001c8\u0011\u0005\u0005Z\u0013B\u0001\u0017#\u0005\u001d\u0001&o\u001c3vGR\u0004\"AL\u001c\u000f\u0005=*dB\u0001\u00195\u001b\u0005\t$B\u0001\u001a4\u0003\u0019a$o\\8u}\r\u0001\u0011\"A\u0012\n\u0005Y\u0012\u0013a\u00029bG.\fw-Z\u0005\u0003qe\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!A\u000e\u0012\u0002\t!|7\u000f^\u000b\u0002yA\u0011Q(\u0011\b\u0003}}\u0002\"\u0001\r\u0012\n\u0005\u0001\u0013\u0013A\u0002)sK\u0012,g-\u0003\u0002C\u0007\n11\u000b\u001e:j]\u001eT!\u0001\u0011\u0012\u0002\u000b!|7\u000f\u001e\u0011\u0002\rqJg.\u001b;?)\t9\u0005\n\u0005\u0002(\u0001!)!h\u0001a\u0001y\u0005AAo\\*ue&tw\rF\u0001=\u0003\u0011\u0019w\u000e]=\u0015\u0005\u001dk\u0005b\u0002\u001e\u0006!\u0003\u0005\r\u0001P\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005\u0001&F\u0001\u001fRW\u0005\u0011\u0006CA*Y\u001b\u0005!&BA+W\u0003%)hn\u00195fG.,GM\u0003\u0002XE\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005e#&!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006i\u0001O]8ek\u000e$\bK]3gSb,\u0012\u0001\u0018\t\u0003;\nl\u0011A\u0018\u0006\u0003?\u0002\fA\u0001\\1oO*\t\u0011-\u0001\u0003kCZ\f\u0017B\u0001\"_\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005)\u0007CA\u0011g\u0013\t9'EA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0002k[B\u0011\u0011e[\u0005\u0003Y\n\u00121!\u00118z\u0011\u001dq\u0017\"!AA\u0002\u0015\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#A9\u0011\u0007I,(.D\u0001t\u0015\t!(%\u0001\u0006d_2dWm\u0019;j_:L!A^:\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0003sr\u0004\"!\t>\n\u0005m\u0014#a\u0002\"p_2,\u0017M\u001c\u0005\b].\t\t\u00111\u0001k\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0005q{\bb\u00028\r\u0003\u0003\u0005\r!Z\u0001\tQ\u0006\u001c\bnQ8eKR\tQ-\u0001\u0004fcV\fGn\u001d\u000b\u0004s\u0006%\u0001b\u00028\u000f\u0003\u0003\u0005\rA[\u0001\u0016\u0011\u001235kQ1dQ\u0016$\u0016m]6M_\u000e\fG/[8o!\t9\u0003cE\u0003\u0011\u0003#\ti\u0002\u0005\u0004\u0002\u0014\u0005eAhR\u0007\u0003\u0003+Q1!a\u0006#\u0003\u001d\u0011XO\u001c;j[\u0016LA!a\u0007\u0002\u0016\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u0019\u0011\t\u0005}\u0011QE\u0007\u0003\u0003CQ1!a\ta\u0003\tIw.C\u00029\u0003C!\"!!\u0004\u0015\u0003q\u000bQ!\u00199qYf$2aRA\u0018\u0011\u0015Q4\u00031\u0001=\u0003\u001d)h.\u00199qYf$B!!\u000e\u0002<A!\u0011%a\u000e=\u0013\r\tID\t\u0002\u0007\u001fB$\u0018n\u001c8\t\u0011\u0005uB#!AA\u0002\u001d\u000b1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t\u0019\u0005E\u0002^\u0003\u000bJ1!a\u0012_\u0005\u0019y%M[3di\u0002"
)
public class HDFSCacheTaskLocation implements TaskLocation, Product, Serializable {
   private final String host;

   public static Option unapply(final HDFSCacheTaskLocation x$0) {
      return HDFSCacheTaskLocation$.MODULE$.unapply(x$0);
   }

   public static HDFSCacheTaskLocation apply(final String host) {
      return HDFSCacheTaskLocation$.MODULE$.apply(host);
   }

   public static Function1 andThen(final Function1 g) {
      return HDFSCacheTaskLocation$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return HDFSCacheTaskLocation$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String host() {
      return this.host;
   }

   public String toString() {
      String var10000 = TaskLocation$.MODULE$.inMemoryLocationTag();
      return var10000 + this.host();
   }

   public HDFSCacheTaskLocation copy(final String host) {
      return new HDFSCacheTaskLocation(host);
   }

   public String copy$default$1() {
      return this.host();
   }

   public String productPrefix() {
      return "HDFSCacheTaskLocation";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.host();
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
      return x$1 instanceof HDFSCacheTaskLocation;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "host";
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
            if (x$1 instanceof HDFSCacheTaskLocation) {
               label40: {
                  HDFSCacheTaskLocation var4 = (HDFSCacheTaskLocation)x$1;
                  String var10000 = this.host();
                  String var5 = var4.host();
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

   public HDFSCacheTaskLocation(final String host) {
      this.host = host;
      Product.$init$(this);
   }
}
