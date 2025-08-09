package breeze.linalg;

import breeze.collection.mutable.OpenAddressHashArray;
import breeze.generic.UFunc;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanCreateZeros;
import breeze.linalg.support.CanMapKeyValuePairs;
import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.CanTranspose;
import breeze.linalg.support.CanTraverseKeyValuePairs;
import breeze.linalg.support.CanTraverseValues;
import breeze.linalg.support.ScalarOf;
import breeze.linalg.support.TensorActive;
import breeze.linalg.support.TensorKeys;
import breeze.linalg.support.TensorPairs;
import breeze.linalg.support.TensorValues;
import breeze.math.Field;
import breeze.math.MutableFiniteCoordinateField;
import breeze.storage.Zero;
import java.io.Serializable;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011Ma\u0001\u0002\u00180\u0001QB\u0001b\u001d\u0001\u0003\u0006\u0004%\t\u0001\u001e\u0005\t{\u0002\u0011\t\u0011)A\u0005k\")a\u0010\u0001C\u0001\u007f\"9\u00111\u0001\u0001\u0005\u0002\u0005\u0015\u0001bBA\r\u0001\u0011\u0005\u00111\u0004\u0005\b\u0003?\u0001A\u0011AA\u0011\u0011\u001d\t)\u0003\u0001C\u0001\u0003OAq!!\f\u0001\t\u0003\ty\u0003C\u0004\u0002>\u0001!\t!a\u0010\t\u000f\u0005\u0005\u0003\u0001\"\u0001\u0002D!9\u0011Q\t\u0001\u0005\u0002\u0005\r\u0003bBA$\u0001\u0011\u0005\u0011\u0011\n\u0005\b\u0003\u0017\u0002A\u0011AA%\u0011\u001d\ti\u0005\u0001C\u0003\u0003\u0007Bq!a\u0014\u0001\t\u0003\t\t\u0006C\u0004\u0002Z\u0001!)!a\u0017\t\u000f\u0005}\u0003\u0001\"\u0002\u0002b!9\u00111\u000e\u0001\u0005\u0002\u00055\u0004bBA8\u0001\u0011\u0005\u0013\u0011\u000f\u0005\b\u0003\u0007\u0003A\u0011AAC\u0011\u001d\t9\t\u0001C!\u0003\u0013;q!a#0\u0011\u0003\tiI\u0002\u0004/_!\u0005\u0011q\u0012\u0005\u0007}^!\t!a(\t\u000f\u0005\u0005v\u0003\"\u0001\u0002$\"9\u0011QE\f\u0005\u0002\u0005\u001d\bbBA\u0013/\u0011\u0005!\u0011\u0003\u0005\b\u0005c9B\u0011\u0001B\u001a\u0011\u001d\u0011Ig\u0006C\u0001\u0005WBq!!\n\u0018\t\u0003\u0011\u0019\u000bC\u0004\u0003F^!\u0019Aa2\u0007\r\t%x\u0003\u0001Bv\u0011)\u0019I\u0001\tB\u0002B\u0003-11\u0002\u0005\u000b\u0007\u001b\u0001#1!Q\u0001\f\r=\u0001B\u0002@!\t\u0003\u0019\t\u0002C\u0004\u0002&\u0001\"\ta!\b\t\u000f\r\rr\u0003b\u0001\u0004&!91\u0011J\f\u0005\u0004\r-\u0003bBB8/\u0011\r1\u0011\u000f\u0005\b\u0007\u0007;B1ABC\u0011\u001d\u0019)j\u0006C\u0002\u0007/Cqaa*\u0018\t\u0007\u0019I\u000bC\u0004\u0004L^!\u0019a!4\t\u000f\rex\u0003\"\u0003\u0002n!IA1A\f\u0002\u0002\u0013%AQ\u0001\u0002\u000b\u0011\u0006\u001c\bNV3di>\u0014(B\u0001\u00192\u0003\u0019a\u0017N\\1mO*\t!'\u0001\u0004ce\u0016,'0Z\u0002\u0001+\t)$iE\u0003\u0001mqBG\u000e\u0005\u00028u5\t\u0001HC\u0001:\u0003\u0015\u00198-\u00197b\u0013\tY\u0004H\u0001\u0004B]f\u0014VM\u001a\t\u0004{y\u0002U\"A\u0018\n\u0005}z#A\u0002,fGR|'\u000f\u0005\u0002B\u00052\u0001A!C\"\u0001A\u0003\u0005\tQ1\u0001E\u0005\u0005)\u0015CA#I!\t9d)\u0003\u0002Hq\t9aj\u001c;iS:<\u0007CA\u001cJ\u0013\tQ\u0005HA\u0002B]fDcA\u0011'P3z\u001b\u0007CA\u001cN\u0013\tq\u0005HA\u0006ta\u0016\u001c\u0017.\u00197ju\u0016$\u0017'B\u0012Q#N\u0013fBA\u001cR\u0013\t\u0011\u0006(\u0001\u0004E_V\u0014G.Z\u0019\u0005IQC\u0016H\u0004\u0002V16\taK\u0003\u0002Xg\u00051AH]8pizJ\u0011!O\u0019\u0006Gi[V\f\u0018\b\u0003omK!\u0001\u0018\u001d\u0002\u0007%sG/\r\u0003%)bK\u0014'B\u0012`A\n\fgBA\u001ca\u0013\t\t\u0007(A\u0003GY>\fG/\r\u0003%)bK\u0014'B\u0012eK\u001e4gBA\u001cf\u0013\t1\u0007(\u0001\u0003M_:<\u0017\u0007\u0002\u0013U1f\u0002B!P5AW&\u0011!n\f\u0002\u000b-\u0016\u001cGo\u001c:MS.,\u0007cA\u001f\u0001\u0001B\u0011Q\u000e\u001d\b\u0003):L!a\u001c\u001d\u0002\u000fA\f7m[1hK&\u0011\u0011O\u001d\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003_b\nQ!\u0019:sCf,\u0012!\u001e\t\u0004mn\u0004U\"A<\u000b\u0005aL\u0018aB7vi\u0006\u0014G.\u001a\u0006\u0003uF\n!bY8mY\u0016\u001cG/[8o\u0013\taxO\u0001\u000bPa\u0016t\u0017\t\u001a3sKN\u001c\b*Y:i\u0003J\u0014\u0018-_\u0001\u0007CJ\u0014\u0018-\u001f\u0011\u0002\rqJg.\u001b;?)\rY\u0017\u0011\u0001\u0005\u0006g\u000e\u0001\r!^\u0001\u000fC\u000e$\u0018N^3Ji\u0016\u0014\u0018\r^8s+\t\t9\u0001E\u0003n\u0003\u0013\ti!C\u0002\u0002\fI\u0014\u0001\"\u0013;fe\u0006$xN\u001d\t\u0007o\u0005=\u00111\u0003!\n\u0007\u0005E\u0001H\u0001\u0004UkBdWM\r\t\u0004o\u0005U\u0011bAA\fq\t\u0019\u0011J\u001c;\u0002)\u0005\u001cG/\u001b<f-\u0006dW/Z:Ji\u0016\u0014\u0018\r^8s+\t\ti\u0002\u0005\u0003n\u0003\u0013\u0001\u0015AE1di&4XmS3zg&#XM]1u_J,\"!a\t\u0011\u000b5\fI!a\u0005\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0007\u0001\u000bI\u0003C\u0004\u0002,\u001d\u0001\r!a\u0005\u0002\u0003%\fa!\u001e9eCR,GCBA\u0019\u0003o\tI\u0004E\u00028\u0003gI1!!\u000e9\u0005\u0011)f.\u001b;\t\u000f\u0005-\u0002\u00021\u0001\u0002\u0014!1\u00111\b\u0005A\u0002\u0001\u000b\u0011A^\u0001\bI\u00164\u0017-\u001e7u+\u0005\u0001\u0015AC1di&4XmU5{KV\u0011\u00111C\u0001\u0007Y\u0016tw\r\u001e5\u0002\t\r|\u0007/_\u000b\u0002W\u0006!!/\u001a9s\u00031IG/\u001a:bE2,7+\u001b>f\u0003\u0011!\u0017\r^1\u0016\u0005\u0005M\u0003\u0003B\u001c\u0002V\u0001K1!a\u00169\u0005\u0015\t%O]1z\u0003\u0015Ig\u000eZ3y+\t\ti\u0006E\u00038\u0003+\n\u0019\"\u0001\u0005jg\u0006\u001bG/\u001b<f)\u0011\t\u0019'!\u001b\u0011\u0007]\n)'C\u0002\u0002ha\u0012qAQ8pY\u0016\fg\u000eC\u0004\u0002,E\u0001\r!a\u0005\u0002\u000b\rdW-\u0019:\u0015\u0005\u0005E\u0012\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005M\u0004\u0003BA;\u0003{rA!a\u001e\u0002zA\u0011Q\u000bO\u0005\u0004\u0003wB\u0014A\u0002)sK\u0012,g-\u0003\u0003\u0002\u0000\u0005\u0005%AB*ue&twMC\u0002\u0002|a\n\u0011$\u00197m-&\u001c\u0018\u000e^1cY\u0016Le\u000eZ5dKN\f5\r^5wKV\u0011\u00111M\u0001\tQ\u0006\u001c\bnQ8eKR\u0011\u00111C\u0001\u000b\u0011\u0006\u001c\bNV3di>\u0014\bCA\u001f\u0018'\u00119b'!%\u0011\t\u0005M\u0015QT\u0007\u0003\u0003+SA!a&\u0002\u001a\u0006\u0011\u0011n\u001c\u0006\u0003\u00037\u000bAA[1wC&\u0019\u0011/!&\u0015\u0005\u00055\u0015!\u0002>fe>\u001cX\u0003BAS\u0003[#B!a*\u0002dR1\u0011\u0011VAb\u0003'\u0004B!\u0010\u0001\u0002,B\u0019\u0011)!,\u0005\u0015\u0005=\u0016\u0004)A\u0001\u0002\u000b\u0007AIA\u0001WQ-\ti\u000bTAZ\u0003o\u000bY,a02\r\r\u0002\u0016+!.Sc\u0011!C\u000bW\u001d2\r\rR6,!/]c\u0011!C\u000bW\u001d2\r\rz\u0006-!0bc\u0011!C\u000bW\u001d2\r\r\"W-!1gc\u0011!C\u000bW\u001d\t\u0013\u0005\u0015\u0017$!AA\u0004\u0005\u001d\u0017AC3wS\u0012,gnY3%cA1\u0011\u0011ZAh\u0003Wk!!a3\u000b\u0007\u00055\u0007(A\u0004sK\u001adWm\u0019;\n\t\u0005E\u00171\u001a\u0002\t\u00072\f7o\u001d+bO\"I\u0011Q[\r\u0002\u0002\u0003\u000f\u0011q[\u0001\u000bKZLG-\u001a8dK\u0012\u0012\u0004CBAm\u0003?\fY+\u0004\u0002\u0002\\*\u0019\u0011Q\\\u0019\u0002\u000fM$xN]1hK&!\u0011\u0011]An\u0005\u0011QVM]8\t\u000f\u0005\u0015\u0018\u00041\u0001\u0002\u0014\u0005!1/\u001b>f+\u0011\tI/!=\u0015\t\u0005-(1\u0002\u000b\u0005\u0003[\u0014)\u0001\u0005\u0003>\u0001\u0005=\bcA!\u0002r\u0012Q\u0011q\u0016\u000e!\u0002\u0003\u0005)\u0019\u0001#)\u0017\u0005EH*!>\u0002z\u0006u(\u0011A\u0019\u0007GA\u000b\u0016q\u001f*2\t\u0011\"\u0006,O\u0019\u0007Gi[\u00161 /2\t\u0011\"\u0006,O\u0019\u0007G}\u0003\u0017q`12\t\u0011\"\u0006,O\u0019\u0007G\u0011,'1\u000142\t\u0011\"\u0006,\u000f\u0005\n\u0005\u000fQ\u0012\u0011!a\u0002\u0005\u0013\t!\"\u001a<jI\u0016t7-\u001a\u00134!\u0019\tI.a8\u0002p\"9!Q\u0002\u000eA\u0002\t=\u0011A\u0002<bYV,7\u000fE\u00038\u0003+\ny/\u0006\u0003\u0003\u0014\tmA\u0003\u0002B\u000b\u0005S!bAa\u0006\u0003\u001e\t\r\u0002\u0003B\u001f\u0001\u00053\u00012!\u0011B\u000e\t\u0019\tyk\u0007b\u0001\t\"I!qD\u000e\u0002\u0002\u0003\u000f!\u0011E\u0001\u000bKZLG-\u001a8dK\u0012\"\u0004CBAe\u0003\u001f\u0014I\u0002C\u0005\u0003&m\t\t\u0011q\u0001\u0003(\u0005QQM^5eK:\u001cW\rJ\u001b\u0011\r\u0005e\u0017q\u001cB\r\u0011\u001d\u0011ia\u0007a\u0001\u0005W\u0001Ra\u000eB\u0017\u00053I1Aa\f9\u0005)a$/\u001a9fCR,GMP\u0001\u0005M&dG.\u0006\u0003\u00036\t}B\u0003\u0002B\u001c\u0005O\"BA!\u000f\u0003`Q1!1\bB*\u00053\u0002B!\u0010\u0001\u0003>A\u0019\u0011Ia\u0010\u0005\u0015\u0005=F\u0004)A\u0001\u0002\u000b\u0007A\tK\u0006\u0003@1\u0013\u0019Ea\u0012\u0003L\t=\u0013GB\u0012Q#\n\u0015#+\r\u0003%)bK\u0014GB\u0012[7\n%C,\r\u0003%)bK\u0014GB\u0012`A\n5\u0013-\r\u0003%)bK\u0014GB\u0012eK\nEc-\r\u0003%)bK\u0004\"\u0003B+9\u0005\u0005\t9\u0001B,\u0003))g/\u001b3f]\u000e,GE\u000e\t\u0007\u0003\u0013\fyM!\u0010\t\u0013\tmC$!AA\u0004\tu\u0013AC3wS\u0012,gnY3%oA1\u0011\u0011\\Ap\u0005{A\u0001\"a\u000f\u001d\t\u0003\u0007!\u0011\r\t\u0006o\t\r$QH\u0005\u0004\u0005KB$\u0001\u0003\u001fcs:\fW.\u001a \t\u000f\u0005\u0015H\u00041\u0001\u0002\u0014\u0005AA/\u00192vY\u0006$X-\u0006\u0003\u0003n\t]D\u0003\u0002B8\u0005C#BA!\u001d\u0003\u0018R1!1\u000fBF\u0005#\u0003B!\u0010\u0001\u0003vA\u0019\u0011Ia\u001e\u0005\u0015\u0005=V\u0004)A\u0001\u0002\u000b\u0007A\tK\u0006\u0003x1\u0013YHa \u0003\u0004\n\u001d\u0015GB\u0012Q#\nu$+\r\u0003%)bK\u0014GB\u0012[7\n\u0005E,\r\u0003%)bK\u0014GB\u0012`A\n\u0015\u0015-\r\u0003%)bK\u0014GB\u0012eK\n%e-\r\u0003%)bK\u0004\"\u0003BG;\u0005\u0005\t9\u0001BH\u0003))g/\u001b3f]\u000e,G\u0005\u000f\t\u0007\u0003\u0013\fyM!\u001e\t\u0013\tMU$!AA\u0004\tU\u0015AC3wS\u0012,gnY3%sA1\u0011\u0011\\Ap\u0005kBqA!'\u001e\u0001\u0004\u0011Y*A\u0001g!\u001d9$QTA\n\u0005kJ1Aa(9\u0005%1UO\\2uS>t\u0017\u0007C\u0004\u0002fv\u0001\r!a\u0005\u0016\t\t\u0015&q\u0016\u000b\u0005\u0005O\u0013\u0019\r\u0006\u0003\u0003*\nuFC\u0002BV\u0005c\u00139\f\u0005\u0003>\u0001\t5\u0006cA!\u00030\u00121\u0011q\u0016\u0010C\u0002\u0011C\u0011Ba-\u001f\u0003\u0003\u0005\u001dA!.\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007\r\t\u0007\u0003\u0013\fyM!,\t\u0013\tef$!AA\u0004\tm\u0016aC3wS\u0012,gnY3%cE\u0002b!!7\u0002`\n5\u0006b\u0002B\u0007=\u0001\u0007!q\u0018\t\u0006o\t5\"\u0011\u0019\t\bo\u0005=\u00111\u0003BW\u0011\u001d\t)E\ba\u0001\u0003'\tabY1o\u0007J,\u0017\r^3[KJ|7/\u0006\u0003\u0003J\nmGC\u0002Bf\u0005;\u0014\u0019\u000f\u0005\u0005\u0003N\nM'q[A\n\u001b\t\u0011yMC\u0002\u0003R>\nqa];qa>\u0014H/\u0003\u0003\u0003V\n='AD\"b]\u000e\u0013X-\u0019;f5\u0016\u0014xn\u001d\t\u0005{\u0001\u0011I\u000eE\u0002B\u00057$a!a, \u0005\u0004!\u0005\"\u0003Bp?\u0005\u0005\t9\u0001Bq\u0003-)g/\u001b3f]\u000e,G%\r\u001a\u0011\r\u0005%\u0017q\u001aBm\u0011%\u0011)oHA\u0001\u0002\b\u00119/A\u0006fm&$WM\\2fIE\u001a\u0004CBAm\u0003?\u0014INA\tDC:\u001cu\u000e]=ICNDg+Z2u_J,BA!<\u0003zN!\u0001E\u000eBx!\u0019\u0011iM!=\u0003v&!!1\u001fBh\u0005\u001d\u0019\u0015M\\\"paf\u0004B!\u0010\u0001\u0003xB\u0019\u0011I!?\u0005\u0015\u0005=\u0006\u0005)A\u0001\u0002\u000b\u0007A\tK\u0005\u0003z2\u0013ip!\u0001\u0004\u0006E21EW.\u0003\u0000r\u000bD\u0001\n+YsE21e\u00181\u0004\u0004\u0005\fD\u0001\n+YsE21\u0005U)\u0004\bI\u000bD\u0001\n+Ys\u0005YQM^5eK:\u001cW\rJ\u00195!\u0019\tI-a4\u0003x\u0006YQM^5eK:\u001cW\rJ\u00196!\u0019\tI.a8\u0003xR\u001111\u0003\u000b\u0007\u0007+\u0019Iba\u0007\u0011\u000b\r]\u0001Ea>\u000e\u0003]Aqa!\u0003$\u0001\b\u0019Y\u0001C\u0004\u0004\u000e\r\u0002\u001daa\u0004\u0015\t\tU8q\u0004\u0005\b\u0007C!\u0003\u0019\u0001B{\u0003\t1\u0018'A\u0006dC:\u001cu\u000e]=ICNDW\u0003BB\u0014\u0007[!ba!\u000b\u0004>\r\r\u0003#BB\fA\r-\u0002cA!\u0004.\u0011Q\u0011qV\u0013!\u0002\u0003\u0005)\u0019\u0001#)\u0013\r5Bj!\r\u00046\re\u0012GB\u0012[7\u000eMB,\r\u0003%)bK\u0014GB\u0012`A\u000e]\u0012-\r\u0003%)bK\u0014GB\u0012Q#\u000em\"+\r\u0003%)bK\u0004\"CB K\u0005\u0005\t9AB!\u0003-)g/\u001b3f]\u000e,G%\r\u001c\u0011\r\u0005%\u0017qZB\u0016\u0011%\u0019)%JA\u0001\u0002\b\u00199%A\u0006fm&$WM\\2fIE:\u0004CBAm\u0003?\u001cY#\u0001\u0007dC:l\u0015\r\u001d,bYV,7/\u0006\u0004\u0004N\re3Q\f\u000b\u0007\u0007\u001f\u001a\u0019g!\u001b\u0011\u0019\t57\u0011KB+\u0007/\u001aYf!\u0019\n\t\rM#q\u001a\u0002\r\u0007\u0006tW*\u00199WC2,Xm\u001d\t\u0005{\u0001\u00199\u0006E\u0002B\u00073\"a!a,'\u0005\u0004!\u0005cA!\u0004^\u001111q\f\u0014C\u0002\u0011\u0013!A\u0016\u001a\u0011\tu\u000211\f\u0005\n\u0007K2\u0013\u0011!a\u0002\u0007O\n1\"\u001a<jI\u0016t7-\u001a\u00132qA1\u0011\u0011ZAh\u00077B\u0011ba\u001b'\u0003\u0003\u0005\u001da!\u001c\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013'\u000f\t\u0007\u00033\fyna\u0017\u0002\u0011M\u001c\u0017\r\\1s\u001f\u001a,Baa\u001d\u0004\u0000U\u00111Q\u000f\t\t\u0005\u001b\u001c9ha\u001f\u0004~%!1\u0011\u0010Bh\u0005!\u00196-\u00197be>3\u0007\u0003B\u001f\u0001\u0007{\u00022!QB@\t\u0019\u0019\ti\nb\u0001\t\n\tA+\u0001\tdC:LE/\u001a:bi\u00164\u0016\r\\;fgV!1qQBJ+\t\u0019I\t\u0005\u0005\u0003N\u000e-5qRBI\u0013\u0011\u0019iIa4\u0003#\r\u000bg\u000e\u0016:bm\u0016\u00148/\u001a,bYV,7\u000f\u0005\u0003>\u0001\rE\u0005cA!\u0004\u0014\u00121\u0011q\u0016\u0015C\u0002\u0011\u000b\u0001dY1o)J\fg/\u001a:tK.+\u0017PV1mk\u0016\u0004\u0016-\u001b:t+\u0011\u0019Ij!*\u0016\u0005\rm\u0005C\u0003Bg\u0007;\u001b\t+a\u0005\u0004$&!1q\u0014Bh\u0005a\u0019\u0015M\u001c+sCZ,'o]3LKf4\u0016\r\\;f!\u0006L'o\u001d\t\u0005{\u0001\u0019\u0019\u000bE\u0002B\u0007K#a!a,*\u0005\u0004!\u0015aC2b]6\u000b\u0007\u000fU1jeN,baa+\u00048\u000emFCBBW\u0007\u007f\u001b)\r\u0005\b\u0003N\u000e=61WA\n\u0007k\u001bIl!0\n\t\rE&q\u001a\u0002\u0014\u0007\u0006tW*\u00199LKf4\u0016\r\\;f!\u0006L'o\u001d\t\u0005{\u0001\u0019)\fE\u0002B\u0007o#a!a,+\u0005\u0004!\u0005cA!\u0004<\u001211q\f\u0016C\u0002\u0011\u0003B!\u0010\u0001\u0004:\"I1\u0011\u0019\u0016\u0002\u0002\u0003\u000f11Y\u0001\fKZLG-\u001a8dK\u0012\u0012\u0004\u0007\u0005\u0004\u0002J\u0006=7\u0011\u0018\u0005\n\u0007\u000fT\u0013\u0011!a\u0002\u0007\u0013\f1\"\u001a<jI\u0016t7-\u001a\u00133cA1\u0011\u0011\\Ap\u0007s\u000bQa\u001d9bG\u0016,Baa4\u0004bRA1\u0011[Br\u0007[\u001c\u0019\u0010\u0005\u0006\u0004T\u000ee7Q\\A\n\u0007?l!a!6\u000b\u0007\r]\u0017'\u0001\u0003nCRD\u0017\u0002BBn\u0007+\u0014A$T;uC\ndWMR5oSR,7i\\8sI&t\u0017\r^3GS\u0016dG\r\u0005\u0003>\u0001\r}\u0007cA!\u0004b\u0012)1i\u000bb\u0001\t\"I1Q]\u0016\u0002\u0002\u0003\u000f1q]\u0001\fKZLG-\u001a8dK\u0012\u0012$\u0007\u0005\u0004\u0004T\u000e%8q\\\u0005\u0005\u0007W\u001c)NA\u0003GS\u0016dG\rC\u0005\u0004p.\n\t\u0011q\u0001\u0004r\u0006YQM^5eK:\u001cW\r\n\u001a4!\u0019\tI-a4\u0004`\"I1Q_\u0016\u0002\u0002\u0003\u000f1q_\u0001\fKZLG-\u001a8dK\u0012\u0012D\u0007\u0005\u0004\u0002Z\u0006}7q\\\u0001\u0005S:LG\u000fK\u0002-\u0007{\u00042aNB\u0000\u0013\r!\t\u0001\u000f\u0002\t]>Lg\u000e\\5oK\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u0011Aq\u0001\t\u0005\t\u0013!y!\u0004\u0002\u0005\f)!AQBAM\u0003\u0011a\u0017M\\4\n\t\u0011EA1\u0002\u0002\u0007\u001f\nTWm\u0019;"
)
public class HashVector implements Vector, Serializable {
   public final OpenAddressHashArray array;

   public static MutableFiniteCoordinateField space(final Field evidence$22, final ClassTag evidence$23, final Zero evidence$24) {
      return HashVector$.MODULE$.space(evidence$22, evidence$23, evidence$24);
   }

   public static CanMapKeyValuePairs canMapPairs(final ClassTag evidence$20, final Zero evidence$21) {
      return HashVector$.MODULE$.canMapPairs(evidence$20, evidence$21);
   }

   public static CanTraverseKeyValuePairs canTraverseKeyValuePairs() {
      return HashVector$.MODULE$.canTraverseKeyValuePairs();
   }

   public static CanTraverseValues canIterateValues() {
      return HashVector$.MODULE$.canIterateValues();
   }

   public static ScalarOf scalarOf() {
      return HashVector$.MODULE$.scalarOf();
   }

   public static CanMapValues canMapValues(final ClassTag evidence$18, final Zero evidence$19) {
      return HashVector$.MODULE$.canMapValues(evidence$18, evidence$19);
   }

   public static CanCopyHashVector canCopyHash(final ClassTag evidence$16, final Zero evidence$17) {
      return HashVector$.MODULE$.canCopyHash(evidence$16, evidence$17);
   }

   public static CanCreateZeros canCreateZeros(final ClassTag evidence$12, final Zero evidence$13) {
      return HashVector$.MODULE$.canCreateZeros(evidence$12, evidence$13);
   }

   public static HashVector tabulate(final int size, final Function1 f, final ClassTag evidence$8, final Zero evidence$9) {
      return HashVector$.MODULE$.tabulate(size, f, evidence$8, evidence$9);
   }

   public static HashVector fill(final int size, final Function0 v, final ClassTag evidence$6, final Zero evidence$7) {
      return HashVector$.MODULE$.fill(size, v, evidence$6, evidence$7);
   }

   public static HashVector zeros(final int size, final ClassTag evidence$1, final Zero evidence$2) {
      return HashVector$.MODULE$.zeros(size, evidence$1, evidence$2);
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

   public boolean equals(final Object p1) {
      return Vector.equals$(this, p1);
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

   public scala.collection.immutable.Vector toScalaVector() {
      return Vector.toScalaVector$(this);
   }

   public Object toArray(final ClassTag cm) {
      return Vector.toArray$(this, cm);
   }

   public double[] toArray$mcD$sp(final ClassTag cm) {
      return Vector.toArray$mcD$sp$(this, cm);
   }

   public float[] toArray$mcF$sp(final ClassTag cm) {
      return Vector.toArray$mcF$sp$(this, cm);
   }

   public int[] toArray$mcI$sp(final ClassTag cm) {
      return Vector.toArray$mcI$sp$(this, cm);
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

   public void foreach(final Function1 fn) {
      VectorLike.foreach$(this, fn);
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

   public void foreach$mcD$sp(final Function1 fn) {
      VectorLike.foreach$mcD$sp$(this, fn);
   }

   public void foreach$mcF$sp(final Function1 fn) {
      VectorLike.foreach$mcF$sp$(this, fn);
   }

   public void foreach$mcI$sp(final Function1 fn) {
      VectorLike.foreach$mcI$sp$(this, fn);
   }

   public void foreach$mcJ$sp(final Function1 fn) {
      VectorLike.foreach$mcJ$sp$(this, fn);
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

   public OpenAddressHashArray array() {
      return this.array;
   }

   public Iterator activeIterator() {
      return this.array().activeIterator();
   }

   public Iterator activeValuesIterator() {
      return this.array().activeValuesIterator();
   }

   public Iterator activeKeysIterator() {
      return this.array().activeKeysIterator();
   }

   public Object apply(final int i) {
      return this.apply$mcI$sp(i);
   }

   public void update(final int i, final Object v) {
      this.array().update(i, v);
   }

   public Object default() {
      return this.array().defaultValue();
   }

   public int activeSize() {
      return this.array().activeSize();
   }

   public int length() {
      return this.array().length();
   }

   public HashVector copy() {
      return new HashVector(this.array().copy());
   }

   public HashVector repr() {
      return this;
   }

   public final int iterableSize() {
      return this.array().iterableSize();
   }

   public Object data() {
      return this.array().data();
   }

   public final int[] index() {
      return this.array().index();
   }

   public final boolean isActive(final int i) {
      return this.array().isActive(i);
   }

   public void clear() {
      this.array().clear();
   }

   public String toString() {
      return this.activeIterator().mkString("HashVector(", ", ", ")");
   }

   public boolean allVisitableIndicesActive() {
      return false;
   }

   public int hashCode() {
      int hash = 47;
      Object dv = this.array().default().value(this.array().zero());

      for(int i = 0; i < this.activeSize(); ++i) {
         if (this.isActive(i)) {
            int ind = this.index()[i];
            Object v = .MODULE$.array_apply(this.data(), i);
            if (!BoxesRunTime.equals(v, dv)) {
               hash = scala.util.hashing.MurmurHash3..MODULE$.mix(hash, Statics.anyHash(v));
               hash = scala.util.hashing.MurmurHash3..MODULE$.mix(hash, ind);
            }
         }
      }

      return scala.util.hashing.MurmurHash3..MODULE$.finalizeHash(hash, this.activeSize());
   }

   public OpenAddressHashArray array$mcD$sp() {
      return this.array();
   }

   public OpenAddressHashArray array$mcF$sp() {
      return this.array();
   }

   public OpenAddressHashArray array$mcI$sp() {
      return this.array();
   }

   public OpenAddressHashArray array$mcJ$sp() {
      return this.array();
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

   public double default$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this.default());
   }

   public float default$mcF$sp() {
      return BoxesRunTime.unboxToFloat(this.default());
   }

   public int default$mcI$sp() {
      return BoxesRunTime.unboxToInt(this.default());
   }

   public long default$mcJ$sp() {
      return BoxesRunTime.unboxToLong(this.default());
   }

   public HashVector copy$mcD$sp() {
      return this.copy();
   }

   public HashVector copy$mcF$sp() {
      return this.copy();
   }

   public HashVector copy$mcI$sp() {
      return this.copy();
   }

   public HashVector copy$mcJ$sp() {
      return this.copy();
   }

   public HashVector repr$mcD$sp() {
      return this.repr();
   }

   public HashVector repr$mcF$sp() {
      return this.repr();
   }

   public HashVector repr$mcI$sp() {
      return this.repr();
   }

   public HashVector repr$mcJ$sp() {
      return this.repr();
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

   public Object apply$mcI$sp(final int i) {
      return this.array().apply(i);
   }

   public boolean specInstance$() {
      return false;
   }

   public HashVector(final OpenAddressHashArray array) {
      this.array = array;
      QuasiTensor.$init$(this);
      ImmutableNumericOps.$init$(this);
      NumericOps.$init$(this);
      TensorLike.$init$(this);
      VectorLike.$init$(this);
      Vector.$init$(this);
      HashVector$.MODULE$.breeze$linalg$HashVector$$init();
   }

   public static class CanCopyHashVector implements CanCopy {
      public final Zero evidence$15;

      public HashVector apply(final HashVector v1) {
         return v1.copy();
      }

      public HashVector apply$mcD$sp(final HashVector v1) {
         return this.apply(v1);
      }

      public HashVector apply$mcF$sp(final HashVector v1) {
         return this.apply(v1);
      }

      public HashVector apply$mcI$sp(final HashVector v1) {
         return this.apply(v1);
      }

      public CanCopyHashVector(final ClassTag evidence$14, final Zero evidence$15) {
         this.evidence$15 = evidence$15;
      }
   }
}
