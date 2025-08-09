package scala.collection.immutable;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Hashing$;
import scala.collection.IterableFactory;
import scala.collection.IterableOnce;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.collection.MapFactory;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.StepperShape$;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.convert.impl.AnyChampStepper;
import scala.collection.convert.impl.AnyChampStepper$;
import scala.collection.convert.impl.DoubleChampStepper;
import scala.collection.convert.impl.DoubleChampStepper$;
import scala.collection.convert.impl.IntChampStepper;
import scala.collection.convert.impl.IntChampStepper$;
import scala.collection.convert.impl.LongChampStepper;
import scala.collection.convert.impl.LongChampStepper$;
import scala.collection.generic.DefaultSerializable;
import scala.collection.mutable.Builder;
import scala.collection.mutable.LinkedHashMap;
import scala.collection.mutable.LinkedHashSet;
import scala.collection.mutable.ReusableBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.util.hashing.MurmurHash3$;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011-a\u0001B!C\u0005%C\u0011\u0002\u001d\u0001\u0003\u0006\u0004%\tAQ9\t\u0011U\u0004!\u0011!Q\u0001\nIDaA\u001e\u0001\u0005\u0002\t;\b\"\u0002<\u0001\t\u0003I\b\"\u0002>\u0001\t\u0003Z\bBB@\u0001\t\u0003\n\t\u0001C\u0004\u0002\n\u0001!\t%!\u0001\t\u000f\u0005-\u0001\u0001\"\u0011\u0002\u000e!9\u0011Q\u0003\u0001\u0005B\u0005]aaBA\u0010\u0001\t\u0011\u0015\u0011\u0005\u0005\u0007m*!\t!a\f\t\u0011\u0005M\"\u0002)C\u0005\u0003kA\u0001\"a\r\u000bA\u0013%\u0011Q\t\u0005\b\u0003+RA\u0011IA,\u0011\u001d\tiF\u0003C!\u0003?Bq!a\u0019\u000b\t\u0003\n)\u0007C\u0004\u0002r)!\t%a\u001d\t\u000f\u0005]\u0004\u0001\"\u0001\u0002z!9\u0011q\u0011\u0001\u0005B\u0005%\u0005bBAG\u0001\u0011\u0005\u0013q\u0012\u0005\t\u0003'\u0003A\u0011\u0003\"\u0002z!9\u0011Q\u0013\u0001\u0005B\u0005]\u0005bBAo\u0001\u0011\u0005\u0013q\u001c\u0005\b\u0003w\u0004A\u0011IA\u007f\u0011\u001d\u0011I\u0002\u0001C#\u00057AqA!\t\u0001\t\u0003\u0012\u0019\u0003C\u0004\u0003(\u0001!\tA!\u000b\t\u000f\tM\u0002\u0001\"\u0011\u00036!A!Q\n\u0001!\n\u0013\u0011y\u0005C\u0004\u0003f\u0001!\tAa\u001a\t\u000f\t]\u0004\u0001\"\u0011\u0003z!9!q\u0012\u0001\u0005\u0002\tE\u0005b\u0002BK\u0001\u0011\u0005#q\u0013\u0005\b\u0005k\u0003A\u0011\tB\\\u0011\u001d\u0011I\f\u0001C!\u0005oCqAa/\u0001\t\u0003\u0012i\fC\u0004\u0003@\u0002!\tE!0\t\u000f\t\u0005\u0007\u0001\"\u0011\u0003D\"9!\u0011\u001c\u0001\u0005B\tm\u0007\u0002\u0003Bv\u0001\u0011\u0005AI!<\t\u000f\te\b\u0001\"\u0011\u0003|\"9!q \u0001\u0005B\r\u0005\u0001\u0002CB\u0002\u0001\u0001&\tf!\u0002\t\u000f\r]\u0001\u0001\"\u0001\u0004\u001a!91q\u0006\u0001\u0005B\rE\u0002\u0002CB!\u0001\u0011ECia\u0011\t\u000f\r5\u0003\u0001\"\u0011\u0004P!91\u0011\f\u0001\u0005B\rm\u0003bBB2\u0001\u0011\u00053Q\r\u0005\b\u0007W\u0002A\u0011IB7\u0011\u001d\u0019\t\b\u0001C!\u0007gBqaa\u001e\u0001\t\u0003\u001aI\bC\u0004\u0004~\u0001!\tea \t\u000f\r\r\u0005\u0001\"\u0011\u0004\u0006\"91\u0011\u0012\u0001\u0005B\r-uaBBH\u0005\"\u00051\u0011\u0013\u0004\u0007\u0003\nC\taa%\t\rYLD\u0011ABN\u0011%\u0019i*\u000fb\u0001\n\u001b\u0019y\n\u0003\u0005\u0004$f\u0002\u000bQBBQ\u0011\u001d\u0019i+\u000fC\u0001\u0007_Cqa!0:\t\u0003\u0019y\fC\u0004\u0004Xf\"\ta!7\t\u0013\rU\u0018(!A\u0005\n\r](a\u0002%bg\"l\u0015\r\u001d\u0006\u0003\u0007\u0012\u000b\u0011\"[7nkR\f'\r\\3\u000b\u0005\u00153\u0015AC2pY2,7\r^5p]*\tq)A\u0003tG\u0006d\u0017m\u0001\u0001\u0016\u0007)\u000bFlE\u0003\u0001\u0017z\u001b'\u000e\u0005\u0003M\u001b>[V\"\u0001\"\n\u00059\u0013%aC!cgR\u0014\u0018m\u0019;NCB\u0004\"\u0001U)\r\u0001\u0011)!\u000b\u0001b\u0001'\n\t1*\u0005\u0002U1B\u0011QKV\u0007\u0002\r&\u0011qK\u0012\u0002\b\u001d>$\b.\u001b8h!\t)\u0016,\u0003\u0002[\r\n\u0019\u0011I\\=\u0011\u0005AcFAB/\u0001\t\u000b\u00071KA\u0001W!\u0019aulT.bE&\u0011\u0001M\u0011\u0002\u0016'R\u0014\u0018n\u0019;PaRLW.\u001b>fI6\u000b\u0007o\u00149t!\ta\u0005\u0001\u0005\u0003M\u0001=[\u0006C\u00023f\u001fn\u000bw-D\u0001E\u0013\t1GI\u0001\nNCB4\u0015m\u0019;pef$UMZ1vYR\u001c\bC\u0001'i\u0013\tI'I\u0001\u0005Ji\u0016\u0014\u0018M\u00197f!\tYg.D\u0001m\u0015\tiG)A\u0004hK:,'/[2\n\u0005=d'a\u0005#fM\u0006,H\u000e^*fe&\fG.\u001b>bE2,\u0017\u0001\u0003:p_Rtu\u000eZ3\u0016\u0003I\u0004B\u0001T:P7&\u0011AO\u0011\u0002\u0015\u0005&$X.\u00199J]\u0012,\u00070\u001a3NCBtu\u000eZ3\u0002\u0013I|w\u000e\u001e(pI\u0016\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002cq\")\u0001o\u0001a\u0001eR\t!-\u0001\u0006nCB4\u0015m\u0019;pef,\u0012\u0001 \t\u0004Iv\f\u0017B\u0001@E\u0005)i\u0015\r\u001d$bGR|'/_\u0001\nW:|wO\\*ju\u0016,\"!a\u0001\u0011\u0007U\u000b)!C\u0002\u0002\b\u0019\u00131!\u00138u\u0003\u0011\u0019\u0018N_3\u0002\u000f%\u001cX)\u001c9usV\u0011\u0011q\u0002\t\u0004+\u0006E\u0011bAA\n\r\n9!i\\8mK\u0006t\u0017AB6fsN+G/\u0006\u0002\u0002\u001aA!A*a\u0007P\u0013\r\tiB\u0011\u0002\u0004'\u0016$(A\u0003%bg\"\\U-_*fiN\u0019!\"a\t\u0011\t\u0005\u0015\u0012qE\u0007\u0002\u0001%!\u0011\u0011FA\u0016\u0005=IU.\\;uC\ndWmS3z'\u0016$\u0018bAA\u0017\u0005\n1Q*\u00199PaN$\"!!\r\u0011\u0007\u0005\u0015\"\"A\boK^\\U-_*fi>\u0013H\u000b[5t)\u0011\tI\"a\u000e\t\u000f\u0005eB\u00021\u0001\u0002<\u0005Qa.Z<ICNDW*\u001991\t\u0005u\u0012\u0011\t\t\u0006\u0019\u0002y\u0015q\b\t\u0004!\u0006\u0005CaCA\"\u0003o\t\t\u0011!A\u0003\u0002M\u00131a\u0018\u00132)\u0011\tI\"a\u0012\t\u000f\u0005%S\u00021\u0001\u0002L\u0005Ya.Z<S_>$hj\u001c3fa\u0011\ti%!\u0015\u0011\u000b1\u001bx*a\u0014\u0011\u0007A\u000b\t\u0006B\u0006\u0002T\u0005\u001d\u0013\u0011!A\u0001\u0006\u0003\u0019&aA0%e\u0005!\u0011N\\2m)\u0011\tI\"!\u0017\t\r\u0005mc\u00021\u0001P\u0003\u0011)G.Z7\u0002\t\u0015D8\r\u001c\u000b\u0005\u00033\t\t\u0007\u0003\u0004\u0002\\=\u0001\raT\u0001\u0007M&dG/\u001a:\u0015\t\u0005e\u0011q\r\u0005\b\u0003S\u0002\u0002\u0019AA6\u0003\u0011\u0001(/\u001a3\u0011\rU\u000bigTA\b\u0013\r\tyG\u0012\u0002\n\rVt7\r^5p]F\n\u0011BZ5mi\u0016\u0014hj\u001c;\u0015\t\u0005e\u0011Q\u000f\u0005\b\u0003S\n\u0002\u0019AA6\u0003!IG/\u001a:bi>\u0014XCAA>!\u0015!\u0017QPAA\u0013\r\ty\b\u0012\u0002\t\u0013R,'/\u0019;peB)Q+a!P7&\u0019\u0011Q\u0011$\u0003\rQ+\b\u000f\\33\u00031YW-_:Ji\u0016\u0014\u0018\r^8s+\t\tY\t\u0005\u0003e\u0003{z\u0015A\u0004<bYV,7/\u0013;fe\u0006$xN]\u000b\u0003\u0003#\u0003B\u0001ZA?7\u0006y!/\u001a<feN,\u0017\n^3sCR|'/A\u0004ti\u0016\u0004\b/\u001a:\u0016\t\u0005e\u00151\u0015\u000b\u0005\u00037\u000b\u0019N\u0005\u0004\u0002\u001e\u0006\u0005\u0016q\u0017\u0004\u0007\u0003?\u0003\u0001!a'\u0003\u0019q\u0012XMZ5oK6,g\u000e\u001e \u0011\u0007A\u000b\u0019\u000bB\u0004\u0002&Z\u0011\r!a*\u0003\u0003M\u000b2\u0001VAUa\u0011\tY+a-\u0011\u000b\u0011\fi+!-\n\u0007\u0005=FIA\u0004Ti\u0016\u0004\b/\u001a:\u0011\u0007A\u000b\u0019\fB\u0006\u00026\u0006\r\u0016\u0011!A\u0001\u0006\u0003\u0019&aA0%gA!\u0011\u0011XAg\u001d\u0011\tY,!3\u000f\t\u0005u\u0016q\u0019\b\u0005\u0003\u007f\u000b)-\u0004\u0002\u0002B*\u0019\u00111\u0019%\u0002\rq\u0012xn\u001c;?\u0013\u00059\u0015BA#G\u0013\r\tY\rR\u0001\b'R,\u0007\u000f]3s\u0013\u0011\ty-!5\u0003\u001d\u00153g-[2jK:$8\u000b\u001d7ji*\u0019\u00111\u001a#\t\u000f\u0005Ug\u0003q\u0001\u0002X\u0006)1\u000f[1qKB9A-!7\u0002\u0002\u0006\u0005\u0016bAAn\t\na1\u000b^3qa\u0016\u00148\u000b[1qK\u0006Q1.Z=Ti\u0016\u0004\b/\u001a:\u0016\t\u0005\u0005\u0018\u0011\u001e\u000b\u0005\u0003G\f9P\u0005\u0004\u0002f\u0006\u001d\u0018q\u0017\u0004\u0007\u0003?\u0003\u0001!a9\u0011\u0007A\u000bI\u000fB\u0004\u0002&^\u0011\r!a;\u0012\u0007Q\u000bi\u000f\r\u0003\u0002p\u0006M\b#\u00023\u0002.\u0006E\bc\u0001)\u0002t\u0012Y\u0011Q_Au\u0003\u0003\u0005\tQ!\u0001T\u0005\ryF\u0005\u000e\u0005\b\u0003+<\u00029AA}!\u0019!\u0017\u0011\\(\u0002h\u0006aa/\u00197vKN#X\r\u001d9feV!\u0011q B\u0004)\u0011\u0011\tA!\u0006\u0013\r\t\r!QAA\\\r\u0019\ty\n\u0001\u0001\u0003\u0002A\u0019\u0001Ka\u0002\u0005\u000f\u0005\u0015\u0006D1\u0001\u0003\nE\u0019AKa\u00031\t\t5!\u0011\u0003\t\u0006I\u00065&q\u0002\t\u0004!\nEAa\u0003B\n\u0005\u000f\t\t\u0011!A\u0003\u0002M\u00131a\u0018\u00136\u0011\u001d\t)\u000e\u0007a\u0002\u0005/\u0001b\u0001ZAm7\n\u0015\u0011\u0001C2p]R\f\u0017N\\:\u0015\t\u0005=!Q\u0004\u0005\u0007\u0005?I\u0002\u0019A(\u0002\u0007-,\u00170A\u0003baBd\u0017\u0010F\u0002\\\u0005KAaAa\b\u001b\u0001\u0004y\u0015aA4fiR!!1\u0006B\u0019!\u0011)&QF.\n\u0007\t=bI\u0001\u0004PaRLwN\u001c\u0005\u0007\u0005?Y\u0002\u0019A(\u0002\u0013\u001d,Go\u0014:FYN,W\u0003\u0002B\u001c\u0005w!bA!\u000f\u0003B\t\r\u0003c\u0001)\u0003<\u00119!Q\b\u000fC\u0002\t}\"A\u0001,2#\tY\u0006\f\u0003\u0004\u0003 q\u0001\ra\u0014\u0005\t\u0005\u000bbB\u00111\u0001\u0003H\u00059A-\u001a4bk2$\b#B+\u0003J\te\u0012b\u0001B&\r\nAAHY=oC6,g(\u0001\toK^D\u0015m\u001d5NCB|%\u000f\u00165jgV!!\u0011\u000bB,)\u0011\u0011\u0019F!\u0017\u0011\u000b1\u0003qJ!\u0016\u0011\u0007A\u00139\u0006B\u0004\u0003>u\u0011\rAa\u0010\t\u000f\u0005%S\u00041\u0001\u0003\\A)Aj](\u0003V!\u001aQDa\u0018\u0011\u0007U\u0013\t'C\u0002\u0003d\u0019\u0013a!\u001b8mS:,\u0017aB;qI\u0006$X\rZ\u000b\u0005\u0005S\u0012y\u0007\u0006\u0004\u0003l\tE$1\u000f\t\u0006\u0019\u0002y%Q\u000e\t\u0004!\n=Da\u0002B\u001f=\t\u0007!q\b\u0005\u0007\u0005?q\u0002\u0019A(\t\u000f\tUd\u00041\u0001\u0003n\u0005)a/\u00197vK\u0006YQ\u000f\u001d3bi\u0016$w+\u001b;i+\u0011\u0011YHa!\u0015\t\tu$Q\u0012\u000b\u0005\u0005\u007f\u0012)\tE\u0003M\u0001=\u0013\t\tE\u0002Q\u0005\u0007#qA!\u0010 \u0005\u0004\u0011y\u0004C\u0004\u0003\b~\u0001\rA!#\u0002#I,W.\u00199qS:<g)\u001e8di&|g\u000eE\u0004V\u0003[\u0012YCa#\u0011\u000bU\u0013iC!!\t\r\t}q\u00041\u0001P\u0003\u001d\u0011X-\\8wK\u0012$2A\u0019BJ\u0011\u0019\u0011y\u0002\ta\u0001\u001f\u000611m\u001c8dCR,BA!'\u0003 R!!1\u0014BQ!\u0015a\u0005a\u0014BO!\r\u0001&q\u0014\u0003\b\u0005{\t#\u0019\u0001B \u0011\u001d\u0011\u0019+\ta\u0001\u0005K\u000bA\u0001\u001e5biB1!q\u0015BW\u0005gsA!!0\u0003*&\u0019!1\u0016$\u0002\u000fA\f7m[1hK&!!q\u0016BY\u00051IE/\u001a:bE2,wJ\\2f\u0015\r\u0011YK\u0012\t\u0007+\u0006\ruJ!(\u0002\tQ\f\u0017\u000e\\\u000b\u0002E\u0006!\u0011N\\5u\u0003\u0011AW-\u00193\u0016\u0005\u0005\u0005\u0015\u0001\u00027bgR\fqAZ8sK\u0006\u001c\u0007.\u0006\u0003\u0003F\nUG\u0003\u0002Bd\u0005\u001b\u00042!\u0016Be\u0013\r\u0011YM\u0012\u0002\u0005+:LG\u000fC\u0004\u0003P\u001a\u0002\rA!5\u0002\u0003\u0019\u0004r!VA7\u0003\u0003\u0013\u0019\u000eE\u0002Q\u0005+$aAa6'\u0005\u0004\u0019&!A+\u0002\u0019\u0019|'/Z1dQ\u0016sGO]=\u0016\t\tu'\u0011\u001e\u000b\u0005\u0005\u000f\u0014y\u000eC\u0004\u0003P\u001e\u0002\rA!9\u0011\u000fU\u0013\u0019oT.\u0003h&\u0019!Q\u001d$\u0003\u0013\u0019+hn\u0019;j_:\u0014\u0004c\u0001)\u0003j\u00121!q[\u0014C\u0002M\u000bqBZ8sK\u0006\u001c\u0007nV5uQ\"\u000b7\u000f\u001b\u000b\u0005\u0005\u000f\u0014y\u000fC\u0004\u0003P\"\u0002\rA!=\u0011\u0013U\u0013\u0019pT.\u0002\u0004\t\u001d\u0017b\u0001B{\r\nIa)\u001e8di&|gn\r\u0015\u0004Q\t}\u0013AB3rk\u0006d7\u000f\u0006\u0003\u0002\u0010\tu\bB\u0002BRS\u0001\u0007\u0001,\u0001\u0005iCND7i\u001c3f)\t\t\u0019!A\u0005dY\u0006\u001c8OT1nKV\u00111q\u0001\t\u0005\u0007\u0013\u0019\u0019\"\u0004\u0002\u0004\f)!1QBB\b\u0003\u0011a\u0017M\\4\u000b\u0005\rE\u0011\u0001\u00026bm\u0006LAa!\u0006\u0004\f\t11\u000b\u001e:j]\u001e\fa!\\3sO\u0016$W\u0003BB\u000e\u0007G!Ba!\b\u0004.Q!1qDB\u0013!\u0015a\u0005aTB\u0011!\r\u000161\u0005\u0003\b\u0005{a#\u0019\u0001B \u0011\u001d\u00199\u0003\fa\u0001\u0007S\ta!\\3sO\u00164\u0007#C+\u0003d\u0006\u000551FB\u0016!\u0019)\u00161Q(\u0004\"!9!1\u0015\u0017A\u0002\r}\u0011!\u0003;sC:\u001chm\u001c:n+\u0011\u0019\u0019d!\u000f\u0015\t\rU2Q\b\t\u0006\u0019\u0002y5q\u0007\t\u0004!\u000eeBABB\u001e[\t\u00071KA\u0001X\u0011\u001d\u0011y-\fa\u0001\u0007\u007f\u0001r!\u0016Br\u001fn\u001b9$\u0001\u0006gS2$XM]%na2$RAYB#\u0007\u0013Bq!!\u001b/\u0001\u0004\u00199\u0005E\u0004V\u0003[\n\t)a\u0004\t\u000f\r-c\u00061\u0001\u0002\u0010\u0005I\u0011n\u001d$mSB\u0004X\rZ\u0001\u000be\u0016lwN^3e\u00032dGc\u00012\u0004R!911K\u0018A\u0002\rU\u0013\u0001B6fsN\u0004Raa\u0016\u0003.>s1!\u0016BU\u0003%\u0001\u0018M\u001d;ji&|g\u000e\u0006\u0003\u0004^\r}\u0003#B+\u0002\u0004\n\u0014\u0007bBB1a\u0001\u00071qI\u0001\u0002a\u0006!A/Y6f)\r\u00117q\r\u0005\b\u0007S\n\u0004\u0019AA\u0002\u0003\u0005q\u0017!\u0003;bW\u0016\u0014\u0016n\u001a5u)\r\u00117q\u000e\u0005\b\u0007S\u0012\u0004\u0019AA\u0002\u0003%!\u0018m[3XQ&dW\rF\u0002c\u0007kBqa!\u00194\u0001\u0004\u00199%A\u0005ee>\u0004x\u000b[5mKR\u0019!ma\u001f\t\u000f\r\u0005D\u00071\u0001\u0004H\u0005IAM]8q%&<\u0007\u000e\u001e\u000b\u0004E\u000e\u0005\u0005bBB5k\u0001\u0007\u00111A\u0001\u0005IJ|\u0007\u000fF\u0002c\u0007\u000fCqa!\u001b7\u0001\u0004\t\u0019!\u0001\u0003ta\u0006tG\u0003BB/\u0007\u001bCqa!\u00198\u0001\u0004\u00199%A\u0004ICNDW*\u00199\u0011\u00051K4\u0003B\u001d\u0004\u0016r\u00042!VBL\u0013\r\u0019IJ\u0012\u0002\u0007\u0003:L(+\u001a4\u0015\u0005\rE\u0015\u0001C#naRLX*\u00199\u0016\u0005\r\u0005\u0006\u0003\u0002'\u0001)R\u000b\u0011\"R7qifl\u0015\r\u001d\u0011)\u0007q\u001a9\u000bE\u0002V\u0007SK1aa+G\u0005%!(/\u00198tS\u0016tG/A\u0003f[B$\u00180\u0006\u0004\u00042\u000e]61X\u000b\u0003\u0007g\u0003b\u0001\u0014\u0001\u00046\u000ee\u0006c\u0001)\u00048\u0012)!+\u0010b\u0001'B\u0019\u0001ka/\u0005\u000buk$\u0019A*\u0002\t\u0019\u0014x.\\\u000b\u0007\u0007\u0003\u001c9ma3\u0015\t\r\r7Q\u001a\t\u0007\u0019\u0002\u0019)m!3\u0011\u0007A\u001b9\rB\u0003S}\t\u00071\u000bE\u0002Q\u0007\u0017$Q!\u0018 C\u0002MCqaa4?\u0001\u0004\u0019\t.\u0001\u0004t_V\u00148-\u001a\t\u0006I\u000eM7Q[\u0005\u0004\u0005_#\u0005cB+\u0002\u0004\u000e\u00157\u0011Z\u0001\u000b]\u0016<()^5mI\u0016\u0014XCBBn\u0007[\u001c\t0\u0006\u0002\u0004^BA1q\\Bs\u0007S\u001c\u00190\u0004\u0002\u0004b*\u001911\u001d#\u0002\u000f5,H/\u00192mK&!1q]Bq\u0005=\u0011V-^:bE2,')^5mI\u0016\u0014\bcB+\u0002\u0004\u000e-8q\u001e\t\u0004!\u000e5H!\u0002*@\u0005\u0004\u0019\u0006c\u0001)\u0004r\u0012)Ql\u0010b\u0001'B1A\nABv\u0007_\fAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"a!?\u0011\t\r%11`\u0005\u0005\u0007{\u001cYA\u0001\u0004PE*,7\r\u001e\u0015\bs\u0011\u0005!Q\u000fC\u0004!\r)F1A\u0005\u0004\t\u000b1%\u0001E*fe&\fGNV3sg&|g.V%E=\u0005\u0019\u0001f\u0002\u001d\u0005\u0002\tUDq\u0001"
)
public final class HashMap extends AbstractMap implements StrictOptimizedMapOps, DefaultSerializable {
   private final BitmapIndexedMapNode rootNode;

   public static ReusableBuilder newBuilder() {
      HashMap$ var10000 = HashMap$.MODULE$;
      return new HashMapBuilder();
   }

   public static HashMap from(final IterableOnce source) {
      return HashMap$.MODULE$.from(source);
   }

   public Object writeReplace() {
      return DefaultSerializable.writeReplace$(this);
   }

   public IterableOps map(final Function1 f) {
      return scala.collection.StrictOptimizedMapOps.map$(this, f);
   }

   public IterableOps flatMap(final Function1 f) {
      return scala.collection.StrictOptimizedMapOps.flatMap$(this, f);
   }

   public IterableOps collect(final PartialFunction pf) {
      return scala.collection.StrictOptimizedMapOps.collect$(this, pf);
   }

   /** @deprecated */
   public IterableOps $plus(final Tuple2 elem1, final Tuple2 elem2, final Seq elems) {
      return scala.collection.StrictOptimizedMapOps.$plus$(this, elem1, elem2, elems);
   }

   public Tuple2 unzip(final Function1 asPair) {
      return StrictOptimizedIterableOps.unzip$(this, asPair);
   }

   public Tuple3 unzip3(final Function1 asTriple) {
      return StrictOptimizedIterableOps.unzip3$(this, asTriple);
   }

   public Object map(final Function1 f) {
      return StrictOptimizedIterableOps.map$(this, f);
   }

   public final Object strictOptimizedMap(final Builder b, final Function1 f) {
      return StrictOptimizedIterableOps.strictOptimizedMap$(this, b, f);
   }

   public Object flatMap(final Function1 f) {
      return StrictOptimizedIterableOps.flatMap$(this, f);
   }

   public final Object strictOptimizedFlatMap(final Builder b, final Function1 f) {
      return StrictOptimizedIterableOps.strictOptimizedFlatMap$(this, b, f);
   }

   public final Object strictOptimizedConcat(final IterableOnce that, final Builder b) {
      return StrictOptimizedIterableOps.strictOptimizedConcat$(this, that, b);
   }

   public Object collect(final PartialFunction pf) {
      return StrictOptimizedIterableOps.collect$(this, pf);
   }

   public final Object strictOptimizedCollect(final Builder b, final PartialFunction pf) {
      return StrictOptimizedIterableOps.strictOptimizedCollect$(this, b, pf);
   }

   public Object flatten(final Function1 toIterableOnce) {
      return StrictOptimizedIterableOps.flatten$(this, toIterableOnce);
   }

   public final Object strictOptimizedFlatten(final Builder b, final Function1 toIterableOnce) {
      return StrictOptimizedIterableOps.strictOptimizedFlatten$(this, b, toIterableOnce);
   }

   public Object zip(final IterableOnce that) {
      return StrictOptimizedIterableOps.zip$(this, that);
   }

   public final Object strictOptimizedZip(final IterableOnce that, final Builder b) {
      return StrictOptimizedIterableOps.strictOptimizedZip$(this, that, b);
   }

   public Object zipWithIndex() {
      return StrictOptimizedIterableOps.zipWithIndex$(this);
   }

   public Object scanLeft(final Object z, final Function2 op) {
      return StrictOptimizedIterableOps.scanLeft$(this, z, op);
   }

   public Object filter(final Function1 pred) {
      return StrictOptimizedIterableOps.filter$(this, pred);
   }

   public Object filterNot(final Function1 pred) {
      return StrictOptimizedIterableOps.filterNot$(this, pred);
   }

   public Tuple2 partitionMap(final Function1 f) {
      return StrictOptimizedIterableOps.partitionMap$(this, f);
   }

   public Object tapEach(final Function1 f) {
      return StrictOptimizedIterableOps.tapEach$(this, f);
   }

   public BitmapIndexedMapNode rootNode() {
      return this.rootNode;
   }

   public MapFactory mapFactory() {
      return HashMap$.MODULE$;
   }

   public int knownSize() {
      return this.rootNode().size();
   }

   public int size() {
      return this.rootNode().size();
   }

   public boolean isEmpty() {
      return this.rootNode().size() == 0;
   }

   public Set keySet() {
      if (this.size() == 0) {
         Set$ var10000 = Set$.MODULE$;
         return Set.EmptySet$.MODULE$;
      } else {
         return new HashKeySet();
      }
   }

   public Iterator iterator() {
      if (this.isEmpty()) {
         Iterator$ var10000 = Iterator$.MODULE$;
         return Iterator$.scala$collection$Iterator$$_empty;
      } else {
         return new MapKeyValueTupleIterator(this.rootNode());
      }
   }

   public Iterator keysIterator() {
      if (this.isEmpty()) {
         Iterator$ var10000 = Iterator$.MODULE$;
         return Iterator$.scala$collection$Iterator$$_empty;
      } else {
         return new MapKeyIterator(this.rootNode());
      }
   }

   public Iterator valuesIterator() {
      if (this.isEmpty()) {
         Iterator$ var10000 = Iterator$.MODULE$;
         return Iterator$.scala$collection$Iterator$$_empty;
      } else {
         return new MapValueIterator(this.rootNode());
      }
   }

   public Iterator reverseIterator() {
      if (this.isEmpty()) {
         Iterator$ var10000 = Iterator$.MODULE$;
         return Iterator$.scala$collection$Iterator$$_empty;
      } else {
         return new MapKeyValueTupleReverseIterator(this.rootNode());
      }
   }

   public Stepper stepper(final StepperShape shape) {
      AnyChampStepper$ var10001 = AnyChampStepper$.MODULE$;
      int var9 = this.size();
      BitmapIndexedMapNode var10002 = this.rootNode();
      Function2 from_extract = (node, i) -> $anonfun$stepper$1(node, BoxesRunTime.unboxToInt(i));
      BitmapIndexedMapNode from_root = var10002;
      int from_maxSize = var9;
      AnyChampStepper from_ans = new AnyChampStepper(from_maxSize, from_extract);
      from_ans.initRoot(from_root);
      AnyChampStepper var10 = from_ans;
      from_root = null;
      from_extract = null;
      Object var8 = null;
      return shape.parUnbox(var10);
   }

   public Stepper keyStepper(final StepperShape shape) {
      int var2 = shape.shape();
      if (StepperShape$.MODULE$.IntShape() == var2) {
         IntChampStepper$ var25 = IntChampStepper$.MODULE$;
         int var26 = this.size();
         BitmapIndexedMapNode var31 = this.rootNode();
         Function2 from_extract = (node, i) -> BoxesRunTime.boxToInteger($anonfun$keyStepper$1(node, BoxesRunTime.unboxToInt(i)));
         BitmapIndexedMapNode from_root = var31;
         int from_maxSize = var26;
         IntChampStepper from_ans = new IntChampStepper(from_maxSize, from_extract);
         from_ans.initRoot(from_root);
         return from_ans;
      } else if (StepperShape$.MODULE$.LongShape() == var2) {
         LongChampStepper$ var23 = LongChampStepper$.MODULE$;
         int var24 = this.size();
         BitmapIndexedMapNode var30 = this.rootNode();
         Function2 from_extract = (node, i) -> BoxesRunTime.boxToLong($anonfun$keyStepper$2(node, BoxesRunTime.unboxToInt(i)));
         BitmapIndexedMapNode from_root = var30;
         int from_maxSize = var24;
         LongChampStepper from_ans = new LongChampStepper(from_maxSize, from_extract);
         from_ans.initRoot(from_root);
         return from_ans;
      } else if (StepperShape$.MODULE$.DoubleShape() == var2) {
         DoubleChampStepper$ var10000 = DoubleChampStepper$.MODULE$;
         int var22 = this.size();
         BitmapIndexedMapNode var29 = this.rootNode();
         Function2 from_extract = (node, i) -> BoxesRunTime.boxToDouble($anonfun$keyStepper$3(node, BoxesRunTime.unboxToInt(i)));
         BitmapIndexedMapNode from_root = var29;
         int from_maxSize = var22;
         DoubleChampStepper from_ans = new DoubleChampStepper(from_maxSize, from_extract);
         from_ans.initRoot(from_root);
         return from_ans;
      } else {
         AnyChampStepper$ var10001 = AnyChampStepper$.MODULE$;
         int var27 = this.size();
         BitmapIndexedMapNode var10002 = this.rootNode();
         Function2 from_extract = (node, i) -> $anonfun$keyStepper$4(node, BoxesRunTime.unboxToInt(i));
         BitmapIndexedMapNode from_root = var10002;
         int from_maxSize = var27;
         AnyChampStepper from_ans = new AnyChampStepper(from_maxSize, from_extract);
         from_ans.initRoot(from_root);
         AnyChampStepper var28 = from_ans;
         from_root = null;
         from_extract = null;
         Object var21 = null;
         return shape.parUnbox(var28);
      }
   }

   public Stepper valueStepper(final StepperShape shape) {
      int var2 = shape.shape();
      if (StepperShape$.MODULE$.IntShape() == var2) {
         IntChampStepper$ var25 = IntChampStepper$.MODULE$;
         int var26 = this.size();
         BitmapIndexedMapNode var31 = this.rootNode();
         Function2 from_extract = (node, i) -> BoxesRunTime.boxToInteger($anonfun$valueStepper$1(node, BoxesRunTime.unboxToInt(i)));
         BitmapIndexedMapNode from_root = var31;
         int from_maxSize = var26;
         IntChampStepper from_ans = new IntChampStepper(from_maxSize, from_extract);
         from_ans.initRoot(from_root);
         return from_ans;
      } else if (StepperShape$.MODULE$.LongShape() == var2) {
         LongChampStepper$ var23 = LongChampStepper$.MODULE$;
         int var24 = this.size();
         BitmapIndexedMapNode var30 = this.rootNode();
         Function2 from_extract = (node, i) -> BoxesRunTime.boxToLong($anonfun$valueStepper$2(node, BoxesRunTime.unboxToInt(i)));
         BitmapIndexedMapNode from_root = var30;
         int from_maxSize = var24;
         LongChampStepper from_ans = new LongChampStepper(from_maxSize, from_extract);
         from_ans.initRoot(from_root);
         return from_ans;
      } else if (StepperShape$.MODULE$.DoubleShape() == var2) {
         DoubleChampStepper$ var10000 = DoubleChampStepper$.MODULE$;
         int var22 = this.size();
         BitmapIndexedMapNode var29 = this.rootNode();
         Function2 from_extract = (node, i) -> BoxesRunTime.boxToDouble($anonfun$valueStepper$3(node, BoxesRunTime.unboxToInt(i)));
         BitmapIndexedMapNode from_root = var29;
         int from_maxSize = var22;
         DoubleChampStepper from_ans = new DoubleChampStepper(from_maxSize, from_extract);
         from_ans.initRoot(from_root);
         return from_ans;
      } else {
         AnyChampStepper$ var10001 = AnyChampStepper$.MODULE$;
         int var27 = this.size();
         BitmapIndexedMapNode var10002 = this.rootNode();
         Function2 from_extract = (node, i) -> $anonfun$valueStepper$4(node, BoxesRunTime.unboxToInt(i));
         BitmapIndexedMapNode from_root = var10002;
         int from_maxSize = var27;
         AnyChampStepper from_ans = new AnyChampStepper(from_maxSize, from_extract);
         from_ans.initRoot(from_root);
         AnyChampStepper var28 = from_ans;
         from_root = null;
         from_extract = null;
         Object var21 = null;
         return shape.parUnbox(var28);
      }
   }

   public final boolean contains(final Object key) {
      int keyUnimprovedHash = Statics.anyHash(key);
      int keyHash = Hashing$.MODULE$.improve(keyUnimprovedHash);
      return this.rootNode().containsKey(key, keyUnimprovedHash, keyHash, 0);
   }

   public Object apply(final Object key) {
      int keyUnimprovedHash = Statics.anyHash(key);
      int keyHash = Hashing$.MODULE$.improve(keyUnimprovedHash);
      return this.rootNode().apply(key, keyUnimprovedHash, keyHash, 0);
   }

   public Option get(final Object key) {
      int keyUnimprovedHash = Statics.anyHash(key);
      int keyHash = Hashing$.MODULE$.improve(keyUnimprovedHash);
      return this.rootNode().get(key, keyUnimprovedHash, keyHash, 0);
   }

   public Object getOrElse(final Object key, final Function0 default) {
      int keyUnimprovedHash = Statics.anyHash(key);
      int keyHash = Hashing$.MODULE$.improve(keyUnimprovedHash);
      BitmapIndexedMapNode var10000 = this.rootNode();
      byte getOrElse_shift = 0;
      if (var10000 == null) {
         throw null;
      } else {
         BitmapIndexedMapNode getOrElse_this = var10000;
         Node$ var12 = Node$.MODULE$;
         int getOrElse_mask = keyHash >>> getOrElse_shift & 31;
         var12 = Node$.MODULE$;
         int getOrElse_bitpos = 1 << getOrElse_mask;
         if ((getOrElse_this.dataMap() & getOrElse_bitpos) != 0) {
            int getOrElse_index = Node$.MODULE$.indexFrom(getOrElse_this.dataMap(), getOrElse_mask, getOrElse_bitpos);
            Object getOrElse_key0 = getOrElse_this.content()[2 * getOrElse_index];
            return BoxesRunTime.equals(key, getOrElse_key0) ? getOrElse_this.content()[2 * getOrElse_index + 1] : default.apply();
         } else if ((getOrElse_this.nodeMap() & getOrElse_bitpos) != 0) {
            int getOrElse_index = Node$.MODULE$.indexFrom(getOrElse_this.nodeMap(), getOrElse_mask, getOrElse_bitpos);
            return getOrElse_this.getNode(getOrElse_index).getOrElse(key, keyUnimprovedHash, keyHash, getOrElse_shift + 5, default);
         } else {
            return default.apply();
         }
      }
   }

   private HashMap newHashMapOrThis(final BitmapIndexedMapNode newRootNode) {
      return newRootNode == this.rootNode() ? this : new HashMap(newRootNode);
   }

   public HashMap updated(final Object key, final Object value) {
      int keyUnimprovedHash = Statics.anyHash(key);
      BitmapIndexedMapNode newHashMapOrThis_newRootNode = this.rootNode().updated(key, value, keyUnimprovedHash, Hashing$.MODULE$.improve(keyUnimprovedHash), 0, true);
      return newHashMapOrThis_newRootNode == this.rootNode() ? this : new HashMap(newHashMapOrThis_newRootNode);
   }

   public HashMap updatedWith(final Object key, final Function1 remappingFunction) {
      return (HashMap)MapOps.updatedWith$(this, key, remappingFunction);
   }

   public HashMap removed(final Object key) {
      int keyUnimprovedHash = Statics.anyHash(key);
      BitmapIndexedMapNode newHashMapOrThis_newRootNode = this.rootNode().removed(key, keyUnimprovedHash, Hashing$.MODULE$.improve(keyUnimprovedHash), 0);
      return newHashMapOrThis_newRootNode == this.rootNode() ? this : new HashMap(newHashMapOrThis_newRootNode);
   }

   public HashMap concat(final IterableOnce that) {
      if (that instanceof HashMap) {
         HashMap var2 = (HashMap)that;
         if (this.isEmpty()) {
            return var2;
         } else {
            BitmapIndexedMapNode newNode = this.rootNode().concat(var2.rootNode(), 0);
            if (newNode == var2.rootNode()) {
               return var2;
            } else {
               return newNode == this.rootNode() ? this : new HashMap(newNode);
            }
         }
      } else if (that instanceof scala.collection.mutable.HashMap) {
         scala.collection.mutable.HashMap var4 = (scala.collection.mutable.HashMap)that;
         Iterator iter = var4.nodeIterator();
         BitmapIndexedMapNode current = this.rootNode();

         while(iter.hasNext()) {
            scala.collection.mutable.HashMap.Node next = (scala.collection.mutable.HashMap.Node)iter.next();
            int originalHash = var4.unimproveHash(next.hash());
            int improved = Hashing$.MODULE$.improve(originalHash);
            current = current.updated(next.key(), next.value(), originalHash, improved, 0, true);
            if (current != this.rootNode()) {
               Node$ var33 = Node$.MODULE$;
               var33 = Node$.MODULE$;
               int maskFrom_shift = 0;
               int bitposFrom_mask = improved >>> maskFrom_shift & 31;

               scala.collection.mutable.HashMap.Node next;
               int originalHash;
               for(int shallowlyMutableNodeMap = 1 << bitposFrom_mask; iter.hasNext(); shallowlyMutableNodeMap = current.updateWithShallowMutations(next.key(), next.value(), originalHash, Hashing$.MODULE$.improve(originalHash), 0, shallowlyMutableNodeMap)) {
                  next = (scala.collection.mutable.HashMap.Node)iter.next();
                  originalHash = var4.unimproveHash(next.hash());
               }

               return new HashMap(current);
            }
         }

         return this;
      } else if (!(that instanceof LinkedHashMap)) {
         class accum$1 extends AbstractFunction2 implements Function1 {
            private boolean changed;
            private int shallowlyMutableNodeMap;
            private BitmapIndexedMapNode current;
            // $FF: synthetic field
            private final HashMap $outer;

            public boolean apply$mcZD$sp(final double v1) {
               return Function1.apply$mcZD$sp$(this, v1);
            }

            public double apply$mcDD$sp(final double v1) {
               return Function1.apply$mcDD$sp$(this, v1);
            }

            public float apply$mcFD$sp(final double v1) {
               return Function1.apply$mcFD$sp$(this, v1);
            }

            public int apply$mcID$sp(final double v1) {
               return Function1.apply$mcID$sp$(this, v1);
            }

            public long apply$mcJD$sp(final double v1) {
               return Function1.apply$mcJD$sp$(this, v1);
            }

            public void apply$mcVD$sp(final double v1) {
               Function1.apply$mcVD$sp$(this, v1);
            }

            public boolean apply$mcZF$sp(final float v1) {
               return Function1.apply$mcZF$sp$(this, v1);
            }

            public double apply$mcDF$sp(final float v1) {
               return Function1.apply$mcDF$sp$(this, v1);
            }

            public float apply$mcFF$sp(final float v1) {
               return Function1.apply$mcFF$sp$(this, v1);
            }

            public int apply$mcIF$sp(final float v1) {
               return Function1.apply$mcIF$sp$(this, v1);
            }

            public long apply$mcJF$sp(final float v1) {
               return Function1.apply$mcJF$sp$(this, v1);
            }

            public void apply$mcVF$sp(final float v1) {
               Function1.apply$mcVF$sp$(this, v1);
            }

            public boolean apply$mcZI$sp(final int v1) {
               return Function1.apply$mcZI$sp$(this, v1);
            }

            public double apply$mcDI$sp(final int v1) {
               return Function1.apply$mcDI$sp$(this, v1);
            }

            public float apply$mcFI$sp(final int v1) {
               return Function1.apply$mcFI$sp$(this, v1);
            }

            public int apply$mcII$sp(final int v1) {
               return Function1.apply$mcII$sp$(this, v1);
            }

            public long apply$mcJI$sp(final int v1) {
               return Function1.apply$mcJI$sp$(this, v1);
            }

            public void apply$mcVI$sp(final int v1) {
               Function1.apply$mcVI$sp$(this, v1);
            }

            public boolean apply$mcZJ$sp(final long v1) {
               return Function1.apply$mcZJ$sp$(this, v1);
            }

            public double apply$mcDJ$sp(final long v1) {
               return Function1.apply$mcDJ$sp$(this, v1);
            }

            public float apply$mcFJ$sp(final long v1) {
               return Function1.apply$mcFJ$sp$(this, v1);
            }

            public int apply$mcIJ$sp(final long v1) {
               return Function1.apply$mcIJ$sp$(this, v1);
            }

            public long apply$mcJJ$sp(final long v1) {
               return Function1.apply$mcJJ$sp$(this, v1);
            }

            public void apply$mcVJ$sp(final long v1) {
               Function1.apply$mcVJ$sp$(this, v1);
            }

            public Function1 compose(final Function1 g) {
               return Function1.compose$(this, g);
            }

            public Function1 andThen(final Function1 g) {
               return Function1.andThen$(this, g);
            }

            public String toString() {
               return Function1.toString$(this);
            }

            public boolean changed() {
               return this.changed;
            }

            public void changed_$eq(final boolean x$1) {
               this.changed = x$1;
            }

            public int shallowlyMutableNodeMap() {
               return this.shallowlyMutableNodeMap;
            }

            public void shallowlyMutableNodeMap_$eq(final int x$1) {
               this.shallowlyMutableNodeMap = x$1;
            }

            public BitmapIndexedMapNode current() {
               return this.current;
            }

            public void current_$eq(final BitmapIndexedMapNode x$1) {
               this.current = x$1;
            }

            public void apply(final Tuple2 kv) {
               this.apply(kv._1(), kv._2());
            }

            public void apply(final Object key, final Object value) {
               int originalHash = Statics.anyHash(key);
               int improved = Hashing$.MODULE$.improve(originalHash);
               if (!this.changed()) {
                  this.current_$eq(this.current().updated(key, value, originalHash, improved, 0, true));
                  if (this.current() != this.$outer.rootNode()) {
                     this.changed_$eq(true);
                     Node$ var10001 = Node$.MODULE$;
                     var10001 = Node$.MODULE$;
                     int maskFrom_shift = 0;
                     int bitposFrom_mask = improved >>> maskFrom_shift & 31;
                     this.shallowlyMutableNodeMap_$eq(1 << bitposFrom_mask);
                  }
               } else {
                  this.shallowlyMutableNodeMap_$eq(this.current().updateWithShallowMutations(key, value, originalHash, improved, 0, this.shallowlyMutableNodeMap()));
               }
            }

            public accum$1() {
               if (HashMap.this == null) {
                  throw null;
               } else {
                  this.$outer = HashMap.this;
                  super();
                  this.changed = false;
                  this.shallowlyMutableNodeMap = 0;
                  this.current = HashMap.this.rootNode();
               }
            }
         }

         if (that instanceof Map) {
            Map var22 = (Map)that;
            if (var22.isEmpty()) {
               return this;
            } else {
               accum$1 accum = new accum$1();
               var22.foreachEntry(accum);
               BitmapIndexedMapNode newHashMapOrThis_newRootNode = accum.current();
               return newHashMapOrThis_newRootNode == this.rootNode() ? this : new HashMap(newHashMapOrThis_newRootNode);
            }
         } else {
            Iterator it = that.iterator();
            if (it.isEmpty()) {
               return this;
            } else {
               accum$1 accum = new accum$1();
               it.foreach(accum);
               BitmapIndexedMapNode newHashMapOrThis_newRootNode = accum.current();
               return newHashMapOrThis_newRootNode == this.rootNode() ? this : new HashMap(newHashMapOrThis_newRootNode);
            }
         }
      } else {
         LinkedHashMap var13 = (LinkedHashMap)that;
         Iterator iter = var13.entryIterator();
         BitmapIndexedMapNode current = this.rootNode();

         while(iter.hasNext()) {
            LinkedHashMap.LinkedEntry next = (LinkedHashMap.LinkedEntry)iter.next();
            int originalHash = var13.unimproveHash(next.hash());
            int improved = Hashing$.MODULE$.improve(originalHash);
            current = current.updated(next.key(), next.value(), originalHash, improved, 0, true);
            if (current != this.rootNode()) {
               Node$ var10000 = Node$.MODULE$;
               var10000 = Node$.MODULE$;
               int maskFrom_shift = 0;
               int bitposFrom_mask = improved >>> maskFrom_shift & 31;

               LinkedHashMap.LinkedEntry next;
               int originalHash;
               for(int shallowlyMutableNodeMap = 1 << bitposFrom_mask; iter.hasNext(); shallowlyMutableNodeMap = current.updateWithShallowMutations(next.key(), next.value(), originalHash, Hashing$.MODULE$.improve(originalHash), 0, shallowlyMutableNodeMap)) {
                  next = (LinkedHashMap.LinkedEntry)iter.next();
                  originalHash = var13.unimproveHash(next.hash());
               }

               return new HashMap(current);
            }
         }

         return this;
      }
   }

   public HashMap tail() {
      Object $minus_key = this.head()._1();
      return this.removed($minus_key);
   }

   public HashMap init() {
      Object $minus_key = this.last()._1();
      return this.removed($minus_key);
   }

   public Tuple2 head() {
      return (Tuple2)this.iterator().next();
   }

   public Tuple2 last() {
      return (Tuple2)this.reverseIterator().next();
   }

   public void foreach(final Function1 f) {
      BitmapIndexedMapNode var10000 = this.rootNode();
      if (var10000 == null) {
         throw null;
      } else {
         BitmapIndexedMapNode foreach_this = var10000;
         int foreach_iN = foreach_this.payloadArity();

         for(int foreach_i = 0; foreach_i < foreach_iN; ++foreach_i) {
            f.apply(foreach_this.getPayload(foreach_i));
         }

         int foreach_jN = foreach_this.nodeArity();

         for(int foreach_j = 0; foreach_j < foreach_jN; ++foreach_j) {
            foreach_this.getNode(foreach_j).foreach(f);
         }

      }
   }

   public void foreachEntry(final Function2 f) {
      BitmapIndexedMapNode var10000 = this.rootNode();
      if (var10000 == null) {
         throw null;
      } else {
         BitmapIndexedMapNode foreachEntry_this = var10000;
         int foreachEntry_iN = foreachEntry_this.payloadArity();

         for(int foreachEntry_i = 0; foreachEntry_i < foreachEntry_iN; ++foreachEntry_i) {
            f.apply(foreachEntry_this.content()[2 * foreachEntry_i], foreachEntry_this.content()[2 * foreachEntry_i + 1]);
         }

         int foreachEntry_jN = foreachEntry_this.nodeArity();

         for(int foreachEntry_j = 0; foreachEntry_j < foreachEntry_jN; ++foreachEntry_j) {
            foreachEntry_this.getNode(foreachEntry_j).foreachEntry(f);
         }

      }
   }

   public void foreachWithHash(final Function3 f) {
      BitmapIndexedMapNode var10000 = this.rootNode();
      if (var10000 == null) {
         throw null;
      } else {
         BitmapIndexedMapNode foreachWithHash_this = var10000;
         int foreachWithHash_i = 0;

         for(int foreachWithHash_iN = foreachWithHash_this.payloadArity(); foreachWithHash_i < foreachWithHash_iN; ++foreachWithHash_i) {
            f.apply(foreachWithHash_this.content()[2 * foreachWithHash_i], foreachWithHash_this.content()[2 * foreachWithHash_i + 1], foreachWithHash_this.originalHashes()[foreachWithHash_i]);
         }

         int foreachWithHash_jN = foreachWithHash_this.nodeArity();

         for(int foreachWithHash_j = 0; foreachWithHash_j < foreachWithHash_jN; ++foreachWithHash_j) {
            foreachWithHash_this.getNode(foreachWithHash_j).foreachWithHash(f);
         }

      }
   }

   public boolean equals(final Object that) {
      if (!(that instanceof HashMap)) {
         return scala.collection.Map.equals$(this, that);
      } else {
         HashMap var2 = (HashMap)that;
         if (this != var2) {
            BitmapIndexedMapNode var10000 = this.rootNode();
            BitmapIndexedMapNode var3 = var2.rootNode();
            if (var10000 == null) {
               if (var3 == null) {
                  return true;
               }
            } else if (var10000.equals(var3)) {
               return true;
            }

            return false;
         } else {
            return true;
         }
      }
   }

   public int hashCode() {
      if (this.isEmpty()) {
         return MurmurHash3$.MODULE$.emptyMapHash();
      } else {
         MapKeyValueTupleHashIterator hashIterator = new MapKeyValueTupleHashIterator(this.rootNode());
         return MurmurHash3$.MODULE$.unorderedHash(hashIterator, MurmurHash3$.MODULE$.mapSeed());
      }
   }

   public String className() {
      return "HashMap";
   }

   public HashMap merged(final HashMap that, final Function2 mergef) {
      if (mergef == null) {
         if (that == null) {
            throw null;
         } else {
            return (HashMap)scala.collection.MapOps.$plus$plus$(that, this);
         }
      } else if (this.isEmpty()) {
         return that;
      } else if (that.isEmpty()) {
         return this;
      } else if (this.size() == 1) {
         Tuple2 var3 = this.rootNode().getPayload(0);
         if (var3 != null) {
            Object k = var3._1();
            Object v = var3._2();
            int originalHash = this.rootNode().getHash(0);
            int improved = Hashing$.MODULE$.improve(originalHash);
            if (that.rootNode().containsKey(k, originalHash, improved, 0)) {
               Tuple2 thatPayload = that.rootNode().getTuple(k, originalHash, improved, 0);
               Tuple2 var9 = (Tuple2)mergef.apply(var3, thatPayload);
               if (var9 != null) {
                  Object mergedK = var9._1();
                  Object mergedV = var9._2();
                  int mergedOriginalHash = Statics.anyHash(mergedK);
                  int mergedImprovedHash = Hashing$.MODULE$.improve(mergedOriginalHash);
                  return new HashMap(that.rootNode().removed(thatPayload._1(), originalHash, improved, 0).updated(mergedK, mergedV, mergedOriginalHash, mergedImprovedHash, 0, true));
               } else {
                  throw new MatchError((Object)null);
               }
            } else {
               return new HashMap(that.rootNode().updated(k, v, originalHash, improved, 0, true));
            }
         } else {
            throw new MatchError((Object)null);
         }
      } else if (that.size() == 0) {
         Tuple2 var14 = this.rootNode().getPayload(0);
         if (var14 != null) {
            Object k = var14._1();
            Object v = var14._2();
            int thatOriginalHash = this.rootNode().getHash(0);
            int thatImproved = Hashing$.MODULE$.improve(thatOriginalHash);
            if (this.rootNode().containsKey(k, thatOriginalHash, thatImproved, 0)) {
               Tuple2 payload = this.rootNode().getTuple(k, thatOriginalHash, thatImproved, 0);
               Tuple2 var20 = (Tuple2)mergef.apply(payload, var14);
               if (var20 != null) {
                  Object mergedK = var20._1();
                  Object mergedV = var20._2();
                  int mergedOriginalHash = Statics.anyHash(mergedK);
                  int mergedImprovedHash = Hashing$.MODULE$.improve(mergedOriginalHash);
                  return new HashMap(this.rootNode().updated(mergedK, mergedV, mergedOriginalHash, mergedImprovedHash, 0, true));
               } else {
                  throw new MatchError((Object)null);
               }
            } else {
               return new HashMap(this.rootNode().updated(k, v, thatOriginalHash, thatImproved, 0, true));
            }
         } else {
            throw new MatchError((Object)null);
         }
      } else {
         HashMapBuilder builder = new HashMapBuilder();
         BitmapIndexedMapNode var10000 = this.rootNode();
         BitmapIndexedMapNode var10001 = that.rootNode();
         byte mergeInto_shift = 0;
         BitmapIndexedMapNode mergeInto_that = var10001;
         if (var10000 == null) {
            throw null;
         } else {
            BitmapIndexedMapNode mergeInto_this = var10000;
            if (!(mergeInto_that instanceof BitmapIndexedMapNode)) {
               throw new MatchError(mergeInto_that);
            } else {
               if (mergeInto_this.size() == 0) {
                  ((MapNode)mergeInto_that).buildTo(builder);
               } else if (mergeInto_that.size() == 0) {
                  mergeInto_this.buildTo(builder);
               } else {
                  int mergeInto_allMap = mergeInto_this.dataMap() | mergeInto_that.dataMap() | mergeInto_this.nodeMap() | mergeInto_that.nodeMap();
                  int mergeInto_minIndex = Integer.numberOfTrailingZeros(mergeInto_allMap);
                  int mergeInto_maxIndex = 32 - Integer.numberOfLeadingZeros(mergeInto_allMap);
                  int mergeInto_index = mergeInto_minIndex;
                  int mergeInto_leftIdx = 0;

                  for(int mergeInto_rightIdx = 0; mergeInto_index < mergeInto_maxIndex; ++mergeInto_index) {
                     Node$ var64 = Node$.MODULE$;
                     int mergeInto_bitpos = 1 << mergeInto_index;
                     if ((mergeInto_bitpos & mergeInto_this.dataMap()) != 0) {
                        Object mergeInto_leftKey = mergeInto_this.content()[2 * mergeInto_leftIdx];
                        Object mergeInto_leftValue = mergeInto_this.content()[2 * mergeInto_leftIdx + 1];
                        int mergeInto_leftOriginalHash = mergeInto_this.originalHashes()[mergeInto_leftIdx];
                        if ((mergeInto_bitpos & mergeInto_that.dataMap()) == 0) {
                           if ((mergeInto_bitpos & mergeInto_that.nodeMap()) != 0) {
                              MapNode mergeInto_subNode = mergeInto_that.getNode(mergeInto_that.nodeIndex(mergeInto_bitpos));
                              int mergeInto_leftImprovedHash = Hashing$.MODULE$.improve(mergeInto_leftOriginalHash);
                              MapNode mergeInto_removed = mergeInto_subNode.removed(mergeInto_leftKey, mergeInto_leftOriginalHash, mergeInto_leftImprovedHash, mergeInto_shift + 5);
                              if (mergeInto_removed == mergeInto_subNode) {
                                 mergeInto_subNode.buildTo(builder);
                                 builder.addOne(mergeInto_leftKey, mergeInto_leftValue, mergeInto_leftOriginalHash, mergeInto_leftImprovedHash);
                              } else {
                                 mergeInto_removed.buildTo(builder);
                                 builder.addOne((Tuple2)mergef.apply(new Tuple2(mergeInto_leftKey, mergeInto_leftValue), mergeInto_subNode.getTuple(mergeInto_leftKey, mergeInto_leftOriginalHash, mergeInto_leftImprovedHash, mergeInto_shift + 5)));
                              }
                           } else {
                              builder.addOne(mergeInto_leftKey, mergeInto_leftValue, mergeInto_leftOriginalHash);
                           }
                        } else {
                           Object mergeInto_rightKey = mergeInto_that.content()[2 * mergeInto_rightIdx];
                           Object mergeInto_rightValue = mergeInto_that.content()[2 * mergeInto_rightIdx + 1];
                           int mergeInto_rightOriginalHash = mergeInto_that.originalHashes()[mergeInto_rightIdx];
                           if (mergeInto_leftOriginalHash == mergeInto_rightOriginalHash && BoxesRunTime.equals(mergeInto_leftKey, mergeInto_rightKey)) {
                              builder.addOne((Tuple2)mergef.apply(new Tuple2(mergeInto_leftKey, mergeInto_leftValue), new Tuple2(mergeInto_rightKey, mergeInto_rightValue)));
                           } else {
                              builder.addOne(mergeInto_leftKey, mergeInto_leftValue, mergeInto_leftOriginalHash);
                              builder.addOne(mergeInto_rightKey, mergeInto_rightValue, mergeInto_rightOriginalHash);
                           }

                           ++mergeInto_rightIdx;
                        }

                        ++mergeInto_leftIdx;
                     } else if ((mergeInto_bitpos & mergeInto_this.nodeMap()) != 0) {
                        if ((mergeInto_bitpos & mergeInto_that.dataMap()) != 0) {
                           Object mergeInto_rightKey = mergeInto_that.content()[2 * mergeInto_rightIdx];
                           Object mergeInto_rightValue = mergeInto_that.content()[2 * mergeInto_rightIdx + 1];
                           int mergeInto_rightOriginalHash = mergeInto_that.originalHashes()[mergeInto_rightIdx];
                           int mergeInto_rightImprovedHash = Hashing$.MODULE$.improve(mergeInto_rightOriginalHash);
                           MapNode mergeInto_subNode = mergeInto_this.getNode(mergeInto_this.nodeIndex(mergeInto_bitpos));
                           MapNode mergeInto_removed = mergeInto_subNode.removed(mergeInto_rightKey, mergeInto_rightOriginalHash, mergeInto_rightImprovedHash, mergeInto_shift + 5);
                           if (mergeInto_removed == mergeInto_subNode) {
                              mergeInto_subNode.buildTo(builder);
                              builder.addOne(mergeInto_rightKey, mergeInto_rightValue, mergeInto_rightOriginalHash, mergeInto_rightImprovedHash);
                           } else {
                              mergeInto_removed.buildTo(builder);
                              builder.addOne((Tuple2)mergef.apply(mergeInto_subNode.getTuple(mergeInto_rightKey, mergeInto_rightOriginalHash, mergeInto_rightImprovedHash, mergeInto_shift + 5), new Tuple2(mergeInto_rightKey, mergeInto_rightValue)));
                           }

                           ++mergeInto_rightIdx;
                        } else if ((mergeInto_bitpos & mergeInto_that.nodeMap()) != 0) {
                           mergeInto_this.getNode(mergeInto_this.nodeIndex(mergeInto_bitpos)).mergeInto(mergeInto_that.getNode(mergeInto_that.nodeIndex(mergeInto_bitpos)), builder, mergeInto_shift + 5, mergef);
                        } else {
                           mergeInto_this.getNode(mergeInto_this.nodeIndex(mergeInto_bitpos)).buildTo(builder);
                        }
                     } else if ((mergeInto_bitpos & mergeInto_that.dataMap()) != 0) {
                        int mergeInto_dataIndex = mergeInto_that.dataIndex(mergeInto_bitpos);
                        builder.addOne(mergeInto_that.content()[2 * mergeInto_dataIndex], mergeInto_that.content()[2 * mergeInto_dataIndex + 1], mergeInto_that.originalHashes()[mergeInto_dataIndex]);
                        ++mergeInto_rightIdx;
                     } else if ((mergeInto_bitpos & mergeInto_that.nodeMap()) != 0) {
                        mergeInto_that.getNode(mergeInto_that.nodeIndex(mergeInto_bitpos)).buildTo(builder);
                     }
                  }
               }

               Object var52 = null;
               Object var53 = null;
               Object var54 = null;
               Object var55 = null;
               Object var56 = null;
               Object var57 = null;
               Object var58 = null;
               Object var59 = null;
               Object var60 = null;
               Object var61 = null;
               Object var62 = null;
               Object var63 = null;
               return builder.result();
            }
         }
      }
   }

   public HashMap transform(final Function2 f) {
      BitmapIndexedMapNode var10000 = this.rootNode();
      if (var10000 == null) {
         throw null;
      } else {
         BitmapIndexedMapNode transform_this = var10000;
         Object[] transform_newContent = null;
         int transform_iN = transform_this.payloadArity();
         int transform_jN = transform_this.nodeArity();
         int transform_newContentLength = transform_this.content().length;

         for(int transform_i = 0; transform_i < transform_iN; ++transform_i) {
            Object transform_key = transform_this.content()[2 * transform_i];
            Object transform_value = transform_this.content()[2 * transform_i + 1];
            Object transform_newValue = f.apply(transform_key, transform_value);
            if (transform_newContent == null) {
               if (transform_newValue != transform_value) {
                  transform_newContent = transform_this.content().clone();
                  transform_newContent[2 * transform_i + 1] = transform_newValue;
               }
            } else {
               transform_newContent[2 * transform_i + 1] = transform_newValue;
            }
         }

         for(int transform_j = 0; transform_j < transform_jN; ++transform_j) {
            MapNode transform_node = transform_this.getNode(transform_j);
            MapNode transform_newNode = transform_node.transform(f);
            if (transform_newContent == null) {
               if (transform_newNode != transform_node) {
                  transform_newContent = transform_this.content().clone();
                  transform_newContent[transform_newContentLength - transform_j - 1] = transform_newNode;
               }
            } else {
               transform_newContent[transform_newContentLength - transform_j - 1] = transform_newNode;
            }
         }

         var10000 = transform_newContent == null ? transform_this : new BitmapIndexedMapNode(transform_this.dataMap(), transform_this.nodeMap(), transform_newContent, transform_this.originalHashes(), transform_this.size(), transform_this.cachedJavaKeySetHashCode());
         Object var15 = null;
         transform_newContent = null;
         Object var17 = null;
         Object var18 = null;
         Object var19 = null;
         Object var20 = null;
         Object var21 = null;
         BitmapIndexedMapNode newHashMapOrThis_newRootNode = var10000;
         if (newHashMapOrThis_newRootNode == this.rootNode()) {
            return this;
         } else {
            return new HashMap(newHashMapOrThis_newRootNode);
         }
      }
   }

   public HashMap filterImpl(final Function1 pred, final boolean isFlipped) {
      BitmapIndexedMapNode var10000 = this.rootNode();
      if (var10000 == null) {
         throw null;
      } else {
         BitmapIndexedMapNode filterImpl_this = var10000;
         if (filterImpl_this.size() == 0) {
            var10000 = filterImpl_this;
         } else if (filterImpl_this.size() == 1) {
            var10000 = BoxesRunTime.unboxToBoolean(pred.apply(filterImpl_this.getPayload(0))) != isFlipped ? filterImpl_this : MapNode$.MODULE$.empty();
         } else if (filterImpl_this.nodeMap() == 0) {
            int filterImpl_minimumIndex = Integer.numberOfTrailingZeros(filterImpl_this.dataMap());
            int filterImpl_maximumIndex = 32 - Integer.numberOfLeadingZeros(filterImpl_this.dataMap());
            int filterImpl_newDataMap = 0;
            int filterImpl_newCachedHashCode = 0;
            int filterImpl_dataIndex = 0;

            for(int filterImpl_i = filterImpl_minimumIndex; filterImpl_i < filterImpl_maximumIndex; ++filterImpl_i) {
               Node$ var67 = Node$.MODULE$;
               int filterImpl_bitpos = 1 << filterImpl_i;
               if ((filterImpl_bitpos & filterImpl_this.dataMap()) != 0) {
                  Tuple2 filterImpl_payload = filterImpl_this.getPayload(filterImpl_dataIndex);
                  if (BoxesRunTime.unboxToBoolean(pred.apply(filterImpl_payload)) != isFlipped) {
                     filterImpl_newDataMap |= filterImpl_bitpos;
                     filterImpl_newCachedHashCode += Hashing$.MODULE$.improve(filterImpl_this.originalHashes()[filterImpl_dataIndex]);
                  }

                  ++filterImpl_dataIndex;
               }
            }

            if (filterImpl_newDataMap == 0) {
               var10000 = MapNode$.MODULE$.empty();
            } else if (filterImpl_newDataMap == filterImpl_this.dataMap()) {
               var10000 = filterImpl_this;
            } else {
               int filterImpl_newSize = Integer.bitCount(filterImpl_newDataMap);
               Object[] filterImpl_newContent = new Object[filterImpl_newSize * 2];
               int[] filterImpl_newOriginalHashCodes = new int[filterImpl_newSize];
               int filterImpl_newMaximumIndex = 32 - Integer.numberOfLeadingZeros(filterImpl_newDataMap);
               int filterImpl_j = Integer.numberOfTrailingZeros(filterImpl_newDataMap);

               for(int filterImpl_newDataIndex = 0; filterImpl_j < filterImpl_newMaximumIndex; ++filterImpl_j) {
                  Node$ var68 = Node$.MODULE$;
                  int filterImpl_bitpos = 1 << filterImpl_j;
                  if ((filterImpl_bitpos & filterImpl_newDataMap) != 0) {
                     var68 = Node$.MODULE$;
                     int filterImpl_oldIndex = Integer.bitCount(filterImpl_this.dataMap() & filterImpl_bitpos - 1);
                     filterImpl_newContent[filterImpl_newDataIndex * 2] = filterImpl_this.content()[filterImpl_oldIndex * 2];
                     filterImpl_newContent[filterImpl_newDataIndex * 2 + 1] = filterImpl_this.content()[filterImpl_oldIndex * 2 + 1];
                     filterImpl_newOriginalHashCodes[filterImpl_newDataIndex] = filterImpl_this.originalHashes()[filterImpl_oldIndex];
                     ++filterImpl_newDataIndex;
                  }
               }

               var10000 = new BitmapIndexedMapNode(filterImpl_newDataMap, 0, filterImpl_newContent, filterImpl_newOriginalHashCodes, filterImpl_newSize, filterImpl_newCachedHashCode);
            }
         } else {
            int filterImpl_allMap = filterImpl_this.dataMap() | filterImpl_this.nodeMap();
            int filterImpl_minimumIndex = Integer.numberOfTrailingZeros(filterImpl_allMap);
            int filterImpl_maximumIndex = 32 - Integer.numberOfLeadingZeros(filterImpl_allMap);
            int filterImpl_oldDataPassThrough = 0;
            int filterImpl_nodeMigrateToDataTargetMap = 0;
            scala.collection.mutable.Queue filterImpl_nodesToMigrateToData = null;
            int filterImpl_nodesToPassThroughMap = 0;
            int filterImpl_mapOfNewNodes = 0;
            scala.collection.mutable.Queue filterImpl_newNodes = null;
            int filterImpl_newDataMap = 0;
            int filterImpl_newNodeMap = 0;
            int filterImpl_newSize = 0;
            int filterImpl_newCachedHashCode = 0;
            int filterImpl_dataIndex = 0;
            int filterImpl_nodeIndex = 0;

            for(int filterImpl_i = filterImpl_minimumIndex; filterImpl_i < filterImpl_maximumIndex; ++filterImpl_i) {
               Node$ var70 = Node$.MODULE$;
               int filterImpl_bitpos = 1 << filterImpl_i;
               if ((filterImpl_bitpos & filterImpl_this.dataMap()) != 0) {
                  Tuple2 filterImpl_payload = filterImpl_this.getPayload(filterImpl_dataIndex);
                  if (BoxesRunTime.unboxToBoolean(pred.apply(filterImpl_payload)) != isFlipped) {
                     filterImpl_newDataMap |= filterImpl_bitpos;
                     filterImpl_oldDataPassThrough |= filterImpl_bitpos;
                     ++filterImpl_newSize;
                     filterImpl_newCachedHashCode += Hashing$.MODULE$.improve(filterImpl_this.originalHashes()[filterImpl_dataIndex]);
                  }

                  ++filterImpl_dataIndex;
               } else if ((filterImpl_bitpos & filterImpl_this.nodeMap()) != 0) {
                  MapNode filterImpl_oldSubNode = filterImpl_this.getNode(filterImpl_nodeIndex);
                  MapNode filterImpl_newSubNode = filterImpl_oldSubNode.filterImpl(pred, isFlipped);
                  filterImpl_newSize += filterImpl_newSubNode.size();
                  filterImpl_newCachedHashCode += filterImpl_newSubNode.cachedJavaKeySetHashCode();
                  if (filterImpl_newSubNode.size() > 1) {
                     filterImpl_newNodeMap |= filterImpl_bitpos;
                     if (filterImpl_oldSubNode == filterImpl_newSubNode) {
                        filterImpl_nodesToPassThroughMap |= filterImpl_bitpos;
                     } else {
                        filterImpl_mapOfNewNodes |= filterImpl_bitpos;
                        if (filterImpl_newNodes == null) {
                           filterImpl_newNodes = scala.collection.mutable.Queue$.MODULE$.empty();
                        }

                        filterImpl_newNodes.$plus$eq(filterImpl_newSubNode);
                     }
                  } else if (filterImpl_newSubNode.size() == 1) {
                     filterImpl_newDataMap |= filterImpl_bitpos;
                     filterImpl_nodeMigrateToDataTargetMap |= filterImpl_bitpos;
                     if (filterImpl_nodesToMigrateToData == null) {
                        filterImpl_nodesToMigrateToData = (scala.collection.mutable.Queue)IterableFactory.apply$(scala.collection.mutable.Queue$.MODULE$, Nil$.MODULE$);
                     }

                     filterImpl_nodesToMigrateToData.$plus$eq(filterImpl_newSubNode);
                  }

                  ++filterImpl_nodeIndex;
               }
            }

            if (filterImpl_newSize == 0) {
               var10000 = MapNode$.MODULE$.empty();
            } else if (filterImpl_newSize == filterImpl_this.size()) {
               var10000 = filterImpl_this;
            } else {
               int filterImpl_newDataSize = Integer.bitCount(filterImpl_newDataMap);
               int filterImpl_newContentSize = 2 * filterImpl_newDataSize + Integer.bitCount(filterImpl_newNodeMap);
               Object[] filterImpl_newContent = new Object[filterImpl_newContentSize];
               int[] filterImpl_newOriginalHashes = new int[filterImpl_newDataSize];
               int filterImpl_newAllMap = filterImpl_newDataMap | filterImpl_newNodeMap;
               int filterImpl_maxIndex = 32 - Integer.numberOfLeadingZeros(filterImpl_newAllMap);
               int filterImpl_i = filterImpl_minimumIndex;
               int filterImpl_oldDataIndex = 0;
               int filterImpl_oldNodeIndex = 0;
               int filterImpl_newDataIndex = 0;

               for(int filterImpl_newNodeIndex = 0; filterImpl_i < filterImpl_maxIndex; ++filterImpl_i) {
                  Node$ var71 = Node$.MODULE$;
                  int filterImpl_bitpos = 1 << filterImpl_i;
                  if ((filterImpl_bitpos & filterImpl_oldDataPassThrough) != 0) {
                     filterImpl_newContent[filterImpl_newDataIndex * 2] = filterImpl_this.content()[2 * filterImpl_oldDataIndex];
                     filterImpl_newContent[filterImpl_newDataIndex * 2 + 1] = filterImpl_this.content()[2 * filterImpl_oldDataIndex + 1];
                     filterImpl_newOriginalHashes[filterImpl_newDataIndex] = filterImpl_this.originalHashes()[filterImpl_oldDataIndex];
                     ++filterImpl_newDataIndex;
                     ++filterImpl_oldDataIndex;
                  } else if ((filterImpl_bitpos & filterImpl_nodesToPassThroughMap) != 0) {
                     filterImpl_newContent[filterImpl_newContentSize - filterImpl_newNodeIndex - 1] = filterImpl_this.getNode(filterImpl_oldNodeIndex);
                     ++filterImpl_newNodeIndex;
                     ++filterImpl_oldNodeIndex;
                  } else if ((filterImpl_bitpos & filterImpl_nodeMigrateToDataTargetMap) != 0) {
                     MapNode filterImpl_node = (MapNode)filterImpl_nodesToMigrateToData.dequeue();
                     filterImpl_newContent[2 * filterImpl_newDataIndex] = filterImpl_node.getKey(0);
                     filterImpl_newContent[2 * filterImpl_newDataIndex + 1] = filterImpl_node.getValue(0);
                     filterImpl_newOriginalHashes[filterImpl_newDataIndex] = filterImpl_node.getHash(0);
                     ++filterImpl_newDataIndex;
                     ++filterImpl_oldNodeIndex;
                  } else if ((filterImpl_bitpos & filterImpl_mapOfNewNodes) != 0) {
                     filterImpl_newContent[filterImpl_newContentSize - filterImpl_newNodeIndex - 1] = filterImpl_newNodes.dequeue();
                     ++filterImpl_newNodeIndex;
                     ++filterImpl_oldNodeIndex;
                  } else if ((filterImpl_bitpos & filterImpl_this.dataMap()) != 0) {
                     ++filterImpl_oldDataIndex;
                  } else if ((filterImpl_bitpos & filterImpl_this.nodeMap()) != 0) {
                     ++filterImpl_oldNodeIndex;
                  }
               }

               var10000 = new BitmapIndexedMapNode(filterImpl_newDataMap, filterImpl_newNodeMap, filterImpl_newContent, filterImpl_newOriginalHashes, filterImpl_newSize, filterImpl_newCachedHashCode);
            }
         }

         Object var54 = null;
         Object var55 = null;
         Object var56 = null;
         Object var57 = null;
         Object var58 = null;
         Object var59 = null;
         Object var60 = null;
         Object var61 = null;
         Object var62 = null;
         Object var63 = null;
         Object var64 = null;
         Object var65 = null;
         BitmapIndexedMapNode newRootNode = var10000;
         if (newRootNode == this.rootNode()) {
            return this;
         } else {
            return newRootNode.size() == 0 ? HashMap$.MODULE$.empty() : new HashMap(newRootNode);
         }
      }
   }

   public HashMap removedAll(final IterableOnce keys) {
      if (this.isEmpty()) {
         return this;
      } else if (keys instanceof HashSet) {
         HashSet var2 = (HashSet)keys;
         if (var2.isEmpty()) {
            return this;
         } else {
            BitmapIndexedMapNode newRootNode = (new MapNodeRemoveAllSetNodeIterator(var2.rootNode())).removeAll(this.rootNode());
            if (newRootNode == this.rootNode()) {
               return this;
            } else {
               return newRootNode.size() <= 0 ? HashMap$.MODULE$.empty() : new HashMap(newRootNode);
            }
         }
      } else if (keys instanceof scala.collection.mutable.HashSet) {
         scala.collection.mutable.HashSet var4 = (scala.collection.mutable.HashSet)keys;
         if (var4.isEmpty()) {
            return this;
         } else {
            Iterator iter = var4.new HashSetIterator() {
               public scala.collection.mutable.HashSet.Node extract(final scala.collection.mutable.HashSet.Node nd) {
                  return nd;
               }
            };
            BitmapIndexedMapNode curr = this.rootNode();

            while(iter.hasNext()) {
               scala.collection.mutable.HashSet.Node next = (scala.collection.mutable.HashSet.Node)iter.next();
               int unimproveHash_improvedHash = next.hash();
               int originalHash = unimproveHash_improvedHash ^ unimproveHash_improvedHash >>> 16;
               int improved = Hashing$.MODULE$.improve(originalHash);
               curr = curr.removed(next.key(), originalHash, improved, 0);
               if (curr.size() == 0) {
                  return HashMap$.MODULE$.empty();
               }
            }

            if (curr == this.rootNode()) {
               return this;
            } else {
               return new HashMap(curr);
            }
         }
      } else if (keys instanceof LinkedHashSet) {
         LinkedHashSet var10 = (LinkedHashSet)keys;
         if (var10.isEmpty()) {
            return this;
         } else {
            Iterator iter = var10.entryIterator();
            BitmapIndexedMapNode curr = this.rootNode();

            while(iter.hasNext()) {
               LinkedHashSet.Entry next = (LinkedHashSet.Entry)iter.next();
               int originalHash = var10.unimproveHash(next.hash());
               int improved = Hashing$.MODULE$.improve(originalHash);
               curr = curr.removed(next.key(), originalHash, improved, 0);
               if (curr.size() == 0) {
                  return HashMap$.MODULE$.empty();
               }
            }

            if (curr == this.rootNode()) {
               return this;
            } else {
               return new HashMap(curr);
            }
         }
      } else {
         Iterator iter = keys.iterator();
         BitmapIndexedMapNode curr = this.rootNode();

         while(iter.hasNext()) {
            Object next = iter.next();
            int originalHash = Statics.anyHash(next);
            int improved = Hashing$.MODULE$.improve(originalHash);
            curr = curr.removed(next, originalHash, improved, 0);
            if (curr.size() == 0) {
               return HashMap$.MODULE$.empty();
            }
         }

         if (curr == this.rootNode()) {
            return this;
         } else {
            return new HashMap(curr);
         }
      }
   }

   public Tuple2 partition(final Function1 p) {
      return StrictOptimizedIterableOps.partition$(this, p);
   }

   public HashMap take(final int n) {
      return (HashMap)IterableOps.take$(this, n);
   }

   public HashMap takeRight(final int n) {
      return (HashMap)StrictOptimizedIterableOps.takeRight$(this, n);
   }

   public HashMap takeWhile(final Function1 p) {
      return (HashMap)IterableOps.takeWhile$(this, p);
   }

   public HashMap dropWhile(final Function1 p) {
      return (HashMap)IterableOps.dropWhile$(this, p);
   }

   public HashMap dropRight(final int n) {
      return (HashMap)StrictOptimizedIterableOps.dropRight$(this, n);
   }

   public HashMap drop(final int n) {
      return (HashMap)IterableOps.drop$(this, n);
   }

   public Tuple2 span(final Function1 p) {
      return StrictOptimizedIterableOps.span$(this, p);
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$stepper$1(final MapNode node, final int i) {
      return node.getPayload(i);
   }

   // $FF: synthetic method
   public static final int $anonfun$keyStepper$1(final MapNode node, final int i) {
      return BoxesRunTime.unboxToInt(node.getKey(i));
   }

   // $FF: synthetic method
   public static final long $anonfun$keyStepper$2(final MapNode node, final int i) {
      return BoxesRunTime.unboxToLong(node.getKey(i));
   }

   // $FF: synthetic method
   public static final double $anonfun$keyStepper$3(final MapNode node, final int i) {
      return BoxesRunTime.unboxToDouble(node.getKey(i));
   }

   // $FF: synthetic method
   public static final Object $anonfun$keyStepper$4(final MapNode node, final int i) {
      return node.getKey(i);
   }

   // $FF: synthetic method
   public static final int $anonfun$valueStepper$1(final MapNode node, final int i) {
      return BoxesRunTime.unboxToInt(node.getValue(i));
   }

   // $FF: synthetic method
   public static final long $anonfun$valueStepper$2(final MapNode node, final int i) {
      return BoxesRunTime.unboxToLong(node.getValue(i));
   }

   // $FF: synthetic method
   public static final double $anonfun$valueStepper$3(final MapNode node, final int i) {
      return BoxesRunTime.unboxToDouble(node.getValue(i));
   }

   // $FF: synthetic method
   public static final Object $anonfun$valueStepper$4(final MapNode node, final int i) {
      return node.getValue(i);
   }

   public HashMap(final BitmapIndexedMapNode rootNode) {
      this.rootNode = rootNode;
      Statics.releaseFence();
   }

   public HashMap() {
      this(MapNode$.MODULE$.empty());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public final class HashKeySet extends MapOps.ImmutableKeySet {
      private Set newKeySetOrThis(final HashMap newHashMap) {
         return (Set)(newHashMap == this.scala$collection$immutable$HashMap$HashKeySet$$$outer() ? this : newHashMap.keySet());
      }

      private Set newKeySetOrThis(final BitmapIndexedMapNode newRootNode) {
         return (Set)(newRootNode == this.scala$collection$immutable$HashMap$HashKeySet$$$outer().rootNode() ? this : (new HashMap(newRootNode)).keySet());
      }

      public Set incl(final Object elem) {
         int originalHash = Statics.anyHash(elem);
         int improvedHash = Hashing$.MODULE$.improve(originalHash);
         BitmapIndexedMapNode newNode = this.scala$collection$immutable$HashMap$HashKeySet$$$outer().rootNode().updated(elem, (Object)null, originalHash, improvedHash, 0, false);
         return this.newKeySetOrThis(newNode);
      }

      public Set excl(final Object elem) {
         HashMap var10001 = this.scala$collection$immutable$HashMap$HashKeySet$$$outer();
         if (var10001 == null) {
            throw null;
         } else {
            return this.newKeySetOrThis(var10001.removed(elem));
         }
      }

      public Set filter(final Function1 pred) {
         HashMap var10001 = this.scala$collection$immutable$HashMap$HashKeySet$$$outer();
         Function1 filter_pred = (kv) -> BoxesRunTime.boxToBoolean($anonfun$filter$1(pred, kv));
         if (var10001 == null) {
            throw null;
         } else {
            HashMap filter_this = var10001;
            boolean filterImpl_isFlipped = false;
            BitmapIndexedMapNode var70 = filter_this.rootNode();
            if (var70 == null) {
               throw null;
            } else {
               BitmapIndexedMapNode filterImpl_filterImpl_this = var70;
               if (filterImpl_filterImpl_this.size() == 0) {
                  var70 = filterImpl_filterImpl_this;
               } else if (filterImpl_filterImpl_this.size() == 1) {
                  Tuple2 var54 = filterImpl_filterImpl_this.getPayload(0);
                  var70 = BoxesRunTime.unboxToBoolean(pred.apply(var54._1())) != filterImpl_isFlipped ? filterImpl_filterImpl_this : MapNode$.MODULE$.empty();
               } else if (filterImpl_filterImpl_this.nodeMap() == 0) {
                  int filterImpl_filterImpl_minimumIndex = Integer.numberOfTrailingZeros(filterImpl_filterImpl_this.dataMap());
                  int filterImpl_filterImpl_maximumIndex = 32 - Integer.numberOfLeadingZeros(filterImpl_filterImpl_this.dataMap());
                  int filterImpl_filterImpl_newDataMap = 0;
                  int filterImpl_filterImpl_newCachedHashCode = 0;
                  int filterImpl_filterImpl_dataIndex = 0;

                  for(int filterImpl_filterImpl_i = filterImpl_filterImpl_minimumIndex; filterImpl_filterImpl_i < filterImpl_filterImpl_maximumIndex; ++filterImpl_filterImpl_i) {
                     Node$ var72 = Node$.MODULE$;
                     int filterImpl_filterImpl_bitpos = 1 << filterImpl_filterImpl_i;
                     if ((filterImpl_filterImpl_bitpos & filterImpl_filterImpl_this.dataMap()) != 0) {
                        Tuple2 var68 = filterImpl_filterImpl_this.getPayload(filterImpl_filterImpl_dataIndex);
                        if (BoxesRunTime.unboxToBoolean(pred.apply(var68._1())) != filterImpl_isFlipped) {
                           filterImpl_filterImpl_newDataMap |= filterImpl_filterImpl_bitpos;
                           filterImpl_filterImpl_newCachedHashCode += Hashing$.MODULE$.improve(filterImpl_filterImpl_this.originalHashes()[filterImpl_filterImpl_dataIndex]);
                        }

                        ++filterImpl_filterImpl_dataIndex;
                     }
                  }

                  if (filterImpl_filterImpl_newDataMap == 0) {
                     var70 = MapNode$.MODULE$.empty();
                  } else if (filterImpl_filterImpl_newDataMap == filterImpl_filterImpl_this.dataMap()) {
                     var70 = filterImpl_filterImpl_this;
                  } else {
                     int filterImpl_filterImpl_newSize = Integer.bitCount(filterImpl_filterImpl_newDataMap);
                     Object[] filterImpl_filterImpl_newContent = new Object[filterImpl_filterImpl_newSize * 2];
                     int[] filterImpl_filterImpl_newOriginalHashCodes = new int[filterImpl_filterImpl_newSize];
                     int filterImpl_filterImpl_newMaximumIndex = 32 - Integer.numberOfLeadingZeros(filterImpl_filterImpl_newDataMap);
                     int filterImpl_filterImpl_j = Integer.numberOfTrailingZeros(filterImpl_filterImpl_newDataMap);

                     for(int filterImpl_filterImpl_newDataIndex = 0; filterImpl_filterImpl_j < filterImpl_filterImpl_newMaximumIndex; ++filterImpl_filterImpl_j) {
                        Node$ var73 = Node$.MODULE$;
                        int filterImpl_filterImpl_bitpos = 1 << filterImpl_filterImpl_j;
                        if ((filterImpl_filterImpl_bitpos & filterImpl_filterImpl_newDataMap) != 0) {
                           var73 = Node$.MODULE$;
                           int filterImpl_filterImpl_oldIndex = Integer.bitCount(filterImpl_filterImpl_this.dataMap() & filterImpl_filterImpl_bitpos - 1);
                           filterImpl_filterImpl_newContent[filterImpl_filterImpl_newDataIndex * 2] = filterImpl_filterImpl_this.content()[filterImpl_filterImpl_oldIndex * 2];
                           filterImpl_filterImpl_newContent[filterImpl_filterImpl_newDataIndex * 2 + 1] = filterImpl_filterImpl_this.content()[filterImpl_filterImpl_oldIndex * 2 + 1];
                           filterImpl_filterImpl_newOriginalHashCodes[filterImpl_filterImpl_newDataIndex] = filterImpl_filterImpl_this.originalHashes()[filterImpl_filterImpl_oldIndex];
                           ++filterImpl_filterImpl_newDataIndex;
                        }
                     }

                     var70 = new BitmapIndexedMapNode(filterImpl_filterImpl_newDataMap, 0, filterImpl_filterImpl_newContent, filterImpl_filterImpl_newOriginalHashCodes, filterImpl_filterImpl_newSize, filterImpl_filterImpl_newCachedHashCode);
                  }
               } else {
                  int filterImpl_filterImpl_allMap = filterImpl_filterImpl_this.dataMap() | filterImpl_filterImpl_this.nodeMap();
                  int filterImpl_filterImpl_minimumIndex = Integer.numberOfTrailingZeros(filterImpl_filterImpl_allMap);
                  int filterImpl_filterImpl_maximumIndex = 32 - Integer.numberOfLeadingZeros(filterImpl_filterImpl_allMap);
                  int filterImpl_filterImpl_oldDataPassThrough = 0;
                  int filterImpl_filterImpl_nodeMigrateToDataTargetMap = 0;
                  scala.collection.mutable.Queue filterImpl_filterImpl_nodesToMigrateToData = null;
                  int filterImpl_filterImpl_nodesToPassThroughMap = 0;
                  int filterImpl_filterImpl_mapOfNewNodes = 0;
                  scala.collection.mutable.Queue filterImpl_filterImpl_newNodes = null;
                  int filterImpl_filterImpl_newDataMap = 0;
                  int filterImpl_filterImpl_newNodeMap = 0;
                  int filterImpl_filterImpl_newSize = 0;
                  int filterImpl_filterImpl_newCachedHashCode = 0;
                  int filterImpl_filterImpl_dataIndex = 0;
                  int filterImpl_filterImpl_nodeIndex = 0;

                  for(int filterImpl_filterImpl_i = filterImpl_filterImpl_minimumIndex; filterImpl_filterImpl_i < filterImpl_filterImpl_maximumIndex; ++filterImpl_filterImpl_i) {
                     Node$ var75 = Node$.MODULE$;
                     int filterImpl_filterImpl_bitpos = 1 << filterImpl_filterImpl_i;
                     if ((filterImpl_filterImpl_bitpos & filterImpl_filterImpl_this.dataMap()) != 0) {
                        Tuple2 var69 = filterImpl_filterImpl_this.getPayload(filterImpl_filterImpl_dataIndex);
                        if (BoxesRunTime.unboxToBoolean(pred.apply(var69._1())) != filterImpl_isFlipped) {
                           filterImpl_filterImpl_newDataMap |= filterImpl_filterImpl_bitpos;
                           filterImpl_filterImpl_oldDataPassThrough |= filterImpl_filterImpl_bitpos;
                           ++filterImpl_filterImpl_newSize;
                           filterImpl_filterImpl_newCachedHashCode += Hashing$.MODULE$.improve(filterImpl_filterImpl_this.originalHashes()[filterImpl_filterImpl_dataIndex]);
                        }

                        ++filterImpl_filterImpl_dataIndex;
                     } else if ((filterImpl_filterImpl_bitpos & filterImpl_filterImpl_this.nodeMap()) != 0) {
                        MapNode filterImpl_filterImpl_oldSubNode = filterImpl_filterImpl_this.getNode(filterImpl_filterImpl_nodeIndex);
                        MapNode filterImpl_filterImpl_newSubNode = filterImpl_filterImpl_oldSubNode.filterImpl(filter_pred, filterImpl_isFlipped);
                        filterImpl_filterImpl_newSize += filterImpl_filterImpl_newSubNode.size();
                        filterImpl_filterImpl_newCachedHashCode += filterImpl_filterImpl_newSubNode.cachedJavaKeySetHashCode();
                        if (filterImpl_filterImpl_newSubNode.size() > 1) {
                           filterImpl_filterImpl_newNodeMap |= filterImpl_filterImpl_bitpos;
                           if (filterImpl_filterImpl_oldSubNode == filterImpl_filterImpl_newSubNode) {
                              filterImpl_filterImpl_nodesToPassThroughMap |= filterImpl_filterImpl_bitpos;
                           } else {
                              filterImpl_filterImpl_mapOfNewNodes |= filterImpl_filterImpl_bitpos;
                              if (filterImpl_filterImpl_newNodes == null) {
                                 filterImpl_filterImpl_newNodes = scala.collection.mutable.Queue$.MODULE$.empty();
                              }

                              filterImpl_filterImpl_newNodes.$plus$eq(filterImpl_filterImpl_newSubNode);
                           }
                        } else if (filterImpl_filterImpl_newSubNode.size() == 1) {
                           filterImpl_filterImpl_newDataMap |= filterImpl_filterImpl_bitpos;
                           filterImpl_filterImpl_nodeMigrateToDataTargetMap |= filterImpl_filterImpl_bitpos;
                           if (filterImpl_filterImpl_nodesToMigrateToData == null) {
                              filterImpl_filterImpl_nodesToMigrateToData = (scala.collection.mutable.Queue)IterableFactory.apply$(scala.collection.mutable.Queue$.MODULE$, Nil$.MODULE$);
                           }

                           filterImpl_filterImpl_nodesToMigrateToData.$plus$eq(filterImpl_filterImpl_newSubNode);
                        }

                        ++filterImpl_filterImpl_nodeIndex;
                     }
                  }

                  if (filterImpl_filterImpl_newSize == 0) {
                     var70 = MapNode$.MODULE$.empty();
                  } else if (filterImpl_filterImpl_newSize == filterImpl_filterImpl_this.size()) {
                     var70 = filterImpl_filterImpl_this;
                  } else {
                     int filterImpl_filterImpl_newDataSize = Integer.bitCount(filterImpl_filterImpl_newDataMap);
                     int filterImpl_filterImpl_newContentSize = 2 * filterImpl_filterImpl_newDataSize + Integer.bitCount(filterImpl_filterImpl_newNodeMap);
                     Object[] filterImpl_filterImpl_newContent = new Object[filterImpl_filterImpl_newContentSize];
                     int[] filterImpl_filterImpl_newOriginalHashes = new int[filterImpl_filterImpl_newDataSize];
                     int filterImpl_filterImpl_newAllMap = filterImpl_filterImpl_newDataMap | filterImpl_filterImpl_newNodeMap;
                     int filterImpl_filterImpl_maxIndex = 32 - Integer.numberOfLeadingZeros(filterImpl_filterImpl_newAllMap);
                     int filterImpl_filterImpl_i = filterImpl_filterImpl_minimumIndex;
                     int filterImpl_filterImpl_oldDataIndex = 0;
                     int filterImpl_filterImpl_oldNodeIndex = 0;
                     int filterImpl_filterImpl_newDataIndex = 0;

                     for(int filterImpl_filterImpl_newNodeIndex = 0; filterImpl_filterImpl_i < filterImpl_filterImpl_maxIndex; ++filterImpl_filterImpl_i) {
                        Node$ var76 = Node$.MODULE$;
                        int filterImpl_filterImpl_bitpos = 1 << filterImpl_filterImpl_i;
                        if ((filterImpl_filterImpl_bitpos & filterImpl_filterImpl_oldDataPassThrough) != 0) {
                           filterImpl_filterImpl_newContent[filterImpl_filterImpl_newDataIndex * 2] = filterImpl_filterImpl_this.content()[2 * filterImpl_filterImpl_oldDataIndex];
                           filterImpl_filterImpl_newContent[filterImpl_filterImpl_newDataIndex * 2 + 1] = filterImpl_filterImpl_this.content()[2 * filterImpl_filterImpl_oldDataIndex + 1];
                           filterImpl_filterImpl_newOriginalHashes[filterImpl_filterImpl_newDataIndex] = filterImpl_filterImpl_this.originalHashes()[filterImpl_filterImpl_oldDataIndex];
                           ++filterImpl_filterImpl_newDataIndex;
                           ++filterImpl_filterImpl_oldDataIndex;
                        } else if ((filterImpl_filterImpl_bitpos & filterImpl_filterImpl_nodesToPassThroughMap) != 0) {
                           filterImpl_filterImpl_newContent[filterImpl_filterImpl_newContentSize - filterImpl_filterImpl_newNodeIndex - 1] = filterImpl_filterImpl_this.getNode(filterImpl_filterImpl_oldNodeIndex);
                           ++filterImpl_filterImpl_newNodeIndex;
                           ++filterImpl_filterImpl_oldNodeIndex;
                        } else if ((filterImpl_filterImpl_bitpos & filterImpl_filterImpl_nodeMigrateToDataTargetMap) != 0) {
                           MapNode filterImpl_filterImpl_node = (MapNode)filterImpl_filterImpl_nodesToMigrateToData.dequeue();
                           filterImpl_filterImpl_newContent[2 * filterImpl_filterImpl_newDataIndex] = filterImpl_filterImpl_node.getKey(0);
                           filterImpl_filterImpl_newContent[2 * filterImpl_filterImpl_newDataIndex + 1] = filterImpl_filterImpl_node.getValue(0);
                           filterImpl_filterImpl_newOriginalHashes[filterImpl_filterImpl_newDataIndex] = filterImpl_filterImpl_node.getHash(0);
                           ++filterImpl_filterImpl_newDataIndex;
                           ++filterImpl_filterImpl_oldNodeIndex;
                        } else if ((filterImpl_filterImpl_bitpos & filterImpl_filterImpl_mapOfNewNodes) != 0) {
                           filterImpl_filterImpl_newContent[filterImpl_filterImpl_newContentSize - filterImpl_filterImpl_newNodeIndex - 1] = filterImpl_filterImpl_newNodes.dequeue();
                           ++filterImpl_filterImpl_newNodeIndex;
                           ++filterImpl_filterImpl_oldNodeIndex;
                        } else if ((filterImpl_filterImpl_bitpos & filterImpl_filterImpl_this.dataMap()) != 0) {
                           ++filterImpl_filterImpl_oldDataIndex;
                        } else if ((filterImpl_filterImpl_bitpos & filterImpl_filterImpl_this.nodeMap()) != 0) {
                           ++filterImpl_filterImpl_oldNodeIndex;
                        }
                     }

                     var70 = new BitmapIndexedMapNode(filterImpl_filterImpl_newDataMap, filterImpl_filterImpl_newNodeMap, filterImpl_filterImpl_newContent, filterImpl_filterImpl_newOriginalHashes, filterImpl_filterImpl_newSize, filterImpl_filterImpl_newCachedHashCode);
                  }
               }

               Object var58 = null;
               Object var59 = null;
               Object var60 = null;
               Object var61 = null;
               Object var62 = null;
               Object var63 = null;
               Object var64 = null;
               Object var65 = null;
               Object var66 = null;
               Object var67 = null;
               BitmapIndexedMapNode filterImpl_newRootNode = var70;
               HashMap var77 = filterImpl_newRootNode == filter_this.rootNode() ? filter_this : (filterImpl_newRootNode.size() == 0 ? HashMap$.MODULE$.empty() : new HashMap(filterImpl_newRootNode));
               Object var57 = null;
               Object var55 = null;
               filter_pred = null;
               return this.newKeySetOrThis(var77);
            }
         }
      }

      public Set filterNot(final Function1 pred) {
         HashMap var10001 = this.scala$collection$immutable$HashMap$HashKeySet$$$outer();
         Function1 filterNot_pred = (kv) -> BoxesRunTime.boxToBoolean($anonfun$filterNot$1(pred, kv));
         if (var10001 == null) {
            throw null;
         } else {
            HashMap filterNot_this = var10001;
            boolean filterImpl_isFlipped = true;
            BitmapIndexedMapNode var70 = filterNot_this.rootNode();
            if (var70 == null) {
               throw null;
            } else {
               BitmapIndexedMapNode filterImpl_filterImpl_this = var70;
               if (filterImpl_filterImpl_this.size() == 0) {
                  var70 = filterImpl_filterImpl_this;
               } else if (filterImpl_filterImpl_this.size() == 1) {
                  Tuple2 var54 = filterImpl_filterImpl_this.getPayload(0);
                  var70 = BoxesRunTime.unboxToBoolean(pred.apply(var54._1())) != filterImpl_isFlipped ? filterImpl_filterImpl_this : MapNode$.MODULE$.empty();
               } else if (filterImpl_filterImpl_this.nodeMap() == 0) {
                  int filterImpl_filterImpl_minimumIndex = Integer.numberOfTrailingZeros(filterImpl_filterImpl_this.dataMap());
                  int filterImpl_filterImpl_maximumIndex = 32 - Integer.numberOfLeadingZeros(filterImpl_filterImpl_this.dataMap());
                  int filterImpl_filterImpl_newDataMap = 0;
                  int filterImpl_filterImpl_newCachedHashCode = 0;
                  int filterImpl_filterImpl_dataIndex = 0;

                  for(int filterImpl_filterImpl_i = filterImpl_filterImpl_minimumIndex; filterImpl_filterImpl_i < filterImpl_filterImpl_maximumIndex; ++filterImpl_filterImpl_i) {
                     Node$ var72 = Node$.MODULE$;
                     int filterImpl_filterImpl_bitpos = 1 << filterImpl_filterImpl_i;
                     if ((filterImpl_filterImpl_bitpos & filterImpl_filterImpl_this.dataMap()) != 0) {
                        Tuple2 var68 = filterImpl_filterImpl_this.getPayload(filterImpl_filterImpl_dataIndex);
                        if (BoxesRunTime.unboxToBoolean(pred.apply(var68._1())) != filterImpl_isFlipped) {
                           filterImpl_filterImpl_newDataMap |= filterImpl_filterImpl_bitpos;
                           filterImpl_filterImpl_newCachedHashCode += Hashing$.MODULE$.improve(filterImpl_filterImpl_this.originalHashes()[filterImpl_filterImpl_dataIndex]);
                        }

                        ++filterImpl_filterImpl_dataIndex;
                     }
                  }

                  if (filterImpl_filterImpl_newDataMap == 0) {
                     var70 = MapNode$.MODULE$.empty();
                  } else if (filterImpl_filterImpl_newDataMap == filterImpl_filterImpl_this.dataMap()) {
                     var70 = filterImpl_filterImpl_this;
                  } else {
                     int filterImpl_filterImpl_newSize = Integer.bitCount(filterImpl_filterImpl_newDataMap);
                     Object[] filterImpl_filterImpl_newContent = new Object[filterImpl_filterImpl_newSize * 2];
                     int[] filterImpl_filterImpl_newOriginalHashCodes = new int[filterImpl_filterImpl_newSize];
                     int filterImpl_filterImpl_newMaximumIndex = 32 - Integer.numberOfLeadingZeros(filterImpl_filterImpl_newDataMap);
                     int filterImpl_filterImpl_j = Integer.numberOfTrailingZeros(filterImpl_filterImpl_newDataMap);

                     for(int filterImpl_filterImpl_newDataIndex = 0; filterImpl_filterImpl_j < filterImpl_filterImpl_newMaximumIndex; ++filterImpl_filterImpl_j) {
                        Node$ var73 = Node$.MODULE$;
                        int filterImpl_filterImpl_bitpos = 1 << filterImpl_filterImpl_j;
                        if ((filterImpl_filterImpl_bitpos & filterImpl_filterImpl_newDataMap) != 0) {
                           var73 = Node$.MODULE$;
                           int filterImpl_filterImpl_oldIndex = Integer.bitCount(filterImpl_filterImpl_this.dataMap() & filterImpl_filterImpl_bitpos - 1);
                           filterImpl_filterImpl_newContent[filterImpl_filterImpl_newDataIndex * 2] = filterImpl_filterImpl_this.content()[filterImpl_filterImpl_oldIndex * 2];
                           filterImpl_filterImpl_newContent[filterImpl_filterImpl_newDataIndex * 2 + 1] = filterImpl_filterImpl_this.content()[filterImpl_filterImpl_oldIndex * 2 + 1];
                           filterImpl_filterImpl_newOriginalHashCodes[filterImpl_filterImpl_newDataIndex] = filterImpl_filterImpl_this.originalHashes()[filterImpl_filterImpl_oldIndex];
                           ++filterImpl_filterImpl_newDataIndex;
                        }
                     }

                     var70 = new BitmapIndexedMapNode(filterImpl_filterImpl_newDataMap, 0, filterImpl_filterImpl_newContent, filterImpl_filterImpl_newOriginalHashCodes, filterImpl_filterImpl_newSize, filterImpl_filterImpl_newCachedHashCode);
                  }
               } else {
                  int filterImpl_filterImpl_allMap = filterImpl_filterImpl_this.dataMap() | filterImpl_filterImpl_this.nodeMap();
                  int filterImpl_filterImpl_minimumIndex = Integer.numberOfTrailingZeros(filterImpl_filterImpl_allMap);
                  int filterImpl_filterImpl_maximumIndex = 32 - Integer.numberOfLeadingZeros(filterImpl_filterImpl_allMap);
                  int filterImpl_filterImpl_oldDataPassThrough = 0;
                  int filterImpl_filterImpl_nodeMigrateToDataTargetMap = 0;
                  scala.collection.mutable.Queue filterImpl_filterImpl_nodesToMigrateToData = null;
                  int filterImpl_filterImpl_nodesToPassThroughMap = 0;
                  int filterImpl_filterImpl_mapOfNewNodes = 0;
                  scala.collection.mutable.Queue filterImpl_filterImpl_newNodes = null;
                  int filterImpl_filterImpl_newDataMap = 0;
                  int filterImpl_filterImpl_newNodeMap = 0;
                  int filterImpl_filterImpl_newSize = 0;
                  int filterImpl_filterImpl_newCachedHashCode = 0;
                  int filterImpl_filterImpl_dataIndex = 0;
                  int filterImpl_filterImpl_nodeIndex = 0;

                  for(int filterImpl_filterImpl_i = filterImpl_filterImpl_minimumIndex; filterImpl_filterImpl_i < filterImpl_filterImpl_maximumIndex; ++filterImpl_filterImpl_i) {
                     Node$ var75 = Node$.MODULE$;
                     int filterImpl_filterImpl_bitpos = 1 << filterImpl_filterImpl_i;
                     if ((filterImpl_filterImpl_bitpos & filterImpl_filterImpl_this.dataMap()) != 0) {
                        Tuple2 var69 = filterImpl_filterImpl_this.getPayload(filterImpl_filterImpl_dataIndex);
                        if (BoxesRunTime.unboxToBoolean(pred.apply(var69._1())) != filterImpl_isFlipped) {
                           filterImpl_filterImpl_newDataMap |= filterImpl_filterImpl_bitpos;
                           filterImpl_filterImpl_oldDataPassThrough |= filterImpl_filterImpl_bitpos;
                           ++filterImpl_filterImpl_newSize;
                           filterImpl_filterImpl_newCachedHashCode += Hashing$.MODULE$.improve(filterImpl_filterImpl_this.originalHashes()[filterImpl_filterImpl_dataIndex]);
                        }

                        ++filterImpl_filterImpl_dataIndex;
                     } else if ((filterImpl_filterImpl_bitpos & filterImpl_filterImpl_this.nodeMap()) != 0) {
                        MapNode filterImpl_filterImpl_oldSubNode = filterImpl_filterImpl_this.getNode(filterImpl_filterImpl_nodeIndex);
                        MapNode filterImpl_filterImpl_newSubNode = filterImpl_filterImpl_oldSubNode.filterImpl(filterNot_pred, filterImpl_isFlipped);
                        filterImpl_filterImpl_newSize += filterImpl_filterImpl_newSubNode.size();
                        filterImpl_filterImpl_newCachedHashCode += filterImpl_filterImpl_newSubNode.cachedJavaKeySetHashCode();
                        if (filterImpl_filterImpl_newSubNode.size() > 1) {
                           filterImpl_filterImpl_newNodeMap |= filterImpl_filterImpl_bitpos;
                           if (filterImpl_filterImpl_oldSubNode == filterImpl_filterImpl_newSubNode) {
                              filterImpl_filterImpl_nodesToPassThroughMap |= filterImpl_filterImpl_bitpos;
                           } else {
                              filterImpl_filterImpl_mapOfNewNodes |= filterImpl_filterImpl_bitpos;
                              if (filterImpl_filterImpl_newNodes == null) {
                                 filterImpl_filterImpl_newNodes = scala.collection.mutable.Queue$.MODULE$.empty();
                              }

                              filterImpl_filterImpl_newNodes.$plus$eq(filterImpl_filterImpl_newSubNode);
                           }
                        } else if (filterImpl_filterImpl_newSubNode.size() == 1) {
                           filterImpl_filterImpl_newDataMap |= filterImpl_filterImpl_bitpos;
                           filterImpl_filterImpl_nodeMigrateToDataTargetMap |= filterImpl_filterImpl_bitpos;
                           if (filterImpl_filterImpl_nodesToMigrateToData == null) {
                              filterImpl_filterImpl_nodesToMigrateToData = (scala.collection.mutable.Queue)IterableFactory.apply$(scala.collection.mutable.Queue$.MODULE$, Nil$.MODULE$);
                           }

                           filterImpl_filterImpl_nodesToMigrateToData.$plus$eq(filterImpl_filterImpl_newSubNode);
                        }

                        ++filterImpl_filterImpl_nodeIndex;
                     }
                  }

                  if (filterImpl_filterImpl_newSize == 0) {
                     var70 = MapNode$.MODULE$.empty();
                  } else if (filterImpl_filterImpl_newSize == filterImpl_filterImpl_this.size()) {
                     var70 = filterImpl_filterImpl_this;
                  } else {
                     int filterImpl_filterImpl_newDataSize = Integer.bitCount(filterImpl_filterImpl_newDataMap);
                     int filterImpl_filterImpl_newContentSize = 2 * filterImpl_filterImpl_newDataSize + Integer.bitCount(filterImpl_filterImpl_newNodeMap);
                     Object[] filterImpl_filterImpl_newContent = new Object[filterImpl_filterImpl_newContentSize];
                     int[] filterImpl_filterImpl_newOriginalHashes = new int[filterImpl_filterImpl_newDataSize];
                     int filterImpl_filterImpl_newAllMap = filterImpl_filterImpl_newDataMap | filterImpl_filterImpl_newNodeMap;
                     int filterImpl_filterImpl_maxIndex = 32 - Integer.numberOfLeadingZeros(filterImpl_filterImpl_newAllMap);
                     int filterImpl_filterImpl_i = filterImpl_filterImpl_minimumIndex;
                     int filterImpl_filterImpl_oldDataIndex = 0;
                     int filterImpl_filterImpl_oldNodeIndex = 0;
                     int filterImpl_filterImpl_newDataIndex = 0;

                     for(int filterImpl_filterImpl_newNodeIndex = 0; filterImpl_filterImpl_i < filterImpl_filterImpl_maxIndex; ++filterImpl_filterImpl_i) {
                        Node$ var76 = Node$.MODULE$;
                        int filterImpl_filterImpl_bitpos = 1 << filterImpl_filterImpl_i;
                        if ((filterImpl_filterImpl_bitpos & filterImpl_filterImpl_oldDataPassThrough) != 0) {
                           filterImpl_filterImpl_newContent[filterImpl_filterImpl_newDataIndex * 2] = filterImpl_filterImpl_this.content()[2 * filterImpl_filterImpl_oldDataIndex];
                           filterImpl_filterImpl_newContent[filterImpl_filterImpl_newDataIndex * 2 + 1] = filterImpl_filterImpl_this.content()[2 * filterImpl_filterImpl_oldDataIndex + 1];
                           filterImpl_filterImpl_newOriginalHashes[filterImpl_filterImpl_newDataIndex] = filterImpl_filterImpl_this.originalHashes()[filterImpl_filterImpl_oldDataIndex];
                           ++filterImpl_filterImpl_newDataIndex;
                           ++filterImpl_filterImpl_oldDataIndex;
                        } else if ((filterImpl_filterImpl_bitpos & filterImpl_filterImpl_nodesToPassThroughMap) != 0) {
                           filterImpl_filterImpl_newContent[filterImpl_filterImpl_newContentSize - filterImpl_filterImpl_newNodeIndex - 1] = filterImpl_filterImpl_this.getNode(filterImpl_filterImpl_oldNodeIndex);
                           ++filterImpl_filterImpl_newNodeIndex;
                           ++filterImpl_filterImpl_oldNodeIndex;
                        } else if ((filterImpl_filterImpl_bitpos & filterImpl_filterImpl_nodeMigrateToDataTargetMap) != 0) {
                           MapNode filterImpl_filterImpl_node = (MapNode)filterImpl_filterImpl_nodesToMigrateToData.dequeue();
                           filterImpl_filterImpl_newContent[2 * filterImpl_filterImpl_newDataIndex] = filterImpl_filterImpl_node.getKey(0);
                           filterImpl_filterImpl_newContent[2 * filterImpl_filterImpl_newDataIndex + 1] = filterImpl_filterImpl_node.getValue(0);
                           filterImpl_filterImpl_newOriginalHashes[filterImpl_filterImpl_newDataIndex] = filterImpl_filterImpl_node.getHash(0);
                           ++filterImpl_filterImpl_newDataIndex;
                           ++filterImpl_filterImpl_oldNodeIndex;
                        } else if ((filterImpl_filterImpl_bitpos & filterImpl_filterImpl_mapOfNewNodes) != 0) {
                           filterImpl_filterImpl_newContent[filterImpl_filterImpl_newContentSize - filterImpl_filterImpl_newNodeIndex - 1] = filterImpl_filterImpl_newNodes.dequeue();
                           ++filterImpl_filterImpl_newNodeIndex;
                           ++filterImpl_filterImpl_oldNodeIndex;
                        } else if ((filterImpl_filterImpl_bitpos & filterImpl_filterImpl_this.dataMap()) != 0) {
                           ++filterImpl_filterImpl_oldDataIndex;
                        } else if ((filterImpl_filterImpl_bitpos & filterImpl_filterImpl_this.nodeMap()) != 0) {
                           ++filterImpl_filterImpl_oldNodeIndex;
                        }
                     }

                     var70 = new BitmapIndexedMapNode(filterImpl_filterImpl_newDataMap, filterImpl_filterImpl_newNodeMap, filterImpl_filterImpl_newContent, filterImpl_filterImpl_newOriginalHashes, filterImpl_filterImpl_newSize, filterImpl_filterImpl_newCachedHashCode);
                  }
               }

               Object var58 = null;
               Object var59 = null;
               Object var60 = null;
               Object var61 = null;
               Object var62 = null;
               Object var63 = null;
               Object var64 = null;
               Object var65 = null;
               Object var66 = null;
               Object var67 = null;
               BitmapIndexedMapNode filterImpl_newRootNode = var70;
               HashMap var77 = filterImpl_newRootNode == filterNot_this.rootNode() ? filterNot_this : (filterImpl_newRootNode.size() == 0 ? HashMap$.MODULE$.empty() : new HashMap(filterImpl_newRootNode));
               Object var57 = null;
               Object var55 = null;
               filterNot_pred = null;
               return this.newKeySetOrThis(var77);
            }
         }
      }

      // $FF: synthetic method
      public HashMap scala$collection$immutable$HashMap$HashKeySet$$$outer() {
         return (HashMap)this.$outer;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$filter$1(final Function1 pred$1, final Tuple2 kv) {
         return BoxesRunTime.unboxToBoolean(pred$1.apply(kv._1()));
      }

      // $FF: synthetic method
      public static final boolean $anonfun$filterNot$1(final Function1 pred$2, final Tuple2 kv) {
         return BoxesRunTime.unboxToBoolean(pred$2.apply(kv._1()));
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
