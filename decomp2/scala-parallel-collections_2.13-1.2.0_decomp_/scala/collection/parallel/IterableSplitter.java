package scala.collection.parallel;

import java.lang.invoke.SerializedLambda;
import scala.;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Tuple2;
import scala.collection.BufferedIterator;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.generic.DelegatedSignalling;
import scala.collection.generic.IdleSignalling$;
import scala.collection.generic.Signalling;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;

@ScalaSignature(
   bytes = "\u0006\u0005\reaa\u0002&L!\u0003\r\tA\u0015\u0005\u0006g\u0002!\t\u0001\u001e\u0005\bq\u0002\u0001\r\u0011\"\u0001z\u0011\u001dQ\b\u00011A\u0005\u0002mDQA \u0001\u0007\u0002}Dq!a\u0001\u0001\r\u0003\t)\u0001C\u0004\u0002\u0016\u0001!\t!!\u0002\t\u000f\u0005]\u0001\u0001\"\u0001\u0002\u001a!9\u0011Q\b\u0001\u0007\u0002\u0005}\u0002bBA!\u0001\u0011E\u00111\t\u0005\t\u0003O\u0002A\u0011A&\u0002j\u00191\u0011\u0011\u0010\u0001\u0001\u0003wB!\"! \f\u0005\u0003\u0005\u000b\u0011BA\u001c\u0011\u001d\tyh\u0003C\u0001\u0003\u0003C\u0011\"!\u0010\f\u0001\u0004%\t!a\u0010\t\u0013\u0005%5\u00021A\u0005\u0002\u0005-\u0005\u0002CAH\u0017\u0001\u0006K!a\u000e\t\u000f\u0005E5\u0002\"\u0001\u0002\u0014\"9\u0011QS\u0006\u0005\u0002\u0005]\u0005\"\u0002@\f\t\u0003y\bbBA\u0002\u0017\u0011\u0005\u0011Q\u0001\u0005\t\u00033[\u0001\u0015\"\u0005\u0002\u001c\"A\u0011\u0011\u001c\u0001\u0005\u00025\u000bY\u000e\u0003\u0005\u0002b\u0002!\t!TAr\u0011\u001d\t9\u0010\u0001C!\u0003sDq!a@\u0001\t\u0003\u0012\t\u0001C\u0004\u0003\u0006\u0001!\tEa\u0002\u0007\r\t=\u0001\u0001\u0001B\t\u0011)\u0011Yb\u0007B\u0001B\u0003%!Q\u0004\u0005\b\u0003\u007fZB\u0011\u0001B\u0010\u0011\u001d\t\tj\u0007C\u0001\u0003'Cq!!&\u001c\t\u0003\u0011)\u0003C\u0004\u0002>m!\t!a\u0010\t\ry\\B\u0011\u0001B\u0014\u0011\u001d\t\u0019a\u0007C\u0001\u0005SAqA!\f\u0001\t\u0003\u0012yC\u0002\u0004\u0003>\u0001\u0001!q\b\u0005\u000b\u0005\u0017\"#Q1A\u0005\u0012\t5\u0003B\u0003B+I\t\u0005\t\u0015!\u0003\u0003P!9\u0011q\u0010\u0013\u0005\u0002\t]\u0003\"\u0003B/I\u0001\u0007I\u0011\u0003B0\u0011%\u0011\t\u0007\na\u0001\n#\u0011\u0019\u0007\u0003\u0005\u0003h\u0011\u0002\u000b\u0015\u0002B\"\u0011\u001d\t\t\n\nC\u0001\u0003'Cq!!&%\t\u0003\u0011I\u0007C\u0004\u0002>\u0011\"\t!a\u0010\t\u000f\t-D\u0005\"\u0005\u0002\u0014\"1a\u0010\nC\u0001\u0005?Bq!a\u0001%\t\u0003\u0011i\u0007C\u0004\u0003r\u0001!\tAa\u001d\u0007\r\t\u001d\u0005\u0001\u0001BE\u0011)\u0011YE\rBC\u0002\u0013E!\u0011\u0014\u0005\u000b\u0005+\u0012$\u0011!Q\u0001\n\tm\u0005bBA@e\u0011\u0005!\u0011\u0015\u0005\b\u0003#\u0013D\u0011AAJ\u0011\u001d\t)J\rC\u0001\u0005OCq!!\u00103\t\u0003\ty\u0004\u0003\u0004\u007fe\u0011\u0005!\u0011\u0016\u0005\b\u0003\u0007\u0011D\u0011\u0001BV\u0011\u001d\u0011y\u000b\u0001C\u0001\u0005c3aA!1\u0001\u0001\t\r\u0007B\u0003B&y\t\u0015\r\u0011\"\u0005\u0003T\"Q!Q\u000b\u001f\u0003\u0002\u0003\u0006IA!6\t\u0015\t]GH!b\u0001\n#\u0011I\u000e\u0003\u0006\u0003\\r\u0012\t\u0011)A\u0005\u0005\u0017D!B!8=\u0005\u000b\u0007I\u0011\u0003Bp\u0011)\u0011\t\u000f\u0010B\u0001B\u0003%!q\u001a\u0005\b\u0003\u007fbD\u0011\u0001Br\u0011\u001d\t\t\n\u0010C\u0001\u0003'Cq!!&=\t\u0003\u0011i\u000fC\u0004\u0002>q\"\t!a\u0010\t\rydD\u0011\u0001Bx\u0011\u001d\t\u0019\u0001\u0010C\u0001\u0005cDqA!>\u0001\t\u0003\u00119P\u0001\tJi\u0016\u0014\u0018M\u00197f'Bd\u0017\u000e\u001e;fe*\u0011A*T\u0001\ta\u0006\u0014\u0018\r\u001c7fY*\u0011ajT\u0001\u000bG>dG.Z2uS>t'\"\u0001)\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001U\u00111KX\n\u0007\u0001QCvM\u001b9\u0011\u0005U3V\"A(\n\u0005]{%AB!osJ+g\rE\u0002Z5rk\u0011aS\u0005\u00037.\u0013\u0011$Q;h[\u0016tG/\u001a3Ji\u0016\u0014\u0018M\u00197f\u0013R,'/\u0019;peB\u0011QL\u0018\u0007\u0001\t\u0019y\u0006\u0001\"b\u0001A\n\tA+\u0005\u0002bIB\u0011QKY\u0005\u0003G>\u0013qAT8uQ&tw\r\u0005\u0002VK&\u0011am\u0014\u0002\u0004\u0003:L\bcA-i9&\u0011\u0011n\u0013\u0002\t'Bd\u0017\u000e\u001e;feB\u00111N\\\u0007\u0002Y*\u0011Q.T\u0001\bO\u0016tWM]5d\u0013\tyGN\u0001\u0006TS\u001et\u0017\r\u001c7j]\u001e\u0004\"a[9\n\u0005Id'a\u0005#fY\u0016<\u0017\r^3e'&<g.\u00197mS:<\u0017A\u0002\u0013j]&$H\u0005F\u0001v!\t)f/\u0003\u0002x\u001f\n!QK\\5u\u00039\u0019\u0018n\u001a8bY\u0012+G.Z4bi\u0016,\u0012A[\u0001\u0013g&<g.\u00197EK2,w-\u0019;f?\u0012*\u0017\u000f\u0006\u0002vy\"9QpAA\u0001\u0002\u0004Q\u0017a\u0001=%c\u0005\u0019A-\u001e9\u0016\u0005\u0005\u0005\u0001cA-\u00019\u0006)1\u000f\u001d7jiV\u0011\u0011q\u0001\t\u0007\u0003\u0013\ty!!\u0001\u000f\u0007U\u000bY!C\u0002\u0002\u000e=\u000bq\u0001]1dW\u0006<W-\u0003\u0003\u0002\u0012\u0005M!aA*fc*\u0019\u0011QB(\u0002'M\u0004H.\u001b;XSRD7+[4oC2d\u0017N\\4\u0002%MDw.\u001e7e'Bd\u0017\u000e\u001e$veRDWM]\u000b\u0005\u00037\ty\u0003\u0006\u0004\u0002\u001e\u0005\r\u00121\u0007\t\u0004+\u0006}\u0011bAA\u0011\u001f\n9!i\\8mK\u0006t\u0007bBA\u0013\u000f\u0001\u0007\u0011qE\u0001\u0005G>dG\u000eE\u0003Z\u0003S\ti#C\u0002\u0002,-\u00131\u0002U1s\u0013R,'/\u00192mKB\u0019Q,a\f\u0005\r\u0005ErA1\u0001a\u0005\u0005\u0019\u0006bBA\u001b\u000f\u0001\u0007\u0011qG\u0001\u0011a\u0006\u0014\u0018\r\u001c7fY&\u001cX\u000eT3wK2\u00042!VA\u001d\u0013\r\tYd\u0014\u0002\u0004\u0013:$\u0018!\u0003:f[\u0006Lg.\u001b8h+\t\t9$A\u0006ck&dGm\u0015;sS:<G\u0003BA#\u00037\u0002B!a\u0012\u0002V9!\u0011\u0011JA)!\r\tYeT\u0007\u0003\u0003\u001bR1!a\u0014R\u0003\u0019a$o\\8u}%\u0019\u00111K(\u0002\rA\u0013X\rZ3g\u0013\u0011\t9&!\u0017\u0003\rM#(/\u001b8h\u0015\r\t\u0019f\u0014\u0005\b\u0003;J\u0001\u0019AA0\u0003\u001d\u0019Gn\\:ve\u0016\u0004b!VA1\u0003K*\u0018bAA2\u001f\nIa)\u001e8di&|g.\r\t\u0007+\u0006\u0005\u0014QI;\u0002!\u0011,'-^4J]\u001a|'/\\1uS>tWCAA6!\u0011\ti'a\u001e\u000e\u0005\u0005=$\u0002BA9\u0003g\nA\u0001\\1oO*\u0011\u0011QO\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002X\u0005=$!\u0002+bW\u0016t7\u0003B\u0006U\u0003\u0003\tQ\u0001^1lK:\fa\u0001P5oSRtD\u0003BAB\u0003\u000f\u00032!!\"\f\u001b\u0005\u0001\u0001bBA?\u001b\u0001\u0007\u0011qG\u0001\u000ee\u0016l\u0017-\u001b8j]\u001e|F%Z9\u0015\u0007U\fi\t\u0003\u0005~\u001f\u0005\u0005\t\u0019AA\u001c\u0003)\u0011X-\\1j]&tw\rI\u0001\bQ\u0006\u001ch*\u001a=u+\t\ti\"\u0001\u0003oKb$H#\u0001/\u0002\u000fQ\f7.Z*fcV!\u0011QTAW)\u0011\ty*a5\u0015\t\u0005\u0005\u0016\u0011\u001a\t\u0007\u0003G\u000bI+a+\u000e\u0005\u0005\u0015&bAAT\u001b\u0006I\u0011.\\7vi\u0006\u0014G.Z\u0005\u0005\u0003#\t)\u000bE\u0002^\u0003[#q!a,\u0016\u0005\u0004\t\tL\u0001\u0002Q\u0013F\u0019\u0011-a-\u0011\te\u0003\u0011Q\u0017\u0016\u00049\u0006]6FAA]!\u0011\tY,!2\u000e\u0005\u0005u&\u0002BA`\u0003\u0003\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005\rw*\u0001\u0006b]:|G/\u0019;j_:LA!a2\u0002>\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\t\u000f\u0005-W\u00031\u0001\u0002N\u0006)A/Y6feBIQ+a4\u0002,\u0006]\u00121V\u0005\u0004\u0003#|%!\u0003$v]\u000e$\u0018n\u001c83\u0011\u001d\t).\u0006a\u0001\u0003/\f!a]9\u0011\r\u0005%\u0011qBAV\u0003!qWm\u001e+bW\u0016tG\u0003BAB\u0003;Dq!a8\u0017\u0001\u0004\t9$A\u0003v]RLG.\u0001\toK^\u001cF.[2f\u0013:$XM\u001d8bYV!\u0011Q]Au)\u0019\t9/a<\u0002tB\u0019Q,!;\u0005\u000f\u0005-xC1\u0001\u0002n\n\tQ+E\u0002b\u0003\u0007Cq!!=\u0018\u0001\u0004\t9/\u0001\u0002ji\"9\u0011Q_\fA\u0002\u0005]\u0012!\u00024s_6\f\u0014\u0001\u00023s_B$B!!\u0001\u0002|\"9\u0011Q \rA\u0002\u0005]\u0012!\u00018\u0002\tQ\f7.\u001a\u000b\u0005\u0003\u0003\u0011\u0019\u0001C\u0004\u0002~f\u0001\r!a\u000e\u0002\u000bMd\u0017nY3\u0015\r\u0005\u0005!\u0011\u0002B\u0006\u0011\u001d\t)P\u0007a\u0001\u0003oAqA!\u0004\u001b\u0001\u0004\t9$\u0001\u0004v]RLG.\r\u0002\u0007\u001b\u0006\u0004\b/\u001a3\u0016\t\tM!\u0011D\n\u00057Q\u0013)\u0002\u0005\u0003Z\u0001\t]\u0001cA/\u0003\u001a\u00111\u0011\u0011G\u000eC\u0002\u0001\f\u0011A\u001a\t\u0007+\u0006\u0005DLa\u0006\u0015\t\t\u0005\"1\u0005\t\u0006\u0003\u000b[\"q\u0003\u0005\b\u00057i\u0002\u0019\u0001B\u000f)\t\u00119\"\u0006\u0002\u0003\u0016U\u0011!1\u0006\t\u0007\u0003\u0013\tyA!\u0006\u0002\u00075\f\u0007/\u0006\u0003\u00032\t]B\u0003\u0002B\u001a\u0005s\u0001B!\u0017\u0001\u00036A\u0019QLa\u000e\u0005\r\u0005E2E1\u0001a\u0011\u001d\u0011Yb\ta\u0001\u0005w\u0001b!VA19\nU\"\u0001C!qa\u0016tG-\u001a3\u0016\r\t\u0005#q\tB)'\u0011!CKa\u0011\u0011\te\u0003!Q\t\t\u0004;\n\u001dCaBAvI\t\u0007!\u0011J\t\u00039\u0012\fA\u0001\u001e5biV\u0011!q\n\t\u0004;\nECaBAXI\t\u0007!1K\t\u0004C\n\r\u0013!\u0002;iCR\u0004C\u0003\u0002B-\u00057\u0002r!!\"%\u0005\u000b\u0012y\u0005C\u0004\u0003L\u001d\u0002\rAa\u0014\u0002\t\r,(O]\u000b\u0003\u0005\u0007\n\u0001bY;se~#S-\u001d\u000b\u0004k\n\u0015\u0004\u0002C?*\u0003\u0003\u0005\rAa\u0011\u0002\u000b\r,(O\u001d\u0011\u0015\u0005\t\u0015\u0013!\u00044jeN$hj\u001c8F[B$\u00180\u0006\u0002\u0003pA1\u0011\u0011BA\b\u0005\u0007\n\u0011#\u00199qK:$\u0007+\u0019:Ji\u0016\u0014\u0018M\u00197f+\u0019\u0011)Ha\u001f\u0003\u0000Q!!q\u000fBC!\u001d\t)\t\nB=\u0005{\u00022!\u0018B>\t\u001d\tY/\rb\u0001\u0005\u0013\u00022!\u0018B@\t\u001d\ty+\rb\u0001\u0005\u0003\u000b2!\u0019BB!\u0011I\u0006A!\u001f\t\u000f\t-\u0013\u00071\u0001\u0003~\t1!,\u001b9qK\u0012,BAa#\u0003\u0018N!!\u0007\u0016BG!\u0011I\u0006Aa$\u0011\rU\u0013\t\n\u0018BK\u0013\r\u0011\u0019j\u0014\u0002\u0007)V\u0004H.\u001a\u001a\u0011\u0007u\u00139\n\u0002\u0004\u00022I\u0012\r\u0001Y\u000b\u0003\u00057\u0003R!\u0017BO\u0005+K1Aa(L\u0005-\u0019V-]*qY&$H/\u001a:\u0015\t\t\r&Q\u0015\t\u0006\u0003\u000b\u0013$Q\u0013\u0005\b\u0005\u0017*\u0004\u0019\u0001BN)\t\u0011y)\u0006\u0002\u0003\u000eV\u0011!Q\u0016\t\u0007\u0003\u0013\tyA!$\u0002\u0013iL\u0007\u000fU1s'\u0016\fX\u0003\u0002BZ\u0005w#BA!.\u0003>B!\u0011\f\u0001B\\!\u0019)&\u0011\u0013/\u0003:B\u0019QLa/\u0005\r\u0005E2H1\u0001a\u0011\u001d\u0011Ye\u000fa\u0001\u0005\u007f\u0003R!\u0017BO\u0005s\u0013\u0011BW5qa\u0016$\u0017\t\u001c7\u0016\r\t\u0015'Q\u001aBi'\u0011aDKa2\u0011\te\u0003!\u0011\u001a\t\b+\nE%1\u001aBh!\ri&Q\u001a\u0003\b\u0003Wd$\u0019\u0001B%!\ri&\u0011\u001b\u0003\u0007\u0003ca$\u0019\u00011\u0016\u0005\tU\u0007#B-\u0003\u001e\n=\u0017\u0001\u0003;iSN,G.Z7\u0016\u0005\t-\u0017!\u0003;iSN,G.Z7!\u0003!!\b.\u0019;fY\u0016lWC\u0001Bh\u0003%!\b.\u0019;fY\u0016l\u0007\u0005\u0006\u0005\u0003f\n\u001d(\u0011\u001eBv!\u001d\t)\t\u0010Bf\u0005\u001fDqAa\u0013D\u0001\u0004\u0011)\u000eC\u0004\u0003X\u000e\u0003\rAa3\t\u000f\tu7\t1\u0001\u0003PR\u0011!\u0011Z\u000b\u0003\u0005\u000f,\"Aa=\u0011\r\u0005%\u0011q\u0002Bd\u00031Q\u0018\u000e]!mYB\u000b'oU3r+!\u0011Ipa\u0003\u0003\u0000\u000e\rA\u0003\u0003B~\u0007\u001b\u0019\tb!\u0006\u0011\u000f\u0005\u0015EH!@\u0004\u0002A\u0019QLa@\u0005\u000f\u0005-\u0018J1\u0001\u0003JA\u0019Qla\u0001\u0005\u000f\r\u0015\u0011J1\u0001\u0004\b\t\t!+E\u0002\u0004\n\u0011\u00042!XB\u0006\t\u0019\t\t$\u0013b\u0001A\"9!1J%A\u0002\r=\u0001#B-\u0003\u001e\u000e%\u0001bBB\n\u0013\u0002\u0007!Q`\u0001\ti\"L7/\u00127f[\"91qC%A\u0002\r\u0005\u0011\u0001\u0003;iCR,E.Z7"
)
public interface IterableSplitter extends AugmentedIterableIterator, Splitter, DelegatedSignalling {
   Signalling signalDelegate();

   void signalDelegate_$eq(final Signalling x$1);

   IterableSplitter dup();

   Seq split();

   default Seq splitWithSignalling() {
      Seq pits = this.split();
      pits.foreach((x$2) -> {
         $anonfun$splitWithSignalling$1(this, x$2);
         return BoxedUnit.UNIT;
      });
      return pits;
   }

   default boolean shouldSplitFurther(final ParIterable coll, final int parallelismLevel) {
      return this.remaining() > package$.MODULE$.thresholdFromSize(coll.size(), parallelismLevel);
   }

   int remaining();

   default String buildString(final Function1 closure) {
      ObjectRef output = ObjectRef.create("");
      closure.apply((Function1)(s) -> {
         $anonfun$buildString$1(output, s);
         return BoxedUnit.UNIT;
      });
      return (String)output.elem;
   }

   default String debugInformation() {
      return (new StringBuilder(19)).append("Parallel iterator: ").append(this.getClass()).toString();
   }

   default Taken newTaken(final int until) {
      return new Taken(until);
   }

   default Taken newSliceInternal(final Taken it, final int from1) {
      for(int count = from1; count > 0 && it.hasNext(); --count) {
         it.next();
      }

      return it;
   }

   default IterableSplitter drop(final int n) {
      for(int i = 0; i < n && this.hasNext(); ++i) {
         this.next();
      }

      return this;
   }

   default IterableSplitter take(final int n) {
      return this.newTaken(n);
   }

   default IterableSplitter slice(final int from1, final int until1) {
      return this.newSliceInternal(this.newTaken(until1), from1);
   }

   default IterableSplitter map(final Function1 f) {
      return new Mapped(f);
   }

   default Appended appendParIterable(final IterableSplitter that) {
      return new Appended(that);
   }

   default IterableSplitter zipParSeq(final SeqSplitter that) {
      return new Zipped(that);
   }

   default ZippedAll zipAllParSeq(final SeqSplitter that, final Object thisElem, final Object thatElem) {
      return new ZippedAll(that, thisElem, thatElem);
   }

   // $FF: synthetic method
   static void $anonfun$splitWithSignalling$1(final IterableSplitter $this, final IterableSplitter x$2) {
      x$2.signalDelegate_$eq($this.signalDelegate());
   }

   private static void appendln$1(final String s, final ObjectRef output$1) {
      output$1.elem = (new StringBuilder(1)).append((String)output$1.elem).append(s).append("\n").toString();
   }

   // $FF: synthetic method
   static void $anonfun$buildString$1(final ObjectRef output$1, final String s) {
      appendln$1(s, output$1);
   }

   static void $init$(final IterableSplitter $this) {
      $this.signalDelegate_$eq(IdleSignalling$.MODULE$);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class Taken implements IterableSplitter {
      private final int taken;
      private int remaining;
      private Signalling signalDelegate;
      // $FF: synthetic field
      public final IterableSplitter $outer;

      public Seq splitWithSignalling() {
         return IterableSplitter.super.splitWithSignalling();
      }

      public boolean shouldSplitFurther(final ParIterable coll, final int parallelismLevel) {
         return IterableSplitter.super.shouldSplitFurther(coll, parallelismLevel);
      }

      public String buildString(final Function1 closure) {
         return IterableSplitter.super.buildString(closure);
      }

      public String debugInformation() {
         return IterableSplitter.super.debugInformation();
      }

      public Taken newTaken(final int until) {
         return IterableSplitter.super.newTaken(until);
      }

      public Taken newSliceInternal(final Taken it, final int from1) {
         return IterableSplitter.super.newSliceInternal(it, from1);
      }

      public IterableSplitter drop(final int n) {
         return IterableSplitter.super.drop(n);
      }

      public IterableSplitter take(final int n) {
         return IterableSplitter.super.take(n);
      }

      public IterableSplitter slice(final int from1, final int until1) {
         return IterableSplitter.super.slice(from1, until1);
      }

      public IterableSplitter map(final Function1 f) {
         return IterableSplitter.super.map(f);
      }

      public Appended appendParIterable(final IterableSplitter that) {
         return IterableSplitter.super.appendParIterable(that);
      }

      public IterableSplitter zipParSeq(final SeqSplitter that) {
         return IterableSplitter.super.zipParSeq(that);
      }

      public ZippedAll zipAllParSeq(final SeqSplitter that, final Object thisElem, final Object thatElem) {
         return IterableSplitter.super.zipAllParSeq(that, thisElem, thatElem);
      }

      public boolean isAborted() {
         return DelegatedSignalling.isAborted$(this);
      }

      public void abort() {
         DelegatedSignalling.abort$(this);
      }

      public int indexFlag() {
         return DelegatedSignalling.indexFlag$(this);
      }

      public void setIndexFlag(final int f) {
         DelegatedSignalling.setIndexFlag$(this, f);
      }

      public void setIndexFlagIfGreater(final int f) {
         DelegatedSignalling.setIndexFlagIfGreater$(this, f);
      }

      public void setIndexFlagIfLesser(final int f) {
         DelegatedSignalling.setIndexFlagIfLesser$(this, f);
      }

      public int tag() {
         return DelegatedSignalling.tag$(this);
      }

      public int count(final Function1 p) {
         return AugmentedIterableIterator.count$(this, p);
      }

      public Object reduce(final Function2 op) {
         return AugmentedIterableIterator.reduce$(this, op);
      }

      public Object fold(final Object z, final Function2 op) {
         return AugmentedIterableIterator.fold$(this, z, op);
      }

      public Object sum(final Numeric num) {
         return AugmentedIterableIterator.sum$(this, num);
      }

      public Object product(final Numeric num) {
         return AugmentedIterableIterator.product$(this, num);
      }

      public Object min(final Ordering ord) {
         return AugmentedIterableIterator.min$(this, ord);
      }

      public Object max(final Ordering ord) {
         return AugmentedIterableIterator.max$(this, ord);
      }

      public Object reduceLeft(final int howmany, final Function2 op) {
         return AugmentedIterableIterator.reduceLeft$(this, howmany, op);
      }

      public Combiner map2combiner(final Function1 f, final Combiner cb) {
         return AugmentedIterableIterator.map2combiner$(this, f, cb);
      }

      public Combiner collect2combiner(final PartialFunction pf, final Combiner cb) {
         return AugmentedIterableIterator.collect2combiner$(this, pf, cb);
      }

      public Combiner flatmap2combiner(final Function1 f, final Combiner cb) {
         return AugmentedIterableIterator.flatmap2combiner$(this, f, cb);
      }

      public Builder copy2builder(final Builder b) {
         return AugmentedIterableIterator.copy2builder$(this, b);
      }

      public Combiner filter2combiner(final Function1 pred, final Combiner cb) {
         return AugmentedIterableIterator.filter2combiner$(this, pred, cb);
      }

      public Combiner filterNot2combiner(final Function1 pred, final Combiner cb) {
         return AugmentedIterableIterator.filterNot2combiner$(this, pred, cb);
      }

      public Tuple2 partition2combiners(final Function1 pred, final Combiner btrue, final Combiner bfalse) {
         return AugmentedIterableIterator.partition2combiners$(this, pred, btrue, bfalse);
      }

      public Combiner take2combiner(final int n, final Combiner cb) {
         return AugmentedIterableIterator.take2combiner$(this, n, cb);
      }

      public Combiner drop2combiner(final int n, final Combiner cb) {
         return AugmentedIterableIterator.drop2combiner$(this, n, cb);
      }

      public Combiner slice2combiner(final int from, final int until, final Combiner cb) {
         return AugmentedIterableIterator.slice2combiner$(this, from, until, cb);
      }

      public Tuple2 splitAt2combiners(final int at, final Combiner before, final Combiner after) {
         return AugmentedIterableIterator.splitAt2combiners$(this, at, before, after);
      }

      public Tuple2 takeWhile2combiner(final Function1 p, final Combiner cb) {
         return AugmentedIterableIterator.takeWhile2combiner$(this, p, cb);
      }

      public Tuple2 span2combiners(final Function1 p, final Combiner before, final Combiner after) {
         return AugmentedIterableIterator.span2combiners$(this, p, before, after);
      }

      public void scanToArray(final Object z, final Function2 op, final Object array, final int from) {
         AugmentedIterableIterator.scanToArray$(this, z, op, array, from);
      }

      public Combiner scanToCombiner(final Object startValue, final Function2 op, final Combiner cb) {
         return AugmentedIterableIterator.scanToCombiner$(this, startValue, op, cb);
      }

      public Combiner scanToCombiner(final int howmany, final Object startValue, final Function2 op, final Combiner cb) {
         return AugmentedIterableIterator.scanToCombiner$(this, howmany, startValue, op, cb);
      }

      public Combiner zip2combiner(final RemainsIterator otherpit, final Combiner cb) {
         return AugmentedIterableIterator.zip2combiner$(this, otherpit, cb);
      }

      public Combiner zipAll2combiner(final RemainsIterator that, final Object thiselem, final Object thatelem, final Combiner cb) {
         return AugmentedIterableIterator.zipAll2combiner$(this, that, thiselem, thatelem, cb);
      }

      public boolean isRemainingCheap() {
         return RemainsIterator.isRemainingCheap$(this);
      }

      /** @deprecated */
      public final boolean hasDefiniteSize() {
         return Iterator.hasDefiniteSize$(this);
      }

      public final Iterator iterator() {
         return Iterator.iterator$(this);
      }

      public Option nextOption() {
         return Iterator.nextOption$(this);
      }

      public boolean contains(final Object elem) {
         return Iterator.contains$(this, elem);
      }

      public BufferedIterator buffered() {
         return Iterator.buffered$(this);
      }

      public Iterator padTo(final int len, final Object elem) {
         return Iterator.padTo$(this, len, elem);
      }

      public Tuple2 partition(final Function1 p) {
         return Iterator.partition$(this, p);
      }

      public Iterator.GroupedIterator grouped(final int size) {
         return Iterator.grouped$(this, size);
      }

      public Iterator.GroupedIterator sliding(final int size, final int step) {
         return Iterator.sliding$(this, size, step);
      }

      public int sliding$default$2() {
         return Iterator.sliding$default$2$(this);
      }

      public Iterator scanLeft(final Object z, final Function2 op) {
         return Iterator.scanLeft$(this, z, op);
      }

      /** @deprecated */
      public Iterator scanRight(final Object z, final Function2 op) {
         return Iterator.scanRight$(this, z, op);
      }

      public int indexWhere(final Function1 p, final int from) {
         return Iterator.indexWhere$(this, p, from);
      }

      public int indexWhere$default$2() {
         return Iterator.indexWhere$default$2$(this);
      }

      public int indexOf(final Object elem) {
         return Iterator.indexOf$(this, elem);
      }

      public int indexOf(final Object elem, final int from) {
         return Iterator.indexOf$(this, elem, from);
      }

      public final int length() {
         return Iterator.length$(this);
      }

      public boolean isEmpty() {
         return Iterator.isEmpty$(this);
      }

      public Iterator filter(final Function1 p) {
         return Iterator.filter$(this, p);
      }

      public Iterator filterNot(final Function1 p) {
         return Iterator.filterNot$(this, p);
      }

      public Iterator filterImpl(final Function1 p, final boolean isFlipped) {
         return Iterator.filterImpl$(this, p, isFlipped);
      }

      public Iterator withFilter(final Function1 p) {
         return Iterator.withFilter$(this, p);
      }

      public Iterator collect(final PartialFunction pf) {
         return Iterator.collect$(this, pf);
      }

      public Iterator distinct() {
         return Iterator.distinct$(this);
      }

      public Iterator distinctBy(final Function1 f) {
         return Iterator.distinctBy$(this, f);
      }

      public Iterator flatMap(final Function1 f) {
         return Iterator.flatMap$(this, f);
      }

      public Iterator flatten(final Function1 ev) {
         return Iterator.flatten$(this, ev);
      }

      public Iterator concat(final Function0 xs) {
         return Iterator.concat$(this, xs);
      }

      public final Iterator $plus$plus(final Function0 xs) {
         return Iterator.$plus$plus$(this, xs);
      }

      public Iterator takeWhile(final Function1 p) {
         return Iterator.takeWhile$(this, p);
      }

      public Iterator dropWhile(final Function1 p) {
         return Iterator.dropWhile$(this, p);
      }

      public Tuple2 span(final Function1 p) {
         return Iterator.span$(this, p);
      }

      public Iterator sliceIterator(final int from, final int until) {
         return Iterator.sliceIterator$(this, from, until);
      }

      public Iterator zip(final IterableOnce that) {
         return Iterator.zip$(this, that);
      }

      public Iterator zipAll(final IterableOnce that, final Object thisElem, final Object thatElem) {
         return Iterator.zipAll$(this, that, thisElem, thatElem);
      }

      public Iterator zipWithIndex() {
         return Iterator.zipWithIndex$(this);
      }

      public boolean sameElements(final IterableOnce that) {
         return Iterator.sameElements$(this, that);
      }

      public Tuple2 duplicate() {
         return Iterator.duplicate$(this);
      }

      public Iterator patch(final int from, final Iterator patchElems, final int replaced) {
         return Iterator.patch$(this, from, patchElems, replaced);
      }

      public Iterator tapEach(final Function1 f) {
         return Iterator.tapEach$(this, f);
      }

      public String toString() {
         return Iterator.toString$(this);
      }

      /** @deprecated */
      public Iterator seq() {
         return Iterator.seq$(this);
      }

      public Tuple2 splitAt(final int n) {
         return IterableOnceOps.splitAt$(this, n);
      }

      public boolean isTraversableAgain() {
         return IterableOnceOps.isTraversableAgain$(this);
      }

      public void foreach(final Function1 f) {
         IterableOnceOps.foreach$(this, f);
      }

      public boolean forall(final Function1 p) {
         return IterableOnceOps.forall$(this, p);
      }

      public boolean exists(final Function1 p) {
         return IterableOnceOps.exists$(this, p);
      }

      public Option find(final Function1 p) {
         return IterableOnceOps.find$(this, p);
      }

      public Object foldLeft(final Object z, final Function2 op) {
         return IterableOnceOps.foldLeft$(this, z, op);
      }

      public Object foldRight(final Object z, final Function2 op) {
         return IterableOnceOps.foldRight$(this, z, op);
      }

      /** @deprecated */
      public final Object $div$colon(final Object z, final Function2 op) {
         return IterableOnceOps.$div$colon$(this, z, op);
      }

      /** @deprecated */
      public final Object $colon$bslash(final Object z, final Function2 op) {
         return IterableOnceOps.$colon$bslash$(this, z, op);
      }

      public Option reduceOption(final Function2 op) {
         return IterableOnceOps.reduceOption$(this, op);
      }

      public Object reduceLeft(final Function2 op) {
         return IterableOnceOps.reduceLeft$(this, op);
      }

      public Object reduceRight(final Function2 op) {
         return IterableOnceOps.reduceRight$(this, op);
      }

      public Option reduceLeftOption(final Function2 op) {
         return IterableOnceOps.reduceLeftOption$(this, op);
      }

      public Option reduceRightOption(final Function2 op) {
         return IterableOnceOps.reduceRightOption$(this, op);
      }

      public boolean nonEmpty() {
         return IterableOnceOps.nonEmpty$(this);
      }

      public int size() {
         return IterableOnceOps.size$(this);
      }

      /** @deprecated */
      public final void copyToBuffer(final Buffer dest) {
         IterableOnceOps.copyToBuffer$(this, dest);
      }

      public int copyToArray(final Object xs) {
         return IterableOnceOps.copyToArray$(this, xs);
      }

      public int copyToArray(final Object xs, final int start) {
         return IterableOnceOps.copyToArray$(this, xs, start);
      }

      public int copyToArray(final Object xs, final int start, final int len) {
         return IterableOnceOps.copyToArray$(this, xs, start, len);
      }

      public Option minOption(final Ordering ord) {
         return IterableOnceOps.minOption$(this, ord);
      }

      public Option maxOption(final Ordering ord) {
         return IterableOnceOps.maxOption$(this, ord);
      }

      public Object maxBy(final Function1 f, final Ordering ord) {
         return IterableOnceOps.maxBy$(this, f, ord);
      }

      public Option maxByOption(final Function1 f, final Ordering ord) {
         return IterableOnceOps.maxByOption$(this, f, ord);
      }

      public Object minBy(final Function1 f, final Ordering ord) {
         return IterableOnceOps.minBy$(this, f, ord);
      }

      public Option minByOption(final Function1 f, final Ordering ord) {
         return IterableOnceOps.minByOption$(this, f, ord);
      }

      public Option collectFirst(final PartialFunction pf) {
         return IterableOnceOps.collectFirst$(this, pf);
      }

      /** @deprecated */
      public Object aggregate(final Function0 z, final Function2 seqop, final Function2 combop) {
         return IterableOnceOps.aggregate$(this, z, seqop, combop);
      }

      public boolean corresponds(final IterableOnce that, final Function2 p) {
         return IterableOnceOps.corresponds$(this, that, p);
      }

      public final String mkString(final String start, final String sep, final String end) {
         return IterableOnceOps.mkString$(this, start, sep, end);
      }

      public final String mkString(final String sep) {
         return IterableOnceOps.mkString$(this, sep);
      }

      public final String mkString() {
         return IterableOnceOps.mkString$(this);
      }

      public scala.collection.mutable.StringBuilder addString(final scala.collection.mutable.StringBuilder b, final String start, final String sep, final String end) {
         return IterableOnceOps.addString$(this, b, start, sep, end);
      }

      public final scala.collection.mutable.StringBuilder addString(final scala.collection.mutable.StringBuilder b, final String sep) {
         return IterableOnceOps.addString$(this, b, sep);
      }

      public final scala.collection.mutable.StringBuilder addString(final scala.collection.mutable.StringBuilder b) {
         return IterableOnceOps.addString$(this, b);
      }

      public Object to(final Factory factory) {
         return IterableOnceOps.to$(this, factory);
      }

      /** @deprecated */
      public final Iterator toIterator() {
         return IterableOnceOps.toIterator$(this);
      }

      public List toList() {
         return IterableOnceOps.toList$(this);
      }

      public Vector toVector() {
         return IterableOnceOps.toVector$(this);
      }

      public Map toMap(final .less.colon.less ev) {
         return IterableOnceOps.toMap$(this, ev);
      }

      public Set toSet() {
         return IterableOnceOps.toSet$(this);
      }

      public Seq toSeq() {
         return IterableOnceOps.toSeq$(this);
      }

      public IndexedSeq toIndexedSeq() {
         return IterableOnceOps.toIndexedSeq$(this);
      }

      /** @deprecated */
      public final Stream toStream() {
         return IterableOnceOps.toStream$(this);
      }

      public final Buffer toBuffer() {
         return IterableOnceOps.toBuffer$(this);
      }

      public Object toArray(final ClassTag evidence$2) {
         return IterableOnceOps.toArray$(this, evidence$2);
      }

      public Iterable reversed() {
         return IterableOnceOps.reversed$(this);
      }

      public Stepper stepper(final StepperShape shape) {
         return IterableOnce.stepper$(this, shape);
      }

      public int knownSize() {
         return IterableOnce.knownSize$(this);
      }

      public Signalling signalDelegate() {
         return this.signalDelegate;
      }

      public void signalDelegate_$eq(final Signalling x$1) {
         this.signalDelegate = x$1;
      }

      public int remaining() {
         return this.remaining;
      }

      public void remaining_$eq(final int x$1) {
         this.remaining = x$1;
      }

      public boolean hasNext() {
         return this.remaining() > 0;
      }

      public Object next() {
         this.remaining_$eq(this.remaining() - 1);
         return this.scala$collection$parallel$IterableSplitter$Taken$$$outer().next();
      }

      public IterableSplitter dup() {
         return this.scala$collection$parallel$IterableSplitter$Taken$$$outer().dup().take(this.taken);
      }

      public Seq split() {
         return this.takeSeq(this.scala$collection$parallel$IterableSplitter$Taken$$$outer().split(), (p, n) -> $anonfun$split$1(p, BoxesRunTime.unboxToInt(n)));
      }

      public Seq takeSeq(final Seq sq, final Function2 taker) {
         Seq sizes = (Seq)sq.scanLeft(BoxesRunTime.boxToInteger(0), (x$3, x$4) -> BoxesRunTime.boxToInteger($anonfun$takeSeq$1(BoxesRunTime.unboxToInt(x$3), x$4)));
         Seq shortened = (Seq)((IterableOps)sq.zip((IterableOnce)((IterableOps)sizes.init()).zip((IterableOnce)sizes.tail()))).withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$takeSeq$2(check$ifrefutable$1))).map((x$5) -> {
            if (x$5 != null) {
               IterableSplitter it = (IterableSplitter)x$5._1();
               Tuple2 var6 = (Tuple2)x$5._2();
               if (var6 != null) {
                  int from = var6._1$mcI$sp();
                  int until = var6._2$mcI$sp();
                  if (until < this.remaining()) {
                     return it;
                  }

                  return (IterableSplitter)taker.apply(it, BoxesRunTime.boxToInteger(this.remaining() - from));
               }
            }

            throw new MatchError(x$5);
         });
         return (Seq)shortened.filter((x$6) -> BoxesRunTime.boxToBoolean($anonfun$takeSeq$4(x$6)));
      }

      // $FF: synthetic method
      public IterableSplitter scala$collection$parallel$IterableSplitter$Taken$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final IterableSplitter $anonfun$split$1(final IterableSplitter p, final int n) {
         return p.take(n);
      }

      // $FF: synthetic method
      public static final int $anonfun$takeSeq$1(final int x$3, final IterableSplitter x$4) {
         return x$3 + x$4.remaining();
      }

      // $FF: synthetic method
      public static final boolean $anonfun$takeSeq$2(final Tuple2 check$ifrefutable$1) {
         if (check$ifrefutable$1 != null) {
            Tuple2 var3 = (Tuple2)check$ifrefutable$1._2();
            if (var3 != null) {
               return true;
            }
         }

         return false;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$takeSeq$4(final IterableSplitter x$6) {
         return x$6.remaining() > 0;
      }

      public Taken(final int taken) {
         this.taken = taken;
         if (IterableSplitter.this == null) {
            throw null;
         } else {
            this.$outer = IterableSplitter.this;
            super();
            IterableOnce.$init$(this);
            IterableOnceOps.$init$(this);
            Iterator.$init$(this);
            RemainsIterator.$init$(this);
            AugmentedIterableIterator.$init$(this);
            DelegatedSignalling.$init$(this);
            IterableSplitter.$init$(this);
            this.remaining = scala.runtime.RichInt..MODULE$.min$extension(scala.Predef..MODULE$.intWrapper(taken), IterableSplitter.this.remaining());
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class Mapped implements IterableSplitter {
      private final Function1 f;
      private Signalling signalDelegate;
      // $FF: synthetic field
      public final IterableSplitter $outer;

      public Seq splitWithSignalling() {
         return IterableSplitter.super.splitWithSignalling();
      }

      public boolean shouldSplitFurther(final ParIterable coll, final int parallelismLevel) {
         return IterableSplitter.super.shouldSplitFurther(coll, parallelismLevel);
      }

      public String buildString(final Function1 closure) {
         return IterableSplitter.super.buildString(closure);
      }

      public String debugInformation() {
         return IterableSplitter.super.debugInformation();
      }

      public Taken newTaken(final int until) {
         return IterableSplitter.super.newTaken(until);
      }

      public Taken newSliceInternal(final Taken it, final int from1) {
         return IterableSplitter.super.newSliceInternal(it, from1);
      }

      public IterableSplitter drop(final int n) {
         return IterableSplitter.super.drop(n);
      }

      public IterableSplitter take(final int n) {
         return IterableSplitter.super.take(n);
      }

      public IterableSplitter slice(final int from1, final int until1) {
         return IterableSplitter.super.slice(from1, until1);
      }

      public IterableSplitter map(final Function1 f) {
         return IterableSplitter.super.map(f);
      }

      public Appended appendParIterable(final IterableSplitter that) {
         return IterableSplitter.super.appendParIterable(that);
      }

      public IterableSplitter zipParSeq(final SeqSplitter that) {
         return IterableSplitter.super.zipParSeq(that);
      }

      public ZippedAll zipAllParSeq(final SeqSplitter that, final Object thisElem, final Object thatElem) {
         return IterableSplitter.super.zipAllParSeq(that, thisElem, thatElem);
      }

      public boolean isAborted() {
         return DelegatedSignalling.isAborted$(this);
      }

      public void abort() {
         DelegatedSignalling.abort$(this);
      }

      public int indexFlag() {
         return DelegatedSignalling.indexFlag$(this);
      }

      public void setIndexFlag(final int f) {
         DelegatedSignalling.setIndexFlag$(this, f);
      }

      public void setIndexFlagIfGreater(final int f) {
         DelegatedSignalling.setIndexFlagIfGreater$(this, f);
      }

      public void setIndexFlagIfLesser(final int f) {
         DelegatedSignalling.setIndexFlagIfLesser$(this, f);
      }

      public int tag() {
         return DelegatedSignalling.tag$(this);
      }

      public int count(final Function1 p) {
         return AugmentedIterableIterator.count$(this, p);
      }

      public Object reduce(final Function2 op) {
         return AugmentedIterableIterator.reduce$(this, op);
      }

      public Object fold(final Object z, final Function2 op) {
         return AugmentedIterableIterator.fold$(this, z, op);
      }

      public Object sum(final Numeric num) {
         return AugmentedIterableIterator.sum$(this, num);
      }

      public Object product(final Numeric num) {
         return AugmentedIterableIterator.product$(this, num);
      }

      public Object min(final Ordering ord) {
         return AugmentedIterableIterator.min$(this, ord);
      }

      public Object max(final Ordering ord) {
         return AugmentedIterableIterator.max$(this, ord);
      }

      public Object reduceLeft(final int howmany, final Function2 op) {
         return AugmentedIterableIterator.reduceLeft$(this, howmany, op);
      }

      public Combiner map2combiner(final Function1 f, final Combiner cb) {
         return AugmentedIterableIterator.map2combiner$(this, f, cb);
      }

      public Combiner collect2combiner(final PartialFunction pf, final Combiner cb) {
         return AugmentedIterableIterator.collect2combiner$(this, pf, cb);
      }

      public Combiner flatmap2combiner(final Function1 f, final Combiner cb) {
         return AugmentedIterableIterator.flatmap2combiner$(this, f, cb);
      }

      public Builder copy2builder(final Builder b) {
         return AugmentedIterableIterator.copy2builder$(this, b);
      }

      public Combiner filter2combiner(final Function1 pred, final Combiner cb) {
         return AugmentedIterableIterator.filter2combiner$(this, pred, cb);
      }

      public Combiner filterNot2combiner(final Function1 pred, final Combiner cb) {
         return AugmentedIterableIterator.filterNot2combiner$(this, pred, cb);
      }

      public Tuple2 partition2combiners(final Function1 pred, final Combiner btrue, final Combiner bfalse) {
         return AugmentedIterableIterator.partition2combiners$(this, pred, btrue, bfalse);
      }

      public Combiner take2combiner(final int n, final Combiner cb) {
         return AugmentedIterableIterator.take2combiner$(this, n, cb);
      }

      public Combiner drop2combiner(final int n, final Combiner cb) {
         return AugmentedIterableIterator.drop2combiner$(this, n, cb);
      }

      public Combiner slice2combiner(final int from, final int until, final Combiner cb) {
         return AugmentedIterableIterator.slice2combiner$(this, from, until, cb);
      }

      public Tuple2 splitAt2combiners(final int at, final Combiner before, final Combiner after) {
         return AugmentedIterableIterator.splitAt2combiners$(this, at, before, after);
      }

      public Tuple2 takeWhile2combiner(final Function1 p, final Combiner cb) {
         return AugmentedIterableIterator.takeWhile2combiner$(this, p, cb);
      }

      public Tuple2 span2combiners(final Function1 p, final Combiner before, final Combiner after) {
         return AugmentedIterableIterator.span2combiners$(this, p, before, after);
      }

      public void scanToArray(final Object z, final Function2 op, final Object array, final int from) {
         AugmentedIterableIterator.scanToArray$(this, z, op, array, from);
      }

      public Combiner scanToCombiner(final Object startValue, final Function2 op, final Combiner cb) {
         return AugmentedIterableIterator.scanToCombiner$(this, startValue, op, cb);
      }

      public Combiner scanToCombiner(final int howmany, final Object startValue, final Function2 op, final Combiner cb) {
         return AugmentedIterableIterator.scanToCombiner$(this, howmany, startValue, op, cb);
      }

      public Combiner zip2combiner(final RemainsIterator otherpit, final Combiner cb) {
         return AugmentedIterableIterator.zip2combiner$(this, otherpit, cb);
      }

      public Combiner zipAll2combiner(final RemainsIterator that, final Object thiselem, final Object thatelem, final Combiner cb) {
         return AugmentedIterableIterator.zipAll2combiner$(this, that, thiselem, thatelem, cb);
      }

      public boolean isRemainingCheap() {
         return RemainsIterator.isRemainingCheap$(this);
      }

      /** @deprecated */
      public final boolean hasDefiniteSize() {
         return Iterator.hasDefiniteSize$(this);
      }

      public final Iterator iterator() {
         return Iterator.iterator$(this);
      }

      public Option nextOption() {
         return Iterator.nextOption$(this);
      }

      public boolean contains(final Object elem) {
         return Iterator.contains$(this, elem);
      }

      public BufferedIterator buffered() {
         return Iterator.buffered$(this);
      }

      public Iterator padTo(final int len, final Object elem) {
         return Iterator.padTo$(this, len, elem);
      }

      public Tuple2 partition(final Function1 p) {
         return Iterator.partition$(this, p);
      }

      public Iterator.GroupedIterator grouped(final int size) {
         return Iterator.grouped$(this, size);
      }

      public Iterator.GroupedIterator sliding(final int size, final int step) {
         return Iterator.sliding$(this, size, step);
      }

      public int sliding$default$2() {
         return Iterator.sliding$default$2$(this);
      }

      public Iterator scanLeft(final Object z, final Function2 op) {
         return Iterator.scanLeft$(this, z, op);
      }

      /** @deprecated */
      public Iterator scanRight(final Object z, final Function2 op) {
         return Iterator.scanRight$(this, z, op);
      }

      public int indexWhere(final Function1 p, final int from) {
         return Iterator.indexWhere$(this, p, from);
      }

      public int indexWhere$default$2() {
         return Iterator.indexWhere$default$2$(this);
      }

      public int indexOf(final Object elem) {
         return Iterator.indexOf$(this, elem);
      }

      public int indexOf(final Object elem, final int from) {
         return Iterator.indexOf$(this, elem, from);
      }

      public final int length() {
         return Iterator.length$(this);
      }

      public boolean isEmpty() {
         return Iterator.isEmpty$(this);
      }

      public Iterator filter(final Function1 p) {
         return Iterator.filter$(this, p);
      }

      public Iterator filterNot(final Function1 p) {
         return Iterator.filterNot$(this, p);
      }

      public Iterator filterImpl(final Function1 p, final boolean isFlipped) {
         return Iterator.filterImpl$(this, p, isFlipped);
      }

      public Iterator withFilter(final Function1 p) {
         return Iterator.withFilter$(this, p);
      }

      public Iterator collect(final PartialFunction pf) {
         return Iterator.collect$(this, pf);
      }

      public Iterator distinct() {
         return Iterator.distinct$(this);
      }

      public Iterator distinctBy(final Function1 f) {
         return Iterator.distinctBy$(this, f);
      }

      public Iterator flatMap(final Function1 f) {
         return Iterator.flatMap$(this, f);
      }

      public Iterator flatten(final Function1 ev) {
         return Iterator.flatten$(this, ev);
      }

      public Iterator concat(final Function0 xs) {
         return Iterator.concat$(this, xs);
      }

      public final Iterator $plus$plus(final Function0 xs) {
         return Iterator.$plus$plus$(this, xs);
      }

      public Iterator takeWhile(final Function1 p) {
         return Iterator.takeWhile$(this, p);
      }

      public Iterator dropWhile(final Function1 p) {
         return Iterator.dropWhile$(this, p);
      }

      public Tuple2 span(final Function1 p) {
         return Iterator.span$(this, p);
      }

      public Iterator sliceIterator(final int from, final int until) {
         return Iterator.sliceIterator$(this, from, until);
      }

      public Iterator zip(final IterableOnce that) {
         return Iterator.zip$(this, that);
      }

      public Iterator zipAll(final IterableOnce that, final Object thisElem, final Object thatElem) {
         return Iterator.zipAll$(this, that, thisElem, thatElem);
      }

      public Iterator zipWithIndex() {
         return Iterator.zipWithIndex$(this);
      }

      public boolean sameElements(final IterableOnce that) {
         return Iterator.sameElements$(this, that);
      }

      public Tuple2 duplicate() {
         return Iterator.duplicate$(this);
      }

      public Iterator patch(final int from, final Iterator patchElems, final int replaced) {
         return Iterator.patch$(this, from, patchElems, replaced);
      }

      public Iterator tapEach(final Function1 f) {
         return Iterator.tapEach$(this, f);
      }

      public String toString() {
         return Iterator.toString$(this);
      }

      /** @deprecated */
      public Iterator seq() {
         return Iterator.seq$(this);
      }

      public Tuple2 splitAt(final int n) {
         return IterableOnceOps.splitAt$(this, n);
      }

      public boolean isTraversableAgain() {
         return IterableOnceOps.isTraversableAgain$(this);
      }

      public void foreach(final Function1 f) {
         IterableOnceOps.foreach$(this, f);
      }

      public boolean forall(final Function1 p) {
         return IterableOnceOps.forall$(this, p);
      }

      public boolean exists(final Function1 p) {
         return IterableOnceOps.exists$(this, p);
      }

      public Option find(final Function1 p) {
         return IterableOnceOps.find$(this, p);
      }

      public Object foldLeft(final Object z, final Function2 op) {
         return IterableOnceOps.foldLeft$(this, z, op);
      }

      public Object foldRight(final Object z, final Function2 op) {
         return IterableOnceOps.foldRight$(this, z, op);
      }

      /** @deprecated */
      public final Object $div$colon(final Object z, final Function2 op) {
         return IterableOnceOps.$div$colon$(this, z, op);
      }

      /** @deprecated */
      public final Object $colon$bslash(final Object z, final Function2 op) {
         return IterableOnceOps.$colon$bslash$(this, z, op);
      }

      public Option reduceOption(final Function2 op) {
         return IterableOnceOps.reduceOption$(this, op);
      }

      public Object reduceLeft(final Function2 op) {
         return IterableOnceOps.reduceLeft$(this, op);
      }

      public Object reduceRight(final Function2 op) {
         return IterableOnceOps.reduceRight$(this, op);
      }

      public Option reduceLeftOption(final Function2 op) {
         return IterableOnceOps.reduceLeftOption$(this, op);
      }

      public Option reduceRightOption(final Function2 op) {
         return IterableOnceOps.reduceRightOption$(this, op);
      }

      public boolean nonEmpty() {
         return IterableOnceOps.nonEmpty$(this);
      }

      public int size() {
         return IterableOnceOps.size$(this);
      }

      /** @deprecated */
      public final void copyToBuffer(final Buffer dest) {
         IterableOnceOps.copyToBuffer$(this, dest);
      }

      public int copyToArray(final Object xs) {
         return IterableOnceOps.copyToArray$(this, xs);
      }

      public int copyToArray(final Object xs, final int start) {
         return IterableOnceOps.copyToArray$(this, xs, start);
      }

      public int copyToArray(final Object xs, final int start, final int len) {
         return IterableOnceOps.copyToArray$(this, xs, start, len);
      }

      public Option minOption(final Ordering ord) {
         return IterableOnceOps.minOption$(this, ord);
      }

      public Option maxOption(final Ordering ord) {
         return IterableOnceOps.maxOption$(this, ord);
      }

      public Object maxBy(final Function1 f, final Ordering ord) {
         return IterableOnceOps.maxBy$(this, f, ord);
      }

      public Option maxByOption(final Function1 f, final Ordering ord) {
         return IterableOnceOps.maxByOption$(this, f, ord);
      }

      public Object minBy(final Function1 f, final Ordering ord) {
         return IterableOnceOps.minBy$(this, f, ord);
      }

      public Option minByOption(final Function1 f, final Ordering ord) {
         return IterableOnceOps.minByOption$(this, f, ord);
      }

      public Option collectFirst(final PartialFunction pf) {
         return IterableOnceOps.collectFirst$(this, pf);
      }

      /** @deprecated */
      public Object aggregate(final Function0 z, final Function2 seqop, final Function2 combop) {
         return IterableOnceOps.aggregate$(this, z, seqop, combop);
      }

      public boolean corresponds(final IterableOnce that, final Function2 p) {
         return IterableOnceOps.corresponds$(this, that, p);
      }

      public final String mkString(final String start, final String sep, final String end) {
         return IterableOnceOps.mkString$(this, start, sep, end);
      }

      public final String mkString(final String sep) {
         return IterableOnceOps.mkString$(this, sep);
      }

      public final String mkString() {
         return IterableOnceOps.mkString$(this);
      }

      public scala.collection.mutable.StringBuilder addString(final scala.collection.mutable.StringBuilder b, final String start, final String sep, final String end) {
         return IterableOnceOps.addString$(this, b, start, sep, end);
      }

      public final scala.collection.mutable.StringBuilder addString(final scala.collection.mutable.StringBuilder b, final String sep) {
         return IterableOnceOps.addString$(this, b, sep);
      }

      public final scala.collection.mutable.StringBuilder addString(final scala.collection.mutable.StringBuilder b) {
         return IterableOnceOps.addString$(this, b);
      }

      public Object to(final Factory factory) {
         return IterableOnceOps.to$(this, factory);
      }

      /** @deprecated */
      public final Iterator toIterator() {
         return IterableOnceOps.toIterator$(this);
      }

      public List toList() {
         return IterableOnceOps.toList$(this);
      }

      public Vector toVector() {
         return IterableOnceOps.toVector$(this);
      }

      public Map toMap(final .less.colon.less ev) {
         return IterableOnceOps.toMap$(this, ev);
      }

      public Set toSet() {
         return IterableOnceOps.toSet$(this);
      }

      public Seq toSeq() {
         return IterableOnceOps.toSeq$(this);
      }

      public IndexedSeq toIndexedSeq() {
         return IterableOnceOps.toIndexedSeq$(this);
      }

      /** @deprecated */
      public final Stream toStream() {
         return IterableOnceOps.toStream$(this);
      }

      public final Buffer toBuffer() {
         return IterableOnceOps.toBuffer$(this);
      }

      public Object toArray(final ClassTag evidence$2) {
         return IterableOnceOps.toArray$(this, evidence$2);
      }

      public Iterable reversed() {
         return IterableOnceOps.reversed$(this);
      }

      public Stepper stepper(final StepperShape shape) {
         return IterableOnce.stepper$(this, shape);
      }

      public int knownSize() {
         return IterableOnce.knownSize$(this);
      }

      public Signalling signalDelegate() {
         return this.signalDelegate;
      }

      public void signalDelegate_$eq(final Signalling x$1) {
         this.signalDelegate = x$1;
      }

      public boolean hasNext() {
         return this.scala$collection$parallel$IterableSplitter$Mapped$$$outer().hasNext();
      }

      public Object next() {
         return this.f.apply(this.scala$collection$parallel$IterableSplitter$Mapped$$$outer().next());
      }

      public int remaining() {
         return this.scala$collection$parallel$IterableSplitter$Mapped$$$outer().remaining();
      }

      public IterableSplitter dup() {
         return this.scala$collection$parallel$IterableSplitter$Mapped$$$outer().dup().map(this.f);
      }

      public Seq split() {
         return (Seq)this.scala$collection$parallel$IterableSplitter$Mapped$$$outer().split().map((x$7) -> x$7.map(this.f));
      }

      // $FF: synthetic method
      public IterableSplitter scala$collection$parallel$IterableSplitter$Mapped$$$outer() {
         return this.$outer;
      }

      public Mapped(final Function1 f) {
         this.f = f;
         if (IterableSplitter.this == null) {
            throw null;
         } else {
            this.$outer = IterableSplitter.this;
            super();
            IterableOnce.$init$(this);
            IterableOnceOps.$init$(this);
            Iterator.$init$(this);
            RemainsIterator.$init$(this);
            AugmentedIterableIterator.$init$(this);
            DelegatedSignalling.$init$(this);
            IterableSplitter.$init$(this);
            this.signalDelegate_$eq(IterableSplitter.this.signalDelegate());
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class Appended implements IterableSplitter {
      private final IterableSplitter that;
      private IterableSplitter curr;
      private Signalling signalDelegate;
      // $FF: synthetic field
      public final IterableSplitter $outer;

      public Seq splitWithSignalling() {
         return IterableSplitter.super.splitWithSignalling();
      }

      public boolean shouldSplitFurther(final ParIterable coll, final int parallelismLevel) {
         return IterableSplitter.super.shouldSplitFurther(coll, parallelismLevel);
      }

      public String buildString(final Function1 closure) {
         return IterableSplitter.super.buildString(closure);
      }

      public String debugInformation() {
         return IterableSplitter.super.debugInformation();
      }

      public Taken newTaken(final int until) {
         return IterableSplitter.super.newTaken(until);
      }

      public Taken newSliceInternal(final Taken it, final int from1) {
         return IterableSplitter.super.newSliceInternal(it, from1);
      }

      public IterableSplitter drop(final int n) {
         return IterableSplitter.super.drop(n);
      }

      public IterableSplitter take(final int n) {
         return IterableSplitter.super.take(n);
      }

      public IterableSplitter slice(final int from1, final int until1) {
         return IterableSplitter.super.slice(from1, until1);
      }

      public IterableSplitter map(final Function1 f) {
         return IterableSplitter.super.map(f);
      }

      public Appended appendParIterable(final IterableSplitter that) {
         return IterableSplitter.super.appendParIterable(that);
      }

      public IterableSplitter zipParSeq(final SeqSplitter that) {
         return IterableSplitter.super.zipParSeq(that);
      }

      public ZippedAll zipAllParSeq(final SeqSplitter that, final Object thisElem, final Object thatElem) {
         return IterableSplitter.super.zipAllParSeq(that, thisElem, thatElem);
      }

      public boolean isAborted() {
         return DelegatedSignalling.isAborted$(this);
      }

      public void abort() {
         DelegatedSignalling.abort$(this);
      }

      public int indexFlag() {
         return DelegatedSignalling.indexFlag$(this);
      }

      public void setIndexFlag(final int f) {
         DelegatedSignalling.setIndexFlag$(this, f);
      }

      public void setIndexFlagIfGreater(final int f) {
         DelegatedSignalling.setIndexFlagIfGreater$(this, f);
      }

      public void setIndexFlagIfLesser(final int f) {
         DelegatedSignalling.setIndexFlagIfLesser$(this, f);
      }

      public int tag() {
         return DelegatedSignalling.tag$(this);
      }

      public int count(final Function1 p) {
         return AugmentedIterableIterator.count$(this, p);
      }

      public Object reduce(final Function2 op) {
         return AugmentedIterableIterator.reduce$(this, op);
      }

      public Object fold(final Object z, final Function2 op) {
         return AugmentedIterableIterator.fold$(this, z, op);
      }

      public Object sum(final Numeric num) {
         return AugmentedIterableIterator.sum$(this, num);
      }

      public Object product(final Numeric num) {
         return AugmentedIterableIterator.product$(this, num);
      }

      public Object min(final Ordering ord) {
         return AugmentedIterableIterator.min$(this, ord);
      }

      public Object max(final Ordering ord) {
         return AugmentedIterableIterator.max$(this, ord);
      }

      public Object reduceLeft(final int howmany, final Function2 op) {
         return AugmentedIterableIterator.reduceLeft$(this, howmany, op);
      }

      public Combiner map2combiner(final Function1 f, final Combiner cb) {
         return AugmentedIterableIterator.map2combiner$(this, f, cb);
      }

      public Combiner collect2combiner(final PartialFunction pf, final Combiner cb) {
         return AugmentedIterableIterator.collect2combiner$(this, pf, cb);
      }

      public Combiner flatmap2combiner(final Function1 f, final Combiner cb) {
         return AugmentedIterableIterator.flatmap2combiner$(this, f, cb);
      }

      public Builder copy2builder(final Builder b) {
         return AugmentedIterableIterator.copy2builder$(this, b);
      }

      public Combiner filter2combiner(final Function1 pred, final Combiner cb) {
         return AugmentedIterableIterator.filter2combiner$(this, pred, cb);
      }

      public Combiner filterNot2combiner(final Function1 pred, final Combiner cb) {
         return AugmentedIterableIterator.filterNot2combiner$(this, pred, cb);
      }

      public Tuple2 partition2combiners(final Function1 pred, final Combiner btrue, final Combiner bfalse) {
         return AugmentedIterableIterator.partition2combiners$(this, pred, btrue, bfalse);
      }

      public Combiner take2combiner(final int n, final Combiner cb) {
         return AugmentedIterableIterator.take2combiner$(this, n, cb);
      }

      public Combiner drop2combiner(final int n, final Combiner cb) {
         return AugmentedIterableIterator.drop2combiner$(this, n, cb);
      }

      public Combiner slice2combiner(final int from, final int until, final Combiner cb) {
         return AugmentedIterableIterator.slice2combiner$(this, from, until, cb);
      }

      public Tuple2 splitAt2combiners(final int at, final Combiner before, final Combiner after) {
         return AugmentedIterableIterator.splitAt2combiners$(this, at, before, after);
      }

      public Tuple2 takeWhile2combiner(final Function1 p, final Combiner cb) {
         return AugmentedIterableIterator.takeWhile2combiner$(this, p, cb);
      }

      public Tuple2 span2combiners(final Function1 p, final Combiner before, final Combiner after) {
         return AugmentedIterableIterator.span2combiners$(this, p, before, after);
      }

      public void scanToArray(final Object z, final Function2 op, final Object array, final int from) {
         AugmentedIterableIterator.scanToArray$(this, z, op, array, from);
      }

      public Combiner scanToCombiner(final Object startValue, final Function2 op, final Combiner cb) {
         return AugmentedIterableIterator.scanToCombiner$(this, startValue, op, cb);
      }

      public Combiner scanToCombiner(final int howmany, final Object startValue, final Function2 op, final Combiner cb) {
         return AugmentedIterableIterator.scanToCombiner$(this, howmany, startValue, op, cb);
      }

      public Combiner zip2combiner(final RemainsIterator otherpit, final Combiner cb) {
         return AugmentedIterableIterator.zip2combiner$(this, otherpit, cb);
      }

      public Combiner zipAll2combiner(final RemainsIterator that, final Object thiselem, final Object thatelem, final Combiner cb) {
         return AugmentedIterableIterator.zipAll2combiner$(this, that, thiselem, thatelem, cb);
      }

      public boolean isRemainingCheap() {
         return RemainsIterator.isRemainingCheap$(this);
      }

      /** @deprecated */
      public final boolean hasDefiniteSize() {
         return Iterator.hasDefiniteSize$(this);
      }

      public final Iterator iterator() {
         return Iterator.iterator$(this);
      }

      public Option nextOption() {
         return Iterator.nextOption$(this);
      }

      public boolean contains(final Object elem) {
         return Iterator.contains$(this, elem);
      }

      public BufferedIterator buffered() {
         return Iterator.buffered$(this);
      }

      public Iterator padTo(final int len, final Object elem) {
         return Iterator.padTo$(this, len, elem);
      }

      public Tuple2 partition(final Function1 p) {
         return Iterator.partition$(this, p);
      }

      public Iterator.GroupedIterator grouped(final int size) {
         return Iterator.grouped$(this, size);
      }

      public Iterator.GroupedIterator sliding(final int size, final int step) {
         return Iterator.sliding$(this, size, step);
      }

      public int sliding$default$2() {
         return Iterator.sliding$default$2$(this);
      }

      public Iterator scanLeft(final Object z, final Function2 op) {
         return Iterator.scanLeft$(this, z, op);
      }

      /** @deprecated */
      public Iterator scanRight(final Object z, final Function2 op) {
         return Iterator.scanRight$(this, z, op);
      }

      public int indexWhere(final Function1 p, final int from) {
         return Iterator.indexWhere$(this, p, from);
      }

      public int indexWhere$default$2() {
         return Iterator.indexWhere$default$2$(this);
      }

      public int indexOf(final Object elem) {
         return Iterator.indexOf$(this, elem);
      }

      public int indexOf(final Object elem, final int from) {
         return Iterator.indexOf$(this, elem, from);
      }

      public final int length() {
         return Iterator.length$(this);
      }

      public boolean isEmpty() {
         return Iterator.isEmpty$(this);
      }

      public Iterator filter(final Function1 p) {
         return Iterator.filter$(this, p);
      }

      public Iterator filterNot(final Function1 p) {
         return Iterator.filterNot$(this, p);
      }

      public Iterator filterImpl(final Function1 p, final boolean isFlipped) {
         return Iterator.filterImpl$(this, p, isFlipped);
      }

      public Iterator withFilter(final Function1 p) {
         return Iterator.withFilter$(this, p);
      }

      public Iterator collect(final PartialFunction pf) {
         return Iterator.collect$(this, pf);
      }

      public Iterator distinct() {
         return Iterator.distinct$(this);
      }

      public Iterator distinctBy(final Function1 f) {
         return Iterator.distinctBy$(this, f);
      }

      public Iterator flatMap(final Function1 f) {
         return Iterator.flatMap$(this, f);
      }

      public Iterator flatten(final Function1 ev) {
         return Iterator.flatten$(this, ev);
      }

      public Iterator concat(final Function0 xs) {
         return Iterator.concat$(this, xs);
      }

      public final Iterator $plus$plus(final Function0 xs) {
         return Iterator.$plus$plus$(this, xs);
      }

      public Iterator takeWhile(final Function1 p) {
         return Iterator.takeWhile$(this, p);
      }

      public Iterator dropWhile(final Function1 p) {
         return Iterator.dropWhile$(this, p);
      }

      public Tuple2 span(final Function1 p) {
         return Iterator.span$(this, p);
      }

      public Iterator sliceIterator(final int from, final int until) {
         return Iterator.sliceIterator$(this, from, until);
      }

      public Iterator zip(final IterableOnce that) {
         return Iterator.zip$(this, that);
      }

      public Iterator zipAll(final IterableOnce that, final Object thisElem, final Object thatElem) {
         return Iterator.zipAll$(this, that, thisElem, thatElem);
      }

      public Iterator zipWithIndex() {
         return Iterator.zipWithIndex$(this);
      }

      public boolean sameElements(final IterableOnce that) {
         return Iterator.sameElements$(this, that);
      }

      public Tuple2 duplicate() {
         return Iterator.duplicate$(this);
      }

      public Iterator patch(final int from, final Iterator patchElems, final int replaced) {
         return Iterator.patch$(this, from, patchElems, replaced);
      }

      public Iterator tapEach(final Function1 f) {
         return Iterator.tapEach$(this, f);
      }

      public String toString() {
         return Iterator.toString$(this);
      }

      /** @deprecated */
      public Iterator seq() {
         return Iterator.seq$(this);
      }

      public Tuple2 splitAt(final int n) {
         return IterableOnceOps.splitAt$(this, n);
      }

      public boolean isTraversableAgain() {
         return IterableOnceOps.isTraversableAgain$(this);
      }

      public void foreach(final Function1 f) {
         IterableOnceOps.foreach$(this, f);
      }

      public boolean forall(final Function1 p) {
         return IterableOnceOps.forall$(this, p);
      }

      public boolean exists(final Function1 p) {
         return IterableOnceOps.exists$(this, p);
      }

      public Option find(final Function1 p) {
         return IterableOnceOps.find$(this, p);
      }

      public Object foldLeft(final Object z, final Function2 op) {
         return IterableOnceOps.foldLeft$(this, z, op);
      }

      public Object foldRight(final Object z, final Function2 op) {
         return IterableOnceOps.foldRight$(this, z, op);
      }

      /** @deprecated */
      public final Object $div$colon(final Object z, final Function2 op) {
         return IterableOnceOps.$div$colon$(this, z, op);
      }

      /** @deprecated */
      public final Object $colon$bslash(final Object z, final Function2 op) {
         return IterableOnceOps.$colon$bslash$(this, z, op);
      }

      public Option reduceOption(final Function2 op) {
         return IterableOnceOps.reduceOption$(this, op);
      }

      public Object reduceLeft(final Function2 op) {
         return IterableOnceOps.reduceLeft$(this, op);
      }

      public Object reduceRight(final Function2 op) {
         return IterableOnceOps.reduceRight$(this, op);
      }

      public Option reduceLeftOption(final Function2 op) {
         return IterableOnceOps.reduceLeftOption$(this, op);
      }

      public Option reduceRightOption(final Function2 op) {
         return IterableOnceOps.reduceRightOption$(this, op);
      }

      public boolean nonEmpty() {
         return IterableOnceOps.nonEmpty$(this);
      }

      public int size() {
         return IterableOnceOps.size$(this);
      }

      /** @deprecated */
      public final void copyToBuffer(final Buffer dest) {
         IterableOnceOps.copyToBuffer$(this, dest);
      }

      public int copyToArray(final Object xs) {
         return IterableOnceOps.copyToArray$(this, xs);
      }

      public int copyToArray(final Object xs, final int start) {
         return IterableOnceOps.copyToArray$(this, xs, start);
      }

      public int copyToArray(final Object xs, final int start, final int len) {
         return IterableOnceOps.copyToArray$(this, xs, start, len);
      }

      public Option minOption(final Ordering ord) {
         return IterableOnceOps.minOption$(this, ord);
      }

      public Option maxOption(final Ordering ord) {
         return IterableOnceOps.maxOption$(this, ord);
      }

      public Object maxBy(final Function1 f, final Ordering ord) {
         return IterableOnceOps.maxBy$(this, f, ord);
      }

      public Option maxByOption(final Function1 f, final Ordering ord) {
         return IterableOnceOps.maxByOption$(this, f, ord);
      }

      public Object minBy(final Function1 f, final Ordering ord) {
         return IterableOnceOps.minBy$(this, f, ord);
      }

      public Option minByOption(final Function1 f, final Ordering ord) {
         return IterableOnceOps.minByOption$(this, f, ord);
      }

      public Option collectFirst(final PartialFunction pf) {
         return IterableOnceOps.collectFirst$(this, pf);
      }

      /** @deprecated */
      public Object aggregate(final Function0 z, final Function2 seqop, final Function2 combop) {
         return IterableOnceOps.aggregate$(this, z, seqop, combop);
      }

      public boolean corresponds(final IterableOnce that, final Function2 p) {
         return IterableOnceOps.corresponds$(this, that, p);
      }

      public final String mkString(final String start, final String sep, final String end) {
         return IterableOnceOps.mkString$(this, start, sep, end);
      }

      public final String mkString(final String sep) {
         return IterableOnceOps.mkString$(this, sep);
      }

      public final String mkString() {
         return IterableOnceOps.mkString$(this);
      }

      public scala.collection.mutable.StringBuilder addString(final scala.collection.mutable.StringBuilder b, final String start, final String sep, final String end) {
         return IterableOnceOps.addString$(this, b, start, sep, end);
      }

      public final scala.collection.mutable.StringBuilder addString(final scala.collection.mutable.StringBuilder b, final String sep) {
         return IterableOnceOps.addString$(this, b, sep);
      }

      public final scala.collection.mutable.StringBuilder addString(final scala.collection.mutable.StringBuilder b) {
         return IterableOnceOps.addString$(this, b);
      }

      public Object to(final Factory factory) {
         return IterableOnceOps.to$(this, factory);
      }

      /** @deprecated */
      public final Iterator toIterator() {
         return IterableOnceOps.toIterator$(this);
      }

      public List toList() {
         return IterableOnceOps.toList$(this);
      }

      public Vector toVector() {
         return IterableOnceOps.toVector$(this);
      }

      public Map toMap(final .less.colon.less ev) {
         return IterableOnceOps.toMap$(this, ev);
      }

      public Set toSet() {
         return IterableOnceOps.toSet$(this);
      }

      public Seq toSeq() {
         return IterableOnceOps.toSeq$(this);
      }

      public IndexedSeq toIndexedSeq() {
         return IterableOnceOps.toIndexedSeq$(this);
      }

      /** @deprecated */
      public final Stream toStream() {
         return IterableOnceOps.toStream$(this);
      }

      public final Buffer toBuffer() {
         return IterableOnceOps.toBuffer$(this);
      }

      public Object toArray(final ClassTag evidence$2) {
         return IterableOnceOps.toArray$(this, evidence$2);
      }

      public Iterable reversed() {
         return IterableOnceOps.reversed$(this);
      }

      public Stepper stepper(final StepperShape shape) {
         return IterableOnce.stepper$(this, shape);
      }

      public int knownSize() {
         return IterableOnce.knownSize$(this);
      }

      public Signalling signalDelegate() {
         return this.signalDelegate;
      }

      public void signalDelegate_$eq(final Signalling x$1) {
         this.signalDelegate = x$1;
      }

      public IterableSplitter that() {
         return this.that;
      }

      public IterableSplitter curr() {
         return this.curr;
      }

      public void curr_$eq(final IterableSplitter x$1) {
         this.curr = x$1;
      }

      public boolean hasNext() {
         if (this.curr().hasNext()) {
            return true;
         } else if (this.curr() == this.scala$collection$parallel$IterableSplitter$Appended$$$outer()) {
            this.curr_$eq(this.that());
            return this.curr().hasNext();
         } else {
            return false;
         }
      }

      public Object next() {
         if (this.curr() == this.scala$collection$parallel$IterableSplitter$Appended$$$outer()) {
            this.hasNext();
            return this.curr().next();
         } else {
            return this.curr().next();
         }
      }

      public int remaining() {
         return this.curr() == this.scala$collection$parallel$IterableSplitter$Appended$$$outer() ? this.curr().remaining() + this.that().remaining() : this.curr().remaining();
      }

      public boolean firstNonEmpty() {
         return this.curr() == this.scala$collection$parallel$IterableSplitter$Appended$$$outer() && this.curr().hasNext();
      }

      public IterableSplitter dup() {
         return this.scala$collection$parallel$IterableSplitter$Appended$$$outer().dup().appendParIterable(this.that());
      }

      public Seq split() {
         return (Seq)(this.firstNonEmpty() ? new scala.collection.immutable..colon.colon(this.curr(), new scala.collection.immutable..colon.colon(this.that(), scala.collection.immutable.Nil..MODULE$)) : this.curr().split());
      }

      // $FF: synthetic method
      public IterableSplitter scala$collection$parallel$IterableSplitter$Appended$$$outer() {
         return this.$outer;
      }

      public Appended(final IterableSplitter that) {
         this.that = that;
         if (IterableSplitter.this == null) {
            throw null;
         } else {
            this.$outer = IterableSplitter.this;
            super();
            IterableOnce.$init$(this);
            IterableOnceOps.$init$(this);
            Iterator.$init$(this);
            RemainsIterator.$init$(this);
            AugmentedIterableIterator.$init$(this);
            DelegatedSignalling.$init$(this);
            IterableSplitter.$init$(this);
            this.signalDelegate_$eq(IterableSplitter.this.signalDelegate());
            this.curr = IterableSplitter.this;
         }
      }
   }

   public class Zipped implements IterableSplitter {
      private final SeqSplitter that;
      private Signalling signalDelegate;
      // $FF: synthetic field
      public final IterableSplitter $outer;

      public Seq splitWithSignalling() {
         return IterableSplitter.super.splitWithSignalling();
      }

      public boolean shouldSplitFurther(final ParIterable coll, final int parallelismLevel) {
         return IterableSplitter.super.shouldSplitFurther(coll, parallelismLevel);
      }

      public String buildString(final Function1 closure) {
         return IterableSplitter.super.buildString(closure);
      }

      public String debugInformation() {
         return IterableSplitter.super.debugInformation();
      }

      public Taken newTaken(final int until) {
         return IterableSplitter.super.newTaken(until);
      }

      public Taken newSliceInternal(final Taken it, final int from1) {
         return IterableSplitter.super.newSliceInternal(it, from1);
      }

      public IterableSplitter drop(final int n) {
         return IterableSplitter.super.drop(n);
      }

      public IterableSplitter take(final int n) {
         return IterableSplitter.super.take(n);
      }

      public IterableSplitter slice(final int from1, final int until1) {
         return IterableSplitter.super.slice(from1, until1);
      }

      public IterableSplitter map(final Function1 f) {
         return IterableSplitter.super.map(f);
      }

      public Appended appendParIterable(final IterableSplitter that) {
         return IterableSplitter.super.appendParIterable(that);
      }

      public IterableSplitter zipParSeq(final SeqSplitter that) {
         return IterableSplitter.super.zipParSeq(that);
      }

      public ZippedAll zipAllParSeq(final SeqSplitter that, final Object thisElem, final Object thatElem) {
         return IterableSplitter.super.zipAllParSeq(that, thisElem, thatElem);
      }

      public boolean isAborted() {
         return DelegatedSignalling.isAborted$(this);
      }

      public void abort() {
         DelegatedSignalling.abort$(this);
      }

      public int indexFlag() {
         return DelegatedSignalling.indexFlag$(this);
      }

      public void setIndexFlag(final int f) {
         DelegatedSignalling.setIndexFlag$(this, f);
      }

      public void setIndexFlagIfGreater(final int f) {
         DelegatedSignalling.setIndexFlagIfGreater$(this, f);
      }

      public void setIndexFlagIfLesser(final int f) {
         DelegatedSignalling.setIndexFlagIfLesser$(this, f);
      }

      public int tag() {
         return DelegatedSignalling.tag$(this);
      }

      public int count(final Function1 p) {
         return AugmentedIterableIterator.count$(this, p);
      }

      public Object reduce(final Function2 op) {
         return AugmentedIterableIterator.reduce$(this, op);
      }

      public Object fold(final Object z, final Function2 op) {
         return AugmentedIterableIterator.fold$(this, z, op);
      }

      public Object sum(final Numeric num) {
         return AugmentedIterableIterator.sum$(this, num);
      }

      public Object product(final Numeric num) {
         return AugmentedIterableIterator.product$(this, num);
      }

      public Object min(final Ordering ord) {
         return AugmentedIterableIterator.min$(this, ord);
      }

      public Object max(final Ordering ord) {
         return AugmentedIterableIterator.max$(this, ord);
      }

      public Object reduceLeft(final int howmany, final Function2 op) {
         return AugmentedIterableIterator.reduceLeft$(this, howmany, op);
      }

      public Combiner map2combiner(final Function1 f, final Combiner cb) {
         return AugmentedIterableIterator.map2combiner$(this, f, cb);
      }

      public Combiner collect2combiner(final PartialFunction pf, final Combiner cb) {
         return AugmentedIterableIterator.collect2combiner$(this, pf, cb);
      }

      public Combiner flatmap2combiner(final Function1 f, final Combiner cb) {
         return AugmentedIterableIterator.flatmap2combiner$(this, f, cb);
      }

      public Builder copy2builder(final Builder b) {
         return AugmentedIterableIterator.copy2builder$(this, b);
      }

      public Combiner filter2combiner(final Function1 pred, final Combiner cb) {
         return AugmentedIterableIterator.filter2combiner$(this, pred, cb);
      }

      public Combiner filterNot2combiner(final Function1 pred, final Combiner cb) {
         return AugmentedIterableIterator.filterNot2combiner$(this, pred, cb);
      }

      public Tuple2 partition2combiners(final Function1 pred, final Combiner btrue, final Combiner bfalse) {
         return AugmentedIterableIterator.partition2combiners$(this, pred, btrue, bfalse);
      }

      public Combiner take2combiner(final int n, final Combiner cb) {
         return AugmentedIterableIterator.take2combiner$(this, n, cb);
      }

      public Combiner drop2combiner(final int n, final Combiner cb) {
         return AugmentedIterableIterator.drop2combiner$(this, n, cb);
      }

      public Combiner slice2combiner(final int from, final int until, final Combiner cb) {
         return AugmentedIterableIterator.slice2combiner$(this, from, until, cb);
      }

      public Tuple2 splitAt2combiners(final int at, final Combiner before, final Combiner after) {
         return AugmentedIterableIterator.splitAt2combiners$(this, at, before, after);
      }

      public Tuple2 takeWhile2combiner(final Function1 p, final Combiner cb) {
         return AugmentedIterableIterator.takeWhile2combiner$(this, p, cb);
      }

      public Tuple2 span2combiners(final Function1 p, final Combiner before, final Combiner after) {
         return AugmentedIterableIterator.span2combiners$(this, p, before, after);
      }

      public void scanToArray(final Object z, final Function2 op, final Object array, final int from) {
         AugmentedIterableIterator.scanToArray$(this, z, op, array, from);
      }

      public Combiner scanToCombiner(final Object startValue, final Function2 op, final Combiner cb) {
         return AugmentedIterableIterator.scanToCombiner$(this, startValue, op, cb);
      }

      public Combiner scanToCombiner(final int howmany, final Object startValue, final Function2 op, final Combiner cb) {
         return AugmentedIterableIterator.scanToCombiner$(this, howmany, startValue, op, cb);
      }

      public Combiner zip2combiner(final RemainsIterator otherpit, final Combiner cb) {
         return AugmentedIterableIterator.zip2combiner$(this, otherpit, cb);
      }

      public Combiner zipAll2combiner(final RemainsIterator that, final Object thiselem, final Object thatelem, final Combiner cb) {
         return AugmentedIterableIterator.zipAll2combiner$(this, that, thiselem, thatelem, cb);
      }

      public boolean isRemainingCheap() {
         return RemainsIterator.isRemainingCheap$(this);
      }

      /** @deprecated */
      public final boolean hasDefiniteSize() {
         return Iterator.hasDefiniteSize$(this);
      }

      public final Iterator iterator() {
         return Iterator.iterator$(this);
      }

      public Option nextOption() {
         return Iterator.nextOption$(this);
      }

      public boolean contains(final Object elem) {
         return Iterator.contains$(this, elem);
      }

      public BufferedIterator buffered() {
         return Iterator.buffered$(this);
      }

      public Iterator padTo(final int len, final Object elem) {
         return Iterator.padTo$(this, len, elem);
      }

      public Tuple2 partition(final Function1 p) {
         return Iterator.partition$(this, p);
      }

      public Iterator.GroupedIterator grouped(final int size) {
         return Iterator.grouped$(this, size);
      }

      public Iterator.GroupedIterator sliding(final int size, final int step) {
         return Iterator.sliding$(this, size, step);
      }

      public int sliding$default$2() {
         return Iterator.sliding$default$2$(this);
      }

      public Iterator scanLeft(final Object z, final Function2 op) {
         return Iterator.scanLeft$(this, z, op);
      }

      /** @deprecated */
      public Iterator scanRight(final Object z, final Function2 op) {
         return Iterator.scanRight$(this, z, op);
      }

      public int indexWhere(final Function1 p, final int from) {
         return Iterator.indexWhere$(this, p, from);
      }

      public int indexWhere$default$2() {
         return Iterator.indexWhere$default$2$(this);
      }

      public int indexOf(final Object elem) {
         return Iterator.indexOf$(this, elem);
      }

      public int indexOf(final Object elem, final int from) {
         return Iterator.indexOf$(this, elem, from);
      }

      public final int length() {
         return Iterator.length$(this);
      }

      public boolean isEmpty() {
         return Iterator.isEmpty$(this);
      }

      public Iterator filter(final Function1 p) {
         return Iterator.filter$(this, p);
      }

      public Iterator filterNot(final Function1 p) {
         return Iterator.filterNot$(this, p);
      }

      public Iterator filterImpl(final Function1 p, final boolean isFlipped) {
         return Iterator.filterImpl$(this, p, isFlipped);
      }

      public Iterator withFilter(final Function1 p) {
         return Iterator.withFilter$(this, p);
      }

      public Iterator collect(final PartialFunction pf) {
         return Iterator.collect$(this, pf);
      }

      public Iterator distinct() {
         return Iterator.distinct$(this);
      }

      public Iterator distinctBy(final Function1 f) {
         return Iterator.distinctBy$(this, f);
      }

      public Iterator flatMap(final Function1 f) {
         return Iterator.flatMap$(this, f);
      }

      public Iterator flatten(final Function1 ev) {
         return Iterator.flatten$(this, ev);
      }

      public Iterator concat(final Function0 xs) {
         return Iterator.concat$(this, xs);
      }

      public final Iterator $plus$plus(final Function0 xs) {
         return Iterator.$plus$plus$(this, xs);
      }

      public Iterator takeWhile(final Function1 p) {
         return Iterator.takeWhile$(this, p);
      }

      public Iterator dropWhile(final Function1 p) {
         return Iterator.dropWhile$(this, p);
      }

      public Tuple2 span(final Function1 p) {
         return Iterator.span$(this, p);
      }

      public Iterator sliceIterator(final int from, final int until) {
         return Iterator.sliceIterator$(this, from, until);
      }

      public Iterator zip(final IterableOnce that) {
         return Iterator.zip$(this, that);
      }

      public Iterator zipAll(final IterableOnce that, final Object thisElem, final Object thatElem) {
         return Iterator.zipAll$(this, that, thisElem, thatElem);
      }

      public Iterator zipWithIndex() {
         return Iterator.zipWithIndex$(this);
      }

      public boolean sameElements(final IterableOnce that) {
         return Iterator.sameElements$(this, that);
      }

      public Tuple2 duplicate() {
         return Iterator.duplicate$(this);
      }

      public Iterator patch(final int from, final Iterator patchElems, final int replaced) {
         return Iterator.patch$(this, from, patchElems, replaced);
      }

      public Iterator tapEach(final Function1 f) {
         return Iterator.tapEach$(this, f);
      }

      public String toString() {
         return Iterator.toString$(this);
      }

      /** @deprecated */
      public Iterator seq() {
         return Iterator.seq$(this);
      }

      public Tuple2 splitAt(final int n) {
         return IterableOnceOps.splitAt$(this, n);
      }

      public boolean isTraversableAgain() {
         return IterableOnceOps.isTraversableAgain$(this);
      }

      public void foreach(final Function1 f) {
         IterableOnceOps.foreach$(this, f);
      }

      public boolean forall(final Function1 p) {
         return IterableOnceOps.forall$(this, p);
      }

      public boolean exists(final Function1 p) {
         return IterableOnceOps.exists$(this, p);
      }

      public Option find(final Function1 p) {
         return IterableOnceOps.find$(this, p);
      }

      public Object foldLeft(final Object z, final Function2 op) {
         return IterableOnceOps.foldLeft$(this, z, op);
      }

      public Object foldRight(final Object z, final Function2 op) {
         return IterableOnceOps.foldRight$(this, z, op);
      }

      /** @deprecated */
      public final Object $div$colon(final Object z, final Function2 op) {
         return IterableOnceOps.$div$colon$(this, z, op);
      }

      /** @deprecated */
      public final Object $colon$bslash(final Object z, final Function2 op) {
         return IterableOnceOps.$colon$bslash$(this, z, op);
      }

      public Option reduceOption(final Function2 op) {
         return IterableOnceOps.reduceOption$(this, op);
      }

      public Object reduceLeft(final Function2 op) {
         return IterableOnceOps.reduceLeft$(this, op);
      }

      public Object reduceRight(final Function2 op) {
         return IterableOnceOps.reduceRight$(this, op);
      }

      public Option reduceLeftOption(final Function2 op) {
         return IterableOnceOps.reduceLeftOption$(this, op);
      }

      public Option reduceRightOption(final Function2 op) {
         return IterableOnceOps.reduceRightOption$(this, op);
      }

      public boolean nonEmpty() {
         return IterableOnceOps.nonEmpty$(this);
      }

      public int size() {
         return IterableOnceOps.size$(this);
      }

      /** @deprecated */
      public final void copyToBuffer(final Buffer dest) {
         IterableOnceOps.copyToBuffer$(this, dest);
      }

      public int copyToArray(final Object xs) {
         return IterableOnceOps.copyToArray$(this, xs);
      }

      public int copyToArray(final Object xs, final int start) {
         return IterableOnceOps.copyToArray$(this, xs, start);
      }

      public int copyToArray(final Object xs, final int start, final int len) {
         return IterableOnceOps.copyToArray$(this, xs, start, len);
      }

      public Option minOption(final Ordering ord) {
         return IterableOnceOps.minOption$(this, ord);
      }

      public Option maxOption(final Ordering ord) {
         return IterableOnceOps.maxOption$(this, ord);
      }

      public Object maxBy(final Function1 f, final Ordering ord) {
         return IterableOnceOps.maxBy$(this, f, ord);
      }

      public Option maxByOption(final Function1 f, final Ordering ord) {
         return IterableOnceOps.maxByOption$(this, f, ord);
      }

      public Object minBy(final Function1 f, final Ordering ord) {
         return IterableOnceOps.minBy$(this, f, ord);
      }

      public Option minByOption(final Function1 f, final Ordering ord) {
         return IterableOnceOps.minByOption$(this, f, ord);
      }

      public Option collectFirst(final PartialFunction pf) {
         return IterableOnceOps.collectFirst$(this, pf);
      }

      /** @deprecated */
      public Object aggregate(final Function0 z, final Function2 seqop, final Function2 combop) {
         return IterableOnceOps.aggregate$(this, z, seqop, combop);
      }

      public boolean corresponds(final IterableOnce that, final Function2 p) {
         return IterableOnceOps.corresponds$(this, that, p);
      }

      public final String mkString(final String start, final String sep, final String end) {
         return IterableOnceOps.mkString$(this, start, sep, end);
      }

      public final String mkString(final String sep) {
         return IterableOnceOps.mkString$(this, sep);
      }

      public final String mkString() {
         return IterableOnceOps.mkString$(this);
      }

      public scala.collection.mutable.StringBuilder addString(final scala.collection.mutable.StringBuilder b, final String start, final String sep, final String end) {
         return IterableOnceOps.addString$(this, b, start, sep, end);
      }

      public final scala.collection.mutable.StringBuilder addString(final scala.collection.mutable.StringBuilder b, final String sep) {
         return IterableOnceOps.addString$(this, b, sep);
      }

      public final scala.collection.mutable.StringBuilder addString(final scala.collection.mutable.StringBuilder b) {
         return IterableOnceOps.addString$(this, b);
      }

      public Object to(final Factory factory) {
         return IterableOnceOps.to$(this, factory);
      }

      /** @deprecated */
      public final Iterator toIterator() {
         return IterableOnceOps.toIterator$(this);
      }

      public List toList() {
         return IterableOnceOps.toList$(this);
      }

      public Vector toVector() {
         return IterableOnceOps.toVector$(this);
      }

      public Map toMap(final .less.colon.less ev) {
         return IterableOnceOps.toMap$(this, ev);
      }

      public Set toSet() {
         return IterableOnceOps.toSet$(this);
      }

      public Seq toSeq() {
         return IterableOnceOps.toSeq$(this);
      }

      public IndexedSeq toIndexedSeq() {
         return IterableOnceOps.toIndexedSeq$(this);
      }

      /** @deprecated */
      public final Stream toStream() {
         return IterableOnceOps.toStream$(this);
      }

      public final Buffer toBuffer() {
         return IterableOnceOps.toBuffer$(this);
      }

      public Object toArray(final ClassTag evidence$2) {
         return IterableOnceOps.toArray$(this, evidence$2);
      }

      public Iterable reversed() {
         return IterableOnceOps.reversed$(this);
      }

      public Stepper stepper(final StepperShape shape) {
         return IterableOnce.stepper$(this, shape);
      }

      public int knownSize() {
         return IterableOnce.knownSize$(this);
      }

      public Signalling signalDelegate() {
         return this.signalDelegate;
      }

      public void signalDelegate_$eq(final Signalling x$1) {
         this.signalDelegate = x$1;
      }

      public SeqSplitter that() {
         return this.that;
      }

      public boolean hasNext() {
         return this.scala$collection$parallel$IterableSplitter$Zipped$$$outer().hasNext() && this.that().hasNext();
      }

      public Tuple2 next() {
         return new Tuple2(this.scala$collection$parallel$IterableSplitter$Zipped$$$outer().next(), this.that().next());
      }

      public int remaining() {
         return scala.runtime.RichInt..MODULE$.min$extension(scala.Predef..MODULE$.intWrapper(this.scala$collection$parallel$IterableSplitter$Zipped$$$outer().remaining()), this.that().remaining());
      }

      public IterableSplitter dup() {
         return this.scala$collection$parallel$IterableSplitter$Zipped$$$outer().dup().zipParSeq(this.that());
      }

      public Seq split() {
         Seq selfs = this.scala$collection$parallel$IterableSplitter$Zipped$$$outer().split();
         Seq sizes = (Seq)selfs.map((x$8) -> BoxesRunTime.boxToInteger($anonfun$split$3(x$8)));
         Seq thats = this.that().psplit(sizes);
         return (Seq)((IterableOps)selfs.zip(thats)).map((p) -> ((IterableSplitter)p._1()).zipParSeq((SeqSplitter)p._2()));
      }

      // $FF: synthetic method
      public IterableSplitter scala$collection$parallel$IterableSplitter$Zipped$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final int $anonfun$split$3(final IterableSplitter x$8) {
         return x$8.remaining();
      }

      public Zipped(final SeqSplitter that) {
         this.that = that;
         if (IterableSplitter.this == null) {
            throw null;
         } else {
            this.$outer = IterableSplitter.this;
            super();
            IterableOnce.$init$(this);
            IterableOnceOps.$init$(this);
            Iterator.$init$(this);
            RemainsIterator.$init$(this);
            AugmentedIterableIterator.$init$(this);
            DelegatedSignalling.$init$(this);
            IterableSplitter.$init$(this);
            this.signalDelegate_$eq(IterableSplitter.this.signalDelegate());
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class ZippedAll implements IterableSplitter {
      private final SeqSplitter that;
      private final Object thiselem;
      private final Object thatelem;
      private Signalling signalDelegate;
      // $FF: synthetic field
      public final IterableSplitter $outer;

      public Seq splitWithSignalling() {
         return IterableSplitter.super.splitWithSignalling();
      }

      public boolean shouldSplitFurther(final ParIterable coll, final int parallelismLevel) {
         return IterableSplitter.super.shouldSplitFurther(coll, parallelismLevel);
      }

      public String buildString(final Function1 closure) {
         return IterableSplitter.super.buildString(closure);
      }

      public String debugInformation() {
         return IterableSplitter.super.debugInformation();
      }

      public Taken newTaken(final int until) {
         return IterableSplitter.super.newTaken(until);
      }

      public Taken newSliceInternal(final Taken it, final int from1) {
         return IterableSplitter.super.newSliceInternal(it, from1);
      }

      public IterableSplitter drop(final int n) {
         return IterableSplitter.super.drop(n);
      }

      public IterableSplitter take(final int n) {
         return IterableSplitter.super.take(n);
      }

      public IterableSplitter slice(final int from1, final int until1) {
         return IterableSplitter.super.slice(from1, until1);
      }

      public IterableSplitter map(final Function1 f) {
         return IterableSplitter.super.map(f);
      }

      public Appended appendParIterable(final IterableSplitter that) {
         return IterableSplitter.super.appendParIterable(that);
      }

      public IterableSplitter zipParSeq(final SeqSplitter that) {
         return IterableSplitter.super.zipParSeq(that);
      }

      public ZippedAll zipAllParSeq(final SeqSplitter that, final Object thisElem, final Object thatElem) {
         return IterableSplitter.super.zipAllParSeq(that, thisElem, thatElem);
      }

      public boolean isAborted() {
         return DelegatedSignalling.isAborted$(this);
      }

      public void abort() {
         DelegatedSignalling.abort$(this);
      }

      public int indexFlag() {
         return DelegatedSignalling.indexFlag$(this);
      }

      public void setIndexFlag(final int f) {
         DelegatedSignalling.setIndexFlag$(this, f);
      }

      public void setIndexFlagIfGreater(final int f) {
         DelegatedSignalling.setIndexFlagIfGreater$(this, f);
      }

      public void setIndexFlagIfLesser(final int f) {
         DelegatedSignalling.setIndexFlagIfLesser$(this, f);
      }

      public int tag() {
         return DelegatedSignalling.tag$(this);
      }

      public int count(final Function1 p) {
         return AugmentedIterableIterator.count$(this, p);
      }

      public Object reduce(final Function2 op) {
         return AugmentedIterableIterator.reduce$(this, op);
      }

      public Object fold(final Object z, final Function2 op) {
         return AugmentedIterableIterator.fold$(this, z, op);
      }

      public Object sum(final Numeric num) {
         return AugmentedIterableIterator.sum$(this, num);
      }

      public Object product(final Numeric num) {
         return AugmentedIterableIterator.product$(this, num);
      }

      public Object min(final Ordering ord) {
         return AugmentedIterableIterator.min$(this, ord);
      }

      public Object max(final Ordering ord) {
         return AugmentedIterableIterator.max$(this, ord);
      }

      public Object reduceLeft(final int howmany, final Function2 op) {
         return AugmentedIterableIterator.reduceLeft$(this, howmany, op);
      }

      public Combiner map2combiner(final Function1 f, final Combiner cb) {
         return AugmentedIterableIterator.map2combiner$(this, f, cb);
      }

      public Combiner collect2combiner(final PartialFunction pf, final Combiner cb) {
         return AugmentedIterableIterator.collect2combiner$(this, pf, cb);
      }

      public Combiner flatmap2combiner(final Function1 f, final Combiner cb) {
         return AugmentedIterableIterator.flatmap2combiner$(this, f, cb);
      }

      public Builder copy2builder(final Builder b) {
         return AugmentedIterableIterator.copy2builder$(this, b);
      }

      public Combiner filter2combiner(final Function1 pred, final Combiner cb) {
         return AugmentedIterableIterator.filter2combiner$(this, pred, cb);
      }

      public Combiner filterNot2combiner(final Function1 pred, final Combiner cb) {
         return AugmentedIterableIterator.filterNot2combiner$(this, pred, cb);
      }

      public Tuple2 partition2combiners(final Function1 pred, final Combiner btrue, final Combiner bfalse) {
         return AugmentedIterableIterator.partition2combiners$(this, pred, btrue, bfalse);
      }

      public Combiner take2combiner(final int n, final Combiner cb) {
         return AugmentedIterableIterator.take2combiner$(this, n, cb);
      }

      public Combiner drop2combiner(final int n, final Combiner cb) {
         return AugmentedIterableIterator.drop2combiner$(this, n, cb);
      }

      public Combiner slice2combiner(final int from, final int until, final Combiner cb) {
         return AugmentedIterableIterator.slice2combiner$(this, from, until, cb);
      }

      public Tuple2 splitAt2combiners(final int at, final Combiner before, final Combiner after) {
         return AugmentedIterableIterator.splitAt2combiners$(this, at, before, after);
      }

      public Tuple2 takeWhile2combiner(final Function1 p, final Combiner cb) {
         return AugmentedIterableIterator.takeWhile2combiner$(this, p, cb);
      }

      public Tuple2 span2combiners(final Function1 p, final Combiner before, final Combiner after) {
         return AugmentedIterableIterator.span2combiners$(this, p, before, after);
      }

      public void scanToArray(final Object z, final Function2 op, final Object array, final int from) {
         AugmentedIterableIterator.scanToArray$(this, z, op, array, from);
      }

      public Combiner scanToCombiner(final Object startValue, final Function2 op, final Combiner cb) {
         return AugmentedIterableIterator.scanToCombiner$(this, startValue, op, cb);
      }

      public Combiner scanToCombiner(final int howmany, final Object startValue, final Function2 op, final Combiner cb) {
         return AugmentedIterableIterator.scanToCombiner$(this, howmany, startValue, op, cb);
      }

      public Combiner zip2combiner(final RemainsIterator otherpit, final Combiner cb) {
         return AugmentedIterableIterator.zip2combiner$(this, otherpit, cb);
      }

      public Combiner zipAll2combiner(final RemainsIterator that, final Object thiselem, final Object thatelem, final Combiner cb) {
         return AugmentedIterableIterator.zipAll2combiner$(this, that, thiselem, thatelem, cb);
      }

      public boolean isRemainingCheap() {
         return RemainsIterator.isRemainingCheap$(this);
      }

      /** @deprecated */
      public final boolean hasDefiniteSize() {
         return Iterator.hasDefiniteSize$(this);
      }

      public final Iterator iterator() {
         return Iterator.iterator$(this);
      }

      public Option nextOption() {
         return Iterator.nextOption$(this);
      }

      public boolean contains(final Object elem) {
         return Iterator.contains$(this, elem);
      }

      public BufferedIterator buffered() {
         return Iterator.buffered$(this);
      }

      public Iterator padTo(final int len, final Object elem) {
         return Iterator.padTo$(this, len, elem);
      }

      public Tuple2 partition(final Function1 p) {
         return Iterator.partition$(this, p);
      }

      public Iterator.GroupedIterator grouped(final int size) {
         return Iterator.grouped$(this, size);
      }

      public Iterator.GroupedIterator sliding(final int size, final int step) {
         return Iterator.sliding$(this, size, step);
      }

      public int sliding$default$2() {
         return Iterator.sliding$default$2$(this);
      }

      public Iterator scanLeft(final Object z, final Function2 op) {
         return Iterator.scanLeft$(this, z, op);
      }

      /** @deprecated */
      public Iterator scanRight(final Object z, final Function2 op) {
         return Iterator.scanRight$(this, z, op);
      }

      public int indexWhere(final Function1 p, final int from) {
         return Iterator.indexWhere$(this, p, from);
      }

      public int indexWhere$default$2() {
         return Iterator.indexWhere$default$2$(this);
      }

      public int indexOf(final Object elem) {
         return Iterator.indexOf$(this, elem);
      }

      public int indexOf(final Object elem, final int from) {
         return Iterator.indexOf$(this, elem, from);
      }

      public final int length() {
         return Iterator.length$(this);
      }

      public boolean isEmpty() {
         return Iterator.isEmpty$(this);
      }

      public Iterator filter(final Function1 p) {
         return Iterator.filter$(this, p);
      }

      public Iterator filterNot(final Function1 p) {
         return Iterator.filterNot$(this, p);
      }

      public Iterator filterImpl(final Function1 p, final boolean isFlipped) {
         return Iterator.filterImpl$(this, p, isFlipped);
      }

      public Iterator withFilter(final Function1 p) {
         return Iterator.withFilter$(this, p);
      }

      public Iterator collect(final PartialFunction pf) {
         return Iterator.collect$(this, pf);
      }

      public Iterator distinct() {
         return Iterator.distinct$(this);
      }

      public Iterator distinctBy(final Function1 f) {
         return Iterator.distinctBy$(this, f);
      }

      public Iterator flatMap(final Function1 f) {
         return Iterator.flatMap$(this, f);
      }

      public Iterator flatten(final Function1 ev) {
         return Iterator.flatten$(this, ev);
      }

      public Iterator concat(final Function0 xs) {
         return Iterator.concat$(this, xs);
      }

      public final Iterator $plus$plus(final Function0 xs) {
         return Iterator.$plus$plus$(this, xs);
      }

      public Iterator takeWhile(final Function1 p) {
         return Iterator.takeWhile$(this, p);
      }

      public Iterator dropWhile(final Function1 p) {
         return Iterator.dropWhile$(this, p);
      }

      public Tuple2 span(final Function1 p) {
         return Iterator.span$(this, p);
      }

      public Iterator sliceIterator(final int from, final int until) {
         return Iterator.sliceIterator$(this, from, until);
      }

      public Iterator zip(final IterableOnce that) {
         return Iterator.zip$(this, that);
      }

      public Iterator zipAll(final IterableOnce that, final Object thisElem, final Object thatElem) {
         return Iterator.zipAll$(this, that, thisElem, thatElem);
      }

      public Iterator zipWithIndex() {
         return Iterator.zipWithIndex$(this);
      }

      public boolean sameElements(final IterableOnce that) {
         return Iterator.sameElements$(this, that);
      }

      public Tuple2 duplicate() {
         return Iterator.duplicate$(this);
      }

      public Iterator patch(final int from, final Iterator patchElems, final int replaced) {
         return Iterator.patch$(this, from, patchElems, replaced);
      }

      public Iterator tapEach(final Function1 f) {
         return Iterator.tapEach$(this, f);
      }

      public String toString() {
         return Iterator.toString$(this);
      }

      /** @deprecated */
      public Iterator seq() {
         return Iterator.seq$(this);
      }

      public Tuple2 splitAt(final int n) {
         return IterableOnceOps.splitAt$(this, n);
      }

      public boolean isTraversableAgain() {
         return IterableOnceOps.isTraversableAgain$(this);
      }

      public void foreach(final Function1 f) {
         IterableOnceOps.foreach$(this, f);
      }

      public boolean forall(final Function1 p) {
         return IterableOnceOps.forall$(this, p);
      }

      public boolean exists(final Function1 p) {
         return IterableOnceOps.exists$(this, p);
      }

      public Option find(final Function1 p) {
         return IterableOnceOps.find$(this, p);
      }

      public Object foldLeft(final Object z, final Function2 op) {
         return IterableOnceOps.foldLeft$(this, z, op);
      }

      public Object foldRight(final Object z, final Function2 op) {
         return IterableOnceOps.foldRight$(this, z, op);
      }

      /** @deprecated */
      public final Object $div$colon(final Object z, final Function2 op) {
         return IterableOnceOps.$div$colon$(this, z, op);
      }

      /** @deprecated */
      public final Object $colon$bslash(final Object z, final Function2 op) {
         return IterableOnceOps.$colon$bslash$(this, z, op);
      }

      public Option reduceOption(final Function2 op) {
         return IterableOnceOps.reduceOption$(this, op);
      }

      public Object reduceLeft(final Function2 op) {
         return IterableOnceOps.reduceLeft$(this, op);
      }

      public Object reduceRight(final Function2 op) {
         return IterableOnceOps.reduceRight$(this, op);
      }

      public Option reduceLeftOption(final Function2 op) {
         return IterableOnceOps.reduceLeftOption$(this, op);
      }

      public Option reduceRightOption(final Function2 op) {
         return IterableOnceOps.reduceRightOption$(this, op);
      }

      public boolean nonEmpty() {
         return IterableOnceOps.nonEmpty$(this);
      }

      public int size() {
         return IterableOnceOps.size$(this);
      }

      /** @deprecated */
      public final void copyToBuffer(final Buffer dest) {
         IterableOnceOps.copyToBuffer$(this, dest);
      }

      public int copyToArray(final Object xs) {
         return IterableOnceOps.copyToArray$(this, xs);
      }

      public int copyToArray(final Object xs, final int start) {
         return IterableOnceOps.copyToArray$(this, xs, start);
      }

      public int copyToArray(final Object xs, final int start, final int len) {
         return IterableOnceOps.copyToArray$(this, xs, start, len);
      }

      public Option minOption(final Ordering ord) {
         return IterableOnceOps.minOption$(this, ord);
      }

      public Option maxOption(final Ordering ord) {
         return IterableOnceOps.maxOption$(this, ord);
      }

      public Object maxBy(final Function1 f, final Ordering ord) {
         return IterableOnceOps.maxBy$(this, f, ord);
      }

      public Option maxByOption(final Function1 f, final Ordering ord) {
         return IterableOnceOps.maxByOption$(this, f, ord);
      }

      public Object minBy(final Function1 f, final Ordering ord) {
         return IterableOnceOps.minBy$(this, f, ord);
      }

      public Option minByOption(final Function1 f, final Ordering ord) {
         return IterableOnceOps.minByOption$(this, f, ord);
      }

      public Option collectFirst(final PartialFunction pf) {
         return IterableOnceOps.collectFirst$(this, pf);
      }

      /** @deprecated */
      public Object aggregate(final Function0 z, final Function2 seqop, final Function2 combop) {
         return IterableOnceOps.aggregate$(this, z, seqop, combop);
      }

      public boolean corresponds(final IterableOnce that, final Function2 p) {
         return IterableOnceOps.corresponds$(this, that, p);
      }

      public final String mkString(final String start, final String sep, final String end) {
         return IterableOnceOps.mkString$(this, start, sep, end);
      }

      public final String mkString(final String sep) {
         return IterableOnceOps.mkString$(this, sep);
      }

      public final String mkString() {
         return IterableOnceOps.mkString$(this);
      }

      public scala.collection.mutable.StringBuilder addString(final scala.collection.mutable.StringBuilder b, final String start, final String sep, final String end) {
         return IterableOnceOps.addString$(this, b, start, sep, end);
      }

      public final scala.collection.mutable.StringBuilder addString(final scala.collection.mutable.StringBuilder b, final String sep) {
         return IterableOnceOps.addString$(this, b, sep);
      }

      public final scala.collection.mutable.StringBuilder addString(final scala.collection.mutable.StringBuilder b) {
         return IterableOnceOps.addString$(this, b);
      }

      public Object to(final Factory factory) {
         return IterableOnceOps.to$(this, factory);
      }

      /** @deprecated */
      public final Iterator toIterator() {
         return IterableOnceOps.toIterator$(this);
      }

      public List toList() {
         return IterableOnceOps.toList$(this);
      }

      public Vector toVector() {
         return IterableOnceOps.toVector$(this);
      }

      public Map toMap(final .less.colon.less ev) {
         return IterableOnceOps.toMap$(this, ev);
      }

      public Set toSet() {
         return IterableOnceOps.toSet$(this);
      }

      public Seq toSeq() {
         return IterableOnceOps.toSeq$(this);
      }

      public IndexedSeq toIndexedSeq() {
         return IterableOnceOps.toIndexedSeq$(this);
      }

      /** @deprecated */
      public final Stream toStream() {
         return IterableOnceOps.toStream$(this);
      }

      public final Buffer toBuffer() {
         return IterableOnceOps.toBuffer$(this);
      }

      public Object toArray(final ClassTag evidence$2) {
         return IterableOnceOps.toArray$(this, evidence$2);
      }

      public Iterable reversed() {
         return IterableOnceOps.reversed$(this);
      }

      public Stepper stepper(final StepperShape shape) {
         return IterableOnce.stepper$(this, shape);
      }

      public int knownSize() {
         return IterableOnce.knownSize$(this);
      }

      public Signalling signalDelegate() {
         return this.signalDelegate;
      }

      public void signalDelegate_$eq(final Signalling x$1) {
         this.signalDelegate = x$1;
      }

      public SeqSplitter that() {
         return this.that;
      }

      public Object thiselem() {
         return this.thiselem;
      }

      public Object thatelem() {
         return this.thatelem;
      }

      public boolean hasNext() {
         return this.scala$collection$parallel$IterableSplitter$ZippedAll$$$outer().hasNext() || this.that().hasNext();
      }

      public Tuple2 next() {
         if (this.scala$collection$parallel$IterableSplitter$ZippedAll$$$outer().hasNext()) {
            return this.that().hasNext() ? new Tuple2(this.scala$collection$parallel$IterableSplitter$ZippedAll$$$outer().next(), this.that().next()) : new Tuple2(this.scala$collection$parallel$IterableSplitter$ZippedAll$$$outer().next(), this.thatelem());
         } else {
            return new Tuple2(this.thiselem(), this.that().next());
         }
      }

      public int remaining() {
         return scala.runtime.RichInt..MODULE$.max$extension(scala.Predef..MODULE$.intWrapper(this.scala$collection$parallel$IterableSplitter$ZippedAll$$$outer().remaining()), this.that().remaining());
      }

      public IterableSplitter dup() {
         return this.scala$collection$parallel$IterableSplitter$ZippedAll$$$outer().dup().zipAllParSeq(this.that(), this.thiselem(), this.thatelem());
      }

      public Seq split() {
         int selfrem = this.scala$collection$parallel$IterableSplitter$ZippedAll$$$outer().remaining();
         int thatrem = this.that().remaining();
         IterableSplitter thisit = (IterableSplitter)(selfrem < thatrem ? this.scala$collection$parallel$IterableSplitter$ZippedAll$$$outer().appendParIterable(scala.collection.parallel.immutable.package$.MODULE$.repetition(this.thiselem(), thatrem - selfrem).splitter()) : this.scala$collection$parallel$IterableSplitter$ZippedAll$$$outer());
         SeqSplitter thatit = (SeqSplitter)(selfrem > thatrem ? this.that().appendParSeq(scala.collection.parallel.immutable.package$.MODULE$.repetition(this.thatelem(), selfrem - thatrem).splitter()) : this.that());
         IterableSplitter zipped = thisit.zipParSeq(thatit);
         return zipped.split();
      }

      // $FF: synthetic method
      public IterableSplitter scala$collection$parallel$IterableSplitter$ZippedAll$$$outer() {
         return this.$outer;
      }

      public ZippedAll(final SeqSplitter that, final Object thiselem, final Object thatelem) {
         this.that = that;
         this.thiselem = thiselem;
         this.thatelem = thatelem;
         if (IterableSplitter.this == null) {
            throw null;
         } else {
            this.$outer = IterableSplitter.this;
            super();
            IterableOnce.$init$(this);
            IterableOnceOps.$init$(this);
            Iterator.$init$(this);
            RemainsIterator.$init$(this);
            AugmentedIterableIterator.$init$(this);
            DelegatedSignalling.$init$(this);
            IterableSplitter.$init$(this);
            this.signalDelegate_$eq(IterableSplitter.this.signalDelegate());
         }
      }
   }
}
