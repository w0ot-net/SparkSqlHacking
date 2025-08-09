package org.apache.spark.streaming.ui;

import java.lang.invoke.SerializedLambda;
import java.util.LinkedHashMap;
import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.apache.spark.scheduler.SparkListener;
import org.apache.spark.scheduler.SparkListenerJobStart;
import org.apache.spark.streaming.StreamingConf$;
import org.apache.spark.streaming.StreamingContext;
import org.apache.spark.streaming.Time;
import org.apache.spark.streaming.Time$;
import org.apache.spark.streaming.scheduler.JobScheduler$;
import org.apache.spark.streaming.scheduler.ReceiverInfo;
import org.apache.spark.streaming.scheduler.StreamInputInfo;
import org.apache.spark.streaming.scheduler.StreamingListener;
import org.apache.spark.streaming.scheduler.StreamingListenerBatchCompleted;
import org.apache.spark.streaming.scheduler.StreamingListenerBatchStarted;
import org.apache.spark.streaming.scheduler.StreamingListenerBatchSubmitted;
import org.apache.spark.streaming.scheduler.StreamingListenerOutputOperationCompleted;
import org.apache.spark.streaming.scheduler.StreamingListenerOutputOperationStarted;
import org.apache.spark.streaming.scheduler.StreamingListenerReceiverError;
import org.apache.spark.streaming.scheduler.StreamingListenerReceiverStarted;
import org.apache.spark.streaming.scheduler.StreamingListenerReceiverStopped;
import org.apache.spark.streaming.scheduler.StreamingListenerStreamingStarted;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.collection.Iterable;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.SeqOps;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.Queue;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\tmf!B!C\u0001\u0019c\u0005\u0002\u0003-\u0001\u0005\u0003\u0005\u000b\u0011\u0002.\t\u000by\u0003A\u0011A0\t\u000f\r\u0004!\u0019!C\u0005I\"1Q\u000f\u0001Q\u0001\n\u0015DqA\u001e\u0001C\u0002\u0013%A\r\u0003\u0004x\u0001\u0001\u0006I!\u001a\u0005\bq\u0002\u0011\r\u0011\"\u0003z\u0011\u0019i\b\u0001)A\u0005u\"9a\u0010\u0001b\u0001\n\u0013y\b\u0002CA\u0005\u0001\u0001\u0006I!!\u0001\t\u0013\u0005-\u0001\u00011A\u0005\n\u00055\u0001\"CA\u000b\u0001\u0001\u0007I\u0011BA\f\u0011!\t\u0019\u0003\u0001Q!\n\u0005=\u0001\"CA\u0013\u0001\u0001\u0007I\u0011BA\u0007\u0011%\t9\u0003\u0001a\u0001\n\u0013\tI\u0003\u0003\u0005\u0002.\u0001\u0001\u000b\u0015BA\b\u0011%\ty\u0003\u0001a\u0001\n\u0013\ti\u0001C\u0005\u00022\u0001\u0001\r\u0011\"\u0003\u00024!A\u0011q\u0007\u0001!B\u0013\ty\u0001C\u0005\u0002:\u0001\u0011\r\u0011\"\u0003\u0002<!A\u0011Q\t\u0001!\u0002\u0013\ti\u0004C\u0005\u0002H\u0001\u0001\r\u0011\"\u0003\u0002\u000e!I\u0011\u0011\n\u0001A\u0002\u0013%\u00111\n\u0005\t\u0003\u001f\u0002\u0001\u0015)\u0003\u0002\u0010!Q\u0011\u0011\u000b\u0001C\u0002\u0013\u0005!)a\u0015\t\u0011\u0005]\u0004\u0001)A\u0005\u0003+B\u0011\"!\u001f\u0001\u0005\u0004%\t!!\u0004\t\u0011\u0005m\u0004\u0001)A\u0005\u0003\u001fAq!! \u0001\t\u0003\ny\bC\u0004\u0002\f\u0002!\t%!$\t\u000f\u0005e\u0005\u0001\"\u0011\u0002\u001c\"9\u0011q\u0015\u0001\u0005B\u0005%\u0006bBA[\u0001\u0011\u0005\u0013q\u0017\u0005\b\u0003\u0007\u0004A\u0011IAc\u0011\u001d\t\t\u000e\u0001C!\u0003'Dq!a8\u0001\t\u0003\n\t\u000fC\u0004\u0002n\u0002!\t%a<\t\u000f\u0005m\b\u0001\"\u0011\u0002~\"9!\u0011\u0002\u0001\u0005\n\t-\u0001b\u0002B\u0012\u0001\u0011\u0005\u0011Q\u0002\u0005\u0007\u0005K\u0001A\u0011A@\t\r\t\u001d\u0002\u0001\"\u0001\u0000\u0011\u0019\u0011I\u0003\u0001C\u0001\u007f\"9!1\u0006\u0001\u0005\u0002\u00055\u0001b\u0002B\u0017\u0001\u0011\u0005\u0011Q\u0002\u0005\b\u0005_\u0001A\u0011AA\u0007\u0011\u001d\u0011\t\u0004\u0001C\u0001\u0003\u001bAqAa\r\u0001\t\u0003\u0011)\u0004C\u0004\u0003P\u0001!\tA!\u000e\t\u000f\tE\u0003\u0001\"\u0001\u00036!9!1\u000b\u0001\u0005\u0002\tU\u0003b\u0002B7\u0001\u0011\u0005!q\u000e\u0005\b\u0005g\u0002A\u0011\u0001B;\u0011\u001d\u00119\t\u0001C\u0001\u0005\u0013CqA!$\u0001\t\u0003\u0011y\tC\u0004\u0003\u0018\u0002!\tA!'\t\u000f\tu\u0005\u0001\"\u0001\u0003\u001a\"9!q\u0014\u0001\u0005\u0002\tU\u0002b\u0002BQ\u0001\u0011\u0005!1U\u0004\t\u0005S\u0013\u0005\u0012\u0001$\u0003,\u001a9\u0011I\u0011E\u0001\r\n5\u0006B\u00020>\t\u0003\u0011),\u0002\u0004\u00038v\u0002\u0011\u0011A\u0003\u0007\u0005sk\u0004!!\u0001\u00039M#(/Z1nS:<'j\u001c2Qe><'/Z:t\u0019&\u001cH/\u001a8fe*\u00111\tR\u0001\u0003k&T!!\u0012$\u0002\u0013M$(/Z1nS:<'BA$I\u0003\u0015\u0019\b/\u0019:l\u0015\tI%*\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u0017\u0006\u0019qN]4\u0014\u0007\u0001i5\u000b\u0005\u0002O#6\tqJ\u0003\u0002Q\r\u0006I1o\u00195fIVdWM]\u0005\u0003%>\u0013Qb\u00159be.d\u0015n\u001d;f]\u0016\u0014\bC\u0001+W\u001b\u0005)&B\u0001)E\u0013\t9VKA\tTiJ,\u0017-\\5oO2K7\u000f^3oKJ\f1a]:d\u0007\u0001\u0001\"a\u0017/\u000e\u0003\u0011K!!\u0018#\u0003!M#(/Z1nS:<7i\u001c8uKb$\u0018A\u0002\u001fj]&$h\b\u0006\u0002aEB\u0011\u0011\rA\u0007\u0002\u0005\")\u0001L\u0001a\u00015\u0006\u0011r/Y5uS:<')\u0019;dQVKE)\u0019;b+\u0005)\u0007\u0003\u00024n_Jl\u0011a\u001a\u0006\u0003Q&\fq!\\;uC\ndWM\u0003\u0002kW\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u00031\fQa]2bY\u0006L!A\\4\u0003\u000f!\u000b7\u000f['baB\u00111\f]\u0005\u0003c\u0012\u0013A\u0001V5nKB\u0011\u0011m]\u0005\u0003i\n\u00131BQ1uG\",\u0016\nR1uC\u0006\u0019r/Y5uS:<')\u0019;dQVKE)\u0019;bA\u0005\u0011\"/\u001e8oS:<')\u0019;dQVKE)\u0019;b\u0003M\u0011XO\u001c8j]\u001e\u0014\u0015\r^2i+&#\u0015\r^1!\u0003Q\u0019w.\u001c9mKR,GMQ1uG\",\u0016\nR1uCV\t!\u0010E\u0002gwJL!\u0001`4\u0003\u000bE+X-^3\u0002+\r|W\u000e\u001d7fi\u0016$')\u0019;dQVKE)\u0019;bA\u0005\u0001\"-\u0019;dQVKE)\u0019;b\u0019&l\u0017\u000e^\u000b\u0003\u0003\u0003\u0001B!a\u0001\u0002\u00065\t1.C\u0002\u0002\b-\u00141!\u00138u\u0003E\u0011\u0017\r^2i+&#\u0015\r^1MS6LG\u000fI\u0001\u0016i>$\u0018\r\\\"p[BdW\r^3e\u0005\u0006$8\r[3t+\t\ty\u0001\u0005\u0003\u0002\u0004\u0005E\u0011bAA\nW\n!Aj\u001c8h\u0003e!x\u000e^1m\u0007>l\u0007\u000f\\3uK\u0012\u0014\u0015\r^2iKN|F%Z9\u0015\t\u0005e\u0011q\u0004\t\u0005\u0003\u0007\tY\"C\u0002\u0002\u001e-\u0014A!\u00168ji\"I\u0011\u0011\u0005\u0007\u0002\u0002\u0003\u0007\u0011qB\u0001\u0004q\u0012\n\u0014A\u0006;pi\u0006d7i\\7qY\u0016$X\r\u001a\"bi\u000eDWm\u001d\u0011\u0002)Q|G/\u00197SK\u000e,\u0017N^3e%\u0016\u001cwN\u001d3t\u0003a!x\u000e^1m%\u0016\u001cW-\u001b<fIJ+7m\u001c:eg~#S-\u001d\u000b\u0005\u00033\tY\u0003C\u0005\u0002\"=\t\t\u00111\u0001\u0002\u0010\u0005)Bo\u001c;bYJ+7-Z5wK\u0012\u0014VmY8sIN\u0004\u0013!\u0006;pi\u0006d\u0007K]8dKN\u001cX\r\u001a*fG>\u0014Hm]\u0001\u001ai>$\u0018\r\u001c)s_\u000e,7o]3e%\u0016\u001cwN\u001d3t?\u0012*\u0017\u000f\u0006\u0003\u0002\u001a\u0005U\u0002\"CA\u0011%\u0005\u0005\t\u0019AA\b\u0003Y!x\u000e^1m!J|7-Z:tK\u0012\u0014VmY8sIN\u0004\u0013!\u0004:fG\u0016Lg/\u001a:J]\u001a|7/\u0006\u0002\u0002>A1a-\\A\u0001\u0003\u007f\u00012\u0001VA!\u0013\r\t\u0019%\u0016\u0002\r%\u0016\u001cW-\u001b<fe&sgm\\\u0001\u000fe\u0016\u001cW-\u001b<fe&sgm\\:!\u0003)y6\u000f^1siRKW.Z\u0001\u000f?N$\u0018M\u001d;US6,w\fJ3r)\u0011\tI\"!\u0014\t\u0013\u0005\u0005r#!AA\u0002\u0005=\u0011aC0ti\u0006\u0014H\u000fV5nK\u0002\n1EY1uG\"$\u0016.\\3U_>+H\u000f];u\u001fBLEm\u00159be.TuNY%e!\u0006L'/\u0006\u0002\u0002VA9\u0011qKA1_\u0006\u0015TBAA-\u0015\u0011\tY&!\u0018\u0002\tU$\u0018\u000e\u001c\u0006\u0003\u0003?\nAA[1wC&!\u00111MA-\u00055a\u0015N\\6fI\"\u000b7\u000f['baB1\u0011qMA7\u0003cj!!!\u001b\u000b\t\u0005-\u0014\u0011L\u0001\u000bG>t7-\u001e:sK:$\u0018\u0002BA8\u0003S\u0012QcQ8oGV\u0014(/\u001a8u\u0019&t7.\u001a3Rk\u0016,X\rE\u0002b\u0003gJ1!!\u001eC\u0005]yU\u000f\u001e9vi>\u0003\u0018\nZ!oIN\u0003\u0018M]6K_\nLE-\u0001\u0013cCR\u001c\u0007\u000eV5nKR{w*\u001e;qkR|\u0005/\u00133Ta\u0006\u00148NS8c\u0013\u0012\u0004\u0016-\u001b:!\u00035\u0011\u0017\r^2i\tV\u0014\u0018\r^5p]\u0006q!-\u0019;dQ\u0012+(/\u0019;j_:\u0004\u0013AE8o'R\u0014X-Y7j]\u001e\u001cF/\u0019:uK\u0012$B!!\u0007\u0002\u0002\"9\u00111Q\u000fA\u0002\u0005\u0015\u0015\u0001E:ue\u0016\fW.\u001b8h'R\f'\u000f^3e!\r!\u0016qQ\u0005\u0004\u0003\u0013+&!I*ue\u0016\fW.\u001b8h\u0019&\u001cH/\u001a8feN#(/Z1nS:<7\u000b^1si\u0016$\u0017!E8o%\u0016\u001cW-\u001b<feN#\u0018M\u001d;fIR!\u0011\u0011DAH\u0011\u001d\t\tJ\ba\u0001\u0003'\u000bqB]3dK&4XM]*uCJ$X\r\u001a\t\u0004)\u0006U\u0015bAAL+\n\u00013\u000b\u001e:fC6Lgn\u001a'jgR,g.\u001a:SK\u000e,\u0017N^3s'R\f'\u000f^3e\u0003=ygNU3dK&4XM]#se>\u0014H\u0003BA\r\u0003;Cq!a( \u0001\u0004\t\t+A\u0007sK\u000e,\u0017N^3s\u000bJ\u0014xN\u001d\t\u0004)\u0006\r\u0016bAAS+\nq2\u000b\u001e:fC6Lgn\u001a'jgR,g.\u001a:SK\u000e,\u0017N^3s\u000bJ\u0014xN]\u0001\u0012_:\u0014VmY3jm\u0016\u00148\u000b^8qa\u0016$G\u0003BA\r\u0003WCq!!,!\u0001\u0004\ty+A\bsK\u000e,\u0017N^3s'R|\u0007\u000f]3e!\r!\u0016\u0011W\u0005\u0004\u0003g+&\u0001I*ue\u0016\fW.\u001b8h\u0019&\u001cH/\u001a8feJ+7-Z5wKJ\u001cFo\u001c9qK\u0012\f\u0001c\u001c8CCR\u001c\u0007nU;c[&$H/\u001a3\u0015\t\u0005e\u0011\u0011\u0018\u0005\b\u0003w\u000b\u0003\u0019AA_\u00039\u0011\u0017\r^2i'V\u0014W.\u001b;uK\u0012\u00042\u0001VA`\u0013\r\t\t-\u0016\u0002 'R\u0014X-Y7j]\u001ed\u0015n\u001d;f]\u0016\u0014()\u0019;dQN+(-\\5ui\u0016$\u0017AD8o\u0005\u0006$8\r[*uCJ$X\r\u001a\u000b\u0005\u00033\t9\rC\u0004\u0002J\n\u0002\r!a3\u0002\u0019\t\fGo\u00195Ti\u0006\u0014H/\u001a3\u0011\u0007Q\u000bi-C\u0002\u0002PV\u0013Qd\u0015;sK\u0006l\u0017N\\4MSN$XM\\3s\u0005\u0006$8\r[*uCJ$X\rZ\u0001\u0011_:\u0014\u0015\r^2i\u0007>l\u0007\u000f\\3uK\u0012$B!!\u0007\u0002V\"9\u0011q[\u0012A\u0002\u0005e\u0017A\u00042bi\u000eD7i\\7qY\u0016$X\r\u001a\t\u0004)\u0006m\u0017bAAo+\ny2\u000b\u001e:fC6Lgn\u001a'jgR,g.\u001a:CCR\u001c\u0007nQ8na2,G/\u001a3\u00021=tw*\u001e;qkR|\u0005/\u001a:bi&|gn\u0015;beR,G\r\u0006\u0003\u0002\u001a\u0005\r\bbBAsI\u0001\u0007\u0011q]\u0001\u0017_V$\b/\u001e;Pa\u0016\u0014\u0018\r^5p]N#\u0018M\u001d;fIB\u0019A+!;\n\u0007\u0005-XKA\u0014TiJ,\u0017-\\5oO2K7\u000f^3oKJ|U\u000f\u001e9vi>\u0003XM]1uS>t7\u000b^1si\u0016$\u0017AG8o\u001fV$\b/\u001e;Pa\u0016\u0014\u0018\r^5p]\u000e{W\u000e\u001d7fi\u0016$G\u0003BA\r\u0003cDq!a=&\u0001\u0004\t)0\u0001\rpkR\u0004X\u000f^(qKJ\fG/[8o\u0007>l\u0007\u000f\\3uK\u0012\u00042\u0001VA|\u0013\r\tI0\u0016\u0002*'R\u0014X-Y7j]\u001ed\u0015n\u001d;f]\u0016\u0014x*\u001e;qkR|\u0005/\u001a:bi&|gnQ8na2,G/\u001a3\u0002\u0015=t'j\u001c2Ti\u0006\u0014H\u000f\u0006\u0003\u0002\u001a\u0005}\bb\u0002B\u0001M\u0001\u0007!1A\u0001\tU>\u00147\u000b^1siB\u0019aJ!\u0002\n\u0007\t\u001dqJA\u000bTa\u0006\u00148\u000eT5ti\u0016tWM\u001d&pEN#\u0018M\u001d;\u00023\u001d,GOQ1uG\"$\u0016.\\3B]\u0012|U\u000f\u001e9vi>\u0003\u0018\n\u001a\u000b\u0005\u0005\u001b\u0011I\u0002\u0005\u0004\u0002\u0004\t=!1C\u0005\u0004\u0005#Y'AB(qi&|g\u000eE\u0004\u0002\u0004\tUq.!\u0001\n\u0007\t]1N\u0001\u0004UkBdWM\r\u0005\b\u000579\u0003\u0019\u0001B\u000f\u0003)\u0001(o\u001c9feRLWm\u001d\t\u0005\u0003/\u0012y\"\u0003\u0003\u0003\"\u0005e#A\u0003)s_B,'\u000f^5fg\u0006I1\u000f^1siRKW.Z\u0001\r]Vl'+Z2fSZ,'o]\u0001\u0013]Vl\u0017i\u0019;jm\u0016\u0014VmY3jm\u0016\u00148/\u0001\u000bok6Le.Y2uSZ,'+Z2fSZ,'o]\u0001\u0019]VlGk\u001c;bY\u000e{W\u000e\u001d7fi\u0016$')\u0019;dQ\u0016\u001c\u0018a\u00068v[R{G/\u00197SK\u000e,\u0017N^3e%\u0016\u001cwN\u001d3t\u0003aqW/\u001c+pi\u0006d\u0007K]8dKN\u001cX\r\u001a*fG>\u0014Hm]\u0001\u0016]VlWK\u001c9s_\u000e,7o]3e\u0005\u0006$8\r[3t\u000399\u0018-\u001b;j]\u001e\u0014\u0015\r^2iKN,\"Aa\u000e\u0011\u000b\te\"\u0011\n:\u000f\t\tm\"Q\t\b\u0005\u0005{\u0011\u0019%\u0004\u0002\u0003@)\u0019!\u0011I-\u0002\rq\u0012xn\u001c;?\u0013\u0005a\u0017b\u0001B$W\u00069\u0001/Y2lC\u001e,\u0017\u0002\u0002B&\u0005\u001b\u00121aU3r\u0015\r\u00119e[\u0001\u000feVtg.\u001b8h\u0005\u0006$8\r[3t\u0003a\u0011X\r^1j]\u0016$7i\\7qY\u0016$X\r\u001a\"bi\u000eDWm]\u0001\u000bgR\u0014X-Y7OC6,G\u0003\u0002B,\u0005S\u0002b!a\u0001\u0003\u0010\te\u0003\u0003\u0002B.\u0005GrAA!\u0018\u0003`A\u0019!QH6\n\u0007\t\u00054.\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0005K\u00129G\u0001\u0004TiJLgn\u001a\u0006\u0004\u0005CZ\u0007b\u0002B6g\u0001\u0007\u0011\u0011A\u0001\tgR\u0014X-Y7JI\u0006I1\u000f\u001e:fC6LEm]\u000b\u0003\u0005c\u0002bA!\u000f\u0003J\u0005\u0005\u0011a\b:fG\u0016Lg/\u001a3SK\u000e|'\u000f\u001a*bi\u0016<\u0016\u000e\u001e5CCR\u001c\u0007\u000eV5nKV\u0011!q\u000f\t\t\u00057\u0012I(!\u0001\u0003~%!!1\u0010B4\u0005\ri\u0015\r\u001d\t\u0007\u0005s\u0011IEa \u0011\u0011\u0005\r!QCA\b\u0005\u0003\u0003B!a\u0001\u0003\u0004&\u0019!QQ6\u0003\r\u0011{WO\u00197f\u0003aa\u0017m\u001d;SK\u000e,\u0017N^3e\u0005\u0006$8\r\u001b*fG>\u0014Hm]\u000b\u0003\u0005\u0017\u0003\u0002Ba\u0017\u0003z\u0005\u0005\u0011qB\u0001\re\u0016\u001cW-\u001b<fe&sgm\u001c\u000b\u0005\u0005#\u0013\u0019\n\u0005\u0004\u0002\u0004\t=\u0011q\b\u0005\b\u0005+;\u0004\u0019AA\u0001\u0003)\u0011XmY3jm\u0016\u0014\u0018\nZ\u0001\u0013Y\u0006\u001cHoQ8na2,G/\u001a3CCR\u001c\u0007.\u0006\u0002\u0003\u001cB)\u00111\u0001B\be\u0006\tB.Y:u%\u0016\u001cW-\u001b<fI\n\u000bGo\u00195\u0002\u001fI,G/Y5oK\u0012\u0014\u0015\r^2iKN\fabZ3u\u0005\u0006$8\r[+J\t\u0006$\u0018\r\u0006\u0003\u0003\u001c\n\u0015\u0006B\u0002BTw\u0001\u0007q.A\u0005cCR\u001c\u0007\u000eV5nK\u0006a2\u000b\u001e:fC6Lgn\u001a&pEB\u0013xn\u001a:fgNd\u0015n\u001d;f]\u0016\u0014\bCA1>'\ri$q\u0016\t\u0005\u0003\u0007\u0011\t,C\u0002\u00034.\u0014a!\u00118z%\u00164GC\u0001BV\u0005)\u0019\u0006/\u0019:l\u0015>\u0014\u0017\n\u001a\u0002\u000b\u001fV$\b/\u001e;Pa&#\u0007"
)
public class StreamingJobProgressListener extends SparkListener implements StreamingListener {
   private final StreamingContext ssc;
   private final HashMap org$apache$spark$streaming$ui$StreamingJobProgressListener$$waitingBatchUIData;
   private final HashMap org$apache$spark$streaming$ui$StreamingJobProgressListener$$runningBatchUIData;
   private final Queue org$apache$spark$streaming$ui$StreamingJobProgressListener$$completedBatchUIData;
   private final int batchUIDataLimit;
   private long totalCompletedBatches;
   private long totalReceivedRecords;
   private long totalProcessedRecords;
   private final HashMap receiverInfos;
   private long _startTime;
   private final LinkedHashMap batchTimeToOutputOpIdSparkJobIdPair;
   private final long batchDuration;

   public HashMap org$apache$spark$streaming$ui$StreamingJobProgressListener$$waitingBatchUIData() {
      return this.org$apache$spark$streaming$ui$StreamingJobProgressListener$$waitingBatchUIData;
   }

   public HashMap org$apache$spark$streaming$ui$StreamingJobProgressListener$$runningBatchUIData() {
      return this.org$apache$spark$streaming$ui$StreamingJobProgressListener$$runningBatchUIData;
   }

   public Queue org$apache$spark$streaming$ui$StreamingJobProgressListener$$completedBatchUIData() {
      return this.org$apache$spark$streaming$ui$StreamingJobProgressListener$$completedBatchUIData;
   }

   private int batchUIDataLimit() {
      return this.batchUIDataLimit;
   }

   private long totalCompletedBatches() {
      return this.totalCompletedBatches;
   }

   private void totalCompletedBatches_$eq(final long x$1) {
      this.totalCompletedBatches = x$1;
   }

   private long totalReceivedRecords() {
      return this.totalReceivedRecords;
   }

   private void totalReceivedRecords_$eq(final long x$1) {
      this.totalReceivedRecords = x$1;
   }

   private long totalProcessedRecords() {
      return this.totalProcessedRecords;
   }

   private void totalProcessedRecords_$eq(final long x$1) {
      this.totalProcessedRecords = x$1;
   }

   private HashMap receiverInfos() {
      return this.receiverInfos;
   }

   private long _startTime() {
      return this._startTime;
   }

   private void _startTime_$eq(final long x$1) {
      this._startTime = x$1;
   }

   public LinkedHashMap batchTimeToOutputOpIdSparkJobIdPair() {
      return this.batchTimeToOutputOpIdSparkJobIdPair;
   }

   public long batchDuration() {
      return this.batchDuration;
   }

   public void onStreamingStarted(final StreamingListenerStreamingStarted streamingStarted) {
      this._startTime_$eq(streamingStarted.time());
   }

   public synchronized void onReceiverStarted(final StreamingListenerReceiverStarted receiverStarted) {
      this.receiverInfos().update(BoxesRunTime.boxToInteger(receiverStarted.receiverInfo().streamId()), receiverStarted.receiverInfo());
   }

   public synchronized void onReceiverError(final StreamingListenerReceiverError receiverError) {
      this.receiverInfos().update(BoxesRunTime.boxToInteger(receiverError.receiverInfo().streamId()), receiverError.receiverInfo());
   }

   public synchronized void onReceiverStopped(final StreamingListenerReceiverStopped receiverStopped) {
      this.receiverInfos().update(BoxesRunTime.boxToInteger(receiverStopped.receiverInfo().streamId()), receiverStopped.receiverInfo());
   }

   public synchronized void onBatchSubmitted(final StreamingListenerBatchSubmitted batchSubmitted) {
      this.org$apache$spark$streaming$ui$StreamingJobProgressListener$$waitingBatchUIData().update(batchSubmitted.batchInfo().batchTime(), BatchUIData$.MODULE$.apply(batchSubmitted.batchInfo()));
   }

   public synchronized void onBatchStarted(final StreamingListenerBatchStarted batchStarted) {
      BatchUIData batchUIData = BatchUIData$.MODULE$.apply(batchStarted.batchInfo());
      this.org$apache$spark$streaming$ui$StreamingJobProgressListener$$runningBatchUIData().update(batchStarted.batchInfo().batchTime(), batchUIData);
      this.org$apache$spark$streaming$ui$StreamingJobProgressListener$$waitingBatchUIData().remove(batchStarted.batchInfo().batchTime());
      this.totalReceivedRecords_$eq(this.totalReceivedRecords() + batchUIData.numRecords());
   }

   public synchronized void onBatchCompleted(final StreamingListenerBatchCompleted batchCompleted) {
      this.org$apache$spark$streaming$ui$StreamingJobProgressListener$$waitingBatchUIData().remove(batchCompleted.batchInfo().batchTime());
      this.org$apache$spark$streaming$ui$StreamingJobProgressListener$$runningBatchUIData().remove(batchCompleted.batchInfo().batchTime());
      BatchUIData batchUIData = BatchUIData$.MODULE$.apply(batchCompleted.batchInfo());
      this.org$apache$spark$streaming$ui$StreamingJobProgressListener$$completedBatchUIData().enqueue(batchUIData);
      if (this.org$apache$spark$streaming$ui$StreamingJobProgressListener$$completedBatchUIData().size() > this.batchUIDataLimit()) {
         BatchUIData removedBatch = (BatchUIData)this.org$apache$spark$streaming$ui$StreamingJobProgressListener$$completedBatchUIData().dequeue();
         this.batchTimeToOutputOpIdSparkJobIdPair().remove(removedBatch.batchTime());
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      this.totalCompletedBatches_$eq(this.totalCompletedBatches() + 1L);
      this.totalProcessedRecords_$eq(this.totalProcessedRecords() + batchUIData.numRecords());
   }

   public synchronized void onOutputOperationStarted(final StreamingListenerOutputOperationStarted outputOperationStarted) {
      ((BatchUIData)this.org$apache$spark$streaming$ui$StreamingJobProgressListener$$runningBatchUIData().apply(outputOperationStarted.outputOperationInfo().batchTime())).updateOutputOperationInfo(outputOperationStarted.outputOperationInfo());
   }

   public synchronized void onOutputOperationCompleted(final StreamingListenerOutputOperationCompleted outputOperationCompleted) {
      ((BatchUIData)this.org$apache$spark$streaming$ui$StreamingJobProgressListener$$runningBatchUIData().apply(outputOperationCompleted.outputOperationInfo().batchTime())).updateOutputOperationInfo(outputOperationCompleted.outputOperationInfo());
   }

   public synchronized void onJobStart(final SparkListenerJobStart jobStart) {
      this.getBatchTimeAndOutputOpId(jobStart.properties()).foreach((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$onJobStart$1(this, jobStart, x0$1)));
   }

   private Option getBatchTimeAndOutputOpId(final Properties properties) {
      String batchTime = properties.getProperty(JobScheduler$.MODULE$.BATCH_TIME_PROPERTY_KEY());
      if (batchTime == null) {
         return .MODULE$;
      } else {
         String outputOpId = properties.getProperty(JobScheduler$.MODULE$.OUTPUT_OP_ID_PROPERTY_KEY());
         scala.Predef..MODULE$.assert(outputOpId != null);
         return new Some(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(new Time(scala.collection.StringOps..MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(batchTime)))), BoxesRunTime.boxToInteger(scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(outputOpId)))));
      }
   }

   public long startTime() {
      return this._startTime();
   }

   public synchronized int numReceivers() {
      return this.receiverInfos().size();
   }

   public synchronized int numActiveReceivers() {
      return this.receiverInfos().count((x$1) -> BoxesRunTime.boxToBoolean($anonfun$numActiveReceivers$1(x$1)));
   }

   public int numInactiveReceivers() {
      return this.ssc.graph().getNumReceivers() - this.numActiveReceivers();
   }

   public synchronized long numTotalCompletedBatches() {
      return this.totalCompletedBatches();
   }

   public synchronized long numTotalReceivedRecords() {
      return this.totalReceivedRecords();
   }

   public synchronized long numTotalProcessedRecords() {
      return this.totalProcessedRecords();
   }

   public synchronized long numUnprocessedBatches() {
      return (long)(this.org$apache$spark$streaming$ui$StreamingJobProgressListener$$waitingBatchUIData().size() + this.org$apache$spark$streaming$ui$StreamingJobProgressListener$$runningBatchUIData().size());
   }

   public synchronized Seq waitingBatches() {
      return this.org$apache$spark$streaming$ui$StreamingJobProgressListener$$waitingBatchUIData().values().toSeq();
   }

   public synchronized Seq runningBatches() {
      return this.org$apache$spark$streaming$ui$StreamingJobProgressListener$$runningBatchUIData().values().toSeq();
   }

   public synchronized Seq retainedCompletedBatches() {
      return this.org$apache$spark$streaming$ui$StreamingJobProgressListener$$completedBatchUIData().toIndexedSeq();
   }

   public Option streamName(final int streamId) {
      return this.ssc.graph().getInputStreamNameAndID().find((x$2) -> BoxesRunTime.boxToBoolean($anonfun$streamName$1(streamId, x$2))).map((x$3) -> (String)x$3._1());
   }

   public Seq streamIds() {
      return (Seq)this.ssc.graph().getInputStreamNameAndID().map((x$4) -> BoxesRunTime.boxToInteger($anonfun$streamIds$1(x$4)));
   }

   public synchronized Map receivedRecordRateWithBatchTime() {
      Seq _retainedBatches = this.retainedBatches();
      Seq latestBatches = (Seq)_retainedBatches.map((batchUIData) -> new Tuple2(BoxesRunTime.boxToLong(batchUIData.batchTime().milliseconds()), batchUIData.streamIdToInputInfo().transform((x$5, v) -> BoxesRunTime.boxToLong($anonfun$receivedRecordRateWithBatchTime$2(BoxesRunTime.unboxToInt(x$5), v)))));
      return ((IterableOnceOps)this.streamIds().map((streamId) -> $anonfun$receivedRecordRateWithBatchTime$3(this, latestBatches, BoxesRunTime.unboxToInt(streamId)))).toMap(scala..less.colon.less..MODULE$.refl());
   }

   public synchronized Map lastReceivedBatchRecords() {
      Option lastReceivedBlockInfoOption = this.lastReceivedBatch().map((x$6) -> (Map)x$6.streamIdToInputInfo().transform((x$7, v) -> BoxesRunTime.boxToLong($anonfun$lastReceivedBatchRecords$2(BoxesRunTime.unboxToInt(x$7), v))));
      return (Map)lastReceivedBlockInfoOption.map((lastReceivedBlockInfo) -> ((IterableOnceOps)this.streamIds().map((streamId) -> $anonfun$lastReceivedBatchRecords$4(lastReceivedBlockInfo, BoxesRunTime.unboxToInt(streamId)))).toMap(scala..less.colon.less..MODULE$.refl())).getOrElse(() -> ((IterableOnceOps)this.streamIds().map((streamId) -> $anonfun$lastReceivedBatchRecords$7(BoxesRunTime.unboxToInt(streamId)))).toMap(scala..less.colon.less..MODULE$.refl()));
   }

   public synchronized Option receiverInfo(final int receiverId) {
      return this.receiverInfos().get(BoxesRunTime.boxToInteger(receiverId));
   }

   public synchronized Option lastCompletedBatch() {
      return ((IterableOps)this.org$apache$spark$streaming$ui$StreamingJobProgressListener$$completedBatchUIData().sortBy((x$8) -> x$8.batchTime(), Time$.MODULE$.ordering())).lastOption();
   }

   public synchronized Option lastReceivedBatch() {
      return this.retainedBatches().lastOption();
   }

   public synchronized Seq retainedBatches() {
      return (Seq)((SeqOps)((IterableOps)this.org$apache$spark$streaming$ui$StreamingJobProgressListener$$waitingBatchUIData().values().toSeq().$plus$plus(this.org$apache$spark$streaming$ui$StreamingJobProgressListener$$runningBatchUIData().values().toSeq())).$plus$plus(this.org$apache$spark$streaming$ui$StreamingJobProgressListener$$completedBatchUIData())).sortBy((x$9) -> x$9.batchTime(), Time$.MODULE$.ordering());
   }

   public synchronized Option getBatchUIData(final Time batchTime) {
      Option batchUIData = this.org$apache$spark$streaming$ui$StreamingJobProgressListener$$waitingBatchUIData().get(batchTime).orElse(() -> this.org$apache$spark$streaming$ui$StreamingJobProgressListener$$runningBatchUIData().get(batchTime).orElse(() -> this.org$apache$spark$streaming$ui$StreamingJobProgressListener$$completedBatchUIData().find((batch) -> BoxesRunTime.boxToBoolean($anonfun$getBatchUIData$3(batchTime, batch)))));
      batchUIData.foreach((_batchUIData) -> {
         $anonfun$getBatchUIData$4(this, batchTime, _batchUIData);
         return BoxedUnit.UNIT;
      });
      return batchUIData;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$onJobStart$1(final StreamingJobProgressListener $this, final SparkListenerJobStart jobStart$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         Time batchTime = (Time)x0$1._1();
         int outputOpId = x0$1._2$mcI$sp();
         ConcurrentLinkedQueue outputOpIdToSparkJobIds = (ConcurrentLinkedQueue)$this.batchTimeToOutputOpIdSparkJobIdPair().get(batchTime);
         if (outputOpIdToSparkJobIds == null) {
            outputOpIdToSparkJobIds = new ConcurrentLinkedQueue();
            $this.batchTimeToOutputOpIdSparkJobIdPair().put(batchTime, outputOpIdToSparkJobIds);
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         return outputOpIdToSparkJobIds.add(new OutputOpIdAndSparkJobId(outputOpId, jobStart$1.jobId()));
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$numActiveReceivers$1(final Tuple2 x$1) {
      return ((ReceiverInfo)x$1._2()).active();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$streamName$1(final int streamId$1, final Tuple2 x$2) {
      return x$2._2$mcI$sp() == streamId$1;
   }

   // $FF: synthetic method
   public static final int $anonfun$streamIds$1(final Tuple2 x$4) {
      return x$4._2$mcI$sp();
   }

   // $FF: synthetic method
   public static final long $anonfun$receivedRecordRateWithBatchTime$2(final int x$5, final StreamInputInfo v) {
      return v.numRecords();
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$receivedRecordRateWithBatchTime$3(final StreamingJobProgressListener $this, final Seq latestBatches$1, final int streamId) {
      Seq recordRates = (Seq)latestBatches$1.map((x0$1) -> {
         if (x0$1 != null) {
            long batchTime = x0$1._1$mcJ$sp();
            Map streamIdToNumRecords = (Map)x0$1._2();
            long numRecords = BoxesRunTime.unboxToLong(streamIdToNumRecords.getOrElse(BoxesRunTime.boxToInteger(streamId), (JFunction0.mcJ.sp)() -> 0L));
            return new Tuple2.mcJD.sp(batchTime, (double)numRecords * (double)1000.0F / (double)$this.batchDuration());
         } else {
            throw new MatchError(x0$1);
         }
      });
      return new Tuple2(BoxesRunTime.boxToInteger(streamId), recordRates);
   }

   // $FF: synthetic method
   public static final long $anonfun$lastReceivedBatchRecords$2(final int x$7, final StreamInputInfo v) {
      return v.numRecords();
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$lastReceivedBatchRecords$4(final Map lastReceivedBlockInfo$1, final int streamId) {
      return new Tuple2.mcIJ.sp(streamId, BoxesRunTime.unboxToLong(lastReceivedBlockInfo$1.getOrElse(BoxesRunTime.boxToInteger(streamId), (JFunction0.mcJ.sp)() -> 0L)));
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$lastReceivedBatchRecords$7(final int streamId) {
      return new Tuple2.mcIJ.sp(streamId, 0L);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getBatchUIData$3(final Time batchTime$1, final BatchUIData batch) {
      boolean var3;
      label23: {
         Time var10000 = batch.batchTime();
         if (var10000 == null) {
            if (batchTime$1 == null) {
               break label23;
            }
         } else if (var10000.equals(batchTime$1)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final void $anonfun$getBatchUIData$4(final StreamingJobProgressListener $this, final Time batchTime$1, final BatchUIData _batchUIData) {
      Iterable outputOpIdToSparkJobIds = (Iterable)scala.Option..MODULE$.apply($this.batchTimeToOutputOpIdSparkJobIdPair().get(batchTime$1)).map((x$10) -> scala.jdk.CollectionConverters..MODULE$.CollectionHasAsScala(x$10).asScala()).getOrElse(() -> (Seq)scala.package..MODULE$.Seq().empty());
      _batchUIData.outputOpIdSparkJobIdPairs_$eq(outputOpIdToSparkJobIds);
   }

   public StreamingJobProgressListener(final StreamingContext ssc) {
      this.ssc = ssc;
      StreamingListener.$init$(this);
      this.org$apache$spark$streaming$ui$StreamingJobProgressListener$$waitingBatchUIData = new HashMap();
      this.org$apache$spark$streaming$ui$StreamingJobProgressListener$$runningBatchUIData = new HashMap();
      this.org$apache$spark$streaming$ui$StreamingJobProgressListener$$completedBatchUIData = new Queue(scala.collection.mutable.Queue..MODULE$.$lessinit$greater$default$1());
      this.batchUIDataLimit = BoxesRunTime.unboxToInt(ssc.conf().get(StreamingConf$.MODULE$.UI_RETAINED_BATCHES()));
      this.totalCompletedBatches = 0L;
      this.totalReceivedRecords = 0L;
      this.totalProcessedRecords = 0L;
      this.receiverInfos = new HashMap();
      this._startTime = -1L;
      this.batchTimeToOutputOpIdSparkJobIdPair = new LinkedHashMap() {
         // $FF: synthetic field
         private final StreamingJobProgressListener $outer;

         public boolean removeEldestEntry(final java.util.Map.Entry p1) {
            return this.size() > this.$outer.org$apache$spark$streaming$ui$StreamingJobProgressListener$$waitingBatchUIData().size() + this.$outer.org$apache$spark$streaming$ui$StreamingJobProgressListener$$runningBatchUIData().size() + this.$outer.org$apache$spark$streaming$ui$StreamingJobProgressListener$$completedBatchUIData().size() + 10;
         }

         public {
            if (StreamingJobProgressListener.this == null) {
               throw null;
            } else {
               this.$outer = StreamingJobProgressListener.this;
            }
         }
      };
      this.batchDuration = ssc.graph().batchDuration().milliseconds();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
