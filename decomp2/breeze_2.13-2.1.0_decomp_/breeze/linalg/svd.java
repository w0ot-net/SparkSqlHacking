package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.operators.HasOps$;
import breeze.linalg.support.CanTranspose;
import breeze.math.Semiring$;
import breeze.storage.Zero$;
import java.io.Serializable;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple3;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\r\u0005r!B#G\u0011\u0003Ye!B'G\u0011\u0003q\u0005\"B.\u0002\t\u0003af\u0001B/\u0002\u0001zC\u0001b\\\u0002\u0003\u0016\u0004%\t\u0001\u001d\u0005\ty\u000e\u0011\t\u0012)A\u0005c\"AQp\u0001BK\u0002\u0013\u0005a\u0010C\u0005\u0002\u0006\r\u0011\t\u0012)A\u0005\u007f\"I\u0011qA\u0002\u0003\u0016\u0004%\t\u0001\u001d\u0005\n\u0003\u0013\u0019!\u0011#Q\u0001\nEDaaW\u0002\u0005\u0002\u0005-\u0001BBA\f\u0007\u0011\u0005\u0001\u000f\u0003\u0004\u0002\u001a\r!\tA \u0005\u0007\u00037\u0019A\u0011\u0001@\t\r\u0005u1\u0001\"\u0001q\u0011%\tybAA\u0001\n\u0003\t\t\u0003C\u0005\u00026\r\t\n\u0011\"\u0001\u00028!I\u00111K\u0002\u0012\u0002\u0013\u0005\u0011Q\u000b\u0005\n\u0003?\u001a\u0011\u0013!C\u0001\u0003CB\u0011\"a\u001a\u0004\u0003\u0003%\t%!\u001b\t\u0013\u0005m4!!A\u0005\u0002\u0005u\u0004\"CAC\u0007\u0005\u0005I\u0011AAD\u0011%\tiiAA\u0001\n\u0003\ny\tC\u0005\u0002\u001e\u000e\t\t\u0011\"\u0001\u0002 \"I\u0011\u0011V\u0002\u0002\u0002\u0013\u0005\u00131\u0016\u0005\n\u0003_\u001b\u0011\u0011!C!\u0003cC\u0011\"a-\u0004\u0003\u0003%\t%!.\t\u0013\u0005]6!!A\u0005B\u0005ev!CA_\u0003\u0005\u0005\t\u0012AA`\r!i\u0016!!A\t\u0002\u0005\u0005\u0007BB.\u001e\t\u0003\ti\rC\u0005\u00024v\t\t\u0011\"\u0012\u00026\"I\u0011qZ\u000f\u0002\u0002\u0013\u0005\u0015\u0011\u001b\u0005\n\u0003Kl\u0012\u0011!CA\u0003OD\u0011B!\u0002\u001e\u0003\u0003%IAa\u0002\u0006\r\t=\u0011\u0001\u0001B\t\u000b\u0019\u0011)#\u0001\u0001\u0003(\u001d9!1G\u0001\t\u0004\tUba\u0002B\u001c\u0003!\u0005!\u0011\b\u0005\u00077\u001a\"\tAa\u0011\t\u000f\u0005=g\u0005\"\u0001\u0003F!I!Q\u0001\u0014\u0002\u0002\u0013%!qA\u0004\b\u0005\u0017\n\u00012\u0001B'\r\u001d\u0011y%\u0001E\u0001\u0005#BaaW\u0016\u0005\u0002\t]\u0003bBAhW\u0011\u0005!\u0011\f\u0005\n\u0005\u000bY\u0013\u0011!C\u0005\u0005\u000f9qA!\u0018\u0002\u0011\u0003\u0011yFB\u0004\u0003b\u0005A\tAa\u0019\t\rm\u0003D\u0011\u0001B3\u000f\u001d\u00119\u0007\rE\u0002\u0005S2qA!\u001c1\u0011\u0003\u0011y\u0007\u0003\u0004\\g\u0011\u0005!1\u000f\u0005\b\u0003\u001f\u001cD\u0011\u0001B;\u0011%\u0011)aMA\u0001\n\u0013\u00119aB\u0004\u0003zAB\u0019Aa\u001f\u0007\u000f\tu\u0004\u0007#\u0001\u0003\u0000!11\f\u000fC\u0001\u0005\u0007Cq!a49\t\u0003\u0011)\tC\u0005\u0003\u0006a\n\t\u0011\"\u0003\u0003\b!9!\u0011R\u0001\u0005\n\t-\u0005b\u0002BN\u0003\u0011%!QT\u0003\u0007\u0005K\u000b\u0001Aa*\t\u000f\t\u0005\u0017\u0001b\u0001\u0003D\u001e91QA\u0001\t\u0004\r\u001daaBB\u0005\u0003!\u000511\u0002\u0005\u00077\u0006#\ta!\u0006\t\u000f\u0005=\u0017\t\"\u0001\u0004\u0018!I!QA!\u0002\u0002\u0013%!qA\u0001\u0004gZ$'BA$I\u0003\u0019a\u0017N\\1mO*\t\u0011*\u0001\u0004ce\u0016,'0Z\u0002\u0001!\ta\u0015!D\u0001G\u0005\r\u0019h\u000fZ\n\u0004\u0003=+\u0006C\u0001)T\u001b\u0005\t&\"\u0001*\u0002\u000bM\u001c\u0017\r\\1\n\u0005Q\u000b&AB!osJ+g\r\u0005\u0002W36\tqK\u0003\u0002Y\u0011\u00069q-\u001a8fe&\u001c\u0017B\u0001.X\u0005\u0015)f)\u001e8d\u0003\u0019a\u0014N\\5u}Q\t1JA\u0002T-\u0012+BaX:\u0002\u0002M!1a\u00141d!\t\u0001\u0016-\u0003\u0002c#\n9\u0001K]8ek\u000e$\bC\u00013m\u001d\t)'N\u0004\u0002gS6\tqM\u0003\u0002i\u0015\u00061AH]8pizJ\u0011AU\u0005\u0003WF\u000bq\u0001]1dW\u0006<W-\u0003\u0002n]\na1+\u001a:jC2L'0\u00192mK*\u00111.U\u0001\fY\u00164GOV3di>\u00148/F\u0001r!\t\u00118\u000f\u0004\u0001\u0005\u000bQ\u001c!\u0019A;\u0003\u00035\u000b\"A^=\u0011\u0005A;\u0018B\u0001=R\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001\u0015>\n\u0005m\f&aA!os\u0006aA.\u001a4u-\u0016\u001cGo\u001c:tA\u0005q1/\u001b8hk2\f'OV1mk\u0016\u001cX#A@\u0011\u0007I\f\t\u0001\u0002\u0004\u0002\u0004\r\u0011\r!\u001e\u0002\u0002-\u0006y1/\u001b8hk2\f'OV1mk\u0016\u001c\b%\u0001\u0007sS\u001eDGOV3di>\u00148/A\u0007sS\u001eDGOV3di>\u00148\u000f\t\u000b\t\u0003\u001b\t\t\"a\u0005\u0002\u0016A)\u0011qB\u0002r\u007f6\t\u0011\u0001C\u0003p\u0015\u0001\u0007\u0011\u000fC\u0003~\u0015\u0001\u0007q\u0010\u0003\u0004\u0002\b)\u0001\r!]\u0001\u0002+\u00061A%\u001e\u001a3cE\n\u0011aU\u0001\u0003-R\fAaY8qsV1\u00111EA\u0015\u0003[!\u0002\"!\n\u00020\u0005E\u00121\u0007\t\b\u0003\u001f\u0019\u0011qEA\u0016!\r\u0011\u0018\u0011\u0006\u0003\u0006i>\u0011\r!\u001e\t\u0004e\u00065BABA\u0002\u001f\t\u0007Q\u000f\u0003\u0005p\u001fA\u0005\t\u0019AA\u0014\u0011!ix\u0002%AA\u0002\u0005-\u0002\"CA\u0004\u001fA\u0005\t\u0019AA\u0014\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*b!!\u000f\u0002P\u0005ESCAA\u001eU\r\t\u0018QH\u0016\u0003\u0003\u007f\u0001B!!\u0011\u0002L5\u0011\u00111\t\u0006\u0005\u0003\u000b\n9%A\u0005v]\u000eDWmY6fI*\u0019\u0011\u0011J)\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002N\u0005\r#!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0012)A\u000f\u0005b\u0001k\u00121\u00111\u0001\tC\u0002U\fabY8qs\u0012\"WMZ1vYR$#'\u0006\u0004\u0002X\u0005m\u0013QL\u000b\u0003\u00033R3a`A\u001f\t\u0015!\u0018C1\u0001v\t\u0019\t\u0019!\u0005b\u0001k\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u001aTCBA\u001d\u0003G\n)\u0007B\u0003u%\t\u0007Q\u000f\u0002\u0004\u0002\u0004I\u0011\r!^\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\u0005-\u0004\u0003BA7\u0003oj!!a\u001c\u000b\t\u0005E\u00141O\u0001\u0005Y\u0006twM\u0003\u0002\u0002v\u0005!!.\u0019<b\u0013\u0011\tI(a\u001c\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\t\ty\bE\u0002Q\u0003\u0003K1!a!R\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\rI\u0018\u0011\u0012\u0005\n\u0003\u0017+\u0012\u0011!a\u0001\u0003\u007f\n1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAAI!\u0015\t\u0019*!'z\u001b\t\t)JC\u0002\u0002\u0018F\u000b!bY8mY\u0016\u001cG/[8o\u0013\u0011\tY*!&\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003C\u000b9\u000bE\u0002Q\u0003GK1!!*R\u0005\u001d\u0011un\u001c7fC:D\u0001\"a#\u0018\u0003\u0003\u0005\r!_\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u0002l\u00055\u0006\"CAF1\u0005\u0005\t\u0019AA@\u0003!A\u0017m\u001d5D_\u0012,GCAA@\u0003!!xn\u0015;sS:<GCAA6\u0003\u0019)\u0017/^1mgR!\u0011\u0011UA^\u0011!\tYiGA\u0001\u0002\u0004I\u0018aA*W\tB\u0019\u0011qB\u000f\u0014\tuy\u00151\u0019\t\u0005\u0003\u000b\fY-\u0004\u0002\u0002H*!\u0011\u0011ZA:\u0003\tIw.C\u0002n\u0003\u000f$\"!a0\u0002\u000b\u0005\u0004\b\u000f\\=\u0016\r\u0005M\u0017\u0011\\Ao)!\t).a8\u0002b\u0006\r\bcBA\b\u0007\u0005]\u00171\u001c\t\u0004e\u0006eG!\u0002;!\u0005\u0004)\bc\u0001:\u0002^\u00121\u00111\u0001\u0011C\u0002UDaa\u001c\u0011A\u0002\u0005]\u0007BB?!\u0001\u0004\tY\u000eC\u0004\u0002\b\u0001\u0002\r!a6\u0002\u000fUt\u0017\r\u001d9msV1\u0011\u0011^A}\u0003{$B!a;\u0002\u0000B)\u0001+!<\u0002r&\u0019\u0011q^)\u0003\r=\u0003H/[8o!%\u0001\u00161_A|\u0003w\f90C\u0002\u0002vF\u0013a\u0001V;qY\u0016\u001c\u0004c\u0001:\u0002z\u0012)A/\tb\u0001kB\u0019!/!@\u0005\r\u0005\r\u0011E1\u0001v\u0011%\u0011\t!IA\u0001\u0002\u0004\u0011\u0019!A\u0002yIA\u0002r!a\u0004\u0004\u0003o\fY0\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0003\nA!\u0011Q\u000eB\u0006\u0013\u0011\u0011i!a\u001c\u0003\r=\u0013'.Z2u\u0005!!UM\\:f'Z#\u0005cBA\b\u0007\tM!q\u0004\t\u0006\u0019\nU!\u0011D\u0005\u0004\u0005/1%a\u0003#f]N,W*\u0019;sSb\u00042\u0001\u0015B\u000e\u0013\r\u0011i\"\u0015\u0002\u0007\t>,(\r\\3\u0011\u000b1\u0013\tC!\u0007\n\u0007\t\rbIA\u0006EK:\u001cXMV3di>\u0014(!C*EK:\u001cXm\u0015,E!\u001d\tya\u0001B\u0015\u0005c\u0001R\u0001\u0014B\u000b\u0005W\u00012\u0001\u0015B\u0017\u0013\r\u0011y#\u0015\u0002\u0006\r2|\u0017\r\u001e\t\u0006\u0019\n\u0005\"1F\u0001\f'Z$w\fR'`\u00136\u0004H\u000eE\u0002\u0002\u0010\u0019\u00121b\u0015<e?\u0012ku,S7qYN!ae\u0014B\u001e!!\tyA!\u0010\u0003\u0014\t\u0005\u0013b\u0001B 3\n!\u0011*\u001c9m!\r\tya\t\u000b\u0003\u0005k!BA!\u0011\u0003H!9!\u0011\n\u0015A\u0002\tM\u0011aA7bi\u0006\t2K\u001e3`\t6{\u0016*\u001c9m?\u001acw.\u0019;\u0011\u0007\u0005=1FA\tTm\u0012|F)T0J[BdwL\u00127pCR\u001cBaK(\u0003TAA\u0011q\u0002B\u001f\u0005S\u0011)\u0006E\u0002\u0002\u0010\u0011\"\"A!\u0014\u0015\t\tU#1\f\u0005\b\u0005\u0013j\u0003\u0019\u0001B\u0015\u0003\u001d\u0011X\rZ;dK\u0012\u00042!a\u00041\u0005\u001d\u0011X\rZ;dK\u0012\u001c2\u0001M(V)\t\u0011y&A\nsK\u0012,8-\u001a3`'Z$w\fR'`\u00136\u0004H\u000eE\u0002\u0003lMj\u0011\u0001\r\u0002\u0014e\u0016$WoY3e?N3Hm\u0018#N?&k\u0007\u000f\\\n\u0005g=\u0013\t\b\u0005\u0005\u0003l\tu\"1\u0003B!)\t\u0011I\u0007\u0006\u0003\u0003B\t]\u0004b\u0002B%k\u0001\u0007!1C\u0001\u001ae\u0016$WoY3e?N3Hm\u0018#N?&k\u0007\u000f\\0GY>\fG\u000fE\u0002\u0003la\u0012\u0011D]3ek\u000e,GmX*wI~#UjX%na2|f\t\\8biN!\u0001h\u0014BA!!\u0011YG!\u0010\u0003*\tUCC\u0001B>)\u0011\u0011)Fa\"\t\u000f\t%#\b1\u0001\u0003*\u0005aAm\\*W\t~#u.\u001e2mKR!!Q\u0012BM)\u0011\u0011\tEa$\t\u000f\tEE\b1\u0001\u0003\u0014\u0006!Qn\u001c3f!\ra%QS\u0005\u0004\u0005/3%aB*W\t6{G-\u001a\u0005\b\u0005\u0013b\u0004\u0019\u0001B\n\u0003-!wn\u0015,E?\u001acw.\u0019;\u0015\t\t}%1\u0015\u000b\u0005\u0005+\u0012\t\u000bC\u0004\u0003\u0012v\u0002\rAa%\t\u000f\t%S\b1\u0001\u0003*\tAr\n]'vY6\u000bGO]5y?6\u000bGo\u0018#W?\u0016\fx\f\u0012,\u0016\t\t%&Q\u0018\t\u000b\u0005W\u00139La/\u0003 \t}a\u0002\u0002BW\u0005gk!Aa,\u000b\u0007\tEf)A\u0005pa\u0016\u0014\u0018\r^8sg&!!Q\u0017BX\u0003-y\u0005/T;m\u001b\u0006$(/\u001b=\n\u0007\te\u0016LA\u0003J[Bd'\u0007E\u0002s\u0005{#aAa0?\u0005\u0004)(aA'bi\u0006y1K\u001e3`'B\f'o]3`\u00136\u0004H.\u0006\u0004\u0003F\n='\u0011\u001e\u000b\u000b\u0005\u000f\u0014\tNa6\u0003n\nM\b\u0003DA\b\u0005\u0013\u0014i-a \u0003\u001a\t\u0005\u0013b\u0001Bf3\n)\u0011*\u001c9mgA\u0019!Oa4\u0005\r\t}vH1\u0001v\u0011\u001d\u0011\u0019n\u0010a\u0002\u0005+\f1!\\;m!\u0015\tyA\u0010Bg\u0011\u001d\u0011In\u0010a\u0002\u00057\fQ\u0001\u001e:b]N\u0004\u0002B!8\u0003d\n5'q]\u0007\u0003\u0005?T1A!9G\u0003\u001d\u0019X\u000f\u001d9peRLAA!:\u0003`\na1)\u00198Ue\u0006t7\u000f]8tKB\u0019!O!;\u0005\r\t-xH1\u0001v\u00051i\u0015\r\u001e+sC:\u001c\bo\\:f\u0011\u001d\u0011yo\u0010a\u0002\u0005c\f\u0001\"\\;m)J\fgn\u001d\t\u0006\u0003\u001fq$q\u001d\u0005\b\u0005k|\u00049\u0001B|\u0003\u001d!\u0017.\\%na2\u0004\u0002B!?\u0003>\t5'q \b\u0004\u0019\nm\u0018b\u0001B\u007f\r\u0006\u0019A-[7\u0011\u000fA\u001b\t!a \u0002\u0000%\u001911A)\u0003\rQ+\b\u000f\\33\u0003-\u0019f\u000fZ0T\u001b~KU\u000e\u001d7\u0011\u0007\u0005=\u0011IA\u0006Tm\u0012|6+T0J[Bd7\u0003B!P\u0007\u001b\u0001\"\"a\u0004\u00038\u000e=\u0011q\u0010B!!\u0015a5\u0011\u0003B\r\u0013\r\u0019\u0019B\u0012\u0002\n\u0007N\u001bU*\u0019;sSb$\"aa\u0002\u0015\r\t\u00053\u0011DB\u000f\u0011\u001d\u0019Yb\u0011a\u0001\u0007\u001f\t!!\u001c;\t\u000f\r}1\t1\u0001\u0002\u0000\u0005\t1\u000e"
)
public final class svd {
   public static UFunc.UImpl3 Svd_Sparse_Impl(final UFunc.UImpl2 mul, final CanTranspose trans, final UFunc.UImpl2 mulTrans, final UFunc.UImpl dimImpl) {
      return svd$.MODULE$.Svd_Sparse_Impl(mul, trans, mulTrans, dimImpl);
   }

   public static Object withSink(final Object s) {
      return svd$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return svd$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return svd$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return svd$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return svd$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return svd$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return svd$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return svd$.MODULE$.apply(v, impl);
   }

   public static class SVD implements Product, Serializable {
      private final Object leftVectors;
      private final Object singularValues;
      private final Object rightVectors;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Object leftVectors() {
         return this.leftVectors;
      }

      public Object singularValues() {
         return this.singularValues;
      }

      public Object rightVectors() {
         return this.rightVectors;
      }

      public Object U() {
         return this.leftVectors();
      }

      public Object $u2211() {
         return this.singularValues();
      }

      public Object S() {
         return this.$u2211();
      }

      public Object Vt() {
         return this.rightVectors();
      }

      public SVD copy(final Object leftVectors, final Object singularValues, final Object rightVectors) {
         return new SVD(leftVectors, singularValues, rightVectors);
      }

      public Object copy$default$1() {
         return this.leftVectors();
      }

      public Object copy$default$2() {
         return this.singularValues();
      }

      public Object copy$default$3() {
         return this.rightVectors();
      }

      public String productPrefix() {
         return "SVD";
      }

      public int productArity() {
         return 3;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.leftVectors();
               break;
            case 1:
               var10000 = this.singularValues();
               break;
            case 2:
               var10000 = this.rightVectors();
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
         return x$1 instanceof SVD;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "leftVectors";
               break;
            case 1:
               var10000 = "singularValues";
               break;
            case 2:
               var10000 = "rightVectors";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         return .MODULE$._hashCode(this);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label53: {
               boolean var2;
               if (x$1 instanceof SVD) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  SVD var4 = (SVD)x$1;
                  if (BoxesRunTime.equals(this.leftVectors(), var4.leftVectors()) && BoxesRunTime.equals(this.singularValues(), var4.singularValues()) && BoxesRunTime.equals(this.rightVectors(), var4.rightVectors()) && var4.canEqual(this)) {
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

      public SVD(final Object leftVectors, final Object singularValues, final Object rightVectors) {
         this.leftVectors = leftVectors;
         this.singularValues = singularValues;
         this.rightVectors = rightVectors;
         Product.$init$(this);
      }
   }

   public static class SVD$ implements Serializable {
      public static final SVD$ MODULE$ = new SVD$();

      public final String toString() {
         return "SVD";
      }

      public SVD apply(final Object leftVectors, final Object singularValues, final Object rightVectors) {
         return new SVD(leftVectors, singularValues, rightVectors);
      }

      public Option unapply(final SVD x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(x$0.leftVectors(), x$0.singularValues(), x$0.rightVectors())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(SVD$.class);
      }
   }

   public static class Svd_DM_Impl$ implements UFunc.UImpl {
      public static final Svd_DM_Impl$ MODULE$ = new Svd_DM_Impl$();

      public double apply$mcDD$sp(final double v) {
         return UFunc.UImpl.apply$mcDD$sp$(this, v);
      }

      public float apply$mcDF$sp(final double v) {
         return UFunc.UImpl.apply$mcDF$sp$(this, v);
      }

      public int apply$mcDI$sp(final double v) {
         return UFunc.UImpl.apply$mcDI$sp$(this, v);
      }

      public double apply$mcFD$sp(final float v) {
         return UFunc.UImpl.apply$mcFD$sp$(this, v);
      }

      public float apply$mcFF$sp(final float v) {
         return UFunc.UImpl.apply$mcFF$sp$(this, v);
      }

      public int apply$mcFI$sp(final float v) {
         return UFunc.UImpl.apply$mcFI$sp$(this, v);
      }

      public double apply$mcID$sp(final int v) {
         return UFunc.UImpl.apply$mcID$sp$(this, v);
      }

      public float apply$mcIF$sp(final int v) {
         return UFunc.UImpl.apply$mcIF$sp$(this, v);
      }

      public int apply$mcII$sp(final int v) {
         return UFunc.UImpl.apply$mcII$sp$(this, v);
      }

      public SVD apply(final DenseMatrix mat) {
         return svd$.MODULE$.breeze$linalg$svd$$doSVD_Double(mat, CompleteSVD$.MODULE$);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Svd_DM_Impl$.class);
      }
   }

   public static class Svd_DM_Impl_Float$ implements UFunc.UImpl {
      public static final Svd_DM_Impl_Float$ MODULE$ = new Svd_DM_Impl_Float$();

      public double apply$mcDD$sp(final double v) {
         return UFunc.UImpl.apply$mcDD$sp$(this, v);
      }

      public float apply$mcDF$sp(final double v) {
         return UFunc.UImpl.apply$mcDF$sp$(this, v);
      }

      public int apply$mcDI$sp(final double v) {
         return UFunc.UImpl.apply$mcDI$sp$(this, v);
      }

      public double apply$mcFD$sp(final float v) {
         return UFunc.UImpl.apply$mcFD$sp$(this, v);
      }

      public float apply$mcFF$sp(final float v) {
         return UFunc.UImpl.apply$mcFF$sp$(this, v);
      }

      public int apply$mcFI$sp(final float v) {
         return UFunc.UImpl.apply$mcFI$sp$(this, v);
      }

      public double apply$mcID$sp(final int v) {
         return UFunc.UImpl.apply$mcID$sp$(this, v);
      }

      public float apply$mcIF$sp(final int v) {
         return UFunc.UImpl.apply$mcIF$sp$(this, v);
      }

      public int apply$mcII$sp(final int v) {
         return UFunc.UImpl.apply$mcII$sp$(this, v);
      }

      public SVD apply(final DenseMatrix mat) {
         return svd$.MODULE$.breeze$linalg$svd$$doSVD_Float(mat, CompleteSVD$.MODULE$);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Svd_DM_Impl_Float$.class);
      }
   }

   public static class reduced$ implements UFunc {
      public static final reduced$ MODULE$ = new reduced$();

      static {
         UFunc.$init$(MODULE$);
      }

      public final Object apply(final Object v, final UFunc.UImpl impl) {
         return UFunc.apply$(this, v, impl);
      }

      public final double apply$mDDc$sp(final double v, final UFunc.UImpl impl) {
         return UFunc.apply$mDDc$sp$(this, v, impl);
      }

      public final float apply$mDFc$sp(final double v, final UFunc.UImpl impl) {
         return UFunc.apply$mDFc$sp$(this, v, impl);
      }

      public final int apply$mDIc$sp(final double v, final UFunc.UImpl impl) {
         return UFunc.apply$mDIc$sp$(this, v, impl);
      }

      public final double apply$mFDc$sp(final float v, final UFunc.UImpl impl) {
         return UFunc.apply$mFDc$sp$(this, v, impl);
      }

      public final float apply$mFFc$sp(final float v, final UFunc.UImpl impl) {
         return UFunc.apply$mFFc$sp$(this, v, impl);
      }

      public final int apply$mFIc$sp(final float v, final UFunc.UImpl impl) {
         return UFunc.apply$mFIc$sp$(this, v, impl);
      }

      public final double apply$mIDc$sp(final int v, final UFunc.UImpl impl) {
         return UFunc.apply$mIDc$sp$(this, v, impl);
      }

      public final float apply$mIFc$sp(final int v, final UFunc.UImpl impl) {
         return UFunc.apply$mIFc$sp$(this, v, impl);
      }

      public final int apply$mIIc$sp(final int v, final UFunc.UImpl impl) {
         return UFunc.apply$mIIc$sp$(this, v, impl);
      }

      public final Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$(this, v1, v2, impl);
      }

      public final double apply$mDDDc$sp(final double v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDDDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mDDFc$sp(final double v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDDFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mDDIc$sp(final double v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDDIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mDFDc$sp(final double v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDFDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mDFFc$sp(final double v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDFFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mDFIc$sp(final double v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDFIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mDIDc$sp(final double v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDIDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mDIFc$sp(final double v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDIFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mDIIc$sp(final double v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDIIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mFDDc$sp(final float v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFDDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mFDFc$sp(final float v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFDFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mFDIc$sp(final float v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFDIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mFFDc$sp(final float v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFFDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mFFFc$sp(final float v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFFFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mFFIc$sp(final float v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFFIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mFIDc$sp(final float v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFIDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mFIFc$sp(final float v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFIFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mFIIc$sp(final float v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFIIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mIDDc$sp(final int v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIDDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mIDFc$sp(final int v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIDFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mIDIc$sp(final int v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIDIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mIFDc$sp(final int v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIFDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mIFFc$sp(final int v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIFFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mIFIc$sp(final int v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIFIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mIIDc$sp(final int v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIIDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mIIFc$sp(final int v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIIFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mIIIc$sp(final int v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIIIc$sp$(this, v1, v2, impl);
      }

      public final Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$(this, v1, v2, v3, impl);
      }

      public final double apply$mDDDc$sp(final Object v1, final double v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDDDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mDDFc$sp(final Object v1, final double v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDDFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mDDIc$sp(final Object v1, final double v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDDIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mDFDc$sp(final Object v1, final double v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDFDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mDFFc$sp(final Object v1, final double v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDFFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mDFIc$sp(final Object v1, final double v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDFIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mDIDc$sp(final Object v1, final double v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDIDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mDIFc$sp(final Object v1, final double v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDIFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mDIIc$sp(final Object v1, final double v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDIIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mFDDc$sp(final Object v1, final float v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFDDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mFDFc$sp(final Object v1, final float v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFDFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mFDIc$sp(final Object v1, final float v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFDIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mFFDc$sp(final Object v1, final float v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFFDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mFFFc$sp(final Object v1, final float v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFFFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mFFIc$sp(final Object v1, final float v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFFIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mFIDc$sp(final Object v1, final float v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFIDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mFIFc$sp(final Object v1, final float v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFIFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mFIIc$sp(final Object v1, final float v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFIIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mIDDc$sp(final Object v1, final int v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIDDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mIDFc$sp(final Object v1, final int v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIDFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mIDIc$sp(final Object v1, final int v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIDIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mIFDc$sp(final Object v1, final int v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIFDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mIFFc$sp(final Object v1, final int v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIFFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mIFIc$sp(final Object v1, final int v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIFIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mIIDc$sp(final Object v1, final int v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIIDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mIIFc$sp(final Object v1, final int v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIIFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mIIIc$sp(final Object v1, final int v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIIIc$sp$(this, v1, v2, v3, impl);
      }

      public final Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
         return UFunc.apply$(this, v1, v2, v3, v4, impl);
      }

      public final Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
         return UFunc.inPlace$(this, v, impl);
      }

      public final Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
         return UFunc.inPlace$(this, v, v2, impl);
      }

      public final Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
         return UFunc.inPlace$(this, v, v2, v3, impl);
      }

      public final Object withSink(final Object s) {
         return UFunc.withSink$(this, s);
      }
   }

   public static class Svd_SM_Impl$ implements UFunc.UImpl2 {
      public static final Svd_SM_Impl$ MODULE$ = new Svd_SM_Impl$();

      public double apply$mcDDD$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
      }

      public float apply$mcDDF$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
      }

      public int apply$mcDDI$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
      }

      public double apply$mcDFD$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
      }

      public float apply$mcDFF$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
      }

      public int apply$mcDFI$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
      }

      public double apply$mcDID$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
      }

      public float apply$mcDIF$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
      }

      public int apply$mcDII$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
      }

      public double apply$mcFDD$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
      }

      public float apply$mcFDF$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
      }

      public int apply$mcFDI$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
      }

      public double apply$mcFFD$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
      }

      public float apply$mcFFF$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
      }

      public int apply$mcFFI$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
      }

      public double apply$mcFID$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
      }

      public float apply$mcFIF$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
      }

      public int apply$mcFII$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
      }

      public double apply$mcIDD$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
      }

      public float apply$mcIDF$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
      }

      public int apply$mcIDI$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
      }

      public double apply$mcIFD$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
      }

      public float apply$mcIFF$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
      }

      public int apply$mcIFI$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
      }

      public double apply$mcIID$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
      }

      public float apply$mcIIF$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
      }

      public int apply$mcIII$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
      }

      public SVD apply(final CSCMatrix mt, final int k) {
         double tol = 1.0E-6;
         if (k < mt.cols() && k < mt.rows()) {
            UFunc.UImpl3 svdImpl = svd$.MODULE$.Svd_Sparse_Impl(HasOps$.MODULE$.canMulM_DV_Double(), HasOps$.MODULE$.canTranspose_CSC(scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero(), Semiring$.MODULE$.semiringD()), HasOps$.MODULE$.canMulM_DV_Double(), CSCMatrix$.MODULE$.canDim());
            boolean isSlimMatrix = mt.rows() > mt.cols();
            SVD var9 = isSlimMatrix ? (SVD)svdImpl.apply(mt.t(HasOps$.MODULE$.canTranspose_CSC(scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero(), Semiring$.MODULE$.semiringD())), BoxesRunTime.boxToInteger(k), BoxesRunTime.boxToDouble(tol)) : (SVD)svdImpl.apply(mt, BoxesRunTime.boxToInteger(k), BoxesRunTime.boxToDouble(tol));
            if (var9 != null) {
               DenseMatrix u = (DenseMatrix)var9.leftVectors();
               DenseVector s = (DenseVector)var9.singularValues();
               DenseMatrix vt = (DenseMatrix)var9.rightVectors();
               Tuple3 var3 = new Tuple3(u, s, vt);
               DenseMatrix u = (DenseMatrix)var3._1();
               DenseVector s = (DenseVector)var3._2();
               DenseMatrix vt = (DenseMatrix)var3._3();
               return isSlimMatrix ? new SVD(vt.t(HasOps$.MODULE$.canTranspose_DM()), s, u.t(HasOps$.MODULE$.canTranspose_DM())) : new SVD(u, s, vt);
            } else {
               throw new MatchError(var9);
            }
         } else {
            throw new IllegalArgumentException("The desired number of singular values is greater than or equal to min(mt.cols, mt.rows). Please use the full svd.");
         }
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Svd_SM_Impl$.class);
      }
   }
}
