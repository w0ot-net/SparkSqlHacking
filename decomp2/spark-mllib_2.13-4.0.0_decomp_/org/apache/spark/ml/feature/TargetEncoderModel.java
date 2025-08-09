package org.apache.spark.ml.feature;

import java.io.IOException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.SparkException;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.attribute.NominalAttribute$;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.StringArrayParam;
import org.apache.spark.ml.param.shared.HasHandleInvalid;
import org.apache.spark.ml.param.shared.HasInputCol;
import org.apache.spark.ml.param.shared.HasInputCols;
import org.apache.spark.ml.param.shared.HasLabelCol;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.param.shared.HasOutputCols;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.DefaultParamsReader$;
import org.apache.spark.ml.util.DefaultParamsWriter$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.MatchError;
import scala.Option;
import scala.Predef;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Tuple4;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.ArrayOps.;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.AbstractFunction4;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\rUb\u0001B A\u0001-C\u0001b\u0017\u0001\u0003\u0006\u0004%\t\u0005\u0018\u0005\tg\u0002\u0011\t\u0011)A\u0005;\"IQ\u000f\u0001BC\u0002\u0013\u0005!I\u001e\u0005\n\u0003\u0017\u0001!\u0011!Q\u0001\n]D\u0001\"a\u0004\u0001\t\u0003\u0011\u0015\u0011\u0003\u0005\t\u0003\u001f\u0001A\u0011\u0001\"\u0002\u001c!9\u0011Q\u0004\u0001\u0005\u0002\u0005}\u0001bBA\u0015\u0001\u0011\u0005\u00111\u0006\u0005\b\u0003c\u0001A\u0011AA\u001a\u0011\u001d\ti\u0004\u0001C\u0001\u0003\u007fAq!!\u0012\u0001\t\u0003\t9\u0005C\u0004\u0002N\u0001!\t!a\u0014\t\u000f\u0005U\u0003\u0001\"\u0011\u0002X!9\u0011q\u000e\u0001\u0005B\u0005E\u0004bBA\\\u0001\u0011\u0005\u0013\u0011\u0018\u0005\b\u0003\u001b\u0004A\u0011IAh\u0011\u001d\tI\u000e\u0001C!\u00037<q!!9A\u0011\u0003\t\u0019O\u0002\u0004@\u0001\"\u0005\u0011Q\u001d\u0005\b\u0003\u001f\u0019B\u0011\u0001B\u0002\r\u001d\u0011)a\u0005\u0001\u0014\u0005\u000fA\u0011B!\u0003\u0016\u0005\u0003\u0005\u000b\u0011\u0002)\t\u000f\u0005=Q\u0003\"\u0001\u0003\f\u00191!1C\u000bE\u0005+A!Ba\u000b\u0019\u0005+\u0007I\u0011\u0001B\u0017\u0011)\u0011)\u0004\u0007B\tB\u0003%!q\u0006\u0005\u000b\u0005oA\"Q3A\u0005\u0002\te\u0002B\u0003B\u001f1\tE\t\u0015!\u0003\u0003<!Q!q\b\r\u0003\u0016\u0004%\tA!\u000f\t\u0015\t\u0005\u0003D!E!\u0002\u0013\u0011Y\u0004C\u0005v1\tU\r\u0011\"\u0001\u0003:!Q\u00111\u0002\r\u0003\u0012\u0003\u0006IAa\u000f\t\u000f\u0005=\u0001\u0004\"\u0001\u0003D!I\u0011q\u0017\r\u0002\u0002\u0013\u0005!\u0011\u000b\u0005\n\u00057B\u0012\u0013!C\u0001\u0005;B\u0011B!\u001d\u0019#\u0003%\tAa\u001d\t\u0013\t]\u0004$%A\u0005\u0002\tM\u0004\"\u0003B=1E\u0005I\u0011\u0001B:\u0011%\u0011Y\bGA\u0001\n\u0003\u0012i\bC\u0005\u0003\nb\t\t\u0011\"\u0001\u0003.!I!1\u0012\r\u0002\u0002\u0013\u0005!Q\u0012\u0005\n\u0005'C\u0012\u0011!C!\u0005+C\u0011Ba)\u0019\u0003\u0003%\tA!*\t\u0013\t=\u0006$!A\u0005B\tE\u0006\"\u0003B[1\u0005\u0005I\u0011\tB\\\u0011%\tI\u000eGA\u0001\n\u0003\u0012I\fC\u0005\u0003<b\t\t\u0011\"\u0011\u0003>\u001eI!\u0011Y\u000b\u0002\u0002#%!1\u0019\u0004\n\u0005')\u0012\u0011!E\u0005\u0005\u000bDq!a\u00042\t\u0003\u0011\u0019\u000eC\u0005\u0002ZF\n\t\u0011\"\u0012\u0003:\"I!Q[\u0019\u0002\u0002\u0013\u0005%q\u001b\u0005\n\u0005C\f\u0014\u0011!CA\u0005GDqA!>\u0016\t#\u00129P\u0002\u0004\u0004\u0004M!1Q\u0001\u0005\b\u0003\u001f9D\u0011AB\u0007\u0011%\u0019\tb\u000eb\u0001\n\u0013\u0011i\b\u0003\u0005\u0004\u0014]\u0002\u000b\u0011\u0002B@\u0011\u001d\u0019)b\u000eC!\u0007/Aqaa\u0007\u0014\t\u0003\u001ai\u0002C\u0004\u0004\u0016M!\te!\t\t\u0013\r\u001d2#!A\u0005\n\r%\"A\u0005+be\u001e,G/\u00128d_\u0012,'/T8eK2T!!\u0011\"\u0002\u000f\u0019,\u0017\r^;sK*\u00111\tR\u0001\u0003[2T!!\u0012$\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u001dC\u0015AB1qC\u000eDWMC\u0001J\u0003\ry'oZ\u0002\u0001'\u0011\u0001AJU+\u0011\u00075s\u0005+D\u0001C\u0013\ty%IA\u0003N_\u0012,G\u000e\u0005\u0002R\u00015\t\u0001\t\u0005\u0002R'&\u0011A\u000b\u0011\u0002\u0012)\u0006\u0014x-\u001a;F]\u000e|G-\u001a:CCN,\u0007C\u0001,Z\u001b\u00059&B\u0001-C\u0003\u0011)H/\u001b7\n\u0005i;&AC'M/JLG/\u00192mK\u0006\u0019Q/\u001b3\u0016\u0003u\u0003\"AX4\u000f\u0005}+\u0007C\u00011d\u001b\u0005\t'B\u00012K\u0003\u0019a$o\\8u})\tA-A\u0003tG\u0006d\u0017-\u0003\u0002gG\u00061\u0001K]3eK\u001aL!\u0001[5\u0003\rM#(/\u001b8h\u0015\t17\rK\u0002\u0002WF\u0004\"\u0001\\8\u000e\u00035T!A\u001c#\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002q[\n)1+\u001b8dK\u0006\n!/A\u00035]Ar\u0003'\u0001\u0003vS\u0012\u0004\u0003f\u0001\u0002lc\u0006)1\u000f^1ugV\tq\u000fE\u0002ysnl\u0011aY\u0005\u0003u\u000e\u0014Q!\u0011:sCf\u0004RA\u0018?\u007f\u0003\u0007I!!`5\u0003\u00075\u000b\u0007\u000f\u0005\u0002y\u007f&\u0019\u0011\u0011A2\u0003\r\u0011{WO\u00197f!\u0015A\u0018Q\u0001@\u007f\u0013\r\t9a\u0019\u0002\u0007)V\u0004H.\u001a\u001a)\u0007\rY\u0017/\u0001\u0004ti\u0006$8\u000f\t\u0015\u0004\t-\f\u0018A\u0002\u001fj]&$h\bF\u0003Q\u0003'\t9\u0002C\u0003\\\u000b\u0001\u0007Q\f\u000b\u0003\u0002\u0014-\f\b\"B;\u0006\u0001\u00049\b\u0006BA\fWF$\u0012\u0001U\u0001\fg\u0016$\u0018J\u001c9vi\u000e{G\u000e\u0006\u0003\u0002\"\u0005\rR\"\u0001\u0001\t\r\u0005\u0015r\u00011\u0001^\u0003\u00151\u0018\r\\;fQ\r91.]\u0001\rg\u0016$x*\u001e;qkR\u001cu\u000e\u001c\u000b\u0005\u0003C\ti\u0003\u0003\u0004\u0002&!\u0001\r!\u0018\u0015\u0004\u0011-\f\u0018\u0001D:fi&s\u0007/\u001e;D_2\u001cH\u0003BA\u0011\u0003kAq!a\u000e\n\u0001\u0004\tI$\u0001\u0004wC2,Xm\u001d\t\u0004qfl\u0006fA\u0005lc\u0006i1/\u001a;PkR\u0004X\u000f^\"pYN$B!!\t\u0002B!9\u0011q\u0007\u0006A\u0002\u0005e\u0002f\u0001\u0006lc\u0006\u00012/\u001a;IC:$G.Z%om\u0006d\u0017\u000e\u001a\u000b\u0005\u0003C\tI\u0005\u0003\u0004\u0002&-\u0001\r!\u0018\u0015\u0004\u0017-\f\u0018\u0001D:fiNkwn\u001c;iS:<G\u0003BA\u0011\u0003#Ba!!\n\r\u0001\u0004q\bf\u0001\u0007lc\u0006yAO]1og\u001a|'/\\*dQ\u0016l\u0017\r\u0006\u0003\u0002Z\u0005%\u0004\u0003BA.\u0003Kj!!!\u0018\u000b\t\u0005}\u0013\u0011M\u0001\u0006if\u0004Xm\u001d\u0006\u0004\u0003G\"\u0015aA:rY&!\u0011qMA/\u0005)\u0019FO];diRK\b/\u001a\u0005\b\u0003Wj\u0001\u0019AA-\u0003\u0019\u00198\r[3nC\"\u001aQb[9\u0002\u0013Q\u0014\u0018M\\:g_JlG\u0003BA:\u0003#\u0003B!!\u001e\u0002\f:!\u0011qOAD\u001d\u0011\tI(!\"\u000f\t\u0005m\u00141\u0011\b\u0005\u0003{\n\tID\u0002a\u0003\u007fJ\u0011!S\u0005\u0003\u000f\"K!!\u0012$\n\u0007\u0005\rD)\u0003\u0003\u0002\n\u0006\u0005\u0014a\u00029bG.\fw-Z\u0005\u0005\u0003\u001b\u000byIA\u0005ECR\fgI]1nK*!\u0011\u0011RA1\u0011\u001d\t\u0019J\u0004a\u0001\u0003+\u000bq\u0001Z1uCN,G\u000f\r\u0003\u0002\u0018\u0006\r\u0006CBAM\u00037\u000by*\u0004\u0002\u0002b%!\u0011QTA1\u0005\u001d!\u0015\r^1tKR\u0004B!!)\u0002$2\u0001A\u0001DAS\u0003#\u000b\t\u0011!A\u0003\u0002\u0005\u001d&aA0%eE!\u0011\u0011VAX!\rA\u00181V\u0005\u0004\u0003[\u001b'a\u0002(pi\"Lgn\u001a\t\u0004q\u0006E\u0016bAAZG\n\u0019\u0011I\\=)\u00079Y\u0017/\u0001\u0003d_BLHc\u0001)\u0002<\"9\u0011QX\bA\u0002\u0005}\u0016!B3yiJ\f\u0007\u0003BAa\u0003\u000fl!!a1\u000b\u0007\u0005\u0015')A\u0003qCJ\fW.\u0003\u0003\u0002J\u0006\r'\u0001\u0003)be\u0006lW*\u00199)\u0007=Y\u0017/A\u0003xe&$X-\u0006\u0002\u0002RB\u0019a+a5\n\u0007\u0005UwK\u0001\u0005N\u0019^\u0013\u0018\u000e^3sQ\r\u00012.]\u0001\ti>\u001cFO]5oOR\tQ\fK\u0002\u0012WFD3\u0001A6r\u0003I!\u0016M]4fi\u0016s7m\u001c3fe6{G-\u001a7\u0011\u0005E\u001b2cB\n\u0002h\u00065\u00181\u001f\t\u0004q\u0006%\u0018bAAvG\n1\u0011I\\=SK\u001a\u0004BAVAx!&\u0019\u0011\u0011_,\u0003\u00155c%+Z1eC\ndW\r\u0005\u0003\u0002v\u0006}XBAA|\u0015\u0011\tI0a?\u0002\u0005%|'BAA\u007f\u0003\u0011Q\u0017M^1\n\t\t\u0005\u0011q\u001f\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u000b\u0003\u0003G\u0014\u0001\u0004V1sO\u0016$XI\\2pI\u0016\u0014Xj\u001c3fY^\u0013\u0018\u000e^3s'\r)\u0012\u0011[\u0001\tS:\u001cH/\u00198dKR!!Q\u0002B\t!\r\u0011y!F\u0007\u0002'!1!\u0011B\fA\u0002A\u0013A\u0001R1uCN9\u0001$a:\u0003\u0018\tu\u0001c\u0001=\u0003\u001a%\u0019!1D2\u0003\u000fA\u0013x\u000eZ;diB!!q\u0004B\u0014\u001d\u0011\u0011\tC!\n\u000f\u0007\u0001\u0014\u0019#C\u0001e\u0013\r\tIiY\u0005\u0005\u0005\u0003\u0011ICC\u0002\u0002\n\u000e\fQ!\u001b8eKb,\"Aa\f\u0011\u0007a\u0014\t$C\u0002\u00034\r\u00141!\u00138u\u0003\u0019Ig\u000eZ3yA\u0005Q1-\u0019;fO>\u0014\u0018.Z:\u0016\u0005\tm\u0002c\u0001=z}\u0006Y1-\u0019;fO>\u0014\u0018.Z:!\u0003\u0019\u0019w.\u001e8ug\u000691m\\;oiN\u0004CC\u0003B#\u0005\u0013\u0012YE!\u0014\u0003PA\u0019!q\t\r\u000e\u0003UAqAa\u000b\"\u0001\u0004\u0011y\u0003C\u0004\u00038\u0005\u0002\rAa\u000f\t\u000f\t}\u0012\u00051\u0001\u0003<!1Q/\ta\u0001\u0005w!\"B!\u0012\u0003T\tU#q\u000bB-\u0011%\u0011YC\tI\u0001\u0002\u0004\u0011y\u0003C\u0005\u00038\t\u0002\n\u00111\u0001\u0003<!I!q\b\u0012\u0011\u0002\u0003\u0007!1\b\u0005\tk\n\u0002\n\u00111\u0001\u0003<\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\nTC\u0001B0U\u0011\u0011yC!\u0019,\u0005\t\r\u0004\u0003\u0002B3\u0005[j!Aa\u001a\u000b\t\t%$1N\u0001\nk:\u001c\u0007.Z2lK\u0012T!A\\2\n\t\t=$q\r\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0003\u0005kRCAa\u000f\u0003b\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\u001a\u0014AD2paf$C-\u001a4bk2$H\u0005N\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\t}\u0004\u0003\u0002BA\u0005\u000fk!Aa!\u000b\t\t\u0015\u00151`\u0001\u0005Y\u0006tw-C\u0002i\u0005\u0007\u000bA\u0002\u001d:pIV\u001cG/\u0011:jif\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u00020\n=\u0005\"\u0003BIS\u0005\u0005\t\u0019\u0001B\u0018\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011!q\u0013\t\u0007\u00053\u0013y*a,\u000e\u0005\tm%b\u0001BOG\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\t\u0005&1\u0014\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0003(\n5\u0006c\u0001=\u0003*&\u0019!1V2\u0003\u000f\t{w\u000e\\3b]\"I!\u0011S\u0016\u0002\u0002\u0003\u0007\u0011qV\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u0003\u0000\tM\u0006\"\u0003BIY\u0005\u0005\t\u0019\u0001B\u0018\u0003!A\u0017m\u001d5D_\u0012,GC\u0001B\u0018)\t\u0011y(\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0005O\u0013y\fC\u0005\u0003\u0012>\n\t\u00111\u0001\u00020\u0006!A)\u0019;b!\r\u00119%M\n\u0006c\t\u001d\u00171\u001f\t\u000f\u0005\u0013\u0014yMa\f\u0003<\tm\"1\bB#\u001b\t\u0011YMC\u0002\u0003N\u000e\fqA];oi&lW-\u0003\u0003\u0003R\n-'!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8oiQ\u0011!1Y\u0001\u0006CB\u0004H.\u001f\u000b\u000b\u0005\u000b\u0012INa7\u0003^\n}\u0007b\u0002B\u0016i\u0001\u0007!q\u0006\u0005\b\u0005o!\u0004\u0019\u0001B\u001e\u0011\u001d\u0011y\u0004\u000ea\u0001\u0005wAa!\u001e\u001bA\u0002\tm\u0012aB;oCB\u0004H.\u001f\u000b\u0005\u0005K\u0014\t\u0010E\u0003y\u0005O\u0014Y/C\u0002\u0003j\u000e\u0014aa\u00149uS>t\u0007c\u0003=\u0003n\n=\"1\bB\u001e\u0005wI1Aa<d\u0005\u0019!V\u000f\u001d7fi!I!1_\u001b\u0002\u0002\u0003\u0007!QI\u0001\u0004q\u0012\u0002\u0014\u0001C:bm\u0016LU\u000e\u001d7\u0015\t\te(q \t\u0004q\nm\u0018b\u0001B\u007fG\n!QK\\5u\u0011\u0019\u0019\tA\u000ea\u0001;\u0006!\u0001/\u0019;i\u0005a!\u0016M]4fi\u0016s7m\u001c3fe6{G-\u001a7SK\u0006$WM]\n\u0004o\r\u001d\u0001\u0003\u0002,\u0004\nAK1aa\u0003X\u0005!iEJU3bI\u0016\u0014HCAB\b!\r\u0011yaN\u0001\nG2\f7o\u001d(b[\u0016\f!b\u00197bgNt\u0015-\\3!\u0003\u0011aw.\u00193\u0015\u0007A\u001bI\u0002\u0003\u0004\u0004\u0002m\u0002\r!X\u0001\u0005e\u0016\fG-\u0006\u0002\u0004\b!\u001aAh[9\u0015\u0007A\u001b\u0019\u0003\u0003\u0004\u0004\u0002u\u0002\r!\u0018\u0015\u0004{-\f\u0018\u0001D<sSR,'+\u001a9mC\u000e,GCAB\u0016!\u0011\u0011\ti!\f\n\t\r=\"1\u0011\u0002\u0007\u001f\nTWm\u0019;)\u0007MY\u0017\u000fK\u0002\u0013WF\u0004"
)
public class TargetEncoderModel extends Model implements TargetEncoderBase, MLWritable {
   private final String uid;
   private final Map[] stats;
   private Param handleInvalid;
   private Param targetType;
   private DoubleParam smoothing;
   private StringArrayParam outputCols;
   private Param outputCol;
   private StringArrayParam inputCols;
   private Param inputCol;
   private Param labelCol;

   public static TargetEncoderModel load(final String path) {
      return TargetEncoderModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return TargetEncoderModel$.MODULE$.read();
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public final String getTargetType() {
      return TargetEncoderBase.getTargetType$(this);
   }

   public final double getSmoothing() {
      return TargetEncoderBase.getSmoothing$(this);
   }

   public String[] inputFeatures() {
      return TargetEncoderBase.inputFeatures$(this);
   }

   public String[] outputFeatures() {
      return TargetEncoderBase.outputFeatures$(this);
   }

   public StructType validateSchema(final StructType schema, final boolean fitting) {
      return TargetEncoderBase.validateSchema$(this, schema, fitting);
   }

   public final String getHandleInvalid() {
      return HasHandleInvalid.getHandleInvalid$(this);
   }

   public final String[] getOutputCols() {
      return HasOutputCols.getOutputCols$(this);
   }

   public final String getOutputCol() {
      return HasOutputCol.getOutputCol$(this);
   }

   public final String[] getInputCols() {
      return HasInputCols.getInputCols$(this);
   }

   public final String getInputCol() {
      return HasInputCol.getInputCol$(this);
   }

   public final String getLabelCol() {
      return HasLabelCol.getLabelCol$(this);
   }

   public Param handleInvalid() {
      return this.handleInvalid;
   }

   public Param targetType() {
      return this.targetType;
   }

   public DoubleParam smoothing() {
      return this.smoothing;
   }

   public void org$apache$spark$ml$feature$TargetEncoderBase$_setter_$handleInvalid_$eq(final Param x$1) {
      this.handleInvalid = x$1;
   }

   public void org$apache$spark$ml$feature$TargetEncoderBase$_setter_$targetType_$eq(final Param x$1) {
      this.targetType = x$1;
   }

   public void org$apache$spark$ml$feature$TargetEncoderBase$_setter_$smoothing_$eq(final DoubleParam x$1) {
      this.smoothing = x$1;
   }

   public void org$apache$spark$ml$param$shared$HasHandleInvalid$_setter_$handleInvalid_$eq(final Param x$1) {
   }

   public final StringArrayParam outputCols() {
      return this.outputCols;
   }

   public final void org$apache$spark$ml$param$shared$HasOutputCols$_setter_$outputCols_$eq(final StringArrayParam x$1) {
      this.outputCols = x$1;
   }

   public final Param outputCol() {
      return this.outputCol;
   }

   public final void org$apache$spark$ml$param$shared$HasOutputCol$_setter_$outputCol_$eq(final Param x$1) {
      this.outputCol = x$1;
   }

   public final StringArrayParam inputCols() {
      return this.inputCols;
   }

   public final void org$apache$spark$ml$param$shared$HasInputCols$_setter_$inputCols_$eq(final StringArrayParam x$1) {
      this.inputCols = x$1;
   }

   public final Param inputCol() {
      return this.inputCol;
   }

   public final void org$apache$spark$ml$param$shared$HasInputCol$_setter_$inputCol_$eq(final Param x$1) {
      this.inputCol = x$1;
   }

   public final Param labelCol() {
      return this.labelCol;
   }

   public final void org$apache$spark$ml$param$shared$HasLabelCol$_setter_$labelCol_$eq(final Param x$1) {
      this.labelCol = x$1;
   }

   public String uid() {
      return this.uid;
   }

   public Map[] stats() {
      return this.stats;
   }

   public TargetEncoderModel setInputCol(final String value) {
      return (TargetEncoderModel)this.set(this.inputCol(), value);
   }

   public TargetEncoderModel setOutputCol(final String value) {
      return (TargetEncoderModel)this.set(this.outputCol(), value);
   }

   public TargetEncoderModel setInputCols(final String[] values) {
      return (TargetEncoderModel)this.set(this.inputCols(), values);
   }

   public TargetEncoderModel setOutputCols(final String[] values) {
      return (TargetEncoderModel)this.set(this.outputCols(), values);
   }

   public TargetEncoderModel setHandleInvalid(final String value) {
      return (TargetEncoderModel)this.set(this.handleInvalid(), value);
   }

   public TargetEncoderModel setSmoothing(final double value) {
      return (TargetEncoderModel)this.set(this.smoothing(), BoxesRunTime.boxToDouble(value));
   }

   public StructType transformSchema(final StructType schema) {
      if (this.outputFeatures().length == this.stats().length) {
         return (StructType).MODULE$.foldLeft$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.outputFeatures()), (x$2) -> BoxesRunTime.boxToBoolean($anonfun$transformSchema$1(x$2)))), this.validateSchema(schema, false), (x0$1, x1$1) -> {
            Tuple2 var3 = new Tuple2(x0$1, x1$1);
            if (var3 != null) {
               StructType newSchema = (StructType)var3._1();
               String outputField = (String)var3._2();
               return newSchema.add(new StructField(outputField, org.apache.spark.sql.types.DoubleType..MODULE$, false, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()));
            } else {
               throw new MatchError(var3);
            }
         });
      } else {
         int var10002 = this.stats().length;
         throw new SparkException("The number of features does not match the number of encodings in the model (" + var10002 + "). Found " + this.outputFeatures().length + " features)");
      }
   }

   public Dataset transform(final Dataset dataset) {
      this.transformSchema(dataset.schema());
      Map[] encodings = (Map[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.stats()), (stat) -> {
         Tuple2 var4 = (Tuple2)stat.get(BoxesRunTime.boxToDouble(TargetEncoder$.MODULE$.UNSEEN_CATEGORY())).get();
         if (var4 != null) {
            double global_count = var4._1$mcD$sp();
            double global_stat = var4._2$mcD$sp();
            Tuple2.mcDD.sp var3 = new Tuple2.mcDD.sp(global_count, global_stat);
            double global_count = ((Tuple2)var3)._1$mcD$sp();
            double global_stat = ((Tuple2)var3)._2$mcD$sp();
            return (Map)stat.map((x0$1) -> {
               if (x0$1 != null) {
                  double cat = x0$1._1$mcD$sp();
                  Tuple2 var12 = (Tuple2)x0$1._2();
                  if (var12 != null) {
                     double class_count;
                     double class_stat;
                     double weight;
                     double var23;
                     Predef.ArrowAssoc var10000;
                     Object var10001;
                     label49: {
                        class_count = var12._1$mcD$sp();
                        class_stat = var12._2$mcD$sp();
                        var10000 = scala.Predef.ArrowAssoc..MODULE$;
                        var10001 = scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToDouble(cat));
                        weight = class_count / (class_count + BoxesRunTime.unboxToDouble(this.$(this.smoothing())));
                        String var19 = (String)this.$(this.targetType());
                        String var10002 = TargetEncoder$.MODULE$.TARGET_BINARY();
                        if (var10002 == null) {
                           if (var19 == null) {
                              break label49;
                           }
                        } else if (var10002.equals(var19)) {
                           break label49;
                        }

                        var10002 = TargetEncoder$.MODULE$.TARGET_CONTINUOUS();
                        if (var10002 == null) {
                           if (var19 != null) {
                              throw new MatchError(var19);
                           }
                        } else if (!var10002.equals(var19)) {
                           throw new MatchError(var19);
                        }

                        var23 = weight * class_stat + ((double)1 - weight) * global_stat;
                        return var10000.$minus$greater$extension(var10001, BoxesRunTime.boxToDouble(var23));
                     }

                     var23 = weight * (class_stat / class_count) + ((double)1 - weight) * (global_stat / global_count);
                     return var10000.$minus$greater$extension(var10001, BoxesRunTime.boxToDouble(var23));
                  }
               }

               throw new MatchError(x0$1);
            });
         } else {
            throw new MatchError(var4);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Map.class));
      Column[] newCols = (Column[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[]).MODULE$.zip$extension(scala.Predef..MODULE$.refArrayOps((Object[]).MODULE$.zip$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.inputFeatures()), scala.Predef..MODULE$.wrapRefArray((Object[])this.outputFeatures()))), scala.Predef..MODULE$.wrapRefArray((Object[])encodings))), (x0$2) -> {
         if (x0$2 != null) {
            Tuple2 var6 = (Tuple2)x0$2._1();
            Map mapping = (Map)x0$2._2();
            if (var6 != null) {
               String featureIn;
               String featureOut;
               Column var25;
               label29: {
                  label28: {
                     featureIn = (String)var6._1();
                     featureOut = (String)var6._2();
                     String unseenErrMsg = "Unseen value %s in feature " + featureIn + ". To handle unseen values, set Param handleInvalid to " + TargetEncoder$.MODULE$.KEEP_INVALID() + ".";
                     Column unseenErrCol = org.apache.spark.sql.functions..MODULE$.raise_error(org.apache.spark.sql.functions..MODULE$.printf(org.apache.spark.sql.functions..MODULE$.lit(unseenErrMsg), scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col(featureIn).cast(org.apache.spark.sql.types.StringType..MODULE$)}))));
                     String var13 = (String)this.$(this.handleInvalid());
                     String var10000 = TargetEncoder$.MODULE$.KEEP_INVALID();
                     if (var10000 == null) {
                        if (var13 == null) {
                           break label28;
                        }
                     } else if (var10000.equals(var13)) {
                        break label28;
                     }

                     var25 = unseenErrCol;
                     break label29;
                  }

                  var25 = org.apache.spark.sql.functions..MODULE$.lit(mapping.apply(BoxesRunTime.boxToDouble(TargetEncoder$.MODULE$.UNSEEN_CATEGORY())));
               }

               Column fillUnseenCol = var25;
               Option var16 = mapping.get(BoxesRunTime.boxToDouble(TargetEncoder$.MODULE$.NULL_CATEGORY()));
               if (var16 instanceof Some) {
                  Some var17 = (Some)var16;
                  double code = BoxesRunTime.unboxToDouble(var17.value());
                  var25 = org.apache.spark.sql.functions..MODULE$.lit(BoxesRunTime.boxToDouble(code));
               } else {
                  var25 = fillUnseenCol;
               }

               Column fillNullCol = var25;
               Map filteredMapping = (Map)mapping.filter((x0$3) -> BoxesRunTime.boxToBoolean($anonfun$transform$4(x0$3)));
               Column castedCol = org.apache.spark.sql.functions..MODULE$.col(featureIn).cast(org.apache.spark.sql.types.DoubleType..MODULE$);
               functions var27 = org.apache.spark.sql.functions..MODULE$;
               functions var10001 = org.apache.spark.sql.functions..MODULE$;
               JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
               JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(TargetEncoderModel.class.getClassLoader());

               final class $typecreator1$1 extends TypeCreator {
                  public Types.TypeApi apply(final Mirror $m$untyped) {
                     Universe $u = $m$untyped.universe();
                     return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala.collection.immutable").asModule().moduleClass()), $m$untyped.staticClass("scala.collection.immutable.Map"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$)));
                  }

                  public $typecreator1$1() {
                  }
               }

               Column targetCol = var27.try_element_at(var10001.typedlit(filteredMapping, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1())), castedCol);
               return org.apache.spark.sql.functions..MODULE$.when(castedCol.isNull(), fillNullCol).when(targetCol.isNull().unary_$bang(), targetCol).otherwise(fillUnseenCol).as(featureOut, NominalAttribute$.MODULE$.defaultAttr().withName(featureOut).withNumValues(mapping.values().toSet().size()).withValues((String[]).MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps((double[])mapping.values().toSet().toArray(scala.reflect.ClassTag..MODULE$.Double())), (x$4) -> $anonfun$transform$5(BoxesRunTime.unboxToDouble(x$4)), scala.reflect.ClassTag..MODULE$.apply(String.class))).toMetadata());
            }
         }

         throw new MatchError(x0$2);
      }, scala.reflect.ClassTag..MODULE$.apply(Column.class));
      return dataset.withColumns(.MODULE$.toIndexedSeq$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.outputFeatures())), .MODULE$.toIndexedSeq$extension(scala.Predef..MODULE$.refArrayOps((Object[])newCols)));
   }

   public TargetEncoderModel copy(final ParamMap extra) {
      TargetEncoderModel copied = new TargetEncoderModel(this.uid(), this.stats());
      return (TargetEncoderModel)((Model)this.copyValues(copied, extra)).setParent(this.parent());
   }

   public MLWriter write() {
      return new TargetEncoderModelWriter(this);
   }

   public String toString() {
      String var10000 = this.uid();
      return "TargetEncoderModel: uid=" + var10000 + ", handleInvalid=" + this.$(this.handleInvalid()) + ", targetType=" + this.$(this.targetType()) + ", numInputCols=" + this.inputFeatures().length + ", numOutputCols=" + this.outputFeatures().length + ", smoothing=" + this.$(this.smoothing());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$transformSchema$1(final String x$2) {
      return x$2 != null;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$transform$4(final Tuple2 x0$3) {
      if (x0$3 == null) {
         throw new MatchError(x0$3);
      } else {
         double k = x0$3._1$mcD$sp();
         return k != TargetEncoder$.MODULE$.UNSEEN_CATEGORY() && k != TargetEncoder$.MODULE$.NULL_CATEGORY();
      }
   }

   // $FF: synthetic method
   public static final String $anonfun$transform$5(final double x$4) {
      return Double.toString(x$4);
   }

   public TargetEncoderModel(final String uid, final Map[] stats) {
      this.uid = uid;
      this.stats = stats;
      HasLabelCol.$init$(this);
      HasInputCol.$init$(this);
      HasInputCols.$init$(this);
      HasOutputCol.$init$(this);
      HasOutputCols.$init$(this);
      HasHandleInvalid.$init$(this);
      TargetEncoderBase.$init$(this);
      MLWritable.$init$(this);
      Statics.releaseFence();
   }

   public TargetEncoderModel() {
      this("", (Map[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(Map.class)));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class TargetEncoderModelWriter extends MLWriter {
      private volatile Data$ Data$module;
      private final TargetEncoderModel instance;

      private Data$ Data() {
         if (this.Data$module == null) {
            this.Data$lzycompute$1();
         }

         return this.Data$module;
      }

      public void saveImpl(final String path) {
         DefaultParamsWriter$.MODULE$.saveMetadata(this.instance, path, this.sparkSession());
         Seq datum = .MODULE$.iterator$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.instance.stats())).zipWithIndex().map((x0$1) -> {
            if (x0$1 != null) {
               Map stat = (Map)x0$1._1();
               int index = x0$1._2$mcI$sp();
               Tuple2 var9 = stat.toSeq().unzip(scala.Predef..MODULE$.$conforms());
               if (var9 != null) {
                  Seq _categories = (Seq)var9._1();
                  Seq _countsAndStats = (Seq)var9._2();
                  Tuple2 var8 = new Tuple2(_categories, _countsAndStats);
                  Seq _categories = (Seq)var8._1();
                  Seq _countsAndStats = (Seq)var8._2();
                  Tuple2 var15 = _countsAndStats.unzip(scala.Predef..MODULE$.$conforms());
                  if (var15 != null) {
                     Seq _counts = (Seq)var15._1();
                     Seq _stats = (Seq)var15._2();
                     Tuple2 var14 = new Tuple2(_counts, _stats);
                     Seq _countsx = (Seq)var14._1();
                     Seq _statsx = (Seq)var14._2();
                     return this.new Data(index, (double[])_categories.toArray(scala.reflect.ClassTag..MODULE$.Double()), (double[])_countsx.toArray(scala.reflect.ClassTag..MODULE$.Double()), (double[])_statsx.toArray(scala.reflect.ClassTag..MODULE$.Double()));
                  } else {
                     throw new MatchError(var15);
                  }
               } else {
                  throw new MatchError(var9);
               }
            } else {
               throw new MatchError(x0$1);
            }
         }).toSeq();
         String dataPath = (new Path(path, "data")).toString();
         SparkSession var10000 = this.sparkSession();
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(TargetEncoderModelWriter.class.getClassLoader());

         final class $typecreator1$2 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticClass("org.apache.spark.ml.feature.TargetEncoderModel.TargetEncoderModelWriter")), $u.internal().reificationSupport().selectType($m$untyped.staticClass("org.apache.spark.ml.feature.TargetEncoderModel.TargetEncoderModelWriter"), "Data"), scala.collection.immutable.Nil..MODULE$);
            }

            public $typecreator1$2() {
            }
         }

         var10000.createDataFrame(datum, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$2())).write().parquet(dataPath);
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

      public TargetEncoderModelWriter(final TargetEncoderModel instance) {
         this.instance = instance;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }

      private class Data implements Product, Serializable {
         private final int index;
         private final double[] categories;
         private final double[] counts;
         private final double[] stats;
         // $FF: synthetic field
         public final TargetEncoderModelWriter $outer;

         public Iterator productElementNames() {
            return Product.productElementNames$(this);
         }

         public int index() {
            return this.index;
         }

         public double[] categories() {
            return this.categories;
         }

         public double[] counts() {
            return this.counts;
         }

         public double[] stats() {
            return this.stats;
         }

         public Data copy(final int index, final double[] categories, final double[] counts, final double[] stats) {
            return this.org$apache$spark$ml$feature$TargetEncoderModel$TargetEncoderModelWriter$Data$$$outer().new Data(index, categories, counts, stats);
         }

         public int copy$default$1() {
            return this.index();
         }

         public double[] copy$default$2() {
            return this.categories();
         }

         public double[] copy$default$3() {
            return this.counts();
         }

         public double[] copy$default$4() {
            return this.stats();
         }

         public String productPrefix() {
            return "Data";
         }

         public int productArity() {
            return 4;
         }

         public Object productElement(final int x$1) {
            switch (x$1) {
               case 0 -> {
                  return BoxesRunTime.boxToInteger(this.index());
               }
               case 1 -> {
                  return this.categories();
               }
               case 2 -> {
                  return this.counts();
               }
               case 3 -> {
                  return this.stats();
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
                  return "categories";
               }
               case 2 -> {
                  return "counts";
               }
               case 3 -> {
                  return "stats";
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
            var1 = Statics.mix(var1, Statics.anyHash(this.categories()));
            var1 = Statics.mix(var1, Statics.anyHash(this.counts()));
            var1 = Statics.mix(var1, Statics.anyHash(this.stats()));
            return Statics.finalizeHash(var1, 4);
         }

         public String toString() {
            return scala.runtime.ScalaRunTime..MODULE$._toString(this);
         }

         public boolean equals(final Object x$1) {
            boolean var10000;
            if (this != x$1) {
               label47: {
                  if (x$1 instanceof Data && ((Data)x$1).org$apache$spark$ml$feature$TargetEncoderModel$TargetEncoderModelWriter$Data$$$outer() == this.org$apache$spark$ml$feature$TargetEncoderModel$TargetEncoderModelWriter$Data$$$outer()) {
                     Data var4 = (Data)x$1;
                     if (this.index() == var4.index() && this.categories() == var4.categories() && this.counts() == var4.counts() && this.stats() == var4.stats() && var4.canEqual(this)) {
                        break label47;
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
         public TargetEncoderModelWriter org$apache$spark$ml$feature$TargetEncoderModel$TargetEncoderModelWriter$Data$$$outer() {
            return this.$outer;
         }

         public Data(final int index, final double[] categories, final double[] counts, final double[] stats) {
            this.index = index;
            this.categories = categories;
            this.counts = counts;
            this.stats = stats;
            if (TargetEncoderModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = TargetEncoderModelWriter.this;
               super();
               Product.$init$(this);
            }
         }
      }

      private class Data$ extends AbstractFunction4 implements Serializable {
         // $FF: synthetic field
         private final TargetEncoderModelWriter $outer;

         public final String toString() {
            return "Data";
         }

         public Data apply(final int index, final double[] categories, final double[] counts, final double[] stats) {
            return this.$outer.new Data(index, categories, counts, stats);
         }

         public Option unapply(final Data x$0) {
            return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple4(BoxesRunTime.boxToInteger(x$0.index()), x$0.categories(), x$0.counts(), x$0.stats())));
         }

         public Data$() {
            if (TargetEncoderModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = TargetEncoderModelWriter.this;
               super();
            }
         }
      }
   }

   private static class TargetEncoderModelReader extends MLReader {
      private final String className = TargetEncoderModel.class.getName();

      private String className() {
         return this.className;
      }

      public TargetEncoderModel load(final String path) {
         DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, this.sparkSession(), this.className());
         String dataPath = (new Path(path, "data")).toString();
         Map[] stats = (Map[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.sortBy$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(this.sparkSession().read().parquet(dataPath).select("index", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"categories", "counts", "stats"}))).collect()), (row) -> {
            int index = row.getInt(0);
            double[] categories = (double[])((IterableOnceOps)row.getAs(1)).toArray(scala.reflect.ClassTag..MODULE$.Double());
            double[] counts = (double[])((IterableOnceOps)row.getAs(2)).toArray(scala.reflect.ClassTag..MODULE$.Double());
            double[] stats = (double[])((IterableOnceOps)row.getAs(3)).toArray(scala.reflect.ClassTag..MODULE$.Double());
            return new Tuple2(BoxesRunTime.boxToInteger(index), scala.Predef..MODULE$.wrapRefArray((Object[]).MODULE$.zip$extension(scala.Predef..MODULE$.doubleArrayOps(categories), scala.Predef..MODULE$.wrapRefArray((Object[]).MODULE$.zip$extension(scala.Predef..MODULE$.doubleArrayOps(counts), scala.Predef..MODULE$.wrapDoubleArray(stats))))).toMap(scala..less.colon.less..MODULE$.refl()));
         }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))), (x$7) -> BoxesRunTime.boxToInteger($anonfun$load$2(x$7)), scala.math.Ordering.Int..MODULE$)), (x$8) -> (Map)x$8._2(), scala.reflect.ClassTag..MODULE$.apply(Map.class));
         TargetEncoderModel model = new TargetEncoderModel(metadata.uid(), stats);
         metadata.getAndSetParams(model, metadata.getAndSetParams$default$2());
         return model;
      }

      // $FF: synthetic method
      public static final int $anonfun$load$2(final Tuple2 x$7) {
         return x$7._1$mcI$sp();
      }

      public TargetEncoderModelReader() {
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
