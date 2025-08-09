package org.apache.spark.ml.optim;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.internal.MDC;
import org.apache.spark.ml.feature.Instance;
import org.apache.spark.ml.linalg.DenseVector;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.util.OptionalInstrumentation;
import org.apache.spark.ml.util.OptionalInstrumentation$;
import org.apache.spark.rdd.RDD;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\r\rd!B:u\u0001Yt\bBCA\u0013\u0001\t\u0015\r\u0011\"\u0001\u0002(!Q\u0011q\u0006\u0001\u0003\u0002\u0003\u0006I!!\u000b\t\u0015\u0005E\u0002A!b\u0001\n\u0003\t\u0019\u0004\u0003\u0006\u0002<\u0001\u0011\t\u0011)A\u0005\u0003kA!\"!\u0010\u0001\u0005\u000b\u0007I\u0011AA\u001a\u0011)\ty\u0004\u0001B\u0001B\u0003%\u0011Q\u0007\u0005\u000b\u0003\u0003\u0002!Q1A\u0005\u0002\u0005\u001d\u0002BCA\"\u0001\t\u0005\t\u0015!\u0003\u0002*!Q\u0011Q\t\u0001\u0003\u0006\u0004%\t!a\n\t\u0015\u0005\u001d\u0003A!A!\u0002\u0013\tI\u0003\u0003\u0006\u0002J\u0001\u0011)\u0019!C\u0001\u0003\u0017B!B!@\u0001\u0005\u0003\u0005\u000b\u0011BA'\u0011)\u0011y\u0010\u0001BC\u0002\u0013\u0005\u0011Q\u000e\u0005\u000b\u0007\u0003\u0001!\u0011!Q\u0001\n\u0005=\u0004BCB\u0002\u0001\t\u0015\r\u0011\"\u0001\u00024!Q1Q\u0001\u0001\u0003\u0002\u0003\u0006I!!\u000e\t\u000f\u0005\u001d\u0004\u0001\"\u0001\u0004\b!911\u0004\u0001\u0005\u0002\ru\u0001\"CB%\u0001E\u0005I\u0011AB&\u0011%\u0019y\u0005AI\u0001\n\u0003\u0011\u0019\u0010C\u0004\u0004R\u0001!Iaa\u0015\t\u000f\rm\u0003\u0001\"\u0003\u0004^\u001dA\u00111\u000b;\t\u0002Y\f)FB\u0004ti\"\u0005a/a\u0016\t\u000f\u0005\u001d\u0004\u0004\"\u0001\u0002j!I\u00111\u000e\rC\u0002\u0013\u0005\u0011Q\u000e\u0005\t\u0003kB\u0002\u0015!\u0003\u0002p\u0019I\u0011q\u000f\r\u0011\u0002G\u0005\u0012\u0011P\u0004\b\u0003oD\u0002\u0012QAG\r\u001d\ti\b\u0007EA\u0003\u007fBq!a\u001a\u001f\t\u0003\tY\tC\u0005\u0002\u0010z\t\t\u0011\"\u0011\u0002\u0012\"I\u0011q\u0014\u0010\u0002\u0002\u0013\u0005\u0011Q\u000e\u0005\n\u0003Cs\u0012\u0011!C\u0001\u0003GC\u0011\"a,\u001f\u0003\u0003%\t%!-\t\u0013\u0005}f$!A\u0005\u0002\u0005\u0005\u0007\"CAc=\u0005\u0005I\u0011IAd\u0011%\tIMHA\u0001\n\u0003\nY\rC\u0005\u0002Nz\t\t\u0011\"\u0003\u0002P\u001e9\u0011\u0011 \r\t\u0002\u0006ugaBAl1!\u0005\u0015\u0011\u001c\u0005\b\u0003OJC\u0011AAn\u0011%\ty)KA\u0001\n\u0003\n\t\nC\u0005\u0002 &\n\t\u0011\"\u0001\u0002n!I\u0011\u0011U\u0015\u0002\u0002\u0013\u0005\u0011q\u001c\u0005\n\u0003_K\u0013\u0011!C!\u0003cC\u0011\"a0*\u0003\u0003%\t!a9\t\u0013\u0005\u0015\u0017&!A\u0005B\u0005\u001d\u0007\"CAeS\u0005\u0005I\u0011IAf\u0011%\ti-KA\u0001\n\u0013\tymB\u0004\u0002|bA\t)!<\u0007\u000f\u0005\u001d\b\u0004#!\u0002j\"9\u0011q\r\u001b\u0005\u0002\u0005-\b\"CAHi\u0005\u0005I\u0011IAI\u0011%\ty\nNA\u0001\n\u0003\ti\u0007C\u0005\u0002\"R\n\t\u0011\"\u0001\u0002p\"I\u0011q\u0016\u001b\u0002\u0002\u0013\u0005\u0013\u0011\u0017\u0005\n\u0003\u007f#\u0014\u0011!C\u0001\u0003gD\u0011\"!25\u0003\u0003%\t%a2\t\u0013\u0005%G'!A\u0005B\u0005-\u0007\"CAgi\u0005\u0005I\u0011BAh\u0011%\ti\u0010\u0007b\u0001\n\u0003\ty\u0010\u0003\u0005\u0003\u000ea\u0001\u000b\u0011\u0002B\u0001\r\u0019\u0011y\u0001\u0007\u0003\u0003\u0012!9\u0011q\r!\u0005\u0002\tM\u0001\"\u0003B\f\u0001\u0002\u0007I\u0011AA\u0014\u0011%\u0011I\u0002\u0011a\u0001\n\u0003\u0011Y\u0002\u0003\u0005\u0003&\u0001\u0003\u000b\u0015BA\u0015\u0011-\u00119\u0003\u0011a\u0001\u0002\u0004%\t!!\u001c\t\u0017\t%\u0002\t1AA\u0002\u0013\u0005!1\u0006\u0005\f\u0005_\u0001\u0005\u0019!A!B\u0013\ty\u0007C\u0006\u00032\u0001\u0003\r\u00111A\u0005\u0002\tM\u0002b\u0003B\u001e\u0001\u0002\u0007\t\u0019!C\u0001\u0005{A1B!\u0011A\u0001\u0004\u0005\t\u0015)\u0003\u00036!Y!1\t!A\u0002\u0003\u0007I\u0011AA7\u0011-\u0011)\u0005\u0011a\u0001\u0002\u0004%\tAa\u0012\t\u0017\t-\u0003\t1A\u0001B\u0003&\u0011q\u000e\u0005\f\u0005\u001b\u0002\u0005\u0019!a\u0001\n\u0003\t\u0019\u0004C\u0006\u0003P\u0001\u0003\r\u00111A\u0005\u0002\tE\u0003b\u0003B+\u0001\u0002\u0007\t\u0011)Q\u0005\u0003kA1Ba\u0016A\u0001\u0004\u0005\r\u0011\"\u0003\u00024!Y!\u0011\f!A\u0002\u0003\u0007I\u0011\u0002B.\u0011-\u0011y\u0006\u0011a\u0001\u0002\u0003\u0006K!!\u000e\t\u0017\t\u0005\u0004\t1AA\u0002\u0013%\u00111\u0007\u0005\f\u0005G\u0002\u0005\u0019!a\u0001\n\u0013\u0011)\u0007C\u0006\u0003j\u0001\u0003\r\u0011!Q!\n\u0005U\u0002b\u0003B6\u0001\u0002\u0007\t\u0019!C\u0005\u0003gA1B!\u001cA\u0001\u0004\u0005\r\u0011\"\u0003\u0003p!Y!1\u000f!A\u0002\u0003\u0005\u000b\u0015BA\u001b\u0011-\u0011)\b\u0011a\u0001\u0002\u0004%IAa\u001e\t\u0017\t\u0015\u0005\t1AA\u0002\u0013%!q\u0011\u0005\f\u0005\u0017\u0003\u0005\u0019!A!B\u0013\u0011I\bC\u0006\u0003\u000e\u0002\u0003\r\u00111A\u0005\n\t]\u0004b\u0003BH\u0001\u0002\u0007\t\u0019!C\u0005\u0005#C1B!&A\u0001\u0004\u0005\t\u0015)\u0003\u0003z!Y!q\u0013!A\u0002\u0003\u0007I\u0011\u0002B<\u0011-\u0011I\n\u0011a\u0001\u0002\u0004%IAa'\t\u0017\t}\u0005\t1A\u0001B\u0003&!\u0011\u0010\u0005\b\u0005C\u0003E\u0011\u0002BR\u0011\u001d\u00119\u000b\u0011C\u0001\u0005SCqA!0A\t\u0003\u0011y\fC\u0004\u0003F\u0002#\tAa2\t\u000f\t%\u0007\t\"\u0001\u0003x!9!1\u001a!\u0005\u0002\u0005M\u0002b\u0002Bg\u0001\u0012\u0005\u00111\u0007\u0005\b\u0005\u001f\u0004E\u0011AA\u001a\u0011\u001d\u0011\t\u000e\u0011C\u0001\u0005oBqAa5A\t\u0003\u00119\bC\u0004\u0003V\u0002#\tAa\u001e\t\u000f\t]\u0007\t\"\u0001\u0003x!I!\u0011\u001c\r\u0012\u0002\u0013\u0005!1\u001c\u0005\n\u0005cD\u0012\u0013!C\u0001\u0005gD\u0011Ba>\u0019#\u0003%\tA!?\t\u0013\u00055\u0007$!A\u0005\n\u0005='\u0001F,fS\u001eDG/\u001a3MK\u0006\u001cHoU9vCJ,7O\u0003\u0002vm\u0006)q\u000e\u001d;j[*\u0011q\u000f_\u0001\u0003[2T!!\u001f>\u0002\u000bM\u0004\u0018M]6\u000b\u0005md\u0018AB1qC\u000eDWMC\u0001~\u0003\ry'oZ\n\u0005\u0001}\fY\u0001\u0005\u0003\u0002\u0002\u0005\u001dQBAA\u0002\u0015\t\t)!A\u0003tG\u0006d\u0017-\u0003\u0003\u0002\n\u0005\r!AB!osJ+g\r\u0005\u0003\u0002\u000e\u0005}a\u0002BA\b\u00037qA!!\u0005\u0002\u001a5\u0011\u00111\u0003\u0006\u0005\u0003+\t9\"\u0001\u0004=e>|GOP\u0002\u0001\u0013\t\t)!\u0003\u0003\u0002\u001e\u0005\r\u0011a\u00029bG.\fw-Z\u0005\u0005\u0003C\t\u0019C\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0003\u0002\u001e\u0005\r\u0011\u0001\u00044ji&sG/\u001a:dKB$XCAA\u0015!\u0011\t\t!a\u000b\n\t\u00055\u00121\u0001\u0002\b\u0005>|G.Z1o\u000351\u0017\u000e^%oi\u0016\u00148-\u001a9uA\u0005A!/Z4QCJ\fW.\u0006\u0002\u00026A!\u0011\u0011AA\u001c\u0013\u0011\tI$a\u0001\u0003\r\u0011{WO\u00197f\u0003%\u0011Xm\u001a)be\u0006l\u0007%A\bfY\u0006\u001cH/[2OKR\u0004\u0016M]1n\u0003A)G.Y:uS\u000etU\r\u001e)be\u0006l\u0007%A\nti\u0006tG-\u0019:eSj,g)Z1ukJ,7/\u0001\u000bti\u0006tG-\u0019:eSj,g)Z1ukJ,7\u000fI\u0001\u0011gR\fg\u000eZ1sI&TX\rT1cK2\f\u0011c\u001d;b]\u0012\f'\u000fZ5{K2\u000b'-\u001a7!\u0003)\u0019x\u000e\u001c<feRK\b/Z\u000b\u0003\u0003\u001b\u00022!a\u0014\u001d\u001d\r\t\tfF\u0007\u0002i\u0006!r+Z5hQR,G\rT3bgR\u001c\u0016/^1sKN\u00042!!\u0015\u0019'\u0011Ar0!\u0017\u0011\t\u0005m\u0013QM\u0007\u0003\u0003;RA!a\u0018\u0002b\u0005\u0011\u0011n\u001c\u0006\u0003\u0003G\nAA[1wC&!\u0011\u0011EA/\u0003\u0019a\u0014N\\5u}Q\u0011\u0011QK\u0001\u0011\u001b\u0006CvLT+N?\u001a+\u0015\tV+S\u000bN+\"!a\u001c\u0011\t\u0005\u0005\u0011\u0011O\u0005\u0005\u0003g\n\u0019AA\u0002J]R\f\u0011#T!Y?:+Vj\u0018$F\u0003R+&+R*!\u0005\u0019\u0019v\u000e\u001c<feN\u0011Ad`\u0015\u00059yICG\u0001\u0003BkR|7\u0003\u0003\u0010\u0000\u0003\u0003\u000b))a\u0003\u0011\u0007\u0005\rE$D\u0001\u0019!\u0011\t\t!a\"\n\t\u0005%\u00151\u0001\u0002\b!J|G-^2u)\t\ti\tE\u0002\u0002\u0004z\tQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAAJ!\u0011\t)*a'\u000e\u0005\u0005]%\u0002BAM\u0003C\nA\u0001\\1oO&!\u0011QTAL\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5us\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BAS\u0003W\u0003B!!\u0001\u0002(&!\u0011\u0011VA\u0002\u0005\r\te.\u001f\u0005\n\u0003[\u0013\u0013\u0011!a\u0001\u0003_\n1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAAZ!\u0019\t),a/\u0002&6\u0011\u0011q\u0017\u0006\u0005\u0003s\u000b\u0019!\u0001\u0006d_2dWm\u0019;j_:LA!!0\u00028\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\tI#a1\t\u0013\u00055F%!AA\u0002\u0005\u0015\u0016\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\u0005=\u0014\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005M\u0015\u0001D<sSR,'+\u001a9mC\u000e,GCAAi!\u0011\t)*a5\n\t\u0005U\u0017q\u0013\u0002\u0007\u001f\nTWm\u0019;\u0003\u0011\rCw\u000e\\3tWf\u001c\u0002\"K@\u0002\u0002\u0006\u0015\u00151\u0002\u000b\u0003\u0003;\u00042!a!*)\u0011\t)+!9\t\u0013\u00055V&!AA\u0002\u0005=D\u0003BA\u0015\u0003KD\u0011\"!,0\u0003\u0003\u0005\r!!*\u0003\u0017E+\u0018m]5OK^$xN\\\n\ti}\f\t)!\"\u0002\fQ\u0011\u0011Q\u001e\t\u0004\u0003\u0007#D\u0003BAS\u0003cD\u0011\"!,9\u0003\u0003\u0005\r!a\u001c\u0015\t\u0005%\u0012Q\u001f\u0005\n\u0003[S\u0014\u0011!a\u0001\u0003K\u000bA!Q;u_\u0006A1\t[8mKN\\\u00170A\u0006Rk\u0006\u001c\u0018NT3xi>t\u0017\u0001E:vaB|'\u000f^3e'>dg/\u001a:t+\t\u0011\t\u0001\u0005\u0004\u0002\u0002\t\r!qA\u0005\u0005\u0005\u000b\t\u0019AA\u0003BeJ\f\u0017P\u0005\u0005\u0003\n\u0005\u0015\u0015\u0011QA-\r\u0019\u0011Y\u0001\u0007\u0001\u0003\b\taAH]3gS:,W.\u001a8u}\u0005\t2/\u001e9q_J$X\rZ*pYZ,'o\u001d\u0011\u0003\u0015\u0005;wM]3hCR|'o\u0005\u0003A\u007f\u0006-AC\u0001B\u000b!\r\t\u0019\tQ\u0001\fS:LG/[1mSj,G-A\bj]&$\u0018.\u00197ju\u0016$w\fJ3r)\u0011\u0011iBa\t\u0011\t\u0005\u0005!qD\u0005\u0005\u0005C\t\u0019A\u0001\u0003V]&$\b\"CAW\u0007\u0006\u0005\t\u0019AA\u0015\u00031Ig.\u001b;jC2L'0\u001a3!\u0003\u0005Y\u0017!B6`I\u0015\fH\u0003\u0002B\u000f\u0005[A\u0011\"!,G\u0003\u0003\u0005\r!a\u001c\u0002\u0005-\u0004\u0013!B2pk:$XC\u0001B\u001b!\u0011\t\tAa\u000e\n\t\te\u00121\u0001\u0002\u0005\u0019>tw-A\u0005d_VtGo\u0018\u0013fcR!!Q\u0004B \u0011%\ti+SA\u0001\u0002\u0004\u0011)$\u0001\u0004d_VtG\u000fI\u0001\u0005iJL7*\u0001\u0005ue&\\u\fJ3r)\u0011\u0011iB!\u0013\t\u0013\u00055F*!AA\u0002\u0005=\u0014!\u0002;sS.\u0003\u0013\u0001B<Tk6\f\u0001b^*v[~#S-\u001d\u000b\u0005\u0005;\u0011\u0019\u0006C\u0005\u0002.>\u000b\t\u00111\u0001\u00026\u0005)qoU;nA\u0005)qo^*v[\u0006Iqo^*v[~#S-\u001d\u000b\u0005\u0005;\u0011i\u0006C\u0005\u0002.J\u000b\t\u00111\u0001\u00026\u00051qo^*v[\u0002\nAAY*v[\u0006A!mU;n?\u0012*\u0017\u000f\u0006\u0003\u0003\u001e\t\u001d\u0004\"CAW+\u0006\u0005\t\u0019AA\u001b\u0003\u0015\u00117+^7!\u0003\u0015\u0011'mU;n\u0003%\u0011'mU;n?\u0012*\u0017\u000f\u0006\u0003\u0003\u001e\tE\u0004\"CAW1\u0006\u0005\t\u0019AA\u001b\u0003\u0019\u0011'mU;nA\u0005!\u0011mU;n+\t\u0011I\b\u0005\u0003\u0003|\t\u0005UB\u0001B?\u0015\r\u0011yH^\u0001\u0007Y&t\u0017\r\\4\n\t\t\r%Q\u0010\u0002\f\t\u0016t7/\u001a,fGR|'/\u0001\u0005b'Vlw\fJ3r)\u0011\u0011iB!#\t\u0013\u000556,!AA\u0002\te\u0014!B1Tk6\u0004\u0013!B1c'Vl\u0017!C1c'Vlw\fJ3r)\u0011\u0011iBa%\t\u0013\u00055f,!AA\u0002\te\u0014AB1c'Vl\u0007%A\u0003bCN+X.A\u0005bCN+Xn\u0018\u0013fcR!!Q\u0004BO\u0011%\ti+YA\u0001\u0002\u0004\u0011I(\u0001\u0004bCN+X\u000eI\u0001\u0005S:LG\u000f\u0006\u0003\u0003\u001e\t\u0015\u0006b\u0002B\u0014G\u0002\u0007\u0011qN\u0001\u0004C\u0012$G\u0003\u0002BV\u0005[k\u0011\u0001\u0011\u0005\b\u0005_#\u0007\u0019\u0001BY\u0003!Ign\u001d;b]\u000e,\u0007\u0003\u0002BZ\u0005sk!A!.\u000b\u0007\t]f/A\u0004gK\u0006$XO]3\n\t\tm&Q\u0017\u0002\t\u0013:\u001cH/\u00198dK\u0006)Q.\u001a:hKR!!1\u0016Ba\u0011\u001d\u0011\u0019-\u001aa\u0001\u0005+\tQa\u001c;iKJ\f\u0001B^1mS\u0012\fG/\u001a\u000b\u0003\u0005;\tA!\u0019\"be\u0006!!MQ1s\u0003\u0015\u0011'MQ1s\u0003\u0011\u00117\u000b\u001e3\u0002\u000b\u0005\u0014')\u0019:\u0002\u000b\u0005\f')\u0019:\u0002\t\u0005\u001cF\u000fZ\u0001\u0005CZ\u000b'/A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HEN\u000b\u0003\u0005;TC!!\u0014\u0003`.\u0012!\u0011\u001d\t\u0005\u0005G\u0014i/\u0004\u0002\u0003f*!!q\u001dBu\u0003%)hn\u00195fG.,GM\u0003\u0003\u0003l\u0006\r\u0011AC1o]>$\u0018\r^5p]&!!q\u001eBs\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u001c\u0016\u0005\tU(\u0006BA8\u0005?\f1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012BTC\u0001B~U\u0011\t)Da8\u0002\u0017M|GN^3s)f\u0004X\rI\u0001\b[\u0006D\u0018\n^3s\u0003!i\u0017\r_%uKJ\u0004\u0013a\u0001;pY\u0006!Ao\u001c7!)I\u0019Iaa\u0003\u0004\u000e\r=1\u0011CB\n\u0007+\u00199b!\u0007\u0011\u0007\u0005E\u0003\u0001C\u0004\u0002&E\u0001\r!!\u000b\t\u000f\u0005E\u0012\u00031\u0001\u00026!9\u0011QH\tA\u0002\u0005U\u0002bBA!#\u0001\u0007\u0011\u0011\u0006\u0005\b\u0003\u000b\n\u0002\u0019AA\u0015\u0011%\tI%\u0005I\u0001\u0002\u0004\ti\u0005C\u0005\u0003\u0000F\u0001\n\u00111\u0001\u0002p!I11A\t\u0011\u0002\u0003\u0007\u0011QG\u0001\u0004M&$H\u0003CB\u0010\u0007K\u0019)d!\u0012\u0011\t\u0005E3\u0011E\u0005\u0004\u0007G!(!G,fS\u001eDG/\u001a3MK\u0006\u001cHoU9vCJ,7/T8eK2Dqaa\n\u0013\u0001\u0004\u0019I#A\u0005j]N$\u0018M\\2fgB111FB\u0019\u0005ck!a!\f\u000b\u0007\r=\u00020A\u0002sI\u0012LAaa\r\u0004.\t\u0019!\u000b\u0012#\t\u0013\r]\"\u0003%AA\u0002\re\u0012!B5ogR\u0014\b\u0003BB\u001e\u0007\u0003j!a!\u0010\u000b\u0007\r}b/\u0001\u0003vi&d\u0017\u0002BB\"\u0007{\u0011qc\u00149uS>t\u0017\r\\%ogR\u0014X/\\3oi\u0006$\u0018n\u001c8\t\u0013\r\u001d#\u0003%AA\u0002\u0005=\u0014!\u00023faRD\u0017!\u00044ji\u0012\"WMZ1vYR$#'\u0006\u0002\u0004N)\"1\u0011\bBp\u000351\u0017\u000e\u001e\u0013eK\u001a\fW\u000f\u001c;%g\u00051q-\u001a;Bi\u0006#bA!\u001f\u0004V\re\u0003b\u0002Bj+\u0001\u00071q\u000b\t\u0007\u0003\u0003\u0011\u0019!!\u000e\t\u000f\t%W\u00031\u0001\u0004X\u00051q-\u001a;Bi\n#bA!\u001f\u0004`\r\u0005\u0004b\u0002Bi-\u0001\u00071q\u000b\u0005\b\u0005\u00174\u0002\u0019AA\u001b\u0001"
)
public class WeightedLeastSquares implements Serializable {
   private final boolean fitIntercept;
   private final double regParam;
   private final double elasticNetParam;
   private final boolean standardizeFeatures;
   private final boolean standardizeLabel;
   private final Solver solverType;
   private final int maxIter;
   private final double tol;

   public static double $lessinit$greater$default$8() {
      return WeightedLeastSquares$.MODULE$.$lessinit$greater$default$8();
   }

   public static int $lessinit$greater$default$7() {
      return WeightedLeastSquares$.MODULE$.$lessinit$greater$default$7();
   }

   public static Solver $lessinit$greater$default$6() {
      return WeightedLeastSquares$.MODULE$.$lessinit$greater$default$6();
   }

   public static Product[] supportedSolvers() {
      return WeightedLeastSquares$.MODULE$.supportedSolvers();
   }

   public static int MAX_NUM_FEATURES() {
      return WeightedLeastSquares$.MODULE$.MAX_NUM_FEATURES();
   }

   public boolean fitIntercept() {
      return this.fitIntercept;
   }

   public double regParam() {
      return this.regParam;
   }

   public double elasticNetParam() {
      return this.elasticNetParam;
   }

   public boolean standardizeFeatures() {
      return this.standardizeFeatures;
   }

   public boolean standardizeLabel() {
      return this.standardizeLabel;
   }

   public Solver solverType() {
      return this.solverType;
   }

   public int maxIter() {
      return this.maxIter;
   }

   public double tol() {
      return this.tol;
   }

   public WeightedLeastSquaresModel fit(final RDD instances, final OptionalInstrumentation instr, final int depth) {
      if (this.regParam() == (double)0.0F) {
         instr.logWarning((Function0)(() -> "regParam is zero, which might cause numerical instability and overfitting."));
      }

      Aggregator summary = (Aggregator)instances.treeAggregate(new Aggregator(), (x$1, x$2) -> x$1.add(x$2), (x$3, x$4) -> x$3.merge(x$4), depth, .MODULE$.apply(Aggregator.class));
      summary.validate();
      instr.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> org.apache.spark.util.MavenUtils..MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Number of instances: ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.COUNT..MODULE$, BoxesRunTime.boxToLong(summary.count()))})))));
      int k = this.fitIntercept() ? summary.k() + 1 : summary.k();
      int numFeatures = summary.k();
      int triK = summary.triK();
      double wSum = summary.wSum();
      double rawBStd = summary.bStd();
      double rawBBar = summary.bBar();
      double bStd = rawBStd == (double)0.0F ? scala.math.package..MODULE$.abs(rawBBar) : rawBStd;
      if (rawBStd == (double)0) {
         if (this.fitIntercept() || rawBBar == (double)0.0F) {
            if (rawBBar == (double)0.0F) {
               instr.logWarning((Function0)(() -> "Mean and standard deviation of the label are zero, so the coefficients and the intercept will all be zero; as a result, training is not needed."));
            } else {
               instr.logWarning((Function0)(() -> "The standard deviation of the label is zero, so the coefficients will be zeros and the intercept will be the mean of the label; as a result, training is not needed."));
            }

            DenseVector coefficients = new DenseVector((double[])scala.Array..MODULE$.ofDim(numFeatures, .MODULE$.Double()));
            DenseVector diagInvAtWA = new DenseVector(new double[]{(double)0.0F});
            return new WeightedLeastSquaresModel(coefficients, rawBBar, diagInvAtWA, new double[]{(double)0.0F});
         }

         scala.Predef..MODULE$.require(!(this.regParam() > (double)0.0F) || !this.standardizeLabel(), () -> "The standard deviation of the label is zero. Model cannot be regularized when labels are standardized.");
         instr.logWarning((Function0)(() -> "The standard deviation of the label is zero. Consider setting fitIntercept=true."));
      }

      double bBar = summary.bBar() / bStd;
      double bbBar = summary.bbBar() / (bStd * bStd);
      DenseVector aStd = summary.aStd();
      double[] aStdValues = aStd.values();
      DenseVector _aBar = summary.aBar();
      double[] _aBarValues = _aBar.values();

      for(int i = 0; i < numFeatures; ++i) {
         if (aStdValues[i] == (double)0.0F) {
            _aBarValues[i] = (double)0.0F;
         } else {
            _aBarValues[i] /= aStdValues[i];
         }
      }

      DenseVector aBar = _aBar;
      double[] aBarValues = _aBar.values();
      DenseVector _abBar = summary.abBar();
      double[] _abBarValues = _abBar.values();

      for(int i = 0; i < numFeatures; ++i) {
         if (aStdValues[i] == (double)0.0F) {
            _abBarValues[i] = (double)0.0F;
         } else {
            _abBarValues[i] /= aStdValues[i] * bStd;
         }
      }

      double[] abBarValues = _abBar.values();
      DenseVector _aaBar = summary.aaBar();
      double[] _aaBarValues = _aaBar.values();
      int j = 0;

      for(int p = 0; j < numFeatures; ++j) {
         double aStdJ = aStdValues[j];

         for(int i = 0; i <= j; ++i) {
            double aStdI = aStdValues[i];
            if (aStdJ != (double)0.0F && aStdI != (double)0.0F) {
               _aaBarValues[p] /= aStdI * aStdJ;
            } else {
               _aaBarValues[p] = (double)0.0F;
            }

            ++p;
         }
      }

      double[] aaBarValues = _aaBar.values();
      double effectiveRegParam = this.regParam() / bStd;
      double effectiveL1RegParam = this.elasticNetParam() * effectiveRegParam;
      double effectiveL2RegParam = ((double)1.0F - this.elasticNetParam()) * effectiveRegParam;
      int i = 0;

      for(int j = 2; i < triK; ++j) {
         double lambda = effectiveL2RegParam;
         if (!this.standardizeFeatures()) {
            double std = aStdValues[j - 2];
            if (std != (double)0.0F) {
               lambda = effectiveL2RegParam / (std * std);
            } else {
               lambda = (double)0.0F;
            }
         }

         if (!this.standardizeLabel()) {
            lambda *= bStd;
         }

         aaBarValues[i] += lambda;
         i += j;
      }

      DenseVector aa;
      DenseVector ab;
      Object var96;
      label162: {
         label223: {
            label160: {
               label159: {
                  aa = this.getAtA(aaBarValues, aBarValues);
                  ab = this.getAtB(abBarValues, bBar);
                  Solver var10000 = this.solverType();
                  Auto$ var69 = WeightedLeastSquares.Auto$.MODULE$;
                  if (var10000 == null) {
                     if (var69 != null) {
                        break label159;
                     }
                  } else if (!var10000.equals(var69)) {
                     break label159;
                  }

                  if (this.elasticNetParam() != (double)0.0F && this.regParam() != (double)0.0F) {
                     break label160;
                  }
               }

               Solver var95 = this.solverType();
               QuasiNewton$ var70 = WeightedLeastSquares.QuasiNewton$.MODULE$;
               if (var95 == null) {
                  if (var70 != null) {
                     break label223;
                  }
               } else if (!var95.equals(var70)) {
                  break label223;
               }
            }

            Option effectiveL1RegFun = (Option)(effectiveL1RegParam != (double)0.0F ? new Some((JFunction1.mcDI.sp)(index) -> {
               if (this.fitIntercept() && index == numFeatures) {
                  return (double)0.0F;
               } else if (this.standardizeFeatures()) {
                  return effectiveL1RegParam;
               } else {
                  return aStdValues[index] != (double)0.0F ? effectiveL1RegParam / aStdValues[index] : (double)0.0F;
               }
            }) : scala.None..MODULE$);
            var96 = new QuasiNewtonSolver(this.fitIntercept(), this.maxIter(), this.tol(), effectiveL1RegFun);
            break label162;
         }

         var96 = new CholeskySolver();
      }

      NormalEquationSolver solver = (NormalEquationSolver)var96;
      NormalEquationSolution var97;
      if (solver instanceof CholeskySolver var74) {
         try {
            var97 = var74.solve(bBar, bbBar, ab, aa, aBar);
         } catch (Throwable var94) {
            label140: {
               if (var94 instanceof SingularMatrixException) {
                  Solver var98 = this.solverType();
                  Auto$ var77 = WeightedLeastSquares.Auto$.MODULE$;
                  if (var98 == null) {
                     if (var77 == null) {
                        break label140;
                     }
                  } else if (var98.equals(var77)) {
                     break label140;
                  }
               }

               throw var94;
            }

            instr.logWarning((Function0)(() -> "Cholesky solver failed due to singular covariance matrix. Retrying with Quasi-Newton solver."));
            DenseVector _aa = this.getAtA(aaBarValues, aBarValues);
            DenseVector _ab = this.getAtB(abBarValues, bBar);
            QuasiNewtonSolver newSolver = new QuasiNewtonSolver(this.fitIntercept(), this.maxIter(), this.tol(), scala.None..MODULE$);
            var97 = newSolver.solve(bBar, bbBar, _ab, _aa, _aBar);
         }
      } else {
         if (!(solver instanceof QuasiNewtonSolver)) {
            throw new MatchError(solver);
         }

         QuasiNewtonSolver var81 = (QuasiNewtonSolver)solver;
         var97 = var81.solve(bBar, bbBar, ab, aa, _aBar);
      }

      NormalEquationSolution solution = var97;
      Tuple2 var83 = this.fitIntercept() ? new Tuple2(scala.collection.ArrayOps..MODULE$.slice$extension(scala.Predef..MODULE$.doubleArrayOps(solution.coefficients()), 0, solution.coefficients().length - 1), BoxesRunTime.boxToDouble(BoxesRunTime.unboxToDouble(scala.collection.ArrayOps..MODULE$.last$extension(scala.Predef..MODULE$.doubleArrayOps(solution.coefficients()))) * bStd)) : new Tuple2(solution.coefficients(), BoxesRunTime.boxToDouble((double)0.0F));
      if (var83 != null) {
         double[] coefficientArray = (double[])var83._1();
         double intercept = var83._2$mcD$sp();
         Tuple2 var82 = new Tuple2(coefficientArray, BoxesRunTime.boxToDouble(intercept));
         double[] coefficientArray = (double[])var82._1();
         double intercept = var82._2$mcD$sp();
         int q = 0;

         for(int len = coefficientArray.length; q < len; ++q) {
            coefficientArray[q] *= aStdValues[q] != (double)0.0F ? bStd / aStdValues[q] : (double)0.0F;
         }

         DenseVector diagInvAtWA = (DenseVector)solution.aaInv().map((inv) -> new DenseVector((double[])scala.runtime.RichInt..MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(1), k).map((JFunction1.mcDI.sp)(i) -> {
               double multiplier = i == k && this.fitIntercept() ? (double)1.0F : aStdValues[i - 1] * aStdValues[i - 1];
               return inv[i + (i - 1) * i / 2 - 1] / (wSum * multiplier);
            }).toArray(.MODULE$.Double()))).getOrElse(() -> new DenseVector(new double[]{(double)0.0F}));
         return new WeightedLeastSquaresModel(new DenseVector(coefficientArray), intercept, diagInvAtWA, (double[])solution.objectiveHistory().getOrElse(() -> new double[]{(double)0.0F}));
      } else {
         throw new MatchError(var83);
      }
   }

   public OptionalInstrumentation fit$default$2() {
      return OptionalInstrumentation$.MODULE$.create(WeightedLeastSquares.class);
   }

   public int fit$default$3() {
      return 2;
   }

   private DenseVector getAtA(final double[] aaBar, final double[] aBar) {
      return this.fitIntercept() ? new DenseVector((double[])scala.Array..MODULE$.concat(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new double[][]{aaBar, aBar, {(double)1.0F}})), .MODULE$.Double())) : new DenseVector((double[])(([D)aaBar).clone());
   }

   private DenseVector getAtB(final double[] abBar, final double bBar) {
      return this.fitIntercept() ? new DenseVector((double[])scala.Array..MODULE$.concat(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new double[][]{abBar, {bBar}})), .MODULE$.Double())) : new DenseVector((double[])(([D)abBar).clone());
   }

   public WeightedLeastSquares(final boolean fitIntercept, final double regParam, final double elasticNetParam, final boolean standardizeFeatures, final boolean standardizeLabel, final Solver solverType, final int maxIter, final double tol) {
      this.fitIntercept = fitIntercept;
      this.regParam = regParam;
      this.elasticNetParam = elasticNetParam;
      this.standardizeFeatures = standardizeFeatures;
      this.standardizeLabel = standardizeLabel;
      this.solverType = solverType;
      this.maxIter = maxIter;
      this.tol = tol;
      scala.Predef..MODULE$.require(regParam >= (double)0.0F, () -> "regParam cannot be negative: " + this.regParam());
      scala.Predef..MODULE$.require(elasticNetParam >= (double)0.0F && elasticNetParam <= (double)1.0F, () -> "elasticNetParam must be in [0, 1]: " + this.elasticNetParam());
      scala.Predef..MODULE$.require(maxIter > 0, () -> "maxIter must be a positive integer: " + this.maxIter());
      scala.Predef..MODULE$.require(tol >= (double)0.0F, () -> "tol must be >= 0, but was set to " + this.tol());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class Auto$ implements Solver, Product, Serializable {
      public static final Auto$ MODULE$ = new Auto$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "Auto";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         return Statics.ioobe(x$1);
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Auto$;
      }

      public int hashCode() {
         return 2052559;
      }

      public String toString() {
         return "Auto";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Auto$.class);
      }
   }

   public static class Cholesky$ implements Solver, Product, Serializable {
      public static final Cholesky$ MODULE$ = new Cholesky$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "Cholesky";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         return Statics.ioobe(x$1);
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Cholesky$;
      }

      public int hashCode() {
         return 1895976638;
      }

      public String toString() {
         return "Cholesky";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Cholesky$.class);
      }
   }

   public static class QuasiNewton$ implements Solver, Product, Serializable {
      public static final QuasiNewton$ MODULE$ = new QuasiNewton$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "QuasiNewton";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         return Statics.ioobe(x$1);
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof QuasiNewton$;
      }

      public int hashCode() {
         return -682498234;
      }

      public String toString() {
         return "QuasiNewton";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(QuasiNewton$.class);
      }
   }

   private static class Aggregator implements Serializable {
      private boolean initialized = false;
      private int k;
      private long count;
      private int triK;
      private double wSum;
      private double wwSum;
      private double bSum;
      private double bbSum;
      private DenseVector aSum;
      private DenseVector abSum;
      private DenseVector aaSum;

      public boolean initialized() {
         return this.initialized;
      }

      public void initialized_$eq(final boolean x$1) {
         this.initialized = x$1;
      }

      public int k() {
         return this.k;
      }

      public void k_$eq(final int x$1) {
         this.k = x$1;
      }

      public long count() {
         return this.count;
      }

      public void count_$eq(final long x$1) {
         this.count = x$1;
      }

      public int triK() {
         return this.triK;
      }

      public void triK_$eq(final int x$1) {
         this.triK = x$1;
      }

      public double wSum() {
         return this.wSum;
      }

      public void wSum_$eq(final double x$1) {
         this.wSum = x$1;
      }

      private double wwSum() {
         return this.wwSum;
      }

      private void wwSum_$eq(final double x$1) {
         this.wwSum = x$1;
      }

      private double bSum() {
         return this.bSum;
      }

      private void bSum_$eq(final double x$1) {
         this.bSum = x$1;
      }

      private double bbSum() {
         return this.bbSum;
      }

      private void bbSum_$eq(final double x$1) {
         this.bbSum = x$1;
      }

      private DenseVector aSum() {
         return this.aSum;
      }

      private void aSum_$eq(final DenseVector x$1) {
         this.aSum = x$1;
      }

      private DenseVector abSum() {
         return this.abSum;
      }

      private void abSum_$eq(final DenseVector x$1) {
         this.abSum = x$1;
      }

      private DenseVector aaSum() {
         return this.aaSum;
      }

      private void aaSum_$eq(final DenseVector x$1) {
         this.aaSum = x$1;
      }

      private void init(final int k) {
         scala.Predef..MODULE$.require(k <= WeightedLeastSquares$.MODULE$.MAX_NUM_FEATURES(), () -> {
            int var10000 = WeightedLeastSquares$.MODULE$.MAX_NUM_FEATURES();
            return "In order to take the normal equation approach efficiently, we set the max number of features to " + var10000 + " but got " + k + ".";
         });
         this.k_$eq(k);
         this.triK_$eq(k * (k + 1) / 2);
         this.count_$eq(0L);
         this.wSum_$eq((double)0.0F);
         this.wwSum_$eq((double)0.0F);
         this.bSum_$eq((double)0.0F);
         this.bbSum_$eq((double)0.0F);
         this.aSum_$eq(new DenseVector((double[])scala.Array..MODULE$.ofDim(k, .MODULE$.Double())));
         this.abSum_$eq(new DenseVector((double[])scala.Array..MODULE$.ofDim(k, .MODULE$.Double())));
         this.aaSum_$eq(new DenseVector((double[])scala.Array..MODULE$.ofDim(this.triK(), .MODULE$.Double())));
         this.initialized_$eq(true);
      }

      public Aggregator add(final Instance instance) {
         if (instance != null) {
            double l = instance.label();
            double w = instance.weight();
            Vector f = instance.features();
            Tuple3 var3 = new Tuple3(BoxesRunTime.boxToDouble(l), BoxesRunTime.boxToDouble(w), f);
            double l = BoxesRunTime.unboxToDouble(var3._1());
            double w = BoxesRunTime.unboxToDouble(var3._2());
            Vector f = (Vector)var3._3();
            int ak = f.size();
            if (!this.initialized()) {
               this.init(ak);
            }

            scala.Predef..MODULE$.assert(ak == this.k(), () -> {
               int var10000 = this.k();
               return "Dimension mismatch. Expect vectors of size " + var10000 + " but got " + ak + ".";
            });
            this.count_$eq(this.count() + 1L);
            this.wSum_$eq(this.wSum() + w);
            this.wwSum_$eq(this.wwSum() + w * w);
            this.bSum_$eq(this.bSum() + w * l);
            this.bbSum_$eq(this.bbSum() + w * l * l);
            org.apache.spark.ml.linalg.BLAS..MODULE$.axpy(w, f, this.aSum());
            org.apache.spark.ml.linalg.BLAS..MODULE$.axpy(w * l, f, this.abSum());
            org.apache.spark.ml.linalg.BLAS..MODULE$.spr(w, f, this.aaSum());
            return this;
         } else {
            throw new MatchError(instance);
         }
      }

      public Aggregator merge(final Aggregator other) {
         if (!other.initialized()) {
            return this;
         } else {
            if (!this.initialized()) {
               this.init(other.k());
            }

            scala.Predef..MODULE$.assert(this.k() == other.k(), () -> {
               int var10000 = this.k();
               return "dimension mismatch: this.k = " + var10000 + " but other.k = " + other.k();
            });
            this.count_$eq(this.count() + other.count());
            this.wSum_$eq(this.wSum() + other.wSum());
            this.wwSum_$eq(this.wwSum() + other.wwSum());
            this.bSum_$eq(this.bSum() + other.bSum());
            this.bbSum_$eq(this.bbSum() + other.bbSum());
            org.apache.spark.ml.linalg.BLAS..MODULE$.axpy((double)1.0F, other.aSum(), this.aSum());
            org.apache.spark.ml.linalg.BLAS..MODULE$.axpy((double)1.0F, other.abSum(), this.abSum());
            org.apache.spark.ml.linalg.BLAS..MODULE$.axpy((double)1.0F, other.aaSum(), this.aaSum());
            return this;
         }
      }

      public void validate() {
         scala.Predef..MODULE$.assert(this.initialized(), () -> "Training dataset is empty.");
         scala.Predef..MODULE$.assert(this.wSum() > (double)0.0F, () -> "Sum of weights cannot be zero.");
      }

      public DenseVector aBar() {
         DenseVector output = this.aSum().copy();
         org.apache.spark.ml.linalg.BLAS..MODULE$.scal((double)1.0F / this.wSum(), output);
         return output;
      }

      public double bBar() {
         return this.bSum() / this.wSum();
      }

      public double bbBar() {
         return this.bbSum() / this.wSum();
      }

      public double bStd() {
         double variance = scala.math.package..MODULE$.max(this.bbSum() / this.wSum() - this.bBar() * this.bBar(), (double)0.0F);
         return scala.math.package..MODULE$.sqrt(variance);
      }

      public DenseVector abBar() {
         DenseVector output = this.abSum().copy();
         org.apache.spark.ml.linalg.BLAS..MODULE$.scal((double)1.0F / this.wSum(), output);
         return output;
      }

      public DenseVector aaBar() {
         DenseVector output = this.aaSum().copy();
         org.apache.spark.ml.linalg.BLAS..MODULE$.scal((double)1.0F / this.wSum(), output);
         return output;
      }

      public DenseVector aStd() {
         double[] std = (double[])scala.Array..MODULE$.ofDim(this.k(), .MODULE$.Double());
         int i = 0;
         int j = 2;

         for(double[] aaValues = this.aaSum().values(); i < this.triK(); ++j) {
            int l = j - 2;
            double aw = this.aSum().apply(l) / this.wSum();
            std[l] = scala.math.package..MODULE$.sqrt(scala.math.package..MODULE$.max(aaValues[i] / this.wSum() - aw * aw, (double)0.0F));
            i += j;
         }

         return new DenseVector(std);
      }

      public DenseVector aVar() {
         double[] variance = (double[])scala.Array..MODULE$.ofDim(this.k(), .MODULE$.Double());
         int i = 0;
         int j = 2;

         for(double[] aaValues = this.aaSum().values(); i < this.triK(); ++j) {
            int l = j - 2;
            double aw = this.aSum().apply(l) / this.wSum();
            variance[l] = scala.math.package..MODULE$.max(aaValues[i] / this.wSum() - aw * aw, (double)0.0F);
            i += j;
         }

         return new DenseVector(variance);
      }

      public Aggregator() {
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public interface Solver {
   }
}
