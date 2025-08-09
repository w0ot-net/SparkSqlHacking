package org.apache.spark;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015d\u0001\u0002\r\u001a\t\u0002B\u0001B\u000e\u0001\u0003\u0016\u0004%\ta\u000e\u0005\t\u0007\u0002\u0011\t\u0012)A\u0005q!)A\t\u0001C\u0001\u000b\"9\u0011\n\u0001b\u0001\n\u0003Q\u0005BB&\u0001A\u0003%1\bC\u0004Z\u0001\u0005\u0005I\u0011\u0001.\t\u000fq\u0003\u0011\u0013!C\u0001;\"9q\rAA\u0001\n\u0003B\u0007b\u00029\u0001\u0003\u0003%\t!\u001d\u0005\bk\u0002\t\t\u0011\"\u0001w\u0011\u001da\b!!A\u0005BuD\u0011\"!\u0003\u0001\u0003\u0003%\t!a\u0003\t\u0013\u0005U\u0001!!A\u0005B\u0005]\u0001\"CA\u000e\u0001\u0005\u0005I\u0011IA\u000f\u0011%\ty\u0002AA\u0001\n\u0003\n\t\u0003C\u0005\u0002$\u0001\t\t\u0011\"\u0011\u0002&\u001dI\u0011\u0011F\r\u0002\u0002#%\u00111\u0006\u0004\t1e\t\t\u0011#\u0003\u0002.!1AI\u0005C\u0001\u0003\u000bB\u0011\"a\b\u0013\u0003\u0003%)%!\t\t\u0013\u0005\u001d##!A\u0005\u0002\u0006%\u0003\"CA'%\u0005\u0005I\u0011QA(\u0011%\tYFEA\u0001\n\u0013\tiF\u0001\u0007FeJ|'oU;c\u0013:4wN\u0003\u0002\u001b7\u0005)1\u000f]1sW*\u0011A$H\u0001\u0007CB\f7\r[3\u000b\u0003y\t1a\u001c:h\u0007\u0001\u0019B\u0001A\u0011(UA\u0011!%J\u0007\u0002G)\tA%A\u0003tG\u0006d\u0017-\u0003\u0002'G\t1\u0011I\\=SK\u001a\u0004\"A\t\u0015\n\u0005%\u001a#a\u0002)s_\u0012,8\r\u001e\t\u0003WMr!\u0001L\u0019\u000f\u00055\u0002T\"\u0001\u0018\u000b\u0005=z\u0012A\u0002\u001fs_>$h(C\u0001%\u0013\t\u00114%A\u0004qC\u000e\\\u0017mZ3\n\u0005Q*$\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u001a$\u0003\u001diWm]:bO\u0016,\u0012\u0001\u000f\t\u0004WeZ\u0014B\u0001\u001e6\u0005\r\u0019V-\u001d\t\u0003y\u0001s!!\u0010 \u0011\u00055\u001a\u0013BA $\u0003\u0019\u0001&/\u001a3fM&\u0011\u0011I\u0011\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005}\u001a\u0013\u0001C7fgN\fw-\u001a\u0011\u0002\rqJg.\u001b;?)\t1\u0005\n\u0005\u0002H\u00015\t\u0011\u0004C\u00037\u0007\u0001\u0007\u0001(A\bnKN\u001c\u0018mZ3UK6\u0004H.\u0019;f+\u0005Y\u0014\u0001E7fgN\fw-\u001a+f[Bd\u0017\r^3!Q\t)Q\n\u0005\u0002O/6\tqJ\u0003\u0002Q#\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\u000b\u0005I\u001b\u0016a\u00026bG.\u001cxN\u001c\u0006\u0003)V\u000b\u0011BZ1ti\u0016\u0014\b0\u001c7\u000b\u0003Y\u000b1aY8n\u0013\tAvJ\u0001\u0006Kg>t\u0017j\u001a8pe\u0016\fAaY8qsR\u0011ai\u0017\u0005\bm\u0019\u0001\n\u00111\u00019\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012A\u0018\u0016\u0003q}[\u0013\u0001\u0019\t\u0003C\u0016l\u0011A\u0019\u0006\u0003G\u0012\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005A\u001b\u0013B\u00014c\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003%\u0004\"A[8\u000e\u0003-T!\u0001\\7\u0002\t1\fgn\u001a\u0006\u0002]\u0006!!.\u0019<b\u0013\t\t5.\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u0001s!\t\u00113/\u0003\u0002uG\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0011qO\u001f\t\u0003EaL!!_\u0012\u0003\u0007\u0005s\u0017\u0010C\u0004|\u0015\u0005\u0005\t\u0019\u0001:\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\u0005q\b\u0003B@\u0002\u0006]l!!!\u0001\u000b\u0007\u0005\r1%\u0001\u0006d_2dWm\u0019;j_:LA!a\u0002\u0002\u0002\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\ti!a\u0005\u0011\u0007\t\ny!C\u0002\u0002\u0012\r\u0012qAQ8pY\u0016\fg\u000eC\u0004|\u0019\u0005\u0005\t\u0019A<\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0004S\u0006e\u0001bB>\u000e\u0003\u0003\u0005\rA]\u0001\tQ\u0006\u001c\bnQ8eKR\t!/\u0001\u0005u_N#(/\u001b8h)\u0005I\u0017AB3rk\u0006d7\u000f\u0006\u0003\u0002\u000e\u0005\u001d\u0002bB>\u0011\u0003\u0003\u0005\ra^\u0001\r\u000bJ\u0014xN]*vE&sgm\u001c\t\u0003\u000fJ\u0019RAEA\u0018\u0003w\u0001b!!\r\u00028a2UBAA\u001a\u0015\r\t)dI\u0001\beVtG/[7f\u0013\u0011\tI$a\r\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0017\u0007\u0005\u0003\u0002>\u0005\rSBAA \u0015\r\t\t%\\\u0001\u0003S>L1\u0001NA )\t\tY#A\u0003baBd\u0017\u0010F\u0002G\u0003\u0017BQAN\u000bA\u0002a\nq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002R\u0005]\u0003\u0003\u0002\u0012\u0002TaJ1!!\u0016$\u0005\u0019y\u0005\u000f^5p]\"A\u0011\u0011\f\f\u0002\u0002\u0003\u0007a)A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a\u0018\u0011\u0007)\f\t'C\u0002\u0002d-\u0014aa\u00142kK\u000e$\b"
)
public class ErrorSubInfo implements Product, Serializable {
   private final Seq message;
   @JsonIgnore
   private final String messageTemplate;

   public static Option unapply(final ErrorSubInfo x$0) {
      return ErrorSubInfo$.MODULE$.unapply(x$0);
   }

   public static ErrorSubInfo apply(final Seq message) {
      return ErrorSubInfo$.MODULE$.apply(message);
   }

   public static Function1 andThen(final Function1 g) {
      return ErrorSubInfo$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return ErrorSubInfo$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Seq message() {
      return this.message;
   }

   public String messageTemplate() {
      return this.messageTemplate;
   }

   public ErrorSubInfo copy(final Seq message) {
      return new ErrorSubInfo(message);
   }

   public Seq copy$default$1() {
      return this.message();
   }

   public String productPrefix() {
      return "ErrorSubInfo";
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
      return x$1 instanceof ErrorSubInfo;
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

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label47: {
            if (x$1 instanceof ErrorSubInfo) {
               label40: {
                  ErrorSubInfo var4 = (ErrorSubInfo)x$1;
                  Seq var10000 = this.message();
                  Seq var5 = var4.message();
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

   public ErrorSubInfo(final Seq message) {
      this.message = message;
      Product.$init$(this);
      this.messageTemplate = message.mkString("\n");
   }
}
