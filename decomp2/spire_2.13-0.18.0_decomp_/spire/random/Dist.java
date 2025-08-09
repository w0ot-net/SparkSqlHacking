package spire.random;

import algebra.ring.AdditiveMonoid;
import algebra.ring.CommutativeRig;
import algebra.ring.CommutativeRing;
import algebra.ring.CommutativeRng;
import algebra.ring.CommutativeSemiring;
import algebra.ring.EuclideanRing;
import algebra.ring.Field;
import algebra.ring.GCDRing;
import algebra.ring.Rig;
import cats.kernel.Eq;
import cats.kernel.Order;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.immutable.LazyList;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Stream;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Builder;
import scala.math.Fractional;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;
import scala.runtime.java8.JFunction0;
import spire.algebra.CModule;
import spire.algebra.InnerProductSpace;
import spire.algebra.IsReal;
import spire.algebra.NormedVectorSpace;
import spire.algebra.Trig;
import spire.algebra.VectorSpace;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d\u001daa\u0002.\\!\u0003\r\t\u0001\u0019\u0005\u0006Q\u0002!\t!\u001b\u0005\u0006[\u00021\tA\u001c\u0005\b\u0003\u0007\u0001A\u0011AA\u0003\u0011\u001d\t\u0019\u0002\u0001C\u0003\u0003+Aq!a\u000b\u0001\t\u000b\ti\u0003C\u0004\u0002<\u0001!)!!\u0010\t\u000f\u00055\u0003\u0001\"\u0002\u0002P!9\u00111\u000b\u0001\u0005\u0002\u0005U\u0003bBA:\u0001\u0011\u0005\u0011Q\u000f\u0005\b\u0003/\u0003A\u0011AAM\u0011\u001d\t\t\f\u0001C\u0001\u0003gCq!!4\u0001\t\u0003\ty\rC\u0004\u0002|\u0002!\t!!@\t\u000f\t\u0015\u0001\u0001\"\u0001\u0003\b!9!Q\u0002\u0001\u0005\u0006\t=\u0001b\u0002B\u0013\u0001\u0011\u0005!q\u0005\u0005\b\u0005\u0003\u0002AQ\u0001B\"\u0011\u001d\u0011i\u0005\u0001C\u0003\u0005\u001fBqAa\u0018\u0001\t\u000b\u0011\t\u0007C\u0004\u0003\u0000\u0001!\tA!!\t\u000f\t\u0005\u0006\u0001\"\u0002\u0003$\"9!Q\u0016\u0001\u0005\u0002\t=\u0006b\u0002B`\u0001\u0011\u0005!\u0011\u0019\u0005\b\u0005K\u0004A\u0011\u0001Bt\u0011\u001d\u00119\u0010\u0001C\u0001\u0005sDqa!\u0005\u0001\t\u0003\u0019\u0019bB\u0004\u0004\u001emC\taa\b\u0007\ri[\u0006\u0012AB\u0011\u0011\u001d\u0019y\u0003\bC\u0001\u0007cAa!\u001c\u000f\u0005\u0006\rM\u0002BB7\u001d\t\u000b\u0019I\u0005\u0003\u0004n9\u0011\u00151\u0011\r\u0005\u0007yr!)aa!\t\u000f\rEE\u0004\"\u0001\u0004\u0014\"91\u0011\u0017\u000f\u0005\u0002\rM\u0006bBBi9\u0011\u000511\u001b\u0005\b\u0007[dB\u0011ABx\u0011\u001d!I\u0001\bC\u0001\t\u0017Aq\u0001b\b\u001d\t\u0003!\t\u0003C\u0004\u0005<q!\t\u0001\"\u0010\t\u000f\u00115C\u0004\"\u0001\u0005P!IA\u0011\r\u000fC\u0002\u0013\rA1\r\u0005\t\tOb\u0002\u0015!\u0003\u0005f!IA\u0011\u000e\u000fC\u0002\u0013\rA1\u000e\u0005\t\t_b\u0002\u0015!\u0003\u0005n!IA\u0011\u000f\u000fC\u0002\u0013\rA1\u000f\u0005\t\tob\u0002\u0015!\u0003\u0005v!IA\u0011\u0010\u000fC\u0002\u0013\rA1\u0010\u0005\t\t\u000bc\u0002\u0015!\u0003\u0005~!IAq\u0011\u000fC\u0002\u0013\rA\u0011\u0012\u0005\t\t'c\u0002\u0015!\u0003\u0005\f\"IAQ\u0013\u000fC\u0002\u0013\rAq\u0013\u0005\t\t7c\u0002\u0015!\u0003\u0005\u001a\"IAQ\u0014\u000fC\u0002\u0013\rAq\u0014\u0005\t\tSc\u0002\u0015!\u0003\u0005\"\"IA1\u0016\u000fC\u0002\u0013\rAQ\u0016\u0005\t\tcc\u0002\u0015!\u0003\u00050\"IA1\u0017\u000fC\u0002\u0013\rAQ\u0017\u0005\t\tsc\u0002\u0015!\u0003\u00058\"IA1\u0018\u000fC\u0002\u0013\rAQ\u0018\u0005\t\t\u001bd\u0002\u0015!\u0003\u0005@\"IAq\u001a\u000fC\u0002\u0013\rA\u0011\u001b\u0005\t\t7d\u0002\u0015!\u0003\u0005T\"IAQ\u001c\u000fC\u0002\u0013\rAq\u001c\u0005\t\tSd\u0002\u0015!\u0003\u0005b\"IA1\u001e\u000fC\u0002\u0013\rAQ\u001e\u0005\t\tod\u0002\u0015!\u0003\u0005p\"9A\u0011 \u000f\u0005\u0004\u0011m\bbBC\u00199\u0011\rQ1\u0007\u0005\b\u000b;bB1AC0\u0011\u001d)9\b\bC\u0002\u000bsBq!b&\u001d\t\u0007)I\nC\u0004\u00066r!\t!b.\t\u000f\u0015\u0005G\u0004\"\u0001\u0006D\"9Q\u0011\u001b\u000f\u0005\u0002\u0015M\u0007bBCq9\u0011\u0005Q1\u001d\u0005\b\u000b_dB\u0011ACy\u0011\u001d1\t\u0001\bC\u0002\r\u0007AqA\"\u0005\u001d\t\u00031\u0019\u0002C\u0004\u0007\u0016q!\tAb\u0006\t\u000f\u0019mA\u0004\"\u0001\u0007\u001e!9a1\u0006\u000f\u0005\u0002\u00195\u0002b\u0002D\u001d9\u0011\ra1\b\u0005\b\r;bB1\u0001D0\u0011\u001d1Y\b\bC\u0002\r{Bq!a\u0005\u001d\t\u00071i\nC\u0004\u0007@r!\tA\"1\t\u000f\u0019eG\u0004\"\u0001\u0007\\\"9a\u0011\u001f\u000f\u0005\u0002\u0019M(\u0001\u0002#jgRT!\u0001X/\u0002\rI\fg\u000eZ8n\u0015\u0005q\u0016!B:qSJ,7\u0001A\u000b\u0003CF\u001c\"\u0001\u00012\u0011\u0005\r4W\"\u00013\u000b\u0003\u0015\fQa]2bY\u0006L!a\u001a3\u0003\u0007\u0005s\u00170\u0001\u0004%S:LG\u000f\n\u000b\u0002UB\u00111m[\u0005\u0003Y\u0012\u0014A!\u00168ji\u0006)\u0011\r\u001d9msR\u0011qn\u001f\t\u0003aFd\u0001\u0001B\u0005s\u0001\u0001\u0006\t\u0011!b\u0001g\n\t\u0011)\u0005\u0002uEB\u00111-^\u0005\u0003m\u0012\u0014qAT8uQ&tw\r\u000b\u0002rqB\u00111-_\u0005\u0003u\u0012\u00141b\u001d9fG&\fG.\u001b>fI\")AP\u0001a\u0001{\u0006\u0019q-\u001a8\u0011\u0005y|X\"A.\n\u0007\u0005\u00051LA\u0005HK:,'/\u0019;pe\u0006!a-\u001b7m)\u0015Q\u0017qAA\u0005\u0011\u0015a8\u00011\u0001~\u0011\u001d\tYa\u0001a\u0001\u0003\u001b\t1!\u0019:s!\u0011\u0019\u0017qB8\n\u0007\u0005EAMA\u0003BeJ\f\u00170A\u0002nCB,B!a\u0006\u0002\u001eQ!\u0011\u0011DA\u0011!\u0011q\b!a\u0007\u0011\u0007A\fi\u0002\u0002\u0004\u0002 \u0011\u0011\ra\u001d\u0002\u0002\u0005\"9\u00111\u0005\u0003A\u0002\u0005\u0015\u0012!\u00014\u0011\r\r\f9c\\A\u000e\u0013\r\tI\u0003\u001a\u0002\n\rVt7\r^5p]F\nqA\u001a7bi6\u000b\u0007/\u0006\u0003\u00020\u0005UB\u0003BA\u0019\u0003o\u0001BA \u0001\u00024A\u0019\u0001/!\u000e\u0005\r\u0005}QA1\u0001t\u0011\u001d\t\u0019#\u0002a\u0001\u0003s\u0001baYA\u0014_\u0006E\u0012A\u00024jYR,'\u000f\u0006\u0003\u0002@\u0005\u0005\u0003c\u0001@\u0001_\"9\u00111\t\u0004A\u0002\u0005\u0015\u0013\u0001\u00029sK\u0012\u0004baYA\u0014_\u0006\u001d\u0003cA2\u0002J%\u0019\u00111\n3\u0003\u000f\t{w\u000e\\3b]\u0006)q-\u001b<f]R!\u0011qHA)\u0011\u001d\t\u0019e\u0002a\u0001\u0003\u000b\nQ!\u001e8uS2$B!a\u0016\u0002rA!a\u0010AA-!\u0015\tY&a\u001bp\u001d\u0011\ti&a\u001a\u000f\t\u0005}\u0013QM\u0007\u0003\u0003CR1!a\u0019`\u0003\u0019a$o\\8u}%\tQ-C\u0002\u0002j\u0011\fq\u0001]1dW\u0006<W-\u0003\u0003\u0002n\u0005=$aA*fc*\u0019\u0011\u0011\u000e3\t\u000f\u0005\r\u0003\u00021\u0001\u0002F\u0005)am\u001c7e]V!\u0011qOA@)\u0019\tI(!#\u0002\u000eR!\u00111PAA!\u0011q\b!! \u0011\u0007A\fy\b\u0002\u0004\u0002 %\u0011\ra\u001d\u0005\b\u0003GI\u0001\u0019AAB!!\u0019\u0017QQA?_\u0006u\u0014bAADI\nIa)\u001e8di&|gN\r\u0005\b\u0003\u0017K\u0001\u0019AA?\u0003\u0011Ig.\u001b;\t\u000f\u0005=\u0015\u00021\u0001\u0002\u0012\u0006\ta\u000eE\u0002d\u0003'K1!!&e\u0005\rIe\u000e^\u0001\u0007k:4w\u000e\u001c3\u0016\t\u0005m\u0015Q\u0015\u000b\u0005\u0003;\u000by\u000b\u0006\u0003\u0002 \u0006-F\u0003BAQ\u0003O\u0003BA \u0001\u0002$B\u0019\u0001/!*\u0005\r\u0005}!B1\u0001t\u0011\u001d\t\u0019E\u0003a\u0001\u0003S\u0003raYA\u0014\u0003G\u000b9\u0005C\u0004\u0002$)\u0001\r!!,\u0011\u0011\r\f))a)p\u0003GCq!a#\u000b\u0001\u0004\t\u0019+\u0001\u0003qC\u000e\\G\u0003BA[\u0003\u0017$B!a.\u0002:B!a\u0010AA\u0007\u0011\u001d\tYl\u0003a\u0002\u0003{\u000b!a\u0019;\u0011\u000b\u0005}\u0016QY8\u000f\t\u0005\u0005\u00171Y\u0007\u0002;&\u0019\u0011\u0011N/\n\t\u0005\u001d\u0017\u0011\u001a\u0002\t\u00072\f7o\u001d+bO*\u0019\u0011\u0011N/\t\u000f\u0005=5\u00021\u0001\u0002\u0012\u00061!/\u001a9fCR,B!!5\u0002ZR!\u00111[A})\u0011\t).!;\u0011\ty\u0004\u0011q\u001b\t\u0005a\u0006ew\u000eB\u0004\u0002\\2\u0011\r!!8\u0003\u0005\r\u001bU\u0003BAp\u0003K\f2\u0001^Aq!\u0019\tY&a\u001b\u0002dB\u0019\u0001/!:\u0005\u000f\u0005\u001d\u0018\u0011\u001cb\u0001g\n\t\u0001\fC\u0004\u0002l2\u0001\u001d!!<\u0002\u0007\r\u0014g\rE\u0004\u0002p\u0006Ux.a6\u000e\u0005\u0005E(bAAzI\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005]\u0018\u0011\u001f\u0002\b\r\u0006\u001cGo\u001c:z\u0011\u001d\ty\t\u0004a\u0001\u0003#\u000bq!\u001b;fe\u0006$X\r\u0006\u0004\u0002@\u0005}(\u0011\u0001\u0005\b\u0003\u001fk\u0001\u0019AAI\u0011\u001d\t\u0019#\u0004a\u0001\u0005\u0007\u0001baYA\u0014_\u0006}\u0012\u0001D5uKJ\fG/Z+oi&dGCBA \u0005\u0013\u0011Y\u0001C\u0004\u0002D9\u0001\r!!\u0012\t\u000f\u0005\rb\u00021\u0001\u0003\u0004\u0005\u0019!0\u001b9\u0016\t\tE!Q\u0004\u000b\u0005\u0005'\u0011y\u0002\u0005\u0003\u007f\u0001\tU\u0001CB2\u0003\u0018=\u0014Y\"C\u0002\u0003\u001a\u0011\u0014a\u0001V;qY\u0016\u0014\u0004c\u00019\u0003\u001e\u00111\u0011qD\bC\u0002MDqA!\t\u0010\u0001\u0004\u0011\u0019#\u0001\u0003uQ\u0006$\b\u0003\u0002@\u0001\u00057\tqA_5q/&$\b.\u0006\u0004\u0003*\tm\"\u0011\u0007\u000b\u0005\u0005W\u0011i\u0004\u0006\u0003\u0003.\tU\u0002\u0003\u0002@\u0001\u0005_\u00012\u0001\u001dB\u0019\t\u0019\u0011\u0019\u0004\u0005b\u0001g\n\t1\tC\u0004\u0002$A\u0001\rAa\u000e\u0011\u0011\r\f)i\u001cB\u001d\u0005_\u00012\u0001\u001dB\u001e\t\u0019\ty\u0002\u0005b\u0001g\"9!\u0011\u0005\tA\u0002\t}\u0002\u0003\u0002@\u0001\u0005s\t!\u0002^8Ji\u0016\u0014\u0018\r^8s)\u0011\u0011)Ea\u0013\u0011\u000b\u0005m#qI8\n\t\t%\u0013q\u000e\u0002\t\u0013R,'/\u0019;pe\")A0\u0005a\u0001{\u0006QAo\u001c'bufd\u0015n\u001d;\u0015\t\tE#Q\f\t\u0006\u0005'\u0012If\\\u0007\u0003\u0005+RAAa\u0016\u0002r\u0006I\u0011.\\7vi\u0006\u0014G.Z\u0005\u0005\u00057\u0012)F\u0001\u0005MCjLH*[:u\u0011\u0015a(\u00031\u0001~\u0003!!xn\u0015;sK\u0006lG\u0003\u0002B2\u0005S\u0002R!a\u0017\u0003f=LAAa\u001a\u0002p\t11\u000b\u001e:fC6DQ\u0001`\nA\u0002uD3b\u0005B7\u0005g\u0012)H!\u001f\u0003|A\u00191Ma\u001c\n\u0007\tEDM\u0001\u0006eKB\u0014XmY1uK\u0012\fq!\\3tg\u0006<W-\t\u0002\u0003x\u0005I\u0002O]3gKJ\u0004Co\u001c'bufd\u0015n\u001d;!S:\u001cH/Z1e\u0003\u0015\u0019\u0018N\\2fC\t\u0011i(\u0001\u00041]E:d\u0006M\u0001\u0007g\u0006l\u0007\u000f\\3\u0016\t\t\r%\u0011\u0012\u000b\u0005\u0005\u000b\u0013y\n\u0006\u0004\u0003\b\ne%1\u0014\t\u0005a\n%u\u000eB\u0004\u0002\\R\u0011\rAa#\u0016\t\t5%qS\t\u0004i\n=\u0005CBA.\u0005#\u0013)*\u0003\u0003\u0003\u0014\u0006=$\u0001C%uKJ\f'\r\\3\u0011\u0007A\u00149\nB\u0004\u0002h\n%%\u0019A:\t\u000bq$\u00029A?\t\u000f\u0005-H\u0003q\u0001\u0003\u001eB9\u0011q^A{_\n\u001d\u0005bBAH)\u0001\u0007\u0011\u0011S\u0001\u0006G>,h\u000e\u001e\u000b\u0007\u0005K\u0013IKa+\u0015\t\u0005E%q\u0015\u0005\u0006yV\u0001\u001d! \u0005\b\u0003\u0007*\u0002\u0019AA#\u0011\u001d\ty)\u0006a\u0001\u0003#\u000b!\u0001\u001d:\u0015\r\tE&1\u0018B_)\u0011\u0011\u0019L!/\u0011\u0007\r\u0014),C\u0002\u00038\u0012\u0014a\u0001R8vE2,\u0007\"\u0002?\u0017\u0001\bi\bbBA\"-\u0001\u0007\u0011Q\t\u0005\b\u0003\u001f3\u0002\u0019AAI\u0003\r\u0019X/\u001c\u000b\u0005\u0005\u0007\u0014\u0019\u000fF\u0003p\u0005\u000b\u00149\rC\u0003}/\u0001\u000fQ\u0010C\u0004\u0003J^\u0001\u001dAa3\u0002\u0007\u0005dw\rE\u0003\u0003N\nuwN\u0004\u0003\u0003P\neg\u0002\u0002Bi\u0005+tA!a\u0018\u0003T&\ta,C\u0002\u0003Xv\u000bq!\u00197hK\n\u0014\u0018-\u0003\u0003\u0002j\tm'b\u0001Bl;&!!q\u001cBq\u0005\r\u0011\u0016n\u001a\u0006\u0005\u0003S\u0012Y\u000eC\u0004\u0002\u0010^\u0001\r!!%\u0002\u0005\u00154H\u0003\u0002Bu\u0005k$Ra\u001cBv\u0005[DQ\u0001 \rA\u0004uDqA!3\u0019\u0001\b\u0011y\u000fE\u0003\u0003N\nEx.\u0003\u0003\u0003t\n\u0005(!\u0002$jK2$\u0007bBAH1\u0001\u0007\u0011\u0011S\u0001\nQ&\u001cHo\\4sC6$BAa?\u0004\u0010Q!!Q`B\u0007!\u001d\u0011ypa\u0002p\u0005gsAa!\u0001\u0004\u0004A\u0019\u0011q\f3\n\u0007\r\u0015A-\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0007\u0013\u0019YAA\u0002NCBT1a!\u0002e\u0011\u0015a\u0018\u0004q\u0001~\u0011\u001d\ty)\u0007a\u0001\u0003#\u000bAB]1x\u0011&\u001cHo\\4sC6$Ba!\u0006\u0004\u001cQ!1qCB\r!\u001d\u0011ypa\u0002p\u0003#CQ\u0001 \u000eA\u0004uDq!a$\u001b\u0001\u0004\t\t*\u0001\u0003ESN$\bC\u0001@\u001d'\u0015a21EB\u0015!\r\u00197QE\u0005\u0004\u0007O!'AB!osJ+g\rE\u0002\u007f\u0007WI1a!\f\\\u00059!\u0015n\u001d;J]N$\u0018M\\2fgf\na\u0001P5oSRtDCAB\u0010+\u0011\u0019)da\u000f\u0015\t\r]2Q\b\t\u0005}\u0002\u0019I\u0004E\u0002q\u0007w!QA\u001d\u0010C\u0002MDqaa\u0010\u001f\u0001\b\u00199$\u0001\u0002oC\"\u001aada\u0011\u0011\u0007\r\u001c)%C\u0002\u0004H\u0011\u0014a!\u001b8mS:,WCBB&\u00077\u001a\u0019\u0006\u0006\u0003\u0004N\ruC\u0003BB(\u0007+\u0002BA \u0001\u0004RA\u0019\u0001oa\u0015\u0005\r\u0005}qD1\u0001t\u0011\u001d\u0019yd\ba\u0002\u0007/\u0002BA \u0001\u0004ZA\u0019\u0001oa\u0017\u0005\u000bI|\"\u0019A:\t\u000f\u0005\rr\u00041\u0001\u0004`A91-a\n\u0004Z\rES\u0003CB2\u0007g\u001aiha\u001b\u0015\t\r\u00154q\u0010\u000b\u0007\u0007O\u001aig!\u001e\u0011\ty\u00041\u0011\u000e\t\u0004a\u000e-DA\u0002B\u001aA\t\u00071\u000fC\u0004\u0004@\u0001\u0002\u001daa\u001c\u0011\ty\u00041\u0011\u000f\t\u0004a\u000eMD!\u0002:!\u0005\u0004\u0019\bbBB<A\u0001\u000f1\u0011P\u0001\u0003]\n\u0004BA \u0001\u0004|A\u0019\u0001o! \u0005\r\u0005}\u0001E1\u0001t\u0011\u001d\t\u0019\u0003\ta\u0001\u0007\u0003\u0003\u0012bYAC\u0007c\u001aYh!\u001b\u0016\t\r\u001551\u0012\u000b\u0005\u0007\u000f\u001bi\t\u0005\u0003\u007f\u0001\r%\u0005c\u00019\u0004\f\u0012)!/\tb\u0001g\"9\u00111E\u0011A\u0002\r=\u0005CB2\u0002(u\u001cI)A\u0004v]&4wN]7\u0016\t\rU5Q\u0014\u000b\u0007\u0007/\u001bIk!,\u0015\t\re5q\u0014\t\u0005}\u0002\u0019Y\nE\u0002q\u0007;#QA\u001d\u0012C\u0002MD\u0011b!)#\u0003\u0003\u0005\u001daa)\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007E\u0003\u007f\u0007K\u001bY*C\u0002\u0004(n\u0013q!\u00168jM>\u0014X\u000eC\u0004\u0004,\n\u0002\raa'\u0002\u00071|w\u000fC\u0004\u00040\n\u0002\raa'\u0002\t!Lw\r[\u0001\tO\u0006,8o]5b]V!1QWB_)\u0019\u00199l!3\u0004NR!1\u0011XB`!\u0011q\baa/\u0011\u0007A\u001ci\fB\u0003sG\t\u00071\u000fC\u0005\u0004B\u000e\n\t\u0011q\u0001\u0004D\u0006QQM^5eK:\u001cW\r\n\u001a\u0011\u000by\u001c)ma/\n\u0007\r\u001d7L\u0001\u0005HCV\u001c8/[1o\u0011\u001d\u0019Ym\ta\u0001\u0007w\u000bA!\\3b]\"91qZ\u0012A\u0002\rm\u0016AB:uI\u0012+g/\u0001\u0004sK\u0012,8-Z\u000b\u0005\u0007+\u001ci\u000e\u0006\u0003\u0004X\u000e\rH\u0003BBm\u0007?\u0004BA \u0001\u0004\\B\u0019\u0001o!8\u0005\u000bI$#\u0019A:\t\u000f\u0005\rB\u00051\u0001\u0004bBI1-!\"\u0004\\\u000em71\u001c\u0005\b\u0007K$\u0003\u0019ABt\u0003\tq7\u000fE\u0003d\u0007S\u001cI.C\u0002\u0004l\u0012\u0014!\u0002\u0010:fa\u0016\fG/\u001a3?\u0003%1'o\\7CsR,7/\u0006\u0003\u0004r\u000eeH\u0003BBz\t\u000f!Ba!>\u0004|B!a\u0010AB|!\r\u00018\u0011 \u0003\u0006e\u0016\u0012\ra\u001d\u0005\b\u0003G)\u0003\u0019AB\u007f!\u001d\u0019\u0017qEB\u0000\u0007o\u0004RaYA\b\t\u0003\u00012a\u0019C\u0002\u0013\r!)\u0001\u001a\u0002\u0005\u0005f$X\rC\u0004\u0002\u0010\u0016\u0002\r!!%\u0002\u0011\u0019\u0014x.\\%oiN,B\u0001\"\u0004\u0005\u0016Q!Aq\u0002C\u000f)\u0011!\t\u0002b\u0006\u0011\ty\u0004A1\u0003\t\u0004a\u0012UA!\u0002:'\u0005\u0004\u0019\bbBA\u0012M\u0001\u0007A\u0011\u0004\t\bG\u0006\u001dB1\u0004C\n!\u0015\u0019\u0017qBAI\u0011\u001d\tyI\na\u0001\u0003#\u000b\u0011B\u001a:p[2{gnZ:\u0016\t\u0011\rB1\u0006\u000b\u0005\tK!I\u0004\u0006\u0003\u0005(\u00115\u0002\u0003\u0002@\u0001\tS\u00012\u0001\u001dC\u0016\t\u0015\u0011xE1\u0001t\u0011\u001d\t\u0019c\na\u0001\t_\u0001raYA\u0014\tc!I\u0003E\u0003d\u0003\u001f!\u0019\u0004E\u0002d\tkI1\u0001b\u000ee\u0005\u0011auN\\4\t\u000f\u0005=u\u00051\u0001\u0002\u0012\u0006\u0019Q.\u001b=\u0016\t\u0011}BQ\t\u000b\u0005\t\u0003\"9\u0005\u0005\u0003\u007f\u0001\u0011\r\u0003c\u00019\u0005F\u0011)!\u000f\u000bb\u0001g\"9A\u0011\n\u0015A\u0002\u0011-\u0013A\u00013t!\u0015\u00197\u0011\u001eC!\u0003-9X-[4ii\u0016$W*\u001b=\u0016\t\u0011ECq\u000b\u000b\u0005\t'\"I\u0006\u0005\u0003\u007f\u0001\u0011U\u0003c\u00019\u0005X\u0011)!/\u000bb\u0001g\"9A1L\u0015A\u0002\u0011u\u0013\u0001\u0002;qYN\u0004RaYBu\t?\u0002ra\u0019B\f\u0005g#\u0019&\u0001\u0003v]&$XC\u0001C3!\rq\bA[\u0001\u0006k:LG\u000fI\u0001\bE>|G.Z1o+\t!i\u0007\u0005\u0003\u007f\u0001\u0005\u001d\u0013\u0001\u00032p_2,\u0017M\u001c\u0011\u0002\t\tLH/Z\u000b\u0003\tk\u0002BA \u0001\u0005\u0002\u0005)!-\u001f;fA\u0005)1\u000f[8siV\u0011AQ\u0010\t\u0005}\u0002!y\bE\u0002d\t\u0003K1\u0001b!e\u0005\u0015\u0019\u0006n\u001c:u\u0003\u0019\u0019\bn\u001c:uA\u0005!1\r[1s+\t!Y\t\u0005\u0003\u007f\u0001\u00115\u0005cA2\u0005\u0010&\u0019A\u0011\u00133\u0003\t\rC\u0017M]\u0001\u0006G\"\f'\u000fI\u0001\u0004S:$XC\u0001CM!\u0011q\b!!%\u0002\t%tG\u000fI\u0001\u0006M2|\u0017\r^\u000b\u0003\tC\u0003BA \u0001\u0005$B\u00191\r\"*\n\u0007\u0011\u001dFMA\u0003GY>\fG/\u0001\u0004gY>\fG\u000fI\u0001\u0005Y>tw-\u0006\u0002\u00050B!a\u0010\u0001C\u001a\u0003\u0015awN\\4!\u0003\u0019!w.\u001e2mKV\u0011Aq\u0017\t\u0005}\u0002\u0011\u0019,A\u0004e_V\u0014G.\u001a\u0011\u0002\u000bU\u0014\u0017\u0010^3\u0016\u0005\u0011}\u0006\u0003\u0002@\u0001\t\u0003\u0004B\u0001b1\u0005J6\u0011AQ\u0019\u0006\u0004\t\u000fl\u0016\u0001B7bi\"LA\u0001b3\u0005F\n)QKQ=uK\u00061QOY=uK\u0002\na!^:i_J$XC\u0001Cj!\u0011q\b\u0001\"6\u0011\t\u0011\rGq[\u0005\u0005\t3$)M\u0001\u0004V'\"|'\u000f^\u0001\bkNDwN\u001d;!\u0003\u0011)\u0018N\u001c;\u0016\u0005\u0011\u0005\b\u0003\u0002@\u0001\tG\u0004B\u0001b1\u0005f&!Aq\u001dCc\u0005\u0011)\u0016J\u001c;\u0002\u000bULg\u000e\u001e\u0011\u0002\u000bUdwN\\4\u0016\u0005\u0011=\b\u0003\u0002@\u0001\tc\u0004B\u0001b1\u0005t&!AQ\u001fCc\u0005\u0015)Fj\u001c8h\u0003\u0019)Hn\u001c8hA\u000591m\\7qY\u0016DX\u0003\u0002C\u007f\u000b\u0013!\"\u0002b@\u0006\f\u0015UQ\u0011EC\u0016!\u0011q\b!\"\u0001\u0011\r\u0011\rW1AC\u0004\u0013\u0011))\u0001\"2\u0003\u000f\r{W\u000e\u001d7fqB\u0019\u0001/\"\u0003\u0005\u000bI$%\u0019A:\t\u0013\u00155A)!AA\u0004\u0015=\u0011AC3wS\u0012,gnY3%gA1\u00111LC\t\u000b\u000fIA!b\u0005\u0002p\tQaI]1di&|g.\u00197\t\u0013\u0015]A)!AA\u0004\u0015e\u0011AC3wS\u0012,gnY3%iA1Q1DC\u000f\u000b\u000fi!Aa7\n\t\u0015}!1\u001c\u0002\u0005)JLw\rC\u0005\u0006$\u0011\u000b\t\u0011q\u0001\u0006&\u0005QQM^5eK:\u001cW\rJ\u001b\u0011\r\u0015mQqEC\u0004\u0013\u0011)ICa7\u0003\r%\u001b(+Z1m\u0011%)i\u0003RA\u0001\u0002\b)y#\u0001\u0006fm&$WM\\2fIY\u0002BA \u0001\u0006\b\u0005A\u0011N\u001c;feZ\fG.\u0006\u0003\u00066\u0015\u0005C\u0003CC\u001c\u000b\u0007*i%b\u0015\u0011\ty\u0004Q\u0011\b\t\u0007\t\u0007,Y$b\u0010\n\t\u0015uBQ\u0019\u0002\t\u0013:$XM\u001d<bYB\u0019\u0001/\"\u0011\u0005\u000bI,%\u0019A:\t\u0013\u0015\u0015S)!AA\u0004\u0015\u001d\u0013AC3wS\u0012,gnY3%oA1!QZC%\u000b\u007fIA!b\u0013\u0003b\nq\u0011\t\u001a3ji&4X-T8o_&$\u0007\"CC(\u000b\u0006\u0005\t9AC)\u0003))g/\u001b3f]\u000e,G\u0005\u000f\t\u0005}\u0002)y\u0004C\u0005\u0006V\u0015\u000b\t\u0011q\u0001\u0006X\u0005QQM^5eK:\u001cW\rJ\u001d\u0011\r\t5W\u0011LC \u0013\u0011)YF!9\u0003\u000b=\u0013H-\u001a:\u0002\r=\u0004H/[8o+\u0011)\t'\"\u001c\u0015\r\u0015\rTqNC:!\u0011q\b!\"\u001a\u0011\u000b\r,9'b\u001b\n\u0007\u0015%DM\u0001\u0004PaRLwN\u001c\t\u0004a\u00165D!\u0002:G\u0005\u0004\u0019\bbBC9\r\u0002\u000fAQN\u0001\u0003]>Dqaa\u0010G\u0001\b))\b\u0005\u0003\u007f\u0001\u0015-\u0014AB3ji\",'/\u0006\u0004\u0006|\u0015\u001dU1\u0012\u000b\t\u000b{*i)b$\u0006\u0014B!a\u0010AC@!!\tY&\"!\u0006\u0006\u0016%\u0015\u0002BCB\u0003_\u0012a!R5uQ\u0016\u0014\bc\u00019\u0006\b\u0012)!o\u0012b\u0001gB\u0019\u0001/b#\u0005\r\u0005}qI1\u0001t\u0011\u001d)\th\u0012a\u0002\t[Bqaa\u0010H\u0001\b)\t\n\u0005\u0003\u007f\u0001\u0015\u0015\u0005bBB<\u000f\u0002\u000fQQ\u0013\t\u0005}\u0002)I)\u0001\u0004ukBdWMM\u000b\u0007\u000b7+\u0019+b*\u0015\r\u0015uU\u0011VCX!\u0011q\b!b(\u0011\u000f\r\u00149\"\")\u0006&B\u0019\u0001/b)\u0005\u000bID%\u0019A:\u0011\u0007A,9\u000b\u0002\u0004\u0002 !\u0013\ra\u001d\u0005\n\u000bWC\u0015\u0011!a\u0002\u000b[\u000b1\"\u001a<jI\u0016t7-\u001a\u00132aA!a\u0010ACQ\u0011%)\t\fSA\u0001\u0002\b)\u0019,A\u0006fm&$WM\\2fIE\n\u0004\u0003\u0002@\u0001\u000bK\u000b\u0001\"\u001b8ue\u0006tw-\u001a\u000b\u0007\t3+I,\"0\t\u000f\u0015m\u0016\n1\u0001\u0002\u0012\u0006!aM]8n\u0011\u001d)y,\u0013a\u0001\u0003#\u000b!\u0001^8\u0002\u000f9\fG/\u001e:bYR!QQYCg!\u0011q\b!b2\u0011\t\u0011\rW\u0011Z\u0005\u0005\u000b\u0017$)MA\u0004OCR,(/\u00197\t\u000f\u0015='\n1\u0001\u0002\u0012\u0006IQ.\u0019=ES\u001eLGo]\u0001\tg\u00064W\r\\8oOR!QQ[Co!\u0011q\b!b6\u0011\t\u0011\rW\u0011\\\u0005\u0005\u000b7$)M\u0001\u0005TC\u001a,Gj\u001c8h\u0011\u001d)yn\u0013a\u0001\u0003#\u000b\u0001\"\\1y\u0005f$Xm]\u0001\u0007E&<\u0017N\u001c;\u0015\t\u0015\u0015XQ\u001e\t\u0005}\u0002)9\u000f\u0005\u0003\u0002\\\u0015%\u0018\u0002BCv\u0003_\u0012aAQ5h\u0013:$\bbBCp\u0019\u0002\u0007\u0011\u0011S\u0001\u000bE&<G-Z2j[\u0006dGCBCz\u000bw,i\u0010\u0005\u0003\u007f\u0001\u0015U\b\u0003BA.\u000boLA!\"?\u0002p\tQ!)[4EK\u000eLW.\u00197\t\u000f\u0015}W\n1\u0001\u0002\u0012\"9Qq`'A\u0002\u0005E\u0015\u0001C7bqN\u001b\u0017\r\\3\u0002\u0011I\fG/[8oC2$BA\"\u0002\u0007\u000eA!a\u0010\u0001D\u0004!\u0011!\u0019M\"\u0003\n\t\u0019-AQ\u0019\u0002\t%\u0006$\u0018n\u001c8bY\"9aq\u0002(A\u0004\u0015\u0015\u0018\u0001\u00028fqR\fA\u0002\\8oOJ\fG/[8oC2,\"A\"\u0002\u0002\u0017\tLwM]1uS>t\u0017\r\u001c\u000b\u0005\r\u000b1I\u0002C\u0004\u0006`B\u0003\r!!%\u0002\u0011\r|gn\u001d;b]R,BAb\b\u0007&Q!a\u0011\u0005D\u0014!\u0011q\bAb\t\u0011\u0007A4)\u0003B\u0003s#\n\u00071\u000fC\u0004\u0007*E\u0003\rAb\t\u0002\u0003\u0005\fa!\u00197xCf\u001cX\u0003\u0002D\u0018\rk!BA\"\r\u00078A!a\u0010\u0001D\u001a!\r\u0001hQ\u0007\u0003\u0006eJ\u0013\ra\u001d\u0005\b\rS\u0011\u0006\u0019\u0001D\u001a\u0003\u0015\t'O]1z+\u00111iDb\u0012\u0015\r\u0019}bQ\u000bD-)\u00191\tE\"\u0013\u0007PA!a\u0010\u0001D\"!\u0015\u0019\u0017q\u0002D#!\r\u0001hq\t\u0003\u0006eN\u0013\ra\u001d\u0005\n\r\u0017\u001a\u0016\u0011!a\u0002\r\u001b\n1\"\u001a<jI\u0016t7-\u001a\u00132eA!a\u0010\u0001D#\u0011%1\tfUA\u0001\u0002\b1\u0019&A\u0006fm&$WM\\2fIE\u001a\u0004CBA`\u0003\u000b4)\u0005C\u0004\u0007XM\u0003\r!!%\u0002\u000f5LgnU5{K\"9a1L*A\u0002\u0005E\u0015aB7bqNK'0Z\u0001\u0005Y&\u001cH/\u0006\u0003\u0007b\u0019=DC\u0002D2\ro2I\b\u0006\u0003\u0007f\u0019E\u0004\u0003\u0002@\u0001\rO\u0002b!a\u0017\u0007j\u00195\u0014\u0002\u0002D6\u0003_\u0012A\u0001T5tiB\u0019\u0001Ob\u001c\u0005\u000bI$&\u0019A:\t\u0013\u0019MD+!AA\u0004\u0019U\u0014aC3wS\u0012,gnY3%cQ\u0002BA \u0001\u0007n!9aq\u000b+A\u0002\u0005E\u0005b\u0002D.)\u0002\u0007\u0011\u0011S\u0001\u0004g\u0016$X\u0003\u0002D@\r\u001b#bA\"!\u0007\u0016\u001aeE\u0003\u0002DB\r\u001f\u0003BA \u0001\u0007\u0006B1!q DD\r\u0017KAA\"#\u0004\f\t\u00191+\u001a;\u0011\u0007A4i\tB\u0003s+\n\u00071\u000fC\u0005\u0007\u0012V\u000b\t\u0011q\u0001\u0007\u0014\u0006YQM^5eK:\u001cW\rJ\u00196!\u0011q\bAb#\t\u000f\u0019]U\u000b1\u0001\u0002\u0012\u0006IQ.\u001b8J]B,Ho\u001d\u0005\b\r7+\u0006\u0019AAI\u0003%i\u0017\r_%oaV$8/\u0006\u0004\u0007 \u001a%fQ\u0016\u000b\u0007\rC3YL\"0\u0015\r\u0019\rfq\u0016D[!\u0011q\bA\"*\u0011\u0011\t}8q\u0001DT\rW\u00032\u0001\u001dDU\t\u0015\u0011hK1\u0001t!\r\u0001hQ\u0016\u0003\u0007\u0003?1&\u0019A:\t\u0013\u0019Ef+!AA\u0004\u0019M\u0016aC3wS\u0012,gnY3%cY\u0002BA \u0001\u0007(\"Iaq\u0017,\u0002\u0002\u0003\u000fa\u0011X\u0001\fKZLG-\u001a8dK\u0012\nt\u0007\u0005\u0003\u007f\u0001\u0019-\u0006b\u0002DL-\u0002\u0007\u0011\u0011\u0013\u0005\b\r73\u0006\u0019AAI\u0003\u0015yg.Z(g+\u00111\u0019Mb3\u0015\t\u0019\u0015g1\u001b\u000b\u0005\r\u000f4i\r\u0005\u0003\u007f\u0001\u0019%\u0007c\u00019\u0007L\u0012)!o\u0016b\u0001g\"IaqZ,\u0002\u0002\u0003\u000fa\u0011[\u0001\fKZLG-\u001a8dK\u0012\n\u0004\b\u0005\u0004\u0002@\u0006\u0015g\u0011\u001a\u0005\b\r+<\u0006\u0019\u0001Dl\u0003\t\t7\u000fE\u0003d\u0007S4I-A\u0004ds\u000edWm\u00144\u0016\t\u0019ugQ\u001d\u000b\u0005\r?4i\u000f\u0006\u0003\u0007b\u001a\u001d\b\u0003\u0002@\u0001\rG\u00042\u0001\u001dDs\t\u0015\u0011\bL1\u0001t\u0011%1I\u000fWA\u0001\u0002\b1Y/A\u0006fm&$WM\\2fIEJ\u0004CBA`\u0003\u000b4\u0019\u000fC\u0004\u0007Vb\u0003\rAb<\u0011\u000b\r\u001cIOb9\u0002%\u001d\fWo]:jC:4%o\\7E_V\u0014G.Z\u000b\u0005\rk4y\u0010\u0006\u0003\u0007x\u001e\u0005\u0001#\u0002@\u0007z\u001au\u0018b\u0001D~7\nYA)[:u\rJ|WnR3o!\r\u0001hq \u0003\u0006ef\u0013\ra\u001d\u0005\n\u000f\u0007I\u0016\u0011!a\u0002\u000f\u000b\t1\"\u001a<jI\u0016t7-\u001a\u00133aA1!Q\u001aBy\r{\u0004"
)
public interface Dist {
   static DistFromGen gaussianFromDouble(final Field evidence$20) {
      return Dist$.MODULE$.gaussianFromDouble(evidence$20);
   }

   static Dist cycleOf(final Seq as, final ClassTag evidence$19) {
      return Dist$.MODULE$.cycleOf(as, evidence$19);
   }

   static Dist oneOf(final Seq as, final ClassTag evidence$18) {
      return Dist$.MODULE$.oneOf(as, evidence$18);
   }

   static Dist set(final int minInputs, final int maxInputs, final Dist evidence$15) {
      return Dist$.MODULE$.set(minInputs, maxInputs, evidence$15);
   }

   static Dist list(final int minSize, final int maxSize, final Dist evidence$14) {
      return Dist$.MODULE$.list(minSize, maxSize, evidence$14);
   }

   static Dist array(final int minSize, final int maxSize, final Dist evidence$12, final ClassTag evidence$13) {
      return Dist$.MODULE$.array(minSize, maxSize, evidence$12, evidence$13);
   }

   static Dist always(final Object a) {
      return Dist$.MODULE$.always(a);
   }

   static Dist constant(final Object a) {
      return Dist$.MODULE$.constant(a);
   }

   static Dist bigrational(final int maxBytes) {
      return Dist$.MODULE$.bigrational(maxBytes);
   }

   static Dist longrational() {
      return Dist$.MODULE$.longrational();
   }

   static Dist rational(final Dist next) {
      return Dist$.MODULE$.rational(next);
   }

   static Dist bigdecimal(final int maxBytes, final int maxScale) {
      return Dist$.MODULE$.bigdecimal(maxBytes, maxScale);
   }

   static Dist bigint(final int maxBytes) {
      return Dist$.MODULE$.bigint(maxBytes);
   }

   static Dist safelong(final int maxBytes) {
      return Dist$.MODULE$.safelong(maxBytes);
   }

   static Dist natural(final int maxDigits) {
      return Dist$.MODULE$.natural(maxDigits);
   }

   static Dist intrange(final int from, final int to) {
      return Dist$.MODULE$.intrange(from, to);
   }

   static Dist tuple2(final Dist evidence$10, final Dist evidence$11) {
      return Dist$.MODULE$.tuple2(evidence$10, evidence$11);
   }

   static Dist either(final Dist no, final Dist na, final Dist nb) {
      return Dist$.MODULE$.either(no, na, nb);
   }

   static Dist option(final Dist no, final Dist na) {
      return Dist$.MODULE$.option(no, na);
   }

   static Dist interval(final AdditiveMonoid evidence$7, final Dist evidence$8, final Order evidence$9) {
      return Dist$.MODULE$.interval(evidence$7, evidence$8, evidence$9);
   }

   static Dist complex(final Fractional evidence$3, final Trig evidence$4, final IsReal evidence$5, final Dist evidence$6) {
      return Dist$.MODULE$.complex(evidence$3, evidence$4, evidence$5, evidence$6);
   }

   static Dist ulong() {
      return Dist$.MODULE$.ulong();
   }

   static Dist uint() {
      return Dist$.MODULE$.uint();
   }

   static Dist ushort() {
      return Dist$.MODULE$.ushort();
   }

   static Dist ubyte() {
      return Dist$.MODULE$.ubyte();
   }

   static Dist double() {
      return Dist$.MODULE$.double();
   }

   static Dist long() {
      return Dist$.MODULE$.long();
   }

   static Dist float() {
      return Dist$.MODULE$.float();
   }

   static Dist int() {
      return Dist$.MODULE$.int();
   }

   static Dist char() {
      return Dist$.MODULE$.char();
   }

   static Dist short() {
      return Dist$.MODULE$.short();
   }

   static Dist byte() {
      return Dist$.MODULE$.byte();
   }

   static Dist boolean() {
      return Dist$.MODULE$.boolean();
   }

   static Dist unit() {
      return Dist$.MODULE$.unit();
   }

   static Dist weightedMix(final Seq tpls) {
      return Dist$.MODULE$.weightedMix(tpls);
   }

   static Dist mix(final Seq ds) {
      return Dist$.MODULE$.mix(ds);
   }

   static Dist fromLongs(final int n, final Function1 f) {
      return Dist$.MODULE$.fromLongs(n, f);
   }

   static Dist fromInts(final int n, final Function1 f) {
      return Dist$.MODULE$.fromInts(n, f);
   }

   static Dist fromBytes(final int n, final Function1 f) {
      return Dist$.MODULE$.fromBytes(n, f);
   }

   static Dist reduce(final Seq ns, final Function2 f) {
      return Dist$.MODULE$.reduce(ns, f);
   }

   static Dist gaussian(final Object mean, final Object stdDev, final Gaussian evidence$2) {
      return Dist$.MODULE$.gaussian(mean, stdDev, evidence$2);
   }

   static Dist uniform(final Object low, final Object high, final Uniform evidence$1) {
      return Dist$.MODULE$.uniform(low, high, evidence$1);
   }

   static Dist gen(final Function1 f) {
      return Dist$.MODULE$.gen(f);
   }

   static InnerProductSpace InnerProductSpace(final Eq ev1, final InnerProductSpace ev2) {
      return Dist$.MODULE$.InnerProductSpace(ev1, ev2);
   }

   static NormedVectorSpace NormedVectorSpace(final Eq ev1, final NormedVectorSpace ev2) {
      return Dist$.MODULE$.NormedVectorSpace(ev1, ev2);
   }

   static VectorSpace vectorSpace(final Eq ev1, final VectorSpace ev2) {
      return Dist$.MODULE$.vectorSpace(ev1, ev2);
   }

   static CModule module(final CModule ev2) {
      return Dist$.MODULE$.module(ev2);
   }

   static Field field(final Eq ev1, final Field ev2) {
      return Dist$.MODULE$.field(ev1, ev2);
   }

   static EuclideanRing euclideanRing(final Eq ev1, final EuclideanRing ev2) {
      return Dist$.MODULE$.euclideanRing(ev1, ev2);
   }

   static GCDRing gcdRing(final Eq ev1, final GCDRing ev2) {
      return Dist$.MODULE$.gcdRing(ev1, ev2);
   }

   static CommutativeRing cRing(final CommutativeRing ev) {
      return Dist$.MODULE$.cRing(ev);
   }

   static CommutativeRng rng(final CommutativeRng ev) {
      return Dist$.MODULE$.rng(ev);
   }

   static CommutativeRig rig(final CommutativeRig ev) {
      return Dist$.MODULE$.rig(ev);
   }

   static CommutativeSemiring cSemiring(final CommutativeSemiring ev) {
      return Dist$.MODULE$.cSemiring(ev);
   }

   Object apply(final Generator gen);

   default void fill(final Generator gen, final Object arr) {
      for(int i = 0; i < .MODULE$.array_length(arr); ++i) {
         .MODULE$.array_update(arr, i, this.apply(gen));
      }

   }

   default Dist map(final Function1 f) {
      return new DistFromGen((g) -> f.apply(this.apply(g)));
   }

   default Dist flatMap(final Function1 f) {
      return new DistFromGen((g) -> ((Dist)f.apply(this.apply(g))).apply(g));
   }

   default Dist filter(final Function1 pred) {
      return new Dist(pred) {
         // $FF: synthetic field
         private final Dist $outer;
         private final Function1 pred$1;

         public boolean apply$mcZ$sp(final Generator gen) {
            return Dist.super.apply$mcZ$sp(gen);
         }

         public byte apply$mcB$sp(final Generator gen) {
            return Dist.super.apply$mcB$sp(gen);
         }

         public char apply$mcC$sp(final Generator gen) {
            return Dist.super.apply$mcC$sp(gen);
         }

         public double apply$mcD$sp(final Generator gen) {
            return Dist.super.apply$mcD$sp(gen);
         }

         public float apply$mcF$sp(final Generator gen) {
            return Dist.super.apply$mcF$sp(gen);
         }

         public int apply$mcI$sp(final Generator gen) {
            return Dist.super.apply$mcI$sp(gen);
         }

         public long apply$mcJ$sp(final Generator gen) {
            return Dist.super.apply$mcJ$sp(gen);
         }

         public short apply$mcS$sp(final Generator gen) {
            return Dist.super.apply$mcS$sp(gen);
         }

         public void apply$mcV$sp(final Generator gen) {
            Dist.super.apply$mcV$sp(gen);
         }

         public void fill(final Generator gen, final Object arr) {
            Dist.super.fill(gen, arr);
         }

         public void fill$mcZ$sp(final Generator gen, final boolean[] arr) {
            Dist.super.fill$mcZ$sp(gen, arr);
         }

         public void fill$mcB$sp(final Generator gen, final byte[] arr) {
            Dist.super.fill$mcB$sp(gen, arr);
         }

         public void fill$mcC$sp(final Generator gen, final char[] arr) {
            Dist.super.fill$mcC$sp(gen, arr);
         }

         public void fill$mcD$sp(final Generator gen, final double[] arr) {
            Dist.super.fill$mcD$sp(gen, arr);
         }

         public void fill$mcF$sp(final Generator gen, final float[] arr) {
            Dist.super.fill$mcF$sp(gen, arr);
         }

         public void fill$mcI$sp(final Generator gen, final int[] arr) {
            Dist.super.fill$mcI$sp(gen, arr);
         }

         public void fill$mcJ$sp(final Generator gen, final long[] arr) {
            Dist.super.fill$mcJ$sp(gen, arr);
         }

         public void fill$mcS$sp(final Generator gen, final short[] arr) {
            Dist.super.fill$mcS$sp(gen, arr);
         }

         public void fill$mcV$sp(final Generator gen, final BoxedUnit[] arr) {
            Dist.super.fill$mcV$sp(gen, arr);
         }

         public Dist map(final Function1 f) {
            return Dist.super.map(f);
         }

         public Dist map$mcZ$sp(final Function1 f) {
            return Dist.super.map$mcZ$sp(f);
         }

         public Dist map$mcB$sp(final Function1 f) {
            return Dist.super.map$mcB$sp(f);
         }

         public Dist map$mcC$sp(final Function1 f) {
            return Dist.super.map$mcC$sp(f);
         }

         public Dist map$mcD$sp(final Function1 f) {
            return Dist.super.map$mcD$sp(f);
         }

         public Dist map$mcF$sp(final Function1 f) {
            return Dist.super.map$mcF$sp(f);
         }

         public Dist map$mcI$sp(final Function1 f) {
            return Dist.super.map$mcI$sp(f);
         }

         public Dist map$mcJ$sp(final Function1 f) {
            return Dist.super.map$mcJ$sp(f);
         }

         public Dist map$mcS$sp(final Function1 f) {
            return Dist.super.map$mcS$sp(f);
         }

         public Dist map$mcV$sp(final Function1 f) {
            return Dist.super.map$mcV$sp(f);
         }

         public Dist flatMap(final Function1 f) {
            return Dist.super.flatMap(f);
         }

         public Dist flatMap$mcZ$sp(final Function1 f) {
            return Dist.super.flatMap$mcZ$sp(f);
         }

         public Dist flatMap$mcB$sp(final Function1 f) {
            return Dist.super.flatMap$mcB$sp(f);
         }

         public Dist flatMap$mcC$sp(final Function1 f) {
            return Dist.super.flatMap$mcC$sp(f);
         }

         public Dist flatMap$mcD$sp(final Function1 f) {
            return Dist.super.flatMap$mcD$sp(f);
         }

         public Dist flatMap$mcF$sp(final Function1 f) {
            return Dist.super.flatMap$mcF$sp(f);
         }

         public Dist flatMap$mcI$sp(final Function1 f) {
            return Dist.super.flatMap$mcI$sp(f);
         }

         public Dist flatMap$mcJ$sp(final Function1 f) {
            return Dist.super.flatMap$mcJ$sp(f);
         }

         public Dist flatMap$mcS$sp(final Function1 f) {
            return Dist.super.flatMap$mcS$sp(f);
         }

         public Dist flatMap$mcV$sp(final Function1 f) {
            return Dist.super.flatMap$mcV$sp(f);
         }

         public Dist filter(final Function1 pred) {
            return Dist.super.filter(pred);
         }

         public Dist filter$mcZ$sp(final Function1 pred) {
            return Dist.super.filter$mcZ$sp(pred);
         }

         public Dist filter$mcB$sp(final Function1 pred) {
            return Dist.super.filter$mcB$sp(pred);
         }

         public Dist filter$mcC$sp(final Function1 pred) {
            return Dist.super.filter$mcC$sp(pred);
         }

         public Dist filter$mcD$sp(final Function1 pred) {
            return Dist.super.filter$mcD$sp(pred);
         }

         public Dist filter$mcF$sp(final Function1 pred) {
            return Dist.super.filter$mcF$sp(pred);
         }

         public Dist filter$mcI$sp(final Function1 pred) {
            return Dist.super.filter$mcI$sp(pred);
         }

         public Dist filter$mcJ$sp(final Function1 pred) {
            return Dist.super.filter$mcJ$sp(pred);
         }

         public Dist filter$mcS$sp(final Function1 pred) {
            return Dist.super.filter$mcS$sp(pred);
         }

         public Dist filter$mcV$sp(final Function1 pred) {
            return Dist.super.filter$mcV$sp(pred);
         }

         public Dist given(final Function1 pred) {
            return Dist.super.given(pred);
         }

         public Dist given$mcZ$sp(final Function1 pred) {
            return Dist.super.given$mcZ$sp(pred);
         }

         public Dist given$mcB$sp(final Function1 pred) {
            return Dist.super.given$mcB$sp(pred);
         }

         public Dist given$mcC$sp(final Function1 pred) {
            return Dist.super.given$mcC$sp(pred);
         }

         public Dist given$mcD$sp(final Function1 pred) {
            return Dist.super.given$mcD$sp(pred);
         }

         public Dist given$mcF$sp(final Function1 pred) {
            return Dist.super.given$mcF$sp(pred);
         }

         public Dist given$mcI$sp(final Function1 pred) {
            return Dist.super.given$mcI$sp(pred);
         }

         public Dist given$mcJ$sp(final Function1 pred) {
            return Dist.super.given$mcJ$sp(pred);
         }

         public Dist given$mcS$sp(final Function1 pred) {
            return Dist.super.given$mcS$sp(pred);
         }

         public Dist given$mcV$sp(final Function1 pred) {
            return Dist.super.given$mcV$sp(pred);
         }

         public Dist until(final Function1 pred) {
            return Dist.super.until(pred);
         }

         public Dist until$mcZ$sp(final Function1 pred) {
            return Dist.super.until$mcZ$sp(pred);
         }

         public Dist until$mcB$sp(final Function1 pred) {
            return Dist.super.until$mcB$sp(pred);
         }

         public Dist until$mcC$sp(final Function1 pred) {
            return Dist.super.until$mcC$sp(pred);
         }

         public Dist until$mcD$sp(final Function1 pred) {
            return Dist.super.until$mcD$sp(pred);
         }

         public Dist until$mcF$sp(final Function1 pred) {
            return Dist.super.until$mcF$sp(pred);
         }

         public Dist until$mcI$sp(final Function1 pred) {
            return Dist.super.until$mcI$sp(pred);
         }

         public Dist until$mcJ$sp(final Function1 pred) {
            return Dist.super.until$mcJ$sp(pred);
         }

         public Dist until$mcS$sp(final Function1 pred) {
            return Dist.super.until$mcS$sp(pred);
         }

         public Dist until$mcV$sp(final Function1 pred) {
            return Dist.super.until$mcV$sp(pred);
         }

         public Dist foldn(final Object init, final int n, final Function2 f) {
            return Dist.super.foldn(init, n, f);
         }

         public Dist foldn$mcZ$sp(final Object init, final int n, final Function2 f) {
            return Dist.super.foldn$mcZ$sp(init, n, f);
         }

         public Dist foldn$mcB$sp(final Object init, final int n, final Function2 f) {
            return Dist.super.foldn$mcB$sp(init, n, f);
         }

         public Dist foldn$mcC$sp(final Object init, final int n, final Function2 f) {
            return Dist.super.foldn$mcC$sp(init, n, f);
         }

         public Dist foldn$mcD$sp(final Object init, final int n, final Function2 f) {
            return Dist.super.foldn$mcD$sp(init, n, f);
         }

         public Dist foldn$mcF$sp(final Object init, final int n, final Function2 f) {
            return Dist.super.foldn$mcF$sp(init, n, f);
         }

         public Dist foldn$mcI$sp(final Object init, final int n, final Function2 f) {
            return Dist.super.foldn$mcI$sp(init, n, f);
         }

         public Dist foldn$mcJ$sp(final Object init, final int n, final Function2 f) {
            return Dist.super.foldn$mcJ$sp(init, n, f);
         }

         public Dist foldn$mcS$sp(final Object init, final int n, final Function2 f) {
            return Dist.super.foldn$mcS$sp(init, n, f);
         }

         public Dist foldn$mcV$sp(final Object init, final int n, final Function2 f) {
            return Dist.super.foldn$mcV$sp(init, n, f);
         }

         public Dist unfold(final Object init, final Function2 f, final Function1 pred) {
            return Dist.super.unfold(init, f, pred);
         }

         public Dist unfold$mcZ$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.super.unfold$mcZ$sp(init, f, pred);
         }

         public Dist unfold$mcB$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.super.unfold$mcB$sp(init, f, pred);
         }

         public Dist unfold$mcC$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.super.unfold$mcC$sp(init, f, pred);
         }

         public Dist unfold$mcD$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.super.unfold$mcD$sp(init, f, pred);
         }

         public Dist unfold$mcF$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.super.unfold$mcF$sp(init, f, pred);
         }

         public Dist unfold$mcI$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.super.unfold$mcI$sp(init, f, pred);
         }

         public Dist unfold$mcJ$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.super.unfold$mcJ$sp(init, f, pred);
         }

         public Dist unfold$mcS$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.super.unfold$mcS$sp(init, f, pred);
         }

         public Dist unfold$mcV$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.super.unfold$mcV$sp(init, f, pred);
         }

         public Dist pack(final int n, final ClassTag ct) {
            return Dist.super.pack(n, ct);
         }

         public Dist pack$mcZ$sp(final int n, final ClassTag ct) {
            return Dist.super.pack$mcZ$sp(n, ct);
         }

         public Dist pack$mcB$sp(final int n, final ClassTag ct) {
            return Dist.super.pack$mcB$sp(n, ct);
         }

         public Dist pack$mcC$sp(final int n, final ClassTag ct) {
            return Dist.super.pack$mcC$sp(n, ct);
         }

         public Dist pack$mcD$sp(final int n, final ClassTag ct) {
            return Dist.super.pack$mcD$sp(n, ct);
         }

         public Dist pack$mcF$sp(final int n, final ClassTag ct) {
            return Dist.super.pack$mcF$sp(n, ct);
         }

         public Dist pack$mcI$sp(final int n, final ClassTag ct) {
            return Dist.super.pack$mcI$sp(n, ct);
         }

         public Dist pack$mcJ$sp(final int n, final ClassTag ct) {
            return Dist.super.pack$mcJ$sp(n, ct);
         }

         public Dist pack$mcS$sp(final int n, final ClassTag ct) {
            return Dist.super.pack$mcS$sp(n, ct);
         }

         public Dist pack$mcV$sp(final int n, final ClassTag ct) {
            return Dist.super.pack$mcV$sp(n, ct);
         }

         public Dist repeat(final int n, final Factory cbf) {
            return Dist.super.repeat(n, cbf);
         }

         public Dist iterate(final int n, final Function1 f) {
            return Dist.super.iterate(n, f);
         }

         public Dist iterate$mcZ$sp(final int n, final Function1 f) {
            return Dist.super.iterate$mcZ$sp(n, f);
         }

         public Dist iterate$mcB$sp(final int n, final Function1 f) {
            return Dist.super.iterate$mcB$sp(n, f);
         }

         public Dist iterate$mcC$sp(final int n, final Function1 f) {
            return Dist.super.iterate$mcC$sp(n, f);
         }

         public Dist iterate$mcD$sp(final int n, final Function1 f) {
            return Dist.super.iterate$mcD$sp(n, f);
         }

         public Dist iterate$mcF$sp(final int n, final Function1 f) {
            return Dist.super.iterate$mcF$sp(n, f);
         }

         public Dist iterate$mcI$sp(final int n, final Function1 f) {
            return Dist.super.iterate$mcI$sp(n, f);
         }

         public Dist iterate$mcJ$sp(final int n, final Function1 f) {
            return Dist.super.iterate$mcJ$sp(n, f);
         }

         public Dist iterate$mcS$sp(final int n, final Function1 f) {
            return Dist.super.iterate$mcS$sp(n, f);
         }

         public Dist iterate$mcV$sp(final int n, final Function1 f) {
            return Dist.super.iterate$mcV$sp(n, f);
         }

         public Dist iterateUntil(final Function1 pred, final Function1 f) {
            return Dist.super.iterateUntil(pred, f);
         }

         public Dist iterateUntil$mcZ$sp(final Function1 pred, final Function1 f) {
            return Dist.super.iterateUntil$mcZ$sp(pred, f);
         }

         public Dist iterateUntil$mcB$sp(final Function1 pred, final Function1 f) {
            return Dist.super.iterateUntil$mcB$sp(pred, f);
         }

         public Dist iterateUntil$mcC$sp(final Function1 pred, final Function1 f) {
            return Dist.super.iterateUntil$mcC$sp(pred, f);
         }

         public Dist iterateUntil$mcD$sp(final Function1 pred, final Function1 f) {
            return Dist.super.iterateUntil$mcD$sp(pred, f);
         }

         public Dist iterateUntil$mcF$sp(final Function1 pred, final Function1 f) {
            return Dist.super.iterateUntil$mcF$sp(pred, f);
         }

         public Dist iterateUntil$mcI$sp(final Function1 pred, final Function1 f) {
            return Dist.super.iterateUntil$mcI$sp(pred, f);
         }

         public Dist iterateUntil$mcJ$sp(final Function1 pred, final Function1 f) {
            return Dist.super.iterateUntil$mcJ$sp(pred, f);
         }

         public Dist iterateUntil$mcS$sp(final Function1 pred, final Function1 f) {
            return Dist.super.iterateUntil$mcS$sp(pred, f);
         }

         public Dist iterateUntil$mcV$sp(final Function1 pred, final Function1 f) {
            return Dist.super.iterateUntil$mcV$sp(pred, f);
         }

         public Dist zip(final Dist that) {
            return Dist.super.zip(that);
         }

         public Dist zip$mcZ$sp(final Dist that) {
            return Dist.super.zip$mcZ$sp(that);
         }

         public Dist zip$mcB$sp(final Dist that) {
            return Dist.super.zip$mcB$sp(that);
         }

         public Dist zip$mcC$sp(final Dist that) {
            return Dist.super.zip$mcC$sp(that);
         }

         public Dist zip$mcD$sp(final Dist that) {
            return Dist.super.zip$mcD$sp(that);
         }

         public Dist zip$mcF$sp(final Dist that) {
            return Dist.super.zip$mcF$sp(that);
         }

         public Dist zip$mcI$sp(final Dist that) {
            return Dist.super.zip$mcI$sp(that);
         }

         public Dist zip$mcJ$sp(final Dist that) {
            return Dist.super.zip$mcJ$sp(that);
         }

         public Dist zip$mcS$sp(final Dist that) {
            return Dist.super.zip$mcS$sp(that);
         }

         public Dist zip$mcV$sp(final Dist that) {
            return Dist.super.zip$mcV$sp(that);
         }

         public Dist zipWith(final Dist that, final Function2 f) {
            return Dist.super.zipWith(that, f);
         }

         public Dist zipWith$mcZ$sp(final Dist that, final Function2 f) {
            return Dist.super.zipWith$mcZ$sp(that, f);
         }

         public Dist zipWith$mcB$sp(final Dist that, final Function2 f) {
            return Dist.super.zipWith$mcB$sp(that, f);
         }

         public Dist zipWith$mcC$sp(final Dist that, final Function2 f) {
            return Dist.super.zipWith$mcC$sp(that, f);
         }

         public Dist zipWith$mcD$sp(final Dist that, final Function2 f) {
            return Dist.super.zipWith$mcD$sp(that, f);
         }

         public Dist zipWith$mcF$sp(final Dist that, final Function2 f) {
            return Dist.super.zipWith$mcF$sp(that, f);
         }

         public Dist zipWith$mcI$sp(final Dist that, final Function2 f) {
            return Dist.super.zipWith$mcI$sp(that, f);
         }

         public Dist zipWith$mcJ$sp(final Dist that, final Function2 f) {
            return Dist.super.zipWith$mcJ$sp(that, f);
         }

         public Dist zipWith$mcS$sp(final Dist that, final Function2 f) {
            return Dist.super.zipWith$mcS$sp(that, f);
         }

         public Dist zipWith$mcV$sp(final Dist that, final Function2 f) {
            return Dist.super.zipWith$mcV$sp(that, f);
         }

         public final Iterator toIterator(final Generator gen) {
            return Dist.super.toIterator(gen);
         }

         public final LazyList toLazyList(final Generator gen) {
            return Dist.super.toLazyList(gen);
         }

         /** @deprecated */
         public final Stream toStream(final Generator gen) {
            return Dist.super.toStream(gen);
         }

         public Iterable sample(final int n, final Generator gen, final Factory cbf) {
            return Dist.super.sample(n, gen, cbf);
         }

         public int count(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.count(pred, n, gen);
         }

         public int count$mcZ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.count$mcZ$sp(pred, n, gen);
         }

         public int count$mcB$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.count$mcB$sp(pred, n, gen);
         }

         public int count$mcC$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.count$mcC$sp(pred, n, gen);
         }

         public int count$mcD$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.count$mcD$sp(pred, n, gen);
         }

         public int count$mcF$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.count$mcF$sp(pred, n, gen);
         }

         public int count$mcI$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.count$mcI$sp(pred, n, gen);
         }

         public int count$mcJ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.count$mcJ$sp(pred, n, gen);
         }

         public int count$mcS$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.count$mcS$sp(pred, n, gen);
         }

         public int count$mcV$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.count$mcV$sp(pred, n, gen);
         }

         public double pr(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.pr(pred, n, gen);
         }

         public double pr$mcZ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.pr$mcZ$sp(pred, n, gen);
         }

         public double pr$mcB$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.pr$mcB$sp(pred, n, gen);
         }

         public double pr$mcC$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.pr$mcC$sp(pred, n, gen);
         }

         public double pr$mcD$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.pr$mcD$sp(pred, n, gen);
         }

         public double pr$mcF$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.pr$mcF$sp(pred, n, gen);
         }

         public double pr$mcI$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.pr$mcI$sp(pred, n, gen);
         }

         public double pr$mcJ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.pr$mcJ$sp(pred, n, gen);
         }

         public double pr$mcS$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.pr$mcS$sp(pred, n, gen);
         }

         public double pr$mcV$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.pr$mcV$sp(pred, n, gen);
         }

         public Object sum(final int n, final Generator gen, final Rig alg) {
            return Dist.super.sum(n, gen, alg);
         }

         public boolean sum$mcZ$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.super.sum$mcZ$sp(n, gen, alg);
         }

         public byte sum$mcB$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.super.sum$mcB$sp(n, gen, alg);
         }

         public char sum$mcC$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.super.sum$mcC$sp(n, gen, alg);
         }

         public double sum$mcD$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.super.sum$mcD$sp(n, gen, alg);
         }

         public float sum$mcF$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.super.sum$mcF$sp(n, gen, alg);
         }

         public int sum$mcI$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.super.sum$mcI$sp(n, gen, alg);
         }

         public long sum$mcJ$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.super.sum$mcJ$sp(n, gen, alg);
         }

         public short sum$mcS$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.super.sum$mcS$sp(n, gen, alg);
         }

         public void sum$mcV$sp(final int n, final Generator gen, final Rig alg) {
            Dist.super.sum$mcV$sp(n, gen, alg);
         }

         public Object ev(final int n, final Generator gen, final Field alg) {
            return Dist.super.ev(n, gen, alg);
         }

         public boolean ev$mcZ$sp(final int n, final Generator gen, final Field alg) {
            return Dist.super.ev$mcZ$sp(n, gen, alg);
         }

         public byte ev$mcB$sp(final int n, final Generator gen, final Field alg) {
            return Dist.super.ev$mcB$sp(n, gen, alg);
         }

         public char ev$mcC$sp(final int n, final Generator gen, final Field alg) {
            return Dist.super.ev$mcC$sp(n, gen, alg);
         }

         public double ev$mcD$sp(final int n, final Generator gen, final Field alg) {
            return Dist.super.ev$mcD$sp(n, gen, alg);
         }

         public float ev$mcF$sp(final int n, final Generator gen, final Field alg) {
            return Dist.super.ev$mcF$sp(n, gen, alg);
         }

         public int ev$mcI$sp(final int n, final Generator gen, final Field alg) {
            return Dist.super.ev$mcI$sp(n, gen, alg);
         }

         public long ev$mcJ$sp(final int n, final Generator gen, final Field alg) {
            return Dist.super.ev$mcJ$sp(n, gen, alg);
         }

         public short ev$mcS$sp(final int n, final Generator gen, final Field alg) {
            return Dist.super.ev$mcS$sp(n, gen, alg);
         }

         public void ev$mcV$sp(final int n, final Generator gen, final Field alg) {
            Dist.super.ev$mcV$sp(n, gen, alg);
         }

         public Map histogram(final int n, final Generator gen) {
            return Dist.super.histogram(n, gen);
         }

         public Map rawHistogram(final int n, final Generator gen) {
            return Dist.super.rawHistogram(n, gen);
         }

         public final Object apply(final Generator gen) {
            return this.loop$1(gen);
         }

         private final Object loop$1(final Generator gen$1) {
            Object a;
            do {
               a = this.$outer.apply(gen$1);
            } while(!BoxesRunTime.unboxToBoolean(this.pred$1.apply(a)));

            return a;
         }

         public {
            if (Dist.this == null) {
               throw null;
            } else {
               this.$outer = Dist.this;
               this.pred$1 = pred$1;
               Dist.$init$(this);
            }
         }
      };
   }

   default Dist given(final Function1 pred) {
      return this.filter(pred);
   }

   default Dist until(final Function1 pred) {
      return new DistFromGen((g) -> this.loop$2(g, this.apply(g), scala.collection.mutable.ArrayBuffer..MODULE$.empty(), pred));
   }

   default Dist foldn(final Object init, final int n, final Function2 f) {
      return new DistFromGen((g) -> this.loop$3(g, n, init, f));
   }

   default Dist unfold(final Object init, final Function2 f, final Function1 pred) {
      return new DistFromGen((g) -> this.loop$4(g, init, pred, f));
   }

   default Dist pack(final int n, final ClassTag ct) {
      return new Dist(ct, n) {
         // $FF: synthetic field
         private final Dist $outer;
         private final ClassTag ct$1;
         private final int n$2;

         public boolean apply$mcZ$sp(final Generator gen) {
            return Dist.super.apply$mcZ$sp(gen);
         }

         public byte apply$mcB$sp(final Generator gen) {
            return Dist.super.apply$mcB$sp(gen);
         }

         public char apply$mcC$sp(final Generator gen) {
            return Dist.super.apply$mcC$sp(gen);
         }

         public double apply$mcD$sp(final Generator gen) {
            return Dist.super.apply$mcD$sp(gen);
         }

         public float apply$mcF$sp(final Generator gen) {
            return Dist.super.apply$mcF$sp(gen);
         }

         public int apply$mcI$sp(final Generator gen) {
            return Dist.super.apply$mcI$sp(gen);
         }

         public long apply$mcJ$sp(final Generator gen) {
            return Dist.super.apply$mcJ$sp(gen);
         }

         public short apply$mcS$sp(final Generator gen) {
            return Dist.super.apply$mcS$sp(gen);
         }

         public void apply$mcV$sp(final Generator gen) {
            Dist.super.apply$mcV$sp(gen);
         }

         public void fill(final Generator gen, final Object arr) {
            Dist.super.fill(gen, arr);
         }

         public void fill$mcZ$sp(final Generator gen, final boolean[] arr) {
            Dist.super.fill$mcZ$sp(gen, arr);
         }

         public void fill$mcB$sp(final Generator gen, final byte[] arr) {
            Dist.super.fill$mcB$sp(gen, arr);
         }

         public void fill$mcC$sp(final Generator gen, final char[] arr) {
            Dist.super.fill$mcC$sp(gen, arr);
         }

         public void fill$mcD$sp(final Generator gen, final double[] arr) {
            Dist.super.fill$mcD$sp(gen, arr);
         }

         public void fill$mcF$sp(final Generator gen, final float[] arr) {
            Dist.super.fill$mcF$sp(gen, arr);
         }

         public void fill$mcI$sp(final Generator gen, final int[] arr) {
            Dist.super.fill$mcI$sp(gen, arr);
         }

         public void fill$mcJ$sp(final Generator gen, final long[] arr) {
            Dist.super.fill$mcJ$sp(gen, arr);
         }

         public void fill$mcS$sp(final Generator gen, final short[] arr) {
            Dist.super.fill$mcS$sp(gen, arr);
         }

         public void fill$mcV$sp(final Generator gen, final BoxedUnit[] arr) {
            Dist.super.fill$mcV$sp(gen, arr);
         }

         public Dist map(final Function1 f) {
            return Dist.super.map(f);
         }

         public Dist map$mcZ$sp(final Function1 f) {
            return Dist.super.map$mcZ$sp(f);
         }

         public Dist map$mcB$sp(final Function1 f) {
            return Dist.super.map$mcB$sp(f);
         }

         public Dist map$mcC$sp(final Function1 f) {
            return Dist.super.map$mcC$sp(f);
         }

         public Dist map$mcD$sp(final Function1 f) {
            return Dist.super.map$mcD$sp(f);
         }

         public Dist map$mcF$sp(final Function1 f) {
            return Dist.super.map$mcF$sp(f);
         }

         public Dist map$mcI$sp(final Function1 f) {
            return Dist.super.map$mcI$sp(f);
         }

         public Dist map$mcJ$sp(final Function1 f) {
            return Dist.super.map$mcJ$sp(f);
         }

         public Dist map$mcS$sp(final Function1 f) {
            return Dist.super.map$mcS$sp(f);
         }

         public Dist map$mcV$sp(final Function1 f) {
            return Dist.super.map$mcV$sp(f);
         }

         public Dist flatMap(final Function1 f) {
            return Dist.super.flatMap(f);
         }

         public Dist flatMap$mcZ$sp(final Function1 f) {
            return Dist.super.flatMap$mcZ$sp(f);
         }

         public Dist flatMap$mcB$sp(final Function1 f) {
            return Dist.super.flatMap$mcB$sp(f);
         }

         public Dist flatMap$mcC$sp(final Function1 f) {
            return Dist.super.flatMap$mcC$sp(f);
         }

         public Dist flatMap$mcD$sp(final Function1 f) {
            return Dist.super.flatMap$mcD$sp(f);
         }

         public Dist flatMap$mcF$sp(final Function1 f) {
            return Dist.super.flatMap$mcF$sp(f);
         }

         public Dist flatMap$mcI$sp(final Function1 f) {
            return Dist.super.flatMap$mcI$sp(f);
         }

         public Dist flatMap$mcJ$sp(final Function1 f) {
            return Dist.super.flatMap$mcJ$sp(f);
         }

         public Dist flatMap$mcS$sp(final Function1 f) {
            return Dist.super.flatMap$mcS$sp(f);
         }

         public Dist flatMap$mcV$sp(final Function1 f) {
            return Dist.super.flatMap$mcV$sp(f);
         }

         public Dist filter(final Function1 pred) {
            return Dist.super.filter(pred);
         }

         public Dist filter$mcZ$sp(final Function1 pred) {
            return Dist.super.filter$mcZ$sp(pred);
         }

         public Dist filter$mcB$sp(final Function1 pred) {
            return Dist.super.filter$mcB$sp(pred);
         }

         public Dist filter$mcC$sp(final Function1 pred) {
            return Dist.super.filter$mcC$sp(pred);
         }

         public Dist filter$mcD$sp(final Function1 pred) {
            return Dist.super.filter$mcD$sp(pred);
         }

         public Dist filter$mcF$sp(final Function1 pred) {
            return Dist.super.filter$mcF$sp(pred);
         }

         public Dist filter$mcI$sp(final Function1 pred) {
            return Dist.super.filter$mcI$sp(pred);
         }

         public Dist filter$mcJ$sp(final Function1 pred) {
            return Dist.super.filter$mcJ$sp(pred);
         }

         public Dist filter$mcS$sp(final Function1 pred) {
            return Dist.super.filter$mcS$sp(pred);
         }

         public Dist filter$mcV$sp(final Function1 pred) {
            return Dist.super.filter$mcV$sp(pred);
         }

         public Dist given(final Function1 pred) {
            return Dist.super.given(pred);
         }

         public Dist given$mcZ$sp(final Function1 pred) {
            return Dist.super.given$mcZ$sp(pred);
         }

         public Dist given$mcB$sp(final Function1 pred) {
            return Dist.super.given$mcB$sp(pred);
         }

         public Dist given$mcC$sp(final Function1 pred) {
            return Dist.super.given$mcC$sp(pred);
         }

         public Dist given$mcD$sp(final Function1 pred) {
            return Dist.super.given$mcD$sp(pred);
         }

         public Dist given$mcF$sp(final Function1 pred) {
            return Dist.super.given$mcF$sp(pred);
         }

         public Dist given$mcI$sp(final Function1 pred) {
            return Dist.super.given$mcI$sp(pred);
         }

         public Dist given$mcJ$sp(final Function1 pred) {
            return Dist.super.given$mcJ$sp(pred);
         }

         public Dist given$mcS$sp(final Function1 pred) {
            return Dist.super.given$mcS$sp(pred);
         }

         public Dist given$mcV$sp(final Function1 pred) {
            return Dist.super.given$mcV$sp(pred);
         }

         public Dist until(final Function1 pred) {
            return Dist.super.until(pred);
         }

         public Dist until$mcZ$sp(final Function1 pred) {
            return Dist.super.until$mcZ$sp(pred);
         }

         public Dist until$mcB$sp(final Function1 pred) {
            return Dist.super.until$mcB$sp(pred);
         }

         public Dist until$mcC$sp(final Function1 pred) {
            return Dist.super.until$mcC$sp(pred);
         }

         public Dist until$mcD$sp(final Function1 pred) {
            return Dist.super.until$mcD$sp(pred);
         }

         public Dist until$mcF$sp(final Function1 pred) {
            return Dist.super.until$mcF$sp(pred);
         }

         public Dist until$mcI$sp(final Function1 pred) {
            return Dist.super.until$mcI$sp(pred);
         }

         public Dist until$mcJ$sp(final Function1 pred) {
            return Dist.super.until$mcJ$sp(pred);
         }

         public Dist until$mcS$sp(final Function1 pred) {
            return Dist.super.until$mcS$sp(pred);
         }

         public Dist until$mcV$sp(final Function1 pred) {
            return Dist.super.until$mcV$sp(pred);
         }

         public Dist foldn(final Object init, final int n, final Function2 f) {
            return Dist.super.foldn(init, n, f);
         }

         public Dist foldn$mcZ$sp(final Object init, final int n, final Function2 f) {
            return Dist.super.foldn$mcZ$sp(init, n, f);
         }

         public Dist foldn$mcB$sp(final Object init, final int n, final Function2 f) {
            return Dist.super.foldn$mcB$sp(init, n, f);
         }

         public Dist foldn$mcC$sp(final Object init, final int n, final Function2 f) {
            return Dist.super.foldn$mcC$sp(init, n, f);
         }

         public Dist foldn$mcD$sp(final Object init, final int n, final Function2 f) {
            return Dist.super.foldn$mcD$sp(init, n, f);
         }

         public Dist foldn$mcF$sp(final Object init, final int n, final Function2 f) {
            return Dist.super.foldn$mcF$sp(init, n, f);
         }

         public Dist foldn$mcI$sp(final Object init, final int n, final Function2 f) {
            return Dist.super.foldn$mcI$sp(init, n, f);
         }

         public Dist foldn$mcJ$sp(final Object init, final int n, final Function2 f) {
            return Dist.super.foldn$mcJ$sp(init, n, f);
         }

         public Dist foldn$mcS$sp(final Object init, final int n, final Function2 f) {
            return Dist.super.foldn$mcS$sp(init, n, f);
         }

         public Dist foldn$mcV$sp(final Object init, final int n, final Function2 f) {
            return Dist.super.foldn$mcV$sp(init, n, f);
         }

         public Dist unfold(final Object init, final Function2 f, final Function1 pred) {
            return Dist.super.unfold(init, f, pred);
         }

         public Dist unfold$mcZ$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.super.unfold$mcZ$sp(init, f, pred);
         }

         public Dist unfold$mcB$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.super.unfold$mcB$sp(init, f, pred);
         }

         public Dist unfold$mcC$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.super.unfold$mcC$sp(init, f, pred);
         }

         public Dist unfold$mcD$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.super.unfold$mcD$sp(init, f, pred);
         }

         public Dist unfold$mcF$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.super.unfold$mcF$sp(init, f, pred);
         }

         public Dist unfold$mcI$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.super.unfold$mcI$sp(init, f, pred);
         }

         public Dist unfold$mcJ$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.super.unfold$mcJ$sp(init, f, pred);
         }

         public Dist unfold$mcS$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.super.unfold$mcS$sp(init, f, pred);
         }

         public Dist unfold$mcV$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.super.unfold$mcV$sp(init, f, pred);
         }

         public Dist pack(final int n, final ClassTag ct) {
            return Dist.super.pack(n, ct);
         }

         public Dist pack$mcZ$sp(final int n, final ClassTag ct) {
            return Dist.super.pack$mcZ$sp(n, ct);
         }

         public Dist pack$mcB$sp(final int n, final ClassTag ct) {
            return Dist.super.pack$mcB$sp(n, ct);
         }

         public Dist pack$mcC$sp(final int n, final ClassTag ct) {
            return Dist.super.pack$mcC$sp(n, ct);
         }

         public Dist pack$mcD$sp(final int n, final ClassTag ct) {
            return Dist.super.pack$mcD$sp(n, ct);
         }

         public Dist pack$mcF$sp(final int n, final ClassTag ct) {
            return Dist.super.pack$mcF$sp(n, ct);
         }

         public Dist pack$mcI$sp(final int n, final ClassTag ct) {
            return Dist.super.pack$mcI$sp(n, ct);
         }

         public Dist pack$mcJ$sp(final int n, final ClassTag ct) {
            return Dist.super.pack$mcJ$sp(n, ct);
         }

         public Dist pack$mcS$sp(final int n, final ClassTag ct) {
            return Dist.super.pack$mcS$sp(n, ct);
         }

         public Dist pack$mcV$sp(final int n, final ClassTag ct) {
            return Dist.super.pack$mcV$sp(n, ct);
         }

         public Dist repeat(final int n, final Factory cbf) {
            return Dist.super.repeat(n, cbf);
         }

         public Dist iterate(final int n, final Function1 f) {
            return Dist.super.iterate(n, f);
         }

         public Dist iterate$mcZ$sp(final int n, final Function1 f) {
            return Dist.super.iterate$mcZ$sp(n, f);
         }

         public Dist iterate$mcB$sp(final int n, final Function1 f) {
            return Dist.super.iterate$mcB$sp(n, f);
         }

         public Dist iterate$mcC$sp(final int n, final Function1 f) {
            return Dist.super.iterate$mcC$sp(n, f);
         }

         public Dist iterate$mcD$sp(final int n, final Function1 f) {
            return Dist.super.iterate$mcD$sp(n, f);
         }

         public Dist iterate$mcF$sp(final int n, final Function1 f) {
            return Dist.super.iterate$mcF$sp(n, f);
         }

         public Dist iterate$mcI$sp(final int n, final Function1 f) {
            return Dist.super.iterate$mcI$sp(n, f);
         }

         public Dist iterate$mcJ$sp(final int n, final Function1 f) {
            return Dist.super.iterate$mcJ$sp(n, f);
         }

         public Dist iterate$mcS$sp(final int n, final Function1 f) {
            return Dist.super.iterate$mcS$sp(n, f);
         }

         public Dist iterate$mcV$sp(final int n, final Function1 f) {
            return Dist.super.iterate$mcV$sp(n, f);
         }

         public Dist iterateUntil(final Function1 pred, final Function1 f) {
            return Dist.super.iterateUntil(pred, f);
         }

         public Dist iterateUntil$mcZ$sp(final Function1 pred, final Function1 f) {
            return Dist.super.iterateUntil$mcZ$sp(pred, f);
         }

         public Dist iterateUntil$mcB$sp(final Function1 pred, final Function1 f) {
            return Dist.super.iterateUntil$mcB$sp(pred, f);
         }

         public Dist iterateUntil$mcC$sp(final Function1 pred, final Function1 f) {
            return Dist.super.iterateUntil$mcC$sp(pred, f);
         }

         public Dist iterateUntil$mcD$sp(final Function1 pred, final Function1 f) {
            return Dist.super.iterateUntil$mcD$sp(pred, f);
         }

         public Dist iterateUntil$mcF$sp(final Function1 pred, final Function1 f) {
            return Dist.super.iterateUntil$mcF$sp(pred, f);
         }

         public Dist iterateUntil$mcI$sp(final Function1 pred, final Function1 f) {
            return Dist.super.iterateUntil$mcI$sp(pred, f);
         }

         public Dist iterateUntil$mcJ$sp(final Function1 pred, final Function1 f) {
            return Dist.super.iterateUntil$mcJ$sp(pred, f);
         }

         public Dist iterateUntil$mcS$sp(final Function1 pred, final Function1 f) {
            return Dist.super.iterateUntil$mcS$sp(pred, f);
         }

         public Dist iterateUntil$mcV$sp(final Function1 pred, final Function1 f) {
            return Dist.super.iterateUntil$mcV$sp(pred, f);
         }

         public Dist zip(final Dist that) {
            return Dist.super.zip(that);
         }

         public Dist zip$mcZ$sp(final Dist that) {
            return Dist.super.zip$mcZ$sp(that);
         }

         public Dist zip$mcB$sp(final Dist that) {
            return Dist.super.zip$mcB$sp(that);
         }

         public Dist zip$mcC$sp(final Dist that) {
            return Dist.super.zip$mcC$sp(that);
         }

         public Dist zip$mcD$sp(final Dist that) {
            return Dist.super.zip$mcD$sp(that);
         }

         public Dist zip$mcF$sp(final Dist that) {
            return Dist.super.zip$mcF$sp(that);
         }

         public Dist zip$mcI$sp(final Dist that) {
            return Dist.super.zip$mcI$sp(that);
         }

         public Dist zip$mcJ$sp(final Dist that) {
            return Dist.super.zip$mcJ$sp(that);
         }

         public Dist zip$mcS$sp(final Dist that) {
            return Dist.super.zip$mcS$sp(that);
         }

         public Dist zip$mcV$sp(final Dist that) {
            return Dist.super.zip$mcV$sp(that);
         }

         public Dist zipWith(final Dist that, final Function2 f) {
            return Dist.super.zipWith(that, f);
         }

         public Dist zipWith$mcZ$sp(final Dist that, final Function2 f) {
            return Dist.super.zipWith$mcZ$sp(that, f);
         }

         public Dist zipWith$mcB$sp(final Dist that, final Function2 f) {
            return Dist.super.zipWith$mcB$sp(that, f);
         }

         public Dist zipWith$mcC$sp(final Dist that, final Function2 f) {
            return Dist.super.zipWith$mcC$sp(that, f);
         }

         public Dist zipWith$mcD$sp(final Dist that, final Function2 f) {
            return Dist.super.zipWith$mcD$sp(that, f);
         }

         public Dist zipWith$mcF$sp(final Dist that, final Function2 f) {
            return Dist.super.zipWith$mcF$sp(that, f);
         }

         public Dist zipWith$mcI$sp(final Dist that, final Function2 f) {
            return Dist.super.zipWith$mcI$sp(that, f);
         }

         public Dist zipWith$mcJ$sp(final Dist that, final Function2 f) {
            return Dist.super.zipWith$mcJ$sp(that, f);
         }

         public Dist zipWith$mcS$sp(final Dist that, final Function2 f) {
            return Dist.super.zipWith$mcS$sp(that, f);
         }

         public Dist zipWith$mcV$sp(final Dist that, final Function2 f) {
            return Dist.super.zipWith$mcV$sp(that, f);
         }

         public final Iterator toIterator(final Generator gen) {
            return Dist.super.toIterator(gen);
         }

         public final LazyList toLazyList(final Generator gen) {
            return Dist.super.toLazyList(gen);
         }

         /** @deprecated */
         public final Stream toStream(final Generator gen) {
            return Dist.super.toStream(gen);
         }

         public Iterable sample(final int n, final Generator gen, final Factory cbf) {
            return Dist.super.sample(n, gen, cbf);
         }

         public int count(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.count(pred, n, gen);
         }

         public int count$mcZ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.count$mcZ$sp(pred, n, gen);
         }

         public int count$mcB$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.count$mcB$sp(pred, n, gen);
         }

         public int count$mcC$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.count$mcC$sp(pred, n, gen);
         }

         public int count$mcD$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.count$mcD$sp(pred, n, gen);
         }

         public int count$mcF$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.count$mcF$sp(pred, n, gen);
         }

         public int count$mcI$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.count$mcI$sp(pred, n, gen);
         }

         public int count$mcJ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.count$mcJ$sp(pred, n, gen);
         }

         public int count$mcS$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.count$mcS$sp(pred, n, gen);
         }

         public int count$mcV$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.count$mcV$sp(pred, n, gen);
         }

         public double pr(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.pr(pred, n, gen);
         }

         public double pr$mcZ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.pr$mcZ$sp(pred, n, gen);
         }

         public double pr$mcB$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.pr$mcB$sp(pred, n, gen);
         }

         public double pr$mcC$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.pr$mcC$sp(pred, n, gen);
         }

         public double pr$mcD$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.pr$mcD$sp(pred, n, gen);
         }

         public double pr$mcF$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.pr$mcF$sp(pred, n, gen);
         }

         public double pr$mcI$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.pr$mcI$sp(pred, n, gen);
         }

         public double pr$mcJ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.pr$mcJ$sp(pred, n, gen);
         }

         public double pr$mcS$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.pr$mcS$sp(pred, n, gen);
         }

         public double pr$mcV$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.pr$mcV$sp(pred, n, gen);
         }

         public Object sum(final int n, final Generator gen, final Rig alg) {
            return Dist.super.sum(n, gen, alg);
         }

         public boolean sum$mcZ$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.super.sum$mcZ$sp(n, gen, alg);
         }

         public byte sum$mcB$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.super.sum$mcB$sp(n, gen, alg);
         }

         public char sum$mcC$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.super.sum$mcC$sp(n, gen, alg);
         }

         public double sum$mcD$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.super.sum$mcD$sp(n, gen, alg);
         }

         public float sum$mcF$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.super.sum$mcF$sp(n, gen, alg);
         }

         public int sum$mcI$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.super.sum$mcI$sp(n, gen, alg);
         }

         public long sum$mcJ$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.super.sum$mcJ$sp(n, gen, alg);
         }

         public short sum$mcS$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.super.sum$mcS$sp(n, gen, alg);
         }

         public void sum$mcV$sp(final int n, final Generator gen, final Rig alg) {
            Dist.super.sum$mcV$sp(n, gen, alg);
         }

         public Object ev(final int n, final Generator gen, final Field alg) {
            return Dist.super.ev(n, gen, alg);
         }

         public boolean ev$mcZ$sp(final int n, final Generator gen, final Field alg) {
            return Dist.super.ev$mcZ$sp(n, gen, alg);
         }

         public byte ev$mcB$sp(final int n, final Generator gen, final Field alg) {
            return Dist.super.ev$mcB$sp(n, gen, alg);
         }

         public char ev$mcC$sp(final int n, final Generator gen, final Field alg) {
            return Dist.super.ev$mcC$sp(n, gen, alg);
         }

         public double ev$mcD$sp(final int n, final Generator gen, final Field alg) {
            return Dist.super.ev$mcD$sp(n, gen, alg);
         }

         public float ev$mcF$sp(final int n, final Generator gen, final Field alg) {
            return Dist.super.ev$mcF$sp(n, gen, alg);
         }

         public int ev$mcI$sp(final int n, final Generator gen, final Field alg) {
            return Dist.super.ev$mcI$sp(n, gen, alg);
         }

         public long ev$mcJ$sp(final int n, final Generator gen, final Field alg) {
            return Dist.super.ev$mcJ$sp(n, gen, alg);
         }

         public short ev$mcS$sp(final int n, final Generator gen, final Field alg) {
            return Dist.super.ev$mcS$sp(n, gen, alg);
         }

         public void ev$mcV$sp(final int n, final Generator gen, final Field alg) {
            Dist.super.ev$mcV$sp(n, gen, alg);
         }

         public Map histogram(final int n, final Generator gen) {
            return Dist.super.histogram(n, gen);
         }

         public Map rawHistogram(final int n, final Generator gen) {
            return Dist.super.rawHistogram(n, gen);
         }

         public Object apply(final Generator gen) {
            int i = 0;

            Object arr;
            for(arr = this.ct$1.newArray(this.n$2); i < .MODULE$.array_length(arr); ++i) {
               .MODULE$.array_update(arr, i, this.$outer.apply(gen));
            }

            return arr;
         }

         public {
            if (Dist.this == null) {
               throw null;
            } else {
               this.$outer = Dist.this;
               this.ct$1 = ct$1;
               this.n$2 = n$2;
               Dist.$init$(this);
            }
         }
      };
   }

   default Dist repeat(final int n, final Factory cbf) {
      return new Dist(cbf, n) {
         // $FF: synthetic field
         private final Dist $outer;
         private final Factory cbf$1;
         private final int n$3;

         public boolean apply$mcZ$sp(final Generator gen) {
            return Dist.super.apply$mcZ$sp(gen);
         }

         public byte apply$mcB$sp(final Generator gen) {
            return Dist.super.apply$mcB$sp(gen);
         }

         public char apply$mcC$sp(final Generator gen) {
            return Dist.super.apply$mcC$sp(gen);
         }

         public double apply$mcD$sp(final Generator gen) {
            return Dist.super.apply$mcD$sp(gen);
         }

         public float apply$mcF$sp(final Generator gen) {
            return Dist.super.apply$mcF$sp(gen);
         }

         public int apply$mcI$sp(final Generator gen) {
            return Dist.super.apply$mcI$sp(gen);
         }

         public long apply$mcJ$sp(final Generator gen) {
            return Dist.super.apply$mcJ$sp(gen);
         }

         public short apply$mcS$sp(final Generator gen) {
            return Dist.super.apply$mcS$sp(gen);
         }

         public void apply$mcV$sp(final Generator gen) {
            Dist.super.apply$mcV$sp(gen);
         }

         public void fill(final Generator gen, final Object arr) {
            Dist.super.fill(gen, arr);
         }

         public void fill$mcZ$sp(final Generator gen, final boolean[] arr) {
            Dist.super.fill$mcZ$sp(gen, arr);
         }

         public void fill$mcB$sp(final Generator gen, final byte[] arr) {
            Dist.super.fill$mcB$sp(gen, arr);
         }

         public void fill$mcC$sp(final Generator gen, final char[] arr) {
            Dist.super.fill$mcC$sp(gen, arr);
         }

         public void fill$mcD$sp(final Generator gen, final double[] arr) {
            Dist.super.fill$mcD$sp(gen, arr);
         }

         public void fill$mcF$sp(final Generator gen, final float[] arr) {
            Dist.super.fill$mcF$sp(gen, arr);
         }

         public void fill$mcI$sp(final Generator gen, final int[] arr) {
            Dist.super.fill$mcI$sp(gen, arr);
         }

         public void fill$mcJ$sp(final Generator gen, final long[] arr) {
            Dist.super.fill$mcJ$sp(gen, arr);
         }

         public void fill$mcS$sp(final Generator gen, final short[] arr) {
            Dist.super.fill$mcS$sp(gen, arr);
         }

         public void fill$mcV$sp(final Generator gen, final BoxedUnit[] arr) {
            Dist.super.fill$mcV$sp(gen, arr);
         }

         public Dist map(final Function1 f) {
            return Dist.super.map(f);
         }

         public Dist map$mcZ$sp(final Function1 f) {
            return Dist.super.map$mcZ$sp(f);
         }

         public Dist map$mcB$sp(final Function1 f) {
            return Dist.super.map$mcB$sp(f);
         }

         public Dist map$mcC$sp(final Function1 f) {
            return Dist.super.map$mcC$sp(f);
         }

         public Dist map$mcD$sp(final Function1 f) {
            return Dist.super.map$mcD$sp(f);
         }

         public Dist map$mcF$sp(final Function1 f) {
            return Dist.super.map$mcF$sp(f);
         }

         public Dist map$mcI$sp(final Function1 f) {
            return Dist.super.map$mcI$sp(f);
         }

         public Dist map$mcJ$sp(final Function1 f) {
            return Dist.super.map$mcJ$sp(f);
         }

         public Dist map$mcS$sp(final Function1 f) {
            return Dist.super.map$mcS$sp(f);
         }

         public Dist map$mcV$sp(final Function1 f) {
            return Dist.super.map$mcV$sp(f);
         }

         public Dist flatMap(final Function1 f) {
            return Dist.super.flatMap(f);
         }

         public Dist flatMap$mcZ$sp(final Function1 f) {
            return Dist.super.flatMap$mcZ$sp(f);
         }

         public Dist flatMap$mcB$sp(final Function1 f) {
            return Dist.super.flatMap$mcB$sp(f);
         }

         public Dist flatMap$mcC$sp(final Function1 f) {
            return Dist.super.flatMap$mcC$sp(f);
         }

         public Dist flatMap$mcD$sp(final Function1 f) {
            return Dist.super.flatMap$mcD$sp(f);
         }

         public Dist flatMap$mcF$sp(final Function1 f) {
            return Dist.super.flatMap$mcF$sp(f);
         }

         public Dist flatMap$mcI$sp(final Function1 f) {
            return Dist.super.flatMap$mcI$sp(f);
         }

         public Dist flatMap$mcJ$sp(final Function1 f) {
            return Dist.super.flatMap$mcJ$sp(f);
         }

         public Dist flatMap$mcS$sp(final Function1 f) {
            return Dist.super.flatMap$mcS$sp(f);
         }

         public Dist flatMap$mcV$sp(final Function1 f) {
            return Dist.super.flatMap$mcV$sp(f);
         }

         public Dist filter(final Function1 pred) {
            return Dist.super.filter(pred);
         }

         public Dist filter$mcZ$sp(final Function1 pred) {
            return Dist.super.filter$mcZ$sp(pred);
         }

         public Dist filter$mcB$sp(final Function1 pred) {
            return Dist.super.filter$mcB$sp(pred);
         }

         public Dist filter$mcC$sp(final Function1 pred) {
            return Dist.super.filter$mcC$sp(pred);
         }

         public Dist filter$mcD$sp(final Function1 pred) {
            return Dist.super.filter$mcD$sp(pred);
         }

         public Dist filter$mcF$sp(final Function1 pred) {
            return Dist.super.filter$mcF$sp(pred);
         }

         public Dist filter$mcI$sp(final Function1 pred) {
            return Dist.super.filter$mcI$sp(pred);
         }

         public Dist filter$mcJ$sp(final Function1 pred) {
            return Dist.super.filter$mcJ$sp(pred);
         }

         public Dist filter$mcS$sp(final Function1 pred) {
            return Dist.super.filter$mcS$sp(pred);
         }

         public Dist filter$mcV$sp(final Function1 pred) {
            return Dist.super.filter$mcV$sp(pred);
         }

         public Dist given(final Function1 pred) {
            return Dist.super.given(pred);
         }

         public Dist given$mcZ$sp(final Function1 pred) {
            return Dist.super.given$mcZ$sp(pred);
         }

         public Dist given$mcB$sp(final Function1 pred) {
            return Dist.super.given$mcB$sp(pred);
         }

         public Dist given$mcC$sp(final Function1 pred) {
            return Dist.super.given$mcC$sp(pred);
         }

         public Dist given$mcD$sp(final Function1 pred) {
            return Dist.super.given$mcD$sp(pred);
         }

         public Dist given$mcF$sp(final Function1 pred) {
            return Dist.super.given$mcF$sp(pred);
         }

         public Dist given$mcI$sp(final Function1 pred) {
            return Dist.super.given$mcI$sp(pred);
         }

         public Dist given$mcJ$sp(final Function1 pred) {
            return Dist.super.given$mcJ$sp(pred);
         }

         public Dist given$mcS$sp(final Function1 pred) {
            return Dist.super.given$mcS$sp(pred);
         }

         public Dist given$mcV$sp(final Function1 pred) {
            return Dist.super.given$mcV$sp(pred);
         }

         public Dist until(final Function1 pred) {
            return Dist.super.until(pred);
         }

         public Dist until$mcZ$sp(final Function1 pred) {
            return Dist.super.until$mcZ$sp(pred);
         }

         public Dist until$mcB$sp(final Function1 pred) {
            return Dist.super.until$mcB$sp(pred);
         }

         public Dist until$mcC$sp(final Function1 pred) {
            return Dist.super.until$mcC$sp(pred);
         }

         public Dist until$mcD$sp(final Function1 pred) {
            return Dist.super.until$mcD$sp(pred);
         }

         public Dist until$mcF$sp(final Function1 pred) {
            return Dist.super.until$mcF$sp(pred);
         }

         public Dist until$mcI$sp(final Function1 pred) {
            return Dist.super.until$mcI$sp(pred);
         }

         public Dist until$mcJ$sp(final Function1 pred) {
            return Dist.super.until$mcJ$sp(pred);
         }

         public Dist until$mcS$sp(final Function1 pred) {
            return Dist.super.until$mcS$sp(pred);
         }

         public Dist until$mcV$sp(final Function1 pred) {
            return Dist.super.until$mcV$sp(pred);
         }

         public Dist foldn(final Object init, final int n, final Function2 f) {
            return Dist.super.foldn(init, n, f);
         }

         public Dist foldn$mcZ$sp(final Object init, final int n, final Function2 f) {
            return Dist.super.foldn$mcZ$sp(init, n, f);
         }

         public Dist foldn$mcB$sp(final Object init, final int n, final Function2 f) {
            return Dist.super.foldn$mcB$sp(init, n, f);
         }

         public Dist foldn$mcC$sp(final Object init, final int n, final Function2 f) {
            return Dist.super.foldn$mcC$sp(init, n, f);
         }

         public Dist foldn$mcD$sp(final Object init, final int n, final Function2 f) {
            return Dist.super.foldn$mcD$sp(init, n, f);
         }

         public Dist foldn$mcF$sp(final Object init, final int n, final Function2 f) {
            return Dist.super.foldn$mcF$sp(init, n, f);
         }

         public Dist foldn$mcI$sp(final Object init, final int n, final Function2 f) {
            return Dist.super.foldn$mcI$sp(init, n, f);
         }

         public Dist foldn$mcJ$sp(final Object init, final int n, final Function2 f) {
            return Dist.super.foldn$mcJ$sp(init, n, f);
         }

         public Dist foldn$mcS$sp(final Object init, final int n, final Function2 f) {
            return Dist.super.foldn$mcS$sp(init, n, f);
         }

         public Dist foldn$mcV$sp(final Object init, final int n, final Function2 f) {
            return Dist.super.foldn$mcV$sp(init, n, f);
         }

         public Dist unfold(final Object init, final Function2 f, final Function1 pred) {
            return Dist.super.unfold(init, f, pred);
         }

         public Dist unfold$mcZ$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.super.unfold$mcZ$sp(init, f, pred);
         }

         public Dist unfold$mcB$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.super.unfold$mcB$sp(init, f, pred);
         }

         public Dist unfold$mcC$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.super.unfold$mcC$sp(init, f, pred);
         }

         public Dist unfold$mcD$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.super.unfold$mcD$sp(init, f, pred);
         }

         public Dist unfold$mcF$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.super.unfold$mcF$sp(init, f, pred);
         }

         public Dist unfold$mcI$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.super.unfold$mcI$sp(init, f, pred);
         }

         public Dist unfold$mcJ$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.super.unfold$mcJ$sp(init, f, pred);
         }

         public Dist unfold$mcS$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.super.unfold$mcS$sp(init, f, pred);
         }

         public Dist unfold$mcV$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.super.unfold$mcV$sp(init, f, pred);
         }

         public Dist pack(final int n, final ClassTag ct) {
            return Dist.super.pack(n, ct);
         }

         public Dist pack$mcZ$sp(final int n, final ClassTag ct) {
            return Dist.super.pack$mcZ$sp(n, ct);
         }

         public Dist pack$mcB$sp(final int n, final ClassTag ct) {
            return Dist.super.pack$mcB$sp(n, ct);
         }

         public Dist pack$mcC$sp(final int n, final ClassTag ct) {
            return Dist.super.pack$mcC$sp(n, ct);
         }

         public Dist pack$mcD$sp(final int n, final ClassTag ct) {
            return Dist.super.pack$mcD$sp(n, ct);
         }

         public Dist pack$mcF$sp(final int n, final ClassTag ct) {
            return Dist.super.pack$mcF$sp(n, ct);
         }

         public Dist pack$mcI$sp(final int n, final ClassTag ct) {
            return Dist.super.pack$mcI$sp(n, ct);
         }

         public Dist pack$mcJ$sp(final int n, final ClassTag ct) {
            return Dist.super.pack$mcJ$sp(n, ct);
         }

         public Dist pack$mcS$sp(final int n, final ClassTag ct) {
            return Dist.super.pack$mcS$sp(n, ct);
         }

         public Dist pack$mcV$sp(final int n, final ClassTag ct) {
            return Dist.super.pack$mcV$sp(n, ct);
         }

         public Dist repeat(final int n, final Factory cbf) {
            return Dist.super.repeat(n, cbf);
         }

         public Dist iterate(final int n, final Function1 f) {
            return Dist.super.iterate(n, f);
         }

         public Dist iterate$mcZ$sp(final int n, final Function1 f) {
            return Dist.super.iterate$mcZ$sp(n, f);
         }

         public Dist iterate$mcB$sp(final int n, final Function1 f) {
            return Dist.super.iterate$mcB$sp(n, f);
         }

         public Dist iterate$mcC$sp(final int n, final Function1 f) {
            return Dist.super.iterate$mcC$sp(n, f);
         }

         public Dist iterate$mcD$sp(final int n, final Function1 f) {
            return Dist.super.iterate$mcD$sp(n, f);
         }

         public Dist iterate$mcF$sp(final int n, final Function1 f) {
            return Dist.super.iterate$mcF$sp(n, f);
         }

         public Dist iterate$mcI$sp(final int n, final Function1 f) {
            return Dist.super.iterate$mcI$sp(n, f);
         }

         public Dist iterate$mcJ$sp(final int n, final Function1 f) {
            return Dist.super.iterate$mcJ$sp(n, f);
         }

         public Dist iterate$mcS$sp(final int n, final Function1 f) {
            return Dist.super.iterate$mcS$sp(n, f);
         }

         public Dist iterate$mcV$sp(final int n, final Function1 f) {
            return Dist.super.iterate$mcV$sp(n, f);
         }

         public Dist iterateUntil(final Function1 pred, final Function1 f) {
            return Dist.super.iterateUntil(pred, f);
         }

         public Dist iterateUntil$mcZ$sp(final Function1 pred, final Function1 f) {
            return Dist.super.iterateUntil$mcZ$sp(pred, f);
         }

         public Dist iterateUntil$mcB$sp(final Function1 pred, final Function1 f) {
            return Dist.super.iterateUntil$mcB$sp(pred, f);
         }

         public Dist iterateUntil$mcC$sp(final Function1 pred, final Function1 f) {
            return Dist.super.iterateUntil$mcC$sp(pred, f);
         }

         public Dist iterateUntil$mcD$sp(final Function1 pred, final Function1 f) {
            return Dist.super.iterateUntil$mcD$sp(pred, f);
         }

         public Dist iterateUntil$mcF$sp(final Function1 pred, final Function1 f) {
            return Dist.super.iterateUntil$mcF$sp(pred, f);
         }

         public Dist iterateUntil$mcI$sp(final Function1 pred, final Function1 f) {
            return Dist.super.iterateUntil$mcI$sp(pred, f);
         }

         public Dist iterateUntil$mcJ$sp(final Function1 pred, final Function1 f) {
            return Dist.super.iterateUntil$mcJ$sp(pred, f);
         }

         public Dist iterateUntil$mcS$sp(final Function1 pred, final Function1 f) {
            return Dist.super.iterateUntil$mcS$sp(pred, f);
         }

         public Dist iterateUntil$mcV$sp(final Function1 pred, final Function1 f) {
            return Dist.super.iterateUntil$mcV$sp(pred, f);
         }

         public Dist zip(final Dist that) {
            return Dist.super.zip(that);
         }

         public Dist zip$mcZ$sp(final Dist that) {
            return Dist.super.zip$mcZ$sp(that);
         }

         public Dist zip$mcB$sp(final Dist that) {
            return Dist.super.zip$mcB$sp(that);
         }

         public Dist zip$mcC$sp(final Dist that) {
            return Dist.super.zip$mcC$sp(that);
         }

         public Dist zip$mcD$sp(final Dist that) {
            return Dist.super.zip$mcD$sp(that);
         }

         public Dist zip$mcF$sp(final Dist that) {
            return Dist.super.zip$mcF$sp(that);
         }

         public Dist zip$mcI$sp(final Dist that) {
            return Dist.super.zip$mcI$sp(that);
         }

         public Dist zip$mcJ$sp(final Dist that) {
            return Dist.super.zip$mcJ$sp(that);
         }

         public Dist zip$mcS$sp(final Dist that) {
            return Dist.super.zip$mcS$sp(that);
         }

         public Dist zip$mcV$sp(final Dist that) {
            return Dist.super.zip$mcV$sp(that);
         }

         public Dist zipWith(final Dist that, final Function2 f) {
            return Dist.super.zipWith(that, f);
         }

         public Dist zipWith$mcZ$sp(final Dist that, final Function2 f) {
            return Dist.super.zipWith$mcZ$sp(that, f);
         }

         public Dist zipWith$mcB$sp(final Dist that, final Function2 f) {
            return Dist.super.zipWith$mcB$sp(that, f);
         }

         public Dist zipWith$mcC$sp(final Dist that, final Function2 f) {
            return Dist.super.zipWith$mcC$sp(that, f);
         }

         public Dist zipWith$mcD$sp(final Dist that, final Function2 f) {
            return Dist.super.zipWith$mcD$sp(that, f);
         }

         public Dist zipWith$mcF$sp(final Dist that, final Function2 f) {
            return Dist.super.zipWith$mcF$sp(that, f);
         }

         public Dist zipWith$mcI$sp(final Dist that, final Function2 f) {
            return Dist.super.zipWith$mcI$sp(that, f);
         }

         public Dist zipWith$mcJ$sp(final Dist that, final Function2 f) {
            return Dist.super.zipWith$mcJ$sp(that, f);
         }

         public Dist zipWith$mcS$sp(final Dist that, final Function2 f) {
            return Dist.super.zipWith$mcS$sp(that, f);
         }

         public Dist zipWith$mcV$sp(final Dist that, final Function2 f) {
            return Dist.super.zipWith$mcV$sp(that, f);
         }

         public final Iterator toIterator(final Generator gen) {
            return Dist.super.toIterator(gen);
         }

         public final LazyList toLazyList(final Generator gen) {
            return Dist.super.toLazyList(gen);
         }

         /** @deprecated */
         public final Stream toStream(final Generator gen) {
            return Dist.super.toStream(gen);
         }

         public Iterable sample(final int n, final Generator gen, final Factory cbf) {
            return Dist.super.sample(n, gen, cbf);
         }

         public int count(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.count(pred, n, gen);
         }

         public int count$mcZ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.count$mcZ$sp(pred, n, gen);
         }

         public int count$mcB$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.count$mcB$sp(pred, n, gen);
         }

         public int count$mcC$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.count$mcC$sp(pred, n, gen);
         }

         public int count$mcD$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.count$mcD$sp(pred, n, gen);
         }

         public int count$mcF$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.count$mcF$sp(pred, n, gen);
         }

         public int count$mcI$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.count$mcI$sp(pred, n, gen);
         }

         public int count$mcJ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.count$mcJ$sp(pred, n, gen);
         }

         public int count$mcS$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.count$mcS$sp(pred, n, gen);
         }

         public int count$mcV$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.count$mcV$sp(pred, n, gen);
         }

         public double pr(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.pr(pred, n, gen);
         }

         public double pr$mcZ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.pr$mcZ$sp(pred, n, gen);
         }

         public double pr$mcB$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.pr$mcB$sp(pred, n, gen);
         }

         public double pr$mcC$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.pr$mcC$sp(pred, n, gen);
         }

         public double pr$mcD$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.pr$mcD$sp(pred, n, gen);
         }

         public double pr$mcF$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.pr$mcF$sp(pred, n, gen);
         }

         public double pr$mcI$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.pr$mcI$sp(pred, n, gen);
         }

         public double pr$mcJ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.pr$mcJ$sp(pred, n, gen);
         }

         public double pr$mcS$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.pr$mcS$sp(pred, n, gen);
         }

         public double pr$mcV$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.pr$mcV$sp(pred, n, gen);
         }

         public Object sum(final int n, final Generator gen, final Rig alg) {
            return Dist.super.sum(n, gen, alg);
         }

         public boolean sum$mcZ$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.super.sum$mcZ$sp(n, gen, alg);
         }

         public byte sum$mcB$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.super.sum$mcB$sp(n, gen, alg);
         }

         public char sum$mcC$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.super.sum$mcC$sp(n, gen, alg);
         }

         public double sum$mcD$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.super.sum$mcD$sp(n, gen, alg);
         }

         public float sum$mcF$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.super.sum$mcF$sp(n, gen, alg);
         }

         public int sum$mcI$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.super.sum$mcI$sp(n, gen, alg);
         }

         public long sum$mcJ$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.super.sum$mcJ$sp(n, gen, alg);
         }

         public short sum$mcS$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.super.sum$mcS$sp(n, gen, alg);
         }

         public void sum$mcV$sp(final int n, final Generator gen, final Rig alg) {
            Dist.super.sum$mcV$sp(n, gen, alg);
         }

         public Object ev(final int n, final Generator gen, final Field alg) {
            return Dist.super.ev(n, gen, alg);
         }

         public boolean ev$mcZ$sp(final int n, final Generator gen, final Field alg) {
            return Dist.super.ev$mcZ$sp(n, gen, alg);
         }

         public byte ev$mcB$sp(final int n, final Generator gen, final Field alg) {
            return Dist.super.ev$mcB$sp(n, gen, alg);
         }

         public char ev$mcC$sp(final int n, final Generator gen, final Field alg) {
            return Dist.super.ev$mcC$sp(n, gen, alg);
         }

         public double ev$mcD$sp(final int n, final Generator gen, final Field alg) {
            return Dist.super.ev$mcD$sp(n, gen, alg);
         }

         public float ev$mcF$sp(final int n, final Generator gen, final Field alg) {
            return Dist.super.ev$mcF$sp(n, gen, alg);
         }

         public int ev$mcI$sp(final int n, final Generator gen, final Field alg) {
            return Dist.super.ev$mcI$sp(n, gen, alg);
         }

         public long ev$mcJ$sp(final int n, final Generator gen, final Field alg) {
            return Dist.super.ev$mcJ$sp(n, gen, alg);
         }

         public short ev$mcS$sp(final int n, final Generator gen, final Field alg) {
            return Dist.super.ev$mcS$sp(n, gen, alg);
         }

         public void ev$mcV$sp(final int n, final Generator gen, final Field alg) {
            Dist.super.ev$mcV$sp(n, gen, alg);
         }

         public Map histogram(final int n, final Generator gen) {
            return Dist.super.histogram(n, gen);
         }

         public Map rawHistogram(final int n, final Generator gen) {
            return Dist.super.rawHistogram(n, gen);
         }

         public Seq apply(final Generator gen) {
            Builder builder = this.cbf$1.newBuilder();
            builder.sizeHint(this.n$3);

            for(int i = 0; i < this.n$3; ++i) {
               builder.$plus$eq(this.$outer.apply(gen));
            }

            return (Seq)builder.result();
         }

         public {
            if (Dist.this == null) {
               throw null;
            } else {
               this.$outer = Dist.this;
               this.cbf$1 = cbf$1;
               this.n$3 = n$3;
               Dist.$init$(this);
            }
         }
      };
   }

   default Dist iterate(final int n, final Function1 f) {
      return n == 0 ? this : this.flatMap(f).iterate(n - 1, f);
   }

   default Dist iterateUntil(final Function1 pred, final Function1 f) {
      return new Dist(pred, f) {
         // $FF: synthetic field
         private final Dist $outer;
         private final Function1 pred$4;
         private final Function1 f$5;

         public boolean apply$mcZ$sp(final Generator gen) {
            return Dist.super.apply$mcZ$sp(gen);
         }

         public byte apply$mcB$sp(final Generator gen) {
            return Dist.super.apply$mcB$sp(gen);
         }

         public char apply$mcC$sp(final Generator gen) {
            return Dist.super.apply$mcC$sp(gen);
         }

         public double apply$mcD$sp(final Generator gen) {
            return Dist.super.apply$mcD$sp(gen);
         }

         public float apply$mcF$sp(final Generator gen) {
            return Dist.super.apply$mcF$sp(gen);
         }

         public int apply$mcI$sp(final Generator gen) {
            return Dist.super.apply$mcI$sp(gen);
         }

         public long apply$mcJ$sp(final Generator gen) {
            return Dist.super.apply$mcJ$sp(gen);
         }

         public short apply$mcS$sp(final Generator gen) {
            return Dist.super.apply$mcS$sp(gen);
         }

         public void apply$mcV$sp(final Generator gen) {
            Dist.super.apply$mcV$sp(gen);
         }

         public void fill(final Generator gen, final Object arr) {
            Dist.super.fill(gen, arr);
         }

         public void fill$mcZ$sp(final Generator gen, final boolean[] arr) {
            Dist.super.fill$mcZ$sp(gen, arr);
         }

         public void fill$mcB$sp(final Generator gen, final byte[] arr) {
            Dist.super.fill$mcB$sp(gen, arr);
         }

         public void fill$mcC$sp(final Generator gen, final char[] arr) {
            Dist.super.fill$mcC$sp(gen, arr);
         }

         public void fill$mcD$sp(final Generator gen, final double[] arr) {
            Dist.super.fill$mcD$sp(gen, arr);
         }

         public void fill$mcF$sp(final Generator gen, final float[] arr) {
            Dist.super.fill$mcF$sp(gen, arr);
         }

         public void fill$mcI$sp(final Generator gen, final int[] arr) {
            Dist.super.fill$mcI$sp(gen, arr);
         }

         public void fill$mcJ$sp(final Generator gen, final long[] arr) {
            Dist.super.fill$mcJ$sp(gen, arr);
         }

         public void fill$mcS$sp(final Generator gen, final short[] arr) {
            Dist.super.fill$mcS$sp(gen, arr);
         }

         public void fill$mcV$sp(final Generator gen, final BoxedUnit[] arr) {
            Dist.super.fill$mcV$sp(gen, arr);
         }

         public Dist map(final Function1 f) {
            return Dist.super.map(f);
         }

         public Dist map$mcZ$sp(final Function1 f) {
            return Dist.super.map$mcZ$sp(f);
         }

         public Dist map$mcB$sp(final Function1 f) {
            return Dist.super.map$mcB$sp(f);
         }

         public Dist map$mcC$sp(final Function1 f) {
            return Dist.super.map$mcC$sp(f);
         }

         public Dist map$mcD$sp(final Function1 f) {
            return Dist.super.map$mcD$sp(f);
         }

         public Dist map$mcF$sp(final Function1 f) {
            return Dist.super.map$mcF$sp(f);
         }

         public Dist map$mcI$sp(final Function1 f) {
            return Dist.super.map$mcI$sp(f);
         }

         public Dist map$mcJ$sp(final Function1 f) {
            return Dist.super.map$mcJ$sp(f);
         }

         public Dist map$mcS$sp(final Function1 f) {
            return Dist.super.map$mcS$sp(f);
         }

         public Dist map$mcV$sp(final Function1 f) {
            return Dist.super.map$mcV$sp(f);
         }

         public Dist flatMap(final Function1 f) {
            return Dist.super.flatMap(f);
         }

         public Dist flatMap$mcZ$sp(final Function1 f) {
            return Dist.super.flatMap$mcZ$sp(f);
         }

         public Dist flatMap$mcB$sp(final Function1 f) {
            return Dist.super.flatMap$mcB$sp(f);
         }

         public Dist flatMap$mcC$sp(final Function1 f) {
            return Dist.super.flatMap$mcC$sp(f);
         }

         public Dist flatMap$mcD$sp(final Function1 f) {
            return Dist.super.flatMap$mcD$sp(f);
         }

         public Dist flatMap$mcF$sp(final Function1 f) {
            return Dist.super.flatMap$mcF$sp(f);
         }

         public Dist flatMap$mcI$sp(final Function1 f) {
            return Dist.super.flatMap$mcI$sp(f);
         }

         public Dist flatMap$mcJ$sp(final Function1 f) {
            return Dist.super.flatMap$mcJ$sp(f);
         }

         public Dist flatMap$mcS$sp(final Function1 f) {
            return Dist.super.flatMap$mcS$sp(f);
         }

         public Dist flatMap$mcV$sp(final Function1 f) {
            return Dist.super.flatMap$mcV$sp(f);
         }

         public Dist filter(final Function1 pred) {
            return Dist.super.filter(pred);
         }

         public Dist filter$mcZ$sp(final Function1 pred) {
            return Dist.super.filter$mcZ$sp(pred);
         }

         public Dist filter$mcB$sp(final Function1 pred) {
            return Dist.super.filter$mcB$sp(pred);
         }

         public Dist filter$mcC$sp(final Function1 pred) {
            return Dist.super.filter$mcC$sp(pred);
         }

         public Dist filter$mcD$sp(final Function1 pred) {
            return Dist.super.filter$mcD$sp(pred);
         }

         public Dist filter$mcF$sp(final Function1 pred) {
            return Dist.super.filter$mcF$sp(pred);
         }

         public Dist filter$mcI$sp(final Function1 pred) {
            return Dist.super.filter$mcI$sp(pred);
         }

         public Dist filter$mcJ$sp(final Function1 pred) {
            return Dist.super.filter$mcJ$sp(pred);
         }

         public Dist filter$mcS$sp(final Function1 pred) {
            return Dist.super.filter$mcS$sp(pred);
         }

         public Dist filter$mcV$sp(final Function1 pred) {
            return Dist.super.filter$mcV$sp(pred);
         }

         public Dist given(final Function1 pred) {
            return Dist.super.given(pred);
         }

         public Dist given$mcZ$sp(final Function1 pred) {
            return Dist.super.given$mcZ$sp(pred);
         }

         public Dist given$mcB$sp(final Function1 pred) {
            return Dist.super.given$mcB$sp(pred);
         }

         public Dist given$mcC$sp(final Function1 pred) {
            return Dist.super.given$mcC$sp(pred);
         }

         public Dist given$mcD$sp(final Function1 pred) {
            return Dist.super.given$mcD$sp(pred);
         }

         public Dist given$mcF$sp(final Function1 pred) {
            return Dist.super.given$mcF$sp(pred);
         }

         public Dist given$mcI$sp(final Function1 pred) {
            return Dist.super.given$mcI$sp(pred);
         }

         public Dist given$mcJ$sp(final Function1 pred) {
            return Dist.super.given$mcJ$sp(pred);
         }

         public Dist given$mcS$sp(final Function1 pred) {
            return Dist.super.given$mcS$sp(pred);
         }

         public Dist given$mcV$sp(final Function1 pred) {
            return Dist.super.given$mcV$sp(pred);
         }

         public Dist until(final Function1 pred) {
            return Dist.super.until(pred);
         }

         public Dist until$mcZ$sp(final Function1 pred) {
            return Dist.super.until$mcZ$sp(pred);
         }

         public Dist until$mcB$sp(final Function1 pred) {
            return Dist.super.until$mcB$sp(pred);
         }

         public Dist until$mcC$sp(final Function1 pred) {
            return Dist.super.until$mcC$sp(pred);
         }

         public Dist until$mcD$sp(final Function1 pred) {
            return Dist.super.until$mcD$sp(pred);
         }

         public Dist until$mcF$sp(final Function1 pred) {
            return Dist.super.until$mcF$sp(pred);
         }

         public Dist until$mcI$sp(final Function1 pred) {
            return Dist.super.until$mcI$sp(pred);
         }

         public Dist until$mcJ$sp(final Function1 pred) {
            return Dist.super.until$mcJ$sp(pred);
         }

         public Dist until$mcS$sp(final Function1 pred) {
            return Dist.super.until$mcS$sp(pred);
         }

         public Dist until$mcV$sp(final Function1 pred) {
            return Dist.super.until$mcV$sp(pred);
         }

         public Dist foldn(final Object init, final int n, final Function2 f) {
            return Dist.super.foldn(init, n, f);
         }

         public Dist foldn$mcZ$sp(final Object init, final int n, final Function2 f) {
            return Dist.super.foldn$mcZ$sp(init, n, f);
         }

         public Dist foldn$mcB$sp(final Object init, final int n, final Function2 f) {
            return Dist.super.foldn$mcB$sp(init, n, f);
         }

         public Dist foldn$mcC$sp(final Object init, final int n, final Function2 f) {
            return Dist.super.foldn$mcC$sp(init, n, f);
         }

         public Dist foldn$mcD$sp(final Object init, final int n, final Function2 f) {
            return Dist.super.foldn$mcD$sp(init, n, f);
         }

         public Dist foldn$mcF$sp(final Object init, final int n, final Function2 f) {
            return Dist.super.foldn$mcF$sp(init, n, f);
         }

         public Dist foldn$mcI$sp(final Object init, final int n, final Function2 f) {
            return Dist.super.foldn$mcI$sp(init, n, f);
         }

         public Dist foldn$mcJ$sp(final Object init, final int n, final Function2 f) {
            return Dist.super.foldn$mcJ$sp(init, n, f);
         }

         public Dist foldn$mcS$sp(final Object init, final int n, final Function2 f) {
            return Dist.super.foldn$mcS$sp(init, n, f);
         }

         public Dist foldn$mcV$sp(final Object init, final int n, final Function2 f) {
            return Dist.super.foldn$mcV$sp(init, n, f);
         }

         public Dist unfold(final Object init, final Function2 f, final Function1 pred) {
            return Dist.super.unfold(init, f, pred);
         }

         public Dist unfold$mcZ$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.super.unfold$mcZ$sp(init, f, pred);
         }

         public Dist unfold$mcB$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.super.unfold$mcB$sp(init, f, pred);
         }

         public Dist unfold$mcC$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.super.unfold$mcC$sp(init, f, pred);
         }

         public Dist unfold$mcD$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.super.unfold$mcD$sp(init, f, pred);
         }

         public Dist unfold$mcF$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.super.unfold$mcF$sp(init, f, pred);
         }

         public Dist unfold$mcI$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.super.unfold$mcI$sp(init, f, pred);
         }

         public Dist unfold$mcJ$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.super.unfold$mcJ$sp(init, f, pred);
         }

         public Dist unfold$mcS$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.super.unfold$mcS$sp(init, f, pred);
         }

         public Dist unfold$mcV$sp(final Object init, final Function2 f, final Function1 pred) {
            return Dist.super.unfold$mcV$sp(init, f, pred);
         }

         public Dist pack(final int n, final ClassTag ct) {
            return Dist.super.pack(n, ct);
         }

         public Dist pack$mcZ$sp(final int n, final ClassTag ct) {
            return Dist.super.pack$mcZ$sp(n, ct);
         }

         public Dist pack$mcB$sp(final int n, final ClassTag ct) {
            return Dist.super.pack$mcB$sp(n, ct);
         }

         public Dist pack$mcC$sp(final int n, final ClassTag ct) {
            return Dist.super.pack$mcC$sp(n, ct);
         }

         public Dist pack$mcD$sp(final int n, final ClassTag ct) {
            return Dist.super.pack$mcD$sp(n, ct);
         }

         public Dist pack$mcF$sp(final int n, final ClassTag ct) {
            return Dist.super.pack$mcF$sp(n, ct);
         }

         public Dist pack$mcI$sp(final int n, final ClassTag ct) {
            return Dist.super.pack$mcI$sp(n, ct);
         }

         public Dist pack$mcJ$sp(final int n, final ClassTag ct) {
            return Dist.super.pack$mcJ$sp(n, ct);
         }

         public Dist pack$mcS$sp(final int n, final ClassTag ct) {
            return Dist.super.pack$mcS$sp(n, ct);
         }

         public Dist pack$mcV$sp(final int n, final ClassTag ct) {
            return Dist.super.pack$mcV$sp(n, ct);
         }

         public Dist repeat(final int n, final Factory cbf) {
            return Dist.super.repeat(n, cbf);
         }

         public Dist iterate(final int n, final Function1 f) {
            return Dist.super.iterate(n, f);
         }

         public Dist iterate$mcZ$sp(final int n, final Function1 f) {
            return Dist.super.iterate$mcZ$sp(n, f);
         }

         public Dist iterate$mcB$sp(final int n, final Function1 f) {
            return Dist.super.iterate$mcB$sp(n, f);
         }

         public Dist iterate$mcC$sp(final int n, final Function1 f) {
            return Dist.super.iterate$mcC$sp(n, f);
         }

         public Dist iterate$mcD$sp(final int n, final Function1 f) {
            return Dist.super.iterate$mcD$sp(n, f);
         }

         public Dist iterate$mcF$sp(final int n, final Function1 f) {
            return Dist.super.iterate$mcF$sp(n, f);
         }

         public Dist iterate$mcI$sp(final int n, final Function1 f) {
            return Dist.super.iterate$mcI$sp(n, f);
         }

         public Dist iterate$mcJ$sp(final int n, final Function1 f) {
            return Dist.super.iterate$mcJ$sp(n, f);
         }

         public Dist iterate$mcS$sp(final int n, final Function1 f) {
            return Dist.super.iterate$mcS$sp(n, f);
         }

         public Dist iterate$mcV$sp(final int n, final Function1 f) {
            return Dist.super.iterate$mcV$sp(n, f);
         }

         public Dist iterateUntil(final Function1 pred, final Function1 f) {
            return Dist.super.iterateUntil(pred, f);
         }

         public Dist iterateUntil$mcZ$sp(final Function1 pred, final Function1 f) {
            return Dist.super.iterateUntil$mcZ$sp(pred, f);
         }

         public Dist iterateUntil$mcB$sp(final Function1 pred, final Function1 f) {
            return Dist.super.iterateUntil$mcB$sp(pred, f);
         }

         public Dist iterateUntil$mcC$sp(final Function1 pred, final Function1 f) {
            return Dist.super.iterateUntil$mcC$sp(pred, f);
         }

         public Dist iterateUntil$mcD$sp(final Function1 pred, final Function1 f) {
            return Dist.super.iterateUntil$mcD$sp(pred, f);
         }

         public Dist iterateUntil$mcF$sp(final Function1 pred, final Function1 f) {
            return Dist.super.iterateUntil$mcF$sp(pred, f);
         }

         public Dist iterateUntil$mcI$sp(final Function1 pred, final Function1 f) {
            return Dist.super.iterateUntil$mcI$sp(pred, f);
         }

         public Dist iterateUntil$mcJ$sp(final Function1 pred, final Function1 f) {
            return Dist.super.iterateUntil$mcJ$sp(pred, f);
         }

         public Dist iterateUntil$mcS$sp(final Function1 pred, final Function1 f) {
            return Dist.super.iterateUntil$mcS$sp(pred, f);
         }

         public Dist iterateUntil$mcV$sp(final Function1 pred, final Function1 f) {
            return Dist.super.iterateUntil$mcV$sp(pred, f);
         }

         public Dist zip(final Dist that) {
            return Dist.super.zip(that);
         }

         public Dist zip$mcZ$sp(final Dist that) {
            return Dist.super.zip$mcZ$sp(that);
         }

         public Dist zip$mcB$sp(final Dist that) {
            return Dist.super.zip$mcB$sp(that);
         }

         public Dist zip$mcC$sp(final Dist that) {
            return Dist.super.zip$mcC$sp(that);
         }

         public Dist zip$mcD$sp(final Dist that) {
            return Dist.super.zip$mcD$sp(that);
         }

         public Dist zip$mcF$sp(final Dist that) {
            return Dist.super.zip$mcF$sp(that);
         }

         public Dist zip$mcI$sp(final Dist that) {
            return Dist.super.zip$mcI$sp(that);
         }

         public Dist zip$mcJ$sp(final Dist that) {
            return Dist.super.zip$mcJ$sp(that);
         }

         public Dist zip$mcS$sp(final Dist that) {
            return Dist.super.zip$mcS$sp(that);
         }

         public Dist zip$mcV$sp(final Dist that) {
            return Dist.super.zip$mcV$sp(that);
         }

         public Dist zipWith(final Dist that, final Function2 f) {
            return Dist.super.zipWith(that, f);
         }

         public Dist zipWith$mcZ$sp(final Dist that, final Function2 f) {
            return Dist.super.zipWith$mcZ$sp(that, f);
         }

         public Dist zipWith$mcB$sp(final Dist that, final Function2 f) {
            return Dist.super.zipWith$mcB$sp(that, f);
         }

         public Dist zipWith$mcC$sp(final Dist that, final Function2 f) {
            return Dist.super.zipWith$mcC$sp(that, f);
         }

         public Dist zipWith$mcD$sp(final Dist that, final Function2 f) {
            return Dist.super.zipWith$mcD$sp(that, f);
         }

         public Dist zipWith$mcF$sp(final Dist that, final Function2 f) {
            return Dist.super.zipWith$mcF$sp(that, f);
         }

         public Dist zipWith$mcI$sp(final Dist that, final Function2 f) {
            return Dist.super.zipWith$mcI$sp(that, f);
         }

         public Dist zipWith$mcJ$sp(final Dist that, final Function2 f) {
            return Dist.super.zipWith$mcJ$sp(that, f);
         }

         public Dist zipWith$mcS$sp(final Dist that, final Function2 f) {
            return Dist.super.zipWith$mcS$sp(that, f);
         }

         public Dist zipWith$mcV$sp(final Dist that, final Function2 f) {
            return Dist.super.zipWith$mcV$sp(that, f);
         }

         public final Iterator toIterator(final Generator gen) {
            return Dist.super.toIterator(gen);
         }

         public final LazyList toLazyList(final Generator gen) {
            return Dist.super.toLazyList(gen);
         }

         /** @deprecated */
         public final Stream toStream(final Generator gen) {
            return Dist.super.toStream(gen);
         }

         public Iterable sample(final int n, final Generator gen, final Factory cbf) {
            return Dist.super.sample(n, gen, cbf);
         }

         public int count(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.count(pred, n, gen);
         }

         public int count$mcZ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.count$mcZ$sp(pred, n, gen);
         }

         public int count$mcB$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.count$mcB$sp(pred, n, gen);
         }

         public int count$mcC$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.count$mcC$sp(pred, n, gen);
         }

         public int count$mcD$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.count$mcD$sp(pred, n, gen);
         }

         public int count$mcF$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.count$mcF$sp(pred, n, gen);
         }

         public int count$mcI$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.count$mcI$sp(pred, n, gen);
         }

         public int count$mcJ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.count$mcJ$sp(pred, n, gen);
         }

         public int count$mcS$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.count$mcS$sp(pred, n, gen);
         }

         public int count$mcV$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.count$mcV$sp(pred, n, gen);
         }

         public double pr(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.pr(pred, n, gen);
         }

         public double pr$mcZ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.pr$mcZ$sp(pred, n, gen);
         }

         public double pr$mcB$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.pr$mcB$sp(pred, n, gen);
         }

         public double pr$mcC$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.pr$mcC$sp(pred, n, gen);
         }

         public double pr$mcD$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.pr$mcD$sp(pred, n, gen);
         }

         public double pr$mcF$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.pr$mcF$sp(pred, n, gen);
         }

         public double pr$mcI$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.pr$mcI$sp(pred, n, gen);
         }

         public double pr$mcJ$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.pr$mcJ$sp(pred, n, gen);
         }

         public double pr$mcS$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.pr$mcS$sp(pred, n, gen);
         }

         public double pr$mcV$sp(final Function1 pred, final int n, final Generator gen) {
            return Dist.super.pr$mcV$sp(pred, n, gen);
         }

         public Object sum(final int n, final Generator gen, final Rig alg) {
            return Dist.super.sum(n, gen, alg);
         }

         public boolean sum$mcZ$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.super.sum$mcZ$sp(n, gen, alg);
         }

         public byte sum$mcB$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.super.sum$mcB$sp(n, gen, alg);
         }

         public char sum$mcC$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.super.sum$mcC$sp(n, gen, alg);
         }

         public double sum$mcD$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.super.sum$mcD$sp(n, gen, alg);
         }

         public float sum$mcF$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.super.sum$mcF$sp(n, gen, alg);
         }

         public int sum$mcI$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.super.sum$mcI$sp(n, gen, alg);
         }

         public long sum$mcJ$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.super.sum$mcJ$sp(n, gen, alg);
         }

         public short sum$mcS$sp(final int n, final Generator gen, final Rig alg) {
            return Dist.super.sum$mcS$sp(n, gen, alg);
         }

         public void sum$mcV$sp(final int n, final Generator gen, final Rig alg) {
            Dist.super.sum$mcV$sp(n, gen, alg);
         }

         public Object ev(final int n, final Generator gen, final Field alg) {
            return Dist.super.ev(n, gen, alg);
         }

         public boolean ev$mcZ$sp(final int n, final Generator gen, final Field alg) {
            return Dist.super.ev$mcZ$sp(n, gen, alg);
         }

         public byte ev$mcB$sp(final int n, final Generator gen, final Field alg) {
            return Dist.super.ev$mcB$sp(n, gen, alg);
         }

         public char ev$mcC$sp(final int n, final Generator gen, final Field alg) {
            return Dist.super.ev$mcC$sp(n, gen, alg);
         }

         public double ev$mcD$sp(final int n, final Generator gen, final Field alg) {
            return Dist.super.ev$mcD$sp(n, gen, alg);
         }

         public float ev$mcF$sp(final int n, final Generator gen, final Field alg) {
            return Dist.super.ev$mcF$sp(n, gen, alg);
         }

         public int ev$mcI$sp(final int n, final Generator gen, final Field alg) {
            return Dist.super.ev$mcI$sp(n, gen, alg);
         }

         public long ev$mcJ$sp(final int n, final Generator gen, final Field alg) {
            return Dist.super.ev$mcJ$sp(n, gen, alg);
         }

         public short ev$mcS$sp(final int n, final Generator gen, final Field alg) {
            return Dist.super.ev$mcS$sp(n, gen, alg);
         }

         public void ev$mcV$sp(final int n, final Generator gen, final Field alg) {
            Dist.super.ev$mcV$sp(n, gen, alg);
         }

         public Map histogram(final int n, final Generator gen) {
            return Dist.super.histogram(n, gen);
         }

         public Map rawHistogram(final int n, final Generator gen) {
            return Dist.super.rawHistogram(n, gen);
         }

         private Object loop(final Generator gen, final Object a) {
            while(!BoxesRunTime.unboxToBoolean(this.pred$4.apply(a))) {
               a = ((Dist)this.f$5.apply(a)).apply(gen);
               gen = gen;
            }

            return a;
         }

         public Object apply(final Generator gen) {
            return this.loop(gen, this.$outer.apply(gen));
         }

         public {
            if (Dist.this == null) {
               throw null;
            } else {
               this.$outer = Dist.this;
               this.pred$4 = pred$4;
               this.f$5 = f$5;
               Dist.$init$(this);
            }
         }
      };
   }

   default Dist zip(final Dist that) {
      return new DistFromGen((g) -> new Tuple2(this.apply(g), that.apply(g)));
   }

   default Dist zipWith(final Dist that, final Function2 f) {
      return new DistFromGen((g) -> f.apply(this.apply(g), that.apply(g)));
   }

   default Iterator toIterator(final Generator gen) {
      return new DistIterator(this, gen);
   }

   default LazyList toLazyList(final Generator gen) {
      return scala.collection.immutable.LazyList..MODULE$.continually(() -> this.apply(gen));
   }

   /** @deprecated */
   default Stream toStream(final Generator gen) {
      return scala.package..MODULE$.Stream().continually(() -> this.apply(gen));
   }

   default Iterable sample(final int n, final Generator gen, final Factory cbf) {
      Builder b = cbf.newBuilder();
      b.sizeHint(n);

      for(int i = 0; i < n; ++i) {
         b.$plus$eq(this.apply(gen));
      }

      return (Iterable)b.result();
   }

   default int count(final Function1 pred, final int n, final Generator gen) {
      return this.loop$5(0, n, pred, gen);
   }

   default double pr(final Function1 pred, final int n, final Generator gen) {
      return (double)1.0F * (double)this.count(pred, n, gen) / (double)n;
   }

   default Object sum(final int n, final Generator gen, final Rig alg) {
      return this.loop$6(alg.zero(), n, alg, gen);
   }

   default Object ev(final int n, final Generator gen, final Field alg) {
      return alg.div(this.sum(n, gen, alg), alg.fromInt(n));
   }

   default Map histogram(final int n, final Generator gen) {
      return (Map)this.rawHistogram(n, gen).map((x0$1) -> {
         if (x0$1 != null) {
            Object k = x0$1._1();
            int v = x0$1._2$mcI$sp();
            Tuple2 var2 = new Tuple2(k, BoxesRunTime.boxToDouble((double)1.0F * (double)v / (double)n));
            return var2;
         } else {
            throw new MatchError(x0$1);
         }
      });
   }

   default Map rawHistogram(final int n, final Generator gen) {
      return (Map)this.toLazyList(gen).take(n).foldLeft(scala.Predef..MODULE$.Map().empty(), (x0$1, x1$1) -> {
         Tuple2 var3 = new Tuple2(x0$1, x1$1);
         if (var3 != null) {
            Map h = (Map)var3._1();
            Object a = var3._2();
            Map var2 = (Map)h.updated(a, BoxesRunTime.boxToInteger(BoxesRunTime.unboxToInt(h.getOrElse(a, (JFunction0.mcI.sp)() -> 0)) + 1));
            return var2;
         } else {
            throw new MatchError(var3);
         }
      });
   }

   default boolean apply$mcZ$sp(final Generator gen) {
      return BoxesRunTime.unboxToBoolean(this.apply(gen));
   }

   default byte apply$mcB$sp(final Generator gen) {
      return BoxesRunTime.unboxToByte(this.apply(gen));
   }

   default char apply$mcC$sp(final Generator gen) {
      return BoxesRunTime.unboxToChar(this.apply(gen));
   }

   default double apply$mcD$sp(final Generator gen) {
      return BoxesRunTime.unboxToDouble(this.apply(gen));
   }

   default float apply$mcF$sp(final Generator gen) {
      return BoxesRunTime.unboxToFloat(this.apply(gen));
   }

   default int apply$mcI$sp(final Generator gen) {
      return BoxesRunTime.unboxToInt(this.apply(gen));
   }

   default long apply$mcJ$sp(final Generator gen) {
      return BoxesRunTime.unboxToLong(this.apply(gen));
   }

   default short apply$mcS$sp(final Generator gen) {
      return BoxesRunTime.unboxToShort(this.apply(gen));
   }

   default void apply$mcV$sp(final Generator gen) {
      this.apply(gen);
   }

   default void fill$mcZ$sp(final Generator gen, final boolean[] arr) {
      this.fill(gen, arr);
   }

   default void fill$mcB$sp(final Generator gen, final byte[] arr) {
      this.fill(gen, arr);
   }

   default void fill$mcC$sp(final Generator gen, final char[] arr) {
      this.fill(gen, arr);
   }

   default void fill$mcD$sp(final Generator gen, final double[] arr) {
      this.fill(gen, arr);
   }

   default void fill$mcF$sp(final Generator gen, final float[] arr) {
      this.fill(gen, arr);
   }

   default void fill$mcI$sp(final Generator gen, final int[] arr) {
      this.fill(gen, arr);
   }

   default void fill$mcJ$sp(final Generator gen, final long[] arr) {
      this.fill(gen, arr);
   }

   default void fill$mcS$sp(final Generator gen, final short[] arr) {
      this.fill(gen, arr);
   }

   default void fill$mcV$sp(final Generator gen, final BoxedUnit[] arr) {
      this.fill(gen, arr);
   }

   default Dist map$mcZ$sp(final Function1 f) {
      return this.map(f);
   }

   default Dist map$mcB$sp(final Function1 f) {
      return this.map(f);
   }

   default Dist map$mcC$sp(final Function1 f) {
      return this.map(f);
   }

   default Dist map$mcD$sp(final Function1 f) {
      return this.map(f);
   }

   default Dist map$mcF$sp(final Function1 f) {
      return this.map(f);
   }

   default Dist map$mcI$sp(final Function1 f) {
      return this.map(f);
   }

   default Dist map$mcJ$sp(final Function1 f) {
      return this.map(f);
   }

   default Dist map$mcS$sp(final Function1 f) {
      return this.map(f);
   }

   default Dist map$mcV$sp(final Function1 f) {
      return this.map(f);
   }

   default Dist flatMap$mcZ$sp(final Function1 f) {
      return this.flatMap(f);
   }

   default Dist flatMap$mcB$sp(final Function1 f) {
      return this.flatMap(f);
   }

   default Dist flatMap$mcC$sp(final Function1 f) {
      return this.flatMap(f);
   }

   default Dist flatMap$mcD$sp(final Function1 f) {
      return this.flatMap(f);
   }

   default Dist flatMap$mcF$sp(final Function1 f) {
      return this.flatMap(f);
   }

   default Dist flatMap$mcI$sp(final Function1 f) {
      return this.flatMap(f);
   }

   default Dist flatMap$mcJ$sp(final Function1 f) {
      return this.flatMap(f);
   }

   default Dist flatMap$mcS$sp(final Function1 f) {
      return this.flatMap(f);
   }

   default Dist flatMap$mcV$sp(final Function1 f) {
      return this.flatMap(f);
   }

   default Dist filter$mcZ$sp(final Function1 pred) {
      return this.filter(pred);
   }

   default Dist filter$mcB$sp(final Function1 pred) {
      return this.filter(pred);
   }

   default Dist filter$mcC$sp(final Function1 pred) {
      return this.filter(pred);
   }

   default Dist filter$mcD$sp(final Function1 pred) {
      return this.filter(pred);
   }

   default Dist filter$mcF$sp(final Function1 pred) {
      return this.filter(pred);
   }

   default Dist filter$mcI$sp(final Function1 pred) {
      return this.filter(pred);
   }

   default Dist filter$mcJ$sp(final Function1 pred) {
      return this.filter(pred);
   }

   default Dist filter$mcS$sp(final Function1 pred) {
      return this.filter(pred);
   }

   default Dist filter$mcV$sp(final Function1 pred) {
      return this.filter(pred);
   }

   default Dist given$mcZ$sp(final Function1 pred) {
      return this.given(pred);
   }

   default Dist given$mcB$sp(final Function1 pred) {
      return this.given(pred);
   }

   default Dist given$mcC$sp(final Function1 pred) {
      return this.given(pred);
   }

   default Dist given$mcD$sp(final Function1 pred) {
      return this.given(pred);
   }

   default Dist given$mcF$sp(final Function1 pred) {
      return this.given(pred);
   }

   default Dist given$mcI$sp(final Function1 pred) {
      return this.given(pred);
   }

   default Dist given$mcJ$sp(final Function1 pred) {
      return this.given(pred);
   }

   default Dist given$mcS$sp(final Function1 pred) {
      return this.given(pred);
   }

   default Dist given$mcV$sp(final Function1 pred) {
      return this.given(pred);
   }

   default Dist until$mcZ$sp(final Function1 pred) {
      return this.until(pred);
   }

   default Dist until$mcB$sp(final Function1 pred) {
      return this.until(pred);
   }

   default Dist until$mcC$sp(final Function1 pred) {
      return this.until(pred);
   }

   default Dist until$mcD$sp(final Function1 pred) {
      return this.until(pred);
   }

   default Dist until$mcF$sp(final Function1 pred) {
      return this.until(pred);
   }

   default Dist until$mcI$sp(final Function1 pred) {
      return this.until(pred);
   }

   default Dist until$mcJ$sp(final Function1 pred) {
      return this.until(pred);
   }

   default Dist until$mcS$sp(final Function1 pred) {
      return this.until(pred);
   }

   default Dist until$mcV$sp(final Function1 pred) {
      return this.until(pred);
   }

   default Dist foldn$mcZ$sp(final Object init, final int n, final Function2 f) {
      return this.foldn(init, n, f);
   }

   default Dist foldn$mcB$sp(final Object init, final int n, final Function2 f) {
      return this.foldn(init, n, f);
   }

   default Dist foldn$mcC$sp(final Object init, final int n, final Function2 f) {
      return this.foldn(init, n, f);
   }

   default Dist foldn$mcD$sp(final Object init, final int n, final Function2 f) {
      return this.foldn(init, n, f);
   }

   default Dist foldn$mcF$sp(final Object init, final int n, final Function2 f) {
      return this.foldn(init, n, f);
   }

   default Dist foldn$mcI$sp(final Object init, final int n, final Function2 f) {
      return this.foldn(init, n, f);
   }

   default Dist foldn$mcJ$sp(final Object init, final int n, final Function2 f) {
      return this.foldn(init, n, f);
   }

   default Dist foldn$mcS$sp(final Object init, final int n, final Function2 f) {
      return this.foldn(init, n, f);
   }

   default Dist foldn$mcV$sp(final Object init, final int n, final Function2 f) {
      return this.foldn(init, n, f);
   }

   default Dist unfold$mcZ$sp(final Object init, final Function2 f, final Function1 pred) {
      return this.unfold(init, f, pred);
   }

   default Dist unfold$mcB$sp(final Object init, final Function2 f, final Function1 pred) {
      return this.unfold(init, f, pred);
   }

   default Dist unfold$mcC$sp(final Object init, final Function2 f, final Function1 pred) {
      return this.unfold(init, f, pred);
   }

   default Dist unfold$mcD$sp(final Object init, final Function2 f, final Function1 pred) {
      return this.unfold(init, f, pred);
   }

   default Dist unfold$mcF$sp(final Object init, final Function2 f, final Function1 pred) {
      return this.unfold(init, f, pred);
   }

   default Dist unfold$mcI$sp(final Object init, final Function2 f, final Function1 pred) {
      return this.unfold(init, f, pred);
   }

   default Dist unfold$mcJ$sp(final Object init, final Function2 f, final Function1 pred) {
      return this.unfold(init, f, pred);
   }

   default Dist unfold$mcS$sp(final Object init, final Function2 f, final Function1 pred) {
      return this.unfold(init, f, pred);
   }

   default Dist unfold$mcV$sp(final Object init, final Function2 f, final Function1 pred) {
      return this.unfold(init, f, pred);
   }

   default Dist pack$mcZ$sp(final int n, final ClassTag ct) {
      return this.pack(n, ct);
   }

   default Dist pack$mcB$sp(final int n, final ClassTag ct) {
      return this.pack(n, ct);
   }

   default Dist pack$mcC$sp(final int n, final ClassTag ct) {
      return this.pack(n, ct);
   }

   default Dist pack$mcD$sp(final int n, final ClassTag ct) {
      return this.pack(n, ct);
   }

   default Dist pack$mcF$sp(final int n, final ClassTag ct) {
      return this.pack(n, ct);
   }

   default Dist pack$mcI$sp(final int n, final ClassTag ct) {
      return this.pack(n, ct);
   }

   default Dist pack$mcJ$sp(final int n, final ClassTag ct) {
      return this.pack(n, ct);
   }

   default Dist pack$mcS$sp(final int n, final ClassTag ct) {
      return this.pack(n, ct);
   }

   default Dist pack$mcV$sp(final int n, final ClassTag ct) {
      return this.pack(n, ct);
   }

   default Dist iterate$mcZ$sp(final int n, final Function1 f) {
      return this.iterate(n, f);
   }

   default Dist iterate$mcB$sp(final int n, final Function1 f) {
      return this.iterate(n, f);
   }

   default Dist iterate$mcC$sp(final int n, final Function1 f) {
      return this.iterate(n, f);
   }

   default Dist iterate$mcD$sp(final int n, final Function1 f) {
      return this.iterate(n, f);
   }

   default Dist iterate$mcF$sp(final int n, final Function1 f) {
      return this.iterate(n, f);
   }

   default Dist iterate$mcI$sp(final int n, final Function1 f) {
      return this.iterate(n, f);
   }

   default Dist iterate$mcJ$sp(final int n, final Function1 f) {
      return this.iterate(n, f);
   }

   default Dist iterate$mcS$sp(final int n, final Function1 f) {
      return this.iterate(n, f);
   }

   default Dist iterate$mcV$sp(final int n, final Function1 f) {
      return this.iterate(n, f);
   }

   default Dist iterateUntil$mcZ$sp(final Function1 pred, final Function1 f) {
      return this.iterateUntil(pred, f);
   }

   default Dist iterateUntil$mcB$sp(final Function1 pred, final Function1 f) {
      return this.iterateUntil(pred, f);
   }

   default Dist iterateUntil$mcC$sp(final Function1 pred, final Function1 f) {
      return this.iterateUntil(pred, f);
   }

   default Dist iterateUntil$mcD$sp(final Function1 pred, final Function1 f) {
      return this.iterateUntil(pred, f);
   }

   default Dist iterateUntil$mcF$sp(final Function1 pred, final Function1 f) {
      return this.iterateUntil(pred, f);
   }

   default Dist iterateUntil$mcI$sp(final Function1 pred, final Function1 f) {
      return this.iterateUntil(pred, f);
   }

   default Dist iterateUntil$mcJ$sp(final Function1 pred, final Function1 f) {
      return this.iterateUntil(pred, f);
   }

   default Dist iterateUntil$mcS$sp(final Function1 pred, final Function1 f) {
      return this.iterateUntil(pred, f);
   }

   default Dist iterateUntil$mcV$sp(final Function1 pred, final Function1 f) {
      return this.iterateUntil(pred, f);
   }

   default Dist zip$mcZ$sp(final Dist that) {
      return this.zip(that);
   }

   default Dist zip$mcB$sp(final Dist that) {
      return this.zip(that);
   }

   default Dist zip$mcC$sp(final Dist that) {
      return this.zip(that);
   }

   default Dist zip$mcD$sp(final Dist that) {
      return this.zip(that);
   }

   default Dist zip$mcF$sp(final Dist that) {
      return this.zip(that);
   }

   default Dist zip$mcI$sp(final Dist that) {
      return this.zip(that);
   }

   default Dist zip$mcJ$sp(final Dist that) {
      return this.zip(that);
   }

   default Dist zip$mcS$sp(final Dist that) {
      return this.zip(that);
   }

   default Dist zip$mcV$sp(final Dist that) {
      return this.zip(that);
   }

   default Dist zipWith$mcZ$sp(final Dist that, final Function2 f) {
      return this.zipWith(that, f);
   }

   default Dist zipWith$mcB$sp(final Dist that, final Function2 f) {
      return this.zipWith(that, f);
   }

   default Dist zipWith$mcC$sp(final Dist that, final Function2 f) {
      return this.zipWith(that, f);
   }

   default Dist zipWith$mcD$sp(final Dist that, final Function2 f) {
      return this.zipWith(that, f);
   }

   default Dist zipWith$mcF$sp(final Dist that, final Function2 f) {
      return this.zipWith(that, f);
   }

   default Dist zipWith$mcI$sp(final Dist that, final Function2 f) {
      return this.zipWith(that, f);
   }

   default Dist zipWith$mcJ$sp(final Dist that, final Function2 f) {
      return this.zipWith(that, f);
   }

   default Dist zipWith$mcS$sp(final Dist that, final Function2 f) {
      return this.zipWith(that, f);
   }

   default Dist zipWith$mcV$sp(final Dist that, final Function2 f) {
      return this.zipWith(that, f);
   }

   default int count$mcZ$sp(final Function1 pred, final int n, final Generator gen) {
      return this.count(pred, n, gen);
   }

   default int count$mcB$sp(final Function1 pred, final int n, final Generator gen) {
      return this.count(pred, n, gen);
   }

   default int count$mcC$sp(final Function1 pred, final int n, final Generator gen) {
      return this.count(pred, n, gen);
   }

   default int count$mcD$sp(final Function1 pred, final int n, final Generator gen) {
      return this.count(pred, n, gen);
   }

   default int count$mcF$sp(final Function1 pred, final int n, final Generator gen) {
      return this.count(pred, n, gen);
   }

   default int count$mcI$sp(final Function1 pred, final int n, final Generator gen) {
      return this.count(pred, n, gen);
   }

   default int count$mcJ$sp(final Function1 pred, final int n, final Generator gen) {
      return this.count(pred, n, gen);
   }

   default int count$mcS$sp(final Function1 pred, final int n, final Generator gen) {
      return this.count(pred, n, gen);
   }

   default int count$mcV$sp(final Function1 pred, final int n, final Generator gen) {
      return this.count(pred, n, gen);
   }

   default double pr$mcZ$sp(final Function1 pred, final int n, final Generator gen) {
      return this.pr(pred, n, gen);
   }

   default double pr$mcB$sp(final Function1 pred, final int n, final Generator gen) {
      return this.pr(pred, n, gen);
   }

   default double pr$mcC$sp(final Function1 pred, final int n, final Generator gen) {
      return this.pr(pred, n, gen);
   }

   default double pr$mcD$sp(final Function1 pred, final int n, final Generator gen) {
      return this.pr(pred, n, gen);
   }

   default double pr$mcF$sp(final Function1 pred, final int n, final Generator gen) {
      return this.pr(pred, n, gen);
   }

   default double pr$mcI$sp(final Function1 pred, final int n, final Generator gen) {
      return this.pr(pred, n, gen);
   }

   default double pr$mcJ$sp(final Function1 pred, final int n, final Generator gen) {
      return this.pr(pred, n, gen);
   }

   default double pr$mcS$sp(final Function1 pred, final int n, final Generator gen) {
      return this.pr(pred, n, gen);
   }

   default double pr$mcV$sp(final Function1 pred, final int n, final Generator gen) {
      return this.pr(pred, n, gen);
   }

   default boolean sum$mcZ$sp(final int n, final Generator gen, final Rig alg) {
      return BoxesRunTime.unboxToBoolean(this.sum(n, gen, alg));
   }

   default byte sum$mcB$sp(final int n, final Generator gen, final Rig alg) {
      return BoxesRunTime.unboxToByte(this.sum(n, gen, alg));
   }

   default char sum$mcC$sp(final int n, final Generator gen, final Rig alg) {
      return BoxesRunTime.unboxToChar(this.sum(n, gen, alg));
   }

   default double sum$mcD$sp(final int n, final Generator gen, final Rig alg) {
      return BoxesRunTime.unboxToDouble(this.sum(n, gen, alg));
   }

   default float sum$mcF$sp(final int n, final Generator gen, final Rig alg) {
      return BoxesRunTime.unboxToFloat(this.sum(n, gen, alg));
   }

   default int sum$mcI$sp(final int n, final Generator gen, final Rig alg) {
      return BoxesRunTime.unboxToInt(this.sum(n, gen, alg));
   }

   default long sum$mcJ$sp(final int n, final Generator gen, final Rig alg) {
      return BoxesRunTime.unboxToLong(this.sum(n, gen, alg));
   }

   default short sum$mcS$sp(final int n, final Generator gen, final Rig alg) {
      return BoxesRunTime.unboxToShort(this.sum(n, gen, alg));
   }

   default void sum$mcV$sp(final int n, final Generator gen, final Rig alg) {
      this.sum(n, gen, alg);
   }

   default boolean ev$mcZ$sp(final int n, final Generator gen, final Field alg) {
      return BoxesRunTime.unboxToBoolean(this.ev(n, gen, alg));
   }

   default byte ev$mcB$sp(final int n, final Generator gen, final Field alg) {
      return BoxesRunTime.unboxToByte(this.ev(n, gen, alg));
   }

   default char ev$mcC$sp(final int n, final Generator gen, final Field alg) {
      return BoxesRunTime.unboxToChar(this.ev(n, gen, alg));
   }

   default double ev$mcD$sp(final int n, final Generator gen, final Field alg) {
      return BoxesRunTime.unboxToDouble(this.ev(n, gen, alg));
   }

   default float ev$mcF$sp(final int n, final Generator gen, final Field alg) {
      return BoxesRunTime.unboxToFloat(this.ev(n, gen, alg));
   }

   default int ev$mcI$sp(final int n, final Generator gen, final Field alg) {
      return BoxesRunTime.unboxToInt(this.ev(n, gen, alg));
   }

   default long ev$mcJ$sp(final int n, final Generator gen, final Field alg) {
      return BoxesRunTime.unboxToLong(this.ev(n, gen, alg));
   }

   default short ev$mcS$sp(final int n, final Generator gen, final Field alg) {
      return BoxesRunTime.unboxToShort(this.ev(n, gen, alg));
   }

   default void ev$mcV$sp(final int n, final Generator gen, final Field alg) {
      this.ev(n, gen, alg);
   }

   private Seq loop$2(final Generator gen, final Object a, final ArrayBuffer buf, final Function1 pred$2) {
      while(true) {
         buf.append(a);
         if (BoxesRunTime.unboxToBoolean(pred$2.apply(a))) {
            return buf.toSeq();
         }

         Object var10001 = this.apply(gen);
         buf = buf;
         a = var10001;
         gen = gen;
      }
   }

   private Object loop$3(final Generator gen, final int i, final Object b, final Function2 f$3) {
      while(i != 0) {
         int var10001 = i - 1;
         b = f$3.apply(b, this.apply(gen));
         i = var10001;
         gen = gen;
      }

      return b;
   }

   private Object loop$4(final Generator gen, final Object b, final Function1 pred$3, final Function2 f$4) {
      while(!BoxesRunTime.unboxToBoolean(pred$3.apply(b))) {
         b = f$4.apply(b, this.apply(gen));
         gen = gen;
      }

      return b;
   }

   private int loop$5(final int num, final int i, final Function1 pred$5, final Generator gen$4) {
      while(i != 0) {
         int var10000 = num + (BoxesRunTime.unboxToBoolean(pred$5.apply(this.apply(gen$4))) ? 1 : 0);
         --i;
         num = var10000;
      }

      return num;
   }

   private Object loop$6(final Object total, final int i, final Rig alg$1, final Generator gen$5) {
      while(i != 0) {
         Object var10000 = alg$1.plus(total, this.apply(gen$5));
         --i;
         total = var10000;
      }

      return total;
   }

   static void $init$(final Dist $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
