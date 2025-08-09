package org.apache.spark.scheduler;

import java.io.Serializable;
import org.apache.spark.annotation.DeveloperApi;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015d\u0001\u0002\f\u0018\u0001\u0002B\u0001B\u000f\u0001\u0003\u0016\u0004%\ta\u000f\u0005\t!\u0002\u0011\t\u0012)A\u0005y!)\u0011\u000b\u0001C\u0001%\"9Q\u000bAA\u0001\n\u00031\u0006b\u0002-\u0001#\u0003%\t!\u0017\u0005\bI\u0002\t\t\u0011\"\u0011f\u0011\u001di\u0007!!A\u0005\u00029DqA\u001d\u0001\u0002\u0002\u0013\u00051\u000fC\u0004z\u0001\u0005\u0005I\u0011\t>\t\u000fy\u0004\u0011\u0011!C\u0001\u007f\"I\u0011\u0011\u0002\u0001\u0002\u0002\u0013\u0005\u00131\u0002\u0005\n\u0003\u001f\u0001\u0011\u0011!C!\u0003#A\u0011\"a\u0005\u0001\u0003\u0003%\t%!\u0006\t\u0013\u0005]\u0001!!A\u0005B\u0005eq!CA\u0015/\u0005\u0005\t\u0012AA\u0016\r!1r#!A\t\u0002\u00055\u0002BB)\u0011\t\u0003\t)\u0005C\u0005\u0002\u0014A\t\t\u0011\"\u0012\u0002\u0016!I\u0011q\t\t\u0002\u0002\u0013\u0005\u0015\u0011\n\u0005\n\u0003\u001b\u0002\u0012\u0011!CA\u0003\u001fB\u0011\"a\u0017\u0011\u0003\u0003%I!!\u0018\u0003=M\u0003\u0018M]6MSN$XM\\3s\u000b:4\u0018N]8o[\u0016tG/\u00169eCR,'B\u0001\r\u001a\u0003%\u00198\r[3ek2,'O\u0003\u0002\u001b7\u0005)1\u000f]1sW*\u0011A$H\u0001\u0007CB\f7\r[3\u000b\u0003y\t1a\u001c:h\u0007\u0001\u0019R\u0001A\u0011(W9\u0002\"AI\u0013\u000e\u0003\rR\u0011\u0001J\u0001\u0006g\u000e\fG.Y\u0005\u0003M\r\u0012a!\u00118z%\u00164\u0007C\u0001\u0015*\u001b\u00059\u0012B\u0001\u0016\u0018\u0005I\u0019\u0006/\u0019:l\u0019&\u001cH/\u001a8fe\u00163XM\u001c;\u0011\u0005\tb\u0013BA\u0017$\u0005\u001d\u0001&o\u001c3vGR\u0004\"aL\u001c\u000f\u0005A*dBA\u00195\u001b\u0005\u0011$BA\u001a \u0003\u0019a$o\\8u}%\tA%\u0003\u00027G\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u001d:\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t14%\u0001\nf]ZL'o\u001c8nK:$H)\u001a;bS2\u001cX#\u0001\u001f\u0011\tu\u0002%IS\u0007\u0002})\u0011qhI\u0001\u000bG>dG.Z2uS>t\u0017BA!?\u0005\ri\u0015\r\u001d\t\u0003\u0007\u001es!\u0001R#\u0011\u0005E\u001a\u0013B\u0001$$\u0003\u0019\u0001&/\u001a3fM&\u0011\u0001*\u0013\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\u0019\u001b\u0003cA\u001fL\u001b&\u0011AJ\u0010\u0002\u0004'\u0016\f\b\u0003\u0002\u0012O\u0005\nK!aT\u0012\u0003\rQ+\b\u000f\\33\u0003M)gN^5s_:lWM\u001c;EKR\f\u0017\u000e\\:!\u0003\u0019a\u0014N\\5u}Q\u00111\u000b\u0016\t\u0003Q\u0001AQAO\u0002A\u0002q\nAaY8qsR\u00111k\u0016\u0005\bu\u0011\u0001\n\u00111\u0001=\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012A\u0017\u0016\u0003ym[\u0013\u0001\u0018\t\u0003;\nl\u0011A\u0018\u0006\u0003?\u0002\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005\u0005\u001c\u0013AC1o]>$\u0018\r^5p]&\u00111M\u0018\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001g!\t9G.D\u0001i\u0015\tI'.\u0001\u0003mC:<'\"A6\u0002\t)\fg/Y\u0005\u0003\u0011\"\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012a\u001c\t\u0003EAL!!]\u0012\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0005Q<\bC\u0001\u0012v\u0013\t18EA\u0002B]fDq\u0001\u001f\u0005\u0002\u0002\u0003\u0007q.A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0002wB\u0019Q\b ;\n\u0005ut$\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!!\u0001\u0002\bA\u0019!%a\u0001\n\u0007\u0005\u00151EA\u0004C_>dW-\u00198\t\u000faT\u0011\u0011!a\u0001i\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\r1\u0017Q\u0002\u0005\bq.\t\t\u00111\u0001p\u0003!A\u0017m\u001d5D_\u0012,G#A8\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012AZ\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005\u0005\u00111\u0004\u0005\bq:\t\t\u00111\u0001uQ\r\u0001\u0011q\u0004\t\u0005\u0003C\t)#\u0004\u0002\u0002$)\u0011\u0011-G\u0005\u0005\u0003O\t\u0019C\u0001\u0007EKZ,Gn\u001c9fe\u0006\u0003\u0018.\u0001\u0010Ta\u0006\u00148\u000eT5ti\u0016tWM]#om&\u0014xN\\7f]R,\u0006\u000fZ1uKB\u0011\u0001\u0006E\n\u0006!\u0005=\u00121\b\t\u0007\u0003c\t9\u0004P*\u000e\u0005\u0005M\"bAA\u001bG\u00059!/\u001e8uS6,\u0017\u0002BA\u001d\u0003g\u0011\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c82!\u0011\ti$a\u0011\u000e\u0005\u0005}\"bAA!U\u0006\u0011\u0011n\\\u0005\u0004q\u0005}BCAA\u0016\u0003\u0015\t\u0007\u000f\u001d7z)\r\u0019\u00161\n\u0005\u0006uM\u0001\r\u0001P\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\t\t&a\u0016\u0011\t\t\n\u0019\u0006P\u0005\u0004\u0003+\u001a#AB(qi&|g\u000e\u0003\u0005\u0002ZQ\t\t\u00111\u0001T\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003?\u00022aZA1\u0013\r\t\u0019\u0007\u001b\u0002\u0007\u001f\nTWm\u0019;"
)
public class SparkListenerEnvironmentUpdate implements SparkListenerEvent, Product, Serializable {
   private final Map environmentDetails;

   public static Option unapply(final SparkListenerEnvironmentUpdate x$0) {
      return SparkListenerEnvironmentUpdate$.MODULE$.unapply(x$0);
   }

   public static SparkListenerEnvironmentUpdate apply(final Map environmentDetails) {
      return SparkListenerEnvironmentUpdate$.MODULE$.apply(environmentDetails);
   }

   public static Function1 andThen(final Function1 g) {
      return SparkListenerEnvironmentUpdate$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return SparkListenerEnvironmentUpdate$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean logEvent() {
      return SparkListenerEvent.logEvent$(this);
   }

   public Map environmentDetails() {
      return this.environmentDetails;
   }

   public SparkListenerEnvironmentUpdate copy(final Map environmentDetails) {
      return new SparkListenerEnvironmentUpdate(environmentDetails);
   }

   public Map copy$default$1() {
      return this.environmentDetails();
   }

   public String productPrefix() {
      return "SparkListenerEnvironmentUpdate";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.environmentDetails();
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
      return x$1 instanceof SparkListenerEnvironmentUpdate;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "environmentDetails";
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
            if (x$1 instanceof SparkListenerEnvironmentUpdate) {
               label40: {
                  SparkListenerEnvironmentUpdate var4 = (SparkListenerEnvironmentUpdate)x$1;
                  Map var10000 = this.environmentDetails();
                  Map var5 = var4.environmentDetails();
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

   public SparkListenerEnvironmentUpdate(final Map environmentDetails) {
      this.environmentDetails = environmentDetails;
      SparkListenerEvent.$init$(this);
      Product.$init$(this);
   }
}
