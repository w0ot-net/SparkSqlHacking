package spire.random;

import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction1;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0015baB\u001d;!\u0003\r\nc\u0010\u0005\u0006w\u00011\tAR\u0004\u0007\u0005GQ\u0004\u0012\u00013\u0007\u000beR\u0004\u0012A1\t\u000b\t\u001cA\u0011A2\t\u000b\u0015\u001cA\u0011\u00014\t\u000b)\u001cA\u0011A6\t\u000b5\u001cA\u0011\u00018\u0007\tM\u001c\u0001\t\u001e\u0005\nS\"\u0011)\u001a!C\u0001\u0003\u0013A\u0011\"a\u0003\t\u0005#\u0005\u000b\u0011\u0002'\t\r\tDA\u0011AA\u0007\u0011\u0019Y\u0004\u0002\"\u0001\u0002\u0016!I\u00111\u0005\u0005\u0002\u0002\u0013\u0005\u0011Q\u0005\u0005\n\u0003SA\u0011\u0013!C\u0001\u0003WA\u0011\"!\u0011\t\u0003\u0003%\t%a\u0011\t\u0013\u0005U\u0003\"!A\u0005\u0002\u0005%\u0001\"CA,\u0011\u0005\u0005I\u0011AA-\u0011%\t)\u0007CA\u0001\n\u0003\n9\u0007C\u0005\u0002v!\t\t\u0011\"\u0001\u0002x!I\u0011\u0011\u0011\u0005\u0002\u0002\u0013\u0005\u00131\u0011\u0005\n\u0003\u000fC\u0011\u0011!C!\u0003\u0013C\u0011\"a#\t\u0003\u0003%\t%!$\t\u0013\u0005=\u0005\"!A\u0005B\u0005Eu!CAK\u0007\u0005\u0005\t\u0012AAL\r!\u00198!!A\t\u0002\u0005e\u0005B\u00022\u001a\t\u0003\t\t\fC\u0005\u0002\ff\t\t\u0011\"\u0012\u0002\u000e\"AQ-GA\u0001\n\u0003\u000b\u0019\fC\u0005\u00028f\t\t\u0011\"!\u0002:\"I\u0011QY\r\u0002\u0002\u0013%\u0011q\u0019\u0004\u0006A\u000e\u0001\u0015\u0011\u001f\u0005\na~\u0011)\u001a!C\u0001\u0003\u0013A\u0011\"a= \u0005#\u0005\u000b\u0011\u0002'\t\u0013I|\"Q3A\u0005\u0002\u0005%\u0001\"CA{?\tE\t\u0015!\u0003M\u0011\u0019\u0011w\u0004\"\u0001\u0002x\"11h\bC\u0001\u0003{D\u0011\"a\t \u0003\u0003%\tAa\u0003\t\u0013\u0005%r$%A\u0005\u0002\u0005-\u0002\"\u0003B\t?E\u0005I\u0011AA\u0016\u0011%\t\teHA\u0001\n\u0003\n\u0019\u0005C\u0005\u0002V}\t\t\u0011\"\u0001\u0002\n!I\u0011qK\u0010\u0002\u0002\u0013\u0005!1\u0003\u0005\n\u0003Kz\u0012\u0011!C!\u0003OB\u0011\"!\u001e \u0003\u0003%\tAa\u0006\t\u0013\u0005\u0005u$!A\u0005B\tm\u0001\"CAD?\u0005\u0005I\u0011IAE\u0011%\tYiHA\u0001\n\u0003\ni\tC\u0005\u0002\u0010~\t\t\u0011\"\u0011\u0003 \u001dI\u0011qZ\u0002\u0002\u0002#\u0005\u0011\u0011\u001b\u0004\tA\u000e\t\t\u0011#\u0001\u0002T\"1!m\rC\u0001\u0003;D\u0011\"a#4\u0003\u0003%)%!$\t\u0011\u0015\u001c\u0014\u0011!CA\u0003?D\u0011\"a.4\u0003\u0003%\t)!:\t\u0013\u0005\u00157'!A\u0005\n\u0005\u001d'\u0001B*ju\u0016T!a\u000f\u001f\u0002\rI\fg\u000eZ8n\u0015\u0005i\u0014!B:qSJ,7\u0001A\n\u0003\u0001\u0001\u0003\"!\u0011#\u000e\u0003\tS\u0011aQ\u0001\u0006g\u000e\fG.Y\u0005\u0003\u000b\n\u0013a!\u00118z%\u00164WCA$R)\tA%\f\u0005\u0003J\u00152{U\"\u0001\u001e\n\u0005-S$A\u0002*b]\u0012|W\u000e\u0005\u0002B\u001b&\u0011aJ\u0011\u0002\u0004\u0013:$\bC\u0001)R\u0019\u0001!QAU\u0001C\u0002M\u0013\u0011aR\t\u0003)^\u0003\"!Q+\n\u0005Y\u0013%a\u0002(pi\"Lgn\u001a\t\u0003\u0013bK!!\u0017\u001e\u0003\u0013\u001d+g.\u001a:bi>\u0014\b\"B.\u0002\u0001\u0004a\u0016!\u0001:\u0011\u0007%kv*\u0003\u0002_u\ty!+\u00198e_6\u001cu.\u001c9b]&|g.K\u0002\u0001?!\u0011qAQ3uo\u0016,gn\u0005\u0002\u0004\u0001\u00061A(\u001b8jiz\"\u0012\u0001\u001a\t\u0003\u0013\u000e\tQ!\u00199qYf$\"a\u001a5\u0011\u0005%\u0003\u0001\"B5\u0006\u0001\u0004a\u0015!\u00018\u0002\tU\u0004Hk\u001c\u000b\u0003O2DQ!\u001b\u0004A\u00021\u000bqAY3uo\u0016,g\u000eF\u0002h_FDQ\u0001]\u0004A\u00021\u000b!A\\\u0019\t\u000bI<\u0001\u0019\u0001'\u0002\u00059\u0014$!B#yC\u000e$8#\u0002\u0005AOVD\bCA!w\u0013\t9(IA\u0004Qe>$Wo\u0019;\u0011\u0007e\f\u0019A\u0004\u0002{\u007f:\u00111P`\u0007\u0002y*\u0011QPP\u0001\u0007yI|w\u000e\u001e \n\u0003\rK1!!\u0001C\u0003\u001d\u0001\u0018mY6bO\u0016LA!!\u0002\u0002\b\ta1+\u001a:jC2L'0\u00192mK*\u0019\u0011\u0011\u0001\"\u0016\u00031\u000b!A\u001c\u0011\u0015\t\u0005=\u00111\u0003\t\u0004\u0003#AQ\"A\u0002\t\u000b%\\\u0001\u0019\u0001'\u0016\t\u0005]\u0011Q\u0004\u000b\u0005\u00033\ty\u0002E\u0003J\u00152\u000bY\u0002E\u0002Q\u0003;!QA\u0015\u0007C\u0002MCaa\u0017\u0007A\u0002\u0005\u0005\u0002\u0003B%^\u00037\tAaY8qsR!\u0011qBA\u0014\u0011\u001dIW\u0002%AA\u00021\u000babY8qs\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0002.)\u001aA*a\f,\u0005\u0005E\u0002\u0003BA\u001a\u0003{i!!!\u000e\u000b\t\u0005]\u0012\u0011H\u0001\nk:\u001c\u0007.Z2lK\u0012T1!a\u000fC\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003\u007f\t)DA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAA#!\u0011\t9%!\u0015\u000e\u0005\u0005%#\u0002BA&\u0003\u001b\nA\u0001\\1oO*\u0011\u0011qJ\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002T\u0005%#AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005m\u0013\u0011\r\t\u0004\u0003\u0006u\u0013bAA0\u0005\n\u0019\u0011I\\=\t\u0011\u0005\r\u0014#!AA\u00021\u000b1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA5!\u0019\tY'!\u001d\u0002\\5\u0011\u0011Q\u000e\u0006\u0004\u0003_\u0012\u0015AC2pY2,7\r^5p]&!\u00111OA7\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005e\u0014q\u0010\t\u0004\u0003\u0006m\u0014bAA?\u0005\n9!i\\8mK\u0006t\u0007\"CA2'\u0005\u0005\t\u0019AA.\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\u0005\u0015\u0013Q\u0011\u0005\t\u0003G\"\u0012\u0011!a\u0001\u0019\u0006A\u0001.Y:i\u0007>$W\rF\u0001M\u0003!!xn\u0015;sS:<GCAA#\u0003\u0019)\u0017/^1mgR!\u0011\u0011PAJ\u0011%\t\u0019gFA\u0001\u0002\u0004\tY&A\u0003Fq\u0006\u001cG\u000fE\u0002\u0002\u0012e\u0019R!GAN\u0003O\u0003r!!(\u0002$2\u000by!\u0004\u0002\u0002 *\u0019\u0011\u0011\u0015\"\u0002\u000fI,h\u000e^5nK&!\u0011QUAP\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|g.\r\t\u0005\u0003S\u000by+\u0004\u0002\u0002,*!\u0011QVA'\u0003\tIw.\u0003\u0003\u0002\u0006\u0005-FCAAL)\u0011\ty!!.\t\u000b%d\u0002\u0019\u0001'\u0002\u000fUt\u0017\r\u001d9msR!\u00111XAa!\u0011\t\u0015Q\u0018'\n\u0007\u0005}&I\u0001\u0004PaRLwN\u001c\u0005\n\u0003\u0007l\u0012\u0011!a\u0001\u0003\u001f\t1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\tI\r\u0005\u0003\u0002H\u0005-\u0017\u0002BAg\u0003\u0013\u0012aa\u00142kK\u000e$\u0018a\u0002\"fi^,WM\u001c\t\u0004\u0003#\u00194#B\u001a\u0002V\u0006\u001d\u0006\u0003CAO\u0003/dE*a7\n\t\u0005e\u0017q\u0014\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u0014\u0004cAA\t?Q\u0011\u0011\u0011\u001b\u000b\u0007\u00037\f\t/a9\t\u000bA4\u0004\u0019\u0001'\t\u000bI4\u0004\u0019\u0001'\u0015\t\u0005\u001d\u0018q\u001e\t\u0006\u0003\u0006u\u0016\u0011\u001e\t\u0006\u0003\u0006-H\nT\u0005\u0004\u0003[\u0014%A\u0002+va2,'\u0007C\u0005\u0002D^\n\t\u00111\u0001\u0002\\N)q\u0004Q4vq\u0006\u0019a.\r\u0011\u0002\u00079\u0014\u0004\u0005\u0006\u0004\u0002\\\u0006e\u00181 \u0005\u0006a\u0012\u0002\r\u0001\u0014\u0005\u0006e\u0012\u0002\r\u0001T\u000b\u0005\u0003\u007f\u0014)\u0001\u0006\u0003\u0003\u0002\t\u001d\u0001#B%K\u0019\n\r\u0001c\u0001)\u0003\u0006\u0011)!+\nb\u0001'\"11,\na\u0001\u0005\u0013\u0001B!S/\u0003\u0004Q1\u00111\u001cB\u0007\u0005\u001fAq\u0001\u001d\u0014\u0011\u0002\u0003\u0007A\nC\u0004sMA\u0005\t\u0019\u0001'\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eQ!\u00111\fB\u000b\u0011!\t\u0019gKA\u0001\u0002\u0004aE\u0003BA=\u00053A\u0011\"a\u0019.\u0003\u0003\u0005\r!a\u0017\u0015\t\u0005\u0015#Q\u0004\u0005\t\u0003Gr\u0013\u0011!a\u0001\u0019R!\u0011\u0011\u0010B\u0011\u0011%\t\u0019'MA\u0001\u0002\u0004\tY&\u0001\u0003TSj,\u0007"
)
public interface Size {
   static Size between(final int n1, final int n2) {
      return Size$.MODULE$.between(n1, n2);
   }

   static Size upTo(final int n) {
      return Size$.MODULE$.upTo(n);
   }

   static Size apply(final int n) {
      return Size$.MODULE$.apply(n);
   }

   Random random(final RandomCompanion r);

   public static class Exact implements Size, Product, Serializable {
      private final int n;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public int n() {
         return this.n;
      }

      public Random random(final RandomCompanion r) {
         return r.spawn(new Const(BoxesRunTime.boxToInteger(this.n())));
      }

      public Exact copy(final int n) {
         return new Exact(n);
      }

      public int copy$default$1() {
         return this.n();
      }

      public String productPrefix() {
         return "Exact";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = BoxesRunTime.boxToInteger(this.n());
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
         return x$1 instanceof Exact;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "n";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, this.n());
         return Statics.finalizeHash(var1, 1);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label49: {
               boolean var2;
               if (x$1 instanceof Exact) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  Exact var4 = (Exact)x$1;
                  if (this.n() == var4.n() && var4.canEqual(this)) {
                     break label49;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      public Exact(final int n) {
         this.n = n;
         Product.$init$(this);
      }
   }

   public static class Exact$ extends AbstractFunction1 implements Serializable {
      public static final Exact$ MODULE$ = new Exact$();

      public final String toString() {
         return "Exact";
      }

      public Exact apply(final int n) {
         return new Exact(n);
      }

      public Option unapply(final Exact x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(BoxesRunTime.boxToInteger(x$0.n())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Exact$.class);
      }
   }

   public static class Between implements Size, Product, Serializable {
      private final int n1;
      private final int n2;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public int n1() {
         return this.n1;
      }

      public int n2() {
         return this.n2;
      }

      public Random random(final RandomCompanion r) {
         return r.int(this.n1(), this.n2());
      }

      public Between copy(final int n1, final int n2) {
         return new Between(n1, n2);
      }

      public int copy$default$1() {
         return this.n1();
      }

      public int copy$default$2() {
         return this.n2();
      }

      public String productPrefix() {
         return "Between";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = BoxesRunTime.boxToInteger(this.n1());
               break;
            case 1:
               var10000 = BoxesRunTime.boxToInteger(this.n2());
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
         return x$1 instanceof Between;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "n1";
               break;
            case 1:
               var10000 = "n2";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, this.n1());
         var1 = Statics.mix(var1, this.n2());
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
               if (x$1 instanceof Between) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  Between var4 = (Between)x$1;
                  if (this.n1() == var4.n1() && this.n2() == var4.n2() && var4.canEqual(this)) {
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

      public Between(final int n1, final int n2) {
         this.n1 = n1;
         this.n2 = n2;
         Product.$init$(this);
      }
   }

   public static class Between$ extends AbstractFunction2 implements Serializable {
      public static final Between$ MODULE$ = new Between$();

      public final String toString() {
         return "Between";
      }

      public Between apply(final int n1, final int n2) {
         return new Between(n1, n2);
      }

      public Option unapply(final Between x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2.mcII.sp(x$0.n1(), x$0.n2())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Between$.class);
      }
   }
}
