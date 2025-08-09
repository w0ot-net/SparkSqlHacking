package scala.collection.immutable;

import java.io.Serializable;
import java.util.NoSuchElementException;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.AbstractIterator;
import scala.collection.IndexedSeqView;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.collection.Searching;
import scala.collection.SeqFactory;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.StepperShape$;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.convert.impl.AnyNumericRangeStepper;
import scala.collection.convert.impl.IntNumericRangeStepper;
import scala.collection.convert.impl.LongNumericRangeStepper;
import scala.collection.generic.CommonErrors$;
import scala.collection.mutable.Builder;
import scala.math.Integral;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.util.hashing.MurmurHash3$;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011ud\u0001B*U!mC!\"a\u0004\u0001\u0005\u000b\u0007I\u0011AA\t\u0011%\t\u0019\u0002\u0001B\u0001B\u0003%\u0011\r\u0003\u0006\u0002\u0016\u0001\u0011)\u0019!C\u0001\u0003#A\u0011\"a\u0006\u0001\u0005\u0003\u0005\u000b\u0011B1\t\u0015\u0005e\u0001A!b\u0001\n\u0003\t\t\u0002C\u0005\u0002\u001c\u0001\u0011\t\u0011)A\u0005C\"Q\u0011Q\u0004\u0001\u0003\u0006\u0004%\t!a\b\t\u0015\u0005\u001d\u0002A!A!\u0002\u0013\t\t\u0003\u0003\u0006\u0002*\u0001\u0011\t\u0011)A\u0006\u0003WAq!!\r\u0001\t\u0003\t\u0019\u0004C\u0004\u0002D\u0001!\t%!\u0012\t\u000f\u00055\u0003\u0001\"\u0011\u0002P!Q\u00111\u0012\u0001\t\u0006\u0004%\t%!$\t\u0015\u0005U\u0005\u0001#b\u0001\n\u0003\ny\u0002C\u0004\u0002\u0018\u0002!\t%!\u0005\t\u000f\u0005e\u0005\u0001\"\u0011\u0002\u001c\"9\u0011Q\u0014\u0001\u0005B\u0005E\u0001bBAP\u0001\u0011\u0005\u00131\u0014\u0005\b\u0003C\u0003A\u0011AAR\u0011\u001d\tI\u000b\u0001C\u0001\u0003WCq!a-\u0001\t\u0003\t)\fC\u0004\u0003\u0002\u0001!\tEa\u0001\t\u0011\t\r\u0003\u0001)C\u0005\u0005\u000bBqAa\u0014\u0001\t\u000b\u0012\t\u0006\u0003\u0005\u0003b\u0001\u0001K\u0011\u0002B2\u0011\u001d\u0011I\u0007\u0001C#\u0005WB\u0011Ba\u001e\u0001#\u0003%)A!\u001f\t\u0011\tM\u0005\u0001)C\u0005\u0005+CqAa'\u0001\t\u0013\u0011i\nC\u0004\u0003\"\u0002!IAa)\t\u000f\t%\u0006\u0001\"\u0003\u0003,\"9!q\u0016\u0001\u0005\n\tE\u0006bBB\u0000\u0001\u0011\u0005C\u0011\u0001\u0005\b\t\u000b\u0001A\u0011\tC\u0004\u0011\u001d!Y\u0001\u0001C!\t\u001bAq\u0001b\u0006\u0001\t\u0003\nY\nC\u0004\u0005\u001a\u0001!\t\u0005b\u0007\t\u000f\u00115\u0002\u0001\"\u0011\u00050!9A1\b\u0001\u0005\u0002\u0011u\u0002b\u0002C\"\u0001\u0011\u0005CQ\t\u0005\b\t#\u0002A\u0011\tC*\u0011)!\t\u0007\u0001EC\u0002\u0013\u0005\u0013Q\u0012\u0005\b\tG\u0002AQKAG\u0011\u001d!)\u0007\u0001C!\tOBq\u0001\"\u001c\u0001\t\u0003\"y\u0007\u0003\u0005\u0005r\u0001\u0001K\u0011\u000bC:\u000f\u001d\u00119\f\u0016E\u0001\u0005s3aa\u0015+\t\u0002\tm\u0006bBA\u0019a\u0011\u0005!\u0011\u001b\u0005\b\u0005'\u0004D\u0011\u0002Bk\u0011\u001d\u0011I\u000f\rC\u0001\u0005W4aa!\u00011\u0001\r\r\u0001bCA\bi\t\u0005\t\u0015!\u0003\u0004\n\u0005A1\"!\u00065\u0005\u0003\u0005\u000b\u0011BB\u0005\u0007!Y\u0011\u0011\u0004\u001b\u0003\u0002\u0003\u0006Ia!\u0003\u0006\u0011)\tI\u0003\u000eB\u0001B\u0003-1Q\u0002\u0005\b\u0003c!D\u0011AB\b\u0011\u001d\tI\u000b\u000eC!\u0007?Aqaa\n5\t\u0003\u0019IC\u0002\u0004\u0004.A\u00021q\u0006\u0005\f\u0003\u001fa$\u0011!Q\u0001\n\rU\u0012\u0001C\u0006\u0002\u0016q\u0012\t\u0011)A\u0005\u0007k\u0019\u0001bCA\ry\t\u0005\t\u0015!\u0003\u00046\u0015A!\"!\u000b=\u0005\u0003\u0005\u000b1BB\u001d\u0011\u001d\t\t\u0004\u0010C\u0001\u0007wAq!!+=\t\u0003\u001aI\u0005C\u0004\u0004Rq\"\taa\u0015\t\u000f\u0005M\u0006\u0007\"\u0001\u0004f!91\u0011\u000b\u0019\u0005\u0002\rm\u0004BCBIa\t\u0007I\u0011\u0001,\u0004\u0014\"A1q\u0016\u0019!\u0002\u0013\u0019)J\u0002\u0004\u0004@B21\u0011\u0019\u0005\u000b\u0007\u001fD%\u0011!Q\u0001\n\rE\u0007BCA\u0015\u0011\n\u0005\t\u0015!\u0003\u0004T\"9\u0011\u0011\u0007%\u0005\u0002\rU\u0007\u0002CBo\u0011\u0002\u0006K!!\t\t\u0011\r}\u0007\n)Q\u0005\u0007\u0017D\u0001b!9IA\u0003%11\u001a\u0005\b\u0007GDE\u0011IAG\u0011\u001d\u0019)\u000f\u0013C\u0001\u0003?Aqaa:I\t\u0003\u0019I\u000fC\u0005\u0004nB\n\t\u0011\"\u0003\u0004p\naa*^7fe&\u001c'+\u00198hK*\u0011QKV\u0001\nS6lW\u000f^1cY\u0016T!a\u0016-\u0002\u0015\r|G\u000e\\3di&|gNC\u0001Z\u0003\u0015\u00198-\u00197b\u0007\u0001)\"\u0001X2\u0014\u000f\u0001iV\u000e\u001d;xwB\u0019alX1\u000e\u0003QK!\u0001\u0019+\u0003\u0017\u0005\u00137\u000f\u001e:bGR\u001cV-\u001d\t\u0003E\u000ed\u0001\u0001B\u0003e\u0001\t\u0007QMA\u0001U#\t1'\u000e\u0005\u0002hQ6\t\u0001,\u0003\u0002j1\n9aj\u001c;iS:<\u0007CA4l\u0013\ta\u0007LA\u0002B]f\u00042A\u00188b\u0013\tyGK\u0001\u0006J]\u0012,\u00070\u001a3TKF\u0004RAX9bg6L!A\u001d+\u0003\u001b%sG-\u001a=fIN+\u0017o\u00149t!\tqf\u000eE\u0003_k\u0006\u001cX.\u0003\u0002w)\n)2\u000b\u001e:jGR|\u0005\u000f^5nSj,GmU3r\u001fB\u001c\b\u0003\u0002=zCNl\u0011AV\u0005\u0003uZ\u0013q#\u0013;fe\u0006\u0014G.\u001a$bGR|'/\u001f#fM\u0006,H\u000e^:\u0011\u0007q\fIAD\u0002~\u0003\u000bq1A`A\u0002\u001b\u0005y(bAA\u00015\u00061AH]8pizJ\u0011!W\u0005\u0004\u0003\u000fA\u0016a\u00029bG.\fw-Z\u0005\u0005\u0003\u0017\tiA\u0001\u0007TKJL\u0017\r\\5{C\ndWMC\u0002\u0002\ba\u000bQa\u001d;beR,\u0012!Y\u0001\u0007gR\f'\u000f\u001e\u0011\u0002\u0007\u0015tG-\u0001\u0003f]\u0012\u0004\u0013\u0001B:uKB\fQa\u001d;fa\u0002\n1\"[:J]\u000edWo]5wKV\u0011\u0011\u0011\u0005\t\u0004O\u0006\r\u0012bAA\u00131\n9!i\\8mK\u0006t\u0017\u0001D5t\u0013:\u001cG.^:jm\u0016\u0004\u0013a\u00018v[B!A0!\fb\u0013\u0011\ty#!\u0004\u0003\u0011%sG/Z4sC2\fa\u0001P5oSRtDCCA\u001b\u0003w\ti$a\u0010\u0002BQ!\u0011qGA\u001d!\rq\u0006!\u0019\u0005\b\u0003SQ\u00019AA\u0016\u0011\u0019\tyA\u0003a\u0001C\"1\u0011Q\u0003\u0006A\u0002\u0005Da!!\u0007\u000b\u0001\u0004\t\u0007bBA\u000f\u0015\u0001\u0007\u0011\u0011E\u0001\tSR,'/\u0019;peV\u0011\u0011q\t\t\u0005q\u0006%\u0013-C\u0002\u0002LY\u0013\u0001\"\u0013;fe\u0006$xN]\u0001\bgR,\u0007\u000f]3s+\u0011\t\t&a\u0017\u0015\t\u0005M\u0013\u0011\u0011\n\u0007\u0003+\nI&a\u001c\u0007\r\u0005]\u0003\u0001AA*\u00051a$/\u001a4j]\u0016lWM\u001c;?!\r\u0011\u00171\f\u0003\b\u0003;b!\u0019AA0\u0005\u0005\u0019\u0016c\u00014\u0002bA\"\u00111MA6!\u0015A\u0018QMA5\u0013\r\t9G\u0016\u0002\b'R,\u0007\u000f]3s!\r\u0011\u00171\u000e\u0003\f\u0003[\nY&!A\u0001\u0002\u000b\u0005QMA\u0002`IE\u0002B!!\u001d\u0002|9!\u00111OA<\u001d\ri\u0018QO\u0005\u0003/bK1!!\u001fW\u0003\u001d\u0019F/\u001a9qKJLA!! \u0002\u0000\tqQI\u001a4jG&,g\u000e^*qY&$(bAA=-\"9\u00111\u0011\u0007A\u0004\u0005\u0015\u0015!B:iCB,\u0007C\u0002=\u0002\b\u0006\fI&C\u0002\u0002\nZ\u0013Ab\u0015;faB,'o\u00155ba\u0016\fa\u0001\\3oORDWCAAH!\r9\u0017\u0011S\u0005\u0004\u0003'C&aA%oi\u00069\u0011n]#naRL\u0018\u0001\u00027bgR\fA!\u001b8jiV\u0011\u0011qG\u0001\u0005Q\u0016\fG-\u0001\u0003uC&d\u0017A\u00012z)\u0011\t9$!*\t\r\u0005\u001d6\u00031\u0001b\u0003\u001dqWm^*uKB\fAaY8qsRA\u0011qGAW\u0003_\u000b\t\f\u0003\u0004\u0002\u0010Q\u0001\r!\u0019\u0005\u0007\u0003+!\u0002\u0019A1\t\r\u0005eA\u00031\u0001b\u0003\u0015\t\u0007\u000f\u001d7z)\r\t\u0017q\u0017\u0005\b\u0003s+\u0002\u0019AAH\u0003\rIG\r\u001f\u0015\u0006+\u0005u\u0016\u0011\u001a\t\u0006O\u0006}\u00161Y\u0005\u0004\u0003\u0003D&A\u0002;ie><8\u000fE\u0002}\u0003\u000bLA!a2\u0002\u000e\tI\u0012J\u001c3fq>+Ho\u00144C_VtGm]#yG\u0016\u0004H/[8oc\u001dq\u00121ZAn\u0003\u007f\u0004B!!4\u0002V:!\u0011qZAi!\tq\b,C\u0002\u0002Tb\u000ba\u0001\u0015:fI\u00164\u0017\u0002BAl\u00033\u0014aa\u0015;sS:<'bAAj1FJ1%!8\u0002d\u0006U\u0018Q]\u000b\u0005\u0003?\f\t/\u0006\u0002\u0002L\u00121AM\u0017b\u0001\u0003WLA!!:\u0002h\u0006YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIER1!!;Y\u0003\u0019!\bN]8xgF\u0019a-!<\u0011\t\u0005=\u0018\u0011\u001f\b\u0004O\u0006\u0015\u0011\u0002BAz\u0003\u001b\u0011\u0011\u0002\u00165s_^\f'\r\\32\u0013\r\n90!?\u0002|\u0006%hbA4\u0002z&\u0019\u0011\u0011\u001e-2\u000b\t:\u0007,!@\u0003\u000bM\u001c\u0017\r\\12\u0007\u0019\n\u0019-A\u0004g_J,\u0017m\u00195\u0016\t\t\u0015!\u0011\u0004\u000b\u0005\u0005\u000f\u0011i\u0001E\u0002h\u0005\u0013I1Aa\u0003Y\u0005\u0011)f.\u001b;\t\u000f\t=a\u00031\u0001\u0003\u0012\u0005\ta\r\u0005\u0004h\u0005'\t'qC\u0005\u0004\u0005+A&!\u0003$v]\u000e$\u0018n\u001c82!\r\u0011'\u0011\u0004\u0003\u000b\u000571\u0002\u0015!A\u0001\u0006\u0004)'!A+)\r\te!q\u0004B\u0013!\r9'\u0011E\u0005\u0004\u0005GA&aC:qK\u000eL\u0017\r\\5{K\u0012\f\u0014b\tB\u0014\u0005w\u0011yD!\u0010\u0011\r\t%\"q\u0006B\u001b\u001d\r9'1F\u0005\u0004\u0005[A\u0016!D*qK\u000eL\u0017\r\\5{C\ndW-\u0003\u0003\u00032\tM\"!B$s_V\u0004(b\u0001B\u00171B)qMa\u000e\u0003\b%\u0019!\u0011\b-\u0003\rQ+\b\u000f\\32\u0013\u0011\u0011iDa\r\u0002\tUs\u0017\u000e^\u0019\nG\t%\"1\u0006B!\u0005[\tT\u0001J?\u0002\u0004e\u000bA\"\u001b8eKb|e\rV=qK\u0012$b!a$\u0003H\t-\u0003B\u0002B%/\u0001\u0007\u0011-\u0001\u0003fY\u0016l\u0007b\u0002B'/\u0001\u0007\u0011qR\u0001\u0005MJ|W.A\u0004j]\u0012,\u0007p\u00144\u0016\t\tM#\u0011\f\u000b\u0007\u0003\u001f\u0013)Fa\u0018\t\u000f\t%\u0003\u00041\u0001\u0003XA\u0019!M!\u0017\u0005\u000f\tm\u0003D1\u0001\u0003^\t\t!)\u0005\u0002bU\"9!Q\n\rA\u0002\u0005=\u0015\u0001\u00057bgRLe\u000eZ3y\u001f\u001a$\u0016\u0010]3e)\u0019\tyI!\u001a\u0003h!1!\u0011J\rA\u0002\u0005Dq!!\u0006\u001a\u0001\u0004\ty)A\u0006mCN$\u0018J\u001c3fq>3W\u0003\u0002B7\u0005g\"b!a$\u0003p\tU\u0004b\u0002B%5\u0001\u0007!\u0011\u000f\t\u0004E\nMDa\u0002B.5\t\u0007!Q\f\u0005\n\u0003+Q\u0002\u0013!a\u0001\u0003\u001f\u000bQ\u0003\\1ti&sG-\u001a=PM\u0012\"WMZ1vYR$#'\u0006\u0003\u0003|\tEUC\u0001B?U\u0011\tyIa ,\u0005\t\u0005\u0005\u0003\u0002BB\u0005\u001bk!A!\"\u000b\t\t\u001d%\u0011R\u0001\nk:\u001c\u0007.Z2lK\u0012T1Aa#Y\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0005\u001f\u0013)IA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016$qAa\u0017\u001c\u0005\u0004\u0011i&A\u0003q_N|e\r\u0006\u0003\u0002\u0010\n]\u0005B\u0002BM9\u0001\u0007\u0011-A\u0001j\u0003II7oV5uQ&t'i\\;oI\u0006\u0014\u0018.Z:\u0015\t\u0005\u0005\"q\u0014\u0005\u0007\u0005\u0013j\u0002\u0019A1\u0002\u001d1|7-\u0019;j_:\fe\r^3s\u001dR\u0019\u0011M!*\t\u000f\t\u001df\u00041\u0001\u0002\u0010\u0006\ta.A\nde>\u001c8/Z:UQ\u0016,e\u000eZ!gi\u0016\u0014h\n\u0006\u0003\u0002\"\t5\u0006b\u0002BT?\u0001\u0007\u0011qR\u0001\u000e]\u0016<X)\u001c9usJ\u000bgnZ3\u0015\t\tM6Q \t\u0005\u0005kc\u0014M\u0004\u0002__\u0005aa*^7fe&\u001c'+\u00198hKB\u0011a\fM\n\u0006a\tu&1\u0019\t\u0004O\n}\u0016b\u0001Ba1\n1\u0011I\\=SK\u001a\u0004BA!2\u0003P6\u0011!q\u0019\u0006\u0005\u0005\u0013\u0014Y-\u0001\u0002j_*\u0011!QZ\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002\f\t\u001dGC\u0001B]\u0003a\u0011\u0017n\u001a#fG&l\u0017\r\\\"iK\u000e\\WK\u001c3fe\u001adwn^\u000b\u0005\u0005/\u0014\t\u000f\u0006\u0005\u0003Z\n\r(Q\u001dBt)\u0011\u00119Aa7\t\u000f\u0005%\"\u0007q\u0001\u0003^B)A0!\f\u0003`B\u0019!M!9\u0005\u000b\u0011\u0014$\u0019A3\t\u000f\u0005=!\u00071\u0001\u0003`\"9\u0011Q\u0003\u001aA\u0002\t}\u0007bBA\re\u0001\u0007!q\\\u0001\u0006G>,h\u000e^\u000b\u0005\u0005[\u00149\u0010\u0006\u0006\u0003p\ne(1 B\u007f\u0005\u007f$B!a$\u0003r\"9\u0011\u0011F\u001aA\u0004\tM\b#\u0002?\u0002.\tU\bc\u00012\u0003x\u0012)Am\rb\u0001K\"9\u0011qB\u001aA\u0002\tU\bbBA\u000bg\u0001\u0007!Q\u001f\u0005\b\u00033\u0019\u0004\u0019\u0001B{\u0011\u001d\tib\ra\u0001\u0003C\u0011\u0011\"\u00138dYV\u001c\u0018N^3\u0016\t\r\u001511B\n\u0004i\r\u001d\u0001\u0003\u00020\u0001\u0007\u0013\u00012AYB\u0006\t\u0015!GG1\u0001f!\u0015a\u0018QFB\u0005)!\u0019\tb!\u0007\u0004\u001c\ruA\u0003BB\n\u0007/\u0001Ra!\u00065\u0007\u0013i\u0011\u0001\r\u0005\b\u0003SI\u00049AB\u0007\u0011\u001d\ty!\u000fa\u0001\u0007\u0013Aq!!\u0006:\u0001\u0004\u0019I\u0001C\u0004\u0002\u001ae\u0002\ra!\u0003\u0015\u0011\rM1\u0011EB\u0012\u0007KAq!a\u0004;\u0001\u0004\u0019I\u0001C\u0004\u0002\u0016i\u0002\ra!\u0003\t\u000f\u0005e!\b1\u0001\u0004\n\u0005IQ\r_2mkNLg/Z\u000b\u0003\u0007W\u0001Ra!\u0006=\u0007\u0013\u0011\u0011\"\u0012=dYV\u001c\u0018N^3\u0016\t\rE2qG\n\u0004y\rM\u0002\u0003\u00020\u0001\u0007k\u00012AYB\u001c\t\u0015!GH1\u0001f!\u0015a\u0018QFB\u001b)!\u0019ida\u0011\u0004F\r\u001dC\u0003BB \u0007\u0003\u0002Ra!\u0006=\u0007kAq!!\u000bB\u0001\b\u0019I\u0004C\u0004\u0002\u0010\u0005\u0003\ra!\u000e\t\u000f\u0005U\u0011\t1\u0001\u00046!9\u0011\u0011D!A\u0002\rUB\u0003CB \u0007\u0017\u001aiea\u0014\t\u000f\u0005=!\t1\u0001\u00046!9\u0011Q\u0003\"A\u0002\rU\u0002bBA\r\u0005\u0002\u00071QG\u0001\nS:\u001cG.^:jm\u0016,\"a!\u0016\u0011\u000b\rUAg!\u000e)\u000fq\u001aIfa\u0018\u0004bA\u0019qma\u0017\n\u0007\ru\u0003L\u0001\tTKJL\u0017\r\u001c,feNLwN\\+J\t\u0006)a/\u00197vKz\t1\u0001K\u00045\u00073\u001ayf!\u0019\u0016\t\r\u001d4q\u000e\u000b\t\u0007S\u001a)ha\u001e\u0004zQ!11NB9!\u0015\u0019)\u0002PB7!\r\u00117q\u000e\u0003\u0006I\u0012\u0013\r!\u001a\u0005\b\u0003S!\u00059AB:!\u0015a\u0018QFB7\u0011\u001d\ty\u0001\u0012a\u0001\u0007[Bq!!\u0006E\u0001\u0004\u0019i\u0007C\u0004\u0002\u001a\u0011\u0003\ra!\u001c\u0016\t\ru4Q\u0011\u000b\t\u0007\u007f\u001aYi!$\u0004\u0010R!1\u0011QBD!\u0015\u0019)\u0002NBB!\r\u00117Q\u0011\u0003\u0006I\u0016\u0013\r!\u001a\u0005\b\u0003S)\u00059ABE!\u0015a\u0018QFBB\u0011\u001d\ty!\u0012a\u0001\u0007\u0007Cq!!\u0006F\u0001\u0004\u0019\u0019\tC\u0004\u0002\u001a\u0015\u0003\raa!\u0002\u001f\u0011,g-Y;mi>\u0013H-\u001a:j]\u001e,\"a!&\u0011\u000fy\u001b9ja'\u00042&\u00191\u0011\u0014+\u0003\u00075\u000b\u0007\u000f\r\u0003\u0004\u001e\u000e-\u0006CBBP\u0007K\u001bI+\u0004\u0002\u0004\"*\u001911\u0015-\u0002\t5\fG\u000f[\u0005\u0005\u0007O\u001b\tKA\u0004Ok6,'/[2\u0011\u0007\t\u001cY\u000b\u0002\u0006\u0004.\u001e\u000b\t\u0011!A\u0003\u0002\u0015\u00141a\u0018\u00133\u0003A!WMZ1vYR|%\u000fZ3sS:<\u0007\u0005\r\u0003\u00044\u000em\u0006CBBP\u0007k\u001bI,\u0003\u0003\u00048\u000e\u0005&\u0001C(sI\u0016\u0014\u0018N\\4\u0011\u0007\t\u001cY\f\u0002\u0006\u0004>\u001e\u000b\t\u0011!A\u0003\u0002\u0015\u00141a\u0018\u00134\u0005QqU/\\3sS\u000e\u0014\u0016M\\4f\u0013R,'/\u0019;peV!11YBg'\u0011A5QY>\u0011\u000ba\u001c9ma3\n\u0007\r%gK\u0001\tBEN$(/Y2u\u0013R,'/\u0019;peB\u0019!m!4\u0005\u000b\u0011D%\u0019A3\u0002\tM,GN\u001a\t\u0005=\u0002\u0019Y\rE\u0003}\u0003[\u0019Y\r\u0006\u0004\u0004X\u000ee71\u001c\t\u0006\u0007+A51\u001a\u0005\b\u0007\u001f\\\u0005\u0019ABi\u0011\u001d\tIc\u0013a\u0001\u0007'\f\u0001b\u00185bg:+\u0007\u0010^\u0001\u0006?:,\u0007\u0010^\u0001\fY\u0006\u001cH/\u00127f[\u0016tG/A\u0005l]><hnU5{K\u00069\u0001.Y:OKb$\u0018\u0001\u00028fqR$\"aa3)\u000f!\u001bIfa\u0018\u0004b\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u00111\u0011\u001f\t\u0005\u0007g\u001cI0\u0004\u0002\u0004v*!1q\u001fBf\u0003\u0011a\u0017M\\4\n\t\rm8Q\u001f\u0002\u0007\u001f\nTWm\u0019;\t\r\r}\u0003\u00051\u0001b\u0003\u0011!\u0018m[3\u0015\t\u0005]B1\u0001\u0005\b\u0005O\u000b\u0003\u0019AAH\u0003\u0011!'o\u001c9\u0015\t\u0005]B\u0011\u0002\u0005\b\u0005O\u0013\u0003\u0019AAH\u0003\u001d\u0019\b\u000f\\5u\u0003R$B\u0001b\u0004\u0005\u0016A9q\r\"\u0005\u00028\u0005]\u0012b\u0001C\n1\n1A+\u001e9mKJBqAa*$\u0001\u0004\ty)A\u0004sKZ,'o]3\u0002\u00075Lg.\u0006\u0003\u0005\u001e\u0011%BcA1\u0005 !9A\u0011E\u0013A\u0004\u0011\r\u0012aA8sIB)A\u0010\"\n\u0005(%!1qWA\u0007!\r\u0011G\u0011\u0006\u0003\b\tW)#\u0019\u0001B/\u0005\t!\u0016'A\u0002nCb,B\u0001\"\r\u0005:Q\u0019\u0011\rb\r\t\u000f\u0011\u0005b\u0005q\u0001\u00056A)A\u0010\"\n\u00058A\u0019!\r\"\u000f\u0005\u000f\u0011-bE1\u0001\u0003^\u0005i1m\u001c8uC&t7\u000fV=qK\u0012$B!!\t\u0005@!1A\u0011I\u0014A\u0002\u0005\f\u0011\u0001_\u0001\tG>tG/Y5ogV!Aq\tC')\u0011\t\t\u0003\"\u0013\t\u000f\u0011\u0005\u0003\u00061\u0001\u0005LA\u0019!\r\"\u0014\u0005\u000f\u0011=\u0003F1\u0001\u0003^\t\u0011\u0011)M\u0001\u0004gVlW\u0003\u0002C+\t3\"B\u0001b\u0016\u0005\\A\u0019!\r\"\u0017\u0005\u000f\tm\u0013F1\u0001\u0003^!9\u0011\u0011F\u0015A\u0004\u0011u\u0003#\u0002?\u0005`\u0011]\u0013\u0002BBT\u0003\u001b\t\u0001\u0002[1tQ\u000e{G-Z\u0001\u0018CB\u0004H.\u001f)sK\u001a,'O]3e\u001b\u0006DH*\u001a8hi\"\fa!Z9vC2\u001cH\u0003BA\u0011\tSBa\u0001b\u001b-\u0001\u0004Q\u0017!B8uQ\u0016\u0014\u0018\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005-\u0017!C2mCN\u001ch*Y7f+\t!)\b\u0005\u0003\u0004t\u0012]\u0014\u0002BAl\u0007kL3\u0001\u0001\u001f5Q\u001d\u00011\u0011LB0\u0007C\u0002"
)
public class NumericRange extends AbstractSeq implements IndexedSeq, StrictOptimizedSeqOps, Serializable {
   private static final long serialVersionUID = 3L;
   private int length;
   private boolean isEmpty;
   private int hashCode;
   private final Object start;
   private final Object end;
   private final Object step;
   private final boolean isInclusive;
   public final Integral scala$collection$immutable$NumericRange$$num;
   private volatile byte bitmap$0;

   public static Inclusive inclusive(final Object start, final Object end, final Object step, final Integral num) {
      NumericRange$ var10000 = NumericRange$.MODULE$;
      return new Inclusive(start, end, step, num);
   }

   // $FF: synthetic method
   public Object scala$collection$immutable$StrictOptimizedSeqOps$$super$sorted(final Ordering ord) {
      return scala.collection.SeqOps.sorted$(this, ord);
   }

   public Object distinctBy(final Function1 f) {
      return StrictOptimizedSeqOps.distinctBy$(this, f);
   }

   public Object updated(final int index, final Object elem) {
      return StrictOptimizedSeqOps.updated$(this, index, elem);
   }

   public Object patch(final int from, final IterableOnce other, final int replaced) {
      return StrictOptimizedSeqOps.patch$(this, from, other, replaced);
   }

   public Object sorted(final Ordering ord) {
      return StrictOptimizedSeqOps.sorted$(this, ord);
   }

   public Object prepended(final Object elem) {
      return scala.collection.StrictOptimizedSeqOps.prepended$(this, elem);
   }

   public Object appended(final Object elem) {
      return scala.collection.StrictOptimizedSeqOps.appended$(this, elem);
   }

   public Object appendedAll(final IterableOnce suffix) {
      return scala.collection.StrictOptimizedSeqOps.appendedAll$(this, suffix);
   }

   public Object prependedAll(final IterableOnce prefix) {
      return scala.collection.StrictOptimizedSeqOps.prependedAll$(this, prefix);
   }

   public Object padTo(final int len, final Object elem) {
      return scala.collection.StrictOptimizedSeqOps.padTo$(this, len, elem);
   }

   public Object diff(final scala.collection.Seq that) {
      return scala.collection.StrictOptimizedSeqOps.diff$(this, that);
   }

   public Object intersect(final scala.collection.Seq that) {
      return scala.collection.StrictOptimizedSeqOps.intersect$(this, that);
   }

   public Tuple2 partition(final Function1 p) {
      return StrictOptimizedIterableOps.partition$(this, p);
   }

   public Tuple2 span(final Function1 p) {
      return StrictOptimizedIterableOps.span$(this, p);
   }

   public Tuple2 unzip(final Function1 asPair) {
      return StrictOptimizedIterableOps.unzip$(this, asPair);
   }

   public Tuple3 unzip3(final Function1 asTriple) {
      return StrictOptimizedIterableOps.unzip3$(this, asTriple);
   }

   public Object map(final Function1 f) {
      return StrictOptimizedIterableOps.map$(this, f);
   }

   public final Object strictOptimizedMap(final Builder b, final Function1 f) {
      return StrictOptimizedIterableOps.strictOptimizedMap$(this, b, f);
   }

   public Object flatMap(final Function1 f) {
      return StrictOptimizedIterableOps.flatMap$(this, f);
   }

   public final Object strictOptimizedFlatMap(final Builder b, final Function1 f) {
      return StrictOptimizedIterableOps.strictOptimizedFlatMap$(this, b, f);
   }

   public final Object strictOptimizedConcat(final IterableOnce that, final Builder b) {
      return StrictOptimizedIterableOps.strictOptimizedConcat$(this, that, b);
   }

   public Object collect(final PartialFunction pf) {
      return StrictOptimizedIterableOps.collect$(this, pf);
   }

   public final Object strictOptimizedCollect(final Builder b, final PartialFunction pf) {
      return StrictOptimizedIterableOps.strictOptimizedCollect$(this, b, pf);
   }

   public Object flatten(final Function1 toIterableOnce) {
      return StrictOptimizedIterableOps.flatten$(this, toIterableOnce);
   }

   public final Object strictOptimizedFlatten(final Builder b, final Function1 toIterableOnce) {
      return StrictOptimizedIterableOps.strictOptimizedFlatten$(this, b, toIterableOnce);
   }

   public Object zip(final IterableOnce that) {
      return StrictOptimizedIterableOps.zip$(this, that);
   }

   public final Object strictOptimizedZip(final IterableOnce that, final Builder b) {
      return StrictOptimizedIterableOps.strictOptimizedZip$(this, that, b);
   }

   public Object zipWithIndex() {
      return StrictOptimizedIterableOps.zipWithIndex$(this);
   }

   public Object scanLeft(final Object z, final Function2 op) {
      return StrictOptimizedIterableOps.scanLeft$(this, z, op);
   }

   public Object filter(final Function1 pred) {
      return StrictOptimizedIterableOps.filter$(this, pred);
   }

   public Object filterNot(final Function1 pred) {
      return StrictOptimizedIterableOps.filterNot$(this, pred);
   }

   public Object filterImpl(final Function1 pred, final boolean isFlipped) {
      return StrictOptimizedIterableOps.filterImpl$(this, pred, isFlipped);
   }

   public Tuple2 partitionMap(final Function1 f) {
      return StrictOptimizedIterableOps.partitionMap$(this, f);
   }

   public Object tapEach(final Function1 f) {
      return StrictOptimizedIterableOps.tapEach$(this, f);
   }

   public Object takeRight(final int n) {
      return StrictOptimizedIterableOps.takeRight$(this, n);
   }

   public Object dropRight(final int n) {
      return StrictOptimizedIterableOps.dropRight$(this, n);
   }

   // $FF: synthetic method
   public boolean scala$collection$immutable$IndexedSeq$$super$canEqual(final Object that) {
      return scala.collection.Seq.canEqual$(this, that);
   }

   // $FF: synthetic method
   public boolean scala$collection$immutable$IndexedSeq$$super$sameElements(final IterableOnce that) {
      return scala.collection.SeqOps.sameElements$(this, that);
   }

   public final IndexedSeq toIndexedSeq() {
      return IndexedSeq.toIndexedSeq$(this);
   }

   public boolean canEqual(final Object that) {
      return IndexedSeq.canEqual$(this, that);
   }

   public boolean sameElements(final IterableOnce o) {
      return IndexedSeq.sameElements$(this, o);
   }

   public SeqFactory iterableFactory() {
      return IndexedSeq.iterableFactory$(this);
   }

   // $FF: synthetic method
   public Object scala$collection$immutable$IndexedSeqOps$$super$slice(final int from, final int until) {
      return scala.collection.IndexedSeqOps.slice$(this, from, until);
   }

   public Object slice(final int from, final int until) {
      return IndexedSeqOps.slice$(this, from, until);
   }

   public String stringPrefix() {
      return scala.collection.IndexedSeq.stringPrefix$(this);
   }

   public Iterator reverseIterator() {
      return scala.collection.IndexedSeqOps.reverseIterator$(this);
   }

   public Object foldRight(final Object z, final Function2 op) {
      return scala.collection.IndexedSeqOps.foldRight$(this, z, op);
   }

   public IndexedSeqView view() {
      return scala.collection.IndexedSeqOps.view$(this);
   }

   /** @deprecated */
   public IndexedSeqView view(final int from, final int until) {
      return scala.collection.IndexedSeqOps.view$(this, from, until);
   }

   public scala.collection.Iterable reversed() {
      return scala.collection.IndexedSeqOps.reversed$(this);
   }

   public Option headOption() {
      return scala.collection.IndexedSeqOps.headOption$(this);
   }

   public final int lengthCompare(final int len) {
      return scala.collection.IndexedSeqOps.lengthCompare$(this, len);
   }

   public int knownSize() {
      return scala.collection.IndexedSeqOps.knownSize$(this);
   }

   public final int lengthCompare(final scala.collection.Iterable that) {
      return scala.collection.IndexedSeqOps.lengthCompare$(this, that);
   }

   public Searching.SearchResult search(final Object elem, final Ordering ord) {
      return scala.collection.IndexedSeqOps.search$(this, elem, ord);
   }

   public Searching.SearchResult search(final Object elem, final int from, final int to, final Ordering ord) {
      return scala.collection.IndexedSeqOps.search$(this, elem, from, to, ord);
   }

   public Object start() {
      return this.start;
   }

   public Object end() {
      return this.end;
   }

   public Object step() {
      return this.step;
   }

   public boolean isInclusive() {
      return this.isInclusive;
   }

   public Iterator iterator() {
      return new NumericRangeIterator(this, this.scala$collection$immutable$NumericRange$$num);
   }

   public Stepper stepper(final StepperShape shape) {
      int var2 = shape.shape();
      if (StepperShape$.MODULE$.IntShape() == var2) {
         return new IntNumericRangeStepper(this, 0, this.length());
      } else {
         return (Stepper)(StepperShape$.MODULE$.LongShape() == var2 ? new LongNumericRangeStepper(this, 0, this.length()) : shape.parUnbox(new AnyNumericRangeStepper(this, 0, this.length())));
      }
   }

   private int length$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.length = NumericRange$.MODULE$.count(this.start(), this.end(), this.step(), this.isInclusive(), this.scala$collection$immutable$NumericRange$$num);
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.length;
   }

   public int length() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.length$lzycompute() : this.length;
   }

   private boolean isEmpty$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.isEmpty = this.scala$collection$immutable$NumericRange$$num.gt(this.start(), this.end()) && this.scala$collection$immutable$NumericRange$$num.gt(this.step(), this.scala$collection$immutable$NumericRange$$num.zero()) || this.scala$collection$immutable$NumericRange$$num.lt(this.start(), this.end()) && this.scala$collection$immutable$NumericRange$$num.lt(this.step(), this.scala$collection$immutable$NumericRange$$num.zero()) || this.scala$collection$immutable$NumericRange$$num.equiv(this.start(), this.end()) && !this.isInclusive();
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.isEmpty;
   }

   public boolean isEmpty() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.isEmpty$lzycompute() : this.isEmpty;
   }

   public Object last() {
      if (this.isEmpty()) {
         Nil$ var10000 = Nil$.MODULE$;
         throw new NoSuchElementException("head of empty list");
      } else {
         return this.locationAfterN(this.length() - 1);
      }
   }

   public NumericRange init() {
      if (this.isEmpty()) {
         Nil$ var10000 = Nil$.MODULE$;
         throw new UnsupportedOperationException("init of empty list");
      } else {
         return new NumericRange(this.start(), this.scala$collection$immutable$NumericRange$$num.mkNumericOps(this.end()).$minus(this.step()), this.step(), this.isInclusive(), this.scala$collection$immutable$NumericRange$$num);
      }
   }

   public Object head() {
      if (this.isEmpty()) {
         Nil$ var10000 = Nil$.MODULE$;
         throw new NoSuchElementException("head of empty list");
      } else {
         return this.start();
      }
   }

   public NumericRange tail() {
      if (this.isEmpty()) {
         Nil$ var10000 = Nil$.MODULE$;
         throw new UnsupportedOperationException("tail of empty list");
      } else {
         return (NumericRange)(this.isInclusive() ? new Inclusive(this.scala$collection$immutable$NumericRange$$num.mkNumericOps(this.start()).$plus(this.step()), this.end(), this.step(), this.scala$collection$immutable$NumericRange$$num) : new Exclusive(this.scala$collection$immutable$NumericRange$$num.mkNumericOps(this.start()).$plus(this.step()), this.end(), this.step(), this.scala$collection$immutable$NumericRange$$num));
      }
   }

   public NumericRange by(final Object newStep) {
      return this.copy(this.start(), this.end(), newStep);
   }

   public NumericRange copy(final Object start, final Object end, final Object step) {
      return new NumericRange(start, end, step, this.isInclusive(), this.scala$collection$immutable$NumericRange$$num);
   }

   public Object apply(final int idx) throws IndexOutOfBoundsException {
      if (idx >= 0 && idx < this.length()) {
         return this.locationAfterN(idx);
      } else {
         throw CommonErrors$.MODULE$.indexOutOfBounds(idx, this.length() - 1);
      }
   }

   public void foreach(final Function1 f) {
      int count = 0;

      for(Object current = this.start(); count < this.length(); ++count) {
         f.apply(current);
         current = this.scala$collection$immutable$NumericRange$$num.mkNumericOps(current).$plus(this.step());
      }

   }

   private int indexOfTyped(final Object elem, final int from) {
      int var3 = this.posOf(elem);
      switch (var3) {
         default:
            return var3 >= from ? var3 : -1;
      }
   }

   public final int indexOf(final Object elem, final int from) {
      try {
         return this.indexOfTyped(elem, from);
      } catch (ClassCastException var3) {
         return scala.collection.SeqOps.indexOf$(this, elem, from);
      }
   }

   private int lastIndexOfTyped(final Object elem, final int end) {
      int var3 = this.posOf(elem);
      switch (var3) {
         default:
            return var3 <= end ? var3 : -1;
      }
   }

   public final int lastIndexOf(final Object elem, final int end) {
      try {
         return this.lastIndexOfTyped(elem, end);
      } catch (ClassCastException var3) {
         return scala.collection.SeqOps.lastIndexOf$(this, elem, end);
      }
   }

   public final int lastIndexOf$default$2() {
      return this.length() - 1;
   }

   private int posOf(final Object i) {
      return this.contains(i) ? NumericRange$.MODULE$.count(this.start(), i, this.step(), false, this.scala$collection$immutable$NumericRange$$num) : -1;
   }

   private boolean isWithinBoundaries(final Object elem) {
      return !this.isEmpty() && (this.scala$collection$immutable$NumericRange$$num.mkOrderingOps(this.step()).$greater(this.scala$collection$immutable$NumericRange$$num.zero()) && this.scala$collection$immutable$NumericRange$$num.mkOrderingOps(this.start()).$less$eq(elem) && this.scala$collection$immutable$NumericRange$$num.mkOrderingOps(elem).$less$eq(this.last()) || this.scala$collection$immutable$NumericRange$$num.mkOrderingOps(this.step()).$less(this.scala$collection$immutable$NumericRange$$num.zero()) && this.scala$collection$immutable$NumericRange$$num.mkOrderingOps(this.last()).$less$eq(elem) && this.scala$collection$immutable$NumericRange$$num.mkOrderingOps(elem).$less$eq(this.start()));
   }

   private Object locationAfterN(final int n) {
      return this.scala$collection$immutable$NumericRange$$num.mkNumericOps(this.start()).$plus(this.scala$collection$immutable$NumericRange$$num.mkNumericOps(this.step()).$times(this.scala$collection$immutable$NumericRange$$num.fromInt(n)));
   }

   private boolean crossesTheEndAfterN(final int n) {
      boolean stepIsInTheSameDirectionAsStartToEndVector = this.scala$collection$immutable$NumericRange$$num.gt(this.end(), this.start()) && this.scala$collection$immutable$NumericRange$$num.gt(this.step(), this.scala$collection$immutable$NumericRange$$num.zero()) || this.scala$collection$immutable$NumericRange$$num.lt(this.end(), this.start()) && BoxesRunTime.equals(this.scala$collection$immutable$NumericRange$$num.sign(this.step()), this.scala$collection$immutable$NumericRange$$num.mkNumericOps(this.scala$collection$immutable$NumericRange$$num.one()).unary_$minus());
      if (!this.scala$collection$immutable$NumericRange$$num.equiv(this.start(), this.end()) && n > 0 && stepIsInTheSameDirectionAsStartToEndVector) {
         if (this.scala$collection$immutable$NumericRange$$num.equiv(this.scala$collection$immutable$NumericRange$$num.sign(this.start()), this.scala$collection$immutable$NumericRange$$num.sign(this.end()))) {
            Object len = this.unsafeRangeLength$1(this);
            if (this.fitsInInteger$1(len)) {
               return n >= this.scala$collection$immutable$NumericRange$$num.toInt(len);
            } else {
               return this.scala$collection$immutable$NumericRange$$num.gteq(this.scala$collection$immutable$NumericRange$$num.fromInt(n), len);
            }
         } else {
            Object stepsRemainderToZero = this.scala$collection$immutable$NumericRange$$num.rem(this.start(), this.step());
            boolean walksOnZero = this.scala$collection$immutable$NumericRange$$num.equiv(stepsRemainderToZero, this.scala$collection$immutable$NumericRange$$num.zero());
            Object closestToZero = walksOnZero ? this.scala$collection$immutable$NumericRange$$num.mkNumericOps(this.step()).unary_$minus() : stepsRemainderToZero;
            Tuple3 var10000;
            if (this.scala$collection$immutable$NumericRange$$num.lt(this.start(), this.scala$collection$immutable$NumericRange$$num.zero())) {
               if (walksOnZero) {
                  Object twoStepsAfterLargestNegativeNumber = this.scala$collection$immutable$NumericRange$$num.plus(closestToZero, this.scala$collection$immutable$NumericRange$$num.times(this.step(), this.scala$collection$immutable$NumericRange$$num.fromInt(2)));
                  NumericRange$ var10002 = NumericRange$.MODULE$;
                  var10002 = (NumericRange$)this.start();
                  Object var10003 = this.step();
                  Integral apply_num = this.scala$collection$immutable$NumericRange$$num;
                  Object apply_step = var10003;
                  Object apply_start = var10002;
                  Exclusive var43 = new Exclusive(apply_start, closestToZero, apply_step, apply_num);
                  apply_start = null;
                  apply_step = null;
                  apply_num = null;
                  var10000 = new Tuple3(var43, this.copy(twoStepsAfterLargestNegativeNumber, this.end(), this.step()), 2);
               } else {
                  NumericRange$ var44 = NumericRange$.MODULE$;
                  var44 = (NumericRange$)this.start();
                  Object var49 = this.step();
                  Integral apply_num = this.scala$collection$immutable$NumericRange$$num;
                  Object apply_step = var49;
                  Object apply_start = var44;
                  Exclusive var46 = new Exclusive(apply_start, closestToZero, apply_step, apply_num);
                  apply_start = null;
                  apply_step = null;
                  apply_num = null;
                  var10000 = new Tuple3(var46, this.copy(this.scala$collection$immutable$NumericRange$$num.plus(closestToZero, this.step()), this.end(), this.step()), 1);
               }
            } else if (walksOnZero) {
               Object twoStepsAfterZero = this.scala$collection$immutable$NumericRange$$num.times(this.step(), this.scala$collection$immutable$NumericRange$$num.fromInt(2));
               NumericRange var47 = this.copy(twoStepsAfterZero, this.end(), this.step());
               NumericRange$ var50 = NumericRange$.MODULE$;
               var50 = (NumericRange$)this.start();
               Object var10004 = this.scala$collection$immutable$NumericRange$$num.mkNumericOps(this.step()).unary_$minus();
               Object var10005 = this.step();
               Integral inclusive_num = this.scala$collection$immutable$NumericRange$$num;
               Object inclusive_step = var10005;
               Object inclusive_end = var10004;
               Object inclusive_start = var50;
               Inclusive var52 = new Inclusive(inclusive_start, inclusive_end, inclusive_step, inclusive_num);
               inclusive_start = null;
               inclusive_end = null;
               inclusive_step = null;
               inclusive_num = null;
               var10000 = new Tuple3(var47, var52, 2);
            } else {
               Object twoStepsAfterSmallestPositiveNumber = this.scala$collection$immutable$NumericRange$$num.plus(closestToZero, this.scala$collection$immutable$NumericRange$$num.times(this.step(), this.scala$collection$immutable$NumericRange$$num.fromInt(2)));
               NumericRange var48 = this.copy(twoStepsAfterSmallestPositiveNumber, this.end(), this.step());
               NumericRange$ var53 = NumericRange$.MODULE$;
               var53 = (NumericRange$)this.start();
               Object var56 = this.step();
               Integral inclusive_num = this.scala$collection$immutable$NumericRange$$num;
               Object inclusive_step = var56;
               Object inclusive_start = var53;
               Inclusive var55 = new Inclusive(inclusive_start, closestToZero, inclusive_step, inclusive_num);
               inclusive_start = null;
               inclusive_step = null;
               inclusive_num = null;
               var10000 = new Tuple3(var48, var55, 2);
            }

            Tuple3 var7 = var10000;
            NumericRange l = (NumericRange)var7._1();
            NumericRange r = (NumericRange)var7._2();
            int carry = BoxesRunTime.unboxToInt(var7._3());
            if (l != null && r != null) {
               Object leftLength = this.unsafeRangeLength$1(l);
               Object rightLength = this.unsafeRangeLength$1(r);
               if (this.fitsInInteger$1(leftLength) && this.fitsInInteger$1(rightLength)) {
                  return n - this.scala$collection$immutable$NumericRange$$num.toInt(leftLength) - carry >= this.scala$collection$immutable$NumericRange$$num.toInt(rightLength);
               } else {
                  return this.scala$collection$immutable$NumericRange$$num.gteq(this.scala$collection$immutable$NumericRange$$num.minus(this.scala$collection$immutable$NumericRange$$num.minus(this.scala$collection$immutable$NumericRange$$num.fromInt(n), leftLength), this.scala$collection$immutable$NumericRange$$num.fromInt(carry)), rightLength);
               }
            } else {
               throw new MatchError(var7);
            }
         }
      } else {
         return n >= 1;
      }
   }

   private Exclusive newEmptyRange(final Object value) {
      NumericRange$ var10000 = NumericRange$.MODULE$;
      var10000 = (NumericRange$)this.step();
      Integral apply_num = this.scala$collection$immutable$NumericRange$$num;
      Object apply_step = var10000;
      return new Exclusive(value, value, apply_step, apply_num);
   }

   public NumericRange take(final int n) {
      if (n > 0 && !this.isEmpty()) {
         return (NumericRange)(this.crossesTheEndAfterN(n) ? this : new Inclusive(this.start(), this.locationAfterN(n - 1), this.step(), this.scala$collection$immutable$NumericRange$$num));
      } else {
         return this.newEmptyRange(this.start());
      }
   }

   public NumericRange drop(final int n) {
      if (n > 0 && !this.isEmpty()) {
         return (NumericRange)(this.crossesTheEndAfterN(n) ? this.newEmptyRange(this.end()) : this.copy(this.locationAfterN(n), this.end(), this.step()));
      } else {
         return this;
      }
   }

   public Tuple2 splitAt(final int n) {
      return new Tuple2(this.take(n), this.drop(n));
   }

   public NumericRange reverse() {
      if (this.isEmpty()) {
         return this;
      } else {
         Object newStep = this.scala$collection$immutable$NumericRange$$num.mkNumericOps(this.step()).unary_$minus();
         if (BoxesRunTime.equals(this.scala$collection$immutable$NumericRange$$num.sign(newStep), this.scala$collection$immutable$NumericRange$$num.sign(this.step()))) {
            throw new ArithmeticException("number type is unsigned, and .reverse requires a negative step");
         } else {
            return new Inclusive(this.last(), this.start(), newStep, this.scala$collection$immutable$NumericRange$$num);
         }
      }
   }

   public Object min(final Ordering ord) {
      if (ord != this.scala$collection$immutable$NumericRange$$num) {
         Option var10000 = NumericRange$.MODULE$.defaultOrdering().get(this.scala$collection$immutable$NumericRange$$num);
         if (var10000 == null) {
            throw null;
         }

         label23: {
            Option exists_this = var10000;
            if (!exists_this.isEmpty()) {
               Ordering var3 = (Ordering)exists_this.get();
               if ($anonfun$min$1(ord, var3)) {
                  var5 = true;
                  break label23;
               }
            }

            var5 = false;
         }

         Object var4 = null;
         if (!var5) {
            return IterableOnceOps.min$(this, ord);
         }
      }

      return this.scala$collection$immutable$NumericRange$$num.mkOrderingOps(this.scala$collection$immutable$NumericRange$$num.sign(this.step())).$greater(this.scala$collection$immutable$NumericRange$$num.zero()) ? this.head() : this.last();
   }

   public Object max(final Ordering ord) {
      if (ord != this.scala$collection$immutable$NumericRange$$num) {
         Option var10000 = NumericRange$.MODULE$.defaultOrdering().get(this.scala$collection$immutable$NumericRange$$num);
         if (var10000 == null) {
            throw null;
         }

         label23: {
            Option exists_this = var10000;
            if (!exists_this.isEmpty()) {
               Ordering var3 = (Ordering)exists_this.get();
               if ($anonfun$max$1(ord, var3)) {
                  var5 = true;
                  break label23;
               }
            }

            var5 = false;
         }

         Object var4 = null;
         if (!var5) {
            return IterableOnceOps.max$(this, ord);
         }
      }

      return this.scala$collection$immutable$NumericRange$$num.mkOrderingOps(this.scala$collection$immutable$NumericRange$$num.sign(this.step())).$greater(this.scala$collection$immutable$NumericRange$$num.zero()) ? this.last() : this.head();
   }

   public boolean containsTyped(final Object x) {
      return this.isWithinBoundaries(x) && BoxesRunTime.equals(this.scala$collection$immutable$NumericRange$$num.mkNumericOps(this.scala$collection$immutable$NumericRange$$num.mkNumericOps(x).$minus(this.start())).$percent(this.step()), this.scala$collection$immutable$NumericRange$$num.zero());
   }

   public boolean contains(final Object x) {
      try {
         return this.containsTyped(x);
      } catch (ClassCastException var2) {
         return false;
      }
   }

   public Object sum(final Numeric num) {
      if (this.isEmpty()) {
         return num.zero();
      } else if (this.length() == 1) {
         return this.head();
      } else if (num != Numeric.IntIsIntegral$.MODULE$ && num != Numeric.ShortIsIntegral$.MODULE$ && num != Numeric.ByteIsIntegral$.MODULE$ && num != Numeric.CharIsIntegral$.MODULE$) {
         if (num == Numeric.LongIsIntegral$.MODULE$) {
            long a = this.scala$collection$immutable$NumericRange$$num.mkNumericOps(this.head()).toLong();
            long b = this.scala$collection$immutable$NumericRange$$num.mkNumericOps(this.last()).toLong();
            long var10000;
            if ((this.length() & 1) == 0) {
               var10000 = (long)(this.length() / 2) * (a + b);
            } else {
               var10000 = (long)this.length();
               long ha = a / 2L;
               long hb = b / 2L;
               var10000 *= ha + hb + (a - 2L * ha + (b - 2L * hb)) / 2L;
            }

            return var10000;
         } else if (num != Numeric.BigIntIsIntegral$.MODULE$ && num != Numeric.BigDecimalAsIfIntegral$.MODULE$) {
            if (this.isEmpty()) {
               return num.zero();
            } else {
               Object acc = num.zero();
               Object i = this.head();

               for(int idx = 0; idx < this.length(); ++idx) {
                  acc = num.plus(acc, i);
                  i = this.scala$collection$immutable$NumericRange$$num.mkNumericOps(i).$plus(this.step());
               }

               return acc;
            }
         } else {
            Integral numAsIntegral = (Integral)num;
            return numAsIntegral.mkNumericOps(numAsIntegral.mkNumericOps(num.fromInt(this.length())).$times(numAsIntegral.mkNumericOps(this.head()).$plus(this.last()))).$div(num.fromInt(2));
         }
      } else {
         long exact = (long)this.length() * (num.toLong(this.head()) + (long)num.toInt(this.last())) / 2L;
         return num.fromInt((int)exact);
      }
   }

   private int hashCode$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 4) == 0) {
            this.hashCode = MurmurHash3$.MODULE$.seqHash(this);
            this.bitmap$0 = (byte)(this.bitmap$0 | 4);
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.hashCode;
   }

   public int hashCode() {
      return (byte)(this.bitmap$0 & 4) == 0 ? this.hashCode$lzycompute() : this.hashCode;
   }

   public final int applyPreferredMaxLength() {
      return Integer.MAX_VALUE;
   }

   public boolean equals(final Object other) {
      if (!(other instanceof NumericRange)) {
         return scala.collection.Seq.equals$(this, other);
      } else {
         NumericRange var2 = (NumericRange)other;
         return var2.canEqual(this) && this.length() == var2.length() && (this.isEmpty() || BoxesRunTime.equals(this.start(), var2.start()) && BoxesRunTime.equals(this.last(), var2.last()));
      }
   }

   public String toString() {
      String empty = this.isEmpty() ? "empty " : "";
      String preposition = this.isInclusive() ? "to" : "until";
      String stepped = BoxesRunTime.equals(this.step(), 1) ? "" : (new StringBuilder(4)).append(" by ").append(this.step()).toString();
      return (new StringBuilder(15)).append(empty).append("NumericRange ").append(this.start()).append(" ").append(preposition).append(" ").append(this.end()).append(stepped).toString();
   }

   public String className() {
      return "NumericRange";
   }

   public void foreach$mVc$sp(final Function1 f) {
      int count = 0;

      for(Object current = this.start(); count < this.length(); ++count) {
         f.apply(current);
         current = this.scala$collection$immutable$NumericRange$$num.mkNumericOps(current).$plus(this.step());
      }

   }

   private final Object unsafeRangeLength$1(final NumericRange r) {
      Object diff = this.scala$collection$immutable$NumericRange$$num.minus(r.end(), r.start());
      Object quotient = this.scala$collection$immutable$NumericRange$$num.quot(diff, r.step());
      Object remainder = this.scala$collection$immutable$NumericRange$$num.rem(diff, r.step());
      return !r.isInclusive() && this.scala$collection$immutable$NumericRange$$num.equiv(remainder, this.scala$collection$immutable$NumericRange$$num.zero()) ? this.scala$collection$immutable$NumericRange$$num.max(quotient, this.scala$collection$immutable$NumericRange$$num.zero()) : this.scala$collection$immutable$NumericRange$$num.max(this.scala$collection$immutable$NumericRange$$num.plus(quotient, this.scala$collection$immutable$NumericRange$$num.one()), this.scala$collection$immutable$NumericRange$$num.zero());
   }

   private final boolean fitsInInteger$1(final Object value) {
      return this.scala$collection$immutable$NumericRange$$num.equiv(this.scala$collection$immutable$NumericRange$$num.fromInt(this.scala$collection$immutable$NumericRange$$num.toInt(value)), value);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$min$1(final Ordering ord$1, final Ordering x$2) {
      return ord$1 == x$2;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$max$1(final Ordering ord$2, final Ordering x$3) {
      return ord$2 == x$3;
   }

   public NumericRange(final Object start, final Object end, final Object step, final boolean isInclusive, final Integral num) {
      this.start = start;
      this.end = end;
      this.step = step;
      this.isInclusive = isInclusive;
      this.scala$collection$immutable$NumericRange$$num = num;
   }

   // $FF: synthetic method
   public static final Object $anonfun$min$1$adapted(final Ordering ord$1, final Ordering x$2) {
      return BoxesRunTime.boxToBoolean($anonfun$min$1(ord$1, x$2));
   }

   // $FF: synthetic method
   public static final Object $anonfun$max$1$adapted(final Ordering ord$2, final Ordering x$3) {
      return BoxesRunTime.boxToBoolean($anonfun$max$1(ord$2, x$3));
   }

   public static class Inclusive extends NumericRange {
      private static final long serialVersionUID = 3L;
      private final Integral num;

      public Inclusive copy(final Object start, final Object end, final Object step) {
         NumericRange$ var10000 = NumericRange$.MODULE$;
         Integral inclusive_num = this.num;
         return new Inclusive(start, end, step, inclusive_num);
      }

      public Exclusive exclusive() {
         NumericRange$ var10000 = NumericRange$.MODULE$;
         var10000 = (NumericRange$)super.start();
         Object var10001 = super.end();
         Object var10002 = super.step();
         Integral apply_num = this.num;
         Object apply_step = var10002;
         Object apply_end = var10001;
         Object apply_start = var10000;
         return new Exclusive(apply_start, apply_end, apply_step, apply_num);
      }

      public Inclusive(final Object start, final Object end, final Object step, final Integral num) {
         super(start, end, step, true, num);
         this.num = num;
      }
   }

   public static class Exclusive extends NumericRange {
      private static final long serialVersionUID = 3L;
      private final Integral num;

      public Exclusive copy(final Object start, final Object end, final Object step) {
         NumericRange$ var10000 = NumericRange$.MODULE$;
         Integral apply_num = this.num;
         return new Exclusive(start, end, step, apply_num);
      }

      public Inclusive inclusive() {
         NumericRange$ var10000 = NumericRange$.MODULE$;
         var10000 = (NumericRange$)super.start();
         Object var10001 = super.end();
         Object var10002 = super.step();
         Integral inclusive_num = this.num;
         Object inclusive_step = var10002;
         Object inclusive_end = var10001;
         Object inclusive_start = var10000;
         return new Inclusive(inclusive_start, inclusive_end, inclusive_step, inclusive_num);
      }

      public Exclusive(final Object start, final Object end, final Object step, final Integral num) {
         super(start, end, step, false, num);
         this.num = num;
      }
   }

   private static final class NumericRangeIterator extends AbstractIterator implements Serializable {
      private static final long serialVersionUID = 3L;
      private final NumericRange self;
      private final Integral num;
      private boolean _hasNext;
      private Object _next;
      private final Object lastElement;

      public int knownSize() {
         return this._hasNext ? this.num.toInt(this.num.mkNumericOps(this.num.mkNumericOps(this.lastElement).$minus(this._next)).$div(this.self.step())) + 1 : 0;
      }

      public boolean hasNext() {
         return this._hasNext;
      }

      public Object next() {
         if (!this._hasNext) {
            Iterator$ var10000 = Iterator$.MODULE$;
            Iterator$.scala$collection$Iterator$$_empty.next();
         }

         Object value = this._next;
         this._hasNext = !BoxesRunTime.equals(value, this.lastElement);
         this._next = this.num.plus(value, this.self.step());
         return value;
      }

      public NumericRangeIterator(final NumericRange self, final Integral num) {
         this.self = self;
         this.num = num;
         this._hasNext = !self.isEmpty();
         this._next = self.start();
         this.lastElement = this._hasNext ? self.last() : self.start();
      }
   }
}
