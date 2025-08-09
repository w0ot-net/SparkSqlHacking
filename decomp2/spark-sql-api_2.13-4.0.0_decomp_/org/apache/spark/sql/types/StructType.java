package org.apache.spark.sql.types;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import org.apache.spark.SparkIllegalArgumentException;
import org.apache.spark.annotation.Stable;
import org.apache.spark.sql.catalyst.parser.DataTypeParser$;
import org.apache.spark.sql.catalyst.trees.Origin;
import org.apache.spark.sql.catalyst.trees.Origin$;
import org.apache.spark.sql.catalyst.util.CaseInsensitiveMap;
import org.apache.spark.sql.catalyst.util.CaseInsensitiveMap$;
import org.apache.spark.sql.catalyst.util.SparkStringUtils$;
import org.apache.spark.sql.catalyst.util.StringConcat;
import org.apache.spark.sql.catalyst.util.StringConcat$;
import org.apache.spark.sql.errors.DataTypeErrors$;
import org.apache.spark.sql.internal.SqlApiConf$;
import org.json4s.JObject;
import scala.;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableFactory;
import scala.collection.IterableFactoryDefaults;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.LazyZip2;
import scala.collection.Map;
import scala.collection.Searching;
import scala.collection.SeqFactory;
import scala.collection.SeqOps;
import scala.collection.SeqView;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.View;
import scala.collection.WithFilter;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.LazyList;
import scala.collection.immutable.List;
import scala.collection.immutable.Range;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.ArraySeq;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@Stable
@ScalaSignature(
   bytes = "\u0006\u0005\r}g\u0001B*U\u0001~C\u0001\u0002 \u0001\u0003\u0016\u0004%\t! \u0005\n\u0003\u0007\u0001!\u0011#Q\u0001\nyDq!!\u0002\u0001\t\u0003\t9\u0001C\u0004\u0002\u0006\u0001!\t!!\u0004\t\u000f\u0005=\u0001\u0001\"\u0001\u0002\u0012!9\u0011Q\u0005\u0001\u0005\u0002\u0005E\u0001BCA\u0014\u0001!\u0015\r\u0011\"\u0003\u0002*!Q\u0011\u0011\u0007\u0001\t\u0006\u0004%I!a\r\t\u0015\u0005\u0005\u0003\u0001#b\u0001\n\u0013\t\u0019\u0005\u0003\u0006\u0002N\u0001A)\u0019!C\u0005\u0003\u001fBq!!\u0019\u0001\t\u0003\n\u0019\u0007C\u0004\u0002v\u0001!\t%a\u001e\t\u0015\u0005e\u0004\u0001#b\u0001\n\u0013\tY\bC\u0004\u0002~\u0001!\t%a \t\u000f\u0005\u0005\u0005\u0001\"\u0001\u0002\u0004\"9\u0011\u0011\u0011\u0001\u0005\u0002\u0005%\u0005bBAA\u0001\u0011\u0005\u00111\u0013\u0005\b\u0003\u0003\u0003A\u0011AAO\u0011\u001d\t\t\t\u0001C\u0001\u0003_Cq!!!\u0001\t\u0003\tY\fC\u0004\u0002\u0002\u0002!\t!!1\t\u000f\u0005\u0005\u0005\u0001\"\u0001\u0002J\"9\u0011\u0011\u0011\u0001\u0005\u0002\u0005M\u0007bBAo\u0001\u0011\u0005\u0011q\u001c\u0005\b\u0003;\u0004A\u0011AAr\u0011\u001d\t9\u000f\u0001C\u0001\u0003SD\u0001\"!<\u0001\t\u00031\u0016q\u001e\u0005\t\u0003s\u0004A\u0011\u0001,\u0002|\"A\u0011q \u0001\u0005\u0002a\u0013\t\u0001\u0003\u0006\u0003<\u0001\t\n\u0011\"\u0001Y\u0005{A!Ba\u0015\u0001#\u0003%\t\u0001\u0017B+\u0011)\u0011I\u0006AI\u0001\n\u0003A&1\f\u0005\b\u0005?\u0002A\u0011\u0001B1\u0011\u001d\u0011y\u0006\u0001C\u0001\u0005GBqA!\u001b\u0001\t\u0003\u0011Y\u0007\u0003\u0005\u0003t\u0001!\tA\u0016B;\u0011!\u00119\t\u0001C!-\n%\u0005bBAo\u0001\u0011\u0005#q\u0014\u0005\b\u0005G\u0003A\u0011IA>\u0011\u001d\u0011)\u000b\u0001C!\u0005OCqAa,\u0001\t\u0003\nY\bC\u0004\u00032\u0002!\tE!\u0019\t\u000f\tM\u0006\u0001\"\u0011\u0003b!1q\u000b\u0001C!\u0005CBqA!.\u0001\t\u0003\u0011\t\u0007\u0003\u0005\u00032\u0002!\tE\u0016B\\\u0011!\u0011i\f\u0001C\u0001-\n}\u0006B\u0003Bd\u0001E\u0005I\u0011\u0001,\u0003>!A!\u0011\u001a\u0001\u0005Ba\u0013Y\rC\u0004\u0003N\u0002!\tAa3\t\u0011\t=\u0007\u0001\"\u0011Y\u0005#D\u0001B!8\u0001\t\u0003B&q\u001c\u0005\n\u0005S\u0004\u0011\u0011!C\u0001\u0005WD\u0011Ba<\u0001#\u0003%\tA!=\t\u0013\tU\b!!A\u0005B\t]\b\"CB\u0004\u0001\u0005\u0005I\u0011AA>\u0011%\u0019I\u0001AA\u0001\n\u0003\u0019Y\u0001C\u0005\u0004\u0012\u0001\t\t\u0011\"\u0011\u0004\u0014!I1\u0011\u0004\u0001\u0002\u0002\u0013\u000531D\u0004\b\u0007W!\u0006\u0012AB\u0017\r\u0019\u0019F\u000b#\u0001\u00040!9\u0011QA\u001f\u0005\u0002\r\u0005\u0003BCB\"{\t\u0007I\u0011\u0001,\u0003x\"A1QI\u001f!\u0002\u0013\u0011I\u0010\u0003\u0005\u0004Hu\"\tEVB%\u0011!\u0019Y%\u0010C!-\u000e5\u0003\u0002\u0003BY{\u0011\u0005cK!\u0019\t\u0011\rMS\b\"\u0001W\u0007+Bqaa\u0017>\t\u0003\u0019i\u0006C\u0004\u0002^v\"\taa\u0019\t\u000f\u0005uW\b\"\u0001\u0004h!A1QO\u001f\u0005\u0002Y\u001b9\b\u0003\u0005\u0004\u0002v\"\tAVBB\u0011!\u0011i,\u0010C\u0001-\u000e5\u0005BCBK{E\u0005I\u0011\u0001,\u0003>!91qS\u001f\u0005\n\re\u0005\u0002CBU{\u0011\u0005aka+\t\u0015\rEV(%A\u0005\u0002Y\u0013i\u0004C\u0004\u00044v\"\ta!.\t\u0013\u0005uW(!A\u0005\u0002\u000e\r\u0007\"CBd{\u0005\u0005I\u0011QBe\u0011%\u0019\t.PA\u0001\n\u0013\u0019\u0019N\u0001\u0006TiJ,8\r\u001e+za\u0016T!!\u0016,\u0002\u000bQL\b/Z:\u000b\u0005]C\u0016aA:rY*\u0011\u0011LW\u0001\u0006gB\f'o\u001b\u0006\u00037r\u000ba!\u00199bG\",'\"A/\u0002\u0007=\u0014xm\u0001\u0001\u0014\u000b\u0001\u0001G-^=\u0011\u0005\u0005\u0014W\"\u0001+\n\u0005\r$&\u0001\u0003#bi\u0006$\u0016\u0010]3\u0011\u0007\u0015|'O\u0004\u0002gY:\u0011qM[\u0007\u0002Q*\u0011\u0011NX\u0001\u0007yI|w\u000e\u001e \n\u0003-\fQa]2bY\u0006L!!\u001c8\u0002\u000fA\f7m[1hK*\t1.\u0003\u0002qc\n\u00191+Z9\u000b\u00055t\u0007CA1t\u0013\t!HKA\u0006TiJ,8\r\u001e$jK2$\u0007C\u0001<x\u001b\u0005q\u0017B\u0001=o\u0005\u001d\u0001&o\u001c3vGR\u0004\"!\u001a>\n\u0005m\f(\u0001D*fe&\fG.\u001b>bE2,\u0017A\u00024jK2$7/F\u0001\u007f!\r1xP]\u0005\u0004\u0003\u0003q'!B!se\u0006L\u0018a\u00024jK2$7\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\t\u0005%\u00111\u0002\t\u0003C\u0002AQ\u0001`\u0002A\u0002y$\"!!\u0003\u0002\u0015\u0019LW\r\u001c3OC6,7/\u0006\u0002\u0002\u0014A!ao`A\u000b!\u0011\t9\"a\b\u000f\t\u0005e\u00111\u0004\t\u0003O:L1!!\bo\u0003\u0019\u0001&/\u001a3fM&!\u0011\u0011EA\u0012\u0005\u0019\u0019FO]5oO*\u0019\u0011Q\u00048\u0002\u000b9\fW.Z:\u0002\u001b\u0019LW\r\u001c3OC6,7oU3u+\t\tY\u0003\u0005\u0004\u0002\u0018\u00055\u0012QC\u0005\u0005\u0003_\t\u0019CA\u0002TKR\f1B\\1nKR{g)[3mIV\u0011\u0011Q\u0007\t\b\u0003o\ti$!\u0006s\u001b\t\tIDC\u0002\u0002<9\f!bY8mY\u0016\u001cG/[8o\u0013\u0011\ty$!\u000f\u0003\u00075\u000b\u0007/A\u0006oC6,Gk\\%oI\u0016DXCAA#!!\t9$!\u0010\u0002\u0016\u0005\u001d\u0003c\u0001<\u0002J%\u0019\u00111\n8\u0003\u0007%sG/\u0001\u000eoC6,Gk\\%oI\u0016D8)Y:f\u0013:\u001cXM\\:ji&4X-\u0006\u0002\u0002RA1\u00111KA/\u0003\u000fj!!!\u0016\u000b\t\u0005]\u0013\u0011L\u0001\u0005kRLGNC\u0002\u0002\\Y\u000b\u0001bY1uC2L8\u000f^\u0005\u0005\u0003?\n)F\u0001\nDCN,\u0017J\\:f]NLG/\u001b<f\u001b\u0006\u0004\u0018AB3rk\u0006d7\u000f\u0006\u0003\u0002f\u0005-\u0004c\u0001<\u0002h%\u0019\u0011\u0011\u000e8\u0003\u000f\t{w\u000e\\3b]\"9\u0011QN\u0006A\u0002\u0005=\u0014\u0001\u0002;iCR\u00042A^A9\u0013\r\t\u0019H\u001c\u0002\u0004\u0003:L\u0018\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005U\u0011!C0iCND7i\u001c3f+\t\t9%\u0001\u0005iCND7i\u001c3f)\t\t9%A\u0002bI\u0012$B!!\u0003\u0002\u0006\"1\u0011qQ\bA\u0002I\fQAZ5fY\u0012$b!!\u0003\u0002\f\u0006=\u0005bBAG!\u0001\u0007\u0011QC\u0001\u0005]\u0006lW\r\u0003\u0004\u0002\u0012B\u0001\r\u0001Y\u0001\tI\u0006$\u0018\rV=qKRA\u0011\u0011BAK\u0003/\u000bI\nC\u0004\u0002\u000eF\u0001\r!!\u0006\t\r\u0005E\u0015\u00031\u0001a\u0011\u001d\tY*\u0005a\u0001\u0003K\n\u0001B\\;mY\u0006\u0014G.\u001a\u000b\u000b\u0003\u0013\ty*!)\u0002$\u0006\u0015\u0006bBAG%\u0001\u0007\u0011Q\u0003\u0005\u0007\u0003#\u0013\u0002\u0019\u00011\t\u000f\u0005m%\u00031\u0001\u0002f!9\u0011q\u0015\nA\u0002\u0005%\u0016\u0001C7fi\u0006$\u0017\r^1\u0011\u0007\u0005\fY+C\u0002\u0002.R\u0013\u0001\"T3uC\u0012\fG/\u0019\u000b\u000b\u0003\u0013\t\t,a-\u00026\u0006]\u0006bBAG'\u0001\u0007\u0011Q\u0003\u0005\u0007\u0003#\u001b\u0002\u0019\u00011\t\u000f\u0005m5\u00031\u0001\u0002f!9\u0011\u0011X\nA\u0002\u0005U\u0011aB2p[6,g\u000e\u001e\u000b\u0007\u0003\u0013\ti,a0\t\u000f\u00055E\u00031\u0001\u0002\u0016!9\u0011\u0011\u0013\u000bA\u0002\u0005UA\u0003CA\u0005\u0003\u0007\f)-a2\t\u000f\u00055U\u00031\u0001\u0002\u0016!9\u0011\u0011S\u000bA\u0002\u0005U\u0001bBAN+\u0001\u0007\u0011Q\r\u000b\u000b\u0003\u0013\tY-!4\u0002P\u0006E\u0007bBAG-\u0001\u0007\u0011Q\u0003\u0005\b\u0003#3\u0002\u0019AA\u000b\u0011\u001d\tYJ\u0006a\u0001\u0003KBq!a*\u0017\u0001\u0004\tI\u000b\u0006\u0006\u0002\n\u0005U\u0017q[Am\u00037Dq!!$\u0018\u0001\u0004\t)\u0002C\u0004\u0002\u0012^\u0001\r!!\u0006\t\u000f\u0005mu\u00031\u0001\u0002f!9\u0011\u0011X\fA\u0002\u0005U\u0011!B1qa2LHc\u0001:\u0002b\"9\u0011Q\u0012\rA\u0002\u0005UA\u0003BA\u0005\u0003KDq!!\n\u001a\u0001\u0004\tY#\u0001\u0006gS\u0016dG-\u00138eKb$B!a\u0012\u0002l\"9\u0011Q\u0012\u000eA\u0002\u0005U\u0011!D4fi\u001aKW\r\u001c3J]\u0012,\u0007\u0010\u0006\u0003\u0002r\u0006]\b#\u0002<\u0002t\u0006\u001d\u0013bAA{]\n1q\n\u001d;j_:Dq!!$\u001c\u0001\u0004\t)\"\u0001\u000fhKR4\u0015.\u001a7e\u0013:$W\r_\"bg\u0016Len]3og&$\u0018N^3\u0015\t\u0005E\u0018Q \u0005\b\u0003\u001bc\u0002\u0019AA\u000b\u0003=1\u0017N\u001c3OKN$X\r\u001a$jK2$GC\u0003B\u0002\u0005\u001b\u0011yAa\u0005\u0003,A)a/a=\u0003\u0006A1aOa\u0002\u0003\fIL1A!\u0003o\u0005\u0019!V\u000f\u001d7feA!Qm\\A\u000b\u0011\u001d\ty!\ba\u0001\u0005\u0017A\u0011B!\u0005\u001e!\u0003\u0005\r!!\u001a\u0002%%t7\r\\;eK\u000e{G\u000e\\3di&|gn\u001d\u0005\n\u0005+i\u0002\u0013!a\u0001\u0005/\t\u0001B]3t_24XM\u001d\t\u0005\u00053\u0011)C\u0004\u0003\u0003\u001c\t\u0005RB\u0001B\u000f\u0015\u0011\u0011y\"!\u0017\u0002\u0011\u0005t\u0017\r\\=tSNLAAa\t\u0003\u001e\u0005q1+\u001d7Ba&\fe.\u00197zg&\u001c\u0018\u0002\u0002B\u0014\u0005S\u0011\u0001BU3t_24XM\u001d\u0006\u0005\u0005G\u0011i\u0002C\u0005\u0003.u\u0001\n\u00111\u0001\u00030\u000591m\u001c8uKb$\b\u0003\u0002B\u0019\u0005oi!Aa\r\u000b\t\tU\u0012\u0011L\u0001\u0006iJ,Wm]\u0005\u0005\u0005s\u0011\u0019D\u0001\u0004Pe&<\u0017N\\\u0001\u001aM&tGMT3ti\u0016$g)[3mI\u0012\"WMZ1vYR$#'\u0006\u0002\u0003@)\"\u0011Q\rB!W\t\u0011\u0019\u0005\u0005\u0003\u0003F\t=SB\u0001B$\u0015\u0011\u0011IEa\u0013\u0002\u0013Ut7\r[3dW\u0016$'b\u0001B']\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\tE#q\t\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017!\u00074j]\u0012tUm\u001d;fI\u001aKW\r\u001c3%I\u00164\u0017-\u001e7uIM*\"Aa\u0016+\t\t]!\u0011I\u0001\u001aM&tGMT3ti\u0016$g)[3mI\u0012\"WMZ1vYR$C'\u0006\u0002\u0003^)\"!q\u0006B!\u0003)!(/Z3TiJLgnZ\u000b\u0003\u0003+!B!!\u0006\u0003f!9!q\r\u0012A\u0002\u0005\u001d\u0013\u0001C7bq\u0012+\u0007\u000f\u001e5\u0002\u001fA\u0014\u0018N\u001c;Ue\u0016,7\u000b\u001e:j]\u001e$\"A!\u001c\u0011\u0007Y\u0014y'C\u0002\u0003r9\u0014A!\u00168ji\u0006!\"-^5mI\u001a{'/\\1ui\u0016$7\u000b\u001e:j]\u001e$\u0002B!\u001c\u0003x\tm$Q\u0011\u0005\b\u0005s\"\u0003\u0019AA\u000b\u0003\u0019\u0001(/\u001a4jq\"9!Q\u0010\u0013A\u0002\t}\u0014\u0001D:ue&twmQ8oG\u0006$\b\u0003BA*\u0005\u0003KAAa!\u0002V\ta1\u000b\u001e:j]\u001e\u001cuN\\2bi\"9!q\r\u0013A\u0002\u0005\u001d\u0013!\u00036t_:4\u0016\r\\;f+\t\u0011Y\t\u0005\u0003\u0003\u000e\nee\u0002\u0002BH\u0005+k!A!%\u000b\u0007\tME,\u0001\u0004kg>tGg]\u0005\u0005\u0005/\u0013\t*A\u0004Kg>t\u0017i\u0015+\n\t\tm%Q\u0014\u0002\b\u0015>\u0013'.Z2u\u0015\u0011\u00119J!%\u0015\u0007I\u0014\t\u000bC\u0004\u0002h\u001a\u0002\r!a\u0012\u0002\r1,gn\u001a;i\u0003!IG/\u001a:bi>\u0014XC\u0001BU!\u0011)'1\u0016:\n\u0007\t5\u0016O\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003-!WMZ1vYR\u001c\u0016N_3\u0002\u0019MLW\u000e\u001d7f'R\u0014\u0018N\\4\u0002\u001b\r\fG/\u00197pON#(/\u001b8h\u0003\u0015!x\u000e\u0012#M)\u0011\t)B!/\t\u000f\tmf\u00061\u0001\u0002H\u0005yQ.\u0019=Ok6\u0014WM\u001d$jK2$7/A\u0003nKJ<W\r\u0006\u0004\u0002\n\t\u0005'1\u0019\u0005\b\u0003[z\u0003\u0019AA\u0005\u0011%\u0011)m\fI\u0001\u0002\u0004\t)'A\u0007dCN,7+\u001a8tSRLg/Z\u0001\u0010[\u0016\u0014x-\u001a\u0013eK\u001a\fW\u000f\u001c;%e\u0005Q\u0011m\u001d(vY2\f'\r\\3\u0016\u0005\u0005%\u0011A\u0003;p\u001dVdG.\u00192mK\u0006\tR\r_5tiN\u0014VmY;sg&4X\r\\=\u0015\t\u0005\u0015$1\u001b\u0005\b\u0005+\u001c\u0004\u0019\u0001Bl\u0003\u00051\u0007C\u0002<\u0003Z\u0002\f)'C\u0002\u0003\\:\u0014\u0011BR;oGRLwN\\\u0019\u0002)Q\u0014\u0018M\\:g_Jl'+Z2veNLg/\u001a7z)\r\u0001'\u0011\u001d\u0005\b\u0005+$\u0004\u0019\u0001Br!\u00151(Q\u001d1a\u0013\r\u00119O\u001c\u0002\u0010!\u0006\u0014H/[1m\rVt7\r^5p]\u0006!1m\u001c9z)\u0011\tIA!<\t\u000fq,\u0004\u0013!a\u0001}\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nTC\u0001BzU\rq(\u0011I\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\te\b\u0003\u0002B~\u0007\u000bi!A!@\u000b\t\t}8\u0011A\u0001\u0005Y\u0006twM\u0003\u0002\u0004\u0004\u0005!!.\u0019<b\u0013\u0011\t\tC!@\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!\u0011qNB\u0007\u0011%\u0019y!OA\u0001\u0002\u0004\t9%A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0007+\u0001b!a\u000e\u0004\u0018\u0005=\u0014\u0002\u0002BW\u0003s\t!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!!\u0011`B\u000f\u0011%\u0019yaOA\u0001\u0002\u0004\t9\u0005K\u0002\u0001\u0007C\u0001Baa\t\u0004(5\u00111Q\u0005\u0006\u0004\u0005\u001bB\u0016\u0002BB\u0015\u0007K\u0011aa\u0015;bE2,\u0017AC*ueV\u001cG\u000fV=qKB\u0011\u0011-P\n\u0006{\rE2q\u0007\t\u0004C\u000eM\u0012bAB\u001b)\n\u0001\u0012IY:ue\u0006\u001cG\u000fR1uCRK\b/\u001a\t\u0005\u0007s\u0019y$\u0004\u0002\u0004<)!1QHB\u0001\u0003\tIw.C\u0002|\u0007w!\"a!\f\u0002CM\u000bFj\u0018$V\u001d\u000e#\u0016j\u0014(`\t\u00163\u0015)\u0016'U?6+E+\u0011#B)\u0006{6*R-\u0002EM\u000bFj\u0018$V\u001d\u000e#\u0016j\u0014(`\t\u00163\u0015)\u0016'U?6+E+\u0011#B)\u0006{6*R-!\u0003M!WMZ1vYR\u001cuN\\2sKR,G+\u001f9f+\u0005\u0001\u0017aC1dG\u0016\u0004Ho\u001d+za\u0016$B!!\u001a\u0004P!11\u0011\u000b\"A\u0002\u0001\fQa\u001c;iKJ\f!B\u001a:p[N#(/\u001b8h)\u0011\tIaa\u0016\t\u000f\reC\t1\u0001\u0002\u0016\u0005\u0019!/Y<\u0002\u000f\u0019\u0014x.\u001c#E\u0019R!\u0011\u0011BB0\u0011\u001d\u0019\t'\u0012a\u0001\u0003+\t1\u0001\u001a3m)\u0011\tIa!\u001a\t\u000bq4\u0005\u0019\u00013\u0015\t\u0005%1\u0011\u000e\u0005\u0007y\u001e\u0003\raa\u001b\u0011\u000b\r54\u0011\u000f:\u000e\u0005\r=$\u0002BA,\u0007\u0003IAaa\u001d\u0004p\t!A*[:u\u00039\u0011X-\\8wK6+G/\u00193bi\u0006$R\u0001YB=\u0007{Bqaa\u001fI\u0001\u0004\t)\"A\u0002lKfDaaa I\u0001\u0004\u0001\u0017A\u00013u\u00039)h.[8o\u0019&\\W-T3sO\u0016$R\u0001YBC\u0007\u0013Caaa\"J\u0001\u0004\u0001\u0017\u0001\u00027fMRDaaa#J\u0001\u0004\u0001\u0017!\u0002:jO\"$Hc\u00021\u0004\u0010\u000eE51\u0013\u0005\u0007\u0007\u000fS\u0005\u0019\u00011\t\r\r-%\n1\u0001a\u0011%\u0011)M\u0013I\u0001\u0002\u0004\t)'A\bnKJ<W\r\n3fM\u0006,H\u000e\u001e\u00134\u00035iWM]4f\u0013:$XM\u001d8bYR9\u0001ma'\u0004\u001e\u000e}\u0005BBBD\u0019\u0002\u0007\u0001\r\u0003\u0004\u0004\f2\u0003\r\u0001\u0019\u0005\b\u0007Cc\u0005\u0019ABR\u0003-iWM]4f'R\u0014Xo\u0019;\u0011\u0013Y\u001c)+!\u0003\u0002\n\u0005%\u0011bABT]\nIa)\u001e8di&|gNM\u0001\nM&,G\u000eZ:NCB$b!!\u000e\u0004.\u000e=\u0006\"\u0002?N\u0001\u0004q\b\"\u0003Bc\u001bB\u0005\t\u0019AA3\u0003M1\u0017.\u001a7eg6\u000b\u0007\u000f\n3fM\u0006,H\u000e\u001e\u00133\u0003E1\u0017N\u001c3NSN\u001c\u0018N\\4GS\u0016dGm\u001d\u000b\t\u0007o\u001bIl!0\u0004BB)a/a=\u0002\n!911X(A\u0002\u0005%\u0011AB:pkJ\u001cW\rC\u0004\u0004@>\u0003\r!!\u0003\u0002\rQ\f'oZ3u\u0011\u001d\u0011)b\u0014a\u0001\u0005/!B!!\u0003\u0004F\")A\u0010\u0015a\u0001}\u00069QO\\1qa2LH\u0003BBf\u0007\u001b\u0004BA^Az}\"I1qZ)\u0002\u0002\u0003\u0007\u0011\u0011B\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCABk!\u0011\u0011Ypa6\n\t\re'Q \u0002\u0007\u001f\nTWm\u0019;)\u0007u\u001a\t\u0003K\u0002=\u0007C\u0001"
)
public class StructType extends DataType implements Seq, Product, Serializable {
   private Set fieldNamesSet;
   private Map nameToField;
   private Map nameToIndex;
   private CaseInsensitiveMap nameToIndexCaseInsensitive;
   private int _hashCode;
   private final StructField[] fields;
   private volatile byte bitmap$0;

   public static Option findMissingFields(final StructType source, final StructType target, final Function2 resolver) {
      return StructType$.MODULE$.findMissingFields(source, target, resolver);
   }

   public static StructType fromDDL(final String ddl) {
      return StructType$.MODULE$.fromDDL(ddl);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public final Seq toSeq() {
      return Seq.toSeq$(this);
   }

   public SeqFactory iterableFactory() {
      return Seq.iterableFactory$(this);
   }

   public boolean canEqual(final Object that) {
      return scala.collection.Seq.canEqual$(this, that);
   }

   public String stringPrefix() {
      return scala.collection.Seq.stringPrefix$(this);
   }

   // $FF: synthetic method
   public Object scala$collection$SeqOps$$super$concat(final IterableOnce suffix) {
      return IterableOps.concat$(this, suffix);
   }

   // $FF: synthetic method
   public int scala$collection$SeqOps$$super$sizeCompare(final int otherSize) {
      return IterableOps.sizeCompare$(this, otherSize);
   }

   // $FF: synthetic method
   public int scala$collection$SeqOps$$super$sizeCompare(final Iterable that) {
      return IterableOps.sizeCompare$(this, that);
   }

   public SeqView view() {
      return SeqOps.view$(this);
   }

   public Object prepended(final Object elem) {
      return SeqOps.prepended$(this, elem);
   }

   public final Object $plus$colon(final Object elem) {
      return SeqOps.$plus$colon$(this, elem);
   }

   public Object appended(final Object elem) {
      return SeqOps.appended$(this, elem);
   }

   public final Object $colon$plus(final Object elem) {
      return SeqOps.$colon$plus$(this, elem);
   }

   public Object prependedAll(final IterableOnce prefix) {
      return SeqOps.prependedAll$(this, prefix);
   }

   public final Object $plus$plus$colon(final IterableOnce prefix) {
      return SeqOps.$plus$plus$colon$(this, prefix);
   }

   public Object appendedAll(final IterableOnce suffix) {
      return SeqOps.appendedAll$(this, suffix);
   }

   public final Object $colon$plus$plus(final IterableOnce suffix) {
      return SeqOps.$colon$plus$plus$(this, suffix);
   }

   public final Object concat(final IterableOnce suffix) {
      return SeqOps.concat$(this, suffix);
   }

   /** @deprecated */
   public final Object union(final scala.collection.Seq that) {
      return SeqOps.union$(this, that);
   }

   public final int size() {
      return SeqOps.size$(this);
   }

   public Object distinct() {
      return SeqOps.distinct$(this);
   }

   public Object distinctBy(final Function1 f) {
      return SeqOps.distinctBy$(this, f);
   }

   public Object reverse() {
      return SeqOps.reverse$(this);
   }

   public Iterator reverseIterator() {
      return SeqOps.reverseIterator$(this);
   }

   public boolean startsWith(final IterableOnce that, final int offset) {
      return SeqOps.startsWith$(this, that, offset);
   }

   public int startsWith$default$2() {
      return SeqOps.startsWith$default$2$(this);
   }

   public boolean endsWith(final Iterable that) {
      return SeqOps.endsWith$(this, that);
   }

   public boolean isDefinedAt(final int idx) {
      return SeqOps.isDefinedAt$(this, idx);
   }

   public Object padTo(final int len, final Object elem) {
      return SeqOps.padTo$(this, len, elem);
   }

   public final int segmentLength(final Function1 p) {
      return SeqOps.segmentLength$(this, p);
   }

   public int segmentLength(final Function1 p, final int from) {
      return SeqOps.segmentLength$(this, p, from);
   }

   /** @deprecated */
   public final int prefixLength(final Function1 p) {
      return SeqOps.prefixLength$(this, p);
   }

   public int indexWhere(final Function1 p, final int from) {
      return SeqOps.indexWhere$(this, p, from);
   }

   public int indexWhere(final Function1 p) {
      return SeqOps.indexWhere$(this, p);
   }

   public int indexOf(final Object elem, final int from) {
      return SeqOps.indexOf$(this, elem, from);
   }

   public int indexOf(final Object elem) {
      return SeqOps.indexOf$(this, elem);
   }

   public int lastIndexOf(final Object elem, final int end) {
      return SeqOps.lastIndexOf$(this, elem, end);
   }

   public int lastIndexOf$default$2() {
      return SeqOps.lastIndexOf$default$2$(this);
   }

   public int lastIndexWhere(final Function1 p, final int end) {
      return SeqOps.lastIndexWhere$(this, p, end);
   }

   public int lastIndexWhere(final Function1 p) {
      return SeqOps.lastIndexWhere$(this, p);
   }

   public int indexOfSlice(final scala.collection.Seq that, final int from) {
      return SeqOps.indexOfSlice$(this, that, from);
   }

   public int indexOfSlice(final scala.collection.Seq that) {
      return SeqOps.indexOfSlice$(this, that);
   }

   public int lastIndexOfSlice(final scala.collection.Seq that, final int end) {
      return SeqOps.lastIndexOfSlice$(this, that, end);
   }

   public int lastIndexOfSlice(final scala.collection.Seq that) {
      return SeqOps.lastIndexOfSlice$(this, that);
   }

   public Option findLast(final Function1 p) {
      return SeqOps.findLast$(this, p);
   }

   public boolean containsSlice(final scala.collection.Seq that) {
      return SeqOps.containsSlice$(this, that);
   }

   public boolean contains(final Object elem) {
      return SeqOps.contains$(this, elem);
   }

   /** @deprecated */
   public Object reverseMap(final Function1 f) {
      return SeqOps.reverseMap$(this, f);
   }

   public Iterator permutations() {
      return SeqOps.permutations$(this);
   }

   public Iterator combinations(final int n) {
      return SeqOps.combinations$(this, n);
   }

   public Object sorted(final Ordering ord) {
      return SeqOps.sorted$(this, ord);
   }

   public Object sortWith(final Function2 lt) {
      return SeqOps.sortWith$(this, lt);
   }

   public Object sortBy(final Function1 f, final Ordering ord) {
      return SeqOps.sortBy$(this, f, ord);
   }

   public Range indices() {
      return SeqOps.indices$(this);
   }

   public final int sizeCompare(final int otherSize) {
      return SeqOps.sizeCompare$(this, otherSize);
   }

   public int lengthCompare(final int len) {
      return SeqOps.lengthCompare$(this, len);
   }

   public final int sizeCompare(final Iterable that) {
      return SeqOps.sizeCompare$(this, that);
   }

   public int lengthCompare(final Iterable that) {
      return SeqOps.lengthCompare$(this, that);
   }

   public final IterableOps lengthIs() {
      return SeqOps.lengthIs$(this);
   }

   public boolean isEmpty() {
      return SeqOps.isEmpty$(this);
   }

   public boolean sameElements(final IterableOnce that) {
      return SeqOps.sameElements$(this, that);
   }

   public boolean corresponds(final scala.collection.Seq that, final Function2 p) {
      return SeqOps.corresponds$(this, that, p);
   }

   public Object diff(final scala.collection.Seq that) {
      return SeqOps.diff$(this, that);
   }

   public Object intersect(final scala.collection.Seq that) {
      return SeqOps.intersect$(this, that);
   }

   public Object patch(final int from, final IterableOnce other, final int replaced) {
      return SeqOps.patch$(this, from, other, replaced);
   }

   public Object updated(final int index, final Object elem) {
      return SeqOps.updated$(this, index, elem);
   }

   public scala.collection.mutable.Map occCounts(final scala.collection.Seq sq) {
      return SeqOps.occCounts$(this, sq);
   }

   public Searching.SearchResult search(final Object elem, final Ordering ord) {
      return SeqOps.search$(this, elem, ord);
   }

   public Searching.SearchResult search(final Object elem, final int from, final int to, final Ordering ord) {
      return SeqOps.search$(this, elem, from, to, ord);
   }

   public Option unapply(final Object a) {
      return PartialFunction.unapply$(this, a);
   }

   public PartialFunction elementWise() {
      return PartialFunction.elementWise$(this);
   }

   public PartialFunction orElse(final PartialFunction that) {
      return PartialFunction.orElse$(this, that);
   }

   public PartialFunction andThen(final Function1 k) {
      return PartialFunction.andThen$(this, k);
   }

   public PartialFunction andThen(final PartialFunction k) {
      return PartialFunction.andThen$(this, k);
   }

   public PartialFunction compose(final PartialFunction k) {
      return PartialFunction.compose$(this, k);
   }

   public Function1 lift() {
      return PartialFunction.lift$(this);
   }

   public Object applyOrElse(final Object x, final Function1 default) {
      return PartialFunction.applyOrElse$(this, x, default);
   }

   public Function1 runWith(final Function1 action) {
      return PartialFunction.runWith$(this, action);
   }

   public boolean apply$mcZD$sp(final double v1) {
      return Function1.apply$mcZD$sp$(this, v1);
   }

   public double apply$mcDD$sp(final double v1) {
      return Function1.apply$mcDD$sp$(this, v1);
   }

   public float apply$mcFD$sp(final double v1) {
      return Function1.apply$mcFD$sp$(this, v1);
   }

   public int apply$mcID$sp(final double v1) {
      return Function1.apply$mcID$sp$(this, v1);
   }

   public long apply$mcJD$sp(final double v1) {
      return Function1.apply$mcJD$sp$(this, v1);
   }

   public void apply$mcVD$sp(final double v1) {
      Function1.apply$mcVD$sp$(this, v1);
   }

   public boolean apply$mcZF$sp(final float v1) {
      return Function1.apply$mcZF$sp$(this, v1);
   }

   public double apply$mcDF$sp(final float v1) {
      return Function1.apply$mcDF$sp$(this, v1);
   }

   public float apply$mcFF$sp(final float v1) {
      return Function1.apply$mcFF$sp$(this, v1);
   }

   public int apply$mcIF$sp(final float v1) {
      return Function1.apply$mcIF$sp$(this, v1);
   }

   public long apply$mcJF$sp(final float v1) {
      return Function1.apply$mcJF$sp$(this, v1);
   }

   public void apply$mcVF$sp(final float v1) {
      Function1.apply$mcVF$sp$(this, v1);
   }

   public boolean apply$mcZI$sp(final int v1) {
      return Function1.apply$mcZI$sp$(this, v1);
   }

   public double apply$mcDI$sp(final int v1) {
      return Function1.apply$mcDI$sp$(this, v1);
   }

   public float apply$mcFI$sp(final int v1) {
      return Function1.apply$mcFI$sp$(this, v1);
   }

   public int apply$mcII$sp(final int v1) {
      return Function1.apply$mcII$sp$(this, v1);
   }

   public long apply$mcJI$sp(final int v1) {
      return Function1.apply$mcJI$sp$(this, v1);
   }

   public void apply$mcVI$sp(final int v1) {
      Function1.apply$mcVI$sp$(this, v1);
   }

   public boolean apply$mcZJ$sp(final long v1) {
      return Function1.apply$mcZJ$sp$(this, v1);
   }

   public double apply$mcDJ$sp(final long v1) {
      return Function1.apply$mcDJ$sp$(this, v1);
   }

   public float apply$mcFJ$sp(final long v1) {
      return Function1.apply$mcFJ$sp$(this, v1);
   }

   public int apply$mcIJ$sp(final long v1) {
      return Function1.apply$mcIJ$sp$(this, v1);
   }

   public long apply$mcJJ$sp(final long v1) {
      return Function1.apply$mcJJ$sp$(this, v1);
   }

   public void apply$mcVJ$sp(final long v1) {
      Function1.apply$mcVJ$sp$(this, v1);
   }

   public Function1 compose(final Function1 g) {
      return Function1.compose$(this, g);
   }

   /** @deprecated */
   public final Iterable toIterable() {
      return Iterable.toIterable$(this);
   }

   public final Iterable coll() {
      return Iterable.coll$(this);
   }

   /** @deprecated */
   public Iterable seq() {
      return Iterable.seq$(this);
   }

   public String className() {
      return Iterable.className$(this);
   }

   public final String collectionClassName() {
      return Iterable.collectionClassName$(this);
   }

   public LazyZip2 lazyZip(final Iterable that) {
      return Iterable.lazyZip$(this, that);
   }

   public IterableOps fromSpecific(final IterableOnce coll) {
      return IterableFactoryDefaults.fromSpecific$(this, coll);
   }

   public Builder newSpecificBuilder() {
      return IterableFactoryDefaults.newSpecificBuilder$(this);
   }

   public IterableOps empty() {
      return IterableFactoryDefaults.empty$(this);
   }

   /** @deprecated */
   public final Iterable toTraversable() {
      return IterableOps.toTraversable$(this);
   }

   public boolean isTraversableAgain() {
      return IterableOps.isTraversableAgain$(this);
   }

   /** @deprecated */
   public final Object repr() {
      return IterableOps.repr$(this);
   }

   /** @deprecated */
   public IterableFactory companion() {
      return IterableOps.companion$(this);
   }

   public Object head() {
      return IterableOps.head$(this);
   }

   public Option headOption() {
      return IterableOps.headOption$(this);
   }

   public Object last() {
      return IterableOps.last$(this);
   }

   public Option lastOption() {
      return IterableOps.lastOption$(this);
   }

   public final IterableOps sizeIs() {
      return IterableOps.sizeIs$(this);
   }

   /** @deprecated */
   public View view(final int from, final int until) {
      return IterableOps.view$(this, from, until);
   }

   public Object transpose(final Function1 asIterable) {
      return IterableOps.transpose$(this, asIterable);
   }

   public Object filter(final Function1 pred) {
      return IterableOps.filter$(this, pred);
   }

   public Object filterNot(final Function1 pred) {
      return IterableOps.filterNot$(this, pred);
   }

   public WithFilter withFilter(final Function1 p) {
      return IterableOps.withFilter$(this, p);
   }

   public Tuple2 partition(final Function1 p) {
      return IterableOps.partition$(this, p);
   }

   public Tuple2 splitAt(final int n) {
      return IterableOps.splitAt$(this, n);
   }

   public Object take(final int n) {
      return IterableOps.take$(this, n);
   }

   public Object takeRight(final int n) {
      return IterableOps.takeRight$(this, n);
   }

   public Object takeWhile(final Function1 p) {
      return IterableOps.takeWhile$(this, p);
   }

   public Tuple2 span(final Function1 p) {
      return IterableOps.span$(this, p);
   }

   public Object drop(final int n) {
      return IterableOps.drop$(this, n);
   }

   public Object dropRight(final int n) {
      return IterableOps.dropRight$(this, n);
   }

   public Object dropWhile(final Function1 p) {
      return IterableOps.dropWhile$(this, p);
   }

   public Iterator grouped(final int size) {
      return IterableOps.grouped$(this, size);
   }

   public Iterator sliding(final int size) {
      return IterableOps.sliding$(this, size);
   }

   public Iterator sliding(final int size, final int step) {
      return IterableOps.sliding$(this, size, step);
   }

   public Object tail() {
      return IterableOps.tail$(this);
   }

   public Object init() {
      return IterableOps.init$(this);
   }

   public Object slice(final int from, final int until) {
      return IterableOps.slice$(this, from, until);
   }

   public scala.collection.immutable.Map groupBy(final Function1 f) {
      return IterableOps.groupBy$(this, f);
   }

   public scala.collection.immutable.Map groupMap(final Function1 key, final Function1 f) {
      return IterableOps.groupMap$(this, key, f);
   }

   public scala.collection.immutable.Map groupMapReduce(final Function1 key, final Function1 f, final Function2 reduce) {
      return IterableOps.groupMapReduce$(this, key, f, reduce);
   }

   public Object scan(final Object z, final Function2 op) {
      return IterableOps.scan$(this, z, op);
   }

   public Object scanLeft(final Object z, final Function2 op) {
      return IterableOps.scanLeft$(this, z, op);
   }

   public Object scanRight(final Object z, final Function2 op) {
      return IterableOps.scanRight$(this, z, op);
   }

   public Object map(final Function1 f) {
      return IterableOps.map$(this, f);
   }

   public Object flatMap(final Function1 f) {
      return IterableOps.flatMap$(this, f);
   }

   public Object flatten(final Function1 asIterable) {
      return IterableOps.flatten$(this, asIterable);
   }

   public Object collect(final PartialFunction pf) {
      return IterableOps.collect$(this, pf);
   }

   public Tuple2 partitionMap(final Function1 f) {
      return IterableOps.partitionMap$(this, f);
   }

   public final Object $plus$plus(final IterableOnce suffix) {
      return IterableOps.$plus$plus$(this, suffix);
   }

   public Object zip(final IterableOnce that) {
      return IterableOps.zip$(this, that);
   }

   public Object zipWithIndex() {
      return IterableOps.zipWithIndex$(this);
   }

   public Object zipAll(final Iterable that, final Object thisElem, final Object thatElem) {
      return IterableOps.zipAll$(this, that, thisElem, thatElem);
   }

   public Tuple2 unzip(final Function1 asPair) {
      return IterableOps.unzip$(this, asPair);
   }

   public Tuple3 unzip3(final Function1 asTriple) {
      return IterableOps.unzip3$(this, asTriple);
   }

   public Iterator tails() {
      return IterableOps.tails$(this);
   }

   public Iterator inits() {
      return IterableOps.inits$(this);
   }

   public Object tapEach(final Function1 f) {
      return IterableOps.tapEach$(this, f);
   }

   /** @deprecated */
   public boolean hasDefiniteSize() {
      return IterableOnceOps.hasDefiniteSize$(this);
   }

   public void foreach(final Function1 f) {
      IterableOnceOps.foreach$(this, f);
   }

   public boolean forall(final Function1 p) {
      return IterableOnceOps.forall$(this, p);
   }

   public boolean exists(final Function1 p) {
      return IterableOnceOps.exists$(this, p);
   }

   public int count(final Function1 p) {
      return IterableOnceOps.count$(this, p);
   }

   public Option find(final Function1 p) {
      return IterableOnceOps.find$(this, p);
   }

   public Object foldLeft(final Object z, final Function2 op) {
      return IterableOnceOps.foldLeft$(this, z, op);
   }

   public Object foldRight(final Object z, final Function2 op) {
      return IterableOnceOps.foldRight$(this, z, op);
   }

   /** @deprecated */
   public final Object $div$colon(final Object z, final Function2 op) {
      return IterableOnceOps.$div$colon$(this, z, op);
   }

   /** @deprecated */
   public final Object $colon$bslash(final Object z, final Function2 op) {
      return IterableOnceOps.$colon$bslash$(this, z, op);
   }

   public Object fold(final Object z, final Function2 op) {
      return IterableOnceOps.fold$(this, z, op);
   }

   public Object reduce(final Function2 op) {
      return IterableOnceOps.reduce$(this, op);
   }

   public Option reduceOption(final Function2 op) {
      return IterableOnceOps.reduceOption$(this, op);
   }

   public Object reduceLeft(final Function2 op) {
      return IterableOnceOps.reduceLeft$(this, op);
   }

   public Object reduceRight(final Function2 op) {
      return IterableOnceOps.reduceRight$(this, op);
   }

   public Option reduceLeftOption(final Function2 op) {
      return IterableOnceOps.reduceLeftOption$(this, op);
   }

   public Option reduceRightOption(final Function2 op) {
      return IterableOnceOps.reduceRightOption$(this, op);
   }

   public boolean nonEmpty() {
      return IterableOnceOps.nonEmpty$(this);
   }

   /** @deprecated */
   public final void copyToBuffer(final Buffer dest) {
      IterableOnceOps.copyToBuffer$(this, dest);
   }

   public int copyToArray(final Object xs) {
      return IterableOnceOps.copyToArray$(this, xs);
   }

   public int copyToArray(final Object xs, final int start) {
      return IterableOnceOps.copyToArray$(this, xs, start);
   }

   public int copyToArray(final Object xs, final int start, final int len) {
      return IterableOnceOps.copyToArray$(this, xs, start, len);
   }

   public Object sum(final Numeric num) {
      return IterableOnceOps.sum$(this, num);
   }

   public Object product(final Numeric num) {
      return IterableOnceOps.product$(this, num);
   }

   public Object min(final Ordering ord) {
      return IterableOnceOps.min$(this, ord);
   }

   public Option minOption(final Ordering ord) {
      return IterableOnceOps.minOption$(this, ord);
   }

   public Object max(final Ordering ord) {
      return IterableOnceOps.max$(this, ord);
   }

   public Option maxOption(final Ordering ord) {
      return IterableOnceOps.maxOption$(this, ord);
   }

   public Object maxBy(final Function1 f, final Ordering ord) {
      return IterableOnceOps.maxBy$(this, f, ord);
   }

   public Option maxByOption(final Function1 f, final Ordering ord) {
      return IterableOnceOps.maxByOption$(this, f, ord);
   }

   public Object minBy(final Function1 f, final Ordering ord) {
      return IterableOnceOps.minBy$(this, f, ord);
   }

   public Option minByOption(final Function1 f, final Ordering ord) {
      return IterableOnceOps.minByOption$(this, f, ord);
   }

   public Option collectFirst(final PartialFunction pf) {
      return IterableOnceOps.collectFirst$(this, pf);
   }

   /** @deprecated */
   public Object aggregate(final Function0 z, final Function2 seqop, final Function2 combop) {
      return IterableOnceOps.aggregate$(this, z, seqop, combop);
   }

   public boolean corresponds(final IterableOnce that, final Function2 p) {
      return IterableOnceOps.corresponds$(this, that, p);
   }

   public final String mkString(final String start, final String sep, final String end) {
      return IterableOnceOps.mkString$(this, start, sep, end);
   }

   public final String mkString(final String sep) {
      return IterableOnceOps.mkString$(this, sep);
   }

   public final String mkString() {
      return IterableOnceOps.mkString$(this);
   }

   public StringBuilder addString(final StringBuilder b, final String start, final String sep, final String end) {
      return IterableOnceOps.addString$(this, b, start, sep, end);
   }

   public final StringBuilder addString(final StringBuilder b, final String sep) {
      return IterableOnceOps.addString$(this, b, sep);
   }

   public final StringBuilder addString(final StringBuilder b) {
      return IterableOnceOps.addString$(this, b);
   }

   public Object to(final Factory factory) {
      return IterableOnceOps.to$(this, factory);
   }

   /** @deprecated */
   public final Iterator toIterator() {
      return IterableOnceOps.toIterator$(this);
   }

   public List toList() {
      return IterableOnceOps.toList$(this);
   }

   public Vector toVector() {
      return IterableOnceOps.toVector$(this);
   }

   public scala.collection.immutable.Map toMap(final .less.colon.less ev) {
      return IterableOnceOps.toMap$(this, ev);
   }

   public Set toSet() {
      return IterableOnceOps.toSet$(this);
   }

   public IndexedSeq toIndexedSeq() {
      return IterableOnceOps.toIndexedSeq$(this);
   }

   /** @deprecated */
   public final Stream toStream() {
      return IterableOnceOps.toStream$(this);
   }

   public final Buffer toBuffer() {
      return IterableOnceOps.toBuffer$(this);
   }

   public Object toArray(final ClassTag evidence$2) {
      return IterableOnceOps.toArray$(this, evidence$2);
   }

   public Iterable reversed() {
      return IterableOnceOps.reversed$(this);
   }

   public Stepper stepper(final StepperShape shape) {
      return IterableOnce.stepper$(this, shape);
   }

   public int knownSize() {
      return IterableOnce.knownSize$(this);
   }

   public StructField[] fields() {
      return this.fields;
   }

   public String[] fieldNames() {
      return (String[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(this.fields()), (x$1) -> x$1.name(), scala.reflect.ClassTag..MODULE$.apply(String.class));
   }

   public String[] names() {
      return this.fieldNames();
   }

   private Set fieldNamesSet$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.fieldNamesSet = scala.Predef..MODULE$.wrapRefArray((Object[])this.fieldNames()).toSet();
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.fieldNamesSet;
   }

   private Set fieldNamesSet() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.fieldNamesSet$lzycompute() : this.fieldNamesSet;
   }

   private Map nameToField$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.nameToField = scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(this.fields()), (f) -> scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(f.name()), f), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))).toMap(scala..less.colon.less..MODULE$.refl());
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.nameToField;
   }

   private Map nameToField() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.nameToField$lzycompute() : this.nameToField;
   }

   private Map nameToIndex$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 4) == 0) {
            this.nameToIndex = org.apache.spark.util.SparkCollectionUtils..MODULE$.toMapWithIndex(scala.Predef..MODULE$.wrapRefArray((Object[])this.fieldNames()));
            this.bitmap$0 = (byte)(this.bitmap$0 | 4);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.nameToIndex;
   }

   private Map nameToIndex() {
      return (byte)(this.bitmap$0 & 4) == 0 ? this.nameToIndex$lzycompute() : this.nameToIndex;
   }

   private CaseInsensitiveMap nameToIndexCaseInsensitive$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 8) == 0) {
            this.nameToIndexCaseInsensitive = CaseInsensitiveMap$.MODULE$.apply(this.nameToIndex().toMap(scala..less.colon.less..MODULE$.refl()));
            this.bitmap$0 = (byte)(this.bitmap$0 | 8);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.nameToIndexCaseInsensitive;
   }

   private CaseInsensitiveMap nameToIndexCaseInsensitive() {
      return (byte)(this.bitmap$0 & 8) == 0 ? this.nameToIndexCaseInsensitive$lzycompute() : this.nameToIndexCaseInsensitive;
   }

   public boolean equals(final Object that) {
      if (that instanceof StructType var4) {
         StructField[] otherFields = var4.fields();
         return Arrays.equals(this.fields(), otherFields);
      } else {
         return false;
      }
   }

   public String toString() {
      String var10000 = this.getClass().getSimpleName();
      return var10000 + scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(this.fields()), (x$2) -> x$2.toString(), scala.reflect.ClassTag..MODULE$.apply(String.class))).mkString("(", ",", ")");
   }

   private int _hashCode$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 16) == 0) {
            this._hashCode = Arrays.hashCode(this.fields());
            this.bitmap$0 = (byte)(this.bitmap$0 | 16);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this._hashCode;
   }

   private int _hashCode() {
      return (byte)(this.bitmap$0 & 16) == 0 ? this._hashCode$lzycompute() : this._hashCode;
   }

   public int hashCode() {
      return this._hashCode();
   }

   public StructType add(final StructField field) {
      return new StructType((StructField[])scala.collection.ArrayOps..MODULE$.$colon$plus$extension(scala.Predef..MODULE$.refArrayOps(this.fields()), field, scala.reflect.ClassTag..MODULE$.apply(StructField.class)));
   }

   public StructType add(final String name, final DataType dataType) {
      return new StructType((StructField[])scala.collection.ArrayOps..MODULE$.$colon$plus$extension(scala.Predef..MODULE$.refArrayOps(this.fields()), new StructField(name, dataType, true, Metadata$.MODULE$.empty()), scala.reflect.ClassTag..MODULE$.apply(StructField.class)));
   }

   public StructType add(final String name, final DataType dataType, final boolean nullable) {
      return new StructType((StructField[])scala.collection.ArrayOps..MODULE$.$colon$plus$extension(scala.Predef..MODULE$.refArrayOps(this.fields()), new StructField(name, dataType, nullable, Metadata$.MODULE$.empty()), scala.reflect.ClassTag..MODULE$.apply(StructField.class)));
   }

   public StructType add(final String name, final DataType dataType, final boolean nullable, final Metadata metadata) {
      return new StructType((StructField[])scala.collection.ArrayOps..MODULE$.$colon$plus$extension(scala.Predef..MODULE$.refArrayOps(this.fields()), new StructField(name, dataType, nullable, metadata), scala.reflect.ClassTag..MODULE$.apply(StructField.class)));
   }

   public StructType add(final String name, final DataType dataType, final boolean nullable, final String comment) {
      return new StructType((StructField[])scala.collection.ArrayOps..MODULE$.$colon$plus$extension(scala.Predef..MODULE$.refArrayOps(this.fields()), (new StructField(name, dataType, nullable, StructField$.MODULE$.apply$default$4())).withComment(comment), scala.reflect.ClassTag..MODULE$.apply(StructField.class)));
   }

   public StructType add(final String name, final String dataType) {
      return this.add(name, DataTypeParser$.MODULE$.parseDataType(dataType), true, Metadata$.MODULE$.empty());
   }

   public StructType add(final String name, final String dataType, final boolean nullable) {
      return this.add(name, DataTypeParser$.MODULE$.parseDataType(dataType), nullable, Metadata$.MODULE$.empty());
   }

   public StructType add(final String name, final String dataType, final boolean nullable, final Metadata metadata) {
      return this.add(name, DataTypeParser$.MODULE$.parseDataType(dataType), nullable, metadata);
   }

   public StructType add(final String name, final String dataType, final boolean nullable, final String comment) {
      return this.add(name, DataTypeParser$.MODULE$.parseDataType(dataType), nullable, comment);
   }

   public StructField apply(final String name) {
      return (StructField)this.nameToField().getOrElse(name, () -> {
         throw new SparkIllegalArgumentException("FIELD_NOT_FOUND", (scala.collection.immutable.Map)scala.collection.immutable.Map..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("fieldName"), DataTypeErrors$.MODULE$.toSQLId(name)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("fields"), scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.fieldNames()), (parts) -> DataTypeErrors$.MODULE$.toSQLId(parts), scala.reflect.ClassTag..MODULE$.apply(String.class))).mkString(", "))}))));
      });
   }

   public StructType apply(final Set names) {
      Set nonExistFields = (Set)names.$minus$minus(this.fieldNamesSet());
      if (nonExistFields.nonEmpty()) {
         throw new SparkIllegalArgumentException("NONEXISTENT_FIELD_NAME_IN_LIST", (scala.collection.immutable.Map)scala.collection.immutable.Map..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("nonExistFields"), ((IterableOnceOps)nonExistFields.map((parts) -> DataTypeErrors$.MODULE$.toSQLId(parts))).mkString(", ")), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("fieldNames"), scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.fieldNames()), (parts) -> DataTypeErrors$.MODULE$.toSQLId(parts), scala.reflect.ClassTag..MODULE$.apply(String.class))).mkString(", "))}))));
      } else {
         return new StructType((StructField[])scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps(this.fields()), (f) -> BoxesRunTime.boxToBoolean($anonfun$apply$5(names, f))));
      }
   }

   public int fieldIndex(final String name) {
      return BoxesRunTime.unboxToInt(this.nameToIndex().getOrElse(name, () -> {
         throw new SparkIllegalArgumentException("FIELD_NOT_FOUND", (scala.collection.immutable.Map)scala.collection.immutable.Map..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("fieldName"), DataTypeErrors$.MODULE$.toSQLId(name)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("fields"), scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.fieldNames()), (parts) -> DataTypeErrors$.MODULE$.toSQLId(parts), scala.reflect.ClassTag..MODULE$.apply(String.class))).mkString(", "))}))));
      }));
   }

   public Option getFieldIndex(final String name) {
      return this.nameToIndex().get(name);
   }

   public Option getFieldIndexCaseInsensitive(final String name) {
      return this.nameToIndexCaseInsensitive().get(name);
   }

   public Option findNestedField(final Seq fieldNames, final boolean includeCollections, final Function2 resolver, final Origin context) {
      return this.findFieldInStruct$1(this, fieldNames, scala.collection.immutable.Nil..MODULE$, resolver, fieldNames, context, includeCollections);
   }

   public boolean findNestedField$default$2() {
      return false;
   }

   public Function2 findNestedField$default$3() {
      return (x$3, x$4) -> BoxesRunTime.boxToBoolean($anonfun$findNestedField$default$3$1(x$3, x$4));
   }

   public Origin findNestedField$default$4() {
      return new Origin(Origin$.MODULE$.apply$default$1(), Origin$.MODULE$.apply$default$2(), Origin$.MODULE$.apply$default$3(), Origin$.MODULE$.apply$default$4(), Origin$.MODULE$.apply$default$5(), Origin$.MODULE$.apply$default$6(), Origin$.MODULE$.apply$default$7(), Origin$.MODULE$.apply$default$8(), Origin$.MODULE$.apply$default$9());
   }

   public String treeString() {
      return this.treeString(Integer.MAX_VALUE);
   }

   public String treeString(final int maxDepth) {
      StringConcat stringConcat = new StringConcat(StringConcat$.MODULE$.$lessinit$greater$default$1());
      stringConcat.append("root\n");
      String prefix = " |";
      int depth = maxDepth > 0 ? maxDepth : Integer.MAX_VALUE;
      scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(this.fields()), (field) -> {
         $anonfun$treeString$1(prefix, stringConcat, depth, field);
         return BoxedUnit.UNIT;
      });
      return stringConcat.toString();
   }

   public void printTreeString() {
      scala.Predef..MODULE$.println(this.treeString());
   }

   public void buildFormattedString(final String prefix, final StringConcat stringConcat, final int maxDepth) {
      scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(this.fields()), (field) -> {
         $anonfun$buildFormattedString$1(prefix, stringConcat, maxDepth, field);
         return BoxedUnit.UNIT;
      });
   }

   public JObject jsonValue() {
      return org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("type"), this.typeName()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("fields"), this.map((x$5) -> x$5.jsonValue())), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, scala.Predef..MODULE$.$conforms()));
   }

   public StructField apply(final int fieldIndex) {
      return this.fields()[fieldIndex];
   }

   public int length() {
      return this.fields().length;
   }

   public Iterator iterator() {
      return scala.collection.ArrayOps..MODULE$.iterator$extension(scala.Predef..MODULE$.refArrayOps(this.fields()));
   }

   public int defaultSize() {
      return BoxesRunTime.unboxToInt(scala.Predef..MODULE$.wrapIntArray((int[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(this.fields()), (x$6) -> BoxesRunTime.boxToInteger($anonfun$defaultSize$1(x$6)), scala.reflect.ClassTag..MODULE$.Int())).sum(scala.math.Numeric.IntIsIntegral..MODULE$));
   }

   public String simpleString() {
      LazyList fieldTypes = ((LazyList)scala.Predef..MODULE$.wrapRefArray(this.fields()).to(scala.collection.IterableFactory..MODULE$.toFactory(scala.package..MODULE$.LazyList()))).map((field) -> {
         String var10000 = field.name();
         return var10000 + ":" + field.dataType().simpleString();
      });
      return SparkStringUtils$.MODULE$.truncatedString(fieldTypes, "struct<", ",", ">", SqlApiConf$.MODULE$.get().maxToStringFields(), SparkStringUtils$.MODULE$.truncatedString$default$6());
   }

   public String catalogString() {
      StringConcat stringConcat = new StringConcat(StringConcat$.MODULE$.$lessinit$greater$default$1());
      int len = this.fields().length;
      stringConcat.append("struct<");
      int i = 0;

      while(i < len) {
         String var10001 = this.fields()[i].name();
         stringConcat.append(var10001 + ":" + this.fields()[i].dataType().catalogString());
         ++i;
         if (i < len) {
            stringConcat.append(",");
         }
      }

      stringConcat.append(">");
      return stringConcat.toString();
   }

   public String sql() {
      ArraySeq.ofRef var10000 = scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(this.fields()), (x$7) -> x$7.sql(), scala.reflect.ClassTag..MODULE$.apply(String.class)));
      return "STRUCT<" + var10000.mkString(", ") + ">";
   }

   public String toDDL() {
      return scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(this.fields()), (x$8) -> x$8.toDDL(), scala.reflect.ClassTag..MODULE$.apply(String.class))).mkString(",");
   }

   public String simpleString(final int maxNumberFields) {
      StringBuilder builder = new StringBuilder();
      String[] fieldTypes = (String[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.take$extension(scala.Predef..MODULE$.refArrayOps(this.fields()), maxNumberFields)), (f) -> {
         String var10000 = f.name();
         return var10000 + ": " + f.dataType().simpleString(maxNumberFields);
      }, scala.reflect.ClassTag..MODULE$.apply(String.class));
      builder.append("struct<");
      builder.append(scala.Predef..MODULE$.wrapRefArray((Object[])fieldTypes).mkString(", "));
      if (this.fields().length > 2) {
         if (this.fields().length - fieldTypes.length == 1) {
            builder.append(" ... 1 more field");
         } else {
            int var10001 = this.fields().length;
            builder.append(" ... " + (var10001 - 2) + " more fields");
         }
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      return builder.append(">").toString();
   }

   public StructType merge(final StructType that, final boolean caseSensitive) {
      return (StructType)StructType$.MODULE$.merge(this, that, caseSensitive);
   }

   public boolean merge$default$2() {
      return true;
   }

   public StructType asNullable() {
      StructField[] newFields = (StructField[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(this.fields()), (x0$1) -> {
         if (x0$1 != null) {
            String name = x0$1.name();
            DataType dataType = x0$1.dataType();
            Metadata metadata = x0$1.metadata();
            return new StructField(name, dataType.asNullable(), true, metadata);
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(StructField.class));
      return new StructType(newFields);
   }

   public StructType toNullable() {
      return this.asNullable();
   }

   public boolean existsRecursively(final Function1 f) {
      return BoxesRunTime.unboxToBoolean(f.apply(this)) || scala.collection.ArrayOps..MODULE$.exists$extension(scala.Predef..MODULE$.refArrayOps(this.fields()), (field) -> BoxesRunTime.boxToBoolean($anonfun$existsRecursively$1(f, field)));
   }

   public DataType transformRecursively(final PartialFunction f) {
      if (f.isDefinedAt(this)) {
         return (DataType)f.apply(this);
      } else {
         StructField[] newFields = (StructField[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(this.fields()), (field) -> {
            DataType x$1 = field.dataType().transformRecursively(f);
            String x$2 = field.copy$default$1();
            boolean x$3 = field.copy$default$3();
            Metadata x$4 = field.copy$default$4();
            return field.copy(x$2, x$1, x$3, x$4);
         }, scala.reflect.ClassTag..MODULE$.apply(StructField.class));
         return new StructType(newFields);
      }
   }

   public StructType copy(final StructField[] fields) {
      return new StructType(fields);
   }

   public StructField[] copy$default$1() {
      return this.fields();
   }

   public String productPrefix() {
      return "StructType";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.fields();
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "fields";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$5(final Set names$1, final StructField f) {
      return names$1.contains(f.name());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$findNestedField$1(final Function2 resolver$1, final String searchName$1, final StructField f) {
      return BoxesRunTime.unboxToBoolean(resolver$1.apply(searchName$1, f.name()));
   }

   private final Option findFieldInStruct$1(final StructType struct, final Seq searchPath, final Seq normalizedPath, final Function2 resolver$1, final Seq fieldNames$1, final Origin context$1, final boolean includeCollections$1) {
      scala.Predef..MODULE$.assert(searchPath.nonEmpty());
      String searchName = (String)searchPath.head();
      StructField[] found = (StructField[])scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps(struct.fields()), (f) -> BoxesRunTime.boxToBoolean($anonfun$findNestedField$1(resolver$1, searchName, f)));
      if (found.length > 1) {
         throw DataTypeErrors$.MODULE$.ambiguousColumnOrFieldError(fieldNames$1, found.length, context$1);
      } else {
         return (Option)(scala.collection.ArrayOps..MODULE$.isEmpty$extension(scala.Predef..MODULE$.refArrayOps(found)) ? scala.None..MODULE$ : this.findField$1((StructField)scala.collection.ArrayOps..MODULE$.head$extension(scala.Predef..MODULE$.refArrayOps(found)), (Seq)searchPath.tail(), normalizedPath, includeCollections$1, fieldNames$1, context$1, resolver$1));
      }
   }

   private final Option findField$1(final StructField parent, final Seq searchPath, final Seq normalizedPath, final boolean includeCollections$1, final Seq fieldNames$1, final Origin context$1, final Function2 resolver$1) {
      while(!searchPath.isEmpty()) {
         Seq currentPath = (Seq)normalizedPath.$colon$plus(parent.name());
         Tuple2 var11 = new Tuple2(searchPath, parent.dataType());
         if (var11 != null) {
            DataType s = (DataType)var11._2();
            if (s instanceof StructType) {
               StructType var13 = (StructType)s;
               return this.findFieldInStruct$1(var13, searchPath, currentPath, resolver$1, fieldNames$1, context$1, includeCollections$1);
            }
         }

         if (!includeCollections$1) {
            throw DataTypeErrors$.MODULE$.invalidFieldName(fieldNames$1, currentPath, context$1);
         }

         if (var11 != null) {
            Seq var14 = (Seq)var11._1();
            DataType var15 = (DataType)var11._2();
            if (var14 != null) {
               SeqOps var16 = scala.package..MODULE$.Seq().unapplySeq(var14);
               if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var16) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var16)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var16), 1) >= 0) {
                  String var17 = (String)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var16), 0);
                  Seq rest = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.drop$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var16), 1);
                  if ("key".equals(var17) && var15 instanceof MapType) {
                     MapType var19 = (MapType)var15;
                     DataType keyType = var19.keyType();
                     StructField var38 = new StructField("key", keyType, false, StructField$.MODULE$.apply$default$4());
                     normalizedPath = currentPath;
                     searchPath = rest;
                     parent = var38;
                     continue;
                  }
               }
            }
         }

         if (var11 != null) {
            Seq var21 = (Seq)var11._1();
            DataType var22 = (DataType)var11._2();
            if (var21 != null) {
               SeqOps var23 = scala.package..MODULE$.Seq().unapplySeq(var21);
               if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var23) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var23)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var23), 1) >= 0) {
                  String var24 = (String)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var23), 0);
                  Seq rest = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.drop$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var23), 1);
                  if ("value".equals(var24) && var22 instanceof MapType) {
                     MapType var26 = (MapType)var22;
                     DataType valueType = var26.valueType();
                     boolean isNullable = var26.valueContainsNull();
                     StructField var37 = new StructField("value", valueType, isNullable, StructField$.MODULE$.apply$default$4());
                     normalizedPath = currentPath;
                     searchPath = rest;
                     parent = var37;
                     continue;
                  }
               }
            }
         }

         if (var11 != null) {
            Seq var29 = (Seq)var11._1();
            DataType var30 = (DataType)var11._2();
            if (var29 != null) {
               SeqOps var31 = scala.package..MODULE$.Seq().unapplySeq(var29);
               if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var31) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var31)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var31), 1) >= 0) {
                  String var32 = (String)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var31), 0);
                  Seq rest = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.drop$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var31), 1);
                  if ("element".equals(var32) && var30 instanceof ArrayType) {
                     ArrayType var34 = (ArrayType)var30;
                     DataType elementType = var34.elementType();
                     boolean isNullable = var34.containsNull();
                     StructField var10000 = new StructField("element", elementType, isNullable, StructField$.MODULE$.apply$default$4());
                     normalizedPath = currentPath;
                     searchPath = rest;
                     parent = var10000;
                     continue;
                  }
               }
            }
         }

         throw DataTypeErrors$.MODULE$.invalidFieldName(fieldNames$1, currentPath, context$1);
      }

      return new Some(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(normalizedPath), parent));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$findNestedField$default$3$1(final String x$3, final String x$4) {
      boolean var10000;
      label23: {
         if (x$3 == null) {
            if (x$4 == null) {
               break label23;
            }
         } else if (x$3.equals(x$4)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   // $FF: synthetic method
   public static final void $anonfun$treeString$1(final String prefix$1, final StringConcat stringConcat$1, final int depth$1, final StructField field) {
      field.buildFormattedString(prefix$1, stringConcat$1, depth$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$buildFormattedString$1(final String prefix$2, final StringConcat stringConcat$2, final int maxDepth$1, final StructField field) {
      field.buildFormattedString(prefix$2, stringConcat$2, maxDepth$1);
   }

   // $FF: synthetic method
   public static final int $anonfun$defaultSize$1(final StructField x$6) {
      return x$6.dataType().defaultSize();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$existsRecursively$1(final Function1 f$1, final StructField field) {
      return field.dataType().existsRecursively(f$1);
   }

   public StructType(final StructField[] fields) {
      this.fields = fields;
      IterableOnce.$init$(this);
      IterableOnceOps.$init$(this);
      IterableOps.$init$(this);
      IterableFactoryDefaults.$init$(this);
      Iterable.$init$(this);
      scala.collection.immutable.Iterable.$init$(this);
      Function1.$init$(this);
      PartialFunction.$init$(this);
      SeqOps.$init$(this);
      scala.collection.Seq.$init$(this);
      Seq.$init$(this);
      Product.$init$(this);
   }

   public StructType() {
      this((StructField[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(StructField.class)));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
