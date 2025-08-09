package org.apache.spark.sql.hive;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import org.apache.hadoop.hive.ql.exec.UDAF;
import org.apache.hadoop.hive.ql.udf.generic.AbstractGenericUDAFResolver;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDAFEvaluator;
import org.apache.hadoop.hive.ql.udf.generic.SimpleGenericUDAFParameterInfo;
import org.apache.hadoop.hive.ql.udf.generic.SparkGenericUDAFBridge;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDAFEvaluator.Mode;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.spark.sql.catalyst.InternalRow;
import org.apache.spark.sql.catalyst.expressions.Expression;
import org.apache.spark.sql.catalyst.expressions.GenericInternalRow;
import org.apache.spark.sql.catalyst.expressions.UnsafeProjection;
import org.apache.spark.sql.catalyst.expressions.UnsafeRow;
import org.apache.spark.sql.catalyst.expressions.UserDefinedExpression;
import org.apache.spark.sql.catalyst.expressions.aggregate.ImperativeAggregate;
import org.apache.spark.sql.catalyst.expressions.aggregate.TypedImperativeAggregate;
import org.apache.spark.sql.types.DataType;
import scala.Function1;
import scala.Function3;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011ub!B3g\u0001\u001a\u0004\bBCA\u001a\u0001\tU\r\u0011\"\u0001\u00026!Q\u0011q\t\u0001\u0003\u0012\u0003\u0006I!a\u000e\t\u0015\u0005%\u0003A!f\u0001\n\u0003\tY\u0005\u0003\u0006\u0002p\u0001\u0011\t\u0012)A\u0005\u0003\u001bB!\"!\u001d\u0001\u0005+\u0007I\u0011AA:\u0011)\t\t\t\u0001B\tB\u0003%\u0011Q\u000f\u0005\u000b\u0003\u0007\u0003!Q3A\u0005\u0002\u0005\u0015\u0005BCAG\u0001\tE\t\u0015!\u0003\u0002\b\"Q\u0011q\u0012\u0001\u0003\u0016\u0004%\t!!%\t\u0015\u0005e\u0005A!E!\u0002\u0013\t\u0019\n\u0003\u0006\u0002\u001c\u0002\u0011)\u001a!C\u0001\u0003#C!\"!(\u0001\u0005#\u0005\u000b\u0011BAJ\u0011\u001d\ty\n\u0001C\u0001\u0003CCq!!-\u0001\t\u0003\n\u0019\fC\u0004\u0002@\u0002!\t%!1\t\u0015\u0005\u001d\u0007\u0001#b\u0001\n\u0013\tI\r\u0003\u0006\u0002p\u0002A)\u0019!C\u0005\u0003cDqAa\u0001\u0001\t\u0013\u0011)A\u0002\u0004\u0003\u001c\u0001!%Q\u0004\u0005\u000b\u0005K\u0019\"Q3A\u0005\u0002\t\u001d\u0002B\u0003B\u0015'\tE\t\u0015!\u0003\u0003\b!Q!1F\n\u0003\u0016\u0004%\tA!\f\t\u0015\t=2C!E!\u0002\u0013\t\t\u000eC\u0004\u0002 N!\tA!\r\t\u0013\tm2#!A\u0005\u0002\tu\u0002\"\u0003B\"'E\u0005I\u0011\u0001B#\u0011%\u0011YfEI\u0001\n\u0003\u0011i\u0006C\u0005\u0003bM\t\t\u0011\"\u0011\u0003d!I!1O\n\u0002\u0002\u0013\u0005\u0011\u0011\u0013\u0005\n\u0005k\u001a\u0012\u0011!C\u0001\u0005oB\u0011Ba!\u0014\u0003\u0003%\tE!\"\t\u0013\tM5#!A\u0005\u0002\tU\u0005\"\u0003BM'\u0005\u0005I\u0011\tBN\u0011%\u0011yjEA\u0001\n\u0003\u0012\t\u000bC\u0005\u0003$N\t\t\u0011\"\u0011\u0003&\"I!qU\n\u0002\u0002\u0013\u0005#\u0011V\u0004\n\u0005[\u0003\u0011\u0011!E\u0005\u0005_3\u0011Ba\u0007\u0001\u0003\u0003EIA!-\t\u000f\u0005}e\u0005\"\u0001\u0003J\"I!1\u0015\u0014\u0002\u0002\u0013\u0015#Q\u0015\u0005\n\u0005\u00174\u0013\u0011!CA\u0005\u001bD\u0011Ba5'\u0003\u0003%\tI!6\t\u0015\t\u001d\b\u0001#b\u0001\n\u0013\u0011I\u000f\u0003\u0006\u0003n\u0002A)\u0019!C\u0005\u0005SD!B!=\u0001\u0011\u000b\u0007I\u0011\u0002Bz\u0011)\u00119\u0010\u0001EC\u0002\u0013%!\u0011 \u0005\u000b\u0007\u000b\u0001\u0001R1A\u0005\n\r\u001d\u0001BCB\u0006\u0001!\u0015\r\u0011\"\u0003\u0004\u000e!Q11\u0003\u0001\t\u0006\u0004%Ia!\u0006\t\u000f\rm\u0004\u0001\"\u0011\u0002\u0006\"Q1Q\u0010\u0001\t\u0006\u0004%\tEa=\t\u000f\r}\u0004\u0001\"\u0011\u00026!1\u0011\u000e\u0001C!\u0007\u0003Cqaa\"\u0001\t\u0003\u001aI\t\u0003\u0006\u0004\f\u0002A)\u0019!C\u0005\u0007SAqaa$\u0001\t\u0003\u001a\t\nC\u0004\u0004\"\u0002!\tea)\t\u000f\r%\u0006\u0001\"\u0011\u0004,\"91q\b\u0001\u0005B\r=\u0006bBB9\u0001\u0011\u000531\u0017\u0004\u0007\u00073\u0001Aaa\u0007\t\u000f\u0005}U\b\"\u0001\u0004\u001e!I1qD\u001fC\u0002\u0013%1q\u0001\u0005\t\u0007Ci\u0004\u0015!\u0003\u0003~\"I11E\u001fC\u0002\u0013%1q\u0001\u0005\t\u0007Ki\u0004\u0015!\u0003\u0003~\"I1qE\u001fC\u0002\u0013%1\u0011\u0006\u0005\t\u0007ci\u0004\u0015!\u0003\u0004,!I11G\u001fC\u0002\u0013%1Q\u0007\u0005\t\u0007{i\u0004\u0015!\u0003\u00048!91qH\u001f\u0005\u0002\r\u0005\u0003bBB9{\u0011\u000511\u000f\u0005\b\u0007o\u0003A\u0011KB]\u0011%\u0011Y\u0004AA\u0001\n\u0003\u0019)\rC\u0005\u0003D\u0001\t\n\u0011\"\u0001\u0004T\"I!1\f\u0001\u0012\u0002\u0013\u00051q\u001b\u0005\n\u00077\u0004\u0011\u0013!C\u0001\u0007;D\u0011b!9\u0001#\u0003%\taa9\t\u0013\r\u001d\b!%A\u0005\u0002\r%\b\"CBw\u0001E\u0005I\u0011ABu\u0011%\u0011\t\u0007AA\u0001\n\u0003\u0012\u0019\u0007C\u0005\u0003t\u0001\t\t\u0011\"\u0001\u0002\u0012\"I!Q\u000f\u0001\u0002\u0002\u0013\u00051q\u001e\u0005\n\u0005\u0007\u0003\u0011\u0011!C!\u0005\u000bC\u0011Ba%\u0001\u0003\u0003%\taa=\t\u0013\te\u0005!!A\u0005B\r]\b\"\u0003BT\u0001\u0005\u0005I\u0011IB~\u000f)\u0019yPZA\u0001\u0012\u00031G\u0011\u0001\u0004\nK\u001a\f\t\u0011#\u0001g\t\u0007Aq!a(Z\t\u0003!Y\u0001C\u0005\u0003$f\u000b\t\u0011\"\u0012\u0003&\"I!1Z-\u0002\u0002\u0013\u0005EQ\u0002\u0005\n\t7I\u0016\u0013!C\u0001\u0007GD\u0011\u0002\"\bZ#\u0003%\ta!;\t\u0013\u0011}\u0011,%A\u0005\u0002\r%\b\"\u0003Bj3\u0006\u0005I\u0011\u0011C\u0011\u0011%!i#WI\u0001\n\u0003\u0019\u0019\u000fC\u0005\u00050e\u000b\n\u0011\"\u0001\u0004j\"IA\u0011G-\u0012\u0002\u0013\u00051\u0011\u001e\u0005\n\tgI\u0016\u0011!C\u0005\tk\u0011\u0001\u0003S5wKV#\u0015I\u0012$v]\u000e$\u0018n\u001c8\u000b\u0005\u001dD\u0017\u0001\u00025jm\u0016T!!\u001b6\u0002\u0007M\fHN\u0003\u0002lY\u0006)1\u000f]1sW*\u0011QN\\\u0001\u0007CB\f7\r[3\u000b\u0003=\f1a\u001c:h'%\u0001\u0011o`A\u0003\u0003\u001b\tI\u0002E\u0002ssnl\u0011a\u001d\u0006\u0003iV\f\u0011\"Y4he\u0016<\u0017\r^3\u000b\u0005Y<\u0018aC3yaJ,7o]5p]NT!\u0001\u001f5\u0002\u0011\r\fG/\u00197zgRL!A_:\u00031QK\b/\u001a3J[B,'/\u0019;jm\u0016\fum\u001a:fO\u0006$X\r\u0005\u0002}{6\ta-\u0003\u0002\u007fM\nq\u0001*\u001b<f+\u0012\u000beIQ;gM\u0016\u0014\bc\u0001?\u0002\u0002%\u0019\u00111\u00014\u0003\u001d!Kg/Z%ogB,7\r^8sgB!\u0011qAA\u0005\u001b\u0005)\u0018bAA\u0006k\n)Rk]3s\t\u00164\u0017N\\3e\u000bb\u0004(/Z:tS>t\u0007\u0003BA\b\u0003+i!!!\u0005\u000b\u0005\u0005M\u0011!B:dC2\f\u0017\u0002BA\f\u0003#\u0011q\u0001\u0015:pIV\u001cG\u000f\u0005\u0003\u0002\u001c\u00055b\u0002BA\u000f\u0003SqA!a\b\u0002(5\u0011\u0011\u0011\u0005\u0006\u0005\u0003G\t)#\u0001\u0004=e>|GOP\u0002\u0001\u0013\t\t\u0019\"\u0003\u0003\u0002,\u0005E\u0011a\u00029bG.\fw-Z\u0005\u0005\u0003_\t\tD\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0003\u0002,\u0005E\u0011\u0001\u00028b[\u0016,\"!a\u000e\u0011\t\u0005e\u0012\u0011\t\b\u0005\u0003w\ti\u0004\u0005\u0003\u0002 \u0005E\u0011\u0002BA \u0003#\ta\u0001\u0015:fI\u00164\u0017\u0002BA\"\u0003\u000b\u0012aa\u0015;sS:<'\u0002BA \u0003#\tQA\\1nK\u0002\n1BZ;oG^\u0013\u0018\r\u001d9feV\u0011\u0011Q\n\t\u0005\u0003\u001f\nIG\u0004\u0003\u0002R\u0005\u0015d\u0002BA*\u0003GrA!!\u0016\u0002b9!\u0011qKA0\u001d\u0011\tI&!\u0018\u000f\t\u0005}\u00111L\u0005\u0002_&\u0011QN\\\u0005\u0003W2L!!\u001b6\n\u0005\u001dD\u0017bAA4M\u0006A\u0001*\u001b<f'\"LW.\u0003\u0003\u0002l\u00055$a\u0005%jm\u00164UO\\2uS>twK]1qa\u0016\u0014(bAA4M\u0006aa-\u001e8d/J\f\u0007\u000f]3sA\u0005A1\r[5mIJ,g.\u0006\u0002\u0002vA1\u00111DA<\u0003wJA!!\u001f\u00022\t\u00191+Z9\u0011\t\u0005\u001d\u0011QP\u0005\u0004\u0003\u007f*(AC#yaJ,7o]5p]\u0006I1\r[5mIJ,g\u000eI\u0001\u0015SN,F)\u0011$Ce&$w-\u001a*fcVL'/\u001a3\u0016\u0005\u0005\u001d\u0005\u0003BA\b\u0003\u0013KA!a#\u0002\u0012\t9!i\\8mK\u0006t\u0017!F5t+\u0012\u000beI\u0011:jI\u001e,'+Z9vSJ,G\rI\u0001\u0017[V$\u0018M\u00197f\u0003\u001e<')\u001e4gKJ|eMZ:fiV\u0011\u00111\u0013\t\u0005\u0003\u001f\t)*\u0003\u0003\u0002\u0018\u0006E!aA%oi\u00069R.\u001e;bE2,\u0017iZ4Ck\u001a4WM](gMN,G\u000fI\u0001\u0015S:\u0004X\u000f^!hO\n+hMZ3s\u001f\u001a47/\u001a;\u0002+%t\u0007/\u001e;BO\u001e\u0014UO\u001a4fe>3gm]3uA\u00051A(\u001b8jiz\"b\"a)\u0002&\u0006\u001d\u0016\u0011VAV\u0003[\u000by\u000b\u0005\u0002}\u0001!9\u00111G\u0007A\u0002\u0005]\u0002bBA%\u001b\u0001\u0007\u0011Q\n\u0005\b\u0003cj\u0001\u0019AA;\u0011%\t\u0019)\u0004I\u0001\u0002\u0004\t9\tC\u0005\u0002\u00106\u0001\n\u00111\u0001\u0002\u0014\"I\u00111T\u0007\u0011\u0002\u0003\u0007\u00111S\u0001\u001eo&$\bNT3x\u001bV$\u0018M\u00197f\u0003\u001e<')\u001e4gKJ|eMZ:fiR!\u0011QWA^!\r\u0011\u0018qW\u0005\u0004\u0003s\u001b(aE%na\u0016\u0014\u0018\r^5wK\u0006;wM]3hCR,\u0007bBA_\u001d\u0001\u0007\u00111S\u0001\u001a]\u0016<X*\u001e;bE2,\u0017iZ4Ck\u001a4WM](gMN,G/A\u000exSRDg*Z<J]B,H/Q4h\u0005V4g-\u001a:PM\u001a\u001cX\r\u001e\u000b\u0005\u0003k\u000b\u0019\rC\u0004\u0002F>\u0001\r!a%\u0002/9,w/\u00138qkR\fum\u001a\"vM\u001a,'o\u00144gg\u0016$\u0018aD5oaV$\u0018J\\:qK\u000e$xN]:\u0016\u0005\u0005-\u0007CBA\b\u0003\u001b\f\t.\u0003\u0003\u0002P\u0006E!!B!se\u0006L\b\u0003BAj\u0003Gl!!!6\u000b\t\u0005]\u0017\u0011\\\u0001\u0010_\nTWm\u0019;j]N\u0004Xm\u0019;pe*!\u00111\\Ao\u0003\u0019\u0019XM\u001d3fe)\u0019q-a8\u000b\u0007\u0005\u0005H.\u0001\u0004iC\u0012|w\u000e]\u0005\u0005\u0003K\f)NA\bPE*,7\r^%ogB,7\r^8sQ\r\u0001\u0012\u0011\u001e\t\u0005\u0003\u001f\tY/\u0003\u0003\u0002n\u0006E!!\u0003;sC:\u001c\u0018.\u001a8u\u00039Ig\u000e];u\t\u0006$\u0018\rV=qKN,\"!a=\u0011\r\u0005=\u0011QZA{!\u0011\t90!@\u000e\u0005\u0005e(bAA~Q\u0006)A/\u001f9fg&!\u0011q`A}\u0005!!\u0015\r^1UsB,\u0007fA\t\u0002j\u0006aa.Z<Fm\u0006dW/\u0019;peR\u0011!q\u0001\t\u0005\u0005\u0013\u00119\"\u0004\u0002\u0003\f)!!Q\u0002B\b\u0003\u001d9WM\\3sS\u000eTAA!\u0005\u0003\u0014\u0005\u0019Q\u000f\u001a4\u000b\t\tU\u0011Q\\\u0001\u0003c2LAA!\u0007\u0003\f\t!r)\u001a8fe&\u001cW\u000bR!G\u000bZ\fG.^1u_J\u0014Q\u0002S5wK\u00163\u0018\r\\;bi>\u00148cB\n\u0003 \u00055\u0011\u0011\u0004\t\u0005\u0003\u001f\u0011\t#\u0003\u0003\u0003$\u0005E!AB!osJ+g-A\u0005fm\u0006dW/\u0019;peV\u0011!qA\u0001\u000bKZ\fG.^1u_J\u0004\u0013aD8cU\u0016\u001cG/\u00138ta\u0016\u001cGo\u001c:\u0016\u0005\u0005E\u0017\u0001E8cU\u0016\u001cG/\u00138ta\u0016\u001cGo\u001c:!)\u0019\u0011\u0019Da\u000e\u0003:A\u0019!QG\n\u000e\u0003\u0001AqA!\n\u0019\u0001\u0004\u00119\u0001C\u0004\u0003,a\u0001\r!!5\u0002\t\r|\u0007/\u001f\u000b\u0007\u0005g\u0011yD!\u0011\t\u0013\t\u0015\u0012\u0004%AA\u0002\t\u001d\u0001\"\u0003B\u00163A\u0005\t\u0019AAi\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\"Aa\u0012+\t\t\u001d!\u0011J\u0016\u0003\u0005\u0017\u0002BA!\u0014\u0003X5\u0011!q\n\u0006\u0005\u0005#\u0012\u0019&A\u0005v]\u000eDWmY6fI*!!QKA\t\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u00053\u0012yEA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'\u0006\u0002\u0003`)\"\u0011\u0011\u001bB%\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011!Q\r\t\u0005\u0005O\u0012\t(\u0004\u0002\u0003j)!!1\u000eB7\u0003\u0011a\u0017M\\4\u000b\u0005\t=\u0014\u0001\u00026bm\u0006LA!a\u0011\u0003j\u0005a\u0001O]8ek\u000e$\u0018I]5us\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003\u0002B=\u0005\u007f\u0002B!a\u0004\u0003|%!!QPA\t\u0005\r\te.\u001f\u0005\n\u0005\u0003s\u0012\u0011!a\u0001\u0003'\u000b1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XC\u0001BD!\u0019\u0011IIa$\u0003z5\u0011!1\u0012\u0006\u0005\u0005\u001b\u000b\t\"\u0001\u0006d_2dWm\u0019;j_:LAA!%\u0003\f\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\t9Ia&\t\u0013\t\u0005\u0005%!AA\u0002\te\u0014A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$BA!\u001a\u0003\u001e\"I!\u0011Q\u0011\u0002\u0002\u0003\u0007\u00111S\u0001\tQ\u0006\u001c\bnQ8eKR\u0011\u00111S\u0001\ti>\u001cFO]5oOR\u0011!QM\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005\u001d%1\u0016\u0005\n\u0005\u0003#\u0013\u0011!a\u0001\u0005s\nQ\u0002S5wK\u00163\u0018\r\\;bi>\u0014\bc\u0001B\u001bMM)aEa-\u0003@BQ!Q\u0017B^\u0005\u000f\t\tNa\r\u000e\u0005\t]&\u0002\u0002B]\u0003#\tqA];oi&lW-\u0003\u0003\u0003>\n]&!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8oeA!!\u0011\u0019Bd\u001b\t\u0011\u0019M\u0003\u0003\u0003F\n5\u0014AA5p\u0013\u0011\tyCa1\u0015\u0005\t=\u0016!B1qa2LHC\u0002B\u001a\u0005\u001f\u0014\t\u000eC\u0004\u0003&%\u0002\rAa\u0002\t\u000f\t-\u0012\u00061\u0001\u0002R\u00069QO\\1qa2LH\u0003\u0002Bl\u0005G\u0004b!a\u0004\u0003Z\nu\u0017\u0002\u0002Bn\u0003#\u0011aa\u00149uS>t\u0007\u0003CA\b\u0005?\u00149!!5\n\t\t\u0005\u0018\u0011\u0003\u0002\u0007)V\u0004H.\u001a\u001a\t\u0013\t\u0015(&!AA\u0002\tM\u0012a\u0001=%a\u0005)\u0002/\u0019:uS\u0006d\u0017\u0007S5wK\u00163\u0018\r\\;bi>\u0014XC\u0001B\u001aQ\rY\u0013\u0011^\u0001\u0013M&t\u0017\r\u001c%jm\u0016,e/\u00197vCR|'\u000fK\u0002-\u0003S\fQ\u0003]1si&\fGNU3tk2$H)\u0019;b)f\u0004X-\u0006\u0002\u0002v\"\u001aQ&!;\u0002\u001b%t\u0007/\u001e;Xe\u0006\u0004\b/\u001a:t+\t\u0011Y\u0010\u0005\u0004\u0002\u0010\u00055'Q \t\t\u0003\u001f\u0011yP!\u001f\u0003z%!1\u0011AA\t\u0005%1UO\\2uS>t\u0017\u0007K\u0002/\u0003S\fqB]3tk2$XK\\<sCB\u0004XM]\u000b\u0003\u0005{D3aLAu\u0003\u0019\u0019\u0017m\u00195fIV\u00111q\u0002\t\u0007\u0003\u001f\tiMa\b)\u0007A\nI/\u0001\bbO\u001e\u0014UO\u001a4feN+'\u000fR3\u0016\u0005\r]\u0001c\u0001B\u001b{\t1\u0012iZ4sK\u001e\fG/[8o\u0005V4g-\u001a:TKJ$UmE\u0002>\u0005?!\"aa\u0006\u0002-A\f'\u000f^5bYJ+7/\u001e7u+:<(/\u00199qKJ\fq\u0003]1si&\fGNU3tk2$XK\\<sCB\u0004XM\u001d\u0011\u0002)A\f'\u000f^5bYJ+7/\u001e7u/J\f\u0007\u000f]3s\u0003U\u0001\u0018M\u001d;jC2\u0014Vm];mi^\u0013\u0018\r\u001d9fe\u0002\n!\u0002\u001d:pU\u0016\u001cG/[8o+\t\u0019Y\u0003\u0005\u0003\u0002\b\r5\u0012bAB\u0018k\n\u0001RK\\:bM\u0016\u0004&o\u001c6fGRLwN\\\u0001\faJ|'.Z2uS>t\u0007%\u0001\u0006nkR\f'\r\\3S_^,\"aa\u000e\u0011\t\u0005\u001d1\u0011H\u0005\u0004\u0007w)(AE$f]\u0016\u0014\u0018nY%oi\u0016\u0014h.\u00197S_^\f1\"\\;uC\ndWMU8xA\u0005I1/\u001a:jC2L'0\u001a\u000b\u0005\u0007\u0007\u001aY\u0005\u0005\u0004\u0002\u0010\u000557Q\t\t\u0005\u0003\u001f\u00199%\u0003\u0003\u0004J\u0005E!\u0001\u0002\"zi\u0016Dqa!\u0014H\u0001\u0004\u0019y%\u0001\u0004ck\u001a4WM\u001d\t\u0005\u0007#\u001aYG\u0004\u0003\u0004T\r\u001dd\u0002BB+\u0007KrAaa\u0016\u0004d9!1\u0011LB1\u001d\u0011\u0019Yfa\u0018\u000f\t\u0005]3QL\u0005\u0004\u0003Cd\u0017bA4\u0002`&!!QCAo\u0013\u0011\u0011\tBa\u0005\n\t\t5!qB\u0005\u0005\u0007S\u0012Y!\u0001\u000bHK:,'/[2V\t\u00063UI^1mk\u0006$xN]\u0005\u0005\u0007[\u001ayGA\tBO\u001e\u0014XmZ1uS>t')\u001e4gKJTAa!\u001b\u0003\f\u0005YA-Z:fe&\fG.\u001b>f)\u0011\u0019ye!\u001e\t\u000f\r]\u0004\n1\u0001\u0004D\u0005)!-\u001f;fg\"\u001a\u0011'!;\u0002\u00119,H\u000e\\1cY\u0016\f\u0001\u0002Z1uCRK\b/Z\u0001\u000baJ,G\u000f^=OC6,G\u0003BA\u001c\u0007\u0007Cqa!\"6\u0001\u0004\t9)\u0001\u0006jg\u0012K7\u000f^5oGR\fqc\u0019:fCR,\u0017iZ4sK\u001e\fG/[8o\u0005V4g-\u001a:\u0015\u0003m\fq\"\u001b8qkR\u0004&o\u001c6fGRLwN\u001c\u0015\u0004o\u0005%\u0018AB;qI\u0006$X\rF\u0003|\u0007'\u001b)\n\u0003\u0004\u0004Na\u0002\ra\u001f\u0005\b\u0007/C\u0004\u0019ABM\u0003\u0015Ig\u000e];u!\u0011\u0019Yj!(\u000e\u0003]L1aa(x\u0005-Ie\u000e^3s]\u0006d'k\\<\u0002\u000b5,'oZ3\u0015\u000bm\u001c)ka*\t\r\r5\u0013\b1\u0001|\u0011\u0019\u00199*\u000fa\u0001w\u0006!QM^1m)\u0011\u0011Ih!,\t\r\r5#\b1\u0001|)\u0011\u0019\u0019e!-\t\r\r53\b1\u0001|)\rY8Q\u0017\u0005\b\u0007ob\u0004\u0019AB\"\u0003]9\u0018\u000e\u001e5OK^\u001c\u0005.\u001b7ee\u0016t\u0017J\u001c;fe:\fG\u000e\u0006\u0003\u0002|\rm\u0006bBB_\u0013\u0002\u00071qX\u0001\f]\u0016<8\t[5mIJ,g\u000e\u0005\u0004\u0002\u001c\r\u0005\u00171P\u0005\u0005\u0007\u0007\f\tD\u0001\u0006J]\u0012,\u00070\u001a3TKF$b\"a)\u0004H\u000e%71ZBg\u0007\u001f\u001c\t\u000eC\u0005\u00024)\u0003\n\u00111\u0001\u00028!I\u0011\u0011\n&\u0011\u0002\u0003\u0007\u0011Q\n\u0005\n\u0003cR\u0005\u0013!a\u0001\u0003kB\u0011\"a!K!\u0003\u0005\r!a\"\t\u0013\u0005=%\n%AA\u0002\u0005M\u0005\"CAN\u0015B\u0005\t\u0019AAJ+\t\u0019)N\u000b\u0003\u00028\t%SCABmU\u0011\tiE!\u0013\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%gU\u00111q\u001c\u0016\u0005\u0003k\u0012I%\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001b\u0016\u0005\r\u0015(\u0006BAD\u0005\u0013\nabY8qs\u0012\"WMZ1vYR$S'\u0006\u0002\u0004l*\"\u00111\u0013B%\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIY\"BA!\u001f\u0004r\"I!\u0011Q*\u0002\u0002\u0003\u0007\u00111\u0013\u000b\u0005\u0003\u000f\u001b)\u0010C\u0005\u0003\u0002V\u000b\t\u00111\u0001\u0003zQ!!QMB}\u0011%\u0011\tIVA\u0001\u0002\u0004\t\u0019\n\u0006\u0003\u0002\b\u000eu\b\"\u0003BA/\u0006\u0005\t\u0019\u0001B=\u0003AA\u0015N^3V\t\u00063e)\u001e8di&|g\u000e\u0005\u0002}3N)\u0011\f\"\u0002\u0003@B\u0011\"Q\u0017C\u0004\u0003o\ti%!\u001e\u0002\b\u0006M\u00151SAR\u0013\u0011!IAa.\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>tg\u0007\u0006\u0002\u0005\u0002Qq\u00111\u0015C\b\t#!\u0019\u0002\"\u0006\u0005\u0018\u0011e\u0001bBA\u001a9\u0002\u0007\u0011q\u0007\u0005\b\u0003\u0013b\u0006\u0019AA'\u0011\u001d\t\t\b\u0018a\u0001\u0003kB\u0011\"a!]!\u0003\u0005\r!a\"\t\u0013\u0005=E\f%AA\u0002\u0005M\u0005\"CAN9B\u0005\t\u0019AAJ\u0003=\t\u0007\u000f\u001d7zI\u0011,g-Y;mi\u0012\"\u0014aD1qa2LH\u0005Z3gCVdG\u000fJ\u001b\u0002\u001f\u0005\u0004\b\u000f\\=%I\u00164\u0017-\u001e7uIY\"B\u0001b\t\u0005,A1\u0011q\u0002Bm\tK\u0001\u0002#a\u0004\u0005(\u0005]\u0012QJA;\u0003\u000f\u000b\u0019*a%\n\t\u0011%\u0012\u0011\u0003\u0002\u0007)V\u0004H.\u001a\u001c\t\u0013\t\u0015\b-!AA\u0002\u0005\r\u0016a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$C'A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H%N\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001c\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0011]\u0002\u0003\u0002B4\tsIA\u0001b\u000f\u0003j\t1qJ\u00196fGR\u0004"
)
public class HiveUDAFFunction extends TypedImperativeAggregate implements HiveInspectors, UserDefinedExpression, Serializable {
   private transient ObjectInspector[] inputInspectors;
   private transient DataType[] inputDataTypes;
   private volatile HiveEvaluator$ HiveEvaluator$module;
   private transient HiveEvaluator org$apache$spark$sql$hive$HiveUDAFFunction$$partial1HiveEvaluator;
   private transient HiveEvaluator org$apache$spark$sql$hive$HiveUDAFFunction$$finalHiveEvaluator;
   private transient DataType org$apache$spark$sql$hive$HiveUDAFFunction$$partialResultDataType;
   private transient Function1[] inputWrappers;
   private transient Function1 resultUnwrapper;
   private transient Object[] cached;
   private transient AggregationBufferSerDe aggBufferSerDe;
   private DataType dataType;
   private transient UnsafeProjection inputProjection;
   private final String name;
   private final HiveShim.HiveFunctionWrapper funcWrapper;
   private final Seq children;
   private final boolean isUDAFBridgeRequired;
   private final int mutableAggBufferOffset;
   private final int inputAggBufferOffset;
   private volatile boolean bitmap$0;
   private transient volatile int bitmap$trans$0;

   public static int $lessinit$greater$default$6() {
      return HiveUDAFFunction$.MODULE$.$lessinit$greater$default$6();
   }

   public static int $lessinit$greater$default$5() {
      return HiveUDAFFunction$.MODULE$.$lessinit$greater$default$5();
   }

   public static boolean $lessinit$greater$default$4() {
      return HiveUDAFFunction$.MODULE$.$lessinit$greater$default$4();
   }

   public static Option unapply(final HiveUDAFFunction x$0) {
      return HiveUDAFFunction$.MODULE$.unapply(x$0);
   }

   public static int apply$default$6() {
      return HiveUDAFFunction$.MODULE$.apply$default$6();
   }

   public static int apply$default$5() {
      return HiveUDAFFunction$.MODULE$.apply$default$5();
   }

   public static boolean apply$default$4() {
      return HiveUDAFFunction$.MODULE$.apply$default$4();
   }

   public static Function1 tupled() {
      return HiveUDAFFunction$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return HiveUDAFFunction$.MODULE$.curried();
   }

   public DataType javaTypeToDataType(final Type clz) {
      return HiveInspectors.javaTypeToDataType$(this, clz);
   }

   public Function1 wrapperFor(final ObjectInspector oi, final DataType dataType) {
      return HiveInspectors.wrapperFor$(this, oi, dataType);
   }

   public Function1 unwrapperFor(final ObjectInspector objectInspector) {
      return HiveInspectors.unwrapperFor$(this, (ObjectInspector)objectInspector);
   }

   public Function3 unwrapperFor(final StructField field) {
      return HiveInspectors.unwrapperFor$(this, (StructField)field);
   }

   public Object wrap(final Object a, final ObjectInspector oi, final DataType dataType) {
      return HiveInspectors.wrap$(this, (Object)a, (ObjectInspector)oi, (DataType)dataType);
   }

   public Object[] wrap(final InternalRow row, final Function1[] wrappers, final Object[] cache, final DataType[] dataTypes) {
      return HiveInspectors.wrap$(this, row, wrappers, cache, dataTypes);
   }

   public Object[] wrap(final Seq row, final Function1[] wrappers, final Object[] cache) {
      return HiveInspectors.wrap$(this, (Seq)row, (Function1[])wrappers, (Object[])cache);
   }

   public ObjectInspector toInspector(final DataType dataType) {
      return HiveInspectors.toInspector$(this, (DataType)dataType);
   }

   public ObjectInspector toInspector(final Expression expr) {
      return HiveInspectors.toInspector$(this, (Expression)expr);
   }

   public DataType inspectorToDataType(final ObjectInspector inspector) {
      return HiveInspectors.inspectorToDataType$(this, inspector);
   }

   public HiveInspectors.typeInfoConversions typeInfoConversions(final DataType dt) {
      return HiveInspectors.typeInfoConversions$(this, dt);
   }

   private HiveEvaluator$ HiveEvaluator() {
      if (this.HiveEvaluator$module == null) {
         this.HiveEvaluator$lzycompute$1();
      }

      return this.HiveEvaluator$module;
   }

   public String name() {
      return this.name;
   }

   public HiveShim.HiveFunctionWrapper funcWrapper() {
      return this.funcWrapper;
   }

   public Seq children() {
      return this.children;
   }

   public boolean isUDAFBridgeRequired() {
      return this.isUDAFBridgeRequired;
   }

   public int mutableAggBufferOffset() {
      return this.mutableAggBufferOffset;
   }

   public int inputAggBufferOffset() {
      return this.inputAggBufferOffset;
   }

   public ImperativeAggregate withNewMutableAggBufferOffset(final int newMutableAggBufferOffset) {
      String x$2 = this.copy$default$1();
      HiveShim.HiveFunctionWrapper x$3 = this.copy$default$2();
      Seq x$4 = this.copy$default$3();
      boolean x$5 = this.copy$default$4();
      int x$6 = this.copy$default$6();
      return this.copy(x$2, x$3, x$4, x$5, newMutableAggBufferOffset, x$6);
   }

   public ImperativeAggregate withNewInputAggBufferOffset(final int newInputAggBufferOffset) {
      String x$2 = this.copy$default$1();
      HiveShim.HiveFunctionWrapper x$3 = this.copy$default$2();
      Seq x$4 = this.copy$default$3();
      boolean x$5 = this.copy$default$4();
      int x$6 = this.copy$default$5();
      return this.copy(x$2, x$3, x$4, x$5, x$6, newInputAggBufferOffset);
   }

   private ObjectInspector[] inputInspectors$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$trans$0 & 1) == 0) {
            this.inputInspectors = (ObjectInspector[])((IterableOnceOps)this.children().map((expr) -> this.toInspector(expr))).toArray(.MODULE$.apply(ObjectInspector.class));
            this.bitmap$trans$0 |= 1;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.inputInspectors;
   }

   private ObjectInspector[] inputInspectors() {
      return (this.bitmap$trans$0 & 1) == 0 ? this.inputInspectors$lzycompute() : this.inputInspectors;
   }

   private DataType[] inputDataTypes$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$trans$0 & 2) == 0) {
            this.inputDataTypes = (DataType[])((IterableOnceOps)this.children().map((x$9) -> x$9.dataType())).toArray(.MODULE$.apply(DataType.class));
            this.bitmap$trans$0 |= 2;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.inputDataTypes;
   }

   private DataType[] inputDataTypes() {
      return (this.bitmap$trans$0 & 2) == 0 ? this.inputDataTypes$lzycompute() : this.inputDataTypes;
   }

   private GenericUDAFEvaluator newEvaluator() {
      AbstractGenericUDAFResolver resolver = (AbstractGenericUDAFResolver)(this.isUDAFBridgeRequired() ? new SparkGenericUDAFBridge((UDAF)this.funcWrapper().createFunction()) : (AbstractGenericUDAFResolver)this.funcWrapper().createFunction());
      SimpleGenericUDAFParameterInfo parameterInfo = new SimpleGenericUDAFParameterInfo(this.inputInspectors(), false, false, false);
      return resolver.getEvaluator(parameterInfo);
   }

   private HiveEvaluator partial1HiveEvaluator$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$trans$0 & 4) == 0) {
            GenericUDAFEvaluator evaluator = this.newEvaluator();
            this.org$apache$spark$sql$hive$HiveUDAFFunction$$partial1HiveEvaluator = new HiveEvaluator(evaluator, evaluator.init(Mode.PARTIAL1, this.inputInspectors()));
            this.bitmap$trans$0 |= 4;
         }
      } catch (Throwable var4) {
         throw var4;
      }

      return this.org$apache$spark$sql$hive$HiveUDAFFunction$$partial1HiveEvaluator;
   }

   public HiveEvaluator org$apache$spark$sql$hive$HiveUDAFFunction$$partial1HiveEvaluator() {
      return (this.bitmap$trans$0 & 4) == 0 ? this.partial1HiveEvaluator$lzycompute() : this.org$apache$spark$sql$hive$HiveUDAFFunction$$partial1HiveEvaluator;
   }

   private HiveEvaluator finalHiveEvaluator$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$trans$0 & 8) == 0) {
            GenericUDAFEvaluator evaluator = this.newEvaluator();
            this.org$apache$spark$sql$hive$HiveUDAFFunction$$finalHiveEvaluator = new HiveEvaluator(evaluator, evaluator.init(Mode.FINAL, (ObjectInspector[])((Object[])(new ObjectInspector[]{this.org$apache$spark$sql$hive$HiveUDAFFunction$$partial1HiveEvaluator().objectInspector()}))));
            this.bitmap$trans$0 |= 8;
         }
      } catch (Throwable var4) {
         throw var4;
      }

      return this.org$apache$spark$sql$hive$HiveUDAFFunction$$finalHiveEvaluator;
   }

   public HiveEvaluator org$apache$spark$sql$hive$HiveUDAFFunction$$finalHiveEvaluator() {
      return (this.bitmap$trans$0 & 8) == 0 ? this.finalHiveEvaluator$lzycompute() : this.org$apache$spark$sql$hive$HiveUDAFFunction$$finalHiveEvaluator;
   }

   private DataType partialResultDataType$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$trans$0 & 16) == 0) {
            this.org$apache$spark$sql$hive$HiveUDAFFunction$$partialResultDataType = this.inspectorToDataType(this.org$apache$spark$sql$hive$HiveUDAFFunction$$partial1HiveEvaluator().objectInspector());
            this.bitmap$trans$0 |= 16;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.org$apache$spark$sql$hive$HiveUDAFFunction$$partialResultDataType;
   }

   public DataType org$apache$spark$sql$hive$HiveUDAFFunction$$partialResultDataType() {
      return (this.bitmap$trans$0 & 16) == 0 ? this.partialResultDataType$lzycompute() : this.org$apache$spark$sql$hive$HiveUDAFFunction$$partialResultDataType;
   }

   private Function1[] inputWrappers$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$trans$0 & 32) == 0) {
            this.inputWrappers = (Function1[])((IterableOnceOps)this.children().map((x) -> this.wrapperFor(this.toInspector(x), x.dataType()))).toArray(.MODULE$.apply(Function1.class));
            this.bitmap$trans$0 |= 32;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.inputWrappers;
   }

   private Function1[] inputWrappers() {
      return (this.bitmap$trans$0 & 32) == 0 ? this.inputWrappers$lzycompute() : this.inputWrappers;
   }

   private Function1 resultUnwrapper$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$trans$0 & 64) == 0) {
            this.resultUnwrapper = this.unwrapperFor(this.org$apache$spark$sql$hive$HiveUDAFFunction$$finalHiveEvaluator().objectInspector());
            this.bitmap$trans$0 |= 64;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.resultUnwrapper;
   }

   private Function1 resultUnwrapper() {
      return (this.bitmap$trans$0 & 64) == 0 ? this.resultUnwrapper$lzycompute() : this.resultUnwrapper;
   }

   private Object[] cached$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$trans$0 & 128) == 0) {
            this.cached = new Object[this.children().length()];
            this.bitmap$trans$0 |= 128;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.cached;
   }

   private Object[] cached() {
      return (this.bitmap$trans$0 & 128) == 0 ? this.cached$lzycompute() : this.cached;
   }

   private AggregationBufferSerDe aggBufferSerDe$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$trans$0 & 256) == 0) {
            this.aggBufferSerDe = new AggregationBufferSerDe();
            this.bitmap$trans$0 |= 256;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.aggBufferSerDe;
   }

   private AggregationBufferSerDe aggBufferSerDe() {
      return (this.bitmap$trans$0 & 256) == 0 ? this.aggBufferSerDe$lzycompute() : this.aggBufferSerDe;
   }

   public boolean nullable() {
      return true;
   }

   private DataType dataType$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.dataType = this.inspectorToDataType(this.org$apache$spark$sql$hive$HiveUDAFFunction$$finalHiveEvaluator().objectInspector());
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.dataType;
   }

   public DataType dataType() {
      return !this.bitmap$0 ? this.dataType$lzycompute() : this.dataType;
   }

   public String prettyName() {
      return this.name();
   }

   public String sql(final boolean isDistinct) {
      String distinct = isDistinct ? "DISTINCT " : " ";
      String var10000 = this.name();
      return var10000 + "(" + distinct + ((IterableOnceOps)this.children().map((x$10) -> x$10.sql())).mkString(", ") + ")";
   }

   public HiveUDAFBuffer createAggregationBuffer() {
      return null;
   }

   private UnsafeProjection inputProjection$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$trans$0 & 512) == 0) {
            this.inputProjection = org.apache.spark.sql.catalyst.expressions.UnsafeProjection..MODULE$.create(this.children());
            this.bitmap$trans$0 |= 512;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.inputProjection;
   }

   private UnsafeProjection inputProjection() {
      return (this.bitmap$trans$0 & 512) == 0 ? this.inputProjection$lzycompute() : this.inputProjection;
   }

   public HiveUDAFBuffer update(final HiveUDAFBuffer buffer, final InternalRow input) {
      HiveUDAFBuffer nonNullBuffer = buffer == null ? new HiveUDAFBuffer(this.org$apache$spark$sql$hive$HiveUDAFFunction$$partial1HiveEvaluator().evaluator().getNewAggregationBuffer(), false) : buffer;
      scala.Predef..MODULE$.assert(!nonNullBuffer.canDoMerge(), () -> "can not call `merge` then `update` on a Hive UDAF.");
      this.org$apache$spark$sql$hive$HiveUDAFFunction$$partial1HiveEvaluator().evaluator().iterate(nonNullBuffer.buf(), this.wrap(this.inputProjection().apply(input), this.inputWrappers(), this.cached(), this.inputDataTypes()));
      return nonNullBuffer;
   }

   public HiveUDAFBuffer merge(final HiveUDAFBuffer buffer, final HiveUDAFBuffer input) {
      HiveUDAFBuffer nonNullBuffer = buffer == null ? new HiveUDAFBuffer(this.org$apache$spark$sql$hive$HiveUDAFFunction$$finalHiveEvaluator().evaluator().getNewAggregationBuffer(), true) : buffer;
      HiveUDAFBuffer var10000;
      if (!nonNullBuffer.canDoMerge()) {
         GenericUDAFEvaluator.AggregationBuffer newBuf = this.org$apache$spark$sql$hive$HiveUDAFFunction$$finalHiveEvaluator().evaluator().getNewAggregationBuffer();
         this.org$apache$spark$sql$hive$HiveUDAFFunction$$finalHiveEvaluator().evaluator().merge(newBuf, this.org$apache$spark$sql$hive$HiveUDAFFunction$$partial1HiveEvaluator().evaluator().terminatePartial(nonNullBuffer.buf()));
         var10000 = new HiveUDAFBuffer(newBuf, true);
      } else {
         var10000 = nonNullBuffer;
      }

      HiveUDAFBuffer mergeableBuf = var10000;
      this.org$apache$spark$sql$hive$HiveUDAFFunction$$finalHiveEvaluator().evaluator().merge(mergeableBuf.buf(), this.org$apache$spark$sql$hive$HiveUDAFFunction$$partial1HiveEvaluator().evaluator().terminatePartial(input.buf()));
      return mergeableBuf;
   }

   public Object eval(final HiveUDAFBuffer buffer) {
      return this.resultUnwrapper().apply(this.org$apache$spark$sql$hive$HiveUDAFFunction$$finalHiveEvaluator().evaluator().terminate(buffer == null ? this.org$apache$spark$sql$hive$HiveUDAFFunction$$finalHiveEvaluator().evaluator().getNewAggregationBuffer() : buffer.buf()));
   }

   public byte[] serialize(final HiveUDAFBuffer buffer) {
      return this.aggBufferSerDe().serialize(buffer == null ? null : buffer.buf());
   }

   public HiveUDAFBuffer deserialize(final byte[] bytes) {
      return new HiveUDAFBuffer(this.aggBufferSerDe().deserialize(bytes), false);
   }

   public Expression withNewChildrenInternal(final IndexedSeq newChildren) {
      String x$2 = this.copy$default$1();
      HiveShim.HiveFunctionWrapper x$3 = this.copy$default$2();
      boolean x$4 = this.copy$default$4();
      int x$5 = this.copy$default$5();
      int x$6 = this.copy$default$6();
      return this.copy(x$2, x$3, newChildren, x$4, x$5, x$6);
   }

   public HiveUDAFFunction copy(final String name, final HiveShim.HiveFunctionWrapper funcWrapper, final Seq children, final boolean isUDAFBridgeRequired, final int mutableAggBufferOffset, final int inputAggBufferOffset) {
      return new HiveUDAFFunction(name, funcWrapper, children, isUDAFBridgeRequired, mutableAggBufferOffset, inputAggBufferOffset);
   }

   public String copy$default$1() {
      return this.name();
   }

   public HiveShim.HiveFunctionWrapper copy$default$2() {
      return this.funcWrapper();
   }

   public Seq copy$default$3() {
      return this.children();
   }

   public boolean copy$default$4() {
      return this.isUDAFBridgeRequired();
   }

   public int copy$default$5() {
      return this.mutableAggBufferOffset();
   }

   public int copy$default$6() {
      return this.inputAggBufferOffset();
   }

   public String productPrefix() {
      return "HiveUDAFFunction";
   }

   public int productArity() {
      return 6;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.name();
         }
         case 1 -> {
            return this.funcWrapper();
         }
         case 2 -> {
            return this.children();
         }
         case 3 -> {
            return BoxesRunTime.boxToBoolean(this.isUDAFBridgeRequired());
         }
         case 4 -> {
            return BoxesRunTime.boxToInteger(this.mutableAggBufferOffset());
         }
         case 5 -> {
            return BoxesRunTime.boxToInteger(this.inputAggBufferOffset());
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
      return x$1 instanceof HiveUDAFFunction;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "name";
         }
         case 1 -> {
            return "funcWrapper";
         }
         case 2 -> {
            return "children";
         }
         case 3 -> {
            return "isUDAFBridgeRequired";
         }
         case 4 -> {
            return "mutableAggBufferOffset";
         }
         case 5 -> {
            return "inputAggBufferOffset";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public boolean equals(final Object x$1) {
      boolean var10;
      if (this != x$1) {
         label75: {
            if (x$1 instanceof HiveUDAFFunction) {
               HiveUDAFFunction var4 = (HiveUDAFFunction)x$1;
               if (this.isUDAFBridgeRequired() == var4.isUDAFBridgeRequired() && this.mutableAggBufferOffset() == var4.mutableAggBufferOffset() && this.inputAggBufferOffset() == var4.inputAggBufferOffset()) {
                  label68: {
                     String var10000 = this.name();
                     String var5 = var4.name();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label68;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label68;
                     }

                     HiveShim.HiveFunctionWrapper var8 = this.funcWrapper();
                     HiveShim.HiveFunctionWrapper var6 = var4.funcWrapper();
                     if (var8 == null) {
                        if (var6 != null) {
                           break label68;
                        }
                     } else if (!var8.equals(var6)) {
                        break label68;
                     }

                     Seq var9 = this.children();
                     Seq var7 = var4.children();
                     if (var9 == null) {
                        if (var7 != null) {
                           break label68;
                        }
                     } else if (!var9.equals(var7)) {
                        break label68;
                     }

                     if (var4.canEqual(this)) {
                        break label75;
                     }
                  }
               }
            }

            var10 = false;
            return var10;
         }
      }

      var10 = true;
      return var10;
   }

   private final void HiveEvaluator$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.HiveEvaluator$module == null) {
            this.HiveEvaluator$module = new HiveEvaluator$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   public HiveUDAFFunction(final String name, final HiveShim.HiveFunctionWrapper funcWrapper, final Seq children, final boolean isUDAFBridgeRequired, final int mutableAggBufferOffset, final int inputAggBufferOffset) {
      this.name = name;
      this.funcWrapper = funcWrapper;
      this.children = children;
      this.isUDAFBridgeRequired = isUDAFBridgeRequired;
      this.mutableAggBufferOffset = mutableAggBufferOffset;
      this.inputAggBufferOffset = inputAggBufferOffset;
      HiveInspectors.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private class HiveEvaluator implements Product, Serializable {
      private final GenericUDAFEvaluator evaluator;
      private final ObjectInspector objectInspector;
      // $FF: synthetic field
      public final HiveUDAFFunction $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public GenericUDAFEvaluator evaluator() {
         return this.evaluator;
      }

      public ObjectInspector objectInspector() {
         return this.objectInspector;
      }

      public HiveEvaluator copy(final GenericUDAFEvaluator evaluator, final ObjectInspector objectInspector) {
         return this.org$apache$spark$sql$hive$HiveUDAFFunction$HiveEvaluator$$$outer().new HiveEvaluator(evaluator, objectInspector);
      }

      public GenericUDAFEvaluator copy$default$1() {
         return this.evaluator();
      }

      public ObjectInspector copy$default$2() {
         return this.objectInspector();
      }

      public String productPrefix() {
         return "HiveEvaluator";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return this.evaluator();
            }
            case 1 -> {
               return this.objectInspector();
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
         return x$1 instanceof HiveEvaluator;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "evaluator";
            }
            case 1 -> {
               return "objectInspector";
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
         boolean var8;
         if (this != x$1) {
            label60: {
               if (x$1 instanceof HiveEvaluator && ((HiveEvaluator)x$1).org$apache$spark$sql$hive$HiveUDAFFunction$HiveEvaluator$$$outer() == this.org$apache$spark$sql$hive$HiveUDAFFunction$HiveEvaluator$$$outer()) {
                  label50: {
                     HiveEvaluator var4 = (HiveEvaluator)x$1;
                     GenericUDAFEvaluator var10000 = this.evaluator();
                     GenericUDAFEvaluator var5 = var4.evaluator();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label50;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label50;
                     }

                     ObjectInspector var7 = this.objectInspector();
                     ObjectInspector var6 = var4.objectInspector();
                     if (var7 == null) {
                        if (var6 != null) {
                           break label50;
                        }
                     } else if (!var7.equals(var6)) {
                        break label50;
                     }

                     if (var4.canEqual(this)) {
                        break label60;
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

      // $FF: synthetic method
      public HiveUDAFFunction org$apache$spark$sql$hive$HiveUDAFFunction$HiveEvaluator$$$outer() {
         return this.$outer;
      }

      public HiveEvaluator(final GenericUDAFEvaluator evaluator, final ObjectInspector objectInspector) {
         this.evaluator = evaluator;
         this.objectInspector = objectInspector;
         if (HiveUDAFFunction.this == null) {
            throw null;
         } else {
            this.$outer = HiveUDAFFunction.this;
            super();
            Product.$init$(this);
         }
      }
   }

   private class HiveEvaluator$ extends AbstractFunction2 implements Serializable {
      // $FF: synthetic field
      private final HiveUDAFFunction $outer;

      public final String toString() {
         return "HiveEvaluator";
      }

      public HiveEvaluator apply(final GenericUDAFEvaluator evaluator, final ObjectInspector objectInspector) {
         return this.$outer.new HiveEvaluator(evaluator, objectInspector);
      }

      public Option unapply(final HiveEvaluator x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.evaluator(), x$0.objectInspector())));
      }

      public HiveEvaluator$() {
         if (HiveUDAFFunction.this == null) {
            throw null;
         } else {
            this.$outer = HiveUDAFFunction.this;
            super();
         }
      }
   }

   private class AggregationBufferSerDe {
      private final Function1 partialResultUnwrapper;
      private final Function1 partialResultWrapper;
      private final UnsafeProjection projection;
      private final GenericInternalRow mutableRow;
      // $FF: synthetic field
      public final HiveUDAFFunction $outer;

      private Function1 partialResultUnwrapper() {
         return this.partialResultUnwrapper;
      }

      private Function1 partialResultWrapper() {
         return this.partialResultWrapper;
      }

      private UnsafeProjection projection() {
         return this.projection;
      }

      private GenericInternalRow mutableRow() {
         return this.mutableRow;
      }

      public byte[] serialize(final GenericUDAFEvaluator.AggregationBuffer buffer) {
         GenericUDAFEvaluator.AggregationBuffer nonNullBuffer = buffer == null ? this.org$apache$spark$sql$hive$HiveUDAFFunction$AggregationBufferSerDe$$$outer().org$apache$spark$sql$hive$HiveUDAFFunction$$partial1HiveEvaluator().evaluator().getNewAggregationBuffer() : buffer;
         this.mutableRow().update(0, this.partialResultUnwrapper().apply(this.org$apache$spark$sql$hive$HiveUDAFFunction$AggregationBufferSerDe$$$outer().org$apache$spark$sql$hive$HiveUDAFFunction$$partial1HiveEvaluator().evaluator().terminatePartial(nonNullBuffer)));
         UnsafeRow unsafeRow = this.projection().apply(this.mutableRow());
         ByteBuffer bytes = ByteBuffer.allocate(unsafeRow.getSizeInBytes());
         unsafeRow.writeTo(bytes);
         return bytes.array();
      }

      public GenericUDAFEvaluator.AggregationBuffer deserialize(final byte[] bytes) {
         GenericUDAFEvaluator.AggregationBuffer buffer = this.org$apache$spark$sql$hive$HiveUDAFFunction$AggregationBufferSerDe$$$outer().org$apache$spark$sql$hive$HiveUDAFFunction$$finalHiveEvaluator().evaluator().getNewAggregationBuffer();
         UnsafeRow unsafeRow = new UnsafeRow(1);
         unsafeRow.pointTo(bytes, bytes.length);
         Object partialResult = unsafeRow.get(0, this.org$apache$spark$sql$hive$HiveUDAFFunction$AggregationBufferSerDe$$$outer().org$apache$spark$sql$hive$HiveUDAFFunction$$partialResultDataType());
         this.org$apache$spark$sql$hive$HiveUDAFFunction$AggregationBufferSerDe$$$outer().org$apache$spark$sql$hive$HiveUDAFFunction$$finalHiveEvaluator().evaluator().merge(buffer, this.partialResultWrapper().apply(partialResult));
         return buffer;
      }

      // $FF: synthetic method
      public HiveUDAFFunction org$apache$spark$sql$hive$HiveUDAFFunction$AggregationBufferSerDe$$$outer() {
         return this.$outer;
      }

      public AggregationBufferSerDe() {
         if (HiveUDAFFunction.this == null) {
            throw null;
         } else {
            this.$outer = HiveUDAFFunction.this;
            super();
            this.partialResultUnwrapper = HiveUDAFFunction.this.unwrapperFor(HiveUDAFFunction.this.org$apache$spark$sql$hive$HiveUDAFFunction$$partial1HiveEvaluator().objectInspector());
            this.partialResultWrapper = HiveUDAFFunction.this.wrapperFor(HiveUDAFFunction.this.org$apache$spark$sql$hive$HiveUDAFFunction$$partial1HiveEvaluator().objectInspector(), HiveUDAFFunction.this.org$apache$spark$sql$hive$HiveUDAFFunction$$partialResultDataType());
            this.projection = org.apache.spark.sql.catalyst.expressions.UnsafeProjection..MODULE$.create((DataType[])((Object[])(new DataType[]{HiveUDAFFunction.this.org$apache$spark$sql$hive$HiveUDAFFunction$$partialResultDataType()})));
            this.mutableRow = new GenericInternalRow(1);
         }
      }
   }
}
