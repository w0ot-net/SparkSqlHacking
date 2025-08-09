package breeze.linalg.operators;

import breeze.generic.MMRegistry2;
import breeze.generic.UFunc;
import breeze.linalg.CSCMatrix;
import breeze.linalg.CSCMatrix$;
import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.LSMR$;
import breeze.linalg.SparseVector;
import breeze.linalg.Vector;
import breeze.linalg.VectorBuilder;
import breeze.linalg.max$;
import breeze.linalg.package$;
import breeze.linalg.support.CanZipMapKeyValues;
import breeze.linalg.support.CanZipMapValues;
import breeze.math.Field;
import breeze.math.MutableInnerProductVectorSpace;
import breeze.math.MutableInnerProductVectorSpace$;
import breeze.math.Ring;
import breeze.math.Ring$;
import breeze.math.Semiring;
import breeze.math.Semiring$;
import breeze.storage.Zero;
import breeze.storage.Zero$;
import breeze.util.ArrayUtil$;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import scala.Function2;
import scala.Function3;
import scala.Tuple2;
import scala.collection.MapView;
import scala.collection.immutable.Map;
import scala.collection.mutable.HashMap;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0019\u001dcaB\u00193!\u0003\r\t!\u000f\u0005\u0006\u0015\u0002!\ta\u0013\u0005\u0006\u001f\u0002!\u0019\u0001\u0015\u0005\u0006w\u0002!\u0019\u0001 \u0005\b\u0003?\u0001A1AA\u0011\u0011\u001d\tI\u0006\u0001C\u0002\u00037Bq!a \u0001\t\u0007\t\t\tC\u0004\u0002*\u0002!\u0019!a+\t\u000f\u0005-\u0007\u0001b\u0001\u0002N\"9\u00111\u001e\u0001\u0005\u0004\u00055\bb\u0002B\u0010\u0001\u0011\r!\u0011\u0005\u0005\b\u0005+\u0002A1\u0001B,\u0011\u001d\u0011)\b\u0001C\u0002\u0005oBqA!&\u0001\t\u0007\u00119\nC\u0004\u00036\u0002!\u0019Aa.\t\u000f\t=\u0007\u0001b\u0001\u0003R\"9!q\u001e\u0001\u0005\u0004\tE\bbBB\t\u0001\u0011\r11\u0003\u0005\b\u0007c\u0001A1AB\u001a\u0011\u001d\u0019\t\u0006\u0001C\u0002\u0007'Bqa!\u001e\u0001\t\u0007\u00199\bC\u0004\u0004\u0016\u0002!\u0019aa&\t\u000f\rU\u0006\u0001b\u0001\u00048\"91q\u001a\u0001\u0005\u0004\rE\u0007bBBu\u0001\u0011\r11\u001e\u0005\b\t\u0007\u0001A1\u0001C\u0003\u0011\u001d!i\u0002\u0001C\t\t?Aq\u0001b\u0014\u0001\t\u0007!\t\u0006C\u0004\u0005l\u0001!\u0019\u0001\"\u001c\t\u000f\u0011\u0015\u0005\u0001b\u0001\u0005\b\"9Aq\u0014\u0001\u0005\u0004\u0011\u0005\u0006b\u0002C]\u0001\u0011\rA1\u0018\u0005\b\t'\u0004A1\u0001Ck\u0011\u001d!i\u000f\u0001C\u0002\t_Dq!b\u0002\u0001\t\u0007)I\u0001C\u0004\u0006\"\u0001!\u0019!b\t\t\u000f\u0015m\u0002\u0001b\u0001\u0006>!9QQ\u000b\u0001\u0005\u0004\u0015]\u0003bBC8\u0001\u0011\rQ\u0011\u000f\u0005\b\u000b\u0013\u0003A1ACF\u0011\u001d)\u0019\u000b\u0001C\u0002\u000bKCq!\"0\u0001\t\u0007)y\fC\u0004\u0006X\u0002!\u0019!\"7\t\u0013\u0019\r\u0001A1A\u0005\u0004\u0019\u0015qa\u0002D\u0005\u0001!\u0005a1\u0002\u0004\b\r\u001f\u0001\u0001\u0012\u0001D\t\u0011\u001d1Y\"\fC\u0001\r;AqAb\b.\t\u00032\t\u0003C\u0004\u0007,5\"IA\"\f\u0003#\r\u001b6)T1ue&Dx\n]:`%&twM\u0003\u00024i\u0005Iq\u000e]3sCR|'o\u001d\u0006\u0003kY\na\u0001\\5oC2<'\"A\u001c\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0019B\u0001\u0001\u001eA\tB\u00111HP\u0007\u0002y)\tQ(A\u0003tG\u0006d\u0017-\u0003\u0002@y\t1\u0011I\\=SK\u001a\u0004\"!\u0011\"\u000e\u0003IJ!a\u0011\u001a\u0003'\r\u001b6)T1ue&Dx\n]:M_^\u0004&/[8\u0011\u0005\u0015CU\"\u0001$\u000b\u0005\u001d3\u0014\u0001B;uS2L!!\u0013$\u0003'M+'/[1mSj\f'\r\\3M_\u001e<\u0017N\\4\u0002\r\u0011Jg.\u001b;%)\u0005a\u0005CA\u001eN\u0013\tqEH\u0001\u0003V]&$\u0018!C2tG~{\u0005OT3h+\t\t&\rF\u0002SWN\u0004Ba\u0015,]9:\u0011\u0011\tV\u0005\u0003+J\nQa\u00149OK\u001eL!a\u0016-\u0003\t%k\u0007\u000f\\\u0005\u00033j\u0013Q!\u0016$v]\u000eT!a\u0017\u001c\u0002\u000f\u001d,g.\u001a:jGB\u0019QL\u00181\u000e\u0003QJ!a\u0018\u001b\u0003\u0013\r\u001b6)T1ue&D\bCA1c\u0019\u0001!Qa\u0019\u0002C\u0002\u0011\u0014\u0011\u0001V\t\u0003K\"\u0004\"a\u000f4\n\u0005\u001dd$a\u0002(pi\"Lgn\u001a\t\u0003w%L!A\u001b\u001f\u0003\u0007\u0005s\u0017\u0010C\u0004m\u0005\u0005\u0005\t9A7\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007\u000e\t\u0004]F\u0004W\"A8\u000b\u0005A4\u0014\u0001B7bi\"L!A]8\u0003\tIKgn\u001a\u0005\bi\n\t\t\u0011q\u0001v\u0003-)g/\u001b3f]\u000e,G%M\u001b\u0011\u0007YL\b-D\u0001x\u0015\tAH(A\u0004sK\u001adWm\u0019;\n\u0005i<(\u0001C\"mCN\u001cH+Y4\u0002\u0017\r\u001c8mU2bY\u0016\fE\rZ\u000b\u0004{\u00065A#\u0002@\u0002\u0010\u0005e\u0001#C@\u0002\u0006\u0005%\u00111BA\u0005\u001d\ri\u0016\u0011A\u0005\u0004\u0003\u0007!\u0014\u0001C:dC2,\u0017\t\u001a3\n\u0007\u0005\u001d\u0001L\u0001\u0007J]Bc\u0017mY3J[Bd7\u0007\u0005\u0003^=\u0006-\u0001cA1\u0002\u000e\u0011)1m\u0001b\u0001I\"I\u0011\u0011C\u0002\u0002\u0002\u0003\u000f\u00111C\u0001\fKZLG-\u001a8dK\u0012\nd\u0007E\u0003o\u0003+\tY!C\u0002\u0002\u0018=\u0014\u0001bU3nSJLgn\u001a\u0005\n\u00037\u0019\u0011\u0011!a\u0002\u0003;\t1\"\u001a<jI\u0016t7-\u001a\u00132oA!a/_A\u0006\u0003I\u0019\u0017M\\'vY6{fkX*f[&\u0014\u0018N\\4\u0016\t\u0005\r\u0012q\u0006\u000b\t\u0003K\ti$a\u0011\u0002TAY\u0011)a\n\u0002,\u0005E\u0012qGA\u0019\u0013\r\tIC\r\u0002\u000f\u0005&t\u0017M]=SK\u001eL7\u000f\u001e:z!\u0011if,!\f\u0011\u0007\u0005\fy\u0003B\u0003d\t\t\u0007A\rE\u0003^\u0003g\ti#C\u0002\u00026Q\u0012aAV3di>\u0014hbA!\u0002:%\u0019\u00111\b\u001a\u0002\u0017=\u0003X*\u001e7NCR\u0014\u0018\u000e\u001f\u0005\n\u0003\u007f!\u0011\u0011!a\u0002\u0003\u0003\n1\"\u001a<jI\u0016t7-\u001a\u00132qA)a.!\u0006\u0002.!I\u0011Q\t\u0003\u0002\u0002\u0003\u000f\u0011qI\u0001\fKZLG-\u001a8dK\u0012\n\u0014\b\u0005\u0004\u0002J\u0005=\u0013QF\u0007\u0003\u0003\u0017R1!!\u00147\u0003\u001d\u0019Ho\u001c:bO\u0016LA!!\u0015\u0002L\t!!,\u001a:p\u0011%\t)\u0006BA\u0001\u0002\b\t9&A\u0006fm&$WM\\2fII\u0002\u0004\u0003\u0002<z\u0003[\t1cY1o\u001bVdWjX*W?N+W.\u001b:j]\u001e,B!!\u0018\u0002fQA\u0011qLA7\u0003g\nI\bE\u0006B\u0003O\t\t'a\u001a\u00028\u0005\u001d\u0004\u0003B/_\u0003G\u00022!YA3\t\u0015\u0019WA1\u0001e!\u0015i\u0016\u0011NA2\u0013\r\tY\u0007\u000e\u0002\r'B\f'o]3WK\u000e$xN\u001d\u0005\n\u0003_*\u0011\u0011!a\u0002\u0003c\n1\"\u001a<jI\u0016t7-\u001a\u00133cA)a.!\u0006\u0002d!I\u0011QO\u0003\u0002\u0002\u0003\u000f\u0011qO\u0001\fKZLG-\u001a8dK\u0012\u0012$\u0007\u0005\u0004\u0002J\u0005=\u00131\r\u0005\n\u0003w*\u0011\u0011!a\u0002\u0003{\n1\"\u001a<jI\u0016t7-\u001a\u00133gA!a/_A2\u0003M\u0019\u0017M\\'vY6{F)T0TK6L'/\u001b8h+\u0011\t\u0019)a$\u0015\u0011\u0005\u0015\u0015qSAO\u0003G\u0003\"\"a\u000e\u0002\b\u0006-\u0015\u0011SAI\u0013\r\tI\t\u0017\u0002\u0006\u00136\u0004HN\r\t\u0005;z\u000bi\tE\u0002b\u0003\u001f#Qa\u0019\u0004C\u0002\u0011\u0004R!XAJ\u0003\u001bK1!!&5\u0005-!UM\\:f\u001b\u0006$(/\u001b=\t\u0013\u0005ee!!AA\u0004\u0005m\u0015aC3wS\u0012,gnY3%eQ\u0002RA\\A\u000b\u0003\u001bC\u0011\"a(\u0007\u0003\u0003\u0005\u001d!!)\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$#'\u000e\t\u0007\u0003\u0013\ny%!$\t\u0013\u0005\u0015f!!AA\u0004\u0005\u001d\u0016aC3wS\u0012,gnY3%eY\u0002BA^=\u0002\u000e\u0006\u00192-\u00198Nk2$UjX'`'\u0016l\u0017N]5oOV!\u0011QVA[)!\ty+!/\u0002@\u0006\u0015\u0007CCA\u001c\u0003\u000f\u000b\t,a.\u00022B)Q,a%\u00024B\u0019\u0011-!.\u0005\u000b\r<!\u0019\u00013\u0011\tus\u00161\u0017\u0005\n\u0003w;\u0011\u0011!a\u0002\u0003{\u000b1\"\u001a<jI\u0016t7-\u001a\u00133oA)a.!\u0006\u00024\"I\u0011\u0011Y\u0004\u0002\u0002\u0003\u000f\u00111Y\u0001\fKZLG-\u001a8dK\u0012\u0012\u0004\b\u0005\u0004\u0002J\u0005=\u00131\u0017\u0005\n\u0003\u000f<\u0011\u0011!a\u0002\u0003\u0013\f1\"\u001a<jI\u0016t7-\u001a\u00133sA!a/_AZ\u0003I\u0019\u0017M\\'vY6{VjX*f[&\u0014\u0018N\\4\u0016\t\u0005=\u0017q\u001b\u000b\t\u0003#\fI.a8\u0002fBQ\u0011qGAD\u0003'\f\u0019.a5\u0011\tus\u0016Q\u001b\t\u0004C\u0006]G!B2\t\u0005\u0004!\u0007\"CAn\u0011\u0005\u0005\t9AAo\u0003-)g/\u001b3f]\u000e,Ge\r\u0019\u0011\u000b9\f)\"!6\t\u0013\u0005\u0005\b\"!AA\u0004\u0005\r\u0018aC3wS\u0012,gnY3%gE\u0002b!!\u0013\u0002P\u0005U\u0007\"CAt\u0011\u0005\u0005\t9AAu\u0003-)g/\u001b3f]\u000e,Ge\r\u001a\u0011\tYL\u0018Q[\u0001\u000bu&\u0004X*\u00199WC2\u001cXCBAx\u0005\u0003\u00119\u0001\u0006\u0005\u0002r\n5!1\u0003B\r!1\t\u00190!?\u0002~\u0006}(Q\u0001B\u0006\u001b\t\t)PC\u0002\u0002xR\nqa];qa>\u0014H/\u0003\u0003\u0002|\u0006U(aD\"b]jK\u0007/T1q-\u0006dW/Z:\u0011\tus\u0016q \t\u0004C\n\u0005AA\u0002B\u0002\u0013\t\u0007AMA\u0001T!\r\t'q\u0001\u0003\u0007\u0005\u0013I!\u0019\u00013\u0003\u0003I\u0003B!\u00180\u0003\u0006!I!qB\u0005\u0002\u0002\u0003\u000f!\u0011C\u0001\fKZLG-\u001a8dK\u0012\u001a4\u0007\u0005\u0003ws\n\u0015\u0001\"\u0003B\u000b\u0013\u0005\u0005\t9\u0001B\f\u0003-)g/\u001b3f]\u000e,Ge\r\u001b\u0011\u000b9\f)B!\u0002\t\u0013\tm\u0011\"!AA\u0004\tu\u0011aC3wS\u0012,gnY3%gU\u0002b!!\u0013\u0002P\t\u0015\u0011!\u0004>ja6\u000b\u0007oS3z-\u0006d7/\u0006\u0004\u0003$\t=\"q\b\u000b\t\u0005K\u0011\u0019E!\u0013\u0003PAq\u00111\u001fB\u0014\u0005W\u0011\tD!\f\u0003>\t\u0005\u0013\u0002\u0002B\u0015\u0003k\u0014!cQ1o5&\u0004X*\u00199LKf4\u0016\r\\;fgB!QL\u0018B\u0017!\r\t'q\u0006\u0003\u0007\u0005\u0007Q!\u0019\u00013\u0011\u000fm\u0012\u0019Da\u000e\u00038%\u0019!Q\u0007\u001f\u0003\rQ+\b\u000f\\33!\rY$\u0011H\u0005\u0004\u0005wa$aA%oiB\u0019\u0011Ma\u0010\u0005\r\t%!B1\u0001e!\u0011ifL!\u0010\t\u0013\t\u0015#\"!AA\u0004\t\u001d\u0013aC3wS\u0012,gnY3%gY\u0002BA^=\u0003>!I!1\n\u0006\u0002\u0002\u0003\u000f!QJ\u0001\fKZLG-\u001a8dK\u0012\u001at\u0007E\u0003o\u0003+\u0011i\u0004C\u0005\u0003R)\t\t\u0011q\u0001\u0003T\u0005YQM^5eK:\u001cW\rJ\u001a9!\u0019\tI%a\u0014\u0003>\u0005\u00112-\u00198BI\u0012lulU0TK6L'/\u001b8h+\u0011\u0011IFa\u001a\u0015\r\tm#\u0011\u000eB8!)\u0011i&a\"\u0003d\t\u0015$1\r\b\u0004\u0003\n}\u0013b\u0001B1e\u0005)q\n]!eIB!QL\u0018B3!\r\t'q\r\u0003\u0006G.\u0011\r\u0001\u001a\u0005\n\u0005WZ\u0011\u0011!a\u0002\u0005[\n1\"\u001a<jI\u0016t7-\u001a\u00134sA)a.!\u0006\u0003f!I!\u0011O\u0006\u0002\u0002\u0003\u000f!1O\u0001\fKZLG-\u001a8dK\u0012\"\u0004\u0007\u0005\u0003ws\n\u0015\u0014AD2b]N+(-T0T?JKgnZ\u000b\u0005\u0005s\u00129\t\u0006\u0004\u0003|\t%%q\u0012\t\u000b\u0005{\n9Ia!\u0003\u0006\n\rebA!\u0003\u0000%\u0019!\u0011\u0011\u001a\u0002\u000b=\u00038+\u001e2\u0011\tus&Q\u0011\t\u0004C\n\u001dE!B2\r\u0005\u0004!\u0007\"\u0003BF\u0019\u0005\u0005\t9\u0001BG\u0003-)g/\u001b3f]\u000e,G\u0005N\u0019\u0011\t9\f(Q\u0011\u0005\n\u0005#c\u0011\u0011!a\u0002\u0005'\u000b1\"\u001a<jI\u0016t7-\u001a\u00135eA!a/\u001fBC\u0003I\u0019\u0017M\\*fi6{6kX*f[&\u0014\u0018N\\4\u0016\t\te%q\u0015\u000b\u0007\u00057\u0013IKa,\u0011\u0015\tu\u0015q\u0011BR\u0005K\u0013\u0019KD\u0002B\u0005?K1A!)3\u0003\u0015y\u0005oU3u!\u0011ifL!*\u0011\u0007\u0005\u00149\u000bB\u0003d\u001b\t\u0007A\rC\u0005\u0003,6\t\t\u0011q\u0001\u0003.\u0006YQM^5eK:\u001cW\r\n\u001b4!\u0015q\u0017Q\u0003BS\u0011%\u0011\t,DA\u0001\u0002\b\u0011\u0019,A\u0006fm&$WM\\2fIQ\"\u0004\u0003\u0002<z\u0005K\u000b!dY1o\u001bVdWjX*`%&twmX(q\u001bVdW*\u0019;sSb,BA!/\u0003BR1!1\u0018Bb\u0005\u0013\u0004\"\"a\u000e\u0002\b\nu&q\u0018B_!\u0011ifLa0\u0011\u0007\u0005\u0014\t\rB\u0003d\u001d\t\u0007A\rC\u0005\u0003F:\t\t\u0011q\u0001\u0003H\u0006YQM^5eK:\u001cW\r\n\u001b6!\u0011q\u0017Oa0\t\u0013\t-g\"!AA\u0004\t5\u0017aC3wS\u0012,gnY3%iY\u0002BA^=\u0003@\u0006Q2-\u00198Nk2lulU0SS:<wl\u00149Nk2\u001c6-\u00197beV!!1\u001bBq)\u0019\u0011)Na9\u0003jBQ!q[AD\u0005;\u0014yN!8\u000f\u0007\u0005\u0013I.C\u0002\u0003\\J\n1b\u00149Nk2\u001c6-\u00197beB!QL\u0018Bp!\r\t'\u0011\u001d\u0003\u0006G>\u0011\r\u0001\u001a\u0005\n\u0005K|\u0011\u0011!a\u0002\u0005O\f1\"\u001a<jI\u0016t7-\u001a\u00135oA!a.\u001dBp\u0011%\u0011YoDA\u0001\u0002\b\u0011i/A\u0006fm&$WM\\2fIQB\u0004\u0003\u0002<z\u0005?\f\u0011eQ*D\u001b\u0006$(/\u001b=DC:lU\u000f\\*dC2\f'/T0N?N+W.\u001b:j]\u001e,BAa=\u0003|RA!Q\u001fB\u0000\u0007\u000b\u0019Y\u0001\u0005\u0006\u0003X\u0006\u001d%q\u001fB|\u0005o\u0004B!\u00180\u0003zB\u0019\u0011Ma?\u0005\r\tu\bC1\u0001e\u0005\u0005\t\u0005\"CB\u0001!\u0005\u0005\t9AB\u0002\u0003-)g/\u001b3f]\u000e,G\u0005N\u001d\u0011\u000b9\f)B!?\t\u0013\r\u001d\u0001#!AA\u0004\r%\u0011aC3wS\u0012,gnY3%kA\u0002BA^=\u0003z\"I1Q\u0002\t\u0002\u0002\u0003\u000f1qB\u0001\fKZLG-\u001a8dK\u0012*\u0014\u0007\u0005\u0004\u0002J\u0005=#\u0011`\u0001\u001d\u0007N\u001bU*\u0019;sSb\u001c\u0015M\\!eI~ku,T0TK6L'/\u001b8h+\u0011\u0019)b!\b\u0015\u0011\r]1qDB\u0013\u0007W\u0001\"B!\u0018\u0002\b\u000ee1\u0011DB\r!\u0011ifla\u0007\u0011\u0007\u0005\u001ci\u0002\u0002\u0004\u0003~F\u0011\r\u0001\u001a\u0005\n\u0007C\t\u0012\u0011!a\u0002\u0007G\t1\"\u001a<jI\u0016t7-\u001a\u00136eA)a.!\u0006\u0004\u001c!I1qE\t\u0002\u0002\u0003\u000f1\u0011F\u0001\fKZLG-\u001a8dK\u0012*4\u0007\u0005\u0004\u0002J\u0005=31\u0004\u0005\n\u0007[\t\u0012\u0011!a\u0002\u0007_\t1\"\u001a<jI\u0016t7-\u001a\u00136iA!a/_B\u000e\u0003]\u00195kQ'biJL\u0007pQ1o'V\u0014WjX'`%&tw-\u0006\u0003\u00046\ruB\u0003CB\u001c\u0007\u007f\u0019)ea\u0013\u0011\u0015\tu\u0014qQB\u001d\u0007s\u0019I\u0004\u0005\u0003^=\u000em\u0002cA1\u0004>\u00111!Q \nC\u0002\u0011D\u0011b!\u0011\u0013\u0003\u0003\u0005\u001daa\u0011\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$S'\u000e\t\u0005]F\u001cY\u0004C\u0005\u0004HI\t\t\u0011q\u0001\u0004J\u0005YQM^5eK:\u001cW\rJ\u001b7!\u0019\tI%a\u0014\u0004<!I1Q\n\n\u0002\u0002\u0003\u000f1qJ\u0001\fKZLG-\u001a8dK\u0012*t\u0007\u0005\u0003ws\u000em\u0012AD2tG~#vl\u00149`\u001fB$\u0015N^\u000b\u0005\u0007+\u001a\u0019\u0007\u0006\u0004\u0004X\r\u00154q\u000e\t\u000b\u00073\n9ia\u0018\u0004b\r}cbA!\u0004\\%\u00191Q\f\u001a\u0002\u000b=\u0003H)\u001b<\u0011\tus6\u0011\r\t\u0004C\u000e\rD!B2\u0014\u0005\u0004!\u0007\"CB4'\u0005\u0005\t9AB5\u0003-)g/\u001b3f]\u000e,G%\u000e\u001d\u0011\u000b9\u001cYg!\u0019\n\u0007\r5tNA\u0003GS\u0016dG\rC\u0005\u0004rM\t\t\u0011q\u0001\u0004t\u0005YQM^5eK:\u001cW\rJ\u001b:!\u00111\u0018p!\u0019\u0002\u001d\r\u001c8m\u0018+`\u001fB|v\n]'pIV!1\u0011PBD)\u0019\u0019Yh!#\u0004\u0010BQ1QPAD\u0007\u0007\u001b)ia!\u000f\u0007\u0005\u001by(C\u0002\u0004\u0002J\nQa\u00149N_\u0012\u0004B!\u00180\u0004\u0006B\u0019\u0011ma\"\u0005\u000b\r$\"\u0019\u00013\t\u0013\r-E#!AA\u0004\r5\u0015aC3wS\u0012,gnY3%mA\u0002RA\\B6\u0007\u000bC\u0011b!%\u0015\u0003\u0003\u0005\u001daa%\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$c'\r\t\u0005mf\u001c))\u0001\bdg\u000e|FkX(q?>\u0003\bk\\<\u0016\t\re5q\u0015\u000b\u0007\u00077\u001bIka,\u0011\u0015\ru\u0015qQBR\u0007K\u001b\u0019KD\u0002B\u0007?K1a!)3\u0003\u0015y\u0005\u000fU8x!\u0011ifl!*\u0011\u0007\u0005\u001c9\u000bB\u0003d+\t\u0007A\rC\u0005\u0004,V\t\t\u0011q\u0001\u0004.\u0006YQM^5eK:\u001cW\r\n\u001c3!\u0015q71NBS\u0011%\u0019\t,FA\u0001\u0002\b\u0019\u0019,A\u0006fm&$WM\\2fIY\u001a\u0004\u0003\u0002<z\u0007K\u000b1cY:d?\u000e\u001c8m\u0018\"bI>\u0003xl\u00149ESZ,Ba!/\u0004BR111XBb\u0007\u0013\u0004\"b!\u0017\u0002\b\u000eu6QXB_!\u0011ifla0\u0011\u0007\u0005\u001c\t\rB\u0003d-\t\u0007A\rC\u0005\u0004FZ\t\t\u0011q\u0001\u0004H\u0006YQM^5eK:\u001cW\r\n\u001c5!\u0015q71NB`\u0011%\u0019YMFA\u0001\u0002\b\u0019i-A\u0006fm&$WM\\2fIY*\u0004\u0003\u0002<z\u0007\u007f\u000b1cY:d?\u000e\u001c8m\u0018\"bI>\u0003xl\u00149N_\u0012,Baa5\u0004\\R11Q[Bo\u0007G\u0004\"b! \u0002\b\u000e]7q[Bl!\u0011ifl!7\u0011\u0007\u0005\u001cY\u000eB\u0003d/\t\u0007A\rC\u0005\u0004`^\t\t\u0011q\u0001\u0004b\u0006YQM^5eK:\u001cW\r\n\u001c7!\u0015q71NBm\u0011%\u0019)oFA\u0001\u0002\b\u00199/A\u0006fm&$WM\\2fIY:\u0004\u0003\u0002<z\u00073\f1cY:d?\u000e\u001c8m\u0018\"bI>\u0003xl\u00149Q_^,Ba!<\u0004vR11q^B|\u0007{\u0004\"b!(\u0002\b\u000eE8\u0011_By!\u0011ifla=\u0011\u0007\u0005\u001c)\u0010B\u0003d1\t\u0007A\rC\u0005\u0004zb\t\t\u0011q\u0001\u0004|\u0006YQM^5eK:\u001cW\r\n\u001c9!\u0015q71NBz\u0011%\u0019y\u0010GA\u0001\u0002\b!\t!A\u0006fm&$WM\\2fIYJ\u0004\u0003\u0002<z\u0007g\f1dQ*D\u001b\u0006$(/\u001b=DC:\u001cV\r^'`\u001b~\u001bV-\\5sS:<W\u0003\u0002C\u0004\t\u001f!b\u0001\"\u0003\u0005\u0012\u0011]\u0001C\u0003BO\u0003\u000f#Y\u0001b\u0003\u0005\fA!QL\u0018C\u0007!\r\tGq\u0002\u0003\u0006Gf\u0011\r\u0001\u001a\u0005\n\t'I\u0012\u0011!a\u0002\t+\t1\"\u001a<jI\u0016t7-\u001a\u00138aA)a.!\u0006\u0005\u000e!IA\u0011D\r\u0002\u0002\u0003\u000fA1D\u0001\fKZLG-\u001a8dK\u0012:\u0014\u0007\u0005\u0003ws\u00125\u0011AE;qI\u0006$XM\u0012:p[B+(/Z0D'\u000e+\u0002\u0002\"\t\u0005>\u0011\u0005CQ\u0007\u000b\u0005\tG!)\u0005\u0005\u0006\u0005&\u00115B1\u0007C\u001d\t\u007fqA\u0001b\n\u0005*5\t!,C\u0002\u0005,i\u000bQ!\u0016$v]\u000eLA\u0001b\f\u00052\ta\u0011J\u001c)mC\u000e,\u0017*\u001c9me)\u0019A1\u0006.\u0011\u0007\u0005$)\u0004\u0002\u0004\u00058i\u0011\r\u0001\u001a\u0002\u0003\u001fB\u0004B!\u00180\u0005<A\u0019\u0011\r\"\u0010\u0005\u000b\rT\"\u0019\u00013\u0011\u0007\u0005$\t\u0005\u0002\u0004\u0005Di\u0011\r\u0001\u001a\u0002\u0006\u001fRDWM\u001d\u0005\b\t\u000fR\u00029\u0001C%\u0003\ty\u0007\u000f\u0005\u0007\u0005&\u0011-C1\u0007C\u001d\t\u007f!I$\u0003\u0003\u0005N\u0011E\"AB+J[Bd''A\u0011j[Bdwl\u00149`\u0007N\u001bulQ*D?\u0016\fxlQ*D?2Lg\r^0Pa\u0006#G-\u0006\u0003\u0005T\u0011uCC\u0002C+\t?\")\u0007\u0005\u0005\u0003^\u0011]C\u0011\fC-\u0013\r!y\u0003\u0017\t\u0005;z#Y\u0006E\u0002b\t;\"QaY\u000eC\u0002\u0011D\u0011\u0002\"\u0019\u001c\u0003\u0003\u0005\u001d\u0001b\u0019\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$sG\r\t\u0006]\u000e-D1\f\u0005\n\tOZ\u0012\u0011!a\u0002\tS\n1\"\u001a<jI\u0016t7-\u001a\u00138gA!a/\u001fC.\u0003\u0005JW\u000e\u001d7`\u001fB|6iU\"`\u0007N\u001bu,Z9`\u0007N\u001bu\f\\5gi~{\u0005oU;c+\u0011!y\u0007b\u001e\u0015\r\u0011ED\u0011\u0010C@!!\u0011i\bb\u0016\u0005t\u0011M\u0004\u0003B/_\tk\u00022!\u0019C<\t\u0015\u0019GD1\u0001e\u0011%!Y\bHA\u0001\u0002\b!i(A\u0006fm&$WM\\2fI]\"\u0004#\u00028\u0004l\u0011U\u0004\"\u0003CA9\u0005\u0005\t9\u0001CB\u0003-)g/\u001b3f]\u000e,GeN\u001b\u0011\tYLHQO\u0001(S6\u0004HnX(q?\u000e\u001b6iX\"T\u0007~+\u0017oX\"T\u0007~c\u0017N\u001a;`\u001fBlU\u000f\\*dC2\f'/\u0006\u0003\u0005\n\u0012EEC\u0002CF\t'#I\n\u0005\u0005\u0003X\u0012]CQ\u0012CG!\u0011if\fb$\u0011\u0007\u0005$\t\nB\u0003d;\t\u0007A\rC\u0005\u0005\u0016v\t\t\u0011q\u0001\u0005\u0018\u0006YQM^5eK:\u001cW\rJ\u001c7!\u0015q71\u000eCH\u0011%!Y*HA\u0001\u0002\b!i*A\u0006fm&$WM\\2fI]:\u0004\u0003\u0002<z\t\u001f\u000b\u0011%[7qY~{\u0005oX\"T\u0007~\u001b5kQ0fc~\u001b5kQ0mS\u001a$xl\u00149TKR,B\u0001b)\u0005,R1AQ\u0015CW\tg\u0003\u0002B!(\u0005X\u0011\u001dFq\u0015\t\u0005;z#I\u000bE\u0002b\tW#Qa\u0019\u0010C\u0002\u0011D\u0011\u0002b,\u001f\u0003\u0003\u0005\u001d\u0001\"-\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$s\u0007\u000f\t\u0006]\u000e-D\u0011\u0016\u0005\n\tks\u0012\u0011!a\u0002\to\u000b1\"\u001a<jI\u0016t7-\u001a\u00138sA!a/\u001fCU\u0003\u0005JW\u000e\u001d7`\u001fB|6iU\"`\u0007N\u001bu,Z9`\u0007N\u001bu\f\\5gi~{\u0005\u000fR5w+\u0011!i\f\"2\u0015\r\u0011}Fq\u0019Cg!!\u0019I\u0006b\u0016\u0005B\u0012\u0005\u0007\u0003B/_\t\u0007\u00042!\u0019Cc\t\u0015\u0019wD1\u0001e\u0011%!ImHA\u0001\u0002\b!Y-A\u0006fm&$WM\\2fIa\u0002\u0004#\u00028\u0004l\u0011\r\u0007\"\u0003Ch?\u0005\u0005\t9\u0001Ci\u0003-)g/\u001b3f]\u000e,G\u0005O\u0019\u0011\tYLH1Y\u0001\"S6\u0004HnX(q?\u000e\u001b6iX\"T\u0007~+\u0017oX\"T\u0007~c\u0017N\u001a;`\u001fB\u0004vn^\u000b\u0005\t/$y\u000e\u0006\u0004\u0005Z\u0012\u0005Hq\u001d\t\t\u0007;#9\u0006b7\u0005\\B!QL\u0018Co!\r\tGq\u001c\u0003\u0006G\u0002\u0012\r\u0001\u001a\u0005\n\tG\u0004\u0013\u0011!a\u0002\tK\f1\"\u001a<jI\u0016t7-\u001a\u00139eA)ana\u001b\u0005^\"IA\u0011\u001e\u0011\u0002\u0002\u0003\u000fA1^\u0001\fKZLG-\u001a8dK\u0012B4\u0007\u0005\u0003ws\u0012u\u0017!I5na2|v\n]0D'\u000e{6iU\"`KF|6iU\"`Y&4GoX(q\u001b>$W\u0003\u0002Cy\ts$b\u0001b=\u0005|\u0016\u0005\u0001\u0003CB?\t/\")\u0010\">\u0011\tusFq\u001f\t\u0004C\u0012eH!B2\"\u0005\u0004!\u0007\"\u0003C\u007fC\u0005\u0005\t9\u0001C\u0000\u0003-)g/\u001b3f]\u000e,G\u0005\u000f\u001b\u0011\u000b9\u001cY\u0007b>\t\u0013\u0015\r\u0011%!AA\u0004\u0015\u0015\u0011aC3wS\u0012,gnY3%qU\u0002BA^=\u0005x\u00061\u0013.\u001c9m?>\u0003x,\u00138QY\u0006\u001cWmX\"T\u0007~#v\f\\5gi~{\u0005/T;m\u001b\u0006$(/\u001b=\u0016\t\u0015-Q1\u0003\u000b\u0007\u000b\u001b))\"b\u0007\u0011\u0011\u0005]BqKC\b\u000b#\u0001B!\u00180\u0006\u0012A\u0019\u0011-b\u0005\u0005\u000b\r\u0014#\u0019\u00013\t\u0013\u0015]!%!AA\u0004\u0015e\u0011aC3wS\u0012,gnY3%qY\u0002RA\\B6\u000b#A\u0011\"\"\b#\u0003\u0003\u0005\u001d!b\b\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0003h\u000e\t\u0005mf,\t\"\u0001\u0011j[Bdwl\u00149`\u0013:\u0004F.Y2f?\u000e\u001b6i\u0018+`Y&4GoX(q'\u0016$X\u0003BC\u0013\u000b[!b!b\n\u00060\u0015U\u0002\u0003\u0003BO\t/*I#b\u000b\u0011\tusV1\u0006\t\u0004C\u00165B!B2$\u0005\u0004!\u0007\"CC\u0019G\u0005\u0005\t9AC\u001a\u0003-)g/\u001b3f]\u000e,G\u0005\u000f\u001d\u0011\u000b9\u001cY'b\u000b\t\u0013\u0015]2%!AA\u0004\u0015e\u0012aC3wS\u0012,gnY3%qe\u0002BA^=\u0006,\u0005\u0001\u0013.\u001c9m?>\u0003x,\u00138QY\u0006\u001cWmX\"T\u0007~#v\f\\5gi~{\u0005oU;c+\u0011)y$b\u0012\u0015\r\u0015\u0005S\u0011JC(!!\u0011i\bb\u0016\u0006D\u0015\u0015\u0003\u0003B/_\u000b\u000b\u00022!YC$\t\u0015\u0019GE1\u0001e\u0011%)Y\u0005JA\u0001\u0002\b)i%A\u0006fm&$WM\\2fIe\u0002\u0004#\u00028\u0004l\u0015\u0015\u0003\"CC)I\u0005\u0005\t9AC*\u0003-)g/\u001b3f]\u000e,G%O\u0019\u0011\tYLXQI\u0001!S6\u0004HnX(q?&s\u0007\u000b\\1dK~\u001b5kQ0U?2Lg\r^0Pa\u0006#G-\u0006\u0003\u0006Z\u0015\u0005DCBC.\u000bG*I\u0007\u0005\u0005\u0003^\u0011]SQLC0!\u0011if,b\u0018\u0011\u0007\u0005,\t\u0007B\u0003dK\t\u0007A\rC\u0005\u0006f\u0015\n\t\u0011q\u0001\u0006h\u0005YQM^5eK:\u001cW\rJ\u001d3!\u0015q71NC0\u0011%)Y'JA\u0001\u0002\b)i'A\u0006fm&$WM\\2fIe\u001a\u0004\u0003\u0002<z\u000b?\na%[7qY~{\u0005oX%o!2\f7-Z0D'\u000e{Fk\u00187jMR|v\n]'vYN\u001b\u0017\r\\1s+\u0011)\u0019(b\u001f\u0015\r\u0015UTQPCB!!\u00119\u000eb\u0016\u0006x\u0015e\u0004\u0003B/_\u000bs\u00022!YC>\t\u0015\u0019gE1\u0001e\u0011%)yHJA\u0001\u0002\b)\t)A\u0006fm&$WM\\2fIe\"\u0004#\u00028\u0004l\u0015e\u0004\"CCCM\u0005\u0005\t9ACD\u0003-)g/\u001b3f]\u000e,G%O\u001b\u0011\tYLX\u0011P\u0001!S6\u0004HnX(q?&s\u0007\u000b\\1dK~\u001b5kQ0U?2Lg\r^0Pa\u0012Kg/\u0006\u0003\u0006\u000e\u0016UECBCH\u000b/+i\n\u0005\u0005\u0004Z\u0011]S\u0011SCJ!\u0011if,b%\u0011\u0007\u0005,)\nB\u0003dO\t\u0007A\rC\u0005\u0006\u001a\u001e\n\t\u0011q\u0001\u0006\u001c\u0006YQM^5eK:\u001cW\rJ\u001d7!\u0015q71NCJ\u0011%)yjJA\u0001\u0002\b)\t+A\u0006fm&$WM\\2fIe:\u0004\u0003\u0002<z\u000b'\u000b\u0001%[7qY~{\u0005oX%o!2\f7-Z0D'\u000e{Fk\u00187jMR|v\n]'pIV!QqUCX)\u0019)I+\"-\u00068BA1Q\u0010C,\u000bW+i\u000b\u0005\u0003^=\u00165\u0006cA1\u00060\u0012)1\r\u000bb\u0001I\"IQ1\u0017\u0015\u0002\u0002\u0003\u000fQQW\u0001\fKZLG-\u001a8dK\u0012J\u0004\bE\u0003o\u0007W*i\u000bC\u0005\u0006:\"\n\t\u0011q\u0001\u0006<\u0006YQM^5eK:\u001cW\rJ\u001d:!\u00111\u00180\",\u0002A%l\u0007\u000f\\0Pa~Ke\u000e\u00157bG\u0016|6iU\"`)~c\u0017N\u001a;`\u001fB\u0004vn^\u000b\u0005\u000b\u0003,I\r\u0006\u0004\u0006D\u0016-W\u0011\u001b\t\t\u0007;#9&\"2\u0006HB!QLXCd!\r\tW\u0011\u001a\u0003\u0006G&\u0012\r\u0001\u001a\u0005\n\u000b\u001bL\u0013\u0011!a\u0002\u000b\u001f\fA\"\u001a<jI\u0016t7-\u001a\u00132aA\u0002RA\\B6\u000b\u000fD\u0011\"b5*\u0003\u0003\u0005\u001d!\"6\u0002\u0019\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007M\u0019\u0011\tYLXqY\u0001%S6\u0004HnX(q'>dg/Z'biJL\u0007PQ=`\u0007N\u001bEi\u0018#W\t~+\u0017o\u0018#W\tV!Q1\\Cx)\u0019)i.b=\u0006zBQQq\\AD\u000bK,i/\"<\u000f\u0007\u0005+\t/C\u0002\u0006dJ\nqb\u00149T_24X-T1ue&D()\u001f\t\u0005;z+9\u000fE\u0002<\u000bSL1!b;=\u0005\u0019!u.\u001e2mKB\u0019\u0011-b<\u0005\r\u0015E(F1\u0001e\u0005\u00051\u0006bBC{U\u0001\u000fQq_\u0001\u0007[VdG/\u0014,\u0011\u0015\u0005]\u0012qQCs\u000b[,i\u000fC\u0004\u0006|*\u0002\u001d!\"@\u0002\r%\u001c\b/Y2f!\u001dqWq`Cw\u000bOL1A\"\u0001p\u0005yiU\u000f^1cY\u0016LeN\\3s!J|G-^2u-\u0016\u001cGo\u001c:Ta\u0006\u001cW-A\u0012j[Bdwl\u00149T_24X-T1ue&D()_0D'\u000e{6iU\"`KF|6iU\"\u0016\u0005\u0019\u001d\u0001CCCp\u0003\u000f+)/\":\u0006f\u0006\u0019bI]8cK:LWo]\"T\u0007B\u0013x\u000eZ;diB\u0019aQB\u0017\u000e\u0003\u0001\u00111C\u0012:pE\u0016t\u0017.^:D'\u000e\u0003&o\u001c3vGR\u001cB!\f\u001e\u0007\u0014AQaQCAD\u000bK,)/b:\u000f\u0007\u000539\"C\u0002\u0007\u001aI\n!b\u00149Nk2LeN\\3s\u0003\u0019a\u0014N\\5u}Q\u0011a1B\u0001\u0006CB\u0004H.\u001f\u000b\u0007\u000bO4\u0019Cb\n\t\u000f\u0019\u0015r\u00061\u0001\u0006f\u0006\ta\u000fC\u0004\u0007*=\u0002\r!\":\u0002\u0005Y\u0014\u0014a\u00023q%\u0006tw-\u001a\u000b\u000f\u000bO4yCb\r\u00078\u0019mbq\bD\"\u0011\u001d1\t\u0004\ra\u0001\u000bK\f\u0011a\u001d\u0005\b\rk\u0001\u0004\u0019\u0001B\u001c\u0003\u0019\u0019()Z4j]\"9a\u0011\b\u0019A\u0002\t]\u0012\u0001B:F]\u0012DqA\"\u00101\u0001\u0004))/A\u0001u\u0011\u001d1\t\u0005\ra\u0001\u0005o\ta\u0001\u001e\"fO&t\u0007b\u0002D#a\u0001\u0007!qG\u0001\u0005i\u0016sG\r"
)
public interface CSCMatrixOps_Ring extends CSCMatrixOpsLowPrio {
   FrobeniusCSCProduct$ FrobeniusCSCProduct();

   void breeze$linalg$operators$CSCMatrixOps_Ring$_setter_$impl_OpSolveMatrixBy_CSC_CSC_eq_CSC_$eq(final UFunc.UImpl2 x$1);

   // $FF: synthetic method
   static UFunc.UImpl csc_OpNeg$(final CSCMatrixOps_Ring $this, final Ring evidence$14, final ClassTag evidence$15) {
      return $this.csc_OpNeg(evidence$14, evidence$15);
   }

   default UFunc.UImpl csc_OpNeg(final Ring evidence$14, final ClassTag evidence$15) {
      return new UFunc.UImpl(evidence$14) {
         private final Ring ring;

         public double apply$mcDD$sp(final double v) {
            return UFunc.UImpl.apply$mcDD$sp$(this, v);
         }

         public float apply$mcDF$sp(final double v) {
            return UFunc.UImpl.apply$mcDF$sp$(this, v);
         }

         public int apply$mcDI$sp(final double v) {
            return UFunc.UImpl.apply$mcDI$sp$(this, v);
         }

         public double apply$mcFD$sp(final float v) {
            return UFunc.UImpl.apply$mcFD$sp$(this, v);
         }

         public float apply$mcFF$sp(final float v) {
            return UFunc.UImpl.apply$mcFF$sp$(this, v);
         }

         public int apply$mcFI$sp(final float v) {
            return UFunc.UImpl.apply$mcFI$sp$(this, v);
         }

         public double apply$mcID$sp(final int v) {
            return UFunc.UImpl.apply$mcID$sp$(this, v);
         }

         public float apply$mcIF$sp(final int v) {
            return UFunc.UImpl.apply$mcIF$sp$(this, v);
         }

         public int apply$mcII$sp(final int v) {
            return UFunc.UImpl.apply$mcII$sp$(this, v);
         }

         private Ring ring() {
            return this.ring;
         }

         public CSCMatrix apply(final CSCMatrix a) {
            CSCMatrix acp = a.copy();

            for(int c = 0; c < acp.cols(); ++c) {
               for(int ip = acp.colPtrs()[c]; ip < acp.colPtrs()[c + 1]; ++ip) {
                  int r = acp.rowIndices()[ip];
                  .MODULE$.array_update(acp.data(), ip, this.ring().negate(.MODULE$.array_apply(acp.data(), ip)));
               }
            }

            return acp;
         }

         public {
            this.ring = (Ring)scala.Predef..MODULE$.implicitly(evidence$14$1);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl3 cscScaleAdd$(final CSCMatrixOps_Ring $this, final Semiring evidence$16, final ClassTag evidence$17) {
      return $this.cscScaleAdd(evidence$16, evidence$17);
   }

   default UFunc.InPlaceImpl3 cscScaleAdd(final Semiring evidence$16, final ClassTag evidence$17) {
      return new UFunc.InPlaceImpl3(evidence$16, evidence$17) {
         private final Semiring evidence$16$1;
         private final ClassTag evidence$17$1;

         public void apply(final CSCMatrix a, final Object s, final CSCMatrix b) {
            Semiring ring = (Semiring)scala.Predef..MODULE$.implicitly(this.evidence$16$1);
            int left$macro$1 = a.rows();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(84)).append("requirement failed: Matrices must have same number of rows!: ").append("a.rows == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int left$macro$3 = a.cols();
               int right$macro$4 = b.cols();
               if (left$macro$3 != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(84)).append("requirement failed: Matrices must have same number of cols!: ").append("a.cols == b.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  int rows = a.rows();
                  int cols = a.cols();
                  if (cols != 0 && rows != 0) {
                     CSCMatrix.Builder bldr = new CSCMatrix.Builder(rows, cols, max$.MODULE$.apply$mIIIc$sp(a.activeSize(), b.activeSize(), max$.MODULE$.maxImpl2_Int()), this.evidence$17$1, this.evidence$16$1, Zero$.MODULE$.zeroFromSemiring(this.evidence$16$1));
                     int ci = 0;
                     int apStop = a.colPtrs()[0];

                     int ci1;
                     for(int bpStop = b.colPtrs()[0]; ci < cols; ci = ci1) {
                        ci1 = ci + 1;
                        int ap = apStop;
                        int bp = bpStop;
                        apStop = a.colPtrs()[ci1];
                        bpStop = b.colPtrs()[ci1];

                        while(ap < apStop || bp < bpStop) {
                           int ari = ap < apStop ? a.rowIndices()[ap] : rows;
                           int bri = bp < bpStop ? b.rowIndices()[bp] : rows;
                           if (ari == bri) {
                              bldr.add(ari, ci, ring.$plus(.MODULE$.array_apply(a.data(), ap), ring.$times(s, .MODULE$.array_apply(b.data(), bp))));
                              ++ap;
                              ++bp;
                           } else if (ari < bri) {
                              bldr.add(ari, ci, .MODULE$.array_apply(a.data(), ap));
                              ++ap;
                           } else {
                              bldr.add(bri, ci, ring.$times(s, .MODULE$.array_apply(b.data(), bp)));
                              ++bp;
                           }
                        }
                     }

                     CSCMatrix res = bldr.result(true, true);
                     a.use(res.data(), res.colPtrs(), res.rowIndices(), res.activeSize());
                  }
               }
            }
         }

         public {
            this.evidence$16$1 = evidence$16$1;
            this.evidence$17$1 = evidence$17$1;
         }
      };
   }

   // $FF: synthetic method
   static BinaryRegistry canMulM_V_Semiring$(final CSCMatrixOps_Ring $this, final Semiring evidence$18, final Zero evidence$19, final ClassTag evidence$20) {
      return $this.canMulM_V_Semiring(evidence$18, evidence$19, evidence$20);
   }

   default BinaryRegistry canMulM_V_Semiring(final Semiring evidence$18, final Zero evidence$19, final ClassTag evidence$20) {
      return new BinaryRegistry(evidence$18, evidence$20, evidence$19) {
         private final Semiring ring;
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;
         private final ClassTag evidence$20$1;
         private final Zero evidence$19$1;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

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

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         private Semiring ring() {
            return this.ring;
         }

         public DenseVector bindingMissing(final CSCMatrix a, final Vector b) {
            int left$macro$1 = a.cols();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(66)).append("requirement failed: Dimension Mismatch!: ").append("a.cols == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector res = DenseVector$.MODULE$.zeros(a.rows(), this.evidence$20$1, this.evidence$19$1);

               for(int c = 0; c < a.cols(); ++c) {
                  int rr = a.colPtrs()[c];

                  for(int rrlast = a.colPtrs()[c + 1]; rr < rrlast; ++rr) {
                     int r = a.rowIndices()[rr];
                     res.update(r, this.ring().$plus(res.apply(r), this.ring().$times(.MODULE$.array_apply(a.data(), rr), b.apply(BoxesRunTime.boxToInteger(c)))));
                  }
               }

               return res;
            }
         }

         public {
            this.evidence$20$1 = evidence$20$1;
            this.evidence$19$1 = evidence$19$1;
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            this.ring = (Semiring)scala.Predef..MODULE$.implicitly(evidence$18$1);
            Statics.releaseFence();
         }
      };
   }

   // $FF: synthetic method
   static BinaryRegistry canMulM_SV_Semiring$(final CSCMatrixOps_Ring $this, final Semiring evidence$21, final Zero evidence$22, final ClassTag evidence$23) {
      return $this.canMulM_SV_Semiring(evidence$21, evidence$22, evidence$23);
   }

   default BinaryRegistry canMulM_SV_Semiring(final Semiring evidence$21, final Zero evidence$22, final ClassTag evidence$23) {
      return new BinaryRegistry(evidence$21, evidence$23) {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;
         private final Semiring evidence$21$1;
         private final ClassTag evidence$23$1;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

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

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public SparseVector bindingMissing(final CSCMatrix a, final SparseVector b) {
            Semiring ring = (Semiring)scala.Predef..MODULE$.implicitly(this.evidence$21$1);
            int left$macro$1 = a.cols();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(66)).append("requirement failed: Dimension Mismatch!: ").append("a.cols == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder res = new VectorBuilder(a.rows(), scala.runtime.RichInt..MODULE$.min$extension(scala.Predef..MODULE$.intWrapper(b.iterableSize()), a.rows()), this.evidence$21$1, this.evidence$23$1);
               int c = 0;

               for(int lastOffset = 0; c < a.cols(); ++c) {
                  int rr = a.colPtrs()[c];
                  int rrlast = a.colPtrs()[c + 1];
                  if (rr < rrlast) {
                     int newBOffset = Arrays.binarySearch(b.index(), lastOffset, scala.math.package..MODULE$.min(b.activeSize(), c + 1), c);
                     if (newBOffset < 0) {
                        lastOffset = ~newBOffset;
                     } else {
                        while(rr < rrlast) {
                           int r = a.rowIndices()[rr];
                           res.add(r, ring.$times(.MODULE$.array_apply(a.data(), rr), b.valueAt(newBOffset)));
                           ++rr;
                        }

                        lastOffset = newBOffset + 1;
                     }
                  }
               }

               return res.toSparseVector();
            }
         }

         public {
            this.evidence$21$1 = evidence$21$1;
            this.evidence$23$1 = evidence$23$1;
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            Statics.releaseFence();
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 canMulM_DM_Semiring$(final CSCMatrixOps_Ring $this, final Semiring evidence$24, final Zero evidence$25, final ClassTag evidence$26) {
      return $this.canMulM_DM_Semiring(evidence$24, evidence$25, evidence$26);
   }

   default UFunc.UImpl2 canMulM_DM_Semiring(final Semiring evidence$24, final Zero evidence$25, final ClassTag evidence$26) {
      return new UFunc.UImpl2(evidence$24, evidence$26) {
         private final Semiring evidence$24$1;
         private final ClassTag evidence$26$1;

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

         public DenseMatrix apply(final CSCMatrix a, final DenseMatrix b) {
            Semiring ring = (Semiring)scala.Predef..MODULE$.implicitly(this.evidence$24$1);
            int left$macro$1 = a.cols();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(88)).append("requirement failed: CSCMatrix Multiplication Dimension Mismatch: ").append("a.cols == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseMatrix res = new DenseMatrix(a.rows(), b.cols(), this.evidence$26$1);

               for(int i = 0; i < b.cols(); ++i) {
                  for(int j = 0; j < a.cols(); ++j) {
                     Object v = b.apply(j, i);

                     for(int k = a.colPtrs()[j]; k < a.colPtrs()[j + 1]; ++k) {
                        res.update(a.rowIndices()[k], i, ring.$plus(res.apply(a.rowIndices()[k], i), ring.$times(v, .MODULE$.array_apply(a.data(), k))));
                     }
                  }
               }

               return res;
            }
         }

         public {
            this.evidence$24$1 = evidence$24$1;
            this.evidence$26$1 = evidence$26$1;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 canMulDM_M_Semiring$(final CSCMatrixOps_Ring $this, final Semiring evidence$27, final Zero evidence$28, final ClassTag evidence$29) {
      return $this.canMulDM_M_Semiring(evidence$27, evidence$28, evidence$29);
   }

   default UFunc.UImpl2 canMulDM_M_Semiring(final Semiring evidence$27, final Zero evidence$28, final ClassTag evidence$29) {
      return new UFunc.UImpl2(evidence$27, evidence$29) {
         private final Semiring evidence$27$1;
         private final ClassTag evidence$29$1;

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

         public DenseMatrix apply(final DenseMatrix a, final CSCMatrix b) {
            Semiring ring = (Semiring)scala.Predef..MODULE$.implicitly(this.evidence$27$1);
            int left$macro$1 = a.cols();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(88)).append("requirement failed: CSCMatrix Multiplication Dimension Mismatch: ").append("a.cols == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseMatrix res = new DenseMatrix(a.rows(), b.cols(), this.evidence$29$1);

               for(int i = 0; i < b.cols(); ++i) {
                  for(int j = b.colPtrs()[i]; j < b.colPtrs()[i + 1]; ++j) {
                     Object dval = .MODULE$.array_apply(b.data(), j);
                     int ival = b.rowIndices()[j];

                     for(int k = 0; k < a.rows(); ++k) {
                        res.update(k, i, ring.$plus(res.apply(k, i), ring.$times(a.apply(k, ival), dval)));
                     }
                  }
               }

               return res;
            }
         }

         public {
            this.evidence$27$1 = evidence$27$1;
            this.evidence$29$1 = evidence$29$1;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 canMulM_M_Semiring$(final CSCMatrixOps_Ring $this, final Semiring evidence$30, final Zero evidence$31, final ClassTag evidence$32) {
      return $this.canMulM_M_Semiring(evidence$30, evidence$31, evidence$32);
   }

   default UFunc.UImpl2 canMulM_M_Semiring(final Semiring evidence$30, final Zero evidence$31, final ClassTag evidence$32) {
      return new UFunc.UImpl2(evidence$30, evidence$32, evidence$31) {
         private final Semiring evidence$30$1;
         private final ClassTag evidence$32$1;
         private final Zero evidence$31$1;

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

         public CSCMatrix apply(final CSCMatrix a, final CSCMatrix b) {
            Semiring ring = (Semiring)scala.Predef..MODULE$.implicitly(this.evidence$30$1);
            int left$macro$1 = a.cols();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(88)).append("requirement failed: CSCMatrix Multiplication Dimension Mismatch: ").append("a.cols == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int numnz = 0;

               for(int i = 0; i < b.cols(); ++i) {
                  for(int j = b.colPtrs()[i]; j < b.colPtrs()[i + 1]; ++j) {
                     numnz += a.colPtrs()[b.rowIndices()[j] + 1] - a.colPtrs()[b.rowIndices()[j]];
                  }
               }

               CSCMatrix.Builder res = new CSCMatrix.Builder(a.rows(), b.cols(), numnz, this.evidence$32$1, this.evidence$30$1, this.evidence$31$1);

               for(int var13 = 0; var13 < b.cols(); ++var13) {
                  for(int j = b.colPtrs()[var13]; j < b.colPtrs()[var13 + 1]; ++j) {
                     Object dval = .MODULE$.array_apply(b.data(), j);

                     for(int k = a.colPtrs()[b.rowIndices()[j]]; k < a.colPtrs()[b.rowIndices()[j] + 1]; ++k) {
                        res.add(a.rowIndices()[k], var13, ring.$times(.MODULE$.array_apply(a.data(), k), dval));
                     }
                  }
               }

               return res.result(res.result$default$1(), res.result$default$2());
            }
         }

         public {
            this.evidence$30$1 = evidence$30$1;
            this.evidence$32$1 = evidence$32$1;
            this.evidence$31$1 = evidence$31$1;
         }
      };
   }

   // $FF: synthetic method
   static CanZipMapValues zipMapVals$(final CSCMatrixOps_Ring $this, final ClassTag evidence$33, final Semiring evidence$34, final Zero evidence$35) {
      return $this.zipMapVals(evidence$33, evidence$34, evidence$35);
   }

   default CanZipMapValues zipMapVals(final ClassTag evidence$33, final Semiring evidence$34, final Zero evidence$35) {
      return new CanZipMapValues(evidence$33, evidence$35, evidence$34) {
         // $FF: synthetic field
         private final CSCMatrixOps_Ring $outer;
         private final ClassTag evidence$33$1;
         private final Zero evidence$35$1;
         private final Semiring evidence$34$1;

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

         public CSCMatrix map(final CSCMatrix a, final CSCMatrix b, final Function2 fn) {
            this.$outer.logger().warn(() -> "Using CSCMatrix.zipMapVals. Note that this implementation currently ZipMaps over active values only, ignoring zeros.");
            int rows = a.rows();
            int cols = a.cols();
            int right$macro$2 = b.rows();
            if (rows != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of rows!: ").append("rows == b.rows (").append(rows).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int right$macro$4 = b.cols();
               if (cols != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of cols!: ").append("cols == b.cols (").append(cols).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  CSCMatrix var10000;
                  if (a.activeSize() == 0) {
                     Object newData = scala.Array..MODULE$.ofDim(.MODULE$.array_length(b.data()), this.evidence$33$1);

                     for(int i = 0; i < .MODULE$.array_length(b.data()); ++i) {
                        .MODULE$.array_update(newData, i, fn.apply(a.zero(), .MODULE$.array_apply(b.data(), i)));
                     }

                     var10000 = new CSCMatrix(newData, rows, cols, Arrays.copyOf(b.colPtrs(), b.colPtrs().length), b.activeSize(), Arrays.copyOf(b.rowIndices(), b.rowIndices().length), this.evidence$35$1);
                  } else if (b.activeSize() == 0) {
                     Object newData = scala.Array..MODULE$.ofDim(.MODULE$.array_length(a.data()), this.evidence$33$1);

                     for(int i = 0; i < .MODULE$.array_length(a.data()); ++i) {
                        .MODULE$.array_update(newData, i, fn.apply(.MODULE$.array_apply(a.data(), i), b.zero()));
                     }

                     var10000 = new CSCMatrix(newData, rows, cols, Arrays.copyOf(a.colPtrs(), a.colPtrs().length), a.activeSize(), Arrays.copyOf(a.rowIndices(), a.rowIndices().length), this.evidence$35$1);
                  } else {
                     CSCMatrix.Builder builder = new CSCMatrix.Builder(a.rows(), a.cols(), a.activeSize(), this.evidence$33$1, this.evidence$34$1, this.evidence$35$1);
                     int ci = 0;
                     int apStop = a.colPtrs()[0];

                     int ci1;
                     for(int bpStop = b.colPtrs()[0]; ci < cols; ci = ci1) {
                        ci1 = ci + 1;
                        int ap = apStop;
                        int bp = bpStop;
                        apStop = a.colPtrs()[ci1];
                        bpStop = b.colPtrs()[ci1];

                        while(ap < apStop || bp < bpStop) {
                           int ar = ap < apStop ? a.rowIndices()[ap] : rows;
                           int br = bp < bpStop ? b.rowIndices()[bp] : rows;
                           if (ar == br) {
                              builder.add(ar, ci, fn.apply(.MODULE$.array_apply(a.data(), ap), .MODULE$.array_apply(b.data(), bp)));
                              ++ap;
                              ++bp;
                           } else if (ar < br) {
                              builder.add(ar, ci, fn.apply(.MODULE$.array_apply(a.data(), ap), b.zero()));
                              ++ap;
                           } else {
                              builder.add(br, ci, fn.apply(a.zero(), .MODULE$.array_apply(b.data(), bp)));
                              ++bp;
                           }
                        }
                     }

                     var10000 = builder.result();
                  }

                  return var10000;
               }
            }
         }

         public {
            if (CSCMatrixOps_Ring.this == null) {
               throw null;
            } else {
               this.$outer = CSCMatrixOps_Ring.this;
               this.evidence$33$1 = evidence$33$1;
               this.evidence$35$1 = evidence$35$1;
               this.evidence$34$1 = evidence$34$1;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static CanZipMapKeyValues zipMapKeyVals$(final CSCMatrixOps_Ring $this, final ClassTag evidence$36, final Semiring evidence$37, final Zero evidence$38) {
      return $this.zipMapKeyVals(evidence$36, evidence$37, evidence$38);
   }

   default CanZipMapKeyValues zipMapKeyVals(final ClassTag evidence$36, final Semiring evidence$37, final Zero evidence$38) {
      return new CanZipMapKeyValues(evidence$36, evidence$37, evidence$38) {
         private final ClassTag evidence$36$1;
         private final Semiring evidence$37$1;
         private final Zero evidence$38$1;

         public CSCMatrix map(final CSCMatrix a, final CSCMatrix b, final Function3 fn) {
            int rows = a.rows();
            int cols = a.cols();
            int right$macro$2 = b.rows();
            if (rows != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of rows!: ").append("rows == b.rows (").append(rows).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int right$macro$4 = b.cols();
               if (cols != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of cols!: ").append("cols == b.cols (").append(cols).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  CSCMatrix.Builder builder = new CSCMatrix.Builder(rows, cols, CSCMatrix.Builder$.MODULE$.$lessinit$greater$default$3(), this.evidence$36$1, this.evidence$37$1, this.evidence$38$1);
                  scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), cols).foreach$mVc$sp((JFunction1.mcVI.sp)(c) -> scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), rows).foreach$mVc$sp((JFunction1.mcVI.sp)(r) -> builder.add(r, c, fn.apply(new Tuple2.mcII.sp(r, c), a.apply(r, c), b.apply(r, c)))));
                  return builder.result(true, true);
               }
            }
         }

         public CSCMatrix mapActive(final CSCMatrix a, final CSCMatrix b, final Function3 fn) {
            int rows = a.rows();
            int cols = a.cols();
            int right$macro$2 = b.rows();
            if (rows != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of rows!: ").append("rows == b.rows (").append(rows).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int right$macro$4 = b.cols();
               if (cols != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of cols!: ").append("cols == b.cols (").append(cols).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  CSCMatrix.Builder builder = new CSCMatrix.Builder(rows, cols, CSCMatrix.Builder$.MODULE$.$lessinit$greater$default$3(), this.evidence$36$1, this.evidence$37$1, this.evidence$38$1);
                  scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), cols).foreach$mVc$sp((JFunction1.mcVI.sp)(c) -> scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), rows).foreach$mVc$sp((JFunction1.mcVI.sp)(r) -> builder.add(r, c, fn.apply(new Tuple2.mcII.sp(r, c), a.apply(r, c), b.apply(r, c)))));
                  return builder.result(true, true);
               }
            }
         }

         public {
            this.evidence$36$1 = evidence$36$1;
            this.evidence$37$1 = evidence$37$1;
            this.evidence$38$1 = evidence$38$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 canAddM_S_Semiring$(final CSCMatrixOps_Ring $this, final Semiring evidence$39, final ClassTag evidence$40) {
      return $this.canAddM_S_Semiring(evidence$39, evidence$40);
   }

   default UFunc.UImpl2 canAddM_S_Semiring(final Semiring evidence$39, final ClassTag evidence$40) {
      return new UFunc.UImpl2(evidence$39, evidence$40) {
         private final Semiring s;
         private final Object zero;
         private final Semiring evidence$39$1;
         private final ClassTag evidence$40$1;

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

         private Object zero() {
            return this.zero;
         }

         public CSCMatrix apply(final CSCMatrix v, final Object v2) {
            if (BoxesRunTime.equals(v2, this.zero())) {
               return (CSCMatrix)package$.MODULE$.copy(v, HasOps$.MODULE$.CSC_canCopy(this.evidence$40$1, Zero$.MODULE$.zeroFromSemiring(this.evidence$39$1)));
            } else {
               Object data = scala.Array..MODULE$.fill(v.rows() * v.cols(), () -> v2, this.evidence$40$1);

               for(int c = 0; c < v.cols(); ++c) {
                  for(int ip = v.colPtrs()[c]; ip < v.colPtrs()[c + 1]; ++ip) {
                     int r = v.rowIndices()[ip];
                     .MODULE$.array_update(data, c * v.rows() + r, this.s().$plus(.MODULE$.array_apply(v.data(), ip), v2));
                  }
               }

               int[] colPtrs = (int[])scala.Array..MODULE$.tabulate(v.cols() + 1, (JFunction1.mcII.sp)(i) -> i * v.rows(), scala.reflect.ClassTag..MODULE$.Int());
               int[] rowIndices = (int[])scala.Array..MODULE$.tabulate(.MODULE$.array_length(data), (JFunction1.mcII.sp)(i) -> i % v.rows(), scala.reflect.ClassTag..MODULE$.Int());
               return new CSCMatrix(data, v.rows(), v.cols(), colPtrs, .MODULE$.array_length(data), rowIndices, Zero$.MODULE$.zeroFromSemiring(this.evidence$39$1));
            }
         }

         public {
            this.evidence$39$1 = evidence$39$1;
            this.evidence$40$1 = evidence$40$1;
            this.s = (Semiring)scala.Predef..MODULE$.implicitly(evidence$39$1);
            this.zero = this.s().zero();
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 canSubM_S_Ring$(final CSCMatrixOps_Ring $this, final Ring evidence$41, final ClassTag evidence$42) {
      return $this.canSubM_S_Ring(evidence$41, evidence$42);
   }

   default UFunc.UImpl2 canSubM_S_Ring(final Ring evidence$41, final ClassTag evidence$42) {
      return new UFunc.UImpl2(evidence$41, evidence$42) {
         private final Ring s;
         private final Object zero;
         private final Ring evidence$41$1;
         private final ClassTag evidence$42$1;

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

         private Ring s() {
            return this.s;
         }

         private Object zero() {
            return this.zero;
         }

         public CSCMatrix apply(final CSCMatrix v, final Object v2) {
            if (BoxesRunTime.equals(v2, this.zero())) {
               return (CSCMatrix)package$.MODULE$.copy(v, HasOps$.MODULE$.CSC_canCopy(this.evidence$42$1, Zero$.MODULE$.zeroFromSemiring(this.evidence$41$1)));
            } else {
               Object data = scala.Array..MODULE$.fill(v.rows() * v.cols(), () -> this.s().negate(v2), this.evidence$42$1);

               for(int c = 0; c < v.cols(); ++c) {
                  for(int ip = v.colPtrs()[c]; ip < v.colPtrs()[c + 1]; ++ip) {
                     int r = v.rowIndices()[ip];
                     .MODULE$.array_update(data, c * v.rows() + r, this.s().$minus(.MODULE$.array_apply(v.data(), ip), v2));
                  }
               }

               int[] colPtrs = (int[])scala.Array..MODULE$.tabulate(v.cols() + 1, (JFunction1.mcII.sp)(i) -> i * v.rows(), scala.reflect.ClassTag..MODULE$.Int());
               int[] rowIndices = (int[])scala.Array..MODULE$.tabulate(.MODULE$.array_length(data), (JFunction1.mcII.sp)(i) -> i % v.rows(), scala.reflect.ClassTag..MODULE$.Int());
               return new CSCMatrix(data, v.rows(), v.cols(), colPtrs, .MODULE$.array_length(data), rowIndices, Zero$.MODULE$.zeroFromSemiring(this.evidence$41$1));
            }
         }

         public {
            this.evidence$41$1 = evidence$41$1;
            this.evidence$42$1 = evidence$42$1;
            this.s = (Ring)scala.Predef..MODULE$.implicitly(evidence$41$1);
            this.zero = this.s().zero();
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 canSetM_S_Semiring$(final CSCMatrixOps_Ring $this, final Semiring evidence$43, final ClassTag evidence$44) {
      return $this.canSetM_S_Semiring(evidence$43, evidence$44);
   }

   default UFunc.UImpl2 canSetM_S_Semiring(final Semiring evidence$43, final ClassTag evidence$44) {
      return new UFunc.UImpl2(evidence$43, evidence$44) {
         private final Semiring r;
         private final Object zero;
         private final Semiring evidence$43$1;
         private final ClassTag evidence$44$1;

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

         private Object zero() {
            return this.zero;
         }

         public CSCMatrix apply(final CSCMatrix v, final Object v2) {
            if (BoxesRunTime.equals(v2, this.zero())) {
               return CSCMatrix$.MODULE$.zeros(v.rows(), v.cols(), this.evidence$44$1, Zero$.MODULE$.zeroFromSemiring(this.evidence$43$1));
            } else {
               Object data = scala.Array..MODULE$.fill(v.rows() * v.cols(), () -> v2, this.evidence$44$1);
               int[] colPtrs = (int[])scala.Array..MODULE$.tabulate(v.cols() + 1, (JFunction1.mcII.sp)(i) -> i * v.rows(), scala.reflect.ClassTag..MODULE$.Int());
               int[] rowIndices = (int[])scala.Array..MODULE$.tabulate(.MODULE$.array_length(data), (JFunction1.mcII.sp)(i) -> i % v.rows(), scala.reflect.ClassTag..MODULE$.Int());
               return new CSCMatrix(data, v.rows(), v.cols(), colPtrs, v.rows() * v.cols(), rowIndices, Zero$.MODULE$.zeroFromSemiring(this.evidence$43$1));
            }
         }

         public {
            this.evidence$43$1 = evidence$43$1;
            this.evidence$44$1 = evidence$44$1;
            this.r = (Semiring)scala.Predef..MODULE$.implicitly(evidence$43$1);
            this.zero = this.r().zero();
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 canMulM_S_Ring_OpMulMatrix$(final CSCMatrixOps_Ring $this, final Ring evidence$45, final ClassTag evidence$46) {
      return $this.canMulM_S_Ring_OpMulMatrix(evidence$45, evidence$46);
   }

   default UFunc.UImpl2 canMulM_S_Ring_OpMulMatrix(final Ring evidence$45, final ClassTag evidence$46) {
      Ring r = (Ring)scala.Predef..MODULE$.implicitly(evidence$45);
      return new UFunc.UImpl2(r, evidence$46, evidence$45) {
         private final Ring r$1;
         private final ClassTag evidence$46$1;
         private final Ring evidence$45$1;

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

         public CSCMatrix apply(final CSCMatrix v, final Object v2) {
            if (BoxesRunTime.equals(v2, this.r$1.zero())) {
               return CSCMatrix$.MODULE$.zeros(v.rows(), v.cols(), this.evidence$46$1, Zero$.MODULE$.zeroFromSemiring(this.evidence$45$1));
            } else {
               Object data = scala.Array..MODULE$.tabulate(.MODULE$.array_length(v.data()), (i) -> $anonfun$apply$30(this, v, v2, BoxesRunTime.unboxToInt(i)), this.evidence$46$1);
               return new CSCMatrix(data, v.rows(), v.cols(), Arrays.copyOf(v.colPtrs(), v.colPtrs().length), v.activeSize(), Arrays.copyOf(v.rowIndices(), v.rowIndices().length), Zero$.MODULE$.zeroFromSemiring(this.evidence$45$1));
            }
         }

         // $FF: synthetic method
         public static final Object $anonfun$apply$30(final Object $this, final CSCMatrix v$4, final Object v2$4, final int i) {
            return $this.r$1.$times(.MODULE$.array_apply(v$4.data(), i), v2$4);
         }

         public {
            this.r$1 = r$1;
            this.evidence$46$1 = evidence$46$1;
            this.evidence$45$1 = evidence$45$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 canMulM_S_Ring_OpMulScalar$(final CSCMatrixOps_Ring $this, final Ring evidence$47, final ClassTag evidence$48) {
      return $this.canMulM_S_Ring_OpMulScalar(evidence$47, evidence$48);
   }

   default UFunc.UImpl2 canMulM_S_Ring_OpMulScalar(final Ring evidence$47, final ClassTag evidence$48) {
      Ring r = (Ring)scala.Predef..MODULE$.implicitly(evidence$47);
      return new UFunc.UImpl2(r, evidence$48, evidence$47) {
         private final Ring r$2;
         private final ClassTag evidence$48$1;
         private final Ring evidence$47$1;

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

         public CSCMatrix apply(final CSCMatrix v, final Object v2) {
            if (BoxesRunTime.equals(v2, this.r$2.zero())) {
               return CSCMatrix$.MODULE$.zeros(v.rows(), v.cols(), this.evidence$48$1, Zero$.MODULE$.zeroFromSemiring(this.evidence$47$1));
            } else {
               Object data = scala.Array..MODULE$.tabulate(.MODULE$.array_length(v.data()), (i) -> $anonfun$apply$31(this, v, v2, BoxesRunTime.unboxToInt(i)), this.evidence$48$1);
               return new CSCMatrix(data, v.rows(), v.cols(), Arrays.copyOf(v.colPtrs(), v.colPtrs().length), v.activeSize(), Arrays.copyOf(v.rowIndices(), v.rowIndices().length), Zero$.MODULE$.zeroFromSemiring(this.evidence$47$1));
            }
         }

         // $FF: synthetic method
         public static final Object $anonfun$apply$31(final Object $this, final CSCMatrix v$5, final Object v2$5, final int i) {
            return $this.r$2.$times(.MODULE$.array_apply(v$5.data(), i), v2$5);
         }

         public {
            this.r$2 = r$2;
            this.evidence$48$1 = evidence$48$1;
            this.evidence$47$1 = evidence$47$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 CSCMatrixCanMulScalarM_M_Semiring$(final CSCMatrixOps_Ring $this, final Semiring evidence$49, final ClassTag evidence$50, final Zero evidence$51) {
      return $this.CSCMatrixCanMulScalarM_M_Semiring(evidence$49, evidence$50, evidence$51);
   }

   default UFunc.UImpl2 CSCMatrixCanMulScalarM_M_Semiring(final Semiring evidence$49, final ClassTag evidence$50, final Zero evidence$51) {
      return new UFunc.UImpl2(evidence$49, evidence$50, evidence$51) {
         private final Semiring ring;
         private final Semiring evidence$49$1;
         private final ClassTag evidence$50$1;
         private final Zero evidence$51$1;

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

         private Semiring ring() {
            return this.ring;
         }

         public final CSCMatrix apply(final CSCMatrix a, final CSCMatrix b) {
            int rows = a.rows();
            int cols = a.cols();
            int right$macro$2 = b.rows();
            if (rows != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of rows!: ").append("rows == b.rows (").append(rows).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int right$macro$4 = b.cols();
               if (cols != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of cols!: ").append("cols == b.cols (").append(cols).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else if (cols != 0 && rows != 0) {
                  CSCMatrix var10000;
                  if (a.activeSize() != 0 && b.activeSize() != 0) {
                     CSCMatrix.Builder res = new CSCMatrix.Builder(rows, cols, scala.math.package..MODULE$.min(a.activeSize(), b.activeSize()), this.evidence$50$1, this.evidence$49$1, this.evidence$51$1);
                     int ci = 0;
                     int apStop = a.colPtrs()[0];

                     int ci1;
                     for(int bpStop = b.colPtrs()[0]; ci < cols; ci = ci1) {
                        ci1 = ci + 1;
                        int ap = apStop;
                        int bp = bpStop;
                        apStop = a.colPtrs()[ci1];
                        bpStop = b.colPtrs()[ci1];

                        while(ap < apStop || bp < bpStop) {
                           int ari = ap < apStop ? a.rowIndices()[ap] : rows;
                           int bri = bp < bpStop ? b.rowIndices()[bp] : rows;
                           if (ari == bri) {
                              res.add(ari, ci, this.ring().$times(.MODULE$.array_apply(a.data(), ap), .MODULE$.array_apply(b.data(), bp)));
                              ++ap;
                              ++bp;
                           } else if (ari < bri) {
                              ++ap;
                           } else {
                              ++bp;
                           }
                        }
                     }

                     var10000 = res.result(true, true);
                  } else {
                     var10000 = CSCMatrix$.MODULE$.zeros(rows, cols, this.evidence$50$1, this.evidence$51$1);
                  }

                  return var10000;
               } else {
                  return CSCMatrix$.MODULE$.zeros(rows, cols, this.evidence$50$1, this.evidence$51$1);
               }
            }
         }

         public {
            this.evidence$49$1 = evidence$49$1;
            this.evidence$50$1 = evidence$50$1;
            this.evidence$51$1 = evidence$51$1;
            this.ring = (Semiring)scala.Predef..MODULE$.implicitly(evidence$49$1);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 CSCMatrixCanAdd_M_M_Semiring$(final CSCMatrixOps_Ring $this, final Semiring evidence$52, final Zero evidence$53, final ClassTag evidence$54) {
      return $this.CSCMatrixCanAdd_M_M_Semiring(evidence$52, evidence$53, evidence$54);
   }

   default UFunc.UImpl2 CSCMatrixCanAdd_M_M_Semiring(final Semiring evidence$52, final Zero evidence$53, final ClassTag evidence$54) {
      return new UFunc.UImpl2(evidence$52, evidence$54, evidence$53) {
         private final Semiring ring;
         private final Semiring evidence$52$1;
         private final ClassTag evidence$54$1;
         private final Zero evidence$53$1;

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

         private Semiring ring() {
            return this.ring;
         }

         public CSCMatrix apply(final CSCMatrix a, final CSCMatrix b) {
            int left$macro$1 = a.rows();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.rows == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int left$macro$3 = a.cols();
               int right$macro$4 = b.cols();
               if (left$macro$3 != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.cols == b.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  int rows = a.rows();
                  int cols = a.cols();
                  if (cols != 0 && rows != 0) {
                     CSCMatrix var10000;
                     if (a.activeSize() == 0) {
                        var10000 = b.copy();
                     } else if (b.activeSize() == 0) {
                        var10000 = a.copy();
                     } else {
                        CSCMatrix.Builder bldr = new CSCMatrix.Builder(rows, cols, scala.math.package..MODULE$.max(a.activeSize(), b.activeSize()), this.evidence$54$1, this.evidence$52$1, this.evidence$53$1);
                        int ci = 0;
                        int apStop = a.colPtrs()[0];

                        int ci1;
                        for(int bpStop = b.colPtrs()[0]; ci < cols; ci = ci1) {
                           ci1 = ci + 1;
                           int ap = apStop;
                           int bp = bpStop;
                           apStop = a.colPtrs()[ci1];
                           bpStop = b.colPtrs()[ci1];

                           while(ap < apStop || bp < bpStop) {
                              int ari = ap < apStop ? a.rowIndices()[ap] : rows;
                              int bri = bp < bpStop ? b.rowIndices()[bp] : rows;
                              if (ari == bri) {
                                 bldr.add(ari, ci, this.ring().$plus(.MODULE$.array_apply(a.data(), ap), .MODULE$.array_apply(b.data(), bp)));
                                 ++ap;
                                 ++bp;
                              } else if (ari < bri) {
                                 bldr.add(ari, ci, .MODULE$.array_apply(a.data(), ap));
                                 ++ap;
                              } else {
                                 bldr.add(bri, ci, .MODULE$.array_apply(b.data(), bp));
                                 ++bp;
                              }
                           }
                        }

                        var10000 = bldr.result(true, true);
                     }

                     return var10000;
                  } else {
                     return CSCMatrix$.MODULE$.zeros(rows, cols, this.evidence$54$1, this.evidence$53$1);
                  }
               }
            }
         }

         public {
            this.evidence$52$1 = evidence$52$1;
            this.evidence$54$1 = evidence$54$1;
            this.evidence$53$1 = evidence$53$1;
            this.ring = (Semiring)scala.Predef..MODULE$.implicitly(evidence$52$1);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 CSCMatrixCanSubM_M_Ring$(final CSCMatrixOps_Ring $this, final Ring evidence$55, final Zero evidence$56, final ClassTag evidence$57) {
      return $this.CSCMatrixCanSubM_M_Ring(evidence$55, evidence$56, evidence$57);
   }

   default UFunc.UImpl2 CSCMatrixCanSubM_M_Ring(final Ring evidence$55, final Zero evidence$56, final ClassTag evidence$57) {
      return new UFunc.UImpl2(evidence$55, evidence$57, evidence$56) {
         private final Ring ring;
         // $FF: synthetic field
         private final CSCMatrixOps_Ring $outer;
         private final Ring evidence$55$1;
         private final ClassTag evidence$57$1;
         private final Zero evidence$56$1;

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

         private Ring ring() {
            return this.ring;
         }

         public CSCMatrix apply(final CSCMatrix a, final CSCMatrix b) {
            int left$macro$1 = a.rows();
            int right$macro$2 = b.rows();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.rows == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int left$macro$3 = a.cols();
               int right$macro$4 = b.cols();
               if (left$macro$3 != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: Matrix dimensions must match: ").append("a.cols == b.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  int rows = a.rows();
                  int cols = a.cols();
                  if (cols != 0 && rows != 0) {
                     CSCMatrix var10000;
                     if (a.activeSize() == 0) {
                        var10000 = (CSCMatrix)b.unary_$minus(this.$outer.csc_OpNeg(this.evidence$55$1, this.evidence$57$1));
                     } else if (b.activeSize() == 0) {
                        var10000 = a.copy();
                     } else {
                        CSCMatrix.Builder bldr = new CSCMatrix.Builder(rows, cols, scala.math.package..MODULE$.max(a.activeSize(), b.activeSize()), this.evidence$57$1, this.evidence$55$1, this.evidence$56$1);
                        int ci = 0;
                        int apStop = a.colPtrs()[0];

                        int ci1;
                        for(int bpStop = b.colPtrs()[0]; ci < cols; ci = ci1) {
                           ci1 = ci + 1;
                           int ap = apStop;
                           int bp = bpStop;
                           apStop = a.colPtrs()[ci1];
                           bpStop = b.colPtrs()[ci1];

                           while(ap < apStop || bp < bpStop) {
                              int ari = ap < apStop ? a.rowIndices()[ap] : rows;
                              int bri = bp < bpStop ? b.rowIndices()[bp] : rows;
                              if (ari == bri) {
                                 Object v = this.ring().$minus(.MODULE$.array_apply(a.data(), ap), .MODULE$.array_apply(b.data(), bp));
                                 bldr.add(ari, ci, v);
                                 ++ap;
                                 ++bp;
                              } else if (ari < bri) {
                                 bldr.add(ari, ci, .MODULE$.array_apply(a.data(), ap));
                                 ++ap;
                              } else {
                                 bldr.add(bri, ci, this.ring().negate(.MODULE$.array_apply(b.data(), bp)));
                                 ++bp;
                              }
                           }
                        }

                        var10000 = bldr.result(true, true);
                     }

                     return var10000;
                  } else {
                     return CSCMatrix$.MODULE$.zeros(rows, cols, this.evidence$57$1, this.evidence$56$1);
                  }
               }
            }
         }

         public {
            if (CSCMatrixOps_Ring.this == null) {
               throw null;
            } else {
               this.$outer = CSCMatrixOps_Ring.this;
               this.evidence$55$1 = evidence$55$1;
               this.evidence$57$1 = evidence$57$1;
               this.evidence$56$1 = evidence$56$1;
               this.ring = (Ring)scala.Predef..MODULE$.implicitly(evidence$55$1);
            }
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 csc_T_Op_OpDiv$(final CSCMatrixOps_Ring $this, final Field evidence$58, final ClassTag evidence$59) {
      return $this.csc_T_Op_OpDiv(evidence$58, evidence$59);
   }

   default UFunc.UImpl2 csc_T_Op_OpDiv(final Field evidence$58, final ClassTag evidence$59) {
      Field f = (Field)scala.Predef..MODULE$.implicitly(evidence$58);
      return new UFunc.UImpl2(f, evidence$59, evidence$58) {
         private final Field f$1;
         private final ClassTag evidence$59$1;
         private final Field evidence$58$1;

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

         public CSCMatrix apply(final CSCMatrix a, final Object b) {
            CSCMatrix var10000;
            if (BoxesRunTime.equals(b, this.f$1.zero())) {
               Object var3 = this.f$1.$div(this.f$1.zero(), b);
               Object data = scala.Array..MODULE$.fill(a.rows() * a.cols(), () -> var3, this.evidence$59$1);
               int[] colPtrs = (int[])scala.Array..MODULE$.tabulate(a.cols() + 1, (JFunction1.mcII.sp)(i) -> i * a.rows(), scala.reflect.ClassTag..MODULE$.Int());
               int[] rowIndices = (int[])scala.Array..MODULE$.tabulate(.MODULE$.array_length(data), (JFunction1.mcII.sp)(i) -> i % a.rows(), scala.reflect.ClassTag..MODULE$.Int());

               for(int c = 0; c < a.cols(); ++c) {
                  for(int ip = a.colPtrs()[c]; ip < a.colPtrs()[c + 1]; ++ip) {
                     int r = a.rowIndices()[ip];
                     .MODULE$.array_update(data, c * a.rows() + r, this.f$1.$div(.MODULE$.array_apply(a.data(), ip), b));
                  }
               }

               var10000 = new CSCMatrix(data, a.rows(), a.cols(), colPtrs, a.rows() * a.cols(), rowIndices, Zero$.MODULE$.zeroFromSemiring(this.evidence$58$1));
            } else {
               CSCMatrix.Builder bldr = new CSCMatrix.Builder(a.rows(), a.cols(), a.activeSize(), this.evidence$59$1, this.evidence$58$1, Zero$.MODULE$.zeroFromSemiring(this.evidence$58$1));

               for(int c = 0; c < a.cols(); ++c) {
                  for(int ip = a.colPtrs()[c]; ip < a.colPtrs()[c + 1]; ++ip) {
                     int r = a.rowIndices()[ip];
                     bldr.add(r, c, this.f$1.$div(.MODULE$.array_apply(a.data(), ip), b));
                  }
               }

               var10000 = bldr.result(true, true);
            }

            return var10000;
         }

         public {
            this.f$1 = f$1;
            this.evidence$59$1 = evidence$59$1;
            this.evidence$58$1 = evidence$58$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 csc_T_Op_OpMod$(final CSCMatrixOps_Ring $this, final Field evidence$60, final ClassTag evidence$61) {
      return $this.csc_T_Op_OpMod(evidence$60, evidence$61);
   }

   default UFunc.UImpl2 csc_T_Op_OpMod(final Field evidence$60, final ClassTag evidence$61) {
      Field f = (Field)scala.Predef..MODULE$.implicitly(evidence$60);
      return new UFunc.UImpl2(f, evidence$61, evidence$60) {
         private final Field f$2;
         private final ClassTag evidence$61$1;
         private final Field evidence$60$1;

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

         public CSCMatrix apply(final CSCMatrix a, final Object b) {
            CSCMatrix var10000;
            if (BoxesRunTime.equals(b, this.f$2.zero())) {
               Object var3 = this.f$2.$percent(this.f$2.zero(), b);
               Object data = scala.Array..MODULE$.fill(a.rows() * a.cols(), () -> var3, this.evidence$61$1);
               int[] colPtrs = (int[])scala.Array..MODULE$.tabulate(a.cols() + 1, (JFunction1.mcII.sp)(i) -> i * a.rows(), scala.reflect.ClassTag..MODULE$.Int());
               int[] rowIndices = (int[])scala.Array..MODULE$.tabulate(.MODULE$.array_length(data), (JFunction1.mcII.sp)(i) -> i % a.rows(), scala.reflect.ClassTag..MODULE$.Int());

               for(int c = 0; c < a.cols(); ++c) {
                  for(int ip = a.colPtrs()[c]; ip < a.colPtrs()[c + 1]; ++ip) {
                     int r = a.rowIndices()[ip];
                     .MODULE$.array_update(data, c * a.rows() + r, this.f$2.$percent(.MODULE$.array_apply(a.data(), ip), b));
                  }
               }

               var10000 = new CSCMatrix(data, a.rows(), a.cols(), colPtrs, a.rows() * a.cols(), rowIndices, Zero$.MODULE$.zeroFromSemiring(this.evidence$60$1));
            } else {
               CSCMatrix.Builder bldr = new CSCMatrix.Builder(a.rows(), a.cols(), a.activeSize(), this.evidence$61$1, this.evidence$60$1, Zero$.MODULE$.zeroFromSemiring(this.evidence$60$1));

               for(int c = 0; c < a.cols(); ++c) {
                  for(int ip = a.colPtrs()[c]; ip < a.colPtrs()[c + 1]; ++ip) {
                     int r = a.rowIndices()[ip];
                     bldr.add(r, c, this.f$2.$percent(.MODULE$.array_apply(a.data(), ip), b));
                  }
               }

               var10000 = bldr.result(true, true);
            }

            return var10000;
         }

         public {
            this.f$2 = f$2;
            this.evidence$61$1 = evidence$61$1;
            this.evidence$60$1 = evidence$60$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 csc_T_Op_OpPow$(final CSCMatrixOps_Ring $this, final Field evidence$62, final ClassTag evidence$63) {
      return $this.csc_T_Op_OpPow(evidence$62, evidence$63);
   }

   default UFunc.UImpl2 csc_T_Op_OpPow(final Field evidence$62, final ClassTag evidence$63) {
      Field f = (Field)scala.Predef..MODULE$.implicitly(evidence$62);
      return new UFunc.UImpl2(f, evidence$63, evidence$62) {
         private final Field f$3;
         private final ClassTag evidence$63$1;
         private final Field evidence$62$1;

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

         public CSCMatrix apply(final CSCMatrix a, final Object b) {
            CSCMatrix var10000;
            if (BoxesRunTime.equals(b, this.f$3.zero())) {
               Object var3 = this.f$3.pow(this.f$3.zero(), b);
               Object data = scala.Array..MODULE$.fill(a.rows() * a.cols(), () -> var3, this.evidence$63$1);
               int[] colPtrs = (int[])scala.Array..MODULE$.tabulate(a.cols() + 1, (JFunction1.mcII.sp)(i) -> i * a.rows(), scala.reflect.ClassTag..MODULE$.Int());
               int[] rowIndices = (int[])scala.Array..MODULE$.tabulate(.MODULE$.array_length(data), (JFunction1.mcII.sp)(i) -> i % a.rows(), scala.reflect.ClassTag..MODULE$.Int());

               for(int c = 0; c < a.cols(); ++c) {
                  for(int ip = a.colPtrs()[c]; ip < a.colPtrs()[c + 1]; ++ip) {
                     int r = a.rowIndices()[ip];
                     .MODULE$.array_update(data, c * a.rows() + r, this.f$3.pow(.MODULE$.array_apply(a.data(), ip), b));
                  }
               }

               var10000 = new CSCMatrix(data, a.rows(), a.cols(), colPtrs, a.rows() * a.cols(), rowIndices, Zero$.MODULE$.zeroFromSemiring(this.evidence$62$1));
            } else {
               CSCMatrix.Builder bldr = new CSCMatrix.Builder(a.rows(), a.cols(), a.activeSize(), this.evidence$63$1, this.evidence$62$1, Zero$.MODULE$.zeroFromSemiring(this.evidence$62$1));

               for(int c = 0; c < a.cols(); ++c) {
                  for(int ip = a.colPtrs()[c]; ip < a.colPtrs()[c + 1]; ++ip) {
                     int r = a.rowIndices()[ip];
                     bldr.add(r, c, this.f$3.pow(.MODULE$.array_apply(a.data(), ip), b));
                  }
               }

               var10000 = bldr.result(true, true);
            }

            return var10000;
         }

         public {
            this.f$3 = f$3;
            this.evidence$63$1 = evidence$63$1;
            this.evidence$62$1 = evidence$62$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 csc_csc_BadOp_OpDiv$(final CSCMatrixOps_Ring $this, final Field evidence$64, final ClassTag evidence$65) {
      return $this.csc_csc_BadOp_OpDiv(evidence$64, evidence$65);
   }

   default UFunc.UImpl2 csc_csc_BadOp_OpDiv(final Field evidence$64, final ClassTag evidence$65) {
      Field f = (Field)scala.Predef..MODULE$.implicitly(evidence$64);
      return new UFunc.UImpl2(f, evidence$65, evidence$64) {
         // $FF: synthetic field
         private final CSCMatrixOps_Ring $outer;
         private final Field f$4;
         private final ClassTag evidence$65$1;
         private final Field evidence$64$1;

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

         public CSCMatrix apply(final CSCMatrix a, final CSCMatrix b) {
            int rows = a.rows();
            int cols = a.cols();
            int right$macro$2 = b.rows();
            if (rows != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of rows!: ").append("rows == b.rows (").append(rows).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int right$macro$4 = b.cols();
               if (cols != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of cols!: ").append("cols == b.cols (").append(cols).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  Object nData = scala.Array..MODULE$.fill(rows * cols, () -> this.f$4.zero(), this.evidence$65$1);
                  int ci = 0;

                  int ci1;
                  for(int apStop = a.colPtrs()[0]; ci < cols; ci = ci1) {
                     ci1 = ci + 1;
                     int ap = apStop;

                     for(apStop = a.colPtrs()[ci1]; ap < apStop; ++ap) {
                        int ar = a.rowIndices()[ap];
                        .MODULE$.array_update(nData, ci * rows + ar, .MODULE$.array_apply(a.data(), ap));
                     }
                  }

                  ci = 0;

                  int ci1;
                  for(int bpStop = b.colPtrs()[0]; ci < cols; ci = ci1) {
                     ci1 = ci + 1;
                     int bp = bpStop;
                     bpStop = b.colPtrs()[ci1];
                     if (bp == bpStop) {
                        CSCMatrixOps_Ring.breeze$linalg$operators$CSCMatrixOps_Ring$$computeZeroOpOnRange$13(nData, ci * cols, ci1 * cols, this.f$4);
                     } else {
                        for(int rL = 0; bp < bpStop; ++bp) {
                           int br = b.rowIndices()[bp];
                           int ndi = ci * rows + br;
                           if (rL < br - 1) {
                              CSCMatrixOps_Ring.breeze$linalg$operators$CSCMatrixOps_Ring$$computeZeroOpOnRange$13(nData, ci * rows + rL, ndi, this.f$4);
                           }

                           .MODULE$.array_update(nData, ndi, this.f$4.$div(.MODULE$.array_apply(nData, ndi), .MODULE$.array_apply(b.data(), bp)));
                           rL = br;
                        }
                     }
                  }

                  int[] colPtrs = (int[])scala.Array..MODULE$.tabulate(cols + 1, (JFunction1.mcII.sp)(i) -> i * rows, scala.reflect.ClassTag..MODULE$.Int());
                  int[] rowIndices = (int[])scala.Array..MODULE$.tabulate(.MODULE$.array_length(nData), (JFunction1.mcII.sp)(i) -> i % rows, scala.reflect.ClassTag..MODULE$.Int());
                  return new CSCMatrix(nData, rows, cols, colPtrs, .MODULE$.array_length(nData), rowIndices, Zero$.MODULE$.zeroFromSemiring(this.evidence$64$1));
               }
            }
         }

         public {
            if (CSCMatrixOps_Ring.this == null) {
               throw null;
            } else {
               this.$outer = CSCMatrixOps_Ring.this;
               this.f$4 = f$4;
               this.evidence$65$1 = evidence$65$1;
               this.evidence$64$1 = evidence$64$1;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 csc_csc_BadOp_OpMod$(final CSCMatrixOps_Ring $this, final Field evidence$66, final ClassTag evidence$67) {
      return $this.csc_csc_BadOp_OpMod(evidence$66, evidence$67);
   }

   default UFunc.UImpl2 csc_csc_BadOp_OpMod(final Field evidence$66, final ClassTag evidence$67) {
      Field f = (Field)scala.Predef..MODULE$.implicitly(evidence$66);
      return new UFunc.UImpl2(f, evidence$67, evidence$66) {
         // $FF: synthetic field
         private final CSCMatrixOps_Ring $outer;
         private final Field f$5;
         private final ClassTag evidence$67$1;
         private final Field evidence$66$1;

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

         public CSCMatrix apply(final CSCMatrix a, final CSCMatrix b) {
            int rows = a.rows();
            int cols = a.cols();
            int right$macro$2 = b.rows();
            if (rows != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of rows!: ").append("rows == b.rows (").append(rows).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int right$macro$4 = b.cols();
               if (cols != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of cols!: ").append("cols == b.cols (").append(cols).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  Object nData = scala.Array..MODULE$.fill(rows * cols, () -> this.f$5.zero(), this.evidence$67$1);
                  int ci = 0;

                  int ci1;
                  for(int apStop = a.colPtrs()[0]; ci < cols; ci = ci1) {
                     ci1 = ci + 1;
                     int ap = apStop;

                     for(apStop = a.colPtrs()[ci1]; ap < apStop; ++ap) {
                        int ar = a.rowIndices()[ap];
                        .MODULE$.array_update(nData, ci * rows + ar, .MODULE$.array_apply(a.data(), ap));
                     }
                  }

                  ci = 0;

                  int ci1;
                  for(int bpStop = b.colPtrs()[0]; ci < cols; ci = ci1) {
                     ci1 = ci + 1;
                     int bp = bpStop;
                     bpStop = b.colPtrs()[ci1];
                     if (bp == bpStop) {
                        CSCMatrixOps_Ring.breeze$linalg$operators$CSCMatrixOps_Ring$$computeZeroOpOnRange$14(nData, ci * cols, ci1 * cols, this.f$5);
                     } else {
                        for(int rL = 0; bp < bpStop; ++bp) {
                           int br = b.rowIndices()[bp];
                           int ndi = ci * rows + br;
                           if (rL < br - 1) {
                              CSCMatrixOps_Ring.breeze$linalg$operators$CSCMatrixOps_Ring$$computeZeroOpOnRange$14(nData, ci * rows + rL, ndi, this.f$5);
                           }

                           .MODULE$.array_update(nData, ndi, this.f$5.$percent(.MODULE$.array_apply(nData, ndi), .MODULE$.array_apply(b.data(), bp)));
                           rL = br;
                        }
                     }
                  }

                  int[] colPtrs = (int[])scala.Array..MODULE$.tabulate(cols + 1, (JFunction1.mcII.sp)(i) -> i * rows, scala.reflect.ClassTag..MODULE$.Int());
                  int[] rowIndices = (int[])scala.Array..MODULE$.tabulate(.MODULE$.array_length(nData), (JFunction1.mcII.sp)(i) -> i % rows, scala.reflect.ClassTag..MODULE$.Int());
                  return new CSCMatrix(nData, rows, cols, colPtrs, .MODULE$.array_length(nData), rowIndices, Zero$.MODULE$.zeroFromSemiring(this.evidence$66$1));
               }
            }
         }

         public {
            if (CSCMatrixOps_Ring.this == null) {
               throw null;
            } else {
               this.$outer = CSCMatrixOps_Ring.this;
               this.f$5 = f$5;
               this.evidence$67$1 = evidence$67$1;
               this.evidence$66$1 = evidence$66$1;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 csc_csc_BadOp_OpPow$(final CSCMatrixOps_Ring $this, final Field evidence$68, final ClassTag evidence$69) {
      return $this.csc_csc_BadOp_OpPow(evidence$68, evidence$69);
   }

   default UFunc.UImpl2 csc_csc_BadOp_OpPow(final Field evidence$68, final ClassTag evidence$69) {
      Field f = (Field)scala.Predef..MODULE$.implicitly(evidence$68);
      return new UFunc.UImpl2(f, evidence$69, evidence$68) {
         // $FF: synthetic field
         private final CSCMatrixOps_Ring $outer;
         private final Field f$6;
         private final ClassTag evidence$69$1;
         private final Field evidence$68$1;

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

         public CSCMatrix apply(final CSCMatrix a, final CSCMatrix b) {
            int rows = a.rows();
            int cols = a.cols();
            int right$macro$2 = b.rows();
            if (rows != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of rows!: ").append("rows == b.rows (").append(rows).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int right$macro$4 = b.cols();
               if (cols != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of cols!: ").append("cols == b.cols (").append(cols).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  Object nData = scala.Array..MODULE$.fill(rows * cols, () -> this.f$6.zero(), this.evidence$69$1);
                  int ci = 0;

                  int ci1;
                  for(int apStop = a.colPtrs()[0]; ci < cols; ci = ci1) {
                     ci1 = ci + 1;
                     int ap = apStop;

                     for(apStop = a.colPtrs()[ci1]; ap < apStop; ++ap) {
                        int ar = a.rowIndices()[ap];
                        .MODULE$.array_update(nData, ci * rows + ar, .MODULE$.array_apply(a.data(), ap));
                     }
                  }

                  ci = 0;

                  int ci1;
                  for(int bpStop = b.colPtrs()[0]; ci < cols; ci = ci1) {
                     ci1 = ci + 1;
                     int bp = bpStop;
                     bpStop = b.colPtrs()[ci1];
                     if (bp == bpStop) {
                        CSCMatrixOps_Ring.breeze$linalg$operators$CSCMatrixOps_Ring$$computeZeroOpOnRange$15(nData, ci * cols, ci1 * cols, this.f$6);
                     } else {
                        for(int rL = 0; bp < bpStop; ++bp) {
                           int br = b.rowIndices()[bp];
                           int ndi = ci * rows + br;
                           if (rL < br - 1) {
                              CSCMatrixOps_Ring.breeze$linalg$operators$CSCMatrixOps_Ring$$computeZeroOpOnRange$15(nData, ci * rows + rL, ndi, this.f$6);
                           }

                           .MODULE$.array_update(nData, ndi, this.f$6.pow(.MODULE$.array_apply(nData, ndi), .MODULE$.array_apply(b.data(), bp)));
                           rL = br;
                        }
                     }
                  }

                  int[] colPtrs = (int[])scala.Array..MODULE$.tabulate(cols + 1, (JFunction1.mcII.sp)(i) -> i * rows, scala.reflect.ClassTag..MODULE$.Int());
                  int[] rowIndices = (int[])scala.Array..MODULE$.tabulate(.MODULE$.array_length(nData), (JFunction1.mcII.sp)(i) -> i % rows, scala.reflect.ClassTag..MODULE$.Int());
                  return new CSCMatrix(nData, rows, cols, colPtrs, .MODULE$.array_length(nData), rowIndices, Zero$.MODULE$.zeroFromSemiring(this.evidence$68$1));
               }
            }
         }

         public {
            if (CSCMatrixOps_Ring.this == null) {
               throw null;
            } else {
               this.$outer = CSCMatrixOps_Ring.this;
               this.f$6 = f$6;
               this.evidence$69$1 = evidence$69$1;
               this.evidence$68$1 = evidence$68$1;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 CSCMatrixCanSetM_M_Semiring$(final CSCMatrixOps_Ring $this, final Semiring evidence$70, final ClassTag evidence$71) {
      return $this.CSCMatrixCanSetM_M_Semiring(evidence$70, evidence$71);
   }

   default UFunc.UImpl2 CSCMatrixCanSetM_M_Semiring(final Semiring evidence$70, final ClassTag evidence$71) {
      Semiring f = (Semiring)scala.Predef..MODULE$.implicitly(evidence$70);
      return new UFunc.UImpl2() {
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

         public CSCMatrix apply(final CSCMatrix a, final CSCMatrix b) {
            int rows = a.rows();
            int cols = a.cols();
            int right$macro$2 = b.rows();
            if (rows != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of rows!: ").append("rows == b.rows (").append(rows).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int right$macro$4 = b.cols();
               if (cols != right$macro$4) {
                  throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrices must have same number of cols!: ").append("cols == b.cols (").append(cols).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
               } else {
                  return b.copy();
               }
            }
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 updateFromPure_CSC$(final CSCMatrixOps_Ring $this, final UFunc.UImpl2 op) {
      return $this.updateFromPure_CSC(op);
   }

   default UFunc.InPlaceImpl2 updateFromPure_CSC(final UFunc.UImpl2 op) {
      return (a, b) -> {
         CSCMatrix result = (CSCMatrix)op.apply(a, b);
         a.use(result.data(), result.colPtrs(), result.rowIndices(), result.activeSize());
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_Op_CSC_CSC_eq_CSC_lift_OpAdd$(final CSCMatrixOps_Ring $this, final Field evidence$72, final ClassTag evidence$73) {
      return $this.impl_Op_CSC_CSC_eq_CSC_lift_OpAdd(evidence$72, evidence$73);
   }

   default UFunc.InPlaceImpl2 impl_Op_CSC_CSC_eq_CSC_lift_OpAdd(final Field evidence$72, final ClassTag evidence$73) {
      return this.updateFromPure_CSC((UFunc.UImpl2)scala.Predef..MODULE$.implicitly(this.CSCMatrixCanAdd_M_M_Semiring(evidence$72, Zero$.MODULE$.zeroFromSemiring(evidence$72), evidence$73)));
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_Op_CSC_CSC_eq_CSC_lift_OpSub$(final CSCMatrixOps_Ring $this, final Field evidence$74, final ClassTag evidence$75) {
      return $this.impl_Op_CSC_CSC_eq_CSC_lift_OpSub(evidence$74, evidence$75);
   }

   default UFunc.InPlaceImpl2 impl_Op_CSC_CSC_eq_CSC_lift_OpSub(final Field evidence$74, final ClassTag evidence$75) {
      return this.updateFromPure_CSC((UFunc.UImpl2)scala.Predef..MODULE$.implicitly(this.CSCMatrixCanSubM_M_Ring(evidence$74, Zero$.MODULE$.zeroFromSemiring(evidence$74), evidence$75)));
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_Op_CSC_CSC_eq_CSC_lift_OpMulScalar$(final CSCMatrixOps_Ring $this, final Field evidence$76, final ClassTag evidence$77) {
      return $this.impl_Op_CSC_CSC_eq_CSC_lift_OpMulScalar(evidence$76, evidence$77);
   }

   default UFunc.InPlaceImpl2 impl_Op_CSC_CSC_eq_CSC_lift_OpMulScalar(final Field evidence$76, final ClassTag evidence$77) {
      return this.updateFromPure_CSC((UFunc.UImpl2)scala.Predef..MODULE$.implicitly(this.CSCMatrixCanMulScalarM_M_Semiring(evidence$76, evidence$77, Zero$.MODULE$.zeroFromSemiring(evidence$76))));
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_Op_CSC_CSC_eq_CSC_lift_OpSet$(final CSCMatrixOps_Ring $this, final Field evidence$78, final ClassTag evidence$79) {
      return $this.impl_Op_CSC_CSC_eq_CSC_lift_OpSet(evidence$78, evidence$79);
   }

   default UFunc.InPlaceImpl2 impl_Op_CSC_CSC_eq_CSC_lift_OpSet(final Field evidence$78, final ClassTag evidence$79) {
      return this.updateFromPure_CSC((UFunc.UImpl2)scala.Predef..MODULE$.implicitly(this.CSCMatrixCanSetM_M_Semiring(evidence$78, evidence$79)));
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_Op_CSC_CSC_eq_CSC_lift_OpDiv$(final CSCMatrixOps_Ring $this, final Field evidence$80, final ClassTag evidence$81) {
      return $this.impl_Op_CSC_CSC_eq_CSC_lift_OpDiv(evidence$80, evidence$81);
   }

   default UFunc.InPlaceImpl2 impl_Op_CSC_CSC_eq_CSC_lift_OpDiv(final Field evidence$80, final ClassTag evidence$81) {
      return this.updateFromPure_CSC((UFunc.UImpl2)scala.Predef..MODULE$.implicitly(this.csc_csc_BadOp_OpDiv(evidence$80, evidence$81)));
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_Op_CSC_CSC_eq_CSC_lift_OpPow$(final CSCMatrixOps_Ring $this, final Field evidence$82, final ClassTag evidence$83) {
      return $this.impl_Op_CSC_CSC_eq_CSC_lift_OpPow(evidence$82, evidence$83);
   }

   default UFunc.InPlaceImpl2 impl_Op_CSC_CSC_eq_CSC_lift_OpPow(final Field evidence$82, final ClassTag evidence$83) {
      return this.updateFromPure_CSC((UFunc.UImpl2)scala.Predef..MODULE$.implicitly(this.csc_csc_BadOp_OpPow(evidence$82, evidence$83)));
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_Op_CSC_CSC_eq_CSC_lift_OpMod$(final CSCMatrixOps_Ring $this, final Field evidence$84, final ClassTag evidence$85) {
      return $this.impl_Op_CSC_CSC_eq_CSC_lift_OpMod(evidence$84, evidence$85);
   }

   default UFunc.InPlaceImpl2 impl_Op_CSC_CSC_eq_CSC_lift_OpMod(final Field evidence$84, final ClassTag evidence$85) {
      return this.updateFromPure_CSC((UFunc.UImpl2)scala.Predef..MODULE$.implicitly(this.csc_csc_BadOp_OpMod(evidence$84, evidence$85)));
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_Op_InPlace_CSC_T_lift_OpMulMatrix$(final CSCMatrixOps_Ring $this, final Field evidence$86, final ClassTag evidence$87) {
      return $this.impl_Op_InPlace_CSC_T_lift_OpMulMatrix(evidence$86, evidence$87);
   }

   default UFunc.InPlaceImpl2 impl_Op_InPlace_CSC_T_lift_OpMulMatrix(final Field evidence$86, final ClassTag evidence$87) {
      return this.updateFromPure_CSC((UFunc.UImpl2)scala.Predef..MODULE$.implicitly(this.canMulM_S_Ring_OpMulMatrix(evidence$86, evidence$87)));
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_Op_InPlace_CSC_T_lift_OpSet$(final CSCMatrixOps_Ring $this, final Field evidence$88, final ClassTag evidence$89) {
      return $this.impl_Op_InPlace_CSC_T_lift_OpSet(evidence$88, evidence$89);
   }

   default UFunc.InPlaceImpl2 impl_Op_InPlace_CSC_T_lift_OpSet(final Field evidence$88, final ClassTag evidence$89) {
      return this.updateFromPure_CSC((UFunc.UImpl2)scala.Predef..MODULE$.implicitly(this.canSetM_S_Semiring(evidence$88, evidence$89)));
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_Op_InPlace_CSC_T_lift_OpSub$(final CSCMatrixOps_Ring $this, final Field evidence$90, final ClassTag evidence$91) {
      return $this.impl_Op_InPlace_CSC_T_lift_OpSub(evidence$90, evidence$91);
   }

   default UFunc.InPlaceImpl2 impl_Op_InPlace_CSC_T_lift_OpSub(final Field evidence$90, final ClassTag evidence$91) {
      return this.updateFromPure_CSC((UFunc.UImpl2)scala.Predef..MODULE$.implicitly(this.canSubM_S_Ring(evidence$90, evidence$91)));
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_Op_InPlace_CSC_T_lift_OpAdd$(final CSCMatrixOps_Ring $this, final Field evidence$92, final ClassTag evidence$93) {
      return $this.impl_Op_InPlace_CSC_T_lift_OpAdd(evidence$92, evidence$93);
   }

   default UFunc.InPlaceImpl2 impl_Op_InPlace_CSC_T_lift_OpAdd(final Field evidence$92, final ClassTag evidence$93) {
      return this.updateFromPure_CSC((UFunc.UImpl2)scala.Predef..MODULE$.implicitly(this.canAddM_S_Semiring(evidence$92, evidence$93)));
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_Op_InPlace_CSC_T_lift_OpMulScalar$(final CSCMatrixOps_Ring $this, final Field evidence$94, final ClassTag evidence$95) {
      return $this.impl_Op_InPlace_CSC_T_lift_OpMulScalar(evidence$94, evidence$95);
   }

   default UFunc.InPlaceImpl2 impl_Op_InPlace_CSC_T_lift_OpMulScalar(final Field evidence$94, final ClassTag evidence$95) {
      return this.updateFromPure_CSC((UFunc.UImpl2)scala.Predef..MODULE$.implicitly(this.canMulM_S_Ring_OpMulScalar(evidence$94, evidence$95)));
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_Op_InPlace_CSC_T_lift_OpDiv$(final CSCMatrixOps_Ring $this, final Field evidence$96, final ClassTag evidence$97) {
      return $this.impl_Op_InPlace_CSC_T_lift_OpDiv(evidence$96, evidence$97);
   }

   default UFunc.InPlaceImpl2 impl_Op_InPlace_CSC_T_lift_OpDiv(final Field evidence$96, final ClassTag evidence$97) {
      return this.updateFromPure_CSC((UFunc.UImpl2)scala.Predef..MODULE$.implicitly(this.csc_T_Op_OpDiv(evidence$96, evidence$97)));
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_Op_InPlace_CSC_T_lift_OpMod$(final CSCMatrixOps_Ring $this, final Field evidence$98, final ClassTag evidence$99) {
      return $this.impl_Op_InPlace_CSC_T_lift_OpMod(evidence$98, evidence$99);
   }

   default UFunc.InPlaceImpl2 impl_Op_InPlace_CSC_T_lift_OpMod(final Field evidence$98, final ClassTag evidence$99) {
      return this.updateFromPure_CSC((UFunc.UImpl2)scala.Predef..MODULE$.implicitly(this.csc_T_Op_OpMod(evidence$98, evidence$99)));
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_Op_InPlace_CSC_T_lift_OpPow$(final CSCMatrixOps_Ring $this, final Field evidence$100, final ClassTag evidence$101) {
      return $this.impl_Op_InPlace_CSC_T_lift_OpPow(evidence$100, evidence$101);
   }

   default UFunc.InPlaceImpl2 impl_Op_InPlace_CSC_T_lift_OpPow(final Field evidence$100, final ClassTag evidence$101) {
      return this.updateFromPure_CSC((UFunc.UImpl2)scala.Predef..MODULE$.implicitly(this.csc_T_Op_OpPow(evidence$100, evidence$101)));
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_OpSolveMatrixBy_CSCD_DVD_eq_DVD$(final CSCMatrixOps_Ring $this, final UFunc.UImpl2 multMV, final MutableInnerProductVectorSpace ispace) {
      return $this.impl_OpSolveMatrixBy_CSCD_DVD_eq_DVD(multMV, ispace);
   }

   default UFunc.UImpl2 impl_OpSolveMatrixBy_CSCD_DVD_eq_DVD(final UFunc.UImpl2 multMV, final MutableInnerProductVectorSpace ispace) {
      return new UFunc.UImpl2(multMV, ispace) {
         private final UFunc.UImpl2 multMV$1;
         private final MutableInnerProductVectorSpace ispace$1;

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

         public Object apply(final CSCMatrix a, final Object b) {
            boolean x$3 = true;
            double x$4 = LSMR$.MODULE$.solve$default$3();
            double x$5 = LSMR$.MODULE$.solve$default$4();
            int x$6 = LSMR$.MODULE$.solve$default$5();
            return LSMR$.MODULE$.solve(a, b, x$4, x$5, x$6, true, this.multMV$1, HasOps$.MODULE$.canTranspose_CSC(scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero(), Semiring$.MODULE$.semiringD()), this.multMV$1, this.ispace$1);
         }

         public {
            this.multMV$1 = multMV$1;
            this.ispace$1 = ispace$1;
         }
      };
   }

   UFunc.UImpl2 impl_OpSolveMatrixBy_CSC_CSC_eq_CSC();

   static void breeze$linalg$operators$CSCMatrixOps_Ring$$computeZeroOpOnRange$13(final Object arr, final int start, final int end, final Field f$4) {
      for(int i = start; i < end; ++i) {
         .MODULE$.array_update(arr, i, f$4.$div(.MODULE$.array_apply(arr, i), f$4.zero()));
      }

   }

   static void breeze$linalg$operators$CSCMatrixOps_Ring$$computeZeroOpOnRange$14(final Object arr, final int start, final int end, final Field f$5) {
      for(int i = start; i < end; ++i) {
         .MODULE$.array_update(arr, i, f$5.$percent(.MODULE$.array_apply(arr, i), f$5.zero()));
      }

   }

   static void breeze$linalg$operators$CSCMatrixOps_Ring$$computeZeroOpOnRange$15(final Object arr, final int start, final int end, final Field f$6) {
      for(int i = start; i < end; ++i) {
         .MODULE$.array_update(arr, i, f$6.pow(.MODULE$.array_apply(arr, i), f$6.zero()));
      }

   }

   static void $init$(final CSCMatrixOps_Ring $this) {
      $this.breeze$linalg$operators$CSCMatrixOps_Ring$_setter_$impl_OpSolveMatrixBy_CSC_CSC_eq_CSC_$eq(new UFunc.UImpl2() {
         // $FF: synthetic field
         private final CSCMatrixOps_Ring $outer;

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

         public CSCMatrix apply(final CSCMatrix a, final CSCMatrix b) {
            FrobeniusCSCProduct$ fakeDot = this.$outer.FrobeniusCSCProduct();
            MutableInnerProductVectorSpace mip = MutableInnerProductVectorSpace$.MODULE$.make(Field.fieldDouble$.MODULE$, scala..less.colon.less..MODULE$.refl(), HasOps$.MODULE$.CSC_canCreateZerosLike(scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero()), this.$outer.canMulM_S_Ring_OpMulScalar(Ring$.MODULE$.ringD(), scala.reflect.ClassTag..MODULE$.Double()), this.$outer.csc_T_Op_OpDiv(Field.fieldDouble$.MODULE$, scala.reflect.ClassTag..MODULE$.Double()), this.$outer.CSCMatrixCanAdd_M_M_Semiring(Semiring$.MODULE$.semiringD(), Zero$.MODULE$.DoubleZero(), scala.reflect.ClassTag..MODULE$.Double()), this.$outer.CSCMatrixCanSubM_M_Ring(Ring$.MODULE$.ringD(), Zero$.MODULE$.DoubleZero(), scala.reflect.ClassTag..MODULE$.Double()), fakeDot, HasOps$.MODULE$.CSC_canCopy$mDc$sp(scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero()), this.$outer.impl_Op_InPlace_CSC_T_lift_OpMulScalar(Field.fieldDouble$.MODULE$, scala.reflect.ClassTag..MODULE$.Double()), this.$outer.impl_Op_InPlace_CSC_T_lift_OpDiv(Field.fieldDouble$.MODULE$, scala.reflect.ClassTag..MODULE$.Double()), this.$outer.impl_Op_CSC_CSC_eq_CSC_lift_OpAdd(Field.fieldDouble$.MODULE$, scala.reflect.ClassTag..MODULE$.Double()), this.$outer.impl_Op_CSC_CSC_eq_CSC_lift_OpSub(Field.fieldDouble$.MODULE$, scala.reflect.ClassTag..MODULE$.Double()), this.$outer.impl_Op_CSC_CSC_eq_CSC_lift_OpSet(Field.fieldDouble$.MODULE$, scala.reflect.ClassTag..MODULE$.Double()), this.$outer.cscScaleAdd(Semiring$.MODULE$.semiringD(), scala.reflect.ClassTag..MODULE$.Double()));
            boolean x$3 = true;
            double x$4 = LSMR$.MODULE$.solve$default$3();
            double x$5 = LSMR$.MODULE$.solve$default$4();
            int x$6 = LSMR$.MODULE$.solve$default$5();
            return (CSCMatrix)LSMR$.MODULE$.solve(a, b, x$4, x$5, x$6, true, this.$outer.canMulM_M_Semiring(Semiring$.MODULE$.semiringD(), Zero$.MODULE$.DoubleZero(), scala.reflect.ClassTag..MODULE$.Double()), HasOps$.MODULE$.canTranspose_CSC(scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero(), Semiring$.MODULE$.semiringD()), this.$outer.canMulM_M_Semiring(Semiring$.MODULE$.semiringD(), Zero$.MODULE$.DoubleZero(), scala.reflect.ClassTag..MODULE$.Double()), mip);
         }

         public {
            if (CSCMatrixOps_Ring.this == null) {
               throw null;
            } else {
               this.$outer = CSCMatrixOps_Ring.this;
            }
         }
      });
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public class FrobeniusCSCProduct$ implements UFunc.UImpl2 {
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

      public double apply(final CSCMatrix v, final CSCMatrix v2) {
         while(true) {
            boolean cond$macro$1 = v.cols() == v2.cols() && v.rows() == v2.rows();
            if (!cond$macro$1) {
               throw new IllegalArgumentException("requirement failed: dimensions must match!: v.cols.==(v2.cols).&&(v.rows.==(v2.rows))");
            }

            if (v.activeSize() <= v2.activeSize()) {
               double result = (double)0.0F;
               int index$macro$3 = 0;

               for(int limit$macro$5 = v.cols(); index$macro$3 < limit$macro$5; ++index$macro$3) {
                  int vBegin = v.colPtrs()[index$macro$3];
                  int vEnd = v.colPtrs()[index$macro$3 + 1];
                  int v2Begin = v2.colPtrs()[index$macro$3];
                  int v2End = v2.colPtrs()[index$macro$3 + 1];
                  result += this.dpRange(v, vBegin, vEnd, v2, v2Begin, v2End);
               }

               return result;
            }

            CSCMatrix var10000 = v2;
            v2 = v;
            v = var10000;
         }
      }

      private double dpRange(final CSCMatrix s, final int sBegin, final int sEnd, final CSCMatrix t, final int tBegin, final int tEnd) {
         while(true) {
            int sLength = sEnd - sBegin;
            int tLength = tEnd - tBegin;
            if (tLength >= sLength) {
               double var20;
               if (tLength < 32) {
                  double result = (double)0.0F;
                  int tOff = tBegin;
                  int index$macro$2 = sBegin;

                  for(int limit$macro$4 = sEnd; index$macro$2 < limit$macro$4; ++index$macro$2) {
                     int sRow = s.rowIndices()[index$macro$2];
                     int newTOff = ArrayUtil$.MODULE$.gallopSearch(t.rowIndices(), tOff, tEnd, sRow);
                     if (newTOff < 0) {
                        tOff = ~newTOff + 1;
                     } else {
                        result += s.data$mcD$sp()[index$macro$2] * t.data$mcD$sp()[newTOff];
                        tOff = newTOff + 1;
                     }
                  }

                  var20 = result;
               } else {
                  int sMid = (int)(((long)sBegin + (long)sEnd) / 2L);
                  int sRow = s.rowIndices()[sMid];
                  int tOff = Arrays.binarySearch(t.rowIndices(), tBegin, tEnd, sRow);
                  if (tOff < 0) {
                     tOff = ~tOff;
                  }

                  var20 = this.dpRange(s, sBegin, sMid, t, tBegin, tOff) + this.dpRange(s, sMid, sEnd, t, tOff, tEnd);
               }

               return var20;
            }

            CSCMatrix var10000 = t;
            int var10001 = tBegin;
            int var10002 = tEnd;
            tEnd = sEnd;
            tBegin = sBegin;
            t = s;
            sEnd = var10002;
            sBegin = var10001;
            s = var10000;
         }
      }
   }
}
