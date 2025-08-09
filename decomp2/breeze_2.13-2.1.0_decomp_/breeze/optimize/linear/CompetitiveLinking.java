package breeze.optimize.linear;

import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Uu!\u0002\u0011\"\u0011\u0003Ac!\u0002\u0016\"\u0011\u0003Y\u0003\"B\u001b\u0002\t\u00031\u0004\"B\u001c\u0002\t\u0003Ad\u0001\u0002*\u0002\tNC\u0001B\u0017\u0003\u0003\u0016\u0004%\ta\u0017\u0005\t9\u0012\u0011\t\u0012)A\u0005\u0011\"AQ\f\u0002BK\u0002\u0013\u00051\f\u0003\u0005_\t\tE\t\u0015!\u0003I\u0011!yFA!f\u0001\n\u0003\u0001\u0007\u0002C1\u0005\u0005#\u0005\u000b\u0011B&\t\u000bU\"A\u0011\u00012\t\u000f!$\u0011\u0011!C\u0001S\"9Q\u000eBI\u0001\n\u0003q\u0007bB=\u0005#\u0003%\tA\u001c\u0005\bu\u0012\t\n\u0011\"\u0001|\u0011\u001diH!!A\u0005ByD\u0001\"a\u0004\u0005\u0003\u0003%\ta\u0017\u0005\n\u0003#!\u0011\u0011!C\u0001\u0003'A\u0011\"a\b\u0005\u0003\u0003%\t%!\t\t\u0013\u0005=B!!A\u0005\u0002\u0005E\u0002\"CA\u001e\t\u0005\u0005I\u0011IA\u001f\u0011%\t\t\u0005BA\u0001\n\u0003\n\u0019\u0005C\u0005\u0002F\u0011\t\t\u0011\"\u0011\u0002H!I\u0011\u0011\n\u0003\u0002\u0002\u0013\u0005\u00131J\u0004\n\u0003\u001f\n\u0011\u0011!E\u0005\u0003#2\u0001BU\u0001\u0002\u0002#%\u00111\u000b\u0005\u0007ki!\t!a\u001b\t\u0013\u0005\u0015#$!A\u0005F\u0005\u001d\u0003\"CA75\u0005\u0005I\u0011QA8\u0011%\t9HGA\u0001\n\u0003\u000bI\bC\u0005\u0002\fj\t\t\u0011\"\u0003\u0002\u000e\u0006\u00112i\\7qKRLG/\u001b<f\u0019&t7.\u001b8h\u0015\t\u00113%\u0001\u0004mS:,\u0017M\u001d\u0006\u0003I\u0015\n\u0001b\u001c9uS6L'0\u001a\u0006\u0002M\u00051!M]3fu\u0016\u001c\u0001\u0001\u0005\u0002*\u00035\t\u0011E\u0001\nD_6\u0004X\r^5uSZ,G*\u001b8lS:<7cA\u0001-eA\u0011Q\u0006M\u0007\u0002])\tq&A\u0003tG\u0006d\u0017-\u0003\u00022]\t1\u0011I\\=SK\u001a\u0004\"!K\u001a\n\u0005Q\n#!\u0005\"ja\u0006\u0014H/\u001b;f\u001b\u0006$8\r[5oO\u00061A(\u001b8jiz\"\u0012\u0001K\u0001\u0010Kb$(/Y2u\u001b\u0006$8\r[5oOR\u0011\u0011H\u0014\t\u0005[ib4*\u0003\u0002<]\t1A+\u001e9mKJ\u00022!P#I\u001d\tq4I\u0004\u0002@\u00056\t\u0001I\u0003\u0002BO\u00051AH]8pizJ\u0011aL\u0005\u0003\t:\nq\u0001]1dW\u0006<W-\u0003\u0002G\u000f\n\u00191+Z9\u000b\u0005\u0011s\u0003CA\u0017J\u0013\tQeFA\u0002J]R\u0004\"!\f'\n\u00055s#A\u0002#pk\ndW\rC\u0003P\u0007\u0001\u0007\u0001+\u0001\nnCR\u001c\u0007.\u001b8h!>$XM\u001c;jC2\u001c\bcA\u001fF#B\u0019Q(R&\u0003\u0015A\u0013X\rZ5di&|gn\u0005\u0003\u0005YQ;\u0006CA\u0017V\u0013\t1fFA\u0004Qe>$Wo\u0019;\u0011\u0005uB\u0016BA-H\u00051\u0019VM]5bY&T\u0018M\u00197f\u0003\u0005IW#\u0001%\u0002\u0005%\u0004\u0013!\u00016\u0002\u0005)\u0004\u0013!\u0001<\u0016\u0003-\u000b!A\u001e\u0011\u0015\t\r,gm\u001a\t\u0003I\u0012i\u0011!\u0001\u0005\u00065.\u0001\r\u0001\u0013\u0005\u0006;.\u0001\r\u0001\u0013\u0005\u0006?.\u0001\raS\u0001\u0005G>\u0004\u0018\u0010\u0006\u0003dU.d\u0007b\u0002.\r!\u0003\u0005\r\u0001\u0013\u0005\b;2\u0001\n\u00111\u0001I\u0011\u001dyF\u0002%AA\u0002-\u000babY8qs\u0012\"WMZ1vYR$\u0013'F\u0001pU\tA\u0005oK\u0001r!\t\u0011x/D\u0001t\u0015\t!X/A\u0005v]\u000eDWmY6fI*\u0011aOL\u0001\u000bC:tw\u000e^1uS>t\u0017B\u0001=t\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIM*\u0012\u0001 \u0016\u0003\u0017B\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#A@\u0011\t\u0005\u0005\u00111B\u0007\u0003\u0003\u0007QA!!\u0002\u0002\b\u0005!A.\u00198h\u0015\t\tI!\u0001\u0003kCZ\f\u0017\u0002BA\u0007\u0003\u0007\u0011aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRL\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0005\u0003+\tY\u0002E\u0002.\u0003/I1!!\u0007/\u0005\r\te.\u001f\u0005\t\u0003;\u0011\u0012\u0011!a\u0001\u0011\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!a\t\u0011\r\u0005\u0015\u00121FA\u000b\u001b\t\t9CC\u0002\u0002*9\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\ti#a\n\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003g\tI\u0004E\u0002.\u0003kI1!a\u000e/\u0005\u001d\u0011un\u001c7fC:D\u0011\"!\b\u0015\u0003\u0003\u0005\r!!\u0006\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0004\u007f\u0006}\u0002\u0002CA\u000f+\u0005\u0005\t\u0019\u0001%\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012\u0001S\u0001\ti>\u001cFO]5oOR\tq0\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003g\ti\u0005C\u0005\u0002\u001ea\t\t\u00111\u0001\u0002\u0016\u0005Q\u0001K]3eS\u000e$\u0018n\u001c8\u0011\u0005\u0011T2#\u0002\u000e\u0002V\u0005\u0005\u0004\u0003CA,\u0003;B\u0005jS2\u000e\u0005\u0005e#bAA.]\u00059!/\u001e8uS6,\u0017\u0002BA0\u00033\u0012\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c84!\u0011\t\u0019'!\u001b\u000e\u0005\u0005\u0015$\u0002BA4\u0003\u000f\t!![8\n\u0007e\u000b)\u0007\u0006\u0002\u0002R\u0005)\u0011\r\u001d9msR91-!\u001d\u0002t\u0005U\u0004\"\u0002.\u001e\u0001\u0004A\u0005\"B/\u001e\u0001\u0004A\u0005\"B0\u001e\u0001\u0004Y\u0015aB;oCB\u0004H.\u001f\u000b\u0005\u0003w\n9\tE\u0003.\u0003{\n\t)C\u0002\u0002\u00009\u0012aa\u00149uS>t\u0007CB\u0017\u0002\u0004\"C5*C\u0002\u0002\u0006:\u0012a\u0001V;qY\u0016\u001c\u0004\u0002CAE=\u0005\u0005\t\u0019A2\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002\u0010B!\u0011\u0011AAI\u0013\u0011\t\u0019*a\u0001\u0003\r=\u0013'.Z2u\u0001"
)
public final class CompetitiveLinking {
   public static Tuple2 extractMatching(final Seq matchingPotentials) {
      return CompetitiveLinking$.MODULE$.extractMatching(matchingPotentials);
   }

   private static class Prediction implements Product, Serializable {
      private final int i;
      private final int j;
      private final double v;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public int i() {
         return this.i;
      }

      public int j() {
         return this.j;
      }

      public double v() {
         return this.v;
      }

      public Prediction copy(final int i, final int j, final double v) {
         return new Prediction(i, j, v);
      }

      public int copy$default$1() {
         return this.i();
      }

      public int copy$default$2() {
         return this.j();
      }

      public double copy$default$3() {
         return this.v();
      }

      public String productPrefix() {
         return "Prediction";
      }

      public int productArity() {
         return 3;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = BoxesRunTime.boxToInteger(this.i());
               break;
            case 1:
               var10000 = BoxesRunTime.boxToInteger(this.j());
               break;
            case 2:
               var10000 = BoxesRunTime.boxToDouble(this.v());
               break;
            default:
               var10000 = Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Prediction;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "i";
               break;
            case 1:
               var10000 = "j";
               break;
            case 2:
               var10000 = "v";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, this.i());
         var1 = Statics.mix(var1, this.j());
         var1 = Statics.mix(var1, Statics.doubleHash(this.v()));
         return Statics.finalizeHash(var1, 3);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label53: {
               boolean var2;
               if (x$1 instanceof Prediction) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  Prediction var4 = (Prediction)x$1;
                  if (this.i() == var4.i() && this.j() == var4.j() && this.v() == var4.v() && var4.canEqual(this)) {
                     break label53;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      public Prediction(final int i, final int j, final double v) {
         this.i = i;
         this.j = j;
         this.v = v;
         Product.$init$(this);
      }
   }

   private static class Prediction$ extends AbstractFunction3 implements Serializable {
      public static final Prediction$ MODULE$ = new Prediction$();

      public final String toString() {
         return "Prediction";
      }

      public Prediction apply(final int i, final int j, final double v) {
         return new Prediction(i, j, v);
      }

      public Option unapply(final Prediction x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToInteger(x$0.i()), BoxesRunTime.boxToInteger(x$0.j()), BoxesRunTime.boxToDouble(x$0.v()))));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Prediction$.class);
      }

      public Prediction$() {
      }
   }
}
