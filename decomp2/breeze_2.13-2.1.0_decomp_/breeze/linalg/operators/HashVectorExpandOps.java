package breeze.linalg.operators;

import breeze.collection.mutable.OpenAddressHashArray$mcD$sp;
import breeze.collection.mutable.OpenAddressHashArray$mcF$sp;
import breeze.collection.mutable.OpenAddressHashArray$mcI$sp;
import breeze.collection.mutable.OpenAddressHashArray$mcJ$sp;
import breeze.generic.UFunc;
import breeze.generic.UFunc$InPlaceImpl2$mcD$sp;
import breeze.generic.UFunc$InPlaceImpl2$mcF$sp;
import breeze.generic.UFunc$InPlaceImpl2$mcI$sp;
import breeze.linalg.HashVector;
import breeze.linalg.HashVector$;
import breeze.linalg.HashVector$mcD$sp;
import breeze.linalg.HashVector$mcF$sp;
import breeze.linalg.HashVector$mcI$sp;
import breeze.linalg.HashVector$mcJ$sp;
import breeze.linalg.Vector;
import breeze.linalg.VectorBuilder;
import breeze.linalg.VectorBuilder$;
import breeze.linalg.VectorBuilder$mcD$sp;
import breeze.linalg.VectorBuilder$mcF$sp;
import breeze.linalg.VectorBuilder$mcI$sp;
import breeze.linalg.VectorBuilder$mcJ$sp;
import breeze.linalg.support.CanTraverseValues;
import breeze.math.PowImplicits$;
import breeze.math.Semiring$;
import breeze.storage.ConfigurableDefault$;
import breeze.storage.Zero$;
import java.lang.invoke.SerializedLambda;
import scala.Function2;
import scala.MatchError;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.DoubleRef;
import scala.runtime.FloatRef;
import scala.runtime.IntRef;
import scala.runtime.LongRef;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011mg!C@\u0002\u0002A\u0005\u0019\u0011AA\b\u0011\u001d\tY\u0003\u0001C\u0001\u0003[A\u0011\"!\u000e\u0001\u0005\u0004%\u0019!a\u000e\t\u0013\u0005m\u0003A1A\u0005\u0004\u0005u\u0003\"CA5\u0001\t\u0007I1AA6\u0011%\t9\b\u0001b\u0001\n\u0007\tI\bC\u0005\u0002\u0006\u0002\u0011\r\u0011b\u0001\u0002\b\"I\u0011Q\u0013\u0001C\u0002\u0013\r\u0011q\u0013\u0005\n\u00037\u0003!\u0019!C\u0002\u0003;C\u0011\"!)\u0001\u0005\u0004%\u0019!a)\t\u0013\u0005\u001d\u0006A1A\u0005\u0004\u0005%\u0006\"CAZ\u0001\t\u0007I1AA[\u0011%\tI\f\u0001b\u0001\n\u0007\tY\fC\u0005\u0002@\u0002\u0011\r\u0011b\u0001\u0002B\"I\u0011Q\u0019\u0001C\u0002\u0013\r\u0011q\u0019\u0005\n\u0003#\u0004!\u0019!C\u0002\u0003'D\u0011\"a6\u0001\u0005\u0004%\u0019!!7\t\u0013\u0005u\u0007A1A\u0005\u0004\u0005}\u0007\"CAr\u0001\t\u0007I1AAs\u0011%\ty\u000f\u0001b\u0001\n\u0007\t\t\u0010C\u0005\u0002v\u0002\u0011\r\u0011b\u0001\u0002x\"I\u00111 \u0001C\u0002\u0013\r\u0011Q \u0005\n\u0005\u0003\u0001!\u0019!C\u0002\u0005\u0007A\u0011B!\u0004\u0001\u0005\u0004%\u0019Aa\u0004\t\u0013\tM\u0001A1A\u0005\u0004\tU\u0001\"\u0003B\r\u0001\t\u0007I1\u0001B\u000e\u0011%\u0011y\u0002\u0001b\u0001\n\u0007\u0011\t\u0003C\u0005\u0003,\u0001\u0011\r\u0011b\u0001\u0003.!I!\u0011\u0007\u0001C\u0002\u0013\r!1\u0007\u0005\n\u0005o\u0001!\u0019!C\u0002\u0005sA\u0011B!\u0010\u0001\u0005\u0004%\u0019Aa\u0010\t\u0013\t%\u0003A1A\u0005\u0004\t-\u0003\"\u0003B)\u0001\t\u0007I1\u0001B*\u0011%\u0011I\u0006\u0001b\u0001\n\u0007\u0011Y\u0006C\u0005\u0003b\u0001\u0011\r\u0011b\u0001\u0003d!I!q\r\u0001C\u0002\u0013\r!\u0011\u000e\u0005\n\u0005[\u0002!\u0019!C\u0002\u0005_B\u0011Ba\u001d\u0001\u0005\u0004%\u0019A!\u001e\t\u0013\te\u0004A1A\u0005\u0004\tm\u0004\"\u0003B@\u0001\t\u0007I1\u0001BA\u0011%\u0011)\t\u0001b\u0001\n\u0007\u00119\tC\u0005\u0003\f\u0002\u0011\r\u0011b\u0001\u0003\u000e\"I!\u0011\u0013\u0001C\u0002\u0013\r!1\u0013\u0005\n\u0005/\u0003!\u0019!C\u0002\u00053C\u0011B!(\u0001\u0005\u0004%\u0019Aa(\t\u0013\t\r\u0006A1A\u0005\u0004\t\u0015\u0006\"\u0003BU\u0001\t\u0007I1\u0001BV\u0011%\u0011)\f\u0001b\u0001\n\u0007\u00119\fC\u0005\u0003<\u0002\u0011\r\u0011b\u0001\u0003>\"I!\u0011\u0019\u0001C\u0002\u0013\r!1\u0019\u0005\n\u0005\u000f\u0004!\u0019!C\u0002\u0005\u0013D\u0011B!4\u0001\u0005\u0004%\u0019Aa4\t\u0013\tM\u0007A1A\u0005\u0004\tU\u0007\"\u0003Bm\u0001\t\u0007I1\u0001Bn\u0011%\u0011y\u000e\u0001b\u0001\n\u0007\u0011\t\u000fC\u0005\u0003f\u0002\u0011\r\u0011b\u0001\u0003h\"I!1\u001e\u0001C\u0002\u0013\r!Q\u001e\u0005\n\u0005c\u0004!\u0019!C\u0002\u0005gD\u0011Ba>\u0001\u0005\u0004%\u0019A!?\t\u0013\tu\bA1A\u0005\u0004\t}\b\"CB\u0002\u0001\t\u0007I1AB\u0003\u0011%\u0019I\u0001\u0001b\u0001\n\u0007\u0019Y\u0001C\u0005\u0004\u0010\u0001\u0011\r\u0011b\u0001\u0004\u0012!I1Q\u0003\u0001C\u0002\u0013\r1q\u0003\u0005\n\u00077\u0001!\u0019!C\u0002\u0007;A\u0011b!\t\u0001\u0005\u0004%\u0019aa\t\t\u0013\r\u001d\u0002A1A\u0005\u0004\r%\u0002\"CB\u0017\u0001\t\u0007I1AB\u0018\u0011%\u0019\u0019\u0004\u0001b\u0001\n\u0007\u0019)\u0004C\u0005\u0004:\u0001\u0011\r\u0011b\u0001\u0004<!I1q\b\u0001C\u0002\u0013\r1\u0011\t\u0005\n\u0007\u0017\u0002!\u0019!C\u0002\u0007\u001bB\u0011b!\u0015\u0001\u0005\u0004%\u0019aa\u0015\t\u0013\r]\u0003A1A\u0005\u0004\re\u0003\"CB/\u0001\t\u0007I1AB0\u0011%\u0019\u0019\u0007\u0001b\u0001\n\u0007\u0019)\u0007C\u0005\u0004j\u0001\u0011\r\u0011b\u0001\u0004l!I1q\u000e\u0001C\u0002\u0013\r1\u0011\u000f\u0005\n\u0007k\u0002!\u0019!C\u0002\u0007oB\u0011ba\u001f\u0001\u0005\u0004%\u0019a! \t\u0013\r\u0005\u0005A1A\u0005\u0004\r\r\u0005\"CBD\u0001\t\u0007I1ABE\u0011%\u0019i\t\u0001b\u0001\n\u0007\u0019y\tC\u0005\u0004\u0014\u0002\u0011\r\u0011b\u0001\u0004\u0016\"I1\u0011\u0014\u0001C\u0002\u0013\r11\u0014\u0005\n\u0007?\u0003!\u0019!C\u0002\u0007CC\u0011b!*\u0001\u0005\u0004%\u0019aa*\t\u0013\r=\u0006A1A\u0005\u0004\rE\u0006\"CB[\u0001\t\u0007I1AB\\\u0011%\u0019Y\f\u0001b\u0001\n\u0007\u0019i\fC\u0005\u0004B\u0002\u0011\r\u0011b\u0001\u0004D\"I1q\u0019\u0001C\u0002\u0013\r1\u0011\u001a\u0005\n\u0007\u001b\u0004!\u0019!C\u0002\u0007\u001fD\u0011ba5\u0001\u0005\u0004%\u0019a!6\t\u0013\re\u0007A1A\u0005\u0004\rm\u0007\"CBp\u0001\t\u0007I1ABq\u0011%\u0019)\u000f\u0001b\u0001\n\u0007\u00199\u000fC\u0005\u0004l\u0002\u0011\r\u0011b\u0001\u0004n\"I1\u0011\u001f\u0001C\u0002\u0013\r11\u001f\u0005\n\u0007o\u0004!\u0019!C\u0002\u0007sD\u0011b!@\u0001\u0005\u0004%\u0019aa@\t\u0013\u0011\r\u0001A1A\u0005\u0004\u0011\u0015\u0001\"\u0003C\u0005\u0001\t\u0007I1\u0001C\u0006\u0011%!y\u0001\u0001b\u0001\n\u0007!\t\u0002C\u0005\u0005\u0016\u0001\u0011\r\u0011b\u0001\u0005\u0018!IA1\u0004\u0001C\u0002\u0013\rAQ\u0004\u0005\n\tC\u0001!\u0019!C\u0002\tGA\u0011\u0002b\n\u0001\u0005\u0004%\u0019\u0001\"\u000b\t\u0013\u00115\u0002A1A\u0005\u0004\u0011=\u0002\"\u0003C\u001a\u0001\t\u0007I1\u0001C\u001b\u0011%!I\u0004\u0001b\u0001\n\u0007!Y\u0004C\u0005\u0005@\u0001\u0011\r\u0011b\u0001\u0005B!IAQ\t\u0001C\u0002\u0013\rAq\t\u0005\n\t\u0017\u0002!\u0019!C\u0002\t\u001bB\u0011\u0002\"\u0015\u0001\u0005\u0004%\u0019\u0001b\u0015\t\u0013\u0011]\u0003A1A\u0005\u0004\u0011e\u0003\"\u0003C/\u0001\t\u0007I1\u0001C0\u0011%!\u0019\u0007\u0001b\u0001\n\u0007!)\u0007C\u0005\u0005j\u0001\u0011\r\u0011b\u0001\u0005l!IAq\u0011\u0001C\u0002\u0013\rA\u0011\u0012\u0005\n\t\u001b\u0003!\u0019!C\u0002\t\u001fC\u0011\u0002b%\u0001\u0005\u0004%\u0019\u0001\"&\t\u0013\u0011e\u0005A1A\u0005\u0004\u0011m\u0005\"\u0003CU\u0001\t\u0007I1\u0001CV\u0011%!y\u000b\u0001b\u0001\n\u0007!\t\fC\u0005\u00056\u0002\u0011\r\u0011b\u0001\u00058\"9A1\u0018\u0001\u0005\u0004\u0011u&a\u0005%bg\"4Vm\u0019;pe\u0016C\b/\u00198e\u001fB\u001c(\u0002BA\u0002\u0003\u000b\t\u0011b\u001c9fe\u0006$xN]:\u000b\t\u0005\u001d\u0011\u0011B\u0001\u0007Y&t\u0017\r\\4\u000b\u0005\u0005-\u0011A\u00022sK\u0016TXm\u0001\u0001\u0014\u000f\u0001\t\t\"!\b\u0002&A!\u00111CA\r\u001b\t\t)B\u0003\u0002\u0002\u0018\u0005)1oY1mC&!\u00111DA\u000b\u0005\u0019\te.\u001f*fMB!\u0011qDA\u0011\u001b\t\t\t!\u0003\u0003\u0002$\u0005\u0005!!\u0003,fGR|'o\u00149t!\u0011\ty\"a\n\n\t\u0005%\u0012\u0011\u0001\u0002\u0016\u0011\u0006\u001c\bNV3di>\u0014xlR3oKJL7m\u00149t\u0003\u0019!\u0013N\\5uIQ\u0011\u0011q\u0006\t\u0005\u0003'\t\t$\u0003\u0003\u00024\u0005U!\u0001B+oSR\f\u0011%[7qY~\u001b8-\u00197f\u0003\u0012$w,\u00138QY\u0006\u001cWm\u0018%W?N{\u0006JV0J]R,\"!!\u000f\u0011\u0015\u0005m\u00121IA(\u0003+\nyE\u0004\u0003\u0002>\u0005}RBAA\u0003\u0013\u0011\t\t%!\u0002\u0002\u0011M\u001c\u0017\r\\3BI\u0012LA!!\u0012\u0002H\ta\u0011J\u001c)mC\u000e,\u0017*\u001c9mg%!\u0011\u0011JA&\u0005\u0015)f)\u001e8d\u0015\u0011\ti%!\u0003\u0002\u000f\u001d,g.\u001a:jGB1\u0011QHA)\u0003+JA!a\u0015\u0002\u0006\tQ\u0001*Y:i-\u0016\u001cGo\u001c:\u0011\t\u0005M\u0011qK\u0005\u0005\u00033\n)BA\u0002J]R\fA%[7qY~\u001b8-\u00197f\u0003\u0012$w,\u00138QY\u0006\u001cWm\u0018%W?N{\u0006JV0E_V\u0014G.Z\u000b\u0003\u0003?\u0002\"\"a\u000f\u0002D\u0005\u0005\u00141MA1!\u0019\ti$!\u0015\u0002dA!\u00111CA3\u0013\u0011\t9'!\u0006\u0003\r\u0011{WO\u00197f\u0003\rJW\u000e\u001d7`g\u000e\fG.Z!eI~Ke\u000e\u00157bG\u0016|\u0006JV0T?\"3vL\u00127pCR,\"!!\u001c\u0011\u0015\u0005m\u00121IA8\u0003c\ny\u0007\u0005\u0004\u0002>\u0005E\u0013\u0011\u000f\t\u0005\u0003'\t\u0019(\u0003\u0003\u0002v\u0005U!!\u0002$m_\u0006$\u0018AI5na2|6oY1mK\u0006#GmX%o!2\f7-Z0I-~\u001bv\f\u0013,`\u0019>tw-\u0006\u0002\u0002|AQ\u00111HA\"\u0003{\ny(! \u0011\r\u0005u\u0012\u0011KA@!\u0011\t\u0019\"!!\n\t\u0005\r\u0015Q\u0003\u0002\u0005\u0019>tw-A\u000fj[Bdwl\u00149`\u0011Z{\u0006JV0fc~CekX%oi~{\u0005/\u00113e+\t\tI\t\u0005\u0006\u0002\f\u0006E\u0015qJA(\u0003\u001frA!a\b\u0002\u000e&!\u0011qRA\u0001\u0003\u0015y\u0005/\u00113e\u0013\u0011\t\u0019*a\u0012\u0003\u000b%k\u0007\u000f\u001c\u001a\u0002A%l\u0007\u000f\\0Pa~Cek\u0018%W?\u0016\fx\f\u0013,`\t>,(\r\\3`\u001fB\fE\rZ\u000b\u0003\u00033\u0003\"\"a#\u0002\u0012\u0006\u0005\u0014\u0011MA1\u0003}IW\u000e\u001d7`\u001fB|\u0006JV0I-~+\u0017o\u0018%W?\u001acw.\u0019;`\u001fB\fE\rZ\u000b\u0003\u0003?\u0003\"\"a#\u0002\u0012\u0006=\u0014qNA8\u0003yIW\u000e\u001d7`\u001fB|\u0006JV0I-~+\u0017o\u0018%W?2{gnZ0Pa\u0006#G-\u0006\u0002\u0002&BQ\u00111RAI\u0003{\ni(! \u0002;%l\u0007\u000f\\0Pa~Cek\u0018%W?\u0016\fx\f\u0013,`\u0013:$xl\u00149Tk\n,\"!a+\u0011\u0015\u00055\u0016\u0011SA(\u0003\u001f\nyE\u0004\u0003\u0002 \u0005=\u0016\u0002BAY\u0003\u0003\tQa\u00149Tk\n\f\u0001%[7qY~{\u0005o\u0018%W?\"3v,Z9`\u0011Z{Fi\\;cY\u0016|v\n]*vEV\u0011\u0011q\u0017\t\u000b\u0003[\u000b\t*!\u0019\u0002b\u0005\u0005\u0014aH5na2|v\n]0I-~CekX3r?\"3vL\u00127pCR|v\n]*vEV\u0011\u0011Q\u0018\t\u000b\u0003[\u000b\t*a\u001c\u0002p\u0005=\u0014AH5na2|v\n]0I-~CekX3r?\"3v\fT8oO~{\u0005oU;c+\t\t\u0019\r\u0005\u0006\u0002.\u0006E\u0015QPA?\u0003{\n\u0001%[7qY~{\u0005/T;m'\u000e\fG.\u0019:`\u0011Z{\u0006JV0fc~CekX%oiV\u0011\u0011\u0011\u001a\t\u000b\u0003\u0017\f\t*a\u0014\u0002P\u0005=c\u0002BA\u0010\u0003\u001bLA!a4\u0002\u0002\u0005Yq\n]'vYN\u001b\u0017\r\\1s\u0003\rJW\u000e\u001d7`\u001fBlU\u000f\\*dC2\f'o\u0018%W?\"3v,Z9`\u0011Z{Fi\\;cY\u0016,\"!!6\u0011\u0015\u0005-\u0017\u0011SA1\u0003C\n\t'\u0001\u0012j[Bdwl\u00149Nk2\u001c6-\u00197be~Cek\u0018%W?\u0016\fx\f\u0013,`\r2|\u0017\r^\u000b\u0003\u00037\u0004\"\"a3\u0002\u0012\u0006=\u0014qNA8\u0003\u0005JW\u000e\u001d7`\u001fBlU\u000f\\*dC2\f'o\u0018%W?\"3v,Z9`\u0011Z{Fj\u001c8h+\t\t\t\u000f\u0005\u0006\u0002L\u0006E\u0015QPA?\u0003{\n1&[7qY~{\u0005o\u0018%W?\"3v,Z9`\u0011Z{F\u000e[:`]&d\u0007o\u001c;f]R|\u0016J\u001c;`\u001fB$\u0015N^\u000b\u0003\u0003O\u0004\"\"!;\u0002\u0012\u0006=\u0013qJA(\u001d\u0011\ty\"a;\n\t\u00055\u0018\u0011A\u0001\u0006\u001fB$\u0015N^\u0001/S6\u0004HnX(q?\"3v\f\u0013,`KF|\u0006JV0mQN|f.\u001b7q_R,g\u000e^0E_V\u0014G.Z0Pa\u0012Kg/\u0006\u0002\u0002tBQ\u0011\u0011^AI\u0003C\n\t'!\u0019\u0002[%l\u0007\u000f\\0Pa~Cek\u0018%W?\u0016\fx\f\u0013,`Y\"\u001cxL\\5ma>$XM\u001c;`\r2|\u0017\r^0Pa\u0012Kg/\u0006\u0002\u0002zBQ\u0011\u0011^AI\u0003_\ny'a\u001c\u0002Y%l\u0007\u000f\\0Pa~Cek\u0018%W?\u0016\fx\f\u0013,`Y\"\u001cxL\\5ma>$XM\u001c;`\u0019>twmX(q\t&4XCAA\u0000!)\tI/!%\u0002~\u0005u\u0014QP\u0001,S6\u0004HnX(q?\"3v\f\u0013,`KF|\u0006JV0mQN|f.\u001b7q_R,g\u000e^0J]R|v\n]'pIV\u0011!Q\u0001\t\u000b\u0005\u000f\t\t*a\u0014\u0002P\u0005=c\u0002BA\u0010\u0005\u0013IAAa\u0003\u0002\u0002\u0005)q\n]'pI\u0006q\u0013.\u001c9m?>\u0003x\f\u0013,`\u0011Z{V-]0I-~c\u0007n]0oS2\u0004x\u000e^3oi~#u.\u001e2mK~{\u0005/T8e+\t\u0011\t\u0002\u0005\u0006\u0003\b\u0005E\u0015\u0011MA1\u0003C\nQ&[7qY~{\u0005o\u0018%W?\"3v,Z9`\u0011Z{F\u000e[:`]&d\u0007o\u001c;f]R|f\t\\8bi~{\u0005/T8e+\t\u00119\u0002\u0005\u0006\u0003\b\u0005E\u0015qNA8\u0003_\nA&[7qY~{\u0005o\u0018%W?\"3v,Z9`\u0011Z{F\u000e[:`]&d\u0007o\u001c;f]R|Fj\u001c8h?>\u0003Xj\u001c3\u0016\u0005\tu\u0001C\u0003B\u0004\u0003#\u000bi(! \u0002~\u0005Y\u0013.\u001c9m?>\u0003x\f\u0013,`\u0011Z{V-]0I-~c\u0007n]0oS2\u0004x\u000e^3oi~Ke\u000e^0PaB{w/\u0006\u0002\u0003$AQ!QEAI\u0003\u001f\ny%a\u0014\u000f\t\u0005}!qE\u0005\u0005\u0005S\t\t!A\u0003PaB{w/\u0001\u0018j[Bdwl\u00149`\u0011Z{\u0006JV0fc~Cek\u00187ig~s\u0017\u000e\u001c9pi\u0016tGo\u0018#pk\ndWmX(q!><XC\u0001B\u0018!)\u0011)#!%\u0002b\u0005\u0005\u0014\u0011M\u0001.S6\u0004HnX(q?\"3v\f\u0013,`KF|\u0006JV0mQN|f.\u001b7q_R,g\u000e^0GY>\fGoX(q!><XC\u0001B\u001b!)\u0011)#!%\u0002p\u0005=\u0014qN\u0001-S6\u0004HnX(q?\"3v\f\u0013,`KF|\u0006JV0mQN|f.\u001b7q_R,g\u000e^0M_:<wl\u00149Q_^,\"Aa\u000f\u0011\u0015\t\u0015\u0012\u0011SA?\u0003{\ni(\u0001\u000fj[Bdwl\u00149`\u0011Z{fkX3r?\"3v,\u00138u?>\u0003\u0018\t\u001a3\u0016\u0005\t\u0005\u0003CCAF\u0003#\u000byEa\u0011\u0002PA1\u0011Q\bB#\u0003+JAAa\u0012\u0002\u0006\t1a+Z2u_J\fq$[7qY~{\u0005o\u0018%W?Z{V-]0I-~#u.\u001e2mK~{\u0005/\u00113e+\t\u0011i\u0005\u0005\u0006\u0002\f\u0006E\u0015\u0011\rB(\u0003C\u0002b!!\u0010\u0003F\u0005\r\u0014AH5na2|v\n]0I-~3v,Z9`\u0011Z{f\t\\8bi~{\u0005/\u00113e+\t\u0011)\u0006\u0005\u0006\u0002\f\u0006E\u0015q\u000eB,\u0003_\u0002b!!\u0010\u0003F\u0005E\u0014!H5na2|v\n]0I-~3v,Z9`\u0011Z{Fj\u001c8h?>\u0003\u0018\t\u001a3\u0016\u0005\tu\u0003CCAF\u0003#\u000biHa\u0018\u0002~A1\u0011Q\bB#\u0003\u007f\nA$[7qY~{\u0005o\u0018%W?Z{V-]0I-~Ke\u000e^0PaN+(-\u0006\u0002\u0003fAQ\u0011QVAI\u0003\u001f\u0012\u0019%a\u0014\u0002?%l\u0007\u000f\\0Pa~Cek\u0018,`KF|\u0006JV0E_V\u0014G.Z0PaN+(-\u0006\u0002\u0003lAQ\u0011QVAI\u0003C\u0012y%!\u0019\u0002=%l\u0007\u000f\\0Pa~Cek\u0018,`KF|\u0006JV0GY>\fGoX(q'V\u0014WC\u0001B9!)\ti+!%\u0002p\t]\u0013qN\u0001\u001eS6\u0004HnX(q?\"3vLV0fc~Cek\u0018'p]\u001e|v\n]*vEV\u0011!q\u000f\t\u000b\u0003[\u000b\t*! \u0003`\u0005u\u0014AI5na2|v\n]0I-~3v,Z9`\u0011Z{\u0016J\u001c;`\u001fBlU\u000f\\*dC2\f'/\u0006\u0002\u0003~AQ\u00111ZAI\u0003\u001f\u0012\u0019%a\u0014\u0002K%l\u0007\u000f\\0Pa~Cek\u0018,`KF|\u0006JV0E_V\u0014G.Z0Pa6+HnU2bY\u0006\u0014XC\u0001BB!)\tY-!%\u0002b\t=\u0013\u0011M\u0001%S6\u0004HnX(q?\"3vLV0fc~Cek\u0018$m_\u0006$xl\u00149Nk2\u001c6-\u00197beV\u0011!\u0011\u0012\t\u000b\u0003\u0017\f\t*a\u001c\u0003X\u0005=\u0014aI5na2|v\n]0I-~3v,Z9`\u0011Z{Fj\u001c8h?>\u0003X*\u001e7TG\u0006d\u0017M]\u000b\u0003\u0005\u001f\u0003\"\"a3\u0002\u0012\u0006u$qLA?\u0003qIW\u000e\u001d7`\u001fB|\u0006JV0W?\u0016\fx\f\u0013,`\u0013:$xl\u00149ESZ,\"A!&\u0011\u0015\u0005%\u0018\u0011SA(\u0005\u0007\ny%A\u0010j[Bdwl\u00149`\u0011Z{fkX3r?\"3v\fR8vE2,wl\u00149ESZ,\"Aa'\u0011\u0015\u0005%\u0018\u0011SA1\u0005\u001f\n\t'\u0001\u0010j[Bdwl\u00149`\u0011Z{fkX3r?\"3vL\u00127pCR|v\n\u001d#jmV\u0011!\u0011\u0015\t\u000b\u0003S\f\t*a\u001c\u0003X\u0005=\u0014!H5na2|v\n]0I-~3v,Z9`\u0011Z{Fj\u001c8h?>\u0003H)\u001b<\u0016\u0005\t\u001d\u0006CCAu\u0003#\u000biHa\u0018\u0002~\u0005a\u0012.\u001c9m?>\u0003x\f\u0013,`-~+\u0017o\u0018%W?&sGoX(q'\u0016$XC\u0001BW!)\u0011y+!%\u0002P\t\r\u0013q\n\b\u0005\u0003?\u0011\t,\u0003\u0003\u00034\u0006\u0005\u0011!B(q'\u0016$\u0018aH5na2|v\n]0I-~3v,Z9`\u0011Z{Fi\\;cY\u0016|v\n]*fiV\u0011!\u0011\u0018\t\u000b\u0005_\u000b\t*!\u0019\u0003P\u0005\u0005\u0014AH5na2|v\n]0I-~3v,Z9`\u0011Z{f\t\\8bi~{\u0005oU3u+\t\u0011y\f\u0005\u0006\u00030\u0006E\u0015q\u000eB,\u0003_\nQ$[7qY~{\u0005o\u0018%W?Z{V-]0I-~cuN\\4`\u001fB\u001cV\r^\u000b\u0003\u0005\u000b\u0004\"Ba,\u0002\u0012\u0006u$qLA?\u0003qIW\u000e\u001d7`\u001fB|\u0006JV0W?\u0016\fx\f\u0013,`\u0013:$xl\u00149N_\u0012,\"Aa3\u0011\u0015\t\u001d\u0011\u0011SA(\u0005\u0007\ny%A\u0010j[Bdwl\u00149`\u0011Z{fkX3r?\"3v\fR8vE2,wl\u00149N_\u0012,\"A!5\u0011\u0015\t\u001d\u0011\u0011SA1\u0005\u001f\n\t'\u0001\u0010j[Bdwl\u00149`\u0011Z{fkX3r?\"3vL\u00127pCR|v\n]'pIV\u0011!q\u001b\t\u000b\u0005\u000f\t\t*a\u001c\u0003X\u0005=\u0014!H5na2|v\n]0I-~3v,Z9`\u0011Z{Fj\u001c8h?>\u0003Xj\u001c3\u0016\u0005\tu\u0007C\u0003B\u0004\u0003#\u000biHa\u0018\u0002~\u0005a\u0012.\u001c9m?>\u0003x\f\u0013,`-~+\u0017o\u0018%W?&sGoX(q!><XC\u0001Br!)\u0011)#!%\u0002P\t\r\u0013qJ\u0001 S6\u0004HnX(q?\"3vLV0fc~Cek\u0018#pk\ndWmX(q!><XC\u0001Bu!)\u0011)#!%\u0002b\t=\u0013\u0011M\u0001\u001fS6\u0004HnX(q?\"3vLV0fc~Cek\u0018$m_\u0006$xl\u00149Q_^,\"Aa<\u0011\u0015\t\u0015\u0012\u0011SA8\u0005/\ny'A\u000fj[Bdwl\u00149`\u0011Z{fkX3r?\"3v\fT8oO~{\u0005\u000fU8x+\t\u0011)\u0010\u0005\u0006\u0003&\u0005E\u0015Q\u0010B0\u0003{\n\u0001%[7qY~{\u0005o\u0018%W?N{V-]0I-~\u000bG\rZ0J]R|v\n]!eIV\u0011!1 \t\u000b\u0003\u0017\u000b\t*a\u0014\u0002V\u0005=\u0013aI5na2|v\n]0I-~\u001bv,Z9`\u0011Z{\u0016\r\u001a3`\t>,(\r\\3`\u001fB\fE\rZ\u000b\u0003\u0007\u0003\u0001\"\"a#\u0002\u0012\u0006\u0005\u00141MA1\u0003\tJW\u000e\u001d7`\u001fB|\u0006JV0T?\u0016\fx\f\u0013,`C\u0012$wL\u00127pCR|v\n]!eIV\u00111q\u0001\t\u000b\u0003\u0017\u000b\t*a\u001c\u0002r\u0005=\u0014!I5na2|v\n]0I-~\u001bv,Z9`\u0011Z{\u0016\r\u001a3`\u0019>twmX(q\u0003\u0012$WCAB\u0007!)\tY)!%\u0002~\u0005}\u0014QP\u0001!S6\u0004HnX(q?\"3vlU0fc~CekX1eI~Ke\u000e^0PaN+(-\u0006\u0002\u0004\u0014AQ\u0011QVAI\u0003\u001f\n)&a\u0014\u0002G%l\u0007\u000f\\0Pa~CekX*`KF|\u0006JV0bI\u0012|Fi\\;cY\u0016|v\n]*vEV\u00111\u0011\u0004\t\u000b\u0003[\u000b\t*!\u0019\u0002d\u0005\u0005\u0014AI5na2|v\n]0I-~\u001bv,Z9`\u0011Z{\u0016\r\u001a3`\r2|\u0017\r^0PaN+(-\u0006\u0002\u0004 AQ\u0011QVAI\u0003_\n\t(a\u001c\u0002C%l\u0007\u000f\\0Pa~CekX*`KF|\u0006JV0bI\u0012|Fj\u001c8h?>\u00038+\u001e2\u0016\u0005\r\u0015\u0002CCAW\u0003#\u000bi(a \u0002~\u0005A\u0013.\u001c9m?>\u0003x\f\u0013,`'~+\u0017o\u0018%W?j,'o\\=`\u0013:$xl\u00149Nk2\u001c6-\u00197beV\u001111\u0006\t\u000b\u0003\u0017\f\t*a\u0014\u0002V\u0005=\u0013aK5na2|v\n]0I-~\u001bv,Z9`\u0011Z{&0\u001a:ps~#u.\u001e2mK~{\u0005/T;m'\u000e\fG.\u0019:\u0016\u0005\rE\u0002CCAf\u0003#\u000b\t'a\u0019\u0002b\u0005Q\u0013.\u001c9m?>\u0003x\f\u0013,`'~+\u0017o\u0018%W?j,'o\\=`\r2|\u0017\r^0Pa6+HnU2bY\u0006\u0014XCAB\u001c!)\tY-!%\u0002p\u0005E\u0014qN\u0001*S6\u0004HnX(q?\"3vlU0fc~Cek\u0018>fe>Lx\fT8oO~{\u0005/T;m'\u000e\fG.\u0019:\u0016\u0005\ru\u0002CCAf\u0003#\u000bi(a \u0002~\u0005A\u0013.\u001c9m?>\u0003x\f\u0013,`'~+\u0017o\u0018%W?j,'o\\=`\u0013:$xl\u00149Nk2l\u0015\r\u001e:jqV\u001111\t\t\u000b\u0007\u000b\n\t*a\u0014\u0002V\u0005=c\u0002BA\u0010\u0007\u000fJAa!\u0013\u0002\u0002\u0005Yq\n]'vY6\u000bGO]5y\u0003-JW\u000e\u001d7`\u001fB|\u0006JV0T?\u0016\fx\f\u0013,`u\u0016\u0014x._0E_V\u0014G.Z0Pa6+H.T1ue&DXCAB(!)\u0019)%!%\u0002b\u0005\r\u0014\u0011M\u0001+S6\u0004HnX(q?\"3vlU0fc~Cek\u0018>fe>LxL\u00127pCR|v\n]'vY6\u000bGO]5y+\t\u0019)\u0006\u0005\u0006\u0004F\u0005E\u0015qNA9\u0003_\n\u0011&[7qY~{\u0005o\u0018%W?N{V-]0I-~SXM]8z?2{gnZ0Pa6+H.T1ue&DXCAB.!)\u0019)%!%\u0002~\u0005}\u0014QP\u0001#S6\u0004HnX(q?\"3vlU0fc~Cek\u0018>fe>Lx,\u00138u?>\u0003H)\u001b<\u0016\u0005\r\u0005\u0004CCAu\u0003#\u000by%!\u0016\u0002P\u0005)\u0013.\u001c9m?>\u0003x\f\u0013,`'~+\u0017o\u0018%W?j,'o\\=`\t>,(\r\\3`\u001fB$\u0015N^\u000b\u0003\u0007O\u0002\"\"!;\u0002\u0012\u0006\u0005\u00141MA1\u0003\u0011JW\u000e\u001d7`\u001fB|\u0006JV0T?\u0016\fx\f\u0013,`u\u0016\u0014x._0GY>\fGoX(q\t&4XCAB7!)\tI/!%\u0002p\u0005E\u0014qN\u0001$S6\u0004HnX(q?\"3vlU0fc~Cek\u0018>fe>Lx\fT8oO~{\u0005\u000fR5w+\t\u0019\u0019\b\u0005\u0006\u0002j\u0006E\u0015QPA@\u0003{\n!%[7qY~{\u0005o\u0018%W?N{V-]0I-~SXM]8z?&sGoX(q\u001b>$WCAB=!)\u00119!!%\u0002P\u0005U\u0013qJ\u0001&S6\u0004HnX(q?\"3vlU0fc~Cek\u0018>fe>Lx\fR8vE2,wl\u00149N_\u0012,\"aa \u0011\u0015\t\u001d\u0011\u0011SA1\u0003G\n\t'\u0001\u0013j[Bdwl\u00149`\u0011Z{6kX3r?\"3vL_3s_f|f\t\\8bi~{\u0005/T8e+\t\u0019)\t\u0005\u0006\u0003\b\u0005E\u0015qNA9\u0003_\n1%[7qY~{\u0005o\u0018%W?N{V-]0I-~SXM]8z?2{gnZ0Pa6{G-\u0006\u0002\u0004\fBQ!qAAI\u0003{\ny(! \u0002E%l\u0007\u000f\\0Pa~CekX*`KF|\u0006JV0{KJ|\u0017pX%oi~{\u0005\u000fU8x+\t\u0019\t\n\u0005\u0006\u0003&\u0005E\u0015qJA+\u0003\u001f\nQ%[7qY~{\u0005o\u0018%W?N{V-]0I-~SXM]8z?\u0012{WO\u00197f?>\u0003\bk\\<\u0016\u0005\r]\u0005C\u0003B\u0013\u0003#\u000b\t'a\u0019\u0002b\u0005!\u0013.\u001c9m?>\u0003x\f\u0013,`'~+\u0017o\u0018%W?j,'o\\=`\r2|\u0017\r^0PaB{w/\u0006\u0002\u0004\u001eBQ!QEAI\u0003_\n\t(a\u001c\u0002G%l\u0007\u000f\\0Pa~CekX*`KF|\u0006JV0{KJ|\u0017p\u0018'p]\u001e|v\n\u001d)poV\u001111\u0015\t\u000b\u0005K\t\t*! \u0002\u0000\u0005u\u0014\u0001H5na2|v\n]*fi~Ke\u000e\u00157bG\u0016|\u0006JV0I-~Ke\u000e^\u000b\u0003\u0007S\u0003\u0002Ba,\u0004,\u0006=\u0013qJ\u0005\u0005\u0007[\u000b9E\u0001\u0007J]Bc\u0017mY3J[Bd''A\u0010j[Bdwl\u00149TKR|\u0016J\u001c)mC\u000e,w\f\u0013,`\u0011Z{Fi\\;cY\u0016,\"aa-\u0011\u0011\t=61VA1\u0003C\na$[7qY~{\u0005oU3u?&s\u0007\u000b\\1dK~Cek\u0018%W?\u001acw.\u0019;\u0016\u0005\re\u0006\u0003\u0003BX\u0007W\u000by'a\u001c\u0002;%l\u0007\u000f\\0PaN+GoX%o!2\f7-Z0I-~Cek\u0018'p]\u001e,\"aa0\u0011\u0011\t=61VA?\u0003{\n\u0011&[7qY~{\u0005oX%o!2\f7-Z0I-~\u001bv,\u001b3f[B|G/\u001a8u?&sGoX(q\u0003\u0012$WCABc!!\tYia+\u0002P\u0005U\u0013\u0001L5na2|v\n]0J]Bc\u0017mY3`\u0011Z{6kX5eK6\u0004x\u000e^3oi~#u.\u001e2mK~{\u0005/\u00113e+\t\u0019Y\r\u0005\u0005\u0002\f\u000e-\u0016\u0011MA2\u0003-JW\u000e\u001d7`\u001fB|\u0016J\u001c)mC\u000e,w\f\u0013,`'~KG-Z7q_R,g\u000e^0GY>\fGoX(q\u0003\u0012$WCABi!!\tYia+\u0002p\u0005E\u0014AK5na2|v\n]0J]Bc\u0017mY3`\u0011Z{6kX5eK6\u0004x\u000e^3oi~cuN\\4`\u001fB\fE\rZ\u000b\u0003\u0007/\u0004\u0002\"a#\u0004,\u0006u\u0014qP\u0001*S6\u0004HnX(q?&s\u0007\u000b\\1dK~CekX*`S\u0012,W\u000e]8uK:$x,\u00138u?>\u00038+\u001e2\u0016\u0005\ru\u0007\u0003CAW\u0007W\u000by%!\u0016\u0002Y%l\u0007\u000f\\0Pa~Ke\u000e\u00157bG\u0016|\u0006JV0T?&$W-\u001c9pi\u0016tGo\u0018#pk\ndWmX(q'V\u0014WCABr!!\tika+\u0002b\u0005\r\u0014aK5na2|v\n]0J]Bc\u0017mY3`\u0011Z{6kX5eK6\u0004x\u000e^3oi~3En\\1u?>\u00038+\u001e2\u0016\u0005\r%\b\u0003CAW\u0007W\u000by'!\u001d\u0002U%l\u0007\u000f\\0Pa~Ke\u000e\u00157bG\u0016|\u0006JV0T?&$W-\u001c9pi\u0016tGo\u0018'p]\u001e|v\n]*vEV\u00111q\u001e\t\t\u0003[\u001bY+! \u0002\u0000\u0005\t\u0013.\u001c9m?>\u0003X*\u001e7TG\u0006d\u0017M]0J]Bc\u0017mY3`\u0011Z{6kX%oiV\u00111Q\u001f\t\t\u0003\u0017\u001cY+a\u0014\u0002V\u0005!\u0013.\u001c9m?>\u0003X*\u001e7TG\u0006d\u0017M]0J]Bc\u0017mY3`\u0011Z{6k\u0018#pk\ndW-\u0006\u0002\u0004|BA\u00111ZBV\u0003C\n\u0019'A\u0012j[Bdwl\u00149Nk2\u001c6-\u00197be~Ke\u000e\u00157bG\u0016|\u0006JV0T?\u001acw.\u0019;\u0016\u0005\u0011\u0005\u0001\u0003CAf\u0007W\u000by'!\u001d\u0002E%l\u0007\u000f\\0Pa6+HnU2bY\u0006\u0014x,\u00138QY\u0006\u001cWm\u0018%W?N{Fj\u001c8h+\t!9\u0001\u0005\u0005\u0002L\u000e-\u0016QPA@\u0003mIW\u000e\u001d7`\u001fB\u001cV\r^0J]Bc\u0017mY3`\u0011Z{6kX%oiV\u0011AQ\u0002\t\t\u0005_\u001bY+a\u0014\u0002V\u0005q\u0012.\u001c9m?>\u00038+\u001a;`\u0013:\u0004F.Y2f?\"3vlU0E_V\u0014G.Z\u000b\u0003\t'\u0001\u0002Ba,\u0004,\u0006\u0005\u00141M\u0001\u001eS6\u0004HnX(q'\u0016$x,\u00138QY\u0006\u001cWm\u0018%W?N{f\t\\8biV\u0011A\u0011\u0004\t\t\u0005_\u001bY+a\u001c\u0002r\u0005a\u0012.\u001c9m?>\u00038+\u001a;`\u0013:\u0004F.Y2f?\"3vlU0M_:<WC\u0001C\u0010!!\u0011yka+\u0002~\u0005}\u0014\u0001L5na2|v\n]0J]Bc\u0017mY3`\u0011Z{6k\u0018'I'~s\u0017\u000e\u001c9pi\u0016tGoX%oi~{\u0005\u000fR5w+\t!)\u0003\u0005\u0005\u0002j\u000e-\u0016qJA+\u0003=JW\u000e\u001d7`\u001fB|\u0016J\u001c)mC\u000e,w\f\u0013,`'~c\u0005jU0oS2\u0004x\u000e^3oi~#u.\u001e2mK~{\u0005\u000fR5w+\t!Y\u0003\u0005\u0005\u0002j\u000e-\u0016\u0011MA2\u00039JW\u000e\u001d7`\u001fB|\u0016J\u001c)mC\u000e,w\f\u0013,`'~c\u0005jU0oS2\u0004x\u000e^3oi~3En\\1u?>\u0003H)\u001b<\u0016\u0005\u0011E\u0002\u0003CAu\u0007W\u000by'!\u001d\u0002[%l\u0007\u000f\\0Pa~Ke\u000e\u00157bG\u0016|\u0006JV0T?2C5k\u00188jYB|G/\u001a8u?2{gnZ0Pa\u0012Kg/\u0006\u0002\u00058AA\u0011\u0011^BV\u0003{\ny(\u0001\u0017j[Bdwl\u00149`\u0013:\u0004F.Y2f?\"3vlU0M\u0011N{f.\u001b7q_R,g\u000e^0J]R|v\n]'pIV\u0011AQ\b\t\t\u0005\u000f\u0019Y+a\u0014\u0002V\u0005y\u0013.\u001c9m?>\u0003x,\u00138QY\u0006\u001cWm\u0018%W?N{F\nS*`]&d\u0007o\u001c;f]R|Fi\\;cY\u0016|v\n]'pIV\u0011A1\t\t\t\u0005\u000f\u0019Y+!\u0019\u0002d\u0005q\u0013.\u001c9m?>\u0003x,\u00138QY\u0006\u001cWm\u0018%W?N{F\nS*`]&d\u0007o\u001c;f]R|f\t\\8bi~{\u0005/T8e+\t!I\u0005\u0005\u0005\u0003\b\r-\u0016qNA9\u00035JW\u000e\u001d7`\u001fB|\u0016J\u001c)mC\u000e,w\f\u0013,`'~c\u0005jU0oS2\u0004x\u000e^3oi~cuN\\4`\u001fBlu\u000eZ\u000b\u0003\t\u001f\u0002\u0002Ba\u0002\u0004,\u0006u\u0014qP\u0001-S6\u0004HnX(q?&s\u0007\u000b\\1dK~CekX*`\u0019\"\u001bvL\\5ma>$XM\u001c;`\u0013:$xl\u00149Q_^,\"\u0001\"\u0016\u0011\u0011\t\u001521VA(\u0003+\nq&[7qY~{\u0005oX%o!2\f7-Z0I-~\u001bv\f\u0014%T?:LG\u000e]8uK:$x\fR8vE2,wl\u00149Q_^,\"\u0001b\u0017\u0011\u0011\t\u001521VA1\u0003G\na&[7qY~{\u0005oX%o!2\f7-Z0I-~\u001bv\f\u0014%T?:LG\u000e]8uK:$xL\u00127pCR|v\n\u001d)poV\u0011A\u0011\r\t\t\u0005K\u0019Y+a\u001c\u0002r\u0005i\u0013.\u001c9m?>\u0003x,\u00138QY\u0006\u001cWm\u0018%W?N{F\nS*`]&d\u0007o\u001c;f]R|Fj\u001c8h?>\u0003\bk\\<\u0016\u0005\u0011\u001d\u0004\u0003\u0003B\u0013\u0007W\u000bi(a \u0002=%l\u0007\u000f\\0Pa6+H.\u00138oKJ|\u0006JV0I-~+\u0017oX*`\u0013:$XC\u0001C7!)!y'!%\u0002P\u0005=\u0013Q\u000b\b\u0005\tc\"\u0019I\u0004\u0003\u0005t\u0011\u0005e\u0002\u0002C;\t\u007frA\u0001b\u001e\u0005~5\u0011A\u0011\u0010\u0006\u0005\tw\ni!\u0001\u0004=e>|GOP\u0005\u0003\u0003\u0017IA!a\u0002\u0002\n%!\u00111AA\u0003\u0013\u0011!))!\u0001\u0002\u0015=\u0003X*\u001e7J]:,'/A\u0010j[Bdwl\u00149Nk2LeN\\3s?\"3v\f\u0013,`KF|6k\u0018'p]\u001e,\"\u0001b#\u0011\u0015\u0011=\u0014\u0011SA?\u0003{\ny(A\u0011j[Bdwl\u00149Nk2LeN\\3s?\"3v\f\u0013,`KF|6k\u0018#pk\ndW-\u0006\u0002\u0005\u0012BQAqNAI\u0003C\n\t'a\u0019\u0002A%l\u0007\u000f\\0Pa6+H.\u00138oKJ|\u0006JV0I-~+\u0017oX*`\r2|\u0017\r^\u000b\u0003\t/\u0003\"\u0002b\u001c\u0002\u0012\u0006=\u0014qNA9\u0003uIW\u000e\u001d7`\u0007\u0006tGK]1wKJ\u001cXMV1mk\u0016\u001cx\f\u0013,`\u0013:$XC\u0001CO!!!y\n\"*\u0002P\u0005USB\u0001CQ\u0015\u0011!\u0019+!\u0002\u0002\u000fM,\b\u000f]8si&!Aq\u0015CQ\u0005E\u0019\u0015M\u001c+sCZ,'o]3WC2,Xm]\u0001!S6\u0004HnX\"b]R\u0013\u0018M^3sg\u00164\u0016\r\\;fg~Cek\u0018#pk\ndW-\u0006\u0002\u0005.BAAq\u0014CS\u0003C\n\u0019'A\u0010j[BdwlQ1o)J\fg/\u001a:tKZ\u000bG.^3t?\"3vL\u00127pCR,\"\u0001b-\u0011\u0011\u0011}EQUA8\u0003c\na$[7qY~\u001b\u0015M\u001c+sCZ,'o]3WC2,Xm]0I-~cuN\\4\u0016\u0005\u0011e\u0006\u0003\u0003CP\tK\u000bi(a \u0002C%l\u0007\u000f\\0DC:$&/\u0019<feN,g+\u00197vKN|\u0006JV0HK:,'/[2\u0016\t\u0011}F\u0011Z\u000b\u0003\t\u0003\u0004\u0002\u0002b(\u0005&\u0012\rGQ\u0019\t\u0007\u0003{\t\t\u0006\"2\u0011\t\u0011\u001dG\u0011\u001a\u0007\u0001\t\u001d!YM b\u0001\t\u001b\u0014\u0011\u0001V\t\u0005\t\u001f$)\u000e\u0005\u0003\u0002\u0014\u0011E\u0017\u0002\u0002Cj\u0003+\u0011qAT8uQ&tw\r\u0005\u0003\u0002\u0014\u0011]\u0017\u0002\u0002Cm\u0003+\u00111!\u00118z\u0001"
)
public interface HashVectorExpandOps extends VectorOps, HashVector_GenericOps {
   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_scaleAdd_InPlace_HV_S_HV_Int_$eq(final UFunc.InPlaceImpl3 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_scaleAdd_InPlace_HV_S_HV_Double_$eq(final UFunc.InPlaceImpl3 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_scaleAdd_InPlace_HV_S_HV_Float_$eq(final UFunc.InPlaceImpl3 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_scaleAdd_InPlace_HV_S_HV_Long_$eq(final UFunc.InPlaceImpl3 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_Int_OpAdd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_Double_OpAdd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_Float_OpAdd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_Long_OpAdd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_Int_OpSub_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_Double_OpSub_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_Float_OpSub_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_Long_OpSub_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpMulScalar_HV_HV_eq_HV_Int_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpMulScalar_HV_HV_eq_HV_Double_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpMulScalar_HV_HV_eq_HV_Float_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpMulScalar_HV_HV_eq_HV_Long_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_lhs_nilpotent_Int_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_lhs_nilpotent_Double_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_lhs_nilpotent_Float_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_lhs_nilpotent_Long_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_lhs_nilpotent_Int_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_lhs_nilpotent_Double_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_lhs_nilpotent_Float_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_lhs_nilpotent_Long_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_lhs_nilpotent_Int_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_lhs_nilpotent_Double_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_lhs_nilpotent_Float_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_lhs_nilpotent_Long_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Int_OpAdd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Double_OpAdd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Float_OpAdd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Long_OpAdd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Int_OpSub_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Double_OpSub_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Float_OpSub_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Long_OpSub_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Int_OpMulScalar_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Double_OpMulScalar_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Float_OpMulScalar_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Long_OpMulScalar_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Int_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Double_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Float_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Long_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Int_OpSet_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Double_OpSet_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Float_OpSet_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Long_OpSet_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Int_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Double_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Float_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Long_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Int_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Double_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Float_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Long_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_add_Int_OpAdd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_add_Double_OpAdd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_add_Float_OpAdd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_add_Long_OpAdd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_add_Int_OpSub_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_add_Double_OpSub_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_add_Float_OpSub_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_add_Long_OpSub_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Int_OpMulScalar_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Double_OpMulScalar_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Float_OpMulScalar_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Long_OpMulScalar_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Int_OpMulMatrix_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Double_OpMulMatrix_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Float_OpMulMatrix_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Long_OpMulMatrix_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Int_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Double_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Float_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Long_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Int_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Double_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Float_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Long_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Int_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Double_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Float_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Long_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpSet_InPlace_HV_HV_Int_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpSet_InPlace_HV_HV_Double_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpSet_InPlace_HV_HV_Float_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpSet_InPlace_HV_HV_Long_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_idempotent_Int_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_idempotent_Double_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_idempotent_Float_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_idempotent_Long_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_idempotent_Int_OpSub_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_idempotent_Double_OpSub_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_idempotent_Float_OpSub_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_idempotent_Long_OpSub_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpMulScalar_InPlace_HV_S_Int_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpMulScalar_InPlace_HV_S_Double_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpMulScalar_InPlace_HV_S_Float_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpMulScalar_InPlace_HV_S_Long_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpSet_InPlace_HV_S_Int_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpSet_InPlace_HV_S_Double_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpSet_InPlace_HV_S_Float_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpSet_InPlace_HV_S_Long_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_LHS_nilpotent_Int_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_LHS_nilpotent_Double_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_LHS_nilpotent_Float_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_LHS_nilpotent_Long_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_LHS_nilpotent_Int_OpMod_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_LHS_nilpotent_Double_OpMod_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_LHS_nilpotent_Float_OpMod_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_LHS_nilpotent_Long_OpMod_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_LHS_nilpotent_Int_OpPow_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_LHS_nilpotent_Double_OpPow_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_LHS_nilpotent_Float_OpPow_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_LHS_nilpotent_Long_OpPow_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpMulInner_HV_HV_eq_S_Int_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpMulInner_HV_HV_eq_S_Long_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpMulInner_HV_HV_eq_S_Double_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpMulInner_HV_HV_eq_S_Float_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_CanTraverseValues_HV_Int_$eq(final CanTraverseValues x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_CanTraverseValues_HV_Double_$eq(final CanTraverseValues x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_CanTraverseValues_HV_Float_$eq(final CanTraverseValues x$1);

   void breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_CanTraverseValues_HV_Long_$eq(final CanTraverseValues x$1);

   UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_HV_S_HV_Int();

   UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_HV_S_HV_Double();

   UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_HV_S_HV_Float();

   UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_HV_S_HV_Long();

   UFunc.UImpl2 impl_Op_HV_HV_eq_HV_Int_OpAdd();

   UFunc.UImpl2 impl_Op_HV_HV_eq_HV_Double_OpAdd();

   UFunc.UImpl2 impl_Op_HV_HV_eq_HV_Float_OpAdd();

   UFunc.UImpl2 impl_Op_HV_HV_eq_HV_Long_OpAdd();

   UFunc.UImpl2 impl_Op_HV_HV_eq_HV_Int_OpSub();

   UFunc.UImpl2 impl_Op_HV_HV_eq_HV_Double_OpSub();

   UFunc.UImpl2 impl_Op_HV_HV_eq_HV_Float_OpSub();

   UFunc.UImpl2 impl_Op_HV_HV_eq_HV_Long_OpSub();

   UFunc.UImpl2 impl_OpMulScalar_HV_HV_eq_HV_Int();

   UFunc.UImpl2 impl_OpMulScalar_HV_HV_eq_HV_Double();

   UFunc.UImpl2 impl_OpMulScalar_HV_HV_eq_HV_Float();

   UFunc.UImpl2 impl_OpMulScalar_HV_HV_eq_HV_Long();

   UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Int_OpDiv();

   UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Double_OpDiv();

   UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Float_OpDiv();

   UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Long_OpDiv();

   UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Int_OpMod();

   UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Double_OpMod();

   UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Float_OpMod();

   UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Long_OpMod();

   UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Int_OpPow();

   UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Double_OpPow();

   UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Float_OpPow();

   UFunc.UImpl2 impl_Op_HV_HV_eq_HV_lhs_nilpotent_Long_OpPow();

   UFunc.UImpl2 impl_Op_HV_V_eq_HV_Int_OpAdd();

   UFunc.UImpl2 impl_Op_HV_V_eq_HV_Double_OpAdd();

   UFunc.UImpl2 impl_Op_HV_V_eq_HV_Float_OpAdd();

   UFunc.UImpl2 impl_Op_HV_V_eq_HV_Long_OpAdd();

   UFunc.UImpl2 impl_Op_HV_V_eq_HV_Int_OpSub();

   UFunc.UImpl2 impl_Op_HV_V_eq_HV_Double_OpSub();

   UFunc.UImpl2 impl_Op_HV_V_eq_HV_Float_OpSub();

   UFunc.UImpl2 impl_Op_HV_V_eq_HV_Long_OpSub();

   UFunc.UImpl2 impl_Op_HV_V_eq_HV_Int_OpMulScalar();

   UFunc.UImpl2 impl_Op_HV_V_eq_HV_Double_OpMulScalar();

   UFunc.UImpl2 impl_Op_HV_V_eq_HV_Float_OpMulScalar();

   UFunc.UImpl2 impl_Op_HV_V_eq_HV_Long_OpMulScalar();

   UFunc.UImpl2 impl_Op_HV_V_eq_HV_Int_OpDiv();

   UFunc.UImpl2 impl_Op_HV_V_eq_HV_Double_OpDiv();

   UFunc.UImpl2 impl_Op_HV_V_eq_HV_Float_OpDiv();

   UFunc.UImpl2 impl_Op_HV_V_eq_HV_Long_OpDiv();

   UFunc.UImpl2 impl_Op_HV_V_eq_HV_Int_OpSet();

   UFunc.UImpl2 impl_Op_HV_V_eq_HV_Double_OpSet();

   UFunc.UImpl2 impl_Op_HV_V_eq_HV_Float_OpSet();

   UFunc.UImpl2 impl_Op_HV_V_eq_HV_Long_OpSet();

   UFunc.UImpl2 impl_Op_HV_V_eq_HV_Int_OpMod();

   UFunc.UImpl2 impl_Op_HV_V_eq_HV_Double_OpMod();

   UFunc.UImpl2 impl_Op_HV_V_eq_HV_Float_OpMod();

   UFunc.UImpl2 impl_Op_HV_V_eq_HV_Long_OpMod();

   UFunc.UImpl2 impl_Op_HV_V_eq_HV_Int_OpPow();

   UFunc.UImpl2 impl_Op_HV_V_eq_HV_Double_OpPow();

   UFunc.UImpl2 impl_Op_HV_V_eq_HV_Float_OpPow();

   UFunc.UImpl2 impl_Op_HV_V_eq_HV_Long_OpPow();

   UFunc.UImpl2 impl_Op_HV_S_eq_HV_add_Int_OpAdd();

   UFunc.UImpl2 impl_Op_HV_S_eq_HV_add_Double_OpAdd();

   UFunc.UImpl2 impl_Op_HV_S_eq_HV_add_Float_OpAdd();

   UFunc.UImpl2 impl_Op_HV_S_eq_HV_add_Long_OpAdd();

   UFunc.UImpl2 impl_Op_HV_S_eq_HV_add_Int_OpSub();

   UFunc.UImpl2 impl_Op_HV_S_eq_HV_add_Double_OpSub();

   UFunc.UImpl2 impl_Op_HV_S_eq_HV_add_Float_OpSub();

   UFunc.UImpl2 impl_Op_HV_S_eq_HV_add_Long_OpSub();

   UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Int_OpMulScalar();

   UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Double_OpMulScalar();

   UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Float_OpMulScalar();

   UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Long_OpMulScalar();

   UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Int_OpMulMatrix();

   UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Double_OpMulMatrix();

   UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Float_OpMulMatrix();

   UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Long_OpMulMatrix();

   UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Int_OpDiv();

   UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Double_OpDiv();

   UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Float_OpDiv();

   UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Long_OpDiv();

   UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Int_OpMod();

   UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Double_OpMod();

   UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Float_OpMod();

   UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Long_OpMod();

   UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Int_OpPow();

   UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Double_OpPow();

   UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Float_OpPow();

   UFunc.UImpl2 impl_Op_HV_S_eq_HV_zeroy_Long_OpPow();

   UFunc.InPlaceImpl2 impl_OpSet_InPlace_HV_HV_Int();

   UFunc.InPlaceImpl2 impl_OpSet_InPlace_HV_HV_Double();

   UFunc.InPlaceImpl2 impl_OpSet_InPlace_HV_HV_Float();

   UFunc.InPlaceImpl2 impl_OpSet_InPlace_HV_HV_Long();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_idempotent_Int_OpAdd();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_idempotent_Double_OpAdd();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_idempotent_Float_OpAdd();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_idempotent_Long_OpAdd();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_idempotent_Int_OpSub();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_idempotent_Double_OpSub();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_idempotent_Float_OpSub();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_idempotent_Long_OpSub();

   UFunc.InPlaceImpl2 impl_OpMulScalar_InPlace_HV_S_Int();

   UFunc.InPlaceImpl2 impl_OpMulScalar_InPlace_HV_S_Double();

   UFunc.InPlaceImpl2 impl_OpMulScalar_InPlace_HV_S_Float();

   UFunc.InPlaceImpl2 impl_OpMulScalar_InPlace_HV_S_Long();

   UFunc.InPlaceImpl2 impl_OpSet_InPlace_HV_S_Int();

   UFunc.InPlaceImpl2 impl_OpSet_InPlace_HV_S_Double();

   UFunc.InPlaceImpl2 impl_OpSet_InPlace_HV_S_Float();

   UFunc.InPlaceImpl2 impl_OpSet_InPlace_HV_S_Long();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Int_OpDiv();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Double_OpDiv();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Float_OpDiv();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Long_OpDiv();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Int_OpMod();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Double_OpMod();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Float_OpMod();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Long_OpMod();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Int_OpPow();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Double_OpPow();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Float_OpPow();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_S_LHS_nilpotent_Long_OpPow();

   UFunc.UImpl2 impl_OpMulInner_HV_HV_eq_S_Int();

   UFunc.UImpl2 impl_OpMulInner_HV_HV_eq_S_Long();

   UFunc.UImpl2 impl_OpMulInner_HV_HV_eq_S_Double();

   UFunc.UImpl2 impl_OpMulInner_HV_HV_eq_S_Float();

   CanTraverseValues impl_CanTraverseValues_HV_Int();

   CanTraverseValues impl_CanTraverseValues_HV_Double();

   CanTraverseValues impl_CanTraverseValues_HV_Float();

   CanTraverseValues impl_CanTraverseValues_HV_Long();

   // $FF: synthetic method
   static CanTraverseValues impl_CanTraverseValues_HV_Generic$(final HashVectorExpandOps $this) {
      return $this.impl_CanTraverseValues_HV_Generic();
   }

   default CanTraverseValues impl_CanTraverseValues_HV_Generic() {
      return new CanTraverseValues() {
         public Object foldLeft(final Object from, final Object b, final Function2 fn) {
            return CanTraverseValues.foldLeft$(this, from, b, fn);
         }

         public CanTraverseValues.ValuesVisitor traverse(final HashVector from, final CanTraverseValues.ValuesVisitor fn) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = from.iterableSize(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               if (from.isActive(index$macro$2)) {
                  fn.visit(.MODULE$.array_apply(from.data(), index$macro$2));
               }
            }

            fn.zeros(from.size() - from.activeSize(), from.default());
            return fn;
         }

         public boolean isTraversableAgain(final HashVector from) {
            return true;
         }

         public {
            CanTraverseValues.$init$(this);
         }
      };
   }

   static void $init$(final HashVectorExpandOps $this) {
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_scaleAdd_InPlace_HV_S_HV_Int_$eq(new UFunc.InPlaceImpl3() {
         public void apply(final HashVector dest, final int scalar, final HashVector source) {
            int left$macro$1 = dest.length();
            int right$macro$2 = source.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(90)).append("requirement failed: Vectors must have the same length: ").append("dest.length == source.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int bsize = source.iterableSize();
               if (scalar != 0) {
                  int[] bd = source.data$mcI$sp();
                  int[] bi = source.index();
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = bsize; index$macro$4 < limit$macro$6; ++index$macro$4) {
                     if (source.isActive(index$macro$4)) {
                        int var11 = bi[index$macro$4];
                        dest.update$mcI$sp(var11, dest.apply$mcI$sp(var11) + scalar * bd[index$macro$4]);
                     }
                  }
               }

            }
         }

         public {
            ((TernaryUpdateRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_scaleAdd_InPlace_V_S_V_Int())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(HashVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_scaleAdd_InPlace_HV_S_HV_Double_$eq(new UFunc.InPlaceImpl3() {
         public void apply(final HashVector dest, final double scalar, final HashVector source) {
            int left$macro$1 = dest.length();
            int right$macro$2 = source.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(90)).append("requirement failed: Vectors must have the same length: ").append("dest.length == source.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int bsize = source.iterableSize();
               if (scalar != (double)0) {
                  double[] bd = source.data$mcD$sp();
                  int[] bi = source.index();
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = bsize; index$macro$4 < limit$macro$6; ++index$macro$4) {
                     if (source.isActive(index$macro$4)) {
                        int var12 = bi[index$macro$4];
                        dest.update$mcD$sp(var12, dest.apply$mcD$sp(var12) + scalar * bd[index$macro$4]);
                     }
                  }
               }

            }
         }

         public {
            ((TernaryUpdateRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_scaleAdd_InPlace_V_S_V_Double())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Double(), scala.reflect.ClassTag..MODULE$.apply(HashVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_scaleAdd_InPlace_HV_S_HV_Float_$eq(new UFunc.InPlaceImpl3() {
         public void apply(final HashVector dest, final float scalar, final HashVector source) {
            int left$macro$1 = dest.length();
            int right$macro$2 = source.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(90)).append("requirement failed: Vectors must have the same length: ").append("dest.length == source.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int bsize = source.iterableSize();
               if (scalar != (float)0) {
                  float[] bd = source.data$mcF$sp();
                  int[] bi = source.index();
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = bsize; index$macro$4 < limit$macro$6; ++index$macro$4) {
                     if (source.isActive(index$macro$4)) {
                        int var11 = bi[index$macro$4];
                        dest.update$mcF$sp(var11, dest.apply$mcF$sp(var11) + scalar * bd[index$macro$4]);
                     }
                  }
               }

            }
         }

         public {
            ((TernaryUpdateRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_scaleAdd_InPlace_V_S_V_Float())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Float(), scala.reflect.ClassTag..MODULE$.apply(HashVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_scaleAdd_InPlace_HV_S_HV_Long_$eq(new UFunc.InPlaceImpl3() {
         public void apply(final HashVector dest, final long scalar, final HashVector source) {
            int left$macro$1 = dest.length();
            int right$macro$2 = source.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(90)).append("requirement failed: Vectors must have the same length: ").append("dest.length == source.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int bsize = source.iterableSize();
               if (scalar != 0L) {
                  long[] bd = source.data$mcJ$sp();
                  int[] bi = source.index();
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = bsize; index$macro$4 < limit$macro$6; ++index$macro$4) {
                     if (source.isActive(index$macro$4)) {
                        int var12 = bi[index$macro$4];
                        dest.update$mcJ$sp(var12, dest.apply$mcJ$sp(var12) + scalar * bd[index$macro$4]);
                     }
                  }
               }

            }
         }

         public {
            ((TernaryUpdateRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_scaleAdd_InPlace_V_S_V_Long())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Long(), scala.reflect.ClassTag..MODULE$.apply(HashVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_Int_OpAdd_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               label30: {
                  OpAdd$ var5;
                  OpAdd$ var10000 = var5 = OpAdd$.MODULE$;
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label30;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label30;
                  }

                  if (a.activeSize() < b.activeSize()) {
                     return this.apply(b, a);
                  }
               }

               HashVector result = a.copy$mcI$sp();
               int index$macro$4 = 0;

               for(int limit$macro$6 = b.iterableSize(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  if (b.isActive(index$macro$4)) {
                     int k = b.index()[index$macro$4];
                     int v = b.data$mcI$sp()[index$macro$4];
                     result.update$mcI$sp(k, a.apply$mcI$sp(k) + v);
                  }
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_idempotent_Int_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(HashVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_Double_OpAdd_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               label30: {
                  OpAdd$ var5;
                  OpAdd$ var10000 = var5 = OpAdd$.MODULE$;
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label30;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label30;
                  }

                  if (a.activeSize() < b.activeSize()) {
                     return this.apply(b, a);
                  }
               }

               HashVector result = a.copy$mcD$sp();
               int index$macro$4 = 0;

               for(int limit$macro$6 = b.iterableSize(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  if (b.isActive(index$macro$4)) {
                     int k = b.index()[index$macro$4];
                     double v = b.data$mcD$sp()[index$macro$4];
                     result.update$mcD$sp(k, a.apply$mcD$sp(k) + v);
                  }
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_idempotent_Double_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(HashVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_Float_OpAdd_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               label30: {
                  OpAdd$ var5;
                  OpAdd$ var10000 = var5 = OpAdd$.MODULE$;
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label30;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label30;
                  }

                  if (a.activeSize() < b.activeSize()) {
                     return this.apply(b, a);
                  }
               }

               HashVector result = a.copy$mcF$sp();
               int index$macro$4 = 0;

               for(int limit$macro$6 = b.iterableSize(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  if (b.isActive(index$macro$4)) {
                     int k = b.index()[index$macro$4];
                     float v = b.data$mcF$sp()[index$macro$4];
                     result.update$mcF$sp(k, a.apply$mcF$sp(k) + v);
                  }
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_idempotent_Float_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(HashVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_Long_OpAdd_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               label30: {
                  OpAdd$ var5;
                  OpAdd$ var10000 = var5 = OpAdd$.MODULE$;
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label30;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label30;
                  }

                  if (a.activeSize() < b.activeSize()) {
                     return this.apply(b, a);
                  }
               }

               HashVector result = a.copy$mcJ$sp();
               int index$macro$4 = 0;

               for(int limit$macro$6 = b.iterableSize(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  if (b.isActive(index$macro$4)) {
                     int k = b.index()[index$macro$4];
                     long v = b.data$mcJ$sp()[index$macro$4];
                     result.update$mcJ$sp(k, a.apply$mcJ$sp(k) + v);
                  }
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_idempotent_Long_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(HashVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_Int_OpSub_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               label30: {
                  OpSub$ var10000 = OpSub$.MODULE$;
                  OpAdd$ var5 = OpAdd$.MODULE$;
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label30;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label30;
                  }

                  if (a.activeSize() < b.activeSize()) {
                     return this.apply(b, a);
                  }
               }

               HashVector result = a.copy$mcI$sp();
               int index$macro$4 = 0;

               for(int limit$macro$6 = b.iterableSize(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  if (b.isActive(index$macro$4)) {
                     int k = b.index()[index$macro$4];
                     int v = b.data$mcI$sp()[index$macro$4];
                     result.update$mcI$sp(k, a.apply$mcI$sp(k) - v);
                  }
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_idempotent_Int_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(HashVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_Double_OpSub_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               label30: {
                  OpSub$ var10000 = OpSub$.MODULE$;
                  OpAdd$ var5 = OpAdd$.MODULE$;
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label30;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label30;
                  }

                  if (a.activeSize() < b.activeSize()) {
                     return this.apply(b, a);
                  }
               }

               HashVector result = a.copy$mcD$sp();
               int index$macro$4 = 0;

               for(int limit$macro$6 = b.iterableSize(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  if (b.isActive(index$macro$4)) {
                     int k = b.index()[index$macro$4];
                     double v = b.data$mcD$sp()[index$macro$4];
                     result.update$mcD$sp(k, a.apply$mcD$sp(k) - v);
                  }
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_idempotent_Double_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(HashVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_Float_OpSub_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               label30: {
                  OpSub$ var10000 = OpSub$.MODULE$;
                  OpAdd$ var5 = OpAdd$.MODULE$;
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label30;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label30;
                  }

                  if (a.activeSize() < b.activeSize()) {
                     return this.apply(b, a);
                  }
               }

               HashVector result = a.copy$mcF$sp();
               int index$macro$4 = 0;

               for(int limit$macro$6 = b.iterableSize(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  if (b.isActive(index$macro$4)) {
                     int k = b.index()[index$macro$4];
                     float v = b.data$mcF$sp()[index$macro$4];
                     result.update$mcF$sp(k, a.apply$mcF$sp(k) - v);
                  }
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_idempotent_Float_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(HashVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_Long_OpSub_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               label30: {
                  OpSub$ var10000 = OpSub$.MODULE$;
                  OpAdd$ var5 = OpAdd$.MODULE$;
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label30;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label30;
                  }

                  if (a.activeSize() < b.activeSize()) {
                     return this.apply(b, a);
                  }
               }

               HashVector result = a.copy$mcJ$sp();
               int index$macro$4 = 0;

               for(int limit$macro$6 = b.iterableSize(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  if (b.isActive(index$macro$4)) {
                     int k = b.index()[index$macro$4];
                     long v = b.data$mcJ$sp()[index$macro$4];
                     result.update$mcJ$sp(k, a.apply$mcJ$sp(k) - v);
                  }
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_idempotent_Long_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(HashVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpMulScalar_HV_HV_eq_HV_Int_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else if (a.activeSize() < b.activeSize()) {
               return this.apply(b, a);
            } else {
               VectorBuilder builder = new VectorBuilder$mcI$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringInt(), scala.reflect.ClassTag..MODULE$.Int());
               b.activeIterator().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$apply$1(check$ifrefutable$1))).foreach((x$1) -> {
                  $anonfun$apply$2(a, builder, x$1);
                  return BoxedUnit.UNIT;
               });
               return builder.toHashVector$mcI$sp();
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$1(final Tuple2 check$ifrefutable$1) {
            boolean var1;
            if (check$ifrefutable$1 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$2(final HashVector a$1, final VectorBuilder builder$1, final Tuple2 x$1) {
            if (x$1 != null) {
               int k = x$1._1$mcI$sp();
               int v = x$1._2$mcI$sp();
               int r = a$1.apply$mcI$sp(k) * v;
               if (r != 0) {
                  builder$1.add$mcI$sp(k, r);
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } else {
                  BoxedUnit var8 = BoxedUnit.UNIT;
               }

            } else {
               throw new MatchError(x$1);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_nilpotent_Int())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(HashVector.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpMulScalar_HV_HV_eq_HV_Double_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else if (a.activeSize() < b.activeSize()) {
               return this.apply(b, a);
            } else {
               VectorBuilder builder = new VectorBuilder$mcD$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringD(), scala.reflect.ClassTag..MODULE$.Double());
               b.activeIterator().withFilter((check$ifrefutable$2) -> BoxesRunTime.boxToBoolean($anonfun$apply$3(check$ifrefutable$2))).foreach((x$2) -> {
                  $anonfun$apply$4(a, builder, x$2);
                  return BoxedUnit.UNIT;
               });
               return builder.toHashVector$mcD$sp();
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$3(final Tuple2 check$ifrefutable$2) {
            boolean var1;
            if (check$ifrefutable$2 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$4(final HashVector a$2, final VectorBuilder builder$2, final Tuple2 x$2) {
            if (x$2 != null) {
               int k = x$2._1$mcI$sp();
               double v = x$2._2$mcD$sp();
               double r = a$2.apply$mcD$sp(k) * v;
               if (r != (double)0.0F) {
                  builder$2.add$mcD$sp(k, r);
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } else {
                  BoxedUnit var10 = BoxedUnit.UNIT;
               }

            } else {
               throw new MatchError(x$2);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_nilpotent_Double())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(HashVector.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpMulScalar_HV_HV_eq_HV_Float_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else if (a.activeSize() < b.activeSize()) {
               return this.apply(b, a);
            } else {
               VectorBuilder builder = new VectorBuilder$mcF$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringFloat(), scala.reflect.ClassTag..MODULE$.Float());
               b.activeIterator().withFilter((check$ifrefutable$3) -> BoxesRunTime.boxToBoolean($anonfun$apply$5(check$ifrefutable$3))).foreach((x$3) -> {
                  $anonfun$apply$6(a, builder, x$3);
                  return BoxedUnit.UNIT;
               });
               return builder.toHashVector$mcF$sp();
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$5(final Tuple2 check$ifrefutable$3) {
            boolean var1;
            if (check$ifrefutable$3 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$6(final HashVector a$3, final VectorBuilder builder$3, final Tuple2 x$3) {
            if (x$3 != null) {
               int k = x$3._1$mcI$sp();
               float v = BoxesRunTime.unboxToFloat(x$3._2());
               float r = a$3.apply$mcF$sp(k) * v;
               if (r != 0.0F) {
                  builder$3.add$mcF$sp(k, r);
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } else {
                  BoxedUnit var8 = BoxedUnit.UNIT;
               }

            } else {
               throw new MatchError(x$3);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_nilpotent_Float())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(HashVector.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpMulScalar_HV_HV_eq_HV_Long_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else if (a.activeSize() < b.activeSize()) {
               return this.apply(b, a);
            } else {
               VectorBuilder builder = new VectorBuilder$mcJ$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringLong(), scala.reflect.ClassTag..MODULE$.Long());
               b.activeIterator().withFilter((check$ifrefutable$4) -> BoxesRunTime.boxToBoolean($anonfun$apply$7(check$ifrefutable$4))).foreach((x$4) -> {
                  $anonfun$apply$8(a, builder, x$4);
                  return BoxedUnit.UNIT;
               });
               return builder.toHashVector$mcJ$sp();
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$7(final Tuple2 check$ifrefutable$4) {
            boolean var1;
            if (check$ifrefutable$4 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$8(final HashVector a$4, final VectorBuilder builder$4, final Tuple2 x$4) {
            if (x$4 != null) {
               int k = x$4._1$mcI$sp();
               long v = x$4._2$mcJ$sp();
               long r = a$4.apply$mcJ$sp(k) * v;
               if (r != 0L) {
                  builder$4.add$mcJ$sp(k, r);
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } else {
                  BoxedUnit var10 = BoxedUnit.UNIT;
               }

            } else {
               throw new MatchError(x$4);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_nilpotent_Long())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(HashVector.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_lhs_nilpotent_Int_OpDiv_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               HashVector result = new HashVector$mcI$sp(new OpenAddressHashArray$mcI$sp(a.length(), ConfigurableDefault$.MODULE$.fromV(BoxesRunTime.boxToInteger(0)), a.array$mcI$sp().iterableSize(), scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero()));
               if (b.activeSize() != b.size()) {
                  a.iterator().withFilter((check$ifrefutable$5) -> BoxesRunTime.boxToBoolean($anonfun$apply$9(check$ifrefutable$5))).foreach((x$5) -> {
                     $anonfun$apply$10(result, b, x$5);
                     return BoxedUnit.UNIT;
                  });
               } else {
                  a.activeIterator().withFilter((check$ifrefutable$6) -> BoxesRunTime.boxToBoolean($anonfun$apply$11(check$ifrefutable$6))).foreach((x$6) -> {
                     $anonfun$apply$12(result, b, x$6);
                     return BoxedUnit.UNIT;
                  });
               }

               return result;
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$9(final Tuple2 check$ifrefutable$5) {
            boolean var1;
            if (check$ifrefutable$5 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$10(final HashVector result$1, final HashVector b$1, final Tuple2 x$5) {
            if (x$5 != null) {
               int k = x$5._1$mcI$sp();
               int v = x$5._2$mcI$sp();
               result$1.update$mcI$sp(k, v / b$1.apply$mcI$sp(k));
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$5);
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$11(final Tuple2 check$ifrefutable$6) {
            boolean var1;
            if (check$ifrefutable$6 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$12(final HashVector result$1, final HashVector b$1, final Tuple2 x$6) {
            if (x$6 != null) {
               int k = x$6._1$mcI$sp();
               int v = x$6._2$mcI$sp();
               result$1.update$mcI$sp(k, v / b$1.apply$mcI$sp(k));
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$6);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_Int_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(HashVector.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_lhs_nilpotent_Double_OpDiv_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               HashVector result = new HashVector$mcD$sp(new OpenAddressHashArray$mcD$sp(a.length(), ConfigurableDefault$.MODULE$.fromV(BoxesRunTime.boxToDouble((double)0.0F)), a.array$mcD$sp().iterableSize(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero()));
               if (b.activeSize() != b.size()) {
                  a.iterator().withFilter((check$ifrefutable$7) -> BoxesRunTime.boxToBoolean($anonfun$apply$13(check$ifrefutable$7))).foreach((x$7) -> {
                     $anonfun$apply$14(result, b, x$7);
                     return BoxedUnit.UNIT;
                  });
               } else {
                  a.activeIterator().withFilter((check$ifrefutable$8) -> BoxesRunTime.boxToBoolean($anonfun$apply$15(check$ifrefutable$8))).foreach((x$8) -> {
                     $anonfun$apply$16(result, b, x$8);
                     return BoxedUnit.UNIT;
                  });
               }

               return result;
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$13(final Tuple2 check$ifrefutable$7) {
            boolean var1;
            if (check$ifrefutable$7 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$14(final HashVector result$2, final HashVector b$2, final Tuple2 x$7) {
            if (x$7 != null) {
               int k = x$7._1$mcI$sp();
               double v = x$7._2$mcD$sp();
               result$2.update$mcD$sp(k, v / b$2.apply$mcD$sp(k));
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$7);
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$15(final Tuple2 check$ifrefutable$8) {
            boolean var1;
            if (check$ifrefutable$8 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$16(final HashVector result$2, final HashVector b$2, final Tuple2 x$8) {
            if (x$8 != null) {
               int k = x$8._1$mcI$sp();
               double v = x$8._2$mcD$sp();
               result$2.update$mcD$sp(k, v / b$2.apply$mcD$sp(k));
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$8);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_Double_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(HashVector.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_lhs_nilpotent_Float_OpDiv_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               HashVector result = new HashVector$mcF$sp(new OpenAddressHashArray$mcF$sp(a.length(), ConfigurableDefault$.MODULE$.fromV(BoxesRunTime.boxToFloat(0.0F)), a.array$mcF$sp().iterableSize(), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero()));
               if (b.activeSize() != b.size()) {
                  a.iterator().withFilter((check$ifrefutable$9) -> BoxesRunTime.boxToBoolean($anonfun$apply$17(check$ifrefutable$9))).foreach((x$9) -> {
                     $anonfun$apply$18(result, b, x$9);
                     return BoxedUnit.UNIT;
                  });
               } else {
                  a.activeIterator().withFilter((check$ifrefutable$10) -> BoxesRunTime.boxToBoolean($anonfun$apply$19(check$ifrefutable$10))).foreach((x$10) -> {
                     $anonfun$apply$20(result, b, x$10);
                     return BoxedUnit.UNIT;
                  });
               }

               return result;
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$17(final Tuple2 check$ifrefutable$9) {
            boolean var1;
            if (check$ifrefutable$9 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$18(final HashVector result$3, final HashVector b$3, final Tuple2 x$9) {
            if (x$9 != null) {
               int k = x$9._1$mcI$sp();
               float v = BoxesRunTime.unboxToFloat(x$9._2());
               result$3.update$mcF$sp(k, v / b$3.apply$mcF$sp(k));
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$9);
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$19(final Tuple2 check$ifrefutable$10) {
            boolean var1;
            if (check$ifrefutable$10 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$20(final HashVector result$3, final HashVector b$3, final Tuple2 x$10) {
            if (x$10 != null) {
               int k = x$10._1$mcI$sp();
               float v = BoxesRunTime.unboxToFloat(x$10._2());
               result$3.update$mcF$sp(k, v / b$3.apply$mcF$sp(k));
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$10);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_Float_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(HashVector.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_lhs_nilpotent_Long_OpDiv_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               HashVector result = new HashVector$mcJ$sp(new OpenAddressHashArray$mcJ$sp(a.length(), ConfigurableDefault$.MODULE$.fromV(BoxesRunTime.boxToLong(0L)), a.array$mcJ$sp().iterableSize(), scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero()));
               if (b.activeSize() != b.size()) {
                  a.iterator().withFilter((check$ifrefutable$11) -> BoxesRunTime.boxToBoolean($anonfun$apply$21(check$ifrefutable$11))).foreach((x$11) -> {
                     $anonfun$apply$22(result, b, x$11);
                     return BoxedUnit.UNIT;
                  });
               } else {
                  a.activeIterator().withFilter((check$ifrefutable$12) -> BoxesRunTime.boxToBoolean($anonfun$apply$23(check$ifrefutable$12))).foreach((x$12) -> {
                     $anonfun$apply$24(result, b, x$12);
                     return BoxedUnit.UNIT;
                  });
               }

               return result;
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$21(final Tuple2 check$ifrefutable$11) {
            boolean var1;
            if (check$ifrefutable$11 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$22(final HashVector result$4, final HashVector b$4, final Tuple2 x$11) {
            if (x$11 != null) {
               int k = x$11._1$mcI$sp();
               long v = x$11._2$mcJ$sp();
               result$4.update$mcJ$sp(k, v / b$4.apply$mcJ$sp(k));
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$11);
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$23(final Tuple2 check$ifrefutable$12) {
            boolean var1;
            if (check$ifrefutable$12 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$24(final HashVector result$4, final HashVector b$4, final Tuple2 x$12) {
            if (x$12 != null) {
               int k = x$12._1$mcI$sp();
               long v = x$12._2$mcJ$sp();
               result$4.update$mcJ$sp(k, v / b$4.apply$mcJ$sp(k));
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$12);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_Long_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(HashVector.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_lhs_nilpotent_Int_OpMod_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               HashVector result = new HashVector$mcI$sp(new OpenAddressHashArray$mcI$sp(a.length(), ConfigurableDefault$.MODULE$.fromV(BoxesRunTime.boxToInteger(0)), a.array$mcI$sp().iterableSize(), scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero()));
               if (b.activeSize() != b.size()) {
                  a.iterator().withFilter((check$ifrefutable$13) -> BoxesRunTime.boxToBoolean($anonfun$apply$25(check$ifrefutable$13))).foreach((x$13) -> {
                     $anonfun$apply$26(result, b, x$13);
                     return BoxedUnit.UNIT;
                  });
               } else {
                  a.activeIterator().withFilter((check$ifrefutable$14) -> BoxesRunTime.boxToBoolean($anonfun$apply$27(check$ifrefutable$14))).foreach((x$14) -> {
                     $anonfun$apply$28(result, b, x$14);
                     return BoxedUnit.UNIT;
                  });
               }

               return result;
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$25(final Tuple2 check$ifrefutable$13) {
            boolean var1;
            if (check$ifrefutable$13 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$26(final HashVector result$5, final HashVector b$5, final Tuple2 x$13) {
            if (x$13 != null) {
               int k = x$13._1$mcI$sp();
               int v = x$13._2$mcI$sp();
               result$5.update$mcI$sp(k, v % b$5.apply$mcI$sp(k));
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$13);
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$27(final Tuple2 check$ifrefutable$14) {
            boolean var1;
            if (check$ifrefutable$14 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$28(final HashVector result$5, final HashVector b$5, final Tuple2 x$14) {
            if (x$14 != null) {
               int k = x$14._1$mcI$sp();
               int v = x$14._2$mcI$sp();
               result$5.update$mcI$sp(k, v % b$5.apply$mcI$sp(k));
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$14);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_Int_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(HashVector.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_lhs_nilpotent_Double_OpMod_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               HashVector result = new HashVector$mcD$sp(new OpenAddressHashArray$mcD$sp(a.length(), ConfigurableDefault$.MODULE$.fromV(BoxesRunTime.boxToDouble((double)0.0F)), a.array$mcD$sp().iterableSize(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero()));
               if (b.activeSize() != b.size()) {
                  a.iterator().withFilter((check$ifrefutable$15) -> BoxesRunTime.boxToBoolean($anonfun$apply$29(check$ifrefutable$15))).foreach((x$15) -> {
                     $anonfun$apply$30(result, b, x$15);
                     return BoxedUnit.UNIT;
                  });
               } else {
                  a.activeIterator().withFilter((check$ifrefutable$16) -> BoxesRunTime.boxToBoolean($anonfun$apply$31(check$ifrefutable$16))).foreach((x$16) -> {
                     $anonfun$apply$32(result, b, x$16);
                     return BoxedUnit.UNIT;
                  });
               }

               return result;
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$29(final Tuple2 check$ifrefutable$15) {
            boolean var1;
            if (check$ifrefutable$15 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$30(final HashVector result$6, final HashVector b$6, final Tuple2 x$15) {
            if (x$15 != null) {
               int k = x$15._1$mcI$sp();
               double v = x$15._2$mcD$sp();
               result$6.update$mcD$sp(k, v % b$6.apply$mcD$sp(k));
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$15);
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$31(final Tuple2 check$ifrefutable$16) {
            boolean var1;
            if (check$ifrefutable$16 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$32(final HashVector result$6, final HashVector b$6, final Tuple2 x$16) {
            if (x$16 != null) {
               int k = x$16._1$mcI$sp();
               double v = x$16._2$mcD$sp();
               result$6.update$mcD$sp(k, v % b$6.apply$mcD$sp(k));
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$16);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_Double_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(HashVector.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_lhs_nilpotent_Float_OpMod_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               HashVector result = new HashVector$mcF$sp(new OpenAddressHashArray$mcF$sp(a.length(), ConfigurableDefault$.MODULE$.fromV(BoxesRunTime.boxToFloat(0.0F)), a.array$mcF$sp().iterableSize(), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero()));
               if (b.activeSize() != b.size()) {
                  a.iterator().withFilter((check$ifrefutable$17) -> BoxesRunTime.boxToBoolean($anonfun$apply$33(check$ifrefutable$17))).foreach((x$17) -> {
                     $anonfun$apply$34(result, b, x$17);
                     return BoxedUnit.UNIT;
                  });
               } else {
                  a.activeIterator().withFilter((check$ifrefutable$18) -> BoxesRunTime.boxToBoolean($anonfun$apply$35(check$ifrefutable$18))).foreach((x$18) -> {
                     $anonfun$apply$36(result, b, x$18);
                     return BoxedUnit.UNIT;
                  });
               }

               return result;
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$33(final Tuple2 check$ifrefutable$17) {
            boolean var1;
            if (check$ifrefutable$17 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$34(final HashVector result$7, final HashVector b$7, final Tuple2 x$17) {
            if (x$17 != null) {
               int k = x$17._1$mcI$sp();
               float v = BoxesRunTime.unboxToFloat(x$17._2());
               result$7.update$mcF$sp(k, v % b$7.apply$mcF$sp(k));
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$17);
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$35(final Tuple2 check$ifrefutable$18) {
            boolean var1;
            if (check$ifrefutable$18 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$36(final HashVector result$7, final HashVector b$7, final Tuple2 x$18) {
            if (x$18 != null) {
               int k = x$18._1$mcI$sp();
               float v = BoxesRunTime.unboxToFloat(x$18._2());
               result$7.update$mcF$sp(k, v % b$7.apply$mcF$sp(k));
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$18);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_Float_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(HashVector.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_lhs_nilpotent_Long_OpMod_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               HashVector result = new HashVector$mcJ$sp(new OpenAddressHashArray$mcJ$sp(a.length(), ConfigurableDefault$.MODULE$.fromV(BoxesRunTime.boxToLong(0L)), a.array$mcJ$sp().iterableSize(), scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero()));
               if (b.activeSize() != b.size()) {
                  a.iterator().withFilter((check$ifrefutable$19) -> BoxesRunTime.boxToBoolean($anonfun$apply$37(check$ifrefutable$19))).foreach((x$19) -> {
                     $anonfun$apply$38(result, b, x$19);
                     return BoxedUnit.UNIT;
                  });
               } else {
                  a.activeIterator().withFilter((check$ifrefutable$20) -> BoxesRunTime.boxToBoolean($anonfun$apply$39(check$ifrefutable$20))).foreach((x$20) -> {
                     $anonfun$apply$40(result, b, x$20);
                     return BoxedUnit.UNIT;
                  });
               }

               return result;
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$37(final Tuple2 check$ifrefutable$19) {
            boolean var1;
            if (check$ifrefutable$19 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$38(final HashVector result$8, final HashVector b$8, final Tuple2 x$19) {
            if (x$19 != null) {
               int k = x$19._1$mcI$sp();
               long v = x$19._2$mcJ$sp();
               result$8.update$mcJ$sp(k, v % b$8.apply$mcJ$sp(k));
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$19);
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$39(final Tuple2 check$ifrefutable$20) {
            boolean var1;
            if (check$ifrefutable$20 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$40(final HashVector result$8, final HashVector b$8, final Tuple2 x$20) {
            if (x$20 != null) {
               int k = x$20._1$mcI$sp();
               long v = x$20._2$mcJ$sp();
               result$8.update$mcJ$sp(k, v % b$8.apply$mcJ$sp(k));
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$20);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_Long_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(HashVector.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_lhs_nilpotent_Int_OpPow_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               HashVector result = new HashVector$mcI$sp(new OpenAddressHashArray$mcI$sp(a.length(), ConfigurableDefault$.MODULE$.fromV(BoxesRunTime.boxToInteger(0)), a.array$mcI$sp().iterableSize(), scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero()));
               if (b.activeSize() != b.size()) {
                  a.iterator().withFilter((check$ifrefutable$21) -> BoxesRunTime.boxToBoolean($anonfun$apply$41(check$ifrefutable$21))).foreach((x$21) -> {
                     $anonfun$apply$42(result, b, x$21);
                     return BoxedUnit.UNIT;
                  });
               } else {
                  a.activeIterator().withFilter((check$ifrefutable$22) -> BoxesRunTime.boxToBoolean($anonfun$apply$43(check$ifrefutable$22))).foreach((x$22) -> {
                     $anonfun$apply$44(result, b, x$22);
                     return BoxedUnit.UNIT;
                  });
               }

               return result;
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$41(final Tuple2 check$ifrefutable$21) {
            boolean var1;
            if (check$ifrefutable$21 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$42(final HashVector result$9, final HashVector b$9, final Tuple2 x$21) {
            if (x$21 != null) {
               int k = x$21._1$mcI$sp();
               int v = x$21._2$mcI$sp();
               result$9.update$mcI$sp(k, PowImplicits$.MODULE$.IntPow(v).pow(b$9.apply$mcI$sp(k)));
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$21);
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$43(final Tuple2 check$ifrefutable$22) {
            boolean var1;
            if (check$ifrefutable$22 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$44(final HashVector result$9, final HashVector b$9, final Tuple2 x$22) {
            if (x$22 != null) {
               int k = x$22._1$mcI$sp();
               int v = x$22._2$mcI$sp();
               result$9.update$mcI$sp(k, PowImplicits$.MODULE$.IntPow(v).pow(b$9.apply$mcI$sp(k)));
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$22);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_Int_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(HashVector.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_lhs_nilpotent_Double_OpPow_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               HashVector result = new HashVector$mcD$sp(new OpenAddressHashArray$mcD$sp(a.length(), ConfigurableDefault$.MODULE$.fromV(BoxesRunTime.boxToDouble((double)0.0F)), a.array$mcD$sp().iterableSize(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero()));
               if (b.activeSize() != b.size()) {
                  a.iterator().withFilter((check$ifrefutable$23) -> BoxesRunTime.boxToBoolean($anonfun$apply$45(check$ifrefutable$23))).foreach((x$23) -> {
                     $anonfun$apply$46(result, b, x$23);
                     return BoxedUnit.UNIT;
                  });
               } else {
                  a.activeIterator().withFilter((check$ifrefutable$24) -> BoxesRunTime.boxToBoolean($anonfun$apply$47(check$ifrefutable$24))).foreach((x$24) -> {
                     $anonfun$apply$48(result, b, x$24);
                     return BoxedUnit.UNIT;
                  });
               }

               return result;
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$45(final Tuple2 check$ifrefutable$23) {
            boolean var1;
            if (check$ifrefutable$23 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$46(final HashVector result$10, final HashVector b$10, final Tuple2 x$23) {
            if (x$23 != null) {
               int k = x$23._1$mcI$sp();
               double v = x$23._2$mcD$sp();
               result$10.update$mcD$sp(k, PowImplicits$.MODULE$.DoublePow(v).pow(b$10.apply$mcD$sp(k)));
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$23);
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$47(final Tuple2 check$ifrefutable$24) {
            boolean var1;
            if (check$ifrefutable$24 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$48(final HashVector result$10, final HashVector b$10, final Tuple2 x$24) {
            if (x$24 != null) {
               int k = x$24._1$mcI$sp();
               double v = x$24._2$mcD$sp();
               result$10.update$mcD$sp(k, PowImplicits$.MODULE$.DoublePow(v).pow(b$10.apply$mcD$sp(k)));
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$24);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_Double_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(HashVector.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_lhs_nilpotent_Float_OpPow_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               HashVector result = new HashVector$mcF$sp(new OpenAddressHashArray$mcF$sp(a.length(), ConfigurableDefault$.MODULE$.fromV(BoxesRunTime.boxToFloat(0.0F)), a.array$mcF$sp().iterableSize(), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero()));
               if (b.activeSize() != b.size()) {
                  a.iterator().withFilter((check$ifrefutable$25) -> BoxesRunTime.boxToBoolean($anonfun$apply$49(check$ifrefutable$25))).foreach((x$25) -> {
                     $anonfun$apply$50(result, b, x$25);
                     return BoxedUnit.UNIT;
                  });
               } else {
                  a.activeIterator().withFilter((check$ifrefutable$26) -> BoxesRunTime.boxToBoolean($anonfun$apply$51(check$ifrefutable$26))).foreach((x$26) -> {
                     $anonfun$apply$52(result, b, x$26);
                     return BoxedUnit.UNIT;
                  });
               }

               return result;
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$49(final Tuple2 check$ifrefutable$25) {
            boolean var1;
            if (check$ifrefutable$25 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$50(final HashVector result$11, final HashVector b$11, final Tuple2 x$25) {
            if (x$25 != null) {
               int k = x$25._1$mcI$sp();
               float v = BoxesRunTime.unboxToFloat(x$25._2());
               result$11.update$mcF$sp(k, PowImplicits$.MODULE$.FloatPow(v).pow(b$11.apply$mcF$sp(k)));
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$25);
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$51(final Tuple2 check$ifrefutable$26) {
            boolean var1;
            if (check$ifrefutable$26 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$52(final HashVector result$11, final HashVector b$11, final Tuple2 x$26) {
            if (x$26 != null) {
               int k = x$26._1$mcI$sp();
               float v = BoxesRunTime.unboxToFloat(x$26._2());
               result$11.update$mcF$sp(k, PowImplicits$.MODULE$.FloatPow(v).pow(b$11.apply$mcF$sp(k)));
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$26);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_Float_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(HashVector.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_HV_eq_HV_lhs_nilpotent_Long_OpPow_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               HashVector result = new HashVector$mcJ$sp(new OpenAddressHashArray$mcJ$sp(a.length(), ConfigurableDefault$.MODULE$.fromV(BoxesRunTime.boxToLong(0L)), a.array$mcJ$sp().iterableSize(), scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero()));
               if (b.activeSize() != b.size()) {
                  a.iterator().withFilter((check$ifrefutable$27) -> BoxesRunTime.boxToBoolean($anonfun$apply$53(check$ifrefutable$27))).foreach((x$27) -> {
                     $anonfun$apply$54(result, b, x$27);
                     return BoxedUnit.UNIT;
                  });
               } else {
                  a.activeIterator().withFilter((check$ifrefutable$28) -> BoxesRunTime.boxToBoolean($anonfun$apply$55(check$ifrefutable$28))).foreach((x$28) -> {
                     $anonfun$apply$56(result, b, x$28);
                     return BoxedUnit.UNIT;
                  });
               }

               return result;
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$53(final Tuple2 check$ifrefutable$27) {
            boolean var1;
            if (check$ifrefutable$27 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$54(final HashVector result$12, final HashVector b$12, final Tuple2 x$27) {
            if (x$27 != null) {
               int k = x$27._1$mcI$sp();
               long v = x$27._2$mcJ$sp();
               result$12.update$mcJ$sp(k, PowImplicits$.MODULE$.LongPow(v).pow(b$12.apply$mcJ$sp(k)));
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$27);
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$55(final Tuple2 check$ifrefutable$28) {
            boolean var1;
            if (check$ifrefutable$28 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$56(final HashVector result$12, final HashVector b$12, final Tuple2 x$28) {
            if (x$28 != null) {
               int k = x$28._1$mcI$sp();
               long v = x$28._2$mcJ$sp();
               result$12.update$mcJ$sp(k, PowImplicits$.MODULE$.LongPow(v).pow(b$12.apply$mcJ$sp(k)));
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$28);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_Long_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(HashVector.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Int_OpAdd_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               HashVector result = HashVector$.MODULE$.zeros$mIc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());

               for(int i = 0; i < a.length(); ++i) {
                  result.update$mcI$sp(i, a.apply$mcI$sp(i) + b.apply$mcII$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_idempotent_Int_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Double_OpAdd_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               HashVector result = HashVector$.MODULE$.zeros$mDc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());

               for(int i = 0; i < a.length(); ++i) {
                  result.update$mcD$sp(i, a.apply$mcD$sp(i) + b.apply$mcID$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_idempotent_Double_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Float_OpAdd_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               HashVector result = HashVector$.MODULE$.zeros$mFc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());

               for(int i = 0; i < a.length(); ++i) {
                  result.update$mcF$sp(i, a.apply$mcF$sp(i) + b.apply$mcIF$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_idempotent_Float_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Long_OpAdd_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               HashVector result = HashVector$.MODULE$.zeros$mJc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());

               for(int i = 0; i < a.length(); ++i) {
                  result.update$mcJ$sp(i, a.apply$mcJ$sp(i) + b.apply$mcIJ$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_idempotent_Long_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Int_OpSub_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               HashVector result = HashVector$.MODULE$.zeros$mIc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());

               for(int i = 0; i < a.length(); ++i) {
                  result.update$mcI$sp(i, a.apply$mcI$sp(i) - b.apply$mcII$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_idempotent_Int_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Double_OpSub_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               HashVector result = HashVector$.MODULE$.zeros$mDc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());

               for(int i = 0; i < a.length(); ++i) {
                  result.update$mcD$sp(i, a.apply$mcD$sp(i) - b.apply$mcID$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_idempotent_Double_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Float_OpSub_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               HashVector result = HashVector$.MODULE$.zeros$mFc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());

               for(int i = 0; i < a.length(); ++i) {
                  result.update$mcF$sp(i, a.apply$mcF$sp(i) - b.apply$mcIF$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_idempotent_Float_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Long_OpSub_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               HashVector result = HashVector$.MODULE$.zeros$mJc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());

               for(int i = 0; i < a.length(); ++i) {
                  result.update$mcJ$sp(i, a.apply$mcJ$sp(i) - b.apply$mcIJ$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_idempotent_Long_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Int_OpMulScalar_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               HashVector result = HashVector$.MODULE$.zeros$mIc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());

               for(int i = 0; i < a.length(); ++i) {
                  result.update$mcI$sp(i, a.apply$mcI$sp(i) * b.apply$mcII$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_nilpotent_Int())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Double_OpMulScalar_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               HashVector result = HashVector$.MODULE$.zeros$mDc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());

               for(int i = 0; i < a.length(); ++i) {
                  result.update$mcD$sp(i, a.apply$mcD$sp(i) * b.apply$mcID$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_nilpotent_Double())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Float_OpMulScalar_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               HashVector result = HashVector$.MODULE$.zeros$mFc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());

               for(int i = 0; i < a.length(); ++i) {
                  result.update$mcF$sp(i, a.apply$mcF$sp(i) * b.apply$mcIF$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_nilpotent_Float())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Long_OpMulScalar_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               HashVector result = HashVector$.MODULE$.zeros$mJc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());

               for(int i = 0; i < a.length(); ++i) {
                  result.update$mcJ$sp(i, a.apply$mcJ$sp(i) * b.apply$mcIJ$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_nilpotent_Long())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Int_OpDiv_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               HashVector result = HashVector$.MODULE$.zeros$mIc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());

               for(int i = 0; i < a.length(); ++i) {
                  result.update$mcI$sp(i, a.apply$mcI$sp(i) / b.apply$mcII$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_Int_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Double_OpDiv_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               HashVector result = HashVector$.MODULE$.zeros$mDc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());

               for(int i = 0; i < a.length(); ++i) {
                  result.update$mcD$sp(i, a.apply$mcD$sp(i) / b.apply$mcID$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_Double_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Float_OpDiv_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               HashVector result = HashVector$.MODULE$.zeros$mFc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());

               for(int i = 0; i < a.length(); ++i) {
                  result.update$mcF$sp(i, a.apply$mcF$sp(i) / b.apply$mcIF$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_Float_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Long_OpDiv_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               HashVector result = HashVector$.MODULE$.zeros$mJc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());

               for(int i = 0; i < a.length(); ++i) {
                  result.update$mcJ$sp(i, a.apply$mcJ$sp(i) / b.apply$mcIJ$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_Long_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Int_OpSet_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               HashVector result = HashVector$.MODULE$.zeros$mIc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());

               for(int i = 0; i < a.length(); ++i) {
                  result.update$mcI$sp(i, b.apply$mcII$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_Int_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Double_OpSet_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               HashVector result = HashVector$.MODULE$.zeros$mDc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());

               for(int i = 0; i < a.length(); ++i) {
                  result.update$mcD$sp(i, b.apply$mcID$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_Double_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Float_OpSet_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               HashVector result = HashVector$.MODULE$.zeros$mFc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());

               for(int i = 0; i < a.length(); ++i) {
                  result.update$mcF$sp(i, b.apply$mcIF$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_Float_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Long_OpSet_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               HashVector result = HashVector$.MODULE$.zeros$mJc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());

               for(int i = 0; i < a.length(); ++i) {
                  result.update$mcJ$sp(i, b.apply$mcIJ$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_Long_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Int_OpMod_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               HashVector result = HashVector$.MODULE$.zeros$mIc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());

               for(int i = 0; i < a.length(); ++i) {
                  result.update$mcI$sp(i, a.apply$mcI$sp(i) % b.apply$mcII$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_Int_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Double_OpMod_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               HashVector result = HashVector$.MODULE$.zeros$mDc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());

               for(int i = 0; i < a.length(); ++i) {
                  result.update$mcD$sp(i, a.apply$mcD$sp(i) % b.apply$mcID$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_Double_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Float_OpMod_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               HashVector result = HashVector$.MODULE$.zeros$mFc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());

               for(int i = 0; i < a.length(); ++i) {
                  result.update$mcF$sp(i, a.apply$mcF$sp(i) % b.apply$mcIF$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_Float_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Long_OpMod_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               HashVector result = HashVector$.MODULE$.zeros$mJc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());

               for(int i = 0; i < a.length(); ++i) {
                  result.update$mcJ$sp(i, a.apply$mcJ$sp(i) % b.apply$mcIJ$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_Long_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Int_OpPow_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               HashVector result = HashVector$.MODULE$.zeros$mIc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());

               for(int i = 0; i < a.length(); ++i) {
                  result.update$mcI$sp(i, PowImplicits$.MODULE$.IntPow(a.apply$mcI$sp(i)).pow(b.apply$mcII$sp(i)));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_Int_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Double_OpPow_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               HashVector result = HashVector$.MODULE$.zeros$mDc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());

               for(int i = 0; i < a.length(); ++i) {
                  result.update$mcD$sp(i, PowImplicits$.MODULE$.DoublePow(a.apply$mcD$sp(i)).pow(b.apply$mcID$sp(i)));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_Double_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Float_OpPow_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               HashVector result = HashVector$.MODULE$.zeros$mFc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());

               for(int i = 0; i < a.length(); ++i) {
                  result.update$mcF$sp(i, PowImplicits$.MODULE$.FloatPow(a.apply$mcF$sp(i)).pow(b.apply$mcIF$sp(i)));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_Float_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_V_eq_HV_Long_OpPow_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               HashVector result = HashVector$.MODULE$.zeros$mJc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());

               for(int i = 0; i < a.length(); ++i) {
                  result.update$mcJ$sp(i, PowImplicits$.MODULE$.LongPow(a.apply$mcJ$sp(i)).pow(b.apply$mcIJ$sp(i)));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_V_eq_V_Long_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_add_Int_OpAdd_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final int b) {
            if (b == 0) {
               return a.copy$mcI$sp();
            } else {
               HashVector result = HashVector$.MODULE$.zeros$mIc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());

               for(int i = 0; i < a.length(); ++i) {
                  result.update$mcI$sp(i, a.apply$mcI$sp(i) + b);
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_S_eq_V_Int_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Int());
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_add_Double_OpAdd_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final double b) {
            if (b == (double)0) {
               return a.copy$mcD$sp();
            } else {
               HashVector result = HashVector$.MODULE$.zeros$mDc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());

               for(int i = 0; i < a.length(); ++i) {
                  result.update$mcD$sp(i, a.apply$mcD$sp(i) + b);
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_S_eq_V_Double_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Double());
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_add_Float_OpAdd_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final float b) {
            if (b == (float)0) {
               return a.copy$mcF$sp();
            } else {
               HashVector result = HashVector$.MODULE$.zeros$mFc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());

               for(int i = 0; i < a.length(); ++i) {
                  result.update$mcF$sp(i, a.apply$mcF$sp(i) + b);
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_S_eq_V_Float_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Float());
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_add_Long_OpAdd_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final long b) {
            if (b == 0L) {
               return a.copy$mcJ$sp();
            } else {
               HashVector result = HashVector$.MODULE$.zeros$mJc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());

               for(int i = 0; i < a.length(); ++i) {
                  result.update$mcJ$sp(i, a.apply$mcJ$sp(i) + b);
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_S_eq_V_Long_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Long());
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_add_Int_OpSub_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final int b) {
            if (b == 0) {
               return a.copy$mcI$sp();
            } else {
               HashVector result = HashVector$.MODULE$.zeros$mIc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());

               for(int i = 0; i < a.length(); ++i) {
                  result.update$mcI$sp(i, a.apply$mcI$sp(i) - b);
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_S_eq_V_Int_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Int());
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_add_Double_OpSub_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final double b) {
            if (b == (double)0) {
               return a.copy$mcD$sp();
            } else {
               HashVector result = HashVector$.MODULE$.zeros$mDc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());

               for(int i = 0; i < a.length(); ++i) {
                  result.update$mcD$sp(i, a.apply$mcD$sp(i) - b);
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_S_eq_V_Double_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Double());
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_add_Float_OpSub_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final float b) {
            if (b == (float)0) {
               return a.copy$mcF$sp();
            } else {
               HashVector result = HashVector$.MODULE$.zeros$mFc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());

               for(int i = 0; i < a.length(); ++i) {
                  result.update$mcF$sp(i, a.apply$mcF$sp(i) - b);
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_S_eq_V_Float_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Float());
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_add_Long_OpSub_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final long b) {
            if (b == 0L) {
               return a.copy$mcJ$sp();
            } else {
               HashVector result = HashVector$.MODULE$.zeros$mJc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());

               for(int i = 0; i < a.length(); ++i) {
                  result.update$mcJ$sp(i, a.apply$mcJ$sp(i) - b);
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_S_eq_V_Long_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Long());
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Int_OpMulScalar_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final int b) {
            HashVector result;
            label30: {
               label29: {
                  result = HashVector$.MODULE$.zeros$mIc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());
                  OpMulScalar$ var4;
                  OpMulScalar$ var10000 = var4 = OpMulScalar$.MODULE$;
                  if (var10000 == null) {
                     if (var4 == null) {
                        break label29;
                     }
                  } else if (var10000.equals(var4)) {
                     break label29;
                  }

                  var10000 = OpMulScalar$.MODULE$;
                  OpMulMatrix$ var5 = OpMulMatrix$.MODULE$;
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label30;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label30;
                  }
               }

               if (b == 0) {
                  return result;
               }
            }

            if (b == 0) {
               a.iterator().withFilter((check$ifrefutable$29) -> BoxesRunTime.boxToBoolean($anonfun$apply$57(check$ifrefutable$29))).foreach((x$29) -> {
                  $anonfun$apply$58(result, b, x$29);
                  return BoxedUnit.UNIT;
               });
            } else {
               a.activeIterator().withFilter((check$ifrefutable$30) -> BoxesRunTime.boxToBoolean($anonfun$apply$59(check$ifrefutable$30))).foreach((x$30) -> {
                  $anonfun$apply$60(result, b, x$30);
                  return BoxedUnit.UNIT;
               });
            }

            return result;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$57(final Tuple2 check$ifrefutable$29) {
            boolean var1;
            if (check$ifrefutable$29 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$58(final HashVector result$13, final int b$13, final Tuple2 x$29) {
            if (x$29 != null) {
               int k = x$29._1$mcI$sp();
               int v = x$29._2$mcI$sp();
               result$13.update$mcI$sp(k, v * b$13);
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$29);
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$59(final Tuple2 check$ifrefutable$30) {
            boolean var1;
            if (check$ifrefutable$30 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$60(final HashVector result$13, final int b$13, final Tuple2 x$30) {
            if (x$30 != null) {
               int k = x$30._1$mcI$sp();
               int v = x$30._2$mcI$sp();
               result$13.update$mcI$sp(k, v * b$13);
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$30);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_S_eq_V_Int_OpMulScalar())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Int());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Double_OpMulScalar_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final double b) {
            HashVector result;
            label30: {
               label29: {
                  result = HashVector$.MODULE$.zeros$mDc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
                  OpMulScalar$ var5;
                  OpMulScalar$ var10000 = var5 = OpMulScalar$.MODULE$;
                  if (var10000 == null) {
                     if (var5 == null) {
                        break label29;
                     }
                  } else if (var10000.equals(var5)) {
                     break label29;
                  }

                  var10000 = OpMulScalar$.MODULE$;
                  OpMulMatrix$ var6 = OpMulMatrix$.MODULE$;
                  if (var10000 == null) {
                     if (var6 != null) {
                        break label30;
                     }
                  } else if (!var10000.equals(var6)) {
                     break label30;
                  }
               }

               if (b == (double)0) {
                  return result;
               }
            }

            if (b == (double)0) {
               a.iterator().withFilter((check$ifrefutable$31) -> BoxesRunTime.boxToBoolean($anonfun$apply$61(check$ifrefutable$31))).foreach((x$31) -> {
                  $anonfun$apply$62(result, b, x$31);
                  return BoxedUnit.UNIT;
               });
            } else {
               a.activeIterator().withFilter((check$ifrefutable$32) -> BoxesRunTime.boxToBoolean($anonfun$apply$63(check$ifrefutable$32))).foreach((x$32) -> {
                  $anonfun$apply$64(result, b, x$32);
                  return BoxedUnit.UNIT;
               });
            }

            return result;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$61(final Tuple2 check$ifrefutable$31) {
            boolean var1;
            if (check$ifrefutable$31 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$62(final HashVector result$14, final double b$14, final Tuple2 x$31) {
            if (x$31 != null) {
               int k = x$31._1$mcI$sp();
               double v = x$31._2$mcD$sp();
               result$14.update$mcD$sp(k, v * b$14);
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$31);
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$63(final Tuple2 check$ifrefutable$32) {
            boolean var1;
            if (check$ifrefutable$32 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$64(final HashVector result$14, final double b$14, final Tuple2 x$32) {
            if (x$32 != null) {
               int k = x$32._1$mcI$sp();
               double v = x$32._2$mcD$sp();
               result$14.update$mcD$sp(k, v * b$14);
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$32);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_S_eq_V_Double_OpMulScalar())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Double());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Float_OpMulScalar_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final float b) {
            HashVector result;
            label30: {
               label29: {
                  result = HashVector$.MODULE$.zeros$mFc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());
                  OpMulScalar$ var4;
                  OpMulScalar$ var10000 = var4 = OpMulScalar$.MODULE$;
                  if (var10000 == null) {
                     if (var4 == null) {
                        break label29;
                     }
                  } else if (var10000.equals(var4)) {
                     break label29;
                  }

                  var10000 = OpMulScalar$.MODULE$;
                  OpMulMatrix$ var5 = OpMulMatrix$.MODULE$;
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label30;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label30;
                  }
               }

               if (b == (float)0) {
                  return result;
               }
            }

            if (b == (float)0) {
               a.iterator().withFilter((check$ifrefutable$33) -> BoxesRunTime.boxToBoolean($anonfun$apply$65(check$ifrefutable$33))).foreach((x$33) -> {
                  $anonfun$apply$66(result, b, x$33);
                  return BoxedUnit.UNIT;
               });
            } else {
               a.activeIterator().withFilter((check$ifrefutable$34) -> BoxesRunTime.boxToBoolean($anonfun$apply$67(check$ifrefutable$34))).foreach((x$34) -> {
                  $anonfun$apply$68(result, b, x$34);
                  return BoxedUnit.UNIT;
               });
            }

            return result;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$65(final Tuple2 check$ifrefutable$33) {
            boolean var1;
            if (check$ifrefutable$33 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$66(final HashVector result$15, final float b$15, final Tuple2 x$33) {
            if (x$33 != null) {
               int k = x$33._1$mcI$sp();
               float v = BoxesRunTime.unboxToFloat(x$33._2());
               result$15.update$mcF$sp(k, v * b$15);
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$33);
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$67(final Tuple2 check$ifrefutable$34) {
            boolean var1;
            if (check$ifrefutable$34 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$68(final HashVector result$15, final float b$15, final Tuple2 x$34) {
            if (x$34 != null) {
               int k = x$34._1$mcI$sp();
               float v = BoxesRunTime.unboxToFloat(x$34._2());
               result$15.update$mcF$sp(k, v * b$15);
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$34);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_S_eq_V_Float_OpMulScalar())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Float());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Long_OpMulScalar_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final long b) {
            HashVector result;
            label30: {
               label29: {
                  result = HashVector$.MODULE$.zeros$mJc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());
                  OpMulScalar$ var5;
                  OpMulScalar$ var10000 = var5 = OpMulScalar$.MODULE$;
                  if (var10000 == null) {
                     if (var5 == null) {
                        break label29;
                     }
                  } else if (var10000.equals(var5)) {
                     break label29;
                  }

                  var10000 = OpMulScalar$.MODULE$;
                  OpMulMatrix$ var6 = OpMulMatrix$.MODULE$;
                  if (var10000 == null) {
                     if (var6 != null) {
                        break label30;
                     }
                  } else if (!var10000.equals(var6)) {
                     break label30;
                  }
               }

               if (b == 0L) {
                  return result;
               }
            }

            if (b == 0L) {
               a.iterator().withFilter((check$ifrefutable$35) -> BoxesRunTime.boxToBoolean($anonfun$apply$69(check$ifrefutable$35))).foreach((x$35) -> {
                  $anonfun$apply$70(result, b, x$35);
                  return BoxedUnit.UNIT;
               });
            } else {
               a.activeIterator().withFilter((check$ifrefutable$36) -> BoxesRunTime.boxToBoolean($anonfun$apply$71(check$ifrefutable$36))).foreach((x$36) -> {
                  $anonfun$apply$72(result, b, x$36);
                  return BoxedUnit.UNIT;
               });
            }

            return result;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$69(final Tuple2 check$ifrefutable$35) {
            boolean var1;
            if (check$ifrefutable$35 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$70(final HashVector result$16, final long b$16, final Tuple2 x$35) {
            if (x$35 != null) {
               int k = x$35._1$mcI$sp();
               long v = x$35._2$mcJ$sp();
               result$16.update$mcJ$sp(k, v * b$16);
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$35);
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$71(final Tuple2 check$ifrefutable$36) {
            boolean var1;
            if (check$ifrefutable$36 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$72(final HashVector result$16, final long b$16, final Tuple2 x$36) {
            if (x$36 != null) {
               int k = x$36._1$mcI$sp();
               long v = x$36._2$mcJ$sp();
               result$16.update$mcJ$sp(k, v * b$16);
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$36);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_S_eq_V_Long_OpMulScalar())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Long());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Int_OpMulMatrix_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final int b) {
            HashVector result;
            label30: {
               label29: {
                  result = HashVector$.MODULE$.zeros$mIc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());
                  OpMulMatrix$ var10000 = OpMulMatrix$.MODULE$;
                  OpMulScalar$ var4 = OpMulScalar$.MODULE$;
                  if (var10000 == null) {
                     if (var4 == null) {
                        break label29;
                     }
                  } else if (var10000.equals(var4)) {
                     break label29;
                  }

                  OpMulMatrix$ var5;
                  var10000 = var5 = OpMulMatrix$.MODULE$;
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label30;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label30;
                  }
               }

               if (b == 0) {
                  return result;
               }
            }

            if (b == 0) {
               a.iterator().withFilter((check$ifrefutable$37) -> BoxesRunTime.boxToBoolean($anonfun$apply$73(check$ifrefutable$37))).foreach((x$37) -> {
                  $anonfun$apply$74(result, b, x$37);
                  return BoxedUnit.UNIT;
               });
            } else {
               a.activeIterator().withFilter((check$ifrefutable$38) -> BoxesRunTime.boxToBoolean($anonfun$apply$75(check$ifrefutable$38))).foreach((x$38) -> {
                  $anonfun$apply$76(result, b, x$38);
                  return BoxedUnit.UNIT;
               });
            }

            return result;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$73(final Tuple2 check$ifrefutable$37) {
            boolean var1;
            if (check$ifrefutable$37 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$74(final HashVector result$17, final int b$17, final Tuple2 x$37) {
            if (x$37 != null) {
               int k = x$37._1$mcI$sp();
               int v = x$37._2$mcI$sp();
               result$17.update$mcI$sp(k, v * b$17);
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$37);
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$75(final Tuple2 check$ifrefutable$38) {
            boolean var1;
            if (check$ifrefutable$38 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$76(final HashVector result$17, final int b$17, final Tuple2 x$38) {
            if (x$38 != null) {
               int k = x$38._1$mcI$sp();
               int v = x$38._2$mcI$sp();
               result$17.update$mcI$sp(k, v * b$17);
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$38);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_S_eq_V_Int_OpMulMatrix())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Int());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Double_OpMulMatrix_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final double b) {
            HashVector result;
            label30: {
               label29: {
                  result = HashVector$.MODULE$.zeros$mDc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
                  OpMulMatrix$ var10000 = OpMulMatrix$.MODULE$;
                  OpMulScalar$ var5 = OpMulScalar$.MODULE$;
                  if (var10000 == null) {
                     if (var5 == null) {
                        break label29;
                     }
                  } else if (var10000.equals(var5)) {
                     break label29;
                  }

                  OpMulMatrix$ var6;
                  var10000 = var6 = OpMulMatrix$.MODULE$;
                  if (var10000 == null) {
                     if (var6 != null) {
                        break label30;
                     }
                  } else if (!var10000.equals(var6)) {
                     break label30;
                  }
               }

               if (b == (double)0) {
                  return result;
               }
            }

            if (b == (double)0) {
               a.iterator().withFilter((check$ifrefutable$39) -> BoxesRunTime.boxToBoolean($anonfun$apply$77(check$ifrefutable$39))).foreach((x$39) -> {
                  $anonfun$apply$78(result, b, x$39);
                  return BoxedUnit.UNIT;
               });
            } else {
               a.activeIterator().withFilter((check$ifrefutable$40) -> BoxesRunTime.boxToBoolean($anonfun$apply$79(check$ifrefutable$40))).foreach((x$40) -> {
                  $anonfun$apply$80(result, b, x$40);
                  return BoxedUnit.UNIT;
               });
            }

            return result;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$77(final Tuple2 check$ifrefutable$39) {
            boolean var1;
            if (check$ifrefutable$39 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$78(final HashVector result$18, final double b$18, final Tuple2 x$39) {
            if (x$39 != null) {
               int k = x$39._1$mcI$sp();
               double v = x$39._2$mcD$sp();
               result$18.update$mcD$sp(k, v * b$18);
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$39);
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$79(final Tuple2 check$ifrefutable$40) {
            boolean var1;
            if (check$ifrefutable$40 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$80(final HashVector result$18, final double b$18, final Tuple2 x$40) {
            if (x$40 != null) {
               int k = x$40._1$mcI$sp();
               double v = x$40._2$mcD$sp();
               result$18.update$mcD$sp(k, v * b$18);
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$40);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_S_eq_V_Double_OpMulMatrix())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Double());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Float_OpMulMatrix_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final float b) {
            HashVector result;
            label30: {
               label29: {
                  result = HashVector$.MODULE$.zeros$mFc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());
                  OpMulMatrix$ var10000 = OpMulMatrix$.MODULE$;
                  OpMulScalar$ var4 = OpMulScalar$.MODULE$;
                  if (var10000 == null) {
                     if (var4 == null) {
                        break label29;
                     }
                  } else if (var10000.equals(var4)) {
                     break label29;
                  }

                  OpMulMatrix$ var5;
                  var10000 = var5 = OpMulMatrix$.MODULE$;
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label30;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label30;
                  }
               }

               if (b == (float)0) {
                  return result;
               }
            }

            if (b == (float)0) {
               a.iterator().withFilter((check$ifrefutable$41) -> BoxesRunTime.boxToBoolean($anonfun$apply$81(check$ifrefutable$41))).foreach((x$41) -> {
                  $anonfun$apply$82(result, b, x$41);
                  return BoxedUnit.UNIT;
               });
            } else {
               a.activeIterator().withFilter((check$ifrefutable$42) -> BoxesRunTime.boxToBoolean($anonfun$apply$83(check$ifrefutable$42))).foreach((x$42) -> {
                  $anonfun$apply$84(result, b, x$42);
                  return BoxedUnit.UNIT;
               });
            }

            return result;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$81(final Tuple2 check$ifrefutable$41) {
            boolean var1;
            if (check$ifrefutable$41 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$82(final HashVector result$19, final float b$19, final Tuple2 x$41) {
            if (x$41 != null) {
               int k = x$41._1$mcI$sp();
               float v = BoxesRunTime.unboxToFloat(x$41._2());
               result$19.update$mcF$sp(k, v * b$19);
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$41);
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$83(final Tuple2 check$ifrefutable$42) {
            boolean var1;
            if (check$ifrefutable$42 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$84(final HashVector result$19, final float b$19, final Tuple2 x$42) {
            if (x$42 != null) {
               int k = x$42._1$mcI$sp();
               float v = BoxesRunTime.unboxToFloat(x$42._2());
               result$19.update$mcF$sp(k, v * b$19);
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$42);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_S_eq_V_Float_OpMulMatrix())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Float());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Long_OpMulMatrix_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final long b) {
            HashVector result;
            label30: {
               label29: {
                  result = HashVector$.MODULE$.zeros$mJc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());
                  OpMulMatrix$ var10000 = OpMulMatrix$.MODULE$;
                  OpMulScalar$ var5 = OpMulScalar$.MODULE$;
                  if (var10000 == null) {
                     if (var5 == null) {
                        break label29;
                     }
                  } else if (var10000.equals(var5)) {
                     break label29;
                  }

                  OpMulMatrix$ var6;
                  var10000 = var6 = OpMulMatrix$.MODULE$;
                  if (var10000 == null) {
                     if (var6 != null) {
                        break label30;
                     }
                  } else if (!var10000.equals(var6)) {
                     break label30;
                  }
               }

               if (b == 0L) {
                  return result;
               }
            }

            if (b == 0L) {
               a.iterator().withFilter((check$ifrefutable$43) -> BoxesRunTime.boxToBoolean($anonfun$apply$85(check$ifrefutable$43))).foreach((x$43) -> {
                  $anonfun$apply$86(result, b, x$43);
                  return BoxedUnit.UNIT;
               });
            } else {
               a.activeIterator().withFilter((check$ifrefutable$44) -> BoxesRunTime.boxToBoolean($anonfun$apply$87(check$ifrefutable$44))).foreach((x$44) -> {
                  $anonfun$apply$88(result, b, x$44);
                  return BoxedUnit.UNIT;
               });
            }

            return result;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$85(final Tuple2 check$ifrefutable$43) {
            boolean var1;
            if (check$ifrefutable$43 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$86(final HashVector result$20, final long b$20, final Tuple2 x$43) {
            if (x$43 != null) {
               int k = x$43._1$mcI$sp();
               long v = x$43._2$mcJ$sp();
               result$20.update$mcJ$sp(k, v * b$20);
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$43);
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$87(final Tuple2 check$ifrefutable$44) {
            boolean var1;
            if (check$ifrefutable$44 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$88(final HashVector result$20, final long b$20, final Tuple2 x$44) {
            if (x$44 != null) {
               int k = x$44._1$mcI$sp();
               long v = x$44._2$mcJ$sp();
               result$20.update$mcJ$sp(k, v * b$20);
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$44);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_S_eq_V_Long_OpMulMatrix())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Long());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Int_OpDiv_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final int b) {
            HashVector result;
            label30: {
               label29: {
                  result = HashVector$.MODULE$.zeros$mIc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());
                  OpDiv$ var10000 = OpDiv$.MODULE$;
                  OpMulScalar$ var4 = OpMulScalar$.MODULE$;
                  if (var10000 == null) {
                     if (var4 == null) {
                        break label29;
                     }
                  } else if (var10000.equals(var4)) {
                     break label29;
                  }

                  var10000 = OpDiv$.MODULE$;
                  OpMulMatrix$ var5 = OpMulMatrix$.MODULE$;
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label30;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label30;
                  }
               }

               if (b == 0) {
                  return result;
               }
            }

            if (b == 0) {
               a.iterator().withFilter((check$ifrefutable$45) -> BoxesRunTime.boxToBoolean($anonfun$apply$89(check$ifrefutable$45))).foreach((x$45) -> {
                  $anonfun$apply$90(result, b, x$45);
                  return BoxedUnit.UNIT;
               });
            } else {
               a.activeIterator().withFilter((check$ifrefutable$46) -> BoxesRunTime.boxToBoolean($anonfun$apply$91(check$ifrefutable$46))).foreach((x$46) -> {
                  $anonfun$apply$92(result, b, x$46);
                  return BoxedUnit.UNIT;
               });
            }

            return result;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$89(final Tuple2 check$ifrefutable$45) {
            boolean var1;
            if (check$ifrefutable$45 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$90(final HashVector result$21, final int b$21, final Tuple2 x$45) {
            if (x$45 != null) {
               int k = x$45._1$mcI$sp();
               int v = x$45._2$mcI$sp();
               result$21.update$mcI$sp(k, v / b$21);
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$45);
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$91(final Tuple2 check$ifrefutable$46) {
            boolean var1;
            if (check$ifrefutable$46 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$92(final HashVector result$21, final int b$21, final Tuple2 x$46) {
            if (x$46 != null) {
               int k = x$46._1$mcI$sp();
               int v = x$46._2$mcI$sp();
               result$21.update$mcI$sp(k, v / b$21);
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$46);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_S_eq_V_Int_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Int());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Double_OpDiv_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final double b) {
            HashVector result;
            label30: {
               label29: {
                  result = HashVector$.MODULE$.zeros$mDc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
                  OpDiv$ var10000 = OpDiv$.MODULE$;
                  OpMulScalar$ var5 = OpMulScalar$.MODULE$;
                  if (var10000 == null) {
                     if (var5 == null) {
                        break label29;
                     }
                  } else if (var10000.equals(var5)) {
                     break label29;
                  }

                  var10000 = OpDiv$.MODULE$;
                  OpMulMatrix$ var6 = OpMulMatrix$.MODULE$;
                  if (var10000 == null) {
                     if (var6 != null) {
                        break label30;
                     }
                  } else if (!var10000.equals(var6)) {
                     break label30;
                  }
               }

               if (b == (double)0) {
                  return result;
               }
            }

            if (b == (double)0) {
               a.iterator().withFilter((check$ifrefutable$47) -> BoxesRunTime.boxToBoolean($anonfun$apply$93(check$ifrefutable$47))).foreach((x$47) -> {
                  $anonfun$apply$94(result, b, x$47);
                  return BoxedUnit.UNIT;
               });
            } else {
               a.activeIterator().withFilter((check$ifrefutable$48) -> BoxesRunTime.boxToBoolean($anonfun$apply$95(check$ifrefutable$48))).foreach((x$48) -> {
                  $anonfun$apply$96(result, b, x$48);
                  return BoxedUnit.UNIT;
               });
            }

            return result;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$93(final Tuple2 check$ifrefutable$47) {
            boolean var1;
            if (check$ifrefutable$47 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$94(final HashVector result$22, final double b$22, final Tuple2 x$47) {
            if (x$47 != null) {
               int k = x$47._1$mcI$sp();
               double v = x$47._2$mcD$sp();
               result$22.update$mcD$sp(k, v / b$22);
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$47);
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$95(final Tuple2 check$ifrefutable$48) {
            boolean var1;
            if (check$ifrefutable$48 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$96(final HashVector result$22, final double b$22, final Tuple2 x$48) {
            if (x$48 != null) {
               int k = x$48._1$mcI$sp();
               double v = x$48._2$mcD$sp();
               result$22.update$mcD$sp(k, v / b$22);
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$48);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_S_eq_V_Double_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Double());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Float_OpDiv_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final float b) {
            HashVector result;
            label30: {
               label29: {
                  result = HashVector$.MODULE$.zeros$mFc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());
                  OpDiv$ var10000 = OpDiv$.MODULE$;
                  OpMulScalar$ var4 = OpMulScalar$.MODULE$;
                  if (var10000 == null) {
                     if (var4 == null) {
                        break label29;
                     }
                  } else if (var10000.equals(var4)) {
                     break label29;
                  }

                  var10000 = OpDiv$.MODULE$;
                  OpMulMatrix$ var5 = OpMulMatrix$.MODULE$;
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label30;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label30;
                  }
               }

               if (b == (float)0) {
                  return result;
               }
            }

            if (b == (float)0) {
               a.iterator().withFilter((check$ifrefutable$49) -> BoxesRunTime.boxToBoolean($anonfun$apply$97(check$ifrefutable$49))).foreach((x$49) -> {
                  $anonfun$apply$98(result, b, x$49);
                  return BoxedUnit.UNIT;
               });
            } else {
               a.activeIterator().withFilter((check$ifrefutable$50) -> BoxesRunTime.boxToBoolean($anonfun$apply$99(check$ifrefutable$50))).foreach((x$50) -> {
                  $anonfun$apply$100(result, b, x$50);
                  return BoxedUnit.UNIT;
               });
            }

            return result;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$97(final Tuple2 check$ifrefutable$49) {
            boolean var1;
            if (check$ifrefutable$49 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$98(final HashVector result$23, final float b$23, final Tuple2 x$49) {
            if (x$49 != null) {
               int k = x$49._1$mcI$sp();
               float v = BoxesRunTime.unboxToFloat(x$49._2());
               result$23.update$mcF$sp(k, v / b$23);
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$49);
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$99(final Tuple2 check$ifrefutable$50) {
            boolean var1;
            if (check$ifrefutable$50 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$100(final HashVector result$23, final float b$23, final Tuple2 x$50) {
            if (x$50 != null) {
               int k = x$50._1$mcI$sp();
               float v = BoxesRunTime.unboxToFloat(x$50._2());
               result$23.update$mcF$sp(k, v / b$23);
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$50);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_S_eq_V_Float_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Float());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Long_OpDiv_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final long b) {
            HashVector result;
            label30: {
               label29: {
                  result = HashVector$.MODULE$.zeros$mJc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());
                  OpDiv$ var10000 = OpDiv$.MODULE$;
                  OpMulScalar$ var5 = OpMulScalar$.MODULE$;
                  if (var10000 == null) {
                     if (var5 == null) {
                        break label29;
                     }
                  } else if (var10000.equals(var5)) {
                     break label29;
                  }

                  var10000 = OpDiv$.MODULE$;
                  OpMulMatrix$ var6 = OpMulMatrix$.MODULE$;
                  if (var10000 == null) {
                     if (var6 != null) {
                        break label30;
                     }
                  } else if (!var10000.equals(var6)) {
                     break label30;
                  }
               }

               if (b == 0L) {
                  return result;
               }
            }

            if (b == 0L) {
               a.iterator().withFilter((check$ifrefutable$51) -> BoxesRunTime.boxToBoolean($anonfun$apply$101(check$ifrefutable$51))).foreach((x$51) -> {
                  $anonfun$apply$102(result, b, x$51);
                  return BoxedUnit.UNIT;
               });
            } else {
               a.activeIterator().withFilter((check$ifrefutable$52) -> BoxesRunTime.boxToBoolean($anonfun$apply$103(check$ifrefutable$52))).foreach((x$52) -> {
                  $anonfun$apply$104(result, b, x$52);
                  return BoxedUnit.UNIT;
               });
            }

            return result;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$101(final Tuple2 check$ifrefutable$51) {
            boolean var1;
            if (check$ifrefutable$51 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$102(final HashVector result$24, final long b$24, final Tuple2 x$51) {
            if (x$51 != null) {
               int k = x$51._1$mcI$sp();
               long v = x$51._2$mcJ$sp();
               result$24.update$mcJ$sp(k, v / b$24);
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$51);
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$103(final Tuple2 check$ifrefutable$52) {
            boolean var1;
            if (check$ifrefutable$52 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$104(final HashVector result$24, final long b$24, final Tuple2 x$52) {
            if (x$52 != null) {
               int k = x$52._1$mcI$sp();
               long v = x$52._2$mcJ$sp();
               result$24.update$mcJ$sp(k, v / b$24);
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$52);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_S_eq_V_Long_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Long());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Int_OpMod_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final int b) {
            HashVector result;
            label30: {
               label29: {
                  result = HashVector$.MODULE$.zeros$mIc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());
                  OpMod$ var10000 = OpMod$.MODULE$;
                  OpMulScalar$ var4 = OpMulScalar$.MODULE$;
                  if (var10000 == null) {
                     if (var4 == null) {
                        break label29;
                     }
                  } else if (var10000.equals(var4)) {
                     break label29;
                  }

                  var10000 = OpMod$.MODULE$;
                  OpMulMatrix$ var5 = OpMulMatrix$.MODULE$;
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label30;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label30;
                  }
               }

               if (b == 0) {
                  return result;
               }
            }

            if (b == 0) {
               a.iterator().withFilter((check$ifrefutable$53) -> BoxesRunTime.boxToBoolean($anonfun$apply$105(check$ifrefutable$53))).foreach((x$53) -> {
                  $anonfun$apply$106(result, b, x$53);
                  return BoxedUnit.UNIT;
               });
            } else {
               a.activeIterator().withFilter((check$ifrefutable$54) -> BoxesRunTime.boxToBoolean($anonfun$apply$107(check$ifrefutable$54))).foreach((x$54) -> {
                  $anonfun$apply$108(result, b, x$54);
                  return BoxedUnit.UNIT;
               });
            }

            return result;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$105(final Tuple2 check$ifrefutable$53) {
            boolean var1;
            if (check$ifrefutable$53 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$106(final HashVector result$25, final int b$25, final Tuple2 x$53) {
            if (x$53 != null) {
               int k = x$53._1$mcI$sp();
               int v = x$53._2$mcI$sp();
               result$25.update$mcI$sp(k, v % b$25);
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$53);
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$107(final Tuple2 check$ifrefutable$54) {
            boolean var1;
            if (check$ifrefutable$54 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$108(final HashVector result$25, final int b$25, final Tuple2 x$54) {
            if (x$54 != null) {
               int k = x$54._1$mcI$sp();
               int v = x$54._2$mcI$sp();
               result$25.update$mcI$sp(k, v % b$25);
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$54);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_S_eq_V_Int_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Int());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Double_OpMod_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final double b) {
            HashVector result;
            label30: {
               label29: {
                  result = HashVector$.MODULE$.zeros$mDc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
                  OpMod$ var10000 = OpMod$.MODULE$;
                  OpMulScalar$ var5 = OpMulScalar$.MODULE$;
                  if (var10000 == null) {
                     if (var5 == null) {
                        break label29;
                     }
                  } else if (var10000.equals(var5)) {
                     break label29;
                  }

                  var10000 = OpMod$.MODULE$;
                  OpMulMatrix$ var6 = OpMulMatrix$.MODULE$;
                  if (var10000 == null) {
                     if (var6 != null) {
                        break label30;
                     }
                  } else if (!var10000.equals(var6)) {
                     break label30;
                  }
               }

               if (b == (double)0) {
                  return result;
               }
            }

            if (b == (double)0) {
               a.iterator().withFilter((check$ifrefutable$55) -> BoxesRunTime.boxToBoolean($anonfun$apply$109(check$ifrefutable$55))).foreach((x$55) -> {
                  $anonfun$apply$110(result, b, x$55);
                  return BoxedUnit.UNIT;
               });
            } else {
               a.activeIterator().withFilter((check$ifrefutable$56) -> BoxesRunTime.boxToBoolean($anonfun$apply$111(check$ifrefutable$56))).foreach((x$56) -> {
                  $anonfun$apply$112(result, b, x$56);
                  return BoxedUnit.UNIT;
               });
            }

            return result;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$109(final Tuple2 check$ifrefutable$55) {
            boolean var1;
            if (check$ifrefutable$55 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$110(final HashVector result$26, final double b$26, final Tuple2 x$55) {
            if (x$55 != null) {
               int k = x$55._1$mcI$sp();
               double v = x$55._2$mcD$sp();
               result$26.update$mcD$sp(k, v % b$26);
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$55);
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$111(final Tuple2 check$ifrefutable$56) {
            boolean var1;
            if (check$ifrefutable$56 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$112(final HashVector result$26, final double b$26, final Tuple2 x$56) {
            if (x$56 != null) {
               int k = x$56._1$mcI$sp();
               double v = x$56._2$mcD$sp();
               result$26.update$mcD$sp(k, v % b$26);
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$56);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_S_eq_V_Double_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Double());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Float_OpMod_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final float b) {
            HashVector result;
            label30: {
               label29: {
                  result = HashVector$.MODULE$.zeros$mFc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());
                  OpMod$ var10000 = OpMod$.MODULE$;
                  OpMulScalar$ var4 = OpMulScalar$.MODULE$;
                  if (var10000 == null) {
                     if (var4 == null) {
                        break label29;
                     }
                  } else if (var10000.equals(var4)) {
                     break label29;
                  }

                  var10000 = OpMod$.MODULE$;
                  OpMulMatrix$ var5 = OpMulMatrix$.MODULE$;
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label30;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label30;
                  }
               }

               if (b == (float)0) {
                  return result;
               }
            }

            if (b == (float)0) {
               a.iterator().withFilter((check$ifrefutable$57) -> BoxesRunTime.boxToBoolean($anonfun$apply$113(check$ifrefutable$57))).foreach((x$57) -> {
                  $anonfun$apply$114(result, b, x$57);
                  return BoxedUnit.UNIT;
               });
            } else {
               a.activeIterator().withFilter((check$ifrefutable$58) -> BoxesRunTime.boxToBoolean($anonfun$apply$115(check$ifrefutable$58))).foreach((x$58) -> {
                  $anonfun$apply$116(result, b, x$58);
                  return BoxedUnit.UNIT;
               });
            }

            return result;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$113(final Tuple2 check$ifrefutable$57) {
            boolean var1;
            if (check$ifrefutable$57 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$114(final HashVector result$27, final float b$27, final Tuple2 x$57) {
            if (x$57 != null) {
               int k = x$57._1$mcI$sp();
               float v = BoxesRunTime.unboxToFloat(x$57._2());
               result$27.update$mcF$sp(k, v % b$27);
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$57);
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$115(final Tuple2 check$ifrefutable$58) {
            boolean var1;
            if (check$ifrefutable$58 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$116(final HashVector result$27, final float b$27, final Tuple2 x$58) {
            if (x$58 != null) {
               int k = x$58._1$mcI$sp();
               float v = BoxesRunTime.unboxToFloat(x$58._2());
               result$27.update$mcF$sp(k, v % b$27);
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$58);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_S_eq_V_Float_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Float());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Long_OpMod_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final long b) {
            HashVector result;
            label30: {
               label29: {
                  result = HashVector$.MODULE$.zeros$mJc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());
                  OpMod$ var10000 = OpMod$.MODULE$;
                  OpMulScalar$ var5 = OpMulScalar$.MODULE$;
                  if (var10000 == null) {
                     if (var5 == null) {
                        break label29;
                     }
                  } else if (var10000.equals(var5)) {
                     break label29;
                  }

                  var10000 = OpMod$.MODULE$;
                  OpMulMatrix$ var6 = OpMulMatrix$.MODULE$;
                  if (var10000 == null) {
                     if (var6 != null) {
                        break label30;
                     }
                  } else if (!var10000.equals(var6)) {
                     break label30;
                  }
               }

               if (b == 0L) {
                  return result;
               }
            }

            if (b == 0L) {
               a.iterator().withFilter((check$ifrefutable$59) -> BoxesRunTime.boxToBoolean($anonfun$apply$117(check$ifrefutable$59))).foreach((x$59) -> {
                  $anonfun$apply$118(result, b, x$59);
                  return BoxedUnit.UNIT;
               });
            } else {
               a.activeIterator().withFilter((check$ifrefutable$60) -> BoxesRunTime.boxToBoolean($anonfun$apply$119(check$ifrefutable$60))).foreach((x$60) -> {
                  $anonfun$apply$120(result, b, x$60);
                  return BoxedUnit.UNIT;
               });
            }

            return result;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$117(final Tuple2 check$ifrefutable$59) {
            boolean var1;
            if (check$ifrefutable$59 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$118(final HashVector result$28, final long b$28, final Tuple2 x$59) {
            if (x$59 != null) {
               int k = x$59._1$mcI$sp();
               long v = x$59._2$mcJ$sp();
               result$28.update$mcJ$sp(k, v % b$28);
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$59);
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$119(final Tuple2 check$ifrefutable$60) {
            boolean var1;
            if (check$ifrefutable$60 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$120(final HashVector result$28, final long b$28, final Tuple2 x$60) {
            if (x$60 != null) {
               int k = x$60._1$mcI$sp();
               long v = x$60._2$mcJ$sp();
               result$28.update$mcJ$sp(k, v % b$28);
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$60);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_S_eq_V_Long_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Long());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Int_OpPow_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final int b) {
            HashVector result;
            label30: {
               label29: {
                  result = HashVector$.MODULE$.zeros$mIc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());
                  OpPow$ var10000 = OpPow$.MODULE$;
                  OpMulScalar$ var4 = OpMulScalar$.MODULE$;
                  if (var10000 == null) {
                     if (var4 == null) {
                        break label29;
                     }
                  } else if (var10000.equals(var4)) {
                     break label29;
                  }

                  var10000 = OpPow$.MODULE$;
                  OpMulMatrix$ var5 = OpMulMatrix$.MODULE$;
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label30;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label30;
                  }
               }

               if (b == 0) {
                  return result;
               }
            }

            if (b == 0) {
               a.iterator().withFilter((check$ifrefutable$61) -> BoxesRunTime.boxToBoolean($anonfun$apply$121(check$ifrefutable$61))).foreach((x$61) -> {
                  $anonfun$apply$122(result, b, x$61);
                  return BoxedUnit.UNIT;
               });
            } else {
               a.activeIterator().withFilter((check$ifrefutable$62) -> BoxesRunTime.boxToBoolean($anonfun$apply$123(check$ifrefutable$62))).foreach((x$62) -> {
                  $anonfun$apply$124(result, b, x$62);
                  return BoxedUnit.UNIT;
               });
            }

            return result;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$121(final Tuple2 check$ifrefutable$61) {
            boolean var1;
            if (check$ifrefutable$61 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$122(final HashVector result$29, final int b$29, final Tuple2 x$61) {
            if (x$61 != null) {
               int k = x$61._1$mcI$sp();
               int v = x$61._2$mcI$sp();
               result$29.update$mcI$sp(k, PowImplicits$.MODULE$.IntPow(v).pow(b$29));
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$61);
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$123(final Tuple2 check$ifrefutable$62) {
            boolean var1;
            if (check$ifrefutable$62 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$124(final HashVector result$29, final int b$29, final Tuple2 x$62) {
            if (x$62 != null) {
               int k = x$62._1$mcI$sp();
               int v = x$62._2$mcI$sp();
               result$29.update$mcI$sp(k, PowImplicits$.MODULE$.IntPow(v).pow(b$29));
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$62);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_S_eq_V_Int_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Int());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Double_OpPow_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final double b) {
            HashVector result;
            label30: {
               label29: {
                  result = HashVector$.MODULE$.zeros$mDc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
                  OpPow$ var10000 = OpPow$.MODULE$;
                  OpMulScalar$ var5 = OpMulScalar$.MODULE$;
                  if (var10000 == null) {
                     if (var5 == null) {
                        break label29;
                     }
                  } else if (var10000.equals(var5)) {
                     break label29;
                  }

                  var10000 = OpPow$.MODULE$;
                  OpMulMatrix$ var6 = OpMulMatrix$.MODULE$;
                  if (var10000 == null) {
                     if (var6 != null) {
                        break label30;
                     }
                  } else if (!var10000.equals(var6)) {
                     break label30;
                  }
               }

               if (b == (double)0) {
                  return result;
               }
            }

            if (b == (double)0) {
               a.iterator().withFilter((check$ifrefutable$63) -> BoxesRunTime.boxToBoolean($anonfun$apply$125(check$ifrefutable$63))).foreach((x$63) -> {
                  $anonfun$apply$126(result, b, x$63);
                  return BoxedUnit.UNIT;
               });
            } else {
               a.activeIterator().withFilter((check$ifrefutable$64) -> BoxesRunTime.boxToBoolean($anonfun$apply$127(check$ifrefutable$64))).foreach((x$64) -> {
                  $anonfun$apply$128(result, b, x$64);
                  return BoxedUnit.UNIT;
               });
            }

            return result;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$125(final Tuple2 check$ifrefutable$63) {
            boolean var1;
            if (check$ifrefutable$63 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$126(final HashVector result$30, final double b$30, final Tuple2 x$63) {
            if (x$63 != null) {
               int k = x$63._1$mcI$sp();
               double v = x$63._2$mcD$sp();
               result$30.update$mcD$sp(k, PowImplicits$.MODULE$.DoublePow(v).pow(b$30));
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$63);
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$127(final Tuple2 check$ifrefutable$64) {
            boolean var1;
            if (check$ifrefutable$64 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$128(final HashVector result$30, final double b$30, final Tuple2 x$64) {
            if (x$64 != null) {
               int k = x$64._1$mcI$sp();
               double v = x$64._2$mcD$sp();
               result$30.update$mcD$sp(k, PowImplicits$.MODULE$.DoublePow(v).pow(b$30));
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$64);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_S_eq_V_Double_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Double());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Float_OpPow_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final float b) {
            HashVector result;
            label30: {
               label29: {
                  result = HashVector$.MODULE$.zeros$mFc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());
                  OpPow$ var10000 = OpPow$.MODULE$;
                  OpMulScalar$ var4 = OpMulScalar$.MODULE$;
                  if (var10000 == null) {
                     if (var4 == null) {
                        break label29;
                     }
                  } else if (var10000.equals(var4)) {
                     break label29;
                  }

                  var10000 = OpPow$.MODULE$;
                  OpMulMatrix$ var5 = OpMulMatrix$.MODULE$;
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label30;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label30;
                  }
               }

               if (b == (float)0) {
                  return result;
               }
            }

            if (b == (float)0) {
               a.iterator().withFilter((check$ifrefutable$65) -> BoxesRunTime.boxToBoolean($anonfun$apply$129(check$ifrefutable$65))).foreach((x$65) -> {
                  $anonfun$apply$130(result, b, x$65);
                  return BoxedUnit.UNIT;
               });
            } else {
               a.activeIterator().withFilter((check$ifrefutable$66) -> BoxesRunTime.boxToBoolean($anonfun$apply$131(check$ifrefutable$66))).foreach((x$66) -> {
                  $anonfun$apply$132(result, b, x$66);
                  return BoxedUnit.UNIT;
               });
            }

            return result;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$129(final Tuple2 check$ifrefutable$65) {
            boolean var1;
            if (check$ifrefutable$65 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$130(final HashVector result$31, final float b$31, final Tuple2 x$65) {
            if (x$65 != null) {
               int k = x$65._1$mcI$sp();
               float v = BoxesRunTime.unboxToFloat(x$65._2());
               result$31.update$mcF$sp(k, PowImplicits$.MODULE$.FloatPow(v).pow(b$31));
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$65);
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$131(final Tuple2 check$ifrefutable$66) {
            boolean var1;
            if (check$ifrefutable$66 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$132(final HashVector result$31, final float b$31, final Tuple2 x$66) {
            if (x$66 != null) {
               int k = x$66._1$mcI$sp();
               float v = BoxesRunTime.unboxToFloat(x$66._2());
               result$31.update$mcF$sp(k, PowImplicits$.MODULE$.FloatPow(v).pow(b$31));
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$66);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_S_eq_V_Float_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Float());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_HV_S_eq_HV_zeroy_Long_OpPow_$eq(new UFunc.UImpl2() {
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

         public HashVector apply(final HashVector a, final long b) {
            HashVector result;
            label30: {
               label29: {
                  result = HashVector$.MODULE$.zeros$mJc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());
                  OpPow$ var10000 = OpPow$.MODULE$;
                  OpMulScalar$ var5 = OpMulScalar$.MODULE$;
                  if (var10000 == null) {
                     if (var5 == null) {
                        break label29;
                     }
                  } else if (var10000.equals(var5)) {
                     break label29;
                  }

                  var10000 = OpPow$.MODULE$;
                  OpMulMatrix$ var6 = OpMulMatrix$.MODULE$;
                  if (var10000 == null) {
                     if (var6 != null) {
                        break label30;
                     }
                  } else if (!var10000.equals(var6)) {
                     break label30;
                  }
               }

               if (b == 0L) {
                  return result;
               }
            }

            if (b == 0L) {
               a.iterator().withFilter((check$ifrefutable$67) -> BoxesRunTime.boxToBoolean($anonfun$apply$133(check$ifrefutable$67))).foreach((x$67) -> {
                  $anonfun$apply$134(result, b, x$67);
                  return BoxedUnit.UNIT;
               });
            } else {
               a.activeIterator().withFilter((check$ifrefutable$68) -> BoxesRunTime.boxToBoolean($anonfun$apply$135(check$ifrefutable$68))).foreach((x$68) -> {
                  $anonfun$apply$136(result, b, x$68);
                  return BoxedUnit.UNIT;
               });
            }

            return result;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$133(final Tuple2 check$ifrefutable$67) {
            boolean var1;
            if (check$ifrefutable$67 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$134(final HashVector result$32, final long b$32, final Tuple2 x$67) {
            if (x$67 != null) {
               int k = x$67._1$mcI$sp();
               long v = x$67._2$mcJ$sp();
               result$32.update$mcJ$sp(k, PowImplicits$.MODULE$.LongPow(v).pow(b$32));
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$67);
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$135(final Tuple2 check$ifrefutable$68) {
            boolean var1;
            if (check$ifrefutable$68 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$136(final HashVector result$32, final long b$32, final Tuple2 x$68) {
            if (x$68 != null) {
               int k = x$68._1$mcI$sp();
               long v = x$68._2$mcJ$sp();
               result$32.update$mcJ$sp(k, PowImplicits$.MODULE$.LongPow(v).pow(b$32));
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$68);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_V_S_eq_V_Long_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Long());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpSet_InPlace_HV_HV_Int_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               b.array$mcI$sp().copyTo$mcI$sp(a.array$mcI$sp());
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_InPlace_V_V_Int_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(HashVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpSet_InPlace_HV_HV_Double_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               b.array$mcD$sp().copyTo$mcD$sp(a.array$mcD$sp());
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_InPlace_V_V_Double_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(HashVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpSet_InPlace_HV_HV_Float_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               b.array$mcF$sp().copyTo$mcF$sp(a.array$mcF$sp());
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_InPlace_V_V_Float_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(HashVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpSet_InPlace_HV_HV_Long_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               b.array$mcJ$sp().copyTo$mcJ$sp(a.array$mcJ$sp());
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_InPlace_V_V_Long_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(HashVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_idempotent_Int_OpAdd_$eq(new UFunc$InPlaceImpl2$mcI$sp() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final int b) {
            this.apply$mcI$sp(a, b);
         }

         public void apply$mcI$sp(final HashVector a, final int b) {
            if (b != 0) {
               for(int i = 0; i < a.length(); ++i) {
                  a.update$mcI$sp(i, a.apply$mcI$sp(i) + b);
               }

            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_InPlace_V_S_Int_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Int());
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_idempotent_Double_OpAdd_$eq(new UFunc$InPlaceImpl2$mcD$sp() {
         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final double b) {
            this.apply$mcD$sp(a, b);
         }

         public void apply$mcD$sp(final HashVector a, final double b) {
            if (b != (double)0) {
               for(int i = 0; i < a.length(); ++i) {
                  a.update$mcD$sp(i, a.apply$mcD$sp(i) + b);
               }

            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_InPlace_V_S_Double_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Double());
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_idempotent_Float_OpAdd_$eq(new UFunc$InPlaceImpl2$mcF$sp() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final float b) {
            this.apply$mcF$sp(a, b);
         }

         public void apply$mcF$sp(final HashVector a, final float b) {
            if (b != (float)0) {
               for(int i = 0; i < a.length(); ++i) {
                  a.update$mcF$sp(i, a.apply$mcF$sp(i) + b);
               }

            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_InPlace_V_S_Float_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Float());
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_idempotent_Long_OpAdd_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final long b) {
            if (b != 0L) {
               for(int i = 0; i < a.length(); ++i) {
                  a.update$mcJ$sp(i, a.apply$mcJ$sp(i) + b);
               }

            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_InPlace_V_S_Long_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Long());
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_idempotent_Int_OpSub_$eq(new UFunc$InPlaceImpl2$mcI$sp() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final int b) {
            this.apply$mcI$sp(a, b);
         }

         public void apply$mcI$sp(final HashVector a, final int b) {
            if (b != 0) {
               for(int i = 0; i < a.length(); ++i) {
                  a.update$mcI$sp(i, a.apply$mcI$sp(i) - b);
               }

            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_InPlace_V_S_Int_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Int());
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_idempotent_Double_OpSub_$eq(new UFunc$InPlaceImpl2$mcD$sp() {
         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final double b) {
            this.apply$mcD$sp(a, b);
         }

         public void apply$mcD$sp(final HashVector a, final double b) {
            if (b != (double)0) {
               for(int i = 0; i < a.length(); ++i) {
                  a.update$mcD$sp(i, a.apply$mcD$sp(i) - b);
               }

            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_InPlace_V_S_Double_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Double());
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_idempotent_Float_OpSub_$eq(new UFunc$InPlaceImpl2$mcF$sp() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final float b) {
            this.apply$mcF$sp(a, b);
         }

         public void apply$mcF$sp(final HashVector a, final float b) {
            if (b != (float)0) {
               for(int i = 0; i < a.length(); ++i) {
                  a.update$mcF$sp(i, a.apply$mcF$sp(i) - b);
               }

            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_InPlace_V_S_Float_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Float());
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_idempotent_Long_OpSub_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final long b) {
            if (b != 0L) {
               for(int i = 0; i < a.length(); ++i) {
                  a.update$mcJ$sp(i, a.apply$mcJ$sp(i) - b);
               }

            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_InPlace_V_S_Long_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Long());
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpMulScalar_InPlace_HV_S_Int_$eq(new UFunc$InPlaceImpl2$mcI$sp() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final int b) {
            this.apply$mcI$sp(a, b);
         }

         public void apply$mcI$sp(final HashVector a, final int b) {
            if (b == 0) {
               a.clear();
            } else {
               a.activeIterator().withFilter((check$ifrefutable$69) -> BoxesRunTime.boxToBoolean($anonfun$apply$137(check$ifrefutable$69))).foreach((x$69) -> {
                  $anonfun$apply$138(a, b, x$69);
                  return BoxedUnit.UNIT;
               });
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$137(final Tuple2 check$ifrefutable$69) {
            boolean var1;
            if (check$ifrefutable$69 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$138(final HashVector a$5, final int b$33, final Tuple2 x$69) {
            if (x$69 != null) {
               int k = x$69._1$mcI$sp();
               int v = x$69._2$mcI$sp();
               a$5.update$mcI$sp(k, v * b$33);
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$69);
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_InPlace_V_S_Int_OpMulScalar())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Int());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpMulScalar_InPlace_HV_S_Double_$eq(new UFunc$InPlaceImpl2$mcD$sp() {
         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final double b) {
            this.apply$mcD$sp(a, b);
         }

         public void apply$mcD$sp(final HashVector a, final double b) {
            if (b == (double)0) {
               a.clear();
            } else {
               a.activeIterator().withFilter((check$ifrefutable$70) -> BoxesRunTime.boxToBoolean($anonfun$apply$139(check$ifrefutable$70))).foreach((x$70) -> {
                  $anonfun$apply$140(a, b, x$70);
                  return BoxedUnit.UNIT;
               });
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$139(final Tuple2 check$ifrefutable$70) {
            boolean var1;
            if (check$ifrefutable$70 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$140(final HashVector a$6, final double b$34, final Tuple2 x$70) {
            if (x$70 != null) {
               int k = x$70._1$mcI$sp();
               double v = x$70._2$mcD$sp();
               a$6.update$mcD$sp(k, v * b$34);
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$70);
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_InPlace_V_S_Double_OpMulScalar())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Double());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpMulScalar_InPlace_HV_S_Float_$eq(new UFunc$InPlaceImpl2$mcF$sp() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final float b) {
            this.apply$mcF$sp(a, b);
         }

         public void apply$mcF$sp(final HashVector a, final float b) {
            if (b == (float)0) {
               a.clear();
            } else {
               a.activeIterator().withFilter((check$ifrefutable$71) -> BoxesRunTime.boxToBoolean($anonfun$apply$141(check$ifrefutable$71))).foreach((x$71) -> {
                  $anonfun$apply$142(a, b, x$71);
                  return BoxedUnit.UNIT;
               });
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$141(final Tuple2 check$ifrefutable$71) {
            boolean var1;
            if (check$ifrefutable$71 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$142(final HashVector a$7, final float b$35, final Tuple2 x$71) {
            if (x$71 != null) {
               int k = x$71._1$mcI$sp();
               float v = BoxesRunTime.unboxToFloat(x$71._2());
               a$7.update$mcF$sp(k, v * b$35);
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$71);
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_InPlace_V_S_Float_OpMulScalar())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Float());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpMulScalar_InPlace_HV_S_Long_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final long b) {
            if (b == 0L) {
               a.clear();
            } else {
               a.activeIterator().withFilter((check$ifrefutable$72) -> BoxesRunTime.boxToBoolean($anonfun$apply$143(check$ifrefutable$72))).foreach((x$72) -> {
                  $anonfun$apply$144(a, b, x$72);
                  return BoxedUnit.UNIT;
               });
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$143(final Tuple2 check$ifrefutable$72) {
            boolean var1;
            if (check$ifrefutable$72 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$144(final HashVector a$8, final long b$36, final Tuple2 x$72) {
            if (x$72 != null) {
               int k = x$72._1$mcI$sp();
               long v = x$72._2$mcJ$sp();
               a$8.update$mcJ$sp(k, v * b$36);
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$72);
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_InPlace_V_S_Long_OpMulScalar())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Long());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpSet_InPlace_HV_S_Int_$eq(new UFunc$InPlaceImpl2$mcI$sp() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final int b) {
            this.apply$mcI$sp(a, b);
         }

         public void apply$mcI$sp(final HashVector a, final int b) {
            if (b == 0) {
               a.clear();
            } else {
               for(int i = 0; i < a.length(); ++i) {
                  a.update$mcI$sp(i, b);
               }

            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_InPlace_V_S_Int_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Int());
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpSet_InPlace_HV_S_Double_$eq(new UFunc$InPlaceImpl2$mcD$sp() {
         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final double b) {
            this.apply$mcD$sp(a, b);
         }

         public void apply$mcD$sp(final HashVector a, final double b) {
            if (b == (double)0) {
               a.clear();
            } else {
               for(int i = 0; i < a.length(); ++i) {
                  a.update$mcD$sp(i, b);
               }

            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_InPlace_V_S_Double_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Double());
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpSet_InPlace_HV_S_Float_$eq(new UFunc$InPlaceImpl2$mcF$sp() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final float b) {
            this.apply$mcF$sp(a, b);
         }

         public void apply$mcF$sp(final HashVector a, final float b) {
            if (b == (float)0) {
               a.clear();
            } else {
               for(int i = 0; i < a.length(); ++i) {
                  a.update$mcF$sp(i, b);
               }

            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_InPlace_V_S_Float_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Float());
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpSet_InPlace_HV_S_Long_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final long b) {
            if (b == 0L) {
               a.clear();
            } else {
               for(int i = 0; i < a.length(); ++i) {
                  a.update$mcJ$sp(i, b);
               }

            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_InPlace_V_S_Long_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Long());
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_LHS_nilpotent_Int_OpDiv_$eq(new UFunc$InPlaceImpl2$mcI$sp() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final int b) {
            this.apply$mcI$sp(a, b);
         }

         public void apply$mcI$sp(final HashVector a, final int b) {
            if (b == 0) {
               for(int i = 0; i < a.length(); ++i) {
                  a.update$mcI$sp(i, a.apply$mcI$sp(i) / b);
               }
            } else {
               a.activeIterator().withFilter((check$ifrefutable$73) -> BoxesRunTime.boxToBoolean($anonfun$apply$145(check$ifrefutable$73))).foreach((x$73) -> {
                  $anonfun$apply$146(a, b, x$73);
                  return BoxedUnit.UNIT;
               });
            }

         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$145(final Tuple2 check$ifrefutable$73) {
            boolean var1;
            if (check$ifrefutable$73 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$146(final HashVector a$9, final int b$37, final Tuple2 x$73) {
            if (x$73 != null) {
               int k = x$73._1$mcI$sp();
               int v = x$73._2$mcI$sp();
               a$9.update$mcI$sp(k, v / b$37);
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$73);
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_InPlace_V_S_Int_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Int());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_LHS_nilpotent_Double_OpDiv_$eq(new UFunc$InPlaceImpl2$mcD$sp() {
         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final double b) {
            this.apply$mcD$sp(a, b);
         }

         public void apply$mcD$sp(final HashVector a, final double b) {
            if (b == (double)0) {
               for(int i = 0; i < a.length(); ++i) {
                  a.update$mcD$sp(i, a.apply$mcD$sp(i) / b);
               }
            } else {
               a.activeIterator().withFilter((check$ifrefutable$74) -> BoxesRunTime.boxToBoolean($anonfun$apply$147(check$ifrefutable$74))).foreach((x$74) -> {
                  $anonfun$apply$148(a, b, x$74);
                  return BoxedUnit.UNIT;
               });
            }

         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$147(final Tuple2 check$ifrefutable$74) {
            boolean var1;
            if (check$ifrefutable$74 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$148(final HashVector a$10, final double b$38, final Tuple2 x$74) {
            if (x$74 != null) {
               int k = x$74._1$mcI$sp();
               double v = x$74._2$mcD$sp();
               a$10.update$mcD$sp(k, v / b$38);
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$74);
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_InPlace_V_S_Double_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Double());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_LHS_nilpotent_Float_OpDiv_$eq(new UFunc$InPlaceImpl2$mcF$sp() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final float b) {
            this.apply$mcF$sp(a, b);
         }

         public void apply$mcF$sp(final HashVector a, final float b) {
            if (b == (float)0) {
               for(int i = 0; i < a.length(); ++i) {
                  a.update$mcF$sp(i, a.apply$mcF$sp(i) / b);
               }
            } else {
               a.activeIterator().withFilter((check$ifrefutable$75) -> BoxesRunTime.boxToBoolean($anonfun$apply$149(check$ifrefutable$75))).foreach((x$75) -> {
                  $anonfun$apply$150(a, b, x$75);
                  return BoxedUnit.UNIT;
               });
            }

         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$149(final Tuple2 check$ifrefutable$75) {
            boolean var1;
            if (check$ifrefutable$75 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$150(final HashVector a$11, final float b$39, final Tuple2 x$75) {
            if (x$75 != null) {
               int k = x$75._1$mcI$sp();
               float v = BoxesRunTime.unboxToFloat(x$75._2());
               a$11.update$mcF$sp(k, v / b$39);
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$75);
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_InPlace_V_S_Float_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Float());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_LHS_nilpotent_Long_OpDiv_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final long b) {
            if (b == 0L) {
               for(int i = 0; i < a.length(); ++i) {
                  a.update$mcJ$sp(i, a.apply$mcJ$sp(i) / b);
               }
            } else {
               a.activeIterator().withFilter((check$ifrefutable$76) -> BoxesRunTime.boxToBoolean($anonfun$apply$151(check$ifrefutable$76))).foreach((x$76) -> {
                  $anonfun$apply$152(a, b, x$76);
                  return BoxedUnit.UNIT;
               });
            }

         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$151(final Tuple2 check$ifrefutable$76) {
            boolean var1;
            if (check$ifrefutable$76 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$152(final HashVector a$12, final long b$40, final Tuple2 x$76) {
            if (x$76 != null) {
               int k = x$76._1$mcI$sp();
               long v = x$76._2$mcJ$sp();
               a$12.update$mcJ$sp(k, v / b$40);
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$76);
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_InPlace_V_S_Long_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Long());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_LHS_nilpotent_Int_OpMod_$eq(new UFunc$InPlaceImpl2$mcI$sp() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final int b) {
            this.apply$mcI$sp(a, b);
         }

         public void apply$mcI$sp(final HashVector a, final int b) {
            if (b == 0) {
               for(int i = 0; i < a.length(); ++i) {
                  a.update$mcI$sp(i, a.apply$mcI$sp(i) % b);
               }
            } else {
               a.activeIterator().withFilter((check$ifrefutable$77) -> BoxesRunTime.boxToBoolean($anonfun$apply$153(check$ifrefutable$77))).foreach((x$77) -> {
                  $anonfun$apply$154(a, b, x$77);
                  return BoxedUnit.UNIT;
               });
            }

         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$153(final Tuple2 check$ifrefutable$77) {
            boolean var1;
            if (check$ifrefutable$77 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$154(final HashVector a$13, final int b$41, final Tuple2 x$77) {
            if (x$77 != null) {
               int k = x$77._1$mcI$sp();
               int v = x$77._2$mcI$sp();
               a$13.update$mcI$sp(k, v % b$41);
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$77);
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_InPlace_V_S_Int_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Int());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_LHS_nilpotent_Double_OpMod_$eq(new UFunc$InPlaceImpl2$mcD$sp() {
         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final double b) {
            this.apply$mcD$sp(a, b);
         }

         public void apply$mcD$sp(final HashVector a, final double b) {
            if (b == (double)0) {
               for(int i = 0; i < a.length(); ++i) {
                  a.update$mcD$sp(i, a.apply$mcD$sp(i) % b);
               }
            } else {
               a.activeIterator().withFilter((check$ifrefutable$78) -> BoxesRunTime.boxToBoolean($anonfun$apply$155(check$ifrefutable$78))).foreach((x$78) -> {
                  $anonfun$apply$156(a, b, x$78);
                  return BoxedUnit.UNIT;
               });
            }

         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$155(final Tuple2 check$ifrefutable$78) {
            boolean var1;
            if (check$ifrefutable$78 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$156(final HashVector a$14, final double b$42, final Tuple2 x$78) {
            if (x$78 != null) {
               int k = x$78._1$mcI$sp();
               double v = x$78._2$mcD$sp();
               a$14.update$mcD$sp(k, v % b$42);
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$78);
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_InPlace_V_S_Double_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Double());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_LHS_nilpotent_Float_OpMod_$eq(new UFunc$InPlaceImpl2$mcF$sp() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final float b) {
            this.apply$mcF$sp(a, b);
         }

         public void apply$mcF$sp(final HashVector a, final float b) {
            if (b == (float)0) {
               for(int i = 0; i < a.length(); ++i) {
                  a.update$mcF$sp(i, a.apply$mcF$sp(i) % b);
               }
            } else {
               a.activeIterator().withFilter((check$ifrefutable$79) -> BoxesRunTime.boxToBoolean($anonfun$apply$157(check$ifrefutable$79))).foreach((x$79) -> {
                  $anonfun$apply$158(a, b, x$79);
                  return BoxedUnit.UNIT;
               });
            }

         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$157(final Tuple2 check$ifrefutable$79) {
            boolean var1;
            if (check$ifrefutable$79 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$158(final HashVector a$15, final float b$43, final Tuple2 x$79) {
            if (x$79 != null) {
               int k = x$79._1$mcI$sp();
               float v = BoxesRunTime.unboxToFloat(x$79._2());
               a$15.update$mcF$sp(k, v % b$43);
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$79);
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_InPlace_V_S_Float_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Float());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_LHS_nilpotent_Long_OpMod_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final long b) {
            if (b == 0L) {
               for(int i = 0; i < a.length(); ++i) {
                  a.update$mcJ$sp(i, a.apply$mcJ$sp(i) % b);
               }
            } else {
               a.activeIterator().withFilter((check$ifrefutable$80) -> BoxesRunTime.boxToBoolean($anonfun$apply$159(check$ifrefutable$80))).foreach((x$80) -> {
                  $anonfun$apply$160(a, b, x$80);
                  return BoxedUnit.UNIT;
               });
            }

         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$159(final Tuple2 check$ifrefutable$80) {
            boolean var1;
            if (check$ifrefutable$80 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$160(final HashVector a$16, final long b$44, final Tuple2 x$80) {
            if (x$80 != null) {
               int k = x$80._1$mcI$sp();
               long v = x$80._2$mcJ$sp();
               a$16.update$mcJ$sp(k, v % b$44);
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$80);
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_InPlace_V_S_Long_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Long());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_LHS_nilpotent_Int_OpPow_$eq(new UFunc$InPlaceImpl2$mcI$sp() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final int b) {
            this.apply$mcI$sp(a, b);
         }

         public void apply$mcI$sp(final HashVector a, final int b) {
            if (b == 0) {
               for(int i = 0; i < a.length(); ++i) {
                  a.update$mcI$sp(i, PowImplicits$.MODULE$.IntPow(a.apply$mcI$sp(i)).pow(b));
               }
            } else {
               a.activeIterator().withFilter((check$ifrefutable$81) -> BoxesRunTime.boxToBoolean($anonfun$apply$161(check$ifrefutable$81))).foreach((x$81) -> {
                  $anonfun$apply$162(a, b, x$81);
                  return BoxedUnit.UNIT;
               });
            }

         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$161(final Tuple2 check$ifrefutable$81) {
            boolean var1;
            if (check$ifrefutable$81 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$162(final HashVector a$17, final int b$45, final Tuple2 x$81) {
            if (x$81 != null) {
               int k = x$81._1$mcI$sp();
               int v = x$81._2$mcI$sp();
               a$17.update$mcI$sp(k, PowImplicits$.MODULE$.IntPow(v).pow(b$45));
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$81);
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_InPlace_V_S_Int_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Int());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_LHS_nilpotent_Double_OpPow_$eq(new UFunc$InPlaceImpl2$mcD$sp() {
         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final double b) {
            this.apply$mcD$sp(a, b);
         }

         public void apply$mcD$sp(final HashVector a, final double b) {
            if (b == (double)0) {
               for(int i = 0; i < a.length(); ++i) {
                  a.update$mcD$sp(i, PowImplicits$.MODULE$.DoublePow(a.apply$mcD$sp(i)).pow(b));
               }
            } else {
               a.activeIterator().withFilter((check$ifrefutable$82) -> BoxesRunTime.boxToBoolean($anonfun$apply$163(check$ifrefutable$82))).foreach((x$82) -> {
                  $anonfun$apply$164(a, b, x$82);
                  return BoxedUnit.UNIT;
               });
            }

         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$163(final Tuple2 check$ifrefutable$82) {
            boolean var1;
            if (check$ifrefutable$82 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$164(final HashVector a$18, final double b$46, final Tuple2 x$82) {
            if (x$82 != null) {
               int k = x$82._1$mcI$sp();
               double v = x$82._2$mcD$sp();
               a$18.update$mcD$sp(k, PowImplicits$.MODULE$.DoublePow(v).pow(b$46));
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$82);
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_InPlace_V_S_Double_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Double());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_LHS_nilpotent_Float_OpPow_$eq(new UFunc$InPlaceImpl2$mcF$sp() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final float b) {
            this.apply$mcF$sp(a, b);
         }

         public void apply$mcF$sp(final HashVector a, final float b) {
            if (b == (float)0) {
               for(int i = 0; i < a.length(); ++i) {
                  a.update$mcF$sp(i, PowImplicits$.MODULE$.FloatPow(a.apply$mcF$sp(i)).pow(b));
               }
            } else {
               a.activeIterator().withFilter((check$ifrefutable$83) -> BoxesRunTime.boxToBoolean($anonfun$apply$165(check$ifrefutable$83))).foreach((x$83) -> {
                  $anonfun$apply$166(a, b, x$83);
                  return BoxedUnit.UNIT;
               });
            }

         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$165(final Tuple2 check$ifrefutable$83) {
            boolean var1;
            if (check$ifrefutable$83 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$166(final HashVector a$19, final float b$47, final Tuple2 x$83) {
            if (x$83 != null) {
               int k = x$83._1$mcI$sp();
               float v = BoxesRunTime.unboxToFloat(x$83._2());
               a$19.update$mcF$sp(k, PowImplicits$.MODULE$.FloatPow(v).pow(b$47));
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$83);
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_InPlace_V_S_Float_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Float());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_Op_InPlace_HV_S_LHS_nilpotent_Long_OpPow_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final long b) {
            if (b == 0L) {
               for(int i = 0; i < a.length(); ++i) {
                  a.update$mcJ$sp(i, PowImplicits$.MODULE$.LongPow(a.apply$mcJ$sp(i)).pow(b));
               }
            } else {
               a.activeIterator().withFilter((check$ifrefutable$84) -> BoxesRunTime.boxToBoolean($anonfun$apply$167(check$ifrefutable$84))).foreach((x$84) -> {
                  $anonfun$apply$168(a, b, x$84);
                  return BoxedUnit.UNIT;
               });
            }

         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$167(final Tuple2 check$ifrefutable$84) {
            boolean var1;
            if (check$ifrefutable$84 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$168(final HashVector a$20, final long b$48, final Tuple2 x$84) {
            if (x$84 != null) {
               int k = x$84._1$mcI$sp();
               long v = x$84._2$mcJ$sp();
               a$20.update$mcJ$sp(k, PowImplicits$.MODULE$.LongPow(v).pow(b$48));
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$84);
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_Op_InPlace_V_S_Long_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.Long());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpMulInner_HV_HV_eq_S_Int_$eq(new UFunc.UImpl2() {
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

         public int apply(final HashVector a, final HashVector b) {
            while(true) {
               int left$macro$1 = b.length();
               int right$macro$2 = a.length();
               if (left$macro$1 != right$macro$2) {
                  throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
               }

               if (a.iterableSize() <= b.iterableSize()) {
                  IntRef result = IntRef.create(0);
                  a.activeIterator().withFilter((check$ifrefutable$85) -> BoxesRunTime.boxToBoolean($anonfun$apply$169(check$ifrefutable$85))).foreach((x$85) -> {
                     $anonfun$apply$170(result, b, x$85);
                     return BoxedUnit.UNIT;
                  });
                  return result.elem;
               }

               HashVector var10000 = b;
               b = a;
               a = var10000;
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$169(final Tuple2 check$ifrefutable$85) {
            boolean var1;
            if (check$ifrefutable$85 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$170(final IntRef result$33, final HashVector b$49, final Tuple2 x$85) {
            if (x$85 != null) {
               int k = x$85._1$mcI$sp();
               int v = x$85._2$mcI$sp();
               result$33.elem += v * b$49.apply$mcI$sp(k);
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$85);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_OpMulInner_V_V_eq_S_Int())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(HashVector.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpMulInner_HV_HV_eq_S_Long_$eq(new UFunc.UImpl2() {
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

         public long apply(final HashVector a, final HashVector b) {
            while(true) {
               int left$macro$1 = b.length();
               int right$macro$2 = a.length();
               if (left$macro$1 != right$macro$2) {
                  throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
               }

               if (a.iterableSize() <= b.iterableSize()) {
                  LongRef result = LongRef.create(0L);
                  a.activeIterator().withFilter((check$ifrefutable$86) -> BoxesRunTime.boxToBoolean($anonfun$apply$171(check$ifrefutable$86))).foreach((x$86) -> {
                     $anonfun$apply$172(result, b, x$86);
                     return BoxedUnit.UNIT;
                  });
                  return result.elem;
               }

               HashVector var10000 = b;
               b = a;
               a = var10000;
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$171(final Tuple2 check$ifrefutable$86) {
            boolean var1;
            if (check$ifrefutable$86 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$172(final LongRef result$34, final HashVector b$50, final Tuple2 x$86) {
            if (x$86 != null) {
               int k = x$86._1$mcI$sp();
               long v = x$86._2$mcJ$sp();
               result$34.elem += v * b$50.apply$mcJ$sp(k);
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$86);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_OpMulInner_V_V_eq_S_Long())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(HashVector.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpMulInner_HV_HV_eq_S_Double_$eq(new UFunc.UImpl2() {
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

         public double apply(final HashVector a, final HashVector b) {
            while(true) {
               int left$macro$1 = b.length();
               int right$macro$2 = a.length();
               if (left$macro$1 != right$macro$2) {
                  throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
               }

               if (a.iterableSize() <= b.iterableSize()) {
                  DoubleRef result = DoubleRef.create((double)0.0F);
                  a.activeIterator().withFilter((check$ifrefutable$87) -> BoxesRunTime.boxToBoolean($anonfun$apply$173(check$ifrefutable$87))).foreach((x$87) -> {
                     $anonfun$apply$174(result, b, x$87);
                     return BoxedUnit.UNIT;
                  });
                  return result.elem;
               }

               HashVector var10000 = b;
               b = a;
               a = var10000;
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$173(final Tuple2 check$ifrefutable$87) {
            boolean var1;
            if (check$ifrefutable$87 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$174(final DoubleRef result$35, final HashVector b$51, final Tuple2 x$87) {
            if (x$87 != null) {
               int k = x$87._1$mcI$sp();
               double v = x$87._2$mcD$sp();
               result$35.elem += v * b$51.apply$mcD$sp(k);
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$87);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_OpMulInner_V_V_eq_S_Double())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(HashVector.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_OpMulInner_HV_HV_eq_S_Float_$eq(new UFunc.UImpl2() {
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

         public float apply(final HashVector a, final HashVector b) {
            while(true) {
               int left$macro$1 = b.length();
               int right$macro$2 = a.length();
               if (left$macro$1 != right$macro$2) {
                  throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
               }

               if (a.iterableSize() <= b.iterableSize()) {
                  FloatRef result = FloatRef.create(0.0F);
                  a.activeIterator().withFilter((check$ifrefutable$88) -> BoxesRunTime.boxToBoolean($anonfun$apply$175(check$ifrefutable$88))).foreach((x$88) -> {
                     $anonfun$apply$176(result, b, x$88);
                     return BoxedUnit.UNIT;
                  });
                  return result.elem;
               }

               HashVector var10000 = b;
               b = a;
               a = var10000;
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$175(final Tuple2 check$ifrefutable$88) {
            boolean var1;
            if (check$ifrefutable$88 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$176(final FloatRef result$36, final HashVector b$52, final Tuple2 x$88) {
            if (x$88 != null) {
               int k = x$88._1$mcI$sp();
               float v = BoxesRunTime.unboxToFloat(x$88._2());
               result$36.elem += v * b$52.apply$mcF$sp(k);
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$88);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HashVectorExpandOps.this.impl_OpMulInner_V_V_eq_S_Float())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(HashVector.class));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_CanTraverseValues_HV_Int_$eq(new CanTraverseValues() {
         public Object foldLeft(final Object from, final Object b, final Function2 fn) {
            return CanTraverseValues.foldLeft$(this, from, b, fn);
         }

         public CanTraverseValues.ValuesVisitor traverse(final HashVector from, final CanTraverseValues.ValuesVisitor fn) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = from.iterableSize(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               if (from.isActive(index$macro$2)) {
                  fn.visit$mcI$sp(from.data$mcI$sp()[index$macro$2]);
               }
            }

            fn.zeros$mcI$sp(from.size() - from.activeSize(), 0);
            return fn;
         }

         public boolean isTraversableAgain(final HashVector from) {
            return true;
         }

         public {
            CanTraverseValues.$init$(this);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_CanTraverseValues_HV_Double_$eq(new CanTraverseValues() {
         public Object foldLeft(final Object from, final Object b, final Function2 fn) {
            return CanTraverseValues.foldLeft$(this, from, b, fn);
         }

         public CanTraverseValues.ValuesVisitor traverse(final HashVector from, final CanTraverseValues.ValuesVisitor fn) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = from.iterableSize(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               if (from.isActive(index$macro$2)) {
                  fn.visit$mcD$sp(from.data$mcD$sp()[index$macro$2]);
               }
            }

            fn.zeros$mcD$sp(from.size() - from.activeSize(), (double)0.0F);
            return fn;
         }

         public boolean isTraversableAgain(final HashVector from) {
            return true;
         }

         public {
            CanTraverseValues.$init$(this);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_CanTraverseValues_HV_Float_$eq(new CanTraverseValues() {
         public Object foldLeft(final Object from, final Object b, final Function2 fn) {
            return CanTraverseValues.foldLeft$(this, from, b, fn);
         }

         public CanTraverseValues.ValuesVisitor traverse(final HashVector from, final CanTraverseValues.ValuesVisitor fn) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = from.iterableSize(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               if (from.isActive(index$macro$2)) {
                  fn.visit$mcF$sp(from.data$mcF$sp()[index$macro$2]);
               }
            }

            fn.zeros$mcF$sp(from.size() - from.activeSize(), 0.0F);
            return fn;
         }

         public boolean isTraversableAgain(final HashVector from) {
            return true;
         }

         public {
            CanTraverseValues.$init$(this);
         }
      });
      $this.breeze$linalg$operators$HashVectorExpandOps$_setter_$impl_CanTraverseValues_HV_Long_$eq(new CanTraverseValues() {
         public Object foldLeft(final Object from, final Object b, final Function2 fn) {
            return CanTraverseValues.foldLeft$(this, from, b, fn);
         }

         public CanTraverseValues.ValuesVisitor traverse(final HashVector from, final CanTraverseValues.ValuesVisitor fn) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = from.iterableSize(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               if (from.isActive(index$macro$2)) {
                  fn.visit$mcJ$sp(from.data$mcJ$sp()[index$macro$2]);
               }
            }

            fn.zeros$mcJ$sp(from.size() - from.activeSize(), 0L);
            return fn;
         }

         public boolean isTraversableAgain(final HashVector from) {
            return true;
         }

         public {
            CanTraverseValues.$init$(this);
         }
      });
   }
}
