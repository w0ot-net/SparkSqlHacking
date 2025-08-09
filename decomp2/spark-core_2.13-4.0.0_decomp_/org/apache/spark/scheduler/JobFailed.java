package org.apache.spark.scheduler;

import java.io.Serializable;
import org.apache.spark.annotation.DeveloperApi;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005-c\u0001\u0002\f\u0018\u0001\u0002B\u0001B\u000f\u0001\u0003\u0016\u0004%\ta\u000f\u0005\t\u007f\u0001\u0011\t\u0012)A\u0005y!)\u0001\t\u0001C\u0001\u0003\"9A\tAA\u0001\n\u0003)\u0005bB$\u0001#\u0003%\t\u0001\u0013\u0005\b'\u0002\t\t\u0011\"\u0011U\u0011\u001di\u0006!!A\u0005\u0002yCqA\u0019\u0001\u0002\u0002\u0013\u00051\rC\u0004j\u0001\u0005\u0005I\u0011\t6\t\u000fE\u0004\u0011\u0011!C\u0001e\"9q\u000fAA\u0001\n\u0003B\bb\u0002>\u0001\u0003\u0003%\te\u001f\u0005\by\u0002\t\t\u0011\"\u0011~\u0011\u001dq\b!!A\u0005B}<\u0011\"a\u0004\u0018\u0003\u0003E\t!!\u0005\u0007\u0011Y9\u0012\u0011!E\u0001\u0003'Aa\u0001\u0011\t\u0005\u0002\u0005-\u0002b\u0002?\u0011\u0003\u0003%)% \u0005\n\u0003[\u0001\u0012\u0011!CA\u0003_A\u0011\"a\r\u0011\u0003\u0003%\t)!\u000e\t\u0013\u0005\u0005\u0003#!A\u0005\n\u0005\r#!\u0003&pE\u001a\u000b\u0017\u000e\\3e\u0015\tA\u0012$A\u0005tG\",G-\u001e7fe*\u0011!dG\u0001\u0006gB\f'o\u001b\u0006\u00039u\ta!\u00199bG\",'\"\u0001\u0010\u0002\u0007=\u0014xm\u0001\u0001\u0014\u000b\u0001\tse\u000b\u0018\u0011\u0005\t*S\"A\u0012\u000b\u0003\u0011\nQa]2bY\u0006L!AJ\u0012\u0003\r\u0005s\u0017PU3g!\tA\u0013&D\u0001\u0018\u0013\tQsCA\u0005K_\n\u0014Vm];miB\u0011!\u0005L\u0005\u0003[\r\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00020o9\u0011\u0001'\u000e\b\u0003cQj\u0011A\r\u0006\u0003g}\ta\u0001\u0010:p_Rt\u0014\"\u0001\u0013\n\u0005Y\u001a\u0013a\u00029bG.\fw-Z\u0005\u0003qe\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!AN\u0012\u0002\u0013\u0015D8-\u001a9uS>tW#\u0001\u001f\u0011\u0005=j\u0014B\u0001 :\u0005%)\u0005pY3qi&|g.\u0001\u0006fq\u000e,\u0007\u000f^5p]\u0002\na\u0001P5oSRtDC\u0001\"D!\tA\u0003\u0001C\u0003;\u0007\u0001\u0007A(\u0001\u0003d_BLHC\u0001\"G\u0011\u001dQD\u0001%AA\u0002q\nabY8qs\u0012\"WMZ1vYR$\u0013'F\u0001JU\ta$jK\u0001L!\ta\u0015+D\u0001N\u0015\tqu*A\u0005v]\u000eDWmY6fI*\u0011\u0001kI\u0001\u000bC:tw\u000e^1uS>t\u0017B\u0001*N\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003U\u0003\"AV.\u000e\u0003]S!\u0001W-\u0002\t1\fgn\u001a\u0006\u00025\u0006!!.\u0019<b\u0013\tavK\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002?B\u0011!\u0005Y\u0005\u0003C\u000e\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"\u0001Z4\u0011\u0005\t*\u0017B\u00014$\u0005\r\te.\u001f\u0005\bQ\"\t\t\u00111\u0001`\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\t1\u000eE\u0002m_\u0012l\u0011!\u001c\u0006\u0003]\u000e\n!bY8mY\u0016\u001cG/[8o\u0013\t\u0001XN\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dGCA:w!\t\u0011C/\u0003\u0002vG\t9!i\\8mK\u0006t\u0007b\u00025\u000b\u0003\u0003\u0005\r\u0001Z\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0002Vs\"9\u0001nCA\u0001\u0002\u0004y\u0016\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003}\u000b\u0001\u0002^8TiJLgn\u001a\u000b\u0002+\u00061Q-];bYN$2a]A\u0001\u0011\u001dAg\"!AA\u0002\u0011D3\u0001AA\u0003!\u0011\t9!a\u0003\u000e\u0005\u0005%!B\u0001)\u001a\u0013\u0011\ti!!\u0003\u0003\u0019\u0011+g/\u001a7pa\u0016\u0014\u0018\t]5\u0002\u0013){'MR1jY\u0016$\u0007C\u0001\u0015\u0011'\u0015\u0001\u0012QCA\u0011!\u0019\t9\"!\b=\u00056\u0011\u0011\u0011\u0004\u0006\u0004\u00037\u0019\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003?\tIBA\tBEN$(/Y2u\rVt7\r^5p]F\u0002B!a\t\u0002*5\u0011\u0011Q\u0005\u0006\u0004\u0003OI\u0016AA5p\u0013\rA\u0014Q\u0005\u000b\u0003\u0003#\tQ!\u00199qYf$2AQA\u0019\u0011\u0015Q4\u00031\u0001=\u0003\u001d)h.\u00199qYf$B!a\u000e\u0002>A!!%!\u000f=\u0013\r\tYd\t\u0002\u0007\u001fB$\u0018n\u001c8\t\u0011\u0005}B#!AA\u0002\t\u000b1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t)\u0005E\u0002W\u0003\u000fJ1!!\u0013X\u0005\u0019y%M[3di\u0002"
)
public class JobFailed implements JobResult, Product, Serializable {
   private final Exception exception;

   public static Option unapply(final JobFailed x$0) {
      return JobFailed$.MODULE$.unapply(x$0);
   }

   public static JobFailed apply(final Exception exception) {
      return JobFailed$.MODULE$.apply(exception);
   }

   public static Function1 andThen(final Function1 g) {
      return JobFailed$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return JobFailed$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Exception exception() {
      return this.exception;
   }

   public JobFailed copy(final Exception exception) {
      return new JobFailed(exception);
   }

   public Exception copy$default$1() {
      return this.exception();
   }

   public String productPrefix() {
      return "JobFailed";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.exception();
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
      return x$1 instanceof JobFailed;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "exception";
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
            if (x$1 instanceof JobFailed) {
               label40: {
                  JobFailed var4 = (JobFailed)x$1;
                  Exception var10000 = this.exception();
                  Exception var5 = var4.exception();
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

   public JobFailed(final Exception exception) {
      this.exception = exception;
      Product.$init$(this);
   }
}
