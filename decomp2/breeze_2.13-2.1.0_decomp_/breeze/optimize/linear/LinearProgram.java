package breeze.optimize.linear;

import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$mcD$sp;
import breeze.linalg.SparseVector;
import breeze.linalg.SparseVector$;
import breeze.linalg.Vector;
import breeze.linalg.operators.HasOps$;
import breeze.storage.Zero$;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Collection;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearConstraintSet;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.linear.SimplexSolver;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Option.;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction1;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015EdaBAJ\u0003+\u0003\u00111\u0015\u0005\b\u0003c\u0003A\u0011AAZ\u0011%\tI\f\u0001a\u0001\n\u0013\tY\fC\u0005\u0002D\u0002\u0001\r\u0011\"\u0003\u0002F\"A\u0011\u0011\u001b\u0001!B\u0013\ti\fC\u0004\u0002T\u0002!I!a/\t\u0013\u0005U\u0007A1A\u0005\n\u0005]\u0007\u0002\u0003Cm\u0001\u0001\u0006I!!7\t\u000f\rE\u0006\u0001\"\u0001\u0005\\\"911\u0014\u0001\u0005\u0002\u0011\u0005h!CA}\u0001A\u0005\u0019\u0011EA~\u0011\u001d\tiP\u0003C\u0001\u0003\u007fDqA!\u0001\u000b\t\u0003\u0011\u0019\u0001C\u0004\u00030)1\tA!\r\t\u000f\tM\"B\"\u0001\u00036!91q\u0001\u0006\u0005\u0002\r%\u0001bBB\n\u0015\u0011\u00051Q\u0003\u0005\b\u0005\u001fTA\u0011\tBz\r%\t\u0019\u0010\u0001I\u0001\u0004C\t)\u0010C\u0004\u0002~J!\t!a@\t\u000f\u0011=!C\"\u0001\u0005\u0012!9A\u0011\u0004\n\u0005\u0002\r-\u0003b\u0002B\u0018%\u0011\u0005!\u0011\u0007\u0005\b\u0005g\u0011B\u0011\u0001B\u001b\u0011\u001d!YB\u0005C\u0001\t;Aq\u0001b\u0007\u0013\t\u0003!\u0019\u0003C\u0004\u0005(I!\t\u0001\"\u000b\t\u000f\u0011\u001d\"\u0003\"\u0001\u0005.!9A\u0011\u0007\n\u0005\u0002\tE\u0002b\u0002C\u001a%\u0011\u0005AQ\u0007\u0005\b\tg\u0011B\u0011\u0001C\u001e\u0011\u001d!\tE\u0005C\u0001\t\u0007Bq\u0001\"\u0011\u0013\t\u0003!9\u0005C\u0004\u0005LI!\t\u0001\"\u0014\t\u000f\u0011-#\u0003\"\u0001\u0005R!9AQ\u000b\n\u0005\u0002\u0011]\u0003b\u0002C.%\u0011\u0005AQ\f\u0004\b\u0005?\u0002\u0011\u0011\u0005B1\u0011)\u0011\u0019'\nBC\u0002\u0013\u0005!Q\r\u0005\u000b\u0005o*#\u0011!Q\u0001\n\t\u001d\u0004bBAYK\u0011\u0005!\u0011P\u0004\b\tK\u0004\u0001\u0012\u0011Bu\r\u001d\u0011\u0019\u000f\u0001EA\u0005KDq!!-+\t\u0003\u00119\u000fC\u0005\u0003\u0014*\n\t\u0011\"\u0011\u0003\u0016\"I!Q\u0015\u0016\u0002\u0002\u0013\u0005\u00111\u0018\u0005\n\u0005OS\u0013\u0011!C\u0001\u0005WD\u0011Ba-+\u0003\u0003%\tE!.\t\u0013\t}&&!A\u0005\u0002\t=\b\"\u0003BfU\u0005\u0005I\u0011\tBg\u0011%\u0011yMKA\u0001\n\u0003\u0012\tnB\u0004\u0005h\u0002A\tI!7\u0007\u000f\tM\u0007\u0001#!\u0003V\"9\u0011\u0011\u0017\u001b\u0005\u0002\t]\u0007\"\u0003BJi\u0005\u0005I\u0011\tBK\u0011%\u0011)\u000bNA\u0001\n\u0003\tY\fC\u0005\u0003(R\n\t\u0011\"\u0001\u0003\\\"I!1\u0017\u001b\u0002\u0002\u0013\u0005#Q\u0017\u0005\n\u0005\u007f#\u0014\u0011!C\u0001\u0005?D\u0011Ba35\u0003\u0003%\tE!4\t\u0013\t=G'!A\u0005B\tEwa\u0002Cu\u0001!\u0005%\u0011\u0013\u0004\b\u0005\u007f\u0002\u0001\u0012\u0011BA\u0011\u001d\t\tL\u0010C\u0001\u0005\u001fC\u0011Ba%?\u0003\u0003%\tE!&\t\u0013\t\u0015f(!A\u0005\u0002\u0005m\u0006\"\u0003BT}\u0005\u0005I\u0011\u0001BU\u0011%\u0011\u0019LPA\u0001\n\u0003\u0012)\fC\u0005\u0003@z\n\t\u0011\"\u0001\u0003B\"I!1\u001a \u0002\u0002\u0013\u0005#Q\u001a\u0005\n\u0005\u001ft\u0014\u0011!C!\u0005#4\u0011B!\u0015\u0001!\u0003\r\tCa\u0015\t\u000f\u0005ux\t\"\u0001\u0002\u0000\"9!QK$\u0007\u0002\tE\u0002b\u0002B,\u000f\u001a\u0005!\u0011\u0007\u0005\b\u00053:e\u0011\u0001B.\u0011\u001d\u0011ym\u0012C!\u0005gDqA!>H\t\u0003\u00119PB\u0005\u0002n\u0002\u0001\n1!\t\u0002p\"9\u0011Q (\u0005\u0002\u0005}\bb\u0002C4\u001d\u001a\u0005!Q\r\u0005\b\tSre\u0011AA^\u0011\u001d!YG\u0014C\u0001\u0003wCqAa4O\t\u0003\u0012\u0019P\u0002\u0004\u0005<\u0002\u0001EQ\u0018\u0005\u000b\tO\"&Q3A\u0005\u0002\t\u0015\u0004B\u0003C:)\nE\t\u0015!\u0003\u0003h!9\u0011\u0011\u0017+\u0005\u0002\u0011}\u0006\"\u0003C5)\n\u0007I\u0011AA^\u0011!!Y\b\u0016Q\u0001\n\u0005u\u0006b\u0002C\b)\u0012\u0005AQ\u0010\u0005\n\u0007\u001b\"\u0016\u0011!C\u0001\t\u000bD\u0011b!\u0016U#\u0003%\t\u0001\"#\t\u0013\tME+!A\u0005B\tU\u0005\"\u0003BS)\u0006\u0005I\u0011AA^\u0011%\u00119\u000bVA\u0001\n\u0003!I\rC\u0005\u00034R\u000b\t\u0011\"\u0011\u00036\"I!q\u0018+\u0002\u0002\u0013\u0005AQ\u001a\u0005\n\u0007w\"\u0016\u0011!C!\t#D\u0011Ba3U\u0003\u0003%\tE!4\t\u0013\r\u0005E+!A\u0005B\u0011Uw!\u0003Cv\u0001\u0005\u0005\t\u0012\u0001Cw\r%!Y\fAA\u0001\u0012\u0003!y\u000fC\u0004\u00022\u001a$\t!b\u0002\t\u0013\t=g-!A\u0005F\tE\u0007\"CC\u0005M\u0006\u0005I\u0011QC\u0006\u0011%)yAZI\u0001\n\u0003!I\tC\u0005\u0006\u0012\u0019\f\t\u0011\"!\u0006\u0014!IQ1\u00044\u0012\u0002\u0013\u0005A\u0011\u0012\u0004\u0007\t;\u0003\u0001\tb(\t\u0015\u0011\u001dTN!f\u0001\n\u0003\u0011)\u0007\u0003\u0006\u0005t5\u0014\t\u0012)A\u0005\u0005OBq!!-n\t\u0003!\t\u000bC\u0005\u0005j5\u0014\r\u0011\"\u0001\u0002<\"AA1P7!\u0002\u0013\ti\fC\u0004\u0005\u00105$\t\u0001\" \t\u0013\r5S.!A\u0005\u0002\u0011\u001d\u0006\"CB+[F\u0005I\u0011\u0001CE\u0011%\u0011\u0019*\\A\u0001\n\u0003\u0012)\nC\u0005\u0003&6\f\t\u0011\"\u0001\u0002<\"I!qU7\u0002\u0002\u0013\u0005A1\u0016\u0005\n\u0005gk\u0017\u0011!C!\u0005kC\u0011Ba0n\u0003\u0003%\t\u0001b,\t\u0013\rmT.!A\u0005B\u0011M\u0006\"\u0003Bf[\u0006\u0005I\u0011\tBg\u0011%\u0019\t)\\A\u0001\n\u0003\"9lB\u0005\u0006\u001e\u0001\t\t\u0011#\u0001\u0006 \u0019IAQ\u0014\u0001\u0002\u0002#\u0005Q\u0011\u0005\u0005\b\u0003c{H\u0011AC\u0013\u0011%\u0011ym`A\u0001\n\u000b\u0012\t\u000eC\u0005\u0006\n}\f\t\u0011\"!\u0006(!IQqB@\u0012\u0002\u0013\u0005A\u0011\u0012\u0005\n\u000b#y\u0018\u0011!CA\u000bWA\u0011\"b\u0007\u0000#\u0003%\t\u0001\"#\u0007\r\u0011=\u0004\u0001\u0011C9\u0011-!9'!\u0004\u0003\u0016\u0004%\tA!\u001a\t\u0017\u0011M\u0014Q\u0002B\tB\u0003%!q\r\u0005\t\u0003c\u000bi\u0001\"\u0001\u0005v!QA\u0011NA\u0007\u0005\u0004%\t!a/\t\u0013\u0011m\u0014Q\u0002Q\u0001\n\u0005u\u0006\u0002\u0003C\b\u0003\u001b!\t\u0001\" \t\u0015\r5\u0013QBA\u0001\n\u0003!)\t\u0003\u0006\u0004V\u00055\u0011\u0013!C\u0001\t\u0013C!Ba%\u0002\u000e\u0005\u0005I\u0011\tBK\u0011)\u0011)+!\u0004\u0002\u0002\u0013\u0005\u00111\u0018\u0005\u000b\u0005O\u000bi!!A\u0005\u0002\u00115\u0005B\u0003BZ\u0003\u001b\t\t\u0011\"\u0011\u00036\"Q!qXA\u0007\u0003\u0003%\t\u0001\"%\t\u0015\rm\u0014QBA\u0001\n\u0003\")\n\u0003\u0006\u0003L\u00065\u0011\u0011!C!\u0005\u001bD!b!!\u0002\u000e\u0005\u0005I\u0011\tCM\u000f%)y\u0003AA\u0001\u0012\u0003)\tDB\u0005\u0005p\u0001\t\t\u0011#\u0001\u00064!A\u0011\u0011WA\u0019\t\u0003)9\u0004\u0003\u0006\u0003P\u0006E\u0012\u0011!C#\u0005#D!\"\"\u0003\u00022\u0005\u0005I\u0011QC\u001d\u0011))y!!\r\u0012\u0002\u0013\u0005A\u0011\u0012\u0005\u000b\u000b#\t\t$!A\u0005\u0002\u0016u\u0002BCC\u000e\u0003c\t\n\u0011\"\u0001\u0005\n\u001a11\u0011\u0004\u0001A\u00077A1b!\b\u0002@\tU\r\u0011\"\u0001\u0004 !Y11GA \u0005#\u0005\u000b\u0011BB\u0011\u0011-\u0019)$a\u0010\u0003\u0016\u0004%\taa\u000e\t\u0017\re\u0012q\bB\tB\u0003%\u0011q\u001f\u0005\t\u0003c\u000by\u0004\"\u0001\u0004<!A1\u0011IA \t\u0003\u0019\u0019\u0005\u0003\u0005\u0004J\u0005}B\u0011AB&\u0011)\u0019i%a\u0010\u0002\u0002\u0013\u00051q\n\u0005\u000b\u0007+\ny$%A\u0005\u0002\r]\u0003BCB7\u0003\u007f\t\n\u0011\"\u0001\u0004p!Q!1SA \u0003\u0003%\tE!&\t\u0015\t\u0015\u0016qHA\u0001\n\u0003\tY\f\u0003\u0006\u0003(\u0006}\u0012\u0011!C\u0001\u0007gB!Ba-\u0002@\u0005\u0005I\u0011\tB[\u0011)\u0011y,a\u0010\u0002\u0002\u0013\u00051q\u000f\u0005\u000b\u0007w\ny$!A\u0005B\ru\u0004B\u0003Bf\u0003\u007f\t\t\u0011\"\u0011\u0003N\"Q!qZA \u0003\u0003%\tE!5\t\u0015\r\u0005\u0015qHA\u0001\n\u0003\u001a\u0019iB\u0005\u0006B\u0001\t\t\u0011#\u0001\u0006D\u0019I1\u0011\u0004\u0001\u0002\u0002#\u0005QQ\t\u0005\t\u0003c\u000bI\u0007\"\u0001\u0006N!Q!qZA5\u0003\u0003%)E!5\t\u0015\u0015%\u0011\u0011NA\u0001\n\u0003+y\u0005\u0003\u0006\u0006\u0012\u0005%\u0014\u0011!CA\u000b+Bqaa'\u0001\t\u0003)\t\u0007C\u0004\u00042\u0002!\t!\"\u001b\b\u0011\r=\u0015Q\u0013E\u0001\u0007#3\u0001\"a%\u0002\u0016\"\u000511\u0013\u0005\t\u0003c\u000bI\b\"\u0001\u0004\u0016\u001aQ1qSA=!\u0003\r\na!'\t\u0011\rm\u0015Q\u0010D\u0001\u0007;C\u0001b!-\u0002~\u0019\u000511\u0017\u0005\u000b\u0007\u0003\fIH1A\u0005\u0004\r\r\u0007\"CBe\u0003s\u0002\u000b\u0011BBc\u000f!\u0019Y-!\u001f\t\u0002\r5g\u0001CBh\u0003sB\ta!5\t\u0011\u0005E\u0016\u0011\u0012C\u0001\u0007'D\u0001ba'\u0002\n\u0012\u00051Q\u001b\u0005\t\u0007c\u000bI\t\"\u0001\u0004d\"A1\u0011_AE\t\u0013\u0019\u0019PA\u0007MS:,\u0017M\u001d)s_\u001e\u0014\u0018-\u001c\u0006\u0005\u0003/\u000bI*\u0001\u0004mS:,\u0017M\u001d\u0006\u0005\u00037\u000bi*\u0001\u0005paRLW.\u001b>f\u0015\t\ty*\u0001\u0004ce\u0016,'0Z\u0002\u0001'\r\u0001\u0011Q\u0015\t\u0005\u0003O\u000bi+\u0004\u0002\u0002**\u0011\u00111V\u0001\u0006g\u000e\fG.Y\u0005\u0005\u0003_\u000bIK\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\u0005U\u0006cAA\\\u00015\u0011\u0011QS\u0001\b?:,\u0007\u0010^%e+\t\ti\f\u0005\u0003\u0002(\u0006}\u0016\u0002BAa\u0003S\u00131!\u00138u\u0003-yf.\u001a=u\u0013\u0012|F%Z9\u0015\t\u0005\u001d\u0017Q\u001a\t\u0005\u0003O\u000bI-\u0003\u0003\u0002L\u0006%&\u0001B+oSRD\u0011\"a4\u0004\u0003\u0003\u0005\r!!0\u0002\u0007a$\u0013'\u0001\u0005`]\u0016DH/\u00133!\u0003\u0019qW\r\u001f;JI\u0006Ia/\u0019:jC\ndWm]\u000b\u0003\u00033\u0004b!a7\u0002f\u0006%XBAAo\u0015\u0011\ty.!9\u0002\u000f5,H/\u00192mK*!\u00111]AU\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003O\fiNA\u0006BeJ\f\u0017PQ;gM\u0016\u0014\bcAAv\u001d6\t\u0001A\u0001\u0005WCJL\u0017M\u00197f'\u0015q\u0015QUAy!\r\tYO\u0005\u0002\u000b\u000bb\u0004(/Z:tS>t7#\u0002\n\u0002&\u0006]\bcAAv\u0015\t9\u0001K]8cY\u0016l7c\u0001\u0006\u0002&\u00061A%\u001b8ji\u0012\"\"!a2\u0002\t\u001d|\u0017\r\\\u000b\u0003\u0005\u000b\u0001b!a*\u0003\b\t-\u0011\u0002\u0002B\u0005\u0003S\u0013aa\u00149uS>t\u0007\u0003\u0002B\u0007\u0005Wi!Aa\u0004\u000b\t\tE!1C\u0001\u0007g\u000e\fG.\u0019:\u000b\t\tU!qC\u0001\n]>tG.\u001b8fCJTAA!\u0007\u0003\u001c\u0005)q\u000e\u001d;j[*!!Q\u0004B\u0010\u0003\u0015i\u0017\r\u001e54\u0015\u0011\u0011\tCa\t\u0002\u000f\r|W.\\8og*!!Q\u0005B\u0014\u0003\u0019\t\u0007/Y2iK*\u0011!\u0011F\u0001\u0004_J<\u0017\u0002\u0002B\u0017\u0005\u001f\u0011\u0001bR8bYRK\b/Z\u0001\n_\nTWm\u0019;jm\u0016,\"!!=\u0002\u0017\r|gn\u001d;sC&tGo]\u000b\u0003\u0005o\u0001bA!\u000f\u0003J\t=c\u0002\u0002B\u001e\u0005\u000brAA!\u0010\u0003D5\u0011!q\b\u0006\u0005\u0005\u0003\n\t+\u0001\u0004=e>|GOP\u0005\u0003\u0003WKAAa\u0012\u0002*\u00069\u0001/Y2lC\u001e,\u0017\u0002\u0002B&\u0005\u001b\u0012!\"\u00138eKb,GmU3r\u0015\u0011\u00119%!+\u0011\u0007\u0005-xI\u0001\u0006D_:\u001cHO]1j]R\u001c2aRAS\u0003\ra\u0007n]\u0001\u0004e\"\u001c\u0018\u0001\u0003:fY\u0006$\u0018n\u001c8\u0016\u0005\tu\u0003cAAvK\tA!+\u001a7bi&|gnE\u0002&\u0003K\u000b\u0001b\u001c9fe\u0006$xN]\u000b\u0003\u0005O\u0002BA!\u001b\u0003r9!!1\u000eB7!\u0011\u0011i$!+\n\t\t=\u0014\u0011V\u0001\u0007!J,G-\u001a4\n\t\tM$Q\u000f\u0002\u0007'R\u0014\u0018N\\4\u000b\t\t=\u0014\u0011V\u0001\n_B,'/\u0019;pe\u0002\"BA!\u0018\u0003|!9!1\r\u0015A\u0002\t\u001d\u0014\u0006B\u0013?i)\u0012!!R)\u0014\u000fy\u0012iFa!\u0003\nB!\u0011q\u0015BC\u0013\u0011\u00119)!+\u0003\u000fA\u0013x\u000eZ;diB!!\u0011\bBF\u0013\u0011\u0011iI!\u0014\u0003\u0019M+'/[1mSj\f'\r\\3\u0015\u0005\tE\u0005cAAv}\u0005i\u0001O]8ek\u000e$\bK]3gSb,\"Aa&\u0011\t\te%1U\u0007\u0003\u00057SAA!(\u0003 \u0006!A.\u00198h\u0015\t\u0011\t+\u0001\u0003kCZ\f\u0017\u0002\u0002B:\u00057\u000bA\u0002\u001d:pIV\u001cG/\u0011:jif\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0003,\nE\u0006\u0003BAT\u0005[KAAa,\u0002*\n\u0019\u0011I\\=\t\u0013\u0005=')!AA\u0002\u0005u\u0016a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\t]\u0006C\u0002B]\u0005w\u0013Y+\u0004\u0002\u0002b&!!QXAq\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\t\r'\u0011\u001a\t\u0005\u0003O\u0013)-\u0003\u0003\u0003H\u0006%&a\u0002\"p_2,\u0017M\u001c\u0005\n\u0003\u001f$\u0015\u0011!a\u0001\u0005W\u000b\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0003{\u000b\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0005/\u00131a\u0012+F'\u001d!$Q\fBB\u0005\u0013#\"A!7\u0011\u0007\u0005-H\u0007\u0006\u0003\u0003,\nu\u0007\"CAhq\u0005\u0005\t\u0019AA_)\u0011\u0011\u0019M!9\t\u0013\u0005='(!AA\u0002\t-&a\u0001'U\u000bN9!F!\u0018\u0003\u0004\n%EC\u0001Bu!\r\tYO\u000b\u000b\u0005\u0005W\u0013i\u000fC\u0005\u0002P:\n\t\u00111\u0001\u0002>R!!1\u0019By\u0011%\ty\rMA\u0001\u0002\u0004\u0011Y\u000b\u0006\u0002\u0003h\u0005Y1\u000f^1oI\u0006\u0014H-\u001b>f+\t\u0011y%K\u0002H\u0005w4aA!@H\u0001\t}(!\u0004\u001fm_\u000e\fG\u000eI2iS2$gh\u0005\u0004\u0003|\u000e\u0005!q\n\t\u0005\u00053\u001b\u0019!\u0003\u0003\u0004\u0006\tm%AB(cU\u0016\u001cG/A\u0005tk\nTWm\u0019;U_R!\u0011q_B\u0006\u0011\u001d\u0011\u0019d\u0004a\u0001\u0007\u001b\u0001b!a*\u0004\u0010\t=\u0013\u0002BB\t\u0003S\u0013!\u0002\u0010:fa\u0016\fG/\u001a3?\u0003\u0015\u0019x\u000e\u001c<f)\u0011\u00199ba\"\u0011\t\u0005-\u0018q\b\u0002\u0007%\u0016\u001cX\u000f\u001c;\u0014\u0011\u0005}\u0012Q\u0015BB\u0005\u0013\u000baA]3tk2$XCAB\u0011!\u0019\u0019\u0019c!\u000b\u0004.5\u00111Q\u0005\u0006\u0005\u0007O\ti*\u0001\u0004mS:\fGnZ\u0005\u0005\u0007W\u0019)CA\u0006EK:\u001cXMV3di>\u0014\b\u0003BAT\u0007_IAa!\r\u0002*\n1Ai\\;cY\u0016\fqA]3tk2$\b%A\u0004qe>\u0014G.Z7\u0016\u0005\u0005]\u0018\u0001\u00039s_\ndW-\u001c\u0011\u0015\r\r]1QHB \u0011!\u0019i\"!\u0013A\u0002\r\u0005\u0002\u0002CB\u001b\u0003\u0013\u0002\r!a>\u0002\u000fY\fG.^3PMR!1QFB#\u0011!\u00199%a\u0013A\u0002\u0005E\u0018!\u0001=\u0002\u000bY\fG.^3\u0016\u0005\r5\u0012\u0001B2paf$baa\u0006\u0004R\rM\u0003BCB\u000f\u0003\u001f\u0002\n\u00111\u0001\u0004\"!Q1QGA(!\u0003\u0005\r!a>\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u00111\u0011\f\u0016\u0005\u0007C\u0019Yf\u000b\u0002\u0004^A!1qLB5\u001b\t\u0019\tG\u0003\u0003\u0004d\r\u0015\u0014!C;oG\",7m[3e\u0015\u0011\u00199'!+\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0004l\r\u0005$!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012TCAB9U\u0011\t9pa\u0017\u0015\t\t-6Q\u000f\u0005\u000b\u0003\u001f\fI&!AA\u0002\u0005uF\u0003\u0002Bb\u0007sB!\"a4\u0002^\u0005\u0005\t\u0019\u0001BV\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\t]5q\u0010\u0005\u000b\u0003\u001f\fy&!AA\u0002\u0005u\u0016AB3rk\u0006d7\u000f\u0006\u0003\u0003D\u000e\u0015\u0005BCAh\u0003K\n\t\u00111\u0001\u0003,\"91\u0011\u0012\tA\u0004\r-\u0015AB:pYZ,'\u000f\u0005\u0003\u0004\u000e\u0006ud\u0002BA\\\u0003o\nQ\u0002T5oK\u0006\u0014\bK]8he\u0006l\u0007\u0003BA\\\u0003s\u001aB!!\u001f\u0002&R\u00111\u0011\u0013\u0002\u0007'>dg/\u001a:\u0014\t\u0005u\u0014QU\u0001\t[\u0006D\u0018.\\5{KR!1qTBT)\u0011\u0019\tka+\u0011\t\r\r\u0016q\b\b\u0005\u0007K\u001b9\u000b\u0004\u0001\t\u0011\r%\u0016q\u0010a\u0001\u0003k\u000b!\u0001\u001c9\t\u0011\r5\u0016q\u0010a\u0001\u0007_\u000b1a\u001c2k!\r\u0019\u0019KC\u0001\t[&t\u0017.\\5{KR!1QWB^)\u0011\u00199l!0\u0011\t\re\u0016q\b\b\u0005\u0007K\u001bY\f\u0003\u0005\u0004*\u0006\u0005\u0005\u0019AA[\u0011!\u0019i+!!A\u0002\r}\u0006cAB]\u0015\u0005AQ._*pYZ,'/\u0006\u0002\u0004FB!1qYA?\u001b\t\tI(A\u0005nsN{GN^3sA\u0005\u0019\u0012\t]1dQ\u0016\u001c\u0016.\u001c9mKb\u001cv\u000e\u001c<feB!1qYAE\u0005M\t\u0005/Y2iKNKW\u000e\u001d7fqN{GN^3s'\u0019\tI)!*\u0004FR\u00111Q\u001a\u000b\u0005\u0007/\u001ci\u000e\u0006\u0003\u0004Z\u000e}\u0007\u0003BBn\u0003\u007fqAa!*\u0004^\"A1\u0011VAG\u0001\u0004\t)\f\u0003\u0005\u00030\u00055\u0005\u0019ABq!\r\u0019YN\u0003\u000b\u0005\u0007K\u001cY\u000f\u0006\u0003\u0004h\u000e5\b\u0003BBu\u0003\u007fqAa!*\u0004l\"A1\u0011VAH\u0001\u0004\t)\f\u0003\u0005\u00030\u0005=\u0005\u0019ABx!\r\u0019IOC\u0001\u0011EVLG\u000eZ\"p]N$(/Y5oiN$Ba!>\u0005\bQ!1q\u001fC\u0001!\u0011\u0019Ip!@\u000e\u0005\rm(\u0002BAL\u0005/IAaa@\u0004|\n\u0019B*\u001b8fCJ\u001cuN\\:ue\u0006Lg\u000e^*fi\"A!qFAI\u0001\u0004!\u0019\u0001E\u0002\u0005\u0006)qAa!*\u0005\b!A1\u0011VAI\u0001\u0004\t),\u000b\u0003\u000b\t\u0017\u0011bA\u0002B\u007f\u0015\u0001!ia\u0005\u0004\u0005\f\r\u0005\u0011q_\u0001\rG>,gMZ5dS\u0016tGo]\u000b\u0003\t'\u0001baa\t\u0005\u0016\r5\u0012\u0002\u0002C\f\u0007K\u0011aAV3di>\u0014\u0018aD:dC2\f'oQ8na>tWM\u001c;\u0002\u000b\u0011\u0002H.^:\u0015\t\u0005EHq\u0004\u0005\b\tCA\u0002\u0019AAy\u0003\u0015yG\u000f[3s)\u0011\t\t\u0010\"\n\t\u000f\u0011\u0005\u0012\u00041\u0001\u0004.\u00051A%\\5okN$B!!=\u0005,!9A\u0011\u0005\u000eA\u0002\u0005EH\u0003BAy\t_Aq\u0001\"\t\u001c\u0001\u0004\u0019i#\u0001\u0007v]\u0006\u0014\u0018p\u0018\u0013nS:,8/\u0001\u0005%Y\u0016\u001c8\u000fJ3r)\u0011\u0011y\u0005b\u000e\t\u000f\u0011eR\u00041\u0001\u0002r\u0006!!\u000f[:`)\u0011\u0011y\u0005\"\u0010\t\u000f\u0011}b\u00041\u0001\u0004.\u0005\t1-A\u0006%OJ,\u0017\r^3sI\u0015\fH\u0003\u0002B(\t\u000bBq\u0001\"\u000f \u0001\u0004\t\t\u0010\u0006\u0003\u0003P\u0011%\u0003b\u0002C A\u0001\u00071QF\u0001\rI\u0015\fHeY8m_:$S-\u001d\u000b\u0005\u0005\u001f\"y\u0005C\u0004\u0005:\u0005\u0002\r!!=\u0015\t\t=C1\u000b\u0005\b\t\u007f\u0011\u0003\u0019AB\u0017\u0003\u0019!C/[7fgR!\u0011\u0011\u001fC-\u0011\u001d!yd\ta\u0001\u0007[\tA\u0002\n;j[\u0016\u001cHeY8m_:$B!!=\u0005`!9Aq\b\u0013A\u0002\r5\u0012\u0006\u0002\n\u0005d93aA!@\u0013\u0001\u0011\u00154C\u0002C2\u0007\u0003\t\t0\u0001\u0003oC6,\u0017AA5e\u0003\u0011\u0019\u0018N_3*\u000b9\u000bi!\u001c+\u0003\r\tKg.\u0019:z')\ti!!*\u0002j\n\r%\u0011R\u0001\u0006]\u0006lW\r\t\u000b\u0005\to\"I\b\u0005\u0003\u0002l\u00065\u0001B\u0003C4\u0003'\u0001\n\u00111\u0001\u0003h\u0005\u0019\u0011\u000e\u001a\u0011\u0016\u0005\u0011}\u0004CBB\u0012\t\u0003\u001bi#\u0003\u0003\u0005\u0004\u000e\u0015\"\u0001D*qCJ\u001cXMV3di>\u0014H\u0003\u0002C<\t\u000fC!\u0002b\u001a\u0002\u001cA\u0005\t\u0019\u0001B4+\t!YI\u000b\u0003\u0003h\rmC\u0003\u0002BV\t\u001fC!\"a4\u0002$\u0005\u0005\t\u0019AA_)\u0011\u0011\u0019\rb%\t\u0015\u0005=\u0017qEA\u0001\u0002\u0004\u0011Y\u000b\u0006\u0003\u0003\u0018\u0012]\u0005BCAh\u0003S\t\t\u00111\u0001\u0002>R!!1\u0019CN\u0011)\ty-!\f\u0002\u0002\u0003\u0007!1\u0016\u0002\b\u0013:$XmZ3s'%i\u0017QUAu\u0005\u0007\u0013I\t\u0006\u0003\u0005$\u0012\u0015\u0006cAAv[\"IAq\r9\u0011\u0002\u0003\u0007!q\r\u000b\u0005\tG#I\u000bC\u0005\u0005hQ\u0004\n\u00111\u0001\u0003hQ!!1\u0016CW\u0011%\ty\r_A\u0001\u0002\u0004\ti\f\u0006\u0003\u0003D\u0012E\u0006\"CAhu\u0006\u0005\t\u0019\u0001BV)\u0011\u00119\n\".\t\u0013\u0005=70!AA\u0002\u0005uF\u0003\u0002Bb\tsC\u0011\"a4~\u0003\u0003\u0005\rAa+\u0003\tI+\u0017\r\\\n\n)\u0006\u0015\u0016\u0011\u001eBB\u0005\u0013#B\u0001\"1\u0005DB\u0019\u00111\u001e+\t\u0013\u0011\u001dt\u000b%AA\u0002\t\u001dD\u0003\u0002Ca\t\u000fD\u0011\u0002b\u001a\\!\u0003\u0005\rAa\u001a\u0015\t\t-F1\u001a\u0005\n\u0003\u001f|\u0016\u0011!a\u0001\u0003{#BAa1\u0005P\"I\u0011qZ1\u0002\u0002\u0003\u0007!1\u0016\u000b\u0005\u0005/#\u0019\u000eC\u0005\u0002P\n\f\t\u00111\u0001\u0002>R!!1\u0019Cl\u0011%\ty\rZA\u0001\u0002\u0004\u0011Y+\u0001\u0006wCJL\u0017M\u00197fg\u0002\"B!a>\u0005^\"9Aq\u001c\u0005A\u0002\u0005E\u0018AC3yaJ,7o]5p]R!\u0011q\u001fCr\u0011\u001d!y.\u0003a\u0001\u0003c\f1\u0001\u0014+F\u0003\r9E+R\u0001\u0003\u000bF\u000bAAU3bYB\u0019\u00111\u001e4\u0014\u000b\u0019$\t\u0010\"@\u0011\u0011\u0011MH\u0011 B4\t\u0003l!\u0001\">\u000b\t\u0011]\u0018\u0011V\u0001\beVtG/[7f\u0013\u0011!Y\u0010\">\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0017\u0007\u0005\u0003\u0005\u0000\u0016\u0015QBAC\u0001\u0015\u0011)\u0019Aa(\u0002\u0005%|\u0017\u0002\u0002BG\u000b\u0003!\"\u0001\"<\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\t\u0011\u0005WQ\u0002\u0005\n\tOJ\u0007\u0013!a\u0001\u0005O\nq\"\u00199qYf$C-\u001a4bk2$H%M\u0001\bk:\f\u0007\u000f\u001d7z)\u0011))\"b\u0006\u0011\r\u0005\u001d&q\u0001B4\u0011%)Ib[A\u0001\u0002\u0004!\t-A\u0002yIA\n1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\n\u0014aB%oi\u0016<WM\u001d\t\u0004\u0003W|8#B@\u0006$\u0011u\b\u0003\u0003Cz\ts\u00149\u0007b)\u0015\u0005\u0015}A\u0003\u0002CR\u000bSA!\u0002b\u001a\u0002\u0006A\u0005\t\u0019\u0001B4)\u0011))\"\"\f\t\u0015\u0015e\u0011\u0011BA\u0001\u0002\u0004!\u0019+\u0001\u0004CS:\f'/\u001f\t\u0005\u0003W\f\td\u0005\u0004\u00022\u0015UBQ \t\t\tg$IPa\u001a\u0005xQ\u0011Q\u0011\u0007\u000b\u0005\to*Y\u0004\u0003\u0006\u0005h\u0005]\u0002\u0013!a\u0001\u0005O\"B!\"\u0006\u0006@!QQ\u0011DA\u001e\u0003\u0003\u0005\r\u0001b\u001e\u0002\rI+7/\u001e7u!\u0011\tY/!\u001b\u0014\r\u0005%Tq\tC\u007f!)!\u00190\"\u0013\u0004\"\u0005]8qC\u0005\u0005\u000b\u0017\")PA\tBEN$(/Y2u\rVt7\r^5p]J\"\"!b\u0011\u0015\r\r]Q\u0011KC*\u0011!\u0019i\"a\u001cA\u0002\r\u0005\u0002\u0002CB\u001b\u0003_\u0002\r!a>\u0015\t\u0015]Sq\f\t\u0007\u0003O\u00139!\"\u0017\u0011\u0011\u0005\u001dV1LB\u0011\u0003oLA!\"\u0018\u0002*\n1A+\u001e9mKJB!\"\"\u0007\u0002r\u0005\u0005\t\u0019AB\f)\u0011)\u0019'b\u001a\u0015\t\r]QQ\r\u0005\t\u0007\u0013\u000b\u0019\bq\u0001\u0004\f\"A!qFA:\u0001\u0004\t9\u0010\u0006\u0003\u0006l\u0015=D\u0003BB\f\u000b[B\u0001b!#\u0002v\u0001\u000f11\u0012\u0005\t\u0005_\t)\b1\u0001\u0002x\u0002"
)
public class LinearProgram {
   private volatile LTE$ LTE$module;
   private volatile GTE$ GTE$module;
   private volatile EQ$ EQ$module;
   private volatile Real$ Real$module;
   private volatile Integer$ Integer$module;
   private volatile Binary$ Binary$module;
   private volatile Result$ Result$module;
   private int _nextId = 0;
   private final ArrayBuffer breeze$optimize$linear$LinearProgram$$variables = new ArrayBuffer();

   public static Solver mySolver() {
      return LinearProgram$.MODULE$.mySolver();
   }

   public LTE$ LTE() {
      if (this.LTE$module == null) {
         this.LTE$lzycompute$1();
      }

      return this.LTE$module;
   }

   public GTE$ GTE() {
      if (this.GTE$module == null) {
         this.GTE$lzycompute$1();
      }

      return this.GTE$module;
   }

   public EQ$ EQ() {
      if (this.EQ$module == null) {
         this.EQ$lzycompute$1();
      }

      return this.EQ$module;
   }

   public Real$ Real() {
      if (this.Real$module == null) {
         this.Real$lzycompute$1();
      }

      return this.Real$module;
   }

   public Integer$ Integer() {
      if (this.Integer$module == null) {
         this.Integer$lzycompute$1();
      }

      return this.Integer$module;
   }

   public Binary$ Binary() {
      if (this.Binary$module == null) {
         this.Binary$lzycompute$1();
      }

      return this.Binary$module;
   }

   public Result$ Result() {
      if (this.Result$module == null) {
         this.Result$lzycompute$1();
      }

      return this.Result$module;
   }

   private int _nextId() {
      return this._nextId;
   }

   private void _nextId_$eq(final int x$1) {
      this._nextId = x$1;
   }

   public int breeze$optimize$linear$LinearProgram$$nextId() {
      this._nextId_$eq(this._nextId() + 1);
      return this._nextId() - 1;
   }

   public ArrayBuffer breeze$optimize$linear$LinearProgram$$variables() {
      return this.breeze$optimize$linear$LinearProgram$$variables;
   }

   public Problem minimize(final Expression expression) {
      return new Problem(expression) {
         // $FF: synthetic field
         private final LinearProgram $outer;
         private final Expression expression$1;

         public Problem subjectTo(final Seq constraints) {
            return LinearProgram.Problem.super.subjectTo(constraints);
         }

         public Result solve(final Solver solver) {
            return LinearProgram.Problem.super.solve(solver);
         }

         public String toString() {
            return LinearProgram.Problem.super.toString();
         }

         public Option goal() {
            return .MODULE$.apply(GoalType.MINIMIZE);
         }

         public Expression objective() {
            return this.expression$1.objective();
         }

         public IndexedSeq constraints() {
            return this.expression$1.constraints();
         }

         // $FF: synthetic method
         public LinearProgram breeze$optimize$linear$LinearProgram$Problem$$$outer() {
            return this.$outer;
         }

         public {
            if (LinearProgram.this == null) {
               throw null;
            } else {
               this.$outer = LinearProgram.this;
               this.expression$1 = expression$1;
               LinearProgram.Problem.$init$(this);
            }
         }
      };
   }

   public Problem maximize(final Expression expression) {
      return new Problem(expression) {
         // $FF: synthetic field
         private final LinearProgram $outer;
         private final Expression expression$2;

         public Problem subjectTo(final Seq constraints) {
            return LinearProgram.Problem.super.subjectTo(constraints);
         }

         public Result solve(final Solver solver) {
            return LinearProgram.Problem.super.solve(solver);
         }

         public String toString() {
            return LinearProgram.Problem.super.toString();
         }

         public Option goal() {
            return .MODULE$.apply(GoalType.MAXIMIZE);
         }

         public Expression objective() {
            return this.expression$2.objective();
         }

         public IndexedSeq constraints() {
            return this.expression$2.constraints();
         }

         // $FF: synthetic method
         public LinearProgram breeze$optimize$linear$LinearProgram$Problem$$$outer() {
            return this.$outer;
         }

         public {
            if (LinearProgram.this == null) {
               throw null;
            } else {
               this.$outer = LinearProgram.this;
               this.expression$2 = expression$2;
               LinearProgram.Problem.$init$(this);
            }
         }
      };
   }

   public Result maximize(final Problem objective, final Solver solver) {
      scala.Predef..MODULE$.assume(!objective.goal().contains(GoalType.MINIMIZE), () -> "Cannot call maximize on a minimization problem");
      return solver.maximize(this, objective);
   }

   public Result minimize(final Problem objective, final Solver solver) {
      scala.Predef..MODULE$.assume(!objective.goal().contains(GoalType.MAXIMIZE), () -> "Cannot call minimize on a maximization problem");
      return solver.minimize(this, objective);
   }

   private final void LTE$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.LTE$module == null) {
            this.LTE$module = new LTE$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void GTE$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.GTE$module == null) {
            this.GTE$module = new GTE$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void EQ$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.EQ$module == null) {
            this.EQ$module = new EQ$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void Real$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Real$module == null) {
            this.Real$module = new Real$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void Integer$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Integer$module == null) {
            this.Integer$module = new Integer$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void Binary$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Binary$module == null) {
            this.Binary$module = new Binary$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void Result$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Result$module == null) {
            this.Result$module = new Result$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public interface Problem {
      default Option goal() {
         return scala.None..MODULE$;
      }

      Expression objective();

      IndexedSeq constraints();

      default Problem subjectTo(final Seq constraints) {
         return new Problem(constraints) {
            // $FF: synthetic field
            private final Problem $outer;
            private final Seq cons$1;

            public Problem subjectTo(final Seq constraints) {
               return LinearProgram.Problem.super.subjectTo(constraints);
            }

            public Result solve(final Solver solver) {
               return LinearProgram.Problem.super.solve(solver);
            }

            public String toString() {
               return LinearProgram.Problem.super.toString();
            }

            public Option goal() {
               return this.$outer.goal();
            }

            public Expression objective() {
               return this.$outer.objective();
            }

            public IndexedSeq constraints() {
               return (IndexedSeq)this.$outer.constraints().$plus$plus(this.cons$1);
            }

            // $FF: synthetic method
            public LinearProgram breeze$optimize$linear$LinearProgram$Problem$$$outer() {
               return this.$outer.breeze$optimize$linear$LinearProgram$Problem$$$outer();
            }

            public {
               if (Problem.this == null) {
                  throw null;
               } else {
                  this.$outer = Problem.this;
                  this.cons$1 = cons$1;
                  LinearProgram.Problem.$init$(this);
               }
            }
         };
      }

      default Result solve(final Solver solver) {
         Result var10000;
         label38: {
            GoalType _goal = (GoalType)this.goal().getOrElse(() -> {
               throw new IllegalArgumentException("Goal is not defined.");
            });
            GoalType var3 = GoalType.MAXIMIZE;
            if (_goal == null) {
               if (var3 == null) {
                  break label38;
               }
            } else if (_goal.equals(var3)) {
               break label38;
            }

            GoalType var4 = GoalType.MINIMIZE;
            if (_goal == null) {
               if (var4 != null) {
                  throw new IllegalArgumentException((new StringBuilder(13)).append("Unknown goal ").append(_goal.name()).toString());
               }
            } else if (!_goal.equals(var4)) {
               throw new IllegalArgumentException((new StringBuilder(13)).append("Unknown goal ").append(_goal.name()).toString());
            }

            var10000 = solver.minimize(this.breeze$optimize$linear$LinearProgram$Problem$$$outer(), this);
            return var10000;
         }

         var10000 = solver.maximize(this.breeze$optimize$linear$LinearProgram$Problem$$$outer(), this);
         return var10000;
      }

      default String toString() {
         Option var3 = this.goal();
         String _goal;
         if (var3 instanceof Some) {
            Some var4 = (Some)var3;
            GoalType g = (GoalType)var4.value();
            _goal = g.name().toLowerCase();
         } else {
            _goal = "problem ";
         }

         String beg = "\nsubject to";
         String sep = "\n          ";
         return (new StringBuilder(0)).append((new StringBuilder(4)).append(_goal).append("    ").toString()).append(this.objective()).append(this.constraints().nonEmpty() ? this.constraints().mkString(beg, sep, "") : "").toString();
      }

      // $FF: synthetic method
      LinearProgram breeze$optimize$linear$LinearProgram$Problem$$$outer();

      static void $init$(final Problem $this) {
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public interface Expression extends Problem {
      Vector coefficients();

      default double scalarComponent() {
         return (double)0.0F;
      }

      default Expression objective() {
         return this;
      }

      default IndexedSeq constraints() {
         return (IndexedSeq)scala.package..MODULE$.IndexedSeq().empty();
      }

      default Expression $plus(final Expression other) {
         return new Expression(other) {
            // $FF: synthetic field
            private final Expression $outer;
            private final Expression other$1;

            public Expression objective() {
               return LinearProgram.Expression.super.objective();
            }

            public IndexedSeq constraints() {
               return LinearProgram.Expression.super.constraints();
            }

            public Expression $plus(final Expression other) {
               return LinearProgram.Expression.super.$plus(other);
            }

            public Expression $plus(final double other) {
               return LinearProgram.Expression.super.$plus(other);
            }

            public Expression $minus(final Expression other) {
               return LinearProgram.Expression.super.$minus(other);
            }

            public Expression $minus(final double other) {
               return LinearProgram.Expression.super.$minus(other);
            }

            public Expression unary_$minus() {
               return LinearProgram.Expression.super.unary_$minus();
            }

            public Constraint $less$eq(final Expression rhs_) {
               return LinearProgram.Expression.super.$less$eq(rhs_);
            }

            public Constraint $less$eq(final double c) {
               return LinearProgram.Expression.super.$less$eq(c);
            }

            public Constraint $greater$eq(final Expression rhs_) {
               return LinearProgram.Expression.super.$greater$eq(rhs_);
            }

            public Constraint $greater$eq(final double c) {
               return LinearProgram.Expression.super.$greater$eq(c);
            }

            public Constraint $eq$colon$eq(final Expression rhs_) {
               return LinearProgram.Expression.super.$eq$colon$eq(rhs_);
            }

            public Constraint $eq$colon$eq(final double c) {
               return LinearProgram.Expression.super.$eq$colon$eq(c);
            }

            public Expression $times(final double c) {
               return LinearProgram.Expression.super.$times(c);
            }

            public Expression $times$colon(final double c) {
               return LinearProgram.Expression.super.$times$colon(c);
            }

            public Option goal() {
               return LinearProgram.Problem.super.goal();
            }

            public Problem subjectTo(final Seq constraints) {
               return LinearProgram.Problem.super.subjectTo(constraints);
            }

            public Result solve(final Solver solver) {
               return LinearProgram.Problem.super.solve(solver);
            }

            public Vector coefficients() {
               return (Vector)this.$outer.coefficients().$plus(this.other$1.coefficients(), HasOps$.MODULE$.impl_Op_V_V_eq_V_idempotent_Double_OpAdd());
            }

            public double scalarComponent() {
               return this.$outer.scalarComponent() + this.other$1.scalarComponent();
            }

            public String toString() {
               return (new StringBuilder(3)).append(this.$outer.toString()).append(" + ").append(this.other$1).toString();
            }

            // $FF: synthetic method
            public LinearProgram breeze$optimize$linear$LinearProgram$Expression$$$outer() {
               return this.$outer.breeze$optimize$linear$LinearProgram$Expression$$$outer();
            }

            // $FF: synthetic method
            public LinearProgram breeze$optimize$linear$LinearProgram$Problem$$$outer() {
               return this.$outer.breeze$optimize$linear$LinearProgram$Expression$$$outer();
            }

            public {
               if (Expression.this == null) {
                  throw null;
               } else {
                  this.$outer = Expression.this;
                  this.other$1 = other$1;
                  LinearProgram.Problem.$init$(this);
                  LinearProgram.Expression.$init$(this);
               }
            }
         };
      }

      default Expression $plus(final double other) {
         return new Expression(other) {
            // $FF: synthetic field
            private final Expression $outer;
            private final double other$2;

            public Expression objective() {
               return LinearProgram.Expression.super.objective();
            }

            public IndexedSeq constraints() {
               return LinearProgram.Expression.super.constraints();
            }

            public Expression $plus(final Expression other) {
               return LinearProgram.Expression.super.$plus(other);
            }

            public Expression $plus(final double other) {
               return LinearProgram.Expression.super.$plus(other);
            }

            public Expression $minus(final Expression other) {
               return LinearProgram.Expression.super.$minus(other);
            }

            public Expression $minus(final double other) {
               return LinearProgram.Expression.super.$minus(other);
            }

            public Expression unary_$minus() {
               return LinearProgram.Expression.super.unary_$minus();
            }

            public Constraint $less$eq(final Expression rhs_) {
               return LinearProgram.Expression.super.$less$eq(rhs_);
            }

            public Constraint $less$eq(final double c) {
               return LinearProgram.Expression.super.$less$eq(c);
            }

            public Constraint $greater$eq(final Expression rhs_) {
               return LinearProgram.Expression.super.$greater$eq(rhs_);
            }

            public Constraint $greater$eq(final double c) {
               return LinearProgram.Expression.super.$greater$eq(c);
            }

            public Constraint $eq$colon$eq(final Expression rhs_) {
               return LinearProgram.Expression.super.$eq$colon$eq(rhs_);
            }

            public Constraint $eq$colon$eq(final double c) {
               return LinearProgram.Expression.super.$eq$colon$eq(c);
            }

            public Expression $times(final double c) {
               return LinearProgram.Expression.super.$times(c);
            }

            public Expression $times$colon(final double c) {
               return LinearProgram.Expression.super.$times$colon(c);
            }

            public Option goal() {
               return LinearProgram.Problem.super.goal();
            }

            public Problem subjectTo(final Seq constraints) {
               return LinearProgram.Problem.super.subjectTo(constraints);
            }

            public Result solve(final Solver solver) {
               return LinearProgram.Problem.super.solve(solver);
            }

            public Vector coefficients() {
               return this.$outer.coefficients();
            }

            public double scalarComponent() {
               return this.$outer.scalarComponent() + this.other$2;
            }

            public String toString() {
               return (new StringBuilder(3)).append(this.$outer.toString()).append(" + ").append(this.other$2).toString();
            }

            // $FF: synthetic method
            public LinearProgram breeze$optimize$linear$LinearProgram$Expression$$$outer() {
               return this.$outer.breeze$optimize$linear$LinearProgram$Expression$$$outer();
            }

            // $FF: synthetic method
            public LinearProgram breeze$optimize$linear$LinearProgram$Problem$$$outer() {
               return this.$outer.breeze$optimize$linear$LinearProgram$Expression$$$outer();
            }

            public {
               if (Expression.this == null) {
                  throw null;
               } else {
                  this.$outer = Expression.this;
                  this.other$2 = other$2;
                  LinearProgram.Problem.$init$(this);
                  LinearProgram.Expression.$init$(this);
               }
            }
         };
      }

      default Expression $minus(final Expression other) {
         return new Expression(other) {
            // $FF: synthetic field
            private final Expression $outer;
            private final Expression other$3;

            public Expression objective() {
               return LinearProgram.Expression.super.objective();
            }

            public IndexedSeq constraints() {
               return LinearProgram.Expression.super.constraints();
            }

            public Expression $plus(final Expression other) {
               return LinearProgram.Expression.super.$plus(other);
            }

            public Expression $plus(final double other) {
               return LinearProgram.Expression.super.$plus(other);
            }

            public Expression $minus(final Expression other) {
               return LinearProgram.Expression.super.$minus(other);
            }

            public Expression $minus(final double other) {
               return LinearProgram.Expression.super.$minus(other);
            }

            public Expression unary_$minus() {
               return LinearProgram.Expression.super.unary_$minus();
            }

            public Constraint $less$eq(final Expression rhs_) {
               return LinearProgram.Expression.super.$less$eq(rhs_);
            }

            public Constraint $less$eq(final double c) {
               return LinearProgram.Expression.super.$less$eq(c);
            }

            public Constraint $greater$eq(final Expression rhs_) {
               return LinearProgram.Expression.super.$greater$eq(rhs_);
            }

            public Constraint $greater$eq(final double c) {
               return LinearProgram.Expression.super.$greater$eq(c);
            }

            public Constraint $eq$colon$eq(final Expression rhs_) {
               return LinearProgram.Expression.super.$eq$colon$eq(rhs_);
            }

            public Constraint $eq$colon$eq(final double c) {
               return LinearProgram.Expression.super.$eq$colon$eq(c);
            }

            public Expression $times(final double c) {
               return LinearProgram.Expression.super.$times(c);
            }

            public Expression $times$colon(final double c) {
               return LinearProgram.Expression.super.$times$colon(c);
            }

            public Option goal() {
               return LinearProgram.Problem.super.goal();
            }

            public Problem subjectTo(final Seq constraints) {
               return LinearProgram.Problem.super.subjectTo(constraints);
            }

            public Result solve(final Solver solver) {
               return LinearProgram.Problem.super.solve(solver);
            }

            public Vector coefficients() {
               return (Vector)this.$outer.coefficients().$minus(this.other$3.coefficients(), HasOps$.MODULE$.impl_Op_V_V_eq_V_idempotent_Double_OpSub());
            }

            public double scalarComponent() {
               return this.$outer.scalarComponent() - this.other$3.scalarComponent();
            }

            public String toString() {
               return (new StringBuilder(3)).append(this.$outer.toString()).append(" - ").append(this.other$3).toString();
            }

            // $FF: synthetic method
            public LinearProgram breeze$optimize$linear$LinearProgram$Expression$$$outer() {
               return this.$outer.breeze$optimize$linear$LinearProgram$Expression$$$outer();
            }

            // $FF: synthetic method
            public LinearProgram breeze$optimize$linear$LinearProgram$Problem$$$outer() {
               return this.$outer.breeze$optimize$linear$LinearProgram$Expression$$$outer();
            }

            public {
               if (Expression.this == null) {
                  throw null;
               } else {
                  this.$outer = Expression.this;
                  this.other$3 = other$3;
                  LinearProgram.Problem.$init$(this);
                  LinearProgram.Expression.$init$(this);
               }
            }
         };
      }

      default Expression $minus(final double other) {
         return new Expression(other) {
            // $FF: synthetic field
            private final Expression $outer;
            private final double other$4;

            public Expression objective() {
               return LinearProgram.Expression.super.objective();
            }

            public IndexedSeq constraints() {
               return LinearProgram.Expression.super.constraints();
            }

            public Expression $plus(final Expression other) {
               return LinearProgram.Expression.super.$plus(other);
            }

            public Expression $plus(final double other) {
               return LinearProgram.Expression.super.$plus(other);
            }

            public Expression $minus(final Expression other) {
               return LinearProgram.Expression.super.$minus(other);
            }

            public Expression $minus(final double other) {
               return LinearProgram.Expression.super.$minus(other);
            }

            public Expression unary_$minus() {
               return LinearProgram.Expression.super.unary_$minus();
            }

            public Constraint $less$eq(final Expression rhs_) {
               return LinearProgram.Expression.super.$less$eq(rhs_);
            }

            public Constraint $less$eq(final double c) {
               return LinearProgram.Expression.super.$less$eq(c);
            }

            public Constraint $greater$eq(final Expression rhs_) {
               return LinearProgram.Expression.super.$greater$eq(rhs_);
            }

            public Constraint $greater$eq(final double c) {
               return LinearProgram.Expression.super.$greater$eq(c);
            }

            public Constraint $eq$colon$eq(final Expression rhs_) {
               return LinearProgram.Expression.super.$eq$colon$eq(rhs_);
            }

            public Constraint $eq$colon$eq(final double c) {
               return LinearProgram.Expression.super.$eq$colon$eq(c);
            }

            public Expression $times(final double c) {
               return LinearProgram.Expression.super.$times(c);
            }

            public Expression $times$colon(final double c) {
               return LinearProgram.Expression.super.$times$colon(c);
            }

            public Option goal() {
               return LinearProgram.Problem.super.goal();
            }

            public Problem subjectTo(final Seq constraints) {
               return LinearProgram.Problem.super.subjectTo(constraints);
            }

            public Result solve(final Solver solver) {
               return LinearProgram.Problem.super.solve(solver);
            }

            public Vector coefficients() {
               return this.$outer.coefficients();
            }

            public double scalarComponent() {
               return this.$outer.scalarComponent() - this.other$4;
            }

            public String toString() {
               return (new StringBuilder(3)).append(this.$outer.toString()).append(" - ").append(this.other$4).toString();
            }

            // $FF: synthetic method
            public LinearProgram breeze$optimize$linear$LinearProgram$Expression$$$outer() {
               return this.$outer.breeze$optimize$linear$LinearProgram$Expression$$$outer();
            }

            // $FF: synthetic method
            public LinearProgram breeze$optimize$linear$LinearProgram$Problem$$$outer() {
               return this.$outer.breeze$optimize$linear$LinearProgram$Expression$$$outer();
            }

            public {
               if (Expression.this == null) {
                  throw null;
               } else {
                  this.$outer = Expression.this;
                  this.other$4 = other$4;
                  LinearProgram.Problem.$init$(this);
                  LinearProgram.Expression.$init$(this);
               }
            }
         };
      }

      default Expression unary_$minus() {
         return new Expression() {
            // $FF: synthetic field
            private final Expression $outer;

            public Expression objective() {
               return LinearProgram.Expression.super.objective();
            }

            public IndexedSeq constraints() {
               return LinearProgram.Expression.super.constraints();
            }

            public Expression $plus(final Expression other) {
               return LinearProgram.Expression.super.$plus(other);
            }

            public Expression $plus(final double other) {
               return LinearProgram.Expression.super.$plus(other);
            }

            public Expression $minus(final Expression other) {
               return LinearProgram.Expression.super.$minus(other);
            }

            public Expression $minus(final double other) {
               return LinearProgram.Expression.super.$minus(other);
            }

            public Expression unary_$minus() {
               return LinearProgram.Expression.super.unary_$minus();
            }

            public Constraint $less$eq(final Expression rhs_) {
               return LinearProgram.Expression.super.$less$eq(rhs_);
            }

            public Constraint $less$eq(final double c) {
               return LinearProgram.Expression.super.$less$eq(c);
            }

            public Constraint $greater$eq(final Expression rhs_) {
               return LinearProgram.Expression.super.$greater$eq(rhs_);
            }

            public Constraint $greater$eq(final double c) {
               return LinearProgram.Expression.super.$greater$eq(c);
            }

            public Constraint $eq$colon$eq(final Expression rhs_) {
               return LinearProgram.Expression.super.$eq$colon$eq(rhs_);
            }

            public Constraint $eq$colon$eq(final double c) {
               return LinearProgram.Expression.super.$eq$colon$eq(c);
            }

            public Expression $times(final double c) {
               return LinearProgram.Expression.super.$times(c);
            }

            public Expression $times$colon(final double c) {
               return LinearProgram.Expression.super.$times$colon(c);
            }

            public Option goal() {
               return LinearProgram.Problem.super.goal();
            }

            public Problem subjectTo(final Seq constraints) {
               return LinearProgram.Problem.super.subjectTo(constraints);
            }

            public Result solve(final Solver solver) {
               return LinearProgram.Problem.super.solve(solver);
            }

            public Vector coefficients() {
               return (Vector)this.$outer.coefficients().$times(BoxesRunTime.boxToDouble((double)-1.0F), HasOps$.MODULE$.impl_Op_V_S_eq_V_Double_OpMulMatrix());
            }

            public double scalarComponent() {
               return -this.$outer.scalarComponent();
            }

            public String toString() {
               return (new StringBuilder(3)).append("-(").append(this.$outer).append(")").toString();
            }

            // $FF: synthetic method
            public LinearProgram breeze$optimize$linear$LinearProgram$Expression$$$outer() {
               return this.$outer.breeze$optimize$linear$LinearProgram$Expression$$$outer();
            }

            // $FF: synthetic method
            public LinearProgram breeze$optimize$linear$LinearProgram$Problem$$$outer() {
               return this.$outer.breeze$optimize$linear$LinearProgram$Expression$$$outer();
            }

            public {
               if (Expression.this == null) {
                  throw null;
               } else {
                  this.$outer = Expression.this;
                  LinearProgram.Problem.$init$(this);
                  LinearProgram.Expression.$init$(this);
               }
            }
         };
      }

      default Constraint $less$eq(final Expression rhs_) {
         return new Constraint(rhs_) {
            // $FF: synthetic field
            private final Expression $outer;
            private final Expression rhs_$1;

            public String toString() {
               return LinearProgram.Constraint.super.toString();
            }

            public Constraint standardize() {
               return LinearProgram.Constraint.super.standardize();
            }

            public Relation relation() {
               return this.$outer.breeze$optimize$linear$LinearProgram$Expression$$$outer().LTE();
            }

            public Expression lhs() {
               return this.$outer;
            }

            public Expression rhs() {
               return this.rhs_$1;
            }

            // $FF: synthetic method
            public LinearProgram breeze$optimize$linear$LinearProgram$Constraint$$$outer() {
               return this.$outer.breeze$optimize$linear$LinearProgram$Expression$$$outer();
            }

            public {
               if (Expression.this == null) {
                  throw null;
               } else {
                  this.$outer = Expression.this;
                  this.rhs_$1 = rhs_$1;
                  LinearProgram.Constraint.$init$(this);
               }
            }
         };
      }

      default Constraint $less$eq(final double c) {
         return new Constraint(c) {
            // $FF: synthetic field
            private final Expression $outer;
            public final double c$1;

            public String toString() {
               return LinearProgram.Constraint.super.toString();
            }

            public Constraint standardize() {
               return LinearProgram.Constraint.super.standardize();
            }

            public Relation relation() {
               return this.$outer.breeze$optimize$linear$LinearProgram$Expression$$$outer().LTE();
            }

            public Expression lhs() {
               return this.$outer;
            }

            public Expression rhs() {
               return new Expression() {
                  // $FF: synthetic field
                  private final <undefinedtype> $outer;

                  public Expression objective() {
                     return LinearProgram.Expression.super.objective();
                  }

                  public IndexedSeq constraints() {
                     return LinearProgram.Expression.super.constraints();
                  }

                  public Expression $plus(final Expression other) {
                     return LinearProgram.Expression.super.$plus(other);
                  }

                  public Expression $plus(final double other) {
                     return LinearProgram.Expression.super.$plus(other);
                  }

                  public Expression $minus(final Expression other) {
                     return LinearProgram.Expression.super.$minus(other);
                  }

                  public Expression $minus(final double other) {
                     return LinearProgram.Expression.super.$minus(other);
                  }

                  public Expression unary_$minus() {
                     return LinearProgram.Expression.super.unary_$minus();
                  }

                  public Constraint $less$eq(final Expression rhs_) {
                     return LinearProgram.Expression.super.$less$eq(rhs_);
                  }

                  public Constraint $less$eq(final double c) {
                     return LinearProgram.Expression.super.$less$eq(c);
                  }

                  public Constraint $greater$eq(final Expression rhs_) {
                     return LinearProgram.Expression.super.$greater$eq(rhs_);
                  }

                  public Constraint $greater$eq(final double c) {
                     return LinearProgram.Expression.super.$greater$eq(c);
                  }

                  public Constraint $eq$colon$eq(final Expression rhs_) {
                     return LinearProgram.Expression.super.$eq$colon$eq(rhs_);
                  }

                  public Constraint $eq$colon$eq(final double c) {
                     return LinearProgram.Expression.super.$eq$colon$eq(c);
                  }

                  public Expression $times(final double c) {
                     return LinearProgram.Expression.super.$times(c);
                  }

                  public Expression $times$colon(final double c) {
                     return LinearProgram.Expression.super.$times$colon(c);
                  }

                  public Option goal() {
                     return LinearProgram.Problem.super.goal();
                  }

                  public Problem subjectTo(final Seq constraints) {
                     return LinearProgram.Problem.super.subjectTo(constraints);
                  }

                  public Result solve(final Solver solver) {
                     return LinearProgram.Problem.super.solve(solver);
                  }

                  public SparseVector coefficients() {
                     return SparseVector$.MODULE$.zeros$mDc$sp(this.$outer.breeze$optimize$linear$LinearProgram$Expression$$anon$$$outer().breeze$optimize$linear$LinearProgram$Expression$$$outer().breeze$optimize$linear$LinearProgram$$variables().length(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
                  }

                  public double scalarComponent() {
                     return this.$outer.c$1;
                  }

                  public String toString() {
                     return Double.toString(this.$outer.c$1);
                  }

                  // $FF: synthetic method
                  public LinearProgram breeze$optimize$linear$LinearProgram$Expression$$$outer() {
                     return this.$outer.breeze$optimize$linear$LinearProgram$Expression$$anon$$$outer().breeze$optimize$linear$LinearProgram$Expression$$$outer();
                  }

                  // $FF: synthetic method
                  public LinearProgram breeze$optimize$linear$LinearProgram$Problem$$$outer() {
                     return this.$outer.breeze$optimize$linear$LinearProgram$Expression$$anon$$$outer().breeze$optimize$linear$LinearProgram$Expression$$$outer();
                  }

                  public {
                     if (<VAR_NAMELESS_ENCLOSURE> == null) {
                        throw null;
                     } else {
                        this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                        LinearProgram.Problem.$init$(this);
                        LinearProgram.Expression.$init$(this);
                     }
                  }
               };
            }

            // $FF: synthetic method
            public Expression breeze$optimize$linear$LinearProgram$Expression$$anon$$$outer() {
               return this.$outer;
            }

            // $FF: synthetic method
            public LinearProgram breeze$optimize$linear$LinearProgram$Constraint$$$outer() {
               return this.$outer.breeze$optimize$linear$LinearProgram$Expression$$$outer();
            }

            public {
               if (Expression.this == null) {
                  throw null;
               } else {
                  this.$outer = Expression.this;
                  this.c$1 = c$1;
                  LinearProgram.Constraint.$init$(this);
               }
            }
         };
      }

      default Constraint $greater$eq(final Expression rhs_) {
         return new Constraint(rhs_) {
            // $FF: synthetic field
            private final Expression $outer;
            private final Expression rhs_$2;

            public String toString() {
               return LinearProgram.Constraint.super.toString();
            }

            public Constraint standardize() {
               return LinearProgram.Constraint.super.standardize();
            }

            public Relation relation() {
               return this.$outer.breeze$optimize$linear$LinearProgram$Expression$$$outer().GTE();
            }

            public Expression lhs() {
               return this.$outer;
            }

            public Expression rhs() {
               return this.rhs_$2;
            }

            // $FF: synthetic method
            public LinearProgram breeze$optimize$linear$LinearProgram$Constraint$$$outer() {
               return this.$outer.breeze$optimize$linear$LinearProgram$Expression$$$outer();
            }

            public {
               if (Expression.this == null) {
                  throw null;
               } else {
                  this.$outer = Expression.this;
                  this.rhs_$2 = rhs_$2;
                  LinearProgram.Constraint.$init$(this);
               }
            }
         };
      }

      default Constraint $greater$eq(final double c) {
         return new Constraint(c) {
            // $FF: synthetic field
            private final Expression $outer;
            public final double c$2;

            public String toString() {
               return LinearProgram.Constraint.super.toString();
            }

            public Constraint standardize() {
               return LinearProgram.Constraint.super.standardize();
            }

            public Relation relation() {
               return this.$outer.breeze$optimize$linear$LinearProgram$Expression$$$outer().GTE();
            }

            public Expression lhs() {
               return this.$outer;
            }

            public Expression rhs() {
               return new Expression() {
                  // $FF: synthetic field
                  private final <undefinedtype> $outer;

                  public Expression objective() {
                     return LinearProgram.Expression.super.objective();
                  }

                  public IndexedSeq constraints() {
                     return LinearProgram.Expression.super.constraints();
                  }

                  public Expression $plus(final Expression other) {
                     return LinearProgram.Expression.super.$plus(other);
                  }

                  public Expression $plus(final double other) {
                     return LinearProgram.Expression.super.$plus(other);
                  }

                  public Expression $minus(final Expression other) {
                     return LinearProgram.Expression.super.$minus(other);
                  }

                  public Expression $minus(final double other) {
                     return LinearProgram.Expression.super.$minus(other);
                  }

                  public Expression unary_$minus() {
                     return LinearProgram.Expression.super.unary_$minus();
                  }

                  public Constraint $less$eq(final Expression rhs_) {
                     return LinearProgram.Expression.super.$less$eq(rhs_);
                  }

                  public Constraint $less$eq(final double c) {
                     return LinearProgram.Expression.super.$less$eq(c);
                  }

                  public Constraint $greater$eq(final Expression rhs_) {
                     return LinearProgram.Expression.super.$greater$eq(rhs_);
                  }

                  public Constraint $greater$eq(final double c) {
                     return LinearProgram.Expression.super.$greater$eq(c);
                  }

                  public Constraint $eq$colon$eq(final Expression rhs_) {
                     return LinearProgram.Expression.super.$eq$colon$eq(rhs_);
                  }

                  public Constraint $eq$colon$eq(final double c) {
                     return LinearProgram.Expression.super.$eq$colon$eq(c);
                  }

                  public Expression $times(final double c) {
                     return LinearProgram.Expression.super.$times(c);
                  }

                  public Expression $times$colon(final double c) {
                     return LinearProgram.Expression.super.$times$colon(c);
                  }

                  public Option goal() {
                     return LinearProgram.Problem.super.goal();
                  }

                  public Problem subjectTo(final Seq constraints) {
                     return LinearProgram.Problem.super.subjectTo(constraints);
                  }

                  public Result solve(final Solver solver) {
                     return LinearProgram.Problem.super.solve(solver);
                  }

                  public SparseVector coefficients() {
                     return SparseVector$.MODULE$.zeros$mDc$sp(this.$outer.breeze$optimize$linear$LinearProgram$Expression$$anon$$$outer().breeze$optimize$linear$LinearProgram$Expression$$$outer().breeze$optimize$linear$LinearProgram$$variables().length(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
                  }

                  public double scalarComponent() {
                     return this.$outer.c$2;
                  }

                  public String toString() {
                     return Double.toString(this.$outer.c$2);
                  }

                  // $FF: synthetic method
                  public LinearProgram breeze$optimize$linear$LinearProgram$Expression$$$outer() {
                     return this.$outer.breeze$optimize$linear$LinearProgram$Expression$$anon$$$outer().breeze$optimize$linear$LinearProgram$Expression$$$outer();
                  }

                  // $FF: synthetic method
                  public LinearProgram breeze$optimize$linear$LinearProgram$Problem$$$outer() {
                     return this.$outer.breeze$optimize$linear$LinearProgram$Expression$$anon$$$outer().breeze$optimize$linear$LinearProgram$Expression$$$outer();
                  }

                  public {
                     if (<VAR_NAMELESS_ENCLOSURE> == null) {
                        throw null;
                     } else {
                        this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                        LinearProgram.Problem.$init$(this);
                        LinearProgram.Expression.$init$(this);
                     }
                  }
               };
            }

            // $FF: synthetic method
            public Expression breeze$optimize$linear$LinearProgram$Expression$$anon$$$outer() {
               return this.$outer;
            }

            // $FF: synthetic method
            public LinearProgram breeze$optimize$linear$LinearProgram$Constraint$$$outer() {
               return this.$outer.breeze$optimize$linear$LinearProgram$Expression$$$outer();
            }

            public {
               if (Expression.this == null) {
                  throw null;
               } else {
                  this.$outer = Expression.this;
                  this.c$2 = c$2;
                  LinearProgram.Constraint.$init$(this);
               }
            }
         };
      }

      default Constraint $eq$colon$eq(final Expression rhs_) {
         return new Constraint(rhs_) {
            // $FF: synthetic field
            private final Expression $outer;
            private final Expression rhs_$3;

            public String toString() {
               return LinearProgram.Constraint.super.toString();
            }

            public Constraint standardize() {
               return LinearProgram.Constraint.super.standardize();
            }

            public Relation relation() {
               return this.$outer.breeze$optimize$linear$LinearProgram$Expression$$$outer().EQ();
            }

            public Expression lhs() {
               return this.$outer;
            }

            public Expression rhs() {
               return this.rhs_$3;
            }

            // $FF: synthetic method
            public LinearProgram breeze$optimize$linear$LinearProgram$Constraint$$$outer() {
               return this.$outer.breeze$optimize$linear$LinearProgram$Expression$$$outer();
            }

            public {
               if (Expression.this == null) {
                  throw null;
               } else {
                  this.$outer = Expression.this;
                  this.rhs_$3 = rhs_$3;
                  LinearProgram.Constraint.$init$(this);
               }
            }
         };
      }

      default Constraint $eq$colon$eq(final double c) {
         return new Constraint(c) {
            // $FF: synthetic field
            private final Expression $outer;
            public final double c$3;

            public String toString() {
               return LinearProgram.Constraint.super.toString();
            }

            public Constraint standardize() {
               return LinearProgram.Constraint.super.standardize();
            }

            public Relation relation() {
               return this.$outer.breeze$optimize$linear$LinearProgram$Expression$$$outer().EQ();
            }

            public Expression lhs() {
               return this.$outer;
            }

            public Expression rhs() {
               return new Expression() {
                  // $FF: synthetic field
                  private final <undefinedtype> $outer;

                  public Expression objective() {
                     return LinearProgram.Expression.super.objective();
                  }

                  public IndexedSeq constraints() {
                     return LinearProgram.Expression.super.constraints();
                  }

                  public Expression $plus(final Expression other) {
                     return LinearProgram.Expression.super.$plus(other);
                  }

                  public Expression $plus(final double other) {
                     return LinearProgram.Expression.super.$plus(other);
                  }

                  public Expression $minus(final Expression other) {
                     return LinearProgram.Expression.super.$minus(other);
                  }

                  public Expression $minus(final double other) {
                     return LinearProgram.Expression.super.$minus(other);
                  }

                  public Expression unary_$minus() {
                     return LinearProgram.Expression.super.unary_$minus();
                  }

                  public Constraint $less$eq(final Expression rhs_) {
                     return LinearProgram.Expression.super.$less$eq(rhs_);
                  }

                  public Constraint $less$eq(final double c) {
                     return LinearProgram.Expression.super.$less$eq(c);
                  }

                  public Constraint $greater$eq(final Expression rhs_) {
                     return LinearProgram.Expression.super.$greater$eq(rhs_);
                  }

                  public Constraint $greater$eq(final double c) {
                     return LinearProgram.Expression.super.$greater$eq(c);
                  }

                  public Constraint $eq$colon$eq(final Expression rhs_) {
                     return LinearProgram.Expression.super.$eq$colon$eq(rhs_);
                  }

                  public Constraint $eq$colon$eq(final double c) {
                     return LinearProgram.Expression.super.$eq$colon$eq(c);
                  }

                  public Expression $times(final double c) {
                     return LinearProgram.Expression.super.$times(c);
                  }

                  public Expression $times$colon(final double c) {
                     return LinearProgram.Expression.super.$times$colon(c);
                  }

                  public Option goal() {
                     return LinearProgram.Problem.super.goal();
                  }

                  public Problem subjectTo(final Seq constraints) {
                     return LinearProgram.Problem.super.subjectTo(constraints);
                  }

                  public Result solve(final Solver solver) {
                     return LinearProgram.Problem.super.solve(solver);
                  }

                  public SparseVector coefficients() {
                     return SparseVector$.MODULE$.zeros$mDc$sp(this.$outer.breeze$optimize$linear$LinearProgram$Expression$$anon$$$outer().breeze$optimize$linear$LinearProgram$Expression$$$outer().breeze$optimize$linear$LinearProgram$$variables().length(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
                  }

                  public double scalarComponent() {
                     return this.$outer.c$3;
                  }

                  public String toString() {
                     return Double.toString(this.$outer.c$3);
                  }

                  // $FF: synthetic method
                  public LinearProgram breeze$optimize$linear$LinearProgram$Expression$$$outer() {
                     return this.$outer.breeze$optimize$linear$LinearProgram$Expression$$anon$$$outer().breeze$optimize$linear$LinearProgram$Expression$$$outer();
                  }

                  // $FF: synthetic method
                  public LinearProgram breeze$optimize$linear$LinearProgram$Problem$$$outer() {
                     return this.$outer.breeze$optimize$linear$LinearProgram$Expression$$anon$$$outer().breeze$optimize$linear$LinearProgram$Expression$$$outer();
                  }

                  public {
                     if (<VAR_NAMELESS_ENCLOSURE> == null) {
                        throw null;
                     } else {
                        this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                        LinearProgram.Problem.$init$(this);
                        LinearProgram.Expression.$init$(this);
                     }
                  }
               };
            }

            // $FF: synthetic method
            public Expression breeze$optimize$linear$LinearProgram$Expression$$anon$$$outer() {
               return this.$outer;
            }

            // $FF: synthetic method
            public LinearProgram breeze$optimize$linear$LinearProgram$Constraint$$$outer() {
               return this.$outer.breeze$optimize$linear$LinearProgram$Expression$$$outer();
            }

            public {
               if (Expression.this == null) {
                  throw null;
               } else {
                  this.$outer = Expression.this;
                  this.c$3 = c$3;
                  LinearProgram.Constraint.$init$(this);
               }
            }
         };
      }

      default Expression $times(final double c) {
         return new Expression(c) {
            // $FF: synthetic field
            private final Expression $outer;
            private final double c$4;

            public Expression objective() {
               return LinearProgram.Expression.super.objective();
            }

            public IndexedSeq constraints() {
               return LinearProgram.Expression.super.constraints();
            }

            public Expression $plus(final Expression other) {
               return LinearProgram.Expression.super.$plus(other);
            }

            public Expression $plus(final double other) {
               return LinearProgram.Expression.super.$plus(other);
            }

            public Expression $minus(final Expression other) {
               return LinearProgram.Expression.super.$minus(other);
            }

            public Expression $minus(final double other) {
               return LinearProgram.Expression.super.$minus(other);
            }

            public Expression unary_$minus() {
               return LinearProgram.Expression.super.unary_$minus();
            }

            public Constraint $less$eq(final Expression rhs_) {
               return LinearProgram.Expression.super.$less$eq(rhs_);
            }

            public Constraint $less$eq(final double c) {
               return LinearProgram.Expression.super.$less$eq(c);
            }

            public Constraint $greater$eq(final Expression rhs_) {
               return LinearProgram.Expression.super.$greater$eq(rhs_);
            }

            public Constraint $greater$eq(final double c) {
               return LinearProgram.Expression.super.$greater$eq(c);
            }

            public Constraint $eq$colon$eq(final Expression rhs_) {
               return LinearProgram.Expression.super.$eq$colon$eq(rhs_);
            }

            public Constraint $eq$colon$eq(final double c) {
               return LinearProgram.Expression.super.$eq$colon$eq(c);
            }

            public Expression $times(final double c) {
               return LinearProgram.Expression.super.$times(c);
            }

            public Expression $times$colon(final double c) {
               return LinearProgram.Expression.super.$times$colon(c);
            }

            public Option goal() {
               return LinearProgram.Problem.super.goal();
            }

            public Problem subjectTo(final Seq constraints) {
               return LinearProgram.Problem.super.subjectTo(constraints);
            }

            public Result solve(final Solver solver) {
               return LinearProgram.Problem.super.solve(solver);
            }

            public Vector coefficients() {
               return (Vector)this.$outer.coefficients().$times(BoxesRunTime.boxToDouble(this.c$4), HasOps$.MODULE$.impl_Op_V_S_eq_V_Double_OpMulMatrix());
            }

            public double scalarComponent() {
               return this.$outer.scalarComponent() * this.c$4;
            }

            public String toString() {
               return (new StringBuilder(5)).append("(").append(this.$outer).append(") * ").append(this.c$4).toString();
            }

            // $FF: synthetic method
            public LinearProgram breeze$optimize$linear$LinearProgram$Expression$$$outer() {
               return this.$outer.breeze$optimize$linear$LinearProgram$Expression$$$outer();
            }

            // $FF: synthetic method
            public LinearProgram breeze$optimize$linear$LinearProgram$Problem$$$outer() {
               return this.$outer.breeze$optimize$linear$LinearProgram$Expression$$$outer();
            }

            public {
               if (Expression.this == null) {
                  throw null;
               } else {
                  this.$outer = Expression.this;
                  this.c$4 = c$4;
                  LinearProgram.Problem.$init$(this);
                  LinearProgram.Expression.$init$(this);
               }
            }
         };
      }

      default Expression $times$colon(final double c) {
         return new Expression(c) {
            // $FF: synthetic field
            private final Expression $outer;
            private final double c$5;

            public Expression objective() {
               return LinearProgram.Expression.super.objective();
            }

            public IndexedSeq constraints() {
               return LinearProgram.Expression.super.constraints();
            }

            public Expression $plus(final Expression other) {
               return LinearProgram.Expression.super.$plus(other);
            }

            public Expression $plus(final double other) {
               return LinearProgram.Expression.super.$plus(other);
            }

            public Expression $minus(final Expression other) {
               return LinearProgram.Expression.super.$minus(other);
            }

            public Expression $minus(final double other) {
               return LinearProgram.Expression.super.$minus(other);
            }

            public Expression unary_$minus() {
               return LinearProgram.Expression.super.unary_$minus();
            }

            public Constraint $less$eq(final Expression rhs_) {
               return LinearProgram.Expression.super.$less$eq(rhs_);
            }

            public Constraint $less$eq(final double c) {
               return LinearProgram.Expression.super.$less$eq(c);
            }

            public Constraint $greater$eq(final Expression rhs_) {
               return LinearProgram.Expression.super.$greater$eq(rhs_);
            }

            public Constraint $greater$eq(final double c) {
               return LinearProgram.Expression.super.$greater$eq(c);
            }

            public Constraint $eq$colon$eq(final Expression rhs_) {
               return LinearProgram.Expression.super.$eq$colon$eq(rhs_);
            }

            public Constraint $eq$colon$eq(final double c) {
               return LinearProgram.Expression.super.$eq$colon$eq(c);
            }

            public Expression $times(final double c) {
               return LinearProgram.Expression.super.$times(c);
            }

            public Expression $times$colon(final double c) {
               return LinearProgram.Expression.super.$times$colon(c);
            }

            public Option goal() {
               return LinearProgram.Problem.super.goal();
            }

            public Problem subjectTo(final Seq constraints) {
               return LinearProgram.Problem.super.subjectTo(constraints);
            }

            public Result solve(final Solver solver) {
               return LinearProgram.Problem.super.solve(solver);
            }

            public Vector coefficients() {
               return (Vector)this.$outer.coefficients().$times(BoxesRunTime.boxToDouble(this.c$5), HasOps$.MODULE$.impl_Op_V_S_eq_V_Double_OpMulMatrix());
            }

            public double scalarComponent() {
               return this.$outer.scalarComponent() * this.c$5;
            }

            public String toString() {
               return (new StringBuilder(5)).append(this.c$5).append(" * (").append(this.$outer).append(")").toString();
            }

            // $FF: synthetic method
            public LinearProgram breeze$optimize$linear$LinearProgram$Expression$$$outer() {
               return this.$outer.breeze$optimize$linear$LinearProgram$Expression$$$outer();
            }

            // $FF: synthetic method
            public LinearProgram breeze$optimize$linear$LinearProgram$Problem$$$outer() {
               return this.$outer.breeze$optimize$linear$LinearProgram$Expression$$$outer();
            }

            public {
               if (Expression.this == null) {
                  throw null;
               } else {
                  this.$outer = Expression.this;
                  this.c$5 = c$5;
                  LinearProgram.Problem.$init$(this);
                  LinearProgram.Expression.$init$(this);
               }
            }
         };
      }

      // $FF: synthetic method
      LinearProgram breeze$optimize$linear$LinearProgram$Expression$$$outer();

      static void $init$(final Expression $this) {
      }
   }

   public abstract class Relation {
      private final String operator;
      // $FF: synthetic field
      public final LinearProgram $outer;

      public String operator() {
         return this.operator;
      }

      // $FF: synthetic method
      public LinearProgram breeze$optimize$linear$LinearProgram$Relation$$$outer() {
         return this.$outer;
      }

      public Relation(final String operator) {
         this.operator = operator;
         if (LinearProgram.this == null) {
            throw null;
         } else {
            this.$outer = LinearProgram.this;
            super();
         }
      }
   }

   public class LTE$ extends Relation implements Product, Serializable {
      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "LTE";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         Object var2 = Statics.ioobe(x$1);
         return var2;
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof LTE$;
      }

      public int hashCode() {
         return 75709;
      }

      public String toString() {
         return "LTE";
      }

      public LTE$() {
         super("<=");
         Product.$init$(this);
      }
   }

   public class GTE$ extends Relation implements Product, Serializable {
      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "GTE";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         Object var2 = Statics.ioobe(x$1);
         return var2;
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof GTE$;
      }

      public int hashCode() {
         return 70904;
      }

      public String toString() {
         return "GTE";
      }

      public GTE$() {
         super(">=");
         Product.$init$(this);
      }
   }

   public class EQ$ extends Relation implements Product, Serializable {
      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "EQ";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         Object var2 = Statics.ioobe(x$1);
         return var2;
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof EQ$;
      }

      public int hashCode() {
         return 2220;
      }

      public String toString() {
         return "EQ";
      }

      public EQ$() {
         super("=:=");
         Product.$init$(this);
      }
   }

   public interface Constraint {
      Expression lhs();

      Expression rhs();

      Relation relation();

      default String toString() {
         return (new StringBuilder(2)).append(this.lhs()).append(" ").append(this.relation().operator()).append(" ").append(this.rhs()).toString();
      }

      default Constraint standardize() {
         return new Constraint() {
            // $FF: synthetic field
            private final Constraint $outer;

            public String toString() {
               return LinearProgram.Constraint.super.toString();
            }

            public Constraint standardize() {
               return LinearProgram.Constraint.super.standardize();
            }

            public Relation relation() {
               return this.$outer.relation();
            }

            public Expression lhs() {
               return new Expression() {
                  // $FF: synthetic field
                  private final <undefinedtype> $outer;

                  public Expression objective() {
                     return LinearProgram.Expression.super.objective();
                  }

                  public IndexedSeq constraints() {
                     return LinearProgram.Expression.super.constraints();
                  }

                  public Expression $plus(final Expression other) {
                     return LinearProgram.Expression.super.$plus(other);
                  }

                  public Expression $plus(final double other) {
                     return LinearProgram.Expression.super.$plus(other);
                  }

                  public Expression $minus(final Expression other) {
                     return LinearProgram.Expression.super.$minus(other);
                  }

                  public Expression $minus(final double other) {
                     return LinearProgram.Expression.super.$minus(other);
                  }

                  public Expression unary_$minus() {
                     return LinearProgram.Expression.super.unary_$minus();
                  }

                  public Constraint $less$eq(final Expression rhs_) {
                     return LinearProgram.Expression.super.$less$eq(rhs_);
                  }

                  public Constraint $less$eq(final double c) {
                     return LinearProgram.Expression.super.$less$eq(c);
                  }

                  public Constraint $greater$eq(final Expression rhs_) {
                     return LinearProgram.Expression.super.$greater$eq(rhs_);
                  }

                  public Constraint $greater$eq(final double c) {
                     return LinearProgram.Expression.super.$greater$eq(c);
                  }

                  public Constraint $eq$colon$eq(final Expression rhs_) {
                     return LinearProgram.Expression.super.$eq$colon$eq(rhs_);
                  }

                  public Constraint $eq$colon$eq(final double c) {
                     return LinearProgram.Expression.super.$eq$colon$eq(c);
                  }

                  public Expression $times(final double c) {
                     return LinearProgram.Expression.super.$times(c);
                  }

                  public Expression $times$colon(final double c) {
                     return LinearProgram.Expression.super.$times$colon(c);
                  }

                  public Option goal() {
                     return LinearProgram.Problem.super.goal();
                  }

                  public Problem subjectTo(final Seq constraints) {
                     return LinearProgram.Problem.super.subjectTo(constraints);
                  }

                  public Result solve(final Solver solver) {
                     return LinearProgram.Problem.super.solve(solver);
                  }

                  public String toString() {
                     return LinearProgram.Problem.super.toString();
                  }

                  public Vector coefficients() {
                     return (Vector)this.$outer.breeze$optimize$linear$LinearProgram$Constraint$$anon$$$outer().lhs().coefficients().$minus(this.$outer.breeze$optimize$linear$LinearProgram$Constraint$$anon$$$outer().rhs().coefficients(), HasOps$.MODULE$.impl_Op_V_V_eq_V_idempotent_Double_OpSub());
                  }

                  public double scalarComponent() {
                     return (double)0.0F;
                  }

                  // $FF: synthetic method
                  public LinearProgram breeze$optimize$linear$LinearProgram$Expression$$$outer() {
                     return this.$outer.breeze$optimize$linear$LinearProgram$Constraint$$anon$$$outer().breeze$optimize$linear$LinearProgram$Constraint$$$outer();
                  }

                  // $FF: synthetic method
                  public LinearProgram breeze$optimize$linear$LinearProgram$Problem$$$outer() {
                     return this.$outer.breeze$optimize$linear$LinearProgram$Constraint$$anon$$$outer().breeze$optimize$linear$LinearProgram$Constraint$$$outer();
                  }

                  public {
                     if (<VAR_NAMELESS_ENCLOSURE> == null) {
                        throw null;
                     } else {
                        this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                        LinearProgram.Problem.$init$(this);
                        LinearProgram.Expression.$init$(this);
                     }
                  }
               };
            }

            public Expression rhs() {
               return new Expression() {
                  // $FF: synthetic field
                  private final <undefinedtype> $outer;

                  public Expression objective() {
                     return LinearProgram.Expression.super.objective();
                  }

                  public IndexedSeq constraints() {
                     return LinearProgram.Expression.super.constraints();
                  }

                  public Expression $plus(final Expression other) {
                     return LinearProgram.Expression.super.$plus(other);
                  }

                  public Expression $plus(final double other) {
                     return LinearProgram.Expression.super.$plus(other);
                  }

                  public Expression $minus(final Expression other) {
                     return LinearProgram.Expression.super.$minus(other);
                  }

                  public Expression $minus(final double other) {
                     return LinearProgram.Expression.super.$minus(other);
                  }

                  public Expression unary_$minus() {
                     return LinearProgram.Expression.super.unary_$minus();
                  }

                  public Constraint $less$eq(final Expression rhs_) {
                     return LinearProgram.Expression.super.$less$eq(rhs_);
                  }

                  public Constraint $less$eq(final double c) {
                     return LinearProgram.Expression.super.$less$eq(c);
                  }

                  public Constraint $greater$eq(final Expression rhs_) {
                     return LinearProgram.Expression.super.$greater$eq(rhs_);
                  }

                  public Constraint $greater$eq(final double c) {
                     return LinearProgram.Expression.super.$greater$eq(c);
                  }

                  public Constraint $eq$colon$eq(final Expression rhs_) {
                     return LinearProgram.Expression.super.$eq$colon$eq(rhs_);
                  }

                  public Constraint $eq$colon$eq(final double c) {
                     return LinearProgram.Expression.super.$eq$colon$eq(c);
                  }

                  public Expression $times(final double c) {
                     return LinearProgram.Expression.super.$times(c);
                  }

                  public Expression $times$colon(final double c) {
                     return LinearProgram.Expression.super.$times$colon(c);
                  }

                  public Option goal() {
                     return LinearProgram.Problem.super.goal();
                  }

                  public Problem subjectTo(final Seq constraints) {
                     return LinearProgram.Problem.super.subjectTo(constraints);
                  }

                  public Result solve(final Solver solver) {
                     return LinearProgram.Problem.super.solve(solver);
                  }

                  public String toString() {
                     return LinearProgram.Problem.super.toString();
                  }

                  public SparseVector coefficients() {
                     return SparseVector$.MODULE$.zeros$mDc$sp(this.$outer.breeze$optimize$linear$LinearProgram$Constraint$$anon$$$outer().breeze$optimize$linear$LinearProgram$Constraint$$$outer().breeze$optimize$linear$LinearProgram$$variables().length(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
                  }

                  public double scalarComponent() {
                     return this.$outer.breeze$optimize$linear$LinearProgram$Constraint$$anon$$$outer().rhs().scalarComponent() - this.$outer.breeze$optimize$linear$LinearProgram$Constraint$$anon$$$outer().lhs().scalarComponent();
                  }

                  // $FF: synthetic method
                  public LinearProgram breeze$optimize$linear$LinearProgram$Expression$$$outer() {
                     return this.$outer.breeze$optimize$linear$LinearProgram$Constraint$$anon$$$outer().breeze$optimize$linear$LinearProgram$Constraint$$$outer();
                  }

                  // $FF: synthetic method
                  public LinearProgram breeze$optimize$linear$LinearProgram$Problem$$$outer() {
                     return this.$outer.breeze$optimize$linear$LinearProgram$Constraint$$anon$$$outer().breeze$optimize$linear$LinearProgram$Constraint$$$outer();
                  }

                  public {
                     if (<VAR_NAMELESS_ENCLOSURE> == null) {
                        throw null;
                     } else {
                        this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                        LinearProgram.Problem.$init$(this);
                        LinearProgram.Expression.$init$(this);
                     }
                  }
               };
            }

            // $FF: synthetic method
            public Constraint breeze$optimize$linear$LinearProgram$Constraint$$anon$$$outer() {
               return this.$outer;
            }

            // $FF: synthetic method
            public LinearProgram breeze$optimize$linear$LinearProgram$Constraint$$$outer() {
               return this.$outer.breeze$optimize$linear$LinearProgram$Constraint$$$outer();
            }

            public {
               if (Constraint.this == null) {
                  throw null;
               } else {
                  this.$outer = Constraint.this;
                  LinearProgram.Constraint.$init$(this);
               }
            }
         };
      }

      // $FF: synthetic method
      LinearProgram breeze$optimize$linear$LinearProgram$Constraint$$$outer();

      static void $init$(final Constraint $this) {
      }
   }

   public interface Variable extends Expression {
      String name();

      int id();

      default int size() {
         return 1;
      }

      default String toString() {
         return this.name();
      }

      // $FF: synthetic method
      LinearProgram breeze$optimize$linear$LinearProgram$Variable$$$outer();

      static void $init$(final Variable $this) {
      }
   }

   public class Real implements Variable, Product, Serializable {
      private final String name;
      private final int id;
      // $FF: synthetic field
      public final LinearProgram $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public int size() {
         return LinearProgram.Variable.super.size();
      }

      public String toString() {
         return LinearProgram.Variable.super.toString();
      }

      public double scalarComponent() {
         return LinearProgram.Expression.super.scalarComponent();
      }

      public Expression objective() {
         return LinearProgram.Expression.super.objective();
      }

      public IndexedSeq constraints() {
         return LinearProgram.Expression.super.constraints();
      }

      public Expression $plus(final Expression other) {
         return LinearProgram.Expression.super.$plus(other);
      }

      public Expression $plus(final double other) {
         return LinearProgram.Expression.super.$plus(other);
      }

      public Expression $minus(final Expression other) {
         return LinearProgram.Expression.super.$minus(other);
      }

      public Expression $minus(final double other) {
         return LinearProgram.Expression.super.$minus(other);
      }

      public Expression unary_$minus() {
         return LinearProgram.Expression.super.unary_$minus();
      }

      public Constraint $less$eq(final Expression rhs_) {
         return LinearProgram.Expression.super.$less$eq(rhs_);
      }

      public Constraint $less$eq(final double c) {
         return LinearProgram.Expression.super.$less$eq(c);
      }

      public Constraint $greater$eq(final Expression rhs_) {
         return LinearProgram.Expression.super.$greater$eq(rhs_);
      }

      public Constraint $greater$eq(final double c) {
         return LinearProgram.Expression.super.$greater$eq(c);
      }

      public Constraint $eq$colon$eq(final Expression rhs_) {
         return LinearProgram.Expression.super.$eq$colon$eq(rhs_);
      }

      public Constraint $eq$colon$eq(final double c) {
         return LinearProgram.Expression.super.$eq$colon$eq(c);
      }

      public Expression $times(final double c) {
         return LinearProgram.Expression.super.$times(c);
      }

      public Expression $times$colon(final double c) {
         return LinearProgram.Expression.super.$times$colon(c);
      }

      public Option goal() {
         return LinearProgram.Problem.super.goal();
      }

      public Problem subjectTo(final Seq constraints) {
         return LinearProgram.Problem.super.subjectTo(constraints);
      }

      public Result solve(final Solver solver) {
         return LinearProgram.Problem.super.solve(solver);
      }

      public String name() {
         return this.name;
      }

      public int id() {
         return this.id;
      }

      public SparseVector coefficients() {
         SparseVector v = SparseVector$.MODULE$.zeros$mDc$sp(this.breeze$optimize$linear$LinearProgram$Real$$$outer().breeze$optimize$linear$LinearProgram$$variables().length(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
         scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), this.size()).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> v.update$mcD$sp(this.id() + i, (double)1.0F));
         return v;
      }

      public Real copy(final String name) {
         return this.breeze$optimize$linear$LinearProgram$Real$$$outer().new Real(name);
      }

      public String copy$default$1() {
         return this.name();
      }

      public String productPrefix() {
         return "Real";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.name();
               break;
            default:
               var10000 = Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Real;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "name";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
      }

      public boolean equals(final Object x$1) {
         boolean var7;
         if (this != x$1) {
            label58: {
               boolean var2;
               if (x$1 instanceof Real && ((Real)x$1).breeze$optimize$linear$LinearProgram$Real$$$outer() == this.breeze$optimize$linear$LinearProgram$Real$$$outer()) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label35: {
                     label34: {
                        Real var4 = (Real)x$1;
                        String var10000 = this.name();
                        String var5 = var4.name();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label34;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label34;
                        }

                        if (var4.canEqual(this)) {
                           var7 = true;
                           break label35;
                        }
                     }

                     var7 = false;
                  }

                  if (var7) {
                     break label58;
                  }
               }

               var7 = false;
               return var7;
            }
         }

         var7 = true;
         return var7;
      }

      // $FF: synthetic method
      public LinearProgram breeze$optimize$linear$LinearProgram$Real$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public LinearProgram breeze$optimize$linear$LinearProgram$Variable$$$outer() {
         return this.breeze$optimize$linear$LinearProgram$Real$$$outer();
      }

      // $FF: synthetic method
      public LinearProgram breeze$optimize$linear$LinearProgram$Expression$$$outer() {
         return this.breeze$optimize$linear$LinearProgram$Real$$$outer();
      }

      // $FF: synthetic method
      public LinearProgram breeze$optimize$linear$LinearProgram$Problem$$$outer() {
         return this.breeze$optimize$linear$LinearProgram$Real$$$outer();
      }

      public Real(final String name) {
         this.name = name;
         if (LinearProgram.this == null) {
            throw null;
         } else {
            this.$outer = LinearProgram.this;
            super();
            LinearProgram.Problem.$init$(this);
            LinearProgram.Expression.$init$(this);
            LinearProgram.Variable.$init$(this);
            Product.$init$(this);
            this.id = LinearProgram.this.breeze$optimize$linear$LinearProgram$$variables().length();
            LinearProgram.this.breeze$optimize$linear$LinearProgram$$variables().$plus$eq(this);
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class Real$ extends AbstractFunction1 implements Serializable {
      // $FF: synthetic field
      private final LinearProgram $outer;

      public String $lessinit$greater$default$1() {
         return (new StringBuilder(2)).append("x_").append(this.$outer.breeze$optimize$linear$LinearProgram$$nextId()).toString();
      }

      public final String toString() {
         return "Real";
      }

      public Real apply(final String name) {
         return this.$outer.new Real(name);
      }

      public String apply$default$1() {
         return (new StringBuilder(2)).append("x_").append(this.$outer.breeze$optimize$linear$LinearProgram$$nextId()).toString();
      }

      public Option unapply(final Real x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.name()));
      }

      public Real$() {
         if (LinearProgram.this == null) {
            throw null;
         } else {
            this.$outer = LinearProgram.this;
            super();
         }
      }
   }

   public class Integer implements Variable, Product, Serializable {
      private final String name;
      private final int id;
      // $FF: synthetic field
      public final LinearProgram $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public int size() {
         return LinearProgram.Variable.super.size();
      }

      public String toString() {
         return LinearProgram.Variable.super.toString();
      }

      public double scalarComponent() {
         return LinearProgram.Expression.super.scalarComponent();
      }

      public Expression objective() {
         return LinearProgram.Expression.super.objective();
      }

      public IndexedSeq constraints() {
         return LinearProgram.Expression.super.constraints();
      }

      public Expression $plus(final Expression other) {
         return LinearProgram.Expression.super.$plus(other);
      }

      public Expression $plus(final double other) {
         return LinearProgram.Expression.super.$plus(other);
      }

      public Expression $minus(final Expression other) {
         return LinearProgram.Expression.super.$minus(other);
      }

      public Expression $minus(final double other) {
         return LinearProgram.Expression.super.$minus(other);
      }

      public Expression unary_$minus() {
         return LinearProgram.Expression.super.unary_$minus();
      }

      public Constraint $less$eq(final Expression rhs_) {
         return LinearProgram.Expression.super.$less$eq(rhs_);
      }

      public Constraint $less$eq(final double c) {
         return LinearProgram.Expression.super.$less$eq(c);
      }

      public Constraint $greater$eq(final Expression rhs_) {
         return LinearProgram.Expression.super.$greater$eq(rhs_);
      }

      public Constraint $greater$eq(final double c) {
         return LinearProgram.Expression.super.$greater$eq(c);
      }

      public Constraint $eq$colon$eq(final Expression rhs_) {
         return LinearProgram.Expression.super.$eq$colon$eq(rhs_);
      }

      public Constraint $eq$colon$eq(final double c) {
         return LinearProgram.Expression.super.$eq$colon$eq(c);
      }

      public Expression $times(final double c) {
         return LinearProgram.Expression.super.$times(c);
      }

      public Expression $times$colon(final double c) {
         return LinearProgram.Expression.super.$times$colon(c);
      }

      public Option goal() {
         return LinearProgram.Problem.super.goal();
      }

      public Problem subjectTo(final Seq constraints) {
         return LinearProgram.Problem.super.subjectTo(constraints);
      }

      public Result solve(final Solver solver) {
         return LinearProgram.Problem.super.solve(solver);
      }

      public String name() {
         return this.name;
      }

      public int id() {
         return this.id;
      }

      public SparseVector coefficients() {
         SparseVector v = SparseVector$.MODULE$.zeros$mDc$sp(this.breeze$optimize$linear$LinearProgram$Integer$$$outer().breeze$optimize$linear$LinearProgram$$variables().length(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
         scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), this.size()).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> v.update$mcD$sp(this.id() + i, (double)1.0F));
         return v;
      }

      public Integer copy(final String name) {
         return this.breeze$optimize$linear$LinearProgram$Integer$$$outer().new Integer(name);
      }

      public String copy$default$1() {
         return this.name();
      }

      public String productPrefix() {
         return "Integer";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.name();
               break;
            default:
               var10000 = Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Integer;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "name";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
      }

      public boolean equals(final Object x$1) {
         boolean var7;
         if (this != x$1) {
            label58: {
               boolean var2;
               if (x$1 instanceof Integer && ((Integer)x$1).breeze$optimize$linear$LinearProgram$Integer$$$outer() == this.breeze$optimize$linear$LinearProgram$Integer$$$outer()) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label35: {
                     label34: {
                        Integer var4 = (Integer)x$1;
                        String var10000 = this.name();
                        String var5 = var4.name();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label34;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label34;
                        }

                        if (var4.canEqual(this)) {
                           var7 = true;
                           break label35;
                        }
                     }

                     var7 = false;
                  }

                  if (var7) {
                     break label58;
                  }
               }

               var7 = false;
               return var7;
            }
         }

         var7 = true;
         return var7;
      }

      // $FF: synthetic method
      public LinearProgram breeze$optimize$linear$LinearProgram$Integer$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public LinearProgram breeze$optimize$linear$LinearProgram$Variable$$$outer() {
         return this.breeze$optimize$linear$LinearProgram$Integer$$$outer();
      }

      // $FF: synthetic method
      public LinearProgram breeze$optimize$linear$LinearProgram$Expression$$$outer() {
         return this.breeze$optimize$linear$LinearProgram$Integer$$$outer();
      }

      // $FF: synthetic method
      public LinearProgram breeze$optimize$linear$LinearProgram$Problem$$$outer() {
         return this.breeze$optimize$linear$LinearProgram$Integer$$$outer();
      }

      public Integer(final String name) {
         this.name = name;
         if (LinearProgram.this == null) {
            throw null;
         } else {
            this.$outer = LinearProgram.this;
            super();
            LinearProgram.Problem.$init$(this);
            LinearProgram.Expression.$init$(this);
            LinearProgram.Variable.$init$(this);
            Product.$init$(this);
            this.id = LinearProgram.this.breeze$optimize$linear$LinearProgram$$variables().length();
            LinearProgram.this.breeze$optimize$linear$LinearProgram$$variables().$plus$eq(this);
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class Integer$ extends AbstractFunction1 implements Serializable {
      // $FF: synthetic field
      private final LinearProgram $outer;

      public String $lessinit$greater$default$1() {
         return (new StringBuilder(2)).append("x_").append(this.$outer.breeze$optimize$linear$LinearProgram$$nextId()).toString();
      }

      public final String toString() {
         return "Integer";
      }

      public Integer apply(final String name) {
         return this.$outer.new Integer(name);
      }

      public String apply$default$1() {
         return (new StringBuilder(2)).append("x_").append(this.$outer.breeze$optimize$linear$LinearProgram$$nextId()).toString();
      }

      public Option unapply(final Integer x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.name()));
      }

      public Integer$() {
         if (LinearProgram.this == null) {
            throw null;
         } else {
            this.$outer = LinearProgram.this;
            super();
         }
      }
   }

   public class Binary implements Variable, Product, Serializable {
      private final String name;
      private final int id;
      // $FF: synthetic field
      public final LinearProgram $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public int size() {
         return LinearProgram.Variable.super.size();
      }

      public String toString() {
         return LinearProgram.Variable.super.toString();
      }

      public double scalarComponent() {
         return LinearProgram.Expression.super.scalarComponent();
      }

      public Expression objective() {
         return LinearProgram.Expression.super.objective();
      }

      public IndexedSeq constraints() {
         return LinearProgram.Expression.super.constraints();
      }

      public Expression $plus(final Expression other) {
         return LinearProgram.Expression.super.$plus(other);
      }

      public Expression $plus(final double other) {
         return LinearProgram.Expression.super.$plus(other);
      }

      public Expression $minus(final Expression other) {
         return LinearProgram.Expression.super.$minus(other);
      }

      public Expression $minus(final double other) {
         return LinearProgram.Expression.super.$minus(other);
      }

      public Expression unary_$minus() {
         return LinearProgram.Expression.super.unary_$minus();
      }

      public Constraint $less$eq(final Expression rhs_) {
         return LinearProgram.Expression.super.$less$eq(rhs_);
      }

      public Constraint $less$eq(final double c) {
         return LinearProgram.Expression.super.$less$eq(c);
      }

      public Constraint $greater$eq(final Expression rhs_) {
         return LinearProgram.Expression.super.$greater$eq(rhs_);
      }

      public Constraint $greater$eq(final double c) {
         return LinearProgram.Expression.super.$greater$eq(c);
      }

      public Constraint $eq$colon$eq(final Expression rhs_) {
         return LinearProgram.Expression.super.$eq$colon$eq(rhs_);
      }

      public Constraint $eq$colon$eq(final double c) {
         return LinearProgram.Expression.super.$eq$colon$eq(c);
      }

      public Expression $times(final double c) {
         return LinearProgram.Expression.super.$times(c);
      }

      public Expression $times$colon(final double c) {
         return LinearProgram.Expression.super.$times$colon(c);
      }

      public Option goal() {
         return LinearProgram.Problem.super.goal();
      }

      public Problem subjectTo(final Seq constraints) {
         return LinearProgram.Problem.super.subjectTo(constraints);
      }

      public Result solve(final Solver solver) {
         return LinearProgram.Problem.super.solve(solver);
      }

      public String name() {
         return this.name;
      }

      public int id() {
         return this.id;
      }

      public SparseVector coefficients() {
         SparseVector v = SparseVector$.MODULE$.zeros$mDc$sp(this.breeze$optimize$linear$LinearProgram$Binary$$$outer().breeze$optimize$linear$LinearProgram$$variables().length(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
         scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), this.size()).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> v.update$mcD$sp(this.id() + i, (double)1.0F));
         return v;
      }

      public Binary copy(final String name) {
         return this.breeze$optimize$linear$LinearProgram$Binary$$$outer().new Binary(name);
      }

      public String copy$default$1() {
         return this.name();
      }

      public String productPrefix() {
         return "Binary";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.name();
               break;
            default:
               var10000 = Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Binary;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "name";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
      }

      public boolean equals(final Object x$1) {
         boolean var7;
         if (this != x$1) {
            label58: {
               boolean var2;
               if (x$1 instanceof Binary && ((Binary)x$1).breeze$optimize$linear$LinearProgram$Binary$$$outer() == this.breeze$optimize$linear$LinearProgram$Binary$$$outer()) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label35: {
                     label34: {
                        Binary var4 = (Binary)x$1;
                        String var10000 = this.name();
                        String var5 = var4.name();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label34;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label34;
                        }

                        if (var4.canEqual(this)) {
                           var7 = true;
                           break label35;
                        }
                     }

                     var7 = false;
                  }

                  if (var7) {
                     break label58;
                  }
               }

               var7 = false;
               return var7;
            }
         }

         var7 = true;
         return var7;
      }

      // $FF: synthetic method
      public LinearProgram breeze$optimize$linear$LinearProgram$Binary$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public LinearProgram breeze$optimize$linear$LinearProgram$Variable$$$outer() {
         return this.breeze$optimize$linear$LinearProgram$Binary$$$outer();
      }

      // $FF: synthetic method
      public LinearProgram breeze$optimize$linear$LinearProgram$Expression$$$outer() {
         return this.breeze$optimize$linear$LinearProgram$Binary$$$outer();
      }

      // $FF: synthetic method
      public LinearProgram breeze$optimize$linear$LinearProgram$Problem$$$outer() {
         return this.breeze$optimize$linear$LinearProgram$Binary$$$outer();
      }

      public Binary(final String name) {
         this.name = name;
         if (LinearProgram.this == null) {
            throw null;
         } else {
            this.$outer = LinearProgram.this;
            super();
            LinearProgram.Problem.$init$(this);
            LinearProgram.Expression.$init$(this);
            LinearProgram.Variable.$init$(this);
            Product.$init$(this);
            this.id = LinearProgram.this.breeze$optimize$linear$LinearProgram$$variables().length();
            LinearProgram.this.breeze$optimize$linear$LinearProgram$$variables().$plus$eq(this);
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class Binary$ extends AbstractFunction1 implements Serializable {
      // $FF: synthetic field
      private final LinearProgram $outer;

      public String $lessinit$greater$default$1() {
         return (new StringBuilder(2)).append("x_").append(this.$outer.breeze$optimize$linear$LinearProgram$$nextId()).toString();
      }

      public final String toString() {
         return "Binary";
      }

      public Binary apply(final String name) {
         return this.$outer.new Binary(name);
      }

      public String apply$default$1() {
         return (new StringBuilder(2)).append("x_").append(this.$outer.breeze$optimize$linear$LinearProgram$$nextId()).toString();
      }

      public Option unapply(final Binary x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.name()));
      }

      public Binary$() {
         if (LinearProgram.this == null) {
            throw null;
         } else {
            this.$outer = LinearProgram.this;
            super();
         }
      }
   }

   public class Result implements Product, Serializable {
      private final DenseVector result;
      private final Problem problem;
      // $FF: synthetic field
      public final LinearProgram $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public DenseVector result() {
         return this.result;
      }

      public Problem problem() {
         return this.problem;
      }

      public double valueOf(final Expression x) {
         return BoxesRunTime.unboxToDouble(this.result().dot(x.coefficients(), HasOps$.MODULE$.impl_OpMulInner_DV_V_eq_S_Double())) + x.scalarComponent();
      }

      public double value() {
         return this.valueOf(this.problem().objective());
      }

      public Result copy(final DenseVector result, final Problem problem) {
         return this.breeze$optimize$linear$LinearProgram$Result$$$outer().new Result(result, problem);
      }

      public DenseVector copy$default$1() {
         return this.result();
      }

      public Problem copy$default$2() {
         return this.problem();
      }

      public String productPrefix() {
         return "Result";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.result();
               break;
            case 1:
               var10000 = this.problem();
               break;
            default:
               var10000 = Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Result;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "result";
               break;
            case 1:
               var10000 = "problem";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var9;
         if (this != x$1) {
            label68: {
               boolean var2;
               if (x$1 instanceof Result && ((Result)x$1).breeze$optimize$linear$LinearProgram$Result$$$outer() == this.breeze$optimize$linear$LinearProgram$Result$$$outer()) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label44: {
                     label58: {
                        Result var4 = (Result)x$1;
                        DenseVector var10000 = this.result();
                        DenseVector var5 = var4.result();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label58;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label58;
                        }

                        Problem var7 = this.problem();
                        Problem var6 = var4.problem();
                        if (var7 == null) {
                           if (var6 != null) {
                              break label58;
                           }
                        } else if (!var7.equals(var6)) {
                           break label58;
                        }

                        if (var4.canEqual(this)) {
                           var9 = true;
                           break label44;
                        }
                     }

                     var9 = false;
                  }

                  if (var9) {
                     break label68;
                  }
               }

               var9 = false;
               return var9;
            }
         }

         var9 = true;
         return var9;
      }

      // $FF: synthetic method
      public LinearProgram breeze$optimize$linear$LinearProgram$Result$$$outer() {
         return this.$outer;
      }

      public Result(final DenseVector result, final Problem problem) {
         this.result = result;
         this.problem = problem;
         if (LinearProgram.this == null) {
            throw null;
         } else {
            this.$outer = LinearProgram.this;
            super();
            Product.$init$(this);
         }
      }
   }

   public class Result$ extends AbstractFunction2 implements Serializable {
      // $FF: synthetic field
      private final LinearProgram $outer;

      public final String toString() {
         return "Result";
      }

      public Result apply(final DenseVector result, final Problem problem) {
         return this.$outer.new Result(result, problem);
      }

      public Option unapply(final Result x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.result(), x$0.problem())));
      }

      public Result$() {
         if (LinearProgram.this == null) {
            throw null;
         } else {
            this.$outer = LinearProgram.this;
            super();
         }
      }
   }

   public static class ApacheSimplexSolver$ implements Solver {
      public static final ApacheSimplexSolver$ MODULE$ = new ApacheSimplexSolver$();

      public Result maximize(final LinearProgram lp, final Problem objective) {
         LinearObjectiveFunction obj = new LinearObjectiveFunction(objective.objective().coefficients().toDenseVector$mcD$sp(scala.reflect.ClassTag..MODULE$.Double()).data$mcD$sp(), objective.objective().scalarComponent());
         LinearConstraintSet constraintSet = this.buildConstraints(lp, objective);
         PointValuePair sol = (new SimplexSolver()).optimize(new OptimizationData[]{obj, constraintSet, GoalType.MAXIMIZE});
         return lp.new Result(new DenseVector$mcD$sp(sol.getPoint()), objective);
      }

      public Result minimize(final LinearProgram lp, final Problem objective) {
         LinearObjectiveFunction obj = new LinearObjectiveFunction(objective.objective().coefficients().toDenseVector$mcD$sp(scala.reflect.ClassTag..MODULE$.Double()).data$mcD$sp(), objective.objective().scalarComponent());
         LinearConstraintSet constraintSet = this.buildConstraints(lp, objective);
         PointValuePair sol = (new SimplexSolver()).optimize(new OptimizationData[]{obj, constraintSet, GoalType.MINIMIZE});
         return lp.new Result(new DenseVector$mcD$sp(sol.getPoint()), objective);
      }

      private LinearConstraintSet buildConstraints(final LinearProgram lp, final Problem objective) {
         lp.breeze$optimize$linear$LinearProgram$$variables().foreach((v) -> {
            $anonfun$buildConstraints$1(v);
            return BoxedUnit.UNIT;
         });
         IndexedSeq constraints = (IndexedSeq)objective.constraints().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$buildConstraints$2(check$ifrefutable$1))).map((c) -> {
            Constraint cs = c.standardize();
            return new LinearConstraint(cs.lhs().coefficients().toDenseVector$mcD$sp(scala.reflect.ClassTag..MODULE$.Double()).data$mcD$sp(), relationToConstraintType$1(c.relation(), lp), cs.rhs().scalarComponent());
         });
         return new LinearConstraintSet((Collection)scala.collection.JavaConverters..MODULE$.seqAsJavaListConverter(constraints).asJava());
      }

      private static final Relationship relationToConstraintType$1(final Relation r, final LinearProgram lp$1) {
         Relationship var2;
         if (lp$1.LTE().equals(r)) {
            var2 = Relationship.LEQ;
         } else if (lp$1.GTE().equals(r)) {
            var2 = Relationship.GEQ;
         } else {
            if (!lp$1.EQ().equals(r)) {
               throw new MatchError(r);
            }

            var2 = Relationship.EQ;
         }

         return var2;
      }

      // $FF: synthetic method
      public static final void $anonfun$buildConstraints$1(final Variable v) {
         if (!(v instanceof Variable)) {
            throw new UnsupportedOperationException("Apache Solver can only handle real-valued linear programs.");
         }
      }

      // $FF: synthetic method
      public static final boolean $anonfun$buildConstraints$2(final Constraint check$ifrefutable$1) {
         boolean var1;
         if (check$ifrefutable$1 != null) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public interface Solver {
      Result maximize(final LinearProgram lp, final Problem obj);

      Result minimize(final LinearProgram lp, final Problem obj);
   }
}
