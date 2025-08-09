package scala.collection;

import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import java.util.NoSuchElementException;
import scala.$less$colon$less$;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Predef$;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Range;
import scala.collection.immutable.Range$;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.ArrayBuffer$;
import scala.collection.mutable.Builder;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashMap$;
import scala.math.Ordering;
import scala.reflect.ClassTag$;
import scala.reflect.ScalaSignature;
import scala.runtime.BooleanRef;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.RichInt$;

@ScalaSignature(
   bytes = "\u0006\u0005\u0019Uca\u00022d!\u0003\r\t\u0001\u001b\u0005\b\u0003\u000f\u0001A\u0011AA\u0005\u0011\u001d\t\t\u0002\u0001C!\u0003'Aq!a\u0007\u0001\r\u0003\ti\u0002C\u0004\u0002\u0004\u00021\t!!\"\t\u000f\u0005\u001d\u0005\u0001\"\u0001\u0002\n\"9\u00111\u0014\u0001\u0005\u0006\u0005u\u0005bBAY\u0001\u0011\u0005\u00111\u0017\u0005\b\u0003\u007f\u0003AQAAa\u0011\u001d\ty\r\u0001C\u0001\u0003#Dq!!:\u0001\t\u000b\n9\u000fC\u0004\u0002x\u0002!\t!!?\t\u000f\t%\u0001\u0001\"\u0002\u0003\f!9!1\u0004\u0001\u0005F\tu\u0001b\u0002B\u0017\u0001\u0011\u0015!q\u0006\u0005\b\u00053\u0002AQIAC\u0011\u001d\u0011Y\u0006\u0001C\u0001\u0005;BqAa\u0018\u0001\t\u0003\u0011\t\u0007C\u0004\u0003t\u0001!\tA!\u0018\t\u000f\tU\u0004\u0001\"\u0001\u0003x!9!q\u0010\u0001\u0005\u0002\t\u0005\u0005\"\u0003BL\u0001E\u0005I\u0011\u0001BM\u0011\u001d\u0011\u0019\f\u0001C\u0001\u0005kCqA!2\u0001\t\u0003\u00119\rC\u0004\u0003N\u0002!\tAa4\t\u000f\t}\u0007\u0001\"\u0002\u0003b\"9!q\u001c\u0001\u0005\u0002\t%\bb\u0002By\u0001\u0011\u0015!1\u001f\u0005\b\u0005\u007f\u0004A\u0011AB\u0001\u0011\u001d\u0011y\u0010\u0001C\u0001\u0007\u000fAqaa\u0006\u0001\t\u0003\u0019I\u0002C\u0004\u0004\u0018\u0001!\ta!\n\t\u000f\rU\u0002\u0001\"\u0001\u00048!I1Q\t\u0001\u0012\u0002\u0013\u00051q\t\u0005\b\u0007\u0017\u0002A\u0011AB'\u0011\u001d\u0019Y\u0005\u0001C\u0001\u0007'B\u0001b!\u0018\u0001A\u0013%1q\f\u0005\b\u0007K\u0002A\u0011AB4\u0011\u001d\u0019)\u0007\u0001C\u0001\u0007kBqaa\"\u0001\t\u0003\u0019I\tC\u0004\u0004\b\u0002!\taa&\t\u000f\r%\u0006\u0001\"\u0001\u0004,\"91Q\u0017\u0001\u0005\u0002\r]\u0006bBBb\u0001\u0011\u00051Q\u0019\u0005\b\u0007#\u0004A\u0011ABj\u0011\u001d\u00199\u000f\u0001C\u0001\u0007SDqa!<\u0001\t\u0003\u0019yO\u0002\u0004\u0004v\u0002!1q\u001f\u0005\b\u0007\u007f|C\u0011\u0001C\u0001\u00111!9a\fI\u0001\u0002\u0007\u0005\u000b\u0011\u0002C\u0005\u0011!!\tc\fQ\u0001\n\u0011=\u0001\u0002\u0003C\u0012_\u0001\u0006I\u0001b\u0007\t\u0011\u0011\u0015r\u0006)Q\u0005\u0005\u000bCq\u0001b\n0\t\u0003!I\u0003C\u0004\u0005,=\"\t\u0001\"\f\t\u000f\u0011\rs\u0006\"\u0003\u0005F!AAQJ\u0018!\n\u0013!yE\u0002\u0004\u0005R\u0001!A1\u000b\u0005\u000b\u0007gL$\u0011!Q\u0001\n\u0005\r\u0002bBB\u0000s\u0011\u0005AQ\u000b\u0005\r\t7J\u0004\u0013!A\u0002B\u0003%AQ\f\u0005\t\tCI\u0004\u0015!\u0003\u0005d!AA\u0011N\u001d!\u0002\u0013!Y\u0002\u0003\u0005\u0005le\u0002\u000b\u0011\u0002C\u000e\u0011!!i'\u000fQ\u0001\n\u0011m\u0001\u0002\u0003C\u0013s\u0001\u0006KA!\"\t\u000f\u0011\u001d\u0012\b\"\u0001\u0005*!9A1F\u001d\u0005\u0002\u00115\u0002b\u0002C's\u0011%Aq\u000e\u0005\b\tc\u0002A\u0011\u0001C:\u0011\u001d!)\t\u0001C\u0001\t\u000fCq\u0001b%\u0001\t\u0003!)\nC\u0004\u0005(\u0002!\t\u0001\"+\t\u000f\u0011]\u0006\u0001\"\u0012\u0005:\"9Aq\u0018\u0001\u0005\u0002\u0011\u0005\u0007b\u0002C\\\u0001\u0011\u0015CQ\u0019\u0005\b\t\u007f\u0003A\u0011\u0001Cj\u0011\u001d!\t\u000f\u0001C\u0003\tGDq\u0001\">\u0001\t\u0003\"I\u0003C\u0004\u0005x\u0002!\t\u0001\"?\t\u000f\u0015\u0015\u0001\u0001\"\u0001\u0006\b!9Q\u0011\u0004\u0001\u0005\u0002\u0015m\u0001bBC\u0014\u0001\u0011\u0005Q\u0011\u0006\u0005\b\u000bk\u0001A\u0011AC\u001c\u0011\u001d)i\u0005\u0001C\u0001\u000b\u001fB\u0001\"b\u0018\u0001\t#\u0019W\u0011\r\u0005\b\u000bk\u0002A\u0011AC<\u0011\u001d))\b\u0001C\u0001\u000b+C\u0001\"b+\u0001A\u0013%QQ\u0016\u0005\u000f\u000b\u0013\u0004\u0001\u0013aA\u0001\u0002\u0013%Q1ZCm\u00119)Y\u000e\u0001I\u0001\u0004\u0003\u0005I\u0011BCo\u000bCDa\"b7\u0001!\u0003\r\t\u0011!C\u0005\u000bG,yoB\u0004\u0006r\u000eD\t!b=\u0007\r\t\u001c\u0007\u0012AC{\u0011\u001d\u0019y0\u0018C\u0001\u000b{Dq!b@^\t\u00131\t\u0001C\u0004\u0007(u#IA\"\u000b\t\u000f\u0019\u0005S\f\"\u0003\u0007D\t11+Z9PaNT!\u0001Z3\u0002\u0015\r|G\u000e\\3di&|gNC\u0001g\u0003\u0015\u00198-\u00197b\u0007\u0001)R!\u001b;|\u0003\u0007\u00192\u0001\u00016o!\tYG.D\u0001f\u0013\tiWMA\u0002B]f\u0004ba\u001c9su\u0006\u0005Q\"A2\n\u0005E\u001c'aC%uKJ\f'\r\\3PaN\u0004\"a\u001d;\r\u0001\u00111Q\u000f\u0001CC\u0002Y\u0014\u0011!Q\t\u0003o*\u0004\"a\u001b=\n\u0005e,'a\u0002(pi\"Lgn\u001a\t\u0003gn$a\u0001 \u0001\u0005\u0006\u0004i(AA\"D+\t1h\u0010B\u0003\u0000w\n\u0007aO\u0001\u0003`I\u0011\n\u0004cA:\u0002\u0004\u00119\u0011Q\u0001\u0001\u0005\u0006\u00041(!A\"\u0002\r\u0011Jg.\u001b;%)\t\tY\u0001E\u0002l\u0003\u001bI1!a\u0004f\u0005\u0011)f.\u001b;\u0002\tYLWm^\u000b\u0003\u0003+\u0001Ba\\A\fe&\u0019\u0011\u0011D2\u0003\u000fM+\u0017OV5fo\u0006)\u0011\r\u001d9msR\u0019!/a\b\t\u000f\u0005\u00052\u00011\u0001\u0002$\u0005\t\u0011\u000eE\u0002l\u0003KI1!a\nf\u0005\rIe\u000e\u001e\u0015\u0006\u0007\u0005-\u0012\u0011\n\t\u0006W\u00065\u0012\u0011G\u0005\u0004\u0003_)'A\u0002;ie><8\u000f\u0005\u0003\u00024\u0005\rc\u0002BA\u001b\u0003\u007fqA!a\u000e\u0002>5\u0011\u0011\u0011\b\u0006\u0004\u0003w9\u0017A\u0002\u001fs_>$h(C\u0001g\u0013\r\t\t%Z\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\t)%a\u0012\u00033%sG-\u001a=PkR|eMQ8v]\u0012\u001cX\t_2faRLwN\u001c\u0006\u0004\u0003\u0003*\u0017g\u0002\u0010\u0002L\u0005m\u0013\u0011\u0011\t\u0005\u0003\u001b\n)F\u0004\u0003\u0002P\u0005E\u0003cAA\u001cK&\u0019\u00111K3\u0002\rA\u0013X\rZ3g\u0013\u0011\t9&!\u0017\u0003\rM#(/\u001b8h\u0015\r\t\u0019&Z\u0019\nG\u0005u\u0013QMA<\u0003O*B!a\u0018\u0002bU\u0011\u00111\n\u0003\b\u0003G:'\u0019AA7\u0005\u0005!\u0016\u0002BA4\u0003S\n1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\n$bAA6K\u00061A\u000f\u001b:poN\f2a^A8!\u0011\t\t(a\u001d\u000f\u0007-\fy$\u0003\u0003\u0002v\u0005\u001d#!\u0003+ie><\u0018M\u00197fc%\u0019\u0013\u0011PA>\u0003{\nYGD\u0002l\u0003wJ1!a\u001bfc\u0015\u00113.ZA@\u0005\u0015\u00198-\u00197bc\r1\u0013\u0011G\u0001\u0007Y\u0016tw\r\u001e5\u0016\u0005\u0005\r\u0012!\u00039sKB,g\u000eZ3e+\u0011\tY)!%\u0015\t\u00055\u0015q\u0013\t\u0005gn\fy\tE\u0002t\u0003##q!a%\u0006\u0005\u0004\t)JA\u0001C#\t\u0011(\u000eC\u0004\u0002\u001a\u0016\u0001\r!a$\u0002\t\u0015dW-\\\u0001\fIAdWo\u001d\u0013d_2|g.\u0006\u0003\u0002 \u0006\u0015F\u0003BAQ\u0003O\u0003Ba]>\u0002$B\u00191/!*\u0005\u000f\u0005MeA1\u0001\u0002\u0016\"9\u0011\u0011\u0014\u0004A\u0002\u0005\r\u0006f\u0001\u0004\u0002,B\u00191.!,\n\u0007\u0005=VM\u0001\u0004j]2Lg.Z\u0001\tCB\u0004XM\u001c3fIV!\u0011QWA^)\u0011\t9,!0\u0011\tM\\\u0018\u0011\u0018\t\u0004g\u0006mFaBAJ\u000f\t\u0007\u0011Q\u0013\u0005\b\u00033;\u0001\u0019AA]\u0003-!3m\u001c7p]\u0012\u0002H.^:\u0016\t\u0005\r\u0017\u0011\u001a\u000b\u0005\u0003\u000b\fY\r\u0005\u0003tw\u0006\u001d\u0007cA:\u0002J\u00129\u00111\u0013\u0005C\u0002\u0005U\u0005bBAM\u0011\u0001\u0007\u0011q\u0019\u0015\u0004\u0011\u0005-\u0016\u0001\u00049sKB,g\u000eZ3e\u00032dW\u0003BAj\u00033$B!!6\u0002\\B!1o_Al!\r\u0019\u0018\u0011\u001c\u0003\b\u0003'K!\u0019AAK\u0011\u001d\ti.\u0003a\u0001\u0003?\fa\u0001\u001d:fM&D\b#B8\u0002b\u0006]\u0017bAArG\na\u0011\n^3sC\ndWm\u00148dK\u0006\u0001B\u0005\u001d7vg\u0012\u0002H.^:%G>dwN\\\u000b\u0005\u0003S\fy\u000f\u0006\u0003\u0002l\u0006E\b\u0003B:|\u0003[\u00042a]Ax\t\u001d\t\u0019J\u0003b\u0001\u0003+Cq!!8\u000b\u0001\u0004\t\u0019\u0010E\u0003p\u0003C\fi\u000fK\u0002\u000b\u0003W\u000b1\"\u00199qK:$W\rZ!mYV!\u00111 B\u0001)\u0011\tiPa\u0001\u0011\tM\\\u0018q \t\u0004g\n\u0005AaBAJ\u0017\t\u0007\u0011Q\u0013\u0005\b\u0005\u000bY\u0001\u0019\u0001B\u0004\u0003\u0019\u0019XO\u001a4jqB)q.!9\u0002\u0000\u0006\u0001BeY8m_:$\u0003\u000f\\;tIAdWo]\u000b\u0005\u0005\u001b\u0011\u0019\u0002\u0006\u0003\u0003\u0010\tU\u0001\u0003B:|\u0005#\u00012a\u001dB\n\t\u001d\t\u0019\n\u0004b\u0001\u0003+CqA!\u0002\r\u0001\u0004\u00119\u0002E\u0003p\u0003C\u0014\t\u0002K\u0002\r\u0003W\u000baaY8oG\u0006$X\u0003\u0002B\u0010\u0005K!BA!\t\u0003(A!1o\u001fB\u0012!\r\u0019(Q\u0005\u0003\b\u0003'k!\u0019AAK\u0011\u001d\u0011)!\u0004a\u0001\u0005S\u0001Ra\\Aq\u0005GA3!DAV\u0003\u0015)h.[8o+\u0011\u0011\tDa\u000e\u0015\t\tM\"\u0011\b\t\u0005gn\u0014)\u0004E\u0002t\u0005o!q!a%\u000f\u0005\u0004\t)\nC\u0004\u0003<9\u0001\rA!\u0010\u0002\tQD\u0017\r\u001e\t\u0006_\n}\"QG\u0005\u0004\u0005\u0003\u001a'aA*fc\"ZaB!\u0012\u0003L\t5#\u0011\u000bB*!\rY'qI\u0005\u0004\u0005\u0013*'A\u00033faJ,7-\u0019;fI\u00069Q.Z:tC\u001e,\u0017E\u0001B(\u0003Q)6/\u001a\u0011aG>t7-\u0019;aA%t7\u000f^3bI\u0006)1/\u001b8dK\u0006\u0012!QK\u0001\u0007e9\n4G\f\u0019)\u00079\tY+\u0001\u0003tSj,\u0017\u0001\u00033jgRLgn\u0019;\u0016\u0005\u0005\u0005\u0011A\u00033jgRLgn\u0019;CsV!!1\rB9)\u0011\t\tA!\u001a\t\u000f\t\u001d\u0014\u00031\u0001\u0003j\u0005\ta\r\u0005\u0004l\u0005W\u0012(qN\u0005\u0004\u0005[*'!\u0003$v]\u000e$\u0018n\u001c82!\r\u0019(\u0011\u000f\u0003\u0007\u0003'\u000b\"\u0019\u0001<\u0002\u000fI,g/\u001a:tK\u0006y!/\u001a<feN,\u0017\n^3sCR|'/\u0006\u0002\u0003zA!qNa\u001fs\u0013\r\u0011ih\u0019\u0002\t\u0013R,'/\u0019;pe\u0006Q1\u000f^1siN<\u0016\u000e\u001e5\u0016\t\t\r%\u0011\u0013\u000b\u0007\u0005\u000b\u0013YIa%\u0011\u0007-\u00149)C\u0002\u0003\n\u0016\u0014qAQ8pY\u0016\fg\u000eC\u0004\u0003<Q\u0001\rA!$\u0011\u000b=\f\tOa$\u0011\u0007M\u0014\t\nB\u0004\u0002\u0014R\u0011\r!!&\t\u0013\tUE\u0003%AA\u0002\u0005\r\u0012AB8gMN,G/\u0001\u000bti\u0006\u0014Ho],ji\"$C-\u001a4bk2$HEM\u000b\u0005\u00057\u0013\t,\u0006\u0002\u0003\u001e*\"\u00111\u0005BPW\t\u0011\t\u000b\u0005\u0003\u0003$\n5VB\u0001BS\u0015\u0011\u00119K!+\u0002\u0013Ut7\r[3dW\u0016$'b\u0001BVK\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\t=&Q\u0015\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,GaBAJ+\t\u0007\u0011QS\u0001\tK:$7oV5uQV!!q\u0017Bb)\u0011\u0011)I!/\t\u000f\tmb\u00031\u0001\u0003<B)qN!0\u0003B&\u0019!qX2\u0003\u0011%#XM]1cY\u0016\u00042a\u001dBb\t\u001d\t\u0019J\u0006b\u0001\u0003+\u000b1\"[:EK\u001aLg.\u001a3BiR!!Q\u0011Be\u0011\u001d\u0011Ym\u0006a\u0001\u0003G\t1!\u001b3y\u0003\u0015\u0001\u0018\r\u001a+p+\u0011\u0011\tNa6\u0015\r\tM'\u0011\u001cBo!\u0011\u00198P!6\u0011\u0007M\u00149\u000eB\u0004\u0002\u0014b\u0011\r!!&\t\u000f\tm\u0007\u00041\u0001\u0002$\u0005\u0019A.\u001a8\t\u000f\u0005e\u0005\u00041\u0001\u0003V\u0006i1/Z4nK:$H*\u001a8hi\"$B!a\t\u0003d\"9!Q]\rA\u0002\t\u001d\u0018!\u00019\u0011\r-\u0014YG\u001dBC)\u0019\t\u0019Ca;\u0003n\"9!Q\u001d\u000eA\u0002\t\u001d\bb\u0002Bx5\u0001\u0007\u00111E\u0001\u0005MJ|W.\u0001\u0007qe\u00164\u0017\u000e\u001f'f]\u001e$\b\u000e\u0006\u0003\u0002$\tU\bb\u0002Bs7\u0001\u0007!q\u001d\u0015\f7\t\u0015#1\nB}\u0005#\u0012\u0019&\t\u0002\u0003|\u0006ISk]3!g\u0016<W.\u001a8u\u0019\u0016tw\r\u001e5!S:\u001cH/Z1eA=4\u0007\u0005\u001d:fM&DH*\u001a8hi\"D3aGAV\u0003)Ig\u000eZ3y/\",'/\u001a\u000b\u0007\u0003G\u0019\u0019a!\u0002\t\u000f\t\u0015H\u00041\u0001\u0003h\"9!q\u001e\u000fA\u0002\u0005\rB\u0003BA\u0012\u0007\u0013AqA!:\u001e\u0001\u0004\u00119\u000fK\u0006\u001e\u0007\u001b\u0011Yea\u0005\u0003R\tM\u0003cA6\u0004\u0010%\u00191\u0011C3\u0003)\u0011,\u0007O]3dCR,Gm\u0014<feJLG-\u001b8hC\t\u0019)\"A&Pm\u0016\u0014(/\u001b3fA%tG-\u001a=XQ\u0016\u0014X\r\u000b9-A\u0019\u0014x.\\\u0015!S:\u001cH/Z1eA5\u0002\u0013N\u001c3fq^CWM]3)a&\u00023-\u00197mg\u0002Jg\u000eZ3y/\",'/\u001a\u0015qY\u0001\u0002\u0014&A\u0004j]\u0012,\u0007p\u00144\u0016\t\rm1\u0011\u0005\u000b\u0007\u0003G\u0019iba\t\t\u000f\u0005ee\u00041\u0001\u0004 A\u00191o!\t\u0005\u000f\u0005MeD1\u0001\u0002\u0016\"9!q\u001e\u0010A\u0002\u0005\rR\u0003BB\u0014\u0007[!B!a\t\u0004*!9\u0011\u0011T\u0010A\u0002\r-\u0002cA:\u0004.\u00119\u00111S\u0010C\u0002\u0005U\u0005fC\u0010\u0004\u000e\t-3\u0011\u0007B)\u0005'\n#aa\r\u0002\u0017>3XM\u001d:jI\u0016\u0004\u0013N\u001c3fq>3\u0007&\u001a7f[2\u0002cM]8nS\u0001Jgn\u001d;fC\u0012\u0004S\u0006I5oI\u0016DxJ\u001a\u0015fY\u0016l\u0017\u0006I2bY2\u001c\b%\u001b8eKb|e\rK3mK6d\u0003\u0005M\u0015\u0002\u00171\f7\u000f^%oI\u0016DxJZ\u000b\u0005\u0007s\u0019y\u0004\u0006\u0004\u0002$\rm2\u0011\t\u0005\b\u00033\u0003\u0003\u0019AB\u001f!\r\u00198q\b\u0003\b\u0003'\u0003#\u0019AAK\u0011%\u0019\u0019\u0005\tI\u0001\u0002\u0004\t\u0019#A\u0002f]\u0012\fQ\u0003\\1ti&sG-\u001a=PM\u0012\"WMZ1vYR$#'\u0006\u0003\u0003\u001c\u000e%CaBAJC\t\u0007\u0011QS\u0001\u000fY\u0006\u001cH/\u00138eKb<\u0006.\u001a:f)\u0019\t\u0019ca\u0014\u0004R!9!Q\u001d\u0012A\u0002\t\u001d\bbBB\"E\u0001\u0007\u00111\u0005\u000b\u0005\u0003G\u0019)\u0006C\u0004\u0003f\u000e\u0002\rAa:)\u0017\r\u001aiAa\u0013\u0004Z\tE#1K\u0011\u0003\u00077\n\u0011m\u0014<feJLG-\u001a\u0011mCN$\u0018J\u001c3fq^CWM]3)a2\u0002SM\u001c3*A%t7\u000f^3bI\u0002j\u0003\u0005\\1ti&sG-\u001a=XQ\u0016\u0014X\r\u000b9*A\r\fG\u000e\\:!Y\u0006\u001cH/\u00138eKb<\u0006.\u001a:fQAd\u0003%\u00138u]5\u000b\u0007PV1mk\u0016L\u0013\u0001\u0004;p\u000f\u0016tWM]5d'\u0016\fXCAB1!\u0011y'q\b:)\u0007\u0011\nY+\u0001\u0007j]\u0012,\u0007p\u00144TY&\u001cW-\u0006\u0003\u0004j\rEDCBA\u0012\u0007W\u001a\u0019\bC\u0004\u0003<\u0015\u0002\ra!\u001c\u0011\u000b=\u0014yda\u001c\u0011\u0007M\u001c\t\bB\u0004\u0002\u0014\u0016\u0012\r!!&\t\u000f\t=X\u00051\u0001\u0002$U!1qOB@)\u0011\t\u0019c!\u001f\t\u000f\tmb\u00051\u0001\u0004|A)qNa\u0010\u0004~A\u00191oa \u0005\u000f\u0005MeE1\u0001\u0002\u0016\"Zae!\u0004\u0003L\r\r%\u0011\u000bB*C\t\u0019))\u0001.Pm\u0016\u0014(/\u001b3fA%tG-\u001a=PMNc\u0017nY3)i\"\fG\u000f\f\u0011ge>l\u0017\u0006I5ogR,\u0017\r\u001a\u0011.A%tG-\u001a=PMNc\u0017nY3)i\"\fG/\u000b\u0011dC2d7\u000fI5oI\u0016DxJZ*mS\u000e,\u0007\u0006\u001e5bi2\u0002\u0003'K\u0001\u0011Y\u0006\u001cH/\u00138eKb|em\u00157jG\u0016,Baa#\u0004\u0014R1\u00111EBG\u0007+CqAa\u000f(\u0001\u0004\u0019y\tE\u0003p\u0005\u007f\u0019\t\nE\u0002t\u0007'#q!a%(\u0005\u0004\t)\nC\u0004\u0004D\u001d\u0002\r!a\t\u0016\t\re5\u0011\u0015\u000b\u0005\u0003G\u0019Y\nC\u0004\u0003<!\u0002\ra!(\u0011\u000b=\u0014yda(\u0011\u0007M\u001c\t\u000bB\u0004\u0002\u0014\"\u0012\r!!&)\u0017!\u001aiAa\u0013\u0004&\nE#1K\u0011\u0003\u0007O\u000b\u0001o\u0014<feJLG-\u001a\u0011mCN$\u0018J\u001c3fq>37\u000b\\5dK\"\"\b.\u0019;-A\u0015tG-\u000b\u0011j]N$X-\u00193![\u0001b\u0017m\u001d;J]\u0012,\u0007p\u00144TY&\u001cW\r\u000b;iCRL\u0003eY1mYN\u0004C.Y:u\u0013:$W\r_(g'2L7-\u001a\u0015uQ\u0006$H\u0006I%oi:j\u0015\r\u001f,bYV,\u0017&\u0001\u0005gS:$G*Y:u)\u0011\u0019ika-\u0011\t-\u001cyK]\u0005\u0004\u0007c+'AB(qi&|g\u000eC\u0004\u0003f&\u0002\rAa:\u0002\u001b\r|g\u000e^1j]N\u001cF.[2f+\u0011\u0019Il!1\u0015\t\t\u001551\u0018\u0005\b\u0005wQ\u0003\u0019AB_!\u0015y'qHB`!\r\u00198\u0011\u0019\u0003\b\u0003'S#\u0019AAK\u0003!\u0019wN\u001c;bS:\u001cX\u0003BBd\u0007\u001b$BA!\"\u0004J\"9\u0011\u0011T\u0016A\u0002\r-\u0007cA:\u0004N\u001291qZ\u0016C\u0002\u0005U%AA!2\u0003)\u0011XM^3sg\u0016l\u0015\r]\u000b\u0005\u0007+\u001cY\u000e\u0006\u0003\u0004X\u000eu\u0007\u0003B:|\u00073\u00042a]Bn\t\u0019\t\u0019\n\fb\u0001m\"9!q\r\u0017A\u0002\r}\u0007CB6\u0003lI\u001cI\u000eK\u0006-\u0005\u000b\u0012Yea9\u0003R\tM\u0013EABs\u0003u*6/\u001a\u0011/e\u00164XM]:f\u0013R,'/\u0019;pe:j\u0017\r\u001d\u0015gS9\"x\u000e\u000b\u0018/]%\u0002\u0013N\\:uK\u0006$\u0007e\u001c4!]I,g/\u001a:tK6\u000b\u0007\u000f\u000b4*\u00031\u0001XM]7vi\u0006$\u0018n\u001c8t+\t\u0019Y\u000fE\u0003p\u0005w\n\t!\u0001\u0007d_6\u0014\u0017N\\1uS>t7\u000f\u0006\u0003\u0004l\u000eE\bbBBz]\u0001\u0007\u00111E\u0001\u0002]\ny\u0001+\u001a:nkR\fG/[8og&#(oE\u00020\u0007s\u0004Ra\\B~\u0003\u0003I1a!@d\u0005A\t%m\u001d;sC\u000e$\u0018\n^3sCR|'/\u0001\u0004=S:LGO\u0010\u000b\u0003\t\u0007\u00012\u0001\"\u00020\u001b\u0005\u0001\u0011a\u0001=%iA91\u000eb\u0003\u0005\u0010\u0011m\u0011b\u0001C\u0007K\n1A+\u001e9mKJ\u0002R\u0001\"\u0005\u0005\u0018Il!\u0001b\u0005\u000b\u0007\u0011U1-A\u0004nkR\f'\r\\3\n\t\u0011eA1\u0003\u0002\f\u0003J\u0014\u0018-\u001f\"vM\u001a,'\u000fE\u0003l\t;\t\u0019#C\u0002\u0005 \u0015\u0014Q!\u0011:sCf\fA!\u001a7ng\u0006!\u0011\u000e\u001a=t\u0003!y\u0006.Y:OKb$\u0018a\u00025bg:+\u0007\u0010^\u000b\u0003\u0005\u000b\u000bAA\\3yiR\u0011\u0011\u0011\u0001\u0015\u0006m\u0011EB\u0011\b\t\u0006W\u00065B1\u0007\t\u0005\u0003g!)$\u0003\u0003\u00058\u0005\u001d#A\u0006(p'V\u001c\u0007.\u00127f[\u0016tG/\u0012=dKB$\u0018n\u001c82\u000fy\tY\u0005b\u000f\u0005BEJ1%!\u0018\u0002f\u0011u\u0012qM\u0019\nG\u0005e\u00141\u0010C \u0003W\nTAI6f\u0003\u007f\n4A\nC\u001a\u0003\u0011\u0019x/\u00199\u0015\r\u0005-Aq\tC%\u0011\u001d\t\tc\u000ea\u0001\u0003GAq\u0001b\u00138\u0001\u0004\t\u0019#A\u0001k\u0003\u0011Ig.\u001b;\u0015\u0005\u0011%!aD\"p[\nLg.\u0019;j_:\u001c\u0018\n\u001e:\u0014\u0007e\u001aI\u0010\u0006\u0003\u0005X\u0011e\u0003c\u0001C\u0003s!911_\u001eA\u0002\u0005\r\u0012a\u0001=%oAI1\u000eb\u0018\u0005d\u0011mA1D\u0005\u0004\tC*'A\u0002+va2,7\u0007\u0005\u0003p\tK\u0012\u0018b\u0001C4G\nQ\u0011J\u001c3fq\u0016$7+Z9\u0002\t\rtGo]\u0001\u0005]Vl7/\u0001\u0003pM\u001a\u001cHC\u0001C/\u0003\u0019\u0019xN\u001d;fIV!AQ\u000fCB)\u0011\t\t\u0001b\u001e\t\u000f\u0011eT\tq\u0001\u0005|\u0005\u0019qN\u001d3\u0011\r\u0005MBQ\u0010CA\u0013\u0011!y(a\u0012\u0003\u0011=\u0013H-\u001a:j]\u001e\u00042a\u001dCB\t\u001d\t\u0019*\u0012b\u0001\u0003+\u000b\u0001b]8si^KG\u000f\u001b\u000b\u0005\u0003\u0003!I\tC\u0004\u0005\f\u001a\u0003\r\u0001\"$\u0002\u00051$\bcB6\u0005\u0010J\u0014(QQ\u0005\u0004\t#+'!\u0003$v]\u000e$\u0018n\u001c83\u0003\u0019\u0019xN\u001d;CsV!Aq\u0013CQ)\u0011!I\nb)\u0015\t\u0005\u0005A1\u0014\u0005\b\ts:\u00059\u0001CO!\u0019\t\u0019\u0004\" \u0005 B\u00191\u000f\")\u0005\r\u0005MuI1\u0001w\u0011\u001d\u00119g\u0012a\u0001\tK\u0003ba\u001bB6e\u0012}\u0015aB5oI&\u001cWm]\u000b\u0003\tW\u0003B\u0001\",\u000546\u0011Aq\u0016\u0006\u0004\tc\u001b\u0017!C5n[V$\u0018M\u00197f\u0013\u0011!)\fb,\u0003\u000bI\u000bgnZ3\u0002\u0017ML'0Z\"p[B\f'/\u001a\u000b\u0005\u0003G!Y\fC\u0004\u0005>&\u0003\r!a\t\u0002\u0013=$\b.\u001a:TSj,\u0017!\u00047f]\u001e$\bnQ8na\u0006\u0014X\r\u0006\u0003\u0002$\u0011\r\u0007b\u0002Bn\u0015\u0002\u0007\u00111\u0005\u000b\u0005\u0003G!9\rC\u0004\u0003<-\u0003\r\u0001\"31\t\u0011-Gq\u001a\t\u0006_\nuFQ\u001a\t\u0004g\u0012=Ga\u0003Ci\t\u000f\f\t\u0011!A\u0003\u0002Y\u00141a\u0018\u00132)\u0011\t\u0019\u0003\"6\t\u000f\tmB\n1\u0001\u0005XB\"A\u0011\u001cCo!\u0015y'Q\u0018Cn!\r\u0019HQ\u001c\u0003\f\t?$).!A\u0001\u0002\u000b\u0005aOA\u0002`II\n\u0001\u0002\\3oORD\u0017j]\u000b\u0003\tK\u0004B\u0001b:\u0005n:\u0019q\u000e\";\n\u0007\u0011-8-A\u0006Ji\u0016\u0014\u0018M\u00197f\u001fB\u001c\u0018\u0002\u0002Cx\tc\u0014abU5{K\u000e{W\u000e]1sK>\u00038OC\u0002\u0005l\u000eD3!TAV\u0003\u001dI7/R7qif\fAb]1nK\u0016cW-\\3oiN,B\u0001b?\u0006\u0004Q!!Q\u0011C\u007f\u0011\u001d\u0011Yd\u0014a\u0001\t\u007f\u0004Ra\\Aq\u000b\u0003\u00012a]C\u0002\t\u001d\t\u0019j\u0014b\u0001\u0003+\u000b1bY8se\u0016\u001c\bo\u001c8egV!Q\u0011BC\n)\u0011)Y!\"\u0006\u0015\t\t\u0015UQ\u0002\u0005\b\u0005K\u0004\u0006\u0019AC\b!!YGq\u0012:\u0006\u0012\t\u0015\u0005cA:\u0006\u0014\u00111\u00111\u0013)C\u0002YDqAa\u000fQ\u0001\u0004)9\u0002E\u0003p\u0005\u007f)\t\"\u0001\u0003eS\u001a4W\u0003BC\u000f\u000bK!B!!\u0001\u0006 !9!1H)A\u0002\u0015\u0005\u0002#B8\u0003@\u0015\r\u0002cA:\u0006&\u00119\u00111S)C\u0002\u0005U\u0015!C5oi\u0016\u00148/Z2u+\u0011)Y#b\r\u0015\t\u0005\u0005QQ\u0006\u0005\b\u0005w\u0011\u0006\u0019AC\u0018!\u0015y'qHC\u0019!\r\u0019X1\u0007\u0003\b\u0003'\u0013&\u0019AAK\u0003\u0015\u0001\u0018\r^2i+\u0011)I$b\u0010\u0015\u0011\u0015mR\u0011IC\"\u000b\u0013\u0002Ba]>\u0006>A\u00191/b\u0010\u0005\u000f\u0005M5K1\u0001\u0002\u0016\"9!q^*A\u0002\u0005\r\u0002bBC#'\u0002\u0007QqI\u0001\u0006_RDWM\u001d\t\u0006_\u0006\u0005XQ\b\u0005\b\u000b\u0017\u001a\u0006\u0019AA\u0012\u0003!\u0011X\r\u001d7bG\u0016$\u0017aB;qI\u0006$X\rZ\u000b\u0005\u000b#*9\u0006\u0006\u0004\u0006T\u0015eSQ\f\t\u0005gn,)\u0006E\u0002t\u000b/\"q!a%U\u0005\u0004\t)\nC\u0004\u0006\\Q\u0003\r!a\t\u0002\u000b%tG-\u001a=\t\u000f\u0005eE\u000b1\u0001\u0006V\u0005IqnY2D_VtGo]\u000b\u0005\u000bG*i\u0007\u0006\u0003\u0006f\u0015=\u0004\u0003\u0003C\t\u000bO*Y'a\t\n\t\u0015%D1\u0003\u0002\u0004\u001b\u0006\u0004\bcA:\u0006n\u00111\u00111S+C\u0002YDq!\"\u001dV\u0001\u0004)\u0019(\u0001\u0002tcB)qNa\u0010\u0006l\u000511/Z1sG\",B!\"\u001f\u0006\u0012R!Q1PCJ)\u0011)i(b#\u0011\t\u0015}TQ\u0011\b\u0004_\u0016\u0005\u0015bACBG\u0006I1+Z1sG\"LgnZ\u0005\u0005\u000b\u000f+II\u0001\u0007TK\u0006\u00148\r\u001b*fgVdGOC\u0002\u0006\u0004\u000eDq\u0001\"\u001fW\u0001\b)i\t\u0005\u0004\u00024\u0011uTq\u0012\t\u0004g\u0016EEaBAJ-\n\u0007\u0011Q\u0013\u0005\b\u000333\u0006\u0019ACH+\u0011)9*\")\u0015\u0011\u0015eU1UCS\u000bO#B!\" \u0006\u001c\"9A\u0011P,A\u0004\u0015u\u0005CBA\u001a\t{*y\nE\u0002t\u000bC#q!a%X\u0005\u0004\t)\nC\u0004\u0002\u001a^\u0003\r!b(\t\u000f\t=x\u000b1\u0001\u0002$!9Q\u0011V,A\u0002\u0005\r\u0012A\u0001;p\u00031a\u0017N\\3beN+\u0017M]2i+\u0011)y+\"/\u0015\u0011\u0015EV1XCc\u000b\u000f$B!\" \u00064\"9A\u0011\u0010-A\u0004\u0015U\u0006CBA\u001a\t{*9\fE\u0002t\u000bs#q!a%Y\u0005\u0004\t)\nC\u0004\u0006>b\u0003\r!b0\u0002\u0003\r\u0004Ba\\Cae&\u0019Q1Y2\u0003\tYKWm\u001e\u0005\b\u00033C\u0006\u0019AC\\\u0011\u001d\u0011)\n\u0017a\u0001\u0003G\tAb];qKJ$3m\u001c8dCR,B!\"4\u0006TR!QqZCk!\u0011\u001980\"5\u0011\u0007M,\u0019\u000eB\u0004\u0002\u0014f\u0013\r!!&\t\u000f\t\u0015\u0011\f1\u0001\u0006XB)q.!9\u0006R&\u0019!1\u00049\u0002#M,\b/\u001a:%g&TXmQ8na\u0006\u0014X\r\u0006\u0003\u0002$\u0015}\u0007b\u0002C_5\u0002\u0007\u00111E\u0005\u0004\to\u0003H\u0003BA\u0012\u000bKDqAa\u000f\\\u0001\u0004)9\u000f\r\u0003\u0006j\u00165\b#B8\u0003>\u0016-\bcA:\u0006n\u0012YA\u0011[Cs\u0003\u0003\u0005\tQ!\u0001w\u0013\r!9\f]\u0001\u0007'\u0016\fx\n]:\u0011\u0005=l6cA/\u0006xB\u00191.\"?\n\u0007\u0015mXM\u0001\u0004B]f\u0014VM\u001a\u000b\u0003\u000bg\f\u0011b[7q'\u0016\f'o\u00195\u0016\t\u0019\raQ\u0002\u000b\u0011\u0003G1)Ab\u0004\u0007\u0014\u0019]a1\u0004D\u0010\rGAqAb\u0002`\u0001\u00041I!A\u0001T!\u0015y'q\bD\u0006!\r\u0019hQ\u0002\u0003\u0007\u0003'{&\u0019\u0001<\t\u000f\u0019Eq\f1\u0001\u0002$\u0005\u0011Q\u000e\r\u0005\b\r+y\u0006\u0019AA\u0012\u0003\ti\u0017\u0007C\u0004\u0007\u001a}\u0003\rA\"\u0003\u0002\u0003]CqA\"\b`\u0001\u0004\t\u0019#\u0001\u0002oa!9a\u0011E0A\u0002\u0005\r\u0012A\u000182\u0011\u001d1)c\u0018a\u0001\u0005\u000b\u000bqAZ8so\u0006\u0014H-A\bl[B|\u0005\u000f^5nSj,wk\u001c:e+\u00111YC\"\u000e\u0015\u0015\u00195bq\u0007D\u001e\r{1y\u0004E\u0003p\r_1\u0019$C\u0002\u00072\r\u0014a\"\u00138eKb,GmU3r-&,w\u000fE\u0002t\rk!a!a%a\u0005\u00041\bb\u0002D\rA\u0002\u0007a\u0011\b\t\u0006_\n}b1\u0007\u0005\b\r;\u0001\u0007\u0019AA\u0012\u0011\u001d1\t\u0003\u0019a\u0001\u0003GAqA\"\na\u0001\u0004\u0011))\u0001\u0007l[BTU/\u001c9UC\ndW-\u0006\u0003\u0007F\u0019=CC\u0002C\u000e\r\u000f2\t\u0006C\u0004\u0007J\u0005\u0004\rAb\u0013\u0002\t]{\u0007\u000f\u001e\t\u0006_\u001a=bQ\n\t\u0004g\u001a=CABAJC\n\u0007a\u000fC\u0004\u0007T\u0005\u0004\r!a\t\u0002\t]dWM\u001c"
)
public interface SeqOps extends IterableOps {
   // $FF: synthetic method
   Object scala$collection$SeqOps$$super$concat(final IterableOnce suffix);

   // $FF: synthetic method
   int scala$collection$SeqOps$$super$sizeCompare(final int otherSize);

   // $FF: synthetic method
   int scala$collection$SeqOps$$super$sizeCompare(final Iterable that);

   // $FF: synthetic method
   static SeqView view$(final SeqOps $this) {
      return $this.view();
   }

   default SeqView view() {
      return new SeqView.Id(this);
   }

   Object apply(final int i) throws IndexOutOfBoundsException;

   int length();

   // $FF: synthetic method
   static Object prepended$(final SeqOps $this, final Object elem) {
      return $this.prepended(elem);
   }

   default Object prepended(final Object elem) {
      return this.iterableFactory().from(new View.Prepended(elem, this));
   }

   // $FF: synthetic method
   static Object $plus$colon$(final SeqOps $this, final Object elem) {
      return $this.$plus$colon(elem);
   }

   default Object $plus$colon(final Object elem) {
      return this.prepended(elem);
   }

   // $FF: synthetic method
   static Object appended$(final SeqOps $this, final Object elem) {
      return $this.appended(elem);
   }

   default Object appended(final Object elem) {
      return this.iterableFactory().from(new View.Appended(this, elem));
   }

   // $FF: synthetic method
   static Object $colon$plus$(final SeqOps $this, final Object elem) {
      return $this.$colon$plus(elem);
   }

   default Object $colon$plus(final Object elem) {
      return this.appended(elem);
   }

   // $FF: synthetic method
   static Object prependedAll$(final SeqOps $this, final IterableOnce prefix) {
      return $this.prependedAll(prefix);
   }

   default Object prependedAll(final IterableOnce prefix) {
      IterableFactory var10000 = this.iterableFactory();
      Object var10001;
      if (prefix instanceof Iterable) {
         Iterable var2 = (Iterable)prefix;
         var10001 = new View.Concat(var2, this);
      } else {
         var10001 = prefix.iterator();
         Function0 $plus$plus_xs = () -> this.iterator();
         if (var10001 == null) {
            throw null;
         }

         var10001 = var10001.concat($plus$plus_xs);
         $plus$plus_xs = null;
      }

      return var10000.from(var10001);
   }

   // $FF: synthetic method
   static Object $plus$plus$colon$(final SeqOps $this, final IterableOnce prefix) {
      return $this.$plus$plus$colon(prefix);
   }

   default Object $plus$plus$colon(final IterableOnce prefix) {
      return this.prependedAll(prefix);
   }

   // $FF: synthetic method
   static Object appendedAll$(final SeqOps $this, final IterableOnce suffix) {
      return $this.appendedAll(suffix);
   }

   default Object appendedAll(final IterableOnce suffix) {
      return this.scala$collection$SeqOps$$super$concat(suffix);
   }

   // $FF: synthetic method
   static Object $colon$plus$plus$(final SeqOps $this, final IterableOnce suffix) {
      return $this.$colon$plus$plus(suffix);
   }

   default Object $colon$plus$plus(final IterableOnce suffix) {
      return this.appendedAll(suffix);
   }

   // $FF: synthetic method
   static Object concat$(final SeqOps $this, final IterableOnce suffix) {
      return $this.concat(suffix);
   }

   default Object concat(final IterableOnce suffix) {
      return this.appendedAll(suffix);
   }

   // $FF: synthetic method
   static Object union$(final SeqOps $this, final Seq that) {
      return $this.union(that);
   }

   /** @deprecated */
   default Object union(final Seq that) {
      return this.appendedAll(that);
   }

   // $FF: synthetic method
   static int size$(final SeqOps $this) {
      return $this.size();
   }

   default int size() {
      return this.length();
   }

   // $FF: synthetic method
   static Object distinct$(final SeqOps $this) {
      return $this.distinct();
   }

   default Object distinct() {
      return this.distinctBy((x) -> Predef$.MODULE$.identity(x));
   }

   // $FF: synthetic method
   static Object distinctBy$(final SeqOps $this, final Function1 f) {
      return $this.distinctBy(f);
   }

   default Object distinctBy(final Function1 f) {
      return this.fromSpecific(new View.DistinctBy(this, f));
   }

   // $FF: synthetic method
   static Object reverse$(final SeqOps $this) {
      return $this.reverse();
   }

   default Object reverse() {
      return this.fromSpecific(this.reversed());
   }

   // $FF: synthetic method
   static Iterator reverseIterator$(final SeqOps $this) {
      return $this.reverseIterator();
   }

   default Iterator reverseIterator() {
      return this.reversed().iterator();
   }

   // $FF: synthetic method
   static boolean startsWith$(final SeqOps $this, final IterableOnce that, final int offset) {
      return $this.startsWith(that, offset);
   }

   default boolean startsWith(final IterableOnce that, final int offset) {
      Iterator i = this.iterator().drop(offset);
      Iterator j = that.iterator();

      while(j.hasNext() && i.hasNext()) {
         if (!BoxesRunTime.equals(i.next(), j.next())) {
            return false;
         }
      }

      return !j.hasNext();
   }

   // $FF: synthetic method
   static int startsWith$default$2$(final SeqOps $this) {
      return $this.startsWith$default$2();
   }

   default int startsWith$default$2() {
      return 0;
   }

   // $FF: synthetic method
   static boolean endsWith$(final SeqOps $this, final Iterable that) {
      return $this.endsWith(that);
   }

   default boolean endsWith(final Iterable that) {
      if (that.isEmpty()) {
         return true;
      } else {
         Iterator i = this.iterator().drop(this.length() - that.size());
         Iterator j = that.iterator();

         while(i.hasNext() && j.hasNext()) {
            if (!BoxesRunTime.equals(i.next(), j.next())) {
               return false;
            }
         }

         return !j.hasNext();
      }
   }

   // $FF: synthetic method
   static boolean isDefinedAt$(final SeqOps $this, final int idx) {
      return $this.isDefinedAt(idx);
   }

   default boolean isDefinedAt(final int idx) {
      if (idx >= 0) {
         IterableOps.SizeCompareOps$ var10000 = IterableOps.SizeCompareOps$.MODULE$;
         if (this.lengthCompare(idx) > 0) {
            return true;
         }
      }

      return false;
   }

   // $FF: synthetic method
   static Object padTo$(final SeqOps $this, final int len, final Object elem) {
      return $this.padTo(len, elem);
   }

   default Object padTo(final int len, final Object elem) {
      return this.iterableFactory().from(new View.PadTo(this, len, elem));
   }

   // $FF: synthetic method
   static int segmentLength$(final SeqOps $this, final Function1 p) {
      return $this.segmentLength(p);
   }

   default int segmentLength(final Function1 p) {
      return this.segmentLength(p, 0);
   }

   // $FF: synthetic method
   static int segmentLength$(final SeqOps $this, final Function1 p, final int from) {
      return $this.segmentLength(p, from);
   }

   default int segmentLength(final Function1 p, final int from) {
      int i = 0;

      for(Iterator it = this.iterator().drop(from); it.hasNext() && BoxesRunTime.unboxToBoolean(p.apply(it.next())); ++i) {
      }

      return i;
   }

   // $FF: synthetic method
   static int prefixLength$(final SeqOps $this, final Function1 p) {
      return $this.prefixLength(p);
   }

   /** @deprecated */
   default int prefixLength(final Function1 p) {
      return this.segmentLength(p, 0);
   }

   // $FF: synthetic method
   static int indexWhere$(final SeqOps $this, final Function1 p, final int from) {
      return $this.indexWhere(p, from);
   }

   default int indexWhere(final Function1 p, final int from) {
      return this.iterator().indexWhere(p, from);
   }

   // $FF: synthetic method
   static int indexWhere$(final SeqOps $this, final Function1 p) {
      return $this.indexWhere(p);
   }

   default int indexWhere(final Function1 p) {
      return this.indexWhere(p, 0);
   }

   // $FF: synthetic method
   static int indexOf$(final SeqOps $this, final Object elem, final int from) {
      return $this.indexOf(elem, from);
   }

   default int indexOf(final Object elem, final int from) {
      return this.indexWhere((x$1) -> BoxesRunTime.boxToBoolean($anonfun$indexOf$1(elem, x$1)), from);
   }

   // $FF: synthetic method
   static int indexOf$(final SeqOps $this, final Object elem) {
      return $this.indexOf(elem);
   }

   default int indexOf(final Object elem) {
      return this.indexOf(elem, 0);
   }

   // $FF: synthetic method
   static int lastIndexOf$(final SeqOps $this, final Object elem, final int end) {
      return $this.lastIndexOf(elem, end);
   }

   default int lastIndexOf(final Object elem, final int end) {
      return this.lastIndexWhere((x$2) -> BoxesRunTime.boxToBoolean($anonfun$lastIndexOf$1(elem, x$2)), end);
   }

   // $FF: synthetic method
   static int lastIndexOf$default$2$(final SeqOps $this) {
      return $this.lastIndexOf$default$2();
   }

   default int lastIndexOf$default$2() {
      return this.length() - 1;
   }

   // $FF: synthetic method
   static int lastIndexWhere$(final SeqOps $this, final Function1 p, final int end) {
      return $this.lastIndexWhere(p, end);
   }

   default int lastIndexWhere(final Function1 p, final int end) {
      int i = this.length() - 1;

      for(Iterator it = this.reverseIterator(); it.hasNext(); --i) {
         Object elem = it.next();
         if (i <= end && BoxesRunTime.unboxToBoolean(p.apply(elem))) {
            break;
         }
      }

      return i;
   }

   // $FF: synthetic method
   static int lastIndexWhere$(final SeqOps $this, final Function1 p) {
      return $this.lastIndexWhere(p);
   }

   default int lastIndexWhere(final Function1 p) {
      return this.lastIndexWhere(p, Integer.MAX_VALUE);
   }

   // $FF: synthetic method
   static Seq scala$collection$SeqOps$$toGenericSeq$(final SeqOps $this) {
      return $this.scala$collection$SeqOps$$toGenericSeq();
   }

   default Seq scala$collection$SeqOps$$toGenericSeq() {
      return (Seq)(this instanceof Seq ? (Seq)this : this.toSeq());
   }

   // $FF: synthetic method
   static int indexOfSlice$(final SeqOps $this, final Seq that, final int from) {
      return $this.indexOfSlice(that, from);
   }

   default int indexOfSlice(final Seq that, final int from) {
      if (that.isEmpty() && from == 0) {
         return 0;
      } else {
         int l = this.knownSize();
         int tl = that.knownSize();
         if (l >= 0 && tl >= 0) {
            scala.math.package$ var10000 = scala.math.package$.MODULE$;
            int clippedFrom = Math.max(0, from);
            if (from > l) {
               return -1;
            } else if (tl < 1) {
               return clippedFrom;
            } else {
               return l < tl ? -1 : SeqOps$.MODULE$.scala$collection$SeqOps$$kmpSearch((Seq)(this instanceof Seq ? (Seq)this : this.toSeq()), clippedFrom, l, that, 0, tl, true);
            }
         } else {
            int i = from;

            for(Seq s = (Seq)((Seq)(this instanceof Seq ? (Seq)this : this.toSeq())).drop(from); !s.isEmpty(); s = (Seq)s.tail()) {
               int x$2 = s.startsWith$default$2();
               if (s.startsWith(that, x$2)) {
                  return i;
               }

               ++i;
            }

            return -1;
         }
      }
   }

   // $FF: synthetic method
   static int indexOfSlice$(final SeqOps $this, final Seq that) {
      return $this.indexOfSlice(that);
   }

   default int indexOfSlice(final Seq that) {
      return this.indexOfSlice(that, 0);
   }

   // $FF: synthetic method
   static int lastIndexOfSlice$(final SeqOps $this, final Seq that, final int end) {
      return $this.lastIndexOfSlice(that, end);
   }

   default int lastIndexOfSlice(final Seq that, final int end) {
      int l = this.length();
      int tl = that.length();
      scala.math.package$ var10000 = scala.math.package$.MODULE$;
      int clippedL = Math.min(l - tl, end);
      if (end < 0) {
         return -1;
      } else if (tl < 1) {
         return clippedL;
      } else {
         return l < tl ? -1 : SeqOps$.MODULE$.scala$collection$SeqOps$$kmpSearch((Seq)(this instanceof Seq ? (Seq)this : this.toSeq()), 0, clippedL + tl, that, 0, tl, false);
      }
   }

   // $FF: synthetic method
   static int lastIndexOfSlice$(final SeqOps $this, final Seq that) {
      return $this.lastIndexOfSlice(that);
   }

   default int lastIndexOfSlice(final Seq that) {
      return this.lastIndexOfSlice(that, Integer.MAX_VALUE);
   }

   // $FF: synthetic method
   static Option findLast$(final SeqOps $this, final Function1 p) {
      return $this.findLast(p);
   }

   default Option findLast(final Function1 p) {
      Iterator it = this.reverseIterator();

      while(it.hasNext()) {
         Object elem = it.next();
         if (BoxesRunTime.unboxToBoolean(p.apply(elem))) {
            return new Some(elem);
         }
      }

      return None$.MODULE$;
   }

   // $FF: synthetic method
   static boolean containsSlice$(final SeqOps $this, final Seq that) {
      return $this.containsSlice(that);
   }

   default boolean containsSlice(final Seq that) {
      return this.indexOfSlice(that) != -1;
   }

   // $FF: synthetic method
   static boolean contains$(final SeqOps $this, final Object elem) {
      return $this.contains(elem);
   }

   default boolean contains(final Object elem) {
      return this.exists((x$3) -> BoxesRunTime.boxToBoolean($anonfun$contains$1(elem, x$3)));
   }

   // $FF: synthetic method
   static Object reverseMap$(final SeqOps $this, final Function1 f) {
      return $this.reverseMap(f);
   }

   /** @deprecated */
   default Object reverseMap(final Function1 f) {
      IterableFactory var10000 = this.iterableFactory();
      View$ var10003 = View$.MODULE$;
      Function0 fromIteratorProvider_it = () -> this.reverseIterator();
      AbstractView var4 = new AbstractView(fromIteratorProvider_it) {
         private final Function0 it$1;

         public Iterator iterator() {
            return (Iterator)this.it$1.apply();
         }

         public {
            this.it$1 = it$1;
         }
      };
      fromIteratorProvider_it = null;
      return var10000.from(new View.Map(var4, f));
   }

   // $FF: synthetic method
   static Iterator permutations$(final SeqOps $this) {
      return $this.permutations();
   }

   default Iterator permutations() {
      if (this.isEmpty()) {
         Iterator$ var10000 = Iterator$.MODULE$;
         Object single_a = this.coll();
         return new AbstractIterator(single_a) {
            private boolean consumed;
            private final Object a$1;

            public boolean hasNext() {
               return !this.consumed;
            }

            public Object next() {
               if (this.consumed) {
                  Iterator$ var10000 = Iterator$.MODULE$;
                  return Iterator$.scala$collection$Iterator$$_empty.next();
               } else {
                  this.consumed = true;
                  return this.a$1;
               }
            }

            public Iterator sliceIterator(final int from, final int until) {
               if (!this.consumed && from <= 0 && until != 0) {
                  return this;
               } else {
                  Iterator$ var10000 = Iterator$.MODULE$;
                  return Iterator$.scala$collection$Iterator$$_empty;
               }
            }

            public {
               this.a$1 = a$1;
               this.consumed = false;
            }
         };
      } else {
         return new PermutationsItr();
      }
   }

   // $FF: synthetic method
   static Iterator combinations$(final SeqOps $this, final int n) {
      return $this.combinations(n);
   }

   default Iterator combinations(final int n) {
      if (n >= 0 && n <= this.length()) {
         return new CombinationsItr(n);
      } else {
         Iterator$ var10000 = Iterator$.MODULE$;
         return Iterator$.scala$collection$Iterator$$_empty;
      }
   }

   // $FF: synthetic method
   static Object sorted$(final SeqOps $this, final Ordering ord) {
      return $this.sorted(ord);
   }

   default Object sorted(final Ordering ord) {
      int len = this.length();
      Builder b = this.newSpecificBuilder();
      if (len == 1) {
         Object $plus$eq_elem = this.head();
         if (b == null) {
            throw null;
         }

         b.addOne($plus$eq_elem);
         $plus$eq_elem = null;
      } else if (len > 1) {
         b.sizeHint(len);
         Object[] arr = new Object[len];
         this.copyToArray(arr);
         Arrays.sort(arr, ord);

         for(int i = 0; i < len; ++i) {
            Object $plus$eq_elem = arr[i];
            b.addOne($plus$eq_elem);
            $plus$eq_elem = null;
         }
      }

      return b.result();
   }

   // $FF: synthetic method
   static Object sortWith$(final SeqOps $this, final Function2 lt) {
      return $this.sortWith(lt);
   }

   default Object sortWith(final Function2 lt) {
      if (scala.package$.MODULE$.Ordering() == null) {
         throw null;
      } else {
         return this.sorted(new Ordering(lt) {
            private final Function2 cmp$2;

            public Some tryCompare(final Object x, final Object y) {
               return Ordering.tryCompare$(this, x, y);
            }

            public boolean equiv(final Object x, final Object y) {
               return Ordering.equiv$(this, x, y);
            }

            public Object max(final Object x, final Object y) {
               return Ordering.max$(this, x, y);
            }

            public Object min(final Object x, final Object y) {
               return Ordering.min$(this, x, y);
            }

            public Ordering reverse() {
               return Ordering.reverse$(this);
            }

            public boolean isReverseOf(final Ordering other) {
               return Ordering.isReverseOf$(this, other);
            }

            public Ordering on(final Function1 f) {
               return Ordering.on$(this, f);
            }

            public Ordering orElse(final Ordering other) {
               return Ordering.orElse$(this, other);
            }

            public Ordering orElseBy(final Function1 f, final Ordering ord) {
               return Ordering.orElseBy$(this, f, ord);
            }

            public Ordering.OrderingOps mkOrderingOps(final Object lhs) {
               return Ordering.mkOrderingOps$(this, lhs);
            }

            public int compare(final Object x, final Object y) {
               if (BoxesRunTime.unboxToBoolean(this.cmp$2.apply(x, y))) {
                  return -1;
               } else {
                  return BoxesRunTime.unboxToBoolean(this.cmp$2.apply(y, x)) ? 1 : 0;
               }
            }

            public boolean lt(final Object x, final Object y) {
               return BoxesRunTime.unboxToBoolean(this.cmp$2.apply(x, y));
            }

            public boolean gt(final Object x, final Object y) {
               return BoxesRunTime.unboxToBoolean(this.cmp$2.apply(y, x));
            }

            public boolean gteq(final Object x, final Object y) {
               return !BoxesRunTime.unboxToBoolean(this.cmp$2.apply(x, y));
            }

            public boolean lteq(final Object x, final Object y) {
               return !BoxesRunTime.unboxToBoolean(this.cmp$2.apply(y, x));
            }

            public {
               this.cmp$2 = cmp$2;
            }
         });
      }
   }

   // $FF: synthetic method
   static Object sortBy$(final SeqOps $this, final Function1 f, final Ordering ord) {
      return $this.sortBy(f, ord);
   }

   default Object sortBy(final Function1 f, final Ordering ord) {
      return this.sorted(ord.on(f));
   }

   // $FF: synthetic method
   static Range indices$(final SeqOps $this) {
      return $this.indices();
   }

   default Range indices() {
      Range$ var10000 = Range$.MODULE$;
      int apply_end = this.length();
      int apply_start = 0;
      return new Range.Exclusive(apply_start, apply_end, 1);
   }

   // $FF: synthetic method
   static int sizeCompare$(final SeqOps $this, final int otherSize) {
      return $this.sizeCompare(otherSize);
   }

   default int sizeCompare(final int otherSize) {
      return this.lengthCompare(otherSize);
   }

   // $FF: synthetic method
   static int lengthCompare$(final SeqOps $this, final int len) {
      return $this.lengthCompare(len);
   }

   default int lengthCompare(final int len) {
      return this.scala$collection$SeqOps$$super$sizeCompare(len);
   }

   // $FF: synthetic method
   static int sizeCompare$(final SeqOps $this, final Iterable that) {
      return $this.sizeCompare(that);
   }

   default int sizeCompare(final Iterable that) {
      return this.lengthCompare(that);
   }

   // $FF: synthetic method
   static int lengthCompare$(final SeqOps $this, final Iterable that) {
      return $this.lengthCompare(that);
   }

   default int lengthCompare(final Iterable that) {
      return this.scala$collection$SeqOps$$super$sizeCompare(that);
   }

   // $FF: synthetic method
   static IterableOps lengthIs$(final SeqOps $this) {
      return $this.lengthIs();
   }

   default IterableOps lengthIs() {
      return this;
   }

   // $FF: synthetic method
   static boolean isEmpty$(final SeqOps $this) {
      return $this.isEmpty();
   }

   default boolean isEmpty() {
      return this.lengthCompare(0) == 0;
   }

   // $FF: synthetic method
   static boolean sameElements$(final SeqOps $this, final IterableOnce that) {
      return $this.sameElements(that);
   }

   default boolean sameElements(final IterableOnce that) {
      int thisKnownSize = this.knownSize();
      boolean var10000;
      if (thisKnownSize != -1) {
         int thatKnownSize = that.knownSize();
         if (thatKnownSize != -1 && thisKnownSize != thatKnownSize) {
            var10000 = true;
            return !var10000 && this.iterator().sameElements(that);
         }
      }

      var10000 = false;
      return !var10000 && this.iterator().sameElements(that);
   }

   // $FF: synthetic method
   static boolean corresponds$(final SeqOps $this, final Seq that, final Function2 p) {
      return $this.corresponds(that, p);
   }

   default boolean corresponds(final Seq that, final Function2 p) {
      Iterator i = this.iterator();
      Iterator j = that.iterator();

      while(i.hasNext() && j.hasNext()) {
         if (!BoxesRunTime.unboxToBoolean(p.apply(i.next(), j.next()))) {
            return false;
         }
      }

      return !i.hasNext() && !j.hasNext();
   }

   // $FF: synthetic method
   static Object diff$(final SeqOps $this, final Seq that) {
      return $this.diff(that);
   }

   default Object diff(final Seq that) {
      scala.collection.mutable.Map occ = this.occCounts(that);
      return this.fromSpecific(this.iterator().filter((x) -> BoxesRunTime.boxToBoolean($anonfun$diff$1(occ, x))));
   }

   // $FF: synthetic method
   static Object intersect$(final SeqOps $this, final Seq that) {
      return $this.intersect(that);
   }

   default Object intersect(final Seq that) {
      scala.collection.mutable.Map occ = this.occCounts(that);
      return this.fromSpecific(this.iterator().filter((x) -> BoxesRunTime.boxToBoolean($anonfun$intersect$1(occ, x))));
   }

   // $FF: synthetic method
   static Object patch$(final SeqOps $this, final int from, final IterableOnce other, final int replaced) {
      return $this.patch(from, other, replaced);
   }

   default Object patch(final int from, final IterableOnce other, final int replaced) {
      return this.iterableFactory().from(new View.Patched(this, from, other, replaced));
   }

   // $FF: synthetic method
   static Object updated$(final SeqOps $this, final int index, final Object elem) {
      return $this.updated(index, elem);
   }

   default Object updated(final int index, final Object elem) {
      if (index < 0) {
         throw new IndexOutOfBoundsException(Integer.toString(index));
      } else {
         int k = this.knownSize();
         if (k >= 0 && index >= k) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
         } else {
            return this.iterableFactory().from(new View.Updated(this, index, elem));
         }
      }
   }

   // $FF: synthetic method
   static scala.collection.mutable.Map occCounts$(final SeqOps $this, final Seq sq) {
      return $this.occCounts(sq);
   }

   default scala.collection.mutable.Map occCounts(final Seq sq) {
      HashMap occ = new HashMap();
      sq.foreach((y) -> occ.updateWith(y, (x0$1) -> {
            if (None$.MODULE$.equals(x0$1)) {
               return new Some(1);
            } else if (x0$1 instanceof Some) {
               int n = BoxesRunTime.unboxToInt(((Some)x0$1).value());
               return new Some(n + 1);
            } else {
               throw new MatchError(x0$1);
            }
         }));
      return occ;
   }

   // $FF: synthetic method
   static Searching.SearchResult search$(final SeqOps $this, final Object elem, final Ordering ord) {
      return $this.search(elem, ord);
   }

   default Searching.SearchResult search(final Object elem, final Ordering ord) {
      return this.linearSearch(this.view(), elem, 0, ord);
   }

   // $FF: synthetic method
   static Searching.SearchResult search$(final SeqOps $this, final Object elem, final int from, final int to, final Ordering ord) {
      return $this.search(elem, from, to, ord);
   }

   default Searching.SearchResult search(final Object elem, final int from, final int to, final Ordering ord) {
      View var10001 = (View)this.view().slice(from, to);
      scala.math.package$ var10003 = scala.math.package$.MODULE$;
      return this.linearSearch(var10001, elem, Math.max(0, from), ord);
   }

   private Searching.SearchResult linearSearch(final View c, final Object elem, final int offset, final Ordering ord) {
      int idx = offset;

      for(Iterator it = c.iterator(); it.hasNext(); ++idx) {
         Object cur = it.next();
         if (ord.equiv(elem, cur)) {
            return new Searching.Found(idx);
         }

         if (ord.lt(elem, cur)) {
            return new Searching.InsertionPoint(idx);
         }
      }

      return new Searching.InsertionPoint(idx);
   }

   // $FF: synthetic method
   static boolean $anonfun$indexOf$1(final Object elem$1, final Object x$1) {
      return BoxesRunTime.equals(elem$1, x$1);
   }

   // $FF: synthetic method
   static boolean $anonfun$lastIndexOf$1(final Object elem$2, final Object x$2) {
      return BoxesRunTime.equals(elem$2, x$2);
   }

   // $FF: synthetic method
   static boolean $anonfun$contains$1(final Object elem$3, final Object x$3) {
      return BoxesRunTime.equals(x$3, elem$3);
   }

   // $FF: synthetic method
   static boolean $anonfun$diff$1(final scala.collection.mutable.Map occ$1, final Object x) {
      boolean create_e = false;
      BooleanRef include = new BooleanRef(create_e);
      occ$1.updateWith(x, (x0$1) -> {
         boolean var2 = false;
         Some var3 = null;
         if (None$.MODULE$.equals(x0$1)) {
            include.elem = true;
            return None$.MODULE$;
         } else {
            if (x0$1 instanceof Some) {
               var2 = true;
               var3 = (Some)x0$1;
               int var4 = BoxesRunTime.unboxToInt(var3.value());
               if (1 == var4) {
                  return None$.MODULE$;
               }
            }

            if (var2) {
               int n = BoxesRunTime.unboxToInt(var3.value());
               return new Some(n - 1);
            } else {
               throw new MatchError(x0$1);
            }
         }
      });
      return include.elem;
   }

   // $FF: synthetic method
   static boolean $anonfun$intersect$1(final scala.collection.mutable.Map occ$2, final Object x) {
      boolean create_e = true;
      BooleanRef include = new BooleanRef(create_e);
      occ$2.updateWith(x, (x0$1) -> {
         boolean var2 = false;
         Some var3 = null;
         if (None$.MODULE$.equals(x0$1)) {
            include.elem = false;
            return None$.MODULE$;
         } else {
            if (x0$1 instanceof Some) {
               var2 = true;
               var3 = (Some)x0$1;
               int var4 = BoxesRunTime.unboxToInt(var3.value());
               if (1 == var4) {
                  return None$.MODULE$;
               }
            }

            if (var2) {
               int n = BoxesRunTime.unboxToInt(var3.value());
               return new Some(n - 1);
            } else {
               throw new MatchError(x0$1);
            }
         }
      });
      return include.elem;
   }

   static void $init$(final SeqOps $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private class PermutationsItr extends AbstractIterator {
      // $FF: synthetic field
      private final Tuple2 x$4;
      private final ArrayBuffer elms;
      private final int[] idxs;
      private boolean _hasNext;
      // $FF: synthetic field
      public final SeqOps $outer;

      public boolean hasNext() {
         return this._hasNext;
      }

      public Object next() throws NoSuchElementException {
         if (!this.hasNext()) {
            Iterator$ var10000 = Iterator$.MODULE$;
            Iterator$.scala$collection$Iterator$$_empty.next();
         }

         ArrayBuffer var7 = new ArrayBuffer;
         ArrayBuffer var10002 = this.elms;
         if (var10002 == null) {
            throw null;
         } else {
            var7.<init>(var10002.length());
            ArrayBuffer forcedElms = (ArrayBuffer)var7.addAll(this.elms);
            Builder var8 = this.scala$collection$SeqOps$PermutationsItr$$$outer().newSpecificBuilder();
            if (var8 == null) {
               throw null;
            } else {
               Object result = ((Builder)var8.addAll(forcedElms)).result();

               int i;
               for(i = this.idxs.length - 2; i >= 0 && this.idxs[i] >= this.idxs[i + 1]; --i) {
               }

               if (i < 0) {
                  this._hasNext = false;
               } else {
                  int j;
                  for(j = this.idxs.length - 1; this.idxs[j] <= this.idxs[i]; --j) {
                  }

                  this.swap(i, j);
                  int len = (this.idxs.length - i) / 2;

                  for(int k = 1; k <= len; ++k) {
                     this.swap(i + k, this.idxs.length - k);
                  }
               }

               return result;
            }
         }
      }

      private void swap(final int i, final int j) {
         int tmpI = this.idxs[i];
         this.idxs[i] = this.idxs[j];
         this.idxs[j] = tmpI;
         Object tmpE = this.elms.apply(i);
         this.elms.update(i, this.elms.apply(j));
         this.elms.update(j, tmpE);
      }

      private Tuple2 init() {
         Nil$ apply_elems = Nil$.MODULE$;
         HashMap$ apply_this = HashMap$.MODULE$;
         HashMap var10000 = apply_this.from(apply_elems);
         Object var10 = null;
         apply_elems = null;
         HashMap m = var10000;
         SeqOps var13 = this.scala$collection$SeqOps$PermutationsItr$$$outer();
         if (var13 == null) {
            throw null;
         } else {
            SeqOps scala$collection$SeqOps$$toGenericSeq_this = var13;
            var13 = (SeqOps)(scala$collection$SeqOps$$toGenericSeq_this instanceof Seq ? (Seq)scala$collection$SeqOps$$toGenericSeq_this : scala$collection$SeqOps$$toGenericSeq_this.toSeq());
            Object var9 = null;
            Tuple2 var2 = ((IterableOps)((SeqOps)var13.map((e) -> new Tuple2(e, m.getOrElseUpdate(e, () -> m.size())))).sortBy((x$5) -> BoxesRunTime.boxToInteger($anonfun$init$3(x$5)), Ordering.Int$.MODULE$)).unzip($less$colon$less$.MODULE$.refl());
            if (var2 != null) {
               Seq es = (Seq)var2._1();
               Seq is = (Seq)var2._2();
               IterableFactory$ var10003 = IterableFactory$.MODULE$;
               ArrayBuffer$ toFactory_factory = ArrayBuffer$.MODULE$;
               IterableFactory.ToFactory var15 = new IterableFactory.ToFactory(toFactory_factory);
               toFactory_factory = null;
               return new Tuple2(es.to(var15), is.toArray(ClassTag$.MODULE$.Int()));
            } else {
               throw new MatchError((Object)null);
            }
         }
      }

      // $FF: synthetic method
      public SeqOps scala$collection$SeqOps$PermutationsItr$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final int $anonfun$init$3(final Tuple2 x$5) {
         return x$5._2$mcI$sp();
      }

      public PermutationsItr() {
         if (SeqOps.this == null) {
            throw null;
         } else {
            this.$outer = SeqOps.this;
            super();
            Tuple2 var2 = this.init();
            if (var2 != null) {
               ArrayBuffer elms = (ArrayBuffer)var2._1();
               int[] idxs = (int[])var2._2();
               this.x$4 = new Tuple2(elms, idxs);
               this.elms = (ArrayBuffer)this.x$4._1();
               this.idxs = (int[])this.x$4._2();
               this._hasNext = true;
            } else {
               throw new MatchError((Object)null);
            }
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   private class CombinationsItr extends AbstractIterator {
      private final int n;
      // $FF: synthetic field
      private final Tuple3 x$7;
      private final IndexedSeq elms;
      private final int[] cnts;
      private final int[] nums;
      private final int[] offs;
      private boolean _hasNext;
      // $FF: synthetic field
      public final SeqOps $outer;

      public boolean hasNext() {
         return this._hasNext;
      }

      public Object next() {
         if (!this.hasNext()) {
            Iterator$ var10000 = Iterator$.MODULE$;
            Iterator$.scala$collection$Iterator$$_empty.next();
         }

         Builder buf = this.scala$collection$SeqOps$CombinationsItr$$$outer().newSpecificBuilder();
         RichInt$ var21 = RichInt$.MODULE$;
         byte var5 = 0;
         int until$extension_end = this.nums.length;
         Range$ var22 = Range$.MODULE$;
         Range foreach$mVc$sp_this = new Range.Exclusive(var5, until$extension_end, 1);
         if (!foreach$mVc$sp_this.isEmpty()) {
            int foreach$mVc$sp_i = foreach$mVc$sp_this.start();

            while(true) {
               $anonfun$next$1(this, buf, foreach$mVc$sp_i);
               if (foreach$mVc$sp_i == foreach$mVc$sp_this.scala$collection$immutable$Range$$lastElement) {
                  break;
               }

               foreach$mVc$sp_i += foreach$mVc$sp_this.step();
            }
         }

         foreach$mVc$sp_this = null;
         Object res = buf.result();

         int idx;
         for(idx = this.nums.length - 1; idx >= 0 && this.nums[idx] == this.cnts[idx]; --idx) {
         }

         int lastIndexWhere$extension_end = idx - 1;
         Object lastIndexWhere$extension_$this = this.nums;
         int lastIndexWhere$extension_i = Math.min(lastIndexWhere$extension_end, ((Object[])lastIndexWhere$extension_$this).length - 1);

         while(true) {
            if (lastIndexWhere$extension_i < 0) {
               var23 = -1;
               break;
            }

            if ($anonfun$next$3((int)((Object[])lastIndexWhere$extension_$this)[lastIndexWhere$extension_i])) {
               var23 = lastIndexWhere$extension_i;
               break;
            }

            --lastIndexWhere$extension_i;
         }

         lastIndexWhere$extension_$this = null;
         idx = var23;
         if (idx < 0) {
            this._hasNext = false;
         } else {
            int var17 = 1;

            for(int i = idx + 1; i < this.nums.length; ++i) {
               var17 += this.nums[i];
            }

            int var10002 = this.nums[idx]--;
            RichInt$ var24 = RichInt$.MODULE$;
            int var6 = idx + 1;
            int until$extension_end = this.nums.length;
            Range$ var25 = Range$.MODULE$;
            Range foreach$mVc$sp_this = new Range.Exclusive(var6, until$extension_end, 1);
            if (!foreach$mVc$sp_this.isEmpty()) {
               int foreach$mVc$sp_i = foreach$mVc$sp_this.start();

               while(true) {
                  RichInt$ var26 = RichInt$.MODULE$;
                  int $anonfun$next$4_min$extension_that = this.cnts[foreach$mVc$sp_i];
                  scala.math.package$ var27 = scala.math.package$.MODULE$;
                  this.nums[foreach$mVc$sp_i] = Math.min(var17, $anonfun$next$4_min$extension_that);
                  var17 -= this.nums[foreach$mVc$sp_i];
                  if (foreach$mVc$sp_i == foreach$mVc$sp_this.scala$collection$immutable$Range$$lastElement) {
                     break;
                  }

                  foreach$mVc$sp_i += foreach$mVc$sp_this.step();
               }
            }
         }

         return res;
      }

      private Tuple3 init() {
         Nil$ apply_elems = Nil$.MODULE$;
         HashMap$ apply_this = HashMap$.MODULE$;
         HashMap var10000 = apply_this.from(apply_elems);
         Object var18 = null;
         apply_elems = null;
         HashMap m = var10000;
         SeqOps var22 = this.scala$collection$SeqOps$CombinationsItr$$$outer();
         if (var22 == null) {
            throw null;
         } else {
            SeqOps scala$collection$SeqOps$$toGenericSeq_this = var22;
            var22 = (SeqOps)(scala$collection$SeqOps$$toGenericSeq_this instanceof Seq ? (Seq)scala$collection$SeqOps$$toGenericSeq_this : scala$collection$SeqOps$$toGenericSeq_this.toSeq());
            Object var17 = null;
            Tuple2 var2 = ((IterableOps)((SeqOps)var22.map((e) -> new Tuple2(e, m.getOrElseUpdate(e, () -> m.size())))).sortBy((x$11) -> BoxesRunTime.boxToInteger($anonfun$init$6(x$11)), Ordering.Int$.MODULE$)).unzip($less$colon$less$.MODULE$.refl());
            if (var2 == null) {
               throw new MatchError((Object)null);
            } else {
               Seq es = (Seq)var2._1();
               Seq is = (Seq)var2._2();
               int[] cs = new int[m.size()];
               is.foreach((i) -> {
                  int var10002 = cs[i]++;
               });
               int[] ns = new int[cs.length];
               int var16 = this.n;
               RichInt$ var24 = RichInt$.MODULE$;
               byte var8 = 0;
               int until$extension_end = ns.length;
               Range$ var25 = Range$.MODULE$;
               Range foreach$mVc$sp_this = new Range.Exclusive(var8, until$extension_end, 1);
               if (!foreach$mVc$sp_this.isEmpty()) {
                  int foreach$mVc$sp_i = foreach$mVc$sp_this.start();

                  while(true) {
                     RichInt$ var10002 = RichInt$.MODULE$;
                     int $anonfun$init$8_min$extension_that = cs[foreach$mVc$sp_i];
                     scala.math.package$ var26 = scala.math.package$.MODULE$;
                     ns[foreach$mVc$sp_i] = Math.min(var16, $anonfun$init$8_min$extension_that);
                     var16 -= ns[foreach$mVc$sp_i];
                     if (foreach$mVc$sp_i == foreach$mVc$sp_this.scala$collection$immutable$Range$$lastElement) {
                        break;
                     }

                     foreach$mVc$sp_i += foreach$mVc$sp_this.step();
                  }
               }

               foreach$mVc$sp_this = null;
               IterableFactory$ var10003 = IterableFactory$.MODULE$;
               IterableFactory toFactory_factory = IndexedSeq$.MODULE$;
               IterableFactory.ToFactory var27 = new IterableFactory.ToFactory(toFactory_factory);
               toFactory_factory = null;
               return new Tuple3(es.to(var27), cs, ns);
            }
         }
      }

      // $FF: synthetic method
      public SeqOps scala$collection$SeqOps$CombinationsItr$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final int $anonfun$offs$1(final int x$8, final int x$9) {
         return x$8 + x$9;
      }

      // $FF: synthetic method
      public static final Builder $anonfun$next$2(final CombinationsItr $this, final Builder buf$1, final int k$1, final int j) {
         Object $plus$eq_elem = $this.elms.apply($this.offs[k$1] + j);
         if (buf$1 == null) {
            throw null;
         } else {
            return (Builder)buf$1.addOne($plus$eq_elem);
         }
      }

      // $FF: synthetic method
      public static final void $anonfun$next$1(final CombinationsItr $this, final Builder buf$1, final int k) {
         RichInt$ var10000 = RichInt$.MODULE$;
         byte var3 = 0;
         int until$extension_end = $this.nums[k];
         Range$ var7 = Range$.MODULE$;
         Range foreach_this = new Range.Exclusive(var3, until$extension_end, 1);
         if (!foreach_this.isEmpty()) {
            int foreach_i = foreach_this.start();

            while(true) {
               $anonfun$next$2($this, buf$1, k, foreach_i);
               if (foreach_i == foreach_this.scala$collection$immutable$Range$$lastElement) {
                  break;
               }

               foreach_i += foreach_this.step();
            }
         }

      }

      // $FF: synthetic method
      public static final boolean $anonfun$next$3(final int x$10) {
         return x$10 > 0;
      }

      // $FF: synthetic method
      public static final void $anonfun$next$4(final CombinationsItr $this, final IntRef sum$1, final int k) {
         RichInt$ var10002 = RichInt$.MODULE$;
         int var3 = sum$1.elem;
         int min$extension_that = $this.cnts[k];
         scala.math.package$ var5 = scala.math.package$.MODULE$;
         $this.nums[k] = Math.min(var3, min$extension_that);
         sum$1.elem -= $this.nums[k];
      }

      // $FF: synthetic method
      public static final int $anonfun$init$6(final Tuple2 x$11) {
         return x$11._2$mcI$sp();
      }

      // $FF: synthetic method
      public static final void $anonfun$init$8(final int[] ns$1, final IntRef r$1, final int[] cs$1, final int k) {
         RichInt$ var10002 = RichInt$.MODULE$;
         int var4 = r$1.elem;
         int min$extension_that = cs$1[k];
         scala.math.package$ var6 = scala.math.package$.MODULE$;
         ns$1[k] = Math.min(var4, min$extension_that);
         r$1.elem -= ns$1[k];
      }

      public CombinationsItr(final int n) {
         this.n = n;
         if (SeqOps.this == null) {
            throw null;
         } else {
            this.$outer = SeqOps.this;
            super();
            Tuple3 var3 = this.init();
            if (var3 == null) {
               throw new MatchError((Object)null);
            } else {
               IndexedSeq elms = (IndexedSeq)var3._1();
               int[] cnts = (int[])var3._2();
               int[] nums = (int[])var3._3();
               this.x$7 = new Tuple3(elms, cnts, nums);
               this.elms = (IndexedSeq)this.x$7._1();
               this.cnts = (int[])this.x$7._2();
               this.nums = (int[])this.x$7._3();
               int scanLeft$extension_z = 0;
               int[] scanLeft$extension_$this = this.cnts;
               int scanLeft$extension_v = scanLeft$extension_z;
               int scanLeft$extension_i = 0;

               int[] scanLeft$extension_res;
               for(scanLeft$extension_res = new int[scanLeft$extension_$this.length + 1]; scanLeft$extension_i < scanLeft$extension_$this.length; ++scanLeft$extension_i) {
                  scanLeft$extension_res[scanLeft$extension_i] = scanLeft$extension_v;
                  scanLeft$extension_v += scanLeft$extension_$this[scanLeft$extension_i];
               }

               scanLeft$extension_res[scanLeft$extension_i] = scanLeft$extension_v;
               this.offs = scanLeft$extension_res;
               this._hasNext = true;
            }
         }
      }

      // $FF: synthetic method
      public static final Builder $anonfun$next$2$adapted(final CombinationsItr $this, final Builder buf$1, final int k$1, final Object j) {
         return $anonfun$next$2($this, buf$1, k$1, BoxesRunTime.unboxToInt(j));
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
