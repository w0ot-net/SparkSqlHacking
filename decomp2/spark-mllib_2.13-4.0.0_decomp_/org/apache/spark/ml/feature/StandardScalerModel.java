package org.apache.spark.ml.feature;

import java.io.IOException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasInputCol;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.DefaultParamsReader$;
import org.apache.spark.ml.util.DefaultParamsWriter$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.mllib.util.MLUtils$;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.types.StructType;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Array.;
import scala.collection.Iterator;
import scala.collection.SeqOps;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\r-c\u0001\u0002\u001f>\u0001!C\u0001\u0002\u0017\u0001\u0003\u0006\u0004%\t%\u0017\u0005\ta\u0002\u0011\t\u0011)A\u00055\"A!\u000f\u0001BC\u0002\u0013\u00051\u000f\u0003\u0005~\u0001\t\u0005\t\u0015!\u0003u\u0011!y\bA!b\u0001\n\u0003\u0019\b\"CA\u0002\u0001\t\u0005\t\u0015!\u0003u\u0011!\t9\u0001\u0001C\u0001\u007f\u0005%\u0001\u0002CA\u0004\u0001\u0011\u0005q(a\u0006\t\u000f\u0005e\u0001\u0001\"\u0001\u0002\u001c!9\u0011\u0011\u0006\u0001\u0005\u0002\u0005-\u0002bBA\u0019\u0001\u0011\u0005\u00131\u0007\u0005\b\u0003\u007f\u0002A\u0011IAA\u0011\u001d\t)\n\u0001C!\u0003/Cq!a,\u0001\t\u0003\n\t\fC\u0004\u0002@\u0002!\t%!1\b\u000f\u0005-W\b#\u0001\u0002N\u001a1A(\u0010E\u0001\u0003\u001fDq!a\u0002\u0012\t\u0003\tiOB\u0004\u0002pF\u0001\u0011#!=\t\u0013\u0005M8C!A!\u0002\u0013i\u0005bBA\u0004'\u0011\u0005\u0011Q\u001f\u0004\u0007\u0003{\u001cB)a@\t\u0011I4\"Q3A\u0005\u0002MD\u0001\" \f\u0003\u0012\u0003\u0006I\u0001\u001e\u0005\t\u007fZ\u0011)\u001a!C\u0001g\"I\u00111\u0001\f\u0003\u0012\u0003\u0006I\u0001\u001e\u0005\b\u0003\u000f1B\u0011\u0001B\u000b\u0011%\t)JFA\u0001\n\u0003\u0011y\u0002C\u0005\u0003&Y\t\n\u0011\"\u0001\u0003(!I!1\b\f\u0012\u0002\u0013\u0005!q\u0005\u0005\n\u0005{1\u0012\u0011!C!\u0005\u007fA\u0011Ba\u0013\u0017\u0003\u0003%\tA!\u0014\t\u0013\tUc#!A\u0005\u0002\t]\u0003\"\u0003B/-\u0005\u0005I\u0011\tB0\u0011%\u0011iGFA\u0001\n\u0003\u0011y\u0007C\u0005\u0003zY\t\t\u0011\"\u0011\u0003|!I!q\u0010\f\u0002\u0002\u0013\u0005#\u0011\u0011\u0005\n\u0003\u007f3\u0012\u0011!C!\u0005\u0007C\u0011B!\"\u0017\u0003\u0003%\tEa\"\b\u0013\t-5#!A\t\n\t5e!CA\u007f'\u0005\u0005\t\u0012\u0002BH\u0011\u001d\t9!\u000bC\u0001\u0005;C\u0011\"a0*\u0003\u0003%)Ea!\t\u0013\t}\u0015&!A\u0005\u0002\n\u0005\u0006\"\u0003BTS\u0005\u0005I\u0011\u0011BU\u0011\u001d\u0011Yl\u0005C)\u0005{3aA!3\u0012\t\t-\u0007bBA\u0004_\u0011\u0005!1\u001b\u0005\n\u0005/|#\u0019!C\u0005\u0005\u007fA\u0001B!70A\u0003%!\u0011\t\u0005\b\u00057|C\u0011\tBo\u0011\u001d\u0011\t/\u0005C!\u0005GDqAa7\u0012\t\u0003\u00129\u000f\u0003\u0005\u0003nF!\t!\u0011Bx\u0011!\u0019I!\u0005C\u0001\u0003\u000e-\u0001\u0002CB\t#\u0011\u0005\u0011ia\u0005\t\u0011\re\u0011\u0003\"\u0001B\u00077A\u0001ba\n\u0012\t\u0003\t5\u0011\u0006\u0005\n\u0007{\t\u0012\u0011!C\u0005\u0007\u007f\u00111c\u0015;b]\u0012\f'\u000fZ*dC2,'/T8eK2T!AP \u0002\u000f\u0019,\u0017\r^;sK*\u0011\u0001)Q\u0001\u0003[2T!AQ\"\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u0011+\u0015AB1qC\u000eDWMC\u0001G\u0003\ry'oZ\u0002\u0001'\u0011\u0001\u0011j\u0014*\u0011\u0007)[U*D\u0001@\u0013\tauHA\u0003N_\u0012,G\u000e\u0005\u0002O\u00015\tQ\b\u0005\u0002O!&\u0011\u0011+\u0010\u0002\u0015'R\fg\u000eZ1sIN\u001b\u0017\r\\3s!\u0006\u0014\u0018-\\:\u0011\u0005M3V\"\u0001+\u000b\u0005U{\u0014\u0001B;uS2L!a\u0016+\u0003\u00155cuK]5uC\ndW-A\u0002vS\u0012,\u0012A\u0017\t\u00037\u0012t!\u0001\u00182\u0011\u0005u\u0003W\"\u00010\u000b\u0005};\u0015A\u0002\u001fs_>$hHC\u0001b\u0003\u0015\u00198-\u00197b\u0013\t\u0019\u0007-\u0001\u0004Qe\u0016$WMZ\u0005\u0003K\u001a\u0014aa\u0015;sS:<'BA2aQ\r\t\u0001N\u001c\t\u0003S2l\u0011A\u001b\u0006\u0003W\u0006\u000b!\"\u00198o_R\fG/[8o\u0013\ti'NA\u0003TS:\u001cW-I\u0001p\u0003\u0015\td\u0006\u000e\u00181\u0003\u0011)\u0018\u000e\u001a\u0011)\u0007\tAg.A\u0002ti\u0012,\u0012\u0001\u001e\t\u0003kbl\u0011A\u001e\u0006\u0003o~\na\u0001\\5oC2<\u0017BA=w\u0005\u00191Vm\u0019;pe\"\u001a1\u0001[>\"\u0003q\fQA\r\u00181]A\nAa\u001d;eA!\u001aA\u0001[>\u0002\t5,\u0017M\u001c\u0015\u0004\u000b!\\\u0018!B7fC:\u0004\u0003f\u0001\u0004iw\u00061A(\u001b8jiz\"r!TA\u0006\u0003\u001f\t\u0019\u0002C\u0003Y\u000f\u0001\u0007!\f\u000b\u0003\u0002\f!t\u0007\"\u0002:\b\u0001\u0004!\b\u0006BA\bQnDQa`\u0004A\u0002QDC!a\u0005iwR\tQ*A\u0006tKRLe\u000e];u\u0007>dG\u0003BA\u000f\u0003?i\u0011\u0001\u0001\u0005\u0007\u0003CI\u0001\u0019\u0001.\u0002\u000bY\fG.^3)\t%A\u0017QE\u0011\u0003\u0003O\tQ!\r\u00183]A\nAb]3u\u001fV$\b/\u001e;D_2$B!!\b\u0002.!1\u0011\u0011\u0005\u0006A\u0002iCCA\u00035\u0002&\u0005IAO]1og\u001a|'/\u001c\u000b\u0005\u0003k\t9\u0006\u0005\u0003\u00028\u0005Ec\u0002BA\u001d\u0003\u0017rA!a\u000f\u0002H9!\u0011QHA#\u001d\u0011\ty$a\u0011\u000f\u0007u\u000b\t%C\u0001G\u0013\t!U)\u0003\u0002C\u0007&\u0019\u0011\u0011J!\u0002\u0007M\fH.\u0003\u0003\u0002N\u0005=\u0013a\u00029bG.\fw-\u001a\u0006\u0004\u0003\u0013\n\u0015\u0002BA*\u0003+\u0012\u0011\u0002R1uC\u001a\u0013\u0018-\\3\u000b\t\u00055\u0013q\n\u0005\b\u00033Z\u0001\u0019AA.\u0003\u001d!\u0017\r^1tKR\u0004D!!\u0018\u0002jA1\u0011qLA1\u0003Kj!!a\u0014\n\t\u0005\r\u0014q\n\u0002\b\t\u0006$\u0018m]3u!\u0011\t9'!\u001b\r\u0001\u0011a\u00111NA,\u0003\u0003\u0005\tQ!\u0001\u0002n\t\u0019q\f\n\u001a\u0012\t\u0005=\u0014q\u000f\t\u0005\u0003c\n\u0019(D\u0001a\u0013\r\t)\b\u0019\u0002\b\u001d>$\b.\u001b8h!\u0011\t\t(!\u001f\n\u0007\u0005m\u0004MA\u0002B]fD3a\u00035|\u0003=!(/\u00198tM>\u0014XnU2iK6\fG\u0003BAB\u0003\u001f\u0003B!!\"\u0002\f6\u0011\u0011q\u0011\u0006\u0005\u0003\u0013\u000by%A\u0003usB,7/\u0003\u0003\u0002\u000e\u0006\u001d%AC*ueV\u001cG\u000fV=qK\"9\u0011\u0011\u0013\u0007A\u0002\u0005\r\u0015AB:dQ\u0016l\u0017\rK\u0002\rQ:\fAaY8qsR\u0019Q*!'\t\u000f\u0005mU\u00021\u0001\u0002\u001e\u0006)Q\r\u001f;sCB!\u0011qTAS\u001b\t\t\tKC\u0002\u0002$~\nQ\u0001]1sC6LA!a*\u0002\"\nA\u0001+\u0019:b[6\u000b\u0007\u000f\u000b\u0003\u000eQ\u0006-\u0016EAAW\u0003\u0015\td\u0006\u000e\u00182\u0003\u00159(/\u001b;f+\t\t\u0019\fE\u0002T\u0003kK1!a.U\u0005!iEj\u0016:ji\u0016\u0014\b\u0006\u0002\bi\u0003w\u000b#!!0\u0002\u000bErcG\f\u0019\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012A\u0017\u0015\u0005\u001f!\f)-\t\u0002\u0002H\u0006)1G\f\u0019/a!\"\u0001\u0001[A\u0013\u0003M\u0019F/\u00198eCJ$7kY1mKJlu\u000eZ3m!\tq\u0015cE\u0004\u0012\u0003#\f9.!8\u0011\t\u0005E\u00141[\u0005\u0004\u0003+\u0004'AB!osJ+g\r\u0005\u0003T\u00033l\u0015bAAn)\nQQ\n\u0014*fC\u0012\f'\r\\3\u0011\t\u0005}\u0017\u0011^\u0007\u0003\u0003CTA!a9\u0002f\u0006\u0011\u0011n\u001c\u0006\u0003\u0003O\fAA[1wC&!\u00111^Aq\u00051\u0019VM]5bY&T\u0018M\u00197f)\t\tiMA\rTi\u0006tG-\u0019:e'\u000e\fG.\u001a:N_\u0012,Gn\u0016:ji\u0016\u00148cA\n\u00024\u0006A\u0011N\\:uC:\u001cW\r\u0006\u0003\u0002x\u0006m\bcAA}'5\t\u0011\u0003\u0003\u0004\u0002tV\u0001\r!\u0014\u0002\u0005\t\u0006$\u0018mE\u0004\u0017\u0003#\u0014\tAa\u0002\u0011\t\u0005E$1A\u0005\u0004\u0005\u000b\u0001'a\u0002)s_\u0012,8\r\u001e\t\u0005\u0005\u0013\u0011\tB\u0004\u0003\u0003\f\t=abA/\u0003\u000e%\t\u0011-C\u0002\u0002N\u0001LA!a;\u0003\u0014)\u0019\u0011Q\n1\u0015\r\t]!1\u0004B\u000f!\r\u0011IBF\u0007\u0002'!)!o\u0007a\u0001i\")qp\u0007a\u0001iR1!q\u0003B\u0011\u0005GAqA\u001d\u000f\u0011\u0002\u0003\u0007A\u000fC\u0004\u00009A\u0005\t\u0019\u0001;\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011!\u0011\u0006\u0016\u0004i\n-2F\u0001B\u0017!\u0011\u0011yCa\u000e\u000e\u0005\tE\"\u0002\u0002B\u001a\u0005k\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005-\u0004\u0017\u0002\u0002B\u001d\u0005c\u0011\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXC\u0001B!!\u0011\u0011\u0019E!\u0013\u000e\u0005\t\u0015#\u0002\u0002B$\u0003K\fA\u0001\\1oO&\u0019QM!\u0012\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0005\t=\u0003\u0003BA9\u0005#J1Aa\u0015a\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\u0011\t9H!\u0017\t\u0013\tm\u0013%!AA\u0002\t=\u0013a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0003bA1!1\rB5\u0003oj!A!\u001a\u000b\u0007\t\u001d\u0004-\u0001\u0006d_2dWm\u0019;j_:LAAa\u001b\u0003f\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\u0011\tHa\u001e\u0011\t\u0005E$1O\u0005\u0004\u0005k\u0002'a\u0002\"p_2,\u0017M\u001c\u0005\n\u00057\u001a\u0013\u0011!a\u0001\u0003o\n!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!!\u0011\tB?\u0011%\u0011Y\u0006JA\u0001\u0002\u0004\u0011y%\u0001\u0005iCND7i\u001c3f)\t\u0011y\u0005\u0006\u0002\u0003B\u00051Q-];bYN$BA!\u001d\u0003\n\"I!1L\u0014\u0002\u0002\u0003\u0007\u0011qO\u0001\u0005\t\u0006$\u0018\rE\u0002\u0003\u001a%\u001aR!\u000bBI\u0003;\u0004\u0002Ba%\u0003\u001aR$(qC\u0007\u0003\u0005+S1Aa&a\u0003\u001d\u0011XO\u001c;j[\u0016LAAa'\u0003\u0016\n\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\u001c\u001a\u0015\u0005\t5\u0015!B1qa2LHC\u0002B\f\u0005G\u0013)\u000bC\u0003sY\u0001\u0007A\u000fC\u0003\u0000Y\u0001\u0007A/A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\t-&q\u0017\t\u0007\u0003c\u0012iK!-\n\u0007\t=\u0006M\u0001\u0004PaRLwN\u001c\t\u0007\u0003c\u0012\u0019\f\u001e;\n\u0007\tU\u0006M\u0001\u0004UkBdWM\r\u0005\n\u0005sk\u0013\u0011!a\u0001\u0005/\t1\u0001\u001f\u00131\u0003!\u0019\u0018M^3J[BdG\u0003\u0002B`\u0005\u000b\u0004B!!\u001d\u0003B&\u0019!1\u00191\u0003\tUs\u0017\u000e\u001e\u0005\u0007\u0005\u000ft\u0003\u0019\u0001.\u0002\tA\fG\u000f\u001b\u0002\u001a'R\fg\u000eZ1sIN\u001b\u0017\r\\3s\u001b>$W\r\u001c*fC\u0012,'oE\u00020\u0005\u001b\u0004Ba\u0015Bh\u001b&\u0019!\u0011\u001b+\u0003\u00115c%+Z1eKJ$\"A!6\u0011\u0007\u0005ex&A\u0005dY\u0006\u001c8OT1nK\u0006Q1\r\\1tg:\u000bW.\u001a\u0011\u0002\t1|\u0017\r\u001a\u000b\u0004\u001b\n}\u0007B\u0002Bdg\u0001\u0007!,\u0001\u0003sK\u0006$WC\u0001BgQ\u0011!\u0004.a/\u0015\u00075\u0013I\u000f\u0003\u0004\u0003HV\u0002\rA\u0017\u0015\u0005k!\fY,A\tue\u0006t7OZ8s[^KG\u000f\u001b\"pi\"$\u0002B!=\u0003~\u000e\u00051Q\u0001\t\u0007\u0003c\u0012\u0019Pa>\n\u0007\tU\bMA\u0003BeJ\f\u0017\u0010\u0005\u0003\u0002r\te\u0018b\u0001B~A\n1Ai\\;cY\u0016DqAa@7\u0001\u0004\u0011\t0A\u0003tQ&4G\u000fC\u0004\u0004\u0004Y\u0002\rA!=\u0002\u000bM\u001c\u0017\r\\3\t\u000f\r\u001da\u00071\u0001\u0003r\u00061a/\u00197vKN\f!\u0003\u001e:b]N4wN]7XSRD7\u000b[5giR1!\u0011_B\u0007\u0007\u001fAqAa@8\u0001\u0004\u0011\t\u0010C\u0004\u0004\b]\u0002\rA!=\u0002/Q\u0014\u0018M\\:g_JlG)\u001a8tK^KG\u000f[*dC2,GC\u0002By\u0007+\u00199\u0002C\u0004\u0004\u0004a\u0002\rA!=\t\u000f\r\u001d\u0001\b1\u0001\u0003r\u0006ABO]1og\u001a|'/\\*qCJ\u001cXmV5uQN\u001b\u0017\r\\3\u0015\u0011\tE8QDB\u0010\u0007KAqaa\u0001:\u0001\u0004\u0011\t\u0010C\u0004\u0004\"e\u0002\raa\t\u0002\u000f%tG-[2fgB1\u0011\u0011\u000fBz\u0005\u001fBqaa\u0002:\u0001\u0004\u0011\t0\u0001\thKR$&/\u00198tM>\u0014XNR;oGRQ11FB\u0019\u0007g\u0019)d!\u000f\u0011\r\u0005E4Q\u0006;u\u0013\r\u0019y\u0003\u0019\u0002\n\rVt7\r^5p]FBqAa@;\u0001\u0004\u0011\t\u0010C\u0004\u0004\u0004i\u0002\rA!=\t\u000f\r]\"\b1\u0001\u0003r\u0005Iq/\u001b;i'\"Lg\r\u001e\u0005\b\u0007wQ\u0004\u0019\u0001B9\u0003%9\u0018\u000e\u001e5TG\u0006dW-\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0004BA!!1IB\"\u0013\u0011\u0019)E!\u0012\u0003\r=\u0013'.Z2uQ\u0011\t\u0002.a/)\tAA\u00171\u0018"
)
public class StandardScalerModel extends Model implements StandardScalerParams, MLWritable {
   private final String uid;
   private final Vector std;
   private final Vector mean;
   private BooleanParam withMean;
   private BooleanParam withStd;
   private Param outputCol;
   private Param inputCol;

   public static StandardScalerModel load(final String path) {
      return StandardScalerModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return StandardScalerModel$.MODULE$.read();
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public boolean getWithMean() {
      return StandardScalerParams.getWithMean$(this);
   }

   public boolean getWithStd() {
      return StandardScalerParams.getWithStd$(this);
   }

   public StructType validateAndTransformSchema(final StructType schema) {
      return StandardScalerParams.validateAndTransformSchema$(this, schema);
   }

   public final String getOutputCol() {
      return HasOutputCol.getOutputCol$(this);
   }

   public final String getInputCol() {
      return HasInputCol.getInputCol$(this);
   }

   public BooleanParam withMean() {
      return this.withMean;
   }

   public BooleanParam withStd() {
      return this.withStd;
   }

   public void org$apache$spark$ml$feature$StandardScalerParams$_setter_$withMean_$eq(final BooleanParam x$1) {
      this.withMean = x$1;
   }

   public void org$apache$spark$ml$feature$StandardScalerParams$_setter_$withStd_$eq(final BooleanParam x$1) {
      this.withStd = x$1;
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

   public Vector std() {
      return this.std;
   }

   public Vector mean() {
      return this.mean;
   }

   public StandardScalerModel setInputCol(final String value) {
      return (StandardScalerModel)this.set(this.inputCol(), value);
   }

   public StandardScalerModel setOutputCol(final String value) {
      return (StandardScalerModel)this.set(this.outputCol(), value);
   }

   public Dataset transform(final Dataset dataset) {
      StructType outputSchema = this.transformSchema(dataset.schema(), true);
      double[] shift = BoxesRunTime.unboxToBoolean(this.$(this.withMean())) ? this.mean().toArray() : .MODULE$.emptyDoubleArray();
      double[] scale = BoxesRunTime.unboxToBoolean(this.$(this.withStd())) ? (double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps(this.std().toArray()), (JFunction1.mcDD.sp)(v) -> v == (double)0 ? (double)0.0F : (double)1.0F / v, scala.reflect.ClassTag..MODULE$.Double()) : .MODULE$.emptyDoubleArray();
      Function1 func = StandardScalerModel$.MODULE$.getTransformFunc(shift, scale, BoxesRunTime.unboxToBoolean(this.$(this.withMean())), BoxesRunTime.unboxToBoolean(this.$(this.withStd())));
      functions var10000 = org.apache.spark.sql.functions..MODULE$;
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(StandardScalerModel.class.getClassLoader());

      final class $typecreator1$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
         }

         public $typecreator1$1() {
         }
      }

      TypeTags.TypeTag var10002 = ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1());
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(StandardScalerModel.class.getClassLoader());

      final class $typecreator2$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
         }

         public $typecreator2$1() {
         }
      }

      UserDefinedFunction transformer = var10000.udf(func, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1()));
      return dataset.withColumn((String)this.$(this.outputCol()), transformer.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.inputCol()))}))), outputSchema.apply((String)this.$(this.outputCol())).metadata());
   }

   public StructType transformSchema(final StructType schema) {
      StructType outputSchema = this.validateAndTransformSchema(schema);
      if (scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.outputCol())))) {
         outputSchema = SchemaUtils$.MODULE$.updateAttributeGroupSize(outputSchema, (String)this.$(this.outputCol()), this.mean().size());
      }

      return outputSchema;
   }

   public StandardScalerModel copy(final ParamMap extra) {
      StandardScalerModel copied = new StandardScalerModel(this.uid(), this.std(), this.mean());
      return (StandardScalerModel)((Model)this.copyValues(copied, extra)).setParent(this.parent());
   }

   public MLWriter write() {
      return new StandardScalerModelWriter(this);
   }

   public String toString() {
      String var10000 = this.uid();
      return "StandardScalerModel: uid=" + var10000 + ", numFeatures=" + this.mean().size() + ", withMean=" + this.$(this.withMean()) + ", withStd=" + this.$(this.withStd());
   }

   public StandardScalerModel(final String uid, final Vector std, final Vector mean) {
      this.uid = uid;
      this.std = std;
      this.mean = mean;
      HasInputCol.$init$(this);
      HasOutputCol.$init$(this);
      StandardScalerParams.$init$(this);
      MLWritable.$init$(this);
      Statics.releaseFence();
   }

   public StandardScalerModel() {
      this("", org.apache.spark.ml.linalg.Vectors..MODULE$.empty(), org.apache.spark.ml.linalg.Vectors..MODULE$.empty());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class StandardScalerModelWriter extends MLWriter {
      private volatile Data$ Data$module;
      private final StandardScalerModel instance;

      private Data$ Data() {
         if (this.Data$module == null) {
            this.Data$lzycompute$1();
         }

         return this.Data$module;
      }

      public void saveImpl(final String path) {
         DefaultParamsWriter$.MODULE$.saveMetadata(this.instance, path, this.sparkSession());
         Data data = new Data(this.instance.std(), this.instance.mean());
         String dataPath = (new Path(path, "data")).toString();
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(data, scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(StandardScalerModelWriter.class.getClassLoader());

         final class $typecreator1$2 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticClass("org.apache.spark.ml.feature.StandardScalerModel.StandardScalerModelWriter")), $u.internal().reificationSupport().selectType($m$untyped.staticClass("org.apache.spark.ml.feature.StandardScalerModel.StandardScalerModelWriter"), "Data"), scala.collection.immutable.Nil..MODULE$);
            }

            public $typecreator1$2() {
            }
         }

         var10000.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$2())).write().parquet(dataPath);
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

      public StandardScalerModelWriter(final StandardScalerModel instance) {
         this.instance = instance;
      }

      private class Data implements Product, Serializable {
         private final Vector std;
         private final Vector mean;
         // $FF: synthetic field
         public final StandardScalerModelWriter $outer;

         public Iterator productElementNames() {
            return Product.productElementNames$(this);
         }

         public Vector std() {
            return this.std;
         }

         public Vector mean() {
            return this.mean;
         }

         public Data copy(final Vector std, final Vector mean) {
            return this.org$apache$spark$ml$feature$StandardScalerModel$StandardScalerModelWriter$Data$$$outer().new Data(std, mean);
         }

         public Vector copy$default$1() {
            return this.std();
         }

         public Vector copy$default$2() {
            return this.mean();
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
                  return this.std();
               }
               case 1 -> {
                  return this.mean();
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
                  return "std";
               }
               case 1 -> {
                  return "mean";
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
                  if (x$1 instanceof Data && ((Data)x$1).org$apache$spark$ml$feature$StandardScalerModel$StandardScalerModelWriter$Data$$$outer() == this.org$apache$spark$ml$feature$StandardScalerModel$StandardScalerModelWriter$Data$$$outer()) {
                     label50: {
                        Data var4 = (Data)x$1;
                        Vector var10000 = this.std();
                        Vector var5 = var4.std();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label50;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label50;
                        }

                        var10000 = this.mean();
                        Vector var6 = var4.mean();
                        if (var10000 == null) {
                           if (var6 != null) {
                              break label50;
                           }
                        } else if (!var10000.equals(var6)) {
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
         public StandardScalerModelWriter org$apache$spark$ml$feature$StandardScalerModel$StandardScalerModelWriter$Data$$$outer() {
            return this.$outer;
         }

         public Data(final Vector std, final Vector mean) {
            this.std = std;
            this.mean = mean;
            if (StandardScalerModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = StandardScalerModelWriter.this;
               super();
               Product.$init$(this);
            }
         }
      }

      private class Data$ extends AbstractFunction2 implements Serializable {
         // $FF: synthetic field
         private final StandardScalerModelWriter $outer;

         public final String toString() {
            return "Data";
         }

         public Data apply(final Vector std, final Vector mean) {
            return this.$outer.new Data(std, mean);
         }

         public Option unapply(final Data x$0) {
            return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.std(), x$0.mean())));
         }

         public Data$() {
            if (StandardScalerModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = StandardScalerModelWriter.this;
               super();
            }
         }
      }
   }

   private static class StandardScalerModelReader extends MLReader {
      private final String className = StandardScalerModel.class.getName();

      private String className() {
         return this.className;
      }

      public StandardScalerModel load(final String path) {
         DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, this.sparkSession(), this.className());
         String dataPath = (new Path(path, "data")).toString();
         Dataset data = this.sparkSession().read().parquet(dataPath);
         Row var7 = (Row)MLUtils$.MODULE$.convertVectorColumnsToML(data, (Seq)scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"std", "mean"}))).select("std", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"mean"}))).head();
         if (var7 != null) {
            Some var8 = org.apache.spark.sql.Row..MODULE$.unapplySeq(var7);
            if (!var8.isEmpty() && var8.get() != null && ((SeqOps)var8.get()).lengthCompare(2) == 0) {
               Object std = ((SeqOps)var8.get()).apply(0);
               Object mean = ((SeqOps)var8.get()).apply(1);
               if (std instanceof Vector) {
                  Vector var11 = (Vector)std;
                  if (mean instanceof Vector) {
                     Vector var12 = (Vector)mean;
                     Tuple2 var6 = new Tuple2(var11, var12);
                     Vector std = (Vector)var6._1();
                     Vector mean = (Vector)var6._2();
                     StandardScalerModel model = new StandardScalerModel(metadata.uid(), std, mean);
                     metadata.getAndSetParams(model, metadata.getAndSetParams$default$2());
                     return model;
                  }
               }
            }
         }

         throw new MatchError(var7);
      }

      public StandardScalerModelReader() {
      }
   }
}
