package org.apache.spark.ml.feature;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.UnaryTransformer;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.sql.types.DataType;
import scala.Function1;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mh\u0001B\r\u001b\u0001\u0015B\u0001\u0002\u000f\u0001\u0003\u0006\u0004%\t%\u000f\u0005\t!\u0002\u0011\t\u0011)A\u0005u!)!\u000b\u0001C\u0001'\")!\u000b\u0001C\u0001/\"9\u0011\f\u0001b\u0001\n\u0003Q\u0006B\u00022\u0001A\u0003%1\fC\u0003e\u0001\u0011\u0005Q\rC\u0003l\u0001\u0011\u0005A\u000eC\u0003r\u0001\u0011E#\u000fC\u0003w\u0001\u0011Es\u000fC\u0004\u0002\f\u0001!\t&!\u0004\t\u000f\u0005=\u0001\u0001\"\u0011\u0002\u0012!9\u00111\u0005\u0001\u0005B\u0005\u0015raBA\u00185!\u0005\u0011\u0011\u0007\u0004\u00073iA\t!a\r\t\rI{A\u0011AA)\u0011\u001d\t\u0019f\u0004C\u0005\u0003+Bq!!\u0018\u0010\t\u0013\ty\u0006C\u0004\u0002\u0004>!I!!\"\t\u000f\u0005Mv\u0002\"\u0003\u00026\"9\u00111W\b\u0005\n\u0005\r\u0007\u0002CAZ\u001f\u0011\u0005!$!5\t\u000f\u0005ew\u0002\"\u0011\u0002\\\"I\u0011q]\b\u0002\u0002\u0013%\u0011\u0011\u001e\u0002\u0014!>d\u0017P\\8nS\u0006dW\t\u001f9b]NLwN\u001c\u0006\u00037q\tqAZ3biV\u0014XM\u0003\u0002\u001e=\u0005\u0011Q\u000e\u001c\u0006\u0003?\u0001\nQa\u001d9be.T!!\t\u0012\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0019\u0013aA8sO\u000e\u00011c\u0001\u0001'eA)q\u0005\u000b\u0016+a5\tA$\u0003\u0002*9\t\u0001RK\\1ssR\u0013\u0018M\\:g_JlWM\u001d\t\u0003W9j\u0011\u0001\f\u0006\u0003[q\ta\u0001\\5oC2<\u0017BA\u0018-\u0005\u00191Vm\u0019;peB\u0011\u0011\u0007A\u0007\u00025A\u00111GN\u0007\u0002i)\u0011Q\u0007H\u0001\u0005kRLG.\u0003\u00028i\t)B)\u001a4bk2$\b+\u0019:b[N<&/\u001b;bE2,\u0017aA;jIV\t!\b\u0005\u0002<\t:\u0011AH\u0011\t\u0003{\u0001k\u0011A\u0010\u0006\u0003\u007f\u0011\na\u0001\u0010:p_Rt$\"A!\u0002\u000bM\u001c\u0017\r\\1\n\u0005\r\u0003\u0015A\u0002)sK\u0012,g-\u0003\u0002F\r\n11\u000b\u001e:j]\u001eT!a\u0011!)\u0007\u0005Ae\n\u0005\u0002J\u00196\t!J\u0003\u0002L=\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u00055S%!B*j]\u000e,\u0017%A(\u0002\u000bErCG\f\u0019\u0002\tULG\r\t\u0015\u0004\u0005!s\u0015A\u0002\u001fj]&$h\b\u0006\u00021)\")\u0001h\u0001a\u0001u!\u001aA\u000b\u0013()\u0007\rAe\nF\u00011Q\r!\u0001JT\u0001\u0007I\u0016<'/Z3\u0016\u0003m\u0003\"\u0001X0\u000e\u0003uS!A\u0018\u000f\u0002\u000bA\f'/Y7\n\u0005\u0001l&\u0001C%oiB\u000b'/Y7)\u0007\u0015Ae*A\u0004eK\u001e\u0014X-\u001a\u0011)\u0007\u0019Ae*A\u0005hKR$Um\u001a:fKV\ta\r\u0005\u0002hQ6\t\u0001)\u0003\u0002j\u0001\n\u0019\u0011J\u001c;)\u0007\u001dAe*A\u0005tKR$Um\u001a:fKR\u0011QN\\\u0007\u0002\u0001!)q\u000e\u0003a\u0001M\u0006)a/\u00197vK\"\u001a\u0001\u0002\u0013(\u0002'\r\u0014X-\u0019;f)J\fgn\u001d4pe64UO\\2\u0016\u0003M\u0004Ba\u001a;+U%\u0011Q\u000f\u0011\u0002\n\rVt7\r^5p]F\n\u0011C^1mS\u0012\fG/Z%oaV$H+\u001f9f)\tA8\u0010\u0005\u0002hs&\u0011!\u0010\u0011\u0002\u0005+:LG\u000fC\u0003}\u0015\u0001\u0007Q0A\u0005j]B,H\u000fV=qKB\u0019a0a\u0002\u000e\u0003}TA!!\u0001\u0002\u0004\u0005)A/\u001f9fg*\u0019\u0011Q\u0001\u0010\u0002\u0007M\fH.C\u0002\u0002\n}\u0014\u0001\u0002R1uCRK\b/Z\u0001\u000f_V$\b/\u001e;ECR\fG+\u001f9f+\u0005i\u0018\u0001B2paf$2\u0001MA\n\u0011\u001d\t)\u0002\u0004a\u0001\u0003/\tQ!\u001a=ue\u0006\u00042\u0001XA\r\u0013\r\tY\"\u0018\u0002\t!\u0006\u0014\u0018-\\'ba\"\"A\u0002SA\u0010C\t\t\t#A\u00032]Qr\u0013'\u0001\u0005u_N#(/\u001b8h)\u0005Q\u0004\u0006B\u0007I\u0003S\t#!a\u000b\u0002\u000bMr\u0003G\f\u0019)\u0007\u0001Ae*A\nQ_2Lhn\\7jC2,\u0005\u0010]1og&|g\u000e\u0005\u00022\u001fM9q\"!\u000e\u0002<\u0005\u0005\u0003cA4\u00028%\u0019\u0011\u0011\b!\u0003\r\u0005s\u0017PU3g!\u0011\u0019\u0014Q\b\u0019\n\u0007\u0005}BGA\u000bEK\u001a\fW\u000f\u001c;QCJ\fWn\u001d*fC\u0012\f'\r\\3\u0011\t\u0005\r\u0013QJ\u0007\u0003\u0003\u000bRA!a\u0012\u0002J\u0005\u0011\u0011n\u001c\u0006\u0003\u0003\u0017\nAA[1wC&!\u0011qJA#\u00051\u0019VM]5bY&T\u0018M\u00197f)\t\t\t$A\u0006hKR\u0004v\u000e\\=TSj,G#\u00024\u0002X\u0005m\u0003BBA-#\u0001\u0007a-A\u0006ok64U-\u0019;ve\u0016\u001c\b\"B-\u0012\u0001\u00041\u0017aC3ya\u0006tG\rR3og\u0016$RBZA1\u0003c\n)(a\u001e\u0002|\u0005}\u0004bBA2%\u0001\u0007\u0011QM\u0001\u0007m\u0006dW/Z:\u0011\u000b\u001d\f9'a\u001b\n\u0007\u0005%\u0004IA\u0003BeJ\f\u0017\u0010E\u0002h\u0003[J1!a\u001cA\u0005\u0019!u.\u001e2mK\"1\u00111\u000f\nA\u0002\u0019\fq\u0001\\1ti&#\u0007\u0010C\u0003Z%\u0001\u0007a\rC\u0004\u0002zI\u0001\r!a\u001b\u0002\u00155,H\u000e^5qY&,'\u000fC\u0004\u0002~I\u0001\r!!\u001a\u0002\u0015A|G.\u001f,bYV,7\u000f\u0003\u0004\u0002\u0002J\u0001\rAZ\u0001\u000bGV\u0014\bk\u001c7z\u0013\u0012D\u0018\u0001D3ya\u0006tGm\u00159beN,Gc\u00054\u0002\b\u00065\u0015qRAI\u0003+\u000b9*!'\u0002.\u0006E\u0006bBAE'\u0001\u0007\u00111R\u0001\bS:$\u0017nY3t!\u00119\u0017q\r4\t\u000f\u0005\r4\u00031\u0001\u0002f!1\u00111O\nA\u0002\u0019Da!a%\u0014\u0001\u00041\u0017A\u00047bgR4U-\u0019;ve\u0016LE\r\u001f\u0005\u00063N\u0001\rA\u001a\u0005\b\u0003s\u001a\u0002\u0019AA6\u0011\u001d\tYj\u0005a\u0001\u0003;\u000b1\u0002]8ms&sG-[2fgB)\u0011qTAUM6\u0011\u0011\u0011\u0015\u0006\u0005\u0003G\u000b)+A\u0004nkR\f'\r\\3\u000b\u0007\u0005\u001d\u0006)\u0001\u0006d_2dWm\u0019;j_:LA!a+\u0002\"\na\u0011I\u001d:bs\n+\u0018\u000e\u001c3fe\"9\u0011QP\nA\u0002\u0005=\u0006CBAP\u0003S\u000bY\u0007\u0003\u0004\u0002\u0002N\u0001\rAZ\u0001\u0007Kb\u0004\u0018M\u001c3\u0015\r\u0005]\u0016QXAa!\rY\u0013\u0011X\u0005\u0004\u0003wc#a\u0003#f]N,g+Z2u_JDq!a0\u0015\u0001\u0004\t9,\u0001\u0002em\")\u0011\f\u0006a\u0001MR1\u0011QYAf\u0003\u001f\u00042aKAd\u0013\r\tI\r\f\u0002\r'B\f'o]3WK\u000e$xN\u001d\u0005\b\u0003\u001b,\u0002\u0019AAc\u0003\t\u0019h\u000fC\u0003Z+\u0001\u0007a\rF\u0003+\u0003'\f9\u000e\u0003\u0004\u0002VZ\u0001\rAK\u0001\u0002m\")\u0011L\u0006a\u0001M\u0006!An\\1e)\r\u0001\u0014Q\u001c\u0005\u0007\u0003?<\u0002\u0019\u0001\u001e\u0002\tA\fG\u000f\u001b\u0015\u0005/!\u000b\u0019/\t\u0002\u0002f\u0006)\u0011G\f\u001c/a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u00111\u001e\t\u0005\u0003[\f\u00190\u0004\u0002\u0002p*!\u0011\u0011_A%\u0003\u0011a\u0017M\\4\n\t\u0005U\u0018q\u001e\u0002\u0007\u001f\nTWm\u0019;)\t=A\u00151\u001d\u0015\u0005\u001d!\u000b\u0019\u000f"
)
public class PolynomialExpansion extends UnaryTransformer implements DefaultParamsWritable {
   private final String uid;
   private final IntParam degree;

   public static PolynomialExpansion load(final String path) {
      return PolynomialExpansion$.MODULE$.load(path);
   }

   public static MLReader read() {
      return PolynomialExpansion$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public String uid() {
      return this.uid;
   }

   public IntParam degree() {
      return this.degree;
   }

   public int getDegree() {
      return BoxesRunTime.unboxToInt(this.$(this.degree()));
   }

   public PolynomialExpansion setDegree(final int value) {
      return (PolynomialExpansion)this.set(this.degree(), BoxesRunTime.boxToInteger(value));
   }

   public Function1 createTransformFunc() {
      return (v) -> PolynomialExpansion$.MODULE$.expand(v, BoxesRunTime.unboxToInt(this.$(this.degree())));
   }

   public void validateInputType(final DataType inputType) {
      .MODULE$.require(inputType instanceof VectorUDT, () -> {
         String var10000 = (new VectorUDT()).catalogString();
         return "Input type must be " + var10000 + " but got " + inputType.catalogString() + ".";
      });
   }

   public DataType outputDataType() {
      return new VectorUDT();
   }

   public PolynomialExpansion copy(final ParamMap extra) {
      return (PolynomialExpansion)this.defaultCopy(extra);
   }

   public String toString() {
      String var10000 = this.uid();
      return "PolynomialExpansion: uid=" + var10000 + ", degree=" + this.$(this.degree());
   }

   public PolynomialExpansion(final String uid) {
      this.uid = uid;
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(PolynomialExpansion.class.getClassLoader());

      final class $typecreator1$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
         }

         public $typecreator1$1() {
         }
      }

      TypeTags.TypeTag var10001 = ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1());
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(PolynomialExpansion.class.getClassLoader());

      final class $typecreator2$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
         }

         public $typecreator2$1() {
         }
      }

      super(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1()));
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      this.degree = new IntParam(this, "degree", "the polynomial degree to expand (>= 1)", ParamValidators$.MODULE$.gtEq((double)1.0F));
      this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{this.degree().$minus$greater(BoxesRunTime.boxToInteger(2))}));
   }

   public PolynomialExpansion() {
      this(Identifiable$.MODULE$.randomUID("poly"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
