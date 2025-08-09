package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.operators.CounterOps;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanCreateZeros;
import breeze.linalg.support.CanCreateZerosLike;
import breeze.linalg.support.CanMapKeyValuePairs;
import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.CanTransformValues;
import breeze.linalg.support.CanTranspose;
import breeze.linalg.support.CanTraverseKeyValuePairs;
import breeze.linalg.support.CanTraverseValues;
import breeze.linalg.support.ScalarOf;
import breeze.linalg.support.TensorActive;
import breeze.linalg.support.TensorKeys;
import breeze.linalg.support.TensorPairs;
import breeze.linalg.support.TensorValues;
import breeze.math.Field;
import breeze.math.MutableEnumeratedCoordinateField;
import breeze.math.Ring;
import breeze.math.Semiring;
import breeze.storage.Zero;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.Set;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Seq;
import scala.collection.mutable.Map;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t=fa\u0002\r\u001a!\u0003\r\nAH\u0004\u0006\tfA\t!\u0012\u0004\u00061eA\tA\u0012\u0005\u0006+\n!\tA\u0016\u0005\u0006/\n!\t\u0001\u0017\u0005\u0006/\n!\t\u0001\u001b\u0005\u0007/\n!\t!a\u0002\t\u000f\u0005}\"\u0001\"\u0001\u0002B!9\u0011q\u000b\u0002\u0005\u0002\u0005ecABA4\u0005\u0001\tI\u0007\u0003\u0006\u0002x%\u0011)\u0019!C!\u0003sB!\"! \n\u0005\u0003\u0005\u000b\u0011BA>\u0011)\ty(\u0003B\u0001B\u0003-\u0011\u0011\u0011\u0005\u0007+&!\t!a!\t\u000f\u0005=\u0015\u0002\"\u0001\u0002\u0012\"9\u0011q\u0014\u0002\u0005\u0004\u0005\u0005\u0006bBAe\u0005\u0011\r\u00111\u001a\u0005\b\u0003?\u0014A1AAq\u0011\u001d\t)P\u0001C\u0002\u0003oDqAa\u0003\u0003\t\u0007\u0011i\u0001C\u0004\u0003@\t!\u0019A!\u0011\t\u000f\t\u0005$\u0001b\u0001\u0003d!9!1\u0011\u0002\u0005\u0004\t\u0015\u0005\"\u0003BP\u0005\u0005\u0005I\u0011\u0002BQ\u0005\u001d\u0019u.\u001e8uKJT!AG\u000e\u0002\r1Lg.\u00197h\u0015\u0005a\u0012A\u00022sK\u0016TXm\u0001\u0001\u0016\u0007}acg\u0005\u0003\u0001A\u0019B\u0004CA\u0011%\u001b\u0005\u0011#\"A\u0012\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0015\u0012#AB!osJ+g\r\u0005\u0003(Q)*T\"A\r\n\u0005%J\"A\u0002+f]N|'\u000f\u0005\u0002,Y1\u0001A!B\u0017\u0001\u0005\u0004q#!A&\u0012\u0005=\u0012\u0004CA\u00111\u0013\t\t$EA\u0004O_RD\u0017N\\4\u0011\u0005\u0005\u001a\u0014B\u0001\u001b#\u0005\r\te.\u001f\t\u0003WY\"Qa\u000e\u0001C\u00029\u0012\u0011A\u0016\t\u0007OeRSgO\"\n\u0005iJ\"aC\"pk:$XM\u001d'jW\u0016\u0004B\u0001P!+k5\tQH\u0003\u0002?\u007f\u00059Q.\u001e;bE2,'B\u0001!#\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003\u0005v\u00121!T1q!\u00119\u0003AK\u001b\u0002\u000f\r{WO\u001c;feB\u0011qEA\n\u0005\u0005\u0001:U\n\u0005\u0002I\u00176\t\u0011J\u0003\u0002K3\u0005Iq\u000e]3sCR|'o]\u0005\u0003\u0019&\u0013!bQ8v]R,'o\u00149t!\tq5+D\u0001P\u0015\t\u0001\u0016+\u0001\u0002j_*\t!+\u0001\u0003kCZ\f\u0017B\u0001+P\u00051\u0019VM]5bY&T\u0018M\u00197f\u0003\u0019a\u0014N\\5u}Q\tQ)A\u0003baBd\u00170F\u0002Z;~#\u0012A\u0017\u000b\u00037\u0002\u0004Ba\n\u0001]=B\u00111&\u0018\u0003\u0006[\u0011\u0011\rA\f\t\u0003W}#Qa\u000e\u0003C\u00029Bq!\u0019\u0003\u0002\u0002\u0003\u000f!-\u0001\u0006fm&$WM\\2fIE\u00022a\u00194_\u001b\u0005!'BA3\u001c\u0003\u001d\u0019Ho\u001c:bO\u0016L!a\u001a3\u0003\ti+'o\\\u000b\u0004S6|GC\u00016|)\rY\u0007o\u001d\t\u0005O\u0001ag\u000e\u0005\u0002,[\u0012)Q&\u0002b\u0001]A\u00111f\u001c\u0003\u0006o\u0015\u0011\rA\f\u0005\bc\u0016\t\t\u0011q\u0001s\u0003))g/\u001b3f]\u000e,GE\r\t\u0004G\u001at\u0007b\u0002;\u0006\u0003\u0003\u0005\u001d!^\u0001\u000bKZLG-\u001a8dK\u0012\u001a\u0004c\u0001<z]6\tqO\u0003\u0002y7\u0005!Q.\u0019;i\u0013\tQxO\u0001\u0005TK6L'/\u001b8h\u0011\u0015aX\u00011\u0001~\u0003\u00191\u0018\r\\;fgB!\u0011E`A\u0001\u0013\ty(E\u0001\u0006=e\u0016\u0004X-\u0019;fIz\u0002R!IA\u0002Y:L1!!\u0002#\u0005\u0019!V\u000f\u001d7feU1\u0011\u0011BA\t\u0003+!B!a\u0003\u0002$Q1\u0011QBA\f\u0003;\u0001ba\n\u0001\u0002\u0010\u0005M\u0001cA\u0016\u0002\u0012\u0011)QF\u0002b\u0001]A\u00191&!\u0006\u0005\u000b]2!\u0019\u0001\u0018\t\u0013\u0005ea!!AA\u0004\u0005m\u0011AC3wS\u0012,gnY3%iA!1MZA\n\u0011%\tyBBA\u0001\u0002\b\t\t#\u0001\u0006fm&$WM\\2fIU\u0002BA^=\u0002\u0014!1AP\u0002a\u0001\u0003K\u0001b!a\n\u00028\u0005ub\u0002BA\u0015\u0003gqA!a\u000b\u000225\u0011\u0011Q\u0006\u0006\u0004\u0003_i\u0012A\u0002\u001fs_>$h(C\u0001$\u0013\r\t)DI\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\tI$a\u000f\u0003\u001fQ\u0013\u0018M^3sg\u0006\u0014G.Z(oG\u0016T1!!\u000e#!\u001d\t\u00131AA\b\u0003'\t\u0001cY8v]R$&/\u0019<feN\f'\r\\3\u0016\t\u0005\r\u0013\u0011\n\u000b\u0005\u0003\u000b\n\t\u0006\u0005\u0004(\u0001\u0005\u001d\u00131\n\t\u0004W\u0005%C!B\u0017\b\u0005\u0004q\u0003cA\u0011\u0002N%\u0019\u0011q\n\u0012\u0003\u0007%sG\u000fC\u0004\u0002T\u001d\u0001\r!!\u0016\u0002\u000b%$X-\\:\u0011\r\u0005\u001d\u0012qGA$\u0003\u0015\u0019w.\u001e8u+\u0011\tY&!\u0019\u0015\t\u0005u\u00131\r\t\u0007O\u0001\ty&a\u0013\u0011\u0007-\n\t\u0007B\u0003.\u0011\t\u0007a\u0006C\u0004\u0002T!\u0001\r!!\u001a\u0011\t\u0005r\u0018q\f\u0002\u0005\u00136\u0004H.\u0006\u0004\u0002l\u0005E\u0014QO\n\u0005\u0013\u0001\ni\u0007\u0005\u0004(\u0001\u0005=\u00141\u000f\t\u0004W\u0005ED!B\u0017\n\u0005\u0004q\u0003cA\u0016\u0002v\u0011)q'\u0003b\u0001]\u0005!A-\u0019;b+\t\tY\b\u0005\u0004=\u0003\u0006=\u00141O\u0001\u0006I\u0006$\u0018\rI\u0001\u0005u\u0016\u0014x\u000e\u0005\u0003dM\u0006MD\u0003BAC\u0003\u001b#B!a\"\u0002\fB9\u0011\u0011R\u0005\u0002p\u0005MT\"\u0001\u0002\t\u000f\u0005}T\u0002q\u0001\u0002\u0002\"9\u0011qO\u0007A\u0002\u0005m\u0014a\u00023fM\u0006,H\u000e^\u000b\u0003\u0003gBs!CAK\u00037\u000bi\nE\u0002\"\u0003/K1!!'#\u0005A\u0019VM]5bYZ+'o]5p]VKE)A\u0003wC2,XM\b\u0005(9lHQh-d!\u00041\u0019\u0017M\\'baZ\u000bG.^3t+!\t\u0019+!.\u0002:\u0006uF\u0003BAS\u0003\u0007\u0004B\"a*\u0002.\u0006E\u0016qWA^\u0003\u0003l!!!+\u000b\u0007\u0005-\u0016$A\u0004tkB\u0004xN\u001d;\n\t\u0005=\u0016\u0011\u0016\u0002\r\u0007\u0006tW*\u00199WC2,Xm\u001d\t\u0007O\u0001\t\u0019,a.\u0011\u0007-\n)\fB\u0003.\u001f\t\u0007a\u0006E\u0002,\u0003s#QaN\bC\u00029\u00022aKA_\t\u0019\tyl\u0004b\u0001]\t\u0011!K\u0016\t\u0007O\u0001\t\u0019,a/\t\u0013\u0005\u0015w\"!AA\u0004\u0005\u001d\u0017AC3wS\u0012,gnY3%mA!1MZA^\u0003A\u0019\u0017M\\%uKJ\fG/\u001a,bYV,7/\u0006\u0004\u0002N\u0006e\u0017Q\\\u000b\u0003\u0003\u001f\u0004\u0002\"a*\u0002R\u0006U\u00171\\\u0005\u0005\u0003'\fIKA\tDC:$&/\u0019<feN,g+\u00197vKN\u0004ba\n\u0001\u0002X\u0006m\u0007cA\u0016\u0002Z\u0012)Q\u0006\u0005b\u0001]A\u00191&!8\u0005\u000b]\u0002\"\u0019\u0001\u0018\u0002\u0011M\u001c\u0017\r\\1s\u001f\u001a,b!a9\u0002p\u0006MXCAAs!!\t9+a:\u0002l\u0006E\u0018\u0002BAu\u0003S\u0013\u0001bU2bY\u0006\u0014xJ\u001a\t\u0007O\u0001\ti/!=\u0011\u0007-\ny\u000fB\u0003.#\t\u0007a\u0006E\u0002,\u0003g$QaN\tC\u00029\n\u0001dY1o)J\fg/\u001a:tK.+\u0017PV1mk\u0016\u0004\u0016-\u001b:t+\u0019\tIP!\u0002\u0003\nU\u0011\u00111 \t\u000b\u0003O\u000biP!\u0001\u0003\u0004\t\u001d\u0011\u0002BA\u0000\u0003S\u0013\u0001dQ1o)J\fg/\u001a:tK.+\u0017PV1mk\u0016\u0004\u0016-\u001b:t!\u00199\u0003Aa\u0001\u0003\bA\u00191F!\u0002\u0005\u000b5\u0012\"\u0019\u0001\u0018\u0011\u0007-\u0012I\u0001B\u00038%\t\u0007a&\u0001\bo_Jl\u0017*\u001c9m\t>,(\r\\3\u0016\r\t=!\u0011\u0006B\u0017)\u0011\u0011\tB!\u000e\u0011\u0015\tM!\u0011\u0004B\u0013\u0005_\u0011yCD\u0002(\u0005+I1Aa\u0006\u001a\u0003\u0011qwN]7\n\t\tm!Q\u0004\u0002\u0006\u00136\u0004HNM\u0005\u0005\u0005?\u0011\tCA\u0003V\rVt7MC\u0002\u0003$m\tqaZ3oKJL7\r\u0005\u0004(\u0001\t\u001d\"1\u0006\t\u0004W\t%B!B\u0017\u0014\u0005\u0004q\u0003cA\u0016\u0003.\u0011)qg\u0005b\u0001]A\u0019\u0011E!\r\n\u0007\tM\"E\u0001\u0004E_V\u0014G.\u001a\u0005\n\u0005o\u0019\u0012\u0011!a\u0002\u0005s\t!\"\u001a<jI\u0016t7-\u001a\u00138!\u00151(1\bB\u0016\u0013\r\u0011id\u001e\u0002\u0006\r&,G\u000eZ\u0001\u000fG\u0006t7I]3bi\u0016TVM]8t+\u0019\u0011\u0019Ea\u0014\u0003TQ1!Q\tB+\u00057\u0002\u0002\"a*\u0003H\t-#QJ\u0005\u0005\u0005\u0013\nIK\u0001\bDC:\u001c%/Z1uKj+'o\\:\u0011\r\u001d\u0002!Q\nB)!\rY#q\n\u0003\u0006[Q\u0011\rA\f\t\u0004W\tMC!B\u001c\u0015\u0005\u0004q\u0003\"\u0003B,)\u0005\u0005\t9\u0001B-\u0003))g/\u001b3f]\u000e,G\u0005\u000f\t\u0005G\u001a\u0014\t\u0006C\u0005\u0003^Q\t\t\u0011q\u0001\u0003`\u0005QQM^5eK:\u001cW\rJ\u001d\u0011\tYL(\u0011K\u0001\u0013G\u0006t7I]3bi\u0016TVM]8t\u0019&\\W-\u0006\u0004\u0003f\tE$Q\u000f\u000b\u0007\u0005O\u00129H! \u0011\u0011\u0005\u001d&\u0011\u000eB7\u0005[JAAa\u001b\u0002*\n\u00112)\u00198De\u0016\fG/\u001a.fe>\u001cH*[6f!\u00199\u0003Aa\u001c\u0003tA\u00191F!\u001d\u0005\u000b5*\"\u0019\u0001\u0018\u0011\u0007-\u0012)\bB\u00038+\t\u0007a\u0006C\u0005\u0003zU\t\t\u0011q\u0001\u0003|\u0005YQM^5eK:\u001cW\rJ\u00191!\u0011\u0019gMa\u001d\t\u0013\t}T#!AA\u0004\t\u0005\u0015aC3wS\u0012,gnY3%cE\u0002BA^=\u0003t\u0005)1\u000f]1dKV1!q\u0011BJ\u0005/#BA!#\u0003\u001aBIaOa#\u0003\u0010\nE%QS\u0005\u0004\u0005\u001b;(\u0001I'vi\u0006\u0014G.Z#ok6,'/\u0019;fI\u000e{wN\u001d3j]\u0006$XMR5fY\u0012\u0004ba\n\u0001\u0003\u0012\nU\u0005cA\u0016\u0003\u0014\u0012)QF\u0006b\u0001]A\u00191Fa&\u0005\u000b]2\"\u0019\u0001\u0018\t\u000f\tme\u0003q\u0001\u0003\u001e\u0006)a-[3mIB)aOa\u000f\u0003\u0016\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u0011!1\u0015\t\u0005\u0005K\u0013Y+\u0004\u0002\u0003(*\u0019!\u0011V)\u0002\t1\fgnZ\u0005\u0005\u0005[\u00139K\u0001\u0004PE*,7\r\u001e"
)
public interface Counter extends Tensor, CounterLike {
   static MutableEnumeratedCoordinateField space(final Field field) {
      return Counter$.MODULE$.space(field);
   }

   static CanCreateZerosLike canCreateZerosLike(final Zero evidence$10, final Semiring evidence$11) {
      return Counter$.MODULE$.canCreateZerosLike(evidence$10, evidence$11);
   }

   static CanCreateZeros canCreateZeros(final Zero evidence$8, final Semiring evidence$9) {
      return Counter$.MODULE$.canCreateZeros(evidence$8, evidence$9);
   }

   static UFunc.UImpl2 normImplDouble(final Field evidence$7) {
      return Counter$.MODULE$.normImplDouble(evidence$7);
   }

   static CanTraverseKeyValuePairs canTraverseKeyValuePairs() {
      return Counter$.MODULE$.canTraverseKeyValuePairs();
   }

   static ScalarOf scalarOf() {
      return Counter$.MODULE$.scalarOf();
   }

   static CanTraverseValues canIterateValues() {
      return Counter$.MODULE$.canIterateValues();
   }

   static CanMapValues canMapValues(final Zero evidence$6) {
      return Counter$.MODULE$.canMapValues(evidence$6);
   }

   static Counter count(final Seq items) {
      return Counter$.MODULE$.count(items);
   }

   static Counter countTraversable(final IterableOnce items) {
      return Counter$.MODULE$.countTraversable(items);
   }

   static CanTraverseValues canTraverseValues() {
      return Counter$.MODULE$.canTraverseValues();
   }

   static CanTransformValues canTransformValues() {
      return Counter$.MODULE$.canTransformValues();
   }

   static CounterOps.CanZipMapKeyValuesCounter zipMapKeyValues(final Zero evidence$27, final Semiring evidence$28) {
      return Counter$.MODULE$.zipMapKeyValues(evidence$27, evidence$28);
   }

   static CounterOps.CanZipMapValuesCounter zipMap(final Zero evidence$23, final Semiring evidence$24) {
      return Counter$.MODULE$.zipMap(evidence$23, evidence$24);
   }

   static UFunc.UImpl2 canNorm(final UFunc.UImpl normImpl) {
      return Counter$.MODULE$.canNorm(normImpl);
   }

   static UFunc.UImpl2 canMulInner(final CanCopy copy, final Semiring semiring) {
      return Counter$.MODULE$.canMulInner(copy, semiring);
   }

   static UFunc.UImpl canNegate(final Ring ring) {
      return Counter$.MODULE$.canNegate(ring);
   }

   static UFunc.InPlaceImpl2 canSetIntoVS() {
      return Counter$.MODULE$.canSetIntoVS();
   }

   static UFunc.InPlaceImpl2 impl_OpSet_InPlace_C_C() {
      return Counter$.MODULE$.impl_OpSet_InPlace_C_C();
   }

   static UFunc.InPlaceImpl2 canDivIntoVS(final Field evidence$20) {
      return Counter$.MODULE$.canDivIntoVS(evidence$20);
   }

   static UFunc.UImpl2 canDivVS(final CanCopy copy, final Field semiring) {
      return Counter$.MODULE$.canDivVS(copy, semiring);
   }

   static UFunc.UImpl2 canDivVV(final CanCopy copy, final Field semiring) {
      return Counter$.MODULE$.canDivVV(copy, semiring);
   }

   static UFunc.InPlaceImpl2 canDivIntoVV(final Field evidence$19) {
      return Counter$.MODULE$.canDivIntoVV(evidence$19);
   }

   static UFunc.UImpl2 canMulVS_M(final Semiring semiring) {
      return Counter$.MODULE$.canMulVS_M(semiring);
   }

   static UFunc.UImpl2 canMulVS(final Semiring semiring) {
      return Counter$.MODULE$.canMulVS(semiring);
   }

   static UFunc.InPlaceImpl2 canMulIntoVS_M(final Semiring evidence$18) {
      return Counter$.MODULE$.canMulIntoVS_M(evidence$18);
   }

   static UFunc.InPlaceImpl2 canMulIntoVS(final Semiring evidence$17) {
      return Counter$.MODULE$.canMulIntoVS(evidence$17);
   }

   static UFunc.UImpl2 canMulVV(final Semiring semiring) {
      return Counter$.MODULE$.canMulVV(semiring);
   }

   static UFunc.InPlaceImpl2 canMulIntoVV(final Semiring evidence$16) {
      return Counter$.MODULE$.canMulIntoVV(evidence$16);
   }

   static UFunc.UImpl2 subVS(final Ring evidence$14, final Zero evidence$15) {
      return Counter$.MODULE$.subVS(evidence$14, evidence$15);
   }

   static UFunc.InPlaceImpl2 subIntoVS(final Ring evidence$13) {
      return Counter$.MODULE$.subIntoVS(evidence$13);
   }

   static UFunc.UImpl2 subVV(final Ring evidence$11, final Zero evidence$12) {
      return Counter$.MODULE$.subVV(evidence$11, evidence$12);
   }

   static UFunc.InPlaceImpl2 subIntoVV(final Ring evidence$10) {
      return Counter$.MODULE$.subIntoVV(evidence$10);
   }

   static UFunc.UImpl2 addVS(final Semiring evidence$8, final Zero evidence$9) {
      return Counter$.MODULE$.addVS(evidence$8, evidence$9);
   }

   static UFunc.InPlaceImpl2 addIntoVS(final Semiring evidence$7) {
      return Counter$.MODULE$.addIntoVS(evidence$7);
   }

   static UFunc.UImpl2 addVV(final Semiring evidence$5, final Zero evidence$6) {
      return Counter$.MODULE$.addVV(evidence$5, evidence$6);
   }

   static UFunc.InPlaceImpl3 canAxpy(final Semiring evidence$4) {
      return Counter$.MODULE$.canAxpy(evidence$4);
   }

   static UFunc.InPlaceImpl2 addIntoVV(final Semiring evidence$3) {
      return Counter$.MODULE$.addIntoVV(evidence$3);
   }

   static UFunc.UImpl2 binaryOpFromBinaryUpdateOp(final CanCopy copy, final UFunc.InPlaceImpl2 op) {
      return Counter$.MODULE$.binaryOpFromBinaryUpdateOp(copy, op);
   }

   static CanCopy canCopy(final Zero evidence$1, final Semiring evidence$2) {
      return Counter$.MODULE$.canCopy(evidence$1, evidence$2);
   }

   public static class Impl implements Counter {
      private static final long serialVersionUID = 2872445575657408160L;
      private final Map data;
      private final Zero zero;

      public Set keySet() {
         return CounterLike.keySet$(this);
      }

      public Counter repr() {
         return CounterLike.repr$(this);
      }

      public int size() {
         return CounterLike.size$(this);
      }

      public int activeSize() {
         return CounterLike.activeSize$(this);
      }

      public boolean isEmpty() {
         return CounterLike.isEmpty$(this);
      }

      public boolean contains(final Object k) {
         return CounterLike.contains$(this, k);
      }

      public Object apply(final Object k) {
         return CounterLike.apply$(this, k);
      }

      public void update(final Object k, final Object v) {
         CounterLike.update$(this, k, v);
      }

      public Option get(final Object k) {
         return CounterLike.get$(this, k);
      }

      public Iterator keysIterator() {
         return CounterLike.keysIterator$(this);
      }

      public Iterator valuesIterator() {
         return CounterLike.valuesIterator$(this);
      }

      public Iterator iterator() {
         return CounterLike.iterator$(this);
      }

      public Iterator activeIterator() {
         return CounterLike.activeIterator$(this);
      }

      public Iterator activeValuesIterator() {
         return CounterLike.activeValuesIterator$(this);
      }

      public Iterator activeKeysIterator() {
         return CounterLike.activeKeysIterator$(this);
      }

      public String toString() {
         return CounterLike.toString$(this);
      }

      public boolean equals(final Object p1) {
         return CounterLike.equals$(this, p1);
      }

      public int hashCode() {
         return CounterLike.hashCode$(this);
      }

      public scala.collection.immutable.Map toMap() {
         return CounterLike.toMap$(this);
      }

      public double apply$mcID$sp(final int i) {
         return TensorLike.apply$mcID$sp$(this, i);
      }

      public float apply$mcIF$sp(final int i) {
         return TensorLike.apply$mcIF$sp$(this, i);
      }

      public int apply$mcII$sp(final int i) {
         return TensorLike.apply$mcII$sp$(this, i);
      }

      public long apply$mcIJ$sp(final int i) {
         return TensorLike.apply$mcIJ$sp$(this, i);
      }

      public void update$mcID$sp(final int i, final double v) {
         TensorLike.update$mcID$sp$(this, i, v);
      }

      public void update$mcIF$sp(final int i, final float v) {
         TensorLike.update$mcIF$sp$(this, i, v);
      }

      public void update$mcII$sp(final int i, final int v) {
         TensorLike.update$mcII$sp$(this, i, v);
      }

      public void update$mcIJ$sp(final int i, final long v) {
         TensorLike.update$mcIJ$sp$(this, i, v);
      }

      public TensorKeys keys() {
         return TensorLike.keys$(this);
      }

      public TensorValues values() {
         return TensorLike.values$(this);
      }

      public TensorPairs pairs() {
         return TensorLike.pairs$(this);
      }

      public TensorActive active() {
         return TensorLike.active$(this);
      }

      public Object apply(final Object slice, final CanSlice canSlice) {
         return TensorLike.apply$(this, slice, canSlice);
      }

      public Object apply(final Object a, final Object b, final Object c, final Seq slice, final CanSlice canSlice) {
         return TensorLike.apply$(this, a, b, c, slice, canSlice);
      }

      public Object apply$mcI$sp(final int a, final int b, final int c, final Seq slice, final CanSlice canSlice) {
         return TensorLike.apply$mcI$sp$(this, a, b, c, slice, canSlice);
      }

      public Object apply(final Object slice1, final Object slice2, final CanSlice2 canSlice) {
         return TensorLike.apply$(this, slice1, slice2, canSlice);
      }

      public Object mapPairs(final Function2 f, final CanMapKeyValuePairs bf) {
         return TensorLike.mapPairs$(this, f, bf);
      }

      public Object mapPairs$mcID$sp(final Function2 f, final CanMapKeyValuePairs bf) {
         return TensorLike.mapPairs$mcID$sp$(this, f, bf);
      }

      public Object mapPairs$mcIF$sp(final Function2 f, final CanMapKeyValuePairs bf) {
         return TensorLike.mapPairs$mcIF$sp$(this, f, bf);
      }

      public Object mapPairs$mcII$sp(final Function2 f, final CanMapKeyValuePairs bf) {
         return TensorLike.mapPairs$mcII$sp$(this, f, bf);
      }

      public Object mapPairs$mcIJ$sp(final Function2 f, final CanMapKeyValuePairs bf) {
         return TensorLike.mapPairs$mcIJ$sp$(this, f, bf);
      }

      public Object mapActivePairs(final Function2 f, final CanMapKeyValuePairs bf) {
         return TensorLike.mapActivePairs$(this, f, bf);
      }

      public Object mapActivePairs$mcID$sp(final Function2 f, final CanMapKeyValuePairs bf) {
         return TensorLike.mapActivePairs$mcID$sp$(this, f, bf);
      }

      public Object mapActivePairs$mcIF$sp(final Function2 f, final CanMapKeyValuePairs bf) {
         return TensorLike.mapActivePairs$mcIF$sp$(this, f, bf);
      }

      public Object mapActivePairs$mcII$sp(final Function2 f, final CanMapKeyValuePairs bf) {
         return TensorLike.mapActivePairs$mcII$sp$(this, f, bf);
      }

      public Object mapActivePairs$mcIJ$sp(final Function2 f, final CanMapKeyValuePairs bf) {
         return TensorLike.mapActivePairs$mcIJ$sp$(this, f, bf);
      }

      public Object mapValues(final Function1 f, final CanMapValues bf) {
         return TensorLike.mapValues$(this, f, bf);
      }

      public Object mapValues$mcD$sp(final Function1 f, final CanMapValues bf) {
         return TensorLike.mapValues$mcD$sp$(this, f, bf);
      }

      public Object mapValues$mcF$sp(final Function1 f, final CanMapValues bf) {
         return TensorLike.mapValues$mcF$sp$(this, f, bf);
      }

      public Object mapValues$mcI$sp(final Function1 f, final CanMapValues bf) {
         return TensorLike.mapValues$mcI$sp$(this, f, bf);
      }

      public Object mapValues$mcJ$sp(final Function1 f, final CanMapValues bf) {
         return TensorLike.mapValues$mcJ$sp$(this, f, bf);
      }

      public Object mapActiveValues(final Function1 f, final CanMapValues bf) {
         return TensorLike.mapActiveValues$(this, f, bf);
      }

      public Object mapActiveValues$mcD$sp(final Function1 f, final CanMapValues bf) {
         return TensorLike.mapActiveValues$mcD$sp$(this, f, bf);
      }

      public Object mapActiveValues$mcF$sp(final Function1 f, final CanMapValues bf) {
         return TensorLike.mapActiveValues$mcF$sp$(this, f, bf);
      }

      public Object mapActiveValues$mcI$sp(final Function1 f, final CanMapValues bf) {
         return TensorLike.mapActiveValues$mcI$sp$(this, f, bf);
      }

      public Object mapActiveValues$mcJ$sp(final Function1 f, final CanMapValues bf) {
         return TensorLike.mapActiveValues$mcJ$sp$(this, f, bf);
      }

      public void foreachKey(final Function1 fn) {
         TensorLike.foreachKey$(this, fn);
      }

      public void foreachKey$mcI$sp(final Function1 fn) {
         TensorLike.foreachKey$mcI$sp$(this, fn);
      }

      public void foreachPair(final Function2 fn) {
         TensorLike.foreachPair$(this, fn);
      }

      public void foreachPair$mcID$sp(final Function2 fn) {
         TensorLike.foreachPair$mcID$sp$(this, fn);
      }

      public void foreachPair$mcIF$sp(final Function2 fn) {
         TensorLike.foreachPair$mcIF$sp$(this, fn);
      }

      public void foreachPair$mcII$sp(final Function2 fn) {
         TensorLike.foreachPair$mcII$sp$(this, fn);
      }

      public void foreachPair$mcIJ$sp(final Function2 fn) {
         TensorLike.foreachPair$mcIJ$sp$(this, fn);
      }

      public void foreachValue(final Function1 fn) {
         TensorLike.foreachValue$(this, fn);
      }

      public void foreachValue$mcD$sp(final Function1 fn) {
         TensorLike.foreachValue$mcD$sp$(this, fn);
      }

      public void foreachValue$mcF$sp(final Function1 fn) {
         TensorLike.foreachValue$mcF$sp$(this, fn);
      }

      public void foreachValue$mcI$sp(final Function1 fn) {
         TensorLike.foreachValue$mcI$sp$(this, fn);
      }

      public void foreachValue$mcJ$sp(final Function1 fn) {
         TensorLike.foreachValue$mcJ$sp$(this, fn);
      }

      public boolean forall(final Function2 fn) {
         return TensorLike.forall$(this, (Function2)fn);
      }

      public boolean forall$mcID$sp(final Function2 fn) {
         return TensorLike.forall$mcID$sp$(this, fn);
      }

      public boolean forall$mcIF$sp(final Function2 fn) {
         return TensorLike.forall$mcIF$sp$(this, fn);
      }

      public boolean forall$mcII$sp(final Function2 fn) {
         return TensorLike.forall$mcII$sp$(this, fn);
      }

      public boolean forall$mcIJ$sp(final Function2 fn) {
         return TensorLike.forall$mcIJ$sp$(this, fn);
      }

      public boolean forall(final Function1 fn) {
         return TensorLike.forall$(this, (Function1)fn);
      }

      public boolean forall$mcD$sp(final Function1 fn) {
         return TensorLike.forall$mcD$sp$(this, fn);
      }

      public boolean forall$mcF$sp(final Function1 fn) {
         return TensorLike.forall$mcF$sp$(this, fn);
      }

      public boolean forall$mcI$sp(final Function1 fn) {
         return TensorLike.forall$mcI$sp$(this, fn);
      }

      public boolean forall$mcJ$sp(final Function1 fn) {
         return TensorLike.forall$mcJ$sp$(this, fn);
      }

      public final Object $plus(final Object b, final UFunc.UImpl2 op) {
         return NumericOps.$plus$(this, b, op);
      }

      public final Object $colon$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$eq$(this, b, op);
      }

      public final Object $colon$plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$plus$eq$(this, b, op);
      }

      public final Object $colon$times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$times$eq$(this, b, op);
      }

      public final Object $plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$plus$eq$(this, b, op);
      }

      public final Object $times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$times$eq$(this, b, op);
      }

      public final Object $colon$minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$minus$eq$(this, b, op);
      }

      public final Object $colon$percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$percent$eq$(this, b, op);
      }

      public final Object $percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$percent$eq$(this, b, op);
      }

      public final Object $minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$minus$eq$(this, b, op);
      }

      public final Object $colon$div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$div$eq$(this, b, op);
      }

      public final Object $colon$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$up$eq$(this, b, op);
      }

      public final Object $div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$div$eq$(this, b, op);
      }

      public final Object $less$colon$less(final Object b, final UFunc.UImpl2 op) {
         return NumericOps.$less$colon$less$(this, b, op);
      }

      public final Object $less$colon$eq(final Object b, final UFunc.UImpl2 op) {
         return NumericOps.$less$colon$eq$(this, b, op);
      }

      public final Object $greater$colon$greater(final Object b, final UFunc.UImpl2 op) {
         return NumericOps.$greater$colon$greater$(this, b, op);
      }

      public final Object $greater$colon$eq(final Object b, final UFunc.UImpl2 op) {
         return NumericOps.$greater$colon$eq$(this, b, op);
      }

      public final Object $colon$amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$amp$eq$(this, b, op);
      }

      public final Object $colon$bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$bar$eq$(this, b, op);
      }

      public final Object $colon$up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$up$up$eq$(this, b, op);
      }

      public final Object $amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$amp$eq$(this, b, op);
      }

      public final Object $bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$bar$eq$(this, b, op);
      }

      public final Object $up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$up$up$eq$(this, b, op);
      }

      public final Object $plus$colon$plus(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$plus$colon$plus$(this, b, op);
      }

      public final Object $times$colon$times(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$times$colon$times$(this, b, op);
      }

      public final Object $colon$eq$eq(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$colon$eq$eq$(this, b, op);
      }

      public final Object $colon$bang$eq(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$colon$bang$eq$(this, b, op);
      }

      public final Object unary_$minus(final UFunc.UImpl op) {
         return ImmutableNumericOps.unary_$minus$(this, op);
      }

      public final Object $minus$colon$minus(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$minus$colon$minus$(this, b, op);
      }

      public final Object $minus(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$minus$(this, b, op);
      }

      public final Object $percent$colon$percent(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$percent$colon$percent$(this, b, op);
      }

      public final Object $percent(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$percent$(this, b, op);
      }

      public final Object $div$colon$div(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$div$colon$div$(this, b, op);
      }

      public final Object $div(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$div$(this, b, op);
      }

      public final Object $up$colon$up(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$up$colon$up$(this, b, op);
      }

      public final Object dot(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.dot$(this, b, op);
      }

      public final Object unary_$bang(final UFunc.UImpl op) {
         return ImmutableNumericOps.unary_$bang$(this, op);
      }

      public final Object $amp$colon$amp(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$amp$colon$amp$(this, b, op);
      }

      public final Object $bar$colon$bar(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$bar$colon$bar$(this, b, op);
      }

      public final Object $up$up$colon$up$up(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$up$up$colon$up$up$(this, b, op);
      }

      public final Object $amp(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$amp$(this, b, op);
      }

      public final Object $bar(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$bar$(this, b, op);
      }

      public final Object $up$up(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$up$up$(this, b, op);
      }

      public final Object $times(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$times$(this, b, op);
      }

      public final Object t(final CanTranspose op) {
         return ImmutableNumericOps.t$(this, op);
      }

      public Object $bslash(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$bslash$(this, b, op);
      }

      public final Object t(final Object a, final Object b, final CanTranspose op, final CanSlice2 canSlice) {
         return ImmutableNumericOps.t$(this, a, b, op, canSlice);
      }

      public final Object t(final Object a, final CanTranspose op, final CanSlice canSlice) {
         return ImmutableNumericOps.t$(this, a, op, canSlice);
      }

      public IndexedSeq findAll(final Function1 f) {
         return QuasiTensor.findAll$(this, f);
      }

      public IndexedSeq findAll$mcD$sp(final Function1 f) {
         return QuasiTensor.findAll$mcD$sp$(this, f);
      }

      public IndexedSeq findAll$mcF$sp(final Function1 f) {
         return QuasiTensor.findAll$mcF$sp$(this, f);
      }

      public IndexedSeq findAll$mcI$sp(final Function1 f) {
         return QuasiTensor.findAll$mcI$sp$(this, f);
      }

      public IndexedSeq findAll$mcJ$sp(final Function1 f) {
         return QuasiTensor.findAll$mcJ$sp$(this, f);
      }

      public Map data() {
         return this.data;
      }

      public Object default() {
         return this.zero.zero();
      }

      public Impl(final Map data, final Zero zero) {
         this.data = data;
         this.zero = zero;
         QuasiTensor.$init$(this);
         ImmutableNumericOps.$init$(this);
         NumericOps.$init$(this);
         TensorLike.$init$(this);
         CounterLike.$init$(this);
      }
   }
}
