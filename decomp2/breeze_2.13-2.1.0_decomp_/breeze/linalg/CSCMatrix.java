package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.operators.HasOps$;
import breeze.linalg.support.CanCreateZeros;
import breeze.linalg.support.CanMapKeyValuePairs;
import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.CanTranspose;
import breeze.linalg.support.TensorActive;
import breeze.linalg.support.TensorKeys;
import breeze.linalg.support.TensorPairs;
import breeze.linalg.support.TensorValues;
import breeze.math.EntrywiseMatrixNorms$;
import breeze.math.Field;
import breeze.math.MatrixInnerProduct;
import breeze.math.MutableFiniteCoordinateField;
import breeze.math.MutableFiniteCoordinateField$;
import breeze.math.Semiring;
import breeze.stats.distributions.Rand;
import breeze.storage.Zero;
import breeze.storage.Zero$;
import breeze.util.ArrayBuilder$;
import breeze.util.ArrayUtil$;
import breeze.util.ReflectionUtil$;
import breeze.util.Sorting$;
import breeze.util.Terminal$;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.ArrayBuilder;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011Eg\u0001B,Y\u0001uC!\"!\u000f\u0001\u0005\u0003\u0007I\u0011BA\u001e\u0011)\t\u0019\u0005\u0001BA\u0002\u0013%\u0011Q\t\u0005\u000b\u0003#\u0002!\u0011!Q!\n\u0005u\u0002BCA*\u0001\t\u0015\r\u0011\"\u0001\u0002V!Q\u0011Q\f\u0001\u0003\u0002\u0003\u0006I!a\u0016\t\u0015\u0005}\u0003A!b\u0001\n\u0003\t)\u0006\u0003\u0006\u0002b\u0001\u0011\t\u0011)A\u0005\u0003/B!\"a\u0019\u0001\u0005\u000b\u0007I\u0011AA3\u0011)\tI\u0007\u0001B\u0001B\u0003%\u0011q\r\u0005\f\u0003W\u0002!\u00111A\u0005\u0002a\u000b)\u0006C\u0006\u0002n\u0001\u0011\t\u0019!C\u00011\u0006=\u0004BCA:\u0001\t\u0005\t\u0015)\u0003\u0002X!Q\u0011Q\u000f\u0001\u0003\u0002\u0004%I!!\u001a\t\u0015\u0005]\u0004A!a\u0001\n\u0013\tI\b\u0003\u0006\u0002~\u0001\u0011\t\u0011)Q\u0005\u0003OB!\"a \u0001\u0005\u0007\u0005\u000b1BAA\u0011\u001d\ti\t\u0001C\u0001\u0003\u001fCq!!$\u0001\t\u0003\t\t\u000bC\u0004\u00026\u0002!\t!!\u001a\t\u000f\u0005-\u0006\u0001\"\u0001\u0002<!9\u0011q\u0017\u0001\u0005\u0002\u0005e\u0006bBAb\u0001\u0011\u0005\u0011Q\u0019\u0005\b\u0003\u001f\u0004A\u0011AAi\u0011\u001d\t9\u000e\u0001C\u0001\u00033Dq!a7\u0001\t\u0003\ti\u000eC\u0004\u0002l\u0002!\t!!<\t\u000f\u0005M\b\u0001\"\u0001\u0002v\"9\u0011\u0011 \u0001\u0005\u0002\u0005U\u0003bBA~\u0001\u0011\u0005\u0011Q \u0005\b\u0003\u007f\u0004A\u0011\u0002B\u0001\u0011\u001d\u00119\u0001\u0001C\u0001\u0005\u0013AqAa\u0003\u0001\t\u0003\u0012i\u0001C\u0004\u0003\f\u0001!\tEa\n\t\u0011\t%\u0002\u0001\"\u0001[\u0005WAqA!\u000b\u0001\t\u0003\u0011\t\u0004C\u0004\u0003<\u0001!\t!!@\t\u000f\tu\u0002\u0001\"\u0001\u0003@!I!\u0011\u000b\u0001\u0012\u0002\u0013\u0005!1\u000b\u0005\b\u0005S\u0002A\u0011\tB6\u0011\u001d\u0011)\t\u0001C\u0001\u0005\u000fCqA!#\u0001\t\u0003\u0012YiB\u0004\u0003\u0018bC\tA!'\u0007\r]C\u0006\u0012\u0001BN\u0011\u001d\tii\u000bC\u0001\u0005gCqA!.,\t\u0003\u00119\fC\u0004\u00036.\"\tA!:\t\u000f\rM1\u0006\"\u0001\u0004\u0016\u001911\u0011I\u0016\u0001\u0007\u0007B!\"a\u00151\u0005\u000b\u0007I\u0011AA+\u0011)\ti\u0006\rB\u0001B\u0003%\u0011q\u000b\u0005\u000b\u0003?\u0002$Q1A\u0005\u0002\u0005U\u0003BCA1a\t\u0005\t\u0015!\u0003\u0002X!Q1q\t\u0019\u0003\u0002\u0003\u0006I!a\u0016\t\u0015\r%\u0003GaA!\u0002\u0017\u0019Y\u0005\u0003\u0006\u0004fA\u0012\u0019\u0011)A\u0006\u0007OB!ba\u001d1\u0005\u0007\u0005\u000b1BB;\u0011\u001d\ti\t\rC\u0001\u0007oBqaa#1\t\u0013\u0019i\tC\u0004\u0004\u0010B\"\ta!%\t\u0013\ru\u0005G1A\u0005\n\r}\u0005\u0002CB_a\u0001\u0006Ia!)\t\u0013\r}\u0006G1A\u0005\n\r\u0005\u0007\u0002CBha\u0001\u0006Iaa1\t\u0013\rE\u0007\u00071A\u0005\n\u0005U\u0003\"CBja\u0001\u0007I\u0011BBk\u0011!\u0019I\u000e\rQ!\n\u0005]\u0003bBA}a\u0011\u0005\u0011Q\u000b\u0005\b\u00077\u0004D\u0011ABo\u0011\u001d\u0019\t\u000f\rC\u0001\u0007GDqaa:1\t\u0013\u0019I\u000fC\u0004\u0004vB\"Iaa>\t\u000f\r\u0005\b\u0007\"\u0001\u0004|\"IAQ\u0001\u0019\u0012\u0002\u0013\u0005Aq\u0001\u0005\n\t\u0017\u0001\u0014\u0013!C\u0001\t\u000f9q\u0001\"\u0004,\u0011\u0003!yAB\u0004\u0004B-B\t\u0001\"\u0005\t\u000f\u00055E\n\"\u0001\u0005\u0014!9AQ\u0003'\u0005\u0002\u0011]\u0001\"\u0003C&\u0019F\u0005I\u0011\u0001C'\u0011\u001d!9g\u000bC\u0002\tS:q\u0001\"#,\u0011\u0003!YIB\u0004\u0005\u000e.B\t\u0001b$\t\u000f\u00055%\u000b\"\u0001\u0005\u0012\"9A1\u0013*\u0005\u0004\u0011U\u0005b\u0002C\\W\u0011%\u0011\u0011\u001c\u0005\n\t\u0003\\\u0013\u0011!C\u0005\t\u0007\u0014\u0011bQ*D\u001b\u0006$(/\u001b=\u000b\u0005eS\u0016A\u00027j]\u0006dwMC\u0001\\\u0003\u0019\u0011'/Z3{K\u000e\u0001QC\u00010l'\u001d\u0001q,ZA\u0012\u0003W\u0001\"\u0001Y2\u000e\u0003\u0005T\u0011AY\u0001\u0006g\u000e\fG.Y\u0005\u0003I\u0006\u0014a!\u00118z%\u00164\u0007c\u00014hS6\t\u0001,\u0003\u0002i1\n1Q*\u0019;sSb\u0004\"A[6\r\u0001\u0011IA\u000e\u0001Q\u0001\u0002\u0003\u0015\r!\u001c\u0002\u0002-F\u0011a.\u001d\t\u0003A>L!\u0001]1\u0003\u000f9{G\u000f[5oOB\u0011\u0001M]\u0005\u0003g\u0006\u00141!\u00118zQ%YW\u000f_A\u0003\u0003\u001f\tI\u0002\u0005\u0002am&\u0011q/\u0019\u0002\fgB,7-[1mSj,G-M\u0003$sjd8P\u0004\u0002au&\u001110Y\u0001\u0007\t>,(\r\\32\u000b\u0011j\u00181\u00012\u000f\u0007y\f\u0019!D\u0001\u0000\u0015\r\t\t\u0001X\u0001\u0007yI|w\u000e\u001e \n\u0003\t\f\u0014bIA\u0004\u0003\u0013\ti!a\u0003\u000f\u0007\u0001\fI!C\u0002\u0002\f\u0005\f1!\u00138uc\u0015!S0a\u0001cc%\u0019\u0013\u0011CA\n\u0003/\t)BD\u0002a\u0003'I1!!\u0006b\u0003\u00151En\\1uc\u0015!S0a\u0001cc%\u0019\u00131DA\u000f\u0003C\tyBD\u0002a\u0003;I1!a\bb\u0003\u0011auN\\42\u000b\u0011j\u00181\u00012\u0011\r\u0019\f)#[A\u0015\u0013\r\t9\u0003\u0017\u0002\u000b\u001b\u0006$(/\u001b=MS.,\u0007c\u00014\u0001SB!\u0011QFA\u001a\u001d\ri\u0018qF\u0005\u0004\u0003c\t\u0017a\u00029bG.\fw-Z\u0005\u0005\u0003k\t9D\u0001\u0007TKJL\u0017\r\\5{C\ndWMC\u0002\u00022\u0005\fQa\u00183bi\u0006,\"!!\u0010\u0011\t\u0001\fy$[\u0005\u0004\u0003\u0003\n'!B!se\u0006L\u0018!C0eCR\fw\fJ3r)\u0011\t9%!\u0014\u0011\u0007\u0001\fI%C\u0002\u0002L\u0005\u0014A!\u00168ji\"I\u0011q\n\u0002\u0002\u0002\u0003\u0007\u0011QH\u0001\u0004q\u0012\n\u0014AB0eCR\f\u0007%\u0001\u0003s_^\u001cXCAA,!\r\u0001\u0017\u0011L\u0005\u0004\u00037\n'aA%oi\u0006)!o\\<tA\u0005!1m\u001c7t\u0003\u0015\u0019w\u000e\\:!\u0003\u001d\u0019w\u000e\u001c)ueN,\"!a\u001a\u0011\u000b\u0001\fy$a\u0016\u0002\u0011\r|G\u000e\u0015;sg\u0002\nA!^:fI\u0006AQo]3e?\u0012*\u0017\u000f\u0006\u0003\u0002H\u0005E\u0004\"CA(\u0017\u0005\u0005\t\u0019AA,\u0003\u0015)8/\u001a3!\u0003-y&o\\<J]\u0012L7-Z:\u0002\u001f}\u0013xn^%oI&\u001cWm]0%KF$B!a\u0012\u0002|!I\u0011q\n\b\u0002\u0002\u0003\u0007\u0011qM\u0001\r?J|w/\u00138eS\u000e,7\u000fI\u0001\u000bKZLG-\u001a8dK\u0012\n\u0004#BAB\u0003\u0013KWBAAC\u0015\r\t9IW\u0001\bgR|'/Y4f\u0013\u0011\tY)!\"\u0003\ti+'o\\\u0001\u0007y%t\u0017\u000e\u001e \u0015\u001d\u0005E\u0015QSAL\u00033\u000bY*!(\u0002 R!\u0011\u0011FAJ\u0011\u001d\ty(\u0005a\u0002\u0003\u0003Cq!!\u000f\u0012\u0001\u0004\ti\u0004C\u0004\u0002TE\u0001\r!a\u0016\t\u000f\u0005}\u0013\u00031\u0001\u0002X!9\u00111M\tA\u0002\u0005\u001d\u0004bBA6#\u0001\u0007\u0011q\u000b\u0005\b\u0003k\n\u0002\u0019AA4)1\t\u0019+!+\u0002.\u0006=\u0016\u0011WAZ)\u0011\tI#!*\t\u0013\u0005\u001d&#!AA\u0004\u0005\u0005\u0015AC3wS\u0012,gnY3%e!9\u00111\u0016\nA\u0002\u0005u\u0012\u0001\u00023bi\u0006Dq!a\u0015\u0013\u0001\u0004\t9\u0006C\u0004\u0002`I\u0001\r!a\u0016\t\u000f\u0005\r$\u00031\u0001\u0002h!9\u0011Q\u0017\nA\u0002\u0005\u001d\u0014A\u0003:po&sG-[2fg\u0006)\u0011\r\u001d9msR)\u0011.a/\u0002@\"9\u0011QX\u000bA\u0002\u0005]\u0013a\u0001:po\"9\u0011\u0011Y\u000bA\u0002\u0005]\u0013aA2pY\u00061Q\u000f\u001d3bi\u0016$\u0002\"a\u0012\u0002H\u0006%\u00171\u001a\u0005\b\u0003{3\u0002\u0019AA,\u0011\u001d\t\tM\u0006a\u0001\u0003/Ba!!4\u0017\u0001\u0004I\u0017!\u0001<\u0002\u000fI,7/\u001a:wKR!\u0011qIAj\u0011\u001d\t)n\u0006a\u0001\u0003/\n1A\u001c8{\u0003\u001d\u0019w.\u001c9bGR$\"!a\u0012\u0002%\u0005\u001cG/\u001b<f\u0017\u0016L8/\u0013;fe\u0006$xN]\u000b\u0003\u0003?\u0004b!!\f\u0002b\u0006\u0015\u0018\u0002BAr\u0003o\u0011\u0001\"\u0013;fe\u0006$xN\u001d\t\bA\u0006\u001d\u0018qKA,\u0013\r\tI/\u0019\u0002\u0007)V\u0004H.\u001a\u001a\u0002\u001d\u0005\u001cG/\u001b<f\u0013R,'/\u0019;peV\u0011\u0011q\u001e\t\u0007\u0003[\t\t/!=\u0011\r\u0001\f9/!:j\u0003Q\t7\r^5wKZ\u000bG.^3t\u0013R,'/\u0019;peV\u0011\u0011q\u001f\t\u0006\u0003[\t\t/[\u0001\u000bC\u000e$\u0018N^3TSj,\u0017\u0001\u0002:faJ,\"!!\u000b\u0002\r1|7-\u0019;f)\u0019\t9Fa\u0001\u0003\u0006!9\u0011Q\u0018\u0010A\u0002\u0005]\u0003bBAa=\u0001\u0007\u0011qK\u0001\u0005u\u0016\u0014x.F\u0001j\u0003!!xn\u0015;sS:<GC\u0002B\b\u0005?\u0011\u0019\u0003\u0005\u0003\u0003\u0012\tea\u0002\u0002B\n\u0005+\u0001\"A`1\n\u0007\t]\u0011-\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u00057\u0011iB\u0001\u0004TiJLgn\u001a\u0006\u0004\u0005/\t\u0007\"\u0003B\u0011AA\u0005\t\u0019AA,\u0003!i\u0017\r\u001f'j]\u0016\u001c\b\"\u0003B\u0013AA\u0005\t\u0019AA,\u0003!i\u0017\r_,jIRDGC\u0001B\b\u0003\r)8/\u001a\u000b\u0005\u0003\u000f\u0012i\u0003C\u0004\u00030\t\u0002\r!!\u000b\u0002\r5\fGO]5y))\t9Ea\r\u00036\t]\"\u0011\b\u0005\b\u0003W\u001b\u0003\u0019AA\u001f\u0011\u001d\t\u0019g\ta\u0001\u0003OBq!!.$\u0001\u0004\t9\u0007C\u0004\u0002l\r\u0002\r!a\u0016\u0002\t\r|\u0007/_\u0001\bM2\fG\u000f^3o)\u0011\u0011\tEa\u0012\u0011\t\u0019\u0014\u0019%[\u0005\u0004\u0005\u000bB&\u0001D*qCJ\u001cXMV3di>\u0014\b\"\u0003B%KA\u0005\t\u0019\u0001B&\u0003\u00111\u0018.Z<\u0011\u0007\u0019\u0014i%C\u0002\u0003Pa\u0013AAV5fo\u0006\tb\r\\1ui\u0016tG\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\tU#\u0006\u0002B&\u0005/Z#A!\u0017\u0011\t\tm#QM\u0007\u0003\u0005;RAAa\u0018\u0003b\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0005G\n\u0017AC1o]>$\u0018\r^5p]&!!q\rB/\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000ei>$UM\\:f\u001b\u0006$(/\u001b=\u0015\r\t5$1\u000fBB!\u00111'qN5\n\u0007\tE\u0004LA\u0006EK:\u001cX-T1ue&D\bb\u0002B;O\u0001\u000f!qO\u0001\u0003G6\u0004RA!\u001f\u0003\u0000%l!Aa\u001f\u000b\u0007\tu\u0014-A\u0004sK\u001adWm\u0019;\n\t\t\u0005%1\u0010\u0002\t\u00072\f7o\u001d+bO\"9!qA\u0014A\u0004\u0005\u0005\u0015a\u0002;p\t\u0016t7/Z\u000b\u0003\u0005[\na!Z9vC2\u001cH\u0003\u0002BG\u0005'\u00032\u0001\u0019BH\u0013\r\u0011\t*\u0019\u0002\b\u0005>|G.Z1o\u0011\u0019\u0011)*\u000ba\u0001c\u0006\u0011\u0001/M\u0001\n\u0007N\u001bU*\u0019;sSb\u0004\"AZ\u0016\u0014\r-z&Q\u0014BS!\u00151'q\u0014BR\u0013\r\u0011\t\u000b\u0017\u0002\u0013\u001b\u0006$(/\u001b=D_:\u001cHO];di>\u00148\u000f\u0005\u0002g\u0001A!!q\u0015BY\u001b\t\u0011IK\u0003\u0003\u0003,\n5\u0016AA5p\u0015\t\u0011y+\u0001\u0003kCZ\f\u0017\u0002BA\u001b\u0005S#\"A!'\u0002\u000bi,'o\\:\u0016\t\te&\u0011\u0019\u000b\t\u0005w\u0013iNa8\u0003bR1!Q\u0018Bi\u0005/\u0004BA\u001a\u0001\u0003@B\u0019!N!1\u0005\u00131l\u0003\u0015!A\u0001\u0006\u0004i\u0007&\u0003Bak\n\u0015'\u0011\u001aBgc%\u0019\u0013qAA\u0005\u0005\u000f\fY!M\u0003%{\u0006\r!-M\u0005$\u0003#\t\u0019Ba3\u0002\u0016E*A%`A\u0002EF21%\u001f>\u0003Pn\fT\u0001J?\u0002\u0004\tD\u0011Ba5.\u0003\u0003\u0005\u001dA!6\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$3\u0007\u0005\u0004\u0003z\t}$q\u0018\u0005\n\u00053l\u0013\u0011!a\u0002\u00057\f!\"\u001a<jI\u0016t7-\u001a\u00135!\u0019\t\u0019)!#\u0003@\"9\u00111K\u0017A\u0002\u0005]\u0003bBA0[\u0001\u0007\u0011q\u000b\u0005\b\u0005Gl\u0003\u0019AA,\u00039Ig.\u001b;jC2tuN\u001c>fe>,BAa:\u0003pR1!\u0011^B\b\u0007#!bAa;\u0004\u0004\r%\u0001\u0003\u00024\u0001\u0005[\u00042A\u001bBx\t%ag\u0006)A\u0001\u0002\u000b\u0007Q\u000eK\u0006\u0003pV\u0014\u0019Pa>\u0003|\n}\u0018GB\u0012zu\nU80M\u0003%{\u0006\r!-M\u0005$\u0003\u000f\tIA!?\u0002\fE*A%`A\u0002EFJ1%!\u0005\u0002\u0014\tu\u0018QC\u0019\u0006Iu\f\u0019AY\u0019\nG\u0005m\u0011QDB\u0001\u0003?\tT\u0001J?\u0002\u0004\tD\u0011b!\u0002/\u0003\u0003\u0005\u001daa\u0002\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$S\u0007\u0005\u0004\u0003z\t}$Q\u001e\u0005\n\u0007\u0017q\u0013\u0011!a\u0002\u0007\u001b\t!\"\u001a<jI\u0016t7-\u001a\u00137!\u0019\t\u0019)!#\u0003n\"9\u00111\u000b\u0018A\u0002\u0005]\u0003bBA0]\u0001\u0007\u0011qK\u0001\u0007GJ,\u0017\r^3\u0016\t\r]1q\u0004\u000b\t\u00073\u0019Ida\u000f\u0004>Q!11DB\u001a!\u00111\u0007a!\b\u0011\u0007)\u001cy\u0002B\u0005m_\u0001\u0006\t\u0011!b\u0001[\"Z1qD;\u0004$\r\u001d21FB\u0018c\u0019\u0019\u0013P_B\u0013wF*A%`A\u0002EFJ1%a\u0002\u0002\n\r%\u00121B\u0019\u0006Iu\f\u0019AY\u0019\nG\u0005E\u00111CB\u0017\u0003+\tT\u0001J?\u0002\u0004\t\f\u0014bIA\u000e\u0003;\u0019\t$a\b2\u000b\u0011j\u00181\u00012\t\u0013\rUr&!AA\u0004\r]\u0012AC3wS\u0012,gnY3%oA1\u00111QAE\u0007;Aq!a\u00150\u0001\u0004\t9\u0006C\u0004\u0002`=\u0002\r!a\u0016\t\u000f\u0005-v\u00061\u0001\u0004@A)\u0001-a\u0010\u0004\u001e\t9!)^5mI\u0016\u0014X\u0003BB#\u0007\u001f\u001a\"\u0001M0\u0002\u000f%t\u0017\u000e\u001e(ou\u0006QQM^5eK:\u001cW\r\n\u001d\u0011\r\te$qPB'!\rQ7q\n\u0003\u000b\u0007#\u0002\u0004\u0015!A\u0001\u0006\u0004i'!\u0001+)\u0017\r=So!\u0016\u0004Z\ru3\u0011M\u0019\u0007GeT8qK>2\u000b\u0011j\u00181\u000122\u0013\r\n9!!\u0003\u0004\\\u0005-\u0011'\u0002\u0013~\u0003\u0007\u0011\u0017'C\u0012\u0002\u0012\u0005M1qLA\u000bc\u0015!S0a\u0001cc%\u0019\u00131DA\u000f\u0007G\ny\"M\u0003%{\u0006\r!-\u0001\u0006fm&$WM\\2fIe\u0002ba!\u001b\u0004p\r5SBAB6\u0015\r\u0019iGW\u0001\u0005[\u0006$\b.\u0003\u0003\u0004r\r-$\u0001C*f[&\u0014\u0018N\\4\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007\r\t\u0007\u0003\u0007\u000bIi!\u0014\u0015\u0011\re4QQBD\u0007\u0013#\u0002ba\u001f\u0004\u0000\r\u000551\u0011\t\u0006\u0007{\u00024QJ\u0007\u0002W!91\u0011J\u001dA\u0004\r-\u0003bBB3s\u0001\u000f1q\r\u0005\b\u0007gJ\u00049AB;\u0011\u001d\t\u0019&\u000fa\u0001\u0003/Bq!a\u0018:\u0001\u0004\t9\u0006C\u0005\u0004He\u0002\n\u00111\u0001\u0002X\u0005!!/\u001b8h+\t\u00199'A\u0002bI\u0012$\u0002\"a\u0012\u0004\u0014\u000e]51\u0014\u0005\b\u0007+[\u0004\u0019AA,\u0003\u0005\u0011\bbBBMw\u0001\u0007\u0011qK\u0001\u0002G\"9\u0011QZ\u001eA\u0002\r5\u0013aB5oI&\u001cWm]\u000b\u0003\u0007C\u0003Baa)\u00048:!1QUBY\u001d\u0011\u00199k!,\u000e\u0005\r%&bABVC\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\r=6\u0011V\u0001\b[V$\u0018M\u00197f\u0013\u0011\u0019\u0019l!.\u0002\u0019\u0005\u0013(/Y=Ck&dG-\u001a:\u000b\t\r=6\u0011V\u0005\u0005\u0007s\u001bYL\u0001\u0004pM2{gn\u001a\u0006\u0005\u0007g\u001b),\u0001\u0005j]\u0012L7-Z:!\u0003\t18/\u0006\u0002\u0004DB11QYBf\u0007\u001bj!aa2\u000b\u0007\r%',\u0001\u0003vi&d\u0017\u0002BBg\u0007\u000f\u0014A\"\u0011:sCf\u0014U/\u001b7eKJ\f1A^:!\u0003!qW/\\!eI\u0016$\u0017\u0001\u00048v[\u0006#G-\u001a3`I\u0015\fH\u0003BA$\u0007/D\u0011\"a\u0014B\u0003\u0003\u0005\r!a\u0016\u0002\u00139,X.\u00113eK\u0012\u0004\u0013\u0001C:ju\u0016D\u0015N\u001c;\u0015\t\u0005\u001d3q\u001c\u0005\b\u0003+$\u0005\u0019AA,\u0003\u0019\u0011Xm];miV\u00111Q\u001d\t\u0005M\u0002\u0019i%\u0001\u0007s_^4%o\\7J]\u0012,\u0007\u0010\u0006\u0003\u0002X\r-\bbBBw\r\u0002\u00071q^\u0001\u0004S\u0012D\bc\u00011\u0004r&\u001911_1\u0003\t1{gnZ\u0001\rG>dgI]8n\u0013:$W\r\u001f\u000b\u0005\u0003/\u001aI\u0010C\u0004\u0004n\u001e\u0003\raa<\u0015\r\r\u00158Q C\u0001\u0011%\u0019y\u0010\u0013I\u0001\u0002\u0004\u0011i)A\tlKf\u001c\u0018\t\u001c:fC\u0012LXK\\5rk\u0016D\u0011\u0002b\u0001I!\u0003\u0005\rA!$\u0002#-,\u0017p]!me\u0016\fG-_*peR,G-\u0001\tsKN,H\u000e\u001e\u0013eK\u001a\fW\u000f\u001c;%cU\u0011A\u0011\u0002\u0016\u0005\u0005\u001b\u00139&\u0001\tsKN,H\u000e\u001e\u0013eK\u001a\fW\u000f\u001c;%e\u00059!)^5mI\u0016\u0014\bcAB?\u0019N\u0011Aj\u0018\u000b\u0003\t\u001f\t!B\u001a:p[6\u000bGO]5y+\u0011!I\u0002\"\t\u0015\t\u0011mAq\t\u000b\t\t;!)\u0004b\u000f\u0005BA)1Q\u0010\u0019\u0005 A\u0019!\u000e\"\t\u0005\u0015\rEc\n)A\u0001\u0002\u000b\u0007Q\u000eK\u0006\u0005\"U$)\u0003\"\u000b\u0005.\u0011E\u0012GB\u0012zu\u0012\u001d20M\u0003%{\u0006\r!-M\u0005$\u0003\u000f\tI\u0001b\u000b\u0002\fE*A%`A\u0002EFJ1%!\u0005\u0002\u0014\u0011=\u0012QC\u0019\u0006Iu\f\u0019AY\u0019\nG\u0005m\u0011Q\u0004C\u001a\u0003?\tT\u0001J?\u0002\u0004\tD\u0011\u0002b\u000eO\u0003\u0003\u0005\u001d\u0001\"\u000f\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013'\r\t\u0007\u0005s\u0012y\bb\b\t\u0013\u0011ub*!AA\u0004\u0011}\u0012aC3wS\u0012,gnY3%cI\u0002ba!\u001b\u0004p\u0011}\u0001\"\u0003C\"\u001d\u0006\u0005\t9\u0001C#\u0003-)g/\u001b3f]\u000e,G%M\u001a\u0011\r\u0005\r\u0015\u0011\u0012C\u0010\u0011\u001d\u0011yC\u0014a\u0001\t\u0013\u0002BA\u001a\u0001\u0005 \u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIM*B\u0001b\u0014\u0005TU\u0011A\u0011\u000b\u0016\u0005\u0003/\u00129\u0006\u0002\u0006\u0004R=\u0003\u000b\u0011!AC\u00025D3\u0002b\u0015v\t/\"Y\u0006b\u0018\u0005dE21%\u001f>\u0005Zm\fT\u0001J?\u0002\u0004\t\f\u0014bIA\u0004\u0003\u0013!i&a\u00032\u000b\u0011j\u00181\u000122\u0013\r\n\t\"a\u0005\u0005b\u0005U\u0011'\u0002\u0013~\u0003\u0007\u0011\u0017'C\u0012\u0002\u001c\u0005uAQMA\u0010c\u0015!S0a\u0001c\u0003\u0019\u0019\u0017M\u001c#j[V!A1\u000eCC+\t!i\u0007\u0005\u0005\u0005p\u0011UD\u0011QAs\u001d\r1G\u0011O\u0005\u0004\tgB\u0016a\u00013j[&!Aq\u000fC=\u0005\u0011IU\u000e\u001d7\n\t\u0011mDQ\u0010\u0002\u0006+\u001a+hn\u0019\u0006\u0004\t\u007fR\u0016aB4f]\u0016\u0014\u0018n\u0019\t\u0005M\u0002!\u0019\tE\u0002k\t\u000b#a\u0001b\"Q\u0005\u0004i'!A#\u0002G\u0019\u0013xNY3oSV\u001c\u0018J\u001c8feB\u0013x\u000eZ;di\u000e\u001b6)T1ue&D8\u000b]1dKB\u00191Q\u0010*\u0003G\u0019\u0013xNY3oSV\u001c\u0018J\u001c8feB\u0013x\u000eZ;di\u000e\u001b6)T1ue&D8\u000b]1dKN\u0011!k\u0018\u000b\u0003\t\u0017\u000bQa\u001d9bG\u0016,B\u0001b&\u0005$R1A\u0011\u0014CT\tc\u0003\"b!\u001b\u0005\u001c\u0012}\u0015Q\u001dCQ\u0013\u0011!ija\u001b\u000395+H/\u00192mK\u001aKg.\u001b;f\u0007>|'\u000fZ5oCR,g)[3mIB!a\r\u0001CQ!\rQG1\u0015\u0003\u0007\tK#&\u0019A7\u0003\u0003MC\u0011\u0002\"+U\u0003\u0003\u0005\u001d\u0001b+\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007\u000e\t\u0007\u0007S\"i\u000b\")\n\t\u0011=61\u000e\u0002\u0006\r&,G\u000e\u001a\u0005\n\tg#\u0016\u0011!a\u0002\tk\u000b1\"\u001a<jI\u0016t7-\u001a\u00132kA1!\u0011\u0010B@\tC\u000bA!\u001b8ji\"\u001aQ\u000bb/\u0011\u0007\u0001$i,C\u0002\u0005@\u0006\u0014\u0001B\\8j]2Lg.Z\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\t\u000b\u0004B\u0001b2\u0005N6\u0011A\u0011\u001a\u0006\u0005\t\u0017\u0014i+\u0001\u0003mC:<\u0017\u0002\u0002Ch\t\u0013\u0014aa\u00142kK\u000e$\b"
)
public class CSCMatrix implements Matrix, Serializable {
   public Object _data;
   private final int rows;
   private final int cols;
   private final int[] colPtrs;
   private int used;
   private int[] breeze$linalg$CSCMatrix$$_rowIndices;
   public final Zero evidence$1;

   public static UFunc.UImpl canDim() {
      return CSCMatrix$.MODULE$.canDim();
   }

   public static CSCMatrix create(final int rows, final int cols, final Object data, final Zero evidence$7) {
      return CSCMatrix$.MODULE$.create(rows, cols, data, evidence$7);
   }

   public static CSCMatrix zeros(final int rows, final int cols, final ClassTag evidence$5, final Zero evidence$6) {
      return CSCMatrix$.MODULE$.zeros(rows, cols, evidence$5, evidence$6);
   }

   public static CSCMatrix zeros(final int rows, final int cols, final int initialNonzero, final ClassTag evidence$3, final Zero evidence$4) {
      return CSCMatrix$.MODULE$.zeros(rows, cols, initialNonzero, evidence$3, evidence$4);
   }

   public static CanCreateZeros canCreateZeros(final ClassTag evidence$19, final Zero evidence$20) {
      return CSCMatrix$.MODULE$.canCreateZeros(evidence$19, evidence$20);
   }

   public static Rand rand$default$3() {
      return CSCMatrix$.MODULE$.rand$default$3();
   }

   public static Matrix rand(final int rows, final int cols, final Rand rand, final ClassTag evidence$17, final Zero evidence$18) {
      return CSCMatrix$.MODULE$.rand(rows, cols, rand, evidence$17, evidence$18);
   }

   public static Matrix tabulate(final int rows, final int cols, final Function2 f, final ClassTag evidence$15, final Zero evidence$16) {
      return CSCMatrix$.MODULE$.tabulate(rows, cols, f, evidence$15, evidence$16);
   }

   public static Matrix fill(final int rows, final int cols, final Function0 v, final ClassTag evidence$13, final Zero evidence$14) {
      return CSCMatrix$.MODULE$.fill(rows, cols, v, evidence$13, evidence$14);
   }

   public static Matrix ones(final int rows, final int cols, final ClassTag evidence$10, final Zero evidence$11, final Semiring evidence$12) {
      return CSCMatrix$.MODULE$.ones(rows, cols, evidence$10, evidence$11, evidence$12);
   }

   public Object apply(final Tuple2 i) {
      return Matrix.apply$(this, i);
   }

   public double apply$mcD$sp(final Tuple2 i) {
      return Matrix.apply$mcD$sp$(this, i);
   }

   public float apply$mcF$sp(final Tuple2 i) {
      return Matrix.apply$mcF$sp$(this, i);
   }

   public int apply$mcI$sp(final Tuple2 i) {
      return Matrix.apply$mcI$sp$(this, i);
   }

   public long apply$mcJ$sp(final Tuple2 i) {
      return Matrix.apply$mcJ$sp$(this, i);
   }

   public void update(final Tuple2 i, final Object e) {
      Matrix.update$(this, i, e);
   }

   public void update$mcD$sp(final Tuple2 i, final double e) {
      Matrix.update$mcD$sp$(this, i, e);
   }

   public void update$mcF$sp(final Tuple2 i, final float e) {
      Matrix.update$mcF$sp$(this, i, e);
   }

   public void update$mcI$sp(final Tuple2 i, final int e) {
      Matrix.update$mcI$sp$(this, i, e);
   }

   public void update$mcJ$sp(final Tuple2 i, final long e) {
      Matrix.update$mcJ$sp$(this, i, e);
   }

   public int size() {
      return Matrix.size$(this);
   }

   public Set keySet() {
      return Matrix.keySet$(this);
   }

   public Iterator iterator() {
      return Matrix.iterator$(this);
   }

   public Iterator valuesIterator() {
      return Matrix.valuesIterator$(this);
   }

   public Iterator keysIterator() {
      return Matrix.keysIterator$(this);
   }

   public int toString$default$1() {
      return Matrix.toString$default$1$(this);
   }

   public int toString$default$2() {
      return Matrix.toString$default$2$(this);
   }

   public Object map(final Function1 fn, final CanMapValues canMapValues) {
      return MatrixLike.map$(this, fn, canMapValues);
   }

   public Object map$mcD$sp(final Function1 fn, final CanMapValues canMapValues) {
      return MatrixLike.map$mcD$sp$(this, fn, canMapValues);
   }

   public Object map$mcF$sp(final Function1 fn, final CanMapValues canMapValues) {
      return MatrixLike.map$mcF$sp$(this, fn, canMapValues);
   }

   public Object map$mcI$sp(final Function1 fn, final CanMapValues canMapValues) {
      return MatrixLike.map$mcI$sp$(this, fn, canMapValues);
   }

   public Object map$mcJ$sp(final Function1 fn, final CanMapValues canMapValues) {
      return MatrixLike.map$mcJ$sp$(this, fn, canMapValues);
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

   public boolean forall(final Function1 fn) {
      return TensorLike.forall$(this, (Function1)fn);
   }

   public boolean forall$mcD$sp(final Function1 fn) {
      return TensorLike.forall$mcD$sp$(this, fn);
   }

   public boolean forall$mcF$sp(final Function1 fn) {
      return TensorLike.forall$mcF$sp$(this, fn);
   }

   public boolean forall$mcI$sp(final Function1 fn) {
      return TensorLike.forall$mcI$sp$(this, fn);
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

   public int hashCode() {
      return QuasiTensor.hashCode$(this);
   }

   public Object _data() {
      return this._data;
   }

   public void _data_$eq(final Object x$1) {
      this._data = x$1;
   }

   public int rows() {
      return this.rows;
   }

   public int cols() {
      return this.cols;
   }

   public int[] colPtrs() {
      return this.colPtrs;
   }

   public int used() {
      return this.used;
   }

   public void used_$eq(final int x$1) {
      this.used = x$1;
   }

   public int[] breeze$linalg$CSCMatrix$$_rowIndices() {
      return this.breeze$linalg$CSCMatrix$$_rowIndices;
   }

   public void breeze$linalg$CSCMatrix$$_rowIndices_$eq(final int[] x$1) {
      this.breeze$linalg$CSCMatrix$$_rowIndices = x$1;
   }

   public int[] rowIndices() {
      return this.breeze$linalg$CSCMatrix$$_rowIndices();
   }

   public Object data() {
      return this._data();
   }

   public Object apply(final int row, final int col) {
      if (row < this.rows() && col < this.cols() && row >= 0 && col >= 0) {
         int ind = this.breeze$linalg$CSCMatrix$$locate(row, col);
         return ind < 0 ? this.zero() : .MODULE$.array_apply(this.data(), ind);
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public void update(final int row, final int col, final Object v) {
      if (row < this.rows() && col < this.cols() && row >= 0 && col >= 0) {
         int ind = this.breeze$linalg$CSCMatrix$$locate(row, col);
         if (ind >= 0) {
            .MODULE$.array_update(this.data(), ind, v);
         } else if (!BoxesRunTime.equals(v, this.zero())) {
            int insertPos = ~ind;
            this.used_$eq(this.used() + 1);
            if (this.used() > .MODULE$.array_length(this.data())) {
               int newLength = .MODULE$.array_length(this.data()) == 0 ? 4 : (.MODULE$.array_length(this.data()) < 1024 ? .MODULE$.array_length(this.data()) * 2 : (.MODULE$.array_length(this.data()) < 2048 ? .MODULE$.array_length(this.data()) + 1024 : (.MODULE$.array_length(this.data()) < 4096 ? .MODULE$.array_length(this.data()) + 2048 : (.MODULE$.array_length(this.data()) < 8192 ? .MODULE$.array_length(this.data()) + 4096 : (.MODULE$.array_length(this.data()) < 16384 ? .MODULE$.array_length(this.data()) + 8192 : .MODULE$.array_length(this.data()) + 16384)))));
               int[] newIndex = Arrays.copyOf(this.rowIndices(), newLength);
               Object newData = ArrayUtil$.MODULE$.copyOf(this.data(), newLength);
               System.arraycopy(this.breeze$linalg$CSCMatrix$$_rowIndices(), insertPos, newIndex, insertPos + 1, this.used() - insertPos - 1);
               System.arraycopy(this.data(), insertPos, newData, insertPos + 1, this.used() - insertPos - 1);
               this.breeze$linalg$CSCMatrix$$_rowIndices_$eq(newIndex);
               this._data_$eq(newData);
            } else if (this.used() - insertPos > 1) {
               System.arraycopy(this.breeze$linalg$CSCMatrix$$_rowIndices(), insertPos, this.breeze$linalg$CSCMatrix$$_rowIndices(), insertPos + 1, this.used() - insertPos - 1);
               System.arraycopy(this.data(), insertPos, this.data(), insertPos + 1, this.used() - insertPos - 1);
            }

            this.rowIndices()[insertPos] = row;
            .MODULE$.array_update(this.data(), insertPos, v);
            int index$macro$2 = col + 1;

            for(int end$macro$3 = this.cols(); index$macro$2 <= end$macro$3; ++index$macro$2) {
               ++this.colPtrs()[index$macro$2];
            }
         }

      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public void reserve(final int nnz) {
      if (nnz >= this.used() && nnz != this.rowIndices().length) {
         this.breeze$linalg$CSCMatrix$$_rowIndices_$eq(Arrays.copyOf(this.rowIndices(), nnz));
         this._data_$eq(ArrayUtil$.MODULE$.copyOf(this.data(), nnz));
      }

   }

   public void compact() {
      this.reserve(this.used());
   }

   public Iterator activeKeysIterator() {
      return scala.package..MODULE$.Iterator().range(0, this.cols()).flatMap((c) -> $anonfun$activeKeysIterator$1(this, BoxesRunTime.unboxToInt(c)));
   }

   public Iterator activeIterator() {
      return scala.package..MODULE$.Iterator().range(0, this.cols()).flatMap((c) -> $anonfun$activeIterator$1(this, BoxesRunTime.unboxToInt(c)));
   }

   public Iterator activeValuesIterator() {
      return scala.collection.ArrayOps..MODULE$.iterator$extension(scala.Predef..MODULE$.genericArrayOps(this.data())).take(this.used());
   }

   public int activeSize() {
      return this.used();
   }

   public CSCMatrix repr() {
      return this;
   }

   public int breeze$linalg$CSCMatrix$$locate(final int row, final int col) {
      int start = this.colPtrs()[col];
      int end = this.colPtrs()[col + 1];
      return Arrays.binarySearch(this.rowIndices(), start, end, row);
   }

   public Object zero() {
      return ((Zero)scala.Predef..MODULE$.implicitly(this.evidence$1)).zero();
   }

   public String toString(final int maxLines, final int maxWidth) {
      StringBuilder buf = new StringBuilder();
      buf.$plus$plus$eq(scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("%d x %d CSCMatrix"), .MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(this.rows()), BoxesRunTime.boxToInteger(this.cols())})));
      this.activeIterator().take(maxLines - 1).foreach((x0$1) -> {
         if (x0$1 != null) {
            Tuple2 var4 = (Tuple2)x0$1._1();
            Object v = x0$1._2();
            if (var4 != null) {
               int r = var4._1$mcI$sp();
               int c = var4._2$mcI$sp();
               buf.$plus$eq(BoxesRunTime.boxToCharacter('\n'));
               buf.$plus$plus$eq(scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("(%d,%d) "), .MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(r), BoxesRunTime.boxToInteger(c)})));
               StringBuilder var2 = buf.$plus$plus$eq(v.toString());
               return var2;
            }
         }

         throw new MatchError(x0$1);
      });
      return buf.toString();
   }

   public String toString() {
      return this.toString(Terminal$.MODULE$.terminalHeight() - 3, this.toString$default$2());
   }

   public void use(final CSCMatrix matrix) {
      this.use(matrix.data(), matrix.colPtrs(), matrix.rowIndices(), matrix.used());
   }

   public void use(final Object data, final int[] colPtrs, final int[] rowIndices, final int used) {
      int left$macro$1 = colPtrs.length;
      int right$macro$2 = this.colPtrs().length;
      if (left$macro$1 != right$macro$2) {
         throw new IllegalArgumentException((new java.lang.StringBuilder(64)).append("requirement failed: ").append("colPtrs.length == this.colPtrs.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
      } else {
         boolean cond$macro$3 = used >= 0;
         if (!cond$macro$3) {
            throw new IllegalArgumentException("requirement failed: used.>=(0)");
         } else {
            boolean cond$macro$4 = .MODULE$.array_length(data) >= used;
            if (!cond$macro$4) {
               throw new IllegalArgumentException("requirement failed: data.length.>=(used)");
            } else {
               boolean cond$macro$5 = rowIndices.length >= used;
               if (!cond$macro$5) {
                  throw new IllegalArgumentException("requirement failed: rowIndices.length.>=(used)");
               } else {
                  this._data_$eq(data);
                  System.arraycopy(colPtrs, 0, this.colPtrs(), 0, colPtrs.length);
                  this.breeze$linalg$CSCMatrix$$_rowIndices_$eq(rowIndices);
                  this.used_$eq(used);
               }
            }
         }
      }
   }

   public CSCMatrix copy() {
      return new CSCMatrix(ArrayUtil$.MODULE$.copyOf(this._data(), this.activeSize()), this.rows(), this.cols(), (int[])this.colPtrs().clone(), this.activeSize(), (int[])this.breeze$linalg$CSCMatrix$$_rowIndices().clone(), this.evidence$1);
   }

   public SparseVector flatten(final View view) {
      SparseVector var2;
      if (View.Require$.MODULE$.equals(view)) {
         int[] indices = new int[.MODULE$.array_length(this.data())];
         int j = 0;

         for(int ind = 0; j < this.cols(); ++j) {
            for(int ip = this.colPtrs()[j]; ip < this.colPtrs()[j + 1]; ++ind) {
               int i = this.rowIndices()[ip];
               indices[ind] = i * this.rows() + j;
               ++ip;
            }
         }

         var2 = new SparseVector(indices, this.data(), this.activeSize(), this.rows() * this.cols(), this.evidence$1);
      } else if (View.Copy$.MODULE$.equals(view)) {
         ClassTag man = ReflectionUtil$.MODULE$.elemClassTagFromArray(this.data());
         SparseVector sv = SparseVector$.MODULE$.zeros(this.rows() * this.cols(), man, this.evidence$1);

         for(int j = 0; j < this.cols(); ++j) {
            for(int ip = this.colPtrs()[j]; ip < this.colPtrs()[j + 1]; ++ip) {
               int i = this.rowIndices()[ip];
               sv.update(i * this.cols() + j, .MODULE$.array_apply(this.data(), ip));
            }
         }

         var2 = sv;
      } else {
         if (!View.Prefer$.MODULE$.equals(view)) {
            throw new MatchError(view);
         }

         var2 = this.flatten(View.Require$.MODULE$);
      }

      return var2;
   }

   public View flatten$default$1() {
      return View.Copy$.MODULE$;
   }

   public DenseMatrix toDenseMatrix(final ClassTag cm, final Zero zero) {
      return this.toDense();
   }

   public DenseMatrix toDense() {
      ClassTag ctg = ReflectionUtil$.MODULE$.elemClassTagFromArray(this.data());
      DenseMatrix res = DenseMatrix$.MODULE$.zeros(this.rows(), this.cols(), ctg, this.evidence$1);

      for(int i = 0; i < this.cols(); ++i) {
         for(int j = this.colPtrs()[i]; j < this.colPtrs()[i + 1]; ++j) {
            res.update(this.rowIndices()[j], i, .MODULE$.array_apply(this.data(), j));
         }
      }

      return res;
   }

   public boolean equals(final Object p1) {
      if (p1 instanceof CSCMatrix) {
         CSCMatrix var4 = (CSCMatrix)p1;
         if (this.rows() == var4.rows() && this.cols() == var4.cols()) {
            Iterator xIter = this.activeIterator();
            Iterator yIter = var4.activeIterator();

            while(true) {
               if (xIter.hasNext() && yIter.hasNext()) {
                  Tuple2 xkeyval = (Tuple2)xIter.next();

                  Tuple2 ykeyval;
                  for(ykeyval = (Tuple2)yIter.next(); BoxesRunTime.equals(xkeyval._2(), BoxesRunTime.boxToInteger(0)) && xIter.hasNext(); xkeyval = (Tuple2)xIter.next()) {
                  }

                  while(BoxesRunTime.equals(ykeyval._2(), BoxesRunTime.boxToInteger(0)) && yIter.hasNext()) {
                     ykeyval = (Tuple2)yIter.next();
                  }

                  if (xkeyval == null) {
                     if (ykeyval == null) {
                        continue;
                     }
                  } else if (xkeyval.equals(ykeyval)) {
                     continue;
                  }

                  return false;
               }

               if (xIter.hasNext() && !yIter.hasNext()) {
                  while(xIter.hasNext()) {
                     if (!BoxesRunTime.equals(((Tuple2)xIter.next())._2(), BoxesRunTime.boxToInteger(0))) {
                        return false;
                     }
                  }
               }

               if (!xIter.hasNext() && yIter.hasNext()) {
                  while(yIter.hasNext()) {
                     if (!BoxesRunTime.equals(((Tuple2)yIter.next())._2(), BoxesRunTime.boxToInteger(0))) {
                        return false;
                     }
                  }
               }

               return true;
            }
         } else {
            return false;
         }
      } else if (!(p1 instanceof Matrix)) {
         return false;
      } else {
         boolean var10000;
         label121: {
            Matrix var10 = (Matrix)p1;
            if (var10 == null) {
               if (this == null) {
                  break label121;
               }
            } else if (var10.equals(this)) {
               break label121;
            }

            var10000 = false;
            return var10000;
         }

         var10000 = true;
         return var10000;
      }
   }

   public double[] _data$mcD$sp() {
      return (double[])this._data();
   }

   public float[] _data$mcF$sp() {
      return (float[])this._data();
   }

   public int[] _data$mcI$sp() {
      return (int[])this._data();
   }

   public long[] _data$mcJ$sp() {
      return (long[])this._data();
   }

   public void _data$mcD$sp_$eq(final double[] x$1) {
      this._data_$eq(x$1);
   }

   public void _data$mcF$sp_$eq(final float[] x$1) {
      this._data_$eq(x$1);
   }

   public void _data$mcI$sp_$eq(final int[] x$1) {
      this._data_$eq(x$1);
   }

   public void _data$mcJ$sp_$eq(final long[] x$1) {
      this._data_$eq(x$1);
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

   public double apply$mcD$sp(final int row, final int col) {
      return BoxesRunTime.unboxToDouble(this.apply(row, col));
   }

   public float apply$mcF$sp(final int row, final int col) {
      return BoxesRunTime.unboxToFloat(this.apply(row, col));
   }

   public int apply$mcI$sp(final int row, final int col) {
      return BoxesRunTime.unboxToInt(this.apply(row, col));
   }

   public long apply$mcJ$sp(final int row, final int col) {
      return BoxesRunTime.unboxToLong(this.apply(row, col));
   }

   public void update$mcD$sp(final int row, final int col, final double v) {
      this.update(row, col, BoxesRunTime.boxToDouble(v));
   }

   public void update$mcF$sp(final int row, final int col, final float v) {
      this.update(row, col, BoxesRunTime.boxToFloat(v));
   }

   public void update$mcI$sp(final int row, final int col, final int v) {
      this.update(row, col, BoxesRunTime.boxToInteger(v));
   }

   public void update$mcJ$sp(final int row, final int col, final long v) {
      this.update(row, col, BoxesRunTime.boxToLong(v));
   }

   public CSCMatrix repr$mcD$sp() {
      return this.repr();
   }

   public CSCMatrix repr$mcF$sp() {
      return this.repr();
   }

   public CSCMatrix repr$mcI$sp() {
      return this.repr();
   }

   public CSCMatrix repr$mcJ$sp() {
      return this.repr();
   }

   public double zero$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this.zero());
   }

   public float zero$mcF$sp() {
      return BoxesRunTime.unboxToFloat(this.zero());
   }

   public int zero$mcI$sp() {
      return BoxesRunTime.unboxToInt(this.zero());
   }

   public long zero$mcJ$sp() {
      return BoxesRunTime.unboxToLong(this.zero());
   }

   public void use$mcD$sp(final CSCMatrix matrix) {
      this.use(matrix);
   }

   public void use$mcF$sp(final CSCMatrix matrix) {
      this.use(matrix);
   }

   public void use$mcI$sp(final CSCMatrix matrix) {
      this.use(matrix);
   }

   public void use$mcJ$sp(final CSCMatrix matrix) {
      this.use(matrix);
   }

   public void use$mcD$sp(final double[] data, final int[] colPtrs, final int[] rowIndices, final int used) {
      this.use(data, colPtrs, rowIndices, used);
   }

   public void use$mcF$sp(final float[] data, final int[] colPtrs, final int[] rowIndices, final int used) {
      this.use(data, colPtrs, rowIndices, used);
   }

   public void use$mcI$sp(final int[] data, final int[] colPtrs, final int[] rowIndices, final int used) {
      this.use(data, colPtrs, rowIndices, used);
   }

   public void use$mcJ$sp(final long[] data, final int[] colPtrs, final int[] rowIndices, final int used) {
      this.use(data, colPtrs, rowIndices, used);
   }

   public CSCMatrix copy$mcD$sp() {
      return this.copy();
   }

   public CSCMatrix copy$mcF$sp() {
      return this.copy();
   }

   public CSCMatrix copy$mcI$sp() {
      return this.copy();
   }

   public CSCMatrix copy$mcJ$sp() {
      return this.copy();
   }

   public SparseVector flatten$mcD$sp(final View view) {
      return this.flatten(view);
   }

   public SparseVector flatten$mcF$sp(final View view) {
      return this.flatten(view);
   }

   public SparseVector flatten$mcI$sp(final View view) {
      return this.flatten(view);
   }

   public SparseVector flatten$mcJ$sp(final View view) {
      return this.flatten(view);
   }

   public DenseMatrix toDenseMatrix$mcD$sp(final ClassTag cm, final Zero zero) {
      return this.toDenseMatrix(cm, zero);
   }

   public DenseMatrix toDenseMatrix$mcF$sp(final ClassTag cm, final Zero zero) {
      return this.toDenseMatrix(cm, zero);
   }

   public DenseMatrix toDenseMatrix$mcI$sp(final ClassTag cm, final Zero zero) {
      return this.toDenseMatrix(cm, zero);
   }

   public DenseMatrix toDenseMatrix$mcJ$sp(final ClassTag cm, final Zero zero) {
      return this.toDenseMatrix(cm, zero);
   }

   public DenseMatrix toDense$mcD$sp() {
      return this.toDense();
   }

   public DenseMatrix toDense$mcF$sp() {
      return this.toDense();
   }

   public DenseMatrix toDense$mcI$sp() {
      return this.toDense();
   }

   public DenseMatrix toDense$mcJ$sp() {
      return this.toDense();
   }

   public boolean specInstance$() {
      return false;
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$activeKeysIterator$2(final CSCMatrix $this, final int c$1, final int rr) {
      return new Tuple2.mcII.sp($this.rowIndices()[rr], c$1);
   }

   // $FF: synthetic method
   public static final Iterator $anonfun$activeKeysIterator$1(final CSCMatrix $this, final int c) {
      return scala.package..MODULE$.Iterator().range($this.colPtrs()[c], $this.colPtrs()[c + 1]).map((rr) -> $anonfun$activeKeysIterator$2($this, c, BoxesRunTime.unboxToInt(rr)));
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$activeIterator$2(final CSCMatrix $this, final int c$2, final int rr) {
      return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(new Tuple2.mcII.sp($this.rowIndices()[rr], c$2)), .MODULE$.array_apply($this.data(), rr));
   }

   // $FF: synthetic method
   public static final Iterator $anonfun$activeIterator$1(final CSCMatrix $this, final int c) {
      return scala.package..MODULE$.Iterator().range($this.colPtrs()[c], $this.colPtrs()[c + 1]).map((rr) -> $anonfun$activeIterator$2($this, c, BoxesRunTime.unboxToInt(rr)));
   }

   public CSCMatrix(final Object _data, final int rows, final int cols, final int[] colPtrs, final int used, final int[] _rowIndices, final Zero evidence$1) {
      this._data = _data;
      this.rows = rows;
      this.cols = cols;
      this.colPtrs = colPtrs;
      this.used = used;
      this.breeze$linalg$CSCMatrix$$_rowIndices = _rowIndices;
      this.evidence$1 = evidence$1;
      super();
      QuasiTensor.$init$(this);
      ImmutableNumericOps.$init$(this);
      NumericOps.$init$(this);
      TensorLike.$init$(this);
      MatrixLike.$init$(this);
      Matrix.$init$(this);
      CSCMatrix$.MODULE$.breeze$linalg$CSCMatrix$$init();
   }

   public CSCMatrix(final Object data, final int rows, final int cols, final int[] colPtrs, final int[] rowIndices, final Zero evidence$2) {
      this(data, rows, cols, colPtrs, .MODULE$.array_length(data), rowIndices, evidence$2);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class Builder {
      private final int rows;
      private final int cols;
      public final ClassTag breeze$linalg$CSCMatrix$Builder$$evidence$8;
      public final Semiring evidence$9;
      public final Zero evidence$10;
      private final ArrayBuilder.ofLong breeze$linalg$CSCMatrix$Builder$$indices;
      public final breeze.util.ArrayBuilder vs;
      private int breeze$linalg$CSCMatrix$Builder$$numAdded;

      public int rows() {
         return this.rows;
      }

      public int cols() {
         return this.cols;
      }

      public Semiring ring() {
         return (Semiring)scala.Predef..MODULE$.implicitly(this.evidence$9);
      }

      public void add(final int r, final int c, final Object v) {
         if (!BoxesRunTime.equals(v, this.ring().zero())) {
            this.breeze$linalg$CSCMatrix$Builder$$numAdded_$eq(this.breeze$linalg$CSCMatrix$Builder$$numAdded() + 1);
            this.vs().$plus$eq(v);
            this.breeze$linalg$CSCMatrix$Builder$$indices().$plus$eq(BoxesRunTime.boxToLong((long)c << 32 | (long)r & 4294967295L));
         }

      }

      public ArrayBuilder.ofLong breeze$linalg$CSCMatrix$Builder$$indices() {
         return this.breeze$linalg$CSCMatrix$Builder$$indices;
      }

      public breeze.util.ArrayBuilder vs() {
         return this.vs;
      }

      public int breeze$linalg$CSCMatrix$Builder$$numAdded() {
         return this.breeze$linalg$CSCMatrix$Builder$$numAdded;
      }

      public void breeze$linalg$CSCMatrix$Builder$$numAdded_$eq(final int x$1) {
         this.breeze$linalg$CSCMatrix$Builder$$numAdded = x$1;
      }

      public int activeSize() {
         return this.breeze$linalg$CSCMatrix$Builder$$numAdded();
      }

      public void sizeHint(final int nnz) {
         this.breeze$linalg$CSCMatrix$Builder$$indices().sizeHint(nnz);
         this.vs().sizeHint(nnz);
      }

      public CSCMatrix result() {
         return this.result(false, false);
      }

      public int breeze$linalg$CSCMatrix$Builder$$rowFromIndex(final long idx) {
         return (int)idx;
      }

      public int breeze$linalg$CSCMatrix$Builder$$colFromIndex(final long idx) {
         return (int)(idx >>> 32);
      }

      public CSCMatrix result(final boolean keysAlreadyUnique, final boolean keysAlreadySorted) {
         long[] indices = this.breeze$linalg$CSCMatrix$Builder$$indices().result();
         Object vs = this.vs().result();
         int nnz = indices.length;
         int _rows = this.rows() >= 0 ? this.rows() : BoxesRunTime.unboxToInt(scala.collection.ArrayOps..MODULE$.foldLeft$extension(scala.Predef..MODULE$.intArrayOps((int[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.longArrayOps(indices), (JFunction1.mcIJ.sp)(idx) -> this.breeze$linalg$CSCMatrix$Builder$$rowFromIndex(idx), scala.reflect.ClassTag..MODULE$.Int())), BoxesRunTime.boxToInteger(0), (JFunction2.mcIII.sp)(x$2, x$3) -> scala.runtime.RichInt..MODULE$.max$extension(scala.Predef..MODULE$.intWrapper(x$2), x$3))) + 1;
         int _cols = this.cols() >= 0 ? this.cols() : BoxesRunTime.unboxToInt(scala.collection.ArrayOps..MODULE$.foldLeft$extension(scala.Predef..MODULE$.intArrayOps((int[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.longArrayOps(indices), (JFunction1.mcIJ.sp)(idx) -> this.breeze$linalg$CSCMatrix$Builder$$colFromIndex(idx), scala.reflect.ClassTag..MODULE$.Int())), BoxesRunTime.boxToInteger(0), (JFunction2.mcIII.sp)(x$4, x$5) -> scala.runtime.RichInt..MODULE$.max$extension(scala.Predef..MODULE$.intWrapper(x$4), x$5))) + 1;
         int[] outCols = new int[_cols + 1];
         if (nnz == 0) {
            return new CSCMatrix(vs, _rows, _cols, outCols, 0, (int[])scala.Array..MODULE$.apply(scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.Int()), this.evidence$10);
         } else {
            Sorting$.MODULE$.indirectSort((long[])indices, vs, 0, nnz);
            int[] outRows = new int[nnz];
            Object outData = this.breeze$linalg$CSCMatrix$Builder$$evidence$8.newArray(nnz);
            outRows[0] = this.breeze$linalg$CSCMatrix$Builder$$rowFromIndex(indices[0]);
            .MODULE$.array_update(outData, 0, .MODULE$.array_apply(vs, 0));
            int outDataIndex = 0;
            int i = 1;

            int lastCol;
            for(lastCol = this.breeze$linalg$CSCMatrix$Builder$$colFromIndex(indices[0]); i < nnz; ++i) {
               long index = indices[i];
               int col = this.breeze$linalg$CSCMatrix$Builder$$colFromIndex(index);
               boolean cond$macro$1 = this.cols() < 0 || col < this.cols();
               if (!cond$macro$1) {
                  throw new IllegalArgumentException((new java.lang.StringBuilder(73)).append("requirement failed: ").append((new java.lang.StringBuilder(54)).append("Column index ").append(col).append(" is out of bounds for number of columns ").append(this.cols()).append("!").toString()).append(": ").append("Builder.this.cols.<(0).||(col.<(Builder.this.cols))").toString());
               }

               boolean colsEqual = col == lastCol;
               int row = this.breeze$linalg$CSCMatrix$Builder$$rowFromIndex(index);
               boolean cond$macro$2 = this.rows() < 0 || row < this.rows();
               if (!cond$macro$2) {
                  throw new IllegalArgumentException((new java.lang.StringBuilder(73)).append("requirement failed: ").append((new java.lang.StringBuilder(48)).append("Row index ").append(row).append(" is out of bounds for number of rows ").append(this.rows()).append("!").toString()).append(": ").append("Builder.this.rows.<(0).||(row.<(Builder.this.rows))").toString());
               }

               if (colsEqual && row == this.breeze$linalg$CSCMatrix$Builder$$rowFromIndex(indices[i - 1])) {
                  boolean cond$macro$3 = !keysAlreadyUnique;
                  if (!cond$macro$3) {
                     throw new AssertionError("assertion failed: keysAlreadyUnique.unary_!");
                  }

                  .MODULE$.array_update(outData, outDataIndex, this.ring().$plus(.MODULE$.array_apply(outData, outDataIndex), .MODULE$.array_apply(vs, i)));
               } else {
                  ++outDataIndex;
                  outRows[outDataIndex] = row;
                  .MODULE$.array_update(outData, outDataIndex, .MODULE$.array_apply(vs, i));
               }

               if (!colsEqual) {
                  while(lastCol < col) {
                     outCols[lastCol + 1] = outDataIndex;
                     ++lastCol;
                  }
               }
            }

            ++outDataIndex;
            if (keysAlreadyUnique && outDataIndex != nnz) {
               throw new AssertionError((new java.lang.StringBuilder(44)).append("assertion failed: ").append("outDataIndex == nnz (").append(outDataIndex).append(" ").append("!=").append(" ").append(nnz).append(")").toString());
            } else {
               while(lastCol < _cols) {
                  outCols[lastCol + 1] = outDataIndex;
                  ++lastCol;
               }

               CSCMatrix out = new CSCMatrix(outData, _rows, _cols, outCols, outDataIndex, outRows, this.evidence$10);
               if (!keysAlreadyUnique) {
                  out.compact();
               }

               return out;
            }
         }
      }

      public boolean result$default$1() {
         return false;
      }

      public boolean result$default$2() {
         return false;
      }

      public Semiring ring$mcD$sp() {
         return this.ring();
      }

      public Semiring ring$mcF$sp() {
         return this.ring();
      }

      public Semiring ring$mcI$sp() {
         return this.ring();
      }

      public Semiring ring$mcJ$sp() {
         return this.ring();
      }

      public void add$mcD$sp(final int r, final int c, final double v) {
         this.add(r, c, BoxesRunTime.boxToDouble(v));
      }

      public void add$mcF$sp(final int r, final int c, final float v) {
         this.add(r, c, BoxesRunTime.boxToFloat(v));
      }

      public void add$mcI$sp(final int r, final int c, final int v) {
         this.add(r, c, BoxesRunTime.boxToInteger(v));
      }

      public void add$mcJ$sp(final int r, final int c, final long v) {
         this.add(r, c, BoxesRunTime.boxToLong(v));
      }

      public breeze.util.ArrayBuilder vs$mcD$sp() {
         return this.vs();
      }

      public breeze.util.ArrayBuilder vs$mcF$sp() {
         return this.vs();
      }

      public breeze.util.ArrayBuilder vs$mcI$sp() {
         return this.vs();
      }

      public breeze.util.ArrayBuilder vs$mcJ$sp() {
         return this.vs();
      }

      public CSCMatrix result$mcD$sp() {
         return this.result();
      }

      public CSCMatrix result$mcF$sp() {
         return this.result();
      }

      public CSCMatrix result$mcI$sp() {
         return this.result();
      }

      public CSCMatrix result$mcJ$sp() {
         return this.result();
      }

      public CSCMatrix result$mcD$sp(final boolean keysAlreadyUnique, final boolean keysAlreadySorted) {
         return this.result(keysAlreadyUnique, keysAlreadySorted);
      }

      public CSCMatrix result$mcF$sp(final boolean keysAlreadyUnique, final boolean keysAlreadySorted) {
         return this.result(keysAlreadyUnique, keysAlreadySorted);
      }

      public CSCMatrix result$mcI$sp(final boolean keysAlreadyUnique, final boolean keysAlreadySorted) {
         return this.result(keysAlreadyUnique, keysAlreadySorted);
      }

      public CSCMatrix result$mcJ$sp(final boolean keysAlreadyUnique, final boolean keysAlreadySorted) {
         return this.result(keysAlreadyUnique, keysAlreadySorted);
      }

      public boolean specInstance$() {
         return false;
      }

      public Builder(final int rows, final int cols, final int initNnz, final ClassTag evidence$8, final Semiring evidence$9, final Zero evidence$10) {
         this.rows = rows;
         this.cols = cols;
         this.breeze$linalg$CSCMatrix$Builder$$evidence$8 = evidence$8;
         this.evidence$9 = evidence$9;
         this.evidence$10 = evidence$10;
         this.breeze$linalg$CSCMatrix$Builder$$indices = new ArrayBuilder.ofLong();
         this.vs = ArrayBuilder$.MODULE$.make(evidence$8);
         this.breeze$linalg$CSCMatrix$Builder$$numAdded = 0;
         Statics.releaseFence();
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class Builder$ {
      public static final Builder$ MODULE$ = new Builder$();

      public int $lessinit$greater$default$3() {
         return 16;
      }

      public Builder fromMatrix(final CSCMatrix matrix, final ClassTag evidence$11, final Semiring evidence$12, final Zero evidence$13) {
         Builder bldr = new Builder(matrix.rows(), matrix.cols(), matrix.activeSize(), evidence$11, evidence$12, evidence$13);

         for(int c = 0; c < matrix.cols(); ++c) {
            int rr = matrix.colPtrs()[c];

            for(int rrlast = matrix.colPtrs()[c + 1]; rr < rrlast; ++rr) {
               int r = matrix.rowIndices()[rr];
               bldr.add(r, c, .MODULE$.array_apply(matrix.data(), rr));
            }
         }

         return bldr;
      }

      public Builder fromMatrix$mDc$sp(final CSCMatrix matrix, final ClassTag evidence$11, final Semiring evidence$12, final Zero evidence$13) {
         Builder bldr = new CSCMatrix$Builder$mcD$sp(matrix.rows(), matrix.cols(), matrix.activeSize(), evidence$11, evidence$12, evidence$13);

         for(int c = 0; c < matrix.cols(); ++c) {
            int rr = matrix.colPtrs()[c];

            for(int rrlast = matrix.colPtrs()[c + 1]; rr < rrlast; ++rr) {
               int r = matrix.rowIndices()[rr];
               bldr.add$mcD$sp(r, c, matrix.data$mcD$sp()[rr]);
            }
         }

         return bldr;
      }

      public Builder fromMatrix$mFc$sp(final CSCMatrix matrix, final ClassTag evidence$11, final Semiring evidence$12, final Zero evidence$13) {
         Builder bldr = new CSCMatrix$Builder$mcF$sp(matrix.rows(), matrix.cols(), matrix.activeSize(), evidence$11, evidence$12, evidence$13);

         for(int c = 0; c < matrix.cols(); ++c) {
            int rr = matrix.colPtrs()[c];

            for(int rrlast = matrix.colPtrs()[c + 1]; rr < rrlast; ++rr) {
               int r = matrix.rowIndices()[rr];
               bldr.add$mcF$sp(r, c, matrix.data$mcF$sp()[rr]);
            }
         }

         return bldr;
      }

      public Builder fromMatrix$mIc$sp(final CSCMatrix matrix, final ClassTag evidence$11, final Semiring evidence$12, final Zero evidence$13) {
         Builder bldr = new CSCMatrix$Builder$mcI$sp(matrix.rows(), matrix.cols(), matrix.activeSize(), evidence$11, evidence$12, evidence$13);

         for(int c = 0; c < matrix.cols(); ++c) {
            int rr = matrix.colPtrs()[c];

            for(int rrlast = matrix.colPtrs()[c + 1]; rr < rrlast; ++rr) {
               int r = matrix.rowIndices()[rr];
               bldr.add$mcI$sp(r, c, matrix.data$mcI$sp()[rr]);
            }
         }

         return bldr;
      }

      public Builder fromMatrix$mJc$sp(final CSCMatrix matrix, final ClassTag evidence$11, final Semiring evidence$12, final Zero evidence$13) {
         Builder bldr = new CSCMatrix$Builder$mcJ$sp(matrix.rows(), matrix.cols(), matrix.activeSize(), evidence$11, evidence$12, evidence$13);

         for(int c = 0; c < matrix.cols(); ++c) {
            int rr = matrix.colPtrs()[c];

            for(int rrlast = matrix.colPtrs()[c + 1]; rr < rrlast; ++rr) {
               int r = matrix.rowIndices()[rr];
               bldr.add$mcJ$sp(r, c, matrix.data$mcJ$sp()[rr]);
            }
         }

         return bldr;
      }
   }

   public static class FrobeniusInnerProductCSCMatrixSpace$ {
      public static final FrobeniusInnerProductCSCMatrixSpace$ MODULE$ = new FrobeniusInnerProductCSCMatrixSpace$();

      public MutableFiniteCoordinateField space(final Field evidence$14, final ClassTag evidence$15) {
         MatrixInnerProduct norms = EntrywiseMatrixNorms$.MODULE$.make(evidence$14, HasOps$.MODULE$.CSCMatrixCanMulScalarM_M_Semiring(evidence$14, evidence$15, Zero$.MODULE$.zeroFromSemiring(evidence$14)), HasOps$.MODULE$.CSC_canIterateValues());
         return MutableFiniteCoordinateField$.MODULE$.make(norms.canNorm_Field(evidence$14), norms.canInnerProductNorm_Ring(evidence$14), evidence$14, HasOps$.MODULE$.canAddM_S_Semiring(evidence$14, evidence$15), HasOps$.MODULE$.canSubM_S_Ring(evidence$14, evidence$15), HasOps$.MODULE$.CSCMatrixCanMulScalarM_M_Semiring(evidence$14, evidence$15, Zero$.MODULE$.zeroFromSemiring(evidence$14)), HasOps$.MODULE$.csc_csc_BadOp_OpDiv(evidence$14, evidence$15), HasOps$.MODULE$.CSC_canCopy(evidence$15, Zero$.MODULE$.zeroFromSemiring(evidence$14)), HasOps$.MODULE$.impl_Op_InPlace_CSC_T_lift_OpMulScalar(evidence$14, evidence$15), HasOps$.MODULE$.impl_Op_InPlace_CSC_T_lift_OpDiv(evidence$14, evidence$15), HasOps$.MODULE$.impl_Op_CSC_CSC_eq_CSC_lift_OpAdd(evidence$14, evidence$15), HasOps$.MODULE$.impl_Op_CSC_CSC_eq_CSC_lift_OpSub(evidence$14, evidence$15), HasOps$.MODULE$.impl_Op_InPlace_CSC_T_lift_OpAdd(evidence$14, evidence$15), HasOps$.MODULE$.impl_Op_InPlace_CSC_T_lift_OpSub(evidence$14, evidence$15), HasOps$.MODULE$.impl_Op_CSC_CSC_eq_CSC_lift_OpMulScalar(evidence$14, evidence$15), HasOps$.MODULE$.impl_Op_CSC_CSC_eq_CSC_lift_OpDiv(evidence$14, evidence$15), HasOps$.MODULE$.impl_Op_CSC_CSC_eq_CSC_lift_OpSet(evidence$14, evidence$15), HasOps$.MODULE$.impl_Op_InPlace_CSC_T_lift_OpSet(evidence$14, evidence$15), HasOps$.MODULE$.cscScaleAdd(evidence$14, evidence$15), HasOps$.MODULE$.CSC_canCreateZerosLike(evidence$15, Zero$.MODULE$.zeroFromSemiring(evidence$14)), CSCMatrix$.MODULE$.canCreateZeros(evidence$15, Zero$.MODULE$.zeroFromSemiring(evidence$14)), CSCMatrix$.MODULE$.canDim(), HasOps$.MODULE$.canMulM_S_Ring_OpMulScalar(evidence$14, evidence$15), HasOps$.MODULE$.csc_T_Op_OpDiv(evidence$14, evidence$15), HasOps$.MODULE$.CSCMatrixCanAdd_M_M_Semiring(evidence$14, Zero$.MODULE$.zeroFromSemiring(evidence$14), evidence$15), HasOps$.MODULE$.CSCMatrixCanSubM_M_Ring(evidence$14, Zero$.MODULE$.zeroFromSemiring(evidence$14), evidence$15), HasOps$.MODULE$.csc_OpNeg(evidence$14, evidence$15), scala..less.colon.less..MODULE$.refl(), norms.canInnerProduct(), HasOps$.MODULE$.zipMapVals(evidence$15, evidence$14, Zero$.MODULE$.zeroFromSemiring(evidence$14)), HasOps$.MODULE$.zipMapKeyVals(evidence$15, evidence$14, Zero$.MODULE$.zeroFromSemiring(evidence$14)), HasOps$.MODULE$.CSC_canIterateValues(), HasOps$.MODULE$.CSC_canMapValues(evidence$15, evidence$14), HasOps$.MODULE$.CSC_scalarOf());
      }
   }
}
