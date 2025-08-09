package breeze.util;

import java.io.File;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.StringContext;
import scala.Tuple2;
import scala.StringContext.;
import scala.collection.BitSet;
import scala.collection.BufferedIterator;
import scala.collection.BuildFrom;
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
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011\u001dv!\u00024h\u0011\u0003ag!\u00028h\u0011\u0003y\u0007\"\u0002<\u0002\t\u00039\b\"\u0002=\u0002\t\u0003I\bB\u0002=\u0002\t\u0003\t\t\u0003C\u0004\u00026\u0005!\t!a\u000e\t\u000f\u0005=\u0013\u0001\"\u0001\u0002R!9\u0011QL\u0001\u0005\u0002\u0005}\u0003\"CA:\u0003E\u0005I\u0011AA;\r\u0019\tY)A\u0002\u0002\u000e\"Q\u0011QS\u0005\u0003\u0006\u0004%\t!a&\t\u0015\u0005}\u0015B!A!\u0002\u0013\tI\n\u0003\u0004w\u0013\u0011\u0005\u0011\u0011\u0015\u0005\b\u0003SKA\u0011AAV\u0011%\t9,CA\u0001\n\u0003\nI\fC\u0005\u0002B&\t\t\u0011\"\u0011\u0002D\u001eI\u0011\u0011Z\u0001\u0002\u0002#\u0005\u00111\u001a\u0004\n\u0003\u0017\u000b\u0011\u0011!E\u0001\u0003\u001bDaA^\t\u0005\u0002\u0005=\u0007bBAi#\u0011\u0015\u00111\u001b\u0005\n\u0003;\f\u0012\u0011!C\u0003\u0003?D\u0011\"a9\u0012\u0003\u0003%)!!:\t\u0013\u0005%\u0017!!A\u0005\u0004\u00055\bbBAy\u0003\u0011\u0005\u00111_\u0003\u0007\u0005\u0013\t\u0001!!\u0001\t\u000f\t-\u0011\u0001\"\u0001\u0003\u000e!9!1E\u0001\u0005\u0002\t\u0015\u0002b\u0002B\u0017\u0003\u0011\u0005!q\u0006\u0005\b\u0005\u000b\nA\u0011\u0001B$\r\u0019\u0011\u0019&A\u0001\u0003V!Q!\u0011L\u000f\u0003\u0002\u0003\u0006IAa\u0017\t\rYlB\u0011\u0001B8\u0011\u001d\u0011)(\bC\u0001\u0005oBqAa!\u001e\t\u0003\u0011)\tC\u0004\u0003\nv!\tAa#\t\u0013\t5\u0017!!A\u0005\u0004\t=\u0007b\u0002Bo\u0003\u0011\r!q\u001c\u0004\u0007\u0005[\f1Aa<\t\u0015\tEXE!b\u0001\n\u0003\u0011\u0019\u0010\u0003\u0006\u0003\u0000\u0016\u0012\t\u0011)A\u0005\u0005kDaA^\u0013\u0005\u0002\r\u0005\u0001bBB\u0004K\u0011\u00051\u0011\u0002\u0005\b\u0007\u001f)C\u0011AB\t\u0011\u001d\u0019I\"\nC\u0001\u00077Aqaa\u000e&\t\u0003\u0019I\u0004C\u0004\u0004F\u0015\"\taa\u0012\t\u000f\r5S\u0005\"\u0001\u0004P!911K\u0013\u0005\u0002\rU\u0003bBB-K\u0011\u000511\f\u0005\b\u0007?*C\u0011AB1\u0011\u001d\u0019)'\nC\u0001\u0007OBqaa\u001b&\t\u0003\u0019i\u0007C\u0004\u0004r\u0015\"\taa\u001d\t\u000f\r]T\u0005\"\u0001\u0003t\"91\u0011P\u0013\u0005\u0002\rm\u0004bBB?K\u0011\u00051q\u0010\u0005\n\u0003o+\u0013\u0011!C!\u0003sC\u0011\"!1&\u0003\u0003%\te!\"\b\u0013\r%\u0015!!A\t\u0002\r-e!\u0003Bw\u0003\u0005\u0005\t\u0012ABG\u0011\u001918\b\"\u0001\u0004\u0010\"91\u0011S\u001e\u0005\u0006\rM\u0005bBBNw\u0011\u00151Q\u0014\u0005\b\u0007C[DQABR\u0011\u001d\u0019il\u000fC\u0003\u0007\u007fCqaa4<\t\u000b\u0019\t\u000eC\u0004\u0004Zn\")aa7\t\u000f\r\r8\b\"\u0002\u0004f\"91Q^\u001e\u0005\u0006\r=\bbBB|w\u0011\u00151\u0011 \u0005\b\t\u0003YDQ\u0001C\u0002\u0011\u001d!Ya\u000fC\u0003\t\u001bAq\u0001\"\u0006<\t\u000b!9\u0002C\u0004\u0005 m\")\u0001\"\t\t\u000f\u0011\u00152\b\"\u0002\u0005(!9A1F\u001e\u0005\u0006\u00115\u0002\"CAow\u0005\u0005IQ\u0001C\u001b\u0011%\t\u0019oOA\u0001\n\u000b!I\u0004C\u0005\u0004\n\u0006\t\t\u0011b\u0001\u0005B\u00191AQI\u0001\u0005\t\u000fB!B!=P\u0005\u0003\u0005\u000b\u0011\u0002B{\u0011\u00191x\n\"\u0001\u0005J!IAqJ(A\u0002\u0013\u0005A\u0011\u000b\u0005\n\t'z\u0005\u0019!C\u0001\t+B\u0001\u0002\"\u0017PA\u0003&\u00111\u0018\u0005\b\t7zE\u0011AB>\u0011\u001d!if\u0014C\u0001\u0003sCq\u0001b\u0018\u0002\t\u0007!\tG\u0002\u0004\u0005r\u0005\u0019A1\u000f\u0005\u000b\u0005cD&Q1A\u0005\u0002\u0011U\u0004B\u0003B\u00001\n\u0005\t\u0015!\u0003\u0005x!1a\u000f\u0017C\u0001\t{Bq\u0001b!Y\t\u0003\u0011\u0019\u0010C\u0005\u00028b\u000b\t\u0011\"\u0011\u0002:\"I\u0011\u0011\u0019-\u0002\u0002\u0013\u0005CQQ\u0004\n\t\u0013\u000b\u0011\u0011!E\u0001\t\u00173\u0011\u0002\"\u001d\u0002\u0003\u0003E\t\u0001\"$\t\rY\u0004G\u0011\u0001CH\u0011\u001d!\t\n\u0019C\u0003\t'C\u0011\"!8a\u0003\u0003%)\u0001b&\t\u0013\u0005\r\b-!A\u0005\u0006\u0011m\u0005\"\u0003CE\u0003\u0005\u0005I1\u0001CR\u0003\u001d\u0001\u0018mY6bO\u0016T!\u0001[5\u0002\tU$\u0018\u000e\u001c\u0006\u0002U\u00061!M]3fu\u0016\u001c\u0001\u0001\u0005\u0002n\u00035\tqMA\u0004qC\u000e\\\u0017mZ3\u0014\u0005\u0005\u0001\bCA9u\u001b\u0005\u0011(\"A:\u0002\u000bM\u001c\u0017\r\\1\n\u0005U\u0014(AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u0002Y\u0006Q!/Z1e\u001f\nTWm\u0019;\u0016\u0005ilHcA>\u0002\u000eA\u0011A0 \u0007\u0001\t\u0015q8A1\u0001\u0000\u0005\u0005!\u0016\u0003BA\u0001\u0003\u000f\u00012!]A\u0002\u0013\r\t)A\u001d\u0002\b\u001d>$\b.\u001b8h!\r\t\u0018\u0011B\u0005\u0004\u0003\u0017\u0011(aA!os\"9\u0011qB\u0002A\u0002\u0005E\u0011a\u00017pGB!\u00111CA\u000f\u001b\t\t)B\u0003\u0003\u0002\u0018\u0005e\u0011AA5p\u0015\t\tY\"\u0001\u0003kCZ\f\u0017\u0002BA\u0010\u0003+\u0011AAR5mKV!\u00111EA\u0014)\u0019\t)#!\u000b\u0002,A\u0019A0a\n\u0005\u000by$!\u0019A@\t\u000f\u0005=A\u00011\u0001\u0002\u0012!9\u0011Q\u0006\u0003A\u0002\u0005=\u0012AF5h]>\u0014XmU3sS\u0006dg+\u001a:tS>tW+\u0013#\u0011\u0007E\f\t$C\u0002\u00024I\u0014qAQ8pY\u0016\fg.\u0001\ttKJL\u0017\r\\5{KR{')\u001f;fgV!\u0011\u0011HA')\u0011\tY$a\u0012\u0011\u000bE\fi$!\u0011\n\u0007\u0005}\"OA\u0003BeJ\f\u0017\u0010E\u0002r\u0003\u0007J1!!\u0012s\u0005\u0011\u0011\u0015\u0010^3\t\u000f\u0005%S\u00011\u0001\u0002L\u0005\u0019qN\u00196\u0011\u0007q\fi\u0005B\u0003\u007f\u000b\t\u0007q0\u0001\u000beKN,'/[1mSj,gI]8n\u0005f$Xm]\u000b\u0005\u0003'\n9\u0006\u0006\u0003\u0002V\u0005e\u0003c\u0001?\u0002X\u0011)aP\u0002b\u0001\u007f\"9\u00111\f\u0004A\u0002\u0005m\u0012!\u00022zi\u0016\u001c\u0018A\u00078p]N$X\u000f]5e\u001f\nTWm\u0019;J]B,Ho\u0015;sK\u0006lGCBA1\u0003O\n\t\b\u0005\u0003\u0002\u0014\u0005\r\u0014\u0002BA3\u0003+\u0011\u0011c\u00142kK\u000e$\u0018J\u001c9viN#(/Z1n\u0011\u001d\tIg\u0002a\u0001\u0003W\naa\u001d;sK\u0006l\u0007\u0003BA\n\u0003[JA!a\u001c\u0002\u0016\tY\u0011J\u001c9viN#(/Z1n\u0011%\tic\u0002I\u0001\u0002\u0004\ty#\u0001\u0013o_:\u001cH/\u001e9jI>\u0013'.Z2u\u0013:\u0004X\u000f^*ue\u0016\fW\u000e\n3fM\u0006,H\u000e\u001e\u00133+\t\t9H\u000b\u0003\u00020\u0005e4FAA>!\u0011\ti(a\"\u000e\u0005\u0005}$\u0002BAA\u0003\u0007\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005\u0015%/\u0001\u0006b]:|G/\u0019;j_:LA!!#\u0002\u0000\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0003\u0011\u0019KG.Z+uS2\u001c2!CAH!\r\t\u0018\u0011S\u0005\u0004\u0003'\u0013(AB!osZ\u000bG.\u0001\u0002tGV\u0011\u0011\u0011\u0014\t\u0004c\u0006m\u0015bAAOe\ni1\u000b\u001e:j]\u001e\u001cuN\u001c;fqR\f1a]2!)\u0011\t\u0019+a*\u0011\u0007\u0005\u0015\u0016\"D\u0001\u0002\u0011\u001d\t)\n\u0004a\u0001\u00033\u000bAAZ5mKR!\u0011\u0011CAW\u0011\u001d\ty+\u0004a\u0001\u0003c\u000bA!\u0019:hgB)\u0011/a-\u0002\b%\u0019\u0011Q\u0017:\u0003\u0015q\u0012X\r]3bi\u0016$g(\u0001\u0005iCND7i\u001c3f)\t\tY\fE\u0002r\u0003{K1!a0s\u0005\rIe\u000e^\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005=\u0012Q\u0019\u0005\n\u0003\u000f|\u0011\u0011!a\u0001\u0003\u000f\t1\u0001\u001f\u00132\u0003!1\u0015\u000e\\3Vi&d\u0007cAAS#M\u0011\u0011\u0003\u001d\u000b\u0003\u0003\u0017\faBZ5mK\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u0002V\u0006eG\u0003BA\t\u0003/Dq!a,\u0014\u0001\u0004\t\t\fC\u0004\u0002\\N\u0001\r!a)\u0002\u000b\u0011\"\b.[:\u0002%!\f7\u000f[\"pI\u0016$S\r\u001f;f]NLwN\u001c\u000b\u0005\u0003s\u000b\t\u000fC\u0004\u0002\\R\u0001\r!a)\u0002!\u0015\fX/\u00197tI\u0015DH/\u001a8tS>tG\u0003BAt\u0003W$B!a\f\u0002j\"I\u0011qY\u000b\u0002\u0002\u0003\u0007\u0011q\u0001\u0005\b\u00037,\u0002\u0019AAR)\u0011\t\u0019+a<\t\u000f\u0005Ue\u00031\u0001\u0002\u001a\u0006YqO]5uK>\u0013'.Z2u+\u0011\t)Pa\u0002\u0015\r\u0005]\u0018Q B\u0001!\r\t\u0018\u0011`\u0005\u0004\u0003w\u0014(\u0001B+oSRDq!a@\u0018\u0001\u0004\t\t\"A\u0002pkRDqAa\u0001\u0018\u0001\u0004\u0011)!\u0001\u0004qCJ\u001cXM\u001d\t\u0004y\n\u001dA!\u0002@\u0018\u0005\u0004y(\u0001\u0002+P\t>\u000b\u0001\u0002T(D\u0003RKuJT\u000b\u0003\u0005\u001f\u0001BA!\u0005\u0003\u00185\u0011!1\u0003\u0006\u0005\u0005+\tI\"\u0001\u0003mC:<\u0017\u0002\u0002B\r\u0005'\u0011aa\u0015;sS:<\u0007fA\r\u0003\u001eA\u0019\u0011Oa\b\n\u0007\t\u0005\"O\u0001\u0005o_&tG.\u001b8f\u0003\u0019\u0019\u0015\t\u0014'F%R!!q\u0002B\u0014\u0011\u001d\u0011IC\u0007a\u0001\u0003w\u000b1A\u001c;iQ\rQ\"QD\u0001\r[\u0016lwN]=TiJLgnZ\u000b\u0003\u0005c\u0001BAa\r\u0003B9!!Q\u0007B\u001f!\r\u00119D]\u0007\u0003\u0005sQ1Aa\u000fl\u0003\u0019a$o\\8u}%\u0019!q\b:\u0002\rA\u0013X\rZ3g\u0013\u0011\u0011IBa\u0011\u000b\u0007\t}\"/A\u0003ue\u0006\u001cW-\u0006\u0003\u0003J\t5C\u0003\u0002B&\u0005\u001f\u00022\u0001 B'\t\u0015qHD1\u0001\u0000\u0011\u001d\u0011\t\u0006\ba\u0001\u0005\u0017\n\u0011!\u0019\u0002\n'\u0016\fX\t\u001f;sCN,BAa\u0016\u0003nM\u0011Q\u0004]\u0001\u0002gB1!Q\fB3\u0005WrAAa\u0018\u0003d9!!q\u0007B1\u0013\u0005\u0019\u0018B\u00014s\u0013\u0011\u00119G!\u001b\u0003\u0007M+\u0017O\u0003\u0002geB\u0019AP!\u001c\u0005\u000byl\"\u0019A@\u0015\t\tE$1\u000f\t\u0006\u0003Kk\"1\u000e\u0005\b\u00053z\u0002\u0019\u0001B.\u0003\u0019\t'oZ7bqR!\u00111\u0018B=\u0011\u001d\u0011Y\b\ta\u0002\u0005{\n\u0001b\u001c:eKJLgn\u001a\t\u0007\u0005;\u0012yHa\u001b\n\t\t\u0005%\u0011\u000e\u0002\t\u001fJ$WM]5oO\u00061\u0011M]4nS:$B!a/\u0003\b\"9!1P\u0011A\u0004\tu\u0014AB;oM>dG-\u0006\u0004\u0003\u000e\nm&Q\u0013\u000b\u0005\u0005\u001f\u0013I\r\u0006\u0003\u0003\u0012\n}F\u0003\u0002BJ\u00053\u00032\u0001 BK\t\u0019\u00119J\tb\u0001\u007f\n\u0011Ak\u001c\u0005\b\u00057\u0013\u00039\u0001BO\u0003\r\u0019'M\u001a\t\u000b\u0005?\u0013\u0019La\u0017\u0003:\nMe\u0002\u0002BQ\u0005_sAAa)\u0003*:!!q\fBS\u0013\r\u00119K]\u0001\u000bG>dG.Z2uS>t\u0017\u0002\u0002BV\u0005[\u000baaY8na\u0006$(b\u0001BTe&\u0019aM!-\u000b\t\t-&QV\u0005\u0005\u0005k\u00139LA\u0005Ck&dGM\u0012:p[*\u0019aM!-\u0011\u0007q\u0014Y\f\u0002\u0004\u0003>\n\u0012\ra \u0002\u0002+\"9!\u0011\u0019\u0012A\u0002\t\r\u0017!\u00014\u0011\u0013E\u0014)M!/\u0003l\te\u0016b\u0001Bde\nIa)\u001e8di&|gN\r\u0005\b\u0005\u0017\u0014\u0003\u0019\u0001B]\u0003\u0011Ig.\u001b;\u0002\u0013M+\u0017/\u0012=ue\u0006\u001cX\u0003\u0002Bi\u0005/$BAa5\u0003ZB)\u0011QU\u000f\u0003VB\u0019APa6\u0005\u000by\u001c#\u0019A@\t\u000f\te3\u00051\u0001\u0003\\B1!Q\fB3\u0005+\fa\"\u0019:sCf\u001cV-]#yiJ\f7/\u0006\u0003\u0003b\n\u001dH\u0003\u0002Br\u0005S\u0004R!!*\u001e\u0005K\u00042\u0001 Bt\t\u0015qHE1\u0001\u0000\u0011\u001d\u0011I\u0006\na\u0001\u0005W\u0004R!]A\u001f\u0005K\u0014Q\"Q<fg>lWMQ5u'\u0016$8cA\u0013\u0002\u0010\u0006\u0011!m]\u000b\u0003\u0005k\u0004BAa>\u0003|6\u0011!\u0011 \u0006\u0004Q\u0006e\u0011\u0002\u0002B\u007f\u0005s\u0014aAQ5u'\u0016$\u0018a\u00012tAQ!11AB\u0003!\r\t)+\n\u0005\b\u0005cD\u0003\u0019\u0001B{\u0003\u0015\t\u0007\u000f\u001d7z)\u0011\tyca\u0003\t\u000f\r5\u0011\u00061\u0001\u0002<\u0006\t!/\u0001\u0005ji\u0016\u0014\u0018\r^8s+\t\u0019\u0019\u0002\u0005\u0004\u0003^\rU\u00111X\u0005\u0005\u0007/\u0011IG\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003\ri\u0017\r]\u000b\u0007\u0007;\u0019ica\t\u0015\t\r}1q\u0006\u000b\u0005\u0007C\u00199\u0003E\u0002}\u0007G!aa!\n,\u0005\u0004y(!A\"\t\u000f\tm5\u0006q\u0001\u0004*AQ!q\u0014BZ\u0005k\u001cYc!\t\u0011\u0007q\u001ci\u0003\u0002\u0004\u0003>.\u0012\ra \u0005\b\u0005\u0003\\\u0003\u0019AB\u0019!\u001d\t81GA^\u0007WI1a!\u000es\u0005%1UO\\2uS>t\u0017'A\u0004g_J,\u0017m\u00195\u0016\t\rm21\t\u000b\u0005\u0003o\u001ci\u0004C\u0004\u0003B2\u0002\raa\u0010\u0011\u000fE\u001c\u0019$a/\u0004BA\u0019Apa\u0011\u0005\r\tuFF1\u0001\u0000\u0003\u001d!\u0013-\u001c9%KF$BA!>\u0004J!911J\u0017A\u0002\tU\u0018!B8uQ\u0016\u0014\u0018!\u0004\u0013b[B$C/\u001b7eK\u0012*\u0017\u000f\u0006\u0003\u0003v\u000eE\u0003bBB&]\u0001\u0007!Q_\u0001\bI\t\f'\u000fJ3r)\u0011\u0011)pa\u0016\t\u000f\r-s\u00061\u0001\u0003v\u00061A%\u001e9%KF$BA!>\u0004^!911\n\u0019A\u0002\tU\u0018\u0001\u0002\u0013cCJ$BA!>\u0004d!911J\u0019A\u0002\tU\u0018A\u0003\u0013b[B$C/\u001b7eKR!!Q_B5\u0011\u001d\u0019YE\ra\u0001\u0005k\fA\u0001J1naR!!Q_B8\u0011\u001d\u0019Ye\ra\u0001\u0005k\f1\u0001J;q)\u0011\u0011)p!\u001e\t\u000f\r-C\u00071\u0001\u0003v\u0006!1m\u001c9z\u0003!qwN\\#naRLXCAA\u0018\u0003!!\u0003\u000f\\;tI\u0015\fH\u0003\u0002B{\u0007\u0003Cqaa!8\u0001\u0004\tY,A\u0001j)\u0011\tyca\"\t\u0013\u0005\u001d\u0017(!AA\u0002\u0005\u001d\u0011!D!xKN|W.\u001a\"jiN+G\u000fE\u0002\u0002&n\u001a\"a\u000f9\u0015\u0005\r-\u0015aD1qa2LH%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\rU5\u0011\u0014\u000b\u0005\u0003_\u00199\nC\u0004\u0004\u000eu\u0002\r!a/\t\u000f\u0005mW\b1\u0001\u0004\u0004\u0005\u0011\u0012\u000e^3sCR|'\u000fJ3yi\u0016t7/[8o)\u0011\u0019\u0019ba(\t\u000f\u0005mg\b1\u0001\u0004\u0004\u0005iQ.\u00199%Kb$XM\\:j_:,ba!*\u00046\u000e5F\u0003BBT\u0007w#Ba!+\u00048R!11VBX!\ra8Q\u0016\u0003\u0007\u0007Ky$\u0019A@\t\u000f\tmu\bq\u0001\u00042BQ!q\u0014BZ\u0005k\u001c\u0019la+\u0011\u0007q\u001c)\f\u0002\u0004\u0003>~\u0012\ra \u0005\b\u0005\u0003|\u0004\u0019AB]!\u001d\t81GA^\u0007gCq!a7@\u0001\u0004\u0019\u0019!A\tg_J,\u0017m\u00195%Kb$XM\\:j_:,Ba!1\u0004LR!11YBg)\u0011\t9p!2\t\u000f\t\u0005\u0007\t1\u0001\u0004HB9\u0011oa\r\u0002<\u000e%\u0007c\u0001?\u0004L\u00121!Q\u0018!C\u0002}Dq!a7A\u0001\u0004\u0019\u0019!A\t%C6\u0004H%Z9%Kb$XM\\:j_:$Baa5\u0004XR!!Q_Bk\u0011\u001d\u0019Y%\u0011a\u0001\u0005kDq!a7B\u0001\u0004\u0019\u0019!A\f%C6\u0004H\u0005^5mI\u0016$S-\u001d\u0013fqR,gn]5p]R!1Q\\Bq)\u0011\u0011)pa8\t\u000f\r-#\t1\u0001\u0003v\"9\u00111\u001c\"A\u0002\r\r\u0011!\u0005\u0013cCJ$S-\u001d\u0013fqR,gn]5p]R!1q]Bv)\u0011\u0011)p!;\t\u000f\r-3\t1\u0001\u0003v\"9\u00111\\\"A\u0002\r\r\u0011\u0001\u0005\u0013va\u0012*\u0017\u000fJ3yi\u0016t7/[8o)\u0011\u0019\tp!>\u0015\t\tU81\u001f\u0005\b\u0007\u0017\"\u0005\u0019\u0001B{\u0011\u001d\tY\u000e\u0012a\u0001\u0007\u0007\ta\u0002\n2be\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u0004|\u000e}H\u0003\u0002B{\u0007{Dqaa\u0013F\u0001\u0004\u0011)\u0010C\u0004\u0002\\\u0016\u0003\raa\u0001\u0002)\u0011\nW\u000e\u001d\u0013uS2$W\rJ3yi\u0016t7/[8o)\u0011!)\u0001\"\u0003\u0015\t\tUHq\u0001\u0005\b\u0007\u00172\u0005\u0019\u0001B{\u0011\u001d\tYN\u0012a\u0001\u0007\u0007\ta\u0002J1na\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u0005\u0010\u0011MA\u0003\u0002B{\t#Aqaa\u0013H\u0001\u0004\u0011)\u0010C\u0004\u0002\\\u001e\u0003\raa\u0001\u0002\u001b\u0011*\b\u000fJ3yi\u0016t7/[8o)\u0011!I\u0002\"\b\u0015\t\tUH1\u0004\u0005\b\u0007\u0017B\u0005\u0019\u0001B{\u0011\u001d\tY\u000e\u0013a\u0001\u0007\u0007\tabY8qs\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u0003v\u0012\r\u0002bBAn\u0013\u0002\u000711A\u0001\u0013]>tW)\u001c9us\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u00020\u0011%\u0002bBAn\u0015\u0002\u000711A\u0001\u0013IAdWo\u001d\u0013fc\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u00050\u0011MB\u0003\u0002B{\tcAqaa!L\u0001\u0004\tY\fC\u0004\u0002\\.\u0003\raa\u0001\u0015\t\u0005eFq\u0007\u0005\b\u00037d\u0005\u0019AB\u0002)\u0011!Y\u0004b\u0010\u0015\t\u0005=BQ\b\u0005\n\u0003\u000fl\u0015\u0011!a\u0001\u0003\u000fAq!a7N\u0001\u0004\u0019\u0019\u0001\u0006\u0003\u0004\u0004\u0011\r\u0003b\u0002By\u001d\u0002\u0007!Q\u001f\u0002\u000b\u0005NKE/\u001a:bi>\u00148\u0003B(q\u0007'!B\u0001b\u0013\u0005NA\u0019\u0011QU(\t\u000f\tE\u0018\u000b1\u0001\u0003v\u0006Q1-\u001e:sK:$()\u001b;\u0016\u0005\u0005m\u0016AD2veJ,g\u000e\u001e\"ji~#S-\u001d\u000b\u0005\u0003o$9\u0006C\u0005\u0002HN\u000b\t\u00111\u0001\u0002<\u0006Y1-\u001e:sK:$()\u001b;!\u0003\u001dA\u0017m\u001d(fqR\fAA\\3yi\u0006QqLY5ug\u0016$8M\u00194\u0016\t\u0011\rD\u0011N\u000b\u0003\tK\u0002\"Ba(\u00034\nUHq\rC6!\raH\u0011\u000e\u0003\u0007\u0005{;&\u0019A@\u0011\r\tMBQ\u000eC4\u0013\u0011!yGa\u0011\u0003\u0007M+GO\u0001\nBo\u0016\u001cx.\\3TG\u0006d\u0017MQ5u'\u0016$8c\u0001-\u0002\u0010V\u0011Aq\u000f\t\u0005\ts\"Y(\u0004\u0002\u0003.&!!Q BW)\u0011!y\b\"!\u0011\u0007\u0005\u0015\u0006\fC\u0004\u0003rn\u0003\r\u0001b\u001e\u0002\u0019Q|'*\u0019<b\u0005&$8+\u001a;\u0015\t\u0005=Bq\u0011\u0005\n\u0003\u000ft\u0016\u0011!a\u0001\u0003\u000f\t!#Q<fg>lWmU2bY\u0006\u0014\u0015\u000e^*fiB\u0019\u0011Q\u00151\u0014\u0005\u0001\u0004HC\u0001CF\u0003Y!xNS1wC\nKGoU3uI\u0015DH/\u001a8tS>tG\u0003\u0002B{\t+Cq!a7c\u0001\u0004!y\b\u0006\u0003\u0002:\u0012e\u0005bBAnG\u0002\u0007Aq\u0010\u000b\u0005\t;#\t\u000b\u0006\u0003\u00020\u0011}\u0005\"CAdI\u0006\u0005\t\u0019AA\u0004\u0011\u001d\tY\u000e\u001aa\u0001\t\u007f\"B\u0001b \u0005&\"9!\u0011_3A\u0002\u0011]\u0004"
)
public final class package {
   public static BitSet AwesomeScalaBitSet(final BitSet bs) {
      return package$.MODULE$.AwesomeScalaBitSet(bs);
   }

   public static BuildFrom _bitsetcbf() {
      return package$.MODULE$._bitsetcbf();
   }

   public static java.util.BitSet AwesomeBitSet(final java.util.BitSet bs) {
      return package$.MODULE$.AwesomeBitSet(bs);
   }

   public static SeqExtras arraySeqExtras(final Object s) {
      return package$.MODULE$.arraySeqExtras(s);
   }

   public static SeqExtras SeqExtras(final Seq s) {
      return package$.MODULE$.SeqExtras(s);
   }

   public static Object trace(final Object a) {
      return package$.MODULE$.trace(a);
   }

   public static String memoryString() {
      return package$.MODULE$.memoryString();
   }

   public static String CALLER(final int nth) {
      return package$.MODULE$.CALLER(nth);
   }

   public static String LOCATION() {
      return package$.MODULE$.LOCATION();
   }

   public static void writeObject(final File out, final Object parser) {
      package$.MODULE$.writeObject(out, parser);
   }

   public static StringContext FileUtil(final StringContext sc) {
      return package$.MODULE$.FileUtil(sc);
   }

   public static boolean nonstupidObjectInputStream$default$2() {
      return package$.MODULE$.nonstupidObjectInputStream$default$2();
   }

   public static ObjectInputStream nonstupidObjectInputStream(final InputStream stream, final boolean ignoreSerialVersionUID) {
      return package$.MODULE$.nonstupidObjectInputStream(stream, ignoreSerialVersionUID);
   }

   public static Object deserializeFromBytes(final byte[] bytes) {
      return package$.MODULE$.deserializeFromBytes(bytes);
   }

   public static byte[] serializeToBytes(final Object obj) {
      return package$.MODULE$.serializeToBytes(obj);
   }

   public static Object readObject(final File loc, final boolean ignoreSerialVersionUID) {
      return package$.MODULE$.readObject(loc, ignoreSerialVersionUID);
   }

   public static Object readObject(final File loc) {
      return package$.MODULE$.readObject(loc);
   }

   public static final class FileUtil {
      private final StringContext sc;

      public StringContext sc() {
         return this.sc;
      }

      public File file(final Seq args) {
         return package.FileUtil$.MODULE$.file$extension(this.sc(), args);
      }

      public int hashCode() {
         return package.FileUtil$.MODULE$.hashCode$extension(this.sc());
      }

      public boolean equals(final Object x$1) {
         return package.FileUtil$.MODULE$.equals$extension(this.sc(), x$1);
      }

      public FileUtil(final StringContext sc) {
         this.sc = sc;
      }
   }

   public static class FileUtil$ {
      public static final FileUtil$ MODULE$ = new FileUtil$();

      public final File file$extension(final StringContext $this, final Seq args) {
         return new File(.MODULE$.standardInterpolator((str) -> .MODULE$.processEscapes(str), (scala.collection.Seq)scala.collection.immutable.List..MODULE$.apply(args), $this.parts()));
      }

      public final int hashCode$extension(final StringContext $this) {
         return $this.hashCode();
      }

      public final boolean equals$extension(final StringContext $this, final Object x$1) {
         boolean var3;
         if (x$1 instanceof FileUtil) {
            var3 = true;
         } else {
            var3 = false;
         }

         boolean var7;
         if (var3) {
            label32: {
               label31: {
                  StringContext var5 = x$1 == null ? null : ((FileUtil)x$1).sc();
                  if ($this == null) {
                     if (var5 == null) {
                        break label31;
                     }
                  } else if ($this.equals(var5)) {
                     break label31;
                  }

                  var7 = false;
                  break label32;
               }

               var7 = true;
            }

            if (var7) {
               var7 = true;
               return var7;
            }
         }

         var7 = false;
         return var7;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class SeqExtras {
      private final Seq s;

      public int argmax(final Ordering ordering) {
         return ((Tuple2)((IterableOnceOps)this.s.zipWithIndex()).reduceLeft((a, b) -> ordering.gt(a._1(), b._1()) ? a : b))._2$mcI$sp();
      }

      public int argmin(final Ordering ordering) {
         return ((Tuple2)((IterableOnceOps)this.s.zipWithIndex()).reduceLeft((a, b) -> ordering.lt(a._1(), b._1()) ? a : b))._2$mcI$sp();
      }

      public Object unfold(final Object init, final Function2 f, final BuildFrom cbf) {
         Builder builder = cbf.newBuilder(this.s);
         builder.sizeHint(this.s.size() + 1);
         ObjectRef u = ObjectRef.create(init);
         builder.$plus$eq(u.elem);
         this.s.foreach((t) -> {
            u.elem = f.apply(u.elem, t);
            return (Builder)builder.$plus$eq(u.elem);
         });
         return builder.result();
      }

      public SeqExtras(final Seq s) {
         this.s = s;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static final class AwesomeBitSet {
      private final java.util.BitSet bs;

      public java.util.BitSet bs() {
         return this.bs;
      }

      public boolean apply(final int r) {
         return package.AwesomeBitSet$.MODULE$.apply$extension(this.bs(), r);
      }

      public Iterator iterator() {
         return package.AwesomeBitSet$.MODULE$.iterator$extension(this.bs());
      }

      public Object map(final Function1 f, final BuildFrom cbf) {
         return package.AwesomeBitSet$.MODULE$.map$extension(this.bs(), f, cbf);
      }

      public void foreach(final Function1 f) {
         package.AwesomeBitSet$.MODULE$.foreach$extension(this.bs(), f);
      }

      public java.util.BitSet $amp$eq(final java.util.BitSet other) {
         return package.AwesomeBitSet$.MODULE$.$amp$eq$extension(this.bs(), other);
      }

      public java.util.BitSet $amp$tilde$eq(final java.util.BitSet other) {
         return package.AwesomeBitSet$.MODULE$.$amp$tilde$eq$extension(this.bs(), other);
      }

      public java.util.BitSet $bar$eq(final java.util.BitSet other) {
         return package.AwesomeBitSet$.MODULE$.$bar$eq$extension(this.bs(), other);
      }

      public java.util.BitSet $up$eq(final java.util.BitSet other) {
         return package.AwesomeBitSet$.MODULE$.$up$eq$extension(this.bs(), other);
      }

      public java.util.BitSet $bar(final java.util.BitSet other) {
         return package.AwesomeBitSet$.MODULE$.$bar$extension(this.bs(), other);
      }

      public java.util.BitSet $amp$tilde(final java.util.BitSet other) {
         return package.AwesomeBitSet$.MODULE$.$amp$tilde$extension(this.bs(), other);
      }

      public java.util.BitSet $amp(final java.util.BitSet other) {
         return package.AwesomeBitSet$.MODULE$.$amp$extension(this.bs(), other);
      }

      public java.util.BitSet $up(final java.util.BitSet other) {
         return package.AwesomeBitSet$.MODULE$.$up$extension(this.bs(), other);
      }

      public java.util.BitSet copy() {
         return package.AwesomeBitSet$.MODULE$.copy$extension(this.bs());
      }

      public boolean nonEmpty() {
         return package.AwesomeBitSet$.MODULE$.nonEmpty$extension(this.bs());
      }

      public java.util.BitSet $plus$eq(final int i) {
         return package.AwesomeBitSet$.MODULE$.$plus$eq$extension(this.bs(), i);
      }

      public int hashCode() {
         return package.AwesomeBitSet$.MODULE$.hashCode$extension(this.bs());
      }

      public boolean equals(final Object x$1) {
         return package.AwesomeBitSet$.MODULE$.equals$extension(this.bs(), x$1);
      }

      public AwesomeBitSet(final java.util.BitSet bs) {
         this.bs = bs;
      }
   }

   public static class AwesomeBitSet$ {
      public static final AwesomeBitSet$ MODULE$ = new AwesomeBitSet$();

      public final boolean apply$extension(final java.util.BitSet $this, final int r) {
         return $this.get(r);
      }

      public final Iterator iterator$extension(final java.util.BitSet $this) {
         return new BSIterator($this);
      }

      public final Object map$extension(final java.util.BitSet $this, final Function1 f, final BuildFrom cbf) {
         Builder r = cbf.newBuilder($this);
         r.sizeHint($this.size());
         this.iterator$extension($this).foreach((i) -> $anonfun$map$1(r, f, BoxesRunTime.unboxToInt(i)));
         return r.result();
      }

      public final void foreach$extension(final java.util.BitSet $this, final Function1 f) {
         for(int i = $this.nextSetBit(0); i != -1; i = $this.nextSetBit(i + 1)) {
            f.apply(BoxesRunTime.boxToInteger(i));
         }

      }

      public final java.util.BitSet $amp$eq$extension(final java.util.BitSet $this, final java.util.BitSet other) {
         $this.and(other);
         return $this;
      }

      public final java.util.BitSet $amp$tilde$eq$extension(final java.util.BitSet $this, final java.util.BitSet other) {
         $this.andNot(other);
         return $this;
      }

      public final java.util.BitSet $bar$eq$extension(final java.util.BitSet $this, final java.util.BitSet other) {
         $this.or(other);
         return $this;
      }

      public final java.util.BitSet $up$eq$extension(final java.util.BitSet $this, final java.util.BitSet other) {
         $this.xor(other);
         return $this;
      }

      public final java.util.BitSet $bar$extension(final java.util.BitSet $this, final java.util.BitSet other) {
         return this.$bar$eq$extension(package$.MODULE$.AwesomeBitSet(this.copy$extension($this)), other);
      }

      public final java.util.BitSet $amp$tilde$extension(final java.util.BitSet $this, final java.util.BitSet other) {
         return this.$amp$tilde$eq$extension(package$.MODULE$.AwesomeBitSet(this.copy$extension($this)), other);
      }

      public final java.util.BitSet $amp$extension(final java.util.BitSet $this, final java.util.BitSet other) {
         return this.$amp$eq$extension(package$.MODULE$.AwesomeBitSet(this.copy$extension($this)), other);
      }

      public final java.util.BitSet $up$extension(final java.util.BitSet $this, final java.util.BitSet other) {
         return this.$up$eq$extension(package$.MODULE$.AwesomeBitSet(this.copy$extension($this)), other);
      }

      public final java.util.BitSet copy$extension(final java.util.BitSet $this) {
         return (java.util.BitSet)$this.clone();
      }

      public final boolean nonEmpty$extension(final java.util.BitSet $this) {
         return !$this.isEmpty();
      }

      public final java.util.BitSet $plus$eq$extension(final java.util.BitSet $this, final int i) {
         $this.set(i);
         return $this;
      }

      public final int hashCode$extension(final java.util.BitSet $this) {
         return $this.hashCode();
      }

      public final boolean equals$extension(final java.util.BitSet $this, final Object x$1) {
         boolean var3;
         if (x$1 instanceof AwesomeBitSet) {
            var3 = true;
         } else {
            var3 = false;
         }

         boolean var7;
         if (var3) {
            label32: {
               label31: {
                  java.util.BitSet var5 = x$1 == null ? null : ((AwesomeBitSet)x$1).bs();
                  if ($this == null) {
                     if (var5 == null) {
                        break label31;
                     }
                  } else if ($this.equals(var5)) {
                     break label31;
                  }

                  var7 = false;
                  break label32;
               }

               var7 = true;
            }

            if (var7) {
               var7 = true;
               return var7;
            }
         }

         var7 = false;
         return var7;
      }

      // $FF: synthetic method
      public static final Builder $anonfun$map$1(final Builder r$1, final Function1 f$2, final int i) {
         return (Builder)r$1.$plus$eq(f$2.apply(BoxesRunTime.boxToInteger(i)));
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   private static class BSIterator implements Iterator {
      private final java.util.BitSet bs;
      private int currentBit;

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

      public int currentBit() {
         return this.currentBit;
      }

      public void currentBit_$eq(final int x$1) {
         this.currentBit = x$1;
      }

      public boolean hasNext() {
         return this.currentBit() != -1;
      }

      public int next() {
         scala.Predef..MODULE$.assert(this.currentBit() != -1);
         int cur = this.currentBit();
         this.currentBit_$eq(this.bs.nextSetBit(cur + 1));
         return cur;
      }

      public BSIterator(final java.util.BitSet bs) {
         this.bs = bs;
         IterableOnce.$init$(this);
         IterableOnceOps.$init$(this);
         Iterator.$init$(this);
         this.currentBit = bs.nextSetBit(0);
      }
   }

   public static final class AwesomeScalaBitSet {
      private final BitSet bs;

      public BitSet bs() {
         return this.bs;
      }

      public java.util.BitSet toJavaBitSet() {
         return package.AwesomeScalaBitSet$.MODULE$.toJavaBitSet$extension(this.bs());
      }

      public int hashCode() {
         return package.AwesomeScalaBitSet$.MODULE$.hashCode$extension(this.bs());
      }

      public boolean equals(final Object x$1) {
         return package.AwesomeScalaBitSet$.MODULE$.equals$extension(this.bs(), x$1);
      }

      public AwesomeScalaBitSet(final BitSet bs) {
         this.bs = bs;
      }
   }

   public static class AwesomeScalaBitSet$ {
      public static final AwesomeScalaBitSet$ MODULE$ = new AwesomeScalaBitSet$();

      public final java.util.BitSet toJavaBitSet$extension(final BitSet $this) {
         java.util.BitSet jbs = new java.util.BitSet(BoxesRunTime.unboxToInt($this.lastOption().getOrElse((JFunction0.mcI.sp)() -> 0)) + 1);
         $this.foreach((JFunction1.mcVI.sp)(x$1) -> jbs.set(x$1));
         return jbs;
      }

      public final int hashCode$extension(final BitSet $this) {
         return $this.hashCode();
      }

      public final boolean equals$extension(final BitSet $this, final Object x$1) {
         boolean var3;
         if (x$1 instanceof AwesomeScalaBitSet) {
            var3 = true;
         } else {
            var3 = false;
         }

         boolean var7;
         if (var3) {
            label32: {
               label31: {
                  BitSet var5 = x$1 == null ? null : ((AwesomeScalaBitSet)x$1).bs();
                  if ($this == null) {
                     if (var5 == null) {
                        break label31;
                     }
                  } else if ($this.equals(var5)) {
                     break label31;
                  }

                  var7 = false;
                  break label32;
               }

               var7 = true;
            }

            if (var7) {
               var7 = true;
               return var7;
            }
         }

         var7 = false;
         return var7;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
