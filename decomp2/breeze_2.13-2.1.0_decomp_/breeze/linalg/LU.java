package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.operators.HasOps$;
import breeze.math.Semiring;
import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Iterator;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\r\u0015r!\u0002\u001b6\u0011\u0003Qd!\u0002\u001f6\u0011\u0003i\u0004\"\u0002&\u0002\t\u0003Ye\u0001\u0002\u001f\u0002\u00012C\u0001\"X\u0002\u0003\u0016\u0004%\tA\u0018\u0005\tU\u000e\u0011\t\u0012)A\u0005?\"A1n\u0001BK\u0002\u0013\u0005a\f\u0003\u0005m\u0007\tE\t\u0015!\u0003`\u0011!i7A!f\u0001\n\u0003q\u0006\u0002\u00038\u0004\u0005#\u0005\u000b\u0011B0\t\u000b)\u001bA\u0011A8\t\u000fU\u001c\u0011\u0011!C\u0001m\"9apAI\u0001\n\u0003y\b\"CA\r\u0007E\u0005I\u0011AA\u000e\u0011%\tybAI\u0001\n\u0003\t\t\u0003C\u0005\u0002&\r\t\t\u0011\"\u0011\u0002(!I\u0011\u0011H\u0002\u0002\u0002\u0013\u0005\u00111\b\u0005\n\u0003\u0007\u001a\u0011\u0011!C\u0001\u0003\u000bB\u0011\"a\u0013\u0004\u0003\u0003%\t%!\u0014\t\u0013\u0005m3!!A\u0005\u0002\u0005u\u0003\"CA4\u0007\u0005\u0005I\u0011IA5\u0011%\tigAA\u0001\n\u0003\ny\u0007C\u0005\u0002r\r\t\t\u0011\"\u0011\u0002t!I\u0011QO\u0002\u0002\u0002\u0013\u0005\u0013qO\u0004\ti\u0005\t\t\u0011#\u0001\u0002|\u0019AA(AA\u0001\u0012\u0003\ti\b\u0003\u0004K3\u0011\u0005\u0011\u0011\u0012\u0005\n\u0003cJ\u0012\u0011!C#\u0003gB\u0011\"a#\u001a\u0003\u0003%\t)!$\t\u0013\u0005u\u0015$!A\u0005\u0002\u0006}\u0005\"CA]3\u0005\u0005I\u0011BA^\u000b\u0019\t\u0019-\u0001\u0001\u0002F\"9\u0011Q[\u0001\u0005\u0004\u0005]\u0007b\u0002B@\u0003\u0011\r!\u0011Q\u0004\b\u0003o\f\u0001\u0012AA}\r\u001d\tY0\u0001E\u0001\u0003{DaAS\u0012\u0005\u0002\u0005}xa\u0002B\u0001G!\r!1\u0001\u0004\b\u0005\u000f\u0019\u0003\u0012\u0001B\u0005\u0011\u0019Qe\u0005\"\u0001\u0003\"!9\u00111\u0012\u0014\u0005\u0002\t\r\u0002\"CA]M\u0005\u0005I\u0011BA^\u0011\u001d\u0011Ic\tC\u0002\u0005W9qA!\u0011$\u0011\u0007\u0011\u0019EB\u0004\u0003F\rB\tAa\u0012\t\r)cC\u0011\u0001B+\u0011\u001d\tY\t\fC\u0001\u0005/B\u0011\"!/-\u0003\u0003%I!a/\t\u000f\tu\u0015\u0001\"\u0001\u0003 \"9!1Y\u0001\u0005\u0002\t\u0015\u0007b\u0002Br\u0003\u0011%!Q\u001d\u0005\b\u0005\u007f\fA\u0011BB\u0001\u0003\taUK\u0003\u00027o\u00051A.\u001b8bY\u001eT\u0011\u0001O\u0001\u0007EJ,WM_3\u0004\u0001A\u00111(A\u0007\u0002k\t\u0011A*V\n\u0004\u0003y\"\u0005CA C\u001b\u0005\u0001%\"A!\u0002\u000bM\u001c\u0017\r\\1\n\u0005\r\u0003%AB!osJ+g\r\u0005\u0002F\u00116\taI\u0003\u0002Ho\u00059q-\u001a8fe&\u001c\u0017BA%G\u0005\u0015)f)\u001e8d\u0003\u0019a\u0014N\\5u}Q\t!(\u0006\u0002NCN!1A\u0010(R!\tyt*\u0003\u0002Q\u0001\n9\u0001K]8ek\u000e$\bC\u0001*[\u001d\t\u0019\u0006L\u0004\u0002U/6\tQK\u0003\u0002Ws\u00051AH]8pizJ\u0011!Q\u0005\u00033\u0002\u000bq\u0001]1dW\u0006<W-\u0003\u0002\\9\na1+\u001a:jC2L'0\u00192mK*\u0011\u0011\fQ\u0001\u0002!V\tq\f\u0005\u0002aC2\u0001A!\u00022\u0004\u0005\u0004\u0019'!A'\u0012\u0005\u0011<\u0007CA f\u0013\t1\u0007IA\u0004O_RD\u0017N\\4\u0011\u0005}B\u0017BA5A\u0005\r\te._\u0001\u0003!\u0002\n\u0011\u0001T\u0001\u0003\u0019\u0002\n\u0011!V\u0001\u0003+\u0002\"B\u0001\u001d:tiB\u0019\u0011oA0\u000e\u0003\u0005AQ!\u0018\u0006A\u0002}CQa\u001b\u0006A\u0002}CQ!\u001c\u0006A\u0002}\u000bAaY8qsV\u0011qO\u001f\u000b\u0005qndX\u0010E\u0002r\u0007e\u0004\"\u0001\u0019>\u0005\u000b\t\\!\u0019A2\t\u000fu[\u0001\u0013!a\u0001s\"91n\u0003I\u0001\u0002\u0004I\bbB7\f!\u0003\u0005\r!_\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0011\t\t!a\u0006\u0016\u0005\u0005\r!fA0\u0002\u0006-\u0012\u0011q\u0001\t\u0005\u0003\u0013\t\u0019\"\u0004\u0002\u0002\f)!\u0011QBA\b\u0003%)hn\u00195fG.,GMC\u0002\u0002\u0012\u0001\u000b!\"\u00198o_R\fG/[8o\u0013\u0011\t)\"a\u0003\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\rB\u0003c\u0019\t\u00071-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\t\u0005\u0005\u0011Q\u0004\u0003\u0006E6\u0011\raY\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00134+\u0011\t\t!a\t\u0005\u000b\tt!\u0019A2\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\tI\u0003\u0005\u0003\u0002,\u0005URBAA\u0017\u0015\u0011\ty#!\r\u0002\t1\fgn\u001a\u0006\u0003\u0003g\tAA[1wC&!\u0011qGA\u0017\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5usV\u0011\u0011Q\b\t\u0004\u007f\u0005}\u0012bAA!\u0001\n\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0019q-a\u0012\t\u0013\u0005%\u0013#!AA\u0002\u0005u\u0012a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002PA)\u0011\u0011KA,O6\u0011\u00111\u000b\u0006\u0004\u0003+\u0002\u0015AC2pY2,7\r^5p]&!\u0011\u0011LA*\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005}\u0013Q\r\t\u0004\u007f\u0005\u0005\u0014bAA2\u0001\n9!i\\8mK\u0006t\u0007\u0002CA%'\u0005\u0005\t\u0019A4\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0003S\tY\u0007C\u0005\u0002JQ\t\t\u00111\u0001\u0002>\u0005A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002>\u0005AAo\\*ue&tw\r\u0006\u0002\u0002*\u00051Q-];bYN$B!a\u0018\u0002z!A\u0011\u0011J\f\u0002\u0002\u0003\u0007q\r\u0005\u0002r3M!\u0011DPA@!\u0011\t\t)a\"\u000e\u0005\u0005\r%\u0002BAC\u0003c\t!![8\n\u0007m\u000b\u0019\t\u0006\u0002\u0002|\u0005)\u0011\r\u001d9msV!\u0011qRAK)!\t\t*a&\u0002\u001a\u0006m\u0005\u0003B9\u0004\u0003'\u00032\u0001YAK\t\u0015\u0011GD1\u0001d\u0011\u0019iF\u00041\u0001\u0002\u0014\"11\u000e\ba\u0001\u0003'Ca!\u001c\u000fA\u0002\u0005M\u0015aB;oCB\u0004H._\u000b\u0005\u0003C\u000b\t\f\u0006\u0003\u0002$\u0006M\u0006#B \u0002&\u0006%\u0016bAAT\u0001\n1q\n\u001d;j_:\u0004\u0012bPAV\u0003_\u000by+a,\n\u0007\u00055\u0006I\u0001\u0004UkBdWm\r\t\u0004A\u0006EF!\u00022\u001e\u0005\u0004\u0019\u0007\"CA[;\u0005\u0005\t\u0019AA\\\u0003\rAH\u0005\r\t\u0005c\u000e\ty+\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002>B!\u00111FA`\u0013\u0011\t\t-!\f\u0003\r=\u0013'.Z2u\u0005\u001d!UM\\:f\u0019V+B!a2\u0002RB!\u0011oAAe!\u0015Y\u00141ZAh\u0013\r\ti-\u000e\u0002\f\t\u0016t7/Z'biJL\u0007\u0010E\u0002a\u0003#$a!a5 \u0005\u0004\u0019'!\u0001+\u00025\u0019\u0014x.\u001c)sS6LG/\u001b<f\t\u0016\u001cw.\u001c9pg&$\u0018n\u001c8\u0016\r\u0005e\u0017Q]Av)!\tY.a<\u0003`\t=\u0004cB9\u0002^\u0006\u0005\u0018q]\u0005\u0004\u0003?D%\u0001B%na2\u0004RaOAf\u0003G\u00042\u0001YAs\t\u0019\t\u0019\u000e\tb\u0001GB!\u0011oHAu!\r\u0001\u00171\u001e\u0003\u0007\u0003[\u0004#\u0019A2\u0003\u0003UCq!!=!\u0001\b\t\u00190\u0001\u0003qe&l\u0007\u0003CA{\u0003;\f\tOa\u0017\u000f\u0005E\u0014\u0013!\u00039sS6LG/\u001b<f!\t\t8EA\u0005qe&l\u0017\u000e^5wKN\u00191E\u0010#\u0015\u0005\u0005e\u0018!\u0005'V?\u0012ku,S7qY~#u.\u001e2mKB\u0019!Q\u0001\u0014\u000e\u0003\r\u0012\u0011\u0003T+`\t6{\u0016*\u001c9m?\u0012{WO\u00197f'\u00111cHa\u0003\u0011\u0011\t\u0015\u0011Q\u001cB\u0007\u0005+\u0001RaOAf\u0005\u001f\u00012a\u0010B\t\u0013\r\u0011\u0019\u0002\u0011\u0002\u0007\t>,(\r\\3\u0011\u000f}\u00129B!\u0004\u0003\u001c%\u0019!\u0011\u0004!\u0003\rQ+\b\u000f\\33!\u0015y$QDA\u001f\u0013\r\u0011y\u0002\u0011\u0002\u0006\u0003J\u0014\u0018-\u001f\u000b\u0003\u0005\u0007!BA!\u0006\u0003&!9!q\u0005\u0015A\u0002\t5\u0011!\u0001-\u0002-1+v\fR'`\u0007\u0006\u001cHoX%na2|Fi\\;cY\u0016,BA!\f\u00036Q!!q\u0006B\u001c!!\u0011)!!8\u00032\tU\u0001#B\u001e\u0002L\nM\u0002c\u00011\u00036\u00111\u00111\u001b\u0016C\u0002\rDqA!\u000f+\u0001\b\u0011Y$\u0001\u0003dCN$\bcB \u0003>\tM\"qB\u0005\u0004\u0005\u007f\u0001%!\u0003$v]\u000e$\u0018n\u001c82\u0003AaUk\u0018#N?&k\u0007\u000f\\0GY>\fG\u000fE\u0002\u0003\u00061\u0012\u0001\u0003T+`\t6{\u0016*\u001c9m?\u001acw.\u0019;\u0014\t1r$\u0011\n\t\t\u0005\u000b\tiNa\u0013\u0003TA)1(a3\u0003NA\u0019qHa\u0014\n\u0007\tE\u0003IA\u0003GY>\fG\u000fE\u0004@\u0005/\u0011YEa\u0007\u0015\u0005\t\rC\u0003\u0002B*\u00053BqAa\n/\u0001\u0004\u0011Y\u0005E\u0004@\u0005/\u0011iFa\u0007\u0011\u000bm\nY-!;\t\u000f\t\u0005\u0004\u0005q\u0001\u0003d\u0005\u00111\r\u001e\t\u0007\u0005K\u0012Y'!;\u000e\u0005\t\u001d$b\u0001B5\u0001\u00069!/\u001a4mK\u000e$\u0018\u0002\u0002B7\u0005O\u0012\u0001b\u00117bgN$\u0016m\u001a\u0005\b\u0005c\u0002\u00039\u0001B:\u0003\u0011\u0019X-\\5\u0011\r\tU$1PAu\u001b\t\u00119HC\u0002\u0003z]\nA!\\1uQ&!!Q\u0010B<\u0005!\u0019V-\\5sS:<\u0017\u0001\t4s_6\u0004&/[7ji&4X\rR3d_6\u0004xn]5uS>t7+[7qY\u0016,BAa!\u0003\fRA!Q\u0011BH\u0005+\u0013I\nE\u0004r\u0003;\u00149I!$\u0011\u000bm\nYM!#\u0011\u0007\u0001\u0014Y\t\u0002\u0004\u0002T\u0006\u0012\ra\u0019\t\u0005c~\u0011I\tC\u0004\u0002r\u0006\u0002\u001dA!%\u0011\u0011\u0005U\u0018Q\u001cBD\u0005'\u0003ra\u0010B\f\u0005\u000f\u0013Y\u0002C\u0004\u0003b\u0005\u0002\u001dAa&\u0011\r\t\u0015$1\u000eBE\u0011\u001d\u0011\t(\ta\u0002\u00057\u0003bA!\u001e\u0003|\t%\u0015aF2sK\u0006$X\rU3s[V$\u0018\r^5p]6\u000bGO]5y+\u0011\u0011\tK!+\u0015\u0011\t\r&q\u0017B^\u0005\u007f#bA!*\u0003,\nE\u0006#B\u001e\u0002L\n\u001d\u0006c\u00011\u0003*\u00121\u00111\u001b\u0019C\u0002\rD\u0011B!,1\u0003\u0003\u0005\u001dAa,\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007\u0005\u0004\u0003f\t-$q\u0015\u0005\n\u0005g\u0003\u0014\u0011!a\u0002\u0005k\u000b!\"\u001a<jI\u0016t7-\u001a\u00133!\u0019\u0011)Ha\u001f\u0003(\"9!\u0011\u0018\u0019A\u0002\tm\u0011\u0001B5qSZDqA!01\u0001\u0004\ti$\u0001\u0003s_^\u001c\bb\u0002Baa\u0001\u0007\u0011QH\u0001\u0005G>d7/A\u0005eK\u000e|W\u000e]8tKV!!q\u0019Bh)\u0019\u0011IM!8\u0003bR1!1\u001aBi\u0005/\u0004B!]\u0010\u0003NB\u0019\u0001Ma4\u0005\r\u0005M\u0017G1\u0001d\u0011%\u0011\u0019.MA\u0001\u0002\b\u0011).\u0001\u0006fm&$WM\\2fIM\u0002bA!\u001e\u0003|\t5\u0007\"\u0003Bmc\u0005\u0005\t9\u0001Bn\u0003))g/\u001b3f]\u000e,G\u0005\u000e\t\u0007\u0005K\u0012YG!4\t\u000f\t\u001d\u0012\u00071\u0001\u0003`B)1(a3\u0003N\"9!\u0011X\u0019A\u0002\tm\u0011a\u00047po\u0016\u0014HK]5b]\u001e,H.\u0019:\u0016\t\t\u001d(q\u001e\u000b\u0005\u0005S\u0014i\u0010\u0006\u0004\u0003l\nE(q\u001f\t\u0006w\u0005-'Q\u001e\t\u0004A\n=HABAje\t\u00071\rC\u0005\u0003tJ\n\t\u0011q\u0001\u0003v\u0006QQM^5eK:\u001cW\rJ\u001b\u0011\r\tU$1\u0010Bw\u0011%\u0011IPMA\u0001\u0002\b\u0011Y0\u0001\u0006fm&$WM\\2fIY\u0002bA!\u001a\u0003l\t5\bb\u0002B\u0014e\u0001\u0007!1^\u0001\u0010kB\u0004XM\u001d+sS\u0006tw-\u001e7beV!11AB\u0006)\u0011\u0019)aa\t\u0015\r\r\u001d1QBB\u000f!\u0015Y\u00141ZB\u0005!\r\u000171\u0002\u0003\u0007\u0003'\u001c$\u0019A2\t\u0013\r=1'!AA\u0004\rE\u0011AC3wS\u0012,gnY3%oA111CB\r\u0007\u0013i!a!\u0006\u000b\u0007\r]q'A\u0004ti>\u0014\u0018mZ3\n\t\rm1Q\u0003\u0002\u00055\u0016\u0014x\u000eC\u0005\u0004 M\n\t\u0011q\u0001\u0004\"\u0005QQM^5eK:\u001cW\r\n\u001d\u0011\r\t\u0015$1NB\u0005\u0011\u001d\u00119c\ra\u0001\u0007\u000f\u0001"
)
public final class LU {
   public static breeze.linalg.LU.LU decompose(final DenseMatrix X, final int[] ipiv, final Semiring evidence$3, final ClassTag evidence$4) {
      return breeze.linalg.LU$.MODULE$.decompose(X, ipiv, evidence$3, evidence$4);
   }

   public static DenseMatrix createPermutationMatrix(final int[] ipiv, final int rows, final int cols, final ClassTag evidence$1, final Semiring evidence$2) {
      return breeze.linalg.LU$.MODULE$.createPermutationMatrix(ipiv, rows, cols, evidence$1, evidence$2);
   }

   public static UFunc.UImpl fromPrimitiveDecompositionSimple(final UFunc.UImpl prim, final ClassTag ct, final Semiring semi) {
      return breeze.linalg.LU$.MODULE$.fromPrimitiveDecompositionSimple(prim, ct, semi);
   }

   public static UFunc.UImpl fromPrimitiveDecomposition(final UFunc.UImpl prim, final ClassTag ct, final Semiring semi) {
      return breeze.linalg.LU$.MODULE$.fromPrimitiveDecomposition(prim, ct, semi);
   }

   public static Object withSink(final Object s) {
      return breeze.linalg.LU$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return breeze.linalg.LU$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return breeze.linalg.LU$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return breeze.linalg.LU$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return breeze.linalg.LU$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return breeze.linalg.LU$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return breeze.linalg.LU$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return breeze.linalg.LU$.MODULE$.apply(v, impl);
   }

   public static class LU implements Product, Serializable {
      private final Object P;
      private final Object L;
      private final Object U;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Object P() {
         return this.P;
      }

      public Object L() {
         return this.L;
      }

      public Object U() {
         return this.U;
      }

      public breeze.linalg.LU.LU copy(final Object P, final Object L, final Object U) {
         return new breeze.linalg.LU.LU(P, L, U);
      }

      public Object copy$default$1() {
         return this.P();
      }

      public Object copy$default$2() {
         return this.L();
      }

      public Object copy$default$3() {
         return this.U();
      }

      public String productPrefix() {
         return "LU";
      }

      public int productArity() {
         return 3;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.P();
               break;
            case 1:
               var10000 = this.L();
               break;
            case 2:
               var10000 = this.U();
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
         return x$1 instanceof breeze.linalg.LU.LU;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "P";
               break;
            case 1:
               var10000 = "L";
               break;
            case 2:
               var10000 = "U";
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
               if (x$1 instanceof breeze.linalg.LU.LU) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  breeze.linalg.LU.LU var4 = (breeze.linalg.LU.LU)x$1;
                  if (BoxesRunTime.equals(this.P(), var4.P()) && BoxesRunTime.equals(this.L(), var4.L()) && BoxesRunTime.equals(this.U(), var4.U()) && var4.canEqual(this)) {
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

      public LU(final Object P, final Object L, final Object U) {
         this.P = P;
         this.L = L;
         this.U = U;
         Product.$init$(this);
      }
   }

   public static class LU$ implements Serializable {
      public static final breeze.linalg.LU.LU$ MODULE$ = new breeze.linalg.LU.LU$();

      public final String toString() {
         return "LU";
      }

      public breeze.linalg.LU.LU apply(final Object P, final Object L, final Object U) {
         return new breeze.linalg.LU.LU(P, L, U);
      }

      public Option unapply(final breeze.linalg.LU.LU x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(x$0.P(), x$0.L(), x$0.U())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(breeze.linalg.LU.LU$.class);
      }
   }

   public static class primitive$ implements UFunc {
      public static final breeze.linalg.LU.primitive$ MODULE$ = new breeze.linalg.LU.primitive$();

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

      public UFunc.UImpl LU_DM_Cast_Impl_Double(final Function1 cast) {
         return new UFunc.UImpl(cast) {
            private final Function1 cast$1;

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

            public Tuple2 apply(final DenseMatrix v) {
               return LU$primitive$LU_DM_Impl_Double$.MODULE$.apply((DenseMatrix)v.mapValues(this.cast$1, HasOps$.MODULE$.canMapValues_DM(scala.reflect.ClassTag..MODULE$.Double())));
            }

            public {
               this.cast$1 = cast$1;
            }
         };
      }
   }
}
