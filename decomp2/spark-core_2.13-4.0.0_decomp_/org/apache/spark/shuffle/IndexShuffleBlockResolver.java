package org.apache.spark.shuffle;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import java.nio.LongBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SeekableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Files;
import java.util.Collections;
import java.util.Map;
import org.apache.spark.SecurityManager;
import org.apache.spark.SecurityManager$;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkEnv$;
import org.apache.spark.errors.SparkCoreErrors$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.package$;
import org.apache.spark.io.NioBufferedFileInputStream;
import org.apache.spark.network.buffer.FileSegmentManagedBuffer;
import org.apache.spark.network.buffer.ManagedBuffer;
import org.apache.spark.network.client.StreamCallbackWithID;
import org.apache.spark.network.netty.SparkTransportConf$;
import org.apache.spark.network.shuffle.ExecutorDiskUtils;
import org.apache.spark.network.shuffle.MergedBlockMeta;
import org.apache.spark.network.shuffle.checksum.ShuffleChecksumHelper;
import org.apache.spark.network.util.TransportConf;
import org.apache.spark.serializer.SerializerManager;
import org.apache.spark.storage.BlockId;
import org.apache.spark.storage.BlockManager;
import org.apache.spark.storage.BlockStatus;
import org.apache.spark.storage.ShuffleBlockBatchId;
import org.apache.spark.storage.ShuffleBlockId;
import org.apache.spark.storage.ShuffleChecksumBlockId;
import org.apache.spark.storage.ShuffleDataBlockId;
import org.apache.spark.storage.ShuffleIndexBlockId;
import org.apache.spark.storage.ShuffleMergedBlockId;
import org.apache.spark.storage.ShuffleMergedDataBlockId;
import org.apache.spark.storage.ShuffleMergedIndexBlockId;
import org.apache.spark.storage.ShuffleMergedMetaBlockId;
import org.apache.spark.util.Utils$;
import org.apache.spark.util.collection.OpenHashSet;
import org.apache.spark.util.collection.OpenHashSet$mcJ$sp;
import org.slf4j.Logger;
import org.sparkproject.guava.cache.Cache;
import org.sparkproject.guava.cache.CacheBuilder;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple4;
import scala.Option.;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\reb!\u0002\u001c8\u0001ez\u0004\u0002C*\u0001\u0005\u0003\u0005\u000b\u0011B+\t\u0011e\u0003!\u00111A\u0005\u0002iC\u0001\"\u0019\u0001\u0003\u0002\u0004%\tA\u0019\u0005\tQ\u0002\u0011\t\u0011)Q\u00057\"A\u0011\u000e\u0001BC\u0002\u0013\u0005!\u000eC\u0005\u0002\u0002\u0001\u0011\t\u0011)A\u0005W\"9\u00111\u0001\u0001\u0005\u0002\u0005\u0015\u0001bBA\u0002\u0001\u0011\u0005\u0011q\u0002\u0005\b\u0003\u0007\u0001A\u0011AA\n\u0011\u001d\t\u0019\u0001\u0001C\u0001\u00033A\u0011\"a\b\u0001\u0011\u000b\u0007I\u0011\u0002.\t\u0013\u0005\u0005\u0002A1A\u0005\n\u0005\r\u0002\u0002CA\u001a\u0001\u0001\u0006I!!\n\t\u0013\u0005U\u0002A1A\u0005\n\u0005]\u0002\u0002CA \u0001\u0001\u0006I!!\u000f\t\u0013\u0005\u0005\u0003A1A\u0005\n\u0005\r\u0003\u0002CA&\u0001\u0001\u0006I!!\u0012\t\u0015\u00055\u0003\u0001#b\u0001\n\u0013\ty\u0005C\u0004\u0002h\u0001!\t!!\u001b\t\u000f\u0005}\u0004\u0001\"\u0011\u0002\u0002\"I\u00111\u0014\u0001C\u0002\u0013%\u0011Q\u0014\u0005\t\u0003\u000f\u0004\u0001\u0015!\u0003\u0002 \"9\u0011\u0011\u001a\u0001\u0005B\u0005-\u0007bBAl\u0001\u0011%\u0011\u0011\u001c\u0005\b\u00037\u0004A\u0011AAo\u0011\u001d\t9\u0007\u0001C\u0001\u0003GDq!!>\u0001\t\u0003\t9\u0010C\u0005\u0002\u0000\u0002\t\n\u0011\"\u0001\u0003\u0002!9!q\u0003\u0001\u0005\n\te\u0001\"\u0003B\u0016\u0001E\u0005I\u0011\u0002B\u0001\u0011\u001d\u0011i\u0003\u0001C\u0005\u0005_A\u0011Ba\u000f\u0001#\u0003%IA!\u0001\t\u000f\tu\u0002\u0001\"\u0003\u0003@!I!1\n\u0001\u0012\u0002\u0013%!\u0011\u0001\u0005\b\u0005\u001b\u0002A\u0011\u0001B(\u0011\u001d\u0011)\u0006\u0001C\u0005\u0005/BqAa\u001a\u0001\t\u0003\u0012I\u0007C\u0004\u0003\u0012\u0002!\tAa%\t\u000f\tE\u0006\u0001\"\u0001\u00034\"9!Q\u0019\u0001\u0005\n\t\u001d\u0007b\u0002Bm\u0001\u0011\u0005#1\u001c\u0005\b\u0005S\u0004A\u0011\tBv\u0011!\u0011Y\u0010\u0001C\u0001o\tu\bbBB\u0004\u0001\u0011\u00051\u0011\u0002\u0005\n\u0007'\u0001\u0011\u0013!C\u0001\u0005\u0003Aqa!\u0006\u0001\t\u0003\u001a9\u0002C\u0004\u0004\u001e\u0001!\tea\b\t\u000f\r\u001d\u0002\u0001\"\u0011\u0004*\u001dA11F\u001c\t\u0002e\u001aiCB\u00047o!\u0005\u0011ha\f\t\u000f\u0005\r!\u0007\"\u0001\u00042!I11\u0007\u001aC\u0002\u0013\u00051Q\u0007\u0005\b\u0007o\u0011\u0004\u0015!\u0003t\u0005eIe\u000eZ3y'\",hM\u001a7f\u00052|7m\u001b*fg>dg/\u001a:\u000b\u0005aJ\u0014aB:ik\u001a4G.\u001a\u0006\u0003um\nQa\u001d9be.T!\u0001P\u001f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005q\u0014aA8sON)\u0001\u0001\u0011$K!B\u0011\u0011\tR\u0007\u0002\u0005*\t1)A\u0003tG\u0006d\u0017-\u0003\u0002F\u0005\n1\u0011I\\=SK\u001a\u0004\"a\u0012%\u000e\u0003]J!!S\u001c\u0003)MCWO\u001a4mK\ncwnY6SKN|GN^3s!\tYe*D\u0001M\u0015\ti\u0015(\u0001\u0005j]R,'O\\1m\u0013\tyEJA\u0004M_\u001e<\u0017N\\4\u0011\u0005\u001d\u000b\u0016B\u0001*8\u0005Ii\u0015n\u001a:bi\u0006\u0014G.\u001a*fg>dg/\u001a:\u0002\t\r|gNZ\u0002\u0001!\t1v+D\u0001:\u0013\tA\u0016HA\u0005Ta\u0006\u00148nQ8oM\u0006iqL\u00197pG.l\u0015M\\1hKJ,\u0012a\u0017\t\u00039~k\u0011!\u0018\u0006\u0003=f\nqa\u001d;pe\u0006<W-\u0003\u0002a;\na!\t\\8dW6\u000bg.Y4fe\u0006\trL\u00197pG.l\u0015M\\1hKJ|F%Z9\u0015\u0005\r4\u0007CA!e\u0013\t)'I\u0001\u0003V]&$\bbB4\u0004\u0003\u0003\u0005\raW\u0001\u0004q\u0012\n\u0014AD0cY>\u001c7.T1oC\u001e,'\u000fI\u0001\u0015i\u0006\u001c8.\u00133NCB\u001chi\u001c:TQV4g\r\\3\u0016\u0003-\u0004B\u0001\\9tm6\tQN\u0003\u0002o_\u0006!Q\u000f^5m\u0015\u0005\u0001\u0018\u0001\u00026bm\u0006L!A]7\u0003\u00075\u000b\u0007\u000f\u0005\u0002Bi&\u0011QO\u0011\u0002\u0004\u0013:$\bcA<|{6\t\u0001P\u0003\u0002zu\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u00059L\u0014B\u0001?y\u0005-y\u0005/\u001a8ICND7+\u001a;\u0011\u0005\u0005s\u0018BA@C\u0005\u0011auN\\4\u0002+Q\f7o[%e\u001b\u0006\u00048OR8s'\",hM\u001a7fA\u00051A(\u001b8jiz\"\u0002\"a\u0002\u0002\n\u0005-\u0011Q\u0002\t\u0003\u000f\u0002AQaU\u0004A\u0002UCQ!W\u0004A\u0002mCQ![\u0004A\u0002-$B!a\u0002\u0002\u0012!)1\u000b\u0003a\u0001+R1\u0011qAA\u000b\u0003/AQaU\u0005A\u0002UCQ!W\u0005A\u0002m#b!a\u0002\u0002\u001c\u0005u\u0001\"B*\u000b\u0001\u0004)\u0006\"B5\u000b\u0001\u0004Y\u0017\u0001\u00042m_\u000e\\W*\u00198bO\u0016\u0014\u0018!\u0004;sC:\u001c\bo\u001c:u\u0007>tg-\u0006\u0002\u0002&A!\u0011qEA\u0018\u001b\t\tICC\u0002o\u0003WQ1!!\f:\u0003\u001dqW\r^<pe.LA!!\r\u0002*\tiAK]1ogB|'\u000f^\"p]\u001a\fa\u0002\u001e:b]N\u0004xN\u001d;D_:4\u0007%\u0001\u000bsK6|G/Z*ik\u001a4G.Z'bq\u0012K7o[\u000b\u0003\u0003s\u0001B!QA\u001e{&\u0019\u0011Q\b\"\u0003\r=\u0003H/[8o\u0003U\u0011X-\\8uKNCWO\u001a4mK6\u000b\u0007\u0010R5tW\u0002\nqb\u00195fG.\u001cX/\\#oC\ndW\rZ\u000b\u0003\u0003\u000b\u00022!QA$\u0013\r\tIE\u0011\u0002\b\u0005>|G.Z1o\u0003A\u0019\u0007.Z2lgVlWI\\1cY\u0016$\u0007%A\u0005bY\u001e|'/\u001b;i[V\u0011\u0011\u0011\u000b\t\u0005\u0003'\n\tG\u0004\u0003\u0002V\u0005u\u0003cAA,\u00056\u0011\u0011\u0011\f\u0006\u0004\u00037\"\u0016A\u0002\u001fs_>$h(C\u0002\u0002`\t\u000ba\u0001\u0015:fI\u00164\u0017\u0002BA2\u0003K\u0012aa\u0015;sS:<'bAA0\u0005\u0006Yq-\u001a;ECR\fg)\u001b7f)\u0019\tY'a\u001e\u0002|A!\u0011QNA:\u001b\t\tyGC\u0002\u0002r=\f!![8\n\t\u0005U\u0014q\u000e\u0002\u0005\r&dW\r\u0003\u0004\u0002zM\u0001\ra]\u0001\ng\",hM\u001a7f\u0013\u0012Da!! \u0014\u0001\u0004i\u0018!B7ba&#\u0017!E4fiN#xN]3e'\",hM\u001a7fgR\u0011\u00111\u0011\t\u0007\u0003\u000b\u000by)!&\u000f\t\u0005\u001d\u00151\u0012\b\u0005\u0003/\nI)C\u0001D\u0013\r\tiIQ\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\t\t*a%\u0003\u0007M+\u0017OC\u0002\u0002\u000e\n\u00032aRAL\u0013\r\tIj\u000e\u0002\u0011'\",hM\u001a7f\u00052|7m[%oM>\f\u0001c\u001d5vM\u001adW-\u00133t)>\u001c6.\u001b9\u0016\u0005\u0005}\u0005\u0003CAQ\u0003g\u000b9,a1\u000e\u0005\u0005\r&\u0002BAS\u0003O\u000bQaY1dQ\u0016TA!!+\u0002,\u000611m\\7n_:TA!!,\u00020\u00061qm\\8hY\u0016T!!!-\u0002\u0007\r|W.\u0003\u0003\u00026\u0006\r&!B\"bG\",\u0007\u0003BA]\u0003\u007fk!!a/\u000b\u0007\u0005uv.\u0001\u0003mC:<\u0017\u0002BAa\u0003w\u0013q!\u00138uK\u001e,'\u000f\u0005\u0003\u0002:\u0006\u0015\u0017\u0002BA%\u0003w\u000b\u0011c\u001d5vM\u001adW-\u00133t)>\u001c6.\u001b9!\u0003A\tG\rZ*ik\u001a4G.\u001a+p'.L\u0007\u000fF\u0002d\u0003\u001bDq!!\u001f\u0018\u0001\u0004\ty\r\u0005\u0003\u0002R\u0006MW\"\u0001\u0001\n\u0007\u0005U\u0007JA\u0005TQV4g\r\\3JI\u0006)r-\u001a;TQV4g\r\\3CsR,7o\u0015;pe\u0016$G#A?\u0002\u001d\r\u0014X-\u0019;f)\u0016l\u0007OR5mKR!\u00111NAp\u0011\u001d\t\t/\u0007a\u0001\u0003W\nAAZ5mKRA\u00111NAs\u0003O\fI\u000f\u0003\u0004\u0002zi\u0001\ra\u001d\u0005\u0007\u0003{R\u0002\u0019A?\t\u000f\u0005-(\u00041\u0001\u0002n\u0006!A-\u001b:t!\u0015\t\u00151HAx!\u0015\t\u0015\u0011_A)\u0013\r\t\u0019P\u0011\u0002\u0006\u0003J\u0014\u0018-_\u0001\rO\u0016$\u0018J\u001c3fq\u001aKG.\u001a\u000b\t\u0003W\nI0a?\u0002~\"1\u0011\u0011P\u000eA\u0002MDa!! \u001c\u0001\u0004i\b\"CAv7A\u0005\t\u0019AAw\u0003Y9W\r^%oI\u0016Dh)\u001b7fI\u0011,g-Y;mi\u0012\u001aTC\u0001B\u0002U\u0011\tiO!\u0002,\u0005\t\u001d\u0001\u0003\u0002B\u0005\u0005'i!Aa\u0003\u000b\t\t5!qB\u0001\nk:\u001c\u0007.Z2lK\u0012T1A!\u0005C\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0005+\u0011YAA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\facZ3u\u001b\u0016\u0014x-\u001a3CY>\u001c7\u000eR1uC\u001aKG.\u001a\u000b\r\u0003W\u0012YBa\b\u0003\"\t\u0015\"\u0011\u0006\u0005\b\u0005;i\u0002\u0019AA)\u0003\u0015\t\u0007\u000f]%e\u0011\u0019\tI(\ba\u0001g\"1!1E\u000fA\u0002M\fab\u001d5vM\u001adW-T3sO\u0016LE\r\u0003\u0004\u0003(u\u0001\ra]\u0001\te\u0016$WoY3JI\"I\u00111^\u000f\u0011\u0002\u0003\u0007\u0011Q^\u0001!O\u0016$X*\u001a:hK\u0012\u0014En\\2l\t\u0006$\u0018MR5mK\u0012\"WMZ1vYR$S'A\fhKRlUM]4fI\ncwnY6J]\u0012,\u0007PR5mKRa\u00111\u000eB\u0019\u0005g\u0011)Da\u000e\u0003:!9!QD\u0010A\u0002\u0005E\u0003BBA=?\u0001\u00071\u000f\u0003\u0004\u0003$}\u0001\ra\u001d\u0005\u0007\u0005Oy\u0002\u0019A:\t\u0013\u0005-x\u0004%AA\u0002\u00055\u0018!I4fi6+'oZ3e\u00052|7m[%oI\u0016Dh)\u001b7fI\u0011,g-Y;mi\u0012*\u0014AF4fi6+'oZ3e\u00052|7m['fi\u00064\u0015\u000e\\3\u0015\u0019\u0005-$\u0011\tB\"\u0005\u000b\u00129E!\u0013\t\u000f\tu\u0011\u00051\u0001\u0002R!1\u0011\u0011P\u0011A\u0002MDaAa\t\"\u0001\u0004\u0019\bB\u0002B\u0014C\u0001\u00071\u000fC\u0005\u0002l\u0006\u0002\n\u00111\u0001\u0002n\u0006\u0001s-\u001a;NKJ<W\r\u001a\"m_\u000e\\W*\u001a;b\r&dW\r\n3fM\u0006,H\u000e\u001e\u00136\u0003=\u0011X-\\8wK\u0012\u000bG/\u0019\"z\u001b\u0006\u0004H#B2\u0003R\tM\u0003BBA=G\u0001\u00071\u000f\u0003\u0004\u0002~\r\u0002\r!`\u0001\u0016G\",7m[%oI\u0016D\u0018I\u001c3ECR\fg)\u001b7f)!\u0011IFa\u0017\u0003`\t\r\u0004\u0003B!\u0002rvDqA!\u0018%\u0001\u0004\tY'A\u0003j]\u0012,\u0007\u0010C\u0004\u0003b\u0011\u0002\r!a\u001b\u0002\t\u0011\fG/\u0019\u0005\u0007\u0005K\"\u0003\u0019A:\u0002\r\tdwnY6t\u0003]\u0001X\u000f^*ik\u001a4G.\u001a\"m_\u000e\\\u0017i]*ue\u0016\fW\u000e\u0006\u0004\u0003l\t]$\u0011\u0011\t\u0005\u0005[\u0012\u0019(\u0004\u0002\u0003p)!!\u0011OA\u0016\u0003\u0019\u0019G.[3oi&!!Q\u000fB8\u0005Q\u0019FO]3b[\u000e\u000bG\u000e\u001c2bG.<\u0016\u000e\u001e5J\t\"9!\u0011P\u0013A\u0002\tm\u0014a\u00022m_\u000e\\\u0017\n\u001a\t\u00049\nu\u0014b\u0001B@;\n9!\t\\8dW&#\u0007b\u0002BBK\u0001\u0007!QQ\u0001\u0012g\u0016\u0014\u0018.\u00197ju\u0016\u0014X*\u00198bO\u0016\u0014\b\u0003\u0002BD\u0005\u001bk!A!#\u000b\u0007\t-\u0015(\u0001\u0006tKJL\u0017\r\\5{KJLAAa$\u0003\n\n\t2+\u001a:jC2L'0\u001a:NC:\fw-\u001a:\u0002%\u001d,G/T5he\u0006$\u0018n\u001c8CY>\u001c7n\u001d\u000b\u0005\u0005+\u0013i\u000b\u0005\u0004\u0002\u0006\n]%1T\u0005\u0005\u00053\u000b\u0019J\u0001\u0003MSN$\bcB!\u0003\u001e\nm$\u0011U\u0005\u0004\u0005?\u0013%A\u0002+va2,'\u0007\u0005\u0003\u0003$\n%VB\u0001BS\u0015\u0011\u00119+a\u000b\u0002\r\t,hMZ3s\u0013\u0011\u0011YK!*\u0003\u001b5\u000bg.Y4fI\n+hMZ3s\u0011\u001d\u0011yK\na\u0001\u0003+\u000b\u0001c\u001d5vM\u001adWM\u00117pG.LeNZ8\u00025]\u0014\u0018\u000e^3NKR\fG-\u0019;b\r&dW-\u00118e\u0007>lW.\u001b;\u0015\u0017\r\u0014)La.\u0003:\nu&\u0011\u0019\u0005\u0007\u0003s:\u0003\u0019A:\t\r\u0005ut\u00051\u0001~\u0011\u001d\u0011Yl\na\u0001\u00053\nq\u0001\\3oORD7\u000fC\u0004\u0003@\u001e\u0002\rA!\u0017\u0002\u0013\rDWmY6tk6\u001c\bb\u0002BbO\u0001\u0007\u00111N\u0001\bI\u0006$\u0018\rV7q\u0003E9(/\u001b;f\u001b\u0016$\u0018\rZ1uC\u001aKG.\u001a\u000b\nG\n%'Q\u001aBi\u0005+DqAa3)\u0001\u0004\u0011I&\u0001\u0006nKR\fg+\u00197vKNDqAa4)\u0001\u0004\tY'A\u0004u[B4\u0015\u000e\\3\t\u000f\tM\u0007\u00061\u0001\u0002l\u0005QA/\u0019:hKR4\u0015\u000e\\3\t\u000f\t]\u0007\u00061\u0001\u0002F\u0005q\u0001O]8qC\u001e\fG/Z#se>\u0014\u0018AE4fi6+'oZ3e\u00052|7m\u001b#bi\u0006$bA!8\u0003`\n\u001d\bCBAC\u0003\u001f\u0013\t\u000bC\u0004\u0003z%\u0002\rA!9\u0011\u0007q\u0013\u0019/C\u0002\u0003fv\u0013Ac\u00155vM\u001adW-T3sO\u0016$'\t\\8dW&#\u0007bBAvS\u0001\u0007\u0011Q^\u0001\u0013O\u0016$X*\u001a:hK\u0012\u0014En\\2l\u001b\u0016$\u0018\r\u0006\u0004\u0003n\n](\u0011 \t\u0005\u0005_\u0014\u00190\u0004\u0002\u0003r*\u0019\u0001(a\u000b\n\t\tU(\u0011\u001f\u0002\u0010\u001b\u0016\u0014x-\u001a3CY>\u001c7.T3uC\"9!\u0011\u0010\u0016A\u0002\t\u0005\bbBAvU\u0001\u0007\u0011Q^\u0001\rO\u0016$8\t[3dWN,Xn\u001d\u000b\u0007\u00053\u0012ypa\u0001\t\u000f\r\u00051\u00061\u0001\u0002l\u0005a1\r[3dWN,XNR5mK\"11QA\u0016A\u0002M\f\u0001B\u00197pG.tU/\\\u0001\u0010O\u0016$8\t[3dWN,XNR5mKRQ\u00111NB\u0006\u0007\u001b\u0019ya!\u0005\t\r\u0005eD\u00061\u0001t\u0011\u0019\ti\b\fa\u0001{\"9\u0011Q\n\u0017A\u0002\u0005E\u0003\"CAvYA\u0005\t\u0019AAw\u0003e9W\r^\"iK\u000e\\7/^7GS2,G\u0005Z3gCVdG\u000f\n\u001b\u0002\u0019\u001d,GO\u00117pG.$\u0015\r^1\u0015\r\t\u00056\u0011DB\u000e\u0011\u001d\u0011IH\fa\u0001\u0005wB\u0011\"a;/!\u0003\u0005\r!!<\u0002'\u001d,GO\u00117pG.\u001chi\u001c:TQV4g\r\\3\u0015\r\r\u000521EB\u0013!\u0019\t))a$\u0003|!1\u0011\u0011P\u0018A\u0002MDa!! 0\u0001\u0004i\u0018\u0001B:u_B$\u0012aY\u0001\u001a\u0013:$W\r_*ik\u001a4G.\u001a\"m_\u000e\\'+Z:pYZ,'\u000f\u0005\u0002HeM\u0011!\u0007\u0011\u000b\u0003\u0007[\taBT(P!~\u0013V\tR+D\u000b~KE)F\u0001t\u0003=quj\u0014)`%\u0016#UkQ#`\u0013\u0012\u0003\u0003"
)
public class IndexShuffleBlockResolver implements ShuffleBlockResolver, Logging, MigratableResolver {
   private BlockManager org$apache$spark$shuffle$IndexShuffleBlockResolver$$blockManager;
   private String algorithm;
   private final SparkConf conf;
   private BlockManager _blockManager;
   private final Map taskIdMapsForShuffle;
   private final TransportConf transportConf;
   private final Option remoteShuffleMaxDisk;
   private final boolean checksumEnabled;
   private final Cache shuffleIdsToSkip;
   private transient Logger org$apache$spark$internal$Logging$$log_;
   private volatile byte bitmap$0;

   public static int NOOP_REDUCE_ID() {
      return IndexShuffleBlockResolver$.MODULE$.NOOP_REDUCE_ID();
   }

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

   public Option getBlockData$default$2() {
      return ShuffleBlockResolver.getBlockData$default$2$(this);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public BlockManager _blockManager() {
      return this._blockManager;
   }

   public void _blockManager_$eq(final BlockManager x$1) {
      this._blockManager = x$1;
   }

   public Map taskIdMapsForShuffle() {
      return this.taskIdMapsForShuffle;
   }

   private BlockManager blockManager$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.org$apache$spark$shuffle$IndexShuffleBlockResolver$$blockManager = (BlockManager).MODULE$.apply(this._blockManager()).getOrElse(() -> SparkEnv$.MODULE$.get().blockManager());
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.org$apache$spark$shuffle$IndexShuffleBlockResolver$$blockManager;
   }

   public BlockManager org$apache$spark$shuffle$IndexShuffleBlockResolver$$blockManager() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.blockManager$lzycompute() : this.org$apache$spark$shuffle$IndexShuffleBlockResolver$$blockManager;
   }

   private TransportConf transportConf() {
      return this.transportConf;
   }

   private Option remoteShuffleMaxDisk() {
      return this.remoteShuffleMaxDisk;
   }

   private boolean checksumEnabled() {
      return this.checksumEnabled;
   }

   private String algorithm$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.algorithm = (String)this.conf.get(package$.MODULE$.SHUFFLE_CHECKSUM_ALGORITHM());
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.algorithm;
   }

   private String algorithm() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.algorithm$lzycompute() : this.algorithm;
   }

   public File getDataFile(final int shuffleId, final long mapId) {
      return this.getDataFile(shuffleId, mapId, scala.None..MODULE$);
   }

   public Seq getStoredShuffles() {
      Seq allBlocks = this.org$apache$spark$shuffle$IndexShuffleBlockResolver$$blockManager().diskBlockManager().getAllBlocks();
      return (Seq)allBlocks.flatMap((x0$1) -> {
         if (x0$1 instanceof ShuffleIndexBlockId var4) {
            int shuffleId = var4.shuffleId();
            long mapId = var4.mapId();
            if (.MODULE$.apply(this.shuffleIdsToSkip().getIfPresent(BoxesRunTime.boxToInteger(shuffleId))).isEmpty()) {
               return new Some(new ShuffleBlockInfo(shuffleId, mapId));
            }
         }

         return scala.None..MODULE$;
      });
   }

   private Cache shuffleIdsToSkip() {
      return this.shuffleIdsToSkip;
   }

   public void addShuffleToSkip(final int shuffleId) {
      this.shuffleIdsToSkip().put(scala.Predef..MODULE$.int2Integer(shuffleId), scala.Predef..MODULE$.boolean2Boolean(true));
   }

   private long getShuffleBytesStored() {
      Seq shuffleFiles = (Seq)this.getStoredShuffles().map((si) -> this.getDataFile(si.shuffleId(), si.mapId()));
      return BoxesRunTime.unboxToLong(((IterableOnceOps)shuffleFiles.map((x$1) -> BoxesRunTime.boxToLong($anonfun$getShuffleBytesStored$2(x$1)))).sum(scala.math.Numeric.LongIsIntegral..MODULE$));
   }

   public File createTempFile(final File file) {
      return this.org$apache$spark$shuffle$IndexShuffleBlockResolver$$blockManager().diskBlockManager().createTempFileWith(file);
   }

   public File getDataFile(final int shuffleId, final long mapId, final Option dirs) {
      ShuffleDataBlockId blockId = new ShuffleDataBlockId(shuffleId, mapId, IndexShuffleBlockResolver$.MODULE$.NOOP_REDUCE_ID());
      return (File)dirs.map((d) -> new File(ExecutorDiskUtils.getFilePath(d, this.org$apache$spark$shuffle$IndexShuffleBlockResolver$$blockManager().subDirsPerLocalDir(), blockId.name()))).getOrElse(() -> this.org$apache$spark$shuffle$IndexShuffleBlockResolver$$blockManager().diskBlockManager().getFile((BlockId)blockId));
   }

   public File getIndexFile(final int shuffleId, final long mapId, final Option dirs) {
      ShuffleIndexBlockId blockId = new ShuffleIndexBlockId(shuffleId, mapId, IndexShuffleBlockResolver$.MODULE$.NOOP_REDUCE_ID());
      return (File)dirs.map((d) -> new File(ExecutorDiskUtils.getFilePath(d, this.org$apache$spark$shuffle$IndexShuffleBlockResolver$$blockManager().subDirsPerLocalDir(), blockId.name()))).getOrElse(() -> this.org$apache$spark$shuffle$IndexShuffleBlockResolver$$blockManager().diskBlockManager().getFile((BlockId)blockId));
   }

   public Option getIndexFile$default$3() {
      return scala.None..MODULE$;
   }

   private File getMergedBlockDataFile(final String appId, final int shuffleId, final int shuffleMergeId, final int reduceId, final Option dirs) {
      return this.org$apache$spark$shuffle$IndexShuffleBlockResolver$$blockManager().diskBlockManager().getMergedShuffleFile((BlockId)(new ShuffleMergedDataBlockId(appId, shuffleId, shuffleMergeId, reduceId)), dirs);
   }

   private File getMergedBlockIndexFile(final String appId, final int shuffleId, final int shuffleMergeId, final int reduceId, final Option dirs) {
      return this.org$apache$spark$shuffle$IndexShuffleBlockResolver$$blockManager().diskBlockManager().getMergedShuffleFile((BlockId)(new ShuffleMergedIndexBlockId(appId, shuffleId, shuffleMergeId, reduceId)), dirs);
   }

   private Option getMergedBlockIndexFile$default$5() {
      return scala.None..MODULE$;
   }

   private File getMergedBlockMetaFile(final String appId, final int shuffleId, final int shuffleMergeId, final int reduceId, final Option dirs) {
      return this.org$apache$spark$shuffle$IndexShuffleBlockResolver$$blockManager().diskBlockManager().getMergedShuffleFile((BlockId)(new ShuffleMergedMetaBlockId(appId, shuffleId, shuffleMergeId, reduceId)), dirs);
   }

   public void removeDataByMap(final int shuffleId, final long mapId) {
      ObjectRef file = ObjectRef.create(this.getDataFile(shuffleId, mapId));
      if (((File)file.elem).exists() && !((File)file.elem).delete()) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error deleting data ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, ((File)file.elem).getPath())})))));
      }

      file.elem = this.getIndexFile(shuffleId, mapId, this.getIndexFile$default$3());
      if (((File)file.elem).exists() && !((File)file.elem).delete()) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error deleting index ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, ((File)file.elem).getPath())})))));
      }

      if (this.checksumEnabled()) {
         file.elem = this.getChecksumFile(shuffleId, mapId, this.algorithm(), this.getChecksumFile$default$4());
         if (((File)file.elem).exists() && !((File)file.elem).delete()) {
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error deleting checksum ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, ((File)file.elem).getPath())})))));
         }
      }
   }

   private long[] checkIndexAndDataFile(final File index, final File data, final int blocks) {
      if (index.length() != (long)(blocks + 1) * 8L) {
         return null;
      } else {
         long[] lengths = new long[blocks];

         DataInputStream var10000;
         try {
            var10000 = new DataInputStream(new NioBufferedFileInputStream(index));
         } catch (IOException var18) {
            return null;
         }

         DataInputStream in = var10000;

         try {
            Object var9;
            try {
               long offset = in.readLong();
               if (offset != 0L) {
                  var9 = null;
                  return (long[])var9;
               }

               for(int i = 0; i < blocks; ++i) {
                  long off = in.readLong();
                  lengths[i] = off - offset;
                  offset = off;
               }
            } catch (IOException var19) {
               var9 = null;
               return (long[])var9;
            }
         } finally {
            in.close();
         }

         if (data.length() == BoxesRunTime.unboxToLong(scala.Predef..MODULE$.wrapLongArray(lengths).sum(scala.math.Numeric.LongIsIntegral..MODULE$))) {
            return lengths;
         } else {
            return null;
         }
      }
   }

   public StreamCallbackWithID putShuffleBlockAsStream(final BlockId blockId, final SerializerManager serializerManager) {
      this.remoteShuffleMaxDisk().foreach((JFunction1.mcVJ.sp)(maxBytes) -> {
         long bytesUsed = this.getShuffleBytesStored();
         if (maxBytes < bytesUsed) {
            throw org.apache.spark.SparkException..MODULE$.internalError("Not storing remote shuffles " + bytesUsed + " exceeds " + maxBytes, "SHUFFLE");
         }
      });
      File var10000;
      if (blockId instanceof ShuffleIndexBlockId var6) {
         int shuffleId = var6.shuffleId();
         long mapId = var6.mapId();
         var10000 = this.getIndexFile(shuffleId, mapId, this.getIndexFile$default$3());
      } else {
         if (!(blockId instanceof ShuffleDataBlockId)) {
            throw org.apache.spark.SparkException..MODULE$.internalError("Unexpected shuffle block transfer " + blockId + " as " + blockId.getClass().getSimpleName(), "SHUFFLE");
         }

         ShuffleDataBlockId var10 = (ShuffleDataBlockId)blockId;
         int shuffleId = var10.shuffleId();
         long mapId = var10.mapId();
         var10000 = this.getDataFile(shuffleId, mapId);
      }

      File file = var10000;
      File fileTmp = this.createTempFile(file);
      WritableByteChannel channel = Channels.newChannel(new FileOutputStream(fileTmp));
      return new StreamCallbackWithID(blockId, channel, fileTmp, file) {
         // $FF: synthetic field
         private final IndexShuffleBlockResolver $outer;
         private final BlockId blockId$3;
         private final WritableByteChannel channel$1;
         private final File fileTmp$1;
         private final File file$2;

         public ByteBuffer getCompletionResponse() {
            return super.getCompletionResponse();
         }

         public String getID() {
            return this.blockId$3.name();
         }

         public void onData(final String streamId, final ByteBuffer buf) {
            while(buf.hasRemaining()) {
               this.channel$1.write(buf);
            }

         }

         public void onComplete(final String streamId) {
            this.$outer.logTrace((Function0)(() -> "Done receiving shuffle block " + this.blockId$3 + ", now storing on local disk."));
            this.channel$1.close();
            long diskSize = this.fileTmp$1.length();
            synchronized(this){}

            try {
               if (this.file$2.exists()) {
                  BoxesRunTime.boxToBoolean(this.file$2.delete());
               } else {
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               }

               if (!this.fileTmp$1.renameTo(this.file$2)) {
                  throw SparkCoreErrors$.MODULE$.failedRenameTempFileError(this.fileTmp$1, this.file$2);
               }
            } catch (Throwable var18) {
               throw var18;
            }

            BlockId var6 = this.blockId$3;
            if (var6 instanceof ShuffleIndexBlockId var7) {
               int shuffleId = var7.shuffleId();
               long mapId = var7.mapId();
               OpenHashSet mapTaskIds = (OpenHashSet)this.$outer.taskIdMapsForShuffle().computeIfAbsent(BoxesRunTime.boxToInteger(shuffleId), (x$2) -> $anonfun$onComplete$2(BoxesRunTime.unboxToInt(x$2)));
               mapTaskIds.add$mcJ$sp(mapId);
               BoxedUnit var19 = BoxedUnit.UNIT;
            } else if (var6 instanceof ShuffleDataBlockId var12) {
               int shuffleId = var12.shuffleId();
               long mapId = var12.mapId();
               OpenHashSet mapTaskIds = (OpenHashSet)this.$outer.taskIdMapsForShuffle().computeIfAbsent(BoxesRunTime.boxToInteger(shuffleId), (x$3) -> $anonfun$onComplete$3(BoxesRunTime.unboxToInt(x$3)));
               mapTaskIds.add$mcJ$sp(mapId);
               BoxedUnit var20 = BoxedUnit.UNIT;
            } else {
               BoxedUnit var21 = BoxedUnit.UNIT;
            }

            this.$outer.org$apache$spark$shuffle$IndexShuffleBlockResolver$$blockManager().reportBlockStatus(this.blockId$3, new BlockStatus(org.apache.spark.storage.StorageLevel..MODULE$.DISK_ONLY(), 0L, diskSize), this.$outer.org$apache$spark$shuffle$IndexShuffleBlockResolver$$blockManager().reportBlockStatus$default$3());
         }

         public void onFailure(final String streamId, final Throwable cause) {
            this.$outer.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error while uploading ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, this.blockId$3)})))), cause);
            this.channel$1.close();
            this.fileTmp$1.delete();
         }

         // $FF: synthetic method
         public static final OpenHashSet $anonfun$onComplete$2(final int x$2) {
            return new OpenHashSet$mcJ$sp(8, scala.reflect.ClassTag..MODULE$.Long());
         }

         // $FF: synthetic method
         public static final OpenHashSet $anonfun$onComplete$3(final int x$3) {
            return new OpenHashSet$mcJ$sp(8, scala.reflect.ClassTag..MODULE$.Long());
         }

         public {
            if (IndexShuffleBlockResolver.this == null) {
               throw null;
            } else {
               this.$outer = IndexShuffleBlockResolver.this;
               this.blockId$3 = blockId$3;
               this.channel$1 = channel$1;
               this.fileTmp$1 = fileTmp$1;
               this.file$2 = file$2;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public List getMigrationBlocks(final ShuffleBlockInfo shuffleBlockInfo) {
      Object var10000;
      try {
         int shuffleId = shuffleBlockInfo.shuffleId();
         long mapId = shuffleBlockInfo.mapId();
         File indexFile = this.getIndexFile(shuffleId, mapId, this.getIndexFile$default$3());
         ShuffleIndexBlockId indexBlockId = new ShuffleIndexBlockId(shuffleId, mapId, IndexShuffleBlockResolver$.MODULE$.NOOP_REDUCE_ID());
         long indexFileSize = indexFile.length();
         FileSegmentManagedBuffer indexBlockData = new FileSegmentManagedBuffer(this.transportConf(), indexFile, 0L, indexFileSize);
         File dataFile = this.getDataFile(shuffleId, mapId);
         ShuffleDataBlockId dataBlockId = new ShuffleDataBlockId(shuffleId, mapId, IndexShuffleBlockResolver$.MODULE$.NOOP_REDUCE_ID());
         FileSegmentManagedBuffer dataBlockData = new FileSegmentManagedBuffer(this.transportConf(), dataFile, 0L, dataFile.length());
         if (!indexFile.exists()) {
            throw org.apache.spark.SparkException..MODULE$.internalError("Index file is deleted already.", "SHUFFLE");
         }

         var10000 = dataFile.exists() ? new scala.collection.immutable..colon.colon(new Tuple2(dataBlockId, dataBlockData), new scala.collection.immutable..colon.colon(new Tuple2(indexBlockId, indexBlockData), scala.collection.immutable.Nil..MODULE$)) : new scala.collection.immutable..colon.colon(new Tuple2(indexBlockId, indexBlockData), scala.collection.immutable.Nil..MODULE$);
      } catch (Exception var13) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to resolve shuffle block "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ". "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SHUFFLE_BLOCK_INFO..MODULE$, shuffleBlockInfo)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"This is expected to occur if a block is removed after decommissioning has started."})))).log(scala.collection.immutable.Nil..MODULE$))));
         var10000 = scala.package..MODULE$.List().empty();
      }

      return (List)var10000;
   }

   public void writeMetadataFileAndCommit(final int shuffleId, final long mapId, final long[] lengths, final long[] checksums, final File dataTmp) {
      File indexFile = this.getIndexFile(shuffleId, mapId, this.getIndexFile$default$3());
      File indexTmp = this.createTempFile(indexFile);
      boolean checksumEnabled = scala.collection.ArrayOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.longArrayOps(checksums));
      Tuple2 var10000;
      if (checksumEnabled) {
         scala.Predef..MODULE$.assert(lengths.length == checksums.length, () -> "The size of partition lengths and checksums should be equal");
         File checksumFile = this.getChecksumFile(shuffleId, mapId, this.algorithm(), this.getChecksumFile$default$4());
         var10000 = new Tuple2(new Some(checksumFile), new Some(this.createTempFile(checksumFile)));
      } else {
         var10000 = new Tuple2(scala.None..MODULE$, scala.None..MODULE$);
      }

      Tuple2 var12 = var10000;
      if (var12 != null) {
         Option checksumFileOpt = (Option)var12._1();
         Option checksumTmpOpt = (Option)var12._2();
         Tuple2 var11 = new Tuple2(checksumFileOpt, checksumTmpOpt);
         Option checksumFileOpt = (Option)var11._1();
         Option checksumTmpOpt = (Option)var11._2();

         try {
            File dataFile = this.getDataFile(shuffleId, mapId);
            synchronized(this){}

            try {
               long[] existingLengths = this.checkIndexAndDataFile(indexFile, dataFile, lengths.length);
               if (existingLengths != null) {
                  System.arraycopy(existingLengths, 0, lengths, 0, lengths.length);
                  if (checksumEnabled) {
                     long[] existingChecksums = this.getChecksums((File)checksumFileOpt.get(), checksums.length);
                     if (existingChecksums != null) {
                        System.arraycopy(existingChecksums, 0, checksums, 0, lengths.length);
                     } else {
                        this.writeMetadataFile(checksums, (File)checksumTmpOpt.get(), (File)checksumFileOpt.get(), false);
                     }
                  }

                  if (dataTmp != null && dataTmp.exists()) {
                     BoxesRunTime.boxToBoolean(dataTmp.delete());
                  } else {
                     BoxedUnit var30 = BoxedUnit.UNIT;
                  }
               } else {
                  long[] offsets = (long[])scala.collection.ArrayOps..MODULE$.scanLeft$extension(scala.Predef..MODULE$.longArrayOps(lengths), BoxesRunTime.boxToLong(0L), (JFunction2.mcJJJ.sp)(x$5, x$6) -> x$5 + x$6, scala.reflect.ClassTag..MODULE$.Long());
                  this.writeMetadataFile(offsets, indexTmp, indexFile, true);
                  if (dataFile.exists()) {
                     BoxesRunTime.boxToBoolean(dataFile.delete());
                  } else {
                     BoxedUnit var31 = BoxedUnit.UNIT;
                  }

                  if (dataTmp != null && dataTmp.exists() && !dataTmp.renameTo(dataFile)) {
                     throw SparkCoreErrors$.MODULE$.failedRenameTempFileError(dataTmp, dataFile);
                  }

                  checksumTmpOpt.zip(checksumFileOpt).foreach((x0$1) -> {
                     $anonfun$writeMetadataFileAndCommit$3(this, checksums, x0$1);
                     return BoxedUnit.UNIT;
                  });
                  BoxedUnit var32 = BoxedUnit.UNIT;
               }
            } catch (Throwable var28) {
               throw var28;
            }
         } finally {
            this.logDebug((Function0)(() -> "Shuffle index for mapId " + mapId + ": " + scala.Predef..MODULE$.wrapLongArray(lengths).mkString("[", ",", "]")));
            if (indexTmp.exists() && !indexTmp.delete()) {
               this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to delete temporary index file at "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, indexTmp.getAbsolutePath())}))))));
            }

            checksumTmpOpt.foreach((checksumTmp) -> {
               $anonfun$writeMetadataFileAndCommit$7(this, checksumTmp);
               return BoxedUnit.UNIT;
            });
         }

      } else {
         throw new MatchError(var12);
      }
   }

   private void writeMetadataFile(final long[] metaValues, final File tmpFile, final File targetFile, final boolean propagateError) {
      DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(tmpFile)));
      Utils$.MODULE$.tryWithSafeFinally((JFunction0.mcV.sp)() -> scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.longArrayOps(metaValues), (JFunction1.mcVJ.sp)(x$1) -> out.writeLong(x$1)), (JFunction0.mcV.sp)() -> out.close());
      if (targetFile.exists()) {
         BoxesRunTime.boxToBoolean(targetFile.delete());
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      if (!tmpFile.renameTo(targetFile)) {
         if (propagateError) {
            throw SparkCoreErrors$.MODULE$.failedRenameTempFileError(tmpFile, targetFile);
         } else {
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"fail to rename file ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TEMP_FILE..MODULE$, tmpFile)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"to ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TARGET_PATH..MODULE$, targetFile)}))))));
         }
      }
   }

   public Seq getMergedBlockData(final ShuffleMergedBlockId blockId, final Option dirs) {
      File indexFile = this.getMergedBlockIndexFile(this.conf.getAppId(), blockId.shuffleId(), blockId.shuffleMergeId(), blockId.reduceId(), dirs);
      File dataFile = this.getMergedBlockDataFile(this.conf.getAppId(), blockId.shuffleId(), blockId.shuffleMergeId(), blockId.reduceId(), dirs);
      int size = (int)indexFile.length();
      LongBuffer offsets = (LongBuffer)Utils$.MODULE$.tryWithResource(() -> new DataInputStream(Files.newInputStream(indexFile.toPath())), (dis) -> {
         ByteBuffer buffer = ByteBuffer.allocate(size);
         dis.readFully(buffer.array());
         return buffer.asLongBuffer();
      });
      int numChunks = size / 8 - 1;
      return scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), numChunks).map((index) -> $anonfun$getMergedBlockData$3(this, dataFile, offsets, BoxesRunTime.unboxToInt(index)));
   }

   private Option getMergedBlockDataFile$default$5() {
      return scala.None..MODULE$;
   }

   public MergedBlockMeta getMergedBlockMeta(final ShuffleMergedBlockId blockId, final Option dirs) {
      File indexFile = this.getMergedBlockIndexFile(this.conf.getAppId(), blockId.shuffleId(), blockId.shuffleMergeId(), blockId.reduceId(), dirs);
      int size = (int)indexFile.length();
      int numChunks = size / 8 - 1;
      File metaFile = this.getMergedBlockMetaFile(this.conf.getAppId(), blockId.shuffleId(), blockId.shuffleMergeId(), blockId.reduceId(), dirs);
      FileSegmentManagedBuffer chunkBitMaps = new FileSegmentManagedBuffer(this.transportConf(), metaFile, 0L, metaFile.length());
      return new MergedBlockMeta(numChunks, chunkBitMaps);
   }

   private Option getMergedBlockMetaFile$default$5() {
      return scala.None..MODULE$;
   }

   public long[] getChecksums(final File checksumFile, final int blockNum) {
      if (!checksumFile.exists()) {
         return null;
      } else {
         ArrayBuffer checksums = new ArrayBuffer();
         DataInputStream in = null;

         Object var9;
         try {
            try {
               in = new DataInputStream(new NioBufferedFileInputStream(checksumFile));

               while(checksums.size() < blockNum) {
                  checksums.$plus$eq(BoxesRunTime.boxToLong(in.readLong()));
               }

               return (long[])checksums.toArray(scala.reflect.ClassTag..MODULE$.Long());
            } catch (Throwable var13) {
            }

            if (!(var13 instanceof IOException ? true : var13 instanceof EOFException)) {
               throw var13;
            }

            var9 = null;
         } finally {
            in.close();
         }

         return (long[])var9;
      }
   }

   public File getChecksumFile(final int shuffleId, final long mapId, final String algorithm, final Option dirs) {
      ShuffleChecksumBlockId blockId = new ShuffleChecksumBlockId(shuffleId, mapId, IndexShuffleBlockResolver$.MODULE$.NOOP_REDUCE_ID());
      String fileName = ShuffleChecksumHelper.getChecksumFileName(blockId.name(), algorithm);
      return (File)dirs.map((d) -> new File(ExecutorDiskUtils.getFilePath(d, this.org$apache$spark$shuffle$IndexShuffleBlockResolver$$blockManager().subDirsPerLocalDir(), fileName))).getOrElse(() -> this.org$apache$spark$shuffle$IndexShuffleBlockResolver$$blockManager().diskBlockManager().getFile(fileName));
   }

   public Option getChecksumFile$default$4() {
      return scala.None..MODULE$;
   }

   public ManagedBuffer getBlockData(final BlockId blockId, final Option dirs) {
      Tuple4 var10000;
      if (blockId instanceof ShuffleBlockId var8) {
         var10000 = new Tuple4(BoxesRunTime.boxToInteger(var8.shuffleId()), BoxesRunTime.boxToLong(var8.mapId()), BoxesRunTime.boxToInteger(var8.reduceId()), BoxesRunTime.boxToInteger(var8.reduceId() + 1));
      } else {
         if (!(blockId instanceof ShuffleBlockBatchId)) {
            throw org.apache.spark.SparkException..MODULE$.internalError("unexpected shuffle block id format: " + blockId, "SHUFFLE");
         }

         ShuffleBlockBatchId var9 = (ShuffleBlockBatchId)blockId;
         var10000 = new Tuple4(BoxesRunTime.boxToInteger(var9.shuffleId()), BoxesRunTime.boxToLong(var9.mapId()), BoxesRunTime.boxToInteger(var9.startReduceId()), BoxesRunTime.boxToInteger(var9.endReduceId()));
      }

      Tuple4 var6 = var10000;
      if (var6 != null) {
         int shuffleId = BoxesRunTime.unboxToInt(var6._1());
         long mapId = BoxesRunTime.unboxToLong(var6._2());
         int startReduceId = BoxesRunTime.unboxToInt(var6._3());
         int endReduceId = BoxesRunTime.unboxToInt(var6._4());
         Tuple4 var5 = new Tuple4(BoxesRunTime.boxToInteger(shuffleId), BoxesRunTime.boxToLong(mapId), BoxesRunTime.boxToInteger(startReduceId), BoxesRunTime.boxToInteger(endReduceId));
         int shuffleId = BoxesRunTime.unboxToInt(var5._1());
         long mapId = BoxesRunTime.unboxToLong(var5._2());
         int startReduceId = BoxesRunTime.unboxToInt(var5._3());
         int endReduceId = BoxesRunTime.unboxToInt(var5._4());
         File indexFile = this.getIndexFile(shuffleId, mapId, dirs);
         SeekableByteChannel channel = Files.newByteChannel(indexFile.toPath());
         channel.position((long)startReduceId * 8L);
         DataInputStream in = new DataInputStream(Channels.newInputStream(channel));

         try {
            long startOffset = in.readLong();
            channel.position((long)endReduceId * 8L);
            long endOffset = in.readLong();
            long actualPosition = channel.position();
            long expectedPosition = (long)endReduceId * 8L + 8L;
            if (actualPosition != expectedPosition) {
               throw org.apache.spark.SparkException..MODULE$.internalError("SPARK-22982: Incorrect channel position after index file reads: expected " + expectedPosition + " but actual position was " + actualPosition + ".", "SHUFFLE");
            }

            new FileSegmentManagedBuffer(this.transportConf(), this.getDataFile(shuffleId, mapId, dirs), startOffset, endOffset - startOffset);
         } finally {
            in.close();
         }

         return in;
      } else {
         throw new MatchError(var6);
      }
   }

   public Seq getBlocksForShuffle(final int shuffleId, final long mapId) {
      return new scala.collection.immutable..colon.colon(new ShuffleIndexBlockId(shuffleId, mapId, IndexShuffleBlockResolver$.MODULE$.NOOP_REDUCE_ID()), new scala.collection.immutable..colon.colon(new ShuffleDataBlockId(shuffleId, mapId, IndexShuffleBlockResolver$.MODULE$.NOOP_REDUCE_ID()), scala.collection.immutable.Nil..MODULE$));
   }

   public void stop() {
   }

   // $FF: synthetic method
   public static final long $anonfun$getShuffleBytesStored$2(final File x$1) {
      return x$1.length();
   }

   // $FF: synthetic method
   public static final void $anonfun$writeMetadataFileAndCommit$3(final IndexShuffleBlockResolver $this, final long[] checksums$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         File checksumTmp = (File)x0$1._1();
         File checksumFile = (File)x0$1._2();

         try {
            $this.writeMetadataFile(checksums$1, checksumTmp, checksumFile, false);
            BoxedUnit var9 = BoxedUnit.UNIT;
         } catch (Exception var8) {
            $this.logError((Function0)(() -> "Failed to write checksum file"), var8);
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$writeMetadataFileAndCommit$7(final IndexShuffleBlockResolver $this, final File checksumTmp) {
      if (checksumTmp.exists()) {
         try {
            if (!checksumTmp.delete()) {
               $this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to delete temporary checksum file at "})))).log(scala.collection.immutable.Nil..MODULE$).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, checksumTmp.getAbsolutePath())}))))));
            }
         } catch (Exception var3) {
            $this.logError((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to delete temporary checksum file "})))).log(scala.collection.immutable.Nil..MODULE$).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"at ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, checksumTmp.getAbsolutePath())}))))), var3);
         }

      }
   }

   // $FF: synthetic method
   public static final FileSegmentManagedBuffer $anonfun$getMergedBlockData$3(final IndexShuffleBlockResolver $this, final File dataFile$1, final LongBuffer offsets$1, final int index) {
      return new FileSegmentManagedBuffer($this.transportConf(), dataFile$1, offsets$1.get(index), offsets$1.get(index + 1) - offsets$1.get(index));
   }

   public IndexShuffleBlockResolver(final SparkConf conf, final BlockManager _blockManager, final Map taskIdMapsForShuffle) {
      this.conf = conf;
      this._blockManager = _blockManager;
      this.taskIdMapsForShuffle = taskIdMapsForShuffle;
      super();
      ShuffleBlockResolver.$init$(this);
      Logging.$init$(this);
      MigratableResolver.$init$(this);
      SecurityManager securityManager = new SecurityManager(conf, SecurityManager$.MODULE$.$lessinit$greater$default$2(), SecurityManager$.MODULE$.$lessinit$greater$default$3());
      String x$2 = "shuffle";
      Some x$3 = new Some(securityManager.getRpcSSLOptions());
      int x$4 = SparkTransportConf$.MODULE$.fromSparkConf$default$3();
      Option x$5 = SparkTransportConf$.MODULE$.fromSparkConf$default$4();
      this.transportConf = SparkTransportConf$.MODULE$.fromSparkConf(conf, "shuffle", x$4, x$5, x$3);
      this.remoteShuffleMaxDisk = (Option)conf.get((ConfigEntry)package$.MODULE$.STORAGE_DECOMMISSION_SHUFFLE_MAX_DISK_SIZE());
      this.checksumEnabled = BoxesRunTime.unboxToBoolean(conf.get(package$.MODULE$.SHUFFLE_CHECKSUM_ENABLED()));
      this.shuffleIdsToSkip = CacheBuilder.newBuilder().maximumSize(1000L).build();
   }

   public IndexShuffleBlockResolver(final SparkConf conf) {
      this(conf, (BlockManager)null, Collections.emptyMap());
   }

   public IndexShuffleBlockResolver(final SparkConf conf, final BlockManager _blockManager) {
      this(conf, _blockManager, Collections.emptyMap());
   }

   public IndexShuffleBlockResolver(final SparkConf conf, final Map taskIdMapsForShuffle) {
      this(conf, (BlockManager)null, taskIdMapsForShuffle);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
