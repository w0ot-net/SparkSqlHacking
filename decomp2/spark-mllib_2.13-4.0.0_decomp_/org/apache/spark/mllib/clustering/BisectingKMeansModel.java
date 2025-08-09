package org.apache.spark.mllib.clustering;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.util.Loader$;
import org.apache.spark.mllib.util.Saveable;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructType;
import org.json4s.DefaultFormats;
import org.json4s.Formats;
import org.json4s.JValue;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Predef;
import scala.Product;
import scala.Some;
import scala.StringContext;
import scala.Tuple1;
import scala.Tuple2;
import scala.Tuple3;
import scala.Tuple7;
import scala.collection.Iterator;
import scala.collection.ArrayOps.;
import scala.collection.immutable.ArraySeq;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\rmg\u0001\u00020`\u0001)D1\"a\u0005\u0001\u0005\u000b\u0007I\u0011A0\u0002\u0016!Q\u0011q\u0004\u0001\u0003\u0002\u0003\u0006I!a\u0006\t\u0015\u0005\u0005\u0002A!b\u0001\n\u0003\t\u0019\u0003\u0003\u0006\u0002H\u0001\u0011\t\u0011)A\u0005\u0003KA!\"a\u0013\u0001\u0005\u000b\u0007I\u0011AA'\u0011)\tY\u0006\u0001B\u0001B\u0003%\u0011q\n\u0005\t\u0003?\u0002A\u0011A0\u0002b!9\u0011q\f\u0001\u0005\u0002\u0005=\u0004\"CA=\u0001\t\u0007I\u0011BA>\u0011!\t\u0019\t\u0001Q\u0001\n\u0005u\u0004bBAC\u0001\u0011\u0005\u0011q\u0011\u0005\u000b\u0003;\u0003\u0001R1A\u0005\u0002\u0005}\u0005bBAT\u0001\u0011\u0005\u0011\u0011\u0016\u0005\b\u0003O\u0003A\u0011AAY\u0011\u001d\t9\u000b\u0001C\u0001\u0003\u000fDq!!<\u0001\t\u0003\ty\u000fC\u0004\u0002n\u0002!\t!!>\t\u000f\u00055\b\u0001\"\u0001\u0002~\"9!1\u0001\u0001\u0005B\t\u0015qa\u0002B\u0013?\"\u0005!q\u0005\u0004\u0007=~C\tA!\u000b\t\u000f\u0005}S\u0003\"\u0001\u0003<!9!QH\u000b\u0005B\t}bA\u0002B$+\u0011\u0013I\u0005\u0003\u0006\u0003Ra\u0011)\u001a!C\u0001\u0003?C!Ba\u0015\u0019\u0005#\u0005\u000b\u0011BAQ\u0011)\u0011)\u0006\u0007BK\u0002\u0013\u0005!q\u000b\u0005\u000b\u0005?B\"\u0011#Q\u0001\n\te\u0003B\u0003B11\tU\r\u0011\"\u0001\u0003d!Q!Q\r\r\u0003\u0012\u0003\u0006I!a$\t\u0015\t\u001d\u0004D!f\u0001\n\u0003\ti\u0005\u0003\u0006\u0003ja\u0011\t\u0012)A\u0005\u0003\u001fB!Ba\u001b\u0019\u0005+\u0007I\u0011AA'\u0011)\u0011i\u0007\u0007B\tB\u0003%\u0011q\n\u0005\u000b\u0005_B\"Q3A\u0005\u0002\u00055\u0003B\u0003B91\tE\t\u0015!\u0003\u0002P!Q!1\u000f\r\u0003\u0016\u0004%\tA!\u001e\t\u0015\tu\u0004D!E!\u0002\u0013\u00119\bC\u0004\u0002`a!\tAa \t\u0013\tM\u0005$!A\u0005\u0002\tU\u0005\"\u0003BS1E\u0005I\u0011\u0001BT\u0011%\u0011Y\fGI\u0001\n\u0003\u0011i\fC\u0005\u0003Bb\t\n\u0011\"\u0001\u0003D\"I!q\u0019\r\u0012\u0002\u0013\u0005!\u0011\u001a\u0005\n\u0005\u001bD\u0012\u0013!C\u0001\u0005\u0013D\u0011Ba4\u0019#\u0003%\tA!3\t\u0013\tE\u0007$%A\u0005\u0002\tM\u0007\"\u0003Bl1\u0005\u0005I\u0011\tBm\u0011%\u0011y\u000eGA\u0001\n\u0003\ty\nC\u0005\u0003bb\t\t\u0011\"\u0001\u0003d\"I!q\u001e\r\u0002\u0002\u0013\u0005#\u0011\u001f\u0005\n\u0005\u007fD\u0012\u0011!C\u0001\u0007\u0003A\u0011ba\u0003\u0019\u0003\u0003%\te!\u0004\t\u0013\rE\u0001$!A\u0005B\rM\u0001\"CB\u000b1\u0005\u0005I\u0011IB\f\u0011%\u0019I\u0002GA\u0001\n\u0003\u001aYbB\u0004\u0004 UAIa!\t\u0007\u000f\t\u001dS\u0003#\u0003\u0004$!9\u0011q\f\u001e\u0005\u0002\r\u0015\u0002bBB\u0014u\u0011\u00051\u0011\u0006\u0005\n\u0007OQ\u0014\u0011!CA\u0007wA\u0011ba\u0013;\u0003\u0003%\ti!\u0014\t\u0013\r}#(!A\u0005\n\r\u0005\u0004bBB5+\u0011%11\u000e\u0005\b\u0007g*B\u0011BB;\u000f!\u0019))\u0006E\u0001?\u000e\u001de\u0001CBE+!\u0005qla#\t\u000f\u0005}3\t\"\u0001\u0004\u000e\"Q1qR\"C\u0002\u0013\u0005qL!7\t\u0011\rE5\t)A\u0005\u00057D!ba%D\u0005\u0004%\ta\u0018Bm\u0011!\u0019)j\u0011Q\u0001\n\tm\u0007b\u0002B\u0002\u0007\u0012\u00051q\u0013\u0005\b\u0005{\u0019E\u0011ABQ\u000f!\u00199+\u0006E\u0001?\u000e%f\u0001CBV+!\u0005ql!,\t\u000f\u0005}C\n\"\u0001\u00040\"Q1q\u0012'C\u0002\u0013\u0005qL!7\t\u0011\rEE\n)A\u0005\u00057D!ba%M\u0005\u0004%\ta\u0018Bm\u0011!\u0019)\n\u0014Q\u0001\n\tm\u0007b\u0002B\u0002\u0019\u0012\u00051\u0011\u0017\u0005\b\u0005{aE\u0011AB]\u000f!\u0019y,\u0006E\u0001?\u000e\u0005g\u0001CBb+!\u0005ql!2\t\u000f\u0005}S\u000b\"\u0001\u0004H\"Q1qR+C\u0002\u0013\u0005qL!7\t\u0011\rEU\u000b)A\u0005\u00057D!ba%V\u0005\u0004%\ta\u0018Bm\u0011!\u0019)*\u0016Q\u0001\n\tm\u0007b\u0002B\u0002+\u0012\u00051\u0011\u001a\u0005\b\u0005{)F\u0011ABi\u0011%\u0019y&FA\u0001\n\u0013\u0019\tG\u0001\u000bCSN,7\r^5oO.kU-\u00198t\u001b>$W\r\u001c\u0006\u0003A\u0006\f!b\u00197vgR,'/\u001b8h\u0015\t\u00117-A\u0003nY2L'M\u0003\u0002eK\u0006)1\u000f]1sW*\u0011amZ\u0001\u0007CB\f7\r[3\u000b\u0003!\f1a\u001c:h\u0007\u0001\u0019b\u0001A6r{\u0006\u001d\u0001C\u00017p\u001b\u0005i'\"\u00018\u0002\u000bM\u001c\u0017\r\\1\n\u0005Al'AB!osJ+g\r\u0005\u0002su:\u00111\u000f\u001f\b\u0003i^l\u0011!\u001e\u0006\u0003m&\fa\u0001\u0010:p_Rt\u0014\"\u00018\n\u0005el\u0017a\u00029bG.\fw-Z\u0005\u0003wr\u0014AbU3sS\u0006d\u0017N_1cY\u0016T!!_7\u0011\u0007y\f\u0019!D\u0001\u0000\u0015\r\t\t!Y\u0001\u0005kRLG.C\u0002\u0002\u0006}\u0014\u0001bU1wK\u0006\u0014G.\u001a\t\u0005\u0003\u0013\ty!\u0004\u0002\u0002\f)\u0019\u0011QB2\u0002\u0011%tG/\u001a:oC2LA!!\u0005\u0002\f\t9Aj\\4hS:<\u0017\u0001\u0002:p_R,\"!a\u0006\u0011\t\u0005e\u00111D\u0007\u0002?&\u0019\u0011QD0\u0003%\rcWo\u001d;fe&tw\r\u0016:fK:{G-Z\u0001\u0006e>|G\u000fI\u0001\u0010I&\u001cH/\u00198dK6+\u0017m];sKV\u0011\u0011Q\u0005\t\u0005\u0003O\tyC\u0004\u0003\u0002*\u0005-\u0002C\u0001;n\u0013\r\ti#\\\u0001\u0007!J,G-\u001a4\n\t\u0005E\u00121\u0007\u0002\u0007'R\u0014\u0018N\\4\u000b\u0007\u00055R\u000eK\u0003\u0004\u0003o\t\u0019\u0005\u0005\u0003\u0002:\u0005}RBAA\u001e\u0015\r\tidY\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA!\u0003w\u0011QaU5oG\u0016\f#!!\u0012\u0002\u000bIrCG\f\u0019\u0002!\u0011L7\u000f^1oG\u0016lU-Y:ve\u0016\u0004\u0003&\u0002\u0003\u00028\u0005\r\u0013\u0001\u0004;sC&t\u0017N\\4D_N$XCAA(!\ra\u0017\u0011K\u0005\u0004\u0003'j'A\u0002#pk\ndW\rK\u0003\u0006\u0003o\t9&\t\u0002\u0002Z\u0005)1G\f\u0019/a\u0005iAO]1j]&twmQ8ti\u0002BSABA\u001c\u0003/\na\u0001P5oSRtD\u0003CA2\u0003K\n9'a\u001b\u0011\u0007\u0005e\u0001\u0001C\u0004\u0002\u0014\u001d\u0001\r!a\u0006\t\u000f\u0005\u0005r\u00011\u0001\u0002&!2\u0011qMA\u001c\u0003\u0007Bq!a\u0013\b\u0001\u0004\ty\u0005\u000b\u0004\u0002l\u0005]\u0012q\u000b\u000b\u0005\u0003G\n\t\bC\u0004\u0002\u0014!\u0001\r!a\u0006)\u000b!\t9$!\u001e\"\u0005\u0005]\u0014!B\u0019/m9\u0002\u0014a\u00063jgR\fgnY3NK\u0006\u001cXO]3J]N$\u0018M\\2f+\t\ti\b\u0005\u0003\u0002\u001a\u0005}\u0014bAAA?\nyA)[:uC:\u001cW-T3bgV\u0014X-\u0001\reSN$\u0018M\\2f\u001b\u0016\f7/\u001e:f\u0013:\u001cH/\u00198dK\u0002\nab\u00197vgR,'oQ3oi\u0016\u00148/\u0006\u0002\u0002\nB)A.a#\u0002\u0010&\u0019\u0011QR7\u0003\u000b\u0005\u0013(/Y=\u0011\t\u0005E\u0015qS\u0007\u0003\u0003'S1!!&b\u0003\u0019a\u0017N\\1mO&!\u0011\u0011TAJ\u0005\u00191Vm\u0019;pe\"*1\"a\u000e\u0002v\u0005\t1.\u0006\u0002\u0002\"B\u0019A.a)\n\u0007\u0005\u0015VNA\u0002J]R\fq\u0001\u001d:fI&\u001cG\u000f\u0006\u0003\u0002\"\u0006-\u0006bBAW\u001b\u0001\u0007\u0011qR\u0001\u0006a>Lg\u000e\u001e\u0015\u0006\u001b\u0005]\u0012Q\u000f\u000b\u0005\u0003g\u000by\f\u0005\u0004\u00026\u0006m\u0016\u0011U\u0007\u0003\u0003oS1!!/d\u0003\r\u0011H\rZ\u0005\u0005\u0003{\u000b9LA\u0002S\t\u0012Cq!!1\u000f\u0001\u0004\t\u0019-\u0001\u0004q_&tGo\u001d\t\u0007\u0003k\u000bY,a$)\u000b9\t9$!\u001e\u0015\t\u0005%\u0017q\u001d\t\u0007\u0003\u0017\f).!7\u000e\u0005\u00055'\u0002BAh\u0003#\fAA[1wC*\u0019\u00111[2\u0002\u0007\u0005\u0004\u0018.\u0003\u0003\u0002X\u00065'a\u0002&bm\u0006\u0014F\t\u0012\t\u0005\u00037\f\u0019/\u0004\u0002\u0002^*!\u0011q\\Aq\u0003\u0011a\u0017M\\4\u000b\u0005\u0005=\u0017\u0002BAs\u0003;\u0014q!\u00138uK\u001e,'\u000fC\u0004\u0002B>\u0001\r!!;\u0011\r\u0005-\u0017Q[AHQ\u0015y\u0011qGA;\u0003-\u0019w.\u001c9vi\u0016\u001cun\u001d;\u0015\t\u0005=\u0013\u0011\u001f\u0005\b\u0003[\u0003\u0002\u0019AAHQ\u0015\u0001\u0012qGA;)\u0011\ty%a>\t\u000f\u0005e\u0018\u00031\u0001\u0002D\u0006!A-\u0019;bQ\u0015\t\u0012qGA;)\u0011\ty%a@\t\u000f\u0005e(\u00031\u0001\u0002j\"*!#a\u000e\u0002v\u0005!1/\u0019<f)\u0019\u00119A!\u0004\u0003\u001aA\u0019AN!\u0003\n\u0007\t-QN\u0001\u0003V]&$\bb\u0002B\b'\u0001\u0007!\u0011C\u0001\u0003g\u000e\u0004BAa\u0005\u0003\u00165\t1-C\u0002\u0003\u0018\r\u0014Ab\u00159be.\u001cuN\u001c;fqRDqAa\u0007\u0014\u0001\u0004\t)#\u0001\u0003qCRD\u0007&B\n\u00028\t}\u0011E\u0001B\u0011\u0003\u0015\u0011d\u0006\r\u00181Q\u0015\u0001\u0011qGA;\u0003Q\u0011\u0015n]3di&twmS'fC:\u001cXj\u001c3fYB\u0019\u0011\u0011D\u000b\u0014\rUY'1\u0006B\u0019!\u0015q(QFA2\u0013\r\u0011yc \u0002\u0007\u0019>\fG-\u001a:\u0011\t\tM\"\u0011H\u0007\u0003\u0005kQAAa\u000e\u0002b\u0006\u0011\u0011n\\\u0005\u0004w\nUBC\u0001B\u0014\u0003\u0011aw.\u00193\u0015\r\u0005\r$\u0011\tB\"\u0011\u001d\u0011ya\u0006a\u0001\u0005#AqAa\u0007\u0018\u0001\u0004\t)\u0003K\u0003\u0018\u0003o\u0011yB\u0001\u0003ECR\f7#\u0002\rl\u0005\u0017\n\bc\u00017\u0003N%\u0019!qJ7\u0003\u000fA\u0013x\u000eZ;di\u0006)\u0011N\u001c3fq\u00061\u0011N\u001c3fq\u0002\nAa]5{KV\u0011!\u0011\f\t\u0004Y\nm\u0013b\u0001B/[\n!Aj\u001c8h\u0003\u0015\u0019\u0018N_3!\u0003\u0019\u0019WM\u001c;feV\u0011\u0011qR\u0001\bG\u0016tG/\u001a:!\u0003\u0011qwN]7\u0002\u000b9|'/\u001c\u0011\u0002\t\r|7\u000f^\u0001\u0006G>\u001cH\u000fI\u0001\u0007Q\u0016Lw\r\u001b;\u0002\u000f!,\u0017n\u001a5uA\u0005A1\r[5mIJ,g.\u0006\u0002\u0003xA)!O!\u001f\u0002\"&\u0019!1\u0010?\u0003\u0007M+\u0017/A\u0005dQ&dGM]3oAQ\u0001\"\u0011\u0011BC\u0005\u000f\u0013IIa#\u0003\u000e\n=%\u0011\u0013\t\u0004\u0005\u0007CR\"A\u000b\t\u000f\tEs\u00051\u0001\u0002\"\"9!QK\u0014A\u0002\te\u0003b\u0002B1O\u0001\u0007\u0011q\u0012\u0005\b\u0005O:\u0003\u0019AA(\u0011\u001d\u0011Yg\na\u0001\u0003\u001fBqAa\u001c(\u0001\u0004\ty\u0005C\u0004\u0003t\u001d\u0002\rAa\u001e\u0002\t\r|\u0007/\u001f\u000b\u0011\u0005\u0003\u00139J!'\u0003\u001c\nu%q\u0014BQ\u0005GC\u0011B!\u0015)!\u0003\u0005\r!!)\t\u0013\tU\u0003\u0006%AA\u0002\te\u0003\"\u0003B1QA\u0005\t\u0019AAH\u0011%\u00119\u0007\u000bI\u0001\u0002\u0004\ty\u0005C\u0005\u0003l!\u0002\n\u00111\u0001\u0002P!I!q\u000e\u0015\u0011\u0002\u0003\u0007\u0011q\n\u0005\n\u0005gB\u0003\u0013!a\u0001\u0005o\nabY8qs\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0003**\"\u0011\u0011\u0015BVW\t\u0011i\u000b\u0005\u0003\u00030\n]VB\u0001BY\u0015\u0011\u0011\u0019L!.\u0002\u0013Ut7\r[3dW\u0016$'bAA\u001f[&!!\u0011\u0018BY\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\t\u0011yL\u000b\u0003\u0003Z\t-\u0016AD2paf$C-\u001a4bk2$HeM\u000b\u0003\u0005\u000bTC!a$\u0003,\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\"TC\u0001BfU\u0011\tyEa+\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%k\u0005q1m\u001c9zI\u0011,g-Y;mi\u00122\u0014AD2paf$C-\u001a4bk2$HeN\u000b\u0003\u0005+TCAa\u001e\u0003,\u0006i\u0001O]8ek\u000e$\bK]3gSb,\"Aa7\u0011\t\u0005m'Q\\\u0005\u0005\u0003c\ti.\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\t\u0015(1\u001e\t\u0004Y\n\u001d\u0018b\u0001Bu[\n\u0019\u0011I\\=\t\u0013\t5('!AA\u0002\u0005\u0005\u0016a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0003tB1!Q\u001fB~\u0005Kl!Aa>\u000b\u0007\teX.\u0001\u0006d_2dWm\u0019;j_:LAA!@\u0003x\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\u0019\u0019a!\u0003\u0011\u00071\u001c)!C\u0002\u0004\b5\u0014qAQ8pY\u0016\fg\u000eC\u0005\u0003nR\n\t\u00111\u0001\u0003f\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\u0011Yna\u0004\t\u0013\t5X'!AA\u0002\u0005\u0005\u0016\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\u0005\u0005\u0016\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\tm\u0017AB3rk\u0006d7\u000f\u0006\u0003\u0004\u0004\ru\u0001\"\u0003Bwq\u0005\u0005\t\u0019\u0001Bs\u0003\u0011!\u0015\r^1\u0011\u0007\t\r%h\u0005\u0003;W\nEBCAB\u0011\u0003\u0015\t\u0007\u000f\u001d7z)\u0011\u0011\tia\u000b\t\u000f\r5B\b1\u0001\u00040\u0005\t!\u000f\u0005\u0003\u00042\r]RBAB\u001a\u0015\r\u0019)dY\u0001\u0004gFd\u0017\u0002BB\u001d\u0007g\u00111AU8x)A\u0011\ti!\u0010\u0004@\r\u000531IB#\u0007\u000f\u001aI\u0005C\u0004\u0003Ru\u0002\r!!)\t\u000f\tUS\b1\u0001\u0003Z!9!\u0011M\u001fA\u0002\u0005=\u0005b\u0002B4{\u0001\u0007\u0011q\n\u0005\b\u0005Wj\u0004\u0019AA(\u0011\u001d\u0011y'\u0010a\u0001\u0003\u001fBqAa\u001d>\u0001\u0004\u00119(A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\r=31\f\t\u0006Y\u000eE3QK\u0005\u0004\u0007'j'AB(qi&|g\u000eE\tm\u0007/\n\tK!\u0017\u0002\u0010\u0006=\u0013qJA(\u0005oJ1a!\u0017n\u0005\u0019!V\u000f\u001d7fo!I1Q\f \u0002\u0002\u0003\u0007!\u0011Q\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAB2!\u0011\tYn!\u001a\n\t\r\u001d\u0014Q\u001c\u0002\u0007\u001f\nTWm\u0019;\u0002\u0011\u001d,GOT8eKN$Ba!\u001c\u0004pA)A.a#\u0002\u0018!91\u0011\u000f!A\u0002\u0005]\u0011\u0001\u00028pI\u0016\f\u0011BY;jY\u0012$&/Z3\u0015\r\u0005]1qOB>\u0011\u001d\u0019I(\u0011a\u0001\u0003C\u000baA]8pi&#\u0007bBB?\u0003\u0002\u00071qP\u0001\u0006]>$Wm\u001d\t\t\u0003O\u0019\t)!)\u0003\u0002&!11QA\u001a\u0005\ri\u0015\r]\u0001\r'\u00064X\rT8bIZ\u000bt\f\r\t\u0004\u0005\u0007\u001b%\u0001D*bm\u0016du.\u00193Wc}\u00034CA\"l)\t\u00199)A\tuQ&\u001chi\u001c:nCR4VM]:j_:\f!\u0003\u001e5jg\u001a{'/\\1u-\u0016\u00148/[8oA\u0005iA\u000f[5t\u00072\f7o\u001d(b[\u0016\fa\u0002\u001e5jg\u000ec\u0017m]:OC6,\u0007\u0005\u0006\u0005\u0003\b\re51TBP\u0011\u001d\u0011y!\u0013a\u0001\u0005#Aqa!(J\u0001\u0004\t\u0019'A\u0003n_\u0012,G\u000eC\u0004\u0003\u001c%\u0003\r!!\n\u0015\r\u0005\r41UBS\u0011\u001d\u0011yA\u0013a\u0001\u0005#AqAa\u0007K\u0001\u0004\t)#\u0001\u0007TCZ,Gj\\1e-Jz\u0006\u0007E\u0002\u0003\u00042\u0013AbU1wK2{\u0017\r\u001a,3?B\u001a\"\u0001T6\u0015\u0005\r%F\u0003\u0003B\u0004\u0007g\u001b)la.\t\u000f\t=!\u000b1\u0001\u0003\u0012!91Q\u0014*A\u0002\u0005\r\u0004b\u0002B\u000e%\u0002\u0007\u0011Q\u0005\u000b\u0007\u0003G\u001aYl!0\t\u000f\t=1\u000b1\u0001\u0003\u0012!9!1D*A\u0002\u0005\u0015\u0012\u0001D*bm\u0016du.\u00193Wg}\u0003\u0004c\u0001BB+\na1+\u0019<f\u0019>\fGMV\u001a`aM\u0011Qk\u001b\u000b\u0003\u0007\u0003$\u0002Ba\u0002\u0004L\u000e57q\u001a\u0005\b\u0005\u001fY\u0006\u0019\u0001B\t\u0011\u001d\u0019ij\u0017a\u0001\u0003GBqAa\u0007\\\u0001\u0004\t)\u0003\u0006\u0004\u0002d\rM7Q\u001b\u0005\b\u0005\u001fa\u0006\u0019\u0001B\t\u0011\u001d\u0011Y\u0002\u0018a\u0001\u0003KAS!FA\u001c\u0005?AS\u0001FA\u001c\u0005?\u0001"
)
public class BisectingKMeansModel implements Serializable, Saveable, Logging {
   private int k;
   private final ClusteringTreeNode root;
   private final String distanceMeasure;
   private final double trainingCost;
   private final DistanceMeasure distanceMeasureInstance;
   private transient Logger org$apache$spark$internal$Logging$$log_;
   private volatile boolean bitmap$0;

   public static BisectingKMeansModel load(final SparkContext sc, final String path) {
      return BisectingKMeansModel$.MODULE$.load(sc, path);
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

   public ClusteringTreeNode root() {
      return this.root;
   }

   public String distanceMeasure() {
      return this.distanceMeasure;
   }

   public double trainingCost() {
      return this.trainingCost;
   }

   private DistanceMeasure distanceMeasureInstance() {
      return this.distanceMeasureInstance;
   }

   public Vector[] clusterCenters() {
      return (Vector[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(this.root().leafNodes()), (x$1) -> x$1.center(), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
   }

   private int k$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.k = this.clusterCenters().length;
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.k;
   }

   public int k() {
      return !this.bitmap$0 ? this.k$lzycompute() : this.k;
   }

   public int predict(final Vector point) {
      return this.root().predict(point, this.distanceMeasureInstance());
   }

   public RDD predict(final RDD points) {
      return points.map((p) -> BoxesRunTime.boxToInteger($anonfun$predict$1(this, p)), scala.reflect.ClassTag..MODULE$.Int());
   }

   public JavaRDD predict(final JavaRDD points) {
      return this.predict(points.rdd()).toJavaRDD();
   }

   public double computeCost(final Vector point) {
      return this.root().computeCost(point, this.distanceMeasureInstance());
   }

   public double computeCost(final RDD data) {
      return org.apache.spark.rdd.RDD..MODULE$.doubleRDDToDoubleRDDFunctions(data.map((x$2) -> BoxesRunTime.boxToDouble($anonfun$computeCost$1(this, x$2)), scala.reflect.ClassTag..MODULE$.Double())).sum();
   }

   public double computeCost(final JavaRDD data) {
      return this.computeCost(data.rdd());
   }

   public void save(final SparkContext sc, final String path) {
      BisectingKMeansModel.SaveLoadV3_0$.MODULE$.save(sc, this, path);
   }

   // $FF: synthetic method
   public static final int $anonfun$predict$1(final BisectingKMeansModel $this, final Vector p) {
      return $this.root().predict(p, $this.distanceMeasureInstance());
   }

   // $FF: synthetic method
   public static final double $anonfun$computeCost$1(final BisectingKMeansModel $this, final Vector x$2) {
      return $this.root().computeCost(x$2, $this.distanceMeasureInstance());
   }

   public BisectingKMeansModel(final ClusteringTreeNode root, final String distanceMeasure, final double trainingCost) {
      this.root = root;
      this.distanceMeasure = distanceMeasure;
      this.trainingCost = trainingCost;
      Logging.$init$(this);
      this.distanceMeasureInstance = DistanceMeasure$.MODULE$.decodeFromString(distanceMeasure);
   }

   public BisectingKMeansModel(final ClusteringTreeNode root) {
      this(root, DistanceMeasure$.MODULE$.EUCLIDEAN(), (double)0.0F);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private static class Data implements Product, Serializable {
      private final int index;
      private final long size;
      private final Vector center;
      private final double norm;
      private final double cost;
      private final double height;
      private final Seq children;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public int index() {
         return this.index;
      }

      public long size() {
         return this.size;
      }

      public Vector center() {
         return this.center;
      }

      public double norm() {
         return this.norm;
      }

      public double cost() {
         return this.cost;
      }

      public double height() {
         return this.height;
      }

      public Seq children() {
         return this.children;
      }

      public Data copy(final int index, final long size, final Vector center, final double norm, final double cost, final double height, final Seq children) {
         return new Data(index, size, center, norm, cost, height, children);
      }

      public int copy$default$1() {
         return this.index();
      }

      public long copy$default$2() {
         return this.size();
      }

      public Vector copy$default$3() {
         return this.center();
      }

      public double copy$default$4() {
         return this.norm();
      }

      public double copy$default$5() {
         return this.cost();
      }

      public double copy$default$6() {
         return this.height();
      }

      public Seq copy$default$7() {
         return this.children();
      }

      public String productPrefix() {
         return "Data";
      }

      public int productArity() {
         return 7;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return BoxesRunTime.boxToInteger(this.index());
            }
            case 1 -> {
               return BoxesRunTime.boxToLong(this.size());
            }
            case 2 -> {
               return this.center();
            }
            case 3 -> {
               return BoxesRunTime.boxToDouble(this.norm());
            }
            case 4 -> {
               return BoxesRunTime.boxToDouble(this.cost());
            }
            case 5 -> {
               return BoxesRunTime.boxToDouble(this.height());
            }
            case 6 -> {
               return this.children();
            }
            default -> {
               return Statics.ioobe(x$1);
            }
         }
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Data;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "index";
            }
            case 1 -> {
               return "size";
            }
            case 2 -> {
               return "center";
            }
            case 3 -> {
               return "norm";
            }
            case 4 -> {
               return "cost";
            }
            case 5 -> {
               return "height";
            }
            case 6 -> {
               return "children";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, this.index());
         var1 = Statics.mix(var1, Statics.longHash(this.size()));
         var1 = Statics.mix(var1, Statics.anyHash(this.center()));
         var1 = Statics.mix(var1, Statics.doubleHash(this.norm()));
         var1 = Statics.mix(var1, Statics.doubleHash(this.cost()));
         var1 = Statics.mix(var1, Statics.doubleHash(this.height()));
         var1 = Statics.mix(var1, Statics.anyHash(this.children()));
         return Statics.finalizeHash(var1, 7);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var8;
         if (this != x$1) {
            label75: {
               if (x$1 instanceof Data) {
                  Data var4 = (Data)x$1;
                  if (this.index() == var4.index() && this.size() == var4.size() && this.norm() == var4.norm() && this.cost() == var4.cost() && this.height() == var4.height()) {
                     label68: {
                        Vector var10000 = this.center();
                        Vector var5 = var4.center();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label68;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label68;
                        }

                        Seq var7 = this.children();
                        Seq var6 = var4.children();
                        if (var7 == null) {
                           if (var6 != null) {
                              break label68;
                           }
                        } else if (!var7.equals(var6)) {
                           break label68;
                        }

                        if (var4.canEqual(this)) {
                           break label75;
                        }
                     }
                  }
               }

               var8 = false;
               return var8;
            }
         }

         var8 = true;
         return var8;
      }

      public Data(final int index, final long size, final Vector center, final double norm, final double cost, final double height, final Seq children) {
         this.index = index;
         this.size = size;
         this.center = center;
         this.norm = norm;
         this.cost = cost;
         this.height = height;
         this.children = children;
         Product.$init$(this);
      }
   }

   private static class Data$ implements Serializable {
      public static final Data$ MODULE$ = new Data$();

      public Data apply(final Row r) {
         return new Data(r.getInt(0), r.getLong(1), (Vector)r.getAs(2), r.getDouble(3), r.getDouble(4), r.getDouble(5), r.getSeq(6));
      }

      public Data apply(final int index, final long size, final Vector center, final double norm, final double cost, final double height, final Seq children) {
         return new Data(index, size, center, norm, cost, height, children);
      }

      public Option unapply(final Data x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple7(BoxesRunTime.boxToInteger(x$0.index()), BoxesRunTime.boxToLong(x$0.size()), x$0.center(), BoxesRunTime.boxToDouble(x$0.norm()), BoxesRunTime.boxToDouble(x$0.cost()), BoxesRunTime.boxToDouble(x$0.height()), x$0.children())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Data$.class);
      }

      public Data$() {
      }
   }

   public static class SaveLoadV1_0$ {
      public static final SaveLoadV1_0$ MODULE$ = new SaveLoadV1_0$();
      private static final String thisFormatVersion = "1.0";
      private static final String thisClassName = "org.apache.spark.mllib.clustering.BisectingKMeansModel";

      public String thisFormatVersion() {
         return thisFormatVersion;
      }

      public String thisClassName() {
         return thisClassName;
      }

      public void save(final SparkContext sc, final BisectingKMeansModel model, final String path) {
         SparkSession spark = org.apache.spark.sql.SparkSession..MODULE$.builder().sparkContext(sc).getOrCreate();
         String metadata = org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), this.thisClassName()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("version"), this.thisFormatVersion()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("rootId"), BoxesRunTime.boxToInteger(model.root().index())), (x) -> $anonfun$save$4(BoxesRunTime.unboxToInt(x)))), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(new Tuple1(metadata), scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator1$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple1"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$));
            }

            public $typecreator1$1() {
            }
         }

         spark.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1())).write().text(Loader$.MODULE$.metadataPath(path));
         Data[] data = (Data[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(BisectingKMeansModel$.MODULE$.org$apache$spark$mllib$clustering$BisectingKMeansModel$$getNodes(model.root())), (node) -> new Data(node.index(), node.size(), node.centerWithNorm().vector(), node.centerWithNorm().norm(), node.cost(), node.height(), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(.MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(node.children()), (x$4) -> BoxesRunTime.boxToInteger($anonfun$save$6(x$4)), scala.reflect.ClassTag..MODULE$.Int())).toImmutableArraySeq()), scala.reflect.ClassTag..MODULE$.apply(Data.class));
         ArraySeq var11 = org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(data).toImmutableArraySeq();
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator2$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.mllib.clustering.BisectingKMeansModel.Data").asType().toTypeConstructor();
            }

            public $typecreator2$1() {
            }
         }

         spark.createDataFrame(var11, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1())).write().parquet(Loader$.MODULE$.dataPath(path));
      }

      public BisectingKMeansModel load(final SparkContext sc, final String path) {
         DefaultFormats formats = org.json4s.DefaultFormats..MODULE$;
         Tuple3 var6 = Loader$.MODULE$.loadMetadata(sc, path);
         if (var6 == null) {
            throw new MatchError(var6);
         } else {
            String formatVersion;
            JValue metadata;
            Predef var10000;
            boolean var10001;
            label36: {
               label35: {
                  String className = (String)var6._1();
                  String formatVersion = (String)var6._2();
                  JValue metadata = (JValue)var6._3();
                  Tuple3 var5 = new Tuple3(className, formatVersion, metadata);
                  String className = (String)var5._1();
                  formatVersion = (String)var5._2();
                  metadata = (JValue)var5._3();
                  var10000 = scala.Predef..MODULE$;
                  String var13 = this.thisClassName();
                  if (className == null) {
                     if (var13 == null) {
                        break label35;
                     }
                  } else if (className.equals(var13)) {
                     break label35;
                  }

                  var10001 = false;
                  break label36;
               }

               var10001 = true;
            }

            label28: {
               label27: {
                  var10000.assert(var10001);
                  var10000 = scala.Predef..MODULE$;
                  String var14 = this.thisFormatVersion();
                  if (formatVersion == null) {
                     if (var14 == null) {
                        break label27;
                     }
                  } else if (formatVersion.equals(var14)) {
                     break label27;
                  }

                  var10001 = false;
                  break label28;
               }

               var10001 = true;
            }

            var10000.assert(var10001);
            int rootId = BoxesRunTime.unboxToInt(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata), "rootId")), formats, scala.reflect.ManifestFactory..MODULE$.Int()));
            SparkSession spark = org.apache.spark.sql.SparkSession..MODULE$.builder().sparkContext(sc).getOrCreate();
            Dataset rows = spark.read().parquet(Loader$.MODULE$.dataPath(path));
            Loader$ var26 = Loader$.MODULE$;
            StructType var28 = rows.schema();
            JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
            JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

            final class $typecreator1$2 extends TypeCreator {
               public Types.TypeApi apply(final Mirror $m$untyped) {
                  Universe $u = $m$untyped.universe();
                  return $m$untyped.staticClass("org.apache.spark.mllib.clustering.BisectingKMeansModel.Data").asType().toTypeConstructor();
               }

               public $typecreator1$2() {
               }
            }

            var26.checkSchema(var28, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$2()));
            Dataset data = rows.select("index", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"size", "center", "norm", "cost", "height", "children"})));
            scala.collection.immutable.Map nodes = scala.Predef..MODULE$.wrapRefArray(.MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(data.rdd().map((r) -> BisectingKMeansModel.Data$.MODULE$.apply(r), scala.reflect.ClassTag..MODULE$.apply(Data.class)).collect()), (d) -> new Tuple2(BoxesRunTime.boxToInteger(d.index()), d), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))).toMap(scala..less.colon.less..MODULE$.refl());
            ClusteringTreeNode rootNode = BisectingKMeansModel$.MODULE$.org$apache$spark$mllib$clustering$BisectingKMeansModel$$buildTree(rootId, nodes);
            double totalCost = BoxesRunTime.unboxToDouble(scala.Predef..MODULE$.wrapDoubleArray((double[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(rootNode.leafNodes()), (x$6) -> BoxesRunTime.boxToDouble($anonfun$load$3(x$6)), scala.reflect.ClassTag..MODULE$.Double())).sum(scala.math.Numeric.DoubleIsFractional..MODULE$));
            return new BisectingKMeansModel(rootNode, DistanceMeasure$.MODULE$.EUCLIDEAN(), totalCost);
         }
      }

      // $FF: synthetic method
      public static final JValue $anonfun$save$4(final int x) {
         return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
      }

      // $FF: synthetic method
      public static final int $anonfun$save$6(final ClusteringTreeNode x$4) {
         return x$4.index();
      }

      // $FF: synthetic method
      public static final double $anonfun$load$3(final ClusteringTreeNode x$6) {
         return x$6.cost();
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class SaveLoadV2_0$ {
      public static final SaveLoadV2_0$ MODULE$ = new SaveLoadV2_0$();
      private static final String thisFormatVersion = "2.0";
      private static final String thisClassName = "org.apache.spark.mllib.clustering.BisectingKMeansModel";

      public String thisFormatVersion() {
         return thisFormatVersion;
      }

      public String thisClassName() {
         return thisClassName;
      }

      public void save(final SparkContext sc, final BisectingKMeansModel model, final String path) {
         SparkSession spark = org.apache.spark.sql.SparkSession..MODULE$.builder().sparkContext(sc).getOrCreate();
         String metadata = org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), this.thisClassName()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("version"), this.thisFormatVersion()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("rootId"), BoxesRunTime.boxToInteger(model.root().index())), (x) -> $anonfun$save$10(BoxesRunTime.unboxToInt(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("distanceMeasure"), model.distanceMeasure()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x))), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(new Tuple1(metadata), scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator1$3 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple1"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$));
            }

            public $typecreator1$3() {
            }
         }

         spark.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$3())).write().text(Loader$.MODULE$.metadataPath(path));
         Data[] data = (Data[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(BisectingKMeansModel$.MODULE$.org$apache$spark$mllib$clustering$BisectingKMeansModel$$getNodes(model.root())), (node) -> new Data(node.index(), node.size(), node.centerWithNorm().vector(), node.centerWithNorm().norm(), node.cost(), node.height(), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(.MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(node.children()), (x$7) -> BoxesRunTime.boxToInteger($anonfun$save$13(x$7)), scala.reflect.ClassTag..MODULE$.Int())).toImmutableArraySeq()), scala.reflect.ClassTag..MODULE$.apply(Data.class));
         ArraySeq var11 = org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(data).toImmutableArraySeq();
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator2$2 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.mllib.clustering.BisectingKMeansModel.Data").asType().toTypeConstructor();
            }

            public $typecreator2$2() {
            }
         }

         spark.createDataFrame(var11, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$2())).write().parquet(Loader$.MODULE$.dataPath(path));
      }

      public BisectingKMeansModel load(final SparkContext sc, final String path) {
         Formats formats = org.json4s.DefaultFormats..MODULE$;
         Tuple3 var6 = Loader$.MODULE$.loadMetadata(sc, path);
         if (var6 == null) {
            throw new MatchError(var6);
         } else {
            String formatVersion;
            JValue metadata;
            Predef var10000;
            boolean var10001;
            label36: {
               label35: {
                  String className = (String)var6._1();
                  String formatVersion = (String)var6._2();
                  JValue metadata = (JValue)var6._3();
                  Tuple3 var5 = new Tuple3(className, formatVersion, metadata);
                  String className = (String)var5._1();
                  formatVersion = (String)var5._2();
                  metadata = (JValue)var5._3();
                  var10000 = scala.Predef..MODULE$;
                  String var13 = this.thisClassName();
                  if (className == null) {
                     if (var13 == null) {
                        break label35;
                     }
                  } else if (className.equals(var13)) {
                     break label35;
                  }

                  var10001 = false;
                  break label36;
               }

               var10001 = true;
            }

            label28: {
               label27: {
                  var10000.assert(var10001);
                  var10000 = scala.Predef..MODULE$;
                  String var14 = this.thisFormatVersion();
                  if (formatVersion == null) {
                     if (var14 == null) {
                        break label27;
                     }
                  } else if (formatVersion.equals(var14)) {
                     break label27;
                  }

                  var10001 = false;
                  break label28;
               }

               var10001 = true;
            }

            var10000.assert(var10001);
            int rootId = BoxesRunTime.unboxToInt(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata), "rootId")), formats, scala.reflect.ManifestFactory..MODULE$.Int()));
            String distanceMeasure = (String)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata), "distanceMeasure")), formats, scala.reflect.ManifestFactory..MODULE$.classType(String.class));
            SparkSession spark = org.apache.spark.sql.SparkSession..MODULE$.builder().sparkContext(sc).getOrCreate();
            Dataset rows = spark.read().parquet(Loader$.MODULE$.dataPath(path));
            Loader$ var27 = Loader$.MODULE$;
            StructType var29 = rows.schema();
            JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
            JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

            final class $typecreator1$4 extends TypeCreator {
               public Types.TypeApi apply(final Mirror $m$untyped) {
                  Universe $u = $m$untyped.universe();
                  return $m$untyped.staticClass("org.apache.spark.mllib.clustering.BisectingKMeansModel.Data").asType().toTypeConstructor();
               }

               public $typecreator1$4() {
               }
            }

            var27.checkSchema(var29, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$4()));
            Dataset data = rows.select("index", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"size", "center", "norm", "cost", "height", "children"})));
            scala.collection.immutable.Map nodes = scala.Predef..MODULE$.wrapRefArray(.MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(data.rdd().map((r) -> BisectingKMeansModel.Data$.MODULE$.apply(r), scala.reflect.ClassTag..MODULE$.apply(Data.class)).collect()), (d) -> new Tuple2(BoxesRunTime.boxToInteger(d.index()), d), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))).toMap(scala..less.colon.less..MODULE$.refl());
            ClusteringTreeNode rootNode = BisectingKMeansModel$.MODULE$.org$apache$spark$mllib$clustering$BisectingKMeansModel$$buildTree(rootId, nodes);
            double totalCost = BoxesRunTime.unboxToDouble(scala.Predef..MODULE$.wrapDoubleArray((double[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(rootNode.leafNodes()), (x$9) -> BoxesRunTime.boxToDouble($anonfun$load$6(x$9)), scala.reflect.ClassTag..MODULE$.Double())).sum(scala.math.Numeric.DoubleIsFractional..MODULE$));
            return new BisectingKMeansModel(rootNode, distanceMeasure, totalCost);
         }
      }

      // $FF: synthetic method
      public static final JValue $anonfun$save$10(final int x) {
         return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
      }

      // $FF: synthetic method
      public static final int $anonfun$save$13(final ClusteringTreeNode x$7) {
         return x$7.index();
      }

      // $FF: synthetic method
      public static final double $anonfun$load$6(final ClusteringTreeNode x$9) {
         return x$9.cost();
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class SaveLoadV3_0$ {
      public static final SaveLoadV3_0$ MODULE$ = new SaveLoadV3_0$();
      private static final String thisFormatVersion = "3.0";
      private static final String thisClassName = "org.apache.spark.mllib.clustering.BisectingKMeansModel";

      public String thisFormatVersion() {
         return thisFormatVersion;
      }

      public String thisClassName() {
         return thisClassName;
      }

      public void save(final SparkContext sc, final BisectingKMeansModel model, final String path) {
         SparkSession spark = org.apache.spark.sql.SparkSession..MODULE$.builder().sparkContext(sc).getOrCreate();
         String metadata = org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), this.thisClassName()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("version"), this.thisFormatVersion()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("rootId"), BoxesRunTime.boxToInteger(model.root().index())), (x) -> $anonfun$save$17(BoxesRunTime.unboxToInt(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("distanceMeasure"), model.distanceMeasure()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("trainingCost"), BoxesRunTime.boxToDouble(model.trainingCost())), (x) -> $anonfun$save$19(BoxesRunTime.unboxToDouble(x)))), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(new Tuple1(metadata), scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator1$5 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple1"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$));
            }

            public $typecreator1$5() {
            }
         }

         spark.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$5())).write().text(Loader$.MODULE$.metadataPath(path));
         Data[] data = (Data[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(BisectingKMeansModel$.MODULE$.org$apache$spark$mllib$clustering$BisectingKMeansModel$$getNodes(model.root())), (node) -> new Data(node.index(), node.size(), node.centerWithNorm().vector(), node.centerWithNorm().norm(), node.cost(), node.height(), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(.MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(node.children()), (x$10) -> BoxesRunTime.boxToInteger($anonfun$save$21(x$10)), scala.reflect.ClassTag..MODULE$.Int())).toImmutableArraySeq()), scala.reflect.ClassTag..MODULE$.apply(Data.class));
         ArraySeq var11 = org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(data).toImmutableArraySeq();
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator2$3 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.mllib.clustering.BisectingKMeansModel.Data").asType().toTypeConstructor();
            }

            public $typecreator2$3() {
            }
         }

         spark.createDataFrame(var11, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$3())).write().parquet(Loader$.MODULE$.dataPath(path));
      }

      public BisectingKMeansModel load(final SparkContext sc, final String path) {
         Formats formats = org.json4s.DefaultFormats..MODULE$;
         Tuple3 var6 = Loader$.MODULE$.loadMetadata(sc, path);
         if (var6 == null) {
            throw new MatchError(var6);
         } else {
            String formatVersion;
            JValue metadata;
            Predef var10000;
            boolean var10001;
            label36: {
               label35: {
                  String className = (String)var6._1();
                  String formatVersion = (String)var6._2();
                  JValue metadata = (JValue)var6._3();
                  Tuple3 var5 = new Tuple3(className, formatVersion, metadata);
                  String className = (String)var5._1();
                  formatVersion = (String)var5._2();
                  metadata = (JValue)var5._3();
                  var10000 = scala.Predef..MODULE$;
                  String var13 = this.thisClassName();
                  if (className == null) {
                     if (var13 == null) {
                        break label35;
                     }
                  } else if (className.equals(var13)) {
                     break label35;
                  }

                  var10001 = false;
                  break label36;
               }

               var10001 = true;
            }

            label28: {
               label27: {
                  var10000.assert(var10001);
                  var10000 = scala.Predef..MODULE$;
                  String var14 = this.thisFormatVersion();
                  if (formatVersion == null) {
                     if (var14 == null) {
                        break label27;
                     }
                  } else if (formatVersion.equals(var14)) {
                     break label27;
                  }

                  var10001 = false;
                  break label28;
               }

               var10001 = true;
            }

            var10000.assert(var10001);
            int rootId = BoxesRunTime.unboxToInt(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata), "rootId")), formats, scala.reflect.ManifestFactory..MODULE$.Int()));
            String distanceMeasure = (String)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata), "distanceMeasure")), formats, scala.reflect.ManifestFactory..MODULE$.classType(String.class));
            double trainingCost = BoxesRunTime.unboxToDouble(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata), "trainingCost")), formats, scala.reflect.ManifestFactory..MODULE$.Double()));
            SparkSession spark = org.apache.spark.sql.SparkSession..MODULE$.builder().sparkContext(sc).getOrCreate();
            Dataset rows = spark.read().parquet(Loader$.MODULE$.dataPath(path));
            Loader$ var27 = Loader$.MODULE$;
            StructType var29 = rows.schema();
            JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
            JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

            final class $typecreator1$6 extends TypeCreator {
               public Types.TypeApi apply(final Mirror $m$untyped) {
                  Universe $u = $m$untyped.universe();
                  return $m$untyped.staticClass("org.apache.spark.mllib.clustering.BisectingKMeansModel.Data").asType().toTypeConstructor();
               }

               public $typecreator1$6() {
               }
            }

            var27.checkSchema(var29, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$6()));
            Dataset data = rows.select("index", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"size", "center", "norm", "cost", "height", "children"})));
            scala.collection.immutable.Map nodes = scala.Predef..MODULE$.wrapRefArray(.MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(data.rdd().map((r) -> BisectingKMeansModel.Data$.MODULE$.apply(r), scala.reflect.ClassTag..MODULE$.apply(Data.class)).collect()), (d) -> new Tuple2(BoxesRunTime.boxToInteger(d.index()), d), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))).toMap(scala..less.colon.less..MODULE$.refl());
            ClusteringTreeNode rootNode = BisectingKMeansModel$.MODULE$.org$apache$spark$mllib$clustering$BisectingKMeansModel$$buildTree(rootId, nodes);
            return new BisectingKMeansModel(rootNode, distanceMeasure, trainingCost);
         }
      }

      // $FF: synthetic method
      public static final JValue $anonfun$save$17(final int x) {
         return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
      }

      // $FF: synthetic method
      public static final JValue $anonfun$save$19(final double x) {
         return org.json4s.JsonDSL..MODULE$.double2jvalue(x);
      }

      // $FF: synthetic method
      public static final int $anonfun$save$21(final ClusteringTreeNode x$10) {
         return x$10.index();
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
