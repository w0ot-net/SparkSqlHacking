package org.apache.spark.storage;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkException;
import org.apache.spark.errors.SparkCoreErrors$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.package$;
import org.apache.spark.network.buffer.ManagedBuffer;
import org.apache.spark.shuffle.ShuffleBlockInfo;
import org.apache.spark.util.ThreadUtils$;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.collection.IterableOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashSet;
import scala.math.Ordering.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0005e!B!C\u0001\tS\u0005\u0002C,\u0001\u0005\u0003\u0005\u000b\u0011B-\t\u0011u\u0003!\u0011!Q\u0001\nyCQA\u0019\u0001\u0005\u0002\rDqa\u001a\u0001C\u0002\u0013%\u0001\u000e\u0003\u0004p\u0001\u0001\u0006I!\u001b\u0005\ba\u0002\u0011\r\u0011\"\u0003r\u0011\u0019)\b\u0001)A\u0005e\"9a\u000f\u0001b\u0001\n\u00139\bbBA\u0001\u0001\u0001\u0006I\u0001\u001f\u0005\u000b\u0003\u0007\u0001\u0001\u0019!C\u0001\u0005\u0006\u0015\u0001BCA\u0007\u0001\u0001\u0007I\u0011\u0001\"\u0002\u0010!A\u00111\u0004\u0001!B\u0013\t9\u0001\u0003\u0006\u0002&\u0001\u0001\r\u0011\"\u0001C\u0003\u000bA!\"a\n\u0001\u0001\u0004%\tAQA\u0015\u0011!\ti\u0003\u0001Q!\n\u0005\u001d\u0001BCA\u0019\u0001\u0001\u0007I\u0011\u0001\"\u00024!Q\u00111\b\u0001A\u0002\u0013\u0005!)!\u0010\t\u0011\u0005\u0005\u0003\u0001)Q\u0005\u0003kA!\"!\u0012\u0001\u0001\u0004%\tAQA\u001a\u0011)\t9\u0005\u0001a\u0001\n\u0003\u0011\u0015\u0011\n\u0005\t\u0003\u001b\u0002\u0001\u0015)\u0003\u00026\u00191\u0011\u0011\u000b\u0001\u0005\u0003'B!\"!\u0019\u0017\u0005\u0003\u0005\u000b\u0011BA2\u0011\u0019\u0011g\u0003\"\u0001\u0002j!I\u0011\u0011\u000f\fA\u0002\u0013\u0005\u00111\u0007\u0005\n\u0003g2\u0002\u0019!C\u0001\u0003kB\u0001\"!\u001f\u0017A\u0003&\u0011Q\u0007\u0005\b\u0003{2B\u0011BA@\u0011\u001d\t)J\u0006C\u0005\u0003/Cq!a(\u0017\t\u0003\n\t\u000b\u0003\u0006\u0002$\u0002\u0011\r\u0011\"\u0001C\u0003KC\u0001\"a.\u0001A\u0003%\u0011q\u0015\u0005\u000b\u0003s\u0003!\u0019!C\u0001\u0005\u0006m\u0006\u0002CAi\u0001\u0001\u0006I!!0\t\u0015\u0005M\u0007A1A\u0005\u0002\t\u000b)\u000e\u0003\u0005\u0002`\u0002\u0001\u000b\u0011BAl\u0011%\t\t\u000f\u0001a\u0001\n\u0013\t\u0019\u0004C\u0005\u0002d\u0002\u0001\r\u0011\"\u0003\u0002f\"A\u0011\u0011\u001e\u0001!B\u0013\t)\u0004\u0003\u0006\u0002n\u0002\u0001\r\u0011\"\u0001C\u0003gA!\"a<\u0001\u0001\u0004%\tAQAy\u0011!\t)\u0010\u0001Q!\n\u0005U\u0002\"CA}\u0001\u0001\u0007I\u0011BA\u001a\u0011%\tY\u0010\u0001a\u0001\n\u0013\ti\u0010\u0003\u0005\u0003\u0002\u0001\u0001\u000b\u0015BA\u001b\u0011%\u0011)\u0001\u0001b\u0001\n\u0013\u00119\u0001\u0003\u0005\u0003\u0010\u0001\u0001\u000b\u0011\u0002B\u0005\u0011%\u0011\t\u0002\u0001b\u0001\n\u0013\u0011\u0019\u0002\u0003\u0005\u0003\u001e\u0001\u0001\u000b\u0011\u0002B\u000b\u0011%\u0011y\u0002\u0001b\u0001\n\u0013\u0011\t\u0003\u0003\u0005\u0003*\u0001\u0001\u000b\u0011\u0002B\u0012\u0011%\u0011y\u0003\u0001b\u0001\n\u0013\u0011\u0019\u0002\u0003\u0005\u00032\u0001\u0001\u000b\u0011\u0002B\u000b\u0011%\u0011\u0019\u0004\u0001b\u0001\n\u0013\u0011)\u0004\u0003\u0005\u0003<\u0001\u0001\u000b\u0011\u0002B\u001c\u0011%\u0011y\u0004\u0001b\u0001\n\u0013\u0011\u0019\u0002\u0003\u0005\u0003B\u0001\u0001\u000b\u0011\u0002B\u000b\u0011!\u0011\u0019\u0005\u0001C\u0001\u0005\n\u0015\u0003\u0002\u0003B$\u0001\u0011\u0005!)!)\t\u0011\t%\u0003\u0001\"\u0001C\u0005\u000bBqAa\u0013\u0001\t\u0013\u0011i\u0005C\u0004\u0003x\u0001!\t!!)\t\u000f\te\u0004\u0001\"\u0001\u0002\"\"A!1\u0010\u0001\u0005\u0002\t\u0013iH\u0001\u000eCY>\u001c7.T1oC\u001e,'\u000fR3d_6l\u0017n]:j_:,'O\u0003\u0002D\t\u000691\u000f^8sC\u001e,'BA#G\u0003\u0015\u0019\b/\u0019:l\u0015\t9\u0005*\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u0013\u0006\u0019qN]4\u0014\u0007\u0001Y\u0015\u000b\u0005\u0002M\u001f6\tQJC\u0001O\u0003\u0015\u00198-\u00197b\u0013\t\u0001VJ\u0001\u0004B]f\u0014VM\u001a\t\u0003%Vk\u0011a\u0015\u0006\u0003)\u0012\u000b\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003-N\u0013q\u0001T8hO&tw-\u0001\u0003d_:47\u0001\u0001\t\u00035nk\u0011\u0001R\u0005\u00039\u0012\u0013\u0011b\u00159be.\u001cuN\u001c4\u0002\u0005\tl\u0007CA0a\u001b\u0005\u0011\u0015BA1C\u00051\u0011En\\2l\u001b\u0006t\u0017mZ3s\u0003\u0019a\u0014N\\5u}Q\u0019A-\u001a4\u0011\u0005}\u0003\u0001\"B,\u0004\u0001\u0004I\u0006\"B/\u0004\u0001\u0004q\u0016a\u00044bY2\u0014\u0017mY6Ti>\u0014\u0018mZ3\u0016\u0003%\u00042\u0001\u00146m\u0013\tYWJ\u0001\u0004PaRLwN\u001c\t\u0003?6L!A\u001c\"\u0003\u001f\u0019\u000bG\u000e\u001c2bG.\u001cFo\u001c:bO\u0016\f\u0001CZ1mY\n\f7m[*u_J\fw-\u001a\u0011\u0002K5\f\u0007PU3qY&\u001c\u0017\r^5p]\u001a\u000b\u0017\u000e\\;sKN4uN\u001d#fG>lW.[:tS>tW#\u0001:\u0011\u00051\u001b\u0018B\u0001;N\u0005\rIe\u000e^\u0001'[\u0006D(+\u001a9mS\u000e\fG/[8o\r\u0006LG.\u001e:fg\u001a{'\u000fR3d_6l\u0017n]:j_:\u0004\u0013a\f2m_\u000e\\7+\u0019<fI>sG)Z2p[6L7o]5p]\u0016$'\t\\8dW6\u000bg.Y4fe\u0016C8-\u001a9uS>tW#\u0001=\u0011\u0005etX\"\u0001>\u000b\u0005md\u0018\u0001\u00027b]\u001eT\u0011!`\u0001\u0005U\u00064\u0018-\u0003\u0002\u0000u\n11\u000b\u001e:j]\u001e\f\u0001G\u00197pG.\u001c\u0016M^3e\u001f:$UmY8n[&\u001c8/[8oK\u0012\u0014En\\2l\u001b\u0006t\u0017mZ3s\u000bb\u001cW\r\u001d;j_:\u0004\u0013\u0001\u00067bgR\u0014F\tR'jOJ\fG/[8o)&lW-\u0006\u0002\u0002\bA\u0019A*!\u0003\n\u0007\u0005-QJ\u0001\u0003M_:<\u0017\u0001\u00077bgR\u0014F\tR'jOJ\fG/[8o)&lWm\u0018\u0013fcR!\u0011\u0011CA\f!\ra\u00151C\u0005\u0004\u0003+i%\u0001B+oSRD\u0011\"!\u0007\f\u0003\u0003\u0005\r!a\u0002\u0002\u0007a$\u0013'A\u000bmCN$(\u000b\u0012#NS\u001e\u0014\u0018\r^5p]RKW.\u001a\u0011)\u00071\ty\u0002E\u0002M\u0003CI1!a\tN\u0005!1x\u000e\\1uS2,\u0017\u0001\u00077bgR\u001c\u0006.\u001e4gY\u0016l\u0015n\u001a:bi&|g\u000eV5nK\u0006aB.Y:u'\",hM\u001a7f\u001b&<'/\u0019;j_:$\u0016.\\3`I\u0015\fH\u0003BA\t\u0003WA\u0011\"!\u0007\u000f\u0003\u0003\u0005\r!a\u0002\u000231\f7\u000f^*ik\u001a4G.Z'jOJ\fG/[8o)&lW\r\t\u0015\u0004\u001f\u0005}\u0011!\u0004:eI\ncwnY6t\u0019\u00164G/\u0006\u0002\u00026A\u0019A*a\u000e\n\u0007\u0005eRJA\u0004C_>dW-\u00198\u0002#I$GM\u00117pG.\u001cH*\u001a4u?\u0012*\u0017\u000f\u0006\u0003\u0002\u0012\u0005}\u0002\"CA\r#\u0005\u0005\t\u0019AA\u001b\u00039\u0011H\r\u001a\"m_\u000e\\7\u000fT3gi\u0002B3AEA\u0010\u0003E\u0019\b.\u001e4gY\u0016\u0014En\\2lg2+g\r^\u0001\u0016g\",hM\u001a7f\u00052|7m[:MK\u001a$x\fJ3r)\u0011\t\t\"a\u0013\t\u0013\u0005eA#!AA\u0002\u0005U\u0012AE:ik\u001a4G.\u001a\"m_\u000e\\7\u000fT3gi\u0002B3!FA\u0010\u0005a\u0019\u0006.\u001e4gY\u0016l\u0015n\u001a:bi&|gNU;o]\u0006\u0014G.Z\n\u0006-\u0005U\u00131\f\t\u0004s\u0006]\u0013bAA-u\n1qJ\u00196fGR\u00042!_A/\u0013\r\tyF\u001f\u0002\t%Vtg.\u00192mK\u0006!\u0001/Z3s!\ry\u0016QM\u0005\u0004\u0003O\u0012%A\u0004\"m_\u000e\\W*\u00198bO\u0016\u0014\u0018\n\u001a\u000b\u0005\u0003W\ny\u0007E\u0002\u0002nYi\u0011\u0001\u0001\u0005\b\u0003CB\u0002\u0019AA2\u0003-YW-\u001a9Sk:t\u0017N\\4\u0002\u001f-,W\r\u001d*v]:LgnZ0%KF$B!!\u0005\u0002x!I\u0011\u0011\u0004\u000e\u0002\u0002\u0003\u0007\u0011QG\u0001\rW\u0016,\u0007OU;o]&tw\r\t\u0015\u00047\u0005}\u0011AC1mY><(+\u001a;ssR1\u0011QGAA\u0003#Cq!a!\u001d\u0001\u0004\t))\u0001\u0007tQV4g\r\\3CY>\u001c7\u000e\u0005\u0003\u0002\b\u00065UBAAE\u0015\r\tY\tR\u0001\bg\",hM\u001a7f\u0013\u0011\ty)!#\u0003!MCWO\u001a4mK\ncwnY6J]\u001a|\u0007BBAJ9\u0001\u0007!/\u0001\u0006gC&dWO]3Ok6\f\u0011D\\3yiNCWO\u001a4mK\ncwnY6U_6KwM]1uKR\u0011\u0011\u0011\u0014\t\u0007\u0019\u0006m\u0015Q\u0011:\n\u0007\u0005uUJ\u0001\u0004UkBdWMM\u0001\u0004eVtGCAA\t\u0003Ei\u0017n\u001a:bi&twm\u00155vM\u001adWm]\u000b\u0003\u0003O\u0003b!!+\u00024\u0006\u0015UBAAV\u0015\u0011\ti+a,\u0002\u000f5,H/\u00192mK*\u0019\u0011\u0011W'\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u00026\u0006-&a\u0002%bg\"\u001cV\r^\u0001\u0013[&<'/\u0019;j]\u001e\u001c\u0006.\u001e4gY\u0016\u001c\b%A\nok6l\u0015n\u001a:bi\u0016$7\u000b[;gM2,7/\u0006\u0002\u0002>B!\u0011qXAg\u001b\t\t\tM\u0003\u0003\u0002D\u0006\u0015\u0017AB1u_6L7M\u0003\u0003\u0002H\u0006%\u0017AC2p]\u000e,(O]3oi*\u0019\u00111\u001a?\u0002\tU$\u0018\u000e\\\u0005\u0005\u0003\u001f\f\tMA\u0007Bi>l\u0017nY%oi\u0016<WM]\u0001\u0015]VlW*[4sCR,Gm\u00155vM\u001adWm\u001d\u0011\u0002#MDWO\u001a4mKN$v.T5he\u0006$X-\u0006\u0002\u0002XB1\u0011\u0011\\An\u00033k!!!2\n\t\u0005u\u0017Q\u0019\u0002\u0016\u0007>t7-\u001e:sK:$H*\u001b8lK\u0012\fV/Z;f\u0003I\u0019\b.\u001e4gY\u0016\u001cHk\\'jOJ\fG/\u001a\u0011\u0002\u000fM$x\u000e\u001d9fI\u0006Y1\u000f^8qa\u0016$w\fJ3r)\u0011\t\t\"a:\t\u0013\u0005ea%!AA\u0002\u0005U\u0012\u0001C:u_B\u0004X\r\u001a\u0011)\u0007\u001d\ny\"\u0001\u0006ti>\u0004\b/\u001a3S\t\u0012\u000bab\u001d;paB,GM\u0015#E?\u0012*\u0017\u000f\u0006\u0003\u0002\u0012\u0005M\b\"CA\rS\u0005\u0005\t\u0019AA\u001b\u0003-\u0019Ho\u001c9qK\u0012\u0014F\t\u0012\u0011)\u0007)\ny\"\u0001\bti>\u0004\b/\u001a3TQV4g\r\\3\u0002%M$x\u000e\u001d9fINCWO\u001a4mK~#S-\u001d\u000b\u0005\u0003#\ty\u0010C\u0005\u0002\u001a1\n\t\u00111\u0001\u00026\u0005y1\u000f^8qa\u0016$7\u000b[;gM2,\u0007\u0005K\u0002.\u0003?\ta\"\\5he\u0006$\u0018n\u001c8QK\u0016\u00148/\u0006\u0002\u0003\nAA\u0011\u0011\u0016B\u0006\u0003G\nY'\u0003\u0003\u0003\u000e\u0005-&a\u0002%bg\"l\u0015\r]\u0001\u0010[&<'/\u0019;j_:\u0004V-\u001a:tA\u0005I\"\u000f\u001a3CY>\u001c7.T5he\u0006$\u0018n\u001c8Fq\u0016\u001cW\u000f^8s+\t\u0011)\u0002\u0005\u0003MU\n]\u0001\u0003BAm\u00053IAAa\u0007\u0002F\n\u0011B\u000b\u001b:fC\u0012\u0004vn\u001c7Fq\u0016\u001cW\u000f^8s\u0003i\u0011H\r\u001a\"m_\u000e\\W*[4sCRLwN\\#yK\u000e,Ho\u001c:!\u0003e\u0011H\r\u001a\"m_\u000e\\W*[4sCRLwN\u001c*v]:\f'\r\\3\u0016\u0005\t\r\"C\u0002B\u0013\u0003+\nYF\u0002\u0004\u0003(M\u0002!1\u0005\u0002\ryI,g-\u001b8f[\u0016tGOP\u0001\u001be\u0012$'\t\\8dW6KwM]1uS>t'+\u001e8oC\ndW\r\t\u0005\u000b\u0005[\u0011)C1A\u0005\u0002\u0005\u0015\u0011!D:mK\u0016\u0004\u0018J\u001c;feZ\fG.\u0001\u0013tQV4g\r\\3CY>\u001c7.T5he\u0006$\u0018n\u001c8SK\u001a\u0014Xm\u001d5Fq\u0016\u001cW\u000f^8s\u0003\u0015\u001a\b.\u001e4gY\u0016\u0014En\\2l\u001b&<'/\u0019;j_:\u0014VM\u001a:fg\",\u00050Z2vi>\u0014\b%\u0001\u0013tQV4g\r\\3CY>\u001c7.T5he\u0006$\u0018n\u001c8SK\u001a\u0014Xm\u001d5Sk:t\u0017M\u00197f+\t\u00119D\u0005\u0004\u0003:\u0005U\u00131\f\u0004\u0007\u0005O9\u0004Aa\u000e\u0002KMDWO\u001a4mK\ncwnY6NS\u001e\u0014\u0018\r^5p]J+gM]3tQJ+hN\\1cY\u0016\u0004\u0003B\u0003B\u0017\u0005s\u0011\r\u0011\"\u0001\u0002\u0006\u0005!2\u000f[;gM2,W*[4sCRLwN\u001c)p_2\fQc\u001d5vM\u001adW-T5he\u0006$\u0018n\u001c8Q_>d\u0007%\u0001\u0010sK\u001a\u0014Xm\u001d5NS\u001e\u0014\u0018\r^1cY\u0016\u001c\u0006.\u001e4gY\u0016\u0014En\\2lgR\u0011\u0011QG\u0001\u001bgR|\u0007/T5he\u0006$\u0018N\\4TQV4g\r\\3CY>\u001c7n]\u0001\u001bI\u0016\u001cw.\\7jgNLwN\u001c*eI\u000e\u000b7\r[3CY>\u001c7n]\u0001\r[&<'/\u0019;f\u00052|7m\u001b\u000b\u0005\u0003k\u0011y\u0005C\u0004\u0003Ru\u0002\rAa\u0015\u0002!\tdwnY6U_J+\u0007\u000f\\5dCR,\u0007\u0003\u0002B+\u0005crAAa\u0016\u0003n9!!\u0011\fB6\u001d\u0011\u0011YF!\u001b\u000f\t\tu#q\r\b\u0005\u0005?\u0012)'\u0004\u0002\u0003b)\u0019!1\r-\u0002\rq\u0012xn\u001c;?\u0013\u0005I\u0015BA$I\u0013\t)e)\u0003\u0002D\t&\u0019!q\u000e\"\u0002)\tcwnY6NC:\fw-\u001a:NKN\u001c\u0018mZ3t\u0013\u0011\u0011\u0019H!\u001e\u0003\u001dI+\u0007\u000f\\5dCR,'\t\\8dW*\u0019!q\u000e\"\u0002\u000bM$\u0018M\u001d;\u0002\tM$x\u000e]\u0001\u0012Y\u0006\u001cH/T5he\u0006$\u0018n\u001c8J]\u001a|GC\u0001B@!\u001da\u00151TA\u0004\u0003k\u0001"
)
public class BlockManagerDecommissioner implements Logging {
   public final SparkConf org$apache$spark$storage$BlockManagerDecommissioner$$conf;
   public final BlockManager org$apache$spark$storage$BlockManagerDecommissioner$$bm;
   private final Option org$apache$spark$storage$BlockManagerDecommissioner$$fallbackStorage;
   private final int org$apache$spark$storage$BlockManagerDecommissioner$$maxReplicationFailuresForDecommission;
   private final String org$apache$spark$storage$BlockManagerDecommissioner$$blockSavedOnDecommissionedBlockManagerException;
   private volatile long lastRDDMigrationTime;
   private volatile long lastShuffleMigrationTime;
   private volatile boolean rddBlocksLeft;
   private volatile boolean shuffleBlocksLeft;
   private final HashSet migratingShuffles;
   private final AtomicInteger numMigratedShuffles;
   private final ConcurrentLinkedQueue shufflesToMigrate;
   private volatile boolean org$apache$spark$storage$BlockManagerDecommissioner$$stopped;
   private volatile boolean stoppedRDD;
   private volatile boolean org$apache$spark$storage$BlockManagerDecommissioner$$stoppedShuffle;
   private final HashMap migrationPeers;
   private final Option rddBlockMigrationExecutor;
   private final Runnable rddBlockMigrationRunnable;
   private final Option shuffleBlockMigrationRefreshExecutor;
   private final Runnable shuffleBlockMigrationRefreshRunnable;
   private final Option shuffleMigrationPool;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public String logName() {
      return Logging.logName$(this);
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final Map context, final Function0 body) {
      Logging.withLogContext$(this, context, body);
   }

   public void logInfo(final Function0 msg) {
      Logging.logInfo$(this, msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, msg, throwable);
   }

   public boolean isTraceEnabled() {
      return Logging.isTraceEnabled$(this);
   }

   public void initializeLogIfNecessary(final boolean isInterpreter) {
      Logging.initializeLogIfNecessary$(this, isInterpreter);
   }

   public boolean initializeLogIfNecessary(final boolean isInterpreter, final boolean silent) {
      return Logging.initializeLogIfNecessary$(this, isInterpreter, silent);
   }

   public boolean initializeLogIfNecessary$default$2() {
      return Logging.initializeLogIfNecessary$default$2$(this);
   }

   public void initializeForcefully(final boolean isInterpreter, final boolean silent) {
      Logging.initializeForcefully$(this, isInterpreter, silent);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public Option org$apache$spark$storage$BlockManagerDecommissioner$$fallbackStorage() {
      return this.org$apache$spark$storage$BlockManagerDecommissioner$$fallbackStorage;
   }

   public int org$apache$spark$storage$BlockManagerDecommissioner$$maxReplicationFailuresForDecommission() {
      return this.org$apache$spark$storage$BlockManagerDecommissioner$$maxReplicationFailuresForDecommission;
   }

   public String org$apache$spark$storage$BlockManagerDecommissioner$$blockSavedOnDecommissionedBlockManagerException() {
      return this.org$apache$spark$storage$BlockManagerDecommissioner$$blockSavedOnDecommissionedBlockManagerException;
   }

   public long lastRDDMigrationTime() {
      return this.lastRDDMigrationTime;
   }

   public void lastRDDMigrationTime_$eq(final long x$1) {
      this.lastRDDMigrationTime = x$1;
   }

   public long lastShuffleMigrationTime() {
      return this.lastShuffleMigrationTime;
   }

   public void lastShuffleMigrationTime_$eq(final long x$1) {
      this.lastShuffleMigrationTime = x$1;
   }

   public boolean rddBlocksLeft() {
      return this.rddBlocksLeft;
   }

   public void rddBlocksLeft_$eq(final boolean x$1) {
      this.rddBlocksLeft = x$1;
   }

   public boolean shuffleBlocksLeft() {
      return this.shuffleBlocksLeft;
   }

   public void shuffleBlocksLeft_$eq(final boolean x$1) {
      this.shuffleBlocksLeft = x$1;
   }

   public HashSet migratingShuffles() {
      return this.migratingShuffles;
   }

   public AtomicInteger numMigratedShuffles() {
      return this.numMigratedShuffles;
   }

   public ConcurrentLinkedQueue shufflesToMigrate() {
      return this.shufflesToMigrate;
   }

   public boolean org$apache$spark$storage$BlockManagerDecommissioner$$stopped() {
      return this.org$apache$spark$storage$BlockManagerDecommissioner$$stopped;
   }

   private void stopped_$eq(final boolean x$1) {
      this.org$apache$spark$storage$BlockManagerDecommissioner$$stopped = x$1;
   }

   public boolean stoppedRDD() {
      return this.stoppedRDD;
   }

   public void stoppedRDD_$eq(final boolean x$1) {
      this.stoppedRDD = x$1;
   }

   public boolean org$apache$spark$storage$BlockManagerDecommissioner$$stoppedShuffle() {
      return this.org$apache$spark$storage$BlockManagerDecommissioner$$stoppedShuffle;
   }

   public void org$apache$spark$storage$BlockManagerDecommissioner$$stoppedShuffle_$eq(final boolean x$1) {
      this.org$apache$spark$storage$BlockManagerDecommissioner$$stoppedShuffle = x$1;
   }

   private HashMap migrationPeers() {
      return this.migrationPeers;
   }

   private Option rddBlockMigrationExecutor() {
      return this.rddBlockMigrationExecutor;
   }

   private Runnable rddBlockMigrationRunnable() {
      return this.rddBlockMigrationRunnable;
   }

   private Option shuffleBlockMigrationRefreshExecutor() {
      return this.shuffleBlockMigrationRefreshExecutor;
   }

   private Runnable shuffleBlockMigrationRefreshRunnable() {
      return this.shuffleBlockMigrationRefreshRunnable;
   }

   private Option shuffleMigrationPool() {
      return this.shuffleMigrationPool;
   }

   public boolean refreshMigratableShuffleBlocks() {
      this.logInfo((Function0)(() -> "Start refreshing migratable shuffle blocks"));
      Set localShuffles = this.org$apache$spark$storage$BlockManagerDecommissioner$$bm.migratableResolver().getStoredShuffles().toSet();
      Seq newShufflesToMigrate = (Seq)localShuffles.diff(this.migratingShuffles()).toSeq().sortBy((b) -> new Tuple2.mcIJ.sp(b.shuffleId(), b.mapId()), .MODULE$.Tuple2(scala.math.Ordering.Int..MODULE$, scala.math.Ordering.Long..MODULE$));
      this.shufflesToMigrate().addAll(scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava((scala.collection.Seq)newShufflesToMigrate.map((x) -> new Tuple2(x, BoxesRunTime.boxToInteger(0)))).asJava());
      this.migratingShuffles().$plus$plus$eq(newShufflesToMigrate);
      int remainedShuffles = this.migratingShuffles().size() - this.numMigratedShuffles().get();
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " of "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.COUNT..MODULE$, BoxesRunTime.boxToInteger(newShufflesToMigrate.size()))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " local shuffles are added. "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TOTAL..MODULE$, BoxesRunTime.boxToInteger(localShuffles.size()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"In total, ", " shuffles are remained."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_REMAINED..MODULE$, BoxesRunTime.boxToInteger(remainedShuffles))}))))));
      Set livePeerSet = this.org$apache$spark$storage$BlockManagerDecommissioner$$bm.getPeers(false).toSet();
      Set currentPeerSet = this.migrationPeers().keys().toSet();
      Set deadPeers = (Set)currentPeerSet.diff(livePeerSet);
      Seq newPeers = Utils$.MODULE$.randomize(livePeerSet.diff(currentPeerSet), scala.reflect.ClassTag..MODULE$.apply(BlockManagerId.class));
      this.migrationPeers().$plus$plus$eq((IterableOnce)newPeers.map((peer) -> {
         this.logDebug((Function0)(() -> "Starting thread to migrate shuffle blocks to " + peer));
         ShuffleMigrationRunnable runnable = this.new ShuffleMigrationRunnable(peer);
         this.shuffleMigrationPool().foreach((x$5) -> x$5.submit(runnable));
         return new Tuple2(peer, runnable);
      }));
      deadPeers.foreach((x$6) -> {
         $anonfun$refreshMigratableShuffleBlocks$8(this, x$6);
         return BoxedUnit.UNIT;
      });
      if (!this.migrationPeers().values().exists((x$8) -> BoxesRunTime.boxToBoolean($anonfun$refreshMigratableShuffleBlocks$10(x$8)))) {
         this.logWarning((Function0)(() -> "No available peers to receive Shuffle blocks, stop migration."));
         this.org$apache$spark$storage$BlockManagerDecommissioner$$stoppedShuffle_$eq(true);
      }

      return newShufflesToMigrate.nonEmpty() || this.migratingShuffles().size() > this.numMigratedShuffles().get();
   }

   public void stopMigratingShuffleBlocks() {
      this.shuffleMigrationPool().foreach((threadPool) -> {
         this.logInfo((Function0)(() -> "Stopping migrating shuffle blocks."));
         this.migrationPeers().values().foreach((x$9) -> {
            $anonfun$stopMigratingShuffleBlocks$3(x$9);
            return BoxedUnit.UNIT;
         });
         return threadPool.shutdownNow();
      });
   }

   public boolean decommissionRddCacheBlocks() {
      Seq replicateBlocksInfo = this.org$apache$spark$storage$BlockManagerDecommissioner$$bm.getMigratableRDDBlocks();
      if (replicateBlocksInfo.nonEmpty()) {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Need to replicate ", " RDD blocks "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_REPLICAS..MODULE$, BoxesRunTime.boxToInteger(replicateBlocksInfo.size()))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"for block manager decommissioning"})))).log(scala.collection.immutable.Nil..MODULE$))));
         Seq blocksFailedReplication = (Seq)((IterableOps)((IterableOps)replicateBlocksInfo.map((replicateBlock) -> {
            boolean replicatedSuccessfully = this.migrateBlock(replicateBlock);
            return new Tuple2(replicateBlock.blockId(), BoxesRunTime.boxToBoolean(replicatedSuccessfully));
         })).filterNot((x$10) -> BoxesRunTime.boxToBoolean($anonfun$decommissionRddCacheBlocks$4(x$10)))).map((x$11) -> (BlockId)x$11._1());
         if (blocksFailedReplication.nonEmpty()) {
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Blocks failed replication in cache decommissioning "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"process: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_IDS..MODULE$, blocksFailedReplication.mkString(","))}))))));
            return true;
         } else {
            return false;
         }
      } else {
         this.logWarning((Function0)(() -> "Asked to decommission RDD cache blocks, but no blocks to migrate"));
         return false;
      }
   }

   private boolean migrateBlock(final BlockManagerMessages.ReplicateBlock blockToReplicate) {
      boolean replicatedSuccessfully = this.org$apache$spark$storage$BlockManagerDecommissioner$$bm.replicateBlock(blockToReplicate.blockId(), blockToReplicate.replicas().toSet(), blockToReplicate.maxReplicas(), new Some(BoxesRunTime.boxToInteger(this.org$apache$spark$storage$BlockManagerDecommissioner$$maxReplicationFailuresForDecommission())));
      if (replicatedSuccessfully) {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Block ", " migrated "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, blockToReplicate.blockId())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"successfully, Removing block now"})))).log(scala.collection.immutable.Nil..MODULE$))));
         this.org$apache$spark$storage$BlockManagerDecommissioner$$bm.removeBlock(blockToReplicate.blockId(), this.org$apache$spark$storage$BlockManagerDecommissioner$$bm.removeBlock$default$2());
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Block ", " removed"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, blockToReplicate.blockId())})))));
      } else {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to migrate block ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, blockToReplicate.blockId())})))));
      }

      return replicatedSuccessfully;
   }

   public void start() {
      this.logInfo((Function0)(() -> "Starting block migration"));
      this.rddBlockMigrationExecutor().foreach((x$12) -> x$12.submit(this.rddBlockMigrationRunnable()));
      this.shuffleBlockMigrationRefreshExecutor().foreach((x$13) -> x$13.submit(this.shuffleBlockMigrationRefreshRunnable()));
   }

   public void stop() {
      if (!this.org$apache$spark$storage$BlockManagerDecommissioner$$stopped()) {
         this.stopped_$eq(true);

         try {
            this.rddBlockMigrationExecutor().foreach((x$14) -> x$14.shutdownNow());
         } catch (Throwable var15) {
            if (var15 == null || !scala.util.control.NonFatal..MODULE$.apply(var15)) {
               throw var15;
            }

            this.logError((Function0)(() -> "Error during shutdown RDD block migration thread"), var15);
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         try {
            this.shuffleBlockMigrationRefreshExecutor().foreach((x$15) -> x$15.shutdownNow());
         } catch (Throwable var14) {
            if (var14 == null || !scala.util.control.NonFatal..MODULE$.apply(var14)) {
               throw var14;
            }

            this.logError((Function0)(() -> "Error during shutdown shuffle block refreshing thread"), var14);
            BoxedUnit var16 = BoxedUnit.UNIT;
         }

         try {
            this.stopMigratingShuffleBlocks();
         } catch (Throwable var13) {
            if (var13 == null || !scala.util.control.NonFatal..MODULE$.apply(var13)) {
               throw var13;
            }

            this.logError((Function0)(() -> "Error during shutdown shuffle block migration thread"), var13);
            BoxedUnit var17 = BoxedUnit.UNIT;
         }

         this.logInfo((Function0)(() -> "Stopped block migration"));
      }
   }

   public Tuple2 lastMigrationInfo() {
      if (!this.org$apache$spark$storage$BlockManagerDecommissioner$$stopped() && (!this.stoppedRDD() || !this.org$apache$spark$storage$BlockManagerDecommissioner$$stoppedShuffle())) {
         long lastMigrationTime = !this.stoppedRDD() && !this.org$apache$spark$storage$BlockManagerDecommissioner$$stoppedShuffle() ? Math.min(this.lastRDDMigrationTime(), this.lastShuffleMigrationTime()) : (!this.org$apache$spark$storage$BlockManagerDecommissioner$$stoppedShuffle() ? this.lastShuffleMigrationTime() : this.lastRDDMigrationTime());
         boolean blocksMigrated = (!this.shuffleBlocksLeft() || this.org$apache$spark$storage$BlockManagerDecommissioner$$stoppedShuffle()) && (!this.rddBlocksLeft() || this.stoppedRDD());
         return new Tuple2.mcJZ.sp(lastMigrationTime, blocksMigrated);
      } else {
         return new Tuple2.mcJZ.sp(Long.MAX_VALUE, true);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$refreshMigratableShuffleBlocks$9(final ShuffleMigrationRunnable x$7) {
      x$7.keepRunning_$eq(false);
   }

   // $FF: synthetic method
   public static final void $anonfun$refreshMigratableShuffleBlocks$8(final BlockManagerDecommissioner $this, final BlockManagerId x$6) {
      $this.migrationPeers().get(x$6).foreach((x$7) -> {
         $anonfun$refreshMigratableShuffleBlocks$9(x$7);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final boolean $anonfun$refreshMigratableShuffleBlocks$10(final ShuffleMigrationRunnable x$8) {
      return x$8.keepRunning();
   }

   // $FF: synthetic method
   public static final void $anonfun$stopMigratingShuffleBlocks$3(final ShuffleMigrationRunnable x$9) {
      x$9.keepRunning_$eq(false);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$decommissionRddCacheBlocks$4(final Tuple2 x$10) {
      return x$10._2$mcZ$sp();
   }

   public BlockManagerDecommissioner(final SparkConf conf, final BlockManager bm) {
      this.org$apache$spark$storage$BlockManagerDecommissioner$$conf = conf;
      this.org$apache$spark$storage$BlockManagerDecommissioner$$bm = bm;
      Logging.$init$(this);
      this.org$apache$spark$storage$BlockManagerDecommissioner$$fallbackStorage = FallbackStorage$.MODULE$.getFallbackStorage(conf);
      this.org$apache$spark$storage$BlockManagerDecommissioner$$maxReplicationFailuresForDecommission = BoxesRunTime.unboxToInt(conf.get(package$.MODULE$.STORAGE_DECOMMISSION_MAX_REPLICATION_FAILURE_PER_BLOCK()));
      this.org$apache$spark$storage$BlockManagerDecommissioner$$blockSavedOnDecommissionedBlockManagerException = BlockSavedOnDecommissionedBlockManagerException.class.getSimpleName();
      this.lastRDDMigrationTime = 0L;
      this.lastShuffleMigrationTime = 0L;
      this.rddBlocksLeft = true;
      this.shuffleBlocksLeft = true;
      this.migratingShuffles = (HashSet)scala.collection.mutable.HashSet..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      this.numMigratedShuffles = new AtomicInteger(0);
      this.shufflesToMigrate = new ConcurrentLinkedQueue();
      this.org$apache$spark$storage$BlockManagerDecommissioner$$stopped = false;
      this.stoppedRDD = !BoxesRunTime.unboxToBoolean(conf.get(package$.MODULE$.STORAGE_DECOMMISSION_RDD_BLOCKS_ENABLED()));
      this.org$apache$spark$storage$BlockManagerDecommissioner$$stoppedShuffle = !BoxesRunTime.unboxToBoolean(conf.get(package$.MODULE$.STORAGE_DECOMMISSION_SHUFFLE_BLOCKS_ENABLED()));
      this.migrationPeers = (HashMap)scala.collection.mutable.HashMap..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      this.rddBlockMigrationExecutor = (Option)(BoxesRunTime.unboxToBoolean(conf.get(package$.MODULE$.STORAGE_DECOMMISSION_RDD_BLOCKS_ENABLED())) ? new Some(ThreadUtils$.MODULE$.newDaemonSingleThreadExecutor("block-manager-decommission-rdd")) : scala.None..MODULE$);
      this.rddBlockMigrationRunnable = new Runnable() {
         private final long sleepInterval;
         // $FF: synthetic field
         private final BlockManagerDecommissioner $outer;

         public long sleepInterval() {
            return this.sleepInterval;
         }

         public void run() {
            this.$outer.logInfo((Function0)(() -> "Attempting to migrate all RDD blocks"));

            while(!this.$outer.org$apache$spark$storage$BlockManagerDecommissioner$$stopped() && !this.$outer.stoppedRDD()) {
               if (!this.$outer.org$apache$spark$storage$BlockManagerDecommissioner$$bm.getPeers(false).exists((x$4) -> BoxesRunTime.boxToBoolean($anonfun$run$19(x$4)))) {
                  this.$outer.logWarning((Function0)(() -> "No available peers to receive RDD blocks, stop migration."));
                  this.$outer.stoppedRDD_$eq(true);
               } else {
                  try {
                     long startTime = System.nanoTime();
                     this.$outer.logInfo((Function0)(() -> "Attempting to migrate all cached RDD blocks"));
                     this.$outer.rddBlocksLeft_$eq(this.$outer.decommissionRddCacheBlocks());
                     this.$outer.lastRDDMigrationTime_$eq(startTime);
                     this.$outer.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Finished current round RDD blocks migration, "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"waiting for ", "ms before the next round migration."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SLEEP_TIME..MODULE$, BoxesRunTime.boxToLong(this.sleepInterval()))}))))));
                     Thread.sleep(this.sleepInterval());
                  } catch (Throwable var7) {
                     if (var7 instanceof InterruptedException) {
                        if (!this.$outer.org$apache$spark$storage$BlockManagerDecommissioner$$stopped() && !this.$outer.stoppedRDD()) {
                           this.$outer.logInfo((Function0)(() -> "Stop RDD blocks migration unexpectedly."));
                        } else {
                           this.$outer.logInfo((Function0)(() -> "Stop RDD blocks migration."));
                        }

                        this.$outer.stoppedRDD_$eq(true);
                        BoxedUnit var8 = BoxedUnit.UNIT;
                     } else {
                        if (var7 == null || !scala.util.control.NonFatal..MODULE$.apply(var7)) {
                           throw var7;
                        }

                        this.$outer.logError((Function0)(() -> "Error occurred during RDD blocks migration."), var7);
                        this.$outer.stoppedRDD_$eq(true);
                        BoxedUnit var10000 = BoxedUnit.UNIT;
                     }
                  }
               }
            }

         }

         // $FF: synthetic method
         public static final boolean $anonfun$run$19(final BlockManagerId x$4) {
            boolean var10000;
            label23: {
               BlockManagerId var1 = FallbackStorage$.MODULE$.FALLBACK_BLOCK_MANAGER_ID();
               if (x$4 == null) {
                  if (var1 != null) {
                     break label23;
                  }
               } else if (!x$4.equals(var1)) {
                  break label23;
               }

               var10000 = false;
               return var10000;
            }

            var10000 = true;
            return var10000;
         }

         public {
            if (BlockManagerDecommissioner.this == null) {
               throw null;
            } else {
               this.$outer = BlockManagerDecommissioner.this;
               this.sleepInterval = BoxesRunTime.unboxToLong(BlockManagerDecommissioner.this.org$apache$spark$storage$BlockManagerDecommissioner$$conf.get(package$.MODULE$.STORAGE_DECOMMISSION_REPLICATION_REATTEMPT_INTERVAL()));
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
      this.shuffleBlockMigrationRefreshExecutor = (Option)(BoxesRunTime.unboxToBoolean(conf.get(package$.MODULE$.STORAGE_DECOMMISSION_SHUFFLE_BLOCKS_ENABLED())) ? new Some(ThreadUtils$.MODULE$.newDaemonSingleThreadExecutor("block-manager-decommission-shuffle")) : scala.None..MODULE$);
      this.shuffleBlockMigrationRefreshRunnable = new Runnable() {
         private final long sleepInterval;
         // $FF: synthetic field
         private final BlockManagerDecommissioner $outer;

         public long sleepInterval() {
            return this.sleepInterval;
         }

         public void run() {
            this.$outer.logInfo((Function0)(() -> "Attempting to migrate all shuffle blocks"));

            while(!this.$outer.org$apache$spark$storage$BlockManagerDecommissioner$$stopped() && !this.$outer.org$apache$spark$storage$BlockManagerDecommissioner$$stoppedShuffle()) {
               try {
                  long startTime = System.nanoTime();
                  this.$outer.shuffleBlocksLeft_$eq(this.$outer.refreshMigratableShuffleBlocks());
                  this.$outer.lastShuffleMigrationTime_$eq(startTime);
                  this.$outer.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Finished current round refreshing migratable shuffle blocks, "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"waiting for ", "ms before the "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SLEEP_TIME..MODULE$, BoxesRunTime.boxToLong(this.sleepInterval()))})))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"next round refreshing."})))).log(scala.collection.immutable.Nil..MODULE$))));
                  Thread.sleep(this.sleepInterval());
               } catch (Throwable var7) {
                  if (var7 instanceof InterruptedException && this.$outer.org$apache$spark$storage$BlockManagerDecommissioner$$stopped()) {
                     this.$outer.logInfo((Function0)(() -> "Stop refreshing migratable shuffle blocks."));
                     BoxedUnit var8 = BoxedUnit.UNIT;
                  } else {
                     if (var7 == null || !scala.util.control.NonFatal..MODULE$.apply(var7)) {
                        throw var7;
                     }

                     this.$outer.logError((Function0)(() -> "Error occurred during shuffle blocks migration."), var7);
                     this.$outer.org$apache$spark$storage$BlockManagerDecommissioner$$stoppedShuffle_$eq(true);
                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  }
               }
            }

         }

         public {
            if (BlockManagerDecommissioner.this == null) {
               throw null;
            } else {
               this.$outer = BlockManagerDecommissioner.this;
               this.sleepInterval = BoxesRunTime.unboxToLong(BlockManagerDecommissioner.this.org$apache$spark$storage$BlockManagerDecommissioner$$conf.get(package$.MODULE$.STORAGE_DECOMMISSION_REPLICATION_REATTEMPT_INTERVAL()));
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
      this.shuffleMigrationPool = (Option)(BoxesRunTime.unboxToBoolean(conf.get(package$.MODULE$.STORAGE_DECOMMISSION_SHUFFLE_BLOCKS_ENABLED())) ? new Some(ThreadUtils$.MODULE$.newDaemonCachedThreadPool("migrate-shuffles", BoxesRunTime.unboxToInt(conf.get(package$.MODULE$.STORAGE_DECOMMISSION_SHUFFLE_MAX_THREADS())), ThreadUtils$.MODULE$.newDaemonCachedThreadPool$default$3())) : scala.None..MODULE$);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private class ShuffleMigrationRunnable implements Runnable {
      private final BlockManagerId peer;
      private volatile boolean keepRunning;
      // $FF: synthetic field
      public final BlockManagerDecommissioner $outer;

      public boolean keepRunning() {
         return this.keepRunning;
      }

      public void keepRunning_$eq(final boolean x$1) {
         this.keepRunning = x$1;
      }

      private boolean allowRetry(final ShuffleBlockInfo shuffleBlock, final int failureNum) {
         if (failureNum < this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().org$apache$spark$storage$BlockManagerDecommissioner$$maxReplicationFailuresForDecommission()) {
            this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Add ", " back to migration queue for "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SHUFFLE_BLOCK_INFO..MODULE$, shuffleBlock)}))).$plus(this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" retry (", " / "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FAILURES..MODULE$, BoxesRunTime.boxToInteger(failureNum))})))).$plus(this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MAX_ATTEMPTS..MODULE$, BoxesRunTime.boxToInteger(this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().org$apache$spark$storage$BlockManagerDecommissioner$$maxReplicationFailuresForDecommission()))}))))));
            return this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().shufflesToMigrate().add(new Tuple2(shuffleBlock, BoxesRunTime.boxToInteger(failureNum)));
         } else {
            this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Give up migrating ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SHUFFLE_BLOCK_INFO..MODULE$, shuffleBlock)}))).$plus(this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"since it's been failed for "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " times"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MAX_ATTEMPTS..MODULE$, BoxesRunTime.boxToInteger(this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().org$apache$spark$storage$BlockManagerDecommissioner$$maxReplicationFailuresForDecommission()))}))))));
            return false;
         }
      }

      private Tuple2 nextShuffleBlockToMigrate() {
         while(true) {
            if (!Thread.currentThread().isInterrupted()) {
               Option var2 = scala.Option..MODULE$.apply(this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().shufflesToMigrate().poll());
               if (var2 instanceof Some) {
                  Some var3 = (Some)var2;
                  Tuple2 head = (Tuple2)var3.value();
                  return head;
               }

               if (scala.None..MODULE$.equals(var2)) {
                  Thread.sleep(1000L);
                  BoxedUnit var10000 = BoxedUnit.UNIT;
                  continue;
               }

               throw new MatchError(var2);
            }

            throw SparkCoreErrors$.MODULE$.interruptedError();
         }
      }

      public void run() {
         this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Starting shuffle block migration thread for ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PEER..MODULE$, this.peer)})))));

         while(this.keepRunning()) {
            try {
               Tuple2 var6 = this.nextShuffleBlockToMigrate();
               if (var6 == null) {
                  throw new MatchError(var6);
               }

               ShuffleBlockInfo shuffleBlockInfo = (ShuffleBlockInfo)var6._1();
               int retryCount = var6._2$mcI$sp();
               Tuple2 var5 = new Tuple2(shuffleBlockInfo, BoxesRunTime.boxToInteger(retryCount));
               ShuffleBlockInfo shuffleBlockInfox = (ShuffleBlockInfo)var5._1();
               int retryCountx = var5._2$mcI$sp();
               List blocks = this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().org$apache$spark$storage$BlockManagerDecommissioner$$bm.migratableResolver().getMigrationBlocks(shuffleBlockInfox);
               boolean isTargetDecommissioned = false;
               if (blocks.isEmpty()) {
                  this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Ignore deleted shuffle block ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SHUFFLE_BLOCK_INFO..MODULE$, shuffleBlockInfox)})))));
               } else {
                  this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Got migration sub-blocks ", ". Trying to migrate "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_IDS..MODULE$, blocks)}))).$plus(this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " to ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SHUFFLE_BLOCK_INFO..MODULE$, shuffleBlockInfox), new MDC(org.apache.spark.internal.LogKeys.PEER..MODULE$, this.peer)})))).$plus(this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", " / "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_RETRY..MODULE$, BoxesRunTime.boxToInteger(retryCountx))})))).$plus(this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MAX_ATTEMPTS..MODULE$, BoxesRunTime.boxToInteger(this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().org$apache$spark$storage$BlockManagerDecommissioner$$maxReplicationFailuresForDecommission()))}))))));

                  try {
                     long startTime;
                     label120: {
                        label119: {
                           startTime = System.currentTimeMillis();
                           if (this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().org$apache$spark$storage$BlockManagerDecommissioner$$fallbackStorage().isDefined()) {
                              BlockManagerId var33 = this.peer;
                              BlockManagerId var15 = FallbackStorage$.MODULE$.FALLBACK_BLOCK_MANAGER_ID();
                              if (var33 == null) {
                                 if (var15 == null) {
                                    break label119;
                                 }
                              } else if (var33.equals(var15)) {
                                 break label119;
                              }
                           }

                           blocks.foreach((x0$1) -> {
                              $anonfun$run$5(this, x0$1);
                              return BoxedUnit.UNIT;
                           });
                           break label120;
                        }

                        this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().org$apache$spark$storage$BlockManagerDecommissioner$$fallbackStorage().foreach((x$2) -> {
                           $anonfun$run$4(this, shuffleBlockInfox, x$2);
                           return BoxedUnit.UNIT;
                        });
                     }

                     this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Migrated ", " ("})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SHUFFLE_BLOCK_INFO..MODULE$, shuffleBlockInfox)}))).$plus(this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"size: ", ") "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SIZE..MODULE$, Utils$.MODULE$.bytesToString(BoxesRunTime.unboxToLong(blocks.map((b) -> BoxesRunTime.boxToLong($anonfun$run$9(b))).sum(scala.math.Numeric.LongIsIntegral..MODULE$))))})))).$plus(this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"to ", " in "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PEER..MODULE$, this.peer)})))).$plus(this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " ms"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DURATION..MODULE$, BoxesRunTime.boxToLong(System.currentTimeMillis() - startTime))}))))));
                  } catch (Throwable var24) {
                     if (var24 instanceof IOException ? true : var24 instanceof SparkException) {
                        if (this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().org$apache$spark$storage$BlockManagerDecommissioner$$bm.migratableResolver().getMigrationBlocks(shuffleBlockInfox).size() < blocks.size()) {
                           this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Skipping block ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SHUFFLE_BLOCK_INFO..MODULE$, shuffleBlockInfox)}))).$plus(this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"block deleted."})))).log(scala.collection.immutable.Nil..MODULE$))));
                           BoxedUnit var27 = BoxedUnit.UNIT;
                        } else {
                           label106: {
                              label134: {
                                 if (this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().org$apache$spark$storage$BlockManagerDecommissioner$$fallbackStorage().isDefined()) {
                                    BlockManagerId var28 = this.peer;
                                    BlockManagerId var18 = FallbackStorage$.MODULE$.FALLBACK_BLOCK_MANAGER_ID();
                                    if (var28 == null) {
                                       if (var18 != null) {
                                          break label134;
                                       }
                                    } else if (!var28.equals(var18)) {
                                       break label134;
                                    }
                                 }

                                 if (var24.getCause() != null && var24.getCause().getMessage() != null && var24.getCause().getMessage().contains(this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().org$apache$spark$storage$BlockManagerDecommissioner$$blockSavedOnDecommissionedBlockManagerException())) {
                                    isTargetDecommissioned = true;
                                    this.keepRunning_$eq(false);
                                    BoxedUnit var30 = BoxedUnit.UNIT;
                                    break label106;
                                 }

                                 this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error occurred during migrating "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SHUFFLE_BLOCK_INFO..MODULE$, shuffleBlockInfox)}))))), var24);
                                 this.keepRunning_$eq(false);
                                 BoxedUnit var29 = BoxedUnit.UNIT;
                                 break label106;
                              }

                              this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().org$apache$spark$storage$BlockManagerDecommissioner$$fallbackStorage().foreach((x$3) -> {
                                 $anonfun$run$11(this, shuffleBlockInfox, x$3);
                                 return BoxedUnit.UNIT;
                              });
                              BoxedUnit var31 = BoxedUnit.UNIT;
                           }
                        }
                     } else {
                        if (!(var24 instanceof Exception)) {
                           throw var24;
                        }

                        Exception var19 = (Exception)var24;
                        this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().logError((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error occurred during migrating "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SHUFFLE_BLOCK_INFO..MODULE$, shuffleBlockInfox)}))))), var19);
                        this.keepRunning_$eq(false);
                        BoxedUnit var32 = BoxedUnit.UNIT;
                     }
                  }
               }

               if (this.keepRunning()) {
                  BoxesRunTime.boxToInteger(this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().numMigratedShuffles().incrementAndGet());
               } else {
                  this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Stop migrating shuffle blocks to ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PEER..MODULE$, this.peer)})))));
                  int newRetryCount = isTargetDecommissioned ? retryCountx : retryCountx + 1;
                  if (!this.allowRetry(shuffleBlockInfox, newRetryCount)) {
                     BoxesRunTime.boxToInteger(this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().numMigratedShuffles().incrementAndGet());
                  } else {
                     BoxedUnit var34 = BoxedUnit.UNIT;
                  }
               }
            } catch (Throwable var25) {
               if (var25 instanceof InterruptedException) {
                  if (this.keepRunning()) {
                     this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().logInfo((Function0)(() -> "Stop shuffle block migration unexpectedly."));
                  } else {
                     this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().logInfo((Function0)(() -> "Stop shuffle block migration."));
                  }

                  this.keepRunning_$eq(false);
                  BoxedUnit var26 = BoxedUnit.UNIT;
               } else {
                  if (var25 == null || !scala.util.control.NonFatal..MODULE$.apply(var25)) {
                     throw var25;
                  }

                  this.keepRunning_$eq(false);
                  this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().logError((Function0)(() -> "Error occurred during shuffle blocks migration."), var25);
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               }
            }
         }

      }

      // $FF: synthetic method
      public BlockManagerDecommissioner org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final void $anonfun$run$4(final ShuffleMigrationRunnable $this, final ShuffleBlockInfo shuffleBlockInfo$1, final FallbackStorage x$2) {
         x$2.copy(shuffleBlockInfo$1, $this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().org$apache$spark$storage$BlockManagerDecommissioner$$bm);
      }

      // $FF: synthetic method
      public static final void $anonfun$run$5(final ShuffleMigrationRunnable $this, final Tuple2 x0$1) {
         if (x0$1 != null) {
            BlockId blockId = (BlockId)x0$1._1();
            ManagedBuffer buffer = (ManagedBuffer)x0$1._2();
            $this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().logDebug((Function0)(() -> "Migrating sub-block " + blockId));
            $this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().org$apache$spark$storage$BlockManagerDecommissioner$$bm.blockTransferService().uploadBlockSync($this.peer.host(), $this.peer.port(), $this.peer.executorId(), blockId, buffer, org.apache.spark.storage.StorageLevel..MODULE$.DISK_ONLY(), (ClassTag)null);
            $this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().logDebug((Function0)(() -> "Migrated sub-block " + blockId));
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            throw new MatchError(x0$1);
         }
      }

      // $FF: synthetic method
      public static final long $anonfun$run$9(final Tuple2 b) {
         return ((ManagedBuffer)b._2()).size();
      }

      // $FF: synthetic method
      public static final void $anonfun$run$11(final ShuffleMigrationRunnable $this, final ShuffleBlockInfo shuffleBlockInfo$1, final FallbackStorage x$3) {
         x$3.copy(shuffleBlockInfo$1, $this.org$apache$spark$storage$BlockManagerDecommissioner$ShuffleMigrationRunnable$$$outer().org$apache$spark$storage$BlockManagerDecommissioner$$bm);
      }

      public ShuffleMigrationRunnable(final BlockManagerId peer) {
         this.peer = peer;
         if (BlockManagerDecommissioner.this == null) {
            throw null;
         } else {
            this.$outer = BlockManagerDecommissioner.this;
            super();
            this.keepRunning = true;
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
