package spire.math;

import algebra.ring.CommutativeRig;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.math.ScalaNumber;
import scala.math.ScalaNumericAnyConversions;
import scala.math.ScalaNumericConversions;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction1;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u00115g\u0001CA\u0019\u0003g\t\t#!\u0010\t\u000f\u0005-\u0004\u0001\"\u0001\u0002n!9\u00111\u000f\u0001\u0007\u0002\u0005U\u0004bBA?\u0001\u0011\u0005\u0011q\u0010\u0005\b\u0003g\u0003A\u0011AA[\u0011\u001d\t9\r\u0001C\u0001\u0003\u0013Dq!!5\u0001\t\u0003\tI\rC\u0004\u0002T\u0002!\t!!6\t\u000f\u0005u\u0007\u0001\"\u0001\u0002`\"9\u0011q\u001d\u0001\u0005\u0002\u0005%\bbBAv\u0001\u0011\u0005\u0011\u0011\u001e\u0005\b\u0003[\u0004A\u0011AAx\u0011\u001d\t9\u0010\u0001C\u0001\u0003sDqAa\u0003\u0001\t\u0003\tI\rC\u0004\u0003\u000e\u0001!\tAa\u0004\t\u000f\t]\u0001\u0001\"\u0001\u0003\u001a!9!\u0011\u0005\u0001\u0005\u0002\t\r\u0002b\u0002B\u0016\u0001\u0011\u0005\u0013\u0011\u001a\u0005\b\u0005[\u0001A\u0011\tB\b\u0011\u001d\u0011y\u0003\u0001C\u0001\u0005cAqA!\u000f\u0001\t\u0003\u0012Y\u0004C\u0004\u0003N\u0001!\tAa\u0014\t\u000f\tE\u0003\u0001\"\u0001\u0002p\"9!1\u000b\u0001\u0005\u0002\u0005=\bb\u0002B+\u0001\u0011\u0005\u0011q\u001e\u0005\b\u0005/\u0002A\u0011AAx\u0011\u001d\u0011I\u0006\u0001C\u0001\u0003\u0013DqAa\u0017\u0001\t\u0003\u0011i\u0006C\u0004\u0003\\\u0001!\tAa\u0019\t\u000f\t\u001d\u0004\u0001\"\u0012\u0003j!9!Q\u000e\u0001\u0005\u0002\t=\u0004b\u0002B:\u0001\u0011\u0005!Q\u000f\u0005\b\u0005s\u0002A\u0011\u0001B>\u0011\u001d\u0011y\b\u0001C\u0001\u0005\u0003CqA!\"\u0001\t\u0003\u00119\tC\u0004\u0003\f\u0002!\tA!$\t\u000f\te\u0004\u0001\"\u0001\u0003\u0012\"9!q\u0010\u0001\u0005\u0002\t]\u0005b\u0002BC\u0001\u0011\u0005!1\u0014\u0005\b\u0005\u0017\u0003A\u0011\u0001BP\u0011\u001d\u0011I\b\u0001C\u0001\u0005GCqAa \u0001\t\u0003\u00119\u000bC\u0004\u0003\u0006\u0002!\tAa+\t\u000f\t-\u0005\u0001\"\u0001\u00030\"9!1\u0017\u0001\u0007\u0002\tU\u0006b\u0002B^\u0001\u0019\u0005!Q\u0018\u0005\b\u0005\u0003\u0004a\u0011\u0001Bb\u0011\u001d\u00119\r\u0001C\u0001\u0005\u0013DqA!4\u0001\r\u0003\u0011y\rC\u0004\u0003T\u00021\tA!6\t\u000f\te\u0007A\"\u0001\u0003\\\"9!1\u0017\u0001\u0005\u0002\t\u0015\bb\u0002B^\u0001\u0011\u0005!\u0011\u001e\u0005\b\u0005\u0003\u0004A\u0011\u0001Bw\u0011\u001d\u00119\r\u0001C\u0001\u0005cDqA!4\u0001\t\u0003\u0011)\u0010C\u0004\u0003T\u0002!\tA!?\t\u000f\te\u0007\u0001\"\u0001\u0003~\"9!1\u0017\u0001\u0005\u0002\r\r\u0001b\u0002B^\u0001\u0011\u00051q\u0001\u0005\b\u0005\u0003\u0004A\u0011AB\u0006\u0011\u001d\u0019y\u0001\u0001C\u0001\u0007#Aqaa\u0004\u0001\t\u0003\u0019)\u0002C\u0004\u0003H\u0002!\ta!\u0007\t\u000f\t5\u0007\u0001\"\u0001\u0004\u001e!9!1\u001b\u0001\u0005\u0002\r\u0005\u0002b\u0002Bm\u0001\u0011\u00051Q\u0005\u0005\b\u0007S\u0001A\u0011BB\u0016\u0011\u001d\u0019)\u0004\u0001C\u0001\u0007oAqa!\u0010\u0001\t\u0003\u0019y\u0004C\u0004\u0004D\u0001!\ta!\u0012\t\u000f\r%\u0003\u0001\"\u0001\u0004L!91\u0011\n\u0001\u0005\u0002\r=\u0003bBB*\u0001\u0011\u00051Q\u000b\u0005\b\u0007'\u0002A\u0011AB-\u0011\u001d\u0019i\u0006\u0001C\u0001\u0007?Bqa!\u0018\u0001\t\u0003\u0019\u0019g\u0002\u0005\u0005L\u0006M\u0002\u0012ABC\r!\t\t$a\r\t\u0002\r-\u0004bBA6\u001d\u0012\u000511\u0011\u0005\f\u0007gq%\u0019!C\u0003\u0003g\t)\b\u0003\u0005\u0004\b:\u0003\u000bQBA<\u0011\u001d\u0019II\u0014C\u0002\u0007\u0017Cqaa$O\t\u0003\u0019\t\nC\u0004\u0004\u0010:#\ta!(\t\u000f\r=e\n\"\u0001\u0004\"\"I1Q\u0015(C\u0002\u0013%\u0011\u0011\u001e\u0005\t\u0007Os\u0005\u0015!\u0003\u0002p!91q\u0012(\u0005\u0002\r%\u0006\"CBX\u001d\n\u0007I\u0011AAu\u0011!\u0019\tL\u0014Q\u0001\n\u0005=\u0004\"CBZ\u001d\n\u0007I\u0011AAu\u0011!\u0019)L\u0014Q\u0001\n\u0005=dABB5\u001d\u0002#Y\t\u0003\u0006\u0004Zv\u0013)\u001a!C\u0001\u0003kB!ba?^\u0005#\u0005\u000b\u0011BA<\u0011)\u0019i.\u0018BK\u0002\u0013\u0005\u0011\u0011\u001e\u0005\u000b\t\u001bk&\u0011#Q\u0001\n\u0005=\u0004bBA6;\u0012\u0005Aq\u0012\u0005\b\u0003gjF\u0011AA;\u0011\u001d!)*\u0018C\u0001\u0003SDqAa-^\t\u0003!9\nC\u0004\u0003<v#\t\u0001b'\t\u000f\t\u0005W\f\"\u0001\u0005 \"9!QZ/\u0005\u0002\u0011\r\u0006b\u0002Bj;\u0012\u0005Aq\u0015\u0005\b\u00053lF\u0011\u0001CV\u0011%!Y\"XA\u0001\n\u0003!y\u000bC\u0005\u0005\"u\u000b\n\u0011\"\u0001\u0005$!IAQW/\u0012\u0002\u0013\u0005Aq\u0017\u0005\n\tsi\u0016\u0011!C!\twA\u0011\u0002\"\u0010^\u0003\u0003%\t!!3\t\u0013\u0011}R,!A\u0005\u0002\u0011m\u0006\"\u0003C$;\u0006\u0005I\u0011\tC%\u0011%!9&XA\u0001\n\u0003!y\fC\u0005\u0005^u\u000b\t\u0011\"\u0011\u0005D\"IA1M/\u0002\u0002\u0013\u0005CQM\u0004\n\u0007os\u0015\u0011!E\u0001\u0007s3\u0011b!\u001bO\u0003\u0003E\ta!0\t\u000f\u0005-d\u000f\"\u0001\u0004N\"I!\u0011\b<\u0002\u0002\u0013\u00153q\u001a\u0005\n\u0007\u001f3\u0018\u0011!CA\u0007+D\u0011ba8w\u0003\u0003%\ti!9\t\u0013\r=h/!A\u0005\n\u0005ehABBy\u001d\u0002\u001b\u0019\u0010\u0003\u0006\u0004Zr\u0014)\u001a!C\u0001\u0003kB!ba?}\u0005#\u0005\u000b\u0011BA<\u0011\u001d\tY\u0007 C\u0001\u0007{Dq!a\u001d}\t\u0003\t)\bC\u0004\u00034r$\t\u0001b\u0001\t\u000f\tmF\u0010\"\u0001\u0005\b!9!\u0011\u0019?\u0005\u0002\u0011-\u0001b\u0002Bgy\u0012\u0005Aq\u0002\u0005\b\u0005'dH\u0011\u0001C\n\u0011\u001d\u0011I\u000e C\u0001\t/A\u0011\u0002b\u0007}\u0003\u0003%\t\u0001\"\b\t\u0013\u0011\u0005B0%A\u0005\u0002\u0011\r\u0002\"\u0003C\u001dy\u0006\u0005I\u0011\tC\u001e\u0011%!i\u0004`A\u0001\n\u0003\tI\rC\u0005\u0005@q\f\t\u0011\"\u0001\u0005B!IAq\t?\u0002\u0002\u0013\u0005C\u0011\n\u0005\n\t/b\u0018\u0011!C\u0001\t3B\u0011\u0002\"\u0018}\u0003\u0003%\t\u0005b\u0018\t\u0013\u0011\rD0!A\u0005B\u0011\u0015t!\u0003C:\u001d\u0006\u0005\t\u0012\u0001C;\r%\u0019\tPTA\u0001\u0012\u0003!9\b\u0003\u0005\u0002l\u0005\rB\u0011\u0001C@\u0011)\u0011I$a\t\u0002\u0002\u0013\u00153q\u001a\u0005\u000b\u0007\u001f\u000b\u0019#!A\u0005\u0002\u0012\u0005\u0005BCBp\u0003G\t\t\u0011\"!\u0005\u0006\"Q1q^A\u0012\u0003\u0003%I!!?\t\u0013\r=h*!A\u0005\n\u0005e(a\u0002(biV\u0014\u0018\r\u001c\u0006\u0005\u0003k\t9$\u0001\u0003nCRD'BAA\u001d\u0003\u0015\u0019\b/\u001b:f\u0007\u0001\u0019r\u0001AA \u0003\u001b\n\u0019\u0006\u0005\u0003\u0002B\u0005%SBAA\"\u0015\u0011\t)$!\u0012\u000b\u0005\u0005\u001d\u0013!B:dC2\f\u0017\u0002BA&\u0003\u0007\u00121bU2bY\u0006tU/\u001c2feB!\u0011\u0011IA(\u0013\u0011\t\t&a\u0011\u0003/M\u001b\u0017\r\\1Ok6,'/[2D_:4XM]:j_:\u001c\b\u0003BA+\u0003KrA!a\u0016\u0002b9!\u0011\u0011LA0\u001b\t\tYF\u0003\u0003\u0002^\u0005m\u0012A\u0002\u001fs_>$h(\u0003\u0002\u0002H%!\u00111MA#\u0003\u001d\u0001\u0018mY6bO\u0016LA!a\u001a\u0002j\ta1+\u001a:jC2L'0\u00192mK*!\u00111MA#\u0003\u0019a\u0014N\\5u}Q\u0011\u0011q\u000e\t\u0004\u0003c\u0002QBAA\u001a\u0003\u0015!\u0017nZ5u+\t\t9\b\u0005\u0003\u0002r\u0005e\u0014\u0002BA>\u0003g\u0011A!V%oi\u0006qam\u001c7e\t&<\u0017\u000e^:MK\u001a$X\u0003BAA\u0003\u0013#B!a!\u00020R!\u0011QQAS!\u0011\t9)!#\r\u0001\u0011Y\u00111R\u0002!\u0002\u0003\u0005)\u0019AAG\u0005\u0005\t\u0015\u0003BAH\u0003/\u0003B!!%\u0002\u00146\u0011\u0011QI\u0005\u0005\u0003+\u000b)EA\u0004O_RD\u0017N\\4\u0011\t\u0005E\u0015\u0011T\u0005\u0005\u00037\u000b)EA\u0002B]fDC!!#\u0002 B!\u0011\u0011SAQ\u0013\u0011\t\u0019+!\u0012\u0003\u0017M\u0004XmY5bY&TX\r\u001a\u0005\b\u0003O\u001b\u0001\u0019AAU\u0003\u00051\u0007CCAI\u0003W\u000b))a\u001e\u0002\u0006&!\u0011QVA#\u0005%1UO\\2uS>t'\u0007C\u0004\u00022\u000e\u0001\r!!\"\u0002\u0003\u0005\fqBZ8mI\u0012Kw-\u001b;t%&<\u0007\u000e^\u000b\u0005\u0003o\u000bi\f\u0006\u0003\u0002:\u0006\u0015G\u0003BA^\u0003\u0003\u0004B!a\"\u0002>\u0012Y\u00111\u0012\u0003!\u0002\u0003\u0005)\u0019AAGQ\u0011\ti,a(\t\u000f\u0005\u001dF\u00011\u0001\u0002DBQ\u0011\u0011SAV\u0003w\u000b9(a/\t\u000f\u0005EF\u00011\u0001\u0002<\u0006Qq-\u001a;Ok6\u0014\u0015\u000e^:\u0016\u0005\u0005-\u0007\u0003BAI\u0003\u001bLA!a4\u0002F\t\u0019\u0011J\u001c;\u0002\u001d\u001d,G\u000fR5hSRdUM\\4uQ\u00061Ao\u001c'jgR,\"!a6\u0011\r\u0005U\u0013\u0011\\A<\u0013\u0011\tY.!\u001b\u0003\t1K7\u000f^\u0001\bi>\f%O]1z+\t\t\t\u000f\u0005\u0004\u0002\u0012\u0006\r\u00181Z\u0005\u0005\u0003K\f)EA\u0003BeJ\f\u00170\u0001\u0005sKZ,'o]3e+\t\ty'\u0001\u0003ue&l\u0017aB5t/\"|G.Z\u000b\u0003\u0003c\u0004B!!%\u0002t&!\u0011Q_A#\u0005\u001d\u0011un\u001c7fC:\f!\"\u001e8eKJd\u00170\u001b8h)\t\tY\u0010\u0005\u0003\u0002~\n\u001dQBAA\u0000\u0015\u0011\u0011\tAa\u0001\u0002\t1\fgn\u001a\u0006\u0003\u0005\u000b\tAA[1wC&!!\u0011BA\u0000\u0005\u0019y%M[3di\u0006A\u0011N\u001c;WC2,X-A\u0005m_:<g+\u00197vKV\u0011!\u0011\u0003\t\u0005\u0003#\u0013\u0019\"\u0003\u0003\u0003\u0016\u0005\u0015#\u0001\u0002'p]\u001e\f!B\u001a7pCR4\u0016\r\\;f+\t\u0011Y\u0002\u0005\u0003\u0002\u0012\nu\u0011\u0002\u0002B\u0010\u0003\u000b\u0012QA\u00127pCR\f1\u0002Z8vE2,g+\u00197vKV\u0011!Q\u0005\t\u0005\u0003#\u00139#\u0003\u0003\u0003*\u0005\u0015#A\u0002#pk\ndW-A\u0003u_&sG/\u0001\u0004u_2{gnZ\u0001\ti>\u0014\u0015nZ%oiV\u0011!1\u0007\t\u0005\u0003+\u0012)$\u0003\u0003\u00038\u0005%$A\u0002\"jO&sG/\u0001\u0005u_N#(/\u001b8h)\t\u0011i\u0004\u0005\u0003\u0003@\t\u001dc\u0002\u0002B!\u0005\u0007\u0002B!!\u0017\u0002F%!!QIA#\u0003\u0019\u0001&/\u001a3fM&!!\u0011\nB&\u0005\u0019\u0019FO]5oO*!!QIA#\u0003\u0019!xNU3qeV\u0011!QH\u0001\u0007SNTVM]8\u0002\u000b%\u001cxJ\\3\u0002\u000b%\u001cx\n\u001a3\u0002\r%\u001cXI^3o\u0003)\u0001xn^3s\u001f\u001a$vo\\\u0001\bG>l\u0007/\u0019:f)\u0011\tYMa\u0018\t\u000f\t\u00054\u00041\u0001\u0002x\u0005\u0019!\u000f[:\u0015\t\u0005-'Q\r\u0005\b\u0005Cb\u0002\u0019AA8\u0003\u0019)\u0017/^1mgR!\u0011\u0011\u001fB6\u0011\u001d\u0011\t'\ba\u0001\u0003/\u000b\u0011\u0002J3rI\u0015\fH%Z9\u0015\t\u0005E(\u0011\u000f\u0005\b\u0005Cr\u0002\u0019AA8\u0003-!S-\u001d\u0013cC:<G%Z9\u0015\t\u0005E(q\u000f\u0005\b\u0005Cz\u0002\u0019AA8\u0003\u0015!C.Z:t)\u0011\t\tP! \t\u000f\t\u0005\u0004\u00051\u0001\u0002p\u0005AA\u0005\\3tg\u0012*\u0017\u000f\u0006\u0003\u0002r\n\r\u0005b\u0002B1C\u0001\u0007\u0011qN\u0001\tI\u001d\u0014X-\u0019;feR!\u0011\u0011\u001fBE\u0011\u001d\u0011\tG\ta\u0001\u0003_\n1\u0002J4sK\u0006$XM\u001d\u0013fcR!\u0011\u0011\u001fBH\u0011\u001d\u0011\tg\ta\u0001\u0003_\"B!!=\u0003\u0014\"9!Q\u0013\u0013A\u0002\u0005]\u0014!\u0001:\u0015\t\u0005E(\u0011\u0014\u0005\b\u0005++\u0003\u0019AA<)\u0011\t\tP!(\t\u000f\tUe\u00051\u0001\u0002xQ!\u0011\u0011\u001fBQ\u0011\u001d\u0011)j\na\u0001\u0003o\"B!!=\u0003&\"9!Q\u0013\u0015A\u0002\tMB\u0003BAy\u0005SCqA!&*\u0001\u0004\u0011\u0019\u0004\u0006\u0003\u0002r\n5\u0006b\u0002BKU\u0001\u0007!1\u0007\u000b\u0005\u0003c\u0014\t\fC\u0004\u0003\u0016.\u0002\rAa\r\u0002\u000b\u0011\u0002H.^:\u0015\t\u0005=$q\u0017\u0005\b\u0005sc\u0003\u0019AA<\u0003\t\u0011H-\u0001\u0004%[&tWo\u001d\u000b\u0005\u0003_\u0012y\fC\u0004\u0003:6\u0002\r!a\u001e\u0002\r\u0011\"\u0018.\\3t)\u0011\tyG!2\t\u000f\tef\u00061\u0001\u0002x\u0005QA\u0005Z5wIQLG\u000eZ3\u0015\t\u0005=$1\u001a\u0005\b\u0005s{\u0003\u0019AA<\u0003\u0011!C-\u001b<\u0015\t\u0005=$\u0011\u001b\u0005\b\u0005s\u0003\u0004\u0019AA<\u0003!!\u0003/\u001a:dK:$H\u0003BA8\u0005/DqA!/2\u0001\u0004\t9(\u0001\u0007%I&4H\u0005]3sG\u0016tG\u000f\u0006\u0003\u0003^\n\r\b\u0003CAI\u0005?\fy'a\u001c\n\t\t\u0005\u0018Q\t\u0002\u0007)V\u0004H.\u001a\u001a\t\u000f\te&\u00071\u0001\u0002xQ!!1\u0007Bt\u0011\u001d\u0011\tg\ra\u0001\u0005g!BAa\r\u0003l\"9!\u0011\r\u001bA\u0002\tMB\u0003\u0002B\u001a\u0005_DqA!\u00196\u0001\u0004\u0011\u0019\u0004\u0006\u0003\u00034\tM\bb\u0002B1m\u0001\u0007!1\u0007\u000b\u0005\u0005g\u00119\u0010C\u0004\u0003b]\u0002\rAa\r\u0015\t\tM\"1 \u0005\b\u0005CB\u0004\u0019\u0001B\u001a)\u0011\u0011yp!\u0001\u0011\u0011\u0005E%q\u001cB\u001a\u0005gAqA!\u0019:\u0001\u0004\u0011\u0019\u0004\u0006\u0003\u0002p\r\u0015\u0001b\u0002B1u\u0001\u0007\u0011q\u000e\u000b\u0005\u0003_\u001aI\u0001C\u0004\u0003bm\u0002\r!a\u001c\u0015\t\u0005=4Q\u0002\u0005\b\u0005Cb\u0004\u0019AA8\u0003\r\u0001xn\u001e\u000b\u0005\u0003_\u001a\u0019\u0002C\u0004\u0003bu\u0002\r!a\u001c\u0015\t\u0005=4q\u0003\u0005\b\u0005Cr\u0004\u0019AA<)\u0011\tyga\u0007\t\u000f\t\u0005t\b1\u0001\u0002pQ!\u0011qNB\u0010\u0011\u001d\u0011\t\u0007\u0011a\u0001\u0003_\"B!a\u001c\u0004$!9!\u0011M!A\u0002\u0005=D\u0003\u0002Bo\u0007OAqA!\u0019C\u0001\u0004\ty'A\u0004m_:<G-\u001b<\u0015\r\tu7QFB\u0019\u0011\u001d\u0019yc\u0011a\u0001\u0003_\n1A\\;n\u0011\u001d\u0019\u0019d\u0011a\u0001\u0003_\nQ\u0001Z3o_6\f!\u0002\n7fgN$C.Z:t)\u0011\tyg!\u000f\t\u000f\rmB\t1\u0001\u0002L\u0006\ta.\u0001\u0003dQ>\u0004H\u0003BA8\u0007\u0003Bqaa\u000fF\u0001\u0004\tY-\u0001\t%OJ,\u0017\r^3sI\u001d\u0014X-\u0019;feR!\u0011qNB$\u0011\u001d\u0019YD\u0012a\u0001\u0003\u0017\fA\u0001\n2beR!\u0011qNB'\u0011\u001d\u0011\tg\u0012a\u0001\u0003_\"B!a\u001c\u0004R!9!\u0011\r%A\u0002\u0005]\u0014\u0001\u0002\u0013b[B$B!a\u001c\u0004X!9!\u0011M%A\u0002\u0005=D\u0003BA8\u00077BqA!\u0019K\u0001\u0004\t9(A\u0002%kB$B!a\u001c\u0004b!9!\u0011M&A\u0002\u0005=D\u0003BA8\u0007KBqA!\u0019M\u0001\u0004\t9(K\u0002\u0001;r\u0014Q\u0001R5hSR\u001crATB7\u0007g\u001aI\b\u0005\u0003\u0002\u0012\u000e=\u0014\u0002BB9\u0003\u000b\u0012a!\u00118z%\u00164\u0007\u0003BA9\u0007kJAaa\u001e\u00024\t\u0001b*\u0019;ve\u0006d\u0017J\\:uC:\u001cWm\u001d\t\u0005\u0007w\u001a\t)\u0004\u0002\u0004~)!1q\u0010B\u0002\u0003\tIw.\u0003\u0003\u0002h\ruDCABC!\r\t\tHT\u0001\u0007I\u0016tw.\u001c\u0011\u0002\u001f9\fG/\u001e:bYR{')[4J]R$BAa\r\u0004\u000e\"911\b*A\u0002\u0005=\u0014!B1qa2LH\u0003BA8\u0007'Cqa!&T\u0001\u0004\u00199*\u0001\u0002vgB1\u0011\u0011SBM\u0003oJAaa'\u0002F\tQAH]3qK\u0006$X\r\u001a \u0015\t\u0005=4q\u0014\u0005\b\u0007w!\u0006\u0019\u0001B\t)\u0011\tyga)\t\u000f\rmR\u000b1\u0001\u00034\u0005)A/\u001a82q\u00051A/\u001a82q\u0001\"B!a\u001c\u0004,\"91Q\u0016-A\u0002\tu\u0012!A:\u0002\ti,'o\\\u0001\u0006u\u0016\u0014x\u000eI\u0001\u0004_:,\u0017\u0001B8oK\u0002\nQ\u0001R5hSR\u00042aa/w\u001b\u0005q5#\u0002<\u0004@\u000ee\u0004CCBa\u0007\u000f\f9(a\u001c\u0004L6\u001111\u0019\u0006\u0005\u0007\u000b\f)%A\u0004sk:$\u0018.\\3\n\t\r%71\u0019\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u0014\u0004cAB^;R\u00111\u0011\u0018\u000b\u0003\u0007#\u0004B!!@\u0004T&!!\u0011JA\u0000)\u0019\u0019Yma6\u0004\\\"91\u0011\\=A\u0002\u0005]\u0014!\u00013\t\u000f\ru\u0017\u00101\u0001\u0002p\u0005\u0011A\u000f\\\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\u0019\u0019oa;\u0011\r\u0005E5Q]Bu\u0013\u0011\u00199/!\u0012\u0003\r=\u0003H/[8o!!\t\tJa8\u0002x\u0005=\u0004\"CBwu\u0006\u0005\t\u0019ABf\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u0002\u0004\u000b:$7c\u0002?\u0002p\u0005M3Q\u001f\t\u0005\u0003#\u001b90\u0003\u0003\u0004z\u0006\u0015#a\u0002)s_\u0012,8\r^\u0001\u0003I\u0002\"Baa@\u0005\u0002A\u001911\u0018?\t\u000f\rew\u00101\u0001\u0002xQ!\u0011q\u000eC\u0003\u0011!\u0019Y$a\u0001A\u0002\u0005]D\u0003BA8\t\u0013A\u0001ba\u000f\u0002\u0006\u0001\u0007\u0011q\u000f\u000b\u0005\u0003_\"i\u0001\u0003\u0005\u0004<\u0005\u001d\u0001\u0019AA<)\u0011\ty\u0007\"\u0005\t\u0011\rm\u0012\u0011\u0002a\u0001\u0003o\"B!a\u001c\u0005\u0016!A11HA\u0006\u0001\u0004\t9\b\u0006\u0003\u0003^\u0012e\u0001\u0002CB\u001e\u0003\u001b\u0001\r!a\u001e\u0002\t\r|\u0007/\u001f\u000b\u0005\u0007\u007f$y\u0002\u0003\u0006\u0004Z\u0006=\u0001\u0013!a\u0001\u0003o\nabY8qs\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0005&)\"\u0011q\u000fC\u0014W\t!I\u0003\u0005\u0003\u0005,\u0011URB\u0001C\u0017\u0015\u0011!y\u0003\"\r\u0002\u0013Ut7\r[3dW\u0016$'\u0002\u0002C\u001a\u0003\u000b\n!\"\u00198o_R\fG/[8o\u0013\u0011!9\u0004\"\f\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0007#\fA\u0002\u001d:pIV\u001cG/\u0011:jif\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0002\u0018\u0012\r\u0003B\u0003C#\u0003/\t\t\u00111\u0001\u0002L\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"\u0001b\u0013\u0011\r\u00115C1KAL\u001b\t!yE\u0003\u0003\u0005R\u0005\u0015\u0013AC2pY2,7\r^5p]&!AQ\u000bC(\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005EH1\f\u0005\u000b\t\u000b\nY\"!AA\u0002\u0005]\u0015A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$Ba!5\u0005b!QAQIA\u000f\u0003\u0003\u0005\r!a3\u0002\u0011!\f7\u000f[\"pI\u0016$\"!a3)\u000fq$I\u0007b\u001c\u0005rA!\u0011\u0011\u0013C6\u0013\u0011!i'!\u0012\u0003!M+'/[1m-\u0016\u00148/[8o+&#\u0015!\u0002<bYV,g$\u0001\u0001\u0002\u0007\u0015sG\r\u0005\u0003\u0004<\u0006\r2CBA\u0012\ts\u001aI\b\u0005\u0005\u0004B\u0012m\u0014qOB\u0000\u0013\u0011!iha1\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0017\u0007\u0006\u0002\u0005vQ!1q CB\u0011!\u0019I.!\u000bA\u0002\u0005]D\u0003\u0002CD\t\u0013\u0003b!!%\u0004f\u0006]\u0004BCBw\u0003W\t\t\u00111\u0001\u0004\u0000N9Q,a\u001c\u0002T\rU\u0018a\u0001;mAQ111\u001aCI\t'Cqa!7c\u0001\u0004\t9\bC\u0004\u0004^\n\u0004\r!a\u001c\u0002\tQ\f\u0017\u000e\u001c\u000b\u0005\u0003_\"I\nC\u0004\u0004<\u0015\u0004\r!a\u001e\u0015\t\u0005=DQ\u0014\u0005\b\u0007w1\u0007\u0019AA<)\u0011\ty\u0007\")\t\u000f\rmr\r1\u0001\u0002xQ!\u0011q\u000eCS\u0011\u001d\u0019Y\u0004\u001ba\u0001\u0003o\"B!a\u001c\u0005*\"911H5A\u0002\u0005]D\u0003\u0002Bo\t[Cqaa\u000fk\u0001\u0004\t9\b\u0006\u0004\u0004L\u0012EF1\u0017\u0005\n\u00073\\\u0007\u0013!a\u0001\u0003oB\u0011b!8l!\u0003\u0005\r!a\u001c\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\u0011A\u0011\u0018\u0016\u0005\u0003_\"9\u0003\u0006\u0003\u0002\u0018\u0012u\u0006\"\u0003C#a\u0006\u0005\t\u0019AAf)\u0011\t\t\u0010\"1\t\u0013\u0011\u0015#/!AA\u0002\u0005]E\u0003BBi\t\u000bD\u0011\u0002\"\u0012t\u0003\u0003\u0005\r!a3)\u000fu#I\u0007b\u001c\u0005r!:\u0001\u0001\"\u001b\u0005p\u0011E\u0014a\u0002(biV\u0014\u0018\r\u001c"
)
public abstract class Natural extends ScalaNumber implements ScalaNumericConversions {
   private static final long serialVersionUID = 0L;

   public static Natural one() {
      return Natural$.MODULE$.one();
   }

   public static Natural zero() {
      return Natural$.MODULE$.zero();
   }

   public static Natural apply(final String s) {
      return Natural$.MODULE$.apply(s);
   }

   public static Natural apply(final BigInt n) {
      return Natural$.MODULE$.apply(n);
   }

   public static Natural apply(final long n) {
      return Natural$.MODULE$.apply(n);
   }

   public static Natural apply(final Seq us) {
      return Natural$.MODULE$.apply(us);
   }

   public static BigInt naturalToBigInt(final Natural n) {
      return Natural$.MODULE$.naturalToBigInt(n);
   }

   public static NumberTag NaturalTag() {
      return Natural$.MODULE$.NaturalTag();
   }

   public static CommutativeRig NaturalAlgebra() {
      return Natural$.MODULE$.NaturalAlgebra();
   }

   public char toChar() {
      return ScalaNumericAnyConversions.toChar$(this);
   }

   public byte toByte() {
      return ScalaNumericAnyConversions.toByte$(this);
   }

   public short toShort() {
      return ScalaNumericAnyConversions.toShort$(this);
   }

   public float toFloat() {
      return ScalaNumericAnyConversions.toFloat$(this);
   }

   public double toDouble() {
      return ScalaNumericAnyConversions.toDouble$(this);
   }

   public boolean isValidByte() {
      return ScalaNumericAnyConversions.isValidByte$(this);
   }

   public boolean isValidShort() {
      return ScalaNumericAnyConversions.isValidShort$(this);
   }

   public boolean isValidInt() {
      return ScalaNumericAnyConversions.isValidInt$(this);
   }

   public boolean isValidChar() {
      return ScalaNumericAnyConversions.isValidChar$(this);
   }

   public int unifiedPrimitiveHashcode() {
      return ScalaNumericAnyConversions.unifiedPrimitiveHashcode$(this);
   }

   public boolean unifiedPrimitiveEquals(final Object x) {
      return ScalaNumericAnyConversions.unifiedPrimitiveEquals$(this, x);
   }

   public abstract int digit();

   public Object foldDigitsLeft(final Object a, final Function2 f) {
      return this.recur$1(this, a, f, a);
   }

   public Object foldDigitsRight(final Object a, final Function2 f) {
      return this.reversed().foldDigitsLeft(a, f);
   }

   public int getNumBits() {
      return this.recur$2(this, 0);
   }

   public int getDigitLength() {
      return this.recur$3(this, 0);
   }

   public List toList() {
      return this.recur$4(this, .MODULE$.Nil());
   }

   public int[] toArray() {
      int n = this.getDigitLength();
      int[] arr = new int[n];
      this.recur$5(this, n - 1, arr);
      return arr;
   }

   public Natural reversed() {
      Natural var1;
      if (this instanceof Digit) {
         Digit var3 = (Digit)this;
         int d = var3.d();
         Natural tail = var3.tl();
         var1 = this.recur$6(tail, new End(d));
      } else {
         var1 = this;
      }

      return var1;
   }

   public Natural trim() {
      return this.recur$7(this.reversed()).reversed();
   }

   public boolean isWhole() {
      return true;
   }

   public Object underlying() {
      return this;
   }

   public int intValue() {
      return this.toInt();
   }

   public long longValue() {
      return this.toLong();
   }

   public float floatValue() {
      return this.toBigInt().toFloat();
   }

   public double doubleValue() {
      return this.toBigInt().toDouble();
   }

   public int toInt() {
      return UInt$.MODULE$.toInt$extension(this.digit()) & Integer.MAX_VALUE;
   }

   public long toLong() {
      long var1;
      if (this instanceof End) {
         End var4 = (End)this;
         int d = var4.d();
         var1 = UInt$.MODULE$.toLong$extension(d);
      } else {
         if (!(this instanceof Digit)) {
            throw new MatchError(this);
         }

         Digit var6 = (Digit)this;
         int d = var6.d();
         Natural tail = var6.tl();
         var1 = (tail.toLong() << (int)32L) + UInt$.MODULE$.toLong$extension(d);
      }

      return var1;
   }

   public BigInt toBigInt() {
      BigInt var1;
      if (this instanceof End) {
         End var3 = (End)this;
         int d = var3.d();
         var1 = .MODULE$.BigInt().apply(UInt$.MODULE$.toLong$extension(d));
      } else {
         if (!(this instanceof Digit)) {
            throw new MatchError(this);
         }

         Digit var5 = (Digit)this;
         int d = var5.d();
         Natural tail = var5.tl();
         var1 = tail.toBigInt().$less$less(32).$plus(.MODULE$.BigInt().apply(UInt$.MODULE$.toLong$extension(d)));
      }

      return var1;
   }

   public String toString() {
      return this.recur$8(this, "");
   }

   public String toRepr() {
      return this.toList().mkString("Natural(", ", ", ")");
   }

   public boolean isZero() {
      return this.recur$9(this);
   }

   public boolean isOne() {
      boolean var1;
      if (this instanceof End) {
         End var3 = (End)this;
         int n = var3.d();
         var1 = UInt$.MODULE$.$eq$eq$extension(n, UInt$.MODULE$.apply(1));
      } else {
         if (!(this instanceof Digit)) {
            throw new MatchError(this);
         }

         Digit var5 = (Digit)this;
         int n = var5.d();
         Natural tail = var5.tl();
         var1 = UInt$.MODULE$.$eq$eq$extension(n, UInt$.MODULE$.apply(1)) && tail.isZero();
      }

      return var1;
   }

   public boolean isOdd() {
      return UInt$.MODULE$.$eq$eq$extension(UInt$.MODULE$.$amp$extension(this.digit(), UInt$.MODULE$.apply(1)), UInt$.MODULE$.apply(1));
   }

   public boolean isEven() {
      return UInt$.MODULE$.$eq$eq$extension(UInt$.MODULE$.$amp$extension(this.digit(), UInt$.MODULE$.apply(1)), UInt$.MODULE$.apply(0));
   }

   public int powerOfTwo() {
      return this.recur$10(this, 0, -1);
   }

   public int compare(final int rhs) {
      int var2;
      if (this instanceof End) {
         End var4 = (End)this;
         int d = var4.d();
         var2 = UInt$.MODULE$.$less$extension(d, rhs) ? -1 : (UInt$.MODULE$.$greater$extension(d, rhs) ? 1 : 0);
      } else {
         if (!(this instanceof Digit)) {
            throw new MatchError(this);
         }

         Digit var6 = (Digit)this;
         int d = var6.d();
         Natural tail = var6.tl();
         var2 = tail.isZero() ? (UInt$.MODULE$.$greater$extension(d, rhs) ? 1 : (UInt$.MODULE$.$less$extension(d, rhs) ? -1 : 0)) : 1;
      }

      return var2;
   }

   public int compare(final Natural rhs) {
      return this.recur$11(this, rhs, 0);
   }

   public final boolean equals(final Object rhs) {
      boolean var2;
      if (rhs instanceof Natural) {
         Natural var4 = (Natural)rhs;
         var2 = this.$eq$eq$eq(var4);
      } else if (rhs instanceof UInt) {
         int var5 = ((UInt)rhs).signed();
         var2 = this.compare(var5) == 0;
      } else if (rhs instanceof BigInt) {
         boolean var17;
         label121: {
            label120: {
               BigInt var6 = (BigInt)rhs;
               BigInt var10000 = this.toBigInt();
               if (var10000 == null) {
                  if (var6 == null) {
                     break label120;
                  }
               } else if (var10000.equals(var6)) {
                  break label120;
               }

               var17 = false;
               break label121;
            }

            var17 = true;
         }

         var2 = var17;
      } else if (rhs instanceof SafeLong) {
         SafeLong var8 = (SafeLong)rhs;
         var2 = BoxesRunTime.equalsNumNum(SafeLong$.MODULE$.apply(this.toBigInt()), var8);
      } else if (rhs instanceof BigDecimal) {
         BigDecimal var9 = (BigDecimal)rhs;
         var2 = var9.isWhole() && BoxesRunTime.equalsNumNum(this.toBigInt(), var9);
      } else if (rhs instanceof Rational) {
         Rational var10 = (Rational)rhs;
         var2 = var10.isWhole() && BoxesRunTime.equalsNumNum(Rational$.MODULE$.apply(this.toBigInt()), var10);
      } else if (rhs instanceof Algebraic) {
         Algebraic var11 = (Algebraic)rhs;
         var2 = BoxesRunTime.equalsNumNum(var11, this);
      } else if (rhs instanceof Real) {
         Real var12 = (Real)rhs;
         var2 = BoxesRunTime.equalsNumNum(this, var12.toRational());
      } else if (rhs instanceof Number) {
         boolean var19;
         label97: {
            label96: {
               Number var13 = (Number)rhs;
               Number var18 = Number$.MODULE$.apply(this.toBigInt());
               if (var18 == null) {
                  if (var13 == null) {
                     break label96;
                  }
               } else if (var18.equals(var13)) {
                  break label96;
               }

               var19 = false;
               break label97;
            }

            var19 = true;
         }

         var2 = var19;
      } else if (rhs instanceof Complex) {
         Complex var15 = (Complex)rhs;
         var2 = BoxesRunTime.equalsNumNum(var15, this);
      } else if (rhs instanceof Quaternion) {
         Quaternion var16 = (Quaternion)rhs;
         var2 = BoxesRunTime.equalsNumNum(var16, this);
      } else {
         var2 = this.unifiedPrimitiveEquals(rhs);
      }

      return var2;
   }

   public boolean $eq$eq$eq(final Natural rhs) {
      return this.compare(rhs) == 0;
   }

   public boolean $eq$bang$eq(final Natural rhs) {
      return !this.$eq$eq$eq(rhs);
   }

   public boolean $less(final Natural rhs) {
      return this.compare(rhs) < 0;
   }

   public boolean $less$eq(final Natural rhs) {
      return this.compare(rhs) <= 0;
   }

   public boolean $greater(final Natural rhs) {
      return this.compare(rhs) > 0;
   }

   public boolean $greater$eq(final Natural rhs) {
      return this.compare(rhs) >= 0;
   }

   public boolean $less(final int r) {
      return this.compare(r) < 0;
   }

   public boolean $less$eq(final int r) {
      return this.compare(r) <= 0;
   }

   public boolean $greater(final int r) {
      return this.compare(r) > 0;
   }

   public boolean $greater$eq(final int r) {
      return this.compare(r) >= 0;
   }

   public boolean $less(final BigInt r) {
      return this.toBigInt().compare(r) < 0;
   }

   public boolean $less$eq(final BigInt r) {
      return this.toBigInt().compare(r) <= 0;
   }

   public boolean $greater(final BigInt r) {
      return this.toBigInt().compare(r) > 0;
   }

   public boolean $greater$eq(final BigInt r) {
      return this.toBigInt().compare(r) >= 0;
   }

   public abstract Natural $plus(final int rd);

   public abstract Natural $minus(final int rd);

   public abstract Natural $times(final int rd);

   public Natural $div$tilde(final int rd) {
      return this.$div(rd);
   }

   public abstract Natural $div(final int rd);

   public abstract Natural $percent(final int rd);

   public abstract Tuple2 $div$percent(final int rd);

   public BigInt $plus(final BigInt rhs) {
      return this.toBigInt().$plus(rhs);
   }

   public BigInt $minus(final BigInt rhs) {
      return this.toBigInt().$minus(rhs);
   }

   public BigInt $times(final BigInt rhs) {
      return this.toBigInt().$times(rhs);
   }

   public BigInt $div$tilde(final BigInt rhs) {
      return this.toBigInt().$div(rhs);
   }

   public BigInt $div(final BigInt rhs) {
      return this.toBigInt().$div(rhs);
   }

   public BigInt $percent(final BigInt rhs) {
      return this.toBigInt().$percent(rhs);
   }

   public Tuple2 $div$percent(final BigInt rhs) {
      return this.toBigInt().$div$percent(rhs);
   }

   public Natural $plus(final Natural rhs) {
      return recur$12(this, rhs, 0L);
   }

   public Natural $minus(final Natural rhs) {
      if (this.$less(rhs)) {
         throw new ArithmeticException(scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("negative subtraction: %s - %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{this, rhs})));
      } else {
         return recur$13(this, rhs, 0L);
      }
   }

   public Natural $times(final Natural rhs) {
      Natural var2;
      if (this instanceof End) {
         End var5 = (End)this;
         int ld = var5.d();
         var2 = rhs.$times(ld);
      } else {
         if (!(this instanceof Digit)) {
            throw new MatchError(this);
         }

         Digit var7 = (Digit)this;
         int ld = var7.d();
         Natural ltail = var7.tl();
         Natural var3;
         if (rhs instanceof End) {
            End var11 = (End)rhs;
            int rd = var11.d();
            var3 = this.$times(rd);
         } else {
            if (!(rhs instanceof Digit)) {
               throw new MatchError(rhs);
            }

            Digit var13 = (Digit)rhs;
            int rd = var13.d();
            Natural rtail = var13.tl();
            var3 = (new Digit(UInt$.MODULE$.apply(0), new Digit(UInt$.MODULE$.apply(0), ltail.$times(rtail)))).$plus(new Digit(UInt$.MODULE$.apply(0), ltail.$times(rd))).$plus((Natural)(new Digit(UInt$.MODULE$.apply(0), rtail.$times(ld)))).$plus(Natural$.MODULE$.apply(UInt$.MODULE$.toLong$extension(ld) * UInt$.MODULE$.toLong$extension(rd)));
         }

         var2 = var3;
      }

      return var2;
   }

   public Natural pow(final Natural rhs) {
      return this._pow$1(Natural$.MODULE$.apply(1L), this, rhs);
   }

   public Natural pow(final int rhs) {
      return this._pow$2(Natural$.MODULE$.apply(1L), this, rhs);
   }

   public Natural $div$tilde(final Natural rhs) {
      return this.$div(rhs);
   }

   public Natural $div(final Natural rhs) {
      Object var2;
      if (rhs instanceof End) {
         End var5 = (End)rhs;
         int rd = var5.d();
         var2 = this.$div(rd);
      } else {
         if (!(rhs instanceof Digit)) {
            throw new MatchError(rhs);
         }

         Object var3;
         if (this instanceof End) {
            var3 = new End(UInt$.MODULE$.apply(0));
         } else {
            if (!(this instanceof Digit)) {
               throw new MatchError(this);
            }

            int var8 = rhs.compare(UInt$.MODULE$.apply(1));
            Natural var10000;
            switch (var8) {
               case -1:
                  throw new IllegalArgumentException("/ by zero");
               case 0:
                  var10000 = this;
                  break;
               case 1:
                  int p = rhs.powerOfTwo();
                  var10000 = p >= 0 ? this.$greater$greater(p) : (Natural)this.longdiv(this, rhs)._1();
                  break;
               default:
                  throw new MatchError(BoxesRunTime.boxToInteger(var8));
            }

            var3 = var10000;
         }

         var2 = var3;
      }

      return (Natural)var2;
   }

   public Natural $percent(final Natural rhs) {
      Object var2;
      if (rhs instanceof End) {
         End var5 = (End)rhs;
         int rd = var5.d();
         var2 = this.$percent(rd);
      } else {
         if (!(rhs instanceof Digit)) {
            throw new MatchError(rhs);
         }

         Object var3;
         if (this instanceof End) {
            End var8 = (End)this;
            int ld = var8.d();
            var3 = new End(ld);
         } else {
            if (!(this instanceof Digit)) {
               throw new MatchError(this);
            }

            int var10 = rhs.compare(UInt$.MODULE$.apply(1));
            Object var10000;
            switch (var10) {
               case -1:
                  throw new IllegalArgumentException("/ by zero");
               case 0:
                  var10000 = new End(UInt$.MODULE$.apply(0));
                  break;
               case 1:
                  int p = rhs.powerOfTwo();
                  var10000 = p >= 0 ? this.$amp(Natural$.MODULE$.apply(1L).$less$less(p).$minus(UInt$.MODULE$.apply(1))) : (Natural)this.longdiv(this, rhs)._2();
                  break;
               default:
                  throw new MatchError(BoxesRunTime.boxToInteger(var10));
            }

            var3 = var10000;
         }

         var2 = var3;
      }

      return (Natural)var2;
   }

   public Tuple2 $div$percent(final Natural rhs) {
      Tuple2 var2;
      if (rhs instanceof End) {
         End var5 = (End)rhs;
         int rd = var5.d();
         var2 = new Tuple2(this.$div(rd), this.$percent(rd));
      } else {
         if (!(rhs instanceof Digit)) {
            throw new MatchError(rhs);
         }

         Tuple2 var3;
         if (this instanceof End) {
            var3 = new Tuple2(new End(UInt$.MODULE$.apply(0)), this);
         } else {
            if (!(this instanceof Digit)) {
               throw new MatchError(this);
            }

            int var8 = rhs.compare(UInt$.MODULE$.apply(1));
            Tuple2 var10000;
            switch (var8) {
               case -1:
                  throw new IllegalArgumentException("/ by zero");
               case 0:
                  var10000 = new Tuple2(this, Natural$.MODULE$.apply(0L));
                  break;
               case 1:
                  int p = rhs.powerOfTwo();
                  if (p >= 0) {
                     Natural mask = Natural$.MODULE$.apply(1L).$less$less(p).$minus(UInt$.MODULE$.apply(1));
                     var10000 = new Tuple2(this.$greater$greater(p), this.$amp(mask));
                  } else {
                     var10000 = this.longdiv(this, rhs);
                  }
                  break;
               default:
                  throw new MatchError(BoxesRunTime.boxToInteger(var8));
            }

            var3 = var10000;
         }

         var2 = var3;
      }

      return var2;
   }

   private Tuple2 longdiv(final Natural num, final Natural denom) {
      Natural rem = num;
      Natural quo = Natural$.MODULE$.apply(0L);
      int remBits = num.getNumBits();
      int denomBits = denom.getNumBits();
      int shift = remBits - denomBits;

      while(shift >= 0) {
         Natural shifted = denom.$less$less(shift);
         if (shifted.$less$eq(rem)) {
            quo = quo.$plus(Natural$.MODULE$.apply(1L).$less$less(shift));
            rem = rem.$minus(shifted);
            remBits = rem.getNumBits();
            shift = remBits - denomBits;
         } else {
            --shift;
         }
      }

      return new Tuple2(quo, rem);
   }

   public Natural $less$less(final int n) {
      int m = n & 31;
      Natural num = recur$14(this, 0L, m);
      return (Natural)scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), n / 32).foldLeft(num, (nx, x$2) -> $anonfun$$less$less$1(nx, BoxesRunTime.unboxToInt(x$2)));
   }

   public Natural chop(final int n) {
      return this.recur$15(this, n);
   }

   public Natural $greater$greater(final int n) {
      int m = n & 31;
      return recur$16(this.chop(n / 32).reversed(), 0L, m).reversed();
   }

   public Natural $bar(final Natural rhs) {
      Object var2;
      if (this instanceof End) {
         End var6 = (End)this;
         int ld = var6.d();
         Object var4;
         if (rhs instanceof End) {
            End var9 = (End)rhs;
            int rd = var9.d();
            var4 = new End(UInt$.MODULE$.$bar$extension(ld, rd));
         } else {
            if (!(rhs instanceof Digit)) {
               throw new MatchError(rhs);
            }

            Digit var11 = (Digit)rhs;
            int rd = var11.d();
            Natural rtail = var11.tl();
            var4 = new Digit(UInt$.MODULE$.$bar$extension(ld, rd), rtail);
         }

         var2 = var4;
      } else {
         if (!(this instanceof Digit)) {
            throw new MatchError(this);
         }

         Digit var14 = (Digit)this;
         int ld = var14.d();
         Natural ltail = var14.tl();
         Digit var3;
         if (rhs instanceof End) {
            End var18 = (End)rhs;
            int rd = var18.d();
            var3 = new Digit(UInt$.MODULE$.$bar$extension(ld, rd), ltail);
         } else {
            if (!(rhs instanceof Digit)) {
               throw new MatchError(rhs);
            }

            Digit var20 = (Digit)rhs;
            int rd = var20.d();
            Natural rtail = var20.tl();
            var3 = new Digit(UInt$.MODULE$.$bar$extension(ld, rd), ltail.$bar(rtail));
         }

         var2 = var3;
      }

      return (Natural)var2;
   }

   public Natural $bar(final int rhs) {
      Object var2;
      if (this instanceof End) {
         End var4 = (End)this;
         int ld = var4.d();
         var2 = new End(UInt$.MODULE$.$bar$extension(ld, rhs));
      } else {
         if (!(this instanceof Digit)) {
            throw new MatchError(this);
         }

         Digit var6 = (Digit)this;
         int ld = var6.d();
         Natural ltail = var6.tl();
         var2 = new Digit(UInt$.MODULE$.$bar$extension(ld, rhs), ltail);
      }

      return (Natural)var2;
   }

   public Natural $amp(final Natural rhs) {
      return and$1(this, rhs).trim();
   }

   public Natural $amp(final int rhs) {
      return new End(UInt$.MODULE$.$amp$extension(this.digit(), rhs));
   }

   public Natural $up(final Natural rhs) {
      return xor$1(this, rhs).trim();
   }

   public Natural $up(final int rhs) {
      Object var2;
      if (this instanceof End) {
         End var4 = (End)this;
         int ld = var4.d();
         var2 = new End(UInt$.MODULE$.$up$extension(ld, rhs));
      } else {
         if (!(this instanceof Digit)) {
            throw new MatchError(this);
         }

         Digit var6 = (Digit)this;
         int ld = var6.d();
         Natural ltail = var6.tl();
         var2 = new Digit(UInt$.MODULE$.$up$extension(ld, rhs), ltail);
      }

      return (Natural)var2;
   }

   public boolean foldDigitsLeft$mZc$sp(final boolean a, final Function2 f) {
      return this.recur$17(this, a, f, a);
   }

   public byte foldDigitsLeft$mBc$sp(final byte a, final Function2 f) {
      return this.recur$18(this, a, f, a);
   }

   public char foldDigitsLeft$mCc$sp(final char a, final Function2 f) {
      return this.recur$19(this, a, f, a);
   }

   public double foldDigitsLeft$mDc$sp(final double a, final Function2 f) {
      return this.recur$20(this, a, f, a);
   }

   public float foldDigitsLeft$mFc$sp(final float a, final Function2 f) {
      return this.recur$21(this, a, f, a);
   }

   public int foldDigitsLeft$mIc$sp(final int a, final Function2 f) {
      return this.recur$22(this, a, f, a);
   }

   public long foldDigitsLeft$mJc$sp(final long a, final Function2 f) {
      return this.recur$23(this, a, f, a);
   }

   public short foldDigitsLeft$mSc$sp(final short a, final Function2 f) {
      return this.recur$24(this, a, f, a);
   }

   public void foldDigitsLeft$mVc$sp(final BoxedUnit a, final Function2 f) {
      this.recur$25(this, a, f, a);
   }

   public boolean foldDigitsRight$mZc$sp(final boolean a, final Function2 f) {
      return this.reversed().foldDigitsLeft$mZc$sp(a, f);
   }

   public byte foldDigitsRight$mBc$sp(final byte a, final Function2 f) {
      return this.reversed().foldDigitsLeft$mBc$sp(a, f);
   }

   public char foldDigitsRight$mCc$sp(final char a, final Function2 f) {
      return this.reversed().foldDigitsLeft$mCc$sp(a, f);
   }

   public double foldDigitsRight$mDc$sp(final double a, final Function2 f) {
      return this.reversed().foldDigitsLeft$mDc$sp(a, f);
   }

   public float foldDigitsRight$mFc$sp(final float a, final Function2 f) {
      return this.reversed().foldDigitsLeft$mFc$sp(a, f);
   }

   public int foldDigitsRight$mIc$sp(final int a, final Function2 f) {
      return this.reversed().foldDigitsLeft$mIc$sp(a, f);
   }

   public long foldDigitsRight$mJc$sp(final long a, final Function2 f) {
      return this.reversed().foldDigitsLeft$mJc$sp(a, f);
   }

   public short foldDigitsRight$mSc$sp(final short a, final Function2 f) {
      return this.reversed().foldDigitsLeft$mSc$sp(a, f);
   }

   public void foldDigitsRight$mVc$sp(final BoxedUnit a, final Function2 f) {
      this.reversed().foldDigitsLeft$mVc$sp(a, f);
   }

   private final Object recur$1(final Natural next, final Object sofar, final Function2 f$1, final Object a$1) {
      while(!(next instanceof End)) {
         if (!(next instanceof Digit)) {
            throw new MatchError(next);
         }

         Digit var10 = (Digit)next;
         int d = var10.d();
         Natural tail = var10.tl();
         f$1.apply(a$1, new UInt(d));
         next = tail;
      }

      End var8 = (End)next;
      int d = var8.d();
      Object var6 = f$1.apply(a$1, new UInt(d));
      return var6;
   }

   private final int bit$1(final int n, final int b) {
      while(!UInt$.MODULE$.$eq$eq$extension(n, UInt$.MODULE$.apply(0))) {
         int var10000 = UInt$.MODULE$.$greater$greater$greater$extension(n, 1);
         ++b;
         n = var10000;
      }

      return b;
   }

   private final int recur$2(final Natural next, final int b) {
      while(!(next instanceof End)) {
         if (!(next instanceof Digit)) {
            throw new MatchError(next);
         }

         Digit var8 = (Digit)next;
         Natural tail = var8.tl();
         b += 32;
         next = tail;
      }

      End var6 = (End)next;
      int d = var6.d();
      int var4 = b + this.bit$1(d, 0);
      return var4;
   }

   private final int recur$3(final Natural next, final int n) {
      while(!(next instanceof End)) {
         if (!(next instanceof Digit)) {
            throw new MatchError(next);
         }

         Digit var6 = (Digit)next;
         Natural tail = var6.tl();
         ++n;
         next = tail;
      }

      int var4 = n + 1;
      return var4;
   }

   private final List recur$4(final Natural next, final List sofar) {
      while(!(next instanceof End)) {
         if (!(next instanceof Digit)) {
            throw new MatchError(next);
         }

         Digit var9 = (Digit)next;
         int d = var9.d();
         Natural tail = var9.tl();
         sofar = sofar.$colon$colon(new UInt(d));
         next = tail;
      }

      End var6 = (End)next;
      int d = var6.d();
      List var4 = sofar.$colon$colon(new UInt(d));
      return var4;
   }

   private final void recur$5(final Natural next, final int i, final int[] arr$1) {
      while(!(next instanceof End)) {
         if (!(next instanceof Digit)) {
            throw new MatchError(next);
         }

         Digit var9 = (Digit)next;
         int d = var9.d();
         Natural tail = var9.tl();
         arr$1[i] = d;
         --i;
         next = tail;
      }

      End var7 = (End)next;
      int d = var7.d();
      arr$1[i] = d;
      BoxedUnit var5 = BoxedUnit.UNIT;
      BoxedUnit var10000 = BoxedUnit.UNIT;
   }

   private final Natural recur$6(final Natural next, final Natural sofar) {
      while(!(next instanceof End)) {
         if (!(next instanceof Digit)) {
            throw new MatchError(next);
         }

         Digit var8 = (Digit)next;
         int d = var8.d();
         Natural tail = var8.tl();
         sofar = new Digit(d, sofar);
         next = tail;
      }

      End var6 = (End)next;
      int d = var6.d();
      Digit var4 = new Digit(d, sofar);
      return var4;
   }

   private final Natural recur$7(final Natural next) {
      while(true) {
         Natural var3;
         if (next instanceof Digit) {
            Digit var5 = (Digit)next;
            int n = var5.d();
            Natural tail = var5.tl();
            if (UInt$.MODULE$.$eq$eq$extension(n, UInt$.MODULE$.apply(0))) {
               next = tail;
               continue;
            }

            var3 = next;
         } else {
            if (!(next instanceof End)) {
               throw new MatchError(next);
            }

            var3 = next;
         }

         return var3;
      }
   }

   private final String recur$8(final Natural next, final String s) {
      while(true) {
         String var4;
         if (next instanceof End) {
            End var7 = (End)next;
            int d = var7.d();
            var4 = (new StringBuilder(0)).append(Long.toString(UInt$.MODULE$.toLong$extension(d))).append(s).toString();
         } else {
            if (!(next instanceof Digit)) {
               throw new MatchError(next);
            }

            Tuple2 var10 = next.$div$percent(Natural$.MODULE$.denom());
            if (var10 == null) {
               throw new MatchError(var10);
            }

            Natural q = (Natural)var10._1();
            Natural r = (Natural)var10._2();
            Tuple2 var5 = new Tuple2(q, r);
            Natural q = (Natural)var5._1();
            Natural r = (Natural)var5._2();
            if (!q.isZero()) {
               s = scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("%09d%s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToLong(UInt$.MODULE$.toLong$extension(r.digit())), s}));
               next = q;
               continue;
            }

            var4 = (new StringBuilder(0)).append(Long.toString(UInt$.MODULE$.toLong$extension(r.digit()))).append(s).toString();
         }

         return var4;
      }
   }

   private final boolean recur$9(final Natural next) {
      while(true) {
         boolean var3;
         if (next instanceof End) {
            End var5 = (End)next;
            int n = var5.d();
            var3 = UInt$.MODULE$.$eq$eq$extension(n, UInt$.MODULE$.apply(0));
         } else {
            if (!(next instanceof Digit)) {
               throw new MatchError(next);
            }

            Digit var7 = (Digit)next;
            int n = var7.d();
            Natural tail = var7.tl();
            if (UInt$.MODULE$.$eq$eq$extension(n, UInt$.MODULE$.apply(0))) {
               next = tail;
               continue;
            }

            var3 = false;
         }

         return var3;
      }
   }

   private static final int test$1(final int n) {
      if ((n & -n) != n) {
         return -1;
      } else {
         int i;
         for(i = 1; i < 32 && UInt$.MODULE$.$bang$eq$extension(UInt$.MODULE$.$greater$greater$greater$extension(n, i), UInt$.MODULE$.apply(0)); ++i) {
         }

         return i - 1;
      }
   }

   private final int recur$10(final Natural next, final int shift, final int bit) {
      while(true) {
         int var5;
         if (next instanceof End) {
            End var7 = (End)next;
            int n = var7.d();
            int t = test$1(n);
            var5 = t < 0 ? -1 : (bit < 0 ? shift + t : -1);
         } else {
            if (!(next instanceof Digit)) {
               throw new MatchError(next);
            }

            Digit var10 = (Digit)next;
            int n = var10.d();
            Natural tail = var10.tl();
            int t = test$1(n);
            if (t < 0) {
               int var14 = shift + 32;
               bit = bit;
               shift = var14;
               next = tail;
               continue;
            }

            if (bit < 0) {
               int var10001 = shift + 32;
               bit = shift + t;
               shift = var10001;
               next = tail;
               continue;
            }

            var5 = -1;
         }

         return var5;
      }
   }

   private static final int cmp$1(final int a, final int b, final int c) {
      return UInt$.MODULE$.$less$extension(a, b) ? -1 : (UInt$.MODULE$.$greater$extension(a, b) ? 1 : c);
   }

   private final int recur$11(final Natural lhs, final Natural rhs, final int d) {
      while(true) {
         int var5;
         if (lhs instanceof End) {
            End var9 = (End)lhs;
            int ld = var9.d();
            int var7;
            if (rhs instanceof End) {
               End var12 = (End)rhs;
               int rd = var12.d();
               var7 = cmp$1(ld, rd, d);
            } else {
               if (!(rhs instanceof Digit)) {
                  throw new MatchError(rhs);
               }

               var7 = -rhs.compare(ld);
            }

            var5 = var7;
         } else {
            if (!(lhs instanceof Digit)) {
               throw new MatchError(lhs);
            }

            Digit var14 = (Digit)lhs;
            int ld = var14.d();
            Natural ltail = var14.tl();
            if (!(rhs instanceof End)) {
               if (rhs instanceof Digit) {
                  Digit var20 = (Digit)rhs;
                  int rd = var20.d();
                  Natural rtail = var20.tl();
                  d = cmp$1(ld, rd, d);
                  rhs = rtail;
                  lhs = ltail;
                  continue;
               }

               throw new MatchError(rhs);
            }

            End var18 = (End)rhs;
            int rd = var18.d();
            int var6 = lhs.compare(rd);
            var5 = var6;
         }

         return var5;
      }
   }

   private static final Natural recur$12(final Natural left, final Natural right, final long carry) {
      Object var4;
      if (left instanceof End) {
         End var8 = (End)left;
         int ld = var8.d();
         Object var6;
         if (right instanceof End) {
            End var11 = (End)right;
            int rd = var11.d();
            var6 = Natural$.MODULE$.apply(UInt$.MODULE$.toLong$extension(ld) + UInt$.MODULE$.toLong$extension(rd) + carry);
         } else {
            if (!(right instanceof Digit)) {
               throw new MatchError(right);
            }

            Digit var13 = (Digit)right;
            int rd = var13.d();
            Natural rtail = var13.tl();
            long t = UInt$.MODULE$.toLong$extension(ld) + UInt$.MODULE$.toLong$extension(rd) + carry;
            var6 = new Digit(UInt$.MODULE$.apply(t), rtail.$plus(UInt$.MODULE$.apply(t >> 32)));
         }

         var4 = var6;
      } else {
         if (!(left instanceof Digit)) {
            throw new MatchError(left);
         }

         Digit var18 = (Digit)left;
         int ld = var18.d();
         Natural ltail = var18.tl();
         Digit var5;
         if (right instanceof End) {
            End var22 = (End)right;
            int rd = var22.d();
            long t = UInt$.MODULE$.toLong$extension(ld) + UInt$.MODULE$.toLong$extension(rd) + carry;
            var5 = new Digit(UInt$.MODULE$.apply(t), ltail.$plus(UInt$.MODULE$.apply(t >> 32)));
         } else {
            if (!(right instanceof Digit)) {
               throw new MatchError(right);
            }

            Digit var26 = (Digit)right;
            int rd = var26.d();
            Natural rtail = var26.tl();
            long t = UInt$.MODULE$.toLong$extension(ld) + UInt$.MODULE$.toLong$extension(rd) + carry;
            var5 = new Digit(UInt$.MODULE$.apply(t), recur$12(ltail, rtail, t >> 32));
         }

         var4 = var5;
      }

      return (Natural)var4;
   }

   private static final Natural recur$13(final Natural left, final Natural right, final long carry) {
      Object var4;
      if (left instanceof End) {
         End var8 = (End)left;
         int ld = var8.d();
         Object var6;
         if (right instanceof End) {
            End var11 = (End)right;
            int rd = var11.d();
            var6 = Natural$.MODULE$.apply(UInt$.MODULE$.toLong$extension(ld) - UInt$.MODULE$.toLong$extension(rd) - carry);
         } else {
            if (!(right instanceof Digit)) {
               throw new MatchError(right);
            }

            Digit var13 = (Digit)right;
            int rd = var13.d();
            Natural rtail = var13.tl();
            long t = UInt$.MODULE$.toLong$extension(ld) - UInt$.MODULE$.toLong$extension(rd) - carry;
            Natural tl = rtail.$minus(UInt$.MODULE$.apply(-(t >> 32)));
            var6 = tl instanceof End && UInt$.MODULE$.$eq$eq$extension(tl.digit(), UInt$.MODULE$.apply(0)) ? new End(UInt$.MODULE$.apply(t)) : new Digit(UInt$.MODULE$.apply(t), tl);
         }

         var4 = var6;
      } else {
         if (!(left instanceof Digit)) {
            throw new MatchError(left);
         }

         Digit var19 = (Digit)left;
         int ld = var19.d();
         Natural ltail = var19.tl();
         Object var5;
         if (right instanceof End) {
            End var23 = (End)right;
            int rd = var23.d();
            long t = UInt$.MODULE$.toLong$extension(ld) - UInt$.MODULE$.toLong$extension(rd) - carry;
            Natural tl = ltail.$minus(UInt$.MODULE$.apply(-(t >> 32)));
            var5 = tl instanceof End && UInt$.MODULE$.$eq$eq$extension(tl.digit(), UInt$.MODULE$.apply(0)) ? new End(UInt$.MODULE$.apply(t)) : new Digit(UInt$.MODULE$.apply(t), tl);
         } else {
            if (!(right instanceof Digit)) {
               throw new MatchError(right);
            }

            Digit var28 = (Digit)right;
            int rd = var28.d();
            Natural rtail = var28.tl();
            long t = UInt$.MODULE$.toLong$extension(ld) - UInt$.MODULE$.toLong$extension(rd) - carry;
            Natural tl = recur$13(ltail, rtail, -(t >> 32));
            var5 = tl instanceof End && UInt$.MODULE$.$eq$eq$extension(tl.digit(), UInt$.MODULE$.apply(0)) ? new End(UInt$.MODULE$.apply(t)) : new Digit(UInt$.MODULE$.apply(t), tl);
         }

         var4 = var5;
      }

      return (Natural)var4;
   }

   private final Natural _pow$1(final Natural t, final Natural b, final Natural e) {
      while(!e.isZero()) {
         if (e.isOdd()) {
            Natural var10000 = t.$times(b);
            Natural var4 = b.$times(b);
            e = e.$greater$greater(1);
            b = var4;
            t = var10000;
         } else {
            Natural var10001 = b.$times(b);
            e = e.$greater$greater(1);
            b = var10001;
            t = t;
         }
      }

      return t;
   }

   private final Natural _pow$2(final Natural t, final Natural b, final int e) {
      while(!UInt$.MODULE$.$eq$eq$extension(e, UInt$.MODULE$.apply(0))) {
         if (UInt$.MODULE$.$eq$eq$extension(UInt$.MODULE$.$amp$extension(e, UInt$.MODULE$.apply(1)), UInt$.MODULE$.apply(1))) {
            Natural var10000 = t.$times(b);
            Natural var4 = b.$times(b);
            e = UInt$.MODULE$.$greater$greater$extension(e, 1);
            b = var4;
            t = var10000;
         } else {
            Natural var10001 = b.$times(b);
            e = UInt$.MODULE$.$greater$greater$extension(e, 1);
            b = var10001;
            t = t;
         }
      }

      return t;
   }

   private static final Natural recur$14(final Natural next, final long carry, final int m$1) {
      Object var4;
      if (next instanceof End) {
         End var6 = (End)next;
         int d = var6.d();
         var4 = Natural$.MODULE$.apply(UInt$.MODULE$.toLong$extension(d) << m$1 | carry);
      } else {
         if (!(next instanceof Digit)) {
            throw new MatchError(next);
         }

         Digit var8 = (Digit)next;
         int d = var8.d();
         Natural tail = var8.tl();
         long t = UInt$.MODULE$.toLong$extension(d) << m$1 | carry;
         var4 = new Digit(UInt$.MODULE$.apply(t), recur$14(tail, t >> 32, m$1));
      }

      return (Natural)var4;
   }

   // $FF: synthetic method
   public static final Digit $anonfun$$less$less$1(final Natural n, final int x$2) {
      return new Digit(UInt$.MODULE$.apply(0), n);
   }

   private final Natural recur$15(final Natural next, final int n) {
      while(true) {
         Object var10000;
         if (n <= 0) {
            var10000 = next;
         } else {
            if (!(next instanceof End)) {
               if (next instanceof Digit) {
                  Digit var6 = (Digit)next;
                  Natural tail = var6.tl();
                  --n;
                  next = tail;
                  continue;
               }

               throw new MatchError(next);
            }

            End var4 = new End(UInt$.MODULE$.apply(0));
            var10000 = var4;
         }

         return (Natural)var10000;
      }
   }

   private static final Natural recur$16(final Natural next, final long carry, final int m$2) {
      Object var4;
      if (next instanceof End) {
         End var6 = (End)next;
         int d = var6.d();
         var4 = Natural$.MODULE$.apply(UInt$.MODULE$.toLong$extension(d) >> m$2 | carry);
      } else {
         if (!(next instanceof Digit)) {
            throw new MatchError(next);
         }

         Digit var8 = (Digit)next;
         int d = var8.d();
         Natural tail = var8.tl();
         long t = (UInt$.MODULE$.toLong$extension(d) | carry) << 32 - m$2;
         var4 = new Digit(UInt$.MODULE$.apply(t >> 32), recur$16(tail, t & 4294967295L, m$2));
      }

      return (Natural)var4;
   }

   private static final Natural and$1(final Natural lhs, final Natural rhs) {
      Object var2;
      if (lhs instanceof End) {
         End var6 = (End)lhs;
         int ld = var6.d();
         End var4;
         if (rhs instanceof End) {
            End var9 = (End)rhs;
            int rd = var9.d();
            var4 = new End(UInt$.MODULE$.$amp$extension(ld, rd));
         } else {
            if (!(rhs instanceof Digit)) {
               throw new MatchError(rhs);
            }

            Digit var11 = (Digit)rhs;
            int rd = var11.d();
            var4 = new End(UInt$.MODULE$.$amp$extension(ld, rd));
         }

         var2 = var4;
      } else {
         if (!(lhs instanceof Digit)) {
            throw new MatchError(lhs);
         }

         Digit var13 = (Digit)lhs;
         int ld = var13.d();
         Natural ltail = var13.tl();
         Object var3;
         if (rhs instanceof End) {
            End var17 = (End)rhs;
            int rd = var17.d();
            var3 = new End(UInt$.MODULE$.$amp$extension(ld, rd));
         } else {
            if (!(rhs instanceof Digit)) {
               throw new MatchError(rhs);
            }

            Digit var19 = (Digit)rhs;
            int rd = var19.d();
            Natural rtail = var19.tl();
            var3 = new Digit(UInt$.MODULE$.$amp$extension(ld, rd), and$1(ltail, rtail));
         }

         var2 = var3;
      }

      return (Natural)var2;
   }

   private static final Natural xor$1(final Natural lhs, final Natural rhs) {
      Object var2;
      if (lhs instanceof End) {
         End var6 = (End)lhs;
         int ld = var6.d();
         Object var4;
         if (rhs instanceof End) {
            End var9 = (End)rhs;
            int rd = var9.d();
            var4 = new End(UInt$.MODULE$.$up$extension(ld, rd));
         } else {
            if (!(rhs instanceof Digit)) {
               throw new MatchError(rhs);
            }

            Digit var11 = (Digit)rhs;
            int rd = var11.d();
            Natural rtail = var11.tl();
            var4 = new Digit(UInt$.MODULE$.$up$extension(ld, rd), rtail);
         }

         var2 = var4;
      } else {
         if (!(lhs instanceof Digit)) {
            throw new MatchError(lhs);
         }

         Digit var14 = (Digit)lhs;
         int ld = var14.d();
         Natural ltail = var14.tl();
         Digit var3;
         if (rhs instanceof End) {
            End var18 = (End)rhs;
            int rd = var18.d();
            var3 = new Digit(UInt$.MODULE$.$up$extension(ld, rd), ltail);
         } else {
            if (!(rhs instanceof Digit)) {
               throw new MatchError(rhs);
            }

            Digit var20 = (Digit)rhs;
            int rd = var20.d();
            Natural rtail = var20.tl();
            var3 = new Digit(UInt$.MODULE$.$up$extension(ld, rd), ltail.$up(rtail));
         }

         var2 = var3;
      }

      return (Natural)var2;
   }

   private final boolean recur$17(final Natural next, final boolean sofar, final Function2 f$2, final boolean a$2) {
      while(!(next instanceof End)) {
         if (!(next instanceof Digit)) {
            throw new MatchError(next);
         }

         Digit var10 = (Digit)next;
         int d = var10.d();
         Natural tail = var10.tl();
         sofar = BoxesRunTime.unboxToBoolean(f$2.apply(BoxesRunTime.boxToBoolean(a$2), new UInt(d)));
         next = tail;
      }

      End var8 = (End)next;
      int d = var8.d();
      boolean var6 = BoxesRunTime.unboxToBoolean(f$2.apply(BoxesRunTime.boxToBoolean(a$2), new UInt(d)));
      return var6;
   }

   private final byte recur$18(final Natural next, final byte sofar, final Function2 f$3, final byte a$3) {
      while(!(next instanceof End)) {
         if (!(next instanceof Digit)) {
            throw new MatchError(next);
         }

         Digit var10 = (Digit)next;
         int d = var10.d();
         Natural tail = var10.tl();
         sofar = BoxesRunTime.unboxToByte(f$3.apply(BoxesRunTime.boxToByte(a$3), new UInt(d)));
         next = tail;
      }

      End var8 = (End)next;
      int d = var8.d();
      byte var6 = BoxesRunTime.unboxToByte(f$3.apply(BoxesRunTime.boxToByte(a$3), new UInt(d)));
      return var6;
   }

   private final char recur$19(final Natural next, final char sofar, final Function2 f$4, final char a$4) {
      while(!(next instanceof End)) {
         if (!(next instanceof Digit)) {
            throw new MatchError(next);
         }

         Digit var10 = (Digit)next;
         int d = var10.d();
         Natural tail = var10.tl();
         sofar = BoxesRunTime.unboxToChar(f$4.apply(BoxesRunTime.boxToCharacter(a$4), new UInt(d)));
         next = tail;
      }

      End var8 = (End)next;
      int d = var8.d();
      char var6 = BoxesRunTime.unboxToChar(f$4.apply(BoxesRunTime.boxToCharacter(a$4), new UInt(d)));
      return var6;
   }

   private final double recur$20(final Natural next, final double sofar, final Function2 f$5, final double a$5) {
      while(!(next instanceof End)) {
         if (!(next instanceof Digit)) {
            throw new MatchError(next);
         }

         Digit var13 = (Digit)next;
         int d = var13.d();
         Natural tail = var13.tl();
         sofar = BoxesRunTime.unboxToDouble(f$5.apply(BoxesRunTime.boxToDouble(a$5), new UInt(d)));
         next = tail;
      }

      End var11 = (End)next;
      int d = var11.d();
      double var8 = BoxesRunTime.unboxToDouble(f$5.apply(BoxesRunTime.boxToDouble(a$5), new UInt(d)));
      return var8;
   }

   private final float recur$21(final Natural next, final float sofar, final Function2 f$6, final float a$6) {
      while(!(next instanceof End)) {
         if (!(next instanceof Digit)) {
            throw new MatchError(next);
         }

         Digit var10 = (Digit)next;
         int d = var10.d();
         Natural tail = var10.tl();
         sofar = BoxesRunTime.unboxToFloat(f$6.apply(BoxesRunTime.boxToFloat(a$6), new UInt(d)));
         next = tail;
      }

      End var8 = (End)next;
      int d = var8.d();
      float var6 = BoxesRunTime.unboxToFloat(f$6.apply(BoxesRunTime.boxToFloat(a$6), new UInt(d)));
      return var6;
   }

   private final int recur$22(final Natural next, final int sofar, final Function2 f$7, final int a$7) {
      while(!(next instanceof End)) {
         if (!(next instanceof Digit)) {
            throw new MatchError(next);
         }

         Digit var10 = (Digit)next;
         int d = var10.d();
         Natural tail = var10.tl();
         sofar = BoxesRunTime.unboxToInt(f$7.apply(BoxesRunTime.boxToInteger(a$7), new UInt(d)));
         next = tail;
      }

      End var8 = (End)next;
      int d = var8.d();
      int var6 = BoxesRunTime.unboxToInt(f$7.apply(BoxesRunTime.boxToInteger(a$7), new UInt(d)));
      return var6;
   }

   private final long recur$23(final Natural next, final long sofar, final Function2 f$8, final long a$8) {
      while(!(next instanceof End)) {
         if (!(next instanceof Digit)) {
            throw new MatchError(next);
         }

         Digit var13 = (Digit)next;
         int d = var13.d();
         Natural tail = var13.tl();
         sofar = BoxesRunTime.unboxToLong(f$8.apply(BoxesRunTime.boxToLong(a$8), new UInt(d)));
         next = tail;
      }

      End var11 = (End)next;
      int d = var11.d();
      long var8 = BoxesRunTime.unboxToLong(f$8.apply(BoxesRunTime.boxToLong(a$8), new UInt(d)));
      return var8;
   }

   private final short recur$24(final Natural next, final short sofar, final Function2 f$9, final short a$9) {
      while(!(next instanceof End)) {
         if (!(next instanceof Digit)) {
            throw new MatchError(next);
         }

         Digit var10 = (Digit)next;
         int d = var10.d();
         Natural tail = var10.tl();
         sofar = BoxesRunTime.unboxToShort(f$9.apply(BoxesRunTime.boxToShort(a$9), new UInt(d)));
         next = tail;
      }

      End var8 = (End)next;
      int d = var8.d();
      short var6 = BoxesRunTime.unboxToShort(f$9.apply(BoxesRunTime.boxToShort(a$9), new UInt(d)));
      return var6;
   }

   private final void recur$25(final Natural next, final BoxedUnit sofar, final Function2 f$10, final BoxedUnit a$10) {
      while(!(next instanceof End)) {
         if (!(next instanceof Digit)) {
            throw new MatchError(next);
         }

         Digit var10 = (Digit)next;
         int d = var10.d();
         Natural tail = var10.tl();
         sofar = (BoxedUnit)f$10.apply(a$10, new UInt(d));
         next = tail;
      }

      End var8 = (End)next;
      int d = var8.d();
      BoxedUnit var6 = (BoxedUnit)f$10.apply(a$10, new UInt(d));
      BoxedUnit var10000 = BoxedUnit.UNIT;
   }

   public Natural() {
      ScalaNumericAnyConversions.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class Digit extends Natural implements Product {
      private static final long serialVersionUID = 0L;
      private final int d;
      private final Natural tl;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public int d() {
         return this.d;
      }

      public Natural tl() {
         return this.tl;
      }

      public int digit() {
         return this.d();
      }

      public Natural tail() {
         return this.tl();
      }

      public Natural $plus(final int n) {
         Digit var10000;
         if (UInt$.MODULE$.$eq$eq$extension(n, UInt$.MODULE$.apply(0))) {
            var10000 = this;
         } else {
            long t = UInt$.MODULE$.toLong$extension(this.d()) + UInt$.MODULE$.toLong$extension(n);
            var10000 = new Digit(UInt$.MODULE$.apply(t), this.tail().$plus(UInt$.MODULE$.apply(t >> 32)));
         }

         return var10000;
      }

      public Natural $minus(final int n) {
         Digit var10000;
         if (UInt$.MODULE$.$eq$eq$extension(n, UInt$.MODULE$.apply(0))) {
            var10000 = this;
         } else {
            long t = UInt$.MODULE$.toLong$extension(this.d()) - UInt$.MODULE$.toLong$extension(n);
            var10000 = new Digit(UInt$.MODULE$.apply(t), this.tail().$minus(UInt$.MODULE$.apply(-(t >> 32))));
         }

         return var10000;
      }

      public Natural $times(final int n) {
         return (Natural)(UInt$.MODULE$.$eq$eq$extension(n, UInt$.MODULE$.apply(0)) ? new End(n) : (UInt$.MODULE$.$eq$eq$extension(n, UInt$.MODULE$.apply(1)) ? this : Natural$.MODULE$.apply(UInt$.MODULE$.toLong$extension(this.d()) * UInt$.MODULE$.toLong$extension(n)).$plus((Natural)(new Digit(UInt$.MODULE$.apply(0), this.tl().$times(n))))));
      }

      public Natural $div(final int n) {
         return (Natural)this.$div$percent(n)._1();
      }

      public Natural $percent(final int n) {
         return (Natural)this.$div$percent(n)._2();
      }

      public Tuple2 $div$percent(final int n) {
         if (UInt$.MODULE$.$eq$eq$extension(n, UInt$.MODULE$.apply(0))) {
            throw new IllegalArgumentException("/ by zero");
         } else {
            Tuple2 var10000;
            if (UInt$.MODULE$.$eq$eq$extension(n, UInt$.MODULE$.apply(1))) {
               var10000 = new Tuple2(this, Natural$.MODULE$.apply((Seq)scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new UInt[]{new UInt(UInt$.MODULE$.apply(0))})));
            } else {
               Natural var3 = this.reversed();
               if (!(var3 instanceof Digit)) {
                  throw new IllegalArgumentException("bug in reversed");
               }

               Digit var4 = (Digit)var3;
               int d = var4.d();
               Natural tail = var4.tl();
               int q = UInt$.MODULE$.$div$extension(d, n);
               int r = UInt$.MODULE$.$percent$extension(d, n);
               Tuple2 var2 = this.recur$26(tail, r, new End(q), n);
               var10000 = var2;
            }

            return var10000;
         }
      }

      public Digit copy(final int d, final Natural tl) {
         return new Digit(d, tl);
      }

      public int copy$default$1() {
         return this.d();
      }

      public Natural copy$default$2() {
         return this.tl();
      }

      public String productPrefix() {
         return "Digit";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = new UInt(this.d());
               break;
            case 1:
               var10000 = this.tl();
               break;
            default:
               var10000 = Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Digit;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "d";
               break;
            case 1:
               var10000 = "tl";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
      }

      private final Tuple2 recur$26(final Natural next, final int rem, final Natural sofar, final int n$1) {
         while(true) {
            long t = ULong$.MODULE$.$plus$extension(ULong$.MODULE$.apply(UInt$.MODULE$.toLong$extension(rem) << 32), ULong$.MODULE$.apply(UInt$.MODULE$.toLong$extension(next.digit())));
            long q = ULong$.MODULE$.toLong$extension(ULong$.MODULE$.$div$extension(t, ULong$.MODULE$.apply(UInt$.MODULE$.toLong$extension(n$1))));
            long r = ULong$.MODULE$.toLong$extension(ULong$.MODULE$.$percent$extension(t, ULong$.MODULE$.apply(UInt$.MODULE$.toLong$extension(n$1))));
            if (next instanceof End) {
               Tuple2 var6 = new Tuple2(new Digit(UInt$.MODULE$.apply(q), sofar), new End(UInt$.MODULE$.apply(r)));
               return var6;
            }

            if (!(next instanceof Digit)) {
               throw new MatchError(next);
            }

            Digit var14 = (Digit)next;
            Natural tail = var14.tl();
            int var10001 = UInt$.MODULE$.apply(r);
            sofar = new Digit(UInt$.MODULE$.apply(q), sofar);
            rem = var10001;
            next = tail;
         }
      }

      public Digit(final int d, final Natural tl) {
         this.d = d;
         this.tl = tl;
         Product.$init$(this);
      }
   }

   public static class Digit$ extends AbstractFunction2 implements Serializable {
      public static final Digit$ MODULE$ = new Digit$();

      public final String toString() {
         return "Digit";
      }

      public Digit apply(final int d, final Natural tl) {
         return new Digit(d, tl);
      }

      public Option unapply(final Digit x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(new UInt(x$0.d()), x$0.tl())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Digit$.class);
      }
   }

   public static class End extends Natural implements Product {
      private static final long serialVersionUID = 0L;
      private final int d;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public int d() {
         return this.d;
      }

      public int digit() {
         return this.d();
      }

      public Natural $plus(final int n) {
         Object var10000;
         if (UInt$.MODULE$.$eq$eq$extension(n, UInt$.MODULE$.apply(0))) {
            var10000 = this;
         } else {
            long t = UInt$.MODULE$.toLong$extension(this.d()) + UInt$.MODULE$.toLong$extension(n);
            var10000 = t <= 4294967295L ? new End(UInt$.MODULE$.apply(t)) : new Digit(UInt$.MODULE$.apply(t), new End(UInt$.MODULE$.apply(1)));
         }

         return (Natural)var10000;
      }

      public Natural $minus(final int n) {
         End var10000;
         if (UInt$.MODULE$.$eq$eq$extension(n, UInt$.MODULE$.apply(0))) {
            var10000 = this;
         } else {
            long t = UInt$.MODULE$.toLong$extension(this.d()) - UInt$.MODULE$.toLong$extension(n);
            if (t < 0L) {
               throw new IllegalArgumentException(scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("illegal subtraction: %s %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{this, new UInt(n)})));
            }

            var10000 = new End(UInt$.MODULE$.apply((int)t));
         }

         return var10000;
      }

      public Natural $times(final int n) {
         return (Natural)(UInt$.MODULE$.$eq$eq$extension(n, UInt$.MODULE$.apply(0)) ? new End(n) : (UInt$.MODULE$.$eq$eq$extension(n, UInt$.MODULE$.apply(1)) ? this : Natural$.MODULE$.apply(UInt$.MODULE$.toLong$extension(this.d()) * UInt$.MODULE$.toLong$extension(n))));
      }

      public Natural $div(final int n) {
         if (UInt$.MODULE$.$eq$eq$extension(n, UInt$.MODULE$.apply(0))) {
            throw new IllegalArgumentException("/ by zero");
         } else {
            return new End(UInt$.MODULE$.$div$extension(this.d(), n));
         }
      }

      public Natural $percent(final int n) {
         if (UInt$.MODULE$.$eq$eq$extension(n, UInt$.MODULE$.apply(0))) {
            throw new IllegalArgumentException("/ by zero");
         } else {
            return new End(UInt$.MODULE$.$percent$extension(this.d(), n));
         }
      }

      public Tuple2 $div$percent(final int n) {
         return new Tuple2(this.$div(n), this.$percent(n));
      }

      public End copy(final int d) {
         return new End(d);
      }

      public int copy$default$1() {
         return this.d();
      }

      public String productPrefix() {
         return "End";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = new UInt(this.d());
               break;
            default:
               var10000 = Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof End;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "d";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
      }

      public End(final int d) {
         this.d = d;
         Product.$init$(this);
      }
   }

   public static class End$ extends AbstractFunction1 implements Serializable {
      public static final End$ MODULE$ = new End$();

      public final String toString() {
         return "End";
      }

      public End apply(final int d) {
         return new End(d);
      }

      public Option unapply(final End x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new UInt(x$0.d())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(End$.class);
      }
   }
}
