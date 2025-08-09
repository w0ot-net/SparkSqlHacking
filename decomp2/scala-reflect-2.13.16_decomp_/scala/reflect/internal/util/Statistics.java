package scala.reflect.internal.util;

import java.lang.invoke.SerializedLambda;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.immutable.;
import scala.collection.immutable.List;
import scala.collection.immutable.Range;
import scala.collection.immutable.Seq;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.ListBuffer;
import scala.math.Ordered;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.Reporter;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.settings.MutableSettings;
import scala.reflect.internal.settings.MutableSettings$;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.LongRef;
import scala.runtime.RichInt;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011ee\u0001CA\u0001\u0003\u0007\t\t!!\u0006\t\u0015\u0005}\u0001A!b\u0001\n\u0003\t\t\u0003\u0003\u0006\u0002,\u0001\u0011\t\u0011)A\u0005\u0003GAa\"!\f\u0001\t\u0003\u0005)Q!A!\u0002\u0013\ty\u0003C\u0004\u0002<\u0001!\t!!\u0010\u0006\r\u0005\u001d\u0003\u0001AA%\u0011\u001d\t)\u0006\u0001C\u0003\u0003/Bq!!\u0016\u0001\t\u000b\u0011y\u0002C\u0004\u0002V\u0001!)A!\u000b\t\u000f\t5\u0005\u0001\"\u0002\u0003\u0010\"9!1\u0018\u0001\u0005\u0006\tu\u0006b\u0002Bc\u0001\u0011\u0015!q\u0019\u0005\b\u0007S\u0001AQAB\u0016\u0011\u001d\u0019\u0019\u0004\u0001C\u0003\u0007kAqa!&\u0001\t\u000b\u00199\nC\u0004\u0004 \u0002!\ta!)\t\u000f\r5\u0006\u0001\"\u0001\u00040\"91q\u0017\u0001\u0005\u0002\re\u0006bBB`\u0001\u0011\u00051\u0011\u0019\u0005\b\u0007\u000f\u0004A\u0011ABe\u0011\u001d\u0019y\r\u0001C\u0001\u0007#Dqaa6\u0001\t\u0003\u0019I\u000eC\u0004\u0004v\u0002!\taa>\t\u000f\u0011]\u0001\u0001\"\u0001\u0005\u001a!9A1\t\u0001\u0005\u0002\r\u0005\u0003b\u0002C#\u0001\u0011\u0005Aq\t\u0005\b\t\u001f\u0002A\u0011\u0002C)\r%\ti\u0007\u0001I\u0001\u0004\u0003\ty\u0007C\u0004\u0002rm!\t!a\u001d\t\u0013\u0005U4D1A\u0007\u0002\u0005]\u0004\"CAH7\t\u0007i\u0011AAI\u0011\u001d\t\tk\u0007C\u0001\u0003GCq!!*\u001c\t\u0003\t9\u000bC\u0004\u00024n!\t!a\u001e\t\u0013\u0005U6D1A\u0005\u0002\u0005]f!\u0003BP\u0001A\u0005\u0019\u0011\u0001BQ\u0011\u001d\t\th\tC\u0001\u0003gBq!!)$\r#\t\u0019K\u0002\u0004\u0002h\u0001\u0001\u0011\u0011\u000e\u0005\u000b\u0003k2#Q1A\u0005\u0002\u0005]\u0004BCAhM\t\u0005\t\u0015!\u0003\u0002z!Q\u0011q\u0012\u0014\u0003\u0006\u0004%\t!!%\t\u0015\u0005EgE!A!\u0002\u0013\t\u0019\nC\u0004\u0002<\u0019\"\t!a5\t\u0013\u0005eg\u00051A\u0005\u0002\u0005m\u0007\"CArM\u0001\u0007I\u0011AAs\u0011!\tYO\nQ!\n\u0005u\u0007bBAwM\u0011\u0005\u0011q\u001e\u0005\b\u0003k4C\u0011IA|\u0011\u001d\u0011\tA\nC!\u0005\u0007AqA!\u0002'\t\u0003\u00129A\u0002\u0004\u0004`\u0002\u00011\u0011\u001d\u0005\u000b\u0003k\u001a$Q1A\u0005\u0002\u0005]\u0004BCAhg\t\u0005\t\u0015!\u0003\u0002z!Q\u0011qR\u001a\u0003\u0006\u0004%\t!!%\t\u0015\u0005E7G!A!\u0002\u0013\t\u0019\n\u0003\u0006\u0004dN\u0012\t\u0011*A\u0005\u0007KDq!a\u000f4\t\u0003\u00199\u000fC\u0004\u0003\u0006M\"\tEa\u0002\u0007\r\u0011m\u0003\u0001\u0002C/\u0011-\t)h\u000fB\u0001B\u0003%\u0011\u0011P\u0014\t\u0015\u0005\u00056H!b\u0001\n\u0003\u0012\u0019\u000b\u0003\u0006\u0003&n\u0012\t\u0011)A\u0005\u0003GBq!a\u000f<\t\u0003!y\u0006C\u0004\u0003\u0006m\"\tEa\u0002\t\u001b\u0011\u001d4\b%A\u0002\u0002\u0003%I!a\u001e(\r\u0019\u0011I\n\u0001\u0001\u0003\u001c\"Y\u0011Q\u000f\"\u0003\u0002\u0003\u0006I!!\u001f(\u0011)\t\tK\u0011BC\u0002\u0013\u0005#1\u0015\u0005\u000b\u0005K\u0013%\u0011!Q\u0001\n\u0005\r\u0004bBA\u001e\u0005\u0012\u0005!q\u0015\u0005\b\u0005[\u0013E\u0011\u0001BX\u0011\u001d\u0011\tL\u0011C\u0001\u0005gCqA!\u0002C\t\u0003\u00129A\u0002\u0004\u0003R\u0002\u0001!1\u001b\u0005\u000b\u0003kR%Q1A\u0005\u0002\u0005]\u0004BCAh\u0015\n\u0005\t\u0015!\u0003\u0002z!Q\u0011q\u0012&\u0003\u0006\u0004%\t!!%\t\u0015\u0005E'J!A!\u0002\u0013\t\u0019\nC\u0004\u0002<)#\tA!6\t\u0011\tm'\n)A\u0005\u0005;D\u0001Ba<KA\u0003%!\u0011\u001f\u0005\f\u0007\u0007Q%\u0019!C\u0001\u0003\u0007\u0019)\u0001\u0003\u0005\u0004\u000e)\u0003\u000b\u0011BB\u0004\u0011-\u0019yA\u0013b\u0001\n\u0003\t\u0019a!\u0005\t\u0011\rM!\n)A\u0005\u0005;Dqa!\u0006K\t\u0003\u00199\u0002C\u0004\u0003.*#\ta!\u0007\t\u000f\tE&\n\"\u0001\u0004\u001c!91q\u0004&\u0005\u0012\r\u0005\u0002b\u0002B\u0003\u0015\u0012\u0005#q\u0001\u0004\u0007\u0007+\u0002\u0001aa\u0016\t\u0017\u0005U4L!A!\u0002\u0013\tIh\u0013\u0005\u000b\u0003C[&Q1A\u0005B\re\u0003B\u0003BS7\n\u0005\t\u0015!\u0003\u0003P\"9\u00111H.\u0005\u0002\rm\u0003bBB\u00107\u0012E3\u0011\r\u0004\u0007\u0007\u001f\u0002\u0001a!\u0015\t\u0017\u0005U\u0014M!A!\u0002\u0013\tIh\u0013\u0005\f\u0003C\u000b'\u0011!Q\u0001\n\t=W\fC\u0004\u0002<\u0005$\taa\u001a\t\u0013\r5\u0014\r1A\u0005\u0002\r]\u0001\"CB8C\u0002\u0007I\u0011AB9\u0011!\u0019)(\u0019Q!\n\u0005=\u0003bBAwC\u0012\u00051q\u000f\u0005\b\u0003k\fG\u0011IB>\u0011\u001d\u0011\t!\u0019C!\u0005\u0007AqA!\u0002b\t\u0003\u00129A\u0002\u0004\u00034\u0001\u0001!Q\u0007\u0005\u000b\u0003kb'Q1A\u0005\u0002\u0005]\u0004BCAhY\n\u0005\t\u0015!\u0003\u0002z!Q\u0011q\u00127\u0003\u0006\u0004%\t!!%\t\u0015\u0005EGN!A!\u0002\u0013\t\u0019\n\u0003\u0006\u0003V1\u0014\t\u0011*A\u0005\u0005/B!B!\u0018m\u0005\u0003\u0005\u000b1\u0002B0\u0011\u001d\tY\u0004\u001cC\u0001\u0005OBqA!\u001em\t\u0003\u00129\bC\u0004\u0003~1$\tEa \t\u000f\t\u0015A\u000e\"\u0011\u0003\u0004\u001a11Q\b\u0001\u0001\u0007\u007fAq!a\u000fx\t\u0003\u0019\t\u0005\u0003\u0005\u0004D]\u0004\u000b\u0015BB#\u0011\u001d\u0019yh\u001eC\u0001\u0007\u0003Cqaa\"x\t\u0003\u0019I\t\u0003\u0005\u0005j\u0001\u0001\u000b\u0011\u0002C6\u0011\u001d!i\u0007\u0001C\u0003\t_Bq\u0001b\u001d\u0001\t\u000b!)\bC\u0004\u0005\u0002\u0002!)\u0001b!\u0003\u0015M#\u0018\r^5ti&\u001c7O\u0003\u0003\u0002\u0006\u0005\u001d\u0011\u0001B;uS2TA!!\u0003\u0002\f\u0005A\u0011N\u001c;fe:\fGN\u0003\u0003\u0002\u000e\u0005=\u0011a\u0002:fM2,7\r\u001e\u0006\u0003\u0003#\tQa]2bY\u0006\u001c\u0001aE\u0002\u0001\u0003/\u0001B!!\u0007\u0002\u001c5\u0011\u0011qB\u0005\u0005\u0003;\tyA\u0001\u0004B]f\u0014VMZ\u0001\fgfl'm\u001c7UC\ndW-\u0006\u0002\u0002$A!\u0011QEA\u0014\u001b\t\t9!\u0003\u0003\u0002*\u0005\u001d!aC*z[\n|G\u000eV1cY\u0016\fAb]=nE>dG+\u00192mK\u0002\n\u0001g]2bY\u0006$#/\u001a4mK\u000e$H%\u001b8uKJt\u0017\r\u001c\u0013vi&dGe\u0015;bi&\u001cH/[2tI\u0011\u001aX\r\u001e;j]\u001e\u001c\b\u0003BA\u0019\u0003oi!!a\r\u000b\t\u0005U\u0012qA\u0001\tg\u0016$H/\u001b8hg&!\u0011\u0011HA\u001a\u0005=iU\u000f^1cY\u0016\u001cV\r\u001e;j]\u001e\u001c\u0018A\u0002\u001fj]&$h\b\u0006\u0004\u0002@\u0005\r\u0013Q\t\t\u0004\u0003\u0003\u0002QBAA\u0002\u0011\u001d\ty\u0002\u0002a\u0001\u0003GAq!!\u000e\u0005\u0001\u0004\tyCA\u0007US6,'o\u00158baNDw\u000e\u001e\t\t\u00033\tY%a\u0014\u0002P%!\u0011QJA\b\u0005\u0019!V\u000f\u001d7feA!\u0011\u0011DA)\u0013\u0011\t\u0019&a\u0004\u0003\t1{gnZ\u0001\u000bS:\u001c7i\\;oi\u0016\u0014H\u0003BA-\u0003?\u0002B!!\u0007\u0002\\%!\u0011QLA\b\u0005\u0011)f.\u001b;\t\u000f\u0005\u0005d\u00011\u0001\u0002d\u0005\t1\rE\u0002\u0002f\u0019j\u0011\u0001\u0001\u0002\b\u0007>,h\u000e^3s'\u001d1\u0013qCA6\u0003\u0013\u00042!!\u001a\u001c\u0005!\tV/\u00198uSRL8cA\u000e\u0002\u0018\u00051A%\u001b8ji\u0012\"\"!!\u0017\u0002\rA\u0014XMZ5y+\t\tI\b\u0005\u0003\u0002|\u0005%e\u0002BA?\u0003\u000b\u0003B!a \u0002\u00105\u0011\u0011\u0011\u0011\u0006\u0005\u0003\u0007\u000b\u0019\"\u0001\u0004=e>|GOP\u0005\u0005\u0003\u000f\u000by!\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0003\u0017\u000biI\u0001\u0004TiJLgn\u001a\u0006\u0005\u0003\u000f\u000by!\u0001\u0004qQ\u0006\u001cXm]\u000b\u0003\u0003'\u0003b!!&\u0002\u001c\u0006ed\u0002BA\r\u0003/KA!!'\u0002\u0010\u00059\u0001/Y2lC\u001e,\u0017\u0002BAO\u0003?\u00131aU3r\u0015\u0011\tI*a\u0004\u0002\u0015UtG-\u001a:ms&tw-\u0006\u0002\u0002l\u000511\u000f[8x\u0003R$B!!+\u00020B!\u0011\u0011DAV\u0013\u0011\ti+a\u0004\u0003\u000f\t{w\u000e\\3b]\"9\u0011\u0011\u0017\u0011A\u0002\u0005e\u0014!\u00029iCN,\u0017\u0001\u00027j]\u0016\f\u0001b\u00195jY\u0012\u0014XM\\\u000b\u0003\u0003s\u0003b!a/\u0002F\u0006-TBAA_\u0015\u0011\ty,!1\u0002\u000f5,H/\u00192mK*!\u00111YA\b\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003\u000f\fiL\u0001\u0006MSN$()\u001e4gKJ\u0004b!!&\u0002L\u0006\r\u0014\u0002BAg\u0003?\u0013qa\u0014:eKJ,G-A\u0004qe\u00164\u0017\u000e\u001f\u0011\u0002\u000fAD\u0017m]3tAQ1\u00111MAk\u0003/Dq!!\u001e,\u0001\u0004\tI\bC\u0004\u0002\u0010.\u0002\r!a%\u0002\u000bY\fG.^3\u0016\u0005\u0005u\u0007\u0003BA\r\u0003?LA!!9\u0002\u0010\t\u0019\u0011J\u001c;\u0002\u0013Y\fG.^3`I\u0015\fH\u0003BA-\u0003OD\u0011\"!;.\u0003\u0003\u0005\r!!8\u0002\u0007a$\u0013'\u0001\u0004wC2,X\rI\u0001\bG>l\u0007/\u0019:f)\u0011\ti.!=\t\u000f\u0005Mx\u00061\u0001\u0002d\u0005!A\u000f[1u\u0003\u0019)\u0017/^1mgR!\u0011\u0011VA}\u0011\u001d\t\u0019\u0010\ra\u0001\u0003w\u0004B!!\u0007\u0002~&!\u0011q`A\b\u0005\r\te._\u0001\tQ\u0006\u001c\bnQ8eKR\u0011\u0011Q\\\u0001\ti>\u001cFO]5oOR\u0011!\u0011\u0002\t\u0005\u0005\u0017\u0011)\"\u0004\u0002\u0003\u000e)!!q\u0002B\t\u0003\u0011a\u0017M\\4\u000b\u0005\tM\u0011\u0001\u00026bm\u0006LA!a#\u0003\u000e!\u001aaA!\u0007\u0011\t\u0005e!1D\u0005\u0005\u0005;\tyA\u0001\u0004j]2Lg.\u001a\u000b\u0007\u00033\u0012\tCa\t\t\u000f\u0005\u0005t\u00011\u0001\u0002d!9!QE\u0004A\u0002\u0005u\u0017!\u00023fYR\f\u0007fA\u0004\u0003\u001aU!!1\u0006BD)\u0019\tIF!\f\u0003\n\"9!q\u0006\u0005A\u0002\tE\u0012\u0001B2ueN\u0004r!!\u001am\u0005\u000b\u000b\u0019G\u0001\u0005Rk\u0006tG/T1q+\u0019\u00119Da\u0011\u0003RM)AN!\u000f\u0002lAA\u00111\u0018B\u001e\u0005\u007f\u0011y%\u0003\u0003\u0003>\u0005u&a\u0002%bg\"l\u0015\r\u001d\t\u0005\u0005\u0003\u0012\u0019\u0005\u0004\u0001\u0005\u000f\t\u0015CN1\u0001\u0003H\t\t1*\u0005\u0003\u0003J\u0005m\b\u0003BA\r\u0005\u0017JAA!\u0014\u0002\u0010\t9aj\u001c;iS:<\u0007\u0003\u0002B!\u0005#\"qAa\u0015m\u0005\u0004\u00119EA\u0001W\u0003%Ig.\u001b;WC2,X\r\u0005\u0004\u0002\u001a\te#qJ\u0005\u0005\u00057\nyA\u0001\u0005=Eft\u0017-\\3?\u0003\t)g\u000f\u0005\u0005\u0002\u001a\t\u0005$q\nB3\u0013\u0011\u0011\u0019'a\u0004\u0003\u0013\u0019+hn\u0019;j_:\f\u0004CBAK\u0003\u0017\u0014y\u0005\u0006\u0005\u0003j\t=$\u0011\u000fB:)\u0011\u0011YG!\u001c\u0011\u000f\u0005\u0015DNa\u0010\u0003P!9!QL:A\u0004\t}\u0003bBA;g\u0002\u0007\u0011\u0011\u0010\u0005\b\u0003\u001f\u001b\b\u0019AAJ\u0011!\u0011)f\u001dCA\u0002\t]\u0013a\u00023fM\u0006,H\u000e\u001e\u000b\u0005\u0005\u001f\u0012I\bC\u0004\u0003|Q\u0004\rAa\u0010\u0002\u0007-,\u00170A\u0003baBd\u0017\u0010\u0006\u0003\u0003P\t\u0005\u0005b\u0002B>k\u0002\u0007!q\b\u000b\u0003\u0003s\u0002BA!\u0011\u0003\b\u00129!Q\t\u0005C\u0002\t\u001d\u0003b\u0002B>\u0011\u0001\u0007!Q\u0011\u0015\u0004\u0011\te\u0011\u0001D:uCJ$8i\\;oi\u0016\u0014H\u0003\u0002BI\u0005'\u0003\u0002\"!\u0007\u0002L\u0005u\u0017Q\u001c\u0005\b\u0005+K\u0001\u0019\u0001BL\u0003\t\u00198\rE\u0002\u0002f\t\u0013!bU;c\u0007>,h\u000e^3s'\u0015\u0011\u00151\rBO!\r\t)g\t\u0002\f'V\u0014\u0017+^1oi&$\u0018pE\u0003$\u0003/\tY'\u0006\u0002\u0002d\u0005YQO\u001c3fe2L\u0018N\\4!)\u0019\u00119J!+\u0003,\"9\u0011Q\u000f$A\u0002\u0005e\u0004bBAQ\r\u0002\u0007\u00111M\u0001\u0006gR\f'\u000f\u001e\u000b\u0003\u0005#\u000bAa\u001d;paR!\u0011\u0011\fB[\u0011\u001d\u00119\f\u0013a\u0001\u0005#\u000bA\u0001\u001d:fm\"\u001a\u0011B!\u0007\u0002\u0017M$x\u000e]\"pk:$XM\u001d\u000b\u0007\u00033\u0012yL!1\t\u000f\tU%\u00021\u0001\u0003\u0018\"9!Q\u0016\u0006A\u0002\tE\u0005f\u0001\u0006\u0003\u001a\u0005Q1\u000f^1siRKW.\u001a:\u0015\t\t%'1\u001a\t\u0004\u0003K*\u0001b\u0002Bg\u0017\u0001\u0007!qZ\u0001\u0003i6\u00042!!\u001aK\u0005\u0015!\u0016.\\3s'\u0015Q\u0015qCA6)\u0019\u0011yMa6\u0003Z\"9\u0011QO(A\u0002\u0005e\u0004bBAH\u001f\u0002\u0007\u00111S\u0001\ri>$\u0018\r\u001c+ie\u0016\fGm\u001d\t\u0005\u0005?\u0014Y/\u0004\u0002\u0003b*!!1\u001dBs\u0003\u0019\tGo\\7jG*!!q\u001dBu\u0003)\u0019wN\\2veJ,g\u000e\u001e\u0006\u0005\u0003\u000b\u0011\t\"\u0003\u0003\u0003n\n\u0005(!D!u_6L7-\u00138uK\u001e,'/A\u0006uQJ,\u0017\r\u001a(b]>\u001c\bC\u0002B\u0006\u0005g\u001490\u0003\u0003\u0003v\n5!a\u0003+ie\u0016\fG\rT8dC2\u0004BA!?\u0003\u00006\u0011!1 \u0006\u0005\u0005{\fy!A\u0004sk:$\u0018.\\3\n\t\r\u0005!1 \u0002\b\u0019>twMU3g\u0003)!x\u000e^1m\u001d\u0006twn]\u000b\u0003\u0007\u000f\u0001BAa8\u0004\n%!11\u0002Bq\u0005)\tEo\\7jG2{gnZ\u0001\fi>$\u0018\r\u001c(b]>\u001c\b%A\u0004uS6LgnZ:\u0016\u0005\tu\u0017\u0001\u0003;j[&twm\u001d\u0011\u0002\u000b9\fgn\\:\u0016\u0005\u0005=CC\u0001Be)\u0011\tIf!\b\t\u000f\t]\u0006\f1\u0001\u0003J\u0006!1\u000f[8x)\u0011\u0011Iaa\t\t\u000f\r\u0015\u0012\f1\u0001\u0002P\u0005\u0011an\u001d\u0015\u0004\u0017\te\u0011!C:u_B$\u0016.\\3s)\u0019\tIf!\f\u00040!9!Q\u001a\u0007A\u0002\t=\u0007b\u0002BW\u0019\u0001\u0007!\u0011\u001a\u0015\u0004\u0019\te\u0011!\u00039vg\"$\u0016.\\3s)\u0019\u0011Ima\u000e\u0004\u000e\"91\u0011H\u0007A\u0002\rm\u0012A\u0002;j[\u0016\u00148\u000fE\u0002\u0002f]\u0014!\u0002V5nKJ\u001cF/Y2l'\r9\u0018q\u0003\u000b\u0003\u0007w\tQ!\u001a7f[N\u0004b!!&\u0004H\r-\u0013\u0002BB%\u0003?\u0013A\u0001T5tiBA\u0011\u0011DA&\u0007\u001b\ny\u0005E\u0002\u0002f\u0005\u0014ab\u0015;bG.\f'\r\\3US6,'oE\u0003b\u0007'\u001a)\u0007E\u0002\u0002fm\u0013\u0001bU;c)&lWM]\n\u00067\n='QT\u000b\u0003\u0005\u001f$baa\u0015\u0004^\r}\u0003bBA;?\u0002\u0007\u0011\u0011\u0010\u0005\b\u0003C{\u0006\u0019\u0001Bh)\u0011\u0011Iaa\u0019\t\u000f\r\u0015\u0002\r1\u0001\u0002PA1\u0011QSAf\u0007\u001b\"ba!\u0014\u0004j\r-\u0004bBA;I\u0002\u0007\u0011\u0011\u0010\u0005\b\u0003C#\u0007\u0019\u0001Bh\u00035\u0019\b/Z2jM&\u001cg*\u00198pg\u0006\t2\u000f]3dS\u001aL7MT1o_N|F%Z9\u0015\t\u0005e31\u000f\u0005\n\u0003S4\u0017\u0011!a\u0001\u0003\u001f\nab\u001d9fG&4\u0017n\u0019(b]>\u001c\b\u0005\u0006\u0003\u0002^\u000ee\u0004bBAzQ\u0002\u00071Q\n\u000b\u0005\u0003S\u001bi\bC\u0004\u0002t&\u0004\r!a?\u0002\tA,8\u000f\u001b\u000b\u0005\u0005\u0013\u001c\u0019\tC\u0004\u0004\u0006j\u0004\ra!\u0014\u0002\u0003Q\f1\u0001]8q)\u0011\tIfa#\t\u000f\t]6\u00101\u0001\u0003J\"A1qR\u0007\u0005\u0002\u0004\u0019\t*A\u0003uS6,'\u000f\u0005\u0004\u0002\u001a\te3Q\n\u0015\u0004\u001b\te\u0011\u0001\u00039paRKW.\u001a:\u0015\r\u0005e3\u0011TBN\u0011\u001d\u0019ID\u0004a\u0001\u0007wAqAa.\u000f\u0001\u0004\u0011I\rK\u0002\u000f\u00053\t!B\\3x\u0007>,h\u000e^3s)\u0019\t\u0019ga)\u0004&\"9\u0011QO\bA\u0002\u0005e\u0004bBAH\u001f\u0001\u00071q\u0015\t\u0007\u00033\u0019I+!\u001f\n\t\r-\u0016q\u0002\u0002\u000byI,\u0007/Z1uK\u0012t\u0014!\u00048foJ+GnQ8v]R,'\u000f\u0006\u0004\u0002d\rE61\u0017\u0005\b\u0003k\u0002\u0002\u0019AA=\u0011\u001d\u0019)\f\u0005a\u0001\u0003G\n1a\u0019;s\u00035qWm^*vE\u000e{WO\u001c;feR1!qSB^\u0007{Cq!!\u001e\u0012\u0001\u0004\tI\bC\u0004\u00046F\u0001\r!a\u0019\u0002\u00119,w\u000fV5nKJ$bAa4\u0004D\u000e\u0015\u0007bBA;%\u0001\u0007\u0011\u0011\u0010\u0005\b\u0003\u001f\u0013\u0002\u0019ABT\u0003-qWm^*vERKW.\u001a:\u0015\r\t=71ZBg\u0011\u001d\t)h\u0005a\u0001\u0003sBqaa$\u0014\u0001\u0004\u0011y-A\toK^\u001cF/Y2lC\ndW\rV5nKJ$ba!\u0014\u0004T\u000eU\u0007bBA;)\u0001\u0007\u0011\u0011\u0010\u0005\b\u0007\u001f#\u0002\u0019\u0001Bh\u0003\u001dqWm\u001e,jK^$baa7\u0004r\u000eMH\u0003BBo\u0007_\u00042!!\u001a4\u0005\u00111\u0016.Z<\u0014\u000bM\n9\"a\u001b\u0002\u000bE,\u0018M\u001c;\u0011\r\u0005e!\u0011LA~)!\u0019in!;\u0004l\u000e5\bbBA;s\u0001\u0007\u0011\u0011\u0010\u0005\b\u0003\u001fK\u0004\u0019AAJ\u0011!\u0019\u0019/\u000fCA\u0002\r\u0015\b\u0002CBr+\u0011\u0005\ra!:\t\u000f\u0005UT\u00031\u0001\u0002z!9\u0011qR\u000bA\u0002\r\u001d\u0016a\u00038foF+\u0018M\u001c;NCB,ba!?\u0005\u0004\u0011\u001dACBB~\t'!)\u0002\u0006\u0003\u0004~\u0012=A\u0003BB\u0000\t\u0013\u0001r!!\u001am\t\u0003!)\u0001\u0005\u0003\u0003B\u0011\rAa\u0002B#-\t\u0007!q\t\t\u0005\u0005\u0003\"9\u0001B\u0004\u0003TY\u0011\rAa\u0012\t\u000f\tuc\u0003q\u0001\u0005\fAA\u0011\u0011\u0004B1\t\u000b!i\u0001\u0005\u0004\u0002\u0016\u0006-GQ\u0001\u0005\t\u0005+2B\u00111\u0001\u0005\u0012A1\u0011\u0011\u0004B-\t\u000bAq!!\u001e\u0017\u0001\u0004\tI\bC\u0004\u0002\u0010Z\u0001\raa*\u0002\u00159,wOQ=DY\u0006\u001c8/\u0006\u0003\u0005\u001c\u0011MBC\u0002C\u000f\t\u007f!\t\u0005\u0006\u0003\u0005 \u0011mB\u0003\u0002C\u0011\tk\u0001r!!\u001am\tG!\t\u0004\r\u0003\u0005&\u00115\u0002CBA>\tO!Y#\u0003\u0003\u0005*\u00055%!B\"mCN\u001c\b\u0003\u0002B!\t[!1\u0002b\f\u0018\u0003\u0003\u0005\tQ!\u0001\u0003H\t\u0019q\fJ\u0019\u0011\t\t\u0005C1\u0007\u0003\b\u0005':\"\u0019\u0001B$\u0011\u001d\u0011if\u0006a\u0002\to\u0001\u0002\"!\u0007\u0003b\u0011EB\u0011\b\t\u0007\u0003+\u000bY\r\"\r\t\u0011\tUs\u0003\"a\u0001\t{\u0001b!!\u0007\u0003Z\u0011E\u0002bBA;/\u0001\u0007\u0011\u0011\u0010\u0005\b\u0003\u001f;\u0002\u0019ABT\u00035qWm\u001e+j[\u0016\u00148\u000b^1dW\u0006i\u0011\r\u001c7Rk\u0006tG/\u001b;jKN,\"\u0001\"\u0013\u0011\r\u0005UE1JA6\u0013\u0011!i%a(\u0003\u0011%#XM]1cY\u0016\f1b\u001d5poB+'oY3oiR1\u0011\u0011\u0010C*\t/Bq\u0001\"\u0016\u001b\u0001\u0004\ty%A\u0001y\u0011\u001d!IF\u0007a\u0001\u0003\u001f\nAAY1tK\nQ!+\u001a7D_VtG/\u001a:\u0014\u000bm\n\u0019G!(\u0015\r\u0011\u0005D1\rC3!\r\t)g\u000f\u0005\b\u0003kz\u0004\u0019AA=\u0011\u001d\t\tk\u0010a\u0001\u0003G\nAb];qKJ$\u0003O]3gSb\f!!]:\u0011\u0011\u0005m&1HA=\u0003W\nq!\u001a8bE2,G-\u0006\u0002\u0002*\"\u001aQP!\u0007\u00021I,\u0007o\u001c:u'R\fG/[:uS\u000e\u001cxJ^3sQ\u0016\fG\r\u0006\u0003\u0002Z\u0011]\u0004b\u0002C=}\u0002\u0007A1P\u0001\te\u0016\u0004xN\u001d;feB!\u0011Q\u0005C?\u0013\u0011!y(a\u0002\u0003\u0011I+\u0007o\u001c:uKJ\fQ\u0001^5nK\u0012,B\u0001\"\"\u0005\fR!Aq\u0011CK)\u0011!I\tb$\u0011\t\t\u0005C1\u0012\u0003\b\t\u001b{(\u0019\u0001B$\u0005\u0005!\u0006\u0002\u0003CI\u007f\u0012\u0005\r\u0001b%\u0002\t\t|G-\u001f\t\u0007\u00033\u0011I\u0006\"#\t\u000f\r=u\u00101\u0001\u0003P\"\u001aqP!\u0007"
)
public abstract class Statistics {
   private final SymbolTable symbolTable;
   public final MutableSettings scala$reflect$internal$util$Statistics$$settings;
   public final HashMap scala$reflect$internal$util$Statistics$$qs;

   public SymbolTable symbolTable() {
      return this.symbolTable;
   }

   public final void incCounter(final Counter c) {
      MutableSettings.SettingsOps$ var10000 = MutableSettings.SettingsOps$.MODULE$;
      MutableSettings$ var6 = MutableSettings$.MODULE$;
      MutableSettings enabled_SettingsOps_settings = this.scala$reflect$internal$util$Statistics$$settings;
      MutableSettings var7 = enabled_SettingsOps_settings;
      enabled_SettingsOps_settings = null;
      MutableSettings enabled_areStatisticsEnabled$extension_$this = var7;
      boolean var8 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(enabled_areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
      enabled_areStatisticsEnabled$extension_$this = null;
      if (var8 && c != null) {
         c.value_$eq(c.value() + 1);
      }
   }

   public final void incCounter(final Counter c, final int delta) {
      MutableSettings.SettingsOps$ var10000 = MutableSettings.SettingsOps$.MODULE$;
      MutableSettings$ var7 = MutableSettings$.MODULE$;
      MutableSettings enabled_SettingsOps_settings = this.scala$reflect$internal$util$Statistics$$settings;
      MutableSettings var8 = enabled_SettingsOps_settings;
      enabled_SettingsOps_settings = null;
      MutableSettings enabled_areStatisticsEnabled$extension_$this = var8;
      boolean var9 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(enabled_areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
      enabled_areStatisticsEnabled$extension_$this = null;
      if (var9 && c != null) {
         c.value_$eq(c.value() + delta);
      }
   }

   public final void incCounter(final QuantMap ctrs, final Object key) {
      MutableSettings.SettingsOps$ var10000 = MutableSettings.SettingsOps$.MODULE$;
      MutableSettings$ var8 = MutableSettings$.MODULE$;
      MutableSettings enabled_SettingsOps_settings = this.scala$reflect$internal$util$Statistics$$settings;
      MutableSettings var9 = enabled_SettingsOps_settings;
      enabled_SettingsOps_settings = null;
      MutableSettings enabled_areStatisticsEnabled$extension_$this = var9;
      boolean var10 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(enabled_areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
      enabled_areStatisticsEnabled$extension_$this = null;
      if (var10 && ctrs != null) {
         Counter var3 = (Counter)ctrs.apply(key);
         var3.value_$eq(var3.value() + 1);
      }
   }

   public final Tuple2 startCounter(final SubCounter sc) {
      MutableSettings.SettingsOps$ var10000 = MutableSettings.SettingsOps$.MODULE$;
      MutableSettings$ var6 = MutableSettings$.MODULE$;
      MutableSettings enabled_SettingsOps_settings = this.scala$reflect$internal$util$Statistics$$settings;
      MutableSettings var7 = enabled_SettingsOps_settings;
      enabled_SettingsOps_settings = null;
      MutableSettings enabled_areStatisticsEnabled$extension_$this = var7;
      boolean var8 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(enabled_areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
      enabled_areStatisticsEnabled$extension_$this = null;
      return var8 && sc != null ? sc.start() : null;
   }

   public final void stopCounter(final SubCounter sc, final Tuple2 start) {
      MutableSettings.SettingsOps$ var10000 = MutableSettings.SettingsOps$.MODULE$;
      MutableSettings$ var7 = MutableSettings$.MODULE$;
      MutableSettings enabled_SettingsOps_settings = this.scala$reflect$internal$util$Statistics$$settings;
      MutableSettings var8 = enabled_SettingsOps_settings;
      enabled_SettingsOps_settings = null;
      MutableSettings enabled_areStatisticsEnabled$extension_$this = var8;
      boolean var9 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(enabled_areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
      enabled_areStatisticsEnabled$extension_$this = null;
      if (var9 && sc != null) {
         sc.stop(start);
      }
   }

   public final Tuple2 startTimer(final Timer tm) {
      MutableSettings.SettingsOps$ var10000 = MutableSettings.SettingsOps$.MODULE$;
      MutableSettings$ var6 = MutableSettings$.MODULE$;
      MutableSettings enabled_SettingsOps_settings = this.scala$reflect$internal$util$Statistics$$settings;
      MutableSettings var7 = enabled_SettingsOps_settings;
      enabled_SettingsOps_settings = null;
      MutableSettings enabled_areStatisticsEnabled$extension_$this = var7;
      boolean var8 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(enabled_areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
      enabled_areStatisticsEnabled$extension_$this = null;
      return var8 && tm != null ? tm.start() : null;
   }

   public final void stopTimer(final Timer tm, final Tuple2 start) {
      MutableSettings.SettingsOps$ var10000 = MutableSettings.SettingsOps$.MODULE$;
      MutableSettings$ var7 = MutableSettings$.MODULE$;
      MutableSettings enabled_SettingsOps_settings = this.scala$reflect$internal$util$Statistics$$settings;
      MutableSettings var8 = enabled_SettingsOps_settings;
      enabled_SettingsOps_settings = null;
      MutableSettings enabled_areStatisticsEnabled$extension_$this = var8;
      boolean var9 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(enabled_areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
      enabled_areStatisticsEnabled$extension_$this = null;
      if (var9 && tm != null) {
         tm.stop(start);
      }
   }

   public final Tuple2 pushTimer(final TimerStack timers, final Function0 timer) {
      MutableSettings.SettingsOps$ var10000 = MutableSettings.SettingsOps$.MODULE$;
      MutableSettings$ var7 = MutableSettings$.MODULE$;
      MutableSettings enabled_SettingsOps_settings = this.scala$reflect$internal$util$Statistics$$settings;
      MutableSettings var8 = enabled_SettingsOps_settings;
      enabled_SettingsOps_settings = null;
      MutableSettings enabled_areStatisticsEnabled$extension_$this = var8;
      boolean var9 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(enabled_areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
      enabled_areStatisticsEnabled$extension_$this = null;
      return var9 && timers != null ? timers.push((StackableTimer)timer.apply()) : null;
   }

   public final void popTimer(final TimerStack timers, final Tuple2 prev) {
      MutableSettings.SettingsOps$ var10000 = MutableSettings.SettingsOps$.MODULE$;
      MutableSettings$ var7 = MutableSettings$.MODULE$;
      MutableSettings enabled_SettingsOps_settings = this.scala$reflect$internal$util$Statistics$$settings;
      MutableSettings var8 = enabled_SettingsOps_settings;
      enabled_SettingsOps_settings = null;
      MutableSettings enabled_areStatisticsEnabled$extension_$this = var8;
      boolean var9 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(enabled_areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
      enabled_areStatisticsEnabled$extension_$this = null;
      if (var9 && timers != null) {
         timers.pop(prev);
      }
   }

   public Counter newCounter(final String prefix, final Seq phases) {
      return new Counter(prefix, phases);
   }

   public Counter newRelCounter(final String prefix, final Counter ctr) {
      return new RelCounter(prefix, ctr);
   }

   public SubCounter newSubCounter(final String prefix, final Counter ctr) {
      return new SubCounter(prefix, ctr);
   }

   public Timer newTimer(final String prefix, final Seq phases) {
      return new Timer(prefix, phases);
   }

   public Timer newSubTimer(final String prefix, final Timer timer) {
      return new SubTimer(prefix, timer);
   }

   public StackableTimer newStackableTimer(final String prefix, final Timer timer) {
      return new StackableTimer(prefix, timer);
   }

   public View newView(final String prefix, final Seq phases, final Function0 quant) {
      return new View(prefix, phases, quant);
   }

   public QuantMap newQuantMap(final String prefix, final Seq phases, final Function0 initValue, final Function1 ev) {
      return new QuantMap(prefix, phases, initValue, ev);
   }

   public QuantMap newByClass(final String prefix, final Seq phases, final Function0 initValue, final Function1 ev) {
      return new QuantMap(prefix, phases, initValue, ev);
   }

   public TimerStack newTimerStack() {
      return new TimerStack();
   }

   public Iterable allQuantities() {
      return (Iterable)this.scala$reflect$internal$util$Statistics$$qs.withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$allQuantities$1(check$ifrefutable$1))).withFilter((x$1) -> BoxesRunTime.boxToBoolean($anonfun$allQuantities$2(x$1))).flatMap((x$2) -> {
         if (x$2 != null) {
            Quantity q = (Quantity)x$2._2();
            List var10000 = q.children().toList();
            if (var10000 == null) {
               throw null;
            } else {
               List $colon$colon_this = var10000;
               .colon.colon var4 = new .colon.colon(q, $colon$colon_this);
               Object var3 = null;
               return (IterableOnce)((List)var4).withFilter((r) -> BoxesRunTime.boxToBoolean($anonfun$allQuantities$4(r))).map((r) -> r);
            }
         } else {
            throw new MatchError((Object)null);
         }
      });
   }

   public String scala$reflect$internal$util$Statistics$$showPercent(final long x, final long base) {
      return base == 0L ? "" : scala.collection.StringOps..MODULE$.format$extension(" (%2.1f%%)", scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{(double)x / (double)base * (double)100}));
   }

   public final boolean enabled() {
      MutableSettings.SettingsOps$ var10000 = MutableSettings.SettingsOps$.MODULE$;
      MutableSettings$ var4 = MutableSettings$.MODULE$;
      MutableSettings SettingsOps_settings = this.scala$reflect$internal$util$Statistics$$settings;
      MutableSettings var5 = SettingsOps_settings;
      SettingsOps_settings = null;
      MutableSettings areStatisticsEnabled$extension_$this = var5;
      return StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
   }

   public final void reportStatisticsOverhead(final Reporter reporter) {
      long start = System.nanoTime();
      long var13 = 0L;
      RichInt var10000 = scala.runtime.RichInt..MODULE$;
      byte var7 = 1;
      int to$extension_end = 10000;
      Range var16 = scala.collection.immutable.Range..MODULE$;
      Range foreach$mVc$sp_this = new Range.Inclusive(var7, to$extension_end, 1);
      if (!foreach$mVc$sp_this.isEmpty()) {
         int foreach$mVc$sp_i = foreach$mVc$sp_this.start();

         while(true) {
            long $anonfun$reportStatisticsOverhead$1_time = System.nanoTime();
            var13 += System.nanoTime() - $anonfun$reportStatisticsOverhead$1_time;
            if (foreach$mVc$sp_i == foreach$mVc$sp_this.scala$collection$immutable$Range$$lastElement) {
               break;
            }

            foreach$mVc$sp_i += foreach$mVc$sp_this.step();
         }
      }

      foreach$mVc$sp_this = null;
      long total2 = System.nanoTime() - start;
      String variation = (new StringBuilder(8)).append((double)var13 / (double)10000.0F).append("ns to ").append((double)total2 / (double)10000.0F).append("ns").toString();
      NoPosition$ var10001 = NoPosition$.MODULE$;
      String var10002 = (new StringBuilder(52)).append("Enabling statistics, measuring overhead = ").append(variation).append(" per timer").toString();
      if (reporter == null) {
         throw null;
      } else {
         reporter.echo(var10001, var10002, scala.collection.immutable.Nil..MODULE$);
      }
   }

   public final Object timed(final Timer timer, final Function0 body) {
      MutableSettings.SettingsOps$ var10000 = MutableSettings.SettingsOps$.MODULE$;
      MutableSettings$ var17 = MutableSettings$.MODULE$;
      MutableSettings startTimer_enabled_SettingsOps_settings = this.scala$reflect$internal$util$Statistics$$settings;
      MutableSettings var18 = startTimer_enabled_SettingsOps_settings;
      startTimer_enabled_SettingsOps_settings = null;
      MutableSettings startTimer_enabled_areStatisticsEnabled$extension_$this = var18;
      boolean var19 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(startTimer_enabled_areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
      startTimer_enabled_areStatisticsEnabled$extension_$this = null;
      Tuple2 start = var19 && timer != null ? timer.start() : null;

      try {
         var20 = body.apply();
      } finally {
         MutableSettings.SettingsOps$ var10001 = MutableSettings.SettingsOps$.MODULE$;
         MutableSettings$ var21 = MutableSettings$.MODULE$;
         MutableSettings stopTimer_enabled_SettingsOps_settings = this.scala$reflect$internal$util$Statistics$$settings;
         MutableSettings var22 = stopTimer_enabled_SettingsOps_settings;
         stopTimer_enabled_SettingsOps_settings = null;
         MutableSettings stopTimer_enabled_areStatisticsEnabled$extension_$this = var22;
         boolean var23 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(stopTimer_enabled_areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
         stopTimer_enabled_areStatisticsEnabled$extension_$this = null;
         if (var23 && timer != null) {
            timer.stop(start);
         }

      }

      return var20;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$allQuantities$1(final Tuple2 check$ifrefutable$1) {
      return check$ifrefutable$1 != null;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$allQuantities$2(final Tuple2 x$1) {
      if (x$1 != null) {
         Quantity q = (Quantity)x$1._2();
         Quantity var10000 = q.underlying();
         if (var10000 != null) {
            if (var10000.equals(q)) {
               return true;
            }
         }

         return false;
      } else {
         throw new MatchError((Object)null);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$allQuantities$4(final Quantity r) {
      return !r.prefix().isEmpty();
   }

   // $FF: synthetic method
   public static final void $anonfun$reportStatisticsOverhead$1(final LongRef total$1, final int i) {
      long time = System.nanoTime();
      total$1.elem += System.nanoTime() - time;
   }

   public Statistics(final SymbolTable symbolTable, final MutableSettings settings) {
      this.symbolTable = symbolTable;
      this.scala$reflect$internal$util$Statistics$$settings = settings;
      this.scala$reflect$internal$util$Statistics$$qs = new HashMap();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public interface Quantity {
      void scala$reflect$internal$util$Statistics$Quantity$_setter_$children_$eq(final ListBuffer x$1);

      String prefix();

      Seq phases();

      default Quantity underlying() {
         return this;
      }

      default boolean showAt(final String phase) {
         return this.phases().isEmpty() || this.phases().contains(phase);
      }

      default String line() {
         return scala.collection.StringOps..MODULE$.format$extension("%-30s: %s", scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{this.prefix(), this}));
      }

      ListBuffer children();

      // $FF: synthetic method
      Statistics scala$reflect$internal$util$Statistics$Quantity$$$outer();

      static void $init$(final Quantity $this) {
         if (!$this.prefix().isEmpty()) {
            String var2;
            StringBuilder var10000;
            label19: {
               var10000 = new StringBuilder(1);
               Quantity var10001 = $this.underlying();
               if (var10001 != null) {
                  if (var10001.equals($this)) {
                     var2 = "";
                     break label19;
                  }
               }

               var2 = $this.underlying().prefix();
            }

            String key = var10000.append(var2).append("/").append($this.prefix()).toString();
            $this.scala$reflect$internal$util$Statistics$Quantity$$$outer().scala$reflect$internal$util$Statistics$$qs.update(key, $this);
         }

         $this.scala$reflect$internal$util$Statistics$Quantity$_setter_$children_$eq(new ListBuffer());
      }
   }

   public interface SubQuantity extends Quantity {
      Quantity underlying();

      // $FF: synthetic method
      Statistics scala$reflect$internal$util$Statistics$SubQuantity$$$outer();

      static void $init$(final SubQuantity $this) {
         ListBuffer var10000 = $this.underlying().children();
         if (var10000 == null) {
            throw null;
         } else {
            var10000.addOne($this);
         }
      }
   }

   public class Counter implements Quantity, Ordered {
      private final String prefix;
      private final Seq phases;
      private int value;
      private ListBuffer children;
      // $FF: synthetic field
      public final Statistics $outer;

      public boolean $less(final Object that) {
         return Ordered.$less$(this, that);
      }

      public boolean $greater(final Object that) {
         return Ordered.$greater$(this, that);
      }

      public boolean $less$eq(final Object that) {
         return Ordered.$less$eq$(this, that);
      }

      public boolean $greater$eq(final Object that) {
         return Ordered.$greater$eq$(this, that);
      }

      public int compareTo(final Object that) {
         return Ordered.compareTo$(this, that);
      }

      public Quantity underlying() {
         return Statistics.Quantity.super.underlying();
      }

      public boolean showAt(final String phase) {
         return Statistics.Quantity.super.showAt(phase);
      }

      public String line() {
         return Statistics.Quantity.super.line();
      }

      public ListBuffer children() {
         return this.children;
      }

      public void scala$reflect$internal$util$Statistics$Quantity$_setter_$children_$eq(final ListBuffer x$1) {
         this.children = x$1;
      }

      public String prefix() {
         return this.prefix;
      }

      public Seq phases() {
         return this.phases;
      }

      public int value() {
         return this.value;
      }

      public void value_$eq(final int x$1) {
         this.value = x$1;
      }

      public int compare(final Counter that) {
         if (this.value() < that.value()) {
            return -1;
         } else {
            return this.value() > that.value() ? 1 : 0;
         }
      }

      public boolean equals(final Object that) {
         if (that instanceof Counter && ((Counter)that).scala$reflect$internal$util$Statistics$Counter$$$outer() == this.scala$reflect$internal$util$Statistics$Counter$$$outer()) {
            Counter var2 = (Counter)that;
            return this.compare(var2) == 0;
         } else {
            return false;
         }
      }

      public int hashCode() {
         return this.value();
      }

      public String toString() {
         return Integer.toString(this.value());
      }

      // $FF: synthetic method
      public Statistics scala$reflect$internal$util$Statistics$Counter$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public Statistics scala$reflect$internal$util$Statistics$Quantity$$$outer() {
         return this.scala$reflect$internal$util$Statistics$Counter$$$outer();
      }

      public Counter(final String prefix, final Seq phases) {
         this.prefix = prefix;
         this.phases = phases;
         if (Statistics.this == null) {
            throw null;
         } else {
            this.$outer = Statistics.this;
            super();
            Statistics.Quantity.$init$(this);
            this.value = 0;
            Statics.releaseFence();
         }
      }
   }

   public class View implements Quantity {
      private final String prefix;
      private final Seq phases;
      private final Function0 quant;
      private ListBuffer children;
      // $FF: synthetic field
      public final Statistics $outer;

      public Quantity underlying() {
         return Statistics.Quantity.super.underlying();
      }

      public boolean showAt(final String phase) {
         return Statistics.Quantity.super.showAt(phase);
      }

      public String line() {
         return Statistics.Quantity.super.line();
      }

      public ListBuffer children() {
         return this.children;
      }

      public void scala$reflect$internal$util$Statistics$Quantity$_setter_$children_$eq(final ListBuffer x$1) {
         this.children = x$1;
      }

      public String prefix() {
         return this.prefix;
      }

      public Seq phases() {
         return this.phases;
      }

      public String toString() {
         return this.quant.apply().toString();
      }

      // $FF: synthetic method
      public Statistics scala$reflect$internal$util$Statistics$View$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public Statistics scala$reflect$internal$util$Statistics$Quantity$$$outer() {
         return this.scala$reflect$internal$util$Statistics$View$$$outer();
      }

      public View(final String prefix, final Seq phases, final Function0 quant) {
         this.prefix = prefix;
         this.phases = phases;
         this.quant = quant;
         if (Statistics.this == null) {
            throw null;
         } else {
            this.$outer = Statistics.this;
            super();
            Statistics.Quantity.$init$(this);
            Statics.releaseFence();
         }
      }
   }

   private class RelCounter extends Counter implements SubQuantity {
      private final Counter underlying;

      // $FF: synthetic method
      private String super$prefix() {
         return super.prefix();
      }

      public Counter underlying() {
         return this.underlying;
      }

      public String toString() {
         if (this.value() == 0) {
            return "0";
         } else if (this.underlying().value() == 0) {
            throw new AssertionError((new StringBuilder(18)).append("assertion failed: ").append($anonfun$toString$1(this)).toString());
         } else {
            return scala.collection.StringOps..MODULE$.format$extension("%2.1f", scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{(float)this.value() / (float)this.underlying().value()}));
         }
      }

      // $FF: synthetic method
      public Statistics scala$reflect$internal$util$Statistics$RelCounter$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public Statistics scala$reflect$internal$util$Statistics$SubQuantity$$$outer() {
         return this.scala$reflect$internal$util$Statistics$RelCounter$$$outer();
      }

      // $FF: synthetic method
      public static final String $anonfun$toString$1(final RelCounter $this) {
         return (new StringBuilder(1)).append($this.super$prefix()).append("/").append($this.underlying().line()).toString();
      }

      public RelCounter(final String prefix, final Counter underlying) {
         super(prefix, underlying.phases());
         this.underlying = underlying;
         Statistics.SubQuantity.$init$(this);
      }
   }

   public class SubCounter extends Counter implements SubQuantity {
      private final Counter underlying;

      public Counter underlying() {
         return this.underlying;
      }

      public Tuple2 start() {
         return new Tuple2.mcII.sp(this.value(), this.underlying().value());
      }

      public void stop(final Tuple2 prev) {
         if (prev != null) {
            int value0 = prev._1$mcI$sp();
            int uvalue0 = prev._2$mcI$sp();
            this.value_$eq(value0 + this.underlying().value() - uvalue0);
         } else {
            throw new MatchError((Object)null);
         }
      }

      public String toString() {
         return (new StringBuilder(0)).append(this.value()).append(this.scala$reflect$internal$util$Statistics$SubCounter$$$outer().scala$reflect$internal$util$Statistics$$showPercent((long)this.value(), (long)this.underlying().value())).toString();
      }

      // $FF: synthetic method
      public Statistics scala$reflect$internal$util$Statistics$SubCounter$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public Statistics scala$reflect$internal$util$Statistics$SubQuantity$$$outer() {
         return this.scala$reflect$internal$util$Statistics$SubCounter$$$outer();
      }

      public SubCounter(final String prefix, final Counter underlying) {
         super(prefix, underlying.phases());
         this.underlying = underlying;
         Statistics.SubQuantity.$init$(this);
      }
   }

   public class Timer implements Quantity {
      private final String prefix;
      private final Seq phases;
      public final AtomicInteger scala$reflect$internal$util$Statistics$Timer$$totalThreads;
      private final ThreadLocal threadNanos;
      private final AtomicLong totalNanos;
      private final AtomicInteger timings;
      private ListBuffer children;
      // $FF: synthetic field
      public final Statistics $outer;

      public Quantity underlying() {
         return Statistics.Quantity.super.underlying();
      }

      public boolean showAt(final String phase) {
         return Statistics.Quantity.super.showAt(phase);
      }

      public String line() {
         return Statistics.Quantity.super.line();
      }

      public ListBuffer children() {
         return this.children;
      }

      public void scala$reflect$internal$util$Statistics$Quantity$_setter_$children_$eq(final ListBuffer x$1) {
         this.children = x$1;
      }

      public String prefix() {
         return this.prefix;
      }

      public Seq phases() {
         return this.phases;
      }

      public AtomicLong totalNanos() {
         return this.totalNanos;
      }

      public AtomicInteger timings() {
         return this.timings;
      }

      public long nanos() {
         return this.totalNanos().get();
      }

      public Tuple2 start() {
         return new Tuple2.mcJJ.sp(((LongRef)this.threadNanos.get()).elem, System.nanoTime());
      }

      public void stop(final Tuple2 prev) {
         if (prev != null) {
            long nanos0 = prev._1$mcJ$sp();
            long start = prev._2$mcJ$sp();
            long newThreadNanos = nanos0 + System.nanoTime() - start;
            LongRef threadNanosCount = (LongRef)this.threadNanos.get();
            long diff = newThreadNanos - threadNanosCount.elem;
            threadNanosCount.elem = newThreadNanos;
            this.totalNanos().addAndGet(diff);
            this.timings().incrementAndGet();
         } else {
            throw new MatchError((Object)null);
         }
      }

      public String show(final long ns) {
         return (new StringBuilder(2)).append((double)(ns / 1000L) / (double)1000.0F).append("ms").toString();
      }

      public String toString() {
         int threads = this.scala$reflect$internal$util$Statistics$Timer$$totalThreads.get();
         return (new StringBuilder(8)).append(this.timings()).append(" spans, ").append(threads > 1 ? (new StringBuilder(10)).append(threads).append(" threads, ").toString() : BoxedUnit.UNIT).append(this.show(this.totalNanos().get())).toString();
      }

      // $FF: synthetic method
      public Statistics scala$reflect$internal$util$Statistics$Timer$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public Statistics scala$reflect$internal$util$Statistics$Quantity$$$outer() {
         return this.scala$reflect$internal$util$Statistics$Timer$$$outer();
      }

      public Timer(final String prefix, final Seq phases) {
         this.prefix = prefix;
         this.phases = phases;
         if (Statistics.this == null) {
            throw null;
         } else {
            this.$outer = Statistics.this;
            super();
            Statistics.Quantity.$init$(this);
            this.scala$reflect$internal$util$Statistics$Timer$$totalThreads = new AtomicInteger();
            this.threadNanos = new ThreadLocal() {
               // $FF: synthetic field
               private final Timer $outer;

               public LongRef initialValue() {
                  this.$outer.scala$reflect$internal$util$Statistics$Timer$$totalThreads.incrementAndGet();
                  return new LongRef(0L);
               }

               public {
                  if (Timer.this == null) {
                     throw null;
                  } else {
                     this.$outer = Timer.this;
                  }
               }
            };
            this.totalNanos = new AtomicLong();
            this.timings = new AtomicInteger();
            Statics.releaseFence();
         }
      }
   }

   public class SubTimer extends Timer implements SubQuantity {
      private final Timer underlying;

      public Timer underlying() {
         return this.underlying;
      }

      public String show(final long ns) {
         return (new StringBuilder(0)).append(super.show(ns)).append(this.scala$reflect$internal$util$Statistics$SubTimer$$$outer().scala$reflect$internal$util$Statistics$$showPercent(ns, this.underlying().totalNanos().get())).toString();
      }

      // $FF: synthetic method
      public Statistics scala$reflect$internal$util$Statistics$SubTimer$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public Statistics scala$reflect$internal$util$Statistics$SubQuantity$$$outer() {
         return this.scala$reflect$internal$util$Statistics$SubTimer$$$outer();
      }

      public SubTimer(final String prefix, final Timer underlying) {
         super(prefix, underlying.phases());
         this.underlying = underlying;
         Statistics.SubQuantity.$init$(this);
      }
   }

   public class StackableTimer extends SubTimer implements Ordered {
      private long specificNanos = 0L;

      public boolean $less(final Object that) {
         return Ordered.$less$(this, that);
      }

      public boolean $greater(final Object that) {
         return Ordered.$greater$(this, that);
      }

      public boolean $less$eq(final Object that) {
         return Ordered.$less$eq$(this, that);
      }

      public boolean $greater$eq(final Object that) {
         return Ordered.$greater$eq$(this, that);
      }

      public int compareTo(final Object that) {
         return Ordered.compareTo$(this, that);
      }

      public long specificNanos() {
         return this.specificNanos;
      }

      public void specificNanos_$eq(final long x$1) {
         this.specificNanos = x$1;
      }

      public int compare(final StackableTimer that) {
         if (this.specificNanos() < that.specificNanos()) {
            return -1;
         } else {
            return this.specificNanos() > that.specificNanos() ? 1 : 0;
         }
      }

      public boolean equals(final Object that) {
         if (that instanceof StackableTimer && ((StackableTimer)that).scala$reflect$internal$util$Statistics$StackableTimer$$$outer() == this.scala$reflect$internal$util$Statistics$StackableTimer$$$outer()) {
            StackableTimer var2 = (StackableTimer)that;
            return this.compare(var2) == 0;
         } else {
            return false;
         }
      }

      public int hashCode() {
         return Statics.longHash(this.specificNanos());
      }

      public String toString() {
         return (new StringBuilder(21)).append(super.toString()).append(" aggregate, ").append(this.show(this.specificNanos())).append(" specific").toString();
      }

      // $FF: synthetic method
      public Statistics scala$reflect$internal$util$Statistics$StackableTimer$$$outer() {
         return this.$outer;
      }

      public StackableTimer(final String prefix, final Timer underlying) {
         super(prefix, underlying);
      }
   }

   public class QuantMap extends HashMap implements Quantity {
      private final String prefix;
      private final Seq phases;
      private final Function0 initValue;
      private final Function1 ev;
      private ListBuffer children;
      // $FF: synthetic field
      public final Statistics $outer;

      public Quantity underlying() {
         return Statistics.Quantity.super.underlying();
      }

      public boolean showAt(final String phase) {
         return Statistics.Quantity.super.showAt(phase);
      }

      public String line() {
         return Statistics.Quantity.super.line();
      }

      public ListBuffer children() {
         return this.children;
      }

      public void scala$reflect$internal$util$Statistics$Quantity$_setter_$children_$eq(final ListBuffer x$1) {
         this.children = x$1;
      }

      public String prefix() {
         return this.prefix;
      }

      public Seq phases() {
         return this.phases;
      }

      public Object default(final Object key) {
         Object elem = this.initValue.apply();
         this.update(key, elem);
         return elem;
      }

      public Object apply(final Object key) {
         return super.apply(key);
      }

      public String toString() {
         IterableOnceOps var10000 = (IterableOnceOps)((IterableOps)this.toSeq().sortWith((x$5, x$6) -> BoxesRunTime.boxToBoolean($anonfun$toString$2(this, x$5, x$6)))).map((x0$1) -> {
            if (x0$1 != null) {
               Object cls = x0$1._1();
               Object elem = x0$1._2();
               if (cls instanceof Class) {
                  Class var3 = (Class)cls;
                  return (new StringBuilder(2)).append(var3.toString().substring(var3.toString().lastIndexOf("$") + 1)).append(": ").append(elem).toString();
               }
            }

            if (x0$1 != null) {
               Object key = x0$1._1();
               Object elem = x0$1._2();
               return (new StringBuilder(2)).append(key).append(": ").append(elem).toString();
            } else {
               throw new MatchError((Object)null);
            }
         });
         String mkString_sep = ", ";
         if (var10000 == null) {
            throw null;
         } else {
            return var10000.mkString("", mkString_sep, "");
         }
      }

      // $FF: synthetic method
      public Statistics scala$reflect$internal$util$Statistics$QuantMap$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public Statistics scala$reflect$internal$util$Statistics$Quantity$$$outer() {
         return this.scala$reflect$internal$util$Statistics$QuantMap$$$outer();
      }

      // $FF: synthetic method
      public static final boolean $anonfun$toString$2(final QuantMap $this, final Tuple2 x$5, final Tuple2 x$6) {
         return ((Ordered)$this.ev.apply(x$5._2())).$greater(x$6._2());
      }

      public QuantMap(final String prefix, final Seq phases, final Function0 initValue, final Function1 ev) {
         this.prefix = prefix;
         this.phases = phases;
         this.initValue = initValue;
         this.ev = ev;
         if (Statistics.this == null) {
            throw null;
         } else {
            this.$outer = Statistics.this;
            super();
            Statistics.Quantity.$init$(this);
            Statics.releaseFence();
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class TimerStack {
      private List elems;
      // $FF: synthetic field
      public final Statistics $outer;

      public Tuple2 push(final StackableTimer t) {
         Long var4 = 0L;
         Tuple2 var2 = new Tuple2(t, var4);
         List var10001 = this.elems;
         if (var10001 == null) {
            throw null;
         } else {
            List $colon$colon_this = var10001;
            .colon.colon var6 = new .colon.colon(var2, $colon$colon_this);
            Object var5 = null;
            this.elems = var6;
            return t.start();
         }
      }

      public void pop(final Tuple2 prev) {
         if (prev == null) {
            throw new MatchError((Object)null);
         } else {
            long nanos0 = prev._1$mcJ$sp();
            long start = prev._2$mcJ$sp();
            long duration = System.nanoTime() - start;
            List var8 = this.elems;
            if (var8 instanceof .colon.colon) {
               .colon.colon var9 = (.colon.colon)var8;
               Tuple2 var10 = (Tuple2)var9.head();
               List rest = var9.next$access$1();
               if (var10 != null) {
                  Object var10001;
                  label35: {
                     StackableTimer topTimer = (StackableTimer)var10._1();
                     long var21 = var10._2$mcJ$sp();
                     topTimer.totalNanos().addAndGet(nanos0 + duration);
                     topTimer.specificNanos_$eq(topTimer.specificNanos() + (duration - var21));
                     topTimer.timings().incrementAndGet();
                     if (rest instanceof .colon.colon) {
                        .colon.colon var13 = (.colon.colon)rest;
                        Tuple2 var14 = (Tuple2)var13.head();
                        List elems1 = var13.next$access$1();
                        if (var14 != null) {
                           StackableTimer outerTimer = (StackableTimer)var14._1();
                           long outerNested = var14._2$mcJ$sp();
                           Long var20 = outerNested + duration;
                           Tuple2 var19 = new Tuple2(outerTimer, var20);
                           if (elems1 == null) {
                              throw null;
                           }

                           var10001 = new .colon.colon(var19, elems1);
                           break label35;
                        }
                     }

                     if (!scala.collection.immutable.Nil..MODULE$.equals(rest)) {
                        throw new MatchError(rest);
                     }

                     var10001 = scala.collection.immutable.Nil..MODULE$;
                  }

                  this.elems = (List)var10001;
                  return;
               }
            }

            throw new MatchError(var8);
         }
      }

      // $FF: synthetic method
      public Statistics scala$reflect$internal$util$Statistics$TimerStack$$$outer() {
         return this.$outer;
      }

      public TimerStack() {
         if (Statistics.this == null) {
            throw null;
         } else {
            this.$outer = Statistics.this;
            super();
            this.elems = scala.collection.immutable.Nil..MODULE$;
         }
      }
   }
}
