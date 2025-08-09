package org.apache.spark.ml.feature;

import java.io.IOException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.LongParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasInputCol;
import org.apache.spark.ml.param.shared.HasMaxIter;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.param.shared.HasSeed;
import org.apache.spark.ml.param.shared.HasStepSize;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.DefaultParamsReader$;
import org.apache.spark.ml.util.DefaultParamsWriter$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLImplicits;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.SparkSession.;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.types.StructType;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Predef;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.collection.ArrayOps;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.immutable.ArraySeq;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\r]d\u0001B A\u0001-C\u0001b\u0017\u0001\u0003\u0006\u0004%\t\u0005\u0018\u0005\tg\u0002\u0011\t\u0011)A\u0005;\"AQ\u000f\u0001BC\u0002\u0013%a\u000f\u0003\u0005~\u0001\t\u0005\t\u0015!\u0003x\u0011!\t9\u0001\u0001C\u0001\u0005\u0006%\u0001\u0002CA\u0004\u0001\u0011\u0005!)!\u0005\t\u0015\u0005M\u0001\u0001#b\u0001\n\u0003\t)\u0002C\u0004\u0002B\u0001!\t!a\u0011\t\u000f\u0005\u0005\u0003\u0001\"\u0001\u0002V!9\u0011q\u000e\u0001\u0005\u0002\u0005E\u0004bBA8\u0001\u0011\u0005\u0011q\u0012\u0005\b\u0003/\u0003A\u0011AAM\u0011\u001d\t\u0019\u000b\u0001C\u0001\u0003KCq!a+\u0001\t\u0003\ni\u000bC\u0004\u0002V\u0002!\t%a6\t\u000f\u0005-\b\u0001\"\u0011\u0002n\"9!Q\u0001\u0001\u0005B\t\u001d\u0001b\u0002B\u000b\u0001\u0011\u0005#qC\u0004\b\u0005C\u0001\u0005\u0012\u0001B\u0012\r\u0019y\u0004\t#\u0001\u0003&!9\u0011q\u0001\u000b\u0005\u0002\t\rca\u0002B#)\u0001#\"q\t\u0005\n\u0003\u000f2\"Q3A\u0005\u0002qC\u0011B!\u0018\u0017\u0005#\u0005\u000b\u0011B/\t\u0015\t}cC!f\u0001\n\u0003\u0011\t\u0007\u0003\u0006\u0003lY\u0011\t\u0012)A\u0005\u0005GBq!a\u0002\u0017\t\u0003\u0011i\u0007C\u0005\u0002lZ\t\t\u0011\"\u0001\u0003x!I!Q\u0010\f\u0012\u0002\u0013\u0005!q\u0010\u0005\n\u0005'3\u0012\u0013!C\u0001\u0005+C\u0011B!'\u0017\u0003\u0003%\tEa'\t\u0013\t\u001df#!A\u0005\u0002\t%\u0006\"\u0003BV-\u0005\u0005I\u0011\u0001BW\u0011%\u0011\u0019LFA\u0001\n\u0003\u0012)\fC\u0005\u0003DZ\t\t\u0011\"\u0001\u0003F\"I!q\u001a\f\u0002\u0002\u0013\u0005#\u0011\u001b\u0005\n\u0005+4\u0012\u0011!C!\u0005/D\u0011B!\u0006\u0017\u0003\u0003%\tE!7\t\u0013\tmg#!A\u0005B\tuwA\u0003Bq)\u0005\u0005\t\u0012\u0001\u000b\u0003d\u001aQ!Q\t\u000b\u0002\u0002#\u0005AC!:\t\u000f\u0005\u001d\u0011\u0006\"\u0001\u0003t\"I!QC\u0015\u0002\u0002\u0013\u0015#\u0011\u001c\u0005\n\u0005kL\u0013\u0011!CA\u0005oD\u0011B!@*\u0003\u0003%\tIa@\t\u0013\r5\u0011&!A\u0005\n\r=aaBB\f)\u0001!2\u0011\u0004\u0005\n\u00077y#\u0011!Q\u0001\nACq!a\u00020\t\u0003\u0019i\u0002C\u0004\u0004$=\"\tf!\n\b\u0011\rEB\u0003#\u0001A\u0007g1\u0001ba\u0006\u0015\u0011\u0003\u00015Q\u0007\u0005\b\u0003\u000f!D\u0011AB\u001c\u0011\u001d\u0019I\u0004\u000eC\u0001\u0007w1aaa\u0014\u0015\t\rE\u0003bBA\u0004o\u0011\u00051\u0011\f\u0005\n\u0007;:$\u0019!C\u0005\u00057C\u0001ba\u00188A\u0003%!Q\u0014\u0005\b\u0007C:D\u0011IB2\u0011\u001d\u00199\u0007\u0006C!\u0007SBqa!\u0019\u0015\t\u0003\u001ai\u0007C\u0005\u0004\u000eQ\t\t\u0011\"\u0003\u0004\u0010\tiqk\u001c:eeY+7-T8eK2T!!\u0011\"\u0002\u000f\u0019,\u0017\r^;sK*\u00111\tR\u0001\u0003[2T!!\u0012$\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u001dC\u0015AB1qC\u000eDWMC\u0001J\u0003\ry'oZ\u0002\u0001'\u0011\u0001AJU+\u0011\u00075s\u0005+D\u0001C\u0013\ty%IA\u0003N_\u0012,G\u000e\u0005\u0002R\u00015\t\u0001\t\u0005\u0002R'&\u0011A\u000b\u0011\u0002\r/>\u0014HM\r,fG\n\u000b7/\u001a\t\u0003-fk\u0011a\u0016\u0006\u00031\n\u000bA!\u001e;jY&\u0011!l\u0016\u0002\u000b\u001b2;&/\u001b;bE2,\u0017aA;jIV\tQ\f\u0005\u0002_O:\u0011q,\u001a\t\u0003A\u000el\u0011!\u0019\u0006\u0003E*\u000ba\u0001\u0010:p_Rt$\"\u00013\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0019\u001c\u0017A\u0002)sK\u0012,g-\u0003\u0002iS\n11\u000b\u001e:j]\u001eT!AZ2)\u0007\u0005Y\u0017\u000f\u0005\u0002m_6\tQN\u0003\u0002o\t\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005Al'!B*j]\u000e,\u0017%\u0001:\u0002\u000bErCG\f\u0019\u0002\tULG\r\t\u0015\u0004\u0005-\f\u0018aC<pe\u00124Vm\u0019;peN,\u0012a\u001e\t\u0003qrl\u0011!\u001f\u0006\u0003\u0003jT!a\u001f#\u0002\u000b5dG.\u001b2\n\u0005}J\u0018\u0001D<pe\u00124Vm\u0019;peN\u0004\u0003F\u0001\u0003\u0000!\u0011\t\t!a\u0001\u000e\u0003\rL1!!\u0002d\u0005%!(/\u00198tS\u0016tG/\u0001\u0004=S:LGO\u0010\u000b\u0006!\u0006-\u0011q\u0002\u0005\u00067\u0016\u0001\r!\u0018\u0015\u0005\u0003\u0017Y\u0017\u000fC\u0003v\u000b\u0001\u0007q\u000fF\u0001Q\u0003)9W\r\u001e,fGR|'o]\u000b\u0003\u0003/\u0001B!!\u0007\u000249!\u00111DA\u0017\u001d\u0011\ti\"!\u000b\u000f\t\u0005}\u0011q\u0005\b\u0005\u0003C\t)CD\u0002a\u0003GI\u0011!S\u0005\u0003\u000f\"K!!\u0012$\n\u0007\u0005-B)A\u0002tc2LA!a\f\u00022\u00059\u0001/Y2lC\u001e,'bAA\u0016\t&!\u0011QGA\u001c\u0005%!\u0015\r^1Ge\u0006lWM\u0003\u0003\u00020\u0005E\u0002\u0006B\u0004l\u0003w\t#!!\u0010\u0002\u000bErSG\f\u0019)\u0005\u001dy\u0018\u0001\u00044j]\u0012\u001c\u0016P\\8os6\u001cHCBA\f\u0003\u000b\nI\u0005\u0003\u0004\u0002H!\u0001\r!X\u0001\u0005o>\u0014H\rC\u0004\u0002L!\u0001\r!!\u0014\u0002\u00079,X\u000e\u0005\u0003\u0002\u0002\u0005=\u0013bAA)G\n\u0019\u0011J\u001c;)\t!Y\u00171\b\u000b\u0007\u0003/\t9&a\u001a\t\u000f\u0005e\u0013\u00021\u0001\u0002\\\u0005\u0019a/Z2\u0011\t\u0005u\u00131M\u0007\u0003\u0003?R1!!\u0019C\u0003\u0019a\u0017N\\1mO&!\u0011QMA0\u0005\u00191Vm\u0019;pe\"9\u00111J\u0005A\u0002\u00055\u0003\u0006B\u0005l\u0003W\n#!!\u001c\u0002\u000bIr\u0003G\f\u0019\u0002#\u0019Lg\u000eZ*z]>t\u00170\\:BeJ\f\u0017\u0010\u0006\u0004\u0002t\u0005\u0015\u0015q\u0011\t\u0007\u0003\u0003\t)(!\u001f\n\u0007\u0005]4MA\u0003BeJ\f\u0017\u0010E\u0004\u0002\u0002\u0005mT,a \n\u0007\u0005u4M\u0001\u0004UkBdWM\r\t\u0005\u0003\u0003\t\t)C\u0002\u0002\u0004\u000e\u0014a\u0001R8vE2,\u0007bBA-\u0015\u0001\u0007\u00111\f\u0005\b\u0003\u0017R\u0001\u0019AA'Q\u0011Q1.a#\"\u0005\u00055\u0015!\u0002\u001a/e9\u0002DCBA:\u0003#\u000b\u0019\n\u0003\u0004\u0002H-\u0001\r!\u0018\u0005\b\u0003\u0017Z\u0001\u0019AA'Q\u0011Y1.a#\u0002\u0017M,G/\u00138qkR\u001cu\u000e\u001c\u000b\u0005\u00037\u000bi*D\u0001\u0001\u0011\u0019\ty\n\u0004a\u0001;\u0006)a/\u00197vK\"\u001aAb[9\u0002\u0019M,GoT;uaV$8i\u001c7\u0015\t\u0005m\u0015q\u0015\u0005\u0007\u0003?k\u0001\u0019A/)\u00075Y\u0017/A\u0005ue\u0006t7OZ8s[R!\u0011qCAX\u0011\u001d\t\tL\u0004a\u0001\u0003g\u000bq\u0001Z1uCN,G\u000f\r\u0003\u00026\u0006\u0005\u0007CBA\\\u0003s\u000bi,\u0004\u0002\u00022%!\u00111XA\u0019\u0005\u001d!\u0015\r^1tKR\u0004B!a0\u0002B2\u0001A\u0001DAb\u0003_\u000b\t\u0011!A\u0003\u0002\u0005\u0015'aA0%eE!\u0011qYAg!\u0011\t\t!!3\n\u0007\u0005-7MA\u0004O_RD\u0017N\\4\u0011\t\u0005\u0005\u0011qZ\u0005\u0004\u0003#\u001c'aA!os\"\"ab[A6\u0003=!(/\u00198tM>\u0014XnU2iK6\fG\u0003BAm\u0003K\u0004B!a7\u0002b6\u0011\u0011Q\u001c\u0006\u0005\u0003?\f\t$A\u0003usB,7/\u0003\u0003\u0002d\u0006u'AC*ueV\u001cG\u000fV=qK\"9\u0011q]\bA\u0002\u0005e\u0017AB:dQ\u0016l\u0017\rK\u0002\u0010WF\fAaY8qsR\u0019\u0001+a<\t\u000f\u0005E\b\u00031\u0001\u0002t\u0006)Q\r\u001f;sCB!\u0011Q_A~\u001b\t\t9PC\u0002\u0002z\n\u000bQ\u0001]1sC6LA!!@\u0002x\nA\u0001+\u0019:b[6\u000b\u0007\u000f\u000b\u0003\u0011W\n\u0005\u0011E\u0001B\u0002\u0003\u0015\td\u0006\u000e\u00182\u0003\u00159(/\u001b;f+\t\u0011I\u0001E\u0002W\u0005\u0017I1A!\u0004X\u0005!iEj\u0016:ji\u0016\u0014\b\u0006B\tl\u0005#\t#Aa\u0005\u0002\u000bErcG\f\u0019\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012!\u0018\u0015\u0005%-\u0014Y\"\t\u0002\u0003\u001e\u0005)1G\f\u0019/a!\u001a\u0001a[9\u0002\u001b]{'\u000f\u001a\u001aWK\u000elu\u000eZ3m!\t\tFcE\u0004\u0015\u0005O\u0011iCa\r\u0011\t\u0005\u0005!\u0011F\u0005\u0004\u0005W\u0019'AB!osJ+g\r\u0005\u0003W\u0005_\u0001\u0016b\u0001B\u0019/\nQQ\n\u0014*fC\u0012\f'\r\\3\u0011\t\tU\"qH\u0007\u0003\u0005oQAA!\u000f\u0003<\u0005\u0011\u0011n\u001c\u0006\u0003\u0005{\tAA[1wC&!!\u0011\tB\u001c\u00051\u0019VM]5bY&T\u0018M\u00197f)\t\u0011\u0019C\u0001\u0003ECR\f7c\u0002\f\u0003(\t%#q\n\t\u0005\u0003\u0003\u0011Y%C\u0002\u0003N\r\u0014q\u0001\u0015:pIV\u001cG\u000f\u0005\u0003\u0003R\tec\u0002\u0002B*\u0005/r1\u0001\u0019B+\u0013\u0005!\u0017bAA\u0018G&!!\u0011\tB.\u0015\r\tycY\u0001\u0006o>\u0014H\rI\u0001\u0007m\u0016\u001cGo\u001c:\u0016\u0005\t\r\u0004CBA\u0001\u0003k\u0012)\u0007\u0005\u0003\u0002\u0002\t\u001d\u0014b\u0001B5G\n)a\t\\8bi\u00069a/Z2u_J\u0004CC\u0002B8\u0005g\u0012)\bE\u0002\u0003rYi\u0011\u0001\u0006\u0005\u0007\u0003\u000fZ\u0002\u0019A/\t\u000f\t}3\u00041\u0001\u0003dQ1!q\u000eB=\u0005wB\u0001\"a\u0012\u001d!\u0003\u0005\r!\u0018\u0005\n\u0005?b\u0002\u0013!a\u0001\u0005G\nabY8qs\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0003\u0002*\u001aQLa!,\u0005\t\u0015\u0005\u0003\u0002BD\u0005\u001fk!A!#\u000b\t\t-%QR\u0001\nk:\u001c\u0007.Z2lK\u0012T!A\\2\n\t\tE%\u0011\u0012\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0003\u0005/SCAa\u0019\u0003\u0004\u0006i\u0001O]8ek\u000e$\bK]3gSb,\"A!(\u0011\t\t}%QU\u0007\u0003\u0005CSAAa)\u0003<\u0005!A.\u00198h\u0013\rA'\u0011U\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0003\u0003\u001b\na\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0002N\n=\u0006\"\u0003BYC\u0005\u0005\t\u0019AA'\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011!q\u0017\t\u0007\u0005s\u0013y,!4\u000e\u0005\tm&b\u0001B_G\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\t\u0005'1\u0018\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0003H\n5\u0007\u0003BA\u0001\u0005\u0013L1Aa3d\u0005\u001d\u0011un\u001c7fC:D\u0011B!-$\u0003\u0003\u0005\r!!4\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0005;\u0013\u0019\u000eC\u0005\u00032\u0012\n\t\u00111\u0001\u0002N\u0005A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002NQ\u0011!QT\u0001\u0007KF,\u0018\r\\:\u0015\t\t\u001d'q\u001c\u0005\n\u0005c;\u0013\u0011!a\u0001\u0003\u001b\fA\u0001R1uCB\u0019!\u0011O\u0015\u0014\u000b%\u00129Oa\r\u0011\u0013\t%(q^/\u0003d\t=TB\u0001Bv\u0015\r\u0011ioY\u0001\beVtG/[7f\u0013\u0011\u0011\tPa;\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t'\u0007\u0006\u0002\u0003d\u0006)\u0011\r\u001d9msR1!q\u000eB}\u0005wDa!a\u0012-\u0001\u0004i\u0006b\u0002B0Y\u0001\u0007!1M\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\u0019\ta!\u0003\u0011\r\u0005\u000511AB\u0004\u0013\r\u0019)a\u0019\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u000f\u0005\u0005\u00111P/\u0003d!I11B\u0017\u0002\u0002\u0003\u0007!qN\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAB\t!\u0011\u0011yja\u0005\n\t\rU!\u0011\u0015\u0002\u0007\u001f\nTWm\u0019;\u0003']{'\u000f\u001a\u001aWK\u000elu\u000eZ3m/JLG/\u001a:\u0014\u0007=\u0012I!\u0001\u0005j]N$\u0018M\\2f)\u0011\u0019yb!\t\u0011\u0007\tEt\u0006\u0003\u0004\u0004\u001cE\u0002\r\u0001U\u0001\tg\u00064X-S7qYR!1qEB\u0017!\u0011\t\ta!\u000b\n\u0007\r-2M\u0001\u0003V]&$\bBBB\u0018e\u0001\u0007Q,\u0001\u0003qCRD\u0017aE,pe\u0012\u0014d+Z2N_\u0012,Gn\u0016:ji\u0016\u0014\bc\u0001B9iM\u0019AGa\n\u0015\u0005\rM\u0012aG2bY\u000e,H.\u0019;f\u001dVl'-\u001a:PMB\u000b'\u000f^5uS>t7\u000f\u0006\u0005\u0002N\ru2qIB&\u0011\u001d\u0019yD\u000ea\u0001\u0007\u0003\n\u0011CY;gM\u0016\u00148+\u001b>f\u0013:\u0014\u0015\u0010^3t!\u0011\t\taa\u0011\n\u0007\r\u00153M\u0001\u0003M_:<\u0007bBB%m\u0001\u0007\u0011QJ\u0001\t]Vlwk\u001c:eg\"91Q\n\u001cA\u0002\u00055\u0013A\u0003<fGR|'oU5{K\n\u0019rk\u001c:eeY+7-T8eK2\u0014V-\u00193feN\u0019qga\u0015\u0011\tY\u001b)\u0006U\u0005\u0004\u0007/:&\u0001C'M%\u0016\fG-\u001a:\u0015\u0005\rm\u0003c\u0001B9o\u0005I1\r\\1tg:\u000bW.Z\u0001\u000bG2\f7o\u001d(b[\u0016\u0004\u0013\u0001\u00027pC\u0012$2\u0001UB3\u0011\u0019\u0019yc\u000fa\u0001;\u0006!!/Z1e+\t\u0019\u0019\u0006\u000b\u0003=W\nEAc\u0001)\u0004p!11qF\u001fA\u0002uCC!P6\u0003\u0012!\"Ac\u001bB\tQ\u0011\u00192N!\u0005"
)
public class Word2VecModel extends Model implements Word2VecBase, MLWritable {
   private transient Dataset getVectors;
   private final String uid;
   private final transient org.apache.spark.mllib.feature.Word2VecModel org$apache$spark$ml$feature$Word2VecModel$$wordVectors;
   private IntParam vectorSize;
   private IntParam windowSize;
   private IntParam numPartitions;
   private IntParam minCount;
   private IntParam maxSentenceLength;
   private LongParam seed;
   private DoubleParam stepSize;
   private IntParam maxIter;
   private Param outputCol;
   private Param inputCol;
   private transient volatile boolean bitmap$trans$0;

   public static Word2VecModel load(final String path) {
      return Word2VecModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return Word2VecModel$.MODULE$.read();
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public int getVectorSize() {
      return Word2VecBase.getVectorSize$(this);
   }

   public int getWindowSize() {
      return Word2VecBase.getWindowSize$(this);
   }

   public int getNumPartitions() {
      return Word2VecBase.getNumPartitions$(this);
   }

   public int getMinCount() {
      return Word2VecBase.getMinCount$(this);
   }

   public int getMaxSentenceLength() {
      return Word2VecBase.getMaxSentenceLength$(this);
   }

   public StructType validateAndTransformSchema(final StructType schema) {
      return Word2VecBase.validateAndTransformSchema$(this, schema);
   }

   public final long getSeed() {
      return HasSeed.getSeed$(this);
   }

   public final double getStepSize() {
      return HasStepSize.getStepSize$(this);
   }

   public final int getMaxIter() {
      return HasMaxIter.getMaxIter$(this);
   }

   public final String getOutputCol() {
      return HasOutputCol.getOutputCol$(this);
   }

   public final String getInputCol() {
      return HasInputCol.getInputCol$(this);
   }

   public final IntParam vectorSize() {
      return this.vectorSize;
   }

   public final IntParam windowSize() {
      return this.windowSize;
   }

   public final IntParam numPartitions() {
      return this.numPartitions;
   }

   public final IntParam minCount() {
      return this.minCount;
   }

   public final IntParam maxSentenceLength() {
      return this.maxSentenceLength;
   }

   public final void org$apache$spark$ml$feature$Word2VecBase$_setter_$vectorSize_$eq(final IntParam x$1) {
      this.vectorSize = x$1;
   }

   public final void org$apache$spark$ml$feature$Word2VecBase$_setter_$windowSize_$eq(final IntParam x$1) {
      this.windowSize = x$1;
   }

   public final void org$apache$spark$ml$feature$Word2VecBase$_setter_$numPartitions_$eq(final IntParam x$1) {
      this.numPartitions = x$1;
   }

   public final void org$apache$spark$ml$feature$Word2VecBase$_setter_$minCount_$eq(final IntParam x$1) {
      this.minCount = x$1;
   }

   public final void org$apache$spark$ml$feature$Word2VecBase$_setter_$maxSentenceLength_$eq(final IntParam x$1) {
      this.maxSentenceLength = x$1;
   }

   public final LongParam seed() {
      return this.seed;
   }

   public final void org$apache$spark$ml$param$shared$HasSeed$_setter_$seed_$eq(final LongParam x$1) {
      this.seed = x$1;
   }

   public DoubleParam stepSize() {
      return this.stepSize;
   }

   public void org$apache$spark$ml$param$shared$HasStepSize$_setter_$stepSize_$eq(final DoubleParam x$1) {
      this.stepSize = x$1;
   }

   public final IntParam maxIter() {
      return this.maxIter;
   }

   public final void org$apache$spark$ml$param$shared$HasMaxIter$_setter_$maxIter_$eq(final IntParam x$1) {
      this.maxIter = x$1;
   }

   public final Param outputCol() {
      return this.outputCol;
   }

   public final void org$apache$spark$ml$param$shared$HasOutputCol$_setter_$outputCol_$eq(final Param x$1) {
      this.outputCol = x$1;
   }

   public final Param inputCol() {
      return this.inputCol;
   }

   public final void org$apache$spark$ml$param$shared$HasInputCol$_setter_$inputCol_$eq(final Param x$1) {
      this.inputCol = x$1;
   }

   public String uid() {
      return this.uid;
   }

   public org.apache.spark.mllib.feature.Word2VecModel org$apache$spark$ml$feature$Word2VecModel$$wordVectors() {
      return this.org$apache$spark$ml$feature$Word2VecModel$$wordVectors;
   }

   private Dataset getVectors$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$trans$0) {
            SparkSession spark = .MODULE$.builder().getOrCreate();
            Map wordVec = (Map)this.org$apache$spark$ml$feature$Word2VecModel$$wordVectors().getVectors().transform((x$2, vec) -> org.apache.spark.ml.linalg.Vectors..MODULE$.dense((double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.floatArrayOps(vec), (JFunction1.mcDF.sp)(x$3) -> (double)x$3, scala.reflect.ClassTag..MODULE$.Double())));
            Seq var10002 = wordVec.toSeq();
            JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
            JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(Word2VecModel.class.getClassLoader());
            this.getVectors = spark.createDataFrame(var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, null.new $typecreator1$1())).toDF(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"word", "vector"})));
            this.bitmap$trans$0 = true;
         }
      } catch (Throwable var7) {
         throw var7;
      }

      return this.getVectors;
   }

   public Dataset getVectors() {
      return !this.bitmap$trans$0 ? this.getVectors$lzycompute() : this.getVectors;
   }

   public Dataset findSynonyms(final String word, final int num) {
      SparkSession spark = .MODULE$.builder().getOrCreate();
      ArraySeq var10001 = org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.findSynonymsArray(word, num)).toImmutableArraySeq();
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(Word2VecModel.class.getClassLoader());

      final class $typecreator1$2 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple2"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$)));
         }

         public $typecreator1$2() {
         }
      }

      return spark.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$2())).toDF(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"word", "similarity"})));
   }

   public Dataset findSynonyms(final Vector vec, final int num) {
      SparkSession spark = .MODULE$.builder().getOrCreate();
      ArraySeq var10001 = org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.findSynonymsArray(vec, num)).toImmutableArraySeq();
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(Word2VecModel.class.getClassLoader());

      final class $typecreator1$3 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple2"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$)));
         }

         public $typecreator1$3() {
         }
      }

      return spark.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$3())).toDF(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"word", "similarity"})));
   }

   public Tuple2[] findSynonymsArray(final Vector vec, final int num) {
      return this.org$apache$spark$ml$feature$Word2VecModel$$wordVectors().findSynonyms(vec.toArray(), num, scala.None..MODULE$);
   }

   public Tuple2[] findSynonymsArray(final String word, final int num) {
      return this.org$apache$spark$ml$feature$Word2VecModel$$wordVectors().findSynonyms(word, num);
   }

   public Word2VecModel setInputCol(final String value) {
      return (Word2VecModel)this.set(this.inputCol(), value);
   }

   public Word2VecModel setOutputCol(final String value) {
      return (Word2VecModel)this.set(this.outputCol(), value);
   }

   public Dataset transform(final Dataset dataset) {
      StructType outputSchema = this.transformSchema(dataset.schema(), true);
      Broadcast bcModel = dataset.sparkSession().sparkContext().broadcast(this.org$apache$spark$ml$feature$Word2VecModel$$wordVectors(), scala.reflect.ClassTag..MODULE$.apply(org.apache.spark.mllib.feature.Word2VecModel.class));
      int size = BoxesRunTime.unboxToInt(this.$(this.vectorSize()));
      Vector emptyVec = org.apache.spark.ml.linalg.Vectors..MODULE$.sparse(size, scala.Array..MODULE$.emptyIntArray(), scala.Array..MODULE$.emptyDoubleArray());
      functions var10000 = org.apache.spark.sql.functions..MODULE$;
      Function1 var10001 = (sentence) -> {
         if (sentence.isEmpty()) {
            return emptyVec;
         } else {
            Map wordIndices = ((org.apache.spark.mllib.feature.Word2VecModel)bcModel.value()).wordIndex();
            float[] wordVectors = ((org.apache.spark.mllib.feature.Word2VecModel)bcModel.value()).wordVectors();
            double[] array = (double[])scala.Array..MODULE$.ofDim(size, scala.reflect.ClassTag..MODULE$.Double());
            IntRef count = IntRef.create(0);
            sentence.foreach((word) -> {
               $anonfun$transform$2(wordIndices, size, array, wordVectors, count, word);
               return BoxedUnit.UNIT;
            });
            Vector vec = org.apache.spark.ml.linalg.Vectors..MODULE$.dense(array);
            org.apache.spark.ml.linalg.BLAS..MODULE$.scal((double)1.0F / (double)count.elem, vec);
            return vec;
         }
      };
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(Word2VecModel.class.getClassLoader());

      final class $typecreator1$4 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
         }

         public $typecreator1$4() {
         }
      }

      TypeTags.TypeTag var10002 = ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$4());
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(Word2VecModel.class.getClassLoader());

      final class $typecreator2$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().thisPrefix($m$untyped.RootClass()), $m$untyped.staticPackage("scala")), $m$untyped.staticModule("scala.package")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.package").asModule().moduleClass(), "Seq"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$));
         }

         public $typecreator2$1() {
         }
      }

      UserDefinedFunction transformer = var10000.udf(var10001, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1()));
      return dataset.withColumn((String)this.$(this.outputCol()), transformer.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.inputCol()))}))), outputSchema.apply((String)this.$(this.outputCol())).metadata());
   }

   public StructType transformSchema(final StructType schema) {
      StructType outputSchema = this.validateAndTransformSchema(schema);
      if (scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.outputCol())))) {
         outputSchema = SchemaUtils$.MODULE$.updateAttributeGroupSize(outputSchema, (String)this.$(this.outputCol()), BoxesRunTime.unboxToInt(this.$(this.vectorSize())));
      }

      return outputSchema;
   }

   public Word2VecModel copy(final ParamMap extra) {
      Word2VecModel copied = new Word2VecModel(this.uid(), this.org$apache$spark$ml$feature$Word2VecModel$$wordVectors());
      return (Word2VecModel)((Model)this.copyValues(copied, extra)).setParent(this.parent());
   }

   public MLWriter write() {
      return new Word2VecModelWriter(this);
   }

   public String toString() {
      String var10000 = this.uid();
      return "Word2VecModel: uid=" + var10000 + ", numWords=" + this.org$apache$spark$ml$feature$Word2VecModel$$wordVectors().wordIndex().size() + ", vectorSize=" + this.$(this.vectorSize());
   }

   // $FF: synthetic method
   public static final void $anonfun$transform$2(final Map wordIndices$1, final int size$1, final double[] array$1, final float[] wordVectors$1, final IntRef count$1, final String word) {
      wordIndices$1.get(word).foreach((JFunction1.mcVI.sp)(index) -> {
         int offset = index * size$1;

         for(int i = 0; i < size$1; ++i) {
            array$1[i] += (double)wordVectors$1[offset + i];
         }

      });
      ++count$1.elem;
   }

   public Word2VecModel(final String uid, final org.apache.spark.mllib.feature.Word2VecModel wordVectors) {
      this.uid = uid;
      this.org$apache$spark$ml$feature$Word2VecModel$$wordVectors = wordVectors;
      HasInputCol.$init$(this);
      HasOutputCol.$init$(this);
      HasMaxIter.$init$(this);
      HasStepSize.$init$(this);
      HasSeed.$init$(this);
      Word2VecBase.$init$(this);
      MLWritable.$init$(this);
      Statics.releaseFence();
   }

   public Word2VecModel() {
      this("", (org.apache.spark.mllib.feature.Word2VecModel)null);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public final class $typecreator1$1 extends TypeCreator {
      public Types.TypeApi apply(final Mirror $m$untyped) {
         Universe $u = $m$untyped.universe();
         return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple2"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$), new scala.collection.immutable..colon.colon($m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$)));
      }
   }

   public static class Data implements Product, Serializable {
      private final String word;
      private final float[] vector;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String word() {
         return this.word;
      }

      public float[] vector() {
         return this.vector;
      }

      public Data copy(final String word, final float[] vector) {
         return new Data(word, vector);
      }

      public String copy$default$1() {
         return this.word();
      }

      public float[] copy$default$2() {
         return this.vector();
      }

      public String productPrefix() {
         return "Data";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return this.word();
            }
            case 1 -> {
               return this.vector();
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
               return "word";
            }
            case 1 -> {
               return "vector";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var6;
         if (this != x$1) {
            label49: {
               if (x$1 instanceof Data) {
                  label42: {
                     Data var4 = (Data)x$1;
                     String var10000 = this.word();
                     String var5 = var4.word();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label42;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label42;
                     }

                     if (this.vector() == var4.vector() && var4.canEqual(this)) {
                        break label49;
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

      public Data(final String word, final float[] vector) {
         this.word = word;
         this.vector = vector;
         Product.$init$(this);
      }
   }

   public static class Data$ extends AbstractFunction2 implements Serializable {
      public static final Data$ MODULE$ = new Data$();

      public final String toString() {
         return "Data";
      }

      public Data apply(final String word, final float[] vector) {
         return new Data(word, vector);
      }

      public Option unapply(final Data x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.word(), x$0.vector())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Data$.class);
      }
   }

   public static class Word2VecModelWriter extends MLWriter {
      private final Word2VecModel instance;

      public void saveImpl(final String path) {
         DefaultParamsWriter$.MODULE$.saveMetadata(this.instance, path, this.sparkSession());
         Map wordVectors = this.instance.org$apache$spark$ml$feature$Word2VecModel$$wordVectors().getVectors();
         String dataPath = (new Path(path, "data")).toString();
         long bufferSizeInBytes = org.apache.spark.util.Utils..MODULE$.byteStringAsBytes(this.sc().conf().get(org.apache.spark.internal.config.Kryo..MODULE$.KRYO_SERIALIZER_MAX_BUFFER_SIZE().key(), "64m"));
         int numPartitions = Word2VecModel.Word2VecModelWriter$.MODULE$.calculateNumberOfPartitions(bufferSizeInBytes, this.instance.org$apache$spark$ml$feature$Word2VecModel$$wordVectors().wordIndex().size(), this.instance.getVectorSize());
         SparkSession spark = this.sparkSession();
         Seq var10001 = wordVectors.toSeq();
         SQLImplicits var10002 = spark.implicits();
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(Word2VecModelWriter.class.getClassLoader());

         final class $typecreator5$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple2"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Array"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Float").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$)));
            }

            public $typecreator5$1() {
            }
         }

         Dataset var10000 = spark.createDataset(var10001, var10002.newProductEncoder(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator5$1()))).repartition(numPartitions);
         Function1 var12 = (x0$1) -> {
            if (x0$1 != null) {
               String word = (String)x0$1._1();
               float[] vector = (float[])x0$1._2();
               return new Data(word, vector);
            } else {
               throw new MatchError(x0$1);
            }
         };
         var10002 = spark.implicits();
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(Word2VecModelWriter.class.getClassLoader());

         final class $typecreator10$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.ml.feature.Word2VecModel.Data").asType().toTypeConstructor();
            }

            public $typecreator10$1() {
            }
         }

         var10000.map(var12, var10002.newProductEncoder(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator10$1()))).toDF().write().parquet(dataPath);
      }

      public Word2VecModelWriter(final Word2VecModel instance) {
         this.instance = instance;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class Word2VecModelWriter$ {
      public static final Word2VecModelWriter$ MODULE$ = new Word2VecModelWriter$();

      public int calculateNumberOfPartitions(final long bufferSizeInBytes, final int numWords, final int vectorSize) {
         long floatSize = 4L;
         int averageWordSize = 15;
         long approximateSizeInBytes = (floatSize * (long)vectorSize + (long)averageWordSize) * (long)numWords;
         long numPartitions = approximateSizeInBytes / bufferSizeInBytes + 1L;
         scala.Predef..MODULE$.require((double)numPartitions < (double)1.0E9F, () -> "Word2VecModel calculated that it needs " + numPartitions + " partitions to save this model, which is too large.  Try increasing spark.kryoserializer.buffer.max so that Word2VecModel can use fewer partitions.");
         return (int)numPartitions;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   private static class Word2VecModelReader extends MLReader {
      private final String className = Word2VecModel.class.getName();

      private String className() {
         return this.className;
      }

      public Word2VecModel load(final String path) {
         SparkSession spark = this.sparkSession();
         DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, this.sparkSession(), this.className());
         Tuple2 var6 = org.apache.spark.util.VersionUtils..MODULE$.majorMinorVersion(metadata.sparkVersion());
         if (var6 == null) {
            throw new MatchError(var6);
         } else {
            int major = var6._1$mcI$sp();
            int minor = var6._2$mcI$sp();
            Tuple2.mcII.sp var5 = new Tuple2.mcII.sp(major, minor);
            int major = ((Tuple2)var5)._1$mcI$sp();
            int minor = ((Tuple2)var5)._2$mcI$sp();
            String dataPath = (new Path(path, "data")).toString();
            org.apache.spark.mllib.feature.Word2VecModel var10000;
            if (major >= 2 && (major != 2 || minor >= 2)) {
               Predef var20 = scala.Predef..MODULE$;
               ArrayOps var10001 = scala.collection.ArrayOps..MODULE$;
               Predef var10002 = scala.Predef..MODULE$;
               Dataset var10003 = spark.read().parquet(dataPath);
               SQLImplicits var10004 = spark.implicits();
               JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
               JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(Word2VecModelReader.class.getClassLoader());

               final class $typecreator5$2 extends TypeCreator {
                  public Types.TypeApi apply(final Mirror $m$untyped) {
                     Universe $u = $m$untyped.universe();
                     return $m$untyped.staticClass("org.apache.spark.ml.feature.Word2VecModel.Data").asType().toTypeConstructor();
                  }

                  public $typecreator5$2() {
                  }
               }

               Map wordVectorsMap = var20.wrapRefArray(var10001.map$extension(var10002.refArrayOps(var10003.as(var10004.newProductEncoder(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator5$2()))).collect()), (wordVector) -> new Tuple2(wordVector.word(), wordVector.vector()), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))).toMap(scala..less.colon.less..MODULE$.refl());
               var10000 = new org.apache.spark.mllib.feature.Word2VecModel(wordVectorsMap);
            } else {
               Row data = (Row)spark.read().parquet(dataPath).select("wordIndex", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"wordVectors"}))).head();
               Map wordIndex = (Map)data.getAs(0);
               float[] wordVectors = (float[])((IterableOnceOps)data.getAs(1)).toArray(scala.reflect.ClassTag..MODULE$.Float());
               var10000 = new org.apache.spark.mllib.feature.Word2VecModel(wordIndex, wordVectors);
            }

            org.apache.spark.mllib.feature.Word2VecModel oldModel = var10000;
            Word2VecModel model = new Word2VecModel(metadata.uid(), oldModel);
            metadata.getAndSetParams(model, metadata.getAndSetParams$default$2());
            return model;
         }
      }

      public Word2VecModelReader() {
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
