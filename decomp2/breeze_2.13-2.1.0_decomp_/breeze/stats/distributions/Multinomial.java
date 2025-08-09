package breeze.stats.distributions;

import breeze.generic.UFunc;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.NumericOps;
import breeze.linalg.QuasiTensor;
import breeze.linalg.logNormalize$;
import breeze.linalg.max$;
import breeze.linalg.package$;
import breeze.linalg.softmax$;
import breeze.linalg.sum$;
import breeze.linalg.operators.HasOps$;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.CanTranspose;
import breeze.math.Module;
import breeze.math.MutableFiniteCoordinateField;
import breeze.math.MutableModule;
import breeze.math.MutablizingAdaptor;
import breeze.math.MutablizingAdaptor$;
import breeze.math.VectorSpace;
import breeze.numerics.package;
import breeze.numerics.package$exp$expDoubleImpl$;
import breeze.numerics.package$log$logDoubleImpl$;
import breeze.optimize.DiffFunction;
import breeze.optimize.StochasticDiffFunction;
import breeze.storage.Zero$;
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
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.collection.mutable.Stack;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.DoubleRef;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.ObjectRef;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\r}f\u0001\u0002&L\u0001JC\u0001\u0002\u001f\u0001\u0003\u0016\u0004%\t!\u001f\u0005\t{\u0002\u0011\t\u0012)A\u0005u\"Aa\u0010\u0001B\u0001B\u0003-q\u0010\u0003\u0006\u0002,\u0001\u0011\t\u0011)A\u0006\u0003[A!\"!\u0012\u0001\u0005\u0003\u0005\u000b1BA$\u0011\u001d\ti\u0005\u0001C\u0001\u0003\u001fB\u0011\"a\u000e\u0001\u0005\u0004%\t!!\u0018\t\u0011\u0005}\u0003\u0001)A\u0005\u0003KA\u0011\"!\u0019\u0001\u0001\u0004%I!a\u0019\t\u0013\u0005-\u0004\u00011A\u0005\n\u00055\u0004\u0002CA=\u0001\u0001\u0006K!!\u001a\t\u0015\u0005m\u0004\u0001#b\u0001\n\u0013\ti\bC\u0004\u0002\u0006\u0002!\t!a\"\t\u000f\u0005%\u0005\u0001\"\u0001\u0002\b\"9\u00111\u0012\u0001\u0005\n\u00055\u0005bBAH\u0001\u0011\u0005\u0011\u0011\u0013\u0005\b\u0003/\u0003A\u0011IAM\u0011\u001d\ti\n\u0001C!\u0003?Cq!!-\u0001\t\u0003\t\u0019\fC\u0005\u0002Z\u0002\t\t\u0011\"\u0001\u0002\\\"I\u0011\u0011 \u0001\u0012\u0002\u0013\u0005\u00111 \u0005\n\u0005/\u0001\u0011\u0011!C!\u00053A\u0011B!\u000b\u0001\u0003\u0003%\tAa\u000b\t\u0013\tM\u0002!!A\u0005\u0002\tU\u0002\"\u0003B\u001d\u0001\u0005\u0005I\u0011\tB\u001e\u0011%\u0011I\u0005AA\u0001\n\u0003\u0011Y\u0005C\u0005\u0003P\u0001\t\t\u0011\"\u0011\u0003R!I!Q\u000b\u0001\u0002\u0002\u0013\u0005#q\u000b\u0005\n\u00053\u0002\u0011\u0011!C!\u00057:qAa\u0018L\u0011\u0003\u0011\tG\u0002\u0004K\u0017\"\u0005!1\r\u0005\b\u0003\u001bzB\u0011\u0001B8\u0011\u001d\u0011\th\bC\u0001\u0005g2aA!% \u0001\tM\u0005B\u0003BWE\t\u0005\t\u0015!\u0003\u0003 \"Q!q\u0016\u0012\u0003\u0002\u0003\u0006YA!-\t\u000f\u00055#\u0005\"\u0001\u00038\u00161!1\u0019\u0012\u0001\u0005\u000bD\u0011Ba3#\u0005\u0004%\tA!4\t\u0011\tm'\u0005)A\u0005\u0005\u001fDqA!8#\t\u0003\u0011y\u000eC\u0004\u0003|\n\"\tA!@\u0006\r\tE(\u0005\u0001BP\r\u0019\u0019iA\t!\u0004\u0010!Q1q\u0003\u0017\u0003\u0016\u0004%\ta!\u0007\t\u0015\rmAF!E!\u0002\u0013\u0011y\nC\u0004\u0002N1\"\ta!\b\t\u000f\r\u0005B\u0006\"\u0001\u0004$!91\u0011\u0006\u0017\u0005\u0002\r-\u0002\"CAmY\u0005\u0005I\u0011AB\u0019\u0011%\tI\u0010LI\u0001\n\u0003\u0019)\u0004C\u0005\u0003\u00181\n\t\u0011\"\u0011\u0003\u001a!I!\u0011\u0006\u0017\u0002\u0002\u0013\u0005!1\u0006\u0005\n\u0005ga\u0013\u0011!C\u0001\u0007sA\u0011B!\u000f-\u0003\u0003%\tEa\u000f\t\u0013\t%C&!A\u0005\u0002\ru\u0002\"\u0003B(Y\u0005\u0005I\u0011IB!\u0011%\u0011)\u0006LA\u0001\n\u0003\u00129\u0006C\u0005\u0002\u001e2\n\t\u0011\"\u0011\u0004F!I!\u0011\f\u0017\u0002\u0002\u0013\u00053qI\u0004\n\u0007\u0017\u0012\u0013\u0011!E\u0001\u0007\u001b2\u0011b!\u0004#\u0003\u0003E\taa\u0014\t\u000f\u00055c\b\"\u0001\u0004^!I\u0011Q\u0014 \u0002\u0002\u0013\u00153Q\t\u0005\n\u0005cr\u0014\u0011!CA\u0007?B\u0011ba\u0019?\u0003\u0003%\ti!\u001a\t\u000f\rE$\u0005\"\u0001\u0004t!91Q\u000f\u0012\u0005\u0002\r]\u0004bBB?E\u0011\u00051q\u0010\u0005\b\u0007\u0007\u0013C\u0011ABC\u0011\u001d\u0019)J\tC!\u0007/C\u0011ba\u0019 \u0003\u0003%\tia)\t\u0013\rUv$!A\u0005\n\r]&aC'vYRLgn\\7jC2T!\u0001T'\u0002\u001b\u0011L7\u000f\u001e:jEV$\u0018n\u001c8t\u0015\tqu*A\u0003ti\u0006$8OC\u0001Q\u0003\u0019\u0011'/Z3{K\u000e\u0001QcA*|AN)\u0001\u0001\u0016.jYB\u0011Q\u000bW\u0007\u0002-*\tq+A\u0003tG\u0006d\u0017-\u0003\u0002Z-\n1\u0011I\\=SK\u001a\u00042a\u0017/_\u001b\u0005Y\u0015BA/L\u00055!\u0015n]2sKR,G)[:ueB\u0011q\f\u0019\u0007\u0001\t\u0015\t\u0007A1\u0001c\u0005\u0005I\u0015CA2g!\t)F-\u0003\u0002f-\n9aj\u001c;iS:<\u0007CA+h\u0013\tAgKA\u0002B]f\u0004\"!\u00166\n\u0005-4&a\u0002)s_\u0012,8\r\u001e\t\u0003[Vt!A\\:\u000f\u0005=\u0014X\"\u00019\u000b\u0005E\f\u0016A\u0002\u001fs_>$h(C\u0001X\u0013\t!h+A\u0004qC\u000e\\\u0017mZ3\n\u0005Y<(\u0001D*fe&\fG.\u001b>bE2,'B\u0001;W\u0003\u0019\u0001\u0018M]1ngV\t!\u0010\u0005\u0002`w\u0012)A\u0010\u0001b\u0001E\n\tA+A\u0004qCJ\fWn\u001d\u0011\u0002\u0005\u00154\bcBA\u0001\u0003'Q\u0018\u0011\u0004\b\u0005\u0003\u0007\tiA\u0004\u0003\u0002\u0006\u0005%abA8\u0002\b%\t\u0001+C\u0002\u0002\f=\u000baaY8na\u0006$\u0018\u0002BA\b\u0003#\tAbU2bY\u0006\u001c4i\\7qCRT1!a\u0003P\u0013\u0011\t)\"a\u0006\u0003'\r{gN^3sg&|gn\u0014:Tk\n$\u0018\u0010]3\u000b\t\u0005=\u0011\u0011\u0003\t\b\u00037\t\tCXA\u0013\u001b\t\tiBC\u0002\u0002 =\u000ba\u0001\\5oC2<\u0017\u0002BA\u0012\u0003;\u00111\"U;bg&$VM\\:peB\u0019Q+a\n\n\u0007\u0005%bK\u0001\u0004E_V\u0014G.Z\u0001\bgVl\u0017*\u001c9m!\u001d\ty#!\u000f{\u0003KqA!!\r\u000269!\u0011QAA\u001a\u0013\r\tybT\u0005\u0005\u0003o\ti\"A\u0002tk6LA!a\u000f\u0002>\t!\u0011*\u001c9m\u0013\u0011\ty$!\u0011\u0003\u000bU3UO\\2\u000b\u0007\u0005\rs*A\u0004hK:,'/[2\u0002\tI\fg\u000e\u001a\t\u00047\u0006%\u0013bAA&\u0017\nI!+\u00198e\u0005\u0006\u001c\u0018n]\u0001\u0007y%t\u0017\u000e\u001e \u0015\t\u0005E\u00131\f\u000b\t\u0003'\n)&a\u0016\u0002ZA!1\f\u0001>_\u0011\u0015qh\u0001q\u0001\u0000\u0011\u001d\tYC\u0002a\u0002\u0003[Aq!!\u0012\u0007\u0001\b\t9\u0005C\u0003y\r\u0001\u0007!0\u0006\u0002\u0002&\u0005!1/^7!\u0003-A\u0017M^3TC6\u0004H.\u001a3\u0016\u0005\u0005\u0015\u0004cA+\u0002h%\u0019\u0011\u0011\u000e,\u0003\u000f\t{w\u000e\\3b]\u0006y\u0001.\u0019<f'\u0006l\u0007\u000f\\3e?\u0012*\u0017\u000f\u0006\u0003\u0002p\u0005U\u0004cA+\u0002r%\u0019\u00111\u000f,\u0003\tUs\u0017\u000e\u001e\u0005\n\u0003oR\u0011\u0011!a\u0001\u0003K\n1\u0001\u001f\u00132\u00031A\u0017M^3TC6\u0004H.\u001a3!\u0003)\tG.[1t)\u0006\u0014G.Z\u000b\u0003\u0003\u007f\u0002BaWAA=&\u0019\u00111Q&\u0003\u0015\u0005c\u0017.Y:UC\ndW-\u0001\u0003ee\u0006<H#\u00010\u0002\u0013\u0011\u0014\u0018m\u001e(bSZ,\u0017a\u00042vS2$\u0017\t\\5bgR\u000b'\r\\3\u0015\u0005\u0005}\u0014!\u00049s_\n\f'-\u001b7jif|e\r\u0006\u0003\u0002&\u0005M\u0005BBAK!\u0001\u0007a,A\u0001f\u0003e)hN\\8s[\u0006d\u0017N_3e!J|'-\u00192jY&$\u0018p\u00144\u0015\t\u0005\u0015\u00121\u0014\u0005\u0007\u0003+\u000b\u0002\u0019\u00010\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!!)\u0011\t\u0005\r\u00161\u0016\b\u0005\u0003K\u000b9\u000b\u0005\u0002p-&\u0019\u0011\u0011\u0016,\u0002\rA\u0013X\rZ3g\u0013\u0011\ti+a,\u0003\rM#(/\u001b8h\u0015\r\tIKV\u0001\u000eKb\u0004Xm\u0019;fIZ\u000bG.^3\u0016\t\u0005U\u00161\u0018\u000b\u0005\u0003o\u000by\r\u0006\u0003\u0002:\u0006}\u0006cA0\u0002<\u00121\u0011QX\nC\u0002\t\u0014\u0011!\u0016\u0005\b\u0003\u0003\u001c\u00029AAb\u0003\t18\u000f\u0005\u0005\u0002F\u0006-\u0017\u0011XA\u0013\u001b\t\t9MC\u0002\u0002J>\u000bA!\\1uQ&!\u0011QZAd\u0005-1Vm\u0019;peN\u0003\u0018mY3\t\u000f\u0005E7\u00031\u0001\u0002T\u0006\ta\r\u0005\u0004V\u0003+t\u0016\u0011X\u0005\u0004\u0003/4&!\u0003$v]\u000e$\u0018n\u001c82\u0003\u0011\u0019w\u000e]=\u0016\r\u0005u\u0017Q]Au)\u0011\ty.a>\u0015\u0011\u0005\u0005\u00181^Ay\u0003k\u0004ba\u0017\u0001\u0002d\u0006\u001d\bcA0\u0002f\u0012)A\u0010\u0006b\u0001EB\u0019q,!;\u0005\u000b\u0005$\"\u0019\u00012\t\ry$\u00029AAw!!\t\t!a\u0005\u0002d\u0006=\b\u0003CA\u000e\u0003C\t9/!\n\t\u000f\u0005-B\u0003q\u0001\u0002tBA\u0011qFA\u001d\u0003G\f)\u0003C\u0004\u0002FQ\u0001\u001d!a\u0012\t\u0011a$\u0002\u0013!a\u0001\u0003G\fabY8qs\u0012\"WMZ1vYR$\u0013'\u0006\u0004\u0002~\nM!QC\u000b\u0003\u0003\u007fT3A\u001fB\u0001W\t\u0011\u0019\u0001\u0005\u0003\u0003\u0006\t=QB\u0001B\u0004\u0015\u0011\u0011IAa\u0003\u0002\u0013Ut7\r[3dW\u0016$'b\u0001B\u0007-\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\tE!q\u0001\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,G!\u0002?\u0016\u0005\u0004\u0011G!B1\u0016\u0005\u0004\u0011\u0017!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070\u0006\u0002\u0003\u001cA!!Q\u0004B\u0014\u001b\t\u0011yB\u0003\u0003\u0003\"\t\r\u0012\u0001\u00027b]\u001eT!A!\n\u0002\t)\fg/Y\u0005\u0005\u0003[\u0013y\"\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0006\u0002\u0003.A\u0019QKa\f\n\u0007\tEbKA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000fF\u0002g\u0005oA\u0011\"a\u001e\u0019\u0003\u0003\u0005\rA!\f\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"A!\u0010\u0011\u000b\t}\"Q\t4\u000e\u0005\t\u0005#b\u0001B\"-\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\t\u001d#\u0011\t\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002f\t5\u0003\u0002CA<5\u0005\u0005\t\u0019\u00014\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u00057\u0011\u0019\u0006C\u0005\u0002xm\t\t\u00111\u0001\u0003.\u0005A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0003.\u00051Q-];bYN$B!!\u001a\u0003^!A\u0011qO\u000f\u0002\u0002\u0003\u0007a-A\u0006Nk2$\u0018N\\8nS\u0006d\u0007CA. '\u0011yBK!\u001a\u0011\t\t\u001d$QN\u0007\u0003\u0005SRAAa\u001b\u0003$\u0005\u0011\u0011n\\\u0005\u0004m\n%DC\u0001B1\u0003\u0015\t\u0007\u000f\u001d7z+\u0019\u0011)H! \u0003\u0002R!!q\u000fBH)!\u0011IHa!\u0003\n\n5\u0005CB.\u0001\u0005w\u0012y\bE\u0002`\u0005{\"Q\u0001`\u0011C\u0002\t\u00042a\u0018BA\t\u0015\t\u0017E1\u0001c\u0011\u0019q\u0018\u0005q\u0001\u0003\u0006BA\u0011\u0011AA\n\u0005w\u00129\t\u0005\u0005\u0002\u001c\u0005\u0005\"qPA\u0013\u0011\u001d\tY#\ta\u0002\u0005\u0017\u0003\u0002\"a\f\u0002:\tm\u0014Q\u0005\u0005\b\u0003\u000b\n\u00039AA$\u0011\u0019A\u0018\u00051\u0001\u0003|\t1Q\t\u001f9GC6,bA!&\u0003\"\n\u00156C\u0002\u0012U\u0005/\u00139\u000bE\u0004\\\u00053\u0013iJa)\n\u0007\tm5JA\tFqB|g.\u001a8uS\u0006dg)Y7jYf\u0004ba\u0017\u0001\u0003 \n\r\u0006cA0\u0003\"\u0012)AP\tb\u0001EB\u0019qL!*\u0005\u000b\u0005\u0014#\u0019\u00012\u0011\u000fm\u0013IK!(\u0003$&\u0019!1V&\u0003#!\u000b7oQ8oUV<\u0017\r^3Qe&|'/\u0001\u0005fq\u0016l\u0007\u000f\\1s\u0003\u0015\u0019\b/Y2f!)\t)Ma-\u0003 \n\r\u0016QE\u0005\u0005\u0005k\u000b9M\u0001\u000fNkR\f'\r\\3GS:LG/Z\"p_J$\u0017N\\1uK\u001aKW\r\u001c3\u0015\t\te&\u0011\u0019\u000b\u0005\u0005w\u0013y\fE\u0004\u0003>\n\u0012yJa)\u000e\u0003}AqAa,&\u0001\b\u0011\t\fC\u0004\u0003.\u0016\u0002\rAa(\u0003\u001d\r{gN[;hCR,\u0007K]5peB91La2\u0003 \n\r\u0016b\u0001Be\u0017\nIA)\u001b:jG\"dW\r^\u0001\u0010G>t'.^4bi\u00164\u0015-\\5msV\u0011!q\u001a\t\t\u0005#\u00149Na(\u0003$:\u00191La5\n\u0007\tU7*A\u0005ESJL7\r\u001b7fi&!!\u0011\u0013Bm\u0015\r\u0011)nS\u0001\u0011G>t'.^4bi\u00164\u0015-\\5ms\u0002\n!\u0002\u001d:fI&\u001cG/\u001b<f)\u0011\u0011\tOa>\u0015\t\t\r(1\u001f\t\b7\n\u0015(\u0011\u001eBR\u0013\r\u00119o\u0013\u0002\u0006!>d\u00170\u0019\t\u0005\u0005W\u0014yOD\u0002\u0003n\u001ej\u0011AI\u0005\u0005\u0005c\u00149NA\u0005QCJ\fW.\u001a;fe\"9!Q_\u0015A\u0004\u0005\u001d\u0013!\u00022bg&\u001c\bb\u0002B}S\u0001\u0007!\u0011^\u0001\na\u0006\u0014\u0018-\\3uKJ\f\u0011\u0002]8ti\u0016\u0014\u0018n\u001c:\u0015\r\t}%q`B\u0002\u0011\u001d\u0019\tA\u000ba\u0001\u0005S\fQ\u0001\u001d:j_JDqa!\u0002+\u0001\u0004\u00199!\u0001\u0005fm&$WM\\2f!\u0015i7\u0011\u0002BR\u0013\r\u0019Ya\u001e\u0002\u0010)J\fg/\u001a:tC\ndWm\u00148dK\n\u00192+\u001e4gS\u000eLWM\u001c;Ti\u0006$\u0018n\u001d;jGN1A\u0006VB\tS2\u0004RaWB\n\u0007+I1a!\u0004L!\r\u0011i\u000fL\u0001\u0007G>,h\u000e^:\u0016\u0005\t}\u0015aB2pk:$8\u000f\t\u000b\u0005\u0007+\u0019y\u0002C\u0004\u0004\u0018=\u0002\rAa(\u0002\u000b\u0011\u0002H.^:\u0015\t\rU1Q\u0005\u0005\b\u0007O\u0001\u0004\u0019AB\u000b\u0003\t!H/\u0001\u0004%i&lWm\u001d\u000b\u0005\u0007+\u0019i\u0003C\u0004\u00040E\u0002\r!!\n\u0002\u0003]$Ba!\u0006\u00044!I1q\u0003\u001a\u0011\u0002\u0003\u0007!qT\u000b\u0003\u0007oQCAa(\u0003\u0002Q\u0019ama\u000f\t\u0013\u0005]d'!AA\u0002\t5B\u0003BA3\u0007\u007fA\u0001\"a\u001e9\u0003\u0003\u0005\rA\u001a\u000b\u0005\u00057\u0019\u0019\u0005C\u0005\u0002xe\n\t\u00111\u0001\u0003.Q\u0011!1\u0004\u000b\u0005\u0003K\u001aI\u0005\u0003\u0005\u0002xq\n\t\u00111\u0001g\u0003M\u0019VO\u001a4jG&,g\u000e^*uCRL7\u000f^5d!\r\u0011iOP\n\u0006}\rE#Q\r\t\t\u0007'\u001aIFa(\u0004\u00165\u00111Q\u000b\u0006\u0004\u0007/2\u0016a\u0002:v]RLW.Z\u0005\u0005\u00077\u001a)FA\tBEN$(/Y2u\rVt7\r^5p]F\"\"a!\u0014\u0015\t\rU1\u0011\r\u0005\b\u0007/\t\u0005\u0019\u0001BP\u0003\u001d)h.\u00199qYf$Baa\u001a\u0004nA)Qk!\u001b\u0003 &\u001911\u000e,\u0003\r=\u0003H/[8o\u0011%\u0019yGQA\u0001\u0002\u0004\u0019)\"A\u0002yIA\n\u0001$Z7qif\u001cVO\u001a4jG&,g\u000e^*uCRL7\u000f^5d+\t\u0019)\"\u0001\ftk\u001a4\u0017nY5f]R\u001cF/\u0019;jgRL7MR8s)\u0011\u0019)b!\u001f\t\u000f\rmD\t1\u0001\u0003$\u0006\tA/A\u0002nY\u0016$BAa(\u0004\u0002\"1a*\u0012a\u0001\u0007+\t!\u0003\\5lK2L\u0007n\\8e\rVt7\r^5p]R!1qQBJ!\u0019\u0019Iia$\u0003 6\u001111\u0012\u0006\u0004\u0007\u001b{\u0015\u0001C8qi&l\u0017N_3\n\t\rE51\u0012\u0002\r\t&4gMR;oGRLwN\u001c\u0005\u0007\u001d\u001a\u0003\ra!\u0006\u0002\u0019\u0011L7\u000f\u001e:jEV$\u0018n\u001c8\u0015\t\re5Q\u0014\u000b\u0005\u0005;\u001bY\nC\u0004\u0002F\u001d\u0003\u001d!a\u0012\t\u000f\r}u\t1\u0001\u0004\"\u0006\t\u0001\u000fE\u0002\u0003n.*ba!*\u0004,\u000eMF\u0003BBT\u0007[\u0003R!VB5\u0007S\u00032aXBV\t\u0015a\bJ1\u0001c\u0011%\u0019y\u0007SA\u0001\u0002\u0004\u0019y\u000b\u0005\u0004\\\u0001\r%6\u0011\u0017\t\u0004?\u000eMF!B1I\u0005\u0004\u0011\u0017\u0001D<sSR,'+\u001a9mC\u000e,GCAB]!\u0011\u0011iba/\n\t\ru&q\u0004\u0002\u0007\u001f\nTWm\u0019;"
)
public class Multinomial implements DiscreteDistr, Product {
   private AliasTable aliasTable;
   private final Object params;
   private final Function1 ev;
   private final RandBasis rand;
   private final double sum;
   private boolean haveSampled;
   private volatile boolean bitmap$0;

   public static Option unapply(final Multinomial x$0) {
      return Multinomial$.MODULE$.unapply(x$0);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public double logProbabilityOf(final Object x) {
      return DiscreteDistr.logProbabilityOf$(this, x);
   }

   public double unnormalizedLogProbabilityOf(final Object x) {
      return DiscreteDistr.unnormalizedLogProbabilityOf$(this, x);
   }

   public double apply(final Object x) {
      return DiscreteDistr.apply$(this, x);
   }

   public double logApply(final Object x) {
      return DiscreteDistr.logApply$(this, x);
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

   public Object params() {
      return this.params;
   }

   public double sum() {
      return this.sum;
   }

   private boolean haveSampled() {
      return this.haveSampled;
   }

   private void haveSampled_$eq(final boolean x$1) {
      this.haveSampled = x$1;
   }

   private AliasTable aliasTable$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.aliasTable = this.buildAliasTable();
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.aliasTable;
   }

   private AliasTable aliasTable() {
      return !this.bitmap$0 ? this.aliasTable$lzycompute() : this.aliasTable;
   }

   public Object draw() {
      Object result = this.haveSampled() ? this.aliasTable().draw() : this.drawNaive();
      this.haveSampled_$eq(true);
      return result;
   }

   public Object drawNaive() {
      Object var1 = new Object();

      Object var10000;
      try {
         DoubleRef prob = DoubleRef.create(this.rand.uniform().draw$mcD$sp() * this.sum());
         .MODULE$.assert(!Double.isNaN(prob.elem), () -> "NaN Probability!");
         ((QuasiTensor)this.ev.apply(this.params())).activeIterator().withFilter((check$ifrefutable$2) -> BoxesRunTime.boxToBoolean($anonfun$drawNaive$2(check$ifrefutable$2))).foreach((x$2) -> {
            $anonfun$drawNaive$3(prob, var1, x$2);
            return BoxedUnit.UNIT;
         });
         var10000 = ((QuasiTensor)this.ev.apply(this.params())).activeKeysIterator().next();
      } catch (NonLocalReturnControl var4) {
         if (var4.key() != var1) {
            throw var4;
         }

         var10000 = var4.value();
      }

      return var10000;
   }

   private AliasTable buildAliasTable() {
      int nOutcomes = ((QuasiTensor)this.ev.apply(this.params())).iterator().length();
      DenseVector aliases = DenseVector$.MODULE$.zeros$mIc$sp(nOutcomes, scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());
      DenseVector probs = DenseVector$.MODULE$.apply$mDc$sp((double[])((QuasiTensor)this.ev.apply(this.params())).iterator().map((x0$1) -> BoxesRunTime.boxToDouble($anonfun$buildAliasTable$1(this, nOutcomes, x0$1))).toArray(scala.reflect.ClassTag..MODULE$.Double()));
      Tuple2 var6 = scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), nOutcomes).partition((JFunction1.mcZI.sp)(x$3) -> probs.apply$mcD$sp(x$3) < (double)1.0F);
      if (var6 == null) {
         throw new MatchError(var6);
      } else {
         IndexedSeq iSmaller = (IndexedSeq)var6._1();
         IndexedSeq iLarger = (IndexedSeq)var6._2();
         Tuple2 var1 = new Tuple2(iSmaller, iLarger);
         IndexedSeq iSmaller = (IndexedSeq)var1._1();
         IndexedSeq iLarger = (IndexedSeq)var1._2();
         Stack smaller = (Stack)scala.collection.mutable.Stack..MODULE$.apply(iSmaller);
         Stack larger = (Stack)scala.collection.mutable.Stack..MODULE$.apply(iLarger);

         while(smaller.nonEmpty() && larger.nonEmpty()) {
            int small = BoxesRunTime.unboxToInt(smaller.pop());
            int large = BoxesRunTime.unboxToInt(larger.pop());
            aliases.update$mcI$sp(small, large);
            probs.update$mcD$sp(large, probs.apply$mcD$sp(large) - ((double)1.0F - probs.apply$mcD$sp(small)));
            if (probs.apply$mcD$sp(large) < (double)1) {
               smaller.push(BoxesRunTime.boxToInteger(large));
            } else {
               larger.push(BoxesRunTime.boxToInteger(large));
            }
         }

         IndexedSeq outcomes = ((QuasiTensor)this.ev.apply(this.params())).keysIterator().toIndexedSeq();
         return new AliasTable(probs, aliases, outcomes, this.rand);
      }
   }

   public double probabilityOf(final Object e) {
      return !((QuasiTensor)this.ev.apply(this.params())).keySet().contains(e) ? (double)0.0F : BoxesRunTime.unboxToDouble(((QuasiTensor)this.ev.apply(this.params())).apply(e)) / this.sum();
   }

   public double unnormalizedProbabilityOf(final Object e) {
      return BoxesRunTime.unboxToDouble(((QuasiTensor)this.ev.apply(this.params())).apply(e));
   }

   public String toString() {
      return ((QuasiTensor)this.ev.apply(this.params())).activeIterator().mkString("Multinomial{", ",", "}");
   }

   public Object expectedValue(final Function1 f, final VectorSpace vs) {
      MutablizingAdaptor wrapped = MutablizingAdaptor$.MODULE$.ensureMutable(vs);
      ObjectRef acc = ObjectRef.create((Object)null);
      ((QuasiTensor)this.ev.apply(this.params())).activeIterator().withFilter((check$ifrefutable$3) -> BoxesRunTime.boxToBoolean($anonfun$expectedValue$1(check$ifrefutable$3))).foreach((x$5) -> {
         $anonfun$expectedValue$2(this, acc, wrapped, f, x$5);
         return BoxedUnit.UNIT;
      });
      .MODULE$.assert(acc.elem != null);
      return wrapped.unwrap(acc.elem);
   }

   public Multinomial copy(final Object params, final Function1 ev, final UFunc.UImpl sumImpl, final RandBasis rand) {
      return new Multinomial(params, ev, sumImpl, rand);
   }

   public Object copy$default$1() {
      return this.params();
   }

   public String productPrefix() {
      return "Multinomial";
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
      return x$1 instanceof Multinomial;
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

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label49: {
            boolean var2;
            if (x$1 instanceof Multinomial) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               Multinomial var4 = (Multinomial)x$1;
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

   // $FF: synthetic method
   public static final boolean $anonfun$new$2(final Tuple2 check$ifrefutable$1) {
      boolean var1;
      if (check$ifrefutable$1 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   // $FF: synthetic method
   public static final void $anonfun$new$3(final Tuple2 x$1) {
      if (x$1 != null) {
         Object k = x$1._1();
         double v = x$1._2$mcD$sp();
         if (v < (double)0) {
            throw new IllegalArgumentException((new StringBuilder(39)).append("Multinomial has negative mass at index ").append(k).toString());
         } else {
            BoxedUnit var1 = BoxedUnit.UNIT;
         }
      } else {
         throw new MatchError(x$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$drawNaive$2(final Tuple2 check$ifrefutable$2) {
      boolean var1;
      if (check$ifrefutable$2 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   // $FF: synthetic method
   public static final void $anonfun$drawNaive$3(final DoubleRef prob$1, final Object nonLocalReturnKey1$1, final Tuple2 x$2) {
      if (x$2 != null) {
         Object i = x$2._1();
         double w = x$2._2$mcD$sp();
         prob$1.elem -= w;
         if (prob$1.elem <= (double)0) {
            throw new NonLocalReturnControl(nonLocalReturnKey1$1, i);
         } else {
            BoxedUnit var3 = BoxedUnit.UNIT;
         }
      } else {
         throw new MatchError(x$2);
      }
   }

   // $FF: synthetic method
   public static final double $anonfun$buildAliasTable$1(final Multinomial $this, final int nOutcomes$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         double param = x0$1._2$mcD$sp();
         double var3 = param / $this.sum() * (double)nOutcomes$1;
         return var3;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$expectedValue$1(final Tuple2 check$ifrefutable$3) {
      boolean var1;
      if (check$ifrefutable$3 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   // $FF: synthetic method
   public static final void $anonfun$expectedValue$2(final Multinomial $this, final ObjectRef acc$1, final MutablizingAdaptor wrapped$1, final Function1 f$1, final Tuple2 x$5) {
      if (x$5 != null) {
         Object k = x$5._1();
         double v = x$5._2$mcD$sp();
         if (acc$1.elem == null) {
            acc$1.elem = ((ImmutableNumericOps)((Module)wrapped$1.mutaVspace()).hasOps().apply(wrapped$1.wrap(f$1.apply(k)))).$times(BoxesRunTime.boxToDouble(v / $this.sum()), ((Module)wrapped$1.mutaVspace()).mulVS_M());
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            package$.MODULE$.axpy(BoxesRunTime.boxToDouble(v / $this.sum()), wrapped$1.wrap(f$1.apply(k)), acc$1.elem, ((MutableModule)wrapped$1.mutaVspace()).scaleAddVV());
            BoxedUnit var10 = BoxedUnit.UNIT;
         }

      } else {
         throw new MatchError(x$5);
      }
   }

   public Multinomial(final Object params, final Function1 ev, final UFunc.UImpl sumImpl, final RandBasis rand) {
      this.params = params;
      this.ev = ev;
      this.rand = rand;
      Density.$init$(this);
      Rand.$init$(this);
      DiscreteDistr.$init$(this);
      Product.$init$(this);
      this.sum = BoxesRunTime.unboxToDouble(sum$.MODULE$.apply(params, sumImpl));
      .MODULE$.require(this.sum() != (double)0.0F, () -> "There's no mass!");
      this.haveSampled = false;
      ((QuasiTensor)ev.apply(params)).activeIterator().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$new$2(check$ifrefutable$1))).foreach((x$1) -> {
         $anonfun$new$3(x$1);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class ExpFam implements HasConjugatePrior {
      private volatile SufficientStatistic$ SufficientStatistic$module;
      private final Object exemplar;
      public final MutableFiniteCoordinateField breeze$stats$distributions$Multinomial$ExpFam$$space;
      private final Dirichlet.ExpFam conjugateFamily;

      public SufficientStatistic$ SufficientStatistic() {
         if (this.SufficientStatistic$module == null) {
            this.SufficientStatistic$lzycompute$1();
         }

         return this.SufficientStatistic$module;
      }

      public Dirichlet.ExpFam conjugateFamily() {
         return this.conjugateFamily;
      }

      public Polya predictive(final Object parameter, final RandBasis basis) {
         return new Polya(parameter, this.breeze$stats$distributions$Multinomial$ExpFam$$space, basis);
      }

      public Object posterior(final Object prior, final IterableOnce evidence) {
         Object localCopy = this.breeze$stats$distributions$Multinomial$ExpFam$$space.copy().apply(prior);
         evidence.iterator().foreach((e) -> {
            $anonfun$posterior$1(this, localCopy, e);
            return BoxedUnit.UNIT;
         });
         return localCopy;
      }

      public SufficientStatistic emptySufficientStatistic() {
         return new SufficientStatistic(this.breeze$stats$distributions$Multinomial$ExpFam$$space.zeroLike().apply(this.exemplar));
      }

      public SufficientStatistic sufficientStatisticFor(final Object t) {
         Object r = this.breeze$stats$distributions$Multinomial$ExpFam$$space.zeroLike().apply(this.exemplar);
         ((QuasiTensor)this.breeze$stats$distributions$Multinomial$ExpFam$$space.hasOps().apply(r)).update(t, BoxesRunTime.boxToDouble((double)1.0F));
         return new SufficientStatistic(r);
      }

      public Object mle(final SufficientStatistic stats) {
         return package.log$.MODULE$.apply(stats.counts(), HasOps$.MODULE$.fromLowOrderCanMapValues(this.breeze$stats$distributions$Multinomial$ExpFam$$space.scalarOf(), package$log$logDoubleImpl$.MODULE$, this.breeze$stats$distributions$Multinomial$ExpFam$$space.mapValues()));
      }

      public DiffFunction likelihoodFunction(final SufficientStatistic stats) {
         return new DiffFunction(stats) {
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

            public Tuple2 calculate(final Object x) {
               Object nn = logNormalize$.MODULE$.apply(x, logNormalize$.MODULE$.logNormalizeImpl(softmax$.MODULE$.reduceDouble(this.$outer.breeze$stats$distributions$Multinomial$ExpFam$$space.iterateValues(), max$.MODULE$.reduce_Double(this.$outer.breeze$stats$distributions$Multinomial$ExpFam$$space.iterateValues())), this.$outer.breeze$stats$distributions$Multinomial$ExpFam$$space.subVS()));
               double lp = BoxesRunTime.unboxToDouble(((ImmutableNumericOps)this.$outer.breeze$stats$distributions$Multinomial$ExpFam$$space.hasOps().apply(nn)).dot(this.stats$1.counts(), this.$outer.breeze$stats$distributions$Multinomial$ExpFam$$space.dotVV()));
               double mysum = BoxesRunTime.unboxToDouble(sum$.MODULE$.apply(this.stats$1.counts(), sum$.MODULE$.reduce_Double(this.$outer.breeze$stats$distributions$Multinomial$ExpFam$$space.iterateValues())));
               Object exped = package.exp$.MODULE$.apply(nn, HasOps$.MODULE$.fromLowOrderCanMapValues(this.$outer.breeze$stats$distributions$Multinomial$ExpFam$$space.scalarOf(), package$exp$expDoubleImpl$.MODULE$, this.$outer.breeze$stats$distributions$Multinomial$ExpFam$$space.mapValues()));
               Object grad = ((ImmutableNumericOps)this.$outer.breeze$stats$distributions$Multinomial$ExpFam$$space.hasOps().apply(((ImmutableNumericOps)this.$outer.breeze$stats$distributions$Multinomial$ExpFam$$space.hasOps().apply(exped)).$times(BoxesRunTime.boxToDouble(mysum), this.$outer.breeze$stats$distributions$Multinomial$ExpFam$$space.mulVS_M()))).$minus(this.stats$1.counts(), this.$outer.breeze$stats$distributions$Multinomial$ExpFam$$space.subVV());
               return new Tuple2(BoxesRunTime.boxToDouble(-lp), grad);
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
               }
            }
         };
      }

      public Multinomial distribution(final Object p, final RandBasis rand) {
         return new Multinomial(package.exp$.MODULE$.apply(p, HasOps$.MODULE$.fromLowOrderCanMapValues(this.breeze$stats$distributions$Multinomial$ExpFam$$space.scalarOf(), package$exp$expDoubleImpl$.MODULE$, this.breeze$stats$distributions$Multinomial$ExpFam$$space.mapValues())), this.breeze$stats$distributions$Multinomial$ExpFam$$space.hasOps(), sum$.MODULE$.reduce_Double(this.breeze$stats$distributions$Multinomial$ExpFam$$space.iterateValues()), rand);
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

      // $FF: synthetic method
      public static final void $anonfun$posterior$1(final ExpFam $this, final Object localCopy$1, final Object e) {
         NumericOps var3 = (NumericOps)$this.breeze$stats$distributions$Multinomial$ExpFam$$space.hasOps().apply(localCopy$1);
         ((QuasiTensor)var3).update(e, BoxesRunTime.boxToDouble(BoxesRunTime.unboxToDouble(((QuasiTensor)var3).apply(e)) + (double)1.0F));
      }

      public ExpFam(final Object exemplar, final MutableFiniteCoordinateField space) {
         this.exemplar = exemplar;
         this.breeze$stats$distributions$Multinomial$ExpFam$$space = space;
         this.conjugateFamily = new Dirichlet.ExpFam(exemplar, space);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }

      public class SufficientStatistic implements SufficientStatistic, Product, Serializable {
         private final Object counts;
         // $FF: synthetic field
         public final ExpFam $outer;

         public Iterator productElementNames() {
            return Product.productElementNames$(this);
         }

         public Object counts() {
            return this.counts;
         }

         public SufficientStatistic $plus(final SufficientStatistic tt) {
            return this.breeze$stats$distributions$Multinomial$ExpFam$SufficientStatistic$$$outer().new SufficientStatistic(((NumericOps)this.breeze$stats$distributions$Multinomial$ExpFam$SufficientStatistic$$$outer().breeze$stats$distributions$Multinomial$ExpFam$$space.hasOps().apply(this.counts())).$plus(tt.counts(), this.breeze$stats$distributions$Multinomial$ExpFam$SufficientStatistic$$$outer().breeze$stats$distributions$Multinomial$ExpFam$$space.addVV()));
         }

         public SufficientStatistic $times(final double w) {
            return this.breeze$stats$distributions$Multinomial$ExpFam$SufficientStatistic$$$outer().new SufficientStatistic(((ImmutableNumericOps)this.breeze$stats$distributions$Multinomial$ExpFam$SufficientStatistic$$$outer().breeze$stats$distributions$Multinomial$ExpFam$$space.hasOps().apply(this.counts())).$times(BoxesRunTime.boxToDouble(w), this.breeze$stats$distributions$Multinomial$ExpFam$SufficientStatistic$$$outer().breeze$stats$distributions$Multinomial$ExpFam$$space.mulVS_M()));
         }

         public SufficientStatistic copy(final Object counts) {
            return this.breeze$stats$distributions$Multinomial$ExpFam$SufficientStatistic$$$outer().new SufficientStatistic(counts);
         }

         public Object copy$default$1() {
            return this.counts();
         }

         public String productPrefix() {
            return "SufficientStatistic";
         }

         public int productArity() {
            return 1;
         }

         public Object productElement(final int x$1) {
            Object var10000;
            switch (x$1) {
               case 0:
                  var10000 = this.counts();
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
                  var10000 = "counts";
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
               label54: {
                  boolean var2;
                  if (x$1 instanceof SufficientStatistic && ((SufficientStatistic)x$1).breeze$stats$distributions$Multinomial$ExpFam$SufficientStatistic$$$outer() == this.breeze$stats$distributions$Multinomial$ExpFam$SufficientStatistic$$$outer()) {
                     var2 = true;
                  } else {
                     var2 = false;
                  }

                  if (var2) {
                     SufficientStatistic var4 = (SufficientStatistic)x$1;
                     if (BoxesRunTime.equals(this.counts(), var4.counts()) && var4.canEqual(this)) {
                        break label54;
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
         public ExpFam breeze$stats$distributions$Multinomial$ExpFam$SufficientStatistic$$$outer() {
            return this.$outer;
         }

         public SufficientStatistic(final Object counts) {
            this.counts = counts;
            if (ExpFam.this == null) {
               throw null;
            } else {
               this.$outer = ExpFam.this;
               super();
               Product.$init$(this);
            }
         }
      }

      public class SufficientStatistic$ extends AbstractFunction1 implements Serializable {
         // $FF: synthetic field
         private final ExpFam $outer;

         public final String toString() {
            return "SufficientStatistic";
         }

         public SufficientStatistic apply(final Object counts) {
            return this.$outer.new SufficientStatistic(counts);
         }

         public Option unapply(final SufficientStatistic x$0) {
            return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.counts()));
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
