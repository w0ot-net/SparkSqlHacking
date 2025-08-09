package org.apache.spark.ml;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import org.apache.spark.annotation.Evolving;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.scheduler.SparkListenerEvent;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@Evolving
@ScalaSignature(
   bytes = "\u0006\u0005\u0005=e\u0001B\r\u001b\u0001\u000eB\u0001\"\u0010\u0001\u0003\u0016\u0004%\tA\u0010\u0005\t\u000f\u0002\u0011\t\u0012)A\u0005\u007f!)\u0001\n\u0001C\u0001\u0013\"IA\n\u0001a\u0001\u0002\u0004%\t!\u0014\u0005\n)\u0002\u0001\r\u00111A\u0005\u0002UC\u0011b\u0017\u0001A\u0002\u0003\u0005\u000b\u0015\u0002(\t\u000f%\u0004\u0011\u0011!C\u0001U\"9A\u000eAI\u0001\n\u0003i\u0007bB<\u0001\u0003\u0003%\t\u0005\u001f\u0005\n\u0003\u0003\u0001\u0011\u0011!C\u0001\u0003\u0007A\u0011\"a\u0003\u0001\u0003\u0003%\t!!\u0004\t\u0013\u0005]\u0001!!A\u0005B\u0005e\u0001\"CA\u0014\u0001\u0005\u0005I\u0011AA\u0015\u0011%\t\u0019\u0004AA\u0001\n\u0003\n)\u0004C\u0005\u0002:\u0001\t\t\u0011\"\u0011\u0002<!I\u0011Q\b\u0001\u0002\u0002\u0013\u0005\u0013q\b\u0005\n\u0003\u0003\u0002\u0011\u0011!C!\u0003\u0007:\u0011\"a\u0015\u001b\u0003\u0003E\t!!\u0016\u0007\u0011eQ\u0012\u0011!E\u0001\u0003/Ba\u0001S\n\u0005\u0002\u0005=\u0004\"CA\u001f'\u0005\u0005IQIA \u0011%\t\thEA\u0001\n\u0003\u000b\u0019\bC\u0005\u0002xM\t\t\u0011\"!\u0002z!I\u0011QQ\n\u0002\u0002\u0013%\u0011q\u0011\u0002\u0010'\u00064X-\u00138ti\u0006t7-Z#oI*\u00111\u0004H\u0001\u0003[2T!!\b\u0010\u0002\u000bM\u0004\u0018M]6\u000b\u0005}\u0001\u0013AB1qC\u000eDWMC\u0001\"\u0003\ry'oZ\u0002\u0001'\u0015\u0001AE\u000b\u00182!\t)\u0003&D\u0001'\u0015\u00059\u0013!B:dC2\f\u0017BA\u0015'\u0005\u0019\te.\u001f*fMB\u00111\u0006L\u0007\u00025%\u0011QF\u0007\u0002\b\u001b2+e/\u001a8u!\t)s&\u0003\u00021M\t9\u0001K]8ek\u000e$\bC\u0001\u001a;\u001d\t\u0019\u0004H\u0004\u00025o5\tQG\u0003\u00027E\u00051AH]8pizJ\u0011aJ\u0005\u0003s\u0019\nq\u0001]1dW\u0006<W-\u0003\u0002<y\ta1+\u001a:jC2L'0\u00192mK*\u0011\u0011HJ\u0001\u0005a\u0006$\b.F\u0001@!\t\u0001EI\u0004\u0002B\u0005B\u0011AGJ\u0005\u0003\u0007\u001a\na\u0001\u0015:fI\u00164\u0017BA#G\u0005\u0019\u0019FO]5oO*\u00111IJ\u0001\u0006a\u0006$\b\u000eI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005)[\u0005CA\u0016\u0001\u0011\u0015i4\u00011\u0001@\u0003\u00199(/\u001b;feV\ta\n\u0005\u0002P%6\t\u0001K\u0003\u0002R5\u0005!Q\u000f^5m\u0013\t\u0019\u0006K\u0001\u0005N\u0019^\u0013\u0018\u000e^3s\u0003)9(/\u001b;fe~#S-\u001d\u000b\u0003-f\u0003\"!J,\n\u0005a3#\u0001B+oSRDqAW\u0003\u0002\u0002\u0003\u0007a*A\u0002yIE\nqa\u001e:ji\u0016\u0014\b\u0005\u000b\u0002\u0007;B\u0011alZ\u0007\u0002?*\u0011\u0001-Y\u0001\u000bC:tw\u000e^1uS>t'B\u00012d\u0003\u001dQ\u0017mY6t_:T!\u0001Z3\u0002\u0013\u0019\f7\u000f^3sq6d'\"\u00014\u0002\u0007\r|W.\u0003\u0002i?\nQ!j]8o\u0013\u001etwN]3\u0002\t\r|\u0007/\u001f\u000b\u0003\u0015.Dq!P\u0004\u0011\u0002\u0003\u0007q(\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u00039T#aP8,\u0003A\u0004\"!];\u000e\u0003IT!a\u001d;\u0002\u0013Ut7\r[3dW\u0016$'B\u00011'\u0013\t1(OA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#A=\u0011\u0005i|X\"A>\u000b\u0005ql\u0018\u0001\u00027b]\u001eT\u0011A`\u0001\u0005U\u00064\u0018-\u0003\u0002Fw\u0006a\u0001O]8ek\u000e$\u0018I]5usV\u0011\u0011Q\u0001\t\u0004K\u0005\u001d\u0011bAA\u0005M\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!\u0011qBA\u000b!\r)\u0013\u0011C\u0005\u0004\u0003'1#aA!os\"A!lCA\u0001\u0002\u0004\t)!A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\tY\u0002\u0005\u0004\u0002\u001e\u0005\r\u0012qB\u0007\u0003\u0003?Q1!!\t'\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003K\tyB\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA\u0016\u0003c\u00012!JA\u0017\u0013\r\tyC\n\u0002\b\u0005>|G.Z1o\u0011!QV\"!AA\u0002\u0005=\u0011A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$2!_A\u001c\u0011!Qf\"!AA\u0002\u0005\u0015\u0011\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\u0005\u0015\u0011\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003e\fa!Z9vC2\u001cH\u0003BA\u0016\u0003\u000bB\u0001BW\t\u0002\u0002\u0003\u0007\u0011q\u0002\u0015\u0004\u0001\u0005%\u0003\u0003BA&\u0003\u001fj!!!\u0014\u000b\u0005\u0001d\u0012\u0002BA)\u0003\u001b\u0012\u0001\"\u0012<pYZLgnZ\u0001\u0010'\u00064X-\u00138ti\u0006t7-Z#oIB\u00111fE\n\u0006'\u0005e\u0013Q\r\t\u0007\u00037\n\tg\u0010&\u000e\u0005\u0005u#bAA0M\u00059!/\u001e8uS6,\u0017\u0002BA2\u0003;\u0012\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c82!\u0011\t9'!\u001c\u000e\u0005\u0005%$bAA6{\u0006\u0011\u0011n\\\u0005\u0004w\u0005%DCAA+\u0003\u0015\t\u0007\u000f\u001d7z)\rQ\u0015Q\u000f\u0005\u0006{Y\u0001\raP\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\tY(!!\u0011\t\u0015\nihP\u0005\u0004\u0003\u007f2#AB(qi&|g\u000e\u0003\u0005\u0002\u0004^\t\t\u00111\u0001K\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003\u0013\u00032A_AF\u0013\r\tii\u001f\u0002\u0007\u001f\nTWm\u0019;"
)
public class SaveInstanceEnd implements MLEvent, Product, Serializable {
   private final String path;
   @JsonIgnore
   private MLWriter writer;

   public static Option unapply(final SaveInstanceEnd x$0) {
      return SaveInstanceEnd$.MODULE$.unapply(x$0);
   }

   public static SaveInstanceEnd apply(final String path) {
      return SaveInstanceEnd$.MODULE$.apply(path);
   }

   public static Function1 andThen(final Function1 g) {
      return SaveInstanceEnd$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return SaveInstanceEnd$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean logEvent() {
      return MLEvent.logEvent$(this);
   }

   public String path() {
      return this.path;
   }

   public MLWriter writer() {
      return this.writer;
   }

   public void writer_$eq(final MLWriter x$1) {
      this.writer = x$1;
   }

   public SaveInstanceEnd copy(final String path) {
      return new SaveInstanceEnd(path);
   }

   public String copy$default$1() {
      return this.path();
   }

   public String productPrefix() {
      return "SaveInstanceEnd";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.path();
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
      return x$1 instanceof SaveInstanceEnd;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "path";
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
            if (x$1 instanceof SaveInstanceEnd) {
               label40: {
                  SaveInstanceEnd var4 = (SaveInstanceEnd)x$1;
                  String var10000 = this.path();
                  String var5 = var4.path();
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

   public SaveInstanceEnd(final String path) {
      this.path = path;
      SparkListenerEvent.$init$(this);
      MLEvent.$init$(this);
      Product.$init$(this);
   }
}
