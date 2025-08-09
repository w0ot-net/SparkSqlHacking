package org.apache.spark.ml.regression;

import java.io.IOException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.linalg.Vectors.;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasFeaturesCol;
import org.apache.spark.ml.param.shared.HasLabelCol;
import org.apache.spark.ml.param.shared.HasPredictionCol;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.DefaultParamsReader$;
import org.apache.spark.ml.util.DefaultParamsWriter$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple3;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\rub\u0001\u0002 @\u0001)C\u0001B\u0017\u0001\u0003\u0006\u0004%\te\u0017\u0005\tS\u0002\u0011\t\u0011)A\u00059\"A!\u000e\u0001BC\u0002\u0013%1\u000e\u0003\u0005s\u0001\t\u0005\t\u0015!\u0003m\u0011\u0019\u0019\b\u0001\"\u0001Bi\"11\u000f\u0001C\u0001\u0003^DQ\u0001\u001f\u0001\u0005\u0002eDq!!\u0004\u0001\t\u0003\ty\u0001C\u0004\u0002\u0016\u0001!\t!a\u0006\t\u000f\u0005\u0015\u0002\u0001\"\u0001\u0002(!9\u00111\b\u0001\u0005\u0002\u0005\u001d\u0002bBA \u0001\u0011\u0005\u0013\u0011\t\u0005\b\u0003+\u0002A\u0011IA,\u0011\u001d\t\t\u000b\u0001C\u0001\u0003GCq!a-\u0001\t\u0003\n)\fC\u0004\u0002J\u0002!\t%a3\t\u0013\u0005e\u0007A1A\u0005\u0002\u0005m\u0007\u0002CAp\u0001\u0001\u0006I!a\u0007\t\u000f\u0005\r\b\u0001\"\u0011\u0002f\u001e9\u00111^ \t\u0002\u00055hA\u0002 @\u0011\u0003\ty\u000f\u0003\u0004t+\u0011\u0005!Q\u0002\u0005\b\u0005\u001f)B\u0011\tB\t\u0011\u001d\u0011Y\"\u0006C!\u0005;1qA!\n\u0016\u0001U\u00119\u0003C\u0005\u00036e\u0011\t\u0011)A\u0005\u001f\"11/\u0007C\u0001\u0005o1aAa\u0010\u001a\t\n\u0005\u0003BCA\u00139\tU\r\u0011\"\u0001\u0003X!Q!q\f\u000f\u0003\u0012\u0003\u0006IA!\u0017\t\u0015\u0005mBD!f\u0001\n\u0003\u00119\u0006\u0003\u0006\u0003bq\u0011\t\u0012)A\u0005\u00053B!Ba\u0019\u001d\u0005+\u0007I\u0011\u0001B3\u0011)\u0011i\u0007\bB\tB\u0003%!q\r\u0005\u0007gr!\tAa\u001c\t\u0013\u0005}B$!A\u0005\u0002\tm\u0004\"\u0003BB9E\u0005I\u0011\u0001BC\u0011%\u0011I\nHI\u0001\n\u0003\u0011)\tC\u0005\u0003\u001cr\t\n\u0011\"\u0001\u0003\u001e\"I!\u0011\u0015\u000f\u0002\u0002\u0013\u0005#1\u0015\u0005\n\u0005_c\u0012\u0011!C\u0001\u00037D\u0011B!-\u001d\u0003\u0003%\tAa-\t\u0013\teF$!A\u0005B\tm\u0006\"\u0003Be9\u0005\u0005I\u0011\u0001Bf\u0011%\u0011y\rHA\u0001\n\u0003\u0012\t\u000eC\u0005\u0003Vr\t\t\u0011\"\u0011\u0003X\"I\u00111\u001d\u000f\u0002\u0002\u0013\u0005#\u0011\u001c\u0005\n\u00057d\u0012\u0011!C!\u0005;<\u0011B!9\u001a\u0003\u0003EIAa9\u0007\u0013\t}\u0012$!A\t\n\t\u0015\bBB:3\t\u0003\u0011\u0019\u0010C\u0005\u0002dJ\n\t\u0011\"\u0012\u0003Z\"I!Q\u001f\u001a\u0002\u0002\u0013\u0005%q\u001f\u0005\n\u0005\u007f\u0014\u0014\u0011!CA\u0007\u0003Aqaa\u0005\u001a\t#\u001a)B\u0002\u0004\u0004 U!1\u0011\u0005\u0005\u0007gb\"\taa\t\t\u0013\r\u001d\u0002H1A\u0005\n\t\r\u0006\u0002CB\u0015q\u0001\u0006IA!*\t\u000f\tm\u0001\b\"\u0011\u0004,!I1qF\u000b\u0002\u0002\u0013%1\u0011\u0007\u0002\u0018\u0013N|Go\u001c8jGJ+wM]3tg&|g.T8eK2T!\u0001Q!\u0002\u0015I,wM]3tg&|gN\u0003\u0002C\u0007\u0006\u0011Q\u000e\u001c\u0006\u0003\t\u0016\u000bQa\u001d9be.T!AR$\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005A\u0015aA8sO\u000e\u00011\u0003\u0002\u0001L#R\u00032\u0001T'P\u001b\u0005\t\u0015B\u0001(B\u0005\u0015iu\u000eZ3m!\t\u0001\u0006!D\u0001@!\t\u0001&+\u0003\u0002T\u007f\t1\u0012j]8u_:L7MU3he\u0016\u001c8/[8o\u0005\u0006\u001cX\r\u0005\u0002V16\taK\u0003\u0002X\u0003\u0006!Q\u000f^5m\u0013\tIfK\u0001\u0006N\u0019^\u0013\u0018\u000e^1cY\u0016\f1!^5e+\u0005a\u0006CA/g\u001d\tqF\r\u0005\u0002`E6\t\u0001M\u0003\u0002b\u0013\u00061AH]8pizR\u0011aY\u0001\u0006g\u000e\fG.Y\u0005\u0003K\n\fa\u0001\u0015:fI\u00164\u0017BA4i\u0005\u0019\u0019FO]5oO*\u0011QMY\u0001\u0005k&$\u0007%\u0001\u0005pY\u0012lu\u000eZ3m+\u0005a\u0007CA7r\u001b\u0005q'B\u0001!p\u0015\t\u00018)A\u0003nY2L'-\u0003\u0002?]\u0006Iq\u000e\u001c3N_\u0012,G\u000eI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007=+h\u000fC\u0003[\u000b\u0001\u0007A\fC\u0003k\u000b\u0001\u0007A\u000eF\u0001P\u00039\u0019X\r\u001e$fCR,(/Z:D_2$\"A_>\u000e\u0003\u0001AQ\u0001`\u0004A\u0002q\u000bQA^1mk\u0016DCa\u0002@\u0002\nA\u0019q0!\u0002\u000e\u0005\u0005\u0005!bAA\u0002\u0007\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005\u001d\u0011\u0011\u0001\u0002\u0006'&t7-Z\u0011\u0003\u0003\u0017\tQ!\r\u00186]A\n\u0001c]3u!J,G-[2uS>t7i\u001c7\u0015\u0007i\f\t\u0002C\u0003}\u0011\u0001\u0007A\f\u000b\u0003\t}\u0006%\u0011aD:fi\u001a+\u0017\r^;sK&sG-\u001a=\u0015\u0007i\fI\u0002\u0003\u0004}\u0013\u0001\u0007\u00111\u0004\t\u0005\u0003;\ty\"D\u0001c\u0013\r\t\tC\u0019\u0002\u0004\u0013:$\b\u0006B\u0005\u007f\u0003\u0013\t!BY8v]\u0012\f'/[3t+\t\tI\u0003\u0005\u0003\u0002,\u0005ERBAA\u0017\u0015\r\ty#Q\u0001\u0007Y&t\u0017\r\\4\n\t\u0005M\u0012Q\u0006\u0002\u0007-\u0016\u001cGo\u001c:)\t)q\u0018qG\u0011\u0003\u0003s\tQA\r\u00181]A\n1\u0002\u001d:fI&\u001cG/[8og\"\"1B`A\u001c\u0003\u0011\u0019w\u000e]=\u0015\u0007=\u000b\u0019\u0005C\u0004\u0002F1\u0001\r!a\u0012\u0002\u000b\u0015DHO]1\u0011\t\u0005%\u0013qJ\u0007\u0003\u0003\u0017R1!!\u0014B\u0003\u0015\u0001\u0018M]1n\u0013\u0011\t\t&a\u0013\u0003\u0011A\u000b'/Y7NCBDC\u0001\u0004@\u0002\n\u0005IAO]1og\u001a|'/\u001c\u000b\u0005\u00033\nY\b\u0005\u0003\u0002\\\u0005Ud\u0002BA/\u0003_rA!a\u0018\u0002l9!\u0011\u0011MA5\u001d\u0011\t\u0019'a\u001a\u000f\u0007}\u000b)'C\u0001I\u0013\t1u)\u0003\u0002E\u000b&\u0019\u0011QN\"\u0002\u0007M\fH.\u0003\u0003\u0002r\u0005M\u0014a\u00029bG.\fw-\u001a\u0006\u0004\u0003[\u001a\u0015\u0002BA<\u0003s\u0012\u0011\u0002R1uC\u001a\u0013\u0018-\\3\u000b\t\u0005E\u00141\u000f\u0005\b\u0003{j\u0001\u0019AA@\u0003\u001d!\u0017\r^1tKR\u0004D!!!\u0002\u000eB1\u00111QAC\u0003\u0013k!!a\u001d\n\t\u0005\u001d\u00151\u000f\u0002\b\t\u0006$\u0018m]3u!\u0011\tY)!$\r\u0001\u0011a\u0011qRA>\u0003\u0003\u0005\tQ!\u0001\u0002\u0012\n\u0019q\fJ\u001a\u0012\t\u0005M\u0015\u0011\u0014\t\u0005\u0003;\t)*C\u0002\u0002\u0018\n\u0014qAT8uQ&tw\r\u0005\u0003\u0002\u001e\u0005m\u0015bAAOE\n\u0019\u0011I\\=)\t5q\u0018qG\u0001\baJ,G-[2u)\u0011\t)+a+\u0011\t\u0005u\u0011qU\u0005\u0004\u0003S\u0013'A\u0002#pk\ndW\r\u0003\u0004}\u001d\u0001\u0007\u0011Q\u0015\u0015\u0005\u001dy\fy+\t\u0002\u00022\u0006)1G\f\u0019/a\u0005yAO]1og\u001a|'/\\*dQ\u0016l\u0017\r\u0006\u0003\u00028\u0006\r\u0007\u0003BA]\u0003\u007fk!!a/\u000b\t\u0005u\u00161O\u0001\u0006if\u0004Xm]\u0005\u0005\u0003\u0003\fYL\u0001\u0006TiJ,8\r\u001e+za\u0016Dq!!2\u0010\u0001\u0004\t9,\u0001\u0004tG\",W.\u0019\u0015\u0005\u001fy\fI!A\u0003xe&$X-\u0006\u0002\u0002NB\u0019Q+a4\n\u0007\u0005EgK\u0001\u0005N\u0019^\u0013\u0018\u000e^3sQ\u0011\u0001b0!6\"\u0005\u0005]\u0017!B\u0019/m9\u0002\u0014a\u00038v[\u001a+\u0017\r^;sKN,\"!a\u0007)\tEq\u0018qV\u0001\r]Vlg)Z1ukJ,7\u000f\t\u0015\u0005%y\fy+\u0001\u0005u_N#(/\u001b8h)\u0005a\u0006\u0006B\n\u007f\u0003_CC\u0001\u0001@\u0002\n\u00059\u0012j]8u_:L7MU3he\u0016\u001c8/[8o\u001b>$W\r\u001c\t\u0003!V\u0019r!FAy\u0003o\fi\u0010\u0005\u0003\u0002\u001e\u0005M\u0018bAA{E\n1\u0011I\\=SK\u001a\u0004B!VA}\u001f&\u0019\u00111 ,\u0003\u00155c%+Z1eC\ndW\r\u0005\u0003\u0002\u0000\n%QB\u0001B\u0001\u0015\u0011\u0011\u0019A!\u0002\u0002\u0005%|'B\u0001B\u0004\u0003\u0011Q\u0017M^1\n\t\t-!\u0011\u0001\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u000b\u0003\u0003[\fAA]3bIV\u0011!1\u0003\t\u0005+\nUq*C\u0002\u0003\u0018Y\u0013\u0001\"\u0014'SK\u0006$WM\u001d\u0015\u0005/y\f).\u0001\u0003m_\u0006$GcA(\u0003 !1!\u0011\u0005\rA\u0002q\u000bA\u0001]1uQ\"\"\u0001D`Ak\u0005uI5o\u001c;p]&\u001c'+Z4sKN\u001c\u0018n\u001c8N_\u0012,Gn\u0016:ji\u0016\u00148#B\r\u0002N\n%\u0002\u0003\u0002B\u0016\u0005ci!A!\f\u000b\u0007\t=2)\u0001\u0005j]R,'O\\1m\u0013\u0011\u0011\u0019D!\f\u0003\u000f1{wmZ5oO\u0006A\u0011N\\:uC:\u001cW\r\u0006\u0003\u0003:\tu\u0002c\u0001B\u001e35\tQ\u0003\u0003\u0004\u00036m\u0001\ra\u0014\u0002\u0005\t\u0006$\u0018mE\u0004\u001d\u0003c\u0014\u0019E!\u0013\u0011\t\u0005u!QI\u0005\u0004\u0005\u000f\u0012'a\u0002)s_\u0012,8\r\u001e\t\u0005\u0005\u0017\u0012\u0019F\u0004\u0003\u0003N\tEcbA0\u0003P%\t1-C\u0002\u0002r\tLAAa\u0003\u0003V)\u0019\u0011\u0011\u000f2\u0016\u0005\te\u0003CBA\u000f\u00057\n)+C\u0002\u0003^\t\u0014Q!\u0011:sCf\f1BY8v]\u0012\f'/[3tA\u0005a\u0001O]3eS\u000e$\u0018n\u001c8tA\u0005A\u0011n]8u_:L7-\u0006\u0002\u0003hA!\u0011Q\u0004B5\u0013\r\u0011YG\u0019\u0002\b\u0005>|G.Z1o\u0003%I7o\u001c;p]&\u001c\u0007\u0005\u0006\u0005\u0003r\tU$q\u000fB=!\r\u0011\u0019\bH\u0007\u00023!9\u0011QE\u0012A\u0002\te\u0003bBA\u001eG\u0001\u0007!\u0011\f\u0005\b\u0005G\u001a\u0003\u0019\u0001B4)!\u0011\tH! \u0003\u0000\t\u0005\u0005\"CA\u0013IA\u0005\t\u0019\u0001B-\u0011%\tY\u0004\nI\u0001\u0002\u0004\u0011I\u0006C\u0005\u0003d\u0011\u0002\n\u00111\u0001\u0003h\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\nTC\u0001BDU\u0011\u0011IF!#,\u0005\t-\u0005\u0003\u0002BG\u0005+k!Aa$\u000b\t\tE%1S\u0001\nk:\u001c\u0007.Z2lK\u0012T1!a\u0001c\u0013\u0011\u00119Ja$\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%gU\u0011!q\u0014\u0016\u0005\u0005O\u0012I)A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0005K\u0003BAa*\u0003.6\u0011!\u0011\u0016\u0006\u0005\u0005W\u0013)!\u0001\u0003mC:<\u0017bA4\u0003*\u0006a\u0001O]8ek\u000e$\u0018I]5us\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BAM\u0005kC\u0011Ba.+\u0003\u0003\u0005\r!a\u0007\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\u0011i\f\u0005\u0004\u0003@\n\u0015\u0017\u0011T\u0007\u0003\u0005\u0003T1Aa1c\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0005\u000f\u0014\tM\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003\u0002B4\u0005\u001bD\u0011Ba.-\u0003\u0003\u0005\r!!'\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0005K\u0013\u0019\u000eC\u0005\u000386\n\t\u00111\u0001\u0002\u001c\u0005A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002\u001cQ\u0011!QU\u0001\u0007KF,\u0018\r\\:\u0015\t\t\u001d$q\u001c\u0005\n\u0005o\u0003\u0014\u0011!a\u0001\u00033\u000bA\u0001R1uCB\u0019!1\u000f\u001a\u0014\u000bI\u00129/!@\u0011\u0019\t%(q\u001eB-\u00053\u00129G!\u001d\u000e\u0005\t-(b\u0001BwE\u00069!/\u001e8uS6,\u0017\u0002\u0002By\u0005W\u0014\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c84)\t\u0011\u0019/A\u0003baBd\u0017\u0010\u0006\u0005\u0003r\te(1 B\u007f\u0011\u001d\t)#\u000ea\u0001\u00053Bq!a\u000f6\u0001\u0004\u0011I\u0006C\u0004\u0003dU\u0002\rAa\u001a\u0002\u000fUt\u0017\r\u001d9msR!11AB\b!\u0019\tib!\u0002\u0004\n%\u00191q\u00012\u0003\r=\u0003H/[8o!)\tiba\u0003\u0003Z\te#qM\u0005\u0004\u0007\u001b\u0011'A\u0002+va2,7\u0007C\u0005\u0004\u0012Y\n\t\u00111\u0001\u0003r\u0005\u0019\u0001\u0010\n\u0019\u0002\u0011M\fg/Z%na2$Baa\u0006\u0004\u001eA!\u0011QDB\r\u0013\r\u0019YB\u0019\u0002\u0005+:LG\u000f\u0003\u0004\u0003\"]\u0002\r\u0001\u0018\u0002\u001e\u0013N|Go\u001c8jGJ+wM]3tg&|g.T8eK2\u0014V-\u00193feN\u0019\u0001Ha\u0005\u0015\u0005\r\u0015\u0002c\u0001B\u001eq\u0005I1\r\\1tg:\u000bW.Z\u0001\u000bG2\f7o\u001d(b[\u0016\u0004CcA(\u0004.!1!\u0011\u0005\u001fA\u0002q\u000bAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"aa\r\u0011\t\t\u001d6QG\u0005\u0005\u0007o\u0011IK\u0001\u0004PE*,7\r\u001e\u0015\u0005+y\f)\u000e\u000b\u0003\u0015}\u0006U\u0007"
)
public class IsotonicRegressionModel extends Model implements IsotonicRegressionBase, MLWritable {
   private final String uid;
   private final org.apache.spark.mllib.regression.IsotonicRegressionModel org$apache$spark$ml$regression$IsotonicRegressionModel$$oldModel;
   private final int numFeatures;
   private BooleanParam isotonic;
   private IntParam featureIndex;
   private Param weightCol;
   private Param predictionCol;
   private Param labelCol;
   private Param featuresCol;

   public static IsotonicRegressionModel load(final String path) {
      return IsotonicRegressionModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return IsotonicRegressionModel$.MODULE$.read();
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public final boolean getIsotonic() {
      return IsotonicRegressionBase.getIsotonic$(this);
   }

   public final int getFeatureIndex() {
      return IsotonicRegressionBase.getFeatureIndex$(this);
   }

   public boolean hasWeightCol() {
      return IsotonicRegressionBase.hasWeightCol$(this);
   }

   public RDD extractWeightedLabeledPoints(final Dataset dataset) {
      return IsotonicRegressionBase.extractWeightedLabeledPoints$(this, dataset);
   }

   public StructType validateAndTransformSchema(final StructType schema, final boolean fitting) {
      return IsotonicRegressionBase.validateAndTransformSchema$(this, schema, fitting);
   }

   public final String getWeightCol() {
      return HasWeightCol.getWeightCol$(this);
   }

   public final String getPredictionCol() {
      return HasPredictionCol.getPredictionCol$(this);
   }

   public final String getLabelCol() {
      return HasLabelCol.getLabelCol$(this);
   }

   public final String getFeaturesCol() {
      return HasFeaturesCol.getFeaturesCol$(this);
   }

   public final BooleanParam isotonic() {
      return this.isotonic;
   }

   public final IntParam featureIndex() {
      return this.featureIndex;
   }

   public final void org$apache$spark$ml$regression$IsotonicRegressionBase$_setter_$isotonic_$eq(final BooleanParam x$1) {
      this.isotonic = x$1;
   }

   public final void org$apache$spark$ml$regression$IsotonicRegressionBase$_setter_$featureIndex_$eq(final IntParam x$1) {
      this.featureIndex = x$1;
   }

   public final Param weightCol() {
      return this.weightCol;
   }

   public final void org$apache$spark$ml$param$shared$HasWeightCol$_setter_$weightCol_$eq(final Param x$1) {
      this.weightCol = x$1;
   }

   public final Param predictionCol() {
      return this.predictionCol;
   }

   public final void org$apache$spark$ml$param$shared$HasPredictionCol$_setter_$predictionCol_$eq(final Param x$1) {
      this.predictionCol = x$1;
   }

   public final Param labelCol() {
      return this.labelCol;
   }

   public final void org$apache$spark$ml$param$shared$HasLabelCol$_setter_$labelCol_$eq(final Param x$1) {
      this.labelCol = x$1;
   }

   public final Param featuresCol() {
      return this.featuresCol;
   }

   public final void org$apache$spark$ml$param$shared$HasFeaturesCol$_setter_$featuresCol_$eq(final Param x$1) {
      this.featuresCol = x$1;
   }

   public String uid() {
      return this.uid;
   }

   public org.apache.spark.mllib.regression.IsotonicRegressionModel org$apache$spark$ml$regression$IsotonicRegressionModel$$oldModel() {
      return this.org$apache$spark$ml$regression$IsotonicRegressionModel$$oldModel;
   }

   public IsotonicRegressionModel setFeaturesCol(final String value) {
      return (IsotonicRegressionModel)this.set(this.featuresCol(), value);
   }

   public IsotonicRegressionModel setPredictionCol(final String value) {
      return (IsotonicRegressionModel)this.set(this.predictionCol(), value);
   }

   public IsotonicRegressionModel setFeatureIndex(final int value) {
      return (IsotonicRegressionModel)this.set(this.featureIndex(), BoxesRunTime.boxToInteger(value));
   }

   public Vector boundaries() {
      return .MODULE$.dense(this.org$apache$spark$ml$regression$IsotonicRegressionModel$$oldModel().boundaries());
   }

   public Vector predictions() {
      return .MODULE$.dense(this.org$apache$spark$ml$regression$IsotonicRegressionModel$$oldModel().predictions());
   }

   public IsotonicRegressionModel copy(final ParamMap extra) {
      return (IsotonicRegressionModel)((Model)this.copyValues(new IsotonicRegressionModel(this.uid(), this.org$apache$spark$ml$regression$IsotonicRegressionModel$$oldModel()), extra)).setParent(this.parent());
   }

   public Dataset transform(final Dataset dataset) {
      StructType outputSchema = this.transformSchema(dataset.schema(), true);
      DataType var5 = dataset.schema().apply((String)this.$(this.featuresCol())).dataType();
      UserDefinedFunction var10000;
      if (org.apache.spark.sql.types.DoubleType..MODULE$.equals(var5)) {
         var10000 = org.apache.spark.sql.functions..MODULE$.udf((JFunction1.mcDD.sp)(feature) -> this.org$apache$spark$ml$regression$IsotonicRegressionModel$$oldModel().predict(feature), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double(), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double());
      } else {
         if (!(var5 instanceof VectorUDT)) {
            throw new MatchError(var5);
         }

         int idx = BoxesRunTime.unboxToInt(this.$(this.featureIndex()));
         functions var9 = org.apache.spark.sql.functions..MODULE$;
         Function1 var10001 = (features) -> BoxesRunTime.boxToDouble($anonfun$transform$2(this, idx, features));
         TypeTags.TypeTag var10002 = ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double();
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(IsotonicRegressionModel.class.getClassLoader());

         final class $typecreator1$2 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
            }

            public $typecreator1$2() {
            }
         }

         var10000 = var9.udf(var10001, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$2()));
      }

      UserDefinedFunction predict = var10000;
      return dataset.withColumn((String)this.$(this.predictionCol()), predict.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.featuresCol()))}))), outputSchema.apply((String)this.$(this.predictionCol())).metadata());
   }

   public double predict(final double value) {
      return this.org$apache$spark$ml$regression$IsotonicRegressionModel$$oldModel().predict(value);
   }

   public StructType transformSchema(final StructType schema) {
      StructType outputSchema = this.validateAndTransformSchema(schema, false);
      if (scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.predictionCol())))) {
         outputSchema = SchemaUtils$.MODULE$.updateNumeric(outputSchema, (String)this.$(this.predictionCol()));
      }

      return outputSchema;
   }

   public MLWriter write() {
      return new IsotonicRegressionModelWriter(this);
   }

   public int numFeatures() {
      return this.numFeatures;
   }

   public String toString() {
      String var10000 = this.uid();
      return "IsotonicRegressionModel: uid=" + var10000 + ", numFeatures=" + this.numFeatures();
   }

   // $FF: synthetic method
   public static final double $anonfun$transform$2(final IsotonicRegressionModel $this, final int idx$2, final Vector features) {
      return $this.org$apache$spark$ml$regression$IsotonicRegressionModel$$oldModel().predict(features.apply(idx$2));
   }

   public IsotonicRegressionModel(final String uid, final org.apache.spark.mllib.regression.IsotonicRegressionModel oldModel) {
      this.uid = uid;
      this.org$apache$spark$ml$regression$IsotonicRegressionModel$$oldModel = oldModel;
      HasFeaturesCol.$init$(this);
      HasLabelCol.$init$(this);
      HasPredictionCol.$init$(this);
      HasWeightCol.$init$(this);
      IsotonicRegressionBase.$init$(this);
      MLWritable.$init$(this);
      this.numFeatures = 1;
      Statics.releaseFence();
   }

   public IsotonicRegressionModel() {
      this("", (org.apache.spark.mllib.regression.IsotonicRegressionModel)null);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class IsotonicRegressionModelWriter extends MLWriter {
      private volatile Data$ Data$module;
      private final IsotonicRegressionModel instance;

      private Data$ Data() {
         if (this.Data$module == null) {
            this.Data$lzycompute$1();
         }

         return this.Data$module;
      }

      public void saveImpl(final String path) {
         DefaultParamsWriter$.MODULE$.saveMetadata(this.instance, path, this.sparkSession());
         Data data = new Data(this.instance.org$apache$spark$ml$regression$IsotonicRegressionModel$$oldModel().boundaries(), this.instance.org$apache$spark$ml$regression$IsotonicRegressionModel$$oldModel().predictions(), this.instance.org$apache$spark$ml$regression$IsotonicRegressionModel$$oldModel().isotonic());
         String dataPath = (new Path(path, "data")).toString();
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(data, scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(IsotonicRegressionModelWriter.class.getClassLoader());

         final class $typecreator1$3 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticClass("org.apache.spark.ml.regression.IsotonicRegressionModel.IsotonicRegressionModelWriter")), $u.internal().reificationSupport().selectType($m$untyped.staticClass("org.apache.spark.ml.regression.IsotonicRegressionModel.IsotonicRegressionModelWriter"), "Data"), scala.collection.immutable.Nil..MODULE$);
            }

            public $typecreator1$3() {
            }
         }

         var10000.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$3())).write().parquet(dataPath);
      }

      private final void Data$lzycompute$1() {
         synchronized(this){}

         try {
            if (this.Data$module == null) {
               this.Data$module = new Data$();
            }
         } catch (Throwable var3) {
            throw var3;
         }

      }

      public IsotonicRegressionModelWriter(final IsotonicRegressionModel instance) {
         this.instance = instance;
      }

      private class Data implements Product, Serializable {
         private final double[] boundaries;
         private final double[] predictions;
         private final boolean isotonic;
         // $FF: synthetic field
         public final IsotonicRegressionModelWriter $outer;

         public Iterator productElementNames() {
            return Product.productElementNames$(this);
         }

         public double[] boundaries() {
            return this.boundaries;
         }

         public double[] predictions() {
            return this.predictions;
         }

         public boolean isotonic() {
            return this.isotonic;
         }

         public Data copy(final double[] boundaries, final double[] predictions, final boolean isotonic) {
            return this.org$apache$spark$ml$regression$IsotonicRegressionModel$IsotonicRegressionModelWriter$Data$$$outer().new Data(boundaries, predictions, isotonic);
         }

         public double[] copy$default$1() {
            return this.boundaries();
         }

         public double[] copy$default$2() {
            return this.predictions();
         }

         public boolean copy$default$3() {
            return this.isotonic();
         }

         public String productPrefix() {
            return "Data";
         }

         public int productArity() {
            return 3;
         }

         public Object productElement(final int x$1) {
            switch (x$1) {
               case 0 -> {
                  return this.boundaries();
               }
               case 1 -> {
                  return this.predictions();
               }
               case 2 -> {
                  return BoxesRunTime.boxToBoolean(this.isotonic());
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
                  return "boundaries";
               }
               case 1 -> {
                  return "predictions";
               }
               case 2 -> {
                  return "isotonic";
               }
               default -> {
                  return (String)Statics.ioobe(x$1);
               }
            }
         }

         public int hashCode() {
            int var1 = -889275714;
            var1 = Statics.mix(var1, this.productPrefix().hashCode());
            var1 = Statics.mix(var1, Statics.anyHash(this.boundaries()));
            var1 = Statics.mix(var1, Statics.anyHash(this.predictions()));
            var1 = Statics.mix(var1, this.isotonic() ? 1231 : 1237);
            return Statics.finalizeHash(var1, 3);
         }

         public String toString() {
            return scala.runtime.ScalaRunTime..MODULE$._toString(this);
         }

         public boolean equals(final Object x$1) {
            boolean var10000;
            if (this != x$1) {
               label45: {
                  if (x$1 instanceof Data && ((Data)x$1).org$apache$spark$ml$regression$IsotonicRegressionModel$IsotonicRegressionModelWriter$Data$$$outer() == this.org$apache$spark$ml$regression$IsotonicRegressionModel$IsotonicRegressionModelWriter$Data$$$outer()) {
                     Data var4 = (Data)x$1;
                     if (this.isotonic() == var4.isotonic() && this.boundaries() == var4.boundaries() && this.predictions() == var4.predictions() && var4.canEqual(this)) {
                        break label45;
                     }
                  }

                  var10000 = false;
                  return var10000;
               }
            }

            var10000 = true;
            return var10000;
         }

         // $FF: synthetic method
         public IsotonicRegressionModelWriter org$apache$spark$ml$regression$IsotonicRegressionModel$IsotonicRegressionModelWriter$Data$$$outer() {
            return this.$outer;
         }

         public Data(final double[] boundaries, final double[] predictions, final boolean isotonic) {
            this.boundaries = boundaries;
            this.predictions = predictions;
            this.isotonic = isotonic;
            if (IsotonicRegressionModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = IsotonicRegressionModelWriter.this;
               super();
               Product.$init$(this);
            }
         }
      }

      private class Data$ extends AbstractFunction3 implements Serializable {
         // $FF: synthetic field
         private final IsotonicRegressionModelWriter $outer;

         public final String toString() {
            return "Data";
         }

         public Data apply(final double[] boundaries, final double[] predictions, final boolean isotonic) {
            return this.$outer.new Data(boundaries, predictions, isotonic);
         }

         public Option unapply(final Data x$0) {
            return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(x$0.boundaries(), x$0.predictions(), BoxesRunTime.boxToBoolean(x$0.isotonic()))));
         }

         public Data$() {
            if (IsotonicRegressionModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = IsotonicRegressionModelWriter.this;
               super();
            }
         }
      }
   }

   private static class IsotonicRegressionModelReader extends MLReader {
      private final String className = IsotonicRegressionModel.class.getName();

      private String className() {
         return this.className;
      }

      public IsotonicRegressionModel load(final String path) {
         DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, this.sparkSession(), this.className());
         String dataPath = (new Path(path, "data")).toString();
         Row data = (Row)this.sparkSession().read().parquet(dataPath).select("boundaries", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"predictions", "isotonic"}))).head();
         double[] boundaries = (double[])((IterableOnceOps)data.getAs(0)).toArray(scala.reflect.ClassTag..MODULE$.Double());
         double[] predictions = (double[])((IterableOnceOps)data.getAs(1)).toArray(scala.reflect.ClassTag..MODULE$.Double());
         boolean isotonic = data.getBoolean(2);
         IsotonicRegressionModel model = new IsotonicRegressionModel(metadata.uid(), new org.apache.spark.mllib.regression.IsotonicRegressionModel(boundaries, predictions, isotonic));
         metadata.getAndSetParams(model, metadata.getAndSetParams$default$2());
         return model;
      }

      public IsotonicRegressionModelReader() {
      }
   }
}
