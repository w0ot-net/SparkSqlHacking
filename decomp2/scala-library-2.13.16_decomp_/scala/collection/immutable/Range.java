package scala.collection.immutable;

import java.io.Serializable;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.AbstractIterator;
import scala.collection.AnyStepper$;
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
import scala.collection.StringOps$;
import scala.collection.convert.impl.RangeStepper;
import scala.collection.generic.CommonErrors$;
import scala.collection.mutable.Builder;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.math.Integral;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.ScalaRunTime$;
import scala.util.hashing.MurmurHash3$;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015\u001dg\u0001CA\u0004\u0003\u0013\t\t#a\u0006\t\u0015\u0005M\u0003A!b\u0001\n\u0003\t)\u0006\u0003\u0006\u0002X\u0001\u0011\t\u0011)A\u0005\u0003CA!\"!\u0017\u0001\u0005\u000b\u0007I\u0011AA+\u0011)\tY\u0006\u0001B\u0001B\u0003%\u0011\u0011\u0005\u0005\u000b\u0003;\u0002!Q1A\u0005\u0002\u0005U\u0003BCA0\u0001\t\u0005\t\u0015!\u0003\u0002\"!9\u0011\u0011\r\u0001\u0005\u0002\u0005\r\u0004bBA7\u0001\u0011\u0015\u0013q\u000e\u0005\b\u0003o\u0002AQIA=\u0011!\ty\r\u0001Q\u0005\n\u0005E\u0007\u0002CAm\u0001\u0001&I!a7\t\u0011\u0005\r\b\u0001)C\u0005\u00037D\u0001\"!:\u0001A\u0013%\u0011\u0011\u001b\u0005\b\u0003O\u0004a\u0011AAn\u0011%\tI\u000f\u0001b\u0001\n\u000b\nY\u000e\u0003\u0005\u0002l\u0002\u0001\u000bQBAo\u0011!\ti\u000f\u0001Q\u0001\n\u0005\u0005\u0002bBAx\u0001\u0011\u0015\u0011Q\u000b\u0005\t\u0003c\u0004\u0001\u0015!\u0003\u0002\"!9\u00111\u001f\u0001\u0005F\u0005U\u0003bBA{\u0001\u0011\u0015\u0013Q\u000b\u0005\b\u0003o\u0004AQIA}\u0011\u001d\tY\u0010\u0001C#\u0003sDq!!@\u0001\t\u0003\ny\u0010C\u0004\u0003\u0016\u0001!)Ba\u0006\t\u0013\t\u0005\u0002!%A\u0005\u0016\t\r\u0002\"\u0003B\u001d\u0001E\u0005IQ\u0003B\u0012\u0011%\u0011Y\u0004AI\u0001\n+\u0011\u0019\u0003C\u0005\u0003>\u0001\t\n\u0011\"\u0006\u0003@!9!1\t\u0001\u0005\u0006\t\u0015\u0003\u0002\u0003B%\u0001\u0001&IAa\u0013\t\u0011\tM\u0003\u0001)C\u0005\u0005+B\u0001Ba\u001a\u0001A\u0013%!\u0011\u000e\u0005\b\u0005W\u0002AQ\u0001B7\u0011\u001d\u00119\u000b\u0001C#\u0005SCqAa2\u0001\t\u000b\u0012I\rC\u0005\u0003f\u0002\t\n\u0011\"\u0002\u0003h\"9!q\u001e\u0001\u0005F\tE\b\"CB\u0001\u0001E\u0005IQAB\u0002\u0011!\u0019Y\u0001\u0001Q\u0005\n\r5\u0001bBB\n\u0001\u0011\u00053Q\u0003\u0005\b\u0007O\u0001AQIB\u0015\u0011\u001d\u0019y\u0003\u0001C#\u0007cAqa!\u000e\u0001\t\u000b\u001a9\u0004C\u0004\u0004<\u0001!)e!\u0010\t\u0011\r\u0005\u0003\u0001)C\u0005\u0007\u0007Bqaa\u0013\u0001\t\u000b\u001ai\u0005C\u0004\u0004R\u0001!)ea\u0015\t\u000f\r]\u0003\u0001\"\u0012\u0004Z!911\r\u0001\u0005F\r\u0015\u0004bBB7\u0001\u0011\u00153q\u000e\u0005\t\u0007g\u0002\u0001\u0015\"\u0003\u0004v!A1\u0011\u0010\u0001!\n\u0013\u0019Y\bC\u0004\u0006L\u0001!)%!?\t\u000f\r}\u0006\u0001\"\u0002\u0002z\"9QQ\n\u0001\u0005\u0006\u0015=\u0003bBC'\u0001\u0011\u0015S1\u000b\u0005\b\u000b;\u0002AQIC0\u0011\u001d)\t\b\u0001C#\u000bgBq!b\"\u0001\t\u000b*I\tC\u0004\u0006\u0016\u0002!\t%b&\t\u000f\u0015m\u0005\u0001\"\u0011\u0006\u0018\"9QQ\u0014\u0001\u0005V\u0005U\u0003b\u0002C^\u0001\u0011\u0015Sq\u0014\u0005\b\to\u0003AQ\tC]\u0011\u001d!I\u000b\u0001C#\u000bKC\u0001\"b*\u0001A\u0013ES\u0011\u0016\u0005\b\u000bW\u0003A\u0011IA}\u0011\u001d)i\u000b\u0001C!\u000b_Cq!\".\u0001\t\u0003*9l\u0002\u0005\u0004\u0002\u0006%\u0001\u0012ABB\r!\t9!!\u0003\t\u0002\r\u0015\u0005bBA1\u0011\u0012\u000511\u0014\u0005\b\u0007;CE\u0011ABP\u0011\u001d\u0019i\n\u0013C\u0001\u0007SCqAa\u001bI\t\u0003\u0019\t\fC\u0004\u0003l!#\ta!/\t\u000f\r}\u0006\n\"\u0001\u0004B\"91q\u0018%\u0005\u0002\r\u001dhABBc\u0011\n\u00199\rC\u0006\u0002TA\u0013\t\u0011)A\u0005\u0003C\t\u0001bCA-!\n\u0005\t\u0015!\u0003\u0002\"\rA1\"!\u0018Q\u0005\u0003\u0005\u000b\u0011BA\u0011\u000b!9\u0011\u0011\r)\u0005\u0002\r%\u0007bBAt!\u0012\u0005\u00111\u001c\u0004\u0007\u0007[D%aa<\t\u0017\u0005McK!A!\u0002\u0013\t\t#\u0001\u0005\f\u000332&\u0011!Q\u0001\n\u0005\u00052\u0001C\u0006\u0002^Y\u0013\t\u0011)A\u0005\u0003C)\u0001bBA1-\u0012\u00051\u0011\u001f\u0005\b\u0003O4F\u0011AAn\u000f\u001d\u0019i\u0010\u0013E\u0001\u0007\u007f4q\u0001\"\u0001I\u0011\u0003!\u0019\u0001C\u0004\u0002bu#\t\u0001\"\u0002\t\u000f\t-T\f\"\u0001\u0005\b!91qX/\u0005\u0002\u0011}qa\u0002C\u0016\u0011\"\u0005AQ\u0006\u0004\b\u0003/D\u0005\u0012\u0001C\u0018\u0011\u001d\t\tG\u0019C\u0001\tcAqAa\u001bc\t\u0003!\u0019\u0004C\u0004\u0004@\n$\t\u0001\"\u0010\b\u000f\u0011\u001d\u0003\n#\u0001\u0005J\u00199A1\n%\t\u0002\u00115\u0003bBA1O\u0012\u0005Aq\n\u0005\n\t#:'\u0019!C\u0002\t'B\u0001\u0002b\u001ahA\u0003%AQ\u000b\u0005\b\u0005W:G\u0011\u0001C5\u0011\u001d\u0019yl\u001aC\u0001\to2a\u0001\"!I\u0005\u0011\r\u0005B\u0004CG[\u0012\u0005\tQ!BC\u0002\u0013%Aq\u0012\u0005\f\t7k'Q!A!\u0002\u0013!\t\nC\u0004\u0002b5$\t\u0001\"(\t\u000f\t\rS\u000e\"\u0001\u0005$\"9A\u0011V7\u0005B\u0011-\u0006\"\u0003C\\[\u0006\u0005I\u0011\tC]\u0011%!Y,\\A\u0001\n\u0003\"ilB\u0005\u0005D\"\u000b\t\u0011#\u0001\u0005F\u001aIA\u0011\u0011%\u0002\u0002#\u0005Aq\u0019\u0005\b\u0003C2H\u0011\u0001Ce\u0011\u001d!YM\u001eC\u0003\t\u001bDq\u0001b9w\t\u000b!)\u000fC\u0005\u0005vZ\f\t\u0011\"\u0002\u0005x\"IQq\u0001<\u0002\u0002\u0013\u0015Q\u0011B\u0004\b\u0005?D\u0005\u0012AC\u000f\r\u001d\t9\u0003\u0013E\u0001\u000b?Aq!!\u0019~\t\u0003)\t\u0003C\u0004\u0003lu$\t!b\t\t\u000f\r}V\u0010\"\u0001\u0006.!9Qq\u0007%\u0005\n\u0015e\u0002\"CC \u0011\u0006\u0005I\u0011BC!\u0005\u0015\u0011\u0016M\\4f\u0015\u0011\tY!!\u0004\u0002\u0013%lW.\u001e;bE2,'\u0002BA\b\u0003#\t!bY8mY\u0016\u001cG/[8o\u0015\t\t\u0019\"A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u001b\u0001\tI\"!\u000b\u00020\u0005]\u0012QHA#!\u0019\tY\"!\b\u0002\"5\u0011\u0011\u0011B\u0005\u0005\u0003?\tIAA\u0006BEN$(/Y2u'\u0016\f\b\u0003BA\u0012\u0003Ki!!!\u0005\n\t\u0005\u001d\u0012\u0011\u0003\u0002\u0004\u0013:$\bCBA\u000e\u0003W\t\t#\u0003\u0003\u0002.\u0005%!AC%oI\u0016DX\rZ*fcBQ\u00111DA\u0019\u0003C\t)$!\u000b\n\t\u0005M\u0012\u0011\u0002\u0002\u000e\u0013:$W\r_3e'\u0016\fx\n]:\u0011\t\u0005m\u00111\u0006\t\u000b\u00037\tI$!\t\u00026\u0005%\u0012\u0002BA\u001e\u0003\u0013\u0011Qc\u0015;sS\u000e$x\n\u001d;j[&TX\rZ*fc>\u00038\u000f\u0005\u0005\u0002@\u0005\u0005\u0013\u0011EA\u001b\u001b\t\ti!\u0003\u0003\u0002D\u00055!aF%uKJ\f'\r\\3GC\u000e$xN]=EK\u001a\fW\u000f\u001c;t!\u0011\t9%!\u0014\u000f\t\u0005\r\u0012\u0011J\u0005\u0005\u0003\u0017\n\t\"A\u0004qC\u000e\\\u0017mZ3\n\t\u0005=\u0013\u0011\u000b\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0005\u0003\u0017\n\t\"A\u0003ti\u0006\u0014H/\u0006\u0002\u0002\"\u000511\u000f^1si\u0002\n1!\u001a8e\u0003\u0011)g\u000e\u001a\u0011\u0002\tM$X\r]\u0001\u0006gR,\u0007\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0011\u0005\u0015\u0014qMA5\u0003W\u00022!a\u0007\u0001\u0011\u001d\t\u0019f\u0002a\u0001\u0003CAq!!\u0017\b\u0001\u0004\t\t\u0003C\u0004\u0002^\u001d\u0001\r!!\t\u0002\u0011%$XM]1u_J,\"!!\u001d\u0011\r\u0005}\u00121OA\u0011\u0013\u0011\t)(!\u0004\u0003\u0011%#XM]1u_J\fqa\u001d;faB,'/\u0006\u0003\u0002|\u0005\u001dE\u0003BA?\u0003\u000b\u0014b!a \u0002\u0004\u0006%fABAA\u0001\u0001\tiH\u0001\u0007=e\u00164\u0017N\\3nK:$h\b\u0005\u0003\u0002\u0006\u0006\u001dE\u0002\u0001\u0003\b\u0003\u0013K!\u0019AAF\u0005\u0005\u0019\u0016\u0003BAG\u0003'\u0003B!a\t\u0002\u0010&!\u0011\u0011SA\t\u0005\u001dqu\u000e\u001e5j]\u001e\u0004D!!&\u0002\u001eB1\u0011qHAL\u00037KA!!'\u0002\u000e\t91\u000b^3qa\u0016\u0014\b\u0003BAC\u0003;#A\"a(\u0002\b\u0006\u0005\t\u0011!B\u0001\u0003C\u00131a\u0018\u00132#\u0011\ti)a)\u0011\t\u0005\r\u0012QU\u0005\u0005\u0003O\u000b\tBA\u0002B]f\u0004B!a+\u0002@:!\u0011QVA^\u001d\u0011\ty+!/\u000f\t\u0005E\u0016qW\u0007\u0003\u0003gSA!!.\u0002\u0016\u00051AH]8pizJ!!a\u0005\n\t\u0005=\u0011\u0011C\u0005\u0005\u0003{\u000bi!A\u0004Ti\u0016\u0004\b/\u001a:\n\t\u0005\u0005\u00171\u0019\u0002\u000f\u000b\u001a4\u0017nY5f]R\u001c\u0006\u000f\\5u\u0015\u0011\ti,!\u0004\t\u000f\u0005\u001d\u0017\u0002q\u0001\u0002J\u0006)1\u000f[1qKBA\u0011qHAf\u0003C\t\u0019)\u0003\u0003\u0002N\u00065!\u0001D*uKB\u0004XM]*iCB,\u0017aA4baV\u0011\u00111\u001b\t\u0005\u0003G\t).\u0003\u0003\u0002X\u0006E!\u0001\u0002'p]\u001e\fq![:Fq\u0006\u001cG/\u0006\u0002\u0002^B!\u00111EAp\u0013\u0011\t\t/!\u0005\u0003\u000f\t{w\u000e\\3b]\u00069\u0001.Y:TiV\u0014\u0017A\u00037p]\u001edUM\\4uQ\u0006Y\u0011n]%oG2,8/\u001b<f\u0003\u001dI7/R7qif\f\u0001\"[:F[B$\u0018\u0010I\u0001\u0011]Vl'+\u00198hK\u0016cW-\\3oiN\fa\u0001\\3oORD\u0017a\u00037bgR,E.Z7f]R\fA\u0001\\1ti\u0006!\u0001.Z1e\u0003\u0011Ig.\u001b;\u0016\u0005\u0005\u0015\u0014\u0001\u0002;bS2\f1!\\1q+\u0011\u0011\tAa\u0002\u0015\t\t\r!1\u0002\t\u0007\u00037\tYC!\u0002\u0011\t\u0005\u0015%q\u0001\u0003\b\u0005\u0013A\"\u0019AAQ\u0005\u0005\u0011\u0005b\u0002B\u00071\u0001\u0007!qB\u0001\u0002MBA\u00111\u0005B\t\u0003C\u0011)!\u0003\u0003\u0003\u0014\u0005E!!\u0003$v]\u000e$\u0018n\u001c82\u0003\u0011\u0019w\u000e]=\u0015\u0015\u0005\u0015$\u0011\u0004B\u000e\u0005;\u0011y\u0002C\u0005\u0002Te\u0001\n\u00111\u0001\u0002\"!I\u0011\u0011L\r\u0011\u0002\u0003\u0007\u0011\u0011\u0005\u0005\n\u0003;J\u0002\u0013!a\u0001\u0003CA\u0011\"a:\u001a!\u0003\u0005\r!!8\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011!Q\u0005\u0016\u0005\u0003C\u00119c\u000b\u0002\u0003*A!!1\u0006B\u001b\u001b\t\u0011iC\u0003\u0003\u00030\tE\u0012!C;oG\",7m[3e\u0015\u0011\u0011\u0019$!\u0005\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u00038\t5\"!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012\u0014AD2paf$C-\u001a4bk2$HeM\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00135+\t\u0011\tE\u000b\u0003\u0002^\n\u001d\u0012A\u00012z)\u0011\t)Ga\u0012\t\u000f\u0005uc\u00041\u0001\u0002\"\u0005\tb/\u00197jI\u0006$X-T1y\u0019\u0016tw\r\u001e5\u0015\u0005\t5\u0003\u0003BA\u0012\u0005\u001fJAA!\u0015\u0002\u0012\t!QK\\5u\u0003-!Wm]2sSB$\u0018n\u001c8\u0016\u0005\t]\u0003\u0003\u0002B-\u0005CrAAa\u0017\u0003^A!\u0011\u0011WA\t\u0013\u0011\u0011y&!\u0005\u0002\rA\u0013X\rZ3g\u0013\u0011\u0011\u0019G!\u001a\u0003\rM#(/\u001b8h\u0015\u0011\u0011y&!\u0005\u0002\t\u0019\f\u0017\u000e\u001c\u000b\u0003\u0003\u001b\u000bQ!\u00199qYf$B!!\t\u0003p!9!\u0011\u000f\u0012A\u0002\u0005\u0005\u0012aA5eq\"*!E!\u001e\u0003\u0002B1\u00111\u0005B<\u0005wJAA!\u001f\u0002\u0012\t1A\u000f\u001b:poN\u0004B!a\u0012\u0003~%!!qPA)\u0005eIe\u000eZ3y\u001fV$xJ\u001a\"pk:$7/\u0012=dKB$\u0018n\u001c82\u000fy\u00119Fa!\u0003&FJ1E!\"\u0003\f\nm%QR\u000b\u0005\u0005+\u00129\t\u0002\u0005\u0003\n\u0006U!\u0019\u0001BJ\u0005\u0005!\u0016\u0002\u0002BG\u0005\u001f\u000b1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\n$\u0002\u0002BI\u0003#\ta\u0001\u001e5s_^\u001c\u0018\u0003BAG\u0005+\u0003B!a\u0012\u0003\u0018&!!\u0011TA)\u0005%!\u0006N]8xC\ndW-M\u0005$\u0005;\u0013yJ!)\u0003\u0012:!\u00111\u0005BP\u0013\u0011\u0011\t*!\u00052\u000f\t\n\u0019#!\u0005\u0003$\n)1oY1mCF\u001aaEa\u001f\u0002\u000f\u0019|'/Z1dQV!!1\u0016BZ)\u0011\u0011iE!,\t\u000f\t51\u00051\u0001\u00030BA\u00111\u0005B\t\u0003C\u0011\t\f\u0005\u0003\u0002\u0006\nMFa\u0003B[G\u0001\u0006\t\u0011!b\u0001\u0003C\u0013\u0011!\u0016\u0015\u0007\u0005g\u0013ILa0\u0011\t\u0005\r\"1X\u0005\u0005\u0005{\u000b\tBA\u0006ta\u0016\u001c\u0017.\u00197ju\u0016$\u0017g\u0002\u0013\u0003B\n\r'Q\u0019\b\u0005\u0003G\u0011\u0019-\u0003\u0003\u0003F\u0006E\u0011\u0001B+oSR\fq!\u001b8eKb|e-\u0006\u0003\u0003L\nMGCBA\u0011\u0005\u001b\u0014\t\u000fC\u0004\u0003P\u0012\u0002\rA!5\u0002\t\u0015dW-\u001c\t\u0005\u0003\u000b\u0013\u0019\u000eB\u0006\u0003\n\u0011\u0002\u000b\u0011!AC\u0002\tU\u0017\u0003BA\u0011\u0003GCcAa5\u0003:\ne\u0017g\u0002\u0013\u0003\\\nu'q\u001c\b\u0005\u0003G\u0011i.\u0003\u0003\u0003`\u0006E\u0011aA%oi\"I!1\u001d\u0013\u0011\u0002\u0003\u0007\u0011\u0011E\u0001\u0005MJ|W.A\tj]\u0012,\u0007p\u00144%I\u00164\u0017-\u001e7uII*BAa\t\u0003j\u0012Y!\u0011B\u0013!\u0002\u0003\u0005)\u0019\u0001BkQ\u0019\u0011IO!/\u0003nF:AEa7\u0003^\n}\u0017a\u00037bgRLe\u000eZ3y\u001f\u001a,BAa=\u0003zR1\u0011\u0011\u0005B{\u0005\u007fDqAa4'\u0001\u0004\u00119\u0010\u0005\u0003\u0002\u0006\neHa\u0003B\u0005M\u0001\u0006\t\u0011!b\u0001\u0005+DcA!?\u0003:\nu\u0018g\u0002\u0013\u0003\\\nu'q\u001c\u0005\n\u000332\u0003\u0013!a\u0001\u0003C\tQ\u0003\\1ti&sG-\u001a=PM\u0012\"WMZ1vYR$#'\u0006\u0003\u0003$\r\u0015Aa\u0003B\u0005O\u0001\u0006\t\u0011!b\u0001\u0005+Dca!\u0002\u0003:\u000e%\u0011g\u0002\u0013\u0003\\\nu'q\\\u0001\u0006a>\u001cxJ\u001a\u000b\u0005\u0003C\u0019y\u0001C\u0004\u0004\u0012!\u0002\r!!\t\u0002\u0003%\fAb]1nK\u0016cW-\\3oiN,Baa\u0006\u0004&Q!\u0011Q\\B\r\u0011\u001d\u0019Y\"\u000ba\u0001\u0007;\tA\u0001\u001e5biB1\u0011qIB\u0010\u0007GIAa!\t\u0002R\ta\u0011\n^3sC\ndWm\u00148dKB!\u0011QQB\u0013\t\u001d\u0011I!\u000bb\u0001\u0005+\fA\u0001^1lKR!\u0011QMB\u0016\u0011\u001d\u0019iC\u000ba\u0001\u0003C\t\u0011A\\\u0001\u0005IJ|\u0007\u000f\u0006\u0003\u0002f\rM\u0002bBB\u0017W\u0001\u0007\u0011\u0011E\u0001\ni\u0006\\WMU5hQR$B!!\u001a\u0004:!91Q\u0006\u0017A\u0002\u0005\u0005\u0012!\u00033s_B\u0014\u0016n\u001a5u)\u0011\t)ga\u0010\t\u000f\r5R\u00061\u0001\u0002\"\u0005a\u0011M]4UC.,w\u000b[5mKR!\u00111[B#\u0011\u001d\u00199E\fa\u0001\u0007\u0013\n\u0011\u0001\u001d\t\t\u0003G\u0011\t\"!\t\u0002^\u0006IA/Y6f/\"LG.\u001a\u000b\u0005\u0003K\u001ay\u0005C\u0004\u0004H=\u0002\ra!\u0013\u0002\u0013\u0011\u0014x\u000e],iS2,G\u0003BA3\u0007+Bqaa\u00121\u0001\u0004\u0019I%\u0001\u0003ta\u0006tG\u0003BB.\u0007C\u0002\u0002\"a\t\u0004^\u0005\u0015\u0014QM\u0005\u0005\u0007?\n\tB\u0001\u0004UkBdWM\r\u0005\b\u0007\u000f\n\u0004\u0019AB%\u0003\u0015\u0019H.[2f)\u0019\t)ga\u001a\u0004j!9!1\u001d\u001aA\u0002\u0005\u0005\u0002bBB6e\u0001\u0007\u0011\u0011E\u0001\u0006k:$\u0018\u000e\\\u0001\bgBd\u0017\u000e^!u)\u0011\u0019Yf!\u001d\t\u000f\r52\u00071\u0001\u0002\"\u0005qAn\\2bi&|g.\u00114uKJtE\u0003BA\u0011\u0007oBqa!\f5\u0001\u0004\t\t#A\u0007oK^,U\u000e\u001d;z%\u0006tw-\u001a\u000b\u0005\u0007{*I\u0005E\u0002\u0004\u0000Ys1!a\u0007H\u0003\u0015\u0011\u0016M\\4f!\r\tY\u0002S\n\u0006\u0011\u000e\u001d5Q\u0012\t\u0005\u0003G\u0019I)\u0003\u0003\u0004\f\u0006E!AB!osJ+g\r\u0005\u0003\u0004\u0010\u000eeUBABI\u0015\u0011\u0019\u0019j!&\u0002\u0005%|'BABL\u0003\u0011Q\u0017M^1\n\t\u0005=3\u0011\u0013\u000b\u0003\u0007\u0007\u000bQaY8v]R$\"\"!\t\u0004\"\u000e\r6QUBT\u0011\u001d\t\u0019F\u0013a\u0001\u0003CAq!!\u0017K\u0001\u0004\t\t\u0003C\u0004\u0002^)\u0003\r!!\t\t\u000f\u0005\u001d(\n1\u0001\u0002^RA\u0011\u0011EBV\u0007[\u001by\u000bC\u0004\u0002T-\u0003\r!!\t\t\u000f\u0005e3\n1\u0001\u0002\"!9\u0011QL&A\u0002\u0005\u0005B\u0003CB?\u0007g\u001b)la.\t\u000f\u0005MC\n1\u0001\u0002\"!9\u0011\u0011\f'A\u0002\u0005\u0005\u0002bBA/\u0019\u0002\u0007\u0011\u0011\u0005\u000b\u0007\u0007{\u001aYl!0\t\u000f\u0005MS\n1\u0001\u0002\"!9\u0011\u0011L'A\u0002\u0005\u0005\u0012!C5oG2,8/\u001b<f)!\u0019\u0019m!9\u0004d\u000e\u0015\bcAB@!\nI\u0011J\\2mkNLg/Z\n\u0004!\u0006\u0015D\u0003CBf\u0007\u001f\u001c\tna5\u0011\u0007\r5\u0007+D\u0001I\u0011\u001d\t\u0019\u0006\u0016a\u0001\u0003CAq!!\u0017U\u0001\u0004\t\t\u0003C\u0004\u0002^Q\u0003\r!!\t)\u000fA\u001b9n!8\u0004`B!\u00111EBm\u0013\u0011\u0019Y.!\u0005\u0003!M+'/[1m-\u0016\u00148/[8o+&#\u0015!\u0002<bYV,g$A\u0002\t\u000f\u0005Mc\n1\u0001\u0002\"!9\u0011\u0011\f(A\u0002\u0005\u0005\u0002bBA/\u001d\u0002\u0007\u0011\u0011\u0005\u000b\u0007\u0007\u0007\u001cIoa;\t\u000f\u0005Ms\n1\u0001\u0002\"!9\u0011\u0011L(A\u0002\u0005\u0005\"!C#yG2,8/\u001b<f'\r1\u0016Q\r\u000b\t\u0007g\u001c)pa>\u0004zB\u00191Q\u001a,\t\u000f\u0005M#\f1\u0001\u0002\"!9\u0011\u0011\f.A\u0002\u0005\u0005\u0002bBA/5\u0002\u0007\u0011\u0011\u0005\u0015\b-\u000e]7Q\\Bp\u0003\u0019\u0011\u0015nZ%oiB\u00191QZ/\u0003\r\tKw-\u00138u'\ri6q\u0011\u000b\u0003\u0007\u007f$\u0002\u0002\"\u0003\u0005\u001a\u0011mAQ\u0004\t\u0007\t\u0017!\t\u0002\"\u0006\u000f\t\u0005mAQB\u0005\u0005\t\u001f\tI!\u0001\u0007Ok6,'/[2SC:<W-\u0003\u0003\u0004n\u0012M!\u0002\u0002C\b\u0003\u0013\u0001B!a\u0012\u0005\u0018%!A\u0011AA)\u0011\u001d\t\u0019f\u0018a\u0001\t+Aq!!\u0017`\u0001\u0004!)\u0002C\u0004\u0002^}\u0003\r\u0001\"\u0006\u0015\u0011\u0011\u0005BQ\u0005C\u0014\tS\u0001b\u0001b\u0003\u0005$\u0011U\u0011\u0002BBc\t'Aq!a\u0015a\u0001\u0004!)\u0002C\u0004\u0002Z\u0001\u0004\r\u0001\"\u0006\t\u000f\u0005u\u0003\r1\u0001\u0005\u0016\u0005!Aj\u001c8h!\r\u0019iMY\n\u0004E\u000e\u001dEC\u0001C\u0017)!!)\u0004b\u000e\u0005:\u0011m\u0002C\u0002C\u0006\t#\t\u0019\u000eC\u0004\u0002T\u0011\u0004\r!a5\t\u000f\u0005eC\r1\u0001\u0002T\"9\u0011Q\f3A\u0002\u0005MG\u0003\u0003C \t\u0003\"\u0019\u0005\"\u0012\u0011\r\u0011-A1EAj\u0011\u001d\t\u0019&\u001aa\u0001\u0003'Dq!!\u0017f\u0001\u0004\t\u0019\u000eC\u0004\u0002^\u0015\u0004\r!a5\u0002\u0015\tKw\rR3dS6\fG\u000eE\u0002\u0004N\u001e\u0014!BQ5h\t\u0016\u001c\u0017.\\1m'\r97q\u0011\u000b\u0003\t\u0013\n\u0001CY5h\t\u0016\u001c\u0017i]%oi\u0016<'/\u00197\u0016\u0005\u0011U\u0003\u0003\u0002C,\t;rA!a\u0012\u0005Z%!A1LA)\u0003\u001dqU/\\3sS\u000eLA\u0001b\u0018\u0005b\t1\")[4EK\u000eLW.\u00197Bg&3\u0017J\u001c;fOJ\fGN\u0003\u0003\u0005\\\u0011\r$\u0002\u0002C3\u0003#\tA!\\1uQ\u0006\t\"-[4EK\u000e\f5/\u00138uK\u001e\u0014\u0018\r\u001c\u0011\u0015\u0011\u0011-D\u0011\u000fC:\tk\u0002b\u0001b\u0003\u0005\u0012\u00115\u0004\u0003BA$\t_JA\u0001b\u0013\u0002R!9\u00111K6A\u0002\u00115\u0004bBA-W\u0002\u0007AQ\u000e\u0005\b\u0003;Z\u0007\u0019\u0001C7)!!I\bb\u001f\u0005~\u0011}\u0004C\u0002C\u0006\tG!i\u0007C\u0004\u0002T1\u0004\r\u0001\"\u001c\t\u000f\u0005eC\u000e1\u0001\u0005n!9\u0011Q\f7A\u0002\u00115$a\u0002)beRL\u0017\r\\\u000b\u0007\t\u000b#)\n\"'\u0014\u00075$9\t\u0005\u0003\u0002$\u0011%\u0015\u0002\u0002CF\u0003#\u0011a!\u00118z-\u0006d\u0017aK:dC2\fGeY8mY\u0016\u001cG/[8oI%lW.\u001e;bE2,GEU1oO\u0016$\u0003+\u0019:uS\u0006dG\u0005\n4\u0016\u0005\u0011E\u0005\u0003CA\u0012\u0005#!\u0019\nb&\u0011\t\u0005\u0015EQ\u0013\u0003\b\u0005\u0013k'\u0019AAQ!\u0011\t)\t\"'\u0005\u000f\tUVN1\u0001\u0002\"\u0006a3oY1mC\u0012\u001aw\u000e\u001c7fGRLwN\u001c\u0013j[6,H/\u00192mK\u0012\u0012\u0016M\\4fIA\u000b'\u000f^5bY\u0012\"c\r\t\u000b\u0005\t?#\t\u000bE\u0004\u0004N6$\u0019\nb&\t\u000f\t5\u0001\u000f1\u0001\u0005\u0012R!Aq\u0013CS\u0011\u001d!9+\u001da\u0001\t'\u000b\u0011\u0001_\u0001\ti>\u001cFO]5oOR\u0011AQ\u0016\t\u0005\t_#),\u0004\u0002\u00052*!A1WBK\u0003\u0011a\u0017M\\4\n\t\t\rD\u0011W\u0001\tQ\u0006\u001c\bnQ8eKR\u0011\u0011\u0011E\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005uGq\u0018\u0005\n\t\u0003$\u0018\u0011!a\u0001\u0003G\u000b1\u0001\u001f\u00132\u0003\u001d\u0001\u0016M\u001d;jC2\u00042a!4w'\r18q\u0011\u000b\u0003\t\u000b\fABY=%Kb$XM\\:j_:,b\u0001b4\u0005\\\u0012UG\u0003\u0002Ci\t;$B\u0001b5\u0005XB!\u0011Q\u0011Ck\t\u001d\u0011)\f\u001fb\u0001\u0003CCq\u0001b*y\u0001\u0004!I\u000e\u0005\u0003\u0002\u0006\u0012mGa\u0002BEq\n\u0007\u0011\u0011\u0015\u0005\b\t?D\b\u0019\u0001Cq\u0003\u0015!C\u000f[5t!\u001d\u0019i-\u001cCm\t'\f!\u0003^8TiJLgn\u001a\u0013fqR,gn]5p]V1Aq\u001dCx\tg$B\u0001b+\u0005j\"9Aq\\=A\u0002\u0011-\bcBBg[\u00125H\u0011\u001f\t\u0005\u0003\u000b#y\u000fB\u0004\u0003\nf\u0014\r!!)\u0011\t\u0005\u0015E1\u001f\u0003\b\u0005kK(\u0019AAQ\u0003IA\u0017m\u001d5D_\u0012,G%\u001a=uK:\u001c\u0018n\u001c8\u0016\r\u0011eX\u0011AC\u0003)\u0011!I\fb?\t\u000f\u0011}'\u00101\u0001\u0005~B91QZ7\u0005\u0000\u0016\r\u0001\u0003BAC\u000b\u0003!qA!#{\u0005\u0004\t\t\u000b\u0005\u0003\u0002\u0006\u0016\u0015Aa\u0002B[u\n\u0007\u0011\u0011U\u0001\u0011KF,\u0018\r\\:%Kb$XM\\:j_:,b!b\u0003\u0006\u0018\u0015mA\u0003BC\u0007\u000b#!B!!8\u0006\u0010!IA\u0011Y>\u0002\u0002\u0003\u0007\u00111\u0015\u0005\b\t?\\\b\u0019AC\n!\u001d\u0019i-\\C\u000b\u000b3\u0001B!!\"\u0006\u0018\u00119!\u0011R>C\u0002\u0005\u0005\u0006\u0003BAC\u000b7!qA!.|\u0005\u0004\t\t\u000bE\u0002\u0004Nv\u001c2!`BD)\t)i\u0002\u0006\u0005\u0006&\u0015\u001dR\u0011FC\u0016!\u0019!Y\u0001\"\u0005\u0002\"!9\u00111K@A\u0002\u0005\u0005\u0002bBA-\u007f\u0002\u0007\u0011\u0011\u0005\u0005\b\u0003;z\b\u0019AA\u0011)!)y#\"\r\u00064\u0015U\u0002C\u0002C\u0006\tG\t\t\u0003\u0003\u0005\u0002T\u0005\u0005\u0001\u0019AA\u0011\u0011!\tI&!\u0001A\u0002\u0005\u0005\u0002\u0002CA/\u0003\u0003\u0001\r!!\t\u0002\u001f\u0015l\u0007\u000f^=SC:<W-\u0012:s_J$BA!&\u0006<!AQQHA\u0002\u0001\u0004\u00119&\u0001\u0003xQ\u0006$\u0018\u0001D<sSR,'+\u001a9mC\u000e,GCAC\"!\u0011!y+\"\u0012\n\t\u0015\u001dC\u0011\u0017\u0002\u0007\u001f\nTWm\u0019;\t\u000f\ruW\u00071\u0001\u0002\"\u00059!/\u001a<feN,\u0017\u0001C2p]R\f\u0017N\\:\u0015\t\u0005uW\u0011\u000b\u0005\b\tOC\u0004\u0019AA\u0011+\u0011))&b\u0017\u0015\t\u0005uWq\u000b\u0005\b\u0005\u001fL\u0004\u0019AC-!\u0011\t))b\u0017\u0005\u000f\t%\u0011H1\u0001\u0003V\u0006\u00191/^7\u0016\t\u0015\u0005Tq\u000e\u000b\u0005\u0003C)\u0019\u0007C\u0004\u0006fi\u0002\u001d!b\u001a\u0002\u00079,X\u000e\u0005\u0004\u0002H\u0015%TQN\u0005\u0005\u000bW\n\tFA\u0004Ok6,'/[2\u0011\t\u0005\u0015Uq\u000e\u0003\b\u0005\u0013Q$\u0019\u0001Bk\u0003\ri\u0017N\\\u000b\u0005\u000bk*\u0019\t\u0006\u0003\u0002\"\u0015]\u0004bBC=w\u0001\u000fQ1P\u0001\u0004_J$\u0007CBA$\u000b{*\t)\u0003\u0003\u0006\u0000\u0005E#\u0001C(sI\u0016\u0014\u0018N\\4\u0011\t\u0005\u0015U1\u0011\u0003\b\u000b\u000b[$\u0019\u0001Bk\u0005\t\t\u0015'A\u0002nCb,B!b#\u0006\u0014R!\u0011\u0011ECG\u0011\u001d)I\b\u0010a\u0002\u000b\u001f\u0003b!a\u0012\u0006~\u0015E\u0005\u0003BAC\u000b'#q!\"\"=\u0005\u0004\u0011).A\u0003uC&d7/\u0006\u0002\u0006\u001aB1\u0011qHA:\u0003K\nQ!\u001b8jiN\fq#\u00199qYf\u0004&/\u001a4feJ,G-T1y\u0019\u0016tw\r\u001e5\u0015\t\u0005uW\u0011\u0015\u0005\b\u000bG\u0003\u0005\u0019AAR\u0003\u0015yG\u000f[3s)\t\u00119&A\u0005dY\u0006\u001c8OT1nKV\u0011AQV\u0001\tI&\u001cH/\u001b8di\u00069qM]8va\u0016$G\u0003BCM\u000bcCq!b-F\u0001\u0004\t\t#\u0001\u0003tSj,\u0017AB:peR,G-\u0006\u0003\u0006:\u0016\u0005G\u0003BA\u0015\u000bwCq!\"\u001fG\u0001\b)i\f\u0005\u0004\u0002H\u0015uTq\u0018\t\u0005\u0003\u000b+\t\rB\u0004\u0003\n\u0019\u0013\rA!6*\u0007\u00011\u0006\u000bK\u0004\u0001\u0007/\u001cina8"
)
public abstract class Range extends AbstractSeq implements IndexedSeq, StrictOptimizedSeqOps, Serializable {
   private static final long serialVersionUID = 3L;
   private final int start;
   private final int end;
   private final int step;
   private final boolean isEmpty;
   public final int scala$collection$immutable$Range$$numRangeElements;
   public final int scala$collection$immutable$Range$$lastElement;

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

   public Tuple2 unzip(final Function1 asPair) {
      return StrictOptimizedIterableOps.unzip$(this, asPair);
   }

   public Tuple3 unzip3(final Function1 asTriple) {
      return StrictOptimizedIterableOps.unzip3$(this, asTriple);
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

   public SeqFactory iterableFactory() {
      return IndexedSeq.iterableFactory$(this);
   }

   // $FF: synthetic method
   public Object scala$collection$immutable$IndexedSeqOps$$super$slice(final int from, final int until) {
      return scala.collection.IndexedSeqOps.slice$(this, from, until);
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

   public int start() {
      return this.start;
   }

   public int end() {
      return this.end;
   }

   public int step() {
      return this.step;
   }

   public final Iterator iterator() {
      return new RangeIterator(this.start(), this.step(), this.scala$collection$immutable$Range$$lastElement, this.isEmpty());
   }

   public final Stepper stepper(final StepperShape shape) {
      RangeStepper st = new RangeStepper(this.start(), this.step(), 0, this.length());
      if (shape.shape() == StepperShape$.MODULE$.IntShape()) {
         return st;
      } else if (shape.shape() != StepperShape$.MODULE$.ReferenceShape()) {
         throw new AssertionError((new StringBuilder(18)).append("assertion failed: ").append($anonfun$stepper$1(shape)).toString());
      } else {
         AnyStepper$ var10000 = AnyStepper$.MODULE$;
         return new Stepper.EfficientSplit(st) {
         };
      }
   }

   private long gap() {
      return (long)this.end() - (long)this.start();
   }

   private boolean isExact() {
      return this.gap() % (long)this.step() == 0L;
   }

   private boolean hasStub() {
      return this.isInclusive() || !this.isExact();
   }

   private long longLength() {
      return this.gap() / (long)this.step() + (long)(this.hasStub() ? 1 : 0);
   }

   public abstract boolean isInclusive();

   public final boolean isEmpty() {
      return this.isEmpty;
   }

   public final int length() {
      if (this.scala$collection$immutable$Range$$numRangeElements < 0) {
         throw this.fail();
      } else {
         return this.scala$collection$immutable$Range$$numRangeElements;
      }
   }

   public final int last() {
      if (this.isEmpty()) {
         throw Range$.MODULE$.scala$collection$immutable$Range$$emptyRangeError("last");
      } else {
         return this.scala$collection$immutable$Range$$lastElement;
      }
   }

   public final int head() {
      if (this.isEmpty()) {
         throw Range$.MODULE$.scala$collection$immutable$Range$$emptyRangeError("head");
      } else {
         return this.start();
      }
   }

   public final Range init() {
      if (this.isEmpty()) {
         throw Range$.MODULE$.scala$collection$immutable$Range$$emptyRangeError("init");
      } else {
         return this.dropRight(1);
      }
   }

   public final Range tail() {
      if (this.isEmpty()) {
         throw Range$.MODULE$.scala$collection$immutable$Range$$emptyRangeError("tail");
      } else if (this.scala$collection$immutable$Range$$numRangeElements == 1) {
         return this.newEmptyRange(this.end());
      } else {
         return (Range)(this.isInclusive() ? new Inclusive(this.start() + this.step(), this.end(), this.step()) : new Exclusive(this.start() + this.step(), this.end(), this.step()));
      }
   }

   public IndexedSeq map(final Function1 f) {
      this.scala$collection$immutable$Range$$validateMaxLength();
      Builder strictOptimizedMap_b = IndexedSeq$.MODULE$.newBuilder();

      Object var5;
      for(Iterator strictOptimizedMap_it = this.iterator(); strictOptimizedMap_it.hasNext(); var5 = null) {
         var5 = f.apply(strictOptimizedMap_it.next());
         if (strictOptimizedMap_b == null) {
            throw null;
         }

         strictOptimizedMap_b.addOne(var5);
      }

      return (IndexedSeq)strictOptimizedMap_b.result();
   }

   public final Range copy(final int start, final int end, final int step, final boolean isInclusive) {
      return (Range)(isInclusive ? new Inclusive(start, end, step) : new Exclusive(start, end, step));
   }

   public final int copy$default$1() {
      return this.start();
   }

   public final int copy$default$2() {
      return this.end();
   }

   public final int copy$default$3() {
      return this.step();
   }

   public final boolean copy$default$4() {
      return this.isInclusive();
   }

   public final Range by(final int step) {
      return this.copy(this.start(), this.end(), step, this.isInclusive());
   }

   public void scala$collection$immutable$Range$$validateMaxLength() {
      if (this.scala$collection$immutable$Range$$numRangeElements < 0) {
         throw this.fail();
      }
   }

   private String description() {
      return StringOps$.MODULE$.format$extension("%d %s %d by %s", ScalaRunTime$.MODULE$.genericWrapArray(new Object[]{this.start(), this.isInclusive() ? "to" : "until", this.end(), this.step()}));
   }

   private Nothing$ fail() {
      throw new IllegalArgumentException((new StringBuilder(54)).append(this.description()).append(": seqs cannot contain more than Int.MaxValue elements.").toString());
   }

   public final int apply(final int idx) throws IndexOutOfBoundsException {
      return this.apply$mcII$sp(idx);
   }

   public final void foreach(final Function1 f) {
      if (!this.isEmpty()) {
         int i = this.start();

         while(true) {
            f.apply(i);
            if (i == this.scala$collection$immutable$Range$$lastElement) {
               return;
            }

            i += this.step();
         }
      }
   }

   public final int indexOf(final Object elem, final int from) {
      if (elem instanceof Integer) {
         int var3 = BoxesRunTime.unboxToInt(elem);
         int pos = this.scala$collection$immutable$Range$$posOf(var3);
         return pos >= from ? pos : -1;
      } else {
         return scala.collection.SeqOps.indexOf$(this, elem, from);
      }
   }

   public final int indexOf$default$2() {
      return 0;
   }

   public final int lastIndexOf(final Object elem, final int end) {
      if (elem instanceof Integer) {
         int var3 = BoxesRunTime.unboxToInt(elem);
         int pos = this.scala$collection$immutable$Range$$posOf(var3);
         return pos <= end ? pos : -1;
      } else {
         return scala.collection.SeqOps.lastIndexOf$(this, elem, end);
      }
   }

   public final int lastIndexOf$default$2() {
      return this.length() - 1;
   }

   public int scala$collection$immutable$Range$$posOf(final int i) {
      return this.contains(i) ? (i - this.start()) / this.step() : -1;
   }

   public boolean sameElements(final IterableOnce that) {
      if (that instanceof Range) {
         Range var2 = (Range)that;
         int var3 = this.length();
         switch (var3) {
            case 0:
               return var2.isEmpty();
            case 1:
               if (var2.length() == 1 && this.start() == var2.start()) {
                  return true;
               }

               return false;
            default:
               return var2.length() == var3 && this.start() == var2.start() && this.step() == var2.step();
         }
      } else {
         return IndexedSeq.sameElements$(this, that);
      }
   }

   public final Range take(final int n) {
      if (n > 0 && !this.isEmpty()) {
         return (Range)(n >= this.scala$collection$immutable$Range$$numRangeElements && this.scala$collection$immutable$Range$$numRangeElements >= 0 ? this : new Inclusive(this.start(), this.locationAfterN(n - 1), this.step()));
      } else {
         return this.newEmptyRange(this.start());
      }
   }

   public final Range drop(final int n) {
      if (n > 0 && !this.isEmpty()) {
         return (Range)(n >= this.scala$collection$immutable$Range$$numRangeElements && this.scala$collection$immutable$Range$$numRangeElements >= 0 ? this.newEmptyRange(this.end()) : this.copy(this.locationAfterN(n), this.end(), this.step(), this.isInclusive()));
      } else {
         return this;
      }
   }

   public final Range takeRight(final int n) {
      if (n <= 0) {
         return this.newEmptyRange(this.start());
      } else if (this.scala$collection$immutable$Range$$numRangeElements >= 0) {
         return this.drop(this.scala$collection$immutable$Range$$numRangeElements - n);
      } else {
         int y = this.last();
         long x = (long)y - (long)this.step() * (long)(n - 1);
         if ((this.step() <= 0 || x >= (long)this.start()) && (this.step() >= 0 || x <= (long)this.start())) {
            Range$ var10000 = Range$.MODULE$;
            int var7 = (int)x;
            int inclusive_step = this.step();
            int inclusive_start = var7;
            return new Inclusive(inclusive_start, y, inclusive_step);
         } else {
            return this;
         }
      }
   }

   public final Range dropRight(final int n) {
      if (n <= 0) {
         return this;
      } else if (this.scala$collection$immutable$Range$$numRangeElements >= 0) {
         return this.take(this.scala$collection$immutable$Range$$numRangeElements - n);
      } else {
         int y = this.last() - this.step() * n;
         if ((this.step() <= 0 || y >= this.start()) && (this.step() >= 0 || y <= this.start())) {
            Range$ var10000 = Range$.MODULE$;
            int var5 = this.start();
            int inclusive_step = this.step();
            int inclusive_start = var5;
            return new Inclusive(inclusive_start, y, inclusive_step);
         } else {
            return this.newEmptyRange(this.start());
         }
      }
   }

   private long argTakeWhile(final Function1 p) {
      if (this.isEmpty()) {
         return (long)this.start();
      } else {
         int current = this.start();

         int stop;
         for(stop = this.last(); current != stop && p.apply$mcZI$sp(current); current += this.step()) {
         }

         return current == stop && p.apply$mcZI$sp(current) ? (long)current + (long)this.step() : (long)current;
      }
   }

   public final Range takeWhile(final Function1 p) {
      long var10000;
      if (this.isEmpty()) {
         var10000 = (long)this.start();
      } else {
         int argTakeWhile_current = this.start();

         int argTakeWhile_stop;
         for(argTakeWhile_stop = this.last(); argTakeWhile_current != argTakeWhile_stop && p.apply$mcZI$sp(argTakeWhile_current); argTakeWhile_current += this.step()) {
         }

         var10000 = argTakeWhile_current == argTakeWhile_stop && p.apply$mcZI$sp(argTakeWhile_current) ? (long)argTakeWhile_current + (long)this.step() : (long)argTakeWhile_current;
      }

      long stop = var10000;
      if (stop == (long)this.start()) {
         return this.newEmptyRange(this.start());
      } else {
         int x = (int)(stop - (long)this.step());
         if (x == this.last()) {
            return this;
         } else {
            Range$ var9 = Range$.MODULE$;
            int var10 = this.start();
            int inclusive_step = this.step();
            int inclusive_start = var10;
            return new Inclusive(inclusive_start, x, inclusive_step);
         }
      }
   }

   public final Range dropWhile(final Function1 p) {
      long var10000;
      if (this.isEmpty()) {
         var10000 = (long)this.start();
      } else {
         int argTakeWhile_current = this.start();

         int argTakeWhile_stop;
         for(argTakeWhile_stop = this.last(); argTakeWhile_current != argTakeWhile_stop && p.apply$mcZI$sp(argTakeWhile_current); argTakeWhile_current += this.step()) {
         }

         var10000 = argTakeWhile_current == argTakeWhile_stop && p.apply$mcZI$sp(argTakeWhile_current) ? (long)argTakeWhile_current + (long)this.step() : (long)argTakeWhile_current;
      }

      long stop = var10000;
      if (stop == (long)this.start()) {
         return this;
      } else {
         int x = (int)(stop - (long)this.step());
         if (x == this.last()) {
            return this.newEmptyRange(this.last());
         } else {
            Range$ var10 = Range$.MODULE$;
            int var11 = x + this.step();
            int var10001 = this.last();
            int inclusive_step = this.step();
            int inclusive_end = var10001;
            int inclusive_start = var11;
            return new Inclusive(inclusive_start, inclusive_end, inclusive_step);
         }
      }
   }

   public final Tuple2 span(final Function1 p) {
      long var10000;
      if (this.isEmpty()) {
         var10000 = (long)this.start();
      } else {
         int argTakeWhile_current = this.start();

         int argTakeWhile_stop;
         for(argTakeWhile_stop = this.last(); argTakeWhile_current != argTakeWhile_stop && p.apply$mcZI$sp(argTakeWhile_current); argTakeWhile_current += this.step()) {
         }

         var10000 = argTakeWhile_current == argTakeWhile_stop && p.apply$mcZI$sp(argTakeWhile_current) ? (long)argTakeWhile_current + (long)this.step() : (long)argTakeWhile_current;
      }

      long border = var10000;
      if (border == (long)this.start()) {
         return new Tuple2(this.newEmptyRange(this.start()), this);
      } else {
         int x = (int)(border - (long)this.step());
         if (x == this.last()) {
            return new Tuple2(this, this.newEmptyRange(this.last()));
         } else {
            Range$ var10002 = Range$.MODULE$;
            int var12 = this.start();
            int inclusive_step = this.step();
            int inclusive_start = var12;
            Inclusive var13 = new Inclusive(inclusive_start, x, inclusive_step);
            Range$ var10003 = Range$.MODULE$;
            int var14 = x + this.step();
            int var10004 = this.last();
            int inclusive_step = this.step();
            int inclusive_end = var10004;
            int inclusive_start = var14;
            return new Tuple2(var13, new Inclusive(inclusive_start, inclusive_end, inclusive_step));
         }
      }
   }

   public final Range slice(final int from, final int until) {
      if (from <= 0) {
         return this.take(until);
      } else if (until >= this.scala$collection$immutable$Range$$numRangeElements && this.scala$collection$immutable$Range$$numRangeElements >= 0) {
         return this.drop(from);
      } else {
         int fromValue = this.locationAfterN(from);
         if (from >= until) {
            return this.newEmptyRange(fromValue);
         } else {
            Range$ var10000 = Range$.MODULE$;
            int var6 = this.locationAfterN(until - 1);
            int inclusive_step = this.step();
            int inclusive_end = var6;
            return new Inclusive(fromValue, inclusive_end, inclusive_step);
         }
      }
   }

   public final Tuple2 splitAt(final int n) {
      return new Tuple2(this.take(n), this.drop(n));
   }

   private int locationAfterN(final int n) {
      return this.start() + this.step() * n;
   }

   private Exclusive newEmptyRange(final int value) {
      return new Exclusive(value, value, this.step());
   }

   public final Range reverse() {
      return (Range)(this.isEmpty() ? this : new Inclusive(this.last(), this.start(), -this.step()));
   }

   public final Range inclusive() {
      return (Range)(this.isInclusive() ? this : new Inclusive(this.start(), this.end(), this.step()));
   }

   public final boolean contains(final int x) {
      if (x == this.end() && !this.isInclusive()) {
         return false;
      } else if (this.step() > 0) {
         if (x >= this.start() && x <= this.end()) {
            return this.step() == 1 || Integer.remainderUnsigned(x - this.start(), this.step()) == 0;
         } else {
            return false;
         }
      } else if (x >= this.end() && x <= this.start()) {
         return this.step() == -1 || Integer.remainderUnsigned(this.start() - x, -this.step()) == 0;
      } else {
         return false;
      }
   }

   public final boolean contains(final Object elem) {
      if (elem instanceof Integer) {
         int var2 = BoxesRunTime.unboxToInt(elem);
         return this.contains(var2);
      } else {
         return scala.collection.SeqOps.contains$(this, elem);
      }
   }

   public final int sum(final Numeric num) {
      if (num == Numeric.IntIsIntegral$.MODULE$) {
         if (this.isEmpty()) {
            return 0;
         } else {
            return this.length() == 1 ? this.head() : (int)((long)this.length() * ((long)this.head() + (long)this.last()) / 2L);
         }
      } else if (this.isEmpty()) {
         return num.toInt(num.zero());
      } else {
         Object acc = num.zero();
         int i = this.head();

         while(true) {
            acc = num.plus(acc, i);
            if (i == this.scala$collection$immutable$Range$$lastElement) {
               return num.toInt(acc);
            }

            i += this.step();
         }
      }
   }

   public final int min(final Ordering ord) {
      if (ord == Ordering.Int$.MODULE$) {
         return this.step() > 0 ? this.head() : this.last();
      } else if (Ordering.CachedReverse.isReverseOf$(Ordering.Int$.MODULE$, ord)) {
         return this.step() > 0 ? this.last() : this.head();
      } else {
         return BoxesRunTime.unboxToInt(IterableOnceOps.min$(this, ord));
      }
   }

   public final int max(final Ordering ord) {
      if (ord == Ordering.Int$.MODULE$) {
         return this.step() > 0 ? this.last() : this.head();
      } else if (Ordering.CachedReverse.isReverseOf$(Ordering.Int$.MODULE$, ord)) {
         return this.step() > 0 ? this.head() : this.last();
      } else {
         return BoxesRunTime.unboxToInt(IterableOnceOps.max$(this, ord));
      }
   }

   public Iterator tails() {
      return new AbstractIterator() {
         private int i;
         // $FF: synthetic field
         private final Range $outer;

         public boolean hasNext() {
            return this.i <= this.$outer.length();
         }

         public Range next() {
            if (this.hasNext()) {
               Range res = this.$outer.drop(this.i);
               ++this.i;
               return res;
            } else {
               Iterator$ var10000 = Iterator$.MODULE$;
               return (Range)Iterator$.scala$collection$Iterator$$_empty.next();
            }
         }

         public {
            if (Range.this == null) {
               throw null;
            } else {
               this.$outer = Range.this;
               this.i = 0;
            }
         }
      };
   }

   public Iterator inits() {
      return new AbstractIterator() {
         private int i;
         // $FF: synthetic field
         private final Range $outer;

         public boolean hasNext() {
            return this.i <= this.$outer.length();
         }

         public Range next() {
            if (this.hasNext()) {
               Range res = this.$outer.dropRight(this.i);
               ++this.i;
               return res;
            } else {
               Iterator$ var10000 = Iterator$.MODULE$;
               return (Range)Iterator$.scala$collection$Iterator$$_empty.next();
            }
         }

         public {
            if (Range.this == null) {
               throw null;
            } else {
               this.$outer = Range.this;
               this.i = 0;
            }
         }
      };
   }

   public final int applyPreferredMaxLength() {
      return Integer.MAX_VALUE;
   }

   public final boolean equals(final Object other) {
      if (other instanceof Range) {
         Range var2 = (Range)other;
         if (this.isEmpty()) {
            return var2.isEmpty();
         } else {
            if (var2.nonEmpty() && this.start() == var2.start()) {
               int l0 = this.last();
               if (l0 == var2.last() && (this.start() == l0 || this.step() == var2.step())) {
                  return true;
               }
            }

            return false;
         }
      } else {
         return scala.collection.Seq.equals$(this, other);
      }
   }

   public final int hashCode() {
      return this.length() >= 2 ? MurmurHash3$.MODULE$.rangeHash(this.start(), this.step(), this.scala$collection$immutable$Range$$lastElement) : MurmurHash3$.MODULE$.seqHash(this);
   }

   public final String toString() {
      String preposition = this.isInclusive() ? "to" : "until";
      String stepped = this.step() == 1 ? "" : (new StringBuilder(4)).append(" by ").append(this.step()).toString();
      String prefix = this.isEmpty() ? "empty " : (!this.isExact() ? "inexact " : "");
      return (new StringBuilder(8)).append(prefix).append("Range ").append(this.start()).append(" ").append(preposition).append(" ").append(this.end()).append(stepped).toString();
   }

   public String className() {
      return "Range";
   }

   public Range distinct() {
      return this;
   }

   public Iterator grouped(final int size) {
      if (size < 1) {
         throw new IllegalArgumentException((new StringBuilder(20)).append("requirement failed: ").append($anonfun$grouped$1(size)).toString());
      } else if (this.isEmpty()) {
         Iterator$ var10000 = Iterator$.MODULE$;
         return Iterator$.scala$collection$Iterator$$_empty;
      } else {
         return new AbstractIterator(size) {
            private int i;
            // $FF: synthetic field
            private final Range $outer;
            private final int s$1;

            public boolean hasNext() {
               return this.$outer.length() > this.i;
            }

            public Range next() {
               if (this.hasNext()) {
                  Range x = this.$outer.slice(this.i, this.i + this.s$1);
                  this.i += this.s$1;
                  return x;
               } else {
                  Iterator$ var10000 = Iterator$.MODULE$;
                  return (Range)Iterator$.scala$collection$Iterator$$_empty.next();
               }
            }

            public {
               if (Range.this == null) {
                  throw null;
               } else {
                  this.$outer = Range.this;
                  this.s$1 = s$1;
                  this.i = 0;
               }
            }
         };
      }
   }

   public IndexedSeq sorted(final Ordering ord) {
      if (ord == Ordering.Int$.MODULE$) {
         return this.step() > 0 ? this : this.reverse();
      } else {
         return (IndexedSeq)scala.collection.SeqOps.sorted$(this, ord);
      }
   }

   public final void foreach$mVc$sp(final Function1 f) {
      if (!this.isEmpty()) {
         int i = this.start();

         while(true) {
            f.apply$mcVI$sp(i);
            if (i == this.scala$collection$immutable$Range$$lastElement) {
               return;
            }

            i += this.step();
         }
      }
   }

   public final int indexOf$mIc$sp(final int elem, final int from) {
      int pos = this.scala$collection$immutable$Range$$posOf(elem);
      return pos >= from ? pos : -1;
   }

   public final int lastIndexOf$mIc$sp(final int elem, final int end) {
      int pos = this.scala$collection$immutable$Range$$posOf(elem);
      return pos <= end ? pos : -1;
   }

   public final int apply$mcII$sp(final int idx) throws IndexOutOfBoundsException {
      this.scala$collection$immutable$Range$$validateMaxLength();
      if (idx >= 0 && idx < this.scala$collection$immutable$Range$$numRangeElements) {
         return this.start() + this.step() * idx;
      } else {
         throw CommonErrors$.MODULE$.indexOutOfBounds(idx, this.scala$collection$immutable$Range$$numRangeElements - 1);
      }
   }

   // $FF: synthetic method
   public static final String $anonfun$stepper$1(final StepperShape shape$1) {
      return (new StringBuilder(25)).append("unexpected StepperShape: ").append(shape$1).toString();
   }

   // $FF: synthetic method
   public static final String $anonfun$grouped$1(final int size$1) {
      return StringOps$.MODULE$.format$extension("size=%d, but size must be positive", ScalaRunTime$.MODULE$.genericWrapArray(new Object[]{size$1}));
   }

   public Range(final int start, final int end, final int step) {
      this.start = start;
      this.end = end;
      this.step = step;
      this.isEmpty = start > end && step > 0 || start < end && step < 0 || start == end && !this.isInclusive();
      if (step == 0) {
         throw new IllegalArgumentException("step cannot be 0.");
      } else {
         int var10001;
         if (this.isEmpty()) {
            var10001 = 0;
         } else {
            long len = this.longLength();
            var10001 = len > 2147483647L ? -1 : (int)len;
         }

         this.scala$collection$immutable$Range$$numRangeElements = var10001;
         switch (step) {
            case -1:
               var10001 = this.isInclusive() ? end : end + 1;
               break;
            case 1:
               var10001 = this.isInclusive() ? end : end - 1;
               break;
            default:
               int remainder = (int)(this.gap() % (long)step);
               var10001 = remainder != 0 ? end - remainder : (this.isInclusive() ? end : end - step);
         }

         this.scala$collection$immutable$Range$$lastElement = var10001;
      }
   }

   public static final class Inclusive extends Range {
      private static final long serialVersionUID = 3L;

      public boolean isInclusive() {
         return true;
      }

      public Inclusive(final int start, final int end, final int step) {
         super(start, end, step);
      }
   }

   public static final class Exclusive extends Range {
      private static final long serialVersionUID = 3L;

      public boolean isInclusive() {
         return false;
      }

      public Exclusive(final int start, final int end, final int step) {
         super(start, end, step);
      }
   }

   public static class BigInt$ {
      public static final BigInt$ MODULE$ = new BigInt$();

      public NumericRange.Exclusive apply(final BigInt start, final BigInt end, final BigInt step) {
         NumericRange$ var10000 = NumericRange$.MODULE$;
         Integral apply_num = Numeric.BigIntIsIntegral$.MODULE$;
         return new NumericRange.Exclusive(start, end, step, apply_num);
      }

      public NumericRange.Inclusive inclusive(final BigInt start, final BigInt end, final BigInt step) {
         NumericRange$ var10000 = NumericRange$.MODULE$;
         Integral inclusive_num = Numeric.BigIntIsIntegral$.MODULE$;
         return new NumericRange.Inclusive(start, end, step, inclusive_num);
      }
   }

   public static class Long$ {
      public static final Long$ MODULE$ = new Long$();

      public NumericRange.Exclusive apply(final long start, final long end, final long step) {
         NumericRange$ var10000 = NumericRange$.MODULE$;
         Long var11 = start;
         Long var10001 = end;
         Long var10002 = step;
         Numeric.LongIsIntegral$ apply_num = Numeric.LongIsIntegral$.MODULE$;
         Long apply_step = var10002;
         Long apply_end = var10001;
         Object apply_start = var11;
         return new NumericRange.Exclusive(apply_start, apply_end, apply_step, apply_num);
      }

      public NumericRange.Inclusive inclusive(final long start, final long end, final long step) {
         NumericRange$ var10000 = NumericRange$.MODULE$;
         Long var11 = start;
         Long var10001 = end;
         Long var10002 = step;
         Numeric.LongIsIntegral$ inclusive_num = Numeric.LongIsIntegral$.MODULE$;
         Long inclusive_step = var10002;
         Long inclusive_end = var10001;
         Object inclusive_start = var11;
         return new NumericRange.Inclusive(inclusive_start, inclusive_end, inclusive_step, inclusive_num);
      }
   }

   public static class BigDecimal$ {
      public static final BigDecimal$ MODULE$ = new BigDecimal$();
      private static final Numeric.BigDecimalAsIfIntegral bigDecAsIntegral;

      static {
         bigDecAsIntegral = Numeric.BigDecimalAsIfIntegral$.MODULE$;
      }

      public Numeric.BigDecimalAsIfIntegral bigDecAsIntegral() {
         return bigDecAsIntegral;
      }

      public NumericRange.Exclusive apply(final BigDecimal start, final BigDecimal end, final BigDecimal step) {
         NumericRange$ var10000 = NumericRange$.MODULE$;
         Integral apply_num = this.bigDecAsIntegral();
         return new NumericRange.Exclusive(start, end, step, apply_num);
      }

      public NumericRange.Inclusive inclusive(final BigDecimal start, final BigDecimal end, final BigDecimal step) {
         NumericRange$ var10000 = NumericRange$.MODULE$;
         Integral inclusive_num = this.bigDecAsIntegral();
         return new NumericRange.Inclusive(start, end, step, inclusive_num);
      }
   }

   public static final class Partial {
      private final Function1 scala$collection$immutable$Range$Partial$$f;

      public Function1 scala$collection$immutable$Range$Partial$$f() {
         return this.scala$collection$immutable$Range$Partial$$f;
      }

      public Object by(final Object x) {
         Partial$ var10000 = Range.Partial$.MODULE$;
         return this.scala$collection$immutable$Range$Partial$$f().apply(x);
      }

      public String toString() {
         Partial$ var10000 = Range.Partial$.MODULE$;
         this.scala$collection$immutable$Range$Partial$$f();
         return "Range requires step";
      }

      public int hashCode() {
         Partial$ var10000 = Range.Partial$.MODULE$;
         return this.scala$collection$immutable$Range$Partial$$f().hashCode();
      }

      public boolean equals(final Object x$1) {
         return Range.Partial$.MODULE$.equals$extension(this.scala$collection$immutable$Range$Partial$$f(), x$1);
      }

      public Partial(final Function1 f) {
         this.scala$collection$immutable$Range$Partial$$f = f;
      }
   }

   public static class Partial$ {
      public static final Partial$ MODULE$ = new Partial$();

      public final Object by$extension(final Function1 $this, final Object x) {
         return $this.apply(x);
      }

      public final String toString$extension(final Function1 $this) {
         return "Range requires step";
      }

      public final int hashCode$extension(final Function1 $this) {
         return $this.hashCode();
      }

      public final boolean equals$extension(final Function1 $this, final Object x$1) {
         if (x$1 instanceof Partial) {
            Function1 var3 = x$1 == null ? null : ((Partial)x$1).scala$collection$immutable$Range$Partial$$f();
            if ($this == null) {
               if (var3 == null) {
                  return true;
               }
            } else if ($this.equals(var3)) {
               return true;
            }
         }

         return false;
      }
   }

   public static class Int$ {
      public static final Int$ MODULE$ = new Int$();

      public NumericRange.Exclusive apply(final int start, final int end, final int step) {
         NumericRange$ var10000 = NumericRange$.MODULE$;
         Integer var8 = start;
         Integer var10001 = end;
         Integer var10002 = step;
         Numeric.IntIsIntegral$ apply_num = Numeric.IntIsIntegral$.MODULE$;
         Integer apply_step = var10002;
         Integer apply_end = var10001;
         Object apply_start = var8;
         return new NumericRange.Exclusive(apply_start, apply_end, apply_step, apply_num);
      }

      public NumericRange.Inclusive inclusive(final int start, final int end, final int step) {
         NumericRange$ var10000 = NumericRange$.MODULE$;
         Integer var8 = start;
         Integer var10001 = end;
         Integer var10002 = step;
         Numeric.IntIsIntegral$ inclusive_num = Numeric.IntIsIntegral$.MODULE$;
         Integer inclusive_step = var10002;
         Integer inclusive_end = var10001;
         Object inclusive_start = var8;
         return new NumericRange.Inclusive(inclusive_start, inclusive_end, inclusive_step, inclusive_num);
      }
   }
}
