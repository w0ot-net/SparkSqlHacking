package breeze.linalg.operators;

import breeze.generic.UFunc;
import breeze.linalg.CSCMatrix;
import breeze.linalg.CSCMatrix$Builder$mcD$sp;
import breeze.linalg.CSCMatrix$Builder$mcF$sp;
import breeze.linalg.CSCMatrix$Builder$mcI$sp;
import breeze.linalg.CSCMatrix$Builder$mcJ$sp;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.SparseVector;
import breeze.linalg.Transpose;
import breeze.linalg.Vector;
import breeze.linalg.VectorBuilder;
import breeze.linalg.VectorBuilder$;
import breeze.linalg.VectorBuilder$mcD$sp;
import breeze.linalg.VectorBuilder$mcF$sp;
import breeze.linalg.VectorBuilder$mcI$sp;
import breeze.linalg.VectorBuilder$mcJ$sp;
import breeze.math.Complex;
import breeze.math.Complex$;
import breeze.math.PowImplicits$;
import breeze.math.Semiring$;
import breeze.storage.Zero$;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\t}haB\"E!\u0003\r\ta\u0013\u0005\u00069\u0002!\t!\u0018\u0005\bC\u0002\u0011\r\u0011b\u0001c\u0011\u001d9\bA1A\u0005\u0004aD\u0001b \u0001C\u0002\u0013\r\u0011\u0011\u0001\u0005\n\u0003\u001f\u0001!\u0019!C\u0002\u0003#A\u0011\"a\b\u0001\u0005\u0004%\u0019!!\t\t\u0013\u0005-\u0002A1A\u0005\u0004\u00055\u0002\"CA\u0019\u0001\t\u0007I1AA\u001a\u0011%\t9\u0004\u0001b\u0001\n\u0007\tI\u0004C\u0005\u0002>\u0001\u0011\r\u0011b\u0001\u0002@!I\u0011\u0011\n\u0001C\u0002\u0013\r\u00111\n\u0005\n\u0003\u001f\u0002!\u0019!C\u0002\u0003#B\u0011\"!\u0016\u0001\u0005\u0004%\u0019!a\u0016\t\u0013\u0005m\u0003A1A\u0005\u0004\u0005u\u0003\"CA4\u0001\t\u0007I1AA5\u0011%\ti\u0007\u0001b\u0001\n\u0007\ty\u0007C\u0005\u0002t\u0001\u0011\r\u0011b\u0001\u0002v!I\u0011\u0011\u0010\u0001C\u0002\u0013\r\u00111\u0010\u0005\n\u0003\u000b\u0003!\u0019!C\u0002\u0003\u000fC\u0011\"a#\u0001\u0005\u0004%\u0019!!$\t\u0013\u0005E\u0005A1A\u0005\u0004\u0005M\u0005\"CAL\u0001\t\u0007I1AAM\u0011%\t\u0019\u000b\u0001b\u0001\n\u0007\t)\u000bC\u0005\u0002*\u0002\u0011\r\u0011b\u0001\u0002,\"I\u0011q\u0016\u0001C\u0002\u0013\r\u0011\u0011\u0017\u0005\n\u0003k\u0003!\u0019!C\u0002\u0003oC\u0011\"!1\u0001\u0005\u0004%\u0019!a1\t\u0013\u0005\u001d\u0007A1A\u0005\u0004\u0005%\u0007\"CAg\u0001\t\u0007I1AAh\u0011%\t\u0019\u000e\u0001b\u0001\n\u0007\t)\u000eC\u0005\u0002^\u0002\u0011\r\u0011b\u0001\u0002`\"I\u00111\u001d\u0001C\u0002\u0013\r\u0011Q\u001d\u0005\n\u0003S\u0004!\u0019!C\u0002\u0003WD\u0011\"a<\u0001\u0005\u0004%\u0019!!=\t\u0013\u0005U\bA1A\u0005\u0004\u0005]\b\"CA~\u0001\t\u0007I1AA\u007f\u0011%\u0011\t\u0001\u0001b\u0001\n\u0007\u0011\u0019\u0001C\u0005\u0003\b\u0001\u0011\r\u0011b\u0001\u0003\n!I!Q\u0002\u0001C\u0002\u0013\r!q\u0002\u0005\n\u0005'\u0001!\u0019!C\u0002\u0005+A\u0011B!\u0007\u0001\u0005\u0004%\u0019Aa\u0007\t\u0013\t}\u0001A1A\u0005\u0004\t\u0005\u0002\"\u0003B\u0013\u0001\t\u0007I1\u0001B\u0014\u0011%\u0011Y\u0003\u0001b\u0001\n\u0007\u0011i\u0003C\u0005\u00032\u0001\u0011\r\u0011b\u0001\u00034!I!q\u0007\u0001C\u0002\u0013\r!\u0011\b\u0005\n\u0005{\u0001!\u0019!C\u0002\u0005\u007fA\u0011Ba\u0011\u0001\u0005\u0004%\u0019A!\u0012\t\u0013\t%\u0003A1A\u0005\u0004\t-\u0003\"\u0003B(\u0001\t\u0007I1\u0001B)\u0011%\u0011)\u0006\u0001b\u0001\n\u0007\u00119\u0006C\u0005\u0003\\\u0001\u0011\r\u0011b\u0001\u0003^!I!\u0011\r\u0001C\u0002\u0013\r!1\r\u0005\n\u0005O\u0002!\u0019!C\u0002\u0005SB\u0011B!\u001c\u0001\u0005\u0004%\u0019Aa\u001c\t\u0013\tM\u0004A1A\u0005\u0004\tU\u0004\"\u0003B=\u0001\t\u0007I1\u0001B>\u0011%\u0011y\b\u0001b\u0001\n\u0007\u0011\t\tC\u0005\u0003\u001e\u0002\u0011\r\u0011b\u0001\u0003 \"I!1\u0015\u0001C\u0002\u0013\r!Q\u0015\u0005\n\u0005S\u0003!\u0019!C\u0002\u0005WC\u0011Ba,\u0001\u0005\u0004%\u0019A!-\t\u0013\t\u001d\u0007A1A\u0005\u0004\t%\u0007\"\u0003Bi\u0001\t\u0007I1\u0001Bj\u0011%\u0011Y\u000e\u0001b\u0001\n\u0007\u0011i\u000eC\u0005\u0003f\u0002\u0011\r\u0011b\u0001\u0003h\na2\u000b]1sg\u00164Vm\u0019;pe~#UM\\:f-\u0016\u001cGo\u001c:`\u001fB\u001c(BA#G\u0003%y\u0007/\u001a:bi>\u00148O\u0003\u0002H\u0011\u00061A.\u001b8bY\u001eT\u0011!S\u0001\u0007EJ,WM_3\u0004\u0001M)\u0001\u0001\u0014*W3B\u0011Q\nU\u0007\u0002\u001d*\tq*A\u0003tG\u0006d\u0017-\u0003\u0002R\u001d\n1\u0011I\\=SK\u001a\u0004\"a\u0015+\u000e\u0003\u0011K!!\u0016#\u0003\u0013Y+7\r^8s\u001fB\u001c\bCA*X\u0013\tAFI\u0001\u0006HK:,'/[2PaN\u0004\"a\u0015.\n\u0005m#%!G*qCJ\u001cXMV3di>\u0014x\f\u0016:bm\u0016\u00148/\u00197PaN\fa\u0001J5oSR$C#\u00010\u0011\u00055{\u0016B\u00011O\u0005\u0011)f.\u001b;\u0002?%l\u0007\u000f\\0Pa~\u001bfk\u0018#W?&s\u0007\u000b\\1dK~Ke\u000e^0Pa\u0006#G-F\u0001d!\u0011!w-\u001c;\u000f\u0005M+\u0017B\u00014E\u0003\u0015y\u0005/\u00113e\u0013\tA\u0017N\u0001\u0007J]Bc\u0017mY3J[Bd''\u0003\u0002kW\n)QKR;oG*\u0011A\u000eS\u0001\bO\u0016tWM]5d!\rqw.]\u0007\u0002\r&\u0011\u0001O\u0012\u0002\r'B\f'o]3WK\u000e$xN\u001d\t\u0003\u001bJL!a\u001d(\u0003\u0007%sG\u000fE\u0002okFL!A\u001e$\u0003\u0017\u0011+gn]3WK\u000e$xN]\u0001#S6\u0004HnX(q?N3v\f\u0012,`\u0013:\u0004F.Y2f?\u0012{WO\u00197f?>\u0003\u0018\t\u001a3\u0016\u0003e\u0004B\u0001Z4{}B\u0019an\\>\u0011\u00055c\u0018BA?O\u0005\u0019!u.\u001e2mKB\u0019a.^>\u0002C%l\u0007\u000f\\0Pa~\u001bfk\u0018#W?&s\u0007\u000b\\1dK~3En\\1u?>\u0003\u0018\t\u001a3\u0016\u0005\u0005\r\u0001C\u00023h\u0003\u000b\ti\u0001\u0005\u0003o_\u0006\u001d\u0001cA'\u0002\n%\u0019\u00111\u0002(\u0003\u000b\u0019cw.\u0019;\u0011\t9,\u0018qA\u0001!S6\u0004HnX(q?N3v\f\u0012,`\u0013:\u0004F.Y2f?2{gnZ0Pa\u0006#G-\u0006\u0002\u0002\u0014A1AmZA\u000b\u0003;\u0001BA\\8\u0002\u0018A\u0019Q*!\u0007\n\u0007\u0005maJ\u0001\u0003M_:<\u0007\u0003\u00028v\u0003/\tq$[7qY~{\u0005oX*W?\u00123v,\u00138QY\u0006\u001cWmX%oi~{\u0005oU;c+\t\t\u0019\u0003E\u0003\u0002&\u001dlGOD\u0002T\u0003OI1!!\u000bE\u0003\u0015y\u0005oU;c\u0003\tJW\u000e\u001d7`\u001fB|6KV0E-~Ke\u000e\u00157bG\u0016|Fi\\;cY\u0016|v\n]*vEV\u0011\u0011q\u0006\t\u0006\u0003K9'P`\u0001\"S6\u0004HnX(q?N3v\f\u0012,`\u0013:\u0004F.Y2f?\u001acw.\u0019;`\u001fB\u001cVOY\u000b\u0003\u0003k\u0001r!!\nh\u0003\u000b\ti!\u0001\u0011j[Bdwl\u00149`'Z{FIV0J]Bc\u0017mY3`\u0019>twmX(q'V\u0014WCAA\u001e!\u001d\t)cZA\u000b\u0003;\tQ%[7qY~{\u0005oX*W?\u00123v,\u00138QY\u0006\u001cWmX%oi~{\u0005/T;m'\u000e\fG.\u0019:\u0016\u0005\u0005\u0005\u0003#BA\"O6$hbA*\u0002F%\u0019\u0011q\t#\u0002\u0017=\u0003X*\u001e7TG\u0006d\u0017M]\u0001)S6\u0004HnX(q?N3v\f\u0012,`\u0013:\u0004F.Y2f?\u0012{WO\u00197f?>\u0003X*\u001e7TG\u0006d\u0017M]\u000b\u0003\u0003\u001b\u0002R!a\u0011huz\fq%[7qY~{\u0005oX*W?\u00123v,\u00138QY\u0006\u001cWm\u0018$m_\u0006$xl\u00149Nk2\u001c6-\u00197beV\u0011\u00111\u000b\t\b\u0003\u0007:\u0017QAA\u0007\u0003\u0019JW\u000e\u001d7`\u001fB|6KV0E-~Ke\u000e\u00157bG\u0016|Fj\u001c8h?>\u0003X*\u001e7TG\u0006d\u0017M]\u000b\u0003\u00033\u0002r!a\u0011h\u0003+\ti\"A\u0010j[Bdwl\u00149`'Z{FIV0J]Bc\u0017mY3`\u0013:$xl\u00149ESZ,\"!a\u0018\u0011\u000b\u0005\u0005t-\u001c;\u000f\u0007M\u000b\u0019'C\u0002\u0002f\u0011\u000bQa\u00149ESZ\f!%[7qY~{\u0005oX*W?\u00123v,\u00138QY\u0006\u001cWm\u0018#pk\ndWmX(q\t&4XCAA6!\u0015\t\tg\u001a>\u007f\u0003\u0005JW\u000e\u001d7`\u001fB|6KV0E-~Ke\u000e\u00157bG\u0016|f\t\\8bi~{\u0005\u000fR5w+\t\t\t\bE\u0004\u0002b\u001d\f)!!\u0004\u0002A%l\u0007\u000f\\0Pa~\u001bfk\u0018#W?&s\u0007\u000b\\1dK~cuN\\4`\u001fB$\u0015N^\u000b\u0003\u0003o\u0002r!!\u0019h\u0003+\ti\"A\u0010j[Bdwl\u00149`'Z{FIV0J]Bc\u0017mY3`\u0013:$xl\u00149TKR,\"!! \u0011\u000b\u0005}t-\u001c;\u000f\u0007M\u000b\t)C\u0002\u0002\u0004\u0012\u000bQa\u00149TKR\f!%[7qY~{\u0005oX*W?\u00123v,\u00138QY\u0006\u001cWm\u0018#pk\ndWmX(q'\u0016$XCAAE!\u0015\tyh\u001a>\u007f\u0003\u0005JW\u000e\u001d7`\u001fB|6KV0E-~Ke\u000e\u00157bG\u0016|f\t\\8bi~{\u0005oU3u+\t\ty\tE\u0004\u0002\u0000\u001d\f)!!\u0004\u0002A%l\u0007\u000f\\0Pa~\u001bfk\u0018#W?&s\u0007\u000b\\1dK~cuN\\4`\u001fB\u001cV\r^\u000b\u0003\u0003+\u0003r!a h\u0003+\ti\"A\u0010j[Bdwl\u00149`'Z{FIV0J]Bc\u0017mY3`\u0013:$xl\u00149N_\u0012,\"!a'\u0011\u000b\u0005uu-\u001c;\u000f\u0007M\u000by*C\u0002\u0002\"\u0012\u000bQa\u00149N_\u0012\f!%[7qY~{\u0005oX*W?\u00123v,\u00138QY\u0006\u001cWm\u0018#pk\ndWmX(q\u001b>$WCAAT!\u0015\tij\u001a>\u007f\u0003\u0005JW\u000e\u001d7`\u001fB|6KV0E-~Ke\u000e\u00157bG\u0016|f\t\\8bi~{\u0005/T8e+\t\ti\u000bE\u0004\u0002\u001e\u001e\f)!!\u0004\u0002A%l\u0007\u000f\\0Pa~\u001bfk\u0018#W?&s\u0007\u000b\\1dK~cuN\\4`\u001fBlu\u000eZ\u000b\u0003\u0003g\u0003r!!(h\u0003+\ti\"A\u0010j[Bdwl\u00149`'Z{FIV0J]Bc\u0017mY3`\u0013:$xl\u00149Q_^,\"!!/\u0011\u000b\u0005mv-\u001c;\u000f\u0007M\u000bi,C\u0002\u0002@\u0012\u000bQa\u00149Q_^\f!%[7qY~{\u0005oX*W?\u00123v,\u00138QY\u0006\u001cWm\u0018#pk\ndWmX(q!><XCAAc!\u0015\tYl\u001a>\u007f\u0003\u0005JW\u000e\u001d7`\u001fB|6KV0E-~Ke\u000e\u00157bG\u0016|f\t\\8bi~{\u0005\u000fU8x+\t\tY\rE\u0004\u0002<\u001e\f)!!\u0004\u0002A%l\u0007\u000f\\0Pa~\u001bfk\u0018#W?&s\u0007\u000b\\1dK~cuN\\4`\u001fB\u0004vn^\u000b\u0003\u0003#\u0004r!a/h\u0003+\ti\"A\u0012j[Bdwl\u00149`'Z{FIV0fc~\u001bfkX%oi~{\u0005/T;m'\u000e\fG.\u0019:\u0016\u0005\u0005]\u0007cBA\"\u00033lG/\\\u0005\u0004\u00037L'!B%na2\u0014\u0014AJ5na2|v\n]0T-~#ekX3r?N3v\fR8vE2,wl\u00149Nk2\u001c6-\u00197beV\u0011\u0011\u0011\u001d\t\b\u0003\u0007\nIN\u001f@{\u0003\u0015JW\u000e\u001d7`\u001fB|6KV0E-~+\u0017oX*W?\u001acw.\u0019;`\u001fBlU\u000f\\*dC2\f'/\u0006\u0002\u0002hBQ\u00111IAm\u0003\u000b\ti!!\u0002\u0002I%l\u0007\u000f\\0Pa~\u001bfk\u0018#W?\u0016\fxl\u0015,`\u0019>twmX(q\u001bVd7kY1mCJ,\"!!<\u0011\u0015\u0005\r\u0013\u0011\\A\u000b\u0003;\t)\"A\u000fj[Bdwl\u00149`'Z{FIV0fc~\u001bfkX%oi~{\u0005\u000fR5w+\t\t\u0019\u0010E\u0004\u0002b\u0005eW\u000e^7\u0002A%l\u0007\u000f\\0Pa~\u001bfk\u0018#W?\u0016\fxl\u0015,`\t>,(\r\\3`\u001fB$\u0015N^\u000b\u0003\u0003s\u0004r!!\u0019\u0002Zjt(0A\u0010j[Bdwl\u00149`'Z{FIV0fc~\u001bfk\u0018$m_\u0006$xl\u00149ESZ,\"!a@\u0011\u0015\u0005\u0005\u0014\u0011\\A\u0003\u0003\u001b\t)!\u0001\u0010j[Bdwl\u00149`'Z{FIV0fc~\u001bfk\u0018'p]\u001e|v\n\u001d#jmV\u0011!Q\u0001\t\u000b\u0003C\nI.!\u0006\u0002\u001e\u0005U\u0011!H5na2|v\n]0T-~#ekX3r?\u00123v,\u00138u?>\u0003\u0018\t\u001a3\u0016\u0005\t-\u0001C\u00023\u0002Z6$H/\u0001\u0011j[Bdwl\u00149`'Z{FIV0fc~#ek\u0018#pk\ndWmX(q\u0003\u0012$WC\u0001B\t!\u0019!\u0017\u0011\u001c>\u007f}\u0006y\u0012.\u001c9m?>\u0003xl\u0015,`\tZ{V-]0E-~3En\\1u?>\u0003\u0018\t\u001a3\u0016\u0005\t]\u0001#\u00033\u0002Z\u0006\u0015\u0011QBA\u0007\u0003yIW\u000e\u001d7`\u001fB|6KV0E-~+\u0017o\u0018#W?2{gnZ0Pa\u0006#G-\u0006\u0002\u0003\u001eAIA-!7\u0002\u0016\u0005u\u0011QD\u0001\u001eS6\u0004HnX(q?N3v\f\u0012,`KF|FIV0J]R|v\n]*vEV\u0011!1\u0005\t\b\u0003K\tI.\u001c;u\u0003\u0001JW\u000e\u001d7`\u001fB|6KV0E-~+\u0017o\u0018#W?\u0012{WO\u00197f?>\u00038+\u001e2\u0016\u0005\t%\u0002cBA\u0013\u00033ThP`\u0001 S6\u0004HnX(q?N3v\f\u0012,`KF|FIV0GY>\fGoX(q'V\u0014WC\u0001B\u0018!)\t)#!7\u0002\u0006\u00055\u0011QB\u0001\u001fS6\u0004HnX(q?N3v\f\u0012,`KF|FIV0M_:<wl\u00149Tk\n,\"A!\u000e\u0011\u0015\u0005\u0015\u0012\u0011\\A\u000b\u0003;\ti\"A\u000fj[Bdwl\u00149`'Z{FIV0fc~#ekX%oi~{\u0005oU3u+\t\u0011Y\u0004E\u0004\u0002\u0000\u0005eW\u000e\u001e;\u0002A%l\u0007\u000f\\0Pa~\u001bfk\u0018#W?\u0016\fx\f\u0012,`\t>,(\r\\3`\u001fB\u001cV\r^\u000b\u0003\u0005\u0003\u0002r!a \u0002Zjth0A\u0010j[Bdwl\u00149`'Z{FIV0fc~#ek\u0018$m_\u0006$xl\u00149TKR,\"Aa\u0012\u0011\u0015\u0005}\u0014\u0011\\A\u0003\u0003\u001b\ti!\u0001\u0010j[Bdwl\u00149`'Z{FIV0fc~#ek\u0018'p]\u001e|v\n]*fiV\u0011!Q\n\t\u000b\u0003\u007f\nI.!\u0006\u0002\u001e\u0005u\u0011!H5na2|v\n]0T-~#ekX3r?\u00123v,\u00138u?>\u0003Xj\u001c3\u0016\u0005\tM\u0003cBAO\u00033lG\u000f^\u0001!S6\u0004HnX(q?N3v\f\u0012,`KF|FIV0E_V\u0014G.Z0Pa6{G-\u0006\u0002\u0003ZA9\u0011QTAmuzt\u0018aH5na2|v\n]0T-~#ekX3r?\u00123vL\u00127pCR|v\n]'pIV\u0011!q\f\t\u000b\u0003;\u000bI.!\u0002\u0002\u000e\u00055\u0011AH5na2|v\n]0T-~#ekX3r?\u00123v\fT8oO~{\u0005/T8e+\t\u0011)\u0007\u0005\u0006\u0002\u001e\u0006e\u0017QCA\u000f\u0003;\tQ$[7qY~{\u0005oX*W?\u00123v,Z9`\tZ{\u0016J\u001c;`\u001fB\u0004vn^\u000b\u0003\u0005W\u0002r!a/\u0002Z6$H/\u0001\u0011j[Bdwl\u00149`'Z{FIV0fc~#ek\u0018#pk\ndWmX(q!><XC\u0001B9!\u001d\tY,!7{}z\fq$[7qY~{\u0005oX*W?\u00123v,Z9`\tZ{f\t\\8bi~{\u0005\u000fU8x+\t\u00119\b\u0005\u0006\u0002<\u0006e\u0017QAA\u0007\u0003\u001b\ta$[7qY~{\u0005oX*W?\u00123v,Z9`\tZ{Fj\u001c8h?>\u0003\bk\\<\u0016\u0005\tu\u0004CCA^\u00033\f)\"!\b\u0002\u001e\u0005q\u0012.\u001c9m?>\u0003X*\u001e7J]:,'oX*W?\u00123v,Z9`)~Ke\u000e^\u000b\u0003\u0005\u0007\u0003rA!\"\u0002Z6$\u0018O\u0004\u0003\u0003\b\nee\u0002\u0002BE\u0005/sAAa#\u0003\u0016:!!Q\u0012BJ\u001b\t\u0011yIC\u0002\u0003\u0012*\u000ba\u0001\u0010:p_Rt\u0014\"A%\n\u0005\u001dC\u0015BA#G\u0013\r\u0011Y\nR\u0001\u000b\u001fBlU\u000f\\%o]\u0016\u0014\u0018!I5na2|v\n]'vY&sg.\u001a:`'Z{FIV0fc~#v\fR8vE2,WC\u0001BQ!\u001d\u0011))!7{}n\f\u0001%[7qY~{\u0005/T;m\u0013:tWM]0T-~#ekX3r?R{f\t\\8biV\u0011!q\u0015\t\u000b\u0005\u000b\u000bI.!\u0002\u0002\u000e\u0005\u001d\u0011aH5na2|v\n]'vY&sg.\u001a:`'Z{FIV0fc~#v\fT8oOV\u0011!Q\u0016\t\u000b\u0005\u000b\u000bI.!\u0006\u0002\u001e\u0005]\u0011AI5na2|v\n]'vY6\u000bGO]5y?N3v\f\u0012,u?\u0016\fxlU'U?&sG/\u0006\u0002\u00034BI!QWAm[\nm&\u0011\u0019\b\u0004'\n]\u0016b\u0001B]\t\u0006Yq\n]'vY6\u000bGO]5y!\u0011q'Q\u0018;\n\u0007\t}fIA\u0005Ue\u0006t7\u000f]8tKB!aNa1r\u0013\r\u0011)M\u0012\u0002\n\u0007N\u001bU*\u0019;sSb\fQ%[7qY~{\u0005/T;m\u001b\u0006$(/\u001b=`'Z{FI\u0016;`KF|6+\u0014+`\t>,(\r\\3\u0016\u0005\t-\u0007#\u0003B[\u00033T(Q\u001aBh!\u0011q'Q\u0018@\u0011\t9\u0014\u0019m_\u0001%S6\u0004HnX(q\u001bVdW*\u0019;sSb|6KV0E-R|V-]0T\u001bR{f\t\\8biV\u0011!Q\u001b\t\u000b\u0005k\u000bI.!\u0002\u0003X\ne\u0007#\u00028\u0003>\u00065\u0001#\u00028\u0003D\u0006\u001d\u0011aI5na2|v\n]'vY6\u000bGO]5y?N3v\f\u0012,u?\u0016\fxlU'U?2{gnZ\u000b\u0003\u0005?\u0004\"B!.\u0002Z\u0006U!\u0011\u001dBr!\u0015q'QXA\u000f!\u0015q'1YA\f\u0003\u0019JW\u000e\u001d7`\u001fBlU\u000f\\'biJL\u0007pX*W?\u00123FoX3r?NkEkX\"p[BdW\r_\u000b\u0003\u0005S\u0004\"B!.\u0002Z\n-(\u0011 B\u007f!\u0011qwN!<\u0011\t\t=(Q_\u0007\u0003\u0005cT1Aa=I\u0003\u0011i\u0017\r\u001e5\n\t\t](\u0011\u001f\u0002\b\u0007>l\u0007\u000f\\3y!\u0015q'Q\u0018B~!\u0011qWO!<\u0011\u000b9\u0014\u0019M!<"
)
public interface SparseVector_DenseVector_Ops extends VectorOps, SparseVector_TraversalOps {
   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Int_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Double_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Float_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Long_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Int_OpSub_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Double_OpSub_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Float_OpSub_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Long_OpSub_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Int_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Double_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Float_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Long_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Int_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Double_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Float_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Long_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Int_OpSet_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Double_OpSet_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Float_OpSet_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Long_OpSet_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Int_OpMod_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Double_OpMod_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Float_OpMod_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Long_OpMod_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Int_OpPow_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Double_OpPow_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Float_OpPow_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Long_OpPow_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_SV_Int_OpMulScalar_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_SV_Double_OpMulScalar_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_SV_Float_OpMulScalar_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_SV_Long_OpMulScalar_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_SV_Int_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_SV_Double_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_SV_Float_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_SV_Long_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Int_OpAdd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Double_OpAdd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Float_OpAdd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Long_OpAdd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Int_OpSub_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Double_OpSub_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Float_OpSub_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Long_OpSub_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Int_OpSet_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Double_OpSet_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Float_OpSet_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Long_OpSet_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Int_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Double_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Float_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Long_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Int_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Double_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Float_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Long_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_OpMulInner_SV_DV_eq_T_Int_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_OpMulInner_SV_DV_eq_T_Double_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_OpMulInner_SV_DV_eq_T_Float_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_OpMulInner_SV_DV_eq_T_Long_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_OpMulMatrix_SV_DVt_eq_SMT_Int_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_OpMulMatrix_SV_DVt_eq_SMT_Double_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_OpMulMatrix_SV_DVt_eq_SMT_Float_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_OpMulMatrix_SV_DVt_eq_SMT_Long_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_OpMulMatrix_SV_DVt_eq_SMT_Complex_$eq(final UFunc.UImpl2 x$1);

   UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Int_OpAdd();

   UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Double_OpAdd();

   UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Float_OpAdd();

   UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Long_OpAdd();

   UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Int_OpSub();

   UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Double_OpSub();

   UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Float_OpSub();

   UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Long_OpSub();

   UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Int_OpMulScalar();

   UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Double_OpMulScalar();

   UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Float_OpMulScalar();

   UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Long_OpMulScalar();

   UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Int_OpDiv();

   UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Double_OpDiv();

   UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Float_OpDiv();

   UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Long_OpDiv();

   UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Int_OpSet();

   UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Double_OpSet();

   UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Float_OpSet();

   UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Long_OpSet();

   UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Int_OpMod();

   UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Double_OpMod();

   UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Float_OpMod();

   UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Long_OpMod();

   UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Int_OpPow();

   UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Double_OpPow();

   UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Float_OpPow();

   UFunc.InPlaceImpl2 impl_Op_SV_DV_InPlace_Long_OpPow();

   UFunc.UImpl2 impl_Op_SV_DV_eq_SV_Int_OpMulScalar();

   UFunc.UImpl2 impl_Op_SV_DV_eq_SV_Double_OpMulScalar();

   UFunc.UImpl2 impl_Op_SV_DV_eq_SV_Float_OpMulScalar();

   UFunc.UImpl2 impl_Op_SV_DV_eq_SV_Long_OpMulScalar();

   UFunc.UImpl2 impl_Op_SV_DV_eq_SV_Int_OpDiv();

   UFunc.UImpl2 impl_Op_SV_DV_eq_SV_Double_OpDiv();

   UFunc.UImpl2 impl_Op_SV_DV_eq_SV_Float_OpDiv();

   UFunc.UImpl2 impl_Op_SV_DV_eq_SV_Long_OpDiv();

   UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Int_OpAdd();

   UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Double_OpAdd();

   UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Float_OpAdd();

   UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Long_OpAdd();

   UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Int_OpSub();

   UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Double_OpSub();

   UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Float_OpSub();

   UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Long_OpSub();

   UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Int_OpSet();

   UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Double_OpSet();

   UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Float_OpSet();

   UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Long_OpSet();

   UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Int_OpMod();

   UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Double_OpMod();

   UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Float_OpMod();

   UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Long_OpMod();

   UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Int_OpPow();

   UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Double_OpPow();

   UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Float_OpPow();

   UFunc.UImpl2 impl_Op_SV_DV_eq_DV_Long_OpPow();

   UFunc.UImpl2 impl_OpMulInner_SV_DV_eq_T_Int();

   UFunc.UImpl2 impl_OpMulInner_SV_DV_eq_T_Double();

   UFunc.UImpl2 impl_OpMulInner_SV_DV_eq_T_Float();

   UFunc.UImpl2 impl_OpMulInner_SV_DV_eq_T_Long();

   UFunc.UImpl2 impl_OpMulMatrix_SV_DVt_eq_SMT_Int();

   UFunc.UImpl2 impl_OpMulMatrix_SV_DVt_eq_SMT_Double();

   UFunc.UImpl2 impl_OpMulMatrix_SV_DVt_eq_SMT_Float();

   UFunc.UImpl2 impl_OpMulMatrix_SV_DVt_eq_SMT_Long();

   UFunc.UImpl2 impl_OpMulMatrix_SV_DVt_eq_SMT_Complex();

   static void $init$(final SparseVector_DenseVector_Ops $this) {
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Int_OpAdd_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcI$sp(a.length(), a.length(), Semiring$.MODULE$.semiringInt(), .MODULE$.Int());
               int[] bd = b.data$mcI$sp();
               int adefault = a.array$mcI$sp().default$mcI$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               int[] ad = a.data$mcI$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.add$mcI$sp(j, adefault + bd[boff]);
                     boff += bstride;
                  }

                  result.add$mcI$sp(j, ad[i] + bd[boff]);
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.add$mcI$sp(j, adefault + bd[boff]);
                  boff += bstride;
                  ++j;
               }

               SparseVector rs = result.toSparseVector$mcI$sp(true, true);
               a.use$mcI$sp(rs.index(), rs.data$mcI$sp(), rs.activeSize());
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Idempotent_Int_OpAdd())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Double_OpAdd_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcD$sp(a.length(), a.length(), Semiring$.MODULE$.semiringD(), .MODULE$.Double());
               double[] bd = b.data$mcD$sp();
               double adefault = a.array$mcD$sp().default$mcD$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               double[] ad = a.data$mcD$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.add$mcD$sp(j, adefault + bd[boff]);
                     boff += bstride;
                  }

                  result.add$mcD$sp(j, ad[i] + bd[boff]);
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.add$mcD$sp(j, adefault + bd[boff]);
                  boff += bstride;
                  ++j;
               }

               SparseVector rs = result.toSparseVector$mcD$sp(true, true);
               a.use$mcD$sp(rs.index(), rs.data$mcD$sp(), rs.activeSize());
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Idempotent_Double_OpAdd())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Float_OpAdd_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcF$sp(a.length(), a.length(), Semiring$.MODULE$.semiringFloat(), .MODULE$.Float());
               float[] bd = b.data$mcF$sp();
               float adefault = a.array$mcF$sp().default$mcF$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               float[] ad = a.data$mcF$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.add$mcF$sp(j, adefault + bd[boff]);
                     boff += bstride;
                  }

                  result.add$mcF$sp(j, ad[i] + bd[boff]);
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.add$mcF$sp(j, adefault + bd[boff]);
                  boff += bstride;
                  ++j;
               }

               SparseVector rs = result.toSparseVector$mcF$sp(true, true);
               a.use$mcF$sp(rs.index(), rs.data$mcF$sp(), rs.activeSize());
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Idempotent_Float_OpAdd())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Long_OpAdd_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcJ$sp(a.length(), a.length(), Semiring$.MODULE$.semiringLong(), .MODULE$.Long());
               long[] bd = b.data$mcJ$sp();
               long adefault = a.array$mcJ$sp().default$mcJ$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               long[] ad = a.data$mcJ$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.add$mcJ$sp(j, adefault + bd[boff]);
                     boff += bstride;
                  }

                  result.add$mcJ$sp(j, ad[i] + bd[boff]);
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.add$mcJ$sp(j, adefault + bd[boff]);
                  boff += bstride;
                  ++j;
               }

               SparseVector rs = result.toSparseVector$mcJ$sp(true, true);
               a.use$mcJ$sp(rs.index(), rs.data$mcJ$sp(), rs.activeSize());
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Idempotent_Long_OpAdd())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Int_OpSub_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcI$sp(a.length(), a.length(), Semiring$.MODULE$.semiringInt(), .MODULE$.Int());
               int[] bd = b.data$mcI$sp();
               int adefault = a.array$mcI$sp().default$mcI$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               int[] ad = a.data$mcI$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.add$mcI$sp(j, adefault - bd[boff]);
                     boff += bstride;
                  }

                  result.add$mcI$sp(j, ad[i] - bd[boff]);
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.add$mcI$sp(j, adefault - bd[boff]);
                  boff += bstride;
                  ++j;
               }

               SparseVector rs = result.toSparseVector$mcI$sp(true, true);
               a.use$mcI$sp(rs.index(), rs.data$mcI$sp(), rs.activeSize());
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Idempotent_Int_OpSub())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Double_OpSub_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcD$sp(a.length(), a.length(), Semiring$.MODULE$.semiringD(), .MODULE$.Double());
               double[] bd = b.data$mcD$sp();
               double adefault = a.array$mcD$sp().default$mcD$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               double[] ad = a.data$mcD$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.add$mcD$sp(j, adefault - bd[boff]);
                     boff += bstride;
                  }

                  result.add$mcD$sp(j, ad[i] - bd[boff]);
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.add$mcD$sp(j, adefault - bd[boff]);
                  boff += bstride;
                  ++j;
               }

               SparseVector rs = result.toSparseVector$mcD$sp(true, true);
               a.use$mcD$sp(rs.index(), rs.data$mcD$sp(), rs.activeSize());
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Idempotent_Double_OpSub())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Float_OpSub_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcF$sp(a.length(), a.length(), Semiring$.MODULE$.semiringFloat(), .MODULE$.Float());
               float[] bd = b.data$mcF$sp();
               float adefault = a.array$mcF$sp().default$mcF$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               float[] ad = a.data$mcF$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.add$mcF$sp(j, adefault - bd[boff]);
                     boff += bstride;
                  }

                  result.add$mcF$sp(j, ad[i] - bd[boff]);
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.add$mcF$sp(j, adefault - bd[boff]);
                  boff += bstride;
                  ++j;
               }

               SparseVector rs = result.toSparseVector$mcF$sp(true, true);
               a.use$mcF$sp(rs.index(), rs.data$mcF$sp(), rs.activeSize());
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Idempotent_Float_OpSub())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Long_OpSub_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcJ$sp(a.length(), a.length(), Semiring$.MODULE$.semiringLong(), .MODULE$.Long());
               long[] bd = b.data$mcJ$sp();
               long adefault = a.array$mcJ$sp().default$mcJ$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               long[] ad = a.data$mcJ$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.add$mcJ$sp(j, adefault - bd[boff]);
                     boff += bstride;
                  }

                  result.add$mcJ$sp(j, ad[i] - bd[boff]);
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.add$mcJ$sp(j, adefault - bd[boff]);
                  boff += bstride;
                  ++j;
               }

               SparseVector rs = result.toSparseVector$mcJ$sp(true, true);
               a.use$mcJ$sp(rs.index(), rs.data$mcJ$sp(), rs.activeSize());
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Idempotent_Long_OpSub())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Int_OpMulScalar_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcI$sp(a.length(), a.length(), Semiring$.MODULE$.semiringInt(), .MODULE$.Int());
               int[] bd = b.data$mcI$sp();
               int adefault = a.array$mcI$sp().default$mcI$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               int[] ad = a.data$mcI$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.add$mcI$sp(j, adefault * bd[boff]);
                     boff += bstride;
                  }

                  result.add$mcI$sp(j, ad[i] * bd[boff]);
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.add$mcI$sp(j, adefault * bd[boff]);
                  boff += bstride;
                  ++j;
               }

               SparseVector rs = result.toSparseVector$mcI$sp(true, true);
               a.use$mcI$sp(rs.index(), rs.data$mcI$sp(), rs.activeSize());
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Int_OpMulScalar())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Double_OpMulScalar_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcD$sp(a.length(), a.length(), Semiring$.MODULE$.semiringD(), .MODULE$.Double());
               double[] bd = b.data$mcD$sp();
               double adefault = a.array$mcD$sp().default$mcD$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               double[] ad = a.data$mcD$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.add$mcD$sp(j, adefault * bd[boff]);
                     boff += bstride;
                  }

                  result.add$mcD$sp(j, ad[i] * bd[boff]);
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.add$mcD$sp(j, adefault * bd[boff]);
                  boff += bstride;
                  ++j;
               }

               SparseVector rs = result.toSparseVector$mcD$sp(true, true);
               a.use$mcD$sp(rs.index(), rs.data$mcD$sp(), rs.activeSize());
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Double_OpMulScalar())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Float_OpMulScalar_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcF$sp(a.length(), a.length(), Semiring$.MODULE$.semiringFloat(), .MODULE$.Float());
               float[] bd = b.data$mcF$sp();
               float adefault = a.array$mcF$sp().default$mcF$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               float[] ad = a.data$mcF$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.add$mcF$sp(j, adefault * bd[boff]);
                     boff += bstride;
                  }

                  result.add$mcF$sp(j, ad[i] * bd[boff]);
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.add$mcF$sp(j, adefault * bd[boff]);
                  boff += bstride;
                  ++j;
               }

               SparseVector rs = result.toSparseVector$mcF$sp(true, true);
               a.use$mcF$sp(rs.index(), rs.data$mcF$sp(), rs.activeSize());
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Float_OpMulScalar())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Long_OpMulScalar_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcJ$sp(a.length(), a.length(), Semiring$.MODULE$.semiringLong(), .MODULE$.Long());
               long[] bd = b.data$mcJ$sp();
               long adefault = a.array$mcJ$sp().default$mcJ$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               long[] ad = a.data$mcJ$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.add$mcJ$sp(j, adefault * bd[boff]);
                     boff += bstride;
                  }

                  result.add$mcJ$sp(j, ad[i] * bd[boff]);
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.add$mcJ$sp(j, adefault * bd[boff]);
                  boff += bstride;
                  ++j;
               }

               SparseVector rs = result.toSparseVector$mcJ$sp(true, true);
               a.use$mcJ$sp(rs.index(), rs.data$mcJ$sp(), rs.activeSize());
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Long_OpMulScalar())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Int_OpDiv_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcI$sp(a.length(), a.length(), Semiring$.MODULE$.semiringInt(), .MODULE$.Int());
               int[] bd = b.data$mcI$sp();
               int adefault = a.array$mcI$sp().default$mcI$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               int[] ad = a.data$mcI$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.add$mcI$sp(j, adefault / bd[boff]);
                     boff += bstride;
                  }

                  result.add$mcI$sp(j, ad[i] / bd[boff]);
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.add$mcI$sp(j, adefault / bd[boff]);
                  boff += bstride;
                  ++j;
               }

               SparseVector rs = result.toSparseVector$mcI$sp(true, true);
               a.use$mcI$sp(rs.index(), rs.data$mcI$sp(), rs.activeSize());
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Int_OpDiv())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Double_OpDiv_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcD$sp(a.length(), a.length(), Semiring$.MODULE$.semiringD(), .MODULE$.Double());
               double[] bd = b.data$mcD$sp();
               double adefault = a.array$mcD$sp().default$mcD$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               double[] ad = a.data$mcD$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.add$mcD$sp(j, adefault / bd[boff]);
                     boff += bstride;
                  }

                  result.add$mcD$sp(j, ad[i] / bd[boff]);
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.add$mcD$sp(j, adefault / bd[boff]);
                  boff += bstride;
                  ++j;
               }

               SparseVector rs = result.toSparseVector$mcD$sp(true, true);
               a.use$mcD$sp(rs.index(), rs.data$mcD$sp(), rs.activeSize());
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Double_OpDiv())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Float_OpDiv_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcF$sp(a.length(), a.length(), Semiring$.MODULE$.semiringFloat(), .MODULE$.Float());
               float[] bd = b.data$mcF$sp();
               float adefault = a.array$mcF$sp().default$mcF$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               float[] ad = a.data$mcF$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.add$mcF$sp(j, adefault / bd[boff]);
                     boff += bstride;
                  }

                  result.add$mcF$sp(j, ad[i] / bd[boff]);
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.add$mcF$sp(j, adefault / bd[boff]);
                  boff += bstride;
                  ++j;
               }

               SparseVector rs = result.toSparseVector$mcF$sp(true, true);
               a.use$mcF$sp(rs.index(), rs.data$mcF$sp(), rs.activeSize());
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Float_OpDiv())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Long_OpDiv_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcJ$sp(a.length(), a.length(), Semiring$.MODULE$.semiringLong(), .MODULE$.Long());
               long[] bd = b.data$mcJ$sp();
               long adefault = a.array$mcJ$sp().default$mcJ$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               long[] ad = a.data$mcJ$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.add$mcJ$sp(j, adefault / bd[boff]);
                     boff += bstride;
                  }

                  result.add$mcJ$sp(j, ad[i] / bd[boff]);
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.add$mcJ$sp(j, adefault / bd[boff]);
                  boff += bstride;
                  ++j;
               }

               SparseVector rs = result.toSparseVector$mcJ$sp(true, true);
               a.use$mcJ$sp(rs.index(), rs.data$mcJ$sp(), rs.activeSize());
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Long_OpDiv())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Int_OpSet_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcI$sp(a.length(), a.length(), Semiring$.MODULE$.semiringInt(), .MODULE$.Int());
               int[] bd = b.data$mcI$sp();
               int adefault = a.array$mcI$sp().default$mcI$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               int[] ad = a.data$mcI$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.add$mcI$sp(j, bd[boff]);
                     boff += bstride;
                  }

                  result.add$mcI$sp(j, bd[boff]);
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.add$mcI$sp(j, bd[boff]);
                  boff += bstride;
                  ++j;
               }

               SparseVector rs = result.toSparseVector$mcI$sp(true, true);
               a.use$mcI$sp(rs.index(), rs.data$mcI$sp(), rs.activeSize());
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Int_OpSet())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Double_OpSet_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcD$sp(a.length(), a.length(), Semiring$.MODULE$.semiringD(), .MODULE$.Double());
               double[] bd = b.data$mcD$sp();
               double adefault = a.array$mcD$sp().default$mcD$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               double[] ad = a.data$mcD$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.add$mcD$sp(j, bd[boff]);
                     boff += bstride;
                  }

                  result.add$mcD$sp(j, bd[boff]);
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.add$mcD$sp(j, bd[boff]);
                  boff += bstride;
                  ++j;
               }

               SparseVector rs = result.toSparseVector$mcD$sp(true, true);
               a.use$mcD$sp(rs.index(), rs.data$mcD$sp(), rs.activeSize());
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Double_OpSet())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Float_OpSet_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcF$sp(a.length(), a.length(), Semiring$.MODULE$.semiringFloat(), .MODULE$.Float());
               float[] bd = b.data$mcF$sp();
               float adefault = a.array$mcF$sp().default$mcF$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               float[] ad = a.data$mcF$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.add$mcF$sp(j, bd[boff]);
                     boff += bstride;
                  }

                  result.add$mcF$sp(j, bd[boff]);
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.add$mcF$sp(j, bd[boff]);
                  boff += bstride;
                  ++j;
               }

               SparseVector rs = result.toSparseVector$mcF$sp(true, true);
               a.use$mcF$sp(rs.index(), rs.data$mcF$sp(), rs.activeSize());
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Float_OpSet())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Long_OpSet_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcJ$sp(a.length(), a.length(), Semiring$.MODULE$.semiringLong(), .MODULE$.Long());
               long[] bd = b.data$mcJ$sp();
               long adefault = a.array$mcJ$sp().default$mcJ$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               long[] ad = a.data$mcJ$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.add$mcJ$sp(j, bd[boff]);
                     boff += bstride;
                  }

                  result.add$mcJ$sp(j, bd[boff]);
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.add$mcJ$sp(j, bd[boff]);
                  boff += bstride;
                  ++j;
               }

               SparseVector rs = result.toSparseVector$mcJ$sp(true, true);
               a.use$mcJ$sp(rs.index(), rs.data$mcJ$sp(), rs.activeSize());
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Long_OpSet())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Int_OpMod_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcI$sp(a.length(), a.length(), Semiring$.MODULE$.semiringInt(), .MODULE$.Int());
               int[] bd = b.data$mcI$sp();
               int adefault = a.array$mcI$sp().default$mcI$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               int[] ad = a.data$mcI$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.add$mcI$sp(j, adefault % bd[boff]);
                     boff += bstride;
                  }

                  result.add$mcI$sp(j, ad[i] % bd[boff]);
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.add$mcI$sp(j, adefault % bd[boff]);
                  boff += bstride;
                  ++j;
               }

               SparseVector rs = result.toSparseVector$mcI$sp(true, true);
               a.use$mcI$sp(rs.index(), rs.data$mcI$sp(), rs.activeSize());
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Int_OpMod())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Double_OpMod_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcD$sp(a.length(), a.length(), Semiring$.MODULE$.semiringD(), .MODULE$.Double());
               double[] bd = b.data$mcD$sp();
               double adefault = a.array$mcD$sp().default$mcD$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               double[] ad = a.data$mcD$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.add$mcD$sp(j, adefault % bd[boff]);
                     boff += bstride;
                  }

                  result.add$mcD$sp(j, ad[i] % bd[boff]);
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.add$mcD$sp(j, adefault % bd[boff]);
                  boff += bstride;
                  ++j;
               }

               SparseVector rs = result.toSparseVector$mcD$sp(true, true);
               a.use$mcD$sp(rs.index(), rs.data$mcD$sp(), rs.activeSize());
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Double_OpMod())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Float_OpMod_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcF$sp(a.length(), a.length(), Semiring$.MODULE$.semiringFloat(), .MODULE$.Float());
               float[] bd = b.data$mcF$sp();
               float adefault = a.array$mcF$sp().default$mcF$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               float[] ad = a.data$mcF$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.add$mcF$sp(j, adefault % bd[boff]);
                     boff += bstride;
                  }

                  result.add$mcF$sp(j, ad[i] % bd[boff]);
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.add$mcF$sp(j, adefault % bd[boff]);
                  boff += bstride;
                  ++j;
               }

               SparseVector rs = result.toSparseVector$mcF$sp(true, true);
               a.use$mcF$sp(rs.index(), rs.data$mcF$sp(), rs.activeSize());
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Float_OpMod())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Long_OpMod_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcJ$sp(a.length(), a.length(), Semiring$.MODULE$.semiringLong(), .MODULE$.Long());
               long[] bd = b.data$mcJ$sp();
               long adefault = a.array$mcJ$sp().default$mcJ$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               long[] ad = a.data$mcJ$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.add$mcJ$sp(j, adefault % bd[boff]);
                     boff += bstride;
                  }

                  result.add$mcJ$sp(j, ad[i] % bd[boff]);
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.add$mcJ$sp(j, adefault % bd[boff]);
                  boff += bstride;
                  ++j;
               }

               SparseVector rs = result.toSparseVector$mcJ$sp(true, true);
               a.use$mcJ$sp(rs.index(), rs.data$mcJ$sp(), rs.activeSize());
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Long_OpMod())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Int_OpPow_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcI$sp(a.length(), a.length(), Semiring$.MODULE$.semiringInt(), .MODULE$.Int());
               int[] bd = b.data$mcI$sp();
               int adefault = a.array$mcI$sp().default$mcI$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               int[] ad = a.data$mcI$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.add$mcI$sp(j, PowImplicits$.MODULE$.IntPow(adefault).pow(bd[boff]));
                     boff += bstride;
                  }

                  result.add$mcI$sp(j, PowImplicits$.MODULE$.IntPow(ad[i]).pow(bd[boff]));
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.add$mcI$sp(j, PowImplicits$.MODULE$.IntPow(adefault).pow(bd[boff]));
                  boff += bstride;
                  ++j;
               }

               SparseVector rs = result.toSparseVector$mcI$sp(true, true);
               a.use$mcI$sp(rs.index(), rs.data$mcI$sp(), rs.activeSize());
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Int_OpPow())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Double_OpPow_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcD$sp(a.length(), a.length(), Semiring$.MODULE$.semiringD(), .MODULE$.Double());
               double[] bd = b.data$mcD$sp();
               double adefault = a.array$mcD$sp().default$mcD$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               double[] ad = a.data$mcD$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.add$mcD$sp(j, PowImplicits$.MODULE$.DoublePow(adefault).pow(bd[boff]));
                     boff += bstride;
                  }

                  result.add$mcD$sp(j, PowImplicits$.MODULE$.DoublePow(ad[i]).pow(bd[boff]));
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.add$mcD$sp(j, PowImplicits$.MODULE$.DoublePow(adefault).pow(bd[boff]));
                  boff += bstride;
                  ++j;
               }

               SparseVector rs = result.toSparseVector$mcD$sp(true, true);
               a.use$mcD$sp(rs.index(), rs.data$mcD$sp(), rs.activeSize());
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Double_OpPow())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Float_OpPow_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcF$sp(a.length(), a.length(), Semiring$.MODULE$.semiringFloat(), .MODULE$.Float());
               float[] bd = b.data$mcF$sp();
               float adefault = a.array$mcF$sp().default$mcF$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               float[] ad = a.data$mcF$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.add$mcF$sp(j, PowImplicits$.MODULE$.FloatPow(adefault).pow(bd[boff]));
                     boff += bstride;
                  }

                  result.add$mcF$sp(j, PowImplicits$.MODULE$.FloatPow(ad[i]).pow(bd[boff]));
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.add$mcF$sp(j, PowImplicits$.MODULE$.FloatPow(adefault).pow(bd[boff]));
                  boff += bstride;
                  ++j;
               }

               SparseVector rs = result.toSparseVector$mcF$sp(true, true);
               a.use$mcF$sp(rs.index(), rs.data$mcF$sp(), rs.activeSize());
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Float_OpPow())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_InPlace_Long_OpPow_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcJ$sp(a.length(), a.length(), Semiring$.MODULE$.semiringLong(), .MODULE$.Long());
               long[] bd = b.data$mcJ$sp();
               long adefault = a.array$mcJ$sp().default$mcJ$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               long[] ad = a.data$mcJ$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.add$mcJ$sp(j, PowImplicits$.MODULE$.LongPow(adefault).pow(bd[boff]));
                     boff += bstride;
                  }

                  result.add$mcJ$sp(j, PowImplicits$.MODULE$.LongPow(ad[i]).pow(bd[boff]));
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.add$mcJ$sp(j, PowImplicits$.MODULE$.LongPow(adefault).pow(bd[boff]));
                  boff += bstride;
                  ++j;
               }

               SparseVector rs = result.toSparseVector$mcJ$sp(true, true);
               a.use$mcJ$sp(rs.index(), rs.data$mcJ$sp(), rs.activeSize());
            }
         }

         public {
            ((BinaryUpdateRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Long_OpPow())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_SV_Int_OpMulScalar_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = VectorBuilder$.MODULE$.zeros$mIc$sp(a.length(), VectorBuilder$.MODULE$.zeros$default$2(), .MODULE$.Int(), Semiring$.MODULE$.semiringInt(), Zero$.MODULE$.IntZero());
               int[] bd = b.data$mcI$sp();
               int adefault = a.array$mcI$sp().default$mcI$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               int[] ad = a.data$mcI$sp();
               int[] ai = a.index();
               int index$macro$4 = 0;

               for(int limit$macro$6 = a.activeSize(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  int ind = a.indexAt(index$macro$4);
                  int res = a.valueAt$mcI$sp(index$macro$4) * b.apply$mcI$sp(ind);
                  if (res != 0) {
                     result.add$mcI$sp(ind, res);
                  }
               }

               return result.toSparseVector$mcI$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_nilpotent_Int())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_SV_Double_OpMulScalar_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = VectorBuilder$.MODULE$.zeros$mDc$sp(a.length(), VectorBuilder$.MODULE$.zeros$default$2(), .MODULE$.Double(), Semiring$.MODULE$.semiringD(), Zero$.MODULE$.DoubleZero());
               double[] bd = b.data$mcD$sp();
               double adefault = a.array$mcD$sp().default$mcD$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               double[] ad = a.data$mcD$sp();
               int[] ai = a.index();
               int index$macro$4 = 0;

               for(int limit$macro$6 = a.activeSize(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  int ind = a.indexAt(index$macro$4);
                  double res = a.valueAt$mcD$sp(index$macro$4) * b.apply$mcD$sp(ind);
                  if (res != (double)0) {
                     result.add$mcD$sp(ind, res);
                  }
               }

               return result.toSparseVector$mcD$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_nilpotent_Double())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_SV_Float_OpMulScalar_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = VectorBuilder$.MODULE$.zeros$mFc$sp(a.length(), VectorBuilder$.MODULE$.zeros$default$2(), .MODULE$.Float(), Semiring$.MODULE$.semiringFloat(), Zero$.MODULE$.FloatZero());
               float[] bd = b.data$mcF$sp();
               float adefault = a.array$mcF$sp().default$mcF$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               float[] ad = a.data$mcF$sp();
               int[] ai = a.index();
               int index$macro$4 = 0;

               for(int limit$macro$6 = a.activeSize(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  int ind = a.indexAt(index$macro$4);
                  float res = a.valueAt$mcF$sp(index$macro$4) * b.apply$mcF$sp(ind);
                  if (res != (float)0) {
                     result.add$mcF$sp(ind, res);
                  }
               }

               return result.toSparseVector$mcF$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_nilpotent_Float())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_SV_Long_OpMulScalar_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = VectorBuilder$.MODULE$.zeros$mJc$sp(a.length(), VectorBuilder$.MODULE$.zeros$default$2(), .MODULE$.Long(), Semiring$.MODULE$.semiringLong(), Zero$.MODULE$.LongZero());
               long[] bd = b.data$mcJ$sp();
               long adefault = a.array$mcJ$sp().default$mcJ$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               long[] ad = a.data$mcJ$sp();
               int[] ai = a.index();
               int index$macro$4 = 0;

               for(int limit$macro$6 = a.activeSize(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  int ind = a.indexAt(index$macro$4);
                  long res = a.valueAt$mcJ$sp(index$macro$4) * b.apply$mcJ$sp(ind);
                  if (res != 0L) {
                     result.add$mcJ$sp(ind, res);
                  }
               }

               return result.toSparseVector$mcJ$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_nilpotent_Long())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_SV_Int_OpDiv_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = VectorBuilder$.MODULE$.zeros$mIc$sp(a.length(), VectorBuilder$.MODULE$.zeros$default$2(), .MODULE$.Int(), Semiring$.MODULE$.semiringInt(), Zero$.MODULE$.IntZero());
               int[] bd = b.data$mcI$sp();
               int adefault = a.array$mcI$sp().default$mcI$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               int[] ad = a.data$mcI$sp();
               int[] ai = a.index();
               int index$macro$4 = 0;

               for(int limit$macro$6 = a.activeSize(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  int ind = a.indexAt(index$macro$4);
                  int res = a.valueAt$mcI$sp(index$macro$4) / b.apply$mcI$sp(ind);
                  if (res != 0) {
                     result.add$mcI$sp(ind, res);
                  }
               }

               return result.toSparseVector$mcI$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_Int_OpDiv())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_SV_Double_OpDiv_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = VectorBuilder$.MODULE$.zeros$mDc$sp(a.length(), VectorBuilder$.MODULE$.zeros$default$2(), .MODULE$.Double(), Semiring$.MODULE$.semiringD(), Zero$.MODULE$.DoubleZero());
               double[] bd = b.data$mcD$sp();
               double adefault = a.array$mcD$sp().default$mcD$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               double[] ad = a.data$mcD$sp();
               int[] ai = a.index();
               int index$macro$4 = 0;

               for(int limit$macro$6 = a.activeSize(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  int ind = a.indexAt(index$macro$4);
                  double res = a.valueAt$mcD$sp(index$macro$4) / b.apply$mcD$sp(ind);
                  if (res != (double)0) {
                     result.add$mcD$sp(ind, res);
                  }
               }

               return result.toSparseVector$mcD$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_Double_OpDiv())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_SV_Float_OpDiv_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = VectorBuilder$.MODULE$.zeros$mFc$sp(a.length(), VectorBuilder$.MODULE$.zeros$default$2(), .MODULE$.Float(), Semiring$.MODULE$.semiringFloat(), Zero$.MODULE$.FloatZero());
               float[] bd = b.data$mcF$sp();
               float adefault = a.array$mcF$sp().default$mcF$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               float[] ad = a.data$mcF$sp();
               int[] ai = a.index();
               int index$macro$4 = 0;

               for(int limit$macro$6 = a.activeSize(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  int ind = a.indexAt(index$macro$4);
                  float res = a.valueAt$mcF$sp(index$macro$4) / b.apply$mcF$sp(ind);
                  if (res != (float)0) {
                     result.add$mcF$sp(ind, res);
                  }
               }

               return result.toSparseVector$mcF$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_Float_OpDiv())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_SV_Long_OpDiv_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = VectorBuilder$.MODULE$.zeros$mJc$sp(a.length(), VectorBuilder$.MODULE$.zeros$default$2(), .MODULE$.Long(), Semiring$.MODULE$.semiringLong(), Zero$.MODULE$.LongZero());
               long[] bd = b.data$mcJ$sp();
               long adefault = a.array$mcJ$sp().default$mcJ$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               long[] ad = a.data$mcJ$sp();
               int[] ai = a.index();
               int index$macro$4 = 0;

               for(int limit$macro$6 = a.activeSize(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  int ind = a.indexAt(index$macro$4);
                  long res = a.valueAt$mcJ$sp(index$macro$4) / b.apply$mcJ$sp(ind);
                  if (res != 0L) {
                     result.add$mcJ$sp(ind, res);
                  }
               }

               return result.toSparseVector$mcJ$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_Long_OpDiv())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Int_OpAdd_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(a.length(), .MODULE$.Int(), Zero$.MODULE$.IntZero());
               int[] bd = b.data$mcI$sp();
               int adefault = a.array$mcI$sp().default$mcI$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               int[] ad = a.data$mcI$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.update$mcI$sp(j, adefault + bd[boff]);
                     boff += bstride;
                  }

                  result.update$mcI$sp(j, ad[i] + bd[boff]);
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.update$mcI$sp(j, adefault + bd[boff]);
                  boff += bstride;
                  ++j;
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_idempotent_Int_OpAdd())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Double_OpAdd_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(a.length(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
               double[] bd = b.data$mcD$sp();
               double adefault = a.array$mcD$sp().default$mcD$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               double[] ad = a.data$mcD$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.update$mcD$sp(j, adefault + bd[boff]);
                     boff += bstride;
                  }

                  result.update$mcD$sp(j, ad[i] + bd[boff]);
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.update$mcD$sp(j, adefault + bd[boff]);
                  boff += bstride;
                  ++j;
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_idempotent_Double_OpAdd())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Float_OpAdd_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mFc$sp(a.length(), .MODULE$.Float(), Zero$.MODULE$.FloatZero());
               float[] bd = b.data$mcF$sp();
               float adefault = a.array$mcF$sp().default$mcF$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               float[] ad = a.data$mcF$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.update$mcF$sp(j, adefault + bd[boff]);
                     boff += bstride;
                  }

                  result.update$mcF$sp(j, ad[i] + bd[boff]);
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.update$mcF$sp(j, adefault + bd[boff]);
                  boff += bstride;
                  ++j;
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_idempotent_Float_OpAdd())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Long_OpAdd_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mJc$sp(a.length(), .MODULE$.Long(), Zero$.MODULE$.LongZero());
               long[] bd = b.data$mcJ$sp();
               long adefault = a.array$mcJ$sp().default$mcJ$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               long[] ad = a.data$mcJ$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.update$mcJ$sp(j, adefault + bd[boff]);
                     boff += bstride;
                  }

                  result.update$mcJ$sp(j, ad[i] + bd[boff]);
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.update$mcJ$sp(j, adefault + bd[boff]);
                  boff += bstride;
                  ++j;
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_idempotent_Long_OpAdd())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Int_OpSub_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(a.length(), .MODULE$.Int(), Zero$.MODULE$.IntZero());
               int[] bd = b.data$mcI$sp();
               int adefault = a.array$mcI$sp().default$mcI$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               int[] ad = a.data$mcI$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.update$mcI$sp(j, adefault - bd[boff]);
                     boff += bstride;
                  }

                  result.update$mcI$sp(j, ad[i] - bd[boff]);
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.update$mcI$sp(j, adefault - bd[boff]);
                  boff += bstride;
                  ++j;
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_idempotent_Int_OpSub())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Double_OpSub_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(a.length(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
               double[] bd = b.data$mcD$sp();
               double adefault = a.array$mcD$sp().default$mcD$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               double[] ad = a.data$mcD$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.update$mcD$sp(j, adefault - bd[boff]);
                     boff += bstride;
                  }

                  result.update$mcD$sp(j, ad[i] - bd[boff]);
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.update$mcD$sp(j, adefault - bd[boff]);
                  boff += bstride;
                  ++j;
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_idempotent_Double_OpSub())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Float_OpSub_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mFc$sp(a.length(), .MODULE$.Float(), Zero$.MODULE$.FloatZero());
               float[] bd = b.data$mcF$sp();
               float adefault = a.array$mcF$sp().default$mcF$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               float[] ad = a.data$mcF$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.update$mcF$sp(j, adefault - bd[boff]);
                     boff += bstride;
                  }

                  result.update$mcF$sp(j, ad[i] - bd[boff]);
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.update$mcF$sp(j, adefault - bd[boff]);
                  boff += bstride;
                  ++j;
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_idempotent_Float_OpSub())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Long_OpSub_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mJc$sp(a.length(), .MODULE$.Long(), Zero$.MODULE$.LongZero());
               long[] bd = b.data$mcJ$sp();
               long adefault = a.array$mcJ$sp().default$mcJ$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               long[] ad = a.data$mcJ$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.update$mcJ$sp(j, adefault - bd[boff]);
                     boff += bstride;
                  }

                  result.update$mcJ$sp(j, ad[i] - bd[boff]);
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.update$mcJ$sp(j, adefault - bd[boff]);
                  boff += bstride;
                  ++j;
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_idempotent_Long_OpSub())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Int_OpSet_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(a.length(), .MODULE$.Int(), Zero$.MODULE$.IntZero());
               int[] bd = b.data$mcI$sp();
               int adefault = a.array$mcI$sp().default$mcI$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               int[] ad = a.data$mcI$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.update$mcI$sp(j, bd[boff]);
                     boff += bstride;
                  }

                  result.update$mcI$sp(j, bd[boff]);
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.update$mcI$sp(j, bd[boff]);
                  boff += bstride;
                  ++j;
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_Int_OpSet())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Double_OpSet_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(a.length(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
               double[] bd = b.data$mcD$sp();
               double adefault = a.array$mcD$sp().default$mcD$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               double[] ad = a.data$mcD$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.update$mcD$sp(j, bd[boff]);
                     boff += bstride;
                  }

                  result.update$mcD$sp(j, bd[boff]);
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.update$mcD$sp(j, bd[boff]);
                  boff += bstride;
                  ++j;
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_Double_OpSet())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Float_OpSet_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mFc$sp(a.length(), .MODULE$.Float(), Zero$.MODULE$.FloatZero());
               float[] bd = b.data$mcF$sp();
               float adefault = a.array$mcF$sp().default$mcF$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               float[] ad = a.data$mcF$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.update$mcF$sp(j, bd[boff]);
                     boff += bstride;
                  }

                  result.update$mcF$sp(j, bd[boff]);
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.update$mcF$sp(j, bd[boff]);
                  boff += bstride;
                  ++j;
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_Float_OpSet())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Long_OpSet_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mJc$sp(a.length(), .MODULE$.Long(), Zero$.MODULE$.LongZero());
               long[] bd = b.data$mcJ$sp();
               long adefault = a.array$mcJ$sp().default$mcJ$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               long[] ad = a.data$mcJ$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.update$mcJ$sp(j, bd[boff]);
                     boff += bstride;
                  }

                  result.update$mcJ$sp(j, bd[boff]);
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.update$mcJ$sp(j, bd[boff]);
                  boff += bstride;
                  ++j;
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_Long_OpSet())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Int_OpMod_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(a.length(), .MODULE$.Int(), Zero$.MODULE$.IntZero());
               int[] bd = b.data$mcI$sp();
               int adefault = a.array$mcI$sp().default$mcI$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               int[] ad = a.data$mcI$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.update$mcI$sp(j, adefault % bd[boff]);
                     boff += bstride;
                  }

                  result.update$mcI$sp(j, ad[i] % bd[boff]);
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.update$mcI$sp(j, adefault % bd[boff]);
                  boff += bstride;
                  ++j;
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_Int_OpMod())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Double_OpMod_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(a.length(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
               double[] bd = b.data$mcD$sp();
               double adefault = a.array$mcD$sp().default$mcD$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               double[] ad = a.data$mcD$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.update$mcD$sp(j, adefault % bd[boff]);
                     boff += bstride;
                  }

                  result.update$mcD$sp(j, ad[i] % bd[boff]);
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.update$mcD$sp(j, adefault % bd[boff]);
                  boff += bstride;
                  ++j;
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_Double_OpMod())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Float_OpMod_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mFc$sp(a.length(), .MODULE$.Float(), Zero$.MODULE$.FloatZero());
               float[] bd = b.data$mcF$sp();
               float adefault = a.array$mcF$sp().default$mcF$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               float[] ad = a.data$mcF$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.update$mcF$sp(j, adefault % bd[boff]);
                     boff += bstride;
                  }

                  result.update$mcF$sp(j, ad[i] % bd[boff]);
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.update$mcF$sp(j, adefault % bd[boff]);
                  boff += bstride;
                  ++j;
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_Float_OpMod())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Long_OpMod_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mJc$sp(a.length(), .MODULE$.Long(), Zero$.MODULE$.LongZero());
               long[] bd = b.data$mcJ$sp();
               long adefault = a.array$mcJ$sp().default$mcJ$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               long[] ad = a.data$mcJ$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.update$mcJ$sp(j, adefault % bd[boff]);
                     boff += bstride;
                  }

                  result.update$mcJ$sp(j, ad[i] % bd[boff]);
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.update$mcJ$sp(j, adefault % bd[boff]);
                  boff += bstride;
                  ++j;
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_Long_OpMod())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Int_OpPow_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(a.length(), .MODULE$.Int(), Zero$.MODULE$.IntZero());
               int[] bd = b.data$mcI$sp();
               int adefault = a.array$mcI$sp().default$mcI$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               int[] ad = a.data$mcI$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.update$mcI$sp(j, PowImplicits$.MODULE$.IntPow(adefault).pow(bd[boff]));
                     boff += bstride;
                  }

                  result.update$mcI$sp(j, PowImplicits$.MODULE$.IntPow(ad[i]).pow(bd[boff]));
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.update$mcI$sp(j, PowImplicits$.MODULE$.IntPow(adefault).pow(bd[boff]));
                  boff += bstride;
                  ++j;
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_Int_OpPow())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Double_OpPow_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(a.length(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
               double[] bd = b.data$mcD$sp();
               double adefault = a.array$mcD$sp().default$mcD$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               double[] ad = a.data$mcD$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.update$mcD$sp(j, PowImplicits$.MODULE$.DoublePow(adefault).pow(bd[boff]));
                     boff += bstride;
                  }

                  result.update$mcD$sp(j, PowImplicits$.MODULE$.DoublePow(ad[i]).pow(bd[boff]));
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.update$mcD$sp(j, PowImplicits$.MODULE$.DoublePow(adefault).pow(bd[boff]));
                  boff += bstride;
                  ++j;
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_Double_OpPow())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Float_OpPow_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mFc$sp(a.length(), .MODULE$.Float(), Zero$.MODULE$.FloatZero());
               float[] bd = b.data$mcF$sp();
               float adefault = a.array$mcF$sp().default$mcF$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               float[] ad = a.data$mcF$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.update$mcF$sp(j, PowImplicits$.MODULE$.FloatPow(adefault).pow(bd[boff]));
                     boff += bstride;
                  }

                  result.update$mcF$sp(j, PowImplicits$.MODULE$.FloatPow(ad[i]).pow(bd[boff]));
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.update$mcF$sp(j, PowImplicits$.MODULE$.FloatPow(adefault).pow(bd[boff]));
                  boff += bstride;
                  ++j;
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_Float_OpPow())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_Op_SV_DV_eq_DV_Long_OpPow_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mJc$sp(a.length(), .MODULE$.Long(), Zero$.MODULE$.LongZero());
               long[] bd = b.data$mcJ$sp();
               long adefault = a.array$mcJ$sp().default$mcJ$sp();
               int boff = b.offset();
               int asize = a.activeSize();
               int bstride = b.stride();
               long[] ad = a.data$mcJ$sp();
               int[] ai = a.index();
               int i = 0;

               int j;
               for(j = 0; i < asize; ++j) {
                  for(int nextBoff = b.offset() + ai[i] * bstride; boff < nextBoff; ++j) {
                     result.update$mcJ$sp(j, PowImplicits$.MODULE$.LongPow(adefault).pow(bd[boff]));
                     boff += bstride;
                  }

                  result.update$mcJ$sp(j, PowImplicits$.MODULE$.LongPow(ad[i]).pow(bd[boff]));
                  boff += b.stride();
                  ++i;
               }

               while(boff < bd.length) {
                  result.update$mcJ$sp(j, PowImplicits$.MODULE$.LongPow(adefault).pow(bd[boff]));
                  boff += bstride;
                  ++j;
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_Long_OpPow())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_OpMulInner_SV_DV_eq_T_Int_$eq(new UFunc.UImpl2() {
         // $FF: synthetic field
         private final SparseVector_DenseVector_Ops $outer;

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

         public int apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               return BoxesRunTime.unboxToInt(b.dot(a, this.$outer.castOps_V_V(scala..less.colon.less..MODULE$.refl(), scala..less.colon.less..MODULE$.refl(), breeze.gymnastics.NotGiven..MODULE$.neq(), this.$outer.impl_OpMulInner_V_V_eq_S_Int())));
            }
         }

         public {
            if (SparseVector_DenseVector_Ops.this == null) {
               throw null;
            } else {
               this.$outer = SparseVector_DenseVector_Ops.this;
               ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_OpMulInner_V_V_eq_S_Int())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
            }
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_OpMulInner_SV_DV_eq_T_Double_$eq(new UFunc.UImpl2() {
         // $FF: synthetic field
         private final SparseVector_DenseVector_Ops $outer;

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

         public double apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               return BoxesRunTime.unboxToDouble(b.dot(a, this.$outer.castOps_V_V(scala..less.colon.less..MODULE$.refl(), scala..less.colon.less..MODULE$.refl(), breeze.gymnastics.NotGiven..MODULE$.neq(), this.$outer.impl_OpMulInner_V_V_eq_S_Double())));
            }
         }

         public {
            if (SparseVector_DenseVector_Ops.this == null) {
               throw null;
            } else {
               this.$outer = SparseVector_DenseVector_Ops.this;
               ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_OpMulInner_V_V_eq_S_Double())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
            }
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_OpMulInner_SV_DV_eq_T_Float_$eq(new UFunc.UImpl2() {
         // $FF: synthetic field
         private final SparseVector_DenseVector_Ops $outer;

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

         public float apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               return BoxesRunTime.unboxToFloat(b.dot(a, this.$outer.castOps_V_V(scala..less.colon.less..MODULE$.refl(), scala..less.colon.less..MODULE$.refl(), breeze.gymnastics.NotGiven..MODULE$.neq(), this.$outer.impl_OpMulInner_V_V_eq_S_Float())));
            }
         }

         public {
            if (SparseVector_DenseVector_Ops.this == null) {
               throw null;
            } else {
               this.$outer = SparseVector_DenseVector_Ops.this;
               ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_OpMulInner_V_V_eq_S_Float())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
            }
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_OpMulInner_SV_DV_eq_T_Long_$eq(new UFunc.UImpl2() {
         // $FF: synthetic field
         private final SparseVector_DenseVector_Ops $outer;

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

         public long apply(final SparseVector a, final DenseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               return BoxesRunTime.unboxToLong(b.dot(a, this.$outer.castOps_V_V(scala..less.colon.less..MODULE$.refl(), scala..less.colon.less..MODULE$.refl(), breeze.gymnastics.NotGiven..MODULE$.neq(), this.$outer.impl_OpMulInner_V_V_eq_S_Long())));
            }
         }

         public {
            if (SparseVector_DenseVector_Ops.this == null) {
               throw null;
            } else {
               this.$outer = SparseVector_DenseVector_Ops.this;
               ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVector_DenseVector_Ops.this.impl_OpMulInner_V_V_eq_S_Long())).register(this, .MODULE$.apply(SparseVector.class), .MODULE$.apply(DenseVector.class));
            }
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_OpMulMatrix_SV_DVt_eq_SMT_Int_$eq(new UFunc.UImpl2() {
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

         public CSCMatrix apply(final SparseVector a, final Transpose b) {
            int sizeHint = a.activeSize() * ((Vector)b.inner()).size();
            CSCMatrix.Builder res = new CSCMatrix$Builder$mcI$sp(a.size(), ((Vector)b.inner()).size(), sizeHint, .MODULE$.Int(), Semiring$.MODULE$.semiringInt(), Zero$.MODULE$.IntZero());
            ((DenseVector)b.inner()).activeIterator().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$apply$1(check$ifrefutable$1))).foreach((x$2) -> {
               $anonfun$apply$2(a, res, x$2);
               return BoxedUnit.UNIT;
            });
            return res.result$mcI$sp(true, true);
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$1(final Tuple2 check$ifrefutable$1) {
            boolean var1;
            if (check$ifrefutable$1 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$3(final Tuple2 check$ifrefutable$2) {
            boolean var1;
            if (check$ifrefutable$2 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$4(final CSCMatrix.Builder res$1, final int j$1, final int bValue$1, final Tuple2 x$1) {
            if (x$1 != null) {
               int i = x$1._1$mcI$sp();
               int aValue = x$1._2$mcI$sp();
               res$1.add$mcI$sp(i, j$1, aValue * bValue$1);
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$1);
            }
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$2(final SparseVector a$1, final CSCMatrix.Builder res$1, final Tuple2 x$2) {
            if (x$2 != null) {
               int j = x$2._1$mcI$sp();
               int bValue = x$2._2$mcI$sp();
               a$1.activeIterator().withFilter((check$ifrefutable$2) -> BoxesRunTime.boxToBoolean($anonfun$apply$3(check$ifrefutable$2))).foreach((x$1) -> {
                  $anonfun$apply$4(res$1, j, bValue, x$1);
                  return BoxedUnit.UNIT;
               });
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$2);
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_OpMulMatrix_SV_DVt_eq_SMT_Double_$eq(new UFunc.UImpl2() {
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

         public CSCMatrix apply(final SparseVector a, final Transpose b) {
            int sizeHint = a.activeSize() * ((Vector)b.inner()).size();
            CSCMatrix.Builder res = new CSCMatrix$Builder$mcD$sp(a.size(), ((Vector)b.inner()).size(), sizeHint, .MODULE$.Double(), Semiring$.MODULE$.semiringD(), Zero$.MODULE$.DoubleZero());
            ((DenseVector)b.inner()).activeIterator().withFilter((check$ifrefutable$3) -> BoxesRunTime.boxToBoolean($anonfun$apply$5(check$ifrefutable$3))).foreach((x$4) -> {
               $anonfun$apply$6(a, res, x$4);
               return BoxedUnit.UNIT;
            });
            return res.result$mcD$sp(true, true);
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$5(final Tuple2 check$ifrefutable$3) {
            boolean var1;
            if (check$ifrefutable$3 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$7(final Tuple2 check$ifrefutable$4) {
            boolean var1;
            if (check$ifrefutable$4 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$8(final CSCMatrix.Builder res$2, final int j$2, final double bValue$2, final Tuple2 x$3) {
            if (x$3 != null) {
               int i = x$3._1$mcI$sp();
               double aValue = x$3._2$mcD$sp();
               res$2.add$mcD$sp(i, j$2, aValue * bValue$2);
               BoxedUnit var5 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$3);
            }
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$6(final SparseVector a$2, final CSCMatrix.Builder res$2, final Tuple2 x$4) {
            if (x$4 != null) {
               int j = x$4._1$mcI$sp();
               double bValue = x$4._2$mcD$sp();
               a$2.activeIterator().withFilter((check$ifrefutable$4) -> BoxesRunTime.boxToBoolean($anonfun$apply$7(check$ifrefutable$4))).foreach((x$3) -> {
                  $anonfun$apply$8(res$2, j, bValue, x$3);
                  return BoxedUnit.UNIT;
               });
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$4);
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_OpMulMatrix_SV_DVt_eq_SMT_Float_$eq(new UFunc.UImpl2() {
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

         public CSCMatrix apply(final SparseVector a, final Transpose b) {
            int sizeHint = a.activeSize() * ((Vector)b.inner()).size();
            CSCMatrix.Builder res = new CSCMatrix$Builder$mcF$sp(a.size(), ((Vector)b.inner()).size(), sizeHint, .MODULE$.Float(), Semiring$.MODULE$.semiringFloat(), Zero$.MODULE$.FloatZero());
            ((DenseVector)b.inner()).activeIterator().withFilter((check$ifrefutable$5) -> BoxesRunTime.boxToBoolean($anonfun$apply$9(check$ifrefutable$5))).foreach((x$6) -> {
               $anonfun$apply$10(a, res, x$6);
               return BoxedUnit.UNIT;
            });
            return res.result$mcF$sp(true, true);
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$9(final Tuple2 check$ifrefutable$5) {
            boolean var1;
            if (check$ifrefutable$5 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$11(final Tuple2 check$ifrefutable$6) {
            boolean var1;
            if (check$ifrefutable$6 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$12(final CSCMatrix.Builder res$3, final int j$3, final float bValue$3, final Tuple2 x$5) {
            if (x$5 != null) {
               int i = x$5._1$mcI$sp();
               float aValue = BoxesRunTime.unboxToFloat(x$5._2());
               res$3.add$mcF$sp(i, j$3, aValue * bValue$3);
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$5);
            }
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$10(final SparseVector a$3, final CSCMatrix.Builder res$3, final Tuple2 x$6) {
            if (x$6 != null) {
               int j = x$6._1$mcI$sp();
               float bValue = BoxesRunTime.unboxToFloat(x$6._2());
               a$3.activeIterator().withFilter((check$ifrefutable$6) -> BoxesRunTime.boxToBoolean($anonfun$apply$11(check$ifrefutable$6))).foreach((x$5) -> {
                  $anonfun$apply$12(res$3, j, bValue, x$5);
                  return BoxedUnit.UNIT;
               });
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$6);
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_OpMulMatrix_SV_DVt_eq_SMT_Long_$eq(new UFunc.UImpl2() {
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

         public CSCMatrix apply(final SparseVector a, final Transpose b) {
            int sizeHint = a.activeSize() * ((Vector)b.inner()).size();
            CSCMatrix.Builder res = new CSCMatrix$Builder$mcJ$sp(a.size(), ((Vector)b.inner()).size(), sizeHint, .MODULE$.Long(), Semiring$.MODULE$.semiringLong(), Zero$.MODULE$.LongZero());
            ((DenseVector)b.inner()).activeIterator().withFilter((check$ifrefutable$7) -> BoxesRunTime.boxToBoolean($anonfun$apply$13(check$ifrefutable$7))).foreach((x$8) -> {
               $anonfun$apply$14(a, res, x$8);
               return BoxedUnit.UNIT;
            });
            return res.result$mcJ$sp(true, true);
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$13(final Tuple2 check$ifrefutable$7) {
            boolean var1;
            if (check$ifrefutable$7 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$15(final Tuple2 check$ifrefutable$8) {
            boolean var1;
            if (check$ifrefutable$8 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$16(final CSCMatrix.Builder res$4, final int j$4, final long bValue$4, final Tuple2 x$7) {
            if (x$7 != null) {
               int i = x$7._1$mcI$sp();
               long aValue = x$7._2$mcJ$sp();
               res$4.add$mcJ$sp(i, j$4, aValue * bValue$4);
               BoxedUnit var5 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$7);
            }
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$14(final SparseVector a$4, final CSCMatrix.Builder res$4, final Tuple2 x$8) {
            if (x$8 != null) {
               int j = x$8._1$mcI$sp();
               long bValue = x$8._2$mcJ$sp();
               a$4.activeIterator().withFilter((check$ifrefutable$8) -> BoxesRunTime.boxToBoolean($anonfun$apply$15(check$ifrefutable$8))).foreach((x$7) -> {
                  $anonfun$apply$16(res$4, j, bValue, x$7);
                  return BoxedUnit.UNIT;
               });
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$8);
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$SparseVector_DenseVector_Ops$_setter_$impl_OpMulMatrix_SV_DVt_eq_SMT_Complex_$eq(new UFunc.UImpl2() {
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

         public CSCMatrix apply(final SparseVector a, final Transpose b) {
            int sizeHint = a.activeSize() * ((Vector)b.inner()).size();
            CSCMatrix.Builder res = new CSCMatrix.Builder(a.size(), ((Vector)b.inner()).size(), sizeHint, .MODULE$.apply(Complex.class), Complex.scalar$.MODULE$, Complex$.MODULE$.ComplexZero());
            ((DenseVector)b.inner()).activeIterator().withFilter((check$ifrefutable$9) -> BoxesRunTime.boxToBoolean($anonfun$apply$17(check$ifrefutable$9))).foreach((x$10) -> {
               $anonfun$apply$18(a, res, x$10);
               return BoxedUnit.UNIT;
            });
            return res.result(true, true);
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$17(final Tuple2 check$ifrefutable$9) {
            boolean var1;
            if (check$ifrefutable$9 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$19(final Tuple2 check$ifrefutable$10) {
            boolean var1;
            if (check$ifrefutable$10 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$20(final CSCMatrix.Builder res$5, final int j$5, final Complex bValue$5, final Tuple2 x$9) {
            if (x$9 != null) {
               int i = x$9._1$mcI$sp();
               Complex aValue = (Complex)x$9._2();
               res$5.add(i, j$5, aValue.$times(bValue$5));
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$9);
            }
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$18(final SparseVector a$5, final CSCMatrix.Builder res$5, final Tuple2 x$10) {
            if (x$10 != null) {
               int j = x$10._1$mcI$sp();
               Complex bValue = (Complex)x$10._2();
               a$5.activeIterator().withFilter((check$ifrefutable$10) -> BoxesRunTime.boxToBoolean($anonfun$apply$19(check$ifrefutable$10))).foreach((x$9) -> {
                  $anonfun$apply$20(res$5, j, bValue, x$9);
                  return BoxedUnit.UNIT;
               });
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$10);
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
   }
}
