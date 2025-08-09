package org.apache.spark.mllib.clustering;

import java.io.OutputStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.pmml.PMMLExportable;
import org.apache.spark.mllib.util.Loader$;
import org.apache.spark.mllib.util.Saveable;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructType;
import org.json4s.Formats;
import org.json4s.JValue;
import scala.MatchError;
import scala.Option;
import scala.Predef;
import scala.Product;
import scala.Some;
import scala.Tuple1;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Iterator;
import scala.collection.ArrayOps.;
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
   bytes = "\u0006\u0005\r-c\u0001\u0002$H\u0001IC\u0001\"\u001d\u0001\u0003\u0006\u0004%\tA\u001d\u0005\n\u0003\u0017\u0001!\u0011!Q\u0001\nMD!\"a\u0004\u0001\u0005\u000b\u0007I\u0011AA\t\u0011)\tI\u0003\u0001B\u0001B\u0003%\u00111\u0003\u0005\u000b\u0003[\u0001!Q1A\u0005\u0002\u0005=\u0002BCA\u001d\u0001\t\u0005\t\u0015!\u0003\u00022!Y\u0011Q\b\u0001\u0003\u0006\u0004%\taSA \u0011)\t9\u0005\u0001B\u0001B\u0003%\u0011\u0011\t\u0005\b\u0003\u0013\u0002A\u0011AA&\u0011)\ty\u0006\u0001EC\u0002\u0013%\u0011\u0011\r\u0005\u000b\u0003c\u0002\u0001R1A\u0005\n\u0005M\u0004BCA@\u0001!\u0015\r\u0011\"\u0003\u0002\u0002\"A\u0011\u0011\n\u0001\u0005\u0002-\u000bi\tC\u0004\u0002J\u0001!\t!!&\t\u000f\u0005%\u0003\u0001\"\u0001\u0002 \"9\u00111\u0018\u0001\u0005\u0002\u0005}\u0002bBAb\u0001\u0011\u0005\u0011Q\u0019\u0005\b\u0003\u0007\u0004A\u0011AAg\u0011\u001d\t\u0019\r\u0001C\u0001\u0003GDq!a@\u0001\t\u0003\u0011\t\u0001C\u0004\u0003\n\u0001!\tEa\u0003\b\u000f\t\u001dr\t#\u0001\u0003*\u00191ai\u0012E\u0001\u0005WAq!!\u0013\u0018\t\u0003\u0011i\u0004C\u0004\u0003@]!\tE!\u0011\u0007\u000f\t%s\u0003Q\f\u0003L!Q!1\u000b\u000e\u0003\u0016\u0004%\t!a\u0010\t\u0015\tU#D!E!\u0002\u0013\t\t\u0005\u0003\u0006\u0002Jj\u0011)\u001a!C\u0001\u0005/B\u0011B!\u0017\u001b\u0005#\u0005\u000b\u0011\u0002<\t\u000f\u0005%#\u0004\"\u0001\u0003\\!I!Q\r\u000e\u0002\u0002\u0013\u0005!q\r\u0005\n\u0005[R\u0012\u0013!C\u0001\u0005_B\u0011Ba!\u001b#\u0003%\tA!\"\t\u0013\t%%$!A\u0005B\t-\u0005\"\u0003BI5\u0005\u0005I\u0011AA \u0011%\u0011\u0019JGA\u0001\n\u0003\u0011)\nC\u0005\u0003\"j\t\t\u0011\"\u0011\u0003$\"I!\u0011\u0017\u000e\u0002\u0002\u0013\u0005!1\u0017\u0005\n\u0005{S\u0012\u0011!C!\u0005\u007fC\u0011Ba1\u001b\u0003\u0003%\tE!2\t\u0013\t\u001d'$!A\u0005B\t%\u0007\"\u0003Bf5\u0005\u0005I\u0011\tBg\u000f!\u0011\tn\u0006E\u0001/\tMg\u0001\u0003B%/!\u0005qC!6\t\u000f\u0005%S\u0006\"\u0001\u0003X\"9!\u0011\\\u0017\u0005\u0002\tm\u0007\"\u0003Bm[\u0005\u0005I\u0011\u0011Bw\u0011%\u0011\u00190LA\u0001\n\u0003\u0013)\u0010C\u0005\u0004\u00045\n\t\u0011\"\u0003\u0004\u0006\u001dA1QB\f\t\u0002\u001d\u001byA\u0002\u0005\u0004\u0012]A\taRB\n\u0011\u001d\tI\u0005\u000eC\u0001\u0007+A\u0011ba\u00065\u0005\u0004%IAa#\t\u0011\reA\u0007)A\u0005\u0005\u001bC!ba\u00075\u0005\u0004%\ta\u0012BF\u0011!\u0019i\u0002\u000eQ\u0001\n\t5\u0005b\u0002B\u0005i\u0011\u00051q\u0004\u0005\b\u0005\u007f!D\u0011AB\u0015\u000f!\u0019yc\u0006E\u0001\u000f\u000eEb\u0001CB\u001a/!\u0005qi!\u000e\t\u000f\u0005%S\b\"\u0001\u00048!I1qC\u001fC\u0002\u0013%!1\u0012\u0005\t\u00073i\u0004\u0015!\u0003\u0003\u000e\"Q11D\u001fC\u0002\u0013\u0005qIa#\t\u0011\ruQ\b)A\u0005\u0005\u001bCqA!\u0003>\t\u0003\u0019I\u0004C\u0004\u0003@u\"\ta!\u0011\t\u0013\r\rq#!A\u0005\n\r\u0015!aC&NK\u0006t7/T8eK2T!\u0001S%\u0002\u0015\rdWo\u001d;fe&twM\u0003\u0002K\u0017\u0006)Q\u000e\u001c7jE*\u0011A*T\u0001\u0006gB\f'o\u001b\u0006\u0003\u001d>\u000ba!\u00199bG\",'\"\u0001)\u0002\u0007=\u0014xm\u0001\u0001\u0014\u000b\u0001\u0019\u0016lX6\u0011\u0005Q;V\"A+\u000b\u0003Y\u000bQa]2bY\u0006L!\u0001W+\u0003\r\u0005s\u0017PU3g!\tQV,D\u0001\\\u0015\ta\u0016*\u0001\u0003vi&d\u0017B\u00010\\\u0005!\u0019\u0016M^3bE2,\u0007C\u00011i\u001d\t\tgM\u0004\u0002cK6\t1M\u0003\u0002e#\u00061AH]8pizJ\u0011AV\u0005\u0003OV\u000bq\u0001]1dW\u0006<W-\u0003\u0002jU\na1+\u001a:jC2L'0\u00192mK*\u0011q-\u0016\t\u0003Y>l\u0011!\u001c\u0006\u0003]&\u000bA\u0001]7nY&\u0011\u0001/\u001c\u0002\u000f!6kE*\u0012=q_J$\u0018M\u00197f\u00039\u0019G.^:uKJ\u001cUM\u001c;feN,\u0012a\u001d\t\u0004)R4\u0018BA;V\u0005\u0015\t%O]1z!\t9(0D\u0001y\u0015\tI\u0018*\u0001\u0004mS:\fGnZ\u0005\u0003wb\u0014aAV3di>\u0014\b\u0006B\u0001~\u0003\u000f\u00012A`A\u0002\u001b\u0005y(bAA\u0001\u0017\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0007\u0005\u0015qPA\u0003TS:\u001cW-\t\u0002\u0002\n\u0005)\u0011G\f\u0019/a\u0005y1\r\\;ti\u0016\u00148)\u001a8uKJ\u001c\b\u0005\u000b\u0003\u0003{\u0006\u001d\u0011a\u00043jgR\fgnY3NK\u0006\u001cXO]3\u0016\u0005\u0005M\u0001\u0003BA\u000b\u0003;qA!a\u0006\u0002\u001aA\u0011!-V\u0005\u0004\u00037)\u0016A\u0002)sK\u0012,g-\u0003\u0003\u0002 \u0005\u0005\"AB*ue&twMC\u0002\u0002\u001cUCCaA?\u0002&\u0005\u0012\u0011qE\u0001\u0006e9\"d\u0006M\u0001\u0011I&\u001cH/\u00198dK6+\u0017m];sK\u0002BC\u0001B?\u0002&\u0005aAO]1j]&twmQ8tiV\u0011\u0011\u0011\u0007\t\u0004)\u0006M\u0012bAA\u001b+\n1Ai\\;cY\u0016DC!B?\u0002&\u0005iAO]1j]&twmQ8ti\u0002BCAB?\u0002&\u00059a.^7Ji\u0016\u0014XCAA!!\r!\u00161I\u0005\u0004\u0003\u000b*&aA%oi\u0006Aa.^7Ji\u0016\u0014\b%\u0001\u0004=S:LGO\u0010\u000b\u000b\u0003\u001b\n\t&!\u0016\u0002Z\u0005u\u0003cAA(\u00015\tq\tC\u0003r\u0013\u0001\u00071\u000fK\u0003\u0002Ru\f9\u0001C\u0004\u0002\u0010%\u0001\r!a\u0005)\u000b\u0005US0!\n\t\u000f\u00055\u0012\u00021\u0001\u00022!*\u0011\u0011L?\u0002&!9\u0011QH\u0005A\u0002\u0005\u0005\u0013a\u00063jgR\fgnY3NK\u0006\u001cXO]3J]N$\u0018M\\2f+\t\t\u0019\u0007\u0005\u0003\u0002P\u0005\u0015\u0014bAA4\u000f\nyA)[:uC:\u001cW-T3bgV\u0014X\rK\u0002\u000b\u0003W\u00022\u0001VA7\u0013\r\ty'\u0016\u0002\niJ\fgn]5f]R\fac\u00197vgR,'oQ3oi\u0016\u00148oV5uQ:{'/\\\u000b\u0003\u0003k\u0002B\u0001\u0016;\u0002xA!\u0011qJA=\u0013\r\tYh\u0012\u0002\u000f-\u0016\u001cGo\u001c:XSRDgj\u001c:nQ\rY\u00111N\u0001\u000bgR\fG/[:uS\u000e\u001cXCAAB!\u0015!\u0016QQAE\u0013\r\t9)\u0016\u0002\u0007\u001fB$\u0018n\u001c8\u0011\tQ#\u0018\u0011\u0007\u0015\u0004\u0019\u0005-DCBA'\u0003\u001f\u000b\t\nC\u0003r\u001b\u0001\u00071\u000fC\u0004\u0002\u00105\u0001\r!a\u0005)\t5i\u0018Q\u0005\u000b\u0005\u0003\u001b\n9\nC\u0003r\u001d\u0001\u00071\u000f\u000b\u0003\u000f{\u0006m\u0015EAAO\u0003\u0015\td&\r\u00181)\u0011\ti%!)\t\u000f\u0005\rv\u00021\u0001\u0002&\u000691-\u001a8uKJ\u001c\b#BAT\u0003c3XBAAU\u0015\u0011\tY+!,\u0002\t1\fgn\u001a\u0006\u0003\u0003_\u000bAA[1wC&!\u00111WAU\u0005!IE/\u001a:bE2,\u0007\u0006B\b~\u0003o\u000b#!!/\u0002\u000bErCG\f\u0019\u0002\u0003-DC\u0001E?\u0002@\u0006\u0012\u0011\u0011Y\u0001\u0006a9Bd\u0006M\u0001\baJ,G-[2u)\u0011\t\t%a2\t\r\u0005%\u0017\u00031\u0001w\u0003\u0015\u0001x.\u001b8uQ\u0011\tR0a0\u0015\t\u0005=\u00171\u001c\t\u0007\u0003#\f9.!\u0011\u000e\u0005\u0005M'bAAk\u0017\u0006\u0019!\u000f\u001a3\n\t\u0005e\u00171\u001b\u0002\u0004%\u0012#\u0005bBAo%\u0001\u0007\u0011q\\\u0001\u0007a>Lg\u000e^:\u0011\u000b\u0005E\u0017q\u001b<)\tIi\u0018q\u0001\u000b\u0005\u0003K\fI\u0010\u0005\u0004\u0002h\u0006=\u00181_\u0007\u0003\u0003STA!a,\u0002l*\u0019\u0011Q^&\u0002\u0007\u0005\u0004\u0018.\u0003\u0003\u0002r\u0006%(a\u0002&bm\u0006\u0014F\t\u0012\t\u0005\u0003O\u000b)0\u0003\u0003\u0002x\u0006%&aB%oi\u0016<WM\u001d\u0005\b\u0003;\u001c\u0002\u0019AA~!\u0015\t9/a<wQ\u0011\u0019R0a\u0002\u0002\u0017\r|W\u000e];uK\u000e{7\u000f\u001e\u000b\u0005\u0003c\u0011\u0019\u0001C\u0004\u0003\u0006Q\u0001\r!a8\u0002\t\u0011\fG/\u0019\u0015\u0005)u\fy,\u0001\u0003tCZ,GC\u0002B\u0007\u0005'\u0011y\u0002E\u0002U\u0005\u001fI1A!\u0005V\u0005\u0011)f.\u001b;\t\u000f\tUQ\u00031\u0001\u0003\u0018\u0005\u00111o\u0019\t\u0005\u00053\u0011Y\"D\u0001L\u0013\r\u0011ib\u0013\u0002\r'B\f'o[\"p]R,\u0007\u0010\u001e\u0005\b\u0005C)\u0002\u0019AA\n\u0003\u0011\u0001\u0018\r\u001e5)\tUi\u0018q\u0017\u0015\u0005\u0001u\fy,A\u0006L\u001b\u0016\fgn]'pI\u0016d\u0007cAA(/M1qc\u0015B\u0017\u0005g\u0001RA\u0017B\u0018\u0003\u001bJ1A!\r\\\u0005\u0019au.\u00193feB!!Q\u0007B\u001e\u001b\t\u00119D\u0003\u0003\u0003:\u00055\u0016AA5p\u0013\rI'q\u0007\u000b\u0003\u0005S\tA\u0001\\8bIR1\u0011Q\nB\"\u0005\u000bBqA!\u0006\u001a\u0001\u0004\u00119\u0002C\u0004\u0003\"e\u0001\r!a\u0005)\tei\u0018q\u0017\u0002\b\u00072,8\u000f^3s'\u0015Q2K!\u0014`!\r!&qJ\u0005\u0004\u0005#*&a\u0002)s_\u0012,8\r^\u0001\u0003S\u0012\f1!\u001b3!+\u00051\u0018A\u00029pS:$\b\u0005\u0006\u0004\u0003^\t\u0005$1\r\t\u0004\u0005?RR\"A\f\t\u000f\tMs\u00041\u0001\u0002B!1\u0011\u0011Z\u0010A\u0002Y\fAaY8qsR1!Q\fB5\u0005WB\u0011Ba\u0015!!\u0003\u0005\r!!\u0011\t\u0011\u0005%\u0007\u0005%AA\u0002Y\fabY8qs\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0003r)\"\u0011\u0011\tB:W\t\u0011)\b\u0005\u0003\u0003x\t}TB\u0001B=\u0015\u0011\u0011YH! \u0002\u0013Ut7\r[3dW\u0016$'bAA\u0001+&!!\u0011\u0011B=\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\t\u00119IK\u0002w\u0005g\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXC\u0001BG!\u0011\t9Ka$\n\t\u0005}\u0011\u0011V\u0001\raJ|G-^2u\u0003JLG/_\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\u0011\u00119J!(\u0011\u0007Q\u0013I*C\u0002\u0003\u001cV\u00131!\u00118z\u0011%\u0011y*JA\u0001\u0002\u0004\t\t%A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0005K\u0003bAa*\u0003.\n]UB\u0001BU\u0015\r\u0011Y+V\u0001\u000bG>dG.Z2uS>t\u0017\u0002\u0002BX\u0005S\u0013\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!!Q\u0017B^!\r!&qW\u0005\u0004\u0005s+&a\u0002\"p_2,\u0017M\u001c\u0005\n\u0005?;\u0013\u0011!a\u0001\u0005/\u000b!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!!Q\u0012Ba\u0011%\u0011y\nKA\u0001\u0002\u0004\t\t%\u0001\u0005iCND7i\u001c3f)\t\t\t%\u0001\u0005u_N#(/\u001b8h)\t\u0011i)\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0005k\u0013y\rC\u0005\u0003 .\n\t\u00111\u0001\u0003\u0018\u000691\t\\;ti\u0016\u0014\bc\u0001B0[M!Qf\u0015B\u001a)\t\u0011\u0019.A\u0003baBd\u0017\u0010\u0006\u0003\u0003^\tu\u0007b\u0002Bp_\u0001\u0007!\u0011]\u0001\u0002eB!!1\u001dBu\u001b\t\u0011)OC\u0002\u0003h.\u000b1a]9m\u0013\u0011\u0011YO!:\u0003\u0007I{w\u000f\u0006\u0004\u0003^\t=(\u0011\u001f\u0005\b\u0005'\u0002\u0004\u0019AA!\u0011\u0019\tI\r\ra\u0001m\u00069QO\\1qa2LH\u0003\u0002B|\u0005\u007f\u0004R\u0001VAC\u0005s\u0004b\u0001\u0016B~\u0003\u00032\u0018b\u0001B\u007f+\n1A+\u001e9mKJB\u0011b!\u00012\u0003\u0003\u0005\rA!\u0018\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0004\bA!\u0011qUB\u0005\u0013\u0011\u0019Y!!+\u0003\r=\u0013'.Z2u\u00031\u0019\u0016M^3M_\u0006$g+M01!\r\u0011y\u0006\u000e\u0002\r'\u00064X\rT8bIZ\u000bt\fM\n\u0003iM#\"aa\u0004\u0002#QD\u0017n\u001d$pe6\fGOV3sg&|g.\u0001\nuQ&\u001chi\u001c:nCR4VM]:j_:\u0004\u0013!\u0004;iSN\u001cE.Y:t\u001d\u0006lW-\u0001\buQ&\u001c8\t\\1tg:\u000bW.\u001a\u0011\u0015\u0011\t51\u0011EB\u0012\u0007OAqA!\u0006;\u0001\u0004\u00119\u0002C\u0004\u0004&i\u0002\r!!\u0014\u0002\u000b5|G-\u001a7\t\u000f\t\u0005\"\b1\u0001\u0002\u0014Q1\u0011QJB\u0016\u0007[AqA!\u0006<\u0001\u0004\u00119\u0002C\u0004\u0003\"m\u0002\r!a\u0005\u0002\u0019M\u000bg/\u001a'pC\u00124&g\u0018\u0019\u0011\u0007\t}SH\u0001\u0007TCZ,Gj\\1e-Jz\u0006g\u0005\u0002>'R\u00111\u0011\u0007\u000b\t\u0005\u001b\u0019Yd!\u0010\u0004@!9!QC\"A\u0002\t]\u0001bBB\u0013\u0007\u0002\u0007\u0011Q\n\u0005\b\u0005C\u0019\u0005\u0019AA\n)\u0019\tiea\u0011\u0004F!9!Q\u0003#A\u0002\t]\u0001b\u0002B\u0011\t\u0002\u0007\u00111\u0003\u0015\u0005/u\f9\f\u000b\u0003\u0017{\u0006]\u0006"
)
public class KMeansModel implements Saveable, Serializable, PMMLExportable {
   private transient DistanceMeasure distanceMeasureInstance;
   private transient VectorWithNorm[] org$apache$spark$mllib$clustering$KMeansModel$$clusterCentersWithNorm;
   private transient Option statistics;
   private final Vector[] clusterCenters;
   private final String distanceMeasure;
   private final double trainingCost;
   private final int numIter;
   private transient volatile byte bitmap$trans$0;

   public static KMeansModel load(final SparkContext sc, final String path) {
      return KMeansModel$.MODULE$.load(sc, path);
   }

   public void toPMML(final String localPath) {
      PMMLExportable.toPMML$(this, (String)localPath);
   }

   public void toPMML(final SparkContext sc, final String path) {
      PMMLExportable.toPMML$(this, sc, path);
   }

   public void toPMML(final OutputStream outputStream) {
      PMMLExportable.toPMML$(this, (OutputStream)outputStream);
   }

   public String toPMML() {
      return PMMLExportable.toPMML$(this);
   }

   public Vector[] clusterCenters() {
      return this.clusterCenters;
   }

   public String distanceMeasure() {
      return this.distanceMeasure;
   }

   public double trainingCost() {
      return this.trainingCost;
   }

   public int numIter() {
      return this.numIter;
   }

   private DistanceMeasure distanceMeasureInstance$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 1) == 0) {
            this.distanceMeasureInstance = DistanceMeasure$.MODULE$.decodeFromString(this.distanceMeasure());
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.distanceMeasureInstance;
   }

   private DistanceMeasure distanceMeasureInstance() {
      return (byte)(this.bitmap$trans$0 & 1) == 0 ? this.distanceMeasureInstance$lzycompute() : this.distanceMeasureInstance;
   }

   private VectorWithNorm[] clusterCentersWithNorm$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 2) == 0) {
            this.org$apache$spark$mllib$clustering$KMeansModel$$clusterCentersWithNorm = this.clusterCenters() == null ? null : (VectorWithNorm[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(this.clusterCenters()), (x$1) -> new VectorWithNorm(x$1), scala.reflect.ClassTag..MODULE$.apply(VectorWithNorm.class));
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.org$apache$spark$mllib$clustering$KMeansModel$$clusterCentersWithNorm;
   }

   public VectorWithNorm[] org$apache$spark$mllib$clustering$KMeansModel$$clusterCentersWithNorm() {
      return (byte)(this.bitmap$trans$0 & 2) == 0 ? this.clusterCentersWithNorm$lzycompute() : this.org$apache$spark$mllib$clustering$KMeansModel$$clusterCentersWithNorm;
   }

   private Option statistics$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 4) == 0) {
            Object var10001;
            if (this.clusterCenters() == null) {
               var10001 = scala.None..MODULE$;
            } else {
               int k = this.clusterCenters().length;
               int numFeatures = ((Vector).MODULE$.head$extension(scala.Predef..MODULE$.refArrayOps(this.clusterCenters()))).size();
               var10001 = DistanceMeasure$.MODULE$.shouldComputeStatistics(k) && DistanceMeasure$.MODULE$.shouldComputeStatisticsLocally(k, numFeatures) ? new Some(this.distanceMeasureInstance().computeStatistics(this.org$apache$spark$mllib$clustering$KMeansModel$$clusterCentersWithNorm())) : scala.None..MODULE$;
            }

            this.statistics = (Option)var10001;
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 4);
         }
      } catch (Throwable var5) {
         throw var5;
      }

      return this.statistics;
   }

   private Option statistics() {
      return (byte)(this.bitmap$trans$0 & 4) == 0 ? this.statistics$lzycompute() : this.statistics;
   }

   public int k() {
      return this.org$apache$spark$mllib$clustering$KMeansModel$$clusterCentersWithNorm().length;
   }

   public int predict(final Vector point) {
      return this.distanceMeasureInstance().findClosest(this.org$apache$spark$mllib$clustering$KMeansModel$$clusterCentersWithNorm(), this.statistics(), new VectorWithNorm(point))._1$mcI$sp();
   }

   public RDD predict(final RDD points) {
      Broadcast bcCentersWithNorm = points.context().broadcast(this.org$apache$spark$mllib$clustering$KMeansModel$$clusterCentersWithNorm(), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(VectorWithNorm.class)));
      Broadcast bcStatistics = points.context().broadcast(this.statistics(), scala.reflect.ClassTag..MODULE$.apply(Option.class));
      return points.map((p) -> BoxesRunTime.boxToInteger($anonfun$predict$1(this, bcCentersWithNorm, bcStatistics, p)), scala.reflect.ClassTag..MODULE$.Int());
   }

   public JavaRDD predict(final JavaRDD points) {
      return this.predict(points.rdd()).toJavaRDD();
   }

   public double computeCost(final RDD data) {
      Broadcast bcCentersWithNorm = data.context().broadcast(this.org$apache$spark$mllib$clustering$KMeansModel$$clusterCentersWithNorm(), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(VectorWithNorm.class)));
      double cost = org.apache.spark.rdd.RDD..MODULE$.doubleRDDToDoubleRDDFunctions(data.map((p) -> BoxesRunTime.boxToDouble($anonfun$computeCost$1(this, bcCentersWithNorm, p)), scala.reflect.ClassTag..MODULE$.Double())).sum();
      bcCentersWithNorm.destroy();
      return cost;
   }

   public void save(final SparkContext sc, final String path) {
      KMeansModel.SaveLoadV2_0$.MODULE$.save(sc, this, path);
   }

   // $FF: synthetic method
   public static final int $anonfun$predict$1(final KMeansModel $this, final Broadcast bcCentersWithNorm$1, final Broadcast bcStatistics$1, final Vector p) {
      return $this.distanceMeasureInstance().findClosest((VectorWithNorm[])bcCentersWithNorm$1.value(), (Option)bcStatistics$1.value(), new VectorWithNorm(p))._1$mcI$sp();
   }

   // $FF: synthetic method
   public static final double $anonfun$computeCost$1(final KMeansModel $this, final Broadcast bcCentersWithNorm$2, final Vector p) {
      return $this.distanceMeasureInstance().pointCost((VectorWithNorm[])bcCentersWithNorm$2.value(), new VectorWithNorm(p));
   }

   public KMeansModel(final Vector[] clusterCenters, final String distanceMeasure, final double trainingCost, final int numIter) {
      this.clusterCenters = clusterCenters;
      this.distanceMeasure = distanceMeasure;
      this.trainingCost = trainingCost;
      this.numIter = numIter;
      PMMLExportable.$init$(this);
   }

   public KMeansModel(final Vector[] clusterCenters, final String distanceMeasure) {
      this(clusterCenters, distanceMeasure, (double)0.0F, -1);
   }

   public KMeansModel(final Vector[] clusterCenters) {
      this(clusterCenters, DistanceMeasure$.MODULE$.EUCLIDEAN(), (double)0.0F, -1);
   }

   public KMeansModel(final Iterable centers) {
      this((Vector[])scala.jdk.CollectionConverters..MODULE$.IterableHasAsScala(centers).asScala().toArray(scala.reflect.ClassTag..MODULE$.apply(Vector.class)));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class Cluster implements Product, Serializable {
      private final int id;
      private final Vector point;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public int id() {
         return this.id;
      }

      public Vector point() {
         return this.point;
      }

      public Cluster copy(final int id, final Vector point) {
         return new Cluster(id, point);
      }

      public int copy$default$1() {
         return this.id();
      }

      public Vector copy$default$2() {
         return this.point();
      }

      public String productPrefix() {
         return "Cluster";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return BoxesRunTime.boxToInteger(this.id());
            }
            case 1 -> {
               return this.point();
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
         return x$1 instanceof Cluster;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "id";
            }
            case 1 -> {
               return "point";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, this.id());
         var1 = Statics.mix(var1, Statics.anyHash(this.point()));
         return Statics.finalizeHash(var1, 2);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var6;
         if (this != x$1) {
            label51: {
               if (x$1 instanceof Cluster) {
                  Cluster var4 = (Cluster)x$1;
                  if (this.id() == var4.id()) {
                     label44: {
                        Vector var10000 = this.point();
                        Vector var5 = var4.point();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label44;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label44;
                        }

                        if (var4.canEqual(this)) {
                           break label51;
                        }
                     }
                  }
               }

               var6 = false;
               return var6;
            }
         }

         var6 = true;
         return var6;
      }

      public Cluster(final int id, final Vector point) {
         this.id = id;
         this.point = point;
         Product.$init$(this);
      }
   }

   public static class Cluster$ implements Serializable {
      public static final Cluster$ MODULE$ = new Cluster$();

      public Cluster apply(final Row r) {
         return new Cluster(r.getInt(0), (Vector)r.getAs(1));
      }

      public Cluster apply(final int id, final Vector point) {
         return new Cluster(id, point);
      }

      public Option unapply(final Cluster x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToInteger(x$0.id()), x$0.point())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Cluster$.class);
      }
   }

   public static class SaveLoadV1_0$ {
      public static final SaveLoadV1_0$ MODULE$ = new SaveLoadV1_0$();
      private static final String thisFormatVersion = "1.0";
      private static final String thisClassName = "org.apache.spark.mllib.clustering.KMeansModel";

      private String thisFormatVersion() {
         return thisFormatVersion;
      }

      public String thisClassName() {
         return thisClassName;
      }

      public void save(final SparkContext sc, final KMeansModel model, final String path) {
         SparkSession spark = org.apache.spark.sql.SparkSession..MODULE$.builder().sparkContext(sc).getOrCreate();
         String metadata = org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), this.thisClassName()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("version"), this.thisFormatVersion()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("k"), BoxesRunTime.boxToInteger(model.k())), (x) -> $anonfun$save$4(BoxesRunTime.unboxToInt(x)))), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
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
         RDD dataRDD = sc.parallelize(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(.MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.refArrayOps(model.org$apache$spark$mllib$clustering$KMeansModel$$clusterCentersWithNorm()))).toImmutableArraySeq(), sc.parallelize$default$2(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)).map((x0$1) -> {
            if (x0$1 != null) {
               VectorWithNorm p = (VectorWithNorm)x0$1._1();
               int id = x0$1._2$mcI$sp();
               return new Cluster(id, p.vector());
            } else {
               throw new MatchError(x0$1);
            }
         }, scala.reflect.ClassTag..MODULE$.apply(Cluster.class));
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator2$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.mllib.clustering.KMeansModel.Cluster").asType().toTypeConstructor();
            }

            public $typecreator2$1() {
            }
         }

         spark.createDataFrame(dataRDD, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1())).write().parquet(Loader$.MODULE$.dataPath(path));
      }

      public KMeansModel load(final SparkContext sc, final String path) {
         Formats formats = org.json4s.DefaultFormats..MODULE$;
         SparkSession spark = org.apache.spark.sql.SparkSession..MODULE$.builder().sparkContext(sc).getOrCreate();
         Tuple3 var7 = Loader$.MODULE$.loadMetadata(sc, path);
         if (var7 == null) {
            throw new MatchError(var7);
         } else {
            String formatVersion;
            JValue metadata;
            Predef var10000;
            boolean var10001;
            label41: {
               label40: {
                  String className = (String)var7._1();
                  String formatVersion = (String)var7._2();
                  JValue metadata = (JValue)var7._3();
                  Tuple3 var6 = new Tuple3(className, formatVersion, metadata);
                  String className = (String)var6._1();
                  formatVersion = (String)var6._2();
                  metadata = (JValue)var6._3();
                  var10000 = scala.Predef..MODULE$;
                  String var14 = this.thisClassName();
                  if (className == null) {
                     if (var14 == null) {
                        break label40;
                     }
                  } else if (className.equals(var14)) {
                     break label40;
                  }

                  var10001 = false;
                  break label41;
               }

               var10001 = true;
            }

            label33: {
               label32: {
                  var10000.assert(var10001);
                  var10000 = scala.Predef..MODULE$;
                  String var15 = this.thisFormatVersion();
                  if (formatVersion == null) {
                     if (var15 == null) {
                        break label32;
                     }
                  } else if (formatVersion.equals(var15)) {
                     break label32;
                  }

                  var10001 = false;
                  break label33;
               }

               var10001 = true;
            }

            var10000.assert(var10001);
            int k = BoxesRunTime.unboxToInt(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata), "k")), formats, scala.reflect.ManifestFactory..MODULE$.Int()));
            Dataset centroids = spark.read().parquet(Loader$.MODULE$.dataPath(path));
            Loader$ var22 = Loader$.MODULE$;
            StructType var24 = centroids.schema();
            JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
            JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

            final class $typecreator1$2 extends TypeCreator {
               public Types.TypeApi apply(final Mirror $m$untyped) {
                  Universe $u = $m$untyped.universe();
                  return $m$untyped.staticClass("org.apache.spark.mllib.clustering.KMeansModel.Cluster").asType().toTypeConstructor();
               }

               public $typecreator1$2() {
               }
            }

            var22.checkSchema(var24, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$2()));
            Cluster[] localCentroids = (Cluster[])centroids.rdd().map((r) -> KMeansModel.Cluster$.MODULE$.apply(r), scala.reflect.ClassTag..MODULE$.apply(Cluster.class)).collect();
            scala.Predef..MODULE$.assert(k == localCentroids.length);
            return new KMeansModel((Vector[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.sortBy$extension(scala.Predef..MODULE$.refArrayOps(localCentroids), (x$4) -> BoxesRunTime.boxToInteger($anonfun$load$2(x$4)), scala.math.Ordering.Int..MODULE$)), (x$5) -> x$5.point(), scala.reflect.ClassTag..MODULE$.apply(Vector.class)));
         }
      }

      // $FF: synthetic method
      public static final JValue $anonfun$save$4(final int x) {
         return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
      }

      // $FF: synthetic method
      public static final int $anonfun$load$2(final Cluster x$4) {
         return x$4.id();
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class SaveLoadV2_0$ {
      public static final SaveLoadV2_0$ MODULE$ = new SaveLoadV2_0$();
      private static final String thisFormatVersion = "2.0";
      private static final String thisClassName = "org.apache.spark.mllib.clustering.KMeansModel";

      private String thisFormatVersion() {
         return thisFormatVersion;
      }

      public String thisClassName() {
         return thisClassName;
      }

      public void save(final SparkContext sc, final KMeansModel model, final String path) {
         SparkSession spark = org.apache.spark.sql.SparkSession..MODULE$.builder().sparkContext(sc).getOrCreate();
         String metadata = org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), this.thisClassName()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("version"), this.thisFormatVersion()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("k"), BoxesRunTime.boxToInteger(model.k())), (x) -> $anonfun$save$9(BoxesRunTime.unboxToInt(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("distanceMeasure"), model.distanceMeasure()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("trainingCost"), BoxesRunTime.boxToDouble(model.trainingCost())), (x) -> $anonfun$save$11(BoxesRunTime.unboxToDouble(x)))), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
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
         RDD dataRDD = sc.parallelize(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(.MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.refArrayOps(model.org$apache$spark$mllib$clustering$KMeansModel$$clusterCentersWithNorm()))).toImmutableArraySeq(), sc.parallelize$default$2(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)).map((x0$1) -> {
            if (x0$1 != null) {
               VectorWithNorm p = (VectorWithNorm)x0$1._1();
               int id = x0$1._2$mcI$sp();
               return new Cluster(id, p.vector());
            } else {
               throw new MatchError(x0$1);
            }
         }, scala.reflect.ClassTag..MODULE$.apply(Cluster.class));
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator2$2 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.mllib.clustering.KMeansModel.Cluster").asType().toTypeConstructor();
            }

            public $typecreator2$2() {
            }
         }

         spark.createDataFrame(dataRDD, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$2())).write().parquet(Loader$.MODULE$.dataPath(path));
      }

      public KMeansModel load(final SparkContext sc, final String path) {
         Formats formats = org.json4s.DefaultFormats..MODULE$;
         SparkSession spark = org.apache.spark.sql.SparkSession..MODULE$.builder().sparkContext(sc).getOrCreate();
         Tuple3 var7 = Loader$.MODULE$.loadMetadata(sc, path);
         if (var7 == null) {
            throw new MatchError(var7);
         } else {
            String formatVersion;
            JValue metadata;
            Predef var10000;
            boolean var10001;
            label41: {
               label40: {
                  String className = (String)var7._1();
                  String formatVersion = (String)var7._2();
                  JValue metadata = (JValue)var7._3();
                  Tuple3 var6 = new Tuple3(className, formatVersion, metadata);
                  String className = (String)var6._1();
                  formatVersion = (String)var6._2();
                  metadata = (JValue)var6._3();
                  var10000 = scala.Predef..MODULE$;
                  String var14 = this.thisClassName();
                  if (className == null) {
                     if (var14 == null) {
                        break label40;
                     }
                  } else if (className.equals(var14)) {
                     break label40;
                  }

                  var10001 = false;
                  break label41;
               }

               var10001 = true;
            }

            label33: {
               label32: {
                  var10000.assert(var10001);
                  var10000 = scala.Predef..MODULE$;
                  String var15 = this.thisFormatVersion();
                  if (formatVersion == null) {
                     if (var15 == null) {
                        break label32;
                     }
                  } else if (formatVersion.equals(var15)) {
                     break label32;
                  }

                  var10001 = false;
                  break label33;
               }

               var10001 = true;
            }

            var10000.assert(var10001);
            int k = BoxesRunTime.unboxToInt(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata), "k")), formats, scala.reflect.ManifestFactory..MODULE$.Int()));
            Dataset centroids = spark.read().parquet(Loader$.MODULE$.dataPath(path));
            Loader$ var25 = Loader$.MODULE$;
            StructType var27 = centroids.schema();
            JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
            JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

            final class $typecreator1$4 extends TypeCreator {
               public Types.TypeApi apply(final Mirror $m$untyped) {
                  Universe $u = $m$untyped.universe();
                  return $m$untyped.staticClass("org.apache.spark.mllib.clustering.KMeansModel.Cluster").asType().toTypeConstructor();
               }

               public $typecreator1$4() {
               }
            }

            var25.checkSchema(var27, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$4()));
            Cluster[] localCentroids = (Cluster[])centroids.rdd().map((r) -> KMeansModel.Cluster$.MODULE$.apply(r), scala.reflect.ClassTag..MODULE$.apply(Cluster.class)).collect();
            scala.Predef..MODULE$.assert(k == localCentroids.length);
            String distanceMeasure = (String)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata), "distanceMeasure")), formats, scala.reflect.ManifestFactory..MODULE$.classType(String.class));
            double trainingCost = BoxesRunTime.unboxToDouble(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata), "trainingCost")), formats, scala.reflect.ManifestFactory..MODULE$.Double()));
            return new KMeansModel((Vector[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.sortBy$extension(scala.Predef..MODULE$.refArrayOps(localCentroids), (x$7) -> BoxesRunTime.boxToInteger($anonfun$load$5(x$7)), scala.math.Ordering.Int..MODULE$)), (x$8) -> x$8.point(), scala.reflect.ClassTag..MODULE$.apply(Vector.class)), distanceMeasure, trainingCost, -1);
         }
      }

      // $FF: synthetic method
      public static final JValue $anonfun$save$9(final int x) {
         return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
      }

      // $FF: synthetic method
      public static final JValue $anonfun$save$11(final double x) {
         return org.json4s.JsonDSL..MODULE$.double2jvalue(x);
      }

      // $FF: synthetic method
      public static final int $anonfun$load$5(final Cluster x$7) {
         return x$7.id();
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
