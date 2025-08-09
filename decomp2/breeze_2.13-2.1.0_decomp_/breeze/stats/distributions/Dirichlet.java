package breeze.stats.distributions;

import breeze.generic.UFunc;
import breeze.linalg.DenseVector;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.NumericOps;
import breeze.linalg.QuasiTensor;
import breeze.linalg.max$;
import breeze.linalg.normalize$;
import breeze.linalg.softmax$;
import breeze.linalg.sum$;
import breeze.linalg.operators.HasOps$;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.CanTranspose;
import breeze.linalg.support.CanTraverseValues$;
import breeze.math.EnumeratedCoordinateField;
import breeze.math.MutableFiniteCoordinateField;
import breeze.numerics.package;
import breeze.numerics.package$digamma$digammaImplDouble$;
import breeze.numerics.package$log$logDoubleImpl$;
import breeze.optimize.DiffFunction;
import breeze.optimize.OptimizationPackage$;
import breeze.optimize.StochasticDiffFunction;
import breeze.optimize.package$;
import breeze.util.Isomorphism;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\rEc\u0001B\"E\u0001.C\u0001\"\u001d\u0001\u0003\u0016\u0004%\tA\u001d\u0005\tg\u0002\u0011\t\u0012)A\u0005/\"AA\u000f\u0001B\u0001B\u0003-Q\u000f\u0003\u0006\u0002\u0016\u0001\u0011\t\u0011)A\u0006\u0003/Aq!!\b\u0001\t\u0003\ty\u0002C\u0004\u0002,\u0001!\t!!\f\t\u000f\u0005=\u0002\u0001\"\u0001\u0002.!9\u0011\u0011\u0007\u0001\u0005\u0002\u00055\u0002bBA\u001a\u0001\u0011\u0005\u0013Q\u0007\u0005\u000b\u0003w\u0001\u0001R1A\u0005\u0002\u0005u\u0002\"CA \u0001\u0005\u0005I\u0011AA!\u0011%\ty\u0006AI\u0001\n\u0003\t\t\u0007C\u0005\u0002\u0004\u0002\t\t\u0011\"\u0011\u0002\u0006\"I\u0011q\u0013\u0001\u0002\u0002\u0013\u0005\u0011\u0011\u0014\u0005\n\u0003C\u0003\u0011\u0011!C\u0001\u0003GC\u0011\"!+\u0001\u0003\u0003%\t%a+\t\u0013\u0005e\u0006!!A\u0005\u0002\u0005m\u0006\"CAc\u0001\u0005\u0005I\u0011IAd\u0011%\tY\rAA\u0001\n\u0003\ni\rC\u0005\u0002P\u0002\t\t\u0011\"\u0011\u0002R\"I\u00111\u001b\u0001\u0002\u0002\u0013\u0005\u0013Q[\u0004\b\u00033$\u0005\u0012AAn\r\u0019\u0019E\t#\u0001\u0002^\"9\u0011QD\f\u0005\u0002\u0005%\bbBAv/\u0011\u0005\u0011Q\u001e\u0005\b\u0005\u00179B\u0011\u0001B\u0007\u0011\u001d\tYo\u0006C\u0001\u0005G1aAa\r\u0018\u0001\tU\u0002B\u0003B%9\t\u0005\t\u0015!\u0003\u0003B!IA\u000f\bB\u0001B\u0003-!1\n\u0005\b\u0003;aB\u0011\u0001B)\u000b\u0019\u0011i\u0006\b\u0001\u0003B\u00191!q\f\u000fA\u0005CB!Ba\u001b\"\u0005+\u0007I\u0011AA\u001f\u0011)\u0011i'\tB\tB\u0003%\u0011q\u0002\u0005\u000b\u0005_\n#Q3A\u0005\u0002\tE\u0004B\u0003B:C\tE\t\u0015!\u0003\u0003B!9\u0011QD\u0011\u0005\u0002\tU\u0004b\u0002B>C\u0011\u0005!Q\u0010\u0005\b\u0005\u0007\u000bC\u0011\u0001BC\u0011%\ty$IA\u0001\n\u0003\u0011Y\tC\u0005\u0002`\u0005\n\n\u0011\"\u0001\u0003\u0012\"I!QS\u0011\u0012\u0002\u0013\u0005!q\u0013\u0005\n\u0003\u0007\u000b\u0013\u0011!C!\u0003\u000bC\u0011\"a&\"\u0003\u0003%\t!!'\t\u0013\u0005\u0005\u0016%!A\u0005\u0002\tm\u0005\"CAUC\u0005\u0005I\u0011IAV\u0011%\tI,IA\u0001\n\u0003\u0011y\nC\u0005\u0002F\u0006\n\t\u0011\"\u0011\u0003$\"I\u00111Z\u0011\u0002\u0002\u0013\u0005\u0013Q\u001a\u0005\n\u0003\u001f\f\u0013\u0011!C!\u0003#D\u0011\"a5\"\u0003\u0003%\tEa*\b\u0013\t-F$!A\t\u0002\t5f!\u0003B09\u0005\u0005\t\u0012\u0001BX\u0011\u001d\tiB\u000eC\u0001\u0005{C\u0011\"a47\u0003\u0003%)%!5\t\u0013\u0005-h'!A\u0005\u0002\n}\u0006\"\u0003Bcm\u0005\u0005I\u0011\u0011Bd\u0011\u001d\u0011I\u000e\bC\u0001\u00057DqA!8\u001d\t\u0003\u0011y\u000eC\u0004\u0003dr!\tA!:\t\u000f\t%H\u0004\"\u0001\u0003l\"91\u0011\u0001\u000f\u0005B\r\r\u0001\"CAv/\u0005\u0005I\u0011QB\t\u0011%\u0011)mFA\u0001\n\u0003\u001by\u0003C\u0005\u0004H]\t\t\u0011\"\u0003\u0004J\tIA)\u001b:jG\"dW\r\u001e\u0006\u0003\u000b\u001a\u000bQ\u0002Z5tiJL'-\u001e;j_:\u001c(BA$I\u0003\u0015\u0019H/\u0019;t\u0015\u0005I\u0015A\u00022sK\u0016TXm\u0001\u0001\u0016\u00071KFpE\u0003\u0001\u001bN\u0013W\r\u0005\u0002O#6\tqJC\u0001Q\u0003\u0015\u00198-\u00197b\u0013\t\u0011vJ\u0001\u0004B]f\u0014VM\u001a\t\u0004)V;V\"\u0001#\n\u0005Y#%aD\"p]RLg.^8vg\u0012K7\u000f\u001e:\u0011\u0005aKF\u0002\u0001\u0003\u00065\u0002\u0011\ra\u0017\u0002\u0002)F\u0011Al\u0018\t\u0003\u001dvK!AX(\u0003\u000f9{G\u000f[5oOB\u0011a\nY\u0005\u0003C>\u00131!\u00118z!\tq5-\u0003\u0002e\u001f\n9\u0001K]8ek\u000e$\bC\u00014o\u001d\t9GN\u0004\u0002iW6\t\u0011N\u0003\u0002k\u0015\u00061AH]8pizJ\u0011\u0001U\u0005\u0003[>\u000bq\u0001]1dW\u0006<W-\u0003\u0002pa\na1+\u001a:jC2L'0\u00192mK*\u0011QnT\u0001\u0007a\u0006\u0014\u0018-\\:\u0016\u0003]\u000bq\u0001]1sC6\u001c\b%A\u0003ta\u0006\u001cW\r\u0005\u0004ws^[\u0018qB\u0007\u0002o*\u0011\u0001\u0010S\u0001\u0005[\u0006$\b.\u0003\u0002{o\nIRI\\;nKJ\fG/\u001a3D_>\u0014H-\u001b8bi\u00164\u0015.\u001a7e!\tAF\u0010B\u0005~\u0001\u0001\u0006\t\u0011!b\u00017\n\t\u0011\n\u000b\u0003}\u007f\u0006\u0015\u0001c\u0001(\u0002\u0002%\u0019\u00111A(\u0003\u0017M\u0004XmY5bY&TX\rZ\u0019\nG\u0005\u001d\u0011\u0011BA\u0007\u0003\u0017q1ATA\u0005\u0013\r\tYaT\u0001\u0004\u0013:$\u0018\u0007\u0002\u0013hWB\u00032ATA\t\u0013\r\t\u0019b\u0014\u0002\u0007\t>,(\r\\3\u0002\tI\fg\u000e\u001a\t\u0004)\u0006e\u0011bAA\u000e\t\nI!+\u00198e\u0005\u0006\u001c\u0018n]\u0001\u0007y%t\u0017\u000e\u001e \u0015\t\u0005\u0005\u0012\u0011\u0006\u000b\u0007\u0003G\t)#a\n\u0011\tQ\u0003qk\u001f\u0005\u0006i\u0016\u0001\u001d!\u001e\u0005\b\u0003+)\u00019AA\f\u0011\u0015\tX\u00011\u0001X\u0003\u0011!'/Y<\u0015\u0003]\u000b\u0001#\u001e8o_Jl\u0017\r\\5{K\u0012$%/Y<\u0002\u000f1|w\r\u0012:bo\u0006\u0011RO\u001c8pe6\fG.\u001b>fI2{w\r\u00153g)\u0011\ty!a\u000e\t\r\u0005e\u0012\u00021\u0001X\u0003\u0005i\u0017!\u00047pO:{'/\\1mSj,'/\u0006\u0002\u0002\u0010\u0005!1m\u001c9z+\u0019\t\u0019%a\u0013\u0002PQ!\u0011QIA/)\u0019\t9%a\u0016\u0002\\A1A\u000bAA%\u0003\u001b\u00022\u0001WA&\t\u0015Q6B1\u0001\\!\rA\u0016q\n\u0003\n{.\u0001\u000b\u0011!AC\u0002mCS!a\u0014\u0000\u0003'\n\u0014bIA\u0004\u0003\u0013\t)&a\u00032\t\u0011:7\u000e\u0015\u0005\u0007i.\u0001\u001d!!\u0017\u0011\u0011YL\u0018\u0011JA'\u0003\u001fAq!!\u0006\f\u0001\b\t9\u0002\u0003\u0005r\u0017A\u0005\t\u0019AA%\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*b!a\u0019\u0002z\u0005mTCAA3U\r9\u0016qM\u0016\u0003\u0003S\u0002B!a\u001b\u0002v5\u0011\u0011Q\u000e\u0006\u0005\u0003_\n\t(A\u0005v]\u000eDWmY6fI*\u0019\u00111O(\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002x\u00055$!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0012)!\f\u0004b\u00017\u0012IQ\u0010\u0004Q\u0001\u0002\u0003\u0015\ra\u0017\u0015\u0006\u0003wz\u0018qP\u0019\nG\u0005\u001d\u0011\u0011BAA\u0003\u0017\tD\u0001J4l!\u0006i\u0001O]8ek\u000e$\bK]3gSb,\"!a\"\u0011\t\u0005%\u00151S\u0007\u0003\u0003\u0017SA!!$\u0002\u0010\u0006!A.\u00198h\u0015\t\t\t*\u0001\u0003kCZ\f\u0017\u0002BAK\u0003\u0017\u0013aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLXCAAN!\rq\u0015QT\u0005\u0004\u0003?{%aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HcA0\u0002&\"I\u0011qU\b\u0002\u0002\u0003\u0007\u00111T\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u00055\u0006#BAX\u0003k{VBAAY\u0015\r\t\u0019lT\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA\\\u0003c\u0013\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u0011QXAb!\rq\u0015qX\u0005\u0004\u0003\u0003|%a\u0002\"p_2,\u0017M\u001c\u0005\t\u0003O\u000b\u0012\u0011!a\u0001?\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\t9)!3\t\u0013\u0005\u001d&#!AA\u0002\u0005m\u0015\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\u0005m\u0015\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005\u001d\u0015AB3rk\u0006d7\u000f\u0006\u0003\u0002>\u0006]\u0007\u0002CAT+\u0005\u0005\t\u0019A0\u0002\u0013\u0011K'/[2iY\u0016$\bC\u0001+\u0018'\u00119R*a8\u0011\t\u0005\u0005\u0018q]\u0007\u0003\u0003GTA!!:\u0002\u0010\u0006\u0011\u0011n\\\u0005\u0004_\u0006\rHCAAn\u0003\u0015\t\u0007\u000f\u001d7z+\u0011\tyOa\u0001\u0015\t\u0005E(q\u0001\u000b\u0005\u0003g\u0014)\u0001\u0005\u0004U\u0001\u0005U(\u0011\u0001\t\t\u0003o\fiP!\u0001\u0002\u00105\u0011\u0011\u0011 \u0006\u0004\u0003wD\u0015A\u00027j]\u0006dw-\u0003\u0003\u0002\u0000\u0006e(aB\"pk:$XM\u001d\t\u00041\n\rA!\u0002.\u001a\u0005\u0004Y\u0006bBA\u000b3\u0001\u000f\u0011q\u0003\u0005\b\u0005\u0013I\u0002\u0019AA{\u0003\u0005\u0019\u0017aA:z[R1!q\u0002B\u000e\u0005?!BA!\u0005\u0003\u001aA1A\u000b\u0001B\n\u00037\u0003b!a>\u0003\u0016\u0005=\u0011\u0002\u0002B\f\u0003s\u00141\u0002R3og\u00164Vm\u0019;pe\"9\u0011Q\u0003\u000eA\u0004\u0005]\u0001b\u0002B\u000f5\u0001\u0007\u0011qB\u0001\u0006C2\u0004\b.\u0019\u0005\b\u0005CQ\u0002\u0019AAN\u0003\u0005YG\u0003\u0002B\u0013\u0005S!BA!\u0005\u0003(!9\u0011QC\u000eA\u0004\u0005]\u0001b\u0002B\u00167\u0001\u0007!QF\u0001\u0004CJ\u0014\b#\u0002(\u00030\u0005=\u0011b\u0001B\u0019\u001f\n)\u0011I\u001d:bs\n1Q\t\u001f9GC6,bAa\u000e\u0003D\t\u001d3\u0003\u0002\u000fN\u0005s\u0001r\u0001\u0016B\u001e\u0005\u007f\u0011\t%C\u0002\u0003>\u0011\u0013\u0011#\u0012=q_:,g\u000e^5bY\u001a\u000bW.\u001b7z!\u0019!\u0006A!\u0011\u0003FA\u0019\u0001La\u0011\u0005\u000bic\"\u0019A.\u0011\u0007a\u00139\u0005B\u0003~9\t\u00071,\u0001\u0005fq\u0016l\u0007\u000f\\1s!%1(Q\nB!\u0005\u000b\ny!C\u0002\u0003P]\u0014A$T;uC\ndWMR5oSR,7i\\8sI&t\u0017\r^3GS\u0016dG\r\u0006\u0003\u0003T\tmC\u0003\u0002B+\u00053\u0002rAa\u0016\u001d\u0005\u0003\u0012)%D\u0001\u0018\u0011\u0019!x\u0004q\u0001\u0003L!9!\u0011J\u0010A\u0002\t\u0005#!\u0003)be\u0006lW\r^3s\u0005M\u0019VO\u001a4jG&,g\u000e^*uCRL7\u000f^5d'\u0019\tSJa\u0019cKB)AK!\u001a\u0003h%\u0019!q\f#\u0011\u0007\t%\u0014%D\u0001\u001d\u0003\u0005q\u0017A\u00018!\u0003\u0005!XC\u0001B!\u0003\t!\b\u0005\u0006\u0004\u0003h\t]$\u0011\u0010\u0005\b\u0005W2\u0003\u0019AA\b\u0011\u001d\u0011yG\na\u0001\u0005\u0003\nQ\u0001\n9mkN$BAa\u001a\u0003\u0000!9!\u0011Q\u0014A\u0002\t\u001d\u0014A\u0001;u\u0003\u0019!C/[7fgR!!q\rBD\u0011\u001d\u0011I\t\u000ba\u0001\u0003\u001f\t\u0011a\u001e\u000b\u0007\u0005O\u0012iIa$\t\u0013\t-\u0014\u0006%AA\u0002\u0005=\u0001\"\u0003B8SA\u0005\t\u0019\u0001B!+\t\u0011\u0019J\u000b\u0003\u0002\u0010\u0005\u001d\u0014AD2paf$C-\u001a4bk2$HEM\u000b\u0003\u00053SCA!\u0011\u0002hQ\u0019qL!(\t\u0013\u0005\u001df&!AA\u0002\u0005mE\u0003BA_\u0005CC\u0001\"a*1\u0003\u0003\u0005\ra\u0018\u000b\u0005\u0003\u000f\u0013)\u000bC\u0005\u0002(F\n\t\u00111\u0001\u0002\u001cR!\u0011Q\u0018BU\u0011!\t9\u000bNA\u0001\u0002\u0004y\u0016aE*vM\u001aL7-[3oiN#\u0018\r^5ti&\u001c\u0007c\u0001B5mM)aG!-\u0002`BQ!1\u0017B]\u0003\u001f\u0011\tEa\u001a\u000e\u0005\tU&b\u0001B\\\u001f\u00069!/\u001e8uS6,\u0017\u0002\u0002B^\u0005k\u0013\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c83)\t\u0011i\u000b\u0006\u0004\u0003h\t\u0005'1\u0019\u0005\b\u0005WJ\u0004\u0019AA\b\u0011\u001d\u0011y'\u000fa\u0001\u0005\u0003\nq!\u001e8baBd\u0017\u0010\u0006\u0003\u0003J\nU\u0007#\u0002(\u0003L\n=\u0017b\u0001Bg\u001f\n1q\n\u001d;j_:\u0004rA\u0014Bi\u0003\u001f\u0011\t%C\u0002\u0003T>\u0013a\u0001V;qY\u0016\u0014\u0004\"\u0003Blu\u0005\u0005\t\u0019\u0001B4\u0003\rAH\u0005M\u0001\u0019K6\u0004H/_*vM\u001aL7-[3oiN#\u0018\r^5ti&\u001cWC\u0001B4\u0003Y\u0019XO\u001a4jG&,g\u000e^*uCRL7\u000f^5d\r>\u0014H\u0003\u0002B4\u0005CDqAa\u001c=\u0001\u0004\u0011\t%A\u0002nY\u0016$BA!\u0011\u0003h\"1q)\u0010a\u0001\u0005O\n!\u0003\\5lK2L\u0007n\\8e\rVt7\r^5p]R!!Q\u001eB\u0000%\u0015\u0011y/\u0014Bz\r\u0019\u0011\tP\u0010\u0001\u0003n\naAH]3gS:,W.\u001a8u}A1!Q\u001fB~\u0005\u0003j!Aa>\u000b\u0007\te\b*\u0001\u0005paRLW.\u001b>f\u0013\u0011\u0011iPa>\u0003\u0019\u0011KgM\u001a$v]\u000e$\u0018n\u001c8\t\r\u001ds\u0004\u0019\u0001B4\u00031!\u0017n\u001d;sS\n,H/[8o)\u0011\u0019)a!\u0004\u0015\t\r\u001d11\u0002\t\u0007)\u0002\u0019IA!\u0012\u0011\u0007\t%\u0004\u0005C\u0004\u0002\u0016}\u0002\u001d!a\u0006\t\u000f\r=q\b1\u0001\u0004\n\u0005\t\u0001/\u0006\u0004\u0004\u0014\rm1q\u0004\u000b\u0005\u0007+\u0019i\u0003\u0006\u0004\u0004\u0018\r\u001d21\u0006\t\u0007)\u0002\u0019Ib!\b\u0011\u0007a\u001bY\u0002B\u0003[\u0001\n\u00071\fE\u0002Y\u0007?!\u0011\" !!\u0002\u0003\u0005)\u0019A.)\u000b\r}qpa\t2\u0013\r\n9!!\u0003\u0004&\u0005-\u0011\u0007\u0002\u0013hWBCa\u0001\u001e!A\u0004\r%\u0002\u0003\u0003<z\u00073\u0019i\"a\u0004\t\u000f\u0005U\u0001\tq\u0001\u0002\u0018!1\u0011\u000f\u0011a\u0001\u00073)ba!\r\u00048\r}B\u0003BB\u001a\u0007s\u0001RA\u0014Bf\u0007k\u00012\u0001WB\u001c\t\u0015Q\u0016I1\u0001\\\u0011%\u00119.QA\u0001\u0002\u0004\u0019Y\u0004\u0005\u0004U\u0001\rU2Q\b\t\u00041\u000e}B!C?BA\u0003\u0005\tQ1\u0001\\Q\u0015\u0019yd`B\"c%\u0019\u0013qAA\u0005\u0007\u000b\nY!\r\u0003%O.\u0004\u0016\u0001D<sSR,'+\u001a9mC\u000e,GCAB&!\u0011\tIi!\u0014\n\t\r=\u00131\u0012\u0002\u0007\u001f\nTWm\u0019;"
)
public class Dirichlet implements ContinuousDistr, Product {
   private double logNormalizer;
   private final Object params;
   private final EnumeratedCoordinateField space;
   private final RandBasis rand;
   private double normalizer;
   private volatile byte bitmap$0;

   public static Option unapply(final Dirichlet x$0) {
      return Dirichlet$.MODULE$.unapply(x$0);
   }

   public static Dirichlet sym(final double alpha, final int k, final RandBasis rand) {
      return Dirichlet$.MODULE$.sym(alpha, k, rand);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public double pdf(final Object x) {
      return ContinuousDistr.pdf$(this, x);
   }

   public double logPdf(final Object x) {
      return ContinuousDistr.logPdf$(this, x);
   }

   public double unnormalizedPdf(final Object x) {
      return ContinuousDistr.unnormalizedPdf$(this, x);
   }

   public double apply(final Object x) {
      return ContinuousDistr.apply$(this, x);
   }

   public double logApply(final Object x) {
      return ContinuousDistr.logApply$(this, x);
   }

   public double draw$mcD$sp() {
      return Rand.draw$mcD$sp$(this);
   }

   public int draw$mcI$sp() {
      return Rand.draw$mcI$sp$(this);
   }

   public Object get() {
      return Rand.get$(this);
   }

   public double get$mcD$sp() {
      return Rand.get$mcD$sp$(this);
   }

   public int get$mcI$sp() {
      return Rand.get$mcI$sp$(this);
   }

   public Option drawOpt() {
      return Rand.drawOpt$(this);
   }

   public Object sample() {
      return Rand.sample$(this);
   }

   public double sample$mcD$sp() {
      return Rand.sample$mcD$sp$(this);
   }

   public int sample$mcI$sp() {
      return Rand.sample$mcI$sp$(this);
   }

   public IndexedSeq sample(final int n) {
      return Rand.sample$(this, n);
   }

   public Iterator samples() {
      return Rand.samples$(this);
   }

   public DenseVector samplesVector(final int size, final ClassTag m) {
      return Rand.samplesVector$(this, size, m);
   }

   public DenseVector samplesVector$mcD$sp(final int size, final ClassTag m) {
      return Rand.samplesVector$mcD$sp$(this, size, m);
   }

   public DenseVector samplesVector$mcI$sp(final int size, final ClassTag m) {
      return Rand.samplesVector$mcI$sp$(this, size, m);
   }

   public Rand flatMap(final Function1 f) {
      return Rand.flatMap$(this, f);
   }

   public Rand flatMap$mcD$sp(final Function1 f) {
      return Rand.flatMap$mcD$sp$(this, f);
   }

   public Rand flatMap$mcI$sp(final Function1 f) {
      return Rand.flatMap$mcI$sp$(this, f);
   }

   public Rand map(final Function1 f) {
      return Rand.map$(this, f);
   }

   public Rand map$mcD$sp(final Function1 f) {
      return Rand.map$mcD$sp$(this, f);
   }

   public Rand map$mcI$sp(final Function1 f) {
      return Rand.map$mcI$sp$(this, f);
   }

   public void foreach(final Function1 f) {
      Rand.foreach$(this, f);
   }

   public void foreach$mcD$sp(final Function1 f) {
      Rand.foreach$mcD$sp$(this, f);
   }

   public void foreach$mcI$sp(final Function1 f) {
      Rand.foreach$mcI$sp$(this, f);
   }

   public Rand filter(final Function1 p) {
      return Rand.filter$(this, p);
   }

   public Rand filter$mcD$sp(final Function1 p) {
      return Rand.filter$mcD$sp$(this, p);
   }

   public Rand filter$mcI$sp(final Function1 p) {
      return Rand.filter$mcI$sp$(this, p);
   }

   public Rand withFilter(final Function1 p) {
      return Rand.withFilter$(this, p);
   }

   public Rand withFilter$mcD$sp(final Function1 p) {
      return Rand.withFilter$mcD$sp$(this, p);
   }

   public Rand withFilter$mcI$sp(final Function1 p) {
      return Rand.withFilter$mcI$sp$(this, p);
   }

   public Rand condition(final Function1 p) {
      return Rand.condition$(this, p);
   }

   public Rand condition$mcD$sp(final Function1 p) {
      return Rand.condition$mcD$sp$(this, p);
   }

   public Rand condition$mcI$sp(final Function1 p) {
      return Rand.condition$mcI$sp$(this, p);
   }

   private double normalizer$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.normalizer = ContinuousDistr.normalizer$(this);
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.normalizer;
   }

   public double normalizer() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.normalizer$lzycompute() : this.normalizer;
   }

   public Object params() {
      return this.params;
   }

   public Object draw() {
      return normalize$.MODULE$.apply(this.unnormalizedDraw(), BoxesRunTime.boxToDouble((double)1.0F), normalize$.MODULE$.normalizeDoubleImpl(this.space.divVS(), this.space.normImpl2()));
   }

   public Object unnormalizedDraw() {
      return this.space.mapValues().mapActive$mcDD$sp(this.params(), (JFunction1.mcDD.sp)(v) -> v == (double)0.0F ? (double)0.0F : (new Gamma(v, (double)1.0F, this.rand)).draw());
   }

   public Object logDraw() {
      Object x = this.space.mapValues().mapActive$mcDD$sp(this.params(), (JFunction1.mcDD.sp)(v) -> v == (double)0.0F ? (double)0.0F : (new Gamma(v, (double)1.0F, this.rand)).logDraw());
      double m = BoxesRunTime.unboxToDouble(softmax$.MODULE$.apply(((QuasiTensor)this.space.hasOps().apply(x)).activeValuesIterator(), softmax$.MODULE$.reduceDouble(CanTraverseValues$.MODULE$.canTraverseIterator(), max$.MODULE$.reduce_Double(CanTraverseValues$.MODULE$.canTraverseIterator()))));
      .MODULE$.assert(!Double.isInfinite(m), () -> x);
      ((QuasiTensor)this.space.hasOps().apply(x)).activeKeysIterator().foreach((i) -> {
         $anonfun$logDraw$3(this, x, m, i);
         return BoxedUnit.UNIT;
      });
      return x;
   }

   public double unnormalizedLogPdf(final Object m) {
      Iterator parts = ((QuasiTensor)this.space.hasOps().apply(this.params())).activeIterator().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$unnormalizedLogPdf$1(check$ifrefutable$1))).map((x$1) -> BoxesRunTime.boxToDouble($anonfun$unnormalizedLogPdf$2(this, m, x$1)));
      return BoxesRunTime.unboxToDouble(parts.sum(scala.math.Numeric.DoubleIsFractional..MODULE$));
   }

   private double logNormalizer$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.logNormalizer = BoxesRunTime.unboxToDouble(package.lbeta$.MODULE$.apply(this.params(), package.lbeta$.MODULE$.reduceDouble(this.space.iterateValues())));
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.logNormalizer;
   }

   public double logNormalizer() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.logNormalizer$lzycompute() : this.logNormalizer;
   }

   public Dirichlet copy(final Object params, final EnumeratedCoordinateField space, final RandBasis rand) {
      return new Dirichlet(params, space, rand);
   }

   public Object copy$default$1() {
      return this.params();
   }

   public String productPrefix() {
      return "Dirichlet";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.params();
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
      return x$1 instanceof Dirichlet;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "params";
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
      boolean var10000;
      if (this != x$1) {
         label49: {
            boolean var2;
            if (x$1 instanceof Dirichlet) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               Dirichlet var4 = (Dirichlet)x$1;
               if (BoxesRunTime.equals(this.params(), var4.params()) && var4.canEqual(this)) {
                  break label49;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public Dirichlet copy$mIc$sp(final Object params, final EnumeratedCoordinateField space, final RandBasis rand) {
      return new Dirichlet$mcI$sp(params, space, rand);
   }

   // $FF: synthetic method
   public static final void $anonfun$logDraw$3(final Dirichlet $this, final Object x$2, final double m$1, final Object i) {
      NumericOps var5 = (NumericOps)$this.space.hasOps().apply(x$2);
      ((QuasiTensor)var5).update(i, BoxesRunTime.boxToDouble(BoxesRunTime.unboxToDouble(((QuasiTensor)var5).apply(i)) - m$1));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$unnormalizedLogPdf$1(final Tuple2 check$ifrefutable$1) {
      boolean var1;
      if (check$ifrefutable$1 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   // $FF: synthetic method
   public static final double $anonfun$unnormalizedLogPdf$2(final Dirichlet $this, final Object m$2, final Tuple2 x$1) {
      if (x$1 != null) {
         Object k = x$1._1();
         double v = x$1._2$mcD$sp();
         double var3 = (v - (double)1) * scala.math.package..MODULE$.log(BoxesRunTime.unboxToDouble(((QuasiTensor)$this.space.hasOps().apply(m$2)).apply(k)));
         return var3;
      } else {
         throw new MatchError(x$1);
      }
   }

   public Dirichlet(final Object params, final EnumeratedCoordinateField space, final RandBasis rand) {
      this.params = params;
      this.space = space;
      this.rand = rand;
      Density.$init$(this);
      Rand.$init$(this);
      ContinuousDistr.$init$(this);
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class ExpFam implements ExponentialFamily {
      private volatile SufficientStatistic$ SufficientStatistic$module;
      private final Object exemplar;
      public final MutableFiniteCoordinateField breeze$stats$distributions$Dirichlet$ExpFam$$space;

      public SufficientStatistic$ SufficientStatistic() {
         if (this.SufficientStatistic$module == null) {
            this.SufficientStatistic$lzycompute$1();
         }

         return this.SufficientStatistic$module;
      }

      public SufficientStatistic emptySufficientStatistic() {
         return new SufficientStatistic((double)0.0F, this.breeze$stats$distributions$Dirichlet$ExpFam$$space.zeroLike().apply(this.exemplar));
      }

      public SufficientStatistic sufficientStatisticFor(final Object t) {
         return new SufficientStatistic((double)1.0F, package.log$.MODULE$.apply(normalize$.MODULE$.apply(t, BoxesRunTime.boxToDouble((double)1.0F), normalize$.MODULE$.normalizeDoubleImpl(this.breeze$stats$distributions$Dirichlet$ExpFam$$space.divVS(), this.breeze$stats$distributions$Dirichlet$ExpFam$$space.normImpl2())), HasOps$.MODULE$.fromLowOrderCanMapValues(this.breeze$stats$distributions$Dirichlet$ExpFam$$space.scalarOf(), package$log$logDoubleImpl$.MODULE$, this.breeze$stats$distributions$Dirichlet$ExpFam$$space.mapValues())));
      }

      public Object mle(final SufficientStatistic stats) {
         DiffFunction likelihood = this.likelihoodFunction(stats);
         Object result = package$.MODULE$.minimize(likelihood, ((ImmutableNumericOps)this.breeze$stats$distributions$Dirichlet$ExpFam$$space.hasOps().apply(this.breeze$stats$distributions$Dirichlet$ExpFam$$space.zeroLike().apply(stats.t()))).$plus$colon$plus(BoxesRunTime.boxToDouble((double)1.0F), this.breeze$stats$distributions$Dirichlet$ExpFam$$space.addVS()), scala.collection.immutable.Nil..MODULE$, OptimizationPackage$.MODULE$.lbfgsMinimizationPackage(this.breeze$stats$distributions$Dirichlet$ExpFam$$space, scala..less.colon.less..MODULE$.refl()));
         return result;
      }

      public DiffFunction likelihoodFunction(final SufficientStatistic stats) {
         return new DiffFunction(stats) {
            private final Object p;
            // $FF: synthetic field
            private final ExpFam $outer;
            private final SufficientStatistic stats$1;

            public DiffFunction repr() {
               return DiffFunction.repr$(this);
            }

            public DiffFunction cached(final CanCopy copy) {
               return DiffFunction.cached$(this, copy);
            }

            public DiffFunction throughLens(final Isomorphism l) {
               return DiffFunction.throughLens$(this, l);
            }

            public Object gradientAt(final Object x) {
               return StochasticDiffFunction.gradientAt$(this, x);
            }

            public double valueAt(final Object x) {
               return StochasticDiffFunction.valueAt$(this, x);
            }

            public final double apply(final Object x) {
               return StochasticDiffFunction.apply$(this, x);
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

            public boolean apply$mcZD$sp(final double v1) {
               return Function1.apply$mcZD$sp$(this, v1);
            }

            public double apply$mcDD$sp(final double v1) {
               return Function1.apply$mcDD$sp$(this, v1);
            }

            public float apply$mcFD$sp(final double v1) {
               return Function1.apply$mcFD$sp$(this, v1);
            }

            public int apply$mcID$sp(final double v1) {
               return Function1.apply$mcID$sp$(this, v1);
            }

            public long apply$mcJD$sp(final double v1) {
               return Function1.apply$mcJD$sp$(this, v1);
            }

            public void apply$mcVD$sp(final double v1) {
               Function1.apply$mcVD$sp$(this, v1);
            }

            public boolean apply$mcZF$sp(final float v1) {
               return Function1.apply$mcZF$sp$(this, v1);
            }

            public double apply$mcDF$sp(final float v1) {
               return Function1.apply$mcDF$sp$(this, v1);
            }

            public float apply$mcFF$sp(final float v1) {
               return Function1.apply$mcFF$sp$(this, v1);
            }

            public int apply$mcIF$sp(final float v1) {
               return Function1.apply$mcIF$sp$(this, v1);
            }

            public long apply$mcJF$sp(final float v1) {
               return Function1.apply$mcJF$sp$(this, v1);
            }

            public void apply$mcVF$sp(final float v1) {
               Function1.apply$mcVF$sp$(this, v1);
            }

            public boolean apply$mcZI$sp(final int v1) {
               return Function1.apply$mcZI$sp$(this, v1);
            }

            public double apply$mcDI$sp(final int v1) {
               return Function1.apply$mcDI$sp$(this, v1);
            }

            public float apply$mcFI$sp(final int v1) {
               return Function1.apply$mcFI$sp$(this, v1);
            }

            public int apply$mcII$sp(final int v1) {
               return Function1.apply$mcII$sp$(this, v1);
            }

            public long apply$mcJI$sp(final int v1) {
               return Function1.apply$mcJI$sp$(this, v1);
            }

            public void apply$mcVI$sp(final int v1) {
               Function1.apply$mcVI$sp$(this, v1);
            }

            public boolean apply$mcZJ$sp(final long v1) {
               return Function1.apply$mcZJ$sp$(this, v1);
            }

            public double apply$mcDJ$sp(final long v1) {
               return Function1.apply$mcDJ$sp$(this, v1);
            }

            public float apply$mcFJ$sp(final long v1) {
               return Function1.apply$mcFJ$sp$(this, v1);
            }

            public int apply$mcIJ$sp(final long v1) {
               return Function1.apply$mcIJ$sp$(this, v1);
            }

            public long apply$mcJJ$sp(final long v1) {
               return Function1.apply$mcJJ$sp$(this, v1);
            }

            public void apply$mcVJ$sp(final long v1) {
               Function1.apply$mcVJ$sp$(this, v1);
            }

            public Function1 compose(final Function1 g) {
               return Function1.compose$(this, g);
            }

            public Function1 andThen(final Function1 g) {
               return Function1.andThen$(this, g);
            }

            public String toString() {
               return Function1.toString$(this);
            }

            private Object p() {
               return this.p;
            }

            public Tuple2 calculate(final Object x) {
               double lp = -this.stats$1.n() * (-BoxesRunTime.unboxToDouble(package.lbeta$.MODULE$.apply(x, package.lbeta$.MODULE$.reduceDouble(this.$outer.breeze$stats$distributions$Dirichlet$ExpFam$$space.iterateValues()))) + BoxesRunTime.unboxToDouble(((ImmutableNumericOps)this.$outer.breeze$stats$distributions$Dirichlet$ExpFam$$space.hasOps().apply(((ImmutableNumericOps)this.$outer.breeze$stats$distributions$Dirichlet$ExpFam$$space.hasOps().apply(x)).$minus(BoxesRunTime.boxToDouble((double)1.0F), this.$outer.breeze$stats$distributions$Dirichlet$ExpFam$$space.subVS()))).dot(this.p(), this.$outer.breeze$stats$distributions$Dirichlet$ExpFam$$space.dotVV())));
               Object grad = ((ImmutableNumericOps)this.$outer.breeze$stats$distributions$Dirichlet$ExpFam$$space.hasOps().apply(((ImmutableNumericOps)this.$outer.breeze$stats$distributions$Dirichlet$ExpFam$$space.hasOps().apply(((ImmutableNumericOps)this.$outer.breeze$stats$distributions$Dirichlet$ExpFam$$space.hasOps().apply(package.digamma$.MODULE$.apply(x, HasOps$.MODULE$.fromLowOrderCanMapValues(this.$outer.breeze$stats$distributions$Dirichlet$ExpFam$$space.scalarOf(), package$digamma$digammaImplDouble$.MODULE$, this.$outer.breeze$stats$distributions$Dirichlet$ExpFam$$space.mapValues())))).$minus(BoxesRunTime.boxToDouble(package.digamma$.MODULE$.apply$mDDc$sp(BoxesRunTime.unboxToDouble(sum$.MODULE$.apply(x, sum$.MODULE$.reduce_Double(this.$outer.breeze$stats$distributions$Dirichlet$ExpFam$$space.iterateValues()))), package$digamma$digammaImplDouble$.MODULE$)), this.$outer.breeze$stats$distributions$Dirichlet$ExpFam$$space.subVS()))).$minus(this.p(), this.$outer.breeze$stats$distributions$Dirichlet$ExpFam$$space.subVV()))).$times(BoxesRunTime.boxToDouble(this.stats$1.n()), this.$outer.breeze$stats$distributions$Dirichlet$ExpFam$$space.mulVS_M());
               return Double.isNaN(lp) ? new Tuple2(BoxesRunTime.boxToDouble(Double.POSITIVE_INFINITY), grad) : new Tuple2(BoxesRunTime.boxToDouble(lp), grad);
            }

            public {
               if (ExpFam.this == null) {
                  throw null;
               } else {
                  this.$outer = ExpFam.this;
                  this.stats$1 = stats$1;
                  Function1.$init$(this);
                  ImmutableNumericOps.$init$(this);
                  NumericOps.$init$(this);
                  StochasticDiffFunction.$init$(this);
                  DiffFunction.$init$(this);
                  this.p = ((ImmutableNumericOps)ExpFam.this.breeze$stats$distributions$Dirichlet$ExpFam$$space.hasOps().apply(stats$1.t())).$div(BoxesRunTime.boxToDouble(stats$1.n()), ExpFam.this.breeze$stats$distributions$Dirichlet$ExpFam$$space.divVS());
               }
            }
         };
      }

      public Dirichlet distribution(final Object p, final RandBasis rand) {
         return new Dirichlet(p, this.breeze$stats$distributions$Dirichlet$ExpFam$$space, rand);
      }

      private final void SufficientStatistic$lzycompute$1() {
         synchronized(this){}

         try {
            if (this.SufficientStatistic$module == null) {
               this.SufficientStatistic$module = new SufficientStatistic$();
            }
         } catch (Throwable var3) {
            throw var3;
         }

      }

      public ExpFam(final Object exemplar, final MutableFiniteCoordinateField space) {
         this.exemplar = exemplar;
         this.breeze$stats$distributions$Dirichlet$ExpFam$$space = space;
      }

      public class SufficientStatistic implements SufficientStatistic, Product, Serializable {
         private final double n;
         private final Object t;
         // $FF: synthetic field
         public final ExpFam $outer;

         public Iterator productElementNames() {
            return Product.productElementNames$(this);
         }

         public double n() {
            return this.n;
         }

         public Object t() {
            return this.t;
         }

         public SufficientStatistic $plus(final SufficientStatistic tt) {
            return this.breeze$stats$distributions$Dirichlet$ExpFam$SufficientStatistic$$$outer().new SufficientStatistic(this.n() + tt.n(), ((NumericOps)this.breeze$stats$distributions$Dirichlet$ExpFam$SufficientStatistic$$$outer().breeze$stats$distributions$Dirichlet$ExpFam$$space.hasOps().apply(this.t())).$plus(tt.t(), this.breeze$stats$distributions$Dirichlet$ExpFam$SufficientStatistic$$$outer().breeze$stats$distributions$Dirichlet$ExpFam$$space.addVV()));
         }

         public SufficientStatistic $times(final double w) {
            return this.breeze$stats$distributions$Dirichlet$ExpFam$SufficientStatistic$$$outer().new SufficientStatistic(this.n() * w, ((ImmutableNumericOps)this.breeze$stats$distributions$Dirichlet$ExpFam$SufficientStatistic$$$outer().breeze$stats$distributions$Dirichlet$ExpFam$$space.hasOps().apply(this.t())).$times(BoxesRunTime.boxToDouble(w), this.breeze$stats$distributions$Dirichlet$ExpFam$SufficientStatistic$$$outer().breeze$stats$distributions$Dirichlet$ExpFam$$space.mulVS_M()));
         }

         public SufficientStatistic copy(final double n, final Object t) {
            return this.breeze$stats$distributions$Dirichlet$ExpFam$SufficientStatistic$$$outer().new SufficientStatistic(n, t);
         }

         public double copy$default$1() {
            return this.n();
         }

         public Object copy$default$2() {
            return this.t();
         }

         public String productPrefix() {
            return "SufficientStatistic";
         }

         public int productArity() {
            return 2;
         }

         public Object productElement(final int x$1) {
            Object var10000;
            switch (x$1) {
               case 0:
                  var10000 = BoxesRunTime.boxToDouble(this.n());
                  break;
               case 1:
                  var10000 = this.t();
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
            return x$1 instanceof SufficientStatistic;
         }

         public String productElementName(final int x$1) {
            String var10000;
            switch (x$1) {
               case 0:
                  var10000 = "n";
                  break;
               case 1:
                  var10000 = "t";
                  break;
               default:
                  var10000 = (String)Statics.ioobe(x$1);
            }

            return var10000;
         }

         public int hashCode() {
            int var1 = -889275714;
            var1 = Statics.mix(var1, this.productPrefix().hashCode());
            var1 = Statics.mix(var1, Statics.doubleHash(this.n()));
            var1 = Statics.mix(var1, Statics.anyHash(this.t()));
            return Statics.finalizeHash(var1, 2);
         }

         public String toString() {
            return scala.runtime.ScalaRunTime..MODULE$._toString(this);
         }

         public boolean equals(final Object x$1) {
            boolean var10000;
            if (this != x$1) {
               label56: {
                  boolean var2;
                  if (x$1 instanceof SufficientStatistic && ((SufficientStatistic)x$1).breeze$stats$distributions$Dirichlet$ExpFam$SufficientStatistic$$$outer() == this.breeze$stats$distributions$Dirichlet$ExpFam$SufficientStatistic$$$outer()) {
                     var2 = true;
                  } else {
                     var2 = false;
                  }

                  if (var2) {
                     SufficientStatistic var4 = (SufficientStatistic)x$1;
                     if (this.n() == var4.n() && BoxesRunTime.equals(this.t(), var4.t()) && var4.canEqual(this)) {
                        break label56;
                     }
                  }

                  var10000 = false;
                  return var10000;
               }
            }

            var10000 = true;
            return var10000;
         }

         // $FF: synthetic method
         public ExpFam breeze$stats$distributions$Dirichlet$ExpFam$SufficientStatistic$$$outer() {
            return this.$outer;
         }

         public SufficientStatistic(final double n, final Object t) {
            this.n = n;
            this.t = t;
            if (ExpFam.this == null) {
               throw null;
            } else {
               this.$outer = ExpFam.this;
               super();
               Product.$init$(this);
            }
         }
      }

      public class SufficientStatistic$ extends AbstractFunction2 implements Serializable {
         // $FF: synthetic field
         private final ExpFam $outer;

         public final String toString() {
            return "SufficientStatistic";
         }

         public SufficientStatistic apply(final double n, final Object t) {
            return this.$outer.new SufficientStatistic(n, t);
         }

         public Option unapply(final SufficientStatistic x$0) {
            return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToDouble(x$0.n()), x$0.t())));
         }

         public SufficientStatistic$() {
            if (ExpFam.this == null) {
               throw null;
            } else {
               this.$outer = ExpFam.this;
               super();
            }
         }
      }
   }
}
