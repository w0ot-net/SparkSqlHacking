package org.apache.spark.streaming.api.java;

import java.io.Closeable;
import java.lang.invoke.SerializedLambda;
import java.util.List;
import java.util.Queue;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function0;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.StreamingContext;
import org.apache.spark.streaming.StreamingContextState;
import org.apache.spark.streaming.receiver.Receiver;
import org.apache.spark.streaming.scheduler.StreamingListener;
import scala.Function1;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;

/** @deprecated */
@ScalaSignature(
   bytes = "\u0006\u0005\u0011Mh\u0001\u0002\u001a4\u0001\u0001C\u0001B\u0014\u0001\u0003\u0006\u0004%\ta\u0014\u0005\t)\u0002\u0011\t\u0011)A\u0005!\")Q\u000b\u0001C\u0001-\")Q\u000b\u0001C\u00015\")Q\u000b\u0001C\u0001c\")Q\u000b\u0001C\u0001s\"1Q\u000b\u0001C\u0001\u0003\u0013Aa!\u0016\u0001\u0005\u0002\u0005\u0015\u0002BB+\u0001\t\u0003\tI\u0004\u0003\u0004V\u0001\u0011\u0005\u0011\u0011\n\u0005\u0007+\u0002!\t!a\u0014\t\u0013\u0005%\u0002A1A\u0005\u0002\u0005\u0015\u0004\u0002CA4\u0001\u0001\u0006I!a\u000b\t\u000f\u0005%\u0004\u0001\"\u0001\u0002l!9\u0011\u0011\u000e\u0001\u0005\u0002\u0005E\u0005bBAL\u0001\u0011\u0005\u0011\u0011\u0014\u0005\b\u0003/\u0004A\u0011AAm\u0011\u001d\t)\u000f\u0001C\u0001\u0003ODq!!?\u0001\t\u0003\tY\u0010C\u0004\u0002z\u0002!\tAa\u0003\t\u000f\te\u0001\u0001\"\u0001\u0003\u001c!9!\u0011\u0004\u0001\u0005\u0002\tu\u0003b\u0002B\r\u0001\u0011\u0005!\u0011\u0015\u0005\b\u0005\u0017\u0004A\u0011\u0001Bg\u0011\u001d\u0011Y\r\u0001C\u0001\u0005ODqAa3\u0001\t\u0003\u0011y\u0010C\u0004\u0004\u0016\u0001!\taa\u0006\t\u000f\r=\u0002\u0001\"\u0001\u00042!91q\u0006\u0001\u0005\u0002\rM\u0003bBB6\u0001\u0011\u00051Q\u000e\u0005\b\u0007g\u0003A\u0011AB[\u0011\u001d\u0019\u0019\u0010\u0001C\u0001\u0007kDqaa@\u0001\t\u0003!\t\u0001C\u0004\u0005\b\u0001!\t\u0001\"\u0003\t\u000f\u0011m\u0001\u0001\"\u0001\u0005\u001e!9AQ\u0005\u0001\u0005\u0002\u0011\u001d\u0002b\u0002C\u0015\u0001\u0011\u0005Aq\u0005\u0005\b\t[\u0002A\u0011\u0001C8\u0011\u001d!9\t\u0001C\u0001\tOAq\u0001b\"\u0001\t\u0003!I\tC\u0004\u0005\b\u0002!\t\u0001b$\t\u000f\u0011]\u0005\u0001\"\u0011\u0005(\u001d9AQV\u001a\t\u0002\u0011=fA\u0002\u001a4\u0011\u0003!\t\f\u0003\u0004VY\u0011\u0005A\u0011\u0018\u0005\b\twcC\u0011\u0001C_\u0011\u001d!Y\f\fC\u0001\t\u001bDq\u0001b/-\t\u0003!)\u000eC\u0004\u0005b2\"\t\u0001b9\u0003))\u000bg/Y*ue\u0016\fW.\u001b8h\u0007>tG/\u001a=u\u0015\t!T'\u0001\u0003kCZ\f'B\u0001\u001c8\u0003\r\t\u0007/\u001b\u0006\u0003qe\n\u0011b\u001d;sK\u0006l\u0017N\\4\u000b\u0005iZ\u0014!B:qCJ\\'B\u0001\u001f>\u0003\u0019\t\u0007/Y2iK*\ta(A\u0002pe\u001e\u001c\u0001aE\u0002\u0001\u0003\"\u0003\"A\u0011$\u000e\u0003\rS!\u0001R#\u0002\t1\fgn\u001a\u0006\u0002i%\u0011qi\u0011\u0002\u0007\u001f\nTWm\u0019;\u0011\u0005%cU\"\u0001&\u000b\u0005-+\u0015AA5p\u0013\ti%JA\u0005DY>\u001cX-\u00192mK\u0006\u00191o]2\u0016\u0003A\u0003\"!\u0015*\u000e\u0003]J!aU\u001c\u0003!M#(/Z1nS:<7i\u001c8uKb$\u0018\u0001B:tG\u0002\na\u0001P5oSRtDCA,Z!\tA\u0006!D\u00014\u0011\u0015q5\u00011\u0001Q)\u001196L\u001b7\t\u000bq#\u0001\u0019A/\u0002\r5\f7\u000f^3s!\tqvM\u0004\u0002`KB\u0011\u0001mY\u0007\u0002C*\u0011!mP\u0001\u0007yI|w\u000e\u001e \u000b\u0003\u0011\fQa]2bY\u0006L!AZ2\u0002\rA\u0013X\rZ3g\u0013\tA\u0017N\u0001\u0004TiJLgn\u001a\u0006\u0003M\u000eDQa\u001b\u0003A\u0002u\u000bq!\u00199q\u001d\u0006lW\rC\u0003n\t\u0001\u0007a.A\u0007cCR\u001c\u0007\u000eR;sCRLwN\u001c\t\u0003#>L!\u0001]\u001c\u0003\u0011\u0011+(/\u0019;j_:$ba\u0016:tiV<\b\"\u0002/\u0006\u0001\u0004i\u0006\"B6\u0006\u0001\u0004i\u0006\"B7\u0006\u0001\u0004q\u0007\"\u0002<\u0006\u0001\u0004i\u0016!C:qCJ\\\u0007j\\7f\u0011\u0015AX\u00011\u0001^\u0003\u001dQ\u0017M\u001d$jY\u0016$ba\u0016>|yvt\b\"\u0002/\u0007\u0001\u0004i\u0006\"B6\u0007\u0001\u0004i\u0006\"B7\u0007\u0001\u0004q\u0007\"\u0002<\u0007\u0001\u0004i\u0006BB@\u0007\u0001\u0004\t\t!\u0001\u0003kCJ\u001c\b#BA\u0002\u0003\u000biV\"A2\n\u0007\u0005\u001d1MA\u0003BeJ\f\u0017\u0010F\u0007X\u0003\u0017\ti!a\u0004\u0002\u0012\u0005M\u0011Q\u0003\u0005\u00069\u001e\u0001\r!\u0018\u0005\u0006W\u001e\u0001\r!\u0018\u0005\u0006[\u001e\u0001\rA\u001c\u0005\u0006m\u001e\u0001\r!\u0018\u0005\u0007\u007f\u001e\u0001\r!!\u0001\t\u000f\u0005]q\u00011\u0001\u0002\u001a\u0005YQM\u001c<je>tW.\u001a8u!\u0019\tY\"!\t^;6\u0011\u0011Q\u0004\u0006\u0004\u0003?)\u0015\u0001B;uS2LA!a\t\u0002\u001e\t\u0019Q*\u00199\u0015\u000b]\u000b9#a\u000e\t\u000f\u0005%\u0002\u00021\u0001\u0002,\u0005a1\u000f]1sW\u000e{g\u000e^3yiB!\u0011QFA\u001a\u001b\t\tyCC\u00025\u0003cQ!AN\u001d\n\t\u0005U\u0012q\u0006\u0002\u0011\u0015\u00064\u0018m\u00159be.\u001cuN\u001c;fqRDQ!\u001c\u0005A\u00029$RaVA\u001e\u0003\u000fBq!!\u0010\n\u0001\u0004\ty$\u0001\u0003d_:4\u0007\u0003BA!\u0003\u0007j\u0011!O\u0005\u0004\u0003\u000bJ$!C*qCJ\\7i\u001c8g\u0011\u0015i\u0017\u00021\u0001o)\r9\u00161\n\u0005\u0007\u0003\u001bR\u0001\u0019A/\u0002\tA\fG\u000f\u001b\u000b\u0006/\u0006E\u00131\u000b\u0005\u0007\u0003\u001bZ\u0001\u0019A/\t\u000f\u0005U3\u00021\u0001\u0002X\u0005Q\u0001.\u00193p_B\u001cuN\u001c4\u0011\t\u0005e\u0013\u0011M\u0007\u0003\u00037RA!!\u0010\u0002^)\u0019\u0011qL\u001e\u0002\r!\fGm\\8q\u0013\u0011\t\u0019'a\u0017\u0003\u001b\r{gNZ5hkJ\fG/[8o+\t\tY#A\u0007ta\u0006\u00148nQ8oi\u0016DH\u000fI\u0001\u0011g>\u001c7.\u001a;UKb$8\u000b\u001e:fC6$\u0002\"!\u001c\u0002t\u0005]\u0014\u0011\u0011\t\u00051\u0006=T,C\u0002\u0002rM\u0012\u0001DS1wCJ+7-Z5wKJLe\u000e];u\tN#(/Z1n\u0011\u0019\t)H\u0004a\u0001;\u0006A\u0001n\\:u]\u0006lW\rC\u0004\u0002z9\u0001\r!a\u001f\u0002\tA|'\u000f\u001e\t\u0005\u0003\u0007\ti(C\u0002\u0002\u0000\r\u00141!\u00138u\u0011\u001d\t\u0019I\u0004a\u0001\u0003\u000b\u000bAb\u001d;pe\u0006<W\rT3wK2\u0004B!a\"\u0002\u000e6\u0011\u0011\u0011\u0012\u0006\u0004\u0003\u0017K\u0014aB:u_J\fw-Z\u0005\u0005\u0003\u001f\u000bII\u0001\u0007Ti>\u0014\u0018mZ3MKZ,G\u000e\u0006\u0004\u0002n\u0005M\u0015Q\u0013\u0005\u0007\u0003kz\u0001\u0019A/\t\u000f\u0005et\u00021\u0001\u0002|\u0005a1o\\2lKR\u001cFO]3b[V!\u00111TAR))\ti*!.\u00028\u0006e\u0016Q\u001b\t\u00061\u0006=\u0014q\u0014\t\u0005\u0003C\u000b\u0019\u000b\u0004\u0001\u0005\u000f\u0005\u0015\u0006C1\u0001\u0002(\n\tA+\u0005\u0003\u0002*\u0006=\u0006\u0003BA\u0002\u0003WK1!!,d\u0005\u001dqu\u000e\u001e5j]\u001e\u0004B!a\u0001\u00022&\u0019\u00111W2\u0003\u0007\u0005s\u0017\u0010\u0003\u0004\u0002vA\u0001\r!\u0018\u0005\b\u0003s\u0002\u0002\u0019AA>\u0011\u001d\tY\f\u0005a\u0001\u0003{\u000b\u0011bY8om\u0016\u0014H/\u001a:\u0011\u0011\u0005}\u0016QYAe\u0003\u001fl!!!1\u000b\t\u0005\r\u0017qF\u0001\tMVt7\r^5p]&!\u0011qYAa\u0005!1UO\\2uS>t\u0007cA%\u0002L&\u0019\u0011Q\u001a&\u0003\u0017%s\u0007/\u001e;TiJ,\u0017-\u001c\t\u0006\u0005\u0006E\u0017qT\u0005\u0004\u0003'\u001c%\u0001C%uKJ\f'\r\\3\t\u000f\u0005\r\u0005\u00031\u0001\u0002\u0006\u0006qA/\u001a=u\r&dWm\u0015;sK\u0006lG\u0003BAn\u0003C\u0004B\u0001WAo;&\u0019\u0011q\\\u001a\u0003\u0017)\u000bg/\u0019#TiJ,\u0017-\u001c\u0005\u0007\u0003G\f\u0002\u0019A/\u0002\u0013\u0011L'/Z2u_JL\u0018a\u00052j]\u0006\u0014\u0018PU3d_J$7o\u0015;sK\u0006lGCBAu\u0003g\f)\u0010E\u0003Y\u0003;\fY\u000f\u0005\u0004\u0002\u0004\u0005\u0015\u0011Q\u001e\t\u0005\u0003\u0007\ty/C\u0002\u0002r\u000e\u0014AAQ=uK\"1\u00111\u001d\nA\u0002uCq!a>\u0013\u0001\u0004\tY(\u0001\u0007sK\u000e|'\u000f\u001a'f]\u001e$\b.A\bsC^\u001cvnY6fiN#(/Z1n+\u0011\tiPa\u0001\u0015\u0011\u0005}(Q\u0001B\u0004\u0005\u0013\u0001R\u0001WA8\u0005\u0003\u0001B!!)\u0003\u0004\u00119\u0011QU\nC\u0002\u0005\u001d\u0006BBA;'\u0001\u0007Q\fC\u0004\u0002zM\u0001\r!a\u001f\t\u000f\u0005\r5\u00031\u0001\u0002\u0006V!!Q\u0002B\n)\u0019\u0011yA!\u0006\u0003\u0018A)\u0001,a\u001c\u0003\u0012A!\u0011\u0011\u0015B\n\t\u001d\t)\u000b\u0006b\u0001\u0003OCa!!\u001e\u0015\u0001\u0004i\u0006bBA=)\u0001\u0007\u00111P\u0001\u000bM&dWm\u0015;sK\u0006lW\u0003\u0003B\u000f\u0005O\u0011iCa\u0013\u0015\u0015\t}!\u0011\u0007B\u001a\u0005{\u0011\u0019\u0005E\u0004Y\u0005C\u0011)Ca\u000b\n\u0007\t\r2G\u0001\u000bKCZ\f\u0007+Y5s\u0013:\u0004X\u000f\u001e#TiJ,\u0017-\u001c\t\u0005\u0003C\u00139\u0003B\u0004\u0003*U\u0011\r!a*\u0003\u0003-\u0003B!!)\u0003.\u00119!qF\u000bC\u0002\u0005\u001d&!\u0001,\t\r\u0005\rX\u00031\u0001^\u0011\u001d\u0011)$\u0006a\u0001\u0005o\taa[\"mCN\u001c\b#\u00020\u0003:\t\u0015\u0012b\u0001B\u001eS\n)1\t\\1tg\"9!qH\u000bA\u0002\t\u0005\u0013A\u0002<DY\u0006\u001c8\u000fE\u0003_\u0005s\u0011Y\u0003C\u0004\u0003FU\u0001\rAa\u0012\u0002\r\u0019\u001cE.Y:t!\u0015q&\u0011\bB%!\u0011\t\tKa\u0013\u0005\u000f\t5SC1\u0001\u0003P\t\ta)\u0005\u0003\u0002*\nE\u0003\u0003\u0003B*\u00053\u0012)Ca\u000b\u000e\u0005\tU#\u0002\u0002B,\u0003;\n\u0011\"\\1qe\u0016$WoY3\n\t\tm#Q\u000b\u0002\f\u0013:\u0004X\u000f\u001e$pe6\fG/\u0006\u0005\u0003`\t\u0015$\u0011\u000eB>)9\u0011\tGa\u001b\u0003n\tE$Q\u000fBA\u00053\u0003r\u0001\u0017B\u0011\u0005G\u00129\u0007\u0005\u0003\u0002\"\n\u0015Da\u0002B\u0015-\t\u0007\u0011q\u0015\t\u0005\u0003C\u0013I\u0007B\u0004\u00030Y\u0011\r!a*\t\r\u0005\rh\u00031\u0001^\u0011\u001d\u0011)D\u0006a\u0001\u0005_\u0002RA\u0018B\u001d\u0005GBqAa\u0010\u0017\u0001\u0004\u0011\u0019\bE\u0003_\u0005s\u00119\u0007C\u0004\u0003FY\u0001\rAa\u001e\u0011\u000by\u0013ID!\u001f\u0011\t\u0005\u0005&1\u0010\u0003\b\u0005\u001b2\"\u0019\u0001B?#\u0011\tIKa \u0011\u0011\tM#\u0011\fB2\u0005OBqAa!\u0017\u0001\u0004\u0011))\u0001\u0004gS2$XM\u001d\t\t\u0003\u007f\u000b)Ma\"\u0003\u0014B!!\u0011\u0012BH\u001b\t\u0011YI\u0003\u0003\u0003\u000e\u0006u\u0013A\u00014t\u0013\u0011\u0011\tJa#\u0003\tA\u000bG\u000f\u001b\t\u0004\u0005\nU\u0015b\u0001BL\u0007\n9!i\\8mK\u0006t\u0007b\u0002BN-\u0001\u0007!QT\u0001\r]\u0016<h)\u001b7fg>sG.\u001f\t\u0005\u0003\u0007\u0011y*C\u0002\u0003\u0018\u000e,\u0002Ba)\u0003*\n5&q\u0018\u000b\u0011\u0005K\u0013yK!-\u00036\ne&Q\u0019Bd\u0005\u0013\u0004r\u0001\u0017B\u0011\u0005O\u0013Y\u000b\u0005\u0003\u0002\"\n%Fa\u0002B\u0015/\t\u0007\u0011q\u0015\t\u0005\u0003C\u0013i\u000bB\u0004\u00030]\u0011\r!a*\t\r\u0005\rx\u00031\u0001^\u0011\u001d\u0011)d\u0006a\u0001\u0005g\u0003RA\u0018B\u001d\u0005OCqAa\u0010\u0018\u0001\u0004\u00119\fE\u0003_\u0005s\u0011Y\u000bC\u0004\u0003F]\u0001\rAa/\u0011\u000by\u0013ID!0\u0011\t\u0005\u0005&q\u0018\u0003\b\u0005\u001b:\"\u0019\u0001Ba#\u0011\tIKa1\u0011\u0011\tM#\u0011\fBT\u0005WCqAa!\u0018\u0001\u0004\u0011)\tC\u0004\u0003\u001c^\u0001\rA!(\t\u000f\u0005ur\u00031\u0001\u0002X\u0005Y\u0011/^3vKN#(/Z1n+\u0011\u0011yM!6\u0015\t\tE'q\u001b\t\u00061\u0006u'1\u001b\t\u0005\u0003C\u0013)\u000eB\u0004\u0002&b\u0011\r!a*\t\u000f\te\u0007\u00041\u0001\u0003\\\u0006)\u0011/^3vKB1\u00111\u0004Bo\u0005CLAAa8\u0002\u001e\t)\u0011+^3vKB1\u0011Q\u0006Br\u0005'LAA!:\u00020\t9!*\u0019<b%\u0012#U\u0003\u0002Bu\u0005g$bAa;\u0003v\nm\b#\u0002-\u0003n\nE\u0018b\u0001Bxg\t\u0001\"*\u0019<b\u0013:\u0004X\u000f\u001e#TiJ,\u0017-\u001c\t\u0005\u0003C\u0013\u0019\u0010B\u0004\u0002&f\u0011\r!a*\t\u000f\te\u0017\u00041\u0001\u0003xB1\u00111\u0004Bo\u0005s\u0004b!!\f\u0003d\nE\bb\u0002B\u007f3\u0001\u0007!QT\u0001\u000b_:,\u0017\t^!US6,W\u0003BB\u0001\u0007\u000f!\u0002ba\u0001\u0004\n\r=1\u0011\u0003\t\u00061\n58Q\u0001\t\u0005\u0003C\u001b9\u0001B\u0004\u0002&j\u0011\r!a*\t\u000f\te'\u00041\u0001\u0004\fA1\u00111\u0004Bo\u0007\u001b\u0001b!!\f\u0003d\u000e\u0015\u0001b\u0002B\u007f5\u0001\u0007!Q\u0014\u0005\b\u0007'Q\u0002\u0019AB\u0007\u0003)!WMZ1vYR\u0014F\tR\u0001\u000fe\u0016\u001cW-\u001b<feN#(/Z1n+\u0011\u0019Iba\b\u0015\t\rm1\u0011\u0005\t\u00061\u0006=4Q\u0004\t\u0005\u0003C\u001by\u0002B\u0004\u0002&n\u0011\r!a*\t\u000f\r\r2\u00041\u0001\u0004&\u0005A!/Z2fSZ,'\u000f\u0005\u0004\u0004(\r-2QD\u0007\u0003\u0007SQ1aa\t8\u0013\u0011\u0019ic!\u000b\u0003\u0011I+7-Z5wKJ\fQ!\u001e8j_:,Baa\r\u0004:Q!1QGB\u001e!\u0015A\u0016Q\\B\u001c!\u0011\t\tk!\u000f\u0005\u000f\u0005\u0015FD1\u0001\u0002(\"91Q\b\u000fA\u0002\r}\u0012!\u00036egR\u0014X-Y7t!\u0019\t\u0019a!\u0011\u00046%\u001911I2\u0003\u0015q\u0012X\r]3bi\u0016$g\bK\u0002\u001d\u0007\u000f\u0002Ba!\u0013\u0004P5\u001111\n\u0006\u0004\u0007\u001b\u001a\u0017AC1o]>$\u0018\r^5p]&!1\u0011KB&\u0005\u001d1\u0018M]1sON,ba!\u0016\u0004`\r\rD\u0003BB,\u0007K\u0002r\u0001WB-\u0007;\u001a\t'C\u0002\u0004\\M\u0012qBS1wCB\u000b\u0017N\u001d#TiJ,\u0017-\u001c\t\u0005\u0003C\u001by\u0006B\u0004\u0003*u\u0011\r!a*\u0011\t\u0005\u000561\r\u0003\b\u0005_i\"\u0019AAT\u0011\u001d\u0019i$\ba\u0001\u0007O\u0002b!a\u0001\u0004B\r]\u0003fA\u000f\u0004H\u0005IAO]1og\u001a|'/\\\u000b\u0005\u0007_\u001a)\b\u0006\u0004\u0004r\r]4Q\u0012\t\u00061\u0006u71\u000f\t\u0005\u0003C\u001b)\bB\u0004\u0002&z\u0011\r!a*\t\u000f\red\u00041\u0001\u0004|\u0005AAm\u001d;sK\u0006l7\u000f\u0005\u0004\u0002\u001c\ru4\u0011Q\u0005\u0005\u0007\u007f\niB\u0001\u0003MSN$\b\u0007BBB\u0007\u000f\u0003R\u0001WAo\u0007\u000b\u0003B!!)\u0004\b\u0012a1\u0011RBF\u0003\u0003\u0005\tQ!\u0001\u0002(\n\u0019q\fJ\u0019\t\u000f\red\u00041\u0001\u0004|!91q\u0012\u0010A\u0002\rE\u0015!\u0004;sC:\u001chm\u001c:n\rVt7\r\u0005\u0006\u0002@\u000eM5qSBT\u0007cKAa!&\u0002B\nIa)\u001e8di&|gN\r\t\u0007\u00037\u0019ih!'1\t\rm5q\u0014\t\u0007\u0003[\u0011\u0019o!(\u0011\t\u0005\u00056q\u0014\u0003\r\u0007C\u001b\u0019+!A\u0001\u0002\u000b\u0005\u0011q\u0015\u0002\u0004?\u0012\u0012\u0004bBBH=\u0001\u00071Q\u0015\t\u000b\u0003\u007f\u001b\u0019ja&\u0004(\u000e5\u0006cA)\u0004*&\u001911V\u001c\u0003\tQKW.\u001a\t\u0007\u0003[\u0011\u0019oa,\u0011\t\u0005\u00056Q\u000f\t\u0007\u0003[\u0011\u0019oa\u001d\u0002\u001fQ\u0014\u0018M\\:g_JlGk\u001c)bSJ,baa.\u0004>\u000e\u0005GCBB]\u0007\u0007\u001c\u0019\u000eE\u0004Y\u00073\u001aYla0\u0011\t\u0005\u00056Q\u0018\u0003\b\u0005Sy\"\u0019AAT!\u0011\t\tk!1\u0005\u000f\t=rD1\u0001\u0002(\"91\u0011P\u0010A\u0002\r\u0015\u0007CBA\u000e\u0007{\u001a9\r\r\u0003\u0004J\u000e5\u0007#\u0002-\u0002^\u000e-\u0007\u0003BAQ\u0007\u001b$Aba4\u0004R\u0006\u0005\t\u0011!B\u0001\u0003O\u00131a\u0018\u00135\u0011\u001d\u0019Ih\ba\u0001\u0007\u000bDqaa$ \u0001\u0004\u0019)\u000e\u0005\u0006\u0002@\u000eM5q[BT\u0007c\u0004b!a\u0007\u0004~\re\u0007\u0007BBn\u0007?\u0004b!!\f\u0003d\u000eu\u0007\u0003BAQ\u0007?$Ab!9\u0004d\u0006\u0005\t\u0011!B\u0001\u0003O\u00131a\u0018\u00136\u0011\u001d\u0019yi\ba\u0001\u0007K\u0004\"\"a0\u0004\u0014\u000e]7qUBt!!\tic!;\u0004n\u000e=\u0018\u0002BBv\u0003_\u00111BS1wCB\u000b\u0017N\u001d*E\tB!\u0011\u0011UB_!\u0011\t\tk!1\u0011\u0011\u000552\u0011^B^\u0007\u007f\u000b!b\u00195fG.\u0004x.\u001b8u)\u0011\u00199p!@\u0011\t\u0005\r1\u0011`\u0005\u0004\u0007w\u001c'\u0001B+oSRDa!a9!\u0001\u0004i\u0016\u0001\u0003:f[\u0016l'-\u001a:\u0015\t\r]H1\u0001\u0005\u0007\t\u000b\t\u0003\u0019\u00018\u0002\u0011\u0011,(/\u0019;j_:\fA#\u00193e'R\u0014X-Y7j]\u001ed\u0015n\u001d;f]\u0016\u0014H\u0003BB|\t\u0017Aq\u0001\"\u0004#\u0001\u0004!y!A\ttiJ,\u0017-\\5oO2K7\u000f^3oKJ\u0004B\u0001\"\u0005\u0005\u00185\u0011A1\u0003\u0006\u0004\t+9\u0014!C:dQ\u0016$W\u000f\\3s\u0013\u0011!I\u0002b\u0005\u0003#M#(/Z1nS:<G*[:uK:,'/\u0001\u0005hKR\u001cF/\u0019;f)\t!y\u0002E\u0002R\tCI1\u0001b\t8\u0005U\u0019FO]3b[&twmQ8oi\u0016DHo\u0015;bi\u0016\fQa\u001d;beR$\"aa>\u0002!\u0005<\u0018-\u001b;UKJl\u0017N\\1uS>t\u0007&B\u0013\u0005.\u0011\u0015\u0003CBA\u0002\t_!\u0019$C\u0002\u00052\r\u0014a\u0001\u001e5s_^\u001c\b\u0003\u0002C\u001b\t\u007fqA\u0001b\u000e\u0005<9\u0019\u0001\r\"\u000f\n\u0003\u0011L1\u0001\"\u0010d\u0003\u001d\u0001\u0018mY6bO\u0016LA\u0001\"\u0011\u0005D\t!\u0012J\u001c;feJ,\b\u000f^3e\u000bb\u001cW\r\u001d;j_:T1\u0001\"\u0010dc\u0019qR\fb\u0012\u0005lEJ1\u0005\"\u0013\u0005P\u0011\u0005D\u0011K\u000b\u0005\t\u0017\"i%F\u0001^\t\u001d\t)k\u0010b\u0001\t/JA\u0001\"\u0015\u0005T\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIER1\u0001\"\u0016d\u0003\u0019!\bN]8xgF!\u0011\u0011\u0016C-!\u0011!Y\u0006\"\u0018\u000f\t\u0005\rA1H\u0005\u0005\t?\"\u0019EA\u0005UQJ|w/\u00192mKFJ1\u0005b\u0019\u0005f\u0011\u001dDQ\u000b\b\u0005\u0003\u0007!)'C\u0002\u0005V\r\fdAIA\u0002G\u0012%$!B:dC2\f\u0017g\u0001\u0014\u00054\u0005I\u0012m^1jiR+'/\\5oCRLwN\\(s)&lWm\\;u)\u0011\u0011i\n\"\u001d\t\u000f\u0011Md\u00051\u0001\u0005v\u00059A/[7f_V$\b\u0003BA\u0002\toJ1\u0001\"\u001fd\u0005\u0011auN\\4)\u000b\u0019\"i\u0003\" 2\ryiFq\u0010CCc%\u0019C\u0011\nC(\t\u0003#\t&M\u0005$\tG\")\u0007b!\u0005VE2!%a\u0001d\tS\n4A\nC\u001a\u0003\u0011\u0019Ho\u001c9\u0015\t\r]H1\u0012\u0005\b\t\u001bC\u0003\u0019\u0001BO\u0003A\u0019Ho\u001c9Ta\u0006\u00148nQ8oi\u0016DH\u000f\u0006\u0004\u0004x\u0012EE1\u0013\u0005\b\t\u001bK\u0003\u0019\u0001BO\u0011\u001d!)*\u000ba\u0001\u0005;\u000bab\u001d;pa\u001e\u0013\u0018mY3gk2d\u00170A\u0003dY>\u001cX\rK\u0006\u0001\t7#\t\u000bb)\u0005(\u0012%\u0006\u0003BA\u0002\t;K1\u0001b(d\u0005)!W\r\u001d:fG\u0006$X\rZ\u0001\b[\u0016\u001c8/Y4fC\t!)+A\u001cE'R\u0014X-Y7!SN\u0004C-\u001a9sK\u000e\fG/\u001a3/A5KwM]1uK\u0002\"x\u000eI*ueV\u001cG/\u001e:fI\u0002\u001aFO]3b[&twML\u0001\u0006g&t7-Z\u0011\u0003\tW\u000b1b\u00159be.\u00043G\f\u001b/a\u0005!\"*\u0019<b'R\u0014X-Y7j]\u001e\u001cuN\u001c;fqR\u0004\"\u0001\u0017\u0017\u0014\u00071\"\u0019\f\u0005\u0003\u0002\u0004\u0011U\u0016b\u0001C\\G\n1\u0011I\\=SK\u001a$\"\u0001b,\u0002\u0017\u001d,Go\u0014:De\u0016\fG/\u001a\u000b\u0006/\u0012}F1\u0019\u0005\u0007\t\u0003t\u0003\u0019A/\u0002\u001d\rDWmY6q_&tG\u000fU1uQ\"9AQ\u0019\u0018A\u0002\u0011\u001d\u0017\u0001D2sK\u0006$\u0018N\\4Gk:\u001c\u0007#BA`\t\u0013<\u0016\u0002\u0002Cf\u0003\u0003\u0014\u0011BR;oGRLwN\u001c\u0019\u0015\u000f]#y\r\"5\u0005T\"1A\u0011Y\u0018A\u0002uCq\u0001\"20\u0001\u0004!9\rC\u0004\u0002V=\u0002\r!a\u0016\u0015\u0013]#9\u000e\"7\u0005\\\u0012u\u0007B\u0002Caa\u0001\u0007Q\fC\u0004\u0005FB\u0002\r\u0001b2\t\u000f\u0005U\u0003\u00071\u0001\u0002X!9Aq\u001c\u0019A\u0002\tu\u0015!D2sK\u0006$Xm\u00148FeJ|'/\u0001\u0006kCJ|em\u00117bgN$B!!\u0001\u0005f\"9Aq]\u0019A\u0002\u0011%\u0018aA2mgB\"A1\u001eCx!\u0015q&\u0011\bCw!\u0011\t\t\u000bb<\u0005\u0019\u0011EHQ]A\u0001\u0002\u0003\u0015\t!a*\u0003\u0007}#s\u0007"
)
public class JavaStreamingContext implements Closeable {
   private final StreamingContext ssc;
   private final JavaSparkContext sparkContext;

   public static String[] jarOfClass(final Class cls) {
      return JavaStreamingContext$.MODULE$.jarOfClass(cls);
   }

   public static JavaStreamingContext getOrCreate(final String checkpointPath, final Function0 creatingFunc, final Configuration hadoopConf, final boolean createOnError) {
      return JavaStreamingContext$.MODULE$.getOrCreate(checkpointPath, creatingFunc, hadoopConf, createOnError);
   }

   public static JavaStreamingContext getOrCreate(final String checkpointPath, final Function0 creatingFunc, final Configuration hadoopConf) {
      return JavaStreamingContext$.MODULE$.getOrCreate(checkpointPath, creatingFunc, hadoopConf);
   }

   public static JavaStreamingContext getOrCreate(final String checkpointPath, final Function0 creatingFunc) {
      return JavaStreamingContext$.MODULE$.getOrCreate(checkpointPath, creatingFunc);
   }

   public JavaDStream union(final JavaDStream... jdstreams) {
      return this.union((Seq).MODULE$.wrapRefArray(jdstreams));
   }

   public JavaPairDStream union(final JavaPairDStream... jdstreams) {
      return this.union((Seq).MODULE$.wrapRefArray(jdstreams));
   }

   public StreamingContext ssc() {
      return this.ssc;
   }

   public JavaSparkContext sparkContext() {
      return this.sparkContext;
   }

   public JavaReceiverInputDStream socketTextStream(final String hostname, final int port, final StorageLevel storageLevel) {
      return JavaReceiverInputDStream$.MODULE$.fromReceiverInputDStream(this.ssc().socketTextStream(hostname, port, storageLevel), scala.reflect.ClassTag..MODULE$.apply(String.class));
   }

   public JavaReceiverInputDStream socketTextStream(final String hostname, final int port) {
      return JavaReceiverInputDStream$.MODULE$.fromReceiverInputDStream(this.ssc().socketTextStream(hostname, port, this.ssc().socketTextStream$default$3()), scala.reflect.ClassTag..MODULE$.apply(String.class));
   }

   public JavaReceiverInputDStream socketStream(final String hostname, final int port, final Function converter, final StorageLevel storageLevel) {
      ClassTag cmt = (ClassTag)scala.Predef..MODULE$.implicitly(scala.reflect.ClassTag..MODULE$.AnyRef());
      return JavaReceiverInputDStream$.MODULE$.fromReceiverInputDStream(this.ssc().socketStream(hostname, port, fn$1(converter), storageLevel, cmt), cmt);
   }

   public JavaDStream textFileStream(final String directory) {
      return JavaDStream$.MODULE$.fromDStream(this.ssc().textFileStream(directory), scala.reflect.ClassTag..MODULE$.apply(String.class));
   }

   public JavaDStream binaryRecordsStream(final String directory, final int recordLength) {
      return JavaDStream$.MODULE$.fromDStream(this.ssc().binaryRecordsStream(directory, recordLength), scala.reflect.ClassTag..MODULE$.apply(.MODULE$.arrayClass(Byte.TYPE)));
   }

   public JavaReceiverInputDStream rawSocketStream(final String hostname, final int port, final StorageLevel storageLevel) {
      ClassTag cmt = (ClassTag)scala.Predef..MODULE$.implicitly(scala.reflect.ClassTag..MODULE$.AnyRef());
      return JavaReceiverInputDStream$.MODULE$.fromReceiverInputDStream(this.ssc().rawSocketStream(hostname, port, storageLevel, cmt), cmt);
   }

   public JavaReceiverInputDStream rawSocketStream(final String hostname, final int port) {
      ClassTag cmt = (ClassTag)scala.Predef..MODULE$.implicitly(scala.reflect.ClassTag..MODULE$.AnyRef());
      return JavaReceiverInputDStream$.MODULE$.fromReceiverInputDStream(this.ssc().rawSocketStream(hostname, port, this.ssc().rawSocketStream$default$3(), cmt), cmt);
   }

   public JavaPairInputDStream fileStream(final String directory, final Class kClass, final Class vClass, final Class fClass) {
      ClassTag cmk = scala.reflect.ClassTag..MODULE$.apply(kClass);
      ClassTag cmv = scala.reflect.ClassTag..MODULE$.apply(vClass);
      ClassTag cmf = scala.reflect.ClassTag..MODULE$.apply(fClass);
      return JavaPairInputDStream$.MODULE$.fromInputDStream(this.ssc().fileStream(directory, cmk, cmv, cmf), cmk, cmv);
   }

   public JavaPairInputDStream fileStream(final String directory, final Class kClass, final Class vClass, final Class fClass, final Function filter, final boolean newFilesOnly) {
      ClassTag cmk = scala.reflect.ClassTag..MODULE$.apply(kClass);
      ClassTag cmv = scala.reflect.ClassTag..MODULE$.apply(vClass);
      ClassTag cmf = scala.reflect.ClassTag..MODULE$.apply(fClass);
      return JavaPairInputDStream$.MODULE$.fromInputDStream(this.ssc().fileStream(directory, fn$2(filter), newFilesOnly, cmk, cmv, cmf), cmk, cmv);
   }

   public JavaPairInputDStream fileStream(final String directory, final Class kClass, final Class vClass, final Class fClass, final Function filter, final boolean newFilesOnly, final Configuration conf) {
      ClassTag cmk = scala.reflect.ClassTag..MODULE$.apply(kClass);
      ClassTag cmv = scala.reflect.ClassTag..MODULE$.apply(vClass);
      ClassTag cmf = scala.reflect.ClassTag..MODULE$.apply(fClass);
      return JavaPairInputDStream$.MODULE$.fromInputDStream(this.ssc().fileStream(directory, fn$3(filter), newFilesOnly, conf, cmk, cmv, cmf), cmk, cmv);
   }

   public JavaDStream queueStream(final Queue queue) {
      ClassTag cm = (ClassTag)scala.Predef..MODULE$.implicitly(scala.reflect.ClassTag..MODULE$.AnyRef());
      scala.collection.mutable.Queue sQueue = new scala.collection.mutable.Queue(scala.collection.mutable.Queue..MODULE$.$lessinit$greater$default$1());
      sQueue.$plus$plus$eq((IterableOnce)scala.jdk.CollectionConverters..MODULE$.CollectionHasAsScala(queue).asScala().map((x$1) -> x$1.rdd()));
      return JavaDStream$.MODULE$.fromDStream(this.ssc().queueStream(sQueue, this.ssc().queueStream$default$2(), cm), cm);
   }

   public JavaInputDStream queueStream(final Queue queue, final boolean oneAtATime) {
      ClassTag cm = (ClassTag)scala.Predef..MODULE$.implicitly(scala.reflect.ClassTag..MODULE$.AnyRef());
      scala.collection.mutable.Queue sQueue = new scala.collection.mutable.Queue(scala.collection.mutable.Queue..MODULE$.$lessinit$greater$default$1());
      sQueue.$plus$plus$eq((IterableOnce)scala.jdk.CollectionConverters..MODULE$.CollectionHasAsScala(queue).asScala().map((x$2) -> x$2.rdd()));
      return JavaInputDStream$.MODULE$.fromInputDStream(this.ssc().queueStream(sQueue, oneAtATime, cm), cm);
   }

   public JavaInputDStream queueStream(final Queue queue, final boolean oneAtATime, final JavaRDD defaultRDD) {
      ClassTag cm = (ClassTag)scala.Predef..MODULE$.implicitly(scala.reflect.ClassTag..MODULE$.AnyRef());
      scala.collection.mutable.Queue sQueue = new scala.collection.mutable.Queue(scala.collection.mutable.Queue..MODULE$.$lessinit$greater$default$1());
      sQueue.$plus$plus$eq((IterableOnce)scala.jdk.CollectionConverters..MODULE$.CollectionHasAsScala(queue).asScala().map((x$3) -> x$3.rdd()));
      return JavaInputDStream$.MODULE$.fromInputDStream(this.ssc().queueStream(sQueue, oneAtATime, defaultRDD.rdd(), cm), cm);
   }

   public JavaReceiverInputDStream receiverStream(final Receiver receiver) {
      ClassTag cm = (ClassTag)scala.Predef..MODULE$.implicitly(scala.reflect.ClassTag..MODULE$.AnyRef());
      return JavaReceiverInputDStream$.MODULE$.fromReceiverInputDStream(this.ssc().receiverStream(receiver, cm), cm);
   }

   public JavaDStream union(final Seq jdstreams) {
      scala.Predef..MODULE$.require(jdstreams.nonEmpty(), () -> "Union called on no streams");
      ClassTag cm = ((JavaDStream)jdstreams.head()).classTag();
      return JavaDStream$.MODULE$.fromDStream(this.ssc().union((Seq)jdstreams.map((x$4) -> x$4.dstream()), cm), cm);
   }

   public JavaPairDStream union(final Seq jdstreams) {
      scala.Predef..MODULE$.require(jdstreams.nonEmpty(), () -> "Union called on no streams");
      ClassTag cm = ((JavaPairDStream)jdstreams.head()).classTag();
      ClassTag kcm = ((JavaPairDStream)jdstreams.head()).kManifest();
      ClassTag vcm = ((JavaPairDStream)jdstreams.head()).vManifest();
      return new JavaPairDStream(this.ssc().union((Seq)jdstreams.map((x$5) -> x$5.dstream()), cm), kcm, vcm);
   }

   public JavaDStream transform(final List dstreams, final Function2 transformFunc) {
      ClassTag cmt = (ClassTag)scala.Predef..MODULE$.implicitly(scala.reflect.ClassTag..MODULE$.AnyRef());
      scala.Function2 scalaTransformFunc = (rdds, time) -> {
         List jrdds = scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava((scala.collection.Seq)rdds.map((x$6) -> org.apache.spark.api.java.JavaRDD..MODULE$.fromRDD(x$6, scala.reflect.ClassTag..MODULE$.apply(Object.class)))).asJava();
         return ((JavaRDD)transformFunc.call(jrdds, time)).rdd();
      };
      return JavaDStream$.MODULE$.fromDStream(this.ssc().transform(((IterableOnceOps)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(dstreams).asScala().map((x$7) -> x$7.dstream())).toSeq(), scalaTransformFunc, cmt), cmt);
   }

   public JavaPairDStream transformToPair(final List dstreams, final Function2 transformFunc) {
      ClassTag cmk = (ClassTag)scala.Predef..MODULE$.implicitly(scala.reflect.ClassTag..MODULE$.AnyRef());
      ClassTag cmv = (ClassTag)scala.Predef..MODULE$.implicitly(scala.reflect.ClassTag..MODULE$.AnyRef());
      scala.Function2 scalaTransformFunc = (rdds, time) -> {
         List jrdds = scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava((scala.collection.Seq)rdds.map((x$8) -> org.apache.spark.api.java.JavaRDD..MODULE$.fromRDD(x$8, scala.reflect.ClassTag..MODULE$.apply(Object.class)))).asJava();
         return ((JavaPairRDD)transformFunc.call(jrdds, time)).rdd();
      };
      return JavaPairDStream$.MODULE$.fromPairDStream(this.ssc().transform(((IterableOnceOps)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(dstreams).asScala().map((x$9) -> x$9.dstream())).toSeq(), scalaTransformFunc, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), cmk, cmv);
   }

   public void checkpoint(final String directory) {
      this.ssc().checkpoint(directory);
   }

   public void remember(final Duration duration) {
      this.ssc().remember(duration);
   }

   public void addStreamingListener(final StreamingListener streamingListener) {
      this.ssc().addStreamingListener(streamingListener);
   }

   public StreamingContextState getState() {
      return this.ssc().getState();
   }

   public void start() {
      this.ssc().start();
   }

   public void awaitTermination() throws InterruptedException {
      this.ssc().awaitTermination();
   }

   public boolean awaitTerminationOrTimeout(final long timeout) throws InterruptedException {
      return this.ssc().awaitTerminationOrTimeout(timeout);
   }

   public void stop() {
      this.ssc().stop(this.ssc().stop$default$1());
   }

   public void stop(final boolean stopSparkContext) {
      this.ssc().stop(stopSparkContext);
   }

   public void stop(final boolean stopSparkContext, final boolean stopGracefully) {
      this.ssc().stop(stopSparkContext, stopGracefully);
   }

   public void close() {
      this.stop();
   }

   private static final Function1 fn$1(final Function converter$1) {
      return (x) -> scala.jdk.CollectionConverters..MODULE$.IteratorHasAsScala(((Iterable)converter$1.call(x)).iterator()).asScala();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$fileStream$1(final Function filter$1, final Path x) {
      return (Boolean)filter$1.call(x);
   }

   private static final Function1 fn$2(final Function filter$1) {
      return (x) -> BoxesRunTime.boxToBoolean($anonfun$fileStream$1(filter$1, x));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$fileStream$2(final Function filter$2, final Path x) {
      return (Boolean)filter$2.call(x);
   }

   private static final Function1 fn$3(final Function filter$2) {
      return (x) -> BoxesRunTime.boxToBoolean($anonfun$fileStream$2(filter$2, x));
   }

   public JavaStreamingContext(final StreamingContext ssc) {
      this.ssc = ssc;
      this.sparkContext = new JavaSparkContext(ssc.sc());
   }

   public JavaStreamingContext(final String master, final String appName, final Duration batchDuration) {
      this(new StreamingContext(master, appName, batchDuration, (String)null, scala.collection.immutable.Nil..MODULE$, (Map)scala.Predef..MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$)));
   }

   public JavaStreamingContext(final String master, final String appName, final Duration batchDuration, final String sparkHome, final String jarFile) {
      this(new StreamingContext(master, appName, batchDuration, sparkHome, new scala.collection.immutable..colon.colon(jarFile, scala.collection.immutable.Nil..MODULE$), (Map)scala.Predef..MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$)));
   }

   public JavaStreamingContext(final String master, final String appName, final Duration batchDuration, final String sparkHome, final String[] jars) {
      this(new StreamingContext(master, appName, batchDuration, sparkHome, scala.Predef..MODULE$.copyArrayToImmutableIndexedSeq(jars), (Map)scala.Predef..MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$)));
   }

   public JavaStreamingContext(final String master, final String appName, final Duration batchDuration, final String sparkHome, final String[] jars, final java.util.Map environment) {
      this(new StreamingContext(master, appName, batchDuration, sparkHome, scala.Predef..MODULE$.copyArrayToImmutableIndexedSeq(jars), scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(environment).asScala()));
   }

   public JavaStreamingContext(final JavaSparkContext sparkContext, final Duration batchDuration) {
      this(new StreamingContext(sparkContext.sc(), batchDuration));
   }

   public JavaStreamingContext(final SparkConf conf, final Duration batchDuration) {
      this(new StreamingContext(conf, batchDuration));
   }

   public JavaStreamingContext(final String path) {
      this(new StreamingContext(path, org.apache.spark.deploy.SparkHadoopUtil..MODULE$.get().conf()));
   }

   public JavaStreamingContext(final String path, final Configuration hadoopConf) {
      this(new StreamingContext(path, hadoopConf));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
