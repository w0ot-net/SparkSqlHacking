package org.apache.spark;

import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-b!B\u000b\u0017\u0001Za\u0002\u0002\u0003\u001b\u0001\u0005+\u0007I\u0011A\u001b\t\u0011e\u0002!\u0011#Q\u0001\nYBQA\u000f\u0001\u0005\u0002mBqA\u0010\u0001\u0002\u0002\u0013\u0005q\bC\u0004B\u0001E\u0005I\u0011\u0001\"\t\u000f5\u0003\u0011\u0011!C!\u001d\"9q\u000bAA\u0001\n\u0003)\u0004b\u0002-\u0001\u0003\u0003%\t!\u0017\u0005\b?\u0002\t\t\u0011\"\u0011a\u0011\u001d9\u0007!!A\u0005\u0002!Dq!\u001c\u0001\u0002\u0002\u0013\u0005c\u000eC\u0004q\u0001\u0005\u0005I\u0011I9\t\u000fI\u0004\u0011\u0011!C!g\u001eAQOFA\u0001\u0012\u00031bO\u0002\u0005\u0016-\u0005\u0005\t\u0012\u0001\fx\u0011\u0019Qt\u0002\"\u0001\u0002\b!I\u0011\u0011B\b\u0002\u0002\u0013\u0015\u00131\u0002\u0005\n\u0003\u001by\u0011\u0011!CA\u0003\u001fA\u0011\"a\u0005\u0010\u0003\u0003%\t)!\u0006\t\u0013\u0005\u0005r\"!A\u0005\n\u0005\r\"!F*qCJ\\Wk]3s\u0003B\u0004X\t_2faRLwN\u001c\u0006\u0003/a\tQa\u001d9be.T!!\u0007\u000e\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005Y\u0012aA8sON!\u0001!H\u0011(!\tqr$D\u0001\u0017\u0013\t\u0001cC\u0001\bTa\u0006\u00148.\u0012=dKB$\u0018n\u001c8\u0011\u0005\t*S\"A\u0012\u000b\u0003\u0011\nQa]2bY\u0006L!AJ\u0012\u0003\u000fA\u0013x\u000eZ;diB\u0011\u0001&\r\b\u0003S=r!A\u000b\u0018\u000e\u0003-R!\u0001L\u0017\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011\u0001J\u0005\u0003a\r\nq\u0001]1dW\u0006<W-\u0003\u00023g\ta1+\u001a:jC2L'0\u00192mK*\u0011\u0001gI\u0001\tKbLGoQ8eKV\ta\u0007\u0005\u0002#o%\u0011\u0001h\t\u0002\u0004\u0013:$\u0018!C3ySR\u001cu\u000eZ3!\u0003\u0019a\u0014N\\5u}Q\u0011A(\u0010\t\u0003=\u0001AQ\u0001N\u0002A\u0002Y\nAaY8qsR\u0011A\b\u0011\u0005\bi\u0011\u0001\n\u00111\u00017\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012a\u0011\u0016\u0003m\u0011[\u0013!\u0012\t\u0003\r.k\u0011a\u0012\u0006\u0003\u0011&\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005)\u001b\u0013AC1o]>$\u0018\r^5p]&\u0011Aj\u0012\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001P!\t\u0001V+D\u0001R\u0015\t\u00116+\u0001\u0003mC:<'\"\u0001+\u0002\t)\fg/Y\u0005\u0003-F\u0013aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRL\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u00035v\u0003\"AI.\n\u0005q\u001b#aA!os\"9a\fCA\u0001\u0002\u00041\u0014a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/F\u0001b!\r\u0011WMW\u0007\u0002G*\u0011AmI\u0001\u000bG>dG.Z2uS>t\u0017B\u00014d\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\u0005%d\u0007C\u0001\u0012k\u0013\tY7EA\u0004C_>dW-\u00198\t\u000fyS\u0011\u0011!a\u00015\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\tyu\u000eC\u0004_\u0017\u0005\u0005\t\u0019\u0001\u001c\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012AN\u0001\u0007KF,\u0018\r\\:\u0015\u0005%$\bb\u00020\u000e\u0003\u0003\u0005\rAW\u0001\u0016'B\f'o[+tKJ\f\u0005\u000f]#yG\u0016\u0004H/[8o!\tqrbE\u0002\u0010qz\u0004B!\u001f?7y5\t!P\u0003\u0002|G\u00059!/\u001e8uS6,\u0017BA?{\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|g.\r\t\u0004\u007f\u0006\u0015QBAA\u0001\u0015\r\t\u0019aU\u0001\u0003S>L1AMA\u0001)\u00051\u0018\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003=\u000bQ!\u00199qYf$2\u0001PA\t\u0011\u0015!$\u00031\u00017\u0003\u001d)h.\u00199qYf$B!a\u0006\u0002\u001eA!!%!\u00077\u0013\r\tYb\t\u0002\u0007\u001fB$\u0018n\u001c8\t\u0011\u0005}1#!AA\u0002q\n1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t)\u0003E\u0002Q\u0003OI1!!\u000bR\u0005\u0019y%M[3di\u0002"
)
public class SparkUserAppException extends SparkException implements Product {
   private final int exitCode;

   public static Option unapply(final SparkUserAppException x$0) {
      return SparkUserAppException$.MODULE$.unapply(x$0);
   }

   public static SparkUserAppException apply(final int exitCode) {
      return SparkUserAppException$.MODULE$.apply(exitCode);
   }

   public static Function1 andThen(final Function1 g) {
      return SparkUserAppException$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return SparkUserAppException$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int exitCode() {
      return this.exitCode;
   }

   public SparkUserAppException copy(final int exitCode) {
      return new SparkUserAppException(exitCode);
   }

   public int copy$default$1() {
      return this.exitCode();
   }

   public String productPrefix() {
      return "SparkUserAppException";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.exitCode());
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
      return x$1 instanceof SparkUserAppException;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "exitCode";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.exitCode());
      return Statics.finalizeHash(var1, 1);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label36: {
            if (x$1 instanceof SparkUserAppException) {
               SparkUserAppException var4 = (SparkUserAppException)x$1;
               if (this.exitCode() == var4.exitCode() && var4.canEqual(this)) {
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

   public SparkUserAppException(final int exitCode) {
      super("User application exited with " + exitCode);
      this.exitCode = exitCode;
      Product.$init$(this);
   }
}
