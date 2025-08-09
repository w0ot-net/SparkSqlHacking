package spire.math.poly;

import algebra.ring.Field;
import algebra.ring.Rig;
import algebra.ring.Ring;
import algebra.ring.Rng;
import algebra.ring.Semiring;
import algebra.ring.Signed;
import cats.kernel.Eq;
import cats.kernel.Order;
import java.io.Serializable;
import java.util.Arrays;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Product;
import scala.Tuple2;
import scala.collection.BufferedIterator;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import spire.math.Polynomial;
import spire.math.Polynomial$;
import spire.math.package$;
import spire.syntax.LiteralIntMultiplicativeSemigroupOps$;
import spire.syntax.package;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015Ee\u0001B!C\u0001&C\u0001\u0002\u001f\u0001\u0003\u0016\u0004%\t!\u001f\u0005\n\u0003\u0003\u0001!\u0011#Q\u0001\niD!\"a\u0001\u0001\u0005+\u0007I\u0011AA\u0003\u0011)\tI\u0001\u0001B\tB\u0003%\u0011q\u0001\u0005\u000b\u0003\u0017\u0001!Q1A\u0005\u0004\u00055\u0001BCA\u000f\u0001\t\u0005\t\u0015!\u0003\u0002\u0010!A\u0011q\u0004\u0001\u0005\u0002\u0019\u000b\t\u0003C\u0004\u00020\u0001!\t!!\r\t\u000f\u0005}\u0003\u0001\"\u0001\u0002b!9\u0011q\r\u0001\u0005\u0002\u0005%\u0004bBAB\u0001\u0011\u0005\u0013Q\u0011\u0005\b\u0003/\u0003A\u0011AAM\u0011\u001d\tY\n\u0001C\u0001\u0003;3a!a+\u0001\u0001\u00055\u0006bBA\u0010\u001d\u0011\u0005\u0011q\u0016\u0005\b\u0003ks\u0001\u0015)\u0003~\u0011!\t9L\u0004Q\u0005\n\u0005e\u0006bBA^\u001d\u0011\u0005\u0011Q\u0018\u0005\b\u0003\u000btA\u0011IAd\u0011\u001d\tI\r\u0001C\u0001\u0003\u0017Dq!a4\u0001\t\u0003\t\t\u000eC\u0004\u0002\\\u0002!\t!!8\t\u000f\u0005\u0005\b\u0001\"\u0001\u0002d\"9\u0011Q\u001e\u0001\u0005\u000e\u0005=\bbBA}\u0001\u00115\u00111 \u0005\b\u0003s\u0004AQ\u0002B\u000e\u0011\u001d\u0011)\u0003\u0001C\u0001\u0003{CqAa\n\u0001\t\u0003\u0011I\u0003C\u0004\u00032\u0001!\tAa\r\t\u000f\t}\u0002\u0001\"\u0001\u0003B!9!q\n\u0001\u0005\u0002\tE\u0003b\u0002B.\u0001\u0011\u0005!Q\f\u0005\b\u0005S\u0002A\u0011\u0001B6\u0011\u001d\u0011)\b\u0001C\u0001\u0005oB\u0011Ba!\u0001\u0003\u0003%\tA!\"\t\u0013\t\u0005\u0006!%A\u0005\u0002\t\r\u0006\"\u0003B`\u0001E\u0005I\u0011\u0001Ba\u0011%\u0011y\rAA\u0001\n\u0003\u0012\t\u000eC\u0005\u0003d\u0002\t\t\u0011\"\u0001\u0002\u001a\"I!Q\u001d\u0001\u0002\u0002\u0013\u0005!q\u001d\u0005\n\u0005[\u0004\u0011\u0011!C!\u0005_D\u0011Ba?\u0001\u0003\u0003%\tA!@\t\u0013\r\u0005\u0001!!A\u0005B\r\rqaBB\u0004\u0005\"\u00051\u0011\u0002\u0004\u0007\u0003\nC\taa\u0003\t\u000f\u0005}Q\u0006\"\u0001\u0004\u0018!A1\u0011D\u0017\u0005\u0006\u0011\u001bY\u0002\u0003\u0005\u0004D5\")\u0001RB#\u0011\u001d\u00119#\fC\u0003\u0007_Bqaa(.\t\u0013\u0019\t\u000bC\u0004\u0003(5\")aa.\t\u000f\t\u001dR\u0006\"\u0002\u0004n\"9AQC\u0017\u0005\u0006\u0011]\u0001b\u0002C\u001d[\u00115A1\b\u0005\b\tOjCQ\u0002C5\u0011\u001d!)*\fC\u0007\t/C\u0011\u0002b-.#\u0003%i\u0001\".\t\u0013\u0011\rW&%A\u0005\u000e\u0011\u0015\u0007b\u0002Ch[\u00115A\u0011\u001b\u0005\b\tglCQ\u0002C{\u0011!)Y\"\fC\u0003\t\u0016u\u0001\"\u0003B\u0014[\u0005\u0005I\u0011QC&\u0011%)9'LA\u0001\n\u0003+I\u0007C\u0005\u0006\b6\n\t\u0011\"\u0003\u0006\n\nQ\u0001k\u001c7z'B\f'o]3\u000b\u0005\r#\u0015\u0001\u00029pYfT!!\u0012$\u0002\t5\fG\u000f\u001b\u0006\u0002\u000f\u0006)1\u000f]5sK\u000e\u0001QC\u0001&X'\u0015\u00011*\u00158r!\tau*D\u0001N\u0015\u0005q\u0015!B:dC2\f\u0017B\u0001)N\u0005\u0019\te.\u001f*fMB\u0019!kU+\u000e\u0003\u0011K!\u0001\u0016#\u0003\u0015A{G.\u001f8p[&\fG\u000e\u0005\u0002W/2\u0001A!\u0003-\u0001A\u0003\u0005\tQ1\u0001Z\u0005\u0005\u0019\u0015C\u0001.^!\ta5,\u0003\u0002]\u001b\n9aj\u001c;iS:<\u0007C\u0001'_\u0013\tyVJA\u0002B]fD3aV1e!\ta%-\u0003\u0002d\u001b\nY1\u000f]3dS\u0006d\u0017N_3ec\u0015\u0019SM\u001a5h\u001d\tae-\u0003\u0002h\u001b\u00061Ai\\;cY\u0016\fD\u0001J5n\u001d:\u0011!.\\\u0007\u0002W*\u0011A\u000eS\u0001\u0007yI|w\u000e\u001e \n\u00039\u0003\"\u0001T8\n\u0005Al%a\u0002)s_\u0012,8\r\u001e\t\u0003eVt!![:\n\u0005Ql\u0015a\u00029bG.\fw-Z\u0005\u0003m^\u0014AbU3sS\u0006d\u0017N_1cY\u0016T!\u0001^'\u0002\u0007\u0015D\b/F\u0001{!\ra50`\u0005\u0003y6\u0013Q!\u0011:sCf\u0004\"\u0001\u0014@\n\u0005}l%aA%oi\u0006!Q\r\u001f9!\u0003\u0015\u0019w.\u001a4g+\t\t9\u0001E\u0002MwV\u000baaY8fM\u001a\u0004\u0013AA2u+\t\ty\u0001E\u0003\u0002\u0012\u0005]QK\u0004\u0003\u0002\u0014\u0005UQ\"\u0001$\n\u0005Q4\u0015\u0002BA\r\u00037\u0011\u0001b\u00117bgN$\u0016m\u001a\u0006\u0003i\u001a\u000b1a\u0019;!\u0003\u0019a\u0014N\\5u}Q1\u00111EA\u0016\u0003[!B!!\n\u0002*A!\u0011q\u0005\u0001V\u001b\u0005\u0011\u0005bBA\u0006\u000f\u0001\u000f\u0011q\u0002\u0005\u0006q\u001e\u0001\rA\u001f\u0005\b\u0003\u00079\u0001\u0019AA\u0004\u0003\u001d!x\u000eR3og\u0016$b!a\r\u0002:\u0005U\u0003#BA\u0014\u0003k)\u0016bAA\u001c\u0005\nI\u0001k\u001c7z\t\u0016t7/\u001a\u0005\b\u0003wA\u00019AA\u001f\u0003\u0011\u0011\u0018N\\4\u0011\u000b\u0005}\u0012qJ+\u000f\t\u0005\u0005\u00131\n\b\u0005\u0003\u0007\n9ED\u0002k\u0003\u000bJ\u0011aR\u0005\u0004\u0003\u00132\u0015aB1mO\u0016\u0014'/Y\u0005\u0004i\u00065#bAA%\r&!\u0011\u0011KA*\u0005!\u0019V-\\5sS:<'b\u0001;\u0002N!9\u0011q\u000b\u0005A\u0004\u0005e\u0013AA3r!\u0015\ty$a\u0017V\u0013\u0011\ti&a\u0015\u0003\u0005\u0015\u000b\u0018\u0001\u0003;p'B\f'o]3\u0015\r\u0005\u0015\u00121MA3\u0011\u001d\tY$\u0003a\u0002\u0003{Aq!a\u0016\n\u0001\b\tI&A\u0004g_J,\u0017m\u00195\u0016\t\u0005-\u0014q\u0010\u000b\u0005\u0003[\n\u0019\bE\u0002M\u0003_J1!!\u001dN\u0005\u0011)f.\u001b;\t\u000f\u0005U$\u00021\u0001\u0002x\u0005\ta\rE\u0004M\u0003sjX+! \n\u0007\u0005mTJA\u0005Gk:\u001cG/[8oeA\u0019a+a \u0005\r\u0005\u0005%B1\u0001Z\u0005\u0005)\u0016A\u00044pe\u0016\f7\r\u001b(p]j+'o\\\u000b\u0005\u0003\u000f\u000b)\n\u0006\u0003\u0002\n\u0006=ECBA7\u0003\u0017\u000bi\tC\u0004\u0002<-\u0001\u001d!!\u0010\t\u000f\u0005]3\u0002q\u0001\u0002Z!9\u0011QO\u0006A\u0002\u0005E\u0005c\u0002'\u0002zu,\u00161\u0013\t\u0004-\u0006UEABAA\u0017\t\u0007\u0011,\u0001\u0004eK\u001e\u0014X-Z\u000b\u0002{\u0006iA/\u001a:ng&#XM]1u_J,\"!a(\u0011\u000bI\f\t+!*\n\u0007\u0005\rvO\u0001\u0005Ji\u0016\u0014\u0018\r^8s!\u0015\t9#a*V\u0013\r\tIK\u0011\u0002\u0005)\u0016\u0014XN\u0001\u0007UKJl\u0017\n^3sCR|'o\u0005\u0003\u000f\u0017\u0006}ECAAY!\r\t\u0019LD\u0007\u0002\u0001\u0005\t\u0011.\u0001\u0005gS:$g*\u001a=u)\t\ti'A\u0004iCNtU\r\u001f;\u0016\u0005\u0005}\u0006c\u0001'\u0002B&\u0019\u00111Y'\u0003\u000f\t{w\u000e\\3b]\u0006!a.\u001a=u)\t\t)+A\u0006d_\u00164gm]!se\u0006LH\u0003BA\u0004\u0003\u001bDq!a\u000f\u0015\u0001\b\ti$A\u0002oi\"$B!a5\u0002XR\u0019Q+!6\t\u000f\u0005mR\u0003q\u0001\u0002>!1\u0011\u0011\\\u000bA\u0002u\f\u0011A\\\u0001\u0012[\u0006DxJ\u001d3feR+'/\\\"pK\u001a4GcA+\u0002`\"9\u00111\b\fA\u0004\u0005u\u0012\u0001\u0003:fIV\u001cG/^7\u0015\u000fE\u000b)/!;\u0002l\"9\u0011q]\fA\u0004\u0005e\u0013!A3\t\u000f\u0005mr\u0003q\u0001\u0002>!9\u00111B\fA\u0004\u0005=\u0011aB3ya\nKGo\u001d\u000b\u0005\u0003c\f)\u0010\u0006\u0003\u0002\b\u0005M\bbBA\u001e1\u0001\u000f\u0011Q\b\u0005\u0007\u0003oD\u0002\u0019A+\u0002\u0003a\fqAZ1ti\u0016C\b\u000f\u0006\u0006\u0002~\n\u0005!Q\u0001B\u0004\u0005\u0013!2!VA\u0000\u0011\u001d\tY$\u0007a\u0002\u0003{AqAa\u0001\u001a\u0001\u0004\t9!\u0001\u0003cSR\u001c\bBBAt3\u0001\u0007Q\u0010\u0003\u0004\u00026f\u0001\r! \u0005\u0007\u0005\u0017I\u0002\u0019A+\u0002\u0007\u0005\u001c7\rK\u0002\u001a\u0005\u001f\u0001BA!\u0005\u0003\u00185\u0011!1\u0003\u0006\u0004\u0005+i\u0015AC1o]>$\u0018\r^5p]&!!\u0011\u0004B\n\u0005\u001d!\u0018-\u001b7sK\u000e$bA!\b\u0003\"\t\rBcA+\u0003 !9\u00111\b\u000eA\u0004\u0005u\u0002b\u0002B\u00025\u0001\u0007\u0011q\u0001\u0005\u0007\u0003OT\u0002\u0019A?\u0002\r%\u001c(,\u001a:p\u0003\u0015\t\u0007\u000f\u001d7z)\u0011\u0011YCa\f\u0015\u0007U\u0013i\u0003C\u0004\u0002<q\u0001\u001d!!\u0010\t\r\u0005]H\u00041\u0001V\u0003)!WM]5wCRLg/\u001a\u000b\u0006#\nU\"Q\b\u0005\b\u0003wi\u00029\u0001B\u001c!\u0015\tyD!\u000fV\u0013\u0011\u0011Y$a\u0015\u0003\tIKgn\u001a\u0005\b\u0003/j\u00029AA-\u0003!Ig\u000e^3he\u0006dG#B)\u0003D\t5\u0003b\u0002B#=\u0001\u000f!qI\u0001\u0006M&,G\u000e\u001a\t\u0006\u0003\u007f\u0011I%V\u0005\u0005\u0005\u0017\n\u0019FA\u0003GS\u0016dG\rC\u0004\u0002Xy\u0001\u001d!!\u0017\u0002\u0019Ut\u0017M]=`I5Lg.^:\u0015\u0007E\u0013\u0019\u0006C\u0004\u0002<}\u0001\u001dA!\u0016\u0011\u000b\u0005}\"qK+\n\t\te\u00131\u000b\u0002\u0004%:<\u0017!\u0002\u0013qYV\u001cH\u0003\u0002B0\u0005K\"R!\u0015B1\u0005GBq!a\u000f!\u0001\b\ti\u0004C\u0004\u0002X\u0001\u0002\u001d!!\u0017\t\r\t\u001d\u0004\u00051\u0001R\u0003\u0011\u0011\bn\u001d\u0019\u0002\r\u0011\"\u0018.\\3t)\u0011\u0011iGa\u001d\u0015\u000bE\u0013yG!\u001d\t\u000f\u0005m\u0012\u0005q\u0001\u0002>!9\u0011qK\u0011A\u0004\u0005e\u0003B\u0002B4C\u0001\u0007\u0011+\u0001\u0007%i&lWm\u001d\u0013d_2|g\u000e\u0006\u0003\u0003z\t}D#B)\u0003|\tu\u0004bBA\u001eE\u0001\u000f\u0011Q\b\u0005\b\u0003/\u0012\u00039AA-\u0011\u0019\u0011\tI\ta\u0001+\u0006\t1.\u0001\u0003d_BLX\u0003\u0002BD\u0005\u001f#bA!#\u0003\u001c\nuE\u0003\u0002BF\u0005/\u0003R!a\n\u0001\u0005\u001b\u00032A\u0016BH\t%A6\u0005)A\u0001\u0002\u000b\u0007\u0011\fK\u0003\u0003\u0010\u0006\u0014\u0019*\r\u0004$K\u001a\u0014)jZ\u0019\u0005I%lg\nC\u0004\u0002\f\r\u0002\u001dA!'\u0011\r\u0005E\u0011q\u0003BG\u0011\u001dA8\u0005%AA\u0002iD\u0011\"a\u0001$!\u0003\u0005\rAa(\u0011\t1[(QR\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0011\u0011)Ka.\u0016\u0005\t\u001d&f\u0001>\u0003*.\u0012!1\u0016\t\u0005\u0005[\u0013\u0019,\u0004\u0002\u00030*!!\u0011\u0017B\n\u0003%)hn\u00195fG.,G-\u0003\u0003\u00036\n=&!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0012I\u0001\f\nQ\u0001\u0002\u0003\u0015\r!\u0017\u0015\u0006\u0005o\u000b'1X\u0019\u0007G\u00154'QX42\t\u0011JWNT\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\u0011\u0011\u0019Ma2\u0016\u0005\t\u0015'\u0006BA\u0004\u0005S#\u0011\u0002W\u0013!\u0002\u0003\u0005)\u0019A-)\u000b\t\u001d\u0017Ma32\r\r*gM!4hc\u0011!\u0013.\u001c(\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\u0011\u0019\u000e\u0005\u0003\u0003V\n}WB\u0001Bl\u0015\u0011\u0011INa7\u0002\t1\fgn\u001a\u0006\u0003\u0005;\fAA[1wC&!!\u0011\u001dBl\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5us\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HcA/\u0003j\"A!1\u001e\u0015\u0002\u0002\u0003\u0007Q0A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0005c\u0004RAa=\u0003zvk!A!>\u000b\u0007\t]X*\u0001\u0006d_2dWm\u0019;j_:LA!a)\u0003v\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002@\n}\b\u0002\u0003BvU\u0005\u0005\t\u0019A/\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0005'\u001c)\u0001\u0003\u0005\u0003l.\n\t\u00111\u0001~\u0003)\u0001v\u000e\\=Ta\u0006\u00148/\u001a\t\u0004\u0003Oi3\u0003B\u0017L\u0007\u001b\u0001Baa\u0004\u0004\u00165\u00111\u0011\u0003\u0006\u0005\u0007'\u0011Y.\u0001\u0002j_&\u0019ao!\u0005\u0015\u0005\r%\u0011\u0001\u00043f]N,'g\u001d9beN,W\u0003BB\u000f\u0007K!Baa\b\u0004@QA1\u0011EB\u0017\u0007g\u0019I\u0004E\u0003\u0002(\u0001\u0019\u0019\u0003E\u0002W\u0007K!\u0011\u0002W\u0018!\u0002\u0003\u0005)\u0019A-)\u000b\r\u0015\u0012m!\u000b2\r\r*gma\u000bhc\u0011!\u0013.\u001c(\t\u0013\r=r&!AA\u0004\rE\u0012AC3wS\u0012,gnY3%cA1\u0011qHA(\u0007GA\u0011b!\u000e0\u0003\u0003\u0005\u001daa\u000e\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$#\u0007\u0005\u0004\u0002@\u0005m31\u0005\u0005\n\u0007wy\u0013\u0011!a\u0002\u0007{\t!\"\u001a<jI\u0016t7-\u001a\u00134!\u0019\t\t\"a\u0006\u0004$!11i\fa\u0001\u0007\u0003\u0002b!a\n\u00026\r\r\u0012\u0001B:bM\u0016,Baa\u0012\u0004PQ11\u0011JB5\u0007W\"\u0002ba\u0013\u0004X\ru31\r\t\u0006\u0003O\u00011Q\n\t\u0004-\u000e=C!\u0003-1A\u0003\u0005\tQ1\u0001ZQ\u0015\u0019y%YB*c\u0019\u0019SMZB+OF\"A%[7O\u0011%\u0019I\u0006MA\u0001\u0002\b\u0019Y&\u0001\u0006fm&$WM\\2fIQ\u0002b!a\u0010\u0002P\r5\u0003\"CB0a\u0005\u0005\t9AB1\u0003))g/\u001b3f]\u000e,G%\u000e\t\u0007\u0003\u007f\tYf!\u0014\t\u0013\r\u0015\u0004'!AA\u0004\r\u001d\u0014AC3wS\u0012,gnY3%mA1\u0011\u0011CA\f\u0007\u001bBQ\u0001\u001f\u0019A\u0002iDq!a\u00011\u0001\u0004\u0019i\u0007\u0005\u0003Mw\u000e5S\u0003BB9\u0007s\"Baa\u001d\u0004\u0014RA1QOBA\u0007\u000f\u001bi\tE\u0003\u0002(\u0001\u00199\bE\u0002W\u0007s\"\u0011\u0002W\u0019!\u0002\u0003\u0005)\u0019A-)\u000b\re\u0014m! 2\r\r*gma hc\u0011!\u0013.\u001c(\t\u0013\r\r\u0015'!AA\u0004\r\u0015\u0015AC3wS\u0012,gnY3%oA1\u0011qHA(\u0007oB\u0011b!#2\u0003\u0003\u0005\u001daa#\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0003\b\u0005\u0004\u0002@\u0005m3q\u000f\u0005\n\u0007\u001f\u000b\u0014\u0011!a\u0002\u0007#\u000b!\"\u001a<jI\u0016t7-\u001a\u0013:!\u0019\t\t\"a\u0006\u0004x!91QS\u0019A\u0002\r]\u0015\u0001\u00023bi\u0006\u0004RA]BM\u0007;K1aa'x\u00051IE/\u001a:bE2,wJ\\2f!\u0019\t9#a*\u0004x\u00059!/\u001a<feN,W\u0003BBR\u0007[#B!!\u001c\u0004&\"91q\u0015\u001aA\u0002\r%\u0016aA1seB!Aj_BV!\r16Q\u0016\u0003\u000b\u0007_\u0013\u0004\u0015!A\u0001\u0006\u0004I&!A!)\u000b\r5\u0016ma-2\r\r*gm!.hc\u0011!\u0013.\u001c(\u0016\t\re6\u0011\u0019\u000b\u0005\u0007w\u001bY\u000e\u0006\u0005\u0004>\u000e%7qZBk!\u0015\t9\u0003AB`!\r16\u0011\u0019\u0003\n1N\u0002\u000b\u0011!AC\u0002eCSa!1b\u0007\u000b\fdaI3g\u0007\u000f<\u0017\u0007\u0002\u0013j[:C\u0011ba34\u0003\u0003\u0005\u001da!4\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007\r\t\u0007\u0003\u007f\tyea0\t\u0013\rE7'!AA\u0004\rM\u0017aC3wS\u0012,gnY3%cE\u0002b!a\u0010\u0002\\\r}\u0006\"CBlg\u0005\u0005\t9ABm\u0003-)g/\u001b3f]\u000e,G%\r\u001a\u0011\r\u0005E\u0011qCB`\u0011\u001d\u0019)j\ra\u0001\u0007;\u0004raa8\u0004hv\u001cyL\u0004\u0003\u0004b\u000e\r\bC\u00016N\u0013\r\u0019)/T\u0001\u0007!J,G-\u001a4\n\t\r%81\u001e\u0002\u0004\u001b\u0006\u0004(bABs\u001bV!1q^B|)\u0011\u0019\t\u0010\"\u0005\u0015\u0011\rM8q C\u0003\t\u0017\u0001R!a\n\u0001\u0007k\u00042AVB|\t%AF\u0007)A\u0001\u0002\u000b\u0007\u0011\fK\u0003\u0004x\u0006\u001cY0\r\u0004$K\u001a\u001cipZ\u0019\u0005I%lg\nC\u0005\u0005\u0002Q\n\t\u0011q\u0001\u0005\u0004\u0005YQM^5eK:\u001cW\rJ\u00194!\u0019\ty$a\u0014\u0004v\"IAq\u0001\u001b\u0002\u0002\u0003\u000fA\u0011B\u0001\fKZLG-\u001a8dK\u0012\nD\u0007\u0005\u0004\u0002@\u0005m3Q\u001f\u0005\n\t\u001b!\u0014\u0011!a\u0002\t\u001f\t1\"\u001a<jI\u0016t7-\u001a\u00132kA1\u0011\u0011CA\f\u0007kDaa\u0011\u001bA\u0002\u0011M\u0001\u0003\u0002*T\u0007k\fAA_3s_V!A\u0011\u0004C\u0010)!!Y\u0002b\n\u0005.\u0011M\u0002#BA\u0014\u0001\u0011u\u0001c\u0001,\u0005 \u0011I\u0001,\u000eQ\u0001\u0002\u0003\u0015\r!\u0017\u0015\u0006\t?\tG1E\u0019\u0007G\u00154GQE42\t\u0011JWN\u0014\u0005\n\tS)\u0014\u0011!a\u0002\tW\t1\"\u001a<jI\u0016t7-\u001a\u00132mA1\u0011qHA(\t;A\u0011\u0002b\f6\u0003\u0003\u0005\u001d\u0001\"\r\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013g\u000e\t\u0007\u0003\u007f\tY\u0006\"\b\t\u0013\u0011UR'!AA\u0004\u0011]\u0012aC3wS\u0012,gnY3%ca\u0002b!!\u0005\u0002\u0018\u0011u\u0011\u0001D7vYRL\u0007\u000f\\=UKJlW\u0003\u0002C\u001f\t\u000b\"\u0002\u0002b\u0010\u0005`\u0011\u0005DQ\r\u000b\t\t\u0003\"i\u0005b\u0015\u0005ZA)\u0011q\u0005\u0001\u0005DA\u0019a\u000b\"\u0012\u0005\u0013a3\u0004\u0015!A\u0001\u0006\u0004I\u0006&\u0002C#C\u0012%\u0013GB\u0012fM\u0012-s-\r\u0003%S6t\u0005\"\u0003C(m\u0005\u0005\t9\u0001C)\u0003-)g/\u001b3f]\u000e,G%M\u001d\u0011\r\u0005}\u0012q\nC\"\u0011%!)FNA\u0001\u0002\b!9&A\u0006fm&$WM\\2fII\u0002\u0004CBA \u00037\"\u0019\u0005C\u0005\u0005\\Y\n\t\u0011q\u0001\u0005^\u0005YQM^5eK:\u001cW\r\n\u001a2!\u0019\t\t\"a\u0006\u0005D!11I\u000ea\u0001\t\u0003Bq\u0001b\u00197\u0001\u0004!\u0019%A\u0001d\u0011\u0019\t9O\u000ea\u0001{\u0006qQ.\u001e7uSBd\u0017p\u00159beN,W\u0003\u0002C6\tg\"b\u0001\"\u001c\u0005\u000e\u0012EE\u0003\u0003C8\tw\"\t\tb\"\u0011\u000b\u0005\u001d\u0002\u0001\"\u001d\u0011\u0007Y#\u0019\bB\u0005Yo\u0001\u0006\t\u0011!b\u00013\"*A1O1\u0005xE21%\u001a4\u0005z\u001d\fD\u0001J5n\u001d\"IAQP\u001c\u0002\u0002\u0003\u000fAqP\u0001\fKZLG-\u001a8dK\u0012\u0012$\u0007\u0005\u0004\u0002@\u0005=C\u0011\u000f\u0005\n\t\u0007;\u0014\u0011!a\u0002\t\u000b\u000b1\"\u001a<jI\u0016t7-\u001a\u00133gA1\u0011qHA.\tcB\u0011\u0002\"#8\u0003\u0003\u0005\u001d\u0001b#\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$#\u0007\u000e\t\u0007\u0003#\t9\u0002\"\u001d\t\u000f\u0011=u\u00071\u0001\u0005p\u0005\u0019A\u000e[:\t\u000f\u0011Mu\u00071\u0001\u0005p\u0005\u0019!\u000f[:\u0002\u001b\r|WO\u001c;Tk6$VM]7t+\u0011!I\n\")\u0015\u0013u$Y\n\"+\u0005,\u0012=\u0006b\u0002CHq\u0001\u0007AQ\u0014\t\u0006\u0003O\u0001Aq\u0014\t\u0004-\u0012\u0005F!\u0003-9A\u0003\u0005\tQ1\u0001ZQ\u0015!\t+\u0019CSc\u0019\u0019SM\u001aCTOF\"A%[7O\u0011\u001d!\u0019\n\u000fa\u0001\t;C\u0001\u0002\",9!\u0003\u0005\r!`\u0001\bY>3gm]3u\u0011!!\t\f\u000fI\u0001\u0002\u0004i\u0018a\u0002:PM\u001a\u001cX\r^\u0001\u0018G>,h\u000e^*v[R+'/\\:%I\u00164\u0017-\u001e7uIM*B\u0001b.\u0005<V\u0011A\u0011\u0018\u0016\u0004{\n%F!\u0003-:A\u0003\u0005\tQ1\u0001ZQ\u0015!Y,\u0019C`c\u0019\u0019SM\u001aCaOF\"A%[7O\u0003]\u0019w.\u001e8u'VlG+\u001a:ng\u0012\"WMZ1vYR$C'\u0006\u0003\u00058\u0012\u001dG!\u0003-;A\u0003\u0005\tQ1\u0001ZQ\u0015!9-\u0019Cfc\u0019\u0019SM\u001aCgOF\"A%[7O\u0003%\tG\rZ*qCJ\u001cX-\u0006\u0003\u0005T\u0012mGC\u0002Ck\t_$\t\u0010\u0006\u0005\u0005X\u0012uG1\u001dCu!\u0015\t9\u0003\u0001Cm!\r1F1\u001c\u0003\u00061n\u0012\r!\u0017\u0005\n\t?\\\u0014\u0011!a\u0002\tC\f1\"\u001a<jI\u0016t7-\u001a\u00133kA1\u0011qHA.\t3D\u0011\u0002\":<\u0003\u0003\u0005\u001d\u0001b:\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$#G\u000e\t\u0007\u0003\u007f\ty\u0005\"7\t\u0013\u0011-8(!AA\u0004\u00115\u0018aC3wS\u0012,gnY3%e]\u0002b!!\u0005\u0002\u0018\u0011e\u0007b\u0002CHw\u0001\u0007Aq\u001b\u0005\b\t'[\u0004\u0019\u0001Cl\u00039\u0019XO\u0019;sC\u000e$8kY1mK\u0012,B\u0001b>\u0005\u0000RQA\u0011`C\n\u000b+)9\"\"\u0007\u0015\u0011\u0011mX\u0011AC\u0004\u000b\u001b\u0001R!a\n\u0001\t{\u00042A\u0016C\u0000\t\u0015AFH1\u0001Z\u0011%)\u0019\u0001PA\u0001\u0002\b))!A\u0006fm&$WM\\2fIIB\u0004CBA \u00037\"i\u0010C\u0005\u0006\nq\n\t\u0011q\u0001\u0006\f\u0005YQM^5eK:\u001cW\r\n\u001a:!\u0019\tyDa\u0016\u0005~\"IQq\u0002\u001f\u0002\u0002\u0003\u000fQ\u0011C\u0001\fKZLG-\u001a8dK\u0012\u001a\u0004\u0007\u0005\u0004\u0002\u0012\u0005]AQ \u0005\b\t\u001fc\u0004\u0019\u0001C~\u0011\u001d!\u0019\u0007\u0010a\u0001\t{Da!a:=\u0001\u0004i\bb\u0002CJy\u0001\u0007A1`\u0001\u000ecV|G/\\8e'B\f'o]3\u0016\t\u0015}QQ\u0006\u000b\u0007\u000bC)9%\"\u0013\u0015\u0011\u0015\rRQGC\u001e\u000b\u0003\u0002r\u0001TC\u0013\u000bS)I#C\u0002\u0006(5\u0013a\u0001V;qY\u0016\u0014\u0004#BA\u0014\u0001\u0015-\u0002c\u0001,\u0006.\u0011I\u0001,\u0010Q\u0001\u0002\u0003\u0015\r!\u0017\u0015\u0006\u000b[\tW\u0011G\u0019\u0007G\u00154W1G42\t\u0011JWN\u0014\u0005\n\u000boi\u0014\u0011!a\u0002\u000bs\t1\"\u001a<jI\u0016t7-\u001a\u00134cA1\u0011q\bB%\u000bWA\u0011\"\"\u0010>\u0003\u0003\u0005\u001d!b\u0010\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$3G\r\t\u0007\u0003\u007f\tY&b\u000b\t\u0013\u0015\rS(!AA\u0004\u0015\u0015\u0013aC3wS\u0012,gnY3%gM\u0002b!!\u0005\u0002\u0018\u0015-\u0002b\u0002CH{\u0001\u0007Q\u0011\u0006\u0005\b\t'k\u0004\u0019AC\u0015+\u0011)i%\"\u0016\u0015\r\u0015=S\u0011MC2)\u0011)\t&\"\u0018\u0011\u000b\u0005\u001d\u0002!b\u0015\u0011\u0007Y+)\u0006B\u0005Y}\u0001\u0006\t\u0011!b\u00013\"*QQK1\u0006ZE21%\u001a4\u0006\\\u001d\fD\u0001J5n\u001d\"9\u00111\u0002 A\u0004\u0015}\u0003CBA\t\u0003/)\u0019\u0006C\u0003y}\u0001\u0007!\u0010C\u0004\u0002\u0004y\u0002\r!\"\u001a\u0011\t1[X1K\u0001\bk:\f\u0007\u000f\u001d7z+\u0011)Y'\"\u001f\u0015\t\u00155T\u0011\u0011\t\u0006\u0019\u0016=T1O\u0005\u0004\u000bcj%AB(qi&|g\u000e\u0005\u0004M\u000bKQXQ\u000f\t\u0005\u0019n,9\bE\u0002W\u000bs\"\u0011\u0002W !\u0002\u0003\u0005)\u0019A-)\u000b\u0015e\u0014-\" 2\r\r*g-b hc\u0011!\u0013.\u001c(\t\u0013\u0015\ru(!AA\u0002\u0015\u0015\u0015a\u0001=%aA)\u0011q\u0005\u0001\u0006x\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011Q1\u0012\t\u0005\u0005+,i)\u0003\u0003\u0006\u0010\n]'AB(cU\u0016\u001cG\u000f"
)
public class PolySparse implements Polynomial, Product, Serializable {
   private final int[] exp;
   public final Object coeff;
   private final ClassTag ct;

   public static Option unapply(final PolySparse x$0) {
      return PolySparse$.MODULE$.unapply(x$0);
   }

   public static PolySparse zero(final Semiring evidence$16, final Eq evidence$17, final ClassTag evidence$18) {
      return PolySparse$.MODULE$.zero(evidence$16, evidence$17, evidence$18);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public List terms(final Semiring ring, final Eq eq) {
      return Polynomial.terms$(this, ring, eq);
   }

   public List terms$mcD$sp(final Semiring ring, final Eq eq) {
      return Polynomial.terms$mcD$sp$(this, ring, eq);
   }

   public Map data(final Semiring ring, final Eq eq) {
      return Polynomial.data$(this, ring, eq);
   }

   public Map data$mcD$sp(final Semiring ring, final Eq eq) {
      return Polynomial.data$mcD$sp$(this, ring, eq);
   }

   public Roots roots(final RootFinder finder) {
      return Polynomial.roots$(this, finder);
   }

   public Term maxTerm(final Semiring ring) {
      return Polynomial.maxTerm$(this, ring);
   }

   public Term maxTerm$mcD$sp(final Semiring ring) {
      return Polynomial.maxTerm$mcD$sp$(this, ring);
   }

   public Term minTerm(final Semiring ring, final Eq eq) {
      return Polynomial.minTerm$(this, ring, eq);
   }

   public Term minTerm$mcD$sp(final Semiring ring, final Eq eq) {
      return Polynomial.minTerm$mcD$sp$(this, ring, eq);
   }

   public boolean isConstant() {
      return Polynomial.isConstant$(this);
   }

   public Object evalWith(final Object x, final Function1 f, final Semiring evidence$56, final Eq evidence$57, final ClassTag evidence$58) {
      return Polynomial.evalWith$(this, x, f, evidence$56, evidence$57, evidence$58);
   }

   public Object evalWith$mcD$sp(final Object x, final Function1 f, final Semiring evidence$56, final Eq evidence$57, final ClassTag evidence$58) {
      return Polynomial.evalWith$mcD$sp$(this, x, f, evidence$56, evidence$57, evidence$58);
   }

   public Polynomial compose(final Polynomial y, final Rig ring, final Eq eq) {
      return Polynomial.compose$(this, y, ring, eq);
   }

   public Polynomial compose$mcD$sp(final Polynomial y, final Rig ring, final Eq eq) {
      return Polynomial.compose$mcD$sp$(this, y, ring, eq);
   }

   public Polynomial shift(final Object h, final Ring ring, final Eq eq) {
      return Polynomial.shift$(this, h, ring, eq);
   }

   public Polynomial shift$mcD$sp(final double h, final Ring ring, final Eq eq) {
      return Polynomial.shift$mcD$sp$(this, h, ring, eq);
   }

   public Polynomial monic(final Field f, final Eq eq) {
      return Polynomial.monic$(this, f, eq);
   }

   public Polynomial monic$mcD$sp(final Field f, final Eq eq) {
      return Polynomial.monic$mcD$sp$(this, f, eq);
   }

   public int signVariations(final Semiring ring, final Order order, final Signed signed) {
      return Polynomial.signVariations$(this, ring, order, signed);
   }

   public int signVariations$mcD$sp(final Semiring ring, final Order order, final Signed signed) {
      return Polynomial.signVariations$mcD$sp$(this, ring, order, signed);
   }

   public Polynomial removeZeroRoots(final Semiring ring, final Eq eq) {
      return Polynomial.removeZeroRoots$(this, ring, eq);
   }

   public Polynomial removeZeroRoots$mcD$sp(final Semiring ring, final Eq eq) {
      return Polynomial.removeZeroRoots$mcD$sp$(this, ring, eq);
   }

   public Polynomial map(final Function1 f, final Semiring evidence$59, final Eq evidence$60, final ClassTag evidence$61) {
      return Polynomial.map$(this, f, evidence$59, evidence$60, evidence$61);
   }

   public Polynomial map$mcD$sp(final Function1 f, final Semiring evidence$59, final Eq evidence$60, final ClassTag evidence$61) {
      return Polynomial.map$mcD$sp$(this, f, evidence$59, evidence$60, evidence$61);
   }

   public Polynomial mapTerms(final Function1 f, final Semiring evidence$62, final Eq evidence$63, final ClassTag evidence$64) {
      return Polynomial.mapTerms$(this, f, evidence$62, evidence$63, evidence$64);
   }

   public Polynomial mapTerms$mcD$sp(final Function1 f, final Semiring evidence$62, final Eq evidence$63, final ClassTag evidence$64) {
      return Polynomial.mapTerms$mcD$sp$(this, f, evidence$62, evidence$63, evidence$64);
   }

   public Polynomial flip(final Rng ring, final Eq eq) {
      return Polynomial.flip$(this, ring, eq);
   }

   public Polynomial flip$mcD$sp(final Rng ring, final Eq eq) {
      return Polynomial.flip$mcD$sp$(this, ring, eq);
   }

   public Polynomial reciprocal(final Semiring ring, final Eq eq) {
      return Polynomial.reciprocal$(this, ring, eq);
   }

   public Polynomial reciprocal$mcD$sp(final Semiring ring, final Eq eq) {
      return Polynomial.reciprocal$mcD$sp$(this, ring, eq);
   }

   public Polynomial $minus(final Polynomial rhs, final Rng ring, final Eq eq) {
      return Polynomial.$minus$(this, rhs, ring, eq);
   }

   public Polynomial $minus$mcD$sp(final Polynomial rhs, final Rng ring, final Eq eq) {
      return Polynomial.$minus$mcD$sp$(this, rhs, ring, eq);
   }

   public Polynomial $times$times(final int k, final Rig ring, final Eq eq) {
      return Polynomial.$times$times$(this, k, ring, eq);
   }

   public Polynomial $times$times$mcD$sp(final int k, final Rig ring, final Eq eq) {
      return Polynomial.$times$times$mcD$sp$(this, k, ring, eq);
   }

   public Polynomial pow(final int k, final Rig ring, final Eq eq) {
      return Polynomial.pow$(this, k, ring, eq);
   }

   public Polynomial pow$mcD$sp(final int k, final Rig ring, final Eq eq) {
      return Polynomial.pow$mcD$sp$(this, k, ring, eq);
   }

   public Polynomial $colon$times(final Object k, final Semiring ring, final Eq eq) {
      return Polynomial.$colon$times$(this, k, ring, eq);
   }

   public Polynomial $colon$times$mcD$sp(final double k, final Semiring ring, final Eq eq) {
      return Polynomial.$colon$times$mcD$sp$(this, k, ring, eq);
   }

   public Polynomial $colon$div(final Object k, final Field field, final Eq eq) {
      return Polynomial.$colon$div$(this, k, field, eq);
   }

   public Polynomial $colon$div$mcD$sp(final double k, final Field field, final Eq eq) {
      return Polynomial.$colon$div$mcD$sp$(this, k, field, eq);
   }

   public int hashCode() {
      return Polynomial.hashCode$(this);
   }

   public boolean equals(final Object that) {
      return Polynomial.equals$(this, that);
   }

   public String toString() {
      return Polynomial.toString$(this);
   }

   public int[] exp() {
      return this.exp;
   }

   public Object coeff() {
      return this.coeff;
   }

   public ClassTag ct() {
      return this.ct;
   }

   public PolyDense toDense(final Semiring ring, final Eq eq) {
      return Polynomial$.MODULE$.dense(this.coeffsArray(ring), ring, eq, this.ct());
   }

   public PolySparse toSparse(final Semiring ring, final Eq eq) {
      return this;
   }

   public void foreach(final Function2 f) {
      for(int index$macro$1 = 0; index$macro$1 < this.exp().length; ++index$macro$1) {
         f.apply(BoxesRunTime.boxToInteger(this.exp()[index$macro$1]), .MODULE$.array_apply(this.coeff(), index$macro$1));
      }

   }

   public void foreachNonZero(final Function2 f, final Semiring ring, final Eq eq) {
      this.foreach(f);
   }

   public int degree() {
      return this.isZero() ? 0 : this.exp()[this.exp().length - 1];
   }

   public Iterator termsIterator() {
      return new TermIterator();
   }

   public Object coeffsArray(final Semiring ring) {
      Object var10000;
      if (this.isZero()) {
         var10000 = this.ct().newArray(0);
      } else {
         Object cs = this.ct().newArray(this.degree() + 1);

         for(int index$macro$1 = 0; index$macro$1 < .MODULE$.array_length(cs); ++index$macro$1) {
            .MODULE$.array_update(cs, index$macro$1, ring.zero());
         }

         for(int index$macro$2 = 0; index$macro$2 < this.exp().length; ++index$macro$2) {
            .MODULE$.array_update(cs, this.exp()[index$macro$2], .MODULE$.array_apply(this.coeff(), index$macro$2));
         }

         var10000 = cs;
      }

      return var10000;
   }

   public Object nth(final int n, final Semiring ring) {
      int i = Arrays.binarySearch(this.exp(), n);
      return i >= 0 ? .MODULE$.array_apply(this.coeff(), i) : ring.zero();
   }

   public Object maxOrderTermCoeff(final Semiring ring) {
      return this.isZero() ? ring.zero() : .MODULE$.array_apply(this.coeff(), .MODULE$.array_length(this.coeff()) - 1);
   }

   public Polynomial reductum(final Eq e, final Semiring ring, final ClassTag ct) {
      int i;
      for(i = .MODULE$.array_length(this.coeff()) - 2; i >= 0 && e.eqv(.MODULE$.array_apply(this.coeff(), i), ring.zero()); --i) {
      }

      PolySparse var10000;
      if (i < 0) {
         var10000 = new PolySparse(new int[0], ct.newArray(0), ct);
      } else {
         int len = i + 1;
         int[] es = new int[len];
         Object cs = ct.newArray(len);
         System.arraycopy(this.coeff(), 0, cs, 0, len);
         System.arraycopy(this.exp(), 0, es, 0, len);
         var10000 = new PolySparse(es, cs, ct);
      }

      return var10000;
   }

   public Object expBits(final Object x, final Semiring ring) {
      Object bits = this.ct().newArray(package$.MODULE$.max((int)2, (int)(32 - Integer.numberOfLeadingZeros(this.degree()))));
      .MODULE$.array_update(bits, 0, x);
      if (.MODULE$.array_length(bits) > 1) {
         .MODULE$.array_update(bits, 1, ring.pow(x, 2));
      }

      for(int index$macro$1 = 2; index$macro$1 < .MODULE$.array_length(bits); ++index$macro$1) {
         Object prev = .MODULE$.array_apply(bits, index$macro$1 - 1);
         .MODULE$.array_update(bits, index$macro$1, ring.times(prev, prev));
      }

      return bits;
   }

   public Object fastExp(final Object bits, final int e, final int i, final Object acc, final Semiring ring) {
      while(e != 0) {
         int lb = Integer.numberOfTrailingZeros(e) + 1;
         int j = i + lb;
         int var10001 = e >>> lb;
         Object var10003 = ring.times(acc, .MODULE$.array_apply(bits, j - 1));
         ring = ring;
         acc = var10003;
         i = j;
         e = var10001;
         bits = bits;
      }

      return acc;
   }

   public Object fastExp(final Object bits, final int e, final Semiring ring) {
      int lb = Integer.numberOfTrailingZeros(e) + 1;
      return this.fastExp(bits, e >>> lb, lb, .MODULE$.array_apply(bits, lb - 1), ring);
   }

   public boolean isZero() {
      return scala.collection.ArrayOps..MODULE$.isEmpty$extension(scala.Predef..MODULE$.intArrayOps(this.exp()));
   }

   public Object apply(final Object x, final Semiring ring) {
      Object var10000;
      if (this.isZero()) {
         var10000 = ring.zero();
      } else if (this.exp().length == 1) {
         var10000 = this.exp()[0] != 0 ? ring.times(.MODULE$.array_apply(this.coeff(), 0), ring.pow(x, this.exp()[0])) : .MODULE$.array_apply(this.coeff(), 0);
      } else {
         Object bits = this.expBits(x, ring);
         int e0 = this.exp()[0];
         Object c0 = .MODULE$.array_apply(this.coeff(), 0);
         Object sum = e0 == 0 ? c0 : ring.times(c0, this.fastExp(bits, e0, ring));

         for(int index$macro$1 = 1; index$macro$1 < this.exp().length; ++index$macro$1) {
            sum = ring.plus(sum, ring.times(.MODULE$.array_apply(this.coeff(), index$macro$1), this.fastExp(bits, this.exp()[index$macro$1], ring)));
         }

         var10000 = sum;
      }

      return var10000;
   }

   public Polynomial derivative(final Ring ring, final Eq eq) {
      PolySparse var10000;
      if (this.exp().length == 0) {
         var10000 = this;
      } else {
         int i0 = this.exp()[0] == 0 ? 1 : 0;
         int[] es = new int[this.exp().length - i0];
         Object cs = this.ct().newArray(es.length);
         this.loop$1(i0, 0, es, cs, ring);
         var10000 = PolySparse$.MODULE$.safe(es, cs, ring, eq, this.ct());
      }

      return var10000;
   }

   public Polynomial integral(final Field field, final Eq eq) {
      int[] es = new int[this.exp().length];
      Object cs = this.ct().newArray(es.length);

      for(int index$macro$1 = 0; index$macro$1 < es.length; ++index$macro$1) {
         int e = this.exp()[index$macro$1] + 1;
         es[index$macro$1] = e;
         .MODULE$.array_update(cs, index$macro$1, field.div(.MODULE$.array_apply(this.coeff(), index$macro$1), field.fromInt(e)));
      }

      return PolySparse$.MODULE$.safe(es, cs, field, eq, this.ct());
   }

   public Polynomial unary_$minus(final Rng ring) {
      Object cs = this.ct().newArray(.MODULE$.array_length(this.coeff()));

      for(int index$macro$1 = 0; index$macro$1 < .MODULE$.array_length(cs); ++index$macro$1) {
         .MODULE$.array_update(cs, index$macro$1, ring.negate(.MODULE$.array_apply(this.coeff(), index$macro$1)));
      }

      return new PolySparse(this.exp(), cs, this.ct());
   }

   public Polynomial $plus(final Polynomial rhs0, final Semiring ring, final Eq eq) {
      PolySparse rhs = PolySparse$.MODULE$.apply(rhs0, ring, eq, this.ct());
      return PolySparse$.MODULE$.spire$math$poly$PolySparse$$addSparse(this, rhs, eq, ring, this.ct());
   }

   public Polynomial $times(final Polynomial rhs0, final Semiring ring, final Eq eq) {
      PolySparse rhs = PolySparse$.MODULE$.apply(rhs0, ring, eq, this.ct());
      return PolySparse$.MODULE$.spire$math$poly$PolySparse$$multiplySparse(this, rhs, ring, eq, this.ct());
   }

   public Polynomial $times$colon(final Object k, final Semiring ring, final Eq eq) {
      PolySparse var10000;
      if (eq.eqv(k, ring.zero())) {
         var10000 = PolySparse$.MODULE$.zero(ring, eq, this.ct());
      } else {
         Object cs = this.ct().newArray(.MODULE$.array_length(this.coeff()));

         for(int index$macro$1 = 0; index$macro$1 < .MODULE$.array_length(cs); ++index$macro$1) {
            .MODULE$.array_update(cs, index$macro$1, ring.times(k, .MODULE$.array_apply(this.coeff(), index$macro$1)));
         }

         var10000 = new PolySparse(this.exp(), cs, this.ct());
      }

      return var10000;
   }

   public PolySparse copy(final int[] exp, final Object coeff, final ClassTag ct) {
      return new PolySparse(exp, coeff, ct);
   }

   public int[] copy$default$1() {
      return this.exp();
   }

   public Object copy$default$2() {
      return this.coeff();
   }

   public String productPrefix() {
      return "PolySparse";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.exp();
            break;
         case 1:
            var10000 = this.coeff();
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
      return x$1 instanceof PolySparse;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "exp";
            break;
         case 1:
            var10000 = "coeff";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public double[] coeff$mcD$sp() {
      return (double[])this.coeff();
   }

   public PolyDense toDense$mcD$sp(final Semiring ring, final Eq eq) {
      return this.toDense(ring, eq);
   }

   public PolySparse toSparse$mcD$sp(final Semiring ring, final Eq eq) {
      return this.toSparse(ring, eq);
   }

   public void foreach$mcD$sp(final Function2 f) {
      this.foreach(f);
   }

   public void foreachNonZero$mcD$sp(final Function2 f, final Semiring ring, final Eq eq) {
      this.foreachNonZero(f, ring, eq);
   }

   public double[] coeffsArray$mcD$sp(final Semiring ring) {
      return (double[])this.coeffsArray(ring);
   }

   public double nth$mcD$sp(final int n, final Semiring ring) {
      return BoxesRunTime.unboxToDouble(this.nth(n, ring));
   }

   public double maxOrderTermCoeff$mcD$sp(final Semiring ring) {
      return BoxesRunTime.unboxToDouble(this.maxOrderTermCoeff(ring));
   }

   public Polynomial reductum$mcD$sp(final Eq e, final Semiring ring, final ClassTag ct) {
      return this.reductum(e, ring, ct);
   }

   public double[] expBits$mcD$sp(final double x, final Semiring ring) {
      return (double[])this.expBits(BoxesRunTime.boxToDouble(x), ring);
   }

   public double fastExp$mcD$sp(final double[] bits, final int e, final int i, final double acc, final Semiring ring) {
      return BoxesRunTime.unboxToDouble(this.fastExp(bits, e, i, BoxesRunTime.boxToDouble(acc), ring));
   }

   public double fastExp$mcD$sp(final double[] bits, final int e, final Semiring ring) {
      return BoxesRunTime.unboxToDouble(this.fastExp(bits, e, ring));
   }

   public double apply$mcD$sp(final double x, final Semiring ring) {
      return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToDouble(x), ring));
   }

   public Polynomial derivative$mcD$sp(final Ring ring, final Eq eq) {
      return this.derivative(ring, eq);
   }

   public Polynomial integral$mcD$sp(final Field field, final Eq eq) {
      return this.integral(field, eq);
   }

   public Polynomial unary_$minus$mcD$sp(final Rng ring) {
      return this.unary_$minus(ring);
   }

   public Polynomial $plus$mcD$sp(final Polynomial rhs0, final Semiring ring, final Eq eq) {
      return this.$plus(rhs0, ring, eq);
   }

   public Polynomial $times$mcD$sp(final Polynomial rhs0, final Semiring ring, final Eq eq) {
      return this.$times(rhs0, ring, eq);
   }

   public Polynomial $times$colon$mcD$sp(final double k, final Semiring ring, final Eq eq) {
      return this.$times$colon(BoxesRunTime.boxToDouble(k), ring, eq);
   }

   public PolySparse copy$mDc$sp(final int[] exp, final double[] coeff, final ClassTag ct) {
      return new PolySparse$mcD$sp(exp, coeff, ct);
   }

   public double[] copy$default$2$mcD$sp() {
      return (double[])this.copy$default$2();
   }

   public boolean specInstance$() {
      return false;
   }

   private final void loop$1(final int i, final int j, final int[] es$1, final Object cs$1, final Ring ring$1) {
      while(j < es$1.length) {
         int e = this.exp()[i];
         es$1[j] = e - 1;
         .MODULE$.array_update(cs$1, j, LiteralIntMultiplicativeSemigroupOps$.MODULE$.$times$extension(package.field$.MODULE$.literalIntMultiplicativeSemigroupOps(e), .MODULE$.array_apply(this.coeff(), i), ring$1));
         int var10000 = i + 1;
         ++j;
         i = var10000;
      }

      BoxedUnit var8 = BoxedUnit.UNIT;
   }

   public PolySparse(final int[] exp, final Object coeff, final ClassTag ct) {
      this.exp = exp;
      this.coeff = coeff;
      this.ct = ct;
      Polynomial.$init$(this);
      Product.$init$(this);
   }

   public class TermIterator implements Iterator {
      private int i;
      // $FF: synthetic field
      public final PolySparse $outer;

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

      public Iterator map(final Function1 f) {
         return Iterator.map$(this, f);
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

      public Iterator take(final int n) {
         return Iterator.take$(this, n);
      }

      public Iterator takeWhile(final Function1 p) {
         return Iterator.takeWhile$(this, p);
      }

      public Iterator drop(final int n) {
         return Iterator.drop$(this, n);
      }

      public Iterator dropWhile(final Function1 p) {
         return Iterator.dropWhile$(this, p);
      }

      public Tuple2 span(final Function1 p) {
         return Iterator.span$(this, p);
      }

      public Iterator slice(final int from, final int until) {
         return Iterator.slice$(this, from, until);
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

      public int count(final Function1 p) {
         return IterableOnceOps.count$(this, p);
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

      public Object fold(final Object z, final Function2 op) {
         return IterableOnceOps.fold$(this, z, op);
      }

      public Object reduce(final Function2 op) {
         return IterableOnceOps.reduce$(this, op);
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

      public Object sum(final Numeric num) {
         return IterableOnceOps.sum$(this, num);
      }

      public Object product(final Numeric num) {
         return IterableOnceOps.product$(this, num);
      }

      public Object min(final Ordering ord) {
         return IterableOnceOps.min$(this, ord);
      }

      public Option minOption(final Ordering ord) {
         return IterableOnceOps.minOption$(this, ord);
      }

      public Object max(final Ordering ord) {
         return IterableOnceOps.max$(this, ord);
      }

      public Option maxOption(final Ordering ord) {
         return IterableOnceOps.maxOption$(this, ord);
      }

      public Object maxBy(final Function1 f, final Ordering cmp) {
         return IterableOnceOps.maxBy$(this, f, cmp);
      }

      public Option maxByOption(final Function1 f, final Ordering cmp) {
         return IterableOnceOps.maxByOption$(this, f, cmp);
      }

      public Object minBy(final Function1 f, final Ordering cmp) {
         return IterableOnceOps.minBy$(this, f, cmp);
      }

      public Option minByOption(final Function1 f, final Ordering cmp) {
         return IterableOnceOps.minByOption$(this, f, cmp);
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

      public StringBuilder addString(final StringBuilder b, final String start, final String sep, final String end) {
         return IterableOnceOps.addString$(this, b, start, sep, end);
      }

      public final StringBuilder addString(final StringBuilder b, final String sep) {
         return IterableOnceOps.addString$(this, b, sep);
      }

      public final StringBuilder addString(final StringBuilder b) {
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

      public Map toMap(final scala..less.colon.less ev) {
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

      private void findNext() {
         while(this.i < this.spire$math$poly$PolySparse$TermIterator$$$outer().exp().length && BoxesRunTime.equals(.MODULE$.array_apply(this.spire$math$poly$PolySparse$TermIterator$$$outer().coeff(), this.i), BoxesRunTime.boxToInteger(0))) {
            ++this.i;
         }

      }

      public boolean hasNext() {
         return this.i < this.spire$math$poly$PolySparse$TermIterator$$$outer().exp().length;
      }

      public Term next() {
         Term term = new Term(.MODULE$.array_apply(this.spire$math$poly$PolySparse$TermIterator$$$outer().coeff(), this.i), this.spire$math$poly$PolySparse$TermIterator$$$outer().exp()[this.i]);
         ++this.i;
         this.findNext();
         return term;
      }

      // $FF: synthetic method
      public PolySparse spire$math$poly$PolySparse$TermIterator$$$outer() {
         return this.$outer;
      }

      public TermIterator() {
         if (PolySparse.this == null) {
            throw null;
         } else {
            this.$outer = PolySparse.this;
            super();
            IterableOnce.$init$(this);
            IterableOnceOps.$init$(this);
            Iterator.$init$(this);
            this.i = 0;
            this.findNext();
         }
      }
   }
}
