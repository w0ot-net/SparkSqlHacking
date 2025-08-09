package breeze.optimize.proximal;

import breeze.linalg.DenseVector;
import breeze.linalg.Transpose;
import breeze.linalg.norm$;
import breeze.linalg.operators.HasOps$;
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
   bytes = "\u0006\u0005\u00055e\u0001B\u000f\u001f\u0001\u0016B\u0001b\u0010\u0001\u0003\u0016\u0004%\t\u0001\u0011\u0005\t\u0015\u0002\u0011\t\u0012)A\u0005\u0003\"A1\n\u0001BK\u0002\u0013\u0005A\n\u0003\u0005N\u0001\tE\t\u0015!\u0003H\u0011\u0015q\u0005\u0001\"\u0001P\u0011\u001d\u0019\u0006A1A\u0005\u0002QCa\u0001\u0017\u0001!\u0002\u0013)\u0006\"B-\u0001\t\u0003Q\u0006b\u00022\u0001#\u0003%\ta\u0019\u0005\b]\u0002\t\t\u0011\"\u0001p\u0011\u001d\u0011\b!%A\u0005\u0002MDq!\u001e\u0001\u0012\u0002\u0013\u00051\rC\u0004w\u0001\u0005\u0005I\u0011I<\t\u0013\u0005\u0005\u0001!!A\u0005\u0002\u0005\r\u0001\"CA\u0006\u0001\u0005\u0005I\u0011AA\u0007\u0011%\tI\u0002AA\u0001\n\u0003\nY\u0002C\u0005\u0002*\u0001\t\t\u0011\"\u0001\u0002,!I\u0011Q\u0007\u0001\u0002\u0002\u0013\u0005\u0013q\u0007\u0005\n\u0003w\u0001\u0011\u0011!C!\u0003{A\u0011\"a\u0010\u0001\u0003\u0003%\t%!\u0011\t\u0013\u0005\r\u0003!!A\u0005B\u0005\u0015s!CA%=\u0005\u0005\t\u0012AA&\r!ib$!A\t\u0002\u00055\u0003B\u0002(\u0018\t\u0003\t)\u0007C\u0005\u0002@]\t\t\u0011\"\u0012\u0002B!I\u0011qM\f\u0002\u0002\u0013\u0005\u0015\u0011\u000e\u0005\n\u0003_:\u0012\u0011!CA\u0003cB\u0011\"a!\u0018\u0003\u0003%I!!\"\u0003#A\u0013xN[3di\"K\b/\u001a:QY\u0006tWM\u0003\u0002 A\u0005A\u0001O]8yS6\fGN\u0003\u0002\"E\u0005Aq\u000e\u001d;j[&TXMC\u0001$\u0003\u0019\u0011'/Z3{K\u000e\u00011#\u0002\u0001'YA\u001a\u0004CA\u0014+\u001b\u0005A#\"A\u0015\u0002\u000bM\u001c\u0017\r\\1\n\u0005-B#AB!osJ+g\r\u0005\u0002.]5\ta$\u0003\u00020=\tA\u0001K]8yS6\fG\u000e\u0005\u0002(c%\u0011!\u0007\u000b\u0002\b!J|G-^2u!\t!DH\u0004\u00026u9\u0011a'O\u0007\u0002o)\u0011\u0001\bJ\u0001\u0007yI|w\u000e\u001e \n\u0003%J!a\u000f\u0015\u0002\u000fA\f7m[1hK&\u0011QH\u0010\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003w!\n\u0011!Y\u000b\u0002\u0003B\u0019!)R$\u000e\u0003\rS!\u0001\u0012\u0012\u0002\r1Lg.\u00197h\u0013\t15IA\u0006EK:\u001cXMV3di>\u0014\bCA\u0014I\u0013\tI\u0005F\u0001\u0004E_V\u0014G.Z\u0001\u0003C\u0002\n\u0011AY\u000b\u0002\u000f\u0006\u0011!\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007A\u000b&\u000b\u0005\u0002.\u0001!)q(\u0002a\u0001\u0003\")1*\u0002a\u0001\u000f\u0006\u0011\u0011\r^\u000b\u0002+B\u0019!IV!\n\u0005]\u001b%!\u0003+sC:\u001c\bo\\:f\u0003\r\tG\u000fI\u0001\u0005aJ|\u0007\u0010F\u0002\\=\u0002\u0004\"a\n/\n\u0005uC#\u0001B+oSRDQa\u0018\u0005A\u0002\u0005\u000b\u0011\u0001\u001f\u0005\bC\"\u0001\n\u00111\u0001H\u0003\r\u0011\bn\\\u0001\u000faJ|\u0007\u0010\n3fM\u0006,H\u000e\u001e\u00133+\u0005!'FA$fW\u00051\u0007CA4m\u001b\u0005A'BA5k\u0003%)hn\u00195fG.,GM\u0003\u0002lQ\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u00055D'!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006!1m\u001c9z)\r\u0001\u0006/\u001d\u0005\b\u007f)\u0001\n\u00111\u0001B\u0011\u001dY%\u0002%AA\u0002\u001d\u000babY8qs\u0012\"WMZ1vYR$\u0013'F\u0001uU\t\tU-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005A\bCA=\u007f\u001b\u0005Q(BA>}\u0003\u0011a\u0017M\\4\u000b\u0003u\fAA[1wC&\u0011qP\u001f\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0005\u0005\u0015\u0001cA\u0014\u0002\b%\u0019\u0011\u0011\u0002\u0015\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005=\u0011Q\u0003\t\u0004O\u0005E\u0011bAA\nQ\t\u0019\u0011I\\=\t\u0013\u0005]q\"!AA\u0002\u0005\u0015\u0011a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002\u001eA1\u0011qDA\u0013\u0003\u001fi!!!\t\u000b\u0007\u0005\r\u0002&\u0001\u0006d_2dWm\u0019;j_:LA!a\n\u0002\"\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\ti#a\r\u0011\u0007\u001d\ny#C\u0002\u00022!\u0012qAQ8pY\u0016\fg\u000eC\u0005\u0002\u0018E\t\t\u00111\u0001\u0002\u0010\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\rA\u0018\u0011\b\u0005\n\u0003/\u0011\u0012\u0011!a\u0001\u0003\u000b\t\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0003\u000b\t\u0001\u0002^8TiJLgn\u001a\u000b\u0002q\u00061Q-];bYN$B!!\f\u0002H!I\u0011qC\u000b\u0002\u0002\u0003\u0007\u0011qB\u0001\u0012!J|'.Z2u\u0011f\u0004XM\u001d)mC:,\u0007CA\u0017\u0018'\u00159\u0012qJA.!\u001d\t\t&a\u0016B\u000fBk!!a\u0015\u000b\u0007\u0005U\u0003&A\u0004sk:$\u0018.\\3\n\t\u0005e\u00131\u000b\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u0014\u0004\u0003BA/\u0003Gj!!a\u0018\u000b\u0007\u0005\u0005D0\u0001\u0002j_&\u0019Q(a\u0018\u0015\u0005\u0005-\u0013!B1qa2LH#\u0002)\u0002l\u00055\u0004\"B \u001b\u0001\u0004\t\u0005\"B&\u001b\u0001\u00049\u0015aB;oCB\u0004H.\u001f\u000b\u0005\u0003g\ny\bE\u0003(\u0003k\nI(C\u0002\u0002x!\u0012aa\u00149uS>t\u0007#B\u0014\u0002|\u0005;\u0015bAA?Q\t1A+\u001e9mKJB\u0001\"!!\u001c\u0003\u0003\u0005\r\u0001U\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAAD!\rI\u0018\u0011R\u0005\u0004\u0003\u0017S(AB(cU\u0016\u001cG\u000f"
)
public class ProjectHyperPlane implements Proximal, Product, Serializable {
   private final DenseVector a;
   private final double b;
   private final Transpose at;

   public static Option unapply(final ProjectHyperPlane x$0) {
      return ProjectHyperPlane$.MODULE$.unapply(x$0);
   }

   public static ProjectHyperPlane apply(final DenseVector a, final double b) {
      return ProjectHyperPlane$.MODULE$.apply(a, b);
   }

   public static Function1 tupled() {
      return ProjectHyperPlane$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ProjectHyperPlane$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public double valueAt(final DenseVector x) {
      return Proximal.valueAt$(this, x);
   }

   public DenseVector a() {
      return this.a;
   }

   public double b() {
      return this.b;
   }

   public Transpose at() {
      return this.at;
   }

   public void prox(final DenseVector x, final double rho) {
      double atx = BoxesRunTime.unboxToDouble(this.at().$times(x, HasOps$.MODULE$.transTimesNormalFromDot(HasOps$.MODULE$.canDotD())));
      double anorm = BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(this.a(), BoxesRunTime.boxToInteger(2), norm$.MODULE$.fromCanNormInt(norm$.MODULE$.canNorm(HasOps$.MODULE$.DV_canIterateValues(), norm$.MODULE$.scalarNorm_Double()))));
      double scale = (this.b() - atx) / (anorm * anorm);
      DenseVector ascaled = (DenseVector)this.a().$times(BoxesRunTime.boxToDouble(scale), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpMulMatrix());
      x.$plus$eq(ascaled, HasOps$.MODULE$.impl_OpAdd_InPlace_DV_DV_Double());
   }

   public double prox$default$2() {
      return (double)0.0F;
   }

   public ProjectHyperPlane copy(final DenseVector a, final double b) {
      return new ProjectHyperPlane(a, b);
   }

   public DenseVector copy$default$1() {
      return this.a();
   }

   public double copy$default$2() {
      return this.b();
   }

   public String productPrefix() {
      return "ProjectHyperPlane";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.a();
            break;
         case 1:
            var10000 = BoxesRunTime.boxToDouble(this.b());
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
      return x$1 instanceof ProjectHyperPlane;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "a";
            break;
         case 1:
            var10000 = "b";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.a()));
      var1 = Statics.mix(var1, Statics.doubleHash(this.b()));
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var7;
      if (this != x$1) {
         label55: {
            boolean var2;
            if (x$1 instanceof ProjectHyperPlane) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label38: {
                  ProjectHyperPlane var4 = (ProjectHyperPlane)x$1;
                  if (this.b() == var4.b()) {
                     label36: {
                        DenseVector var10000 = this.a();
                        DenseVector var5 = var4.a();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label36;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label36;
                        }

                        if (var4.canEqual(this)) {
                           var7 = true;
                           break label38;
                        }
                     }
                  }

                  var7 = false;
               }

               if (var7) {
                  break label55;
               }
            }

            var7 = false;
            return var7;
         }
      }

      var7 = true;
      return var7;
   }

   public ProjectHyperPlane(final DenseVector a, final double b) {
      this.a = a;
      this.b = b;
      Proximal.$init$(this);
      Product.$init$(this);
      this.at = (Transpose)a.t(HasOps$.MODULE$.transposeTensor(scala..less.colon.less..MODULE$.refl()));
   }
}
