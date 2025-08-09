package breeze.linalg.operators;

import breeze.generic.UFunc;
import breeze.generic.UFunc$InPlaceImpl2$mcD$sp;
import breeze.generic.UFunc$InPlaceImpl2$mcF$sp;
import breeze.generic.UFunc$InPlaceImpl2$mcI$sp;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.ZippedValues;
import breeze.linalg.ZippedValues$mcDD$sp;
import breeze.math.PowImplicits$;
import breeze.storage.Zero$;
import scala.Function2;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015UfACA\u001f\u0003\u007f\u0001\n1!\u0001\u0002N!9\u0011\u0011\u000e\u0001\u0005\u0002\u0005-\u0004\"CA:\u0001\t\u0007I1AA;\u0011%\tI\n\u0001b\u0001\n\u0007\tY\nC\u0005\u0002(\u0002\u0011\r\u0011b\u0001\u0002*\"I\u0011Q\u0017\u0001C\u0002\u0013\r\u0011q\u0017\u0005\n\u0003\u0007\u0004!\u0019!C\u0002\u0003\u000bD\u0011\"a4\u0001\u0005\u0004%\u0019!!5\t\u0013\u0005U\u0007A1A\u0005\u0004\u0005]\u0007\"CAn\u0001\t\u0007I1AAo\u0011%\t\t\u000f\u0001b\u0001\n\u0007\t\u0019\u000fC\u0005\u0002n\u0002\u0011\r\u0011b\u0001\u0002p\"I\u00111\u001f\u0001C\u0002\u0013\r\u0011Q\u001f\u0005\n\u0003s\u0004!\u0019!C\u0002\u0003wD\u0011\"a@\u0001\u0005\u0004%\u0019A!\u0001\t\u0013\t-\u0001A1A\u0005\u0004\t5\u0001\"\u0003B\t\u0001\t\u0007I1\u0001B\n\u0011%\u00119\u0002\u0001b\u0001\n\u0007\u0011I\u0002C\u0005\u0003\u001e\u0001\u0011\r\u0011b\u0001\u0003 !I!\u0011\u0006\u0001C\u0002\u0013\r!1\u0006\u0005\n\u0005_\u0001!\u0019!C\u0002\u0005cA\u0011B!\u000e\u0001\u0005\u0004%\u0019Aa\u000e\t\u0013\tm\u0002A1A\u0005\u0004\tu\u0002\"\u0003B$\u0001\t\u0007I1\u0001B%\u0011%\u0011i\u0005\u0001b\u0001\n\u0007\u0011y\u0005C\u0005\u0003T\u0001\u0011\r\u0011b\u0001\u0003V!I!\u0011\f\u0001C\u0002\u0013\r!1\f\u0005\n\u0005K\u0002!\u0019!C\u0002\u0005OB\u0011Ba\u001b\u0001\u0005\u0004%\u0019A!\u001c\t\u0013\tE\u0004A1A\u0005\u0004\tM\u0004\"\u0003B<\u0001\t\u0007I1\u0001B=\u0011%\u0011\u0019\t\u0001b\u0001\n\u0007\u0011)\tC\u0005\u0003\n\u0002\u0011\r\u0011b\u0001\u0003\f\"I!q\u0012\u0001C\u0002\u0013\r!\u0011\u0013\u0005\n\u0005+\u0003!\u0019!C\u0002\u0005/C\u0011Ba'\u0001\u0005\u0004%\u0019A!(\t\u0013\t\u0005\u0006A1A\u0005\u0004\t\r\u0006\"\u0003BT\u0001\t\u0007I1\u0001BU\u0011%\u0011i\u000b\u0001b\u0001\n\u0007\u0011y\u000bC\u0005\u00034\u0002\u0011\r\u0011b\u0001\u00036\"I!\u0011\u0018\u0001C\u0002\u0013\r!1\u0018\u0005\n\u0005\u007f\u0003!\u0019!C\u0002\u0005\u0003D\u0011B!2\u0001\u0005\u0004%\u0019Aa2\t\u0013\t-\u0007A1A\u0005\u0004\t5\u0007\"\u0003Bi\u0001\t\u0007I1\u0001Bj\u0011%\u00119\u000e\u0001b\u0001\n\u0007\u0011I\u000eC\u0005\u0003^\u0002\u0011\r\u0011b\u0001\u0003`\"I!1\u001d\u0001C\u0002\u0013\r!Q\u001d\u0005\n\u0005S\u0004!\u0019!C\u0002\u0005WD\u0011Ba<\u0001\u0005\u0004%\u0019A!=\t\u0013\tU\bA1A\u0005\u0004\t]\b\"\u0003B~\u0001\t\u0007I1\u0001B\u007f\u0011%\u0019\t\u0001\u0001b\u0001\n\u0007\u0019\u0019\u0001C\u0005\u0004\b\u0001\u0011\r\u0011b\u0001\u0004\n!I1Q\u0002\u0001C\u0002\u0013\r1q\u0002\u0005\n\u0007'\u0001!\u0019!C\u0002\u0007+A\u0011b!\u0007\u0001\u0005\u0004%\u0019aa\u0007\t\u0013\r}\u0001A1A\u0005\u0004\r\u0005\u0002\"CB\u0013\u0001\t\u0007I1AB\u0014\u0011%\u0019Y\u0003\u0001b\u0001\n\u0007\u0019i\u0003C\u0005\u00042\u0001\u0011\r\u0011b\u0001\u00044!I1q\u0007\u0001C\u0002\u0013\r1\u0011\b\u0005\n\u0007{\u0001!\u0019!C\u0002\u0007\u007fA\u0011ba\u0011\u0001\u0005\u0004%\u0019a!\u0012\t\u0013\r%\u0003A1A\u0005\u0004\r-\u0003\"CB(\u0001\t\u0007I1AB)\u0011%\u0019)\u0006\u0001b\u0001\n\u0007\u00199\u0006C\u0005\u0004\\\u0001\u0011\r\u0011b\u0001\u0004^!I1\u0011\r\u0001C\u0002\u0013\r11\r\u0005\n\u0007O\u0002!\u0019!C\u0002\u0007SB\u0011b!\u001c\u0001\u0005\u0004%\u0019aa\u001c\t\u0013\rM\u0004A1A\u0005\u0004\rU\u0004\"CB=\u0001\t\u0007I1AB>\u0011%\u0019y\b\u0001b\u0001\n\u0007\u0019\t\tC\u0005\u0004\u0006\u0002\u0011\r\u0011b\u0001\u0004\b\"I11\u0012\u0001C\u0002\u0013\r1Q\u0012\u0005\n\u0007#\u0003!\u0019!C\u0002\u0007'C\u0011ba&\u0001\u0005\u0004%\u0019a!'\t\u0013\ru\u0005A1A\u0005\u0004\r}\u0005\"CBR\u0001\t\u0007I1ABS\u0011%\u0019I\u000b\u0001b\u0001\n\u0007\u0019Y\u000bC\u0005\u00040\u0002\u0011\r\u0011b\u0001\u00042\"I1Q\u0017\u0001C\u0002\u0013\r1q\u0017\u0005\n\u0007w\u0003!\u0019!C\u0002\u0007{C\u0011b!1\u0001\u0005\u0004%\u0019aa1\t\u0013\r\u001d\u0007A1A\u0005\u0004\r%\u0007\"CBg\u0001\t\u0007I1ABh\u0011%\u0019\u0019\u000e\u0001b\u0001\n\u0007\u0019)\u000eC\u0005\u0004Z\u0002\u0011\r\u0011b\u0001\u0004\\\"I1q\u001c\u0001C\u0002\u0013\r1\u0011\u001d\u0005\n\u0007K\u0004!\u0019!C\u0002\u0007OD\u0011ba;\u0001\u0005\u0004%\u0019a!<\t\u0013\rE\bA1A\u0005\u0004\rM\b\"CB|\u0001\t\u0007I1AB}\u0011%\u0019i\u0010\u0001b\u0001\n\u0007\u0019y\u0010C\u0005\u0005\b\u0001\u0011\r\u0011b\u0001\u0005\n!IAQ\u0002\u0001C\u0002\u0013\rAq\u0002\u0005\n\t'\u0001!\u0019!C\u0002\t+A\u0011\u0002\"\u0007\u0001\u0005\u0004%\u0019\u0001b\u0007\t\u0013\u0011}\u0001A1A\u0005\u0004\u0011\u0005\u0002\"\u0003C\u0013\u0001\t\u0007I1\u0001C\u0014\u0011%!Y\u0003\u0001b\u0001\n\u0007!i\u0003C\u0005\u00052\u0001\u0011\r\u0011b\u0001\u00054!IAq\u0007\u0001C\u0002\u0013\rA\u0011\b\u0005\n\t{\u0001!\u0019!C\u0002\t\u007fA\u0011\u0002b\u0011\u0001\u0005\u0004%\u0019\u0001\"\u0012\t\u0013\u0011%\u0003A1A\u0005\u0004\u0011-\u0003\"\u0003C(\u0001\t\u0007I1\u0001C)\u0011%!)\u0006\u0001b\u0001\n\u0007!9\u0006C\u0005\u0005\\\u0001\u0011\r\u0011b\u0001\u0005^!IA\u0011\r\u0001C\u0002\u0013\rA1\r\u0005\n\tO\u0002!\u0019!C\u0002\tSB\u0011\u0002\"\u001c\u0001\u0005\u0004%\u0019\u0001b\u001c\t\u0013\u0011M\u0004A1A\u0005\u0004\u0011U\u0004\"\u0003C=\u0001\t\u0007I1\u0001C>\u0011%!y\b\u0001b\u0001\n\u0007!\t\tC\u0005\u0005\u0006\u0002\u0011\r\u0011b\u0001\u0005\b\"IA1\u0012\u0001C\u0002\u0013\rAQ\u0012\u0005\n\t#\u0003!\u0019!C\u0002\t'C\u0011\u0002b&\u0001\u0005\u0004%\u0019\u0001\"'\t\u0013\u0011u\u0005A1A\u0005\u0004\u0011}\u0005\"\u0003CR\u0001\t\u0007I1\u0001CS\u0011%!I\u000b\u0001b\u0001\n\u0007!Y\u000bC\u0005\u00050\u0002\u0011\r\u0011b\u0001\u00052\"IAQ\u0017\u0001C\u0002\u0013\rAq\u0017\u0005\n\tw\u0003!\u0019!C\u0002\t{C\u0011\u0002\"1\u0001\u0005\u0004%\u0019\u0001b1\t\u0013\u0011\u001d\u0007A1A\u0005\u0004\u0011%\u0007\"\u0003Cg\u0001\t\u0007I1\u0001Ch\u0011%!\u0019\u000e\u0001b\u0001\n\u0007!)\u000eC\u0005\u0005Z\u0002\u0011\r\u0011b\u0001\u0005\\\"IAq\u001c\u0001C\u0002\u0013\rA\u0011\u001d\u0005\n\tK\u0004!\u0019!C\u0002\tOD\u0011\u0002b;\u0001\u0005\u0004%\u0019\u0001\"<\t\u0013\u0011E\bA1A\u0005\u0004\u0011M\b\"\u0003C|\u0001\t\u0007I1\u0001C}\u0011%!i\u0010\u0001b\u0001\n\u0007!y\u0010C\u0005\u0006\u0004\u0001\u0011\r\u0011b\u0001\u0006\u0006!IQ\u0011\u0002\u0001C\u0002\u0013\rQ1\u0002\u0005\n\u000b\u001f\u0001!\u0019!C\u0002\u000b#A\u0011\"\"\u0006\u0001\u0005\u0004%\u0019!b\u0006\t\u0013\u0015m\u0001A1A\u0005\u0004\u0015u\u0001\"CC\u0011\u0001\t\u0007I1AC\u0012\u0011%)9\u0003\u0001b\u0001\n\u0007)I\u0003C\u0005\u0006.\u0001\u0011\r\u0011b\u0001\u00060!IQ1\u0007\u0001C\u0002\u0013\rQQ\u0007\u0005\n\u000bs\u0001!\u0019!C\u0002\u000bwA\u0011\"b\u0010\u0001\u0005\u0004%\u0019!\"\u0011\t\u0013\u0015\u0015\u0003A1A\u0005\u0004\u0015\u001d\u0003\"CC&\u0001\t\u0007I1AC'\u0011%)\t\u0006\u0001b\u0001\n\u0007)\u0019\u0006C\u0005\u0006p\u0001\u0011\r\u0011b\u0001\u0006r!IQQ\u000f\u0001C\u0002\u0013\rQq\u000f\u0005\n\u000b\u000f\u0003!\u0019!C\u0002\u000b\u0013C\u0011\"b$\u0001\u0005\u0004%\u0019!\"%\t\u0013\u0015]\u0005A1A\u0005\u0004\u0015e\u0005\"CCP\u0001\t\u0007I1ACQ\u0011%)y\u000b\u0001b\u0001\n\u0007)\tL\u0001\u000bEK:\u001cXMV3di>\u0014X\t\u001f9b]\u0012|\u0005o\u001d\u0006\u0005\u0003\u0003\n\u0019%A\u0005pa\u0016\u0014\u0018\r^8sg*!\u0011QIA$\u0003\u0019a\u0017N\\1mO*\u0011\u0011\u0011J\u0001\u0007EJ,WM_3\u0004\u0001M9\u0001!a\u0014\u0002\\\u0005\r\u0004\u0003BA)\u0003/j!!a\u0015\u000b\u0005\u0005U\u0013!B:dC2\f\u0017\u0002BA-\u0003'\u0012a!\u00118z%\u00164\u0007\u0003BA/\u0003?j!!a\u0010\n\t\u0005\u0005\u0014q\b\u0002\n-\u0016\u001cGo\u001c:PaN\u0004B!!\u0018\u0002f%!\u0011qMA \u0005q!UM\\:f-\u0016\u001cGo\u001c:`-\u0016\u001cGo\u001c:`\u000bb\u0004\u0018M\u001c3PaN\fa\u0001J5oSR$CCAA7!\u0011\t\t&a\u001c\n\t\u0005E\u00141\u000b\u0002\u0005+:LG/\u0001\u000fj[Bdwl\u00149`\tZ{6kX3r?\u00123v,\u00138u?>\u0003\u0018\t\u001a3\u0016\u0005\u0005]\u0004CCA=\u0003\u007f\nY)a%\u0002\f:!\u0011QLA>\u0013\u0011\ti(a\u0010\u0002\u000b=\u0003\u0018\t\u001a3\n\t\u0005\u0005\u00151\u0011\u0002\u0006\u00136\u0004HNM\u0005\u0005\u0003\u000b\u000b9IA\u0003V\rVt7M\u0003\u0003\u0002\n\u0006\u001d\u0013aB4f]\u0016\u0014\u0018n\u0019\t\u0007\u0003\u001b\u000by)a%\u000e\u0005\u0005\r\u0013\u0002BAI\u0003\u0007\u00121\u0002R3og\u00164Vm\u0019;peB!\u0011\u0011KAK\u0013\u0011\t9*a\u0015\u0003\u0007%sG/A\u0010j[Bdwl\u00149`\tZ{6kX3r?\u00123v\fR8vE2,wl\u00149BI\u0012,\"!!(\u0011\u0015\u0005e\u0014qPAP\u0003C\u000by\n\u0005\u0004\u0002\u000e\u0006=\u0015\u0011\u0015\t\u0005\u0003#\n\u0019+\u0003\u0003\u0002&\u0006M#A\u0002#pk\ndW-\u0001\u0010j[Bdwl\u00149`\tZ{6kX3r?\u00123vL\u00127pCR|v\n]!eIV\u0011\u00111\u0016\t\u000b\u0003s\ny(!,\u00020\u00065\u0006CBAG\u0003\u001f\u000by\u000b\u0005\u0003\u0002R\u0005E\u0016\u0002BAZ\u0003'\u0012QA\u00127pCR\fQ$[7qY~{\u0005o\u0018#W?N{V-]0E-~cuN\\4`\u001fB\fE\rZ\u000b\u0003\u0003s\u0003\"\"!\u001f\u0002\u0000\u0005m\u0016QXA^!\u0019\ti)a$\u0002>B!\u0011\u0011KA`\u0013\u0011\t\t-a\u0015\u0003\t1{gnZ\u0001\u001dS6\u0004HnX(q?\u00123vlU0fc~#ekX%oi~{\u0005oU;c+\t\t9\r\u0005\u0006\u0002J\u0006}\u00141RAJ\u0003\u0017sA!!\u0018\u0002L&!\u0011QZA \u0003\u0015y\u0005oU;c\u0003}IW\u000e\u001d7`\u001fB|FIV0T?\u0016\fx\f\u0012,`\t>,(\r\\3`\u001fB\u001cVOY\u000b\u0003\u0003'\u0004\"\"!3\u0002\u0000\u0005}\u0015\u0011UAP\u0003yIW\u000e\u001d7`\u001fB|FIV0T?\u0016\fx\f\u0012,`\r2|\u0017\r^0PaN+(-\u0006\u0002\u0002ZBQ\u0011\u0011ZA@\u0003[\u000by+!,\u0002;%l\u0007\u000f\\0Pa~#ekX*`KF|FIV0M_:<wl\u00149Tk\n,\"!a8\u0011\u0015\u0005%\u0017qPA^\u0003{\u000bY,\u0001\u0012j[Bdwl\u00149`\tZ{6kX3r?\u00123v,\u00138u?>\u0003X*\u001e7TG\u0006d\u0017M]\u000b\u0003\u0003K\u0004\"\"a:\u0002\u0000\u0005-\u00151SAF\u001d\u0011\ti&!;\n\t\u0005-\u0018qH\u0001\f\u001fBlU\u000f\\*dC2\f'/A\u0013j[Bdwl\u00149`\tZ{6kX3r?\u00123v\fR8vE2,wl\u00149Nk2\u001c6-\u00197beV\u0011\u0011\u0011\u001f\t\u000b\u0003O\fy(a(\u0002\"\u0006}\u0015\u0001J5na2|v\n]0E-~\u001bv,Z9`\tZ{f\t\\8bi~{\u0005/T;m'\u000e\fG.\u0019:\u0016\u0005\u0005]\bCCAt\u0003\u007f\ni+a,\u0002.\u0006\u0019\u0013.\u001c9m?>\u0003x\f\u0012,`'~+\u0017o\u0018#W?2{gnZ0Pa6+HnU2bY\u0006\u0014XCAA\u007f!)\t9/a \u0002<\u0006u\u00161X\u0001#S6\u0004HnX(q?\u00123vlU0fc~#ekX%oi~{\u0005/T;m\u001b\u0006$(/\u001b=\u0016\u0005\t\r\u0001C\u0003B\u0003\u0003\u007f\nY)a%\u0002\f:!\u0011Q\fB\u0004\u0013\u0011\u0011I!a\u0010\u0002\u0017=\u0003X*\u001e7NCR\u0014\u0018\u000e_\u0001&S6\u0004HnX(q?\u00123vlU0fc~#ek\u0018#pk\ndWmX(q\u001bVdW*\u0019;sSb,\"Aa\u0004\u0011\u0015\t\u0015\u0011qPAP\u0003C\u000by*\u0001\u0013j[Bdwl\u00149`\tZ{6kX3r?\u00123vL\u00127pCR|v\n]'vY6\u000bGO]5y+\t\u0011)\u0002\u0005\u0006\u0003\u0006\u0005}\u0014QVAX\u0003[\u000b1%[7qY~{\u0005o\u0018#W?N{V-]0E-~cuN\\4`\u001fBlU\u000f\\'biJL\u00070\u0006\u0002\u0003\u001cAQ!QAA@\u0003w\u000bi,a/\u00029%l\u0007\u000f\\0Pa~#ekX*`KF|FIV0J]R|v\n\u001d#jmV\u0011!\u0011\u0005\t\u000b\u0005G\ty(a#\u0002\u0014\u0006-e\u0002BA/\u0005KIAAa\n\u0002@\u0005)q\n\u001d#jm\u0006y\u0012.\u001c9m?>\u0003x\f\u0012,`'~+\u0017o\u0018#W?\u0012{WO\u00197f?>\u0003H)\u001b<\u0016\u0005\t5\u0002C\u0003B\u0012\u0003\u007f\ny*!)\u0002 \u0006q\u0012.\u001c9m?>\u0003x\f\u0012,`'~+\u0017o\u0018#W?\u001acw.\u0019;`\u001fB$\u0015N^\u000b\u0003\u0005g\u0001\"Ba\t\u0002\u0000\u00055\u0016qVAW\u0003uIW\u000e\u001d7`\u001fB|FIV0T?\u0016\fx\f\u0012,`\u0019>twmX(q\t&4XC\u0001B\u001d!)\u0011\u0019#a \u0002<\u0006u\u00161X\u0001\u001dS6\u0004HnX(q?\u00123vlU0fc~#ekX%oi~{\u0005oU3u+\t\u0011y\u0004\u0005\u0006\u0003B\u0005}\u00141RAJ\u0003\u0017sA!!\u0018\u0003D%!!QIA \u0003\u0015y\u0005oU3u\u0003}IW\u000e\u001d7`\u001fB|FIV0T?\u0016\fx\f\u0012,`\t>,(\r\\3`\u001fB\u001cV\r^\u000b\u0003\u0005\u0017\u0002\"B!\u0011\u0002\u0000\u0005}\u0015\u0011UAP\u0003yIW\u000e\u001d7`\u001fB|FIV0T?\u0016\fx\f\u0012,`\r2|\u0017\r^0PaN+G/\u0006\u0002\u0003RAQ!\u0011IA@\u0003[\u000by+!,\u0002;%l\u0007\u000f\\0Pa~#ekX*`KF|FIV0M_:<wl\u00149TKR,\"Aa\u0016\u0011\u0015\t\u0005\u0013qPA^\u0003{\u000bY,\u0001\u000fj[Bdwl\u00149`\tZ{6kX3r?\u00123v,\u00138u?>\u0003Xj\u001c3\u0016\u0005\tu\u0003C\u0003B0\u0003\u007f\nY)a%\u0002\f:!\u0011Q\fB1\u0013\u0011\u0011\u0019'a\u0010\u0002\u000b=\u0003Xj\u001c3\u0002?%l\u0007\u000f\\0Pa~#ekX*`KF|FIV0E_V\u0014G.Z0Pa6{G-\u0006\u0002\u0003jAQ!qLA@\u0003?\u000b\t+a(\u0002=%l\u0007\u000f\\0Pa~#ekX*`KF|FIV0GY>\fGoX(q\u001b>$WC\u0001B8!)\u0011y&a \u0002.\u0006=\u0016QV\u0001\u001eS6\u0004HnX(q?\u00123vlU0fc~#ek\u0018'p]\u001e|v\n]'pIV\u0011!Q\u000f\t\u000b\u0005?\ny(a/\u0002>\u0006m\u0016\u0001H5na2|v\n]0E-~\u001bv,Z9`\tZ{\u0016J\u001c;`\u001fB\u0004vn^\u000b\u0003\u0005w\u0002\"B! \u0002\u0000\u0005-\u00151SAF\u001d\u0011\tiFa \n\t\t\u0005\u0015qH\u0001\u0006\u001fB\u0004vn^\u0001 S6\u0004HnX(q?\u00123vlU0fc~#ek\u0018#pk\ndWmX(q!><XC\u0001BD!)\u0011i(a \u0002 \u0006\u0005\u0016qT\u0001\u001fS6\u0004HnX(q?\u00123vlU0fc~#ek\u0018$m_\u0006$xl\u00149Q_^,\"A!$\u0011\u0015\tu\u0014qPAW\u0003_\u000bi+A\u000fj[Bdwl\u00149`\tZ{6kX3r?\u00123v\fT8oO~{\u0005\u000fU8x+\t\u0011\u0019\n\u0005\u0006\u0003~\u0005}\u00141XA_\u0003w\u000bA$[7qY~{\u0005oX*`\tZ{V-]0E-~Ke\u000e^0Pa\u0006#G-\u0006\u0002\u0003\u001aBQ\u0011\u0011PA@\u0003'\u000bY)a#\u0002?%l\u0007\u000f\\0Pa~\u001bv\f\u0012,`KF|FIV0E_V\u0014G.Z0Pa\u0006#G-\u0006\u0002\u0003 BQ\u0011\u0011PA@\u0003C\u000by*a(\u0002=%l\u0007\u000f\\0Pa~\u001bv\f\u0012,`KF|FIV0GY>\fGoX(q\u0003\u0012$WC\u0001BS!)\tI(a \u00020\u00065\u0016QV\u0001\u001eS6\u0004HnX(q?N{FIV0fc~#ek\u0018'p]\u001e|v\n]!eIV\u0011!1\u0016\t\u000b\u0003s\ny(!0\u0002<\u0006m\u0016\u0001H5na2|v\n]0T?\u00123v,Z9`\tZ{\u0016J\u001c;`\u001fB\u001cVOY\u000b\u0003\u0005c\u0003\"\"!3\u0002\u0000\u0005M\u00151RAF\u0003}IW\u000e\u001d7`\u001fB|6k\u0018#W?\u0016\fx\f\u0012,`\t>,(\r\\3`\u001fB\u001cVOY\u000b\u0003\u0005o\u0003\"\"!3\u0002\u0000\u0005\u0005\u0016qTAP\u0003yIW\u000e\u001d7`\u001fB|6k\u0018#W?\u0016\fx\f\u0012,`\r2|\u0017\r^0PaN+(-\u0006\u0002\u0003>BQ\u0011\u0011ZA@\u0003_\u000bi+!,\u0002;%l\u0007\u000f\\0Pa~\u001bv\f\u0012,`KF|FIV0M_:<wl\u00149Tk\n,\"Aa1\u0011\u0015\u0005%\u0017qPA_\u0003w\u000bY,\u0001\u0012j[Bdwl\u00149`'~#ekX3r?\u00123v,\u00138u?>\u0003X*\u001e7TG\u0006d\u0017M]\u000b\u0003\u0005\u0013\u0004\"\"a:\u0002\u0000\u0005M\u00151RAF\u0003\u0015JW\u000e\u001d7`\u001fB|6k\u0018#W?\u0016\fx\f\u0012,`\t>,(\r\\3`\u001fBlU\u000f\\*dC2\f'/\u0006\u0002\u0003PBQ\u0011q]A@\u0003C\u000by*a(\u0002I%l\u0007\u000f\\0Pa~\u001bv\f\u0012,`KF|FIV0GY>\fGoX(q\u001bVd7kY1mCJ,\"A!6\u0011\u0015\u0005\u001d\u0018qPAX\u0003[\u000bi+A\u0012j[Bdwl\u00149`'~#ekX3r?\u00123v\fT8oO~{\u0005/T;m'\u000e\fG.\u0019:\u0016\u0005\tm\u0007CCAt\u0003\u007f\ni,a/\u0002<\u0006\u0011\u0013.\u001c9m?>\u0003xlU0E-~+\u0017o\u0018#W?&sGoX(q\u001bVdW*\u0019;sSb,\"A!9\u0011\u0015\t\u0015\u0011qPAJ\u0003\u0017\u000bY)A\u0013j[Bdwl\u00149`'~#ekX3r?\u00123v\fR8vE2,wl\u00149Nk2l\u0015\r\u001e:jqV\u0011!q\u001d\t\u000b\u0005\u000b\ty(!)\u0002 \u0006}\u0015\u0001J5na2|v\n]0T?\u00123v,Z9`\tZ{f\t\\8bi~{\u0005/T;m\u001b\u0006$(/\u001b=\u0016\u0005\t5\bC\u0003B\u0003\u0003\u007f\ny+!,\u0002.\u0006\u0019\u0013.\u001c9m?>\u0003xlU0E-~+\u0017o\u0018#W?2{gnZ0Pa6+H.T1ue&DXC\u0001Bz!)\u0011)!a \u0002>\u0006m\u00161X\u0001\u001dS6\u0004HnX(q?N{FIV0fc~#ekX%oi~{\u0005\u000fR5w+\t\u0011I\u0010\u0005\u0006\u0003$\u0005}\u00141SAF\u0003\u0017\u000bq$[7qY~{\u0005oX*`\tZ{V-]0E-~#u.\u001e2mK~{\u0005\u000fR5w+\t\u0011y\u0010\u0005\u0006\u0003$\u0005}\u0014\u0011UAP\u0003?\u000ba$[7qY~{\u0005oX*`\tZ{V-]0E-~3En\\1u?>\u0003H)\u001b<\u0016\u0005\r\u0015\u0001C\u0003B\u0012\u0003\u007f\ny+!,\u0002.\u0006i\u0012.\u001c9m?>\u0003xlU0E-~+\u0017o\u0018#W?2{gnZ0Pa\u0012Kg/\u0006\u0002\u0004\fAQ!1EA@\u0003{\u000bY,a/\u00029%l\u0007\u000f\\0Pa~\u001bv\f\u0012,`KF|FIV0J]R|v\n]*fiV\u00111\u0011\u0003\t\u000b\u0005\u0003\ny(a%\u0002\f\u0006-\u0015aH5na2|v\n]0T?\u00123v,Z9`\tZ{Fi\\;cY\u0016|v\n]*fiV\u00111q\u0003\t\u000b\u0005\u0003\ny(!)\u0002 \u0006}\u0015AH5na2|v\n]0T?\u00123v,Z9`\tZ{f\t\\8bi~{\u0005oU3u+\t\u0019i\u0002\u0005\u0006\u0003B\u0005}\u0014qVAW\u0003[\u000bQ$[7qY~{\u0005oX*`\tZ{V-]0E-~cuN\\4`\u001fB\u001cV\r^\u000b\u0003\u0007G\u0001\"B!\u0011\u0002\u0000\u0005u\u00161XA^\u0003qIW\u000e\u001d7`\u001fB|6k\u0018#W?\u0016\fx\f\u0012,`\u0013:$xl\u00149N_\u0012,\"a!\u000b\u0011\u0015\t}\u0013qPAJ\u0003\u0017\u000bY)A\u0010j[Bdwl\u00149`'~#ekX3r?\u00123v\fR8vE2,wl\u00149N_\u0012,\"aa\f\u0011\u0015\t}\u0013qPAQ\u0003?\u000by*\u0001\u0010j[Bdwl\u00149`'~#ekX3r?\u00123vL\u00127pCR|v\n]'pIV\u00111Q\u0007\t\u000b\u0005?\ny(a,\u0002.\u00065\u0016!H5na2|v\n]0T?\u00123v,Z9`\tZ{Fj\u001c8h?>\u0003Xj\u001c3\u0016\u0005\rm\u0002C\u0003B0\u0003\u007f\ni,a/\u0002<\u0006a\u0012.\u001c9m?>\u0003xlU0E-~+\u0017o\u0018#W?&sGoX(q!><XCAB!!)\u0011i(a \u0002\u0014\u0006-\u00151R\u0001 S6\u0004HnX(q?N{FIV0fc~#ek\u0018#pk\ndWmX(q!><XCAB$!)\u0011i(a \u0002\"\u0006}\u0015qT\u0001\u001fS6\u0004HnX(q?N{FIV0fc~#ek\u0018$m_\u0006$xl\u00149Q_^,\"a!\u0014\u0011\u0015\tu\u0014qPAX\u0003[\u000bi+A\u000fj[Bdwl\u00149`'~#ekX3r?\u00123v\fT8oO~{\u0005\u000fU8x+\t\u0019\u0019\u0006\u0005\u0006\u0003~\u0005}\u0014QXA^\u0003w\u000bQ$[7qY~{\u0005o\u0018#W?\u00123v,Z9`\tZ{\u0016J\u001c;`\u001fB\fE\rZ\u000b\u0003\u00073\u0002\"\"!\u001f\u0002\u0000\u0005-\u00151RAF\u0003\u0001JW\u000e\u001d7`\u001fB|FIV0E-~+\u0017o\u0018#W?\u0012{WO\u00197f?>\u0003\u0018\t\u001a3\u0016\u0005\r}\u0003CCA=\u0003\u007f\ny*a(\u0002 \u0006y\u0012.\u001c9m?>\u0003x\f\u0012,`\tZ{V-]0E-~3En\\1u?>\u0003\u0018\t\u001a3\u0016\u0005\r\u0015\u0004CCA=\u0003\u007f\ni+!,\u0002.\u0006q\u0012.\u001c9m?>\u0003x\f\u0012,`\tZ{V-]0E-~cuN\\4`\u001fB\fE\rZ\u000b\u0003\u0007W\u0002\"\"!\u001f\u0002\u0000\u0005m\u00161XA^\u0003uIW\u000e\u001d7`\u001fB|FIV0E-~+\u0017o\u0018#W?&sGoX(q'V\u0014WCAB9!)\tI-a \u0002\f\u0006-\u00151R\u0001!S6\u0004HnX(q?\u00123v\f\u0012,`KF|FIV0E_V\u0014G.Z0PaN+(-\u0006\u0002\u0004xAQ\u0011\u0011ZA@\u0003?\u000by*a(\u0002?%l\u0007\u000f\\0Pa~#ek\u0018#W?\u0016\fx\f\u0012,`\r2|\u0017\r^0PaN+(-\u0006\u0002\u0004~AQ\u0011\u0011ZA@\u0003[\u000bi+!,\u0002=%l\u0007\u000f\\0Pa~#ek\u0018#W?\u0016\fx\f\u0012,`\u0019>twmX(q'V\u0014WCABB!)\tI-a \u0002<\u0006m\u00161X\u0001$S6\u0004HnX(q?\u00123v\f\u0012,`KF|FIV0J]R|v\n]'vYN\u001b\u0017\r\\1s+\t\u0019I\t\u0005\u0006\u0002h\u0006}\u00141RAF\u0003\u0017\u000ba%[7qY~{\u0005o\u0018#W?\u00123v,Z9`\tZ{Fi\\;cY\u0016|v\n]'vYN\u001b\u0017\r\\1s+\t\u0019y\t\u0005\u0006\u0002h\u0006}\u0014qTAP\u0003?\u000bQ%[7qY~{\u0005o\u0018#W?\u00123v,Z9`\tZ{f\t\\8bi~{\u0005/T;m'\u000e\fG.\u0019:\u0016\u0005\rU\u0005CCAt\u0003\u007f\ni+!,\u0002.\u0006!\u0013.\u001c9m?>\u0003x\f\u0012,`\tZ{V-]0E-~cuN\\4`\u001fBlU\u000f\\*dC2\f'/\u0006\u0002\u0004\u001cBQ\u0011q]A@\u0003w\u000bY,a/\u0002;%l\u0007\u000f\\0Pa~#ek\u0018#W?\u0016\fx\f\u0012,`\u0013:$xl\u00149ESZ,\"a!)\u0011\u0015\t\r\u0012qPAF\u0003\u0017\u000bY)\u0001\u0011j[Bdwl\u00149`\tZ{FIV0fc~#ek\u0018#pk\ndWmX(q\t&4XCABT!)\u0011\u0019#a \u0002 \u0006}\u0015qT\u0001 S6\u0004HnX(q?\u00123v\f\u0012,`KF|FIV0GY>\fGoX(q\t&4XCABW!)\u0011\u0019#a \u0002.\u00065\u0016QV\u0001\u001fS6\u0004HnX(q?\u00123v\f\u0012,`KF|FIV0M_:<wl\u00149ESZ,\"aa-\u0011\u0015\t\r\u0012qPA^\u0003w\u000bY,A\u000fj[Bdwl\u00149`\tZ{FIV0fc~#ekX%oi~{\u0005oU3u+\t\u0019I\f\u0005\u0006\u0003B\u0005}\u00141RAF\u0003\u0017\u000b\u0001%[7qY~{\u0005o\u0018#W?\u00123v,Z9`\tZ{Fi\\;cY\u0016|v\n]*fiV\u00111q\u0018\t\u000b\u0005\u0003\ny(a(\u0002 \u0006}\u0015aH5na2|v\n]0E-~#ekX3r?\u00123vL\u00127pCR|v\n]*fiV\u00111Q\u0019\t\u000b\u0005\u0003\ny(!,\u0002.\u00065\u0016AH5na2|v\n]0E-~#ekX3r?\u00123v\fT8oO~{\u0005oU3u+\t\u0019Y\r\u0005\u0006\u0003B\u0005}\u00141XA^\u0003w\u000bQ$[7qY~{\u0005o\u0018#W?\u00123v,Z9`\tZ{\u0016J\u001c;`\u001fBlu\u000eZ\u000b\u0003\u0007#\u0004\"Ba\u0018\u0002\u0000\u0005-\u00151RAF\u0003\u0001JW\u000e\u001d7`\u001fB|FIV0E-~+\u0017o\u0018#W?\u0012{WO\u00197f?>\u0003Xj\u001c3\u0016\u0005\r]\u0007C\u0003B0\u0003\u007f\ny*a(\u0002 \u0006y\u0012.\u001c9m?>\u0003x\f\u0012,`\tZ{V-]0E-~3En\\1u?>\u0003Xj\u001c3\u0016\u0005\ru\u0007C\u0003B0\u0003\u007f\ni+!,\u0002.\u0006q\u0012.\u001c9m?>\u0003x\f\u0012,`\tZ{V-]0E-~cuN\\4`\u001fBlu\u000eZ\u000b\u0003\u0007G\u0004\"Ba\u0018\u0002\u0000\u0005m\u00161XA^\u0003uIW\u000e\u001d7`\u001fB|FIV0E-~+\u0017o\u0018#W?&sGoX(q!><XCABu!)\u0011i(a \u0002\f\u0006-\u00151R\u0001!S6\u0004HnX(q?\u00123v\f\u0012,`KF|FIV0E_V\u0014G.Z0PaB{w/\u0006\u0002\u0004pBQ!QPA@\u0003?\u000by*a(\u0002?%l\u0007\u000f\\0Pa~#ek\u0018#W?\u0016\fx\f\u0012,`\r2|\u0017\r^0PaB{w/\u0006\u0002\u0004vBQ!QPA@\u0003[\u000bi+!,\u0002=%l\u0007\u000f\\0Pa~#ek\u0018#W?\u0016\fx\f\u0012,`\u0019>twmX(q!><XCAB~!)\u0011i(a \u0002<\u0006m\u00161X\u0001 S6\u0004HnX(q?&s\u0007\u000b\\1dK~#ek\u0018#W?&sGoX(q\u0003\u0012$WC\u0001C\u0001!!\tI\bb\u0001\u0002\f\u0006-\u0015\u0002\u0002C\u0003\u0003\u0007\u0013A\"\u00138QY\u0006\u001cW-S7qYJ\n!%[7qY~{\u0005oX%o!2\f7-Z0E-~#ek\u0018#pk\ndWmX(q\u0003\u0012$WC\u0001C\u0006!!\tI\bb\u0001\u0002 \u0006}\u0015!I5na2|v\n]0J]Bc\u0017mY3`\tZ{FIV0GY>\fGoX(q\u0003\u0012$WC\u0001C\t!!\tI\bb\u0001\u0002.\u00065\u0016\u0001I5na2|v\n]0J]Bc\u0017mY3`\tZ{FIV0M_:<wl\u00149BI\u0012,\"\u0001b\u0006\u0011\u0011\u0005eD1AA^\u0003w\u000bq$[7qY~{\u0005oX%o!2\f7-Z0E-~#ekX%oi~{\u0005oU;c+\t!i\u0002\u0005\u0005\u0002J\u0012\r\u00111RAF\u0003\tJW\u000e\u001d7`\u001fB|\u0016J\u001c)mC\u000e,w\f\u0012,`\tZ{Fi\\;cY\u0016|v\n]*vEV\u0011A1\u0005\t\t\u0003\u0013$\u0019!a(\u0002 \u0006\t\u0013.\u001c9m?>\u0003x,\u00138QY\u0006\u001cWm\u0018#W?\u00123vL\u00127pCR|v\n]*vEV\u0011A\u0011\u0006\t\t\u0003\u0013$\u0019!!,\u0002.\u0006\u0001\u0013.\u001c9m?>\u0003x,\u00138QY\u0006\u001cWm\u0018#W?\u00123v\fT8oO~{\u0005oU;c+\t!y\u0003\u0005\u0005\u0002J\u0012\r\u00111XA^\u0003\u0015JW\u000e\u001d7`\u001fB|\u0016J\u001c)mC\u000e,w\f\u0012,`\tZ{\u0016J\u001c;`\u001fBlU\u000f\\*dC2\f'/\u0006\u0002\u00056AA\u0011q\u001dC\u0002\u0003\u0017\u000bY)\u0001\u0015j[Bdwl\u00149`\u0013:\u0004F.Y2f?\u00123v\f\u0012,`\t>,(\r\\3`\u001fBlU\u000f\\*dC2\f'/\u0006\u0002\u0005<AA\u0011q\u001dC\u0002\u0003?\u000by*A\u0014j[Bdwl\u00149`\u0013:\u0004F.Y2f?\u00123v\f\u0012,`\r2|\u0017\r^0Pa6+HnU2bY\u0006\u0014XC\u0001C!!!\t9\u000fb\u0001\u0002.\u00065\u0016AJ5na2|v\n]0J]Bc\u0017mY3`\tZ{FIV0M_:<wl\u00149Nk2\u001c6-\u00197beV\u0011Aq\t\t\t\u0003O$\u0019!a/\u0002<\u0006y\u0012.\u001c9m?>\u0003x,\u00138QY\u0006\u001cWm\u0018#W?\u00123v,\u00138u?>\u0003H)\u001b<\u0016\u0005\u00115\u0003\u0003\u0003B\u0012\t\u0007\tY)a#\u0002E%l\u0007\u000f\\0Pa~Ke\u000e\u00157bG\u0016|FIV0E-~#u.\u001e2mK~{\u0005\u000fR5w+\t!\u0019\u0006\u0005\u0005\u0003$\u0011\r\u0011qTAP\u0003\u0005JW\u000e\u001d7`\u001fB|\u0016J\u001c)mC\u000e,w\f\u0012,`\tZ{f\t\\8bi~{\u0005\u000fR5w+\t!I\u0006\u0005\u0005\u0003$\u0011\r\u0011QVAW\u0003\u0001JW\u000e\u001d7`\u001fB|\u0016J\u001c)mC\u000e,w\f\u0012,`\tZ{Fj\u001c8h?>\u0003H)\u001b<\u0016\u0005\u0011}\u0003\u0003\u0003B\u0012\t\u0007\tY,a/\u0002?%l\u0007\u000f\\0Pa~Ke\u000e\u00157bG\u0016|FIV0E-~Ke\u000e^0PaN+G/\u0006\u0002\u0005fAA!\u0011\tC\u0002\u0003\u0017\u000bY)\u0001\u0012j[Bdwl\u00149`\u0013:\u0004F.Y2f?\u00123v\f\u0012,`\t>,(\r\\3`\u001fB\u001cV\r^\u000b\u0003\tW\u0002\u0002B!\u0011\u0005\u0004\u0005}\u0015qT\u0001\"S6\u0004HnX(q?&s\u0007\u000b\\1dK~#ek\u0018#W?\u001acw.\u0019;`\u001fB\u001cV\r^\u000b\u0003\tc\u0002\u0002B!\u0011\u0005\u0004\u00055\u0016QV\u0001!S6\u0004HnX(q?&s\u0007\u000b\\1dK~#ek\u0018#W?2{gnZ0PaN+G/\u0006\u0002\u0005xAA!\u0011\tC\u0002\u0003w\u000bY,A\u0010j[Bdwl\u00149`\u0013:\u0004F.Y2f?\u00123v\f\u0012,`\u0013:$xl\u00149N_\u0012,\"\u0001\" \u0011\u0011\t}C1AAF\u0003\u0017\u000b!%[7qY~{\u0005oX%o!2\f7-Z0E-~#ek\u0018#pk\ndWmX(q\u001b>$WC\u0001CB!!\u0011y\u0006b\u0001\u0002 \u0006}\u0015!I5na2|v\n]0J]Bc\u0017mY3`\tZ{FIV0GY>\fGoX(q\u001b>$WC\u0001CE!!\u0011y\u0006b\u0001\u0002.\u00065\u0016\u0001I5na2|v\n]0J]Bc\u0017mY3`\tZ{FIV0M_:<wl\u00149N_\u0012,\"\u0001b$\u0011\u0011\t}C1AA^\u0003w\u000bq$[7qY~{\u0005oX%o!2\f7-Z0E-~#ekX%oi~{\u0005\u000fU8x+\t!)\n\u0005\u0005\u0003~\u0011\r\u00111RAF\u0003\tJW\u000e\u001d7`\u001fB|\u0016J\u001c)mC\u000e,w\f\u0012,`\tZ{Fi\\;cY\u0016|v\n\u001d)poV\u0011A1\u0014\t\t\u0005{\"\u0019!a(\u0002 \u0006\t\u0013.\u001c9m?>\u0003x,\u00138QY\u0006\u001cWm\u0018#W?\u00123vL\u00127pCR|v\n\u001d)poV\u0011A\u0011\u0015\t\t\u0005{\"\u0019!!,\u0002.\u0006\u0001\u0013.\u001c9m?>\u0003x,\u00138QY\u0006\u001cWm\u0018#W?\u00123v\fT8oO~{\u0005\u000fU8x+\t!9\u000b\u0005\u0005\u0003~\u0011\r\u00111XA^\u0003yIW\u000e\u001d7`\u001fB|\u0016J\u001c)mC\u000e,w\f\u0012,`'~Ke\u000e^0Pa\u0006#G-\u0006\u0002\u0005.BA\u0011\u0011\u0010C\u0002\u0003\u0017\u000b\u0019*A\u0011j[Bdwl\u00149`\u0013:\u0004F.Y2f?\u00123vlU0E_V\u0014G.Z0Pa\u0006#G-\u0006\u0002\u00054BA\u0011\u0011\u0010C\u0002\u0003?\u000b\t+\u0001\u0011j[Bdwl\u00149`\u0013:\u0004F.Y2f?\u00123vlU0GY>\fGoX(q\u0003\u0012$WC\u0001C]!!\tI\bb\u0001\u0002.\u0006=\u0016aH5na2|v\n]0J]Bc\u0017mY3`\tZ{6k\u0018'p]\u001e|v\n]!eIV\u0011Aq\u0018\t\t\u0003s\"\u0019!a/\u0002>\u0006q\u0012.\u001c9m?>\u0003x,\u00138QY\u0006\u001cWm\u0018#W?N{\u0016J\u001c;`\u001fB\u001cVOY\u000b\u0003\t\u000b\u0004\u0002\"!3\u0005\u0004\u0005-\u00151S\u0001\"S6\u0004HnX(q?&s\u0007\u000b\\1dK~#ekX*`\t>,(\r\\3`\u001fB\u001cVOY\u000b\u0003\t\u0017\u0004\u0002\"!3\u0005\u0004\u0005}\u0015\u0011U\u0001!S6\u0004HnX(q?&s\u0007\u000b\\1dK~#ekX*`\r2|\u0017\r^0PaN+(-\u0006\u0002\u0005RBA\u0011\u0011\u001aC\u0002\u0003[\u000by+A\u0010j[Bdwl\u00149`\u0013:\u0004F.Y2f?\u00123vlU0M_:<wl\u00149Tk\n,\"\u0001b6\u0011\u0011\u0005%G1AA^\u0003{\u000bA%[7qY~{\u0005oX%o!2\f7-Z0E-~\u001bv,\u00138u?>\u0003X*\u001e7TG\u0006d\u0017M]\u000b\u0003\t;\u0004\u0002\"a:\u0005\u0004\u0005-\u00151S\u0001(S6\u0004HnX(q?&s\u0007\u000b\\1dK~#ekX*`\t>,(\r\\3`\u001fBlU\u000f\\*dC2\f'/\u0006\u0002\u0005dBA\u0011q\u001dC\u0002\u0003?\u000b\t+\u0001\u0014j[Bdwl\u00149`\u0013:\u0004F.Y2f?\u00123vlU0GY>\fGoX(q\u001bVd7kY1mCJ,\"\u0001\";\u0011\u0011\u0005\u001dH1AAW\u0003_\u000bQ%[7qY~{\u0005oX%o!2\f7-Z0E-~\u001bv\fT8oO~{\u0005/T;m'\u000e\fG.\u0019:\u0016\u0005\u0011=\b\u0003CAt\t\u0007\tY,!0\u0002I%l\u0007\u000f\\0Pa~Ke\u000e\u00157bG\u0016|FIV0T?&sGoX(q\u001bVdW*\u0019;sSb,\"\u0001\">\u0011\u0011\t\u0015A1AAF\u0003'\u000bq%[7qY~{\u0005oX%o!2\f7-Z0E-~\u001bv\fR8vE2,wl\u00149Nk2l\u0015\r\u001e:jqV\u0011A1 \t\t\u0005\u000b!\u0019!a(\u0002\"\u00061\u0013.\u001c9m?>\u0003x,\u00138QY\u0006\u001cWm\u0018#W?N{f\t\\8bi~{\u0005/T;m\u001b\u0006$(/\u001b=\u0016\u0005\u0015\u0005\u0001\u0003\u0003B\u0003\t\u0007\ti+a,\u0002K%l\u0007\u000f\\0Pa~Ke\u000e\u00157bG\u0016|FIV0T?2{gnZ0Pa6+H.T1ue&DXCAC\u0004!!\u0011)\u0001b\u0001\u0002<\u0006u\u0016AH5na2|v\n]0J]Bc\u0017mY3`\tZ{6kX%oi~{\u0005\u000fR5w+\t)i\u0001\u0005\u0005\u0003$\u0011\r\u00111RAJ\u0003\u0005JW\u000e\u001d7`\u001fB|\u0016J\u001c)mC\u000e,w\f\u0012,`'~#u.\u001e2mK~{\u0005\u000fR5w+\t)\u0019\u0002\u0005\u0005\u0003$\u0011\r\u0011qTAQ\u0003\u0001JW\u000e\u001d7`\u001fB|\u0016J\u001c)mC\u000e,w\f\u0012,`'~3En\\1u?>\u0003H)\u001b<\u0016\u0005\u0015e\u0001\u0003\u0003B\u0012\t\u0007\ti+a,\u0002?%l\u0007\u000f\\0Pa~Ke\u000e\u00157bG\u0016|FIV0T?2{gnZ0Pa\u0012Kg/\u0006\u0002\u0006 AA!1\u0005C\u0002\u0003w\u000bi,\u0001\u0010j[Bdwl\u00149`\u0013:\u0004F.Y2f?\u00123vlU0J]R|v\n]*fiV\u0011QQ\u0005\t\t\u0005\u0003\"\u0019!a#\u0002\u0014\u0006\t\u0013.\u001c9m?>\u0003x,\u00138QY\u0006\u001cWm\u0018#W?N{Fi\\;cY\u0016|v\n]*fiV\u0011Q1\u0006\t\t\u0005\u0003\"\u0019!a(\u0002\"\u0006\u0001\u0013.\u001c9m?>\u0003x,\u00138QY\u0006\u001cWm\u0018#W?N{f\t\\8bi~{\u0005oU3u+\t)\t\u0004\u0005\u0005\u0003B\u0011\r\u0011QVAX\u0003}IW\u000e\u001d7`\u001fB|\u0016J\u001c)mC\u000e,w\f\u0012,`'~cuN\\4`\u001fB\u001cV\r^\u000b\u0003\u000bo\u0001\u0002B!\u0011\u0005\u0004\u0005m\u0016QX\u0001\u001fS6\u0004HnX(q?&s\u0007\u000b\\1dK~#ekX*`\u0013:$xl\u00149N_\u0012,\"!\"\u0010\u0011\u0011\t}C1AAF\u0003'\u000b\u0011%[7qY~{\u0005oX%o!2\f7-Z0E-~\u001bv\fR8vE2,wl\u00149N_\u0012,\"!b\u0011\u0011\u0011\t}C1AAP\u0003C\u000b\u0001%[7qY~{\u0005oX%o!2\f7-Z0E-~\u001bvL\u00127pCR|v\n]'pIV\u0011Q\u0011\n\t\t\u0005?\"\u0019!!,\u00020\u0006y\u0012.\u001c9m?>\u0003x,\u00138QY\u0006\u001cWm\u0018#W?N{Fj\u001c8h?>\u0003Xj\u001c3\u0016\u0005\u0015=\u0003\u0003\u0003B0\t\u0007\tY,!0\u0002=%l\u0007\u000f\\0Pa6+H.\u00138oKJ|FIV0E-~+\u0017oX*`\u0013:$XCAC+!))9&a \u0002\f\u0006-\u00151\u0013\b\u0005\u000b3*YG\u0004\u0003\u0006\\\u0015%d\u0002BC/\u000bOrA!b\u0018\u0006f5\u0011Q\u0011\r\u0006\u0005\u000bG\nY%\u0001\u0004=e>|GOP\u0005\u0003\u0003\u0013JA!!\u0012\u0002H%!\u0011\u0011IA\"\u0013\u0011)i'a\u0010\u0002\u0015=\u0003X*\u001e7J]:,'/A\u0010j[Bdwl\u00149Nk2LeN\\3s?\u00123v\f\u0012,`KF|6k\u0018'p]\u001e,\"!b\u001d\u0011\u0015\u0015]\u0013qPA^\u0003w\u000bi,\u0001\rj[BdwL_5q-\u0006dW/Z:`\tZ{FIV0J]R,\"!\"\u001f\u0011\u0015\u0015m\u0014qPAF\u0003\u0017+\tI\u0004\u0003\u0002\u000e\u0016u\u0014\u0002BC@\u0003\u0007\n\u0011B_5q-\u0006dW/Z:\u0011\u0011\u00055U1QAJ\u0003'KA!\"\"\u0002D\ta!,\u001b9qK\u00124\u0016\r\\;fg\u0006Y\u0012.\u001c9m?jL\u0007OV1mk\u0016\u001cx\f\u0012,`\tZ{Fi\\;cY\u0016,\"!b#\u0011\u0015\u0015m\u0014qPAP\u0003?+i\t\u0005\u0005\u0002\u000e\u0016\r\u0015\u0011UAQ\u0003iIW\u000e\u001d7`u&\u0004h+\u00197vKN|FIV0E-~3En\\1u+\t)\u0019\n\u0005\u0006\u0006|\u0005}\u0014QVAW\u000b+\u0003\u0002\"!$\u0006\u0004\u0006=\u0016qV\u0001\u001aS6\u0004Hn\u0018>jaZ\u000bG.^3t?\u00123v\f\u0012,`\u0019>tw-\u0006\u0002\u0006\u001cBQQ1PA@\u0003w\u000bY,\"(\u0011\u0011\u00055U1QA_\u0003{\u000b\u0011%[7qY~\u001b8-\u00197f\u0003\u0012$w,\u00138QY\u0006\u001cWm\u0018#W?N{FIV0J]R,\"!b)\u0011\u0015\u0015\u0015V1VAF\u0003'\u000bYI\u0004\u0003\u0002\u000e\u0016\u001d\u0016\u0002BCU\u0003\u0007\n\u0001b]2bY\u0016\fE\rZ\u0005\u0005\u000b[\u000b\u0019I\u0001\u0007J]Bc\u0017mY3J[Bd7'\u0001\u0012j[Bdwl]2bY\u0016\fE\rZ0J]Bc\u0017mY3`\tZ{6k\u0018#W?2{gnZ\u000b\u0003\u000bg\u0003\"\"\"*\u0006,\u0006m\u0016QXA^\u0001"
)
public interface DenseVectorExpandOps extends DenseVector_Vector_ExpandOps {
   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Int_OpAdd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Double_OpAdd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Float_OpAdd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Long_OpAdd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Int_OpSub_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Double_OpSub_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Float_OpSub_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Long_OpSub_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Int_OpMulScalar_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Double_OpMulScalar_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Float_OpMulScalar_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Long_OpMulScalar_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Int_OpMulMatrix_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Double_OpMulMatrix_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Float_OpMulMatrix_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Long_OpMulMatrix_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Int_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Double_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Float_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Long_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Int_OpSet_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Double_OpSet_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Float_OpSet_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Long_OpSet_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Int_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Double_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Float_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Long_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Int_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Double_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Float_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Long_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Int_OpAdd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Double_OpAdd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Float_OpAdd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Long_OpAdd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Int_OpSub_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Double_OpSub_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Float_OpSub_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Long_OpSub_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Int_OpMulScalar_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Double_OpMulScalar_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Float_OpMulScalar_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Long_OpMulScalar_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Int_OpMulMatrix_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Double_OpMulMatrix_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Float_OpMulMatrix_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Long_OpMulMatrix_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Int_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Double_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Float_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Long_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Int_OpSet_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Double_OpSet_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Float_OpSet_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Long_OpSet_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Int_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Double_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Float_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Long_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Int_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Double_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Float_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Long_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Int_OpAdd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Double_OpAdd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Float_OpAdd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Long_OpAdd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Int_OpSub_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Double_OpSub_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Float_OpSub_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Long_OpSub_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Int_OpMulScalar_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Double_OpMulScalar_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Float_OpMulScalar_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Long_OpMulScalar_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Int_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Double_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Float_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Long_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Int_OpSet_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Double_OpSet_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Float_OpSet_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Long_OpSet_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Int_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Double_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Float_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Long_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Int_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Double_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Float_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Long_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Int_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Double_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Float_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Long_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Int_OpSub_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Double_OpSub_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Float_OpSub_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Long_OpSub_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Int_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Double_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Float_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Long_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Int_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Double_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Float_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Long_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Int_OpSet_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Double_OpSet_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Float_OpSet_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Long_OpSet_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Int_OpMod_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Double_OpMod_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Float_OpMod_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Long_OpMod_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Int_OpPow_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Double_OpPow_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Float_OpPow_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Long_OpPow_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Int_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Double_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Float_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Long_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Int_OpSub_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Double_OpSub_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Float_OpSub_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Long_OpSub_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Int_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Double_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Float_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Long_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Int_OpMulMatrix_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Double_OpMulMatrix_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Float_OpMulMatrix_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Long_OpMulMatrix_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Int_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Double_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Float_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Long_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Int_OpSet_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Double_OpSet_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Float_OpSet_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Long_OpSet_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Int_OpMod_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Double_OpMod_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Float_OpMod_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Long_OpMod_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_OpMulInner_DV_DV_eq_S_Int_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_OpMulInner_DV_DV_eq_S_Long_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_zipValues_DV_DV_Int_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_zipValues_DV_DV_Double_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_zipValues_DV_DV_Float_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_zipValues_DV_DV_Long_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_scaleAdd_InPlace_DV_S_DV_Int_$eq(final UFunc.InPlaceImpl3 x$1);

   void breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_scaleAdd_InPlace_DV_S_DV_Long_$eq(final UFunc.InPlaceImpl3 x$1);

   UFunc.UImpl2 impl_Op_DV_S_eq_DV_Int_OpAdd();

   UFunc.UImpl2 impl_Op_DV_S_eq_DV_Double_OpAdd();

   UFunc.UImpl2 impl_Op_DV_S_eq_DV_Float_OpAdd();

   UFunc.UImpl2 impl_Op_DV_S_eq_DV_Long_OpAdd();

   UFunc.UImpl2 impl_Op_DV_S_eq_DV_Int_OpSub();

   UFunc.UImpl2 impl_Op_DV_S_eq_DV_Double_OpSub();

   UFunc.UImpl2 impl_Op_DV_S_eq_DV_Float_OpSub();

   UFunc.UImpl2 impl_Op_DV_S_eq_DV_Long_OpSub();

   UFunc.UImpl2 impl_Op_DV_S_eq_DV_Int_OpMulScalar();

   UFunc.UImpl2 impl_Op_DV_S_eq_DV_Double_OpMulScalar();

   UFunc.UImpl2 impl_Op_DV_S_eq_DV_Float_OpMulScalar();

   UFunc.UImpl2 impl_Op_DV_S_eq_DV_Long_OpMulScalar();

   UFunc.UImpl2 impl_Op_DV_S_eq_DV_Int_OpMulMatrix();

   UFunc.UImpl2 impl_Op_DV_S_eq_DV_Double_OpMulMatrix();

   UFunc.UImpl2 impl_Op_DV_S_eq_DV_Float_OpMulMatrix();

   UFunc.UImpl2 impl_Op_DV_S_eq_DV_Long_OpMulMatrix();

   UFunc.UImpl2 impl_Op_DV_S_eq_DV_Int_OpDiv();

   UFunc.UImpl2 impl_Op_DV_S_eq_DV_Double_OpDiv();

   UFunc.UImpl2 impl_Op_DV_S_eq_DV_Float_OpDiv();

   UFunc.UImpl2 impl_Op_DV_S_eq_DV_Long_OpDiv();

   UFunc.UImpl2 impl_Op_DV_S_eq_DV_Int_OpSet();

   UFunc.UImpl2 impl_Op_DV_S_eq_DV_Double_OpSet();

   UFunc.UImpl2 impl_Op_DV_S_eq_DV_Float_OpSet();

   UFunc.UImpl2 impl_Op_DV_S_eq_DV_Long_OpSet();

   UFunc.UImpl2 impl_Op_DV_S_eq_DV_Int_OpMod();

   UFunc.UImpl2 impl_Op_DV_S_eq_DV_Double_OpMod();

   UFunc.UImpl2 impl_Op_DV_S_eq_DV_Float_OpMod();

   UFunc.UImpl2 impl_Op_DV_S_eq_DV_Long_OpMod();

   UFunc.UImpl2 impl_Op_DV_S_eq_DV_Int_OpPow();

   UFunc.UImpl2 impl_Op_DV_S_eq_DV_Double_OpPow();

   UFunc.UImpl2 impl_Op_DV_S_eq_DV_Float_OpPow();

   UFunc.UImpl2 impl_Op_DV_S_eq_DV_Long_OpPow();

   UFunc.UImpl2 impl_Op_S_DV_eq_DV_Int_OpAdd();

   UFunc.UImpl2 impl_Op_S_DV_eq_DV_Double_OpAdd();

   UFunc.UImpl2 impl_Op_S_DV_eq_DV_Float_OpAdd();

   UFunc.UImpl2 impl_Op_S_DV_eq_DV_Long_OpAdd();

   UFunc.UImpl2 impl_Op_S_DV_eq_DV_Int_OpSub();

   UFunc.UImpl2 impl_Op_S_DV_eq_DV_Double_OpSub();

   UFunc.UImpl2 impl_Op_S_DV_eq_DV_Float_OpSub();

   UFunc.UImpl2 impl_Op_S_DV_eq_DV_Long_OpSub();

   UFunc.UImpl2 impl_Op_S_DV_eq_DV_Int_OpMulScalar();

   UFunc.UImpl2 impl_Op_S_DV_eq_DV_Double_OpMulScalar();

   UFunc.UImpl2 impl_Op_S_DV_eq_DV_Float_OpMulScalar();

   UFunc.UImpl2 impl_Op_S_DV_eq_DV_Long_OpMulScalar();

   UFunc.UImpl2 impl_Op_S_DV_eq_DV_Int_OpMulMatrix();

   UFunc.UImpl2 impl_Op_S_DV_eq_DV_Double_OpMulMatrix();

   UFunc.UImpl2 impl_Op_S_DV_eq_DV_Float_OpMulMatrix();

   UFunc.UImpl2 impl_Op_S_DV_eq_DV_Long_OpMulMatrix();

   UFunc.UImpl2 impl_Op_S_DV_eq_DV_Int_OpDiv();

   UFunc.UImpl2 impl_Op_S_DV_eq_DV_Double_OpDiv();

   UFunc.UImpl2 impl_Op_S_DV_eq_DV_Float_OpDiv();

   UFunc.UImpl2 impl_Op_S_DV_eq_DV_Long_OpDiv();

   UFunc.UImpl2 impl_Op_S_DV_eq_DV_Int_OpSet();

   UFunc.UImpl2 impl_Op_S_DV_eq_DV_Double_OpSet();

   UFunc.UImpl2 impl_Op_S_DV_eq_DV_Float_OpSet();

   UFunc.UImpl2 impl_Op_S_DV_eq_DV_Long_OpSet();

   UFunc.UImpl2 impl_Op_S_DV_eq_DV_Int_OpMod();

   UFunc.UImpl2 impl_Op_S_DV_eq_DV_Double_OpMod();

   UFunc.UImpl2 impl_Op_S_DV_eq_DV_Float_OpMod();

   UFunc.UImpl2 impl_Op_S_DV_eq_DV_Long_OpMod();

   UFunc.UImpl2 impl_Op_S_DV_eq_DV_Int_OpPow();

   UFunc.UImpl2 impl_Op_S_DV_eq_DV_Double_OpPow();

   UFunc.UImpl2 impl_Op_S_DV_eq_DV_Float_OpPow();

   UFunc.UImpl2 impl_Op_S_DV_eq_DV_Long_OpPow();

   UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Int_OpAdd();

   UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Double_OpAdd();

   UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Float_OpAdd();

   UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Long_OpAdd();

   UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Int_OpSub();

   UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Double_OpSub();

   UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Float_OpSub();

   UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Long_OpSub();

   UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Int_OpMulScalar();

   UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Double_OpMulScalar();

   UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Float_OpMulScalar();

   UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Long_OpMulScalar();

   UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Int_OpDiv();

   UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Double_OpDiv();

   UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Float_OpDiv();

   UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Long_OpDiv();

   UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Int_OpSet();

   UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Double_OpSet();

   UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Float_OpSet();

   UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Long_OpSet();

   UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Int_OpMod();

   UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Double_OpMod();

   UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Float_OpMod();

   UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Long_OpMod();

   UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Int_OpPow();

   UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Double_OpPow();

   UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Float_OpPow();

   UFunc.UImpl2 impl_Op_DV_DV_eq_DV_Long_OpPow();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Int_OpAdd();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Double_OpAdd();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Float_OpAdd();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Long_OpAdd();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Int_OpSub();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Double_OpSub();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Float_OpSub();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Long_OpSub();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Int_OpMulScalar();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Double_OpMulScalar();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Float_OpMulScalar();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Long_OpMulScalar();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Int_OpDiv();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Double_OpDiv();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Float_OpDiv();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Long_OpDiv();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Int_OpSet();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Double_OpSet();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Float_OpSet();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Long_OpSet();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Int_OpMod();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Double_OpMod();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Float_OpMod();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Long_OpMod();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Int_OpPow();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Double_OpPow();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Float_OpPow();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_DV_Long_OpPow();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Int_OpAdd();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Double_OpAdd();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Float_OpAdd();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Long_OpAdd();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Int_OpSub();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Double_OpSub();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Float_OpSub();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Long_OpSub();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Int_OpMulScalar();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Double_OpMulScalar();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Float_OpMulScalar();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Long_OpMulScalar();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Int_OpMulMatrix();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Double_OpMulMatrix();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Float_OpMulMatrix();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Long_OpMulMatrix();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Int_OpDiv();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Double_OpDiv();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Float_OpDiv();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Long_OpDiv();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Int_OpSet();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Double_OpSet();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Float_OpSet();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Long_OpSet();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Int_OpMod();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Double_OpMod();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Float_OpMod();

   UFunc.InPlaceImpl2 impl_Op_InPlace_DV_S_Long_OpMod();

   UFunc.UImpl2 impl_OpMulInner_DV_DV_eq_S_Int();

   UFunc.UImpl2 impl_OpMulInner_DV_DV_eq_S_Long();

   UFunc.UImpl2 impl_zipValues_DV_DV_Int();

   UFunc.UImpl2 impl_zipValues_DV_DV_Double();

   UFunc.UImpl2 impl_zipValues_DV_DV_Float();

   UFunc.UImpl2 impl_zipValues_DV_DV_Long();

   UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_DV_S_DV_Int();

   UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_DV_S_DV_Long();

   static void $init$(final DenseVectorExpandOps $this) {
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Int_OpAdd_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final int b) {
            int[] ad = a.data$mcI$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(a.length(), .MODULE$.Int(), Zero$.MODULE$.IntZero());
            int[] rd = result.data$mcI$sp();
            int stride = a.stride();
            if (stride == 1) {
               if (aoff == 0) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = rd.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
                     rd[index$macro$2] = ad[index$macro$2] + b;
                  }
               } else {
                  int index$macro$7 = 0;

                  for(int limit$macro$9 = rd.length; index$macro$7 < limit$macro$9; ++index$macro$7) {
                     rd[index$macro$7] = ad[index$macro$7 + aoff] + b;
                  }
               }
            } else {
               int j = aoff;
               int index$macro$12 = 0;

               for(int limit$macro$14 = rd.length; index$macro$12 < limit$macro$14; ++index$macro$12) {
                  rd[index$macro$12] = ad[j] + b;
                  j += stride;
               }
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_S_eq_V_Int_OpAdd())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Int());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Double_OpAdd_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final double b) {
            double[] ad = a.data$mcD$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(a.length(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            double[] rd = result.data$mcD$sp();
            int stride = a.stride();
            if (stride == 1) {
               if (aoff == 0) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = rd.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
                     rd[index$macro$2] = ad[index$macro$2] + b;
                  }
               } else {
                  int index$macro$7 = 0;

                  for(int limit$macro$9 = rd.length; index$macro$7 < limit$macro$9; ++index$macro$7) {
                     rd[index$macro$7] = ad[index$macro$7 + aoff] + b;
                  }
               }
            } else {
               int j = aoff;
               int index$macro$12 = 0;

               for(int limit$macro$14 = rd.length; index$macro$12 < limit$macro$14; ++index$macro$12) {
                  rd[index$macro$12] = ad[j] + b;
                  j += stride;
               }
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_S_eq_V_Double_OpAdd())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Double());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Float_OpAdd_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final float b) {
            float[] ad = a.data$mcF$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mFc$sp(a.length(), .MODULE$.Float(), Zero$.MODULE$.FloatZero());
            float[] rd = result.data$mcF$sp();
            int stride = a.stride();
            if (stride == 1) {
               if (aoff == 0) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = rd.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
                     rd[index$macro$2] = ad[index$macro$2] + b;
                  }
               } else {
                  int index$macro$7 = 0;

                  for(int limit$macro$9 = rd.length; index$macro$7 < limit$macro$9; ++index$macro$7) {
                     rd[index$macro$7] = ad[index$macro$7 + aoff] + b;
                  }
               }
            } else {
               int j = aoff;
               int index$macro$12 = 0;

               for(int limit$macro$14 = rd.length; index$macro$12 < limit$macro$14; ++index$macro$12) {
                  rd[index$macro$12] = ad[j] + b;
                  j += stride;
               }
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_S_eq_V_Float_OpAdd())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Float());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Long_OpAdd_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final long b) {
            long[] ad = a.data$mcJ$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mJc$sp(a.length(), .MODULE$.Long(), Zero$.MODULE$.LongZero());
            long[] rd = result.data$mcJ$sp();
            int stride = a.stride();
            if (stride == 1) {
               if (aoff == 0) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = rd.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
                     rd[index$macro$2] = ad[index$macro$2] + b;
                  }
               } else {
                  int index$macro$7 = 0;

                  for(int limit$macro$9 = rd.length; index$macro$7 < limit$macro$9; ++index$macro$7) {
                     rd[index$macro$7] = ad[index$macro$7 + aoff] + b;
                  }
               }
            } else {
               int j = aoff;
               int index$macro$12 = 0;

               for(int limit$macro$14 = rd.length; index$macro$12 < limit$macro$14; ++index$macro$12) {
                  rd[index$macro$12] = ad[j] + b;
                  j += stride;
               }
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_S_eq_V_Long_OpAdd())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Long());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Int_OpSub_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final int b) {
            int[] ad = a.data$mcI$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(a.length(), .MODULE$.Int(), Zero$.MODULE$.IntZero());
            int[] rd = result.data$mcI$sp();
            int stride = a.stride();
            if (stride == 1) {
               if (aoff == 0) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = rd.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
                     rd[index$macro$2] = ad[index$macro$2] - b;
                  }
               } else {
                  int index$macro$7 = 0;

                  for(int limit$macro$9 = rd.length; index$macro$7 < limit$macro$9; ++index$macro$7) {
                     rd[index$macro$7] = ad[index$macro$7 + aoff] - b;
                  }
               }
            } else {
               int j = aoff;
               int index$macro$12 = 0;

               for(int limit$macro$14 = rd.length; index$macro$12 < limit$macro$14; ++index$macro$12) {
                  rd[index$macro$12] = ad[j] - b;
                  j += stride;
               }
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_S_eq_V_Int_OpSub())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Int());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Double_OpSub_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final double b) {
            double[] ad = a.data$mcD$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(a.length(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            double[] rd = result.data$mcD$sp();
            int stride = a.stride();
            if (stride == 1) {
               if (aoff == 0) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = rd.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
                     rd[index$macro$2] = ad[index$macro$2] - b;
                  }
               } else {
                  int index$macro$7 = 0;

                  for(int limit$macro$9 = rd.length; index$macro$7 < limit$macro$9; ++index$macro$7) {
                     rd[index$macro$7] = ad[index$macro$7 + aoff] - b;
                  }
               }
            } else {
               int j = aoff;
               int index$macro$12 = 0;

               for(int limit$macro$14 = rd.length; index$macro$12 < limit$macro$14; ++index$macro$12) {
                  rd[index$macro$12] = ad[j] - b;
                  j += stride;
               }
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_S_eq_V_Double_OpSub())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Double());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Float_OpSub_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final float b) {
            float[] ad = a.data$mcF$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mFc$sp(a.length(), .MODULE$.Float(), Zero$.MODULE$.FloatZero());
            float[] rd = result.data$mcF$sp();
            int stride = a.stride();
            if (stride == 1) {
               if (aoff == 0) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = rd.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
                     rd[index$macro$2] = ad[index$macro$2] - b;
                  }
               } else {
                  int index$macro$7 = 0;

                  for(int limit$macro$9 = rd.length; index$macro$7 < limit$macro$9; ++index$macro$7) {
                     rd[index$macro$7] = ad[index$macro$7 + aoff] - b;
                  }
               }
            } else {
               int j = aoff;
               int index$macro$12 = 0;

               for(int limit$macro$14 = rd.length; index$macro$12 < limit$macro$14; ++index$macro$12) {
                  rd[index$macro$12] = ad[j] - b;
                  j += stride;
               }
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_S_eq_V_Float_OpSub())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Float());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Long_OpSub_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final long b) {
            long[] ad = a.data$mcJ$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mJc$sp(a.length(), .MODULE$.Long(), Zero$.MODULE$.LongZero());
            long[] rd = result.data$mcJ$sp();
            int stride = a.stride();
            if (stride == 1) {
               if (aoff == 0) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = rd.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
                     rd[index$macro$2] = ad[index$macro$2] - b;
                  }
               } else {
                  int index$macro$7 = 0;

                  for(int limit$macro$9 = rd.length; index$macro$7 < limit$macro$9; ++index$macro$7) {
                     rd[index$macro$7] = ad[index$macro$7 + aoff] - b;
                  }
               }
            } else {
               int j = aoff;
               int index$macro$12 = 0;

               for(int limit$macro$14 = rd.length; index$macro$12 < limit$macro$14; ++index$macro$12) {
                  rd[index$macro$12] = ad[j] - b;
                  j += stride;
               }
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_S_eq_V_Long_OpSub())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Long());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Int_OpMulScalar_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final int b) {
            int[] ad = a.data$mcI$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(a.length(), .MODULE$.Int(), Zero$.MODULE$.IntZero());
            int[] rd = result.data$mcI$sp();
            int stride = a.stride();
            if (stride == 1) {
               if (aoff == 0) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = rd.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
                     rd[index$macro$2] = ad[index$macro$2] * b;
                  }
               } else {
                  int index$macro$7 = 0;

                  for(int limit$macro$9 = rd.length; index$macro$7 < limit$macro$9; ++index$macro$7) {
                     rd[index$macro$7] = ad[index$macro$7 + aoff] * b;
                  }
               }
            } else {
               int j = aoff;
               int index$macro$12 = 0;

               for(int limit$macro$14 = rd.length; index$macro$12 < limit$macro$14; ++index$macro$12) {
                  rd[index$macro$12] = ad[j] * b;
                  j += stride;
               }
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_S_eq_V_Int_OpMulScalar())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Int());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Double_OpMulScalar_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final double b) {
            double[] ad = a.data$mcD$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(a.length(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            double[] rd = result.data$mcD$sp();
            int stride = a.stride();
            if (stride == 1) {
               if (aoff == 0) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = rd.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
                     rd[index$macro$2] = ad[index$macro$2] * b;
                  }
               } else {
                  int index$macro$7 = 0;

                  for(int limit$macro$9 = rd.length; index$macro$7 < limit$macro$9; ++index$macro$7) {
                     rd[index$macro$7] = ad[index$macro$7 + aoff] * b;
                  }
               }
            } else {
               int j = aoff;
               int index$macro$12 = 0;

               for(int limit$macro$14 = rd.length; index$macro$12 < limit$macro$14; ++index$macro$12) {
                  rd[index$macro$12] = ad[j] * b;
                  j += stride;
               }
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_S_eq_V_Double_OpMulScalar())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Double());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Float_OpMulScalar_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final float b) {
            float[] ad = a.data$mcF$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mFc$sp(a.length(), .MODULE$.Float(), Zero$.MODULE$.FloatZero());
            float[] rd = result.data$mcF$sp();
            int stride = a.stride();
            if (stride == 1) {
               if (aoff == 0) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = rd.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
                     rd[index$macro$2] = ad[index$macro$2] * b;
                  }
               } else {
                  int index$macro$7 = 0;

                  for(int limit$macro$9 = rd.length; index$macro$7 < limit$macro$9; ++index$macro$7) {
                     rd[index$macro$7] = ad[index$macro$7 + aoff] * b;
                  }
               }
            } else {
               int j = aoff;
               int index$macro$12 = 0;

               for(int limit$macro$14 = rd.length; index$macro$12 < limit$macro$14; ++index$macro$12) {
                  rd[index$macro$12] = ad[j] * b;
                  j += stride;
               }
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_S_eq_V_Float_OpMulScalar())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Float());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Long_OpMulScalar_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final long b) {
            long[] ad = a.data$mcJ$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mJc$sp(a.length(), .MODULE$.Long(), Zero$.MODULE$.LongZero());
            long[] rd = result.data$mcJ$sp();
            int stride = a.stride();
            if (stride == 1) {
               if (aoff == 0) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = rd.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
                     rd[index$macro$2] = ad[index$macro$2] * b;
                  }
               } else {
                  int index$macro$7 = 0;

                  for(int limit$macro$9 = rd.length; index$macro$7 < limit$macro$9; ++index$macro$7) {
                     rd[index$macro$7] = ad[index$macro$7 + aoff] * b;
                  }
               }
            } else {
               int j = aoff;
               int index$macro$12 = 0;

               for(int limit$macro$14 = rd.length; index$macro$12 < limit$macro$14; ++index$macro$12) {
                  rd[index$macro$12] = ad[j] * b;
                  j += stride;
               }
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_S_eq_V_Long_OpMulScalar())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Long());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Int_OpMulMatrix_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final int b) {
            int[] ad = a.data$mcI$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(a.length(), .MODULE$.Int(), Zero$.MODULE$.IntZero());
            int[] rd = result.data$mcI$sp();
            int stride = a.stride();
            if (stride == 1) {
               if (aoff == 0) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = rd.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
                     rd[index$macro$2] = ad[index$macro$2] * b;
                  }
               } else {
                  int index$macro$7 = 0;

                  for(int limit$macro$9 = rd.length; index$macro$7 < limit$macro$9; ++index$macro$7) {
                     rd[index$macro$7] = ad[index$macro$7 + aoff] * b;
                  }
               }
            } else {
               int j = aoff;
               int index$macro$12 = 0;

               for(int limit$macro$14 = rd.length; index$macro$12 < limit$macro$14; ++index$macro$12) {
                  rd[index$macro$12] = ad[j] * b;
                  j += stride;
               }
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_S_eq_V_Int_OpMulMatrix())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Int());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Double_OpMulMatrix_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final double b) {
            double[] ad = a.data$mcD$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(a.length(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            double[] rd = result.data$mcD$sp();
            int stride = a.stride();
            if (stride == 1) {
               if (aoff == 0) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = rd.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
                     rd[index$macro$2] = ad[index$macro$2] * b;
                  }
               } else {
                  int index$macro$7 = 0;

                  for(int limit$macro$9 = rd.length; index$macro$7 < limit$macro$9; ++index$macro$7) {
                     rd[index$macro$7] = ad[index$macro$7 + aoff] * b;
                  }
               }
            } else {
               int j = aoff;
               int index$macro$12 = 0;

               for(int limit$macro$14 = rd.length; index$macro$12 < limit$macro$14; ++index$macro$12) {
                  rd[index$macro$12] = ad[j] * b;
                  j += stride;
               }
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_S_eq_V_Double_OpMulMatrix())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Double());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Float_OpMulMatrix_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final float b) {
            float[] ad = a.data$mcF$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mFc$sp(a.length(), .MODULE$.Float(), Zero$.MODULE$.FloatZero());
            float[] rd = result.data$mcF$sp();
            int stride = a.stride();
            if (stride == 1) {
               if (aoff == 0) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = rd.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
                     rd[index$macro$2] = ad[index$macro$2] * b;
                  }
               } else {
                  int index$macro$7 = 0;

                  for(int limit$macro$9 = rd.length; index$macro$7 < limit$macro$9; ++index$macro$7) {
                     rd[index$macro$7] = ad[index$macro$7 + aoff] * b;
                  }
               }
            } else {
               int j = aoff;
               int index$macro$12 = 0;

               for(int limit$macro$14 = rd.length; index$macro$12 < limit$macro$14; ++index$macro$12) {
                  rd[index$macro$12] = ad[j] * b;
                  j += stride;
               }
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_S_eq_V_Float_OpMulMatrix())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Float());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Long_OpMulMatrix_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final long b) {
            long[] ad = a.data$mcJ$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mJc$sp(a.length(), .MODULE$.Long(), Zero$.MODULE$.LongZero());
            long[] rd = result.data$mcJ$sp();
            int stride = a.stride();
            if (stride == 1) {
               if (aoff == 0) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = rd.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
                     rd[index$macro$2] = ad[index$macro$2] * b;
                  }
               } else {
                  int index$macro$7 = 0;

                  for(int limit$macro$9 = rd.length; index$macro$7 < limit$macro$9; ++index$macro$7) {
                     rd[index$macro$7] = ad[index$macro$7 + aoff] * b;
                  }
               }
            } else {
               int j = aoff;
               int index$macro$12 = 0;

               for(int limit$macro$14 = rd.length; index$macro$12 < limit$macro$14; ++index$macro$12) {
                  rd[index$macro$12] = ad[j] * b;
                  j += stride;
               }
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_S_eq_V_Long_OpMulMatrix())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Long());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Int_OpDiv_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final int b) {
            int[] ad = a.data$mcI$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(a.length(), .MODULE$.Int(), Zero$.MODULE$.IntZero());
            int[] rd = result.data$mcI$sp();
            int stride = a.stride();
            if (stride == 1) {
               if (aoff == 0) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = rd.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
                     rd[index$macro$2] = ad[index$macro$2] / b;
                  }
               } else {
                  int index$macro$7 = 0;

                  for(int limit$macro$9 = rd.length; index$macro$7 < limit$macro$9; ++index$macro$7) {
                     rd[index$macro$7] = ad[index$macro$7 + aoff] / b;
                  }
               }
            } else {
               int j = aoff;
               int index$macro$12 = 0;

               for(int limit$macro$14 = rd.length; index$macro$12 < limit$macro$14; ++index$macro$12) {
                  rd[index$macro$12] = ad[j] / b;
                  j += stride;
               }
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_S_eq_V_Int_OpDiv())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Int());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Double_OpDiv_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final double b) {
            double[] ad = a.data$mcD$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(a.length(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            double[] rd = result.data$mcD$sp();
            int stride = a.stride();
            if (stride == 1) {
               if (aoff == 0) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = rd.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
                     rd[index$macro$2] = ad[index$macro$2] / b;
                  }
               } else {
                  int index$macro$7 = 0;

                  for(int limit$macro$9 = rd.length; index$macro$7 < limit$macro$9; ++index$macro$7) {
                     rd[index$macro$7] = ad[index$macro$7 + aoff] / b;
                  }
               }
            } else {
               int j = aoff;
               int index$macro$12 = 0;

               for(int limit$macro$14 = rd.length; index$macro$12 < limit$macro$14; ++index$macro$12) {
                  rd[index$macro$12] = ad[j] / b;
                  j += stride;
               }
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_S_eq_V_Double_OpDiv())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Double());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Float_OpDiv_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final float b) {
            float[] ad = a.data$mcF$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mFc$sp(a.length(), .MODULE$.Float(), Zero$.MODULE$.FloatZero());
            float[] rd = result.data$mcF$sp();
            int stride = a.stride();
            if (stride == 1) {
               if (aoff == 0) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = rd.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
                     rd[index$macro$2] = ad[index$macro$2] / b;
                  }
               } else {
                  int index$macro$7 = 0;

                  for(int limit$macro$9 = rd.length; index$macro$7 < limit$macro$9; ++index$macro$7) {
                     rd[index$macro$7] = ad[index$macro$7 + aoff] / b;
                  }
               }
            } else {
               int j = aoff;
               int index$macro$12 = 0;

               for(int limit$macro$14 = rd.length; index$macro$12 < limit$macro$14; ++index$macro$12) {
                  rd[index$macro$12] = ad[j] / b;
                  j += stride;
               }
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_S_eq_V_Float_OpDiv())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Float());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Long_OpDiv_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final long b) {
            long[] ad = a.data$mcJ$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mJc$sp(a.length(), .MODULE$.Long(), Zero$.MODULE$.LongZero());
            long[] rd = result.data$mcJ$sp();
            int stride = a.stride();
            if (stride == 1) {
               if (aoff == 0) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = rd.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
                     rd[index$macro$2] = ad[index$macro$2] / b;
                  }
               } else {
                  int index$macro$7 = 0;

                  for(int limit$macro$9 = rd.length; index$macro$7 < limit$macro$9; ++index$macro$7) {
                     rd[index$macro$7] = ad[index$macro$7 + aoff] / b;
                  }
               }
            } else {
               int j = aoff;
               int index$macro$12 = 0;

               for(int limit$macro$14 = rd.length; index$macro$12 < limit$macro$14; ++index$macro$12) {
                  rd[index$macro$12] = ad[j] / b;
                  j += stride;
               }
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_S_eq_V_Long_OpDiv())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Long());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Int_OpSet_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final int b) {
            int[] ad = a.data$mcI$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(a.length(), .MODULE$.Int(), Zero$.MODULE$.IntZero());
            int[] rd = result.data$mcI$sp();
            int stride = a.stride();
            if (stride == 1) {
               if (aoff == 0) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = rd.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
                     rd[index$macro$2] = b;
                  }
               } else {
                  int index$macro$7 = 0;

                  for(int limit$macro$9 = rd.length; index$macro$7 < limit$macro$9; ++index$macro$7) {
                     rd[index$macro$7] = b;
                  }
               }
            } else {
               int j = aoff;
               int index$macro$12 = 0;

               for(int limit$macro$14 = rd.length; index$macro$12 < limit$macro$14; ++index$macro$12) {
                  rd[index$macro$12] = b;
                  j += stride;
               }
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_S_eq_V_Int_OpSet())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Int());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Double_OpSet_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final double b) {
            double[] ad = a.data$mcD$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(a.length(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            double[] rd = result.data$mcD$sp();
            int stride = a.stride();
            if (stride == 1) {
               if (aoff == 0) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = rd.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
                     rd[index$macro$2] = b;
                  }
               } else {
                  int index$macro$7 = 0;

                  for(int limit$macro$9 = rd.length; index$macro$7 < limit$macro$9; ++index$macro$7) {
                     rd[index$macro$7] = b;
                  }
               }
            } else {
               int j = aoff;
               int index$macro$12 = 0;

               for(int limit$macro$14 = rd.length; index$macro$12 < limit$macro$14; ++index$macro$12) {
                  rd[index$macro$12] = b;
                  j += stride;
               }
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_S_eq_V_Double_OpSet())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Double());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Float_OpSet_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final float b) {
            float[] ad = a.data$mcF$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mFc$sp(a.length(), .MODULE$.Float(), Zero$.MODULE$.FloatZero());
            float[] rd = result.data$mcF$sp();
            int stride = a.stride();
            if (stride == 1) {
               if (aoff == 0) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = rd.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
                     rd[index$macro$2] = b;
                  }
               } else {
                  int index$macro$7 = 0;

                  for(int limit$macro$9 = rd.length; index$macro$7 < limit$macro$9; ++index$macro$7) {
                     rd[index$macro$7] = b;
                  }
               }
            } else {
               int j = aoff;
               int index$macro$12 = 0;

               for(int limit$macro$14 = rd.length; index$macro$12 < limit$macro$14; ++index$macro$12) {
                  rd[index$macro$12] = b;
                  j += stride;
               }
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_S_eq_V_Float_OpSet())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Float());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Long_OpSet_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final long b) {
            long[] ad = a.data$mcJ$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mJc$sp(a.length(), .MODULE$.Long(), Zero$.MODULE$.LongZero());
            long[] rd = result.data$mcJ$sp();
            int stride = a.stride();
            if (stride == 1) {
               if (aoff == 0) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = rd.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
                     rd[index$macro$2] = b;
                  }
               } else {
                  int index$macro$7 = 0;

                  for(int limit$macro$9 = rd.length; index$macro$7 < limit$macro$9; ++index$macro$7) {
                     rd[index$macro$7] = b;
                  }
               }
            } else {
               int j = aoff;
               int index$macro$12 = 0;

               for(int limit$macro$14 = rd.length; index$macro$12 < limit$macro$14; ++index$macro$12) {
                  rd[index$macro$12] = b;
                  j += stride;
               }
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_S_eq_V_Long_OpSet())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Long());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Int_OpMod_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final int b) {
            int[] ad = a.data$mcI$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(a.length(), .MODULE$.Int(), Zero$.MODULE$.IntZero());
            int[] rd = result.data$mcI$sp();
            int stride = a.stride();
            if (stride == 1) {
               if (aoff == 0) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = rd.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
                     rd[index$macro$2] = ad[index$macro$2] % b;
                  }
               } else {
                  int index$macro$7 = 0;

                  for(int limit$macro$9 = rd.length; index$macro$7 < limit$macro$9; ++index$macro$7) {
                     rd[index$macro$7] = ad[index$macro$7 + aoff] % b;
                  }
               }
            } else {
               int j = aoff;
               int index$macro$12 = 0;

               for(int limit$macro$14 = rd.length; index$macro$12 < limit$macro$14; ++index$macro$12) {
                  rd[index$macro$12] = ad[j] % b;
                  j += stride;
               }
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_S_eq_V_Int_OpMod())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Int());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Double_OpMod_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final double b) {
            double[] ad = a.data$mcD$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(a.length(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            double[] rd = result.data$mcD$sp();
            int stride = a.stride();
            if (stride == 1) {
               if (aoff == 0) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = rd.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
                     rd[index$macro$2] = ad[index$macro$2] % b;
                  }
               } else {
                  int index$macro$7 = 0;

                  for(int limit$macro$9 = rd.length; index$macro$7 < limit$macro$9; ++index$macro$7) {
                     rd[index$macro$7] = ad[index$macro$7 + aoff] % b;
                  }
               }
            } else {
               int j = aoff;
               int index$macro$12 = 0;

               for(int limit$macro$14 = rd.length; index$macro$12 < limit$macro$14; ++index$macro$12) {
                  rd[index$macro$12] = ad[j] % b;
                  j += stride;
               }
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_S_eq_V_Double_OpMod())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Double());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Float_OpMod_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final float b) {
            float[] ad = a.data$mcF$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mFc$sp(a.length(), .MODULE$.Float(), Zero$.MODULE$.FloatZero());
            float[] rd = result.data$mcF$sp();
            int stride = a.stride();
            if (stride == 1) {
               if (aoff == 0) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = rd.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
                     rd[index$macro$2] = ad[index$macro$2] % b;
                  }
               } else {
                  int index$macro$7 = 0;

                  for(int limit$macro$9 = rd.length; index$macro$7 < limit$macro$9; ++index$macro$7) {
                     rd[index$macro$7] = ad[index$macro$7 + aoff] % b;
                  }
               }
            } else {
               int j = aoff;
               int index$macro$12 = 0;

               for(int limit$macro$14 = rd.length; index$macro$12 < limit$macro$14; ++index$macro$12) {
                  rd[index$macro$12] = ad[j] % b;
                  j += stride;
               }
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_S_eq_V_Float_OpMod())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Float());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Long_OpMod_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final long b) {
            long[] ad = a.data$mcJ$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mJc$sp(a.length(), .MODULE$.Long(), Zero$.MODULE$.LongZero());
            long[] rd = result.data$mcJ$sp();
            int stride = a.stride();
            if (stride == 1) {
               if (aoff == 0) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = rd.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
                     rd[index$macro$2] = ad[index$macro$2] % b;
                  }
               } else {
                  int index$macro$7 = 0;

                  for(int limit$macro$9 = rd.length; index$macro$7 < limit$macro$9; ++index$macro$7) {
                     rd[index$macro$7] = ad[index$macro$7 + aoff] % b;
                  }
               }
            } else {
               int j = aoff;
               int index$macro$12 = 0;

               for(int limit$macro$14 = rd.length; index$macro$12 < limit$macro$14; ++index$macro$12) {
                  rd[index$macro$12] = ad[j] % b;
                  j += stride;
               }
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_S_eq_V_Long_OpMod())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Long());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Int_OpPow_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final int b) {
            int[] ad = a.data$mcI$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(a.length(), .MODULE$.Int(), Zero$.MODULE$.IntZero());
            int[] rd = result.data$mcI$sp();
            int stride = a.stride();
            if (stride == 1) {
               if (aoff == 0) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = rd.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
                     rd[index$macro$2] = PowImplicits$.MODULE$.IntPow(ad[index$macro$2]).pow(b);
                  }
               } else {
                  int index$macro$7 = 0;

                  for(int limit$macro$9 = rd.length; index$macro$7 < limit$macro$9; ++index$macro$7) {
                     rd[index$macro$7] = PowImplicits$.MODULE$.IntPow(ad[index$macro$7 + aoff]).pow(b);
                  }
               }
            } else {
               int j = aoff;
               int index$macro$12 = 0;

               for(int limit$macro$14 = rd.length; index$macro$12 < limit$macro$14; ++index$macro$12) {
                  rd[index$macro$12] = PowImplicits$.MODULE$.IntPow(ad[j]).pow(b);
                  j += stride;
               }
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_S_eq_V_Int_OpPow())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Int());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Double_OpPow_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final double b) {
            double[] ad = a.data$mcD$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(a.length(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            double[] rd = result.data$mcD$sp();
            int stride = a.stride();
            if (stride == 1) {
               if (aoff == 0) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = rd.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
                     rd[index$macro$2] = PowImplicits$.MODULE$.DoublePow(ad[index$macro$2]).pow(b);
                  }
               } else {
                  int index$macro$7 = 0;

                  for(int limit$macro$9 = rd.length; index$macro$7 < limit$macro$9; ++index$macro$7) {
                     rd[index$macro$7] = PowImplicits$.MODULE$.DoublePow(ad[index$macro$7 + aoff]).pow(b);
                  }
               }
            } else {
               int j = aoff;
               int index$macro$12 = 0;

               for(int limit$macro$14 = rd.length; index$macro$12 < limit$macro$14; ++index$macro$12) {
                  rd[index$macro$12] = PowImplicits$.MODULE$.DoublePow(ad[j]).pow(b);
                  j += stride;
               }
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_S_eq_V_Double_OpPow())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Double());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Float_OpPow_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final float b) {
            float[] ad = a.data$mcF$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mFc$sp(a.length(), .MODULE$.Float(), Zero$.MODULE$.FloatZero());
            float[] rd = result.data$mcF$sp();
            int stride = a.stride();
            if (stride == 1) {
               if (aoff == 0) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = rd.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
                     rd[index$macro$2] = PowImplicits$.MODULE$.FloatPow(ad[index$macro$2]).pow(b);
                  }
               } else {
                  int index$macro$7 = 0;

                  for(int limit$macro$9 = rd.length; index$macro$7 < limit$macro$9; ++index$macro$7) {
                     rd[index$macro$7] = PowImplicits$.MODULE$.FloatPow(ad[index$macro$7 + aoff]).pow(b);
                  }
               }
            } else {
               int j = aoff;
               int index$macro$12 = 0;

               for(int limit$macro$14 = rd.length; index$macro$12 < limit$macro$14; ++index$macro$12) {
                  rd[index$macro$12] = PowImplicits$.MODULE$.FloatPow(ad[j]).pow(b);
                  j += stride;
               }
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_S_eq_V_Float_OpPow())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Float());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_S_eq_DV_Long_OpPow_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final long b) {
            long[] ad = a.data$mcJ$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mJc$sp(a.length(), .MODULE$.Long(), Zero$.MODULE$.LongZero());
            long[] rd = result.data$mcJ$sp();
            int stride = a.stride();
            if (stride == 1) {
               if (aoff == 0) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = rd.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
                     rd[index$macro$2] = PowImplicits$.MODULE$.LongPow(ad[index$macro$2]).pow(b);
                  }
               } else {
                  int index$macro$7 = 0;

                  for(int limit$macro$9 = rd.length; index$macro$7 < limit$macro$9; ++index$macro$7) {
                     rd[index$macro$7] = PowImplicits$.MODULE$.LongPow(ad[index$macro$7 + aoff]).pow(b);
                  }
               }
            } else {
               int j = aoff;
               int index$macro$12 = 0;

               for(int limit$macro$14 = rd.length; index$macro$12 < limit$macro$14; ++index$macro$12) {
                  rd[index$macro$12] = PowImplicits$.MODULE$.LongPow(ad[j]).pow(b);
                  j += stride;
               }
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_S_eq_V_Long_OpPow())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Long());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Int_OpAdd_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final int a, final DenseVector b) {
            int[] bd = b.data$mcI$sp();
            int boff = b.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(b.length(), .MODULE$.Int(), Zero$.MODULE$.IntZero());
            int[] rd = result.data$mcI$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = b.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = a + bd[boff];
               boff += b.stride();
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_S_V_eq_V_Int_OpAdd())).register(this, .MODULE$.Int(), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Double_OpAdd_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final double a, final DenseVector b) {
            double[] bd = b.data$mcD$sp();
            int boff = b.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(b.length(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            double[] rd = result.data$mcD$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = b.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = a + bd[boff];
               boff += b.stride();
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_S_V_eq_V_Double_OpAdd())).register(this, .MODULE$.Double(), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Float_OpAdd_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final float a, final DenseVector b) {
            float[] bd = b.data$mcF$sp();
            int boff = b.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mFc$sp(b.length(), .MODULE$.Float(), Zero$.MODULE$.FloatZero());
            float[] rd = result.data$mcF$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = b.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = a + bd[boff];
               boff += b.stride();
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_S_V_eq_V_Float_OpAdd())).register(this, .MODULE$.Float(), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Long_OpAdd_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final long a, final DenseVector b) {
            long[] bd = b.data$mcJ$sp();
            int boff = b.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mJc$sp(b.length(), .MODULE$.Long(), Zero$.MODULE$.LongZero());
            long[] rd = result.data$mcJ$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = b.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = a + bd[boff];
               boff += b.stride();
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_S_V_eq_V_Long_OpAdd())).register(this, .MODULE$.Long(), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Int_OpSub_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final int a, final DenseVector b) {
            int[] bd = b.data$mcI$sp();
            int boff = b.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(b.length(), .MODULE$.Int(), Zero$.MODULE$.IntZero());
            int[] rd = result.data$mcI$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = b.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = a - bd[boff];
               boff += b.stride();
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_S_V_eq_V_Int_OpSub())).register(this, .MODULE$.Int(), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Double_OpSub_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final double a, final DenseVector b) {
            double[] bd = b.data$mcD$sp();
            int boff = b.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(b.length(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            double[] rd = result.data$mcD$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = b.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = a - bd[boff];
               boff += b.stride();
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_S_V_eq_V_Double_OpSub())).register(this, .MODULE$.Double(), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Float_OpSub_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final float a, final DenseVector b) {
            float[] bd = b.data$mcF$sp();
            int boff = b.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mFc$sp(b.length(), .MODULE$.Float(), Zero$.MODULE$.FloatZero());
            float[] rd = result.data$mcF$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = b.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = a - bd[boff];
               boff += b.stride();
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_S_V_eq_V_Float_OpSub())).register(this, .MODULE$.Float(), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Long_OpSub_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final long a, final DenseVector b) {
            long[] bd = b.data$mcJ$sp();
            int boff = b.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mJc$sp(b.length(), .MODULE$.Long(), Zero$.MODULE$.LongZero());
            long[] rd = result.data$mcJ$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = b.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = a - bd[boff];
               boff += b.stride();
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_S_V_eq_V_Long_OpSub())).register(this, .MODULE$.Long(), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Int_OpMulScalar_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final int a, final DenseVector b) {
            int[] bd = b.data$mcI$sp();
            int boff = b.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(b.length(), .MODULE$.Int(), Zero$.MODULE$.IntZero());
            int[] rd = result.data$mcI$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = b.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = a * bd[boff];
               boff += b.stride();
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_S_V_eq_V_Int_OpMulScalar())).register(this, .MODULE$.Int(), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Double_OpMulScalar_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final double a, final DenseVector b) {
            double[] bd = b.data$mcD$sp();
            int boff = b.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(b.length(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            double[] rd = result.data$mcD$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = b.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = a * bd[boff];
               boff += b.stride();
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_S_V_eq_V_Double_OpMulScalar())).register(this, .MODULE$.Double(), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Float_OpMulScalar_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final float a, final DenseVector b) {
            float[] bd = b.data$mcF$sp();
            int boff = b.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mFc$sp(b.length(), .MODULE$.Float(), Zero$.MODULE$.FloatZero());
            float[] rd = result.data$mcF$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = b.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = a * bd[boff];
               boff += b.stride();
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_S_V_eq_V_Float_OpMulScalar())).register(this, .MODULE$.Float(), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Long_OpMulScalar_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final long a, final DenseVector b) {
            long[] bd = b.data$mcJ$sp();
            int boff = b.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mJc$sp(b.length(), .MODULE$.Long(), Zero$.MODULE$.LongZero());
            long[] rd = result.data$mcJ$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = b.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = a * bd[boff];
               boff += b.stride();
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_S_V_eq_V_Long_OpMulScalar())).register(this, .MODULE$.Long(), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Int_OpMulMatrix_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final int a, final DenseVector b) {
            int[] bd = b.data$mcI$sp();
            int boff = b.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(b.length(), .MODULE$.Int(), Zero$.MODULE$.IntZero());
            int[] rd = result.data$mcI$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = b.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = a * bd[boff];
               boff += b.stride();
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_S_V_eq_V_Int_OpMulMatrix())).register(this, .MODULE$.Int(), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Double_OpMulMatrix_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final double a, final DenseVector b) {
            double[] bd = b.data$mcD$sp();
            int boff = b.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(b.length(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            double[] rd = result.data$mcD$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = b.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = a * bd[boff];
               boff += b.stride();
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_S_V_eq_V_Double_OpMulMatrix())).register(this, .MODULE$.Double(), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Float_OpMulMatrix_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final float a, final DenseVector b) {
            float[] bd = b.data$mcF$sp();
            int boff = b.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mFc$sp(b.length(), .MODULE$.Float(), Zero$.MODULE$.FloatZero());
            float[] rd = result.data$mcF$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = b.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = a * bd[boff];
               boff += b.stride();
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_S_V_eq_V_Float_OpMulMatrix())).register(this, .MODULE$.Float(), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Long_OpMulMatrix_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final long a, final DenseVector b) {
            long[] bd = b.data$mcJ$sp();
            int boff = b.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mJc$sp(b.length(), .MODULE$.Long(), Zero$.MODULE$.LongZero());
            long[] rd = result.data$mcJ$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = b.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = a * bd[boff];
               boff += b.stride();
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_S_V_eq_V_Long_OpMulMatrix())).register(this, .MODULE$.Long(), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Int_OpDiv_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final int a, final DenseVector b) {
            int[] bd = b.data$mcI$sp();
            int boff = b.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(b.length(), .MODULE$.Int(), Zero$.MODULE$.IntZero());
            int[] rd = result.data$mcI$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = b.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = a / bd[boff];
               boff += b.stride();
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_S_V_eq_V_Int_OpDiv())).register(this, .MODULE$.Int(), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Double_OpDiv_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final double a, final DenseVector b) {
            double[] bd = b.data$mcD$sp();
            int boff = b.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(b.length(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            double[] rd = result.data$mcD$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = b.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = a / bd[boff];
               boff += b.stride();
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_S_V_eq_V_Double_OpDiv())).register(this, .MODULE$.Double(), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Float_OpDiv_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final float a, final DenseVector b) {
            float[] bd = b.data$mcF$sp();
            int boff = b.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mFc$sp(b.length(), .MODULE$.Float(), Zero$.MODULE$.FloatZero());
            float[] rd = result.data$mcF$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = b.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = a / bd[boff];
               boff += b.stride();
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_S_V_eq_V_Float_OpDiv())).register(this, .MODULE$.Float(), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Long_OpDiv_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final long a, final DenseVector b) {
            long[] bd = b.data$mcJ$sp();
            int boff = b.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mJc$sp(b.length(), .MODULE$.Long(), Zero$.MODULE$.LongZero());
            long[] rd = result.data$mcJ$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = b.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = a / bd[boff];
               boff += b.stride();
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_S_V_eq_V_Long_OpDiv())).register(this, .MODULE$.Long(), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Int_OpSet_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final int a, final DenseVector b) {
            int[] bd = b.data$mcI$sp();
            int boff = b.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(b.length(), .MODULE$.Int(), Zero$.MODULE$.IntZero());
            int[] rd = result.data$mcI$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = b.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = bd[boff];
               boff += b.stride();
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_S_V_eq_V_Int_OpSet())).register(this, .MODULE$.Int(), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Double_OpSet_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final double a, final DenseVector b) {
            double[] bd = b.data$mcD$sp();
            int boff = b.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(b.length(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            double[] rd = result.data$mcD$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = b.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = bd[boff];
               boff += b.stride();
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_S_V_eq_V_Double_OpSet())).register(this, .MODULE$.Double(), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Float_OpSet_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final float a, final DenseVector b) {
            float[] bd = b.data$mcF$sp();
            int boff = b.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mFc$sp(b.length(), .MODULE$.Float(), Zero$.MODULE$.FloatZero());
            float[] rd = result.data$mcF$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = b.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = bd[boff];
               boff += b.stride();
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_S_V_eq_V_Float_OpSet())).register(this, .MODULE$.Float(), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Long_OpSet_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final long a, final DenseVector b) {
            long[] bd = b.data$mcJ$sp();
            int boff = b.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mJc$sp(b.length(), .MODULE$.Long(), Zero$.MODULE$.LongZero());
            long[] rd = result.data$mcJ$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = b.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = bd[boff];
               boff += b.stride();
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_S_V_eq_V_Long_OpSet())).register(this, .MODULE$.Long(), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Int_OpMod_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final int a, final DenseVector b) {
            int[] bd = b.data$mcI$sp();
            int boff = b.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(b.length(), .MODULE$.Int(), Zero$.MODULE$.IntZero());
            int[] rd = result.data$mcI$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = b.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = a % bd[boff];
               boff += b.stride();
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_S_V_eq_V_Int_OpMod())).register(this, .MODULE$.Int(), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Double_OpMod_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final double a, final DenseVector b) {
            double[] bd = b.data$mcD$sp();
            int boff = b.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(b.length(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            double[] rd = result.data$mcD$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = b.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = a % bd[boff];
               boff += b.stride();
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_S_V_eq_V_Double_OpMod())).register(this, .MODULE$.Double(), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Float_OpMod_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final float a, final DenseVector b) {
            float[] bd = b.data$mcF$sp();
            int boff = b.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mFc$sp(b.length(), .MODULE$.Float(), Zero$.MODULE$.FloatZero());
            float[] rd = result.data$mcF$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = b.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = a % bd[boff];
               boff += b.stride();
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_S_V_eq_V_Float_OpMod())).register(this, .MODULE$.Float(), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Long_OpMod_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final long a, final DenseVector b) {
            long[] bd = b.data$mcJ$sp();
            int boff = b.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mJc$sp(b.length(), .MODULE$.Long(), Zero$.MODULE$.LongZero());
            long[] rd = result.data$mcJ$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = b.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = a % bd[boff];
               boff += b.stride();
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_S_V_eq_V_Long_OpMod())).register(this, .MODULE$.Long(), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Int_OpPow_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final int a, final DenseVector b) {
            int[] bd = b.data$mcI$sp();
            int boff = b.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(b.length(), .MODULE$.Int(), Zero$.MODULE$.IntZero());
            int[] rd = result.data$mcI$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = b.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = PowImplicits$.MODULE$.IntPow(a).pow(bd[boff]);
               boff += b.stride();
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_S_V_eq_V_Int_OpPow())).register(this, .MODULE$.Int(), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Double_OpPow_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final double a, final DenseVector b) {
            double[] bd = b.data$mcD$sp();
            int boff = b.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(b.length(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            double[] rd = result.data$mcD$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = b.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = PowImplicits$.MODULE$.DoublePow(a).pow(bd[boff]);
               boff += b.stride();
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_S_V_eq_V_Double_OpPow())).register(this, .MODULE$.Double(), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Float_OpPow_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final float a, final DenseVector b) {
            float[] bd = b.data$mcF$sp();
            int boff = b.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mFc$sp(b.length(), .MODULE$.Float(), Zero$.MODULE$.FloatZero());
            float[] rd = result.data$mcF$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = b.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = PowImplicits$.MODULE$.FloatPow(a).pow(bd[boff]);
               boff += b.stride();
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_S_V_eq_V_Float_OpPow())).register(this, .MODULE$.Float(), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_S_DV_eq_DV_Long_OpPow_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final long a, final DenseVector b) {
            long[] bd = b.data$mcJ$sp();
            int boff = b.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mJc$sp(b.length(), .MODULE$.Long(), Zero$.MODULE$.LongZero());
            long[] rd = result.data$mcJ$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = b.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = PowImplicits$.MODULE$.LongPow(a).pow(bd[boff]);
               boff += b.stride();
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_S_V_eq_V_Long_OpPow())).register(this, .MODULE$.Long(), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Int_OpAdd_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int[] ad = a.data$mcI$sp();
               int[] bd = b.data$mcI$sp();
               int aoff = a.offset();
               int boff = b.offset();
               DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(a.length(), .MODULE$.Int(), Zero$.MODULE$.IntZero());
               int[] rd = result.data$mcI$sp();
               if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                     rd[index$macro$4] = ad[index$macro$4] + bd[index$macro$4];
                  }
               } else if (a.stride() == 1 && b.stride() == 1) {
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = a.length(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                     rd[index$macro$9] = ad[index$macro$9 + aoff] + bd[index$macro$9 + boff];
                  }
               } else {
                  int index$macro$14 = 0;

                  for(int limit$macro$16 = a.length(); index$macro$14 < limit$macro$16; ++index$macro$14) {
                     rd[index$macro$14] = ad[aoff] + bd[boff];
                     aoff += a.stride();
                     boff += b.stride();
                  }
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_V_eq_V_idempotent_Int_OpAdd())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Double_OpAdd_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               double[] ad = a.data$mcD$sp();
               double[] bd = b.data$mcD$sp();
               int aoff = a.offset();
               int boff = b.offset();
               DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(a.length(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
               double[] rd = result.data$mcD$sp();
               if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                     rd[index$macro$4] = ad[index$macro$4] + bd[index$macro$4];
                  }
               } else if (a.stride() == 1 && b.stride() == 1) {
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = a.length(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                     rd[index$macro$9] = ad[index$macro$9 + aoff] + bd[index$macro$9 + boff];
                  }
               } else {
                  int index$macro$14 = 0;

                  for(int limit$macro$16 = a.length(); index$macro$14 < limit$macro$16; ++index$macro$14) {
                     rd[index$macro$14] = ad[aoff] + bd[boff];
                     aoff += a.stride();
                     boff += b.stride();
                  }
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_V_eq_V_idempotent_Double_OpAdd())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Float_OpAdd_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               float[] ad = a.data$mcF$sp();
               float[] bd = b.data$mcF$sp();
               int aoff = a.offset();
               int boff = b.offset();
               DenseVector result = DenseVector$.MODULE$.zeros$mFc$sp(a.length(), .MODULE$.Float(), Zero$.MODULE$.FloatZero());
               float[] rd = result.data$mcF$sp();
               if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                     rd[index$macro$4] = ad[index$macro$4] + bd[index$macro$4];
                  }
               } else if (a.stride() == 1 && b.stride() == 1) {
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = a.length(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                     rd[index$macro$9] = ad[index$macro$9 + aoff] + bd[index$macro$9 + boff];
                  }
               } else {
                  int index$macro$14 = 0;

                  for(int limit$macro$16 = a.length(); index$macro$14 < limit$macro$16; ++index$macro$14) {
                     rd[index$macro$14] = ad[aoff] + bd[boff];
                     aoff += a.stride();
                     boff += b.stride();
                  }
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_V_eq_V_idempotent_Float_OpAdd())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Long_OpAdd_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               long[] ad = a.data$mcJ$sp();
               long[] bd = b.data$mcJ$sp();
               int aoff = a.offset();
               int boff = b.offset();
               DenseVector result = DenseVector$.MODULE$.zeros$mJc$sp(a.length(), .MODULE$.Long(), Zero$.MODULE$.LongZero());
               long[] rd = result.data$mcJ$sp();
               if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                     rd[index$macro$4] = ad[index$macro$4] + bd[index$macro$4];
                  }
               } else if (a.stride() == 1 && b.stride() == 1) {
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = a.length(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                     rd[index$macro$9] = ad[index$macro$9 + aoff] + bd[index$macro$9 + boff];
                  }
               } else {
                  int index$macro$14 = 0;

                  for(int limit$macro$16 = a.length(); index$macro$14 < limit$macro$16; ++index$macro$14) {
                     rd[index$macro$14] = ad[aoff] + bd[boff];
                     aoff += a.stride();
                     boff += b.stride();
                  }
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_V_eq_V_idempotent_Long_OpAdd())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Int_OpSub_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int[] ad = a.data$mcI$sp();
               int[] bd = b.data$mcI$sp();
               int aoff = a.offset();
               int boff = b.offset();
               DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(a.length(), .MODULE$.Int(), Zero$.MODULE$.IntZero());
               int[] rd = result.data$mcI$sp();
               if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                     rd[index$macro$4] = ad[index$macro$4] - bd[index$macro$4];
                  }
               } else if (a.stride() == 1 && b.stride() == 1) {
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = a.length(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                     rd[index$macro$9] = ad[index$macro$9 + aoff] - bd[index$macro$9 + boff];
                  }
               } else {
                  int index$macro$14 = 0;

                  for(int limit$macro$16 = a.length(); index$macro$14 < limit$macro$16; ++index$macro$14) {
                     rd[index$macro$14] = ad[aoff] - bd[boff];
                     aoff += a.stride();
                     boff += b.stride();
                  }
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_V_eq_V_idempotent_Int_OpSub())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Double_OpSub_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               double[] ad = a.data$mcD$sp();
               double[] bd = b.data$mcD$sp();
               int aoff = a.offset();
               int boff = b.offset();
               DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(a.length(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
               double[] rd = result.data$mcD$sp();
               if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                     rd[index$macro$4] = ad[index$macro$4] - bd[index$macro$4];
                  }
               } else if (a.stride() == 1 && b.stride() == 1) {
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = a.length(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                     rd[index$macro$9] = ad[index$macro$9 + aoff] - bd[index$macro$9 + boff];
                  }
               } else {
                  int index$macro$14 = 0;

                  for(int limit$macro$16 = a.length(); index$macro$14 < limit$macro$16; ++index$macro$14) {
                     rd[index$macro$14] = ad[aoff] - bd[boff];
                     aoff += a.stride();
                     boff += b.stride();
                  }
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_V_eq_V_idempotent_Double_OpSub())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Float_OpSub_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               float[] ad = a.data$mcF$sp();
               float[] bd = b.data$mcF$sp();
               int aoff = a.offset();
               int boff = b.offset();
               DenseVector result = DenseVector$.MODULE$.zeros$mFc$sp(a.length(), .MODULE$.Float(), Zero$.MODULE$.FloatZero());
               float[] rd = result.data$mcF$sp();
               if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                     rd[index$macro$4] = ad[index$macro$4] - bd[index$macro$4];
                  }
               } else if (a.stride() == 1 && b.stride() == 1) {
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = a.length(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                     rd[index$macro$9] = ad[index$macro$9 + aoff] - bd[index$macro$9 + boff];
                  }
               } else {
                  int index$macro$14 = 0;

                  for(int limit$macro$16 = a.length(); index$macro$14 < limit$macro$16; ++index$macro$14) {
                     rd[index$macro$14] = ad[aoff] - bd[boff];
                     aoff += a.stride();
                     boff += b.stride();
                  }
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_V_eq_V_idempotent_Float_OpSub())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Long_OpSub_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               long[] ad = a.data$mcJ$sp();
               long[] bd = b.data$mcJ$sp();
               int aoff = a.offset();
               int boff = b.offset();
               DenseVector result = DenseVector$.MODULE$.zeros$mJc$sp(a.length(), .MODULE$.Long(), Zero$.MODULE$.LongZero());
               long[] rd = result.data$mcJ$sp();
               if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                     rd[index$macro$4] = ad[index$macro$4] - bd[index$macro$4];
                  }
               } else if (a.stride() == 1 && b.stride() == 1) {
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = a.length(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                     rd[index$macro$9] = ad[index$macro$9 + aoff] - bd[index$macro$9 + boff];
                  }
               } else {
                  int index$macro$14 = 0;

                  for(int limit$macro$16 = a.length(); index$macro$14 < limit$macro$16; ++index$macro$14) {
                     rd[index$macro$14] = ad[aoff] - bd[boff];
                     aoff += a.stride();
                     boff += b.stride();
                  }
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_V_eq_V_idempotent_Long_OpSub())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Int_OpMulScalar_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int[] ad = a.data$mcI$sp();
               int[] bd = b.data$mcI$sp();
               int aoff = a.offset();
               int boff = b.offset();
               DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(a.length(), .MODULE$.Int(), Zero$.MODULE$.IntZero());
               int[] rd = result.data$mcI$sp();
               if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                     rd[index$macro$4] = ad[index$macro$4] * bd[index$macro$4];
                  }
               } else if (a.stride() == 1 && b.stride() == 1) {
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = a.length(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                     rd[index$macro$9] = ad[index$macro$9 + aoff] * bd[index$macro$9 + boff];
                  }
               } else {
                  int index$macro$14 = 0;

                  for(int limit$macro$16 = a.length(); index$macro$14 < limit$macro$16; ++index$macro$14) {
                     rd[index$macro$14] = ad[aoff] * bd[boff];
                     aoff += a.stride();
                     boff += b.stride();
                  }
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_V_eq_V_nilpotent_Int())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Double_OpMulScalar_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               double[] ad = a.data$mcD$sp();
               double[] bd = b.data$mcD$sp();
               int aoff = a.offset();
               int boff = b.offset();
               DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(a.length(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
               double[] rd = result.data$mcD$sp();
               if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                     rd[index$macro$4] = ad[index$macro$4] * bd[index$macro$4];
                  }
               } else if (a.stride() == 1 && b.stride() == 1) {
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = a.length(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                     rd[index$macro$9] = ad[index$macro$9 + aoff] * bd[index$macro$9 + boff];
                  }
               } else {
                  int index$macro$14 = 0;

                  for(int limit$macro$16 = a.length(); index$macro$14 < limit$macro$16; ++index$macro$14) {
                     rd[index$macro$14] = ad[aoff] * bd[boff];
                     aoff += a.stride();
                     boff += b.stride();
                  }
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_V_eq_V_nilpotent_Double())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Float_OpMulScalar_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               float[] ad = a.data$mcF$sp();
               float[] bd = b.data$mcF$sp();
               int aoff = a.offset();
               int boff = b.offset();
               DenseVector result = DenseVector$.MODULE$.zeros$mFc$sp(a.length(), .MODULE$.Float(), Zero$.MODULE$.FloatZero());
               float[] rd = result.data$mcF$sp();
               if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                     rd[index$macro$4] = ad[index$macro$4] * bd[index$macro$4];
                  }
               } else if (a.stride() == 1 && b.stride() == 1) {
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = a.length(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                     rd[index$macro$9] = ad[index$macro$9 + aoff] * bd[index$macro$9 + boff];
                  }
               } else {
                  int index$macro$14 = 0;

                  for(int limit$macro$16 = a.length(); index$macro$14 < limit$macro$16; ++index$macro$14) {
                     rd[index$macro$14] = ad[aoff] * bd[boff];
                     aoff += a.stride();
                     boff += b.stride();
                  }
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_V_eq_V_nilpotent_Float())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Long_OpMulScalar_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               long[] ad = a.data$mcJ$sp();
               long[] bd = b.data$mcJ$sp();
               int aoff = a.offset();
               int boff = b.offset();
               DenseVector result = DenseVector$.MODULE$.zeros$mJc$sp(a.length(), .MODULE$.Long(), Zero$.MODULE$.LongZero());
               long[] rd = result.data$mcJ$sp();
               if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                     rd[index$macro$4] = ad[index$macro$4] * bd[index$macro$4];
                  }
               } else if (a.stride() == 1 && b.stride() == 1) {
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = a.length(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                     rd[index$macro$9] = ad[index$macro$9 + aoff] * bd[index$macro$9 + boff];
                  }
               } else {
                  int index$macro$14 = 0;

                  for(int limit$macro$16 = a.length(); index$macro$14 < limit$macro$16; ++index$macro$14) {
                     rd[index$macro$14] = ad[aoff] * bd[boff];
                     aoff += a.stride();
                     boff += b.stride();
                  }
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_V_eq_V_nilpotent_Long())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Int_OpDiv_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int[] ad = a.data$mcI$sp();
               int[] bd = b.data$mcI$sp();
               int aoff = a.offset();
               int boff = b.offset();
               DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(a.length(), .MODULE$.Int(), Zero$.MODULE$.IntZero());
               int[] rd = result.data$mcI$sp();
               if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                     rd[index$macro$4] = ad[index$macro$4] / bd[index$macro$4];
                  }
               } else if (a.stride() == 1 && b.stride() == 1) {
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = a.length(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                     rd[index$macro$9] = ad[index$macro$9 + aoff] / bd[index$macro$9 + boff];
                  }
               } else {
                  int index$macro$14 = 0;

                  for(int limit$macro$16 = a.length(); index$macro$14 < limit$macro$16; ++index$macro$14) {
                     rd[index$macro$14] = ad[aoff] / bd[boff];
                     aoff += a.stride();
                     boff += b.stride();
                  }
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_V_eq_V_Int_OpDiv())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Double_OpDiv_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               double[] ad = a.data$mcD$sp();
               double[] bd = b.data$mcD$sp();
               int aoff = a.offset();
               int boff = b.offset();
               DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(a.length(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
               double[] rd = result.data$mcD$sp();
               if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                     rd[index$macro$4] = ad[index$macro$4] / bd[index$macro$4];
                  }
               } else if (a.stride() == 1 && b.stride() == 1) {
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = a.length(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                     rd[index$macro$9] = ad[index$macro$9 + aoff] / bd[index$macro$9 + boff];
                  }
               } else {
                  int index$macro$14 = 0;

                  for(int limit$macro$16 = a.length(); index$macro$14 < limit$macro$16; ++index$macro$14) {
                     rd[index$macro$14] = ad[aoff] / bd[boff];
                     aoff += a.stride();
                     boff += b.stride();
                  }
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_V_eq_V_Double_OpDiv())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Float_OpDiv_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               float[] ad = a.data$mcF$sp();
               float[] bd = b.data$mcF$sp();
               int aoff = a.offset();
               int boff = b.offset();
               DenseVector result = DenseVector$.MODULE$.zeros$mFc$sp(a.length(), .MODULE$.Float(), Zero$.MODULE$.FloatZero());
               float[] rd = result.data$mcF$sp();
               if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                     rd[index$macro$4] = ad[index$macro$4] / bd[index$macro$4];
                  }
               } else if (a.stride() == 1 && b.stride() == 1) {
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = a.length(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                     rd[index$macro$9] = ad[index$macro$9 + aoff] / bd[index$macro$9 + boff];
                  }
               } else {
                  int index$macro$14 = 0;

                  for(int limit$macro$16 = a.length(); index$macro$14 < limit$macro$16; ++index$macro$14) {
                     rd[index$macro$14] = ad[aoff] / bd[boff];
                     aoff += a.stride();
                     boff += b.stride();
                  }
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_V_eq_V_Float_OpDiv())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Long_OpDiv_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               long[] ad = a.data$mcJ$sp();
               long[] bd = b.data$mcJ$sp();
               int aoff = a.offset();
               int boff = b.offset();
               DenseVector result = DenseVector$.MODULE$.zeros$mJc$sp(a.length(), .MODULE$.Long(), Zero$.MODULE$.LongZero());
               long[] rd = result.data$mcJ$sp();
               if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                     rd[index$macro$4] = ad[index$macro$4] / bd[index$macro$4];
                  }
               } else if (a.stride() == 1 && b.stride() == 1) {
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = a.length(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                     rd[index$macro$9] = ad[index$macro$9 + aoff] / bd[index$macro$9 + boff];
                  }
               } else {
                  int index$macro$14 = 0;

                  for(int limit$macro$16 = a.length(); index$macro$14 < limit$macro$16; ++index$macro$14) {
                     rd[index$macro$14] = ad[aoff] / bd[boff];
                     aoff += a.stride();
                     boff += b.stride();
                  }
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_V_eq_V_Long_OpDiv())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Int_OpSet_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int[] ad = a.data$mcI$sp();
               int[] bd = b.data$mcI$sp();
               int aoff = a.offset();
               int boff = b.offset();
               DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(a.length(), .MODULE$.Int(), Zero$.MODULE$.IntZero());
               int[] rd = result.data$mcI$sp();
               if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                     rd[index$macro$4] = bd[index$macro$4];
                  }
               } else if (a.stride() == 1 && b.stride() == 1) {
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = a.length(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                     rd[index$macro$9] = bd[index$macro$9 + boff];
                  }
               } else {
                  int index$macro$14 = 0;

                  for(int limit$macro$16 = a.length(); index$macro$14 < limit$macro$16; ++index$macro$14) {
                     rd[index$macro$14] = bd[boff];
                     aoff += a.stride();
                     boff += b.stride();
                  }
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_V_eq_V_Int_OpSet())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Double_OpSet_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               double[] ad = a.data$mcD$sp();
               double[] bd = b.data$mcD$sp();
               int aoff = a.offset();
               int boff = b.offset();
               DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(a.length(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
               double[] rd = result.data$mcD$sp();
               if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                     rd[index$macro$4] = bd[index$macro$4];
                  }
               } else if (a.stride() == 1 && b.stride() == 1) {
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = a.length(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                     rd[index$macro$9] = bd[index$macro$9 + boff];
                  }
               } else {
                  int index$macro$14 = 0;

                  for(int limit$macro$16 = a.length(); index$macro$14 < limit$macro$16; ++index$macro$14) {
                     rd[index$macro$14] = bd[boff];
                     aoff += a.stride();
                     boff += b.stride();
                  }
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_V_eq_V_Double_OpSet())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Float_OpSet_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               float[] ad = a.data$mcF$sp();
               float[] bd = b.data$mcF$sp();
               int aoff = a.offset();
               int boff = b.offset();
               DenseVector result = DenseVector$.MODULE$.zeros$mFc$sp(a.length(), .MODULE$.Float(), Zero$.MODULE$.FloatZero());
               float[] rd = result.data$mcF$sp();
               if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                     rd[index$macro$4] = bd[index$macro$4];
                  }
               } else if (a.stride() == 1 && b.stride() == 1) {
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = a.length(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                     rd[index$macro$9] = bd[index$macro$9 + boff];
                  }
               } else {
                  int index$macro$14 = 0;

                  for(int limit$macro$16 = a.length(); index$macro$14 < limit$macro$16; ++index$macro$14) {
                     rd[index$macro$14] = bd[boff];
                     aoff += a.stride();
                     boff += b.stride();
                  }
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_V_eq_V_Float_OpSet())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Long_OpSet_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               long[] ad = a.data$mcJ$sp();
               long[] bd = b.data$mcJ$sp();
               int aoff = a.offset();
               int boff = b.offset();
               DenseVector result = DenseVector$.MODULE$.zeros$mJc$sp(a.length(), .MODULE$.Long(), Zero$.MODULE$.LongZero());
               long[] rd = result.data$mcJ$sp();
               if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                     rd[index$macro$4] = bd[index$macro$4];
                  }
               } else if (a.stride() == 1 && b.stride() == 1) {
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = a.length(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                     rd[index$macro$9] = bd[index$macro$9 + boff];
                  }
               } else {
                  int index$macro$14 = 0;

                  for(int limit$macro$16 = a.length(); index$macro$14 < limit$macro$16; ++index$macro$14) {
                     rd[index$macro$14] = bd[boff];
                     aoff += a.stride();
                     boff += b.stride();
                  }
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_V_eq_V_Long_OpSet())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Int_OpMod_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int[] ad = a.data$mcI$sp();
               int[] bd = b.data$mcI$sp();
               int aoff = a.offset();
               int boff = b.offset();
               DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(a.length(), .MODULE$.Int(), Zero$.MODULE$.IntZero());
               int[] rd = result.data$mcI$sp();
               if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                     rd[index$macro$4] = ad[index$macro$4] % bd[index$macro$4];
                  }
               } else if (a.stride() == 1 && b.stride() == 1) {
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = a.length(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                     rd[index$macro$9] = ad[index$macro$9 + aoff] % bd[index$macro$9 + boff];
                  }
               } else {
                  int index$macro$14 = 0;

                  for(int limit$macro$16 = a.length(); index$macro$14 < limit$macro$16; ++index$macro$14) {
                     rd[index$macro$14] = ad[aoff] % bd[boff];
                     aoff += a.stride();
                     boff += b.stride();
                  }
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_V_eq_V_Int_OpMod())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Double_OpMod_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               double[] ad = a.data$mcD$sp();
               double[] bd = b.data$mcD$sp();
               int aoff = a.offset();
               int boff = b.offset();
               DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(a.length(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
               double[] rd = result.data$mcD$sp();
               if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                     rd[index$macro$4] = ad[index$macro$4] % bd[index$macro$4];
                  }
               } else if (a.stride() == 1 && b.stride() == 1) {
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = a.length(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                     rd[index$macro$9] = ad[index$macro$9 + aoff] % bd[index$macro$9 + boff];
                  }
               } else {
                  int index$macro$14 = 0;

                  for(int limit$macro$16 = a.length(); index$macro$14 < limit$macro$16; ++index$macro$14) {
                     rd[index$macro$14] = ad[aoff] % bd[boff];
                     aoff += a.stride();
                     boff += b.stride();
                  }
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_V_eq_V_Double_OpMod())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Float_OpMod_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               float[] ad = a.data$mcF$sp();
               float[] bd = b.data$mcF$sp();
               int aoff = a.offset();
               int boff = b.offset();
               DenseVector result = DenseVector$.MODULE$.zeros$mFc$sp(a.length(), .MODULE$.Float(), Zero$.MODULE$.FloatZero());
               float[] rd = result.data$mcF$sp();
               if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                     rd[index$macro$4] = ad[index$macro$4] % bd[index$macro$4];
                  }
               } else if (a.stride() == 1 && b.stride() == 1) {
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = a.length(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                     rd[index$macro$9] = ad[index$macro$9 + aoff] % bd[index$macro$9 + boff];
                  }
               } else {
                  int index$macro$14 = 0;

                  for(int limit$macro$16 = a.length(); index$macro$14 < limit$macro$16; ++index$macro$14) {
                     rd[index$macro$14] = ad[aoff] % bd[boff];
                     aoff += a.stride();
                     boff += b.stride();
                  }
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_V_eq_V_Float_OpMod())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Long_OpMod_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               long[] ad = a.data$mcJ$sp();
               long[] bd = b.data$mcJ$sp();
               int aoff = a.offset();
               int boff = b.offset();
               DenseVector result = DenseVector$.MODULE$.zeros$mJc$sp(a.length(), .MODULE$.Long(), Zero$.MODULE$.LongZero());
               long[] rd = result.data$mcJ$sp();
               if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                     rd[index$macro$4] = ad[index$macro$4] % bd[index$macro$4];
                  }
               } else if (a.stride() == 1 && b.stride() == 1) {
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = a.length(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                     rd[index$macro$9] = ad[index$macro$9 + aoff] % bd[index$macro$9 + boff];
                  }
               } else {
                  int index$macro$14 = 0;

                  for(int limit$macro$16 = a.length(); index$macro$14 < limit$macro$16; ++index$macro$14) {
                     rd[index$macro$14] = ad[aoff] % bd[boff];
                     aoff += a.stride();
                     boff += b.stride();
                  }
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_V_eq_V_Long_OpMod())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Int_OpPow_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int[] ad = a.data$mcI$sp();
               int[] bd = b.data$mcI$sp();
               int aoff = a.offset();
               int boff = b.offset();
               DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(a.length(), .MODULE$.Int(), Zero$.MODULE$.IntZero());
               int[] rd = result.data$mcI$sp();
               if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                     rd[index$macro$4] = PowImplicits$.MODULE$.IntPow(ad[index$macro$4]).pow(bd[index$macro$4]);
                  }
               } else if (a.stride() == 1 && b.stride() == 1) {
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = a.length(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                     rd[index$macro$9] = PowImplicits$.MODULE$.IntPow(ad[index$macro$9 + aoff]).pow(bd[index$macro$9 + boff]);
                  }
               } else {
                  int index$macro$14 = 0;

                  for(int limit$macro$16 = a.length(); index$macro$14 < limit$macro$16; ++index$macro$14) {
                     rd[index$macro$14] = PowImplicits$.MODULE$.IntPow(ad[aoff]).pow(bd[boff]);
                     aoff += a.stride();
                     boff += b.stride();
                  }
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_V_eq_V_Int_OpPow())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Double_OpPow_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               double[] ad = a.data$mcD$sp();
               double[] bd = b.data$mcD$sp();
               int aoff = a.offset();
               int boff = b.offset();
               DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(a.length(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
               double[] rd = result.data$mcD$sp();
               if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                     rd[index$macro$4] = PowImplicits$.MODULE$.DoublePow(ad[index$macro$4]).pow(bd[index$macro$4]);
                  }
               } else if (a.stride() == 1 && b.stride() == 1) {
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = a.length(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                     rd[index$macro$9] = PowImplicits$.MODULE$.DoublePow(ad[index$macro$9 + aoff]).pow(bd[index$macro$9 + boff]);
                  }
               } else {
                  int index$macro$14 = 0;

                  for(int limit$macro$16 = a.length(); index$macro$14 < limit$macro$16; ++index$macro$14) {
                     rd[index$macro$14] = PowImplicits$.MODULE$.DoublePow(ad[aoff]).pow(bd[boff]);
                     aoff += a.stride();
                     boff += b.stride();
                  }
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_V_eq_V_Double_OpPow())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Float_OpPow_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               float[] ad = a.data$mcF$sp();
               float[] bd = b.data$mcF$sp();
               int aoff = a.offset();
               int boff = b.offset();
               DenseVector result = DenseVector$.MODULE$.zeros$mFc$sp(a.length(), .MODULE$.Float(), Zero$.MODULE$.FloatZero());
               float[] rd = result.data$mcF$sp();
               if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                     rd[index$macro$4] = PowImplicits$.MODULE$.FloatPow(ad[index$macro$4]).pow(bd[index$macro$4]);
                  }
               } else if (a.stride() == 1 && b.stride() == 1) {
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = a.length(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                     rd[index$macro$9] = PowImplicits$.MODULE$.FloatPow(ad[index$macro$9 + aoff]).pow(bd[index$macro$9 + boff]);
                  }
               } else {
                  int index$macro$14 = 0;

                  for(int limit$macro$16 = a.length(); index$macro$14 < limit$macro$16; ++index$macro$14) {
                     rd[index$macro$14] = PowImplicits$.MODULE$.FloatPow(ad[aoff]).pow(bd[boff]);
                     aoff += a.stride();
                     boff += b.stride();
                  }
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_V_eq_V_Float_OpPow())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_DV_DV_eq_DV_Long_OpPow_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               long[] ad = a.data$mcJ$sp();
               long[] bd = b.data$mcJ$sp();
               int aoff = a.offset();
               int boff = b.offset();
               DenseVector result = DenseVector$.MODULE$.zeros$mJc$sp(a.length(), .MODULE$.Long(), Zero$.MODULE$.LongZero());
               long[] rd = result.data$mcJ$sp();
               if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                  int index$macro$4 = 0;

                  for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                     rd[index$macro$4] = PowImplicits$.MODULE$.LongPow(ad[index$macro$4]).pow(bd[index$macro$4]);
                  }
               } else if (a.stride() == 1 && b.stride() == 1) {
                  int index$macro$9 = 0;

                  for(int limit$macro$11 = a.length(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                     rd[index$macro$9] = PowImplicits$.MODULE$.LongPow(ad[index$macro$9 + aoff]).pow(bd[index$macro$9 + boff]);
                  }
               } else {
                  int index$macro$14 = 0;

                  for(int limit$macro$16 = a.length(); index$macro$14 < limit$macro$16; ++index$macro$14) {
                     rd[index$macro$14] = PowImplicits$.MODULE$.LongPow(ad[aoff]).pow(bd[boff]);
                     aoff += a.stride();
                     boff += b.stride();
                  }
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_V_V_eq_V_Long_OpPow())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Int_OpAdd_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final DenseVector b) {
            while(true) {
               int left$macro$1 = a.length();
               int right$macro$2 = b.length();
               if (left$macro$1 != right$macro$2) {
                  throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
               }

               int[] ad = a.data$mcI$sp();
               int[] bd = b.data$mcI$sp();
               int aoff = a.offset();
               int boff = b.offset();
               int astride = a.stride();
               int bstride = b.stride();
               int length = a.length();
               if (!a.overlaps$mcI$sp(b)) {
                  if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                     int index$macro$4 = 0;

                     for(int limit$macro$6 = length; index$macro$4 < limit$macro$6; ++index$macro$4) {
                        ad[index$macro$4] += bd[index$macro$4];
                     }

                     BoxedUnit var20 = BoxedUnit.UNIT;
                  } else if (astride == 1 && bstride == 1) {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = length; index$macro$9 < limit$macro$11; ++index$macro$9) {
                        ad[index$macro$9 + aoff] += bd[index$macro$9 + boff];
                     }

                     BoxedUnit var19 = BoxedUnit.UNIT;
                  } else {
                     int index$macro$14 = 0;

                     for(int limit$macro$16 = length; index$macro$14 < limit$macro$16; ++index$macro$14) {
                        ad[aoff + astride * index$macro$14] += bd[boff + bstride * index$macro$14];
                     }

                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  }

                  return;
               }

               b = b.copy$mcI$sp();
               a = a;
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_V_Idempotent_Int_OpAdd())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Double_OpAdd_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final DenseVector b) {
            while(true) {
               int left$macro$1 = a.length();
               int right$macro$2 = b.length();
               if (left$macro$1 != right$macro$2) {
                  throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
               }

               double[] ad = a.data$mcD$sp();
               double[] bd = b.data$mcD$sp();
               int aoff = a.offset();
               int boff = b.offset();
               int astride = a.stride();
               int bstride = b.stride();
               int length = a.length();
               if (!a.overlaps$mcD$sp(b)) {
                  if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                     int index$macro$4 = 0;

                     for(int limit$macro$6 = length; index$macro$4 < limit$macro$6; ++index$macro$4) {
                        ad[index$macro$4] += bd[index$macro$4];
                     }

                     BoxedUnit var20 = BoxedUnit.UNIT;
                  } else if (astride == 1 && bstride == 1) {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = length; index$macro$9 < limit$macro$11; ++index$macro$9) {
                        ad[index$macro$9 + aoff] += bd[index$macro$9 + boff];
                     }

                     BoxedUnit var19 = BoxedUnit.UNIT;
                  } else {
                     int index$macro$14 = 0;

                     for(int limit$macro$16 = length; index$macro$14 < limit$macro$16; ++index$macro$14) {
                        ad[aoff + astride * index$macro$14] += bd[boff + bstride * index$macro$14];
                     }

                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  }

                  return;
               }

               b = b.copy$mcD$sp();
               a = a;
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_V_Idempotent_Double_OpAdd())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Float_OpAdd_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final DenseVector b) {
            while(true) {
               int left$macro$1 = a.length();
               int right$macro$2 = b.length();
               if (left$macro$1 != right$macro$2) {
                  throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
               }

               float[] ad = a.data$mcF$sp();
               float[] bd = b.data$mcF$sp();
               int aoff = a.offset();
               int boff = b.offset();
               int astride = a.stride();
               int bstride = b.stride();
               int length = a.length();
               if (!a.overlaps$mcF$sp(b)) {
                  if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                     int index$macro$4 = 0;

                     for(int limit$macro$6 = length; index$macro$4 < limit$macro$6; ++index$macro$4) {
                        ad[index$macro$4] += bd[index$macro$4];
                     }

                     BoxedUnit var20 = BoxedUnit.UNIT;
                  } else if (astride == 1 && bstride == 1) {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = length; index$macro$9 < limit$macro$11; ++index$macro$9) {
                        ad[index$macro$9 + aoff] += bd[index$macro$9 + boff];
                     }

                     BoxedUnit var19 = BoxedUnit.UNIT;
                  } else {
                     int index$macro$14 = 0;

                     for(int limit$macro$16 = length; index$macro$14 < limit$macro$16; ++index$macro$14) {
                        ad[aoff + astride * index$macro$14] += bd[boff + bstride * index$macro$14];
                     }

                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  }

                  return;
               }

               b = b.copy$mcF$sp();
               a = a;
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_V_Idempotent_Float_OpAdd())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Long_OpAdd_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final DenseVector b) {
            while(true) {
               int left$macro$1 = a.length();
               int right$macro$2 = b.length();
               if (left$macro$1 != right$macro$2) {
                  throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
               }

               long[] ad = a.data$mcJ$sp();
               long[] bd = b.data$mcJ$sp();
               int aoff = a.offset();
               int boff = b.offset();
               int astride = a.stride();
               int bstride = b.stride();
               int length = a.length();
               if (!a.overlaps$mcJ$sp(b)) {
                  if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                     int index$macro$4 = 0;

                     for(int limit$macro$6 = length; index$macro$4 < limit$macro$6; ++index$macro$4) {
                        ad[index$macro$4] += bd[index$macro$4];
                     }

                     BoxedUnit var20 = BoxedUnit.UNIT;
                  } else if (astride == 1 && bstride == 1) {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = length; index$macro$9 < limit$macro$11; ++index$macro$9) {
                        ad[index$macro$9 + aoff] += bd[index$macro$9 + boff];
                     }

                     BoxedUnit var19 = BoxedUnit.UNIT;
                  } else {
                     int index$macro$14 = 0;

                     for(int limit$macro$16 = length; index$macro$14 < limit$macro$16; ++index$macro$14) {
                        ad[aoff + astride * index$macro$14] += bd[boff + bstride * index$macro$14];
                     }

                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  }

                  return;
               }

               b = b.copy$mcJ$sp();
               a = a;
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_V_Idempotent_Long_OpAdd())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Int_OpSub_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final DenseVector b) {
            while(true) {
               int left$macro$1 = a.length();
               int right$macro$2 = b.length();
               if (left$macro$1 != right$macro$2) {
                  throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
               }

               int[] ad = a.data$mcI$sp();
               int[] bd = b.data$mcI$sp();
               int aoff = a.offset();
               int boff = b.offset();
               int astride = a.stride();
               int bstride = b.stride();
               int length = a.length();
               if (!a.overlaps$mcI$sp(b)) {
                  if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                     int index$macro$4 = 0;

                     for(int limit$macro$6 = length; index$macro$4 < limit$macro$6; ++index$macro$4) {
                        ad[index$macro$4] -= bd[index$macro$4];
                     }

                     BoxedUnit var20 = BoxedUnit.UNIT;
                  } else if (astride == 1 && bstride == 1) {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = length; index$macro$9 < limit$macro$11; ++index$macro$9) {
                        ad[index$macro$9 + aoff] -= bd[index$macro$9 + boff];
                     }

                     BoxedUnit var19 = BoxedUnit.UNIT;
                  } else {
                     int index$macro$14 = 0;

                     for(int limit$macro$16 = length; index$macro$14 < limit$macro$16; ++index$macro$14) {
                        ad[aoff + astride * index$macro$14] -= bd[boff + bstride * index$macro$14];
                     }

                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  }

                  return;
               }

               b = b.copy$mcI$sp();
               a = a;
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_V_Idempotent_Int_OpSub())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Double_OpSub_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final DenseVector b) {
            while(true) {
               int left$macro$1 = a.length();
               int right$macro$2 = b.length();
               if (left$macro$1 != right$macro$2) {
                  throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
               }

               double[] ad = a.data$mcD$sp();
               double[] bd = b.data$mcD$sp();
               int aoff = a.offset();
               int boff = b.offset();
               int astride = a.stride();
               int bstride = b.stride();
               int length = a.length();
               if (!a.overlaps$mcD$sp(b)) {
                  if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                     int index$macro$4 = 0;

                     for(int limit$macro$6 = length; index$macro$4 < limit$macro$6; ++index$macro$4) {
                        ad[index$macro$4] -= bd[index$macro$4];
                     }

                     BoxedUnit var20 = BoxedUnit.UNIT;
                  } else if (astride == 1 && bstride == 1) {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = length; index$macro$9 < limit$macro$11; ++index$macro$9) {
                        ad[index$macro$9 + aoff] -= bd[index$macro$9 + boff];
                     }

                     BoxedUnit var19 = BoxedUnit.UNIT;
                  } else {
                     int index$macro$14 = 0;

                     for(int limit$macro$16 = length; index$macro$14 < limit$macro$16; ++index$macro$14) {
                        ad[aoff + astride * index$macro$14] -= bd[boff + bstride * index$macro$14];
                     }

                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  }

                  return;
               }

               b = b.copy$mcD$sp();
               a = a;
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_V_Idempotent_Double_OpSub())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Float_OpSub_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final DenseVector b) {
            while(true) {
               int left$macro$1 = a.length();
               int right$macro$2 = b.length();
               if (left$macro$1 != right$macro$2) {
                  throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
               }

               float[] ad = a.data$mcF$sp();
               float[] bd = b.data$mcF$sp();
               int aoff = a.offset();
               int boff = b.offset();
               int astride = a.stride();
               int bstride = b.stride();
               int length = a.length();
               if (!a.overlaps$mcF$sp(b)) {
                  if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                     int index$macro$4 = 0;

                     for(int limit$macro$6 = length; index$macro$4 < limit$macro$6; ++index$macro$4) {
                        ad[index$macro$4] -= bd[index$macro$4];
                     }

                     BoxedUnit var20 = BoxedUnit.UNIT;
                  } else if (astride == 1 && bstride == 1) {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = length; index$macro$9 < limit$macro$11; ++index$macro$9) {
                        ad[index$macro$9 + aoff] -= bd[index$macro$9 + boff];
                     }

                     BoxedUnit var19 = BoxedUnit.UNIT;
                  } else {
                     int index$macro$14 = 0;

                     for(int limit$macro$16 = length; index$macro$14 < limit$macro$16; ++index$macro$14) {
                        ad[aoff + astride * index$macro$14] -= bd[boff + bstride * index$macro$14];
                     }

                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  }

                  return;
               }

               b = b.copy$mcF$sp();
               a = a;
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_V_Idempotent_Float_OpSub())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Long_OpSub_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final DenseVector b) {
            while(true) {
               int left$macro$1 = a.length();
               int right$macro$2 = b.length();
               if (left$macro$1 != right$macro$2) {
                  throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
               }

               long[] ad = a.data$mcJ$sp();
               long[] bd = b.data$mcJ$sp();
               int aoff = a.offset();
               int boff = b.offset();
               int astride = a.stride();
               int bstride = b.stride();
               int length = a.length();
               if (!a.overlaps$mcJ$sp(b)) {
                  if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                     int index$macro$4 = 0;

                     for(int limit$macro$6 = length; index$macro$4 < limit$macro$6; ++index$macro$4) {
                        ad[index$macro$4] -= bd[index$macro$4];
                     }

                     BoxedUnit var20 = BoxedUnit.UNIT;
                  } else if (astride == 1 && bstride == 1) {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = length; index$macro$9 < limit$macro$11; ++index$macro$9) {
                        ad[index$macro$9 + aoff] -= bd[index$macro$9 + boff];
                     }

                     BoxedUnit var19 = BoxedUnit.UNIT;
                  } else {
                     int index$macro$14 = 0;

                     for(int limit$macro$16 = length; index$macro$14 < limit$macro$16; ++index$macro$14) {
                        ad[aoff + astride * index$macro$14] -= bd[boff + bstride * index$macro$14];
                     }

                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  }

                  return;
               }

               b = b.copy$mcJ$sp();
               a = a;
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_V_Idempotent_Long_OpSub())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Int_OpMulScalar_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final DenseVector b) {
            while(true) {
               int left$macro$1 = a.length();
               int right$macro$2 = b.length();
               if (left$macro$1 != right$macro$2) {
                  throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
               }

               int[] ad = a.data$mcI$sp();
               int[] bd = b.data$mcI$sp();
               int aoff = a.offset();
               int boff = b.offset();
               int astride = a.stride();
               int bstride = b.stride();
               int length = a.length();
               if (!a.overlaps$mcI$sp(b)) {
                  if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                     int index$macro$4 = 0;

                     for(int limit$macro$6 = length; index$macro$4 < limit$macro$6; ++index$macro$4) {
                        ad[index$macro$4] *= bd[index$macro$4];
                     }

                     BoxedUnit var20 = BoxedUnit.UNIT;
                  } else if (astride == 1 && bstride == 1) {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = length; index$macro$9 < limit$macro$11; ++index$macro$9) {
                        ad[index$macro$9 + aoff] *= bd[index$macro$9 + boff];
                     }

                     BoxedUnit var19 = BoxedUnit.UNIT;
                  } else {
                     int index$macro$14 = 0;

                     for(int limit$macro$16 = length; index$macro$14 < limit$macro$16; ++index$macro$14) {
                        ad[aoff + astride * index$macro$14] *= bd[boff + bstride * index$macro$14];
                     }

                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  }

                  return;
               }

               b = b.copy$mcI$sp();
               a = a;
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_V_Int_OpMulScalar())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Double_OpMulScalar_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final DenseVector b) {
            while(true) {
               int left$macro$1 = a.length();
               int right$macro$2 = b.length();
               if (left$macro$1 != right$macro$2) {
                  throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
               }

               double[] ad = a.data$mcD$sp();
               double[] bd = b.data$mcD$sp();
               int aoff = a.offset();
               int boff = b.offset();
               int astride = a.stride();
               int bstride = b.stride();
               int length = a.length();
               if (!a.overlaps$mcD$sp(b)) {
                  if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                     int index$macro$4 = 0;

                     for(int limit$macro$6 = length; index$macro$4 < limit$macro$6; ++index$macro$4) {
                        ad[index$macro$4] *= bd[index$macro$4];
                     }

                     BoxedUnit var20 = BoxedUnit.UNIT;
                  } else if (astride == 1 && bstride == 1) {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = length; index$macro$9 < limit$macro$11; ++index$macro$9) {
                        ad[index$macro$9 + aoff] *= bd[index$macro$9 + boff];
                     }

                     BoxedUnit var19 = BoxedUnit.UNIT;
                  } else {
                     int index$macro$14 = 0;

                     for(int limit$macro$16 = length; index$macro$14 < limit$macro$16; ++index$macro$14) {
                        ad[aoff + astride * index$macro$14] *= bd[boff + bstride * index$macro$14];
                     }

                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  }

                  return;
               }

               b = b.copy$mcD$sp();
               a = a;
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_V_Double_OpMulScalar())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Float_OpMulScalar_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final DenseVector b) {
            while(true) {
               int left$macro$1 = a.length();
               int right$macro$2 = b.length();
               if (left$macro$1 != right$macro$2) {
                  throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
               }

               float[] ad = a.data$mcF$sp();
               float[] bd = b.data$mcF$sp();
               int aoff = a.offset();
               int boff = b.offset();
               int astride = a.stride();
               int bstride = b.stride();
               int length = a.length();
               if (!a.overlaps$mcF$sp(b)) {
                  if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                     int index$macro$4 = 0;

                     for(int limit$macro$6 = length; index$macro$4 < limit$macro$6; ++index$macro$4) {
                        ad[index$macro$4] *= bd[index$macro$4];
                     }

                     BoxedUnit var20 = BoxedUnit.UNIT;
                  } else if (astride == 1 && bstride == 1) {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = length; index$macro$9 < limit$macro$11; ++index$macro$9) {
                        ad[index$macro$9 + aoff] *= bd[index$macro$9 + boff];
                     }

                     BoxedUnit var19 = BoxedUnit.UNIT;
                  } else {
                     int index$macro$14 = 0;

                     for(int limit$macro$16 = length; index$macro$14 < limit$macro$16; ++index$macro$14) {
                        ad[aoff + astride * index$macro$14] *= bd[boff + bstride * index$macro$14];
                     }

                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  }

                  return;
               }

               b = b.copy$mcF$sp();
               a = a;
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_V_Float_OpMulScalar())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Long_OpMulScalar_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final DenseVector b) {
            while(true) {
               int left$macro$1 = a.length();
               int right$macro$2 = b.length();
               if (left$macro$1 != right$macro$2) {
                  throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
               }

               long[] ad = a.data$mcJ$sp();
               long[] bd = b.data$mcJ$sp();
               int aoff = a.offset();
               int boff = b.offset();
               int astride = a.stride();
               int bstride = b.stride();
               int length = a.length();
               if (!a.overlaps$mcJ$sp(b)) {
                  if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                     int index$macro$4 = 0;

                     for(int limit$macro$6 = length; index$macro$4 < limit$macro$6; ++index$macro$4) {
                        ad[index$macro$4] *= bd[index$macro$4];
                     }

                     BoxedUnit var20 = BoxedUnit.UNIT;
                  } else if (astride == 1 && bstride == 1) {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = length; index$macro$9 < limit$macro$11; ++index$macro$9) {
                        ad[index$macro$9 + aoff] *= bd[index$macro$9 + boff];
                     }

                     BoxedUnit var19 = BoxedUnit.UNIT;
                  } else {
                     int index$macro$14 = 0;

                     for(int limit$macro$16 = length; index$macro$14 < limit$macro$16; ++index$macro$14) {
                        ad[aoff + astride * index$macro$14] *= bd[boff + bstride * index$macro$14];
                     }

                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  }

                  return;
               }

               b = b.copy$mcJ$sp();
               a = a;
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_V_Long_OpMulScalar())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Int_OpDiv_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final DenseVector b) {
            while(true) {
               int left$macro$1 = a.length();
               int right$macro$2 = b.length();
               if (left$macro$1 != right$macro$2) {
                  throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
               }

               int[] ad = a.data$mcI$sp();
               int[] bd = b.data$mcI$sp();
               int aoff = a.offset();
               int boff = b.offset();
               int astride = a.stride();
               int bstride = b.stride();
               int length = a.length();
               if (!a.overlaps$mcI$sp(b)) {
                  if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                     int index$macro$4 = 0;

                     for(int limit$macro$6 = length; index$macro$4 < limit$macro$6; ++index$macro$4) {
                        ad[index$macro$4] /= bd[index$macro$4];
                     }

                     BoxedUnit var20 = BoxedUnit.UNIT;
                  } else if (astride == 1 && bstride == 1) {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = length; index$macro$9 < limit$macro$11; ++index$macro$9) {
                        ad[index$macro$9 + aoff] /= bd[index$macro$9 + boff];
                     }

                     BoxedUnit var19 = BoxedUnit.UNIT;
                  } else {
                     int index$macro$14 = 0;

                     for(int limit$macro$16 = length; index$macro$14 < limit$macro$16; ++index$macro$14) {
                        ad[aoff + astride * index$macro$14] /= bd[boff + bstride * index$macro$14];
                     }

                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  }

                  return;
               }

               b = b.copy$mcI$sp();
               a = a;
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_V_Int_OpDiv())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Double_OpDiv_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final DenseVector b) {
            while(true) {
               int left$macro$1 = a.length();
               int right$macro$2 = b.length();
               if (left$macro$1 != right$macro$2) {
                  throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
               }

               double[] ad = a.data$mcD$sp();
               double[] bd = b.data$mcD$sp();
               int aoff = a.offset();
               int boff = b.offset();
               int astride = a.stride();
               int bstride = b.stride();
               int length = a.length();
               if (!a.overlaps$mcD$sp(b)) {
                  if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                     int index$macro$4 = 0;

                     for(int limit$macro$6 = length; index$macro$4 < limit$macro$6; ++index$macro$4) {
                        ad[index$macro$4] /= bd[index$macro$4];
                     }

                     BoxedUnit var20 = BoxedUnit.UNIT;
                  } else if (astride == 1 && bstride == 1) {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = length; index$macro$9 < limit$macro$11; ++index$macro$9) {
                        ad[index$macro$9 + aoff] /= bd[index$macro$9 + boff];
                     }

                     BoxedUnit var19 = BoxedUnit.UNIT;
                  } else {
                     int index$macro$14 = 0;

                     for(int limit$macro$16 = length; index$macro$14 < limit$macro$16; ++index$macro$14) {
                        ad[aoff + astride * index$macro$14] /= bd[boff + bstride * index$macro$14];
                     }

                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  }

                  return;
               }

               b = b.copy$mcD$sp();
               a = a;
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_V_Double_OpDiv())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Float_OpDiv_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final DenseVector b) {
            while(true) {
               int left$macro$1 = a.length();
               int right$macro$2 = b.length();
               if (left$macro$1 != right$macro$2) {
                  throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
               }

               float[] ad = a.data$mcF$sp();
               float[] bd = b.data$mcF$sp();
               int aoff = a.offset();
               int boff = b.offset();
               int astride = a.stride();
               int bstride = b.stride();
               int length = a.length();
               if (!a.overlaps$mcF$sp(b)) {
                  if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                     int index$macro$4 = 0;

                     for(int limit$macro$6 = length; index$macro$4 < limit$macro$6; ++index$macro$4) {
                        ad[index$macro$4] /= bd[index$macro$4];
                     }

                     BoxedUnit var20 = BoxedUnit.UNIT;
                  } else if (astride == 1 && bstride == 1) {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = length; index$macro$9 < limit$macro$11; ++index$macro$9) {
                        ad[index$macro$9 + aoff] /= bd[index$macro$9 + boff];
                     }

                     BoxedUnit var19 = BoxedUnit.UNIT;
                  } else {
                     int index$macro$14 = 0;

                     for(int limit$macro$16 = length; index$macro$14 < limit$macro$16; ++index$macro$14) {
                        ad[aoff + astride * index$macro$14] /= bd[boff + bstride * index$macro$14];
                     }

                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  }

                  return;
               }

               b = b.copy$mcF$sp();
               a = a;
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_V_Float_OpDiv())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Long_OpDiv_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final DenseVector b) {
            while(true) {
               int left$macro$1 = a.length();
               int right$macro$2 = b.length();
               if (left$macro$1 != right$macro$2) {
                  throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
               }

               long[] ad = a.data$mcJ$sp();
               long[] bd = b.data$mcJ$sp();
               int aoff = a.offset();
               int boff = b.offset();
               int astride = a.stride();
               int bstride = b.stride();
               int length = a.length();
               if (!a.overlaps$mcJ$sp(b)) {
                  if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                     int index$macro$4 = 0;

                     for(int limit$macro$6 = length; index$macro$4 < limit$macro$6; ++index$macro$4) {
                        ad[index$macro$4] /= bd[index$macro$4];
                     }

                     BoxedUnit var20 = BoxedUnit.UNIT;
                  } else if (astride == 1 && bstride == 1) {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = length; index$macro$9 < limit$macro$11; ++index$macro$9) {
                        ad[index$macro$9 + aoff] /= bd[index$macro$9 + boff];
                     }

                     BoxedUnit var19 = BoxedUnit.UNIT;
                  } else {
                     int index$macro$14 = 0;

                     for(int limit$macro$16 = length; index$macro$14 < limit$macro$16; ++index$macro$14) {
                        ad[aoff + astride * index$macro$14] /= bd[boff + bstride * index$macro$14];
                     }

                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  }

                  return;
               }

               b = b.copy$mcJ$sp();
               a = a;
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_V_Long_OpDiv())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Int_OpSet_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final DenseVector b) {
            while(true) {
               int left$macro$1 = a.length();
               int right$macro$2 = b.length();
               if (left$macro$1 != right$macro$2) {
                  throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
               }

               int[] ad = a.data$mcI$sp();
               int[] bd = b.data$mcI$sp();
               int aoff = a.offset();
               int boff = b.offset();
               int astride = a.stride();
               int bstride = b.stride();
               int length = a.length();
               if (!a.overlaps$mcI$sp(b)) {
                  if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                     int index$macro$4 = 0;

                     for(int limit$macro$6 = length; index$macro$4 < limit$macro$6; ++index$macro$4) {
                        ad[index$macro$4] = bd[index$macro$4];
                     }

                     BoxedUnit var20 = BoxedUnit.UNIT;
                  } else if (astride == 1 && bstride == 1) {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = length; index$macro$9 < limit$macro$11; ++index$macro$9) {
                        ad[index$macro$9 + aoff] = bd[index$macro$9 + boff];
                     }

                     BoxedUnit var19 = BoxedUnit.UNIT;
                  } else {
                     int index$macro$14 = 0;

                     for(int limit$macro$16 = length; index$macro$14 < limit$macro$16; ++index$macro$14) {
                        ad[aoff + astride * index$macro$14] = bd[boff + bstride * index$macro$14];
                     }

                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  }

                  return;
               }

               b = b.copy$mcI$sp();
               a = a;
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_V_Int_OpSet())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Double_OpSet_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final DenseVector b) {
            while(true) {
               int left$macro$1 = a.length();
               int right$macro$2 = b.length();
               if (left$macro$1 != right$macro$2) {
                  throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
               }

               double[] ad = a.data$mcD$sp();
               double[] bd = b.data$mcD$sp();
               int aoff = a.offset();
               int boff = b.offset();
               int astride = a.stride();
               int bstride = b.stride();
               int length = a.length();
               if (!a.overlaps$mcD$sp(b)) {
                  if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                     int index$macro$4 = 0;

                     for(int limit$macro$6 = length; index$macro$4 < limit$macro$6; ++index$macro$4) {
                        ad[index$macro$4] = bd[index$macro$4];
                     }

                     BoxedUnit var20 = BoxedUnit.UNIT;
                  } else if (astride == 1 && bstride == 1) {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = length; index$macro$9 < limit$macro$11; ++index$macro$9) {
                        ad[index$macro$9 + aoff] = bd[index$macro$9 + boff];
                     }

                     BoxedUnit var19 = BoxedUnit.UNIT;
                  } else {
                     int index$macro$14 = 0;

                     for(int limit$macro$16 = length; index$macro$14 < limit$macro$16; ++index$macro$14) {
                        ad[aoff + astride * index$macro$14] = bd[boff + bstride * index$macro$14];
                     }

                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  }

                  return;
               }

               b = b.copy$mcD$sp();
               a = a;
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_V_Double_OpSet())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Float_OpSet_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final DenseVector b) {
            while(true) {
               int left$macro$1 = a.length();
               int right$macro$2 = b.length();
               if (left$macro$1 != right$macro$2) {
                  throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
               }

               float[] ad = a.data$mcF$sp();
               float[] bd = b.data$mcF$sp();
               int aoff = a.offset();
               int boff = b.offset();
               int astride = a.stride();
               int bstride = b.stride();
               int length = a.length();
               if (!a.overlaps$mcF$sp(b)) {
                  if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                     int index$macro$4 = 0;

                     for(int limit$macro$6 = length; index$macro$4 < limit$macro$6; ++index$macro$4) {
                        ad[index$macro$4] = bd[index$macro$4];
                     }

                     BoxedUnit var20 = BoxedUnit.UNIT;
                  } else if (astride == 1 && bstride == 1) {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = length; index$macro$9 < limit$macro$11; ++index$macro$9) {
                        ad[index$macro$9 + aoff] = bd[index$macro$9 + boff];
                     }

                     BoxedUnit var19 = BoxedUnit.UNIT;
                  } else {
                     int index$macro$14 = 0;

                     for(int limit$macro$16 = length; index$macro$14 < limit$macro$16; ++index$macro$14) {
                        ad[aoff + astride * index$macro$14] = bd[boff + bstride * index$macro$14];
                     }

                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  }

                  return;
               }

               b = b.copy$mcF$sp();
               a = a;
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_V_Float_OpSet())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Long_OpSet_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final DenseVector b) {
            while(true) {
               int left$macro$1 = a.length();
               int right$macro$2 = b.length();
               if (left$macro$1 != right$macro$2) {
                  throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
               }

               long[] ad = a.data$mcJ$sp();
               long[] bd = b.data$mcJ$sp();
               int aoff = a.offset();
               int boff = b.offset();
               int astride = a.stride();
               int bstride = b.stride();
               int length = a.length();
               if (!a.overlaps$mcJ$sp(b)) {
                  if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                     int index$macro$4 = 0;

                     for(int limit$macro$6 = length; index$macro$4 < limit$macro$6; ++index$macro$4) {
                        ad[index$macro$4] = bd[index$macro$4];
                     }

                     BoxedUnit var20 = BoxedUnit.UNIT;
                  } else if (astride == 1 && bstride == 1) {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = length; index$macro$9 < limit$macro$11; ++index$macro$9) {
                        ad[index$macro$9 + aoff] = bd[index$macro$9 + boff];
                     }

                     BoxedUnit var19 = BoxedUnit.UNIT;
                  } else {
                     int index$macro$14 = 0;

                     for(int limit$macro$16 = length; index$macro$14 < limit$macro$16; ++index$macro$14) {
                        ad[aoff + astride * index$macro$14] = bd[boff + bstride * index$macro$14];
                     }

                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  }

                  return;
               }

               b = b.copy$mcJ$sp();
               a = a;
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_V_Long_OpSet())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Int_OpMod_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final DenseVector b) {
            while(true) {
               int left$macro$1 = a.length();
               int right$macro$2 = b.length();
               if (left$macro$1 != right$macro$2) {
                  throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
               }

               int[] ad = a.data$mcI$sp();
               int[] bd = b.data$mcI$sp();
               int aoff = a.offset();
               int boff = b.offset();
               int astride = a.stride();
               int bstride = b.stride();
               int length = a.length();
               if (!a.overlaps$mcI$sp(b)) {
                  if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                     int index$macro$4 = 0;

                     for(int limit$macro$6 = length; index$macro$4 < limit$macro$6; ++index$macro$4) {
                        ad[index$macro$4] %= bd[index$macro$4];
                     }

                     BoxedUnit var20 = BoxedUnit.UNIT;
                  } else if (astride == 1 && bstride == 1) {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = length; index$macro$9 < limit$macro$11; ++index$macro$9) {
                        ad[index$macro$9 + aoff] %= bd[index$macro$9 + boff];
                     }

                     BoxedUnit var19 = BoxedUnit.UNIT;
                  } else {
                     int index$macro$14 = 0;

                     for(int limit$macro$16 = length; index$macro$14 < limit$macro$16; ++index$macro$14) {
                        ad[aoff + astride * index$macro$14] %= bd[boff + bstride * index$macro$14];
                     }

                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  }

                  return;
               }

               b = b.copy$mcI$sp();
               a = a;
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_V_Int_OpMod())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Double_OpMod_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final DenseVector b) {
            while(true) {
               int left$macro$1 = a.length();
               int right$macro$2 = b.length();
               if (left$macro$1 != right$macro$2) {
                  throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
               }

               double[] ad = a.data$mcD$sp();
               double[] bd = b.data$mcD$sp();
               int aoff = a.offset();
               int boff = b.offset();
               int astride = a.stride();
               int bstride = b.stride();
               int length = a.length();
               if (!a.overlaps$mcD$sp(b)) {
                  if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                     int index$macro$4 = 0;

                     for(int limit$macro$6 = length; index$macro$4 < limit$macro$6; ++index$macro$4) {
                        ad[index$macro$4] %= bd[index$macro$4];
                     }

                     BoxedUnit var20 = BoxedUnit.UNIT;
                  } else if (astride == 1 && bstride == 1) {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = length; index$macro$9 < limit$macro$11; ++index$macro$9) {
                        ad[index$macro$9 + aoff] %= bd[index$macro$9 + boff];
                     }

                     BoxedUnit var19 = BoxedUnit.UNIT;
                  } else {
                     int index$macro$14 = 0;

                     for(int limit$macro$16 = length; index$macro$14 < limit$macro$16; ++index$macro$14) {
                        ad[aoff + astride * index$macro$14] %= bd[boff + bstride * index$macro$14];
                     }

                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  }

                  return;
               }

               b = b.copy$mcD$sp();
               a = a;
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_V_Double_OpMod())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Float_OpMod_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final DenseVector b) {
            while(true) {
               int left$macro$1 = a.length();
               int right$macro$2 = b.length();
               if (left$macro$1 != right$macro$2) {
                  throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
               }

               float[] ad = a.data$mcF$sp();
               float[] bd = b.data$mcF$sp();
               int aoff = a.offset();
               int boff = b.offset();
               int astride = a.stride();
               int bstride = b.stride();
               int length = a.length();
               if (!a.overlaps$mcF$sp(b)) {
                  if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                     int index$macro$4 = 0;

                     for(int limit$macro$6 = length; index$macro$4 < limit$macro$6; ++index$macro$4) {
                        ad[index$macro$4] %= bd[index$macro$4];
                     }

                     BoxedUnit var20 = BoxedUnit.UNIT;
                  } else if (astride == 1 && bstride == 1) {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = length; index$macro$9 < limit$macro$11; ++index$macro$9) {
                        ad[index$macro$9 + aoff] %= bd[index$macro$9 + boff];
                     }

                     BoxedUnit var19 = BoxedUnit.UNIT;
                  } else {
                     int index$macro$14 = 0;

                     for(int limit$macro$16 = length; index$macro$14 < limit$macro$16; ++index$macro$14) {
                        ad[aoff + astride * index$macro$14] %= bd[boff + bstride * index$macro$14];
                     }

                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  }

                  return;
               }

               b = b.copy$mcF$sp();
               a = a;
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_V_Float_OpMod())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Long_OpMod_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final DenseVector b) {
            while(true) {
               int left$macro$1 = a.length();
               int right$macro$2 = b.length();
               if (left$macro$1 != right$macro$2) {
                  throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
               }

               long[] ad = a.data$mcJ$sp();
               long[] bd = b.data$mcJ$sp();
               int aoff = a.offset();
               int boff = b.offset();
               int astride = a.stride();
               int bstride = b.stride();
               int length = a.length();
               if (!a.overlaps$mcJ$sp(b)) {
                  if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                     int index$macro$4 = 0;

                     for(int limit$macro$6 = length; index$macro$4 < limit$macro$6; ++index$macro$4) {
                        ad[index$macro$4] %= bd[index$macro$4];
                     }

                     BoxedUnit var20 = BoxedUnit.UNIT;
                  } else if (astride == 1 && bstride == 1) {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = length; index$macro$9 < limit$macro$11; ++index$macro$9) {
                        ad[index$macro$9 + aoff] %= bd[index$macro$9 + boff];
                     }

                     BoxedUnit var19 = BoxedUnit.UNIT;
                  } else {
                     int index$macro$14 = 0;

                     for(int limit$macro$16 = length; index$macro$14 < limit$macro$16; ++index$macro$14) {
                        ad[aoff + astride * index$macro$14] %= bd[boff + bstride * index$macro$14];
                     }

                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  }

                  return;
               }

               b = b.copy$mcJ$sp();
               a = a;
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_V_Long_OpMod())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Int_OpPow_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final DenseVector b) {
            while(true) {
               int left$macro$1 = a.length();
               int right$macro$2 = b.length();
               if (left$macro$1 != right$macro$2) {
                  throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
               }

               int[] ad = a.data$mcI$sp();
               int[] bd = b.data$mcI$sp();
               int aoff = a.offset();
               int boff = b.offset();
               int astride = a.stride();
               int bstride = b.stride();
               int length = a.length();
               if (!a.overlaps$mcI$sp(b)) {
                  if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                     int index$macro$4 = 0;

                     for(int limit$macro$6 = length; index$macro$4 < limit$macro$6; ++index$macro$4) {
                        ad[index$macro$4] = PowImplicits$.MODULE$.IntPow(ad[index$macro$4]).pow(bd[index$macro$4]);
                     }

                     BoxedUnit var20 = BoxedUnit.UNIT;
                  } else if (astride == 1 && bstride == 1) {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = length; index$macro$9 < limit$macro$11; ++index$macro$9) {
                        ad[index$macro$9 + aoff] = PowImplicits$.MODULE$.IntPow(ad[index$macro$9 + aoff]).pow(bd[index$macro$9 + boff]);
                     }

                     BoxedUnit var19 = BoxedUnit.UNIT;
                  } else {
                     int index$macro$14 = 0;

                     for(int limit$macro$16 = length; index$macro$14 < limit$macro$16; ++index$macro$14) {
                        ad[aoff + astride * index$macro$14] = PowImplicits$.MODULE$.IntPow(ad[aoff + astride * index$macro$14]).pow(bd[boff + bstride * index$macro$14]);
                     }

                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  }

                  return;
               }

               b = b.copy$mcI$sp();
               a = a;
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_V_Int_OpPow())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Double_OpPow_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final DenseVector b) {
            while(true) {
               int left$macro$1 = a.length();
               int right$macro$2 = b.length();
               if (left$macro$1 != right$macro$2) {
                  throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
               }

               double[] ad = a.data$mcD$sp();
               double[] bd = b.data$mcD$sp();
               int aoff = a.offset();
               int boff = b.offset();
               int astride = a.stride();
               int bstride = b.stride();
               int length = a.length();
               if (!a.overlaps$mcD$sp(b)) {
                  if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                     int index$macro$4 = 0;

                     for(int limit$macro$6 = length; index$macro$4 < limit$macro$6; ++index$macro$4) {
                        ad[index$macro$4] = PowImplicits$.MODULE$.DoublePow(ad[index$macro$4]).pow(bd[index$macro$4]);
                     }

                     BoxedUnit var20 = BoxedUnit.UNIT;
                  } else if (astride == 1 && bstride == 1) {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = length; index$macro$9 < limit$macro$11; ++index$macro$9) {
                        ad[index$macro$9 + aoff] = PowImplicits$.MODULE$.DoublePow(ad[index$macro$9 + aoff]).pow(bd[index$macro$9 + boff]);
                     }

                     BoxedUnit var19 = BoxedUnit.UNIT;
                  } else {
                     int index$macro$14 = 0;

                     for(int limit$macro$16 = length; index$macro$14 < limit$macro$16; ++index$macro$14) {
                        ad[aoff + astride * index$macro$14] = PowImplicits$.MODULE$.DoublePow(ad[aoff + astride * index$macro$14]).pow(bd[boff + bstride * index$macro$14]);
                     }

                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  }

                  return;
               }

               b = b.copy$mcD$sp();
               a = a;
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_V_Double_OpPow())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Float_OpPow_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final DenseVector b) {
            while(true) {
               int left$macro$1 = a.length();
               int right$macro$2 = b.length();
               if (left$macro$1 != right$macro$2) {
                  throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
               }

               float[] ad = a.data$mcF$sp();
               float[] bd = b.data$mcF$sp();
               int aoff = a.offset();
               int boff = b.offset();
               int astride = a.stride();
               int bstride = b.stride();
               int length = a.length();
               if (!a.overlaps$mcF$sp(b)) {
                  if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                     int index$macro$4 = 0;

                     for(int limit$macro$6 = length; index$macro$4 < limit$macro$6; ++index$macro$4) {
                        ad[index$macro$4] = PowImplicits$.MODULE$.FloatPow(ad[index$macro$4]).pow(bd[index$macro$4]);
                     }

                     BoxedUnit var20 = BoxedUnit.UNIT;
                  } else if (astride == 1 && bstride == 1) {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = length; index$macro$9 < limit$macro$11; ++index$macro$9) {
                        ad[index$macro$9 + aoff] = PowImplicits$.MODULE$.FloatPow(ad[index$macro$9 + aoff]).pow(bd[index$macro$9 + boff]);
                     }

                     BoxedUnit var19 = BoxedUnit.UNIT;
                  } else {
                     int index$macro$14 = 0;

                     for(int limit$macro$16 = length; index$macro$14 < limit$macro$16; ++index$macro$14) {
                        ad[aoff + astride * index$macro$14] = PowImplicits$.MODULE$.FloatPow(ad[aoff + astride * index$macro$14]).pow(bd[boff + bstride * index$macro$14]);
                     }

                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  }

                  return;
               }

               b = b.copy$mcF$sp();
               a = a;
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_V_Float_OpPow())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_DV_Long_OpPow_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final DenseVector b) {
            while(true) {
               int left$macro$1 = a.length();
               int right$macro$2 = b.length();
               if (left$macro$1 != right$macro$2) {
                  throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: Lengths must match!: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
               }

               long[] ad = a.data$mcJ$sp();
               long[] bd = b.data$mcJ$sp();
               int aoff = a.offset();
               int boff = b.offset();
               int astride = a.stride();
               int bstride = b.stride();
               int length = a.length();
               if (!a.overlaps$mcJ$sp(b)) {
                  if (a.noOffsetOrStride() && b.noOffsetOrStride()) {
                     int index$macro$4 = 0;

                     for(int limit$macro$6 = length; index$macro$4 < limit$macro$6; ++index$macro$4) {
                        ad[index$macro$4] = PowImplicits$.MODULE$.LongPow(ad[index$macro$4]).pow(bd[index$macro$4]);
                     }

                     BoxedUnit var20 = BoxedUnit.UNIT;
                  } else if (astride == 1 && bstride == 1) {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = length; index$macro$9 < limit$macro$11; ++index$macro$9) {
                        ad[index$macro$9 + aoff] = PowImplicits$.MODULE$.LongPow(ad[index$macro$9 + aoff]).pow(bd[index$macro$9 + boff]);
                     }

                     BoxedUnit var19 = BoxedUnit.UNIT;
                  } else {
                     int index$macro$14 = 0;

                     for(int limit$macro$16 = length; index$macro$14 < limit$macro$16; ++index$macro$14) {
                        ad[aoff + astride * index$macro$14] = PowImplicits$.MODULE$.LongPow(ad[aoff + astride * index$macro$14]).pow(bd[boff + bstride * index$macro$14]);
                     }

                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  }

                  return;
               }

               b = b.copy$mcJ$sp();
               a = a;
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_V_Long_OpPow())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Int_OpAdd_$eq(new UFunc$InPlaceImpl2$mcI$sp() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final int b) {
            this.apply$mcI$sp(a, b);
         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$fastPath(final int b, final int[] ad, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2] += b;
            }

         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$medPath(final int[] ad, final int aoff, final int b, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2 + aoff] += b;
            }

         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$slowPath(final int[] ad, final int aoff, final int stride, final int b, final int length) {
            int j = aoff;
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[j] += b;
               j += stride;
            }

         }

         public void apply$mcI$sp(final DenseVector a, final int b) {
            int[] ad = a.data$mcI$sp();
            int aoff = a.offset();
            int stride = a.stride();
            int length = a.length();
            if (aoff == 0 && stride == 1) {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$fastPath(b, ad, length);
            } else if (stride == 1) {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$medPath(ad, aoff, b, length);
            } else {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$slowPath(ad, aoff, stride, b, length);
            }

         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_S_Int_OpAdd())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Int());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Double_OpAdd_$eq(new UFunc$InPlaceImpl2$mcD$sp() {
         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final double b) {
            this.apply$mcD$sp(a, b);
         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$fastPath(final double b, final double[] ad, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2] += b;
            }

         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$medPath(final double[] ad, final int aoff, final double b, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2 + aoff] += b;
            }

         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$slowPath(final double[] ad, final int aoff, final int stride, final double b, final int length) {
            int j = aoff;
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[j] += b;
               j += stride;
            }

         }

         public void apply$mcD$sp(final DenseVector a, final double b) {
            double[] ad = a.data$mcD$sp();
            int aoff = a.offset();
            int stride = a.stride();
            int length = a.length();
            if (aoff == 0 && stride == 1) {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$fastPath(b, ad, length);
            } else if (stride == 1) {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$medPath(ad, aoff, b, length);
            } else {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$slowPath(ad, aoff, stride, b, length);
            }

         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_S_Double_OpAdd())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Double());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Float_OpAdd_$eq(new UFunc$InPlaceImpl2$mcF$sp() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final float b) {
            this.apply$mcF$sp(a, b);
         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$fastPath(final float b, final float[] ad, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2] += b;
            }

         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$medPath(final float[] ad, final int aoff, final float b, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2 + aoff] += b;
            }

         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$slowPath(final float[] ad, final int aoff, final int stride, final float b, final int length) {
            int j = aoff;
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[j] += b;
               j += stride;
            }

         }

         public void apply$mcF$sp(final DenseVector a, final float b) {
            float[] ad = a.data$mcF$sp();
            int aoff = a.offset();
            int stride = a.stride();
            int length = a.length();
            if (aoff == 0 && stride == 1) {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$fastPath(b, ad, length);
            } else if (stride == 1) {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$medPath(ad, aoff, b, length);
            } else {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$slowPath(ad, aoff, stride, b, length);
            }

         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_S_Float_OpAdd())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Float());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Long_OpAdd_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final long b) {
            long[] ad = a.data$mcJ$sp();
            int aoff = a.offset();
            int stride = a.stride();
            int length = a.length();
            if (aoff == 0 && stride == 1) {
               this.fastPath(b, ad, length);
            } else if (stride == 1) {
               this.medPath(ad, aoff, b, length);
            } else {
               this.slowPath(ad, aoff, stride, b, length);
            }

         }

         private void fastPath(final long b, final long[] ad, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2] += b;
            }

         }

         private void medPath(final long[] ad, final int aoff, final long b, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2 + aoff] += b;
            }

         }

         private void slowPath(final long[] ad, final int aoff, final int stride, final long b, final int length) {
            int j = aoff;
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[j] += b;
               j += stride;
            }

         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_S_Long_OpAdd())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Long());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Int_OpSub_$eq(new UFunc$InPlaceImpl2$mcI$sp() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final int b) {
            this.apply$mcI$sp(a, b);
         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$fastPath(final int b, final int[] ad, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2] -= b;
            }

         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$medPath(final int[] ad, final int aoff, final int b, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2 + aoff] -= b;
            }

         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$slowPath(final int[] ad, final int aoff, final int stride, final int b, final int length) {
            int j = aoff;
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[j] -= b;
               j += stride;
            }

         }

         public void apply$mcI$sp(final DenseVector a, final int b) {
            int[] ad = a.data$mcI$sp();
            int aoff = a.offset();
            int stride = a.stride();
            int length = a.length();
            if (aoff == 0 && stride == 1) {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$fastPath(b, ad, length);
            } else if (stride == 1) {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$medPath(ad, aoff, b, length);
            } else {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$slowPath(ad, aoff, stride, b, length);
            }

         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_S_Int_OpSub())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Int());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Double_OpSub_$eq(new UFunc$InPlaceImpl2$mcD$sp() {
         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final double b) {
            this.apply$mcD$sp(a, b);
         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$fastPath(final double b, final double[] ad, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2] -= b;
            }

         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$medPath(final double[] ad, final int aoff, final double b, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2 + aoff] -= b;
            }

         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$slowPath(final double[] ad, final int aoff, final int stride, final double b, final int length) {
            int j = aoff;
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[j] -= b;
               j += stride;
            }

         }

         public void apply$mcD$sp(final DenseVector a, final double b) {
            double[] ad = a.data$mcD$sp();
            int aoff = a.offset();
            int stride = a.stride();
            int length = a.length();
            if (aoff == 0 && stride == 1) {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$fastPath(b, ad, length);
            } else if (stride == 1) {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$medPath(ad, aoff, b, length);
            } else {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$slowPath(ad, aoff, stride, b, length);
            }

         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_S_Double_OpSub())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Double());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Float_OpSub_$eq(new UFunc$InPlaceImpl2$mcF$sp() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final float b) {
            this.apply$mcF$sp(a, b);
         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$fastPath(final float b, final float[] ad, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2] -= b;
            }

         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$medPath(final float[] ad, final int aoff, final float b, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2 + aoff] -= b;
            }

         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$slowPath(final float[] ad, final int aoff, final int stride, final float b, final int length) {
            int j = aoff;
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[j] -= b;
               j += stride;
            }

         }

         public void apply$mcF$sp(final DenseVector a, final float b) {
            float[] ad = a.data$mcF$sp();
            int aoff = a.offset();
            int stride = a.stride();
            int length = a.length();
            if (aoff == 0 && stride == 1) {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$fastPath(b, ad, length);
            } else if (stride == 1) {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$medPath(ad, aoff, b, length);
            } else {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$slowPath(ad, aoff, stride, b, length);
            }

         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_S_Float_OpSub())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Float());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Long_OpSub_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final long b) {
            long[] ad = a.data$mcJ$sp();
            int aoff = a.offset();
            int stride = a.stride();
            int length = a.length();
            if (aoff == 0 && stride == 1) {
               this.fastPath(b, ad, length);
            } else if (stride == 1) {
               this.medPath(ad, aoff, b, length);
            } else {
               this.slowPath(ad, aoff, stride, b, length);
            }

         }

         private void fastPath(final long b, final long[] ad, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2] -= b;
            }

         }

         private void medPath(final long[] ad, final int aoff, final long b, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2 + aoff] -= b;
            }

         }

         private void slowPath(final long[] ad, final int aoff, final int stride, final long b, final int length) {
            int j = aoff;
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[j] -= b;
               j += stride;
            }

         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_S_Long_OpSub())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Long());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Int_OpMulScalar_$eq(new UFunc$InPlaceImpl2$mcI$sp() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final int b) {
            this.apply$mcI$sp(a, b);
         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$fastPath(final int b, final int[] ad, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2] *= b;
            }

         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$medPath(final int[] ad, final int aoff, final int b, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2 + aoff] *= b;
            }

         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$slowPath(final int[] ad, final int aoff, final int stride, final int b, final int length) {
            int j = aoff;
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[j] *= b;
               j += stride;
            }

         }

         public void apply$mcI$sp(final DenseVector a, final int b) {
            int[] ad = a.data$mcI$sp();
            int aoff = a.offset();
            int stride = a.stride();
            int length = a.length();
            if (aoff == 0 && stride == 1) {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$fastPath(b, ad, length);
            } else if (stride == 1) {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$medPath(ad, aoff, b, length);
            } else {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$slowPath(ad, aoff, stride, b, length);
            }

         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_S_Int_OpMulScalar())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Int());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Double_OpMulScalar_$eq(new UFunc$InPlaceImpl2$mcD$sp() {
         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final double b) {
            this.apply$mcD$sp(a, b);
         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$fastPath(final double b, final double[] ad, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2] *= b;
            }

         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$medPath(final double[] ad, final int aoff, final double b, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2 + aoff] *= b;
            }

         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$slowPath(final double[] ad, final int aoff, final int stride, final double b, final int length) {
            int j = aoff;
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[j] *= b;
               j += stride;
            }

         }

         public void apply$mcD$sp(final DenseVector a, final double b) {
            double[] ad = a.data$mcD$sp();
            int aoff = a.offset();
            int stride = a.stride();
            int length = a.length();
            if (aoff == 0 && stride == 1) {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$fastPath(b, ad, length);
            } else if (stride == 1) {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$medPath(ad, aoff, b, length);
            } else {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$slowPath(ad, aoff, stride, b, length);
            }

         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_S_Double_OpMulScalar())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Double());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Float_OpMulScalar_$eq(new UFunc$InPlaceImpl2$mcF$sp() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final float b) {
            this.apply$mcF$sp(a, b);
         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$fastPath(final float b, final float[] ad, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2] *= b;
            }

         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$medPath(final float[] ad, final int aoff, final float b, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2 + aoff] *= b;
            }

         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$slowPath(final float[] ad, final int aoff, final int stride, final float b, final int length) {
            int j = aoff;
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[j] *= b;
               j += stride;
            }

         }

         public void apply$mcF$sp(final DenseVector a, final float b) {
            float[] ad = a.data$mcF$sp();
            int aoff = a.offset();
            int stride = a.stride();
            int length = a.length();
            if (aoff == 0 && stride == 1) {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$fastPath(b, ad, length);
            } else if (stride == 1) {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$medPath(ad, aoff, b, length);
            } else {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$slowPath(ad, aoff, stride, b, length);
            }

         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_S_Float_OpMulScalar())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Float());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Long_OpMulScalar_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final long b) {
            long[] ad = a.data$mcJ$sp();
            int aoff = a.offset();
            int stride = a.stride();
            int length = a.length();
            if (aoff == 0 && stride == 1) {
               this.fastPath(b, ad, length);
            } else if (stride == 1) {
               this.medPath(ad, aoff, b, length);
            } else {
               this.slowPath(ad, aoff, stride, b, length);
            }

         }

         private void fastPath(final long b, final long[] ad, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2] *= b;
            }

         }

         private void medPath(final long[] ad, final int aoff, final long b, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2 + aoff] *= b;
            }

         }

         private void slowPath(final long[] ad, final int aoff, final int stride, final long b, final int length) {
            int j = aoff;
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[j] *= b;
               j += stride;
            }

         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_S_Long_OpMulScalar())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Long());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Int_OpMulMatrix_$eq(new UFunc$InPlaceImpl2$mcI$sp() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final int b) {
            this.apply$mcI$sp(a, b);
         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$fastPath(final int b, final int[] ad, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2] *= b;
            }

         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$medPath(final int[] ad, final int aoff, final int b, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2 + aoff] *= b;
            }

         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$slowPath(final int[] ad, final int aoff, final int stride, final int b, final int length) {
            int j = aoff;
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[j] *= b;
               j += stride;
            }

         }

         public void apply$mcI$sp(final DenseVector a, final int b) {
            int[] ad = a.data$mcI$sp();
            int aoff = a.offset();
            int stride = a.stride();
            int length = a.length();
            if (aoff == 0 && stride == 1) {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$fastPath(b, ad, length);
            } else if (stride == 1) {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$medPath(ad, aoff, b, length);
            } else {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$slowPath(ad, aoff, stride, b, length);
            }

         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_S_Int_OpMulMatrix())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Int());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Double_OpMulMatrix_$eq(new UFunc$InPlaceImpl2$mcD$sp() {
         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final double b) {
            this.apply$mcD$sp(a, b);
         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$fastPath(final double b, final double[] ad, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2] *= b;
            }

         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$medPath(final double[] ad, final int aoff, final double b, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2 + aoff] *= b;
            }

         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$slowPath(final double[] ad, final int aoff, final int stride, final double b, final int length) {
            int j = aoff;
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[j] *= b;
               j += stride;
            }

         }

         public void apply$mcD$sp(final DenseVector a, final double b) {
            double[] ad = a.data$mcD$sp();
            int aoff = a.offset();
            int stride = a.stride();
            int length = a.length();
            if (aoff == 0 && stride == 1) {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$fastPath(b, ad, length);
            } else if (stride == 1) {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$medPath(ad, aoff, b, length);
            } else {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$slowPath(ad, aoff, stride, b, length);
            }

         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_S_Double_OpMulMatrix())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Double());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Float_OpMulMatrix_$eq(new UFunc$InPlaceImpl2$mcF$sp() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final float b) {
            this.apply$mcF$sp(a, b);
         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$fastPath(final float b, final float[] ad, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2] *= b;
            }

         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$medPath(final float[] ad, final int aoff, final float b, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2 + aoff] *= b;
            }

         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$slowPath(final float[] ad, final int aoff, final int stride, final float b, final int length) {
            int j = aoff;
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[j] *= b;
               j += stride;
            }

         }

         public void apply$mcF$sp(final DenseVector a, final float b) {
            float[] ad = a.data$mcF$sp();
            int aoff = a.offset();
            int stride = a.stride();
            int length = a.length();
            if (aoff == 0 && stride == 1) {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$fastPath(b, ad, length);
            } else if (stride == 1) {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$medPath(ad, aoff, b, length);
            } else {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$slowPath(ad, aoff, stride, b, length);
            }

         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_S_Float_OpMulMatrix())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Float());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Long_OpMulMatrix_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final long b) {
            long[] ad = a.data$mcJ$sp();
            int aoff = a.offset();
            int stride = a.stride();
            int length = a.length();
            if (aoff == 0 && stride == 1) {
               this.fastPath(b, ad, length);
            } else if (stride == 1) {
               this.medPath(ad, aoff, b, length);
            } else {
               this.slowPath(ad, aoff, stride, b, length);
            }

         }

         private void fastPath(final long b, final long[] ad, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2] *= b;
            }

         }

         private void medPath(final long[] ad, final int aoff, final long b, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2 + aoff] *= b;
            }

         }

         private void slowPath(final long[] ad, final int aoff, final int stride, final long b, final int length) {
            int j = aoff;
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[j] *= b;
               j += stride;
            }

         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_S_Long_OpMulMatrix())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Long());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Int_OpDiv_$eq(new UFunc$InPlaceImpl2$mcI$sp() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final int b) {
            this.apply$mcI$sp(a, b);
         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$fastPath(final int b, final int[] ad, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2] /= b;
            }

         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$medPath(final int[] ad, final int aoff, final int b, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2 + aoff] /= b;
            }

         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$slowPath(final int[] ad, final int aoff, final int stride, final int b, final int length) {
            int j = aoff;
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[j] /= b;
               j += stride;
            }

         }

         public void apply$mcI$sp(final DenseVector a, final int b) {
            int[] ad = a.data$mcI$sp();
            int aoff = a.offset();
            int stride = a.stride();
            int length = a.length();
            if (aoff == 0 && stride == 1) {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$fastPath(b, ad, length);
            } else if (stride == 1) {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$medPath(ad, aoff, b, length);
            } else {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$slowPath(ad, aoff, stride, b, length);
            }

         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_S_Int_OpDiv())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Int());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Double_OpDiv_$eq(new UFunc$InPlaceImpl2$mcD$sp() {
         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final double b) {
            this.apply$mcD$sp(a, b);
         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$fastPath(final double b, final double[] ad, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2] /= b;
            }

         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$medPath(final double[] ad, final int aoff, final double b, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2 + aoff] /= b;
            }

         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$slowPath(final double[] ad, final int aoff, final int stride, final double b, final int length) {
            int j = aoff;
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[j] /= b;
               j += stride;
            }

         }

         public void apply$mcD$sp(final DenseVector a, final double b) {
            double[] ad = a.data$mcD$sp();
            int aoff = a.offset();
            int stride = a.stride();
            int length = a.length();
            if (aoff == 0 && stride == 1) {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$fastPath(b, ad, length);
            } else if (stride == 1) {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$medPath(ad, aoff, b, length);
            } else {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$slowPath(ad, aoff, stride, b, length);
            }

         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_S_Double_OpDiv())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Double());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Float_OpDiv_$eq(new UFunc$InPlaceImpl2$mcF$sp() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final float b) {
            this.apply$mcF$sp(a, b);
         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$fastPath(final float b, final float[] ad, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2] /= b;
            }

         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$medPath(final float[] ad, final int aoff, final float b, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2 + aoff] /= b;
            }

         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$slowPath(final float[] ad, final int aoff, final int stride, final float b, final int length) {
            int j = aoff;
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[j] /= b;
               j += stride;
            }

         }

         public void apply$mcF$sp(final DenseVector a, final float b) {
            float[] ad = a.data$mcF$sp();
            int aoff = a.offset();
            int stride = a.stride();
            int length = a.length();
            if (aoff == 0 && stride == 1) {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$fastPath(b, ad, length);
            } else if (stride == 1) {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$medPath(ad, aoff, b, length);
            } else {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$slowPath(ad, aoff, stride, b, length);
            }

         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_S_Float_OpDiv())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Float());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Long_OpDiv_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final long b) {
            long[] ad = a.data$mcJ$sp();
            int aoff = a.offset();
            int stride = a.stride();
            int length = a.length();
            if (aoff == 0 && stride == 1) {
               this.fastPath(b, ad, length);
            } else if (stride == 1) {
               this.medPath(ad, aoff, b, length);
            } else {
               this.slowPath(ad, aoff, stride, b, length);
            }

         }

         private void fastPath(final long b, final long[] ad, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2] /= b;
            }

         }

         private void medPath(final long[] ad, final int aoff, final long b, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2 + aoff] /= b;
            }

         }

         private void slowPath(final long[] ad, final int aoff, final int stride, final long b, final int length) {
            int j = aoff;
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[j] /= b;
               j += stride;
            }

         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_S_Long_OpDiv())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Long());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Int_OpSet_$eq(new UFunc$InPlaceImpl2$mcI$sp() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final int b) {
            this.apply$mcI$sp(a, b);
         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$fastPath(final int b, final int[] ad, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2] = b;
            }

         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$medPath(final int[] ad, final int aoff, final int b, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2 + aoff] = b;
            }

         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$slowPath(final int[] ad, final int aoff, final int stride, final int b, final int length) {
            int j = aoff;
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[j] = b;
               j += stride;
            }

         }

         public void apply$mcI$sp(final DenseVector a, final int b) {
            int[] ad = a.data$mcI$sp();
            int aoff = a.offset();
            int stride = a.stride();
            int length = a.length();
            if (aoff == 0 && stride == 1) {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$fastPath(b, ad, length);
            } else if (stride == 1) {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$medPath(ad, aoff, b, length);
            } else {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$slowPath(ad, aoff, stride, b, length);
            }

         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_S_Int_OpSet())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Int());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Double_OpSet_$eq(new UFunc$InPlaceImpl2$mcD$sp() {
         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final double b) {
            this.apply$mcD$sp(a, b);
         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$fastPath(final double b, final double[] ad, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2] = b;
            }

         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$medPath(final double[] ad, final int aoff, final double b, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2 + aoff] = b;
            }

         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$slowPath(final double[] ad, final int aoff, final int stride, final double b, final int length) {
            int j = aoff;
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[j] = b;
               j += stride;
            }

         }

         public void apply$mcD$sp(final DenseVector a, final double b) {
            double[] ad = a.data$mcD$sp();
            int aoff = a.offset();
            int stride = a.stride();
            int length = a.length();
            if (aoff == 0 && stride == 1) {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$fastPath(b, ad, length);
            } else if (stride == 1) {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$medPath(ad, aoff, b, length);
            } else {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$slowPath(ad, aoff, stride, b, length);
            }

         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_S_Double_OpSet())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Double());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Float_OpSet_$eq(new UFunc$InPlaceImpl2$mcF$sp() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final float b) {
            this.apply$mcF$sp(a, b);
         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$fastPath(final float b, final float[] ad, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2] = b;
            }

         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$medPath(final float[] ad, final int aoff, final float b, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2 + aoff] = b;
            }

         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$slowPath(final float[] ad, final int aoff, final int stride, final float b, final int length) {
            int j = aoff;
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[j] = b;
               j += stride;
            }

         }

         public void apply$mcF$sp(final DenseVector a, final float b) {
            float[] ad = a.data$mcF$sp();
            int aoff = a.offset();
            int stride = a.stride();
            int length = a.length();
            if (aoff == 0 && stride == 1) {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$fastPath(b, ad, length);
            } else if (stride == 1) {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$medPath(ad, aoff, b, length);
            } else {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$slowPath(ad, aoff, stride, b, length);
            }

         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_S_Float_OpSet())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Float());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Long_OpSet_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final long b) {
            long[] ad = a.data$mcJ$sp();
            int aoff = a.offset();
            int stride = a.stride();
            int length = a.length();
            if (aoff == 0 && stride == 1) {
               this.fastPath(b, ad, length);
            } else if (stride == 1) {
               this.medPath(ad, aoff, b, length);
            } else {
               this.slowPath(ad, aoff, stride, b, length);
            }

         }

         private void fastPath(final long b, final long[] ad, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2] = b;
            }

         }

         private void medPath(final long[] ad, final int aoff, final long b, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2 + aoff] = b;
            }

         }

         private void slowPath(final long[] ad, final int aoff, final int stride, final long b, final int length) {
            int j = aoff;
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[j] = b;
               j += stride;
            }

         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_S_Long_OpSet())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Long());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Int_OpMod_$eq(new UFunc$InPlaceImpl2$mcI$sp() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final int b) {
            this.apply$mcI$sp(a, b);
         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$fastPath(final int b, final int[] ad, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2] %= b;
            }

         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$medPath(final int[] ad, final int aoff, final int b, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2 + aoff] %= b;
            }

         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$slowPath(final int[] ad, final int aoff, final int stride, final int b, final int length) {
            int j = aoff;
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[j] %= b;
               j += stride;
            }

         }

         public void apply$mcI$sp(final DenseVector a, final int b) {
            int[] ad = a.data$mcI$sp();
            int aoff = a.offset();
            int stride = a.stride();
            int length = a.length();
            if (aoff == 0 && stride == 1) {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$fastPath(b, ad, length);
            } else if (stride == 1) {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$medPath(ad, aoff, b, length);
            } else {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$slowPath(ad, aoff, stride, b, length);
            }

         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_S_Int_OpMod())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Int());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Double_OpMod_$eq(new UFunc$InPlaceImpl2$mcD$sp() {
         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final double b) {
            this.apply$mcD$sp(a, b);
         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$fastPath(final double b, final double[] ad, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2] %= b;
            }

         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$medPath(final double[] ad, final int aoff, final double b, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2 + aoff] %= b;
            }

         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$slowPath(final double[] ad, final int aoff, final int stride, final double b, final int length) {
            int j = aoff;
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[j] %= b;
               j += stride;
            }

         }

         public void apply$mcD$sp(final DenseVector a, final double b) {
            double[] ad = a.data$mcD$sp();
            int aoff = a.offset();
            int stride = a.stride();
            int length = a.length();
            if (aoff == 0 && stride == 1) {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$fastPath(b, ad, length);
            } else if (stride == 1) {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$medPath(ad, aoff, b, length);
            } else {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$slowPath(ad, aoff, stride, b, length);
            }

         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_S_Double_OpMod())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Double());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Float_OpMod_$eq(new UFunc$InPlaceImpl2$mcF$sp() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final float b) {
            this.apply$mcF$sp(a, b);
         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$fastPath(final float b, final float[] ad, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2] %= b;
            }

         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$medPath(final float[] ad, final int aoff, final float b, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2 + aoff] %= b;
            }

         }

         public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$slowPath(final float[] ad, final int aoff, final int stride, final float b, final int length) {
            int j = aoff;
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[j] %= b;
               j += stride;
            }

         }

         public void apply$mcF$sp(final DenseVector a, final float b) {
            float[] ad = a.data$mcF$sp();
            int aoff = a.offset();
            int stride = a.stride();
            int length = a.length();
            if (aoff == 0 && stride == 1) {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$fastPath(b, ad, length);
            } else if (stride == 1) {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$medPath(ad, aoff, b, length);
            } else {
               this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$slowPath(ad, aoff, stride, b, length);
            }

         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_S_Float_OpMod())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Float());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_Op_InPlace_DV_S_Long_OpMod_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final long b) {
            long[] ad = a.data$mcJ$sp();
            int aoff = a.offset();
            int stride = a.stride();
            int length = a.length();
            if (aoff == 0 && stride == 1) {
               this.fastPath(b, ad, length);
            } else if (stride == 1) {
               this.medPath(ad, aoff, b, length);
            } else {
               this.slowPath(ad, aoff, stride, b, length);
            }

         }

         private void fastPath(final long b, final long[] ad, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2] %= b;
            }

         }

         private void medPath(final long[] ad, final int aoff, final long b, final int length) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[index$macro$2 + aoff] %= b;
            }

         }

         private void slowPath(final long[] ad, final int aoff, final int stride, final long b, final int length) {
            int j = aoff;
            int index$macro$2 = 0;

            for(int limit$macro$4 = length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[j] %= b;
               j += stride;
            }

         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_Op_InPlace_V_S_Long_OpMod())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Long());
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_OpMulInner_DV_DV_eq_S_Int_$eq(new UFunc.UImpl2() {
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

         public int apply(final DenseVector a, final DenseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int[] ad = a.data$mcI$sp();
               int[] bd = b.data$mcI$sp();
               int aoff = a.offset();
               int boff = b.offset();
               int result = 0;
               int index$macro$4 = 0;

               for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  result += ad[aoff] * bd[boff];
                  aoff += a.stride();
                  boff += b.stride();
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_OpMulInner_V_V_eq_S_Int())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_OpMulInner_DV_DV_eq_S_Long_$eq(new UFunc.UImpl2() {
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

         public long apply(final DenseVector a, final DenseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               long[] ad = a.data$mcJ$sp();
               long[] bd = b.data$mcJ$sp();
               int aoff = a.offset();
               int boff = b.offset();
               long result = 0L;
               int index$macro$4 = 0;

               for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  result += ad[aoff] * bd[boff];
                  aoff += a.stride();
                  boff += b.stride();
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_OpMulInner_V_V_eq_S_Long())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      UFunc.UImpl2 res = new UFunc.UImpl2() {
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

         public ZippedValues apply(final DenseVector v1, final DenseVector v2) {
            int left$macro$1 = v2.length();
            int right$macro$2 = v1.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: vector length mismatch: ").append("v2.length == v1.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int n = v1.length();
               return new ZippedValues(v1, v2, n) {
                  private final DenseVector v1$1;
                  private final DenseVector v2$1;
                  private final int n$1;

                  public void foreach$mcDD$sp(final Function2 f) {
                     ZippedValues.foreach$mcDD$sp$(this, f);
                  }

                  public boolean exists(final Function2 f) {
                     return ZippedValues.exists$(this, f);
                  }

                  public boolean exists$mcDD$sp(final Function2 f) {
                     return ZippedValues.exists$mcDD$sp$(this, f);
                  }

                  public boolean forall(final Function2 f) {
                     return ZippedValues.forall$(this, f);
                  }

                  public boolean forall$mcDD$sp(final Function2 f) {
                     return ZippedValues.forall$mcDD$sp$(this, f);
                  }

                  public void foreach(final Function2 fn) {
                     if (this.v1$1.stride() == 1 && this.v2$1.stride() == 1) {
                        int[] data1 = this.v1$1.data$mcI$sp();
                        int offset1 = this.v1$1.offset();
                        int[] data2 = this.v2$1.data$mcI$sp();
                        int offset2 = this.v2$1.offset();
                        int index$macro$2 = 0;

                        for(int limit$macro$4 = this.v1$1.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                           fn.apply$mcVII$sp(data1[offset1 + index$macro$2], data2[offset2 + index$macro$2]);
                        }
                     } else {
                        this.slowPath(fn);
                     }

                  }

                  private void slowPath(final Function2 fn) {
                     int[] data1 = this.v1$1.data$mcI$sp();
                     int stride1 = this.v1$1.stride();
                     int offset1 = this.v1$1.offset();
                     int[] data2 = this.v2$1.data$mcI$sp();
                     int stride2 = this.v2$1.stride();
                     int offset2 = this.v2$1.offset();
                     int index$macro$2 = 0;

                     for(int limit$macro$4 = this.n$1; index$macro$2 < limit$macro$4; ++index$macro$2) {
                        fn.apply$mcVII$sp(data1[offset1], data2[offset2]);
                        offset1 += stride1;
                        offset2 += stride2;
                     }

                  }

                  public {
                     this.v1$1 = v1$1;
                     this.v2$1 = v2$1;
                     this.n$1 = n$1;
                     ZippedValues.$init$(this);
                  }
               };
            }
         }
      };
      ((BinaryRegistry)scala.Predef..MODULE$.implicitly($this.zipValuesImpl_V_V_Int())).register(res, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_zipValues_DV_DV_Int_$eq(res);
      UFunc.UImpl2 res = new UFunc.UImpl2() {
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

         public ZippedValues apply(final DenseVector v1, final DenseVector v2) {
            int left$macro$1 = v2.length();
            int right$macro$2 = v1.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: vector length mismatch: ").append("v2.length == v1.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int n = v1.length();
               return new ZippedValues$mcDD$sp(v1, v2, n) {
                  private final DenseVector v1$2;
                  private final DenseVector v2$2;
                  private final int n$2;

                  public boolean exists(final Function2 f) {
                     return ZippedValues$mcDD$sp.exists$(this, f);
                  }

                  public boolean exists$mcDD$sp(final Function2 f) {
                     return ZippedValues$mcDD$sp.exists$mcDD$sp$(this, f);
                  }

                  public boolean forall(final Function2 f) {
                     return ZippedValues$mcDD$sp.forall$(this, f);
                  }

                  public boolean forall$mcDD$sp(final Function2 f) {
                     return ZippedValues$mcDD$sp.forall$mcDD$sp$(this, f);
                  }

                  public void foreach(final Function2 fn) {
                     this.foreach$mcDD$sp(fn);
                  }

                  public void breeze$linalg$operators$DenseVectorExpandOps$$anon$$anon$$slowPath(final Function2 fn) {
                     double[] data1 = this.v1$2.data$mcD$sp();
                     int stride1 = this.v1$2.stride();
                     int offset1 = this.v1$2.offset();
                     double[] data2 = this.v2$2.data$mcD$sp();
                     int stride2 = this.v2$2.stride();
                     int offset2 = this.v2$2.offset();
                     int index$macro$2 = 0;

                     for(int limit$macro$4 = this.n$2; index$macro$2 < limit$macro$4; ++index$macro$2) {
                        fn.apply$mcVDD$sp(data1[offset1], data2[offset2]);
                        offset1 += stride1;
                        offset2 += stride2;
                     }

                  }

                  public void foreach$mcDD$sp(final Function2 fn) {
                     if (this.v1$2.stride() == 1 && this.v2$2.stride() == 1) {
                        double[] data1 = this.v1$2.data$mcD$sp();
                        int offset1 = this.v1$2.offset();
                        double[] data2 = this.v2$2.data$mcD$sp();
                        int offset2 = this.v2$2.offset();
                        int index$macro$2 = 0;

                        for(int limit$macro$4 = this.v1$2.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                           fn.apply$mcVDD$sp(data1[offset1 + index$macro$2], data2[offset2 + index$macro$2]);
                        }
                     } else {
                        this.breeze$linalg$operators$DenseVectorExpandOps$$anon$$anon$$slowPath(fn);
                     }

                  }

                  public {
                     this.v1$2 = v1$2;
                     this.v2$2 = v2$2;
                     this.n$2 = n$2;
                     ZippedValues.$init$(this);
                  }
               };
            }
         }
      };
      ((BinaryRegistry)scala.Predef..MODULE$.implicitly($this.zipValuesImpl_V_V_Double())).register(res, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_zipValues_DV_DV_Double_$eq(res);
      UFunc.UImpl2 res = new UFunc.UImpl2() {
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

         public ZippedValues apply(final DenseVector v1, final DenseVector v2) {
            int left$macro$1 = v2.length();
            int right$macro$2 = v1.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: vector length mismatch: ").append("v2.length == v1.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int n = v1.length();
               return new ZippedValues(v1, v2, n) {
                  private final DenseVector v1$3;
                  private final DenseVector v2$3;
                  private final int n$3;

                  public void foreach$mcDD$sp(final Function2 f) {
                     ZippedValues.foreach$mcDD$sp$(this, f);
                  }

                  public boolean exists(final Function2 f) {
                     return ZippedValues.exists$(this, f);
                  }

                  public boolean exists$mcDD$sp(final Function2 f) {
                     return ZippedValues.exists$mcDD$sp$(this, f);
                  }

                  public boolean forall(final Function2 f) {
                     return ZippedValues.forall$(this, f);
                  }

                  public boolean forall$mcDD$sp(final Function2 f) {
                     return ZippedValues.forall$mcDD$sp$(this, f);
                  }

                  public void foreach(final Function2 fn) {
                     if (this.v1$3.stride() == 1 && this.v2$3.stride() == 1) {
                        float[] data1 = this.v1$3.data$mcF$sp();
                        int offset1 = this.v1$3.offset();
                        float[] data2 = this.v2$3.data$mcF$sp();
                        int offset2 = this.v2$3.offset();
                        int index$macro$2 = 0;

                        for(int limit$macro$4 = this.v1$3.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                           fn.apply(BoxesRunTime.boxToFloat(data1[offset1 + index$macro$2]), BoxesRunTime.boxToFloat(data2[offset2 + index$macro$2]));
                        }
                     } else {
                        this.slowPath(fn);
                     }

                  }

                  private void slowPath(final Function2 fn) {
                     float[] data1 = this.v1$3.data$mcF$sp();
                     int stride1 = this.v1$3.stride();
                     int offset1 = this.v1$3.offset();
                     float[] data2 = this.v2$3.data$mcF$sp();
                     int stride2 = this.v2$3.stride();
                     int offset2 = this.v2$3.offset();
                     int index$macro$2 = 0;

                     for(int limit$macro$4 = this.n$3; index$macro$2 < limit$macro$4; ++index$macro$2) {
                        fn.apply(BoxesRunTime.boxToFloat(data1[offset1]), BoxesRunTime.boxToFloat(data2[offset2]));
                        offset1 += stride1;
                        offset2 += stride2;
                     }

                  }

                  public {
                     this.v1$3 = v1$3;
                     this.v2$3 = v2$3;
                     this.n$3 = n$3;
                     ZippedValues.$init$(this);
                  }
               };
            }
         }
      };
      ((BinaryRegistry)scala.Predef..MODULE$.implicitly($this.zipValuesImpl_V_V_Float())).register(res, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_zipValues_DV_DV_Float_$eq(res);
      UFunc.UImpl2 res = new UFunc.UImpl2() {
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

         public ZippedValues apply(final DenseVector v1, final DenseVector v2) {
            int left$macro$1 = v2.length();
            int right$macro$2 = v1.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: vector length mismatch: ").append("v2.length == v1.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int n = v1.length();
               return new ZippedValues(v1, v2, n) {
                  private final DenseVector v1$4;
                  private final DenseVector v2$4;
                  private final int n$4;

                  public void foreach$mcDD$sp(final Function2 f) {
                     ZippedValues.foreach$mcDD$sp$(this, f);
                  }

                  public boolean exists(final Function2 f) {
                     return ZippedValues.exists$(this, f);
                  }

                  public boolean exists$mcDD$sp(final Function2 f) {
                     return ZippedValues.exists$mcDD$sp$(this, f);
                  }

                  public boolean forall(final Function2 f) {
                     return ZippedValues.forall$(this, f);
                  }

                  public boolean forall$mcDD$sp(final Function2 f) {
                     return ZippedValues.forall$mcDD$sp$(this, f);
                  }

                  public void foreach(final Function2 fn) {
                     if (this.v1$4.stride() == 1 && this.v2$4.stride() == 1) {
                        long[] data1 = this.v1$4.data$mcJ$sp();
                        int offset1 = this.v1$4.offset();
                        long[] data2 = this.v2$4.data$mcJ$sp();
                        int offset2 = this.v2$4.offset();
                        int index$macro$2 = 0;

                        for(int limit$macro$4 = this.v1$4.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                           fn.apply$mcVJJ$sp(data1[offset1 + index$macro$2], data2[offset2 + index$macro$2]);
                        }
                     } else {
                        this.slowPath(fn);
                     }

                  }

                  private void slowPath(final Function2 fn) {
                     long[] data1 = this.v1$4.data$mcJ$sp();
                     int stride1 = this.v1$4.stride();
                     int offset1 = this.v1$4.offset();
                     long[] data2 = this.v2$4.data$mcJ$sp();
                     int stride2 = this.v2$4.stride();
                     int offset2 = this.v2$4.offset();
                     int index$macro$2 = 0;

                     for(int limit$macro$4 = this.n$4; index$macro$2 < limit$macro$4; ++index$macro$2) {
                        fn.apply$mcVJJ$sp(data1[offset1], data2[offset2]);
                        offset1 += stride1;
                        offset2 += stride2;
                     }

                  }

                  public {
                     this.v1$4 = v1$4;
                     this.v2$4 = v2$4;
                     this.n$4 = n$4;
                     ZippedValues.$init$(this);
                  }
               };
            }
         }
      };
      ((BinaryRegistry)scala.Predef..MODULE$.implicitly($this.zipValuesImpl_V_V_Long())).register(res, .MODULE$.apply(DenseVector.class), .MODULE$.apply(DenseVector.class));
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_zipValues_DV_DV_Long_$eq(res);
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_scaleAdd_InPlace_DV_S_DV_Int_$eq(new UFunc.InPlaceImpl3() {
         public void apply(final DenseVector y, final int s, final DenseVector x) {
            while(true) {
               int left$macro$1 = x.length();
               int right$macro$2 = y.length();
               if (left$macro$1 != right$macro$2) {
                  throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("x.length == y.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
               }

               if (!y.overlaps$mcI$sp(x)) {
                  if (x.noOffsetOrStride() && y.noOffsetOrStride()) {
                     int[] ad = x.data$mcI$sp();
                     int[] bd = y.data$mcI$sp();
                     int index$macro$4 = 0;

                     for(int limit$macro$6 = x.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                        bd[index$macro$4] += ad[index$macro$4] * s;
                     }

                     BoxedUnit var13 = BoxedUnit.UNIT;
                  } else {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = x.length(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                        y.update$mcI$sp(index$macro$9, y.apply$mcI$sp(index$macro$9) + x.apply$mcI$sp(index$macro$9) * s);
                     }

                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  }

                  return;
               }

               x = x.copy$mcI$sp();
               s = s;
               y = y;
            }
         }

         public {
            ((TernaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_scaleAdd_InPlace_V_S_V_Int())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Int(), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVectorExpandOps$_setter_$impl_scaleAdd_InPlace_DV_S_DV_Long_$eq(new UFunc.InPlaceImpl3() {
         public void apply(final DenseVector y, final long s, final DenseVector x) {
            while(true) {
               int left$macro$1 = x.length();
               int right$macro$2 = y.length();
               if (left$macro$1 != right$macro$2) {
                  throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("x.length == y.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
               }

               if (!y.overlaps$mcJ$sp(x)) {
                  if (x.noOffsetOrStride() && y.noOffsetOrStride()) {
                     long[] ad = x.data$mcJ$sp();
                     long[] bd = y.data$mcJ$sp();
                     int index$macro$4 = 0;

                     for(int limit$macro$6 = x.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                        bd[index$macro$4] += ad[index$macro$4] * s;
                     }

                     BoxedUnit var14 = BoxedUnit.UNIT;
                  } else {
                     int index$macro$9 = 0;

                     for(int limit$macro$11 = x.length(); index$macro$9 < limit$macro$11; ++index$macro$9) {
                        y.update$mcJ$sp(index$macro$9, y.apply$mcJ$sp(index$macro$9) + x.apply$mcJ$sp(index$macro$9) * s);
                     }

                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  }

                  return;
               }

               x = x.copy$mcJ$sp();
               s = s;
               y = y;
            }
         }

         public {
            ((TernaryUpdateRegistry)scala.Predef..MODULE$.implicitly(DenseVectorExpandOps.this.impl_scaleAdd_InPlace_V_S_V_Long())).register(this, .MODULE$.apply(DenseVector.class), .MODULE$.Long(), .MODULE$.apply(DenseVector.class));
         }
      });
   }
}
