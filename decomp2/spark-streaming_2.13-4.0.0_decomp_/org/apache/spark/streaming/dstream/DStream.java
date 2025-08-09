package org.apache.spark.streaming.dstream;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkContext;
import org.apache.spark.SparkException;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.rdd.BlockRDD;
import org.apache.spark.rdd.RDD;
import org.apache.spark.rdd.RDDOperationScope;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.DStreamGraph;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.Interval;
import org.apache.spark.streaming.Seconds$;
import org.apache.spark.streaming.StreamingConf$;
import org.apache.spark.streaming.StreamingContext;
import org.apache.spark.streaming.StreamingContext$;
import org.apache.spark.streaming.StreamingContextState;
import org.apache.spark.streaming.Time;
import org.apache.spark.streaming.scheduler.Job;
import org.apache.spark.ui.UIUtils.;
import org.apache.spark.util.CallSite;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.MatchError;
import scala.Option;
import scala.Predef;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.mutable.HashMap;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Null;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015eh!\u0002:t\u0003\u0003q\bbCA\u0019\u0001\t\u0005\r\u0011\"\u0001v\u0003gA1\"!\u0010\u0001\u0005\u0003\u0007I\u0011A;\u0002@!Q\u00111\n\u0001\u0003\u0002\u0003\u0006K!!\u000e\t\u0015\u0005U\u0003AaA!\u0002\u0017\t9\u0006C\u0004\u0002z\u0001!\t!a\u001f\t\u000f\u0005\u001d\u0005A\"\u0001\u0002\n\"9\u0011\u0011\u0013\u0001\u0007\u0002\u0005M\u0005bBAS\u0001\u0019\u0005\u0011q\u0015\u0005\u000b\u0003\u000b\u0004\u0001\u0019!C\u0001k\u0006\u001d\u0007BCAm\u0001\u0001\u0007I\u0011A;\u0002\\\"A\u0011q\u001c\u0001!B\u0013\tI\r\u0003\u0006\u0002d\u0002\u0001\r\u0011\"\u0001v\u0003KD!\"a:\u0001\u0001\u0004%\t!^Au\u0011!\ti\u000f\u0001Q!\n\u0005}\u0006BCAx\u0001\u0001\u0007I\u0011A;\u0002\n\"Q\u0011\u0011\u001f\u0001A\u0002\u0013\u0005Q/a=\t\u0011\u0005]\b\u0001)Q\u0005\u0003\u0017C!\"!?\u0001\u0001\u0004%\t!^A~\u0011)\u0011I\u0001\u0001a\u0001\n\u0003)(1\u0002\u0005\t\u0005\u001f\u0001\u0001\u0015)\u0003\u0002~\"Q!\u0011\u0003\u0001C\u0002\u0013\u0005QOa\u0005\t\u0011\tm\u0001\u0001)A\u0005\u0005+A!B!\b\u0001\u0001\u0004%\t!^AE\u0011)\u0011y\u0002\u0001a\u0001\n\u0003)(\u0011\u0005\u0005\t\u0005K\u0001\u0001\u0015)\u0003\u0002\f\"Q!q\u0005\u0001C\u0002\u0013\u0005QO!\u000b\t\u0011\tE\u0002\u0001)A\u0005\u0005WA\u0011Ba\r\u0001\u0001\u0004%IAa\u0005\t\u0013\tU\u0002\u00011A\u0005\n\t]\u0002\u0002\u0003B\u001e\u0001\u0001\u0006KA!\u0006\t\u0015\t}\u0002\u00011A\u0005\u0002U\u0014\t\u0005\u0003\u0006\u0003J\u0001\u0001\r\u0011\"\u0001v\u0005\u0017B\u0001Ba\u0014\u0001A\u0003&!1\t\u0005\t\u0005#\u0002A\u0011A;\u0003\u0014!A!1\u000b\u0001\u0005\u0002U\fI\tC\u0004\u0003V\u0001!\t!a\r\t\u0015\t]\u0003A1A\u0005\u0002U\u0014I\u0006\u0003\u0005\u0003h\u0001\u0001\u000b\u0011\u0002B.\u0011)\u0011I\u0007\u0001b\u0001\n#)(1\u000e\u0005\t\u0005\u007f\u0002\u0001\u0015!\u0003\u0003n!9!\u0011\u0011\u0001\u0005\n\t\r\u0005b\u0002BI\u0001\u0011\u0005!1\u0013\u0005\b\u0005#\u0003A\u0011\u0001BM\u0011\u001d\u0011Y\n\u0001C\u0001\u00053CqA!(\u0001\t\u0003\u0011y\n\u0003\u0005\u0003&\u0002!\t!\u001eBT\u0011\u001d\u0011Y\u000b\u0001C\u0005\u0005[C\u0001Ba,\u0001\t\u0003)(Q\u0016\u0005\t\u0005c\u0003A\u0011A;\u00034\"A!\u0011\u0018\u0001\u0005\u0002U\u0014Y\f\u0003\u0005\u0003B\u0002!\t!\u001eBb\u0011!\u0011I\r\u0001C\u0001k\n-\u0007\u0002\u0003Bh\u0001\u0011\u0015QO!5\t\u0011\tU\u0007\u0001\"\u0005v\u0005/D\u0001Ba=\u0001\t\u0003)(Q\u001f\u0005\t\u0007\u000f\u0001A\u0011A;\u0004\n!A1Q\u0002\u0001\u0005\u0002U\u001cy\u0001\u0003\u0005\u0004\u0016\u0001!\t!^B\f\u0011!\u0019Y\u0002\u0001C\u0001k\n5\u0006bBB\u000f\u0001\u0011%1q\u0004\u0005\b\u0007\u000b\u0002A\u0011BB$\u0011\u001d\u0019)\u0006\u0001C\u0001\u0007/Bqaa\u001d\u0001\t\u0003\u0019)\bC\u0004\u0004\u0014\u0002!\ta!&\t\u000f\ru\u0005\u0001\"\u0001\u0004 \"91\u0011\u0016\u0001\u0005\u0002\r-\u0006bBB\\\u0001\u0011\u00051\u0011\u0018\u0005\n\u0007;\u0004\u0011\u0013!C\u0001\u0007?Dqa!?\u0001\t\u0003\u0019Y\u0010C\u0004\u0005\b\u0001!\t\u0001\"\u0003\t\u000f\u0011M\u0001\u0001\"\u0001\u0005\u0016!IAQ\u0006\u0001\u0012\u0002\u0013\u0005Aq\u0006\u0005\n\tg\u0001\u0011\u0013!C\u0001\tkAq\u0001b\u000f\u0001\t\u0003!i\u0004C\u0004\u0005<\u0001!\t\u0001\"\u0012\t\u000f\u0011m\u0002\u0001\"\u0003\u0005L!9A\u0011\u000b\u0001\u0005\u0002\u0011M\u0003b\u0002C)\u0001\u0011\u0005AQ\u000e\u0005\b\t\u000b\u0003A\u0011\u0001CD\u0011\u001d!)\t\u0001C\u0001\tgCq\u0001b8\u0001\t\u0003\u0011i\u000bC\u0004\u0005`\u0002!\t\u0001\"9\t\u000f\u0011\u001d\b\u0001\"\u0001\u0005j\"9Aq\u001d\u0001\u0005\u0002\u0011=\bb\u0002C{\u0001\u0011\u0005Aq\u001f\u0005\b\tk\u0004A\u0011\u0001C\u0000\u0011\u001d)Y\u0001\u0001C\u0001\u000b\u001bAq!b\u0005\u0001\t\u0003))\u0002C\u0005\u0006\"\u0001\t\n\u0011\"\u0001\u00050!IQ1\u0005\u0001\u0012\u0002\u0013\u0005QQ\u0005\u0005\b\u000b[\u0001A\u0011AC\u0018\u0011\u001d))\u0004\u0001C\u0001\u000boAq!\"\u000e\u0001\t\u0003)9\u0005C\u0004\u0006R\u0001!\t!b\u0015\t\u0013\u0015u\u0003!%A\u0005\u0002\u0015}\u0003bBC2\u0001\u0011\u0005QQ\r\u0005\n\u000bW\u0002\u0011\u0013!C\u0001\u000b?B\u0001\"\"\u001c\u0001\t\u0003)(\u0011T\u0004\b\u000b_\u001a\b\u0012AC9\r\u0019\u00118\u000f#\u0001\u0006t!9\u0011\u0011\u00103\u0005\u0002\u0015e\u0004\"CC>I\n\u0007I\u0011BC?\u0011!)i\t\u001aQ\u0001\n\u0015}\u0004\"CCHI\n\u0007I\u0011BC?\u0011!)\t\n\u001aQ\u0001\n\u0015}\u0004\"CCJI\n\u0007I\u0011BC?\u0011!))\n\u001aQ\u0001\n\u0015}\u0004\"CCLI\n\u0007I\u0011BC?\u0011!)I\n\u001aQ\u0001\n\u0015}\u0004bBCNI\u0012\rQQ\u0014\u0005\n\u000b\u0017$\u0017\u0013!C\u0001\u000b\u001bD\u0001\"\":e\t\u0003)Xq\u001d\u0005\n\u000bS$\u0017\u0011!C\u0005\u000bW\u0014q\u0001R*ue\u0016\fWN\u0003\u0002uk\u00069Am\u001d;sK\u0006l'B\u0001<x\u0003%\u0019HO]3b[&twM\u0003\u0002ys\u0006)1\u000f]1sW*\u0011!p_\u0001\u0007CB\f7\r[3\u000b\u0003q\f1a\u001c:h\u0007\u0001)2a`A4'\u001d\u0001\u0011\u0011AA\u0007\u0003K\u0001B!a\u0001\u0002\n5\u0011\u0011Q\u0001\u0006\u0003\u0003\u000f\tQa]2bY\u0006LA!a\u0003\u0002\u0006\t1\u0011I\\=SK\u001a\u0004B!a\u0004\u0002 9!\u0011\u0011CA\u000e\u001d\u0011\t\u0019\"!\u0007\u000e\u0005\u0005U!bAA\f{\u00061AH]8pizJ!!a\u0002\n\t\u0005u\u0011QA\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\t\t#a\t\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\t\u0005u\u0011Q\u0001\t\u0005\u0003O\ti#\u0004\u0002\u0002*)\u0019\u00111F<\u0002\u0011%tG/\u001a:oC2LA!a\f\u0002*\t9Aj\\4hS:<\u0017aA:tGV\u0011\u0011Q\u0007\t\u0005\u0003o\tI$D\u0001v\u0013\r\tY$\u001e\u0002\u0011'R\u0014X-Y7j]\u001e\u001cuN\u001c;fqR\fqa]:d?\u0012*\u0017\u000f\u0006\u0003\u0002B\u0005\u001d\u0003\u0003BA\u0002\u0003\u0007JA!!\u0012\u0002\u0006\t!QK\\5u\u0011%\tIEAA\u0001\u0002\u0004\t)$A\u0002yIE\nAa]:dA!\u001a1!a\u0014\u0011\t\u0005\r\u0011\u0011K\u0005\u0005\u0003'\n)AA\u0005ue\u0006t7/[3oi\u0006QQM^5eK:\u001cW\rJ\u0019\u0011\r\u0005e\u0013qLA2\u001b\t\tYF\u0003\u0003\u0002^\u0005\u0015\u0011a\u0002:fM2,7\r^\u0005\u0005\u0003C\nYF\u0001\u0005DY\u0006\u001c8\u000fV1h!\u0011\t)'a\u001a\r\u0001\u00119\u0011\u0011\u000e\u0001C\u0002\u0005-$!\u0001+\u0012\t\u00055\u00141\u000f\t\u0005\u0003\u0007\ty'\u0003\u0003\u0002r\u0005\u0015!a\u0002(pi\"Lgn\u001a\t\u0005\u0003\u0007\t)(\u0003\u0003\u0002x\u0005\u0015!aA!os\u00061A(\u001b8jiz\"B!! \u0002\u0006R!\u0011qPAB!\u0015\t\t\tAA2\u001b\u0005\u0019\bbBA+\u000b\u0001\u000f\u0011q\u000b\u0005\b\u0003c)\u0001\u0019AA\u001b\u00035\u0019H.\u001b3f\tV\u0014\u0018\r^5p]V\u0011\u00111\u0012\t\u0005\u0003o\ti)C\u0002\u0002\u0010V\u0014\u0001\u0002R;sCRLwN\\\u0001\rI\u0016\u0004XM\u001c3f]\u000eLWm]\u000b\u0003\u0003+\u0003b!a\u0004\u0002\u0018\u0006m\u0015\u0002BAM\u0003G\u0011A\u0001T5tiB\"\u0011QTAQ!\u0015\t\t\tAAP!\u0011\t)'!)\u0005\u0017\u0005\rv!!A\u0001\u0002\u000b\u0005\u00111\u000e\u0002\u0004?\u0012\n\u0014aB2p[B,H/\u001a\u000b\u0005\u0003S\u000bY\f\u0005\u0004\u0002\u0004\u0005-\u0016qV\u0005\u0005\u0003[\u000b)A\u0001\u0004PaRLwN\u001c\t\u0007\u0003c\u000b9,a\u0019\u000e\u0005\u0005M&bAA[o\u0006\u0019!\u000f\u001a3\n\t\u0005e\u00161\u0017\u0002\u0004%\u0012#\u0005bBA_\u0011\u0001\u0007\u0011qX\u0001\nm\u0006d\u0017\u000e\u001a+j[\u0016\u0004B!a\u000e\u0002B&\u0019\u00111Y;\u0003\tQKW.Z\u0001\u000eO\u0016tWM]1uK\u0012\u0014F\tR:\u0016\u0005\u0005%\u0007\u0003CAf\u0003+\fy,a,\u000e\u0005\u00055'\u0002BAh\u0003#\fq!\\;uC\ndWM\u0003\u0003\u0002T\u0006\u0015\u0011AC2pY2,7\r^5p]&!\u0011q[Ag\u0005\u001dA\u0015m\u001d5NCB\f\u0011cZ3oKJ\fG/\u001a3S\t\u0012\u001bx\fJ3r)\u0011\t\t%!8\t\u0013\u0005%#\"!AA\u0002\u0005%\u0017AD4f]\u0016\u0014\u0018\r^3e%\u0012#5\u000f\t\u0015\u0004\u0017\u0005=\u0013\u0001\u0003>fe>$\u0016.\\3\u0016\u0005\u0005}\u0016\u0001\u0004>fe>$\u0016.\\3`I\u0015\fH\u0003BA!\u0003WD\u0011\"!\u0013\u000e\u0003\u0003\u0005\r!a0\u0002\u0013i,'o\u001c+j[\u0016\u0004\u0013\u0001\u0005:f[\u0016l'-\u001a:EkJ\fG/[8o\u0003Q\u0011X-\\3nE\u0016\u0014H)\u001e:bi&|gn\u0018\u0013fcR!\u0011\u0011IA{\u0011%\tI\u0005EA\u0001\u0002\u0004\tY)A\tsK6,WNY3s\tV\u0014\u0018\r^5p]\u0002\nAb\u001d;pe\u0006<W\rT3wK2,\"!!@\u0011\t\u0005}(QA\u0007\u0003\u0005\u0003Q1Aa\u0001x\u0003\u001d\u0019Ho\u001c:bO\u0016LAAa\u0002\u0003\u0002\ta1\u000b^8sC\u001e,G*\u001a<fY\u0006\u00012\u000f^8sC\u001e,G*\u001a<fY~#S-\u001d\u000b\u0005\u0003\u0003\u0012i\u0001C\u0005\u0002JM\t\t\u00111\u0001\u0002~\u0006i1\u000f^8sC\u001e,G*\u001a<fY\u0002\na\"\\;ti\u000eCWmY6q_&tG/\u0006\u0002\u0003\u0016A!\u00111\u0001B\f\u0013\u0011\u0011I\"!\u0002\u0003\u000f\t{w\u000e\\3b]\u0006yQ.^:u\u0007\",7m\u001b9pS:$\b%\u0001\ndQ\u0016\u001c7\u000e]8j]R$UO]1uS>t\u0017AF2iK\u000e\\\u0007o\\5oi\u0012+(/\u0019;j_:|F%Z9\u0015\t\u0005\u0005#1\u0005\u0005\n\u0003\u0013B\u0012\u0011!a\u0001\u0003\u0017\u000b1c\u00195fG.\u0004x.\u001b8u\tV\u0014\u0018\r^5p]\u0002\nab\u00195fG.\u0004x.\u001b8u\t\u0006$\u0018-\u0006\u0002\u0003,A1\u0011\u0011\u0011B\u0017\u0003GJ1Aa\ft\u0005U!5\u000b\u001e:fC6\u001c\u0005.Z2la>Lg\u000e\u001e#bi\u0006\fqb\u00195fG.\u0004x.\u001b8u\t\u0006$\u0018\rI\u0001\u001be\u0016\u001cHo\u001c:fI\u001a\u0013x.\\\"iK\u000e\\\u0007o\\5oi\u0012\u000bG/Y\u0001\u001fe\u0016\u001cHo\u001c:fI\u001a\u0013x.\\\"iK\u000e\\\u0007o\\5oi\u0012\u000bG/Y0%KF$B!!\u0011\u0003:!I\u0011\u0011J\u000f\u0002\u0002\u0003\u0007!QC\u0001\u001ce\u0016\u001cHo\u001c:fI\u001a\u0013x.\\\"iK\u000e\\\u0007o\\5oi\u0012\u000bG/\u0019\u0011)\u0007y\ty%A\u0003he\u0006\u0004\b.\u0006\u0002\u0003DA!\u0011q\u0007B#\u0013\r\u00119%\u001e\u0002\r\tN#(/Z1n\u000fJ\f\u0007\u000f[\u0001\nOJ\f\u0007\u000f[0%KF$B!!\u0011\u0003N!I\u0011\u0011\n\u0011\u0002\u0002\u0003\u0007!1I\u0001\u0007OJ\f\u0007\u000f\u001b\u0011\u0002\u001b%\u001c\u0018J\\5uS\u0006d\u0017N_3e\u0003Y\u0001\u0018M]3oiJ+W.Z7cKJ$UO]1uS>t\u0017aB2p]R,\u0007\u0010^\u0001\rGJ,\u0017\r^5p]NKG/Z\u000b\u0003\u00057\u0002BA!\u0018\u0003d5\u0011!q\f\u0006\u0004\u0005C:\u0018\u0001B;uS2LAA!\u001a\u0003`\tA1)\u00197m'&$X-A\u0007de\u0016\fG/[8o'&$X\rI\u0001\nE\u0006\u001cXmU2pa\u0016,\"A!\u001c\u0011\r\u0005\r\u00111\u0016B8!\u0011\u0011\tH!\u001f\u000f\t\tM$Q\u000f\t\u0005\u0003'\t)!\u0003\u0003\u0003x\u0005\u0015\u0011A\u0002)sK\u0012,g-\u0003\u0003\u0003|\tu$AB*ue&twM\u0003\u0003\u0003x\u0005\u0015\u0011A\u00032bg\u0016\u001c6m\u001c9fA\u0005IQ.Y6f'\u000e|\u0007/\u001a\u000b\u0005\u0005\u000b\u0013i\t\u0005\u0004\u0002\u0004\u0005-&q\u0011\t\u0005\u0003c\u0013I)\u0003\u0003\u0003\f\u0006M&!\u0005*E\t>\u0003XM]1uS>t7kY8qK\"9!qR\u0015A\u0002\u0005}\u0016\u0001\u0002;j[\u0016\fq\u0001]3sg&\u001cH\u000f\u0006\u0003\u0002\u0000\tU\u0005b\u0002BLU\u0001\u0007\u0011Q`\u0001\u0006Y\u00164X\r\u001c\u000b\u0003\u0003\u007f\nQaY1dQ\u0016\f!b\u00195fG.\u0004x.\u001b8u)\u0011\tyH!)\t\u000f\t\rV\u00061\u0001\u0002\f\u0006A\u0011N\u001c;feZ\fG.\u0001\u0006j]&$\u0018.\u00197ju\u0016$B!!\u0011\u0003*\"9!q\u0012\u0018A\u0002\u0005}\u0016A\u0004<bY&$\u0017\r^3Bi&s\u0017\u000e\u001e\u000b\u0003\u0003\u0003\nqB^1mS\u0012\fG/Z!u'R\f'\u000f^\u0001\u000bg\u0016$8i\u001c8uKb$H\u0003BA!\u0005kCqAa.2\u0001\u0004\t)$A\u0001t\u0003!\u0019X\r^$sCBDG\u0003BA!\u0005{CqAa03\u0001\u0004\u0011\u0019%A\u0001h\u0003!\u0011X-\\3nE\u0016\u0014H\u0003BA!\u0005\u000bDqAa24\u0001\u0004\tY)\u0001\u0005ekJ\fG/[8o\u0003-I7\u000fV5nKZ\u000bG.\u001b3\u0015\t\tU!Q\u001a\u0005\b\u0005\u001f#\u0004\u0019AA`\u000319W\r^(s\u0007>l\u0007/\u001e;f)\u0011\tIKa5\t\u000f\t=U\u00071\u0001\u0002@\u0006a2M]3bi\u0016\u0014F\tR,ji\"dunY1m!J|\u0007/\u001a:uS\u0016\u001cX\u0003\u0002Bm\u0005?$bAa7\u0003n\n=H\u0003\u0002Bo\u0005G\u0004B!!\u001a\u0003`\u00129!\u0011\u001d\u001cC\u0002\u0005-$!A+\t\u0011\t\u0015h\u0007\"a\u0001\u0005O\fAAY8esB1\u00111\u0001Bu\u0005;LAAa;\u0002\u0006\tAAHY=oC6,g\bC\u0004\u0003\u0010Z\u0002\r!a0\t\u000f\tEh\u00071\u0001\u0003\u0016\u0005\u0011B-[:qY\u0006L\u0018J\u001c8feJ#Ei\u00149t\u0003-9WM\\3sCR,'j\u001c2\u0015\t\t]8Q\u0001\t\u0007\u0003\u0007\tYK!?\u0011\t\tm8\u0011A\u0007\u0003\u0005{T1Aa@v\u0003%\u00198\r[3ek2,'/\u0003\u0003\u0004\u0004\tu(a\u0001&pE\"9!qR\u001cA\u0002\u0005}\u0016!D2mK\u0006\u0014X*\u001a;bI\u0006$\u0018\r\u0006\u0003\u0002B\r-\u0001b\u0002BHq\u0001\u0007\u0011qX\u0001\u0015kB$\u0017\r^3DQ\u0016\u001c7\u000e]8j]R$\u0015\r^1\u0015\t\u0005\u00053\u0011\u0003\u0005\b\u0007'I\u0004\u0019AA`\u0003-\u0019WO\u001d:f]R$\u0016.\\3\u0002'\rdW-\u0019:DQ\u0016\u001c7\u000e]8j]R$\u0015\r^1\u0015\t\u0005\u00053\u0011\u0004\u0005\b\u0005\u001fS\u0004\u0019AA`\u0003U\u0011Xm\u001d;pe\u0016\u001c\u0005.Z2la>Lg\u000e\u001e#bi\u0006\f1b\u001e:ji\u0016|%M[3diR!\u0011\u0011IB\u0011\u0011\u001d\u0019\u0019\u0003\u0010a\u0001\u0007K\t1a\\8t!\u0011\u00199c!\r\u000e\u0005\r%\"\u0002BB\u0016\u0007[\t!![8\u000b\u0005\r=\u0012\u0001\u00026bm\u0006LAaa\r\u0004*\t\u0011rJ\u00196fGR|U\u000f\u001e9viN#(/Z1nQ\u0015a4qGB\"!\u0019\t\u0019a!\u000f\u0004>%!11HA\u0003\u0005\u0019!\bN]8xgB!1qEB \u0013\u0011\u0019\te!\u000b\u0003\u0017%{U\t_2faRLwN\\\u0012\u0003\u0007{\t!B]3bI>\u0013'.Z2u)\u0011\t\te!\u0013\t\u000f\r-S\b1\u0001\u0004N\u0005\u0019q.[:\u0011\t\r\u001d2qJ\u0005\u0005\u0007#\u001aICA\tPE*,7\r^%oaV$8\u000b\u001e:fC6DS!PB\u001c\u0007\u0007\n1!\\1q+\u0011\u0019If!\u0019\u0015\t\rm3\u0011\u000e\u000b\u0005\u0007;\u001a\u0019\u0007E\u0003\u0002\u0002\u0002\u0019y\u0006\u0005\u0003\u0002f\r\u0005Da\u0002Bq}\t\u0007\u00111\u000e\u0005\n\u0007Kr\u0014\u0011!a\u0002\u0007O\n!\"\u001a<jI\u0016t7-\u001a\u00133!\u0019\tI&a\u0018\u0004`!911\u000e A\u0002\r5\u0014aB7ba\u001a+hn\u0019\t\t\u0003\u0007\u0019y'a\u0019\u0004`%!1\u0011OA\u0003\u0005%1UO\\2uS>t\u0017'A\u0004gY\u0006$X*\u00199\u0016\t\r]4q\u0010\u000b\u0005\u0007s\u001a9\t\u0006\u0003\u0004|\r\u0005\u0005#BAA\u0001\ru\u0004\u0003BA3\u0007\u007f\"qA!9@\u0005\u0004\tY\u0007C\u0005\u0004\u0004~\n\t\u0011q\u0001\u0004\u0006\u0006QQM^5eK:\u001cW\rJ\u001a\u0011\r\u0005e\u0013qLB?\u0011\u001d\u0019Ii\u0010a\u0001\u0007\u0017\u000b1B\u001a7bi6\u000b\u0007OR;oGBA\u00111AB8\u0003G\u001ai\t\u0005\u0004\u0002\u0010\r=5QP\u0005\u0005\u0007#\u000b\u0019C\u0001\u0007Ji\u0016\u0014\u0018M\u00197f\u001f:\u001cW-\u0001\u0004gS2$XM\u001d\u000b\u0005\u0003\u007f\u001a9\nC\u0004\u0004\u001a\u0002\u0003\raa'\u0002\u0015\u0019LG\u000e^3s\rVt7\r\u0005\u0005\u0002\u0004\r=\u00141\rB\u000b\u0003\u00119Gn\\7\u0015\u0005\r\u0005\u0006#BAA\u0001\r\r\u0006CBA\u0002\u0007K\u000b\u0019'\u0003\u0003\u0004(\u0006\u0015!!B!se\u0006L\u0018a\u0003:fa\u0006\u0014H/\u001b;j_:$B!a \u0004.\"91q\u0016\"A\u0002\rE\u0016!\u00048v[B\u000b'\u000f^5uS>t7\u000f\u0005\u0003\u0002\u0004\rM\u0016\u0002BB[\u0003\u000b\u00111!\u00138u\u00035i\u0017\r\u001d)beRLG/[8ogV!11XBb)\u0019\u0019ila3\u0004ZR!1qXBc!\u0015\t\t\tABa!\u0011\t)ga1\u0005\u000f\t\u00058I1\u0001\u0002l!I1qY\"\u0002\u0002\u0003\u000f1\u0011Z\u0001\u000bKZLG-\u001a8dK\u0012\"\u0004CBA-\u0003?\u001a\t\rC\u0004\u0004N\u000e\u0003\raa4\u0002\u00175\f\u0007\u000fU1si\u001a+hn\u0019\t\t\u0003\u0007\u0019yg!5\u0004XB1\u0011qBBj\u0003GJAa!6\u0002$\tA\u0011\n^3sCR|'\u000f\u0005\u0004\u0002\u0010\rM7\u0011\u0019\u0005\n\u00077\u001c\u0005\u0013!a\u0001\u0005+\tA\u0003\u001d:fg\u0016\u0014h/\u001a)beRLG/[8oS:<\u0017aF7baB\u000b'\u000f^5uS>t7\u000f\n3fM\u0006,H\u000e\u001e\u00133+\u0011\u0019\toa>\u0016\u0005\r\r(\u0006\u0002B\u000b\u0007K\\#aa:\u0011\t\r%81_\u0007\u0003\u0007WTAa!<\u0004p\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0005\u0007c\f)!\u0001\u0006b]:|G/\u0019;j_:LAa!>\u0004l\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0005\u000f\t\u0005HI1\u0001\u0002l\u00051!/\u001a3vG\u0016$B!a \u0004~\"91q`#A\u0002\u0011\u0005\u0011A\u0003:fIV\u001cWMR;oGBQ\u00111\u0001C\u0002\u0003G\n\u0019'a\u0019\n\t\u0011\u0015\u0011Q\u0001\u0002\n\rVt7\r^5p]J\nQaY8v]R$\"\u0001b\u0003\u0011\u000b\u0005\u0005\u0005\u0001\"\u0004\u0011\t\u0005\rAqB\u0005\u0005\t#\t)A\u0001\u0003M_:<\u0017\u0001D2pk:$()\u001f,bYV,G\u0003\u0002C\f\tW!B\u0001\"\u0007\u0005\"A)\u0011\u0011\u0011\u0001\u0005\u001cAA\u00111\u0001C\u000f\u0003G\"i!\u0003\u0003\u0005 \u0005\u0015!A\u0002+va2,'\u0007C\u0005\u0005$\u001d\u0003\n\u0011q\u0001\u0005&\u0005\u0019qN\u001d3\u0011\r\u0005=AqEA2\u0013\u0011!I#a\t\u0003\u0011=\u0013H-\u001a:j]\u001eD\u0011ba,H!\u0003\u0005\ra!-\u0002-\r|WO\u001c;CsZ\u000bG.^3%I\u00164\u0017-\u001e7uIE*\"\u0001\"\r+\t\rE6Q]\u0001\u0017G>,h\u000e\u001e\"z-\u0006dW/\u001a\u0013eK\u001a\fW\u000f\u001c;%eQ!Aq\u0007C\u001dU\u0011!)c!:\t\u000f\r=\u0016\n1\u0001\u00042\u0006Qam\u001c:fC\u000eD'\u000b\u0012#\u0015\t\u0005\u0005Cq\b\u0005\b\t\u0003R\u0005\u0019\u0001C\"\u0003-1wN]3bG\"4UO\\2\u0011\u0011\u0005\r1qNAX\u0003\u0003\"B!!\u0011\u0005H!9A\u0011I&A\u0002\u0011%\u0003CCA\u0002\t\u0007\ty+a0\u0002BQ1\u0011\u0011\tC'\t\u001fBq\u0001\"\u0011M\u0001\u0004!I\u0005C\u0004\u0003r2\u0003\rA!\u0006\u0002\u0013Q\u0014\u0018M\\:g_JlW\u0003\u0002C+\t;\"B\u0001b\u0016\u0005fQ!A\u0011\fC0!\u0015\t\t\t\u0001C.!\u0011\t)\u0007\"\u0018\u0005\u000f\t\u0005XJ1\u0001\u0002l!IA\u0011M'\u0002\u0002\u0003\u000fA1M\u0001\u000bKZLG-\u001a8dK\u0012*\u0004CBA-\u0003?\"Y\u0006C\u0004\u0005h5\u0003\r\u0001\"\u001b\u0002\u001bQ\u0014\u0018M\\:g_Jlg)\u001e8d!!\t\u0019aa\u001c\u00020\u0012-\u0004CBAY\u0003o#Y&\u0006\u0003\u0005p\u0011]D\u0003\u0002C9\t\u007f\"B\u0001b\u001d\u0005zA)\u0011\u0011\u0011\u0001\u0005vA!\u0011Q\rC<\t\u001d\u0011\tO\u0014b\u0001\u0003WB\u0011\u0002b\u001fO\u0003\u0003\u0005\u001d\u0001\" \u0002\u0015\u00154\u0018\u000eZ3oG\u0016$c\u0007\u0005\u0004\u0002Z\u0005}CQ\u000f\u0005\b\tOr\u0005\u0019\u0001CA!)\t\u0019\u0001b\u0001\u00020\u0006}F1\u0011\t\u0007\u0003c\u000b9\f\"\u001e\u0002\u001bQ\u0014\u0018M\\:g_Jlw+\u001b;i+\u0019!I\t\"(\u0005\u0012R1A1\u0012CS\tW#b\u0001\"$\u0005\u0016\u0012}\u0005#BAA\u0001\u0011=\u0005\u0003BA3\t##q\u0001b%P\u0005\u0004\tYGA\u0001W\u0011%!9jTA\u0001\u0002\b!I*\u0001\u0006fm&$WM\\2fI]\u0002b!!\u0017\u0002`\u0011m\u0005\u0003BA3\t;#qA!9P\u0005\u0004\tY\u0007C\u0005\u0005\">\u000b\t\u0011q\u0001\u0005$\u0006QQM^5eK:\u001cW\r\n\u001d\u0011\r\u0005e\u0013q\fCH\u0011\u001d!9k\u0014a\u0001\tS\u000bQa\u001c;iKJ\u0004R!!!\u0001\t7Cq\u0001b\u001aP\u0001\u0004!i\u000b\u0005\u0006\u0002\u0004\u0011\r\u0011q\u0016CX\tc\u0003b!!-\u00028\u0012m\u0005CBAY\u0003o#y)\u0006\u0004\u00056\u0012\u001dGQ\u0018\u000b\u0007\to#y\rb5\u0015\r\u0011eFq\u0018Ce!\u0015\t\t\t\u0001C^!\u0011\t)\u0007\"0\u0005\u000f\u0011M\u0005K1\u0001\u0002l!IA\u0011\u0019)\u0002\u0002\u0003\u000fA1Y\u0001\u000bKZLG-\u001a8dK\u0012J\u0004CBA-\u0003?\")\r\u0005\u0003\u0002f\u0011\u001dGa\u0002Bq!\n\u0007\u00111\u000e\u0005\n\t\u0017\u0004\u0016\u0011!a\u0002\t\u001b\f1\"\u001a<jI\u0016t7-\u001a\u00132aA1\u0011\u0011LA0\twCq\u0001b*Q\u0001\u0004!\t\u000eE\u0003\u0002\u0002\u0002!)\rC\u0004\u0005hA\u0003\r\u0001\"6\u0011\u0019\u0005\rAq[AX\t7\fy\f\"8\n\t\u0011e\u0017Q\u0001\u0002\n\rVt7\r^5p]N\u0002b!!-\u00028\u0012\u0015\u0007CBAY\u0003o#Y,A\u0003qe&tG\u000f\u0006\u0003\u0002B\u0011\r\bb\u0002Cs%\u0002\u00071\u0011W\u0001\u0004]Vl\u0017AB<j]\u0012|w\u000f\u0006\u0003\u0002\u0000\u0011-\bb\u0002Cw'\u0002\u0007\u00111R\u0001\u000fo&tGm\\<EkJ\fG/[8o)\u0019\ty\b\"=\u0005t\"9AQ\u001e+A\u0002\u0005-\u0005bBAD)\u0002\u0007\u00111R\u0001\u000fe\u0016$WoY3Cs^Kg\u000eZ8x)!\ty\b\"?\u0005|\u0012u\bbBB\u0000+\u0002\u0007A\u0011\u0001\u0005\b\t[,\u0006\u0019AAF\u0011\u001d\t9)\u0016a\u0001\u0003\u0017#\"\"a \u0006\u0002\u0015\rQqAC\u0005\u0011\u001d\u0019yP\u0016a\u0001\t\u0003Aq!\"\u0002W\u0001\u0004!\t!A\u0007j]Z\u0014V\rZ;dK\u001a+hn\u0019\u0005\b\t[4\u0006\u0019AAF\u0011\u001d\t9I\u0016a\u0001\u0003\u0017\u000bQbY8v]R\u0014\u0015pV5oI><HC\u0002C\u0006\u000b\u001f)\t\u0002C\u0004\u0005n^\u0003\r!a#\t\u000f\u0005\u001du\u000b1\u0001\u0002\f\u0006)2m\\;oi\nKh+\u00197vK\u0006sGmV5oI><H\u0003CC\f\u000b7)i\"b\b\u0015\t\u0011eQ\u0011\u0004\u0005\n\tGA\u0006\u0013!a\u0002\tKAq\u0001\"<Y\u0001\u0004\tY\tC\u0004\u0002\bb\u0003\r!a#\t\u0013\r=\u0006\f%AA\u0002\rE\u0016aH2pk:$()\u001f,bYV,\u0017I\u001c3XS:$wn\u001e\u0013eK\u001a\fW\u000f\u001c;%g\u0005y2m\\;oi\nKh+\u00197vK\u0006sGmV5oI><H\u0005Z3gCVdG\u000f\n\u001b\u0015\u0011\u0011]RqEC\u0015\u000bWAq\u0001\"<[\u0001\u0004\tY\tC\u0004\u0002\bj\u0003\r!a#\t\u000f\r=&\f1\u0001\u00042\u0006)QO\\5p]R!\u0011qPC\u0019\u0011\u001d)\u0019d\u0017a\u0001\u0003\u007f\nA\u0001\u001e5bi\u0006)1\u000f\\5dKR!Q\u0011HC !\u0019\ty!b\u000f\u00020&!QQHA\u0012\u0005\r\u0019V-\u001d\u0005\b\u0005Gc\u0006\u0019AC!!\u0011\t9$b\u0011\n\u0007\u0015\u0015SO\u0001\u0005J]R,'O^1m)\u0019)I$\"\u0013\u0006N!9Q1J/A\u0002\u0005}\u0016\u0001\u00034s_6$\u0016.\\3\t\u000f\u0015=S\f1\u0001\u0002@\u00061Ao\u001c+j[\u0016\f\u0011c]1wK\u0006\u001bxJ\u00196fGR4\u0015\u000e\\3t)\u0019\t\t%\"\u0016\u0006Z!9Qq\u000b0A\u0002\t=\u0014A\u00029sK\u001aL\u0007\u0010C\u0005\u0006\\y\u0003\n\u00111\u0001\u0003p\u000511/\u001e4gSb\f1d]1wK\u0006\u001bxJ\u00196fGR4\u0015\u000e\\3tI\u0011,g-Y;mi\u0012\u0012TCAC1U\u0011\u0011yg!:\u0002\u001fM\fg/Z!t)\u0016DHOR5mKN$b!!\u0011\u0006h\u0015%\u0004bBC,A\u0002\u0007!q\u000e\u0005\n\u000b7\u0002\u0007\u0013!a\u0001\u0005_\n\u0011d]1wK\u0006\u001bH+\u001a=u\r&dWm\u001d\u0013eK\u001a\fW\u000f\u001c;%e\u0005A!/Z4jgR,'/A\u0004E'R\u0014X-Y7\u0011\u0007\u0005\u0005EmE\u0003e\u0003\u0003))\b\u0005\u0003\u0004(\u0015]\u0014\u0002BA\u0011\u0007S!\"!\"\u001d\u0002#M\u0003\u0016IU&`\u00072\u000b5kU0S\u000b\u001e+\u0005,\u0006\u0002\u0006\u0000A!Q\u0011QCE\u001b\t)\u0019I\u0003\u0003\u0006\u0006\u0016\u001d\u0015\u0001C7bi\u000eD\u0017N\\4\u000b\t\t\u0005\u0014QA\u0005\u0005\u000b\u0017+\u0019IA\u0003SK\u001e,\u00070\u0001\nT!\u0006\u00136jX\"M\u0003N\u001bvLU#H\u000bb\u0003\u0013aH*Q\u0003J[ul\u0015+S\u000b\u0006k\u0015JT$`)\u0016\u001bFk\u0011'B'N{&+R$F1\u0006\u00013\u000bU!S\u0017~\u001bFKU#B\u001b&sui\u0018+F'R\u001bE*Q*T?J+u)\u0012-!\u0003i\u0019\u0006+\u0011*L?\u0016C\u0016)\u0014)M\u000bN{6\tT!T'~\u0013ViR#Y\u0003m\u0019\u0006+\u0011*L?\u0016C\u0016)\u0014)M\u000bN{6\tT!T'~\u0013ViR#YA\u0005\t2kQ!M\u0003~\u001bE*Q*T?J+u)\u0012-\u0002%M\u001b\u0015\tT!`\u00072\u000b5kU0S\u000b\u001e+\u0005\fI\u0001\u0017i>\u0004\u0016-\u001b:E'R\u0014X-Y7Gk:\u001cG/[8ogV1QqTCV\u000bc#B!\")\u0006DRAQ1UCZ\u000bs+y\f\u0005\u0005\u0002\u0002\u0016\u0015V\u0011VCX\u0013\r)9k\u001d\u0002\u0015!\u0006L'\u000fR*ue\u0016\fWNR;oGRLwN\\:\u0011\t\u0005\u0015T1\u0016\u0003\b\u000b[s'\u0019AA6\u0005\u0005Y\u0005\u0003BA3\u000bc#q\u0001b%o\u0005\u0004\tY\u0007C\u0004\u00066:\u0004\u001d!b.\u0002\u0005-$\bCBA-\u0003?*I\u000bC\u0004\u0006<:\u0004\u001d!\"0\u0002\u0005Y$\bCBA-\u0003?*y\u000bC\u0005\u0005$9\u0004\n\u0011q\u0001\u0006BB1\u0011q\u0002C\u0014\u000bSCq!\"2o\u0001\u0004)9-\u0001\u0004tiJ,\u0017-\u001c\t\u0006\u0003\u0003\u0003Q\u0011\u001a\t\t\u0003\u0007!i\"\"+\u00060\u0006\u0001Co\u001c)bSJ$5\u000b\u001e:fC64UO\\2uS>t7\u000f\n3fM\u0006,H\u000e\u001e\u00135+\u0019)y-b8\u0006dR!Q\u0011[ClU\u0011)\u0019n!:\u0010\u0005\u0015U'\u0005\u0001\u0005\b\u000b\u000b|\u0007\u0019ACm!\u0015\t\t\tACn!!\t\u0019\u0001\"\b\u0006^\u0016\u0005\b\u0003BA3\u000b?$q!\",p\u0005\u0004\tY\u0007\u0005\u0003\u0002f\u0015\rHa\u0002CJ_\n\u0007\u00111N\u0001\u0010O\u0016$8I]3bi&|gnU5uKR\u0011!1L\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u000b[\u0004B!b<\u0006v6\u0011Q\u0011\u001f\u0006\u0005\u000bg\u001ci#\u0001\u0003mC:<\u0017\u0002BC|\u000bc\u0014aa\u00142kK\u000e$\b"
)
public abstract class DStream implements Serializable, Logging {
   private transient StreamingContext ssc;
   private final ClassTag evidence$1;
   private transient HashMap generatedRDDs;
   private Time zeroTime;
   private Duration rememberDuration;
   private StorageLevel storageLevel;
   private final boolean mustCheckpoint;
   private Duration checkpointDuration;
   private final DStreamCheckpointData checkpointData;
   private transient boolean restoredFromCheckpointData;
   private DStreamGraph graph;
   private final CallSite creationSite;
   private final Option baseScope;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static Null toPairDStreamFunctions$default$4(final DStream stream) {
      return DStream$.MODULE$.toPairDStreamFunctions$default$4(stream);
   }

   public static PairDStreamFunctions toPairDStreamFunctions(final DStream stream, final ClassTag kt, final ClassTag vt, final Ordering ord) {
      return DStream$.MODULE$.toPairDStreamFunctions(stream, kt, vt, ord);
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

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public StreamingContext ssc() {
      return this.ssc;
   }

   public void ssc_$eq(final StreamingContext x$1) {
      this.ssc = x$1;
   }

   public abstract Duration slideDuration();

   public abstract List dependencies();

   public abstract Option compute(final Time validTime);

   public HashMap generatedRDDs() {
      return this.generatedRDDs;
   }

   public void generatedRDDs_$eq(final HashMap x$1) {
      this.generatedRDDs = x$1;
   }

   public Time zeroTime() {
      return this.zeroTime;
   }

   public void zeroTime_$eq(final Time x$1) {
      this.zeroTime = x$1;
   }

   public Duration rememberDuration() {
      return this.rememberDuration;
   }

   public void rememberDuration_$eq(final Duration x$1) {
      this.rememberDuration = x$1;
   }

   public StorageLevel storageLevel() {
      return this.storageLevel;
   }

   public void storageLevel_$eq(final StorageLevel x$1) {
      this.storageLevel = x$1;
   }

   public boolean mustCheckpoint() {
      return this.mustCheckpoint;
   }

   public Duration checkpointDuration() {
      return this.checkpointDuration;
   }

   public void checkpointDuration_$eq(final Duration x$1) {
      this.checkpointDuration = x$1;
   }

   public DStreamCheckpointData checkpointData() {
      return this.checkpointData;
   }

   private boolean restoredFromCheckpointData() {
      return this.restoredFromCheckpointData;
   }

   private void restoredFromCheckpointData_$eq(final boolean x$1) {
      this.restoredFromCheckpointData = x$1;
   }

   public DStreamGraph graph() {
      return this.graph;
   }

   public void graph_$eq(final DStreamGraph x$1) {
      this.graph = x$1;
   }

   public boolean isInitialized() {
      return this.zeroTime() != null;
   }

   public Duration parentRememberDuration() {
      return this.rememberDuration();
   }

   public StreamingContext context() {
      return this.ssc();
   }

   public CallSite creationSite() {
      return this.creationSite;
   }

   public Option baseScope() {
      return this.baseScope;
   }

   private Option makeScope(final Time time) {
      return this.baseScope().map((bsJson) -> {
         String formattedBatchTime = .MODULE$.formatBatchTime(time.milliseconds(), this.ssc().graph().batchDuration().milliseconds(), false, .MODULE$.formatBatchTime$default$4());
         RDDOperationScope bs = org.apache.spark.rdd.RDDOperationScope..MODULE$.fromJson(bsJson);
         String baseName = bs.name();
         String scopeName = baseName.length() > 10 ? baseName + "\n@ " + formattedBatchTime : baseName + " @ " + formattedBatchTime;
         String var10000 = bs.id();
         String scopeId = var10000 + "_" + time.milliseconds();
         Option x$3 = org.apache.spark.rdd.RDDOperationScope..MODULE$.$lessinit$greater$default$2();
         return new RDDOperationScope(scopeName, x$3, scopeId);
      });
   }

   public DStream persist(final StorageLevel level) {
      if (this.isInitialized()) {
         throw new UnsupportedOperationException("Cannot change storage level of a DStream after streaming context has started");
      } else {
         this.storageLevel_$eq(level);
         return this;
      }
   }

   public DStream persist() {
      return this.persist(org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_ONLY_SER());
   }

   public DStream cache() {
      return this.persist();
   }

   public DStream checkpoint(final Duration interval) {
      if (this.isInitialized()) {
         throw new UnsupportedOperationException("Cannot change checkpoint interval of a DStream after streaming context has started");
      } else {
         this.persist();
         this.checkpointDuration_$eq(interval);
         return this;
      }
   }

   public void initialize(final Time time) {
      label39: {
         if (this.zeroTime() != null) {
            Time var10000 = this.zeroTime();
            if (var10000 == null) {
               if (time != null) {
                  break label39;
               }
            } else if (!var10000.equals(time)) {
               break label39;
            }
         }

         this.zeroTime_$eq(time);
         if (this.mustCheckpoint() && this.checkpointDuration() == null) {
            this.checkpointDuration_$eq(this.slideDuration().$times((int)scala.math.package..MODULE$.ceil(Seconds$.MODULE$.apply(10L).$div(this.slideDuration()))));
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Checkpoint interval automatically set to "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CHECKPOINT_INTERVAL..MODULE$, this.checkpointDuration())}))))));
         }

         Duration minRememberDuration = this.slideDuration();
         if (this.checkpointDuration() != null && minRememberDuration.$less$eq(this.checkpointDuration())) {
            minRememberDuration = this.checkpointDuration().$times(2);
         }

         if (this.rememberDuration() == null || this.rememberDuration().$less(minRememberDuration)) {
            this.rememberDuration_$eq(minRememberDuration);
         }

         this.dependencies().foreach((x$1) -> {
            $anonfun$initialize$2(this, x$1);
            return BoxedUnit.UNIT;
         });
         return;
      }

      Time var10002 = this.zeroTime();
      throw new SparkException("ZeroTime is already initialized to " + var10002 + ", cannot initialize it again to " + time);
   }

   private void validateAtInit() {
      StreamingContextState var2 = this.ssc().getState();
      if (StreamingContextState.INITIALIZED.equals(var2)) {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else if (StreamingContextState.ACTIVE.equals(var2)) {
         throw new IllegalStateException("Adding new inputs, transformations, and output operations after starting a context is not supported");
      } else if (StreamingContextState.STOPPED.equals(var2)) {
         throw new IllegalStateException("Adding new inputs, transformations, and output operations after stopping a context is not supported");
      } else {
         throw new MatchError(var2);
      }
   }

   public void validateAtStart() {
      boolean var2;
      Predef var10000;
      label77: {
         scala.Predef..MODULE$.require(this.rememberDuration() != null, () -> "Remember duration is set to null");
         scala.Predef..MODULE$.require(!this.mustCheckpoint() || this.checkpointDuration() != null, () -> "The checkpoint interval for " + this.getClass().getSimpleName() + " has not been set. Please use DStream.checkpoint() to set the interval.");
         scala.Predef..MODULE$.require(this.checkpointDuration() == null || this.context().sparkContext().checkpointDir().isDefined(), () -> "The checkpoint directory has not been set. Please set it by StreamingContext.checkpoint().");
         scala.Predef..MODULE$.require(this.checkpointDuration() == null || this.checkpointDuration().$greater$eq(this.slideDuration()), () -> {
            String var10000 = this.getClass().getSimpleName();
            return "The checkpoint interval for " + var10000 + " has been set to " + this.checkpointDuration() + " which is lower than its slide time (" + this.slideDuration() + "). Please set it to at least " + this.slideDuration() + ".";
         });
         scala.Predef..MODULE$.require(this.checkpointDuration() == null || this.checkpointDuration().isMultipleOf(this.slideDuration()), () -> {
            String var10000 = this.getClass().getSimpleName();
            return "The checkpoint interval for " + var10000 + " has been set to  " + this.checkpointDuration() + " which not a multiple of its slide time (" + this.slideDuration() + "). Please set it to a multiple of " + this.slideDuration() + ".";
         });
         var10000 = scala.Predef..MODULE$;
         if (this.checkpointDuration() != null) {
            label76: {
               StorageLevel var10001 = this.storageLevel();
               StorageLevel var1 = org.apache.spark.storage.StorageLevel..MODULE$.NONE();
               if (var10001 == null) {
                  if (var1 != null) {
                     break label76;
                  }
               } else if (!var10001.equals(var1)) {
                  break label76;
               }

               var2 = false;
               break label77;
            }
         }

         var2 = true;
      }

      var10000.require(var2, () -> this.getClass().getSimpleName() + " has been marked for checkpointing but the storage level has not been set to enable persisting. Please use DStream.persist() to set the storage level to use memory for better checkpointing performance.");
      scala.Predef..MODULE$.require(this.checkpointDuration() == null || this.rememberDuration().$greater(this.checkpointDuration()), () -> {
         String var10000 = this.getClass().getSimpleName();
         return "The remember duration for " + var10000 + " has been set to  " + this.rememberDuration() + " which is not more than the checkpoint interval (" + this.checkpointDuration() + "). Please set it to a value higher than " + this.checkpointDuration() + ".";
      });
      this.dependencies().foreach((x$2) -> {
         $anonfun$validateAtStart$8(x$2);
         return BoxedUnit.UNIT;
      });
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Slide time = ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SLIDE_DURATION..MODULE$, this.slideDuration())})))));
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Storage level = ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STORAGE_LEVEL..MODULE$, this.storageLevel().description())})))));
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Checkpoint interval = ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CHECKPOINT_INTERVAL..MODULE$, this.checkpointDuration())})))));
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Remember interval = ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.INTERVAL..MODULE$, this.rememberDuration())})))));
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Initialized and validated ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DSTREAM..MODULE$, this)})))));
   }

   public void setContext(final StreamingContext s) {
      if (this.ssc() != null) {
         StreamingContext var10000 = this.ssc();
         if (var10000 == null) {
            if (s != null) {
               throw new SparkException("Context must not be set again for " + this);
            }
         } else if (!var10000.equals(s)) {
            throw new SparkException("Context must not be set again for " + this);
         }
      }

      this.ssc_$eq(s);
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Set context for ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STREAMING_CONTEXT..MODULE$, this)})))));
      this.dependencies().foreach((x$3) -> {
         $anonfun$setContext$2(this, x$3);
         return BoxedUnit.UNIT;
      });
   }

   public void setGraph(final DStreamGraph g) {
      if (this.graph() != null) {
         DStreamGraph var10000 = this.graph();
         if (var10000 == null) {
            if (g != null) {
               throw new SparkException("Graph must not be set again for " + this);
            }
         } else if (!var10000.equals(g)) {
            throw new SparkException("Graph must not be set again for " + this);
         }
      }

      this.graph_$eq(g);
      this.dependencies().foreach((x$4) -> {
         $anonfun$setGraph$1(this, x$4);
         return BoxedUnit.UNIT;
      });
   }

   public void remember(final Duration duration) {
      if (duration != null && (this.rememberDuration() == null || duration.$greater(this.rememberDuration()))) {
         this.rememberDuration_$eq(duration);
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Duration for remembering RDDs set to "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " for "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DURATION..MODULE$, this.rememberDuration())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DSTREAM..MODULE$, this.toString())}))))));
      }

      this.dependencies().foreach((x$5) -> {
         $anonfun$remember$2(this, x$5);
         return BoxedUnit.UNIT;
      });
   }

   public boolean isTimeValid(final Time time) {
      if (!this.isInitialized()) {
         throw new SparkException(this.toString() + " has not been initialized");
      } else if (!time.$less$eq(this.zeroTime()) && time.$minus(this.zeroTime()).isMultipleOf(this.slideDuration())) {
         this.logDebug((Function0)(() -> "Time " + time + " is valid"));
         return true;
      } else {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Time ", " is invalid as zeroTime is "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIME..MODULE$, time)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ", slideDuration is "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ZERO_TIME..MODULE$, this.zeroTime())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " and difference is "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SLIDE_DURATION..MODULE$, this.slideDuration())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DURATION..MODULE$, time.$minus(this.zeroTime()))}))))));
         return false;
      }
   }

   public final Option getOrCompute(final Time time) {
      return this.generatedRDDs().get(time).orElse(() -> {
         if (this.isTimeValid(time)) {
            Option rddOption = (Option)this.createRDDWithLocalProperties(time, false, () -> (Option)org.apache.spark.internal.io.SparkHadoopWriterUtils..MODULE$.disableOutputSpecValidation().withValue(BoxesRunTime.boxToBoolean(true), () -> this.compute(time)));
            rddOption.foreach((x0$1) -> {
               label20: {
                  StorageLevel var10000 = this.storageLevel();
                  StorageLevel var5 = org.apache.spark.storage.StorageLevel..MODULE$.NONE();
                  if (var10000 == null) {
                     if (var5 == null) {
                        break label20;
                     }
                  } else if (var10000.equals(var5)) {
                     break label20;
                  }

                  x0$1.persist(this.storageLevel());
                  this.logDebug((Function0)(() -> "Persisting RDD " + x0$1.id() + " for time " + time + " to " + this.storageLevel()));
               }

               if (this.checkpointDuration() != null && time.$minus(this.zeroTime()).isMultipleOf(this.checkpointDuration())) {
                  x0$1.checkpoint();
                  this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Marking RDD ", " for time "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RDD_ID..MODULE$, BoxesRunTime.boxToInteger(x0$1.id()))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " for checkpointing"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIME..MODULE$, time)}))))));
               }

               return this.generatedRDDs().put(time, x0$1);
            });
            return rddOption;
         } else {
            return scala.None..MODULE$;
         }
      });
   }

   public Object createRDDWithLocalProperties(final Time time, final boolean displayInnerRDDOps, final Function0 body) {
      String scopeKey = org.apache.spark.SparkContext..MODULE$.RDD_SCOPE_KEY();
      String scopeNoOverrideKey = org.apache.spark.SparkContext..MODULE$.RDD_SCOPE_NO_OVERRIDE_KEY();
      CallSite prevCallSite = new CallSite(this.ssc().sparkContext().getLocalProperty(org.apache.spark.util.CallSite..MODULE$.SHORT_FORM()), this.ssc().sparkContext().getLocalProperty(org.apache.spark.util.CallSite..MODULE$.LONG_FORM()));
      String prevScope = this.ssc().sparkContext().getLocalProperty(scopeKey);
      String prevScopeNoOverride = this.ssc().sparkContext().getLocalProperty(scopeNoOverrideKey);

      Object var10000;
      try {
         if (displayInnerRDDOps) {
            this.ssc().sparkContext().setLocalProperty(org.apache.spark.util.CallSite..MODULE$.SHORT_FORM(), (String)null);
            this.ssc().sparkContext().setLocalProperty(org.apache.spark.util.CallSite..MODULE$.LONG_FORM(), (String)null);
         } else {
            this.ssc().sparkContext().setCallSite(this.creationSite());
         }

         this.makeScope(time).foreach((s) -> {
            $anonfun$createRDDWithLocalProperties$1(this, scopeKey, displayInnerRDDOps, scopeNoOverrideKey, s);
            return BoxedUnit.UNIT;
         });
         var10000 = body.apply();
      } finally {
         this.ssc().sparkContext().setCallSite(prevCallSite);
         this.ssc().sparkContext().setLocalProperty(scopeKey, prevScope);
         this.ssc().sparkContext().setLocalProperty(scopeNoOverrideKey, prevScopeNoOverride);
      }

      return var10000;
   }

   public Option generateJob(final Time time) {
      Option var3 = this.getOrCompute(time);
      if (var3 instanceof Some var4) {
         RDD rdd = (RDD)var4.value();
         Function0 jobFunc = () -> {
            Function1 emptyFunc = (iterator) -> {
               $anonfun$generateJob$2(iterator);
               return BoxedUnit.UNIT;
            };
            return (BoxedUnit[])this.context().sparkContext().runJob(rdd, emptyFunc, scala.reflect.ClassTag..MODULE$.Unit());
         };
         return new Some(new Job(time, jobFunc));
      } else if (scala.None..MODULE$.equals(var3)) {
         return scala.None..MODULE$;
      } else {
         throw new MatchError(var3);
      }
   }

   public void clearMetadata(final Time time) {
      boolean unpersistData = BoxesRunTime.unboxToBoolean(this.ssc().conf().get(StreamingConf$.MODULE$.STREAMING_UNPERSIST()));
      HashMap oldRDDs = (HashMap)this.generatedRDDs().filter((x$6) -> BoxesRunTime.boxToBoolean($anonfun$clearMetadata$1(this, time, x$6)));
      this.logDebug((Function0)(() -> {
         IterableOnceOps var10000 = (IterableOnceOps)oldRDDs.map((x) -> {
            Object var10000 = x._1();
            return var10000 + " -> " + ((RDD)x._2()).id();
         });
         return "Clearing references to old RDDs: [" + var10000.mkString(", ") + "]";
      }));
      this.generatedRDDs().$minus$minus$eq(oldRDDs.keys());
      if (unpersistData) {
         this.logDebug((Function0)(() -> {
            IterableOnceOps var10000 = (IterableOnceOps)oldRDDs.values().map((x$7) -> BoxesRunTime.boxToInteger($anonfun$clearMetadata$5(x$7)));
            return "Unpersisting old RDDs: " + var10000.mkString(", ");
         }));
         oldRDDs.values().foreach((rdd) -> {
            $anonfun$clearMetadata$6(this, time, rdd);
            return BoxedUnit.UNIT;
         });
      }

      this.logDebug((Function0)(() -> {
         int var10000 = oldRDDs.size();
         return "Cleared " + var10000 + " RDDs that were older than " + time.$minus(this.rememberDuration()) + ": " + oldRDDs.keys().mkString(", ");
      }));
      this.dependencies().foreach((x$8) -> {
         $anonfun$clearMetadata$9(time, x$8);
         return BoxedUnit.UNIT;
      });
   }

   public void updateCheckpointData(final Time currentTime) {
      this.logDebug((Function0)(() -> "Updating checkpoint data for time " + currentTime));
      this.checkpointData().update(currentTime);
      this.dependencies().foreach((x$9) -> {
         $anonfun$updateCheckpointData$2(currentTime, x$9);
         return BoxedUnit.UNIT;
      });
      this.logDebug((Function0)(() -> "Updated checkpoint data for time " + currentTime + ": " + this.checkpointData()));
   }

   public void clearCheckpointData(final Time time) {
      this.logDebug((Function0)(() -> "Clearing checkpoint data"));
      this.checkpointData().cleanup(time);
      this.dependencies().foreach((x$10) -> {
         $anonfun$clearCheckpointData$2(time, x$10);
         return BoxedUnit.UNIT;
      });
      this.logDebug((Function0)(() -> "Cleared checkpoint data"));
   }

   public void restoreCheckpointData() {
      if (!this.restoredFromCheckpointData()) {
         this.logInfo((Function0)(() -> "Restoring checkpoint data"));
         this.checkpointData().restore();
         this.dependencies().foreach((x$11) -> {
            $anonfun$restoreCheckpointData$2(x$11);
            return BoxedUnit.UNIT;
         });
         this.restoredFromCheckpointData_$eq(true);
         this.logInfo((Function0)(() -> "Restored checkpoint data"));
      }
   }

   private void writeObject(final ObjectOutputStream oos) throws IOException {
      org.apache.spark.util.Utils..MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         this.logDebug((Function0)(() -> this.getClass().getSimpleName() + ".writeObject used"));
         if (this.graph() != null) {
            synchronized(this.graph()){}

            try {
               if (!this.graph().checkpointInProgress()) {
                  String msg = "Object of " + this.getClass().getName() + " is being serialized  possibly as a part of closure of an RDD operation. This is because  the DStream object is being referred to from within the closure.  Please rewrite the RDD operation inside this DStream to avoid this.  This has been enforced to avoid bloating of Spark tasks  with unnecessary objects.";
                  throw new NotSerializableException(msg);
               }

               oos.defaultWriteObject();
            } catch (Throwable var5) {
               throw var5;
            }

         } else {
            throw new NotSerializableException("Graph is unexpectedly null when DStream is being serialized.");
         }
      });
   }

   private void readObject(final ObjectInputStream ois) throws IOException {
      org.apache.spark.util.Utils..MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         this.logDebug((Function0)(() -> this.getClass().getSimpleName() + ".readObject used"));
         ois.defaultReadObject();
         this.generatedRDDs_$eq(new HashMap());
      });
   }

   public DStream map(final Function1 mapFunc, final ClassTag evidence$2) {
      return (DStream)this.ssc().withScope(() -> {
         SparkContext qual$1 = this.context().sparkContext();
         boolean x$2 = qual$1.clean$default$2();
         return new MappedDStream(this, (Function1)qual$1.clean(mapFunc, x$2), this.evidence$1, evidence$2);
      });
   }

   public DStream flatMap(final Function1 flatMapFunc, final ClassTag evidence$3) {
      return (DStream)this.ssc().withScope(() -> {
         SparkContext qual$1 = this.context().sparkContext();
         boolean x$2 = qual$1.clean$default$2();
         return new FlatMappedDStream(this, (Function1)qual$1.clean(flatMapFunc, x$2), this.evidence$1, evidence$3);
      });
   }

   public DStream filter(final Function1 filterFunc) {
      return (DStream)this.ssc().withScope(() -> {
         SparkContext qual$1 = this.context().sparkContext();
         boolean x$2 = qual$1.clean$default$2();
         return new FilteredDStream(this, (Function1)qual$1.clean(filterFunc, x$2), this.evidence$1);
      });
   }

   public DStream glom() {
      return (DStream)this.ssc().withScope(() -> new GlommedDStream(this, this.evidence$1));
   }

   public DStream repartition(final int numPartitions) {
      return (DStream)this.ssc().withScope(() -> this.transform((Function1)((x$12) -> {
            Ordering x$2 = x$12.repartition$default$2(numPartitions);
            return x$12.repartition(numPartitions, x$2);
         }), this.evidence$1));
   }

   public DStream mapPartitions(final Function1 mapPartFunc, final boolean preservePartitioning, final ClassTag evidence$4) {
      return (DStream)this.ssc().withScope(() -> {
         SparkContext qual$1 = this.context().sparkContext();
         boolean x$2 = qual$1.clean$default$2();
         return new MapPartitionedDStream(this, (Function1)qual$1.clean(mapPartFunc, x$2), preservePartitioning, this.evidence$1, evidence$4);
      });
   }

   public boolean mapPartitions$default$2() {
      return false;
   }

   public DStream reduce(final Function2 reduceFunc) {
      return (DStream)this.ssc().withScope(() -> DStream$.MODULE$.toPairDStreamFunctions(this.map((x$13) -> new Tuple2((Object)null, x$13), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), scala.reflect.ClassTag..MODULE$.Null(), this.evidence$1, scala.math.Ordering..MODULE$.ordered(scala.Predef..MODULE$.$conforms())).reduceByKey(reduceFunc, 1).map((x$14) -> x$14._2(), this.evidence$1));
   }

   public DStream count() {
      return (DStream)this.ssc().withScope(() -> DStream$.MODULE$.toPairDStreamFunctions(this.map((x$15) -> new Tuple2((Object)null, BoxesRunTime.boxToLong(1L)), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)).transform((Function1)((x$16) -> x$16.union(this.context().sparkContext().makeRDD(new scala.collection.immutable..colon.colon(new Tuple2((Object)null, BoxesRunTime.boxToLong(0L)), scala.collection.immutable.Nil..MODULE$), 1, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)))), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), scala.reflect.ClassTag..MODULE$.Null(), scala.reflect.ClassTag..MODULE$.Long(), scala.math.Ordering..MODULE$.ordered(scala.Predef..MODULE$.$conforms())).reduceByKey((JFunction2.mcJJJ.sp)(x$17, x$18) -> x$17 + x$18).map((x$19) -> BoxesRunTime.boxToLong($anonfun$count$5(x$19)), scala.reflect.ClassTag..MODULE$.Long()));
   }

   public DStream countByValue(final int numPartitions, final Ordering ord) {
      return (DStream)this.ssc().withScope(() -> DStream$.MODULE$.toPairDStreamFunctions(this.map((x$20) -> new Tuple2(x$20, BoxesRunTime.boxToLong(1L)), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), this.evidence$1, scala.reflect.ClassTag..MODULE$.Long(), ord).reduceByKey((JFunction2.mcJJJ.sp)(x, y) -> x + y, numPartitions));
   }

   public int countByValue$default$1() {
      return this.ssc().sc().defaultParallelism();
   }

   public Ordering countByValue$default$2(final int numPartitions) {
      return null;
   }

   public void foreachRDD(final Function1 foreachFunc) {
      this.ssc().withScope((JFunction0.mcV.sp)() -> {
         Function1 cleanedF = (Function1)this.context().sparkContext().clean(foreachFunc, false);
         this.foreachRDD((r, x$21) -> {
            $anonfun$foreachRDD$2(cleanedF, r, x$21);
            return BoxedUnit.UNIT;
         }, true);
      });
   }

   public void foreachRDD(final Function2 foreachFunc) {
      this.ssc().withScope((JFunction0.mcV.sp)() -> this.foreachRDD(foreachFunc, true));
   }

   private void foreachRDD(final Function2 foreachFunc, final boolean displayInnerRDDOps) {
      (new ForEachDStream(this, (Function2)this.context().sparkContext().clean(foreachFunc, false), displayInnerRDDOps, this.evidence$1)).register();
   }

   public DStream transform(final Function1 transformFunc, final ClassTag evidence$5) {
      return (DStream)this.ssc().withScope(() -> {
         Function1 cleanedF = (Function1)this.context().sparkContext().clean(transformFunc, false);
         return this.transform((Function2)((r, x$22) -> (RDD)cleanedF.apply(r)), evidence$5);
      });
   }

   public DStream transform(final Function2 transformFunc, final ClassTag evidence$6) {
      return (DStream)this.ssc().withScope(() -> {
         Function2 cleanedF = (Function2)this.context().sparkContext().clean(transformFunc, false);
         Function2 realTransformFunc = (rdds, time) -> {
            scala.Predef..MODULE$.assert(rdds.length() == 1);
            return (RDD)cleanedF.apply((RDD)rdds.head(), time);
         };
         return new TransformedDStream(new scala.collection.immutable..colon.colon(this, scala.collection.immutable.Nil..MODULE$), realTransformFunc, evidence$6);
      });
   }

   public DStream transformWith(final DStream other, final Function2 transformFunc, final ClassTag evidence$7, final ClassTag evidence$8) {
      return (DStream)this.ssc().withScope(() -> {
         Function2 cleanedF = (Function2)this.ssc().sparkContext().clean(transformFunc, false);
         return this.transformWith(other, (Function3)((rdd1, rdd2, time) -> (RDD)cleanedF.apply(rdd1, rdd2)), evidence$7, evidence$8);
      });
   }

   public DStream transformWith(final DStream other, final Function3 transformFunc, final ClassTag evidence$9, final ClassTag evidence$10) {
      return (DStream)this.ssc().withScope(() -> {
         Function3 cleanedF = (Function3)this.ssc().sparkContext().clean(transformFunc, false);
         Function2 realTransformFunc = (rdds, time) -> {
            scala.Predef..MODULE$.assert(rdds.length() == 2);
            RDD rdd1 = (RDD)rdds.apply(0);
            RDD rdd2 = (RDD)rdds.apply(1);
            return (RDD)cleanedF.apply(rdd1, rdd2, time);
         };
         return new TransformedDStream(new scala.collection.immutable..colon.colon(this, new scala.collection.immutable..colon.colon(other, scala.collection.immutable.Nil..MODULE$)), realTransformFunc, evidence$10);
      });
   }

   public void print() {
      this.ssc().withScope((JFunction0.mcV.sp)() -> this.print(10));
   }

   public void print(final int num) {
      this.ssc().withScope((JFunction0.mcV.sp)() -> {
         SparkContext qual$1 = this.context().sparkContext();
         Function2 x$1 = foreachFunc$3(num);
         boolean x$2 = qual$1.clean$default$2();
         this.foreachRDD((Function2)qual$1.clean(x$1, x$2), false);
      });
   }

   public DStream window(final Duration windowDuration) {
      return this.window(windowDuration, this.slideDuration());
   }

   public DStream window(final Duration windowDuration, final Duration slideDuration) {
      return (DStream)this.ssc().withScope(() -> new WindowedDStream(this, windowDuration, slideDuration, this.evidence$1));
   }

   public DStream reduceByWindow(final Function2 reduceFunc, final Duration windowDuration, final Duration slideDuration) {
      return (DStream)this.ssc().withScope(() -> this.reduce(reduceFunc).window(windowDuration, slideDuration).reduce(reduceFunc));
   }

   public DStream reduceByWindow(final Function2 reduceFunc, final Function2 invReduceFunc, final Duration windowDuration, final Duration slideDuration) {
      return (DStream)this.ssc().withScope(() -> {
         PairDStreamFunctions qual$1 = DStream$.MODULE$.toPairDStreamFunctions(this.map((x$23) -> new Tuple2(BoxesRunTime.boxToInteger(1), x$23), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), scala.reflect.ClassTag..MODULE$.Int(), this.evidence$1, scala.math.Ordering.Int..MODULE$);
         int x$5 = 1;
         Function1 x$6 = qual$1.reduceByKeyAndWindow$default$6();
         return qual$1.reduceByKeyAndWindow(reduceFunc, invReduceFunc, windowDuration, slideDuration, 1, x$6).map((x$24) -> x$24._2(), this.evidence$1);
      });
   }

   public DStream countByWindow(final Duration windowDuration, final Duration slideDuration) {
      return (DStream)this.ssc().withScope(() -> this.map((x$25) -> BoxesRunTime.boxToLong($anonfun$countByWindow$2(x$25)), scala.reflect.ClassTag..MODULE$.Long()).reduceByWindow((JFunction2.mcJJJ.sp)(x$26, x$27) -> x$26 + x$27, (JFunction2.mcJJJ.sp)(x$28, x$29) -> x$28 - x$29, windowDuration, slideDuration));
   }

   public DStream countByValueAndWindow(final Duration windowDuration, final Duration slideDuration, final int numPartitions, final Ordering ord) {
      return (DStream)this.ssc().withScope(() -> DStream$.MODULE$.toPairDStreamFunctions(this.map((x$30) -> new Tuple2(x$30, BoxesRunTime.boxToLong(1L)), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), this.evidence$1, scala.reflect.ClassTag..MODULE$.Long(), ord).reduceByKeyAndWindow((JFunction2.mcJJJ.sp)(x, y) -> x + y, (JFunction2.mcJJJ.sp)(x, y) -> x - y, windowDuration, slideDuration, numPartitions, (x) -> BoxesRunTime.boxToBoolean($anonfun$countByValueAndWindow$5(x))));
   }

   public int countByValueAndWindow$default$3() {
      return this.ssc().sc().defaultParallelism();
   }

   public Ordering countByValueAndWindow$default$4(final Duration windowDuration, final Duration slideDuration, final int numPartitions) {
      return null;
   }

   public DStream union(final DStream that) {
      return (DStream)this.ssc().withScope(() -> new UnionDStream((DStream[])(new DStream[]{this, that}), this.evidence$1));
   }

   public Seq slice(final Interval interval) {
      return (Seq)this.ssc().withScope(() -> this.slice(interval.beginTime(), interval.endTime()));
   }

   public Seq slice(final Time fromTime, final Time toTime) {
      return (Seq)this.ssc().withScope(() -> {
         if (!this.isInitialized()) {
            throw new SparkException(this.toString() + " has not been initialized");
         } else {
            Time var10000;
            if (toTime.$minus(this.zeroTime()).isMultipleOf(this.slideDuration())) {
               var10000 = toTime;
            } else {
               this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"toTime (", ") is not a multiple of slideDuration "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TO_TIME..MODULE$, toTime)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SLIDE_DURATION..MODULE$, this.slideDuration())}))))));
               var10000 = toTime.floor(this.slideDuration(), this.zeroTime());
            }

            Time alignedToTime = var10000;
            if (fromTime.$minus(this.zeroTime()).isMultipleOf(this.slideDuration())) {
               var10000 = fromTime;
            } else {
               this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"fromTime (", ") is not a multiple of slideDuration "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FROM_TIME..MODULE$, fromTime)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SLIDE_DURATION..MODULE$, this.slideDuration())}))))));
               var10000 = fromTime.floor(this.slideDuration(), this.zeroTime());
            }

            Time alignedFromTime = var10000;
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Slicing from ", " to "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FROM_TIME..MODULE$, fromTime)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TO_TIME..MODULE$, toTime)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" (aligned to ", " and "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ALIGNED_FROM_TIME..MODULE$, alignedFromTime)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ALIGNED_TO_TIME..MODULE$, alignedToTime)}))))));
            return (Seq)alignedFromTime.to(alignedToTime, this.slideDuration()).flatMap((time) -> (IterableOnce)(time.$greater$eq(this.zeroTime()) ? this.getOrCompute(time) : scala.None..MODULE$));
         }
      });
   }

   public void saveAsObjectFiles(final String prefix, final String suffix) {
      this.ssc().withScope((JFunction0.mcV.sp)() -> {
         Function2 saveFunc = (rdd, time) -> {
            $anonfun$saveAsObjectFiles$2(prefix, suffix, rdd, time);
            return BoxedUnit.UNIT;
         };
         this.foreachRDD(saveFunc, false);
      });
   }

   public String saveAsObjectFiles$default$2() {
      return "";
   }

   public void saveAsTextFiles(final String prefix, final String suffix) {
      this.ssc().withScope((JFunction0.mcV.sp)() -> {
         Function2 saveFunc = (rdd, time) -> {
            $anonfun$saveAsTextFiles$2(prefix, suffix, rdd, time);
            return BoxedUnit.UNIT;
         };
         this.foreachRDD(saveFunc, false);
      });
   }

   public String saveAsTextFiles$default$2() {
      return "";
   }

   public DStream register() {
      this.ssc().graph().addOutputStream(this);
      return this;
   }

   // $FF: synthetic method
   public static final void $anonfun$initialize$2(final DStream $this, final DStream x$1) {
      x$1.initialize($this.zeroTime());
   }

   // $FF: synthetic method
   public static final void $anonfun$validateAtStart$8(final DStream x$2) {
      x$2.validateAtStart();
   }

   // $FF: synthetic method
   public static final void $anonfun$setContext$2(final DStream $this, final DStream x$3) {
      x$3.setContext($this.ssc());
   }

   // $FF: synthetic method
   public static final void $anonfun$setGraph$1(final DStream $this, final DStream x$4) {
      x$4.setGraph($this.graph());
   }

   // $FF: synthetic method
   public static final void $anonfun$remember$2(final DStream $this, final DStream x$5) {
      x$5.remember($this.parentRememberDuration());
   }

   // $FF: synthetic method
   public static final void $anonfun$createRDDWithLocalProperties$1(final DStream $this, final String scopeKey$1, final boolean displayInnerRDDOps$1, final String scopeNoOverrideKey$1, final RDDOperationScope s) {
      $this.ssc().sparkContext().setLocalProperty(scopeKey$1, s.toJson());
      if (displayInnerRDDOps$1) {
         $this.ssc().sparkContext().setLocalProperty(scopeNoOverrideKey$1, (String)null);
      } else {
         $this.ssc().sparkContext().setLocalProperty(scopeNoOverrideKey$1, "true");
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$generateJob$2(final Iterator iterator) {
   }

   // $FF: synthetic method
   public static final boolean $anonfun$clearMetadata$1(final DStream $this, final Time time$4, final Tuple2 x$6) {
      return ((Time)x$6._1()).$less$eq(time$4.$minus($this.rememberDuration()));
   }

   // $FF: synthetic method
   public static final int $anonfun$clearMetadata$5(final RDD x$7) {
      return x$7.id();
   }

   // $FF: synthetic method
   public static final void $anonfun$clearMetadata$6(final DStream $this, final Time time$4, final RDD rdd) {
      rdd.unpersist(rdd.unpersist$default$1());
      if (rdd instanceof BlockRDD var5) {
         $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Removing blocks of RDD ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RDD..MODULE$, var5)}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"of time ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIME..MODULE$, time$4)}))))));
         var5.removeBlocks();
         BoxedUnit var6 = BoxedUnit.UNIT;
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$clearMetadata$9(final Time time$4, final DStream x$8) {
      x$8.clearMetadata(time$4);
   }

   // $FF: synthetic method
   public static final void $anonfun$updateCheckpointData$2(final Time currentTime$1, final DStream x$9) {
      x$9.updateCheckpointData(currentTime$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$clearCheckpointData$2(final Time time$5, final DStream x$10) {
      x$10.clearCheckpointData(time$5);
   }

   // $FF: synthetic method
   public static final void $anonfun$restoreCheckpointData$2(final DStream x$11) {
      x$11.restoreCheckpointData();
   }

   // $FF: synthetic method
   public static final long $anonfun$count$5(final Tuple2 x$19) {
      return x$19._2$mcJ$sp();
   }

   // $FF: synthetic method
   public static final void $anonfun$foreachRDD$2(final Function1 cleanedF$1, final RDD r, final Time x$21) {
      cleanedF$1.apply(r);
   }

   // $FF: synthetic method
   public static final void $anonfun$print$4(final Object x) {
      scala.Predef..MODULE$.println(x);
   }

   // $FF: synthetic method
   public static final void $anonfun$print$3(final int num$1, final RDD rdd, final Time time) {
      Object firstNum = rdd.take(num$1 + 1);
      scala.Predef..MODULE$.println("-------------------------------------------");
      scala.Predef..MODULE$.println("Time: " + time);
      scala.Predef..MODULE$.println("-------------------------------------------");
      scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.genericArrayOps(scala.collection.ArrayOps..MODULE$.take$extension(scala.Predef..MODULE$.genericArrayOps(firstNum), num$1)), (x) -> {
         $anonfun$print$4(x);
         return BoxedUnit.UNIT;
      });
      if (scala.runtime.ScalaRunTime..MODULE$.array_length(firstNum) > num$1) {
         scala.Predef..MODULE$.println("...");
      }

      scala.Predef..MODULE$.println();
   }

   private static final Function2 foreachFunc$3(final int num$1) {
      return (rdd, time) -> {
         $anonfun$print$3(num$1, rdd, time);
         return BoxedUnit.UNIT;
      };
   }

   // $FF: synthetic method
   public static final long $anonfun$countByWindow$2(final Object x$25) {
      return 1L;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$countByValueAndWindow$5(final Tuple2 x) {
      return x._2$mcJ$sp() != 0L;
   }

   // $FF: synthetic method
   public static final void $anonfun$saveAsObjectFiles$2(final String prefix$1, final String suffix$1, final RDD rdd, final Time time) {
      String file = StreamingContext$.MODULE$.rddToFileName(prefix$1, suffix$1, time);
      rdd.saveAsObjectFile(file);
   }

   // $FF: synthetic method
   public static final void $anonfun$saveAsTextFiles$2(final String prefix$2, final String suffix$2, final RDD rdd, final Time time) {
      String file = StreamingContext$.MODULE$.rddToFileName(prefix$2, suffix$2, time);
      rdd.saveAsTextFile(file);
   }

   public DStream(final StreamingContext ssc, final ClassTag evidence$1) {
      this.ssc = ssc;
      this.evidence$1 = evidence$1;
      super();
      Logging.$init$(this);
      this.validateAtInit();
      this.generatedRDDs = new HashMap();
      this.zeroTime = null;
      this.rememberDuration = null;
      this.storageLevel = org.apache.spark.storage.StorageLevel..MODULE$.NONE();
      this.mustCheckpoint = false;
      this.checkpointDuration = null;
      this.checkpointData = new DStreamCheckpointData(this, evidence$1);
      this.restoredFromCheckpointData = false;
      this.graph = null;
      this.creationSite = DStream$.MODULE$.getCreationSite();
      this.baseScope = scala.Option..MODULE$.apply(this.ssc().sc().getLocalProperty(org.apache.spark.SparkContext..MODULE$.RDD_SCOPE_KEY()));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
