package scala.collection.mutable;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.PartialFunction;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.AbstractIterator;
import scala.collection.Factory;
import scala.collection.IterableFactory;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.StepperShape$;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.View;
import scala.collection.convert.impl.AnyTableStepper;
import scala.collection.convert.impl.DoubleTableStepper;
import scala.collection.convert.impl.IntTableStepper;
import scala.collection.convert.impl.LongTableStepper;
import scala.collection.generic.DefaultSerializationProxy;
import scala.collection.immutable.BitmapIndexedSetNode;
import scala.math.Integral;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt$;
import scala.runtime.Statics;
import scala.util.hashing.MurmurHash3$;

@ScalaSignature(
   bytes = "\u0006\u0005\r\u001de\u0001B'O\u0005UC\u0011b \u0001\u0003\u0002\u0003\u0006I!!\u0001\t\u0015\u0005\u001d\u0001A!A!\u0002\u0013\tI\u0001C\u0004\u0002\u0010\u0001!\t!!\u0005\t\u000f\u0005=\u0001\u0001\"\u0001\u0002\u0018!q\u0011\u0011\u0004\u0001\u0005\u0002\u0003\u0015\t\u0011!Q!\n\u0005m\u0001\u0002\u0003B%\u0001\u0001\u0006K!!\u0001\t\u0011\t-\u0003\u0001)Q\u0005\u0003\u0003AqA!\u0014\u0001\t\u0003\n)\t\u0003\u0005\u0003P\u0001!\t\u0001\u0015B)\u00119\u0011y\u0006\u0001C\u0001\u0002\u000b\u0005\t\u0011)C\u0005\u0005CBaBa\u001a\u0001\t\u0003\u0005)\u0011!A!\n\u0013\u0011I\u0007\u0003\b\u0003r\u0001!\t\u0011!B\u0001\u0002\u0003&IAa\u001d\t\u000f\te\u0004\u0001\"\u0011\u0003|!A\u0011Q\u001f\u0001!\n\u0013\u00119\tC\u0004\u0003\u000e\u0002!\tEa$\t\u000f\tM\u0005\u0001\"\u0011\u0003\u0016\"9!\u0011\u0014\u0001\u0005B\tm\u0005b\u0002BS\u0001\u0011\u0005#q\u0015\u0005\t\u0005W\u0003\u0001\u0015\"\u0003\u0003.\"A!1\u0017\u0001!\n\u0013\u0011)\fC\u0004\u00034\u0002!\tEa/\u0007\u0011\t}\u0006\u0001iA\u0005\u0005\u0003Dq!a\u0004\u0017\t\u0003\u0011y\r\u0003\u0005\u0003TZ\u0001\u000b\u0015BA\u0001\u0011!\u0011)N\u0006Q!\n\u0005\u0005\u0002\u0002\u0003Bl-\u0001\u0006I!!\u0001\t\u0011\teg\u0003)D\t\u00057DqA!9\u0017\t\u0003\u0011\u0019\u000fC\u0004\u0002dZ!\tA!:\t\u000f\t\u001d\b\u0001\"\u0011\u0003j\"A!\u0011\u001f\u0001\u0005\u0002A\u0013\u0019\u0010C\u0004\u0003x\u0002!\tE!?\t\u0011\rU\u0002\u0001)C\u0005\u0007oAqa!\u0010\u0001\t\u0003\u001ay\u0004\u0003\u0005\u0004H\u0001\u0001K\u0011BB%\u0011!\u0019y\u0005\u0001Q\u0005\n\rE\u0003bBB+\u0001\u0011\u00051q\u000b\u0005\b\u00073\u0002A\u0011IB.\u0011\u001d\u0019i\u0006\u0001C\u0001\u0007?Bqa!\u001a\u0001\t\u0003\u00199\u0007C\u0004\u0004n\u0001!\t%!\"\t\u000f\r=\u0004\u0001\"\u0011\u0003d\"9!q\u0002\u0001\u0005B\rE\u0004\u0002\u0003B\u001e\u0001\u0001&\tb! \t\u0011\r}\u0004\u0001)C)\u0007\u0003Cqaa!\u0001\t\u0003\u001a)iB\u0004\u0002&9C\t!a\n\u0007\r5s\u0005\u0012AA\u0015\u0011\u001d\ty\u0001\rC\u0001\u0003oAq!!\u000f1\t\u0003\tY\u0004C\u0004\u0002RA\"\t!a\u0015\t\u000f\u0005u\u0003\u0007\"\u0001\u0002`!9\u0011Q\f\u0019\u0005\u0002\u0005=\u0004bBA@a\u0011\u0015\u0011\u0011\u0011\u0005\b\u0003\u0007\u0003DQAAC\r\u0019\t9\t\r\u0004\u0002\n\"Q\u0011\u0011\u0014\u001d\u0003\u0006\u0004%\t!!\"\t\u0015\u0005m\u0005H!A!\u0002\u0013\t\t\u0001\u0003\u0006\u0002\ba\u0012)\u0019!C\u0001\u0003\u0003C!\"!(9\u0005\u0003\u0005\u000b\u0011BA\u0005\u0011\u001d\ty\u0001\u000fC\u0001\u0003?Cq!!+9\t\u0003\tY\u000bC\u0004\u0002^a\"\t!!-\u0007\u000f\u0005\u0005\u0007G\u0001)\u0002D\"Q\u0011q\u0019!\u0003\u0002\u0003\u0006I!!3\t\u0015\u0005=\u0007I!A!\u0002\u0013\t\t\u0001\u0003\u0006\u0002R\u0002\u0013\t\u0011)Q\u0005\u0003'Dq!a\u0004A\t\u0003\t)\u000eC\u0004\u0002^\u0002#\t!a8\t\u000f\u0005\u0005\b\t\"\u0001\u0002\u0006\"9\u00111\u001d!\u0005\u0002\u0005\u0015\bbBAt\u0001\u0012\u0005\u0011\u0011\u001e\u0005\b\u0003k\u0004E\u0011AA|\u0011\u001d\u0011y\u0001\u0011C\u0001\u0005#AqAa\nA\t\u0003\u0012I\u0003C\u0005\u0003<A\n\t\u0011\"\u0003\u0003>\t9\u0001*Y:i'\u0016$(BA(Q\u0003\u001diW\u000f^1cY\u0016T!!\u0015*\u0002\u0015\r|G\u000e\\3di&|gNC\u0001T\u0003\u0015\u00198-\u00197b\u0007\u0001)\"AV/\u0014\r\u00019v\r\u001c9t!\rA\u0016lW\u0007\u0002\u001d&\u0011!L\u0014\u0002\f\u0003\n\u001cHO]1diN+G\u000f\u0005\u0002];2\u0001A!\u00020\u0001\u0005\u0004y&!A!\u0012\u0005\u0001$\u0007CA1c\u001b\u0005\u0011\u0016BA2S\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!Y3\n\u0005\u0019\u0014&aA!osB)\u0001\f[.kW&\u0011\u0011N\u0014\u0002\u0007'\u0016$x\n]:\u0011\u0005a\u0003\u0001c\u0001-\u00017B)QN\\.kW6\t\u0001+\u0003\u0002p!\nQ2\u000b\u001e:jGR|\u0005\u000f^5nSj,G-\u0013;fe\u0006\u0014G.Z(qgB!Q.].k\u0013\t\u0011\bKA\fJi\u0016\u0014\u0018M\u00197f\r\u0006\u001cGo\u001c:z\t\u00164\u0017-\u001e7ugB\u0011A\u000f \b\u0003kjt!A^=\u000e\u0003]T!\u0001\u001f+\u0002\rq\u0012xn\u001c;?\u0013\u0005\u0019\u0016BA>S\u0003\u001d\u0001\u0018mY6bO\u0016L!! @\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005m\u0014\u0016aD5oSRL\u0017\r\\\"ba\u0006\u001c\u0017\u000e^=\u0011\u0007\u0005\f\u0019!C\u0002\u0002\u0006I\u00131!\u00138u\u0003)aw.\u00193GC\u000e$xN\u001d\t\u0004C\u0006-\u0011bAA\u0007%\n1Ai\\;cY\u0016\fa\u0001P5oSRtD#B6\u0002\u0014\u0005U\u0001BB@\u0004\u0001\u0004\t\t\u0001C\u0004\u0002\b\r\u0001\r!!\u0003\u0015\u0003-\fqe]2bY\u0006$3m\u001c7mK\u000e$\u0018n\u001c8%[V$\u0018M\u00197fI!\u000b7\u000f[*fi\u0012\"C/\u00192mKB)\u0011-!\b\u0002\"%\u0019\u0011q\u0004*\u0003\u000b\u0005\u0013(/Y=\u0011\t\u0005\r\u0002i\u0017\b\u00031>\nq\u0001S1tQN+G\u000f\u0005\u0002YaM)\u0001'a\u000b\u00022A\u0019\u0011-!\f\n\u0007\u0005=\"K\u0001\u0004B]f\u0014VM\u001a\t\u0005[\u0006M\".C\u0002\u00026A\u0013q\"\u0013;fe\u0006\u0014G.\u001a$bGR|'/\u001f\u000b\u0003\u0003O\tAA\u001a:p[V!\u0011QHA\")\u0011\ty$a\u0012\u0011\ta\u0003\u0011\u0011\t\t\u00049\u0006\rCABA#e\t\u0007qLA\u0001C\u0011\u001d\tIE\ra\u0001\u0003\u0017\n!!\u001b;\u0011\u000b5\fi%!\u0011\n\u0007\u0005=\u0003K\u0001\u0007Ji\u0016\u0014\u0018M\u00197f\u001f:\u001cW-A\u0003f[B$\u00180\u0006\u0003\u0002V\u0005mSCAA,!\u0011A\u0006!!\u0017\u0011\u0007q\u000bY\u0006B\u0003_g\t\u0007q,\u0001\u0006oK^\u0014U/\u001b7eKJ,B!!\u0019\u0002lU\u0011\u00111\r\t\b1\u0006\u0015\u0014\u0011NA7\u0013\r\t9G\u0014\u0002\b\u0005VLG\u000eZ3s!\ra\u00161\u000e\u0003\u0006=R\u0012\ra\u0018\t\u00051\u0002\tI'\u0006\u0003\u0002r\u0005]DCBA:\u0003w\ni\bE\u0004Y\u0003K\n)(!\u001f\u0011\u0007q\u000b9\bB\u0003_k\t\u0007q\f\u0005\u0003Y\u0001\u0005U\u0004BB@6\u0001\u0004\t\t\u0001C\u0004\u0002\bU\u0002\r!!\u0003\u0002#\u0011,g-Y;mi2{\u0017\r\u001a$bGR|'/\u0006\u0002\u0002\n\u00051B-\u001a4bk2$\u0018J\\5uS\u0006d7)\u00199bG&$\u00180\u0006\u0002\u0002\u0002\t1B)Z:fe&\fG.\u001b>bi&|gNR1di>\u0014\u00180\u0006\u0003\u0002\f\u0006U5C\u0002\u001d\u0002,\u000555\u000fE\u0004n\u0003\u001f\u000b\u0019*a&\n\u0007\u0005E\u0005KA\u0004GC\u000e$xN]=\u0011\u0007q\u000b)\nB\u0003_q\t\u0007q\f\u0005\u0003Y\u0001\u0005M\u0015a\u0003;bE2,G*\u001a8hi\"\fA\u0002^1cY\u0016dUM\\4uQ\u0002\n1\u0002\\8bI\u001a\u000b7\r^8sAQ1\u0011\u0011UAS\u0003O\u0003R!a)9\u0003'k\u0011\u0001\r\u0005\b\u00033k\u0004\u0019AA\u0001\u0011\u001d\t9!\u0010a\u0001\u0003\u0013\tAB\u001a:p[N\u0003XmY5gS\u000e$B!a&\u0002.\"9\u0011\u0011\n A\u0002\u0005=\u0006#B7\u0002N\u0005MUCAAZ!\u001dA\u0016QMAJ\u0003/Cs\u0001OA\\\u0003{\u000by\fE\u0002b\u0003sK1!a/S\u0005A\u0019VM]5bYZ+'o]5p]VKE)A\u0003wC2,XMH\u0001\u0004\u0005\u0011qu\u000eZ3\u0016\t\u0005\u0015\u00171Z\n\u0004\u0001\u0006-\u0012\u0001B0lKf\u00042\u0001XAf\t\u0019\ti\r\u0011b\u0001?\n\t1*A\u0003`Q\u0006\u001c\b.A\u0003`]\u0016DH\u000fE\u0003\u0002$\u0002\u000bI\r\u0006\u0005\u0002T\u0006]\u0017\u0011\\An\u0011\u001d\t9\r\u0012a\u0001\u0003\u0013Dq!a4E\u0001\u0004\t\t\u0001C\u0004\u0002R\u0012\u0003\r!a5\u0002\u0007-,\u00170\u0006\u0002\u0002J\u0006!\u0001.Y:i\u0003\u0011qW\r\u001f;\u0016\u0005\u0005M\u0017\u0001\u00038fqR|F%Z9\u0015\t\u0005-\u0018\u0011\u001f\t\u0004C\u00065\u0018bAAx%\n!QK\\5u\u0011\u001d\t\u0019\u0010\u0013a\u0001\u0003'\f\u0011A\\\u0001\tM&tGMT8eKR1\u00111[A}\u0003{Dq!a?J\u0001\u0004\tI-A\u0001l\u0011\u001d\ty0\u0013a\u0001\u0003\u0003\t\u0011\u0001\u001b\u0015\u0004\u0013\n\r\u0001\u0003\u0002B\u0003\u0005\u0017i!Aa\u0002\u000b\u0007\t%!+\u0001\u0006b]:|G/\u0019;j_:LAA!\u0004\u0003\b\t9A/Y5me\u0016\u001c\u0017a\u00024pe\u0016\f7\r[\u000b\u0005\u0005'\u0011\t\u0003\u0006\u0003\u0002l\nU\u0001b\u0002B\f\u0015\u0002\u0007!\u0011D\u0001\u0002MB9\u0011Ma\u0007\u0002J\n}\u0011b\u0001B\u000f%\nIa)\u001e8di&|g.\r\t\u00049\n\u0005BA\u0002B\u0012\u0015\n\u0007qLA\u0001VQ\rQ%1A\u0001\ti>\u001cFO]5oOR\u0011!1\u0006\t\u0005\u0005[\u00119$\u0004\u0002\u00030)!!\u0011\u0007B\u001a\u0003\u0011a\u0017M\\4\u000b\u0005\tU\u0012\u0001\u00026bm\u0006LAA!\u000f\u00030\t11\u000b\u001e:j]\u001e\fAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"Aa\u0010\u0011\t\t5\"\u0011I\u0005\u0005\u0005\u0007\u0012yC\u0001\u0004PE*,7\r\u001e\u0015\ba\u0005]\u0016QXA`Q\u001dy\u0013qWA_\u0003\u007f\u000b\u0011\u0002\u001e5sKNDw\u000e\u001c3\u0002\u0017\r|g\u000e^3oiNK'0Z\u0001\u0005g&TX-A\u0007v]&l\u0007O]8wK\"\u000b7\u000f\u001b\u000b\u0005\u0003\u0003\u0011\u0019\u0006C\u0004\u0003V%\u0001\r!!\u0001\u0002\u0019%l\u0007O]8wK\u0012D\u0015m\u001d5)\u0007%\u0011I\u0006E\u0002b\u00057J1A!\u0018S\u0005\u0019Ig\u000e\\5oK\u0006i3oY1mC\u0012\u001aw\u000e\u001c7fGRLwN\u001c\u0013nkR\f'\r\\3%\u0011\u0006\u001c\bnU3uI\u0011JW\u000e\u001d:pm\u0016D\u0015m\u001d5\u0015\t\u0005\u0005!1\r\u0005\b\u0005KR\u0001\u0019AA\u0001\u00031y'/[4j]\u0006d\u0007*Y:i\u00035\u001a8-\u00197bI\r|G\u000e\\3di&|g\u000eJ7vi\u0006\u0014G.\u001a\u0013ICND7+\u001a;%I\r|W\u000e];uK\"\u000b7\u000f\u001b\u000b\u0005\u0003\u0003\u0011Y\u0007\u0003\u0004\u0003n-\u0001\raW\u0001\u0002_\"\u001a1B!\u0017\u0002OM\u001c\u0017\r\\1%G>dG.Z2uS>tG%\\;uC\ndW\r\n%bg\"\u001cV\r\u001e\u0013%S:$W\r\u001f\u000b\u0005\u0003\u0003\u0011)\bC\u0004\u0002b2\u0001\r!!\u0001)\u00071\u0011I&\u0001\u0005d_:$\u0018-\u001b8t)\u0011\u0011iHa!\u0011\u0007\u0005\u0014y(C\u0002\u0003\u0002J\u0013qAQ8pY\u0016\fg\u000e\u0003\u0004\u0003\u00066\u0001\raW\u0001\u0005K2,W\u000e\u0006\u0003\u0002\"\t%\u0005B\u0002BC\u001d\u0001\u00071\fK\u0002\u000f\u00053\n\u0001b]5{K\"Kg\u000e\u001e\u000b\u0005\u0003W\u0014\t\nC\u0004\u0003N=\u0001\r!!\u0001\u0002\u0007\u0005$G\r\u0006\u0003\u0003~\t]\u0005B\u0002BC!\u0001\u00071,\u0001\u0004bI\u0012\fE\u000e\u001c\u000b\u0005\u0005;\u0013y*D\u0001\u0001\u0011\u001d\u0011\t+\u0005a\u0001\u0005G\u000b!\u0001_:\u0011\t5\fieW\u0001\fgV\u0014GO]1di\u0006cG\u000e\u0006\u0003\u0003\u001e\n%\u0006b\u0002BQ%\u0001\u0007!1U\u0001\bC\u0012$W\t\\3n)\u0019\u0011iHa,\u00032\"1!QQ\nA\u0002mCq!!9\u0014\u0001\u0004\t\t!\u0001\u0004sK6|g/\u001a\u000b\u0007\u0005{\u00129L!/\t\r\t\u0015E\u00031\u0001\\\u0011\u001d\t\t\u000f\u0006a\u0001\u0003\u0003!BA! \u0003>\"1!QQ\u000bA\u0002m\u0013q\u0002S1tQN+G/\u0013;fe\u0006$xN]\u000b\u0005\u0005\u0007\u0014imE\u0002\u0017\u0005\u000b\u0004R!\u001cBd\u0005\u0017L1A!3Q\u0005A\t%m\u001d;sC\u000e$\u0018\n^3sCR|'\u000fE\u0002]\u0005\u001b$a!!\u0012\u0017\u0005\u0004yFC\u0001Bi!\u0015\u0011iJ\u0006Bf\u0003\u0005I\u0017\u0001\u00028pI\u0016\f1\u0001\\3o\u0003\u001d)\u0007\u0010\u001e:bGR$BAa3\u0003^\"9!q\\\u000eA\u0002\u0005\u0005\u0012A\u00018e\u0003\u001dA\u0017m\u001d(fqR,\"A! \u0015\u0005\t-\u0017\u0001C5uKJ\fGo\u001c:\u0016\u0005\t-\b\u0003B7\u0003nnK1Aa<Q\u0005!IE/\u001a:bi>\u0014\u0018\u0001\u00048pI\u0016LE/\u001a:bi>\u0014XC\u0001B{!\u0015i'Q^A\u0011\u0003\u001d\u0019H/\u001a9qKJ,BAa?\u0004\u0006Q!!Q`B\u0016%\u0019\u0011ypa\u0001\u0004\u001a\u001911\u0011\u0001\u0001\u0001\u0005{\u0014A\u0002\u0010:fM&tW-\\3oiz\u00022\u0001XB\u0003\t\u001d\u00199\u0001\tb\u0001\u0007\u0013\u0011\u0011aU\t\u0004A\u000e-\u0001\u0007BB\u0007\u0007+\u0001R!\\B\b\u0007'I1a!\u0005Q\u0005\u001d\u0019F/\u001a9qKJ\u00042\u0001XB\u000b\t-\u00199b!\u0002\u0002\u0002\u0003\u0005)\u0011A0\u0003\u0007}#\u0013\u0007\u0005\u0003\u0004\u001c\r\u0015b\u0002BB\u000f\u0007Cq1!^B\u0010\u0013\t\t&+C\u0002\u0004$A\u000bqa\u0015;faB,'/\u0003\u0003\u0004(\r%\"AD#gM&\u001c\u0017.\u001a8u'Bd\u0017\u000e\u001e\u0006\u0004\u0007G\u0001\u0006bBB\u0017A\u0001\u000f1qF\u0001\u0006g\"\f\u0007/\u001a\t\u0007[\u000eE2la\u0001\n\u0007\rM\u0002K\u0001\u0007Ti\u0016\u0004\b/\u001a:TQ\u0006\u0004X-A\u0005he><H+\u00192mKR!\u00111^B\u001d\u0011\u001d\u0019Y$\ta\u0001\u0003\u0003\taA\\3xY\u0016t\u0017!\u00044jYR,'/\u00138QY\u0006\u001cW\r\u0006\u0003\u0003\u001e\u000e\u0005\u0003bBB\"E\u0001\u00071QI\u0001\u0002aB1\u0011Ma\u0007\\\u0005{\nA\u0002^1cY\u0016\u001c\u0016N_3G_J$B!!\u0001\u0004L!91QJ\u0012A\u0002\u0005\u0005\u0011\u0001C2ba\u0006\u001c\u0017\u000e^=\u0002\u00199,w\u000f\u00165sKNDw\u000e\u001c3\u0015\t\u0005\u000511\u000b\u0005\b\u0005\u001b\"\u0003\u0019AA\u0001\u0003\u0015\u0019G.Z1s)\t\tY/A\bji\u0016\u0014\u0018M\u00197f\r\u0006\u001cGo\u001c:z+\t\t\t$\u0001\u0004bI\u0012|e.\u001a\u000b\u0005\u0005;\u001b\t\u0007\u0003\u0004\u0003\u0006\u001e\u0002\ra\u0017\u0015\u0004O\te\u0013aC:vER\u0014\u0018m\u0019;P]\u0016$BA!(\u0004j!1!Q\u0011\u0015A\u0002mC3\u0001\u000bB-\u0003%Ygn\\<o'&TX-A\u0004jg\u0016k\u0007\u000f^=\u0016\t\rM41\u0010\u000b\u0005\u0003W\u001c)\bC\u0004\u0003\u0018-\u0002\raa\u001e\u0011\r\u0005\u0014YbWB=!\ra61\u0010\u0003\u0007\u0005GY#\u0019A0\u0015\u0005\u0005-\u0012!C2mCN\u001ch*Y7f+\t\u0011Y#\u0001\u0005iCND7i\u001c3f)\t\t\t\u0001"
)
public final class HashSet extends AbstractSet implements StrictOptimizedIterableOps, Serializable {
   private final double loadFactor;
   public Node[] scala$collection$mutable$HashSet$$table;
   private int threshold;
   private int contentSize;

   public static int defaultInitialCapacity() {
      HashSet$ var10000 = HashSet$.MODULE$;
      return 16;
   }

   public static double defaultLoadFactor() {
      HashSet$ var10000 = HashSet$.MODULE$;
      return (double)0.75F;
   }

   public static Builder newBuilder(final int initialCapacity, final double loadFactor) {
      HashSet$ var10000 = HashSet$.MODULE$;
      return new GrowableBuilder(initialCapacity, loadFactor) {
         public void sizeHint(final int size) {
            ((HashSet)this.elems()).sizeHint(size);
         }
      };
   }

   public static Builder newBuilder() {
      return HashSet$.MODULE$.newBuilder();
   }

   public static HashSet from(final IterableOnce it) {
      return HashSet$.MODULE$.from(it);
   }

   public static Object tabulate(final int n1, final int n2, final int n3, final int n4, final int n5, final Function5 f) {
      HashSet$ tabulate_this = HashSet$.MODULE$;
      Function1 tabulate_f = IterableFactory::$anonfun$tabulate$7$adapted;
      IterableOnce from_source = new View.Tabulate(n1, tabulate_f);
      return tabulate_this.from(from_source);
   }

   public static Object tabulate(final int n1, final int n2, final int n3, final int n4, final Function4 f) {
      HashSet$ tabulate_this = HashSet$.MODULE$;
      Function1 tabulate_f = IterableFactory::$anonfun$tabulate$5$adapted;
      IterableOnce from_source = new View.Tabulate(n1, tabulate_f);
      return tabulate_this.from(from_source);
   }

   public static Object tabulate(final int n1, final int n2, final int n3, final Function3 f) {
      HashSet$ tabulate_this = HashSet$.MODULE$;
      Function1 tabulate_f = IterableFactory::$anonfun$tabulate$3$adapted;
      IterableOnce from_source = new View.Tabulate(n1, tabulate_f);
      return tabulate_this.from(from_source);
   }

   public static Object tabulate(final int n1, final int n2, final Function2 f) {
      HashSet$ tabulate_this = HashSet$.MODULE$;
      Function1 tabulate_f = IterableFactory::$anonfun$tabulate$1$adapted;
      IterableOnce from_source = new View.Tabulate(n1, tabulate_f);
      return tabulate_this.from(from_source);
   }

   public static Object tabulate(final int n, final Function1 f) {
      HashSet$ tabulate_this = HashSet$.MODULE$;
      IterableOnce from_source = new View.Tabulate(n, f);
      return tabulate_this.from(from_source);
   }

   public static Object fill(final int n1, final int n2, final int n3, final int n4, final int n5, final Function0 elem) {
      HashSet$ fill_this = HashSet$.MODULE$;
      Function0 fill_elem = IterableFactory::$anonfun$fill$4;
      IterableOnce from_source = new View.Fill(n1, fill_elem);
      return fill_this.from(from_source);
   }

   public static Object fill(final int n1, final int n2, final int n3, final int n4, final Function0 elem) {
      HashSet$ fill_this = HashSet$.MODULE$;
      Function0 fill_elem = IterableFactory::$anonfun$fill$3;
      IterableOnce from_source = new View.Fill(n1, fill_elem);
      return fill_this.from(from_source);
   }

   public static Object fill(final int n1, final int n2, final int n3, final Function0 elem) {
      HashSet$ fill_this = HashSet$.MODULE$;
      Function0 fill_elem = IterableFactory::$anonfun$fill$2;
      IterableOnce from_source = new View.Fill(n1, fill_elem);
      return fill_this.from(from_source);
   }

   public static Object fill(final int n1, final int n2, final Function0 elem) {
      HashSet$ fill_this = HashSet$.MODULE$;
      Function0 fill_elem = IterableFactory::$anonfun$fill$1;
      IterableOnce from_source = new View.Fill(n1, fill_elem);
      return fill_this.from(from_source);
   }

   public static Object fill(final int n, final Function0 elem) {
      HashSet$ fill_this = HashSet$.MODULE$;
      IterableOnce from_source = new View.Fill(n, elem);
      return fill_this.from(from_source);
   }

   public static Object range(final Object start, final Object end, final Object step, final Integral evidence$4) {
      return IterableFactory.range$(HashSet$.MODULE$, start, end, step, evidence$4);
   }

   public static Object range(final Object start, final Object end, final Integral evidence$3) {
      return IterableFactory.range$(HashSet$.MODULE$, start, end, evidence$3);
   }

   public static Object unfold(final Object init, final Function1 f) {
      HashSet$ unfold_this = HashSet$.MODULE$;
      IterableOnce from_source = new View.Unfold(init, f);
      return unfold_this.from(from_source);
   }

   public static Object iterate(final Object start, final int len, final Function1 f) {
      HashSet$ iterate_this = HashSet$.MODULE$;
      IterableOnce from_source = new View.Iterate(start, len, f);
      return iterate_this.from(from_source);
   }

   public Tuple2 partition(final Function1 p) {
      return StrictOptimizedIterableOps.partition$(this, p);
   }

   public Tuple2 span(final Function1 p) {
      return StrictOptimizedIterableOps.span$(this, p);
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

   public Object filterImpl(final Function1 pred, final boolean isFlipped) {
      return StrictOptimizedIterableOps.filterImpl$(this, pred, isFlipped);
   }

   public Tuple2 partitionMap(final Function1 f) {
      return StrictOptimizedIterableOps.partitionMap$(this, f);
   }

   public Object tapEach(final Function1 f) {
      return StrictOptimizedIterableOps.tapEach$(this, f);
   }

   public Object takeRight(final int n) {
      return StrictOptimizedIterableOps.takeRight$(this, n);
   }

   public Object dropRight(final int n) {
      return StrictOptimizedIterableOps.dropRight$(this, n);
   }

   public int size() {
      return this.contentSize;
   }

   public int unimproveHash(final int improvedHash) {
      return improvedHash ^ improvedHash >>> 16;
   }

   public int scala$collection$mutable$HashSet$$improveHash(final int originalHash) {
      return originalHash ^ originalHash >>> 16;
   }

   public int scala$collection$mutable$HashSet$$computeHash(final Object o) {
      int scala$collection$mutable$HashSet$$improveHash_originalHash = Statics.anyHash(o);
      return scala$collection$mutable$HashSet$$improveHash_originalHash ^ scala$collection$mutable$HashSet$$improveHash_originalHash >>> 16;
   }

   public int scala$collection$mutable$HashSet$$index(final int hash) {
      return hash & this.scala$collection$mutable$HashSet$$table.length - 1;
   }

   public boolean contains(final Object elem) {
      int findNode_scala$collection$mutable$HashSet$$computeHash_scala$collection$mutable$HashSet$$improveHash_originalHash = Statics.anyHash(elem);
      int findNode_hash = findNode_scala$collection$mutable$HashSet$$computeHash_scala$collection$mutable$HashSet$$improveHash_originalHash ^ findNode_scala$collection$mutable$HashSet$$computeHash_scala$collection$mutable$HashSet$$improveHash_originalHash >>> 16;
      Node var3 = this.scala$collection$mutable$HashSet$$table[findNode_hash & this.scala$collection$mutable$HashSet$$table.length - 1];
      Node var10000 = var3 == null ? null : var3.findNode(elem, findNode_hash);
      Object var5 = null;
      return var10000 != null;
   }

   private Node findNode(final Object elem) {
      int scala$collection$mutable$HashSet$$computeHash_scala$collection$mutable$HashSet$$improveHash_originalHash = Statics.anyHash(elem);
      int hash = scala$collection$mutable$HashSet$$computeHash_scala$collection$mutable$HashSet$$improveHash_originalHash ^ scala$collection$mutable$HashSet$$computeHash_scala$collection$mutable$HashSet$$improveHash_originalHash >>> 16;
      Node var3 = this.scala$collection$mutable$HashSet$$table[hash & this.scala$collection$mutable$HashSet$$table.length - 1];
      return var3 == null ? null : var3.findNode(elem, hash);
   }

   public void sizeHint(final int size) {
      int target = this.tableSizeFor((int)((double)(size + 1) / this.loadFactor));
      if (target > this.scala$collection$mutable$HashSet$$table.length) {
         this.growTable(target);
      }
   }

   public boolean add(final Object elem) {
      if (this.contentSize + 1 >= this.threshold) {
         this.growTable(this.scala$collection$mutable$HashSet$$table.length * 2);
      }

      int scala$collection$mutable$HashSet$$computeHash_scala$collection$mutable$HashSet$$improveHash_originalHash = Statics.anyHash(elem);
      return this.addElem(elem, scala$collection$mutable$HashSet$$computeHash_scala$collection$mutable$HashSet$$improveHash_originalHash ^ scala$collection$mutable$HashSet$$computeHash_scala$collection$mutable$HashSet$$improveHash_originalHash >>> 16);
   }

   public HashSet addAll(final IterableOnce xs) {
      int sizeHint_delta = 0;
      Builder.sizeHint$(this, xs, sizeHint_delta);
      if (xs instanceof scala.collection.immutable.HashSet) {
         scala.collection.immutable.HashSet var2 = (scala.collection.immutable.HashSet)xs;
         Function2 foreachWithHash_f = (k, h) -> {
            $anonfun$addAll$1(this, k, BoxesRunTime.unboxToInt(h));
            return BoxedUnit.UNIT;
         };
         if (var2 == null) {
            throw null;
         } else {
            BitmapIndexedSetNode var10000 = var2.rootNode();
            if (var10000 == null) {
               throw null;
            } else {
               BitmapIndexedSetNode foreachWithHash_foreachWithHash_this = var10000;
               int foreachWithHash_foreachWithHash_iN = foreachWithHash_foreachWithHash_this.payloadArity();

               for(int foreachWithHash_foreachWithHash_i = 0; foreachWithHash_foreachWithHash_i < foreachWithHash_foreachWithHash_iN; ++foreachWithHash_foreachWithHash_i) {
                  var10000 = (BitmapIndexedSetNode)foreachWithHash_foreachWithHash_this.content()[foreachWithHash_foreachWithHash_i];
                  int foreachWithHash_foreachWithHash_boxToInteger_i = foreachWithHash_foreachWithHash_this.originalHashes()[foreachWithHash_foreachWithHash_i];
                  Object var16 = var10000;
                  $anonfun$addAll$1(this, var16, foreachWithHash_foreachWithHash_boxToInteger_i);
               }

               int foreachWithHash_foreachWithHash_jN = foreachWithHash_foreachWithHash_this.nodeArity();

               for(int foreachWithHash_foreachWithHash_j = 0; foreachWithHash_foreachWithHash_j < foreachWithHash_foreachWithHash_jN; ++foreachWithHash_foreachWithHash_j) {
                  foreachWithHash_foreachWithHash_this.getNode(foreachWithHash_foreachWithHash_j).foreachWithHash(foreachWithHash_f);
               }

               return this;
            }
         }
      } else if (xs instanceof HashSet) {
         HashSet var3 = (HashSet)xs;
         if (var3 == null) {
            throw null;
         } else {
            Iterator iter = var3.new HashSetIterator() {
               public Node extract(final Node nd) {
                  return nd;
               }
            };

            while(iter.hasNext()) {
               Node next = (Node)iter.next();
               this.addElem(next.key(), next.hash());
            }

            return this;
         }
      } else if (!(xs instanceof LinkedHashSet)) {
         return (HashSet)Growable.addAll$(this, xs);
      } else {
         Iterator iter = ((LinkedHashSet)xs).entryIterator();

         while(iter.hasNext()) {
            LinkedHashSet.Entry next = (LinkedHashSet.Entry)iter.next();
            this.addElem(next.key(), next.hash());
         }

         return this;
      }
   }

   public HashSet subtractAll(final IterableOnce xs) {
      if (this.size() == 0) {
         return this;
      } else if (!(xs instanceof scala.collection.immutable.HashSet)) {
         if (xs instanceof HashSet) {
            HashSet var3 = (HashSet)xs;
            if (var3 == null) {
               throw null;
            } else {
               Iterator iter = var3.new HashSetIterator() {
                  public Node extract(final Node nd) {
                     return nd;
                  }
               };

               while(iter.hasNext()) {
                  Node next = (Node)iter.next();
                  this.remove(next.key(), next.hash());
                  if (this.size() == 0) {
                     return this;
                  }
               }

               return this;
            }
         } else if (xs instanceof LinkedHashSet) {
            Iterator iter = ((LinkedHashSet)xs).entryIterator();

            while(iter.hasNext()) {
               LinkedHashSet.Entry next = (LinkedHashSet.Entry)iter.next();
               this.remove(next.key(), next.hash());
               if (this.size() == 0) {
                  return this;
               }
            }

            return this;
         } else {
            return (HashSet)Shrinkable.subtractAll$(this, xs);
         }
      } else {
         scala.collection.immutable.HashSet var2 = (scala.collection.immutable.HashSet)xs;
         Function2 foreachWithHashWhile_f = (k, h) -> BoxesRunTime.boxToBoolean($anonfun$subtractAll$1(this, k, BoxesRunTime.unboxToInt(h)));
         if (var2 == null) {
            throw null;
         } else {
            BitmapIndexedSetNode var10000 = var2.rootNode();
            if (var10000 == null) {
               throw null;
            } else {
               BitmapIndexedSetNode foreachWithHashWhile_foreachWithHashWhile_this = var10000;
               int foreachWithHashWhile_foreachWithHashWhile_thisPayloadArity = foreachWithHashWhile_foreachWithHashWhile_this.payloadArity();
               boolean foreachWithHashWhile_foreachWithHashWhile_pass = true;

               for(int foreachWithHashWhile_foreachWithHashWhile_i = 0; foreachWithHashWhile_foreachWithHashWhile_i < foreachWithHashWhile_foreachWithHashWhile_thisPayloadArity && foreachWithHashWhile_foreachWithHashWhile_pass; ++foreachWithHashWhile_foreachWithHashWhile_i) {
                  label87: {
                     if (foreachWithHashWhile_foreachWithHashWhile_pass) {
                        var10000 = (BitmapIndexedSetNode)foreachWithHashWhile_foreachWithHashWhile_this.content()[foreachWithHashWhile_foreachWithHashWhile_i];
                        int foreachWithHashWhile_foreachWithHashWhile_boxToInteger_i = foreachWithHashWhile_foreachWithHashWhile_this.originalHashes()[foreachWithHashWhile_foreachWithHashWhile_i];
                        Object var16 = var10000;
                        if ($anonfun$subtractAll$1(this, var16, foreachWithHashWhile_foreachWithHashWhile_boxToInteger_i)) {
                           var18 = true;
                           break label87;
                        }
                     }

                     var18 = false;
                  }

                  foreachWithHashWhile_foreachWithHashWhile_pass = var18;
               }

               int foreachWithHashWhile_foreachWithHashWhile_thisNodeArity = foreachWithHashWhile_foreachWithHashWhile_this.nodeArity();

               for(int foreachWithHashWhile_foreachWithHashWhile_j = 0; foreachWithHashWhile_foreachWithHashWhile_j < foreachWithHashWhile_foreachWithHashWhile_thisNodeArity && foreachWithHashWhile_foreachWithHashWhile_pass; ++foreachWithHashWhile_foreachWithHashWhile_j) {
                  foreachWithHashWhile_foreachWithHashWhile_pass = foreachWithHashWhile_foreachWithHashWhile_pass && foreachWithHashWhile_foreachWithHashWhile_this.getNode(foreachWithHashWhile_foreachWithHashWhile_j).foreachWithHashWhile(foreachWithHashWhile_f);
               }

               return this;
            }
         }
      }
   }

   private boolean addElem(final Object elem, final int hash) {
      int idx = hash & this.scala$collection$mutable$HashSet$$table.length - 1;
      Node var4 = this.scala$collection$mutable$HashSet$$table[idx];
      if (var4 == null) {
         this.scala$collection$mutable$HashSet$$table[idx] = new Node(elem, hash, (Node)null);
      } else {
         Node prev = null;

         for(Node n = var4; n != null && n.hash() <= hash; n = n.next()) {
            if (n.hash() == hash && BoxesRunTime.equals(elem, n.key())) {
               return false;
            }

            prev = n;
         }

         if (prev == null) {
            this.scala$collection$mutable$HashSet$$table[idx] = new Node(elem, hash, var4);
         } else {
            prev.next_$eq(new Node(elem, hash, prev.next()));
         }
      }

      ++this.contentSize;
      return true;
   }

   private boolean remove(final Object elem, final int hash) {
      int idx = hash & this.scala$collection$mutable$HashSet$$table.length - 1;
      Node var4 = this.scala$collection$mutable$HashSet$$table[idx];
      if (var4 == null) {
         return false;
      } else if (var4.hash() == hash && BoxesRunTime.equals(var4.key(), elem)) {
         this.scala$collection$mutable$HashSet$$table[idx] = var4.next();
         --this.contentSize;
         return true;
      } else {
         Node prev = var4;

         for(Node next = var4.next(); next != null && next.hash() <= hash; next = next.next()) {
            if (next.hash() == hash && BoxesRunTime.equals(next.key(), elem)) {
               prev.next_$eq(next.next());
               --this.contentSize;
               return true;
            }

            prev = next;
         }

         return false;
      }
   }

   public boolean remove(final Object elem) {
      int scala$collection$mutable$HashSet$$computeHash_scala$collection$mutable$HashSet$$improveHash_originalHash = Statics.anyHash(elem);
      return this.remove(elem, scala$collection$mutable$HashSet$$computeHash_scala$collection$mutable$HashSet$$improveHash_originalHash ^ scala$collection$mutable$HashSet$$computeHash_scala$collection$mutable$HashSet$$improveHash_originalHash >>> 16);
   }

   public Iterator iterator() {
      return new HashSetIterator() {
         public Object extract(final Node nd) {
            return nd.key();
         }
      };
   }

   public Iterator nodeIterator() {
      return new HashSetIterator() {
         public Node extract(final Node nd) {
            return nd;
         }
      };
   }

   public Stepper stepper(final StepperShape shape) {
      int var2 = shape.shape();
      if (StepperShape$.MODULE$.IntShape() == var2) {
         return new IntTableStepper(this.size(), this.scala$collection$mutable$HashSet$$table, (x$1) -> x$1.next(), (x$2) -> BoxesRunTime.boxToInteger($anonfun$stepper$2(x$2)), 0, this.scala$collection$mutable$HashSet$$table.length);
      } else if (StepperShape$.MODULE$.LongShape() == var2) {
         return new LongTableStepper(this.size(), this.scala$collection$mutable$HashSet$$table, (x$3) -> x$3.next(), (x$4) -> BoxesRunTime.boxToLong($anonfun$stepper$4(x$4)), 0, this.scala$collection$mutable$HashSet$$table.length);
      } else {
         return (Stepper)(StepperShape$.MODULE$.DoubleShape() == var2 ? new DoubleTableStepper(this.size(), this.scala$collection$mutable$HashSet$$table, (x$5) -> x$5.next(), (x$6) -> BoxesRunTime.boxToDouble($anonfun$stepper$6(x$6)), 0, this.scala$collection$mutable$HashSet$$table.length) : shape.parUnbox(new AnyTableStepper(this.size(), this.scala$collection$mutable$HashSet$$table, (x$7) -> x$7.next(), (x$8) -> x$8.key(), 0, this.scala$collection$mutable$HashSet$$table.length)));
      }
   }

   private void growTable(final int newlen) {
      int oldlen = this.scala$collection$mutable$HashSet$$table.length;
      this.threshold = this.newThreshold(newlen);
      if (this.size() == 0) {
         this.scala$collection$mutable$HashSet$$table = new Node[newlen];
      } else {
         this.scala$collection$mutable$HashSet$$table = (Node[])Arrays.copyOf(this.scala$collection$mutable$HashSet$$table, newlen);
         Node preLow = new Node((Object)null, 0, (Node)null);

         for(Node preHigh = new Node((Object)null, 0, (Node)null); oldlen < newlen; oldlen *= 2) {
            for(int i = 0; i < oldlen; ++i) {
               Node old = this.scala$collection$mutable$HashSet$$table[i];
               if (old != null) {
                  preLow.next_$eq((Node)null);
                  preHigh.next_$eq((Node)null);
                  Node lastLow = preLow;
                  Node lastHigh = preHigh;

                  Node next;
                  for(Node n = old; n != null; n = next) {
                     next = n.next();
                     if ((n.hash() & oldlen) == 0) {
                        lastLow.next_$eq(n);
                        lastLow = n;
                     } else {
                        lastHigh.next_$eq(n);
                        lastHigh = n;
                     }
                  }

                  lastLow.next_$eq((Node)null);
                  if (old != preLow.next()) {
                     this.scala$collection$mutable$HashSet$$table[i] = preLow.next();
                  }

                  if (preHigh.next() != null) {
                     this.scala$collection$mutable$HashSet$$table[i + oldlen] = preHigh.next();
                     lastHigh.next_$eq((Node)null);
                  }
               }
            }
         }

      }
   }

   public HashSet filterInPlace(final Function1 p) {
      if (IterableOnceOps.nonEmpty$(this)) {
         for(int bucket = 0; bucket < this.scala$collection$mutable$HashSet$$table.length; ++bucket) {
            Node head;
            for(head = this.scala$collection$mutable$HashSet$$table[bucket]; head != null && !BoxesRunTime.unboxToBoolean(p.apply(head.key())); --this.contentSize) {
               head = head.next();
            }

            if (head != null) {
               Node prev = head;

               for(Node next = head.next(); next != null; next = next.next()) {
                  if (BoxesRunTime.unboxToBoolean(p.apply(next.key()))) {
                     prev = next;
                  } else {
                     prev.next_$eq(next.next());
                     --this.contentSize;
                  }
               }
            }

            this.scala$collection$mutable$HashSet$$table[bucket] = head;
         }
      }

      return this;
   }

   private int tableSizeFor(final int capacity) {
      RichInt$ var10000 = RichInt$.MODULE$;
      var10000 = RichInt$.MODULE$;
      int var2 = capacity - 1;
      int max$extension_that = 4;
      scala.math.package$ var7 = scala.math.package$.MODULE$;
      int var3 = Integer.highestOneBit(Math.max(var2, max$extension_that)) * 2;
      int min$extension_that = 1073741824;
      var7 = scala.math.package$.MODULE$;
      return Math.min(var3, min$extension_that);
   }

   private int newThreshold(final int size) {
      return (int)((double)size * this.loadFactor);
   }

   public void clear() {
      Arrays.fill(this.scala$collection$mutable$HashSet$$table, (Object)null);
      this.contentSize = 0;
   }

   public IterableFactory iterableFactory() {
      return HashSet$.MODULE$;
   }

   public HashSet addOne(final Object elem) {
      this.add(elem);
      return this;
   }

   public HashSet subtractOne(final Object elem) {
      this.remove(elem);
      return this;
   }

   public int knownSize() {
      return this.size();
   }

   public boolean isEmpty() {
      return this.size() == 0;
   }

   public void foreach(final Function1 f) {
      int len = this.scala$collection$mutable$HashSet$$table.length;

      for(int i = 0; i < len; ++i) {
         Node n = this.scala$collection$mutable$HashSet$$table[i];
         if (n != null) {
            n.foreach(f);
         }
      }

   }

   public Object writeReplace() {
      return new DefaultSerializationProxy(new DeserializationFactory(this.scala$collection$mutable$HashSet$$table.length, this.loadFactor), this);
   }

   public String className() {
      return "HashSet";
   }

   public int hashCode() {
      Iterator setIterator = new HashSetIterator() {
         public Object extract(final Node nd) {
            return nd.key();
         }
      };
      Iterator hashIterator = (Iterator)(setIterator.isEmpty() ? setIterator : new HashSetIterator() {
         private int hash;
         // $FF: synthetic field
         private final HashSet $outer;

         private int hash() {
            return this.hash;
         }

         private void hash_$eq(final int x$1) {
            this.hash = x$1;
         }

         public int hashCode() {
            return this.hash();
         }

         public Object extract(final Node nd) {
            HashSet var10001 = this.$outer;
            int unimproveHash_improvedHash = nd.hash();
            if (var10001 == null) {
               throw null;
            } else {
               this.hash_$eq(unimproveHash_improvedHash ^ unimproveHash_improvedHash >>> 16);
               return this;
            }
         }

         public {
            if (HashSet.this == null) {
               throw null;
            } else {
               this.$outer = HashSet.this;
               this.hash = 0;
            }
         }
      });
      return MurmurHash3$.MODULE$.unorderedHash(hashIterator, MurmurHash3$.MODULE$.setSeed());
   }

   // $FF: synthetic method
   public static final void $anonfun$addAll$1(final HashSet $this, final Object k, final int h) {
      $this.addElem(k, h ^ h >>> 16);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$subtractAll$1(final HashSet $this, final Object k, final int h) {
      $this.remove(k, h ^ h >>> 16);
      return $this.size() > 0;
   }

   // $FF: synthetic method
   public static final int $anonfun$stepper$2(final Node x$2) {
      return BoxesRunTime.unboxToInt(x$2.key());
   }

   // $FF: synthetic method
   public static final long $anonfun$stepper$4(final Node x$4) {
      return BoxesRunTime.unboxToLong(x$4.key());
   }

   // $FF: synthetic method
   public static final double $anonfun$stepper$6(final Node x$6) {
      return BoxesRunTime.unboxToDouble(x$6.key());
   }

   public HashSet(final int initialCapacity, final double loadFactor) {
      this.loadFactor = loadFactor;
      this.scala$collection$mutable$HashSet$$table = new Node[this.tableSizeFor(initialCapacity)];
      this.threshold = this.newThreshold(this.scala$collection$mutable$HashSet$$table.length);
      this.contentSize = 0;
   }

   public HashSet() {
      HashSet$ var10001 = HashSet$.MODULE$;
      HashSet$ var10002 = HashSet$.MODULE$;
      this(16, (double)0.75F);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private abstract class HashSetIterator extends AbstractIterator {
      private int i;
      private Node node;
      private final int len;
      // $FF: synthetic field
      public final HashSet $outer;

      public abstract Object extract(final Node nd);

      public boolean hasNext() {
         if (this.node != null) {
            return true;
         } else {
            while(this.i < this.len) {
               Node n = this.scala$collection$mutable$HashSet$HashSetIterator$$$outer().scala$collection$mutable$HashSet$$table[this.i];
               ++this.i;
               if (n != null) {
                  this.node = n;
                  return true;
               }
            }

            return false;
         }
      }

      public Object next() {
         if (!this.hasNext()) {
            Iterator$ var10000 = Iterator$.MODULE$;
            return Iterator$.scala$collection$Iterator$$_empty.next();
         } else {
            Object r = this.extract(this.node);
            this.node = this.node.next();
            return r;
         }
      }

      // $FF: synthetic method
      public HashSet scala$collection$mutable$HashSet$HashSetIterator$$$outer() {
         return this.$outer;
      }

      public HashSetIterator() {
         if (HashSet.this == null) {
            throw null;
         } else {
            this.$outer = HashSet.this;
            super();
            this.i = 0;
            this.node = null;
            this.len = HashSet.this.scala$collection$mutable$HashSet$$table.length;
         }
      }
   }

   private static final class DeserializationFactory implements Factory, Serializable {
      private static final long serialVersionUID = 3L;
      private final int tableLength;
      private final double loadFactor;

      public int tableLength() {
         return this.tableLength;
      }

      public double loadFactor() {
         return this.loadFactor;
      }

      public HashSet fromSpecific(final IterableOnce it) {
         return (new HashSet(this.tableLength(), this.loadFactor())).addAll(it);
      }

      public Builder newBuilder() {
         HashSet$ var10000 = HashSet$.MODULE$;
         int var4 = this.tableLength();
         double newBuilder_loadFactor = this.loadFactor();
         int newBuilder_initialCapacity = var4;
         return new GrowableBuilder(newBuilder_initialCapacity, newBuilder_loadFactor) {
            public void sizeHint(final int size) {
               ((HashSet)this.elems()).sizeHint(size);
            }
         };
      }

      public DeserializationFactory(final int tableLength, final double loadFactor) {
         this.tableLength = tableLength;
         this.loadFactor = loadFactor;
      }
   }

   public static final class Node {
      private final Object _key;
      private final int _hash;
      private Node _next;

      public Object key() {
         return this._key;
      }

      public int hash() {
         return this._hash;
      }

      public Node next() {
         return this._next;
      }

      public void next_$eq(final Node n) {
         this._next = n;
      }

      public Node findNode(final Object k, final int h) {
         while(h != this._hash || !BoxesRunTime.equals(k, this._key)) {
            if (this._next == null || this._hash > h) {
               return null;
            }

            h = h;
            k = k;
            this = this._next;
         }

         return this;
      }

      public void foreach(final Function1 f) {
         while(true) {
            f.apply(this._key);
            if (this._next == null) {
               return;
            }

            f = f;
            this = this._next;
         }
      }

      public String toString() {
         return (new java.lang.StringBuilder(12)).append("Node(").append(this.key()).append(", ").append(this.hash()).append(") -> ").append(this.next()).toString();
      }

      public Node(final Object _key, final int _hash, final Node _next) {
         this._key = _key;
         this._hash = _hash;
         this._next = _next;
         super();
      }
   }
}
