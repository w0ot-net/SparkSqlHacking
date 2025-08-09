package breeze.linalg.operators;

import breeze.generic.MMRegistry2;
import breeze.generic.UFunc;
import breeze.linalg.CSCMatrix;
import breeze.linalg.CSCMatrix$;
import breeze.linalg.CSCMatrix$Builder$mcD$sp;
import breeze.linalg.CSCMatrix$Builder$mcF$sp;
import breeze.linalg.CSCMatrix$Builder$mcI$sp;
import breeze.linalg.CSCMatrix$Builder$mcJ$sp;
import breeze.linalg.CSCMatrix$mcD$sp;
import breeze.linalg.CSCMatrix$mcF$sp;
import breeze.linalg.CSCMatrix$mcI$sp;
import breeze.linalg.CSCMatrix$mcJ$sp;
import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseMatrix$;
import breeze.linalg.DenseMatrix$mcD$sp;
import breeze.linalg.DenseMatrix$mcF$sp;
import breeze.linalg.DenseMatrix$mcI$sp;
import breeze.linalg.DenseMatrix$mcJ$sp;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.NumericOps;
import breeze.linalg.SparseVector;
import breeze.linalg.Transpose;
import breeze.linalg.Vector;
import breeze.linalg.VectorBuilder;
import breeze.linalg.VectorBuilder$mcD$sp;
import breeze.linalg.VectorBuilder$mcF$sp;
import breeze.linalg.VectorBuilder$mcI$sp;
import breeze.linalg.VectorBuilder$mcJ$sp;
import breeze.linalg.max$;
import breeze.math.Field;
import breeze.math.PowImplicits$;
import breeze.math.Ring$;
import breeze.math.Semiring;
import breeze.math.Semiring$;
import breeze.storage.Zero;
import breeze.storage.Zero$;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import scala.Array.;
import scala.collection.MapView;
import scala.collection.immutable.Map;
import scala.collection.mutable.HashMap;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u00195cACA\u001b\u0003o\u0001\n1!\u0001\u0002F!9\u0011\u0011\r\u0001\u0005\u0002\u0005\r\u0004bBA6\u0001\u0011\r\u0011Q\u000e\u0005\b\u0003\u007f\u0003A1AAa\u0011%\ti\u000f\u0001b\u0001\n\u0007\ty\u000fC\u0005\u0003\u0006\u0001\u0011\r\u0011b\u0001\u0003\b!I!1\u0003\u0001C\u0002\u0013\r!Q\u0003\u0005\n\u0005C\u0001!\u0019!C\u0002\u0005GA\u0011Ba\f\u0001\u0005\u0004%\u0019A!\r\t\u0013\t}\u0002A1A\u0005\u0004\t\u0005\u0003\"\u0003B#\u0001\t\u0007I1\u0001B$\u0011%\u0011Y\u0005\u0001b\u0001\n\u0007\u0011i\u0005C\u0005\u0003R\u0001\u0011\r\u0011b\u0001\u0003T!I!Q\f\u0001C\u0002\u0013\r!q\f\u0005\n\u0005G\u0002!\u0019!C\u0002\u0005KB\u0011B!\u001b\u0001\u0005\u0004%\u0019Aa\u001b\t\u0013\t=\u0004A1A\u0005\u0004\tE\u0004\"\u0003B>\u0001\t\u0007I1\u0001B?\u0011%\u0011\t\t\u0001b\u0001\n\u0007\u0011\u0019\tC\u0005\u0003\b\u0002\u0011\r\u0011b\u0001\u0003\n\"I!Q\u0012\u0001C\u0002\u0013\r!q\u0012\u0005\n\u00053\u0003!\u0019!C\u0002\u00057C\u0011Ba(\u0001\u0005\u0004%\u0019A!)\t\u0013\t\u0015\u0006A1A\u0005\u0004\t\u001d\u0006\"\u0003BV\u0001\t\u0007I1\u0001BW\u0011%\u00119\f\u0001b\u0001\n\u0007\u0011I\fC\u0005\u0003>\u0002\u0011\r\u0011b\u0001\u0003@\"I!1\u0019\u0001C\u0002\u0013\r!Q\u0019\u0005\n\u0005\u0013\u0004!\u0019!C\u0002\u0005\u0017D\u0011Ba8\u0001\u0005\u0004%\u0019A!9\t\u0013\t\u001d\bA1A\u0005\u0004\t%\b\"\u0003Bx\u0001\t\u0007I1\u0001By\u0011%\u00119\u0010\u0001b\u0001\n\u0007\u0011I\u0010C\u0005\u0003~\u0002\u0011\r\u0011b\u0001\u0003\u0000\"I11\u0001\u0001C\u0002\u0013\r1Q\u0001\u0005\n\u0007\u0013\u0001!\u0019!C\u0002\u0007\u0017A\u0011ba\u0004\u0001\u0005\u0004%\u0019a!\u0005\t\u0013\rm\u0001A1A\u0005\u0004\ru\u0001\"CB\u0011\u0001\t\u0007I1AB\u0012\u0011%\u00199\u0003\u0001b\u0001\n\u0007\u0019I\u0003C\u0005\u0004.\u0001\u0011\r\u0011b\u0001\u00040!I11\u0007\u0001C\u0002\u0013\r1Q\u0007\u0005\n\u0007s\u0001!\u0019!C\u0002\u0007wA\u0011ba\u0010\u0001\u0005\u0004%\u0019a!\u0011\t\u0013\r\u0015\u0003A1A\u0005\u0004\r\u001d\u0003\"CB&\u0001\t\u0007I1AB'\u0011%\u0019\t\u0006\u0001b\u0001\n\u0007\u0019\u0019\u0006C\u0005\u0004X\u0001\u0011\r\u0011b\u0001\u0004Z!I1Q\f\u0001C\u0002\u0013\r1q\f\u0005\n\u0007G\u0002!\u0019!C\u0002\u0007KB\u0011b!\u001b\u0001\u0005\u0004%\u0019aa\u001b\t\u0013\r=\u0004A1A\u0005\u0004\rE\u0004\"CB;\u0001\t\u0007I1AB<\u0011%\u0019Y\b\u0001b\u0001\n\u0007\u0019i\bC\u0005\u0004\u0002\u0002\u0011\r\u0011b\u0001\u0004\u0004\"I1q\u0011\u0001C\u0002\u0013\r1\u0011\u0012\u0005\b\u0007\u001b\u0003A1ABH\u0011\u001d\u0019\u0019\f\u0001C\u0002\u0007kC\u0011ba4\u0001\u0005\u0004%\u0019a!5\t\u0013\rm\u0007A1A\u0005\u0004\ru\u0007\"CBq\u0001\t\u0007I1ABr\u0011%\u00199\u000f\u0001b\u0001\n\u0007\u0019I\u000fC\u0005\u0004n\u0002\u0011\r\u0011b\u0001\u0004p\"I11\u001f\u0001C\u0002\u0013\r1Q\u001f\u0005\n\u0007s\u0004!\u0019!C\u0002\u0007wD\u0011ba@\u0001\u0005\u0004%\u0019\u0001\"\u0001\t\u0013\u0011\u0015\u0001A1A\u0005\u0004\u0011\u001d\u0001\"\u0003C\u0006\u0001\t\u0007I1\u0001C\u0007\u0011%!\t\u0002\u0001b\u0001\n\u0007!\u0019\u0002C\u0005\u0005\u0018\u0001\u0011\r\u0011b\u0001\u0005\u001a!IAQ\u0004\u0001C\u0002\u0013\rAq\u0004\u0005\n\tG\u0001!\u0019!C\u0002\tKA\u0011\u0002\"\u000b\u0001\u0005\u0004%\u0019\u0001b\u000b\t\u0013\u0011=\u0002A1A\u0005\u0004\u0011E\u0002\"\u0003C\u001b\u0001\t\u0007I1\u0001C\u001c\u0011%!)\u0005\u0001b\u0001\n\u0007!9\u0005C\u0005\u0005N\u0001\u0011\r\u0011b\u0001\u0005P!IAQ\u000b\u0001C\u0002\u0013\rAq\u000b\u0005\n\t;\u0002!\u0019!C\u0002\t?B\u0011\u0002\"\u001b\u0001\u0005\u0004%\u0019\u0001b\u001b\t\u0013\u0011E\u0004A1A\u0005\u0004\u0011M\u0004\"\u0003C=\u0001\t\u0007I1\u0001C>\u0011%!\t\t\u0001b\u0001\n\u0007!\u0019\tC\u0005\u0005\n\u0002\u0011\r\u0011b\u0001\u0005\f\"IA\u0011\u0013\u0001C\u0002\u0013\rA1\u0013\u0005\n\t3\u0003!\u0019!C\u0002\t7C\u0011\u0002\")\u0001\u0005\u0004%\u0019\u0001b)\t\u0013\u0011m\u0006A1A\u0005\u0004\u0011u\u0006\"\u0003Ca\u0001\t\u0007I1\u0001Cb\u0011%!9\r\u0001b\u0001\n\u0007!I\rC\u0005\u0005N\u0002\u0011\r\u0011b\u0001\u0005P\"IA1\u001b\u0001C\u0002\u0013\rAQ\u001b\u0005\n\t3\u0004!\u0019!C\u0002\t7D\u0011\u0002b8\u0001\u0005\u0004%\u0019\u0001\"9\t\u0013\u0011\u0015\bA1A\u0005\u0004\u0011\u001d\b\"\u0003Cv\u0001\t\u0007I1\u0001Cw\u0011%!\t\u0010\u0001b\u0001\n\u0007!\u0019\u0010C\u0005\u0005x\u0002\u0011\r\u0011b\u0001\u0005z\"IAQ \u0001C\u0002\u0013\rAq \u0005\n\u000b\u0007\u0001!\u0019!C\u0002\u000b\u000bA\u0011\"\"\u0003\u0001\u0005\u0004%\u0019!b\u0003\t\u0013\u0015=\u0001A1A\u0005\u0004\u0015E\u0001\"CC\u000b\u0001\t\u0007I1AC\f\u0011%)Y\u0002\u0001b\u0001\n\u0007)i\u0002C\u0005\u0006\"\u0001\u0011\r\u0011b\u0001\u0006$!IQq\u0005\u0001C\u0002\u0013\rQ\u0011\u0006\u0005\n\u000b[\u0001!\u0019!C\u0002\u000b_A\u0011\"b\r\u0001\u0005\u0004%\u0019!\"\u000e\t\u0013\u0015e\u0002A1A\u0005\u0004\u0015m\u0002\"CC \u0001\t\u0007I1AC!\u0011%))\u0005\u0001b\u0001\n\u0007)9\u0005C\u0005\u0006L\u0001\u0011\r\u0011b\u0001\u0006N!IQ\u0011\u000b\u0001C\u0002\u0013\rQ1\u000b\u0005\n\u000b/\u0002!\u0019!C\u0002\u000b3B\u0011\"\"\u0018\u0001\u0005\u0004%\u0019!b\u0018\t\u0013\u0015\r\u0004A1A\u0005\u0004\u0015\u0015\u0004\"CC5\u0001\t\u0007I1AC6\u0011%)y\u0007\u0001b\u0001\n\u0007)\t\bC\u0005\u0006v\u0001\u0011\r\u0011b\u0001\u0006x!IQ1\u0010\u0001C\u0002\u0013\rQQ\u0010\u0005\n\u000b\u0003\u0003!\u0019!C\u0002\u000b\u0007C\u0011\"b\"\u0001\u0005\u0004%\u0019!\"#\t\u0013\u00155\u0005A1A\u0005\u0004\u0015=\u0005\"CCJ\u0001\t\u0007I1ACK\u0011%)I\n\u0001b\u0001\n\u0007)Y\nC\u0005\u0006 \u0002\u0011\r\u0011b\u0001\u0006\"\"IQQ\u0015\u0001C\u0002\u0013\rQq\u0015\u0005\n\u000bW\u0003!\u0019!C\u0002\u000b[C\u0011\"\"-\u0001\u0005\u0004%\u0019!b-\t\u0013\u0015]\u0006A1A\u0005\u0004\u0015e\u0006\"CC_\u0001\t\u0007I1AC`\u0011%)\u0019\r\u0001b\u0001\n\u0007))\rC\u0005\u0006J\u0002\u0011\r\u0011b\u0001\u0006L\"IQq\u001a\u0001C\u0002\u0013\rQ\u0011\u001b\u0005\n\u000b+\u0004!\u0019!C\u0002\u000b/D\u0011\"b7\u0001\u0005\u0004%\u0019!\"8\t\u0013\u0015\u0005\bA1A\u0005\u0004\u0015\r\b\"CCt\u0001\t\u0007I1ACu\u0011%)i\u000f\u0001b\u0001\n\u0007)y\u000fC\u0005\u0006t\u0002\u0011\r\u0011b\u0001\u0006v\"IQ\u0011 \u0001C\u0002\u0013\rQ1 \u0005\n\u000b\u007f\u0004!\u0019!C\u0002\r\u0003A\u0011B\"\u0002\u0001\u0005\u0004%\u0019Ab\u0002\t\u0013\u0019-\u0001A1A\u0005\u0004\u00195\u0001\"\u0003D\t\u0001\t\u0007I1\u0001D\n\u0011%19\u0002\u0001b\u0001\n\u00071I\u0002C\u0005\u0007\u001e\u0001\u0011\r\u0011b\u0001\u0007 !Ia1\u0005\u0001C\u0002\u0013\raQ\u0005\u0005\n\rS\u0001!\u0019!C\u0002\rWA\u0011Bb\f\u0001\u0005\u0004%\u0019A\"\r\t\u0013\u0019U\u0002A1A\u0005\u0004\u0019]\u0002\"\u0003D\u001e\u0001\t\u0007I1\u0001D\u001f\u0011%1\t\u0005\u0001b\u0001\n\u00071\u0019\u0005C\u0005\u0007H\u0001\u0011\r\u0011b\u0001\u0007J\t!2iU\"NCR\u0014\u0018\u000e_#ya\u0006tG-\u001a3PaNTA!!\u000f\u0002<\u0005Iq\u000e]3sCR|'o\u001d\u0006\u0005\u0003{\ty$\u0001\u0004mS:\fGn\u001a\u0006\u0003\u0003\u0003\naA\u0019:fKj,7\u0001A\n\b\u0001\u0005\u001d\u00131KA.!\u0011\tI%a\u0014\u000e\u0005\u0005-#BAA'\u0003\u0015\u00198-\u00197b\u0013\u0011\t\t&a\u0013\u0003\r\u0005s\u0017PU3g!\u0011\t)&a\u0016\u000e\u0005\u0005]\u0012\u0002BA-\u0003o\u0011\u0011\"T1ue&Dx\n]:\u0011\t\u0005U\u0013QL\u0005\u0005\u0003?\n9DA\tD'\u000ek\u0015\r\u001e:jq>\u00038o\u0018*j]\u001e\fa\u0001J5oSR$CCAA3!\u0011\tI%a\u001a\n\t\u0005%\u00141\n\u0002\u0005+:LG/\u0001\u000bdC:lU\u000f\\0T-~\u001b5kQ0fc~\u001b5kQ\u000b\u0005\u0003_\n\t\n\u0006\u0004\u0002r\u0005%\u0016q\u0016\t\u000b\u0003g\nI(!\"\u0002$\u0006\rf\u0002BA+\u0003kJA!a\u001e\u00028\u0005Yq\n]'vY6\u000bGO]5y\u0013\u0011\tY(! \u0003\u000b%k\u0007\u000f\u001c\u001a\n\t\u0005}\u0014\u0011\u0011\u0002\u0006+\u001a+hn\u0019\u0006\u0005\u0003\u0007\u000by$A\u0004hK:,'/[2\u0011\r\u0005\u001d\u0015\u0011RAG\u001b\t\tY$\u0003\u0003\u0002\f\u0006m\"\u0001D*qCJ\u001cXMV3di>\u0014\b\u0003BAH\u0003#c\u0001\u0001B\u0004\u0002\u0014\n\u0011\r!!&\u0003\u0003Q\u000bB!a&\u0002\u001eB!\u0011\u0011JAM\u0013\u0011\tY*a\u0013\u0003\u000f9{G\u000f[5oOB!\u0011\u0011JAP\u0013\u0011\t\t+a\u0013\u0003\u0007\u0005s\u0017\u0010\u0005\u0004\u0002\b\u0006\u0015\u0016QR\u0005\u0005\u0003O\u000bYDA\u0005D'\u000ek\u0015\r\u001e:jq\"9\u00111\u0016\u0002A\u0004\u00055\u0016AA8q!)\t\u0019(!\u001f\u0002$\u0006\r\u00161\u0015\u0005\b\u0003c\u0013\u00019AAZ\u0003\u0011QXM]8\u0011\r\u0005U\u00161XAG\u001b\t\t9L\u0003\u0003\u0002:\u0006}\u0012aB:u_J\fw-Z\u0005\u0005\u0003{\u000b9L\u0001\u0003[KJ|\u0017!F2b]6+HnX*Wi~\u001b5kQ0fc~\u001bf\u000b^\u000b\u0005\u0003\u0007\f\t\u000e\u0006\u0005\u0002F\u0006U\u0017\u0011\\Ao!)\t\u0019(!\u001f\u0002H\u0006M\u0017q\u0019\t\u0007\u0003\u000f\u000bI-!4\n\t\u0005-\u00171\b\u0002\n)J\fgn\u001d9pg\u0016\u0004b!a\"\u0002\n\u0006=\u0007\u0003BAH\u0003#$q!a%\u0004\u0005\u0004\t)\n\u0005\u0004\u0002\b\u0006\u0015\u0016q\u001a\u0005\b\u0003W\u001b\u00019AAl!)\t\u0019(!\u001f\u0002T\u0006M\u00171\u001b\u0005\b\u0003c\u001b\u00019AAn!\u0019\t),a/\u0002P\"9\u0011q\\\u0002A\u0004\u0005\u0005\u0018AA2u!\u0019\t\u0019/!;\u0002P6\u0011\u0011Q\u001d\u0006\u0005\u0003O\fY%A\u0004sK\u001adWm\u0019;\n\t\u0005-\u0018Q\u001d\u0002\t\u00072\f7o\u001d+bO\u0006i1m]2`\u001fBtUmZ0J]R,\"!!=\u0011\u0011\u0005M\u0018\u0011`A\u007f\u0003{tA!!\u0016\u0002v&!\u0011q_A\u001c\u0003\u0015y\u0005OT3h\u0013\u0011\tY0! \u0003\t%k\u0007\u000f\u001c\t\u0007\u0003\u000f\u000b)+a@\u0011\t\u0005%#\u0011A\u0005\u0005\u0005\u0007\tYEA\u0002J]R\f\u0001cY:d?>\u0003h*Z4`\t>,(\r\\3\u0016\u0005\t%\u0001\u0003CAz\u0003s\u0014YAa\u0003\u0011\r\u0005\u001d\u0015Q\u0015B\u0007!\u0011\tIEa\u0004\n\t\tE\u00111\n\u0002\u0007\t>,(\r\\3\u0002\u001f\r\u001c8mX(q\u001d\u0016<wL\u00127pCR,\"Aa\u0006\u0011\u0011\u0005M\u0018\u0011 B\r\u00053\u0001b!a\"\u0002&\nm\u0001\u0003BA%\u0005;IAAa\b\u0002L\t)a\t\\8bi\u0006q1m]2`\u001fBtUmZ0M_:<WC\u0001B\u0013!!\t\u00190!?\u0003(\t\u001d\u0002CBAD\u0003K\u0013I\u0003\u0005\u0003\u0002J\t-\u0012\u0002\u0002B\u0017\u0003\u0017\u0012A\u0001T8oO\u0006y1m]2TG\u0006dW-\u00113e?&sG/\u0006\u0002\u00034AQ!Q\u0007B\u001e\u0003{\fy0!@\u000f\t\u0005\u001d%qG\u0005\u0005\u0005s\tY$\u0001\u0005tG\u0006dW-\u00113e\u0013\u0011\u0011i$! \u0003\u0019%s\u0007\u000b\\1dK&k\u0007\u000f\\\u001a\u0002%\r\u001c8mU2bY\u0016\fE\rZ0E_V\u0014G.Z\u000b\u0003\u0005\u0007\u0002\"B!\u000e\u0003<\t-!Q\u0002B\u0006\u0003E\u00197oY*dC2,\u0017\t\u001a3`\r2|\u0017\r^\u000b\u0003\u0005\u0013\u0002\"B!\u000e\u0003<\te!1\u0004B\r\u0003A\u00197oY*dC2,\u0017\t\u001a3`\u0019>tw-\u0006\u0002\u0003PAQ!Q\u0007B\u001e\u0005O\u0011ICa\n\u00021\r\u001c8mX2tG~\u0013\u0015\rZ(qg~Ke\u000e^0PaB{w/\u0006\u0002\u0003VAQ!qKA=\u0003{\fi0!@\u000f\t\u0005U#\u0011L\u0005\u0005\u00057\n9$A\u0003PaB{w/A\u000edg\u000e|6m]2`\u0005\u0006$w\n]:`\t>,(\r\\3`\u001fB\u0004vn^\u000b\u0003\u0005C\u0002\"Ba\u0016\u0002z\t-!1\u0002B\u0006\u0003i\u00197oY0dg\u000e|&)\u00193PaN|f\t\\8bi~{\u0005\u000fU8x+\t\u00119\u0007\u0005\u0006\u0003X\u0005e$\u0011\u0004B\r\u00053\t\u0011dY:d?\u000e\u001c8m\u0018\"bI>\u00038o\u0018'p]\u001e|v\n\u001d)poV\u0011!Q\u000e\t\u000b\u0005/\nIHa\n\u0003(\t\u001d\u0012\u0001G2tG~\u001b7oY0CC\u0012|\u0005o]0J]R|v\n\u001d#jmV\u0011!1\u000f\t\u000b\u0005k\nI(!@\u0002~\u0006uh\u0002BA+\u0005oJAA!\u001f\u00028\u0005)q\n\u001d#jm\u0006Y2m]2`GN\u001cwLQ1e\u001fB\u001cx\fR8vE2,wl\u00149ESZ,\"Aa \u0011\u0015\tU\u0014\u0011\u0010B\u0006\u0005\u0017\u0011Y!\u0001\u000edg\u000e|6m]2`\u0005\u0006$w\n]:`\r2|\u0017\r^0Pa\u0012Kg/\u0006\u0002\u0003\u0006BQ!QOA=\u00053\u0011IB!\u0007\u00023\r\u001c8mX2tG~\u0013\u0015\rZ(qg~cuN\\4`\u001fB$\u0015N^\u000b\u0003\u0005\u0017\u0003\"B!\u001e\u0002z\t\u001d\"q\u0005B\u0014\u0003a\u00197oY0dg\u000e|&)\u00193PaN|\u0016J\u001c;`\u001fBlu\u000eZ\u000b\u0003\u0005#\u0003\"Ba%\u0002z\u0005u\u0018Q`A\u007f\u001d\u0011\t)F!&\n\t\t]\u0015qG\u0001\u0006\u001fBlu\u000eZ\u0001\u001cGN\u001cwlY:d?\n\u000bGm\u00149t?\u0012{WO\u00197f?>\u0003Xj\u001c3\u0016\u0005\tu\u0005C\u0003BJ\u0003s\u0012YAa\u0003\u0003\f\u0005Q2m]2`GN\u001cwLQ1e\u001fB\u001cxL\u00127pCR|v\n]'pIV\u0011!1\u0015\t\u000b\u0005'\u000bIH!\u0007\u0003\u001a\te\u0011!G2tG~\u001b7oY0CC\u0012|\u0005o]0M_:<wl\u00149N_\u0012,\"A!+\u0011\u0015\tM\u0015\u0011\u0010B\u0014\u0005O\u00119#A\tdg\u000e|6m]2`\u001fB\fE\rZ0J]R,\"Aa,\u0011\u0015\tE\u0016\u0011PA\u007f\u0003{\fiP\u0004\u0003\u0002V\tM\u0016\u0002\u0002B[\u0003o\tQa\u00149BI\u0012\fAcY:d?\u000e\u001c8mX(q\u0003\u0012$w\fR8vE2,WC\u0001B^!)\u0011\t,!\u001f\u0003\f\t-!1B\u0001\u0014GN\u001cwlY:d?>\u0003\u0018\t\u001a3`\r2|\u0017\r^\u000b\u0003\u0005\u0003\u0004\"B!-\u0002z\te!\u0011\u0004B\r\u0003I\u00197oY0dg\u000e|v\n]!eI~cuN\\4\u0016\u0005\t\u001d\u0007C\u0003BY\u0003s\u00129Ca\n\u0003(\u0005AB-\\0dg\u000e|\u0016J\u001c)mC\u000e,wl\u00149TKR|\u0016J\u001c;\u0016\u0005\t5\u0007\u0003\u0003Bh\u0005+\u0014I.!@\u000f\t\u0005U#\u0011[\u0005\u0005\u0005'\f9$A\u0003PaN+G/\u0003\u0003\u0003X\u0006u$\u0001D%o!2\f7-Z%na2\u0014\u0004CBAD\u00057\fy0\u0003\u0003\u0003^\u0006m\"a\u0003#f]N,W*\u0019;sSb\f1\u0004Z7`GN\u001cw,\u00138QY\u0006\u001cWmX(q'\u0016$x\fR8vE2,WC\u0001Br!!\u0011yM!6\u0003f\n-\u0001CBAD\u00057\u0014i!\u0001\u000ee[~\u001b7oY0J]Bc\u0017mY3`\u001fB\u001cV\r^0GY>\fG/\u0006\u0002\u0003lBA!q\u001aBk\u0005[\u0014I\u0002\u0005\u0004\u0002\b\nm'1D\u0001\u001aI6|6m]2`\u0013:\u0004F.Y2f?>\u00038+\u001a;`\u0019>tw-\u0006\u0002\u0003tBA!q\u001aBk\u0005k\u00149\u0003\u0005\u0004\u0002\b\nm'\u0011F\u0001\u0019I6|6m]2`\u0013:\u0004F.Y2f?>\u0003\u0018\t\u001a3`\u0013:$XC\u0001B~!!\u0011\tL!6\u0003Z\u0006u\u0018a\u00073n?\u000e\u001c8mX%o!2\f7-Z0Pa\u0006#Gm\u0018#pk\ndW-\u0006\u0002\u0004\u0002AA!\u0011\u0017Bk\u0005K\u0014Y!\u0001\u000ee[~\u001b7oY0J]Bc\u0017mY3`\u001fB\fE\rZ0GY>\fG/\u0006\u0002\u0004\bAA!\u0011\u0017Bk\u0005[\u0014I\"A\re[~\u001b7oY0J]Bc\u0017mY3`\u001fB\fE\rZ0M_:<WCAB\u0007!!\u0011\tL!6\u0003v\n\u001d\u0012\u0001\u00073n?\u000e\u001c8mX%o!2\f7-Z0PaN+(mX%oiV\u001111\u0003\t\t\u0007+\u0011)N!7\u0002~:!\u0011QKB\f\u0013\u0011\u0019I\"a\u000e\u0002\u000b=\u00038+\u001e2\u00027\u0011lwlY:d?&s\u0007\u000b\\1dK~{\u0005oU;c?\u0012{WO\u00197f+\t\u0019y\u0002\u0005\u0005\u0004\u0016\tU'Q\u001dB\u0006\u0003i!WnX2tG~Ke\u000e\u00157bG\u0016|v\n]*vE~3En\\1u+\t\u0019)\u0003\u0005\u0005\u0004\u0016\tU'Q\u001eB\r\u0003e!WnX2tG~Ke\u000e\u00157bG\u0016|v\n]*vE~cuN\\4\u0016\u0005\r-\u0002\u0003CB\u000b\u0005+\u0014)Pa\n\u0002!\r\u001c8m\u00183n?>\u0003\u0018\t\u001a3`\u0013:$XCAB\u0019!)\u0011\t,!\u001f\u0002~\ne'\u0011\\\u0001\u0014GN\u001cw\fZ7`\u001fB\fE\rZ0E_V\u0014G.Z\u000b\u0003\u0007o\u0001\"B!-\u0002z\t-!Q\u001dBs\u0003I\u00197oY0e[~{\u0005/\u00113e?\u001acw.\u0019;\u0016\u0005\ru\u0002C\u0003BY\u0003s\u0012IB!<\u0003n\u0006\t2m]2`I6|v\n]!eI~cuN\\4\u0016\u0005\r\r\u0003C\u0003BY\u0003s\u00129C!>\u0003v\u0006\u0001B-\\0dg\u000e|v\n]!eI~Ke\u000e^\u000b\u0003\u0007\u0013\u0002\"B!-\u0002z\te\u0017Q Bm\u0003M!WnX2tG~{\u0005/\u00113e?\u0012{WO\u00197f+\t\u0019y\u0005\u0005\u0006\u00032\u0006e$Q\u001dB\u0006\u0005K\f!\u0003Z7`GN\u001cwl\u00149BI\u0012|f\t\\8biV\u00111Q\u000b\t\u000b\u0005c\u000bIH!<\u0003\u001a\t5\u0018!\u00053n?\u000e\u001c8mX(q\u0003\u0012$w\fT8oOV\u001111\f\t\u000b\u0005c\u000bIH!>\u0003(\tU\u0018\u0001\u00053n?\u000e\u001c8mX(q'V\u0014w,\u00138u+\t\u0019\t\u0007\u0005\u0006\u0004\u0016\u0005e$\u0011\\A\u007f\u00053\f1\u0003Z7`GN\u001cwl\u00149Tk\n|Fi\\;cY\u0016,\"aa\u001a\u0011\u0015\rU\u0011\u0011\u0010Bs\u0005\u0017\u0011)/\u0001\ne[~\u001b7oY0PaN+(m\u0018$m_\u0006$XCAB7!)\u0019)\"!\u001f\u0003n\ne!Q^\u0001\u0012I6|6m]2`\u001fB\u001cVOY0M_:<WCAB:!)\u0019)\"!\u001f\u0003v\n\u001d\"Q_\u0001\u0011GN\u001cw\fZ7`\u001fB\u001cVOY0J]R,\"a!\u001f\u0011\u0015\rU\u0011\u0011PA\u007f\u00053\u0014I.A\ndg\u000e|F-\\0PaN+(m\u0018#pk\ndW-\u0006\u0002\u0004\u0000AQ1QCA=\u0005\u0017\u0011)O!:\u0002%\r\u001c8m\u00183n?>\u00038+\u001e2`\r2|\u0017\r^\u000b\u0003\u0007\u000b\u0003\"b!\u0006\u0002z\te!Q\u001eBw\u0003E\u00197oY0e[~{\u0005oU;c?2{gnZ\u000b\u0003\u0007\u0017\u0003\"b!\u0006\u0002z\t\u001d\"Q\u001fB{\u0003E!WnX2tG~{\u0005/\u00113e?N+W.[\u000b\u0005\u0007#\u001bI\n\u0006\u0004\u0004\u0014\u000eu5Q\u0016\t\u000b\u0005c\u000bIh!&\u0004\u001c\u000eU\u0005CBAD\u00057\u001c9\n\u0005\u0003\u0002\u0010\u000eeEaBAJq\t\u0007\u0011Q\u0013\t\u0007\u0003\u000f\u000b)ka&\t\u0013\r}\u0005(!AA\u0004\r\u0005\u0016aC3wS\u0012,gnY3%cA\u0002baa)\u0004*\u000e]UBABS\u0015\u0011\u00199+a\u0010\u0002\t5\fG\u000f[\u0005\u0005\u0007W\u001b)K\u0001\u0005TK6L'/\u001b8h\u0011%\u0019y\u000bOA\u0001\u0002\b\u0019\t,A\u0006fm&$WM\\2fIE\n\u0004CBAr\u0003S\u001c9*A\u0006dg\u000e|F-\\0TK6LW\u0003BB\\\u0007\u007f#ba!/\u0004D\u000e%\u0007C\u0003BY\u0003s\u001aYl!1\u0004BB1\u0011qQAS\u0007{\u0003B!a$\u0004@\u00129\u00111S\u001dC\u0002\u0005U\u0005CBAD\u00057\u001ci\fC\u0005\u0004Ff\n\t\u0011q\u0001\u0004H\u0006YQM^5eK:\u001cW\rJ\u00193!\u0019\u0019\u0019k!+\u0004>\"I11Z\u001d\u0002\u0002\u0003\u000f1QZ\u0001\fKZLG-\u001a8dK\u0012\n4\u0007\u0005\u0004\u0002d\u0006%8QX\u0001\u0018GN\u001cwlY:d?>\u0003X*\u001e7TG\u0006d\u0017M]0J]R,\"aa5\u0011\u0015\rU\u0017\u0011PA\u007f\u0003{\fiP\u0004\u0003\u0002V\r]\u0017\u0002BBm\u0003o\t1b\u00149Nk2\u001c6-\u00197be\u0006Q2m]2`GN\u001cwl\u00149Nk2\u001c6-\u00197be~#u.\u001e2mKV\u00111q\u001c\t\u000b\u0007+\fIHa\u0003\u0003\f\t-\u0011!G2tG~\u001b7oY0Pa6+HnU2bY\u0006\u0014xL\u00127pCR,\"a!:\u0011\u0015\rU\u0017\u0011\u0010B\r\u00053\u0011I\"\u0001\rdg\u000e|6m]2`\u001fBlU\u000f\\*dC2\f'o\u0018'p]\u001e,\"aa;\u0011\u0015\rU\u0017\u0011\u0010B\u0014\u0005O\u00119#A\tdg\u000e|6m]2`\u001fB\u001cVOY0J]R,\"a!=\u0011\u0015\rU\u0011\u0011PA\u007f\u0003{\fi0\u0001\u000bdg\u000e|6m]2`\u001fB\u001cVOY0E_V\u0014G.Z\u000b\u0003\u0007o\u0004\"b!\u0006\u0002z\t-!1\u0002B\u0006\u0003M\u00197oY0dg\u000e|v\n]*vE~3En\\1u+\t\u0019i\u0010\u0005\u0006\u0004\u0016\u0005e$\u0011\u0004B\r\u00053\t!cY:d?\u000e\u001c8mX(q'V\u0014w\fT8oOV\u0011A1\u0001\t\u000b\u0007+\tIHa\n\u0003(\t\u001d\u0012AJ5na2|v\n]0D'\u000e#v\fV0fc~\u001b5k\u0011+`\u0013:$xl\u00149Nk2\u001c6-\u00197beV\u0011A\u0011\u0002\t\u000b\u0007+\fI(!@\u0002\u0000\u0006u\u0018!K5na2|v\n]0D'\u000e#v\fV0fc~\u001b5k\u0011+`\t>,(\r\\3`\u001fBlU\u000f\\*dC2\f'/\u0006\u0002\u0005\u0010AQ1Q[A=\u0005\u0017\u0011iAa\u0003\u0002Q%l\u0007\u000f\\0Pa~\u001b5k\u0011+`)~+\u0017oX\"T\u0007R{f\t\\8bi~{\u0005/T;m'\u000e\fG.\u0019:\u0016\u0005\u0011U\u0001CCBk\u0003s\u0012IBa\u0007\u0003\u001a\u00059\u0013.\u001c9m?>\u0003xlQ*D)~#v,Z9`\u0007N\u001bEk\u0018'p]\u001e|v\n]'vYN\u001b\u0017\r\\1s+\t!Y\u0002\u0005\u0006\u0004V\u0006e$q\u0005B\u0015\u0005O\ta%[7qY~{\u0005oX\"T\u0007R{FkX3r?\u000e\u001b6\tV0J]R|v\n]'vY6\u000bGO]5y+\t!\t\u0003\u0005\u0006\u0002t\u0005e\u0014Q`A\u0000\u0003{\f\u0011&[7qY~{\u0005oX\"T\u0007R{FkX3r?\u000e\u001b6\tV0E_V\u0014G.Z0Pa6+H.T1ue&DXC\u0001C\u0014!)\t\u0019(!\u001f\u0003\f\t5!1B\u0001)S6\u0004HnX(q?\u000e\u001b6\tV0U?\u0016\fxlQ*D)~3En\\1u?>\u0003X*\u001e7NCR\u0014\u0018\u000e_\u000b\u0003\t[\u0001\"\"a\u001d\u0002z\te!1\u0004B\r\u0003\u001dJW\u000e\u001d7`\u001fB|6iU\"U?R{V-]0D'\u000e#v\fT8oO~{\u0005/T;m\u001b\u0006$(/\u001b=\u0016\u0005\u0011M\u0002CCA:\u0003s\u00129C!\u000b\u0003(\u0005i1-\u00198Nk2luLV0J]R,\"\u0001\"\u000f\u0011\u0019\u0005UC1HA\u007f\t\u007f\t\u0019\bb\u0010\n\t\u0011u\u0012q\u0007\u0002\u000f\u0005&t\u0017M]=SK\u001eL7\u000f\u001e:z!\u0019\t9\t\"\u0011\u0002\u0000&!A1IA\u001e\u0005\u00191Vm\u0019;pe\u0006y1-\u00198Nk2luLV0GY>\fG/\u0006\u0002\u0005JAa\u0011Q\u000bC\u001e\u00053!Y%a\u001d\u0005LA1\u0011q\u0011C!\u00057\t\u0001cY1o\u001bVdWj\u0018,`\t>,(\r\\3\u0016\u0005\u0011E\u0003\u0003DA+\tw\u0011Y\u0001b\u0015\u0002t\u0011M\u0003CBAD\t\u0003\u0012i!\u0001\bdC:lU\u000f\\'`-~cuN\\4\u0016\u0005\u0011e\u0003\u0003DA+\tw\u00119\u0003b\u0017\u0002t\u0011m\u0003CBAD\t\u0003\u0012I#\u0001\bdC:lU\u000f\\'`\tZ{\u0016J\u001c;\u0016\u0005\u0011\u0005\u0004CCA:\u0003s\ni\u0010b\u0019\u0005dA1\u0011q\u0011C3\u0003\u007fLA\u0001b\u001a\u0002<\tYA)\u001a8tKZ+7\r^8s\u0003A\u0019\u0017M\\'vY6{FIV0GY>\fG/\u0006\u0002\u0005nAQ\u00111OA=\u00053!y\u0007b\u001c\u0011\r\u0005\u001dEQ\rB\u000e\u0003E\u0019\u0017M\\'vY6{FIV0E_V\u0014G.Z\u000b\u0003\tk\u0002\"\"a\u001d\u0002z\t-Aq\u000fC<!\u0019\t9\t\"\u001a\u0003\u000e\u0005y1-\u00198Nk2lu\f\u0012,`\u0019>tw-\u0006\u0002\u0005~AQ\u00111OA=\u0005O!y\bb \u0011\r\u0005\u001dEQ\rB\u0015\u00039\u0019\u0017M\\'vY6{6KV0J]R,\"\u0001\"\"\u0011\u0019\u0005UC1HA\u007f\t\u000f\u000b\u0019\bb\"\u0011\r\u0005\u001d\u0015\u0011RA\u0000\u0003A\u0019\u0017M\\'vY6{6KV0GY>\fG/\u0006\u0002\u0005\u000eBa\u0011Q\u000bC\u001e\u00053!y)a\u001d\u0005\u0010B1\u0011qQAE\u00057\t\u0011cY1o\u001bVdWjX*W?\u0012{WO\u00197f+\t!)\n\u0005\u0007\u0002V\u0011m\"1\u0002CL\u0003g\"9\n\u0005\u0004\u0002\b\u0006%%QB\u0001\u0010G\u0006tW*\u001e7N?N3v\fT8oOV\u0011AQ\u0014\t\r\u0003+\"YDa\n\u0005 \u0006MDq\u0014\t\u0007\u0003\u000f\u000bII!\u000b\u0002\u001d\r\fg.T;m\u001b~#UjX%oiV\u0011AQ\u0015\t\u000b\tO\u000bI(!@\u0003Z\neg\u0002\u0002CU\u0003krA\u0001b+\u0005::!AQ\u0016C\\\u001d\u0011!y\u000b\".\u000e\u0005\u0011E&\u0002\u0002CZ\u0003\u0007\na\u0001\u0010:p_Rt\u0014BAA!\u0013\u0011\ti$a\u0010\n\t\u0005e\u00121H\u0001\u0011G\u0006tW*\u001e7N?\u0012kuL\u00127pCR,\"\u0001b0\u0011\u0015\u0011\u001d\u0016\u0011\u0010B\r\u0005[\u0014i/A\tdC:lU\u000f\\'`\t6{Fi\\;cY\u0016,\"\u0001\"2\u0011\u0015\u0011\u001d\u0016\u0011\u0010B\u0006\u0005K\u0014)/A\bdC:lU\u000f\\'`\t6{Fj\u001c8h+\t!Y\r\u0005\u0006\u0005(\u0006e$q\u0005B{\u0005k\fabY1o\u001bVdG)T0N?&sG/\u0006\u0002\u0005RBQAqUA=\u00053\fiP!7\u0002!\r\fg.T;m\t6{Vj\u0018$m_\u0006$XC\u0001Cl!)!9+!\u001f\u0003n\ne!Q^\u0001\u0012G\u0006tW*\u001e7E\u001b~ku\fR8vE2,WC\u0001Co!)!9+!\u001f\u0003f\n-!Q]\u0001\u0010G\u0006tW*\u001e7E\u001b~ku\fT8oOV\u0011A1\u001d\t\u000b\tO\u000bIH!>\u0003(\tU\u0018!D2b]6+H.T0N?&sG/\u0006\u0002\u0005jBQAqUA=\u0003{\fi0!@\u0002\u001f\r\fg.T;m\u001b~kuL\u00127pCR,\"\u0001b<\u0011\u0015\u0011\u001d\u0016\u0011\u0010B\r\u00053\u0011I\"\u0001\tdC:lU\u000f\\'`\u001b~#u.\u001e2mKV\u0011AQ\u001f\t\u000b\tO\u000bIHa\u0003\u0003\f\t-\u0011AD2b]6+H.T0N?2{gnZ\u000b\u0003\tw\u0004\"\u0002b*\u0002z\t\u001d\"q\u0005B\u0014\u0003\rJW\u000e\u001d7`\u001fB|6iU\"`)~+\u0017oX\"T\u0007~c\u0017N\u001a;`\u0013:$xl\u00149BI\u0012,\"!\"\u0001\u0011\u0011\tE&Q[A\u007f\u0003\u007f\fQ%[7qY~{\u0005oX\"T\u0007~#v,Z9`\u0007N\u001bu\f\\5gi~3En\\1u?>\u0003\u0018\t\u001a3\u0016\u0005\u0015\u001d\u0001\u0003\u0003BY\u0005+\u0014IBa\u0007\u0002M%l\u0007\u000f\\0Pa~\u001b5kQ0U?\u0016\fxlQ*D?2Lg\r^0E_V\u0014G.Z0Pa\u0006#G-\u0006\u0002\u0006\u000eAA!\u0011\u0017Bk\u0005\u0017\u0011i!\u0001\u0013j[Bdwl\u00149`\u0007N\u001bu\fV0fc~\u001b5kQ0mS\u001a$x\fT8oO~{\u0005/\u00113e+\t)\u0019\u0002\u0005\u0005\u00032\nU'q\u0005B\u0015\u0003\rJW\u000e\u001d7`\u001fB|6iU\"`)~+\u0017oX\"T\u0007~c\u0017N\u001a;`\u0013:$xl\u00149Tk\n,\"!\"\u0007\u0011\u0011\rU!Q[A\u007f\u0003\u007f\fQ%[7qY~{\u0005oX\"T\u0007~#v,Z9`\u0007N\u001bu\f\\5gi~3En\\1u?>\u00038+\u001e2\u0016\u0005\u0015}\u0001\u0003CB\u000b\u0005+\u0014IBa\u0007\u0002M%l\u0007\u000f\\0Pa~\u001b5kQ0U?\u0016\fxlQ*D?2Lg\r^0E_V\u0014G.Z0PaN+(-\u0006\u0002\u0006&AA1Q\u0003Bk\u0005\u0017\u0011i!\u0001\u0013j[Bdwl\u00149`\u0007N\u001bu\fV0fc~\u001b5kQ0mS\u001a$x\fT8oO~{\u0005oU;c+\t)Y\u0003\u0005\u0005\u0004\u0016\tU'q\u0005B\u0015\u0003\rJW\u000e\u001d7`\u001fB|6iU\"`)~+\u0017oX\"T\u0007~c\u0017N\u001a;`\u0013:$xl\u00149ESZ,\"!\"\r\u0011\u0011\tU$Q[A\u007f\u0003\u007f\fQ%[7qY~{\u0005oX\"T\u0007~#v,Z9`\u0007N\u001bu\f\\5gi~3En\\1u?>\u0003H)\u001b<\u0016\u0005\u0015]\u0002\u0003\u0003B;\u0005+\u0014IBa\u0007\u0002M%l\u0007\u000f\\0Pa~\u001b5kQ0U?\u0016\fxlQ*D?2Lg\r^0E_V\u0014G.Z0Pa\u0012Kg/\u0006\u0002\u0006>AA!Q\u000fBk\u0005\u0017\u0011i!\u0001\u0013j[Bdwl\u00149`\u0007N\u001bu\fV0fc~\u001b5kQ0mS\u001a$x\fT8oO~{\u0005\u000fR5w+\t)\u0019\u0005\u0005\u0005\u0003v\tU'q\u0005B\u0015\u0003\rJW\u000e\u001d7`\u001fB|6iU\"`)~+\u0017oX\"T\u0007~c\u0017N\u001a;`\u0013:$xl\u00149Q_^,\"!\"\u0013\u0011\u0011\t]#Q[A\u007f\u0003\u007f\fQ%[7qY~{\u0005oX\"T\u0007~#v,Z9`\u0007N\u001bu\f\\5gi~3En\\1u?>\u0003\bk\\<\u0016\u0005\u0015=\u0003\u0003\u0003B,\u0005+\u0014IBa\u0007\u0002M%l\u0007\u000f\\0Pa~\u001b5kQ0U?\u0016\fxlQ*D?2Lg\r^0E_V\u0014G.Z0PaB{w/\u0006\u0002\u0006VAA!q\u000bBk\u0005\u0017\u0011i!\u0001\u0013j[Bdwl\u00149`\u0007N\u001bu\fV0fc~\u001b5kQ0mS\u001a$x\fT8oO~{\u0005\u000fU8x+\t)Y\u0006\u0005\u0005\u0003X\tU'q\u0005B\u0015\u0003\rJW\u000e\u001d7`\u001fB|6iU\"`)~+\u0017oX\"T\u0007~c\u0017N\u001a;`\u0013:$xl\u00149N_\u0012,\"!\"\u0019\u0011\u0011\tM%Q[A\u007f\u0003\u007f\fQ%[7qY~{\u0005oX\"T\u0007~#v,Z9`\u0007N\u001bu\f\\5gi~3En\\1u?>\u0003Xj\u001c3\u0016\u0005\u0015\u001d\u0004\u0003\u0003BJ\u0005+\u0014IBa\u0007\u0002M%l\u0007\u000f\\0Pa~\u001b5kQ0U?\u0016\fxlQ*D?2Lg\r^0E_V\u0014G.Z0Pa6{G-\u0006\u0002\u0006nAA!1\u0013Bk\u0005\u0017\u0011i!\u0001\u0013j[Bdwl\u00149`\u0007N\u001bu\fV0fc~\u001b5kQ0mS\u001a$x\fT8oO~{\u0005/T8e+\t)\u0019\b\u0005\u0005\u0003\u0014\nU'q\u0005B\u0015\u0003%JW\u000e\u001d7`\u001fB|6iU\"`)~+\u0017oX\"T\u0007~c\u0017N\u001a;`\u0013:$xl\u00149Nk2\u001c6-\u00197beV\u0011Q\u0011\u0010\t\t\u0007+\u0014).!@\u0002\u0000\u0006Y\u0013.\u001c9m?>\u0003xlQ*D?R{V-]0D'\u000e{F.\u001b4u?\u001acw.\u0019;`\u001fBlU\u000f\\*dC2\f'/\u0006\u0002\u0006\u0000AA1Q\u001bBk\u00053\u0011Y\"\u0001\u0017j[Bdwl\u00149`\u0007N\u001bu\fV0fc~\u001b5kQ0mS\u001a$x\fR8vE2,wl\u00149Nk2\u001c6-\u00197beV\u0011QQ\u0011\t\t\u0007+\u0014)Na\u0003\u0003\u000e\u0005Q\u0013.\u001c9m?>\u0003xlQ*D?R{V-]0D'\u000e{F.\u001b4u?2{gnZ0Pa6+HnU2bY\u0006\u0014XCACF!!\u0019)N!6\u0003(\t%\u0012!K5na2|v\n]0D'\u000e{FkX3r?\u000e\u001b6i\u00187jMR|\u0016J\u001c;`\u001fBlU\u000f\\'biJL\u00070\u0006\u0002\u0006\u0012BA\u00111\u000fBk\u0003{\fy0A\u0016j[Bdwl\u00149`\u0007N\u001bu\fV0fc~\u001b5kQ0mS\u001a$xL\u00127pCR|v\n]'vY6\u000bGO]5y+\t)9\n\u0005\u0005\u0002t\tU'\u0011\u0004B\u000e\u00031JW\u000e\u001d7`\u001fB|6iU\"`)~+\u0017oX\"T\u0007~c\u0017N\u001a;`\t>,(\r\\3`\u001fBlU\u000f\\'biJL\u00070\u0006\u0002\u0006\u001eBA\u00111\u000fBk\u0005\u0017\u0011i!\u0001\u0016j[Bdwl\u00149`\u0007N\u001bu\fV0fc~\u001b5kQ0mS\u001a$x\fT8oO~{\u0005/T;m\u001b\u0006$(/\u001b=\u0016\u0005\u0015\r\u0006\u0003CA:\u0005+\u00149C!\u000b\u00023\r\u001c8mX2tG~Ke\u000e\u00157bG\u0016|\u0016J\u001c;`\u001fB\fE\rZ\u000b\u0003\u000bS\u0003\u0002B!-\u0003V\u0006u\u0018Q`\u0001\u001cGN\u001cwlY:d?&s\u0007\u000b\\1dK~3En\\1u?>\u0003\u0018\t\u001a3\u0016\u0005\u0015=\u0006\u0003\u0003BY\u0005+\u0014IB!\u0007\u00029\r\u001c8mX2tG~Ke\u000e\u00157bG\u0016|Fi\\;cY\u0016|v\n]!eIV\u0011QQ\u0017\t\t\u0005c\u0013)Na\u0003\u0003\f\u0005Q2m]2`GN\u001cw,\u00138QY\u0006\u001cWm\u0018'p]\u001e|v\n]!eIV\u0011Q1\u0018\t\t\u0005c\u0013)Na\n\u0003(\u0005I2m]2`GN\u001cw,\u00138QY\u0006\u001cWmX%oi~{\u0005oU;c+\t)\t\r\u0005\u0005\u0004\u0016\tU\u0017Q`A\u007f\u0003m\u00197oY0dg\u000e|\u0016J\u001c)mC\u000e,wL\u00127pCR|v\n]*vEV\u0011Qq\u0019\t\t\u0007+\u0011)N!\u0007\u0003\u001a\u0005a2m]2`GN\u001cw,\u00138QY\u0006\u001cWm\u0018#pk\ndWmX(q'V\u0014WCACg!!\u0019)B!6\u0003\f\t-\u0011AG2tG~\u001b7oY0J]Bc\u0017mY3`\u0019>twmX(q'V\u0014WCACj!!\u0019)B!6\u0003(\t\u001d\u0012!G2tG~\u001b7oY0J]Bc\u0017mY3`\u0013:$xl\u00149ESZ,\"!\"7\u0011\u0011\tU$Q[A\u007f\u0003{\f1dY:d?\u000e\u001c8mX%o!2\f7-Z0GY>\fGoX(q\t&4XCACp!!\u0011)H!6\u0003\u001a\te\u0011\u0001H2tG~\u001b7oY0J]Bc\u0017mY3`\t>,(\r\\3`\u001fB$\u0015N^\u000b\u0003\u000bK\u0004\u0002B!\u001e\u0003V\n-!1B\u0001\u001bGN\u001cwlY:d?&s\u0007\u000b\\1dK~cuN\\4`\u001fB$\u0015N^\u000b\u0003\u000bW\u0004\u0002B!\u001e\u0003V\n\u001d\"qE\u0001\u001aGN\u001cwlY:d?&s\u0007\u000b\\1dK~Ke\u000e^0PaB{w/\u0006\u0002\u0006rBA!q\u000bBk\u0003{\fi0A\u000edg\u000e|6m]2`\u0013:\u0004F.Y2f?\u001acw.\u0019;`\u001fB\u0004vn^\u000b\u0003\u000bo\u0004\u0002Ba\u0016\u0003V\ne!\u0011D\u0001\u001dGN\u001cwlY:d?&s\u0007\u000b\\1dK~#u.\u001e2mK~{\u0005\u000fU8x+\t)i\u0010\u0005\u0005\u0003X\tU'1\u0002B\u0006\u0003i\u00197oY0dg\u000e|\u0016J\u001c)mC\u000e,w\fT8oO~{\u0005\u000fU8x+\t1\u0019\u0001\u0005\u0005\u0003X\tU'q\u0005B\u0014\u0003e\u00197oY0dg\u000e|\u0016J\u001c)mC\u000e,w,\u00138u?>\u0003Xj\u001c3\u0016\u0005\u0019%\u0001\u0003\u0003BJ\u0005+\fi0!@\u00027\r\u001c8mX2tG~Ke\u000e\u00157bG\u0016|f\t\\8bi~{\u0005/T8e+\t1y\u0001\u0005\u0005\u0003\u0014\nU'\u0011\u0004B\r\u0003q\u00197oY0dg\u000e|\u0016J\u001c)mC\u000e,w\fR8vE2,wl\u00149N_\u0012,\"A\"\u0006\u0011\u0011\tM%Q\u001bB\u0006\u0005\u0017\t!dY:d?\u000e\u001c8mX%o!2\f7-Z0M_:<wl\u00149N_\u0012,\"Ab\u0007\u0011\u0011\tM%Q\u001bB\u0014\u0005O\tqdY:d?\u000e\u001c8mX%o!2\f7-Z0J]R|v\n]'vYN\u001b\u0017\r\\1s+\t1\t\u0003\u0005\u0005\u0004V\nU\u0017Q`A\u007f\u0003\u0005\u001a7oY0dg\u000e|\u0016J\u001c)mC\u000e,wL\u00127pCR|v\n]'vYN\u001b\u0017\r\\1s+\t19\u0003\u0005\u0005\u0004V\nU'\u0011\u0004B\r\u0003\t\u001a7oY0dg\u000e|\u0016J\u001c)mC\u000e,w\fR8vE2,wl\u00149Nk2\u001c6-\u00197beV\u0011aQ\u0006\t\t\u0007+\u0014)Na\u0003\u0003\f\u0005\u00013m]2`GN\u001cw,\u00138QY\u0006\u001cWm\u0018'p]\u001e|v\n]'vYN\u001b\u0017\r\\1s+\t1\u0019\u0004\u0005\u0005\u0004V\nU'q\u0005B\u0014\u0003E\t\u0007\u0010]=D'\u000e{F)T0E\u001b~Ke\u000e^\u000b\u0003\rs\u0001\"B!\u000e\u0003<\te\u0017Q Bm\u0003M\t\u0007\u0010]=D'\u000e{F)T0E\u001b~3En\\1u+\t1y\u0004\u0005\u0006\u00036\tm\"Q\u001eB\r\u0005[\fA#\u0019=qs\u000e\u001b6i\u0018#N?\u0012ku\fR8vE2,WC\u0001D#!)\u0011)Da\u000f\u0003f\n-!Q]\u0001\u0013Cb\u0004\u0018pQ*D?\u0012ku\fR'`\u0019>tw-\u0006\u0002\u0007LAQ!Q\u0007B\u001e\u0005k\u00149C!>"
)
public interface CSCMatrixExpandedOps extends MatrixOps, CSCMatrixOps_Ring {
   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_OpNeg_Int_$eq(final UFunc.UImpl x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_OpNeg_Double_$eq(final UFunc.UImpl x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_OpNeg_Float_$eq(final UFunc.UImpl x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_OpNeg_Long_$eq(final UFunc.UImpl x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$cscScaleAdd_Int_$eq(final UFunc.InPlaceImpl3 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$cscScaleAdd_Double_$eq(final UFunc.InPlaceImpl3 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$cscScaleAdd_Float_$eq(final UFunc.InPlaceImpl3 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$cscScaleAdd_Long_$eq(final UFunc.InPlaceImpl3 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_BadOps_Int_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_BadOps_Double_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_BadOps_Float_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_BadOps_Long_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_BadOps_Int_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_BadOps_Double_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_BadOps_Float_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_BadOps_Long_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_BadOps_Int_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_BadOps_Double_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_BadOps_Float_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_BadOps_Long_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_OpAdd_Int_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_OpAdd_Double_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_OpAdd_Float_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_OpAdd_Long_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_InPlace_OpSet_Int_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_InPlace_OpSet_Double_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_InPlace_OpSet_Float_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_InPlace_OpSet_Long_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_InPlace_OpAdd_Int_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_InPlace_OpAdd_Double_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_InPlace_OpAdd_Float_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_InPlace_OpAdd_Long_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_InPlace_OpSub_Int_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_InPlace_OpSub_Double_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_InPlace_OpSub_Float_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_InPlace_OpSub_Long_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_dm_OpAdd_Int_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_dm_OpAdd_Double_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_dm_OpAdd_Float_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_dm_OpAdd_Long_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_OpAdd_Int_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_OpAdd_Double_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_OpAdd_Float_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_OpAdd_Long_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_OpSub_Int_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_OpSub_Double_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_OpSub_Float_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_OpSub_Long_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_dm_OpSub_Int_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_dm_OpSub_Double_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_dm_OpSub_Float_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_dm_OpSub_Long_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_OpMulScalar_Int_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_OpMulScalar_Double_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_OpMulScalar_Float_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_OpMulScalar_Long_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_OpSub_Int_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_OpSub_Double_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_OpSub_Float_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_OpSub_Long_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSCT_T_eq_CSCT_Int_OpMulScalar_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSCT_T_eq_CSCT_Double_OpMulScalar_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSCT_T_eq_CSCT_Float_OpMulScalar_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSCT_T_eq_CSCT_Long_OpMulScalar_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSCT_T_eq_CSCT_Int_OpMulMatrix_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSCT_T_eq_CSCT_Double_OpMulMatrix_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSCT_T_eq_CSCT_Float_OpMulMatrix_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSCT_T_eq_CSCT_Long_OpMulMatrix_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_V_Int_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_V_Float_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_V_Double_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_V_Long_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_DV_Int_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_DV_Float_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_DV_Double_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_DV_Long_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_SV_Int_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_SV_Float_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_SV_Double_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_SV_Long_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_DM_Int_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_DM_Float_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_DM_Double_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_DM_Long_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulDM_M_Int_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulDM_M_Float_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulDM_M_Double_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulDM_M_Long_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_M_Int_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_M_Float_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_M_Double_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_M_Long_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Int_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Float_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Double_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Long_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Int_OpSub_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Float_OpSub_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Double_OpSub_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Long_OpSub_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Int_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Float_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Double_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Long_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Int_OpPow_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Float_OpPow_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Double_OpPow_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Long_OpPow_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Int_OpMod_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Float_OpMod_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Double_OpMod_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Long_OpMod_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Int_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Float_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Double_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Long_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Int_OpMulMatrix_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Float_OpMulMatrix_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Double_OpMulMatrix_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Long_OpMulMatrix_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Int_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Float_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Double_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Long_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Int_OpSub_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Float_OpSub_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Double_OpSub_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Long_OpSub_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Int_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Float_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Double_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Long_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Int_OpPow_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Float_OpPow_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Double_OpPow_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Long_OpPow_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Int_OpMod_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Float_OpMod_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Double_OpMod_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Long_OpMod_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Int_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Float_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Double_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Long_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$axpyCSC_DM_DM_Int_$eq(final UFunc.InPlaceImpl3 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$axpyCSC_DM_DM_Float_$eq(final UFunc.InPlaceImpl3 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$axpyCSC_DM_DM_Double_$eq(final UFunc.InPlaceImpl3 x$1);

   void breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$axpyCSC_DM_DM_Long_$eq(final UFunc.InPlaceImpl3 x$1);

   // $FF: synthetic method
   static UFunc.UImpl2 canMul_SV_CSC_eq_CSC$(final CSCMatrixExpandedOps $this, final UFunc.UImpl2 op, final Zero zero) {
      return $this.canMul_SV_CSC_eq_CSC(op, zero);
   }

   default UFunc.UImpl2 canMul_SV_CSC_eq_CSC(final UFunc.UImpl2 op, final Zero zero) {
      return new UFunc.UImpl2(zero, op) {
         private final Zero zero$2;
         private final UFunc.UImpl2 op$1;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public CSCMatrix apply(final SparseVector v, final CSCMatrix v2) {
            int left$macro$1 = v2.rows();
            int right$macro$2 = 1;
            if (left$macro$1 != 1) {
               throw new IllegalArgumentException((new StringBuilder(50)).append("requirement failed: ").append("v2.rows == 1 (").append(left$macro$1).append(" ").append("!=").append(" ").append(1).append(")").toString());
            } else {
               CSCMatrix csc = new CSCMatrix(v.data(), v.length(), 1, new int[]{0, v.activeSize()}, v.activeSize(), v.index(), this.zero$2);
               return (CSCMatrix)this.op$1.apply(csc, v2);
            }
         }

         public {
            this.zero$2 = zero$2;
            this.op$1 = op$1;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 canMul_SVt_CSC_eq_SVt$(final CSCMatrixExpandedOps $this, final UFunc.UImpl2 op, final Zero zero, final ClassTag ct) {
      return $this.canMul_SVt_CSC_eq_SVt(op, zero, ct);
   }

   default UFunc.UImpl2 canMul_SVt_CSC_eq_SVt(final UFunc.UImpl2 op, final Zero zero, final ClassTag ct) {
      return new UFunc.UImpl2(ct, op, zero) {
         private final ClassTag ct$1;
         private final UFunc.UImpl2 op$2;
         private final Zero zero$3;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public Transpose apply(final Transpose v, final CSCMatrix v2) {
            int left$macro$1 = v2.rows();
            int right$macro$2 = ((SparseVector)v.inner()).length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(52)).append("requirement failed: ").append("v2.rows == v.inner.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               CSCMatrix csc = ((SparseVector)v.inner()).asCscRow(this.ct$1);
               CSCMatrix cscr = (CSCMatrix)this.op$2.apply(csc, v2);
               int[] ind = (int[]).MODULE$.ofDim(scala.runtime.ScalaRunTime..MODULE$.array_length(cscr.data()), scala.reflect.ClassTag..MODULE$.Int());
               int i = 0;

               for(int c = 1; c < cscr.colPtrs().length; ++c) {
                  if (cscr.colPtrs()[c - 1] != cscr.colPtrs()[c]) {
                     ind[i] = c - 1;
                     ++i;
                  }
               }

               return new Transpose(new SparseVector(ind, cscr.data(), cscr.activeSize(), cscr.cols(), this.zero$3));
            }
         }

         public {
            this.ct$1 = ct$1;
            this.op$2 = op$2;
            this.zero$3 = zero$3;
         }
      };
   }

   UFunc.UImpl csc_OpNeg_Int();

   UFunc.UImpl csc_OpNeg_Double();

   UFunc.UImpl csc_OpNeg_Float();

   UFunc.UImpl csc_OpNeg_Long();

   UFunc.InPlaceImpl3 cscScaleAdd_Int();

   UFunc.InPlaceImpl3 cscScaleAdd_Double();

   UFunc.InPlaceImpl3 cscScaleAdd_Float();

   UFunc.InPlaceImpl3 cscScaleAdd_Long();

   UFunc.UImpl2 csc_csc_BadOps_Int_OpPow();

   UFunc.UImpl2 csc_csc_BadOps_Double_OpPow();

   UFunc.UImpl2 csc_csc_BadOps_Float_OpPow();

   UFunc.UImpl2 csc_csc_BadOps_Long_OpPow();

   UFunc.UImpl2 csc_csc_BadOps_Int_OpDiv();

   UFunc.UImpl2 csc_csc_BadOps_Double_OpDiv();

   UFunc.UImpl2 csc_csc_BadOps_Float_OpDiv();

   UFunc.UImpl2 csc_csc_BadOps_Long_OpDiv();

   UFunc.UImpl2 csc_csc_BadOps_Int_OpMod();

   UFunc.UImpl2 csc_csc_BadOps_Double_OpMod();

   UFunc.UImpl2 csc_csc_BadOps_Float_OpMod();

   UFunc.UImpl2 csc_csc_BadOps_Long_OpMod();

   UFunc.UImpl2 csc_csc_OpAdd_Int();

   UFunc.UImpl2 csc_csc_OpAdd_Double();

   UFunc.UImpl2 csc_csc_OpAdd_Float();

   UFunc.UImpl2 csc_csc_OpAdd_Long();

   UFunc.InPlaceImpl2 dm_csc_InPlace_OpSet_Int();

   UFunc.InPlaceImpl2 dm_csc_InPlace_OpSet_Double();

   UFunc.InPlaceImpl2 dm_csc_InPlace_OpSet_Float();

   UFunc.InPlaceImpl2 dm_csc_InPlace_OpSet_Long();

   UFunc.InPlaceImpl2 dm_csc_InPlace_OpAdd_Int();

   UFunc.InPlaceImpl2 dm_csc_InPlace_OpAdd_Double();

   UFunc.InPlaceImpl2 dm_csc_InPlace_OpAdd_Float();

   UFunc.InPlaceImpl2 dm_csc_InPlace_OpAdd_Long();

   UFunc.InPlaceImpl2 dm_csc_InPlace_OpSub_Int();

   UFunc.InPlaceImpl2 dm_csc_InPlace_OpSub_Double();

   UFunc.InPlaceImpl2 dm_csc_InPlace_OpSub_Float();

   UFunc.InPlaceImpl2 dm_csc_InPlace_OpSub_Long();

   UFunc.UImpl2 csc_dm_OpAdd_Int();

   UFunc.UImpl2 csc_dm_OpAdd_Double();

   UFunc.UImpl2 csc_dm_OpAdd_Float();

   UFunc.UImpl2 csc_dm_OpAdd_Long();

   UFunc.UImpl2 dm_csc_OpAdd_Int();

   UFunc.UImpl2 dm_csc_OpAdd_Double();

   UFunc.UImpl2 dm_csc_OpAdd_Float();

   UFunc.UImpl2 dm_csc_OpAdd_Long();

   UFunc.UImpl2 dm_csc_OpSub_Int();

   UFunc.UImpl2 dm_csc_OpSub_Double();

   UFunc.UImpl2 dm_csc_OpSub_Float();

   UFunc.UImpl2 dm_csc_OpSub_Long();

   UFunc.UImpl2 csc_dm_OpSub_Int();

   UFunc.UImpl2 csc_dm_OpSub_Double();

   UFunc.UImpl2 csc_dm_OpSub_Float();

   UFunc.UImpl2 csc_dm_OpSub_Long();

   // $FF: synthetic method
   static UFunc.UImpl2 dm_csc_OpAdd_Semi$(final CSCMatrixExpandedOps $this, final Semiring evidence$10, final ClassTag evidence$11) {
      return $this.dm_csc_OpAdd_Semi(evidence$10, evidence$11);
   }

   default UFunc.UImpl2 dm_csc_OpAdd_Semi(final Semiring evidence$10, final ClassTag evidence$11) {
      return new UFunc.UImpl2(evidence$10, evidence$11) {
         // $FF: synthetic field
         private final CSCMatrixExpandedOps $outer;
         private final Semiring evidence$10$1;
         private final ClassTag evidence$11$1;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public DenseMatrix apply(final DenseMatrix a, final CSCMatrix b) {
            return (DenseMatrix)b.$plus(a, this.$outer.csc_dm_Semi(this.evidence$10$1, this.evidence$11$1));
         }

         public {
            if (CSCMatrixExpandedOps.this == null) {
               throw null;
            } else {
               this.$outer = CSCMatrixExpandedOps.this;
               this.evidence$10$1 = evidence$10$1;
               this.evidence$11$1 = evidence$11$1;
            }
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 csc_dm_Semi$(final CSCMatrixExpandedOps $this, final Semiring evidence$12, final ClassTag evidence$13) {
      return $this.csc_dm_Semi(evidence$12, evidence$13);
   }

   default UFunc.UImpl2 csc_dm_Semi(final Semiring evidence$12, final ClassTag evidence$13) {
      return new UFunc.UImpl2(evidence$12, evidence$13) {
         private final Semiring semi;
         private final Semiring evidence$12$1;
         private final ClassTag evidence$13$1;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         private Semiring semi() {
            return this.semi;
         }

         public DenseMatrix apply(final CSCMatrix a, final DenseMatrix b) {
            int left$macro$1 = a.rows();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.rows == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int left$macro$3 = a.cols();
               int right$macro$4 = b.cols();
               if (left$macro$3 != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.cols == b.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  int rows = a.rows();
                  int cols = a.cols();
                  if (cols != 0 && rows != 0) {
                     DenseMatrix res = b.copy();
                     int ci = 0;

                     int ci1;
                     for(int apStop = a.colPtrs()[0]; ci < cols; ci = ci1) {
                        ci1 = ci + 1;
                        int ap = apStop;

                        for(apStop = a.colPtrs()[ci1]; ap < apStop; ++ap) {
                           int ari = ap < apStop ? a.rowIndices()[ap] : rows;
                           res.update(ari, ci, this.semi().$plus(res.apply(ari, ci), scala.runtime.ScalaRunTime..MODULE$.array_apply(a.data(), ap)));
                        }
                     }

                     return res;
                  } else {
                     return DenseMatrix$.MODULE$.zeros(rows, cols, this.evidence$13$1, Zero$.MODULE$.zeroFromSemiring(this.evidence$12$1));
                  }
               }
            }
         }

         public {
            this.evidence$12$1 = evidence$12$1;
            this.evidence$13$1 = evidence$13$1;
            this.semi = (Semiring)scala.Predef..MODULE$.implicitly(evidence$12$1);
         }
      };
   }

   UFunc.UImpl2 csc_csc_OpMulScalar_Int();

   UFunc.UImpl2 csc_csc_OpMulScalar_Double();

   UFunc.UImpl2 csc_csc_OpMulScalar_Float();

   UFunc.UImpl2 csc_csc_OpMulScalar_Long();

   UFunc.UImpl2 csc_csc_OpSub_Int();

   UFunc.UImpl2 csc_csc_OpSub_Double();

   UFunc.UImpl2 csc_csc_OpSub_Float();

   UFunc.UImpl2 csc_csc_OpSub_Long();

   UFunc.UImpl2 impl_Op_CSCT_T_eq_CSCT_Int_OpMulScalar();

   UFunc.UImpl2 impl_Op_CSCT_T_eq_CSCT_Double_OpMulScalar();

   UFunc.UImpl2 impl_Op_CSCT_T_eq_CSCT_Float_OpMulScalar();

   UFunc.UImpl2 impl_Op_CSCT_T_eq_CSCT_Long_OpMulScalar();

   UFunc.UImpl2 impl_Op_CSCT_T_eq_CSCT_Int_OpMulMatrix();

   UFunc.UImpl2 impl_Op_CSCT_T_eq_CSCT_Double_OpMulMatrix();

   UFunc.UImpl2 impl_Op_CSCT_T_eq_CSCT_Float_OpMulMatrix();

   UFunc.UImpl2 impl_Op_CSCT_T_eq_CSCT_Long_OpMulMatrix();

   BinaryRegistry canMulM_V_Int();

   BinaryRegistry canMulM_V_Float();

   BinaryRegistry canMulM_V_Double();

   BinaryRegistry canMulM_V_Long();

   UFunc.UImpl2 canMulM_DV_Int();

   UFunc.UImpl2 canMulM_DV_Float();

   UFunc.UImpl2 canMulM_DV_Double();

   UFunc.UImpl2 canMulM_DV_Long();

   BinaryRegistry canMulM_SV_Int();

   BinaryRegistry canMulM_SV_Float();

   BinaryRegistry canMulM_SV_Double();

   BinaryRegistry canMulM_SV_Long();

   UFunc.UImpl2 canMulM_DM_Int();

   UFunc.UImpl2 canMulM_DM_Float();

   UFunc.UImpl2 canMulM_DM_Double();

   UFunc.UImpl2 canMulM_DM_Long();

   UFunc.UImpl2 canMulDM_M_Int();

   UFunc.UImpl2 canMulDM_M_Float();

   UFunc.UImpl2 canMulDM_M_Double();

   UFunc.UImpl2 canMulDM_M_Long();

   UFunc.UImpl2 canMulM_M_Int();

   UFunc.UImpl2 canMulM_M_Float();

   UFunc.UImpl2 canMulM_M_Double();

   UFunc.UImpl2 canMulM_M_Long();

   UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Int_OpAdd();

   UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Float_OpAdd();

   UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Double_OpAdd();

   UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Long_OpAdd();

   UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Int_OpSub();

   UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Float_OpSub();

   UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Double_OpSub();

   UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Long_OpSub();

   UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Int_OpDiv();

   UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Float_OpDiv();

   UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Double_OpDiv();

   UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Long_OpDiv();

   UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Int_OpPow();

   UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Float_OpPow();

   UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Double_OpPow();

   UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Long_OpPow();

   UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Int_OpMod();

   UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Float_OpMod();

   UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Double_OpMod();

   UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Long_OpMod();

   UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Int_OpMulScalar();

   UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Float_OpMulScalar();

   UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Double_OpMulScalar();

   UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Long_OpMulScalar();

   UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Int_OpMulMatrix();

   UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Float_OpMulMatrix();

   UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Double_OpMulMatrix();

   UFunc.InPlaceImpl2 impl_Op_CSC_T_eq_CSC_lift_Long_OpMulMatrix();

   UFunc.InPlaceImpl2 csc_csc_InPlace_Int_OpAdd();

   UFunc.InPlaceImpl2 csc_csc_InPlace_Float_OpAdd();

   UFunc.InPlaceImpl2 csc_csc_InPlace_Double_OpAdd();

   UFunc.InPlaceImpl2 csc_csc_InPlace_Long_OpAdd();

   UFunc.InPlaceImpl2 csc_csc_InPlace_Int_OpSub();

   UFunc.InPlaceImpl2 csc_csc_InPlace_Float_OpSub();

   UFunc.InPlaceImpl2 csc_csc_InPlace_Double_OpSub();

   UFunc.InPlaceImpl2 csc_csc_InPlace_Long_OpSub();

   UFunc.InPlaceImpl2 csc_csc_InPlace_Int_OpDiv();

   UFunc.InPlaceImpl2 csc_csc_InPlace_Float_OpDiv();

   UFunc.InPlaceImpl2 csc_csc_InPlace_Double_OpDiv();

   UFunc.InPlaceImpl2 csc_csc_InPlace_Long_OpDiv();

   UFunc.InPlaceImpl2 csc_csc_InPlace_Int_OpPow();

   UFunc.InPlaceImpl2 csc_csc_InPlace_Float_OpPow();

   UFunc.InPlaceImpl2 csc_csc_InPlace_Double_OpPow();

   UFunc.InPlaceImpl2 csc_csc_InPlace_Long_OpPow();

   UFunc.InPlaceImpl2 csc_csc_InPlace_Int_OpMod();

   UFunc.InPlaceImpl2 csc_csc_InPlace_Float_OpMod();

   UFunc.InPlaceImpl2 csc_csc_InPlace_Double_OpMod();

   UFunc.InPlaceImpl2 csc_csc_InPlace_Long_OpMod();

   UFunc.InPlaceImpl2 csc_csc_InPlace_Int_OpMulScalar();

   UFunc.InPlaceImpl2 csc_csc_InPlace_Float_OpMulScalar();

   UFunc.InPlaceImpl2 csc_csc_InPlace_Double_OpMulScalar();

   UFunc.InPlaceImpl2 csc_csc_InPlace_Long_OpMulScalar();

   UFunc.InPlaceImpl3 axpyCSC_DM_DM_Int();

   UFunc.InPlaceImpl3 axpyCSC_DM_DM_Float();

   UFunc.InPlaceImpl3 axpyCSC_DM_DM_Double();

   UFunc.InPlaceImpl3 axpyCSC_DM_DM_Long();

   private static void computeZeroOpOnRange$1(final int[] arr, final int start, final int end, final int mZero$1) {
      for(int i = start; i < end; ++i) {
         arr[i] = PowImplicits$.MODULE$.IntPow(arr[i]).pow(mZero$1);
      }

   }

   private static void computeZeroOpOnRange$2(final double[] arr, final int start, final int end, final double mZero$2) {
      for(int i = start; i < end; ++i) {
         arr[i] = PowImplicits$.MODULE$.DoublePow(arr[i]).pow(mZero$2);
      }

   }

   private static void computeZeroOpOnRange$3(final float[] arr, final int start, final int end, final float mZero$3) {
      for(int i = start; i < end; ++i) {
         arr[i] = PowImplicits$.MODULE$.FloatPow(arr[i]).pow(mZero$3);
      }

   }

   private static void computeZeroOpOnRange$4(final long[] arr, final int start, final int end, final long mZero$4) {
      for(int i = start; i < end; ++i) {
         arr[i] = PowImplicits$.MODULE$.LongPow(arr[i]).pow(mZero$4);
      }

   }

   private static void computeZeroOpOnRange$5(final int[] arr, final int start, final int end, final int mZero$5) {
      for(int i = start; i < end; ++i) {
         arr[i] /= mZero$5;
      }

   }

   private static void computeZeroOpOnRange$6(final double[] arr, final int start, final int end, final double mZero$6) {
      for(int i = start; i < end; ++i) {
         arr[i] /= mZero$6;
      }

   }

   private static void computeZeroOpOnRange$7(final float[] arr, final int start, final int end, final float mZero$7) {
      for(int i = start; i < end; ++i) {
         arr[i] /= mZero$7;
      }

   }

   private static void computeZeroOpOnRange$8(final long[] arr, final int start, final int end, final long mZero$8) {
      for(int i = start; i < end; ++i) {
         arr[i] /= mZero$8;
      }

   }

   private static void computeZeroOpOnRange$9(final int[] arr, final int start, final int end, final int mZero$9) {
      for(int i = start; i < end; ++i) {
         arr[i] %= mZero$9;
      }

   }

   private static void computeZeroOpOnRange$10(final double[] arr, final int start, final int end, final double mZero$10) {
      for(int i = start; i < end; ++i) {
         arr[i] %= mZero$10;
      }

   }

   private static void computeZeroOpOnRange$11(final float[] arr, final int start, final int end, final float mZero$11) {
      for(int i = start; i < end; ++i) {
         arr[i] %= mZero$11;
      }

   }

   private static void computeZeroOpOnRange$12(final long[] arr, final int start, final int end, final long mZero$12) {
      for(int i = start; i < end; ++i) {
         arr[i] %= mZero$12;
      }

   }

   static void $init$(final CSCMatrixExpandedOps $this) {
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_OpNeg_Int_$eq(new UFunc.UImpl() {
         public double apply$mcDD$sp(final double v) {
            return UFunc.UImpl.apply$mcDD$sp$(this, v);
         }

         public float apply$mcDF$sp(final double v) {
            return UFunc.UImpl.apply$mcDF$sp$(this, v);
         }

         public int apply$mcDI$sp(final double v) {
            return UFunc.UImpl.apply$mcDI$sp$(this, v);
         }

         public double apply$mcFD$sp(final float v) {
            return UFunc.UImpl.apply$mcFD$sp$(this, v);
         }

         public float apply$mcFF$sp(final float v) {
            return UFunc.UImpl.apply$mcFF$sp$(this, v);
         }

         public int apply$mcFI$sp(final float v) {
            return UFunc.UImpl.apply$mcFI$sp$(this, v);
         }

         public double apply$mcID$sp(final int v) {
            return UFunc.UImpl.apply$mcID$sp$(this, v);
         }

         public float apply$mcIF$sp(final int v) {
            return UFunc.UImpl.apply$mcIF$sp$(this, v);
         }

         public int apply$mcII$sp(final int v) {
            return UFunc.UImpl.apply$mcII$sp$(this, v);
         }

         public CSCMatrix apply(final CSCMatrix a) {
            CSCMatrix acp = a.copy$mcI$sp();

            for(int c = 0; c < acp.cols(); ++c) {
               for(int ip = acp.colPtrs()[c]; ip < acp.colPtrs()[c + 1]; ++ip) {
                  int r = acp.rowIndices()[ip];
                  acp.data$mcI$sp()[ip] = -acp.data$mcI$sp()[ip];
               }
            }

            return acp;
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_OpNeg_Double_$eq(new UFunc.UImpl() {
         public double apply$mcDD$sp(final double v) {
            return UFunc.UImpl.apply$mcDD$sp$(this, v);
         }

         public float apply$mcDF$sp(final double v) {
            return UFunc.UImpl.apply$mcDF$sp$(this, v);
         }

         public int apply$mcDI$sp(final double v) {
            return UFunc.UImpl.apply$mcDI$sp$(this, v);
         }

         public double apply$mcFD$sp(final float v) {
            return UFunc.UImpl.apply$mcFD$sp$(this, v);
         }

         public float apply$mcFF$sp(final float v) {
            return UFunc.UImpl.apply$mcFF$sp$(this, v);
         }

         public int apply$mcFI$sp(final float v) {
            return UFunc.UImpl.apply$mcFI$sp$(this, v);
         }

         public double apply$mcID$sp(final int v) {
            return UFunc.UImpl.apply$mcID$sp$(this, v);
         }

         public float apply$mcIF$sp(final int v) {
            return UFunc.UImpl.apply$mcIF$sp$(this, v);
         }

         public int apply$mcII$sp(final int v) {
            return UFunc.UImpl.apply$mcII$sp$(this, v);
         }

         public CSCMatrix apply(final CSCMatrix a) {
            CSCMatrix acp = a.copy$mcD$sp();

            for(int c = 0; c < acp.cols(); ++c) {
               for(int ip = acp.colPtrs()[c]; ip < acp.colPtrs()[c + 1]; ++ip) {
                  int r = acp.rowIndices()[ip];
                  acp.data$mcD$sp()[ip] = -acp.data$mcD$sp()[ip];
               }
            }

            return acp;
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_OpNeg_Float_$eq(new UFunc.UImpl() {
         public double apply$mcDD$sp(final double v) {
            return UFunc.UImpl.apply$mcDD$sp$(this, v);
         }

         public float apply$mcDF$sp(final double v) {
            return UFunc.UImpl.apply$mcDF$sp$(this, v);
         }

         public int apply$mcDI$sp(final double v) {
            return UFunc.UImpl.apply$mcDI$sp$(this, v);
         }

         public double apply$mcFD$sp(final float v) {
            return UFunc.UImpl.apply$mcFD$sp$(this, v);
         }

         public float apply$mcFF$sp(final float v) {
            return UFunc.UImpl.apply$mcFF$sp$(this, v);
         }

         public int apply$mcFI$sp(final float v) {
            return UFunc.UImpl.apply$mcFI$sp$(this, v);
         }

         public double apply$mcID$sp(final int v) {
            return UFunc.UImpl.apply$mcID$sp$(this, v);
         }

         public float apply$mcIF$sp(final int v) {
            return UFunc.UImpl.apply$mcIF$sp$(this, v);
         }

         public int apply$mcII$sp(final int v) {
            return UFunc.UImpl.apply$mcII$sp$(this, v);
         }

         public CSCMatrix apply(final CSCMatrix a) {
            CSCMatrix acp = a.copy$mcF$sp();

            for(int c = 0; c < acp.cols(); ++c) {
               for(int ip = acp.colPtrs()[c]; ip < acp.colPtrs()[c + 1]; ++ip) {
                  int r = acp.rowIndices()[ip];
                  acp.data$mcF$sp()[ip] = -acp.data$mcF$sp()[ip];
               }
            }

            return acp;
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_OpNeg_Long_$eq(new UFunc.UImpl() {
         public double apply$mcDD$sp(final double v) {
            return UFunc.UImpl.apply$mcDD$sp$(this, v);
         }

         public float apply$mcDF$sp(final double v) {
            return UFunc.UImpl.apply$mcDF$sp$(this, v);
         }

         public int apply$mcDI$sp(final double v) {
            return UFunc.UImpl.apply$mcDI$sp$(this, v);
         }

         public double apply$mcFD$sp(final float v) {
            return UFunc.UImpl.apply$mcFD$sp$(this, v);
         }

         public float apply$mcFF$sp(final float v) {
            return UFunc.UImpl.apply$mcFF$sp$(this, v);
         }

         public int apply$mcFI$sp(final float v) {
            return UFunc.UImpl.apply$mcFI$sp$(this, v);
         }

         public double apply$mcID$sp(final int v) {
            return UFunc.UImpl.apply$mcID$sp$(this, v);
         }

         public float apply$mcIF$sp(final int v) {
            return UFunc.UImpl.apply$mcIF$sp$(this, v);
         }

         public int apply$mcII$sp(final int v) {
            return UFunc.UImpl.apply$mcII$sp$(this, v);
         }

         public CSCMatrix apply(final CSCMatrix a) {
            CSCMatrix acp = a.copy$mcJ$sp();

            for(int c = 0; c < acp.cols(); ++c) {
               for(int ip = acp.colPtrs()[c]; ip < acp.colPtrs()[c + 1]; ++ip) {
                  int r = acp.rowIndices()[ip];
                  acp.data$mcJ$sp()[ip] = -acp.data$mcJ$sp()[ip];
               }
            }

            return acp;
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$cscScaleAdd_Int_$eq(new UFunc.InPlaceImpl3() {
         public void apply(final CSCMatrix a, final int s, final CSCMatrix b) {
            int left$macro$1 = a.rows();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(84)).append("requirement failed: Matrices must have same number of rows!: ").append("a.rows == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int left$macro$3 = a.cols();
               int right$macro$4 = b.cols();
               if (left$macro$3 != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(84)).append("requirement failed: Matrices must have same number of cols!: ").append("a.cols == b.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  int rows = a.rows();
                  int cols = a.cols();
                  if (cols != 0 && rows != 0) {
                     int mm = max$.MODULE$.apply$mIIIc$sp(a.activeSize(), b.activeSize(), max$.MODULE$.maxImpl2_Int());
                     CSCMatrix.Builder bldr = new CSCMatrix$Builder$mcI$sp(rows, cols, max$.MODULE$.apply$mIIIc$sp(a.activeSize(), b.activeSize(), max$.MODULE$.maxImpl2_Int()), scala.reflect.ClassTag..MODULE$.Int(), Semiring$.MODULE$.semiringInt(), Zero$.MODULE$.IntZero());
                     int ci = 0;
                     int apStop = a.colPtrs()[0];

                     int ci1;
                     for(int bpStop = b.colPtrs()[0]; ci < cols; ci = ci1) {
                        ci1 = ci + 1;
                        int ap = apStop;
                        int bp = bpStop;
                        apStop = a.colPtrs()[ci1];
                        bpStop = b.colPtrs()[ci1];

                        while(ap < apStop || bp < bpStop) {
                           int ari = ap < apStop ? a.rowIndices()[ap] : rows;
                           int bri = bp < bpStop ? b.rowIndices()[bp] : rows;
                           if (ari == bri) {
                              bldr.add$mcI$sp(ari, ci, a.data$mcI$sp()[ap] + s * b.data$mcI$sp()[bp]);
                              ++ap;
                              ++bp;
                           } else if (ari < bri) {
                              bldr.add$mcI$sp(ari, ci, a.data$mcI$sp()[ap]);
                              ++ap;
                           } else {
                              bldr.add$mcI$sp(bri, ci, s * b.data$mcI$sp()[bp]);
                              ++bp;
                           }
                        }
                     }

                     CSCMatrix res = bldr.result$mcI$sp(true, true);
                     a.use$mcI$sp(res.data$mcI$sp(), res.colPtrs(), res.rowIndices(), res.activeSize());
                  }
               }
            }
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$cscScaleAdd_Double_$eq(new UFunc.InPlaceImpl3() {
         public void apply(final CSCMatrix a, final double s, final CSCMatrix b) {
            int left$macro$1 = a.rows();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(84)).append("requirement failed: Matrices must have same number of rows!: ").append("a.rows == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int left$macro$3 = a.cols();
               int right$macro$4 = b.cols();
               if (left$macro$3 != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(84)).append("requirement failed: Matrices must have same number of cols!: ").append("a.cols == b.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  int rows = a.rows();
                  int cols = a.cols();
                  if (cols != 0 && rows != 0) {
                     int mm = max$.MODULE$.apply$mIIIc$sp(a.activeSize(), b.activeSize(), max$.MODULE$.maxImpl2_Int());
                     CSCMatrix.Builder bldr = new CSCMatrix$Builder$mcD$sp(rows, cols, max$.MODULE$.apply$mIIIc$sp(a.activeSize(), b.activeSize(), max$.MODULE$.maxImpl2_Int()), scala.reflect.ClassTag..MODULE$.Double(), Semiring$.MODULE$.semiringD(), Zero$.MODULE$.DoubleZero());
                     int ci = 0;
                     int apStop = a.colPtrs()[0];

                     int ci1;
                     for(int bpStop = b.colPtrs()[0]; ci < cols; ci = ci1) {
                        ci1 = ci + 1;
                        int ap = apStop;
                        int bp = bpStop;
                        apStop = a.colPtrs()[ci1];
                        bpStop = b.colPtrs()[ci1];

                        while(ap < apStop || bp < bpStop) {
                           int ari = ap < apStop ? a.rowIndices()[ap] : rows;
                           int bri = bp < bpStop ? b.rowIndices()[bp] : rows;
                           if (ari == bri) {
                              bldr.add$mcD$sp(ari, ci, a.data$mcD$sp()[ap] + s * b.data$mcD$sp()[bp]);
                              ++ap;
                              ++bp;
                           } else if (ari < bri) {
                              bldr.add$mcD$sp(ari, ci, a.data$mcD$sp()[ap]);
                              ++ap;
                           } else {
                              bldr.add$mcD$sp(bri, ci, s * b.data$mcD$sp()[bp]);
                              ++bp;
                           }
                        }
                     }

                     CSCMatrix res = bldr.result$mcD$sp(true, true);
                     a.use$mcD$sp(res.data$mcD$sp(), res.colPtrs(), res.rowIndices(), res.activeSize());
                  }
               }
            }
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$cscScaleAdd_Float_$eq(new UFunc.InPlaceImpl3() {
         public void apply(final CSCMatrix a, final float s, final CSCMatrix b) {
            int left$macro$1 = a.rows();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(84)).append("requirement failed: Matrices must have same number of rows!: ").append("a.rows == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int left$macro$3 = a.cols();
               int right$macro$4 = b.cols();
               if (left$macro$3 != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(84)).append("requirement failed: Matrices must have same number of cols!: ").append("a.cols == b.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  int rows = a.rows();
                  int cols = a.cols();
                  if (cols != 0 && rows != 0) {
                     int mm = max$.MODULE$.apply$mIIIc$sp(a.activeSize(), b.activeSize(), max$.MODULE$.maxImpl2_Int());
                     CSCMatrix.Builder bldr = new CSCMatrix$Builder$mcF$sp(rows, cols, max$.MODULE$.apply$mIIIc$sp(a.activeSize(), b.activeSize(), max$.MODULE$.maxImpl2_Int()), scala.reflect.ClassTag..MODULE$.Float(), Semiring$.MODULE$.semiringFloat(), Zero$.MODULE$.FloatZero());
                     int ci = 0;
                     int apStop = a.colPtrs()[0];

                     int ci1;
                     for(int bpStop = b.colPtrs()[0]; ci < cols; ci = ci1) {
                        ci1 = ci + 1;
                        int ap = apStop;
                        int bp = bpStop;
                        apStop = a.colPtrs()[ci1];
                        bpStop = b.colPtrs()[ci1];

                        while(ap < apStop || bp < bpStop) {
                           int ari = ap < apStop ? a.rowIndices()[ap] : rows;
                           int bri = bp < bpStop ? b.rowIndices()[bp] : rows;
                           if (ari == bri) {
                              bldr.add$mcF$sp(ari, ci, a.data$mcF$sp()[ap] + s * b.data$mcF$sp()[bp]);
                              ++ap;
                              ++bp;
                           } else if (ari < bri) {
                              bldr.add$mcF$sp(ari, ci, a.data$mcF$sp()[ap]);
                              ++ap;
                           } else {
                              bldr.add$mcF$sp(bri, ci, s * b.data$mcF$sp()[bp]);
                              ++bp;
                           }
                        }
                     }

                     CSCMatrix res = bldr.result$mcF$sp(true, true);
                     a.use$mcF$sp(res.data$mcF$sp(), res.colPtrs(), res.rowIndices(), res.activeSize());
                  }
               }
            }
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$cscScaleAdd_Long_$eq(new UFunc.InPlaceImpl3() {
         public void apply(final CSCMatrix a, final long s, final CSCMatrix b) {
            int left$macro$1 = a.rows();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(84)).append("requirement failed: Matrices must have same number of rows!: ").append("a.rows == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int left$macro$3 = a.cols();
               int right$macro$4 = b.cols();
               if (left$macro$3 != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(84)).append("requirement failed: Matrices must have same number of cols!: ").append("a.cols == b.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  int rows = a.rows();
                  int cols = a.cols();
                  if (cols != 0 && rows != 0) {
                     int mm = max$.MODULE$.apply$mIIIc$sp(a.activeSize(), b.activeSize(), max$.MODULE$.maxImpl2_Int());
                     CSCMatrix.Builder bldr = new CSCMatrix$Builder$mcJ$sp(rows, cols, max$.MODULE$.apply$mIIIc$sp(a.activeSize(), b.activeSize(), max$.MODULE$.maxImpl2_Int()), scala.reflect.ClassTag..MODULE$.Long(), Semiring$.MODULE$.semiringLong(), Zero$.MODULE$.LongZero());
                     int ci = 0;
                     int apStop = a.colPtrs()[0];

                     int ci1;
                     for(int bpStop = b.colPtrs()[0]; ci < cols; ci = ci1) {
                        ci1 = ci + 1;
                        int ap = apStop;
                        int bp = bpStop;
                        apStop = a.colPtrs()[ci1];
                        bpStop = b.colPtrs()[ci1];

                        while(ap < apStop || bp < bpStop) {
                           int ari = ap < apStop ? a.rowIndices()[ap] : rows;
                           int bri = bp < bpStop ? b.rowIndices()[bp] : rows;
                           if (ari == bri) {
                              bldr.add$mcJ$sp(ari, ci, a.data$mcJ$sp()[ap] + s * b.data$mcJ$sp()[bp]);
                              ++ap;
                              ++bp;
                           } else if (ari < bri) {
                              bldr.add$mcJ$sp(ari, ci, a.data$mcJ$sp()[ap]);
                              ++ap;
                           } else {
                              bldr.add$mcJ$sp(bri, ci, s * b.data$mcJ$sp()[bp]);
                              ++bp;
                           }
                        }
                     }

                     CSCMatrix res = bldr.result$mcJ$sp(true, true);
                     a.use$mcJ$sp(res.data$mcJ$sp(), res.colPtrs(), res.rowIndices(), res.activeSize());
                  }
               }
            }
         }
      });
      int mZero = BoxesRunTime.unboxToInt(scala.Predef..MODULE$.implicitly(BoxesRunTime.boxToInteger(0)));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_BadOps_Int_OpPow_$eq(new UFunc.UImpl2(mZero) {
         private final int mZero$1;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public CSCMatrix apply(final CSCMatrix a, final CSCMatrix b) {
            int rows = a.rows();
            int cols = a.cols();
            int right$macro$2 = b.rows();
            if (rows != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of rows!: ").append("rows == b.rows (").append(rows).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int right$macro$4 = b.cols();
               if (cols != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of cols!: ").append("cols == b.cols (").append(cols).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  CSCMatrix.Builder resultBuilder = new CSCMatrix$Builder$mcI$sp(rows, cols, rows * cols, scala.reflect.ClassTag..MODULE$.Int(), Semiring$.MODULE$.semiringInt(), Zero$.MODULE$.IntZero());
                  int index$macro$11 = 0;

                  for(int limit$macro$13 = cols; index$macro$11 < limit$macro$13; ++index$macro$11) {
                     int index$macro$6 = 0;

                     for(int limit$macro$8 = rows; index$macro$6 < limit$macro$8; ++index$macro$6) {
                        ((j, i) -> {
                           int aVal = a.apply$mcI$sp(i, j);
                           int bVal = b.apply$mcI$sp(i, j);
                           int res = PowImplicits$.MODULE$.IntPow(aVal).pow(bVal);
                           if (res != this.mZero$1) {
                              resultBuilder.add$mcI$sp(i, j, res);
                           }

                        }).apply$mcVII$sp(index$macro$11, index$macro$6);
                     }
                  }

                  return resultBuilder.result$mcI$sp(true, true);
               }
            }
         }

         public {
            this.mZero$1 = mZero$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      double mZero = BoxesRunTime.unboxToDouble(scala.Predef..MODULE$.implicitly(BoxesRunTime.boxToDouble((double)0.0F)));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_BadOps_Double_OpPow_$eq(new UFunc.UImpl2(mZero) {
         private final double mZero$2;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public CSCMatrix apply(final CSCMatrix a, final CSCMatrix b) {
            int rows = a.rows();
            int cols = a.cols();
            int right$macro$2 = b.rows();
            if (rows != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of rows!: ").append("rows == b.rows (").append(rows).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int right$macro$4 = b.cols();
               if (cols != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of cols!: ").append("cols == b.cols (").append(cols).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  CSCMatrix.Builder resultBuilder = new CSCMatrix$Builder$mcD$sp(rows, cols, rows * cols, scala.reflect.ClassTag..MODULE$.Double(), Semiring$.MODULE$.semiringD(), Zero$.MODULE$.DoubleZero());
                  int index$macro$11 = 0;

                  for(int limit$macro$13 = cols; index$macro$11 < limit$macro$13; ++index$macro$11) {
                     int index$macro$6 = 0;

                     for(int limit$macro$8 = rows; index$macro$6 < limit$macro$8; ++index$macro$6) {
                        ((j, i) -> {
                           double aVal = a.apply$mcD$sp(i, j);
                           double bVal = b.apply$mcD$sp(i, j);
                           double res = PowImplicits$.MODULE$.DoublePow(aVal).pow(bVal);
                           if (res != this.mZero$2) {
                              resultBuilder.add$mcD$sp(i, j, res);
                           }

                        }).apply$mcVII$sp(index$macro$11, index$macro$6);
                     }
                  }

                  return resultBuilder.result$mcD$sp(true, true);
               }
            }
         }

         public {
            this.mZero$2 = mZero$2;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      float mZero = BoxesRunTime.unboxToFloat(scala.Predef..MODULE$.implicitly(BoxesRunTime.boxToFloat(0.0F)));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_BadOps_Float_OpPow_$eq(new UFunc.UImpl2(mZero) {
         private final float mZero$3;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public CSCMatrix apply(final CSCMatrix a, final CSCMatrix b) {
            int rows = a.rows();
            int cols = a.cols();
            int right$macro$2 = b.rows();
            if (rows != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of rows!: ").append("rows == b.rows (").append(rows).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int right$macro$4 = b.cols();
               if (cols != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of cols!: ").append("cols == b.cols (").append(cols).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  CSCMatrix.Builder resultBuilder = new CSCMatrix$Builder$mcF$sp(rows, cols, rows * cols, scala.reflect.ClassTag..MODULE$.Float(), Semiring$.MODULE$.semiringFloat(), Zero$.MODULE$.FloatZero());
                  int index$macro$11 = 0;

                  for(int limit$macro$13 = cols; index$macro$11 < limit$macro$13; ++index$macro$11) {
                     int index$macro$6 = 0;

                     for(int limit$macro$8 = rows; index$macro$6 < limit$macro$8; ++index$macro$6) {
                        ((j, i) -> {
                           float aVal = a.apply$mcF$sp(i, j);
                           float bVal = b.apply$mcF$sp(i, j);
                           float res = PowImplicits$.MODULE$.FloatPow(aVal).pow(bVal);
                           if (res != this.mZero$3) {
                              resultBuilder.add$mcF$sp(i, j, res);
                           }

                        }).apply$mcVII$sp(index$macro$11, index$macro$6);
                     }
                  }

                  return resultBuilder.result$mcF$sp(true, true);
               }
            }
         }

         public {
            this.mZero$3 = mZero$3;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      long mZero = BoxesRunTime.unboxToLong(scala.Predef..MODULE$.implicitly(BoxesRunTime.boxToLong(0L)));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_BadOps_Long_OpPow_$eq(new UFunc.UImpl2(mZero) {
         private final long mZero$4;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public CSCMatrix apply(final CSCMatrix a, final CSCMatrix b) {
            int rows = a.rows();
            int cols = a.cols();
            int right$macro$2 = b.rows();
            if (rows != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of rows!: ").append("rows == b.rows (").append(rows).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int right$macro$4 = b.cols();
               if (cols != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of cols!: ").append("cols == b.cols (").append(cols).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  CSCMatrix.Builder resultBuilder = new CSCMatrix$Builder$mcJ$sp(rows, cols, rows * cols, scala.reflect.ClassTag..MODULE$.Long(), Semiring$.MODULE$.semiringLong(), Zero$.MODULE$.LongZero());
                  int index$macro$11 = 0;

                  for(int limit$macro$13 = cols; index$macro$11 < limit$macro$13; ++index$macro$11) {
                     int index$macro$6 = 0;

                     for(int limit$macro$8 = rows; index$macro$6 < limit$macro$8; ++index$macro$6) {
                        ((j, i) -> {
                           long aVal = a.apply$mcJ$sp(i, j);
                           long bVal = b.apply$mcJ$sp(i, j);
                           long res = PowImplicits$.MODULE$.LongPow(aVal).pow(bVal);
                           if (res != this.mZero$4) {
                              resultBuilder.add$mcJ$sp(i, j, res);
                           }

                        }).apply$mcVII$sp(index$macro$11, index$macro$6);
                     }
                  }

                  return resultBuilder.result$mcJ$sp(true, true);
               }
            }
         }

         public {
            this.mZero$4 = mZero$4;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      int mZero = BoxesRunTime.unboxToInt(scala.Predef..MODULE$.implicitly(BoxesRunTime.boxToInteger(0)));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_BadOps_Int_OpDiv_$eq(new UFunc.UImpl2(mZero) {
         private final int mZero$5;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public CSCMatrix apply(final CSCMatrix a, final CSCMatrix b) {
            int rows = a.rows();
            int cols = a.cols();
            int right$macro$2 = b.rows();
            if (rows != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of rows!: ").append("rows == b.rows (").append(rows).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int right$macro$4 = b.cols();
               if (cols != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of cols!: ").append("cols == b.cols (").append(cols).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  CSCMatrix.Builder resultBuilder = new CSCMatrix$Builder$mcI$sp(rows, cols, rows * cols, scala.reflect.ClassTag..MODULE$.Int(), Semiring$.MODULE$.semiringInt(), Zero$.MODULE$.IntZero());
                  int index$macro$11 = 0;

                  for(int limit$macro$13 = cols; index$macro$11 < limit$macro$13; ++index$macro$11) {
                     int index$macro$6 = 0;

                     for(int limit$macro$8 = rows; index$macro$6 < limit$macro$8; ++index$macro$6) {
                        ((j, i) -> {
                           int aVal = a.apply$mcI$sp(i, j);
                           int bVal = b.apply$mcI$sp(i, j);
                           int res = aVal / bVal;
                           if (res != this.mZero$5) {
                              resultBuilder.add$mcI$sp(i, j, res);
                           }

                        }).apply$mcVII$sp(index$macro$11, index$macro$6);
                     }
                  }

                  return resultBuilder.result$mcI$sp(true, true);
               }
            }
         }

         public {
            this.mZero$5 = mZero$5;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      double mZero = BoxesRunTime.unboxToDouble(scala.Predef..MODULE$.implicitly(BoxesRunTime.boxToDouble((double)0.0F)));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_BadOps_Double_OpDiv_$eq(new UFunc.UImpl2(mZero) {
         private final double mZero$6;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public CSCMatrix apply(final CSCMatrix a, final CSCMatrix b) {
            int rows = a.rows();
            int cols = a.cols();
            int right$macro$2 = b.rows();
            if (rows != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of rows!: ").append("rows == b.rows (").append(rows).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int right$macro$4 = b.cols();
               if (cols != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of cols!: ").append("cols == b.cols (").append(cols).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  CSCMatrix.Builder resultBuilder = new CSCMatrix$Builder$mcD$sp(rows, cols, rows * cols, scala.reflect.ClassTag..MODULE$.Double(), Semiring$.MODULE$.semiringD(), Zero$.MODULE$.DoubleZero());
                  int index$macro$11 = 0;

                  for(int limit$macro$13 = cols; index$macro$11 < limit$macro$13; ++index$macro$11) {
                     int index$macro$6 = 0;

                     for(int limit$macro$8 = rows; index$macro$6 < limit$macro$8; ++index$macro$6) {
                        ((j, i) -> {
                           double aVal = a.apply$mcD$sp(i, j);
                           double bVal = b.apply$mcD$sp(i, j);
                           double res = aVal / bVal;
                           if (res != this.mZero$6) {
                              resultBuilder.add$mcD$sp(i, j, res);
                           }

                        }).apply$mcVII$sp(index$macro$11, index$macro$6);
                     }
                  }

                  return resultBuilder.result$mcD$sp(true, true);
               }
            }
         }

         public {
            this.mZero$6 = mZero$6;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      float mZero = BoxesRunTime.unboxToFloat(scala.Predef..MODULE$.implicitly(BoxesRunTime.boxToFloat(0.0F)));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_BadOps_Float_OpDiv_$eq(new UFunc.UImpl2(mZero) {
         private final float mZero$7;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public CSCMatrix apply(final CSCMatrix a, final CSCMatrix b) {
            int rows = a.rows();
            int cols = a.cols();
            int right$macro$2 = b.rows();
            if (rows != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of rows!: ").append("rows == b.rows (").append(rows).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int right$macro$4 = b.cols();
               if (cols != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of cols!: ").append("cols == b.cols (").append(cols).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  CSCMatrix.Builder resultBuilder = new CSCMatrix$Builder$mcF$sp(rows, cols, rows * cols, scala.reflect.ClassTag..MODULE$.Float(), Semiring$.MODULE$.semiringFloat(), Zero$.MODULE$.FloatZero());
                  int index$macro$11 = 0;

                  for(int limit$macro$13 = cols; index$macro$11 < limit$macro$13; ++index$macro$11) {
                     int index$macro$6 = 0;

                     for(int limit$macro$8 = rows; index$macro$6 < limit$macro$8; ++index$macro$6) {
                        ((j, i) -> {
                           float aVal = a.apply$mcF$sp(i, j);
                           float bVal = b.apply$mcF$sp(i, j);
                           float res = aVal / bVal;
                           if (res != this.mZero$7) {
                              resultBuilder.add$mcF$sp(i, j, res);
                           }

                        }).apply$mcVII$sp(index$macro$11, index$macro$6);
                     }
                  }

                  return resultBuilder.result$mcF$sp(true, true);
               }
            }
         }

         public {
            this.mZero$7 = mZero$7;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      long mZero = BoxesRunTime.unboxToLong(scala.Predef..MODULE$.implicitly(BoxesRunTime.boxToLong(0L)));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_BadOps_Long_OpDiv_$eq(new UFunc.UImpl2(mZero) {
         private final long mZero$8;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public CSCMatrix apply(final CSCMatrix a, final CSCMatrix b) {
            int rows = a.rows();
            int cols = a.cols();
            int right$macro$2 = b.rows();
            if (rows != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of rows!: ").append("rows == b.rows (").append(rows).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int right$macro$4 = b.cols();
               if (cols != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of cols!: ").append("cols == b.cols (").append(cols).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  CSCMatrix.Builder resultBuilder = new CSCMatrix$Builder$mcJ$sp(rows, cols, rows * cols, scala.reflect.ClassTag..MODULE$.Long(), Semiring$.MODULE$.semiringLong(), Zero$.MODULE$.LongZero());
                  int index$macro$11 = 0;

                  for(int limit$macro$13 = cols; index$macro$11 < limit$macro$13; ++index$macro$11) {
                     int index$macro$6 = 0;

                     for(int limit$macro$8 = rows; index$macro$6 < limit$macro$8; ++index$macro$6) {
                        ((j, i) -> {
                           long aVal = a.apply$mcJ$sp(i, j);
                           long bVal = b.apply$mcJ$sp(i, j);
                           long res = aVal / bVal;
                           if (res != this.mZero$8) {
                              resultBuilder.add$mcJ$sp(i, j, res);
                           }

                        }).apply$mcVII$sp(index$macro$11, index$macro$6);
                     }
                  }

                  return resultBuilder.result$mcJ$sp(true, true);
               }
            }
         }

         public {
            this.mZero$8 = mZero$8;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      int mZero = BoxesRunTime.unboxToInt(scala.Predef..MODULE$.implicitly(BoxesRunTime.boxToInteger(0)));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_BadOps_Int_OpMod_$eq(new UFunc.UImpl2(mZero) {
         private final int mZero$9;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public CSCMatrix apply(final CSCMatrix a, final CSCMatrix b) {
            int rows = a.rows();
            int cols = a.cols();
            int right$macro$2 = b.rows();
            if (rows != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of rows!: ").append("rows == b.rows (").append(rows).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int right$macro$4 = b.cols();
               if (cols != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of cols!: ").append("cols == b.cols (").append(cols).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  CSCMatrix.Builder resultBuilder = new CSCMatrix$Builder$mcI$sp(rows, cols, rows * cols, scala.reflect.ClassTag..MODULE$.Int(), Semiring$.MODULE$.semiringInt(), Zero$.MODULE$.IntZero());
                  int index$macro$11 = 0;

                  for(int limit$macro$13 = cols; index$macro$11 < limit$macro$13; ++index$macro$11) {
                     int index$macro$6 = 0;

                     for(int limit$macro$8 = rows; index$macro$6 < limit$macro$8; ++index$macro$6) {
                        ((j, i) -> {
                           int aVal = a.apply$mcI$sp(i, j);
                           int bVal = b.apply$mcI$sp(i, j);
                           int res = aVal % bVal;
                           if (res != this.mZero$9) {
                              resultBuilder.add$mcI$sp(i, j, res);
                           }

                        }).apply$mcVII$sp(index$macro$11, index$macro$6);
                     }
                  }

                  return resultBuilder.result$mcI$sp(true, true);
               }
            }
         }

         public {
            this.mZero$9 = mZero$9;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      double mZero = BoxesRunTime.unboxToDouble(scala.Predef..MODULE$.implicitly(BoxesRunTime.boxToDouble((double)0.0F)));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_BadOps_Double_OpMod_$eq(new UFunc.UImpl2(mZero) {
         private final double mZero$10;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public CSCMatrix apply(final CSCMatrix a, final CSCMatrix b) {
            int rows = a.rows();
            int cols = a.cols();
            int right$macro$2 = b.rows();
            if (rows != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of rows!: ").append("rows == b.rows (").append(rows).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int right$macro$4 = b.cols();
               if (cols != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of cols!: ").append("cols == b.cols (").append(cols).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  CSCMatrix.Builder resultBuilder = new CSCMatrix$Builder$mcD$sp(rows, cols, rows * cols, scala.reflect.ClassTag..MODULE$.Double(), Semiring$.MODULE$.semiringD(), Zero$.MODULE$.DoubleZero());
                  int index$macro$11 = 0;

                  for(int limit$macro$13 = cols; index$macro$11 < limit$macro$13; ++index$macro$11) {
                     int index$macro$6 = 0;

                     for(int limit$macro$8 = rows; index$macro$6 < limit$macro$8; ++index$macro$6) {
                        ((j, i) -> {
                           double aVal = a.apply$mcD$sp(i, j);
                           double bVal = b.apply$mcD$sp(i, j);
                           double res = aVal % bVal;
                           if (res != this.mZero$10) {
                              resultBuilder.add$mcD$sp(i, j, res);
                           }

                        }).apply$mcVII$sp(index$macro$11, index$macro$6);
                     }
                  }

                  return resultBuilder.result$mcD$sp(true, true);
               }
            }
         }

         public {
            this.mZero$10 = mZero$10;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      float mZero = BoxesRunTime.unboxToFloat(scala.Predef..MODULE$.implicitly(BoxesRunTime.boxToFloat(0.0F)));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_BadOps_Float_OpMod_$eq(new UFunc.UImpl2(mZero) {
         private final float mZero$11;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public CSCMatrix apply(final CSCMatrix a, final CSCMatrix b) {
            int rows = a.rows();
            int cols = a.cols();
            int right$macro$2 = b.rows();
            if (rows != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of rows!: ").append("rows == b.rows (").append(rows).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int right$macro$4 = b.cols();
               if (cols != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of cols!: ").append("cols == b.cols (").append(cols).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  CSCMatrix.Builder resultBuilder = new CSCMatrix$Builder$mcF$sp(rows, cols, rows * cols, scala.reflect.ClassTag..MODULE$.Float(), Semiring$.MODULE$.semiringFloat(), Zero$.MODULE$.FloatZero());
                  int index$macro$11 = 0;

                  for(int limit$macro$13 = cols; index$macro$11 < limit$macro$13; ++index$macro$11) {
                     int index$macro$6 = 0;

                     for(int limit$macro$8 = rows; index$macro$6 < limit$macro$8; ++index$macro$6) {
                        ((j, i) -> {
                           float aVal = a.apply$mcF$sp(i, j);
                           float bVal = b.apply$mcF$sp(i, j);
                           float res = aVal % bVal;
                           if (res != this.mZero$11) {
                              resultBuilder.add$mcF$sp(i, j, res);
                           }

                        }).apply$mcVII$sp(index$macro$11, index$macro$6);
                     }
                  }

                  return resultBuilder.result$mcF$sp(true, true);
               }
            }
         }

         public {
            this.mZero$11 = mZero$11;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      long mZero = BoxesRunTime.unboxToLong(scala.Predef..MODULE$.implicitly(BoxesRunTime.boxToLong(0L)));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_BadOps_Long_OpMod_$eq(new UFunc.UImpl2(mZero) {
         private final long mZero$12;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public CSCMatrix apply(final CSCMatrix a, final CSCMatrix b) {
            int rows = a.rows();
            int cols = a.cols();
            int right$macro$2 = b.rows();
            if (rows != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of rows!: ").append("rows == b.rows (").append(rows).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int right$macro$4 = b.cols();
               if (cols != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of cols!: ").append("cols == b.cols (").append(cols).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  CSCMatrix.Builder resultBuilder = new CSCMatrix$Builder$mcJ$sp(rows, cols, rows * cols, scala.reflect.ClassTag..MODULE$.Long(), Semiring$.MODULE$.semiringLong(), Zero$.MODULE$.LongZero());
                  int index$macro$11 = 0;

                  for(int limit$macro$13 = cols; index$macro$11 < limit$macro$13; ++index$macro$11) {
                     int index$macro$6 = 0;

                     for(int limit$macro$8 = rows; index$macro$6 < limit$macro$8; ++index$macro$6) {
                        ((j, i) -> {
                           long aVal = a.apply$mcJ$sp(i, j);
                           long bVal = b.apply$mcJ$sp(i, j);
                           long res = aVal % bVal;
                           if (res != this.mZero$12) {
                              resultBuilder.add$mcJ$sp(i, j, res);
                           }

                        }).apply$mcVII$sp(index$macro$11, index$macro$6);
                     }
                  }

                  return resultBuilder.result$mcJ$sp(true, true);
               }
            }
         }

         public {
            this.mZero$12 = mZero$12;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_OpAdd_Int_$eq(new UFunc.UImpl2() {
         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public CSCMatrix apply(final CSCMatrix a, final CSCMatrix b) {
            int left$macro$1 = a.rows();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.rows == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int left$macro$3 = a.cols();
               int right$macro$4 = b.cols();
               if (left$macro$3 != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.cols == b.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  int rows = a.rows();
                  int cols = a.cols();
                  if (cols != 0 && rows != 0) {
                     CSCMatrix var10000;
                     if (a.activeSize() == 0) {
                        var10000 = b.copy$mcI$sp();
                     } else if (b.activeSize() == 0) {
                        var10000 = a.copy$mcI$sp();
                     } else {
                        CSCMatrix.Builder bldr = new CSCMatrix$Builder$mcI$sp(rows, cols, scala.math.package..MODULE$.max(a.activeSize(), b.activeSize()), scala.reflect.ClassTag..MODULE$.Int(), Semiring$.MODULE$.semiringInt(), Zero$.MODULE$.IntZero());
                        int ci = 0;
                        int apStop = a.colPtrs()[0];

                        int ci1;
                        for(int bpStop = b.colPtrs()[0]; ci < cols; ci = ci1) {
                           ci1 = ci + 1;
                           int ap = apStop;
                           int bp = bpStop;
                           apStop = a.colPtrs()[ci1];
                           bpStop = b.colPtrs()[ci1];

                           while(ap < apStop || bp < bpStop) {
                              int ari = ap < apStop ? a.rowIndices()[ap] : rows;
                              int bri = bp < bpStop ? b.rowIndices()[bp] : rows;
                              if (ari == bri) {
                                 bldr.add$mcI$sp(ari, ci, a.data$mcI$sp()[ap] + b.data$mcI$sp()[bp]);
                                 ++ap;
                                 ++bp;
                              } else if (ari < bri) {
                                 bldr.add$mcI$sp(ari, ci, a.data$mcI$sp()[ap]);
                                 ++ap;
                              } else {
                                 bldr.add$mcI$sp(bri, ci, b.data$mcI$sp()[bp]);
                                 ++bp;
                              }
                           }
                        }

                        var10000 = bldr.result$mcI$sp(true, true);
                     }

                     return var10000;
                  } else {
                     return CSCMatrix$.MODULE$.zeros(rows, cols, scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());
                  }
               }
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(CSCMatrixExpandedOps.this.op_M_DM_Int_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class), scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class));
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_OpAdd_Double_$eq(new UFunc.UImpl2() {
         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public CSCMatrix apply(final CSCMatrix a, final CSCMatrix b) {
            int left$macro$1 = a.rows();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.rows == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int left$macro$3 = a.cols();
               int right$macro$4 = b.cols();
               if (left$macro$3 != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.cols == b.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  int rows = a.rows();
                  int cols = a.cols();
                  if (cols != 0 && rows != 0) {
                     CSCMatrix var10000;
                     if (a.activeSize() == 0) {
                        var10000 = b.copy$mcD$sp();
                     } else if (b.activeSize() == 0) {
                        var10000 = a.copy$mcD$sp();
                     } else {
                        CSCMatrix.Builder bldr = new CSCMatrix$Builder$mcD$sp(rows, cols, scala.math.package..MODULE$.max(a.activeSize(), b.activeSize()), scala.reflect.ClassTag..MODULE$.Double(), Semiring$.MODULE$.semiringD(), Zero$.MODULE$.DoubleZero());
                        int ci = 0;
                        int apStop = a.colPtrs()[0];

                        int ci1;
                        for(int bpStop = b.colPtrs()[0]; ci < cols; ci = ci1) {
                           ci1 = ci + 1;
                           int ap = apStop;
                           int bp = bpStop;
                           apStop = a.colPtrs()[ci1];
                           bpStop = b.colPtrs()[ci1];

                           while(ap < apStop || bp < bpStop) {
                              int ari = ap < apStop ? a.rowIndices()[ap] : rows;
                              int bri = bp < bpStop ? b.rowIndices()[bp] : rows;
                              if (ari == bri) {
                                 bldr.add$mcD$sp(ari, ci, a.data$mcD$sp()[ap] + b.data$mcD$sp()[bp]);
                                 ++ap;
                                 ++bp;
                              } else if (ari < bri) {
                                 bldr.add$mcD$sp(ari, ci, a.data$mcD$sp()[ap]);
                                 ++ap;
                              } else {
                                 bldr.add$mcD$sp(bri, ci, b.data$mcD$sp()[bp]);
                                 ++bp;
                              }
                           }
                        }

                        var10000 = bldr.result$mcD$sp(true, true);
                     }

                     return var10000;
                  } else {
                     return CSCMatrix$.MODULE$.zeros(rows, cols, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
                  }
               }
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(CSCMatrixExpandedOps.this.op_M_DM_Double_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class), scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class));
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_OpAdd_Float_$eq(new UFunc.UImpl2() {
         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public CSCMatrix apply(final CSCMatrix a, final CSCMatrix b) {
            int left$macro$1 = a.rows();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.rows == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int left$macro$3 = a.cols();
               int right$macro$4 = b.cols();
               if (left$macro$3 != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.cols == b.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  int rows = a.rows();
                  int cols = a.cols();
                  if (cols != 0 && rows != 0) {
                     CSCMatrix var10000;
                     if (a.activeSize() == 0) {
                        var10000 = b.copy$mcF$sp();
                     } else if (b.activeSize() == 0) {
                        var10000 = a.copy$mcF$sp();
                     } else {
                        CSCMatrix.Builder bldr = new CSCMatrix$Builder$mcF$sp(rows, cols, scala.math.package..MODULE$.max(a.activeSize(), b.activeSize()), scala.reflect.ClassTag..MODULE$.Float(), Semiring$.MODULE$.semiringFloat(), Zero$.MODULE$.FloatZero());
                        int ci = 0;
                        int apStop = a.colPtrs()[0];

                        int ci1;
                        for(int bpStop = b.colPtrs()[0]; ci < cols; ci = ci1) {
                           ci1 = ci + 1;
                           int ap = apStop;
                           int bp = bpStop;
                           apStop = a.colPtrs()[ci1];
                           bpStop = b.colPtrs()[ci1];

                           while(ap < apStop || bp < bpStop) {
                              int ari = ap < apStop ? a.rowIndices()[ap] : rows;
                              int bri = bp < bpStop ? b.rowIndices()[bp] : rows;
                              if (ari == bri) {
                                 bldr.add$mcF$sp(ari, ci, a.data$mcF$sp()[ap] + b.data$mcF$sp()[bp]);
                                 ++ap;
                                 ++bp;
                              } else if (ari < bri) {
                                 bldr.add$mcF$sp(ari, ci, a.data$mcF$sp()[ap]);
                                 ++ap;
                              } else {
                                 bldr.add$mcF$sp(bri, ci, b.data$mcF$sp()[bp]);
                                 ++bp;
                              }
                           }
                        }

                        var10000 = bldr.result$mcF$sp(true, true);
                     }

                     return var10000;
                  } else {
                     return CSCMatrix$.MODULE$.zeros(rows, cols, scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());
                  }
               }
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(CSCMatrixExpandedOps.this.op_M_DM_Float_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class), scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class));
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_OpAdd_Long_$eq(new UFunc.UImpl2() {
         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public CSCMatrix apply(final CSCMatrix a, final CSCMatrix b) {
            int left$macro$1 = a.rows();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.rows == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int left$macro$3 = a.cols();
               int right$macro$4 = b.cols();
               if (left$macro$3 != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.cols == b.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  int rows = a.rows();
                  int cols = a.cols();
                  if (cols != 0 && rows != 0) {
                     CSCMatrix var10000;
                     if (a.activeSize() == 0) {
                        var10000 = b.copy$mcJ$sp();
                     } else if (b.activeSize() == 0) {
                        var10000 = a.copy$mcJ$sp();
                     } else {
                        CSCMatrix.Builder bldr = new CSCMatrix$Builder$mcJ$sp(rows, cols, scala.math.package..MODULE$.max(a.activeSize(), b.activeSize()), scala.reflect.ClassTag..MODULE$.Long(), Semiring$.MODULE$.semiringLong(), Zero$.MODULE$.LongZero());
                        int ci = 0;
                        int apStop = a.colPtrs()[0];

                        int ci1;
                        for(int bpStop = b.colPtrs()[0]; ci < cols; ci = ci1) {
                           ci1 = ci + 1;
                           int ap = apStop;
                           int bp = bpStop;
                           apStop = a.colPtrs()[ci1];
                           bpStop = b.colPtrs()[ci1];

                           while(ap < apStop || bp < bpStop) {
                              int ari = ap < apStop ? a.rowIndices()[ap] : rows;
                              int bri = bp < bpStop ? b.rowIndices()[bp] : rows;
                              if (ari == bri) {
                                 bldr.add$mcJ$sp(ari, ci, a.data$mcJ$sp()[ap] + b.data$mcJ$sp()[bp]);
                                 ++ap;
                                 ++bp;
                              } else if (ari < bri) {
                                 bldr.add$mcJ$sp(ari, ci, a.data$mcJ$sp()[ap]);
                                 ++ap;
                              } else {
                                 bldr.add$mcJ$sp(bri, ci, b.data$mcJ$sp()[bp]);
                                 ++bp;
                              }
                           }
                        }

                        var10000 = bldr.result$mcJ$sp(true, true);
                     }

                     return var10000;
                  } else {
                     return CSCMatrix$.MODULE$.zeros(rows, cols, scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());
                  }
               }
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(CSCMatrixExpandedOps.this.op_M_DM_Long_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class), scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class));
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_InPlace_OpSet_Int_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseMatrix b, final CSCMatrix a) {
            int left$macro$1 = a.rows();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.rows == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int left$macro$3 = a.cols();
               int right$macro$4 = b.cols();
               if (left$macro$3 != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.cols == b.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  int rows = a.rows();
                  int cols = a.cols();
                  if (cols != 0 && rows != 0) {
                     b.$colon$eq(BoxesRunTime.boxToInteger(0), HasOps$.MODULE$.dm_s_UpdateOp_Int_OpSet());
                     int ci = 0;

                     int ci1;
                     for(int apStop = a.colPtrs()[0]; ci < cols; ci = ci1) {
                        ci1 = ci + 1;
                        int ap = apStop;

                        for(apStop = a.colPtrs()[ci1]; ap < apStop; ++ap) {
                           int ari = ap < apStop ? a.rowIndices()[ap] : rows;
                           b.update$mcI$sp(ari, ci, a.data$mcI$sp()[ap]);
                        }
                     }

                  }
               }
            }
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_InPlace_OpSet_Double_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseMatrix b, final CSCMatrix a) {
            int left$macro$1 = a.rows();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.rows == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int left$macro$3 = a.cols();
               int right$macro$4 = b.cols();
               if (left$macro$3 != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.cols == b.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  int rows = a.rows();
                  int cols = a.cols();
                  if (cols != 0 && rows != 0) {
                     b.$colon$eq(BoxesRunTime.boxToDouble((double)0.0F), HasOps$.MODULE$.dm_s_UpdateOp_Double_OpSet());
                     int ci = 0;

                     int ci1;
                     for(int apStop = a.colPtrs()[0]; ci < cols; ci = ci1) {
                        ci1 = ci + 1;
                        int ap = apStop;

                        for(apStop = a.colPtrs()[ci1]; ap < apStop; ++ap) {
                           int ari = ap < apStop ? a.rowIndices()[ap] : rows;
                           b.update$mcD$sp(ari, ci, a.data$mcD$sp()[ap]);
                        }
                     }

                  }
               }
            }
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_InPlace_OpSet_Float_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseMatrix b, final CSCMatrix a) {
            int left$macro$1 = a.rows();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.rows == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int left$macro$3 = a.cols();
               int right$macro$4 = b.cols();
               if (left$macro$3 != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.cols == b.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  int rows = a.rows();
                  int cols = a.cols();
                  if (cols != 0 && rows != 0) {
                     b.$colon$eq(BoxesRunTime.boxToFloat(0.0F), HasOps$.MODULE$.dm_s_UpdateOp_Float_OpSet());
                     int ci = 0;

                     int ci1;
                     for(int apStop = a.colPtrs()[0]; ci < cols; ci = ci1) {
                        ci1 = ci + 1;
                        int ap = apStop;

                        for(apStop = a.colPtrs()[ci1]; ap < apStop; ++ap) {
                           int ari = ap < apStop ? a.rowIndices()[ap] : rows;
                           b.update$mcF$sp(ari, ci, a.data$mcF$sp()[ap]);
                        }
                     }

                  }
               }
            }
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_InPlace_OpSet_Long_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseMatrix b, final CSCMatrix a) {
            int left$macro$1 = a.rows();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.rows == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int left$macro$3 = a.cols();
               int right$macro$4 = b.cols();
               if (left$macro$3 != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.cols == b.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  int rows = a.rows();
                  int cols = a.cols();
                  if (cols != 0 && rows != 0) {
                     b.$colon$eq(BoxesRunTime.boxToLong(0L), HasOps$.MODULE$.dm_s_UpdateOp_Long_OpSet());
                     int ci = 0;

                     int ci1;
                     for(int apStop = a.colPtrs()[0]; ci < cols; ci = ci1) {
                        ci1 = ci + 1;
                        int ap = apStop;

                        for(apStop = a.colPtrs()[ci1]; ap < apStop; ++ap) {
                           int ari = ap < apStop ? a.rowIndices()[ap] : rows;
                           b.update$mcJ$sp(ari, ci, a.data$mcJ$sp()[ap]);
                        }
                     }

                  }
               }
            }
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_InPlace_OpAdd_Int_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseMatrix b, final CSCMatrix a) {
            int left$macro$1 = a.rows();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.rows == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int left$macro$3 = a.cols();
               int right$macro$4 = b.cols();
               if (left$macro$3 != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.cols == b.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  int rows = a.rows();
                  int cols = a.cols();
                  if (cols != 0 && rows != 0) {
                     int ci = 0;

                     int ci1;
                     for(int apStop = a.colPtrs()[0]; ci < cols; ci = ci1) {
                        ci1 = ci + 1;
                        int ap = apStop;

                        for(apStop = a.colPtrs()[ci1]; ap < apStop; ++ap) {
                           int ari = ap < apStop ? a.rowIndices()[ap] : rows;
                           b.update$mcI$sp(ari, ci, b.apply$mcI$sp(ari, ci) + a.data$mcI$sp()[ap]);
                        }
                     }

                  }
               }
            }
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_InPlace_OpAdd_Double_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseMatrix b, final CSCMatrix a) {
            int left$macro$1 = a.rows();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.rows == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int left$macro$3 = a.cols();
               int right$macro$4 = b.cols();
               if (left$macro$3 != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.cols == b.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  int rows = a.rows();
                  int cols = a.cols();
                  if (cols != 0 && rows != 0) {
                     int ci = 0;

                     int ci1;
                     for(int apStop = a.colPtrs()[0]; ci < cols; ci = ci1) {
                        ci1 = ci + 1;
                        int ap = apStop;

                        for(apStop = a.colPtrs()[ci1]; ap < apStop; ++ap) {
                           int ari = ap < apStop ? a.rowIndices()[ap] : rows;
                           b.update$mcD$sp(ari, ci, b.apply$mcD$sp(ari, ci) + a.data$mcD$sp()[ap]);
                        }
                     }

                  }
               }
            }
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_InPlace_OpAdd_Float_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseMatrix b, final CSCMatrix a) {
            int left$macro$1 = a.rows();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.rows == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int left$macro$3 = a.cols();
               int right$macro$4 = b.cols();
               if (left$macro$3 != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.cols == b.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  int rows = a.rows();
                  int cols = a.cols();
                  if (cols != 0 && rows != 0) {
                     int ci = 0;

                     int ci1;
                     for(int apStop = a.colPtrs()[0]; ci < cols; ci = ci1) {
                        ci1 = ci + 1;
                        int ap = apStop;

                        for(apStop = a.colPtrs()[ci1]; ap < apStop; ++ap) {
                           int ari = ap < apStop ? a.rowIndices()[ap] : rows;
                           b.update$mcF$sp(ari, ci, b.apply$mcF$sp(ari, ci) + a.data$mcF$sp()[ap]);
                        }
                     }

                  }
               }
            }
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_InPlace_OpAdd_Long_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseMatrix b, final CSCMatrix a) {
            int left$macro$1 = a.rows();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.rows == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int left$macro$3 = a.cols();
               int right$macro$4 = b.cols();
               if (left$macro$3 != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.cols == b.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  int rows = a.rows();
                  int cols = a.cols();
                  if (cols != 0 && rows != 0) {
                     int ci = 0;

                     int ci1;
                     for(int apStop = a.colPtrs()[0]; ci < cols; ci = ci1) {
                        ci1 = ci + 1;
                        int ap = apStop;

                        for(apStop = a.colPtrs()[ci1]; ap < apStop; ++ap) {
                           int ari = ap < apStop ? a.rowIndices()[ap] : rows;
                           b.update$mcJ$sp(ari, ci, b.apply$mcJ$sp(ari, ci) + a.data$mcJ$sp()[ap]);
                        }
                     }

                  }
               }
            }
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_InPlace_OpSub_Int_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseMatrix b, final CSCMatrix a) {
            int left$macro$1 = a.rows();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.rows == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int left$macro$3 = a.cols();
               int right$macro$4 = b.cols();
               if (left$macro$3 != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.cols == b.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  int rows = a.rows();
                  int cols = a.cols();
                  if (cols != 0 && rows != 0) {
                     int ci = 0;

                     int ci1;
                     for(int apStop = a.colPtrs()[0]; ci < cols; ci = ci1) {
                        ci1 = ci + 1;
                        int ap = apStop;

                        for(apStop = a.colPtrs()[ci1]; ap < apStop; ++ap) {
                           int ari = ap < apStop ? a.rowIndices()[ap] : rows;
                           b.update$mcI$sp(ari, ci, b.apply$mcI$sp(ari, ci) - a.data$mcI$sp()[ap]);
                        }
                     }

                  }
               }
            }
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_InPlace_OpSub_Double_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseMatrix b, final CSCMatrix a) {
            int left$macro$1 = a.rows();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.rows == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int left$macro$3 = a.cols();
               int right$macro$4 = b.cols();
               if (left$macro$3 != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.cols == b.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  int rows = a.rows();
                  int cols = a.cols();
                  if (cols != 0 && rows != 0) {
                     int ci = 0;

                     int ci1;
                     for(int apStop = a.colPtrs()[0]; ci < cols; ci = ci1) {
                        ci1 = ci + 1;
                        int ap = apStop;

                        for(apStop = a.colPtrs()[ci1]; ap < apStop; ++ap) {
                           int ari = ap < apStop ? a.rowIndices()[ap] : rows;
                           b.update$mcD$sp(ari, ci, b.apply$mcD$sp(ari, ci) - a.data$mcD$sp()[ap]);
                        }
                     }

                  }
               }
            }
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_InPlace_OpSub_Float_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseMatrix b, final CSCMatrix a) {
            int left$macro$1 = a.rows();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.rows == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int left$macro$3 = a.cols();
               int right$macro$4 = b.cols();
               if (left$macro$3 != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.cols == b.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  int rows = a.rows();
                  int cols = a.cols();
                  if (cols != 0 && rows != 0) {
                     int ci = 0;

                     int ci1;
                     for(int apStop = a.colPtrs()[0]; ci < cols; ci = ci1) {
                        ci1 = ci + 1;
                        int ap = apStop;

                        for(apStop = a.colPtrs()[ci1]; ap < apStop; ++ap) {
                           int ari = ap < apStop ? a.rowIndices()[ap] : rows;
                           b.update$mcF$sp(ari, ci, b.apply$mcF$sp(ari, ci) - a.data$mcF$sp()[ap]);
                        }
                     }

                  }
               }
            }
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_InPlace_OpSub_Long_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseMatrix b, final CSCMatrix a) {
            int left$macro$1 = a.rows();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.rows == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int left$macro$3 = a.cols();
               int right$macro$4 = b.cols();
               if (left$macro$3 != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.cols == b.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  int rows = a.rows();
                  int cols = a.cols();
                  if (cols != 0 && rows != 0) {
                     int ci = 0;

                     int ci1;
                     for(int apStop = a.colPtrs()[0]; ci < cols; ci = ci1) {
                        ci1 = ci + 1;
                        int ap = apStop;

                        for(apStop = a.colPtrs()[ci1]; ap < apStop; ++ap) {
                           int ari = ap < apStop ? a.rowIndices()[ap] : rows;
                           b.update$mcJ$sp(ari, ci, b.apply$mcJ$sp(ari, ci) - a.data$mcJ$sp()[ap]);
                        }
                     }

                  }
               }
            }
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_dm_OpAdd_Int_$eq(new UFunc.UImpl2() {
         // $FF: synthetic field
         private final CSCMatrixExpandedOps $outer;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public DenseMatrix apply(final CSCMatrix a, final DenseMatrix b) {
            int left$macro$1 = a.rows();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.rows == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int left$macro$3 = a.cols();
               int right$macro$4 = b.cols();
               if (left$macro$3 != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.cols == b.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  return (DenseMatrix)b.copy$mcI$sp().$plus$eq(a, this.$outer.dm_csc_InPlace_OpAdd_Int());
               }
            }
         }

         public {
            if (CSCMatrixExpandedOps.this == null) {
               throw null;
            } else {
               this.$outer = CSCMatrixExpandedOps.this;
            }
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_dm_OpAdd_Double_$eq(new UFunc.UImpl2() {
         // $FF: synthetic field
         private final CSCMatrixExpandedOps $outer;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public DenseMatrix apply(final CSCMatrix a, final DenseMatrix b) {
            int left$macro$1 = a.rows();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.rows == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int left$macro$3 = a.cols();
               int right$macro$4 = b.cols();
               if (left$macro$3 != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.cols == b.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  return (DenseMatrix)b.copy$mcD$sp().$plus$eq(a, this.$outer.dm_csc_InPlace_OpAdd_Double());
               }
            }
         }

         public {
            if (CSCMatrixExpandedOps.this == null) {
               throw null;
            } else {
               this.$outer = CSCMatrixExpandedOps.this;
            }
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_dm_OpAdd_Float_$eq(new UFunc.UImpl2() {
         // $FF: synthetic field
         private final CSCMatrixExpandedOps $outer;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public DenseMatrix apply(final CSCMatrix a, final DenseMatrix b) {
            int left$macro$1 = a.rows();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.rows == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int left$macro$3 = a.cols();
               int right$macro$4 = b.cols();
               if (left$macro$3 != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.cols == b.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  return (DenseMatrix)b.copy$mcF$sp().$plus$eq(a, this.$outer.dm_csc_InPlace_OpAdd_Float());
               }
            }
         }

         public {
            if (CSCMatrixExpandedOps.this == null) {
               throw null;
            } else {
               this.$outer = CSCMatrixExpandedOps.this;
            }
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_dm_OpAdd_Long_$eq(new UFunc.UImpl2() {
         // $FF: synthetic field
         private final CSCMatrixExpandedOps $outer;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public DenseMatrix apply(final CSCMatrix a, final DenseMatrix b) {
            int left$macro$1 = a.rows();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.rows == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int left$macro$3 = a.cols();
               int right$macro$4 = b.cols();
               if (left$macro$3 != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.cols == b.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  return (DenseMatrix)b.copy$mcJ$sp().$plus$eq(a, this.$outer.dm_csc_InPlace_OpAdd_Long());
               }
            }
         }

         public {
            if (CSCMatrixExpandedOps.this == null) {
               throw null;
            } else {
               this.$outer = CSCMatrixExpandedOps.this;
            }
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_OpAdd_Int_$eq(new UFunc.UImpl2() {
         // $FF: synthetic field
         private final CSCMatrixExpandedOps $outer;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public DenseMatrix apply(final DenseMatrix a, final CSCMatrix b) {
            return (DenseMatrix)b.$plus(a, this.$outer.csc_dm_OpAdd_Int());
         }

         public {
            if (CSCMatrixExpandedOps.this == null) {
               throw null;
            } else {
               this.$outer = CSCMatrixExpandedOps.this;
            }
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_OpAdd_Double_$eq(new UFunc.UImpl2() {
         // $FF: synthetic field
         private final CSCMatrixExpandedOps $outer;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public DenseMatrix apply(final DenseMatrix a, final CSCMatrix b) {
            return (DenseMatrix)b.$plus(a, this.$outer.csc_dm_OpAdd_Double());
         }

         public {
            if (CSCMatrixExpandedOps.this == null) {
               throw null;
            } else {
               this.$outer = CSCMatrixExpandedOps.this;
            }
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_OpAdd_Float_$eq(new UFunc.UImpl2() {
         // $FF: synthetic field
         private final CSCMatrixExpandedOps $outer;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public DenseMatrix apply(final DenseMatrix a, final CSCMatrix b) {
            return (DenseMatrix)b.$plus(a, this.$outer.csc_dm_OpAdd_Float());
         }

         public {
            if (CSCMatrixExpandedOps.this == null) {
               throw null;
            } else {
               this.$outer = CSCMatrixExpandedOps.this;
            }
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_OpAdd_Long_$eq(new UFunc.UImpl2() {
         // $FF: synthetic field
         private final CSCMatrixExpandedOps $outer;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public DenseMatrix apply(final DenseMatrix a, final CSCMatrix b) {
            return (DenseMatrix)b.$plus(a, this.$outer.csc_dm_OpAdd_Long());
         }

         public {
            if (CSCMatrixExpandedOps.this == null) {
               throw null;
            } else {
               this.$outer = CSCMatrixExpandedOps.this;
            }
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_OpSub_Int_$eq(new UFunc.UImpl2() {
         // $FF: synthetic field
         private final CSCMatrixExpandedOps $outer;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public DenseMatrix apply(final DenseMatrix b, final CSCMatrix a) {
            return (DenseMatrix)b.copy$mcI$sp().$minus$eq(a, this.$outer.dm_csc_InPlace_OpSub_Int());
         }

         public {
            if (CSCMatrixExpandedOps.this == null) {
               throw null;
            } else {
               this.$outer = CSCMatrixExpandedOps.this;
            }
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_OpSub_Double_$eq(new UFunc.UImpl2() {
         // $FF: synthetic field
         private final CSCMatrixExpandedOps $outer;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public DenseMatrix apply(final DenseMatrix b, final CSCMatrix a) {
            return (DenseMatrix)b.copy$mcD$sp().$minus$eq(a, this.$outer.dm_csc_InPlace_OpSub_Double());
         }

         public {
            if (CSCMatrixExpandedOps.this == null) {
               throw null;
            } else {
               this.$outer = CSCMatrixExpandedOps.this;
            }
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_OpSub_Float_$eq(new UFunc.UImpl2() {
         // $FF: synthetic field
         private final CSCMatrixExpandedOps $outer;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public DenseMatrix apply(final DenseMatrix b, final CSCMatrix a) {
            return (DenseMatrix)b.copy$mcF$sp().$minus$eq(a, this.$outer.dm_csc_InPlace_OpSub_Float());
         }

         public {
            if (CSCMatrixExpandedOps.this == null) {
               throw null;
            } else {
               this.$outer = CSCMatrixExpandedOps.this;
            }
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$dm_csc_OpSub_Long_$eq(new UFunc.UImpl2() {
         // $FF: synthetic field
         private final CSCMatrixExpandedOps $outer;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public DenseMatrix apply(final DenseMatrix b, final CSCMatrix a) {
            return (DenseMatrix)b.copy$mcJ$sp().$minus$eq(a, this.$outer.dm_csc_InPlace_OpSub_Long());
         }

         public {
            if (CSCMatrixExpandedOps.this == null) {
               throw null;
            } else {
               this.$outer = CSCMatrixExpandedOps.this;
            }
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_dm_OpSub_Int_$eq(new UFunc.UImpl2() {
         // $FF: synthetic field
         private final CSCMatrixExpandedOps $outer;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public DenseMatrix apply(final CSCMatrix a, final DenseMatrix b) {
            return (DenseMatrix)((NumericOps)b.unary_$minus(this.$outer.impl_OpNeg_T_Generic_from_OpMulScalar(DenseMatrix$.MODULE$.scalarOf(), Ring$.MODULE$.ringInt(), this.$outer.pureFromUpdate(HasOps$.MODULE$.dm_s_UpdateOp_Int_OpMulScalar(), HasOps$.MODULE$.canCopy_DM(scala.reflect.ClassTag..MODULE$.Int()))))).$plus$eq(a, this.$outer.dm_csc_InPlace_OpAdd_Int());
         }

         public {
            if (CSCMatrixExpandedOps.this == null) {
               throw null;
            } else {
               this.$outer = CSCMatrixExpandedOps.this;
            }
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_dm_OpSub_Double_$eq(new UFunc.UImpl2() {
         // $FF: synthetic field
         private final CSCMatrixExpandedOps $outer;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public DenseMatrix apply(final CSCMatrix a, final DenseMatrix b) {
            return (DenseMatrix)((NumericOps)b.unary_$minus(this.$outer.impl_OpNeg_T_Generic_from_OpMulScalar(DenseMatrix$.MODULE$.scalarOf(), Ring$.MODULE$.ringD(), this.$outer.pureFromUpdate(HasOps$.MODULE$.dm_s_UpdateOp_Double_OpMulScalar(), HasOps$.MODULE$.canCopy_DM(scala.reflect.ClassTag..MODULE$.Double()))))).$plus$eq(a, this.$outer.dm_csc_InPlace_OpAdd_Double());
         }

         public {
            if (CSCMatrixExpandedOps.this == null) {
               throw null;
            } else {
               this.$outer = CSCMatrixExpandedOps.this;
            }
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_dm_OpSub_Float_$eq(new UFunc.UImpl2() {
         // $FF: synthetic field
         private final CSCMatrixExpandedOps $outer;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public DenseMatrix apply(final CSCMatrix a, final DenseMatrix b) {
            return (DenseMatrix)((NumericOps)b.unary_$minus(this.$outer.impl_OpNeg_T_Generic_from_OpMulScalar(DenseMatrix$.MODULE$.scalarOf(), Ring$.MODULE$.ringFloat(), this.$outer.pureFromUpdate(HasOps$.MODULE$.dm_s_UpdateOp_Float_OpMulScalar(), HasOps$.MODULE$.canCopy_DM(scala.reflect.ClassTag..MODULE$.Float()))))).$plus$eq(a, this.$outer.dm_csc_InPlace_OpAdd_Float());
         }

         public {
            if (CSCMatrixExpandedOps.this == null) {
               throw null;
            } else {
               this.$outer = CSCMatrixExpandedOps.this;
            }
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_dm_OpSub_Long_$eq(new UFunc.UImpl2() {
         // $FF: synthetic field
         private final CSCMatrixExpandedOps $outer;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public DenseMatrix apply(final CSCMatrix a, final DenseMatrix b) {
            return (DenseMatrix)((NumericOps)b.unary_$minus(this.$outer.impl_OpNeg_T_Generic_from_OpMulScalar(DenseMatrix$.MODULE$.scalarOf(), Ring$.MODULE$.ringLong(), this.$outer.pureFromUpdate(HasOps$.MODULE$.dm_s_UpdateOp_Long_OpMulScalar(), HasOps$.MODULE$.canCopy_DM(scala.reflect.ClassTag..MODULE$.Long()))))).$plus$eq(a, this.$outer.dm_csc_InPlace_OpAdd_Long());
         }

         public {
            if (CSCMatrixExpandedOps.this == null) {
               throw null;
            } else {
               this.$outer = CSCMatrixExpandedOps.this;
            }
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_OpMulScalar_Int_$eq(new UFunc.UImpl2() {
         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public CSCMatrix apply(final CSCMatrix a, final CSCMatrix b) {
            int rows = a.rows();
            int cols = a.cols();
            int right$macro$2 = b.rows();
            if (rows != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of rows!: ").append("rows == b.rows (").append(rows).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int right$macro$4 = b.cols();
               if (cols != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of cols!: ").append("cols == b.cols (").append(cols).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else if (cols != 0 && rows != 0) {
                  CSCMatrix var10000;
                  if (a.activeSize() != 0 && b.activeSize() != 0) {
                     CSCMatrix.Builder res = new CSCMatrix$Builder$mcI$sp(rows, cols, scala.math.package..MODULE$.min(a.activeSize(), b.activeSize()), scala.reflect.ClassTag..MODULE$.Int(), Semiring$.MODULE$.semiringInt(), Zero$.MODULE$.IntZero());
                     int ci = 0;
                     int apStop = a.colPtrs()[0];

                     int ci1;
                     for(int bpStop = b.colPtrs()[0]; ci < cols; ci = ci1) {
                        ci1 = ci + 1;
                        int ap = apStop;
                        int bp = bpStop;
                        apStop = a.colPtrs()[ci1];
                        bpStop = b.colPtrs()[ci1];

                        while(ap < apStop || bp < bpStop) {
                           int ari = ap < apStop ? a.rowIndices()[ap] : rows;
                           int bri = bp < bpStop ? b.rowIndices()[bp] : rows;
                           if (ari == bri) {
                              res.add$mcI$sp(ari, ci, a.data$mcI$sp()[ap] * b.data$mcI$sp()[bp]);
                              ++ap;
                              ++bp;
                           } else if (ari < bri) {
                              ++ap;
                           } else {
                              ++bp;
                           }
                        }
                     }

                     var10000 = res.result$mcI$sp(true, true);
                  } else {
                     var10000 = CSCMatrix$.MODULE$.zeros(rows, cols, scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());
                  }

                  return var10000;
               } else {
                  return CSCMatrix$.MODULE$.zeros(rows, cols, scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());
               }
            }
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_OpMulScalar_Double_$eq(new UFunc.UImpl2() {
         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public CSCMatrix apply(final CSCMatrix a, final CSCMatrix b) {
            int rows = a.rows();
            int cols = a.cols();
            int right$macro$2 = b.rows();
            if (rows != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of rows!: ").append("rows == b.rows (").append(rows).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int right$macro$4 = b.cols();
               if (cols != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of cols!: ").append("cols == b.cols (").append(cols).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else if (cols != 0 && rows != 0) {
                  CSCMatrix var10000;
                  if (a.activeSize() != 0 && b.activeSize() != 0) {
                     CSCMatrix.Builder res = new CSCMatrix$Builder$mcD$sp(rows, cols, scala.math.package..MODULE$.min(a.activeSize(), b.activeSize()), scala.reflect.ClassTag..MODULE$.Double(), Semiring$.MODULE$.semiringD(), Zero$.MODULE$.DoubleZero());
                     int ci = 0;
                     int apStop = a.colPtrs()[0];

                     int ci1;
                     for(int bpStop = b.colPtrs()[0]; ci < cols; ci = ci1) {
                        ci1 = ci + 1;
                        int ap = apStop;
                        int bp = bpStop;
                        apStop = a.colPtrs()[ci1];
                        bpStop = b.colPtrs()[ci1];

                        while(ap < apStop || bp < bpStop) {
                           int ari = ap < apStop ? a.rowIndices()[ap] : rows;
                           int bri = bp < bpStop ? b.rowIndices()[bp] : rows;
                           if (ari == bri) {
                              res.add$mcD$sp(ari, ci, a.data$mcD$sp()[ap] * b.data$mcD$sp()[bp]);
                              ++ap;
                              ++bp;
                           } else if (ari < bri) {
                              ++ap;
                           } else {
                              ++bp;
                           }
                        }
                     }

                     var10000 = res.result$mcD$sp(true, true);
                  } else {
                     var10000 = CSCMatrix$.MODULE$.zeros(rows, cols, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
                  }

                  return var10000;
               } else {
                  return CSCMatrix$.MODULE$.zeros(rows, cols, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
               }
            }
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_OpMulScalar_Float_$eq(new UFunc.UImpl2() {
         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public CSCMatrix apply(final CSCMatrix a, final CSCMatrix b) {
            int rows = a.rows();
            int cols = a.cols();
            int right$macro$2 = b.rows();
            if (rows != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of rows!: ").append("rows == b.rows (").append(rows).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int right$macro$4 = b.cols();
               if (cols != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of cols!: ").append("cols == b.cols (").append(cols).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else if (cols != 0 && rows != 0) {
                  CSCMatrix var10000;
                  if (a.activeSize() != 0 && b.activeSize() != 0) {
                     CSCMatrix.Builder res = new CSCMatrix$Builder$mcF$sp(rows, cols, scala.math.package..MODULE$.min(a.activeSize(), b.activeSize()), scala.reflect.ClassTag..MODULE$.Float(), Semiring$.MODULE$.semiringFloat(), Zero$.MODULE$.FloatZero());
                     int ci = 0;
                     int apStop = a.colPtrs()[0];

                     int ci1;
                     for(int bpStop = b.colPtrs()[0]; ci < cols; ci = ci1) {
                        ci1 = ci + 1;
                        int ap = apStop;
                        int bp = bpStop;
                        apStop = a.colPtrs()[ci1];
                        bpStop = b.colPtrs()[ci1];

                        while(ap < apStop || bp < bpStop) {
                           int ari = ap < apStop ? a.rowIndices()[ap] : rows;
                           int bri = bp < bpStop ? b.rowIndices()[bp] : rows;
                           if (ari == bri) {
                              res.add$mcF$sp(ari, ci, a.data$mcF$sp()[ap] * b.data$mcF$sp()[bp]);
                              ++ap;
                              ++bp;
                           } else if (ari < bri) {
                              ++ap;
                           } else {
                              ++bp;
                           }
                        }
                     }

                     var10000 = res.result$mcF$sp(true, true);
                  } else {
                     var10000 = CSCMatrix$.MODULE$.zeros(rows, cols, scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());
                  }

                  return var10000;
               } else {
                  return CSCMatrix$.MODULE$.zeros(rows, cols, scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());
               }
            }
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_OpMulScalar_Long_$eq(new UFunc.UImpl2() {
         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public CSCMatrix apply(final CSCMatrix a, final CSCMatrix b) {
            int rows = a.rows();
            int cols = a.cols();
            int right$macro$2 = b.rows();
            if (rows != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of rows!: ").append("rows == b.rows (").append(rows).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int right$macro$4 = b.cols();
               if (cols != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of cols!: ").append("cols == b.cols (").append(cols).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else if (cols != 0 && rows != 0) {
                  CSCMatrix var10000;
                  if (a.activeSize() != 0 && b.activeSize() != 0) {
                     CSCMatrix.Builder res = new CSCMatrix$Builder$mcJ$sp(rows, cols, scala.math.package..MODULE$.min(a.activeSize(), b.activeSize()), scala.reflect.ClassTag..MODULE$.Long(), Semiring$.MODULE$.semiringLong(), Zero$.MODULE$.LongZero());
                     int ci = 0;
                     int apStop = a.colPtrs()[0];

                     int ci1;
                     for(int bpStop = b.colPtrs()[0]; ci < cols; ci = ci1) {
                        ci1 = ci + 1;
                        int ap = apStop;
                        int bp = bpStop;
                        apStop = a.colPtrs()[ci1];
                        bpStop = b.colPtrs()[ci1];

                        while(ap < apStop || bp < bpStop) {
                           int ari = ap < apStop ? a.rowIndices()[ap] : rows;
                           int bri = bp < bpStop ? b.rowIndices()[bp] : rows;
                           if (ari == bri) {
                              res.add$mcJ$sp(ari, ci, a.data$mcJ$sp()[ap] * b.data$mcJ$sp()[bp]);
                              ++ap;
                              ++bp;
                           } else if (ari < bri) {
                              ++ap;
                           } else {
                              ++bp;
                           }
                        }
                     }

                     var10000 = res.result$mcJ$sp(true, true);
                  } else {
                     var10000 = CSCMatrix$.MODULE$.zeros(rows, cols, scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());
                  }

                  return var10000;
               } else {
                  return CSCMatrix$.MODULE$.zeros(rows, cols, scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());
               }
            }
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_OpSub_Int_$eq(new UFunc.UImpl2() {
         // $FF: synthetic field
         private final CSCMatrixExpandedOps $outer;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public CSCMatrix apply(final CSCMatrix a, final CSCMatrix b) {
            int left$macro$1 = a.rows();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.rows == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int left$macro$3 = a.cols();
               int right$macro$4 = b.cols();
               if (left$macro$3 != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.cols == b.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  int rows = a.rows();
                  int cols = a.cols();
                  if (cols != 0 && rows != 0) {
                     CSCMatrix var10000;
                     if (a.activeSize() == 0) {
                        var10000 = (CSCMatrix)b.unary_$minus(this.$outer.csc_OpNeg_Int());
                     } else if (b.activeSize() == 0) {
                        var10000 = a.copy$mcI$sp();
                     } else {
                        CSCMatrix.Builder bldr = new CSCMatrix$Builder$mcI$sp(rows, cols, scala.math.package..MODULE$.max(a.activeSize(), b.activeSize()), scala.reflect.ClassTag..MODULE$.Int(), Semiring$.MODULE$.semiringInt(), Zero$.MODULE$.IntZero());
                        int ci = 0;
                        int apStop = a.colPtrs()[0];

                        int ci1;
                        for(int bpStop = b.colPtrs()[0]; ci < cols; ci = ci1) {
                           ci1 = ci + 1;
                           int ap = apStop;
                           int bp = bpStop;
                           apStop = a.colPtrs()[ci1];
                           bpStop = b.colPtrs()[ci1];

                           while(ap < apStop || bp < bpStop) {
                              int ari = ap < apStop ? a.rowIndices()[ap] : rows;
                              int bri = bp < bpStop ? b.rowIndices()[bp] : rows;
                              if (ari == bri) {
                                 int v = a.data$mcI$sp()[ap] - b.data$mcI$sp()[bp];
                                 bldr.add$mcI$sp(ari, ci, v);
                                 ++ap;
                                 ++bp;
                              } else if (ari < bri) {
                                 bldr.add$mcI$sp(ari, ci, a.data$mcI$sp()[ap]);
                                 ++ap;
                              } else {
                                 bldr.add$mcI$sp(bri, ci, -b.data$mcI$sp()[bp]);
                                 ++bp;
                              }
                           }
                        }

                        var10000 = bldr.result$mcI$sp(true, true);
                     }

                     return var10000;
                  } else {
                     return CSCMatrix$.MODULE$.zeros(rows, cols, scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());
                  }
               }
            }
         }

         public {
            if (CSCMatrixExpandedOps.this == null) {
               throw null;
            } else {
               this.$outer = CSCMatrixExpandedOps.this;
            }
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_OpSub_Double_$eq(new UFunc.UImpl2() {
         // $FF: synthetic field
         private final CSCMatrixExpandedOps $outer;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public CSCMatrix apply(final CSCMatrix a, final CSCMatrix b) {
            int left$macro$1 = a.rows();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.rows == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int left$macro$3 = a.cols();
               int right$macro$4 = b.cols();
               if (left$macro$3 != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.cols == b.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  int rows = a.rows();
                  int cols = a.cols();
                  if (cols != 0 && rows != 0) {
                     CSCMatrix var10000;
                     if (a.activeSize() == 0) {
                        var10000 = (CSCMatrix)b.unary_$minus(this.$outer.csc_OpNeg_Double());
                     } else if (b.activeSize() == 0) {
                        var10000 = a.copy$mcD$sp();
                     } else {
                        CSCMatrix.Builder bldr = new CSCMatrix$Builder$mcD$sp(rows, cols, scala.math.package..MODULE$.max(a.activeSize(), b.activeSize()), scala.reflect.ClassTag..MODULE$.Double(), Semiring$.MODULE$.semiringD(), Zero$.MODULE$.DoubleZero());
                        int ci = 0;
                        int apStop = a.colPtrs()[0];

                        int ci1;
                        for(int bpStop = b.colPtrs()[0]; ci < cols; ci = ci1) {
                           ci1 = ci + 1;
                           int ap = apStop;
                           int bp = bpStop;
                           apStop = a.colPtrs()[ci1];
                           bpStop = b.colPtrs()[ci1];

                           while(ap < apStop || bp < bpStop) {
                              int ari = ap < apStop ? a.rowIndices()[ap] : rows;
                              int bri = bp < bpStop ? b.rowIndices()[bp] : rows;
                              if (ari == bri) {
                                 double v = a.data$mcD$sp()[ap] - b.data$mcD$sp()[bp];
                                 bldr.add$mcD$sp(ari, ci, v);
                                 ++ap;
                                 ++bp;
                              } else if (ari < bri) {
                                 bldr.add$mcD$sp(ari, ci, a.data$mcD$sp()[ap]);
                                 ++ap;
                              } else {
                                 bldr.add$mcD$sp(bri, ci, -b.data$mcD$sp()[bp]);
                                 ++bp;
                              }
                           }
                        }

                        var10000 = bldr.result$mcD$sp(true, true);
                     }

                     return var10000;
                  } else {
                     return CSCMatrix$.MODULE$.zeros(rows, cols, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
                  }
               }
            }
         }

         public {
            if (CSCMatrixExpandedOps.this == null) {
               throw null;
            } else {
               this.$outer = CSCMatrixExpandedOps.this;
            }
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_OpSub_Float_$eq(new UFunc.UImpl2() {
         // $FF: synthetic field
         private final CSCMatrixExpandedOps $outer;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public CSCMatrix apply(final CSCMatrix a, final CSCMatrix b) {
            int left$macro$1 = a.rows();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.rows == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int left$macro$3 = a.cols();
               int right$macro$4 = b.cols();
               if (left$macro$3 != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.cols == b.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  int rows = a.rows();
                  int cols = a.cols();
                  if (cols != 0 && rows != 0) {
                     CSCMatrix var10000;
                     if (a.activeSize() == 0) {
                        var10000 = (CSCMatrix)b.unary_$minus(this.$outer.csc_OpNeg_Float());
                     } else if (b.activeSize() == 0) {
                        var10000 = a.copy$mcF$sp();
                     } else {
                        CSCMatrix.Builder bldr = new CSCMatrix$Builder$mcF$sp(rows, cols, scala.math.package..MODULE$.max(a.activeSize(), b.activeSize()), scala.reflect.ClassTag..MODULE$.Float(), Semiring$.MODULE$.semiringFloat(), Zero$.MODULE$.FloatZero());
                        int ci = 0;
                        int apStop = a.colPtrs()[0];

                        int ci1;
                        for(int bpStop = b.colPtrs()[0]; ci < cols; ci = ci1) {
                           ci1 = ci + 1;
                           int ap = apStop;
                           int bp = bpStop;
                           apStop = a.colPtrs()[ci1];
                           bpStop = b.colPtrs()[ci1];

                           while(ap < apStop || bp < bpStop) {
                              int ari = ap < apStop ? a.rowIndices()[ap] : rows;
                              int bri = bp < bpStop ? b.rowIndices()[bp] : rows;
                              if (ari == bri) {
                                 float v = a.data$mcF$sp()[ap] - b.data$mcF$sp()[bp];
                                 bldr.add$mcF$sp(ari, ci, v);
                                 ++ap;
                                 ++bp;
                              } else if (ari < bri) {
                                 bldr.add$mcF$sp(ari, ci, a.data$mcF$sp()[ap]);
                                 ++ap;
                              } else {
                                 bldr.add$mcF$sp(bri, ci, -b.data$mcF$sp()[bp]);
                                 ++bp;
                              }
                           }
                        }

                        var10000 = bldr.result$mcF$sp(true, true);
                     }

                     return var10000;
                  } else {
                     return CSCMatrix$.MODULE$.zeros(rows, cols, scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());
                  }
               }
            }
         }

         public {
            if (CSCMatrixExpandedOps.this == null) {
               throw null;
            } else {
               this.$outer = CSCMatrixExpandedOps.this;
            }
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_OpSub_Long_$eq(new UFunc.UImpl2() {
         // $FF: synthetic field
         private final CSCMatrixExpandedOps $outer;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public CSCMatrix apply(final CSCMatrix a, final CSCMatrix b) {
            int left$macro$1 = a.rows();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.rows == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int left$macro$3 = a.cols();
               int right$macro$4 = b.cols();
               if (left$macro$3 != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.cols == b.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  int rows = a.rows();
                  int cols = a.cols();
                  if (cols != 0 && rows != 0) {
                     CSCMatrix var10000;
                     if (a.activeSize() == 0) {
                        var10000 = (CSCMatrix)b.unary_$minus(this.$outer.csc_OpNeg_Long());
                     } else if (b.activeSize() == 0) {
                        var10000 = a.copy$mcJ$sp();
                     } else {
                        CSCMatrix.Builder bldr = new CSCMatrix$Builder$mcJ$sp(rows, cols, scala.math.package..MODULE$.max(a.activeSize(), b.activeSize()), scala.reflect.ClassTag..MODULE$.Long(), Semiring$.MODULE$.semiringLong(), Zero$.MODULE$.LongZero());
                        int ci = 0;
                        int apStop = a.colPtrs()[0];

                        int ci1;
                        for(int bpStop = b.colPtrs()[0]; ci < cols; ci = ci1) {
                           ci1 = ci + 1;
                           int ap = apStop;
                           int bp = bpStop;
                           apStop = a.colPtrs()[ci1];
                           bpStop = b.colPtrs()[ci1];

                           while(ap < apStop || bp < bpStop) {
                              int ari = ap < apStop ? a.rowIndices()[ap] : rows;
                              int bri = bp < bpStop ? b.rowIndices()[bp] : rows;
                              if (ari == bri) {
                                 long v = a.data$mcJ$sp()[ap] - b.data$mcJ$sp()[bp];
                                 bldr.add$mcJ$sp(ari, ci, v);
                                 ++ap;
                                 ++bp;
                              } else if (ari < bri) {
                                 bldr.add$mcJ$sp(ari, ci, a.data$mcJ$sp()[ap]);
                                 ++ap;
                              } else {
                                 bldr.add$mcJ$sp(bri, ci, -b.data$mcJ$sp()[bp]);
                                 ++bp;
                              }
                           }
                        }

                        var10000 = bldr.result$mcJ$sp(true, true);
                     }

                     return var10000;
                  } else {
                     return CSCMatrix$.MODULE$.zeros(rows, cols, scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());
                  }
               }
            }
         }

         public {
            if (CSCMatrixExpandedOps.this == null) {
               throw null;
            } else {
               this.$outer = CSCMatrixExpandedOps.this;
            }
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSCT_T_eq_CSCT_Int_OpMulScalar_$eq(new UFunc.UImpl2() {
         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public CSCMatrix apply(final CSCMatrix a, final int b) {
            if (b == 0) {
               return CSCMatrix$.MODULE$.zeros(a.rows(), a.cols(), scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());
            } else {
               int[] data = (int[]).MODULE$.tabulate(a.data$mcI$sp().length, (JFunction1.mcII.sp)(i) -> a.data$mcI$sp()[i] * b, scala.reflect.ClassTag..MODULE$.Int());
               return new CSCMatrix$mcI$sp(data, a.rows(), a.cols(), Arrays.copyOf(a.colPtrs(), a.colPtrs().length), a.activeSize(), Arrays.copyOf(a.rowIndices(), a.rowIndices().length), Zero$.MODULE$.IntZero());
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(CSCMatrixExpandedOps.this.op_M_S_Int_OpMulScalar())).register(this, scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class), scala.reflect.ClassTag..MODULE$.Int());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSCT_T_eq_CSCT_Double_OpMulScalar_$eq(new UFunc.UImpl2() {
         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public CSCMatrix apply(final CSCMatrix a, final double b) {
            if (b == (double)0.0F) {
               return CSCMatrix$.MODULE$.zeros(a.rows(), a.cols(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            } else {
               double[] data = (double[]).MODULE$.tabulate(a.data$mcD$sp().length, (JFunction1.mcDI.sp)(i) -> a.data$mcD$sp()[i] * b, scala.reflect.ClassTag..MODULE$.Double());
               return new CSCMatrix$mcD$sp(data, a.rows(), a.cols(), Arrays.copyOf(a.colPtrs(), a.colPtrs().length), a.activeSize(), Arrays.copyOf(a.rowIndices(), a.rowIndices().length), Zero$.MODULE$.DoubleZero());
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(CSCMatrixExpandedOps.this.op_M_S_Double_OpMulScalar())).register(this, scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class), scala.reflect.ClassTag..MODULE$.Double());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSCT_T_eq_CSCT_Float_OpMulScalar_$eq(new UFunc.UImpl2() {
         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public CSCMatrix apply(final CSCMatrix a, final float b) {
            if (b == 0.0F) {
               return CSCMatrix$.MODULE$.zeros(a.rows(), a.cols(), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());
            } else {
               float[] data = (float[]).MODULE$.tabulate(a.data$mcF$sp().length, (JFunction1.mcFI.sp)(i) -> a.data$mcF$sp()[i] * b, scala.reflect.ClassTag..MODULE$.Float());
               return new CSCMatrix$mcF$sp(data, a.rows(), a.cols(), Arrays.copyOf(a.colPtrs(), a.colPtrs().length), a.activeSize(), Arrays.copyOf(a.rowIndices(), a.rowIndices().length), Zero$.MODULE$.FloatZero());
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(CSCMatrixExpandedOps.this.op_M_S_Float_OpMulScalar())).register(this, scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class), scala.reflect.ClassTag..MODULE$.Float());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSCT_T_eq_CSCT_Long_OpMulScalar_$eq(new UFunc.UImpl2() {
         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public CSCMatrix apply(final CSCMatrix a, final long b) {
            if (b == 0L) {
               return CSCMatrix$.MODULE$.zeros(a.rows(), a.cols(), scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());
            } else {
               long[] data = (long[]).MODULE$.tabulate(a.data$mcJ$sp().length, (JFunction1.mcJI.sp)(i) -> a.data$mcJ$sp()[i] * b, scala.reflect.ClassTag..MODULE$.Long());
               return new CSCMatrix$mcJ$sp(data, a.rows(), a.cols(), Arrays.copyOf(a.colPtrs(), a.colPtrs().length), a.activeSize(), Arrays.copyOf(a.rowIndices(), a.rowIndices().length), Zero$.MODULE$.LongZero());
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(CSCMatrixExpandedOps.this.op_M_S_Long_OpMulScalar())).register(this, scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class), scala.reflect.ClassTag..MODULE$.Long());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSCT_T_eq_CSCT_Int_OpMulMatrix_$eq(new UFunc.UImpl2() {
         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public CSCMatrix apply(final CSCMatrix a, final int b) {
            if (b == 0) {
               return CSCMatrix$.MODULE$.zeros(a.rows(), a.cols(), scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());
            } else {
               int[] data = (int[]).MODULE$.tabulate(a.data$mcI$sp().length, (JFunction1.mcII.sp)(i) -> a.data$mcI$sp()[i] * b, scala.reflect.ClassTag..MODULE$.Int());
               return new CSCMatrix$mcI$sp(data, a.rows(), a.cols(), Arrays.copyOf(a.colPtrs(), a.colPtrs().length), a.activeSize(), Arrays.copyOf(a.rowIndices(), a.rowIndices().length), Zero$.MODULE$.IntZero());
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(CSCMatrixExpandedOps.this.op_M_S_Int_OpMulMatrix())).register(this, scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class), scala.reflect.ClassTag..MODULE$.Int());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSCT_T_eq_CSCT_Double_OpMulMatrix_$eq(new UFunc.UImpl2() {
         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public CSCMatrix apply(final CSCMatrix a, final double b) {
            if (b == (double)0.0F) {
               return CSCMatrix$.MODULE$.zeros(a.rows(), a.cols(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            } else {
               double[] data = (double[]).MODULE$.tabulate(a.data$mcD$sp().length, (JFunction1.mcDI.sp)(i) -> a.data$mcD$sp()[i] * b, scala.reflect.ClassTag..MODULE$.Double());
               return new CSCMatrix$mcD$sp(data, a.rows(), a.cols(), Arrays.copyOf(a.colPtrs(), a.colPtrs().length), a.activeSize(), Arrays.copyOf(a.rowIndices(), a.rowIndices().length), Zero$.MODULE$.DoubleZero());
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(CSCMatrixExpandedOps.this.op_M_S_Double_OpMulMatrix())).register(this, scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class), scala.reflect.ClassTag..MODULE$.Double());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSCT_T_eq_CSCT_Float_OpMulMatrix_$eq(new UFunc.UImpl2() {
         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public CSCMatrix apply(final CSCMatrix a, final float b) {
            if (b == 0.0F) {
               return CSCMatrix$.MODULE$.zeros(a.rows(), a.cols(), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());
            } else {
               float[] data = (float[]).MODULE$.tabulate(a.data$mcF$sp().length, (JFunction1.mcFI.sp)(i) -> a.data$mcF$sp()[i] * b, scala.reflect.ClassTag..MODULE$.Float());
               return new CSCMatrix$mcF$sp(data, a.rows(), a.cols(), Arrays.copyOf(a.colPtrs(), a.colPtrs().length), a.activeSize(), Arrays.copyOf(a.rowIndices(), a.rowIndices().length), Zero$.MODULE$.FloatZero());
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(CSCMatrixExpandedOps.this.op_M_S_Float_OpMulMatrix())).register(this, scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class), scala.reflect.ClassTag..MODULE$.Float());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSCT_T_eq_CSCT_Long_OpMulMatrix_$eq(new UFunc.UImpl2() {
         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public CSCMatrix apply(final CSCMatrix a, final long b) {
            if (b == 0L) {
               return CSCMatrix$.MODULE$.zeros(a.rows(), a.cols(), scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());
            } else {
               long[] data = (long[]).MODULE$.tabulate(a.data$mcJ$sp().length, (JFunction1.mcJI.sp)(i) -> a.data$mcJ$sp()[i] * b, scala.reflect.ClassTag..MODULE$.Long());
               return new CSCMatrix$mcJ$sp(data, a.rows(), a.cols(), Arrays.copyOf(a.colPtrs(), a.colPtrs().length), a.activeSize(), Arrays.copyOf(a.rowIndices(), a.rowIndices().length), Zero$.MODULE$.LongZero());
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(CSCMatrixExpandedOps.this.op_M_S_Long_OpMulMatrix())).register(this, scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class), scala.reflect.ClassTag..MODULE$.Long());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_V_Int_$eq(new BinaryRegistry() {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public DenseVector bindingMissing(final CSCMatrix a, final Vector b) {
            int left$macro$1 = a.cols();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(66)).append("requirement failed: Dimension Mismatch!: ").append("a.cols == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector res = DenseVector$.MODULE$.zeros$mIc$sp(a.rows(), scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());

               for(int c = 0; c < a.cols(); ++c) {
                  int rr = a.colPtrs()[c];

                  for(int rrlast = a.colPtrs()[c + 1]; rr < rrlast; ++rr) {
                     int r = a.rowIndices()[rr];
                     res.update$mcI$sp(r, res.apply$mcI$sp(r) + a.data$mcI$sp()[rr] * b.apply$mcII$sp(c));
                  }
               }

               return res;
            }
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(CSCMatrixExpandedOps.this.op_M_V_Int())).register(this, scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_V_Float_$eq(new BinaryRegistry() {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public DenseVector bindingMissing(final CSCMatrix a, final Vector b) {
            int left$macro$1 = a.cols();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(66)).append("requirement failed: Dimension Mismatch!: ").append("a.cols == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector res = DenseVector$.MODULE$.zeros$mFc$sp(a.rows(), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());

               for(int c = 0; c < a.cols(); ++c) {
                  int rr = a.colPtrs()[c];

                  for(int rrlast = a.colPtrs()[c + 1]; rr < rrlast; ++rr) {
                     int r = a.rowIndices()[rr];
                     res.update$mcF$sp(r, res.apply$mcF$sp(r) + a.data$mcF$sp()[rr] * b.apply$mcIF$sp(c));
                  }
               }

               return res;
            }
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(CSCMatrixExpandedOps.this.op_M_V_Float())).register(this, scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_V_Double_$eq(new BinaryRegistry() {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public DenseVector bindingMissing(final CSCMatrix a, final Vector b) {
            int left$macro$1 = a.cols();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(66)).append("requirement failed: Dimension Mismatch!: ").append("a.cols == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector res = DenseVector$.MODULE$.zeros$mDc$sp(a.rows(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());

               for(int c = 0; c < a.cols(); ++c) {
                  int rr = a.colPtrs()[c];

                  for(int rrlast = a.colPtrs()[c + 1]; rr < rrlast; ++rr) {
                     int r = a.rowIndices()[rr];
                     res.update$mcD$sp(r, res.apply$mcD$sp(r) + a.data$mcD$sp()[rr] * b.apply$mcID$sp(c));
                  }
               }

               return res;
            }
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(CSCMatrixExpandedOps.this.op_M_V_Double())).register(this, scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_V_Long_$eq(new BinaryRegistry() {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public DenseVector bindingMissing(final CSCMatrix a, final Vector b) {
            int left$macro$1 = a.cols();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(66)).append("requirement failed: Dimension Mismatch!: ").append("a.cols == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector res = DenseVector$.MODULE$.zeros$mJc$sp(a.rows(), scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());

               for(int c = 0; c < a.cols(); ++c) {
                  int rr = a.colPtrs()[c];

                  for(int rrlast = a.colPtrs()[c + 1]; rr < rrlast; ++rr) {
                     int r = a.rowIndices()[rr];
                     res.update$mcJ$sp(r, res.apply$mcJ$sp(r) + a.data$mcJ$sp()[rr] * b.apply$mcIJ$sp(c));
                  }
               }

               return res;
            }
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(CSCMatrixExpandedOps.this.op_M_V_Long())).register(this, scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_DV_Int_$eq(new UFunc.UImpl2() {
         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public DenseVector apply(final CSCMatrix a, final DenseVector b) {
            int left$macro$1 = a.cols();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(66)).append("requirement failed: Dimension Mismatch!: ").append("a.cols == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector res = DenseVector$.MODULE$.zeros$mIc$sp(a.rows(), scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());

               for(int c = 0; c < a.cols(); ++c) {
                  int rr = a.colPtrs()[c];

                  for(int rrlast = a.colPtrs()[c + 1]; rr < rrlast; ++rr) {
                     int r = a.rowIndices()[rr];
                     res.update$mcI$sp(r, res.apply$mcI$sp(r) + a.data$mcI$sp()[rr] * b.apply$mcI$sp(c));
                  }
               }

               return res;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(CSCMatrixExpandedOps.this.op_M_V_Int())).register(this, scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(CSCMatrixExpandedOps.this.canMulM_V_Int())).register(this, scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_DV_Float_$eq(new UFunc.UImpl2() {
         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public DenseVector apply(final CSCMatrix a, final DenseVector b) {
            int left$macro$1 = a.cols();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(66)).append("requirement failed: Dimension Mismatch!: ").append("a.cols == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector res = DenseVector$.MODULE$.zeros$mFc$sp(a.rows(), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());

               for(int c = 0; c < a.cols(); ++c) {
                  int rr = a.colPtrs()[c];

                  for(int rrlast = a.colPtrs()[c + 1]; rr < rrlast; ++rr) {
                     int r = a.rowIndices()[rr];
                     res.update$mcF$sp(r, res.apply$mcF$sp(r) + a.data$mcF$sp()[rr] * b.apply$mcF$sp(c));
                  }
               }

               return res;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(CSCMatrixExpandedOps.this.op_M_V_Float())).register(this, scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(CSCMatrixExpandedOps.this.canMulM_V_Float())).register(this, scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_DV_Double_$eq(new UFunc.UImpl2() {
         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public DenseVector apply(final CSCMatrix a, final DenseVector b) {
            int left$macro$1 = a.cols();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(66)).append("requirement failed: Dimension Mismatch!: ").append("a.cols == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector res = DenseVector$.MODULE$.zeros$mDc$sp(a.rows(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());

               for(int c = 0; c < a.cols(); ++c) {
                  int rr = a.colPtrs()[c];

                  for(int rrlast = a.colPtrs()[c + 1]; rr < rrlast; ++rr) {
                     int r = a.rowIndices()[rr];
                     res.update$mcD$sp(r, res.apply$mcD$sp(r) + a.data$mcD$sp()[rr] * b.apply$mcD$sp(c));
                  }
               }

               return res;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(CSCMatrixExpandedOps.this.op_M_V_Double())).register(this, scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(CSCMatrixExpandedOps.this.canMulM_V_Double())).register(this, scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_DV_Long_$eq(new UFunc.UImpl2() {
         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public DenseVector apply(final CSCMatrix a, final DenseVector b) {
            int left$macro$1 = a.cols();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(66)).append("requirement failed: Dimension Mismatch!: ").append("a.cols == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector res = DenseVector$.MODULE$.zeros$mJc$sp(a.rows(), scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());

               for(int c = 0; c < a.cols(); ++c) {
                  int rr = a.colPtrs()[c];

                  for(int rrlast = a.colPtrs()[c + 1]; rr < rrlast; ++rr) {
                     int r = a.rowIndices()[rr];
                     res.update$mcJ$sp(r, res.apply$mcJ$sp(r) + a.data$mcJ$sp()[rr] * b.apply$mcJ$sp(c));
                  }
               }

               return res;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(CSCMatrixExpandedOps.this.op_M_V_Long())).register(this, scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(CSCMatrixExpandedOps.this.canMulM_V_Long())).register(this, scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_SV_Int_$eq(new BinaryRegistry() {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public SparseVector bindingMissing(final CSCMatrix a, final SparseVector b) {
            int left$macro$1 = a.cols();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(66)).append("requirement failed: Dimension Mismatch!: ").append("a.cols == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder res = new VectorBuilder$mcI$sp(a.rows(), scala.runtime.RichInt..MODULE$.min$extension(scala.Predef..MODULE$.intWrapper(b.iterableSize()), a.rows()), Semiring$.MODULE$.semiringInt(), scala.reflect.ClassTag..MODULE$.Int());
               int c = 0;

               for(int lastOffset = 0; c < a.cols(); ++c) {
                  int rr = a.colPtrs()[c];
                  int rrlast = a.colPtrs()[c + 1];
                  if (rr < rrlast) {
                     int newBOffset = Arrays.binarySearch(b.index(), lastOffset, scala.math.package..MODULE$.min(b.activeSize(), c + 1), c);
                     if (newBOffset < 0) {
                        lastOffset = ~newBOffset;
                     } else {
                        while(rr < rrlast) {
                           int r = a.rowIndices()[rr];
                           res.add$mcI$sp(r, a.data$mcI$sp()[rr] * b.valueAt$mcI$sp(newBOffset));
                           ++rr;
                        }

                        lastOffset = newBOffset + 1;
                     }
                  }
               }

               return res.toSparseVector$mcI$sp();
            }
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(CSCMatrixExpandedOps.this.op_M_V_Int())).register(this, scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_SV_Float_$eq(new BinaryRegistry() {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public SparseVector bindingMissing(final CSCMatrix a, final SparseVector b) {
            int left$macro$1 = a.cols();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(66)).append("requirement failed: Dimension Mismatch!: ").append("a.cols == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder res = new VectorBuilder$mcF$sp(a.rows(), scala.runtime.RichInt..MODULE$.min$extension(scala.Predef..MODULE$.intWrapper(b.iterableSize()), a.rows()), Semiring$.MODULE$.semiringFloat(), scala.reflect.ClassTag..MODULE$.Float());
               int c = 0;

               for(int lastOffset = 0; c < a.cols(); ++c) {
                  int rr = a.colPtrs()[c];
                  int rrlast = a.colPtrs()[c + 1];
                  if (rr < rrlast) {
                     int newBOffset = Arrays.binarySearch(b.index(), lastOffset, scala.math.package..MODULE$.min(b.activeSize(), c + 1), c);
                     if (newBOffset < 0) {
                        lastOffset = ~newBOffset;
                     } else {
                        while(rr < rrlast) {
                           int r = a.rowIndices()[rr];
                           res.add$mcF$sp(r, a.data$mcF$sp()[rr] * b.valueAt$mcF$sp(newBOffset));
                           ++rr;
                        }

                        lastOffset = newBOffset + 1;
                     }
                  }
               }

               return res.toSparseVector$mcF$sp();
            }
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(CSCMatrixExpandedOps.this.op_M_V_Float())).register(this, scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_SV_Double_$eq(new BinaryRegistry() {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public SparseVector bindingMissing(final CSCMatrix a, final SparseVector b) {
            int left$macro$1 = a.cols();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(66)).append("requirement failed: Dimension Mismatch!: ").append("a.cols == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder res = new VectorBuilder$mcD$sp(a.rows(), scala.runtime.RichInt..MODULE$.min$extension(scala.Predef..MODULE$.intWrapper(b.iterableSize()), a.rows()), Semiring$.MODULE$.semiringD(), scala.reflect.ClassTag..MODULE$.Double());
               int c = 0;

               for(int lastOffset = 0; c < a.cols(); ++c) {
                  int rr = a.colPtrs()[c];
                  int rrlast = a.colPtrs()[c + 1];
                  if (rr < rrlast) {
                     int newBOffset = Arrays.binarySearch(b.index(), lastOffset, scala.math.package..MODULE$.min(b.activeSize(), c + 1), c);
                     if (newBOffset < 0) {
                        lastOffset = ~newBOffset;
                     } else {
                        while(rr < rrlast) {
                           int r = a.rowIndices()[rr];
                           res.add$mcD$sp(r, a.data$mcD$sp()[rr] * b.valueAt$mcD$sp(newBOffset));
                           ++rr;
                        }

                        lastOffset = newBOffset + 1;
                     }
                  }
               }

               return res.toSparseVector$mcD$sp();
            }
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(CSCMatrixExpandedOps.this.op_M_V_Double())).register(this, scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_SV_Long_$eq(new BinaryRegistry() {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public SparseVector bindingMissing(final CSCMatrix a, final SparseVector b) {
            int left$macro$1 = a.cols();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(66)).append("requirement failed: Dimension Mismatch!: ").append("a.cols == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder res = new VectorBuilder$mcJ$sp(a.rows(), scala.runtime.RichInt..MODULE$.min$extension(scala.Predef..MODULE$.intWrapper(b.iterableSize()), a.rows()), Semiring$.MODULE$.semiringLong(), scala.reflect.ClassTag..MODULE$.Long());
               int c = 0;

               for(int lastOffset = 0; c < a.cols(); ++c) {
                  int rr = a.colPtrs()[c];
                  int rrlast = a.colPtrs()[c + 1];
                  if (rr < rrlast) {
                     int newBOffset = Arrays.binarySearch(b.index(), lastOffset, scala.math.package..MODULE$.min(b.activeSize(), c + 1), c);
                     if (newBOffset < 0) {
                        lastOffset = ~newBOffset;
                     } else {
                        while(rr < rrlast) {
                           int r = a.rowIndices()[rr];
                           res.add$mcJ$sp(r, a.data$mcJ$sp()[rr] * b.valueAt$mcJ$sp(newBOffset));
                           ++rr;
                        }

                        lastOffset = newBOffset + 1;
                     }
                  }
               }

               return res.toSparseVector$mcJ$sp();
            }
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(CSCMatrixExpandedOps.this.op_M_V_Long())).register(this, scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_DM_Int_$eq(new UFunc.UImpl2() {
         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public DenseMatrix apply(final CSCMatrix a, final DenseMatrix b) {
            if (a.cols() != b.rows()) {
               throw new RuntimeException("Dimension Mismatch!");
            } else {
               DenseMatrix res = new DenseMatrix$mcI$sp(a.rows(), b.cols(), scala.reflect.ClassTag..MODULE$.Int());

               for(int i = 0; i < b.cols(); ++i) {
                  for(int j = 0; j < a.cols(); ++j) {
                     int v = b.apply$mcI$sp(j, i);

                     for(int k = a.colPtrs()[j]; k < a.colPtrs()[j + 1]; ++k) {
                        int var8 = a.rowIndices()[k];
                        res.update$mcI$sp(var8, i, res.apply$mcI$sp(var8, i) + v * a.data$mcI$sp()[k]);
                     }
                  }
               }

               return res;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(CSCMatrixExpandedOps.this.op_M_M_Int())).register(this, scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class), scala.reflect.ClassTag..MODULE$.apply(DenseMatrix.class));
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_DM_Float_$eq(new UFunc.UImpl2() {
         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public DenseMatrix apply(final CSCMatrix a, final DenseMatrix b) {
            if (a.cols() != b.rows()) {
               throw new RuntimeException("Dimension Mismatch!");
            } else {
               DenseMatrix res = new DenseMatrix$mcF$sp(a.rows(), b.cols(), scala.reflect.ClassTag..MODULE$.Float());

               for(int i = 0; i < b.cols(); ++i) {
                  for(int j = 0; j < a.cols(); ++j) {
                     float v = b.apply$mcF$sp(j, i);

                     for(int k = a.colPtrs()[j]; k < a.colPtrs()[j + 1]; ++k) {
                        int var8 = a.rowIndices()[k];
                        res.update$mcF$sp(var8, i, res.apply$mcF$sp(var8, i) + v * a.data$mcF$sp()[k]);
                     }
                  }
               }

               return res;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(CSCMatrixExpandedOps.this.op_M_M_Float())).register(this, scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class), scala.reflect.ClassTag..MODULE$.apply(DenseMatrix.class));
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_DM_Double_$eq(new UFunc.UImpl2() {
         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public DenseMatrix apply(final CSCMatrix a, final DenseMatrix b) {
            if (a.cols() != b.rows()) {
               throw new RuntimeException("Dimension Mismatch!");
            } else {
               DenseMatrix res = new DenseMatrix$mcD$sp(a.rows(), b.cols(), scala.reflect.ClassTag..MODULE$.Double());

               for(int i = 0; i < b.cols(); ++i) {
                  for(int j = 0; j < a.cols(); ++j) {
                     double v = b.apply$mcD$sp(j, i);

                     for(int k = a.colPtrs()[j]; k < a.colPtrs()[j + 1]; ++k) {
                        int var9 = a.rowIndices()[k];
                        res.update$mcD$sp(var9, i, res.apply$mcD$sp(var9, i) + v * a.data$mcD$sp()[k]);
                     }
                  }
               }

               return res;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(CSCMatrixExpandedOps.this.op_M_M_Double())).register(this, scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class), scala.reflect.ClassTag..MODULE$.apply(DenseMatrix.class));
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_DM_Long_$eq(new UFunc.UImpl2() {
         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public DenseMatrix apply(final CSCMatrix a, final DenseMatrix b) {
            if (a.cols() != b.rows()) {
               throw new RuntimeException("Dimension Mismatch!");
            } else {
               DenseMatrix res = new DenseMatrix$mcJ$sp(a.rows(), b.cols(), scala.reflect.ClassTag..MODULE$.Long());

               for(int i = 0; i < b.cols(); ++i) {
                  for(int j = 0; j < a.cols(); ++j) {
                     long v = b.apply$mcJ$sp(j, i);

                     for(int k = a.colPtrs()[j]; k < a.colPtrs()[j + 1]; ++k) {
                        int var9 = a.rowIndices()[k];
                        res.update$mcJ$sp(var9, i, res.apply$mcJ$sp(var9, i) + v * a.data$mcJ$sp()[k]);
                     }
                  }
               }

               return res;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(CSCMatrixExpandedOps.this.op_M_M_Long())).register(this, scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class), scala.reflect.ClassTag..MODULE$.apply(DenseMatrix.class));
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulDM_M_Int_$eq(new UFunc.UImpl2() {
         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public DenseMatrix apply(final DenseMatrix a, final CSCMatrix b) {
            if (a.cols() != b.rows()) {
               throw new RuntimeException("Dimension Mismatch!");
            } else {
               DenseMatrix res = new DenseMatrix$mcI$sp(a.rows(), b.cols(), scala.reflect.ClassTag..MODULE$.Int());

               for(int i = 0; i < b.cols(); ++i) {
                  for(int j = b.colPtrs()[i]; j < b.colPtrs()[i + 1]; ++j) {
                     int dval = b.data$mcI$sp()[j];
                     int ival = b.rowIndices()[j];

                     for(int k = 0; k < a.rows(); ++k) {
                        res.update$mcI$sp(k, i, res.apply$mcI$sp(k, i) + a.apply$mcI$sp(k, ival) * dval);
                     }
                  }
               }

               return res;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(CSCMatrixExpandedOps.this.op_M_M_Int())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseMatrix.class), scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class));
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HasOps$.MODULE$.impl_OpMulMatrix_DM_M_eq_DM_Int())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseMatrix.class), scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class));
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulDM_M_Float_$eq(new UFunc.UImpl2() {
         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public DenseMatrix apply(final DenseMatrix a, final CSCMatrix b) {
            if (a.cols() != b.rows()) {
               throw new RuntimeException("Dimension Mismatch!");
            } else {
               DenseMatrix res = new DenseMatrix$mcF$sp(a.rows(), b.cols(), scala.reflect.ClassTag..MODULE$.Float());

               for(int i = 0; i < b.cols(); ++i) {
                  for(int j = b.colPtrs()[i]; j < b.colPtrs()[i + 1]; ++j) {
                     float dval = b.data$mcF$sp()[j];
                     int ival = b.rowIndices()[j];

                     for(int k = 0; k < a.rows(); ++k) {
                        res.update$mcF$sp(k, i, res.apply$mcF$sp(k, i) + a.apply$mcF$sp(k, ival) * dval);
                     }
                  }
               }

               return res;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(CSCMatrixExpandedOps.this.op_M_M_Float())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseMatrix.class), scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class));
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HasOps$.MODULE$.impl_OpMulMatrix_DM_M_eq_DM_Float())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseMatrix.class), scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class));
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulDM_M_Double_$eq(new UFunc.UImpl2() {
         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public DenseMatrix apply(final DenseMatrix a, final CSCMatrix b) {
            if (a.cols() != b.rows()) {
               throw new RuntimeException("Dimension Mismatch!");
            } else {
               DenseMatrix res = new DenseMatrix$mcD$sp(a.rows(), b.cols(), scala.reflect.ClassTag..MODULE$.Double());

               for(int i = 0; i < b.cols(); ++i) {
                  for(int j = b.colPtrs()[i]; j < b.colPtrs()[i + 1]; ++j) {
                     double dval = b.data$mcD$sp()[j];
                     int ival = b.rowIndices()[j];

                     for(int k = 0; k < a.rows(); ++k) {
                        res.update$mcD$sp(k, i, res.apply$mcD$sp(k, i) + a.apply$mcD$sp(k, ival) * dval);
                     }
                  }
               }

               return res;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(CSCMatrixExpandedOps.this.op_M_M_Double())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseMatrix.class), scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class));
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HasOps$.MODULE$.impl_OpMulMatrix_DM_M_eq_DM_Double())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseMatrix.class), scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class));
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulDM_M_Long_$eq(new UFunc.UImpl2() {
         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public DenseMatrix apply(final DenseMatrix a, final CSCMatrix b) {
            if (a.cols() != b.rows()) {
               throw new RuntimeException("Dimension Mismatch!");
            } else {
               DenseMatrix res = new DenseMatrix$mcJ$sp(a.rows(), b.cols(), scala.reflect.ClassTag..MODULE$.Long());

               for(int i = 0; i < b.cols(); ++i) {
                  for(int j = b.colPtrs()[i]; j < b.colPtrs()[i + 1]; ++j) {
                     long dval = b.data$mcJ$sp()[j];
                     int ival = b.rowIndices()[j];

                     for(int k = 0; k < a.rows(); ++k) {
                        res.update$mcJ$sp(k, i, res.apply$mcJ$sp(k, i) + a.apply$mcJ$sp(k, ival) * dval);
                     }
                  }
               }

               return res;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(CSCMatrixExpandedOps.this.op_M_M_Long())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseMatrix.class), scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class));
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(HasOps$.MODULE$.impl_OpMulMatrix_DM_M_eq_DM_Long())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseMatrix.class), scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class));
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_M_Int_$eq(new UFunc.UImpl2() {
         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public CSCMatrix apply(final CSCMatrix a, final CSCMatrix b) {
            int left$macro$1 = a.cols();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(63)).append("requirement failed: Dimension Mismatch: ").append("a.cols == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int[] workData = new int[a.rows()];
               int[] workIndex = new int[a.rows()];
               Arrays.fill(workIndex, -1);
               int totalNnz = this.computeNnz(a, b, workIndex);
               Arrays.fill(workIndex, -1);
               CSCMatrix res = CSCMatrix$.MODULE$.zeros(a.rows(), b.cols(), scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());
               res.reserve(totalNnz);
               int[] resRows = res.rowIndices();
               int[] resData = res.data$mcI$sp();
               int[] aRows = a.rowIndices();
               int[] aData = a.data$mcI$sp();
               int[] aPtrs = a.colPtrs();
               int index$macro$20 = 0;

               for(int limit$macro$22 = b.cols(); index$macro$20 < limit$macro$22; ++index$macro$20) {
                  int nnz = res.used();
                  int index$macro$9 = b.colPtrs()[index$macro$20];

                  for(int limit$macro$11 = b.colPtrs()[index$macro$20 + 1]; index$macro$9 < limit$macro$11; ++index$macro$9) {
                     int bRow = b.rowIndices()[index$macro$9];
                     int bVal = b.data$mcI$sp()[index$macro$9];
                     int index$macro$4 = aPtrs[bRow];

                     for(int limit$macro$6 = aPtrs[bRow + 1]; index$macro$4 < limit$macro$6; ++index$macro$4) {
                        int aRow = aRows[index$macro$4];
                        int aVal = aData[index$macro$4];
                        if (workIndex[aRow] < index$macro$20) {
                           workData[aRow] = 0;
                           workIndex[aRow] = index$macro$20;
                           resRows[nnz] = aRow;
                           ++nnz;
                        }

                        workData[aRow] += aVal * bVal;
                     }
                  }

                  res.colPtrs()[index$macro$20 + 1] = nnz;
                  res.used_$eq(nnz);
                  Arrays.sort(resRows, res.colPtrs()[index$macro$20], res.colPtrs()[index$macro$20 + 1]);
                  int index$macro$14 = res.colPtrs()[index$macro$20];

                  for(int limit$macro$16 = res.colPtrs()[index$macro$20 + 1]; index$macro$14 < limit$macro$16; ++index$macro$14) {
                     int row = resRows[index$macro$14];
                     resData[index$macro$14] = workData[row];
                  }

                  boolean cond$macro$18 = nnz <= totalNnz;
                  if (!cond$macro$18) {
                     throw new AssertionError("assertion failed: nnz.<=(totalNnz)");
                  }
               }

               res.compact();
               return res;
            }
         }

         private int computeNnz(final CSCMatrix a, final CSCMatrix b, final int[] workIndex) {
            int nnz = 0;
            int index$macro$12 = 0;

            for(int limit$macro$14 = b.cols(); index$macro$12 < limit$macro$14; ++index$macro$12) {
               int index$macro$7 = b.colPtrs()[index$macro$12];

               for(int limit$macro$9 = b.colPtrs()[index$macro$12 + 1]; index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int bRow = b.rowIndices()[index$macro$7];
                  int index$macro$2 = a.colPtrs()[bRow];

                  for(int limit$macro$4 = a.colPtrs()[bRow + 1]; index$macro$2 < limit$macro$4; ++index$macro$2) {
                     int aRow = a.rowIndices()[index$macro$2];
                     if (workIndex[aRow] < index$macro$12) {
                        workIndex[aRow] = index$macro$12;
                        ++nnz;
                     }
                  }
               }
            }

            return nnz;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(CSCMatrixExpandedOps.this.op_M_M_Int())).register(this, scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class), scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class));
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_M_Float_$eq(new UFunc.UImpl2() {
         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public CSCMatrix apply(final CSCMatrix a, final CSCMatrix b) {
            int left$macro$1 = a.cols();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(63)).append("requirement failed: Dimension Mismatch: ").append("a.cols == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               float[] workData = new float[a.rows()];
               int[] workIndex = new int[a.rows()];
               Arrays.fill(workIndex, -1);
               int totalNnz = this.computeNnz(a, b, workIndex);
               Arrays.fill(workIndex, -1);
               CSCMatrix res = CSCMatrix$.MODULE$.zeros(a.rows(), b.cols(), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());
               res.reserve(totalNnz);
               int[] resRows = res.rowIndices();
               float[] resData = res.data$mcF$sp();
               int[] aRows = a.rowIndices();
               float[] aData = a.data$mcF$sp();
               int[] aPtrs = a.colPtrs();
               int index$macro$20 = 0;

               for(int limit$macro$22 = b.cols(); index$macro$20 < limit$macro$22; ++index$macro$20) {
                  int nnz = res.used();
                  int index$macro$9 = b.colPtrs()[index$macro$20];

                  for(int limit$macro$11 = b.colPtrs()[index$macro$20 + 1]; index$macro$9 < limit$macro$11; ++index$macro$9) {
                     int bRow = b.rowIndices()[index$macro$9];
                     float bVal = b.data$mcF$sp()[index$macro$9];
                     int index$macro$4 = aPtrs[bRow];

                     for(int limit$macro$6 = aPtrs[bRow + 1]; index$macro$4 < limit$macro$6; ++index$macro$4) {
                        int aRow = aRows[index$macro$4];
                        float aVal = aData[index$macro$4];
                        if (workIndex[aRow] < index$macro$20) {
                           workData[aRow] = 0.0F;
                           workIndex[aRow] = index$macro$20;
                           resRows[nnz] = aRow;
                           ++nnz;
                        }

                        workData[aRow] += aVal * bVal;
                     }
                  }

                  res.colPtrs()[index$macro$20 + 1] = nnz;
                  res.used_$eq(nnz);
                  Arrays.sort(resRows, res.colPtrs()[index$macro$20], res.colPtrs()[index$macro$20 + 1]);
                  int index$macro$14 = res.colPtrs()[index$macro$20];

                  for(int limit$macro$16 = res.colPtrs()[index$macro$20 + 1]; index$macro$14 < limit$macro$16; ++index$macro$14) {
                     int row = resRows[index$macro$14];
                     resData[index$macro$14] = workData[row];
                  }

                  boolean cond$macro$18 = nnz <= totalNnz;
                  if (!cond$macro$18) {
                     throw new AssertionError("assertion failed: nnz.<=(totalNnz)");
                  }
               }

               res.compact();
               return res;
            }
         }

         private int computeNnz(final CSCMatrix a, final CSCMatrix b, final int[] workIndex) {
            int nnz = 0;
            int index$macro$12 = 0;

            for(int limit$macro$14 = b.cols(); index$macro$12 < limit$macro$14; ++index$macro$12) {
               int index$macro$7 = b.colPtrs()[index$macro$12];

               for(int limit$macro$9 = b.colPtrs()[index$macro$12 + 1]; index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int bRow = b.rowIndices()[index$macro$7];
                  int index$macro$2 = a.colPtrs()[bRow];

                  for(int limit$macro$4 = a.colPtrs()[bRow + 1]; index$macro$2 < limit$macro$4; ++index$macro$2) {
                     int aRow = a.rowIndices()[index$macro$2];
                     if (workIndex[aRow] < index$macro$12) {
                        workIndex[aRow] = index$macro$12;
                        ++nnz;
                     }
                  }
               }
            }

            return nnz;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(CSCMatrixExpandedOps.this.op_M_M_Float())).register(this, scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class), scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class));
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_M_Double_$eq(new UFunc.UImpl2() {
         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public CSCMatrix apply(final CSCMatrix a, final CSCMatrix b) {
            int left$macro$1 = a.cols();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(63)).append("requirement failed: Dimension Mismatch: ").append("a.cols == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               double[] workData = new double[a.rows()];
               int[] workIndex = new int[a.rows()];
               Arrays.fill(workIndex, -1);
               int totalNnz = this.computeNnz(a, b, workIndex);
               Arrays.fill(workIndex, -1);
               CSCMatrix res = CSCMatrix$.MODULE$.zeros(a.rows(), b.cols(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
               res.reserve(totalNnz);
               int[] resRows = res.rowIndices();
               double[] resData = res.data$mcD$sp();
               int[] aRows = a.rowIndices();
               double[] aData = a.data$mcD$sp();
               int[] aPtrs = a.colPtrs();
               int index$macro$20 = 0;

               for(int limit$macro$22 = b.cols(); index$macro$20 < limit$macro$22; ++index$macro$20) {
                  int nnz = res.used();
                  int index$macro$9 = b.colPtrs()[index$macro$20];

                  for(int limit$macro$11 = b.colPtrs()[index$macro$20 + 1]; index$macro$9 < limit$macro$11; ++index$macro$9) {
                     int bRow = b.rowIndices()[index$macro$9];
                     double bVal = b.data$mcD$sp()[index$macro$9];
                     int index$macro$4 = aPtrs[bRow];

                     for(int limit$macro$6 = aPtrs[bRow + 1]; index$macro$4 < limit$macro$6; ++index$macro$4) {
                        int aRow = aRows[index$macro$4];
                        double aVal = aData[index$macro$4];
                        if (workIndex[aRow] < index$macro$20) {
                           workData[aRow] = (double)0.0F;
                           workIndex[aRow] = index$macro$20;
                           resRows[nnz] = aRow;
                           ++nnz;
                        }

                        workData[aRow] += aVal * bVal;
                     }
                  }

                  res.colPtrs()[index$macro$20 + 1] = nnz;
                  res.used_$eq(nnz);
                  Arrays.sort(resRows, res.colPtrs()[index$macro$20], res.colPtrs()[index$macro$20 + 1]);
                  int index$macro$14 = res.colPtrs()[index$macro$20];

                  for(int limit$macro$16 = res.colPtrs()[index$macro$20 + 1]; index$macro$14 < limit$macro$16; ++index$macro$14) {
                     int row = resRows[index$macro$14];
                     resData[index$macro$14] = workData[row];
                  }

                  boolean cond$macro$18 = nnz <= totalNnz;
                  if (!cond$macro$18) {
                     throw new AssertionError("assertion failed: nnz.<=(totalNnz)");
                  }
               }

               res.compact();
               return res;
            }
         }

         private int computeNnz(final CSCMatrix a, final CSCMatrix b, final int[] workIndex) {
            int nnz = 0;
            int index$macro$12 = 0;

            for(int limit$macro$14 = b.cols(); index$macro$12 < limit$macro$14; ++index$macro$12) {
               int index$macro$7 = b.colPtrs()[index$macro$12];

               for(int limit$macro$9 = b.colPtrs()[index$macro$12 + 1]; index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int bRow = b.rowIndices()[index$macro$7];
                  int index$macro$2 = a.colPtrs()[bRow];

                  for(int limit$macro$4 = a.colPtrs()[bRow + 1]; index$macro$2 < limit$macro$4; ++index$macro$2) {
                     int aRow = a.rowIndices()[index$macro$2];
                     if (workIndex[aRow] < index$macro$12) {
                        workIndex[aRow] = index$macro$12;
                        ++nnz;
                     }
                  }
               }
            }

            return nnz;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(CSCMatrixExpandedOps.this.op_M_M_Double())).register(this, scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class), scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class));
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$canMulM_M_Long_$eq(new UFunc.UImpl2() {
         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public CSCMatrix apply(final CSCMatrix a, final CSCMatrix b) {
            int left$macro$1 = a.cols();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(63)).append("requirement failed: Dimension Mismatch: ").append("a.cols == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               long[] workData = new long[a.rows()];
               int[] workIndex = new int[a.rows()];
               Arrays.fill(workIndex, -1);
               int totalNnz = this.computeNnz(a, b, workIndex);
               Arrays.fill(workIndex, -1);
               CSCMatrix res = CSCMatrix$.MODULE$.zeros(a.rows(), b.cols(), scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());
               res.reserve(totalNnz);
               int[] resRows = res.rowIndices();
               long[] resData = res.data$mcJ$sp();
               int[] aRows = a.rowIndices();
               long[] aData = a.data$mcJ$sp();
               int[] aPtrs = a.colPtrs();
               int index$macro$20 = 0;

               for(int limit$macro$22 = b.cols(); index$macro$20 < limit$macro$22; ++index$macro$20) {
                  int nnz = res.used();
                  int index$macro$9 = b.colPtrs()[index$macro$20];

                  for(int limit$macro$11 = b.colPtrs()[index$macro$20 + 1]; index$macro$9 < limit$macro$11; ++index$macro$9) {
                     int bRow = b.rowIndices()[index$macro$9];
                     long bVal = b.data$mcJ$sp()[index$macro$9];
                     int index$macro$4 = aPtrs[bRow];

                     for(int limit$macro$6 = aPtrs[bRow + 1]; index$macro$4 < limit$macro$6; ++index$macro$4) {
                        int aRow = aRows[index$macro$4];
                        long aVal = aData[index$macro$4];
                        if (workIndex[aRow] < index$macro$20) {
                           workData[aRow] = 0L;
                           workIndex[aRow] = index$macro$20;
                           resRows[nnz] = aRow;
                           ++nnz;
                        }

                        workData[aRow] += aVal * bVal;
                     }
                  }

                  res.colPtrs()[index$macro$20 + 1] = nnz;
                  res.used_$eq(nnz);
                  Arrays.sort(resRows, res.colPtrs()[index$macro$20], res.colPtrs()[index$macro$20 + 1]);
                  int index$macro$14 = res.colPtrs()[index$macro$20];

                  for(int limit$macro$16 = res.colPtrs()[index$macro$20 + 1]; index$macro$14 < limit$macro$16; ++index$macro$14) {
                     int row = resRows[index$macro$14];
                     resData[index$macro$14] = workData[row];
                  }

                  boolean cond$macro$18 = nnz <= totalNnz;
                  if (!cond$macro$18) {
                     throw new AssertionError("assertion failed: nnz.<=(totalNnz)");
                  }
               }

               res.compact();
               return res;
            }
         }

         private int computeNnz(final CSCMatrix a, final CSCMatrix b, final int[] workIndex) {
            int nnz = 0;
            int index$macro$12 = 0;

            for(int limit$macro$14 = b.cols(); index$macro$12 < limit$macro$14; ++index$macro$12) {
               int index$macro$7 = b.colPtrs()[index$macro$12];

               for(int limit$macro$9 = b.colPtrs()[index$macro$12 + 1]; index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int bRow = b.rowIndices()[index$macro$7];
                  int index$macro$2 = a.colPtrs()[bRow];

                  for(int limit$macro$4 = a.colPtrs()[bRow + 1]; index$macro$2 < limit$macro$4; ++index$macro$2) {
                     int aRow = a.rowIndices()[index$macro$2];
                     if (workIndex[aRow] < index$macro$12) {
                        workIndex[aRow] = index$macro$12;
                        ++nnz;
                     }
                  }
               }
            }

            return nnz;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(CSCMatrixExpandedOps.this.op_M_M_Long())).register(this, scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class), scala.reflect.ClassTag..MODULE$.apply(CSCMatrix.class));
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Int_OpAdd_$eq(GenericOps$.MODULE$.updateFromPure($this.canAddM_S_Semiring(Semiring$.MODULE$.semiringInt(), scala.reflect.ClassTag..MODULE$.Int()), $this.impl_Op_CSC_CSC_eq_CSC_lift_OpSet(Field.fieldInt$.MODULE$, scala.reflect.ClassTag..MODULE$.Int())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Float_OpAdd_$eq(GenericOps$.MODULE$.updateFromPure($this.canAddM_S_Semiring(Semiring$.MODULE$.semiringFloat(), scala.reflect.ClassTag..MODULE$.Float()), $this.impl_Op_CSC_CSC_eq_CSC_lift_OpSet(Field.fieldFloat$.MODULE$, scala.reflect.ClassTag..MODULE$.Float())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Double_OpAdd_$eq(GenericOps$.MODULE$.updateFromPure($this.canAddM_S_Semiring(Semiring$.MODULE$.semiringD(), scala.reflect.ClassTag..MODULE$.Double()), $this.impl_Op_CSC_CSC_eq_CSC_lift_OpSet(Field.fieldDouble$.MODULE$, scala.reflect.ClassTag..MODULE$.Double())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Long_OpAdd_$eq(GenericOps$.MODULE$.updateFromPure($this.canAddM_S_Semiring(Semiring$.MODULE$.semiringLong(), scala.reflect.ClassTag..MODULE$.Long()), $this.impl_Op_CSC_CSC_eq_CSC_lift_OpSet(Field.fieldLong$.MODULE$, scala.reflect.ClassTag..MODULE$.Long())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Int_OpSub_$eq(GenericOps$.MODULE$.updateFromPure($this.canSubM_S_Ring(Ring$.MODULE$.ringInt(), scala.reflect.ClassTag..MODULE$.Int()), $this.impl_Op_CSC_CSC_eq_CSC_lift_OpSet(Field.fieldInt$.MODULE$, scala.reflect.ClassTag..MODULE$.Int())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Float_OpSub_$eq(GenericOps$.MODULE$.updateFromPure($this.canSubM_S_Ring(Ring$.MODULE$.ringFloat(), scala.reflect.ClassTag..MODULE$.Float()), $this.impl_Op_CSC_CSC_eq_CSC_lift_OpSet(Field.fieldFloat$.MODULE$, scala.reflect.ClassTag..MODULE$.Float())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Double_OpSub_$eq(GenericOps$.MODULE$.updateFromPure($this.canSubM_S_Ring(Ring$.MODULE$.ringD(), scala.reflect.ClassTag..MODULE$.Double()), $this.impl_Op_CSC_CSC_eq_CSC_lift_OpSet(Field.fieldDouble$.MODULE$, scala.reflect.ClassTag..MODULE$.Double())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Long_OpSub_$eq(GenericOps$.MODULE$.updateFromPure($this.canSubM_S_Ring(Ring$.MODULE$.ringLong(), scala.reflect.ClassTag..MODULE$.Long()), $this.impl_Op_CSC_CSC_eq_CSC_lift_OpSet(Field.fieldLong$.MODULE$, scala.reflect.ClassTag..MODULE$.Long())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Int_OpDiv_$eq(GenericOps$.MODULE$.updateFromPure($this.csc_T_Op_OpDiv(Field.fieldInt$.MODULE$, scala.reflect.ClassTag..MODULE$.Int()), $this.impl_Op_CSC_CSC_eq_CSC_lift_OpSet(Field.fieldInt$.MODULE$, scala.reflect.ClassTag..MODULE$.Int())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Float_OpDiv_$eq(GenericOps$.MODULE$.updateFromPure($this.csc_T_Op_OpDiv(Field.fieldFloat$.MODULE$, scala.reflect.ClassTag..MODULE$.Float()), $this.impl_Op_CSC_CSC_eq_CSC_lift_OpSet(Field.fieldFloat$.MODULE$, scala.reflect.ClassTag..MODULE$.Float())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Double_OpDiv_$eq(GenericOps$.MODULE$.updateFromPure($this.csc_T_Op_OpDiv(Field.fieldDouble$.MODULE$, scala.reflect.ClassTag..MODULE$.Double()), $this.impl_Op_CSC_CSC_eq_CSC_lift_OpSet(Field.fieldDouble$.MODULE$, scala.reflect.ClassTag..MODULE$.Double())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Long_OpDiv_$eq(GenericOps$.MODULE$.updateFromPure($this.csc_T_Op_OpDiv(Field.fieldLong$.MODULE$, scala.reflect.ClassTag..MODULE$.Long()), $this.impl_Op_CSC_CSC_eq_CSC_lift_OpSet(Field.fieldLong$.MODULE$, scala.reflect.ClassTag..MODULE$.Long())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Int_OpPow_$eq(GenericOps$.MODULE$.updateFromPure($this.csc_T_Op_OpPow(Field.fieldInt$.MODULE$, scala.reflect.ClassTag..MODULE$.Int()), $this.impl_Op_CSC_CSC_eq_CSC_lift_OpSet(Field.fieldInt$.MODULE$, scala.reflect.ClassTag..MODULE$.Int())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Float_OpPow_$eq(GenericOps$.MODULE$.updateFromPure($this.csc_T_Op_OpPow(Field.fieldFloat$.MODULE$, scala.reflect.ClassTag..MODULE$.Float()), $this.impl_Op_CSC_CSC_eq_CSC_lift_OpSet(Field.fieldFloat$.MODULE$, scala.reflect.ClassTag..MODULE$.Float())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Double_OpPow_$eq(GenericOps$.MODULE$.updateFromPure($this.csc_T_Op_OpPow(Field.fieldDouble$.MODULE$, scala.reflect.ClassTag..MODULE$.Double()), $this.impl_Op_CSC_CSC_eq_CSC_lift_OpSet(Field.fieldDouble$.MODULE$, scala.reflect.ClassTag..MODULE$.Double())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Long_OpPow_$eq(GenericOps$.MODULE$.updateFromPure($this.csc_T_Op_OpPow(Field.fieldLong$.MODULE$, scala.reflect.ClassTag..MODULE$.Long()), $this.impl_Op_CSC_CSC_eq_CSC_lift_OpSet(Field.fieldLong$.MODULE$, scala.reflect.ClassTag..MODULE$.Long())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Int_OpMod_$eq(GenericOps$.MODULE$.updateFromPure($this.csc_T_Op_OpMod(Field.fieldInt$.MODULE$, scala.reflect.ClassTag..MODULE$.Int()), $this.impl_Op_CSC_CSC_eq_CSC_lift_OpSet(Field.fieldInt$.MODULE$, scala.reflect.ClassTag..MODULE$.Int())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Float_OpMod_$eq(GenericOps$.MODULE$.updateFromPure($this.csc_T_Op_OpMod(Field.fieldFloat$.MODULE$, scala.reflect.ClassTag..MODULE$.Float()), $this.impl_Op_CSC_CSC_eq_CSC_lift_OpSet(Field.fieldFloat$.MODULE$, scala.reflect.ClassTag..MODULE$.Float())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Double_OpMod_$eq(GenericOps$.MODULE$.updateFromPure($this.csc_T_Op_OpMod(Field.fieldDouble$.MODULE$, scala.reflect.ClassTag..MODULE$.Double()), $this.impl_Op_CSC_CSC_eq_CSC_lift_OpSet(Field.fieldDouble$.MODULE$, scala.reflect.ClassTag..MODULE$.Double())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Long_OpMod_$eq(GenericOps$.MODULE$.updateFromPure($this.csc_T_Op_OpMod(Field.fieldLong$.MODULE$, scala.reflect.ClassTag..MODULE$.Long()), $this.impl_Op_CSC_CSC_eq_CSC_lift_OpSet(Field.fieldLong$.MODULE$, scala.reflect.ClassTag..MODULE$.Long())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Int_OpMulScalar_$eq(GenericOps$.MODULE$.updateFromPure($this.impl_Op_CSCT_T_eq_CSCT_Int_OpMulScalar(), $this.impl_Op_CSC_CSC_eq_CSC_lift_OpSet(Field.fieldInt$.MODULE$, scala.reflect.ClassTag..MODULE$.Int())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Float_OpMulScalar_$eq(GenericOps$.MODULE$.updateFromPure($this.impl_Op_CSCT_T_eq_CSCT_Float_OpMulScalar(), $this.impl_Op_CSC_CSC_eq_CSC_lift_OpSet(Field.fieldFloat$.MODULE$, scala.reflect.ClassTag..MODULE$.Float())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Double_OpMulScalar_$eq(GenericOps$.MODULE$.updateFromPure($this.impl_Op_CSCT_T_eq_CSCT_Double_OpMulScalar(), $this.impl_Op_CSC_CSC_eq_CSC_lift_OpSet(Field.fieldDouble$.MODULE$, scala.reflect.ClassTag..MODULE$.Double())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Long_OpMulScalar_$eq(GenericOps$.MODULE$.updateFromPure($this.impl_Op_CSCT_T_eq_CSCT_Long_OpMulScalar(), $this.impl_Op_CSC_CSC_eq_CSC_lift_OpSet(Field.fieldLong$.MODULE$, scala.reflect.ClassTag..MODULE$.Long())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Int_OpMulMatrix_$eq(GenericOps$.MODULE$.updateFromPure($this.impl_Op_CSCT_T_eq_CSCT_Int_OpMulMatrix(), $this.impl_Op_CSC_CSC_eq_CSC_lift_OpSet(Field.fieldInt$.MODULE$, scala.reflect.ClassTag..MODULE$.Int())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Float_OpMulMatrix_$eq(GenericOps$.MODULE$.updateFromPure($this.impl_Op_CSCT_T_eq_CSCT_Float_OpMulMatrix(), $this.impl_Op_CSC_CSC_eq_CSC_lift_OpSet(Field.fieldFloat$.MODULE$, scala.reflect.ClassTag..MODULE$.Float())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Double_OpMulMatrix_$eq(GenericOps$.MODULE$.updateFromPure($this.impl_Op_CSCT_T_eq_CSCT_Double_OpMulMatrix(), $this.impl_Op_CSC_CSC_eq_CSC_lift_OpSet(Field.fieldDouble$.MODULE$, scala.reflect.ClassTag..MODULE$.Double())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$impl_Op_CSC_T_eq_CSC_lift_Long_OpMulMatrix_$eq(GenericOps$.MODULE$.updateFromPure($this.impl_Op_CSCT_T_eq_CSCT_Long_OpMulMatrix(), $this.impl_Op_CSC_CSC_eq_CSC_lift_OpSet(Field.fieldLong$.MODULE$, scala.reflect.ClassTag..MODULE$.Long())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Int_OpAdd_$eq($this.updateFromPure_CSC((UFunc.UImpl2)scala.Predef..MODULE$.implicitly($this.csc_csc_OpAdd_Int())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Float_OpAdd_$eq($this.updateFromPure_CSC((UFunc.UImpl2)scala.Predef..MODULE$.implicitly($this.csc_csc_OpAdd_Float())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Double_OpAdd_$eq($this.updateFromPure_CSC((UFunc.UImpl2)scala.Predef..MODULE$.implicitly($this.csc_csc_OpAdd_Double())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Long_OpAdd_$eq($this.updateFromPure_CSC((UFunc.UImpl2)scala.Predef..MODULE$.implicitly($this.csc_csc_OpAdd_Long())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Int_OpSub_$eq($this.updateFromPure_CSC((UFunc.UImpl2)scala.Predef..MODULE$.implicitly($this.csc_csc_OpSub_Int())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Float_OpSub_$eq($this.updateFromPure_CSC((UFunc.UImpl2)scala.Predef..MODULE$.implicitly($this.csc_csc_OpSub_Float())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Double_OpSub_$eq($this.updateFromPure_CSC((UFunc.UImpl2)scala.Predef..MODULE$.implicitly($this.csc_csc_OpSub_Double())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Long_OpSub_$eq($this.updateFromPure_CSC((UFunc.UImpl2)scala.Predef..MODULE$.implicitly($this.csc_csc_OpSub_Long())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Int_OpDiv_$eq($this.updateFromPure_CSC((UFunc.UImpl2)scala.Predef..MODULE$.implicitly($this.csc_csc_BadOps_Int_OpDiv())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Float_OpDiv_$eq($this.updateFromPure_CSC((UFunc.UImpl2)scala.Predef..MODULE$.implicitly($this.csc_csc_BadOps_Float_OpDiv())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Double_OpDiv_$eq($this.updateFromPure_CSC((UFunc.UImpl2)scala.Predef..MODULE$.implicitly($this.csc_csc_BadOps_Double_OpDiv())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Long_OpDiv_$eq($this.updateFromPure_CSC((UFunc.UImpl2)scala.Predef..MODULE$.implicitly($this.csc_csc_BadOps_Long_OpDiv())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Int_OpPow_$eq($this.updateFromPure_CSC((UFunc.UImpl2)scala.Predef..MODULE$.implicitly($this.csc_csc_BadOps_Int_OpPow())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Float_OpPow_$eq($this.updateFromPure_CSC((UFunc.UImpl2)scala.Predef..MODULE$.implicitly($this.csc_csc_BadOps_Float_OpPow())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Double_OpPow_$eq($this.updateFromPure_CSC((UFunc.UImpl2)scala.Predef..MODULE$.implicitly($this.csc_csc_BadOps_Double_OpPow())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Long_OpPow_$eq($this.updateFromPure_CSC((UFunc.UImpl2)scala.Predef..MODULE$.implicitly($this.csc_csc_BadOps_Long_OpPow())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Int_OpMod_$eq($this.updateFromPure_CSC((UFunc.UImpl2)scala.Predef..MODULE$.implicitly($this.csc_csc_BadOps_Int_OpMod())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Float_OpMod_$eq($this.updateFromPure_CSC((UFunc.UImpl2)scala.Predef..MODULE$.implicitly($this.csc_csc_BadOps_Float_OpMod())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Double_OpMod_$eq($this.updateFromPure_CSC((UFunc.UImpl2)scala.Predef..MODULE$.implicitly($this.csc_csc_BadOps_Double_OpMod())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Long_OpMod_$eq($this.updateFromPure_CSC((UFunc.UImpl2)scala.Predef..MODULE$.implicitly($this.csc_csc_BadOps_Long_OpMod())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Int_OpMulScalar_$eq($this.updateFromPure_CSC((UFunc.UImpl2)scala.Predef..MODULE$.implicitly($this.csc_csc_OpMulScalar_Int())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Float_OpMulScalar_$eq($this.updateFromPure_CSC((UFunc.UImpl2)scala.Predef..MODULE$.implicitly($this.csc_csc_OpMulScalar_Float())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Double_OpMulScalar_$eq($this.updateFromPure_CSC((UFunc.UImpl2)scala.Predef..MODULE$.implicitly($this.csc_csc_OpMulScalar_Double())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$csc_csc_InPlace_Long_OpMulScalar_$eq($this.updateFromPure_CSC((UFunc.UImpl2)scala.Predef..MODULE$.implicitly($this.csc_csc_OpMulScalar_Long())));
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$axpyCSC_DM_DM_Int_$eq(new UFunc.InPlaceImpl3() {
         public void apply(final DenseMatrix sink, final CSCMatrix a, final DenseMatrix x) {
            int left$macro$1 = a.rows();
            int right$macro$2 = sink.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(46)).append("requirement failed: ").append("a.rows == sink.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int left$macro$3 = x.cols();
               int right$macro$4 = sink.cols();
               if (left$macro$3 != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(46)).append("requirement failed: ").append("x.cols == sink.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  int left$macro$5 = a.cols();
                  int right$macro$6 = x.rows();
                  if (left$macro$5 != right$macro$6) {
                     throw new IllegalArgumentException((new StringBuilder(43)).append("requirement failed: ").append("a.cols == x.rows (").append(left$macro$5).append(" ").append("!=").append(" ").append(right$macro$6).append(")").toString());
                  } else {
                     for(int i = 0; i < x.cols(); ++i) {
                        for(int j = 0; j < a.cols(); ++j) {
                           int v = x.apply$mcI$sp(j, i);

                           for(int k = a.colPtrs()[j]; k < a.colPtrs()[j + 1]; ++k) {
                              int var14 = a.rowIndices()[k];
                              sink.update$mcI$sp(var14, i, sink.apply$mcI$sp(var14, i) + v * a.data$mcI$sp()[k]);
                           }
                        }
                     }

                  }
               }
            }
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$axpyCSC_DM_DM_Float_$eq(new UFunc.InPlaceImpl3() {
         public void apply(final DenseMatrix sink, final CSCMatrix a, final DenseMatrix x) {
            int left$macro$1 = a.rows();
            int right$macro$2 = sink.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(46)).append("requirement failed: ").append("a.rows == sink.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int left$macro$3 = x.cols();
               int right$macro$4 = sink.cols();
               if (left$macro$3 != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(46)).append("requirement failed: ").append("x.cols == sink.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  int left$macro$5 = a.cols();
                  int right$macro$6 = x.rows();
                  if (left$macro$5 != right$macro$6) {
                     throw new IllegalArgumentException((new StringBuilder(43)).append("requirement failed: ").append("a.cols == x.rows (").append(left$macro$5).append(" ").append("!=").append(" ").append(right$macro$6).append(")").toString());
                  } else {
                     for(int i = 0; i < x.cols(); ++i) {
                        for(int j = 0; j < a.cols(); ++j) {
                           float v = x.apply$mcF$sp(j, i);

                           for(int k = a.colPtrs()[j]; k < a.colPtrs()[j + 1]; ++k) {
                              int var14 = a.rowIndices()[k];
                              sink.update$mcF$sp(var14, i, sink.apply$mcF$sp(var14, i) + v * a.data$mcF$sp()[k]);
                           }
                        }
                     }

                  }
               }
            }
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$axpyCSC_DM_DM_Double_$eq(new UFunc.InPlaceImpl3() {
         public void apply(final DenseMatrix sink, final CSCMatrix a, final DenseMatrix x) {
            int left$macro$1 = a.rows();
            int right$macro$2 = sink.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(46)).append("requirement failed: ").append("a.rows == sink.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int left$macro$3 = x.cols();
               int right$macro$4 = sink.cols();
               if (left$macro$3 != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(46)).append("requirement failed: ").append("x.cols == sink.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  int left$macro$5 = a.cols();
                  int right$macro$6 = x.rows();
                  if (left$macro$5 != right$macro$6) {
                     throw new IllegalArgumentException((new StringBuilder(43)).append("requirement failed: ").append("a.cols == x.rows (").append(left$macro$5).append(" ").append("!=").append(" ").append(right$macro$6).append(")").toString());
                  } else {
                     for(int i = 0; i < x.cols(); ++i) {
                        for(int j = 0; j < a.cols(); ++j) {
                           double v = x.apply$mcD$sp(j, i);

                           for(int k = a.colPtrs()[j]; k < a.colPtrs()[j + 1]; ++k) {
                              int var15 = a.rowIndices()[k];
                              sink.update$mcD$sp(var15, i, sink.apply$mcD$sp(var15, i) + v * a.data$mcD$sp()[k]);
                           }
                        }
                     }

                  }
               }
            }
         }
      });
      $this.breeze$linalg$operators$CSCMatrixExpandedOps$_setter_$axpyCSC_DM_DM_Long_$eq(new UFunc.InPlaceImpl3() {
         public void apply(final DenseMatrix sink, final CSCMatrix a, final DenseMatrix x) {
            int left$macro$1 = a.rows();
            int right$macro$2 = sink.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(46)).append("requirement failed: ").append("a.rows == sink.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int left$macro$3 = x.cols();
               int right$macro$4 = sink.cols();
               if (left$macro$3 != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(46)).append("requirement failed: ").append("x.cols == sink.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  int left$macro$5 = a.cols();
                  int right$macro$6 = x.rows();
                  if (left$macro$5 != right$macro$6) {
                     throw new IllegalArgumentException((new StringBuilder(43)).append("requirement failed: ").append("a.cols == x.rows (").append(left$macro$5).append(" ").append("!=").append(" ").append(right$macro$6).append(")").toString());
                  } else {
                     for(int i = 0; i < x.cols(); ++i) {
                        for(int j = 0; j < a.cols(); ++j) {
                           long v = x.apply$mcJ$sp(j, i);

                           for(int k = a.colPtrs()[j]; k < a.colPtrs()[j + 1]; ++k) {
                              int var15 = a.rowIndices()[k];
                              sink.update$mcJ$sp(var15, i, sink.apply$mcJ$sp(var15, i) + v * a.data$mcJ$sp()[k]);
                           }
                        }
                     }

                  }
               }
            }
         }
      });
   }
}
