package org.apache.spark.streaming.dstream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.rdd.RDD;
import org.apache.spark.rdd.UnionRDD;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.Seconds$;
import org.apache.spark.streaming.StreamingContext;
import org.apache.spark.streaming.Time;
import org.apache.spark.streaming.Time$;
import org.apache.spark.streaming.scheduler.StreamInputInfo;
import org.apache.spark.streaming.scheduler.StreamInputInfo$;
import org.apache.spark.util.Clock;
import org.apache.spark.util.SerializableConfiguration;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashSet;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\r}c!B!C\u0001\u0011c\u0005\u0002C4\u0001\u0005\u0003\u0005\u000b\u0011\u00025\t\u00111\u0004!\u0011!Q\u0001\n5D\u0001\u0002\u001f\u0001\u0003\u0002\u0003\u0006I!\u001f\u0005\u000b\u0003\u001f\u0001!\u0011!Q\u0001\n\u0005%\u0001BCA\t\u0001\t\u0005\t\u0015!\u0003\u0002\u0014!Q\u00111\u0005\u0001\u0003\u0002\u0003\u0006Y!!\n\t\u0015\u0005E\u0002A!A!\u0002\u0017\t\u0019\u0004\u0003\u0006\u00026\u0001\u0011\t\u0011)A\u0006\u0003oAq!!\u0014\u0001\t\u0003\ty\u0005C\u0005\u0002f\u0001\u0011\r\u0011\"\u0003\u0002h!A\u0011q\u000f\u0001!\u0002\u0013\tI\u0007C\u0005\u0002z\u0001\u0011\r\u0011\"\u0003\u0002|!A\u00111\u0011\u0001!\u0002\u0013\ti\bC\u0004\u0002\u0006\u0002!I!a\"\t\u0015\u0005=\u0005A1A\u0005R\u0011\u000b\t\n\u0003\u0005\u0002`\u0002\u0001\u000b\u0011BAJ\u0011%\t\t\u000f\u0001b\u0001\n\u0013\t\u0019\u000f\u0003\u0005\u0002l\u0002\u0001\u000b\u0011BAs\u0011%\ti\u000f\u0001b\u0001\n\u0013\ty\u000f\u0003\u0005\u0002x\u0002\u0001\u000b\u0011BAy\u0011%\tI\u0010\u0001b\u0001\n\u0013\tY\b\u0003\u0005\u0002|\u0002\u0001\u000b\u0011BA?\u0011)\ti\u0010\u0001a\u0001\n\u0003!\u0015Q\u0015\u0005\u000b\u0003\u007f\u0004\u0001\u0019!C\u0001\t\n\u0005\u0001\u0002\u0003B\u0004\u0001\u0001\u0006K!a*\t\u0013\tE\u0001\u00011A\u0005\n\tM\u0001\"\u0003B\u000e\u0001\u0001\u0007I\u0011\u0002B\u000f\u0011!\u0011\t\u0003\u0001Q!\n\tU\u0001\"\u0003B\u0013\u0001\u0001\u0007I\u0011BAr\u0011%\u00119\u0003\u0001a\u0001\n\u0013\u0011I\u0003\u0003\u0005\u0003.\u0001\u0001\u000b\u0015BAs\u0011%\u0011\t\u0004\u0001a\u0001\n\u0013\u0011\u0019\u0004C\u0005\u00036\u0001\u0001\r\u0011\"\u0003\u00038!9!1\b\u0001!B\u0013a\b\"\u0003B \u0001\u0001\u0007I\u0011\u0002B!\u0011%\u0011I\u0005\u0001a\u0001\n\u0013\u0011Y\u0005\u0003\u0005\u0003P\u0001\u0001\u000b\u0015\u0002B\"\u0011\u001d\u0011\u0019\u0006\u0001C!\u00033DqA!\u0016\u0001\t\u0003\nI\u000eC\u0004\u0003X\u0001!\tE!\u0017\t\u0011\t5\u0004\u0001\"\u0015E\u0005_BqAa\u001d\u0001\t\u0013\u0011)\bC\u0004\u0003|\u0001!IA! \t\u000f\t=\u0005\u0001\"\u0003\u0003\u0012\"9!\u0011\u0016\u0001\u0005\n\tM\u0002BB@\u0001\t\u0013\u0011\t\u0005C\u0004\u0003,\u0002!I!!7\t\u000f\t5\u0006\u0001\"\u0003\u00030\u001a9\u0011q\u0013\u0001\u0001\t\u0006e\u0005bBA'c\u0011\u0005\u0011\u0011\u0015\u0005\b\u0003G\u000bD\u0011BAS\u0011\u001d\t\u0019-\rC!\u0003\u000bDq!!52\t\u0003\n\u0019\u000eC\u0004\u0002XF\"\t%!7\t\u000f\u0005m\u0017\u0007\"\u0011\u0002^\u001eA!Q\u001b\"\t\u0002\u0011\u00139NB\u0004B\u0005\"\u0005AI!7\t\u000f\u00055\u0013\b\"\u0001\u0003h\"9!\u0011^\u001d\u0005\u0002\t-\bb\u0002Bys\u0011\u0005!1\u001f\u0005\n\u0005wL\u0014\u0013!C\u0001\u0005{D\u0011ba\t:#\u0003%\ta!\n\t\u0013\re\u0012(%A\u0005\u0002\rm\u0002\"CB(s\u0005\u0005I\u0011BB)\u0005A1\u0015\u000e\\3J]B,H\u000fR*ue\u0016\fWN\u0003\u0002D\t\u00069Am\u001d;sK\u0006l'BA#G\u0003%\u0019HO]3b[&twM\u0003\u0002H\u0011\u0006)1\u000f]1sW*\u0011\u0011JS\u0001\u0007CB\f7\r[3\u000b\u0003-\u000b1a\u001c:h+\u0015i%,ZA\u001e'\t\u0001a\nE\u0002P!Jk\u0011AQ\u0005\u0003#\n\u0013A\"\u00138qkR$5\u000b\u001e:fC6\u0004Ba\u0015,YI6\tAKC\u0001V\u0003\u0015\u00198-\u00197b\u0013\t9FK\u0001\u0004UkBdWM\r\t\u00033jc\u0001\u0001B\u0003\\\u0001\t\u0007QLA\u0001L\u0007\u0001\t\"AX1\u0011\u0005M{\u0016B\u00011U\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"a\u00152\n\u0005\r$&aA!osB\u0011\u0011,\u001a\u0003\u0006M\u0002\u0011\r!\u0018\u0002\u0002-\u0006!ql]:d!\tI'.D\u0001E\u0013\tYGI\u0001\tTiJ,\u0017-\\5oO\u000e{g\u000e^3yi\u0006IA-\u001b:fGR|'/\u001f\t\u0003]Vt!a\\:\u0011\u0005A$V\"A9\u000b\u0005Id\u0016A\u0002\u001fs_>$h(\u0003\u0002u)\u00061\u0001K]3eK\u001aL!A^<\u0003\rM#(/\u001b8h\u0015\t!H+\u0001\u0004gS2$XM\u001d\t\u0006'jd\u0018\u0011B\u0005\u0003wR\u0013\u0011BR;oGRLwN\\\u0019\u0011\u0007u\f)!D\u0001\u007f\u0015\ry\u0018\u0011A\u0001\u0003MNT1!a\u0001I\u0003\u0019A\u0017\rZ8pa&\u0019\u0011q\u0001@\u0003\tA\u000bG\u000f\u001b\t\u0004'\u0006-\u0011bAA\u0007)\n9!i\\8mK\u0006t\u0017\u0001\u00048fo\u001aKG.Z:P]2L\u0018\u0001B2p]\u001a\u0004RaUA\u000b\u00033I1!a\u0006U\u0005\u0019y\u0005\u000f^5p]B!\u00111DA\u0010\u001b\t\tiB\u0003\u0003\u0002\u0012\u0005\u0005\u0011\u0002BA\u0011\u0003;\u0011QbQ8oM&<WO]1uS>t\u0017AA6n!\u0015\t9#!\fY\u001b\t\tICC\u0002\u0002,Q\u000bqA]3gY\u0016\u001cG/\u0003\u0003\u00020\u0005%\"\u0001C\"mCN\u001cH+Y4\u0002\u0005Yl\u0007#BA\u0014\u0003[!\u0017A\u00014n!\u0019\t9#!\f\u0002:A\u0019\u0011,a\u000f\u0005\u000f\u0005u\u0002A1\u0001\u0002@\t\ta)E\u0002_\u0003\u0003\u0002b!a\u0011\u0002Ja#WBAA#\u0015\u0011\t9%!\u0001\u0002\u00135\f\u0007O]3ek\u000e,\u0017\u0002BA&\u0003\u000b\u00121\"\u00138qkR4uN]7bi\u00061A(\u001b8jiz\"B\"!\u0015\u0002\\\u0005u\u0013qLA1\u0003G\"\u0002\"a\u0015\u0002V\u0005]\u0013\u0011\f\t\u0007\u001f\u0002AF-!\u000f\t\u000f\u0005\r\u0012\u0002q\u0001\u0002&!9\u0011\u0011G\u0005A\u0004\u0005M\u0002bBA\u001b\u0013\u0001\u000f\u0011q\u0007\u0005\u0006O&\u0001\r\u0001\u001b\u0005\u0006Y&\u0001\r!\u001c\u0005\bq&\u0001\n\u00111\u0001z\u0011%\ty!\u0003I\u0001\u0002\u0004\tI\u0001C\u0005\u0002\u0012%\u0001\n\u00111\u0001\u0002\u0014\u0005\u00192/\u001a:jC2L'0\u00192mK\u000e{gNZ(qiV\u0011\u0011\u0011\u000e\t\u0006'\u0006U\u00111\u000e\t\u0005\u0003[\n\u0019(\u0004\u0002\u0002p)\u0019\u0011\u0011\u000f$\u0002\tU$\u0018\u000e\\\u0005\u0005\u0003k\nyGA\rTKJL\u0017\r\\5{C\ndWmQ8oM&<WO]1uS>t\u0017\u0001F:fe&\fG.\u001b>bE2,7i\u001c8g\u001fB$\b%\u0001\u000bnS:\u0014V-\\3nE\u0016\u0014H)\u001e:bi&|gnU\u000b\u0003\u0003{\u00022![A@\u0013\r\t\t\t\u0012\u0002\t\tV\u0014\u0018\r^5p]\u0006)R.\u001b8SK6,WNY3s\tV\u0014\u0018\r^5p]N\u0003\u0013!B2m_\u000e\\WCAAE!\u0011\ti'a#\n\t\u00055\u0015q\u000e\u0002\u0006\u00072|7m[\u0001\u000fG\",7m\u001b9pS:$H)\u0019;b+\t\t\u0019\nE\u0002\u0002\u0016Fj\u0011\u0001\u0001\u0002\u001f\r&dW-\u00138qkR$5\u000b\u001e:fC6\u001c\u0005.Z2la>Lg\u000e\u001e#bi\u0006\u001c2!MAN!\u0011y\u0015Q\u0014*\n\u0007\u0005}%IA\u000bE'R\u0014X-Y7DQ\u0016\u001c7\u000e]8j]R$\u0015\r^1\u0015\u0005\u0005M\u0015a\u00035bI>|\u0007OR5mKN,\"!a*\u0011\u0011\u0005%\u00161WA\\\u0003{k!!a+\u000b\t\u00055\u0016qV\u0001\b[V$\u0018M\u00197f\u0015\r\t\t\fV\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA[\u0003W\u0013q\u0001S1tQ6\u000b\u0007\u000fE\u0002j\u0003sK1!a/E\u0005\u0011!\u0016.\\3\u0011\tM\u000by,\\\u0005\u0004\u0003\u0003$&!B!se\u0006L\u0018AB;qI\u0006$X\r\u0006\u0003\u0002H\u00065\u0007cA*\u0002J&\u0019\u00111\u001a+\u0003\tUs\u0017\u000e\u001e\u0005\b\u0003\u001f$\u0004\u0019AA\\\u0003\u0011!\u0018.\\3\u0002\u000f\rdW-\u00198vaR!\u0011qYAk\u0011\u001d\ty-\u000ea\u0001\u0003o\u000bqA]3ti>\u0014X\r\u0006\u0002\u0002H\u0006AAo\\*ue&tw\rF\u0001n\u0003=\u0019\u0007.Z2la>Lg\u000e\u001e#bi\u0006\u0004\u0013!H5oSRL\u0017\r\\'pIRKW.Z%h]>\u0014X\r\u00165sKNDw\u000e\u001c3\u0016\u0005\u0005\u0015\bcA*\u0002h&\u0019\u0011\u0011\u001e+\u0003\t1{gnZ\u0001\u001fS:LG/[1m\u001b>$G+[7f\u0013\u001etwN]3UQJ,7\u000f[8mI\u0002\nAC\\;n\u0005\u0006$8\r[3t)>\u0014V-\\3nE\u0016\u0014XCAAy!\r\u0019\u00161_\u0005\u0004\u0003k$&aA%oi\u0006)b.^7CCR\u001c\u0007.Z:U_J+W.Z7cKJ\u0004\u0013A\u00053ve\u0006$\u0018n\u001c8U_J+W.Z7cKJ\f1\u0003Z;sCRLwN\u001c+p%\u0016lW-\u001c2fe\u0002\n\u0001DY1uG\"$\u0016.\\3U_N+G.Z2uK\u00124\u0015\u000e\\3t\u0003q\u0011\u0017\r^2i)&lW\rV8TK2,7\r^3e\r&dWm]0%KF$B!a2\u0003\u0004!I!Q\u0001\r\u0002\u0002\u0003\u0007\u0011qU\u0001\u0004q\u0012\n\u0014!\u00072bi\u000eDG+[7f)>\u001cV\r\\3di\u0016$g)\u001b7fg\u0002B3!\u0007B\u0006!\r\u0019&QB\u0005\u0004\u0005\u001f!&!\u0003;sC:\u001c\u0018.\u001a8u\u0003U\u0011XmY3oi2L8+\u001a7fGR,GMR5mKN,\"A!\u0006\u0011\u000b\u0005%&qC7\n\t\te\u00111\u0016\u0002\b\u0011\u0006\u001c\bnU3u\u0003e\u0011XmY3oi2L8+\u001a7fGR,GMR5mKN|F%Z9\u0015\t\u0005\u001d'q\u0004\u0005\n\u0005\u000bY\u0012\u0011!a\u0001\u0005+\taC]3dK:$H._*fY\u0016\u001cG/\u001a3GS2,7\u000f\t\u0015\u00049\t-\u0011A\u00067bgRtUm\u001e$jY\u00164\u0015N\u001c3j]\u001e$\u0016.\\3\u000251\f7\u000f\u001e(fo\u001aKG.\u001a$j]\u0012Lgn\u001a+j[\u0016|F%Z9\u0015\t\u0005\u001d'1\u0006\u0005\n\u0005\u000bq\u0012\u0011!a\u0001\u0003K\fq\u0003\\1ti:+wOR5mK\u001aKg\u000eZ5oORKW.\u001a\u0011)\u0007}\u0011Y!A\u0003`a\u0006$\b.F\u0001}\u0003%y\u0006/\u0019;i?\u0012*\u0017\u000f\u0006\u0003\u0002H\ne\u0002\u0002\u0003B\u0003C\u0005\u0005\t\u0019\u0001?\u0002\r}\u0003\u0018\r\u001e5!Q\r\u0011#1B\u0001\u0004?\u001a\u001cXC\u0001B\"!\ri(QI\u0005\u0004\u0005\u000fr(A\u0003$jY\u0016\u001c\u0016p\u001d;f[\u00069qLZ:`I\u0015\fH\u0003BAd\u0005\u001bB\u0011B!\u0002%\u0003\u0003\u0005\rAa\u0011\u0002\t}37\u000f\t\u0015\u0004K\t-\u0011!B:uCJ$\u0018\u0001B:u_B\fqaY8naV$X\r\u0006\u0003\u0003\\\t%\u0004#B*\u0002\u0016\tu\u0003#\u0002B0\u0005K\u0012VB\u0001B1\u0015\r\u0011\u0019GR\u0001\u0004e\u0012$\u0017\u0002\u0002B4\u0005C\u00121A\u0015#E\u0011\u001d\u0011Y\u0007\u000ba\u0001\u0003o\u000b\u0011B^1mS\u0012$\u0016.\\3\u0002\u001b\rdW-\u0019:NKR\fG-\u0019;b)\u0011\t9M!\u001d\t\u000f\u0005=\u0017\u00061\u0001\u00028\u0006aa-\u001b8e\u001d\u0016<h)\u001b7fgR!\u0011Q\u0018B<\u0011\u001d\u0011IH\u000ba\u0001\u0003K\f1bY;se\u0016tG\u000fV5nK\u0006I\u0011n\u001d(fo\u001aKG.\u001a\u000b\t\u0003\u0013\u0011yH!#\u0003\f\"9!\u0011Q\u0016A\u0002\t\r\u0015A\u00034jY\u0016\u001cF/\u0019;vgB\u0019QP!\"\n\u0007\t\u001deP\u0001\u0006GS2,7\u000b^1ukNDqA!\u001f,\u0001\u0004\t)\u000fC\u0004\u0003\u000e.\u0002\r!!:\u0002-5|G\rV5nK&;gn\u001c:f)\"\u0014Xm\u001d5pY\u0012\f!BZ5mKN$vN\u0015#E)\u0011\u0011iFa%\t\u000f\tUE\u00061\u0001\u0003\u0018\u0006)a-\u001b7fgB)!\u0011\u0014BR[:!!1\u0014BP\u001d\r\u0001(QT\u0005\u0002+&\u0019!\u0011\u0015+\u0002\u000fA\f7m[1hK&!!Q\u0015BT\u0005\r\u0019V-\u001d\u0006\u0004\u0005C#\u0016!\u00043je\u0016\u001cGo\u001c:z!\u0006$\b.A\u0003sKN,G/\u0001\u0006sK\u0006$wJ\u00196fGR$B!a2\u00032\"9!1\u0017\u0019A\u0002\tU\u0016aA8jgB!!q\u0017Ba\u001b\t\u0011IL\u0003\u0003\u0003<\nu\u0016AA5p\u0015\t\u0011y,\u0001\u0003kCZ\f\u0017\u0002\u0002Bb\u0005s\u0013\u0011c\u00142kK\u000e$\u0018J\u001c9viN#(/Z1nQ\u0015\u0001$q\u0019Bj!\u0015\u0019&\u0011\u001aBg\u0013\r\u0011Y\r\u0016\u0002\u0007i\"\u0014xn^:\u0011\t\t]&qZ\u0005\u0005\u0005#\u0014ILA\u0006J\u001f\u0016C8-\u001a9uS>t7E\u0001Bg\u0003A1\u0015\u000e\\3J]B,H\u000fR*ue\u0016\fW\u000e\u0005\u0002PsM)\u0011Ha7\u0003bB\u00191K!8\n\u0007\t}GK\u0001\u0004B]f\u0014VM\u001a\t\u0005\u0005o\u0013\u0019/\u0003\u0003\u0003f\ne&\u0001D*fe&\fG.\u001b>bE2,GC\u0001Bl\u00035!WMZ1vYR4\u0015\u000e\u001c;feR!\u0011\u0011\u0002Bw\u0011\u0019\u0011yo\u000fa\u0001y\u0006!\u0001/\u0019;i\u0003u\u0019\u0017\r\\2vY\u0006$XMT;n\u0005\u0006$8\r[3t)>\u0014V-\\3nE\u0016\u0014HCBAy\u0005k\u0014I\u0010C\u0004\u0003xr\u0002\r!! \u0002\u001b\t\fGo\u00195EkJ\fG/[8o\u0011\u001d\tI\b\u0010a\u0001\u0003{\n1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\u001aT\u0003\u0003B\u0000\u0007+\u00199b!\u0007\u0016\u0005\r\u0005!fA=\u0004\u0004-\u00121Q\u0001\t\u0005\u0007\u000f\u0019\t\"\u0004\u0002\u0004\n)!11BB\u0007\u0003%)hn\u00195fG.,GMC\u0002\u0004\u0010Q\u000b!\"\u00198o_R\fG/[8o\u0013\u0011\u0019\u0019b!\u0003\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\rB\u0003\\{\t\u0007Q\fB\u0003g{\t\u0007Q\fB\u0004\u0002>u\u0012\raa\u0007\u0012\u0007y\u001bi\u0002\u0005\u0005\u0002D\u0005%3qDB\u0011!\rI6Q\u0003\t\u00043\u000e]\u0011a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$C'\u0006\u0005\u0004(\r-2QFB\u0018+\t\u0019IC\u000b\u0003\u0002\n\r\rA!B.?\u0005\u0004iF!\u00024?\u0005\u0004iFaBA\u001f}\t\u00071\u0011G\t\u0004=\u000eM\u0002\u0003CA\"\u0003\u0013\u001a)da\u000e\u0011\u0007e\u001bY\u0003E\u0002Z\u0007[\t1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012*T\u0003CB\u001f\u0007\u0003\u001a\u0019e!\u0012\u0016\u0005\r}\"\u0006BA\n\u0007\u0007!QaW C\u0002u#QAZ C\u0002u#q!!\u0010@\u0005\u0004\u00199%E\u0002_\u0007\u0013\u0002\u0002\"a\u0011\u0002J\r-3Q\n\t\u00043\u000e\u0005\u0003cA-\u0004D\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u001111\u000b\t\u0005\u0007+\u001aY&\u0004\u0002\u0004X)!1\u0011\fB_\u0003\u0011a\u0017M\\4\n\t\ru3q\u000b\u0002\u0007\u001f\nTWm\u0019;"
)
public class FileInputDStream extends InputDStream {
   private final String directory;
   private final Function1 filter;
   private final ClassTag km;
   private final ClassTag vm;
   private final ClassTag fm;
   private final Option serializableConfOpt;
   private final Duration minRememberDurationS;
   private final FileInputDStreamCheckpointData checkpointData;
   private final long initialModTimeIgnoreThreshold;
   private final int numBatchesToRemember;
   private final Duration durationToRemember;
   private transient HashMap batchTimeToSelectedFiles;
   private transient HashSet org$apache$spark$streaming$dstream$FileInputDStream$$recentlySelectedFiles;
   private transient long lastNewFileFindingTime;
   private transient Path _path;
   private transient FileSystem _fs;

   public static Option $lessinit$greater$default$5() {
      return FileInputDStream$.MODULE$.$lessinit$greater$default$5();
   }

   public static boolean $lessinit$greater$default$4() {
      return FileInputDStream$.MODULE$.$lessinit$greater$default$4();
   }

   public static Function1 $lessinit$greater$default$3() {
      return FileInputDStream$.MODULE$.$lessinit$greater$default$3();
   }

   public static int calculateNumBatchesToRemember(final Duration batchDuration, final Duration minRememberDurationS) {
      return FileInputDStream$.MODULE$.calculateNumBatchesToRemember(batchDuration, minRememberDurationS);
   }

   public static boolean defaultFilter(final Path path) {
      return FileInputDStream$.MODULE$.defaultFilter(path);
   }

   private Option serializableConfOpt() {
      return this.serializableConfOpt;
   }

   private Duration minRememberDurationS() {
      return this.minRememberDurationS;
   }

   private Clock clock() {
      return this.ssc().scheduler().clock();
   }

   public FileInputDStreamCheckpointData checkpointData() {
      return this.checkpointData;
   }

   private long initialModTimeIgnoreThreshold() {
      return this.initialModTimeIgnoreThreshold;
   }

   private int numBatchesToRemember() {
      return this.numBatchesToRemember;
   }

   private Duration durationToRemember() {
      return this.durationToRemember;
   }

   public HashMap batchTimeToSelectedFiles() {
      return this.batchTimeToSelectedFiles;
   }

   public void batchTimeToSelectedFiles_$eq(final HashMap x$1) {
      this.batchTimeToSelectedFiles = x$1;
   }

   public HashSet org$apache$spark$streaming$dstream$FileInputDStream$$recentlySelectedFiles() {
      return this.org$apache$spark$streaming$dstream$FileInputDStream$$recentlySelectedFiles;
   }

   private void recentlySelectedFiles_$eq(final HashSet x$1) {
      this.org$apache$spark$streaming$dstream$FileInputDStream$$recentlySelectedFiles = x$1;
   }

   private long lastNewFileFindingTime() {
      return this.lastNewFileFindingTime;
   }

   private void lastNewFileFindingTime_$eq(final long x$1) {
      this.lastNewFileFindingTime = x$1;
   }

   private Path _path() {
      return this._path;
   }

   private void _path_$eq(final Path x$1) {
      this._path = x$1;
   }

   private FileSystem _fs() {
      return this._fs;
   }

   private void _fs_$eq(final FileSystem x$1) {
      this._fs = x$1;
   }

   public void start() {
   }

   public void stop() {
   }

   public Option compute(final Time validTime) {
      String[] newFiles = this.findNewFiles(validTime.milliseconds());
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"New files at time ", ":\\n"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BATCH_TIMESTAMP..MODULE$, validTime)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILE_NAME..MODULE$, scala.Predef..MODULE$.wrapRefArray((Object[])newFiles).mkString("\n"))}))))));
      synchronized(this.batchTimeToSelectedFiles()){}

      try {
         HashMap var10000 = (HashMap)this.batchTimeToSelectedFiles().$plus$eq(new Tuple2(validTime, newFiles));
      } catch (Throwable var8) {
         throw var8;
      }

      this.org$apache$spark$streaming$dstream$FileInputDStream$$recentlySelectedFiles().$plus$plus$eq(scala.Predef..MODULE$.wrapRefArray((Object[])newFiles));
      Some rdds = new Some(this.org$apache$spark$streaming$dstream$FileInputDStream$$filesToRDD(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(newFiles).toImmutableArraySeq()));
      Map metadata = (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("files"), scala.Predef..MODULE$.wrapRefArray((Object[])newFiles).toList()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(StreamInputInfo$.MODULE$.METADATA_KEY_DESCRIPTION()), scala.Predef..MODULE$.wrapRefArray((Object[])newFiles).mkString("\n"))})));
      StreamInputInfo inputInfo = new StreamInputInfo(this.id(), 0L, metadata);
      this.ssc().scheduler().inputInfoTracker().reportInfo(validTime, inputInfo);
      return rdds;
   }

   public void clearMetadata(final Time time) {
      super.clearMetadata(time);
      synchronized(this.batchTimeToSelectedFiles()){}

      try {
         HashMap oldFiles = (HashMap)this.batchTimeToSelectedFiles().filter((x$2) -> BoxesRunTime.boxToBoolean($anonfun$clearMetadata$1(this, time, x$2)));
         this.batchTimeToSelectedFiles().$minus$minus$eq(oldFiles.keys());
         this.org$apache$spark$streaming$dstream$FileInputDStream$$recentlySelectedFiles().$minus$minus$eq((IterableOnce)oldFiles.values().flatten((xs) -> scala.Predef..MODULE$.wrapRefArray((Object[])xs)));
         this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Cleared ", " old files that were older "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.COUNT..MODULE$, BoxesRunTime.boxToInteger(oldFiles.size()))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"than ", ": "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIME..MODULE$, time.$minus(this.rememberDuration()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILES..MODULE$, oldFiles.keys().mkString(", "))}))))));
         this.logDebug(() -> {
            IterableOps var10000 = oldFiles.map((p) -> new Tuple2(p._1(), scala.Predef..MODULE$.wrapRefArray(p._2()).mkString(", ")));
            return "Cleared files are:\n" + var10000.mkString("\n");
         });
      } catch (Throwable var5) {
         throw var5;
      }

   }

   private String[] findNewFiles(final long currentTime) {
      String[] var10000;
      try {
         this.lastNewFileFindingTime_$eq(this.clock().getTimeMillis());
         long modTimeIgnoreThreshold = scala.math.package..MODULE$.max(this.initialModTimeIgnoreThreshold(), currentTime - this.durationToRemember().milliseconds());
         this.logDebug(() -> "Getting new files for time " + currentTime + ", ignoring files older than " + modTimeIgnoreThreshold);
         Path[] directories = (Path[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps(scala.Option..MODULE$.apply(this.fs().globStatus(this.directoryPath())).getOrElse(() -> (FileStatus[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(FileStatus.class)))), (x$3) -> BoxesRunTime.boxToBoolean($anonfun$findNewFiles$3(x$3)))), (x$4) -> x$4.getPath(), scala.reflect.ClassTag..MODULE$.apply(Path.class));
         String[] newFiles = (String[])scala.collection.ArrayOps..MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps((Object[])directories), (dir) -> (String[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.fs().listStatus(dir)), (x$5) -> BoxesRunTime.boxToBoolean($anonfun$findNewFiles$6(this, currentTime, modTimeIgnoreThreshold, x$5)))), (x$6) -> x$6.getPath().toString(), scala.reflect.ClassTag..MODULE$.apply(String.class)), (xs) -> scala.Predef..MODULE$.wrapRefArray((Object[])xs), scala.reflect.ClassTag..MODULE$.apply(String.class));
         long timeTaken = this.clock().getTimeMillis() - this.lastNewFileFindingTime();
         this.logDebug(() -> "Finding new files took " + timeTaken + " ms");
         if (timeTaken > this.slideDuration().milliseconds()) {
            this.logWarning(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Time taken to find new files ", " exceeds the "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ELAPSED_TIME..MODULE$, BoxesRunTime.boxToLong(timeTaken))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"batch size. Consider increasing the batch size or reducing the number of files in "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"the monitored directories."})))).log(scala.collection.immutable.Nil..MODULE$))));
         }

         var10000 = newFiles;
      } catch (FileNotFoundException var11) {
         this.logWarning(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"No directory to scan: ", ":"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, this.directoryPath())})))), var11);
         var10000 = (String[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(String.class));
      } catch (Exception var12) {
         this.logWarning(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error finding new files under ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, this.directoryPath())})))), var12);
         this.reset();
         var10000 = (String[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(String.class));
      }

      return var10000;
   }

   private boolean isNewFile(final FileStatus fileStatus, final long currentTime, final long modTimeIgnoreThreshold) {
      Path path = fileStatus.getPath();
      String pathStr = path.toString();
      if (!BoxesRunTime.unboxToBoolean(this.filter.apply(path))) {
         this.logDebug(() -> pathStr + " rejected by filter");
         return false;
      } else {
         long modTime = fileStatus.getModificationTime();
         if (modTime <= modTimeIgnoreThreshold) {
            this.logDebug(() -> pathStr + " ignored as mod time " + modTime + " <= ignore time " + modTimeIgnoreThreshold);
            return false;
         } else if (modTime > currentTime) {
            this.logDebug(() -> pathStr + " not selected as mod time " + modTime + " > current time " + currentTime);
            return false;
         } else if (this.org$apache$spark$streaming$dstream$FileInputDStream$$recentlySelectedFiles().contains(pathStr)) {
            this.logDebug(() -> pathStr + " already considered");
            return false;
         } else {
            this.logDebug(() -> pathStr + " accepted with mod time " + modTime);
            return true;
         }
      }
   }

   public RDD org$apache$spark$streaming$dstream$FileInputDStream$$filesToRDD(final Seq files) {
      Seq fileRDDs = (Seq)files.map((file) -> {
         Option var4 = this.serializableConfOpt().map((x$7) -> x$7.value());
         RDD var10000;
         if (var4 instanceof Some var5) {
            Configuration config = (Configuration)var5.value();
            var10000 = this.context().sparkContext().newAPIHadoopFile(file, this.fm.runtimeClass(), this.km.runtimeClass(), this.vm.runtimeClass(), config);
         } else {
            if (!scala.None..MODULE$.equals(var4)) {
               throw new MatchError(var4);
            }

            var10000 = this.context().sparkContext().newAPIHadoopFile(file, this.km, this.vm, this.fm);
         }

         RDD rdd = var10000;
         if (scala.collection.ArrayOps..MODULE$.isEmpty$extension(scala.Predef..MODULE$.refArrayOps((Object[])rdd.partitions()))) {
            this.logError(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"File ", " has no data in it. Spark Streaming can only ingest "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, file)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"files that have been \"moved\" to the directory assigned to the file stream. "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Refer to the streaming programming guide for more details."})))).log(scala.collection.immutable.Nil..MODULE$))));
         }

         return rdd;
      });
      return new UnionRDD(this.context().sparkContext(), fileRDDs, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
   }

   private Path directoryPath() {
      if (this._path() == null) {
         this._path_$eq(new Path(this.directory));
      }

      return this._path();
   }

   private FileSystem fs() {
      if (this._fs() == null) {
         this._fs_$eq(this.directoryPath().getFileSystem(this.ssc().sparkContext().hadoopConfiguration()));
      }

      return this._fs();
   }

   private void reset() {
      this._fs_$eq((FileSystem)null);
   }

   private void readObject(final ObjectInputStream ois) throws IOException {
      org.apache.spark.util.Utils..MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         this.logDebug(() -> this.getClass().getSimpleName() + ".readObject used");
         ois.defaultReadObject();
         this.generatedRDDs_$eq(new HashMap());
         this.batchTimeToSelectedFiles_$eq(new HashMap());
         this.recentlySelectedFiles_$eq(new HashSet());
      });
   }

   // $FF: synthetic method
   public static final boolean $anonfun$clearMetadata$1(final FileInputDStream $this, final Time time$1, final Tuple2 x$2) {
      return ((Time)x$2._1()).$less(time$1.$minus($this.rememberDuration()));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$findNewFiles$3(final FileStatus x$3) {
      return x$3.isDirectory();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$findNewFiles$6(final FileInputDStream $this, final long currentTime$1, final long modTimeIgnoreThreshold$1, final FileStatus x$5) {
      return $this.isNewFile(x$5, currentTime$1, modTimeIgnoreThreshold$1);
   }

   public FileInputDStream(final StreamingContext _ssc, final String directory, final Function1 filter, final boolean newFilesOnly, final Option conf, final ClassTag km, final ClassTag vm, final ClassTag fm) {
      super(_ssc, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      this.directory = directory;
      this.filter = filter;
      this.km = km;
      this.vm = vm;
      this.fm = fm;
      this.serializableConfOpt = conf.map((x$1) -> new SerializableConfiguration(x$1));
      this.minRememberDurationS = Seconds$.MODULE$.apply(this.ssc().conf().getTimeAsSeconds("spark.streaming.fileStream.minRememberDuration", this.ssc().conf().get("spark.streaming.minRememberDuration", "60s")));
      this.checkpointData = new FileInputDStreamCheckpointData();
      this.initialModTimeIgnoreThreshold = newFilesOnly ? this.clock().getTimeMillis() : 0L;
      this.numBatchesToRemember = FileInputDStream$.MODULE$.calculateNumBatchesToRemember(this.slideDuration(), this.minRememberDurationS());
      this.durationToRemember = this.slideDuration().$times(this.numBatchesToRemember());
      this.remember(this.durationToRemember());
      this.batchTimeToSelectedFiles = new HashMap();
      this.org$apache$spark$streaming$dstream$FileInputDStream$$recentlySelectedFiles = new HashSet();
      this.lastNewFileFindingTime = 0L;
      this._path = null;
      this._fs = null;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class FileInputDStreamCheckpointData extends DStreamCheckpointData {
      // $FF: synthetic field
      public final FileInputDStream $outer;

      private HashMap hadoopFiles() {
         return this.data();
      }

      public void update(final Time time) {
         this.hadoopFiles().clear();
         synchronized(this.org$apache$spark$streaming$dstream$FileInputDStream$FileInputDStreamCheckpointData$$$outer().batchTimeToSelectedFiles()){}

         try {
            HashMap var10000 = (HashMap)this.hadoopFiles().$plus$plus$eq(this.org$apache$spark$streaming$dstream$FileInputDStream$FileInputDStreamCheckpointData$$$outer().batchTimeToSelectedFiles());
         } catch (Throwable var4) {
            throw var4;
         }

      }

      public void cleanup(final Time time) {
      }

      public void restore() {
         ((IterableOnceOps)this.hadoopFiles().toSeq().sortBy((x$8) -> (Time)x$8._1(), Time$.MODULE$.ordering())).foreach((x0$1) -> {
            if (x0$1 != null) {
               Time t = (Time)x0$1._1();
               String[] f = (String[])x0$1._2();
               this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Restoring files for time ", " - "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIME..MODULE$, t)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILES..MODULE$, scala.Predef..MODULE$.wrapRefArray((Object[])f).mkString("[", ", ", "]"))}))))));
               synchronized(this.org$apache$spark$streaming$dstream$FileInputDStream$FileInputDStreamCheckpointData$$$outer().batchTimeToSelectedFiles()){}

               try {
                  HashMap var10000 = (HashMap)this.org$apache$spark$streaming$dstream$FileInputDStream$FileInputDStreamCheckpointData$$$outer().batchTimeToSelectedFiles().$plus$eq(new Tuple2(t, f));
               } catch (Throwable var8) {
                  throw var8;
               }

               this.org$apache$spark$streaming$dstream$FileInputDStream$FileInputDStreamCheckpointData$$$outer().org$apache$spark$streaming$dstream$FileInputDStream$$recentlySelectedFiles().$plus$plus$eq(scala.Predef..MODULE$.wrapRefArray((Object[])f));
               return (HashMap)this.org$apache$spark$streaming$dstream$FileInputDStream$FileInputDStreamCheckpointData$$$outer().generatedRDDs().$plus$eq(new Tuple2(t, this.org$apache$spark$streaming$dstream$FileInputDStream$FileInputDStreamCheckpointData$$$outer().org$apache$spark$streaming$dstream$FileInputDStream$$filesToRDD(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(f).toImmutableArraySeq())));
            } else {
               throw new MatchError(x0$1);
            }
         });
      }

      public String toString() {
         int var10000 = this.hadoopFiles().size();
         return "[\n" + var10000 + " file sets\n" + this.hadoopFiles().map((p) -> new Tuple2(p._1(), scala.Predef..MODULE$.wrapRefArray(p._2()).mkString(", "))).mkString("\n") + "\n]";
      }

      // $FF: synthetic method
      public FileInputDStream org$apache$spark$streaming$dstream$FileInputDStream$FileInputDStreamCheckpointData$$$outer() {
         return this.$outer;
      }

      public FileInputDStreamCheckpointData() {
         if (FileInputDStream.this == null) {
            throw null;
         } else {
            this.$outer = FileInputDStream.this;
            super(FileInputDStream.this, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
