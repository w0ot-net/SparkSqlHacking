package scala.collection.mutable;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.IterableOnce;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.collection.SortedMapFactoryDefaults;
import scala.collection.SortedOps;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.StepperShape$;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.StrictOptimizedMapOps;
import scala.collection.StrictOptimizedSortedMapOps;
import scala.collection.convert.impl.AnyBinaryTreeStepper;
import scala.collection.convert.impl.AnyBinaryTreeStepper$;
import scala.collection.convert.impl.BinaryTreeStepper$;
import scala.collection.convert.impl.DoubleBinaryTreeStepper;
import scala.collection.convert.impl.DoubleBinaryTreeStepper$;
import scala.collection.convert.impl.IntBinaryTreeStepper;
import scala.collection.convert.impl.IntBinaryTreeStepper$;
import scala.collection.convert.impl.LongBinaryTreeStepper;
import scala.collection.convert.impl.LongBinaryTreeStepper$;
import scala.collection.generic.DefaultSerializable;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\r=d\u0001\u0002!B!!C!\"a\u0001\u0001\u0005\u0003\u0005\u000b\u0011BA\u0003\u0011)\t\u0019\u0002\u0001BC\u0002\u0013\r\u0011Q\u0003\u0005\u000b\u0003K\u0001!\u0011!Q\u0001\n\u0005]\u0001bBA\u0014\u0001\u0011%\u0011\u0011\u0006\u0005\b\u0003c\u0001A\u0011IA\u001a\u0011\u001d\t9\u0003\u0001C\u0001\u0003\u000bDq!!4\u0001\t\u0003\ty\rC\u0004\u0002X\u0002!\t%!7\t\u000f\u0005u\u0007\u0001\"\u0011\u0002`\"9\u00111\u001d\u0001\u0005\u0002\u0005\u0015\bbBAv\u0001\u0011\u0005\u0011Q\u001e\u0005\b\u0003c\u0004A\u0011IAz\u0011\u001d\t9\u0010\u0001C!\u0003sDqAa\u0010\u0001\t\u0003\u0012\t\u0005C\u0004\u0003^\u0001!\tEa\u0018\t\u000f\tm\u0004\u0001\"\u0001\u0003~!9!Q\u0011\u0001\u0005\u0002\t\u001d\u0005b\u0002BF\u0001\u0011\u0005#Q\u0012\u0005\b\u0005+\u0003A\u0011\u0001BL\u0011\u001d\u0011\u0019\u000b\u0001C\u0001\u0005KCqAa,\u0001\t\u0003\u0012\t\fC\u0004\u0003F\u0002!\tEa2\t\u000f\t]\u0007\u0001\"\u0011\u0003Z\"9!\u0011\u001d\u0001\u0005B\te\u0007b\u0002Br\u0001\u0011\u0005#Q\u001d\u0005\b\u0005[\u0004A\u0011\tBx\u0011\u001d\u0011\u0019\u0010\u0001C!\u0005kDqAa>\u0001\t\u0003\u0012)\u0010C\u0004\u0003z\u0002!\tEa?\t\u000f\r\u0005\u0001\u0001\"\u0011\u0004\u0004!A1q\u0001\u0001!\n#\u001aIA\u0002\u0005\u0004\u001c\u0001\u0001\u000bQBB\u000f\u0011)\tY\u0005\tB\u0001B\u0003%!\u0011\u0016\u0005\u000b\u0005[\u0003#\u0011!Q\u0001\n\t%\u0006bBA\u0014A\u0011\u00051q\u0004\u0005\t\u0007O\u0001\u0003\u0015\"\u0003\u0004*!A1q\u0006\u0011!\n\u0013\u0019\t\u0004\u0003\u0005\u00048\u0001\u0002K\u0011BB\u001d\u0011\u001d\u0011\u0019\u000b\tC!\u0007{AqA!&!\t\u0003\u001a\u0019\u0005C\u0004\u0002N\u0002\"\t%a4\t\u000f\u0005]\u0007\u0005\"\u0011\u0002Z\"9\u0011Q\u001c\u0011\u0005B\u0005}\u0007bBArA\u0011\u00053q\t\u0005\b\u0003W\u0004C\u0011IB&\u0011\u001d\t\t\u0010\tC!\u0007\u001fBqAa6!\t\u0003\u0012I\u000eC\u0004\u0003b\u0002\"\tE!7\t\u000f\t\r\b\u0005\"\u0011\u0003f\"9!Q\u001e\u0011\u0005B\rM\u0003b\u0002BzA\u0011\u0005#Q\u001f\u0005\b\u0007/\u0002C\u0011IB-\u0011\u001d\u00119\u0010\tC!\u0005kDqaa\u0017!\t\u0003\u001aI\u0006C\u0004\u00030\u0002\"\te!\u0018\t\u000f\r%\u0004\u0005\"\u0011\u0004l\u001d9\u0011qG!\t\u0002\u0005ebA\u0002!B\u0011\u0003\tY\u0004C\u0004\u0002(i\"\t!!\u0013\t\u000f\u0005-#\b\"\u0001\u0002N!9\u0011q\u000e\u001e\u0005\u0002\u0005E\u0004bBACu\u0011\u0005\u0011q\u0011\u0005\n\u0003GS\u0014\u0011!C\u0005\u0003K\u0013q\u0001\u0016:fK6\u000b\u0007O\u0003\u0002C\u0007\u00069Q.\u001e;bE2,'B\u0001#F\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002\r\u0006)1oY1mC\u000e\u0001QcA%Q7NI\u0001AS/aK>,\bp\u001f\t\u0005\u00172s%,D\u0001B\u0013\ti\u0015IA\u0006BEN$(/Y2u\u001b\u0006\u0004\bCA(Q\u0019\u0001!Q!\u0015\u0001C\u0002I\u0013\u0011aS\t\u0003'^\u0003\"\u0001V+\u000e\u0003\u0015K!AV#\u0003\u000f9{G\u000f[5oOB\u0011A\u000bW\u0005\u00033\u0016\u00131!\u00118z!\ty5\fB\u0003]\u0001\t\u0007!KA\u0001W!\u0011YeL\u0014.\n\u0005}\u000b%!C*peR,G-T1q!\u0019Y\u0015M\u0014.dI&\u0011!-\u0011\u0002\r'>\u0014H/\u001a3NCB|\u0005o\u001d\t\u0003\u0017\u0002\u0001Ba\u0013\u0001O5B)amZ5mI6\t1)\u0003\u0002i\u0007\nQ2\u000b\u001e:jGR|\u0005\u000f^5nSj,G-\u0013;fe\u0006\u0014G.Z(qgB!AK\u001b([\u0013\tYWI\u0001\u0004UkBdWM\r\t\u0003\u00176L!A\\!\u0003\u0011%#XM]1cY\u0016\u0004bA\u001a9O5J$\u0017BA9D\u0005U\u0019FO]5di>\u0003H/[7ju\u0016$W*\u00199PaN\u0004\"aS:\n\u0005Q\f%aA'baB1aM\u001e([G\u0012L!a^\"\u00037M#(/[2u\u001fB$\u0018.\\5{K\u0012\u001cvN\u001d;fI6\u000b\u0007o\u00149t!\u001d1\u0017P\u0014.dYJL!A_\"\u00031M{'\u000f^3e\u001b\u0006\u0004h)Y2u_JLH)\u001a4bk2$8\u000f\u0005\u0002}\u007f6\tQP\u0003\u0002\u007f\u0007\u00069q-\u001a8fe&\u001c\u0017bAA\u0001{\n\u0019B)\u001a4bk2$8+\u001a:jC2L'0\u00192mK\u0006!AO]3f!\u0019\t9!!\u0004O5:\u00191*!\u0003\n\u0007\u0005-\u0011)\u0001\u0007SK\u0012\u0014E.Y2l)J,W-\u0003\u0003\u0002\u0010\u0005E!\u0001\u0002+sK\u0016T1!a\u0003B\u0003!y'\u000fZ3sS:<WCAA\f!\u0015\tI\"a\bO\u001d\r!\u00161D\u0005\u0004\u0003;)\u0015a\u00029bG.\fw-Z\u0005\u0005\u0003C\t\u0019C\u0001\u0005Pe\u0012,'/\u001b8h\u0015\r\ti\"R\u0001\n_J$WM]5oO\u0002\na\u0001P5oSRtD\u0003BA\u0016\u0003_!2\u0001ZA\u0017\u0011\u001d\t\u0019\u0002\u0002a\u0002\u0003/Aq!a\u0001\u0005\u0001\u0004\t)!\u0001\tt_J$X\rZ'ba\u001a\u000b7\r^8ssV\u0011\u0011Q\u0007\b\u0003\u0017f\nq\u0001\u0016:fK6\u000b\u0007\u000f\u0005\u0002LuM)!(!\u0010\u0002DA\u0019A+a\u0010\n\u0007\u0005\u0005SI\u0001\u0004B]f\u0014VM\u001a\t\u0005M\u0006\u00153-C\u0002\u0002H\r\u0013\u0001cU8si\u0016$W*\u00199GC\u000e$xN]=\u0015\u0005\u0005e\u0012\u0001\u00024s_6,b!a\u0014\u0002X\u0005mC\u0003BA)\u0003G\"B!a\u0015\u0002^A11\nAA+\u00033\u00022aTA,\t\u0015\tFH1\u0001S!\ry\u00151\f\u0003\u00069r\u0012\rA\u0015\u0005\n\u0003?b\u0014\u0011!a\u0002\u0003C\n!\"\u001a<jI\u0016t7-\u001a\u00132!\u0019\tI\"a\b\u0002V!9\u0011Q\r\u001fA\u0002\u0005\u001d\u0014AA5u!\u00151\u0017\u0011NA7\u0013\r\tYg\u0011\u0002\r\u0013R,'/\u00192mK>s7-\u001a\t\u0007)*\f)&!\u0017\u0002\u000b\u0015l\u0007\u000f^=\u0016\r\u0005M\u0014\u0011PA?)\u0011\t)(a \u0011\r-\u0003\u0011qOA>!\ry\u0015\u0011\u0010\u0003\u0006#v\u0012\rA\u0015\t\u0004\u001f\u0006uD!\u0002/>\u0005\u0004\u0011\u0006\"CAA{\u0005\u0005\t9AAB\u0003))g/\u001b3f]\u000e,GE\r\t\u0007\u00033\ty\"a\u001e\u0002\u00159,wOQ;jY\u0012,'/\u0006\u0004\u0002\n\u0006U\u0015\u0011\u0014\u000b\u0005\u0003\u0017\u000bi\nE\u0004L\u0003\u001b\u000b\t*a'\n\u0007\u0005=\u0015IA\u0004Ck&dG-\u001a:\u0011\rQS\u00171SAL!\ry\u0015Q\u0013\u0003\u0006#z\u0012\rA\u0015\t\u0004\u001f\u0006eE!\u0002/?\u0005\u0004\u0011\u0006CB&\u0001\u0003'\u000b9\nC\u0005\u0002 z\n\t\u0011q\u0001\u0002\"\u0006QQM^5eK:\u001cW\rJ\u001a\u0011\r\u0005e\u0011qDAJ\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t9\u000b\u0005\u0003\u0002*\u0006MVBAAV\u0015\u0011\ti+a,\u0002\t1\fgn\u001a\u0006\u0003\u0003c\u000bAA[1wC&!\u0011QWAV\u0005\u0019y%M[3di\":!(!/\u0002@\u0006\u0005\u0007c\u0001+\u0002<&\u0019\u0011QX#\u0003!M+'/[1m-\u0016\u00148/[8o+&#\u0015!\u0002<bYV,g$A\u0002)\u000fe\nI,a0\u0002BR\u0011\u0011q\u0019\u000b\u0004I\u0006%\u0007bBAf\r\u0001\u000f\u0011qC\u0001\u0004_J$\u0017\u0001C5uKJ\fGo\u001c:\u0016\u0005\u0005E\u0007\u0003\u00024\u0002T&L1!!6D\u0005!IE/\u001a:bi>\u0014\u0018\u0001D6fsNLE/\u001a:bi>\u0014XCAAn!\u00111\u00171\u001b(\u0002\u001dY\fG.^3t\u0013R,'/\u0019;peV\u0011\u0011\u0011\u001d\t\u0005M\u0006M',\u0001\tlKf\u001c\u0018\n^3sCR|'O\u0012:p[R!\u00111\\At\u0011\u0019\tIO\u0003a\u0001\u001d\u0006)1\u000f^1si\u0006a\u0011\u000e^3sCR|'O\u0012:p[R!\u0011\u0011[Ax\u0011\u0019\tIo\u0003a\u0001\u001d\u0006\u0011b/\u00197vKNLE/\u001a:bi>\u0014hI]8n)\u0011\t\t/!>\t\r\u0005%H\u00021\u0001O\u0003\u001d\u0019H/\u001a9qKJ,B!a?\u0003\u0006Q!\u0011Q B\u001b%\u0019\tyPa\u0001\u0003\u001a\u00191!\u0011\u0001\u0001\u0001\u0003{\u0014A\u0002\u0010:fM&tW-\\3oiz\u00022a\u0014B\u0003\t\u001d\u00119!\u0004b\u0001\u0005\u0013\u0011\u0011aU\t\u0004'\n-\u0001\u0007\u0002B\u0007\u0005+\u0001RA\u001aB\b\u0005'I1A!\u0005D\u0005\u001d\u0019F/\u001a9qKJ\u00042a\u0014B\u000b\t-\u00119B!\u0002\u0002\u0002\u0003\u0005)\u0011\u0001*\u0003\u0007}#\u0013\u0007\u0005\u0003\u0003\u001c\t=b\u0002\u0002B\u000f\u0005WqAAa\b\u0003*9!!\u0011\u0005B\u0014\u001b\t\u0011\u0019CC\u0002\u0003&\u001d\u000ba\u0001\u0010:p_Rt\u0014\"\u0001$\n\u0005\u0011+\u0015b\u0001B\u0017\u0007\u000691\u000b^3qa\u0016\u0014\u0018\u0002\u0002B\u0019\u0005g\u0011a\"\u00124gS\u000eLWM\u001c;Ta2LGOC\u0002\u0003.\rCqAa\u000e\u000e\u0001\b\u0011I$A\u0003tQ\u0006\u0004X\r\u0005\u0004g\u0005wI'1A\u0005\u0004\u0005{\u0019%\u0001D*uKB\u0004XM]*iCB,\u0017AC6fsN#X\r\u001d9feV!!1\tB&)\u0011\u0011)E!\u0017\u0013\r\t\u001d#\u0011\nB\r\r\u0019\u0011\t\u0001\u0001\u0001\u0003FA\u0019qJa\u0013\u0005\u000f\t\u001daB1\u0001\u0003NE\u00191Ka\u00141\t\tE#Q\u000b\t\u0006M\n=!1\u000b\t\u0004\u001f\nUCa\u0003B,\u0005\u0017\n\t\u0011!A\u0003\u0002I\u00131a\u0018\u00133\u0011\u001d\u00119D\u0004a\u0002\u00057\u0002bA\u001aB\u001e\u001d\n%\u0013\u0001\u0004<bYV,7\u000b^3qa\u0016\u0014X\u0003\u0002B1\u0005S\"BAa\u0019\u0003xI1!Q\rB4\u000531aA!\u0001\u0001\u0001\t\r\u0004cA(\u0003j\u00119!qA\bC\u0002\t-\u0014cA*\u0003nA\"!q\u000eB:!\u00151'q\u0002B9!\ry%1\u000f\u0003\f\u0005k\u0012I'!A\u0001\u0002\u000b\u0005!KA\u0002`IMBqAa\u000e\u0010\u0001\b\u0011I\b\u0005\u0004g\u0005wQ&qM\u0001\u0007C\u0012$wJ\\3\u0015\t\t}$\u0011Q\u0007\u0002\u0001!1!1\u0011\tA\u0002%\fA!\u001a7f[\u0006Y1/\u001e2ue\u0006\u001cGo\u00148f)\u0011\u0011yH!#\t\r\t\r\u0015\u00031\u0001O\u0003\u0015\u0019G.Z1s)\t\u0011y\tE\u0002U\u0005#K1Aa%F\u0005\u0011)f.\u001b;\u0002\u0007\u001d,G\u000f\u0006\u0003\u0003\u001a\n}\u0005\u0003\u0002+\u0003\u001cjK1A!(F\u0005\u0019y\u0005\u000f^5p]\"1!\u0011U\nA\u00029\u000b1a[3z\u0003%\u0011\u0018M\\4f\u00136\u0004H\u000eF\u0003e\u0005O\u0013Y\u000bC\u0004\u0002LQ\u0001\rA!+\u0011\tQ\u0013YJ\u0014\u0005\b\u0005[#\u0002\u0019\u0001BU\u0003\u0015)h\u000e^5m\u0003\u001d1wN]3bG\",BAa-\u0003BR!!q\u0012B[\u0011\u001d\u00119,\u0006a\u0001\u0005s\u000b\u0011A\u001a\t\u0007)\nm\u0016Na0\n\u0007\tuVIA\u0005Gk:\u001cG/[8ocA\u0019qJ!1\u0005\r\t\rWC1\u0001S\u0005\u0005)\u0016\u0001\u00044pe\u0016\f7\r[#oiJLX\u0003\u0002Be\u0005+$BAa$\u0003L\"9!q\u0017\fA\u0002\t5\u0007c\u0002+\u0003P:S&1[\u0005\u0004\u0005#,%!\u0003$v]\u000e$\u0018n\u001c83!\ry%Q\u001b\u0003\u0007\u0005\u00074\"\u0019\u0001*\u0002\tML'0Z\u000b\u0003\u00057\u00042\u0001\u0016Bo\u0013\r\u0011y.\u0012\u0002\u0004\u0013:$\u0018!C6o_^t7+\u001b>f\u0003\u001dI7/R7qif,\"Aa:\u0011\u0007Q\u0013I/C\u0002\u0003l\u0016\u0013qAQ8pY\u0016\fg.\u0001\u0005d_:$\u0018-\u001b8t)\u0011\u00119O!=\t\r\t\u0005&\u00041\u0001O\u0003\u0011AW-\u00193\u0016\u0003%\fA\u0001\\1ti\u0006AQ.\u001b8BMR,'\u000f\u0006\u0003\u0003~\n}\b\u0003\u0002+\u0003\u001c&DaA!)\u001e\u0001\u0004q\u0015!C7bq\n+gm\u001c:f)\u0011\u0011ip!\u0002\t\r\t\u0005f\u00041\u0001O\u0003%\u0019G.Y:t\u001d\u0006lW-\u0006\u0002\u0004\fA!1QBB\u000b\u001d\u0011\u0019ya!\u0005\u0011\u0007\t\u0005R)C\u0002\u0004\u0014\u0015\u000ba\u0001\u0015:fI\u00164\u0017\u0002BB\f\u00073\u0011aa\u0015;sS:<'bAB\n\u000b\n\tBK]3f\u001b\u0006\u0004\bK]8kK\u000e$\u0018n\u001c8\u0014\u0005\u0001\"GCBB\u0011\u0007G\u0019)\u0003E\u0002\u0003\u0000\u0001Bq!a\u0013$\u0001\u0004\u0011I\u000bC\u0004\u0003.\u000e\u0002\rA!+\u0002\u001dAL7m\u001b'po\u0016\u0014(i\\;oIR!!\u0011VB\u0016\u0011\u001d\u0019i\u0003\na\u0001\u0005S\u000bqA\\3x\rJ|W.\u0001\bqS\u000e\\W\u000b\u001d9fe\n{WO\u001c3\u0015\t\t%61\u0007\u0005\b\u0007k)\u0003\u0019\u0001BU\u0003!qWm^+oi&d\u0017AE5t\u0013:\u001c\u0018\u000eZ3WS\u0016<(i\\;oIN$BAa:\u0004<!1!\u0011\u0015\u0014A\u00029#R\u0001ZB \u0007\u0003Bq!a\u0013(\u0001\u0004\u0011I\u000bC\u0004\u0003.\u001e\u0002\rA!+\u0015\t\te5Q\t\u0005\u0007\u0005CC\u0003\u0019\u0001(\u0015\t\u0005m7\u0011\n\u0005\u0007\u0003Sd\u0003\u0019\u0001(\u0015\t\u0005E7Q\n\u0005\u0007\u0003Sl\u0003\u0019\u0001(\u0015\t\u0005\u00058\u0011\u000b\u0005\u0007\u0003St\u0003\u0019\u0001(\u0015\t\t\u001d8Q\u000b\u0005\u0007\u0005C\u0013\u0004\u0019\u0001(\u0002\u0015!,\u0017\rZ(qi&|g.\u0006\u0002\u0003~\u0006QA.Y:u\u001fB$\u0018n\u001c8\u0016\t\r}3q\r\u000b\u0005\u0005\u001f\u001b\t\u0007C\u0004\u00038^\u0002\raa\u0019\u0011\rQ\u0013Y,[B3!\ry5q\r\u0003\u0007\u0005\u0007<$\u0019\u0001*\u0002\u000b\rdwN\\3\u0015\u0003\u0011L#\u0001\u0001\u0011"
)
public class TreeMap extends AbstractMap implements SortedMap, StrictOptimizedSortedMapOps, DefaultSerializable {
   public final RedBlackTree.Tree scala$collection$mutable$TreeMap$$tree;
   private final Ordering ordering;

   public static Builder newBuilder(final Ordering evidence$3) {
      return TreeMap$.MODULE$.newBuilder(evidence$3);
   }

   public Object writeReplace() {
      return DefaultSerializable.writeReplace$(this);
   }

   public scala.collection.Map map(final Function1 f, final Ordering ordering) {
      return StrictOptimizedSortedMapOps.map$(this, f, ordering);
   }

   public scala.collection.Map flatMap(final Function1 f, final Ordering ordering) {
      return StrictOptimizedSortedMapOps.flatMap$(this, f, ordering);
   }

   public scala.collection.Map concat(final IterableOnce xs) {
      return StrictOptimizedSortedMapOps.concat$(this, xs);
   }

   public scala.collection.Map collect(final PartialFunction pf, final Ordering ordering) {
      return StrictOptimizedSortedMapOps.collect$(this, pf, ordering);
   }

   /** @deprecated */
   public scala.collection.Map $plus(final Tuple2 elem1, final Tuple2 elem2, final scala.collection.immutable.Seq elems) {
      return StrictOptimizedSortedMapOps.$plus$(this, elem1, elem2, elems);
   }

   public IterableOps map(final Function1 f) {
      return StrictOptimizedMapOps.map$(this, f);
   }

   public IterableOps flatMap(final Function1 f) {
      return StrictOptimizedMapOps.flatMap$(this, f);
   }

   public IterableOps collect(final PartialFunction pf) {
      return StrictOptimizedMapOps.collect$(this, pf);
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

   public Map unsorted() {
      return SortedMap.unsorted$(this);
   }

   public SortedMap withDefault(final Function1 d) {
      return SortedMap.withDefault$(this, d);
   }

   public SortedMap withDefaultValue(final Object d) {
      return SortedMap.withDefaultValue$(this, d);
   }

   /** @deprecated */
   public Map updated(final Object key, final Object value) {
      return SortedMapOps.updated$(this, key, value);
   }

   // $FF: synthetic method
   public boolean scala$collection$SortedMap$$super$equals(final Object o) {
      return scala.collection.Map.equals$(this, o);
   }

   public String stringPrefix() {
      return scala.collection.SortedMap.stringPrefix$(this);
   }

   public boolean equals(final Object that) {
      return scala.collection.SortedMap.equals$(this, that);
   }

   public scala.collection.SortedMapOps empty() {
      return SortedMapFactoryDefaults.empty$(this);
   }

   public scala.collection.SortedMapOps fromSpecific(final IterableOnce coll) {
      return SortedMapFactoryDefaults.fromSpecific$(this, coll);
   }

   public Builder newSpecificBuilder() {
      return SortedMapFactoryDefaults.newSpecificBuilder$(this);
   }

   public scala.collection.SortedMapOps.WithFilter withFilter(final Function1 p) {
      return SortedMapFactoryDefaults.withFilter$(this, p);
   }

   public final scala.collection.Map sortedMapFromIterable(final scala.collection.Iterable it, final Ordering ordering) {
      return scala.collection.SortedMapOps.sortedMapFromIterable$(this, it, ordering);
   }

   public Object firstKey() {
      return scala.collection.SortedMapOps.firstKey$(this);
   }

   public Object lastKey() {
      return scala.collection.SortedMapOps.lastKey$(this);
   }

   public scala.collection.SortedMapOps rangeTo(final Object to) {
      return scala.collection.SortedMapOps.rangeTo$(this, to);
   }

   public scala.collection.SortedSet keySet() {
      return scala.collection.SortedMapOps.keySet$(this);
   }

   public final scala.collection.Map $plus$plus(final IterableOnce xs) {
      return scala.collection.SortedMapOps.$plus$plus$(this, xs);
   }

   /** @deprecated */
   public scala.collection.Map $plus(final Tuple2 kv) {
      return scala.collection.SortedMapOps.$plus$(this, kv);
   }

   /** @deprecated */
   public int compare(final Object k0, final Object k1) {
      return SortedOps.compare$(this, k0, k1);
   }

   public Object range(final Object from, final Object until) {
      return SortedOps.range$(this, from, until);
   }

   /** @deprecated */
   public final Object from(final Object from) {
      return SortedOps.from$(this, from);
   }

   public Object rangeFrom(final Object from) {
      return SortedOps.rangeFrom$(this, from);
   }

   /** @deprecated */
   public final Object until(final Object until) {
      return SortedOps.until$(this, until);
   }

   public Object rangeUntil(final Object until) {
      return SortedOps.rangeUntil$(this, until);
   }

   /** @deprecated */
   public final Object to(final Object to) {
      return SortedOps.to$(this, to);
   }

   public Ordering ordering() {
      return this.ordering;
   }

   public TreeMap$ sortedMapFactory() {
      return TreeMap$.MODULE$;
   }

   public Iterator iterator() {
      if (this.isEmpty()) {
         Iterator$ var6 = Iterator$.MODULE$;
         return Iterator$.scala$collection$Iterator$$_empty;
      } else {
         RedBlackTree$ var10000 = RedBlackTree$.MODULE$;
         RedBlackTree.Tree var5 = this.scala$collection$mutable$TreeMap$$tree;
         RedBlackTree$ var10001 = RedBlackTree$.MODULE$;
         None$ var7 = None$.MODULE$;
         RedBlackTree$ var10002 = RedBlackTree$.MODULE$;
         None$ var8 = None$.MODULE$;
         Ordering iterator_evidence$3 = this.ordering();
         None$ iterator_end = var8;
         None$ iterator_start = var7;
         RedBlackTree.Tree iterator_tree = var5;
         return new RedBlackTree.EntriesIterator(iterator_tree, iterator_start, iterator_end, iterator_evidence$3);
      }
   }

   public Iterator keysIterator() {
      if (this.isEmpty()) {
         Iterator$ var6 = Iterator$.MODULE$;
         return Iterator$.scala$collection$Iterator$$_empty;
      } else {
         RedBlackTree$ var10000 = RedBlackTree$.MODULE$;
         RedBlackTree.Tree var5 = this.scala$collection$mutable$TreeMap$$tree;
         None$ var10001 = None$.MODULE$;
         RedBlackTree$ var10002 = RedBlackTree$.MODULE$;
         None$ var7 = None$.MODULE$;
         Ordering keysIterator_evidence$4 = this.ordering();
         None$ keysIterator_end = var7;
         None$ keysIterator_start = var10001;
         RedBlackTree.Tree keysIterator_tree = var5;
         return new RedBlackTree.KeysIterator(keysIterator_tree, keysIterator_start, keysIterator_end, keysIterator_evidence$4);
      }
   }

   public Iterator valuesIterator() {
      if (this.isEmpty()) {
         Iterator$ var6 = Iterator$.MODULE$;
         return Iterator$.scala$collection$Iterator$$_empty;
      } else {
         RedBlackTree$ var10000 = RedBlackTree$.MODULE$;
         RedBlackTree.Tree var5 = this.scala$collection$mutable$TreeMap$$tree;
         None$ var10001 = None$.MODULE$;
         RedBlackTree$ var10002 = RedBlackTree$.MODULE$;
         None$ var7 = None$.MODULE$;
         Ordering valuesIterator_evidence$5 = this.ordering();
         None$ valuesIterator_end = var7;
         None$ valuesIterator_start = var10001;
         RedBlackTree.Tree valuesIterator_tree = var5;
         return new RedBlackTree.ValuesIterator(valuesIterator_tree, valuesIterator_start, valuesIterator_end, valuesIterator_evidence$5);
      }
   }

   public Iterator keysIteratorFrom(final Object start) {
      if (this.isEmpty()) {
         Iterator$ var7 = Iterator$.MODULE$;
         return Iterator$.scala$collection$Iterator$$_empty;
      } else {
         RedBlackTree$ var10000 = RedBlackTree$.MODULE$;
         RedBlackTree.Tree var6 = this.scala$collection$mutable$TreeMap$$tree;
         Some var10001 = new Some(start);
         RedBlackTree$ var10002 = RedBlackTree$.MODULE$;
         None$ var8 = None$.MODULE$;
         Ordering keysIterator_evidence$4 = this.ordering();
         None$ keysIterator_end = var8;
         Some keysIterator_start = var10001;
         RedBlackTree.Tree keysIterator_tree = var6;
         return new RedBlackTree.KeysIterator(keysIterator_tree, keysIterator_start, keysIterator_end, keysIterator_evidence$4);
      }
   }

   public Iterator iteratorFrom(final Object start) {
      if (this.isEmpty()) {
         Iterator$ var7 = Iterator$.MODULE$;
         return Iterator$.scala$collection$Iterator$$_empty;
      } else {
         RedBlackTree$ var10000 = RedBlackTree$.MODULE$;
         RedBlackTree.Tree var6 = this.scala$collection$mutable$TreeMap$$tree;
         Some var10001 = new Some(start);
         RedBlackTree$ var10002 = RedBlackTree$.MODULE$;
         None$ var8 = None$.MODULE$;
         Ordering iterator_evidence$3 = this.ordering();
         None$ iterator_end = var8;
         Some iterator_start = var10001;
         RedBlackTree.Tree iterator_tree = var6;
         return new RedBlackTree.EntriesIterator(iterator_tree, iterator_start, iterator_end, iterator_evidence$3);
      }
   }

   public Iterator valuesIteratorFrom(final Object start) {
      if (this.isEmpty()) {
         Iterator$ var7 = Iterator$.MODULE$;
         return Iterator$.scala$collection$Iterator$$_empty;
      } else {
         RedBlackTree$ var10000 = RedBlackTree$.MODULE$;
         RedBlackTree.Tree var6 = this.scala$collection$mutable$TreeMap$$tree;
         Some var10001 = new Some(start);
         RedBlackTree$ var10002 = RedBlackTree$.MODULE$;
         None$ var8 = None$.MODULE$;
         Ordering valuesIterator_evidence$5 = this.ordering();
         None$ valuesIterator_end = var8;
         Some valuesIterator_start = var10001;
         RedBlackTree.Tree valuesIterator_tree = var6;
         return new RedBlackTree.ValuesIterator(valuesIterator_tree, valuesIterator_start, valuesIterator_end, valuesIterator_evidence$5);
      }
   }

   public Stepper stepper(final StepperShape shape) {
      AnyBinaryTreeStepper$ var10001 = AnyBinaryTreeStepper$.MODULE$;
      int var13 = this.size();
      RedBlackTree.Node var10002 = this.scala$collection$mutable$TreeMap$$tree.root();
      Function1 var10003 = (x$1) -> x$1.left();
      Function1 var10004 = (x$2) -> x$2.right();
      Function1 from_extract = (x) -> new Tuple2(x.key(), x.value());
      Function1 from_right = var10004;
      Function1 from_left = var10003;
      RedBlackTree.Node from_root = var10002;
      int from_maxLength = var13;
      AnyBinaryTreeStepper from_ans = new AnyBinaryTreeStepper(0, (Object)null, BinaryTreeStepper$.MODULE$.emptyStack(), -1, from_left, from_right, from_extract);
      from_ans.initialize(from_root, from_maxLength);
      AnyBinaryTreeStepper var14 = from_ans;
      from_root = null;
      from_left = null;
      from_right = null;
      from_extract = null;
      Object var12 = null;
      return shape.parUnbox(var14);
   }

   public Stepper keyStepper(final StepperShape shape) {
      int var2 = shape.shape();
      if (StepperShape$.MODULE$.IntShape() == var2) {
         IntBinaryTreeStepper$ var35 = IntBinaryTreeStepper$.MODULE$;
         int var36 = this.size();
         RedBlackTree.Node var41 = this.scala$collection$mutable$TreeMap$$tree.root();
         Function1 var44 = (x$3) -> x$3.left();
         Function1 var47 = (x$4) -> x$4.right();
         Function1 from_extract = (x$5) -> BoxesRunTime.boxToInteger($anonfun$keyStepper$3(x$5));
         Function1 from_right = var47;
         Function1 from_left = var44;
         RedBlackTree.Node from_root = var41;
         int from_maxLength = var36;
         IntBinaryTreeStepper from_ans = new IntBinaryTreeStepper(0, (Object)null, BinaryTreeStepper$.MODULE$.emptyStack(), -1, from_left, from_right, from_extract);
         from_ans.initialize(from_root, from_maxLength);
         return from_ans;
      } else if (StepperShape$.MODULE$.LongShape() == var2) {
         LongBinaryTreeStepper$ var33 = LongBinaryTreeStepper$.MODULE$;
         int var34 = this.size();
         RedBlackTree.Node var40 = this.scala$collection$mutable$TreeMap$$tree.root();
         Function1 var43 = (x$6) -> x$6.left();
         Function1 var46 = (x$7) -> x$7.right();
         Function1 from_extract = (x$8) -> BoxesRunTime.boxToLong($anonfun$keyStepper$6(x$8));
         Function1 from_right = var46;
         Function1 from_left = var43;
         RedBlackTree.Node from_root = var40;
         int from_maxLength = var34;
         LongBinaryTreeStepper from_ans = new LongBinaryTreeStepper(0, (Object)null, BinaryTreeStepper$.MODULE$.emptyStack(), -1, from_left, from_right, from_extract);
         from_ans.initialize(from_root, from_maxLength);
         return from_ans;
      } else if (StepperShape$.MODULE$.DoubleShape() == var2) {
         DoubleBinaryTreeStepper$ var10000 = DoubleBinaryTreeStepper$.MODULE$;
         int var32 = this.size();
         RedBlackTree.Node var39 = this.scala$collection$mutable$TreeMap$$tree.root();
         Function1 var42 = (x$9) -> x$9.left();
         Function1 var45 = (x$10) -> x$10.right();
         Function1 from_extract = (x$11) -> BoxesRunTime.boxToDouble($anonfun$keyStepper$9(x$11));
         Function1 from_right = var45;
         Function1 from_left = var42;
         RedBlackTree.Node from_root = var39;
         int from_maxLength = var32;
         DoubleBinaryTreeStepper from_ans = new DoubleBinaryTreeStepper(0, (Object)null, BinaryTreeStepper$.MODULE$.emptyStack(), -1, from_left, from_right, from_extract);
         from_ans.initialize(from_root, from_maxLength);
         return from_ans;
      } else {
         AnyBinaryTreeStepper$ var10001 = AnyBinaryTreeStepper$.MODULE$;
         int var37 = this.size();
         RedBlackTree.Node var10002 = this.scala$collection$mutable$TreeMap$$tree.root();
         Function1 var10003 = (x$12) -> x$12.left();
         Function1 var10004 = (x$13) -> x$13.right();
         Function1 from_extract = (x$14) -> x$14.key();
         Function1 from_right = var10004;
         Function1 from_left = var10003;
         RedBlackTree.Node from_root = var10002;
         int from_maxLength = var37;
         AnyBinaryTreeStepper from_ans = new AnyBinaryTreeStepper(0, (Object)null, BinaryTreeStepper$.MODULE$.emptyStack(), -1, from_left, from_right, from_extract);
         from_ans.initialize(from_root, from_maxLength);
         AnyBinaryTreeStepper var38 = from_ans;
         from_root = null;
         from_left = null;
         from_right = null;
         from_extract = null;
         Object var31 = null;
         return shape.parUnbox(var38);
      }
   }

   public Stepper valueStepper(final StepperShape shape) {
      int var2 = shape.shape();
      if (StepperShape$.MODULE$.IntShape() == var2) {
         IntBinaryTreeStepper$ var35 = IntBinaryTreeStepper$.MODULE$;
         int var36 = this.size();
         RedBlackTree.Node var41 = this.scala$collection$mutable$TreeMap$$tree.root();
         Function1 var44 = (x$15) -> x$15.left();
         Function1 var47 = (x$16) -> x$16.right();
         Function1 from_extract = (x$17) -> BoxesRunTime.boxToInteger($anonfun$valueStepper$3(x$17));
         Function1 from_right = var47;
         Function1 from_left = var44;
         RedBlackTree.Node from_root = var41;
         int from_maxLength = var36;
         IntBinaryTreeStepper from_ans = new IntBinaryTreeStepper(0, (Object)null, BinaryTreeStepper$.MODULE$.emptyStack(), -1, from_left, from_right, from_extract);
         from_ans.initialize(from_root, from_maxLength);
         return from_ans;
      } else if (StepperShape$.MODULE$.LongShape() == var2) {
         LongBinaryTreeStepper$ var33 = LongBinaryTreeStepper$.MODULE$;
         int var34 = this.size();
         RedBlackTree.Node var40 = this.scala$collection$mutable$TreeMap$$tree.root();
         Function1 var43 = (x$18) -> x$18.left();
         Function1 var46 = (x$19) -> x$19.right();
         Function1 from_extract = (x$20) -> BoxesRunTime.boxToLong($anonfun$valueStepper$6(x$20));
         Function1 from_right = var46;
         Function1 from_left = var43;
         RedBlackTree.Node from_root = var40;
         int from_maxLength = var34;
         LongBinaryTreeStepper from_ans = new LongBinaryTreeStepper(0, (Object)null, BinaryTreeStepper$.MODULE$.emptyStack(), -1, from_left, from_right, from_extract);
         from_ans.initialize(from_root, from_maxLength);
         return from_ans;
      } else if (StepperShape$.MODULE$.DoubleShape() == var2) {
         DoubleBinaryTreeStepper$ var10000 = DoubleBinaryTreeStepper$.MODULE$;
         int var32 = this.size();
         RedBlackTree.Node var39 = this.scala$collection$mutable$TreeMap$$tree.root();
         Function1 var42 = (x$21) -> x$21.left();
         Function1 var45 = (x$22) -> x$22.right();
         Function1 from_extract = (x$23) -> BoxesRunTime.boxToDouble($anonfun$valueStepper$9(x$23));
         Function1 from_right = var45;
         Function1 from_left = var42;
         RedBlackTree.Node from_root = var39;
         int from_maxLength = var32;
         DoubleBinaryTreeStepper from_ans = new DoubleBinaryTreeStepper(0, (Object)null, BinaryTreeStepper$.MODULE$.emptyStack(), -1, from_left, from_right, from_extract);
         from_ans.initialize(from_root, from_maxLength);
         return from_ans;
      } else {
         AnyBinaryTreeStepper$ var10001 = AnyBinaryTreeStepper$.MODULE$;
         int var37 = this.size();
         RedBlackTree.Node var10002 = this.scala$collection$mutable$TreeMap$$tree.root();
         Function1 var10003 = (x$24) -> x$24.left();
         Function1 var10004 = (x$25) -> x$25.right();
         Function1 from_extract = (x$26) -> x$26.value();
         Function1 from_right = var10004;
         Function1 from_left = var10003;
         RedBlackTree.Node from_root = var10002;
         int from_maxLength = var37;
         AnyBinaryTreeStepper from_ans = new AnyBinaryTreeStepper(0, (Object)null, BinaryTreeStepper$.MODULE$.emptyStack(), -1, from_left, from_right, from_extract);
         from_ans.initialize(from_root, from_maxLength);
         AnyBinaryTreeStepper var38 = from_ans;
         from_root = null;
         from_left = null;
         from_right = null;
         from_extract = null;
         Object var31 = null;
         return shape.parUnbox(var38);
      }
   }

   public TreeMap addOne(final Tuple2 elem) {
      RedBlackTree$.MODULE$.insert(this.scala$collection$mutable$TreeMap$$tree, elem._1(), elem._2(), this.ordering());
      return this;
   }

   public TreeMap subtractOne(final Object elem) {
      RedBlackTree$.MODULE$.delete(this.scala$collection$mutable$TreeMap$$tree, elem, this.ordering());
      return this;
   }

   public void clear() {
      RedBlackTree$.MODULE$.clear(this.scala$collection$mutable$TreeMap$$tree);
   }

   public Option get(final Object key) {
      return RedBlackTree$.MODULE$.get(this.scala$collection$mutable$TreeMap$$tree, key, this.ordering());
   }

   public TreeMap rangeImpl(final Option from, final Option until) {
      return new TreeMapProjection(from, until);
   }

   public void foreach(final Function1 f) {
      RedBlackTree$.MODULE$.foreach(this.scala$collection$mutable$TreeMap$$tree, f);
   }

   public void foreachEntry(final Function2 f) {
      RedBlackTree$.MODULE$.foreachEntry(this.scala$collection$mutable$TreeMap$$tree, f);
   }

   public int size() {
      RedBlackTree$ var10000 = RedBlackTree$.MODULE$;
      return this.scala$collection$mutable$TreeMap$$tree.size();
   }

   public int knownSize() {
      return this.size();
   }

   public boolean isEmpty() {
      return RedBlackTree$.MODULE$.isEmpty(this.scala$collection$mutable$TreeMap$$tree);
   }

   public boolean contains(final Object key) {
      return RedBlackTree$.MODULE$.contains(this.scala$collection$mutable$TreeMap$$tree, key, this.ordering());
   }

   public Tuple2 head() {
      return (Tuple2)RedBlackTree$.MODULE$.min(this.scala$collection$mutable$TreeMap$$tree).get();
   }

   public Tuple2 last() {
      return (Tuple2)RedBlackTree$.MODULE$.max(this.scala$collection$mutable$TreeMap$$tree).get();
   }

   public Option minAfter(final Object key) {
      return RedBlackTree$.MODULE$.minAfter(this.scala$collection$mutable$TreeMap$$tree, key, this.ordering());
   }

   public Option maxBefore(final Object key) {
      return RedBlackTree$.MODULE$.maxBefore(this.scala$collection$mutable$TreeMap$$tree, key, this.ordering());
   }

   public String className() {
      return "TreeMap";
   }

   // $FF: synthetic method
   public static final int $anonfun$keyStepper$3(final RedBlackTree.Node x$5) {
      return BoxesRunTime.unboxToInt(x$5.key());
   }

   // $FF: synthetic method
   public static final long $anonfun$keyStepper$6(final RedBlackTree.Node x$8) {
      return BoxesRunTime.unboxToLong(x$8.key());
   }

   // $FF: synthetic method
   public static final double $anonfun$keyStepper$9(final RedBlackTree.Node x$11) {
      return BoxesRunTime.unboxToDouble(x$11.key());
   }

   // $FF: synthetic method
   public static final int $anonfun$valueStepper$3(final RedBlackTree.Node x$17) {
      return BoxesRunTime.unboxToInt(x$17.value());
   }

   // $FF: synthetic method
   public static final long $anonfun$valueStepper$6(final RedBlackTree.Node x$20) {
      return BoxesRunTime.unboxToLong(x$20.value());
   }

   // $FF: synthetic method
   public static final double $anonfun$valueStepper$9(final RedBlackTree.Node x$23) {
      return BoxesRunTime.unboxToDouble(x$23.value());
   }

   public TreeMap(final RedBlackTree.Tree tree, final Ordering ordering) {
      this.scala$collection$mutable$TreeMap$$tree = tree;
      this.ordering = ordering;
   }

   public TreeMap(final Ordering ord) {
      this(RedBlackTree.Tree$.MODULE$.empty(), ord);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private final class TreeMapProjection extends TreeMap {
      private final Option from;
      private final Option until;
      // $FF: synthetic field
      private final TreeMap $outer;

      private Option pickLowerBound(final Option newFrom) {
         Option var4 = this.from;
         if (var4 instanceof Some) {
            Object fr = ((Some)var4).value();
            if (newFrom instanceof Some) {
               Object newFr = ((Some)newFrom).value();
               return new Some(this.ordering().max(fr, newFr));
            }
         }

         return None$.MODULE$.equals(var4) ? newFrom : this.from;
      }

      private Option pickUpperBound(final Option newUntil) {
         Option var4 = this.until;
         if (var4 instanceof Some) {
            Object unt = ((Some)var4).value();
            if (newUntil instanceof Some) {
               Object newUnt = ((Some)newUntil).value();
               return new Some(this.ordering().min(unt, newUnt));
            }
         }

         return None$.MODULE$.equals(var4) ? newUntil : this.until;
      }

      private boolean isInsideViewBounds(final Object key) {
         boolean afterFrom = this.from.isEmpty() || this.ordering().compare(this.from.get(), key) <= 0;
         boolean beforeUntil = this.until.isEmpty() || this.ordering().compare(key, this.until.get()) < 0;
         return afterFrom && beforeUntil;
      }

      public TreeMap rangeImpl(final Option from, final Option until) {
         return this.$outer.new TreeMapProjection(this.pickLowerBound(from), this.pickUpperBound(until));
      }

      public Option get(final Object key) {
         return (Option)(this.isInsideViewBounds(key) ? RedBlackTree$.MODULE$.get(this.$outer.scala$collection$mutable$TreeMap$$tree, key, this.ordering()) : None$.MODULE$);
      }

      public Iterator iterator() {
         RedBlackTree$ var10000 = RedBlackTree$.MODULE$;
         if (this.$outer.scala$collection$mutable$TreeMap$$tree.size() == 0) {
            Iterator$ var7 = Iterator$.MODULE$;
            return Iterator$.scala$collection$Iterator$$_empty;
         } else {
            var10000 = RedBlackTree$.MODULE$;
            RedBlackTree.Tree var6 = this.$outer.scala$collection$mutable$TreeMap$$tree;
            Option var10001 = this.from;
            Option var10002 = this.until;
            Ordering iterator_evidence$3 = this.ordering();
            Option iterator_end = var10002;
            Option iterator_start = var10001;
            RedBlackTree.Tree iterator_tree = var6;
            return new RedBlackTree.EntriesIterator(iterator_tree, iterator_start, iterator_end, iterator_evidence$3);
         }
      }

      public Iterator keysIterator() {
         RedBlackTree$ var10000 = RedBlackTree$.MODULE$;
         if (this.$outer.scala$collection$mutable$TreeMap$$tree.size() == 0) {
            Iterator$ var7 = Iterator$.MODULE$;
            return Iterator$.scala$collection$Iterator$$_empty;
         } else {
            var10000 = RedBlackTree$.MODULE$;
            RedBlackTree.Tree var6 = this.$outer.scala$collection$mutable$TreeMap$$tree;
            Option var10001 = this.from;
            Option var10002 = this.until;
            Ordering keysIterator_evidence$4 = this.ordering();
            Option keysIterator_end = var10002;
            Option keysIterator_start = var10001;
            RedBlackTree.Tree keysIterator_tree = var6;
            return new RedBlackTree.KeysIterator(keysIterator_tree, keysIterator_start, keysIterator_end, keysIterator_evidence$4);
         }
      }

      public Iterator valuesIterator() {
         RedBlackTree$ var10000 = RedBlackTree$.MODULE$;
         if (this.$outer.scala$collection$mutable$TreeMap$$tree.size() == 0) {
            Iterator$ var7 = Iterator$.MODULE$;
            return Iterator$.scala$collection$Iterator$$_empty;
         } else {
            var10000 = RedBlackTree$.MODULE$;
            RedBlackTree.Tree var6 = this.$outer.scala$collection$mutable$TreeMap$$tree;
            Option var10001 = this.from;
            Option var10002 = this.until;
            Ordering valuesIterator_evidence$5 = this.ordering();
            Option valuesIterator_end = var10002;
            Option valuesIterator_start = var10001;
            RedBlackTree.Tree valuesIterator_tree = var6;
            return new RedBlackTree.ValuesIterator(valuesIterator_tree, valuesIterator_start, valuesIterator_end, valuesIterator_evidence$5);
         }
      }

      public Iterator keysIteratorFrom(final Object start) {
         RedBlackTree$ var10000 = RedBlackTree$.MODULE$;
         if (this.$outer.scala$collection$mutable$TreeMap$$tree.size() == 0) {
            Iterator$ var8 = Iterator$.MODULE$;
            return Iterator$.scala$collection$Iterator$$_empty;
         } else {
            var10000 = RedBlackTree$.MODULE$;
            RedBlackTree.Tree var7 = this.$outer.scala$collection$mutable$TreeMap$$tree;
            Option var10001 = this.pickLowerBound(new Some(start));
            Option var10002 = this.until;
            Ordering keysIterator_evidence$4 = this.ordering();
            Option keysIterator_end = var10002;
            Option keysIterator_start = var10001;
            RedBlackTree.Tree keysIterator_tree = var7;
            return new RedBlackTree.KeysIterator(keysIterator_tree, keysIterator_start, keysIterator_end, keysIterator_evidence$4);
         }
      }

      public Iterator iteratorFrom(final Object start) {
         RedBlackTree$ var10000 = RedBlackTree$.MODULE$;
         if (this.$outer.scala$collection$mutable$TreeMap$$tree.size() == 0) {
            Iterator$ var8 = Iterator$.MODULE$;
            return Iterator$.scala$collection$Iterator$$_empty;
         } else {
            var10000 = RedBlackTree$.MODULE$;
            RedBlackTree.Tree var7 = this.$outer.scala$collection$mutable$TreeMap$$tree;
            Option var10001 = this.pickLowerBound(new Some(start));
            Option var10002 = this.until;
            Ordering iterator_evidence$3 = this.ordering();
            Option iterator_end = var10002;
            Option iterator_start = var10001;
            RedBlackTree.Tree iterator_tree = var7;
            return new RedBlackTree.EntriesIterator(iterator_tree, iterator_start, iterator_end, iterator_evidence$3);
         }
      }

      public Iterator valuesIteratorFrom(final Object start) {
         RedBlackTree$ var10000 = RedBlackTree$.MODULE$;
         if (this.$outer.scala$collection$mutable$TreeMap$$tree.size() == 0) {
            Iterator$ var8 = Iterator$.MODULE$;
            return Iterator$.scala$collection$Iterator$$_empty;
         } else {
            var10000 = RedBlackTree$.MODULE$;
            RedBlackTree.Tree var7 = this.$outer.scala$collection$mutable$TreeMap$$tree;
            Option var10001 = this.pickLowerBound(new Some(start));
            Option var10002 = this.until;
            Ordering valuesIterator_evidence$5 = this.ordering();
            Option valuesIterator_end = var10002;
            Option valuesIterator_start = var10001;
            RedBlackTree.Tree valuesIterator_tree = var7;
            return new RedBlackTree.ValuesIterator(valuesIterator_tree, valuesIterator_start, valuesIterator_end, valuesIterator_evidence$5);
         }
      }

      public int size() {
         RedBlackTree$ var10000 = RedBlackTree$.MODULE$;
         if (this.$outer.scala$collection$mutable$TreeMap$$tree.size() == 0) {
            return 0;
         } else {
            Iterator var1 = this.iterator();
            if (var1 == null) {
               throw null;
            } else {
               return var1.size();
            }
         }
      }

      public int knownSize() {
         RedBlackTree$ var10000 = RedBlackTree$.MODULE$;
         return this.$outer.scala$collection$mutable$TreeMap$$tree.size() == 0 ? 0 : -1;
      }

      public boolean isEmpty() {
         RedBlackTree$ var10000 = RedBlackTree$.MODULE$;
         return this.$outer.scala$collection$mutable$TreeMap$$tree.size() == 0 || !this.iterator().hasNext();
      }

      public boolean contains(final Object key) {
         return this.isInsideViewBounds(key) && RedBlackTree$.MODULE$.contains(this.$outer.scala$collection$mutable$TreeMap$$tree, key, this.ordering());
      }

      public Tuple2 head() {
         return (Tuple2)this.headOption().get();
      }

      public Option headOption() {
         Option entry = this.from.isDefined() ? RedBlackTree$.MODULE$.minAfter(this.$outer.scala$collection$mutable$TreeMap$$tree, this.from.get(), this.ordering()) : RedBlackTree$.MODULE$.min(this.$outer.scala$collection$mutable$TreeMap$$tree);
         Option var4 = this.until;
         if (entry instanceof Some) {
            Tuple2 e = (Tuple2)((Some)entry).value();
            if (var4 instanceof Some) {
               Object unt = ((Some)var4).value();
               if (this.ordering().compare(e._1(), unt) >= 0) {
                  return None$.MODULE$;
               }
            }
         }

         return entry;
      }

      public Tuple2 last() {
         return (Tuple2)this.lastOption().get();
      }

      public Option lastOption() {
         Option entry = this.until.isDefined() ? RedBlackTree$.MODULE$.maxBefore(this.$outer.scala$collection$mutable$TreeMap$$tree, this.until.get(), this.ordering()) : RedBlackTree$.MODULE$.max(this.$outer.scala$collection$mutable$TreeMap$$tree);
         Option var4 = this.from;
         if (entry instanceof Some) {
            Tuple2 e = (Tuple2)((Some)entry).value();
            if (var4 instanceof Some) {
               Object fr = ((Some)var4).value();
               if (this.ordering().compare(e._1(), fr) < 0) {
                  return None$.MODULE$;
               }
            }
         }

         return entry;
      }

      public void foreach(final Function1 f) {
         this.iterator().foreach(f);
      }

      public TreeMap clone() {
         return ((TreeMap)MapOps.clone$(this)).rangeImpl(this.from, this.until);
      }

      public TreeMapProjection(final Option from, final Option until) {
         this.from = from;
         this.until = until;
         if (TreeMap.this == null) {
            throw null;
         } else {
            this.$outer = TreeMap.this;
            super(TreeMap.this.scala$collection$mutable$TreeMap$$tree, TreeMap.this.ordering());
         }
      }
   }
}
