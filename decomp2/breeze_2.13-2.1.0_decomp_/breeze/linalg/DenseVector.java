package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.operators.HasOps$;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanCreateZeros;
import breeze.linalg.support.CanCreateZerosLike;
import breeze.linalg.support.CanMapKeyValuePairs;
import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.CanTranspose;
import breeze.linalg.support.CanZipMapKeyValues;
import breeze.linalg.support.CanZipMapValues;
import breeze.linalg.support.ScalarOf;
import breeze.linalg.support.TensorActive;
import breeze.linalg.support.TensorKeys;
import breeze.linalg.support.TensorPairs;
import breeze.linalg.support.TensorValues;
import breeze.math.Field;
import breeze.math.MutableFiniteCoordinateField;
import breeze.math.Semiring;
import breeze.stats.distributions.Rand;
import breeze.storage.Storage;
import breeze.storage.Zero;
import breeze.util.ArrayUtil$;
import breeze.util.RangeUtils$;
import breeze.util.ReflectionUtil$;
import java.io.ObjectStreamException;
import java.io.Serializable;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple4;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Range;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction4;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d=haBA\b\u0003#\u0001\u00111\u0004\u0005\u000b\u00033\u0003!Q1A\u0005\u0002\u0005m\u0005BCAR\u0001\t\u0005\t\u0015!\u0003\u0002\u001e\"Q\u0011Q\u0015\u0001\u0003\u0006\u0004%\t!a*\t\u0015\u0005=\u0006A!A!\u0002\u0013\tI\u000b\u0003\u0006\u00022\u0002\u0011)\u0019!C\u0001\u0003OC!\"a-\u0001\u0005\u0003\u0005\u000b\u0011BAU\u0011)\t)\f\u0001BC\u0002\u0013\u0005\u0011q\u0015\u0005\u000b\u0003o\u0003!\u0011!Q\u0001\n\u0005%\u0006bBA]\u0001\u0011\u0005\u00111\u0018\u0005\b\u0003s\u0003A\u0011AAc\u0011\u001d\tI\f\u0001C\u0001\u0003\u0013Dq!!/\u0001\t\u0003\ty\rC\u0004\u0002f\u0002!\t!a:\t\u000f\u0005%\b\u0001\"\u0001\u0002(\"9\u00111\u001e\u0001\u0005\u0002\u00055\bbBAz\u0001\u0011\u0005\u0011Q\u001f\u0005\f\u0005\u0007\u0001!\u0019!C\u0001\u0003#\u0011)\u0001\u0003\u0005\u0003\u000e\u0001\u0001\u000b\u0011\u0002B\u0004\u0011\u001d\u0011y\u0001\u0001C\u0005\u0005#AqAa\u0005\u0001\t\u0003\u0011)\u0002C\u0004\u0003$\u0001!\tA!\n\t\u000f\t%\u0002\u0001\"\u0001\u0003,!9!q\u0006\u0001\u0005B\tE\u0002b\u0002B\u001c\u0001\u0011\u0005#\u0011\b\u0005\b\u0005w\u0001A\u0011\tB\u001f\u0011\u001d\u0011y\u0005\u0001C\u0001\u0003ODqA!\u0015\u0001\t\u0003\u0011\u0019\u0006C\u0004\u0003X\u0001!\tA!\u0017\t\u000f\tu\u0003\u0001\"\u0001\u0003`!9!1\r\u0001\u0005\u0002\t\u0015\u0001b\u0002B3\u0001\u0011\u0005#q\r\u0005\b\u0005\u000f\u0003A\u0011\u0001BE\u0011%\u0011)\nAI\u0001\n\u0003\u00119\nC\u0004\u0003.\u0002!\tAa,\t\u000f\t]\u0006\u0001\"\u0001\u00030\"9!\u0011\u0018\u0001\u0005B\tm\u0006b\u0002Ba\u0001\u0011\u0005#1\u0019\u0005\b\u0005\u0017\u0004A\u0011\u0003Bg\u0011%\u0011)\u0010\u0001C\u0001\u0003#\u00119\u0010C\u0004\u0003~\u0002!IAa@\b\u0011\rM\u0011\u0011\u0003E\u0001\u0007+1\u0001\"a\u0004\u0002\u0012!\u00051q\u0003\u0005\b\u0003sSC\u0011AB\u0013\u0011\u001d\u00199C\u000bC\u0001\u0007SAq!a;+\t\u0003\u0019\t\u0007C\u0004\u0004\u0004*\"\ta!\"\t\u000f\r\r%\u0006\"\u0001\u00044\"91\u0011\u001d\u0016\u0005\u0002\r\r\bb\u0002C\nU\u0011\u0005AQ\u0003\u0005\b\tSQC\u0011\u0001C\u0016\u0011\u001d\u0019\tO\u000bC\u0001\tCBq\u0001b$+\t\u0003!\t\nC\u0004\u00056*\"\t\u0001b.\t\u000f\u0011E(\u0006b\u0001\u0005t\"9QQ\u0003\u0016\u0005\u0004\u0015]\u0001bBC\u0017U\u0011\rQq\u0006\u0005\b\u000bORC1AC5\r\u0019)YH\u000b\u0001\u0006~!QQ\u0011\u0017\u001e\u0003\u0004\u0003\u0006Y!b-\t\u000f\u0005e&\b\"\u0001\u00066\"9A1\u0003\u001e\u0005\u0002\u0015}\u0006bBCbu\u0011\u0005QQ\u0019\u0005\b\u000b/TC1ACm\u0011%)yO\u000bb\u0001\n\u0007)\t\u0010\u0003\u0005\u0006|*\u0002\u000b\u0011BCz\u0011%)iP\u000bb\u0001\n\u0007)y\u0010\u0003\u0005\u0007\n)\u0002\u000b\u0011\u0002D\u0001\u0011%1YA\u000bb\u0001\n\u00071i\u0001\u0003\u0005\u0007\u0012)\u0002\u000b\u0011\u0002D\b\r\u00191\u0019B\u000b\u0001\u0007\u0016!Qaq\t$\u0003\u0004\u0003\u0006YA\"\u0013\t\u000f\u0005ef\t\"\u0001\u0007L!9A1\u0003$\u0005\u0002\u0019M\u0003bBCb\r\u0012\u0005aq\u000b\u0005\b\rK2E\u0011\tD4\u0011\u001d1yG\u000bC\u0002\rcBqA\"\"+\t\u000719\tC\u0005\u0007(*\u0012\r\u0011b\u0001\u0007*\"Aaq\u0016\u0016!\u0002\u00131Y\u000bC\u0005\u00072*\u0012\r\u0011b\u0001\u00074\"Aa\u0011\u0018\u0016!\u0002\u00131)\fC\u0005\u0007<*\u0012\r\u0011b\u0001\u0007>\"Aa1\u0019\u0016!\u0002\u00131y\fC\u0005\u0007F*\u0012\r\u0011b\u0001\u0007H\"Aa1\u001b\u0016!\u0002\u00131ImB\u0004\u0007V*B\tAb6\u0007\u000f\u0019e'\u0006#\u0001\u0007\\\"9\u0011\u0011X,\u0005\u0002\u0019uwa\u0002Dp/\"\ra\u0011\u001d\u0004\b\rK<\u0006\u0012\u0001Dt\u0011\u001d\tIL\u0017C\u0001\rkDqAb>[\t\u00031I\u0010C\u0004\u0007\u0000j#\ta\"\u0001\t\u0013\t-',!A\u0005\n\t5waBD\u0003/\"\rqq\u0001\u0004\b\u000f\u00139\u0006\u0012AD\u0006\u0011\u001d\tI\f\u0019C\u0001\u000f#AqAb>a\t\u00039\u0019\u0002C\u0004\u0007\u0000\u0002$\tab\u0006\t\u0013\t-\u0007-!A\u0005\n\t5gABD\u000eU\u0001;i\u0002\u0003\u0006\u0002\u001a\u0016\u0014)\u001a!C\u0001\u000fKA!\"a)f\u0005#\u0005\u000b\u0011BD\u0014\u0011)\t)+\u001aBK\u0002\u0013\u0005\u0011q\u0015\u0005\u000b\u0003_+'\u0011#Q\u0001\n\u0005%\u0006BCAYK\nU\r\u0011\"\u0001\u0002(\"Q\u00111W3\u0003\u0012\u0003\u0006I!!+\t\u0015\u0005UVM!f\u0001\n\u0003\t9\u000b\u0003\u0006\u00028\u0016\u0014\t\u0012)A\u0005\u0003SCq!!/f\t\u00039\t\u0004C\u0004\bF\u0015$\tA!4\t\u0013\t=S-!A\u0005\u0002\u001d%\u0003\"CD*KF\u0005I\u0011AD+\u0011%9\t'ZI\u0001\n\u0003\u00119\nC\u0005\bd\u0015\f\n\u0011\"\u0001\u0003\u0018\"IqQM3\u0012\u0002\u0013\u0005!q\u0013\u0005\n\u000fO*\u0017\u0011!C!\u000fSB\u0011bb\u001cf\u0003\u0003%\t!a*\t\u0013\u001dET-!A\u0005\u0002\u001dM\u0004\"CD=K\u0006\u0005I\u0011ID>\u0011%99)ZA\u0001\n\u00039I\tC\u0005\b\u000e\u0016\f\t\u0011\"\u0011\b\u0010\"I!qG3\u0002\u0002\u0013\u0005#\u0011\b\u0005\n\u0005w)\u0017\u0011!C!\u000f'C\u0011Ba\ff\u0003\u0003%\te\"&\b\u0013\u001dm%&!A\t\u0002\u001due!CD\u000eU\u0005\u0005\t\u0012ADP\u0011\u001d\tIl C\u0001\u000fkC\u0011Ba\u000f\u0000\u0003\u0003%)eb%\t\u0013\u0005-x0!A\u0005\u0002\u001e]\u0006\"CDe\u007f\u0006\u0005I\u0011QDf\u0011%\u0011Ym`A\u0001\n\u0013\u0011i\rC\u0004\bf*\"IA!\u0005\t\u0013\t-'&!A\u0005\n\t5'a\u0003#f]N,g+Z2u_JTA!a\u0005\u0002\u0016\u00051A.\u001b8bY\u001eT!!a\u0006\u0002\r\t\u0014X-\u001a>f\u0007\u0001)B!!\b\u00028MI\u0001!a\b\u0002,\u0005\r\u00151\u0012\t\u0005\u0003C\t9#\u0004\u0002\u0002$)\u0011\u0011QE\u0001\u0006g\u000e\fG.Y\u0005\u0005\u0003S\t\u0019C\u0001\u0004B]f\u0014VM\u001a\t\u0007\u0003[\ty#a\r\u000e\u0005\u0005E\u0011\u0002BA\u0019\u0003#\u0011Qb\u0015;pe\u0006<WMV3di>\u0014\b\u0003BA\u001b\u0003oa\u0001\u0001B\u0006\u0002:\u0001\u0001\u000b\u0011!AC\u0002\u0005m\"!\u0001,\u0012\t\u0005u\u00121\t\t\u0005\u0003C\ty$\u0003\u0003\u0002B\u0005\r\"a\u0002(pi\"Lgn\u001a\t\u0005\u0003C\t)%\u0003\u0003\u0002H\u0005\r\"aA!os\"b\u0011qGA&\u0003#\n)'a\u001c\u0002zA!\u0011\u0011EA'\u0013\u0011\ty%a\t\u0003\u0017M\u0004XmY5bY&TX\rZ\u0019\nG\u0005M\u0013QKA-\u0003/rA!!\t\u0002V%!\u0011qKA\u0012\u0003\u0019!u.\u001e2mKF:A%a\u0017\u0002d\u0005\u0015b\u0002BA/\u0003Gj!!a\u0018\u000b\t\u0005\u0005\u0014\u0011D\u0001\u0007yI|w\u000e\u001e \n\u0005\u0005\u0015\u0012'C\u0012\u0002h\u0005%\u0014QNA6\u001d\u0011\t\t#!\u001b\n\t\u0005-\u00141E\u0001\u0004\u0013:$\u0018g\u0002\u0013\u0002\\\u0005\r\u0014QE\u0019\nG\u0005E\u00141OA<\u0003krA!!\t\u0002t%!\u0011QOA\u0012\u0003\u00151En\\1uc\u001d!\u00131LA2\u0003K\t\u0014bIA>\u0003{\n\t)a \u000f\t\u0005\u0005\u0012QP\u0005\u0005\u0003\u007f\n\u0019#\u0001\u0003M_:<\u0017g\u0002\u0013\u0002\\\u0005\r\u0014Q\u0005\t\t\u0003[\t))a\r\u0002\n&!\u0011qQA\t\u0005)1Vm\u0019;pe2K7.\u001a\t\u0006\u0003[\u0001\u00111\u0007\t\u0005\u0003\u001b\u000b\u0019J\u0004\u0003\u0002\\\u0005=\u0015\u0002BAI\u0003G\tq\u0001]1dW\u0006<W-\u0003\u0003\u0002\u0016\u0006]%\u0001D*fe&\fG.\u001b>bE2,'\u0002BAI\u0003G\tA\u0001Z1uCV\u0011\u0011Q\u0014\t\u0007\u0003C\ty*a\r\n\t\u0005\u0005\u00161\u0005\u0002\u0006\u0003J\u0014\u0018-_\u0001\u0006I\u0006$\u0018\rI\u0001\u0007_\u001a47/\u001a;\u0016\u0005\u0005%\u0006\u0003BA\u0011\u0003WKA!!,\u0002$\t\u0019\u0011J\u001c;\u0002\u000f=4gm]3uA\u000511\u000f\u001e:jI\u0016\fqa\u001d;sS\u0012,\u0007%\u0001\u0004mK:<G\u000f[\u0001\bY\u0016tw\r\u001e5!\u0003\u0019a\u0014N\\5u}QQ\u0011\u0011RA_\u0003\u007f\u000b\t-a1\t\u000f\u0005e\u0015\u00021\u0001\u0002\u001e\"9\u0011QU\u0005A\u0002\u0005%\u0006bBAY\u0013\u0001\u0007\u0011\u0011\u0016\u0005\b\u0003kK\u0001\u0019AAU)\u0011\tI)a2\t\u000f\u0005e%\u00021\u0001\u0002\u001eR1\u0011\u0011RAf\u0003\u001bDq!!'\f\u0001\u0004\ti\nC\u0004\u0002&.\u0001\r!!+\u0015\t\u0005E\u00171\u001d\u000b\u0005\u0003\u0013\u000b\u0019\u000eC\u0004\u0002V2\u0001\u001d!a6\u0002\u00075\fg\u000e\u0005\u0004\u0002Z\u0006}\u00171G\u0007\u0003\u00037TA!!8\u0002$\u00059!/\u001a4mK\u000e$\u0018\u0002BAq\u00037\u0014\u0001b\u00117bgN$\u0016m\u001a\u0005\b\u0003kc\u0001\u0019AAU\u0003\u0011\u0011X\r\u001d:\u0016\u0005\u0005%\u0015AC1di&4XmU5{K\u0006)\u0011\r\u001d9msR!\u00111GAx\u0011\u001d\t\tp\u0004a\u0001\u0003S\u000b\u0011![\u0001\u0007kB$\u0017\r^3\u0015\r\u0005]\u0018Q`A\u0000!\u0011\t\t#!?\n\t\u0005m\u00181\u0005\u0002\u0005+:LG\u000fC\u0004\u0002rB\u0001\r!!+\t\u000f\t\u0005\u0001\u00031\u0001\u00024\u0005\ta/\u0001\to_>3gm]3u\u001fJ\u001cFO]5eKV\u0011!q\u0001\t\u0005\u0003C\u0011I!\u0003\u0003\u0003\f\u0005\r\"a\u0002\"p_2,\u0017M\\\u0001\u0012]>|eMZ:fi>\u00138\u000b\u001e:jI\u0016\u0004\u0013AE2iK\u000e\\\u0017JZ*qK\u000eL\u0017\r\\5{K\u0012$\"!a>\u0002\u001d\u0005\u001cG/\u001b<f\u0013R,'/\u0019;peV\u0011!q\u0003\t\u0007\u0003\u001b\u0013IB!\b\n\t\tm\u0011q\u0013\u0002\t\u0013R,'/\u0019;peBA\u0011\u0011\u0005B\u0010\u0003S\u000b\u0019$\u0003\u0003\u0003\"\u0005\r\"A\u0002+va2,''\u0001\u000bbGRLg/\u001a,bYV,7/\u0013;fe\u0006$xN]\u000b\u0003\u0005O\u0001b!!$\u0003\u001a\u0005M\u0012AE1di&4XmS3zg&#XM]1u_J,\"A!\f\u0011\r\u00055%\u0011DAU\u0003\u0019)\u0017/^1mgR!!q\u0001B\u001a\u0011\u001d\u0011)d\u0006a\u0001\u0003\u0007\n!\u0001]\u0019\u0002\u0011!\f7\u000f[\"pI\u0016$\"!!+\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"Aa\u0010\u0011\t\t\u0005#\u0011\n\b\u0005\u0005\u0007\u0012)\u0005\u0005\u0003\u0002^\u0005\r\u0012\u0002\u0002B$\u0003G\ta\u0001\u0015:fI\u00164\u0017\u0002\u0002B&\u0005\u001b\u0012aa\u0015;sS:<'\u0002\u0002B$\u0003G\tAaY8qs\u00069a/\u00197vK\u0006#H\u0003BA\u001a\u0005+Bq!!=\u001c\u0001\u0004\tI+A\u0004j]\u0012,\u00070\u0011;\u0015\t\u0005%&1\f\u0005\b\u0003cd\u0002\u0019AAU\u0003!I7/Q2uSZ,G\u0003\u0002B\u0004\u0005CBq!!=\u001e\u0001\u0004\tI+A\rbY24\u0016n]5uC\ndW-\u00138eS\u000e,7/Q2uSZ,\u0017a\u00024pe\u0016\f7\r[\u000b\u0005\u0005S\u00129\b\u0006\u0003\u0002x\n-\u0004b\u0002B7?\u0001\u0007!qN\u0001\u0003M:\u0004\u0002\"!\t\u0003r\u0005M\"QO\u0005\u0005\u0005g\n\u0019CA\u0005Gk:\u001cG/[8ocA!\u0011Q\u0007B<\t-\u0011Ih\bQ\u0001\u0002\u0003\u0015\r!a\u000f\u0003\u0003UCcAa\u001e\u0002L\tu\u0014'C\u0012\u0003\u0000\t\u0005%Q\u0011BB\u001d\u0011\t\tC!!\n\t\t\r\u00151E\u0001\u0005+:LG/M\u0004%\u00037\n\u0019'!\n\u0002\u000bMd\u0017nY3\u0015\u0011\u0005%%1\u0012BH\u0005'CqA!$!\u0001\u0004\tI+A\u0003ti\u0006\u0014H\u000fC\u0004\u0003\u0012\u0002\u0002\r!!+\u0002\u0007\u0015tG\rC\u0005\u00022\u0002\u0002\n\u00111\u0001\u0002*\u0006y1\u000f\\5dK\u0012\"WMZ1vYR$3'\u0006\u0002\u0003\u001a*\"\u0011\u0011\u0016BNW\t\u0011i\n\u0005\u0003\u0003 \n%VB\u0001BQ\u0015\u0011\u0011\u0019K!*\u0002\u0013Ut7\r[3dW\u0016$'\u0002\u0002BT\u0003G\t!\"\u00198o_R\fG/[8o\u0013\u0011\u0011YK!)\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u0007u_\u0012+gn]3NCR\u0014\u0018\u000e_\u000b\u0003\u0005c\u0003b!!\f\u00034\u0006M\u0012\u0002\u0002B[\u0003#\u00111\u0002R3og\u0016l\u0015\r\u001e:jq\u0006i\u0011m\u001d#f]N,W*\u0019;sSb\fq\u0001^8BeJ\f\u0017\u0010\u0006\u0003\u0002\u001e\nu\u0006b\u0002B`I\u0001\u000f\u0011q[\u0001\u0003GR\fQ\u0002^8TG\u0006d\u0017MV3di>\u0014XC\u0001Bc!\u0019\tiIa2\u00024%!!\u0011ZAL\u0005\u00191Vm\u0019;pe\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u0011!q\u001a\t\u0005\u0005#\u0014Y.\u0004\u0002\u0003T*!!Q\u001bBl\u0003\u0011a\u0017M\\4\u000b\u0005\te\u0017\u0001\u00026bm\u0006LAA!8\u0003T\n1qJ\u00196fGRDSA\nBq\u0005g\u0004b!!\t\u0003d\n\u001d\u0018\u0002\u0002Bs\u0003G\u0011a\u0001\u001e5s_^\u001c\b\u0003\u0002Bu\u0005_l!Aa;\u000b\t\t5(q[\u0001\u0003S>LAA!=\u0003l\n)rJ\u00196fGR\u001cFO]3b[\u0016C8-\u001a9uS>t7E\u0001Bt\u0003!yg/\u001a:mCB\u001cH\u0003\u0002B\u0004\u0005sDqAa?(\u0001\u0004\tI)A\u0003pi\",'/A\u0005g_>$\bO]5oiV\u00111\u0011\u0001\t\u0005\u0003\u001b\u001b\u0019!\u0003\u0003\u0004\u0006\u0005]%!\u0002*b]\u001e,\u0007f\u0002\u0001\u0004\n\r=1\u0011\u0003\t\u0005\u0003C\u0019Y!\u0003\u0003\u0004\u000e\u0005\r\"\u0001E*fe&\fGNV3sg&|g.V%E\u0003\u00151\u0018\r\\;f=\u0005\t\u0011a\u0003#f]N,g+Z2u_J\u00042!!\f+'\u001dQ\u0013qDB\r\u0007C\u0001b!!\f\u0004\u001c\r}\u0011\u0002BB\u000f\u0003#\u0011!CV3di>\u00148i\u001c8tiJ,8\r^8sgB\u0019\u0011Q\u0006\u0001\u0011\t\t%81E\u0005\u0005\u0003+\u0013Y\u000f\u0006\u0002\u0004\u0016\u0005)!0\u001a:pgV!11FB\u001a)\u0011\u0019ic!\u0018\u0015\r\r=2qIB'!\u0015\ti\u0003AB\u0019!\u0011\t)da\r\u0005\u0017\u0005eB\u0006)A\u0001\u0002\u000b\u0007\u00111\b\u0015\r\u0007g\tYea\u000e\u0004<\r}21I\u0019\nG\u0005M\u0013QKB\u001d\u0003/\nt\u0001JA.\u0003G\n)#M\u0005$\u0003O\nIg!\u0010\u0002lE:A%a\u0017\u0002d\u0005\u0015\u0012'C\u0012\u0002r\u0005M4\u0011IA;c\u001d!\u00131LA2\u0003K\t\u0014bIA>\u0003{\u001a)%a 2\u000f\u0011\nY&a\u0019\u0002&!I1\u0011\n\u0017\u0002\u0002\u0003\u000f11J\u0001\u000bKZLG-\u001a8dK\u0012\n\u0004CBAm\u0003?\u001c\t\u0004C\u0005\u0004P1\n\t\u0011q\u0001\u0004R\u0005QQM^5eK:\u001cW\r\n\u001a\u0011\r\rM3\u0011LB\u0019\u001b\t\u0019)F\u0003\u0003\u0004X\u0005U\u0011aB:u_J\fw-Z\u0005\u0005\u00077\u001a)F\u0001\u0003[KJ|\u0007bBB0Y\u0001\u0007\u0011\u0011V\u0001\u0005g&TX-\u0006\u0003\u0004d\r%D\u0003BB3\u0007{\u0002R!!\f\u0001\u0007O\u0002B!!\u000e\u0004j\u0011Y\u0011\u0011H\u0017!\u0002\u0003\u0005)\u0019AA\u001eQ1\u0019I'a\u0013\u0004n\rE4QOB=c%\u0019\u00131KA+\u0007_\n9&M\u0004%\u00037\n\u0019'!\n2\u0013\r\n9'!\u001b\u0004t\u0005-\u0014g\u0002\u0013\u0002\\\u0005\r\u0014QE\u0019\nG\u0005E\u00141OB<\u0003k\nt\u0001JA.\u0003G\n)#M\u0005$\u0003w\niha\u001f\u0002\u0000E:A%a\u0017\u0002d\u0005\u0015\u0002bBB@[\u0001\u00071\u0011Q\u0001\u0007m\u0006dW/Z:\u0011\r\u0005\u0005\u0012qTB4\u0003!!\u0018MY;mCR,W\u0003BBD\u0007##Ba!#\u00042R!11RBV)\u0011\u0019ii!*\u0011\u000b\u00055\u0002aa$\u0011\t\u0005U2\u0011\u0013\u0003\f\u0003sq\u0003\u0015!A\u0001\u0006\u0004\tY\u0004\u000b\u0007\u0004\u0012\u0006-3QSBM\u0007;\u001b\t+M\u0005$\u0003'\n)fa&\u0002XE:A%a\u0017\u0002d\u0005\u0015\u0012'C\u0012\u0002h\u0005%41TA6c\u001d!\u00131LA2\u0003K\t\u0014bIA9\u0003g\u001ay*!\u001e2\u000f\u0011\nY&a\u0019\u0002&EJ1%a\u001f\u0002~\r\r\u0016qP\u0019\bI\u0005m\u00131MA\u0013\u0011%\u00199KLA\u0001\u0002\b\u0019I+\u0001\u0006fm&$WM\\2fIM\u0002b!!7\u0002`\u000e=\u0005bBBW]\u0001\u00071qV\u0001\u0002MBA\u0011\u0011\u0005B9\u0003S\u001by\tC\u0004\u0004`9\u0002\r!!+\u0016\t\rU6q\u0018\u000b\u0005\u0007o\u001bi\u000e\u0006\u0003\u0004:\u000eeG\u0003BB^\u0007'\u0004R!!\f\u0001\u0007{\u0003B!!\u000e\u0004@\u0012Y\u0011\u0011H\u0018!\u0002\u0003\u0005)\u0019AA\u001eQ1\u0019y,a\u0013\u0004D\u000e\u001d71ZBhc%\u0019\u00131KA+\u0007\u000b\f9&M\u0004%\u00037\n\u0019'!\n2\u0013\r\n9'!\u001b\u0004J\u0006-\u0014g\u0002\u0013\u0002\\\u0005\r\u0014QE\u0019\nG\u0005E\u00141OBg\u0003k\nt\u0001JA.\u0003G\n)#M\u0005$\u0003w\nih!5\u0002\u0000E:A%a\u0017\u0002d\u0005\u0015\u0002\"CBk_\u0005\u0005\t9ABl\u0003))g/\u001b3f]\u000e,G\u0005\u000e\t\u0007\u00033\fyn!0\t\u000f\r5v\u00061\u0001\u0004\\BA\u0011\u0011\u0005B9\u0003S\u001bi\fC\u0004\u0004`>\u0002\ra!\u0001\u0002\u000bI\fgnZ3\u0002\t\u0019LG\u000e\\\u000b\u0005\u0007K\u001cy\u000f\u0006\u0003\u0004h\u0012EA\u0003BBu\t\u0013!Baa;\u0005\u0004A)\u0011Q\u0006\u0001\u0004nB!\u0011QGBx\t-\tI\u0004\rQ\u0001\u0002\u0003\u0015\r!a\u000f)\u0019\r=\u00181JBz\u0007o\u001cYpa@2\u0013\r\n\u0019&!\u0016\u0004v\u0006]\u0013g\u0002\u0013\u0002\\\u0005\r\u0014QE\u0019\nG\u0005\u001d\u0014\u0011NB}\u0003W\nt\u0001JA.\u0003G\n)#M\u0005$\u0003c\n\u0019h!@\u0002vE:A%a\u0017\u0002d\u0005\u0015\u0012'C\u0012\u0002|\u0005uD\u0011AA@c\u001d!\u00131LA2\u0003KA\u0011\u0002\"\u00021\u0003\u0003\u0005\u001d\u0001b\u0002\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$S\u0007\u0005\u0004\u0002Z\u0006}7Q\u001e\u0005\t\u0005\u0003\u0001D\u00111\u0001\u0005\fA1\u0011\u0011\u0005C\u0007\u0007[LA\u0001b\u0004\u0002$\tAAHY=oC6,g\bC\u0004\u0004`A\u0002\r!!+\u0002\r\r\u0014X-\u0019;f+\u0011!9\u0002\"\b\u0015\u0015\u0011eAq\u0004C\u0012\tK!9\u0003E\u0003\u0002.\u0001!Y\u0002\u0005\u0003\u00026\u0011uAaBA\u001dc\t\u0007\u00111\b\u0005\b\u00033\u000b\u0004\u0019\u0001C\u0011!\u0019\t\t#a(\u0005\u001c!9\u0011QU\u0019A\u0002\u0005%\u0006bBAYc\u0001\u0007\u0011\u0011\u0016\u0005\b\u0003k\u000b\u0004\u0019AAU\u0003\u0011yg.Z:\u0016\t\u00115BQ\u0007\u000b\u0005\t_!y\u0006\u0006\u0004\u00052\u0011%Cq\n\t\u0006\u0003[\u0001A1\u0007\t\u0005\u0003k!)\u0004B\u0006\u0002:I\u0002\u000b\u0011!AC\u0002\u0005m\u0002\u0006\u0004C\u001b\u0003\u0017\"I\u0004\"\u0010\u0005B\u0011\u0015\u0013'C\u0012\u0002T\u0005UC1HA,c\u001d!\u00131LA2\u0003K\t\u0014bIA4\u0003S\"y$a\u001b2\u000f\u0011\nY&a\u0019\u0002&EJ1%!\u001d\u0002t\u0011\r\u0013QO\u0019\bI\u0005m\u00131MA\u0013c%\u0019\u00131PA?\t\u000f\ny(M\u0004%\u00037\n\u0019'!\n\t\u0013\u0011-#'!AA\u0004\u00115\u0013AC3wS\u0012,gnY3%mA1\u0011\u0011\\Ap\tgA\u0011\u0002\"\u00153\u0003\u0003\u0005\u001d\u0001b\u0015\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$s\u0007\u0005\u0004\u0005V\u0011mC1G\u0007\u0003\t/RA\u0001\"\u0017\u0002\u0016\u0005!Q.\u0019;i\u0013\u0011!i\u0006b\u0016\u0003\u0011M+W.\u001b:j]\u001eDqaa\u00183\u0001\u0004\tI+\u0006\u0003\u0005d\u0011-DC\u0002C3\t\u0017#i\t\u0006\u0004\u0005h\u0011}DQ\u0011\t\u0006\u0003[\u0001A\u0011\u000e\t\u0005\u0003k!Y\u0007B\u0006\u0002:M\u0002\u000b\u0011!AC\u0002\u0005m\u0002\u0006\u0004C6\u0003\u0017\"y\u0007b\u001d\u0005x\u0011m\u0014'C\u0012\u0002T\u0005UC\u0011OA,c\u001d!\u00131LA2\u0003K\t\u0014bIA4\u0003S\")(a\u001b2\u000f\u0011\nY&a\u0019\u0002&EJ1%!\u001d\u0002t\u0011e\u0014QO\u0019\bI\u0005m\u00131MA\u0013c%\u0019\u00131PA?\t{\ny(M\u0004%\u00037\n\u0019'!\n\t\u0013\u0011\u00055'!AA\u0004\u0011\r\u0015AC3wS\u0012,gnY3%qA1\u0011\u0011\\Ap\tSB\u0011\u0002b\"4\u0003\u0003\u0005\u001d\u0001\"#\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\b\u0005\u0004\u0005V\u0011mC\u0011\u000e\u0005\b\u0007?\u001a\u0004\u0019AAU\u0011\u001d\u0011\ta\ra\u0001\tS\nq\u0001[8su\u000e\fG/\u0006\u0003\u0005\u0014\u0012mE\u0003\u0002CK\tS#b\u0001b&\u0005\u001e\u0012\r\u0006CBA\u0017\u0005g#I\n\u0005\u0003\u00026\u0011mEaBA\u001di\t\u0007\u00111\b\u0005\n\t?#\u0014\u0011!a\u0002\tC\u000b1\"\u001a<jI\u0016t7-\u001a\u00132aA1\u0011\u0011\\Ap\t3C\u0011\u0002\"*5\u0003\u0003\u0005\u001d\u0001b*\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013'\r\t\u0007\u0007'\u001aI\u0006\"'\t\u000f\u0011-F\u00071\u0001\u0005.\u00069a/Z2u_J\u001c\bCBA\u0011\t_#\u0019,\u0003\u0003\u00052\u0006\r\"A\u0003\u001fsKB,\u0017\r^3e}A)\u0011Q\u0006\u0001\u0005\u001a\u00069a/\u001a:uG\u0006$X\u0003\u0002C]\t\u0003$B\u0001b/\u0005nRAAQ\u0018Cb\tC$9\u000fE\u0003\u0002.\u0001!y\f\u0005\u0003\u00026\u0011\u0005GaBA\u001dk\t\u0007\u00111\b\u0005\b\t\u000b,\u00049\u0001Cd\u0003\u0019\u0019\u0017M\\*fiBAA\u0011\u001aCk\t{#iL\u0004\u0003\u0005L\u0012EWB\u0001Cg\u0015\u0011!y-!\u0005\u0002\u0013=\u0004XM]1u_J\u001c\u0018\u0002\u0002Cj\t\u001b\fQa\u00149TKRLA\u0001b6\u0005Z\na\u0011J\u001c)mC\u000e,\u0017*\u001c9me%!A1\u001cCo\u0005\u0015)f)\u001e8d\u0015\u0011!y.!\u0006\u0002\u000f\u001d,g.\u001a:jG\"9A1]\u001bA\u0004\u0011\u0015\u0018\u0001\u0002<nC:\u0004b!!7\u0002`\u0012}\u0006b\u0002Cuk\u0001\u000fA1^\u0001\u0005u\u0016\u0014x\u000e\u0005\u0004\u0004T\reCq\u0018\u0005\b\tW+\u0004\u0019\u0001Cx!\u0019\t\t\u0003b,\u0005>\u0006\u00112-\u00198De\u0016\fG/\u001a.fe>\u001cH*[6f+\u0011!)0b\u0002\u0015\r\u0011]X\u0011BC\b!!!I\u0010b@\u0006\u0004\u0015\rQB\u0001C~\u0015\u0011!i0!\u0005\u0002\u000fM,\b\u000f]8si&!Q\u0011\u0001C~\u0005I\u0019\u0015M\\\"sK\u0006$XMW3s_Nd\u0015n[3\u0011\u000b\u00055\u0002!\"\u0002\u0011\t\u0005URq\u0001\u0003\b\u0003s1$\u0019AA\u001e\u0011%)YANA\u0001\u0002\b)i!A\u0006fm&$WM\\2fIE\u0012\u0004CBAm\u0003?,)\u0001C\u0005\u0006\u0012Y\n\t\u0011q\u0001\u0006\u0014\u0005YQM^5eK:\u001cW\rJ\u00194!\u0019\u0019\u0019f!\u0017\u0006\u0006\u0005\u00112-\u00198D_BLH)\u001a8tKZ+7\r^8s+\u0011)I\"\"\n\u0015\t\u0015mQq\u0005\t\u0007\ts,i\"\"\t\n\t\u0015}A1 \u0002\b\u0007\u0006t7i\u001c9z!\u0015\ti\u0003AC\u0012!\u0011\t)$\"\n\u0005\u000f\u0005erG1\u0001\u0002<!IQ\u0011F\u001c\u0002\u0002\u0003\u000fQ1F\u0001\fKZLG-\u001a8dK\u0012\nD\u0007\u0005\u0004\u0002Z\u0006}W1E\u0001\u0010\tZ{6-\u00198NCB4\u0016\r\\;fgV1Q\u0011GC\u001f\u000b\u001f\"B!b\r\u0006dAaA\u0011`C\u001b\u000bs)Y$\"\u0014\u0006b%!Qq\u0007C~\u00051\u0019\u0015M\\'baZ\u000bG.^3t!\u0015\ti\u0003AC\u001e!\u0011\t)$\"\u0010\u0005\u0017\u0005e\u0002\b)A\u0001\u0002\u000b\u0007\u00111\b\u0015\u000b\u000b{\tY%\"\u0011\u0006F\u0015%\u0013'C\u0012\u0002h\u0005%T1IA6c\u001d!\u00131LA2\u0003K\t\u0014bIA9\u0003g*9%!\u001e2\u000f\u0011\nY&a\u0019\u0002&EJ1%a\u0015\u0002V\u0015-\u0013qK\u0019\bI\u0005m\u00131MA\u0013!\u0011\t)$b\u0014\u0005\u0017\u0015E\u0003\b)A\u0001\u0002\u000b\u0007\u00111\b\u0002\u0003-JB#\"b\u0014\u0002L\u0015US\u0011LC/c%\u0019\u0013qMA5\u000b/\nY'M\u0004%\u00037\n\u0019'!\n2\u0013\r\n\t(a\u001d\u0006\\\u0005U\u0014g\u0002\u0013\u0002\\\u0005\r\u0014QE\u0019\nG\u0005M\u0013QKC0\u0003/\nt\u0001JA.\u0003G\n)\u0003E\u0003\u0002.\u0001)i\u0005C\u0004\u0002Vb\u0002\u001d!\"\u001a\u0011\r\u0005e\u0017q\\C'\u0003-!ekX:dC2\f'o\u00144\u0016\t\u0015-TqO\u000b\u0003\u000b[\u0002\u0002\u0002\"?\u0006p\u0015MTQO\u0005\u0005\u000bc\"YP\u0001\u0005TG\u0006d\u0017M](g!\u0015\ti\u0003AC;!\u0011\t)$b\u001e\u0005\u000f\u0015e\u0014H1\u0001\u0002<\t\tAK\u0001\u000eDC:T\u0016\u000e]'baZ\u000bG.^3t\t\u0016t7/\u001a,fGR|'/\u0006\u0004\u0006\u0000\u0015-U\u0011U\n\u0006u\u0005}Q\u0011\u0011\t\r\ts,\u0019)b\"\u0006\n\u0016}UqV\u0005\u0005\u000b\u000b#YPA\bDC:T\u0016\u000e]'baZ\u000bG.^3t!\u0015\ti\u0003ACE!\u0011\t)$b#\u0005\u0017\u0005e\"\b)A\u0001\u0002\u000b\u0007\u00111\b\u0015\r\u000b\u0017\u000bY%b$\u0006\u0014\u0016]U1T\u0019\nG\u0005M\u0013QKCI\u0003/\nt\u0001JA.\u0003G\n)#M\u0005$\u0003O\nI'\"&\u0002lE:A%a\u0017\u0002d\u0005\u0015\u0012'C\u0012\u0002r\u0005MT\u0011TA;c\u001d!\u00131LA2\u0003K\t\u0014bIA>\u0003{*i*a 2\u000f\u0011\nY&a\u0019\u0002&A!\u0011QGCQ\t-)\u0019K\u000fQ\u0001\u0002\u0003\u0015\r!a\u000f\u0003\u0005I3\u0006\u0006CCQ\u0003\u0017*9+b+2\u0013\r\n9'!\u001b\u0006*\u0006-\u0014g\u0002\u0013\u0002\\\u0005\r\u0014QE\u0019\nG\u0005M\u0013QKCW\u0003/\nt\u0001JA.\u0003G\n)\u0003E\u0003\u0002.\u0001)y*A\u0006fm&$WM\\2fIE*\u0004CBAm\u0003?,y\n\u0006\u0002\u00068R!Q\u0011XC_!\u001d)YLOCE\u000b?k\u0011A\u000b\u0005\b\u000bcc\u00049ACZ)\u0011)y+\"1\t\u000f\u0005UV\b1\u0001\u0002*\u0006\u0019Q.\u00199\u0015\u0011\u0015=VqYCf\u000b\u001fDq!\"3?\u0001\u0004)9)\u0001\u0003ge>l\u0007bBCg}\u0001\u0007QqQ\u0001\u0006MJ|WN\r\u0005\b\u0005[r\u0004\u0019ACi!)\t\t#b5\u0006\n\u0016%UqT\u0005\u0005\u000b+\f\u0019CA\u0005Gk:\u001cG/[8oe\u00051!0\u001b9NCB,b!b7\u0006b\u0016\u0015H\u0003BCo\u000bS\u0004r!b/;\u000b?,\u0019\u000f\u0005\u0003\u00026\u0015\u0005HaBA\u001d\u007f\t\u0007\u00111\b\t\u0005\u0003k))\u000fB\u0004\u0006h~\u0012\r!a\u000f\u0003\u0003IC\u0011\"b;@\u0003\u0003\u0005\u001d!\"<\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013G\u000e\t\u0007\u00033\fy.b9\u0002\u0011iL\u0007/T1q?\u0012,\"!b=\u0011\u000f\u0015m&(\">\u0006vB!\u0011\u0011EC|\u0013\u0011)I0a\t\u0003\r\u0011{WO\u00197f\u0003%Q\u0018\u000e]'ba~#\u0007%\u0001\u0005{SBl\u0015\r]0g+\t1\t\u0001E\u0004\u0006<j2\u0019Ab\u0001\u0011\t\u0005\u0005bQA\u0005\u0005\r\u000f\t\u0019CA\u0003GY>\fG/A\u0005{SBl\u0015\r]0gA\u0005A!0\u001b9NCB|\u0016.\u0006\u0002\u0007\u0010A9Q1\u0018\u001e\u0002*\u0006%\u0016!\u0003>ja6\u000b\u0007oX5!\u0005u\u0019\u0015M\u001c.ja6\u000b\u0007oS3z-\u0006dW/Z:EK:\u001cXMV3di>\u0014XC\u0002D\f\rG1IdE\u0003G\u0003?1I\u0002\u0005\b\u0005z\u001amaqDAU\rC19D\"\u0012\n\t\u0019uA1 \u0002\u0013\u0007\u0006t',\u001b9NCB\\U-\u001f,bYV,7\u000fE\u0003\u0002.\u00011\t\u0003\u0005\u0003\u00026\u0019\rBaCA\u001d\r\u0002\u0006\t\u0011!b\u0001\u0003wACBb\t\u0002L\u0019\u001db1\u0006D\u0018\rg\t\u0014bIA*\u0003+2I#a\u00162\u000f\u0011\nY&a\u0019\u0002&EJ1%a\u001a\u0002j\u00195\u00121N\u0019\bI\u0005m\u00131MA\u0013c%\u0019\u0013\u0011OA:\rc\t)(M\u0004%\u00037\n\u0019'!\n2\u0013\r\nY(! \u00076\u0005}\u0014g\u0002\u0013\u0002\\\u0005\r\u0014Q\u0005\t\u0005\u0003k1I\u0004B\u0006\u0006$\u001a\u0003\u000b\u0011!AC\u0002\u0005m\u0002\u0006\u0003D\u001d\u0003\u00172iD\"\u00112\u0013\r\n9'!\u001b\u0007@\u0005-\u0014g\u0002\u0013\u0002\\\u0005\r\u0014QE\u0019\nG\u0005M\u0013Q\u000bD\"\u0003/\nt\u0001JA.\u0003G\n)\u0003E\u0003\u0002.\u000119$A\u0006fm&$WM\\2fIE:\u0004CBAm\u0003?49\u0004\u0006\u0002\u0007NQ!aq\nD)!\u001d)YL\u0012D\u0011\roAqAb\u0012I\u0001\b1I\u0005\u0006\u0003\u0007F\u0019U\u0003bBA[\u0013\u0002\u0007\u0011\u0011\u0016\u000b\t\r\u000b2IFb\u0017\u0007^!9Q\u0011\u001a&A\u0002\u0019}\u0001bBCg\u0015\u0002\u0007aq\u0004\u0005\b\u0005[R\u0005\u0019\u0001D0!1\t\tC\"\u0019\u0002*\u001a\u0005b\u0011\u0005D\u001c\u0013\u00111\u0019'a\t\u0003\u0013\u0019+hn\u0019;j_:\u001c\u0014!C7ba\u0006\u001bG/\u001b<f)!1)E\"\u001b\u0007l\u00195\u0004bBCe\u0017\u0002\u0007aq\u0004\u0005\b\u000b\u001b\\\u0005\u0019\u0001D\u0010\u0011\u001d\u0011ig\u0013a\u0001\r?\n\u0001B_5q\u001b\u0006\u00048JV\u000b\u0007\rg2IH\" \u0015\t\u0019Udq\u0010\t\b\u000bw3eq\u000fD>!\u0011\t)D\"\u001f\u0005\u000f\u0005eBJ1\u0001\u0002<A!\u0011Q\u0007D?\t\u001d)9\u000f\u0014b\u0001\u0003wA\u0011B\"!M\u0003\u0003\u0005\u001dAb!\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007\u000f\t\u0007\u00033\fyNb\u001f\u0002\u000bM\u0004\u0018mY3\u0016\t\u0019%eQ\u0013\u000b\u0007\r\u00173IJb)\u0011\u0015\u0011UcQ\u0012DI\u0003S3\u0019*\u0003\u0003\u0007\u0010\u0012]#\u0001H'vi\u0006\u0014G.\u001a$j]&$XmQ8pe\u0012Lg.\u0019;f\r&,G\u000e\u001a\t\u0006\u0003[\u0001a1\u0013\t\u0005\u0003k1)\nB\u0004\u0007\u00186\u0013\r!a\u000f\u0003\u0003\u0015CqAb'N\u0001\b1i*A\u0003gS\u0016dG\r\u0005\u0004\u0005V\u0019}e1S\u0005\u0005\rC#9FA\u0003GS\u0016dG\rC\u0004\u0002V6\u0003\u001dA\"*\u0011\r\u0005e\u0017q\u001cDJ\u00031\u0019\b/Y2f?\u0012{WO\u00197f+\t1Y\u000b\u0005\u0006\u0005V\u00195eQVAU\u000bk\u0004R!!\f\u0001\u000bk\fQb\u001d9bG\u0016|Fi\\;cY\u0016\u0004\u0013aC:qC\u000e,wL\u00127pCR,\"A\".\u0011\u0015\u0011UcQ\u0012D\\\u0003S3\u0019\u0001E\u0003\u0002.\u00011\u0019!\u0001\u0007ta\u0006\u001cWm\u0018$m_\u0006$\b%A\u0005ta\u0006\u001cWmX%oiV\u0011aq\u0018\t\u000b\t+2iI\"1\u0002*\u0006%\u0006#BA\u0017\u0001\u0005%\u0016AC:qC\u000e,w,\u00138uA\u0005Q1\u000f]1dK~cuN\\4\u0016\u0005\u0019%\u0007C\u0003C+\r\u001b3Y-!+\u0007NB)\u0011Q\u0006\u0001\u0007NB!\u0011\u0011\u0005Dh\u0013\u00111\t.a\t\u0003\t1{gnZ\u0001\fgB\f7-Z0M_:<\u0007%A\tUkBdW-S:p[>\u0014\b\u000f[5t[N\u00042!b/X\u0005E!V\u000f\u001d7f\u0013N|Wn\u001c:qQ&\u001cXn]\n\u0004/\u0006}AC\u0001Dl\u00039!w.\u001e2mK&\u001bh+Z2u_J\u00042Ab9[\u001b\u00059&A\u00043pk\ndW-S:WK\u000e$xN]\n\u00065\u0006}a\u0011\u001e\t\t\rW4\t0\">\u0007.6\u0011aQ\u001e\u0006\u0005\r_\f)\"\u0001\u0003vi&d\u0017\u0002\u0002Dz\r[\u00141\"S:p[>\u0014\b\u000f[5t[R\u0011a\u0011]\u0001\bM>\u0014x/\u0019:e)\u00111iKb?\t\u000f\u0019uH\f1\u0001\u0006v\u0006\tA/\u0001\u0005cC\u000e\\w/\u0019:e)\u0011))pb\u0001\t\u000f\u0019uX\f1\u0001\u0007.\u0006y\u0001\u000fZ8vE2,\u0017j\u001d,fGR|'\u000fE\u0002\u0007d\u0002\u0014q\u0002\u001d3pk\ndW-S:WK\u000e$xN]\n\u0006A\u0006}qQ\u0002\t\t\rW4\tpb\u0004\u0007.BA\u0011\u0011\u0005B\u0010\u000bk,)\u0010\u0006\u0002\b\bQ!aQVD\u000b\u0011\u001d1iP\u0019a\u0001\u000f\u001f!Bab\u0004\b\u001a!9aQ`2A\u0002\u00195&AD*fe&\fG.\u001b>fI\u001a{'/\\\n\bK\u0006}\u00111RD\u0010!\u0011\t\tc\"\t\n\t\u001d\r\u00121\u0005\u0002\b!J|G-^2u+\t99\u0003\r\u0003\b*\u001d5\u0002CBA\u0011\u0003?;Y\u0003\u0005\u0003\u00026\u001d5BaCD\u0018O\u0006\u0005\t\u0011!B\u0001\u0003w\u00111a\u0018\u00132))9\u0019d\"\u000e\b@\u001d\u0005s1\t\t\u0004\u000bw+\u0007bBAM]\u0002\u0007qq\u0007\u0019\u0005\u000fs9i\u0004\u0005\u0004\u0002\"\u0005}u1\b\t\u0005\u0003k9i\u0004\u0002\u0007\b0\u001dU\u0012\u0011!A\u0001\u0006\u0003\tY\u0004C\u0004\u0002&:\u0004\r!!+\t\u000f\u0005Ef\u000e1\u0001\u0002*\"9\u0011Q\u00178A\u0002\u0005%\u0016a\u0003:fC\u0012\u0014Vm]8mm\u0016DSa\u001cBq\u0005g$\"bb\r\bL\u001d5sqJD)\u0011%\tI\n\u001dI\u0001\u0002\u000499\u0004C\u0005\u0002&B\u0004\n\u00111\u0001\u0002*\"I\u0011\u0011\u00179\u0011\u0002\u0003\u0007\u0011\u0011\u0016\u0005\n\u0003k\u0003\b\u0013!a\u0001\u0003S\u000babY8qs\u0012\"WMZ1vYR$\u0013'\u0006\u0002\bXA\"q\u0011LD0U\u00119YFa'\u0011\r\u0005\u0005\u0012qTD/!\u0011\t)db\u0018\u0005\u0017\u001d=\u0012/!A\u0001\u0002\u000b\u0005\u00111H\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIM\nabY8qs\u0012\"WMZ1vYR$C'A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u000fW\u0002BA!5\bn%!!1\nBj\u00031\u0001(o\u001c3vGR\f%/\u001b;z\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!a\u0011\bv!IqqO<\u0002\u0002\u0003\u0007\u0011\u0011V\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u001du\u0004CBD@\u000f\u000b\u000b\u0019%\u0004\u0002\b\u0002*!q1QA\u0012\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u000579\t)\u0001\u0005dC:,\u0015/^1m)\u0011\u00119ab#\t\u0013\u001d]\u00140!AA\u0002\u0005\r\u0013A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$Bab\u001b\b\u0012\"Iqq\u000f>\u0002\u0002\u0003\u0007\u0011\u0011\u0016\u000b\u0003\u000fW\"BAa\u0002\b\u0018\"IqqO?\u0002\u0002\u0003\u0007\u00111\t\u0015\bK\u000e%1qBB\t\u00039\u0019VM]5bY&TX\r\u001a$pe6\u00042!b/\u0000'\u0015yx\u0011UB\u0011!99\u0019k\"+\b.\u0006%\u0016\u0011VAU\u000fgi!a\"*\u000b\t\u001d\u001d\u00161E\u0001\beVtG/[7f\u0013\u00119Yk\"*\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>tG\u0007\r\u0003\b0\u001eM\u0006CBA\u0011\u0003?;\t\f\u0005\u0003\u00026\u001dMFaCD\u0018\u007f\u0006\u0005\t\u0011!B\u0001\u0003w!\"a\"(\u0015\u0015\u001dMr\u0011XDb\u000f\u000b<9\r\u0003\u0005\u0002\u001a\u0006\u0015\u0001\u0019AD^a\u00119il\"1\u0011\r\u0005\u0005\u0012qTD`!\u0011\t)d\"1\u0005\u0019\u001d=r\u0011XA\u0001\u0002\u0003\u0015\t!a\u000f\t\u0011\u0005\u0015\u0016Q\u0001a\u0001\u0003SC\u0001\"!-\u0002\u0006\u0001\u0007\u0011\u0011\u0016\u0005\t\u0003k\u000b)\u00011\u0001\u0002*\u00069QO\\1qa2LH\u0003BDg\u000fC\u0004b!!\t\bP\u001eM\u0017\u0002BDi\u0003G\u0011aa\u00149uS>t\u0007\u0003DA\u0011\u000f+<I.!+\u0002*\u0006%\u0016\u0002BDl\u0003G\u0011a\u0001V;qY\u0016$\u0004\u0007BDn\u000f?\u0004b!!\t\u0002 \u001eu\u0007\u0003BA\u001b\u000f?$Abb\f\u0002\b\u0005\u0005\t\u0011!B\u0001\u0003wA!bb9\u0002\b\u0005\u0005\t\u0019AD\u001a\u0003\rAH\u0005M\u0001\u0005S:LG\u000f\u000b\u0003\u0002\f\u001d%\b\u0003BA\u0011\u000fWLAa\"<\u0002$\tAan\\5oY&tW\r"
)
public class DenseVector implements StorageVector, Serializable {
   private static final long serialVersionUID = 1L;
   public final Object data;
   private final int offset;
   private final int stride;
   private final int length;
   private final boolean noOffsetOrStride;

   public static MutableFiniteCoordinateField space_Long() {
      return DenseVector$.MODULE$.space_Long();
   }

   public static MutableFiniteCoordinateField space_Int() {
      return DenseVector$.MODULE$.space_Int();
   }

   public static MutableFiniteCoordinateField space_Float() {
      return DenseVector$.MODULE$.space_Float();
   }

   public static MutableFiniteCoordinateField space_Double() {
      return DenseVector$.MODULE$.space_Double();
   }

   public static MutableFiniteCoordinateField space(final Field field, final ClassTag man) {
      return DenseVector$.MODULE$.space(field, man);
   }

   public static CanZipMapKeyValuesDenseVector zipMapKV(final ClassTag evidence$18) {
      return DenseVector$.MODULE$.zipMapKV(evidence$18);
   }

   public static CanZipMapValuesDenseVector zipMap_i() {
      return DenseVector$.MODULE$.zipMap_i();
   }

   public static CanZipMapValuesDenseVector zipMap_f() {
      return DenseVector$.MODULE$.zipMap_f();
   }

   public static CanZipMapValuesDenseVector zipMap_d() {
      return DenseVector$.MODULE$.zipMap_d();
   }

   public static CanZipMapValuesDenseVector zipMap(final ClassTag evidence$16) {
      return DenseVector$.MODULE$.zipMap(evidence$16);
   }

   public static ScalarOf DV_scalarOf() {
      return DenseVector$.MODULE$.DV_scalarOf();
   }

   public static CanMapValues DV_canMapValues(final ClassTag man) {
      return DenseVector$.MODULE$.DV_canMapValues(man);
   }

   public static CanCopy canCopyDenseVector(final ClassTag evidence$14) {
      return DenseVector$.MODULE$.canCopyDenseVector(evidence$14);
   }

   public static CanCreateZerosLike canCreateZerosLike(final ClassTag evidence$12, final Zero evidence$13) {
      return DenseVector$.MODULE$.canCreateZerosLike(evidence$12, evidence$13);
   }

   public static DenseVector vertcat(final Seq vectors, final UFunc.InPlaceImpl2 canSet, final ClassTag vman, final Zero zero) {
      return DenseVector$.MODULE$.vertcat(vectors, canSet, vman, zero);
   }

   public static DenseMatrix horzcat(final Seq vectors, final ClassTag evidence$10, final Zero evidence$11) {
      return DenseVector$.MODULE$.horzcat(vectors, evidence$10, evidence$11);
   }

   public static DenseVector fill(final int size, final Object v, final ClassTag evidence$8, final Semiring evidence$9) {
      return DenseVector$.MODULE$.fill(size, v, evidence$8, evidence$9);
   }

   public static DenseVector ones(final int size, final ClassTag evidence$6, final Semiring evidence$7) {
      return DenseVector$.MODULE$.ones(size, evidence$6, evidence$7);
   }

   public static DenseVector create(final Object data, final int offset, final int stride, final int length) {
      return DenseVector$.MODULE$.create(data, offset, stride, length);
   }

   public static DenseVector fill(final int size, final Function0 v, final ClassTag evidence$5) {
      return DenseVector$.MODULE$.fill(size, v, evidence$5);
   }

   public static DenseVector tabulate(final Range range, final Function1 f, final ClassTag evidence$4) {
      return DenseVector$.MODULE$.tabulate(range, f, evidence$4);
   }

   public static DenseVector tabulate(final int size, final Function1 f, final ClassTag evidence$3) {
      return DenseVector$.MODULE$.tabulate(size, f, evidence$3);
   }

   public static DenseVector zeros(final int size, final ClassTag evidence$1, final Zero evidence$2) {
      return DenseVector$.MODULE$.zeros(size, evidence$1, evidence$2);
   }

   public static double rangeD$default$3() {
      return DenseVector$.MODULE$.rangeD$default$3();
   }

   public static Vector rangeD(final double start, final double end, final double step) {
      return DenseVector$.MODULE$.rangeD(start, end, step);
   }

   public static float rangeF$default$3() {
      return DenseVector$.MODULE$.rangeF$default$3();
   }

   public static Vector rangeF(final float start, final float end, final float step) {
      return DenseVector$.MODULE$.rangeF(start, end, step);
   }

   public static Vector range(final int start, final int end, final int step) {
      return DenseVector$.MODULE$.range(start, end, step);
   }

   public static Vector range(final int start, final int end) {
      return DenseVector$.MODULE$.range(start, end);
   }

   public static Rand rand$default$2() {
      return DenseVector$.MODULE$.rand$default$2();
   }

   public static Vector rand(final int size, final Rand rand, final ClassTag evidence$11) {
      return DenseVector$.MODULE$.rand(size, rand, evidence$11);
   }

   public static CanCreateZeros canCreateZeros(final ClassTag evidence$9, final Zero evidence$10) {
      return DenseVector$.MODULE$.canCreateZeros(evidence$9, evidence$10);
   }

   public int iterableSize() {
      return Storage.iterableSize$(this);
   }

   public Set keySet() {
      return Vector.keySet$(this);
   }

   public int size() {
      return Vector.size$(this);
   }

   public Iterator iterator() {
      return Vector.iterator$(this);
   }

   public Iterator valuesIterator() {
      return Vector.valuesIterator$(this);
   }

   public Iterator keysIterator() {
      return Vector.keysIterator$(this);
   }

   public DenseVector toDenseVector(final ClassTag cm) {
      return Vector.toDenseVector$(this, cm);
   }

   public DenseVector toDenseVector$mcD$sp(final ClassTag cm) {
      return Vector.toDenseVector$mcD$sp$(this, cm);
   }

   public DenseVector toDenseVector$mcF$sp(final ClassTag cm) {
      return Vector.toDenseVector$mcF$sp$(this, cm);
   }

   public DenseVector toDenseVector$mcI$sp(final ClassTag cm) {
      return Vector.toDenseVector$mcI$sp$(this, cm);
   }

   public Vector toVector(final ClassTag cm) {
      return Vector.toVector$(this, cm);
   }

   public Vector toVector$mcD$sp(final ClassTag cm) {
      return Vector.toVector$mcD$sp$(this, cm);
   }

   public Vector toVector$mcF$sp(final ClassTag cm) {
      return Vector.toVector$mcF$sp$(this, cm);
   }

   public Vector toVector$mcI$sp(final ClassTag cm) {
      return Vector.toVector$mcI$sp$(this, cm);
   }

   public Vector padTo(final int len, final Object elem, final ClassTag cm) {
      return Vector.padTo$(this, len, elem, cm);
   }

   public Vector padTo$mcD$sp(final int len, final double elem, final ClassTag cm) {
      return Vector.padTo$mcD$sp$(this, len, elem, cm);
   }

   public Vector padTo$mcF$sp(final int len, final float elem, final ClassTag cm) {
      return Vector.padTo$mcF$sp$(this, len, elem, cm);
   }

   public Vector padTo$mcI$sp(final int len, final int elem, final ClassTag cm) {
      return Vector.padTo$mcI$sp$(this, len, elem, cm);
   }

   public boolean exists(final Function1 f) {
      return Vector.exists$(this, f);
   }

   public boolean exists$mcD$sp(final Function1 f) {
      return Vector.exists$mcD$sp$(this, f);
   }

   public boolean exists$mcF$sp(final Function1 f) {
      return Vector.exists$mcF$sp$(this, f);
   }

   public boolean exists$mcI$sp(final Function1 f) {
      return Vector.exists$mcI$sp$(this, f);
   }

   public boolean forall(final Function1 f) {
      return Vector.forall$(this, f);
   }

   public boolean forall$mcD$sp(final Function1 f) {
      return Vector.forall$mcD$sp$(this, f);
   }

   public boolean forall$mcF$sp(final Function1 f) {
      return Vector.forall$mcF$sp$(this, f);
   }

   public boolean forall$mcI$sp(final Function1 f) {
      return Vector.forall$mcI$sp$(this, f);
   }

   public Object fold(final Object z, final Function2 op) {
      return Vector.fold$(this, z, op);
   }

   public Object fold$mcD$sp(final Object z, final Function2 op) {
      return Vector.fold$mcD$sp$(this, z, op);
   }

   public Object fold$mcF$sp(final Object z, final Function2 op) {
      return Vector.fold$mcF$sp$(this, z, op);
   }

   public Object fold$mcI$sp(final Object z, final Function2 op) {
      return Vector.fold$mcI$sp$(this, z, op);
   }

   public Object foldLeft(final Object z, final Function2 op) {
      return Vector.foldLeft$(this, z, op);
   }

   public Object foldLeft$mcD$sp(final Object z, final Function2 op) {
      return Vector.foldLeft$mcD$sp$(this, z, op);
   }

   public Object foldLeft$mcF$sp(final Object z, final Function2 op) {
      return Vector.foldLeft$mcF$sp$(this, z, op);
   }

   public Object foldLeft$mcI$sp(final Object z, final Function2 op) {
      return Vector.foldLeft$mcI$sp$(this, z, op);
   }

   public Object foldRight(final Object z, final Function2 op) {
      return Vector.foldRight$(this, z, op);
   }

   public Object foldRight$mcD$sp(final Object z, final Function2 op) {
      return Vector.foldRight$mcD$sp$(this, z, op);
   }

   public Object foldRight$mcF$sp(final Object z, final Function2 op) {
      return Vector.foldRight$mcF$sp$(this, z, op);
   }

   public Object foldRight$mcI$sp(final Object z, final Function2 op) {
      return Vector.foldRight$mcI$sp$(this, z, op);
   }

   public Object reduce(final Function2 op) {
      return Vector.reduce$(this, op);
   }

   public Object reduce$mcD$sp(final Function2 op) {
      return Vector.reduce$mcD$sp$(this, op);
   }

   public Object reduce$mcF$sp(final Function2 op) {
      return Vector.reduce$mcF$sp$(this, op);
   }

   public Object reduce$mcI$sp(final Function2 op) {
      return Vector.reduce$mcI$sp$(this, op);
   }

   public Object reduceLeft(final Function2 op) {
      return Vector.reduceLeft$(this, op);
   }

   public Object reduceLeft$mcD$sp(final Function2 op) {
      return Vector.reduceLeft$mcD$sp$(this, op);
   }

   public Object reduceLeft$mcF$sp(final Function2 op) {
      return Vector.reduceLeft$mcF$sp$(this, op);
   }

   public Object reduceLeft$mcI$sp(final Function2 op) {
      return Vector.reduceLeft$mcI$sp$(this, op);
   }

   public Object reduceRight(final Function2 op) {
      return Vector.reduceRight$(this, op);
   }

   public Object reduceRight$mcD$sp(final Function2 op) {
      return Vector.reduceRight$mcD$sp$(this, op);
   }

   public Object reduceRight$mcF$sp(final Function2 op) {
      return Vector.reduceRight$mcF$sp$(this, op);
   }

   public Object reduceRight$mcI$sp(final Function2 op) {
      return Vector.reduceRight$mcI$sp$(this, op);
   }

   public Vector scan(final Object z, final Function2 op, final ClassTag cm, final ClassTag cm1) {
      return Vector.scan$(this, z, op, cm, cm1);
   }

   public Vector scan$mcD$sp(final Object z, final Function2 op, final ClassTag cm, final ClassTag cm1) {
      return Vector.scan$mcD$sp$(this, z, op, cm, cm1);
   }

   public Vector scan$mcF$sp(final Object z, final Function2 op, final ClassTag cm, final ClassTag cm1) {
      return Vector.scan$mcF$sp$(this, z, op, cm, cm1);
   }

   public Vector scan$mcI$sp(final Object z, final Function2 op, final ClassTag cm, final ClassTag cm1) {
      return Vector.scan$mcI$sp$(this, z, op, cm, cm1);
   }

   public Vector scanLeft(final Object z, final Function2 op, final ClassTag cm1) {
      return Vector.scanLeft$(this, z, op, cm1);
   }

   public Vector scanLeft$mcD$sp(final Object z, final Function2 op, final ClassTag cm1) {
      return Vector.scanLeft$mcD$sp$(this, z, op, cm1);
   }

   public Vector scanLeft$mcF$sp(final Object z, final Function2 op, final ClassTag cm1) {
      return Vector.scanLeft$mcF$sp$(this, z, op, cm1);
   }

   public Vector scanLeft$mcI$sp(final Object z, final Function2 op, final ClassTag cm1) {
      return Vector.scanLeft$mcI$sp$(this, z, op, cm1);
   }

   public Vector scanRight(final Object z, final Function2 op, final ClassTag cm1) {
      return Vector.scanRight$(this, z, op, cm1);
   }

   public Vector scanRight$mcD$sp(final Object z, final Function2 op, final ClassTag cm1) {
      return Vector.scanRight$mcD$sp$(this, z, op, cm1);
   }

   public Vector scanRight$mcF$sp(final Object z, final Function2 op, final ClassTag cm1) {
      return Vector.scanRight$mcF$sp$(this, z, op, cm1);
   }

   public Vector scanRight$mcI$sp(final Object z, final Function2 op, final ClassTag cm1) {
      return Vector.scanRight$mcI$sp$(this, z, op, cm1);
   }

   public Object map(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike.map$(this, fn, canMapValues);
   }

   public Object map$mcZ$sp(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike.map$mcZ$sp$(this, fn, canMapValues);
   }

   public Object map$mcB$sp(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike.map$mcB$sp$(this, fn, canMapValues);
   }

   public Object map$mcC$sp(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike.map$mcC$sp$(this, fn, canMapValues);
   }

   public Object map$mcD$sp(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike.map$mcD$sp$(this, fn, canMapValues);
   }

   public Object map$mcF$sp(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike.map$mcF$sp$(this, fn, canMapValues);
   }

   public Object map$mcI$sp(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike.map$mcI$sp$(this, fn, canMapValues);
   }

   public Object map$mcJ$sp(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike.map$mcJ$sp$(this, fn, canMapValues);
   }

   public Object map$mcS$sp(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike.map$mcS$sp$(this, fn, canMapValues);
   }

   public Object map$mcV$sp(final Function1 fn, final CanMapValues canMapValues) {
      return VectorLike.map$mcV$sp$(this, fn, canMapValues);
   }

   public void foreach$mcZ$sp(final Function1 fn) {
      VectorLike.foreach$mcZ$sp$(this, fn);
   }

   public void foreach$mcB$sp(final Function1 fn) {
      VectorLike.foreach$mcB$sp$(this, fn);
   }

   public void foreach$mcC$sp(final Function1 fn) {
      VectorLike.foreach$mcC$sp$(this, fn);
   }

   public void foreach$mcS$sp(final Function1 fn) {
      VectorLike.foreach$mcS$sp$(this, fn);
   }

   public void foreach$mcV$sp(final Function1 fn) {
      VectorLike.foreach$mcV$sp$(this, fn);
   }

   public double apply$mcID$sp(final int i) {
      return TensorLike.apply$mcID$sp$(this, i);
   }

   public float apply$mcIF$sp(final int i) {
      return TensorLike.apply$mcIF$sp$(this, i);
   }

   public int apply$mcII$sp(final int i) {
      return TensorLike.apply$mcII$sp$(this, i);
   }

   public long apply$mcIJ$sp(final int i) {
      return TensorLike.apply$mcIJ$sp$(this, i);
   }

   public void update$mcID$sp(final int i, final double v) {
      TensorLike.update$mcID$sp$(this, i, v);
   }

   public void update$mcIF$sp(final int i, final float v) {
      TensorLike.update$mcIF$sp$(this, i, v);
   }

   public void update$mcII$sp(final int i, final int v) {
      TensorLike.update$mcII$sp$(this, i, v);
   }

   public void update$mcIJ$sp(final int i, final long v) {
      TensorLike.update$mcIJ$sp$(this, i, v);
   }

   public TensorKeys keys() {
      return TensorLike.keys$(this);
   }

   public TensorValues values() {
      return TensorLike.values$(this);
   }

   public TensorPairs pairs() {
      return TensorLike.pairs$(this);
   }

   public TensorActive active() {
      return TensorLike.active$(this);
   }

   public Object apply(final Object slice, final CanSlice canSlice) {
      return TensorLike.apply$(this, slice, canSlice);
   }

   public Object apply(final Object a, final Object b, final Object c, final Seq slice, final CanSlice canSlice) {
      return TensorLike.apply$(this, a, b, c, slice, canSlice);
   }

   public Object apply$mcI$sp(final int a, final int b, final int c, final Seq slice, final CanSlice canSlice) {
      return TensorLike.apply$mcI$sp$(this, a, b, c, slice, canSlice);
   }

   public Object apply(final Object slice1, final Object slice2, final CanSlice2 canSlice) {
      return TensorLike.apply$(this, slice1, slice2, canSlice);
   }

   public Object mapPairs(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike.mapPairs$(this, f, bf);
   }

   public Object mapPairs$mcID$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike.mapPairs$mcID$sp$(this, f, bf);
   }

   public Object mapPairs$mcIF$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike.mapPairs$mcIF$sp$(this, f, bf);
   }

   public Object mapPairs$mcII$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike.mapPairs$mcII$sp$(this, f, bf);
   }

   public Object mapPairs$mcIJ$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike.mapPairs$mcIJ$sp$(this, f, bf);
   }

   public Object mapActivePairs(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike.mapActivePairs$(this, f, bf);
   }

   public Object mapActivePairs$mcID$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike.mapActivePairs$mcID$sp$(this, f, bf);
   }

   public Object mapActivePairs$mcIF$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike.mapActivePairs$mcIF$sp$(this, f, bf);
   }

   public Object mapActivePairs$mcII$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike.mapActivePairs$mcII$sp$(this, f, bf);
   }

   public Object mapActivePairs$mcIJ$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike.mapActivePairs$mcIJ$sp$(this, f, bf);
   }

   public Object mapValues(final Function1 f, final CanMapValues bf) {
      return TensorLike.mapValues$(this, f, bf);
   }

   public Object mapValues$mcD$sp(final Function1 f, final CanMapValues bf) {
      return TensorLike.mapValues$mcD$sp$(this, f, bf);
   }

   public Object mapValues$mcF$sp(final Function1 f, final CanMapValues bf) {
      return TensorLike.mapValues$mcF$sp$(this, f, bf);
   }

   public Object mapValues$mcI$sp(final Function1 f, final CanMapValues bf) {
      return TensorLike.mapValues$mcI$sp$(this, f, bf);
   }

   public Object mapValues$mcJ$sp(final Function1 f, final CanMapValues bf) {
      return TensorLike.mapValues$mcJ$sp$(this, f, bf);
   }

   public Object mapActiveValues(final Function1 f, final CanMapValues bf) {
      return TensorLike.mapActiveValues$(this, f, bf);
   }

   public Object mapActiveValues$mcD$sp(final Function1 f, final CanMapValues bf) {
      return TensorLike.mapActiveValues$mcD$sp$(this, f, bf);
   }

   public Object mapActiveValues$mcF$sp(final Function1 f, final CanMapValues bf) {
      return TensorLike.mapActiveValues$mcF$sp$(this, f, bf);
   }

   public Object mapActiveValues$mcI$sp(final Function1 f, final CanMapValues bf) {
      return TensorLike.mapActiveValues$mcI$sp$(this, f, bf);
   }

   public Object mapActiveValues$mcJ$sp(final Function1 f, final CanMapValues bf) {
      return TensorLike.mapActiveValues$mcJ$sp$(this, f, bf);
   }

   public void foreachKey(final Function1 fn) {
      TensorLike.foreachKey$(this, fn);
   }

   public void foreachKey$mcI$sp(final Function1 fn) {
      TensorLike.foreachKey$mcI$sp$(this, fn);
   }

   public void foreachPair(final Function2 fn) {
      TensorLike.foreachPair$(this, fn);
   }

   public void foreachPair$mcID$sp(final Function2 fn) {
      TensorLike.foreachPair$mcID$sp$(this, fn);
   }

   public void foreachPair$mcIF$sp(final Function2 fn) {
      TensorLike.foreachPair$mcIF$sp$(this, fn);
   }

   public void foreachPair$mcII$sp(final Function2 fn) {
      TensorLike.foreachPair$mcII$sp$(this, fn);
   }

   public void foreachPair$mcIJ$sp(final Function2 fn) {
      TensorLike.foreachPair$mcIJ$sp$(this, fn);
   }

   public void foreachValue(final Function1 fn) {
      TensorLike.foreachValue$(this, fn);
   }

   public void foreachValue$mcD$sp(final Function1 fn) {
      TensorLike.foreachValue$mcD$sp$(this, fn);
   }

   public void foreachValue$mcF$sp(final Function1 fn) {
      TensorLike.foreachValue$mcF$sp$(this, fn);
   }

   public void foreachValue$mcI$sp(final Function1 fn) {
      TensorLike.foreachValue$mcI$sp$(this, fn);
   }

   public void foreachValue$mcJ$sp(final Function1 fn) {
      TensorLike.foreachValue$mcJ$sp$(this, fn);
   }

   public boolean forall(final Function2 fn) {
      return TensorLike.forall$(this, (Function2)fn);
   }

   public boolean forall$mcID$sp(final Function2 fn) {
      return TensorLike.forall$mcID$sp$(this, fn);
   }

   public boolean forall$mcIF$sp(final Function2 fn) {
      return TensorLike.forall$mcIF$sp$(this, fn);
   }

   public boolean forall$mcII$sp(final Function2 fn) {
      return TensorLike.forall$mcII$sp$(this, fn);
   }

   public boolean forall$mcIJ$sp(final Function2 fn) {
      return TensorLike.forall$mcIJ$sp$(this, fn);
   }

   public boolean forall$mcJ$sp(final Function1 fn) {
      return TensorLike.forall$mcJ$sp$(this, fn);
   }

   public final Object $plus(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$plus$(this, b, op);
   }

   public final Object $colon$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$eq$(this, b, op);
   }

   public final Object $colon$plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$plus$eq$(this, b, op);
   }

   public final Object $colon$times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$times$eq$(this, b, op);
   }

   public final Object $plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$plus$eq$(this, b, op);
   }

   public final Object $times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$times$eq$(this, b, op);
   }

   public final Object $colon$minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$minus$eq$(this, b, op);
   }

   public final Object $colon$percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$percent$eq$(this, b, op);
   }

   public final Object $percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$percent$eq$(this, b, op);
   }

   public final Object $minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$minus$eq$(this, b, op);
   }

   public final Object $colon$div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$div$eq$(this, b, op);
   }

   public final Object $colon$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$up$eq$(this, b, op);
   }

   public final Object $div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$div$eq$(this, b, op);
   }

   public final Object $less$colon$less(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$less$colon$less$(this, b, op);
   }

   public final Object $less$colon$eq(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$less$colon$eq$(this, b, op);
   }

   public final Object $greater$colon$greater(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$greater$colon$greater$(this, b, op);
   }

   public final Object $greater$colon$eq(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$greater$colon$eq$(this, b, op);
   }

   public final Object $colon$amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$amp$eq$(this, b, op);
   }

   public final Object $colon$bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$bar$eq$(this, b, op);
   }

   public final Object $colon$up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$up$up$eq$(this, b, op);
   }

   public final Object $amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$amp$eq$(this, b, op);
   }

   public final Object $bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$bar$eq$(this, b, op);
   }

   public final Object $up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$up$up$eq$(this, b, op);
   }

   public final Object $plus$colon$plus(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$plus$colon$plus$(this, b, op);
   }

   public final Object $times$colon$times(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$times$colon$times$(this, b, op);
   }

   public final Object $colon$eq$eq(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$colon$eq$eq$(this, b, op);
   }

   public final Object $colon$bang$eq(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$colon$bang$eq$(this, b, op);
   }

   public final Object unary_$minus(final UFunc.UImpl op) {
      return ImmutableNumericOps.unary_$minus$(this, op);
   }

   public final Object $minus$colon$minus(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$minus$colon$minus$(this, b, op);
   }

   public final Object $minus(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$minus$(this, b, op);
   }

   public final Object $percent$colon$percent(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$percent$colon$percent$(this, b, op);
   }

   public final Object $percent(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$percent$(this, b, op);
   }

   public final Object $div$colon$div(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$div$colon$div$(this, b, op);
   }

   public final Object $div(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$div$(this, b, op);
   }

   public final Object $up$colon$up(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$up$colon$up$(this, b, op);
   }

   public final Object dot(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.dot$(this, b, op);
   }

   public final Object unary_$bang(final UFunc.UImpl op) {
      return ImmutableNumericOps.unary_$bang$(this, op);
   }

   public final Object $amp$colon$amp(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$amp$colon$amp$(this, b, op);
   }

   public final Object $bar$colon$bar(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$bar$colon$bar$(this, b, op);
   }

   public final Object $up$up$colon$up$up(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$up$up$colon$up$up$(this, b, op);
   }

   public final Object $amp(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$amp$(this, b, op);
   }

   public final Object $bar(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$bar$(this, b, op);
   }

   public final Object $up$up(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$up$up$(this, b, op);
   }

   public final Object $times(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$times$(this, b, op);
   }

   public final Object t(final CanTranspose op) {
      return ImmutableNumericOps.t$(this, op);
   }

   public Object $bslash(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$bslash$(this, b, op);
   }

   public final Object t(final Object a, final Object b, final CanTranspose op, final CanSlice2 canSlice) {
      return ImmutableNumericOps.t$(this, a, b, op, canSlice);
   }

   public final Object t(final Object a, final CanTranspose op, final CanSlice canSlice) {
      return ImmutableNumericOps.t$(this, a, op, canSlice);
   }

   public IndexedSeq findAll(final Function1 f) {
      return QuasiTensor.findAll$(this, f);
   }

   public IndexedSeq findAll$mcD$sp(final Function1 f) {
      return QuasiTensor.findAll$mcD$sp$(this, f);
   }

   public IndexedSeq findAll$mcF$sp(final Function1 f) {
      return QuasiTensor.findAll$mcF$sp$(this, f);
   }

   public IndexedSeq findAll$mcI$sp(final Function1 f) {
      return QuasiTensor.findAll$mcI$sp$(this, f);
   }

   public IndexedSeq findAll$mcJ$sp(final Function1 f) {
      return QuasiTensor.findAll$mcJ$sp$(this, f);
   }

   public Object data() {
      return this.data;
   }

   public int offset() {
      return this.offset;
   }

   public int stride() {
      return this.stride;
   }

   public int length() {
      return this.length;
   }

   public DenseVector repr() {
      return this;
   }

   public int activeSize() {
      return this.length();
   }

   public Object apply(final int i) {
      return this.apply$mcI$sp(i);
   }

   public void update(final int i, final Object v) {
      if (i >= -this.size() && i < this.size()) {
         int trueI = i < 0 ? i + this.size() : i;
         if (this.noOffsetOrStride()) {
            .MODULE$.array_update(this.data(), trueI, v);
         } else {
            .MODULE$.array_update(this.data(), this.offset() + trueI * this.stride(), v);
         }

      } else {
         throw new IndexOutOfBoundsException((new StringBuilder(12)).append(i).append(" not in [-").append(this.size()).append(",").append(this.size()).append(")").toString());
      }
   }

   public boolean noOffsetOrStride() {
      return this.noOffsetOrStride;
   }

   private void checkIfSpecialized() {
      if (this.data() instanceof double[]) {
         String var10000 = this.getClass().getName();
         String var1 = "breeze.linalg.DenseVector";
         if (var10000 == null) {
            if (var1 == null) {
               throw new Exception("...");
            }
         } else if (var10000.equals(var1)) {
            throw new Exception("...");
         }
      }

   }

   public Iterator activeIterator() {
      return this.iterator();
   }

   public Iterator activeValuesIterator() {
      return this.valuesIterator();
   }

   public Iterator activeKeysIterator() {
      return this.keysIterator();
   }

   public boolean equals(final Object p1) {
      boolean var2;
      if (p1 instanceof DenseVector) {
         DenseVector var4 = (DenseVector)p1;
         var2 = var4.length() == this.length() && ArrayUtil$.MODULE$.nonstupidEquals(this.data(), this.offset(), this.stride(), this.length(), var4.data(), var4.offset(), var4.stride(), var4.length());
      } else {
         var2 = Vector.equals$(this, p1);
      }

      return var2;
   }

   public int hashCode() {
      return ArrayUtil$.MODULE$.zeroSkippingHashCode(this.data(), this.offset(), this.stride(), this.length());
   }

   public String toString() {
      return this.valuesIterator().mkString("DenseVector(", ", ", ")");
   }

   public DenseVector copy() {
      DenseVector var10000;
      if (this.stride() == 1) {
         Object newData = ArrayUtil$.MODULE$.copyOfRange(this.data(), this.offset(), this.offset() + this.length());
         var10000 = new DenseVector(newData);
      } else {
         ClassTag man = ReflectionUtil$.MODULE$.elemClassTagFromArray(this.data());
         DenseVector r = new DenseVector(man.newArray(this.length()));
         r.$colon$eq(this, HasOps$.MODULE$.impl_OpSet_InPlace_DV_DV());
         var10000 = r;
      }

      return var10000;
   }

   public Object valueAt(final int i) {
      return this.apply(i);
   }

   public int indexAt(final int i) {
      return i;
   }

   public boolean isActive(final int i) {
      return true;
   }

   public boolean allVisitableIndicesActive() {
      return true;
   }

   public void foreach(final Function1 fn) {
      if (this.stride() == 1) {
         int index$macro$2 = this.offset();

         for(int limit$macro$4 = this.offset() + this.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
            fn.apply(.MODULE$.array_apply(this.data(), index$macro$2));
         }
      } else {
         int i = this.offset();
         int index$macro$7 = 0;

         for(int limit$macro$9 = this.length(); index$macro$7 < limit$macro$9; ++index$macro$7) {
            fn.apply(.MODULE$.array_apply(this.data(), i));
            i += this.stride();
         }
      }

   }

   public DenseVector slice(final int start, final int end, final int stride) {
      if (start <= end && start >= 0) {
         if (end <= this.length() && end >= 0) {
            int len = (end - start + stride - 1) / stride;
            return new DenseVector(this.data(), start * this.stride() + this.offset(), stride * this.stride(), len);
         } else {
            throw new IllegalArgumentException((new StringBuilder(56)).append("End ").append(end).append("is out of bounds for slice of DenseVector of length ").append(this.length()).toString());
         }
      } else {
         throw new IllegalArgumentException((new StringBuilder(27)).append("Slice arguments ").append(start).append(", ").append(end).append(" invalid.").toString());
      }
   }

   public int slice$default$3() {
      return 1;
   }

   public DenseMatrix toDenseMatrix() {
      return this.copy().asDenseMatrix();
   }

   public DenseMatrix asDenseMatrix() {
      return new DenseMatrix(1, this.length(), this.data(), this.offset(), this.stride(), DenseMatrix$.MODULE$.$lessinit$greater$default$6());
   }

   public Object toArray(final ClassTag ct) {
      Object var10000;
      if (this.stride() == 1) {
         var10000 = ArrayUtil$.MODULE$.copyOfRange(this.data(), this.offset(), this.offset() + this.length());
      } else {
         Object arr = ct.newArray(this.length());
         int i = 0;

         for(int off = this.offset(); i < this.length(); ++i) {
            .MODULE$.array_update(arr, i, .MODULE$.array_apply(this.data(), off));
            off += this.stride();
         }

         var10000 = arr;
      }

      return var10000;
   }

   public scala.collection.immutable.Vector toScalaVector() {
      ClassTag ct = ReflectionUtil$.MODULE$.elemClassTagFromArray(this.data());
      return scala.Predef..MODULE$.genericWrapArray(this.toArray(ct)).toVector();
   }

   public Object writeReplace() throws ObjectStreamException {
      return new SerializedForm(this.data(), this.offset(), this.stride(), this.length());
   }

   public boolean overlaps(final DenseVector other) {
      return this.data() == other.data() && RangeUtils$.MODULE$.overlaps(this.breeze$linalg$DenseVector$$footprint(), other.breeze$linalg$DenseVector$$footprint());
   }

   public Range breeze$linalg$DenseVector$$footprint() {
      Object var10000;
      if (this.length() == 0) {
         var10000 = scala.package..MODULE$.Range().apply(this.offset(), this.offset());
      } else {
         Range.Inclusive r = scala.runtime.RichInt..MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(this.offset()), this.offset() + this.stride() * (this.length() - 1), this.stride());
         var10000 = this.stride() < 0 ? r.reverse() : r;
      }

      return (Range)var10000;
   }

   public double[] data$mcD$sp() {
      return (double[])this.data();
   }

   public float[] data$mcF$sp() {
      return (float[])this.data();
   }

   public int[] data$mcI$sp() {
      return (int[])this.data();
   }

   public long[] data$mcJ$sp() {
      return (long[])this.data();
   }

   public DenseVector repr$mcD$sp() {
      return this.repr();
   }

   public DenseVector repr$mcF$sp() {
      return this.repr();
   }

   public DenseVector repr$mcI$sp() {
      return this.repr();
   }

   public DenseVector repr$mcJ$sp() {
      return this.repr();
   }

   public double apply$mcD$sp(final int i) {
      return BoxesRunTime.unboxToDouble(this.apply(i));
   }

   public float apply$mcF$sp(final int i) {
      return BoxesRunTime.unboxToFloat(this.apply(i));
   }

   public int apply$mcI$sp(final int i) {
      return BoxesRunTime.unboxToInt(this.apply(i));
   }

   public long apply$mcJ$sp(final int i) {
      return BoxesRunTime.unboxToLong(this.apply(i));
   }

   public void update$mcD$sp(final int i, final double v) {
      this.update(i, BoxesRunTime.boxToDouble(v));
   }

   public void update$mcF$sp(final int i, final float v) {
      this.update(i, BoxesRunTime.boxToFloat(v));
   }

   public void update$mcI$sp(final int i, final int v) {
      this.update(i, BoxesRunTime.boxToInteger(v));
   }

   public void update$mcJ$sp(final int i, final long v) {
      this.update(i, BoxesRunTime.boxToLong(v));
   }

   public DenseVector copy$mcD$sp() {
      return this.copy();
   }

   public DenseVector copy$mcF$sp() {
      return this.copy();
   }

   public DenseVector copy$mcI$sp() {
      return this.copy();
   }

   public DenseVector copy$mcJ$sp() {
      return this.copy();
   }

   public double valueAt$mcD$sp(final int i) {
      return BoxesRunTime.unboxToDouble(this.valueAt(i));
   }

   public float valueAt$mcF$sp(final int i) {
      return BoxesRunTime.unboxToFloat(this.valueAt(i));
   }

   public int valueAt$mcI$sp(final int i) {
      return BoxesRunTime.unboxToInt(this.valueAt(i));
   }

   public long valueAt$mcJ$sp(final int i) {
      return BoxesRunTime.unboxToLong(this.valueAt(i));
   }

   public void foreach$mcD$sp(final Function1 fn) {
      this.foreach(fn);
   }

   public void foreach$mcF$sp(final Function1 fn) {
      this.foreach(fn);
   }

   public void foreach$mcI$sp(final Function1 fn) {
      this.foreach(fn);
   }

   public void foreach$mcJ$sp(final Function1 fn) {
      this.foreach(fn);
   }

   public void foreach$mVc$sp(final Function1 fn) {
      if (this.stride() == 1) {
         int index$macro$2 = this.offset();

         for(int limit$macro$4 = this.offset() + this.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
            fn.apply(.MODULE$.array_apply(this.data(), index$macro$2));
         }
      } else {
         int i = this.offset();
         int index$macro$7 = 0;

         for(int limit$macro$9 = this.length(); index$macro$7 < limit$macro$9; ++index$macro$7) {
            fn.apply(.MODULE$.array_apply(this.data(), i));
            i += this.stride();
         }
      }

   }

   public void foreach$mVcD$sp(final Function1 fn) {
      this.foreach$mVc$sp(fn);
   }

   public void foreach$mVcF$sp(final Function1 fn) {
      this.foreach$mVc$sp(fn);
   }

   public void foreach$mVcI$sp(final Function1 fn) {
      this.foreach$mVc$sp(fn);
   }

   public void foreach$mVcJ$sp(final Function1 fn) {
      this.foreach$mVc$sp(fn);
   }

   public DenseVector slice$mcD$sp(final int start, final int end, final int stride) {
      return this.slice(start, end, stride);
   }

   public DenseVector slice$mcF$sp(final int start, final int end, final int stride) {
      return this.slice(start, end, stride);
   }

   public DenseVector slice$mcI$sp(final int start, final int end, final int stride) {
      return this.slice(start, end, stride);
   }

   public DenseVector slice$mcJ$sp(final int start, final int end, final int stride) {
      return this.slice(start, end, stride);
   }

   public DenseMatrix toDenseMatrix$mcD$sp() {
      return this.toDenseMatrix();
   }

   public DenseMatrix toDenseMatrix$mcF$sp() {
      return this.toDenseMatrix();
   }

   public DenseMatrix toDenseMatrix$mcI$sp() {
      return this.toDenseMatrix();
   }

   public DenseMatrix toDenseMatrix$mcJ$sp() {
      return this.toDenseMatrix();
   }

   public DenseMatrix asDenseMatrix$mcD$sp() {
      return this.asDenseMatrix();
   }

   public DenseMatrix asDenseMatrix$mcF$sp() {
      return this.asDenseMatrix();
   }

   public DenseMatrix asDenseMatrix$mcI$sp() {
      return this.asDenseMatrix();
   }

   public DenseMatrix asDenseMatrix$mcJ$sp() {
      return this.asDenseMatrix();
   }

   public double[] toArray$mcD$sp(final ClassTag ct) {
      return (double[])this.toArray(ct);
   }

   public float[] toArray$mcF$sp(final ClassTag ct) {
      return (float[])this.toArray(ct);
   }

   public int[] toArray$mcI$sp(final ClassTag ct) {
      return (int[])this.toArray(ct);
   }

   public long[] toArray$mcJ$sp(final ClassTag ct) {
      return (long[])this.toArray(ct);
   }

   public boolean overlaps$mcD$sp(final DenseVector other) {
      return this.overlaps(other);
   }

   public boolean overlaps$mcF$sp(final DenseVector other) {
      return this.overlaps(other);
   }

   public boolean overlaps$mcI$sp(final DenseVector other) {
      return this.overlaps(other);
   }

   public boolean overlaps$mcJ$sp(final DenseVector other) {
      return this.overlaps(other);
   }

   public Object apply$mcI$sp(final int i) {
      if (i >= -this.size() && i < this.size()) {
         int trueI = i < 0 ? i + this.size() : i;
         return this.noOffsetOrStride() ? .MODULE$.array_apply(this.data(), trueI) : .MODULE$.array_apply(this.data(), this.offset() + trueI * this.stride());
      } else {
         throw new IndexOutOfBoundsException((new StringBuilder(12)).append(i).append(" not in [-").append(this.size()).append(",").append(this.size()).append(")").toString());
      }
   }

   public boolean specInstance$() {
      return false;
   }

   public DenseVector(final Object data, final int offset, final int stride, final int length) {
      this.data = data;
      this.offset = offset;
      this.stride = stride;
      this.length = length;
      QuasiTensor.$init$(this);
      ImmutableNumericOps.$init$(this);
      NumericOps.$init$(this);
      TensorLike.$init$(this);
      VectorLike.$init$(this);
      Vector.$init$(this);
      Storage.$init$(this);
      DenseVector$.MODULE$.breeze$linalg$DenseVector$$init();
      this.noOffsetOrStride = offset == 0 && stride == 1;
      Statics.releaseFence();
   }

   public DenseVector(final Object data) {
      this(data, 0, 1, .MODULE$.array_length(data));
   }

   public DenseVector(final Object data, final int offset) {
      this(data, offset, 1, .MODULE$.array_length(data));
   }

   public DenseVector(final int length, final ClassTag man) {
      this(man.newArray(length), 0, 1, length);
   }

   public static class CanZipMapValuesDenseVector implements CanZipMapValues {
      public final ClassTag breeze$linalg$DenseVector$CanZipMapValuesDenseVector$$evidence$15;

      public Object map$mcDD$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcDD$sp$(this, from, from2, fn);
      }

      public Object map$mcFD$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcFD$sp$(this, from, from2, fn);
      }

      public Object map$mcID$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcID$sp$(this, from, from2, fn);
      }

      public Object map$mcJD$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcJD$sp$(this, from, from2, fn);
      }

      public Object map$mcDF$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcDF$sp$(this, from, from2, fn);
      }

      public Object map$mcFF$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcFF$sp$(this, from, from2, fn);
      }

      public Object map$mcIF$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcIF$sp$(this, from, from2, fn);
      }

      public Object map$mcJF$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcJF$sp$(this, from, from2, fn);
      }

      public Object map$mcDI$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcDI$sp$(this, from, from2, fn);
      }

      public Object map$mcFI$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcFI$sp$(this, from, from2, fn);
      }

      public Object map$mcII$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcII$sp$(this, from, from2, fn);
      }

      public Object map$mcJI$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcJI$sp$(this, from, from2, fn);
      }

      public Object map$mcDJ$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcDJ$sp$(this, from, from2, fn);
      }

      public Object map$mcFJ$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcFJ$sp$(this, from, from2, fn);
      }

      public Object map$mcIJ$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcIJ$sp$(this, from, from2, fn);
      }

      public Object map$mcJJ$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcJJ$sp$(this, from, from2, fn);
      }

      public DenseVector create(final int length) {
         return DenseVector$.MODULE$.apply(this.breeze$linalg$DenseVector$CanZipMapValuesDenseVector$$evidence$15.newArray(length));
      }

      public DenseVector map(final DenseVector from, final DenseVector from2, final Function2 fn) {
         int left$macro$1 = from.length();
         int right$macro$2 = from2.length();
         if (left$macro$1 != right$macro$2) {
            throw new IllegalArgumentException((new StringBuilder(56)).append("requirement failed: ").append("Vectors must have same length").append(": ").append("from.length == from2.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
         } else {
            DenseVector result = this.create(from.length());

            for(int i = 0; i < from.length(); ++i) {
               .MODULE$.array_update(result.data(), i, fn.apply(from.apply(i), from2.apply(i)));
            }

            return result;
         }
      }

      public DenseVector create$mcD$sp(final int length) {
         return this.create(length);
      }

      public DenseVector create$mcI$sp(final int length) {
         return this.create(length);
      }

      public DenseVector map$mcDD$sp(final DenseVector from, final DenseVector from2, final Function2 fn) {
         return this.map(from, from2, fn);
      }

      public DenseVector map$mcID$sp(final DenseVector from, final DenseVector from2, final Function2 fn) {
         return this.map(from, from2, fn);
      }

      public DenseVector map$mcDF$sp(final DenseVector from, final DenseVector from2, final Function2 fn) {
         return this.map(from, from2, fn);
      }

      public DenseVector map$mcIF$sp(final DenseVector from, final DenseVector from2, final Function2 fn) {
         return this.map(from, from2, fn);
      }

      public DenseVector map$mcDI$sp(final DenseVector from, final DenseVector from2, final Function2 fn) {
         return this.map(from, from2, fn);
      }

      public DenseVector map$mcII$sp(final DenseVector from, final DenseVector from2, final Function2 fn) {
         return this.map(from, from2, fn);
      }

      public DenseVector map$mcDJ$sp(final DenseVector from, final DenseVector from2, final Function2 fn) {
         return this.map(from, from2, fn);
      }

      public DenseVector map$mcIJ$sp(final DenseVector from, final DenseVector from2, final Function2 fn) {
         return this.map(from, from2, fn);
      }

      public CanZipMapValuesDenseVector(final ClassTag evidence$15) {
         this.breeze$linalg$DenseVector$CanZipMapValuesDenseVector$$evidence$15 = evidence$15;
      }
   }

   public static class CanZipMapKeyValuesDenseVector implements CanZipMapKeyValues {
      public final ClassTag breeze$linalg$DenseVector$CanZipMapKeyValuesDenseVector$$evidence$17;

      public DenseVector create(final int length) {
         return DenseVector$.MODULE$.apply(this.breeze$linalg$DenseVector$CanZipMapKeyValuesDenseVector$$evidence$17.newArray(length));
      }

      public DenseVector map(final DenseVector from, final DenseVector from2, final Function3 fn) {
         int left$macro$1 = from.length();
         int right$macro$2 = from2.length();
         if (left$macro$1 != right$macro$2) {
            throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vector lengths must match!: ").append("from.length == from2.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
         } else {
            DenseVector result = this.create(from.length());

            for(int i = 0; i < from.length(); ++i) {
               .MODULE$.array_update(result.data(), i, fn.apply(BoxesRunTime.boxToInteger(i), from.apply(i), from2.apply(i)));
            }

            return result;
         }
      }

      public DenseVector mapActive(final DenseVector from, final DenseVector from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      public DenseVector create$mcD$sp(final int length) {
         return this.create(length);
      }

      public DenseVector create$mcI$sp(final int length) {
         return this.create(length);
      }

      public DenseVector map$mcDD$sp(final DenseVector from, final DenseVector from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      public DenseVector map$mcID$sp(final DenseVector from, final DenseVector from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      public DenseVector map$mcDF$sp(final DenseVector from, final DenseVector from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      public DenseVector map$mcIF$sp(final DenseVector from, final DenseVector from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      public DenseVector map$mcDI$sp(final DenseVector from, final DenseVector from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      public DenseVector map$mcII$sp(final DenseVector from, final DenseVector from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      public DenseVector map$mcDJ$sp(final DenseVector from, final DenseVector from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      public DenseVector map$mcIJ$sp(final DenseVector from, final DenseVector from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      public DenseVector mapActive$mcDD$sp(final DenseVector from, final DenseVector from2, final Function3 fn) {
         return this.mapActive(from, from2, fn);
      }

      public DenseVector mapActive$mcID$sp(final DenseVector from, final DenseVector from2, final Function3 fn) {
         return this.mapActive(from, from2, fn);
      }

      public DenseVector mapActive$mcDF$sp(final DenseVector from, final DenseVector from2, final Function3 fn) {
         return this.mapActive(from, from2, fn);
      }

      public DenseVector mapActive$mcIF$sp(final DenseVector from, final DenseVector from2, final Function3 fn) {
         return this.mapActive(from, from2, fn);
      }

      public DenseVector mapActive$mcDI$sp(final DenseVector from, final DenseVector from2, final Function3 fn) {
         return this.mapActive(from, from2, fn);
      }

      public DenseVector mapActive$mcII$sp(final DenseVector from, final DenseVector from2, final Function3 fn) {
         return this.mapActive(from, from2, fn);
      }

      public DenseVector mapActive$mcDJ$sp(final DenseVector from, final DenseVector from2, final Function3 fn) {
         return this.mapActive(from, from2, fn);
      }

      public DenseVector mapActive$mcIJ$sp(final DenseVector from, final DenseVector from2, final Function3 fn) {
         return this.mapActive(from, from2, fn);
      }

      public CanZipMapKeyValuesDenseVector(final ClassTag evidence$17) {
         this.breeze$linalg$DenseVector$CanZipMapKeyValuesDenseVector$$evidence$17 = evidence$17;
      }
   }

   public static class TupleIsomorphisms$ {
      public static final TupleIsomorphisms$ MODULE$ = new TupleIsomorphisms$();
   }

   public static class SerializedForm implements Serializable, Product {
      private static final long serialVersionUID = 1L;
      private final Object data;
      private final int offset;
      private final int stride;
      private final int length;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Object data() {
         return this.data;
      }

      public int offset() {
         return this.offset;
      }

      public int stride() {
         return this.stride;
      }

      public int length() {
         return this.length;
      }

      public Object readResolve() throws ObjectStreamException {
         Object var2 = this.data();
         Object var1;
         if (var2 instanceof int[]) {
            int[] var3 = (int[])var2;
            var1 = new DenseVector$mcI$sp(var3, this.offset(), this.stride(), this.length());
         } else if (var2 instanceof long[]) {
            long[] var4 = (long[])var2;
            var1 = new DenseVector$mcJ$sp(var4, this.offset(), this.stride(), this.length());
         } else if (var2 instanceof double[]) {
            double[] var5 = (double[])var2;
            var1 = new DenseVector$mcD$sp(var5, this.offset(), this.stride(), this.length());
         } else if (var2 instanceof float[]) {
            float[] var6 = (float[])var2;
            var1 = new DenseVector$mcF$sp(var6, this.offset(), this.stride(), this.length());
         } else if (var2 instanceof short[]) {
            short[] var7 = (short[])var2;
            var1 = new DenseVector(var7, this.offset(), this.stride(), this.length());
         } else if (var2 instanceof byte[]) {
            byte[] var8 = (byte[])var2;
            var1 = new DenseVector(var8, this.offset(), this.stride(), this.length());
         } else if (var2 instanceof char[]) {
            char[] var9 = (char[])var2;
            var1 = new DenseVector(var9, this.offset(), this.stride(), this.length());
         } else {
            if (var2 == null) {
               throw new MatchError(var2);
            }

            var1 = new DenseVector(var2, this.offset(), this.stride(), this.length());
         }

         return var1;
      }

      public SerializedForm copy(final Object data, final int offset, final int stride, final int length) {
         return new SerializedForm(data, offset, stride, length);
      }

      public Object copy$default$1() {
         return this.data();
      }

      public int copy$default$2() {
         return this.offset();
      }

      public int copy$default$3() {
         return this.stride();
      }

      public int copy$default$4() {
         return this.length();
      }

      public String productPrefix() {
         return "SerializedForm";
      }

      public int productArity() {
         return 4;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.data();
               break;
            case 1:
               var10000 = BoxesRunTime.boxToInteger(this.offset());
               break;
            case 2:
               var10000 = BoxesRunTime.boxToInteger(this.stride());
               break;
            case 3:
               var10000 = BoxesRunTime.boxToInteger(this.length());
               break;
            default:
               var10000 = Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof SerializedForm;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "data";
               break;
            case 1:
               var10000 = "offset";
               break;
            case 2:
               var10000 = "stride";
               break;
            case 3:
               var10000 = "length";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.anyHash(this.data()));
         var1 = Statics.mix(var1, this.offset());
         var1 = Statics.mix(var1, this.stride());
         var1 = Statics.mix(var1, this.length());
         return Statics.finalizeHash(var1, 4);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label55: {
               boolean var2;
               if (x$1 instanceof SerializedForm) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  SerializedForm var4 = (SerializedForm)x$1;
                  if (this.offset() == var4.offset() && this.stride() == var4.stride() && this.length() == var4.length() && BoxesRunTime.equals(this.data(), var4.data()) && var4.canEqual(this)) {
                     break label55;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      public SerializedForm(final Object data, final int offset, final int stride, final int length) {
         this.data = data;
         this.offset = offset;
         this.stride = stride;
         this.length = length;
         Product.$init$(this);
      }
   }

   public static class SerializedForm$ extends AbstractFunction4 implements Serializable {
      public static final SerializedForm$ MODULE$ = new SerializedForm$();

      public final String toString() {
         return "SerializedForm";
      }

      public SerializedForm apply(final Object data, final int offset, final int stride, final int length) {
         return new SerializedForm(data, offset, stride, length);
      }

      public Option unapply(final SerializedForm x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple4(x$0.data(), BoxesRunTime.boxToInteger(x$0.offset()), BoxesRunTime.boxToInteger(x$0.stride()), BoxesRunTime.boxToInteger(x$0.length()))));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(SerializedForm$.class);
      }
   }
}
