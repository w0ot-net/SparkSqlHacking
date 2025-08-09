package breeze.linalg.operators;

import breeze.generic.UFunc;
import breeze.linalg.HashVector;
import breeze.linalg.HashVector$;
import breeze.linalg.Vector;
import breeze.linalg.support.CanZipMapKeyValues;
import breeze.linalg.support.CanZipMapValues;
import breeze.math.Field;
import breeze.math.Ring;
import breeze.math.Semiring;
import breeze.storage.Zero;
import breeze.storage.Zero$;
import breeze.util.ReflectionUtil$;
import java.lang.invoke.SerializedLambda;
import scala.Function2;
import scala.Function3;
import scala.MatchError;
import scala.Tuple2;
import scala.Predef.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\r}aa\u0002\u0011\"!\u0003\r\t\u0001\u000b\u0005\u0006g\u0001!\t\u0001\u000e\u0005\u0006q\u0001!\u0019!\u000f\u0005\u0006)\u0002!\u0019!\u0016\u0005\u0006=\u0002!\u0019a\u0018\u0005\u0006K\u0002!\u0019A\u001a\u0005\u0006s\u0002!\u0019A\u001f\u0005\b\u0003'\u0001A1AA\u000b\r\u0019\t\t\u0004\u0001\u0001\u00024!Q\u0011Q\u0013\u0005\u0003\u0004\u0003\u0006Y!a&\t\u0015\u0005\r\u0006BaA!\u0002\u0017\t)\u000bC\u0004\u00022\"!\t!a-\t\u000f\u0005}\u0006\u0002\"\u0001\u0002B\"9\u0011Q\u001a\u0005\u0005\u0002\u0005=\u0007bBAr\u0011\u0011\u0005\u0011Q\u001d\u0005\b\u0003[\u0004A1AAx\u0011%\u0011Y\u0001\u0001b\u0001\n\u0007\u0011i\u0001C\u0005\u0003\u0018\u0001\u0011\r\u0011b\u0001\u0003\u001a!I!1\u0005\u0001C\u0002\u0013\r!Q\u0005\u0004\u0007\u0005S\u0001\u0001Aa\u000b\t\u0015\tu3CaA!\u0002\u0017\u0011y\u0006\u0003\u0006\u0003bM\u0011\u0019\u0011)A\u0006\u0005GBq!!-\u0014\t\u0003\u0011)\u0007C\u0004\u0002@N!\tAa\u001c\t\u000f\u000557\u0003\"\u0001\u0003t!9\u00111]\n\u0005B\t\u0005\u0005b\u0002BE\u0001\u0011\r!1\u0012\u0005\b\u0005K\u0003A1\u0001BT\u0011\u001d\u0011I\r\u0001C\u0002\u0005\u0017DqA!9\u0001\t\u0007\u0011\u0019\u000fC\u0004\u0003t\u0002!\u0019A!>\t\u000f\r\u0015\u0001\u0001b\u0001\u0004\b\t)\u0002*Y:i-\u0016\u001cGo\u001c:`\u000f\u0016tWM]5d\u001fB\u001c(B\u0001\u0012$\u0003%y\u0007/\u001a:bi>\u00148O\u0003\u0002%K\u00051A.\u001b8bY\u001eT\u0011AJ\u0001\u0007EJ,WM_3\u0004\u0001M\u0019\u0001!K\u0018\u0011\u0005)jS\"A\u0016\u000b\u00031\nQa]2bY\u0006L!AL\u0016\u0003\r\u0005s\u0017PU3g!\t\u0001\u0014'D\u0001\"\u0013\t\u0011\u0014E\u0001\u0006HK:,'/[2PaN\fa\u0001J5oSR$C#A\u001b\u0011\u0005)2\u0014BA\u001c,\u0005\u0011)f.\u001b;\u0002?%l\u0007\u000f\\0PaN+GoX%o!2\f7-Z0I-~\u001bvlR3oKJL7-\u0006\u0002;\u0017V\t1\b\u0005\u0003=\u007f\u0015KeB\u0001\u0019>\u0013\tq\u0014%A\u0003PaN+G/\u0003\u0002A\u0003\na\u0011J\u001c)mC\u000e,\u0017*\u001c9me%\u0011!i\u0011\u0002\u0006+\u001a+hn\u0019\u0006\u0003\t\u0016\nqaZ3oKJL7\rE\u0002G\u000f&k\u0011aI\u0005\u0003\u0011\u000e\u0012!\u0002S1tQZ+7\r^8s!\tQ5\n\u0004\u0001\u0005\u000b1\u0013!\u0019A'\u0003\u0003Y\u000b\"AT)\u0011\u0005)z\u0015B\u0001),\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"A\u000b*\n\u0005M[#aA!os\u0006y\u0012.\u001c9m?>\u00038+\u001a;`\u0013:\u0004F.Y2f?\"3vLV0HK:,'/[2\u0016\u0005YSV#A,\u0011\tqz\u0004l\u0017\t\u0004\r\u001eK\u0006C\u0001&[\t\u0015a5A1\u0001N!\r1E,W\u0005\u0003;\u000e\u0012aAV3di>\u0014\u0018\u0001I5na2|v\n]*fi~Ke\u000e\u00157bG\u0016|\u0006JV0I-~;UM\\3sS\u000e,\"\u0001\u00193\u0016\u0003\u0005\u0004B\u0001P cEB\u0019aiR2\u0011\u0005)#G!\u0002'\u0005\u0005\u0004i\u0015!H5na2|6oY1mK\u0006#GmX%o!2\f7-Z0I-~3v\f\u0013,\u0016\u0005\u001d\u0004HC\u00015r!\u0015IGN\\8o\u001d\t1%.\u0003\u0002lG\u0005A1oY1mK\u0006#G-\u0003\u0002n\u0003\na\u0011J\u001c)mC\u000e,\u0017*\u001c9mgA\u0019aiR8\u0011\u0005)\u0003H!\u0002'\u0006\u0005\u0004i\u0005b\u0002:\u0006\u0003\u0003\u0005\u001da]\u0001\fKZLG-\u001a8dK\u0012\nT\u0007E\u0002uo>l\u0011!\u001e\u0006\u0003m\u0016\nA!\\1uQ&\u0011\u00010\u001e\u0002\t'\u0016l\u0017N]5oO\u0006i\u0012.\u001c9m?>\u0003\u0018\t\u001a3`\u0011Z{6kX3r?\"3vlR3oKJL7-F\u0002|\u0003\u0013!2\u0001`A\u0007!%i\u0018\u0011AA\u0003\u0003\u000f\t)A\u0004\u00021}&\u0011q0I\u0001\u0006\u001fB\fE\rZ\u0005\u0004\u0003\u0007\t%!B%na2\u0014\u0004\u0003\u0002$H\u0003\u000f\u00012ASA\u0005\t\u0019\tYA\u0002b\u0001\u001b\n\tA\u000bC\u0004\u0002\u0010\u0019\u0001\u001d!!\u0005\u0002\tM,W.\u001b\t\u0005i^\f9!A\u000fj[Bdwl\u00149Tk\n|\u0006JV0T?\u0016\fx\f\u0013,`\u000f\u0016tWM]5d+\u0011\t9\"!\n\u0015\t\u0005e\u0011q\u0005\t\u000b\u00037\t\t!!\t\u0002$\u0005\u0005bb\u0001\u0019\u0002\u001e%\u0019\u0011qD\u0011\u0002\u000b=\u00038+\u001e2\u0011\t\u0019;\u00151\u0005\t\u0004\u0015\u0006\u0015BABA\u0006\u000f\t\u0007Q\nC\u0004\u0002*\u001d\u0001\u001d!a\u000b\u0002\tILgn\u001a\t\u0006i\u00065\u00121E\u0005\u0004\u0003_)(\u0001\u0002*j]\u001e\u0014\u0011dQ1o5&\u0004X*\u00199WC2,Xm\u001d%bg\"4Vm\u0019;peV1\u0011QGA$\u0003\u000b\u001bB\u0001C\u0015\u00028Aa\u0011\u0011HA \u0003\u0007\n)%a!\u0002\u00146\u0011\u00111\b\u0006\u0004\u0003{\u0019\u0013aB:vaB|'\u000f^\u0005\u0005\u0003\u0003\nYDA\bDC:T\u0016\u000e]'baZ\u000bG.^3t!\u00111u)!\u0012\u0011\u0007)\u000b9\u0005B\u0005M\u0011\u0001\u0006\t\u0011!b\u0001\u001b\"b\u0011qIA&\u0003#\n)'a\u001c\u0002zA\u0019!&!\u0014\n\u0007\u0005=3FA\u0006ta\u0016\u001c\u0017.\u00197ju\u0016$\u0017'C\u0012\u0002T\u0005U\u0013\u0011LA,\u001d\rQ\u0013QK\u0005\u0004\u0003/Z\u0013A\u0002#pk\ndW-\r\u0004%\u00037\n\u0019\u0007\f\b\u0005\u0003;\n\u0019'\u0004\u0002\u0002`)\u0019\u0011\u0011M\u0014\u0002\rq\u0012xn\u001c;?\u0013\u0005a\u0013'C\u0012\u0002h\u0005%\u0014QNA6\u001d\rQ\u0013\u0011N\u0005\u0004\u0003WZ\u0013aA%oiF2A%a\u0017\u0002d1\n\u0014bIA9\u0003g\n9(!\u001e\u000f\u0007)\n\u0019(C\u0002\u0002v-\nQA\u00127pCR\fd\u0001JA.\u0003Gb\u0013'C\u0012\u0002|\u0005u\u0014\u0011QA@\u001d\rQ\u0013QP\u0005\u0004\u0003\u007fZ\u0013\u0001\u0002'p]\u001e\fd\u0001JA.\u0003Gb\u0003c\u0001&\u0002\u0006\u0012Q\u0011q\u0011\u0005!\u0002\u0003\u0005)\u0019A'\u0003\u0005I3\u0006\u0006CAC\u0003\u0017\nY)a$2\u0013\r\n9'!\u001b\u0002\u000e\u0006-\u0014G\u0002\u0013\u0002\\\u0005\rD&M\u0005$\u0003'\n)&!%\u0002XE2A%a\u0017\u0002d1\u0002BAR$\u0002\u0004\u0006YQM^5eK:\u001cW\rJ\u00197!\u0019\tI*a(\u0002\u00046\u0011\u00111\u0014\u0006\u0004\u0003;[\u0013a\u0002:fM2,7\r^\u0005\u0005\u0003C\u000bYJ\u0001\u0005DY\u0006\u001c8\u000fV1h\u0003-)g/\u001b3f]\u000e,G%M\u001c\u0011\r\u0005\u001d\u0016QVAB\u001b\t\tIKC\u0002\u0002,\u0016\nqa\u001d;pe\u0006<W-\u0003\u0003\u00020\u0006%&\u0001\u0002.fe>\fa\u0001P5oSRtDCAA[)\u0019\t9,a/\u0002>B9\u0011\u0011\u0018\u0005\u0002F\u0005\rU\"\u0001\u0001\t\u000f\u0005U5\u0002q\u0001\u0002\u0018\"9\u00111U\u0006A\u0004\u0005\u0015\u0016AB2sK\u0006$X\r\u0006\u0003\u0002\u0014\u0006\r\u0007bBAc\u0019\u0001\u0007\u0011qY\u0001\u0007Y\u0016tw\r\u001e5\u0011\u0007)\nI-C\u0002\u0002L.\u00121!\u00138u\u0003\ri\u0017\r\u001d\u000b\t\u0003'\u000b\t.!6\u0002Z\"9\u00111[\u0007A\u0002\u0005\r\u0013\u0001\u00024s_6Dq!a6\u000e\u0001\u0004\t\u0019%A\u0003ge>l'\u0007C\u0004\u0002\\6\u0001\r!!8\u0002\u0005\u0019t\u0007#\u0003\u0016\u0002`\u0006\u0015\u0013QIAB\u0013\r\t\to\u000b\u0002\n\rVt7\r^5p]J\n\u0011\"\\1q\u0003\u000e$\u0018N^3\u0015\u0011\u0005M\u0015q]Au\u0003WDq!a5\u000f\u0001\u0004\t\u0019\u0005C\u0004\u0002X:\u0001\r!a\u0011\t\u000f\u0005mg\u00021\u0001\u0002^\u0006I\u0001JV0{SBl\u0015\r]\u000b\u0007\u0003c\f90a?\u0015\r\u0005M\u0018q B\u0003!\u001d\tI\fCA{\u0003s\u00042ASA|\t\u0015auB1\u0001N!\rQ\u00151 \u0003\u0007\u0003{|!\u0019A'\u0003\u0003IC\u0011B!\u0001\u0010\u0003\u0003\u0005\u001dAa\u0001\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007\u000f\t\u0007\u00033\u000by*!?\t\u0013\t\u001dq\"!AA\u0004\t%\u0011aC3wS\u0012,gnY3%ce\u0002b!a*\u0002.\u0006e\u0018a\u0003%W?jL\u0007/T1q?\u0012,\"Aa\u0004\u0011\u000f\u0005e\u0006B!\u0005\u0003\u0012A\u0019!Fa\u0005\n\u0007\tU1F\u0001\u0004E_V\u0014G.Z\u0001\f\u0011Z{&0\u001b9NCB|f-\u0006\u0002\u0003\u001cA9\u0011\u0011\u0018\u0005\u0003\u001e\tu\u0001c\u0001\u0016\u0003 %\u0019!\u0011E\u0016\u0003\u000b\u0019cw.\u0019;\u0002\u0017!3vL_5q\u001b\u0006\u0004x,[\u000b\u0003\u0005O\u0001r!!/\t\u0003\u000f\f9M\u0001\u000fDC:T\u0016\u000e]'ba.+\u0017PV1mk\u0016\u001c\b*Y:i-\u0016\u001cGo\u001c:\u0016\r\t5\"\u0011\bB('\u0011\u0019\u0012Fa\f\u0011\u001d\u0005e\"\u0011\u0007B\u001b\u0003\u000f\u00149D!\u0014\u0003\\%!!1GA\u001e\u0005I\u0019\u0015M\u001c.ja6\u000b\u0007oS3z-\u0006dW/Z:\u0011\t\u0019;%q\u0007\t\u0004\u0015\neB!\u0003'\u0014A\u0003\u0005\tQ1\u0001NQ1\u0011I$a\u0013\u0003>\t\u0005#Q\tB%c%\u0019\u00131KA+\u0005\u007f\t9&\r\u0004%\u00037\n\u0019\u0007L\u0019\nG\u0005\u001d\u0014\u0011\u000eB\"\u0003W\nd\u0001JA.\u0003Gb\u0013'C\u0012\u0002r\u0005M$qIA;c\u0019!\u00131LA2YEJ1%a\u001f\u0002~\t-\u0013qP\u0019\u0007I\u0005m\u00131\r\u0017\u0011\u0007)\u0013y\u0005\u0002\u0006\u0002\bN\u0001\u000b\u0011!AC\u00025C\u0003Ba\u0014\u0002L\tM#qK\u0019\nG\u0005\u001d\u0014\u0011\u000eB+\u0003W\nd\u0001JA.\u0003Gb\u0013'C\u0012\u0002T\u0005U#\u0011LA,c\u0019!\u00131LA2YA!ai\u0012B'\u0003-)g/\u001b3f]\u000e,GE\r\u0019\u0011\r\u0005e\u0015q\u0014B'\u0003-)g/\u001b3f]\u000e,GEM\u0019\u0011\r\u0005\u001d\u0016Q\u0016B')\t\u00119\u0007\u0006\u0004\u0003j\t-$Q\u000e\t\b\u0003s\u001b\"q\u0007B'\u0011\u001d\u0011iF\u0006a\u0002\u0005?BqA!\u0019\u0017\u0001\b\u0011\u0019\u0007\u0006\u0003\u0003\\\tE\u0004bBAc/\u0001\u0007\u0011q\u0019\u000b\t\u00057\u0012)Ha\u001e\u0003z!9\u00111\u001b\rA\u0002\tU\u0002bBAl1\u0001\u0007!Q\u0007\u0005\b\u00037D\u0002\u0019\u0001B>!-Q#QPAd\u0005o\u00119D!\u0014\n\u0007\t}4FA\u0005Gk:\u001cG/[8ogQA!1\fBB\u0005\u000b\u00139\tC\u0004\u0002Tf\u0001\rA!\u000e\t\u000f\u0005]\u0017\u00041\u0001\u00036!9\u00111\\\rA\u0002\tm\u0014a\u0003%W?jL\u0007/T1q\u0017Z+bA!$\u0003\u0014\n]EC\u0002BH\u00053\u0013y\nE\u0004\u0002:N\u0011\tJ!&\u0011\u0007)\u0013\u0019\nB\u0003M5\t\u0007Q\nE\u0002K\u0005/#a!!@\u001b\u0005\u0004i\u0005\"\u0003BN5\u0005\u0005\t9\u0001BO\u0003-)g/\u001b3f]\u000e,GE\r\u001a\u0011\r\u0005e\u0015q\u0014BK\u0011%\u0011\tKGA\u0001\u0002\b\u0011\u0019+A\u0006fm&$WM\\2fII\u001a\u0004CBAT\u0003[\u0013)*\u0001\u0014j[Bdwl\u00149Nk2\u001c6-\u00197be~Ke\u000e\u00157bG\u0016|\u0006JV0I-~;UM\\3sS\u000e,BA!+\u00038R1!1\u0016B]\u0005\u0007\u0004rA!,@\u0005g\u0013\u0019LD\u00021\u0005_K1A!-\"\u0003-y\u0005/T;m'\u000e\fG.\u0019:\u0011\t\u0019;%Q\u0017\t\u0004\u0015\n]FABA\u00067\t\u0007Q\nC\u0004\u0003<n\u0001\u001dA!0\u0002\u000b\u0019LW\r\u001c3\u0011\u000bQ\u0014yL!.\n\u0007\t\u0005WOA\u0003GS\u0016dG\rC\u0004\u0003Fn\u0001\u001dAa2\u0002\u0005\r$\bCBAM\u0003?\u0013),\u0001\u0011j[Bdwl\u00149ESZ|\u0016J\u001c)mC\u000e,w\f\u0013,`\u0011Z{v)\u001a8fe&\u001cW\u0003\u0002Bg\u00057$BAa4\u0003^B9!\u0011[ \u0003X\n]gb\u0001\u0019\u0003T&\u0019!Q[\u0011\u0002\u000b=\u0003H)\u001b<\u0011\t\u0019;%\u0011\u001c\t\u0004\u0015\nmGABA\u00069\t\u0007Q\nC\u0004\u0003<r\u0001\u001dAa8\u0011\u000bQ\u0014yL!7\u0002K%l\u0007\u000f\\0Pa6+HnU2bY\u0006\u0014x,\u00138QY\u0006\u001cWm\u0018%W?N{v)\u001a8fe&\u001cW\u0003\u0002Bs\u0005[$BAa:\u0003pB9!QV \u0003j\n-\b\u0003\u0002$H\u0005W\u00042A\u0013Bw\t\u0019\tY!\bb\u0001\u001b\"9!1X\u000fA\u0004\tE\b\u0003\u0002;x\u0005W\fq$[7qY~{\u0005\u000fR5w?&s\u0007\u000b\\1dK~CekX*`\u000f\u0016tWM]5d+\u0011\u00119Pa@\u0015\t\te8\u0011\u0001\t\b\u0005#|$1 B\u007f!\u00111uI!@\u0011\u0007)\u0013y\u0010\u0002\u0004\u0002\fy\u0011\r!\u0014\u0005\b\u0005ws\u00029AB\u0002!\u0015!(q\u0018B\u007f\u0003}IW\u000e\u001d7`\u001fB\u0004vn^0J]Bc\u0017mY3`\u0011Z{6kX$f]\u0016\u0014\u0018nY\u000b\u0005\u0007\u0013\u00199\u0002\u0006\u0003\u0004\f\re\u0001cBB\u0007\u007f\rM1Q\u0003\b\u0004a\r=\u0011bAB\tC\u0005)q\n\u001d)poB!aiRB\u000b!\rQ5q\u0003\u0003\u0007\u0003\u0017y\"\u0019A'\t\u000f\rmq\u0004q\u0001\u0004\u001e\u0005\u0019\u0001o\\<\u0011\u0015\r5\u0011\u0011AB\u000b\u0007+\u0019)\u0002"
)
public interface HashVector_GenericOps extends GenericOps {
   void breeze$linalg$operators$HashVector_GenericOps$_setter_$HV_zipMap_d_$eq(final CanZipMapValuesHashVector x$1);

   void breeze$linalg$operators$HashVector_GenericOps$_setter_$HV_zipMap_f_$eq(final CanZipMapValuesHashVector x$1);

   void breeze$linalg$operators$HashVector_GenericOps$_setter_$HV_zipMap_i_$eq(final CanZipMapValuesHashVector x$1);

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_OpSet_InPlace_HV_S_Generic$(final HashVector_GenericOps $this) {
      return $this.impl_OpSet_InPlace_HV_S_Generic();
   }

   default UFunc.InPlaceImpl2 impl_OpSet_InPlace_HV_S_Generic() {
      return new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final Object b) {
            if (BoxesRunTime.equals(b, a.default())) {
               a.clear();
            } else {
               int index$macro$2 = 0;

               for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  a.update(index$macro$2, b);
               }
            }

         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_OpSet_InPlace_HV_V_Generic$(final HashVector_GenericOps $this) {
      return $this.impl_OpSet_InPlace_HV_V_Generic();
   }

   default UFunc.InPlaceImpl2 impl_OpSet_InPlace_HV_V_Generic() {
      return new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               a.clear();
               b.activeIterator().withFilter((check$ifrefutable$9) -> BoxesRunTime.boxToBoolean($anonfun$apply$19(check$ifrefutable$9))).foreach((x$9) -> {
                  $anonfun$apply$20(a, x$9);
                  return BoxedUnit.UNIT;
               });
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$19(final Tuple2 check$ifrefutable$9) {
            boolean var1;
            if (check$ifrefutable$9 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$20(final HashVector a$4, final Tuple2 x$9) {
            if (x$9 != null) {
               int k = x$9._1$mcI$sp();
               Object v = x$9._2();
               a$4.update(k, v);
               BoxedUnit var2 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$9);
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_OpSet_InPlace_HV_HV_Generic$(final HashVector_GenericOps $this) {
      return $this.impl_OpSet_InPlace_HV_HV_Generic();
   }

   default UFunc.InPlaceImpl2 impl_OpSet_InPlace_HV_HV_Generic() {
      return new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(85)).append("requirement failed: HashVectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               a.clear();
               b.array().copyTo(a.array());
            }
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_HV_V_HV$(final HashVector_GenericOps $this, final Semiring evidence$15) {
      return $this.impl_scaleAdd_InPlace_HV_V_HV(evidence$15);
   }

   default UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_HV_V_HV(final Semiring evidence$15) {
      return new UFunc.InPlaceImpl3(evidence$15) {
         private final Semiring ring;

         private Semiring ring() {
            return this.ring;
         }

         public void apply(final HashVector a, final Object s, final HashVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else if (!BoxesRunTime.equals(s, this.ring().zero())) {
               b.activeIterator().withFilter((check$ifrefutable$10) -> BoxesRunTime.boxToBoolean($anonfun$apply$21(check$ifrefutable$10))).foreach((x$10) -> {
                  $anonfun$apply$22(this, a, s, x$10);
                  return BoxedUnit.UNIT;
               });
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$21(final Tuple2 check$ifrefutable$10) {
            boolean var1;
            if (check$ifrefutable$10 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$22(final Object $this, final HashVector a$5, final Object s$2, final Tuple2 x$10) {
            if (x$10 != null) {
               int k = x$10._1$mcI$sp();
               Object v = x$10._2();
               a$5.update(k, $this.ring().$plus(a$5.apply(k), $this.ring().$times(s$2, v)));
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$10);
            }
         }

         public {
            this.ring = (Semiring).MODULE$.implicitly(evidence$15$1);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_OpAdd_HV_S_eq_HV_Generic$(final HashVector_GenericOps $this, final Semiring semi) {
      return $this.impl_OpAdd_HV_S_eq_HV_Generic(semi);
   }

   default UFunc.UImpl2 impl_OpAdd_HV_S_eq_HV_Generic(final Semiring semi) {
      return new UFunc.UImpl2(semi) {
         private final Semiring semi$1;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public HashVector apply(final HashVector a, final Object b) {
            ClassTag ct = ReflectionUtil$.MODULE$.elemClassTagFromArray(a.array().data());
            if (BoxesRunTime.equals(b, this.semi$1.zero())) {
               return a.copy();
            } else {
               HashVector result = HashVector$.MODULE$.zeros(a.length(), ct, Zero$.MODULE$.zeroFromSemiring(this.semi$1));
               int index$macro$2 = 0;

               for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  result.update(index$macro$2, this.semi$1.$plus(a.apply(index$macro$2), b));
               }

               return result;
            }
         }

         public {
            this.semi$1 = semi$1;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_OpSub_HV_S_eq_HV_Generic$(final HashVector_GenericOps $this, final Ring ring) {
      return $this.impl_OpSub_HV_S_eq_HV_Generic(ring);
   }

   default UFunc.UImpl2 impl_OpSub_HV_S_eq_HV_Generic(final Ring ring) {
      return new UFunc.UImpl2(ring) {
         private final Ring ring$2;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public HashVector apply(final HashVector a, final Object b) {
            ClassTag ct = ReflectionUtil$.MODULE$.elemClassTagFromArray(a.array().data());
            if (BoxesRunTime.equals(b, this.ring$2.zero())) {
               return a.copy();
            } else {
               HashVector result = HashVector$.MODULE$.zeros(a.length(), ct, Zero$.MODULE$.zeroFromSemiring(this.ring$2));
               int index$macro$2 = 0;

               for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  result.update(index$macro$2, this.ring$2.$minus(a.apply(index$macro$2), b));
               }

               return result;
            }
         }

         public {
            this.ring$2 = ring$2;
         }
      };
   }

   // $FF: synthetic method
   static CanZipMapValuesHashVector HV_zipMap$(final HashVector_GenericOps $this, final ClassTag evidence$18, final Zero evidence$19) {
      return $this.HV_zipMap(evidence$18, evidence$19);
   }

   default CanZipMapValuesHashVector HV_zipMap(final ClassTag evidence$18, final Zero evidence$19) {
      return new CanZipMapValuesHashVector(evidence$18, evidence$19);
   }

   CanZipMapValuesHashVector HV_zipMap_d();

   CanZipMapValuesHashVector HV_zipMap_f();

   CanZipMapValuesHashVector HV_zipMap_i();

   // $FF: synthetic method
   static CanZipMapKeyValuesHashVector HV_zipMapKV$(final HashVector_GenericOps $this, final ClassTag evidence$22, final Zero evidence$23) {
      return $this.HV_zipMapKV(evidence$22, evidence$23);
   }

   default CanZipMapKeyValuesHashVector HV_zipMapKV(final ClassTag evidence$22, final Zero evidence$23) {
      return new CanZipMapKeyValuesHashVector(evidence$22, evidence$23);
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_OpMulScalar_InPlace_HV_HV_Generic$(final HashVector_GenericOps $this, final Field field, final ClassTag ct) {
      return $this.impl_OpMulScalar_InPlace_HV_HV_Generic(field, ct);
   }

   default UFunc.InPlaceImpl2 impl_OpMulScalar_InPlace_HV_HV_Generic(final Field field, final ClassTag ct) {
      return new UFunc.InPlaceImpl2(field) {
         private final Field field$17;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector v, final HashVector v2) {
            scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), v.length()).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> v.update(i, this.field$17.$times(v.apply(i), v2.apply(i))));
         }

         public {
            this.field$17 = field$17;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_OpDiv_InPlace_HV_HV_Generic$(final HashVector_GenericOps $this, final Field field) {
      return $this.impl_OpDiv_InPlace_HV_HV_Generic(field);
   }

   default UFunc.InPlaceImpl2 impl_OpDiv_InPlace_HV_HV_Generic(final Field field) {
      return new UFunc.InPlaceImpl2(field) {
         private final Field field$18;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector v, final HashVector v2) {
            scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), v.length()).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> v.update(i, this.field$18.$div(v.apply(i), v2.apply(i))));
         }

         public {
            this.field$18 = field$18;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_OpMulScalar_InPlace_HV_S_Generic$(final HashVector_GenericOps $this, final Semiring field) {
      return $this.impl_OpMulScalar_InPlace_HV_S_Generic(field);
   }

   default UFunc.InPlaceImpl2 impl_OpMulScalar_InPlace_HV_S_Generic(final Semiring field) {
      return new UFunc.InPlaceImpl2(field) {
         private final Semiring field$19;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector v, final Object v2) {
            if (BoxesRunTime.equals(v2, this.field$19.zero())) {
               v.clear();
            } else {
               if (BoxesRunTime.equals(v2, this.field$19.one())) {
                  return;
               }

               int index$macro$2 = 0;

               for(int limit$macro$4 = v.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  v.update(index$macro$2, this.field$19.$times(v.apply(index$macro$2), v2));
               }
            }

         }

         public {
            this.field$19 = field$19;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_OpDiv_InPlace_HV_S_Generic$(final HashVector_GenericOps $this, final Field field) {
      return $this.impl_OpDiv_InPlace_HV_S_Generic(field);
   }

   default UFunc.InPlaceImpl2 impl_OpDiv_InPlace_HV_S_Generic(final Field field) {
      return new UFunc.InPlaceImpl2(field) {
         private final Field field$20;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector v, final Object v2) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = v.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               v.update(index$macro$2, this.field$20.$div(v.apply(index$macro$2), v2));
            }

         }

         public {
            this.field$20 = field$20;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_OpPow_InPlace_HV_S_Generic$(final HashVector_GenericOps $this, final UFunc.UImpl2 pow) {
      return $this.impl_OpPow_InPlace_HV_S_Generic(pow);
   }

   default UFunc.InPlaceImpl2 impl_OpPow_InPlace_HV_S_Generic(final UFunc.UImpl2 pow) {
      return new UFunc.InPlaceImpl2(pow) {
         private final UFunc.UImpl2 pow$5;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector v, final Object v2) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = v.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               v.update(index$macro$2, this.pow$5.apply(v.apply(index$macro$2), v2));
            }

         }

         public {
            this.pow$5 = pow$5;
         }
      };
   }

   static void $init$(final HashVector_GenericOps $this) {
      $this.breeze$linalg$operators$HashVector_GenericOps$_setter_$HV_zipMap_d_$eq(new HashVector_GenericOps$CanZipMapValuesHashVector$mcDD$sp($this, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero()));
      $this.breeze$linalg$operators$HashVector_GenericOps$_setter_$HV_zipMap_f_$eq($this.new CanZipMapValuesHashVector(scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero()));
      $this.breeze$linalg$operators$HashVector_GenericOps$_setter_$HV_zipMap_i_$eq(new HashVector_GenericOps$CanZipMapValuesHashVector$mcII$sp($this, scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero()));
   }

   public class CanZipMapValuesHashVector implements CanZipMapValues {
      public final ClassTag breeze$linalg$operators$HashVector_GenericOps$CanZipMapValuesHashVector$$evidence$16;
      public final Zero evidence$17;
      // $FF: synthetic field
      public final HashVector_GenericOps $outer;

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

      public HashVector create(final int length) {
         return HashVector$.MODULE$.zeros(length, this.breeze$linalg$operators$HashVector_GenericOps$CanZipMapValuesHashVector$$evidence$16, this.evidence$17);
      }

      public HashVector map(final HashVector from, final HashVector from2, final Function2 fn) {
         int left$macro$1 = from.length();
         int right$macro$2 = from2.length();
         if (left$macro$1 != right$macro$2) {
            throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vector lengths must match!: ").append("from.length == from2.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
         } else {
            HashVector result = this.create(from.length());

            for(int i = 0; i < from.length(); ++i) {
               result.update(i, fn.apply(from.apply(i), from2.apply(i)));
            }

            return result;
         }
      }

      public HashVector mapActive(final HashVector from, final HashVector from2, final Function2 fn) {
         return this.map(from, from2, fn);
      }

      public HashVector create$mcD$sp(final int length) {
         return this.create(length);
      }

      public HashVector create$mcI$sp(final int length) {
         return this.create(length);
      }

      public HashVector map$mcDD$sp(final HashVector from, final HashVector from2, final Function2 fn) {
         return this.map(from, from2, fn);
      }

      public HashVector map$mcID$sp(final HashVector from, final HashVector from2, final Function2 fn) {
         return this.map(from, from2, fn);
      }

      public HashVector map$mcDF$sp(final HashVector from, final HashVector from2, final Function2 fn) {
         return this.map(from, from2, fn);
      }

      public HashVector map$mcIF$sp(final HashVector from, final HashVector from2, final Function2 fn) {
         return this.map(from, from2, fn);
      }

      public HashVector map$mcDI$sp(final HashVector from, final HashVector from2, final Function2 fn) {
         return this.map(from, from2, fn);
      }

      public HashVector map$mcII$sp(final HashVector from, final HashVector from2, final Function2 fn) {
         return this.map(from, from2, fn);
      }

      public HashVector map$mcDJ$sp(final HashVector from, final HashVector from2, final Function2 fn) {
         return this.map(from, from2, fn);
      }

      public HashVector map$mcIJ$sp(final HashVector from, final HashVector from2, final Function2 fn) {
         return this.map(from, from2, fn);
      }

      public HashVector mapActive$mcDD$sp(final HashVector from, final HashVector from2, final Function2 fn) {
         return this.mapActive(from, from2, fn);
      }

      public HashVector mapActive$mcID$sp(final HashVector from, final HashVector from2, final Function2 fn) {
         return this.mapActive(from, from2, fn);
      }

      public HashVector mapActive$mcDF$sp(final HashVector from, final HashVector from2, final Function2 fn) {
         return this.mapActive(from, from2, fn);
      }

      public HashVector mapActive$mcIF$sp(final HashVector from, final HashVector from2, final Function2 fn) {
         return this.mapActive(from, from2, fn);
      }

      public HashVector mapActive$mcDI$sp(final HashVector from, final HashVector from2, final Function2 fn) {
         return this.mapActive(from, from2, fn);
      }

      public HashVector mapActive$mcII$sp(final HashVector from, final HashVector from2, final Function2 fn) {
         return this.mapActive(from, from2, fn);
      }

      public HashVector mapActive$mcDJ$sp(final HashVector from, final HashVector from2, final Function2 fn) {
         return this.mapActive(from, from2, fn);
      }

      public HashVector mapActive$mcIJ$sp(final HashVector from, final HashVector from2, final Function2 fn) {
         return this.mapActive(from, from2, fn);
      }

      // $FF: synthetic method
      public HashVector_GenericOps breeze$linalg$operators$HashVector_GenericOps$CanZipMapValuesHashVector$$$outer() {
         return this.$outer;
      }

      public CanZipMapValuesHashVector(final ClassTag evidence$16, final Zero evidence$17) {
         this.breeze$linalg$operators$HashVector_GenericOps$CanZipMapValuesHashVector$$evidence$16 = evidence$16;
         this.evidence$17 = evidence$17;
         if (HashVector_GenericOps.this == null) {
            throw null;
         } else {
            this.$outer = HashVector_GenericOps.this;
            super();
         }
      }
   }

   public class CanZipMapKeyValuesHashVector implements CanZipMapKeyValues {
      public final ClassTag breeze$linalg$operators$HashVector_GenericOps$CanZipMapKeyValuesHashVector$$evidence$20;
      public final Zero evidence$21;
      // $FF: synthetic field
      public final HashVector_GenericOps $outer;

      public HashVector create(final int length) {
         return HashVector$.MODULE$.zeros(length, this.breeze$linalg$operators$HashVector_GenericOps$CanZipMapKeyValuesHashVector$$evidence$20, this.evidence$21);
      }

      public HashVector map(final HashVector from, final HashVector from2, final Function3 fn) {
         int left$macro$1 = from.length();
         int right$macro$2 = from2.length();
         if (left$macro$1 != right$macro$2) {
            throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vector lengths must match!: ").append("from.length == from2.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
         } else {
            HashVector result = this.create(from.length());

            for(int i = 0; i < from.length(); ++i) {
               result.update(i, fn.apply(BoxesRunTime.boxToInteger(i), from.apply(i), from2.apply(i)));
            }

            return result;
         }
      }

      public HashVector mapActive(final HashVector from, final HashVector from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      public HashVector create$mcD$sp(final int length) {
         return this.create(length);
      }

      public HashVector create$mcI$sp(final int length) {
         return this.create(length);
      }

      public HashVector map$mcDD$sp(final HashVector from, final HashVector from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      public HashVector map$mcID$sp(final HashVector from, final HashVector from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      public HashVector map$mcDF$sp(final HashVector from, final HashVector from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      public HashVector map$mcIF$sp(final HashVector from, final HashVector from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      public HashVector map$mcDI$sp(final HashVector from, final HashVector from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      public HashVector map$mcII$sp(final HashVector from, final HashVector from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      public HashVector map$mcDJ$sp(final HashVector from, final HashVector from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      public HashVector map$mcIJ$sp(final HashVector from, final HashVector from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      public HashVector mapActive$mcDD$sp(final HashVector from, final HashVector from2, final Function3 fn) {
         return this.mapActive(from, from2, fn);
      }

      public HashVector mapActive$mcID$sp(final HashVector from, final HashVector from2, final Function3 fn) {
         return this.mapActive(from, from2, fn);
      }

      public HashVector mapActive$mcDF$sp(final HashVector from, final HashVector from2, final Function3 fn) {
         return this.mapActive(from, from2, fn);
      }

      public HashVector mapActive$mcIF$sp(final HashVector from, final HashVector from2, final Function3 fn) {
         return this.mapActive(from, from2, fn);
      }

      public HashVector mapActive$mcDI$sp(final HashVector from, final HashVector from2, final Function3 fn) {
         return this.mapActive(from, from2, fn);
      }

      public HashVector mapActive$mcII$sp(final HashVector from, final HashVector from2, final Function3 fn) {
         return this.mapActive(from, from2, fn);
      }

      public HashVector mapActive$mcDJ$sp(final HashVector from, final HashVector from2, final Function3 fn) {
         return this.mapActive(from, from2, fn);
      }

      public HashVector mapActive$mcIJ$sp(final HashVector from, final HashVector from2, final Function3 fn) {
         return this.mapActive(from, from2, fn);
      }

      // $FF: synthetic method
      public HashVector_GenericOps breeze$linalg$operators$HashVector_GenericOps$CanZipMapKeyValuesHashVector$$$outer() {
         return this.$outer;
      }

      public CanZipMapKeyValuesHashVector(final ClassTag evidence$20, final Zero evidence$21) {
         this.breeze$linalg$operators$HashVector_GenericOps$CanZipMapKeyValuesHashVector$$evidence$20 = evidence$20;
         this.evidence$21 = evidence$21;
         if (HashVector_GenericOps.this == null) {
            throw null;
         } else {
            this.$outer = HashVector_GenericOps.this;
            super();
         }
      }
   }
}
