package breeze.linalg.operators;

import breeze.generic.UFunc;
import breeze.linalg.SparseVector;
import breeze.linalg.SparseVector$;
import breeze.linalg.SparseVector$mcD$sp;
import breeze.linalg.SparseVector$mcF$sp;
import breeze.linalg.SparseVector$mcI$sp;
import breeze.linalg.SparseVector$mcJ$sp;
import breeze.linalg.Transpose;
import breeze.linalg.Vector;
import breeze.linalg.VectorBuilder;
import breeze.linalg.VectorBuilder$;
import breeze.linalg.VectorBuilder$mcD$sp;
import breeze.linalg.VectorBuilder$mcF$sp;
import breeze.linalg.VectorBuilder$mcI$sp;
import breeze.linalg.VectorBuilder$mcJ$sp;
import breeze.linalg.support.CanZipMapKeyValues;
import breeze.linalg.support.CanZipMapValues;
import breeze.math.PowImplicits$;
import breeze.math.Ring;
import breeze.math.Semiring;
import breeze.math.Semiring$;
import breeze.storage.Zero;
import breeze.storage.Zero$;
import breeze.util.ArrayUtil$;
import java.util.Arrays;
import scala.Function2;
import scala.Function3;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015\u0005ga\u00028p!\u0003\r\tA\u001e\u0005\b\u0003\u001f\u0001A\u0011AA\t\u0011\u001d\tI\u0002\u0001C\u0002\u00037A\u0011\"!&\u0001\u0005\u0004%\u0019!a&\t\u0013\u0005E\u0006A1A\u0005\u0004\u0005M\u0006\"CA`\u0001\t\u0007I1AAa\u0011%\ti\r\u0001b\u0001\n\u0007\ty\rC\u0005\u0002\\\u0002\u0011\r\u0011b\u0001\u0002^\"I\u0011q\u001d\u0001C\u0002\u0013\r\u0011\u0011\u001e\u0005\n\u0003[\u0004!\u0019!C\u0002\u0003_D\u0011\"a=\u0001\u0005\u0004%\u0019!!>\t\u000f\u0005e\b\u0001b\u0001\u0002|\"9!q\u0004\u0001\u0005\u0004\t\u0005\u0002\"\u0003B\u001f\u0001\t\u0007I1\u0001B \u0011%\u0011I\u0005\u0001b\u0001\n\u0007\u0011Y\u0005C\u0005\u0003P\u0001\u0011\r\u0011b\u0001\u0003R!I!Q\u000b\u0001C\u0002\u0013\r!q\u000b\u0005\n\u00057\u0002!\u0019!C\u0002\u0005;B\u0011Ba\u001a\u0001\u0005\u0004%\u0019A!\u001b\t\u0013\t5\u0004A1A\u0005\u0004\t=\u0004\"\u0003B:\u0001\t\u0007I1\u0001B;\u0011%\u0011I\b\u0001b\u0001\n\u0007\u0011Y\bC\u0005\u0003\u0006\u0002\u0011\r\u0011b\u0001\u0003\b\"I!1\u0012\u0001C\u0002\u0013\r!Q\u0012\u0005\n\u0005#\u0003!\u0019!C\u0002\u0005'C\u0011Ba&\u0001\u0005\u0004%\u0019A!'\t\u0013\t\r\u0006A1A\u0005\u0004\t\u0015\u0006\"\u0003BU\u0001\t\u0007I1\u0001BV\u0011%\u0011y\u000b\u0001b\u0001\n\u0007\u0011\t\fC\u0005\u00036\u0002\u0011\r\u0011b\u0001\u00038\"I!\u0011\u0019\u0001C\u0002\u0013\r!1\u0019\u0005\n\u0005\u000f\u0004!\u0019!C\u0002\u0005\u0013D\u0011B!4\u0001\u0005\u0004%\u0019Aa4\t\u0013\tM\u0007A1A\u0005\u0004\tU\u0007\"\u0003Bp\u0001\t\u0007I1\u0001Bq\u0011%\u00119\u000f\u0001b\u0001\n\u0007\u0011I\u000fC\u0005\u0003p\u0002\u0011\r\u0011b\u0001\u0003r\"I!q\u001f\u0001C\u0002\u0013\r!\u0011 \u0005\n\u0005{\u0004!\u0019!C\u0002\u0005\u007fD\u0011ba\u0001\u0001\u0005\u0004%\u0019a!\u0002\t\u0013\r%\u0001A1A\u0005\u0004\r-\u0001\"CB\b\u0001\t\u0007I1AB\t\u0011%\u0019)\u0002\u0001b\u0001\n\u0007\u00199\u0002C\u0005\u0004\u001c\u0001\u0011\r\u0011b\u0001\u0004\u001e!I1\u0011\u0005\u0001C\u0002\u0013\r11\u0005\u0005\n\u0007O\u0001!\u0019!C\u0002\u0007SA\u0011b!\f\u0001\u0005\u0004%\u0019aa\f\t\u0013\rM\u0002A1A\u0005\u0004\rU\u0002\"CB\u001d\u0001\t\u0007I1AB\u001e\u0011%\u0019y\u0004\u0001b\u0001\n\u0007\u0019\t\u0005C\u0005\u0004F\u0001\u0011\r\u0011b\u0001\u0004H!I11\n\u0001C\u0002\u0013\r1Q\n\u0005\n\u0007#\u0002!\u0019!C\u0002\u0007'B\u0011ba\u0016\u0001\u0005\u0004%\u0019a!\u0017\t\u0013\ru\u0003A1A\u0005\u0004\r}\u0003\"CB2\u0001\t\u0007I1AB3\u0011%\u0019I\u0007\u0001b\u0001\n\u0007\u0019Y\u0007C\u0005\u0004p\u0001\u0011\r\u0011b\u0001\u0004r!I1Q\u000f\u0001C\u0002\u0013\r1q\u000f\u0005\n\u0007w\u0002!\u0019!C\u0002\u0007{B\u0011b!!\u0001\u0005\u0004%\u0019aa!\t\u0013\r\u001d\u0005A1A\u0005\u0004\r%\u0005\"CBG\u0001\t\u0007I1ABH\u0011%\u0019\u0019\n\u0001b\u0001\n\u0007\u0019)\nC\u0005\u0004\u001a\u0002\u0011\r\u0011b\u0001\u0004\u001c\"I1q\u0014\u0001C\u0002\u0013\r1\u0011\u0015\u0005\n\u0007K\u0003!\u0019!C\u0002\u0007OC\u0011ba+\u0001\u0005\u0004%\u0019a!,\t\u0013\rE\u0006A1A\u0005\u0004\rM\u0006\"CB\\\u0001\t\u0007I1AB]\u0011%\u0019i\f\u0001b\u0001\n\u0007\u0019y\fC\u0005\u0004D\u0002\u0011\r\u0011b\u0001\u0004F\"I1\u0011\u001a\u0001C\u0002\u0013\r11\u001a\u0005\n\u0007\u001f\u0004!\u0019!C\u0002\u0007#D\u0011b!6\u0001\u0005\u0004%\u0019aa6\t\u0013\rm\u0007A1A\u0005\u0004\ru\u0007\"CBq\u0001\t\u0007I1ABr\u0011%\u00199\u000f\u0001b\u0001\n\u0007\u0019I\u000fC\u0005\u0004t\u0002\u0011\r\u0011b\u0001\u0004v\"I1\u0011 \u0001C\u0002\u0013\r11 \u0005\n\u0007\u007f\u0004!\u0019!C\u0002\t\u0003A\u0011\u0002\"\u0002\u0001\u0005\u0004%\u0019\u0001b\u0002\t\u0013\u0011E\u0001A1A\u0005\u0004\u0011M\u0001\"\u0003C\f\u0001\t\u0007I1\u0001C\r\u0011%!i\u0002\u0001b\u0001\n\u0007!y\u0002C\u0004\u0005$\u0001!\u0019\u0001\"\n\t\u0013\u0011\r\u0003A1A\u0005\u0004\u0011\u0015\u0003\"\u0003C*\u0001\t\u0007I1\u0001C+\u0011%!I\u0006\u0001b\u0001\n\u0007!Y\u0006C\u0005\u0005`\u0001\u0011\r\u0011b\u0001\u0005b\u00191AQ\r\u0001\u0001\tOB!\u0002\"3[\u0005\u0007\u0005\u000b1\u0002Cf\u0011)!iM\u0017B\u0002B\u0003-Aq\u001a\u0005\u000b\t#T&1!Q\u0001\f\u0011M\u0007b\u0002Ck5\u0012\u0005Aq\u001b\u0005\b\tKTF\u0011\u0001Ct\u0011\u001d!iO\u0017C\u0001\t_Dq!b\u0001\u0001\t\u0007))\u0001C\u0005\u0006&\u0001\u0011\r\u0011b\u0001\u0006(!IQ1\u0006\u0001C\u0002\u0013\rQQ\u0006\u0005\n\u000bc\u0001!\u0019!C\u0002\u000bg1a!b\u000e\u0001\u0001\u0015e\u0002BCC6K\n\r\t\u0015a\u0003\u0006n!QQqN3\u0003\u0004\u0003\u0006Y!\"\u001d\t\u0015\u0015MTMaA!\u0002\u0017))\bC\u0004\u0005V\u0016$\t!b\u001e\t\u000f\u0011\u0015X\r\"\u0001\u0006\u0004\"9AQ^3\u0005\u0002\u0015\u001d\u0005bBCKK\u0012\u0005Sq\u0013\u0005\b\u000b?\u0003A1ACQ\u0005U\u0019\u0006/\u0019:tKZ+7\r^8s\u000bb\u0004\u0018M\u001c3PaNT!\u0001]9\u0002\u0013=\u0004XM]1u_J\u001c(B\u0001:t\u0003\u0019a\u0017N\\1mO*\tA/\u0001\u0004ce\u0016,'0Z\u0002\u0001'\u001d\u0001q/`A\u0002\u0003\u0013\u0001\"\u0001_>\u000e\u0003eT\u0011A_\u0001\u0006g\u000e\fG.Y\u0005\u0003yf\u0014a!\u00118z%\u00164\u0007C\u0001@\u0000\u001b\u0005y\u0017bAA\u0001_\nIa+Z2u_J|\u0005o\u001d\t\u0004}\u0006\u0015\u0011bAA\u0004_\nY2\u000b]1sg\u00164Vm\u0019;pe~#UM\\:f\u001b\u0006$(/\u001b=PaN\u00042A`A\u0006\u0013\r\tia\u001c\u0002\u0018'B\f'o]3WK\u000e$xN]0HK:,'/[2PaN\fa\u0001J5oSR$CCAA\n!\rA\u0018QC\u0005\u0004\u0003/I(\u0001B+oSR\fq\u0003\\5gi\u000e\u001b6i\u00149U_N3&/\u00198ta>\u001cXm\u00149\u0016\u0015\u0005u\u0011qGA0\u0003\u0017\n)\u0007\u0006\u0005\u0002 \u0005%\u0014QOAC!1\t\t#!\f\u00024\u0005%\u0013qJA2\u001d\u0011\t\u0019#!\u000b\u000e\u0005\u0005\u0015\"bAA\u0014g\u00069q-\u001a8fe&\u001c\u0017\u0002BA\u0016\u0003K\tQ!\u0016$v]\u000eLA!a\f\u00022\t1Q+S7qYJRA!a\u000b\u0002&A!\u0011QGA\u001c\u0019\u0001!q!!\u000f\u0003\u0005\u0004\tYDA\u0002UC\u001e\fB!!\u0010\u0002DA\u0019\u00010a\u0010\n\u0007\u0005\u0005\u0013PA\u0004O_RD\u0017N\\4\u0011\u0007a\f)%C\u0002\u0002He\u00141!\u00118z!\u0011\t)$a\u0013\u0005\u000f\u00055#A1\u0001\u0002<\t\u0019A\nS*\u0011\r\u0005E\u00131KA,\u001b\u0005\t\u0018bAA+c\nIAK]1ogB|7/\u001a\t\u0007\u0003#\nI&!\u0018\n\u0007\u0005m\u0013O\u0001\u0007Ta\u0006\u00148/\u001a,fGR|'\u000f\u0005\u0003\u00026\u0005}CaBA1\u0005\t\u0007\u00111\b\u0002\u0002-B!\u0011QGA3\t\u001d\t9G\u0001b\u0001\u0003w\u0011\u0011A\u0015\u0005\b\u0003W\u0012\u00019AA7\u0003\ty\u0007\u000f\u0005\u0007\u0002\"\u00055\u00121GA%\u0003_\n\u0019\u0007\u0005\u0004\u0002R\u0005E\u0014QL\u0005\u0004\u0003g\n(!C\"T\u00076\u000bGO]5y\u0011\u001d\t9H\u0001a\u0002\u0003s\nAA_3s_B1\u00111PAA\u0003;j!!! \u000b\u0007\u0005}4/A\u0004ti>\u0014\u0018mZ3\n\t\u0005\r\u0015Q\u0010\u0002\u00055\u0016\u0014x\u000eC\u0004\u0002\b\n\u0001\u001d!!#\u0002\u0005\r$\bCBAF\u0003#\u000bi&\u0004\u0002\u0002\u000e*\u0019\u0011qR=\u0002\u000fI,g\r\\3di&!\u00111SAG\u0005!\u0019E.Y:t)\u0006<\u0017!H5na2|v\n]0T-~\u001bfkX3r?N3v,\u00138u?>\u0003\u0018\t\u001a3\u0016\u0005\u0005e\u0005CCAN\u0003C\u000bI+!+\u0002*:\u0019a0!(\n\u0007\u0005}u.A\u0003Pa\u0006#G-\u0003\u0003\u0002$\u0006\u0015&!B%na2\u0014\u0014\u0002BAT\u0003K\u0011Q!\u0016$v]\u000e\u0004b!!\u0015\u0002Z\u0005-\u0006c\u0001=\u0002.&\u0019\u0011qV=\u0003\u0007%sG/\u0001\u0011j[Bdwl\u00149`'Z{6KV0fc~\u001bfk\u0018#pk\ndWmX(q\u0003\u0012$WCAA[!)\tY*!)\u00028\u0006]\u0016q\u0017\t\u0007\u0003#\nI&!/\u0011\u0007a\fY,C\u0002\u0002>f\u0014a\u0001R8vE2,\u0017aH5na2|v\n]0T-~\u001bfkX3r?N3vL\u00127pCR|v\n]!eIV\u0011\u00111\u0019\t\u000b\u00037\u000b\t+!2\u0002F\u0006\u0015\u0007CBA)\u00033\n9\rE\u0002y\u0003\u0013L1!a3z\u0005\u00151En\\1u\u0003yIW\u000e\u001d7`\u001fB|6KV0T-~+\u0017oX*W?2{gnZ0Pa\u0006#G-\u0006\u0002\u0002RBQ\u00111TAQ\u0003'\f\u0019.a5\u0011\r\u0005E\u0013\u0011LAk!\rA\u0018q[\u0005\u0004\u00033L(\u0001\u0002'p]\u001e\fQ$[7qY~{\u0005oX*W?N3v,Z9`'Z{\u0016J\u001c;`\u001fB\u001cVOY\u000b\u0003\u0003?\u0004\"\"!9\u0002\"\u0006%\u0016\u0011VAU\u001d\rq\u00181]\u0005\u0004\u0003K|\u0017!B(q'V\u0014\u0017\u0001I5na2|v\n]0T-~\u001bfkX3r?N3v\fR8vE2,wl\u00149Tk\n,\"!a;\u0011\u0015\u0005\u0005\u0018\u0011UA\\\u0003o\u000b9,A\u0010j[Bdwl\u00149`'Z{6KV0fc~\u001bfk\u0018$m_\u0006$xl\u00149Tk\n,\"!!=\u0011\u0015\u0005\u0005\u0018\u0011UAc\u0003\u000b\f)-\u0001\u0010j[Bdwl\u00149`'Z{6KV0fc~\u001bfk\u0018'p]\u001e|v\n]*vEV\u0011\u0011q\u001f\t\u000b\u0003C\f\t+a5\u0002T\u0006M\u0017!F5na2\u001cVOY(q?N3vl\u0015,`KF|6KV\u000b\u0005\u0003{\u0014)\u0001\u0006\u0004\u0002\u0000\n%!\u0011\u0004\t\u000b\u0003C\f\tK!\u0001\u0003\u0002\t\u0005\u0001CBA)\u00033\u0012\u0019\u0001\u0005\u0003\u00026\t\u0015Aa\u0002B\u0004\u0017\t\u0007\u00111\b\u0002\u0002)\"I!1B\u0006\u0002\u0002\u0003\u000f!QB\u0001\u000bKZLG-\u001a8dK\u0012\n\u0004C\u0002B\b\u0005+\u0011\u0019!\u0004\u0002\u0003\u0012)\u0019!1C:\u0002\t5\fG\u000f[\u0005\u0005\u0005/\u0011\tB\u0001\u0003SS:<\u0007\"\u0003B\u000e\u0017\u0005\u0005\t9\u0001B\u000f\u0003))g/\u001b3f]\u000e,GE\r\t\u0007\u0003\u0017\u000b\tJa\u0001\u0002+%l\u0007\u000f\\!eI>\u0003xl\u0015,`'Z{V-]0T-V!!1\u0005B\u0016)\u0019\u0011)C!\f\u00038AQ\u00111TAQ\u0005O\u00119Ca\n\u0011\r\u0005E\u0013\u0011\fB\u0015!\u0011\t)Da\u000b\u0005\u000f\t\u001dAB1\u0001\u0002<!I!q\u0006\u0007\u0002\u0002\u0003\u000f!\u0011G\u0001\u000bKZLG-\u001a8dK\u0012\u001a\u0004C\u0002B\b\u0005g\u0011I#\u0003\u0003\u00036\tE!\u0001C*f[&\u0014\u0018N\\4\t\u0013\teB\"!AA\u0004\tm\u0012AC3wS\u0012,gnY3%iA1\u00111RAI\u0005S\t\u0001%[7qY~{\u0005/T;m'\u000e\fG.\u0019:`'Z{6KV0fc~\u001bfkX%oiV\u0011!\u0011\t\t\u000b\u0005\u0007\n\t+!+\u0002*\u0006%fb\u0001@\u0003F%\u0019!qI8\u0002\u0017=\u0003X*\u001e7TG\u0006d\u0017M]\u0001$S6\u0004HnX(q\u001bVd7kY1mCJ|6KV0T-~+\u0017oX*W?\u0012{WO\u00197f+\t\u0011i\u0005\u0005\u0006\u0003D\u0005\u0005\u0016qWA\\\u0003o\u000b!%[7qY~{\u0005/T;m'\u000e\fG.\u0019:`'Z{6KV0fc~\u001bfk\u0018$m_\u0006$XC\u0001B*!)\u0011\u0019%!)\u0002F\u0006\u0015\u0017QY\u0001\"S6\u0004HnX(q\u001bVd7kY1mCJ|6KV0T-~+\u0017oX*W?2{gnZ\u000b\u0003\u00053\u0002\"Ba\u0011\u0002\"\u0006M\u00171[Aj\u0003uIW\u000e\u001d7`\u001fB|6KV0T-~+\u0017oX*W?&sGoX(q\t&4XC\u0001B0!)\u0011\t'!)\u0002*\u0006%\u0016\u0011\u0016\b\u0004}\n\r\u0014b\u0001B3_\u0006)q\n\u001d#jm\u0006\u0001\u0013.\u001c9m?>\u0003xl\u0015,`'Z{V-]0T-~#u.\u001e2mK~{\u0005\u000fR5w+\t\u0011Y\u0007\u0005\u0006\u0003b\u0005\u0005\u0016qWA\\\u0003o\u000bq$[7qY~{\u0005oX*W?N3v,Z9`'Z{f\t\\8bi~{\u0005\u000fR5w+\t\u0011\t\b\u0005\u0006\u0003b\u0005\u0005\u0016QYAc\u0003\u000b\fa$[7qY~{\u0005oX*W?N3v,Z9`'Z{Fj\u001c8h?>\u0003H)\u001b<\u0016\u0005\t]\u0004C\u0003B1\u0003C\u000b\u0019.a5\u0002T\u0006i\u0012.\u001c9m?>\u0003xl\u0015,`'Z{V-]0T-~Ke\u000e^0PaN+G/\u0006\u0002\u0003~AQ!qPAQ\u0003S\u000bI+!+\u000f\u0007y\u0014\t)C\u0002\u0003\u0004>\fQa\u00149TKR\f\u0001%[7qY~{\u0005oX*W?N3v,Z9`'Z{Fi\\;cY\u0016|v\n]*fiV\u0011!\u0011\u0012\t\u000b\u0005\u007f\n\t+a.\u00028\u0006]\u0016aH5na2|v\n]0T-~\u001bfkX3r?N3vL\u00127pCR|v\n]*fiV\u0011!q\u0012\t\u000b\u0005\u007f\n\t+!2\u0002F\u0006\u0015\u0017AH5na2|v\n]0T-~\u001bfkX3r?N3v\fT8oO~{\u0005oU3u+\t\u0011)\n\u0005\u0006\u0003\u0000\u0005\u0005\u00161[Aj\u0003'\fQ$[7qY~{\u0005oX*W?N3v,Z9`'Z{\u0016J\u001c;`\u001fBlu\u000eZ\u000b\u0003\u00057\u0003\"B!(\u0002\"\u0006%\u0016\u0011VAU\u001d\rq(qT\u0005\u0004\u0005C{\u0017!B(q\u001b>$\u0017\u0001I5na2|v\n]0T-~\u001bfkX3r?N3v\fR8vE2,wl\u00149N_\u0012,\"Aa*\u0011\u0015\tu\u0015\u0011UA\\\u0003o\u000b9,A\u0010j[Bdwl\u00149`'Z{6KV0fc~\u001bfk\u0018$m_\u0006$xl\u00149N_\u0012,\"A!,\u0011\u0015\tu\u0015\u0011UAc\u0003\u000b\f)-\u0001\u0010j[Bdwl\u00149`'Z{6KV0fc~\u001bfk\u0018'p]\u001e|v\n]'pIV\u0011!1\u0017\t\u000b\u0005;\u000b\t+a5\u0002T\u0006M\u0017!H5na2|v\n]0T-~\u001bfkX3r?N3v,\u00138u?>\u0003\bk\\<\u0016\u0005\te\u0006C\u0003B^\u0003C\u000bI+!+\u0002*:\u0019aP!0\n\u0007\t}v.A\u0003PaB{w/\u0001\u0011j[Bdwl\u00149`'Z{6KV0fc~\u001bfk\u0018#pk\ndWmX(q!><XC\u0001Bc!)\u0011Y,!)\u00028\u0006]\u0016qW\u0001 S6\u0004HnX(q?N3vl\u0015,`KF|6KV0GY>\fGoX(q!><XC\u0001Bf!)\u0011Y,!)\u0002F\u0006\u0015\u0017QY\u0001\u001fS6\u0004HnX(q?N3vl\u0015,`KF|6KV0M_:<wl\u00149Q_^,\"A!5\u0011\u0015\tm\u0016\u0011UAj\u0003'\f\u0019.\u0001\u000fj[Bdwl\u00149`'Z{fkX3r?N3v,\u00138u?>\u0003H)\u001b<\u0016\u0005\t]\u0007C\u0003B1\u0003C\u000bIK!7\u0002*B1\u0011\u0011\u000bBn\u0003WK1A!8r\u0005\u00191Vm\u0019;pe\u0006y\u0012.\u001c9m?>\u0003xl\u0015,`-~+\u0017oX*W?\u0012{WO\u00197f?>\u0003H)\u001b<\u0016\u0005\t\r\bC\u0003B1\u0003C\u000b9L!:\u00028B1\u0011\u0011\u000bBn\u0003s\u000ba$[7qY~{\u0005oX*W?Z{V-]0T-~3En\\1u?>\u0003H)\u001b<\u0016\u0005\t-\bC\u0003B1\u0003C\u000b)M!<\u0002FB1\u0011\u0011\u000bBn\u0003\u000f\fQ$[7qY~{\u0005oX*W?Z{V-]0T-~cuN\\4`\u001fB$\u0015N^\u000b\u0003\u0005g\u0004\"B!\u0019\u0002\"\u0006M'Q_Aj!\u0019\t\tFa7\u0002V\u0006a\u0012.\u001c9m?>\u0003xl\u0015,`-~+\u0017oX*W?&sGoX(q'\u0016$XC\u0001B~!)\u0011y(!)\u0002*\ne\u0017\u0011V\u0001 S6\u0004HnX(q?N3vLV0fc~\u001bfk\u0018#pk\ndWmX(q'\u0016$XCAB\u0001!)\u0011y(!)\u00028\n\u0015\u0018qW\u0001\u001fS6\u0004HnX(q?N3vLV0fc~\u001bfk\u0018$m_\u0006$xl\u00149TKR,\"aa\u0002\u0011\u0015\t}\u0014\u0011UAc\u0005[\f)-A\u000fj[Bdwl\u00149`'Z{fkX3r?N3v\fT8oO~{\u0005oU3u+\t\u0019i\u0001\u0005\u0006\u0003\u0000\u0005\u0005\u00161\u001bB{\u0003'\fA$[7qY~{\u0005oX*W?Z{V-]0T-~Ke\u000e^0Pa6{G-\u0006\u0002\u0004\u0014AQ!QTAQ\u0003S\u0013I.!+\u0002?%l\u0007\u000f\\0Pa~\u001bfk\u0018,`KF|6KV0E_V\u0014G.Z0Pa6{G-\u0006\u0002\u0004\u001aAQ!QTAQ\u0003o\u0013)/a.\u0002=%l\u0007\u000f\\0Pa~\u001bfk\u0018,`KF|6KV0GY>\fGoX(q\u001b>$WCAB\u0010!)\u0011i*!)\u0002F\n5\u0018QY\u0001\u001eS6\u0004HnX(q?N3vLV0fc~\u001bfk\u0018'p]\u001e|v\n]'pIV\u00111Q\u0005\t\u000b\u0005;\u000b\t+a5\u0003v\u0006M\u0017\u0001H5na2|v\n]0T-~3v,Z9`'Z{\u0016J\u001c;`\u001fB\u0004vn^\u000b\u0003\u0007W\u0001\"Ba/\u0002\"\u0006%&\u0011\\AU\u0003}IW\u000e\u001d7`\u001fB|6KV0W?\u0016\fxl\u0015,`\t>,(\r\\3`\u001fB\u0004vn^\u000b\u0003\u0007c\u0001\"Ba/\u0002\"\u0006]&Q]A\\\u0003yIW\u000e\u001d7`\u001fB|6KV0W?\u0016\fxl\u0015,`\r2|\u0017\r^0PaB{w/\u0006\u0002\u00048AQ!1XAQ\u0003\u000b\u0014i/!2\u0002;%l\u0007\u000f\\0Pa~\u001bfk\u0018,`KF|6KV0M_:<wl\u00149Q_^,\"a!\u0010\u0011\u0015\tm\u0016\u0011UAj\u0005k\f\u0019.\u0001\u000fj[Bdwl\u00149`'Z{6kX3r?N3v,\u00138u?>\u0003\u0018\t\u001a3\u0016\u0005\r\r\u0003CCAN\u0003C\u000bI+a+\u0002*\u0006y\u0012.\u001c9m?>\u0003xl\u0015,`'~+\u0017oX*W?\u0012{WO\u00197f?>\u0003\u0018\t\u001a3\u0016\u0005\r%\u0003CCAN\u0003C\u000b9,!/\u00028\u0006q\u0012.\u001c9m?>\u0003xl\u0015,`'~+\u0017oX*W?\u001acw.\u0019;`\u001fB\fE\rZ\u000b\u0003\u0007\u001f\u0002\"\"a'\u0002\"\u0006\u0015\u0017qYAc\u0003uIW\u000e\u001d7`\u001fB|6KV0T?\u0016\fxl\u0015,`\u0019>twmX(q\u0003\u0012$WCAB+!)\tY*!)\u0002T\u0006U\u00171[\u0001\u001dS6\u0004HnX(q?N3vlU0fc~\u001bfkX%oi~{\u0005oU;c+\t\u0019Y\u0006\u0005\u0006\u0002b\u0006\u0005\u0016\u0011VAV\u0003S\u000bq$[7qY~{\u0005oX*W?N{V-]0T-~#u.\u001e2mK~{\u0005oU;c+\t\u0019\t\u0007\u0005\u0006\u0002b\u0006\u0005\u0016qWA]\u0003o\u000ba$[7qY~{\u0005oX*W?N{V-]0T-~3En\\1u?>\u00038+\u001e2\u0016\u0005\r\u001d\u0004CCAq\u0003C\u000b)-a2\u0002F\u0006i\u0012.\u001c9m?>\u0003xl\u0015,`'~+\u0017oX*W?2{gnZ0PaN+(-\u0006\u0002\u0004nAQ\u0011\u0011]AQ\u0003'\f).a5\u00029%l\u0007\u000f\\0Pa~\u001bfkX*`KF|6KV0J]R|v\n]*fiV\u001111\u000f\t\u000b\u0005\u007f\n\t+!+\u0002,\u0006%\u0016aH5na2|v\n]0T-~\u001bv,Z9`'Z{Fi\\;cY\u0016|v\n]*fiV\u00111\u0011\u0010\t\u000b\u0005\u007f\n\t+a.\u0002:\u0006]\u0016AH5na2|v\n]0T-~\u001bv,Z9`'Z{f\t\\8bi~{\u0005oU3u+\t\u0019y\b\u0005\u0006\u0003\u0000\u0005\u0005\u0016QYAd\u0003\u000b\fQ$[7qY~{\u0005oX*W?N{V-]0T-~cuN\\4`\u001fB\u001cV\r^\u000b\u0003\u0007\u000b\u0003\"Ba \u0002\"\u0006M\u0017Q[Aj\u0003qIW\u000e\u001d7`\u001fB|6KV0T?\u0016\fxl\u0015,`\u0013:$xl\u00149Q_^,\"aa#\u0011\u0015\tm\u0016\u0011UAU\u0003W\u000bI+A\u0010j[Bdwl\u00149`'Z{6kX3r?N3v\fR8vE2,wl\u00149Q_^,\"a!%\u0011\u0015\tm\u0016\u0011UA\\\u0003s\u000b9,\u0001\u0010j[Bdwl\u00149`'Z{6kX3r?N3vL\u00127pCR|v\n\u001d)poV\u00111q\u0013\t\u000b\u0005w\u000b\t+!2\u0002H\u0006\u0015\u0017!H5na2|v\n]0T-~\u001bv,Z9`'Z{Fj\u001c8h?>\u0003\bk\\<\u0016\u0005\ru\u0005C\u0003B^\u0003C\u000b\u0019.!6\u0002T\u0006a\u0012.\u001c9m?>\u0003xl\u0015,`'~+\u0017oX*W?&sGoX(q\t&4XCABR!)\u0011\t'!)\u0002*\u0006-\u0016\u0011V\u0001 S6\u0004HnX(q?N3vlU0fc~\u001bfk\u0018#pk\ndWmX(q\t&4XCABU!)\u0011\t'!)\u00028\u0006e\u0016qW\u0001\u001fS6\u0004HnX(q?N3vlU0fc~\u001bfk\u0018$m_\u0006$xl\u00149ESZ,\"aa,\u0011\u0015\t\u0005\u0014\u0011UAc\u0003\u000f\f)-A\u000fj[Bdwl\u00149`'Z{6kX3r?N3v\fT8oO~{\u0005\u000fR5w+\t\u0019)\f\u0005\u0006\u0003b\u0005\u0005\u00161[Ak\u0003'\fA$[7qY~{\u0005oX*W?N{V-]0T-~Ke\u000e^0Pa6{G-\u0006\u0002\u0004<BQ!QTAQ\u0003S\u000bY+!+\u0002?%l\u0007\u000f\\0Pa~\u001bfkX*`KF|6KV0E_V\u0014G.Z0Pa6{G-\u0006\u0002\u0004BBQ!QTAQ\u0003o\u000bI,a.\u0002=%l\u0007\u000f\\0Pa~\u001bfkX*`KF|6KV0GY>\fGoX(q\u001b>$WCABd!)\u0011i*!)\u0002F\u0006\u001d\u0017QY\u0001\u001eS6\u0004HnX(q?N3vlU0fc~\u001bfk\u0018'p]\u001e|v\n]'pIV\u00111Q\u001a\t\u000b\u0005;\u000b\t+a5\u0002V\u0006M\u0017AI5na2|v\n]0T-~\u001bv,Z9`'Z{\u0016J\u001c;`\u001fBlU\u000f\\*dC2\f'/\u0006\u0002\u0004TBQ!1IAQ\u0003S\u000bY+!+\u0002K%l\u0007\u000f\\0Pa~\u001bfkX*`KF|6KV0E_V\u0014G.Z0Pa6+HnU2bY\u0006\u0014XCABm!)\u0011\u0019%!)\u00028\u0006e\u0016qW\u0001%S6\u0004HnX(q?N3vlU0fc~\u001bfk\u0018$m_\u0006$xl\u00149Nk2\u001c6-\u00197beV\u00111q\u001c\t\u000b\u0005\u0007\n\t+!2\u0002H\u0006\u0015\u0017aI5na2|v\n]0T-~\u001bv,Z9`'Z{Fj\u001c8h?>\u0003X*\u001e7TG\u0006d\u0017M]\u000b\u0003\u0007K\u0004\"Ba\u0011\u0002\"\u0006M\u0017Q[Aj\u0003\tJW\u000e\u001d7`\u001fB|6KV0T?\u0016\fxl\u0015,`\u0013:$xl\u00149Nk2l\u0015\r\u001e:jqV\u001111\u001e\t\u000b\u0007[\f\t+!+\u0002,\u0006%fb\u0001@\u0004p&\u00191\u0011_8\u0002\u0017=\u0003X*\u001e7NCR\u0014\u0018\u000e_\u0001&S6\u0004HnX(q?N3vlU0fc~\u001bfk\u0018#pk\ndWmX(q\u001bVdW*\u0019;sSb,\"aa>\u0011\u0015\r5\u0018\u0011UA\\\u0003s\u000b9,\u0001\u0013j[Bdwl\u00149`'Z{6kX3r?N3vL\u00127pCR|v\n]'vY6\u000bGO]5y+\t\u0019i\u0010\u0005\u0006\u0004n\u0006\u0005\u0016QYAd\u0003\u000b\f1%[7qY~{\u0005oX*W?N{V-]0T-~cuN\\4`\u001fBlU\u000f\\'biJL\u00070\u0006\u0002\u0005\u0004AQ1Q^AQ\u0003'\f).a5\u0002=%l\u0007\u000f\\0Pa6+H.\u00138oKJ|6KV0T-~+\u0017o\u0018+`\u0013:$XC\u0001C\u0005!)!Y!!)\u0002*\u0006%\u00161\u0016\b\u0004}\u00125\u0011b\u0001C\b_\u0006Qq\n]'vY&sg.\u001a:\u0002C%l\u0007\u000f\\0Pa6+H.\u00138oKJ|6KV0T-~+\u0017o\u0018+`\t>,(\r\\3\u0016\u0005\u0011U\u0001C\u0003C\u0006\u0003C\u000b9,a.\u0002:\u0006\u0001\u0013.\u001c9m?>\u0003X*\u001e7J]:,'oX*W?N3v,Z9`)~3En\\1u+\t!Y\u0002\u0005\u0006\u0005\f\u0005\u0005\u0016QYAc\u0003\u000f\fq$[7qY~{\u0005/T;m\u0013:tWM]0T-~\u001bfkX3r?R{Fj\u001c8h+\t!\t\u0003\u0005\u0006\u0005\f\u0005\u0005\u00161[Aj\u0003+\f!$[7qY~{\u0005/T;m\u0013:tWM]0T-~\u001bfkX3r?R+B\u0001b\n\u00050QAA\u0011\u0006C\u0019\to!i\u0004\u0005\u0006\u0005\f\u0005\u0005F1\u0006C\u0016\t[\u0001b!!\u0015\u0002Z\u00115\u0002\u0003BA\u001b\t_!qAa\u0002V\u0005\u0004\tY\u0004C\u0005\u00054U\u000b\t\u0011q\u0001\u00056\u0005QQM^5eK:\u001cW\rJ\u001b\u0011\r\u0005-\u0015\u0011\u0013C\u0017\u0011%!I$VA\u0001\u0002\b!Y$\u0001\u0006fm&$WM\\2fIY\u0002b!a\u001f\u0002\u0002\u00125\u0002\"\u0003C +\u0006\u0005\t9\u0001C!\u0003))g/\u001b3f]\u000e,Ge\u000e\t\u0007\u0005\u001f\u0011\u0019\u0004\"\f\u0002A%l\u0007\u000f\\*dC2,\u0017\t\u001a3`'Z{6kX*W?&s\u0007\u000b\\1dK~Ke\u000e^\u000b\u0003\t\u000f\u0002\"\u0002\"\u0013\u0005P\u0005%\u00161VAU\u001d\u0011\t\t\u0006b\u0013\n\u0007\u00115\u0013/\u0001\u0005tG\u0006dW-\u00113e\u0013\u0011!\t&!*\u0003\u0019%s\u0007\u000b\\1dK&k\u0007\u000f\\\u001a\u0002G%l\u0007\u000f\\*dC2,\u0017\t\u001a3`'Z{6kX*W?&s\u0007\u000b\\1dK~#u.\u001e2mKV\u0011Aq\u000b\t\u000b\t\u0013\"y%a.\u0002:\u0006]\u0016AI5na2\u001c6-\u00197f\u0003\u0012$wl\u0015,`'~\u001bfkX%o!2\f7-Z0GY>\fG/\u0006\u0002\u0005^AQA\u0011\nC(\u0003\u000b\f9-!2\u0002C%l\u0007\u000f\\*dC2,\u0017\t\u001a3`'Z{6kX*W?&s\u0007\u000b\\1dK~cuN\\4\u0016\u0005\u0011\r\u0004C\u0003C%\t\u001f\n\u0019.!6\u0002T\nY2)\u00198[SBl\u0015\r\u001d,bYV,7o\u00159beN,g+Z2u_J,b\u0001\"\u001b\u0005|\u0011e6\u0003\u0002.x\tW\u0002B\u0002\"\u001c\u0005t\u0011]D\u0011\u0010C\\\t\u000fl!\u0001b\u001c\u000b\u0007\u0011E\u0014/A\u0004tkB\u0004xN\u001d;\n\t\u0011UDq\u000e\u0002\u0010\u0007\u0006t',\u001b9NCB4\u0016\r\\;fgB1\u0011\u0011KA-\ts\u0002B!!\u000e\u0005|\u0011Y\u0011\u0011\r.!\u0002\u0003\u0005)\u0019AA\u001eQ1!Y\bb \u0005\u0006\u0012eE1\u0015CW!\rAH\u0011Q\u0005\u0004\t\u0007K(aC:qK\u000eL\u0017\r\\5{K\u0012\f\u0014b\tCD\t\u0013#i\tb#\u000f\u0007a$I)C\u0002\u0005\ff\fa\u0001R8vE2,\u0017G\u0002\u0013\u0005\u0010\u0012]%P\u0004\u0003\u0005\u0012\u0012]UB\u0001CJ\u0015\r!)*^\u0001\u0007yI|w\u000e\u001e \n\u0003i\f\u0014b\tCN\t;#\t\u000bb(\u000f\u0007a$i*C\u0002\u0005 f\f1!\u00138uc\u0019!Cq\u0012CLuFJ1\u0005\"*\u0005(\u0012-F\u0011\u0016\b\u0004q\u0012\u001d\u0016b\u0001CUs\u0006)a\t\\8biF2A\u0005b$\u0005\u0018j\f\u0014b\tCX\tc#)\fb-\u000f\u0007a$\t,C\u0002\u00054f\fA\u0001T8oOF2A\u0005b$\u0005\u0018j\u0004B!!\u000e\u0005:\u0012YA1\u0018.!\u0002\u0003\u0005)\u0019AA\u001e\u0005\t\u0011f\u000b\u000b\u0005\u0005:\u0012}Dq\u0018Cbc%\u0019C1\u0014CO\t\u0003$y*\r\u0004%\t\u001f#9J_\u0019\nG\u0011\u001dE\u0011\u0012Cc\t\u0017\u000bd\u0001\nCH\t/S\bCBA)\u00033\"9,\u0001\u0006fm&$WM\\2fIa\u0002b!a#\u0002\u0012\u0012]\u0016AC3wS\u0012,gnY3%sA1\u00111PAA\to\u000b1\"\u001a<jI\u0016t7-\u001a\u00132aA1!q\u0002B\u001a\to\u000ba\u0001P5oSRtDC\u0001Cm)!!Y\u000eb8\u0005b\u0012\r\bc\u0002Co5\u0012eDqW\u0007\u0002\u0001!9A\u0011\u001a0A\u0004\u0011-\u0007b\u0002Cg=\u0002\u000fAq\u001a\u0005\b\t#t\u00069\u0001Cj\u0003\u0019\u0019'/Z1uKR!Aq\u0019Cu\u0011\u001d!Yo\u0018a\u0001\u0003W\u000ba\u0001\\3oORD\u0017aA7baRAAq\u0019Cy\tk$I\u0010C\u0004\u0005t\u0002\u0004\r\u0001b\u001e\u0002\t\u0019\u0014x.\u001c\u0005\b\to\u0004\u0007\u0019\u0001C<\u0003\u00151'o\\73\u0011\u001d!Y\u0010\u0019a\u0001\t{\f!A\u001a8\u0011\u0013a$y\u0010\"\u001f\u0005z\u0011]\u0016bAC\u0001s\nIa)\u001e8di&|gNM\u0001\u0007u&\u0004X*\u00199\u0016\r\u0015\u001dQQBC\t)!)I!b\u0005\u0006\u001a\u0015}\u0001c\u0002Co5\u0016-Qq\u0002\t\u0005\u0003k)i\u0001B\u0004\u0002b\u0005\u0014\r!a\u000f\u0011\t\u0005UR\u0011\u0003\u0003\b\u0003O\n'\u0019AA\u001e\u0011%))\"YA\u0001\u0002\b)9\"A\u0006fm&$WM\\2fIE\n\u0004CBAF\u0003#+y\u0001C\u0005\u0006\u001c\u0005\f\t\u0011q\u0001\u0006\u001e\u0005YQM^5eK:\u001cW\rJ\u00193!\u0019\tY(!!\u0006\u0010!IQ\u0011E1\u0002\u0002\u0003\u000fQ1E\u0001\fKZLG-\u001a8dK\u0012\n4\u0007\u0005\u0004\u0003\u0010\tMRqB\u0001\tu&\u0004X*\u00199`IV\u0011Q\u0011\u0006\t\b\t;T\u0016\u0011XA]\u0003!Q\u0018\u000e]'ba~3WCAC\u0018!\u001d!iNWAd\u0003\u000f\f\u0001B_5q\u001b\u0006\u0004x,[\u000b\u0003\u000bk\u0001r\u0001\"8[\u0003W\u000bYK\u0001\u0010DC:T\u0016\u000e]'ba.+\u0017PV1mk\u0016\u001c8\u000b]1sg\u00164Vm\u0019;peV1Q1HC$\u000b;\u001aB!Z<\u0006>AqAQNC \u000b\u0007\nY+\"\u0012\u0006\\\u0015%\u0014\u0002BC!\t_\u0012!cQ1o5&\u0004X*\u00199LKf4\u0016\r\\;fgB1\u0011\u0011KA-\u000b\u000b\u0002B!!\u000e\u0006H\u0011Y\u0011\u0011M3!\u0002\u0003\u0005)\u0019AA\u001eQ1)9\u0005b \u0006L\u0015=S1KC,c%\u0019Cq\u0011CE\u000b\u001b\"Y)\r\u0004%\t\u001f#9J_\u0019\nG\u0011mEQTC)\t?\u000bd\u0001\nCH\t/S\u0018'C\u0012\u0005&\u0012\u001dVQ\u000bCUc\u0019!Cq\u0012CLuFJ1\u0005b,\u00052\u0016eC1W\u0019\u0007I\u0011=Eq\u0013>\u0011\t\u0005URQ\f\u0003\f\tw+\u0007\u0015!A\u0001\u0006\u0004\tY\u0004\u000b\u0005\u0006^\u0011}T\u0011MC3c%\u0019C1\u0014CO\u000bG\"y*\r\u0004%\t\u001f#9J_\u0019\nG\u0011\u001dE\u0011RC4\t\u0017\u000bd\u0001\nCH\t/S\bCBA)\u00033*Y&A\u0006fm&$WM\\2fIE\"\u0004CBAF\u0003#+Y&A\u0006fm&$WM\\2fIE*\u0004CBA>\u0003\u0003+Y&A\u0006fm&$WM\\2fIE2\u0004C\u0002B\b\u0005g)Y\u0006\u0006\u0002\u0006zQAQ1PC?\u000b\u007f*\t\tE\u0004\u0005^\u0016,)%b\u0017\t\u000f\u0015-\u0014\u000eq\u0001\u0006n!9QqN5A\u0004\u0015E\u0004bBC:S\u0002\u000fQQ\u000f\u000b\u0005\u000bS*)\tC\u0004\u0005l*\u0004\r!a+\u0015\u0011\u0015%T\u0011RCF\u000b\u001bCq\u0001b=l\u0001\u0004)\u0019\u0005C\u0004\u0005x.\u0004\r!b\u0011\t\u000f\u0011m8\u000e1\u0001\u0006\u0010BY\u00010\"%\u0002,\u0016\u0015SQIC.\u0013\r)\u0019*\u001f\u0002\n\rVt7\r^5p]N\n\u0011\"\\1q\u0003\u000e$\u0018N^3\u0015\u0011\u0015%T\u0011TCN\u000b;Cq\u0001b=m\u0001\u0004)\u0019\u0005C\u0004\u0005x2\u0004\r!b\u0011\t\u000f\u0011mH\u000e1\u0001\u0006\u0010\u0006A!0\u001b9NCB\\e+\u0006\u0004\u0006$\u0016%VQ\u0016\u000b\t\u000bK+y+\".\u0006<B9AQ\\3\u0006(\u0016-\u0006\u0003BA\u001b\u000bS#q!!\u0019n\u0005\u0004\tY\u0004\u0005\u0003\u00026\u00155FaBA4[\n\u0007\u00111\b\u0005\n\u000bck\u0017\u0011!a\u0002\u000bg\u000b1\"\u001a<jI\u0016t7-\u001a\u00132oA1\u00111RAI\u000bWC\u0011\"b.n\u0003\u0003\u0005\u001d!\"/\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007\u000f\t\u0007\u0003w\n\t)b+\t\u0013\u0015uV.!AA\u0004\u0015}\u0016aC3wS\u0012,gnY3%ce\u0002bAa\u0004\u00034\u0015-\u0006"
)
public interface SparseVectorExpandOps extends VectorOps, SparseVector_DenseMatrixOps, SparseVector_GenericOps {
   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Int_OpAdd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Double_OpAdd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Float_OpAdd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Long_OpAdd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Int_OpSub_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Double_OpSub_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Float_OpSub_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Long_OpSub_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_OpMulScalar_SV_SV_eq_SV_Int_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_OpMulScalar_SV_SV_eq_SV_Double_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_OpMulScalar_SV_SV_eq_SV_Float_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_OpMulScalar_SV_SV_eq_SV_Long_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Int_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Double_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Float_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Long_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Int_OpSet_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Double_OpSet_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Float_OpSet_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Long_OpSet_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Int_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Double_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Float_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Long_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Int_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Double_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Float_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Long_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Int_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Double_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Float_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Long_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Int_OpSet_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Double_OpSet_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Float_OpSet_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Long_OpSet_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Int_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Double_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Float_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Long_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Int_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Double_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Float_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Long_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Int_OpAdd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Double_OpAdd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Float_OpAdd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Long_OpAdd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Int_OpSub_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Double_OpSub_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Float_OpSub_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Long_OpSub_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Int_OpSet_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Double_OpSet_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Float_OpSet_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Long_OpSet_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Int_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Double_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Float_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Long_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Int_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Double_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Float_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Long_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Int_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Double_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Float_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Long_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Int_OpMulScalar_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Double_OpMulScalar_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Float_OpMulScalar_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Long_OpMulScalar_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Int_OpMulMatrix_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Double_OpMulMatrix_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Float_OpMulMatrix_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Long_OpMulMatrix_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_OpMulInner_SV_SV_eq_T_Int_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_OpMulInner_SV_SV_eq_T_Double_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_OpMulInner_SV_SV_eq_T_Float_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_OpMulInner_SV_SV_eq_T_Long_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$implScaleAdd_SV_S_SV_InPlace_Int_$eq(final UFunc.InPlaceImpl3 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$implScaleAdd_SV_S_SV_InPlace_Double_$eq(final UFunc.InPlaceImpl3 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$implScaleAdd_SV_S_SV_InPlace_Float_$eq(final UFunc.InPlaceImpl3 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$implScaleAdd_SV_S_SV_InPlace_Long_$eq(final UFunc.InPlaceImpl3 x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$zipMap_d_$eq(final CanZipMapValuesSparseVector x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$zipMap_f_$eq(final CanZipMapValuesSparseVector x$1);

   void breeze$linalg$operators$SparseVectorExpandOps$_setter_$zipMap_i_$eq(final CanZipMapValuesSparseVector x$1);

   // $FF: synthetic method
   static UFunc.UImpl2 liftCSCOpToSVransposeOp$(final SparseVectorExpandOps $this, final UFunc.UImpl2 op, final Zero zero, final ClassTag ct) {
      return $this.liftCSCOpToSVransposeOp(op, zero, ct);
   }

   default UFunc.UImpl2 liftCSCOpToSVransposeOp(final UFunc.UImpl2 op, final Zero zero, final ClassTag ct) {
      return new UFunc.UImpl2(op, ct) {
         private final UFunc.UImpl2 op$1;
         private final ClassTag ct$1;

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

         public Object apply(final Object v, final Transpose v2) {
            return this.op$1.apply(v, ((SparseVector)v2.inner()).asCscRow(this.ct$1));
         }

         public {
            this.op$1 = op$1;
            this.ct$1 = ct$1;
         }
      };
   }

   UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Int_OpAdd();

   UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Double_OpAdd();

   UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Float_OpAdd();

   UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Long_OpAdd();

   UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Int_OpSub();

   UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Double_OpSub();

   UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Float_OpSub();

   UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Long_OpSub();

   // $FF: synthetic method
   static UFunc.UImpl2 implSubOp_SV_SV_eq_SV$(final SparseVectorExpandOps $this, final Ring evidence$1, final ClassTag evidence$2) {
      return $this.implSubOp_SV_SV_eq_SV(evidence$1, evidence$2);
   }

   default UFunc.UImpl2 implSubOp_SV_SV_eq_SV(final Ring evidence$1, final ClassTag evidence$2) {
      return new UFunc.UImpl2(evidence$1, evidence$2) {
         private final Ring r;
         private final Ring evidence$1$1;
         private final ClassTag evidence$2$1;

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

         private Ring r() {
            return this.r;
         }

         public SparseVector apply(final SparseVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int asize = a.activeSize();
               int bsize = b.activeSize();
               Object q = this.r().zero();
               int[] resultI = new int[asize + bsize];
               Object resultV = this.evidence$2$1.newArray(asize + bsize);
               int resultOff = 0;
               int aoff = 0;

               int boff;
               for(boff = 0; aoff < asize; ++aoff) {
                  while(boff < bsize && b.indexAt(boff) < a.indexAt(aoff)) {
                     resultI[resultOff] = b.indexAt(boff);
                     .MODULE$.array_update(resultV, resultOff, this.r().$minus(q, b.valueAt(boff)));
                     ++resultOff;
                     ++boff;
                  }

                  Object var10000;
                  if (boff < bsize && b.indexAt(boff) == a.indexAt(aoff)) {
                     Object bv = b.valueAt(boff);
                     ++boff;
                     var10000 = bv;
                  } else {
                     var10000 = q;
                  }

                  Object bvalue = var10000;
                  resultI[resultOff] = a.indexAt(aoff);
                  .MODULE$.array_update(resultV, resultOff, this.r().$minus(a.valueAt(aoff), bvalue));
                  ++resultOff;
               }

               while(boff < bsize) {
                  resultI[resultOff] = b.indexAt(boff);
                  .MODULE$.array_update(resultV, resultOff, this.r().$minus(q, b.valueAt(boff)));
                  ++resultOff;
                  ++boff;
               }

               SparseVector var16;
               if (resultOff != resultI.length) {
                  Object dat = this.evidence$2$1.newArray(resultOff);
                  scala.Array..MODULE$.copy(resultV, 0, dat, 0, resultOff);
                  var16 = new SparseVector(Arrays.copyOf(resultI, resultOff), dat, resultOff, a.length(), Zero$.MODULE$.zeroFromSemiring(this.evidence$1$1));
               } else {
                  var16 = new SparseVector(resultI, resultV, resultOff, a.length(), Zero$.MODULE$.zeroFromSemiring(this.evidence$1$1));
               }

               return var16;
            }
         }

         public {
            this.evidence$1$1 = evidence$1$1;
            this.evidence$2$1 = evidence$2$1;
            this.r = (Ring)scala.Predef..MODULE$.implicitly(evidence$1$1);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 implAddOp_SV_SV_eq_SV$(final SparseVectorExpandOps $this, final Semiring evidence$3, final ClassTag evidence$4) {
      return $this.implAddOp_SV_SV_eq_SV(evidence$3, evidence$4);
   }

   default UFunc.UImpl2 implAddOp_SV_SV_eq_SV(final Semiring evidence$3, final ClassTag evidence$4) {
      return new UFunc.UImpl2(evidence$3, evidence$4) {
         private final Semiring r;
         private final Semiring evidence$3$1;
         private final ClassTag evidence$4$1;

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

         private Semiring r() {
            return this.r;
         }

         public SparseVector apply(final SparseVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int asize = a.activeSize();
               int bsize = b.activeSize();
               Object q = this.r().zero();
               int[] resultI = new int[asize + bsize];
               Object resultV = this.evidence$4$1.newArray(asize + bsize);
               int resultOff = 0;
               int aoff = 0;

               int boff;
               for(boff = 0; aoff < asize; ++aoff) {
                  while(boff < bsize && b.indexAt(boff) < a.indexAt(aoff)) {
                     resultI[resultOff] = b.indexAt(boff);
                     .MODULE$.array_update(resultV, resultOff, this.r().$plus(q, b.valueAt(boff)));
                     ++resultOff;
                     ++boff;
                  }

                  Object var10000;
                  if (boff < bsize && b.indexAt(boff) == a.indexAt(aoff)) {
                     Object bv = b.valueAt(boff);
                     ++boff;
                     var10000 = bv;
                  } else {
                     var10000 = q;
                  }

                  Object bvalue = var10000;
                  resultI[resultOff] = a.indexAt(aoff);
                  .MODULE$.array_update(resultV, resultOff, this.r().$plus(a.valueAt(aoff), bvalue));
                  ++resultOff;
               }

               while(boff < bsize) {
                  resultI[resultOff] = b.indexAt(boff);
                  .MODULE$.array_update(resultV, resultOff, this.r().$plus(q, b.valueAt(boff)));
                  ++resultOff;
                  ++boff;
               }

               SparseVector var16;
               if (resultOff != resultI.length) {
                  Object dat = this.evidence$4$1.newArray(resultOff);
                  scala.Array..MODULE$.copy(resultV, 0, dat, 0, resultOff);
                  var16 = new SparseVector(Arrays.copyOf(resultI, resultOff), dat, resultOff, a.length(), Zero$.MODULE$.zeroFromSemiring(this.evidence$3$1));
               } else {
                  var16 = new SparseVector(resultI, resultV, resultOff, a.length(), Zero$.MODULE$.zeroFromSemiring(this.evidence$3$1));
               }

               return var16;
            }
         }

         public {
            this.evidence$3$1 = evidence$3$1;
            this.evidence$4$1 = evidence$4$1;
            this.r = (Semiring)scala.Predef..MODULE$.implicitly(evidence$3$1);
         }
      };
   }

   UFunc.UImpl2 impl_OpMulScalar_SV_SV_eq_SV_Int();

   UFunc.UImpl2 impl_OpMulScalar_SV_SV_eq_SV_Double();

   UFunc.UImpl2 impl_OpMulScalar_SV_SV_eq_SV_Float();

   UFunc.UImpl2 impl_OpMulScalar_SV_SV_eq_SV_Long();

   UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Int_OpDiv();

   UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Double_OpDiv();

   UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Float_OpDiv();

   UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Long_OpDiv();

   UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Int_OpSet();

   UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Double_OpSet();

   UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Float_OpSet();

   UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Long_OpSet();

   UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Int_OpMod();

   UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Double_OpMod();

   UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Float_OpMod();

   UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Long_OpMod();

   UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Int_OpPow();

   UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Double_OpPow();

   UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Float_OpPow();

   UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Long_OpPow();

   UFunc.UImpl2 impl_Op_SV_V_eq_SV_Int_OpDiv();

   UFunc.UImpl2 impl_Op_SV_V_eq_SV_Double_OpDiv();

   UFunc.UImpl2 impl_Op_SV_V_eq_SV_Float_OpDiv();

   UFunc.UImpl2 impl_Op_SV_V_eq_SV_Long_OpDiv();

   UFunc.UImpl2 impl_Op_SV_V_eq_SV_Int_OpSet();

   UFunc.UImpl2 impl_Op_SV_V_eq_SV_Double_OpSet();

   UFunc.UImpl2 impl_Op_SV_V_eq_SV_Float_OpSet();

   UFunc.UImpl2 impl_Op_SV_V_eq_SV_Long_OpSet();

   UFunc.UImpl2 impl_Op_SV_V_eq_SV_Int_OpMod();

   UFunc.UImpl2 impl_Op_SV_V_eq_SV_Double_OpMod();

   UFunc.UImpl2 impl_Op_SV_V_eq_SV_Float_OpMod();

   UFunc.UImpl2 impl_Op_SV_V_eq_SV_Long_OpMod();

   UFunc.UImpl2 impl_Op_SV_V_eq_SV_Int_OpPow();

   UFunc.UImpl2 impl_Op_SV_V_eq_SV_Double_OpPow();

   UFunc.UImpl2 impl_Op_SV_V_eq_SV_Float_OpPow();

   UFunc.UImpl2 impl_Op_SV_V_eq_SV_Long_OpPow();

   UFunc.UImpl2 impl_Op_SV_S_eq_SV_Int_OpAdd();

   UFunc.UImpl2 impl_Op_SV_S_eq_SV_Double_OpAdd();

   UFunc.UImpl2 impl_Op_SV_S_eq_SV_Float_OpAdd();

   UFunc.UImpl2 impl_Op_SV_S_eq_SV_Long_OpAdd();

   UFunc.UImpl2 impl_Op_SV_S_eq_SV_Int_OpSub();

   UFunc.UImpl2 impl_Op_SV_S_eq_SV_Double_OpSub();

   UFunc.UImpl2 impl_Op_SV_S_eq_SV_Float_OpSub();

   UFunc.UImpl2 impl_Op_SV_S_eq_SV_Long_OpSub();

   UFunc.UImpl2 impl_Op_SV_S_eq_SV_Int_OpSet();

   UFunc.UImpl2 impl_Op_SV_S_eq_SV_Double_OpSet();

   UFunc.UImpl2 impl_Op_SV_S_eq_SV_Float_OpSet();

   UFunc.UImpl2 impl_Op_SV_S_eq_SV_Long_OpSet();

   UFunc.UImpl2 impl_Op_SV_S_eq_SV_Int_OpPow();

   UFunc.UImpl2 impl_Op_SV_S_eq_SV_Double_OpPow();

   UFunc.UImpl2 impl_Op_SV_S_eq_SV_Float_OpPow();

   UFunc.UImpl2 impl_Op_SV_S_eq_SV_Long_OpPow();

   UFunc.UImpl2 impl_Op_SV_S_eq_SV_Int_OpDiv();

   UFunc.UImpl2 impl_Op_SV_S_eq_SV_Double_OpDiv();

   UFunc.UImpl2 impl_Op_SV_S_eq_SV_Float_OpDiv();

   UFunc.UImpl2 impl_Op_SV_S_eq_SV_Long_OpDiv();

   UFunc.UImpl2 impl_Op_SV_S_eq_SV_Int_OpMod();

   UFunc.UImpl2 impl_Op_SV_S_eq_SV_Double_OpMod();

   UFunc.UImpl2 impl_Op_SV_S_eq_SV_Float_OpMod();

   UFunc.UImpl2 impl_Op_SV_S_eq_SV_Long_OpMod();

   UFunc.UImpl2 impl_Op_SV_S_eq_SV_Int_OpMulScalar();

   UFunc.UImpl2 impl_Op_SV_S_eq_SV_Double_OpMulScalar();

   UFunc.UImpl2 impl_Op_SV_S_eq_SV_Float_OpMulScalar();

   UFunc.UImpl2 impl_Op_SV_S_eq_SV_Long_OpMulScalar();

   UFunc.UImpl2 impl_Op_SV_S_eq_SV_Int_OpMulMatrix();

   UFunc.UImpl2 impl_Op_SV_S_eq_SV_Double_OpMulMatrix();

   UFunc.UImpl2 impl_Op_SV_S_eq_SV_Float_OpMulMatrix();

   UFunc.UImpl2 impl_Op_SV_S_eq_SV_Long_OpMulMatrix();

   UFunc.UImpl2 impl_OpMulInner_SV_SV_eq_T_Int();

   UFunc.UImpl2 impl_OpMulInner_SV_SV_eq_T_Double();

   UFunc.UImpl2 impl_OpMulInner_SV_SV_eq_T_Float();

   UFunc.UImpl2 impl_OpMulInner_SV_SV_eq_T_Long();

   // $FF: synthetic method
   static UFunc.UImpl2 impl_OpMulInner_SV_SV_eq_T$(final SparseVectorExpandOps $this, final ClassTag evidence$5, final Zero evidence$6, final Semiring evidence$7) {
      return $this.impl_OpMulInner_SV_SV_eq_T(evidence$5, evidence$6, evidence$7);
   }

   default UFunc.UImpl2 impl_OpMulInner_SV_SV_eq_T(final ClassTag evidence$5, final Zero evidence$6, final Semiring evidence$7) {
      return new UFunc.UImpl2(evidence$7) {
         private final Semiring s;

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

         private Semiring s() {
            return this.s;
         }

         public Object apply(final SparseVector a, final SparseVector b) {
            while(b.activeSize() < a.activeSize()) {
               SparseVector var10000 = b;
               b = a;
               a = var10000;
            }

            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int asize = a.activeSize();
               int bsize = b.activeSize();
               Object result = this.s().zero();
               int aoff = 0;
               int boff = 0;

               while(aoff < asize) {
                  int aind = a.indexAt(aoff);
                  boff = Arrays.binarySearch(b.index(), boff, scala.math.package..MODULE$.min(bsize, aind + 1), aind);
                  if (boff < 0) {
                     boff = ~boff;
                     if (boff == bsize) {
                        aoff = asize;
                     } else {
                        int bind = b.indexAt(boff);
                        int newAoff = Arrays.binarySearch(a.index(), aoff, scala.math.package..MODULE$.min(asize, bind + 1), bind);
                        if (newAoff < 0) {
                           newAoff = ~newAoff;
                           ++boff;
                        }

                        boolean cond$macro$3 = newAoff > aoff;
                        if (!cond$macro$3) {
                           throw new AssertionError((new StringBuilder(35)).append("assertion failed: ").append((new StringBuilder(1)).append(aoff).append(" ").append(newAoff).toString()).append(": ").append("newAoff.>(aoff)").toString());
                        }

                        aoff = newAoff;
                     }
                  } else {
                     result = this.s().$plus(result, this.s().$times(a.valueAt(aoff), b.valueAt(boff)));
                     ++aoff;
                     ++boff;
                  }
               }

               return result;
            }
         }

         public {
            this.s = (Semiring)scala.Predef..MODULE$.implicitly(evidence$7$1);
         }
      };
   }

   UFunc.InPlaceImpl3 implScaleAdd_SV_S_SV_InPlace_Int();

   UFunc.InPlaceImpl3 implScaleAdd_SV_S_SV_InPlace_Double();

   UFunc.InPlaceImpl3 implScaleAdd_SV_S_SV_InPlace_Float();

   UFunc.InPlaceImpl3 implScaleAdd_SV_S_SV_InPlace_Long();

   // $FF: synthetic method
   static CanZipMapValuesSparseVector zipMap$(final SparseVectorExpandOps $this, final ClassTag evidence$11, final Zero evidence$12, final Semiring evidence$13) {
      return $this.zipMap(evidence$11, evidence$12, evidence$13);
   }

   default CanZipMapValuesSparseVector zipMap(final ClassTag evidence$11, final Zero evidence$12, final Semiring evidence$13) {
      return new CanZipMapValuesSparseVector(evidence$11, evidence$12, evidence$13);
   }

   CanZipMapValuesSparseVector zipMap_d();

   CanZipMapValuesSparseVector zipMap_f();

   CanZipMapValuesSparseVector zipMap_i();

   // $FF: synthetic method
   static CanZipMapKeyValuesSparseVector zipMapKV$(final SparseVectorExpandOps $this, final ClassTag evidence$17, final Zero evidence$18, final Semiring evidence$19) {
      return $this.zipMapKV(evidence$17, evidence$18, evidence$19);
   }

   default CanZipMapKeyValuesSparseVector zipMapKV(final ClassTag evidence$17, final Zero evidence$18, final Semiring evidence$19) {
      return new CanZipMapKeyValuesSparseVector(evidence$17, evidence$18, evidence$19);
   }

   static void $init$(final SparseVectorExpandOps $this) {
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Int_OpAdd_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int asize = a.activeSize();
               int bsize = b.activeSize();
               int q = 0;
               int[] resultI = new int[asize + bsize];
               int[] resultV = new int[asize + bsize];
               int resultOff = 0;
               int aoff = 0;

               int boff;
               for(boff = 0; aoff < asize; ++aoff) {
                  while(boff < bsize && b.indexAt(boff) < a.indexAt(aoff)) {
                     resultI[resultOff] = b.indexAt(boff);
                     resultV[resultOff] = q + b.valueAt$mcI$sp(boff);
                     ++resultOff;
                     ++boff;
                  }

                  int var10000;
                  if (boff < bsize && b.indexAt(boff) == a.indexAt(aoff)) {
                     int bv = b.valueAt$mcI$sp(boff);
                     ++boff;
                     var10000 = bv;
                  } else {
                     var10000 = q;
                  }

                  int bvalue = var10000;
                  resultI[resultOff] = a.indexAt(aoff);
                  resultV[resultOff] = a.valueAt$mcI$sp(aoff) + bvalue;
                  ++resultOff;
               }

               while(boff < bsize) {
                  resultI[resultOff] = b.indexAt(boff);
                  resultV[resultOff] = q + b.valueAt$mcI$sp(boff);
                  ++resultOff;
                  ++boff;
               }

               return resultOff != resultI.length ? new SparseVector$mcI$sp(Arrays.copyOf(resultI, resultOff), Arrays.copyOf(resultV, resultOff), resultOff, a.length(), Zero$.MODULE$.IntZero()) : new SparseVector$mcI$sp(resultI, resultV, resultOff, a.length(), Zero$.MODULE$.IntZero());
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_V_eq_V_idempotent_Int_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Double_OpAdd_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int asize = a.activeSize();
               int bsize = b.activeSize();
               double q = (double)0.0F;
               int[] resultI = new int[asize + bsize];
               double[] resultV = new double[asize + bsize];
               int resultOff = 0;
               int aoff = 0;

               int boff;
               for(boff = 0; aoff < asize; ++aoff) {
                  while(boff < bsize && b.indexAt(boff) < a.indexAt(aoff)) {
                     resultI[resultOff] = b.indexAt(boff);
                     resultV[resultOff] = q + b.valueAt$mcD$sp(boff);
                     ++resultOff;
                     ++boff;
                  }

                  double var10000;
                  if (boff < bsize && b.indexAt(boff) == a.indexAt(aoff)) {
                     double bv = b.valueAt$mcD$sp(boff);
                     ++boff;
                     var10000 = bv;
                  } else {
                     var10000 = q;
                  }

                  double bvalue = var10000;
                  resultI[resultOff] = a.indexAt(aoff);
                  resultV[resultOff] = a.valueAt$mcD$sp(aoff) + bvalue;
                  ++resultOff;
               }

               while(boff < bsize) {
                  resultI[resultOff] = b.indexAt(boff);
                  resultV[resultOff] = q + b.valueAt$mcD$sp(boff);
                  ++resultOff;
                  ++boff;
               }

               return resultOff != resultI.length ? new SparseVector$mcD$sp(Arrays.copyOf(resultI, resultOff), Arrays.copyOf(resultV, resultOff), resultOff, a.length(), Zero$.MODULE$.DoubleZero()) : new SparseVector$mcD$sp(resultI, resultV, resultOff, a.length(), Zero$.MODULE$.DoubleZero());
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_V_eq_V_idempotent_Double_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Float_OpAdd_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int asize = a.activeSize();
               int bsize = b.activeSize();
               float q = 0.0F;
               int[] resultI = new int[asize + bsize];
               float[] resultV = new float[asize + bsize];
               int resultOff = 0;
               int aoff = 0;

               int boff;
               for(boff = 0; aoff < asize; ++aoff) {
                  while(boff < bsize && b.indexAt(boff) < a.indexAt(aoff)) {
                     resultI[resultOff] = b.indexAt(boff);
                     resultV[resultOff] = q + b.valueAt$mcF$sp(boff);
                     ++resultOff;
                     ++boff;
                  }

                  float var10000;
                  if (boff < bsize && b.indexAt(boff) == a.indexAt(aoff)) {
                     float bv = b.valueAt$mcF$sp(boff);
                     ++boff;
                     var10000 = bv;
                  } else {
                     var10000 = q;
                  }

                  float bvalue = var10000;
                  resultI[resultOff] = a.indexAt(aoff);
                  resultV[resultOff] = a.valueAt$mcF$sp(aoff) + bvalue;
                  ++resultOff;
               }

               while(boff < bsize) {
                  resultI[resultOff] = b.indexAt(boff);
                  resultV[resultOff] = q + b.valueAt$mcF$sp(boff);
                  ++resultOff;
                  ++boff;
               }

               return resultOff != resultI.length ? new SparseVector$mcF$sp(Arrays.copyOf(resultI, resultOff), Arrays.copyOf(resultV, resultOff), resultOff, a.length(), Zero$.MODULE$.FloatZero()) : new SparseVector$mcF$sp(resultI, resultV, resultOff, a.length(), Zero$.MODULE$.FloatZero());
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_V_eq_V_idempotent_Float_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Long_OpAdd_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int asize = a.activeSize();
               int bsize = b.activeSize();
               long q = 0L;
               int[] resultI = new int[asize + bsize];
               long[] resultV = new long[asize + bsize];
               int resultOff = 0;
               int aoff = 0;

               int boff;
               for(boff = 0; aoff < asize; ++aoff) {
                  while(boff < bsize && b.indexAt(boff) < a.indexAt(aoff)) {
                     resultI[resultOff] = b.indexAt(boff);
                     resultV[resultOff] = q + b.valueAt$mcJ$sp(boff);
                     ++resultOff;
                     ++boff;
                  }

                  long var10000;
                  if (boff < bsize && b.indexAt(boff) == a.indexAt(aoff)) {
                     long bv = b.valueAt$mcJ$sp(boff);
                     ++boff;
                     var10000 = bv;
                  } else {
                     var10000 = q;
                  }

                  long bvalue = var10000;
                  resultI[resultOff] = a.indexAt(aoff);
                  resultV[resultOff] = a.valueAt$mcJ$sp(aoff) + bvalue;
                  ++resultOff;
               }

               while(boff < bsize) {
                  resultI[resultOff] = b.indexAt(boff);
                  resultV[resultOff] = q + b.valueAt$mcJ$sp(boff);
                  ++resultOff;
                  ++boff;
               }

               return resultOff != resultI.length ? new SparseVector$mcJ$sp(Arrays.copyOf(resultI, resultOff), Arrays.copyOf(resultV, resultOff), resultOff, a.length(), Zero$.MODULE$.LongZero()) : new SparseVector$mcJ$sp(resultI, resultV, resultOff, a.length(), Zero$.MODULE$.LongZero());
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_V_eq_V_idempotent_Long_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Int_OpSub_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int asize = a.activeSize();
               int bsize = b.activeSize();
               int q = 0;
               int[] resultI = new int[asize + bsize];
               int[] resultV = new int[asize + bsize];
               int resultOff = 0;
               int aoff = 0;

               int boff;
               for(boff = 0; aoff < asize; ++aoff) {
                  while(boff < bsize && b.indexAt(boff) < a.indexAt(aoff)) {
                     resultI[resultOff] = b.indexAt(boff);
                     resultV[resultOff] = q - b.valueAt$mcI$sp(boff);
                     ++resultOff;
                     ++boff;
                  }

                  int var10000;
                  if (boff < bsize && b.indexAt(boff) == a.indexAt(aoff)) {
                     int bv = b.valueAt$mcI$sp(boff);
                     ++boff;
                     var10000 = bv;
                  } else {
                     var10000 = q;
                  }

                  int bvalue = var10000;
                  resultI[resultOff] = a.indexAt(aoff);
                  resultV[resultOff] = a.valueAt$mcI$sp(aoff) - bvalue;
                  ++resultOff;
               }

               while(boff < bsize) {
                  resultI[resultOff] = b.indexAt(boff);
                  resultV[resultOff] = q - b.valueAt$mcI$sp(boff);
                  ++resultOff;
                  ++boff;
               }

               return resultOff != resultI.length ? new SparseVector$mcI$sp(Arrays.copyOf(resultI, resultOff), Arrays.copyOf(resultV, resultOff), resultOff, a.length(), Zero$.MODULE$.IntZero()) : new SparseVector$mcI$sp(resultI, resultV, resultOff, a.length(), Zero$.MODULE$.IntZero());
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_V_eq_V_idempotent_Int_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Double_OpSub_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int asize = a.activeSize();
               int bsize = b.activeSize();
               double q = (double)0.0F;
               int[] resultI = new int[asize + bsize];
               double[] resultV = new double[asize + bsize];
               int resultOff = 0;
               int aoff = 0;

               int boff;
               for(boff = 0; aoff < asize; ++aoff) {
                  while(boff < bsize && b.indexAt(boff) < a.indexAt(aoff)) {
                     resultI[resultOff] = b.indexAt(boff);
                     resultV[resultOff] = q - b.valueAt$mcD$sp(boff);
                     ++resultOff;
                     ++boff;
                  }

                  double var10000;
                  if (boff < bsize && b.indexAt(boff) == a.indexAt(aoff)) {
                     double bv = b.valueAt$mcD$sp(boff);
                     ++boff;
                     var10000 = bv;
                  } else {
                     var10000 = q;
                  }

                  double bvalue = var10000;
                  resultI[resultOff] = a.indexAt(aoff);
                  resultV[resultOff] = a.valueAt$mcD$sp(aoff) - bvalue;
                  ++resultOff;
               }

               while(boff < bsize) {
                  resultI[resultOff] = b.indexAt(boff);
                  resultV[resultOff] = q - b.valueAt$mcD$sp(boff);
                  ++resultOff;
                  ++boff;
               }

               return resultOff != resultI.length ? new SparseVector$mcD$sp(Arrays.copyOf(resultI, resultOff), Arrays.copyOf(resultV, resultOff), resultOff, a.length(), Zero$.MODULE$.DoubleZero()) : new SparseVector$mcD$sp(resultI, resultV, resultOff, a.length(), Zero$.MODULE$.DoubleZero());
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_V_eq_V_idempotent_Double_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Float_OpSub_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int asize = a.activeSize();
               int bsize = b.activeSize();
               float q = 0.0F;
               int[] resultI = new int[asize + bsize];
               float[] resultV = new float[asize + bsize];
               int resultOff = 0;
               int aoff = 0;

               int boff;
               for(boff = 0; aoff < asize; ++aoff) {
                  while(boff < bsize && b.indexAt(boff) < a.indexAt(aoff)) {
                     resultI[resultOff] = b.indexAt(boff);
                     resultV[resultOff] = q - b.valueAt$mcF$sp(boff);
                     ++resultOff;
                     ++boff;
                  }

                  float var10000;
                  if (boff < bsize && b.indexAt(boff) == a.indexAt(aoff)) {
                     float bv = b.valueAt$mcF$sp(boff);
                     ++boff;
                     var10000 = bv;
                  } else {
                     var10000 = q;
                  }

                  float bvalue = var10000;
                  resultI[resultOff] = a.indexAt(aoff);
                  resultV[resultOff] = a.valueAt$mcF$sp(aoff) - bvalue;
                  ++resultOff;
               }

               while(boff < bsize) {
                  resultI[resultOff] = b.indexAt(boff);
                  resultV[resultOff] = q - b.valueAt$mcF$sp(boff);
                  ++resultOff;
                  ++boff;
               }

               return resultOff != resultI.length ? new SparseVector$mcF$sp(Arrays.copyOf(resultI, resultOff), Arrays.copyOf(resultV, resultOff), resultOff, a.length(), Zero$.MODULE$.FloatZero()) : new SparseVector$mcF$sp(resultI, resultV, resultOff, a.length(), Zero$.MODULE$.FloatZero());
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_V_eq_V_idempotent_Float_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Long_OpSub_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int asize = a.activeSize();
               int bsize = b.activeSize();
               long q = 0L;
               int[] resultI = new int[asize + bsize];
               long[] resultV = new long[asize + bsize];
               int resultOff = 0;
               int aoff = 0;

               int boff;
               for(boff = 0; aoff < asize; ++aoff) {
                  while(boff < bsize && b.indexAt(boff) < a.indexAt(aoff)) {
                     resultI[resultOff] = b.indexAt(boff);
                     resultV[resultOff] = q - b.valueAt$mcJ$sp(boff);
                     ++resultOff;
                     ++boff;
                  }

                  long var10000;
                  if (boff < bsize && b.indexAt(boff) == a.indexAt(aoff)) {
                     long bv = b.valueAt$mcJ$sp(boff);
                     ++boff;
                     var10000 = bv;
                  } else {
                     var10000 = q;
                  }

                  long bvalue = var10000;
                  resultI[resultOff] = a.indexAt(aoff);
                  resultV[resultOff] = a.valueAt$mcJ$sp(aoff) - bvalue;
                  ++resultOff;
               }

               while(boff < bsize) {
                  resultI[resultOff] = b.indexAt(boff);
                  resultV[resultOff] = q - b.valueAt$mcJ$sp(boff);
                  ++resultOff;
                  ++boff;
               }

               return resultOff != resultI.length ? new SparseVector$mcJ$sp(Arrays.copyOf(resultI, resultOff), Arrays.copyOf(resultV, resultOff), resultOff, a.length(), Zero$.MODULE$.LongZero()) : new SparseVector$mcJ$sp(resultI, resultV, resultOff, a.length(), Zero$.MODULE$.LongZero());
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_V_eq_V_idempotent_Long_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_OpMulScalar_SV_SV_eq_SV_Int_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final SparseVector b) {
            while(b.activeSize() < a.activeSize()) {
               SparseVector var10000 = b;
               b = a;
               a = var10000;
            }

            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int asize = a.activeSize();
               int bsize = b.activeSize();
               int[] resultI = new int[scala.math.package..MODULE$.min(asize, bsize)];
               int[] resultV = new int[scala.math.package..MODULE$.min(asize, bsize)];
               int resultOff = 0;
               int aoff = 0;
               int boff = 0;

               while(aoff < asize) {
                  int aind = a.indexAt(aoff);
                  boff = Arrays.binarySearch(b.index(), boff, scala.math.package..MODULE$.min(bsize, aind + 1), aind);
                  if (boff < 0) {
                     boff = ~boff;
                     if (boff == bsize) {
                        aoff = asize;
                     } else {
                        int bind = b.indexAt(boff);
                        int newAoff = Arrays.binarySearch(a.index(), aoff, scala.math.package..MODULE$.min(asize, bind + 1), bind);
                        if (newAoff < 0) {
                           newAoff = ~newAoff;
                           ++boff;
                        }

                        boolean cond$macro$3 = newAoff > aoff;
                        if (!cond$macro$3) {
                           throw new AssertionError((new StringBuilder(35)).append("assertion failed: ").append((new StringBuilder(6)).append(bind).append(" ").append(aoff).append(" ").append(newAoff).append(" ").append(a.index()[aoff]).append(" ").append(a.index()[newAoff]).append(" ").append(a).append(" ").append(b).toString()).append(": ").append("newAoff.>(aoff)").toString());
                        }

                        aoff = newAoff;
                     }
                  } else {
                     resultI[resultOff] = aind;
                     resultV[resultOff] = a.valueAt$mcI$sp(aoff) * b.valueAt$mcI$sp(boff);
                     ++aoff;
                     ++boff;
                     ++resultOff;
                  }
               }

               return resultOff != resultI.length ? new SparseVector$mcI$sp(Arrays.copyOf(resultI, resultOff), Arrays.copyOf(resultV, resultOff), resultOff, a.length(), Zero$.MODULE$.IntZero()) : new SparseVector$mcI$sp(resultI, resultV, resultOff, a.length(), Zero$.MODULE$.IntZero());
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_V_eq_V_nilpotent_Int())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_OpMulScalar_SV_SV_eq_SV_Double_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final SparseVector b) {
            while(b.activeSize() < a.activeSize()) {
               SparseVector var10000 = b;
               b = a;
               a = var10000;
            }

            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int asize = a.activeSize();
               int bsize = b.activeSize();
               int[] resultI = new int[scala.math.package..MODULE$.min(asize, bsize)];
               double[] resultV = new double[scala.math.package..MODULE$.min(asize, bsize)];
               int resultOff = 0;
               int aoff = 0;
               int boff = 0;

               while(aoff < asize) {
                  int aind = a.indexAt(aoff);
                  boff = Arrays.binarySearch(b.index(), boff, scala.math.package..MODULE$.min(bsize, aind + 1), aind);
                  if (boff < 0) {
                     boff = ~boff;
                     if (boff == bsize) {
                        aoff = asize;
                     } else {
                        int bind = b.indexAt(boff);
                        int newAoff = Arrays.binarySearch(a.index(), aoff, scala.math.package..MODULE$.min(asize, bind + 1), bind);
                        if (newAoff < 0) {
                           newAoff = ~newAoff;
                           ++boff;
                        }

                        boolean cond$macro$3 = newAoff > aoff;
                        if (!cond$macro$3) {
                           throw new AssertionError((new StringBuilder(35)).append("assertion failed: ").append((new StringBuilder(6)).append(bind).append(" ").append(aoff).append(" ").append(newAoff).append(" ").append(a.index()[aoff]).append(" ").append(a.index()[newAoff]).append(" ").append(a).append(" ").append(b).toString()).append(": ").append("newAoff.>(aoff)").toString());
                        }

                        aoff = newAoff;
                     }
                  } else {
                     resultI[resultOff] = aind;
                     resultV[resultOff] = a.valueAt$mcD$sp(aoff) * b.valueAt$mcD$sp(boff);
                     ++aoff;
                     ++boff;
                     ++resultOff;
                  }
               }

               return resultOff != resultI.length ? new SparseVector$mcD$sp(Arrays.copyOf(resultI, resultOff), Arrays.copyOf(resultV, resultOff), resultOff, a.length(), Zero$.MODULE$.DoubleZero()) : new SparseVector$mcD$sp(resultI, resultV, resultOff, a.length(), Zero$.MODULE$.DoubleZero());
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_V_eq_V_nilpotent_Double())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_OpMulScalar_SV_SV_eq_SV_Float_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final SparseVector b) {
            while(b.activeSize() < a.activeSize()) {
               SparseVector var10000 = b;
               b = a;
               a = var10000;
            }

            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int asize = a.activeSize();
               int bsize = b.activeSize();
               int[] resultI = new int[scala.math.package..MODULE$.min(asize, bsize)];
               float[] resultV = new float[scala.math.package..MODULE$.min(asize, bsize)];
               int resultOff = 0;
               int aoff = 0;
               int boff = 0;

               while(aoff < asize) {
                  int aind = a.indexAt(aoff);
                  boff = Arrays.binarySearch(b.index(), boff, scala.math.package..MODULE$.min(bsize, aind + 1), aind);
                  if (boff < 0) {
                     boff = ~boff;
                     if (boff == bsize) {
                        aoff = asize;
                     } else {
                        int bind = b.indexAt(boff);
                        int newAoff = Arrays.binarySearch(a.index(), aoff, scala.math.package..MODULE$.min(asize, bind + 1), bind);
                        if (newAoff < 0) {
                           newAoff = ~newAoff;
                           ++boff;
                        }

                        boolean cond$macro$3 = newAoff > aoff;
                        if (!cond$macro$3) {
                           throw new AssertionError((new StringBuilder(35)).append("assertion failed: ").append((new StringBuilder(6)).append(bind).append(" ").append(aoff).append(" ").append(newAoff).append(" ").append(a.index()[aoff]).append(" ").append(a.index()[newAoff]).append(" ").append(a).append(" ").append(b).toString()).append(": ").append("newAoff.>(aoff)").toString());
                        }

                        aoff = newAoff;
                     }
                  } else {
                     resultI[resultOff] = aind;
                     resultV[resultOff] = a.valueAt$mcF$sp(aoff) * b.valueAt$mcF$sp(boff);
                     ++aoff;
                     ++boff;
                     ++resultOff;
                  }
               }

               return resultOff != resultI.length ? new SparseVector$mcF$sp(Arrays.copyOf(resultI, resultOff), Arrays.copyOf(resultV, resultOff), resultOff, a.length(), Zero$.MODULE$.FloatZero()) : new SparseVector$mcF$sp(resultI, resultV, resultOff, a.length(), Zero$.MODULE$.FloatZero());
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_V_eq_V_nilpotent_Float())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_OpMulScalar_SV_SV_eq_SV_Long_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final SparseVector b) {
            while(b.activeSize() < a.activeSize()) {
               SparseVector var10000 = b;
               b = a;
               a = var10000;
            }

            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int asize = a.activeSize();
               int bsize = b.activeSize();
               int[] resultI = new int[scala.math.package..MODULE$.min(asize, bsize)];
               long[] resultV = new long[scala.math.package..MODULE$.min(asize, bsize)];
               int resultOff = 0;
               int aoff = 0;
               int boff = 0;

               while(aoff < asize) {
                  int aind = a.indexAt(aoff);
                  boff = Arrays.binarySearch(b.index(), boff, scala.math.package..MODULE$.min(bsize, aind + 1), aind);
                  if (boff < 0) {
                     boff = ~boff;
                     if (boff == bsize) {
                        aoff = asize;
                     } else {
                        int bind = b.indexAt(boff);
                        int newAoff = Arrays.binarySearch(a.index(), aoff, scala.math.package..MODULE$.min(asize, bind + 1), bind);
                        if (newAoff < 0) {
                           newAoff = ~newAoff;
                           ++boff;
                        }

                        boolean cond$macro$3 = newAoff > aoff;
                        if (!cond$macro$3) {
                           throw new AssertionError((new StringBuilder(35)).append("assertion failed: ").append((new StringBuilder(6)).append(bind).append(" ").append(aoff).append(" ").append(newAoff).append(" ").append(a.index()[aoff]).append(" ").append(a.index()[newAoff]).append(" ").append(a).append(" ").append(b).toString()).append(": ").append("newAoff.>(aoff)").toString());
                        }

                        aoff = newAoff;
                     }
                  } else {
                     resultI[resultOff] = aind;
                     resultV[resultOff] = a.valueAt$mcJ$sp(aoff) * b.valueAt$mcJ$sp(boff);
                     ++aoff;
                     ++boff;
                     ++resultOff;
                  }
               }

               return resultOff != resultI.length ? new SparseVector$mcJ$sp(Arrays.copyOf(resultI, resultOff), Arrays.copyOf(resultV, resultOff), resultOff, a.length(), Zero$.MODULE$.LongZero()) : new SparseVector$mcJ$sp(resultI, resultV, resultOff, a.length(), Zero$.MODULE$.LongZero());
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_V_eq_V_nilpotent_Long())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Int_OpDiv_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcI$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringInt(), scala.reflect.ClassTag..MODULE$.Int());

               for(int i = 0; i < a.length(); ++i) {
                  result.add$mcI$sp(i, a.apply$mcI$sp(i) / b.apply$mcI$sp(i));
               }

               return result.toSparseVector$mcI$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_V_eq_V_Int_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Double_OpDiv_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcD$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringD(), scala.reflect.ClassTag..MODULE$.Double());

               for(int i = 0; i < a.length(); ++i) {
                  result.add$mcD$sp(i, a.apply$mcD$sp(i) / b.apply$mcD$sp(i));
               }

               return result.toSparseVector$mcD$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_V_eq_V_Double_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Float_OpDiv_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcF$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringFloat(), scala.reflect.ClassTag..MODULE$.Float());

               for(int i = 0; i < a.length(); ++i) {
                  result.add$mcF$sp(i, a.apply$mcF$sp(i) / b.apply$mcF$sp(i));
               }

               return result.toSparseVector$mcF$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_V_eq_V_Float_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Long_OpDiv_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcJ$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringLong(), scala.reflect.ClassTag..MODULE$.Long());

               for(int i = 0; i < a.length(); ++i) {
                  result.add$mcJ$sp(i, a.apply$mcJ$sp(i) / b.apply$mcJ$sp(i));
               }

               return result.toSparseVector$mcJ$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_V_eq_V_Long_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Int_OpSet_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcI$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringInt(), scala.reflect.ClassTag..MODULE$.Int());

               for(int i = 0; i < a.length(); ++i) {
                  result.add$mcI$sp(i, b.apply$mcI$sp(i));
               }

               return result.toSparseVector$mcI$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_V_eq_V_Int_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Double_OpSet_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcD$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringD(), scala.reflect.ClassTag..MODULE$.Double());

               for(int i = 0; i < a.length(); ++i) {
                  result.add$mcD$sp(i, b.apply$mcD$sp(i));
               }

               return result.toSparseVector$mcD$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_V_eq_V_Double_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Float_OpSet_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcF$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringFloat(), scala.reflect.ClassTag..MODULE$.Float());

               for(int i = 0; i < a.length(); ++i) {
                  result.add$mcF$sp(i, b.apply$mcF$sp(i));
               }

               return result.toSparseVector$mcF$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_V_eq_V_Float_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Long_OpSet_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcJ$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringLong(), scala.reflect.ClassTag..MODULE$.Long());

               for(int i = 0; i < a.length(); ++i) {
                  result.add$mcJ$sp(i, b.apply$mcJ$sp(i));
               }

               return result.toSparseVector$mcJ$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_V_eq_V_Long_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Int_OpMod_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcI$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringInt(), scala.reflect.ClassTag..MODULE$.Int());

               for(int i = 0; i < a.length(); ++i) {
                  result.add$mcI$sp(i, a.apply$mcI$sp(i) % b.apply$mcI$sp(i));
               }

               return result.toSparseVector$mcI$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_V_eq_V_Int_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Double_OpMod_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcD$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringD(), scala.reflect.ClassTag..MODULE$.Double());

               for(int i = 0; i < a.length(); ++i) {
                  result.add$mcD$sp(i, a.apply$mcD$sp(i) % b.apply$mcD$sp(i));
               }

               return result.toSparseVector$mcD$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_V_eq_V_Double_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Float_OpMod_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcF$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringFloat(), scala.reflect.ClassTag..MODULE$.Float());

               for(int i = 0; i < a.length(); ++i) {
                  result.add$mcF$sp(i, a.apply$mcF$sp(i) % b.apply$mcF$sp(i));
               }

               return result.toSparseVector$mcF$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_V_eq_V_Float_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Long_OpMod_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcJ$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringLong(), scala.reflect.ClassTag..MODULE$.Long());

               for(int i = 0; i < a.length(); ++i) {
                  result.add$mcJ$sp(i, a.apply$mcJ$sp(i) % b.apply$mcJ$sp(i));
               }

               return result.toSparseVector$mcJ$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_V_eq_V_Long_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Int_OpPow_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcI$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringInt(), scala.reflect.ClassTag..MODULE$.Int());

               for(int i = 0; i < a.length(); ++i) {
                  result.add$mcI$sp(i, PowImplicits$.MODULE$.IntPow(a.apply$mcI$sp(i)).pow(b.apply$mcI$sp(i)));
               }

               return result.toSparseVector$mcI$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_V_eq_V_Int_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Double_OpPow_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcD$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringD(), scala.reflect.ClassTag..MODULE$.Double());

               for(int i = 0; i < a.length(); ++i) {
                  result.add$mcD$sp(i, PowImplicits$.MODULE$.DoublePow(a.apply$mcD$sp(i)).pow(b.apply$mcD$sp(i)));
               }

               return result.toSparseVector$mcD$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_V_eq_V_Double_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Float_OpPow_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcF$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringFloat(), scala.reflect.ClassTag..MODULE$.Float());

               for(int i = 0; i < a.length(); ++i) {
                  result.add$mcF$sp(i, PowImplicits$.MODULE$.FloatPow(a.apply$mcF$sp(i)).pow(b.apply$mcF$sp(i)));
               }

               return result.toSparseVector$mcF$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_V_eq_V_Float_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_SV_eq_SV_Long_OpPow_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final SparseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcJ$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringLong(), scala.reflect.ClassTag..MODULE$.Long());

               for(int i = 0; i < a.length(); ++i) {
                  result.add$mcJ$sp(i, PowImplicits$.MODULE$.LongPow(a.apply$mcJ$sp(i)).pow(b.apply$mcJ$sp(i)));
               }

               return result.toSparseVector$mcJ$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_V_eq_V_Long_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Int_OpDiv_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcI$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringInt(), scala.reflect.ClassTag..MODULE$.Int());
               int index$macro$4 = 0;

               for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  result.add$mcI$sp(index$macro$4, a.apply$mcI$sp(index$macro$4) / b.apply$mcII$sp(index$macro$4));
               }

               return result.toSparseVector$mcI$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_V_eq_V_Int_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Double_OpDiv_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcD$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringD(), scala.reflect.ClassTag..MODULE$.Double());
               int index$macro$4 = 0;

               for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  result.add$mcD$sp(index$macro$4, a.apply$mcD$sp(index$macro$4) / b.apply$mcID$sp(index$macro$4));
               }

               return result.toSparseVector$mcD$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_V_eq_V_Double_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Float_OpDiv_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcF$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringFloat(), scala.reflect.ClassTag..MODULE$.Float());
               int index$macro$4 = 0;

               for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  result.add$mcF$sp(index$macro$4, a.apply$mcF$sp(index$macro$4) / b.apply$mcIF$sp(index$macro$4));
               }

               return result.toSparseVector$mcF$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_V_eq_V_Float_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Long_OpDiv_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcJ$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringLong(), scala.reflect.ClassTag..MODULE$.Long());
               int index$macro$4 = 0;

               for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  result.add$mcJ$sp(index$macro$4, a.apply$mcJ$sp(index$macro$4) / b.apply$mcIJ$sp(index$macro$4));
               }

               return result.toSparseVector$mcJ$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_V_eq_V_Long_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Int_OpSet_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcI$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringInt(), scala.reflect.ClassTag..MODULE$.Int());
               int index$macro$4 = 0;

               for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  result.add$mcI$sp(index$macro$4, b.apply$mcII$sp(index$macro$4));
               }

               return result.toSparseVector$mcI$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_V_eq_V_Int_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Double_OpSet_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcD$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringD(), scala.reflect.ClassTag..MODULE$.Double());
               int index$macro$4 = 0;

               for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  result.add$mcD$sp(index$macro$4, b.apply$mcID$sp(index$macro$4));
               }

               return result.toSparseVector$mcD$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_V_eq_V_Double_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Float_OpSet_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcF$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringFloat(), scala.reflect.ClassTag..MODULE$.Float());
               int index$macro$4 = 0;

               for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  result.add$mcF$sp(index$macro$4, b.apply$mcIF$sp(index$macro$4));
               }

               return result.toSparseVector$mcF$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_V_eq_V_Float_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Long_OpSet_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcJ$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringLong(), scala.reflect.ClassTag..MODULE$.Long());
               int index$macro$4 = 0;

               for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  result.add$mcJ$sp(index$macro$4, b.apply$mcIJ$sp(index$macro$4));
               }

               return result.toSparseVector$mcJ$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_V_eq_V_Long_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Int_OpMod_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcI$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringInt(), scala.reflect.ClassTag..MODULE$.Int());
               int index$macro$4 = 0;

               for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  result.add$mcI$sp(index$macro$4, a.apply$mcI$sp(index$macro$4) % b.apply$mcII$sp(index$macro$4));
               }

               return result.toSparseVector$mcI$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_V_eq_V_Int_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Double_OpMod_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcD$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringD(), scala.reflect.ClassTag..MODULE$.Double());
               int index$macro$4 = 0;

               for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  result.add$mcD$sp(index$macro$4, a.apply$mcD$sp(index$macro$4) % b.apply$mcID$sp(index$macro$4));
               }

               return result.toSparseVector$mcD$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_V_eq_V_Double_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Float_OpMod_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcF$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringFloat(), scala.reflect.ClassTag..MODULE$.Float());
               int index$macro$4 = 0;

               for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  result.add$mcF$sp(index$macro$4, a.apply$mcF$sp(index$macro$4) % b.apply$mcIF$sp(index$macro$4));
               }

               return result.toSparseVector$mcF$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_V_eq_V_Float_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Long_OpMod_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcJ$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringLong(), scala.reflect.ClassTag..MODULE$.Long());
               int index$macro$4 = 0;

               for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  result.add$mcJ$sp(index$macro$4, a.apply$mcJ$sp(index$macro$4) % b.apply$mcIJ$sp(index$macro$4));
               }

               return result.toSparseVector$mcJ$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_V_eq_V_Long_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Int_OpPow_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcI$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringInt(), scala.reflect.ClassTag..MODULE$.Int());
               int index$macro$4 = 0;

               for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  result.add$mcI$sp(index$macro$4, PowImplicits$.MODULE$.IntPow(a.apply$mcI$sp(index$macro$4)).pow(b.apply$mcII$sp(index$macro$4)));
               }

               return result.toSparseVector$mcI$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_V_eq_V_Int_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Double_OpPow_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcD$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringD(), scala.reflect.ClassTag..MODULE$.Double());
               int index$macro$4 = 0;

               for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  result.add$mcD$sp(index$macro$4, PowImplicits$.MODULE$.DoublePow(a.apply$mcD$sp(index$macro$4)).pow(b.apply$mcID$sp(index$macro$4)));
               }

               return result.toSparseVector$mcD$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_V_eq_V_Double_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Float_OpPow_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcF$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringFloat(), scala.reflect.ClassTag..MODULE$.Float());
               int index$macro$4 = 0;

               for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  result.add$mcF$sp(index$macro$4, PowImplicits$.MODULE$.FloatPow(a.apply$mcF$sp(index$macro$4)).pow(b.apply$mcIF$sp(index$macro$4)));
               }

               return result.toSparseVector$mcF$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_V_eq_V_Float_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_V_eq_SV_Long_OpPow_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = new VectorBuilder$mcJ$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringLong(), scala.reflect.ClassTag..MODULE$.Long());
               int index$macro$4 = 0;

               for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  result.add$mcJ$sp(index$macro$4, PowImplicits$.MODULE$.LongPow(a.apply$mcJ$sp(index$macro$4)).pow(b.apply$mcIJ$sp(index$macro$4)));
               }

               return result.toSparseVector$mcJ$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_V_eq_V_Long_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Int_OpAdd_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final int b) {
            VectorBuilder result = new VectorBuilder$mcI$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringInt(), scala.reflect.ClassTag..MODULE$.Int());
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               int r = a.apply$mcI$sp(index$macro$2) + b;
               if (r != 0) {
                  result.add$mcI$sp(index$macro$2, r);
               }
            }

            return result.toSparseVector$mcI$sp(true, true);
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_S_eq_V_Int_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.Int());
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Double_OpAdd_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final double b) {
            VectorBuilder result = new VectorBuilder$mcD$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringD(), scala.reflect.ClassTag..MODULE$.Double());
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               double r = a.apply$mcD$sp(index$macro$2) + b;
               if (r != (double)0.0F) {
                  result.add$mcD$sp(index$macro$2, r);
               }
            }

            return result.toSparseVector$mcD$sp(true, true);
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_S_eq_V_Double_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.Double());
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Float_OpAdd_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final float b) {
            VectorBuilder result = new VectorBuilder$mcF$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringFloat(), scala.reflect.ClassTag..MODULE$.Float());
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               float r = a.apply$mcF$sp(index$macro$2) + b;
               if (r != 0.0F) {
                  result.add$mcF$sp(index$macro$2, r);
               }
            }

            return result.toSparseVector$mcF$sp(true, true);
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_S_eq_V_Float_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.Float());
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Long_OpAdd_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final long b) {
            VectorBuilder result = new VectorBuilder$mcJ$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringLong(), scala.reflect.ClassTag..MODULE$.Long());
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               long r = a.apply$mcJ$sp(index$macro$2) + b;
               if (r != 0L) {
                  result.add$mcJ$sp(index$macro$2, r);
               }
            }

            return result.toSparseVector$mcJ$sp(true, true);
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_S_eq_V_Long_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.Long());
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Int_OpSub_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final int b) {
            VectorBuilder result = new VectorBuilder$mcI$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringInt(), scala.reflect.ClassTag..MODULE$.Int());
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               int r = a.apply$mcI$sp(index$macro$2) - b;
               if (r != 0) {
                  result.add$mcI$sp(index$macro$2, r);
               }
            }

            return result.toSparseVector$mcI$sp(true, true);
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_S_eq_V_Int_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.Int());
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Double_OpSub_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final double b) {
            VectorBuilder result = new VectorBuilder$mcD$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringD(), scala.reflect.ClassTag..MODULE$.Double());
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               double r = a.apply$mcD$sp(index$macro$2) - b;
               if (r != (double)0.0F) {
                  result.add$mcD$sp(index$macro$2, r);
               }
            }

            return result.toSparseVector$mcD$sp(true, true);
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_S_eq_V_Double_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.Double());
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Float_OpSub_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final float b) {
            VectorBuilder result = new VectorBuilder$mcF$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringFloat(), scala.reflect.ClassTag..MODULE$.Float());
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               float r = a.apply$mcF$sp(index$macro$2) - b;
               if (r != 0.0F) {
                  result.add$mcF$sp(index$macro$2, r);
               }
            }

            return result.toSparseVector$mcF$sp(true, true);
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_S_eq_V_Float_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.Float());
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Long_OpSub_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final long b) {
            VectorBuilder result = new VectorBuilder$mcJ$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringLong(), scala.reflect.ClassTag..MODULE$.Long());
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               long r = a.apply$mcJ$sp(index$macro$2) - b;
               if (r != 0L) {
                  result.add$mcJ$sp(index$macro$2, r);
               }
            }

            return result.toSparseVector$mcJ$sp(true, true);
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_S_eq_V_Long_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.Long());
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Int_OpSet_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final int b) {
            VectorBuilder result = new VectorBuilder$mcI$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringInt(), scala.reflect.ClassTag..MODULE$.Int());
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               if (b != 0) {
                  result.add$mcI$sp(index$macro$2, b);
               }
            }

            return result.toSparseVector$mcI$sp(true, true);
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_S_eq_V_Int_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.Int());
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Double_OpSet_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final double b) {
            VectorBuilder result = new VectorBuilder$mcD$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringD(), scala.reflect.ClassTag..MODULE$.Double());
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               if (b != (double)0.0F) {
                  result.add$mcD$sp(index$macro$2, b);
               }
            }

            return result.toSparseVector$mcD$sp(true, true);
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_S_eq_V_Double_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.Double());
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Float_OpSet_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final float b) {
            VectorBuilder result = new VectorBuilder$mcF$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringFloat(), scala.reflect.ClassTag..MODULE$.Float());
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               if (b != 0.0F) {
                  result.add$mcF$sp(index$macro$2, b);
               }
            }

            return result.toSparseVector$mcF$sp(true, true);
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_S_eq_V_Float_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.Float());
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Long_OpSet_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final long b) {
            VectorBuilder result = new VectorBuilder$mcJ$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringLong(), scala.reflect.ClassTag..MODULE$.Long());
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               if (b != 0L) {
                  result.add$mcJ$sp(index$macro$2, b);
               }
            }

            return result.toSparseVector$mcJ$sp(true, true);
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_S_eq_V_Long_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.Long());
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Int_OpPow_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final int b) {
            VectorBuilder result = new VectorBuilder$mcI$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringInt(), scala.reflect.ClassTag..MODULE$.Int());
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               int r = PowImplicits$.MODULE$.IntPow(a.apply$mcI$sp(index$macro$2)).pow(b);
               if (r != 0) {
                  result.add$mcI$sp(index$macro$2, r);
               }
            }

            return result.toSparseVector$mcI$sp(true, true);
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_S_eq_V_Int_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.Int());
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Double_OpPow_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final double b) {
            VectorBuilder result = new VectorBuilder$mcD$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringD(), scala.reflect.ClassTag..MODULE$.Double());
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               double r = PowImplicits$.MODULE$.DoublePow(a.apply$mcD$sp(index$macro$2)).pow(b);
               if (r != (double)0.0F) {
                  result.add$mcD$sp(index$macro$2, r);
               }
            }

            return result.toSparseVector$mcD$sp(true, true);
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_S_eq_V_Double_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.Double());
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Float_OpPow_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final float b) {
            VectorBuilder result = new VectorBuilder$mcF$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringFloat(), scala.reflect.ClassTag..MODULE$.Float());
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               float r = PowImplicits$.MODULE$.FloatPow(a.apply$mcF$sp(index$macro$2)).pow(b);
               if (r != 0.0F) {
                  result.add$mcF$sp(index$macro$2, r);
               }
            }

            return result.toSparseVector$mcF$sp(true, true);
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_S_eq_V_Float_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.Float());
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Long_OpPow_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final long b) {
            VectorBuilder result = new VectorBuilder$mcJ$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringLong(), scala.reflect.ClassTag..MODULE$.Long());
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               long r = PowImplicits$.MODULE$.LongPow(a.apply$mcJ$sp(index$macro$2)).pow(b);
               if (r != 0L) {
                  result.add$mcJ$sp(index$macro$2, r);
               }
            }

            return result.toSparseVector$mcJ$sp(true, true);
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_S_eq_V_Long_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.Long());
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Int_OpDiv_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final int b) {
            VectorBuilder result = new VectorBuilder$mcI$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringInt(), scala.reflect.ClassTag..MODULE$.Int());
            if (b == 0) {
               int index$macro$2 = 0;

               for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  int r = a.apply$mcI$sp(index$macro$2) / b;
                  if (r != 0) {
                     result.add$mcI$sp(index$macro$2, r);
                  }
               }
            } else {
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.activeSize(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int r = a.valueAt$mcI$sp(index$macro$7) / b;
                  if (r != 0) {
                     result.add$mcI$sp(a.indexAt(index$macro$7), r);
                  }
               }
            }

            return result.toSparseVector$mcI$sp(true, true);
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_S_eq_V_Int_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.Int());
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Double_OpDiv_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final double b) {
            VectorBuilder result = new VectorBuilder$mcD$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringD(), scala.reflect.ClassTag..MODULE$.Double());
            if (b == (double)0.0F) {
               int index$macro$2 = 0;

               for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  double r = a.apply$mcD$sp(index$macro$2) / b;
                  if (r != (double)0.0F) {
                     result.add$mcD$sp(index$macro$2, r);
                  }
               }
            } else {
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.activeSize(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  double r = a.valueAt$mcD$sp(index$macro$7) / b;
                  if (r != (double)0.0F) {
                     result.add$mcD$sp(a.indexAt(index$macro$7), r);
                  }
               }
            }

            return result.toSparseVector$mcD$sp(true, true);
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_S_eq_V_Double_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.Double());
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Float_OpDiv_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final float b) {
            VectorBuilder result = new VectorBuilder$mcF$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringFloat(), scala.reflect.ClassTag..MODULE$.Float());
            if (b == 0.0F) {
               int index$macro$2 = 0;

               for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  float r = a.apply$mcF$sp(index$macro$2) / b;
                  if (r != 0.0F) {
                     result.add$mcF$sp(index$macro$2, r);
                  }
               }
            } else {
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.activeSize(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  float r = a.valueAt$mcF$sp(index$macro$7) / b;
                  if (r != 0.0F) {
                     result.add$mcF$sp(a.indexAt(index$macro$7), r);
                  }
               }
            }

            return result.toSparseVector$mcF$sp(true, true);
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_S_eq_V_Float_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.Float());
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Long_OpDiv_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final long b) {
            VectorBuilder result = new VectorBuilder$mcJ$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringLong(), scala.reflect.ClassTag..MODULE$.Long());
            if (b == 0L) {
               int index$macro$2 = 0;

               for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  long r = a.apply$mcJ$sp(index$macro$2) / b;
                  if (r != 0L) {
                     result.add$mcJ$sp(index$macro$2, r);
                  }
               }
            } else {
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.activeSize(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  long r = a.valueAt$mcJ$sp(index$macro$7) / b;
                  if (r != 0L) {
                     result.add$mcJ$sp(a.indexAt(index$macro$7), r);
                  }
               }
            }

            return result.toSparseVector$mcJ$sp(true, true);
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_S_eq_V_Long_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.Long());
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Int_OpMod_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final int b) {
            VectorBuilder result = new VectorBuilder$mcI$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringInt(), scala.reflect.ClassTag..MODULE$.Int());
            if (b == 0) {
               int index$macro$2 = 0;

               for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  int r = a.apply$mcI$sp(index$macro$2) % b;
                  if (r != 0) {
                     result.add$mcI$sp(index$macro$2, r);
                  }
               }
            } else {
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.activeSize(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int r = a.valueAt$mcI$sp(index$macro$7) % b;
                  if (r != 0) {
                     result.add$mcI$sp(a.indexAt(index$macro$7), r);
                  }
               }
            }

            return result.toSparseVector$mcI$sp(true, true);
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_S_eq_V_Int_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.Int());
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Double_OpMod_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final double b) {
            VectorBuilder result = new VectorBuilder$mcD$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringD(), scala.reflect.ClassTag..MODULE$.Double());
            if (b == (double)0.0F) {
               int index$macro$2 = 0;

               for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  double r = a.apply$mcD$sp(index$macro$2) % b;
                  if (r != (double)0.0F) {
                     result.add$mcD$sp(index$macro$2, r);
                  }
               }
            } else {
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.activeSize(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  double r = a.valueAt$mcD$sp(index$macro$7) % b;
                  if (r != (double)0.0F) {
                     result.add$mcD$sp(a.indexAt(index$macro$7), r);
                  }
               }
            }

            return result.toSparseVector$mcD$sp(true, true);
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_S_eq_V_Double_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.Double());
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Float_OpMod_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final float b) {
            VectorBuilder result = new VectorBuilder$mcF$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringFloat(), scala.reflect.ClassTag..MODULE$.Float());
            if (b == 0.0F) {
               int index$macro$2 = 0;

               for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  float r = a.apply$mcF$sp(index$macro$2) % b;
                  if (r != 0.0F) {
                     result.add$mcF$sp(index$macro$2, r);
                  }
               }
            } else {
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.activeSize(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  float r = a.valueAt$mcF$sp(index$macro$7) % b;
                  if (r != 0.0F) {
                     result.add$mcF$sp(a.indexAt(index$macro$7), r);
                  }
               }
            }

            return result.toSparseVector$mcF$sp(true, true);
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_S_eq_V_Float_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.Float());
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Long_OpMod_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final long b) {
            VectorBuilder result = new VectorBuilder$mcJ$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringLong(), scala.reflect.ClassTag..MODULE$.Long());
            if (b == 0L) {
               int index$macro$2 = 0;

               for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  long r = a.apply$mcJ$sp(index$macro$2) % b;
                  if (r != 0L) {
                     result.add$mcJ$sp(index$macro$2, r);
                  }
               }
            } else {
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.activeSize(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  long r = a.valueAt$mcJ$sp(index$macro$7) % b;
                  if (r != 0L) {
                     result.add$mcJ$sp(a.indexAt(index$macro$7), r);
                  }
               }
            }

            return result.toSparseVector$mcJ$sp(true, true);
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_S_eq_V_Long_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.Long());
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Int_OpMulScalar_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final int b) {
            VectorBuilder result = new VectorBuilder$mcI$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringInt(), scala.reflect.ClassTag..MODULE$.Int());

            for(int i = 0; i < a.activeSize(); ++i) {
               result.add$mcI$sp(a.indexAt(i), a.valueAt$mcI$sp(i) * b);
            }

            return result.toSparseVector$mcI$sp(true, true);
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_S_eq_V_Int_OpMulScalar())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.Int());
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Double_OpMulScalar_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final double b) {
            VectorBuilder result = new VectorBuilder$mcD$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringD(), scala.reflect.ClassTag..MODULE$.Double());

            for(int i = 0; i < a.activeSize(); ++i) {
               result.add$mcD$sp(a.indexAt(i), a.valueAt$mcD$sp(i) * b);
            }

            return result.toSparseVector$mcD$sp(true, true);
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_S_eq_V_Double_OpMulScalar())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.Double());
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Float_OpMulScalar_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final float b) {
            VectorBuilder result = new VectorBuilder$mcF$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringFloat(), scala.reflect.ClassTag..MODULE$.Float());

            for(int i = 0; i < a.activeSize(); ++i) {
               result.add$mcF$sp(a.indexAt(i), a.valueAt$mcF$sp(i) * b);
            }

            return result.toSparseVector$mcF$sp(true, true);
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_S_eq_V_Float_OpMulScalar())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.Float());
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Long_OpMulScalar_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final long b) {
            VectorBuilder result = new VectorBuilder$mcJ$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringLong(), scala.reflect.ClassTag..MODULE$.Long());

            for(int i = 0; i < a.activeSize(); ++i) {
               result.add$mcJ$sp(a.indexAt(i), a.valueAt$mcJ$sp(i) * b);
            }

            return result.toSparseVector$mcJ$sp(true, true);
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_S_eq_V_Long_OpMulScalar())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.Long());
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Int_OpMulMatrix_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final int b) {
            VectorBuilder result = new VectorBuilder$mcI$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringInt(), scala.reflect.ClassTag..MODULE$.Int());

            for(int i = 0; i < a.activeSize(); ++i) {
               result.add$mcI$sp(a.indexAt(i), a.valueAt$mcI$sp(i) * b);
            }

            return result.toSparseVector$mcI$sp(true, true);
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_S_eq_V_Int_OpMulMatrix())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.Int());
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Double_OpMulMatrix_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final double b) {
            VectorBuilder result = new VectorBuilder$mcD$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringD(), scala.reflect.ClassTag..MODULE$.Double());

            for(int i = 0; i < a.activeSize(); ++i) {
               result.add$mcD$sp(a.indexAt(i), a.valueAt$mcD$sp(i) * b);
            }

            return result.toSparseVector$mcD$sp(true, true);
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_S_eq_V_Double_OpMulMatrix())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.Double());
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Float_OpMulMatrix_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final float b) {
            VectorBuilder result = new VectorBuilder$mcF$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringFloat(), scala.reflect.ClassTag..MODULE$.Float());

            for(int i = 0; i < a.activeSize(); ++i) {
               result.add$mcF$sp(a.indexAt(i), a.valueAt$mcF$sp(i) * b);
            }

            return result.toSparseVector$mcF$sp(true, true);
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_S_eq_V_Float_OpMulMatrix())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.Float());
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_Op_SV_S_eq_SV_Long_OpMulMatrix_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final SparseVector a, final long b) {
            VectorBuilder result = new VectorBuilder$mcJ$sp(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringLong(), scala.reflect.ClassTag..MODULE$.Long());

            for(int i = 0; i < a.activeSize(); ++i) {
               result.add$mcJ$sp(a.indexAt(i), a.valueAt$mcJ$sp(i) * b);
            }

            return result.toSparseVector$mcJ$sp(true, true);
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_Op_V_S_eq_V_Long_OpMulMatrix())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.Long());
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_OpMulInner_SV_SV_eq_T_Int_$eq(new UFunc.UImpl2() {
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

         public int apply(final SparseVector a, final SparseVector b) {
            while(true) {
               int left$macro$1 = b.length();
               int right$macro$2 = a.length();
               if (left$macro$1 != right$macro$2) {
                  throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
               }

               if (b.activeSize() >= a.activeSize()) {
                  return a.activeSize() == 0 ? 0 : (b.activeSize() <= 32 ? this.smallVectors(a, b) : this.bigVectors(a, b));
               }

               SparseVector var10000 = b;
               b = a;
               a = var10000;
            }
         }

         private int smallVectors(final SparseVector a, final SparseVector b) {
            int asize = a.activeSize();
            int bsize = b.activeSize();
            int result = 0;
            int aoff = 0;
            int boff = 0;

            while(aoff < asize && boff < bsize) {
               if (a.indexAt(aoff) < b.indexAt(boff)) {
                  ++aoff;
               } else if (b.indexAt(boff) < a.indexAt(aoff)) {
                  ++boff;
               } else {
                  result += a.valueAt$mcI$sp(aoff) * b.valueAt$mcI$sp(boff);
                  ++aoff;
                  ++boff;
               }
            }

            return result;
         }

         private int bigVectors(final SparseVector a, final SparseVector b) {
            int asize = a.activeSize();
            int bsize = b.activeSize();
            int result = 0;
            int aoff = 0;
            int boff = 0;

            while(aoff < asize) {
               int aind = a.indexAt(aoff);
               int bMax = scala.math.package..MODULE$.min(bsize, aind + 1);
               boff = ArrayUtil$.MODULE$.gallopSearch(b.index(), boff, bMax, aind);
               if (boff < 0) {
                  boff = ~boff;
                  if (boff == bsize) {
                     aoff = asize;
                  } else {
                     int bind = b.indexAt(boff);
                     int aMax = scala.math.package..MODULE$.min(asize, bind + 1);
                     int newAoff = ArrayUtil$.MODULE$.gallopSearch(a.index(), aoff, aMax, bind);
                     if (newAoff < 0) {
                        newAoff = ~newAoff;
                        ++boff;
                     }

                     aoff = newAoff;
                  }
               } else {
                  result += a.valueAt$mcI$sp(aoff) * b.valueAt$mcI$sp(boff);
                  ++aoff;
                  ++boff;
               }
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_OpMulInner_V_V_eq_S_Int())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_OpMulInner_SV_SV_eq_T_Double_$eq(new UFunc.UImpl2() {
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

         public double apply(final SparseVector a, final SparseVector b) {
            while(true) {
               int left$macro$1 = b.length();
               int right$macro$2 = a.length();
               if (left$macro$1 != right$macro$2) {
                  throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
               }

               if (b.activeSize() >= a.activeSize()) {
                  return a.activeSize() == 0 ? (double)0.0F : (b.activeSize() <= 32 ? this.smallVectors(a, b) : this.bigVectors(a, b));
               }

               SparseVector var10000 = b;
               b = a;
               a = var10000;
            }
         }

         private double smallVectors(final SparseVector a, final SparseVector b) {
            int asize = a.activeSize();
            int bsize = b.activeSize();
            double result = (double)0.0F;
            int aoff = 0;
            int boff = 0;

            while(aoff < asize && boff < bsize) {
               if (a.indexAt(aoff) < b.indexAt(boff)) {
                  ++aoff;
               } else if (b.indexAt(boff) < a.indexAt(aoff)) {
                  ++boff;
               } else {
                  result += a.valueAt$mcD$sp(aoff) * b.valueAt$mcD$sp(boff);
                  ++aoff;
                  ++boff;
               }
            }

            return result;
         }

         private double bigVectors(final SparseVector a, final SparseVector b) {
            int asize = a.activeSize();
            int bsize = b.activeSize();
            double result = (double)0.0F;
            int aoff = 0;
            int boff = 0;

            while(aoff < asize) {
               int aind = a.indexAt(aoff);
               int bMax = scala.math.package..MODULE$.min(bsize, aind + 1);
               boff = ArrayUtil$.MODULE$.gallopSearch(b.index(), boff, bMax, aind);
               if (boff < 0) {
                  boff = ~boff;
                  if (boff == bsize) {
                     aoff = asize;
                  } else {
                     int bind = b.indexAt(boff);
                     int aMax = scala.math.package..MODULE$.min(asize, bind + 1);
                     int newAoff = ArrayUtil$.MODULE$.gallopSearch(a.index(), aoff, aMax, bind);
                     if (newAoff < 0) {
                        newAoff = ~newAoff;
                        ++boff;
                     }

                     aoff = newAoff;
                  }
               } else {
                  result += a.valueAt$mcD$sp(aoff) * b.valueAt$mcD$sp(boff);
                  ++aoff;
                  ++boff;
               }
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_OpMulInner_V_V_eq_S_Double())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_OpMulInner_SV_SV_eq_T_Float_$eq(new UFunc.UImpl2() {
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

         public float apply(final SparseVector a, final SparseVector b) {
            while(true) {
               int left$macro$1 = b.length();
               int right$macro$2 = a.length();
               if (left$macro$1 != right$macro$2) {
                  throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
               }

               if (b.activeSize() >= a.activeSize()) {
                  return a.activeSize() == 0 ? 0.0F : (b.activeSize() <= 32 ? this.smallVectors(a, b) : this.bigVectors(a, b));
               }

               SparseVector var10000 = b;
               b = a;
               a = var10000;
            }
         }

         private float smallVectors(final SparseVector a, final SparseVector b) {
            int asize = a.activeSize();
            int bsize = b.activeSize();
            float result = 0.0F;
            int aoff = 0;
            int boff = 0;

            while(aoff < asize && boff < bsize) {
               if (a.indexAt(aoff) < b.indexAt(boff)) {
                  ++aoff;
               } else if (b.indexAt(boff) < a.indexAt(aoff)) {
                  ++boff;
               } else {
                  result += a.valueAt$mcF$sp(aoff) * b.valueAt$mcF$sp(boff);
                  ++aoff;
                  ++boff;
               }
            }

            return result;
         }

         private float bigVectors(final SparseVector a, final SparseVector b) {
            int asize = a.activeSize();
            int bsize = b.activeSize();
            float result = 0.0F;
            int aoff = 0;
            int boff = 0;

            while(aoff < asize) {
               int aind = a.indexAt(aoff);
               int bMax = scala.math.package..MODULE$.min(bsize, aind + 1);
               boff = ArrayUtil$.MODULE$.gallopSearch(b.index(), boff, bMax, aind);
               if (boff < 0) {
                  boff = ~boff;
                  if (boff == bsize) {
                     aoff = asize;
                  } else {
                     int bind = b.indexAt(boff);
                     int aMax = scala.math.package..MODULE$.min(asize, bind + 1);
                     int newAoff = ArrayUtil$.MODULE$.gallopSearch(a.index(), aoff, aMax, bind);
                     if (newAoff < 0) {
                        newAoff = ~newAoff;
                        ++boff;
                     }

                     aoff = newAoff;
                  }
               } else {
                  result += a.valueAt$mcF$sp(aoff) * b.valueAt$mcF$sp(boff);
                  ++aoff;
                  ++boff;
               }
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_OpMulInner_V_V_eq_S_Float())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$impl_OpMulInner_SV_SV_eq_T_Long_$eq(new UFunc.UImpl2() {
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

         public long apply(final SparseVector a, final SparseVector b) {
            while(true) {
               int left$macro$1 = b.length();
               int right$macro$2 = a.length();
               if (left$macro$1 != right$macro$2) {
                  throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
               }

               if (b.activeSize() >= a.activeSize()) {
                  return a.activeSize() == 0 ? 0L : (b.activeSize() <= 32 ? this.smallVectors(a, b) : this.bigVectors(a, b));
               }

               SparseVector var10000 = b;
               b = a;
               a = var10000;
            }
         }

         private long smallVectors(final SparseVector a, final SparseVector b) {
            int asize = a.activeSize();
            int bsize = b.activeSize();
            long result = 0L;
            int aoff = 0;
            int boff = 0;

            while(aoff < asize && boff < bsize) {
               if (a.indexAt(aoff) < b.indexAt(boff)) {
                  ++aoff;
               } else if (b.indexAt(boff) < a.indexAt(aoff)) {
                  ++boff;
               } else {
                  result += a.valueAt$mcJ$sp(aoff) * b.valueAt$mcJ$sp(boff);
                  ++aoff;
                  ++boff;
               }
            }

            return result;
         }

         private long bigVectors(final SparseVector a, final SparseVector b) {
            int asize = a.activeSize();
            int bsize = b.activeSize();
            long result = 0L;
            int aoff = 0;
            int boff = 0;

            while(aoff < asize) {
               int aind = a.indexAt(aoff);
               int bMax = scala.math.package..MODULE$.min(bsize, aind + 1);
               boff = ArrayUtil$.MODULE$.gallopSearch(b.index(), boff, bMax, aind);
               if (boff < 0) {
                  boff = ~boff;
                  if (boff == bsize) {
                     aoff = asize;
                  } else {
                     int bind = b.indexAt(boff);
                     int aMax = scala.math.package..MODULE$.min(asize, bind + 1);
                     int newAoff = ArrayUtil$.MODULE$.gallopSearch(a.index(), aoff, aMax, bind);
                     if (newAoff < 0) {
                        newAoff = ~newAoff;
                        ++boff;
                     }

                     aoff = newAoff;
                  }
               } else {
                  result += a.valueAt$mcJ$sp(aoff) * b.valueAt$mcJ$sp(boff);
                  ++aoff;
                  ++boff;
               }
            }

            return result;
         }

         public {
            ((BinaryRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_OpMulInner_V_V_eq_S_Long())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$implScaleAdd_SV_S_SV_InPlace_Int_$eq(new UFunc.InPlaceImpl3() {
         public void apply(final SparseVector y, final int a, final SparseVector x) {
            int left$macro$1 = x.length();
            int right$macro$2 = y.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("x.length == y.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int asize = y.activeSize();
               int bsize = x.activeSize();
               if (a != 0) {
                  int[] resultI = new int[asize + bsize];
                  int[] resultV = new int[asize + bsize];
                  int resultOff = 0;
                  int aoff = 0;

                  int boff;
                  for(boff = 0; aoff < asize; ++aoff) {
                     while(boff < bsize && x.indexAt(boff) < y.indexAt(aoff)) {
                        resultI[resultOff] = x.indexAt(boff);
                        resultV[resultOff] = a * x.valueAt$mcI$sp(boff);
                        ++resultOff;
                        ++boff;
                     }

                     int var10000;
                     if (boff < bsize && x.indexAt(boff) == y.indexAt(aoff)) {
                        int bv = a * x.valueAt$mcI$sp(boff);
                        ++boff;
                        var10000 = bv;
                     } else {
                        var10000 = 0;
                     }

                     int bvalue = var10000;
                     resultI[resultOff] = y.indexAt(aoff);
                     resultV[resultOff] = y.valueAt$mcI$sp(aoff) + bvalue;
                     ++resultOff;
                  }

                  while(boff < bsize) {
                     resultI[resultOff] = x.indexAt(boff);
                     resultV[resultOff] = a * x.valueAt$mcI$sp(boff);
                     ++resultOff;
                     ++boff;
                  }

                  if (resultOff != resultI.length) {
                     y.use$mcI$sp(Arrays.copyOf(resultI, resultOff), Arrays.copyOf(resultV, resultOff), resultOff);
                  } else {
                     y.use$mcI$sp(resultI, resultV, resultOff);
                  }
               }

            }
         }

         public {
            ((TernaryUpdateRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_scaleAdd_InPlace_V_S_V_Int())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$implScaleAdd_SV_S_SV_InPlace_Double_$eq(new UFunc.InPlaceImpl3() {
         public void apply(final SparseVector y, final double a, final SparseVector x) {
            int left$macro$1 = x.length();
            int right$macro$2 = y.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("x.length == y.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int asize = y.activeSize();
               int bsize = x.activeSize();
               if (a != (double)0.0F) {
                  int[] resultI = new int[asize + bsize];
                  double[] resultV = new double[asize + bsize];
                  int resultOff = 0;
                  int aoff = 0;

                  int boff;
                  for(boff = 0; aoff < asize; ++aoff) {
                     while(boff < bsize && x.indexAt(boff) < y.indexAt(aoff)) {
                        resultI[resultOff] = x.indexAt(boff);
                        resultV[resultOff] = a * x.valueAt$mcD$sp(boff);
                        ++resultOff;
                        ++boff;
                     }

                     double var10000;
                     if (boff < bsize && x.indexAt(boff) == y.indexAt(aoff)) {
                        double bv = a * x.valueAt$mcD$sp(boff);
                        ++boff;
                        var10000 = bv;
                     } else {
                        var10000 = (double)0.0F;
                     }

                     double bvalue = var10000;
                     resultI[resultOff] = y.indexAt(aoff);
                     resultV[resultOff] = y.valueAt$mcD$sp(aoff) + bvalue;
                     ++resultOff;
                  }

                  while(boff < bsize) {
                     resultI[resultOff] = x.indexAt(boff);
                     resultV[resultOff] = a * x.valueAt$mcD$sp(boff);
                     ++resultOff;
                     ++boff;
                  }

                  if (resultOff != resultI.length) {
                     y.use$mcD$sp(Arrays.copyOf(resultI, resultOff), Arrays.copyOf(resultV, resultOff), resultOff);
                  } else {
                     y.use$mcD$sp(resultI, resultV, resultOff);
                  }
               }

            }
         }

         public {
            ((TernaryUpdateRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_scaleAdd_InPlace_V_S_V_Double())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.Double(), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$implScaleAdd_SV_S_SV_InPlace_Float_$eq(new UFunc.InPlaceImpl3() {
         public void apply(final SparseVector y, final float a, final SparseVector x) {
            int left$macro$1 = x.length();
            int right$macro$2 = y.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("x.length == y.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int asize = y.activeSize();
               int bsize = x.activeSize();
               if (a != 0.0F) {
                  int[] resultI = new int[asize + bsize];
                  float[] resultV = new float[asize + bsize];
                  int resultOff = 0;
                  int aoff = 0;

                  int boff;
                  for(boff = 0; aoff < asize; ++aoff) {
                     while(boff < bsize && x.indexAt(boff) < y.indexAt(aoff)) {
                        resultI[resultOff] = x.indexAt(boff);
                        resultV[resultOff] = a * x.valueAt$mcF$sp(boff);
                        ++resultOff;
                        ++boff;
                     }

                     float var10000;
                     if (boff < bsize && x.indexAt(boff) == y.indexAt(aoff)) {
                        float bv = a * x.valueAt$mcF$sp(boff);
                        ++boff;
                        var10000 = bv;
                     } else {
                        var10000 = 0.0F;
                     }

                     float bvalue = var10000;
                     resultI[resultOff] = y.indexAt(aoff);
                     resultV[resultOff] = y.valueAt$mcF$sp(aoff) + bvalue;
                     ++resultOff;
                  }

                  while(boff < bsize) {
                     resultI[resultOff] = x.indexAt(boff);
                     resultV[resultOff] = a * x.valueAt$mcF$sp(boff);
                     ++resultOff;
                     ++boff;
                  }

                  if (resultOff != resultI.length) {
                     y.use$mcF$sp(Arrays.copyOf(resultI, resultOff), Arrays.copyOf(resultV, resultOff), resultOff);
                  } else {
                     y.use$mcF$sp(resultI, resultV, resultOff);
                  }
               }

            }
         }

         public {
            ((TernaryUpdateRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_scaleAdd_InPlace_V_S_V_Float())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.Float(), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$implScaleAdd_SV_S_SV_InPlace_Long_$eq(new UFunc.InPlaceImpl3() {
         public void apply(final SparseVector y, final long a, final SparseVector x) {
            int left$macro$1 = x.length();
            int right$macro$2 = y.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("x.length == y.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int asize = y.activeSize();
               int bsize = x.activeSize();
               if (a != 0L) {
                  int[] resultI = new int[asize + bsize];
                  long[] resultV = new long[asize + bsize];
                  int resultOff = 0;
                  int aoff = 0;

                  int boff;
                  for(boff = 0; aoff < asize; ++aoff) {
                     while(boff < bsize && x.indexAt(boff) < y.indexAt(aoff)) {
                        resultI[resultOff] = x.indexAt(boff);
                        resultV[resultOff] = a * x.valueAt$mcJ$sp(boff);
                        ++resultOff;
                        ++boff;
                     }

                     long var10000;
                     if (boff < bsize && x.indexAt(boff) == y.indexAt(aoff)) {
                        long bv = a * x.valueAt$mcJ$sp(boff);
                        ++boff;
                        var10000 = bv;
                     } else {
                        var10000 = 0L;
                     }

                     long bvalue = var10000;
                     resultI[resultOff] = y.indexAt(aoff);
                     resultV[resultOff] = y.valueAt$mcJ$sp(aoff) + bvalue;
                     ++resultOff;
                  }

                  while(boff < bsize) {
                     resultI[resultOff] = x.indexAt(boff);
                     resultV[resultOff] = a * x.valueAt$mcJ$sp(boff);
                     ++resultOff;
                     ++boff;
                  }

                  if (resultOff != resultI.length) {
                     y.use$mcJ$sp(Arrays.copyOf(resultI, resultOff), Arrays.copyOf(resultV, resultOff), resultOff);
                  } else {
                     y.use$mcJ$sp(resultI, resultV, resultOff);
                  }
               }

            }
         }

         public {
            ((TernaryUpdateRegistry)scala.Predef..MODULE$.implicitly(SparseVectorExpandOps.this.impl_scaleAdd_InPlace_V_S_V_Long())).register(this, scala.reflect.ClassTag..MODULE$.apply(SparseVector.class), scala.reflect.ClassTag..MODULE$.Long(), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$zipMap_d_$eq(new SparseVectorExpandOps$CanZipMapValuesSparseVector$mcDD$sp($this, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero(), Semiring$.MODULE$.semiringD()));
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$zipMap_f_$eq($this.new CanZipMapValuesSparseVector(scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero(), Semiring$.MODULE$.semiringFloat()));
      $this.breeze$linalg$operators$SparseVectorExpandOps$_setter_$zipMap_i_$eq(new SparseVectorExpandOps$CanZipMapValuesSparseVector$mcII$sp($this, scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero(), Semiring$.MODULE$.semiringInt()));
   }

   public class CanZipMapValuesSparseVector implements CanZipMapValues {
      public final ClassTag breeze$linalg$operators$SparseVectorExpandOps$CanZipMapValuesSparseVector$$evidence$8;
      public final Zero evidence$9;
      public final Semiring evidence$10;
      // $FF: synthetic field
      public final SparseVectorExpandOps $outer;

      public Object map$mcDD$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcDD$sp$(this, from, from2, fn);
      }

      public Object map$mcFD$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcFD$sp$(this, from, from2, fn);
      }

      public Object map$mcID$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcID$sp$(this, from, from2, fn);
      }

      public Object map$mcJD$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcJD$sp$(this, from, from2, fn);
      }

      public Object map$mcDF$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcDF$sp$(this, from, from2, fn);
      }

      public Object map$mcFF$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcFF$sp$(this, from, from2, fn);
      }

      public Object map$mcIF$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcIF$sp$(this, from, from2, fn);
      }

      public Object map$mcJF$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcJF$sp$(this, from, from2, fn);
      }

      public Object map$mcDI$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcDI$sp$(this, from, from2, fn);
      }

      public Object map$mcFI$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcFI$sp$(this, from, from2, fn);
      }

      public Object map$mcII$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcII$sp$(this, from, from2, fn);
      }

      public Object map$mcJI$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcJI$sp$(this, from, from2, fn);
      }

      public Object map$mcDJ$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcDJ$sp$(this, from, from2, fn);
      }

      public Object map$mcFJ$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcFJ$sp$(this, from, from2, fn);
      }

      public Object map$mcIJ$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcIJ$sp$(this, from, from2, fn);
      }

      public Object map$mcJJ$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcJJ$sp$(this, from, from2, fn);
      }

      public SparseVector create(final int length) {
         return SparseVector$.MODULE$.zeros(length, this.breeze$linalg$operators$SparseVectorExpandOps$CanZipMapValuesSparseVector$$evidence$8, this.evidence$9);
      }

      public SparseVector map(final SparseVector from, final SparseVector from2, final Function2 fn) {
         int left$macro$1 = from.length();
         int right$macro$2 = from2.length();
         if (left$macro$1 != right$macro$2) {
            throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vector lengths must match!: ").append("from.length == from2.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
         } else {
            Object zz = fn.apply(from.default(), from2.default());
            SparseVector var10000;
            if (!BoxesRunTime.equals(zz, ((Zero)scala.Predef..MODULE$.implicitly(this.evidence$9)).zero())) {
               SparseVector result = this.create(from.length());

               for(int i = 0; i < from.length(); ++i) {
                  result.update(i, fn.apply(from.apply(i), from2.apply(i)));
               }

               var10000 = result;
            } else {
               VectorBuilder vb = new VectorBuilder(from.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), this.evidence$10, this.breeze$linalg$operators$SparseVectorExpandOps$CanZipMapValuesSparseVector$$evidence$8);
               int off1 = 0;

               int off2;
               for(off2 = 0; off1 < from.activeSize(); ++off1) {
                  while(off2 < from2.activeSize() && from2.indexAt(off2) < from.indexAt(off1)) {
                     int index = from2.indexAt(off2);
                     vb.add(index, fn.apply(from.default(), from2.valueAt(off2)));
                     ++off2;
                  }

                  if (off2 < from2.activeSize() && from.indexAt(off1) == from2.indexAt(off2)) {
                     int index = from2.indexAt(off2);
                     vb.add(index, fn.apply(from.valueAt(off1), from2.valueAt(off2)));
                     ++off2;
                  } else {
                     int index = from.indexAt(off1);
                     vb.add(index, fn.apply(from.valueAt(off1), from2.default()));
                  }
               }

               while(off2 < from2.activeSize()) {
                  int index = from2.indexAt(off2);
                  vb.add(index, fn.apply(from.default(), from2.valueAt(off2)));
                  ++off2;
               }

               var10000 = vb.toSparseVector(true, true);
            }

            return var10000;
         }
      }

      public SparseVector create$mcD$sp(final int length) {
         return this.create(length);
      }

      public SparseVector create$mcI$sp(final int length) {
         return this.create(length);
      }

      public SparseVector map$mcDD$sp(final SparseVector from, final SparseVector from2, final Function2 fn) {
         return this.map(from, from2, fn);
      }

      public SparseVector map$mcID$sp(final SparseVector from, final SparseVector from2, final Function2 fn) {
         return this.map(from, from2, fn);
      }

      public SparseVector map$mcDF$sp(final SparseVector from, final SparseVector from2, final Function2 fn) {
         return this.map(from, from2, fn);
      }

      public SparseVector map$mcIF$sp(final SparseVector from, final SparseVector from2, final Function2 fn) {
         return this.map(from, from2, fn);
      }

      public SparseVector map$mcDI$sp(final SparseVector from, final SparseVector from2, final Function2 fn) {
         return this.map(from, from2, fn);
      }

      public SparseVector map$mcII$sp(final SparseVector from, final SparseVector from2, final Function2 fn) {
         return this.map(from, from2, fn);
      }

      public SparseVector map$mcDJ$sp(final SparseVector from, final SparseVector from2, final Function2 fn) {
         return this.map(from, from2, fn);
      }

      public SparseVector map$mcIJ$sp(final SparseVector from, final SparseVector from2, final Function2 fn) {
         return this.map(from, from2, fn);
      }

      // $FF: synthetic method
      public SparseVectorExpandOps breeze$linalg$operators$SparseVectorExpandOps$CanZipMapValuesSparseVector$$$outer() {
         return this.$outer;
      }

      public CanZipMapValuesSparseVector(final ClassTag evidence$8, final Zero evidence$9, final Semiring evidence$10) {
         this.breeze$linalg$operators$SparseVectorExpandOps$CanZipMapValuesSparseVector$$evidence$8 = evidence$8;
         this.evidence$9 = evidence$9;
         this.evidence$10 = evidence$10;
         if (SparseVectorExpandOps.this == null) {
            throw null;
         } else {
            this.$outer = SparseVectorExpandOps.this;
            super();
         }
      }
   }

   public class CanZipMapKeyValuesSparseVector implements CanZipMapKeyValues {
      public final ClassTag breeze$linalg$operators$SparseVectorExpandOps$CanZipMapKeyValuesSparseVector$$evidence$14;
      public final Zero evidence$15;
      public final Semiring evidence$16;
      // $FF: synthetic field
      public final SparseVectorExpandOps $outer;

      public SparseVector create(final int length) {
         return SparseVector$.MODULE$.zeros(length, this.breeze$linalg$operators$SparseVectorExpandOps$CanZipMapKeyValuesSparseVector$$evidence$14, this.evidence$15);
      }

      public SparseVector map(final SparseVector from, final SparseVector from2, final Function3 fn) {
         int left$macro$1 = from.length();
         int right$macro$2 = from2.length();
         if (left$macro$1 != right$macro$2) {
            throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vector lengths must match!: ").append("from.length == from2.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
         } else {
            SparseVector result = this.create(from.length());
            int index$macro$4 = 0;

            for(int limit$macro$6 = from.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
               result.update(index$macro$4, fn.apply(BoxesRunTime.boxToInteger(index$macro$4), from.apply(index$macro$4), from2.apply(index$macro$4)));
            }

            return result;
         }
      }

      public SparseVector mapActive(final SparseVector from, final SparseVector from2, final Function3 fn) {
         int left$macro$1 = from.length();
         int right$macro$2 = from2.length();
         if (left$macro$1 != right$macro$2) {
            throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vector lengths must match!: ").append("from.length == from2.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
         } else {
            VectorBuilder vb = new VectorBuilder(from.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), this.evidence$16, this.breeze$linalg$operators$SparseVectorExpandOps$CanZipMapKeyValuesSparseVector$$evidence$14);
            int off1 = 0;

            int off2;
            for(off2 = 0; off1 < from.activeSize(); ++off1) {
               while(off2 < from2.activeSize() && from2.indexAt(off2) < from.indexAt(off1)) {
                  int index = from2.indexAt(off2);
                  vb.add(index, fn.apply(BoxesRunTime.boxToInteger(index), from.default(), from2.valueAt(off2)));
                  ++off2;
               }

               if (off2 < from2.activeSize() && from.indexAt(off1) == from2.indexAt(off2)) {
                  int index = from2.indexAt(off2);
                  vb.add(index, fn.apply(BoxesRunTime.boxToInteger(index), from.valueAt(off1), from2.valueAt(off2)));
                  ++off2;
               } else {
                  int index = from.indexAt(off1);
                  vb.add(index, fn.apply(BoxesRunTime.boxToInteger(index), from.valueAt(off1), from2.default()));
               }
            }

            while(off2 < from2.activeSize()) {
               int index = from2.indexAt(off2);
               vb.add(index, fn.apply(BoxesRunTime.boxToInteger(index), from.default(), from2.valueAt(off2)));
               ++off2;
            }

            return vb.toSparseVector(true, true);
         }
      }

      public SparseVector create$mcD$sp(final int length) {
         return this.create(length);
      }

      public SparseVector create$mcI$sp(final int length) {
         return this.create(length);
      }

      public SparseVector map$mcDD$sp(final SparseVector from, final SparseVector from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      public SparseVector map$mcID$sp(final SparseVector from, final SparseVector from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      public SparseVector map$mcDF$sp(final SparseVector from, final SparseVector from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      public SparseVector map$mcIF$sp(final SparseVector from, final SparseVector from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      public SparseVector map$mcDI$sp(final SparseVector from, final SparseVector from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      public SparseVector map$mcII$sp(final SparseVector from, final SparseVector from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      public SparseVector map$mcDJ$sp(final SparseVector from, final SparseVector from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      public SparseVector map$mcIJ$sp(final SparseVector from, final SparseVector from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      public SparseVector mapActive$mcDD$sp(final SparseVector from, final SparseVector from2, final Function3 fn) {
         return this.mapActive(from, from2, fn);
      }

      public SparseVector mapActive$mcID$sp(final SparseVector from, final SparseVector from2, final Function3 fn) {
         return this.mapActive(from, from2, fn);
      }

      public SparseVector mapActive$mcDF$sp(final SparseVector from, final SparseVector from2, final Function3 fn) {
         return this.mapActive(from, from2, fn);
      }

      public SparseVector mapActive$mcIF$sp(final SparseVector from, final SparseVector from2, final Function3 fn) {
         return this.mapActive(from, from2, fn);
      }

      public SparseVector mapActive$mcDI$sp(final SparseVector from, final SparseVector from2, final Function3 fn) {
         return this.mapActive(from, from2, fn);
      }

      public SparseVector mapActive$mcII$sp(final SparseVector from, final SparseVector from2, final Function3 fn) {
         return this.mapActive(from, from2, fn);
      }

      public SparseVector mapActive$mcDJ$sp(final SparseVector from, final SparseVector from2, final Function3 fn) {
         return this.mapActive(from, from2, fn);
      }

      public SparseVector mapActive$mcIJ$sp(final SparseVector from, final SparseVector from2, final Function3 fn) {
         return this.mapActive(from, from2, fn);
      }

      // $FF: synthetic method
      public SparseVectorExpandOps breeze$linalg$operators$SparseVectorExpandOps$CanZipMapKeyValuesSparseVector$$$outer() {
         return this.$outer;
      }

      public CanZipMapKeyValuesSparseVector(final ClassTag evidence$14, final Zero evidence$15, final Semiring evidence$16) {
         this.breeze$linalg$operators$SparseVectorExpandOps$CanZipMapKeyValuesSparseVector$$evidence$14 = evidence$14;
         this.evidence$15 = evidence$15;
         this.evidence$16 = evidence$16;
         if (SparseVectorExpandOps.this == null) {
            throw null;
         } else {
            this.$outer = SparseVectorExpandOps.this;
            super();
         }
      }
   }
}
