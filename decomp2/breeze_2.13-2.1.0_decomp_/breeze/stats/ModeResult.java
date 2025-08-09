package breeze.stats;

import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055d\u0001B\r\u001b\u0001~A\u0001B\u000e\u0001\u0003\u0016\u0004%\ta\u000e\u0005\t\u0007\u0002\u0011\t\u0012)A\u0005q!AA\t\u0001BK\u0002\u0013\u0005Q\t\u0003\u0005J\u0001\tE\t\u0015!\u0003G\u0011\u0015Q\u0005\u0001\"\u0001L\u0011\u001d\u0001\u0006!!A\u0005\u0002ECq\u0001\u0017\u0001\u0012\u0002\u0013\u0005\u0011\fC\u0004g\u0001E\u0005I\u0011A4\t\u000f-\u0004\u0011\u0011!C!Y\"9Q\u000fAA\u0001\n\u0003)\u0005b\u0002<\u0001\u0003\u0003%\ta\u001e\u0005\bu\u0002\t\t\u0011\"\u0011|\u0011%\t)\u0001AA\u0001\n\u0003\t9\u0001C\u0005\u0002\u0012\u0001\t\t\u0011\"\u0011\u0002\u0014!I\u0011q\u0003\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0004\u0005\n\u00037\u0001\u0011\u0011!C!\u0003;A\u0011\"a\b\u0001\u0003\u0003%\t%!\t\b\u0013\u0005\u0015\"$!A\t\u0002\u0005\u001db\u0001C\r\u001b\u0003\u0003E\t!!\u000b\t\r)\u001bB\u0011AA\u001b\u0011%\tYbEA\u0001\n\u000b\ni\u0002C\u0005\u00028M\t\t\u0011\"!\u0002:!I\u0011qI\n\u0002\u0002\u0013\u0005\u0015\u0011\n\u0005\n\u0003G\u001a\u0012\u0011!C\u0005\u0003K\u0012!\"T8eKJ+7/\u001e7u\u0015\tYB$A\u0003ti\u0006$8OC\u0001\u001e\u0003\u0019\u0011'/Z3{K\u000e\u0001QC\u0001\u0011;'\u0011\u0001\u0011e\n\u0016\u0011\u0005\t*S\"A\u0012\u000b\u0003\u0011\nQa]2bY\u0006L!AJ\u0012\u0003\r\u0005s\u0017PU3g!\t\u0011\u0003&\u0003\u0002*G\t9\u0001K]8ek\u000e$\bCA\u00164\u001d\ta\u0013G\u0004\u0002.a5\taF\u0003\u00020=\u00051AH]8pizJ\u0011\u0001J\u0005\u0003e\r\nq\u0001]1dW\u0006<W-\u0003\u00025k\ta1+\u001a:jC2L'0\u00192mK*\u0011!gI\u0001\u0005[>$W-F\u00019!\tI$\b\u0004\u0001\u0005\u000bm\u0002!\u0019\u0001\u001f\u0003\u0003Q\u000b\"!\u0010!\u0011\u0005\tr\u0014BA $\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"AI!\n\u0005\t\u001b#aA!os\u0006)Qn\u001c3fA\u0005IaM]3rk\u0016t7-_\u000b\u0002\rB\u0011!eR\u0005\u0003\u0011\u000e\u00121!\u00138u\u0003)1'/Z9vK:\u001c\u0017\u0010I\u0001\u0007y%t\u0017\u000e\u001e \u0015\u00071su\nE\u0002N\u0001aj\u0011A\u0007\u0005\u0006m\u0015\u0001\r\u0001\u000f\u0005\u0006\t\u0016\u0001\rAR\u0001\u0005G>\u0004\u00180\u0006\u0002S+R\u00191KV,\u0011\u00075\u0003A\u000b\u0005\u0002:+\u0012)1H\u0002b\u0001y!9aG\u0002I\u0001\u0002\u0004!\u0006b\u0002#\u0007!\u0003\u0005\rAR\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\tQV-F\u0001\\U\tADlK\u0001^!\tq6-D\u0001`\u0015\t\u0001\u0017-A\u0005v]\u000eDWmY6fI*\u0011!mI\u0001\u000bC:tw\u000e^1uS>t\u0017B\u00013`\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a\u0003\u0006w\u001d\u0011\r\u0001P\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\tA'.F\u0001jU\t1E\fB\u0003<\u0011\t\u0007A(A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002[B\u0011an]\u0007\u0002_*\u0011\u0001/]\u0001\u0005Y\u0006twMC\u0001s\u0003\u0011Q\u0017M^1\n\u0005Q|'AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0005\u0001C\bbB=\f\u0003\u0003\u0005\rAR\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0003q\u0004B!`A\u0001\u00016\taP\u0003\u0002\u0000G\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0007\u0005\raP\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA\u0005\u0003\u001f\u00012AIA\u0006\u0013\r\tia\t\u0002\b\u0005>|G.Z1o\u0011\u001dIX\"!AA\u0002\u0001\u000b!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0019Q.!\u0006\t\u000fet\u0011\u0011!a\u0001\r\u0006A\u0001.Y:i\u0007>$W\rF\u0001G\u0003!!xn\u0015;sS:<G#A7\u0002\r\u0015\fX/\u00197t)\u0011\tI!a\t\t\u000fe\f\u0012\u0011!a\u0001\u0001\u0006QQj\u001c3f%\u0016\u001cX\u000f\u001c;\u0011\u00055\u001b2\u0003B\n\"\u0003W\u0001B!!\f\u000245\u0011\u0011q\u0006\u0006\u0004\u0003c\t\u0018AA5p\u0013\r!\u0014q\u0006\u000b\u0003\u0003O\tQ!\u00199qYf,B!a\u000f\u0002BQ1\u0011QHA\"\u0003\u000b\u0002B!\u0014\u0001\u0002@A\u0019\u0011(!\u0011\u0005\u000bm2\"\u0019\u0001\u001f\t\rY2\u0002\u0019AA \u0011\u0015!e\u00031\u0001G\u0003\u001d)h.\u00199qYf,B!a\u0013\u0002\\Q!\u0011QJA/!\u0015\u0011\u0013qJA*\u0013\r\t\tf\t\u0002\u0007\u001fB$\u0018n\u001c8\u0011\r\t\n)&!\u0017G\u0013\r\t9f\t\u0002\u0007)V\u0004H.\u001a\u001a\u0011\u0007e\nY\u0006B\u0003</\t\u0007A\bC\u0005\u0002`]\t\t\u00111\u0001\u0002b\u0005\u0019\u0001\u0010\n\u0019\u0011\t5\u0003\u0011\u0011L\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003O\u00022A\\A5\u0013\r\tYg\u001c\u0002\u0007\u001f\nTWm\u0019;"
)
public class ModeResult implements Product, Serializable {
   private final Object mode;
   private final int frequency;

   public static Option unapply(final ModeResult x$0) {
      return ModeResult$.MODULE$.unapply(x$0);
   }

   public static ModeResult apply(final Object mode, final int frequency) {
      return ModeResult$.MODULE$.apply(mode, frequency);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Object mode() {
      return this.mode;
   }

   public int frequency() {
      return this.frequency;
   }

   public ModeResult copy(final Object mode, final int frequency) {
      return new ModeResult(mode, frequency);
   }

   public Object copy$default$1() {
      return this.mode();
   }

   public int copy$default$2() {
      return this.frequency();
   }

   public String productPrefix() {
      return "ModeResult";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.mode();
            break;
         case 1:
            var10000 = BoxesRunTime.boxToInteger(this.frequency());
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
      return x$1 instanceof ModeResult;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "mode";
            break;
         case 1:
            var10000 = "frequency";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.mode()));
      var1 = Statics.mix(var1, this.frequency());
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label51: {
            boolean var2;
            if (x$1 instanceof ModeResult) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               ModeResult var4 = (ModeResult)x$1;
               if (this.frequency() == var4.frequency() && BoxesRunTime.equals(this.mode(), var4.mode()) && var4.canEqual(this)) {
                  break label51;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public ModeResult(final Object mode, final int frequency) {
      this.mode = mode;
      this.frequency = frequency;
      Product.$init$(this);
   }
}
